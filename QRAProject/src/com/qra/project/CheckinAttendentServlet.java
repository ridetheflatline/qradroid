package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckinAttendentServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
    	String output = "Error. This is url is only meant for POST requests.";
    	output += "\nBody of POST must include conf_code and user_id.";
    	res.setContentType("text/plain");
		res.getWriter().print(output);
    }

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{ 
		log.info("In CheckinAttendentServlet");
		
		String conf_code = req.getParameter("conf_code");
		String user_id = req.getParameter("user_id");
		
		if(conf_code == null || user_id == null){
			log.info("conf_code or user_id is null. Error.");
		}
		else{
			log.info("conf_code: " + conf_code);
			log.info("user_id: " + user_id);
			
			//Insert into Attendance_Records with the Given session_code and user_id
		}
	}

}
