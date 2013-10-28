package com.qra.project;

import java.io.IOException;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AttendeeNameServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String id = req.getParameter("id");
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		if(id == null){
			resp.getWriter().print("request must contain an id parameter");
		}
		else if(id.equalsIgnoreCase("")){
			resp.getWriter().print("id parameter must not be empty");
		}
		else{
			id = id.trim();
			try{
				User user = pm.getObjectById(User.class, id);
				resp.setStatus(HttpServletResponse.SC_OK);
				
				//Switched to using CSV for this part, leaving
				//	this in here just in case...
				/*
				resp.setContentType("application/json");
				//Response in JSON Format
				String contentString = "{\"first_name\":" + "\"" + 
				user.getFirst_name() + "\"" + ", " + "\"last_name\":" + "\"" +
						user.getLast_name() + "\"" +", "+ "\"profile_img_url\":" +
				"\"" + user.getProfile_img() + "\"" +"}";
				resp.getWriter().print(contentString);
				*/
				
				String contentString = user.getFirst_name() 
						+ " " + user.getLast_name()  + "," + user.getProfile_img();
				resp.getWriter().print(contentString);
				resp.getWriter().flush();
			}
			catch(JDOObjectNotFoundException e){
				resp.getWriter().print("unable to find the user with the given id");
			}
		}	
	}
}
