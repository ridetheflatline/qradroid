<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qra.project.Conference" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create QR Codes</title>
</head>
<body>
	<div class="ui-widget">

	<%
	out.println("<h2>Print an ID for a conference you are attending:</h2></br>");
	ArrayList<Conference> attData=(ArrayList<Conference>) request.getAttribute("attqrarray");
	   for (int i=0;i<attData.size();i++){
		   out.println("<li><a href='/createmyqr?conf_id="+attData.get(i).getConf_code()+"'>"+attData.get(i).getConf_name()+"</a></li>");
	   }
	out.println("<h2>Look at conferences that you are hosting:</h2></br>");
	ArrayList<Conference> hostData=(ArrayList<Conference>) request.getAttribute("hostqrarray");
	for (int i=0;i<hostData.size();i++){
			out.println(""+hostData.get(i).getConf_name());
		   out.println("<li><a href='/createbatchqr?conf_id="+hostData.get(i).getConf_code()+"'>"+"Batch Create QR Codes"+"</a></li>");
		   out.println("<li><a href='/calcattendance?conf_id="+hostData.get(i).getConf_code()+"'>"+"View Attendnace Records"+"</a></li>");
	   }
	
	%>
	</div>

</body>

<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/index.js'/>">
	
</script>
</html>