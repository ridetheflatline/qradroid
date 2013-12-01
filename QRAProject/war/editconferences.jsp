<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit Conference</title>
</head>

<%@ page import="com.qra.project.Conference" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qra.project.Session" %>
<jsp:include page="header.jsp" />

<%
	Conference c = (Conference)session.getAttribute("conference");
	List<Session> mySessions = (List <Session>)session.getAttribute("mySessions");
%>

<body>
<div class="ui-widget">
<h2>Edit Conference</h2>

<label>Conference Name: </label>
	<input type="hidden" id="confId" value="<%=c.getConf_code() %>">
	<br>
	<input id="conf_name" type="text" name="conf_name" value="<%=c.getConf_name() %>" class="text ui-widget-content ui-corner-all" >
	<br>
	<label>Conference Description: </label>
	<br>
	<textarea id="conf_description" rows="8" cols="40" class="text ui-widget-content ui-corner-all"><%= c.getConference_description() %></textarea>
	<br>
	<label>Street</label>
	<br>
	<input id="street" type="text" name="street" value="<%=c.getAddress() %>" class="text ui-widget-content ui-corner-all" >
	<br>
	<label>City</label>
	<br>
	<input id="city" type="text" name="city" value="<%=c.getCity()%>" class="text ui-widget-content ui-corner-all" >
	<br>
	<label>State</label>
	<br>
	<input type="hidden" id="defaultState" value="<%=c.getState() %>">
	<select id="state" class="ui-widget ui-widget-content ui-corner-all" id="state">
	</select>
	<br>
	<label>Time Zone</label>
	<br>
	
	<input type="hidden" id="defaultTimeZone" value="<%= c.getTimeZone() %>">
	<select id="timeZone" class="ui-widget ui-widget-content ui-corner-all">
	</select>
	
	<p>
	Click <a href="http://www.statoids.com/tus.html">here</a> to find your conference Timezone.
	</p>
	<div class="ui-widget">
		<table id="sessions">
			<thead>
				<tr class="ui-widget-header ">
					<th>Start Time</th>
					<th>End Time</th>
					<th>Session Description</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
			<% for(int i = 0; i < mySessions.size(); i++) { 
				String mySessId = "sess" + i;
				String[] sessStartTimeArray =  mySessions.get(i).getStartDateAsFormattedString().split(" ");
				String sessStartTime = sessStartTimeArray[0] + " at " + sessStartTimeArray[1] + " " + sessStartTimeArray[2];
				String[] sessEndTimeArray =  mySessions.get(i).getEndDateAsFormattedString().split(" ");
				String sessEndTime = sessEndTimeArray[0] + " at " + sessEndTimeArray[1] + " " + sessEndTimeArray[2];
			%>
				<tr>
					<td><%= sessStartTime %></td>
					<td><%= sessEndTime %></td>
					<td><%= mySessions.get(i).getDescription() %></td>
					<td><button type="button" class="sessionEdits" id="<%= mySessId%>">Edit</button></td>
				</tr>
			 <% } %>
			 </tbody>
		</table>
	</div>
	<br>
	<button type="button" id="addSession">Add Session</button>
	<button type="button" id="saveConferenceChanges">Save</button>
</div>

<div id="dialog-form" title="Create Session">
  <p class="validateTips">All form fields are required.</p>
  <div id="sessionFormErrors">
  </div>
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
					<select id="startHour" class="ui-widget ui-widget-content ui-corner-all">
					</select>
				</td>
				<td>
					<select id="startMinute" class="ui-widget ui-widget-content ui-corner-all">
					</select>
				</td>
				<td>
					<select id="startAmOrPm" class="ui-widget ui-widget-content ui-corner-all">
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
					<select id="endHour" class="ui-widget ui-widget-content ui-corner-all">
					</select>
				</td>
				<td>
					<select id="endMinute" class="ui-widget ui-widget-content ui-corner-all">
					</select>
				</td>
				<td>
					<select id="endAmOrPm" class="ui-widget ui-widget-content ui-corner-all">
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

<div id="addConfSuccess" title="Success!">
	<p>
		You have successfully added the Conference!
	</p>
</div> 

<div id="noSessionMessage" title="No Sessions">
	<p>
		Conference needs to have at least one session.
	</p>
</div>

</body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/moment.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/js/editconference.js'/>"> </script>

</html>