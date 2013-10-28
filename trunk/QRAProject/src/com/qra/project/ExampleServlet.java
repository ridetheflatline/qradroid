package com.qra.project;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;


@SuppressWarnings("serial")
public class ExampleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String example = "{\"valid\":\"true\"}";
		String p = req.getParameter("p1");
		
		/*Employee e = new Employee("Alfred", "Smith");		
		try{
			pm.makePersistent(e);
		}
		finally{
			pm.close();
		}*/
		
		resp.setContentType("text/plain");
		resp.getWriter().print(example);
	}
}
