package com.rawchen.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.*;

/**
 * @author RawChen
 * @since 2021-11-02 9:35
 */
public class ReflectUtil {
	private static final String SETTER_PREFIX = "set";
	private static final String GETTER_PREFIX = "get";
	private static final String CGLIB_CLASS_SEPARATOR = "$$";
	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	public ReflectUtil() {
	}

	public static Object invokeGetter(Object obj, String propertyName) {
		Object object = obj;
		String[] strings = StringUtils.split(propertyName, ".");
		int len = strings.length;

		for(int i = 0; i < len; ++i) {
			String name = strings[i];
			String getterMethodName = "get" + StringUtils.capitalize(name);
			object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
		}

		return object;
	}

	public static void invokeSetter(Object obj, String propertyName, Object value) {
		Object object = obj;
		String[] names = StringUtils.split(propertyName, ".");

		for(int i = 0; i < names.length; ++i) {
			String getterMethodName;
			if (i < names.length - 1) {
				getterMethodName = "get" + StringUtils.capitalize(names[i]);
				object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
			} else {
				getterMethodName = "set" + StringUtils.capitalize(names[i]);
				invokeMethodByName(object, getterMethodName, new Object[]{value});
			}
		}

	}

	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		} else {
			Object result = null;

			try {
				result = field.get(obj);
			} catch (IllegalAccessException e) {
				logger.error("不可能抛出的异常{}", e.getMessage());
			}

			return result;
		}
	}

	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		} else {
			try {
				field.set(obj, value);
			} catch (IllegalAccessException e) {
				logger.error("不可能抛出的异常:{}", e.getMessage());
			}

		}
	}

	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes, final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		} else {
			try {
				return method.invoke(obj, args);
			} catch (Exception e) {
				throw convertReflectionExceptionToUnchecked(e);
			}
		}
	}

	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		} else {
			try {
				return method.invoke(obj, args);
			} catch (Exception e) {
				throw convertReflectionExceptionToUnchecked(e);
			}
		}
	}

	public static Field getAccessibleField(final Object obj, final String fieldName) {
		Validate.notNull(obj, "object can't be null", new Object[0]);
		Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
		Class superClass = obj.getClass();

		while(superClass != Object.class) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				makeAccessible(field);
				return field;
			} catch (NoSuchFieldException e) {
				superClass = superClass.getSuperclass();
			}
		}

		return null;
	}

	public static Method getAccessibleMethod(final Object obj, final String methodName, final Class<?>... parameterTypes) {
		Validate.notNull(obj, "object can't be null", new Object[0]);
		Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);
		Class searchType = obj.getClass();

		while(searchType != Object.class) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				searchType = searchType.getSuperclass();
			}
		}

		return null;
	}

	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		Validate.notNull(obj, "object can't be null", new Object[0]);
		Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);

		for(Class searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			Method[] tMethod = methods;
			int len = methods.length;

			for(int i = 0; i < len; ++i) {
				Method method = tMethod[i];
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}

		return null;
	}

	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
			method.setAccessible(true);
		}

	}

	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}

	}

	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		} else {
			Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
			if (index < params.length && index >= 0) {
				if (!(params[index] instanceof Class)) {
					logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
					return Object.class;
				} else {
					return (Class)params[index];
				}
			} else {
				logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
				return Object.class;
			}
		}
	}

	public static Class<?> getUserClass(Object instance) {
		Assert.notNull(instance, "Instance must not be null");
		Class clazz = instance.getClass();
		if (clazz != null && clazz.getName().contains("$$")) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass != null && !Object.class.equals(superClass)) {
				return superClass;
			}
		}

		return clazz;
	}

	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (!(e instanceof IllegalAccessException) && !(e instanceof IllegalArgumentException) && !(e instanceof NoSuchMethodException)) {
			if (e instanceof InvocationTargetException) {
				return new RuntimeException(((InvocationTargetException)e).getTargetException());
			} else {
				return e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException("Unexpected Checked Exception.", e);
			}
		} else {
			return new IllegalArgumentException(e);
		}
	}
}

