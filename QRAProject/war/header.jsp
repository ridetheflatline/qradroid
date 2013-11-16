<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<% String myTitle = (String)request.getAttribute("title"); %>

<!DOCTYPE html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<link rel="stylesheet" type="text/css" href="/css/default.css"
	media="screen" />
	</head>
	<header>
<ul id="nav">
	<%
		Cookie[] cookies = request.getCookies();
		String cookieName = "userIDCookie";
		String cookieValue = "";
		out.println("<li><a href=\"index.jsp\">Home</a></li>");
		if (cookies != null)//if there are cookies
		{
			for (int i = 0; i < cookies.length; i++) //search through the cookies
			{
				Cookie cookie = cookies[i]; //take one cookie

				if (cookieName.equals(cookie.getName()))// is it the cookie we are looking for?
				{
					cookieValue = cookie.getValue(); //cookieValue is username
					if (cookieValue != null) //if it has a username
					{
						out.println("<li><a href=\"/editprofile.jsp\">Edit Profile</a></li>");
						out.println("<li><a href=\"/createconference\">Create Conference</a></li>");
						out.println("<li><a href=\"/findconferences\">Search Conference</a></li>");
						out.println("<li><a href=\"LINKHERE\">NAMEHERE</a></li>");
						out.println("<li><a href=\"/logout\">Log Out</a></li>");
						break;
					} 
				}
				else
				{
					if(i == cookies.length-1 && cookieValue != null) //If it's the last cookie and the userIdCookie is still null
					{
					 out.println("<li><a href=\"register.jsp\">Register</a></li>");
			  		 out.println("<li><a href=\"login.jsp\">Log In</a></li>");
					}
				}
			}
		}
		else {
			out.println("<li><a href=\"register.jsp\">Register</a></li>");
			out.println("<li><a href=\"login.jsp\">Log In</a></li>");
		}
	%>
</ul>
</header>
	
<body>