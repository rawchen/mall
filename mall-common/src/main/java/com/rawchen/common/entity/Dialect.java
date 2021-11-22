package com.rawchen.common.entity;

/**
 * @author RawChen
 * @since 2021-11-02 9:32
 */
public interface Dialect {
	boolean supportsLimit();

	String getLimitString(String sql, int offset, int limit);
}