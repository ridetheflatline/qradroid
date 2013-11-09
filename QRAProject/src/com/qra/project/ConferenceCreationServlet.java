package com.qra.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


//Test data
/*
 * {"conference_name":"Test Conference","conference_description":"This is test conference description","sessions":[{"start_time":"11/08/2013 at 12:30 PM","end_time":"11/08/2013 at 1:30 PM","session_description":"This is session"},{"start_time":"11/09/2013 at 12:30 PM","end_time":"11/09/2013 at 1:30 PM","session_description":"This is another session"}]}
 *
 * {"conference_name":"My conference","conference_description":"fdafjdla;dfj","sessions":[{"start_time":"11/08/2013 at 2:12 PM","end_time":"11/08/2013 at 3:12 PM","session_description":"jdlakjfda"}]}
 * 
 */

public class ConferenceCreationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		res.sendRedirect("createconference.jsp");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{ 
		//Contains 
		String bodyContent = getBody(req);
		log("body: " + bodyContent);
		
		//Parse JSON body string and insert into datastore
		//Dates should be: 11/08/13 12:30 PM
		//or 11/08/13 1:12 PM
		JSONObject jsonObject =  (JSONObject) JSONValue.parse(bodyContent);
		String conferenceName = (String) jsonObject.get("conference_name");
		String conferenceDescription = (String) jsonObject.get("conference_description");
		
		log.info("conferenceName: " + conferenceName);
		log.info("conferenceDescription: " + conferenceDescription);
		
		JSONArray sessions = (JSONArray) jsonObject.get("sessions");
		ArrayList<Date> myDates = new ArrayList<Date>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy h:m a");
		
		for(int i = 0; i < sessions.size(); i++){
			//log.info("session " + i + ": ");
			JSONObject sessionObj = (JSONObject)sessions.get(i);
			
			String sesStartTime = (String) sessionObj.get("start_time");
			log.info("\tstart_time: " + sessionObj.get("start_time"));
			
			String sesEndTime = (String)sessionObj.get("end_time");
			log.info("\tend_time: " + sessionObj.get("end_time") );
			
			log.info("\tsession_description: " + sessionObj.get("session_description") );
			try {
				Date startDtFormatted = formatter.parse(sesStartTime);
				myDates.add(startDtFormatted);
				
				Date endDtFormatted = formatter.parse(sesEndTime);
				myDates.add(endDtFormatted);
				
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
		
		//For testing purposes...
		String tempHostId = "";
		
		//Insert conference
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean insertConfSuccessful = true;
		
		Conference c = new Conference
				(conferenceName, tempHostId, conf_start_time, conf_end_time, conferenceDescription);
		Object o = null;
		try{
			 o = pm.makePersistent(c);
		}
		finally{
			pm.close();
			
			if(o == null){
				insertConfSuccessful = false;
			}
		}
		
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
