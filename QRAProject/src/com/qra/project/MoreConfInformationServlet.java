package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MoreConfInformationServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		String conf_id = req.getParameter("conf_id");
		String page_output = req.getParameter("page_output");
		
		if(conf_id != null && (page_output == null || page_output.equals("false"))){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			//Find all the conferences and the sessions			
			Conference c = pm.getObjectById(Conference.class, conf_id);
			log.info("conference name: " + c.getConf_name());
			
			
			JSONObject results = new JSONObject();
			results.put("conf_name", c.getConf_code());
			results.put("conf_descrip", c.getConference_description());
			results.put("start_time", c.getStartTime());
			results.put("end_time", c.getEndTime());
			
			JSONArray jsonSessions = new JSONArray();
			for(Session s : getConfSessions(pm,conf_id)){
				JSONObject sess = new JSONObject();
				//TODO add street, city, and zip code
				sess.put("sess_descrip", s.getDescription());
				sess.put("sess_start_time", s.getStartTime());
				sess.put("sess_end_time", s.getEndTime());
				jsonSessions.add(sess);
			}
			
			results.put("sessions", jsonSessions);
			res.getWriter().println(results);
		}
		else if (conf_id != null && page_output.equals("true")){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			//Find all the conferences and the sessions			
			Conference c = pm.getObjectById(Conference.class, conf_id);
			req.setAttribute("conference", c);
			req.setAttribute("sessions", getConfSessions(pm,conf_id));
			req.setAttribute("title", "Conference Information");
			String url = "/moreconfinfo.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			try {
				dispatcher.forward(req, res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("error dispatching to jsp page from MoreConfInformationServlet");
			}
		}
		else{
			res.getWriter().println("error");
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{ 
		
	}
	
	public static List<Session> getConfSessions(PersistenceManager pm, String conf_id){
		List<Session> result;
		Query q = pm.newQuery(Session.class);
		q.setFilter("confCode == confCodeParam");
		q.declareParameters("String confCodeParam");
		
		List<Session> myConferenceSessions = 
				(List<Session>) q.execute(conf_id);
		result = myConferenceSessions;
		return result;
	}

}
