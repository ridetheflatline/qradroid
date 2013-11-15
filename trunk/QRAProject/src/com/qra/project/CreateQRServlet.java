package com.qra.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQRServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpSession session = req.getSession();
		String userName=(String)session.getAttribute("userSess");
		String userID=null;

		Query q = pm.newQuery(User.class, "username == '" + userName+ "'");

		List<User> results = (List<User>) q.execute();

		resp.getWriter().print(""+results.get(0).getID());
		
//		fullName=results.get(0).getFirst_name()+" "+results.get(0).getLast_name();
		userID=results.get(0).getID();
		
		//Get Conferences user is signed up to attend
		Query q2 = pm.newQuery(ConferenceAttendee.class, "user_id == '" +userID+ "'");
		
		List<ConferenceAttendee> attResults = (List<ConferenceAttendee>) q2.execute();
		String confID=null;
		Conference tempConf=null;
		ArrayList<Conference> attQRData = new ArrayList<Conference>();
		
		for (int i=0;i<attResults.size();i++){
			confID=attResults.get(i).getConf_code();
			tempConf = pm.getObjectById(Conference.class, confID);
			attQRData.add(tempConf);
		}
		req.setAttribute("attqrarray", attQRData);
		
		
		//Get Conferences user is hosting
		Query q3 = pm.newQuery(Conference.class, "host_ID == '" + userName+ "'");

		List<Conference> confResults = (List<Conference>) q3.execute();
		ArrayList<Conference> hostQRData = new ArrayList<Conference>();
		
		for(int j=0;j<confResults.size();j++){
			hostQRData.add(confResults.get(j));
		}
		req.setAttribute("hostqrarray", hostQRData);
	
		
		String url = "/createqr.jsp";
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(url);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
