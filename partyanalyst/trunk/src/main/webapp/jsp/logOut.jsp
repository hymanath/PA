<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Logout</title>
	</head>
	<body>
		<c:remove var="loginStatus" scope="session" />
		<p> You are successfully logged out </p><br/><br/>
	</body>
</html>
