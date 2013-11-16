package com.qra.project;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/***
 * Class that will retrieve the cookie username from a request.
 * @author Joel Friberg
 *
 */
public class CookieCheck {

	public static String check(HttpServletRequest req, HttpServletResponse resp){
		
		Cookie[] cookies = req.getCookies();
		String cookieName = "userIDCookie";
		String cookieValue = "";
		if (cookies != null)//if there are cookies
		{
			for (int i = 0; i < cookies.length; i++) //search through the cookies
			{
				Cookie cookie = cookies[i]; //take one cookie

				if (cookieName.equals(cookie.getName()))// is it the cookie we are looking for?
				{
					cookieValue = cookie.getValue(); //cookieValue is username 
				}
				else
				{
					if(i == cookies.length-1 && cookieValue != null) //If it's the last cookie and the userIdCookie is still null
					{
						try {
							resp.getWriter().print("You're not logged in.<br>");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		else {
			try {
				resp.getWriter().print("<br>You're not logged in.<br>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cookieValue;
	}
}
