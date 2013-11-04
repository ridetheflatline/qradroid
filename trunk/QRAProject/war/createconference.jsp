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
	<button type="button" id="createSessionBtn">Create Session</button>
	<div id="sessionFields">
		<h2>Create Sessions</h2>
		<textarea rows="10" cols="50"></textarea>
	</div>
	
	<br>
	<input type="submit" name="submit" value="Create Conference"/>
</form>

</div>
</body>

<div id="dialog-form" title="Create new user">
  <p class="validateTips">All form fields are required.</p>
  <form>
  <fieldset>
    <label for="name">Session Description</label>
    <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
    <label for="email">Email</label>
    <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
    <label for="password">Password</label>
    <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
  </fieldset>
  </form>
</div>

<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/createconference.js'/>"> </script>
</html>