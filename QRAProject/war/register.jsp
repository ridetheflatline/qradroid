<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR Attendance - Registration</title>
</head>

<jsp:include page="header.jsp" />

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<body>
	<div class="ui-widget">
		<form method="post" action="<%= blobstoreService.createUploadUrl("/register") %>" 
			style="text-align: left" enctype="multipart/form-data" id="registration_form">
				<legend>
					<h2>Registration</h2>
				</legend>
				<p id="errors"></p>
				<table>
					<tr>
						<td><label id="Label1">Username</label></td>
						<td><input type="text" name="username" id="username" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label id="Label1">Email Address</label></td>
						<td><input type="text" name="email" id="email" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label id="Label1">Password</label></td>
						<td><input type="password" name="password" id="password" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label id="Label1">First Name</label></td>
						<td><input type="text" name="first_name" id="first_name" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label id="Label1">Middle Name</label></td>
						<td><input type="text" name="middle_name" id="middle_name" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label id="Label1">Last Name</label></td>
						<td><input type="text" name="last_name" id="last_name" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label>Birthday</label></td>
						<td><input type="text" name="birthdate" id="datepicker" class="ui-widget ui-widget-content ui-corner-all"/></td>
					</tr>

					<tr>
						<td><label id="Label1">Profile Image</label></td>
						<td><input type="file" name="profile_img" ></td>
					</tr>

				</table>
				<div id="submit">
					<input type="submit" value="Register" id="submitBtn" />
				</div>
		</form>


	</div>

</body>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>">	
</script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/register.js'/>">	</script>

</html>