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


/***
 * Servlet that retrieves all the conferences a user is either attending or hosting
 * and passes those to a jsp to allow the user to choose what to print.
 * @author Joel Friberg
 *
 */
public class CreateQRServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userName=CookieSessionCheck.check(req, resp);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String userID = null;
		boolean attEmpty=false;

		if (userName != null) {
			Query q = pm.newQuery(User.class, "username == '" + userName + "'");

			List<User> results = (List<User>) q.execute();
			userID = results.get(0).getID();

			// Get Conferences user is signed up to attend
			Query q2 = pm.newQuery(ConferenceAttendee.class, "user_id == '"+ userID + "'");

			List<ConferenceAttendee> attResults = (List<ConferenceAttendee>) q2.execute();
			if (attResults.size() > 0) {

				String confID = null;
				Conference tempConf = null;
				ArrayList<Conference> attQRData = new ArrayList<Conference>();

				 for (int j=0;j<attResults.size();j++){
					 confID=attResults.get(j).getConf_code();
					 tempConf = pm.getObjectById(Conference.class, confID);
					 attQRData.add(tempConf);
				 }
				req.setAttribute("attqrarray", attQRData);
			} else{
				attEmpty=true;
			}

			// Get Conferences user is hosting
			Query q3 = pm.newQuery(Conference.class, "host_ID == '" + userID+ "'");

			List<Conference> confResults = (List<Conference>) q3.execute();

			if (confResults.size() > 0) {

				ArrayList<Conference> hostQRData = new ArrayList<Conference>();

				 for(int j=0;j<confResults.size();j++){
				 hostQRData.add(confResults.get(j));
				 }
				req.setAttribute("hostqrarray", hostQRData);
			} else{
				if(attEmpty){
					resp.setHeader("Refresh", "5; URL=index.jsp");
					resp.getWriter().print("You are not attending or hosting any conferences");
					resp.getWriter().print("<br>You will be redirected to home in 5 seconds.");
					resp.getWriter().print("<br><a href=\"index.jsp\">Return to home</a>");
				}
			}
			
			
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
}
