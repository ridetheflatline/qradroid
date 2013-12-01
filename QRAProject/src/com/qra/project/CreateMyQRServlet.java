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
 * Servlet that creates ID card data for individual attendees. 
 * @author Joel Friberg
 *
 */
public class CreateMyQRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String userName=CookieSessionCheck.check(req, resp);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String confID = req.getParameter("conf_id");
		String fullName = null;
		String userID = null;
		Conference tempConf=null;
		ArrayList<QRData> qrData = new ArrayList<QRData>();
		
		if(userName!=null){
			Query q = pm.newQuery(User.class, "username == '" + userName+ "'");
	
			List<User> results = (List<User>) q.execute();
			
			fullName=results.get(0).getFirst_name()+" "+results.get(0).getLast_name();
			userID=results.get(0).getID();
			tempConf=pm.getObjectById(Conference.class, confID);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/YYYY");
			String dates=sdf.format(tempConf.getStartTime())+"-"+sdf.format(tempConf.getEndTime());
			
			qrData.add(new QRData(tempConf.getConf_name(),fullName,userID,confID,dates,null));
			
			
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
}
