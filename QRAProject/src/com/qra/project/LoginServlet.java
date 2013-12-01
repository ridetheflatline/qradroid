package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class LoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		if(username == null || password == null){
			resp.getWriter().print("The username or password must contain an id parameter");
		}
		else if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
			resp.getWriter().print("Please do not leave the username or password blank");
		}
		else{
			username = username.trim();
			password = password.trim();
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			
			try{
				
				Query q = pm.newQuery(User.class, "email == '"  + username + "'");
			
				List<User> results = (List<User>) q.execute();
				
				if(results.size() == 0 || (results.size()>0? !passwordEncryptor.checkPassword(password, results.get(0).getPassword()):true)){
					resp.getWriter().print("The entered username or password is incorrect");
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
