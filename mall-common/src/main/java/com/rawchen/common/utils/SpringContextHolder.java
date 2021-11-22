package com.rawchen.common.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author RawChen
 * @since 2021-11-02 9:34
 */
@Service("springContextHolder")
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
	private static ApplicationContext applicationContext = null;
	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	public SpringContextHolder() {
	}

	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	public static <T> Object getBean(String name) {
		assertContextInjected();
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	public static void clearHolder() {
		if (logger.isDebugEnabled()) {
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
		if (SpringContextHolder.applicationContext != null) {
			logger.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
		}
		SpringContextHolder.applicationContext = applicationContext;
	}

	public void destroy() throws Exception {
		clearHolder();
	}

	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.", new Object[0]);
	}
}
