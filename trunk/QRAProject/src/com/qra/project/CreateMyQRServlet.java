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
import javax.servlet.http.HttpSession;

public class CreateMyQRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String confID = req.getParameter("conf_id");
		String fullName = null;
		String userID = null;
		HttpSession session = req.getSession();
//		String userName=(String)session.getAttribute("userSess");
		String userName=CookieCheck.check(req, resp);
		Conference tempConf=null;
		ArrayList<QRData> qrData = new ArrayList<QRData>();
		
		if(userName!=null){
			Query q = pm.newQuery(User.class, "username == '" + userName+ "'");
	
			List<User> results = (List<User>) q.execute();
			
			fullName=results.get(0).getFirst_name()+" "+results.get(0).getLast_name();
			userID=results.get(0).getID();
			tempConf=pm.getObjectById(Conference.class, confID);
			qrData.add(new QRData(tempConf.getConf_name(),fullName,userID,confID,"test"));
			
			
			req.setAttribute("qrarray", qrData);
			String url = "/createmyqr.jsp";
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		}
		
	}
}
