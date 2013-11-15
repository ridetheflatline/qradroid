package com.qra.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

public class SearchConferencesServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		String searchString = req.getParameter("search");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		
		//Currently just search for the conf_name and conf_description
		//TODO: add geographical searching
		
		//generatE pages for joining conferences
		List<Conference> conferences = testReturnConferences();
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}
	
	//Temporary placeholder to return some conferences
	public static List<Conference> testReturnConferences(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Conference.class);
		
		List<Conference> conferences = (List <Conference>)q.execute();
		log.info("number of conferences: " +conferences.size());
		
		q.closeAll();
		return conferences;
	}

}
