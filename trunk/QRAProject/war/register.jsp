<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR Attendance - Registration</title>
</head>
<body>
<div class="ui-widget">
			<form name = "register" method="get" action="/register" style="text-align:left">
			<fieldset name="registration">
			<legend><h2>Registration</h2></legend>
			
			
			<table>

			<tr>
			<td> <label id="Label1" >Username</label>   </td>
			<td><input type="text" name="username"/>	</td>
			</tr>
			
			<tr>
			<td><label id="Label1" >Email Address</label>  </td>
			<td><input type ="text" name="email" />	</td>
			</tr>	
				
			<tr>
			<td><label id="Label1" >Password</label> </td>
			<td><input type ="password" name="password" /></td>
			</tr>
			
			<tr>
			<td> <label id="Label1" >First Name</label> </td>
			<td><input type ="text" name="first_name" />	</td>
			</tr>
			
			<tr>
			<td> <label id="Label1" >Middle Name</label> </td>
			<td><input type ="text" name="middle_name" />	</td>
			</tr>
			
			<tr>
			<td> <label id="Label1" >Last Name</label>   </td>
			<td><input type="text" name="last_name" />	</td>
			</tr>
			
			<tr>
			<td> <label>Birthday</label>   </td>
			<td><input type="text" name="birthdate" id="datepicker" /></td>
			</tr>
			
			<tr>
			<td> <label id="Label1" >Profile Image</label>   </td>
			<td><input type="text" name="profile_img" />	</td>
			</tr>
			
			</table>
				
		
		
			<div id="submit">
			<input type="submit" value="Register" id="submitBtn" />
			</div>
			
			</form>
			
			<br>
			
			<form name = "home" action="index.jsp" style="text-align:left">
			<input type="submit" value="Return to Home" id="homeBtn" />
			</form>
	
</div>

</body>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/register.js'/>"> </script>
 <script>
 $(function() {
	 $( "#datepicker" ).datepicker();
	 });
</script>
</html>