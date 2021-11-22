package com.rawchen.common.base;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author RawChen
 * @since 2021-10-30 23:38
 */
public interface BaseMapper<T> {
	T get(String id);

	T get(int id);

	T get(T entity);

	List<T> findByIdList(List<String> ids);

	T findUniqueByProperty(@Param("propertyName") String propertyName, @Param("value") Object value);

	List<T> findList(T entity);

	List<T> findAllList(T entity);

	/** @deprecated */
	@Deprecated
	List<T> findAllList();

	int insert(T entity);

	int update(T entity);

	/** @deprecated */
	@Deprecated
	int delete(String id);

	/** @deprecated */
	@Deprecated
	int deleteByLogic(String id);

	int delete(T entity);

	int deleteByLogic(T entity);
}