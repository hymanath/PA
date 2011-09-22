<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js">
</script>
<!-- JQuery files (Start) -->
<!--<script type="text/javascript" src="js/jQuery/jquery-1.5.2.js"></script>-->
 
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
    width: 457px;
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

#tableStyle{
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
    padding: 18px 67px 99px;
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
    -moz-border-radius: 6px 6px 6px 6px;
    background: none repeat scroll 0 0 #FFFFFF;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 1px;
	height: 28px;
    padding-left: 8px;

}
 #headerDiv{
   -moz-border-radius: 9px 9px 9px 9px;
    background: -moz-linear-gradient(center top , #F9F9F9 0pt, #E8E8E8 100%) repeat scroll 0 0 transparent;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 0;
    color: Navy;
    font-size: 20px;
    font-weight: bold;
    height: 33px;
    margin-top: 23px;
    padding-top: 10px;
    text-align: center;
    width: 210px;
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
</style>
</head>

<script type="text/javascript">	
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
								title:'<font color="Navy">Edit Problem</font>',
								overlay: { opacity: 0.5, background: 'black'},
								});
	$("#editCallTrackingProblem").dialog();
								
	var str = '';
	str += '<table>';
	str +=  '<tr>';
	str +=  '<div id="updateProblemDiv" />';
	str +=  '</tr>';
    str +=  '<tr>';
    str +=   '<th>Name</th>';
	str +=   '<th>MobileNo</th>';
	str +=   '<th>CallPurpose</th>';	  
	str +=   '<th>Reference No</th>';
	str +=   '<th>Village/Town</th>';
    str +=  '</tr>';
	str +=  '<tr>';
	str +=    '<td><input type="text" size="15" class="textFieldStyle"  style="height:23px;" id="name2"/></td>';
	str +=    '<td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="mobile1"/></td>';
	str +=   '<td>';
	str +=      '<select style="width:130px;" class="textFieldStyle" style="height:23px;" id="problemPurpose1" >';
    str +=        '<option>All</option>';
	str +=        '<option>Problem Reporting</option>';
	str += 		  '<option>Problem Status Enquiry</option>';
	str += 		  '<option>For Information</option>';
	str += 		  '<option>Appointment Fixing</option>';
	str += 		  '<option>Appointment Cancellation</option>';
	str += 		  '<option>Others</option>';
	str += 	    '</select>';
	str +=    '</td>';	   
	str +=    '<td><input type="text" size="15" class="textFieldStyle" readonly="readonly" style="height:23px;" id="referenceNo1" /></td>';
	str +=    '<td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="villageTown1"/></td>';
	str +=    '<td><input id="resultBtnId" type="button" value="Update" onclick="updateCallTrackingProblem();"/></td>';
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
       var name = document.getElementById("name2").value;
	   var mobile = document.getElementById("mobile1").value;
	   var villageTown = document.getElementById("villageTown1").value;
       if(name.trim().length == 0)
	{
		document.getElementById("errorNameDiv1").innerHTML ='<font color="red">Name is Required</font>';
		val = 1;
	}
	if(mobile.trim().length == 0)
	{
		document.getElementById("errorMobileDiv1").innerHTML = '<font color="red">MobileNo is Required</font>';
		val = 1;
	}
	if(mobile.trim().length != 0){
       if(isNaN(mobile) || mobile.length<10){
		document.getElementById("errorMobileDiv1").innerHTML = '<font color="red">Please enter valid Mobile <br /> Number</font>';
		val = 1;
	    }
	 }
	if(villageTown.trim().length == 0)
	{
		document.getElementById("errorVillageDiv1").innerHTML= '<font color="red">village/Town is Required</font>';
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
	  var problemPurpose = document.getElementById("problemPurpose1").value;
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
	  var mobile = document.getElementById("mobile").value;
	  var problemPurpose = document.getElementById("problemPurpose").value;
	  var referenceNo = document.getElementById("referenceNo").value;
	  var villageTown = document.getElementById("villageTown").value;
	  if(problemPurpose =="All")
	     problemPurpose = '';

      var jsObj=
	      {				
			name: name,
			mobile: mobile,
			problemPurpose: problemPurpose,
			referenceNo:referenceNo,
			villageOrTown:villageTown,
			task: "searchCallTrackingProblem"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
}
function validatePhoneNo(){
  document.getElementById("errorMobileDiv").innerHTML='';
  var mobile = document.getElementById("mobile").value;
  if(mobile.trim().length == 0)
	{
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">MobileNo is Required</font>';
		val = 1;
	}
	if(mobile.trim().length != 0){
       if(isNaN(mobile) || mobile.length<10){
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
	   var problemPurpose = document.getElementById("problemPurpose").value;
	   var referenceNo = document.getElementById("referenceNo").value;
	   var villageTown = document.getElementById("villageTown").value;
       if(name.trim().length == 0)
	{
		document.getElementById("errorNameDiv").innerHTML ='<font color="red">Name is Required</font>';
		val = 1;
	}
	if(mobile.trim().length == 0)
	{
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">MobileNo is Required</font>';
		val = 1;
	}
	if(mobile.trim().length != 0){
       if(isNaN(mobile) || mobile.length<10){
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Please enter valid Mobile <br /> Number</font>';
		val = 1;
	    }
	 }
	if(problemPurpose.trim().length == 0){
		 document.getElementById("errorProblemPurposeDiv").innerHTML= '<font color="red">ProblemPurpose is Required</font>';
		 value = 1;
	}
	if(referenceNo.trim().length == 0)
	{
		 document.getElementById("errorRefDiv").innerHTML = '<font color="red">ReferenceNo is Required</font><BR/>';
		val = 1;
	}
	if(villageTown.trim().length == 0)
	{
		document.getElementById("errorVillageDiv").innerHTML= '<font color="red">village/Town is Required</font>';
		val = 1;
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
	   var problemPurpose = document.getElementById("problemPurpose").value;
	   var villageTown = document.getElementById("villageTown").value;
       if(name.trim().length == 0)
	{
		document.getElementById("errorNameDiv").innerHTML ='<font color="red">Name is Required</font>';
		val = 1;
	}
	if(mobile.trim().length == 0)
	{
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">MobileNo is Required</font>';
		val = 1;
	}
	if(mobile.trim().length != 0){
       if(isNaN(mobile) || mobile.length<10){
		document.getElementById("errorMobileDiv").innerHTML = '<font color="red">Please enter valid Mobile <br /> Number</font>';
		val = 1;
	    }
	 }
	if(problemPurpose.trim().length == 0){
		 document.getElementById("errorProblemPurposeDiv").innerHTML= '<font color="red">ProblemPurpose is Required</font>';
		 value = 1;
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
	if(refNo.trim().length != 0){
	document.getElementById("errorRefDiv").innerHTML='<font color="red">Reference No must be empty</font>';;
	}
if(refNo.trim().length <= 0){
  if(validateForAddProblem()!=1){
      var name = document.getElementById("name1").value;
	  var mobile = document.getElementById("mobile").value;
	  var problemPurpose = document.getElementById("problemPurpose").value;
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
	  var problemPurpose = document.getElementById("problemPurpose").value;
	  var referenceNo = document.getElementById("referenceNo").value;
	  var villageTown = document.getElementById("villageTown").value;
      var jsObj=
	      {				
			name: name,
			mobile: mobile,
			problemPurpose: problemPurpose,
			referenceNo:referenceNo,
			villageOrTown:villageTown,
			task: "saveCallTrackingProblem"
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveCallTrackingProblemAction.action?"+rparam;						
      callAjax(rparam,jsObj,url);
	  }
    }
function quickSearch(){
 
  var str='';
  var quickSearchDivElmt = document.getElementById("quickSearchDiv");
  str+='<div id="searchDiv">Quick Search For Problems';
  str+='</div>';
  str+='<table cellspacing="2px" cellpadding="11px" id="tableStyle" >';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byName" value="Name" id="nameId" cssClass="textFieldStyle" size="30px" onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>';
  str+='</td>';
  str+='</tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byMobileNum" value="Mobile Number" id="mobileNumId" cssClass="textFieldStyle" size="30px" onClick="removeTextInTextBoxes(this.id)" onBlur=" numbersonly(this.id);showTextInTextBoxes(this.id);" theme="simple"/>';
  str+='&nbsp<span id="errMsg">';
  str+='</span>';
  str+='</td>';
  
  str+='</tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byEmail" value="Email Id" id="emailId" cssClass="textFieldStyle" size="30px" onClick="removeTextInTextBoxes(this.id)" theme="simple" onBlur="validateEmail(this.id),showTextInTextBoxes(this.id)"/>';
  str+='<span id="emailErrMsg" style="padding-left:37px;">';
  str+='</span>';
  str+='</td>';
  
  str+='</tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byRefNum" value="Problem Reference" id="refId" cssClass="textFieldStyle"  onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id)" size="30px" theme="simple"/>';
  str+='</td>';
  str+='</tr>';
  str+='<tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="fromDate" value="From Date" id="fromDate" size="30px" cssClass="textFieldStyle" onClick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>';
  str+='<s:textfield name="endDate" value="To Date" id="endDate" cssClass="textFieldStyle" size="30px" style="margin-left: 20px;" onClick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>';
  str+='</td>';
  str+='</tr>';
  str+='<tr>';
  str+='<td align="center">';
  str+='<input type="button" style="padding:8px;" id="resultBtnId" value="Search" onClick="getProblemDetails()">';
  str+='</td>';
  str+='</tr>';
  str+='</table>';
  

quickSearchDivElmt.innerHTML = str;

}
function validateEmail(id){
	
	var email=document.getElementById("emailId").value;
	var emailFilter=/^.+@.+\..{2,3}$/
	if(email!="" && email!="Email Id"){
		if(!emailFilter.test(email)){
			document.getElementById("emailErrMsg").innerHTML = '<font color="red">Please enter valid Email</font>';
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
		document.getElementById("errMsg").innerHTML ='<font color="red">Please enter valid Mobile Number</font>';
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
    $("input#"+id).val($(this).html());
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
	
	  if($("#nameId").val() =="Name"){
		$("#nameId").val($(this).html());
	  } 
	  if($("#refId").val() =="Problem Reference"){
		$("#refId").val($(this).html());
	  }
	  if($("#mobileNumId").val() =="Mobile Number"){
		 $("#mobileNumId").val($(this).html()); 
	  }
	  if($("#emailId").val() =="Email Id"){
		 $("#emailId").val($(this).html());
	  }
	 if($("#fromDate").val() =="From Date"){
		 $("#fromDate").val($(this).html());
	 }
	 if($("#endDate").val() =="To Date"){
         $("#endDate").val($(this).html());
	 }

	  var jsObj = {
		name:$("#nameId").val(),
		refNum:$("#refId").val(),
		mobileNum:$("#mobileNumId").val(),
        emailId:$("#emailId").val(),
        fromDate:$("#fromDate").val(),
		endDate:$("#endDate").val(),
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
if(result.length!=0){
resultsCountEl.innerHTML = '<div id="showCount" style=" color: rgb(112, 112, 112); font-weight: bold; font-size: 13px; "><span>'+result[0].count+'</span> Calls Received Today</div>';
  }
 else{
  resultsCountEl.innerHTML = '<div id="showCount" style="color: rgb(112, 112, 112); font-weight: bold; font-size: 13px; "><span> 0 </span> Calls Received Today</div>';
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
							{key:"referenceNo", label: "ReferenceNo", sortable: true},
							{key:"problemPurpose", label: "CallPurpose", sortable: true},
		    				{key:"problemAddedDate", label: "ProblemAddedDate",sortable:true},
							{key:"villageOrTown", label: "village/Town", sortable: true}
							
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
							{key:"referenceNo", label: "ReferenceNo", sortable: true},
							{key:"problemPurpose", label: "CallPurpose", sortable: true},
		    				{key:"problemAddedDate", label: "ProblemAddedDate",sortable:true},
							{key:"villageOrTown", label: "village/Town", sortable: true},
							{key:"Edit", label: "Edit",formatter:YAHOO.widget.DataTable.edit},
							{key:"AddProblem", label: "AddProblem",formatter:YAHOO.widget.DataTable.add}
							
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
		label : "Identified on",
		sortable : true
		},
		 
		{
		key : "impactLevel",
		label : "Problem Scope",
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
function openProblemWindow(problemHistoryId){

var browser2 = 
window.open("addNewProblemAction.action?problemHistoryId="+problemHistoryId,"editProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
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
  <table>
    <tr>
      
         <div id="headerDiv">Call Center</div>
    </tr>
	<tr>
      
	     <div id="showProblemCountDiv"></div>
      
    </tr>
	<tr>
      
	     <div id="showCallAddedResult"></div>
      
    </tr>
  </table>


<div id="callTrackingMainDiv" style="padding-top:20px;">
   <table>
     <tr>
      <th>Name</th>
	  <th>MobileNo</th>
	  <th>CallPurpose</th>	  
	  <th>Reference No</th>
	  <th>Village/Town</th>
     </tr>
	 <tr>
	   <td><input type="text" size="15" class="textFieldStyle"  style="height:23px;" id="name1"/></td>
	   <td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="mobile" onBlur="validatePhoneNo(this.id)"/></td>
	   <td>
	       <select style="width:130px;" class="textFieldStyle" style="height:23px;" id="problemPurpose" >
	         <option>All</option>
			 <option>Problem Reporting</option>
			 <option>Problem Status Enquiry</option>
			 <option>For Information</option>
			 <option>Appointment Fixing</option>
			 <option>Appointment Cancellation</option>
			 <option>Others</option>
		   </select>
	   </td>	   
	   <td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="referenceNo" /></td>
	   <td><input type="text" size="15" class="textFieldStyle" style="height:23px;" id="villageTown"/></td>
	   <td><input id="resultBtnId" type="button" value="Add" onclick="saveCallTrackingProblem();"/></td>
	   <td><input id="resultBtnId" type="button" value="Search" onclick="searchCallTrackingProblem();"/></td>
	   <td><input id="resultBtnId" type="button" value="AddProblem" onclick="addCallTrackingProb();"/></td>
     </tr>
	 <tr>
	   <td><div id="errorNameDiv"></div></td>
	   <td><div id="errorMobileDiv"></div></td>
	   <td><div id="errorProblemPurposeDiv"></div></td>
	   <td><div id="errorRefDiv"></div></td>
	   <td><div id="errorVillageDiv"></div></td>
	 </tr>
   </table>
</div>
<div id="callTrackingTotalCountDiv" style="padding-top:20px;"></div>
<div id="callTrackingCurrentDiv" class="yui-skin-sam yui-dt">
</div>
<div id="editCallTrackingProblem"> </div>
<div id="mainDiv">
<table>
<tr><td>
<div id="table1">Quick Links</div>
<table class="table1Style">

 <tr>
   <td>
  	<img  style="margin-left: -43px;" src="images/icons/homePage_new/widgetHeaderIcon.jpeg" />
   &nbsp&nbsp
	 <a class="tdStyle" href="" onclick="openAddNewProblemWindow()">
		Add New Problem</a>
  </td>
</tr>
<tr>
   <td>
   <img  style="margin-left: -43px;" src="images/icons/homePage_new/widgetHeaderIcon.jpeg" />
   &nbsp&nbsp
   <a class="tdStyle" href="" onclick="openManageProblemWindow()">
   Manage Problem</a>
   </td>
</tr>
<tr>
   <td>
   <img  style="margin-left: -43px;" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"/>
   &nbsp&nbsp
   <a class="tdStyle" href="" onclick="openProblemSearchWindow()">
   Detailed Search on Problems</a>
   </td>
</tr>
</table>

</td>
<td>
<table style="margin-left: 62px; margin-top: 65px;">
<tr>
<td><div id="quickSearchDiv"></div>
</td>
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
quickSearch();
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
