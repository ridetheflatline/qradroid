<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qra.project.AttCalc" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Track Attendance</title>
</head>

<body>

<div class="ui-widget">
<%
   
   ArrayList<AttCalc> attData=(ArrayList<AttCalc>) request.getAttribute("attdata");
   

	out.print("<table border='1'>");
   for (int i=0;i<attData.size();i++){
	   out.print("<tr><td>"+attData.get(i).getName()+"</td>");
	   String[] tempAtt=attData.get(i).getAtt();
	   for(int j=0;j<attData.get(i).getSize();j++){
		   out.print("<td>"+tempAtt[j]+"</td>");
	   }
	   out.print("</tr>");
   }
   out.print("</table>");
   
   
%>
</div>

</body>



<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />

</html>