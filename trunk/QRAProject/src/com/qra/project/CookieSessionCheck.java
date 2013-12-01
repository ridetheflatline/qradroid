package com.qra.project;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/***
 * Class that will retrieve the cookie username from a request.
 * @author Joel Friberg
 *
 */
public class CookieSessionCheck {

	public static String check(HttpServletRequest req, HttpServletResponse resp){
		
		Cookie[] cookies = req.getCookies();
		String cookieName = "userIDCookie";
		String cookieValue = "";
		HttpSession session = req.getSession();
		String userIdFromSess = (String) session.getAttribute("userSess");
		
		
		if (cookies != null)//if there are cookies
		{
			for (int i = 0; i < cookies.length; i++) //search through the cookies
			{
				Cookie cookie = cookies[i]; //take one cookie

				if (cookieName.equals(cookie.getName()))// is it the cookie we are looking for?
				{
					cookieValue = cookie.getValue(); //cookieValue is username 
					if(userIdFromSess==null){
						session.setAttribute("userSess", cookieValue);
					}
				}
			}
		}
		if(userIdFromSess!=null&&cookieValue==""){
			Cookie userKeyIdCookie = 
					new Cookie("userIDCookie", userIdFromSess);
			cookieValue=userIdFromSess;
			//Set for 10 minutes
			userKeyIdCookie.setMaxAge(60*10);
			userKeyIdCookie.setPath("/");
			resp.addCookie(userKeyIdCookie);
		}
		if(cookieValue=="") {
			try {
				resp.sendRedirect("/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return cookieValue;
	}
}
