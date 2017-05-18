package com.sultan.quiz;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//Path: http://localhost/<appln-folder-name>/register
@Path("/register")
public class Register {
	// HTTP Method

	@GET
	// Path: http://localhost/<appln-folder-name>/register/doregister
	@Path("doregister")
	// Produce JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://localhost/<appln-folder-name>/register/doregister?name=psrq&username=abc&password=xyz
	public String doRegister(@QueryParam("name") String name, @QueryParam("username") String username,
			@QueryParam("password") String password) {
		String response = "";
		int retCode = registerUser(name, username, password);
		if (retCode == 0) {
			response = Utility.constructJSON("register", true);
		} else if (retCode == 1) {
			response = Utility.constructJSON("register", false, "You are already registered");
		} else if (retCode == 2) {
			response = Utility.constructJSON("register", false,
					"Special Characters are not allowed in username and password");
		} else if (retCode == 3) {
			response = Utility.constructJSON("register", false, "Error occured");
		}
		return response;
	}

	private int registerUser(String name, String username, String password) {
		System.out.println("Inside checkCredentials");
		int result = 3;
		if (Utility.isNotNull(username) && Utility.isNotNull(password)) {
			try {
				if (DBConnection.insertUser(name, username, password)) {
					System.out.println("RegisterUser if");
					result = 0;
				}
			} catch (SQLException sqle) {
				System.out.println("Register User catch sqle");
				// when primary key violation occurs that means the user is
				// already registered
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				}
				// When special characters are used in name, username or
				// password
				else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				System.out.println("Inside Check Credentials catch e");
				result = 3;
			}
		} else {
			System.out.println("Inside Check Credentials else");
			result = 3;
		}
		return result;
	}

}
