<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Login Success</title>
	</head>
	<body>
		<p align="left"><font color="#000080" size="5">Hi&nbsp; <c:out value="${sessionScope.UserName}" />,</font></p><br/>
		<p align="center"><font color="#000080" size="3">You are successfully Logged in</font></p>
	</body>
</html>