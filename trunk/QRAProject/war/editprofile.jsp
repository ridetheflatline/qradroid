<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.List"%>
<%@ page import = "javax.jdo.PersistenceManager"%>
<%@ page import = "javax.jdo.Query"%>
<%@ page import="com.qra.project.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>

</head>
<jsp:include page="header.jsp" />

<%
	User user = (User)session.getAttribute("user");
%>
<body>
	<div class="ui-widget">
		<form name="edit" method="get" action="/edit" style="text-align: left">
			<legend>
				<h2>Edit Profile</h2>
			</legend>
			
			<p>	
				Profile Image: <br>
				<img src="/profileimg?profile_img_key=<%= user.getProfile_img() %>">
			</p>

			<table>
				<tr>
					<td><label id="Label1">First Name</label></td>
					<td><input type="text" name="first_name" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>

				<tr>
					<td><label id="Label1">Middle Name</label></td>
					<td><input type="text" name="middle_name" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>

				<tr>
					<td><label id="Label1">Last Name</label></td>
					<td><input type="text" name="last_name" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>
				
				<tr>
						<td><label id="Label1">Old Password</label></td>
						<td><input type="password" name="oldpassword" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>
				<tr>
						<td><label id="Label1">New Password</label></td>
						<td><input type="password" name="newpassword1" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>
				
				<tr>
						<td><label id="Label1">Confirm New Password</label></td>
						<td><input type="password" name="newpassword2" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>

				<tr>
					<td><label>Birthday</label></td>
					<td><input type="text" name="birthdate" id="datepicker" class="ui-widget ui-widget-content ui-corner-all"/></td>
				</tr>

				<tr>
					<td><label id="Label1">Profile Image</label></td>
					<td><input type="text" name="profile_img" /></td>
				</tr>

			</table>



			<div id="submit">
				<input type="submit" value="Update Information" id="submitBtn" />
			</div>

		</form>


	</div>

</body>
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/editprofile.js'/>">
</script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
</html>

