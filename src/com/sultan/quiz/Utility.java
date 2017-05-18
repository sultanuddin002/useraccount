package com.sultan.quiz;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utility {

	/*
	 * Null check method
	 * 
	 * @param txt
	 * 
	 * @return
	 */

	public static boolean isNotNull(String txt) {
		return txt != null && txt.trim().length() > 0 ? true : false;
	}

	/*
	 * Method to construct JSON
	 * 
	 * @param tag
	 * 
	 * @param status
	 * 
	 * @return
	 */

	public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj.toString();
	}

	/*
	 * Method to construct JSON with error message
	 * 
	 * @param tag
	 * 
	 * @param status
	 * 
	 * @param err_msg
	 * 
	 * @return
	 */

	public static String constructJSON(String tag, boolean status, String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", status);
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO: handle exception
		}
		return obj.toString();
	}

}
