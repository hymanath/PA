<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 


<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>





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
String candidateId = "";
String showMessage = "";


if(request.getParameter("candidateId")!=null){
	candidateId = request.getParameter("candidateId");
}
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
if(request.getParameter("showMessage")!=null){
	showMessage = request.getParameter("showMessage");
	
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

	#accessDiv 
	{
		background: none repeat scroll 0 0 #FFF8E5;
		border: 1px solid #06ABEA;
		border-radius: 7px 7px 7px 7px;
		height: auto;
		padding: 10px;
		text-align: center;
		width: 450px;
	}

	</style>	
	</head>
	<body><center>	 
		<div id="forgot_password_window" style="background-color: #C7CFD2;">
		<div id="forgot_password_window_inner" style="font-size:0.8em"></div>
		</div>
		<div style="background-color:#E4EDF0;"><br>
		<div style="display:none;" id="accessDiv">
			<img style="display:inline;" alt="sorry" src="images/icons/smiley_sad.png">&nbsp;&nbsp;<h3 style="color:#ff0000;display:inline;position:relative;top:-10px;font-weight:bold;">Only Registered users can view this content.<br>
			Already a member, Please Login Here.<br>
			(OR)<br>
			Not a member, Click here for <a href="freeUserRegistration.action">FREE REGISTRATION</a>.</h3>

		</div>
		</div>
		<div id="loginPanel_main" class="background" style="align:center">
		
		<div style="width:360px;">
			<table width="100%" cellpadding="0" cellspacing="0" style="width:360px;">
				<tr>
					<td width="15px"><img src="images/icons/homePage_new/blue_header_top_left_login.jpg">
					</img></td>
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
								<td colspan="2" style="color:red"><s:actionerror /></td>
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
							<input type="hidden" name="candidateId" value="<%=candidateId%>"/>
								
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
										<input id="paUserRadio" type="radio" name="userType" value="1"/>&nbsp;&nbsp;Customer
										<input id="freeUserRadio" type="radio" name="userType" value="2" checked="checked"/>&nbsp;&nbsp;Free User
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
									<td align="right"><s:submit value="Sign In" cssClass="btnStyle" align="center"/></td></tr><tr>
									<td colspan="2"><a href="javascript:{}" onclick="showForgotPasswordPanel()" style="margin:179px;color:#0174DF;" >Forgot Password</a></td>
								</tr>
							</table>							
						
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
							
								<!-- <input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
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
								<input type="hidden" name="candidateId" value="<%=candidateId%>"/> -->
								
								<table  class="loginDetailTable">
									<tr>
										<td>
											<p class="signUpLabelPara">New to Party Analyst ? Sign up to connect to your people, post your area problems and access to many features. </p>
										</td>
									</tr>
									<tr>	
										<td align="right">
											<h3><a href="freeUserRegistration.action"><img src="images/icons/homePage_new/signup_button.gif"></a></h3>
										</td>
									</tr>	
								</table>
								<!--<h3><a href="freeUserRegistration.action">Register</a></h3>-->
							</s:form>
						</div>
					</div>
				</div>
			</div>		
		</div>
        <div id="showLoginErrorMsgDiv"></div>
       
		</div>

		<script type="text/javascript">


function callAJAX(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "forgotPassword")
					{
						showDetails(results);
					}
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
function closewindow(){
	
	$("#forgot_password_window").dialog("destroy");
}

function showDetails(results)
{
	
	var result = document.getElementById("feedback_window_errorMsg");
	 str='';
	
	if(results == null){
		str+='<div style="color:red"> Your request not submitted. Please try again.</div>';
	}
	else if(results.email == null){		
		str+='<div style="color:red"><b> Username Doesnot exist </b></div>';
	}
	else{
		$("#forgot_password_window").dialog("destroy");
		afterPasswordSubmit(results.email);
		return;
	}
	result.innerHTML = str;
	
}
function afterPasswordSubmit(email){

 $("#forgot_password_window").dialog({
			resizable:false,
			width: 600,
			minHeight:150,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("forgot_password_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">ForgotPassword?</div>';
		str += '<div id="feedback_window_body" style="font-weight:bold;color:green;text-align:center;">';
		str += 'Your password has been mailed to your email address :'+email+'</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr><td>';
		str += '	<input id="OkButton" type="button" width="50px" align="center"' ;
		str += '   value="OK"></input></td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton2 = new YAHOO.widget.Button("OkButton");

		oPushButton2.on("click",function(){
			$("#forgot_password_window").dialog("destroy");
		});
}

function showForgotPasswordPanel(){

$("#forgot_password_window").dialog({
			resizable:false,
			width: 600,
			minHeight:200,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("forgot_password_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Forgot Password ?</div>';
		str += '<div id="feedback_window_body">';
		str += '	<div id="feedBackNote_div">';
		str += '		<table>';
		str += '		<tr>';
		str += '		<td><img src="images/icons/infoicon.png"></td>';
		str += '		<td>Fields marked with (<font color="red">*</font>) are mandatory</td>';
		str += '		</tr>';
		str += '		</table>';
		str += '	</div>';
		str += '	<div id="feedBackForm_div">';
		str += '		<table id="feedbackTable" width="100%">';
		str += '		<tr>';
		str += '		<th><font color="red">*</font>UserName </th>';
		str += '		<td>';
		str += '			<input type="text" id="userName_FP" size="25"/>';
		str += '		</td>';
		str += '		</tr>';
		str += '		</table>';
		str += '	</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input  style="text-align:center;" id="submitButton" type="button" value="Submit"></input>';
		str += '		<input style="text-align:center;" id="cancelButton" type="button" value="Cancel"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("submitButton");  
		var oPushButton2 = new YAHOO.widget.Button("cancelButton");

		oPushButton1.on("click",function(){
			checkAvailability();
		});

		oPushButton2.on("click",function(){
			$("#forgot_password_window").dialog("destroy");
		});

}


function checkAvailability()
{

	var name = document.getElementById("userName_FP").value;
 	
	if(name==""){
		document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName field cannot be empty</font>";
 	 }
	 else if (name.charAt(0).indexOf(" ")==0){
		 document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName Should not contain spaces</font>";
 	 }
	 else if(name.length < 6)
	{
		document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName must be between 6 and 20 characters long.</font>";
	}
	 
     else{ 
		var str = '<font color="#000000">Sending Your Request.Please wait</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
 		document.getElementById("feedback_window_errorMsg").innerHTML = str;
 		var jsObj=
		{		
 				userName:name,
				task:"forgotPassword",
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/recoverPasswordAnanymousUserAction.action?"+rparam;						
		callAJAX(jsObj,url);
 	 }
}


</script>

<script type="text/javascript">

function executeOnload()
{  
   var checkedValue='';
	checkedValue = '${sessionScope.checkedTypeValue}'; 
	 if(checkedValue =="1")
	  { 
		  document.getElementById("paUserRadio").checked=true ;
		  document.getElementById("freeUserRadio").checked=false;
	  }
	 if(checkedValue =="2")
	 { 
		  document.getElementById("paUserRadio").checked=false ;
		  document.getElementById("freeUserRadio").checked=true;
	  }
	var textBoxEl = document.getElementById("userName");
	textBoxEl.focus();
	
}
		
</script> 

<script type="text/javascript">
	executeOnload();
if('<%=showMessage%>' == 'yes')
	document.getElementById('accessDiv').style.display = 'block';
</script>
</body>
</html>