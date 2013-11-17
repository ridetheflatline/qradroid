package com.qra.project;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.KeyFactory;

public class SearchConferencesServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		String searchString = req.getParameter("search");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		
		boolean validSearchString = false;
		boolean validCityString = false;
		boolean validStateString = false;
		
		//Or search of user_id
		//if this is not null then only use this
		String user_id = req.getParameter("user_id");
		
		String jsonString = "";
		
		if(searchString != null && searchString != ""){
			validSearchString=true;
		}
		
		if(city != null && city != ""){
			validCityString=true;
		}
		
		if(state != null && state != ""){
			validStateString=true;
		}
		
		//search by searchstring
		//search by city
		//search by state
		//search by searchstring and city
		//search by searchstring and state
		//search by city and state
		if(validSearchString && !validCityString && !validStateString){
			
		}
		else{
			//generate pages for joining conferences
			jsonString = returnAllConferences();
		}
		
		res.getWriter().println(jsonString);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}
	
	public static String returnAllConferences(){
		String result = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Conference.class);
		
		List<Conference> conferences = (List <Conference>)q.execute();
		//log.info("number of conferences: " +conferences.size());
		
		JSONArray jsonConferences = new JSONArray();
		for(Conference c : conferences){
			q = pm.newQuery(Session.class);
			q.setFilter("confCode == confCodeParam");
			q.declareParameters("String confCodeParam");
			
			String confCodeStr = c.getConf_code();
					//KeyFactory.createKeyString("Conference",c.getConf_code());
			log.info("conference code str: " + confCodeStr);
			List<Session> myConferenceSessions = 
					(List<Session>) q.execute(confCodeStr);
						
			if(myConferenceSessions.size() > 0){
				JSONObject obj = new JSONObject();
				obj.put("conf_id", confCodeStr);
				obj.put("conf_name", c.getConf_name());
				obj.put("conf_descrip", c.getConference_description());
				obj.put("numSessions", myConferenceSessions.size());
				jsonConferences.add(obj);
			}
		}
		
		StringWriter out = new StringWriter();
		
		try {
			jsonConferences.writeJSONString(out);
			result = out.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Unable to write jsonConferences");
		}
		
		q.closeAll();
		return result;
	}
	
}
