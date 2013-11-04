<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create Conference</title>
</head>

<body>
<%
   Cookie[] cookies = request.getCookies();
   String cookieName = "userIDCookie";
   String cookieValue = "";
   // Get an array of Cookies associated with this domain
   if( cookies != null ){
      for (int i = 0; i < cookies.length; i++)
      {
    	 Cookie cookie = cookies[i];
    	 if(cookieName.equals(cookie.getName()))
    	 {
    		 cookieValue = cookie.getValue();
	         out.print("Hello " + cookieValue + "!");
    	 }
      }
  }
   else{
	  out.println("You are not logged in.");
	  out.println("<br> <a href=\"login.jsp\">Go to Log In page.</a>");
  }
%>



<h2>Create Conference</h2>
<div>

<form method="post" action="">
<label>Conference Name: </label>
<br>
<input type="text" name="conf_name">
<br>
<label>Conference Description: </label>
<br>
<input type="text" name="conf_description">
<br>
<h3>Add Sessions (TODO)</h3>
<input type="submit" name="submit" value="Create Conference"/>
</form>
</div>
</body>

<script type="text/javascript" src="<c:url value='/js/test.js'/>"> </script>

</html>