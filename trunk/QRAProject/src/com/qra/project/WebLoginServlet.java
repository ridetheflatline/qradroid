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

import org.jasypt.util.password.BasicPasswordEncryptor;

public class WebLoginServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(WebLoginServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		resp.setContentType("text/HTML");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		if(username == null || password == null){
			resp.setHeader("Refresh", "5; URL=login.jsp");
			resp.getWriter().print("The username or password must contain an id parameter");
			resp.getWriter().print("<br>You will return to Log In in 5 seconds.<br>");
			resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
			resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
		}
		else if(username.equalsIgnoreCase("")){
			resp.setHeader("Refresh", "5; URL=login.jsp");
			resp.getWriter().print("Please do not leave the username blank");
			resp.getWriter().print("<br>You will return to Log In in 5 seconds.<br>");
			resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
			resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
		}
		else if(password.equalsIgnoreCase("")){
			resp.setHeader("Refresh", "5; URL=login.jsp");
			resp.getWriter().print("Please do not leave the password blank");
			resp.getWriter().print("<br>You will return to Log In in 5 seconds.<br>");
			resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
			resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
		}
		else{
			username = username.trim();
			password = password.trim();
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			
			try{
				Query q = pm.newQuery(User.class, "username == '"  + username + "'");
				
				List<User> results = (List<User>) q.execute(); //query for user with the same username and password
				
				if(results.size() == 0 || (results.size()>0? !passwordEncryptor.checkPassword(password, results.get(0).getPassword()):true)){//if there are no matches
					resp.setHeader("Refresh", "5; URL=login.jsp");
					resp.getWriter().print("The entered username or password is incorrect");
					resp.getWriter().print("<br>You will return to Log In in 5 seconds.<br>");
					resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
					resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
				}
				else{
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("application/json");
					
					//Cookie creation
					Cookie userIDCookie = new Cookie("userIDCookie", username);
					userIDCookie.setMaxAge(60*60*24); //24 hours
					userIDCookie.setPath("/"); //allow access by the entire application?
					resp.addCookie(userIDCookie);
					
					//Session creation
					HttpSession session = req.getSession();
					session.setAttribute("userSess", username);
					
					
					resp.sendRedirect("index.jsp");
				}
			}
			catch(JDOObjectNotFoundException e){
				resp.setHeader("Refresh", "5; URL=login.jsp");
				resp.getWriter().print("We are unable to find the user with the given username and password");
				resp.getWriter().print("<br>You will return to Log In in 5 seconds.<br>");
				resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
				resp.getWriter().print("<br> <a href=\"login.jsp\">Return to Log In</a>");
				
				
			}
		}
		resp.getWriter().flush();
	}
}
