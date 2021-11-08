package project1.frontcontroller;

import io.javalin.Javalin;
import project1.controller.UserController;
import project1.controller.ReimbursementController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {
	public Dispatcher(Javalin app) {
		setupAllPaths(app);
	}

	public static void setupAllPaths(Javalin app) {
		setupUserControllerPaths(app);
		setupReimbersementControllerPaths(app);
	}

	public static void setupUserControllerPaths(Javalin app) {
		app.routes(() -> {
			path("/api/login", () -> {
				post(UserController::login);
			});
			path("/api/logout", () -> {
				get(UserController::logout);
			});

			path("/api/user", () -> {
				get(UserController::getUser);

			});

			path("/api/employee", () -> {

			});

			path("/api/finance_manager", () -> {

			});
		});
	}

	public static void setupReimbersementControllerPaths(Javalin app) {
		app.routes(() -> {
			path("/api/reimbersement", () -> {
				get(ReimbursementController::getAllReimbursements);
				path("/user", () -> {
					path("{input}", () -> {
						get(ReimbursementController::getReimbursementsByUser);
					});
				});
				path("/filter/{input}", () -> {

				});
				path("/add", () ->{
					post(ReimbursementController::addReimbursement);
				});
				
				path("/update", () ->{
					post(ReimbursementController::updateReimbursement);
				});
			});
		});
	}

}
