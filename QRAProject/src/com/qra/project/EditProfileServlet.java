package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfileServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {			
			resp.setContentType("text/HTML");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			String first_name = req.getParameter("first_name");
			String middle_name = req.getParameter("middle_name");
			String last_name = req.getParameter("last_name");
			String profile_img = req.getParameter("profile_img");
			String birthdate = req.getParameter("birthdate");
			String oldpassword = req.getParameter("oldpassword");
			String newpassword1 = req.getParameter("newpassword1");
			String newpassword2 = req.getParameter("newpassword2");
			
			
			
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
			
			

			if(first_name == null || middle_name == null || last_name == null || profile_img == null || birthdate == null){
				
				log.info("first_name: "+first_name);
				log.info("middle_name: "+middle_name);
				log.info("last_name: "+last_name);
				log.info("profile_img: "+profile_img);
				log.info("birthdate: "+birthdate);
				
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "A Parameter is null.");
			}
			else{	
				Query q = pm.newQuery(User.class, "username == '"  + cookieValue + "'");
				List<User> results = (List<User>) q.execute();
				
				//resp.getWriter().print(results.get(0).getFirst_name()); 
				//results.get(0) gets the first matching entity
				if(first_name.equalsIgnoreCase("")||first_name.equalsIgnoreCase(""))
				{
					resp.getWriter().print("You have not entered a first name.<br>");
					resp.getWriter().print("No changes will be made to your first name.<br>");
				}
				else
				{
					results.get(0).setFirst_name(first_name); //sets firstname to the first name put in the form
				}
				if(middle_name.equalsIgnoreCase("")||middle_name.equalsIgnoreCase(""))
				{
					resp.getWriter().print("You have not entered a middle name.<br>");
					resp.getWriter().print("No changes will be made to your middle name.<br>");
				}
				else
				{
				results.get(0).setMiddle_name(middle_name);
				}
				if(last_name.equalsIgnoreCase("")||last_name.equalsIgnoreCase(""))
				{
					resp.getWriter().print("You have not entered a last name.<br>");
					resp.getWriter().print("No changes will be made to your last name.<br>");
				}
				else
				{
					results.get(0).setLast_name(last_name);
				}
				if(newpassword1.equalsIgnoreCase("")||newpassword1.equalsIgnoreCase(""))
				{
					resp.getWriter().print("You have not entered a new password.<br>");
					resp.getWriter().print("No changes will be made to your password.<br>");
				}
				else
				{
					if(!results.get(0).getPassword().equals(oldpassword))
					{
						resp.getWriter().print("You have entered the wrong password for your account.<br>");
						resp.getWriter().print("No changes will be made to your password<br>");
					}
					else if(!newpassword1.equals(newpassword2))
					{
						resp.getWriter().print("Your new password doesn't match with your confirmation of the new password.<br>");
					}
					else
					{
						results.get(0).setPassword(newpassword1);
					}
				}
				if(birthdate.equalsIgnoreCase("")||birthdate.equalsIgnoreCase("")||birthdate.length()!=10)
				{
					resp.getWriter().print("You have entered an invalid birth date.<br>");
					resp.getWriter().print("No changes will be made to your birth date.<br>");
				}
				else
				{
					results.get(0).setBirthdate(birthdate);
				}
				if(profile_img.equalsIgnoreCase("")||profile_img.equalsIgnoreCase(""))
				{
					resp.getWriter().print("You have not entered a profile image.<br>");
					resp.getWriter().print("No changes will be made to your profile image.<br>");
				}
				else
				{
					results.get(0).setProfile_img(profile_img);
				}
				resp.setHeader("Refresh", "5; URL=editprofile.jsp");
				resp.getWriter().print("<br>You will return to Edit Profile in 5 seconds.<br>");
				resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
				resp.getWriter().print("<a href=\"editprofile.jsp\">Return to Edit Profile</a><br>");
				
			}
		}
	
		
	
	
}
