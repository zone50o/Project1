package project1.controller;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import project1.MainDriver;
import project1.model.User;
import project1.service.UserService;

public class UserController {

	final static Logger loggy = Logger.getLogger(MainDriver.class);

	public static void login(Context context) {

		String username = context.formParam("myUsername");
		String password = context.formParam("myPassword");

//		System.out.println("username: " + username + " " + "\t\tpassword: " + password);
		User temp = UserService.getUser(username, password);
		if (temp != null) {
			context.sessionAttribute("currentUser", temp);
			if (temp.getRole().equals("Employee")) {
				loggy.info("Successfully logged in as employee: " + temp.getUsername());
				context.redirect("/html/employee.html");
			} else {
				loggy.info("Successfully logged in as Finance Manager: " + temp.getUsername());
				context.redirect("/html/manager.html");
			}
		} else {
			loggy.warn("Invalid Login");
			context.redirect("http://localhost:9010/");
		}

	}

	public static void logout(Context context) {
//		System.out.println("Entered Logout");
		User temp = context.sessionAttribute("currentUser");
		loggy.info("Logged Out user: " + temp.getUsername());
		context.sessionAttribute("currentUser", null);
		context.redirect("http://localhost:9010/");
	}

	public static void getUser(Context context) {
		User temp = context.sessionAttribute("currentUser");
		loggy.info("Retriving info on user: " + temp.getUsername());
		context.json(temp);
	}
}
