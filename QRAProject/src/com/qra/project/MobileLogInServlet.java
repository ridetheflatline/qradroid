package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobileLogInServlet extends HttpServlet{
	
    private static final Logger log = Logger.getLogger(MobileLogInServlet.class.getName());
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws IOException 
		{
		 
		 	//Extrat the parameters and find the conference creator
		    String username = req.getParameter("username");
		    String password = req.getParameter("password");
		    
		    if(username == null || password == null){
		    	log.info("Error, username or password in request was null");
		    }
		    else{
		    	log.info("Searching for  username: " + username);
		    	//Look in the datastore to see if the username and password match
		    	
		    	//response should be a JSON string
		        //{"valid" : "true"} or {"valid" : "false"}
		    	String response = "";
		    	
		    	res.setContentType("text/plain");
				res.getWriter().print(response);
		    }
		 
		}

}
