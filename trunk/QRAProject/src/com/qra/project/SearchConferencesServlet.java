package com.qra.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchConferencesServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		String searchString = req.getParameter("search");
		String radius = req.getParameter("radius");
		String cityOrZip = req.getParameter("cityOrZip");
		String state = req.getParameter("state");
		
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}

}
