package project1.frontcontroller;


import io.javalin.Javalin;
import io.javalin.http.Context;
import project1.model.User;

public class FrontController {
	Javalin app;
	Dispatcher dispatcher;
	
	public FrontController(Javalin app) {
		this.app = app;
		
		app.before("/api/*", FrontController::checkAllRequests);
		
		this.dispatcher = new Dispatcher(app);
	}
	
	public static void checkAllRequests(Context context) {
//		System.out.println("In front controller");
		
		if(context.path().equals("/api/login")) {
			return;
		}
		User temp = context.sessionAttribute("currentUser");
		if(temp==null) {
			context.redirect("/index.html");
		}
//		else if(!(temp.getRole().equals("Admin"))) {
//			if(temp.getRole().equals("Employee")) {
//				context.redirect("/html/employee.html");
//			}else if(temp.getRole().equals("Finance Manager")) {
//				context.redirect("/html/manager.html");
//			}
//		}
	}
}
