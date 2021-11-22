package com.rawchen.common.utils;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author RawChen
 * @since 2021-11-02 9:36
 */
public class ExecutorUtil {
	private static Field additionalParametersField;

	public ExecutorUtil() {
	}

	public static Map<String, Object> getAdditionalParameter(BoundSql boundSql) {
		try {
			return (Map)additionalParametersField.get(boundSql);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("获取 BoundSql 属性 additionalParameters 失败: " + e, e);
		}
	}

	public static MappedStatement getExistedMappedStatement(Configuration configuration, String id) {
		MappedStatement mappedStatement = null;

		try {
			mappedStatement = configuration.getMappedStatement(id);
		} catch (Throwable throwable) {
		}

		return mappedStatement;
	}

	public static long executeCountSql(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql, ResultHandler resultHandler) throws SQLException {
		CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
		BoundSql countBoundSql = countMs.getBoundSql(parameter);
		Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
		return ((Number)((List)countResultList).get(0)).longValue();
	}

	public static MappedStatement createCountMappedStatement(MappedStatement ms, String newCountMsId, Object param, BoundSql boundSql) {
		String countSql = boundSql.getSql();
		SqlSource countSqlSource = new StaticSqlSource(ms.getConfiguration(), countSql, boundSql.getParameterMappings());
		SqlCommandType countSqlCommandType = SqlCommandType.SELECT;
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), newCountMsId, countSqlSource, countSqlCommandType);
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuilder keyProperties = new StringBuilder();
			String[] properties = ms.getKeyProperties();
			int len = properties.length;

			for(int i = 0; i < len; ++i) {
				String keyProperty = properties[i];
				keyProperties.append(keyProperty).append(",");
			}

			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}

		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		List<ResultMap> resultMaps = new ArrayList<>();
		ResultMap resultMap = (new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class, new ArrayList())).build();
		resultMaps.add(resultMap);
		builder.resultMaps(resultMaps);
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	static {
		try {
			additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
			additionalParametersField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException("获取 BoundSql 属性 additionalParameters 失败: " + e, e);
		}
	}
}
