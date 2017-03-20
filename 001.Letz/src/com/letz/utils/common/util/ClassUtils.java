package com.letz.utils.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with Class instances.
 */
public final class ClassUtils {

	/**
	 * Private constructor to prevent instantiation of this class.
	 */
	private ClassUtils() {
	}

	/**
	 * Return the <code>Class</code> object for the specified fully qualified class name, from the appropriate class loader.
	 *
	 * @param className
	 *            Fully qualified name of the class to be loaded.
	 *
	 * @return The Class object for the requested class.
	 *
	 * @throws ClassNotFoundException
	 *             If the class cannot be found.
	 */
	public static Class loadClass(String className) throws ClassNotFoundException {
		// Look up the class loader to be used
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassUtils.class.getClassLoader();
		}

		// Attempt to load the specified class
		return (classLoader.loadClass(className));
	}

	/**
	 * Return a new instance of the specified fully qualified class name, after loading the class from the appropriate class loader. The
	 * specified class <strong>MUST</strong> have a public zero-argument constructor.
	 *
	 * @param className
	 *            Fully qualified name of the class to use.
	 *
	 * @return A new instance of the requested class.
	 *
	 * @throws ClassNotFoundException
	 *             if the class cannot be found
	 * @throws IllegalAccessException
	 *             if the class or its constructor is not accessible
	 * @throws InstantiationException
	 *             if this class represents an abstract class, an interface, an array class, a primitive type, or void
	 * @throws InstantiationException
	 *             if this class has no zero-arguments constructor
	 */
	public static Object createInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return (loadClass(className).newInstance());
	}

	/**
	 * Creates and returns a map of the names of public static final constants to their values, for the specified class.
	 *
	 * @param className
	 *            The fully qualified name of the class for which the constants should be determined
	 *
	 * @return A <code>Map</code> from constant names to values.
	 * @throws ClassNotFoundException
	 *             if TODOC error occurs.
	 * @throws IllegalAccessException
	 *             if TODOC error occurs.
	 * @throws IllegalArgumentException
	 *             if TODOC error occurs.
	 */
	public static Map /* String, Object */getClassConstants(String className) throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException {
		Map /* String, Object */constants = new HashMap();
		Class clazz = ClassUtils.loadClass(className);

		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int modifiers = field.getModifiers();
			if ((modifiers & Modifier.STATIC) != 0 && (modifiers & Modifier.FINAL) != 0) {
				Object value = field.get(null);
				if (value != null) {
					constants.put(field.getName(), value);
				}
			}
		}
		return constants;
	}
}