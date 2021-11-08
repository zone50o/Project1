package project1.controller;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import project1.MainDriver;
import project1.model.Reimbursement;
import project1.model.User;
import project1.service.ReimbursementService;

public class ReimbursementController {
	
	final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void getReimbursementsByUser(Context context) {
		String fullName = context.pathParam("input");
		loggy.info("Getting Reimbursements for: "+fullName);
		context.json(ReimbursementService.getReimbursementsByUser(fullName));
		
	}
	
	public static void getReimbursementsByFilter(Context context) {
		String status = context.pathParam("input");
		loggy.info("Getting Reimbursements of status: "+status);
		context.json(ReimbursementService.getReimbursementsByStatus(status));
	}
	
	public static void getAllReimbursements(Context context) {
		loggy.info("Getting All Reimbursements");
		context.json(ReimbursementService.getAllReimbursements());
	}
	
	public static void addReimbursement(Context context){
		User temp = context.sessionAttribute("currentUser");
		
		double amount = 0.00;
		String description = "";
		if(checkDouble(context.formParam("newAmount"))
			&&checkDescription(context.formParam("newDescription"))){
			amount = Double.parseDouble(context.formParam("newAmount"));
			description = context.formParam("newDescription");
		}else {
			loggy.warn("User: "+ temp.getUsername() + " has given invalid input");
			context.redirect("/html/employee.html");
		}
		
		
		String type = context.formParam("newType");
		
		
		Reimbursement newReimbursement = new Reimbursement(amount, "", "",
				description, "",	temp.getFullName(),	"",	"",	type);
				
		
		ReimbursementService.addReimbursement(newReimbursement);
		loggy.info("Succesfully created new Reimbursement");
		context.redirect("/html/employee.html");
	}
	
	public static void updateReimbursement(Context context) {
//		System.out.println(context.formParam("disabledInput"));
		int id = Integer.parseInt(context.formParam("disabledInput"));
		String status = context.formParam("newStatus");
		User temp = context.sessionAttribute("currentUser");
		ReimbursementService.updateReimbursementResolve(temp.getFullName(), status, id);
		loggy.info("Successfully updated a Reimbursement");
		context.redirect("/html/manager.html");
	}
	
	public static boolean checkDouble(String input) {
		// Returns an integer from user input
		if(input==null || input.isEmpty()) {
			return false;
		}
		
		
		return Double.parseDouble(input)>0.00;
	}
	
	public static boolean checkDescription(String input) {
		if(input==null) {
			return false;
		}
		return input.length()<=250;
	}
	
}
