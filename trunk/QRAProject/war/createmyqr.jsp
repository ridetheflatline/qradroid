<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create Conference</title>
</head>

<body>
<%

/*
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
   ArrayList<QRData> qrData=(ArrayList<QRData>) request.getAttribute("qrarray");
   
   for (int i=0;i<qrData.size();i++){
	   out.println("<table border='1'><tr><td><h3>"+qrData.get(i).getConfName()+"</h3></br>Name: "+qrData.get(i).getAttName()+"</br>ID: "+qrData.get(i).getUserID()+"</br>Dates: "+qrData.get(i).getDates()+"</td><td><img src=https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl="+qrData.get(i).getConfID()+","+qrData.get(i).getUserID()+"</td></tr></table>");
   }
   
  */
   
   
%>

</body>



<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />

</html>