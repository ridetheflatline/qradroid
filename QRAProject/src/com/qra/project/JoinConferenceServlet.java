package com.qra.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinConferenceServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(JoinConferenceServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException 
	{
		
		if(CookieSessionCheck.check(req, res)!=null){
			
			String conf_code = req.getParameter("conf_code");
			String user_id = req.getParameter("user_id");
			
			String page_output = req.getParameter("page_output");
			
			//The generated key string
			log.info("conf_code: " + conf_code);
			//The generated key string
			log.info("user_id: "+ user_id);
			
			res.setContentType("text/plain");
			
			if(page_output == null || page_output.equals("false")){
				if(conf_code != null && user_id != null && 
						!conf_code.equals("") && !user_id.equals("")){
					ConferenceAttendee ca = new ConferenceAttendee(conf_code, user_id);
					//Make need to add check to make sure the given conf code and 
					//user id actually exists
					PersistenceManager pm = PMF.get().getPersistenceManager();
					Object o = null;
					try{
						 o = pm.makePersistent(ca);
					}
					finally{
						pm.close();
						if(o != null){
							res.setStatus(HttpServletResponse.SC_OK);
							res.getWriter().println("Success");
						}
					}
				}else{
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					res.getWriter().println("Error");
				}
			}
			else{
				if(conf_code != null && user_id != null && 
						!conf_code.equals("") && !user_id.equals("")){
					ConferenceAttendee ca = new ConferenceAttendee(conf_code, user_id);
					//Make need to add check to make sure the given conf code and 
					//user id actually exists
					PersistenceManager pm = PMF.get().getPersistenceManager();
					Object o = null;
					boolean joinSucessful = false;
					try{
						 o = pm.makePersistent(ca);
					}
					finally{
						pm.close();
						if(o != null){
							joinSucessful = true;
						}
					}
					
					if(joinSucessful){
						req.setAttribute("resultMsg", "Successfully Joined Conference!");
					}
					else{
						req.setAttribute("resultMsg", "Error Joining Conference.");
					}
					
					String url = "/joinconfresult.jsp";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
					try {
						dispatcher.forward(req, res);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.info("error dispatching to jsp page from JoinConferenceServlet");
					}
					
				}else{
					res.sendRedirect("/login.jsp");
				}
			}
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException 
	{
		
	}

}
