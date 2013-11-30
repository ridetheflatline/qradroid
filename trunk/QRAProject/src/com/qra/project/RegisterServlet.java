package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class RegisterServlet extends HttpServlet {
	
    private static final Logger log = Logger.getLogger(CheckinAttendentServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{	
		resp.sendRedirect("/register.jsp");
	}//doget
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
				//Sample Request:
				//http://localhost:8888/userInsertTest?first_name=Danny&middle_name=Joe&last_name=Johnson&
				//email=fred.johnson@gmail.com&username=fjohnson&password=johnson
				//&profile_img=www.url.com&birthdate=01171990
				
				String first_name = req.getParameter("first_name");
				String middle_name = req.getParameter("middle_name");
				String last_name = req.getParameter("last_name");
				String email = req.getParameter("email");
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String birthdate = req.getParameter("birthdate");
				
				Map<String, List<BlobKey>> files_sent = BlobstoreServiceFactory.getBlobstoreService().getUploads(req);
			    BlobKey file_uploaded_key = files_sent.get("profile_img").get(0);
			    String profile_img = file_uploaded_key.getKeyString();
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				resp.setContentType("text/HTML");
				
				if(first_name == null || middle_name == null || last_name == null || email == null || 
						username == null || password == null || profile_img == null || birthdate == null){
					
					log.info("first_name: "+first_name);
					log.info("middle_name: "+middle_name);
					log.info("last_name: "+last_name);
					log.info("email: "+email);
					log.info("username: "+username);
					log.info("password: "+password);
					log.info("profile_img: "+profile_img);
					log.info("birthdate: "+birthdate);
					
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "A Parameter is null.");
				}
				else if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("") || first_name.equalsIgnoreCase("")
						|| middle_name.equalsIgnoreCase("") || last_name.equalsIgnoreCase("") || email.equalsIgnoreCase("") ||
						profile_img.equalsIgnoreCase("") || birthdate.equalsIgnoreCase("") || password.length()<5 || birthdate.length()!=10){
					resp.setHeader("Refresh", "5; URL=register.jsp");
					resp.getWriter().print("You must fill in all the information.<br>");
					if(username.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a username.<br>");
					}
					if(password.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a password.<br>");
					}
					if(first_name.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a first name.<br>");
					}
					if(middle_name.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a middle name. If you don't have one, please enter N/A.<br>");
					}
					if(last_name.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a last name.<br>");
					}
					if(email.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered an email.<br>");
					}
					if(profile_img.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a profile image link. If you would not like one, please enter N/A<br>");
					}
					if(birthdate.equalsIgnoreCase(""))
					{
						resp.getWriter().print("You have not entered a birth date.<br>");
					}
					if(password.length()<5)
					{
						resp.getWriter().print("Please enter a password at least 5 characters long.<br>");
					}
					if(birthdate.length()!=10)
					{
						resp.getWriter().print("Please enter a valid date in the correct format.<br>");
					}
					resp.getWriter().print("<br>You will return to Register in 5 seconds.<br>");
					resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
					resp.getWriter().print("<br> <a href=\"register.jsp\">Return to Registration</a>");
				}
				else{
					
					Query q = pm.newQuery(User.class, "username == '"  + username + "'");
					List<User> results = (List<User>) q.execute();
					Query q2 = pm.newQuery(User.class, "email == '"  + email + "'");
					List<User> results2 = (List<User>) q2.execute();
					//Check for username that already exists
					if(!(results.size() == 0)){
						resp.setHeader("Refresh", "5; URL=register.jsp");
						resp.getWriter().print("That username already exists");
						resp.getWriter().print("<br>You will return to Register in 5 seconds.<br>");
						resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
						resp.getWriter().print("<br> <a href=\"register.jsp\">Return to Register</a>");
					}
					//Check for if the email is already used
					else if(!(results2.size() == 0)){
						resp.setHeader("Refresh", "5; URL=register.jsp");
						resp.getWriter().print("That email is already used");
						resp.getWriter().print("<br>You will return to Register in 5 seconds.<br>");
						resp.getWriter().print("If you are not redirect, please click on the following link.<br>");
						resp.getWriter().print("<br> <a href=\"register.jsp\">Return to Register</a>");
					}
					else{
					//resp.getWriter().print(results.toString());
					
					User u = new User
							(first_name, middle_name, last_name, 
									email, username, password, profile_img, birthdate);
					Object o = null;
					try{
						 o = pm.makePersistent(u);
					}
					finally{
						pm.close();
						
						if(o != null){
							resp.setStatus(HttpServletResponse.SC_OK);
							//resp.setContentType("text/plain");
							//resp.getWriter().println("Success");
							resp.sendRedirect("/index");
							}//if
						}//finally
					}//else
				}//else
				
				
	}
	
}
