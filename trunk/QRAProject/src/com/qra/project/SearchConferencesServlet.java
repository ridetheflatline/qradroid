package com.qra.project;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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
	
    private static final Logger log = Logger.getLogger(SearchConferencesServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		
		if(CookieSessionCheck.check(req, res)!=null){
			
			String searchString = req.getParameter("search");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
	
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			boolean validSearchString = false;
			boolean validCityString = false;
			boolean validStateString = false;
			
			log.info("serachString: " + searchString);
			log.info("city: " + city);
			log.info("state: " + state);
			
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
		
			log.info("validsearch: " + validSearchString);
			log.info("validcity: " + validCityString);
			log.info("validstate: " + validStateString);
			
			//search by searchstring
			//search by city
			//search by state
			//search by searchstring and city
			//search by searchstring and state
			//search by city and state
			//serach by search string, city , and state
			if(validSearchString && !validCityString && !validStateString){
				//return conferences by search string
				jsonString = getSearchStringConferences(searchString,pm);
			}
			else if(!validSearchString && validCityString && !validStateString){
				//return conferences by city
				jsonString = getCitySearchConferences(city, pm);
			}
			else if(!validSearchString && !validCityString && validStateString){
				//return conferences by state
				jsonString = getStateSearchConferences(state, pm);
			}
			else if(validSearchString && validCityString && !validStateString){
				//return conferences by search string and city
				jsonString = getSearchAndCityConferences(searchString, city, pm);
			}
			else if(validSearchString && validCityString && !validStateString){
				//return conferences by search string and state
				jsonString = getSearchAndStateConferences(searchString, state, pm);
			}
			else if(!validSearchString && validCityString && validStateString){
				//return city and state search
				jsonString = getCityAndStateConferences(city, state, pm);
			}
			else if(validSearchString && validCityString && validStateString){
				//return conferences that match all
				jsonString = getSearchCityAndStateConferences(searchString,
						city, state,pm);
			}
			else{
				//return all conferences
				jsonString = getAllConferences(pm);
			}
			
			res.getWriter().println(jsonString);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}
	
	public static String getSearchCityAndStateConferences(String searchString, 
			String cityString, String stateString, 
			PersistenceManager pm){
		String result = "";
		Query q = null;
		
		List<Conference> conferences = getCityConferencesList(cityString, pm, q);
		List<Conference> subConferences = new ArrayList<Conference>();
		List<Conference> finalResults = new ArrayList<Conference>();
		
		for(Conference c : conferences){
			if(c.getState().trim().toLowerCase().equals(stateString.trim().toLowerCase())){
				subConferences.add(c); 
			}
		}
		
		for(Conference c : subConferences){
			String confName = c.getConf_name();
			String confDescript = c.getConference_description();
			boolean confNameMatch = 
					confName.toLowerCase().contains(searchString.toLowerCase());
			boolean confDescriptMatch =
					confDescript.toLowerCase().contains(searchString.toLowerCase());
			if(confNameMatch || confDescriptMatch){
				finalResults.add(c);
			}
		}
		result = buildJSONString(finalResults,pm, q);
		return result;
	}
	
	public static String getCityAndStateConferences(String cityString, String stateString, 
			PersistenceManager pm){
		String result = "";
		Query q = null;
		List<Conference> conferences = getCityConferencesList(cityString, pm, q);
		List<Conference> subConferences = new ArrayList<Conference>();
		
		for(Conference c : conferences){
			if(c.getState().trim().toLowerCase().equals(stateString.trim().toLowerCase())){
				subConferences.add(c); 
			}
		}
		result = buildJSONString(subConferences,pm, q);
		return result;
	}
	public static String getSearchAndStateConferences(String searchString,
			String stateString,PersistenceManager pm){
		String result = "";
		Query q = null;
		List<Conference> conferences = getStateConferencesList(stateString, pm, q);
		result = 
			buildJSONString(returnConferencesFromSearchStringConferences(searchString,conferences),pm, q);
		return result;
	}
	
	public static String getSearchAndCityConferences(String searchString, 
			String cityString, PersistenceManager pm){
		String result = "";
		Query q = null;
		List<Conference> conferences = getCityConferencesList(cityString, pm, q);
		result = buildJSONString(returnConferencesFromSearchStringConferences(searchString,conferences),pm, q);
		return result;
	}
	
	public static List<Conference> getCityConferencesList(String cityString, 
			PersistenceManager pm, Query q){
		q = pm.newQuery(Conference.class);
		q.setFilter("city == cityParam");
		q.declareParameters("String cityParam");
		List<Conference> conferences = (List <Conference>)q.execute(cityString.toLowerCase().trim());
		return conferences;
	}
	
	public static List<Conference> getStateConferencesList(String stateString, 
			PersistenceManager pm, Query q){
		q = pm.newQuery(Conference.class);
		q.setFilter("state == stateParam");
		q.declareParameters("String stateParam");
		List<Conference> conferences = (List <Conference>)q.execute(stateString.toLowerCase().trim());
		return conferences;
	}
	
	public static List<Conference> returnConferencesFromSearchStringConferences(
			String searchString, List<Conference> conferences){
		List<Conference> conferencesSubSet = new ArrayList<Conference>();
		for(Conference c : conferences){
			String confName = c.getConf_name();
			String confDescript = c.getConference_description();
			boolean confNameMatch = 
					confName.toLowerCase().contains(searchString.toLowerCase());
			boolean confDescriptMatch =
					confDescript.toLowerCase().contains(searchString.toLowerCase());
			if(confNameMatch || confDescriptMatch){
				conferencesSubSet.add(c);
			}
		}
		return conferencesSubSet;
	}
	
	public static String getStateSearchConferences(String stateString, PersistenceManager pm){
		String result = "";
		Query q  = null;
		List <Conference> conferences = getStateConferencesList(stateString, pm, q);
		result = buildJSONString(conferences, pm, q);
		return result;
	}
	public static String getCitySearchConferences(String cityString, PersistenceManager pm){
		String result = "";
		Query q = null;
		List<Conference> conferences = getCityConferencesList(cityString, pm, q);
		result = buildJSONString(conferences, pm, q);
		return result;
	}
	//Return conferences that have matches with conference name and conference description
	public static String getSearchStringConferences(String searchString, PersistenceManager pm){
		String result = "";
		Query q = pm.newQuery(Conference.class);
		List<Conference> conferences = (List <Conference>)q.execute();
		List<Conference> conferencesSubSet = new ArrayList<Conference>();
		
		for(Conference c : conferences){
			String confName = c.getConf_name();
			String confDescript = c.getConference_description();
			boolean confNameMatch = 
					confName.toLowerCase().contains(searchString.toLowerCase());
			boolean confDescriptMatch =
					confDescript.toLowerCase().contains(searchString.toLowerCase());
			if(confNameMatch || confDescriptMatch){
				conferencesSubSet.add(c);
			}
		}
		result = buildJSONString(conferencesSubSet, pm, q);
		return result;
	}
	public static String getAllConferences(PersistenceManager pm){
		String result = "";
		Query q = pm.newQuery(Conference.class);
		List<Conference> conferences = (List <Conference>)q.execute();
			//log.info("number of conferences: " +conferences.size());
		log.info("getAllConferences Size: " + conferences.size());
		result=buildJSONString(conferences,pm, q);
		return result;
	}
	public static String buildJSONString(List<Conference> myInputConferences, 
			PersistenceManager pm, Query q)
	{
		String result = "";
		JSONArray jsonConferences = new JSONArray();
		for(Conference c : myInputConferences){
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
		//q.closeAll();
		return jsonToString(jsonConferences);
	}
	public static String jsonToString(JSONArray jsonConferences){
		String result = "";
		StringWriter out = new StringWriter();
		try {
			jsonConferences.writeJSONString(out);
			result = out.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Unable to write jsonConferences");
		}
		return result;
	}
}
