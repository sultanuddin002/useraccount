package com.sultan.quiz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//Path: http://localhost/<appln-folder-name>/login
@Path("/login")
public class Login {
	// HTTP Get Method
	@GET
	// Path: http://localhost/<appln-folder-name>/login/dologin
	@Path("/dologin")
	// Produce JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameteres are parameteres:
	// http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
	public String doLogin(@QueryParam("username") String username, @QueryParam("password") String password) {
		String response = "";
		if (checkCredentials(username, password)) {
			response = Utility.constructJSON("login", true);
		} else {
			response = Utility.constructJSON("login", false, "Incorrect Email or Password");
		}
		return response;
	}

	/*
	 * Method to check whether the entered credentials are correct
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @return
	 */

	private boolean checkCredentials(String username, String password) {
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if (Utility.isNotNull(username) && Utility.isNotNull(password)) {
			try {
				result = DBConnection.checkLogin(username, password);
			} catch (Exception e) {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}
}
