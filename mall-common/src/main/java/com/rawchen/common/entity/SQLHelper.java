package com.rawchen.common.entity;

import com.rawchen.common.utils.ReflectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author RawChen
 * @since 2021-11-02 9:34
 */
public class SQLHelper {
	public SQLHelper() {
	}

	public static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);

			for (int i = 0; i < parameterMappings.size(); ++i) {
				ParameterMapping parameterMapping = (ParameterMapping) parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					Object value;
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith("__frch_") && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	public static int getCount(final String sql, final Connection connection, final MappedStatement mappedStatement, final Object parameterObject, final BoundSql boundSql, Log log) throws SQLException {
		log.error("执行 PreparePaginationInterceptor getCount 方法存在存在并发问题, 并发情况会出现服务假死。 Sql" + sql);
		String countSql = "select count(1) from (" + removeOrders(sql) + ") tmp_count";
		Connection conn = connection;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int result;
		try {
			if (log.isDebugEnabled()) {
				log.debug("COUNT SQL: " + StringUtils.replaceEach(countSql, new String[]{"\n", "\t"}, new String[]{" ", " "}));
			}

			if (conn == null) {
				conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			}

			ps = conn.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
			if (ReflectUtil.getFieldValue(boundSql, "metaParameters") != null) {
				MetaObject mo = (MetaObject) ReflectUtil.getFieldValue(boundSql, "metaParameters");
				ReflectUtil.setFieldValue(countBS, "metaParameters", mo);
			}

			setParameters(ps, mappedStatement, countBS, parameterObject);
			rs = ps.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			result = count;
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (ps != null) {
				ps.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}

	public static String generatePageSql(String sql, Page<Object> page, Dialect dialect) {
		return dialect.supportsLimit() ? dialect.getLimitString(sql, page.getFirstResult(), page.getMaxResults()) : sql;
	}

	private static String removeSelect(String qlString) {
		int beginPos = qlString.toLowerCase().indexOf("from");
		return qlString.substring(beginPos);
	}

	public static String removeOrders(String qlString) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
		Matcher m = p.matcher(qlString);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}