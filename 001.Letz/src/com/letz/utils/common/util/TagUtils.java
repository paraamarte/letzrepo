package com.sivillage.common.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

/**
 * Utility methods for use in the implementation of JSP tags. These methods are separated from the tags themselves for two reasons. First,
 * it enables code reuse across tag implementations. Second, and perhaps more important, it allows for testing these methods independently
 * from a JSP container.
 */
public final class TagUtils {

	/**
	 * Private constructor to prevent instantiation of this class.
	 */
	private TagUtils() {
	}

	/**
	 * Convert the string representation of a scope into the corresponding constant from the <code>PageContext</code> class. If the string
	 * value is null, the supplied default value is returned instead.
	 *
	 * @param scope
	 *            The string value to convert.
	 * @param defaultScope
	 *            The scope to return if the string value supplied is <code>null</code>.
	 *
	 * @return The constant corresponding to the string value supplied.
	 *
	 * @throws JspException
	 *             if the string value is not a valid scope name, or if the string value is <code>null</code> and the default value supplied
	 *             is not a valid scope.
	 */
	public static int getScope(String scope, int defaultScope) throws JspException {
		int pcscope;

		if (scope == null) {
			switch (defaultScope) {
				case PageContext.PAGE_SCOPE:
				case PageContext.REQUEST_SCOPE:
				case PageContext.SESSION_SCOPE:
				case PageContext.APPLICATION_SCOPE:
					pcscope = defaultScope;
					break;
				default:
					throw new JspTagException("Invalid default scope: " + defaultScope);
			}
		} else if ("page".equals(scope)) {
			pcscope = PageContext.PAGE_SCOPE;
		} else if ("request".equals(scope)) {
			pcscope = PageContext.REQUEST_SCOPE;
		} else if ("session".equals(scope)) {
			pcscope = PageContext.SESSION_SCOPE;
		} else if ("application".equals(scope)) {
			pcscope = PageContext.APPLICATION_SCOPE;
		} else {
			throw new JspTagException("Invalid scope name: " + scope);
		}

		return pcscope;
	}
}