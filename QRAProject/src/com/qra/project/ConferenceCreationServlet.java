package com.qra.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

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
		//Dates should be: 11/08/13 12:30PM
		JSONObject jsonObject =  (JSONObject) JSONValue.parse(bodyContent);
		String conferenceName = (String) jsonObject.get("conference_name");
		String conferenceDescription = (String) jsonObject.get("conference_description");
		
		log.info("conferenceName: " + conferenceName);
		log.info("conferenceDescription: " + conferenceDescription);
		
		JSONArray sessions = (JSONArray) jsonObject.get("sessions");
		ArrayList<Date> myDates = new ArrayList<Date>();
		
		for(int i = 0; i < sessions.size(); i++){
			log.info("session " + i + ": ");
			JSONObject sessionObj = (JSONObject)sessions.get(i);
			log.info("\tstart_time: " + sessionObj.get("start_time"));
			log.info("\tend_time: " + sessionObj.get("end_time") );
			log.info("\tsession_description: " + sessionObj.get("session_description") );
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
