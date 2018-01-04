<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alert Details </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	<!-- for file uploader -->
	<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
	<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />  
	<!-- for file uploader -->
	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	<!--<script src="dist/CreateAlert/alertCommonCadreSearch.js" type="text/javascript"></script>-->
	
	<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	
	<style type="text/css">
	.attachmentsBlock , .attachmentsUpload
	{
		padding:0px;
	}
	.attachmentsBlock li , .attachmentsUpload li
	{
		margin:5px 0px;
		list-style:none;
		font-size:13px;
		color:rgba(0,0,0,0.6);
		position:relative;
	}            
	.attachmentsUpload li .closeIcon
	{
		padding:2px 3px;
		text-align:center;
		cursor:pointer;
		position:absolute;
		right:0px;
	}
	.attachmentsUpload li
	{
		background-color:#fff;
		border:1px solid #ddd;
		padding:5px;
	}
	#alertStatusForm label
	{
		font-weight:600;
	}
	.jFiler-input-dragDrop
	{
		padding:5px;
	}
	.icon-jfi-folder
	{
		font-size: 30px
	}
	.jFiler-input-text h3
	{
		font-size:16px;
	}
	.jFiler-input-text span
	{
		margin-top:5px !important;
		margin-bottom:5px !important;
	}
	.jFiler-input-choose-btn.blue
	{
		color:#fff !important;
	}
		.panelTitleFont
		{
			font-size:15px;
		}
		.attachmentsBlock , .attachmentsUpload
		{
			padding:0px;
		}
		.attachmentsBlock li , .attachmentsUpload li
		{
			margin:5px 0px;
			list-style:none;
			font-size:13px;
			color:rgba(0,0,0,0.6);
			position:relative;
		}
		.attachmentsUpload li .closeIcon
		{
			padding:2px 3px;
			text-align:center;
			cursor:pointer;
			position:absolute;
			right:0px;
		}
		.attachmentsBlock li span.border
		{
			border-bottom:1px solid rgba(0,0,0,0.6);
		}
		.attachmentsBlock li i
		{
			margin-right:8px;
			font-size:12px;
			color:#333;
		}
		.attachmentsUpload li
		{
			background-color:#fff;
			border:1px solid #ddd;
			padding:5px;
		}
		.m_top20
		{
			margin-top:20px !important;
		}
		.uploadedDocuments
		{
			padding:0px;
		}
		.uploadedDocuments li
		{
			padding-left:15px;
			position:relative;
			list-style:none;
		}
		.uploadedDocuments li:before
		{
			position:absolute;
			left:0px;
			content:'\e142';
			font-family: 'Glyphicons Halflings';
		}
		.label-neutral{
			background:orange;
		}
		#searchedMembersId_length , #searchedMembersId_info
		{
			display:none;
		}
		#commomCadreSearchDiv .panel .panel-body
		{
			background:#fff !important;
		}
		
		.panelAlert
		{
			border:0px;
			box-shadow:none;
			box-shadow:0px 0px 4px rgba(0,0,0,0.4);
			background:#fff;
		}
		.panelAlert .panel-heading , .panelAlert .panel-body
		{
			background:#fff;
			padding:15px;
		}
		.createAlertModalCls
		{
			border-radius:0px;
		}
		.form-control
		{
			border-radius:0px;
		}
		.createAlertModalCls .modal-header
		{background:#ccc;}
		.input-group .form-control , .input-group .input-group-addon
		{
			border-radius:0px;
		}
		
		
		.btnCreateAlert
		{
			position:absolute;bottom:30px;right:30px;height:40px;width:40px;border-radius:50%;box-shadow:0px 0px 5px rgba(0,0,0,0.4);padding:3px 7px
		}
		.m_top10
		{
			margin-top:10px;
		}
		.tableModal tr td
		{
			border:0px !important; 
		}
		p,ul,h1,h2,h3,h4,h5,h6
		{
			margin:0px;
		}
		.alertStatusTracking li
		{
			list-style:none;
			border-radius:4px;
			position:relative;
			margin-top:5px;
		}
		.arrow_box {
			position: relative;
			background: #fff;
			border: 1px solid #ddd;
			padding:7px;
			border-radius:4px;
		}
		.arrow_box:after, .arrow_box:before {
			right: 100%;
			top: 10px;
			border: solid transparent;
			content: " ";
			height: 0;
			width: 0;
			position: absolute;
			pointer-events: none;
		}

		.arrow_box:after {
			border-color: rgba(255, 255, 255, 0);
			border-right-color: #fff;
			border-width: 5px;
			margin-top: -5px;
		}
		.arrow_box:before {
			border-color: rgba(194, 225, 245, 0);
			border-right-color: #c2e1f5;
			border-width: 6px;
			margin-top: -6px;
		}
		.assignCandidate {
    background: #ccc none repeat scroll 0 0;
    color: #666;
    cursor: pointer;
    padding: 5px;
    position: absolute;
    right: 0;
    top: 0;
}
.assignCandidate {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0 !important;
}
.commentsUlCls li:before{
	top:6px !important;
}

.spinner {
	margin:auto;
	width: 40px;
	height: 40px;
	position: relative;
	text-align: center;
	-webkit-animation: sk-chasingDotsRotate 2s infinite linear;
    animation: sk-chasingDotsRotate 2s infinite linear; 
}
.spinner .dot1 , .spinner .dot2 {
    width: 60%;
    height: 60%;
    display: inline-block;
    position: absolute;
    top: 0;
    background-color: #1ABC9C;
    border-radius: 100%;
    -webkit-animation: sk-chasingDotsBounce 2s infinite ease-in-out;
    animation: sk-chasingDotsBounce 2s infinite ease-in-out; 
}
.spinner .dot2 {
    top: auto;
    bottom: 0;
    -webkit-animation-delay: -1s;
    animation-delay: -1s; 
}
@-webkit-keyframes sk-chasingDotsRotate {
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg); } }

@keyframes sk-chasingDotsRotate {
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg); } }

@-webkit-keyframes sk-chasingDotsBounce {
  0%, 100% {
    -webkit-transform: scale(0);
            transform: scale(0); }
  50% {
    -webkit-transform: scale(1);
            transform: scale(1); } }

@keyframes sk-chasingDotsBounce {
  0%, 100% {
    -webkit-transform: scale(0);
            transform: scale(0); }
  50% {
    -webkit-transform: scale(1);
            transform: scale(1); } }
.uploadCssDiv {padding: 10px; margin-top: 10px; background: rgb(229, 229, 229) none repeat scroll 0% 0%;text-align:left}
.uploadCssDiv1 {padding: 3px; margin-top: 10px; background: rgb(229, 229, 229) none repeat scroll 0% 0%;text-align:left}
	</style>
	
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
	<script type="text/javascript" src="js/yahoo/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- YUI Dependency files (End) -->
 </head>     
<body style="position:relative;">
<div class="container">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">

						<h4 class="panel-title "><span id="headingSpanId" class="text-capital"></span> ALERT DETAILS</h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" id="alertBasicDataDiv"></div>
						<div class="table-responsive" >
							<table class="table table-bordered tableCategory">
								<tr>
									<td style="vertical-align: top;">
										<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;type</b></span></p>
										<p><span  id="typeId"></span></p>
									</td>
									<td style="vertical-align: top;">
										<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;created&nbsp;date</b></span></p>
										<p><span  id="createdDate"></span></p>
									</td>
									<td style="vertical-align: top;">
										<p class="text-capital"><span class="text-muted"><b>Alert&nbsp;status</b></span></p>
										<p><span id="alertStatus"></span></p>
									</td>
									<td id="severityTdId" style="vertical-align: top;display:none;">
										<p class="text-capital"><span class="text-muted"><b>severity</b></span></p>
										<p><span class="circle severityIdColorCls"></span><span  id="severityId">Critical</span></p>
									</td>
									<td style="vertical-align: top;">
										<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;impact&nbsp;level</b></span></p>
										<p style="text-transform: lowercase;"><span  id="levelId"></span></p>
									</td>
									<td style="vertical-align: top;">
										<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;location</b></span></p>
										<p><span  id="LocationId"></span></p>
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<p class="text-capital"><span class="text-muted "><b>Title</b></span></p>
										<p><span  id="titleId"></span></p>
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<p class="text-capital"><span class="text-muted "><b>description</b></span></p>
										<p><span  id="descriptionId"></span></p>
									</td>
								</tr>
								<tr style="display:none" id="imageUrlTrId">
									
									<td colspan="8">
									<p class="text-capital"><span class="text-muted ">Article  </span> :
										<ul class="list-inline imageUrlUlCls"></ul>
									</td>
								</tr>
								<tr style="display:none" id="groupArticlesId">
									
									<td colspan="8">
									<p class="text-capital"><span class="text-muted ">Grouped Articles  </span> :
										<ul class="list-inline imageUrlUlCls groupArticlesCls"></ul>
									</td>
								</tr>
								<tr id="existingDocsTrId" style="display:none;">
									<td id="existingDocsTdId" colspan="8"></td>
								</tr>
								<tr>
									<td colspan="8">
										<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
											<form id="alertDocs" name="alertDocs">
												<h4 class="m_0 text-success font_weight" style="">UPLOAD SCAN COPY</h4>  
												<input type="file" id="alertScanCopyId" multiple="multiple"  name="files[]" class="m_top20"/>
												<input type="hidden" id="alertHiddenId" name="alertId">
												<p id="errFileId" class="text-center text-danger"></p>
												<center><button type="button" style="width:38%" class="btn btn-success" id="uploadAlertDocBtnId">Upload</button></center>
											</form>										
										</div>
									</td>
								</tr>
							</table>
						</div>
						<!--Clarification Block start -->
						<c:if test="${sessionScope.USER.isAdmin != 'true'}">
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'CREATE_ALERT_ENTITLEMENT')}">
							 <div class="row m_top10">
								 <div class="col-md-12 col-xs-12 col-sm-12 hideVarificationStatusCls">
									<div class="panel">
										<div class="panel-body" style="background-color:#E5E5E5;border: 2px solid #c3c3c3;border-radius: 7px;">
											<div class="row">
												<div class="col-md-6 col-xs-12 col-sm-6 verificationHeadingCls">
													<h5 class="panel-title"> 
														<b style="font-size:14px"><span class="text-capital" >Verification Required? </span>
														<span >(Clarification from info-cell)</span></b>
													</h5>
													
												</div>
												<div class="col-md-6 col-xs-12 col-sm-6">
													
													<div id="verificationCreationHeadingId">
														<span class="pull-right">
															<i class="glyphicon glyphicon-menu-down" id="menuDown"></i>
															<i class="glyphicon glyphicon-menu-up" id="menuUp" style="display:none;"></i>
														</span>
														<label class="checkbox-inline pull-right">
															<input id="isClarificationNotRequiredChckBxId" checked type="checkbox"/>No
														</label>
														<label class="checkbox-inline pull-right">
															<input id="isClarificationRequiredChckBxId" type="checkbox"/>Yes
														</label>
													</div>
													<span class="pull-right text-capital" id="alertStatusHeadingId" style="font-weight:bold;font-size:14px"></span>
												</div>
											</div>
											<hr class="m_top10" style="border-top:1px solid rgba(0,0,0,0.6);"/>
											<div class="hideCommentBlockCls">
												<div id="converSationDtlsDivId"></div>
											</div>
										<form id="updateVerificationStatusFormAction" name="updateVerificationStatusFormAction">
											<div class="hideUpdateBlockCls" style="display:none;">
											   <p style="font-weight:bold;font-size:15px;color: #3e3e3e;" class="text-capitalize m_top20 panelTitleFont">Add Comments&nbsp<span style="color:red;">*</span></p>
											   <textarea class="form-control commentCls" name="clarificationComments" placeholder="Few Lines About  Explanatory"></textarea>
												<div class="row">
													<div class="col-md-3 col-xs-12 col-sm-6">
														<p style="font-weight:bold;font-size:15px;color: #3e3e3e;" class="text-capitalize m_top20 panelTitleFont">Add Attachments&nbsp&nbsp</p>
													</div>
													<div class="col-md-3 col-xs-12 col-sm-6">
														<button type="button" class="btn btn-primary btn-xs pull-left m_top20" id="addClarificationFile" title="Add Another File" style="border-radius:50%;padding:5px 6px 7px 7px;"><i class="glyphicon glyphicon-plus"></i></button>
													</div>
												</div>
											  <div class="row">
													<div class="uploadAttachmentDivCls col-md-6 col-xs-12 col-sm-6">
														<ul class="attachmentsUpload"  id="">
															<li>
																<input type="file" class="btn btn-mini" name="files" id="uploadClarificationFileId0"><span class="ClearFileCls closeIcon" style="display:none;cursor:pointer;" attr_id="0">x</span>
															</li>
														</ul>
														<ul class="attachmentsUpload"  id="extraClarificationUploadFileDiv"></ul>
													</div>
												</div>
												<div class="m_top20 row">
													<div class="col-md-5 col-xs-12 col-sm-6">
														<label class="verificationStatusCls" style="display:none;">Verification Status</label>
														<select id="verificationStatusSlctBxId" style="display:none;" class="form-control verificationStatusCls">
														</select>
														<select id="verificationUserSeletBoxId" style="display:none;" name="alertVerificationAssignedUserId" class="form-control m_top20"></select>
														<button class="btn btn-success m_top20 text-capital " type="button" id="updateVerificationStatusBtnId"><h4>update verification status</h4></button>
													</div>
													  <div class="col-md-12 col-xs-12 col-sm-12 m_top10 notificationHeadingCls"  style="display:none;">
														  <p style="font-size: 15px; font-weight: bold;">Note : Adding comment will reverse this status to PROGRESS</p>
													  </div>
												</div>
											</div>
											<input type="text" hidden value="" id="alertIdForClarification" name="alertId"/>
											<input type="text" hidden value="0" id="clarificationStatusId" name="clarificationStatusId"/>
										 </form>
										</div>
									</div>
								</div>
							 </div>
						 </c:if>
					 </c:if>
				<!-- End -->
						<div class="row m_top10">
							<div class="col-md-4 col-xs-12 col-sm-6" style="border-right:1px solid #ddd;">
								<h4 class="panel-title text-capital"><b>involved members in this alert</b>
								<span id="involvedCandidatesCnt" class="pull-right">0</span></h4>
								<ul class="involvedMembersUl" id="alertCandidateDataId">
									<li>
										<div class="media">
											<div class="media-left">
												<img src="dist/img/thumb.jpg" alt="Profile Image" style="width:50px;"/>
											</div>
											<div class="media-body"></div>
										</div>
									</li>
								</ul>  
							</div>  
							<div class="col-md-8 col-xs-12 col-sm-6" id="statusTrckingDivId">
								<h4 class="panel-title text-capital">alert status tracking comments</h4>
								
								<div id="alertCommentsDivIdNew"></div>
								<!--<div  id="alertCommentsDiv"></div>-->
							</div>
						</div>
						<div class="row m_top10 hideBlockCls" style="display:none;">
							<div class="">
								<div class="bg_cc pad_10" style="box-shadow: 0 -10px 8px rgba(0, 0, 0, 0.4);">
									<div class="row">
										<!--<div class="col-md-4 col-xs-12 col-sm-4">
												<div class="panel panel-default panelHeights">
													<div class="panel-heading">
														<h4 class="panel-title">ALERT CLARIFICATION REPORT</h4>
													</div>
													<div class="panel-body">
														<div class="row">
															<div id="mainDivId"></div>
														</div>
													</div>
												</div>
										</div>-->
										<div class="col-md-4 col-xs-12 col-sm-4">
											<div class="panel panel-default panelHeights" style="position:relative;">
												<div class="panel-heading bg_ff">
													<h4 class="panel-title text-success">ASSIGNED CANDIDATES - 	
														<span id="assignCandidatesCnt">0</span>
														<c:if test="${fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT')}">
														<input type="button" value="ASSIGN" class="btn btn-primary assignModel pull-right btn-xs">
														</c:if>
													</h4>
												</div>
												<div class="panel-body ">
													<div class=" " id="disabledDivId">
													<!--<p class="text-center">
														<img src="images/TIme.png"/>
														<h4 class="panel-title"><b>WAITING FOR CLARIFICATION REQUIRED [OR] NOT</b></h4>
													</p>-->
													</div>
												</div>
												<div style="text-align: center;" id="alertAssignedCandidateDataId"></div>
											</div>
										</div>
										<c:if test="${fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_DASHBOARD_USER_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_LOGIN_ENTITLEMENT')}">
										<div class="col-md-8 col-xs-12 col-sm-8" id="editCmntDivId">
											<div class="panel panel-default panelHeights" style="position:relative;">
												<div class="panel-body" >
													<form id="alertStatusForm" name="alertStatusForm">
														<div class="row">
															<div class="col-md-6 col-xs-12 col-sm-6">
																<label>Alert Status</label>
																<select class="dropkickClass" id="statusId">
																	<!--<option value='0'>Select Status</option>
																	<option value='1'>Pending</option>      
																	<option value='2'>Notified</option>  
																	<option value='3'>Action In Progess</option>  
																	<option value='4'>Completed</option>
																	<option value='5'>Unable to Resolve</option>
																	<option value='6'>Action Not Required</option>
																	<option value='7'>Duplicate</option>-->
																</select>
															</div>
															<div class="col-md-6 col-xs-12 col-sm-6">
																<label>Assigned Cadre</label>
																<select class="" id="assignedCadreId"  multiple name="cadreIds">
																	<option value="0">Select Assign Cadre</option>
																</select>	
															</div>
														</div>
														<div class="row">
															<div class="col-md-12 col-xs-12 col-sm-12">
																<label>Comments</label>
																<label class="radio-inline">
																	<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
																</label>
																<label class="radio-inline">
																	<input type="radio"  value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
																</label>
															</div>
															<div class="col-md-12 col-xs-12 col-sm-12">
																<textarea class="form-control" placeholder="Enter Comments" id="commentsId"></textarea>
															</div>
															<div class="uploadAttachmentDivCls col-md-7 col-xs-12 col-sm-6">
																<label>Add Attachments</label>  
																<ul class="attachmentsUpload">  
																	<li>
																		<input type="file" class="btn btn-mini uploadCssDiv btn-block" name="imageForDisplay" id="uploadFileId0"  />
																		<span class="closeIcon clearFileCls" style="display:none;">x</span>  
																	</li>
																</ul>	
															</div>
															<div class="col-md-7 col-xs-12 col-sm-6">
																<ul class="attachmentsUpload"  id="extraUploadFileDiv"></ul>
															</div>
															<div class="col-md-12 col-xs-12 col-sm-12">
																<button type="button" title="Add Another File" class="btn btn-primary btn-sm pull-right" id="addFile"><i class="glyphicon glyphicon-plus"></i></button>
															</div>
															<div id="errorId" class="m_top10"></div>
															<div class="col-md-12 col-xs-12 col-sm-6">
																<button type="button" style="" class="btn btn-success" id="uploadAlertStatusDocBtnId">Update Alert Status</button>
															</div>
															<input type="hidden" id="alertHiddenIdStatus" name="alertId">
															<input type="hidden" id="alertStatusHiddenId" name="clarificationStatusId">
															<input type="hidden" id="alertCommentsHiddenId" name="clarificationComments">
															<!--<button class="btn btn-success text-capital m_top10 updateAlertStatusCls">Update Alert</button>-->
															<span id="updateAlertajaxImg" class="m_top10"></span>
														</div>
													</form>
												</div>
											</div>
										</div>
										</c:if>
									</div>
									
									<div class="row">
										
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
		
<div class="modal fade" id="ModalShow" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:85%;">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="descriptionTitleId">Assign Candidate</h4>
	  </div>
	  <div class="modal-body">
		<jsp:include page="commonCadreSearch.jsp" flush="true"/>
	 </div>
	 <div class="modal-footer">
		<div id="assignBtnId" >
			<div id="assignEroorDiv"></div>
				<input type="button" value="ASSIGN CANDIDATE" onclick="saveAlertAssignedUser();"  class="btn btn-primary btnNewCustom1">
		</div>	
	 </div>
	</div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
		
		<!--
		<div class="row">
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">INVOLVED CANDIDATES</h4>
					</div>
					<div class="panel-body" style="height:320px;">
						
						<div class="media" id="alertCandidateDataId">
							
						</div>
					</div>
				</div>
				
			</div>
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div id="alertCommentsDiv"></div>
			</div>  
		</div>
		 <div class="row">
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">ASSIGNED CANDIDATES - <span id="assignCandidatesCnt"> - 0</span><input type="button" class="btn btn-primary assignModel pull-right btn-xs" value="ASSIGN"/></h4>
					</div>
					<div class="panel-body" style="height:320px;">
						<div class="media" id="alertAssignedCandidateDataId"></div>
					</div>
				</div>
			</div> 			
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">UPDATE ALERT STATUS</h4>
					</div>
					<div class="panel-body">
						<label>Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<select class="dropkickClass" id="statusId">
							<option value='0'>Select Status</option>
							<option value='1'>Created</option>
							<option value='2'>Action In Progess</option>
							<option value='3'>Completed</option>
						</select>
						
						<div class="m_top10"><b>Select Language: </b> <input type="radio"  value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"> Telugu<input type="radio"  value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"> English </div>
						<label class="m_top10">Comments</label>
						<textarea class="form-control" id="commentsId"></textarea>
						<div id="errorId" class="m_top10"></div>
						<button class="btn btn-success updateAlertStatusCls m_top10">UPDATE</button>
					</div>
					
					
				</div>
			</div>
		</div>	
			<!--<input type="button" value="ASSIGN" class="btn btn-primary assignModel">
			
		<div class="modal fade" id="ModalShow" tabindex="-1" role="dialog">
		  <div class="modal-dialog" style="width:85%;">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="descriptionTitleId">Assign Alert</h4>
			  </div>
			  <div class="modal-body">
				<jsp:include page="commonCadreSearch.jsp" flush="true"/>
			 </div>
			 <div class="modal-footer">
				<div style="display:none;" id="assignBtnId" >
					<div id="assignEroorDiv"></div>
						<input type="button" value="ASSIGN ALERT" onclick="saveAlertAssignedUser();"  class="btn btn-primary btnNewCustom1">
				</div>	
			 </div>
			</div><!-- /.modal-content 
		  </div><!-- /.modal-dialog 
		</div><!-- /.modal 
		</div>-->
<div class="modal fade" tabindex="-1" id="alertDocumentModalId" role="dialog" style="z-index:99999;">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Alert Document</h4>  
			</div>
			<div class="modal-body" id="alertDocumentBodyId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->
</div><!--  /.modal -->
<div class="modal fade" id="myModalShowNew"></div>	
<script type="text/javascript">

var alertId = '${alertId}';
var globalTdpCadreId = '${tdpCadreId}';
var globalUserId = '${sessionScope.USER.registrationID}';
var globalTypeValue = '${status}';
var entilementStr = '${sessionScope.USER.entitlements}';
$(".dropkickClass").dropkick();
 
       /* Hiding involved and assigned block for info cell user */
          var entitlementsArr = [];
		//  entitlementsArr.push("IGNORE_PROGRAMME_COMMITTEE_ALERT_COMMENTS_ENTITLEMENT");
		  var strArr = entilementStr.split(",");
				for(var i=0;i<strArr.length;i++){
					if(i==0){
						entitlementsArr.push(strArr[i].split("[")[1]);
					}else if(i==(strArr.length-1))
						entitlementsArr.push(strArr[i].split("]")[0]);
					else
						entitlementsArr.push(strArr[i]);
				}
				
		    var isInfoCellUser="false";
		   for(var i=0;i<entitlementsArr.length;i++){
			   if(entitlementsArr[i].trim()=="ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT"){
                 isInfoCellUser = "true";
                }
		   }
		  if(isInfoCellUser=="false"){
			$(".hideBlockCls").show();  
		  }
		 /* end */ 
function deleteAlertAssignedCandidates(tdpCadreId)
{
	$("#deleteProcessing"+tdpCadreId).show();
   	var jsObj =
		     {
				alertId  : alertId,
				tdpCadreId: tdpCadreId,
				task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'deleteAlertAssignedCandidateAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
				   $("#deleteProcessing"+tdpCadreId).hide();
				   getAlertAssignedCandidates(alertId);
				   getAlertAssignedCandidate(alertId,"updateStatus");
			      //buildAlertAssignedCandidateData(result);
				});
}

var globalAssignedCadreId=[];
function buildAlertAssignedCandidateData(result)
{
	
	if(result == null || result.length == 0)
	{
	 $("#alertAssignedCandidateDataId").html('No Assigned Candidates..');
	 $("#assignCandidatesCnt").html('0');
		return;
	}
	var str='';
	for(var i in result)
	{
		for(var j in result[i].subList)  
		{
			
			globalAssignedCadreId.push(result[i].subList[j].id.toString().trim());
			str+='<div class="media" style="margin-top:5px;border:1px solid #ddd;">';
			str+='<div class="media-left">';
			str+='<img src="images/cadre_images/'+result[i].subList[j].image+'" onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
			str+='</div>';
			str+='<div class="media-body" style="position:relative;">';
			str+='<c:if test="${fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_DASHBOARD_USER_ENTITLEMENT')}">';
			str+='<span id="deleteProcessing'+result[i].subList[j].id+'" style="display:none;"><img src="images/search.gif" /></span>';
			
			if(result[i].subList[j].comment == null || result[i].subList[j].comment.length<=0){
				str+='<span class=" assignCandidate" attr_tdpCadreId="'+result[i].subList[j].id+'"  onclick="getConfirmation(\''+result[i].subList[j].id+'\');"><i class="glyphicon glyphicon-trash" title="Click here to Delete"></i></span>';
			}
			
			str+='</c:if>';  
			str+='<p class="text-capital"><b>'+result[i].subList[j].name+'</b></p>';
			if(result[i].subList[j].committeePosition != null && result[i].subList[j].committeePosition.length > 0)
				str+='<p class="text-capital"><b>'+result[i].subList[j].electionType+" "+result[i].subList[j].committeeName+' Committee '+result[i].subList[j].committeePosition+'</b></p>';
			if(result[i].subList[j].designation != null && result[i].subList[j].designation != "")
				str+='<b><p class="text-capital">'+result[i].subList[j].designation+'</b></p>';			  
			//str+='<input type="button" class="btn btn-primary assignModel pull-right btn-xs" value="ASSIGN">';
			//str+=' <p class="text-capital"><i><b>-Constituency Incharge</b></i></p>';
			str+=' <p>'+result[i].subList[j].mobileNo+'</p>';
			str+='<p>'+result[i].subList[j].locationVO.constituencyName+'</p>';
			 str+=' </div>';
			 str+=' </div>';
		}
		$("#assignCandidatesCnt").html(result[0].subList.length);
	}
	$("#alertAssignedCandidateDataId").html(str);
	if(result[0].subList.length > 3)
	{
		$("#alertAssignedCandidateDataId").mCustomScrollbar({setHeight:'290px'});
	}
	/*str+='<div  style="border:1px solid #ddd;padding:8px;margin-top:5px;" class="media">';
	str+='<div class="media-left">';
	str+='<img src="'+result[i].image+' "  onerror="setDefaultImage(this);" class="media-object img-center img-responsive  thumbnailSearch thumbnail" alt="Image" style="width: 60px !important; height: 60px  !important;"/>';
	str+='</div>';
	str+='<div class="media-body">';
	
	str+='<p><b>Name </b>:'+result[i].subList[j].name+' </p>';	
	str+='<p><b>State </b>:'+result[i].subList[j].locationVO.state+' <b>District </b>: '+result[i].subList[j].locationVO.districtName+'<br/><b>Constituency </b>:'+result[i].subList[j].locationVO.constituencyName+' <b>Mandel </b>:'+result[i].subList[j].locationVO.tehsilName+' <b>Village </b>:'+result[i].subList[j].locationVO.villageName+'</p>';
	str+='</div>';
	str+='</div>';
	}
	}
	$("#alertAssignedCandidateDataId").html(str);
	
	$("#assignCandidatesCnt").html(result[0].subList.length);
	if(result[0].subList.length > 3)
	{
		$("#alertAssignedCandidateDataId").mCustomScrollbar({setHeight:'290px'});
	}*/
	//hideAndShow();
}
/*$(document).on("click",".assignCandidate",function(){
	 var tdpCadreId = $(this).attr("attr_tdpCadreId");
	 getConfirmation(tdpCadreId);
	// deleteAlertAssignedCandidates(tdpCadreId);
});*/
	
	$(document).on("click","#uploadAlertDocBtnId",function(){
		var childEleCount = $(".jFiler-items-list").children().length;
		if(childEleCount == 0){
			alert("Please select atleast one file");
			return;    
		}
		$("#alertHiddenId").val('${alertId}');
		var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					showStatus(uploadResult,'${alertId}');
				}
			};

		YAHOO.util.Connect.setForm('alertDocs',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadAlertsDocAction.action',uploadHandler);
	});
	
	function showStatus(myResult,alertId){
		var result = (String)(myResult);
		if(result.search('success') != -1){
			alert("Documents Uploaded Successfully.");
			$(".jFiler input").each(function(){     
				$(this).val('');    
			});
			var filerKit = $("#alertScanCopyId").prop("jFiler");
			filerKit.reset();
			getDocumentsForAlert(alertId)
		}else{
			alert("Please Try Again.");
		}
	}	
/*function hideAndShow(){
	if(globalTdpCadreId != globalAssignedCadreId){
	    $(".cmntsDataCls").hide();
	    $("#statusId").prop('disabled', true);
	    $("#assignedCadreId").prop('disabled', true).trigger("chosen:updated"); 
	}else if(entitlementsArr != null && entitlementsArr.length >0){
		for(var i=0;i<entitlementsArr.length;i++){
			if(entitlementsArr[i].trim()=="IGNORE_PROGRAMME_COMMITTEE_ALERT_COMMENTS_ENTITLEMENT"){
				$(".cmntsDataCls").hide();
				$("#statusId").prop('disabled', true);
				$("#assignedCadreId").prop('disabled', true).trigger("chosen:updated");
			}
		}
	}else{
		$(".cmntsDataCls").show();
		$("#statusId").prop('disabled', false);
		$("#assignedCadreId").prop('disabled', false).trigger("chosen:updated");
	}
	
 }  */ 
		
</script>
<script src="dist/CreateAlert/alertCommonCadreSearch.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/alertDetails.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/alertClarification.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<!--<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>  -->   
<script type="text/javascript" src="dragAndDropPhoto/js/alertScanCopy.js?v=1.0.5"></script> 
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
 initializeFile();
 
	google.load("elements", "1", {
        packages: "transliteration"
    });
	var control;
	var lang;
	function onLoad() {
	   lang = $("input[name=language]:checked").val();
		var options = {
			sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage:[''+lang+''],
			shortcutKey: 'alt+t',
			transliterationEnabled: true
		};

		// Create an instance on TransliterationControl with the required
		// options.
		control = new google.elements.transliteration.TransliterationControl(options);

		// Enable transliteration in the textbox with id
		// 'descrptionId'.

	 	if ($('#commentsId').length){
			control.makeTransliteratable(['commentsId']);
		}
	}
	
	function languageChangeHandler() {
        var lang1 = $("input[name=language]:checked").val();
		if(lang1 =="en"){
			control.disableTransliteration();
		}else{
			control.enableTransliteration();
			control.setLanguagePair(google.elements.transliteration.LanguageCode.ENGLISH,lang1);
		}
    }
 google.setOnLoadCallback(onLoad);
 </script>
</body>
</html>