<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Find Conferences</title>
</head>
<body>

<h3>Search for Conferences</h3>

<form>
<label for="search">Search</label>
<input type="text" name="search" id="search"></input>

<label for="state">State</label>
<input type="text" name="state" id="state"></input>

<label for="city">City</label>
<input type="text" name="city" id="city"></input>

<input type="submit" id="submit" name="submit" value="Submit">
</form>

</body>

<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/createconference.js'/>"> </script>
</html>