<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String src = "";
//Problem Management
String redirectLoc = "";
String task = "";
String name = "";
String stateId = "";
String districtId = "";
String localBodyId = "";
String constituencyId = "";
String localBodyElectionTypeId = "";
String districtName = "";
String constituencyName = "";
String url = "";

String electionId = "";
String stateID = "";
String electionType = "";
String electionTypeId = "";
String stateName = "";
String year = "";

if(request.getParameter("electionId")!=null){
	electionId = request.getParameter("electionId");
}
if(request.getParameter("stateID")!=null){
	stateID = request.getParameter("stateID");
}
if(request.getParameter("electionType")!=null){
	electionType = request.getParameter("electionType");
}
if(request.getParameter("electionTypeId")!=null){
	electionTypeId = request.getParameter("electionTypeId");
}
if(request.getParameter("stateName")!=null){
	stateName = request.getParameter("stateName");
}
if(request.getParameter("year")!=null){
	year = request.getParameter("year");
}
if(request.getParameter("src")!=null){
	src = request.getParameter("src");
}

if(request.getParameter("url")!=null){
	url = request.getParameter("url");
}
		
src = request.getParameter("src");

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

if(request.getParameter("districtName")!=null){
	districtName = request.getParameter("districtName");
}

if(request.getParameter("constituencyName")!=null){
	constituencyName = request.getParameter("constituencyName");
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
		function executeOnload()
		{
			var textBoxEl = document.getElementById("userName");
			textBoxEl.focus();
		}
			
		</script> 
	<style>
	#loginPanel
	{
		border:1px solid;
		width:300px;
		background-color:#94A9C8;
		color:#FFFFFF;
		font-weight:bold;
		padding:15px;
		margin-top:25px;
	}
	btnStyle {
		font-weight:bold;
		width:50px;
	}
	</style>	
	</head>
	<body>	    
		<div id="loginPanel">
			<table>
				<tr>
					<td colspan="2"><s:actionerror /></td>
				</tr>
			</table>
			
			<s:form name="loginForm" action="loginAction" method="POST">
				<%
				if(src != ""){
				%>
				<input type="hidden" name="src" value="<%=src %>" />
				<% } %>
				<input type="hidden" name="url" value="<%=url %>" />
                <input type="hidden" name="electionId" value="<%=electionId %>" />
				<input type="hidden" name="stateID" value="<%=stateID %>" />
				<input type="hidden" name="electionType" value="<%=electionType %>" />
				<input type="hidden" name="electionTypeId" value="<%=electionTypeId %>" />
				<input type="hidden" name="stateName" value="<%=stateName %>" />
                <input type="hidden" name="year" value="<%=year %>" />


				<P>Registered User Sign In</P>
				
				<input id="paUserRadio" type="radio" name="userType" value="1" checked="checked"/>Customer
				<input id="freeUserRadio" type="radio" name="userType" value="2"/>Free User
				<BR>
				<BR>
				<input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
				<input type="hidden" name="task" value="<%=task %>" />
				<input type="hidden" name="name" value="<%=name %>" />
				<input type="hidden" name="stateId" value="<%=stateId %>" />
				<input type="hidden" name="districtId" value="<%=districtId %>" />
				<input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
				<input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
				<input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />
				<input type="hidden" name="districtName" value="<%=districtName %>" />
				<input type="hidden" name="constituencyName" value="<%=constituencyName %>" />
				    
				<c:out value="${sessionScope.USER_REG_SUCCESS}" />
				<c:remove var="USER_REG_SUCCESS" scope="session" />
				<s:textfield name="userName" id="userName" label="%{getText('userName')}"/>
				<s:password name="password" label="%{getText('password')}"/>
				<s:submit value="Sign In" cssClass="btnStyle" align="center"/>
			</s:form>
        </div>
        
        <div id="loginPanel">
				<table>
					<tr>
						<td colspan="2"><s:actionerror /></td>
					</tr>
				</table>
				<div>
					<s:form name="regForm" action="anonymousUserAction" method="POST" theme="simple" >
						<input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
						<input type="hidden" name="task" value="<%=task %>" />
						<input type="hidden" name="name" value="<%=name %>" />
						<input type="hidden" name="stateId" value="<%=stateId %>" />
						<input type="hidden" name="districtId" value="<%=districtId %>" />
						<input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
						<input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
						<input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />			 
						<input type="hidden" name="districtName" value="<%=districtName %>" />
						<input type="hidden" name="constituencyName" value="<%=constituencyName %>" />					
						<table>
							<tr>
								<td>
									<table>
										<tr>
											<td style="padding-right:210px;"><IMG src="images/icons/b_arrow.gif"> New User?</td>
										</tr>	
									</table>
								</td>
							</tr>
							<tr>	
								<td>
									<table>
										<tr>	
											<td style="padding-left:100px;"><s:submit value="Sign Up" cssStyle="btnStyle" align="center"/></td>
										</tr>	
									</table>
								</td>
							</tr>	
						</table>
						<!--<h3><a href="anonymousUserAction.action">Register</a></h3>-->
					</s:form>
				</div>
			</div>
        <script type="text/javascript">
        	executeOnload();
        </script>
	</body>
</html>