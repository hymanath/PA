<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> GOVT GRIEVANCE </title>
	<!-- Bootstrap -->
	<!--<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">-->
	<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
	<!--<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">-->
	<!-- JQuery files (Start) -->
	
	<!--<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>-->
	<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
		<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	
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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<!-- YUI Dependency files (End) -->
	
	<style>
	/*#partMeetingModalData thead th:nth-child(5) , #partMeetingModalData thead th:nth-child(3),
	{
		width:150px !important;
		max-width:150px !important;
	}
	#partMeetingModalData tr td:nth-child(5) , #partMeetingModalData tr td:nth-child(3),*/
	#partMeetingModalData thead th:nth-child(9) , #partMeetingModalData thead th:nth-child(9)
	{
		width:100px !important;
		max-width:100px !important;
	}
	.bg_ED
	{
		background-color:#EDEDED
	}	
	label{
		font-weight : 400;
	}
	.block{
		padding:10px;
		background-color:#fff;
	}
	h4{
		font-weight : 600;
	}
	.m_top25{
		margin-top : 25px;
	}
	.block
	{
	  border:2px solid #ee935d ;
	  position:relative;
	  padding:8px;
	  margin-top:20px;
	  padding-top:18px;
	}
	.block-heading
	{
	  position:absolute;
	  top:-10px;
	}
	.block-heading span
	{
	  background-color:#fff;
	  padding:0px 10px;
	}
	.text-primary , .panelNewCustom.panel.panel-default span.panel-title
	{
	  color:#ee935d  !important;
	}
	</style>
	</head>  	
<body>
<div class="container" style="margin-top:30px">
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default panelNewCustom">
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-8">
							<h4 class="panel-title" >GOVERNMENT GRIEVANCE </h4>
						</div>
						<div class="col-sm-4">
							<ul class="switch-btn pull-right">
								<li onclick="showDashboard();">Dashboard</li>
								<li class="active"  onclick="showNewGrievance();"> + New Grievance</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12" id="newGrevanceDivId">
							<form id="saveGrievanceAlertForm" name="saveGrievanceAlertForm" enctype="multipart/form-data" method="POST">
								<div class="panel-body bg_EF">
									<div class="row">
										<div class="col-sm-3 m_top10" >
											<label>Complaint Given By<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCallerTypeId"></span></label>
											<select class="chosen" id="callerTypeId" disabled>
												<option value="1">CITIZEN</option>
											</select>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Grievance Related To<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgIssueTypeId"></span></label>
											<select class="chosen" id="issueTypeId" onChange="getRealtedDepartments();" name="grievanceAlertVO.issueTypeId">
												<option value="1">Drinking Water</option>
											</select>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Problem<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgSubTypeId"></span></label>
											<select class="chosen" id="issueSubTypeId" name="grievanceAlertVO.alertIssueSubTypeId">
												<option value="0">Select Problem</option>
											</select>
										</div>
										<!-- <div class="col-sm-3 m_top10">
											<label>Grievance Entry Category<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgEntrySourceId"></span></label>
											<select class="chosen" id="entrySourceId" disabled>
												<option value="1">Toll Free Number</option>
											</select>
										</div> -->
										<div class="col-sm-3 m_top10">
											<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDeptId"></span></label>
											<select class="chosenSelect" id="departmentId" onChange="getRuralUrbanLocations();" name="grievanceAlertVO.departmentId">  
												<!--<option value="49">RWS</option>-->
												<option value="0">Select Department</option>
											</select>
										</div>
									</div>
									<div class="block">
										<div class="block-heading">
											<strong><span class="panel-title text-primary">STEP-01<small>(UPDATE CALLER DETAILS)</small></span></strong>
										</div>
										<div class="block-body">
											<div class="row">
												<div class="col-sm-12 m_top10">
													<h4 class="text-success text-capital">caller details</h4>
												</div>
												<div class="col-sm-3 m_top10">
													<label>Mobile No<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMobileNoId"></span></label>
													<input id="mobileNoId" class="form-control" name="grievanceAlertVO.mobileNo" style="width: 230px" type="text"/><!--<span class="glyphicon glyphicon-search pull-right govtGrievanceCls" style="margin-top: -34px;background-color: lightgrey;padding:10px;margin-right: -10px;: 2px;cursor:pointer;" attr_id="mobile" title=" Click here to get existing Grievance requests with this mobile no"></span>-->
												</div>
												<div class="col-sm-3 m_top10">
													<label>Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgNameId"></span></label>
													<input type="text" id="nameId" class="form-control" name="grievanceAlertVO.name"/>
												</div>
												<div class="col-sm-3 m_top10">
													<label>Email ID<!--<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgEmailId"></span>--></label>
													<input type="text" id="emailId" class="form-control" placeholder="abc@xyz.com" name="grievanceAlertVO.emailId"/>
												</div>
												<div class="col-sm-3 m_top10">
													<label>Address </label>
													<input type="text" id="addressId" class="form-control" name="grievanceAlertVO.address"/>
												</div>
											</div>
											
											<div class="row">
												<div class="col-sm-12">
													<h4 class="text-success text-capital m_top10">location details</h4>
												</div>
												<div class="col-sm-3 m_top10">
													<label>Grievance Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLevelId"></span></label>
													<select class="chosen" id="alertlevelId">
														<!--<option value="0">Select Location Level</option>
														<option value="1">Central</option>
														<option value="2">State</option>
														<option value="3">District</option>
														<option value="4">Constituency</option>
														<option value="5">Mandal/Muncipality</option>
														<option value="6">Village/Ward</option>-->
														<!--<option value="7" selected>Habitation</option>-->
														<option value="0">Select Level</option>-->
													</select>
												</div>
												<div class="col-sm-3 m_top10" id="stateDiv">
													<label>State<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgStateId"></span></label>
													<select class="chosen" id="stateId" onChange="getDistrictsForReferPopup();" name="grievanceAlertVO.stateId" disabled>
														<!--<option value="0">Select State</option>-->
														<option value="1" selected>Andhra Pradesh</option>
													</select>
												</div>
												<div class="col-sm-3 m_top10" id="districtDiv" style="display:block;">
													<label>District<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDistrictId"></span></label>
													<select class="chosen" id="referdistrictId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.districtId">
														<option value="0">Select District</option>
													</select>
												</div>
												<!--<div class="col-sm-3 m_top10" id="constituencyDiv" style="display:block;">
													<label>Constituency<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgConstId"></span></label>
													<select class="chosen" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.constituencyId">
														<option value="0">Select Constituency</option>
													</select>
												</div>-->
												<div class="col-sm-3 m_top10" id="mandalDiv" style="display:block;">
													<label>Mandal/Muncipality<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMandalId"></span></label>
													<select class="chosen" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="grievanceAlertVO.mandalId">
														<option value="0">Select Mandal/Muncipality</option>
													</select>
												</div>
												<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
													<label>Village/Urban Locality<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
													<select class="chosen" id="referpanchayatId" onchange="getHamletss();" name="grievanceAlertVO.panchayatId">
														<option value="0">Select Village/Urban Locality</option>
													</select>
												</div>
												<div class="col-sm-3 m_top10" id="villageDiv" style="display:block;">
													<label>Habitation/Urban Block<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
													<select class="chosen" id="hamletId" name="grievanceAlertVO.hamletId">
														<option value="0">Select Habitation/Urban Block</option>
													</select>
												</div>
												<!--<div class="col-md-3 col-xs-12 col-sm-6" style="margin-left: 10px; margin-top: 45px;">
												 <a class="govtGrievanceCls" attr_id="location" style="cursor:pointer;">Click here to view Alerts in this location</a>
												 </div>-->
											</div>
										</div>
									</div>
									
									<div class="block">
										<div class="block-heading">
											<strong><span class="panel-title text-primary">STEP-02<small>(SEARCH FOR EXISTED GRIEVANCE)</small></span></strong>
										</div>
										<div class="block-body">
											<div class="row m_top25">
												<div class="col-sm-12">
													<label class="radio-inline">
														Search By
													</label>
													<label class="radio-inline">
														<input type="radio" class="searchRadioCls" name="searchRadio" checked value="mobile"/> Mobile No
													</label>
													<label class="radio-inline">
														<input type="radio" class="searchRadioCls" name="searchRadio" value="location"/> Location
													</label>
													<a class="btn btn-success btn-xs govtGrievanceCls">SEARCH</a>
												</div>
											</div>
										</div>
									</div>
									
									<div class="block">
										<div class="block-heading">
											<strong><span class="panel-title text-primary">STEP-03<small>(GRIEVANCE NOT EXIST-CREATE NEW)</small></span></strong>
										</div>
										<div class="block-body">
											<div class="row m_top10">
												<div class="col-sm-12 m_top10">
													<h4 class="text-success text-capital">grievance details</h4>
												</div>
											</div>
											
											<div class="row">
												<div class="col-sm-8 m_top10">
													<label>Grievance Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgTitleId"></span></label>
													<label class="radio-inline" style="margin-bottom: 5px;">
														<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
													</label>
													<label class="radio-inline" style="margin-bottom: 5px;">
														<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
													</label>
													<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
												</div>
												<!--<div class="col-sm-4 m_top10">
													<label>Complaint No</label>
													<input type="text" class="form-control" id="complaintNo" name="grievanceAlertVO.complaintNo"/>
												</div>-->
												<div class="col-sm-12 m_top10">
													<label>Grievance Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDescId"></span></label>
													<!--<label class="radio-inline">
														<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
													</label>
													<label class="radio-inline">
														<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
													</label>-->
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12 m_top10">
													<textarea class="form-control" id="alertdescriptionId" name="grievanceAlertVO.description"></textarea>
												</div>
											</div>
											
											<div class="row">
												<div class="col-sm-12 m_top10">
													<h4 class="text-success text-capital">assign alert to department officer</h4>
												</div>
												  
												<div class="col-sm-3 m_top10">
													<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLvlId"></span></label>
													<select  class="chosenSelect" id="locationLevelSelectId" name="grievanceAlertVO.levelId">  
														<option value="0">Select Level</option>
														<!--<option value="5">MANDAL</option>-->
													</select>
												</div>
												<div id="parentLevelDivId"> </div>

												<div class="col-sm-3 m_top10">
													<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDesgId"></span></label>
													<select name="grievanceAlertVO.designationId" id="designationsId" class="chosenSelect">
														<option></option>  
													</select>
												</div>
												<div class="col-sm-3 m_top10">
													<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgOffcrId"></span></label>
													<select name="grievanceAlertVO.govtOfficerId" id="officerNamesId" class="chosenSelect">
														<option></option>
													</select>
												</div>
											</div>
											
											<div class="row">
												<div class="col-sm-12 m_top10">
													<h4 class="text-success text-capital">upload document linking to this alert</h4>
													<div class="block  m_top10">
														<input type="file" id="uploadFileId0" name="imageForDisplay"/>
														<button type="button" class="close closeFileCls"  style="margin-top:-26px;margin-right:700px" title="Click here to remove document" >x</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
									<div class="row">
										<div class="col-sm-4 m_top25">
											<input type="button" class="btn btn-success btn-block text-capital" value="create grievance request" onclick="createGrievanceAlert();" id="createAlertId"></input>
										</div>
										<div class="col-sm-4">
											<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="creatingLdngImg"/>
										</div>
										<div class="col-sm-4">
											<span id="successmsg"></span>
										</div>
									</div>
								</div>
								<input type="hidden" class="form-control" id="locationLevelValhidden" name="grievanceAlertVO.locationValue"/>
								<input type="hidden" class="form-control" id="locationLevelIdhidden" name="grievanceAlertVO.locationLevelId"/>
								
								<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.callerTypeId"/>
								<!--<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.issueTypeId"/>-->
								<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.entrySourceId"/>
								<!--<input type="hidden" class="form-control" value="49" name="grievanceAlertVO.departmentId"/>-->
								<!--<input type="hidden" class="form-control" value="5" name="grievanceAlertVO.levelId"/>-->
								<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.stateId"/>
								<!-- NEW VARIABLES -->
								<input type="hidden" class="form-control" id="locationTypeHidVal" name="grievanceAlertVO.locationTypeStr"/>
								<input type="hidden" class="form-control" id="alertCallCenterTypeId" name="grievanceAlertVO.alertCallCenterTypeId" value="1"/>
							</form>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-6 pull-right" id="dateDivId" style="display:none;margin-bottom:10px">
							
						</div>
						<div class="col-sm-12">
							<div id="dashboardGrevanceDivId" style="display:none;">
								<div class="panel panel-default">
									<div class="panel-heading headingColor">
										<div class="row">
											<div class="col-sm-8">
												<h4 class="panel-title">Grievance Details</h4>
											</div>
											<div class="col-sm-4">
												<span class="input-group pull-right dateRangePickerClsForEvents" expand-block-date="events" style="width:210px;">
													<input type="text" id="dateRangeIdForEvents" style="width:190px;border-right:0px;" class="form-control" onChange="getGrievanceSummary()"/>
													<span class="input-group-addon" style="background-color:#fff">
														<i class="glyphicon glyphicon-calendar"></i>
													</span>
												</span>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div id="summaryDiv"></div>
										
									</div>
								</div>
								<div id="alertDataDivId"></div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">
            	<div class="panel-heading bg_cc"   style="height:50px;" >
                	<h4 class="panel-title" >GOVERNMENT GRIEVANCE 
						<button class="btn btn-success pull-right" onclick="showDashboard();"> Dashboard </button>
						<button class="btn btn-primary  pull-right" style="margin-right:5px"  onclick="showNewGrievance();" > + New Grievance </button> 
					</h4>
					
                </div>
				<div class="col-md-4 col-xs-12 col-sm-6 pull-right" id="dateDivId" style="display:none;margin-bottom:10px">
				<span class="input-group pull-right dateRangePickerClsForEvents m_top10" expand-block-date="events" style="width:200px;">
						<input type="text" id="dateRangeIdForEvents" style="width:180px" class="form-control" onChange="getGrievanceSummary()"/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</span>
				</div>
				
				
            </div>
        </div>
    </div>-->
</div>

<div class="modal fade" id="updateAlertModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content customModal">
      <div class="modal-header">
       <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Feedback Status Updation</h4>
      </div>
      <div class="panel-body" style="padding:10px;background-color: lightgrey;">
		<div id="buildUpdateDivId">
		</div>
		
		<div class="col-md-12 col-xs-12 col-sm-12  m_top10">
			  <label>FeedBack Status :<span style="color:red">*</span><span id="statusErrMsgId" style="color:red"></span></label><br>
			  <select id="feedbackStatusList" class="form-control">
				<option value="0"> Select Feedback Status</option>
			  </select>
			  
			  <span id="reOpenSpanId" style="display:none"><input id="reopenCheckboxId" type="checkbox" value="11"/> Reopen </span>
			  
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">			
				<span id="saveMsgId"></span>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onClick="saveAlertStatusDetails();" >Save</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--srujana-->
 <div class="modal fade" id="govtGrievanceAlertModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width:95%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="mdlHeadingId" style="text-transform: uppercase;">Search And Mark As Duplicate</h4>
      </div>
      <div class="modal-body">
	  <div class ="row">
		   <div class="col-md-3 col-sm-4 col-xs-12 govtGrievanceView" >
				<label class="radio-inline">Search By:</label>
				<label class="radio-inline">
					<input  type="radio" name="checkBoxName"  style="" value="1" class="radioBtnCls" id="radioBtnMobileId"/> Mobile No
				</label>
				<label class="radio-inline">
					<input  type="radio" name="checkBoxName"  style="" value="2" class="radioBtnCls" id="radioBtnClsId" />Location
				</label>
			</div>
			<div id="modalErrorDiv" style="color:red"></div>
			<div id="modalSuccessDiv" style="color:green"></div>
		</div>
	   <div class="row locationSearchCls" style="display:none;">
			<div class="col-sm-3 m_top10" id="districtIdDiv" style="display:block;">
				<label>District<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDistrictId"></span></label>
				<select class="chosen" id="modalDistrictId" onChange="getMandalsByConstituencyForReferPopup1();" name="grievanceAlertVO.districtId">
					<option value="0">ALL</option>
				</select>
			</div>						
			<div class="col-sm-3 m_top10" id="mandalDiv" style="display:block;">
				<label>Mandal/Muncipality<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMandalId"></span></label>
				<select class="chosen" id="modalMandalNameId" onChange="getPanchayatsForReferPopup1();" name="grievanceAlertVO.mandalId">
					<option value="0">ALL</option>
				</select>
			</div>
			<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
				<label>Village/Urban Locality<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
				<select class="chosen" id="modalPanchayatId" onchange="getHamletss1();" name="grievanceAlertVO.panchayatId">
					<option value="0">ALL</option>
				</select>
			</div>
			<div class="col-sm-3 m_top10" id="villageDiv" style="display:block;">
					<label>Habitation/Urban Block<span style="color:red"></span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
					<select class="chosen" id="modalhamletId" name="grievanceAlertVO.hamletId">
						<option value="0">ALL</option>
					</select>
			</div>
			<div class="col-sm-3 m_top10">
				<input type="button" class="btn btn-success" value="SEARCH" id="" onclick="getGovtGrievanceAlertDetails();" style="margin-top: 19px;"></input>
			</div>
		</div>
		<div class="row mobileSearchCls"  style="display:none;">
			<div class="col-sm-4 m_top10">
				<div id="errorMassgeId" style="color:red"></div>
				<input type="text" class="form-control" id="searchBy"/><!--<span class="glyphicon glyphicon-search pull-right" style="margin-top: -34px;background-color: lightgrey;padding:10px;margin-right: -10px;: 2px"></span>-->
				
			</div>
			<div class="col-sm-4 m_top10">
				<input type="button" class="btn btn-success" value="SEARCH" onclick="getGovtGrievanceAlertDetails();"></input>
			</div>
		 </div>
        <div id="commentsBlock" class="m_top10"></div>		
        <!--<div id="commentsDivId"></div>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<div class="modal fade" id="alertManagementPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">ALERT DETAILS</h4>
			</div>
			<div class="modal-body modal-insurance">
				<div id="alertManagementPopupBody"></div>
			</div>
		</div>
  </div>
</div>
<div class="modal fade" id="alertManagementPopup1" tabindex="-2" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="alertManagementPopupHeading">Modal title</h4>
			</div>
			<div class="modal-body">
				<div id="alertManagementPopupBody1"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeSecondModal" data-dismiss="modal">Close</button>
			</div>
		</div>
  </div>
</div>
<!-- Hidden Variables -->
<input type="hidden" id="hiddenAlertId"></input>
<input type="hidden" id="hiddenSourceId"></input>
<input type="hidden" id="hiddenStatusId"></input>


<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script src="alertDepartment/js/jquery.hotkeys.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystemNewUpload.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
<script type="text/javascript" src="alertDepartment/js/newAlertUserManagementDetail.js"></script>
<script>
$(document).on("click",".closeFirstModal",function(){
	setTimeout(function(){
		$("body").addClass("modal-open")
	},1000);
});
var globalUserLevelId = 0;
var globalUserLevelValues = [0];
$(".chosen").chosen({
	width : '100%'
});
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
// Load the Google Transliterate API
google.load("elements", "1", {
	packages: "transliteration"
});

getAlertIssueTypes();
//getAlertCallerTypes();

$("#dateRangeIdForEvents").daterangepicker({
	opens: 'left',
	startDate: moment().startOf('month'),
	endDate: moment(),
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
		'Today' : [moment(), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});
var dates= $("#dateRangeIdForEvents").val();
var pickerDates = currentFromDate+' - '+currentToDate
if(dates == pickerDates)
{
	$("#dateRangeIdForEvents").val('All');
}
function getDistrictsForReferPopup() {
	var stateId = $("#stateId").val();
	
	var jobj = {
		stateId : stateId  
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		var str='';
		
			str+='<option value="0">Select District</option>';
		if(result != null && result.length > 0){    
			for(var i in result){
				if(result[i].id > 0 && result[i].id != 517)
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#referdistrictId").html(str);
			$("#referdistrictId").trigger('chosen:updated');
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#referpanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
			$("#hamletId").trigger('chosen:updated');
			$("#modalDistrictId").html(str);
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalMandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#modalMandalNameId").trigger('chosen:updated');
			$("#modalPanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#modalPanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
			$("#hamletId").trigger('chosen:updated');
			
			 
		}else{
			//for dist
			$("#referdistrictId").html('<option value="0">Select District</option>');
			$("#referdistrictId").trigger('chosen:updated');   //srujana modalDistrictId  modalMandalNameId modalPanchayatId
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#referpanchayatId").trigger('chosen:updated');  
			$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
			$("#hamletId").trigger('chosen:updated');
			$("#modalDistrictId").html('<option value="0">Select District</option>');
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalMandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#modalMandalNameId").trigger('chosen:updated');
			$("#modalPanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#modalPanchayatId").trigger('chosen:updated'); 
		} 
	});  
  }

 function getMandalsByConstituencyForReferPopup(){
	 var districtId = $('#referdistrictId').val();
	 
	 var url = "getAllMandalsByDistrictIDAction.action";
	 var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getAllMandalsByDistrictIDAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getAllLebsByDistrictIDAction.action";
	}
	 
	 var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : url,
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">Select Mandal/Muncipality</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#refermandalNameId").html(mandalStr);
				$("#refermandalNameId").trigger('chosen:updated');                
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
				$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
				$("#hamletId").trigger('chosen:updated');
				$("#designationsId").html('<option value="0">Select Designation</option>');//empty();
				$("#designationsId").trigger("chosen:updated");
			}else{
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
				$("#referpanchayatId").trigger('chosen:updated');
				$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
				$("#hamletId").trigger('chosen:updated');				
			}
		});
 }
 
 function getPanchayatsForReferPopup(){
	 designationsByDepartment();
	
	 $("#referpanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId').val();
	 var  type = $("#refermandalNameId option:selected").text();
	 		   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	 var url = "getPanchayatAndWardDetailsAction.action";
	 var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getPanchayatAndWardDetailsAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getUrbanLocalitiesForMuncipalityAction.action";
	}
	
	  var jsObj={
				mandalId :mandalId,
				type:type,
				task:""
				
			}
	 $.ajax({
				type:"POST",
				url :url,
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Village/Urban Locality</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#referpanchayatId").html(panchyatStr);
					$("#referpanchayatId").trigger('chosen:updated');
					$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
					$("#referpanchayatId").trigger('chosen:updated');
					$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
					$("#hamletId").trigger('chosen:updated');					
				}     
		});
}

function getHamletss(){
	
	 $("#hamletId").find('option').not(':first').remove();
	 var panchayatId = $('#referpanchayatId').val();
	 var  type = $("#refermandalNameId option:selected").text();
			   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	var url = "getHamletsForPanchayatAction.action";
	 var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getHamletsForPanchayatAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getUrbanBlocksForLocalityAction.action";
	}
	
	//if(type == "mandal"){
		
		var jsObj={
				panchayatId :panchayatId,
				task:""
			}
	 $.ajax({
				type:"POST",
				url :url,
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Habitation/Urban Block</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#hamletId").html(panchyatStr);
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
					$("#hamletId").trigger('chosen:updated'); 
				}     
		});
	//}
}

function getAlertCallerTypes(){
	var jsObj={
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getAlertCallerTypesAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str +='<option value="0">Select Caller Type</option>';
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#callerTypeId").html(str);
			$("#callerTypeId").trigger('chosen:updated');
		}
	});
}

function getAlertIssueTypes(){
	var jsObj={
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getAlertIssueTypesAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str +='<option value="0">Select Issue Type</option>';
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#issueTypeId").html(str);
			$("#issueTypeId").trigger('chosen:updated');
		}
	});
}

function disableByGrievLevel(){
	var alertLevelId = $("#alertlevelId").val();
	if(alertLevelId == 2){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").hide();
		$("#constituencyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 3){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 4){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 5){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 6){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 7){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
		$("#villageDiv").show();
	}
}

</script>
<script type="text/javascript">

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
	// Create an instance on TransliterationControl with the required options.
	control = new google.elements.transliteration.TransliterationControl(options);
	// Enable transliteration in the textbox with id 'descrptionId'.
	if ($('#alertTitleId').length){
		control.makeTransliteratable(['alertTitleId']);
	}
	if ($('#alertdescriptionId').length){
		control.makeTransliteratable(['alertdescriptionId']);
	}
	
}
function languageChangeHandler(){
   var lang1 = $("input[name=language]:checked").val();
	if(lang1 =="en"){
		control.disableTransliteration();
	}else{
		control.enableTransliteration();
		control.setLanguagePair(
		google.elements.transliteration.LanguageCode.ENGLISH,
		lang1);
	}
}
google.setOnLoadCallback(onLoad);

function createGrievanceAlert()
{
	$(".errorMsgClas").html('');
	//var validate = true;
	
  var  level=$("#alertlevelId").val();
  var  state=$("#stateId").val();
  var  district=$("#referdistrictId").val();
  //var  assembly=$("#referconstituencyId").val();
  var  mandal=$("#refermandalNameId").val();
  var  panchayat=$("#referpanchayatId").val();
  var  village = $("#hamletId").val();
  
  var title=$("#alertTitleId").val().trim();
  var description=$("#alertdescriptionId").val().trim();
  
  var callerType = $("#callerTypeId").val();
  var issueType = $("#issueTypeId").val();
  var entrySource = $("#entrySourceId").val();
  var issueSubType = $("#issueSubTypeId").val();
  
  var name = $("#nameId").val().trim();
  var address = $("#addressId").val().trim();
  var mobileNo = $("#mobileNoId").val().trim();
  var email = $("#emailId").val().trim();
  
  var department = $("#departmentId").val();
  var assignLevelId = $("#locationLevelSelectId").val();
  var levelValue = $(".locationCls").val();
  var designation = $("#designationsId").val();
  var officer = $("#officerNamesId").val();
  
  if(issueSubType == 0){
	  $("#errMsgSubTypeId").html("Select Issue Sub Type");
	  return;
  }
  if(mobileNo.length==0 ||mobileNo==''){
		$("#errMsgMobileNoId").html(" Please Enter MobileNo ");
		return;
	}
	if(mobileNo.length != 10){
		$("#errMsgMobileNoId").html(" Please Enter Valid MobileNO ");
		return;
	}
	if(mobileNo.length > 0){
		var numericExpression = /^[0-9]+$/;
		if(!mobileNo.match(numericExpression)){
			$('#errMsgMobileNoId').html('Enter Numerics Only.');
			return;
		}
	}
	if(name.length==0 ||name==''){
		$("#errMsgNameId").html(" Please Enter Name ");
		return;
	}
	/*if(address.length==0 ||address==''){
		$("#errMsgAddressId").html(" Please Enter Address ");
		return;
	}*/
	
  
  if(level==0)
  {
     $("#errMsgLevelId").html(" Please Select Level ");
  }
  
  if(level==2)
  {
    if(state==0)               
      {
      $("#errMsgStateId").html(" Please Select State ");
          return;
    }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      $("#errMsgStateId").html(" Please Select State ");
          return;
    }
    if(district==0)
    {
      $("#errMsgDistrictId").html(" Please Select District ");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
    /*if(assembly==0)
    {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
    }
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);*/
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId1 option:selected").text();
    if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
    /*if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
    if(mandal==0)
    {
      $("#errMsgMandalId").html(" Please Select Mandal/ Municipality ");
          return;
    }
	$("#locationLevelValhidden").val(mandal);
		if(mandalName.indexOf('Mandal') == -1)
		$("#locationLevelIdhidden").val(7);
	else
		$("#locationLevelIdhidden").val(5);
		
  }
  if(level==7 || level == 13)
  {
	if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
     /* if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
      if(mandal==0)
     {
      $("#errMsgMandalId").html(" Please Select Mandal/Muncipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errMsgVillageId").html(" Please Select Village/Urban Locality ");
        return;
     }
	 if(village==0)
     {
    $("#errMsgHamletId").html(" Please Select Habitation/Urban Block ");
        return;
    }
	if(level == 7){
		$("#locationLevelValhidden").val(village);
		$("#locationLevelIdhidden").val(11);
	}
	else if(level == 13){
		$("#locationLevelValhidden").val(village);
		$("#locationLevelIdhidden").val(13);
	}
    }
	if(level==6)
  {
	   var panchayatName = $("#referpanchayatId1 option:selected").text();
    if(state==0)
      {
        $("#errMsgStateId").html(" Please Select State ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please Select District ");
            return;
      }
      /*if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
      if(mandal==0)
     {
      $("#errMsgMandalId").html(" Please Select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errMsgVillageId").html(" Please Select Village/Urban Locality ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
	
	 if(title.length==0 || title=='')
	  {
		$("#errMsgTitleId").html(" Please Enter Alert Title.  ");
			return;
	  }
  
	if(description.length==0 ||description==''){
		$("#errMsgDescId").html(" Please Enter Description ");
		return;
	}
	
	if(assignLevelId == 0){
		$("#errMsgLvlId").html(" Select Level ");
		return;
	}
	if(levelValue == 0){
		$("#errMsgLocationId").html(" Select Location ");
		return;
	}
	if(designation == 0){
		$("#errMsgDesgId").html(" Select Designation ");
		return;
	}
	if(officer == 0){
		$("#errMsgOffcrId").html(" Select Officer ");
		return;
	}
	
	if(callerType == 0){
		$("#errMsgCallerTypeId").html(" Select Caller Type ");
		return;
	}
	if(issueType == 0){
		$("#errMsgIssueTypeId").html(" Select Issue Type ");
		return;
	}
	if(entrySource == 0){
		$("#errMsgEntrySourceId").html(" Select Entry Category ");
		return;
	}
	
  if(department == 0){
		$("#errMsgDeptId").html(" Select Department ");
		return;
	}
	
	
	//if(validate){
		$("#creatingLdngImg").show();
		$("#createAlertId").hide();
		
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				$("#createAlertId").show();
				if(uploadResult.indexOf("success") !=-1)
				{
				 $("#successmsg").html("Alert Created And Assigned Successfully ").css("color","green");	
				 $("#creatingLdngImg").hide();
				 setTimeout(function(){ 
					$("#successmsg").html("");
					clearFields();
					location.reload();
				 }, 1000);
				}else{  
					$("#successmsg").html("Exception Occured..Try Again...").css("color","red");	
					$("#creatingLdngImg").hide();
					setTimeout(function(){ 
						$("#successmsg").html("");
					}, 1000);
				}  
				return false;
			}
		};
		
		YAHOO.util.Connect.setForm('saveGrievanceAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveGrievanceAlertAction.action',uploadHandler);
	//}
	
}

function clearFields(){
	$("#alertlevelId").val(0).trigger('chosen:updated');
	$("#departmentId").val(1).trigger('chosen:updated');
	$("#entrySourceId").val(0).trigger('chosen:updated');
	$("#telugu").prop("checked", true);
	
	$("#alertTitleId").val('');
	$("#alertdescriptionId").val('');
	$("#nameId").val('');
	$("#addressId").val('');
	$("#mobileNoId").val('');
	$("#emailId").val('');
	$("#uploadFileId0").val('');
}
//getAlertIsuueSubTypes();
function getAlertIsuueSubTypes(){
	$('#issueSubTypeId').empty();
	$('#issueSubTypeId').trigger('chosen:updated');
	
	var issueTypeId = $("#issueTypeId").val();
	
	var jObj = {
			issueTypeId : issueTypeId
		}
	$.ajax({
	  type:'GET',
	  url: 'getAlertIssueSubTypesAction.action',
	  data: {task :JSON.stringify(jObj)}
	}).done(function(result){
		$('#issueSubTypeId').append('<option value="0">Select Problem</option>');
		if(result != null && result.length > 0)
		{
			for(var i in result)
				$('#issueSubTypeId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		}
		$('#issueSubTypeId').trigger('chosen:updated');
	});
}

//getDepartmentLevels();
	var loginUserId = "${sessionScope.USER.registrationID}";
	/* Assign */
	$(".chosenSelect").chosen({width:'100%'})
	$(document).on('change', '#locationLevelSelectId', function(){
		getParentLevelsOfLevel();
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	/*$(document).on('change', '#departmentsId', function(){
		getDepartmentLevels();
	});*/
	$(document).on('change','#designationsId', function(evt, params) {
		var designationId = $(this).val();
		officersByDesignationAndLevel(designationId)
	});
	function getDepartmentLevels(){
		
		var jsObj = {
			departmentId : 49
		}
		$.ajax({
		  type:'GET',
		  url: 'getDepartmentLevelsAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDepartmentLevels(result);
			}
		});
		
	}
	function buildDepartmentLevels(result){
		
		var str='';	
		str+='<option value="0">Select Level</option>';
		for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		
		$("#locationLevelSelectId").html(str);
		$("#locationLevelSelectId").trigger("chosen:updated");
		
		$("#locationLevelSelectId").val(8);
		$("#locationLevelSelectId").trigger("chosen:updated");
		setTimeout(function(){
			getParentLevelsOfLevel();
		},1000);
	}


	function getParentLevelsOfLevel(){
		departmentId = 49;
		var jsObj = {
			departmentId : departmentId,
			levelId : $("#locationLevelSelectId").val()
		}
		$.ajax({
		  type:'GET',
		  url: 'getParentLevelsOfLevelAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildParentLevelsOfLevel(result,departmentId);
			}
		});
	}
	function buildParentLevelsOfLevel(result,departmentId){
		var str='';
			
			for(var i in result){
				if(i<result.length-1){
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
						str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
					str+='</div>';
				}else{
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
						str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="grievanceAlertVO.levelValue" ></select>';
					str+='</div>';
				}
				
			}
		
		$("#parentLevelDivId").html(str);
		$(".chosenSelect").chosen({width:'100%'});
		
		for(var i in result){
			
			if(result[i].idnameList !=null && result[i].idnameList.length>0){
				var newStr='';		
				newStr+='<option value="0">Select '+result[i].name+'</option>';
				for(var j in result[i].idnameList){
					 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
				}			
				$("#locationSubLevelSelectId"+result[i].id+"").html(newStr);
				$("#locationSubLevelSelectId"+result[i].id+"").trigger("chosen:updated");
			}
		}
		
		$("#locationSubLevelSelectId1").val(1);
		$("#locationSubLevelSelectId1").trigger('chosen:updated');
		$("#locationSubLevelSelectId1").trigger("change");
	}
	function getGovtSubLevelInfo(departmentId,levelId){
		
		$("#designationsId").empty();
		$("#designationsId").trigger("chosen:updated");
		$("#officerNamesId").empty();
		$("#officerNamesId").trigger("chosen:updated");	
		
		var levelValue=$("#locationSubLevelSelectId"+levelId+"").val();	
		
		var jsObj = {
			departmentId : departmentId,
			levelId :levelId,
			levelValue:levelValue
		}
		$.ajax({
		  type:'GET',
		  url: 'getGovtSubLevelInfoAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null){
				buildGovtSubLevelInfoAction(result);
			}
				
		});
	}
	function buildGovtSubLevelInfoAction(result){
		
		var str='';
		if(result !=null){		
			if(result.idnameList !=null && result.idnameList.length>0){
				str+='<option value="0">Select '+result.name+'</option>';
				for(var i in result.idnameList){
					str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
				}
			}
			
			$("#locationSubLevelSelectId"+result.id+"").html(str);
			$("#locationSubLevelSelectId"+result.id+"").trigger("chosen:updated");
		}
		
	}



	function designationsByDepartment()
	{
		$("#designationsId").empty();
		$("#designationsId").trigger("chosen:updated");
		$("#officerNamesId").empty();
		$("#officerNamesId").trigger("chosen:updated");
		var LevelId = $("#locationLevelSelectId").chosen().val();
		var deprtmntId = $("#departmentId").chosen().val();
		var levelValue = $("#refermandalNameId").chosen().val();
		
		var jsObj = {
			departmentId	: deprtmntId,
			levelId			: LevelId,
			levelValue			: levelValue
		}
		$.ajax({
		  type:'GET',
		  url: 'getOldDesignationsByDepartmentNewAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Designation</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#designationsId").html(str);
			$("#designationsId").trigger("chosen:updated");
		});
	}

	function officersByDesignationAndLevel(designationId)
	{
		$("#officerNamesId").empty();
		$("#officerNamesId").trigger("chosen:updated");
		var LevelId = $("#locationLevelSelectId").chosen().val()
		var LevelValue = $("#refermandalNameId").chosen().val()
		
		var jsObj = {
			levelId				: LevelId,
			levelValue			: LevelValue,
			designationId		: designationId
		}
		$.ajax({
		  type:'GET',
		  url: 'getOldOfficersByDesignationAndLevelNewAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Officer</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#officerNamesId").html(str);
			$("#officerNamesId").trigger("chosen:updated");
		});
	}

function getAlertDetails(alertStatusId,status,feedbackStattusId)
	{
		/* var datesArr =[]; 
		if(datesArr != null){
			startDate = datesArr[0];
			endDate = datesArr[1];
		}
		var mobileNo;
		var statusId; */
		$('#alertDataDivId').html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
		
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		 var dateArray = dates.split("-");
		 if(dateArray != null){
			 fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		var jObj = {
			alertStatusId : alertStatusId,
			mobileNo : '',
			fromDate :fromDateStr,//"12/04/2016",
			toDate : toDateStr,//"12/04/2017"
			feedbackStattusId:feedbackStattusId,
			categoryId:4
			
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertDetailsByStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertDetails(result,status,alertStatusId);
			}
		});
}

function getAlertCallerDetails(alertId)
	{
		$("#comntId").val('');
		$("#saveMsgId").html('');
		$("#feedbackStatusList").val(0);
		$("#updateAlertModalDivId").modal('show');
		var jObj = {
			alertId : alertId
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertCallerDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null){
				buildAlertCallerDetails(result);
			}
		});
}

function saveAlertStatusDetails()
	{
		var comment = $("#comntId").val();
		var feedBackStatus =$("#feedbackStatusList").val();
		var alertId = $("#hiddenAlertId").val();
		var sourceId = $("#hiddenSourceId").val();
		var statusId = $("#hiddenStatusId").val();
		var newAlertStatusId = $('#reopenCheckboxId').prop('checked') ? $('#reopenCheckboxId').val() : 0;
		var alertCallerId =$("#alertCallerList").val();
		
		$("#commentErrMsgId").html('');
		$("#statusErrMsgId").html('');
		
		if(comment == null || comment.trim().length == 0 || comment == "undefined" ){
			$("#commentErrMsgId").html("Please Enter Comment");
			return;
		}
		if(feedBackStatus == null || feedBackStatus == 0 || feedBackStatus == "undefined" ){
			$("#statusErrMsgId").html("Please Select Anyone ");
			return;
		}
		if(alertCallerId == null || alertCallerId == 0 || alertCallerId == "undefined" ){
			$("#callrErrMsgId").html("Please Select Anyone ");
			return;
		}
		var jObj = {
			alertId : alertId,
			comment : comment,
			alertStatusId : statusId,
			alertFeedBackStatusId : feedBackStatus,
			alertSourceId : sourceId,
			newAlertStatusId:newAlertStatusId,
			alertCallerId:alertCallerId
		}
		$.ajax({
		  type:'GET',
		  //url: 'saveAlertStatusAction.action',
		  url: 'saveAlertFeedbackStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result == "success"){
				showDashboard();
				$("#saveMsgId").html("<span style='color:green;'>Updated Successfully..</span>");
			}else{
				$("#saveMsgId").html("<span style='color:green;'>Updation failed.Please try again.</span>");
			}
			setTimeout(function(){ 
				 $("#updateAlertModalDivId").modal('hide');
			},1500);
		});
}
getFeedBackStatusDetails();
function getFeedBackStatusDetails()
	{
		var jObj = {
			}
		$.ajax({
		  type:'GET',
		  url: 'getAlertFeedBackStatusDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null)
			{
				for(var i in result)
					$('#feedbackStatusList').append('<option value="'+result[i].id+'">'+result[i].status+'</option>');
			}
		});
}	
	
function buildAlertDetails(result,status,alertStatusId){
	var str = '';
	if(status == 0)
		status = "Total";
	str+='<div class="panel panel-default">';
		str+='<div class="panel-heading headingColor">';
			str+='<h4 class="panel-title">'+status+' Status Grievance Details</h4>';
		str+='</div>';
		str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
				str+='<table class="table table-bordered text-center table-condensed" id="tabbDetails">';
					str+='<thead>';
						str+='<th  style="text-align:center">ALERT ID</th>';
						str+='<th  style="text-align:center">TITLE</th>';
						str+='<th  style="text-align:center">DEPARTMENT</th>';
						str+='<th  style="text-align:center">ISSUE TYPE</th>';
						str+='<th  style="text-align:center">ISSUE SUB TYPE</th>';
						str+='<th  style="text-align:center">ALERT STATUS</th>';
						str+='<th  style="text-align:center">IMPACT LEVEL</th>';
						str+='<th  style="text-align:center">LOCATION</th>';
						str+='<th  style="text-align:center"> CREATED ON</th>'; 
						str+='<th  style="text-align:center"> CALLER DETAILS</th>'; 
						str+='<th  style="text-align:center"> CREATED BY</th>'; 
						str+='<th  style="text-align:center"> FEEDBACK STATUS  </th>'; 
						
						if(alertStatusId !=null && alertStatusId>0 &&  ( alertStatusId == 12)){
							str+='<th  style="text-align:center"> UPDATE STATUS  </th>'; 
						}else{
							str+='<th  style="text-align:center"> UPDATE STATUS  </th>'; 
						}
						
					str+='</thead>';
					str+='<tbody>';
					for( var i in result){
						var locationName ="";
						if(result[i].district != null && result[i].district.length>0)
							locationName = locationName+"District : "+result[i].district+"<br> ";
						if(result[i].constituency != null && result[i].constituency.length>0)
							locationName = locationName+"Assembly : "+result[i].constituency+"<br> ";
						
						if(result[i].tehsil != null){
							if(result[i].tehsil != null && result[i].tehsil.length>0)
								locationName = locationName+"Mandal : "+result[i].tehsil+"<br> ";
							if(result[i].panchayat != null && result[i].panchayat.length>0)
								locationName = locationName+"Panchayat : "+result[i].panchayat+"<br> ";
							if(result[i].hamlet != null && result[i].hamlet.length>0)
								locationName = locationName+"Hamlet : "+result[i].hamlet+"<br>";
						}
						else{
							if(result[i].leb != null && result[i].leb.length>0)
								locationName = locationName+"Munci/Corp/Greater City : "+result[i].leb+"<br>";
							if(result[i].ward != null && result[i].ward.length>0)
								locationName = locationName+"Ward : "+result[i].ward+"";
						}
						
						if(result[i].callerDuplicate == 'YES')
							str+='<tr style="background-color:#ddd;">';
						else
							str+='<tr>';
							str+='<td><a style="text-transform: uppercase;cursor:pointer;color:#337ab7" class="text-center alertIdSpecialSearch" attr_alertId="'+result[i].alertId+'">'+result[i].alertId+'</a></td>';
							str+='<td>'+result[i].title+'</td>';
							str+='<td>'+result[i].deptName+'</td>';
							str+='<td>'+result[i].issueType+'</td>';
							str+='<td>'+result[i].issueSubType+'</td>';
							str+='<td>'+result[i].status+'</td>';
							str+='<td >'+result[i].locationName+'</td>';
							str+='<td style="text-align:left;">'+locationName+'</td>';
							str+='<td>'+result[i].createdTime+'</td>';
							//str+='<td>'+result[i].name+'<br>MobileNo:'+result[i].mobileNo+'</td>';
							if(result[i].idNamesList != null && result[i].idNamesList.length > 0){
								str+='<td style="text-align:left;">';
								for(var j in result[i].idNamesList){
									str+='<p>'+result[i].idNamesList[j].name+', MNo:'+result[i].idNamesList[j].mobileNo+'</p>';
								}
								str+='</td>';
							}else{
								str+='<td> - </td>';
							}
							str+='<td>'+result[i].userName+'</td>';
							str+='<td>';
							if(result[i].feedbackStatus != null){
									str+=' '+result[i].feedbackStatus+' <br>';
							}
						str+='</td>';
						
						if(result[i].statusId !=null && result[i].statusId>0 &&  ( result[i].statusId == 12)){
							str+='<td>';
								str+='<button class="btn btn-success updateAlertCls btn-xs btn-mini" attr_alert_id ="'+result[i].alertId+'" attr_alert__source_id ="'+result[i].alertSourceId+'" attr_alert__status_id ="'+result[i].statusId+'">Update</button>';
							str+='</td>';
						}else{
							str+='<td> - </td>';
						}
						str+='</tr>';
					}
					str+='</tbody>';
				str+='</table>';
				str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#alertDataDivId").html(str);
	$('#tabbDetails').dataTable();
	$("#tabbDetails").removeClass("dataTable");
	setTimeout(function(){
		$("#alertManagementPopup").find(".closeSecondModal").removeClass("closeSecondModal");
	},1000)
}
$(document).on("click",".updateAlertCls",function(){ 
	var alertId = $(this).attr("attr_alert_id");
	var sourceId =$(this).attr("attr_alert__source_id");
	var statusId =$(this).attr("attr_alert__status_id");
	$('#reopenCheckboxId').attr('checked', false);
	$("#reOpenSpanId").hide();
	getAlertCallerDetails(alertId);
	$("#hiddenAlertId").val(alertId);
	$("#hiddenSourceId").val(sourceId);
	$("#hiddenStatusId").val(statusId);
});	
	
function buildAlertCallerDetails(result){
	var str= '';
	
	//	str+='<div class="panel panel-default m_top10">';
		//	str+='<div class="panel-body">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>Title : </span> <span>'+result[0].title+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>description : </span> <span>'+result[0].desc+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12  m_top10">';
					str+='<label>Comment<span style="color:red">*</span></label><span id="commentErrMsgId" style="color:red"></span><br>';
					str+='<textarea id="comntId" rows="3" style="width: 799px;"></textarea>';
					str+='</div>';
				
				//str+='<p> Given By : '+result[i].name+' - '+result[i].mobileNo+'</p>';
					//str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					/* str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Given By : </span> <span>'+result[i].name+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Mobile No : </span> <span>'+result[i].mobileNo+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Created On : </span> <span>'+result[i].date1+'</span></p>';
					str+='</div>'; 
					//str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>Title : </span> <span>'+result[0].title+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>description : </span> <span>'+result[0].desc+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12  m_top10">';
					str+='<label>Comment<span style="color:red">*</span></label><span id="commentErrMsgId" style="color:red"></span><br>';
					str+='<textarea id="comntId" rows="3" style="width: 799px;"></textarea>';
					str+='</div>';*/
					
					str+='<div class="col-md-12 col-xs-12 col-sm-12  m_top10">';
					str+=' <label>Alert Callers :<span style="color:red">*</span><span id="callrErrMsgId" style="color:red"></span></label><br>';
					str+='<select id="alertCallerList" class="form-control">';
					str+='<option value="0"> Select Alert Caller</option>';
					for(var i in result){
						str+='<option value="'+result[i].alertCallerId+'"> '+result[i].name+'-'+result[i].mobileNo+'</option>';
					}
					str+=' </select>';
					str+='</div>';
				
			 
		//	str+='</div>';
		//str+='</div>';
		
$("#buildUpdateDivId").html(str);
}



	function showDashboard(){
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:nth-child(1)").addClass("active");
		$("#dateDivId").show();
		getGrievanceSummary();
		$('#dashboardGrevanceDivId').show();
		$('#newGrevanceDivId').hide();
	}
	function showNewGrievance(){
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:nth-child(2)").addClass("active");
		$('#dateDivId').hide();
		$('#dashboardGrevanceDivId').hide();
		$('#newGrevanceDivId').show();
	}
	function getGrievanceSummary(){
	    $('#alertDataDivId').html('');
		$('#summaryDiv').html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  var deptId = $("#departmentId").val();
		var jsObj ={
			startDate:fromDateStr,//'01/04/2017',
			endDate:toDateStr,//'01/05/2017',
			mobileNo:'',
			status:"",
			deptId:deptId,
			task:""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertCallerDetails.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				buildGrievanceSummary(result);
			}	  
		});
	}
	
	function buildGrievanceSummary(result){
		if(result != null && result.length>0){
			var str='';
			var total = 0;
			if(result[0].statusList != null && result[0].statusList.length>0) {
				
				str+='<table  class="table table-bordered"  style="text-align:center;" id="tabDetails">';
					str+='<thead>';
					//str+='<tr>';
						str+='<th  style="text-align:center">  </th>';
						var totalCount =0;
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId != 1 && result[0].statusList[0].statusList[k].alertStatusId !=14)
							str+='<th  style="text-align:center" > '+result[0].statusList[0].statusList[k].status+' </th>';
						}
						str+='<th  style="text-align:center">TOTAL COUNT </th>';
					//str+='</tr>';
					
					str+='</thead>';
					str+='<tbody>';
						/*for(var k in result[0].statusList){
							str+='<tr>';
							str+='<th  style="text-align:center" > '+result[0].statusList[k].status+' </th>';
							for(var l in result[0].statusList[k].statusList){
								if(result[0].statusList[k].statusList[l].count != null){
									if(result[0].statusList[k].statusList[l].alertStatusId != 2 && result[0].statusList[k].statusList[l].alertStatusId != 1 && result[0].statusList[k].statusList[l].alertStatusId != 3)
											str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[k].statusList[l].alertStatusId+',\''+result[0].statusList[k].statusList[l].status+'\','+result[0].statusList[k].alertStatusId+')}"> '+result[0].statusList[k].statusList[l].count+' </a> </td>';
										else
											str+='<td  style="text-align:center" > '+result[0].statusList[k].statusList[l].count+' </td>';
								}	
								else 
									str+='<td  style="text-align:center" > -  </td>';
							}
							str+='</tr>';
						}
						str+='<tr>';*/
						str+='<th  style="text-align:center" > TOTAL </th>';
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId != 1 && result[0].statusList[0].statusList[k].alertStatusId !=14){
								if(result[0].statusList[0].statusList[k].totalCount != null && parseInt(result[0].statusList[0].statusList[k].totalCount)>0){
									total = total+result[0].statusList[0].statusList[k].totalCount;
									//str+='<td  style="text-align:center" > '+result[0].statusList[0].statusList[k].count+' </td>';
									str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].totalCount+' </a> </td>';
								}
								else if(result[0].statusList[0].statusList[k].count != null && parseInt(result[0].statusList[0].statusList[k].count)>0){
									//str+='<td  style="text-align:center" > '+result[0].statusList[0].statusList[k].count+' </td>';
									str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].count+' </a> </td>';
								}
								else{
									str+='<td  style="text-align:center" >  -  </td>';
								}
								}
							}
							str+='<td  style="text-align:center" ><a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails(0,0,0)}"> '+total+'</a></td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			}
			
			$('#summaryDiv').html(str);
			
		}
	}
	getDistrictsForReferPopup();
	function getGovtGrievanceAlertDetails(){
		//$("#commentsBlock").html('');
		 var locatoinType;
		 var mobileNo;
		 var locationId;
		 var departmentsId = $("#departmentId").val();
		/* $(document).on("click",".radioBtnCls",function(){
			  var searchType  = $('input[name=checkBoxName]:checked').val();
			  if(searchType == 1){
				  $(".mobileSearchCls").show();
				  $(".locationSearchCls").hide();
				 mobileNo =$("#searchBy").val();
				if(mobileNo != null && mobileNo.trim().length>0){
				      locationId = 0;
					  locatoinType = "";
				}
			  }else if(searchType == 2){
				  $(".locationSearchCls").show();
				  $(".mobileSearchCls").hide();
				  var  districtId = $("#modalDistrictId").val();
				  var  mandalId = $("#modalMandalNameId").val();
				  var  villageId = $("#modalPanchayatId").val();
				  var hamletId = $("#modalhamletId").val();
				  if(hamletId != null && hamletId>0){
					  locatoinType = "hamlet";
					  locationId = hamletId;
					   mobileNo ="";
				  }
				  if(villageId != null && villageId>0){
					  locatoinType = "panchayat";
					  locationId = villageId;
					   mobileNo ="";
				  }
				  if(mandalId != null && mandalId>0){
					  locatoinType = "tehsil";
					  locationId = mandalId;
					   mobileNo ="";
				  }
				  if(districtId != null && districtId>0){
					  locatoinType = "district";
					  locationId = districtId;
					  mobileNo ="";
				  }
				  
			  }
			  });*/
		 $(".radioBtnCls").each(function(){
			 if($(this).prop('checked')==true){
			  var searchType  = $('input[name=checkBoxName]:checked').val();
			  if(searchType == 1){
				  $(".mobileSearchCls").show();
				  $(".locationSearchCls").hide();
				 mobileNo =$("#searchBy").val();
				if(mobileNo != null && mobileNo.trim().length>0){
				      locationId = 0;
					  locatoinType = "";
				}
			  }else if(searchType == 2){
				  $(".locationSearchCls").show();
				  $(".mobileSearchCls").hide();
				  var  districtId = $("#modalDistrictId").val();
				  var  mandalId = $("#modalMandalNameId").val();
				  var  villageId = $("#modalPanchayatId").val();
				  var hamletId = $("#modalhamletId").val();
				  if(districtId != null && districtId>0){
					  locatoinType = "district";
					  locationId = districtId;
					  mobileNo ="";
				  }
				  if(mandalId != null && mandalId>0){
					  locatoinType = "tehsil";
					  locationId = mandalId;
					   mobileNo ="";
				  }
				  if(villageId != null && villageId>0){
					  locatoinType = "panchayat";
					  locationId = villageId;
					   mobileNo ="";
				  }
				if(hamletId != null && hamletId>0){
					  locatoinType = "hamlet";
					  locationId = hamletId;
					   mobileNo ="";
				  }				  
			  }
			 }
		});
		 /*mobileNo = $('#searchBy').val().trim();
			if(mobileNo.length == 0 )
			{
				$('#errorMassgeId').html('Please enter Mobile No.');
				return;
			}
			if(mobileNo.length != 10 ||mobileNo.length > 10)
			{
				$('#errorMassgeId').html('Invalid Mobile No.');
				return;
			}*/
      	$('#commentsBlock').html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');		  
		var jsObj ={
			mobileNo: mobileNo,
			locatoinType:locatoinType,
			locationId : locationId,
			fromDate : "",
			toDate : "",
			alertStatusId : 0,
			deptId : departmentsId
		}
		$.ajax({
			type:'GET',
			url: 'getGovtGrievanceAlertDetailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGovtGrievanceAlertDetails(result);
			}else{
			 $("#commentsBlock").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>"); 
		  }	  
		});
	}
  function buildGovtGrievanceAlertDetails(result){
	var str = '';
	str+='<div class="table-responsive">';
	str +='<table class="table table-bordered table-condensed " id="partMeetingModalData">'; 
		str +='<thead class=" bg_ED">';
			str +='<th style="text-transform: uppercase;"> Alert ID</th>';
			str +='<th style="text-transform: uppercase;"> Date & Time</th>';
			str +='<th style="text-transform: uppercase;"> Location</th>';
			str +='<th style="text-transform: uppercase;"> Title</th>';
			str +='<th style="text-transform: uppercase;"> Discription</th>';
			str +='<th style="text-transform: uppercase;"> Related To</th>';
			str +='<th style="text-transform: uppercase;"> Problem</th>';
			str +='<th style="text-transform: uppercase;"> Status</th>';
			str +='<th style="text-transform: uppercase;"> Caller Details</th>';
			str +='<th style="text-transform: uppercase;"> Duplicate</th>';
		str +='</thead>';
		str +='<tbody>';
		for(var i in result){	
			if(result[i].callerDuplicate == 'YES')
				str+='<tr style="background-color:#ddd">';
			else
				str+='<tr>';
				if(result[i].alertId != null){
					str+='<td><a style="text-transform: uppercase;cursor:pointer;color:#337ab7" class="text-center alertIdSpecialSearch" attr_alertId="'+result[i].alertId+'">'+result[i].alertId+'</a></td>';
				}else{
					str+='<td style="text-transform: uppercase;" class="text-center">-</td>';
				}
			if(result[i].date != null){
				str+='<td style="text-transform: uppercase;">'+result[i].date+'<br/>'+result[i].time+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].tehsil != null){
				var locationName ="";
				if(result[i].district != null && result[i].district.length>0)
					locationName = locationName+"District : "+result[i].district+" ";
				if(result[i].assembly != null && result[i].assembly.length>0)
					locationName = locationName+"Assembly : "+result[i].assembly+" ";
				if(result[i].tehsil != null && result[i].tehsil.length>0)
					locationName = locationName+"Mandal : "+result[i].tehsil+" ";
				if(result[i].panchayat != null && result[i].panchayat.length>0)
					locationName = locationName+"Panchayat : "+result[i].panchayat+" ";
				if(result[i].hamlet != null && result[i].hamlet.length>0)
					locationName = locationName+"Hamlet : "+result[i].hamlet+"";
				if(result[i].leb != null && result[i].leb.length>0)
					locationName = locationName+" Munci/Corp/Greater City : "+result[i].leb+"";
				if(result[i].ward != null && result[i].ward.length>0)
					locationName = locationName+" "+result[i].ward+"  ";
				if(locationName.length > 25)
					str+='<td><span  class="tooltipCls" title="'+locationName+'">'+locationName.substring(0,25)+'...</span></td>';
				else
					str+='<td>'+locationName+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].title != null){
				str+='<td style="text-transform: uppercase;">'+result[i].title+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].description != null){
				if(result[i].description.length > 50)
				{
					str+='<td style="text-transform: uppercase;"><span  class="tooltipCls" title="'+result[i].description+'">'+result[i].description.substring(0,50)+'...</span></td>';
				}else{
					str+='<td style="text-transform: uppercase;">'+result[i].description+'</td>';
				}
				
			}else{
				str+='<td>-</td>';
			}
			if(result[i].relatedTo != null){
				str+='<td style="text-transform: uppercase;">'+result[i].relatedTo+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].problem != null){
				str+='<td style="text-transform: uppercase;">'+result[i].problem+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].status != null){
				str+='<td style="text-transform: uppercase;">'+result[i].status+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].subList != null &&  result[i].subList.length > 0){
				str+='<td>';
					for(var j in result[i].subList){
						str+='<p>'+result[i].subList[j].name+' <br/> '+result[i].subList[j].mobileNo+'</p>'
					}
				str+='</td>';
				//str+='<td style="text-transform: uppercase;">'+result[i].createdBy+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(result[i].status != null && result[i].status == "Closed" || result[i].status == "Completed"){
				str+='<td><input type="radio" disabled name="modalRadio" class="modalRadioCls" attr_btn_divId="modalSubmitId'+i+'"/>';
			}else{
				str+='<td><input type="radio" name="modalRadio" class="modalRadioCls" attr_btn_divId="modalSubmitId'+i+'"/>';
			}			
				str+='<button class="btn btn-success btn-sm modalBtnCls" id="modalSubmitId'+i+'" attr_loading_img="modalLoadingImgId'+i+'" attr_alert_id='+result[i].alertId+' style="display:none;margin-left:5px;">Duplicate</button><img id="modalLoadingImgId'+i+'" style="width:50px;height:50px;display:none;"  src="./images/Loading-data.gif" alt="Processing Image"/>';
			str+='</td>';
		str+='</tr>';
		
		}
		str +='</tbody>';
	str +='</table>';
	str +='</div>';
	$("#commentsBlock").html(str); 
	$(".tooltipCls").tooltip();
	$("#partMeetingModalData").dataTable();
	$("#partMeetingModalData").removeClass("dataTable");
	$("#alertManagementPopup").find(".close").addClass("closeSecondModal");
}
	
	$(document).on("click",".modalRadioCls",function(){
		$(".modalBtnCls").hide();
		var btnDiv = $(this).attr("attr_btn_divId");
		$("#"+btnDiv).show();
	});
	
	$(document).on("click",".modalBtnCls",function(){
		var alertId = $(this).attr("attr_alert_id");
		var mobileNo = $("#mobileNoId").val();
		var nmae = $("#nameId").val();
		var email = $("#emailId").val();
		var address = $("#addressId").val();
		var loadngImg = $(this).attr("attr_loading_img");
		
		/*if(mobileNo.length==0 ||mobileNo==''){
			$("#modalErrorDiv").html(" Please Enter Caller Details,Enter MobileNo ");
			return;
		}
		if(mobileNo.length != 10){
			$("#modalErrorDiv").html(" Please Enter Caller Details,Enter Valid MobileNO ");
			return;
		}
		if(mobileNo.length > 0){
			var numericExpression = /^[0-9]+$/;
			if(!mobileNo.match(numericExpression)){
				$('#modalErrorDiv').html('Enter Caller Details,Enter Numerics Only.');
				return;
			}
		}
		if(nmae.length==0 ||nmae==''){
			$("#modalErrorDiv").html(" Please Enter Caller Details,Enter Name ");
			return;
		}
		if(address.length==0 ||address==''){
			$("#modalErrorDiv").html(" Please Enter Caller Details,Enter Address ");
			return;
		}*/
		$("#"+loadngImg).show();
		var jobj={
			mobileNo : mobileNo,
			name : nmae,
			email : email,
			address : address,
			alertId : alertId
		}
		$.ajax({
			type : "POST",
			url  : "updateDuplicateAlertCallerDetailsAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			if(result != null && result == 'success'){
				$("#"+loadngImg).hide();
				alert("This Caller Request has been marked as Duplicate for the Ref No: "+alertId+" and SMS sent to Caller mobile "+mobileNo);
				location.reload();
				$("#mobileNoId").val('');
				$("#nameId").val('');
				$("#emailId").val('');
				$("#addressId").val('');
			}
			else{
				$("#"+loadngImg).hide();
				alert("Exception Occured...Please Try Again..");
			}
		});
	});
	
	function getMandalsByConstituencyForReferPopup1(){
		$("#commentsBlock").html('');
	 var districtId = $('#modalDistrictId').val();
	 
	 var url = "getAllMandalsByDistrictIDAction.action";
	 var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getAllMandalsByDistrictIDAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getAllLebsByDistrictIDAction.action";
	}
	 
	 var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : url,
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">ALL</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#modalMandalNameId").html(mandalStr);
				$("#modalMandalNameId").trigger('chosen:updated');                
				//for panchayat
				$("#modalPanchayatId").html('<option value="0">ALL</option>');
				$("#modalPanchayatId").trigger('chosen:updated'); 
			
				if(glMandalId != null && glMandalId>0){
					$("#modalMandalNameId").val(glMandalId);
					$("#modalMandalNameId").trigger('chosen:updated'); 
				}
				else{
					getGovtGrievanceAlertDetails();
				}
				
				if(glPanchayatId != null && glPanchayatId>0){
					$("#modalMandalNameId").trigger('onchange');
				}
				else{
					getGovtGrievanceAlertDetails();
				}
				if(glhamletId != null && glhamletId>0)
					$("#modalMandalNameId").trigger('onchange');
				
			}else{
				//for mandal/municipality
				$("#modalMandalNameId").html('<option value="0">ALL</option>');
				$("#modalMandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#modalPanchayatId").html('<option value="0">ALL</option>');
				$("#modalPanchayatId").trigger('chosen:updated'); 
			}
		});
 }
 function getPanchayatsForReferPopup1(){
	 $("#commentsBlock").html('');
	 designationsByDepartment();
	
	 $("#modalPanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#modalMandalNameId').val();
	 var  type = $("#modalMandalNameId option:selected").text();
	 		   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ;

	var url = "getPanchayatAndWardDetailsAction.action";
	 var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getPanchayatAndWardDetailsAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getUrbanLocalitiesForMuncipalityAction.action";
	}
	
	  var jsObj={
				mandalId :mandalId,
				type:type,
				task:""
				
			}
	 $.ajax({
				type:"POST",
				url :url,
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">ALL</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#modalPanchayatId").html(panchyatStr);
					$("#modalPanchayatId").trigger('chosen:updated');
					
					if(glPanchayatId != null && glPanchayatId>0){
						$("#modalPanchayatId").val(glPanchayatId);
						$("#modalPanchayatId").trigger('chosen:updated'); 
					}
					else{
						getGovtGrievanceAlertDetails();
					}
					if(glhamletId != null && glhamletId>0)
						$("#modalPanchayatId").trigger('onchange');
					else{
						getGovtGrievanceAlertDetails();
					}
				}else{
					//for panchayat
					$("#modalPanchayatId").html('<option value="0">ALL</option>');
					$("#modalPanchayatId").trigger('chosen:updated'); 
				}     
		});
}

 $(document).on("click",".radioBtnCls",function(){ //mobileSearchByCls
	  $("#searchBy").val('');
	  $("#commentsBlock").html(''); 
	  $("#modalDistrictId").val(0).trigger('chosen:updated');
	  $("#modalMandalNameId").val(0).trigger('chosen:updated');
	  $("#modalPanchayatId").val(0).trigger('chosen:updated');
	  $("#modalhamletId").val(0).trigger('chosen:updated');
	  var searchType  = $('input[name=checkBoxName]:checked').val();
	  if(searchType == 1){
		  $(".mobileSearchCls").show();
		  $(".locationSearchCls").hide();
		 mobileNo =$("#searchBy").val();
		 var priviousMobileNo =$("#mobileNoId").val();
			$("#searchBy").val(priviousMobileNo);
			getGovtGrievanceAlertDetails();
	  }else if(searchType == 2){
		  $(".locationSearchCls").show();
		  $(".mobileSearchCls").hide();
		  glDistrictId =$("#referdistrictId").val();
			glMandalId = $("#refermandalNameId").val();
			glPanchayatId = $("#referpanchayatId").val();
			glhamletId =$("#hamletId").val();
			
			$("#modalDistrictId").val(glDistrictId);
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalDistrictId").trigger("onchange");
	  }
});
			  
var glDistrictId =0;
var glMandalId = 0;
var glPanchayatId = 0;
var glhamletId =0;

 $(document).on("click",".govtGrievanceCls",function(){
	 $("#errMsgMobileNoId").html('');
	 $("#errMsgNameId").html('');
	 $("#errMsgIssueTypeId").html('');
	 
	var name = $("#nameId").val().trim();
	var address = $("#addressId").val().trim();
	var mobileNo = $("#mobileNoId").val().trim();
	var issueTypeId = $("#issueTypeId").val();
	
	
	if(issueTypeId == 0){
		$("#errMsgIssueTypeId").html(" Select Issue Type ");
		return;
	}
	if(mobileNo.length==0 ||mobileNo==''){
		$("#errMsgMobileNoId").html(" Please Enter MobileNo ");
		return;
	}
	if(mobileNo.length != 10){
		$("#errMsgMobileNoId").html(" Please Enter Valid MobileNO ");
		return;
	}
	if(mobileNo.length > 0){
		var numericExpression = /^[0-9]+$/;
		if(!mobileNo.match(numericExpression)){
			$('#errMsgMobileNoId').html('Enter Numerics Only.');
			return;
		}
	}
	if(name.length==0 ||name==''){
		$("#errMsgNameId").html(" Please Enter Name ");
		return;
	}
	
	  $("#govtGrievanceAlertModalId").modal("show");
       $("#commentsBlock").html('');
      $("#searchBy").val('');	   
	   //$("#radioBtnClsId").trigger("click");
	    $("#modalDistrictId").val(0);
		$("#modalMandalNameId").val(0);
		$("#modalPanchayatId").val(0);
		$("#modalhamletId").val(0);
		
		$("#modalDistrictId").trigger('chosen:updated');
		$("#modalMandalNameId").trigger('chosen:updated');
		$("#modalPanchayatId").trigger('chosen:updated');
		$("#modalhamletId").trigger('chosen:updated');
		glDistrictId =0;
		glMandalId = 0;
		glPanchayatId = 0;
        glhamletId =0;
	   var type = $('input[name=searchRadio]:checked').val();;
	   
	   if(type =='mobile'){
			$(".mobileSearchCls").show();
			$(".locationSearchCls").hide();
			$('#radioBtnMobileId').prop('checked',true);
			var priviousMobileNo =$("#mobileNoId").val();
			$("#searchBy").val(priviousMobileNo);
			getGovtGrievanceAlertDetails();
		 }
		 else if(type =='location'){
			 $(".mobileSearchCls").hide();
			 $(".locationSearchCls").show();
			 $('#radioBtnClsId').prop('checked',true);
			 
			 glDistrictId =$("#referdistrictId").val();
			glMandalId = $("#refermandalNameId").val();
			glPanchayatId = $("#referpanchayatId").val();
			glhamletId =$("#hamletId").val();
			
			$("#modalDistrictId").val(glDistrictId);
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalDistrictId").trigger("onchange");
		 } 
 });
 function getHamletss1(){
	  $("#commentsBlock").html('');
	 $("#modalhamletId").find('option').not(':first').remove();
	 var panchayatId = $('#modalPanchayatId').val();
	 var  type = $("#modalMandalNameId option:selected").text();
			   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	var url = "getHamletsForPanchayatAction.action";
	 var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getHamletsForPanchayatAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getUrbanBlocksForLocalityAction.action";
	}
	
	//if(type == "mandal"){
		
		var jsObj={
				panchayatId :panchayatId,
				task:""
			}
	 $.ajax({
				type:"POST",
				url :url,
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">ALL</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#modalhamletId").html(panchyatStr);
					$("#modalhamletId").trigger('chosen:updated');
					if(glhamletId != null && glhamletId>0){
						$("#modalhamletId").val(glhamletId);
						$("#modalhamletId").trigger('chosen:updated');
						getGovtGrievanceAlertDetails();
					}
					else{
						getGovtGrievanceAlertDetails();
					}
					
				}else{
					//for panchayat
					$("#modalhamletId").html('<option value="0">ALL</option>');
					$("#modalhamletId").trigger('chosen:updated'); 
				}     
		});
	//}
}
$(document).on("click",".closeFileCls",function(){
	$("#uploadFileId0").val('');
});

function getRealtedDepartments(){
	$('#departmentId').empty();
	$('#departmentId').trigger('chosen:updated');
	
	getAlertIsuueSubTypes();
	
	var issueType = $("#issueTypeId").val();
	var jsObj={
		issueTypeId :issueType
	}
	$.ajax({
		type:"POST",
		url :"getRelatedDepartmentsForIssueTypeAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		//$('#departmentId').append('<option value="0">Select Department</option>');
		if(result != null && result.length > 0)
		{
			for(var i in result)
				$('#departmentId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		}
		$('#departmentId').trigger('chosen:updated');
		$('#departmentId').trigger("change");
	});
}

function getRuralUrbanLocations(){
	var deptName = $("#departmentId option:selected").text();
	var locationType = deptName.split("-")[1];
	$("#locationTypeHidVal").val(locationType);
	if(locationType != null && locationType == "Rural"){
		$('#alertlevelId').empty();
		$('#alertlevelId').trigger('chosen:updated');
		$('#alertlevelId').append('<option value="7" selected>Habitation</option>');
		$('#alertlevelId').trigger('chosen:updated');
		
		$('#locationLevelSelectId').empty();
		$('#locationLevelSelectId').trigger('chosen:updated');
		$('#locationLevelSelectId').append('<option value="5">MANDAL</option>');
		$('#locationLevelSelectId').trigger('chosen:updated');
	}
	else if(locationType != null && locationType == "Urban"){
		$('#alertlevelId').empty();
		$('#alertlevelId').trigger('chosen:updated');
		$('#alertlevelId').append('<option value="13" selected>Urban Block</option>');
		$('#alertlevelId').trigger('chosen:updated');
		
		$('#locationLevelSelectId').empty();
		$('#locationLevelSelectId').trigger('chosen:updated');
		$('#locationLevelSelectId').append('<option value="7">MUNICIPALITY</option>');
		$('#locationLevelSelectId').trigger('chosen:updated');
	}
}
$(document).on("change","#feedbackStatusList",function(){
		var feedbackId = $("#feedbackStatusList").val();
		
		$('#reopenCheckboxId').attr('checked', false);
		
		if(feedbackId ==2 || feedbackId == 3){
			$("#reOpenSpanId").show();
		}else{
			$("#reOpenSpanId").hide();
		}
	});
</script>
</body>
</html>