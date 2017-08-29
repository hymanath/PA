<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Debate Information - TDP Portal </title>
	<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		

	
	
	
	<link  rel="stylesheet" type="text/css" href="debate/js/jquery.google.api/jquery-ui1.10.3.css"/>
	
	
	<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<link rel="stylesheet" type="text/css" href="styles/custom-yui-styles.css">	
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 	
	<link rel="stylesheet" type="text/css" href="debate/pagination/simplePagination.css">
		 
		 		
	
	<!-- YUI Dependency files (Start) -->
		<script type="text/javascript" src="debate/js/yahoo/yahoo-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/yahoo-dom-event.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/element-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/container-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/dom-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/yui-min.js"></script>
		<script type="text/javascript" src="debate/js/json/json-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/connection-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datasource-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datatable-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/paginator-min.js"></script>
		
		
		
		<!-- Skin CSS files resize.css must load before layout.css --> 
		<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
		<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">

		<!-- YUI Dependency files (End) -->
		
		
	
<!-- JQuery files (Start) -->
	<script src="debate/js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>	
	<script src="debate/js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	<script type="text/javascript" src="debate/js/bootstrap.min.js"></script>
	
	<script src="debate/js/jquery-ui-themes-1.10.3.js"></script>
	<script src="debate/js/jquery-ui-timepicker-addon.js"></script>
	<script src="debate/js/jquery-ui-sliderAccess.js"></script>
	
	<script src="debate/js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="js/debate.js"></script>
	
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.js"></script>
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.filter.js"></script>
	<script type="text/javascript" src="debate/js/jquery.dataTables.js"></script>	
	<script type="text/javascript" src="debate/js/jQuery/debate/js/jquery-ui-1.8.24.custom.min.js"/>
	
	

	<!--<script type="text/javascript" src="pagination/pagination1.js"></script>-->

	
	<script type="text/javascript" src="debate/pagination/pagination1.js"></script>
	<script type="text/javascript" src="debate/pagination/jquery.simplePagination.js"></script>
	
	
			
	<style type="text/css">
	#startDateId,#endDateId,#fromDateId,#toDateId
	{
		width:240px !important;
	}
	.requiredFont{
		color:red;
	}
	#errorMsgDiv,#RerrDiv,#RerrDivForAnalysis,#errorForTotal,#RRerrDiv{
		font-weight:bold;
		margin-bottom:10px;
		color:red;
	}
	
	#newsreportTab  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 15px !important;
			}
			
	#debatesTab > thead {
	background:#CDE6FC;
	}

	textarea{
	resize:none;
	}
	select {
	width:220px;
	}
	.errDiv{
	color:red;
	font-weight:bold;
	font-size:12px;
	}
	.succDiv{
	color:green;
	font-weight:bold;
	}
	.scrollit {
	overflow:scroll;
	}

	.evenColor 
	{
		background-color: #E9E9E9;
	}
	.oddColor 
	{
		background-color: #F9F9F9;
	}


	</style>
</head>
<script>
  

  	var fromDateEdit 		= '${fromDate}';
	var toDateEdit 			= '${toDate}';
	var channelEdit			= '${channel}';
	var partyIdEdit 		= '${partyId}';
	var candidateIdEdit 	= '${candidateId}';

function buildDebateDetailsAfterEdit(){
	$('#fromDateId').val(fromDateEdit);
	$('#toDateId').val(toDateEdit);
	$('#channelSelecction').val(channelEdit);
	$('#partySelecction').val(partyIdEdit);
	getCandidatesForSelectedParty(partyIdEdit);
	if(fromDateEdit.trim().length >0 && toDateEdit.trim().length > 0){
		getDebateDetailsBetwinDates(fromDateEdit,toDateEdit,channelEdit,partyIdEdit,candidateIdEdit,0,10)
	}
}
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
	
	$('#fromDateId,#startDateId').datepicker({
	maxDate:new Date()
	});
	$('#toDateId,#endDateId').datepicker({
	maxDate:new Date()
	});
	$('#fromDateIdForAnalysis').datepicker({
	maxDate:new Date()
	});
	$('#toDateIdForAnalysis').datepicker({
	maxDate:new Date()
	});
	
});


$( document ).ready(function() {
	$('#debateTabId').addClass('menuActive');
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').hide();
		getValues();
		getRespectiveSelection();
		getRespectiveSelectionForAnalysis();
		buildDebateDetailsAfterEdit();
		$('#channelsList,#channelSelecction').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
		}).multiselectfilter({    
		});
	$("#createCandidateId").live("click",function(){
		
		$("#errorMsgDiv").html('');
		//var partyId = $("#partySelectNewList").val();
		var candidateName = $.trim($("#newCandidateName").val());
		//var designationId = $("#designationsList").val();
		candidteName = $.trim($("#newCandidateName").val());
		/* if(isValid(candidateName)){
			$('#errorMsgDiv').html('<b style="color:red;margin-left:-125px;">Candidate Name should not contain #,$,%,& Special characters</b>');
			return false;
		} */
	   /* if(partyId == 0)
		{
		  $("#errorMsgDiv").html("Please Select Party");
		  return;
		}*/
		if(candidateName.length == 0)
		{
		 $("#errorMsgDiv").html("Please Enter Candidate Name.");
		  return;
		}
		/* if(designationId == 0)
		{
		 $("#errorMsgDiv").html("Please Select Designation");
		  return;
		} */
		/* if($('#locationId option:selected').val() == 0){
		  $("#errorMsgDiv").html("Please Select Location");
		  return;
		} */

	 /*  var locationValue = "";
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
		} */

		var jsObj =
			{ 
				partyId : debateNewCandiPartyId,
				name:candidateName,
				divId :partyDiv,
				task:"saveCandidateForDebate"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "createCandidateAction.action?"+rparam;						
		callAjax(jsObj,url);
			
	});
	
	$('#myTab > li').click(function(){
	
	$(".subjectClass,#channel,#observer,#telecastTime").css("border","1px solid #CCCCCC");
	$(".hasDatepicker,.partysClass,.candidatesClass").css("border","1px solid #CCCCCC");
	$(".participntRoles,.participantsRoles,.smsOptinPerc").css("border","1px solid #CCCCCC");
	$("#debetSum,#smsques1,.debateAnswr,.smsOptin").css("border","1px solid #CCCCCC");
	$(".ui-state-default").removeClass("ui-state-error");
	
	$('#RerrDiv').html('');
	
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
var channelsArray = new Array();
<c:forEach var="channel" items="${channelList}">
	var channels ={
	id:"${channel.id}",
	name:"${channel.name}"
	}
	channelsArray.push(channels);
</c:forEach>

</script>
<script>

function generateExcelReport(id)
{
	tableToExcel(id, 'Debate Report');
}


var tableToExcel = (function() {
var uri = 'data:application/vnd.ms-excel;base64,'
, template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
return function(table, name) {
if (!table.nodeType) table = document.getElementById(table)
var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
window.location.href = uri + base64(format(template, ctx))
}
})()

</script>

<body>
<br>
<!-- For Heading -->

<div id="editNewsOuter">
  <div id="editNewsInner"></div>
</div>

<div id='profileManagementMainOuterDiv' class="container" style="margin-top:-40px;">

 <!-- For Heading end -->

<div id='debateMainDiv' class="divInfo">
	<div id="debateManagementDiv">	
	
				<!---Tab Header --Menu--->
				<ul class="nav nav-tabs" id="myTab"  style="margin-top: 23px;">
				
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'DEBATE_ENTITLEMENT' )  ||
						fn:contains(sessionScope.USER.entitlements, 'DEBATE_ADMIN_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'DEBATE_CREATE_ENTITLEMENT' ) }">
					<li class="active"><a  id="createId" data-toggle="tab" value="Upload News" style="cursor:pointer;color: #005580;" onclick="showNewDebateDiv();" style="cursor:pointer;color: #005580;" > Create Debate </a>	
					</li>
				</c:if>
					<!--<li class="">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showDebateReportDiv()" style="cursor:pointer;color: #005580;">View Debate Details</a>
					</li>-->
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'DEBATE_ENTITLEMENT' )  ||
						fn:contains(sessionScope.USER.entitlements, 'DEBATE_ADMIN_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'DEBATE_CREATE_ENTITLEMENT' ) || 
						fn:contains(sessionScope.USER.entitlements, 'DEBATE_REPORT_ENTITLEMENT' ) }">
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="detailsNewsId" onClick="showDebateReportDiv()" style="cursor:pointer;color: #005580;">View Debate Details</a>
					</li>
				</c:if>
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'DEBATE_ENTITLEMENT' )  ||
						fn:contains(sessionScope.USER.entitlements, 'DEBATE_ADMIN_ENTITLEMENT' ) ||
						fn:contains(sessionScope.USER.entitlements, 'DEBATE_REPORT_ENTITLEMENT' ) }">
						
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="AnalysisNewsId" onClick="showDebateAnalysisDiv()" style="cursor:pointer;color: #005580;">Debate Analysis</a>
					</li>
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="newDebateAnalysis" onClick="showNewDebateAnalysisDiv()" style="cursor:pointer;color: #005580;">Debate Reports</a>
					</li>
					
				</c:if>
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'DEBATE_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'DEBATE_ADMIN_ENTITLEMENT' ) }">
						
					<li class="dropdown" role="presentation">
						<a aria-controls="myTabDrop1-contents" data-toggle="dropdown" class="dropdown-toggle" id="myTabDrop1" href="#" aria-expanded="false">Debate Actions <span class="caret"></span></a> 
						<ul id="myTabDrop1-contents" aria-labelledby="myTabDrop1" class="dropdown-menu">
							<li><a aria-controls="dropdown1" data-toggle="tab" id="dropdown1-tab" role="tab" href="#dropdown1"onclick="createRole();">Create Role</a></li> 
							<li><a aria-controls="dropdown2" data-toggle="tab" id="dropdown2-tab" role="tab" href="#dropdown2"onclick="createCharacteristics();">Create Characteristics</a></li> 
							
							<li><a aria-controls="dropdown3" data-toggle="tab" id="dropdown3-tab" role="tab" href="#dropdown3"onclick="createDebateQuestion();">Create Question</a></li>
							
							<li><a aria-controls="dropdown4" data-toggle="tab" id="dropdown4-tab" role="tab" href="#dropdown4"onclick="createObserver();">Create Observer</a></li>
							
							<li><a aria-controls="dropdown5" data-toggle="tab" id="dropdown5-tab" role="tab" href="#dropdown5"onclick="createChannel();">Create Channel</a></li>
						</ul> 
					</li>
				</c:if>
				</ul>
	</div>
	

	<div id="newDibateDiv">
	<div>
		
	</div>
		<div id="successMsg" style="display:none;" align="center"></div>
			<div id="debateDiv" class="container" style="font-size: 17px;font-weight: bold;line-height: 1.5;">
				<div class="row-fluid">
					<div class="span3">
						<h5 class="boxHeading">Debate Information </h5>
					</div>
					<div class="span4">
					<span><h5>Debate Location</h5></span>
					   <!--<label class="radio inline">
							<input type="radio" name="stateSelection11"  checked value="0"   class="radioStateCls"/>All
						</label>-->
						<label class="radio inline">
							<input type="radio" name="stateSelection11" value="1"  class="radioStateCls"/>AP
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection11" value="36"  class="radioStateCls"/>TS
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection11" value="2"  class="radioStateCls"/>Others
						</label>
						<span id="debateLocationErrDiv" class="errDiv clearErrCls" style ="display:block;"></span>
				   </div>
					<div class="span5">
						<p style="margin-top: 10px;font-size:12px;"> <b style="">NOTE</b> : Press Alt+t to toggle between Telugu &amp; English </p>
					</div>
					

				<div class="row-fluid" >
						<div class="span12">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Subject : <font class="requiredFont">*</font><span id="subject1Err" class="errDiv clearErrCls" style="margin-left: 100px;"> </span><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreSubject();"><i class="icon-plus" style="margin-left:15px;"></i></a></label>
							<input type="text" Class="subjectClass span12" name="subject1" id="subject1" />
							<div id="addedSubjectDiv"></div>				
							
						</div>

				</div>
				<div id="dialogueBoxDiv"></div>
				<div class="row-fluid" >
						<div class="span3" >
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Channel : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" onclick="addAttribute('Channel');" title="Click here to add another Channel" href="javascript:{}"><i class="icon-plus"></i></a></label>
							<s:select name="channel"  id="channel" list="channelList" theme="simple" listKey="id" listValue="name"/>
							<span id="channelErr" class="errDiv clearErrCls"></span>
						</div>
						<div class="span3">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Observer : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Observer" onClick="addAttribute('Observer');"><i class="icon-plus"></i></a></label>
								<s:select name="observer"  id="observer" list="observerList" theme="simple" listKey="id" listValue="name" multiple="multiple"/>
								<span id="observerErr" class="errDiv clearErrCls" ></span>

						</div>	
						<!--<div class="span2">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Telecast Time : <font class="requiredFont">*</font></label>
							<s:select name="telecastTime"   id="telecastTime" list="telecastTimeList" theme="simple" listKey="id" listValue="name" cssClass="input-block-level"/>
						</div>-->
						<div class="span3">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Start Date : <font class="requiredFont">*</font></label>
							<input type="text" class="input-block-level selectWidth" name="startTime" id="startTime"/>
							<span id="startTimeErr" class="errDiv clearErrCls"></span>
						</div>				
						<div class="span3">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">End Date : <font class="requiredFont">*</font></label>
							<input type="text" class="input-block-level selectWidth" name="endTime" id="endTime"/> 
							<span id="endTimeErr" class="errDiv clearErrCls"></span>
						</div>	
				</div>	
			</div>
				
				<div id="participantDiv" class="row-fluid m_top10" >
				
				<span style="color:red" class="pull-right errDiv clearErrCls" id="participantErrSpanId"> </span>
				
				<legend class="boxHeading">Participant Details And Performance:</legend>
				<div><b>Scale (5 points scale : 0 Poor - 5 Excellent)</b></div>
				<div id="participantInnerDiv1"  class="participantDetailsClass scrollit">
				
				</div>
				<div  class="span12">
				<!--<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreCandidates();"><i class="icon-plus"></i></a>-->
				<a title="Click here to add another Participant" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="Add More" id=""  style="float: right; margin-bottom: 10px; margin-top: 10px;"/></a>
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
								<span id="answer${i.index+1}Err" class="errDiv clearErrCls"></span>
							<input type="text" Class="selectWidth debateAnswr input-block-level" name="answer${i.index+1}" id="answer${i.index+1}"/>	
							<input type="hidden" class="hiddenAnswerCls" attr_hidden_id="answer${i.index+1}"/>
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
								<textarea placeholder="Please Enter SMS Question ..."class="input-block-level" rows="4" cols="50" name="smsques1" id="smsques1" ></textarea> 
						</div>	<div class="row">
								<div class="span7" >
									<label>
									<strong>Option : </strong><font class="requiredFont">*</font>
									</label>
									<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption1"/>
									<span id="smsoption1Err" class="errDiv clearErrCls"></span>
								</div>
								<div class="span3">
									<label><strong>Percentage : </strong><font class="requiredFont">*</font></label>							
									<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper1" key="smsoption1" onKeyup='isNumber("percentage","smsper1"),updatePercntage("smsper1");';/>
									<span id="smsper1Err" class="errDiv clearErrCls"></span>
								</div>
								<div style="width: 160px; float: right; font-size: 12px; color: green;">Remaining Percent  : <span id="percCount">100</span>%</div>
							</div>
							
						</div>
					  </div>
					  <div id="errorForTotal" class="errDiv"></div>
				</div>
				<div  class="span1 offset10">
				<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Option" onClick="addMorePole();"><i class="icon-plus"></i></a>
				</div>

				<div class="row">
					<div class="span12" >
						<label>
						<strong>Youtube URL : </strong>
						</label>
						<input type="text" Class="selectWidth span12" id="youtubeUrl"/>
					</div>
				</div>
				<div id="debateSummery" class="row-fluid m_top10">
					<legend class="boxHeading">Summary :<font class="requiredFont">*</font><span id="debetSumErr" class="errDiv clearErrCls"></span></legend>
					<div class="control-group form-horizontal">
						<!--<label>
						<strong>Debate Summary : <font class="requiredFont">*</font></strong></label>-->

						<textarea placeholder="Please Enter Debate Summary ..." rows="4" cols="50" class="span12" name="debetSum" id="debetSum" ></textarea>
					</div>
					
							
				</div>
				
				<div align="center" style="margin-bottom: 15px; margin-top: 10px;">
					<a class="btn btn-success" onClick="submitForm('save');">Submit</a>
					<img src='images/Loading-data.gif'  id="loadingImgForSaveId" style="width:40px;height:40px;display:none;"/>
				</div>

				</div>
			<form id="debateFromDiv" method="post" action="saveDebateDetailsAction.action" name="debateFromDiv">
			<input type="hidden" name="task" id="getDebateDetails" /></form>
	</div>
	
	<!--<div id="debateReportDiv" class="container" >-->
	
	<div id="debateReportDiv" class="container" style="display:none;">

			<div id="debateRport" >
			<div id="RerrDiv"></div>
			<div class="row-fluid" >
			<div class="span3">
			  <h5 class="boxHeading">Generate Debate Report :</h5>
			</div>
			<div class="span4">
			            <label class="radio inline">
							<input type="radio" name="stateSelection2"  checked value="0"   class="radioStateCls1"/>All
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection2" value="1"  class="radioStateCls1"/>AP
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection2" value="36"  class="radioStateCls1"/>TS
						</label>
				</div>
				</div>
			<h4>  </h4>
				<div class="row-fluid" >
					<div class="span4" > 
					<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">From Date  <font class="requiredFont">*</font></label> <input type="text" id="fromDateId"></input>
					</div>
					
					<div class="span4" > 
					<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">To Date  <font class="requiredFont">*</font></label>  <input type="text" id="toDateId"></input>
					</div>
				<div class="span4" >
				<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Channel :    </label>
				<s:select name="channel"  id="channelSelecction" list="channelList" theme="simple" listKey="id" listValue="name"/>
				</div>					
				</div>
				
				<div class="row-fluid" id="reportTypeSelectionDiv"></div>
				
				<div align="center" style="margin-bottom: 15px; margin-top: 10px;">
					<a class="btn btn-success" onClick="getDebateDetailsBtDates();">Submit</a>
				</div>
				
				
			
			</div>
			
			<div id="dateWiseReportDiv" class="span12 well-small yui-skin-sam yui-dt-sortable yui-dt-paginator yui-pg-container yui-dt" style="overflow-x: scroll;display:none;"></div>
			<div id="paginationAtEnd" style="margin-left:255px;"> </div>
	</div>
		
	<div id="debateAnalysisDiv"  style="display:none;">
		<div id="RerrDivForAnalysis"></div>
		<!--<legend class="boxHeading">Debate Analysis : </legend>-->
		<div class="row-fluid" >
			<div class="span3">
			  <h5 class="boxHeading">Debate Analysis : </h5>
			</div>
			<div class="span4">
			            <label class="radio inline">
							<input type="radio" name="stateSelection3" value="0" checked  class="radioStateCls2"/>All
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection3" value="1"  class="radioStateCls2"/>AP
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection3" value="36" class="radioStateCls2"/>TS
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection3" value="2" class="radioStateCls2"/>Others
						</label>
				</div>
				</div>
		<div class="row-fluid m_top10 text-center" >
			<div class="span4 offset2" > 
			<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">From Date  <font class="requiredFont">*</font></label> <input type="text" id="fromDateIdForAnalysis"></input>
			</div>
			
			<div class="span4" > 
			<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">To Date  <font class="requiredFont">*</font></label>  <input type="text" id="toDateIdForAnalysis"></input>
			</div>		
		</div>
		<div class="row-fluid m_top10 btn-group text-center" >
			<div>
				<ul class="inline">   
					<li><a class="btn btn-info" onClick="getDebateAnalysisDetails('candidate');">Candidate</a> </li> 
					<li><a class="btn btn-info" onClick="getDebateAnalysisDetails('party');">Party</a></li>
					<li><a class="btn btn-info" onClick="getDebateAnalysisDetails('smsPole');">SMS Poll</a></li> 
				</ul>
			</div>
		
		</div>
		<div id="analysisDiv"></div>
	</div>
	
	<div id="newDebateAnalysisDiv" style="display:none;">
		
		<div class="container">
		<div id="RRerrDiv"></div>
		<div class="row-fluid">
			<div class="span8">
				<h3>Debate Analysis Report</h3>   
			</div>
			<div class="span4" style="margin-top: 20px;">
				<div class="pull-right">
				<label class="radio inline">
					<input type="radio" name="stateSelection4" value="1" type="radio" class="debateReportRadioStateCls"/>AP
				</label>
				<label class="radio inline">
					<input type="radio" name="stateSelection4" value="36" type="radio" class="debateReportRadioStateCls"/>TS
				</label>
				<label class="radio inline">
					<input type="radio" name="stateSelection4" value="0" checked type="radio" class="debateReportRadioStateCls"/>All
				</label>
				</div>
			</div>
		</div>
			
		<div class="row-fluid" >
					<div class="span4" >           
					<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">From Date  <font class="requiredFont">*</font></label> <input type="text" id="startDateId"></input>
					</div>
					
					<div class="span4" > 
					<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">To Date  <font class="requiredFont">*</font></label>  <input type="text" id="endDateId"></input>
					</div>
				<div class="span4" >
				<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Channel :    </label>
				<s:select name="channel"  id="channelsList" list="channelList" theme="simple" listKey="id" listValue="name" />
				</div>
				

				<div class="row-fluid" id="reportTypesDiv"></div>
				
				<div align="center" style="margin-bottom: 15px; margin-top: 10px;">
					<a class="btn btn-success" onClick="getNewsDebateAnalysisReport();">Submit</a>
				</div>
				
				<div align="center" style="margin-bottom: 15px; margin-top: 10px;">
						<img src='images/Loading-data.gif'  id="searchDataImg" style="width:70px;height:60px;display:none;"/>
				</div>
				
			
		</div>
				
				
		<div class="row" id="partyOverallSummeryRow" style="display:none;">
			<div class="span12">
				<div class="row">
					<div class="span12  widgetservey_Red m_top20">
							
							<legend class="boxHeading">Party Wise Over All Performance <a class="btn btn-info  btn-mini" style="float:right;display:none;" onClick="generateExcelReport('firstReport')"  id="summeryExoperExcelId" >Export Excel </a></legend>
						<div class="row">
						 <div class="span12" id="partyOverallSummery" >							
						</div>
						</div>					
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="row"  id="topicwiseStrongAndWeakRow" style="display:none;">
			<div class="span12">
				<div class="row">
					<div class="span12  widgetservey_Red m_top20">
							
							<legend class="boxHeading">Debate Wise Each Party Strong And Weak<a class="btn btn-info  btn-mini" style="float:right;display:none;" onClick="generateExcelReport('topicwiseStrongAndWeak')" id="StrongAndWeakExoperExcelId" >Export Excel </a></legend>
						<div id="topicwiseStrongAndWeak"></div>
					
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="eachAttributePartyCandidateIdRow" style="display:none;">
			<div class="span12">
				<div class="row ">
					<div class="span12  widgetservey_Red m_top20">
					<legend class="boxHeading">Party Wise Each Candidate Performance With Attributes<a class="btn btn-info  btn-mini" style="float:right;display:none;" onClick="generateExcelReport('secondReport')" id="candidateIdExoperExcelId">Export Excel</a></legend>
							
						<div class="row">
						 <div class="span12" id="eachAttributePartyCandidateId" >
							
							
						</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="row"  id="partyCandidatePerformanceDivRow" style="display:none;">
			<div class="span12">
				<div class="row ">
					<div class="span12  widgetservey_Red m_top20">
						
							<legend class="boxHeading">Topic Wise Each Candidate Performance<a class="btn btn-info  btn-mini" style="float:right;display:none;" onClick="generateExcelReport('thirdReport')" id="performanceExoperExcelId">Export Excel</a></legend>
						<div class="row">
						 <div class="span12" id="partyCandidatePerformanceDiv" >							
						  </div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		
		
		<div class="row" id="candidatePartyPerformanceIdRow"  style="display:none;">
			<div class="span12">
				<div class="row">
					<div class="span12  widgetservey_Red m_top20">
							<legend class="boxHeading">Topic Wise Each Candidate Performance With Attributes<a class="btn btn-info btn-mini" style="float:right;display:none;" onClick="generateExcelReport('fourthReport')"  id="candidatePartyExoperExcelId">Export Excel</a></legend>
						<div class="row">
						 <div class="span12" id="candidatePartyPerformanceId">
						</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	</div>
	
		<div id="creationDiv" class="span10" align="center"></div> 
	
		<div id="createCandidateDiv" style="display:none;">
				
				<div id="errorMsgDiv"></div>
				<div id="dHintDiv"> Note: Candidate Name should not contain #,$,%,& And Alphanumeric characters.
				</div>
				<table style="margin-top: 24px;"><tr>
				<td> Party Name </td>
				<td><!-- <select id="partySelectNewList"> --><span id="presentParty"></span>
				</select></td></tr>

				<tr><td>Candidate Name</td>
				<td><input type="text" id="newCandidateName" onkeypress="return onlyAlphabets(event,this);"/></td>
				</tr>
				<tr><!--<td>Location Level</td>
				<td>
					
					   <label class="radio inline">
							<input type="radio" name="stateSelection1" value="1"  class="radioDebateDetailsStateCls"/>AP
						</label>
						<label class="radio inline">
							<input type="radio" name="stateSelection1" value="36"  class="radioDebateDetailsStateCls"/>TS
						</label>
				</td>-->
				</tr>
				</table>
				<input type="button" value="submit" class="btn" id="createCandidateId" key="'+key+'" partyListId="'+partyListId+'"/>

			</div>
</div>
<script>
$("#createId,#AnalysisNewsId,#detailsNewsId,#newDebateAnalysis").on("click",function(){
	$( 'input[name="stateSelection1"][value="0"]').trigger("click");
	$( 'input[name="stateSelection2"][value="0"]').trigger("click");
	$( 'input[name="stateSelection3"][value="0"]').trigger("click");
	$( 'input[name="stateSelection4"][value="0"]').trigger("click");
});
/*$(".radioStateCls1").on("click",function(){
	alert(4);
	$("#debateReportDiv").html('');
});*/
</script>
</body>
</html>