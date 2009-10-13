<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script type="text/javascript">
	window.onload=function() 
	{
		document.forms[0][0].focus();
	}
</script> 
<title>Login</title>
</head>
	<body>
		<table>
			<tr>
  	 			<td colspan="2"><s:actionerror /></td>
  			</tr>
  		</table>
		<s:form name="loginForm" action="loginAction" method="POST" >
			<s:textfield name="userName" label="%{getText('userName')}"/>
			<s:password name="password" label="%{getText('password')}"/>
			<s:submit value="Login" align="center"/>
		</s:form>
	</body>
</html>