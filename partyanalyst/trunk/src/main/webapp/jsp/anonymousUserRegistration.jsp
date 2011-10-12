<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//Problem Management
String redirectLoc = "";
String task = "";
String name = "";
String stateId = "";
String districtId = "";
String localBodyId = "";
String constituencyId = "";
String localBodyElectionTypeId = "";

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

<html>  
<head>  
<title> Registration</title>
	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

	<!-- YUI Dependency files (End) -->

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>
<link rel="stylesheet" type="text/css" href="styles/connectPeople/connectPeople.css">

<!-- JQuery files (End) -->
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>	
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>	
    <link rel="stylesheet" type="text/css" href="styles/jQuery/jquery.datepick.css">

<script type="text/javascript" src="js/jQuery/jquery.datepick.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.datepick-en-GB.js"></script>
<style type="text/css">
.yui-skin-sam .yui-panel 
		{
			background:#FFFFFF none repeat scroll 0 0;
			border-color:#808080;
			border-style:solid;
			border-width:1px 0;
			left:0;
			position:relative;
			top:0;
			z-index:1;
		}
.yui-skin-sam .yui-dt  {
    border: 2px solid #9696C0;
    border-collapse: separate;
    border-spacing: 0;
    font-family: arial;
    font-size: 12px;
    margin: 0;
    padding: 0;
    width: 100%;
}
		.yui-skin-sam .yui-dt th .yui-dt th a
		{
		background-image:url("images/YUI-images/sprite.png")
		}
		
		#yui-dt0-th-Categorize
		{
			background-color:blue;
		}
	
#searchDiv {
	
    -moz-border-radius-topleft: 14px;
    -moz-border-radius-topright: 14px;
    background: -moz-linear-gradient(center top , #73B8DB 0pt, #4393BB 100%) repeat scroll 0 0 transparent;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 0;
    color: #FFFFFF;
    font-size: 12px;
    font-weight: bold;
    height: 29px;
    padding-left: 12px;
    padding-top: 6px;
    width: 454px;
  }
.tdStyle {
    font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;
}
#problemDetails_body{
	border: 2px solid;
    font-size: 12px;
    margin-left: 75px;
    margin-right: 75px;
    margin-top: 59px;
}

#tableStyle{
	-moz-border-radius-topleft: 6px;
	-moz-border-radius-topright: 6px;
-moz-border-radius-bottomleft: 6px;
    -moz-border-radius-bottomright: 6px;
    background: none repeat scroll 0 0 #FFFFFF;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 1px;
}
#table1{
    -moz-border-radius-topleft: 9px;
    -moz-border-radius-topright: 9px;
    background: -moz-linear-gradient(center top , #73B8DB 0pt, #4393BB 100%) repeat scroll 0 0 transparent;
    color: #FFFFFF;
    margin-left: 1px;
    margin-top: 18px;
    padding: 8px;
	font-size: 12px;
    font-weight: bold;
    height: 29px;
    height: 20px;
    padding: 6px 8px 8px 10px;
}

.table1Style{
    -moz-border-radius-bottomleft: 6px;
    -moz-border-radius-bottomright: 6px;
    background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #DDDDDD;
    padding: 18px 100px 99px;
    height: 300px;
 }
#resultBtnId{
	background: -moz-linear-gradient(center top , #AFD47B 0pt, #4393BB 1px, #4393BB 1px, #4393BB 100%) repeat scroll 0 0 transparent;
	-moz-border-radius: 6px 6px 6px 6px;
    border-color: #669933;
    color: #FFFFFF !important;
    font-weight: bold;
    white-space: nowrap;
}
#btnStyle{
    background: -moz-linear-gradient(center top , #AFD47B 0pt, #AFD47B 1px, #8BC03F 1px, #69A219 100%) repeat scroll 0 0 transparent;
	-moz-border-radius: 6px 6px 6px 6px;
    border-color: #669933;
    color: #FFFFFF !important;
    font-weight: bold;
    white-space: nowrap;
}
.textFieldStyle{
   
    border-radius: 2px 2px 2px 2px;
	background: none repeat scroll 0 0 #FFFFFF;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 1px;
	height: 28px;
    padding-left: 8px;
    font: 12px/22px Arial,Helvetica,sans-serif;
	}
 #headerDiv{
   -moz-border-radius: 9px 9px 9px 9px;
    background: -moz-linear-gradient(center top , #F9F9F9 0pt, #E8E8E8 100%) repeat scroll 0 0 transparent;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 0;
    color: #666666;
    font-size: 20px;
    font-weight: bold;
    height: 33px;
    margin-top: 23px;
    padding-top: 10px;
    text-align: center;
    width: 210px;
 }
 #password-clear {
    display: none;
}
 #password-reClear {
    display: none;
}
  .calBtn
	{
		background-image: url("images/icons/constituencyManagement/calendar.jpeg");
		height: 24px;
		width: 24px;	
	}

   .dotline {
		border-top: 1px solid #D6D6D6;
		height: 1px;
		margin-bottom:10px !important; margin-bottom:0px;
		margin-top: 0.5em;
		width: 70%;
		
   }
	.headerDiv
	{
		border-bottom: 2px solid #B8C4D0;
		display: inline-block;
		margin-left: 20px;
		overflow: hidden;
		padding-top: 12px;
		width: 66.6%;
		
	}

	.headerDiv p 
	{
       color: #662986;
	   font-size: 160%;
	   padding-bottom: 10px;
	   text-align:center;
	}

	.row
	{
	   margin-bottom: 0.9em;
       position: relative;
	}

	.row label {
		display: inline-block;
		color:#926682;
		text-align: right;
		/*width: 13.8em;*/
		font-weight : bold;
    }
	
	.regTable td {
		color:#926682;
		font-family:verdana;
		font-weight:bold;
		text-align:right;
	}

	.label {
		color: #505050;
		font-size: 100%;
		padding-right: 4px;
    }

	#personalFieldsCollection {
		margin-top: 0.5em;
    }

   .fieldsCollection {
		margin: -1.3em 1.5em;
		position: relative;
    }
   fieldset, img {
		border: 0 none;
   }

   .fieldsCollection legend {
		color: #662986;
		font-size: 108%;
		font-weight: bold;
   }
	
   .freeUserRegDetailsTable
   {
	margin-left:70px;
   }

   .freeUserRegDetailsTable td
   {
	padding:5px;
   }
   .postedDiv_dataInfo_main
{
	border:1px solid #D0CCC4;
	margin:15px 0;
	padding:10px;
}
</style>
<script type="text/javascript"><!--
var userFirstName = '${sessionScope.USER.firstName}';
var userLastName = '${sessionScope.USER.lastName}';
var results='';	


var spvar = 'special';
var r={
  'special':/[\W]/g
  //'quotes':/['\''&'\"']/g,
  //'notnumbers':/[^\d]/g
}

function callAJAX(jsObj,url){
	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "checkAnanymousUserNameAvailability")
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
	
function checkAvailability()
{ 
	var name = document.getElementById("emailField").value;
 	var resultDIVEle = document.getElementById("mobileDiv");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(name.length == 0)
		resultDIVEle.innerHTML="<font color='red'>UserName Sholud Not Be Empty.</font>";
	
	else if(reg.test(name) == false)
		resultDIVEle.innerHTML = "<font color='red'>Please Provide Valid Email Address.</font>";
		
	else {		

 		document.getElementById("mobileDiv").innerHTML = " ";
 		var jsObj=
		{		
 				userName:name,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);
	}

}

function validUsername()
{
	var textEle = document.getElementById("userNameField");
	var resultDIVEle = document.getElementById("resultDIV");

	resultDIVEle.innerHTML = "";
	if(textEle.value.length > 0 && textEle.value.length < 6)
	{
		resultDIVEle.innerHTML = "<font color='red'>UserName must be between 6 and 20 characters long.</font>";
		return;
	}
		
	if(textEle.value.match(r[spvar]))
		resultDIVEle.innerHTML = "<font color='red'>UserName Should not contain Special Characters</font>";
	else
		resultDIVEle.innerHTML = "";
		
}
/* function validPassword()  
{  
	var textEle = document.getElementById("password-password");
	var resultDIVEle = document.getElementById("errMsg");
	resultDIVEle.innerHTML = "";
	
	if(textEle.value.length > 0 &&textEle.value.length < 6)
		resultDIVEle.innerHTML = "<font color='red'>Password Minimum Of 6 Characters.</font>";
		if(textEle.value==""){
         resultDIVEle.innerHTML="<font color='red'>Please Enter Password.</font>";
	     return false; 
	}
	else 
             return true; 

}
function validRePassword()
{
	var pwd1 = document.getElementById("password-password");
	var pwd2 = document.getElementById("password-rePassword");
	var resultDIVEle = document.getElementById("errMsg1");
	resultDIVEle.innerHTML = "";
	if(pwd1.value.length > 0 && pwd2.value.length > 0 && pwd1.value != pwd2.value){
		resultDIVEle.innerHTML = "<font color='red'>Passwords Do Not Match.</font>";
        return false;
	}
	if(pwd1.value.length > 0 && pwd1.value.length < 6)
	{
		resultDIVEle.innerHTML = "<font color='red'>Password Minimum Of 6 Characters.</font>";
		return false;
	}
	
		if(pwd2.value==""){
         resultDIVEle.innerHTML="<font color='red'>Please Re-enter Password.</font>";
	return false;
	}
	else 
             return true;
}  */

function validNames(id)
{
	var textEle = document.getElementById(id);
	var resultDIVEle = document.getElementById("namesDiv");
	var name = '';
	if(id == 'nameField')
		name = 'First name';
	else 
		name = 'Last name';

	resultDIVEle.innerHTML = "";
	if(textEle.value.match(r[spvar]))
		resultDIVEle.innerHTML = "<font color='red'>"+name+" Should not contain Special Characters</font>";
	else
		resultDIVEle.innerHTML = "";
		
}
function showDetails(results)
{  
	var textEle = document.getElementById("emailField");
	var result = document.getElementById("mobileDiv");
	var str='';
	if(results==121){		
		str+="<font color='green'> Username is available.</font>";
		result.innerHTML = str;
		return true;
	}else if(textEle.value == 'Username (Email)'){
		str+="<font color='red'>Please Enter Username.</font>";	
        result.innerHTML = str;
		return false;
		}
	else
	{
		str+="<font color='red'> Username is not available.</font>";	
	    result.innerHTML = str;
		return false;
	}
	result.innerHTML = str;
}
function validateMobile()
{
	var mobilEle = document.getElementById("mobileField");
	var resultDIVEle = document.getElementById("mobileFieldDiv");
	var mobile = mobilEle.value;

	resultDIVEle.innerHTML = "";
	if(mobilEle.value =="Mobile Number"){
			resultDIVEle.innerHTML = "";
              return true;
	}	
	if(isNaN(mobile)|| mobile.indexOf(" ")!=-1){
		resultDIVEle.innerHTML = "<font color='red'><b>Mobile Number Contains Only Numbers.</b></font>";
	   return false;
	}
	else if((mobile.length > 0 && mobile.length < 10) || mobile.length > 12){
		resultDIVEle.innerHTML = "<font color='red'><b>Please Provide Correct Mobile Number.</b></font>";
		return false;
	}
	else
		return true;
}

function validateAddress()
{
	var mobilEle = document.getElementById("addressField");
	var resultDIVEle = document.getElementById("addressFieldDiv");
	var mobile = mobilEle.value;

	resultDIVEle.innerHTML = "";
	
	if(mobilEle.value.match(r[spvar]))
		resultDIVEle.innerHTML = "<font color='red'>Address Should Not Contain Special Characters</font>";
	else
		resultDIVEle.innerHTML = "";
		
}
function validateFirstName()
{ 

	var fstEle = document.getElementById("firstNameId");
	var fstEleErr=document.getElementById("fstNameId");
	fstEleErr.innerHTML = "";
	
    if(fstEle.value== 'First Name'){
		fstEleErr.innerHTML = "<font color='red'>&nbsp;Please Enter First Name.</font>";
	    return false;
	}
	if(fstEle.value.match(r[spvar])){
		fstEleErr.innerHTML = "<font color='red'>Should Not Contain Special Characters</font>";
	    return false;
	}
	else 
             return true;
 }
function validateAccept(){
var acpt=document.getElementById("acceptId").checked;
acptErr=document.getElementById("acceptErrId");
if(!acpt)
	{
acptErr.innerHTML="<font color='red'>You Should Accept Terms of use</font>";
	return false;
	}
else 
	{
	acptErr.innerHTML="";
	return true;
	}
}
function validateLastName()
{
	var lstEle = document.getElementById("lastNameId");
	var lstEleErr=document.getElementById("lstNameId");
	lstEleErr.innerHTML = "";
    if(lstEle.value== 'Last Name'){
		lstEleErr.innerHTML = "<font color='red'>&nbsp;Please Enter Last Name.</font>";
	    return false;
	}
	if(lstEle.value.match(r[spvar])){
		lstEleErr.innerHTML = "<font color='red'>Should Not Contain Special Characters</font>";
		 return false;
	}
	
	else 
             return true;
}
function validateState()
{
	var lstElmt = document.getElementById("stateSelectBox");
	var lstEleErrs=document.getElementById("SelectState");
	lstEleErrs.innerHTML = "";

	if(lstElmt.value== '0'){
		lstEleErrs.innerHTML = "<font color='red'>&nbsp;Please Select State.</font>";
         return false;
}
else 
             return true;
}
function validateConstituency()
{
	var lstEle = document.getElementById("constituency");
	var lstEleErr=document.getElementById("selectConstituency");
	lstEleErr.innerHTML = "";

	if(lstEle.value== '0'||lstEle.value== ''){
		lstEleErr.innerHTML = "<font color='red'>&nbsp;Please Select Constituency.</font>";
		return false;
	}
	else 
             return true;

}

function validatefields()
{   
	var flag = true;
	 if($("#dateOfBirthField").val() =="DateOfBirth")
	   $("#dateOfBirthField").val('');
	  if($("#addressField").val() =="Address")
		  $("#addressField").val('');
	 if($("#mobileField").val() =="Mobile Number")
	$("#mobileField").val('');
	
	if(!validateFirstName())
		flag = false;
	if(!validateLastName())
		flag = false;
	<c:if test="${registrationId == '0'}">
	if(!validateEmail())
		flag = false;
	/*if(!validPassword())
		flag = false;
	if(!validRePassword())
		flag = false;*/
    if(!showDetails(results))
        flag = false;
	if(!validateAccept())
        flag = false;
	</c:if>
   <c:if test="${registrationId != '0'}">
    if(!validateMobile())
	    flag = false;
	</c:if>
	
	if(!validateState())
		flag = false;
	if(!validateConstituency())
		flag = false;
   
	 
	return flag;


}


function validateEmail()
{   
	var email = document.getElementById("emailField").value;
	var resultDIVEle = document.getElementById("mobileDiv");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(reg.test(email) == false)
		resultDIVEle.innerHTML = "<font color='red'>Please Provide Correct Email Address.</font>";
    else 
             return true;

	if(email.length == 0){
		resultDIVEle.innerHTML = "<font color='red'>&nbsp; UserName Should Not Be Empty.</font>";
        return false;
	}
	
	
}

function numbersonly(id){
	

	var num = document.getElementById(id).value;
	if(num !=''&& num!="Mobile Number"){
	 if(isNaN(num) || num.length<10){
		document.getElementById("numMsg").innerHTML ='<font color="red">Please enter valid Mobile Number</font>';
	}
	else{
		document.getElementById("numMsg").innerHTML ='';
	}
	}
	else{
		document.getElementById("numMsg").innerHTML ='';
	}
}
function removeTextInTextBoxes(id){

  var ids=document.getElementById(id);
   if(ids.value  =='Username (Email)' || 
	  ids.value =='Password' ||
	  ids.value =='State' || 
	  ids.value =='Constituency'||
	  ids.value =='First Name' ||
	  ids.value =='Re-Enter Password' ||
	  ids.value =='DateOfBirth'||
	  ids.value =='Address'||
	  ids.value =='Last Name' ||
      ids.value =='Mobile Number'){
       
		ids.value='';
	return;
  }
}
function showTextInTextBoxes(id){

   if($("#emailField").val() ==''){
		$("#emailField").val('Username (Email)');
	  } 
	  if($("#constituencyId").val() ==''){
		$("#constituencyId").val('Constituency');
	  }
	  if($("#mobileField").val() ==''){
		$("#mobileField").val('Mobile Number');
	  }
	  if($("#firstNameId").val() =='' ){
		 $("#firstNameId").val('First Name');
	  }
	 if($("#stateId").val() ==''){
		 $("#stateId").val('State');
	 }
	 if($("#lastNameId").val() ==''){
         $("#lastNameId").val('Last Name');
	 }
	  if($("#reEnterPasswordId").val() ==''){
         $("#reEnterPasswordId").val('Re-Enter Password');
	 }
	 if($("#dateOfBirthField").val() ==''){
         $("#dateOfBirthField").val('DateOfBirth');
	 }
   
	 if($("#addressField").val() ==''){
         $("#addressField").val('Address');
	 }
      
}

--></script>

</head>  
<body>  

<s:form action="anonymousUserRegistrationAction.action" method="POST" theme="simple" enctype="multipart/form-data" onsubmit="return validatefields()">  
   <br><br>
  <div id="registrationMainDiv" style="padding-bottom:5px;">
		<table class="registrationTable">
			<tr>
				<td colspan="2">
					<div style="color: red;font-weight:bold;" id="errorMessageDiv">
						<s:actionerror />
						<s:fielderror />
						<s:actionmessage/>						
					</div>
				</td>
			</tr>
		</table></div>
<c:if test="${registrationId == '0'}">
<div id="tableStyle" style="width:870px;height:530px;"></c:if>
<c:if test="${registrationId != '0'}">
<div id="tableStyle" style="width:870px;height:495px;"></c:if>
<div style="margin:0px 0px 0px 20px !important; margin:0px 0px 0px 10px; padding:0px 0px 0px 0px; float:left; width:847px;">
<table>
<tr>
<td>
<table width="100%">
<tr>
<td width="250">
<c:if test="${registrationId == '0'}">
<img src="images/icons/homePage_new/freeRegPage.jpg" width="250" height="192" />
</c:if>
 <c:if test="${registrationId != '0'}">
<img src="images/icons/homePage_new/update.jpg" width="250" height="192" /></c:if>
</td>
<td width="80">&nbsp;</td>
<td width="515" align="left">
<table cellspacing="1px" cellpadding="5px" >
 <tr>
 <c:if test="${registrationId == '0'}">
 <td><strong style="color:red;margin">REGISTER</strong><span style="padding-left:30px"> Fields marked with (<b style="color:red">*</b>) are mandatory</span></td></tr>
 <tr>
 <tr>
 <td>
Your password will be sent to this Email address
 </td>
 </tr>
 <td style="font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;"><b style="color:red">*</b> 
 <s:textfield theme="simple" name="email" value="Username (Email)" id="emailField" style=" border-radius: 2px 2px 2px 2px;background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" size="30" onClick="removeTextInTextBoxes(this.id)" onBlur="validateEmail(),checkAvailability(),showTextInTextBoxes(this.id)"/>
 <span id="mobileDiv" style="padding-left:10px;font-weight:lighter"></span>
  </td>
  
  </tr>
  <!--
  <tr>
  <td class="tdStyle"><b style="color:red">*</b> 
 <s:textfield id="password-clear" cssClass="textFieldStyle" value="Password" size="30px" />
  <s:password name="password" value="Password" id="password-password" cssClass="textFieldStyle" size="30px" onBlur="validPassword()" theme="simple"/><span id="errMsg" style="padding-left:10px;font-weight:lighter">
  </span>
  </td>
  </tr>
  <tr>
  <td class="tdStyle"><b style="color:red">*</b> 
  <s:textfield id="password-reClear" cssClass="textFieldStyle" value="Re-Enter Password" size="30px" />
    <s:password name="reEnteredPassword" value="Re-Enter Password" id="password-rePassword" cssClass="textFieldStyle" onBlur="validRePassword()" size="30px" theme="simple"/><span id="errMsg1" style="padding-left:10px;font-weight:lighter">
  </span>
  </td>
   </tr>
  -->
  <tr>
  <td style="font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;"><b style="color:red">*</b> 
  <s:textfield name="firstName" value="First Name" id="firstNameId" style=" border-radius: 2px 2px 2px 2px;background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" size="30" onClick="removeTextInTextBoxes(this.id)" theme="simple" onBlur="showTextInTextBoxes(this.id),validateFirstName()"/>
  <span id="fstNameId" style="padding-left:10px;font-weight:lighter">
  </span>
  </td>
  
  </tr>
  <tr>
  <td style="font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;"><b style="color:red">*</b> 
  <s:textfield name="lastName" value="Last Name" id="lastNameId" style=" border-radius: 2px 2px 2px 2px;background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;	height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" size="30"  onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id),validateLastName()" theme="simple"/>
  <span id="lstNameId" style="padding-left:10px;font-weight:lighter">
  </span>
  </td>
  </tr>
  </c:if>
  <c:if test="${registrationId != '0'}">
  <td>
  <strong style="color:red">Account Information</strong><span style="padding-left:30px"> Fields marked with (<b style="color:red">*</b>) are mandatory</span> 
  </td>
  </tr>
  <tr>
  <td style="font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;"><b style="color:red">*</b> 
  <s:textfield name="firstName"  id="firstNameId" cssClass="textFieldStyle" style=" border-radius: 2px 2px 2px 2px;	background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" size="30" onClick="removeTextInTextBoxes(this.id)" theme="simple" onBlur="showTextInTextBoxes(this.id),validateFirstName()"/>
  <span id="fstNameId" style="padding-left:10px;font-weight:lighter">
  </span>
  </td>
  </tr>
  <tr>
  <td class="tdStyle"><b style="color:red">*</b> 
  <s:textfield name="lastName" id="lastNameId" cssClass="textFieldStyle" style=" border-radius: 2px 2px 2px 2px;background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id),validateLastName()" size="30" theme="simple"/>
  <span id="lstNameId" style="padding-left:10px;font-weight:lighter">
  </span>
  </td>
  </tr>
  <tr>
  <td style="padding-left:18px"><s:textfield id="dateOfBirthField"  name="dateOfBirth" readOnly="true" size="30" style=" border-radius: 2px 2px 2px 2px;background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" theme="simple" cssClass="textFieldStyle" onBlur="showTextInTextBoxes(this.id)" onclick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)"/>
    <input id="dateOfBirthField" type="button" class="calBtn" title="Click To Select A Date" size="30px"  theme="simple" cssClass="textFieldStyle" onclick="focusCalTextElmt(this.id)"/>
</td>


  </tr>
  <tr>
  <td style="padding-left:18px"><s:textfield id="mobileField" name="mobile" maxlength="12" onBlur="showTextInTextBoxes(this.id),validateMobile()" onclick="removeTextInTextBoxes(this.id)" style=" border-radius: 2px 2px 2px 2px;background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" size="30" theme="simple" cssClass="textFieldStyle"/>
  <span id="mobileFieldDiv" style="padding-left:10px;font-weight:lighter">
  </span></td>
  </tr>
  <tr>
  <td style="padding-left:18px"><s:radio id="genderField" name="gender" list="#session.gender"/> </td>	
  </tr>
  <tr>
  <td style="padding-left:18px"><s:textfield id="addressField" name="address" cssClass="textFieldStyle" style=" border-radius: 2px 2px 2px 2px;	background: none repeat scroll 0 0 #FFFFFF;border-color: #DDDDDD;border-style: solid;border-width: 1px 1px 1px;height: 28px;padding-left: 8px;font: 12px/22px Arial,Helvetica,sans-serif;" size="30" theme="simple" onBlur="showTextInTextBoxes(this.id),validateAddress()" onclick="removeTextInTextBoxes(this.id)"/>
  <span id="addressFieldDiv" style="padding-left:10px;font-weight:lighter">
  </span></td>
  </tr>
  </c:if>
  <tr>
  <td class="tdStyle"><b style="color:red">*</b> 
   <s:select name="state" id="stateSelectBox" cssClass="textFieldStyle" headerKey="0" headerValue="Select State" list="#session.states" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')" cssStyle="width:189px;"  theme="simple" /><span id="SelectState" style="border-radius: 2px 2px 2px 2px;padding-left:10px;font-weight:lighter"></span>
  </td>

  </tr><tr>
  <td class="tdStyle"><b style="color:red">*</b> 
  <s:select name="constituency" id="constituency"  cssClass="textFieldStyle" headerKey="0" headerValue="Select Constituency" list="#session.constituencies" listKey="id" listValue="name" cssStyle="width:189px;" theme="simple" /><span id="selectConstituency" style="border-radius: 2px 2px 2px 2px;padding-left:10px;font-weight:lighter"></span>
  </td>

  </tr>

</table>
</td>
</tr>

 </table>
 <c:if test="${registrationId == '0'}">
<table style="margin-left:253px !important; margin-left:20px;">
	</c:if>
<c:if test="${registrationId != '0'}">
<table  style="padding-left:80px">
	</c:if>
  <tr>
  <td>
        <div style="margin:5px;margin-left:26px" id="acceptErrId"></div>
		<div style="margin:5px; margin:0px 0px 0px 90px !important; margin:0px 0px 0px 140px; font:11px Arial,Helvetica,sans-serif;">
			<c:if test="${registrationId == '0'}">
			 <s:checkbox name="accept" id="acceptId"/>
			I accept <a target="_blank" href="footerLinksAction.action?linkFrom=termsOfUse#termsOfUse"> Terms of use </a> &nbsp; and <a target="_blank" href="footerLinksAction.action?linkFrom=privacy#privacyPolicy">Privacy policy</a> of the Website
		</div>
		<FIELDSET id="personalFieldsCollection" class="fieldsCollection">
		<div class="dotline" style="margin:0px 0px 0px 62px !important; margin:0px 0px 0px 125px;"></div>
        <div style="margin-left:7px; width:300px; height:50px; float:left;">
		<div style="float:left; margin:0px 0px 0px 62px !important; margin:0px 0px 0px 60px;">
		<div style="font-size:12px; float:left; font-weight:bold;">Do you want to</div>
		<br>
		 <div id="contactDetailsDiv" style="font:11px Arial,Helvetica,sans-serif;">
					
			<s:checkboxlist list="#session.profileOpts" labelposition="top" theme="vertical-checkbox" listKey="id" listValue="name" name="profileOpts"/>
			<div style="padding-top:15px;">
				<input type="image" src="images/icons/homePage_new/createbutton.jpg"/>
			</div>
			</c:if>
			<c:if test="${registrationId != '0'}">
			<div style="margin:0px 0px 0px 183px !important; margin:0px 0px 0px 0px;">
				<input type="image" src="images/icons/homePage_new/updatebutton.jpg"/>
						</div>
			</c:if>
		 </div>  
		</div>
		</div>

		
		 

  </td>
  </tr>
 </table>
</td>
</tr>
</table>
</td>
</tr>
</table>
</div>

 
</div>
         <!--  Problem Management Params -->
         <input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
		 <input type="hidden" name="task" value="<%=task %>" />
		 <input type="hidden" name="name" value="<%=name %>" />
		 <input type="hidden" name="registrationId" value="${registrationId }" />
		 <input type="hidden" name="stateId" value="<%=stateId %>" />
		 <input type="hidden" name="districtId" value="<%=districtId %>" />
		 <input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
		 <input type="hidden" name="constituencyId" value="<%=constituencyId %>"
		 />
         
		 <input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />  </s:form> 
	
<script language="javascript">

//document.getElementsByName("gender")[0].checked = true;
//document.getElementsByName("accept")[0].checked = true;

</script>
<script>
$(document).ready(function() {
 
	$('#password-clear').show();
	$('#password-password').hide();
 
	$('#password-clear').focus(function() {
		$('#password-clear').hide();
		$('#password-password').show();
		$('#password-password').focus();
	});
	$('#password-password').blur(function() {
		if($('#password-password').val() == '') {
			$('#password-clear').show();
			$('#password-password').hide();
		}
	});
 
	$('.default-value').each(function() {
		var default_value = this.value;
		$(this).focus(function() {
			if(this.value == default_value) {
				this.value = '';
			}
		});
		$(this).blur(function() {
			if(this.value == '') {
				this.value = default_value;
			}
		});
	});
 if($("#dateOfBirthField").val() ==''){
         $("#dateOfBirthField").val('DateOfBirth');
	 }
   
	 if($("#addressField").val() ==''){
         $("#addressField").val('Address');
	 }
	 if($("#mobileField").val() ==''){
		$("#mobileField").val('Mobile Number');
	  }
	 
});
</script>
<script>
$(document).ready(function() {
 
	$('#password-reClear').show();
	$('#password-rePassword').hide();
 
	$('#password-reClear').focus(function() {
		$('#password-reClear').hide();
		$('#password-rePassword').show();
		$('#password-rePassword').focus();
	});
	$('#password-rePassword').blur(function() {
		if($('#password-rePassword').val() == '') {
			$('#password-reClear').show();
			$('#password-rePassword').hide();
		}
	});
 
	$('.default-value').each(function() {
		var default_value = this.value;
		$(this).focus(function() {
			if(this.value == default_value) {
				this.value = '';
			}
		});
		$(this).blur(function() {
			if(this.value == '') {
				this.value = default_value;
			}
		});
	});
 
});
 
</script>
 



</body>  
</html>