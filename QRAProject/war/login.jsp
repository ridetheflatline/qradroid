<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR Attendance - Log In</title>
<link rel="stylesheet" type="text/css" href="/css/default.css"
	media="screen" />
</head>


<jsp:include page="header.jsp" />


<body>
	<div class="ui-widget">
		<form method="get" style="text-align: left" action="/login">
				<Legend><h2>Log In</h2></Legend>
				<table>
					<tr>
						<td><label id="Label1">Username</label></td>
						<td><input name="username" type="text" /></td>
					</tr>

					<tr>
						<td><label id="Label1">Password</label></td>
						<td><input type="password" name="password" /></td>
					</tr>


				</table>
			<div id="submit" class="ui-widget">
				<input type="submit" value="Log In" id="loginBtn" />
			</div>
		</form>

		<br>


	</div>
</body>

<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/login.js'/>">
	
</script>
</html>