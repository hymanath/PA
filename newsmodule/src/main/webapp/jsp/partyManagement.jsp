<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> TDP News Portal </title>
<!--<script type="text/javascript" src="js/jquery.js"></script>-->


  <link rel="stylesheet" href="js/ui/1.10.3/smoothness/jquery1.10.3-ui.css" />

  <script src="js/ui/1.10.3/jquery-ui.js"></script>
    <script src="js/jquery-1.8.2.js"></script>	
    <script src="js/ui/1.9.0-themes-base/jquery-ui.js"></script>

	 <SCRIPT type="text/javascript" src="js/loginpopup.js"></SCRIPT>
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	
<!-- JQuery files (Start) -->

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->


<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/partyManagement.js"></script>

<!-- JQuery files (End) -->
<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- keywords -->
    <link rel="stylesheet" type="text/css" href="styles/autoSuggest.css"> 
	<script type="text/javascript" src="js/jquery.autoSuggest.js"></script>


<!-- keywords -->
<link  rel="stylesheet" type="text/css" href="styles/partyManagement/partyManagement.css"/>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="js/blockui.js"></script>
<style type="text/css">
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
 
 .enadu
{
font-family: eFont;
font-size:20px;
}
#candidateListForPartyImg{margin-left:300px;}

#userTrackingDetails table,#profileManagementMainOuterDiv4 table,#reportsDiv table,#locationWiseNewsDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#userTrackingDetails table tr:nth-child(even),#profileManagementMainOuterDiv4 table tr:nth-child(even),#reportsDiv table tr:nth-child(even),#locationWiseNewsDiv table tr:nth-child(even){background:#EdF5FF;}
#userTrackingDetails table td,#profileManagementMainOuterDiv4 table td,#reportsDiv table td,#locationWiseNewsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#userTrackingDetails table th,#profileManagementMainOuterDiv4 table th,#reportsDiv table th,#locationWiseNewsDiv table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}
#profileManagementMainOuterDiv4{
	font-family : arial;
	font-size: 13px;
    margin-top:-20px;
	padding: 10px 10px 10px 0px;
}

#reportsDiv,#locationWiseNewsDiv
{
	font-family : arial;
	font-size: 13px;
    margin-top: -32px;
	padding: 10px 10px 10px 15px;
}
#profileManagementMainOuterDiv4 table th a{
color:#333333;
}
.newsLinkCls{ color: #0088CC;}
#totalSelectedNewsCount{float: none;
    margin-left: auto;
    margin-right: auto;
    text-align: left;
    width: 901px;font-weight:bold;}
	/*#selectLevel{float: none;
    margin-left: auto;
    margin-right: auto;
	width: 901px;font-weight:bold;}*/
#selectedNewsCount{color:red;}
.createNewCandidate{width: 20px; height: 20px;cursor:pointer;}
.m_topN65{margin-top: -24px;}
form{
border:1px solid #C5C5C5;
}
#reportGenaratorSpanCLS{color:red;}
#newsReportBtnDiv{text-align: center;}
.ui-multiselect{margin-top:10px;width:222px !important;}

.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 13px 312px;
    white-space: nowrap;
}
.form-actions{
   margin-bottom: -11px;
}
.form-actions input {
    margin: 0 12px;
}
h2{
  margin-bottom: -38px;
}
.yui-skin-sam .yui-dt-liner{
	padding:4px 0px;
}
#gettrackdtlsId{
    margin-bottom: 5px;
    margin-left: 440px;
    margin-top: 7px;
}
.delStyle{
  margin-left:5px;
}
</style>
</head>
<script type="text/javascript">

var reportGallary = new Array();
var keywordGallary = new Array();
var districtsArr = new Array();
var assemblyConstiArr = new Array();
var parliamentConstiArr = new Array();
var loginUserType = '${sessionScope.USER.userAccessType}';
var createCandPartyKey ="";
var createCandCandKey ="";
var locationScop1e = '${sessionScope.USER.accessType}';
var statteId = '${userDetailsVO[0].stateId}';
var distritcId =  '${userDetailsVO[0].districtId}';
var cosntiId =  '${userDetailsVO[0].constituencyId}';

<c:forEach var="districts" items="${districtsList1}">
	var districtList ={
	id:"${districts.id}",
	name:"${districts.name}"
	}
	districtsArr.push(districtList);
</c:forEach>

<c:forEach var="assemConstiList" items="${assemConstiList1}">
	var assemblyConstiList ={
	id:"${assemConstiList.id}",
	name:"${assemConstiList.name}"
	}
	assemblyConstiArr.push(assemblyConstiList);
	
</c:forEach>

<c:forEach var="parlConstiList" items="${parlConstiList1}">
	var parliamentConstiList ={
	id:"${parlConstiList.id}",
	name:"${parlConstiList.name}"
	}
	parliamentConstiArr.push(parliamentConstiList);
</c:forEach>

var gGallaryId;
		var timeST = new Date().getTime();
		var sizeOfArray;

var sourceObj = null;
var languagesObj = null;
var fileCount=0;
var fileImgCount=0;

var partyIdsListArray = new Array();
var candidateIdsListArray = new Array();
var tempPartyIdsArray = new Array();

var sourcePartyArray = new Array();
var destinationPartyArray = new Array();
var sourceCandidatesArray = new Array();
var destinationCandidatesArray = new Array();

var sourceCandidateIdsArray = new Array();
var destinationCandidateIdsArray = new Array();
var tempCount = 0;
var keywordHiddenTempCount = 0;
var responseFileIdsArray = new Array();
var newsReportFileIdsArray = new Array();

var fileSourceMainArray = {
	
 fileSourceSubArray:[]

}
	  
var fileSourceSubArray = {

	filePaths:[],
    source:'',
	language:'',
	edition:'',
	pageNumber:'',
	newsLength:'',
	newsDescriptionInDetailed:'',
	fontCheckBox:''

};


function isKeywordExist(){
	$('#statusDiv1').html('');
	var keyValue = $('#newKeyword').val();
	
	if(isValid(keyValue)){
		$('#statusDiv1').html('<b style="color:red">Keyword Name should not contain #,$,%,& Special charactors</b>');
		return false; 
	}
	
	if(keyValue.trim().length ==0){
		$('#statusDiv1').html('<span style="color:red;">Please enter keyword name.</span>');
		return;
	}	
	 $.ajax({
		type: "GET",
		url: "isKeywordExistAction.action",
		data: { keyword :keyValue,task:"addNewKeyword" }
		})
		.done(function(msg) {
		var status = msg.toLowerCase();
		if(status.indexOf("successfully") !=-1){
			$('#newKeyword').val('');
			$('#statusDiv1').html('<span style="color:green;font-weight:bold;">'+msg+'</span>');
			}
		else
			$('#statusDiv1').html('<span style="color:red;font-weight:bold;">'+msg+'</span>');
	});
}
function mergeKeywords(){
    $('#statusDiv2').html('');
    var selected_values = $("#keywordsList").multiselect("getChecked").map(function(){
       return this.value;    
    }).get();
    
    var keywordsList="";
	var aliasName = $('#aliasKeyword').val();
	if(isValid(aliasName)){
		$('#statusDiv2').html('<b style="color:red">New keyword should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	
	
    for(var i in selected_values){
    keywordsList = keywordsList+""+selected_values[i]+",";
    }	

    if(keywordsList.length == 0){
	$('#statusDiv2').html('<span style="color:red;">Please select atleast one keyword.</span>');
	return;
	}	
	else if(aliasName.trim().length == 0)
	{
	$('#statusDiv2').html('<span style="color:red;">Please enter new keyword name .</span>');
	return;
	}

     $.ajax({
    	type: "GET",
		url: "isKeywordExistAction.action",
		data: { keywords :keywordsList,newKeyword:$('#aliasKeyword').val(),task:"mergeKeywords" }
		})
		.done(function( msg ) {
		var status = msg.toLowerCase();
		if(status.indexOf("success") !=-1){
		$('#aliasKeyword').val('');
			reFreshKeywordList();
			$('#statusDiv2').html('<span style="color:green;font-weight:bold;">'+msg+'</span>');
		}
		else{
			$('#statusDiv2').html('<span style="color:red;font-weight:bold;">'+msg+'</span>');
		}
			
      });
}

function reFreshKeywordList(){
 $.ajax({
    	type: "GET",
		url: "getKeywordsListAction.action",
		data: { task:"getKeywordsList" }
		})
		.done(function( result ) {
			//console.log(result);
		$('#keywordsList').find('option').remove();
		$.each(result,function(index,value){
			$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#keywordsList").multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
		});
}
function createPartyKeywordDiv(){

 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDivStatus").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","block");
  $("#newDesignationDiv").css("display","none");
   $("#newEditCandidateDiv").css("display","none");
  $("#newCandidateCreationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  $('#statusDiv1').html('');
  $('#statusDiv2').html('');
 }

 function createDesignationDiv()
 {
	  $("#newsReportDiv").css("display","block");
	  $("#newsGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").html('');
	  $("#profileManagementMainOuterDiv4").css("display","none");
	  $("#profileManagementHeaderDiv2").css("display","none");
	  $("#profileManagementMainOuterDiv3").css("display","none");
	  $("#profileManagementHeaderDiv3").css("display","none");
	  $("#videoGallaryDiv").css("display","none");
	  $("#dateSelectDiv").css("display","none");
	  $("#profileManagementMainOuterDiv5").css("display","none");
	  $("#profileManagementHeaderDiv5").css("display","none");
	  $("#profileManagementMainOuterDiv6").css("display","none");
	  $("#profileManagementMainOuterDiv7").css("display","none");
	  $("#profileManagementMainOuterDivStatus").css("display","none");
	  $("#profileManagementMainOuterDiv8").css("display","none");
	  $("#newDesignationDiv").css("display","block");
	  $("#newEditCandidateDiv").css("display","none");
	  $("#newCandidateCreationDiv").css("display","none");
	  $("#newPartyCreationDiv").css("display","none");
	  $('#showKeywordsDiv').css("display","none");
	  $('#statusDiv1').html('');
	  $('#statusDiv2').html('');
 }
 
 function createNewCandidateDiv()
 {
	  
	  $("#newsReportDiv").css("display","block");
	  $("#newsGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").html('');
	  $("#profileManagementMainOuterDiv4").css("display","none");
	  $("#profileManagementHeaderDiv2").css("display","none");
	  $("#profileManagementMainOuterDiv3").css("display","none");
	  $("#profileManagementHeaderDiv3").css("display","none");
	  $("#videoGallaryDiv").css("display","none");
	  $("#dateSelectDiv").css("display","none");
	  $("#profileManagementMainOuterDiv5").css("display","none");
	  $("#profileManagementHeaderDiv5").css("display","none");
	  $("#profileManagementMainOuterDiv6").css("display","none");
	  $("#profileManagementMainOuterDiv7").css("display","none");
	  $("#profileManagementMainOuterDivStatus").css("display","none");
	  $("#profileManagementMainOuterDiv8").css("display","none");
	  $("#newDesignationDiv").css("display","none");
	  $("#newEditCandidateDiv").css("display","none");
	  $("#newCandidateCreationDiv").css("display","block");
	  $("#newPartyCreationDiv").css("display","none");
	  $('#showKeywordsDiv').css("display","none");
	  $('#statusDiv1').html('');
	  $('#statusDiv2').html('');
   getPartiesList("partySelectNewList1",null);
   getDesignationList("designationsList1");
 }
 
 function createEditCandidateDiv()
 {
	  $("#newsReportDiv").css("display","block");
	  $("#newsGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").html('');
	  $("#profileManagementMainOuterDiv4").css("display","none");
	  $("#profileManagementHeaderDiv2").css("display","none");
	  $("#profileManagementMainOuterDiv3").css("display","none");
	  $("#profileManagementHeaderDiv3").css("display","none");
	  $("#videoGallaryDiv").css("display","none");
	  $("#dateSelectDiv").css("display","none");
	  $("#profileManagementMainOuterDiv5").css("display","none");
	  $("#profileManagementHeaderDiv5").css("display","none");
	  $("#profileManagementMainOuterDiv6").css("display","none");
	  $("#profileManagementMainOuterDiv7").css("display","none");
	  $("#profileManagementMainOuterDivStatus").css("display","none");
	  $("#profileManagementMainOuterDiv8").css("display","none");
	  $("#newDesignationDiv").css("display","none");
	  $("#newEditCandidateDiv").css("display","block");
	  $("#newCandidateCreationDiv").css("display","none");
	  $("#newPartyCreationDiv").css("display","none");
	  $('#showKeywordsDiv').css("display","none");
	  $('#statusDiv1').html('');
	  $('#statusDiv2').html('');
	   getPartiesList("EditpartySelectNewList",null);
	   getPartiesList("currentpartySelectNewList",null);
   getDesignationList("designationsList2");
 }
 
 function createPartyDiv()
 {
	  $("#newsReportDiv").css("display","block");
	  $("#newsGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").css("display","none");
	  $("#newsAssignGallaryDiv").html('');
	  $("#profileManagementMainOuterDiv4").css("display","none");
	  $("#profileManagementHeaderDiv2").css("display","none");
	  $("#profileManagementMainOuterDiv3").css("display","none");
	  $("#profileManagementHeaderDiv3").css("display","none");
	  $("#videoGallaryDiv").css("display","none");
	  $("#dateSelectDiv").css("display","none");
	  $("#profileManagementMainOuterDiv5").css("display","none");
	  $("#profileManagementHeaderDiv5").css("display","none");
	  $("#profileManagementMainOuterDiv6").css("display","none");
	  $("#profileManagementMainOuterDiv7").css("display","none");
	  $("#profileManagementMainOuterDivStatus").css("display","none");
	  $("#profileManagementMainOuterDiv8").css("display","none");
	  $("#newDesignationDiv").css("display","none");
	  $("#newEditCandidateDiv").css("display","none");
	  $("#newCandidateCreationDiv").css("display","none");
	  $("#newPartyCreationDiv").css("display","block");
	  $('#showKeywordsDiv').css("display","none");
	  $('#statusDiv1').html('');
	  $('#statusDiv2').html('');
 }
 
function clearDivsForGallary(){
 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","block");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","block");
  $("#profileManagementHeaderDiv3").css("display","block");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDivStatus").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  $('#statusDiv1').html('');
  $('#statusDiv2').html('');
  $("#newDesignationDiv").css("display","none");
   $("#newEditCandidateDiv").css("display","none");
  $("#newCandidateCreationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  $('#showKeywordsDiv').css("display","none");
}

function createNewDesignation()
{
	var designationId = $('#designationId').val();	

	//designationId = replaceSpeclChars(designationId);
	if(isValid(designationId)){
		$('#statusDivForDesignation').html('<b style="color:red">Desgination should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	else if( designationId == ""){
		$('#statusDivForDesignation').html('<b style="color:red">Please Enter Desgination Description</b>');
		//$('#statusDivForDesignation').delay(1000);
		//$('#statusDivForDesignation').show();
		//$('#statusDivForDesignation').hide();
	}
	else if(designationId.trim().length < 2 ){
		$('#statusDivForDesignation').html('<b style="color:red"> Desgination should contains minimum 2 Charactors.</b>');
		return false;		
	}
	else
	{
		var jsObj={
		designation :designationId,
		time:timeST,
		task:"createNewDesignation"
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveDesignationDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	  
}

function createNewParty()
{
	var partyLongName = $('#partyLongName').val();
	var partyShortName = $('#partyShortName').val();
	//partyLongName =  replaceSpeclChars(partyLongName)
	//partyShortName =  replaceSpeclChars(partyShortName)
	if(isValid(partyLongName)){
		$('#statusForParty').html('<b style="color:red">Party Long Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	if(isValid(partyShortName)){
		$('#statusForParty').html('<b style="color:red">Party Short Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	if(partyShortName == "" || partyLongName == ""){
		$('#statusForParty').html('<b style="color:red">Please Enter Party Details</b>');
		//$('#statusForParty').delay(1000);
		//$('#statusForParty').show();
		//$('#statusForParty').hide();
		return false;
	}
	if(partyShortName.trim().length < 2 || partyLongName.trim().length < 2){
		$('#statusForParty').html('<b style="color:red"> Party Details should contains  minimum  2 Charactors.</b>');
		return false;		
	}
	else
	{
		var jsObj={
			partyLongName  : partyLongName,
			partyShortName : partyShortName,
			time:timeST,
			task:"createNewParty"
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "savePartyDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
}
</script>

<body>
<br>
<!-- For Heading -->

<div id="editNewsOuter">
  <div id="editNewsInner"></div>
</div>

<div id='profileManagementMainOuterDiv' style="margin-top:-40px;">

<div id='profileManagementHeaderDiv'>
	
	
</div><br>
 <!-- For Heading end -->
 
<div id='profileManagementMainInnerDiv' class="divInfo">
	<div id="profileManagementDiv">
	
				<!---Tab Header --Menu--->
				<ul class="nav nav-tabs" id="myTab">
					
					
					<li class="active"><a data-toggle="tab" value="Upload News" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();uploadNewsForPartyAndCandidate(null);" style="cursor:pointer;color: #005580;" > Upload News</a>	
					</li>
					
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showTheNewsToUpdate()" style="cursor:pointer;color: #005580;">Add Response To News</a>
					</li>
					
                    <li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" style="cursor:pointer;color: #005580;">Reports <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="">
							  <a data-toggle="tab" value="create Report" id="createReportId" onClick="createReport();" style="cursor:pointer;color: #005580;">Create Report </a>
					       </li>
						  <li class="">
					         <a data-toggle="tab" value="viewReport" id="viewReports" onclick=" getNewsReports();" style="cursor:pointer;color: #005580;">View Report</a>
					      </li>
					      <c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
						  <li class="">
					         <a data-toggle="tab" value="viewReport" id="viewReports" onclick=" showUploadStatus();" style="cursor:pointer;color: #005580;">View Users News Uploading Status</a>
					      </li>
					      </c:if>		
						</ul>
					</li>
					<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" style="cursor:pointer;color: #005580;" id="allKeywordsDiv" >Keyword Management <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<li><a data-toggle="tab" id="newKeywordBtn" style="cursor:pointer;color: #005580;" onclick="createPartyKeywordDiv();">Create New Keyword</a></li>
							</li>
							<li>
								<a data-toggle="tab"  style="cursor:pointer;color: #005580;" onClick="openMergeKeywordWindow();"> Merge Keywords </a>
							</li>
							<li>
								<a data-toggle="tab" id="mergeKeywordBtn" style="cursor:pointer;color: #005580;" onClick="getKeywordsByCount()"> View Keyword Wise Total News </a>
							</li>
						</ul>
					</li>
					</c:if>					
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" style="cursor:pointer;color: #005580;" >Actions<b class="caret"></b></a>
					
						<ul class="dropdown-menu">
						<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
							<li>
								<li><a data-toggle="tab" id="newKeywordBtn" style="cursor:pointer;color: #005580;" onclick="createPartyDiv();">Create  Party</a></li>
							</li>
						</c:if>
							<li>
								<a data-toggle="tab" id="mergeKeywordBtn" style="cursor:pointer;color: #005580;" onClick="createDesignationDiv();"> Create Designation </a>
							</li>
					
							<li>
								<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();buildCreateNewsCategory();" >Create News Category</a>
							</li>
						
						<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
						    <li>
									<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();createNewSource();">Create New Source</a>
							</li>
						</c:if>
						
						    <li>
									<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();createNewCandidateDiv();">Create New Candidate</a>
							</li>
					
						    <li>
									<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();createEditCandidateDiv();">Edit Candidate</a>
							</li>
						</ul>
					</li>
					
				</ul>
				
				<!----Tab Header Menu End--->
</div>
</div>
</div>

<div id="dialog" title="Update Category" style="display:none;">
<div>
	<div style="clear:both;"><span>Name</span>: <input type="text" id="categoryNameId"/></div>
	<div style="clear:both;"><span>Visibility</span>: 
				<input type="radio"  name="visibility" value="public" checked="checked" style="margin-top:0px;margin-left:4px;">Public
				<input type="radio"  name="visibility" value="private" style="margin-top:0px;margin-left:4px;">Private</div>
				<input id="idVal" type="hidden" value="" />
				
	<div id="errMsg" style="margin:5px;"></div>
	
	<span class="btn btn-mini pull-right btn-inverse" id="updateCategoryId">Update</span>
</div>
</div>


<!-- for  body 1 start    result  -->
<HR>
<div id='profileManagementMainOuterDiv1' style="display:none">
	<div id='profileManagementHeaderDiv1' class="row-fluid">
		
		<div class="span10 offset1 text-center alert"><input type="button" class="btn btn-success highlight" value="Create Gallery" onclick="buildCreateGallaryDiv()">
		<input type="button" class="btn btn-success highlight" value="Upload photos" onclick="buildUploadPhotosDiv()"></div>

		
	</div>

	<div id='photoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 1 end    result  -->

<!-- for  body 2 start    result  -->

<div id='profileManagementMainOuterDiv2' style="display:none">
	<div id='profileManagementHeaderDiv2' class="row-fluid">
		<div class="span10 offset1 text-center alert"><input type="button" class="btn btn-success highlight" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()">
		<input type="button" class="btn btn-success highlight" value="Upload Video" onclick="buildUploadVideoDiv()">
		</div>
		
	</div>	

	<div id='videoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->
<div id='profileManagementMainOuterDiv3' style="display:none">
	<div id='profileManagementHeaderDiv3' class="row-fluid">
</div>
		<div id='newsGallaryDiv' class="divInfo">
	 </div>		
		<div id='newsAssignGallaryDiv' class="divInfo"> </div>		 
	</div>
</div>


<!-- for  body 4  result  start -->

<div id="dateSelectDiv" style="display:none;" class="container well">

  <legend class="boxHeading text-center">Update News  </legend>
  <div style="border:1px solid #ADC248;width: 905px;padding:5px 15px 15px 15px;" class="well">
   <div class="row-fluid" style="background-color:#f5f5f5;">
   <div class="span3" style="margin-left:240px;"><label style="float: left;"><strong>Start Date<span class="requiredFont">*</span></strong></label><input type="text" name="fromDate" class="inpit-block-level  dateField" id="newsFromDateId" readonly="true"/></div>
   <div class="span3" style="margin-left:3px;"><label style="float: left;"><strong>End Date<span class="requiredFont">*</span></strong></label><input type="text" name="toDate" readonly="true" class="inpit-block-level  dateField" id="newsToDateId"/></div>
  
  </div>
  
  <div id="selectLevel" class="row-fluid" style="background-color:#f5f5f5;">
   <div class="span4" style="margin-left:200px;"><label style="float: left;margin-left: 40px;"><strong>Select Level</strong></label><select id="responseRegionLevel" onchange="showCorrespondingLocations1();"><option value="1">ALL</option><option value="2">DISTRICT</option><option value="3">PARLIAMENT CONSTITUENCY</option><option value="4">ASSEMBLY CONSTITUENCY</option></select>
  <span class="help-block" style="margin-left:11px;"><input type="checkbox"  class="userCheckbox"/> &nbsp;<b><font style="color:#000">View News Uploaded By Me Only</font></b></span>  </div>
	<div class="regionClass districtSelReport1 span2" style="display:none;margin-left:-29px;">
    <label style="float: left;"><strong> Select District</strong></label> <s:select name="districtSelReport" id="districtSelReportId1" list="districtsList" theme="simple" listKey="id" listValue="name"/>
    </div>
	<div class="regionClass parliamSelReport1 span2"  style="display:none;margin-left:-29px;">
   <label style="float: left;"><strong>Select Parliament</strong></label><s:select name="parliamSelReport" id="parliamSelReportId1" list="parlConstiList" theme="simple" listKey="id" listValue="name"/>
      </div>
	  
	  <div class="regionClass assembSelReport1 span2"  style="display:none;margin-left:-29px;">
    <label style="float: left;"><strong>Select Assembly </strong></label><s:select name="assembSelReport" id="assembSelReportId1" list="assemConstiList" theme="simple" listKey="id" listValue="name"/>
   </div>

   </div>
   <input type="button" value="submit" onclick="getTotalNewsWithPagination();" class="btn btn-info"/>
   </div>
   <br/>
     <div class="span4" style="float:right;">
    
 <input type="button" value="Add Response" onclick="addToNewsResponse()" class="btn btn-info"/>
 </div>
 <br/>
 
 <div id="errorMsgNewsDiv"></div>

 <div id="totalSelectedNewsCount">Total Selected News Count: <span id="selectedNewsCount"></span></div>

<div id="successMsg" style="float: left; margin-left: 60px; margin-top: 12px;"></div>
<div id='profileManagementMainOuterDiv4' style="display:none">
	<div id='profileManagementHeaderDiv4' class="row-fluid">

		<div class="span10 offset1 text-center alert">NEWS</div>
	
</div>
<div id='newsGallaryDiv' class="divInfo">
	 </div>		

<!-- for  body 4  result  end -->

<div id="ajaxImg" style="display:none;margin-left:300px;margin-top:30px;"><img src="images/icons/goldAjaxLoad.gif"></img></div>
</div>
</div>
<!-- for  body 5  result  start -->
<div id='profileManagementMainOuterDiv5' style="display:none">
	<div id='profileManagementHeaderDiv5' class="row-fluid">

		<div class="span10 offset1 text-center alert">Report</div>
	
</div>
 <form id="generateReportForm" method="post" action="saveNewsUserAction.action" name="generateReportForm" style="border:0px">
   <div><input type="hidden" name="task" id="generateReportFormValues" /></div>
 </form>
<div id='newsReportDiv' class="divInfo">
 <div class="container well">
  <h2 style="text-align: center;margin-bottom:10px;">Create Report</h2>
  <c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
     <div style="margin-bottom: 10px;margin-left: 334px;"><input type="checkbox" style="margin-top:0px;" id="reportChkBoxId" onclick="showHideNewsReport();">&nbsp;&nbsp;Check To Create Report Using News Ids</input></div>
  </c:if>
  <div id="reportHintDiv" align="center" style="margin-left:-25px;margin-bottom: 10px;"> Note: Description should not contain #,$,%,& Special charactors.</div>
  <div id="reportErrDiv" align="center"></div>
  <div id="newsReportInnerDiv">
    <div id="newsReporterrorMessageDiv"></div>
    <label id="fromDateLabelId">From Date:<input type="text" readonly="true" id="fromDateId1" class="inputClass assignNewsDateCls fromDateCls" name="fromDate"></label>
    <label id="toDateLabelId">ToDate :<input type="text" id="toDateId1" class="inputClass assignNewsDateCls toDateCls" readonly="true" name="toDate"></label>
    <table>
      <tr>
        <td>Report Description </td><td><textarea maxlength="330" name="fileDescription" rows="3" cols="20" id="newsreportfileDescription" onkeyup="clearDiv('reportErrDiv');"></textarea></td>
      </tr>
	    <tr><td></td><td><input type="radio" class="reporttypeclass" id="byLevelChecked" onclick="showHideLocationLvl('level');" checked="checked" value="byLocationLvl" name="byLocationLvl"></input> By Location Level&nbsp;&nbsp;<input class="reporttypeclass" type="radio" onclick="showHideLocationLvl('location');" value="byLocation" name="byLocationLvl"></input> By Location</td></tr>
      <tr class="regionLvlClass">
        <td>Select Level</td><td><select id="regionlevel"><option value="1">All</option><option value="2">STATE</option><option value="3">DISTRICT</option><option value="4">CONSTITUENCY</option></select></td>
      </tr>
	  <tr class="regionClass regSelReport">
        <td>Select Level</td><td><select id="reportRegionLevel" onchange="showCorrespondingLocations();"><option value="1">STATE</option><option value="2">DISTRICT</option><option value="3">PARLIAMENT CONSTITUENCY</option><option value="4">ASSEMBLY CONSTITUENCY</option></select></td>
      </tr>
	  <tr class="regionClass districtSelReport">
        <td>Select District</td><td><s:select name="districtSelReport" id="districtSelReportId" list="districtsList" theme="simple" listKey="id" listValue="name"/></td>
      </tr>
	  <tr class="regionClass parliamSelReport">
        <td>Select Parliament</td><td><s:select name="parliamSelReport" id="parliamSelReportId" list="parlConstiList" theme="simple" listKey="id" listValue="name"/></td>
      </tr>
	  
	  <tr class="regionClass assembSelReport">
        <td>Select Assembly</td><td><s:select name="assembSelReport" id="assembSelReportId" list="assemConstiList" theme="simple" listKey="id" listValue="name"/></td>
      </tr>
	  
	  <tr>
        <td>Select News</td><td><select id="newsPriority"><option value="0">All</option><option value="1">Low</option><option value="2">Medium</option><option value="3">High</option></select></td>
      </tr>
	   <tr><td></td>
	 <td>
	 <input type="checkbox" class="" value="byCategory"  id="byCategory"  onclick="showHideLocationLvl1('category');" />&nbsp;By Category&nbsp;&nbsp;
	<input type="checkbox" class="" value="bykeyword"  id="byKeyword" onclick="showHideLocationLvl1('keyword');"  />&nbsp; By Keywords&nbsp;&nbsp;
	  </tr>
      <tr class="categoryClass" style="display:none;">
    <td>Select Categories</td><td><select key="reportGallaryId" style="width: 222px;margin-top:10px;" id="reportGallaryId">
		<option>Select Category</option>
		</select>
	  </td>
      </tr>
	 <tr class="keywordClass" style="display:none;">
        <td>Select Keyword</td><td><select  key="reportKeywordId" id="reportKeywordId" style="width: 222px;margin-top:10px;"><option>Select Keyword</option></select></td>
      </tr>
      </table>
  </div>
  <div id="newsReportNewsIdsInnerDiv" style="display:none;">
    <table style="margin-left: 271px;">
       <tr>
        <td>Report Description </td><td><textarea maxlength="330" name="fileDescriptionNew" rows="3" cols="20" id="newsreportfileDescription1" onkeyup="clearDiv('reportErrDiv');"></textarea></td>
      </tr>
	  <tr>
        <td>News Ids </td><td><textarea maxlength="330" name="fileIdsAll" rows="3" cols="20" id="fileIdsAll" ></textarea></td>
      </tr>
	  <tr>
        <td colspan="2">Note : Please enter all news Ids with comma( <b>,</b> ) separator</td></td>
      </tr>
	</table>
  </div>
  <div class="form-actions text-center">
    <input type="button" value="submit" class="btn btn-info" id="getNewsreport" style="margin-left:360px;float:left;" onclick="getNewsDetailsForNewsReportGeneration()"/>
	<input type="button" value="Generate Report" class="btn btn-info" id="createNewsreport" style="display: none;" onclick="saveNewsReport()"/>
    <img id="newsReportAjaxImg" src="images/search.jpg" style="display:none;"/>
  </div>
   <div id="reportGenaratorNewsDiv" style="display:none;">
    <p style="margin-left: 20px; margin-bottom: 7px; margin-top: -15px;"><b>Total Selected News Count: </b><span id="reportGenaratorSpanCLS"></span></p>
	 
	 
	 <label class="checkbox inline"><input type="button" id="newsReportSelectAllCheckBox" value="Select All"  class="btn"/></label>
	 <label class="checkbox inline"><input type="button" id="newsReportUnSelectAllCheckBox" value="Unselect All" class="btn"/></label>
	 
	</div>
  <div id="locationWiseNewsDiv" class="divInfo" style="display:none;"></div>
  <div id="newsReportBtnDiv" style="display:none;"><input type="button" value="Generate Report" class="btn btn-info" id="savenewsReport" onclick="saveNewsReport()"/></div>
 </div>
</div>			
</div>

	 <!-- for  body 5  result  end -->
	 
<!-- for  body 6  result  start -->
<div id='profileManagementMainOuterDiv6' style="display:none">
	<div id='profileManagementHeaderDiv6' class="row-fluid">
	<div class="span10 offset1 text-center alert">KeyWords</div>
	</div>
<div id='keyWordsMainDiv' class="divInfo">
		
	 </div>	
</div>	 

<!-- for  body 6  result  end -->
<!-- for  body 7  result  start -->
<div id='profileManagementMainOuterDiv7' style="display:none">
	<div id='profileManagementHeaderDiv7' class="row-fluid">
	<div class="span10 offset1 text-center alert">News Report</div>
	</div>
<div id='newsReportsMainDiv' class="divInfo">
		
	 </div>	
</div>	 
<div id='profileManagementMainOuterDivStatus' style="display:none">
	<div id='profileManagementHeaderStatus' class="row-fluid">
	<h2 class="span12 text-center" style="background-color: #F5F5F5;font-weight: bold;padding-bottom: 43px;">News Articles Uploaded By Users</h2>
	</div>
<div id='newsUploadsReports' class="divInfo">
		
	 </div>	
</div>
<div id="createCandidateDiv" style="display:none;">

<div id="errorMsgDiv"></div>
<table style="margin-top: 24px;"><tr>
<td>Select Party</td>
<td><select id="partySelectNewList">
</select></td></tr>

<tr><td>Candidate Name</td>
<td><input type="text" id="newCandidateName"/></td></tr>
<tr>
<td>Designation</td>
<td><select id="designationsList"></select></td>
</tr>
<tr>
<td>Location</td>
<td><select id="locationId" onChange="getTypeOfConstituency(this.value);"><option value=0>Select Location</option><option value=3>Country</option><option value=1>Assembly Constituency</option><option value=2>Parliment Constituency</option></select></td>
</tr>  
<tr style="display:none;" id="pcConstituencyRow">
<td>Constituency</td>
<td>
<s:select name="parliamSelReport"  id="parliamSelReportId" list="parlConstiList1" theme="simple" listKey="id" listValue="name"/></td>
</tr>
<tr style="display:none;" id="acConstituencyRow">
<td>Constituency</td>
<td>
<s:select name="assembSelReport"  id="assembSelReportId" list="assemConstiList1" theme="simple" listKey="id" listValue="name"/></td>
</tr>
</table>
<input type="button" value="submit" class="btn" id="createCandidateId" key="'+key+'" partyListId="'+partyListId+'"/>

</div>

<div id='keyWordsMainDiv' class="divInfo">
		
</div>
<div id='showKeywordsDiv' align="center" style="display:none; width: 400px; margin: 15px auto 0px auto;border:1px solid #CCCCCC; padding:15px;"><img src="images/icons/goldAjaxLoad.gif" /></div>
<div id="alterKeywords">
	<div id='profileManagementMainOuterDiv8' style="display:none">

		<div id='keywordsDiv' class="container well">


		<div id="newKeywordDiv" align="center">
		<h2 align="center">Create New Keyword</h2>
			<div align="center" style="width: 400px;padding:15px;">
				<div id="statusDiv1" align="center" style="margin-top: 25px;"></div>
				<div id="keywordHintDiv"> Note: Keyword should not contain #,$,%,& Special charactors.
	</div>
							Enter Keyword <span style="margin-left: 15px;">:</span> <input type="text" id="newKeyword" style="margin-top: 10px;" onkeyup="clearDiv('statusDiv1');"/>
							<br>
							<button class="btn btn-success" onclick="isKeywordExist();" style="margin-left: 55px;">Create New Keyword </button>
			</div>
		</div>
		
		
		
		
		<div id="mergeKeywordDiv" align="center" style="display:none;">
		<h2 align="center">Merge Keywords </h2>
		<div style="margin-top:25px;padding:15px;width: 600px;">
				<div id="statusDiv2" align="center" style="margin-bottom: 10px;"></div>
				<div id="mergeHintDiv"> Note: New Keyword should not contain #,$,%,& Special charactors.
	</div>
						<div id="keywordLists" style="width: 550px; margin-left: 60px;">
						  	Select Keywords <span style="margin-left: 15px;">:</span>
							<select  style="width:400px;" id="keywordsList"> </select>
						</div>
						<div style="width: 400px; margin-left: 66px;">
							Enter New Keyword <span>:</span> <input type="text" id="aliasKeyword" style="margin-top: 10px;" onkeyup="clearDiv('statusDiv2');"/>
						</div>
							<button class="btn btn-success" onclick="mergeKeywords();" style="margin-left: 90px;"> Merge Keywords </button>
		</div>
	
		</div>	
	</div>
</div>
			
</div>

<!-- updared by prasad for Actions Div-->
<div id="newDesignationDiv" style="display:none;" align="center" class="container well">
	<h2 align="center">Create New Designation</h2>
	<div align="center" style="width: 410px; margin: 2px 0px 2px 12px;padding:15px;">
	<div id="statusDivForDesignation" align="center" style="margin :10px 0px 15px 13px;"></div>
	<div id="dHintDiv" style="margin-left: 25px;"> Note: Designation should not contain #,$,%,& Special charactors.
	</div>		
	Enter Designation <span style="margin-left: 15px;">:</span> <input type="text" id="designationId" style="margin-top: 10px;" onkeyup="clearDiv('statusDivForDesignation');"/>
	<br>
	<button class="btn btn-success" onclick="createNewDesignation();">Create New Designation </button>
</div>
</div>


<div id="newPartyCreationDiv" style="display:none;" align="center" class="container well">
	<h2 align="center">Create New Party</h2>
	<div align="center" style="width: 500px; margin: 15px 0px 0px 40px;padding:15px;">
	<div id="statusForParty" align="center" style="margin: 2px 0px 2px 55px;"></div>
	<div id="pHintDiv"> Note: Name should not contain #,$,%,& Special charactors.
	</div>
	Enter Long Name <span style="margin-left: 15px;">:</span> <input type="text" id="partyLongName" style="margin-top: 10px;" onkeyup="clearDiv('statusForParty');"/>
	<br>
	Enter Short Name <span style="margin-left: 15px;">:</span> <input type="text" id="partyShortName" style="margin-top: 10px;" onkeyup="clearDiv('statusForParty');"/>
	</br><br>
	<button class="btn btn-success" onclick="createNewParty();">Create New Party </button>
	</div>
</div>

<div id="newCandidateCreationDiv" style="display:none;" align="center" class="container well">
	<h2 align="center">Create New Candidate</h2>
	<div align="center" style="width: 400px; margin: 15px 0px 0px 40px;padding:15px;">

	
<div id="errorMsgDiv1" style="color:red;"></div>
	<table style="margin-top: 24px;"><tr>
<td>Select Party</td>
<td><select id="partySelectNewList1">
</select></td></tr>

<tr><td>Candidate Name</td>
<td><input type="text" id="newCandidateName1"/></td></tr>
<tr>
<td>Designation</td>
<td><select id="designationsList1"></select></td>
</tr>
<tr>
<td>Location</td>
<td><select id="locationId1" onChange="getTypeOfConstituencyForCreate(this.value);"><option value=0>Select Location</option><option value=3>Country</option><option value=1>Assembly Constituency</option><option value=2>Parliment Constituency</option></select></td>
</tr>  
<tr style="display:none;" id="pcConstituencyRowForCreate">
<td>Constituency</td>
<td>
<s:select name="parliamSelReport"  id="parliamSelReportIdForCreate" list="parlConstiList1" theme="simple" listKey="id" listValue="name"/></td>
</tr>
<tr style="display:none;" id="acConstituencyRowForCreate">
<td>Constituency</td>
<td>
<s:select name="assembSelReport"  id="assembSelReportIdForCreate" list="assemConstiList1" theme="simple" listKey="id" listValue="name"/></td>
</tr>
</table>
<input type="button" value="submit" class="btn" id="createCandidateId1"/>
</div>
</div>
<div id="newEditCandidateDiv" style="display:none;" align="center" class="container well" >
	<h2 align="center">Edit Candidate</h2>
	<div align="center" style="width: 500px; margin: 15px 0px 0px 40px;padding:15px;">
	<div id="statusForParty" align="center" style="margin-bottom: 2px;margin-top: 2px;"></div>
	<div id="caHintDiv" align="right"> Note: Candidate Name should not contain #,$,%,& Special charactors.
	</div>
<div id="errorMsgDiv2" style="color:red;margin-top:5px;margin-left:75px"></div>
	<table style="margin-top: 24px;"><tr>
<td>Select Party</td>
<td><select id="EditpartySelectNewList" onchange="getEditCandidatesListByPartyId(this.value,'EditCandidateListForParty')">
</select></td></tr>

<tr><td>Candidate Name</td>
<td><select id="EditCandidateListForParty" onchange="getTextCandidateId()" >';
		   <option value="0">Select Candidate</option>';
		</select></td></tr>
		
	<tr><td>Candidate</td>
<td><input type="text" id="textCandidate" onkeyup="clearDiv('errorMsgDiv2');"/></td></tr>
<tr>
<td>Select Current Party</td>
<td><select id="currentpartySelectNewList">
</select></td></tr>
<tr>
<td>Designation</td>
<td><select id="designationsList2"></select></td>
</tr>
<tr>
<td>Location</td>
<td><select id="editcandidateLocType" onChange="getTypeOfConstituencyForEdit(this.value,'editacConstituencyRow','editpcConstituencyRow');"><option value=0>Select Location</option><option value=3>Country</option><option value=1>Assembly Constituency</option><option value=2>Parliment Constituency</option></select></td>
</tr>  
<tr style="display:none;" id="editpcConstituencyRow">
<td>Constituency</td>
<td>
<s:select name="parliamSelReport"  id="editparliamSelReportId" list="parlConstiList1" theme="simple" listKey="id" listValue="name"/></td>
</tr>
<tr style="display:none;" id="editacConstituencyRow">
<td>Constituency</td>
<td>
<s:select name="assembSelReport"  id="editassembSelReportId" list="assemConstiList1" theme="simple" listKey="id" listValue="name"/></td>
</tr>
</table>
<input type="button" value="Update" class="btn" onclick="updateExistingCandidate();" />
</div>
</div>

<!-- updated by prasad for Actions Div-->

<!-- for  body 7  result  end -->
<script>
function showHideNewsReport(){
       $("#fileIdsAll").val('');
	   $("#newsreportfileDescription1").val('');
 if($("#reportChkBoxId").is(':checked')){
    $("#createNewsreport").css("display","none");
    $("#locationWiseNewsDiv").css("display","none");
    $("#newsReportBtnDiv").css("display","none");
    $("#locationWiseNewsDiv").html("");
	$("#reportGenaratorNewsDiv").css("display","none");
	
    $('#reportErrDiv').html('');
   $("#newsReportInnerDiv").hide();
   $("#getNewsreport").hide();
   $("#createNewsreport").show();
   $("#newsReportNewsIdsInnerDiv").show();
 }else{
     $("#newsReportInnerDiv").show();
     $("#newsReportNewsIdsInnerDiv").hide();
	 $("#getNewsreport").show();
     $("#createNewsreport").hide();
 }
}
function getTypeOfConstituency(value)
{	
	if(value == 1)
	{
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').show();
	}
	else if(value == 2)
	{
		$('#pcConstituencyRow').show();
		$('#acConstituencyRow').hide();
	}
	
	else
	{
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').hide();
	}	
}

function getTypeOfConstituencyForCreate(value)
{	
	if(value == 1)
	{
		$('#pcConstituencyRowForCreate').hide();
		$('#acConstituencyRowForCreate').show();
	}
	else if(value == 2)
	{
		$('#pcConstituencyRowForCreate').show();
		$('#acConstituencyRowForCreate').hide();
	}
	else
	{
		$('#pcConstituencyRowForCreate').hide();
		$('#acConstituencyRowForCreate').hide();
	}
}


var keywordGallaries;
var newsDetails;
var bvalue = false;
var noOfRowsPerPage = 10;
var modifiedRecord = 0;
$(document).ready(function() {

$(document).ready(function(){
$('#CErrMsgDiv').html('');
	$(".dropdown-menu > li > a").click(function(){	
		$('#partyLongName').val('');
		$('#partyShortName').val('');
		$('#designationId').val('');
		$('#newsCateName').val('');
		$('#newsCateDesc').val('');
		$('#sourceName').val('');
		$('#textCandidate').val('');
		$('#statusForParty').html('');
		$('#statusDivForDesignation').html('');
		$('#errorDiv').html('');
		$('#errorMsgDiv2').html('');
		$('#CErrMsgDiv').html('');
		$('#reportErrDiv').html('');
		
		$('#statusDiv1').html('');
		$('#statusDiv2').html('');		
		$('#aliasKeyword').val('');
		$('#newKeyword').val('');
		$('#newsreportfileDescription').val('');
	});
	$('#newKeywordBtn').click(function(){	
		$('#newKeywordDiv').css("display","block");
		$('#mergeKeywordDiv').css("display","none");
        $('#showKeywordsDiv').css("display","none");
		$('#statusDiv1').html('');
		$('#statusDiv2').html('');		
	});

	$('#mergeKeywordBtn').click(function(){
		$('#newKeywordDiv').css("display","none");
		$('#mergeKeywordDiv').css("display","block");
		$('#showKeywordsDiv').css("display","none");
		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
	});
	$("#newDesignationDiv").css("display","none");
	 $("#newEditCandidateDiv").css("display","none");
	$("#newCandidateCreationDiv").css("display","none");
    $("#newPartyCreationDiv").css("display","none");
	$('#partyManagementTabId').addClass('menuActive');
});


	showNewsGallaey(null);

	$(".nav-tabs li a").click(function()
			{
				$(".nav-tabs li").removeClass('active');
				$(this).parent("li").addClass('active');
				
			});
			$(".highlight").live("click",function(){
				
				$(this).css("background","#BBBB51");
			});
			$(".highlight").live("blur",function(){
				
				$(this).css("background","#51A351");
			});
$(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date(),
	
		
	}).datepicker("show");

});



$(document).on("click",'#fromDateId1 , #toDateId1', function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
        changeYear: true, 
		maxDate: new Date()
	    
	}).datepicker("show");
});
	


//CategoryGallary
//gallary
$("#gallaryCheckboxId").live("click",function(){
 $("#categoryCheckboxId").attr('checked',false);
 $("#gallaryAllCheckboxId").attr('checked',false);
 $("#respenseNewsList").find('option').remove();
 $("#respenseNewsList").css('display','none');
 $("#buttonsDiv").css('display','none');
 $("#categoryShowHideDiv").css('display','none');
 if($("#gallaryCheckboxId").is(":checked"))
 {
  getCandidateGallaries();
 }
	
});
$("#gallaryList").live("change",function(){
 getNewsTitlesForACandidateByGallaryId();
	
});
$("#categoryGallarySelect").live("change",function(){
 getNewsTitlesForACandidateByGallaryId();
	
});
//category
$("#categoryCheckboxId").live("click",function(){
 $("#gallaryCheckboxId").attr('checked',false);
 $("#gallaryAllCheckboxId").attr('checked',false);
 $("#candidateNewsList").css("display","none");
 $("#buttonsDiv").css("display","none");
 $("#respenseNewsList").find("option").remove();
 $("#respenseNewsList").css("display","none");
 $("#categoryGallary").attr('checked',false);

 $("#candidateNewsList").css("display","none");

 $("#respenseNewsList").find('option');
 $("#respenseNewsList").css("display","none");

 $("#gallaryShowHideDiv").css("display","none");

 if($("#categoryCheckboxId").is(":checked"))
  getCandidatecategories();
	
});
$("#gallaryList").live("change",function(){
 $("#respenseNewsList").find('option').remove();
	
});

$("#categoryGallary").live("click",function(){

	var candidateId = $("#candidatesList").val();
	$('#dateErrorMessage').html('');
	$("#candidateNewsList").css('display','none');
    $("#buttonsDiv").css('display','none');
	$("#respenseNewsList").css('display','none');
	$('#noNewsError').html('');

  if($("#categoryGallary").is(":checked"))
  {
	 
	 if(candidateId == 0)
	 {
       $('#dateErrorMessage').html('Please Select Candidate.');
		return;
	 }
      if($("#candidateCategoryId").val() == 0)
	  {
        $('#dateErrorMessage').html('Please Select Category.');
		return;
	  }

	 var categoryIdsArray = new Array();
    
	  $('#candidateCategoryId > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
     });
       if(categoryIdsArray == null || categoryIdsArray == "null" || categoryIdsArray.length == 0)
	   {
         $('#dateErrorMessage').html('Please Select Category.');
		 return;
	   }
	$("#gallaryShowHideDiv").css('display','block');
	
      var jsObj={
		candidateId:candidateId,
		categoryIds:categoryIdsArray,
		task:'getGallariesForSelectedCategory'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGallariesForSelectedCategoryAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
  }
  else
	{
     clearOptionsListForSelectElmtId('categoryGallarySelect');
	 $("#gallaryShowHideDiv").css("display","none");
	}

});
$("#candidateCategoryId").live("click",function(){
	
	$("#categoryGallary").attr('checked', false);
	$("#gallaryShowHideDiv").css("display","none");
});


//Category Onchange

$("#candidateCategoryId").live("change",function(){

  var candidateId = $("#candidatesList").val();
  var categoryId = $("#candidateCategoryId").val();
  var fromDate = $("#fromDateId").val();
  var toDate = $("#toDateId").val();
  $("#respenseNewsList").find('option').remove();
  $("#candidateNewsList").find('option').remove();

  $("#respenseNewsList").css('display','none');
  $("#candidateNewsList").css('display','none');
  $("#buttonsDiv").css('display','none');

  $("#noNewsError").html('');
  $("#dateErrorMessage").html('');

  if(categoryId == 0)
  {
    $("#noNewsError").html('Please Select Category.');
	return;
  }
  
  
  if($("#categoryGallary").is(":checked") == false)
  {
	 $("#respenseNewsList").css('display','inline-block');
     $("#candidateNewsList").css('display','inline-block');
	 $("#buttonsDiv").css('display','inline-block');

    var jsObj={
		candidateId:candidateId,
		categoryId:categoryId,
		fromDate:fromDate,
	    toDate:toDate,
		task:'getNewsForACandidateByCategoryId'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "getNewsForACandidateByCategoryIdAction.action?"+rparam;
	 callAjax(jsObj, url);
  }
	
});


$("#assignNewsgallaryList").live("change",function(){
getNewsTitlesByGalleryId("assignNewsgallaryList");
 
});
$("#responseNewsgallaryList").live("change",function(){
	
getNewsTitlesByGalleryId("responseNewsgallaryList");	
});

$("#assignNewsbtn").live("click",function(){
   
    $("#errorMessageDiv").html('');
    
	var gallaryId = $("#assignNewsgallaryList").val();
    var fileGalleryId = $("#newsTitlesSelectList").val();
	var radioVal = $('input[name=assignNewsRadio]:checked').val();
	var responseId =0;
	var candidateId =0;
	var tempVar = "";
	var resFileGalId;
	if(gallaryId == 0)
	{
     $("#errorMessageDiv").html('Please Select Gallery.');
	 return;
	}
    else if(fileGalleryId == 0)
    {
     $("#errorMessageDiv").html('Please Select News.');
	 return;
	}
	if(radioVal == "assignResponse")
	{

	  var responseGalleryId = $("#responseNewsgallaryList").val();
	  resFileGalId = $("#responseNewsTitlesSelectList").val();
      
	   if(responseGalleryId == 0)
      {
       $("#errorMessageDiv").html('Please Select Response Gallery.');
	   return;
	  }
	  else if(fileGalleryId == 0)
      {
       $("#errorMessageDiv").html('Please Select News.');
	   return;
	  }
	  else if(fileGalleryId == resFileGalId)
	  {
       $("#errorMessageDiv").html("We Can't assign this news to response.");
	   return;
	  }
	  tempVar = "assignResponse";
	}
	else
    {
		var partyId = $("#partiesList").val();
		candidateId = $("#candidatesLists").val();
		resFileGalId = 0;
		if(partyId == 0)
		{
		 $("#errorMessageDiv").html('Please Select Party.');
	     return;
		}
		else if(candidateId == 0)
		{
         $("#errorMessageDiv").html('Please Select Candidate.');
	     return;
		}

	   
	   tempVar = "assignToCandidate";

	}
	var jsObj={
		candidateId:candidateId,
		fileGalleryId:fileGalleryId,
		resFileGalId:resFileGalId,
	    tempVar:tempVar,
		task:'assignResToCandidateOrAGallary'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "assignResToCandidateOrAGallaryAction.action?"+rparam;
	 callAjax1(jsObj, url);

	
});


$(".assignNewsRadioCls").live("click",function(){
 var radioVal = $('input[name=assignNewsRadio]:checked').val();
 
 if(radioVal == "assignToCandidate")
 {
  $("#candidateShowHideDiv").css("display","block");
  $("#showHideResponseGallaryDiv").css("display","none");
  getCandidatesByPartyId();
 }
 else
 {
   $("#showHideResponseGallaryDiv").css("display","block");
    $("#candidateShowHideDiv").css("display","none");
 }
	
});


});//End of Ready 


</script>

<script>


var count = 0;
function changeLanguage(){
	if($("#sourceTelugu").is(':checked') == true)
	{
		$('#newsfileTitle').addClass('enadu');
	}
	else
	{
		$('#newsfileTitle').removeClass('enadu');
	}

	if($("#sourceDescTelugu").is(':checked') == true)
	{
		$('#newsfileDescription').addClass('enadu');
	}
	else
	{
		$('#newsfileDescription').removeClass('enadu');
	}
	
	if($("#newsSynopsysDescTelugu").is(':checked') == true)
	{
		$('#newsSynopsysDesc').addClass('enadu');
	}
	else
	{
		$('#newsSynopsysDesc').removeClass('enadu');
	}

}


//changes by anil
function htmlEntity(aa) {

var bb = '';
for (i = 0; i < aa.length; i++)
bb += hoj(aa.charAt(i));
return bb;
}
function hoj(d) 
{         if (d == '<')
         return '&lt;';
       else
        if (d == '>')
          return '&gt;';
    
if (d == '<')
return '&lt;';
if (d == '>')
return '&gt;';
if (d == '&')
return '&amp;';
  
   if (d.charCodeAt(0) > 127)
   return '&#' + d.charCodeAt(0) + ';';
  return d;
    }

	function buildNewsOfAGallary(divId , results)
{
 $('#noNewsError').html('');
  $('#'+divId).find('option').remove();
  if(results != null && results.length > 0){
	  	  $('#candidateNewsList , #respenseNewsList , #buttonsDiv').show();


	 $.each(results , function(index , value){

		if(value.flag == true)
		  $('#'+divId).append('<option class="enadu" title="'+value.name+'" value="'+value.id+'">'+value.name+'</option>');
		else
			$('#'+divId).append('<option class="notEenadu" title="'+value.name+'" value="'+value.id+'">'+value.name+'</option>');


	});
  }else{
	  $('#noNewsError').html('<b style="color:red;">No news found.Changing the date range may help you.</b>');
	  $('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();

  }

}



function buildGallaries(divId , results)
{
   $('#'+divId).find('option').remove();
   $('#'+divId).append('<option value="0">Select Gallery</option>');

  $.each(results,function(key , value){
   $('#'+divId).append('<option value="'+value.id+'">'+value.name+'</option>');

  });


}
function IsNumeric(val) {
	$('#Err4Numer').hide();
	if (!(Number(val)==val)) {
	$('#Err4Numer').show();
		$('.pageno').val('');
	}
}
function IsNumeric1(val) {
	$('#Err4Numer1').hide();
	if (!(Number(val)==val)) {
		$('#Err4Numer1').show();
		$('.newslength').val('');
	}
}


//Gallary

function getCandidateGallaries()
{
  $("#candidateNewsList").css("display","none");
  $("#gallaryShowHideDiv").css("display","block");
    var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesList").val();
	$("#noNewsError").html('');
	$("#dateErrorMessage").html('');
	if(candidateId == 0)
	{
	  $("#noNewsError").html('Please Select Candidate');
	  return;	
	}
	   fromDate = $("#fromDateId").val();
	   toDate = $("#toDateId").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $("#noNewsError").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $("#noNewsError").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $("#noNewsError").html('Please Select To Date');
		 return;
	   }
	   /* else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $("#noNewsError").html('Invalid Date Selection.');
         return;
	   }  */
	

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedGallaries'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedGallariesAction.action?"+rparam;
	callAjax(jsObj, url);


}

function getCandidatecategories()
{
	$("#categoryShowHideDiv").css("display","block");
   var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesList").val();
	$("#dateErrorMessage").html('');
	$('#noNewsError').html("");
	if(candidateId == 0)
	{
	  $("#dateErrorMessage").html('Please Select Candidate');
	  return;	
	}
	
	   fromDate = $("#fromDateId").val();
	   toDate = $("#toDateId").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $("#dateErrorMessage").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $("#dateErrorMessage").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $("#dateErrorMessage").html('Please Select To Date');
		 return;
	   }


	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedCategories'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedCategoriesAction.action?"+rparam;
	callAjax(jsObj, url);

}

function getNewsTitlesForACandidateByGallaryId()
{
	$("#noNewsError").html('');
	$("#dateErrorMessage").html('');
  var candidateId = $("#candidatesList").val();
	var gallaryId = $("#gallaryList").val();
	var fromDate = $("#fromDateId").val();
	var toDate = $("#toDateId").val();

	$("#respenseNewsList").find('option').remove();
  $("#candidateNewsList").find('option').remove();

  $("#respenseNewsList").css('display','none');
  $("#candidateNewsList").css('display','none');
  $("#buttonsDiv").css('display','none');

	if(gallaryId == 0)
	{
	  $("#noNewsError").html('Please Select Gallery.');
	  return;
	}

    $("#respenseNewsList").css('display','inline-block');
    $("#candidateNewsList").css('display','inline-block');
    $("#buttonsDiv").css('display','inline-block');

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		gallaryId:gallaryId,
		task:'getNewsTitlesForACandidate'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getNewsTitlesForACandidateAction.action?"+rparam;
	callAjax(jsObj, url);
}

function createOptionsForSelectElement(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

}


function assignNewsToCandidate()
{
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","block");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","block");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
   $("#profileManagementMainOuterDiv7").css("display","none");
   $("#profileManagementMainOuterDivStatus").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
  var str = '';
  str +='<div id="content" style="width:650px;" class="assignNewsDivCls">';
  str +='<h2 style="text-align: center;">Assign News</h2>';
  str +='<div id="errorMessageDiv"></div>';
  str +='<div id="assignNewsInnerDiv">';
  str +='<label id="fromDateLabelId">From Date:<input type="text" readonly="true" id="fromDateId1" class="inputClass assignNewsDateCls fromDateCls" name="fromDate"></label>';
  str +='<label id="toDateLabelId">ToDate :<input type="text" id="toDateId1" class="inputClass assignNewsDateCls toDateCls" readonly="true" name="toDate"></label>';
  str +='<label>Select Gallery: <select id="assignNewsgallaryList"></select></label>';
  str +='<label>Select News: <select id="newsTitlesSelectList"></select></label>';
  str +='<div id="assignNewsRadioDiv">';
  str +='<input id="assignNewsResradio" type="radio" value="assignResponse" name="assignNewsRadio" class="assignNewsRadioCls" checked="true"/>Set As Response';
  str +='<input id="assignNewsCandidateRadio" type="radio" value="assignToCandidate" name="assignNewsRadio" class="assignNewsRadioCls"/>Assign Candidate';
  str +='</div>';
  
  str +='<div id="showHideResponseGallaryDiv">';
  str +='<label>Select Gallery: <select id="responseNewsgallaryList"></select></label>';
  str +='<label>Select News: <select id="responseNewsTitlesSelectList"></select></label>';
  str +='</div>';

  str +='<div id="candidateShowHideDiv" style="display:none;">';
  str += '<label>Select Party : <font class="requiredFont">*</font>';
  str += ' <select id="partiesList" name="party" onchange="getCandidatesByPartyId()">';

  str +='</select></label>';
	

  str +='<label>Select Candidate : <font class="requiredFont">*</font><select id="candidatesLists"></select></label>';
  str +='</div>';
  str +='<input type="button" value="submit" class="btn btn-info" id="assignNewsbtn"/>';

  str +='</div>';
  str +='</div>';
  $("#newsAssignGallaryDiv").html(str);

  getGalleryListForAParty();
}


function getGalleryListForAParty()
{
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
    var locationIdsArray = new Array();
	var locationScope = "";
	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		locationIdsArray:locationIdsArray,
		locationScope:locationScope,
		task:'getGalleryListForAParty'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGalleryListForAPartyAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getNewsTitlesByGalleryId(divId)
{
	var gallaryId;
	var fromDate;
	var toDate;
  if(divId == "assignNewsgallaryList"){
   gallaryId = $("#assignNewsgallaryList").val();
	fromDate = $("#fromDateId1").val();
	toDate = $("#toDateId1").val();
   }

  if(divId == "responseNewsgallaryList"){
    gallaryId = $("#responseNewsgallaryList").val();
	toDate = $("#toDateId1").val();
	fromDate = $("#fromDateId1").val();
   }
 $("errorMsgDiv").html('');
 if(gallaryId == 0)
 {
   $("errorMsgDiv").html('Please Select Gallery.');
   return;
 }

 var jsObj={
		gallaryId:gallaryId,
		fromDate:fromDate,
	    toDate:toDate,
        divId:divId,
		task:'getNewsByGalleryId'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "getNewsByGalleryIdAction.action?"+rparam;
	 callAjax(jsObj, url);

}

function getCandidatesByPartyId()
{
 $("#errorMessageDiv").html('');
 var partyId = $("#partiesList").val();
 if(partyId == 0)
 {
   $("#errorMessageDiv").html('Please Select Party.');
   return;
 }

  var jsObj = {
			partyId :partyId,
			task : "getCandidatesByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
  callAjax(jsObj,url);
}

function getEditCandidatesByPartyId()
{ 
 $("#errorMessageDiv2").html('');
 var partyId = $("#partiesList").val();
 if(partyId == 0)
 {
   $("#errorMessageDiv2").html('Please Select Party.');
   return;
 }

  var jsObj = {
			partyId :partyId,
			task : "getEditCandidatesByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
  callAjax(jsObj,url);
}
function showAssignNewsStatus(results)
{

	$("#errorMessageDiv").html('');
  if(results.resultCode == 0 && results.message != null)
  {
	  $("#assignNewsgallaryList").val("0");
	  $("#responseNewsgallaryList").val("0");

	  clearOptionsListForSelectElmtId('newsTitlesSelectList');
	  clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');

   $("#errorMessageDiv").html('News is Already Assigned.').css("color","green");
   return;
  }
  else if(results.resultCode == 0 && results.message == null)
  {
	  $("#assignNewsgallaryList").val("0");
	  $("#responseNewsgallaryList").val("0");
	  clearOptionsListForSelectElmtId('newsTitlesSelectList');
	  clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');

   $("#errorMessageDiv").html('News Assigned Successfully.').css("color","green");
   return;
  }
  else
  {
   $("#errorMessageDiv").html('Error occured! try again.').css("color","red");
   return;
  }
}	
	
function isDatesValid()
{
	$('#noNewsError').html("");
	var fromDate = $("#fromDateId").val();
	var toDate  = $("#toDateId").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{
		 $('#noNewsError').html("Start Date should be Less Than End Date");
		}
	}
}

function isDatesValid1()
{
	$('#errorMessageDiv').html("");
	var fromDate = $("#fromDateId1").val();
	var toDate  = $("#toDateId1").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorMessageDiv').html("From Date should be Less Than To Date");
		}
	}
}
function saveNewSourceDetails()
{
	$('#errorDiv').html('');
	var sourceName =  $.trim($('#sourceName').val());
	//sourceName = replaceSpeclChars(sourceName);
	if(isValid(sourceName)){
		$('#errorDiv').html('<b style="color:red">Source Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	if(sourceName.length <=0){
		$('#errorDiv').html('Source Name Should not be empty');
		$('#errorDiv').css('color','red')
		return false;
	}
	if(sourceName.trim().length < 5){
		$('#errorDiv').html('<b style="color:red"> Source Name should contains minimum 5 Charactors.</b>');
		return false;		
	}
	var jsObj=
	{
		name  : sourceName,
		task  : "storeSource"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "storeSourceDetails.action?"+rparam;	

	callAjax(jsObj,url);
}

function createNewSource()
{
	//$('#sourceDetails').show();
	var str = "";
	str+='<div id="sourceDetails" class="container well">';
	str +='<h2 align="center" style="margin-left: -59px;margin-bottom:5px;">Create New Source</h2>';
	str+='<div id="sHintDiv" align="center" style="margin-left: 60px;"> Note: Source Name should not contain #,$,%,& Special charactors.	</div>';
	str += "<div id='errorDiv' align='center' style='color:red;margin:0px 0px 10px 40px;'> </div>";
	str +=  '<span style="margin-left: 310px;">Source Name : </span><input type="text" id="sourceName" onkeyup="clearDiv(\'errorDiv\');"></input></br>';
	str +=  '<input type="button" value="Create New Source" onClick="saveNewSourceDetails();" class="btn btn-info" style="margin-left: 358px;"></input>';
	
	str+='</div>';
	$('#newsGallaryDiv').html(str);
	
	
}

$('#updateCategoryId').live('click',function(){
	var jsObj=
	{
		task  : "getAllCategories"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
});
function buildCategoriesOfUser(results,jsObj){
var visibility="";
var sno=0;
	var str='';
	str+='<table class="table table-bordered" id="categoriesDT">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>S.No';
			str+='</th>';
			
			str+='<th>Category';
			str+='</th>';
			
			str+='<th>Access Type';
			str+='</th>';
			
			str+='<th>Is Deleted';
			str+='</th>';
			
			str+='<th>Update';
			str+='</th>';
			
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
		for(var i in results){
		sno=sno+1;
			str+='<tr>';
				str+='<td>'+sno+'</td>';
			
				str+='<td>'+results[i].name+'</td>';
			
			if(results[i].visibility=="false"||results[i].visibility=="public"){
				visibility="public";
			}else{
				visibility="private";
			}
			
				str+='<td>'+visibility+'</td>';
			
				if(results[i].isDeleted=="false"){
					str+='<td onclick="onCategory('+results[i].id+',\'delete\')" title="Delete Category"><img src="images/icons/delete.png"/></td>';
                   
				 str+='<td onclick="editCategory('+results[i].id+',\''+results[i].name+'\',\''+visibility+'\')"><img src="images/icons/edit.png"/></td>';

				}else{
					str+='<td onclick="onCategory('+results[i].id+',\'accept\')" title="Enable this Category"><img src="images/icons/accept.png"/></td>';

					str+='<td><img src="images/icons/edit.png"/></td>';
				}
			
			str+='</tr>';
		}
	str+='</tbody>';
	str+='</table>';
	$('#categoriesTable').html(str);
	
	$('#categoriesDT').dataTable({
		   "iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
	});
}
 $(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
	    modal: true,
		position:'center',
		resizable: false,

	});
});

function onCategory(id,name){
$('#errMsg').html('');
 var jsObj=
	{
		idVal:id,
		name :name,
		task : "onOroffCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteOrAcceptCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
 
}

function editCategory(id,name,visibility){
	$('#errMsg').html('');
	$( "#dialog" ).dialog( "open" );
	$('#categoryNameId').val(name);
	$('input[name=visibility][value='+visibility+']').prop("checked",true);
	$('#idVal').val(id);
	
}

$('#updateCategoryId').click(function(){
	var categoryName=$('#categoryNameId').val();
	var visibility = $('input:radio[name=visibility]:checked').val();
	var idVal=$('#idVal').val();
	
	var jsObj=
	{
		category:categoryName,
		visibility:visibility,
		idVal:idVal,
		task  : "updateCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
});

function buildUpdateStatus(result)
{
	
	$('#newsSuccessDiv').html("<font style='font-weight:bold;color:green;margin-left:50px;'>News Updated Successfully.</font>");
       setTimeout(hideDialog,3000);
    buildNewsDetails();
}

function getAllCategoriesForGallary()
{
 var jsObj=
	{
		task  : "getTotalCategories"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCategoriesListAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function getGallariesForSelectedCategory()
{

  $("#uploadNewsFileErrorDiv1").html('');
  var categoryId = $("#category").val();
  if(categoryId == 0)
  {
    $("#uploadNewsFileErrorDiv1").html('Please Select Category.').css("color","red");
     return;
  }
	 var jsObj=
	{
	    categoryId:categoryId,
		task  : "getGallariesInCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGallariesByCategoryIdAction.action?"+rparam;						
	callAjax1(jsObj,url);
}
function getScopeForUser(index){
  
 var jsObj =
		{ 
            time : timeST,
			divId:"scopeDiv",
  		    task:"getLocationScope",
			index:index
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationScopeAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}


function showUserAccessLocationScopeList(results,index)
{
  if(index == undefined)
  index = 0;
 
  var id = results.scopeId;
  var str = '';

  $("#scopeDiv"+index).val(id);
  
  if(id==0 || id==1)
   str +='';

  else if(id==2)
  {
    str += '<div class="span2">';
    str += ' <label>State';
	 if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
	str += '</label>';
    str += ' <select class="input-block-level" name="locationValue['+index+']" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
	str += '</div>';
	
	document.getElementById("showScopeSubs"+index).innerHTML = str;

    buildSelectOptionVOList(results.stateList,'stateDiv'+index,1);
  }

  else if(id == 3)
  {
   
    str += '<div class="span2">';
    str += ' <label>State</label>';
    str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
	str +='</div>';

	str += '<div class="span2">';
    str += ' <label>District';
	if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
	str += '</label>';
    str += ' <select class="input-block-level" name="locationValue['+index+']" id="districtDiv'+index+'" ></select>';
	str +='</div>';
    
    document.getElementById("showScopeSubs"+index).innerHTML = str;

    buildSelectOptionVOList(results.stateList,'stateDiv'+index,results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv'+index,results.districtId);

  }
  else if(id == 4)
  {
  
    str += '<div class="span2">';
    str += ' <label>State</label>';
    str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
	str +='</div>';

	str += '<div class="span2">';
    str += ' <label>District</label>';
    str += ' <select class="input-block-level" id="districtDiv'+index+'"></select>';
	str +='</div>';

	str += '<div class="span2">';
    str += ' <label>Assembly Consti';
	if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
	str +='</label>';
    str += ' <select class="input-block-level" id="constituencyDiv'+index+'" name="locationValue['+index+']"></select>';
	str +='</div>';
    
	document.getElementById("showScopeSubs"+index).innerHTML = str;

    buildSelectOptionVOList(results.stateList,'stateDiv'+index,results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv'+index,results.districtId);
	buildSelectOptionVOList(results.constituencyList,'constituencyDiv'+index,results.constituencyId);
	
  }
   
}

function buildSelectOptionVOList(optionsList,elmt,populatedId)
{
	
 if(!elmt || optionsList == null)
  return;
 
 var divEle = document.getElementById(elmt);
  var option = document.createElement('option');
	option.value="0";
	option.text="Select";
  if(populatedId == null)
  {
	try
	{
		divEle.add(option,null); // standards compliant
	}
	catch(ex)
	{
		divEle.add(option); // IE only
	}
  }

	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			divEle.add(option,null); // standards compliant
		}
		catch(ex)
		{
			divEle.add(option); // IE only
		}
	}

	if(populatedId != null)
     divEle.value = populatedId;
	 


}


function createReport()
{
  $("#locationWiseNewsDiv").html("");
 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","block");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDivStatus").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  $('#showKeywordsDiv').css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
  $("#newsReportInnerDiv").show();
  $("#newsReportNewsIdsInnerDiv").hide();
  <c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
    $("#reportChkBoxId").removeAttr('checked'); 
	$("#getNewsreport").show();
	$("#createNewsreport").hide();
  </c:if>
$("#fromDateId1").datepicker({ 
dateFormat: 'dd/mm/yy',
maxDate : new Date()
 });
$("#fromDateId1").datepicker("setDate", new Date());
$("#toDateId1").datepicker({ 
dateFormat: 'dd/mm/yy',
maxDate : new Date()
 });
$("#toDateId1").datepicker("setDate", new Date());
$("#newDesignationDiv").css("display","none");
 $("#newEditCandidateDiv").css("display","none");
$("#newCandidateCreationDiv").css("display","none");
$("#newPartyCreationDiv").css("display","none");
$("#newsReporterrorMessageDiv").html('');
$("#reportGenaratorNewsDiv").css("display","none");
$("#locationWiseNewsDiv").html('');
$("#newsReportBtnDiv").css("display","none");
$("#byCategory").attr("checked", false);
$("#byKeyword").attr("checked", false);
}
function getNews()
{
$("#newsReportAjaxImg").css({ 'display': 'inline-block' });
$("#locationWiseNewsDiv").css("display","none");
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
	var regionLevel = $("#regionlevel").val();
	var importance = $("#newsPriority").val();
	var reportRegionLevel = $("#reportRegionLevel").val();
	
	var reportRegionLevelVal = 0;
	 var type="";
	if($("#byLocationLvl").is(':checked')){
	  type = "byLevel";
	}else{
	  type = "byRegion";
	  if(reportRegionLevel == 1){
	      reportRegionLevelVal = 1;
	  }else if(reportRegionLevel == 2){
		  reportRegionLevelVal = $("#districtSelReportId option:selected").val();
	  }else if(reportRegionLevel == 3){
		  reportRegionLevelVal = $("#parliamSelReportId option:selected").val();
	  }else if(reportRegionLevel == 4){
		  reportRegionLevelVal = $("#parliamSelReportIdForCreate option:selected").val();
	  }else if(reportRegionLevel == 5){
		  reportRegionLevelVal = $("#assembSelReportIdForCreate option:selected").val();
	  }else if(reportRegionLevel == 6){
		  reportRegionLevelVal = $("#countrySelReportIdForCreate option:selected").val();  
	  }
	} 

	var jsObj = {
			task: 'getNews',
			fromDate:fromDate,
			toDate:toDate,
			regionLevel:regionLevel,
			importance:importance,
			reportRegionLevel:reportRegionLevel,
			reportRegionLevelVal:reportRegionLevelVal,
			keywordsArr:keywordsArr,
			categoryArr:categoryArr,
			type:type
	};
 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAllNewsForAUserAction.action?"+rparam;
	callnewAjax(jsObj,url);
}


function saveNewsReport()
{
 var isadmin = false;
 var isNewsIds = false;
 <c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
   isadmin = true;
 </c:if>
	 if(isadmin){
	     if($("#reportChkBoxId").is(':checked')){
		     isNewsIds = true;
		 }
	 }
	if(!isadmin){
	   saveAllNews();
	}else if(isadmin && isNewsIds){
	  saveAllNewsWithNewsIds();
	}else{
	     saveAllNews();
	}
}

function saveAllNewsWithNewsIds(){
$("#newsReporterrorMessageDiv").html("");
 var str = '<font color="red">';
	var flag =false;
         $("#generateReportFormValues").val("");
$('#reportErrDiv').html('');
var newsreportfileDescription = $.trim($("#newsreportfileDescription1").val());

	if(isValid(newsreportfileDescription)){
		$('#reportErrDiv').html('<b style="color:red">Report Description should not contain #,$,%,& Special charactors</b>');
		return false;
	}
newsReportFileIdsArray = new Array();
var ids = $.trim($('#fileIdsAll').val());
if(!(ids.length == 0)){
   var strArr = ids.split(",");
   for(var i in strArr){
      if($.trim(strArr[i]).length > 0){
		  if(isNaN($.trim(strArr[i]))){
			 str +='Please Enter Valid News Ids<br/>';
			  flag =true;
			  break;
		  }else{
			 newsReportFileIdsArray.push($.trim(strArr[i]));
		  }
	  
	  }
   }
}
	

	if(!(str.contains("Please Enter Valid News Ids"))){
		if(newsReportFileIdsArray.length == 0)
		{
		  str +='Please Enter Atleast One News Id<br/>';
			flag =true;
		}
	}
	
	 if(newsreportfileDescription.length == 0)
	{
		str +='Report Description is required<br/>';
		flag = true;
	}
	str+= '</font>';
	
	 if(flag == true)
	{
	     $('#reportErrDiv').html(str);
		$('html, body').animate({
         scrollTop: $("#reportErrDiv").offset().top
     }, 2000);
		
	return;
	}
	else
	{
	$("#savenewsAjaxImg").css({'display': 'inline-block' });
	reportErrDiv.innerHTML = '';
    var jsObj = {
			fileGallaryIds:newsReportFileIdsArray,
			description:newsreportfileDescription,
			task: 'saveNews'
	};
	    $("#generateReportFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
		var uploadHandlernew1 = {
		   success : function( o ) { 
			        var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
	                showReportFileNewsStatus(uploadResult,2);
				}									
	   };	
	  YAHOO.util.Connect.setForm('generateReportForm',false);
	  YAHOO.util.Connect.asyncRequest('POST','saveNewsUserAction.action',uploadHandlernew1);

	}

}

function saveAllNews(){

     $("#generateReportFormValues").val("");
$('#reportErrDiv').html('');
var newsreportfileDescription = $("#newsreportfileDescription").val();

	if(isValid(newsreportfileDescription)){
		$('#reportErrDiv').html('<b style="color:red">Report Description should not contain #,$,%,& Special charactors</b>');
		return false;
	}

	var str = '<font color="red">';
	var flag =false;
	if(newsReportFileIdsArray.length == 0)
	{
	  str +='Select atleast one news<br/>';
		flag =true;
	}
	
	 if(newsreportfileDescription == 0)
	{
		str +='Description is required<br/>';
		flag = true;
	}
	
	newsReporterrorMessageDiv.innerHTML = str;
	 if(flag == true)
	{
		$('html, body').animate({
         scrollTop: $("#newsReporterrorMessageDiv").offset().top
     }, 2000);
		
	return;
	}
	else
	{
	$("#savenewsAjaxImg").css({'display': 'inline-block' });
	newsReporterrorMessageDiv.innerHTML = '';
    var jsObj = {
			fileGallaryIds:newsReportFileIdsArray,
			description:newsreportfileDescription,
			task: 'saveNews'
	};
	    $("#generateReportFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
		var uploadHandlernew1 = {
		   success : function( o ) { 
			        var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
	                showReportFileNewsStatus(uploadResult);
				}									
	   };	
	  YAHOO.util.Connect.setForm('generateReportForm',false);
	  YAHOO.util.Connect.asyncRequest('POST','saveNewsUserAction.action',uploadHandlernew1);

	}

}
function showReportFileNewsStatus(result,id)
{ 
  var newsIds = false;
  if(id == 2){
    newsIds = true;
  }
	$("#savenewsAjaxImg").css("display","none");
	if(result.resultCode == 0)
	{
	  if(!newsIds){
	   $("#newsreportfileDescription").val('');
	   $("#newsReporterrorMessageDiv").html('Report Generated Successfully..').css('color','green');
	   $("#newsReportUnSelectAllCheckBox").trigger("click");
	  }else{
	   $("#fileIdsAll").val('');
	   $("#newsreportfileDescription1").val('');
	   $("#reportErrDiv").html('Report Generated Successfully..').css('color','green');
	   }
	}
	else
	{
	if(!newsIds){
      $("#newsReporterrorMessageDiv").html('Error Occured! Try Again..').css('color','red');
	}else{
	    $("#reportErrDiv").html('Error Occured! Try Again..').css('color','red');
	}
   }
   if(!newsIds){
    $('html, body').animate({
         scrollTop: $("#newsReporterrorMessageDiv").offset().top
     }, 2000);
  }else{
     $('html, body').animate({
         scrollTop: $("#reportErrDiv").offset().top
     }, 2000);
  }
		 return;
}


$(document).ready(function(){
	$(function(){
    $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


  });
});

function getKeyWords()
{
	 var jsObj={
		
		task:'getKeywords'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeywordsAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
}
function getMappedKeyWords()
{
	 var jsObj={
		
		task:'getGallaryMapedKeywords'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeywordsAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
}

function getGallariesForSelectedCategory1(selEle)
{
 if(selEle == "keywordCategory")
  $("#categorygallary").css("display","block");
  if(selEle == "keywordCategory1")
  $("#categorygallary1").css("display","block");;
  var categoryId = $('#'+selEle+'').val();
 var jsObj=
	{
	    categoryId:categoryId,
		selEle : selEle,
		task  : "getGallariesInCategory1"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGallariesByCategoryIdAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function updateGallaryKeyword()
{
var keywordsArr =[]; 
var gallariesArr = [];
$(".keywords").each(function() {
		 if($(this).is(":checked")){
		
		keywordsArr.push($(this).val());
	   }
    });
	$(".gallary").each(function() {
		 if($(this).is(":checked")){
		gallariesArr.push($(this).val());
	   }
    });
	var keywordCategory = $("#keywordCategory").val();
	 var flag =false;
	 var str='<font color="red">';
	var keywordErrorMsgDivId = document.getElementById('keywordErrorMsgDivId');
	
	if(keywordCategory == 0)
	{
	str +='Select Category<br/>';
		flag =true;
	}
	else if(gallariesArr == "")
	{
		str +='Select atleast one Gallary<br/>';
		flag =true;
	}
	else if(keywordsArr == "")
	{
		str +='Select atleast one keyWord<br/>';
		flag =true;
	}
	if(flag == true)
	{
	keywordErrorMsgDivId.innerHTML = str;
	return;
	}
	else
	keywordErrorMsgDivId.innerHTML = '';
	$("#keywordAjaxImg").css("display","inline-block");
		var jsObj = {
			gallariesArr:gallariesArr,
			keywordsArr:keywordsArr,
			task: 'updateGallaryKeyword',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "updateGallaryKeywordAction.action?"+rparam;
	callnewAjax(jsObj,url);

}

function uploadNewsForPartyAndCandidate(fileId)
{

 if(fileId != null)
 {
  $("#addResponseToNewsLiId").removeClass("active");
  $("#newsGallaryLiId").addClass("active");
 }

  var tempPartyId = 872;
   var str ='';
     
        str += '<form name="uploadForm1" action="uploadFilesForPartyAndCandidatesKeywords.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
		str+='<div class="container">';
         str += '<table class="aligncenter"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';
		
		str+='<div class="span12"> ';
		if(fileId == null || fileId == '')
		 str +='<legend class="boxHeading text-center">Upload A News   </legend> ';
		else
		 str +='<legend class="boxHeading text-center">Adding Response To Selected News</legend> ';

			str+='<div class="well" style="border:1px solid #ADC248;width: 905px; margin-left: -10px;padding:5px 15px 15px 15px;" >';
	str+='	<legend class="">&nbsp; Basic Information of News  </legend>';
	str+='	<div class="row-fluid">       ';
	str+='<div class="span6">';
				str+='<label><strong>News Title<span class="requiredFont">*</span></strong></label>  ';
				str+='<input type="text"  maxlength="800" size="25" name="fileTitle" placeholder="Enter Title Of the News" id="newsfileTitle" class="input-block-level">       ';
				str+='<span class="help-block"> <input type="checkbox" id="sourceTelugu" onclick="changeLanguage();" name="titleCheckBox">&nbsp;Please check if title is from eenadu.net</span>       ';
			str+='</div>       ';
			str+='<div class="span3 ">         ';
				str+='<label><strong>News Date<span class="requiredFont">*</span><strong></strong></strong></label>  ';
				str+='<input type="text" size="20" name="fileDate" readonly="true" class="dateField" placeholder="Select News Date" id="newsdatedatepic" style="cursor:text;">         ';
				str+='<span class="help-block"> Select Date of News</span> ';
			str+='</div>       ';
			str+='<div class="span3 ">         ';
				str+='<label><strong>News Importance<span class="requiredFont">*</span><strong></strong></strong></label>   ';      
				str+='<select name="newsimportance" id="newsimportance"><option value="3">High</option><option value="1">Low</option><option value="2">Medium</option></select>';
				str+='<span class="help-block"> &nbsp;Please select News Importance</span>       ';
			str+='</div>    ';
		str+='</div> ';

		str+='<div class="span3" style="margin-left: 12px;"><label class="radio"><input type="radio" value="public" name="visibility" id="newsPublicRadioId" checked="true"><b><font id="newsfontDiv">Mark This News As Public</font></b></label></div>';
		str+='<div class="span3"><label class="radio"><input type="radio" id="newsprivateRadioId" name="visibility" value="private"><b><font>Mark This News As Private</font></b></label></div>';

		str+='<div class="row-fluid">       <div class="span8 ">         <label><strong>Short Description<span class="requiredFont">*</span><strong></strong></strong></label>         <textarea id="newsfileDescription" class="input-block-level" cols="20" rows="2" name="fileDescription" maxlength="1800"></textarea><span class="help-block"> <input type="checkbox" id="sourceDescTelugu" onclick="changeLanguage();" name="descCheckBox">&nbsp;Please check if description is from eenadu.net</span>       </div>';
		str+='<div class="span4 ">      ';
		str+='<label><strong>Image To Display</strong></label>';
		 str+='<input type="file" class="m_top10" name="imageForDisplay" id="mainImgId" style="width: 225px;"/><span class="icon-remove" style="cursor: pointer; margin-left: 15px; margin-top: 8px;" title="Click Here To remove Image" onclick="deleteExistingImg(\'mainImgId\');"></span><div>( .jpeg or .jpg or .png or .gif formats only)</div></div>    </div>';
		str+='<div class="row-fluid" style="display:none">       <div class="span8 ">         <label><strong>News Synopsys<span class="requiredFont">*</span><strong></strong></strong></label>         <textarea id="newsSynopsysDesc" class="input-block-level" cols="20" rows="2" name="newsSynopsysDesc" maxlength="1800">s</textarea><span class="help-block"> <input type="checkbox" id="newsSynopsysDescTelugu" onclick="changeLanguage();" name="synopsysCheckBox">&nbsp;Please check if news synopsys is from eenadu.net</span>       </div>   </div>';
		
	str+='</div></div></div>';
	
		str+='<div class="container">';

		str+='<div class="row-fluid"> ';

		str+='    <div class="sapn12"> <div id="myID" style="border:1px solid #ADC248;position: inherit; border-radius:5px; padding:5px 15px 15px 15px;width:906px;"> ';  
		str+='         <legend>From - Who</legend>';
		str+='    <div id="whoTalkedMainDIV"><div style="margin-left: 0px;" class="row alert alert-warning">';
		str+='    <div class="span5 well well-small ">';
		str+='<label><strong>Select Party</strong></label><span id="errDiv11" style="margin-top: -25px; color: red; margin-bottom: 9px;" ></span><select class="input-block-level" id="partiesList" name="candidatePartyNewsVOList.sourceVOList[0].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForParty\',11)">';
		//str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872" >TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" style="display:none;" id="candidateListForPartyImg" />';
		str+='</div>';

		str+='    <div class="span5 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str+='<div id="searchDiv"></div>';
		str +='<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 260px;"> <a onclick="buildSearchDiv(11,0);"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img partylistid="partiesList" key="candidateListForParty" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>';
		str +='<select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList[0].candidateId" id="candidateListForParty">';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
				
		str+='</div>';
		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList[0].benefitId">';
        str +='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
        str +='   </select>';
		
		str+='    </div>';
				   
		str+='</div></div>  ';

		str+='<div class=" well well-small" style="margin-bottom: 0px;"> <a class="btn btn-danger" onclick="addNewFrom();" href="javascript:void(0);">Click to add another From - Who</a></div><legend>To - Whom</legend>';
		str+='  ';
		str+='   <div id="whomeTalkedMainDIV"> <div class="row alert alert-warning" style="margin-left: 0px;">';
		str+='    <div class="span2 well well-small ">';
		str+='<label><strong>Select Party</strong></label> <span id="errDiv22" style="color: red; margin-bottom: 5px; margin-left: -5px; float: left; position: absolute; margin-top: 30px;"></span><select class="input-block-level" id="partiesListForWhome" name="candidatePartyNewsVOList.destinationVOList[0].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo\',22)">';
		//str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872">TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" style="display:none;" id="candidateListForPartyForNewsToImg" />';
		str+='</div>';

		str+='    <div class="span4 well well-small">';
		str+='<label><strong>Select Candidate</strong></label> ';
		str +='<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 190px;"> <a onclick="buildSearchDiv(22,0);"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForPartyForNewsTo" partyListId="partiesListForWhome" ></span>';
		str +='<select id="candidateListForPartyForNewsTo" name="candidatePartyNewsVOList.destinationVOList[0].candidateId" class="input-block-level">';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		
		str+='</div>';
		str+='<div class="span4 well well-small">';
		str+='<label><strong>Select Categories</strong></label>';
		str+='<span style="float:left;margin-left:135px;margin-top:-25px"><a title="refresh list" onclick="refreshCategories(\'whomegallaryId\');" href="javascript:{}"><i class="icon-refresh"></i></a></span>';
		str+='<select key="keywordIdHiddenCat0" style="width: 252px;" id="whomegallaryId" >';
		str+='    <option>Select Category</option>';
		str+='</select>';
		
		str+='    </span>';
		str+='</div>';

		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select name="candidatePartyNewsVOList.destinationVOList[0].benefitId" class="input-block-level">';
        str+='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
		str+='</select>';

		str+='    </div>';
		str+='<div class="span12 well well-small" style="margin-left: 0px;">';
		str+='<label><strong>Enter Keywords</strong></label><input type="text" class="input-block-level keyword0 destinationKeywords" key="keywordId0" id="keywordId">';
		str+='<span class="help-block">Enter multiple keywords with comma separator Ex : padayatra,scam.';
		str+='<span class="help-block">If it is an activity done by cadre please select "Cadre" keyword.';
		str+='<span class="help-block">If it is an activity done by mla incharge or mp incharge please select "MLA/Incharge" , "MP/Incharge" keyword.';
		str+='<span class="help-block">Note:Keyword should not contain #,$,%,& Special characters.';
		str+='    </span>';
		str+='</div>';
		str +='<input type="hidden" id="keywordId0Hidden" name="candidatePartyNewsVOList.destinationVOList[0].keywordsList" />';
        str +='<input type="hidden" id="keywordIdHiddenCat0" name="candidatePartyNewsVOList.destinationVOList[0].categoryIdsStr" />';		   	   
		str+='</div></div>';
		
				   
		str+='<div class=" well well-small">     <a href="javascript:void(0);" onclick="addWhome();" class="btn btn-danger">Click to add another To - Whom</a></div>';

		str+='</div>   </div>';
		str+=' </div>';
		str+='</div>';

		str+='<div class="row-fluid"><div id="formdata" style="display:none;"></div></div><div class="container" style="margin-top: 10px;margin-left:14px;border-radius:5px;padding:5px 15px 15px 15px;width: 906px;border:1px solid #ADC248;" ><legend class="">&nbsp;Add News From Different Sources</legend><div class="row-fluid ">';
		str+='    <div class="span12">';
		str+='        <div class="row-fluid">';
		str+='<div class="container ">';
		str+='        <div class="span4" style="width:auto">';
		str+='        <label><strong>File Path</strong></label>';
			   
		str+='<br/><input type="file" name="fileSourceVOList[0].sourceFileList[0].fileImage" class="btn fileImgCls" key="aaanewsfileDescription" id="aaanewsfile0" style="width: 225px;"/><span><span class="icon-remove" style="cursor: pointer; margin-top: 15px; float: right; position: absolute;" title="Click Here To remove Image" onclick="deleteExistingImg(\'aaanewsfile0\');"></span></span>';
		

		str+='        </div>';

		str+='<div class="span2 " style="margin-left: 17px;">';
		str+='        <label><strong>News<br> Edition</strong><span class="requiredFont">*</span></label>';
		str+='        <select id="newsedition0" name="fileSourceVOList[0].sourceFileList[0].newsEdition"  class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
		str+='        <label><strong>Page Number</strong><span class="requiredFont">*</span></label>';
		str+='        <input type="text" id="pageno0" name="fileSourceVOList[0].sourceFileList[0].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
		str+='        <label><strong>News Length</strong><font class="requiredFont">*</font></label>';
		str+='        <input type="text" id="newslength0" name="fileSourceVOList[0].sourceFileList[0].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>File Source</strong></label>';
		str+='        <select  id="filesourceId0" style="margin-top:25px;" name="fileSourceVOList[0].fileSourceId"  class="input-block-level m_top20 fileNewSourceCls"><option value="0">Select Source</option></select>';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>News Language</strong></label>';
		str+='        <select id="sourceLangId0" style="margin-top:25px;" name="fileSourceVOList[0].sourceLangId" class="input-block-level m_top20"><option value="0">Select Language</option></select></select>';

		str+='        </div>';
		str+='</div>';
		str+='<div id="source0newfile"></div>';
		str+='<div class="container m_top5">';
		str +='<span class="help-block">&nbsp;&nbsp;&nbsp;File Path Or Detailed News Description is Mandatory. File must be .jpeg or .jpg or .png or .gif formats only.</span>';
		str+='<div class="span12 ">         <label><strong>Detailed News Description</strong></label>         <textarea  name="fileSourceVOList[0].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls" id="aaanewsfileDescription" style="width: 900px;"></textarea><span class="help-block">&nbsp;&nbsp;&nbsp;<input style="margin-top: -1px;" name="fileSourceVOList[0].newsDescCheck" onclick="changeToEEnadutxt(\'newsdetdescchk\',\'aaanewsfileDescription\');" id="newsdetdescchk" type="checkbox"/>&nbsp;Please check if title is from eenadu.net</span>       </div>';

		str+='        </div>';
			 
		str+='</div>';
		str+='</div>';
		str+='<div class="">';
		str+='      <a href="javascript:void(0);" onclick="addNewFilePart(0);" class="btn btn-success span5" style="margin-left: 15px;">Click here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source                   </a></div></div>';
        str+='<div id="addNewSourceToExisting"></div>';


		str+='<div class="row-fluid">';
		str+='      <a style="margin-top:-30px" class="offset6 span5 btn btn-danger" onclick="addNewFileSource();" href="javascript:void(0);" style="margin-top: -60px;">Click here to  <span class="label ">Add <i class="icon-plus-sign icon-white"></i></span> another source</a></div></div>';
		str+='<div class="row-fluid"><div class="container m_top10" style="padding:5px 15px 15px 15px;width: 920px;border:1px solid #ADC248;margin-left:14px;border-radius:5px;width:906px;"><legend class="">Select News Location</legend>';
		str+='<div id="newsLocationMainDiv"><div class="span12 newLocationClass" id="newsLocation0">';    
		str+='    <div class="row-fluid"> ';   
		str+='       <div class="span2"> ';   
		str+='         <label>Location Scope</label>';
		str+='         <select class="input-block-level scopeLevel" key="0" id="scopeDiv0" name="locationScope[0]" onchange="removeAndAddSelection(\'scopeDiv0\');getLocations(this.options[this.selectedIndex].value,0)"></select>';
		str+='       </div>';
        str+='       <div id="showScopeSubs0"></div>';
		str+='    </div>';
		str+='</div></div>'; 
	    str+='<div><a style="margin-left: 15px;" class="btn btn-success span5" onclick="addNewLocation();" href="javascript:void(0);">Click here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another location</a></div>';
		str+='</div></div></div><div class="form-actions text-center"><input type="button" id="uploadNewsBtnId" onclick="uploadFile()" value="Submit" class="btn btn-success btn-large">                         </div></div></div>';
       
	   if(fileId != null)
        str +='<input type="hidden" name="responseFileIdsStr" value="'+fileId+'">';  	
	  
	  str+='</form>';
   
  
    str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';  
    

  
	
	document.getElementById("newsGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod("News Gallary","whomegallaryId");

	$("#newsdatedatepic").datepicker({ dateFormat: 'dd/mm/yy',maxDate:new Date() });
    $("#newsdatedatepic").datepicker("setDate", new Date());
	
	 getScopes(0);
	 getSource("filesourceId0");
	getLanguage('sourceLangId0');
	 getNewsImportance();


	  getScopeForUser(0);


	 getBenefitList();
	 getPartiesList("partiesList","partiesListForWhome");
	 

$("#keywordId").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


  
  $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


		$("#KeywordPartiesListForNewsTo").multiselect({
	  noneSelectedText:"Select Party"});
		$("#KeywordPartiesListForNewsTo").multiselect('refresh');
		$("#KeywordPartiesListForNewsTo").multiselect('create');

}
newsLocCloneNo = 1;
function addNewLocation(){
  var str = $("#newsLocation0").html();
  str = '<span id="deleteMultiLoc'+newsLocCloneNo+'">'+str+'</span>'; 
  str = str.replace('<span style="display:none">changeDel</span>','<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+newsLocCloneNo+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>');
  str = str.replace('key="0"','key="'+newsLocCloneNo+'"');
  str = str.replace(/showScopeSubs0/gi,'showScopeSubs'+newsLocCloneNo);
  str = str.replace(/getLocations(this.options[this.selectedIndex].value,0)/gi,'getLocations(this.options[this.selectedIndex].value,'+newsLocCloneNo+')');
  str = str.replace(/,0/gi,','+newsLocCloneNo+'');
  str = str.replace('locationScope[0]','locationScope['+newsLocCloneNo+']');
  str = str.replace('locationValue[0]','locationValue['+newsLocCloneNo+']');
  str = str.replace(/scopeDiv0/gi,'scopeDiv'+newsLocCloneNo);
  str = str.replace(/stateDiv0/gi,'stateDiv'+newsLocCloneNo);
  str = str.replace(/districtDiv0/gi,'districtDiv'+newsLocCloneNo);
  str = str.replace(/constituencyDiv0/gi,'constituencyDiv'+newsLocCloneNo);
  str = str.replace(/mandalDiv0/gi,'mandalDiv'+newsLocCloneNo);
  str = str.replace('mandalId[0]','mandalId['+newsLocCloneNo+']');
  str = str.replace(/villageDiv0/gi,'villageDiv'+newsLocCloneNo);
  $("#newsLocationMainDiv").append(str);
  newsLocCloneNo=newsLocCloneNo+1;
}

function removeThisLocation(id){
  if(confirm("Do you want to delete this Location?")){
    $("#"+id).remove();
  }
}

function removeAndAddSelection(id){
  var value = $("#"+id+" option:selected").val();
  $("#"+id).find('option').removeAttr("selected");
   $("#"+id).find('option[value='+value+']').attr('selected','selected');
}
var addSource = 0;
function addNewFileSource(){
        addSource = addSource+1;
        var str ='';
        str+='<div id="newfilesourceremov'+addSource+'" class="row-fluid ">';
		str+='    <div class="span12">';
		str+='        <div class="row-fluid">';
		str+='<div class="container ">';
		str+='        <div class="span4" style="width: 275px;">';
		str+='        <label><strong>File Path</strong></label>';
			   
		str+='<br/><input type="file" name="fileSourceVOList['+addSource+'].sourceFileList[0].fileImage" class="btn fileImgCls" key="'+addSource+'aaanewsfileDescription" id="aaanewsfile'+addSource+'" style="width: 225px;"/> <span><span class="icon-remove" style="cursor: pointer;float: right; position: absolute; margin-top: 15px;" title="Click Here To remove Image" onclick="deleteExistingImg(\'aaanewsfile'+addSource+'\');"></span></span>';
		

		str+='        </div>';

		str+='<div class="span2 "  style="margin-left: -7px;">';
		str+='        <label><strong>News<br> Edition</strong><span class="requiredFont">*</span></label>';
		str+='        <select  name="fileSourceVOList['+addSource+'].sourceFileList[0].newsEdition"  class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
		str+='        <label><strong>Page Number</strong><span class="requiredFont">*</span></label>';
		str+='        <input type="text" name="fileSourceVOList['+addSource+'].sourceFileList[0].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
		str+='        <label><strong>News Length</strong><font class="requiredFont">*</font></label>';
		str+='        <input type="text"  name="fileSourceVOList['+addSource+'].sourceFileList[0].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>File Source</strong></label>';
		str+='        <select id="'+addSource+'addfilenewSource" style="margin-top:25px;" name="fileSourceVOList['+addSource+'].fileSourceId"  class="input-block-level m_top20 fileNewSourceCls"><option value="0">Select Source</option></select>';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>News Language</strong><i class="icon-trash" title="Click here to remove this source" onclick="removeThisFileSource(\'newfilesourceremov'+addSource+'\')" style="margin-left: 20px;"></i></label>';
		str+='        <select  id="'+addSource+'addfilenewLanguage" style="margin-top:25px;"  name="fileSourceVOList['+addSource+'].sourceLangId" class="input-block-level m_top20"><option value="0">Select Language</option></select></select>';

		str+='        </div>';
		str+='</div>';
		str+='<div id="source'+addSource+'newfile"></div>';
		str+='<div class="container m_top5">';
		str +='<span class="help-block">&nbsp;&nbsp;&nbsp;File Path Or Detailed News Description is Mandatory. File must be .jpeg or .jpg or .png or .gif formats only.</span>';
		str+='<div class="span12 ">         <label><strong>Detailed News Description</strong></label>         <textarea id="'+addSource+'aaanewsfileDescription" name="fileSourceVOList['+addSource+'].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls" style="width: 875px;" ></textarea> <span class="help-block">&nbsp;&nbsp;&nbsp;<input id="'+addSource+'newsdetdescchk" onclick="changeToEEnadutxt(\''+addSource+'newsdetdescchk\',\''+addSource+'aaanewsfileDescription\');" style="margin-top:-1px;" name="fileSourceVOList['+addSource+'].newsDescCheck" type="checkbox"/>&nbsp;Please check if detailed news description is from eenadu.net</span>       </div>';

		str+='        </div>';
			 
		str+='</div>';
		str+='</div>';
		str+='<div class="">';
		str+='      <a href="javascript:void(0);" onclick="addNewFilePart('+addSource+');" class="btn btn-success span6">Click here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source                   </a></div></div>';
        $("#addNewSourceToExisting").append(str);
		getSource(addSource+'addfilenewSource');
	getLanguage(addSource+'addfilenewLanguage');
}
function changeToEEnadutxt(ck,id){
   
   if($('#'+ck).is(':checked')){
     $('#'+id).addClass('enadu');
   }else{
		 $('#'+id).removeClass('enadu');
   }
}
var addFile = 0;
function addNewFilePart(id){
     addFile = addFile+1;
        var str ='';
        str+='<div id ="'+addFile+'newpart'+id+'" class="container " >';
		str+='        <div class="span4" style="width: 275px;margin-right: -17px;">';
				
		str+='<input type="file" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].fileImage" class="btn addFileImgCls" style="width: 225px;"/>';

		str+='        </div>';

		str+='<div class="span2 ">';
				
		str+='        <select name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].newsEdition" class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
			  
		str+='        <input type="text" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].pageNo" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
			   
		str+='        <input type="text" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].newsLength"  class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
			   
		str+='        <a href="javascript:void(0);" onclick="removeThisFile(\''+addFile+'newpart'+id+'\')" class="btn btn-block"><i class="icon-trash"></i> Delete This Row</a>';

		str+='        </div>';

		str+='</div>';
		
		$("#source"+id+"newfile").append(str);
}
var who = 0;  
function addNewFrom(){
 who = who+1;
 var str ='';
 str+='    <div id="whocandidate'+who+'" style="margin-left: 0px;" class="row alert alert-warning">';
		str+='    <div class="span5 well well-small ">';
		str+='<label style="float: left;"><strong>Select Party</strong></label><span id="errDiv3'+who+'" style="margin-top: -25px; color: red; margin-left: 125px; margin-bottom: 9px;" ></span><select class="input-block-level" id="partiesList'+who+'" name="candidatePartyNewsVOList.sourceVOList['+who+'].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForParty'+who+'\',3'+who+',0)">';
		
		str +='</select>';
		str +='<img src="images/search.jpg" id="candidateListForParty'+who+'Img" style="display:none;"/>';
		str+='</div>';

		str+='    <div class="span5 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 260px;"> <a onclick="buildSearchDiv(3'+who+','+who+');"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForParty'+who+'" partylistid="partiesList'+who+'"></span>';
		str +='<select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList['+who+'].candidateId" id="candidateListForParty'+who+'" >';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		
		str+='</div>';
		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList['+who+'].benefitId">';
        str +='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
        str +='   </select>';
		
		str+='    </div>';
		str+='<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <a href="javascript:void(0);" onclick="deletethisDiv(\'whocandidate'+who+'\');" class="btn"><i class="icon-trash"></i> Delete This Block</a>    </p></div>	';	   
		str+='</div>  ';
		

$( "#whoTalkedMainDIV").append(str);

getPartiesList("partiesList"+who+"",null);

}
function deletethisDiv(id){
 if(confirm("Do you want to delete this Party/Candidate?")){
    $("#"+id).remove();
  }
}
var whome = 0;
function addWhome(){

 whome = whome+1;
var str ='';

	    str+='    <div id="whomecandidate'+whome+'"><div class="row alert alert-warning" style="margin-left: 0px;">';
		str+='    <div class="span2 well well-small ">';
		str+='<label><strong>Select Party</strong></label><span id="errDiv4'+whome+'" style="float:left;position:absolute;margin-top: 30px; color: red; margin-left: -5px; margin-bottom: 9px;" ></span><select class="input-block-level" id="partiesListForWhome'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo'+whome+'\',4'+whome+',0)">';
		str +='</select>';
		str +='<img src="images/search.jpg" id="candidateListForPartyForNewsTo'+whome+'Img" style="display:none;" />';
		str+='</div>';

		str+='    <div class="span4 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 190px;"> <a onclick="buildSearchDiv(4'+whome+','+whome+');"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img partylistid="partiesListForWhome'+whome+'" key="candidateListForPartyForNewsTo'+whome+'" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>';
		str +='<select id="candidateListForPartyForNewsTo'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].candidateId" class="input-block-level" >';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		str+='</div>';
		str+='<div class="span4 well well-small">';
		str+='<label><strong>Select Categories</strong></label>';
		str+='<span style="float:left;margin-left:135px;margin-top:-25px"><a title="refresh list" onclick="refreshCategories(\''+whome+'whomegallaryId\');" href="javascript:{}"><i class="icon-refresh"></i></a></span>';
		str+='<select key="keywordIdHiddenCat'+whome+'" style="width: 252px;" id="'+whome+'whomegallaryId" >';
		str+='    <option>Select Category</option>';
		str+='</select>';
		
		str+='    </span>';
		str+='</div>';

		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select name="candidatePartyNewsVOList.destinationVOList['+whome+'].benefitId" class="input-block-level">';
        str+='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
		str+='</select>';

		str+='    </div>';
		str+='<div class="span12 well well-small" style="margin-left: 0px;">';
		str+='<label><strong>Enter Keywords</strong></label><input type="text" class="input-block-level keyword'+whome+' destinationKeywords" key="keywordId'+whome+'" id="'+whome+'keywordId">';

		str+='</div>';
		str +='<input type="hidden" id="keywordId'+whome+'Hidden" name="candidatePartyNewsVOList.destinationVOList['+whome+'].keywordsList" />';
        str +='<input type="hidden" id="keywordIdHiddenCat'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].categoryIdsStr" />';		   
		str+='<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <a href="javascript:void(0);" onclick="deletethisDiv(\'whomecandidate'+whome+'\');" class="btn"><i class="icon-trash"></i> Delete This Block</a>    </p></div>	   </div>';


$( "#whomeTalkedMainDIV").append(str);


$("#"+whome+"keywordId").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});
getPartyGallariesForUplaod("News Gallary",whome+"whomegallaryId");
getPartiesList("partiesListForWhome"+whome+"",null);

}
var o = 0;
function addNewFileToUpload(id){
   o = o+1;
   $("#multifile"+id).append('<div id="removeThisFil'+o+'"><table><tr><td><input type="file" name="fileSourceVOList['+id+'].fileImage"  size="25"  /></td><td><a class="btn btn-small btn-block" style="width: 17px;" href="javascript:void(0)" onclick="removeThisFile(\'removeThisFil'+o+'\');"> <i class="icon-minus-sign"></i></a></td></tr></table></div>');

}
function removeThisFile(id){
 if(confirm("Do you want to delete this row?")){
  $("#"+id).remove();
  }
}
var t =0;
function addNewSourceToDiv(){
t=t+1;
var str='';
str += '        <tr id="newFileSourceFile'+t+'">';
str += '            <td><input  type="file" name="fileSourceVOList['+t+'].fileImage"  size="25"  /><div id="multifile'+t+'"></div>';
str += '<a href="javascript:void(0)" onclick="addNewFileToUpload('+t+');" style="width: 100px; margin-left: 51px; margin-top: 11px;" class="btn btn-small btn-block"> <i class="icon-plus-sign"></i></a></td>';
str += '            <td><select id="filesourceId'+t+'" name="fileSourceVOList['+t+'].fileSourceId" class="input-small"><option value="0">Select Source</option></select></td>';
str += '            <td><select id="sourceLangId'+t+'" name="fileSourceVOList['+t+'].sourceLangId" class="input-small"><option value="0">Select Language</option></select></td>';
str += '            <td><select id="newsedition'+t+'" name="fileSourceVOList['+t+'].newsEdition" class="input-small"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
str += '            <td><input type="text" id="pageno'+t+'" name="fileSourceVOList['+t+'].pageNo" class="pageno input-small"  onKeyup="IsNumeric(this.value);"></input></td>';
str += '            <td><input type="text" id="newslength'+t+'" class="newslength input-small" name="fileSourceVOList['+t+'].newsLength" onKeyup="IsNumeric1(this.value);"></input></td>';
str += '            <td><textarea  id="completeDesc'+t+'" name="fileSourceVOList['+t+'].completeDesc" class="completeDescCls'+t+' input-block-level"></textarea></td>';
str += '            <td><a onclick="removeThisFileSource(\'newFileSourceFile'+t+'\');" href="javascript:void(0)" style="width: 17px;" class="btn btn-small btn-block"> <i class="icon-minus-sign"></i></a></td>';
str += '               </tr>';

$('#uploadFilesTable > tbody:last').append(str);
  getSource("filesourceId"+t);
	getLanguage('sourceLangId'+t);
}
function removeThisFileSource(id){
  if(confirm("Do you want to delete this Source?")){
  $("#"+id).remove();
  }
}
function addMoreFilesForPartyCandidate()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'" class="moreFileDivCls" key="'+fileCount+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="fileSourceVOList['+fileCount+'].fileImage" class="newsFileId'+fileCount+'" size="25"  /></td>';
    str +='<td><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreImagesForPartyCandidate(\''+fileCount+'\')" title="Click here to add more images" alt="Click here to add more images"></td>';

	str += ' </tr>';
    
	str +='<tr>';
	str +='<td colspan="2"><div id="moreFilePathsImagesDiv'+fileCount+'"></div></td>';
	str +='</tr>';

	str += ' <tr>';
	str += ' <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+fileCount+'" name="fileSourceVOList['+fileCount+'].fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+fileCount+'" name="fileSourceVOList['+fileCount+'].sourceLangId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+fileCount+'" name="fileSourceVOList['+fileCount+'].newsEdition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+fileCount+'" name="fileSourceVOList['+fileCount+'].pageNo" class="pageno pagenoCls" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+fileCount+'" class="newslength newsLengthCls" name="fileSourceVOList['+fileCount+'].newsLength" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric1(this.value);"></input></td>';

	str += '   </tr>';
    
	str +='<tr>';
	str +='<td>News description in details : </td>';
	str +='<td><textarea rows="3" columns = "20" id="completeDesc'+fileCount+'" name="fileSourceVOList['+fileCount+'].completeDesc" class="completeDescCls'+fileCount+'"></textarea></td>';
	str +='<td><input type="checkbox" id="newsDescCheck'+fileCount+'" name="fileSourceVOList['+fileCount+'].newsDescCheck" value="true" class="newsDescCheckId" key="'+fileCount+'" /><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';
	str +='</tr>';

	str +='</table>';
	moreDivElmt.innerHTML = str;
	document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	getSource("filesourceId"+fileCount+"");
	getLanguage('sourceLangId'+fileCount+'');
	fileCount++;
}

function addMoreImagesForPartyCandidate(count)
{
  
  
  var divElmt = document.createElement("moreFilePathsImagesDiv"+count+"");
  var str = '';
  str +='<table id="moreFilePathsId'+count+''+fileImgCount+'">';
  str += '<tr>';
  str += '<td>File Path : </td>';
  str += '<td><input type="file" class="newsFileId'+count+'" size="50" name="fileSourceVOList['+count+'].fileImage"/></td>';
  str += '<td><img onclick="deleteFileImagePaths(\''+count+''+fileImgCount+'\');" title="Click here to delete file" src="images/minus.png" style="background: #fff; border-radius: 11px; padding: 4px;"></td>';
  str +='<tr>';
  str +='</table>';
  divElmt.innerHTML = str;
  document.getElementById("moreFilePathsImagesDiv"+count+"").appendChild(divElmt);
  
  fileImgCount++;
}

function deleteFileImagePaths(imgId)
{
 
 $('#moreFilePathsId'+imgId+'').html('');
}

function getPartyCandidatesList()
{
	
  if(partyIdsListArray != null && partyIdsListArray.length > 0)
  {
    
	 buildPartyKeywordsDiv();

	 var partyIds = new Array();
	 for(var i=0;i<partyIdsListArray.length;i++)
	  partyIds.push(partyIdsListArray[i].id);

	 var jsObj={
		partyIds:partyIds,
		task:'getCandidatesByPartyIds'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidatesByPartyIdsAction.action?"+rparam;
	callAjax(jsObj, url);
  }
}

function buildCandidateList(result)
{
	
	$("#candidateListForParty").find('option').remove();
 if(result != null && result.length > 0)
 {
    $.each(result,function(index,value){
			$("#candidateListForParty").append($("<option></option>").attr("value",value.id).text(value.name));
	});
	

 }

}

function buildPartyKeywordsDiv()
{
  
  $('#partyKeywordsDiv').html('');
  var divElmt = document.createElement("partyKeywordsDiv");
  var str = '';
  str +='<table>';
  
  
  for(var i=0;i<partyIdsListArray.length;i++)
  {
   str += '<tr>';
   str +='<td>'+partyIdsListArray[i].name+'</td>';
   str += '  <td class="selectWidthPadd"><input type="text" name="keywordList1" id="keywordListId1"/></td>';
   str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteSelectedParty(\'party'+partyIdsListArray[i].id+'\')"></td>';
   str += '</tr>';
   str +='<input type="hidden" value="'+partyIdsListArray[i].id+'" name="partyKeywordsList"/>';
  }
  
  str +='</table>';
  divElmt.innerHTML = str;
  
 

  document.getElementById("partyKeywordsDiv").appendChild(divElmt);
   $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});
}








function buildCandidateKeywordsList()
{
  
  $('#candidateKeywordsDiv').html('');
  var divElmt = document.createElement("candidateKeywordsDiv");
  var str = '';
  str +='<table id="moreFilePathsId'+count+''+fileImgCount+'">';
  
  
  for(var i=0;i<candidateIdsListArray.length;i++)
  {
   str += '<tr>';
   str +='<td>'+candidateIdsListArray[i].name+'</td>';
   str += '  <td class="selectWidthPadd"><input type="text" name="keywordList1" id="keywordListId1"/></td>';
   str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" ></td>';
   str += '</tr>';
   str +='<input type="hidden" value="'+candidateIdsListArray[i].id+'" name="candidateKeywordsList"/>';
  }
  
  str +='</table>';
  divElmt.innerHTML = str;
  
  document.getElementById("candidateKeywordsDiv").appendChild(divElmt);
   $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});
  
}

function buildSourceCandidateKeywordsList(){

 var divElmt = document.createElement("newsFromCanListLabel");
   var str = '';

 if(sourceCandidatesArray != null && sourceCandidatesArray.length > 0)
  for(var i=0;i<sourceCandidatesArray.length;i++)
  {
	if(sourceCandidateIdsArray.indexOf(sourceCandidatesArray[i].id) == -1)
	{
	 str +='<label class="btn" id="sourceCan'+sourceCandidatesArray[i].id+'" class="sourceCandidateImgCls">';
	 str +=''+sourceCandidatesArray[i].name+'';
	 str +='<img src="images/closeImg.png" key="'+sourceCandidatesArray[i].id+'" class="candidateCloseImg"/>';
	 str +='</label>';
	 sourceCandidateIdsArray.push(sourceCandidatesArray[i].id);
	}
  }
  divElmt.innerHTML = str;
 document.getElementById("newsFromCanListLabel").appendChild(divElmt);

}

function buildDestinationCandidateKeywordsList(){

 var divElmt = document.createElement("newsToCanListLabel");
   var str = '';
  if(destinationCandidatesArray != null && destinationCandidatesArray.length > 0)
  for(var i=0;i<destinationCandidatesArray.length;i++)
  {
	if(destinationCandidateIdsArray.indexOf(destinationCandidatesArray[i].id) == -1)
	{
	 str +='<label class="btn" id="destinationCan'+destinationCandidatesArray[i].id+'" class="destinationCandidateImgCls">';
	 str +=''+destinationCandidatesArray[i].name+'';
	 str +='<img src="images/closeImg.png" key="'+destinationCandidatesArray[i].id+'" class="destinationCandidateCloseImg" id="descCan'+destinationCandidatesArray[i].id+'"/>';
	 str +='</label>';
	 destinationCandidateIdsArray.push(destinationCandidatesArray[i].id);
	}
  }
  divElmt.innerHTML = str;
 document.getElementById("newsToCanListLabel").appendChild(divElmt);

}



function deleteSelectedParty(id)
{
	

 $("#"+id+"").closest('table').html('');
}

function getCandidatesListByPartyId(partyId,type,divId1,val)
{
	var divSourceId;
	var divId = divId1; 
	if(divId1 != 11 && divId1 != 22){
		divSourceId = (divId1 + "").charAt(0);
		divId = (divId1 + "").charAt(1);
	}
	var candiId = $("#candidateId"+divId+"").val();

	var id = candidates[candiId];

	if(divId == 11){
	$("#errDiv11").html('');
	}
	else if(divId == 22){
		$("#errDiv22").html('');
	}

	else if(divSourceId == 3){
	$("#errDiv3"+divId+"").html('');
	}

	else if(divSourceId == 4){
		$("#errDiv4"+divId+"").html('');
	}

    $("#"+type+"Img").css("display","block");
		var jsObj = {
			partyId :partyId,
			type:type,
			val:val,
			task : "getCandidatesListByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
   callAjax(jsObj,url);
}

function getEditCandidatesListByPartyId(partyId,type)
{

	var candiId = $("#candidateId").val();
	var id = candidates[candiId];

    $("#"+type+"Img").css("display","block");
		var jsObj = {
			partyId :partyId,
			type:type,
			task : "getEditCandidatesListByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
   callAjax(jsObj,url);
}
function getTextCandidateId()
{
     $("#currentpartySelectNewList").val($("#EditCandidateListForParty").val());
	var name=$("#EditCandidateListForParty option:selected").text().replace(/\(.*?\)/g, '');
	$("#textCandidate").attr("value",name);
	
	 var candidateId = $('#EditCandidateListForParty option:selected').val();
	var jsObj = {
			candidateId :candidateId,
			task : "getDesignationAndLocationByCandidateId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDesignationsAndLocationOfAParty.action?"+rparam;					
	
   callAjax(jsObj,url);
 
}	

function setSourceDestinationPartyCandidateIds()
{

 if(sourcePartyArray != null && sourcePartyArray.length > 0)
 {
   var str = '';
   for(var i in sourcePartyArray)
    str +=sourcePartyArray[i]+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#sourcePartyListId").val(str);
 }

 if(destinationPartyArray != null && destinationPartyArray.length > 0)
 {
   var str = '';
   for(var i in destinationPartyArray)
    str +=destinationPartyArray[i]+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#destinationPartyListId").val(str);
 }

 if(sourceCandidatesArray != null && sourceCandidatesArray.length > 0)
 {
   var str = '';
   for(var i in sourceCandidatesArray)
    str +=sourceCandidatesArray[i].id+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#sourceCandidatesListId").val(str);
 }

 if(destinationCandidatesArray != null && destinationCandidatesArray.length > 0)
 {
   var str = '';
   for(var i in destinationCandidatesArray)
    str +=destinationCandidatesArray[i].id+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#destinationCandidateListId").val(str);
 }

}

$(document).ready(function(){
	
 $(".newsDescCheckId").live("click",function(){
	 
	var key = $(this).attr("key");	
	
	if($(this).is(':checked'))
	 $("#completeDesc"+key+"").addClass('enadu');
	else
     $("#completeDesc"+key+"").removeClass('enadu');
 
 });


//news From
$("#newsFromCanCheckboxId").live("click",function(){

  if($(this).is(':checked'))
	{
     $("#newsFromCandidateDiv").css("display","block");
	  $("#newsFromPartyDiv").css("display","none");
	 $("#newsFromPartyCheckboxId").attr("checked",false);
	 $("#newsFromBothCheckboxId").attr("checked",false);
	}
  else
	$("#newsFromCandidateDiv").css("display","none");
});

$("#newsFromPartyCheckboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	 $("#newsFromPartyDiv").css("display","block");
     $("#newsFromCandidateDiv").css("display","none");
	 $("#newsFromCanCheckboxId").attr("checked",false);
	 $("#newsFromBothCheckboxId").attr("checked",false);

	}
  else
	$("#newsFromPartyDiv").css("display","none");

});

$("#newsFromBothCheckboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	
	 $("#newsFromCandidateDiv").css("display","block");
	 $("#newsFromPartyDiv").css("display","block");

	 $("#newsFromCanCheckboxId").attr("checked",false);
	 $("#newsFromPartyCheckboxId").attr("checked",false);
	}
  else{
	 $("#newsFromCandidateDiv").css("display","none");
	$("#newsFromPartyDiv").css("display","none");
  }

});


//news to
$("#newsToCanCheckboxId").live("click",function(){
  if($(this).is(':checked'))
	{
	 $("#newsToCandidateDiv").css("display","block");
	 $("#newsToPartyDiv").css("display","none");
     $("#newsToPartyCheckboxId").attr("checked",false);
     $("#newsToBothChechboxId").attr("checked",false);

	}
  else
	$("#newsToCandidateDiv").css("display","none");
});

$("#newsToPartyCheckboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	 $("#newsToPartyDiv").css("display","block");
	 $("#newsToCandidateDiv").css("display","none");
     $("#newsToCanCheckboxId").attr("checked",false);
     $("#newsToBothChechboxId").attr("checked",false);
	}
  else
	$("#newsToPartyDiv").css("display","none");

});

$("#newsToBothChechboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	
	 $("#newsToCandidateDiv").css("display","block");
	 $("#newsToPartyDiv").css("display","block");
	 $("#newsToCanCheckboxId").attr("checked",false);
     $("#newsToPartyCheckboxId").attr("checked",false);
	}
  else{
	 $("#newsToCandidateDiv").css("display","none");
	$("#newsToPartyDiv").css("display","none");
  }

});

//candidate creation
$(".createNewCandidate").live("click",function(){
$('#errorMsgDiv').html('');
$('#newCandidateName').val('');
$('#partySelectNewList').val(0);
$('#designationsList').val(0);
$('#locationId').val(0);
	    createCandPartyKey ="";
	    createCandCandKey ="";
		$("#createCandidateDiv").dialog({
            modal: true,
            title: "<b>Create New Candidate</b>",
			width: 500,
            height: 350
           
        });
  
    $("#createCandidateInnerDiv").html('');
	
	
	createCandPartyKey =$(this).attr("partyListId");
    createCandCandKey =$(this).attr("key");
   
   getPartiesList("partySelectNewList",null);
   getDesignationList("designationsList");
   
});


//candidate creation

$("#createCandidateId").live("click",function(){
    
	$("#errorMsgDiv").html('');
	var partyId = $("#partySelectNewList").val();
	var candidateName = $.trim($("#newCandidateName").val());
	var designationId = $("#designationsList").val();
	
	if(isValid(candidateName)){
		$('#errorMsgDiv').html('<b style="color:red;margin-left:-125px;">Candidate Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
    if(partyId == 0)
	{
	  $("#errorMsgDiv").html("Please Select Party");
	  return;
	}
	if(candidateName.length == 0)
	{
	 $("#errorMsgDiv").html("Please Select Candidate");
	  return;
	}
	if(designationId == 0)
	{
	 $("#errorMsgDiv").html("Please Select Designation");
	  return;
	}
	if($('#locationId option:selected').val() == 0){
	  $("#errorMsgDiv").html("Please Select Location");
	  return;
	}
  var candidateListId = createCandCandKey;
  var partyListId = createCandPartyKey;
  var locationValue = "";
	if($('#locationId option:selected').val() == 1)
	{
		locationValue = $('#assembSelReportId option:selected').val();
	}
	else if($('#locationId option:selected').val() == 2)
	{
		locationValue = $('#parliamSelReportId option:selected').val();
	}
	else if($('#locationId option:selected').val() == 3)
	{
		locationValue = $('#locationId option:selected').val();
	}
	 
	var jsObj =
		{ 
            partyId : partyId,
			candidateName:candidateName,
			candidateListId:candidateListId,
			partyListId:partyListId,
			designationId:designationId,
			locationId : $('#locationId option:selected').val(),
			locationValue : locationValue,
			task:"saveCandidate"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveCandidateAndPartyAction.action?"+rparam;						
	callAjax(jsObj,url);
		
});
$("#createCandidateId1").live("click",function(){
   
    
	$("#errorMsgDiv1").html('');
	var partyId = $("#partySelectNewList1").val();
	var candidateName = $.trim($("#newCandidateName1").val());
	var designationId = $("#designationsList1").val();
	
    if(partyId == 0)
	{
	  $("#errorMsgDiv1").html("Please Select Party");
	  return;
	}
	if(candidateName.length == 0)
	{
	 $("#errorMsgDiv1").html("Please Select Candidate");
	  return;
	}
	if(candidateName.trim().length < 5 ){
		$('#errorMsgDiv1').html('<b style="color:red"> Candidate Name should contains minimum 5 Charactors.</b>');
		return false;		
	}
	
	if(designationId == 0)
	{
	 $("#errorMsgDiv1").html("Please Select Designation");
	  return;
	}
	if($('#locationId1 option:selected').val() == 0){
	  $("#errorMsgDiv1").html("Please Select Location");
	  return;
	}
  var candidateListId = $(this).attr("key");
  var partyListId = $(this).attr("partyListId");
  var locationValue = "";
	if($('#locationId1 option:selected').val() == 1)
	{
		locationValue = $('#assembSelReportIdForCreate option:selected').val();
	}
	else if($('#locationId1 option:selected').val() == 2)
	{
		locationValue = $('#parliamSelReportIdForCreate option:selected').val();
	}
	
	else if($('#locationId1 option:selected').val() == 3)
	{
		locationValue = $('#locationId1 option:selected').val();
	}
	
	var jsObj =
		{ 
            partyId : partyId,
			candidateName:candidateName,
			candidateListId:candidateListId,
			partyListId:partyListId,
			designationId:designationId,
			locationId : $('#locationId1 option:selected').val(),
			locationValue : locationValue,
			task:"saveCandidate1"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveCandidateAndPartyAction.action?"+rparam;						
	callAjax(jsObj,url);
		
});

$(".candidateCloseImg").live("click",function(){
 var key = $(this).attr("key");
 
 $("#sourceCan"+key+"").removeClass("btn");
 $("#sourceCan"+key+"").html('');
 
 if(sourceCandidateIdsArray.indexOf(key) != -1)
 {
   sourceCandidateIdsArray = jQuery.removeFromArray(key, sourceCandidateIdsArray);
   sourceCandidatesArray.splice( $.inArray(key, sourceCandidatesArray), 1 );
 }
 

});

$(".destinationCandidateCloseImg").live("click",function(){
 var key = $(this).attr("key");

 $("#destinationCan"+key+"").removeClass("btn");
 $("#destinationCan"+key+"").html('');

 if(destinationCandidateIdsArray.indexOf(key) != -1)
 {
   destinationCandidateIdsArray = jQuery.removeFromArray(key, destinationCandidateIdsArray);
   destinationCandidatesArray.splice( $.inArray(key, destinationCandidatesArray), 1 );
 }

});


jQuery.removeFromArray = function(value, arr) {
return jQuery.grep(arr, function(elem, index) {
return elem !== value;
});
};


$(".newsResponseCheckId").live("click",function(){
 var fileId = $(this).attr('value');
  if($(this).is(':checked') && responseFileIdsArray.indexOf(fileId) == -1)
   responseFileIdsArray.push(fileId);
  else{
   if(responseFileIdsArray.indexOf(fileId) != -1)
    responseFileIdsArray = jQuery.removeFromArray(fileId, responseFileIdsArray);
  }
   var length = responseFileIdsArray.length;
 $("#selectedNewsCount").html(''+length+''); 
});



$("#newsReportSelectAllCheckBox").live("click",function(){
  
    $(".createReporcCheckBoxCls").attr("checked","checked");
       $(".createReporcCheckBoxCls").each(function(){
            var fileId = $(this).attr("value"); 
            if($(this).is(":checked"))
			{
			  if(newsReportFileIdsArray.indexOf(""+fileId+"") == -1)
              newsReportFileIdsArray.push(fileId);
            }
            else
            {
			  if(newsReportFileIdsArray.indexOf(""+fileId+"") != -1)
			  newsReportFileIdsArray = jQuery.removeFromArray(fileId, newsReportFileIdsArray);
			}
 
          var length = newsReportFileIdsArray.length;
          $("#reportGenaratorSpanCLS").html(''+length+''); 
      });
  
});

$("#newsReportUnSelectAllCheckBox").live("click",function(){
  
   
      $(".createReporcCheckBoxCls").attr("checked",false);
	  newsReportFileIdsArray = new Array();
	  $("#reportGenaratorSpanCLS").html('0'); 
    
   
 
});


$(".createReporcCheckBoxCls").live("click",function(){
 var fileId = $(this).attr("value"); 
 if($(this).is(":checked"))
 {
   if(newsReportFileIdsArray.indexOf(""+fileId+"") == -1)
    newsReportFileIdsArray.push(fileId);
 }
 else
 {
  if(newsReportFileIdsArray.indexOf(""+fileId+"") != -1)
   newsReportFileIdsArray = jQuery.removeFromArray(fileId, newsReportFileIdsArray);
  }
 
 var length = newsReportFileIdsArray.length;
 $("#reportGenaratorSpanCLS").html(''+length+''); 
});

	
});//End ready
function testValues(){
  
   $('.destinationKeywords').each(function() {
   var str = '';
			var $ul = $(this).closest('ul');
			  $ul.find('li').each(function(){
			  str +=''+$(this).text()+',';
			  //alert($(this).text());
			});	
           
        });
}
function getReportFiles(id){
	  var win=window.open('createReportAction.action?reportId='+id, '_blank');
	   win.focus();
	}
	
function generateKey(reportId,id){
   var jsObj =
		{ 
            reportId : reportId,
			id:id,
			task:"generateKeyForReport"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "generateReportKeyAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showHideLocationLvl1(key)
{
   if(key == 'keyword'){
	    if($("#byKeyword").attr("checked") == "checked"){ 
	       $("#byCategory").attr("checked", false);
	       $(".categoryClass").hide();
	       $(".keywordClass").show();
	       getTotalKeyWords("reportKeywordId");
	    }
	    else
	       $(".keywordClass").hide();
	}
	else
	{
	   if($("#byCategory").attr("checked") == "checked")
		{
           getPartyGallariesForUplaod("News Gallary","reportGallaryId");
           $("#byKeyword").attr("checked", false);
           $(".categoryClass").show();
           $(".keywordClass").hide();
		}
		else
            $(".categoryClass").hide(); 
	}
	
}
function showHideLocationLvl(key){
if(key == 'level'){
   $(".regionLvlClass").show();
   $('.regionClass').each(function() {
          $(this).hide();
   });
}else if(key == 'location'){
   $(".regionLvlClass").hide();
	 $(".regSelReport").show();
   showCorrespondingLocations();
}
}

function showCorrespondingLocations(){
 var loc = $("#reportRegionLevel").val();
 if(loc == 1){
  showHideLocations(false,false,false);
 }else if(loc == 2){
  showHideLocations(true,false,false);
 }else if(loc == 3){
  showHideLocations(false,true,false);
 }else if(loc == 4){
  showHideLocations(false,false,true);
 }
}

function showHideLocations(dist,pc,ac){
 if(dist){
  $(".districtSelReport").show();
 }else{
  $(".districtSelReport").hide();
 }
 if(pc){
  $(".parliamSelReport").show();
 }else{
  $(".parliamSelReport").hide();
 }
 if(ac){
  $(".assembSelReport").show();
 }else{
  $(".assembSelReport").hide();
 }
}
function showCorrespondingLocations1(){
 var loc = $("#responseRegionLevel").val();
 if(loc == 1){
  showHideLocationsForResponse(false,false,false);
 }else if(loc == 2){
  showHideLocationsForResponse(true,false,false);
 }else if(loc == 3){
  showHideLocationsForResponse(false,true,false);
 }else if(loc == 4){
  showHideLocationsForResponse(false,false,true);
 }
}

function showHideLocationsForResponse(dist,pc,ac){
 if(dist){
  $(".districtSelReport1").show();
 }else{
  $(".districtSelReport1").hide();
 }
 if(pc){
  $(".parliamSelReport1").show();
 }else{
  $(".parliamSelReport1").hide();
 }
 if(ac){
  $(".assembSelReport1").show();
 }else{
  $(".assembSelReport1").hide();
 }
}

function getDesignationList(designationList)
{
  var jsObj={
		designationList:designationList,
		task:'getDesignationsList'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getDesignationsListAction.action?"+rparam;
	callAjax(jsObj, url);
}
function getDesignationList(designationList1)
{
  var jsObj={
		designationList:designationList1,
		task:'getDesignationsList'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getDesignationsListAction.action?"+rparam;
	callAjax(jsObj, url);
}

function populateDate()
{
$(".dateField").datepicker({
		 dateFormat: "dd/mm/yy",
		 changeMonth: true,
         changeYear: true,
		 maxDate: new Date()	
	});
	$('.dateField').datepicker('setDate', new Date());
}
function getTotalKeyWords()
{

	var jsObj =
		{ 
		    type :"reportKeywordId",
        	task : "getTotalKeyWords"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getTotalKeyWordsAction.action?"+rparam;
	callAjax(jsObj,url);
}
function getKeywordsByCount()
{
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDivStatus").css("display","none");
  $("#newKeywordDiv").css("display","none");
  $("#mergeKeywordDiv").css("display","none");
  $("#showKeywordsDiv").css("display","block");
  $("#newDesignationDiv").css("display","none");
  $("#newEditCandidateDiv").css("display","none");
  $("#newCandidateCreationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");


   var jsObj=
	{
		task  : "getAllKeywordsByCount"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getKeywordsByCountAction.action?"+rparam;						
	callAjax1(jsObj,url);
} 

function replaceSpeclChars(value){
	return value.replace(/[#$%&]/g,'');
}

function isValid(str){
 var iChars = "#$%&";
 var flag = false;
	for (var i = 0; i < str.length; i++) {
		if (iChars.indexOf(str.charAt(i)) != -1) {			
			flag = true;
		}
    }
	return flag;
}
function getStatesForSpecialPage(index)
{
  var jsObj =
		{ 
            time : timeST,
			task:"getStates",
			divId:"stateDiv"+index,
			index:index
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function openMergeKeywordWindow(){
	window.open("keyWordsMergeAction.action");
}
</script>
</body>
</html>