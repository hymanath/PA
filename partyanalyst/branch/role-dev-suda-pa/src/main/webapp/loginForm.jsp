<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Login</title>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<script type="text/javascript">
			window.onload=function() 
			{
				document.forms[0][0].focus();
			}
		</script> 
		
	</head>
	<body>
		<table>
			<tr>
				<td colspan="2"><s:actionerror /></td>
			</tr>
		</table>
		<s:form name="loginForm" action="loginAction" method="POST" >
			<c:out value="${sessionScope.USER_REG_SUCCESS}" />
			<c:remove var="USER_REG_SUCCESS" scope="session" />
			<s:textfield name="userName" label="%{getText('userName')}"/>
			<s:password name="password" label="%{getText('password')}"/>
			<s:submit value="Login" align="center"/>
		</s:form>
	</body>
</html>