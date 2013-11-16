package com.qra.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQRServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		/*
		HttpSession session = req.getSession();
		String userName=(String)session.getAttribute("userSess");
		*/
		String userName;
		String userID=null;
		
		Cookie[] cookies = req.getCookies();
		String cookieName = "userIDCookie";
		String cookieValue = "";
		if (cookies != null)//if there are cookies
		{
			for (int i = 0; i < cookies.length; i++) //search through the cookies
			{
				Cookie cookie = cookies[i]; //take one cookie

				if (cookieName.equals(cookie.getName()))// is it the cookie we are looking for?
				{
					cookieValue = cookie.getValue(); //cookieValue is username 
				}
				else
				{
					if(i == cookies.length-1 && cookieValue != null) //If it's the last cookie and the userIdCookie is still null
					{
						resp.getWriter().print("You're not logged in.<br>");
					}
				}
			}
		}
		else {
			resp.getWriter().print("<br>You're not logged in.<br>");
		}
		
		userName = cookieValue;

		Query q = pm.newQuery(User.class, "username == '" + userName+ "'");

		List<User> results = (List<User>) q.execute();
		userID=results.get(0).getID();
		
		//Get Conferences user is signed up to attend
		Query q2 = pm.newQuery(ConferenceAttendee.class, "user_id == '" +userID+ "'");
		
		List<ConferenceAttendee> attResults = (List<ConferenceAttendee>) q2.execute();
		if (attResults.size()>0){
			
			String confID=null;
			Conference tempConf=null;
			ArrayList<Conference> attQRData = new ArrayList<Conference>();
			
			for (int i=0;i<attResults.size();i++){
				confID=attResults.get(i).getConf_code();
				tempConf = pm.getObjectById(Conference.class, confID);
				attQRData.add(tempConf);
			}
			req.setAttribute("attqrarray", attQRData);
			
			resp.getWriter().print("attResults.size() is greater than 0 <br>");
		}
		else
			resp.getWriter().print("No results for attResults.size()");
		
		
		//Get Conferences user is hosting
		Query q3 = pm.newQuery(Conference.class, "host_ID == '" + userName+ "'");

		List<Conference> confResults = (List<Conference>) q3.execute();
		
		if(confResults.size()>0){
			
			ArrayList<Conference> hostQRData = new ArrayList<Conference>();
			
			for(int j=0;j<confResults.size();j++){
				hostQRData.add(confResults.get(j));
			}
			req.setAttribute("hostqrarray", hostQRData);
			
			resp.getWriter().print("confResults.size()>0\"<br>");
		}
		else
			resp.getWriter().print("No results for confResults.size()>0");
	
		
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
