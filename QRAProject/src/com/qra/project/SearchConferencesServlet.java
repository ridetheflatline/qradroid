package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchConferencesServlet extends HttpServlet {


	private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException 
	{
		//attend a conference
		String searchString = req.getParameter("search_string");
		String searchRadius = req.getParameter("radius");
		String zipOrCity = req.getParameter("zipOrCity");
		String state = req.getParameter("state");
		
		log.info("search String: " + searchString);
		log.info("searchRadius: " + searchRadius);
		log.info("zipOrCity: " + zipOrCity);
		log.info("state: " + state);
		//res.sendRedirect("searchconferences.jsp");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException 
	{ 
				
	}
}
