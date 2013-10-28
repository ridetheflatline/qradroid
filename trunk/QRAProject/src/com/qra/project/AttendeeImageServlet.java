package com.qra.project;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AttendeeImageServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String id = req.getParameter("id");
		
		User user = pm.getObjectById(User.class, id);
		
		String url = "";
		
		resp.setContentType("text/plain");
		resp.getWriter().print(url);
	}
}
