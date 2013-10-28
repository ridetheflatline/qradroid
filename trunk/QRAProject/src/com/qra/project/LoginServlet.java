package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		//Sample Request
		//hostlogin?username=fjohnson&password=johnson
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		if(username == null || password == null){
			resp.getWriter().print("username or password must contain an id parameter");
		}
		else if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
			resp.getWriter().print("username or password parameter must not be empty");
		}
		else{
			username = username.trim();
			password = password.trim();
			
			try{
				
				Query q = pm.newQuery(User.class, "username == '"  + username + "' && "
						+ "password == '" + password + "'");
			
				List<User> results = (List<User>) q.execute();
				
				if(results.size() == 0){
					resp.getWriter().print("unable to find the user with the given username and password");
				}
				else{
					
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("application/json");
					
					//Response in JSON Format
					String contentString = "{\"valid\": \"true\"}";
					resp.getWriter().print(contentString);
				}
			}
			catch(JDOObjectNotFoundException e){
				resp.getWriter().print("unable to find the user with the given username and password");
			}
		}
		resp.getWriter().flush();
	}
}
