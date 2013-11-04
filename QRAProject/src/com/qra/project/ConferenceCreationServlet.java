package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConferenceCreationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		
		
		res.sendRedirect("createconference.jsp");
	
	}

}
