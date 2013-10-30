<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Logout</title>
	</head>
	<body>
		<c:remove var="loginStatus" scope="session" />
		<c:remove var="USER" scope="session" />
		<c:remove var="USERCADRESINFOVO" scope="session" />
		<c:remove var="UserType" scope="session" />
		<%
			session.invalidate(); 
		%>
		<p> You are successfully logged out </p><br/><br/>
		<% response.sendRedirect("homePage.action"); %>
	</body>
</html>
