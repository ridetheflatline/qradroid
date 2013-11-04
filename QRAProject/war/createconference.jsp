<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>

<body>
<h2>Create Conference</h2>
<div id="conferenceFormDiv">

<form method="post" action="">
<label>Conference Name: </label>
<br>
<input type="text" name="conf_name">
<br>
<label>Conference Description: </label>
<br>
<input type="text" name="conf_description">
<br>
<h3>Add Sessions (TODO)</h3>
<input type="submit" name="submit" value="Create Conference"/>
</form>
</div>
</body>

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/createconference.js'/>"> </script>

</html>