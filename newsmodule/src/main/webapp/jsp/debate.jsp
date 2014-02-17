<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Debate Information - TDP Portal </title>
	<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="js/bootstrap.min.js"></script>

	
	<script src="js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>
	
		<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>

	<script src="js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	

	<script src="js/jquery-ui-themes-1.10.3.js"></script>
	<script src="js/jquery-ui-timepicker-addon.js"></script>
	<script src="js/jquery-ui-sliderAccess.js"></script>



	<script src="js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
	
	
	
	
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui1.10.3.css"/>
	
	
	<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<link rel="stylesheet" type="text/css" href="styles/custom-yui-styles.css">	
		
		 
		 		
	<!-- JQuery files (Start) -->

	<!-- YUI Dependency files (Start) -->
		<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
		<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
		<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
		<script type="text/javascript" src="js/json/json-min.js"></script>
		<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
		
		
		
		<!-- Skin CSS files resize.css must load before layout.css --> 
		<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
		<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">

		<!-- YUI Dependency files (End) -->

		 
	<script src="js/debate.js"></script>
		
			
	<style type="text/css">
	#errorMsgDiv,#RerrDiv,#RerrDivForAnalysis{
		font-weight:bold;
		margin-bottom:10px;
		color:red;
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
	
	$('#fromDateId').datepicker({
	maxDate:new Date()
	});
	$('#toDateId').datepicker({
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

		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').hide();
		getValues();
		getRespectiveSelection();
	$("#createCandidateId").live("click",function(){
		
		$("#errorMsgDiv").html('');
		//var partyId = $("#partySelectNewList").val();
		var candidateName = $.trim($("#newCandidateName").val());
		var designationId = $("#designationsList").val();
		candidteName = $.trim($("#newCandidateName").val());
		if(isValid(candidateName)){
			$('#errorMsgDiv').html('<b style="color:red;margin-left:-125px;">Candidate Name should not contain #,$,%,& Special charactors</b>');
			return false;
		}
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
		if(designationId == 0)
		{
		 $("#errorMsgDiv").html("Please Select Designation");
		  return;
		}
		if($('#locationId option:selected').val() == 0){
		  $("#errorMsgDiv").html("Please Select Location");
		  return;
		}

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
				partyId : debateNewCandiPartyId,
				candidateName:candidateName,
				divId :partyDiv,
				roleOptionsID :roleOptionsID,
				designationId:designationId,
				locationId : $('#locationId option:selected').val(),
				locationValue : locationValue,
				task:"saveCandidateForDebate"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveCandidateAndPartyAction.action?"+rparam;						
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
				<c:if test="${sessionScope.USER != null}">
				<c:if test="${sessionScope.USER.userAccessType == 'debate'}">
					<li class="active"><a data-toggle="tab" value="Upload News" style="cursor:pointer;color: #005580;" onclick="showNewDebateDiv();" style="cursor:pointer;color: #005580;" > Create Debate </a>	
					</li>
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showDebateReportDiv()" style="cursor:pointer;color: #005580;">View Debate Details</a>
					</li>
				</c:if>	
				</c:if>	
				
						
				<c:if test="${sessionScope.USER != null}">
				<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
					<li class="active">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showDebateReportDiv()" style="cursor:pointer;color: #005580;">View Debate Details</a>
					</li>
					<li class="">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showDebateAnalysisDiv()" style="cursor:pointer;color: #005580;">Debate Analysis</a>
					</li>
				</c:if>	
				</c:if>	
				</ul>
	</div>
	
	<c:if test="${sessionScope.USER != null}">
	<c:if test="${sessionScope.USER.userAccessType == 'debate'}">
	<div id="newDibateDiv">
		<div id="successMsg" style="display:none;" align="center"></div>
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
						<!--<div class="span2">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Telecast Time : <font class="requiredFont">*</font></label>
							<s:select name="telecastTime"   id="telecastTime" list="telecastTimeList" theme="simple" listKey="id" listValue="name" cssClass="input-block-level"/>
						</div>-->
						<div class="span3">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Start Date : <font class="requiredFont">*</font></label>
							<input type="text" class="input-block-level selectWidth" name="startTime" id="startTime"/>
						</div>				
						<div class="span3">
							<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">End Date : <font class="requiredFont">*</font></label>
							<input type="text" class="input-block-level selectWidth" name="endTime" id="endTime"/> 
						</div>	
				</div>	
			</div>
				
				<div id="participantDiv" class="row-fluid m_top10" >
				<legend class="boxHeading">Participant Details And Performance:</legend>
				<div><b>Scale (5 points scale : 0 Poor - 5 Excellent)</b></div>
				<div id="participantInnerDiv1"  class="participantDetailsClass scrollit">
				
				</div>
				<div  class="span12">
				<!--<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreCandidates();"><i class="icon-plus"></i></a>-->
				<a title="Click here to add another Subject" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="Add More" id=""  style="float: right; margin-bottom: 10px; margin-top: 10px;"/></a>
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
								<textarea placeholder="Please Enter SMS Question ..."class="input-block-level" rows="4" cols="50" name="smsques1" id="smsques1" ></textarea> 
						</div>	<div class="row">
								<div class="span7" >
									<label>
									<strong>Option : </strong>
									</label>
									<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption1"/>
								</div>
								<div class="span3">
									<label><strong>Percentage : </strong></label>							
									<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper1" key="smsoption1" onKeyup='isNumber("percentage","smsper1"),updatePercntage("smsper1");';/>
								</div>
								<div style="width: 160px; float: right; font-size: 12px; color: green;">Remaining Percent  : <span id="percCount">100</span>%</div>
							</div>
							
						</div>
					  </div>

				</div>
				<div  class="span1 offset10">
				<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMorePole();"><i class="icon-plus"></i></a>
				</div>

				
				<div id="debateSummery" class="row-fluid m_top10">
					<legend class="boxHeading">Summary :</legend>
					<div class="control-group form-horizontal">
						<!--<label>
						<strong>Debate Summary : <font class="requiredFont">*</font></strong></label>-->

						<textarea placeholder="Please Enter Debate Summary ..." rows="4" cols="50" class="span12" name="debetSum" id="debetSum" ></textarea>
					</div>
					
							
				</div>
				
				<div align="center" style="margin-bottom: 15px; margin-top: 10px;">
					<a class="btn btn-success" onClick="submitForm();">Submit</a>
				</div>

				</div>
			<form id="debateFromDiv" method="post" action="saveDebateDetailsAction.action" name="debateFromDiv">
			<input type="hidden" name="task" id="getDebateDetails" /></form>
	</div>
	</c:if>
	</c:if>
	<c:if test="${sessionScope.USER != null}">
	<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
	<div id="debateReportDiv" class="container" >
	</c:if>
	</c:if>
	<c:if test="${sessionScope.USER != null}">
	<c:if test="${sessionScope.USER.userAccessType == 'debate'}">
	<div id="debateReportDiv" class="container" style="display:none;">
	</c:if>
	</c:if>
			<div id="debateRport" >
			<div id="RerrDiv"></div>
			<legend class="boxHeading">Generate Debate Report :</legend>
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
			
			<div id="dateWiseReportDiv" class="span12 well-small yui-skin-sam yui-dt-sortable yui-dt-paginator yui-pg-container yui-dt" style="overflow-x: scroll;"></div>
	</div>
	
	
	<c:if test="${sessionScope.USER != null}">
	<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
	
	<div id="debateAnalysisDiv" class="container" style="display:none;">
		<div id="RerrDivForAnalysis"></div>
		<legend class="boxHeading">Debate Analysis : </legend>
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
	</c:if>
	</c:if>
		<div id="createCandidateDiv" style="display:none;">
				
				<div id="errorMsgDiv"></div>
				<div id="dHintDiv"> Note: Candidate Name should not contain #,$,%,& Special charactors.
				</div>
				<table style="margin-top: 24px;"><tr>
				<td> Party Name </td>
				<td><!-- <select id="partySelectNewList"> --><span id="presentParty"></span>
				</select></td></tr>

				<tr><td>Candidate Name</td>
				<td><input type="text" id="newCandidateName"/></td></tr>
				<tr>
				<td>Designation</td>
				<td><select id="designationsList"></select></td>
				</tr>
				<tr>
				<td>Location</td>
				<td>
					<select id="locationId" onChange="getTypeOfConstituency(this.value);">
						<option value=0>Select Location</option>
						<option value=3>Country</option>
						<option value=1>Assembly Constituency</option>
						<option value=2>Parliment Constituency</option>
					</select>
				</td>
				</tr>  
				<tr id="pcConstituencyRow">
				<td>Constituency</td>
				<td>
				<s:select name="parliamSelReport"  id="parliamSelReportId" list="parlConstiList1" theme="simple" listKey="id" listValue="name"/></td>
				</tr>
				<tr id="acConstituencyRow">
				<td>Constituency</td>
				<td>
				<s:select name="assembSelReport"  id="assembSelReportId" list="assemConstiList1" theme="simple" listKey="id" listValue="name"/></td>
				</tr>
				</table>
				<input type="button" value="submit" class="btn" id="createCandidateId" key="'+key+'" partyListId="'+partyListId+'"/>

			</div>
</div>
</body>
</html>