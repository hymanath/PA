</table>
<div class="background">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<!--  BootStrap Code-->
 

<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<link type="text/css" rel="stylesheet" href="styles/newsstyle.css">
<!--  BootStrap Stating -->
 <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!--  BootStrap Ending	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="styles/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="styles/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="styles/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="styles/assets/ico/apple-touch-icon-57-precomposed.png">
 -->

<style>
.service-box{width:265px;margin:10px;float:left;padding:5px;height:267px;}

</style>

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

	<!--#accessDiv 
	{
		background: none repeat scroll 0 0 #FFF8E5;
		border: 1px solid #06ABEA;
		border-radius: 7px 7px 7px 7px;
		height: auto;
		padding: 10px;
		text-align: center;
		width: 450px;
		border-bottom: 2px solid #06ABEA;
	}-->

	.newFontSize{
		font-size:14px;

	}
	body{background:#5d5d5d !important;}
	
    #cnstituencyDiv{
	   heigth:263px;
	}
	[if IE]
	    #cnstituencyDiv{
	      heigth:244px;
	   }
    [endif]

	</style>	
	</head>
	<body style="background-color:#5d5d5d;">
	
		<div id="forgot_password_window" style="background-color: #C7CFD2;">
		<div id="forgot_password_window_inner" style="font-size:0.8em"></div>
		</div>
		<div><br>
		 <!--<div style="display:none;width: 615px;margin-left: 184px;" id="accessDiv">
			<h3 style="color:#ff0000;display:inline;position:relative;top:-10px;font-weight:bold;font-size:15px">Only Registered users can view this content.
			Already a member, Please Login Here.
			(OR)
			Not a member, Click here for <a href="freeUserRegistration.action">FREE REGISTRATION</a>.</h3>

		</div>-->
		<!--<div id="registerDiv" style= "background-color: #F9F9F9;border: 2px solid #06ABEA;color: #000000; cursor: pointer;margin-left: 152px;margin-top:-19px;padding: 6px;position: fixed;width: 235px;z-index: 9999;">
		<a href="javaScript:hideRegisterDiv();" style="float: right;"><b>(X)</b></a>
		Only Registered users can view this content.
			Already a member, Please Login Here.
			</br>(OR)
			Not a member, Click here for <a href="freeUserRegistration.action">FREE REGISTRATION</a>.</h3> 
		</div>-->
		</div>
		
		<!-- bootstap code(html 5)-->
	<div class="container">
	
	<div class="span12">

           
       


	    <div class="row">
		    <div class="span8">
			 
		        <h4>PARTY ANALYST</h4>
                <p style="lead" class="newFontSize">We always strive to make things easy for you, Here are our services.</p>
            
                <div class="">
				   			    
			                 <div class="">
							 
						         <div class="service-box">
				                 <a href="electionAnalysisAndManagementTool.action?src=homePageAd"><span style="padding: 16px 15px;">Election Analysis and Management Tool</span></a>
                                 <img src="images/new_homepage/sp-pa.jpg"/>
                                 <span style = "padding: 24px;"  class="newFontSize" ><p>Analyze your constituency,   track your constituency problems,manage your cadres,  share your updates to <font size="2px"><strong>Cadre, Influencing people and Voters</strong></font>.</p></span>
                                 </div>  
			                 </div>

                            <div class="">
				                 <div class="service-box">
                                 <a href="VotersPulse.action?src=homePageAd"><span>Voters Pulse</span></a>
                                 <img src="images/new_homepage/checklist.jpg"/>
                                 <span style = "padding: 34px;"><p class="newFontSize">Now, you can drive your survey's through Party Analyst...
                                 Ask, Analyze, Improve.</p></span>
                                 </div>
				            </div>
							
					    
                   </div>  
					
					<div class="">
					    
			                 <div class="" >
							    
						         <div id ="cnstituencyDiv" class="service-box">
                                 <a href="constituencyProfileReport.action?src=homePageAd"><span>Constituency Profile Report</span></a>
                                 <img src="images/site/imgpartyperf.jpg">
                                 <span style = "padding: 39px;"><p class="newFontSize">Are you aspirant candidate for
                                 2014 elections in Andhra Pradesh get the complete picture of your constituency profile report.</p></span>
                                </div>
			                 </div>

                            <div class="" >
				                 
                                 <div class="service-box">
                                 <a href="newsMonitoringService.action?src=homePageAd"><span>News Monitoring Service</span></a>
                                 <img src="images/new_homepage/NewsTracker.png"/>
                                 <span style = "padding: 40px;"><p class="newFontSize">As a Politician or Political Party, News Monitoring and analyzing is very important &nbsp;to keep upper hand in the political arena.</p></span>
                                 </div>
				            </div>
					   
                    </div>
					
		        </div> 
           
			
		    <div class="span4">
			              <div>
			                <jsp:include page="loginPage.jsp" flush="true" />
						 </div>
		                 <div class="span3 well"style="border: 5px solid #E3E3E3;
    margin-left: 21px;">
		                      <h4 style="margin-bottom: 7px;">New to Party Analyst?</h4>
		                      <a href="freeUserRegistration.action" class="btn btn-success pull-right">Create an Account</a>
		                 </div>
		        
						
			
                  <div id="showLoginErrorMsgDiv"></div>
       
		    </div>
	 
			
	</div>	
</div>	
	
</div>	


		

	<script type="text/javascript">  
function handleErrorMessage()
{ 
                 //document.getElementById("getLoginErrorMessageDiv").innerHTML = '';
				 
                 var emailId = document.getElementById("userName").value;
                 var securityName = document.getElementById("passWord_Id").value;
                 var errorDivEle = document.getElementById("getLoginErrorMessageDiv");
	             var eFlag = false;

	             var str = '<font color="red">';

	              if(emailId.length == 0 || emailId == "Email" )
	               {
		              str += ' username is required<br>';
		              eFlag = true;
	               }
	
	              if(securityName.length == 0 || securityName =="Password")
	               {
		               str += ' password is required<br>';
		                eFlag = true;
	               }
	                str += '</font>';
	                errorDivEle.innerHTML = str;
	
	                if(eFlag)
		                return false;
						
					return true;
						  
}


function callAJAX(jsObj,url){

	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);
					if(jsObj.task == "saveUserEmailAndSendPwd")
					{
						
						showEmailStatus(results);
						
					}
					else if(jsObj.task == "forgotPassword")
					{
						showDetails(results);
					}
					else if(jsObj.task == "recoverPassword")
					{
						showPasswordStatus(results);
					}

					
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
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
	else if(results.userName == null){		
		str+='<div style="color:red"><b> Username Doesnot exist </b></div>';
	}
	else{
		$("#forgot_password_window").dialog("destroy");
		afterPasswordSubmit(results.userName,results.email);
		return;
	}
	result.innerHTML = str;
	
}


function showPasswordStatus(results)
{
	
	var errorDivEle = document.getElementById('ErrorMsgDivId');
	var str='';

	if(results == null){
		str+='<div style="color:red"> Your request not submitted. Please try again.</div>';
	}
	else if(results.userName == null){		
		str+='<div style="color:red"><b> Username Doesnot exist </b></div>';
	}
	else{
		//$("#forgot_password_window").dialog("destroy");
		afterPasswordSubmitToUser(results.userName,results.email);
		return;
	}
	errorDivEle.innerHTML = str;
	
}


function showEmailStatus(results)
{

	var errorDivEle = document.getElementById('ErrorMsgDivId');
	var str = '';
	
	if(results.resultCode == 0)
	{
		clearEmailFields();
		
	
		str += '<font color="#000000">Sending Your Request. Please wait...</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
		
	}
	else
		
		str += '<font color="red"><b>Error Ocuured, Try Again.</b></font>';

	errorDivEle.innerHTML = str;
	sendPasswordToUser();
}


function clearEmailFields()
{

	 document.getElementById('emailIdOfUser').value = '';

	
}

function afterPasswordSubmit(username,email){

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
		
	if(email == null)
	{
		str +='<div id="ErrorMsgDivId"></div>';
		str += 'Enter your Email :';
		str +='<input id="emailIdOfUser" type="text">';
		str += '<input type="hidden" id="hiddenUserId" value="'+username+'">';
		//str +='<input type="button"  value="submit" onclick="saveEmailAndSendPassword()" style="width:70px;margin-left: 8px;">';
		
		//str +='<button type="button" onclick="clearDialogBox()" style="margin-left:6px;">Cancel</button>';
		
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '<input id="submitButtonId" type="button" value="Submit"></input>';
		str += '<input type="button" id="cancelButtonId" value="Cancel"></input>';
		str +='</td>';
		str +='</tr>';
		str +='</div>';
		
		elmt.innerHTML = str;
		var oPushButton2 = new YAHOO.widget.Button("cancelButtonId");
		var oPushButton1 = new YAHOO.widget.Button("submitButtonId");
		
		oPushButton1.on("click",function(){
			saveEmailAndSendPassword();
		});
		
		oPushButton2.on("click",function(){
			$("#forgot_password_window").dialog("destroy");
		});
		
		
	}
	else
	{
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
}




function afterPasswordSubmitToUser(username,email){
	
		var elmt = document.getElementById("forgot_password_window_inner");
		var username = document.getElementById("");


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


function showForgotPasswordPanel()
{

	document.getElementById("validate").style.display = 'none';
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
 	
	if(name=="")
	{
		document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName field cannot be empty</font>";
 	}
	 else if (name.charAt(0).indexOf(" ")==0){
		 document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName Should not contain spaces</font>";
	 }
	 else if(name.length < 3)
	{
		document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName must be between 6 and 20 characters long.</font>";
	}
	else{ 
		var str = '<font color="#000000">Sending Your Request. Please wait...</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
 		document.getElementById("feedback_window_errorMsg").innerHTML = str;
 		var jsObj=
		{		
 				userName:name,
				task:"forgotPassword",
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "recoverPasswordAnanymousUserAction.action?"+rparam;						
		callAJAX(jsObj,url);
 	 }
}


function sendPasswordToUser()
{

	var usernameVal = document.getElementById('hiddenUserId').value;
	//var email = document.getElementById('textid').value;
	var jsObj=
	{
			userName  : usernameVal,
			task:"recoverPassword",

	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendPasswordAnanymousUserAction.action?"+rparam;						
		callAJAX(jsObj,url);
 	 }



function saveEmailAndSendPassword()
{
	var errorDivEle = document.getElementById('ErrorMsgDivId');

	var username = document.getElementById('hiddenUserId').value;
	var email = document.getElementById('emailIdOfUser').value;
	var eFlag = false;
	var str = '<font color="red">';
	if(email.length == 0)
	{
		str +='<b>enter Email</b>';
		eFlag = true;
	}
	var email = document.getElementById("emailIdOfUser").value;
		var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
      if(email !='' && email!='your email'){
          
		  if(!email.match(emailExp)){

				document.getElementById("ErrorMsgDivId").innerHTML = '<font color="red">Please enter valid Email</font>';
				return;
		  }
	  }
	 else {
		document.getElementById("ErrorMsgDivId").innerHTML ='<font color="red">Please enter Email id</font>';  
		return;
	 }

	str += '</font>';

	errorDivEle.innerHTML = str;
	if(eFlag)
		return;

	var jsObj = {

		userName : username,
		email : email,
		task : "saveUserEmailAndSendPwd",
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserEmailAction.action?"+rparam;						
	callAJAX(jsObj,url);

}
function clearDialogBox()
{
	document.getElementById('forgot_password_window').style.display = 'none';
    document.getElementById('forgot_password_window_inner').style.display = 'none';
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
	/*function hideRegisterDiv(){
	$('#registerDiv').hide('slow');

}	*/
</script> 

<script type="text/javascript">
	executeOnload();
if('<%=showMessage%>' == 'yes')
	document.getElementById('accessDiv').style.display = 'block';
</script>
</body>
</html>