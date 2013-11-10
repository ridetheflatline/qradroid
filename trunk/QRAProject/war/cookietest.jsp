<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cookie Testing</title>
</head>
<body>

	<%
		Cookie[] cookies = request.getCookies();
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
					 out.println("You're not logged in.<br>");
					}
				}
			}
		}
		else {
			out.println("<br>You're not logged in.<br>");
		}
		%>
		
</body>
</html>