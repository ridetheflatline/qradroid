package com.qra.project;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		Cookie userIDCookie = new Cookie("userIDCookie", null);
		userIDCookie.setMaxAge(0); //0 hours
		userIDCookie.setPath("/"); //allow access by the entire application?
		resp.addCookie(userIDCookie);
		
		resp.sendRedirect("index.jsp");
	}
}
