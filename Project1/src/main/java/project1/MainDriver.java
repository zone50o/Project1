package project1;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import project1.frontcontroller.FrontController;

public class MainDriver {
	
	final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	
	public static void main(String[] args) {
		loggy.setLevel(Level.ALL);

		if (loggy.isInfoEnabled()) {
			loggy.info("Log Successfully Deployed");
		}
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles(staticFiles -> {
				staticFiles.directory = "/resources";
				staticFiles.hostedPath = "/";
				staticFiles.location = Location.CLASSPATH;
			});
		}).start(9010);
		loggy.info("Javalin Created");
		FrontController frontCont = new FrontController(app);
	}
}
