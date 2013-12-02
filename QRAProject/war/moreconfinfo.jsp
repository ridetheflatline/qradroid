<jsp:include page="header.jsp" />

<%@ page import="com.qra.project.Session"%>
<%@ page import="com.qra.project.Conference" %>
<%@ page import="java.util.List" %>
<% Conference c = (Conference) request.getAttribute("conference");  %>
<% 
	List<Session> sessions = (List<Session>) request.getAttribute("sessions"); 
	String userKeyIdString = (String) request.getAttribute("userKeyIdString");
%>

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
			<td>Conference Address</td>
			<td><%= c.getAddress() %></td>
		</tr>
		<tr>
			<td>Conference City</td>
			<td><%= c.getCity() %></td>
		</tr>
		<tr>
			<td>Conference State</td>
			<td><%= c.getState() %></td>
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
	
	<br>
	<table>
		<thead>
		<tr class="ui-widget-header ">
			<th>Session start time</th>
			<th>Session end time</th>
			<th>Session Description</th>
		</tr>
		</thead>
			
		<tbody>
			<% for(Session s : sessions) {%>
				<tr>	
					<td><%= s.getStartDateAsFormattedString() %></td>
					<td><%= s.getEndDateAsFormattedString() %></td>
					<td><%= s.getDescription() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
	
	<br>
	
	<table>
		<tr>
			<td><a href="/joinconference?conf_code=<%=c.getConf_code()%>&user_id=<%=userKeyIdString%>&page_output=true" id="joinConf">Join Conference</a></td>
		</tr>
	</table>
	
</div>
<jsp:include page="footer.jsp" />