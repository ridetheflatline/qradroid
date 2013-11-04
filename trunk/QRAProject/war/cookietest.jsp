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
   //Copy this whole thing to the body of the html or elsewhere
   
   Cookie[] cookies = request.getCookies();
   String cookieName = "userIDCookie";
   String cookieValue = "";
   // Get an array of cookies associated with this domain
   if( cookies != null ){
      out.println("<h2> Found Cookies Name and Value</h2>");
      for (int i = 0; i < cookies.length; i++)
      {
    	 Cookie cookie = cookies[i];
    	 if(cookieName.equals(cookie.getName()))
    	 {
    		 cookieValue = cookie.getValue(); //cookieValue is username
	         out.print("Name : " + cookie.getName( ) + ",  "); //delete this
	         out.print("Value: " + cookie.getValue( )+" <br/>"); //delete this
    	 }
      }
  }else{
      out.println("You are not logged in.");
      out.println("<br> <a href=\"login.jsp\">Go to Log In page.</a>");
  }
%>

</body>
</html>