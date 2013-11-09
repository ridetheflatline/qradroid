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

public class CreateMyQRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String userName = req.getParameter("username");
		String fullName = null;
		String userID = null;

		try {

			Query q = pm.newQuery(User.class, "username == '" + userName+ "'");

			List<User> results = (List<User>) q.execute();

			resp.getWriter().print(
					""+results.get(0).getID());
			
			fullName=results.get(0).getFirst_name()+" "+results.get(0).getLast_name();
			userID=results.get(0).getID();
			
			
			Query q2 = pm.newQuery(ConferenceAttendee.class, "user_id == '" +userID+ "'");
			
			List<ConferenceAttendee> attResults = (List<ConferenceAttendee>) q2.execute();
			String confID=null;
			Conference tempConf=null;
			ArrayList<QRData> qrData = new ArrayList<QRData>();
			
			for (int i=0;i<attResults.size();i++){
				confID=attResults.get(i).getConf_code();
				tempConf = pm.getObjectById(Conference.class, confID);
				qrData.add(new QRData(tempConf.getConf_name(),fullName,userID,confID,"test"));
				
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
