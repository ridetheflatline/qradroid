<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR Attendance - Registration</title>
</head>
<body>
<div>
			<form name = "register" method="get" action="/register" style="text-align:left">
			<fieldset name="registration">
			<legend>Registration </legend>
			
			
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
			<td> <label id="Label1" >Birthday</label>   </td>
			<td><input type="text" name="birthdate" />	</td>
			</tr>
			
			<tr>
			<td> <label id="Label1" >Profile Image</label>   </td>
			<td><input type="text" name="profile_img" />	</td>
			</tr>
			
			</table>
					
		
			<div id="submit">
			<input type="submit" value="Register" id="submit-button" />
			</div>
			
			</form>
			
			<br>
			<a href="index.html">Home</a>
</div>

</body>
</html>