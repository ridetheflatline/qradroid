<jsp:include page="header.jsp" />

<%@ page import="com.qra.project.Session"%>
<%@ page import="com.qra.project.Conference" %>
<%@ page import="java.util.List" %>
<% Conference c = (Conference) request.getAttribute("conference");  %>
<% List<Session> sessions = (List<Session>) request.getAttribute("sessions"); %>

<div class="ui-widget">
<h2>Conference Information</h2>
	<table>
		<tr>
			<td>Conference Name</td>
			<td><%= c.getConf_name() %></td>
		</tr>
		<tr>
			<td>Conference Description</td>
			<td><%= c.getConference_description() %></td>
		</tr>
		<tr>
			<td>Conference Start Date </td>
			<td><%= c.getStartTime() %></td>
		</tr>
		<tr>
			<td>Conference End Date</td>
			<td><%= c.getEndTime() %></td>
		</tr>
	</table>
	
	<table>
		<thead>
		<tr>
			<th>Session start time</th>
			<th>Session end time</th>
			<th>Session Description</th>
		</tr>
		</thead>
		
		<tbody>
			<% for(Session s : sessions) {%>
				<tr>	
					<td><%= s.getStartTime() %></td>
					<td><%= s.getEndTime() %></td>
					<td><%= s.getDescription() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
	
	<table>
		<tr>
			<td><a href="#" id="joinConf">Join Conference</a></td>
		</tr>
	</table>
	
</div>
<jsp:include page="footer.jsp" />