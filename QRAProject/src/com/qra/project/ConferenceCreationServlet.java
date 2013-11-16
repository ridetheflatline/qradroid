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
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

//Test data
/*
 * {"userId": "kdfd", "conference_name":"Test Conference","conference_description":"This is test conference description","sessions":[{"start_time":"11/08/2013 at 12:30 PM","end_time":"11/08/2013 at 1:30 PM","session_description":"This is session"},{"start_time":"11/09/2013 at 12:30 PM","end_time":"11/09/2013 at 1:30 PM","session_description":"This is another session"}]}
 *
 * {"userId": "kdfk", "conference_name":"My conference","conference_description":"fdafjdla;dfj","sessions":[{"start_time":"11/08/2013 at 2:12 PM","end_time":"11/08/2013 at 3:12 PM","session_description":"jdlakjfda"}]}
 * 
 */
public class ConferenceCreationServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		HttpSession session = req.getSession();
		String userIdFromSess = (String) session.getAttribute("userSess");
		String userIdFromCookie = CookieCheck.check(req, res);
		
		if(userIdFromSess != null ||
				userIdFromCookie != null){
			
			String userId = "";
			
			if(userIdFromSess != null)
				userId = userIdFromSess;
			else
				userId = userIdFromCookie;
			
			//log.info(getUserKeyId(userId));
			Cookie userKeyIdCookie = 
					new Cookie("userKeyId", getUserKeyId(userId));
			//Set for 10 minutes
			userKeyIdCookie.setMaxAge(60*10);
			userKeyIdCookie.setPath("/");
			res.addCookie(userKeyIdCookie);
			res.sendRedirect("/createconference.jsp");
		}
		else{
			res.sendRedirect("/index.jsp");
		}
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
		String conferenceStreet = (String)jsonObject.get("conf_street");
		String conferenceCity = (String)jsonObject.get("conf_city");
		String conferenceState = (String)jsonObject.get("conf_city");
		String hostId = (String)jsonObject.get("userId");
		JSONArray sessions = (JSONArray) jsonObject.get("sessions");
		
		if(conferenceName != null && conferenceDescription != null 
				&& conferenceStreet != null && conferenceCity != null &&
				conferenceState != null && hostId != null && sessions != null &&
				sessions.size() > 0){
			
			if(insertConference(conferenceName,conferenceDescription,conferenceStreet,
					conferenceCity,conferenceState, hostId, sessions))
			{
				//return successful code
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("text/plain");
				res.getWriter().println("Success");
			}
			else{
				//return error
				res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				res.setContentType("text/plain");
				res.getWriter().println("Insert failed. Please try again later.");
			}
		}
		else{
			//return parameters not good
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setContentType("text/plain");
			res.getWriter().println("Invalid Parameters.");
		}
		
	}
	
	public static String getUserKeyId(String username){
		String result = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		
		q.setFilter("username == usernameParam");
		q.declareParameters("String usernameParam");
		
		List<User> myUser = (List<User>)q.execute(username);
		
		if(myUser.size() > 0){
			log.info("key id: " + myUser.get(0).getID());
			result = myUser.get(0).getID();
		}
		
		return result;
	}
	
	public static boolean insertConference(String conferenceName,
			String conferenceDescription, String conferenceStreet, String conferenceCity,
			String conferenceState, String hostId, JSONArray sessions){
		
		boolean insertSuccess = true;
		
		ArrayList <Session> mySessions = new ArrayList <Session>();
		ArrayList<Date> myDates = new ArrayList<Date>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy h:m a");
		
		for(int i = 0; i < sessions.size(); i++){
			//log.info("session " + i + ": ");
			JSONObject sessionObj = (JSONObject)sessions.get(i);
			
			String sesStartTime = (String) sessionObj.get("start_time");
			log.info("\tstart_time: " + sessionObj.get("start_time"));
			
			String sesEndTime = (String)sessionObj.get("end_time");
			log.info("\tend_time: " + sessionObj.get("end_time") );
			
			String sesDescription = (String)sessionObj.get("session_description");
			log.info("\tsession_description: " + sesDescription);
			try {
				Date startDtFormatted = formatter.parse(sesStartTime);
				myDates.add(startDtFormatted);
				
				Date endDtFormatted = formatter.parse(sesEndTime);
				myDates.add(endDtFormatted);
				
				mySessions.add(new Session(sesDescription, startDtFormatted, endDtFormatted));
				
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
		
		//Insert conference
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Object o = null;
		boolean insertConfSuccessful = true;
		
		Conference c = new Conference(conferenceName,
				hostId, conf_start_time, conf_end_time, conferenceDescription,
				conferenceStreet, conferenceCity,conferenceState);
		
		String confCodeString = "";
		try{
			 o = pm.makePersistent(c);
			 confCodeString = c.getConf_code();
		}
		finally{
			if(o == null){
				insertConfSuccessful = false;
			}
		}
		
		if(!insertConfSuccessful){
			log.info("Unable to insert the conference");
			insertSuccess = false;
		}
		
		if(insertConfSuccessful){
			o = null;
			boolean insertSessionSuccessful = true;
			
			//Set all the session with the correct conference code
			for(Session s : mySessions){
				s.setConfCode(confCodeString);
			}
			
			try{
				o = pm.makePersistentAll(mySessions);
			}
			finally{
				pm.close();
				if(o == null){
					insertSessionSuccessful = false;
				}
			}
			
			if(!insertSessionSuccessful){
				log.info("Unable to insert the sessions.");
				insertSuccess = false;
			}
		}
		
		return insertSuccess;
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
