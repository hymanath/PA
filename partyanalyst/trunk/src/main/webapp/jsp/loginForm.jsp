<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String src = "";
String type = "";
//Problem Management
String redirectLoc = "";
String task = "";
String name = "";
String stateId = "";
String districtId = "";
String localBodyId = "";
String constituencyId = "";
String localBodyElectionTypeId = "";

if(request.getParameter("src")!=null){
	src = request.getParameter("src");
}
		
src = request.getParameter("src");

if(request.getParameter("type")!=null){
	type = request.getParameter("type");
}

if(request.getParameter("redirectLoc")!=null){
	redirectLoc = request.getParameter("redirectLoc");
}

if(request.getParameter("task")!=null){
	task = request.getParameter("task");
}

if(request.getParameter("name")!=null){
	name = request.getParameter("name");
}

if(request.getParameter("stateId")!=null){
	stateId = request.getParameter("stateId");
}

if(request.getParameter("districtId")!=null){
	districtId = request.getParameter("districtId");
}

if(request.getParameter("localBodyId")!=null){
	localBodyId = request.getParameter("localBodyId");
}

if(request.getParameter("constituencyId")!=null){
	constituencyId = request.getParameter("constituencyId");
}

if(request.getParameter("localBodyElectionTypeId")!=null){
	localBodyElectionTypeId = request.getParameter("localBodyElectionTypeId");
}



%>
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

	    <center>
		        <h2>Login Details</h2>
		</center>
		</BR></BR>
		<table>
			<tr>
				<td colspan="2"><s:actionerror /></td>
			</tr>
		</table>
		<s:form name="loginForm" action="loginAction" method="POST" >
		<%
		if(src != ""){
		%>
		<input type="hidden" name="src" value="<%=src %>" />
		<% } %>

		<%
		if(type != ""){
		%>
	     <input id="paUserRadio" type="radio" name="userType" value="1" checked="checked"/>Commercial User
	     <input id="freeUserRadio" type="radio" name="userType" value="2"/>Free User

		 <input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
		 <input type="hidden" name="task" value="<%=task %>" />
		 <input type="hidden" name="name" value="<%=name %>" />
		 <input type="hidden" name="stateId" value="<%=stateId %>" />
		 <input type="hidden" name="districtId" value="<%=districtId %>" />
		 <input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
		 <input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
		 <input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />
	    <% } %>
            
			<c:out value="${sessionScope.USER_REG_SUCCESS}" />
			<c:remove var="USER_REG_SUCCESS" scope="session" />
			<s:textfield name="userName" label="%{getText('userName')}"/>
			<s:password name="password" label="%{getText('password')}"/>
			<s:submit value="Login" align="center"/>
		</s:form>
		
		<%
		if(type != ""){
		%>
		 
		 <s:form name="regForm" action="anonymousUserAction" method="POST" >

		     <input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
			 <input type="hidden" name="task" value="<%=task %>" />
			 <input type="hidden" name="name" value="<%=name %>" />
			 <input type="hidden" name="stateId" value="<%=stateId %>" />
			 <input type="hidden" name="districtId" value="<%=districtId %>" />
			 <input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
			 <input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
			 <input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />
			 <s:submit value="Register" align="center"/>

         <!--<h3><a href="anonymousUserAction.action">Register</a></h3>-->
		 </s:form>
        <% } %>
	</body>
</html>