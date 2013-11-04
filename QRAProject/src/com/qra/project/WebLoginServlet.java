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
import javax.servlet.http.HttpSession;

public class WebLoginServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		resp.setContentType("text/HTML");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		if(username == null || password == null){
			resp.getWriter().print("The username or password must contain an id parameter");
			resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
		}
		else if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
			resp.getWriter().print("Please do not leave the username or password blank");
			resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
		}
		else{
			username = username.trim();
			password = password.trim();
			
			try{
				
				Query q = pm.newQuery(User.class, "username == '"  + username + "' && "
						+ "password == '" + password + "'");
				
				
				List<User> results = (List<User>) q.execute();
				
				
				if(results.size() == 0){
					resp.getWriter().print("The entered username or password is incorrect");
					resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
				}
				else{
					
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("application/json");
					
					//Response in JSON Format
					//String contentString = "{\"valid\": \"true\"}";
					//resp.getWriter().print(contentString);
					
					//Cookie creation
					Cookie userIDCookie = new Cookie("userIDCookie", username);
					userIDCookie.setMaxAge(60*60*24); //24 hours
					userIDCookie.setPath("/"); //allow access by the entire application?
					resp.addCookie(userIDCookie);
					
					//Session creation
					HttpSession session = req.getSession();
					session.setAttribute("userSess", username);
					
				
					//resp.sendRedirect("createconference.jsp"); //temporary, change to a page that allows user to pick
					//they would like to do
					
					resp.sendRedirect("sessiontest.jsp");
				}
			}
			catch(JDOObjectNotFoundException e){
				resp.getWriter().print("We are unable to find the user with the given username and password");
				resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
			}
		}
		resp.getWriter().flush();
	}
}
