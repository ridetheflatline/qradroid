package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinConferenceServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		String conf_code = req.getParameter("conf_code");
		String user_id = req.getParameter("user_id");
		
		//The generated key string
		log.info("conf_code: " + conf_code);
		//The generated key string
		log.info("user_id: "+ user_id);
		
		res.setContentType("text/plain");
		if(conf_code != null && user_id != null && 
				!conf_code.equals("") && !user_id.equals("")){
			ConferenceAttendee ca = new ConferenceAttendee(conf_code, user_id);
			//Make need to add check to make sure the given conf code and 
			//user id actually exists
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Object o = null;
			try{
				 o = pm.makePersistent(ca);
			}
			finally{
				pm.close();
				if(o != null){
					res.setStatus(HttpServletResponse.SC_OK);
					res.getWriter().println("Success");
				}
			}
		}else{
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.getWriter().println("Error");
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}

}
