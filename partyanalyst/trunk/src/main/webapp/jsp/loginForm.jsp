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

String parliamentConstiId = "";
String taskType = "";
String problemHistoryId = "";

String electionId1 = "";
String electionId2 = "";
String party = "";
String allianceCheck = "";

String selectedPartyName="";

String selectedPartyShortName= "";
String selectedPartyId= "";
String selectedElectionTypeName= "";
String selectedLocationName= "";
String reportLevel= "";
String stateSelectName= "";
String partySelectName= "";
String constituencySelectName= "";
String districtSelectName= "";


if(request.getParameter("districtSelectName")!=null){
	districtSelectName = request.getParameter("districtSelectName");
}
if(request.getParameter("selectedPartyShortName")!=null){
	selectedPartyShortName = request.getParameter("selectedPartyShortName");
}

if(request.getParameter("selectedPartyId")!=null){
	selectedPartyId = request.getParameter("selectedPartyId");
}

if(request.getParameter("selectedElectionTypeName")!=null){
	selectedElectionTypeName = request.getParameter("selectedElectionTypeName");
}

if(request.getParameter("selectedLocationName")!=null){
	selectedLocationName = request.getParameter("selectedLocationName");
}
if(request.getParameter("reportLevel")!=null){
	reportLevel = request.getParameter("reportLevel");
}

if(request.getParameter("stateSelectName")!=null){
	stateSelectName = request.getParameter("stateSelectName");
}

if(request.getParameter("partySelectName")!=null){
	partySelectName = request.getParameter("partySelectName");
}

if(request.getParameter("constituencySelectName")!=null){
	constituencySelectName = request.getParameter("constituencySelectName");
}

if(request.getParameter("selectedPartyName")!=null){
	selectedPartyName = request.getParameter("selectedPartyName");
}
if(request.getParameter("electionId1")!=null){
	electionId1 = request.getParameter("electionId1");
}
if(request.getParameter("party")!=null){
	party = request.getParameter("party");
}

if(request.getParameter("allianceCheck")!=null){
	allianceCheck = request.getParameter("allianceCheck");
}

if(request.getParameter("electionId2")!=null){
	electionId2 = request.getParameter("electionId2");
}


if(request.getParameter("parliamentConstiId")!=null){
	parliamentConstiId = request.getParameter("parliamentConstiId");
}
if(request.getParameter("taskType")!=null){
	taskType = request.getParameter("taskType");
}
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

if(request.getParameter("problemHistoryId")!=null){
	problemHistoryId = request.getParameter("problemHistoryId");
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

	
	<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">		

	<script type="text/javascript">
	function executeOnload()
	{
		var textBoxEl = document.getElementById("userName");
		textBoxEl.focus();
	}
		
	</script> 

	<style type="text/css">

	#loginPanel_main
	{
		background-color:#E4EDF0;
		min-height:600px;
		padding:40px;
	}

	.panelContainer
	{
		width:300px;
		/*background-color:#94A9C8;*/
		color:#94A9C8;
		font-weight:bold;
		padding:15px;
		-moz-border-radius:15px 15px 15px 15px;
		border:2px solid;
	}
	.btnStyle
	{
		background-image:url("images/icons/homePage_new/btn_homePage.png");
		border:0 none;
		color:#FFFFFF;
		font-weight:bold;
		height:30px;
		width:100px;
		cursor:pointer;
	}

	#loginPanelContentOuter,#registerContentOuter
	{
		width:360px;
	}

	
	.rounded
	{
		margin:0 0 25px;
		padding:0 0 20px;
	}
	
	.containerHeader_center
	{
		background-image:url("images/icons/homePage_new/blue_header_center.jpg");
		height:40px;
		background-repeat:repeat;
	}

	.containerHeader_center_label
	{
		color:#FFFFFF;
		font-size:14px;
		font-weight:bold;
		position:relative;
		top:10px;
	}

	.loginDetailTable th
	{
		color:#2E5979;
		font-size:12px;
		padding:5px;
	}
	
	.loginDetailTable td
	{
		padding:5px;
		color:#574E3E;
	}

	.signUpLabelPara
	{
		font-size:12px;
		line-height:20px;
	}

	</style>	
	</head>
	<body>	 
		<div id="loginPanel_main">
		
		<div style="width:360px;">
			<table width="100%" cellpadding="0" cellspacing="0" style="width:360px;">
				<tr>
					<td width="15px"><img src="images/icons/homePage_new/blue_header_top_left_login.jpg"></img></td>
					<td><div id="loginHeadCenter_center" class="containerHeader_center">
							<span class="containerHeader_center_label">Member Login</span>
						</div>
					</td>
					<td width="15px"><img src="images/icons/homePage_new/blue_header_top_right_login.jpg"></img></td>
				</tr>
			</table>
		</div>
		<div id="loginPanelContentOuter" class="rounded"> 						
			<!--<div class="corner topLeft"></div>-->
			<!--<div class="corner topRight"></div>-->
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
		
			<div id="loginPanel_Main" class="innerLayoutDivClass">
				<div id="loginPanel_Head" class="layoutHeadersClass"></div>
				<div id="loginPanel_Body" class="layoutBodyClass yui-skin-sam">
					<div id="loginPanel" class="panelContainer">
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
							<input type="hidden" name="taskType" value="<%=taskType %>" />
							<input type="hidden" name="parliamentConstiId" value="<%=parliamentConstiId %>" />
							<input type="hidden" name="problemHistoryId" value="<%=problemHistoryId%>" />
							<input type="hidden" name="electionId1" value="<%=electionId1%>" />
							<input type="hidden" name="electionId2" value="<%=electionId2%>" />
							<input type="hidden" name="party" value="<%=party%>" />
							<input type="hidden" name="allianceCheck" value="<%=allianceCheck%>" />
							<input type="hidden" name="selectedPartyName" value="<%=selectedPartyName%>" />
							<input type="hidden" name="selectedPartyShortName" value="<%=selectedPartyShortName%>"/>
							<input type="hidden" name="selectedPartyId" value="<%=selectedPartyId%>"/>
							<input type="hidden" name="selectedElectionTypeName" value="<%=selectedElectionTypeName%>"/>
							<input type="hidden" name="selectedLocationName" value="<%=selectedLocationName%>"/>
							<input type="hidden" name="reportLevel" value="<%=reportLevel%>"/>
							<input type="hidden" name="stateSelectName" value="<%=stateSelectName%>"/>
							<input type="hidden" name="partySelectName" value="<%=partySelectName%>"/>
							<input type="hidden" name="constituencySelectName" value="<%=constituencySelectName%>"/>
							<input type="hidden" name="districtSelectName" value="<%=districtSelectName%>"/>
								
							<c:out value="${sessionScope.USER_REG_SUCCESS}" />
							<c:remove var="USER_REG_SUCCESS" scope="session" />
							
							<table class="loginDetailTable" width="100%">
								<tr>
									<td width="10"><img src="images/icons/indexPage/listIcon.png"></img></td>
									<th style="font-size:12px;color:#AB6413;text-decoration:underline;">Sign in with your Party Analyst account</th>
								</tr>
							</table>
							<table class="loginDetailTable">								
								<tr>
									<th>Sign In As</th>
									<td>
										<input id="paUserRadio" type="radio" name="userType" value="1"/>Customer
										<input id="freeUserRadio" type="radio" name="userType" value="2" checked="checked"/>Free User
									</td>
								</tr>
								<tr>
									<th><s:label theme="simple" value="%{getText('userName')}"></s:label></th>
									<td>
										<s:textfield theme="simple" name="userName" id="userName" label="%{getText('userName')}"/>
									</td>
								</tr>
								<tr>
									<th><s:label theme="simple" value="%{getText('password')}"></s:label></th>
									<td>
										<s:password theme="simple" name="password" label="%{getText('password')}"/>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="right"><s:submit value="Sign In" cssClass="btnStyle" align="center"/></td>
								</tr>
							</table>							
						</s:form>
					</div>
				</div>
			</div>		
		</div>
		
		<div style="width:360px;">
			<table width="100%" cellpadding="0" cellspacing="0" style="width:360px;">
				<tr>
					<td width="15px"><img src="images/icons/homePage_new/blue_header_top_left_login.jpg"></img></td>
					<td>
						<div id="registerHeadCenter_center" class="containerHeader_center">
							<span class="containerHeader_center_label">New Free User Signup</span>
						</div>
					</td>
					<td width="15px"><img src="images/icons/homePage_new/blue_header_top_right_login.jpg"></img></td>
				</tr>
			</table>
		</div>
		<div id="registerContentOuter" class="rounded"> 						
			<!--<div class="corner topLeft"></div>-->
			<!--<div class="corner topRight"></div>-->
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
		
			<div id="registerInfo_Main" class="innerLayoutDivClass">
				<div id="registerInfo_Head" class="layoutHeadersClass"></div>
				<div id="registerInfo_Body" class="layoutBodyClass yui-skin-sam">
					 <div id="registerPanel" class="panelContainer">
						<!--<table>
							<tr>
								<td colspan="2"><s:actionerror /></td>
							</tr>
						</table>-->
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
								<input type="hidden" name="taskType" value="<%=taskType %>" />
								<input type="hidden" name="parliamentConstiId" value="<%=parliamentConstiId %>" />		
								<input type="hidden" name="electionId1" value="<%=electionId1%>" />
								<input type="hidden" name="electionId2" value="<%=electionId2%>" />
								<input type="hidden" name="party" value="<%=party%>" />
								<input type="hidden" name="allianceCheck" value="<%=allianceCheck%>" />	
								<input type="hidden" name="selectedPartyName" value="<%=selectedPartyName%>" />
								<input type="hidden" name="selectedPartyShortName" value="<%=selectedPartyShortName%>"/>
								<input type="hidden" name="selectedPartyId" value="<%=selectedPartyId%>"/>
								<input type="hidden" name="selectedElectionTypeName" value="<%=selectedElectionTypeName%>"/>
								<input type="hidden" name="selectedLocationName" value="<%=selectedLocationName%>"/>
								<input type="hidden" name="reportLevel" value="<%=reportLevel%>"/>
								<input type="hidden" name="stateSelectName" value="<%=stateSelectName%>"/>
								<input type="hidden" name="partySelectName" value="<%=partySelectName%>"/>
								<input type="hidden" name="constituencySelectName" value="<%=constituencySelectName%>"/>
								<input type="hidden" name="districtSelectName" value="<%=districtSelectName%>"/>
								
								<table  class="loginDetailTable">
									<tr>
										<td>
											<p class="signUpLabelPara">New to Party Analyst ? Sign up to connect to your people, post your area problems and access to many features. </p>
										</td>
									</tr>
									<tr>	
										<td align="right">
											<s:submit value="Sign Up" cssClass="btnStyle" align="center"/>
										</td>
									</tr>	
								</table>
								<!--<h3><a href="anonymousUserAction.action">Register</a></h3>-->
							</s:form>
						</div>
					</div>
				</div>
			</div>		
		</div>
        
       
		</div>
        <script type="text/javascript">
        	executeOnload();
        </script>
	</body>
</html>