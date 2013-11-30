package com.qra.project;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * This servlet creates empty user accounts, signs them up for the conference, and prints QR ID cards for them.
 * The user can login using their username as username and password and change all the other info about themself.
 * @author Joel Friberg
 *
 */
public class CreateBlankQRServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		int blankNum = Integer.parseInt(req.getParameter("blanknum"));
		String confID = req.getParameter("conf_id");
		String fullName = null;
		String userID = null;
		Conference tempConf=null;
		ArrayList<QRData> qrData = new ArrayList<QRData>();
		tempConf=pm.getObjectById(Conference.class, confID);
		String username = req.getParameter("username");
		
		//create users, sign them up for the conference, and print id cards
		for(int i=0; i<blankNum; i++){
			//check if user already exists
			username=tempConf.getConf_name()+" Attendee "+(i+1);
			Query q = pm.newQuery(User.class, "username == '"  + username + "'");
			List<User> results = (List<User>) q.execute();
			if((results.size() == 0)){
				//if user does not exist, create new user object and store in the datastore
				User u = new User(null, null, null, null, username, username, null, null);
				try{
					 pm.makePersistent(u);
				}
				finally{}
				//retrieve new user ID
				Query q2 = pm.newQuery(User.class, "username == '"  + username + "'");
				List<User> uResults = (List<User>) q2.execute();
				while(!(uResults.size()>0)){
					uResults = (List<User>) q2.execute();
				}				
				userID=uResults.get(0).getID();
				//register user for the conference
				ConferenceAttendee ca = new ConferenceAttendee(confID, userID);
				try{
					 pm.makePersistent(ca);
				}
				finally{}
				//Create QR data to be displayed
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
				String dates=sdf.format(tempConf.getStartTime())+"-"+sdf.format(tempConf.getEndTime());
				fullName="____________________";
				qrData.add(new QRData(tempConf.getConf_name(),fullName,userID,confID,dates,username));
			}
			else{//if the user does exist, increment loop counter so higher # users will be created
				blankNum++;
			}
		}
		//send data to jsp file for ID generation
		req.setAttribute("qrarray", qrData);
		String url = "/createmyqr.jsp";
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(url);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
