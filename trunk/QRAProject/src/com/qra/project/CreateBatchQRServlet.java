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
 * Servlet that creates ID card data for all attendees of a conference.
 * @author Joel Friberg
 *
 */
public class CreateBatchQRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String confID = req.getParameter("conf_id");
		String fullName = null;
		String userID = null;
		Conference tempConf=null;
		User tempUser=null;
		ArrayList<QRData> qrData = new ArrayList<QRData>();
		
		
		tempConf=pm.getObjectById(Conference.class, confID);
		
		Query q = pm.newQuery(ConferenceAttendee.class, "conf_code == '" +confID+ "'");
		List<ConferenceAttendee> attResults = (List<ConferenceAttendee>) q.execute();
		
		
		for(int j=0;j<attResults.size();j++){
			userID=attResults.get(j).getUser_id();
			tempUser=pm.getObjectById(User.class, userID);
			fullName=tempUser.getFirst_name()+" "+tempUser.getLast_name();
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
			String dates=sdf.format(tempConf.getStartTime())+"-"+sdf.format(tempConf.getEndTime());
			
			qrData.add(new QRData(tempConf.getConf_name(),fullName,userID,confID,dates));
		}
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
