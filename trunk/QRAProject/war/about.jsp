<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About</title>

</head>
<jsp:include page="header.jsp" />
<body>

	<div class="ui-widget">
		<p>
		<h2>Website Tutorial - Compatible with Chrome</h2>
		<h3>For temporary users (Registered with a blank ID provided by a
			Host organizer):</h3>
		<ol>
			<li>Log into website</li>
			<li>Go to Edit Profile</li>
			<li>Fill in the information, especially the email address. If
				you do not fill in the email, you will not be able to take
				attendance on your mobile application.</li>
		</ol>

		<h3>For new users (Permanent):</h3>
		<ol>
			<li>Register on website</li>
			<li>Log into website</li>
			<li>To form a new conference
				<ol type="a">
					<li>Click on Create Conference</li>
					<li>You must fill in all the specified information.</li>
					<li>Create at least one session.
						<ol type="i">
							<li>You must fill in all the specified information. You must
								upload an image. You must have a password at least five
								characters long.</li>
							<li>Your Start Date must be before your End Date</li>
						</ol>
					</li>
					<li>After the session is created, if you wish to delete a
						session, click on remove.</li>
					<li>When you finished with setting up your conference, click
						on Create Conference</li>
					<li>There should be message popping up, indicating it was a
						successfully added.</li>
				</ol>
			</li>
		</ol>

		<h3>To search for a conference to join</h3>
		<ol type="a">
			<li>Click on Search Conference in the header</li>
			<li>You must specify the State</li>
			<li>If you would like to search by name, type into the Search
				text field.</li>
			<li>If you would like to search by city, type into the City text
				field.</li>
			<li>After you have finished specifying what you would like to
				search by, please click on Find Conferences.</li>
			<li>If you have no preferences, click on Find All Conferences</li>
			<li>The conferences will then be displayed underneath below
				Results.
				<ol type="i">
					<li>To see more information on the conference, click on More
						Information

						<ol>
							<li>You can join the conference by clicking Join Conference
								on this page.</li>
						</ol>
					</li>
					<li>To join the conference, click on Join Conference</li>
					<li>There should a message indicating that you have
						successfully joined the conference.</li>
				</ol>
			</li>
		</ol>



		<h3>To see the conferences you're hosting or attending</h3>
		<ol>
			<li>Click on My Conferences</li>
			<li>To print an ID for a conference you are attending:</li>
			<li>Click on the conference that wish to print for under the
				list of conferences that you are planning to attend.</li>
			<li>For the Conferences that you are hosting
				<ol type="i">
					<li>
						<ol>
							If you want to edit one of the conferences you are hosting
							<li>Click on Edit Conference under the name of the
								conference you want to edit.</li>
							<li>To create ID cards for all of the people who are
								planning to attend your conference, click on Batch Create QR
								Codes under the conference you want to create the QR codes for.</li>
							<li>To create blank ID's for people who are not registered
								onto QR Attendance, enter the number of blank IDs you want to
								create. Then click on Create Blank QR ID Cards.</li>
							<li>To see all the people who have planned to attend the
								conference, click on View Attendance Records under the
								conference you want to check the attendance for.</li>
							<li>You will see whether the person was absent or present in
								the conference.</li>
						</ol>
					</li>
				</ol>
			</li>
		</ol>

		To log out, click on Log Out in the header.
		</ol>

		<h3>Android Tutorial</h3>
		<ol>
			<li>You must be logged in, in order to check people in with the
				scanner.</li>
			<li>Press on on Log In on the top right hand corner</li>
			<li>Enter your the email you used to register onto the website.</li>
			<li>Enter the password you used to enter the website. Note you
				must input at least four characters.</li>
			<li>After entering your email and password properly, click on
				Sign in.</li>
			<li>You can then scan in the QR codes of the people who attend.</li>
			<li>If the person has a picture, the application will up an
				image of the attendee.</li>
			<li>You can choose whether this person is the attendee, by
				clicking Valid ID or Invalid ID.
				<ol type="a">
					<li>If you click Valid ID, the person will be checked in.</li>
					<li>If you click Invalid ID, the person will not be checked
						in.</li>
				</ol>
			</li>
			<li>To Log Out, simply click Log In again on the top right hand
				corner.</li>
		</ol>

		<h3>iOS Tutorial</h3>

		<ol>
			<li>you must be logged in, in order to check people in with the
				scanner.</li>
			<li>Press Log In</li>
			<li>Enter your the email you used to register onto the website.</li>
			<li>Enter the password you used to enter the website.</li>
			<li>After entering your email and password properly, click on
				submit.</li>
			<li>You can then scan in the QR codes of the people who attend.</li>
			<li>If the person has a picture, the application will up an
				image of the attendee.</li>
			<li>You can choose whether this person is the attendee, by
				clicking Valid ID or Invalid ID.
				<ol type="a">
					<li>If you click Check-in, the person will be checked in.</li>
					<li>If you click Cancel, the person will not be checked in.</li>
				</ol>
			</li>
			<li>To Log Out, simply click Log Out.</li>
		</ol>

		</p>
	</div>

</body>

<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/redmond/jquery-ui-1.10.3.custom.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/index.js'/>">
</script>
</html>