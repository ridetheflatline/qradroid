package com.qra.project;

import java.io.IOException;
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

public class FindConferencesServlet extends HttpServlet {
	 private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	 
	 public void doGet(HttpServletRequest req, HttpServletResponse res)
			 throws IOException 
	 {
		HttpSession session = req.getSession();
		String userIdFromSess = (String) session.getAttribute("userSess");
		String userIdFromCookie = CookieCheck.check(req, res);
		
		if(userIdFromSess != null ||
				userIdFromCookie != null){
		
			Cookie userKeyIdCookie = null;
			if(userIdFromSess != null){
				userKeyIdCookie = new Cookie("userKeyId", getUserKeyId(userIdFromSess));
			}
			else{
				userKeyIdCookie = new Cookie("userKeyId", getUserKeyId(userIdFromSess));
			}
			//Set for 10 minutes
			userKeyIdCookie.setMaxAge(60*10);
			userKeyIdCookie.setPath("/");
			res.addCookie(userKeyIdCookie);
		}
		
		res.sendRedirect("/findconferences.jsp");	
	 }
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException 
	 {
		 
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
}
