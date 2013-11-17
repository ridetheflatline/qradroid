package com.qra.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateAttServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String confID = req.getParameter("conf_id");
		String fullName = null;
		String userID = null;
		String userName=CookieCheck.check(req, resp);
		Conference tempConf=null;
		
		
		if(userName!=null){
			
			
			List<Session> sessions=getConfSessions(pm,confID);
			List<ConferenceAttendee> attendees=getAttendees(pm,confID);
			
			ArrayList<AttCalc> attData = new ArrayList<AttCalc>();
			
			for(int i=0;i<attendees.size();i++){
				userID=attendees.get(i).getID();
				User tempUser=pm.getObjectById(User.class, userID);
				fullName=tempUser.getFirst_name()+" "+tempUser.getLast_name();
				String[] presence=new String[sessions.size()];
				for(int j=0;j<sessions.size();j++){
					List<Attendance_Records> records=getRecords(pm,confID,userID);
					boolean inRange=false;
					presence[j]="Absent";
					for(int k=0;k<records.size();k++){
						if(isWithinRange(records.get(k).getDate(),sessions.get(j).getStartTime(),sessions.get(j).getEndTime())){
							presence[j]="Present";
							break;
						}
					}
				}
				attData.add(new AttCalc(fullName,presence,sessions.size()));
			}
			
			
			
			
			
			req.setAttribute("attdata", attData);
			String url = "/calcatt.jsp";
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(url);
			try {
				dispatcher.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	public static List<ConferenceAttendee> getAttendees(PersistenceManager pm, String conf_id){
		List<ConferenceAttendee> result;
		Query q = pm.newQuery(ConferenceAttendee.class);
		q.setFilter("conf_code == confCodeParam");
		q.declareParameters("String confCodeParam");
		
		List<ConferenceAttendee> myConferenceSessions = 
				(List<ConferenceAttendee>) q.execute(conf_id);
		result = myConferenceSessions;
		return result;
	}
	public static List<Attendance_Records> getRecords(PersistenceManager pm, String conf_id, String user_id){
		List<Attendance_Records> result;
		Query q = pm.newQuery(Attendance_Records.class);
		q.setFilter("conf_code == confCodeParam");
		q.declareParameters("String confCodeParam");
		q.setFilter("user_id == userParam");
		q.declareParameters("String userParam");
		
		List<Attendance_Records> myConferenceSessions = 
				(List<Attendance_Records>) q.execute(conf_id,user_id);
		result = myConferenceSessions;
		return result;
	}
	
	
	boolean isWithinRange(Date testDate, Date startDate, Date endDate){
		return !(testDate.before(startDate)||testDate.after(endDate));
	}
	
}
