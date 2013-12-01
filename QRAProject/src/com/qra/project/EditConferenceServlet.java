package com.qra.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class EditConferenceServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		String confId = req.getParameter("confId");
		
		if(confId != null){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			log.info("confId: " + confId);
			Conference c = pm.getObjectById(Conference.class, confId);
			req.getSession().setAttribute("conference", c);
			Query q = pm.newQuery(Session.class, "confCode == confCodeParam");
			q.declareParameters("String confCodeParam");
			List<Session> sessionResults = (List<Session>) q.execute(confId);
			ArrayList <Session> mySessions = new ArrayList<Session>();
			
			for(int i =0; i < sessionResults.size(); i++){
				Session s = sessionResults.get(i);
				mySessions.add(new Session(confId, s.getDescription(), s.getStartTime(), s.getEndTime(), c.getTimeZone()));
			}
			req.getSession().setAttribute("mySessions", mySessions);
			resp.sendRedirect("/editconferences.jsp");
		}else{
			
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
		
		String bodyContent = getBody(req);
		log("body: " + bodyContent);
		
		//Parse JSON body string and insert into datastore
		//Dates should be: 11/08/13 12:30 PM
		//or 11/08/13 1:12 PM
		JSONObject jsonObject =  (JSONObject) JSONValue.parse(bodyContent);
		
		String confId = (String)jsonObject.get("conference_id");
		String conferenceName = (String) jsonObject.get("conference_name");
		String conferenceDescription = (String) jsonObject.get("conference_description");
		String conferenceStreet = (String)jsonObject.get("conf_street");
		String conferenceCity = (String)jsonObject.get("conf_city");
		String conferenceState = (String)jsonObject.get("conf_state");
		String timeZoneValue=(String)jsonObject.get("timeZoneValue");
		
		JSONArray sessions = (JSONArray) jsonObject.get("sessions");
		
		if(conferenceName != null && conferenceDescription != null 
		  && conferenceStreet != null && conferenceCity != null &&
		  conferenceState != null && timeZoneValue != null && confId != null && sessions != null &&
		  sessions.size() > 0){
			
			if(updateConference(confId,conferenceName,conferenceDescription,
			conferenceStreet,conferenceCity,conferenceState,timeZoneValue,sessions)){
				
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("Success");
				
			}else{
				resp.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				resp.setContentType("text/plain");
				resp.getWriter().println("Insert failed. Please try again later.");
			}
			
		}else{
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.setContentType("text/plain");
			resp.getWriter().println("Invalid Parameters.");
		}
		
	}
	public static boolean updateConference(String confId, String conferenceName,String conferenceDescription,
			String conferenceStreet,String conferenceCity,
			String conferenceState,String timeZoneValue,JSONArray sessions){
		
		boolean result = true;
		
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    
	    //Delete all the sessions where the confCode is == confId  
	    Query q = pm.newQuery(Session.class, "confCode");
	    q.declareParameters("String confCodeParam");
	    
	    List<Session> sessionResults = (List<Session>) q.execute(confId);
	    pm.deletePersistentAll(sessionResults);
	 
	    //Add the new sessions
	    ArrayList <Session> mySessions = new ArrayList <Session>();
		ArrayList<Date> myDates = new ArrayList<Date>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy h:m a");
		TimeZone ts = TimeZone.getTimeZone(timeZoneValue);
		log.info("ts: " + ts);
		formatter.setTimeZone(ts);
		
		SimpleDateFormat timeZoneFormat = new SimpleDateFormat("MM/dd/yy h:m Z"); 
		
		for(int i = 0; i < sessions.size(); i++){
			//log.info("session " + i + ": ");
			JSONObject sessionObj = (JSONObject)sessions.get(i);
			
			//Change these times based on the timezone
			String sesStartTime = (String) sessionObj.get("start_time");
			log.info("\tstart_time: " + sessionObj.get("start_time"));
			
			String sesEndTime = (String)sessionObj.get("end_time");
			log.info("\tend_time: " + sessionObj.get("end_time") );
			
			String sesDescription = (String)sessionObj.get("session_description");
			log.info("\tsession_description: " + sesDescription);
			try {
				Date startDtFormatted = formatter.parse(sesStartTime);
				log.info("old Timezone: " +startDtFormatted);
				
				//String newDateFormmated = timeZoneFormat.format(startDtFormatted);
				//log.info("newDateFormmated: "+newDateFormmated);
				
				//Date oldWithNewFormatter = timeZoneFormat.parse(sesStartTime);
				//log.info("oldWithNewFormatter: " + oldWithNewFormatter);
				
				//Date timeZoneStartDt = timeZoneFormat.parse(newDateFormmated);
				//log.info("timeZoneStartDt: " + timeZoneStartDt);
				
				myDates.add(startDtFormatted);
				
				Date endDtFormatted = formatter.parse(sesEndTime);
				myDates.add(endDtFormatted);
				
				mySessions.add(new Session(confId, sesDescription, startDtFormatted, endDtFormatted));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Sort the dates to get the startTime and endTime
		Collections.sort(myDates, new Comparator<Date>() {
		    @Override
		    public int compare(Date lhs, Date rhs) {
		        if (lhs.getTime() < rhs.getTime())
		            return -1;
		        else if (lhs.getTime() == rhs.getTime())
		            return 0;
		        else
		            return 1;
		    }
		});
		
		Date conf_start_time = myDates.get(0);
		Date conf_end_time = myDates.get(myDates.size() - 1);
		Object o = null;
		
	    try{
	    	Conference c = pm.getObjectById(Conference.class, confId);
	    	c.setConf_name(conferenceName);
	    	c.setConference_description(conferenceDescription);
	    	c.setAddress(conferenceStreet);
	    	c.setCity(conferenceCity);
	    	c.setState(conferenceState);
	    	c.setTimeZone(timeZoneValue);
	    	c.setStartTime(conf_start_time);
	    	c.setEndTime(conf_end_time);
	    	
	    	o = pm.makePersistent(c);
	    }finally{
	    	if(o == null){
	    		result = false;
	    	}   
	    	
	    }
	    
	    try{
			o = pm.makePersistentAll(mySessions);
		}
		finally{
			pm.close();
			if(o == null){
				result = false;
			}
			pm.close();
		}
	    
		return result;
	}
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}

}
