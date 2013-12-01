<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Find Conferences</title>
</head>
<jsp:include page="header.jsp" />

<body>
<h3>Search for Conferences</h3>
<div>
	<table>
	<tr>
		<td>
			<label for="search">Search</label>
		</td>
		<td>
			<input type="text" name="search" id="search" class="ui-widget ui-widget-content ui-corner-all"></input>
		</td>
	</tr>
	
	<tr>
		<td>
			<label for="state">State</label>
		</td>
		<td>
			<select id="state" class="ui-widget ui-widget-content ui-corner-all">
			</select>
		</td>
	</tr>
	
	<tr>
		<td>
			<label for="city">City</label>
		</td>
		<td>
			<input type="text" name="city" id="city" class="ui-widget ui-widget-content ui-corner-all"></input>
		</td>
	</tr>
	
	<tr>
		<td>
			<button type="button" id="findBtn">Find Conferences</button>
			
		</td>
		<td>
			<button type="button" id="findBtn2">Find All Conferences</button>
		</td>
	</tr>
	</table>
</div>


<div id="searchResultsDiv" class="ui-widget">
<h3>Results</h3>
	<table id="searchResultsTable">
		<tr>
			<th>Conference Name</th>
			<th>Conference Description</th>
			<th>Number of Sessions</th>
			<th>Join Conference</th>
			<th>More Information</th>
		</tr>
		<tbody id="searchResultsTableBody">
		</tbody>
	</table>
</div>

</body>

<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/findconferences.js'/>"> </script>
</html>