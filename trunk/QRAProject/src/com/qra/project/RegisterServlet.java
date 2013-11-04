package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{	
		//Sample Request:
		//http://localhost:8888/userInsertTest?first_name=Danny&middle_name=Joe&last_name=Johnson&
		//email=fred.johnson@gmail.com&username=fjohnson&password=johnson
		//&profile_img=www.url.com&birthdate=01171990
		
		String first_name = req.getParameter("first_name");
		String middle_name = req.getParameter("middle_name");
		String last_name = req.getParameter("last_name");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String profile_img = req.getParameter("profile_img");
		String birthdate = req.getParameter("birthdate");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(first_name == null || middle_name == null || last_name == null || email == null || 
				username == null || password == null || profile_img == null || birthdate == null){
			
			log.info("first_name: "+first_name);
			log.info("middle_name: "+middle_name);
			log.info("last_name: "+last_name);
			log.info("email: "+email);
			log.info("username: "+username);
			log.info("password: "+password);
			log.info("profile_img: "+profile_img);
			log.info("birthdate: "+birthdate);
			
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "A Parameter is null.");
		}
		else if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("") || first_name.equalsIgnoreCase("")
				|| middle_name.equalsIgnoreCase("") || last_name.equalsIgnoreCase("") || email.equalsIgnoreCase("") ||
				profile_img.equalsIgnoreCase("") || birthdate.equalsIgnoreCase("")){
			resp.getWriter().print("You must fill in all the information.");
		}
		else{
			User u = new User(first_name, middle_name, last_name, email, username, password, profile_img, birthdate);
			Object o = null;
			try{
				 o = pm.makePersistent(u);
			}
			finally{
				pm.close();
				
				if(o != null){
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("text/plain");
					resp.getWriter().println("Success");
				}
			}
		}
		
	}

}
