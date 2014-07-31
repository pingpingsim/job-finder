package android.job.finder.util;

import android.text.Html;

/**
 * Commonly used string utility methods
 * 
 * @author user
 * 
 */
public class StringUtils {
	/**
	 * Strip off HTML tags from the target HTML string
	 * 
	 * @param html
	 * @return boolean
	 */
	public static String stripHtml(String html) {
		return Html.fromHtml(html).toString();
	}

	/**
	 * Validate whether target string is null or empty
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String str) {
		return ((str == null || str.isEmpty()) ? true : false);
	}
}
