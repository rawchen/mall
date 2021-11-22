package com.rawchen.common.utils;

import com.rawchen.common.base.BaseInterceptor;
import com.rawchen.common.entity.Dialect;
import com.rawchen.common.entity.MySQLDialect;
import com.rawchen.common.entity.Page;
import com.rawchen.common.entity.SQLHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author RawChen
 * @since 2021-11-02 9:28
 */
@Component
@Intercepts({@Signature(
		type = Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class PaginationInterceptor extends BaseInterceptor {
	private static final long serialVersionUID = 1L;

	public PaginationInterceptor() {
	}

	protected Dialect getDialect() {
		Object dialect = null;

		String dbType;
		try {
			dbType = ((DatabaseIdProvider) SpringContextHolder.getBean(DatabaseIdProvider.class)).getDatabaseId((DataSource)SpringContextHolder.getBean(DataSource.class));
		} catch (Exception e) {
			dbType = "mysql";
		}

		if ("mysql".equals(dbType)) {
			dialect = new MySQLDialect();
		}

//		else if ("dm".equals(dbType)) {
//			dialect = new DmDialect();
//		} else if ("kb".equals(dbType)) {
//			dialect = new KbDialect();
//		}

		if (dialect == null) {
			throw new RuntimeException("mybatis dialect error.");
		} else {
			return (Dialect)dialect;
		}
	}

	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
		Object[] args = invocation.getArgs();
		Object parameter = args[1];
		ResultHandler resultHandler = (ResultHandler)args[3];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Object parameterObject = boundSql.getParameterObject();
		Executor executor = (Executor)invocation.getTarget();
		Page<Object> page = null;
		if (parameterObject != null) {
			page = convertParameter(parameterObject, page);
		}

		if (page != null && page.getPageSize() != -1) {
			if (StringUtils.isBlank(boundSql.getSql())) {
				return null;
			}

			String originalSql = boundSql.getSql().trim();
			Dialect dialect = this.getDialect();
			String countSql = "select count(1) from (" + SQLHelper.removeOrders(originalSql) + ") tmp_count";
			if (this.log.isDebugEnabled()) {
				this.log.debug("COUNT SQL: " + StringUtils.replaceEach(countSql, new String[]{"\n", "\t"}, new String[]{" ", " "}));
			}

			BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			long count = this.count(executor, mappedStatement, parameter, resultHandler, countBoundSql);
			this.log.debug("查询count :" + count);
			page.setCount(count);
			String pageSql = SQLHelper.generatePageSql(originalSql, page, dialect);
			invocation.getArgs()[2] = new RowBounds(0, 2147483647);
			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			if (ReflectUtil.getFieldValue(boundSql, "metaParameters") != null) {
				MetaObject mo = (MetaObject) ReflectUtil.getFieldValue(boundSql, "metaParameters");
				ReflectUtil.setFieldValue(newBoundSql, "metaParameters", mo);
			}

			MappedStatement newMs = this.copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
			invocation.getArgs()[0] = newMs;
		}

		return invocation.proceed();
	}

	private long count(Executor executor, MappedStatement ms, Object param, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
		String countMsId = this.getCountMsId(ms);
		MappedStatement countMs = ExecutorUtil.getExistedMappedStatement(ms.getConfiguration(), countMsId);
		if (countMs == null) {
			countMs = ExecutorUtil.createCountMappedStatement(ms, countMsId, param, boundSql);
		}

		long count = ExecutorUtil.executeCountSql(executor, countMs, param, boundSql, resultHandler);
		return count;
	}

	private String getCountMsId(MappedStatement ms) {
		String methodFullName = ms.getId();
		int lastDotPos = methodFullName.lastIndexOf(46);
		String methodName = methodFullName.substring(lastDotPos + 1);
		String countMethodName = String.format("count%c%s", Character.toUpperCase(methodName.charAt(0)), methodName.substring(1));
		return methodFullName.substring(0, lastDotPos + 1).concat(countMethodName);
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		super.initProperties(properties);
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			String[] keyProperties = ms.getKeyProperties();
			int len = keyProperties.length;

			for(int i = 0; i < len; ++i) {
				String keyProperty = keyProperties[i];
				builder.keyProperty(keyProperty);
			}
		}

		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return this.boundSql;
		}
	}
}

