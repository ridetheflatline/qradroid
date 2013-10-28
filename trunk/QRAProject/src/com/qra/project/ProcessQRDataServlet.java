package com.qra.project;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ProcessQRDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String checkedIn;
		String confCode = req.getParameter("conf_code");
		String userID = req.getParameter("user_id");
		String date = req.getParameter("timestamp");
		Date timestamp=null;
		
		try {
			timestamp = new SimpleDateFormat("MM/dd/yyyyHH:mm:ssz").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Attendance_Records a = new Attendance_Records(confCode,userID,timestamp);
		
		try{
			pm.makePersistent(a);
		}
		finally{
			pm.close();
		}
		
		checkedIn="{\"checkedIn\":\"true\"}";

		resp.setContentType("text/plain");
		resp.getWriter().print(checkedIn); //output for debug
		//resp.getWriter().print("test "+confCode+" "+userID+" "+date); //output for debug
	}
}