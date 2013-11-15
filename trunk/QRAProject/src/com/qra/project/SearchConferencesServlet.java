package com.qra.project;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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
		
		//Currently just search for the conf_name and conf_description
		//TODO: add geographical searching
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Conference.class);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}
	
	//Temporary placeholder to return some conferences
	public static void testReturnConferences(){
		
	}

}
