<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
            %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QR Attendance - Log In</title>
</head>
<body>
<div>
	<form method="get" style="text-align:left" action="/login">
	<fieldset name = "log in">
	<Legend>Log In</Legend>
	<table>
	<tr>
			<td> <label id="Label1" >Username</label>   </td>
			<td><input name="username" type="text" />	</td>
			</tr>

			<tr>
			<td> <label id="Label1" >Password</label> </td>
			<td><input type ="password" name="password" />	</td>
			</tr>
	
	
	</table>
	</fieldset>
	
	<div id="submit">
			<input type="submit" value="Log In" id="submit-button" />
			</div>
			</form>
			
			<br>
			
			<a href="index.html">Home</a>
			

</div>
</body>
</html>