package com.qra.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateBatchQRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String userName = req.getParameter("username");
		String fullName = null;
		String userID = null;
		String confID=null;
		String confName=null;

		try {

			Query q = pm.newQuery(Conference.class, "host_ID == '" + userName+ "'");

			List<Conference> confResults = (List<Conference>) q.execute();

//			resp.getWriter().print(""+results.get(0).getID());
			ArrayList<QRData> qrData = new ArrayList<QRData>();
			
			for(int i=0; i<confResults.size();i++){
				confID=confResults.get(i).getConf_code();
				confName=confResults.get(i).getConf_name();
				Query q2 = pm.newQuery(ConferenceAttendee.class, "conf_code == '" +confID+ "'");
				List<ConferenceAttendee> attResults = (List<ConferenceAttendee>) q2.execute();
				
				User tempUser=null;
				for(int j=0;j<attResults.size();j++){
					userID=attResults.get(j).getUser_id();
					tempUser=pm.getObjectById(User.class, userID);
					fullName=tempUser.getFirst_name()+" "+tempUser.getLast_name();
					qrData.add(new QRData(confName,fullName,userID,confID,"test"));
				}
				
			}
			
			

			
			req.setAttribute("qrarray", qrData);
			String url = "/createmyqr.jsp";
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(url);
			dispatcher.forward(req, resp);
			
		} catch (JDOObjectNotFoundException e) {
			resp.getWriter()
					.print("We are unable to find the user with the given username and password");
			resp.getWriter().print(
					"<br> <a href=\"login.jsp\">Return to Log In</a>");
		}
	}
	
	
}
