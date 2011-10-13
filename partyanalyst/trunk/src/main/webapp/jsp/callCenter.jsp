<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js">
</script> 
<script
	src="js/jQuery/jquery-ui.min.js">
</script>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/jquery-ui-1.8.14.custom.css" />
<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/demos.css" />
<link rel="stylesheet" type="text/css" href="styles/mandalPage/mandalPage.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<!-- JQuery files (End) -->
<title>Call Center</title>
<style type="text/css">

.ui-widget-header {
    background: url("images/ui-bg_highlight-soft_75_cccccc_1x100.png") repeat-x scroll 50% 50% #CCCCCC;
    border: 1px solid #AAAAAA;
    color: #222222;
    font-weight: bold;
}
.yui-skin-sam .yui-dt-liner {
    padding: 4px 8px;
}
td.tdStyle{
    font-family: times New Roman;
    font-size: 16px;
    font-weight: bold;
}
 #callTrackingCurrentDiv
        {          
          font-size: 12px;
           width: 800px;
        }
    th
        {
		  margin-left: 30px;  
		  font-weight: bold; 
		  font-size: 12px; 
		  text-align: left;
        }
.tdStyle {
    font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;
	
}
#problemDetails_body{
	
    font-size: 12px;
    margin-left: 139px;
    margin-right: 139px;
    margin-top: 59px;
}
.textFieldStyle{
    
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 1px;
	height: 28px;
    padding-left: 8px;

}
 #headerDiv{
  background-image:url("images/icons/CallCenterHeader.jpg");
   border-width:1px 1px 0;
  color:Navy;
  font-size:20px;
  font-weight:bold;
  height:45px;
  margin-top:20px;
  padding-top: 23px;
  text-align:center;
  width:240px;
 }
  .yui-skin-sam.yui-dt table{
	border: 1px solid #CCCCCC;
    width: 100%;
 
 }
 .yui-skin-sam.yui-dt table th{
	background: url("images/ui-bg_highlight-soft_75_cccccc_1x100.png") repeat-x scroll 50% 50% #EEEEEE;
     
 }
 #showProblemCountDiv{
     text-align:right;
	 align:right;
	 padding-right:40px;
 }
 #showCallAddedResult{
     text-align:left;
	 align:left;
	 padding-left:50px;
	 font-weight: bold; 
	 font-size: 13px;
 }
 .ui-widget-header .ui-icon {
   background-image: url("images/ui-icons_222222_256x240.png");
}

.ui-widget-content .ui-icon {
    background-image: url("images/ui-icons_222222_256x240.png");
}

.quickbg13{
background:#FFFFFF; 
width:283px; 
height:235px; 
border-left:#dddddd solid 1px; 
border-right:#dddddd solid 1px;
}

.quickbg12{
	margin:0px;
	padding:0px;
	list-style-type:none;
}

.quickbg12{
margin:0px;
padding:0px 0px 0px 10px;
font-size:12px;
color:#669900;
line-height:60px;
font-family:Verdana, Arial, Helvetica, sans-serif;
list-style-type:none;
font-style:normal;
font-weight:bold;
text-decoration:none;
}

.quickbg12 a{
margin:0px;
padding:0px 0px 0px 10px;
font-size:12px;
color:#669900;
line-height:60px;
font-family:Verdana, Arial, Helvetica, sans-serif;
list-style-type:none;
font-style:normal;
font-weight:bold;
text-decoration:none;
}

.quickbg12 a:hover{
text-decoration:underline;
}

.formbg{
margin:9px 150px 9px 0px !important; margin:9px 130px 9px 0px;
background:#FFFFFF;
float:left;
font-family:Verdana, Arial, Helvetica, sans-serif;
font-size:13px;
color:#000000;
border:1px solid #dddddd;
width:210px;
height:30px;
}

.formbg_1{
margin:9px 3px 9px 0px !important; margin:9px 3px 9px 0px;
background:#FFFFFF;
float:left;
font-family:Verdana, Arial, Helvetica, sans-serif;
font-size:13px;
color:#000000;
border:1px solid #dddddd;
width:210px;
height:30px;
}



.formbg12{
margin:5px 42px 0px 0px !important; margin:5px 42px 10px 0px;
padding:0px;
background:#FFFFFF;
float:left;
font-family:Verdana, Arial, Helvetica, sans-serif;
font-size:13px;
color:#000000;
border:1px solid #dddddd;
width:210px;
height:30px;
}

.formbg13{
margin:5px 0px 0px 0px !important; margin:5px 10px 0px 0px;
padding:0px;
background:#FFFFFF;
float:left;
font-family:Verdana, Arial, Helvetica, sans-serif;
font-size:13px;
color:#000000;
border:1px solid #dddddd;
width:210px;
height:30px;
}

</style>
</head>

<script type="text/javascript">	
var timeST = new Date().getTime();
  function clearAll(){
      document.getElementById("name1").value='';
	  document.getElementById("mobile").value='';
	  document.getElementById("referenceNo").value='';
	  document.getElementById("villageTown").value='';
	  document.getElementById("errorNameDiv").innerHTML='';
	   document.getElementById("errorMobileDiv").innerHTML='';
	   document.getElementById("errorProblemPurposeDiv").innerHTML='';
	   document.getElementById("errorRefDiv").innerHTML='';
	   document.getElementById("errorVillageDiv").innerHTML='';
  }
function showAddedResult(result){
 var obj = document.getElementById("showCallAddedResult");
    
    if(result[0].status ==1){
       obj.innerHTML='<font color="green">CallDetails Added Successfully</font>';
	  setTimeout('hideMessage()',15000);
	}
}
function hideMessage(){
      document.getElementById("showCallAddedResult").innerHTML='';
}
function showResultMessage(){
  document.getElementById("updateProblemDiv").innerHTML ='<font color="green">Updated Successfully</font>';
}
function showCallTrackingEditWindow(result){
	var resultDiv = document.getElementById("editCallTrackingProblem");
	

	$("#editCallTrackingProblem").dialog({ stack: false,
							    height: 150,
								width: 700,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">Edit Call Details</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#editCallTrackingProblem").dialog();
								
	var str = '';
	str += '<table>';
	str +=  '<tr>';
	str +=  '<div id="updateProblemDiv" />';
	str +=  '</tr>';
    str +=  '<tr>';
    str +=   '<th>Name</th>';
	str +=   '<th>Mobile No</th>';
	str +=   '<th>Call Purpose</th>';	  
	str +=   '<th>Reference No</th>';
	str +=   '<th>Action Taken</th>';
    str +=  '</tr>';
	str +=  '<tr>';
	str +=    '<td><input type="text" size="15" class="textFieldStyle"  style="height:23px;" id="name2"/></td>';
	str +=    '<td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="mobile1"/></td>';
	str +=   '<td>';
	str +=      '<select style="width:175px;" class="textFieldStyle" style="height:23px;" id="problemPurpose1" >';
    str +=        '<option>All</option>';
	str += 		  '<option value="Appointment Cancellation">Appointment Cancellation</option>';
	str += 		  '<option value="Appointment Fixing">Appointment Fixing</option>';
	str += 		  '<option value="For Information">For Information</option>';	
	str +=        '<option value="Problem Reporting">Problem Reporting</option>';
	str += 		  '<option value="Problem Status Enquiry">Problem Status Enquiry</option>';	
	str += 		  '<option value="Others">Others</option>';
	str += 	    '</select>';
	str +=    '</td>';	   
	str +=    '<td><input type="text" size="15" class="textFieldStyle" readonly="readonly" style="height:23px;" id="referenceNo1" /></td>';
	str +=    '<td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="villageTown1"/></td>';
	str +=    '<td><img onclick="updateCallTrackingProblem();" src="images/update_button.jpg"/></td>';
	str +=    '<td><input type="hidden"  id="problemId1" /></td>';
	str +=  '</tr>';
	str +=  '<tr>';
	str +=    '<td><div id="errorNameDiv1"></div></td>';
	str +=    '<td><div id="errorMobileDiv1"></div></td>';
	str +=    '<td><div id="errorProblemPurposeDiv1"></div></td>';
	str +=    '<td><div id="errorRefDiv1"></div></td>';
	str +=    '<td><div id="errorVillageDiv1"></div></td>';
	str +=  '</tr>';
    str += '</table>';
  resultDiv.innerHTML = str;
populateDataToEditCallTracking(result);
  
  }
 function validateForUpdate(){
     var val = 0;	    	   	   	   	   	   	   	   	  	   	   
	   document.getElementById("errorNameDiv1").innerHTML='';
	   document.getElementById("errorMobileDiv1").innerHTML='';
	   document.getElementById("errorVillageDiv1").innerHTML='';
	   document.getElementById("errorProblemPurposeDiv1").innerHTML='';
       var name = document.getElementById("name2").value;
	   var mobile = document.getElementById("mobile1").value;
	   var villageTown = document.getElementById("villageTown1").value;
	   var problemPurposeEle = document.getElementById("problemPurpose1");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
       if(name.length == 0)
	{
		document.getElementById("errorNameDiv1").innerHTML ='<font color="red">Name is Required</font>';
		val = 1;
	}
	if(mobile.length == 0)
	{
		document.getElementById("errorMobileDiv1").innerHTML = '<font color="red">MobileNo is Required</font>';
		val = 1;
	}
	if(mobile.length != 0){
       if(isNaN(mobile) || mobile.length<10 || mobile.length>10 || !(mobile.charAt(0)=="9" || mobile.charAt(0)=="8" || mobile.charAt(0)=="7")){
		document.getElementById("errorMobileDiv1").innerHTML = '<font color="red">Please enter valid Mobile <br /> Number</font>';
		val = 1;
	    }
	 }
	 if(problemPurpose =="All"){
		 document.getElementById("errorProblemPurposeDiv1").innerHTML = '<font color="red">Please Select Call Purpose</font><BR/>';
		val = 1;
	    }
	
	
	return val;
 }
 function  populateDataToEditCallTracking(result){
       document.getElementById("name2").value = result[0].name;
       document.getElementById("mobile1").value = result[0].mobile;
       document.getElementById("problemPurpose1").value = result[0].problemPurpose;
       document.getElementById("referenceNo1").value = result[0].referenceNo;
       document.getElementById("villageTown1").value = result[0].villageOrTown;
	   document.getElementById("problemId1").value = result[0].problemId;
 }
function updateCallTrackingProblem(){
       if(validateForUpdate()!=1){
      var name = document.getElementById("name2").value;
	  var mobile = document.getElementById("mobile1").value;
	  var problemPurposeEle = document.getElementById("problemPurpose1");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
	  var referenceNo = document.getElementById("referenceNo1").value;
	  var villageTown = document.getElementById("villageTown1").value;
	  var problemId = document.getElementById("problemId1").value;
      var jsObj=
	      {	
		    problemId:problemId,
			name: name,
			mobile: mobile,
			problemPurpose: problemPurpose,
			referenceNo:referenceNo,
			villageOrTown:villageTown,
				time : timeST,
			task: "updateCallTrackingProblem"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
	 }
 }
function setReferenceNo(refNo){
  document.getElementById("referenceNo").value = refNo;
}
function searchCallTrackingProblem(){
       document.getElementById("errorNameDiv").innerHTML='';
	   document.getElementById("errorMobileDiv").innerHTML='';
	   document.getElementById("errorProblemPurposeDiv").innerHTML='';
	   document.getElementById("errorRefDiv").innerHTML='';
	   document.getElementById("errorVillageDiv").innerHTML='';
      var name = document.getElementById("name1").value;
	  if(name.length<=0)
         name ='';
	  var mobile = document.getElementById("mobile").value;
	  if(mobile.length<=0)
         mobile ='';
	  var problemPurposeEle = document.getElementById("problemPurpose");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
	  var referenceNo = document.getElementById("referenceNo").value;
	  if(referenceNo.length<=0)
         referenceNo ='';
	  var villageTown = document.getElementById("villageTown").value;
	  if(villageTown.length<=0)
         villageTown ='';
	  if(problemPurpose =="All")
	     problemPurpose = '';
	 
      var jsObj=
	      {				
			name: name,
			mobile: mobile,
			problemPurpose: problemPurpose,
			referenceNo:referenceNo,
			villageOrTown:villageTown,
			time : timeST,
			task: "searchCallTrackingProblem"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
}
function validatePhoneNo(){
  document.getElementById("errorMobileDiv").innerHTML='';
  var mobile = document.getElementById("mobile").value;
  if(mobile.length == 0)
	{
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">MobileNo is Required</font>';
		val = 1;
	}
	if(mobile.length != 0){
       if(isNaN(mobile) || mobile.length<10 || mobile.length>10 || !(mobile.charAt(0)=="9" || mobile.charAt(0)=="8" || mobile.charAt(0)=="7")){
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Please enter valid Mobile <br /> Number</font>';
		val = 1;
	    }
	 }
  
}
function validate(){
       var val = 0;	    	   	   	   	   	   	   	   	  	   	   
	   document.getElementById("errorNameDiv").innerHTML='';
	   document.getElementById("errorMobileDiv").innerHTML='';
	   document.getElementById("errorProblemPurposeDiv").innerHTML='';
	   document.getElementById("errorRefDiv").innerHTML='';
	   document.getElementById("errorVillageDiv").innerHTML='';
       var name = document.getElementById("name1").value;
	   var mobile = document.getElementById("mobile").value;
	   var problemPurposeEle = document.getElementById("problemPurpose");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
	   var referenceNo = document.getElementById("referenceNo").value;
	   var villageTown = document.getElementById("villageTown").value;
       if(name.length == 0)
	{
		document.getElementById("errorNameDiv").innerHTML ='<font color="red">Name is Required</font>';
		val = 1;
	}
	if(mobile.length == 0)
	{
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Mobile No is Required</font>';
		val = 1;
	}
	if(mobile.length != 0){
       if(isNaN(mobile) || mobile.length<10 || mobile.length>10 || !(mobile.charAt(0)=="9" || mobile.charAt(0)=="8" || mobile.charAt(0)=="7")){
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Please Enter Valid Mobile<br /> Number</font>';
		val = 1;
	    }
	 }
	if(problemPurpose =="All"){
		 document.getElementById("errorProblemPurposeDiv").innerHTML= '<font color="red">Please Select Call Purpose</font>';
		 val = 1;
	}
	if(problemPurpose=="Problem Reporting"){
	
	    if(referenceNo.length == 0)
	    {
		 document.getElementById("errorRefDiv").innerHTML = '<font color="red">ReferenceNo is Required</font><BR/>';
		val = 1;
	    }
	}
	
	
	return val;
}

function validateForAddProblem(){
       var val = 0;	    	   	   	   	   	   	   	   	  	   	   
	   document.getElementById("errorNameDiv").innerHTML='';
	   document.getElementById("errorMobileDiv").innerHTML='';
	   document.getElementById("errorProblemPurposeDiv").innerHTML='';
	   document.getElementById("errorRefDiv").innerHTML='';
	   document.getElementById("errorVillageDiv").innerHTML='';
       var name = document.getElementById("name1").value;
	   var mobile = document.getElementById("mobile").value;
	   var problemPurposeEle = document.getElementById("problemPurpose");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
	   var villageTown = document.getElementById("villageTown").value;
       if(name.length == 0)
	{
		document.getElementById("errorNameDiv").innerHTML ='<font color="red">Name is Required</font>';
		val = 1;
	}
	if(mobile.length == 0)
	{
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Mobile No is Required</font>';
		val = 1;
	}
	if(mobile.length != 0){
       if(isNaN(mobile) || mobile.length<10 || mobile.length>10 || !(mobile.charAt(0)=="9" || mobile.charAt(0)=="8" || mobile.charAt(0)=="7")){
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Please Enter valid Mobile<br /> Number</font>';
		val = 1;
	    }
	 }
	if(problemPurpose =="All"){
		 document.getElementById("errorProblemPurposeDiv").innerHTML= '<font color="red">Please Select Call Purpose</font>';
		 val = 1;
	}
	
	return val;
}
function addProblem(problemId){
 var browser = 
window.open("addNewProblemAction.action?callTrackingProblemId="+problemId,"addCallTrackingProblem","scrollbars=yes,height=600,width=600,left=200,top=200");		 
		 browser.focus();
}
function addCallTrackingProb(){
    var refNo = document.getElementById("referenceNo").value;
	document.getElementById("errorRefDiv").innerHTML='';
	if(refNo.length != 0){
	document.getElementById("errorRefDiv").innerHTML='<font color="red">ReferenceNo must be empty</font>';
	}
if(refNo.length <= 0){
  if(validateForAddProblem()!=1){
      var name = document.getElementById("name1").value;
	  var mobile = document.getElementById("mobile").value;
	  var problemPurposeEle = document.getElementById("problemPurpose");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
	  var villageTown = document.getElementById("villageTown").value;
	  var url = 'addNewProblemAction.action?callTrackingName='+name ;
	     url = url+'&mobile='+mobile+'&problemPurpose='+problemPurpose+'&villageTown='+villageTown+'&callTrackProb=callTracking';
      var browser = window.open(url,"addCallTrackingProblem","scrollbars=yes,height=600,width=600,left=200,top=200");		 
		 browser.focus();
	}
  }
}

 function editProblem(problemId){
   var jsObj=
	      { 
		    ProblemId:problemId,
			time : timeST,
			task: "getCallTrackingProblemByProblemId"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
   
  }
 function getCurrentDayCallTrackingProblem(){
   document.getElementById("callTrackingTotalCountDiv").innerHTML='';
     var jsObj=
	      {
		    time : timeST,
			task: "getCurrentDayCallTrackingProblem"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
	}
function callForEveryMinute(){
      getCurrentDayProblemCount();
     setTimeout('callForEveryMinute()',60000);
}
 function getCurrentDayProblemCount(){
     var jsObj=
	      {
		    time : timeST,
			task: "getCurrentDayProblemCount"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
	}
function saveCallTrackingProblem(){
        if(validate()!=1){
      var name = document.getElementById("name1").value;
	  var mobile = document.getElementById("mobile").value;
	  var problemPurposeEle = document.getElementById("problemPurpose");
	  var problemPurpose = problemPurposeEle.options[problemPurposeEle.selectedIndex].value ;
	  var referenceNo = document.getElementById("referenceNo").value;
	  var villageTown = document.getElementById("villageTown").value;
	  if(referenceNo.length <= 0)
	    referenceNo='';
	  if(villageTown.length <= 0)
	     villageTown='';
      var jsObj=
	      {				
			name: name,
			mobile: mobile,
			problemPurpose: problemPurpose,
			referenceNo:referenceNo,
			villageOrTown:villageTown,
			time : timeST,
			task: "saveCallTrackingProblem"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
	  }
    }

function validateEmail(id){
	
	var email=document.getElementById("emailId").value;
	var emailFilter=/^.+@.+\..{2,3}$/
	if(email!="" && email!="Email Id"){
		if(!emailFilter.test(email)){
			document.getElementById("emailErrMsg").innerHTML = 'Please enter valid Email';
		}
	else{
		document.getElementById("emailErrMsg").innerHTML ='';
		}
	}
	else{
		document.getElementById("emailErrMsg").innerHTML ='';
		}
}
function numbersonly(id){
	var num = document.getElementById(id).value;
	if(num !=''&& num!="Mobile Number"){
	 if(isNaN(num) || num.length<10){
		document.getElementById("errMsg").innerHTML ='Please Enter Valid Mobile No';
	}
	else{
		document.getElementById("errMsg").innerHTML ='';
	}
	}
	else{
		document.getElementById("errMsg").innerHTML ='';
	}
}
function removeTextInTextBoxes(id){

  if($("#"+id).val() =="Name" || 
	  $("#"+id).val() =="Mobile Number" ||
	  $("#"+id).val() =="Email Id" || 
	  $("#"+id).val() =="Problem Reference"||
	  $("#"+id).val() =="From Date" ||
	  $("#"+id).val() =="To Date"){
    $("input#"+id).val('');
	return;
  }
}
function showTextInTextBoxes(id){

   if($("#nameId").val() ==''){
		$("#nameId").val('Name');
	  } 
	  if($("#refId").val() ==''){
		$("#refId").val('Problem Reference');
	  }
	  if($("#mobileNumId").val() ==''){
		$("#mobileNumId").val('Mobile Number'); 
	  }
	  if($("#emailId").val() ==''){
		 $("#emailId").val('Email Id');
	  }
	 if($("#fromDate").val() ==''){
		 $("#fromDate").val('From Date');
	 }
	 if($("#endDate").val() ==''){
         $("#endDate").val('To Date');
	 }
}

function getProblemDetails(){
    var name;
    var refNum;
    var mobileNum;
    var emailId;
    var fromDate;
	var endDate;

     if($("#nameId").val() =="Name"){
		name = '';
	  } 
	  else
	  name = $("#nameId").val();
	  
	  if($("#refId").val() =="Problem Reference"){
		refNum ='';
	  }
	  else
	  refNum=$("#refId").val();
	  
	  if($("#mobileNumId").val() =="Mobile Number"){
		 mobileNum ='';
	  }
	  else
	     mobileNum = $("#mobileNumId").val();
	  
	  if($("#emailId").val() =="Email Id"){
		 emailId ='';
	  }
	  else
	     emailId = $("#emailId").val();
	  
	 if($("#fromDate").val() =="From Date"){
		 fromDate ='';
	 }
	 else
	    fromDate = $("#fromDate").val();
	 
	 if($("#endDate").val() =="To Date"){
         endDate ='';
	 }
	 else
	    endDate = $("#endDate").val();
	 
	 

	  var jsObj = {
		name:name,
		refNum:refNum,
		mobileNum:mobileNum,
        emailId:emailId,
        fromDate:fromDate,
		endDate:endDate,
		time : timeST,
		task:"problemSearch"
  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "callCenterAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}
function callAjax(param,jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults =YAHOO.lang.JSON.parse(o.responseText);	if(jsObj.task =="problemSearch"){
				
				getProblemsDetailsDatatable(myResults);
			}
			else if(jsObj.task =="saveCallTrackingProblem"){
				getCurrentDayProblemCount();
			    getCurrentDayCallTrackingProblem();
				showAddedResult(myResults);
				
			}
			else if(jsObj.task =="getCurrentDayCallTrackingProblem"){
			     showCurrentDayProblems(myResults);
			}
			else if(jsObj.task =="getCurrentDayProblemCount"){
			      showTotalCount(myResults);
			}
			else if(jsObj.task =="deleteCallTrackingProblem"){
			      getCurrentDayProblemCount();
			      getCurrentDayCallTrackingProblem();
			 }
			 else if(jsObj.task =="searchCallTrackingProblem"){
			      showSearchDetails(myResults);
				  showSearchCount(myResults);
			 }
			  else if(jsObj.task =="getCallTrackingProblemByProblemId"){
				 showCallTrackingEditWindow(myResults);
			 }
			 else if(jsObj.task =="updateCallTrackingProblem"){
			     showResultMessage();
				 getCurrentDayCallTrackingProblem();
			 }
			
		}catch (e) {   		
		   	alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			//alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showTotalCount(result){
var resultsCountEl = document.getElementById("showProblemCountDiv");
if(result!=null && result.length!=0){
resultsCountEl.innerHTML = '<div id="showCount" style=" margin-right: 30px; color: rgb(112, 112, 112); font-weight: bold; font-size: 13px; "><span>'+result[0].count+'</span> Calls Received Today</div>';
  }
 else{
  resultsCountEl.innerHTML = '<div id="showCount" style="margin-left: 30px; color: rgb(112, 112, 112); font-weight: bold; font-size: 13px; "><span> 0 </span> Calls Received Today</div>';
  }
}  
function showSearchCount(result){
 var resultsCountEl = document.getElementById("callTrackingTotalCountDiv");
if(result.length!=0){

resultsCountEl.innerHTML = '<div id="showCount" style="margin-left: 30px; color: rgb(112, 112, 112); font-weight: bold; font-size: 13px; text-align: center;"><span>'+result[0].count+'</span> Records Found With This Search Criteria</div>';
  }
else{

resultsCountEl.innerHTML = '<div id="showCount" style="margin-left: 30px; color: rgb(112, 112, 112); font-weight: bold; font-size: 13px; text-align: center;"><span> 0 </span> Records Found With This Search Criteria</div>';
  }  
}

function showSearchDetails(result){

  document.getElementById("callTrackingTotalCountDiv").innerHTML='';
  var CallTrackingResultColumnDefs = [ 		    	             
		    	            
							{key:"name", label: "Name", sortable: true},
		    	           	{key:"mobile", label: "Mobile", sortable: true},
							{key:"referenceNo", label: "Reference No", sortable: true},
							{key:"problemPurpose", label: "Call Purpose", sortable: true},
		    				{key:"problemAddedDate", label: "Problem Added Date",sortable:true},
							{key:"villageOrTown", label: "Action Taken", sortable: true}
							
		    	        ]; 
	var CallTrackingResultDataSource = new YAHOO.util.DataSource(result); 
	


    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    }) 
				};
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "problem","name","mobile","problemAddedDate","villageOrTown"]
					};

		var CallTrackingResultDataTable = new YAHOO.widget.DataTable("callTrackingCurrentDiv", CallTrackingResultColumnDefs,myDataSource, myConfigs);

		
 
}
function showCurrentDayProblems(result){
 
       document.getElementById("name1").value='';
	   document.getElementById("mobile").value='';
	   document.getElementById("referenceNo").value='';
	   document.getElementById("villageTown").value='';
 YAHOO.widget.DataTable.add = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("problemId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='addProblem("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/plusNew.png'></a>";
		
  };
  YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("problemId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='editProblem("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";
		
  };

  
  var CallTrackingResultColumnDefs = [ 		    	             
		    	            {key:"name", label: "Name", sortable: true},
		    	           	{key:"mobile", label: "Mobile", sortable: true},
							{key:"referenceNo", label: "Reference No", sortable: true},
							{key:"problemPurpose", label: "Call Purpose", sortable: true},
		    				{key:"problemAddedDate", label: "Problem Added Date",sortable:true},
							{key:"villageOrTown", label: "Action Taken", sortable: true},
							{key:"Edit", label: "Edit",formatter:YAHOO.widget.DataTable.edit},
							{key:"AddProblem", label: "Add Problem",formatter:YAHOO.widget.DataTable.add}
							
		    	        ]; 
	var CallTrackingResultDataSource = new YAHOO.util.DataSource(result); 
	


   var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    }) 
				};
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "problem","name","mobile","problemAddedDate","villageOrTown"]
					};

		var CallTrackingResultDataTable = new YAHOO.widget.DataTable("callTrackingCurrentDiv", CallTrackingResultColumnDefs,myDataSource, myConfigs);
	
	}


function getProblemsDetailsDatatable(result){

YAHOO.widget.DataTable.problemLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var problem = oData;
		var phId= oRecord.getData("problemHistoryId");
		elLiner.innerHTML ="<a href='javascript:{}' onClick='openProblemWindow("+phId+")'>"+problem+"</a>";
			
	};
var resultsColumnDefs = [ 	
	 {
		key : "problem",
		label : "Problem",
		sortable : true,
		formatter:YAHOO.widget.DataTable.problemLink
	}, 
	  {
		key : "description",
		label : "Description",
		sortable : true
		},
		{
		key : "reportedDate",
		label : "Posted on",
		sortable : true
		},
		{
		key : "status",
		label : "Problem Status",
		sortable : true		
	    } ,
		{
		key : "problemPostedBy",
		label : "Posted By",
		sortable : true		
	}
		
		
	];


    var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [10,20,30], 
						pageLinks: 10
						})
						
					};	
		var myDataSource = new YAHOO.util.DataSource(result);
myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "problem","description" , "reportedDate" ,"impactLevel"]
					};

				
	var myDataTable = new YAHOO.widget.DataTable("problemDetails_body",resultsColumnDefs, myDataSource,myConfigs);  


}
function openProblemWindow(pHistoryId){

var browser2 = 
window.open("problemDetailsAndStatusAction.action?pHistoryId="+pHistoryId+"&&requestFrom=callCenter","problemWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
						 
		 browser2.focus();

}
function openAddNewProblemWindow()
{	
var browser = 
window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
		 browser.focus();
	}
function openManageProblemWindow()
{	
var browser1 = 
window.open("<s:url action="constituencyManagementAction.action"/>","ManageProblem","scrollbars=yes,height=900,width=900,left=5,top=200");
						 
		 browser1.focus();
}

function openProblemSearchWindow()
{	
var browser1 = 
window.open("<s:url action="problemManagementReportAction.action"/>","ManageProblem","scrollbars=yes,height=900,width=1000,left=5,top=200");
						 
		 browser1.focus();
}


</script>
<body>
  <table width="100%" cellpadding="0" cellspacing="0" border="0" align="left">
  <tr>
  <td valign="left">
<div style="float:left;margin-left:350px;">
<div id="headerDiv">Call Center</div>
</div>
</td>
  </tr>
  <tr>
  <td valign="left"><div style="float:left;">
  <table>
  <tr>
  <td width="88" align="left">&nbsp;</td>
  <td width="664" align="left"><div id="showCallAddedResult"></div></td>
  <td width="231" align="left"><div id="showProblemCountDiv"></div></div></td>
  </tr>
  </table>
  
  </div></td>
  </tr>
  </table>
  <table>
	<tr>
      
	     
      
    </tr>
	<tr>
      
	    
      
    </tr>
  </table>


<div id="callTrackingMainDiv" style="padding-top:20px;">
   <table>
     <tr>
      <th>Name</th>
	  <th>Mobile No</th>
	  <th>Call Purpose</th>	  
	  <th>Reference No</th>
	  <th>Action Taken</th>
     </tr>
	 <tr>
	   <td><input type="text" size="15" class="textFieldStyle"  style="height:23px;" id="name1"/></td>
	   <td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="mobile" onBlur="validatePhoneNo(this.id)"/></td>
	   <td>
	       <select style="width:190px;" class="textFieldStyle" style="height:23px;" id="problemPurpose" >
	         <option>All</option>
			 <option>Appointment Cancellation</option>
			 <option>Appointment Fixing</option>
			 <option>For Information</option>
			 <option>Problem Reporting</option>
			 <option>Problem Status Enquiry</option>			 			 			 
			 <option>Others</option>
		   </select>
	   </td>	   
	   <td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="referenceNo" /></td>
	   <td><input type="textarea" maxlength="50"  class="textFieldStyle" style="height:23px;" id="villageTown"/></td>
	   
     </tr>
	 <tr>
	   <td><div id="errorNameDiv"></div></td>
	   <td><div id="errorMobileDiv"></div></td>
	   <td><div id="errorProblemPurposeDiv"></div></td>
	   <td><div id="errorRefDiv"></div></td>
	   <td><div id="errorVillageDiv"></div></td>
	 </tr>
	 
   </table>
   <table style="padding-top:10px;">
     <tr>
	   <td><img    onclick="saveCallTrackingProblem();" src="images/add_button.jpg"/></td>
	   <td><img  onclick="searchCallTrackingProblem();" src="images/search_button.jpg"/></td>
	   <td><img onclick="addCallTrackingProb();" src="images/add_problem.jpg"/></td>
	   <td><img  onclick="clearAll();" src="images/clear_all_buttom.jpg"/></td>
	 </tr>
   </table>
</div>
<div id="callTrackingTotalCountDiv" style="padding-top:20px;"></div>
<div id="callTrackingCurrentDiv" class="yui-skin-sam yui-dt">
</div>
<div id="editCallTrackingProblem"> </div>
<div id="mainDiv">
<table>
<tr>
<td>
&nbsp;
</td>
</tr>
</table>
<table>
<tr><td> 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="283" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><img src="images/lift_cure.jpg" width="9" height="28" /></td>
                      <td width="271"  background="images/middel_cure.jpg" ><span style="font-family:Verdana, Arial, Helvetica, sans-serif; float:left; font-size:12px; font-style:normal; color:#FFFFFF; font-weight:bold; padding-left:5px;">Quick Links</span></td>
                      <td align="right" valign="middle"><img src="images/right_cure.jpg" width="9" height="28" /></td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td align="left" valign="top" style="background:#FFFFFF; width:283px; height:289px; border-left:#dddddd solid 1px; border-right:#dddddd solid 1px;">
                <div class="quickbg12">
                <table width="100%">
				<tr>
   <td>
  	<img src="images/icons/homePage_new/widgetHeaderIcon.jpeg" />
   &nbsp&nbsp
	 <a class="tdStyle" href="" onclick="openAddNewProblemWindow()">
		Add New Problem</a>
  </td>
</tr>
<tr>
   <td>
   <img src="images/icons/homePage_new/widgetHeaderIcon.jpeg" />
   &nbsp&nbsp
   <a class="tdStyle" href="" onclick="openManageProblemWindow()">
   Manage Problem</a>
   </td>
</tr>
<tr>
   <td>
   <img src="images/icons/homePage_new/widgetHeaderIcon.jpeg"/>
   &nbsp&nbsp
   <a class="tdStyle" href="" onclick="openProblemSearchWindow()">
   Detailed Search on Problems</a>
   </td>
</tr>
				</table>
                </div></td>
              </tr>
              <tr>
                <td><img src="images/buttom_border_bg.jpg" width="283" height="10" /></td>
              </tr>
            </table></td>
            <td width="24">&nbsp;</td>
            <td width="492"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="9"><img src="images/lift_cure.jpg" width="9" height="28" /></td>
                      <td background="images/middel_cure.jpg"><span style="font-family:Verdana, Arial, Helvetica, sans-serif; float:left;font-size:12px; font-style:normal; color:#FFFFFF; font-weight:bold; padding-left:5px;">Quick Search For Problems</span></td>
                      <td width="9" align="right" valign="middle"><img src="images/right_cure.jpg" width="9" height="28" /></td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td align="left" valign="top" style="background:#FFFFFF;  height:235px; border-left:#dddddd solid 1px; border-right:#dddddd solid 1px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="12">&nbsp;</td>
                        <td>
                        <table width="100%">
						<tr>
						<td><s:textfield name="byName" value="Name" id="nameId" cssClass="formbg" onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>
						</td>
						</tr>
<tr>
 <td>
<s:textfield name="byMobileNum" value="Mobile Number" id="mobileNumId" cssClass="formbg_1" onClick="removeTextInTextBoxes(this.id)" onBlur=" numbersonly(this.id);showTextInTextBoxes(this.id);" theme="simple"/>
<span style="font-family:verdana; float:left; font-size:11px; color:#FF0000; padding:18px 0px 0px 5px;" id="errMsg"></span>
 </td>
</tr>
						<tr>
						<td><s:textfield name="byEmail" value="Email Id" id="emailId" cssClass="formbg_1" onClick="removeTextInTextBoxes(this.id)" theme="simple"onBlur="validateEmail(this.id),showTextInTextBoxes(this.id)"/><span style="font-family:verdana; float:left; font-size:11px; color:#FF0000; padding:18px 0px 0px 5px;" id="emailErrMsg"></span></td>
						</tr>
						
						<tr>
						<td>
						<s:textfield name="byRefNum" value="Problem Reference" id="refId" cssClass="formbg"  onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id)"  theme="simple"/>
						</td>
						</tr>
						</table>


                        </td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="15">&nbsp;</td>
                        <td>

<s:textfield name="fromDate" value="From Date" id="fromDate"  cssClass="formbg12" onClick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>

<s:textfield name="endDate" value="To Date" id="endDate" cssClass="formbg13" style="margin-left: 20px;" onClick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>
						</td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="44%" height="30">&nbsp;</td>
                        <td width="56%" valign="bottom"><a href="#"><img src="images/search_button.jpg" onclick="getProblemDetails();" width="71" height="26" border="0" /></a></td>
                      </tr>
                    </table></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td><img src="images/buttom_border_bgright.jpg" width="492" height="10" /></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</td>
</tr>
</table>
</div>
<div>
<div id="problemDetails_body" class="yui-skin-sam" ></div>
</div>

<script type="text/javascript">
callForEveryMinute();
getCurrentDayCallTrackingProblem();
</script>
<script>
$(function() {
	
		$( "input:button", ".demo" ).button();
		$( "button", ".demo" ).click(function() { return false; });
	});
</script>
<script>
	$(function() {
		$( "#fromDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			maxDate:new Date(),
			dateFormat:"yy-mm-dd"
		});

		$( "#endDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange:"c-10",
			maxDate:new Date(),
			dateFormat:"yy-mm-dd"
        });
		
	});

	</script>




</body>

</html>
