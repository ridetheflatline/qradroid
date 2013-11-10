<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR Attendance</title>
<link rel="stylesheet" type="text/css" href="/css/default.css" media="screen" />
</head>
<header>
<ul id="nav">
	<%  
   Cookie[] cookies = request.getCookies();
   String cookieName = "userIDCookie";
   String cookieValue = "";
   out.println("<li><a href=\"index.jsp\">Home</a></li>");
   if( cookies != null ){
	   out.println("<li><a href=\"/editprofile.jsp\">Edit Profile</a></li>");
	   out.println("<li><a href=\"createconference.jsp\">Create Conference</a></li>");
	   out.println("<li><a href=\"/logout\">Log Out</a></li>");
   }
   else{
	   out.println("<li><a href=\"register.jsp\">Register</a></li>");
	   out.println("<li><a href=\"login.jsp\">Log In</a></li>");
   }
      
    
%>
</ul>
</header>
<body>
<div class="ui-widget">
<h1>QR Attendance</h1>
<p>Welcome to QR attendance.</p>

</div>

</body>

<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/index.js'/>"> </script>
</html>