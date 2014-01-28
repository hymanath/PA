<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Debate Information - TDP Portal </title>

 <link href="Green/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="Green/js/bootstrap.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<script src="js/jquery-ui-themes-1.10.3.js"></script>
<script src="js/jquery-ui-timepicker-addon.js"></script>
<script src="js/jquery-ui-sliderAccess.js"></script>

<script src="http://trentrichardson.com/examples/timepicker/jquery-ui-sliderAccess.js">
</script>
<link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script src="js/debate.js"></script>




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

#profileManagementMainOuterDiv4 table,#reportsDiv table,#locationWiseNewsDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#profileManagementMainOuterDiv4 table tr:nth-child(even),#reportsDiv table tr:nth-child(even),#locationWiseNewsDiv table tr:nth-child(even){background:#EdF5FF;}
#profileManagementMainOuterDiv4 table td,#reportsDiv table td,#locationWiseNewsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#profileManagementMainOuterDiv4 table th,#reportsDiv table th,#locationWiseNewsDiv table th{
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
.scrollit {
overflow:scroll;
}
textarea{
resize:none;
}
#debatErrDiv1,#debatErrDiv2,#debatErrDiv3{
color:red;
font-weight:bold;
}
select {
width:220px;
}
.errDiv{
color:red;
font-weight:bold;
}
.succDiv{
color:green;
font-weight:bold;
}


/* css for timepicker */
 .ui-timepicker-div .ui-widget-header {
    margin-bottom: 8px;
}
.ui-timepicker-div dl {
    text-align: left;
}
.ui-timepicker-div dl dt {
    float: left;
    clear:left;
    padding: 0 0 0 5px;
}
.ui-timepicker-div dl dd {
    margin: 0 10px 10px 45%;
}
.ui-timepicker-div td {
    font-size: 90%;
}
.ui-tpicker-grid-label {
    background: none;
    border: none;
    margin: 0;
    padding: 0;
}
.ui-timepicker-rtl {
    direction: rtl;
}
.ui-timepicker-rtl dl {
    text-align: right;
    padding: 0 5px 0 0;
}
.ui-timepicker-rtl dl dt {
    float: right;
    clear: right;
}
.ui-timepicker-rtl dl dd {
    margin: 0 45% 10px 10px;
}		

select {
    font-size: 17px;
    font-weight: bold;
    width: 220px;
}
.ui-multiselect {
    height: 33px !important;
    padding: 2px 0 2px 4px;
    text-align: left;
    width: 221px !important;
}
</style>
</head>
<script>
  


$(function () {
    
	$('#startTime').datetimepicker({
	addSliderAccess: true,
	maxDate:new Date(),
	sliderAccessArgs: { touchonly: false }
	});
    
	$('#endTime').datetimepicker({
	addSliderAccess: true,
	maxDate:new Date(),
	sliderAccessArgs: { touchonly: false }
    });
});



var partiesArray = new Array();
<c:forEach var="parties" items="${partiesList}">
	var parties1 ={
	id:"${parties.id}",
	name:"${parties.name}"
	}
	partiesArray.push(parties1);
</c:forEach>

var charsArray = new Array();
<c:forEach var="chars" items="${characteristicsList}">
	var chars1 ={
	id:"${chars.id}",
	name:"${chars.name}"
	}
	charsArray.push(chars1);
</c:forEach>
var rolesArray = new Array();
<c:forEach var="role" items="${rolesList}">
	var roles ={
	id:"${role.id}",
	name:"${role.name}"
	}
	rolesArray.push(roles);
</c:forEach>
$( document ).ready(function() {

	//$( "#startTime" ).datepicker();
	//$( "#endTime" ).datepicker();
	getValues();
});


function showNewDebateDiv(){
$('#newDibateDiv').show();
$('#debateReportDiv').hide();
}
function showDebateReportDiv(){
$('#newDibateDiv').hide();
$('#debateReportDiv').show();
}
</script>


<body>
<br>
<!-- For Heading -->

<div id="editNewsOuter">
  <div id="editNewsInner"></div>
</div>

<div id='profileManagementMainOuterDiv' style="margin-top:-40px;">

 <!-- For Heading end -->
 
<div id='debateMainDiv' class="divInfo">
	<div id="debateManagementDiv">	
	
				<!---Tab Header --Menu--->
				<ul class="nav nav-tabs" id="myTab"  style="margin-top: 23px;">
				
					<li class="active"><a data-toggle="tab" value="Upload News" style="cursor:pointer;color: #005580;" onclick="showNewDebateDiv();" style="cursor:pointer;color: #005580;" > Create Debate </a>	
					</li>
					
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showDebateReportDiv()" style="cursor:pointer;color: #005580;">View Debate Details</a>
					</li>	                   
				</ul>
	</div>
	
	
	<div id="newDibateDiv">
		<div id="errTab" style="display:none;width:777px;margin-left:50px;">
			<table  class="table">
				<tr>
					<td> Errors in Debate Information : </td>
					<td> Participant Details And Performance : </td>
					<td> Errors in Question : </td>
				</tr>
				<tr>
					<td> <div id="debatErrDiv1"></div></td>
					<td> <div id="debatErrDiv2"></div></td>
					<td> <div id="debatErrDiv3"></div></td>
				</tr>
			</table>
		</div>
			<div id="debateDiv" class="container" style="font-size: 17px;font-weight: bold;line-height: 1.5;">
				<div class="row-fluid" >
					<legend class="boxHeading">Debate Information : </legend>

				<div class="row-fluid" >
						<div class="span12">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Subject : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreSubject();"><i class="icon-plus" style="margin-left:15px;"></i></a></label>
							<input type="text" Class="subjectClass span12" name="subject1" id="subject1" />
							<div id="addedSubjectDiv"></div>				
							
						</div>

				</div>
				<div id="dialogueBoxDiv"></div>
				<div class="row-fluid" >
						<div class="span3" >
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Channel : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" onclick="addAttribute('Channel');" title="Click here to add another Channel" href="javascript:{}"><i class="icon-plus"></i></a></label>
							<s:select name="channel"  id="channel" list="channelList" theme="simple" listKey="id" listValue="name"/>
						</div>
						<div class="span3">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Observer : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Observer" onClick="addAttribute('Observer');"><i class="icon-plus"></i></a></label>
								<s:select name="observer"  id="observer" list="observerList" theme="simple" listKey="id" listValue="name" multiple="multiple"/>

						</div>	
						<div class="span2">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Telecast Time : <font class="requiredFont">*</font></label>
							<s:select name="telecastTime"   id="telecastTime" list="telecastTimeList" theme="simple" listKey="id" listValue="name" cssClass="input-block-level"/>
						</div>
						<div class="span2">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Start Time : <font class="requiredFont">*</font></label>
							<input type="text" class="input-block-level selectWidth" name="startTime" id="startTime"/>
						</div>				
						<div class="span2">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">End Time : <font class="requiredFont">*</font></label>
							<input type="text" class="input-block-level selectWidth" name="endTime" id="endTime"/> 
						</div>	
				</div>	
			</div>
				
				<div id="participantDiv" class="row-fluid m_top10" >
				<legend class="boxHeading">Participant Details And Performance:</legend>
				<div id="participantInnerDiv1"  class="participantDetailsClass scrollit">
				</div>
				<div  class="span12">
				<!--<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreCandidates();"><i class="icon-plus"></i></a>-->
				<a title="Click here to add another Subject" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;margin-right:20px;"/></a>
				</div>
				</div>

				<div id="debateQuestnsDiv">	
					<div class="row-fluid" >
						<!--<legend class="span10" style="margin-left:20px;">Questions :</legend>-->
					<div id="questionDiv1" class="span12">
						<c:forEach  var="parties" items="${debateQuestionList}" varStatus="i">
							<div class="questionAnswerClass row-fluid">
							<div class="span5">
							<input type="hidden" Class="selectWidth"  id="question${i.index+1}" name="question${i.index+1}" value="${parties.id}"></input>
							<label><p style="font-size: 17px;font-weight: bold;line-height: 1.5;"> ${i.index+1} )${parties.name} </p></label>
							</div>
							<div class="span7" >
								Answer : <font class="requiredFont">*</font>
							<input type="text" Class="selectWidth debateAnswr input-block-level" name="answer${i.index+1}" id="answer${i.index+1}"/>	
						</div>

							</div>
						</c:forEach>
					</div>
					</div>
				</div>
				<div id="smsQuestnsDiv" class="row-fluid m_top10">	
					<legend class="boxHeading">SMS Question :</legend>
						<div id="smsPole" class="span12">
						
						<div class="smsPoleClass">
						<div class="row">
								<textarea class="input-block-level" rows="4" cols="50" name="smsques1" id="smsques1" ></textarea> 
						</div>	<div class="row">
								<div class="span8" >
									<label>
									<strong>Option : <font class="requiredFont">*</font></strong>
									</label>
									<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption1"/>
								</div>
								<div class="span3">
									<label><strong>Percentage : <font class="requiredFont">*</font></strong></label>
									<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper1"/>
								</div>
							</div>
							
						</div>
					  </div>

				</div>
				<div  class="span1 offset10">
				<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMorePole();"><i class="icon-plus"></i></a>
				</div>
				<div>
				
				<div id="debateSummery" class="row-fluid m_top10">
					<legend class="boxHeading">Debate Summery :</legend>
					<div class="control-group form-horizontal">
						<label>
						<strong>Debate Summery : <font class="requiredFont">*</font></strong></label>

						<textarea rows="4" cols="50" class="span12" name="debetSum" id="debetSum" ></textarea>
					</div>
					
							
				</div>
				
				<div align="center">
					<a class="btn btn-success" onClick="submitForm();">Submit</a>
				</div>
				</div>
				</div>
			<form id="debateFromDiv" method="post" action="saveDebateDetailsAction.action" name="debateFromDiv">
			<input type="hidden" name="task" id="getDebateDetails" /></form>
	</div>
	<div id="debateReportDiv" style="display:none">
			debate reporrt
	</div>
</div>


</body>
</html>