<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Appointment</title>
<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/Appointment/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Appointment/DragDrop/app.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="dist/Appointment/MultiDatePicker/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
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
	<!-- YUI Dependency files (End) -->
<style type="text/css">
.SelectPosition li
{
	padding:5px;
	width:100%;
	border-top:1px solid #ccc;
	border-left:1px solid #ccc;
	border-right:1px solid #ccc;
	list-style:none;
	cursor:pointer;
}
.daterangepicker_end_input
{
	padding:0px !important;
}
.SelectPosition li:last-child
{
	border-bottom:1px solid #ccc;
}
.SelectPositionScroll 
{
	width: 100%;
	height: 140px;
	overflow: auto;
}
.SelectPosition
{
	padding:0px;
	margin-top:10px;
}
.m_left16{
	 margin-left: 16px !important;
}
.validateClr{
	color:red;
}
.ui-widget-header
{
	background:#fff !important;
	border:1px solid #fff !important;
	color:#333;
	font-weight:400;
}
.m_top5
{
	margin-top:5px;
}
.closeIcon
{
	background:#ccc;
	color:#666;
	padding:5px;
	position:absolute;
	right:0px;
	top:0px;
	cursor:pointer;
}
.block{
	position:relative;
}
.m_top10
{
	margin-top:10px;
}
.prev,.next{width:60px !important;}
#upcomingAppointments,#inprogreessAppointMentId,#completedAppointMentId {height:36px;}
.tableAppointment thead th  {
    background: #f2f2f2 none repeat scroll 0 0;
    border: 1px solid #fff !important;
    padding-left: 8px !important;
	font-weight: normal;
}
.removetopborder td{
	border-top: none !important;
}
</style>
</head>
<body>
<div class="container">
<div class="row">
  <div class="col-md-12 col-xs-12 col-sm-12">
	<div class="panel panel-default panelCustom1">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-3">
							<h3 class="m_top10"><b>APPOINTMENTS</b></h3>
						</div>
						<div class="col-md-3 col-md-offset-6">
							<select id="appointmentUserSelectBoxId" class="form-control"></select>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div>
					  <ul class="nav nav-tabs navTabsCustom" role="tablist">
						<li role="presentation"  class="active refreshBlockDiv"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><img src="dist/Appointment/img/dashboard.png">Dashboard</a></li>
						<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="createAppReqCls"><img src="dist/Appointment/img/createappointment.png">Create Appointment Request</a></li>
						<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab" class="MngeAppntmntCls"><img src="dist/Appointment/img/manageappointments.png">Manage APpointments</a></li>
						<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab" class="cnfrmaptsCls"><img src="dist/Appointment/img/confirmappointments.png">Confirm APpointments</a></li>
					  </ul>
					  <!-- Tab panes -->
					  <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<table class="table table-bordered bg_ff m_top10">
										<tr>
											<td>
												<h4 class="panel-title m_top10">TODAY APPOINTMENTS</h4>
												<table class="table table-condensed tableAppointment" id="todayAppointmentsId">
													<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="todayAptLoadingId"></center></div>
												</table>
											</td>
											<td>
												<table class="table removetopborder">
													<tr>
														<td>
															<h4 class="panel-title">TOTAL APPOINTMENTS REQUESTED</h4>
																<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="totalAptLoadingId"></center></div>
															<!--<ul class="columnChartUl" id="totalAppointmentsId"></ul>-->
															<table class="table table-condensed tableAppointment" id="totalAppointmentsId">
															</table>	
															
														</td>
														<td>
															<div id="LineChart" style="width:500px;height:300px;"></div>
															<div  style="text-align: center;">Total Appointments - <span id="totalApptStatusCounts"></span></div>
														</td>
													</tr>
												</table>
                                            	
                                            </td>
										</tr>
									</table>
								</div>
							</div>
							<div id="containerpie" ></div>
							<div class="row m_top10">
								<div class="col-md-12">
									<div class="todayBlock ">
										<div class="row">
											<!--<div class="col-md-12">
												<h4 class="text-success text-capitalize">SEARCH for Scheduled appointment </h4>
											</div>-->
											<div class="col-md-3">
                                            	<label>Appointment Status</label>
                                                <select class="dropkickClass" id="selectStatusId">
                                                	<option value="0">All</option>
													<option value="1">Upcoming</option>
													<option value="2">InProgress</option>
													<option value="3">Completed</option>
													
                                                </select>
											</div>
											<div class="col-md-2">
                                            	<label>Appointment Created By</label>
                                                <select class="dropkickClass" id="appointmentcreatedBy">
                                                	<option value="0">All</option>
                                                </select>
                                            </div>
											<div class="col-md-3">
                                            	<label>Select Appointment Created Date</label>
                                                <div class="input-group inputSearch">
                                                	<span class="input-group-addon">
                                                    	<i class="glyphicon glyphicon-calendar"></i>
                                                        <span class="caret"></span>
                                                    </span>
                                                    <input type="text" class="form-control" id="dashboardSelectDateIds">                                               
												</div>
                                            </div>
											<div  class="col-md-1 m_top25">
                                            	<button id="" class="btn btn-success btn-block showTimeSlotsCls" onclick="getSearchDetails();">VIEW</button>
                                            </div>
											<div class="col-md-3" style="border-left:1px solid #ddd;">
												<label>Search</label>
												<div class="input-group inputSearch">
													<input class="form-control" type="text" id="searchStrId"  placeholder="Name or MobileNumber"">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-search"></i>
													</span>
												</div>
											</div>
											
										</div>
									</div>
									</div>
							</div>
							
                            <div class="row">
								<div class="col-md-12 m_top20">
									<h4>TOTAL APPOINTMENTS</h4>
								</div>
                            	<div class="col-md-4">
                                	<div id="upcomingAppointMentId" >
                                    
                                    </div>
                                </div>
                                <div class="col-md-4 pad_0 " >
                                	<div id="inprogreessAppointMentId">
                                    
                                    </div>
                                </div>
                                <div class="col-md-4 ">
                                	<div id="completedAppointMentId"></div>
                                </div>
                            </div>
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="block">
										<div class="col-md-9">
											<h4 class="text-success">SEARCH MEMBER TO CREATE APPOINTMENT REQUESTED</h4>
										</div>
										<div class="col-md-3">
												<div class="onoffswitch">
													<input type="checkbox" value="1" name="searchTypeRadio" class="onoffswitch-checkbox searchTypeRadioCls" id="myonoffswitch" checked>
													<label class="onoffswitch-label" for="myonoffswitch">
														<span class="onoffswitch-inner"></span>
														<span class="onoffswitch-switch"></span>
													</label>
												</div>
											<!--<button class="btn btn-block btn-success m_top25 getDetailsBySrch">SEARCH MEMBER</button>
											<button class="btn btn-block btn-success m_top25 getDetailsByAdvancedSearch">Advanced Search</button>-->
											<!--<input type="radio" value="1" name="searchTypeRadio" class="searchTypeRadioCls" checked/>SEARCH MEMBER
											<input type="radio" value="2" name="searchTypeRadio" class="searchTypeRadioCls"/>Advanced Search-->
										</div>
                                        <div class="row m_top20">
                                        	<!--<div class="col-md-4 pad_right0">
                                            	<label>Select Location</label>
                                                <input type="text" class="form-control"/>
                                                <ul class="searchOptions">
                                                	<li></li>
                                                </ul>
                                            </div>-->
											
											<div class="col-md-2 searchCls">
												<label>Search Type</label>
                                                <select class="dropkickClass"  id="searchTypeId">
													<option value="0">Select Search Type</option>
													<option value="mobileno">Mobile No</option>
													<option value="mebershipno">Membership No</option>
													<option value="votercardno">Voter Id Card No</option>
												</select>
											</div>
                                            <div class="col-md-4 pad_0 searchCls">
                                            	<label>Search By Membership No/ Phone No/ Voter ID<span class="text-danger">*</span></label>
                                                <input type="text" class="form-control clearCls" id="searchValueId">
												<span id="errDigitsId" class="full-right" style="color:red;"></span>
                                            </div>
											
											<div class="col-md-3 advanceSearchCls">
												<label>Search Type</label>
                                                <select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();">
													<option value="0">Select Search Type</option>
													<option value="1">Name</option>
													<option value="2">Public Representative</option>
													<option value="3">Party Committee</option>
												</select>
											</div>
                                            <div class="col-md-4 pad_0 advanceSearchCls advanceprcls">
                                            	<label class="advanceNameCls">Search By Name<span class="text-danger">*</span></label>
                                                <input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
												<label class="advancePRCls">Search Designation</label>
												 <select class="advancePRCls dropkickClass"  id="advanceDesignationId">
													<option value="0">Select Designation</option>
													<!--<option value="1">MP</option>
													<option value="2">MLA</option>
													<option value="6">2014 AP STATE MINISTERS</option>
													<option value="7">2014 CENTRAL MINISTERS</option>
													<option value="8">EX-MLA</option>
													<option value="9">EX-MP</option>
													<option value="10">2014 ASSEMBLY CONTESTED</option>
													<option value="11">2014 PARLIAMENT CONTESTED</option>
													<option value="12">MLC</option>
													<option value="16">MP (RAJYA SABHA)</option>
													<option value="23">EX-STATE MINISTER</option>-->
												</select>
												<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
												
                                            </div>
											<div id="OtherSelectlocationsDiv">
												<div>
													<div class="col-md-2 locationsFilterCls">
														<label>Level</label>
														<select class="dropkickClass" id="levelId" onchange="disableByLevel();">
														<!--<option value="0">ALL</option>-->
														<option value="10">State</option>
														<option value="11">District</option>
														<option value="5">Mandal/Muncipality</option>
														<option value="6">Village/Ward</option>
														</select>
													</div>
												  <div class="col-md-2 locationsFilterCls stateCls">
														<label>State</label>
														<select class="dropkickClass" id="stateId">
														<option value="0">All</option>
														<option value="1">AP</option>
														<option value="36">TS</option>
														</select>
												 </div>
													<div class="col-md-2 locationsFilterCls distCls">
														<label>District</label>
														<select class="dropkickClass" id="referdistrictId" onchange="getConstituenciesBydistrictForReferPopup();">
														<option value="0">All</option></select>
													</div>
													<div class="col-md-2 locationsFilterCls constiCls">
														<label>Assembly</label>
														<select class="dropkickClass" id="referconstituencyId" onchange="getMandalsByConstituencyForReferPopup();">
															<option value="0">All</option>
														</select>
													</div>
													<div class="col-md-3 locationsFilterCls mandalCls">
														<label>Mandal/ Municipality</label>
														<select class="dropkickClass" id="refermandalNameId" onchange="getPanchayatsForReferPopup();">
															<option value="0">All</option>
														</select>
													</div>
													<div class="col-md-3 locationsFilterCls panchayatCls">
														<label>Panchayat</label>
														<select class="dropkickClass" id="referpanchayatId">
															<option value="0">All</option>
														</select>
													</div>
													
													
													<div>
														
								
														<div class="advanceCadreCommittee" style="margin-top:5px;" id="referCommitteeDiv">
														 <div class="col-md-5">
															<label>Select Committee</label>
															<select id="referCommitteeId" class="dropkickClass" >
																<option value="0">All</option>
																<option value="1">Main</option><option value="2">Telugu Yuvatha</option>
																<option value="3">Telugu Mahila</option><option value="4">Telugu Rythu</option>
																<option value="17">Trade</option><option value="6">BC Cell</option><option value="7">SC Cell</option>
																<option value="8">ST Cell</option><option value="9">Minority Cell</option><option value="18">Christian</option>
																<option value="11">TNSF (Student Union)</option><option value="5">TNTUC</option><option value="15">TSNV (Technical Expert Cell)</option>
																<option value="10">Legal Cell</option><option value="16">Doctor Cell</option><option value="20">Kallu Geetha Karmikulu</option>
																<option value="21">Chenetha</option><option value="19">Telugu Rakshana Vedika</option><option value="14">TNUS ( Teachers Union) </option>
																<option value="12">Commercial Cell</option><option value="13">Cultural Cell</option>
															</select>
														  </div>
														</div> 
														<div >
															<div class="col-md-6">
																<div id="cadreCommitteeDiv" class="advanceCadreCommittee"></div>
																<div id="representativesDiv"></div>
																<div id="referRoleErrorDiv"></div>
															</div>
														</div>
													</div></br>
												</div>
											</div>
											<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
											<div class="col-md-2">
											
												<button class="btn btn-block btn-success m_top20 advancedSearchBtn" onclick="handleBySearchType();"  style="margin-top: 25px;">Search Member</button>
											</div>
                                            
											
											<div class="col-md-1">
												<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
											</div>
												<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
									</div>
										<div class="row m_top25">
											<div class="col-md-12" id="clearSerchDivId">
												<div id="apptmemberDetailsDiv"></div>
											</div>
                                        </div>
                                    </div>
									<div class="block">
										<div class="row">
											<div class="col-md-12">
                                            	<h4 class="text-success">CREATE APPOINTMENT REQUEST</h4>
                                            </div>
										</div>
									</div>
									<form name="saveAppointment" id="saveAppointment"  method="post">
										<div class="block">
											<div class="row">
												<div class="col-md-4 m_top10">
													<label>Appointment Priority Type</label><span style='color:red'> &nbsp * </span>
													<select name="appointmentVO.appointmentPriorityId" class="manageAppTypeCls" id="createAppTypeListId">
														<option value="0">Select Priority</option>
													</select>
													<div class="errorAptCls validateClr"></div>
												</div>
												<div class="col-md-8 m_top10">
													<label class="radio-inline">
														<input type="radio" id="selectManualDateId" class="dateRadioCls" checked name="dateTypeRadio" value="multipleDates">Select Preferrable Dates
													</label>
													<label class="radio-inline">
														<input type="radio" class="dateRadioCls" name="dateTypeRadio" value="nextWeek">Next Week(Any Date)
													</label>
													<label class="radio-inline">
														<input type="radio" class="dateRadioCls" name="dateTypeRadio" value="nextMonth">Next Month(Any Date)
													</label>
													<label class="radio-inline">
														<input type="radio" class="dateRadioCls" name="dateTypeRadio" value="thisWeek">This Week
													</label>
													<div class="input-group inputSearch m_top5">
														<span class="input-group-addon">
															<i class="glyphicon glyphicon-calendar"></i>
															<span class="caret"></span>
														</span>
														<input type="text" class="form-control multiDateCls disableCls" id="multiDate" name="appointmentVO.appointmentDates" readOnly>
													</div>
													<div class="errorSpdCls validateClr"></div>
												</div>
												<div class="col-md-6 m_top10">
													
													
												</div>
												<div class="col-md-12 m_top10">
													<label>Appointment Purpose</label><span style='color:red'> &nbsp * </span>
													<textarea class="form-control" id="appointmentReasonId" name="appointmentVO.reason"></textarea>
													<div class="errorArCls validateClr"></div>
												</div>
												
												
											</div>
											
										</div>
										<div style="margin-top: 50px;"><img id="checkboxMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
										<div class="errorCandidateMainDivCls validateClr"></div>
										<div id="showapptDetails">
											<div id="moreCandidatesDivId"></div>
												<div class="row m_top10">
													<div class="col-md-4 col-md-offset-8">
														<p style="cursor:pointer;float:right" id="addOneBlock">
															Add Candidate
															<i class="glyphicon glyphicon-plus-sign text-success"></i>
														</p>
													</div>
												</div>
										</div>
										<div class="row">
											<div class="col-md-6 m_top25">
												<button class="btn btn-success btn-block" type="button" onClick="savingAppointment();">CREATE APPOINTMENT</button>
											</div>
											<div style="margin-top:12px;"><img id="appntCreateAjax" src="images/icons/loading.gif" style="display:none;"/></div>
											<div class="col-md-6 m_top25" id="savingStatusDivId"></div>
										</div>
										<input type="hidden" id="dateTypeText" name="appointmentVO.appointmentPreferableTimeType">
										<input type="hidden" id="uniqueCode" name="appointmentVO.uniqueCode">
									</form>
									
									<div class="block cloneBlock addattrid" style="display:none;">
										<input type="hidden" class="cloneCandidateIdCls"/>
										<input type="hidden" class="cloneCandiImageUrlCls"/>
										<div class="row">
											<span class="closeIcon"><i class="glyphicon glyphicon-remove"></i></span>
											<div class="col-md-3 m_top10">
												<label>Name</label><span style='color:red'> &nbsp * </span>
												<input type="text" class="form-control cloneNameCls">
												<div class="cloneErrCandidateNameCls validateClr"></div>
											</div>
												<div class="col-md-3 m_top10">
												<label>Candidate Type</label><span style='color:red'> &nbsp * </span>
												<select class="cloneCandidateTypeCls">
													<option value="0">Select Candidate Type</option>
												</select>
												<div class="cloneErrCandidateTypeCls validateClr"></div>
											</div>
											<div class="col-md-3 m_top10">
												<label>Designation</label><span style='color:red'> &nbsp * </span>
												<select class="cloneDesignationCls " >
													<option value="0">Select Designation</option>
												</select>
												<div class="cloneErrCandidateDesgCls validateClr"></div>
											</div>
										 <div class="col-md-3 m_top10">
												<label>Contact Number</label><span style='color:red'> &nbsp * </span>
												<input type="text" class="form-control cloneMobileCls">
												<div class="cloneErrCandidateMobileCls validateClr"></div>
											</div>
										</div>
										<div class="row">
											<!--<div class="col-md-4 m_top10">
												<label>Location</label>
												<input type="text" class="form-control cloneLocationCls">
											</div>-->
											<div class="col-md-4 m_top10">
												<label>Voter ID</label>
												<input type="text" class="form-control cloneVoterIdCls">
												<div class="cloneErrCandidateVoterCls validateClr"></div>
											</div>
											<div class="col-md-4 m_top10">
												<label>Membership Number</label>
												<input type="text" class="form-control cloneMembershipNumCls">
												<div class="cloneErrCandidateMemShipCls validateClr"></div>
											</div>
											<div class="col-md-4 m_top10">
												<label>Location Scope</label><span style='color:red'> &nbsp * </span>
												<select class="cloneLocationScopeCls regionScopeCls ">
													<option value="0">Select Scope</option>
													<option value="3">DISTRICT</option>
													<option value="4">CONSTITUENCY</option>
													<option value="5">MANDAL</option>
													<option value="6">VILLAGE</option>
													<option value="7">MUNICIPAL-CORP-GMC</option>
													<option value="8">WARD</option>
												</select>
												<div class="cloneErrCandidateLcScopeCls validateClr"></div>
											</div>
										</div>
										<div class="row m_top10">
											<!--<div class="col-md-4">
												<label>Location Scope</label>
												<select class="cloneLocationScopeCls regionScopeCls">
													<option value="0">Select Scope</option>
													<option value="3">DISTRICT</option>
													<option value="4">CONSTITUENCY</option>
													<option value="5">MANDAL</option>
													<option value="6">VILLAGE</option>
													<option value="7">MUNICIPAL-CORP-GMC</option>
													<option value="8">WARD</option>
												</select>
											</div>-->
											<div class="col-md-4 cloneDistDivCls" style="display:none;">
												<label>Select District</label><span style='color:red'> &nbsp * </span>
												<select class="cloneDistrictCls ">
													<option value="0">select dist</option>
													<!--<option value="14">test dist1</option>-->
												</select>
												<div class="cloneErrCandidateDistrictCls validateClr"></div>
											</div>
											<div class="col-md-4 cloneConstDivCls" style="display:none;">
												<label>Select Constituency</label><span style='color:red'> &nbsp * </span>
												<select class="cloneConstituencyCls " >
													<option value="0">select const</option>
													<!--<option value="142">test const1</option>-->
												</select>
												<div class="cloneErrCandidateConstCls validateClr"></div>
											</div>
											<div class="col-md-4 cloneMandalDivCLs" style="display:none;">
													<label>Select Mandal/Muncilpality</label><span style='color:red'> &nbsp * </span>
													<select class="cloneMandalCls ">
														<option value="0">Select Mandal</option>
													</select>
													<div class="cloneErrCandidateMandalCls validateClr"></div>
												</div>
												<div class="col-md-4 cloneVillageDivCls" style="display:none;">
													<label>Select Village/Ward</label><span style='color:red'> &nbsp * </span>
													<select class="cloneVillageCls ">
														<option value="0">Select VILLAGE</option>
													</select>
													<div class="cloneErrCandidateVillageCls validateClr"></div>
												</div>
											</div>
									</div>
									
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="messages">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
								  <div class="panel panel-default m_top10 panelWhite" id="allAppointmentsHideBlock">
                                    	<div class="panel-heading">
                                        	<div class="row">
                                            	<div class="col-md-4">
                                                	<h4 class="panel-title text-success">VIEW CREATED APPOINTMENT LABEL</h4>
                                                </div>
												<div class="col-md-2">
													<!--<div class="input-group inputSearch">
														<span class="input-group-addon">
															<i class="glyphicon glyphicon-calendar"></i>
														</span>
														<input class="form-control" type="text" id="mngAppntmntsDtPckrId"/>
													</div>-->
												</div>
                                                <div class="col-md-3">
                                                	<button class="btn btn-success btn-block" id="viewAllAppointmentId">VIEW ALL APPOINTMENT REQUESTS</button>
                                                </div>
                                                <div class="col-md-3">
                                                	<button class="btn btn-success btn-block errrLabelClearCls"  data-toggle="modal" data-target=".bs-example-modal-sm">CREATE APPOINTMENT LABEL</button>
                                                </div>
                                            </div>
                                        </div>
										<div class="row" style="margin-left:5px;">
											<div class=" col-md-2 m_top10">
												<select id="selectStsForLabelId" class="form-control" placeholder="Select Status"></select>
											</div>
										</div>
                                        <div class="panel-body">
                                        	<div class="table-responsive">
											<div id="appntmntLblDltSttsId"></div>
											<div id="bldCnfrmtnMdlBoxId"></div>
											<div id="buildAppntmntLblTblId"></div>  
											<div id="buildAppntmntStsTblId"></div>  
											 
											</div>
										
										</div>
										 
                                  </div>
									<div class="panel panel-default m_top10 panelWhite">
										<div class="panel-heading">
											<h4 class="panel-title">OVERALL PENDING APPOINTMENT REQUESTS OVERVIEW</h4>
										</div>
										<div class="panel-body">
											<table>
												<tr>
													<td style="width: 25%;background-color:#339967; text-align: center; padding: 20px 80px; "><h4 class="text-success" style="color:#fff;">Waiting <br> <span  id="waitingStatus">0</span></h4></td>
													<td style="width: 25%; background-color:#336799; text-align: center; padding: 20px 80px;color:#fff;"> <h4 class="text-success" style="color:#fff;">Rescheduled  <br> <span  id="rescheduledStatus">0</span></h4></td>
													<td style="width: 25%;background-color:#6A0208; text-align: center; padding: 20px 80px;color:#fff;"> <h4 class="text-success" style="color:#fff;">Cancelled  <br> <span  id="cancelledStatus">0</span></h4></td>
													<td style="width: 25%; background-color:#996532; text-align: center; padding: 20px 80px;color:#fff;"> <h4 class="text-success" style="color:#fff;">Not Attended  <br> <span  id="notAttendedStatus">0</span></h4></td>												
												</tr>
											</table>
										</div>
									</div>
										
								<!--Swadhin-->
								<div class="panel panel-default m_top10 panelWhite" id="appointmentReqBlock">
									<div class="panel-heading b_bottom0">
										<h4 class="text-success">ALL APPOINTMENT REQUEST MEMBER
											<span class="pull-right" style="cursor:pointer;font-size:13px;" id="backToReqBlock">
												<u><i class="glyphicon glyphicon-arrow-left"></i> BACK TO APPOINTMENT LABEL</u>
											</span>
										</h4>
									</div>
                                        <div class="panel-body">
											<div class="row">
												<!--<div class="col-md-8" style="padding-right:0px;">
													<input class="form-control" type="text"/>
												</div>
												<div class="col-md-2" style="padding-left:0px">
													<button class="btn btn-success btn-block">SEARCH</button>
												</div>-->
												<div class="col-md-2 col-md-offset-10">
					                               <input type="button" value="Export to Excel" onClick="generateToExcel()" class="btn btn-success pull-right"/>
												</div>
											</div>
                                        	<div class="table-responsive m_top10">
												<div id="appointmentListId" ></div>
												<div id="paginationDivId" ></div>	
											</div>
                                    </div>
                                  </div>
							  </div>
							</div>
                            <div class="row searchDivCls commonDivCls" style="display:none">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="row">
										<div class="col-md-12">
											<div class="block">
												<!-- style="color:#5cb85c"-->
												<h4 class="text-success">ADD APPOINTMENT REQUESTES TO LABEL</h4>
											</div>
										</div>
									</div>
                                	<div class="block">
									
										<div class="row">
											<div class="col-md-3">
												<label class="radio-inline"><input type="radio" name="aptRequestedName"   id="aptPrefferRadioId" value="1" checked/>Appointment Prefferable Dates</label>
											</div>
											<div class="col-md-3">
												<label class="radio-inline"><input type="radio" name="aptRequestedName"   id="aptRequestedRadioId" value="2"/>Appointment Requested Dates</label>
											</div>
										</div>
                                    	<div class="row m_top10">
										
											<div class="col-md-3">
												<label>Date Range</label>
												<div class="input-group inputSearch">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-calendar"></i>
														<span class="caret"></span>
													</span>
													<input type="text" class="form-control" id="addMembersFromDateId">
												</div>
											</div>
										
										<!--<div class="col-md-3">
											<label>To Date</label>
											<div class="input-group inputSearch">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-calendar"></i>
													<span class="caret"></span>
												</span>
												<input type="text" class="form-control" id="addMembersToDateId">
											</div>
										</div>-->
										
                                            <!--<div class="col-md-3">
                                            	<label>Designation<span style='color:red'> &nbsp * </span></label>
                                                <select class="designationListCls errClearCls" id="manageAppDesigId"></select>
												<span style='color:red' id="appDesigErrId"></span>
                                            </div>-->
											
											<div class="col-md-3">
												<label>Candidate Type</label><span style='color:red'> &nbsp * </span>
												<select class="addCandidateTypeCls form-control" id="candidateTypeAddSelId">
													<option value="select">Select Candidate Type</option>
													<option value="0">All</option>
												</select>
												<span class="addErrCandidateTypeAddCls validateClr"></span>
											</div>
											<div class="col-md-3">
												<label>Designation</label><span style='color:red'> &nbsp * </span>
												<select class="designationListCls errClearCls" id="manageAppDesigId">
													<option value="select">Select Designation</option>
													<option value="0" selected>All</option>
												</select>
												<span style='color:red' id="appDesigErrId"></span>
											</div>
											
                                            <div class="col-md-3">
                                            	<label>Appointment Priority Type<span style='color:red'> &nbsp * </span></label>
                                                <select class="manageAppTypeCls errClearCls" id="manageAppTypeId"></select>
												<span style='color:red' id="appPrrtyErrTypId"></span>
                                            </div>
										</div>
										<div class="row">
                                            <div class="col-md-3 m_top10">
                                            	<label>Appointment Status<span style='color:red'> &nbsp * </span></label>
                                                <select class="manageAppStatusCls errClearCls" id="manageAppStatusId"></select>
												<span style='color:red' id="appStatusErrId"></span>
                                            </div>
                                          <div class="col-md-3 m_top10">
                                            	<label>Select District<span style='color:red' >&nbsp *</label>
                                                <select id="manageAppDistId" class="dropkickClass">
                                                	<option value="0">Select District</option>
                                                </select>
												</span><span style='color:red' id="appDistErrId"></span>
                                          </div>
                                          <div class="col-md-3 m_top10">
                                            	<label>Select Constituency<span style='color:red'>&nbsp *</span></label>
                                                <select id="manageAppConstId" class="dropkickClass">
                                                	<option value="select">Constituency Name</option>
													<option value="0" selected>ALL</option>
                                                </select>
												<span style='color:red' id="appConstErrId"></span>
                                          </div>
                                          <!--<div class="col-md-12 m_top10">
                                            	<label class="checkbox-inline">
                                                	<input type="checkbox" class="checkboxCls" id="mnthChckbxId" value="currentMonth">This Month(Any Date)
                                                </label>
                                                <label class="checkbox-inline">
                                                	<input type="checkbox" class="checkboxCls " id="anyDtChckbxId" value="anyDate">Any Date
                                                </label><span style='color:red' id="checkBoxErrId">
												
                                          </div>-->
                                          <div class="col-md-3">
                                            	<button class="btn btn-success" style="margin-top: 33px;" id="searchAppointmentdetailsId" >VIEW</button>
                                            	<img src="images/search.gif" style="display:none;"  id="ajaxImgForApntSearchId"/> 
                                          </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          <div class="row">
                            	<div class="col-md-12">
                                	<div class="appointmentRequestedMembers m_top10 commonDivCls" id="appointmentRequestedMembersId">
									</div>
                                </div>
                          </div>
                            <div class="row appointmentsViewDivCls commonDivCls" style="display:none;"></div>
                            <div class="row appointmentsUpdateDivCls commonDivCls" style="display:none;">
                            	<div class="col-md-12">
                                	<div class="block">
                                    	<h4 class="text-success" style="margin-bottom:10px;">UPDATE LABEL MEMBER ( <span id="updateLabelNameSpanId"></span> )</h4>
										<div id="updateAppointmentsForLabelDivId"></div>
                                     </div>
                                </div>
                            </div>
							<div class="row appointmentsDeleteDivCls commonDivCls" style="display:none;"></div>
						</div>
						<div role="tabpanel" class="tab-pane" id="settings">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
                                	<div class="block">
                                    	<div class="row">
                                        	<div class="col-md-6">
                                            	
                                                <select class="dropkickClass" id="appointmentLabelToGetSlotsId">
                                                	<option value="0">Select Label</option>
                                                </select>
												<div id="timeSlotsErrId"></div>
                                            </div>
                                            <div class="col-md-2">
                                            	<button class="btn btn-success btn-block showTimeSlotsCls " id="showTimeSlotsId">VIEW</button>
                                            </div>
                                            <img id="apptRqstMemberAjax" src="images/icons/loading.gif" style="display:none; height:20px;"/>
                                        </div>
                                    </div>
								</div>
							</div>
                            <div class="row">
								<div id="confirmAppointmentsDivId"></div>
								<div class="col-md-12 changeClass">
									<div class="block">
										<h4 class="text-success">
											CREATE APPOINTMENT TIME SLOT
											<!--<button class="btn btn-success pull-right">VIEW BOOKED TIME SLOTS</button>-->
										</h4>
										<div class="row">
											<div class="col-md-12">
												<div class="row">
													<div class="col-md-12">
														<div id="timeSlotsWarnId" class="validateClr"></div>
														<div class="pluginTable" id="pluginTableId">
															<ul class="row">
																<li class="col-md-2 col-xs-4 col-sm-2">
																	<table class="table tablePluginDate" id="tablePluginDateId">
																		
																	</table>
																</li>
																<li class="col-md-10 col-xs-8 col-sm-10">
																	<table class="table table-bordered tablePlugin" id="tablePluginId">
																	
																	</table>
																</li>
															</ul>
															<div class="row">
																<div class="col-md-12">
																	<p class="pull-right"><span class="boxIcon">&nbsp;</span>Appointment Booked Time Slot</p>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												 <div class="confirmAppointmentBlock">
													<div class="row">
														<div class="col-md-12">
															<div class="drophere">
																<div id="confirmAppointmentBlockDropId" class="confirmAppointmentsDropBlock" style="height:150px;">
																	<h4 class="deleteTag">DROP HERE</h4>
																</div>
															</div>
														</div>
													</div>
													<div class="row m_top20">
														<div class="col-md-12">
															<div style="background:#F3f3f3;margin:0px -10px;padding:12px 0px;" class="row">
																<div class="col-md-4">
																	<label>Select Date</label>
																	<div class="input-group inputSearch">
																		<span class="input-group-addon">
																			<i class="glyphicon glyphicon-calendar"></i>
																			<span class="caret"></span>
																		</span>
																		<input type="text" class="form-control" id="appointmentDateSlotId">
																	</div>
																</div>
																<div class="col-md-3">
																	<label>From Time</label><span style='color:red'> &nbsp * </span>
																	<div class="input-group inputSearch">
																		<span class="input-group-addon">
																			<i class="glyphicon glyphicon-time"></i>
																			<span class="caret"></span>
																		</span>
																		<input type="text" id="fromTimeId" class="form-control">
																	</div>
																</div>
																<div class="col-md-3">
																	<label>To Time</label><span style='color:red;width:500px'> &nbsp * &nbsp </span>
																	<div class="input-group inputSearch">
																		<span class="input-group-addon">
																			<i class="glyphicon glyphicon-time"></i>
																			<span class="caret"></span>
																		</span>
																		<input type="text" id="toTimeId" class="form-control">
																	</div>
																</div>
																<div class="col-md-2">
																	<button class="btn btn-success m_top25" id="setTimeSlotBtnId">SET</button>
																	<img src="images/search.gif" style="display:none;" id="ajaxImgForTimeSlotId"/>
																</div>
																<div id="errorDivForTimeSlotId" class="validateClr m_left16"  ></div>
															</div>
														</div>
														
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12 m_top20" id="appointmentMembersId"></div>
										</div>
									</div>
								</div>
                            </div>
						</div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade bs-example-modal-sm" id="createLabelModelId" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body" style="padding:25px;">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<label>Enter New Label Name</label>
		<input type="text" class="form-control" id="labelNameId">
		<div id="errLabelName"></div>
		<label class="m_top10">Select Date</label>
		<div class="input-group inputSearch">
			<span class="input-group-addon">
				<i class="glyphicon glyphicon-calendar"></i>
			</span>
			<input class="form-control" class="text" id="modalDateId">
		</div>
		<button class="btn btn-success btn-block m_top10" id="createNewLabelId">CREATE NEW LABEL</button>
		<div id="successDiv"></div>
	  </div>
    </div>
  </div>
</div>
<jsp:include page="appointmentCandidateHistory.jsp" flush="true"/>

<script src="dist/2016DashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/js/AppointmentScreenBootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="dist/Appointment/MultiDatePicker/js/jquery-ui-1.11.1.js" type="text/javascript"></script>
<script src="dist/Appointment/MultiDatePicker/js/jquery-ui.multidatespicker.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="dist/Appointment/DragDrop/Sortable.js" type="text/javascript"></script>
<script type="text/javascript">
/* Drag and Drop */

 
  Sortable.create(confirmAppointmentBlockDropId,{
      filter: '.hidelabel',
      onFilter: function (evt) {
		 var cloningData = $(evt.item.parentNode).html();
		// $("#dragId").html(cloningData);
		 $("#dragId").append(cloningData);
		 $("#dragId").find(".manageAppViewPanelClass").addClass("newClass");
		// $("#dragId").find(".panel-default").addClass("newClass")
		evt.item.parentNode.removeChild(evt.item);
		if($("#confirmAppointmentBlockDropId").has( ".manageAppViewPanelClass" ))
		{
			$("#confirmAppointmentBlockDropId").append("<h4 class='deleteTag' style='height:150px;'>DROP HERE</h4>")
		}
      },
      setData: function (dataTransfer, dragEl) {
        dataTransfer.setData('Text', dragEl.textContent);
      },
      //group: "QuestionnOptions",
	  group: { name: "confirmAppointmentsBlock", pull: false, put: true},
      animation: 150,
      store: {
        get: function (sortable) {
          var order = localStorage.getItem(sortable.options.group);
          return order ? order.split('|') : [];
        },
        set: function (sortable) {
          var order = sortable.toArray();
          localStorage.setItem(sortable.options.group, order.join('|'));
        }
      },
      onAdd: function (evt){console.log('onAdd.editable:', [evt.item, evt.from]);
		//$("#confirmAppointmentBlockDropId").find(".deleteTag").remove();
		//$("#confirmAppointmentBlockDropId").css("height","");
	
	   var oldBlock = $('#confirmAppointmentBlockDropId > :not(.newClass)').html().trim();
	   var appointmentId=$('#confirmAppointmentBlockDropId > :not(.newClass)').attr("attr_appointment_id");
		$('#confirmAppointmentBlockDropId > :not(.newClass)').remove();
		$("#confirmAppointmentBlockDropId").find(".manageAppViewPanelClass").removeClass("newClass");
		
		if(oldBlock!="DROP HERE"){
			 var str='';
			  str+='<div class="panel panel-default manageAppViewPanelClass newClass" attr_appointment_id="'+appointmentId+'" draggable="false" style="">';
			  str+=oldBlock;
			  str+='</div>';
			  $("#dragId").append(str);
		}
	  },
      onUpdate: function (evt){ console.log('onUpdate.editable:', [evt.item, evt.from]);},
      onRemove: function (evt){ console.log('onRemove.editable:', [evt.item, evt.from]); },
      onStart:function(evt){console.log('onStart.editable:', [evt.item, evt.from]);},
      onSort:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
      onEnd: function(evt){ console.log('onEnd.editable:', [evt.item, evt.from]);}
  });
  
/* Drag and Drop END */
//getTotalAppointmentStatus();
function getTotalAppointmentStatus(){
	
	$("#todayAppointmentsId").html('');
	
	$("#todayAptLoadingId").show();
	
	var aptUserId = $("#appointmentUserSelectBoxId").val();
		var jsObj = {
			aptUserId:aptUserId
		}
	$.ajax({
		type : 'GET',
		url : 'getStatusWiseCountsOfAppointmentsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
	$("#todayAptLoadingId").hide();
		if(result != null){
			buildTotalAppointmentStatusForToday(result);
			//buildJSONForAppStatus(result);
			//buildTotalAppointmentStatus(result);
		}
		
	});     
}
function getAppointmentStatusCounts(){
	
	$("#totalAppointmentsId").html('');
	
	$("#totalAptLoadingId").show();
	
	var aptUserId = $("#appointmentUserSelectBoxId").val();
		var jsObj = {
			aptUserId:aptUserId
		}
	$.ajax({
		type : 'GET',
		url : 'getAppointmentStatusCountsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
	$("#totalAptLoadingId").hide();
		if(result != null){
			buildJSONForAppStatus(result);
			buildTotalAppointmentStatus(result);
		}
		
	});     
}

var color = ["#2095F4","#98CCCA","#673301","#650199","#4EAF50","#FF9800","#CC0001","#9800CD","#656533"];

function buildJSONForAppStatus(result){	

    var jsonObj = [];
	var flag=false;
	for(var i in result){
		if(result[i].statusCount>0)
			flag = true;
		jsonObj.push({"name":result[i].status,"y":result[i].statusCount,"color":color[i%9]});
	}
	if(flag==true){
		buildChartForAppStatus(jsonObj);
	}
	else{
		$('#LineChart').html("<div class='col-xs-12 m_top30' style='text-align:center;'><h4>NO DATA AVAILABLE</h4></div>");
	}
		
}

function buildChartForAppStatus(jsonObj) {
	var flag = false;
	
    $('#LineChart').highcharts({
        chart: {
            type: 'pie'
        },
        title: {
            text: ''
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },

        tooltip: {
			 pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>',
             headerFormat: '<span style="font-size:11px"></span>'
           
        },
        series: [{
            name: 'count',
            colorByPoint: true,
            data: jsonObj
        }],
      
    });

}

function buildTotalAppointmentStatus(result){
	
	var totalApptCount =0;
	var totalUniqueMembersCount=0;
	
	if(result!=null && result.length>0){
		for(var i in result){
			totalApptCount = totalApptCount + result[i].statusCount;
			totalUniqueMembersCount=totalUniqueMembersCount+result[i].membersCount;
		}
	}
	
	var str='';
		str+='<thead>';
		str+='<th>TOTAL APPOINTMENTS</th>';
		if(totalApptCount!=0){
			
			str+='<th>'+totalApptCount+'</th>';
		}else{
			str+='<th> - </th>';
		}
		if(totalUniqueMembersCount!=0){
			str+='<th>'+totalUniqueMembersCount+' (UNIQUE MEMBERS)</th>';
		}else{
			str+='<th> - UNIQUE MEMBERS</th>';
		}
		
		str+='</thead>';
		str+='<tbody>';
	for(var i in result){
		
		
		str+='<tr style="color:'+color[i%9]+'">';
			str+='<td><span class="columnChart" style="background-color:'+color[i%9]+'"></span>'+result[i].status+'</td>';
			if(result[i].statusCount == 0){
				str+='<td style="text-align:center;"> - </td>';
			}else{
				str+='<td style="text-align:center;">'+result[i].statusCount+'</td>';
			}
			
			if(result[i].membersCount == 0){
				str+='<td style="text-align:center;"> - </td>';
			}else{
				str+='<td style="text-align:center;">'+result[i].membersCount+'</td>';
			}
			
		str+='</tr>';
	}
	str+='</tbody>';
	$("#totalApptStatusCounts").html(totalApptCount);
	$("#totalAppointmentsId").html(str);
}

function buildTotalAppointmentStatusForToday(result){
	var str='';
	$.each(result.statusList,function(index,value){		
		str+='<tr style="color:'+color[index%9]+'">';
			str+='<td>'+value.status+'</td>';
			str+='<td>'+value.totalCount+'</td>';
		str+='</tr>';	
	});
	$("#todayAppointmentsId").html(str);
}

	$(document).on("click",".appointmentSettings",function(e){
		$(".updateAppointment").hide()
		$(".messageBlock").hide()
		$(".appointmentSettingsBLock").hide()
		e.stopPropagation()
		$(this).parent().parent().find(".updateAppointment").show()
		})
	$(document).on("click","body",function(){
		$(".updateAppointment").hide()
		$(".appointmentSettingsBLock").hide()
		$(".messageBlock").hide()		
		});
	$(document).on("click",".settingsIcon",function(e){
		e.stopPropagation();
		$(".updateAppointment").hide()
		$(".messageBlock").hide()
		$(".appointmentSettingsBLock").hide()
		$(this).parent().parent().parent().find(".appointmentSettingsBLock ").show()
	})
	$(document).on("click",".messageIcon",function(e){
		e.stopPropagation();
		$(".updateAppointment").hide()
		$(".messageBlock").hide()
		$(".appointmentSettingsBLock").hide()
		$(this).parent().parent().find(".messageBlock").show()
	})
	$(document).on("click",".appointmentSettingsBLock,.messageBlock,.updateAppointment",function(event){
		event.stopPropagation();
	});

var cloneCount=0;

$(document).on("click","#addOneBlock",function(){

	var e = $(".cloneBlock").clone();
	
	
	e.css("display","block");
	e.attr("id",'block'+cloneCount);
	e.addClass("validateCls");
	e.find(".cloneNameCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].name');
	e.find(".cloneNameCls").attr("id",'candidateNameId'+cloneCount);	
	e.find(".cloneErrCandidateNameCls").attr("id",'cloneErrCandidateNameId'+cloneCount);	
	
	e.find(".cloneDesignationCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].designationId');
	e.find(".cloneDesignationCls").attr("id",'designationSelId'+cloneCount);
	e.find(".cloneDesignationCls").attr("attr_val",cloneCount);	
	e.find(".cloneErrCandidateDesgCls").attr("id",'cloneErrCandidateDesgId'+cloneCount);
	
	e.find(".cloneMobileCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].mobileNo');
	e.find(".cloneMobileCls").attr("id",'mobileNoId'+cloneCount);	
	e.find(".cloneErrCandidateMobileCls").attr("id",'cloneErrCandidateMobileId'+cloneCount);
	
	e.find(".cloneMembershipNumCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].membershipNum');
	e.find(".cloneMembershipNumCls").attr("id",'membershipNumId'+cloneCount);
	e.find(".cloneErrCandidateMemShipCls").attr("id",'cloneErrCandidateMemShipId'+cloneCount);
	
	
	e.find(".cloneVoterIdCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].voterCardNo');
	e.find(".cloneVoterIdCls").attr("id",'voterCardNoID'+cloneCount);
	e.find(".cloneErrCandidateVoterCls").attr("id",'cloneErrCandidateVoterId'+cloneCount);
	
	e.find(".cloneLocationScopeCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].locationScopeId');
	//e.find(".cloneDistrictCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].districtId');
	//e.find(".cloneConstituencyCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].constituencyId');
	 
	 
	e.find(".cloneLocationScopeCls").attr("id",'locationScopeSelId'+cloneCount);
	e.find(".cloneLocationScopeCls").attr("attr_val",cloneCount);
	//e.find(".cloneLocationScopeCls").attr("id","locationScopeSelId"+cloneCount);
	e.find(".cloneErrCandidateLcScopeCls").attr("id",'cloneErrCandidateLcScopeId'+cloneCount);
	
	e.find(".cloneLocationScopeCls").attr("onChange","showhideLocationBoxes("+cloneCount+")");
	
	e.find(".cloneDistDivCls").attr("id","districtId"+cloneCount+"DivId");		
	e.find(".cloneDistDivCls").addClass("locationCls"+cloneCount);
	
	
	e.find(".cloneDistrictCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].districtId');
	e.find(".cloneDistrictCls").attr("id",'districtId'+cloneCount);
	e.find(".cloneErrCandidateDistrictCls").attr("id","cloneErrCandidateDistrictId"+cloneCount);
	e.find(".cloneDistrictCls").attr("onChange",'getConstituencies('+cloneCount+');');
	e.find(".cloneDistrictCls").attr("attr_val",cloneCount);
	
	e.find(".cloneConstDivCls").attr("id","constituencyId"+cloneCount+"DivId");
	e.find(".cloneConstDivCls").addClass("locationCls"+cloneCount);
	
	e.find(".cloneConstituencyCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].constituencyId');
	e.find(".cloneConstituencyCls").attr("id",'constituencyId'+cloneCount);
	e.find(".cloneErrCandidateConstCls").attr("id",'cloneErrCandidateConstId'+cloneCount);
	e.find(".cloneConstituencyCls").attr("onChange",'getMandamMuncipalties('+cloneCount+');');
	e.find(".cloneConstituencyCls").attr("attr_val",cloneCount);
	
	e.find(".cloneMandalDivCLs").attr("id","tehsilId"+cloneCount+"DivId");
	e.find(".cloneMandalDivCLs").addClass("locationCls"+cloneCount);

	e.find(".cloneMandalCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].tehsilId');
	e.find(".cloneMandalCls").attr("id",'tehsilId'+cloneCount);
	e.find(".cloneErrCandidateMandalCls").attr("id",'cloneErrCandidateMandalId'+cloneCount);
	e.find(".cloneMandalCls").attr("onChange",'getVillageWard('+cloneCount+');');
	e.find(".cloneMandalCls").attr("attr_val",cloneCount);
	
	e.find(".cloneVillageDivCls").attr("id","villageId"+cloneCount+"DivId");
	e.find(".cloneVillageDivCls").addClass("locationCls"+cloneCount);
	
	e.find(".cloneVillageCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].villageId');
	e.find(".cloneVillageCls").attr("id",'villageId'+cloneCount);
	e.find(".cloneErrCandidateVillageCls").attr("id",'cloneErrCandidateVillageId'+cloneCount);
	e.find(".cloneVillageCls").attr("attr_val",cloneCount);
	
	e.find(".cloneCandidateIdCls").attr("id","candidateId"+cloneCount);
	e.find(".cloneCandidateIdCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].appointCandidateId');
	
	e.find(".cloneCandiImageUrlCls").attr("id","candiImageUrlId"+cloneCount);
	e.find(".cloneCandiImageUrlCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].candiImageUrl');
	e.find(".closeIcon").attr("clone_block_count",cloneCount);
	
	e.find(".cloneCandidateTypeCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].candidateTypeId');
	e.find(".cloneCandidateTypeCls").attr("id",'candidateTypeSelId'+cloneCount);
	e.find(".cloneCandidateTypeCls").attr("attr_val",cloneCount);	
	e.find(".cloneErrCandidateTypeCls").attr("id",'cloneErrCandidateTypeId'+cloneCount);
	
	e.find(".cloneCandidateTypeCls").attr("onChange",'getDesignationsByTypeForChange('+cloneCount+');');
	
	e.removeClass("cloneBlock");
	$("#moreCandidatesDivId").append(e);
	
	 var candType = "candidateTypeSelId"+cloneCount;
	$("#"+candType).dropkick();
	var select7 = new Dropkick("#"+candType);
	select7.refresh();
	
	 var t = "designationSelId"+cloneCount;
	$("#"+t).dropkick();
	var select2 = new Dropkick("#"+t);
	select2.refresh();   
	
	var loc = "locationScopeSelId"+cloneCount;
	var select3 = new Dropkick("#"+loc);
	select3.refresh();
	
	var dis = "districtId"+cloneCount;
	var select4 = new Dropkick("#"+dis);
	select4.refresh();
	
	var con = "constituencyId"+cloneCount;
	var select5 = new Dropkick("#"+con);
	select5.refresh();
	
	var man = "tehsilId"+cloneCount;
	var select6 = new Dropkick("#"+man);
	select6.refresh();
	
	var village = "villageId"+cloneCount; 
	var select6 = new Dropkick("#"+village);
	select6.refresh();
	
	cloneCount=cloneCount+1;
});

$(document).on("click",".closeIcon",function(){
	$(this).parent().parent().remove();
	var blockCount = $(this).attr("clone_block_count");
	$("#uncheck"+blockCount).parent().find(".apptDetailsDiv").prop('checked', false); 
});
$(".dropkickClass").dropkick();
	$(document).ready(function(){
		//getAppointmentLabelsAction						
			setTimeout(function(){ 
			/* balu */
				//getAppointmentLabels();					
				getTotalAppointmentStatus();
				getAppointmentStatusCounts();
				getCandidateDesignation();
				getDistricts();
				getAppointmentCreatedUsers();
				getAppointmentStatusList();
				getAppointmentPriority();
				searchTypeRadioCls();
				getAllCandidateTypes();
				
			}, 1000);
			getAppointmentsLabelStatus("onload");
	
			
	});
	getAppointmentUsersDtls();
	//getCandidateDesignation();
	function getCandidateDesignation(){
		$.ajax({
			type : 'GET',
			url : 'getCandidateDesignation.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForCreateApp(result);
			}
			
		});
	}
	function buildDesignationForCreateApp(result){
			$("#designationListId  option").remove();
			$("#designationListId").append('<option value="0">Select Designation</option>');
			 $(".cloneDesignationCls option").remove(); 
			$(".cloneDesignationCls").append('<option value="0">Select Designation</option>'); 
			
			//$("#manageAppDesigId  option").remove();
			//$("#manageAppDesigId").append('<option value="select">Select Designation</option>');
			//$("#manageAppDesigId").append('<option value="0" selected>ALL</option>');
			for(var i in result){
				$("#designationListId").append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
				//$("#manageAppDesigId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$(".cloneDesignationCls").append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
			}
			 /* $(".designationListCls").dropkick();
			 var select = new Dropkick("#designationListId");
			select.refresh(); */ 
			 $("#manageAppDesigId").dropkick();
			var select1 = new Dropkick("#manageAppDesigId");
			select1.refresh();  
			
			
			
	} 
	

	function getAppointmentStatusList(){
			$.ajax({
			type : 'GET',
			url : 'getAppointmentStatusList.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				buildAppointmentStatusList(result);
			}
			
		}); 
	}
		

	function buildAppointmentStatusList(result){
			$("#manageAppStatusId  option").remove();
			$("#manageAppStatusId").append('<option value="select">Select Appointment Status</option>');
			$("#manageAppStatusId").append('<option value="0" selected>ALL</option>');
			for(var i in result){
				$("#manageAppStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$(".manageAppStatusCls").dropkick();
			var select = new Dropkick("#manageAppStatusId");
			select.refresh();
	}
	function getAppointmentPriority(){
		
		$.ajax({
			type : 'GET',
			url : 'getAppointmentPriority.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				buildAppointmentPriorityList(result);
				
			}				
		});
	}
	function buildAppointmentPriorityList(result){
		$("#manageAppTypeId  option").remove();
		$("#manageAppTypeId").append('<option value="select">Select Priority</option>');
		$("#manageAppTypeId").append('<option value="0" selected>ALL</option>');
		$("#createAppTypeListId  option").remove();
		$("#createAppTypeListId").append('<option value="0">Select Appointment Priority Type</option>');
		for(var i in result){
			$("#manageAppTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			$("#createAppTypeListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$(".manageAppTypeCls").dropkick();
		var select = new Dropkick("#manageAppTypeId");
		select.refresh();
		
		var select1 = new Dropkick("#createAppTypeListId");
		select1.refresh();
	}
	
	function savingAppointment(){
		clearAllValidationCls();
		setTimeout(function () {
			$("#savingStatusDivId").html('');
		}, 2500);
		var flag = validateSavingDetails();
		
		if(!flag){
			
			$("#appntCreateAjax").css("display","block");			
			$("#dateTypeText").val($('input[name=dateTypeRadio]:checked').val());
			var temp = $("#appointmentUserSelectBoxId option:selected").attr("attr_unique_code")+"_"+$("#appointmentUserSelectBoxId").val();
			$("#uniqueCode").val(temp);
			
			var uploadHandler = {
				upload: function(o) {
					$("#appntCreateAjax").css("display","none");
					uploadResult = o.responseText;
					console.log(uploadResult);
					showStatus(uploadResult);
				}
			};
	
			YAHOO.util.Connect.setForm('saveAppointment',true);
			YAHOO.util.Connect.asyncRequest('POST','appointmentSavingAction.action',uploadHandler);
		}
	}
	
	function showStatus(myResult,num){
		var result = myResult.split("<pre>");
		var result1 = result[1].split("</pre>");
		if(result1[0] == "success"){
			setTimeout(function(){
			$("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Appointment Created Successfully.</span>").delay( 2500 );
			}, 1000);
			//$( ".closeIcon" ).trigger( "click" );
			$("#moreCandidatesDivId").html('');
			$( "#multiDate" ).multiDatesPicker("resetDates");
			cloneCount = 0;
			saveFieldsEmpty();
		}else{
			setTimeout(function(){
			$("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Appointment Creation Failed. Please Try Again.</span>").delay( 2500 );
			}, 1000);
		}
	}
	function saveFieldsEmpty(){
		$("#createAppTypeListId").val(0);
		$("#createAppTypeListId").dropkick('reset');
		$("#selectManualDateId").trigger('click');
		$("#appointmentReasonId").val("");
		//$("#searchTypeId").val(0);
		$("#searchTypeId").dropkick('reset');
		$("#apptmemberDetailsDiv").html("");
		$("#searchValueId").val("");
	}
	function showhideLocationBoxes(num){
		$(".locationCls"+num).css("display","none");
		var id = $("#locationScopeSelId"+num).attr("attr_val");
		var selId = "locationScopeSelId"+num;
		if($("#"+selId).val()==3){
			var divId = "districtId"+id+"DivId";
			$("#"+divId).css("display","block");
		}else if($("#"+selId).val()==4){
			var divId = "districtId"+id+"DivId";
			$("#"+divId).css("display","block");
			var divId1 = "constituencyId"+id+"DivId";
			$("#"+divId1).css("display","block");
		}else if($("#"+selId).val()==5 || $("#"+selId).val()==7){
			var divId = "districtId"+id+"DivId";
			$("#"+divId).css("display","block");
			var divId1 = "constituencyId"+id+"DivId";
			$("#"+divId1).css("display","block");
			var divId2	 = "tehsilId"+id+"DivId";
			$("#"+divId2).css("display","block");
		}else if($("#"+selId).val()==6 || $("#"+selId).val()==8){
			var divId = "districtId"+id+"DivId";
			$("#"+divId).css("display","block");
			var divId1 = "constituencyId"+id+"DivId";
			$("#"+divId1).css("display","block");
			var divId2	 = "tehsilId"+id+"DivId";
			$("#"+divId2).css("display","block");
			var divId3	 = "villageId"+id+"DivId";
			$("#"+divId3).css("display","block");
		}
	}
	
	//getDistricts();
	//var distArr=[];
	function getDistricts(){
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForAppointmentsAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){
			var str='';
			var str1='';
			str+='<option value="select">Select District</option>';
			str1+='<option value="select">Select District</option>';
			str+='<option value="0" selected>ALL</option>';
			if(result != null && result.length > 0){
				for(var i in result){
					// var obj={id:result[i].id,value:result[i].name};
					 //distArr.push(obj);
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
					str1+='<option value="'+result[i].id+'">'+result[i].name+'</option>';					
				}
			}
			$("#manageAppDistId").html(str);
			$("#manageAppDistId").dropkick();
			var select1 = new Dropkick("#manageAppDistId");
			select1.refresh();
			$(".cloneDistrictCls").html(str1);
			
			
		});
	}
	
	$('#manageAppDistId').dropkick({
      change: function () {
		
		   var distId = $("#manageAppDistId").val();
			var jsObj ={
						districtId:distId
						}
						
			$.ajax({
				type : 'post',
				url : 'getConstituenciesForADistrictAction.action',
				dataType: 'json',
				data:    {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				var str='';
				str+='<option value="select">Select Constituency</option>';
				str+='<option value="0" selected>ALL</option>';
				if(result != null && result.length > 0){
					for(var i in result){
						str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';	
					}
				}
				$("#manageAppConstId").html(str);
				$("#manageAppConstId").dropkick();
				var select = new Dropkick("#manageAppConstId");
				select.refresh();
			});
	    }
	});
	
	function getConstituencies(num){
		var distId = $("#districtId"+num).val();
		var jsObj ={
					districtId:distId
					}
					
		$.ajax({
			type : 'post',
			url : 'getConstituenciesForADistrictAction.action',
			dataType: 'json',
			data:    {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Constituency</option>';
			if(result != null && result.length > 0){
				for(var i in result){
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';	
				}
			}
			var id="constituencyId"+num;
			$("#"+id).html(str);
			$("#"+id).dropkick();
			var select = new Dropkick("#"+id);
			select.refresh();
		});
	}
	
	function getMandamMuncipalties(num){
		var constId = $("#constituencyId"+num).val();
		var locationScopeId = $("#locationScopeSelId"+num).val();
		var jsObj ={
					constId:constId,
					locationScopeId:locationScopeId
					}
					
		$.ajax({
			type : 'GET',
			url : 'getMandamMuncipaltiesAction.action',
			dataType: 'json',
			data:    {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Mandal/Muncipality</option>';
			if(result != null && result.length > 0){
				for(var i in result){
					str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';	
				}
			}
			
			var id="tehsilId"+num;
			$("#"+id).html(str);
			$("#"+id).dropkick();
			var select = new Dropkick("#"+id);
			select.refresh();
		});
	}
	
	function getVillageWard(num){
		var jsObj ={
					tehsilId:$("#tehsilId"+num).val()
					}
					
		$.ajax({
			type : 'GET',
			url : 'getVillageWardAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Village/Ward</option>';
			if(result != null && result.length > 0){
				for(var i in result){
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';	
				}
			}
			
			var id="villageId"+num;
			$("#"+id).html(str);
			$("#"+id).dropkick();
			var select = new Dropkick("#"+id);
			select.refresh();
		});
	}
	
	$(document).on('click','#createNewLabelId',function(){
		$("#successDiv").html("");
		$("#successDiv").show();
		$("#errLabelName").html("");
		var lblName = $("#labelNameId").val();
		if(lblName=="" && lblName.length==0){
			$("#errLabelName").html("please enter label Name.").css("color","red");
			return;
		}
		
		var fromDate='';
		var toDate='';
		var dateStr = $("#modalDateId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var aptuserId = $("#appointmentUserSelectBoxId").val();
		var jobj = {
			labelName	:	$("#labelNameId").val(),
			fromDate	:	fromDate,
			toDate		:	toDate,
			aptuserId	:	aptuserId
		}
		$.ajax({
			  type     : "POST",
			  url      : "createAppointmentLabel.action",
			  dataType : "json",
			  data     : {task:JSON.stringify(jobj)}
			}).done(function(result){
				if(result!=null){
					$("#successDiv").html(result.message).css("color","green");
					setTimeout(function(){	$("#successDiv").hide(); },3000);
					$("#labelNameId").val("");
					$("#createLabelModelId").modal('hide');
					getLabelDtls();
				}
		  });     
	});		 
		function getAppointmentUsersDtls(){
		$.ajax({
			  type:'GET',
			  url: 'getAppointmentUsersDtlsAction.action',
			  dataType: 'json',
			  data: {}
		}).done(function(result){
			if(result!=null && result!=0){
				buildAppntmntUsrSlctBx(result);
			}
		});
	}
	function buildAppntmntUsrSlctBx(result){
		$("#appointmentUserSelectBoxId  option").remove();
		for(var i in result){
			$("#appointmentUserSelectBoxId").append('<option attr_unique_code="'+result[i].date+'" value='+result[i].appointmentUserId+'>'+result[i].name+'</option>');
		}
		getSearchDetails();
		/* $("#appointmentUserSelectBoxId").dropkick();
			var select = new Dropkick("#appointmentUserSelectBoxId");
			select.refresh();
			select.selectOne(result[0].appointmentUserId); */
	}
	$(document).on("click",".MngeAppntmntCls",function(){
		$(".commonDivCls").hide();
		$("#selectStsForLabelId").val(1);
		getLabelDtls();
		getAppointmentStatusOverview();
	});
	
	/*Get label details based on selected user.*/
	/* $('#appointmentUserSelectBoxId').dropkick({
		change: function () {
			if(this.value>0){
				getLabelDtls();
			}
		}
	}); */
	
	$('#appointmentUserSelectBoxId').change(function(){
		getLabelDtls();
	});
	
	function getLabelDtls(){
		
		var slctDate='';
		var appntmntUsrId=$("#appointmentUserSelectBoxId").val();
		var status = $("#selectStsForLabelId").val();
		
		var jsObj={
			currentDate:slctDate,
			apptmntUsrId:appntmntUsrId,
			status: status
		}
		$.ajax({
			type : 'GET',
			url : 'getLabelDtlsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result!=null && result!=0){
				buildAppntmntLblResult(result);
			}else{
				$("#buildAppntmntLblTblId").html("<div><p style='color:green;font-size:20px'>No Data Available</p></div>")
			}
		}); 
	}
	function buildAppntmntLblResult(result){
	   var str='';
	  str+='<table class="table table-condensed bg_ff">';
		   str+='<thead>';
				 str+='<th>LABEL NAME</th>';
				 str+='<th>TOTAL</th>';
				 if(result[0].staticStatusList != null && result[0].staticStatusList.length > 0){
					 for(var i in result[0].staticStatusList){
						 if(result[0].staticStatusList[i].appointmentStatusId != 3 && result[0].staticStatusList[i].appointmentStatusId !=4)
							str+='<th>'+result[0].staticStatusList[i].status+'</th>';
					 }
				 }
				 str+='<th>LABEL STATUS</th>';
		   str+='</thead>';                                                      
			  str+='<tbody';                                                   
	  for(var i in result){
				str+='<tr>';
					str+='<td attr_label_id='+result[i].labelId+'>'+result[i].labelName+'</td>';
					var totalCount=0;
					if(result[i].statusList != null && result[i].statusList.length >0){
						for(var j in result[i].statusList){
							totalCount=totalCount+result[i].statusList[j].totalCount;
						}
					}
					str+='<td>'+totalCount+'</td>';
					if(result[i].statusList != null && result[i].statusList.length >0){
						for(var j in result[i].statusList){
							str+='<td>'+result[i].statusList[j].totalCount+'</td>';
						}
					}
					str+='<td>'+result[i].status+'</td>';
					str+='<td>';
					
					if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs viewMembersClass" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="View Appointments Of '+result[i].labelName+'" style="margin-right: 5px;">View</button>';
						}else{
							str+='<button class="btn btn-success btn-xs viewMembersClass" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">View</button>';
						}
						
						str+='<button class="btn btn-success btn-xs addMembersClass" attr_label_name="'+result[i].labelName+'" title="Add Appointments To '+result[i].labelName+'" id="addApptsId" style="margin-right: 5px;">Add Appts</button>';
						if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs updateLableAppointmentsCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="Update Status Of Appointments" style="margin-right: 5px;">Update</button>';
						}else{
							str+='<button class="btn btn-success btn-xs updateLableAppointmentsCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">Update</button>';
						}
						
						if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs labelStatusCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" attr_status="'+result[i].status+'" attr_status_id="'+result[i].statusId+'" title="Change The Status Of '+result[i].labelName+' Label" style="margin-right: 5px;">Status</button>';
						}else{
							str+='<button class="btn btn-success btn-xs labelStatusCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" attr_status="'+result[i].status+'" attr_status_id="'+result[i].statusId+'" disabled style="margin-right: 5px;">Status</button>';
						}
						if(totalCount !=null && totalCount >0 ){
							str+='<button class="btn btn-success btn-xs deleteAppointments" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="Delete Appointments Of '+result[i].labelName+'" style="margin-right: 5px;">Del Appts</button>';
						}else{
							str+='<button class="btn btn-success btn-xs deleteAppointments" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">Del Appts</button>';
						}
						
						
						str+='<i class="glyphicon glyphicon-remove lblDltCls" title="Delete Label '+result[i].labelName+'" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" style="color:red;cursor:pointer;"></i>';
					str+='</td>';
			  str+='</tr>';
	  }
	  str+='</tbody>';   
	  str+='</table>';  
	  $("#buildAppntmntLblTblId").html(str);
	}
	var labelId='';
	var labelName='';
	$(document).on("click",".lblDltCls",function(){
	     labelId=$(this).attr("attr_label_id");
		 labelName=$(this).attr("attr_label_name");
		   showConfirmationBox();
	});
	$(document).on("click","#dlteLblBttnId",function(){
	 var isCheckedDelete=$("#dltChckbxMdlId").is(':checked');
	 var remarks = $("#remarksId").val();
     if(isCheckedDelete==true){
			$("#deleteLabelErr").html(" ");
		}else{
		 $("#deleteLabelErr").html("Please select agree to delete.");	
		 return;
	 }
	 if(remarks.length==0){
		 $("#deleteLabelErr").html("Please enter remarks.");	
		 return;
	 }else{
		 $("#deleteLabelErr").html(" ");
	 }
	 deleteAppointmentLabel(labelId,remarks);
	
		
	});
	function deleteAppointmentLabel(labelId,remarks){
		$("#appntmntLblDltSttsId").html(" ");
		$("#appntmntLblDltSttsId").show();
		var jsObj={
				labelId:labelId,   
				remarks:remarks
			}
			$.ajax({
				type : 'GET',
				url : 'deleteAppointmentLabelAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result!=null && result!=0){
					if(result.message=="success"){
					 $("#appntmntLblDltSttsId").html("<div><p style='color:red'>Label Deleted Successfully");	
                      setTimeout('$("#appntmntLblDltSttsId").hide()', 2000);					 
					}
					getLabelDtls();
				}
		  }); 
	}
function showConfirmationBox(){
		 var str='';
		str+='<div class="modal fade" id="myModal" role="dialog">';
		  str+='<div class="modal-dialog modal-sm">';
				str+='<div class="modal-content">';
				  str+='<button style="margin-left:260px" type="button" class="btn btn-default" data-dismiss="modal">X</button>';
				  str+='<div class="modal-body text-center">';
				  str+='<p class="text-center">'+labelName+'</p>';
				  str+='<p class="text-center m_top10"><b>Are you sure want to delete Label ?</b></p>';
				  str+='<p class="text-center text-success m_top10">Current Status - INPROGRESS</p>';
				  str+='<label class="checkbox-inline text-center m_top10"><input id="dltChckbxMdlId" type="checkbox"/>Agree to delete</label>';
				  str+='<br>';
				  str+='<div class="m_top10">';
				  str+='<input type="text" class="form-control" name="" id="remarksId" placeholder="Remarks">';
				  str+='</div>';
				  str+='<p  style="color:red" id="deleteLabelErr"></p>';
				  str+='<input class="btn btn-success btn-block m_top10" type="button" id="dlteLblBttnId" value="DELETE"/>';
				  str+='<p>*Note : if you select (Agree to delete) total label remove  from appointment label list'; 
				  str+='</div>';
				str+='</div>';
			 str+='</div>';
		 str+='</div>';
		  $("#bldCnfrmtnMdlBoxId").html(str);
		  $("#myModal").modal("show");
	}
	

	
$("#toTimeId").datetimepicker({format: 'LT'})	
$("#fromTimeId").datetimepicker({format: 'LT'})
$("#modalDateId").daterangepicker({singleDatePicker:false});	
$('#modalDateId').val(moment().format('MM/DD/YYYY') + ' - ' + moment().format('MM/DD/YYYY'));	
$("#mngAppntmntsDtPckrId").daterangepicker({singleDatePicker:true});
$(".daterangepicker_end_input").find("input").addClass("form-control");
$(".daterangepicker_start_input").find("input").addClass("form-control");
$("#mngAppntmntsDtPckrId").val("");
$("#multiDate").multiDatesPicker({numberOfMonths: [1,2],minDate:0
})
$("#dashboardSelectDateIds").daterangepicker({opens:"left"});
$('#dashboardSelectDateIds').val(moment().format('MM/DD/YYYY') + ' - ' + moment().format('MM/DD/YYYY'));
$("#appointmentDateSlotId").daterangepicker({singleDatePicker:true});
$('#appointmentDateSlotId').val(moment().format('MM/DD/YYYY'));
$("#addMembersFromDateId").daterangepicker({singleDatePicker:false});
$('#addMembersFromDateId').val(moment().format('MM/DD/YYYY') + ' - ' + moment().format('MM/DD/YYYY'));



	/*This code is used to select only one checkbox while displaying the appointment details.*/	
	 $('#mnthChckbxId').click(function(){
		  var isCheckedMonth=$("#mnthChckbxId").is(':checked');
			if(isCheckedMonth){
			   $('#anyDtChckbxId').attr('checked', false);
		   }
	 });
	 $('#anyDtChckbxId').click(function(){
		  var isCheckedAnyDate=$("#anyDtChckbxId").is(':checked');
			if(isCheckedAnyDate){
			   $('#mnthChckbxId').attr('checked', false);
		   }
	 });
	 
	 function validateFields(){
		 
		 var appDesigId=$("#manageAppDesigId").val();
		 var appPrrtyTypId= $("#manageAppTypeId").val();
		 var appStatusId=$("#manageAppStatusId").val();
		 
		 if(appDesigId==0){
		  $("#appDesigErrId").html("Please select Designation.");
           return;		  
		 }
		  $("#appDesigErrId").html(" ");
		 if(appPrrtyTypId==0){
		  $("#appPrrtyErrTypId").html("Please select Priority Type.");
           return;		  
		 }
		  $("#appPrrtyErrTypId").html(" ");
		 if(appStatusId==0){
		  $("#appStatusErrId").html("Please select Appointment Status.");
           return;		  
		 }
		 $("#appStatusErrId").html(" ");
    	 var isCheckedMonth=$("#mnthChckbxId").is(':checked');
	     var isCheckedAnyDate=$("#anyDtChckbxId").is(':checked');
		 var flag=true;
		 if(isCheckedAnyDate==false && isCheckedMonth==false){
			 $("#checkBoxErrId").html("Please select one checkbox."); 
			 flag=false;
			 return;
		 }
		 if(flag){
			 $("#checkBoxErrId").html(" ");
			  getAppntmntsCnddtDtls();
		 }
	 }
	 function getAppntmntsCnddtDtls(){
		 
		  var isCheckedMonth=$("#mnthChckbxId").is(':checked');
		  var isCheckedAnyDate=$("#anyDtChckbxId").is(':checked');
		  var currentMonth='';
		  var anyDate='';
	  
		   if(isCheckedMonth){
			  currentMonth=$("#mnthChckbxId").val();
		   }
		   if(isCheckedAnyDate){
			  anyDate=$("#anyDtChckbxId").val();
		   }
		   
    	var jsObj={
			candidateDsgntnId:$("#manageAppDesigId").val(),
			appntmntPrprtyId:$("#manageAppTypeId").val(),
			appntmntSttsId:$("#manageAppStatusId").val(),
			currentMonth:currentMonth,
			anyDate:anyDate
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAppntmntsCnddtDtlsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				console.log(result);
				if(result!=null && result!=0){
				  buildAppntmntCnddtDtlsRsult(result);
				}else{
				  $("#appntmntCnddtSttsUlId").html("<div><p style='color:green;font-size:20px'>No record available.")	
				}
		  }); 
	 }
   function buildAppntmntCnddtDtlsRsult(result){
	var str=''; 
	for(var i in result){
	    str+='<li>';
		 str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<span class="requestedCheckbox">';
					str+='<input type="checkbox">';
				str+='</span>';
			str+='</div>';
			str+='<div class="col-md-6">';
				str+='<div class="media">';
					str+='<div class="media-left">';
						str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
						str+='<span class="colorStatus green"></span>';
					str+='</div>';
					str+='<div class="media-body">';
					str+='<p>'+result[i].name+'</p>';
						str+='<p>Contact Number:'+result[i].mobileNo+'</p>';
						str+='<p>Designation   :'+result[i].designation+'</p>';
						str+='<p>Constituency  :'+result[i].constituencyName+'</p>';
						str+='<p>Last Visit    :'+result[i].date+'</p>';
						str+='<p>Appt Type     :'+result[i].priority+'</p>';
						str+='<p>Purpose       :'+result[i].reason+'</p>';
					str+='</div>';
				str+='</div>';
				str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
				str+='<table class="table table-bordered">';
				  if(result[i].appointStatusCountList!=null && result[i].appointStatusCountList.length>0){
				     str+='<tr>';					 
					 for(var j in result[i].appointStatusCountList){
						 str+='<td><h4>'+result[i].appointStatusCountList[j].statusCount+'</h4><p>'+result[i].appointStatusCountList[j].status+'</p></td>'; 
					  }
					  str+='</tr>';
				  }
				  str+='</table>'
				str+='<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>';
				str+='<p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>';
			str+='</div>';
			str+='<div class="col-md-6">';
				str+='<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>';
				str+='<table class="table table-bordered m_top10">';
					str+='<thead>';
				        str+='<th>Appt Last Requested Date</th>';
						str+='<th colspan="2">Appt Status</th>';
					str+='</thead>';
				if(result[i].appointStatusRequestedList!=null && result[i].appointStatusRequestedList.length>0){
					for(var j in result[i].appointStatusRequestedList){
						str+='<tr>';
							str+='<td>'+result[i].appointStatusRequestedList[j].updatedTime+'</td>';
							str+='<td>'+result[i].appointStatusRequestedList[j].status+'</td>';
						str+='</tr>';					
					}
				}
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</li>';
	}
	$("#appntmntCnddtSttsUlId").html(str);
  }	 
  
  $(document).on("click",".dateRadioCls",function(){
	  $("#multiDate").val("");
  });
  
  function showHideSearch(type)
  {
	  if(type == "search")
	  {
		  $(".searchCls").show();
		  $(".advanceSearchCls").hide();
	  }
	  else
	  {
		 $(".advanceSearchCls").show();  
		  $(".searchCls").hide();
	  }
		 
	  
  }
 
	function getDetailsBySrch()
	{
		//clearing the Data Div
		$("#apptmemberDetailsDiv").html("");
		//clearing err Div
		$("#errDigitsId").html(" ");
		
		var searchValue=$("#searchValueId").val();
		var errStr=false;
		//Validations
		if($("#searchTypeId").val()==0){
			 $("#errDigitsId").html("Please Select Search Type");
			 errStr=true;
		}
		else if($("#searchTypeId").val()=="mobileno"){			
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errDigitsId").html("Please enter Mobile No");
					  errStr=true;
				 }	
				else if(searchValue.length != 10 || isNaN(searchValue)){		
					$("#errDigitsId").html("Please Enter Valid Mobile Number");
					 errStr=true;
				}
		}else if($("#searchTypeId").val() == "mebershipno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errDigitsId").html("Please Enter MembershipNo ");
					  errStr=true;
			}else if(searchValue.length !=8 || isNaN(searchValue)){
				  $("#errDigitsId").html("Please Enter valid Membership ");
					  errStr=true;
			}
		}else if($("#searchTypeId").val() == "votercardno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errDigitsId").html("Please Enter Votercardno ");
					  errStr=true;
			}
		}
		
		if(errStr){
			return;
		}else{
				getAppntmntSearchDetails();
				//$(".addattrid").hide();
			}
		
	}
	

	function getAppntmntSearchDetails(){
	  
	  $("#searchMemberAjax").css("display","block");
	  var searchType = $("#searchTypeId").val();
	  var searchValue = $("#searchValueId").val();
		var jsObj={
			searchType:searchType,
			searchValue:searchValue
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAppntmntSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 $("#searchMemberAjax").css("display","none");
				if(result !=null && result.length>0){
				buildapptmemberDetails(result);
				
				}else{
					$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
				}
		  }); 
	 }
	 
	function buildapptmemberDetails(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead><th></th></thead>';
			str+='<tbody>';
			for(var i in result){
					str+='<tr><td style="padding:0px !important;">';
					str+='<div class="col-md-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li>';
							str+='<div class="row">';
								str+='<div class="col-md-7">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
										str+='</div>';
										str+='<div class="media-body">';
										if(result[i].constituency !=null && result[i].constituency.length>0){
												str+='<span>'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span>';
										}else{
											str+='<p>'+result[i].name+'</p>';
										}
										/* if(result[i].candidateType !=null && result[i].candidateType.length>0){
											str+='<p >'+result[i].name+' - '+result[i].candidateType+'</p>';
										}else{
											str+='<p>'+result[i].name+'</p>';
										} */
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p >Contact Number: '+result[i].mobileNo+'</p>';
										}else{
											str+='<p>Contact Number: - </p>';
										}
										if(result[i].designation !=null && result[i].designation.length>0){
												str+='<p >Designation: '+result[i].designation+'</p>';
										}else{
											if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
												str+='<p>Designation: - Cadre</p>';
											}else{
												str+='<p>Designation: - </p>';
											}
										}
										
										  // str+='<p>Recent Appt History: 20-feb-2016</p>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							   str+='<div class="col-md-2">';
									//str+='<p class="m_top10"><a href="#" class="text-success">View Appt History</a></p>';
								str+='</div>';
								str+='<div class="col-md-2">';
									//str+='<p class="m_top10"><a href="#" class="text-success">View/Edit Profile</a></p>';
								str+='</div>';
								if(result[i].designation==null)
									result[i].designation = "";
								str+='<div class="col-md-2 m_top10 pull-right" attr_id="'+result[i].id+'" >';
								if(result[i].appointmentCandidateId != null && result[i].appointmentCandidateId > 0)
								str+='<a  title="Click here to View '+result[i].name+' History" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].appointmentCandidateId+'" attr-name="'+result[i].name+'" attr-designation="'+result[i].designation+'" attr-mobile="'+result[i].mobileNo+'">View History</a>&nbsp;&nbsp;';
								str+='<a target="_blank" title="Click here to View '+result[i].name+' Cadre Details" style="cursor:pointer;" href="cadreDetailsAction.action?cadreId='+result[i].id+'">View Profile</a>&nbsp;&nbsp;';
								str+='<input type="checkbox" class="apptDetailsDiv"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile='+result[i].mobileNo+' attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+'>';
								str+='</div>';
								
							str+='</div>';
						str+='</li>';
					 
					str+='</ul>';
				str+='</div>';
				str+='</td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
		}
		
		$("#apptmemberDetailsDiv").html(str);
		$(document).on("click",".historyShowModalBtn",function(){
			$("#appCandidateNameId").html('');
			$(".historyShowModal").modal("show");
			//alert($(this).attr("attr-id"))
			var id = $(this).attr("attr-id");
			var name = $(this).attr("attr-name");
			var designation = $(this).attr("attr-designation");
			var mobile = $(this).attr("attr-mobile");
			if(designation != null && designation.length > 0)
			$("#appCandidateNameId").html(''+name+' ('+designation+') - '+mobile+'');
		else
			$("#appCandidateNameId").html(''+name+' - '+mobile+'');
			getAppointStatusOverviewforCandidate(id);
			getAppointmentHistoryForCandidate(id);
		});
		applyPagination();
	}
	var popDesignation ;
	 $(document).on("click",".apptDetailsDiv",function(){
		 
		 
		 if($(this).is(':checked')){
			  var searchType = $("#searchTypeId").val();
			  $("#addOneBlock").trigger("click");
			
				// $("#checkboxMemberAjax").css("display","block");
				
				 var temp = cloneCount-1;
				 $('html, body').animate({
                    scrollTop: $('#block'+temp).offset().top
                }, 2000);
				 $("#candiImageUrlId"+temp).val($(this).attr("attr_img_url"));//setting imageUrl to hidden variable for saving
				 
				 if($(this).attr("attr_candidateType")=="appointmentCandidate"){
					 
					$("#candidateId"+temp).val($(this).attr("attr_id")); //setting existingCandidateId to hidden variable for saving
				 }
				 
				   $(this).attr("clone_block_count",temp);
				   $(this).attr("id",'uncheck'+temp);
				   
				 var candidateType = $(this).attr("attr_candidatetype");
				 var id = $(this).attr("attr_id");
				 
				 var name = $(this).attr("attr_name");
				 var mobile = $(this).attr("attr_mobile");
				 var desg = $(this).attr("attr_desg");
				 var membershipno = $(this).attr("attr_memberShipNo");
				 var votercardno = $(this).attr("attr_voterCardNo");
				 var closeId1 = $(this).attr("attr_id");
				 var candidateTypeId = $(this).attr("attr_candidateType_id");
				 var designation = $(this).attr("attr_designation");
			    popDesignation = designation;
		
		var jsObj={
			candidateType:candidateType,
			id:id,
			designation:designation
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getCandidateWiseDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				// $("#checkboxMemberAjax").css("display","none");
				var lctscpid = ''+result.locationScopeId+'';
				var consId = ''+result.constituencyId+'';
				var distId = ''+result.districtId+'';
				var locEleId = ''+result.localElectionBodyId+'';
				var tehsilId = ''+result.tehsilId+'';
				var villageId = ''+result.villageId+'';
				var wardId = ''+result.wardId+'';
				var temp=parseInt(cloneCount)-1;
				
				$('#candidateNameId'+temp).val(name);
				$('#block'+temp).attr("attr_blk",closeId1);
				$('#mobileNoId'+temp).val(mobile);
				
				
				
				$('#voterCardNoID'+temp).val(votercardno);
				$('#membershipNumId'+temp).val(membershipno);
			    //$('#designationSelId'+temp).val(desg);
				var candidateType1;
				//$("#designationSelId+temp option").each(function() {
					$('#designationSelId'+temp+' option').each(function() {
				if($(this).text().toUpperCase() == ''+designation.toUpperCase()+'') {
				//$(this).prop('selected', true) ;
				candidateType1 = $(this).attr("typeId");
				if(designation != null)
				getDesignationsByType(candidateType1,'designationSelId'+temp);
				} 
			  });	
				var selectx = new Dropkick('#designationSelId'+temp);
					selectx.refresh();
					
			  if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){	
			 
				$('#candidateTypeSelId'+temp).val(3);
			 	 var selectcc = new Dropkick('#candidateTypeSelId'+temp);
				selectcc.refresh();
				
				getDesignationsByType(3,'designationSelId'+temp);
				 setTimeout(function () {
					$('#designationSelId'+temp).val(63);
						var selectcd = new Dropkick('#designationSelId'+temp);
						selectcd.refresh();
					}, 500);	
				}
				$('#locationScopeSelId'+temp).val(lctscpid);
				var selectL = new Dropkick('#locationScopeSelId'+temp);
				selectL.refresh();
				 showhideLocationBoxes(temp);
				
				$('#districtId'+temp).val(distId);
				var selectD = new Dropkick('#districtId'+temp);
				selectD.refresh();
					
				if(result.constList != null && result.constList.length > 0){
					var str='';
					str+='<option value="0">Select Constituency</option>';
					for(var i in result.constList){
						str+='<option value="'+result.constList[i].id+'">'+result.constList[i].name+'</option>';
					}
					$('#constituencyId'+temp).html(str);
					var select = new Dropkick('#constituencyId'+temp);
					select.refresh();
					}
					$('#constituencyId'+temp).val(consId);
					var selectC = new Dropkick('#constituencyId'+temp);
					 selectC.refresh();
					 
					if(lctscpid == 5 ){	
				
					if(result.tehLebDivList != null && result.tehLebDivList.length > 0){
						var str='';
						str+='<option value="0">Select Mandal/Muncilpality</option>';
						for(var i in result.tehLebDivList){
							str+='<option value="'+result.tehLebDivList[i].locationId+'">'+result.tehLebDivList[i].locationName+'</option>';
						}
						$('#tehsilId'+temp).html(str);
						var select = new Dropkick('#tehsilId'+temp);
						select.refresh();
						}
						
						$('#tehsilId'+temp).val(tehsilId);
						var selectT = new Dropkick('#tehsilId'+temp);
						selectT.refresh();
					}
					if(lctscpid == 7){	
					
					if(result.tehLebDivList != null && result.tehLebDivList.length > 0){
						var str='';
						str+='<option value="0">Select Mandal/Muncilpality</option>';
						for(var i in result.tehLebDivList){
							str+='<option value="'+result.tehLebDivList[i].locationId+'">'+result.tehLebDivList[i].locationName+'</option>';
						}
						$('#tehsilId'+temp).html(str);
						var select = new Dropkick('#tehsilId'+temp);
						select.refresh();
						}
						
						$('#tehsilId'+temp).val(locEleId);
						var selectT = new Dropkick('#tehsilId'+temp);
						selectT.refresh();
					}
				
				if(lctscpid == 6){
					
					if(result.tehLebDivList != null && result.tehLebDivList.length > 0){
						var str='';
						str+='<option value="0">Select Mandal/Muncilpality</option>';
						for(var i in result.tehLebDivList){
							str+='<option value="'+result.tehLebDivList[i].locationId+'">'+result.tehLebDivList[i].locationName+'</option>';
						}
						$('#tehsilId'+temp).html(str);
						var select = new Dropkick('#tehsilId'+temp);
						select.refresh();
						}
						
						$('#tehsilId'+temp).val(tehsilId);
						var selectT = new Dropkick('#tehsilId'+temp);
						selectT.refresh();
						
					if(result.villWardList != null && result.villWardList.length > 0){
					var str='';
					str+='<option value="0">Select Village/Ward</option>';
					for(var i in result.villWardList){
						str+='<option value="'+result.villWardList[i].locationId+'">'+result.villWardList[i].locationName+'</option>';
					}
					$('#villageId'+temp).html(str);
					var select = new Dropkick('#villageId'+temp);
					select.refresh();
					}
					
					$('#villageId'+temp).val(villageId);
					var selectV = new Dropkick('#villageId'+temp);
					selectV.refresh();
				}
				if(lctscpid == 8 ){
					if(result.tehLebDivList != null && result.tehLebDivList.length > 0){
					var str='';
					str+='<option value="0">Select Mandal/Muncilpality</option>';
					for(var i in result.tehLebDivList){
						str+='<option value="'+result.tehLebDivList[i].locationId+'">'+result.tehLebDivList[i].locationName+'</option>';
					}
					$('#tehsilId'+temp).html(str);
					var select = new Dropkick('#tehsilId'+temp);
					select.refresh();
					}
					
					$('#tehsilId'+temp).val(locEleId);
					var selectT = new Dropkick('#tehsilId'+temp);
					selectT.refresh();
					
					if(result.villWardList != null && result.villWardList.length > 0){
					var str='';
					str+='<option value="0">Select Village/Ward</option>';
					for(var i in result.villWardList){
						str+='<option value="'+result.villWardList[i].locationId+'">'+result.villWardList[i].locationName+'</option>';
					}
					$('#villageId'+temp).html(str);
					var select = new Dropkick('#villageId'+temp);
					select.refresh();
					}
					
					$('#villageId'+temp).val(wardId);
					var selectW = new Dropkick('#villageId'+temp);
					selectW.refresh();
				}
				
				
				
			}); 
		 }else{
			var blockCount = $(this).attr("clone_block_count");
			 $('#block'+blockCount).remove();
			}
	});
  
  
  var appointmentlabelId;
  $(document).on("click",".addMembersClass",function(){
	  appointmentlabelId = '';
	  appointmentlabelId = $(this).closest("tr").find("td:eq(0)").attr("attr_label_id");
	  $(".commonDivCls").hide();
	  $(".searchDivCls").show();
	  $("#searchAppointmentdetailsId").attr("attr_label_name",$(this).attr("attr_label_name"));
	  
	  $("#manageAppDesigId").val(0);
	  $("#manageAppDesigId").dropkick('reset');
		
	  $("#manageAppTypeId").val(0);
	  $("#manageAppTypeId").dropkick('reset');
	  $("#manageAppStatusId").val(0);
	  $("#manageAppStatusId").dropkick('reset');
	  $("#manageAppDistId").val(0);
	  $("#manageAppDistId").dropkick('reset');
	  $("#manageAppConstId").val(0);
	  $("#manageAppConstId").dropkick('reset');
   });
   
   $(document).on("click","#searchAppointmentdetailsId",function(){
	  getAppointmentsBySearchCriteria($(this).attr("attr_label_name"));
  });
  function clearAppointmentsSearchFields(){
	  $("#appDesigErrId,#appPrrtyErrTypId,#appStatusErrId,#appDistErrId,#appConstErrId").html('');  
  }
  function getAppointmentsBySearchCriteria(labelName){
		  
		  clearAppointmentsSearchFields();
		  $("#appointmentRequestedMembersId").html('');  
		  
		var fromDate='';
		var toDate='';
		var dateStr = $("#addMembersFromDateId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		 
		 var designationId=$("#manageAppDesigId").val();
		 var priorityId= $("#manageAppTypeId").val();
		 var statusId=$("#manageAppStatusId").val();
		 var districtId = $("#manageAppDistId").val();
		 var constituencyId = $("#manageAppConstId").val();
		 var candidateTypeId = $("#candidateTypeAddSelId").val();
		 
		 if(candidateTypeId ==null && candidateTypeId.length == 0){
			  $("#addErrCandidateTypeAddCls").html("Select Candidate Type.");
				return;	
		 }
		 if(designationId=="select"){
		  $("#appDesigErrId").html("Select Designation.");
           return;		  
		 }		 
		  
		 if(priorityId=="select"){
		  $("#appPrrtyErrTypId").html("Select Priority Type.");
           return;		  
		 }
		 
		 if(statusId=="select"){
		  $("#appStatusErrId").html("Select Appointment Status.");
           return;		  
		 }
		 if(districtId=="select"){
		  $("#appDistErrId").html("Select District.");
           return;		  
		 }
		 if(constituencyId=="select"){
		  $("#appConstErrId").html("Select Constituency.");
           return;		  
		 }

		var radioValue = $("input[name='aptRequestedName']:checked").val();
		 
		 $("#ajaxImgForApntSearchId").show();
    	var jsObj={
			designationId:designationId,
			priorityId:priorityId,
			statusId:statusId,
			districtId:districtId,
			constituencyid:constituencyId,
			appointmentlabelId : appointmentlabelId,
			fromDate :fromDate,
			toDate:toDate,
			selUserId:$("#appointmentUserSelectBoxId").val(),
			candidateTypeId:candidateTypeId,
			dateType:radioValue
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentsBySearchCriteriaAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 $("#ajaxImgForApntSearchId").hide();
				$("#appointmentRequestedMembersId").show();
				if(result!=null && result!=0){
				  buidResult(result,labelName);
				}else{
					var str='';
					str+='<div class="block">';
					str+='<h4 class="text-success">APPOINTMENT REQUESTED MEMBERS To '+labelName+'</h4>';
					str+='<p class="m_top20" style="color:green;font-size:20px">No Data available.</p>';
					str+='</div>';
				  $("#appointmentRequestedMembersId").html(str);	
				}
		  }); 
	 }
  function buidResult(result,labelName){
		 var i = 0;
		 var str='';
		  str+='<div class="block">';
			 str+='<h4 class="text-success">Assign Appointments To '+labelName+'</h4>';
			  //str+='<center><img id="apptRqstMemberAjax" src="images/icons/loading.gif" style="display:none;"/></center>';
			 
		 for(var i in result){
			
			  str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading font12">';
				        if(result[i].labeled){
							str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
							str+='<span class="requestedCheckbox text-success"><input class="appointmentcheckBoxClass pull-right" type="checkbox" value="'+result[i].appointmentId+'" checked></span>';
							str+='<span class="pull-right">Current Status : '+result[i].status+'</span>';
						}else{
							str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
							str+='<span class="requestedCheckbox text-success"><input class="appointmentcheckBoxClass pull-right" type="checkbox" value="'+result[i].appointmentId+'" ></span>';
							str+='<span class="pull-right">Current Status : '+result[i].status+'</span>';
						}
						if(result[i].subject !=null && result[i].subject.length>0){
							str+='<p>Purpose : '+result[i].subject+'</p>';
						}else{
							str+='<p>Purpose : - </p>';
						}if(result[i].priority !=null && result[i].priority.length>0){
							str+='<p>Priority Type : '+result[i].priority+'</p>';
						}else{
							str+='<p>Priority Type : - </p>';
						}if(result[i].dateString !=null && result[i].dateString.length>0){
							str+='<p>Requested Date : '+result[i].dateString+'</p>';
						}else{
							str+='<p>Requested Date : - </p>';
						}/* if(result[i].status !=null && result[i].status.length>0){
							str+='<p>Status : '+result[i].status+'</p>';
						}else{
							str+='<p>Status : - </p>';
						} */
						/* if(result[i].apptpreferableDates !=null && result[i].apptpreferableDates.length>0){
							str+='<p>Requested Dates : '+result[i].apptpreferableDates+'</p>';
						}else{
							str+='<p>Requested Dates : - </p>';
						} */
					
				str+='</div>';
				  str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers font12">';
							str+='<li>';
								str+='<div class="row">';
									//str+='<div class="col-md-12">';
										//str+='<span class="requestedCheckbox text-success"></span>';
									//str+='</div>';
									str+='<div class="col-md-4">';
										str+='<div class="media font12">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
											if(result[i].subList[j].cadre == true){
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
											}else{
												str+='<p>'+result[i].subList[j].name+'</p>';
											}if(result[i].subList[j].mobileNo != null && result[i].subList[j].mobileNo.length>0){
												str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
											}else{
												str+='<p>Contact Number: - </p>';
											}if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
											}else{
												str+='<p>Designation: - </p>';
											}if(result[i].subList[j].constituency !=null &&  result[i].subList[j].constituency.length>0){
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
											}else{
												str+='<p>Constituency : - </p>';
											}
                                            
                                            /* if(result[i].subList[j].lastVisit !=null && result[i].subList[j].lastVisit.trim().length>0){
												str+='<p>Last Visit: '+result[i].subList[j].lastVisit+'</p>';
											}else{
												str+='<p>Last Visit: - </p>';
											} */
												
											str+='</div>';
										str+='</div>';
										/* str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
										str+='<table class="table table-bordered">';
											str+='<tr>';
												str+='<td><h4>'+result[i].subList[j].requestCount+'</h4><p>Requests</p></td>';
												
												var confirmedCount=0;
												
												for(var k in result[i].subList[j].statusList){
													
													if(result[i].subList[j].statusList[k].id==2 || result[i].subList[j].statusList[k].id ==3){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														
													}
													else if(result[i].subList[j].statusList[k].id==4){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														str+='<td><h4>'+confirmedCount+'</h4><p> Confirmed </p></td>';
														
													}
													else{
														str+='<td><h4>'+result[i].subList[j].statusList[k].actualCount+'</h4><p>'+result[i].subList[j].statusList[k].name+'</p></td>';
													}
												}
												
											str+='</tr>';
										str+='</table>'; */
										
									str+='</div>';
									str+='<div class="col-md-8">';
										//str+='<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>';
										str+='<table class="table table-bordered table-condensed m_top10 font12">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
											str+='<th>CREATED DATE</th>';
										    str+='<th>APPOINTMENT PREFERABLE DATES</th>';
											str+='<th>STATUS</th>';
												
											str+='</thead>';
											str+='<tbody>';
											
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													str+='<td>'+result[i].subList[j].subList[l].dateString+'</td>';
													
													if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId >1){
														str+='<td>'+result[i].subList[j].subList[l].dateType+' : <span>'+result[i].subList[j].subList[l].minDate+' - '+result[i].subList[j].subList[l].maxDate+'</span></td>';
													}else if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId ==1){
														str+='<td>'+result[i].subList[j].subList[l].apptpreferableDates+'</td>';
													}else{
														str+='<td>-</td>';
													}
													
													if(result[i].subList[j].subList[l].status !=null){
														str+='<td>'+result[i].subList[j].subList[l].status+'</td>';
													}else{
														str+='<td>-</td>';
													}													
													str+='</tr>';
												}
											}else{
													str+='<thead>';
													str+='<th>CREATED DATE</th>';
													str+='<th>APPOINTMENT PREFERABLE DATES</th>';
													str+='<th>STATUS</th>';
												
													str+='<tr>';
													str+='<td colspan="3"><center>No Data Available</center></td>';
													str+='</tr>';
											}
											
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						/* str+='<h4 class="m_top10"><b>NEW REQUESTED DATES :</b></h4>';
						if(result[i].apptpreferableDates != null && result[i].dateTypeId == 1){
							str+='<p><span>'+result[i].apptpreferableDates+'</span></p>';
						}else{ 
							str+='<p><span>'+result[i].dateType.toUpperCase()+' : '+ result[i].minDate +' - '+result[i].maxDate+'</span></p>';
						} */						
				  str+='</div>';
				str+='</div>';
			
		 }
		  str+='<button class="btn btn-success" id="updateLabelId" >UPDATE LABEL</button>';
		  str+=' <span id="statusMsgAppntReqt"></span>';
		 str+='<div ><center ><img style="display: none;margin-top: -30px;" src="images/icons/loading.gif" id="updateMemberAjax"></center></div>';
		 str+='</div>';
		
		 $("#appointmentRequestedMembersId").html(str)  
	 }
	 
	  $(document).on("click","#updateLabelId",function(){
	      addAppointmentsToLable();
      });
	 
	  function addAppointmentsToLable(){
		  $("#statusMsgAppntReqt").html("");
		var appointmentsArray = [];
		$('.appointmentcheckBoxClass').each(function(){
			if ($(this).is(':checked')){
				appointmentsArray.push( $(this).val() );
			 }
        });
		  
		if(appointmentsArray == null || appointmentsArray.length <= 0){
			$("#statusMsgAppntReqt").html("<center><h5 style='margin-top: -22px;color: red;'>Please Select Atleast One Appointment.</h5></center>");
			return;
		  }
		   $("#updateMemberAjax").css("display","block");
		  var jsObj={
				  	  apptLabelId:appointmentlabelId,
				  	  appointmentsArray:appointmentsArray
				  }
		  
		  	$.ajax({
				type : 'POST',
				url :  'addAppointmentstoLabelAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				  $("#updateMemberAjax").css("display","none");
				if(result!=null && result!=0){
				  if(result.resultCode == 1){
					   setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Appointments Added To Label Successfully</h4></center>").fadeOut(4000);
						}, 500);
					    setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
						 getLabelDtls();
				  }
				}else{
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(4000);
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				 }
		  }); 
	  }
	$(document).on("click","#viewAllAppointmentId",function(){
		var startIndex = 0;
		viewAllAppointment(startIndex,5);
	});
	function viewAllAppointment(startIndex,maxIndex){
		
		var aptUserId = $("#appointmentUserSelectBoxId").val();
	
		var jsObj={
				startIndex:	startIndex,
				maxIndex  :	maxIndex,
				aptUserId : aptUserId
			}  
		$.ajax({  
			type : 'GET',
			url : 'getAllAppointmentDetailsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}                  
			}).done(function(result){ 
				buildAppointmentDetails(result); 
			});   
	}
	function buildAppointmentDetails(result){
		var str = '';
		str+='<table class="table table-condensed bg_ff" id="allMemberTableId">';
		str+='<thead>';
		str+='<th>APPOINTMENT UNIQUE CODE</th>';
		str+='<th>NAME</th>';
		str+='<th>CONTACT NUMBER</th>';
		str+='<th>DESIGNATION</th>';
		str+='<th>APPOINTMENT REQUESTED TIME</th>';
		str+='</thead>';
		for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].uniqueId+'</td>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].membershipNum+'</td>';
		str+='<td>'+result[i].designation+'</td>';
		str+='<td>'+result[i].date+'</td>';
		str+='</tr>';
		}	
		str+='</table>';
		$("#appointmentListId").html(str);
		$('#allMemberTableId').DataTable({
			responsive: true,
			"info":     false,
			"bSearching": false,
			"sDom": '<"bottom"flp><"clear">',
			"columnDefs": [{  "targets": 0 }],		
			"bPaginate": false,
			"bLengthChange": false,
			"bAutoWidth": false,
			
		});
		var total=result[0].count;
		if(total> 5){
			$("#paginationDivId").pagination({
			items: total,
			itemsOnPage: 5,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*5;
				viewAllAppointment(num,5);
				}
				});
		}
	}
	
	$("#appointmentReqBlock").hide();
	
	$(document).on("click","#viewAllAppointmentId",function(){
		$("#appointmentReqBlock").show();
		$("#allAppointmentsHideBlock").hide();
	});
	
	$(document).on("click","#backToReqBlock",function(){
		$("#appointmentReqBlock").hide();
		$("#allAppointmentsHideBlock").show();
	});
	
	$(document).on("click",".updateLableAppointmentsCls",function(){
		$("#updateLabelNameSpanId").text($(this).attr("attr_label_name"));
		var jsObj={
			labelId : $(this).attr("attr_label_id")
		}
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentsOfALableForUpdateAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.length > 0){
					var str = ''
					for(var i in result){
						str+='<div class="panel panel-default manageAppViewPanelClass">';
							str+='<div class="panel-heading">';
								str+='<div class="row">';
									str+='<div class="col-md-12">';
										str+='<span class="requestedCheckboxPanel text-danger" id="apptStatus'+result[i].appointmentId+'">'+result[i].status+'</span>';
									str+='<div class="col-xs-5">';
										str+='<p>Purpose : '+result[i].reason+'</p>';
										str+='<p>Priority Type : '+result[i].priority+'</p>';
									str+='</div>';
									str+='<div class="col-xs-7">';
									str+='<div class="col-xs-5">';
											str+='<label>Update Status</label>';
											str+='<select class="form-control upadteAppntStatusCls" id="upadteAppntStatus" attr_appnt_id="'+result[i].appointmentId+'">';
											str+='<option value="0"> Select Status</option>';
											str+='<option value="5"> Reschedule</option>';
											str+='<option value="6"> Cancelled</option>';
											str+='</select>';
										str+='</div>';
										str+='<div class="col-xs-7" id="appointmentStatusMsg'+result[i].appointmentId+'" style="margin-top: 25px;"></div>';
									str+='</div>';
									
									str+='</div>';
								str+='</div>';
								
									
							   str+='</div>';
							
							str+='<div class="panel-body">';
								
								str+='<ul class="updateLabelMembers" style="list-style-type: none;">';
									
									if(result[i].basicInfoList != null && result[i].basicInfoList.length > 0){
										for(var j in result[i].basicInfoList){
											str+='<li>';
												str+='<div class="row">';
													str+='<div class="col-md-5">';
														str+='<div class="media">';
															str+='<div class="media-left">';
																str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
															str+='</div>';
															str+='<div class="media-body">';
																str+='<p>'+result[i].basicInfoList[j].name+'';
																if(result[i].basicInfoList[j].membershipNum != null)
																	str+='- Cadre</p>';
																else
																	str+='</p>';
																str+='<p>Contact Number: '+result[i].basicInfoList[j].mobileNo+'</p>';
																str+='<p>Designation: '+result[i].basicInfoList[j].designation+'</p>';
															str+='</div>';
														str+='</div>';
													str+='</div>';
													
												 /*  str+='<div class="col-md-2">';
														str+='<select class="form-control m_top25">';
															str+='<option>Tentitive</option>';
															str+='<option>Next 2 weeks</option>';
														str+='</select>';
												  str+='</div>'; */
													
												str+='</div>';
											str+='</li>';
										}
									}
									
								str+='</ul>';
								str+='<p class="font12 m_top10">Appt Created By: '+result[i].userName+' &nbsp;&nbsp;&nbsp;&nbsp; <img src="dist/Appointment/img/message.png" class="messageIcon"></p>';
								str+='<div class="messageBlock arrow_box">';
				                str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
				                str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
				                str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
				               str+='</div>';
							str+='</div>';
						str+='</div>';
						
					}
					str+='<div class="col-md-12">';
						str+='<button class="btn btn-success" id="upStatusBtnId">UpDate</button>';
						str+='<div id="updateStatusErrDivId"></div>';	
					str+='</div>';
					
					$("#updateAppointmentsForLabelDivId").html(str);
				}else{
					$("#updateAppointmentsForLabelDivId").html("<span style='color:green;font-size:20px'>No Data Available.</span>");
				}
				
			});
		$(".commonDivCls").hide();
		$(".appointmentsUpdateDivCls").show();
	});
	
	$(document).on("click",".viewMembersClass",function(){
		
		$(".commonDivCls").hide();
		
		var labelName = $(this).attr("attr_label_name");
		
		var jsObj={
			labelId : $(this).attr("attr_label_id"),
			callFrom : "print"
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
			  buildViewResult(result,labelName,jsObj.labelId);
			}else{
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	});
	
	
	function printMembersForView(labelId,labelName)
	{
		
		$(".commonDivCls").hide();
		
		var labelName =labelName;
		
		var jsObj={
			labelId :labelId,
			callFrom : ""
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
				buildViewResult(result,labelName,labelId);
			}else{
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	}
	
	function buildViewResult(result,labelName,labelId){
		var i = 0;
		var str='';
			str+='<div class="col-md-12">';
			str+='<div class="block">';
			str+='<h4 class="text-success" style="margin-bottom:10px;">'+labelName +' MEMBERS</h4>';
			for(var i in result){
			
				str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading font12">';
				    str+='<div class="row">';
				    str+='<div class="col-md-12">';
					//str+='<input type="button" class="text-danger" value="Print" onClick="printMembersForView(\''+labelId+'\',\''+labelName+'\');"></input>';
						str+='<span class="requestedCheckboxPanel text-danger">'+result[i].status+'</span>';
						str+='</div>';
					str+='</div>';
					str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
					if(result[i].priority !=null && result[i].priority.length>0){
						str+='<p>Priority : '+result[i].priority+'</p>';
					}else{
						str+='<p>Priority : - </p>';
					}if(result[i].dateString !=null && result[i].dateString.length>0){
						str+='<p>Requested Date : '+result[i].dateString.split(" ")[0]+'</p>';
					}else{
						str+='<p>Requested Date : - </p>';
					}
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}else{
						str+='<p>Purpose : - </p>';
					}	
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers font12">';
							str+='<li>';
								str+='<div class="row">';
									//str+='<div class="col-md-12">';
										//str+='<span class="requestedCheckbox text-success"></span>';
									//str+='</div>';
									str+='<div class="col-md-4">';
										str+='<div class="media font12">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
											if(result[i].subList[j].cadre == true){
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
											}else{
												str+='<p>'+result[i].subList[j].name+' </p>';
											}if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
													str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
											}else{
													str+='<p>Contact Number: - </p>';
											}if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
											}else{
												str+='<p>Designation: - </p>';
											}if(result[i].subList[j].constituency !=null && result[i].subList[j].constituency.length>0){
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
											}else{
												str+='<p>Constituency : - </p>';
											}
											
											/* if(result[i].subList[j].lastVisit !=null && result[i].subList[j].lastVisit.trim().length>0){
												str+='<p>Last Visit: '+result[i].subList[j].lastVisit+'</p>';
											}else{
												str+='<p>Last Visit: - </p>';
											} */
												//str+='<p>Appt Type  '+result[i].subList[j].priority+'</p>';												
											str+='</div>';
										str+='</div>';
										/* str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
										str+='<table class="table table-bordered font12">';
											str+='<tr>';
												str+='<td><h4>'+result[i].subList[j].requestCount+'</h4><p>Requests</p></td>';
												
												var confirmedCount=0;
												
												for(var k in result[i].subList[j].statusList){
													
													if(result[i].subList[j].statusList[k].id==2 || result[i].subList[j].statusList[k].id ==3){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														
													}
													else if(result[i].subList[j].statusList[k].id==4){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														str+='<td><h4>'+confirmedCount+'</h4><p> Confirmed </p></td>';
														
													}
													else{
														str+='<td><h4>'+result[i].subList[j].statusList[k].actualCount+'</h4><p>'+result[i].subList[j].statusList[k].name+'</p></td>';
													}
												}
												
											str+='</tr>';
										str+='</table>'; */
										
											
									str+='</div>';
									str+='<div class="col-md-8 font12">';
										//str+='<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>';balu1
										str+='<table class="table table-bordered table-condensed m_top10 font12">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
												str+='<th>CREATED DATE</th>';
												str+='<th>APPOINTMENT PREFERABLE DATES</th>';
												str+='<th>STATUS</th>';												
											str+='</thead>';
											str+='<tbody>';
											
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													str+='<td>'+result[i].subList[j].subList[l].dateString+'</td>';
													
													if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId >1){
														str+='<td>'+result[i].subList[j].subList[l].dateType+' : <span>'+result[i].subList[j].subList[l].minDate+' - '+result[i].subList[j].subList[l].maxDate+'</span></td>';
													}else if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId ==1){
														str+='<td>'+result[i].subList[j].subList[l].apptpreferableDates+'</td>';
													}else{
														str+='<td>-</td>';
													}
													
													if(result[i].subList[j].subList[l].status !=null){
														str+='<td>'+result[i].subList[j].subList[l].status+'</td>';
													}else{
														str+='<td>-</td>';
													}													
													str+='</tr>';
												}
											}else{
													str+='<thead>';
													str+='<th>CREATED DATE</th>';
													str+='<th>APPOINTMENT PREFERABLE DATES</th>';
													str+='<th>STATUS</th>';
												
													str+='<tr>';
													str+='<td colspan="3"><center>No Data Available</center></td>';
													str+='</tr>';
											}
											
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						/* if(result[i].apptpreferableDates != null){
							str+='<h4 class="m_top10"><b>NEW REQUESTED DATES :</b></h4>';
							str+='<p><span>'+result[i].apptpreferableDates+'</span></p>';
						}else{
							str+='<h4 class="m_top10"><b>NEW REQUESTED DATES :</b></h4>';
							str+='<p><span> - </span></p>';
						} */	
						str+='<h4 class="m_top10 font12"><b>NEW REQUESTED DATES :</b></h4>';
						if(result[i].apptpreferableDates != null && result[i].dateTypeId == 1){
							str+='<p class="font12"><span>'+result[i].apptpreferableDates+'</span></p>';
						}else{ 
							str+='<p class="font12"><span>'+result[i].dateType.toUpperCase()+' : '+ result[i].minDate +' - '+result[i].maxDate+'</span></p>';
						}
				  str+='</div>';
				str+='</div>';
			}
			str+='</div>';
			str+='</div>';
		
		$(".appointmentsViewDivCls").html(str)  
	}
	 
	 var searchJobj;
	function getSearchDetails()
	{
		searchJobj;
		$(".appointmentSettings").show();
		var createdBy =$("#appointmentcreatedBy").val();
		var statusId =$("#selectStatusId").val();
		var appointmentUserId =$("#appointmentUserSelectBoxId").val();
		var searchStr=$("#searchStrId").val().trim();
		var date = $("#dashboardSelectDateIds").val().split("-"); 
		var strDate =date[0];
		var endDate =date[1];
		var jsObj={
			createdBy : createdBy,
			appointmentUserId:appointmentUserId,
			searchStr:searchStr,
			strDate:strDate,
			endDate:endDate,
			statusId:statusId,
			task:""
			
		}
		searchJobj = jsObj;
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildUpcomingResult(result,statusId);
				buildInprogressResult(result,statusId);
				buildCompletedResult(result,statusId);
				if(statusId == 0)
				{
				  $(".heightAdjust").parent().parent().hide();
				  $("#upcomingAppointMentId,#inprogreessAppointMentId,#completedAppointMentId").parent().removeClass("col-md-12")
				  $("#upcomingAppointMentId,#inprogreessAppointMentId,#completedAppointMentId").parent().addClass("col-md-4")
				  $("#upcomingAppointMentId,#inprogreessAppointMentId,#completedAppointMentId").parent().show();
				}else if(statusId == 1)
				{
				  $(".heightAdjust").parent().parent().hide();
				  $("#upcomingAppointMentId").parent().addClass("col-md-12")
				  $("#upcomingAppointMentId").parent().removeClass("col-md-4")
				  $("#upcomingAppointMentId").parent().show();
				}else if(statusId == 2)
				{
				  $(".heightAdjust").parent().parent().hide();
				  $("#inprogreessAppointMentId").parent().addClass("col-md-12")
				  $("#inprogreessAppointMentId").parent().removeClass("col-md-4")
				  $("#inprogreessAppointMentId").parent().removeClass("pad_0")
				  $("#inprogreessAppointMentId").parent().show();
				}else if(statusId == 3)
				{
				  $(".heightAdjust").parent().parent().hide();
				  $("#completedAppointMentId").parent().addClass("col-md-12")
				  $("#completedAppointMentId").parent().removeClass("col-md-4")
				  $("#completedAppointMentId").parent().show();
				}
			})
	}
	function buildUpcomingResult(result,statusId)
	{
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		str+='<h4 class="text-success">UPCOMING APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings upcomingSetting"/>';
		str+='</h4>';
		
		str+='<div class="updateAppointment arrow_box">';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="5" name="upcomeRadio1" class="statusAllupcome" checked/>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="3" name="upcomeRadio1" class="statusAllupcome"/>Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 upcomeSmsText" ></textarea>';
		str+='<span class="msgDiv2upcome"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="upcome">UPDATE APPOINTMENT</button>';
		str+='</div>';
		if(result != null)
		{
			str+='<ul>';
			for(var i in result)
			{
				if(result[i].scheduleType == "UpCome")
				{
					str+='<div class="panel panel-default manageAppViewPanelClass m_top20">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="font12">ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							if(result[i].appointmentStatus == "Waiting")
							{
								str+='<span style="font-weight:bold;color:#2095F4">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Fixed")
							{
								str+='<span style="font-weight:bold;color:#650199">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Attended")
							{
								str+='<span style="font-weight:bold;color:#4EAF50">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Not Attended")
							{
								str+='<span style="font-weight:bold;color:#FF9800">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Rescheduled")
							{
								str+='<span style="font-weight:bold;color:#9800CD">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Cancelled")
							{
								str+='<span style="font-weight:bold;color:#CC0001">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Tentative")
							{
								str+='<span style="font-weight:bold;color:#673301">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Void")
							{
								str+='<span style="font-weight:bold;color:#656533">'+result[i].appointmentStatus+'</span>';
							}
							str+='<span class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].date+'&nbsp;&nbsp;'+result[i].time+'</span> &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
							
							str+='<div class="appointmentSettingsBLock arrow_box">';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="5" name="upcomeRadio" class="status'+result[i].appointmentId+' status" checked>Reschedule';
							str+='</label>';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="3"  name="upcomeRadio" class="status'+result[i].appointmentId+' status">Cancel';
							str+='</label>';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="4"  name="upcomeRadio" class="status'+result[i].appointmentId+' status">Not Attended';
							str+='</label>';
							str+='<label class="upcomedateCls1 m_top10">Select Date</label>';
							/*str+='<div class="input-group inputSearch upcomedateCls1">';
							str+='<span class="input-group-addon">';
							str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='<span class="caret"></span>';
							str+='</span>';
							str+='<input type="text" class="form-control upcomedateCls " id="upcomeDate">';
							str+='</div>';
							str+='<label class="upcomedateCls1 m_top10">Select Time</label>';
							str+='<div class="input-group inputSearch upcomedateCls1">';
							str+='<span class="input-group-addon">';
							str+='<i class="glyphicon glyphicon-time"></i>';
							str+='<span class="caret"></span>';
							str+='</span>';
							str+='<input type="text" class="form-control upcometimeCls">';
							str+='</div>';*/
							str+='<label class="checkbox-inline">';
							str+='<input type="checkbox" class="smsCheckedCls'+result[i].appointmentId+'">Send SMS';
							str+='</label>';
							str+='<textarea class="form-control smsTextCls'+result[i].appointmentId+'" id="smsTextId"></textarea>';
							str+='<span class="msgDiv'+result[i].appointmentId+'"></span>';
							str+='<button class="btn btn-block btn-success appointmentStatus" appointmentId='+result[i].appointmentId+' >UPDATE APPOINTMENT</button>';
							str+='</div>';
							
						str+='</div>';
						str+='<div class="panel-body pad_5">';
						str+='<ul>';
						flag = true;
						for(var j in result[i].subList)
						{
						
						str+='<li>';
						str+='<div class="media m_0">';
						str+='<div class="media-left">';
						str+='<img class="media-object thumbnail " src="dist/Appointment/img/thumb.jpg" alt="...">';
						str+='</div>';
						str+='<div class="media-body font12">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
						str+='<p></p>';
						str+='</div>';
						str+='</div>';
						str+='</li>';
						}
						if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="font12" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Sub: '+result[i].subject.substring(0,35)+'...</p>';
							}else{
							   str+='<p class="font12" style="margin-left: 52px; margin-top: -6px;">Purpose:'+result[i].subject+' </p>';
							}
						str+='</ul>';
					
					str+='<p class="font12 m_top10">';
					str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				}
			}
			str+='</ul>';
		}
		else
		{
			flag = false;
			str+='No Data';	
		}
		
		if(flag == false)
		{
			str+='No Data';	
		}
		$("#upcomingAppointMentId").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		if(!flag)
		$(".upcomingSetting").hide();
		$(".upcomedateCls").daterangepicker({singleDatePicker:true});
		$(".upcometimeCls").datetimepicker({format: "LT"});
	
	}
	
	$(document).on("click",".messageIcon",function() {
		
		$(".errorCls").html("");
	})
	$(document).on("click",".sendsms",function() {
		
		var flag = false;
		var appointmentId = $(this).attr("value");
		$(".msgDiv1"+appointmentId).html("").css("color","");;
		var smsText = $(".sendSms"+appointmentId).val().trim();
		if(smsText == "" || smsText.length == 0)
		{
		  $(".msgDiv1"+appointmentId).html("Sms Text is Required..").css("color","red");
		  flag = true;
		}

		if(flag == true)
		{
			return;
		}
		var jsObj={
			appointmentId : appointmentId,
			smsText:smsText
			}
			$.ajax({
			type : 'POST',
			url : 'sendSmsForAppointmentAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".msgDiv1"+appointmentId).html("Updated Successfully..").css("color","green");
			setTimeout(function(){
			 $(".msgDiv1"+appointmentId).html("");
			},2000);
			$(".sendSms"+appointmentId).val('');
		});
	})
	
	$(document).on("click",".appointmentStatus",function() {
	
		
		var appointmentId = $(this).attr("appointmentId");
		$(".msgDiv"+appointmentId).html("").css("color","");;
		var statusId;
		var smsCheck = false;
		var smsText = '';
		$(".status"+appointmentId).each(function(){
			if($(this).is(':checked'))
			{
				statusId = $(this).val();
			}
		})
		if($(".smsCheckedCls"+appointmentId).is(':checked'))
		{
			smsCheck = true;
			smsText = $(".smsTextCls"+appointmentId).val().trim();
		}
		
		var jsObj={
			appointmentId : appointmentId,
			date : '',
			time : '',
			smsCheck : smsCheck,
			smsText:smsText,
			statusId:statusId
		}
			$.ajax({
			type : 'POST',
			url : 'updateAppointmentStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".msgDiv"+appointmentId).html("Updated Successfully..").css("color","green");
			setTimeout(function(){
 $(".msgDiv"+appointmentId).html("");
},2000);
		});
	})
	
	
	$(document).on("click",".updateAll",function() {
		console.log(searchJobj)
		var appointmentType = $(this).attr("value");
		$(".msgDiv2"+appointmentType).html("").css("color","");
		var statusId;
			$(".statusAll"+appointmentType).each(function(){
			if($(this).is(':checked'))
			{
				statusId = $(this).val();
			}
		})
	  	var jsObj={
			appointmentType : appointmentType,
			statusId:statusId,
			appointmentUserId:searchJobj.appointmentUserId,
			createdBy:searchJobj.createdBy,
			endDate:searchJobj.endDate,
			searchStr:searchJobj.searchStr,
			strDate:searchJobj.strDate,
			task:""
		}
		
		
			$.ajax({
			type : 'POST',
			url : 'updateAllAppointmentStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".msgDiv2"+appointmentType).html("Updated Successfully..").css("color","green");
			setTimeout(function(){
			 $(".msgDiv2"+appointmentId).html("");
			},2000);
			
		});
	})
	/*function updateAppointmentUpcomeStatus(appointmentId)
	{
		
		var statusId;
		var smsCheck = false;
		var smsText = '';
		$(".upcomeStatus").each(function(){
			if($(this).is(':checked'))
			{
				statusId = $(this).val();
			}
		})
		if($("#upcomeSmsCheckedId").is(':checked'))
		{
			smsCheck = true;
			smsText = $("#upcomeSmsTextId").val().trim();
		}
		
		var jsObj={
			appointmentId : appointmentId,
			date : '',
			time : '',
			smsCheck : smsCheck,
			smsText:smsText,
			statusId:statusId
		}
			$.ajax({
			type : 'POST',
			url : 'updateAppointmentStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}
	
	*/
	$(document).on("click",".status",function(){
		var val = $(this).val();
			if(val == 5)
			$(".upcomedateCls1").show();
		else
				$(".upcomedateCls1").hide();
		
	});
	function buildInprogressResult(result,statusId)
	{
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		str+='<h4 class="text-success">INPROGRESS APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings inprogressSetting">';
		str+='</h4>';
		str+='<div class="updateAppointment arrow_box">';
			str+='<label class="radio-inline">';
		str+='<input type="radio" value="5" name="InProgressRadio1" class="statusAllInProgress" checked>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="3" name="InProgressRadio1" class="statusAllInProgress">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 InProgressSmsText" ></textarea>';
		str+='<span class="msgDiv2InProgress"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="InProgress">UPDATE APPOINTMENT</button>';
		str+='</div>';
		
		if(result != null)
		{
			str+='<ul class="">';
			for(var i in result)
			{
				if(result[i].scheduleType == "InProgress")
				{
					str+='<div class="panel panel-default manageAppViewPanelClass m_top20">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="font12">ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							if(result[i].appointmentStatus == "Waiting")
							{
								str+='<span style="font-weight:bold;color:#2095F4">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Fixed")
							{
								str+='<span style="font-weight:bold;color:#650199">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Attended")
							{
								str+='<span style="font-weight:bold;color:#4EAF50">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Not Attended")
							{
								str+='<span style="font-weight:bold;color:#FF9800">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Rescheduled")
							{
								str+='<span style="font-weight:bold;color:#9800CD">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Cancelled")
							{
								str+='<span style="font-weight:bold;color:#CC0001">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Tentative")
							{
								str+='<span style="font-weight:bold;color:#673301">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Void")
							{
								str+='<span style="font-weight:bold;color:#656533">'+result[i].appointmentStatus+'</span>';
							}
							str+='<span class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].date+'&nbsp;&nbsp;'+result[i].time+'</span> &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
							
						    str+='<div class="appointmentSettingsBLock arrow_box">';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="5" name="upcomeRadio" class="status'+result[i].appointmentId+' status" checked>Reschedule';
							str+='</label>';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="3"  name="upcomeRadio" class="status'+result[i].appointmentId+' status">Cancel';
							str+='</label>';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="4"  name="upcomeRadio" class="status'+result[i].appointmentId+' status">Not Attended';
							str+='</label>';
							/*str+='<label class="upcomedateCls1 m_top10">Select Date</label>';
							str+='<div class="input-group inputSearch upcomedateCls1">';
							str+='<span class="input-group-addon">';
							str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='<span class="caret"></span>';
							str+='</span>';
							str+='<input type="text" class="form-control upcomedateCls " id="upcomeDate">';
							str+='</div>';
							str+='<label class="upcomedateCls1 m_top10">Select Time</label>';
							str+='<div class="input-group inputSearch upcomedateCls1">';
							str+='<span class="input-group-addon">';
							str+='<i class="glyphicon glyphicon-time"></i>';
							str+='<span class="caret"></span>';
							str+='</span>';
							str+='<input type="text" class="form-control upcometimeCls">';
							str+='</div>';*/
							str+='<label class="checkbox-inline">';
							str+='<input type="checkbox" class="smsCheckedCls'+result[i].appointmentId+'">Send SMS';
							str+='</label>';
							str+='<textarea class="form-control smsTextCls'+result[i].appointmentId+'" id="smsTextId"></textarea>';
							str+='<span class="msgDiv'+result[i].appointmentId+'"></span>';
							str+='<button class="btn btn-block btn-success appointmentStatus" appointmentId='+result[i].appointmentId+' >UPDATE APPOINTMENT</button>';
							str+='</div>';
							
						str+='</div>';
						str+='<div class="panel-body pad_5">';
						str+='<ul>';
						flag = true;
						for(var j in result[i].subList)
						{
						
						str+='<li>';
						str+='<div class="media m_0">';
						str+='<div class="media-left">';
						str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
						str+='</div>';
						str+='<div class="media-body font12">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
						str+='<p></p>';
						str+='</div>';
						str+='</div>';
						str+='</li>';
						}
						if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p <p class="font12" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else{
							   str+='<p class="font12" style="margin-left: 52px; margin-top: -6px;">Purpose:'+result[i].subject+' </p>';
							}
						str+='</ul>';
					
					str+='<p class="font12 m_top10">';
					str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				}
			}
			str+='</ul>';
		}
		else
		{
			flag = false;
			str+='No Data';	
		}
		
		if(flag == false)
		{
			str+='No Data';	
		}
			
		str+='</div>';
		$("#inprogreessAppointMentId").html(str);
		 $('[data-toggle="tooltip"]').tooltip();
		if(!flag)
			$(".inprogressSetting").hide();	
		
	}
	
	
	function buildCompletedResult(result,statusId)
	{
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		str+='<h4 class="text-success">COMPLETED APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings completedSetting">';
		str+='</h4>';
		str+='<div class="updateAppointment arrow_box">';
			str+='<label class="radio-inline">';
		str+='<input type="radio" value="5" name="CompletedRadio1" class="statusAllCompleted" checked>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="3" name="CompletedRadio1" class="statusAllCompleted">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 CompletedSmsText" ></textarea>';
		str+='<span class="msgDiv2Completed"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="Completed">UPDATE APPOINTMENT</button>';
		str+='</div>';
		
		if(result != null)
		{
			str+='<ul>';
			for(var i in result)
			{ 
				if(result[i].scheduleType == "Completed")
				{
					str+='<div class="panel panel-default manageAppViewPanelClass m_top20">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="font12">ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							if(result[i].appointmentStatus == "Waiting")
							{
								str+='<span style="font-weight:bold;color:#2095F4">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Fixed")
							{
								str+='<span style="font-weight:bold;color:#650199">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Attended")
							{
								str+='<span style="font-weight:bold;color:#4EAF50">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Not Attended")
							{
								str+='<span style="font-weight:bold;color:#FF9800">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Rescheduled")
							{
								str+='<span style="font-weight:bold;color:#9800CD">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Cancelled")
							{
								str+='<span style="font-weight:bold;color:#CC0001">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Tentative")
							{
								str+='<span style="font-weight:bold;color:#673301">'+result[i].appointmentStatus+'</span>';
							}
							else if(result[i].appointmentStatus == "Void")
							{
								str+='<span style="font-weight:bold;color:#656533">'+result[i].appointmentStatus+'</span>';
							}
							str+='<span class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].date+'&nbsp;&nbsp;'+result[i].time+'</span> &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
							
							str+='<div class="appointmentSettingsBLock arrow_box">';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="5" name="upcomeRadio" class="status'+result[i].appointmentId+' status" checked>Reschedule';
							str+='</label>';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="3"  name="upcomeRadio" class="status'+result[i].appointmentId+' status">Cancel';
							str+='</label>';
							str+='<label class="radio-inline">';
							str+='<input type="radio" value="4"  name="upcomeRadio" class="status'+result[i].appointmentId+' status">Not Attended';
							str+='</label>';
							/*str+='<label class="upcomedateCls1 m_top10">Select Date</label>';
							str+='<div class="input-group inputSearch upcomedateCls1">';
							str+='<span class="input-group-addon">';
							str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='<span class="caret"></span>';
							str+='</span>';
							str+='<input type="text" class="form-control upcomedateCls " id="upcomeDate">';
							str+='</div>';
							str+='<label class="upcomedateCls1 m_top10">Select Time</label>';
							str+='<div class="input-group inputSearch upcomedateCls1">';
							str+='<span class="input-group-addon">';
							str+='<i class="glyphicon glyphicon-time"></i>';
							str+='<span class="caret"></span>';
							str+='</span>';
							str+='<input type="text" class="form-control upcometimeCls">';
							str+='</div>';*/
							str+='<label class="checkbox-inline">';
							str+='<input type="checkbox" class="smsCheckedCls'+result[i].appointmentId+'">Send SMS';
							str+='</label>';
							str+='<textarea class="form-control smsTextCls'+result[i].appointmentId+'" id="smsTextId"></textarea>';
							str+='<span class="msgDiv'+result[i].appointmentId+'"></span>';
							str+='<button class="btn btn-block btn-success appointmentStatus" appointmentId='+result[i].appointmentId+' >UPDATE APPOINTMENT</button>';
							str+='</div>';
							
						str+='</div>';
						str+='<div class="panel-body pad_5">';
						str+='<ul>';
						flag = true;
						for(var j in result[i].subList)
						{
						
						str+='<li>';
						str+='<div class="media m_0">';
						str+='<div class="media-left">';
						str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
						str+='</div>';
						str+='<div class="media-body font12">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';

						
						str+='</div>';
						str+='</div>';
						//multiple
						str+='</li>';
						}
						str+='</ul>';
						if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="font12" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else{
							  str+='<p class="font12" style="margin-left: 52px; margin-top: -6px;">Purpose:'+result[i].subject+' </p>';
							}
						
					
					str+='<p class="font12 m_top10">';
					str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				}
			}
			str+='</ul>';
		}
		else
		{
			flag = false;
			str+='No Data';	
		}
		
		if(flag == false)
		{
			$(".completedSetting").hide();
			str+='No Data';	
		}
		$("#completedAppointMentId").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		if(flag == false)
		$(".completedSetting").hide();
	}
</script>
<script>
	
	$(document).on("click",".cnfrmaptsCls",function(){
		getAppointmentLabels();
	});
	
	function getAppointmentLabels(){
		
		var aptUserId = $("#appointmentUserSelectBoxId").val();
		var jsObj = {
			aptUserId:aptUserId
		}
		$.ajax({
		type : 'GET',
		url : 'getAppointmentLabelsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){ 
		if(result!=null && result!=0){
			buildAppointmentLabel(result);
		}else{
			$("#appointmentLabelToGetSlotsId").html("<option value='0'>Select Label</option>");
			var select = new Dropkick("#appointmentLabelToGetSlotsId");
			select.refresh();
		}
		
	});     
	}
	function buildAppointmentLabel(result){
		var str='';
			str+='<option value="0">Select Label</option>';
			for(var i in result){
				str+='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#appointmentLabelToGetSlotsId").html(str);
			$("#appointmentLabelToGetSlotsId").dropkick();
			var select = new Dropkick("#appointmentLabelToGetSlotsId");
			select.refresh();
	} 
	$("#pluginTableId").hide();
	$("#showTimeSlotsId").click(function(){
		
		$("#timeSlotsErrId").html("");

		var appointmentLabelId = $("#appointmentLabelToGetSlotsId").val();
		if(appointmentLabelId==0){
			$("#timeSlotsErrId").html("<span style='color:red'>Please select a label</span>");
			return;
		}
		$("#apptRqstMemberAjax").show();
		//View Details Of Appointments call
		getViewAppointmentsOfALable();
		//get appointments of a lable
		getAppointmentsOfALabel();
		
		var jsObj = {
		appointmentLabelId:appointmentLabelId
	}
	$.ajax({
		type : 'GET',
		url : 'getTimeSlotsDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#apptRqstMemberAjax").hide();
		if(result!= null && result.length!=0 ){
			$("#pluginTableId").show();
			buildTimeSlotsTable(result);
			$(".changeClass").removeClass("col-md-12")
			$(".changeClass").addClass("col-md-8")
			$("#timeSlotsWarnId").hide();
		}else{
			$(".changeClass").removeClass("col-md-12")
			$(".changeClass").addClass("col-md-8")
			$("#pluginTableId").hide();
			$("#timeSlotsWarnId").show();
		}
		});
		var user = $("#appointmentUserSelectBoxId").text();
	});
	
	function getAppointmentsOfALabel(){
		var jsObj={
			labelId : $("#appointmentLabelToGetSlotsId").val(),
			callFrom : "timeSlot"
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
			  buildLabelResult(result,labelName);
			}else{
			  $("#confirmAppointmentsDivId").html("<div class='col-md-4'><div class='block'><div><p style='color:green;font-size:20px'>No Data available.</p></div></div></div>");	
			}
		});
	}
	
	function buildLabelResult(result,labelName){
		var i = 0;
		var str='';
			str+='<div class="col-md-4">';
				str+='<div class="block"  id="dragId">';
			
			//str+='<h4 class="text-success" style="margin-bottom:10px;">'+labelName +' MEMBERS</h4>';
			for(var i in result){
			
				str+='<div class="panel panel-default manageAppViewPanelClass newClass" attr_appointment_id='+result[i].appointmentId+'>';
				str+='<div class="panel-heading">';
				    str+='<div class="row">';
						str+='<div class="col-md-12">';
						str+='<span class="requestedCheckboxPanel text-danger" style="margin-right:25px">'+result[i].status+'</span>';
						str+='<span class="requestedCheckboxPanel hidelabel"><i class="glyphicon glyphicon-remove"></i></span>';
						str+='</div>';
					str+='</div>';
						
					str+='<p>Purpose : '+result[i].subject+'</p>';
					str+='<p>Priority Type : '+result[i].priority+'</p>';
					str+='<p>Requested Date : '+result[i].dateString+'</p>';
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					str+='<ul class="viewAppointmentRequestedMembers">';
					for(var j in result[i].subList){
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-12">';
										str+='<span class="requestedCheckbox text-success"></span>';
									str+='</div>';
									str+='<div class="col-md-12">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
												str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
												if(result[i].subList[j].lastVisit !=null && result[i].subList[j].lastVisit.trim().length>0){
													str+='<p>Last Visit: '+result[i].subList[j].lastVisit+'</p>';
												}else{
													str+='<p>Last Visit: - </p>';
												}
												//str+='<p>Appt Type  '+result[i].subList[j].priority+'</p>';												
											str+='</div>';
										str+='</div>';
										str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
										str+='<table class="table table-bordered" style="font-size:10px;">';
											str+='<tr>';
												str+='<td><h4>'+result[i].subList[j].requestCount+'</h4><p>Requests</p></td>';
												var confirmedCount=0;
												
												for(var k in result[i].subList[j].statusList){
													
													if(result[i].subList[j].statusList[k].id==2 || result[i].subList[j].statusList[k].id ==3){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														
													}
													else if(result[i].subList[j].statusList[k].id==4){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														str+='<td><h4>'+confirmedCount+'</h4><p> Confirmed </p></td>';
														
													}
													else{
														str+='<td><h4>'+result[i].subList[j].statusList[k].actualCount+'</h4><p>'+result[i].subList[j].statusList[k].name+'</p></td>';
													}
												}
											str+='</tr>';
										str+='</table>';
										if(result[i].apptpreferableDates != null){
											str+='<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>';
											str+='<p><span>'+result[i].apptpreferableDates+'</span></p>';
										}
											
									str+='</div>';
								str+='</div>';
							str+='</li>';
						}	
					str+='</ul>';	
				  str+='</div>';
				str+='</div>';
			}
			str+='</div>'
			str+='</div>'
		$("#confirmAppointmentsDivId").html(str);
		Sortable.create(dragId,{
			  filter: '.js-remove',
			  onFilter: function (evt) {
				evt.item.parentNode.removeChild(evt.item);
			  },
			  setData: function (dataTransfer, dragEl) {
				dataTransfer.setData('Text', dragEl.textContent);
			  },
			  //group: "QuestionnOptions",
			  group: { name: "confirmAppointmentsBlock", put: false, pull: true },
			  animation: 150,
			  store: {
				get: function (sortable) {
				  var order = localStorage.getItem(sortable.options.group);
				  return order ? order.split('|') : [];
				},
				set: function (sortable) {
				  var order = sortable.toArray();
				  localStorage.setItem(sortable.options.group, order.join('|'));
				}
			  },
			  onAdd: function (evt){console.log('onAdd.editable:', [evt.item, evt.from]);},
			  onUpdate: function (evt){ console.log('onUpdate.editable:', [evt.item, evt.from]); },
			  onRemove: function (evt){ console.log('onRemove.editable:', [evt.item, evt.from]); },
			  onStart:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
			  onSort:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
			  onEnd: function(evt){ console.log('onEnd.editable:', [evt.item, evt.from]);}
		  });		
	}
	
	$(document).on("click",".labelStatusCls",function(){		
	     showStatusBox($(this).attr("attr_label_id"),$(this).attr("attr_label_name"),$(this).attr("attr_status"),$(this).attr("attr_status_id"));
	});
	
	function showStatusBox(labelId,labelName,labelStatus,statusId){
		 var str='';
		str+='<div class="modal fade" id="myModalId" role="dialog">';
		  str+='<div class="modal-dialog modal-sm">';
				str+='<div class="modal-content">';
				  str+='<button style="margin-left:260px" type="button" class="btn btn-default" data-dismiss="modal">X</button>';
				  str+='<div class="modal-body text-center">';
				  str+='<p class="text-center">'+labelName+'</p>';
				  str+='<p class="text-center m_top10"><b>Are you sure you want to change label status ?</b></p>';
				  str+='<p class="text-center text-success m_top10" >Current Status - '+labelStatus+'</p>';
				  str+='<br>';
				  str+='<div class="m_top10">';
				str+='<select id="selectStsId" class="form-control" placeholder="Select Status"></select>';
				  str+='</div>';
				  str+='<p  style="color:red" id="updateStsErr"></p>';
				  str+='<input class="btn btn-success btn-block m_top10" attr_label_id="'+labelId+'" attr_label_status_id="'+statusId+'" type="button" id="updateStsBttnId" value="UPDATE"/>';
				  str+='<div id="statusErrDivId"></div>';
				   str+='<div class="m_top10">';
				  str+='<p id="updateStatusMsg"></p>';
				  str+='</div>';
				  str+='</div>';
				str+='</div>';
			 str+='</div>';
		 str+='</div>';
		 getAppointmentsLabelStatus("status");
		  $("#buildAppntmntStsTblId").html(str);
		  $("#myModalId").modal("show");
	}
	
	function getAppointmentsLabelStatus(type){
		
		if(type !=null && type == "status"){
			$("#selectStsId option").remove();
		}else if(type !=null && type == "onload"){
			$("#selectStsForLabelId option").remove();
		}
		
		var jsObj={
		}
		$.ajax({
			type : 'POST',
			url : 'getAppointmentsLabelStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			if(type !=null && type == "status"){
				$("#selectStsId").append('<option value="0">Select Status</option>');
				if(result!=null && result.length>0){
				for(var i in result){
					$("#selectStsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			  }
			}else if(type !=null && type == "onload"){
					str+='<option value="0">Select Status</option>';
				if(result!=null && result.length>0){
				for(var i in result){
					if(result[i].id==1){
						str+='<option value='+result[i].id+' selected>'+result[i].name+'</option>';
					}else{
						str+='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
				}
				$("#selectStsForLabelId").html(str);
				
			  }
			}
			
		});
	}

	$(document).on("click","#updateStsBttnId",function(){
		
		var labelId = $(this).attr("attr_label_id");
		var attrlabelstatusId = $(this).attr("attr_label_status_id");
		var labelstatusId = $("#selectStsId").val();
		if(labelstatusId == 0){
			$("#statusErrDivId").html("<span style='color:red;'>Please Select Status.</span>");
		}else if(attrlabelstatusId == labelstatusId){
			$("#statusErrDivId").html("<span style='color:red;'>Label Already In Selected Status.</span>");
		}else{
			updateAppointmentsLabelStatus(labelId,labelstatusId);
			
		}
		
	});
	function updateAppointmentsLabelStatus(labelId,labelstatusId){
		$("#statusErrDivId").html("");
		var jsObj={
				labelId:labelId,   
				labelstatusId:labelstatusId
		}
		$.ajax({
			type : 'POST',
			url : 'updateAppointmentsLabelStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
				  if(result.message=="success"){
					  getLabelDtls();
					  setTimeout(function () {
						$("#updateStatusMsg").html("<center><h4 style='color: green;'>Status Updated Successfully</h4></center>").fadeOut(3000);
						}, 500);
						setTimeout(function () {
						$("#myModalId").modal("hide");
						}, 1000);
				  }
				}else{
					setTimeout(function () {
						$("#updateStatusMsg").html("<center><h4 style='color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(3000);
						}, 500);
					
				 }
		});
	}
	
	$(document).on("change",".upadteAppntStatusCls",function(){
		updateMemberAppointmentsStatus($(this).attr("attr_appnt_id"),$(this).val(),$(this).find("option:selected").text());
	});
	
	
	function updateMemberAppointmentsStatus(apptId,statusId,status){
		$("#appointmentStatusMsg"+apptId).html("");
		var jsObj={
				apptId:apptId,
				statusId:statusId
		}
		$.ajax({
			type : 'POST',
			url : 'updateMemberAppointmentsStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result.message=="success"){
				$("#appointmentStatusMsg"+apptId).html("<span style='color:green;'>Status Updated Successfully.</span>");
				$("#apptStatus"+apptId).text(status);
				getLabelDtls();	
			}else{
				$("#appointmentStatusMsg"+apptId).html("<span style='color:red;'>Updation Failed..Try Later</span>");
			}
			
		});
	}
</script>
<script>
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

	function generateToExcel()
	{	
		tableToExcel('allMemberTableId', 'Users Report');
	}
	
	$(document).on("click","#setTimeSlotBtnId",function(){
		//var appointmentId = $("#appointmentLabelToGetSlotsId").val();		
		var appointmentId = $("#confirmAppointmentBlockDropId div").attr("attr_appointment_id");
		var date = $("#appointmentDateSlotId").val();
		var fromTime = $("#fromTimeId").val().trim();
		var toTime = $("#toTimeId").val().trim();
		
		//Saving
		setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,"save",0);
		
		
		//Validations For Time Slot Creation
		/*if(appointmentId ==null || appointmentId <=0 || appointmentId ==undefined){
			$("#errorDivForTimeSlotId").html("Please Specify the Appointment");
			return;
		}
		if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$("#errorDivForTimeSlotId").html("Please Specify the From Time");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$("#errorDivForTimeSlotId").html("Please Specify the To Time");
			return;
		}
		
		var jsObj={
			appointmentId : appointmentId,
			date : date,
			fromTime : fromTime,
			toTime : toTime
		}
		
		$.ajax({
			type : 'POST',
			url : 'setTimeSlotForAppointmentAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg != null && result.exceptionMsg == "success"){
				getViewAppointmentsOfALable();
				alert("New Time Slot Created Successfully");				
			}else{
				alert("failure,some problem occured While creating time slot");
			}
		});*/
	});
function buildTimeSlotsTable(result){
	
		var str='';
		str+='<tr>';
		str+='<td class="text-center" style="height:29px;">';
		str+='<i class="glyphicon glyphicon-triangle-top"></i>';
		str+='</td>';
		str+='</tr>';
		if(result.listOfTimePairPerDate!=null && result.listOfTimePairPerDate.length>0){
			for(var i in result.listOfTimePairPerDate){
				str+='<tr>';
				str+='<td class="text-center" style="height:29px;">'+(((result.listOfTimePairPerDate[i])[0])[0]).substr(0,10)+'</td>';
				str+='</tr>';
			}	
		}else if(result.labelDate!=null && result.labelDate.length>0){
			    str+='<tr>';
				str+='<td class="text-center" style="height:29px;">'+result.labelDate.substr(0,10)+'</td>';
				str+='</tr>';
		}
		str+='<tr>';
		str+='<td class="text-center" style="height:29px;">';
		str+='<i class="glyphicon glyphicon-triangle-bottom"></i>';
		str+='</td>';
		str+='</tr>';
		$("#tablePluginDateId").html(str);
		var str1='';
		str1+='<thead>';
		str1+='<th colspan="4">8a</th>';
		str1+='<th colspan="4">9</th>';
		str1+='<th colspan="4">10</th>';
		str1+='<th colspan="4">11</th>';
		str1+='<th colspan="4">12p</th>';
		str1+='<th colspan="4">1</th>';
		str1+='<th colspan="4">2</th>';
		str1+='<th colspan="4">3</th>';
		str1+='<th colspan="4">4</th>';
		str1+='<th colspan="4">5</th>';
		str1+='<th colspan="4">6</th>';
		str1+='<th colspan="4">7</th>';
		str1+='<th colspan="4">8</th>';
		str1+='</thead>';
		if(result.listOfTimePairPerDate!=null && result.listOfTimePairPerDate.length>0){
			for(var i in result.listOfTimePairPerDate){
			str1+='<tr id="'+i+'"class="borderSlot">';
			for(var unique=0;unique<=47;unique++){
				str1+='<td id="'+i+''+unique+'"></td>';
			}
			str1+='</tr>';
			}
			$("#tablePluginId").html(str1);
			for(var i in result.listOfTimePairPerDate){
				for(var j in result.listOfTimePairPerDate[i]){
					//alert(((result.listOfTimePairPerDate[i])[j])[0]);
					var start=((result.listOfTimePairPerDate[i])[j])[0];
					var end = ((result.listOfTimePairPerDate[i])[j])[1];
					var startIdForHour=start.substr(11,2);
					var startIdForMin=start.substr(14,2);
					var startId=(startIdForHour-8)*4;
				
					startId= startId+(startIdForMin/15);
					var strtDividedVleForMnte=startIdForMin%15;
					   if(strtDividedVleForMnte>=8){
						   startId=startId+1;
					   }
					var endIdForHour=end.substr(11,2);
					var endIdForMin=end.substr(14,2);
					var endId=(endIdForHour-8)*4;
					
					endId= endId+(endIdForMin/15);
					var endDividedVleForMnte=endIdForMin%15;
					   if(endDividedVleForMnte>=8){
						   endId=endId+1;
					   }
					endId=endId-1;  
					for(var start=startId;start<=endId;start++){
						$("#"+i+""+start).addClass("bookedSlots");
					}
				}
			}
			
		}else{
			str1+='<tr class="borderSlot">';
			for(var unique=0;unique<=47;unique++){
				str1+='<td></td>';
			}
			str1+='</tr>';
			$("#tablePluginId").html(str1);
		}
		
	}
	function clearAllValidationCls(){
		$(".errorAptCls").html('');
		$(".errorSpdCls").html('');
		$(".errorArCls").html('');
		$(".errorCandidateMainDivCls").html('');
		$(".cloneErrCandidateNameCls").html('');
		$(".cloneErrCandidateDesgCls").html('');
		$(".cloneErrCandidateMobileCls").html('');
		$(".cloneErrCandidateLcScopeCls").html('');
		$(".cloneErrCandidateDistrictCls").html('');
		$(".cloneErrCandidateConstCls").html('');
		$(".cloneErrCandidateMandalCls").html('');
		$(".cloneErrCandidateVillageCls").html('');
		$(".cloneErrCandidateTypeCls").html('');
		$(".cloneErrCandidateMemShipCls").html('');
	}
	
	//Required validation For Appointment Creation
	function validateSavingDetails(){
	
		var isErrAvailable=false;
		var prType = $("#createAppTypeListId").val();
		var selectDate = $(".multiDateCls").val();
		var validateReason=$("#appointmentReasonId").val();
		
		if(prType == null || prType ==0 || prType == undefined){
			$(".errorAptCls").html("Please Select AppointmentType");
			isErrAvailable=true;
		}		
		if(selectDate == null || selectDate.length<=0 || selectDate == undefined){
			if($("#selectManualDateId").is(":checked")){
				$(".errorSpdCls").html("Please Select Appointment Date(s)");
				isErrAvailable=true;				
			}			
		}
		if(validateReason ==null || validateReason.length<=0 || validateReason == undefined || validateReason==""){
			$(".errorArCls").html("Please Specify The Reason");	
			isErrAvailable=true;				
		}
		if (isEmpty($('#moreCandidatesDivId'))) {
			$(".errorCandidateMainDivCls").html("Please Add Candidate");	
			isErrAvailable=true;				
		}else{
			$(".validateCls").each(function(i){
				var nameValue=$("#candidateNameId"+i).val();
				 if(nameValue ==null || nameValue.length<=0 || nameValue == undefined || typeof nameValue === "undefined" || nameValue.trim() == ""){
					  isErrAvailable=true;
					  $("#cloneErrCandidateNameId"+i+"").html("Please enter Name");
				 }
				 var desgValue=$("#designationSelId"+i).val();
				 if(desgValue ==null || desgValue ==0 || desgValue == undefined || desgValue ==""){
					  $("#cloneErrCandidateDesgId"+i).html("Please Select Designation");
					  isErrAvailable=true;	
				 }
				 
				 var mobileValue=$("#mobileNoId"+i).val();
				 if(mobileValue ==null || mobileValue.length ==0 || mobileValue == undefined || mobileValue ==""){
					  $("#cloneErrCandidateMobileId"+i).html("Please enter Mobile No");
					  isErrAvailable=true;
				 }	
				else if(mobileValue.length != 10 || isNaN(mobileValue)){		
					$("#cloneErrCandidateMobileId"+i).html("Please enter Valid Mobile Number");
					isErrAvailable=true;
				}
				
				 var canTypeValue=$("#candidateTypeSelId"+i).val();
				 if(canTypeValue ==null || canTypeValue ==0 || canTypeValue == undefined || canTypeValue ==""){
					  $("#cloneErrCandidateTypeId"+i).html("Please Select Candidate Type");
					  isErrAvailable=true;	
				 }
				 
				if(canTypeValue==1 || canTypeValue==2 || canTypeValue==3){
					var membershpNmbr=$("#membershipNumId"+i).val();
					  if(membershpNmbr ==null || membershpNmbr.length<=0 || membershpNmbr == undefined || typeof membershpNmbr === "undefined" || membershpNmbr.trim() == ""){
						 $("#cloneErrCandidateMemShipId"+i).html("Please Enter Membership Number");
						 isErrAvailable=true;
				   }
				}
							 
			 var locationScopeValue=$("#locationScopeSelId"+i).val();
				 if(locationScopeValue ==null || locationScopeValue ==0 || locationScopeValue == undefined || locationScopeValue == ""){
					  $("#cloneErrCandidateLcScopeId"+i).html("Please Selection Location Scope");
					  isErrAvailable=true;	
				 }else{
					 //District Level
					 if(locationScopeValue == 3){					 
							  var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						 
					//Constituency Level
					 }else if(locationScopeValue == 4){
						 
							  var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;	
							   }
							   
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}						 
						 
						// Mandal Level
					 }else if(locationScopeValue == 5){
						 
							  var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
						var tehsilValue = $("#tehsilId"+i).val();
						if(tehsilValue ==null || tehsilValue ==0 || tehsilValue == undefined || tehsilValue == ""){					
							$("#cloneErrCandidateMandalId"+i).html("Please select Mandal"); 
							isErrAvailable=true;
						}					
						 
					// Village level
					 }else if(locationScopeValue == 6){
						 
							var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District");
									isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
								
							var tehsilValue = $("#tehsilId"+i).val();
								if(tehsilValue ==null || tehsilValue ==0 || tehsilValue == undefined || tehsilValue == ""){					
									$("#cloneErrCandidateMandalId"+i).html("Please select Mandal"); 
									isErrAvailable=true;
								}	
								
								var villageValue = $("#villageId"+i).val();
								if(villageValue ==null || villageValue ==0 || villageValue == undefined || villageValue == ""){					
									$("#cloneErrCandidateVillageId"+i).html("Please select Village"); 
									isErrAvailable=true;
								}	
					//Municipality Level
					 }else if(locationScopeValue == 7){
						 
						 var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
						var MunicipalValue = $("#tehsilId"+i).val();
						if(MunicipalValue ==null || MunicipalValue ==0 || MunicipalValue == undefined || MunicipalValue == ""){					
							$("#cloneErrCandidateMandalId"+i).html("Please select Municipality"); 
							isErrAvailable=true;
						}
						
					//Ward Level
					 }else if(locationScopeValue == 8){
						 
						 var districtValue=$("#districtId"+i).val(); 
							   if(districtValue ==null || districtValue ==0 || districtValue == undefined || districtValue == ""){
								  $("#cloneErrCandidateDistrictId"+i).html("Please select District"); 
								  isErrAvailable=true;
							   }
						
							 var constValue = $("#constituencyId"+i).val();						
								if(constValue ==null || constValue ==0 || constValue == undefined || constValue == ""){
									$("#cloneErrCandidateConstId"+i).html("Please select Constituency"); 
									isErrAvailable=true;
								}
								
							var MunicipalValue = $("#tehsilId"+i).val();
								if(MunicipalValue ==null || MunicipalValue ==0 || MunicipalValue == undefined || MunicipalValue == ""){					
									$("#cloneErrCandidateMandalId"+i).html("Please select Municipality"); 
									isErrAvailable=true;
								}	
								
								var wardValue = $("#villageId"+i).val();
								if(wardValue ==null || wardValue ==0 || wardValue == undefined || wardValue == ""){					
									$("#cloneErrCandidateVillageId"+i).html("Please select Ward"); 
									isErrAvailable=true;
								}					 
					 }
				 }
			});
			
			
		}
		
		return isErrAvailable;	
	}
	
	//div emtty checking
	function isEmpty(el){
      return !$.trim(el.html())
	}
	function getViewAppointmentsOfALable(){
		var jsObj={
			labelId : $("#appointmentLabelToGetSlotsId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'getViewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){
				buildAppointmentMembers(result);
			}
		});
	}
	
	function buildAppointmentMembers(result){
		var str = '';
		var fromTime = 0;
		var toTime = 0;
		var firstMean = '';
		var secondMean = '';
		var firstHour = 0;
		var firstMin = 0;
		var secondHour = 0;
		var secondMin = 0;
		str+='<table id="appntmntMmbrsTblId">';
		str+='<thead><th></th></thead>';
		for(var i in result){
			str+='<tr>';
			str+='<td>';
			str+='<div class="panel panel-default manageAppViewPanelClass m_top15">';
			str+='<div class="panel-heading">';
			str+='<i id="setDfltTmFrmtId" class="glyphicon glyphicon-edit settingsIconConfirm settingsIcon pull-right" title="Click here to update label time slot." style="margin-left:10px;cursor:pointer;"></i>';
			str+='<div class="appointmentSettingsBLock arrow_box" style="display: none;">';
			str+='<div class="row updateAppMemCls" attr_timeSlotId="'+result[i].timeSlotId+'" attr_appointmentId="'+result[i].appointmentId+'">';
			str+='<div class="col-md-12 m_top10">';
			str+='<label>Select Date &nbsp <span style="color:red;" class="errorDivFrTmSltUpdtId"></span></label>';
			str+='<div class="inputSearch input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span><input type="text"    class="form-control appntmntCnddteUpdtDtRngPckrCls"/></div>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
			str+='<label>From Time</label>';
			str+='<div class="inputSearch input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span><input type="text" class="form-control appntmntCnddteUpdtFrmTmCls "/></div>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
			str+='<label>To Time</label>';
			str+='<div class="inputSearch input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span><input type="text" class="form-control appntmntCnddteUpdtTotmCls"/></div>';
			str+='</div>';
			str+='<div class="col-md-12">';
			str+='<button class="btn btn-success btn-block m_top10 updateTimeSlotCls">SET</button>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='<span class="text-success pull-right" style="margin-left:8px;" id="timeSpnCls">';
			str+='<i class="glyphicon glyphicon-time"></i>';
			fromTime = result[i].fromDateStr.substr(11,5);
			firstHour = fromTime.substr(0,2);
			firstMin = fromTime.substr(3,2);
			if(firstHour>12){
				firstHour = firstHour-12;
				firstMean = "PM";
			}else{
				firstMean = "AM";
			}
			
			toTime = result[i].toDateStr.substr(11,5);
			secondHour = toTime.substr(0,2);
			secondMin = toTime.substr(3,2);
			if(secondHour>12){
				secondHour = secondHour-12;
				secondMean = "PM";
			}else{
				secondMean = "AM";
			}
			str+=''+firstHour+' : '+firstMin+' '+firstMean +' to '+secondHour+' : '+secondMin+' '+secondMean +'';
			str+='</span>&nbsp;';
			str+='<span class="pull-right" style="margin-left:8px;" id="dateSpnCls">';
			str+='<i class="glyphicon glyphicon-calendar"></i> '+result[i].fromDateStr.substr(0,10)+'</span>&nbsp;';
			
			str+='<p>Purpose : '+result[i].subject+'</p>';
			str+='<p>Priority Type: '+result[i].priority+'</p>';
			str+='</div>';
			str+='<div class="panel-body pad_5">';
			str+='<ul class="confirmSearchUl" style="list-style: none;padding:0px">';
			for(var j in result[i].subList){
				str+='<li>';
					str+='<div class="row">';
							str+='<div class="col-md-7">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
									str+='</div>';
									str+='<div class="media-body">';
									if(result[i].subList[j].name !=null){
										str+='<p>'+result[i].subList[j].name+'</p>';
									}else{
										str+='<p> -- </p>';
									}
									if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
											str+='<p >Contact Number: '+result[i].subList[j].mobileNo+'</p>';
									}else{
										str+='<p>Contact Number: - </p>';
									}
									if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
											str+='<p >Designation: '+result[i].subList[j].designation+'</p>';
									}else{
										str+='<p>Designation: - </p>';
									}
									if(result[i].subList[j].constituency !=null && result[i].subList[j].constituency.length>0){
											str+='<p>Constituency: '+result[i].subList[j].constituency+'</p>';
									}else{
										str+='<p>Constituency: - </p>';
									}
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='</li>';
			}
			str+='</ul>';
			str+='<p class="font12 m_top10">';
			str+="<i>Appt Created By: "+result[i].userName+"</i>";
			str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';
		$("#appointmentMembersId").html(str);
        $(".appntmntCnddteUpdtDtRngPckrCls").daterangepicker({singleDatePicker:true});		
		//$('.appntmntCnddteUpdtDtRngPckrCls').val(moment().format('MM/DD/YYYY'));		
		//$(".appntmntCnddteUpdtFrmTmCls").datetimepicker({format:"LT"});
	 	//$(".appntmntCnddteUpdtTotmCls").datetimepicker({format:"LT"});
	    $('#appntmntMmbrsTblId').dataTable({
		   // "bPaginate" : $('#appntmntMmbrsTblBdyId tr').length>10,
			"iDisplayLength": 2,
			//"bAutoWidth": false,
			//"aoColumnDefs": [
				//{"bSortable": true, "aTargets": [0,2]}
			//]
		});
	}
	
	$(document).on("click",".createAppReqCls",function(){
		clearAllValidationCls();
		$("#errDigitsId").html('');
		$("#searchValueId").val('');
		$("#searchTypeId").val(0);
		 $("#advanceSearchTypeId").val(0); 
		 $("#advanceSearchValueId").val('');
		$("#createAppTypeListId").val(0);
		$("#errorDivId").html('');
		
		var searchTypeSelect = new Dropkick("#searchTypeId");
		searchTypeSelect.refresh();
		var createAppTypeSelect = new Dropkick("#createAppTypeListId");
		createAppTypeSelect.refresh();
		var advanceSearchType = new Dropkick("#advanceSearchTypeId");
		advanceSearchType.refresh();
		
		var selected = $("input[type='checkbox'][name='searchTypeRadio']:checked").val();
		if(selected != 1){
			$( "#myonoffswitch" ).trigger( "click" );
		}
		
		$("#apptmemberDetailsDiv").html('');
		
	});
	
	$(".errrLabelClearCls").click(function(){
		$("#errLabelName").html('');
	});
	
	$(".cnfrmaptsCls").click(function(){
		$("#errorDivForTimeSlotId").html('');
		//setting default time format
		$("#fromTimeId").val("8:00AM");
		$("#toTimeId").val("8:00AM");
	});
	
	 $(document).on("click","#setDfltTmFrmtId",function(){
	    //setting default time format
	    var timeArr=[];
		timeArr = $(this).closest("tr").find("#timeSpnCls").text().split("to");
		var date = $(this).closest("tr").find("#dateSpnCls").text();
		
		$(this).closest("tr").find(".appntmntCnddteUpdtDtRngPckrCls").val(date);
		$(this).closest("tr").find(".appntmntCnddteUpdtFrmTmCls").val(timeArr[0]);
		$(this).closest("tr").find(".appntmntCnddteUpdtTotmCls").val(timeArr[1]);
		//$(this).closest("tr").find(".appntmntCnddteUpdtFrmTmCls").val("8:00AM");
		//$(this).closest("tr").find(".appntmntCnddteUpdtTotmCls").val("8:00AM");
	 });
	$(".dateRadioCls").click(function(){		
		if($("#selectManualDateId").is(":checked")){
			$(".disableCls").attr('disabled', false); 
		}else{
			$(".disableCls").attr('disabled', true); 
		}		
	}); 
	
	$(document).on("click",".deleteAppointments",function(){
		
		$(".commonDivCls").hide();
		
		var labelName = $(this).attr("attr_label_name");
		var labelId = $(this).attr("attr_label_id");
		var jsObj={
			labelId : labelId,
			callFrom : ""
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsDeleteDivCls").show();
			if(result!=null && result!=0){
			  buildDeleteAppResult(result,labelName,labelId);
			}else{
			  $(".appointmentsDeleteDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	});
	
	function buildDeleteAppResult(result,labelName,labelId){
		var i = 0;
		var str='';
			str+='<div class="col-md-12">';
			str+='<div class="block">';
			str+='<h4 class="text-success" style="margin-bottom:10px;">'+labelName +' MEMBERS</h4>';
			for(var i in result){
			
				str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading">';
				    str+='<div class="row">';
						str+='<div class="col-md-12">';
						str+='<span class="pull-right"><input type="checkbox" value="'+result[i].appointmentId+'" class="deleteAppointmentChckCls"></input></span>';
						str+='</div>';
					str+='</div>';
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}else{
						str+='<p>Purpose : - </p>';
					}if(result[i].priority !=null && result[i].priority.length>0){
						str+='<p>Priority Type : '+result[i].priority+'</p>';
					}else{
						str+='<p>Priority Type : - </p>';
					}if(result[i].dateString !=null && result[i].dateString.length>0){
						str+='<p>Requested Date : '+result[i].dateString+'</p>';
					}else{
						str+='<p>Requested Date : - </p>';
					}
					if(result[i].status != null){
						str+='<p>Status : '+result[i].status+'</p>';
					}else{
						str+='<p>Status : - </p>';
					}					
					
				str+='</div>';
				str+='<div class="panel-body">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-12">';
										str+='<span class="requestedCheckbox text-success"></span>';
									str+='</div>';
									str+='<div class="col-md-6">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
											if(result[i].subList[j].cadre == true){
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
											}else{
												str+='<p>'+result[i].subList[j].name+' </p>';
											}if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
													str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
											}else{
													str+='<p>Contact Number: - </p>';
											}if(result[i].subList[j].designation !=null && result[i].subList[j].designation.length>0){
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
											}else{
												str+='<p>Designation: - </p>';
											}if(result[i].subList[j].constituency !=null && result[i].subList[j].constituency.length>0){
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
											}else{
												str+='<p>Constituency : - </p>';
											}
											
											if(result[i].subList[j].lastVisit !=null && result[i].subList[j].lastVisit.trim().length>0){
												str+='<p>Last Visit: '+result[i].subList[j].lastVisit+'</p>';
											}else{
												str+='<p>Last Visit: - </p>';
											}
												//str+='<p>Appt Type  '+result[i].subList[j].priority+'</p>';												
											str+='</div>';
										str+='</div>';
										str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
										str+='<table class="table table-bordered">';
											str+='<tr>';
												str+='<td><h4>'+result[i].subList[j].requestCount+'</h4><p>Requests</p></td>';
												
												var confirmedCount=0;
												
												for(var k in result[i].subList[j].statusList){
													
													if(result[i].subList[j].statusList[k].id==2 || result[i].subList[j].statusList[k].id ==3){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														
													}
													else if(result[i].subList[j].statusList[k].id==4){
														confirmedCount=confirmedCount+result[i].subList[j].statusList[k].actualCount;
														str+='<td><h4>'+confirmedCount+'</h4><p> Confirmed </p></td>';
														
													}
													else{
														str+='<td><h4>'+result[i].subList[j].statusList[k].actualCount+'</h4><p>'+result[i].subList[j].statusList[k].name+'</p></td>';
													}
												}
												
											str+='</tr>';
										str+='</table>';
										
											
									str+='</div>';
									str+='<div class="col-md-6">';
										str+='<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>';
										str+='<table class="table table-bordered m_top10">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
										  	str+='<th>Appt Last Requested Date</th>';
												str+='<th colspan="2">Appt Status</th>';
												
											str+='</thead>';
											str+='<tbody>';
											
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													str+='<td>'+result[i].subList[j].subList[l].dateString+'</td>';
													str+='<td>'+result[i].subList[j].subList[l].status+'</td>';
													if(result[i].subList[j].subList[l].apptStatus!=null && result[i].subList[j].subList[l].apptStatus.trim().length>0){
														str+='<td> '+result[i].subList[j].subList[l].apptStatus+'</td>';
													}else{
														str+='<td> - </td>';
													}
													
													str+='</tr>';
												}
											}else{
													str+='<thead>';
													str+='<th>Appt Last Requested Date</th>';
													str+='<th colspan="3">Appt Status</th>';
													str+='</thead>';
												
													str+='<tr>';
													str+='<td  colspan="2"><center>No Data Available</center></td>';
													str+='</tr>';
											}
											
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						if(result[i].apptpreferableDates != null){
							str+='<h4 class="m_top10"><b>NEW REQUESTED DATES :</b></h4>';
							str+='<p><span>'+result[i].apptpreferableDates+'</span></p>';
						}else{
							str+='<h4 class="m_top10"><b>NEW REQUESTED DATES :</b></h4>';
							str+='<p><span> - </span></p>';
						}	
				  str+='</div>';
				str+='</div>';
			}
			str+='<button class="btn btn-success" attr_label_id="'+labelId+'" id="deleteMultipleAppointmentsId">Delete Appointments</button>';
			str+='<div id="deleteAppointmentErrDivId"></div>';
			str+='</div>';
			str+='</div>';
		
		$(".appointmentsDeleteDivCls").html(str)  
	}
	
    function showHideBySearchType()
	{
		
		//setToDefaultAdvancedSearch();
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			
			if(selectVal == 2)
			{
				$(".advancePRCls").show();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$(".advanceprcls").show();
				setToDefaultAdvancedSearch();
				$("#advanceDesignationId").css("display","none");
				getPublicRepresentsDetails();
				disableByLevel();
				
			}
			else if(selectVal == 3)
			{
				$(".advancePRCls").hide();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").show();
				$(".locationsFilterCls").show();
				$(".advanceprcls").hide();
				getCommitteeRoles();
				$(".referRolesCheck").removeAttr("checked");
				setToDefaultAdvancedSearch();
				disableByLevel();
			}
			else
			{
				$(".advanceprcls").show();
				$(".advanceNameCls").show();
				$(".advancePRCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").hide();
				$("#advanceSearchValueId").val("");
				
			}
			
	}
	function setToDefaultAdvancedSearch()
	{
	
		
			$("#advanceDesignationId").val(0);
			$("#advanceDesignationId").dropkick('reset');
			$("#levelId").val(0);	
			$("#levelId").dropkick('reset');
			$("#referdistrictId").val(0);
				$("#referdistrictId").dropkick('reset');
		   $("#referconstituencyId").val(0);
			$("#referconstituencyId").dropkick('reset');
		   $("#refermandalNameId").val(0);
			$("#refermandalNameId").dropkick('reset');
		   $("#referpanchayatId").val(0);
		$("#referpanchayatId").dropkick('reset');
	}
	$(document).on("click","#deleteMultipleAppointmentsId",function(){
		$("#deleteAppointmentErrDivId").html("");
		var idsArr=[];
		$(".deleteAppointmentChckCls").each(function(){
			if($(this).prop('checked')==true){
				idsArr.push($(this).val());
			}
		});
		
		if(idsArr != null && idsArr.length <= 0){
			$("#deleteAppointmentErrDivId").html("<span style='color:red;'>Please Select Appointments To Delete.</span>");
			return;
		}
		
		var jsObj={
			idsArr : idsArr,
			labelId : $(this).attr("attr_label_id")
		}
		
		$.ajax({
			type : 'POST',
			url : 'deleteAppointmentsOfLabelAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				if(result.exceptionMsg != null && result.exceptionMsg == "success" && result.resultCode != null && result.resultCode == 0){
					$("#deleteAppointmentErrDivId").html("<span style='color:green;'>Appointments Deleted</span>");
					setTimeout(function(){	$("#deleteAppointmentErrDivId").hide(); },2000);
				}else{
					$("#deleteAppointmentErrDivId").html("<span style='color:red;'>Appointments Not Deleted, Please Try Again.</span>");
				}
			}
		});
	});
	//getAppointmentCreatedUsers();
function getAppointmentCreatedUsers(){
		$.ajax({
			type : 'GET',
			url : 'appointmentCreatedUsers.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildAppointmentCreatedUsers(result);
			}
			
		});
	}
	function buildAppointmentCreatedUsers(result){
		$("#appointmentcreatedBy  option").remove();
		$("#appointmentcreatedBy").append('<option value="0">All</option>');
	  for(var i in result){
			$("#appointmentcreatedBy").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
		//$(".appointmentCreatedidCls").dropkick();
		var select = new Dropkick("#appointmentcreatedBy");
		select.refresh();
	}
	
	function getAdvancedSearchDetails()
	{
		$("#apptmemberDetailsDiv").html("");
		var statusArr=[];
		var tdpCadreIds=[];
		var level;
		var levelValue;
		var tehsilId = 0;
		var committeeId = 0;
		var referCommitteeId;
		var errorStr='';
		var levelStr;
		$("#errorDivId").html('');
		var searchType;
		var searchValue = "";
		var districtId=0;
		var constituencyId=0;
		var mandalId = 0;
		var panchayatId=0;
		var levelId=0;
		var stateId=0;
		var advanceSearchType = $("#advanceSearchTypeId").val();
		 if(advanceSearchType==0){
			 errorStr='Please Select Search Type';
			 $("#errorDivId").html(errorStr);
		 }
		if(advanceSearchType == 1)
		{
			levelStr ="";
			 searchType = "name";
			 searchValue = $("#advanceSearchValueId").val().trim();
			 if(searchValue == null || searchValue.length ==0){
				 errorStr='Please Enter Name';
				 $("#errorDivId").html(errorStr);
				 return;
			 }else if(searchValue.length<3){
				 errorStr='Name should be minimum three characters.';
				 $("#errorDivId").html(errorStr);
				 return; 
			 }
		}
		else if(advanceSearchType == 2)
		{
			 searchType = "publicRepresentative";
			 searchValue = $("#advanceDesignationId").val();
			 if(searchValue == 0 || searchValue.length ==0)
			 {
				 errorStr='Please Select Designation';
				 $("#errorDivId").html(errorStr);
				 return;
			 }
		}
		else if(advanceSearchType == 3)
		{
			 searchType = "CadreCommittee";
				$("input[name='designationName']:checked").each(function ()
			{		
				var desgnaValue = $(this).attr("value");
				if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
					return false;
				}
				else{
					statusArr.push($(this).attr("value"));
				}		
			});
			 referCommitteeId = $("#referCommitteeId").val();
			 
		}
		if(advanceSearchType > 1)
		{
		 districtId = $("#referdistrictId").val();
		 constituencyId = $("#referconstituencyId").val();
		var tehsilName =  $("#refermandalNameId selected:option").text();
		if($("#refermandalNameId").val() > 0){
			if(tehsilName.indexOf('Mandal') == -1)
		tehsilId = "2"+$("#refermandalNameId").val();
		else
		tehsilId = "1"+$("#refermandalNameId").val();
		}
		if($("#refermandalNameId").val() == 0)
		tehsilId = $("#refermandalNameId").val();
	     panchayatId = $("#referpanchayatId").val();
		 levelId  = $("#levelId").val();
		if(levelId == 10)
			level = "state";
		if(levelId == 11)
			level = "district";
		if(levelId == 5)
			level = "mandal";
		if(levelId == 6)
			level = "village";
	 if(districtId == 0)
		{
			levelStr = "state";
			levelValue = 0;
			
		}
		else if(districtId > 0 && constituencyId == 0)
		{
			levelStr = "district";
			levelValue = districtId;
			
		}
		else if(districtId > 0 && constituencyId > 0 && tehsilId == 0)
		{
			levelStr = "constituency";
			levelValue = constituencyId;
			
		}
		
		else if(districtId > 0 && constituencyId > 0 && tehsilId > 0 && panchayatId == 0)
		{
			
			levelStr = "mandal";
			levelValue = tehsilId;
			
		}
		
		else if(districtId > 0 && constituencyId > 0 && tehsilId > 0 && panchayatId > 0)
		{
			levelStr = "village";
			levelValue = panchayatId;
			
		}
		
	
		$('#errorDivId').html(errorStr);
          if(levelId == 5){
		
			 districtId = $("#referdistrictId").val();
			
			if(districtId==0 || districtId=='select'){
			
				errorStr +="Please Select District";
			}
		}
		
		 else if(levelId == 6){
			 districtId = $("#referdistrictId").val();
			if(districtId==0 || districtId=='select'){
				
				errorStr +="Please Select District";
				$("#errorDivId").html(errorStr);
				return ;
				
			}
			  if(constituencyId == 0 || constituencyId=='select'){
				 constituencyId = $("#referconstituencyId").val();
				errorStr +="Please Select Assembly";
				$("#errorDivId").html(errorStr);
				return;
			}
		}
		
		}
	
		if(errorStr.length >0)
       {
	  $('#errorDivId').html(errorStr);
	  return ;
       } 
	   
	//Party Commitee Members	
	if(advanceSearchType !=null && advanceSearchType == 3){
		$("input[name='designationName']:checked").each(function ()
		{		
			var desgnaValue = $(this).attr("value");
			if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
				return false;
			}
			else{
				statusArr.push($(this).attr("value"));
			}		
		});
		committeeId = referCommitteeId;	
	}
	
	//Public Representatives
	if(advanceSearchType !=null && advanceSearchType == 2){
		/*$("input[name='designationPRName']:checked").each(function ()
		{		
			var desgnaValue = $(this).attr("value");
			if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
				return false;
			}
			else{
				statusArr.push($(this).attr("value"));
			}		
		});*/
		var desgnaValue = $("#advanceDesignationId").val();
		statusArr.push(desgnaValue);
		committeeId = "0";	
	}
	
	
	//$("#processingImgCandidateId").show();
	if(errorStr.length>0){
			$("#errorDivId").html(errorStr);
			return;
		}
		//$("#ajaxImgForAppintId").show();
	
		$("#apptmemberDetailsDiv").html("<center><img src='images/search.gif'/> </center>");
		
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			designations:statusArr,
			committeeId:committeeId, // "PR" -- if public representatives
			levelId:levelId,
			districtId:districtId,
			constituencyId:constituencyId,
			mandalId:tehsilId,
			panchayatId:panchayatId,
			stateId:0,
			levelStr:levelStr
		}
		  	$.ajax({
				type : 'POST',
				url : 'getAppntmntAdvancedSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				//$("#ajaxImgForAppintId").hide();
				$("#apptmemberDetailsDiv").html("");
				if(result !=null && result.length>0){
				buildapptmemberDetails(result);
				
				}else{
					$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
				}
		  }); 
	}
		$(document).on("click",".searchTypeRadioCls",function(){
			searchTypeRadioCls();
		})
	function searchTypeRadioCls()
	{
		$(".clearCls").val("");
		var selected = $("input[type='checkbox'][name='searchTypeRadio']:checked").val();
		if(selected == 1)
		{
			showHideSearch("search");
			$(".locationsFilterCls").hide();
			$(".advanceCadreCommittee").hide();
			
		}
		else
		{
			showHideSearch("advanceSearch");
			showHideBySearchType();
		}
	}
	function handleBySearchType()
	{
		var selected = $("input[type='checkbox'][name='searchTypeRadio']:checked").val();
		if(selected == 1)
		{
			getDetailsBySrch();
		}
		else
		{
			getAdvancedSearchDetails();
		}
	}
	
	$('#searchValueId').keydown(function(event){    
    if(event.keyCode==13){
       $('.advancedSearchBtn').trigger('click');
    }
	});

	$( "#appointmentUserSelectBoxId" ).change(function() {
		getAppointmentLabels();					
		getTotalAppointmentStatus();
		getAppointmentStatusCounts();
	});
	$( "#selectStsForLabelId" ).change(function() {
		getLabelDtls();
		
	})
	/*$(document).on('change',".cloneCandidateTypeCls",function() {
		
		getDesignationsByType($(this).val(),$(this).attr(id));
	})*/
	function getDesignationsByTypeForChange(cnt)
	{
	var typeId = $("#candidateTypeSelId"+cnt).val();	
	var jsObj = {
		typeId : typeId,
		task:""
	}
		$.ajax({
			type : 'GET',
			url : 'getAppCandidateDesigListByTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForCreateApp1(result,"designationSelId"+cnt);
			}
			
		});
	}
	
	function getDesignationsByType(typeId,selectId)
	{
	
	var jsObj = {
		typeId : typeId,
		task:""
	}
		$.ajax({
			type : 'GET',
			url : 'getAppCandidateDesigListByTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForCreateApp1(result,selectId);
			}
			
		});
	}
	
	function buildDesignationForCreateApp1(result,selectId){
			/*$("#designationListId  option").remove();
			$("#designationListId").append('<option value="0">Select Designation</option>');
			 $(".cloneDesignationCls option").remove(); 
			$(".cloneDesignationCls").append('<option value="0">Select Designation</option>'); 
			
			$("#manageAppDesigId  option").remove();
			$("#manageAppDesigId").append('<option value="select">Select Designation</option>');
			$("#manageAppDesigId").append('<option value="0" selected>ALL</option>');
			for(var i in result){
				$("#designationListId").append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
				$("#manageAppDesigId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$(".cloneDesignationCls").append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
			}
		
			 $("#manageAppDesigId").dropkick();
			var select1 = new Dropkick("#manageAppDesigId");
			select1.refresh();  */
			
			$("#"+selectId+"  option").remove();
		$('#'+selectId).append('<option value="0">Select Designation</option>');
		for(var i in result){
			if(popDesignation == result[i].name)
			$('#'+selectId).append('<option value='+result[i].id+' typeId='+result[i].orderId+' selected="true">'+result[i].name+'</option>');
		else
			$('#'+selectId).append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
		 }
		var select = new Dropkick('#'+selectId);
		select.refresh();
	} 
	function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
   $(document).on('click','.addMembersClass',function(){
		$("#appDesigErrId").html('');
		$("#appPrrtyErrTypId").html('');
		$("#appStatusErrId").html('');
		$("#appDistErrId").html('');
		$("#appConstErrId").html('');
	});
	$(document).on("click","#myonoffswitch",function(){
		$("#apptmemberDetailsDiv").html('');
	});
	function applyPagination(){
		$('#searchedMembersId').DataTable();
	}
	$(document).on("click",".updateTimeSlotCls",function(){
		
		var appointmentId =$(this).closest("tr").find(".updateAppMemCls").attr("attr_appointmentId");
		var timeSlotId = $(this).closest("tr").find(".updateAppMemCls").attr("attr_timeSlotId");
		var date = $(this).closest("tr").find(".appntmntCnddteUpdtDtRngPckrCls").val();
		var fromTime = $(this).closest("tr").find(".appntmntCnddteUpdtFrmTmCls").val().trim();
		var toTime = $(this).closest("tr").find(".appntmntCnddteUpdtTotmCls").val().trim();
		
		$(".errorDivFrTmSltUpdtId").html(' ');
		if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("Please Specify the From Time");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("Please Specify the To Time");
			return;
		}
		var fromTimeArr=fromTime.split(":");
		var toTimeArr=toTime.split(":");
		
		
	     if(fromTimeArr[0]<=7 && fromTimeArr[1].substr(2,3).trim()=="AM"){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("From time must be greater than or equal to 8:00AM");
		         return;
		 }	
         if(fromTimeArr[0]>=9 &&  fromTimeArr[1].substr(2,3).trim()=="PM"){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("From time must be greater than or equal to 8:00AM");
		        return;
		 }
	 	if(fromTimeArr[0]==8 && fromTimeArr[1].substr(0,2)>0 && fromTimeArr[1].substr(2,3).trim()=="PM"){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("From time must be greater than or equal to 8:00AM");
		        return;
		}  
		 if(toTimeArr[0]<=7 && toTimeArr[1].substr(2,3).trim()=="AM"){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To time must be less than or equal to 8:00PM");
		    return;
		 }	
         if(toTimeArr[0]>=9 && toTimeArr[1].substr(0,2)>=0 &&  toTimeArr[1].substr(2,3).trim()=="PM"){
		  $(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To time must be less than or equal to 8:00PM");
		  return;
		 }		
		 if(toTimeArr[0]==8 && toTimeArr[1].substr(0,2)>0 && toTimeArr[1].substr(2,3).trim()=="PM"){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To time must be less than or equal to 8:00PM");
		        return;
		}  
		if(!(Date.parse(date+" "+toTime) > Date.parse(date+" "+fromTime))){
			 $(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To Time should be greater than From Time.");
			 return;
		 }
		
	    setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,"update",timeSlotId)
	});
	
	function setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,type,timeSlotId){
		 $("#errorDivForTimeSlotId").show();
		//Validations For Time Slot Creation
		if(appointmentId ==null || appointmentId <=0 || appointmentId ==undefined){
			$("#errorDivForTimeSlotId").html("Please Specify the Appointment");
			return;
		}
		if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$("#errorDivForTimeSlotId").html("Please Specify the From Time");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$("#errorDivForTimeSlotId").html("Please Specify the To Time");
			return;
		}
		var fromTimeArr=fromTime.split(":");
		var toTimeArr=toTime.split(":");
		
		//debugger;
	     if(fromTimeArr[0]<=7 && fromTimeArr[1].substr(2,3).trim()=="AM"){
			$("#errorDivForTimeSlotId").html("From time must be greater than or equal to 8:00AM");
		         return;
		 }	
         if(fromTimeArr[0]>=9 &&  fromTimeArr[1].substr(2,3).trim()=="PM"){
			$("#errorDivForTimeSlotId").html("From time must be greater than or equal to 8:00AM");
		        return;
		 }
	 	if(fromTimeArr[0]==8 && fromTimeArr[1].substr(0,2)>0 && fromTimeArr[1].substr(2,3).trim()=="PM"){
			$("#errorDivForTimeSlotId").html("From time must be greater than or equal to 8:00AM");
		        return;
		}  
		 if(toTimeArr[0]<=7 && toTimeArr[1].substr(2,3).trim()=="AM"){
			$("#errorDivForTimeSlotId").html("To time must be less than or equal to 8:00PM");
		    return;
		 }	
         if(toTimeArr[0]>=9 && toTimeArr[1].substr(0,2)>=0 &&  toTimeArr[1].substr(2,3).trim()=="PM"){
		  $("#errorDivForTimeSlotId").html("To time must be less than or equal to 8:00PM");
		  return;
		 }		
		 if(toTimeArr[0]==8 && toTimeArr[1].substr(0,2)>0 && toTimeArr[1].substr(2,3).trim()=="PM"){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To time must be less than or equal to 8:00PM");
		        return;
		}  
		if(!(Date.parse(date+" "+toTime) > Date.parse(date+" "+fromTime))){
			 $("#errorDivForTimeSlotId").html("To Time should be greater than From Time.");
			 return;
		 }
		 $("#ajaxImgForTimeSlotId").css("display","inline-block");
		var jsObj={
			appointmentId : appointmentId,
			date : date,
			fromTime : fromTime,
			toTime : toTime,
			type :type,
			timeSlotId:timeSlotId
		}
		
		$.ajax({
			type : 'POST',
			url : 'setTimeSlotForAppointmentAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#errorDivForTimeSlotId").show();
			$("#ajaxImgForTimeSlotId").css("display","none");
			if(result != null && result.exceptionMsg != null && result.exceptionMsg == "success"){
				getViewAppointmentsOfALable();
				if(type=="save"){
				 $("#errorDivForTimeSlotId").html("<p style='color:green;font-size:20px'>Saved Successfully</p>");
				  setTimeout('$("#errorDivForTimeSlotId").hide()', 2000);
				  $('html, body').animate({
				   scrollTop: $("#errorDivForTimeSlotId").offset().top
					}, 2000);
					$("#confirmAppointmentBlockDropId").empty();
					$("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
					//setting default time format
					   $("#fromTimeId").val("8:00AM");
		               $("#toTimeId").val("8:00AM");
					}else{
			    $("#errorDivForTimeSlotId").html("<p style='color:green;font-size:20px'>Updated Successfully</p>");
				  setTimeout('$("#errorDivForTimeSlotId").hide()', 2000);
				   $('html, body').animate({
				    scrollTop: $("#errorDivForTimeSlotId").offset().top
					}, 2000);
				}
			}else{
				 $("#errorDivForTimeSlotId").html("<p style='color:red;font-size:20px'>Failure,Some problem occured while creating time slot.</p>");
				   $('html, body').animate({
				    scrollTop: $("#errorDivForTimeSlotId").offset().top
					}, 2000);
			}
		});
		
	}
	 $(document).on("click","#addApptsId",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});
</script>

<script>

function getDistrictsForReferPopup()
{
	$.ajax({
		type : 'GET',
		url : 'getDistrictsListAction.action',
		dataType : 'json',
		data : {}
	}).done(function(result){
		var str='';
		//str+='<option value="select">Select District</option>';
		str+='<option value="0">ALL</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#referdistrictId").html(str);
		$("#referdistrictId").dropkick();
		var select1 = new Dropkick("#referdistrictId");
		select1.refresh();
	});
 }
 
 
 function getConstituenciesBydistrictForReferPopup()
{
var constiStr ='';
	if($("#referdistrictId").val() == 0)
	{
	 setDefault();
	 return;	 
	}
	 var levelId = $("#levelId").val();
	 if(levelId == 10 || levelId == 11)
		  return; 
$("#referconstituencyId").find("option").remove();
$("#referconstituencyId").html('');
	
	var districtId= $("#referdistrictId").val();
	var jobj = {
	districtId:districtId,
	task       : ""
	}
	if(districtId > 0)
	{
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			    constiStr +='<option value="0">All</option>';
				for(var i in result){
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#referconstituencyId").html(''+constiStr+'');
			 $("#referconstituencyId").dropkick();
			 var select = new Dropkick("#referconstituencyId");
			select.refresh();
			//getMandalsByConstituency();
			}
		});
	
  }
}
 function setDefault1()
  {
	  $("#refermandalNameId").find('option').not(':first').remove();
	  $("#referpanchayatId").find('option').not(':first').remove();
	 /* $("#refermandalNameId").append('<option value=0>All</option>');
	  $("#referpanchayatId").append('<option value=0>All</option>');*/
	  var select = new Dropkick("#refermandalNameId");
		select.refresh();
		var select = new Dropkick("#referpanchayatId");
		select.refresh();
  }
function getMandalsByConstituencyForReferPopup()
{
var mandalStr ='';
if($("#referconstituencyId").val() == 0)
	{
	 setDefault1();
	 return;	 
	}
	$("#refermandalNameId").html('');
	var constituencyId = $('#referconstituencyId').val();
	var jobj = {
	constituencyId:constituencyId,
	task       : ""
	}
	if(constituencyId > 0){
		$.ajax({
		 type : "POST",
		 url  : "getMandalsByConstituencyAction.action",
		data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			if(result != null && result.length > 0){
			mandalStr +='<option value="0">All</option>';
				for(var i in result)
				{
					
				
					 mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				
				}
			 $("#refermandalNameId").html(''+mandalStr+'');
			 $("#refermandalNameId").dropkick();
			 var select = new Dropkick("#refermandalNameId");
			 select.refresh();
			}
		});
		}
}	
	
function getPanchayatsForReferPopup(){
			$("#referpanchayatId").find('option').not(':first').remove();
			var mandalId =  $("#refermandalNameId").val();
			var  type = $("#refermandalNameId option:selected").text();
			  if(mandalId == 0){
				return;
			  }
			  var levelId = $("#levelId").val();
				if(levelId == 5)
				return;
			 if(type.indexOf("Mandal") == -1) 
				type = "muncipality" ;
			else
				type = "mandal" ;
			  var jsObj={
						mandalId :mandalId,
						type:type,
						task:""
					}
			 $.ajax({
						type:"POST",
						url :"getPanchayatDetailsAction.action",
						 dataType: 'json',
						data: {task:JSON.stringify(jsObj)}
					}).done(function(result){
			//$("#referpanchayatId").append('<option value="0">All</option>');
			 for(var i in result){
			   $("#referpanchayatId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }
			  $("#referpanchayatId").dropkick();
			 var select = new Dropkick("#referpanchayatId");
			 select.refresh();
		   });
		}
	//disable by level
  function disableByLevel()
  {
	  setDefault();	
	  var levelId = $("#levelId").val();
	  //alert(levelId);
	  var districtId = $("#referdistrictId").val();
	  //alert(districtId);
	  var constituencyId = $("#referconstituencyId").val();
	  var panchayatId = $("#referpanchayatId").val();
	  var mandalId = $("#refermandalNameId").val();
	  var select = new Dropkick("#referdistrictId");
		select.refresh();
		
		if(levelId != 10 && levelId != 0)
		{
			getDistrictsForReferPopup();
		}
		
		if(levelId == 10 || levelId == 0)
		{
			  $("#referdistrictId").find('option').not(':first').remove();
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referdistrictId");
				select.refresh();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
				
		}
		else if(levelId == 11)
		{
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
		}
		else if(levelId == 5)
		{
			$("#referpanchayatId").find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").show();
				$(".panchayatCls").hide();
		}
		else if(levelId == 6)
		{
			$("#referpanchayatId").find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").show();
				$(".panchayatCls").show();
		}
		
  }
  function setDefault()
  {
	  $("#referconstituencyId").find('option').not(':first').remove();
	  $("#refermandalNameId").find('option').not(':first').remove();
	  $("#referpanchayatId").find('option').not(':first').remove();
	 /* $("#referconstituencyId").append('<option value=0>All</option>');
	  $("#refermandalNameId").append('<option value=0>All</option>');
	  $("#referpanchayatId").append('<option value=0>All</option>');*/
	    var select = new Dropkick("#referconstituencyId");
		select.refresh();
		var select = new Dropkick("#refermandalNameId");
		select.refresh();
		var select = new Dropkick("#referpanchayatId");
		select.refresh();
  } 
   function getAllCandidateTypes(){
		$.ajax({
			type : 'GET',
			url : 'getAllCandidateTypesAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildAllCandidateTypes(result);
			}
			
		});
	}
	
	function buildAllCandidateTypes(result){
		$(".cloneCandidateTypeCls option").remove(); 
		$(".addCandidateTypeCls option").remove(); 
			$(".cloneCandidateTypeCls").append('<option value="0">Select Candidate Type</option>'); 
			$(".addCandidateTypeCls").append('<option value="select">Select Candidate Type</option>');
			$(".addCandidateTypeCls").append('<option value="0" selected>All</option>');
			for(var i in result){
				$(".cloneCandidateTypeCls").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$(".addCandidateTypeCls").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
	}
function getCommitteeRoles(){
    	
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
			var str ='';
				str+='<ul class="SelectPosition SelectPositionScroll">';
				str+=' <li  id="0">All<input type="checkbox" name="" value="0" class="pull-right" attr_value="All" id="allDesignationId"></input></li>';
			   for(var i in result){
				   str+=' <li  id="'+result[i].id+'">'+result[i].name+'   <input type="checkbox" name="designationName" value="'+result[i].id+'" class="referRolesCheck pull-right" attr_value="'+result[i].name+'"></input></li>';
				}
				
				str+='</ul>';
				$("#cadreCommitteeDiv").html(str);
				$(".SelectPositionScroll").mCustomScrollbar();
			   });			  
      }
     
			$(document).on("click",".refreshBlockDiv",function(e){
				$("#LineChart").html('');
				getTotalAppointmentStatus();
				//getAppointmentUsersDtls();
				getAppointmentStatusCounts();
			});
			
		function getPublicRepresentsDetails(){
    	 $("#advanceDesignationId").html('');
    	var jsObj={
    			task:"publicRepresentatives"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getPublicRepresentativeTypes.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		if(result != null && result.length > 0){
				str +='<option value="0">All</option>';
				for(var i in result)
				{
				 str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
			 $("#advanceDesignationId").html(''+str+'');
			 $("#advanceDesignationId").dropkick();
			 var select = new Dropkick("#advanceDesignationId");
			 select.refresh();
			}
			
    	   });	
	 }
	 
	   function getAppointmentStatusOverview(){
    	
    	var jsObj={
    			task:""
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAppointmentStatusOverviewAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
				buildAppointmentStatusOverView(result);
    	   });	
	  }
	  function buildAppointmentStatusOverView(result)
	  {
		 for(var i in result)
		  {
			  if(result[i].name == "Waiting")
			  {
				  $("#waitingStatus").html(''+result[i].availableCount+'');
			  }
			  if(result[i].name == "Reschedule")
			  {
				  $("#rescheduledStatus").html(''+result[i].availableCount+''); 
			  }
			  if(result[i].name == "Cancelled")
			  {
				  $("#cancelledStatus").html(''+result[i].availableCount+''); 
			  }
			  if(result[i].name == "Not Attended")
			  {
				  $("#notAttendedStatus").html(''+result[i].availableCount+''); 
			  }
		  }
	 }
	 
	$("#candidateTypeAddSelId").change(function(){
		getDesignationsByTypeForAddAptmnt(); 
	});
	 
  function getDesignationsByTypeForAddAptmnt()
	{
		var typeId = $("#candidateTypeAddSelId").val();	
		var jsObj = {
			typeId : typeId,
			task:""
		}
		
		$.ajax({
			type : 'GET',
			url : 'getAppCandidateDesigListByTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){
				//app-appointment
				buildDesignationForAddApp(result);
			}			
		});
	}
	
	function buildDesignationForAddApp(result){
		$("#manageAppDesigId  option").remove();
		$('#manageAppDesigId').append('<option value="select">Select Designation</option>');
		$('#manageAppDesigId').append('<option value="0" selected>All</option>');
		for(var i in result){
			$('#manageAppDesigId').append('<option value='+result[i].id+' typeId='+result[i].orderId+' >'+result[i].name+'</option>');
		}
		var select = new Dropkick('#manageAppDesigId');
		select.refresh();
	}
	
	
	  function getAppointStatusOverviewforCandidate(id){
			
	    	var jsObj={
	    			appointmentCandidateId:id,
					task:""
	    		}
	    		$.ajax({
	    			  type:'GET',
	    			  url: 'getAppointStatusOverviewforCandidateAction.action',
	    			  data: {task:JSON.stringify(jsObj)}
	    	   }).done(function(result){
					
					buildAppointmentStatusOverView(result);
					
	    	   });	
		  }
		  function buildAppointmentStatusOverView(result)
		  {
			  
			var str = '';
			var total = 0;
			for(var i in result)
			{
				total = total + result[i].availableCount;
			}
			str+='<p>Total - '+total+'</p>';
			str+='<table class="table table-bordered">';
			str+='<tr class="text-center">';
			for(var i in result)
			{
			
			str+='<td>';
			str+='<h4>'+result[i].availableCount+'</h4>';
			str+='<h5>'+result[i].name+'</h5>';
			str+='</td>';
			
			}
			str+='</tr>';
			str+='</table>';
		   $("#aptCandidateHistorystatusOverViewDiv").html(str);			
		  }
		  
		  function getAppointmentHistoryForCandidate(id){
			$("#aptCandidateHistoryDiv").html('<img src="images/search.gif" />');
	    	var jsObj={
	    			appointmentCandidateId:id,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAppointmentHistoryForCandidateAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAppointmentHistoryForCandidate(result);
		  });
	  }
	  
	function buildAppointmentHistoryForCandidate(result)
	{
		var str='';
		str+='<table class="table table-condensed" style="border:1px solid #ddd">';
		str+='<thead>';
		str+='<th>ID</th>';
		str+='<th>PURPOSE</th>';
		str+='<th>CREATED ON</th>';
		str+='<th>PREFERED DATE</th>';
		str+='<th>CONFIRMED DATE</th>';
		str+='<th>STATUS</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		str+='<tr>';
		str+='<td>'+result[i].uniqueCode+'</td>';
		str+='<td>'+result[i].purpose+'</td>';
		str+='<td>'+result[i].createdOn+'</td>';
		str+='<td>'+result[i].preferredDate+'</td>';
		str+='<td>'+result[i].confirmedDate+'</td>';
		str+='<td>'+result[i].status+'</td>';
		str+='</tr>';	
		}
		str+='</tbody>';
		str+='</table>';
		$("#aptCandidateHistoryDiv").html(str);	
	}
	 
</script>
</body>
</html>