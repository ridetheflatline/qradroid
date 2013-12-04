package com.qra.project;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.mortbay.log.Log;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

public class EditProfileServlet extends HttpServlet {
	private static final Logger log = Logger
			.getLogger(EditProfileServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String userName=CookieSessionCheck.check(req, resp);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpSession session = req.getSession();
		
		if(userName!=null){
			Query q = pm.newQuery(User.class, "username == '" + userName + "'");
			List<User> results = (List<User>) q.execute();
			session.setAttribute("user", results.get(0));

			resp.sendRedirect("editprofile.jsp");
		}
//		HttpSession session = req.getSession();
//		String userIdFromSess = (String) session.getAttribute("userSess");
//		String userIdFromCookie = CookieCheck.check(req, resp);
//
//
//		log.info("userIdFromCookie: " + userIdFromCookie);
//		log.info("userIdFromSess: " + userIdFromSess);
//
//		if (userIdFromSess == null && userIdFromCookie == null) {
//			resp.sendRedirect("/index");
//		} 
//		else {
//			User myUser = GetUserFromId.getUser(userIdFromSess,
//					userIdFromCookie);
//			session.setAttribute("user", myUser);
			
//		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/HTML");
		PersistenceManager pm = PMF.get().getPersistenceManager();

		String first_name = req.getParameter("first_name");
		String middle_name = req.getParameter("middle_name");
		String last_name = req.getParameter("last_name");
		String birthdate = req.getParameter("birthdate");
		String profile_img = req.getParameter("profile_img");
		String oldpassword = req.getParameter("oldpassword");
		String newpassword1 = req.getParameter("newpassword1");
		String newpassword2 = req.getParameter("newpassword2");
		String email = req.getParameter("email");
//		String username = req.getParameter("username");
		
		
		Map<String, List<BlobKey>> files_sent = BlobstoreServiceFactory.getBlobstoreService().getUploads(req);
		//int numImgFiles = files_sent.get("profile_img").size();
		if(files_sent.get("profile_img") != null){
		    BlobKey file_uploaded_key = files_sent.get("profile_img").get(0);
		    
//		    Image myImg = ImagesServiceFactory.makeImageFromBlob(file_uploaded_key);
//		    int myImgByteSize = myImg.getImageData().length;
//		    if(myImgByteSize == 0)
//		    	profile_img = "";
//		    else
		    BlobInfoFactory blobif=new BlobInfoFactory();
		    BlobInfo file = blobif.loadBlobInfo(file_uploaded_key);
		    String fileName=file.getFilename();
		    
//		    resp.getWriter().print("Filename? "+fileName);
		    if(fileName!=null&&!fileName.equals(""))
		    	profile_img = file_uploaded_key.getKeyString();
		    else
		    	profile_img = "";
//		    resp.getWriter().print("profile? "+profile_img);
		}
		else{
			profile_img = "";
		}
	    
	    
		String cookieValue = "";
		cookieValue = CookieSessionCheck.check(req, resp);


//		if (first_name == null || middle_name == null || last_name == null
//				|| profile_img == null || birthdate == null) {
////
//			log.info("first_name: " + first_name);
//			log.info("middle_name: " + middle_name);
//			log.info("last_name: " + last_name);
//			log.info("profile_img: " + profile_img);
//			log.info("birthdate: " + birthdate);
////
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
//					"A Parameter is null.");
//		} 
//		else {
			Query q = pm.newQuery(User.class, "username == '" + cookieValue + "'");
			List<User> results = (List<User>) q.execute();
			User u = results.get(0);
			
			log.info("first_name: " + first_name);
			log.info("middle_name: " + middle_name);
			log.info("last_name: " + last_name);
			log.info("profile_img: " + profile_img);
			log.info("birthdate: " + birthdate);
//			log.info(username);

			if (first_name.equalsIgnoreCase("")
					|| first_name == null) {
			} else {
				u.setFirst_name(first_name);
			}
			if (middle_name.equalsIgnoreCase("")
					|| middle_name == null) {
			} 
			else {
				u.setMiddle_name(middle_name);
			}
			if (last_name.equalsIgnoreCase("")
					|| last_name == null) {
			} 
			else {
				u.setLast_name(last_name);
			}
			if (newpassword1.equalsIgnoreCase("")
					|| newpassword1 == null) {
			} 
			else {
				BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
				if (!passwordEncryptor.checkPassword(oldpassword, u.getPassword())) {
				} 
				else if (!newpassword1.equals(newpassword2)) {
				} 
				else {
					String encryptedPassword = passwordEncryptor.encryptPassword(newpassword1);
					u.setPassword(encryptedPassword);
				}
			}
			if (birthdate.equalsIgnoreCase("")
					|| birthdate == null
					|| birthdate.length() != 10) {
			} 
			else {
				u.setBirthdate(birthdate);
			}
			
			if (profile_img.equalsIgnoreCase("")
					|| profile_img == null) {
				log.info("hit if");
				String oldProfileImg = u.getProfile_img();
				u.setProfile_img(oldProfileImg);
			} 
			else {
				log.info("hit else");
				u.setProfile_img(profile_img);
			}
			
//			Query q3 = pm.newQuery(User.class, "username == '"  + username + "'");
//			List<User> results3 = (List<User>) q3.execute();
			Query q2 = pm.newQuery(User.class, "email == '"  + email + "'");
			List<User> results2 = (List<User>) q2.execute();
//			log.info("results3 "+results3.size());
			//Check for username that already exists
//			if(results3.size() == 0){
//				results.get(0).setUsername(username);
//			}
			//Check for if the email is already used
			if(results2.size() == 0){
				u.setEmail(email);
			}
			
			Object o = null;
			try {
				o = pm.makePersistent(u);
			} finally {
				pm.close();
	
				if (o != null) {
					resp.setStatus(HttpServletResponse.SC_OK);
					// resp.setContentType("text/plain");
					// resp.getWriter().println("Success");
				}// if
			}// finally
			resp.sendRedirect("/index");
		}
//	}

}
