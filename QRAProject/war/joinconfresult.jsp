<jsp:include page="header.jsp" />

<% String resultMsg = (String)request.getAttribute("resultMsg"); %>

<div class="ui-widget">

<p>
<!-- 
Output the result of attempting to join a conference here in JoinConferenceServlet
 -->
 <%= resultMsg %>
</p>

</div>

<jsp:include page="footer.jsp" />