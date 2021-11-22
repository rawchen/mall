package com.rawchen.common.base;

import com.rawchen.common.entity.Dialect;
import com.rawchen.common.entity.MySQLDialect;
import com.rawchen.common.entity.Page;
import com.rawchen.common.utils.ReflectUtil;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author RawChen
 * @since 2021-11-02 9:31
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {
	private static final long serialVersionUID = 1L;
	protected static final String PAGE = "page";
	protected static final String DELEGATE = "delegate";
	protected static final String MAPPED_STATEMENT = "mappedStatement";
	protected Log log = LogFactory.getLog(this.getClass());
	protected Dialect DIALECT;

	public BaseInterceptor() {
	}

	protected static Page<Object> convertParameter(Object parameterObject, Page<Object> page) {
		try {
			return parameterObject instanceof Page ? (Page)parameterObject : (Page) ReflectUtil.getFieldValue(parameterObject, "page");
		} catch (Exception e) {
			return null;
		}
	}

	protected void initProperties(Properties p) {
		Dialect dialect = new MySQLDialect();
		this.DIALECT = dialect;
	}
}
