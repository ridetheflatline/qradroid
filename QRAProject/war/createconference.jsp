<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create Conference</title>
</head>

<jsp:include page="header.jsp" />

<body>
<div id="sessions-contain" class="ui-widget">
<h2>Create Conference</h2>
<form>

	<div id="conferenceErrors">
	</div>
	
	<label>Conference Name: </label>
	<br>
	<input id="conf_name" type="text" name="conf_name" class="text ui-widget-content ui-corner-all" >
	<br>
	<label>Conference Description: </label>
	<br>
	<textarea id="conf_description" rows="8" cols="40" class="text ui-widget-content ui-corner-all"></textarea>
	<br>
	<label>Street</label>
	<br>
	<input id="street" type="text" name="street" class="text ui-widget-content ui-corner-all" >
	<br>
	<label>City</label>
	<br>
	<input id="city" type="text" name="city" class="text ui-widget-content ui-corner-all" >
	<br>
	<label>State</label>
	<br>
	<input id="state" type="text" name="state" class="text ui-widget-content ui-corner-all" >
	

	<h3>Sessions</h3>
	<p>You have no sessions</p>
	<table id="sessions" class="ui-widget ui-widget-content">
		<thead>
			<tr class="ui-widget-header ">
				<th>Start Date</th>
				<th>End Date</th>
				<th>Description</th>
				<th>Remove Session</th>
				<th>Edit Session</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

	<br>
    <button type="button" id="createSessionBtn">Add Session</button>
    <button type="button" id="createConferenceBtn">Create Conference</button>
</form>

</div>
</body>

<div id="dialog-form" title="Create Session">
  <p class="validateTips">All form fields are required.</p>
  <div id="sessionFormErrors"></div>
  <form>
		<table>
			<tr>
				<td><label for="startDate">Start Date</label></td>
				<td><label for="startHour">Hour</label></td>
				<td><label for="startMinute">Minute</label></td>
				<td><label for="startAmOrPm">AM/PM</label></td>
			</tr>
			<tr>
				<td><input size="20" type="text" name="startDate"
					id="startDate" value=""
					class="text ui-widget-content ui-corner-all" /></td>
				<td>
					<select id="startHour">
					</select>
				</td>
				<td>
					<select id="startMinute">
					</select>
				</td>
				<td>
					<select id="startAmOrPm" class="ui-widget">
						<option value=""></option>
						<option value="AM">AM</option>
						<option value="PM">PM</option>
					</select>
				</td>
			</tr>

			<tr>
				<td><label for="endDate">End Date</label></td>
				<td><label for="endHour">Hour</label></td>
				<td><label for="endMinute">Minute</label></td>
				<td><label for="endAmOrPm">AM/PM</label></td>
			</tr>

			<tr>
				<td><input size="20" type="text" name="endDate" id="endDate"
					value="" class="text ui-widget-content ui-corner-all" /></td>
				<td>
					<select id="endHour">
					</select>
				</td>
				<td>
					<select id="endMinute">
					</select>
				</td>
				<td>
					<select id="endAmOrPm" class="ui-widget">
						<option value=""></option>
						<option value="AM">AM</option>
						<option value="PM">PM</option>
					</select>
				</td>
			</tr>

			<tr>
		</table>

		<label for="name">Session Description</label>
		<textarea id="sessionDescrip" rows="8" cols="40"
			class="text ui-widget-content ui-corner-all"></textarea>

	</form>
</div>

<div id="noSessionMessage" title="No Sessions">
	<p>
		Conference needs to have at least one session.
	</p>
</div>

<div id="addConfSuccess" title="Success!">
	<p>
		You have successfully added the Conference!
	</p>
</div> 

<script type="text/javascript" src="<c:url value='/js/moment.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/createconference.js'/>"> </script>

</html>