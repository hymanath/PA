<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!--<link href="dist/Appointment/DropkickNew/dropkick.css" rel="stylesheet" type="text/css">-->
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
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
						<li role="presentation"><a style="padding-left:0px;padding-right:0px" href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="createAppReqCls"><img src="dist/Appointment/img/createappointment.png">Create Appointment Request</a></li>
						<li role="presentation"><a href="#settings" style="cursor:pointer;" aria-controls="settings" role="tab" data-toggle="tab" class="cnfrmaptsCls"><img src="dist/Appointment/img/confirmappointments.png">Confirm Appointments</a></li>
						<li role="presentation"><a href="#advncdDashboard" aria-controls="advnceDashboard" role="tab" data-toggle="tab" class="advnceDashboardCls"><img src="dist/Appointment/img/AdvanceDashboard.png">Advance Dashboard</a></li>
					  </ul>
					  <!-- Tab panes -->
					  <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="block">
										<div class="row">
											<div class="col-md-6" style="border-right: 1px solid rgb(221, 221, 221);">
												<div style="margin-top: 0px; color: #fff; padding: 5px; font-size: 18px; background: rgba(10, 37, 63, 0.5);">
													<p>TODAY APPOINTMENTS</p>
												</div>
												<table class="table table-condensed tableAppointment" style="font-size:20px;" id="todayAppointmentsId">
													<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="todayAptLoadingId"></center></div>
												</table>
												<div style="margin-top: 20px; color: #fff; padding: 5px; font-size: 18px; background: rgba(10, 37, 63, 0.5);">
													<p>TOTAL RESCHEDULED APPOINTMENTS</p>
												</div>
												 <div style="">
												  <center>
														<img id="reschdldAppntmntsRprtTblIdPrcssingImgId" src="images/icons/loading.gif" style="display:none;width:50px;height:50px">
												   </center>
												   <div id="reschdldAppntmntsRprtTblId">
												  
												   </div>
												</div>
											</div>
											<div class="col-md-6">
												<div id="LineChart" style="width:500px;height:300px;"></div>
												<div  style="text-align: center;">Total Appointments - <span id="totalApptStatusCounts"></span></div>
											</div>
										</div>
									</div>
									<!-- EXPORT TO EXCEL TABLE ID-->
									<div style='display:none' id="appntmntCnddtDtlsTblId"></div>
									<div class="panel panel-default m_top20">
										<div class="panel-heading bg_ff" style="background:#fff">
											<h4 class="panel-title text-capitalize text-success dashboardPanelCollapse" >Select Date To View Scheduled Appointment
												<span class="pull-right">
													<i class="glyphicon glyphicon-plus-sign" style="cursor:pointer"></i>
												</span>
											</h4>
										</div>
										<div class="panel-body pad_0 dashboardPanelCollapseBody">
											<div class="block m_0">
												<div class="row">
													<div class="col-md-6 m_top10">
														<div class="form-inline">
														  <div class="form-group">
															<label>Date<span style="color:red;">*</span> </label>
															<div class="input-group inputSearch">
																<span class="input-group-addon">
																	<i class="glyphicon glyphicon-calendar"></i>
																</span>
																<input class="form-control" type="text" id="appointmentDashboardDateSlotHeadingId"/>
															</div>
														  </div>
														  <input style="margin-left:8px" class="btn btn-success" type="button" value="Submit" id="dashboardSubmitBtn"/>
														</div>
													</div>
													
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="block">
										<div class="row">
											<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
												<div id="newStatusBuildingId"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="containerpie" ></div>
							<div class="row m_top10">
								<div class="col-md-12">
									<div class="todayBlock ">
										<div class="row">
											<div class="col-md-3">
                                            	<label>Appointment Status</label>
                                                <select class="dropkickClass" id="selectStatusId">
                                                	<option value="0">All</option>
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
											<div class="col-md-3" >
												<label>Search</label>
												<div class="input-group inputSearch">
													<input class="form-control" type="text" id="searchStrId"  placeholder="Name or MobileNumber"">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-search"></i>
													</span>
												</div>
											</div>
											<div  class="col-md-1 m_top25" style="border-left:1px solid #ddd;">
                                            	<button id="" class="btn btn-success btn-block showTimeSlotsCls btnNewCustom1" onclick="getSearchDetails('false');">VIEW</button>
                                            </div>
										</div>
									</div>
									</div>
							</div>
						    <div class="row">
								<div class="col-md-12 ">
									<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="srhPrcssngImgId"></center></div>
                                	<div id="searchApptmntDivId"></div>
                                </div>
						    </div>
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="block">
										<div class="row">
											<div class="col-md-9">
												<h4 class="text-success">SEARCH MEMBER TO CREATE APPOINTMENT REQUESTED</h4>
											</div>
												<div class="col-md-3">
												 <div class="btn-group">
												  <button type="button" attr_val="1" class="btn btnClassChange btnNewCustom btnActive">SEARCH</button>
												  <button type="button" attr_val="2" class="btn btnClassChange btnNewCustom">ADVANCED SEARCH</button>
												</div>
											</div>
										</div>
                                        <div class="row m_top20">
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
                                                <select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();buildLevels();">
													<option value="0">Select Search Type</option>
													<option value="1">Name</option>
													<option value="2">Public Representative</option>
													<option value="3">Party Committee</option>
												</select>
											</div>
                                            <div class="col-md-4 pad_0 advanceSearchCls advanceprclsDiv">
                                            	<label class="advanceNameCls">Search By Name<span class="text-danger">*</span></label>
                                                <input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
												<label class="advancePRCls">Search Designation</label>
												 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
													<option value="0">Select Designation</option>
												</select>
												<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
						                    </div>
											<div id="OtherSelectlocationsDiv">
												<div>
													<div class="col-md-2 levelShowCls" style="display:none;">
														<label>Level</label>
														<select class="dropkickClass" id="levelId" onchange="disableByLevel();">
														</select>
													</div>
												  <div class="col-md-2 stateShowCls" style="display:none;">
														<label>State</label>
														<select class="dropkickClass" id="stateId" onChange="getDistrictsForReferPopup();">
														<option value="0">All</option>
														<option value="1">AP</option>
														<option value="36">TS</option>
														</select>
												 </div>
												 <div class="row col-md-12">
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
													</div>
													<div>
														<div class="advanceCadreCommittee" style="margin-top:5px;" id="referCommitteeDiv">
														 <div class="col-md-3">
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
																<select id="cadreCommitteeDiv" multiple class="advanceCadreCommittee" style="width:250px !important;"></select>
																<div id="representativesDiv"></div>
																<div id="referRoleErrorDiv"></div>
															</div>
														</div>
													</div></br>
												</div>
											</div>
											<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
											<div class="col-md-2">
												<button class="btn btn-block btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();"  style="margin-top: 25px;">Search Member</button>
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
									<form name="saveAppointment" id="saveAppointment"  method="post">
										<div class="block">
											<div class="row">
												<div class="col-md-12">
													<h4 class="text-success">CREATE APPOINTMENT REQUEST</h4>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4 m_top10">
													<label>Appointment Priority Type</label>
													<select name="appointmentVO.appointmentPriorityId" class="manageAppTypeCls" id="createAppTypeListId">
														<option value="0">Select Priority</option>
													</select>
													<!--<div class="errorAptCls validateClr"></div>-->
												</div>
												<div class="col-md-8 m_top10">
													<label class="radio-inline">
														<input type="radio" id="selectManualDateId" class="dateRadioCls" name="dateTypeRadio" value="multipleDates">Select Preferrable Dates
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
													<!--<div class="errorSpdCls validateClr"></div>-->
												</div>
												<div class="col-md-6 m_top10">
												</div>
												<div class="col-md-12 m_top10">
													<label>Appointment Purpose</label>
													<textarea class="form-control" id="appointmentReasonId" name="appointmentVO.reason"></textarea>
													<!--<div class="errorArCls validateClr"></div>-->
												</div>
											</div>
										</div>
										<div><img id="checkboxMemberAjax" src="images/icons/loading.gif" style="display:none;margin-top: 50px;"/></div>
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
												<button class="btn btn-success btn-block btnNewCustom1" type="button" onClick="savingAppointment();">CREATE APPOINTMENT</button>
											</div>
											<div style="margin-top:12px;"><img id="appntCreateAjax" src="images/icons/loading.gif" style="display:none;"/></div>
											<div class="col-md-6 m_top25" id="savingStatusDivId"></div>
										</div>
										<input type="hidden" id="dateTypeText" name="appointmentVO.appointmentPreferableTimeType">
										<input type="hidden" id="uniqueCode" name="appointmentVO.uniqueCode">
										<input type="hidden" id="appointmentUserId" name="appointmentVO.appointmentUserId">
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
												<label>Candidate Type</label>
												<!--<span style='color:red'> &nbsp * </span>-->
												<select class="cloneCandidateTypeCls">
													<option value="0">Select Candidate Type</option>
												</select>
												<!--<div class="cloneErrCandidateTypeCls validateClr"></div>-->
											</div>
											<div class="col-md-3 m_top10">
												<label>Designation</label>
												<span class="pull-right cloneDesignationSpanCls" style="display:none;">
													<i class="glyphicon glyphicon-plus-sign" title="Click here to add new designation." style="margin-left:10px;cursor:pointer"></i>
												</span>
												<select class="cloneDesignationCls " >
													<option value="0">Select Designation</option>
												</select>
												<div class="cloneErrCandidateDesgCls validateClr"></div>
											</div>
										 <div class="col-md-3 m_top10">
												<label>Contact Number</label>
												<input type="text" class="form-control cloneMobileCls">
												<!--<div class="cloneErrCandidateMobileCls validateClr"></div>-->
											</div>
										</div>
										<div class="row">											
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
												<label>Location Scope</label>
												<!--<span style='color:red'> &nbsp * </span>-->
												<select class="cloneLocationScopeCls regionScopeCls ">
													<option value="0">Select Scope</option>
													<option value="3">DISTRICT</option>
													<option value="4">CONSTITUENCY</option>
													<option value="5">MANDAL</option>
													<option value="6">VILLAGE</option>
													<option value="7">MUNICIPAL-CORP-GMC</option>
													<option value="8">WARD</option>
												</select>
												<!--<div class="cloneErrCandidateLcScopeCls validateClr"></div>-->
											</div>
										</div>
										<div class="row m_top10">
											<div class="col-md-4 cloneDistDivCls" style="display:none;">
												<label>Select District</label>
												<!--<span style='color:red'> &nbsp * </span>-->
												<select class="cloneDistrictCls ">
													<option value="0">select dist</option>
													<!--<option value="14">test dist1</option>-->
												</select>
												<!--<div class="cloneErrCandidateDistrictCls validateClr"></div>-->
											</div>
											<div class="col-md-4 cloneConstDivCls" style="display:none;">
												<label>Select Constituency</label>
												<!--<span style='color:red'> &nbsp * </span>-->
												<select class="cloneConstituencyCls " >
													<option value="0">select const</option>
													<!--<option value="142">test const1</option>-->
												</select>
												<!--<div class="cloneErrCandidateConstCls validateClr"></div>-->
											</div>
											<div class="col-md-4 cloneMandalDivCLs" style="display:none;">
													<label>Select Mandal/Muncilpality</label>
													<!--<span style='color:red'> &nbsp * </span>-->
													<select class="cloneMandalCls ">
														<option value="0">Select Mandal</option>
													</select>
													<!--<div class="cloneErrCandidateMandalCls validateClr"></div>-->
												</div>
												<div class="col-md-4 cloneVillageDivCls" style="display:none;">
													<label>Select Village/Ward</label>
													<!--<span style='color:red'> &nbsp * </span>-->
													<select class="cloneVillageCls">
														<option value="0">Select VILLAGE</option>
													</select>
													<!--<div class="cloneErrCandidateVillageCls validateClr"></div>-->
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
												<label class="radio-inline"><input type="radio" name="aptRequestedName"   id="aptRequestedRadioId" value="2"/>Appointment Request Created Dates</label>
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
							<div class="panel panel-default m_top10 panelWhite">
								<div class="panel-heading text-success">
									<h4 class="panel-title">OVERALL PENDING APPOINTMENT REQUESTS OVERVIEW</h4>
								</div>
								<div class="panel-body">
									<table>
										<tr>
										
										<td style="width: 20%;background-color:#0489B1; text-align: center; padding: 20px 60px; "><h4 class="text-success" style="color:#fff !important;">ALL <br> <span  id="AllStatus">0</span></h4></td>
											<td style="width: 20%;background-color:#339967; text-align: center; padding: 20px 60px; "><h4 class="text-success" style="color:#fff !important;">APPROVED <br> <span  id="approvedStatus">0</span></h4></td>
											<td style="width: 20%; background-color:#336799; text-align: center; padding: 20px 60px;color:#fff;"> <h4 class="text-success" style="color:#fff !important;">NOT ATTENDED <br> <span  id="notAttendedStatus">0</span></h4></td>
											<td style="width: 20%;background-color:#6A0208; text-align: center; padding: 20px 60px;color:#fff;"> <h4 class="text-success" style="color:#fff !important;">CANCELLED <br> <span  id="cancelledStatus">0</span></h4></td>
											<td style="width: 20%; background-color:#996532; text-align: center; padding: 20px 60px;color:#fff;"> <h4 class="text-success" style="width:110px;color:#fff !important;">RESCHEDULED <br> <span  id="reScheduledStatus">0</span></h4></td>
										</tr>
									</table>
								</div>
							</div>
                            <div class="row">
								<img id="confirmAppointmentsAjaxImg" src="images/icons/loading.gif" style="display:none; height: 50px; width: 50px;"/>
								<div id="confirmAppointmentsDivId"></div>
								
								<div class="col-md-12 changeClass" >
									<div class="block">
										<h4 class="text-success">
											CREATE APPOINTMENT TIME SLOT
											<!--<button class="btn btn-success pull-right">VIEW BOOKED TIME SLOTS</button>-->
										</h4>
										<div class="row">
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
																<div id="errorDivForTimeSlotId" class="validateClr m_left16"></div>
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
																	<label>From Time</label><!--<span style='color:red'> &nbsp * </span>-->
																	<div class="input-group inputSearch">
																		<span class="input-group-addon">
																			<i class="glyphicon glyphicon-time"></i>
																			<span class="caret"></span>
																		</span>
																		<input type="text" id="fromTimeId" class="form-control">
																	</div>
																</div>
																<div class="col-md-3">
																	<label>To Time</label><!--<span style='color:red;width:500px'> &nbsp * &nbsp </span>-->
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
																<div class="col-md-2 m_top10">
																<textarea  placeholder="Please Enter Comment..." cols="40" rows="2" id="commentTxt"></textarea>
																</div>
															</div>
														</div>
														
													</div>
												</div>
											</div>
										</div>
								<div class="row">
									<div class="col-md-12 m_top20">
										<div class="panel panel-default">
											<div class="panel-heading" style="background:#fff">
												<div class="row">
													<div class="col-md-8">
														<h4 class="panel-title text-capitalize text-success">Select Date To View Scheduled Appointment
														</h4>
													</div>
													<div class="col-md-4">
														<span class="pull-right timeSlotHideShowMainCls">
															<i class="glyphicon glyphicon-plus-sign timeSlotHideShowCls" title="View TimeSlot"  data-toggle="tooltip" data-placement="top" style="margin-right:17px;cursor:pointer;font-size:20px;color:#333;"></i>
														</span>
													</div>
												</div>
											</div>
											<div class="panel-body pad_0">
												 <!--  TIME SLOT --> 
												 <div class="row">
													<div class="col-md-12 changeTimeSlotClass" style="display:none">
														<div class="block m_0">
															<div class="row">
																<div class="col-md-6 m_top10">
																	<div class="form-inline">
																	  <div class="form-group">
																		<label>Date<span style="color:red;">*</span> </label>
																		<div class="input-group inputSearch">
																			<span class="input-group-addon">
																				<i class="glyphicon glyphicon-calendar"></i>
																			</span>
																			<input class="form-control" type="text" id="appointmentDateSlotHeadingId"/>
																		</div>
																	  </div>
																	  <input style="margin-left:8px" class="btn btn-success" type="button" value="Submit" id="timeSlotButtonId" onClick="getTimeSlotsForADayByAppytUserId();getAllScheduledApptsByDate(this)"/>
																	  <img src="images/search.gif" style="display:none;" id="ajaxImgFortimeSlotButtonId"/>
																	</div>
																</div>
																<div class="col-md-4 m_top10" style="color:red;font-size:16px;" id="timeSlotErrMsgId">
																</div>
																<div class="col-md-12 m_top10" id="timeSlotDatesBuildId">
																</div>
																
																
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								
								 
								</div>	
										<div class="row">
											<div class="col-md-12 m_top20" id="appointmentMembersDivId"></div>
										</div>
									</div>
								</div>
                            </div>
						</div>
						<div role="tabpanel" class="tab-pane" id="advncdDashboard">
							<jsp:include page="appointmentReportDashBoard.jsp" flush="true"/>
						</div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade bs-example-modal-sm" id="viewTimeSlotModelId" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-body" style="padding:25px;">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<label id="labelNameId">APPOINTMENT TIMESLOT UPDATION</label>
			
		<div id="dashBoardScheduleDivId"></div>
	  </div>
    </div>
  </div>
</div>
<div class="modal fade statusTrackingModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title text-capitalize" id="statusTrackingName">Appointment Status Tracking</h4>
      </div>
      <div class="modal-body">
        <div id="apptStatusTracking"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--appointment time slot-->
<div class="modal fade appointmentTimeSlotModalpopup" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
	 <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <h4 class="modal-title text-capitalize" >Update Appointment Time Slot</h4>
		  </div>
      <div class="modal-body">
		<div id="appointmentTimeSlotModal"></div>
      </div>
      <div class="modal-footer">
	  <div id="updateTimeSlotMsgShow"  class="col-xs-10"></div>
        <button type="button" class="btn btn-default closeForExtraTimeSlotCls" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- model for members-->
<div class="modal fade bs-example-modal-lg" id="membersModelId" tabindex="-1" role="dialog" aria-labelledby="membersModelLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	 <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <h4 class="modal-title text-capitalize" id="membersHeading">Appointment Members</h4>
		  </div>
      <div class="modal-body" style="padding:25px;">
		<div id="appointmentMembersDiv"></div>
	  </div>
    </div>
  </div>
</div>
<!-- end -->
<!--Designation block for other candidate-->
<div class="modal fade bs-example-modal-sm" id="blockForOtherCandidateModalId" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
       <div class="modal-body" style="padding:25px;">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<label>New Designation</label><span class="designationErrCls" style="color:red;"></span>
		<input type="text" class="form-control designationCls">
		</div>
		<div class="statusCls text-center"></div>
		<button class="btn btn-success btn-block m_top10 saveDesignationCls" >Save</button>
	  </div>
    </div>
 </div>
 
 <!--   MODAL POPUP FOR APPOINTMENT DETAILS  STRAT-->
<div class="modal fade " id="appointmentAllDetailsDiv" tabindex="-1" role="dialog">
  <div class="modal-dialog  modal-lg">
    <div class="modal-content" >
	<div id="buildAppointmentAllDetailsDiv"> </div>
	</div>
  </div>
</div>
<!--Reschedule Appointments Report Modal -->
<div id="rschdldAppntmntsRprtMdlId" class="modal fade" role="dialog">
  <div class="modal-dialog modal-dialog modal-lg" style='width:80%'>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" style="font-size:30px;">&times;</button>
		<h2>Rescheduled Appointments Report Details</h2>
       </div>
      <div class="modal-body">
	  <div>
	   <center>
			<img id="ttlRschdlAppntmntsImgPrcssngId" src="images/icons/loading.gif" style="width:50px;height:50px">
	   </center>
	  </div>
        <div id="rschdldAppntmntsRprtTblId"></div>
      </div>
    </div>
  </div>
</div>
 <!--   MODAL POPUP FOR APPOINTMENT DETAILS  END-->
<jsp:include page="appointmentCandidateHistory.jsp" flush="true"/>

<script src="dist/2016DashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/js/AppointmentScreenBootstrap.js" type="text/javascript"></script>
<!--<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>-->
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="dist/Appointment/MultiDatePicker/js/jquery-ui-1.11.1.js" type="text/javascript"></script>
<script src="dist/Appointment/MultiDatePicker/js/jquery-ui.multidatespicker.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="dist/Appointment/DragDrop/Sortable.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.min.js" type="text/javascript"></script>
<script src="dist/Appointment/AdvancedDashboard/advanceddashboard.js" type="text/javascript"></script>
<script type="text/javascript" src="dist/scroll/jquery.mCustomScrollbar.js"></script>
<script src="dist/Appointment/statusColorAppointment.js" type="text/javascript"></script>
<script src="dist/Appointment/AppointmentJS/appointment.js" type="text/javascript"></script>
<script type="text/javascript">
$('[data-toggle="tooltip"]').tooltip()
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
		$("#setTimeSlotBtnId").prop('disabled',true);
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
	  // var appointmentId=$(oldBlock).find(".appointmentCls").attr("attr_appointment_id");
		
	/*    var appointmentId=$('#confirmAppointmentBlockDropId > :not(.newClass)').attr("attr_appointment_id"); */

		$('#confirmAppointmentBlockDropId > :not(.newClass)').remove();
		$("#confirmAppointmentBlockDropId").find(".manageAppViewPanelClass").removeClass("newClass");
		
		if(oldBlock!="DROP HERE"){
			 var str='';
			  str+='<tr class="newClass manageAppViewPanelClass" role="row">';
			  str+=oldBlock;
			  str+='</tr>';
			  $("#dragId").append(str);
		}
		//Set Button disabling and Enabling when drag and Drop
		 if (isEmpty($('#confirmAppointmentBlockDropId'))) {
			   $("#setTimeSlotBtnId").prop('disabled',true);
		  }else{
			 $("#setTimeSlotBtnId").prop('disabled',false);
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
	//$("#todayApptsForAdvancedDashBrd").html('');
	
	$("#todayAptLoadingId").show();
	$("#todayForAdvancedDashBrdAjaxId").show();
	
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
	$("#todayForAdvancedDashBrdAjaxId").hide();
		if(result != null){
			buildTotalAppointmentStatusForToday(result);
			//buildTtlAppntmntSttsFrAdncDshBrd(result);
			//buildJSONForAppStatus(result);
			//buildTotalAppointmentStatus(result);
		}
		
	});     
}
function getAppointmentStatusCounts(){
	
	$("#totalAppointmentsId").html('');
	$("#totalApptsForAdvancedDashBrd").html('');
	
	$("#totalAptLoadingId").show();
	$("#totalForAdvancedDashBrdAjaxId").show();
	
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
	$("#totalForAdvancedDashBrdAjaxId").hide();
		if(result != null){
			buildJSONForAppStatus(result);
			buildTotalAppointmentStatus(result);
			buildTotalAppointmentStatusForNew(result);
		}
		
	});     
}


function buildJSONForAppStatus(result){	

    var jsonObj = [];
	var flag=false;
	for(var i in result){
		var color = getColorCodeByStatus(result[i].status);
		if(result[i].statusCount>0 && result[i].membersCount > 0)
		{	flag = true;
			jsonObj.push({"name":result[i].status,"y":result[i].statusCount,"color":color});
		}
	}
	if(flag==true){
		buildChartForAppStatus(jsonObj);
	}
	else{
		$('#LineChart').html("<div class='col-xs-12 m_top30' style='text-align:center;'><h4>NO DATA AVAILABLE</h4></div>");
		$('#LineChartForAdvancedDashBrd').html("<div class='col-xs-12 m_top30' style='text-align:center;'><h4>NO DATA AVAILABLE</h4></div>");
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
	
	 $('#LineChartForAdvancedDashBrd').highcharts({
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
	if(result!=null && result.length>0){
		for(var i in result){
			totalApptCount = totalApptCount + result[i].statusCount;
		}
	}
	
	    var str='';
		str+='<thead>';
		str+='<th style="font-weight:bold;"> APPOINTMENTS</th>';
		str+='<th style="text-align:center;font-weight:bold;"> TOTAL</th>';
		str+='</thead>';
		str+='<tbody>';
		
	for(var i in result){
		var color = getColorCodeByStatus(result[i].status);
		if(result[i].subList !=null && result[i].subList.length >0 ){
			
			
			str+='<tr style="color:'+color+'">';
			str+='<td><i style="cursor:pointer" class="glyphicon glyphicon-plus-sign changeIcon totalparentStatusClass pull-right"></i><span class="columnChart" style="background-color:'+color+'"></span>'+result[i].status+'</td>';
			if(result[i].statusCount == 0){
				str+='<td style="text-align:center;"> - </td>';
			}else{
				var statusArr= result[i].clickIds;
				str+='<td attr_statusArrId ="'+statusArr+'">'+result[i].statusCount+'</td>';
			}
			str+='</tr>';
			
           for(var j in result[i].subList){
			   var color = getColorCodeByStatus(result[i].subList[j].status);
				str+='<tr class="totalSubStatusClass" style="color:'+color+'" >';
				str+='<td style="background:#f8f8f8;">&nbsp;&nbsp;&nbsp;<span class="columnChart" ></span>'+result[i].subList[j].status+'</td>';
				if(result[i].subList[j].statusCount == 0){
					str+='<td style="text-align:center;background:#f8f8f8"> - </td>';
				}else{
					var statusArr= result[i].subList[j].clickIds;
					str+='<td style="background:#f8f8f8;text-align:center;" class="text-center"  attr_statusArrId ="'+statusArr+'">'+result[i].subList[j].statusCount+'</td>';
				}
				str+='</tr>';
			}
		
		}else{
			
			str+='<tr style="color:'+color+'">';
			str+='<td><span class="columnChart" style="background-color:'+color+'"></span>'+result[i].status+'</td>';
			if(result[i].statusCount == 0){
				str+='<td style="text-align:center;"> - </td>';
			}else{
				var statusArr= result[i].clickIds;
				str+='<td style="text-align:center;" attr_statusArrId ="'+statusArr+'">'+result[i].statusCount+'</td>';
			}
			str+='</tr>';
		}
		
	}
		str+='<tr>';
		str+='<td style="text-align:center;">TOTAL</td>';
		if(totalApptCount!=0){
			str+='<td style="text-align:center;"><h4>'+totalApptCount+'</h4></td>';
		}else{
			str+='<td> - </td>';
		}
		
		str+='</tr>';
	str+='</tbody>';
	$("#totalApptStatusCounts").html(totalApptCount);
	$("#totalAppointmentsId").html(str);
	
	$("#totalApptStatusCountsAdDash").html(totalApptCount);
	$("#totalApptsForAdvancedDashBrd").html(str);
	
	$(".totalSubStatusClass").hide();
}


function buildTotalAppointmentStatusForToday(result){
	var str='';
	
	var totalAppts =0;
	$.each(result.statusList,function(index,value){
	    totalAppts = totalAppts + value.totalCount;
	});
	if(totalAppts>0){
			str+='<tr class= "text-center todayTotalAppointmentStatusCls" style="font-weight:bold;cursor:pointer"><td>Today Appointments</td><td>'+totalAppts+'<div><center><img style="display:none;height:20px" src="images/icons/loading.gif" id="tdyAppntmntPrcssngImgId0"></center></div></td></tr>';	
	}else{
		str+='<tr class= "text-center" style="font-weight:bold;"><td>Today Appointments</td><td>'+totalAppts+'</td></tr>';
	}
	$.each(result.statusList,function(index,value){	
	var color = getColorCodeByStatus(value.status);
		if(value.subList !=null && value.subList.length >0 ){
			
			str+='<tr style="color:'+color+';">';
		
			str+='<td><i style="cursor:pointer" class="glyphicon glyphicon-chevron-down changeIcon parentStatusClass pull-right"></i>'+value.status+'</td>';
			var todayStatusArr = value.clickIds;
			if(value.totalCount == 0){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="todayappointmentStatusCls text-center" attr_appntmnt_status_id='+value.statusId+' attr_status_type="totalApproved" attr_todayStatusArr= "'+todayStatusArr+'" style="cursor:pointer;">'+value.totalCount+' <div><center><img style="display:none;height:20px" src="images/icons/loading.gif" id="tdyAppntmntPrcssngImgId'+value.statusId+'"></center></div></td>';
			}
			str+='</tr>';
			
			 for(var i in value.subList){
				 var color = getColorCodeByStatus(value.subList[i].status);
				 str+='<tr class="subStatusClass" style="color:'+color+';">';
				str+='<td style="background:#f8f8f8">&nbsp;&nbsp;&nbsp; '+value.subList[i].status+'</td>';
				var clickAray = value.subList[i].clickIds;
				if(value.subList[i].totalCount == 0){
					str+='<td class="text-center" style="background:#f8f8f8"> - </td>';
				}else{
					str+='<td style="background:#f8f8f8;cursor:pointer;" attr_appntmnt_status_id='+value.subList[i].statusId+' class="todayappointmentStatusCls text-center" attr_todayStatusArr= "'+clickAray+'" >'+value.subList[i].totalCount+' <div><center><img style="display:none;height:20px" src="images/icons/loading.gif" id="tdyAppntmntPrcssngImgId'+value.subList[i].statusId+'"></center></div></td></td>';
				} 
				str+='</tr>';
			 }
			
			
		}else{
			str+='<tr style="color:'+color+';">';
		
			str+='<td>'+value.status+'</td>';
			var todayStatusArr = value.clickIds;
			if(value.totalCount == 0){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="todayappointmentStatusCls text-center"  attr_appntmnt_status_id='+value.statusId+' attr_todayStatusArr= "'+todayStatusArr+'" style="cursor:pointer">'+value.totalCount+' <div><center><img style="display:none;height:20px" src="images/icons/loading.gif" id="tdyAppntmntPrcssngImgId'+value.statusId+'"></center></div></td></td>';
			}
			str+='</tr>';
		}
	});
	$("#todayAppointmentsId").html(str);
	//$("#todayApptsForAdvancedDashBrd").html(str);
	
	$(".subStatusClass").hide();
	
}
	
	$(document).on("click",".parentStatusClass",function(){
		$(this).toggleClass("glyphicon-chevron-up");
		$(".subStatusClass").toggle();
	});
	$(document).on("click",".totalparentStatusClass",function(){
		$(this).toggleClass("glyphicon-chevron-up");
		$(".totalSubStatusClass").toggle();
	});
	$(document).on("click",".appointmentSettings",function(e){
		$(".updateAppointment").hide();
		$(".messageBlock").hide();
		$(".appointmentSettingsBLock").hide();
		e.stopPropagation();
		$(this).parent().parent().find(".updateAppointment").show();
		})
	$(document).on("click","body",function(){
		$(".updateAppointment").hide();
		$(".appointmentSettingsBLock").hide();
		$(".messageBlock").hide();		
		});
		
	$(document).on("click",".settingsIcon",function(e){
		
		var appId=$(this).attr("attr_span_popup_id");
		
		$("#errSpanId"+appId).html("");
		
		e.stopPropagation();
		$(".updateAppointment").hide();
		$(".messageBlock").hide();
		$(".appointmentSettingsBLock").hide();
		 
		 
		 var apptSelectBoxId = $("#appointmentStatus"+appId).attr("id")
		 getUpdatedStatusForaAppointment($(this).attr("attr_appt_status_id"),apptSelectBoxId);
		 
		 var appointmentSettingsBLockId=$("#appointmentSettingsBLockId"+appId).attr("id");
		 $("#"+appointmentSettingsBLockId).show();
		
	})
	
    $(document).on("click",".messageIcon",function(e){
		e.stopPropagation();
		$(".updateAppointment").hide();
		$(".messageBlock").hide();
		$(".appointmentSettingsBLock").hide();
		$(this).parent().parent().find(".messageBlock").show();
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
	e.attr("attr_count",cloneCount);
	e.find(".cloneNameCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].name');
	e.find(".cloneNameCls").attr("id",'candidateNameId'+cloneCount);	
	e.find(".cloneErrCandidateNameCls").attr("id",'cloneErrCandidateNameId'+cloneCount);	
	
	e.find(".cloneDesignationCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].designationId');
	e.find(".cloneDesignationCls").attr("id",'designationSelId'+cloneCount);
	
	e.find(".cloneDesignationSpanCls").attr("id",'othrCnddtDsgntnBlckId'+cloneCount);
	e.find(".cloneDesignationSpanCls").attr("attr_coneId",cloneCount);
	
	e.find(".cloneDesignationCls").attr("attr_val",cloneCount);	
	e.find(".cloneErrCandidateDesgCls").attr("id",'cloneErrCandidateDesgId'+cloneCount);
	
	e.find(".cloneMobileCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].mobileNo');
	e.find(".cloneMobileCls").attr("id",'mobileNoId'+cloneCount);	
	//e.find(".cloneErrCandidateMobileCls").attr("id",'cloneErrCandidateMobileId'+cloneCount);
	
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
	//e.find(".cloneErrCandidateLcScopeCls").attr("id",'cloneErrCandidateLcScopeId'+cloneCount);
	
	e.find(".cloneLocationScopeCls").attr("onChange","showhideLocationBoxes("+cloneCount+")");
	
	e.find(".cloneDistDivCls").attr("id","districtId"+cloneCount+"DivId");		
	e.find(".cloneDistDivCls").addClass("locationCls"+cloneCount);
	
	
	e.find(".cloneDistrictCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].districtId');
	e.find(".cloneDistrictCls").attr("id",'districtId'+cloneCount);
	//e.find(".cloneErrCandidateDistrictCls").attr("id","cloneErrCandidateDistrictId"+cloneCount);
	e.find(".cloneDistrictCls").attr("onChange",'getConstituencies('+cloneCount+');');
	e.find(".cloneDistrictCls").attr("attr_val",cloneCount);
	
	e.find(".cloneConstDivCls").attr("id","constituencyId"+cloneCount+"DivId");
	e.find(".cloneConstDivCls").addClass("locationCls"+cloneCount);
	
	e.find(".cloneConstituencyCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].constituencyId');
	e.find(".cloneConstituencyCls").attr("id",'constituencyId'+cloneCount);
	//e.find(".cloneErrCandidateConstCls").attr("id",'cloneErrCandidateConstId'+cloneCount);
	e.find(".cloneConstituencyCls").attr("onChange",'getMandamMuncipalties('+cloneCount+');');
	e.find(".cloneConstituencyCls").attr("attr_val",cloneCount);
	
	e.find(".cloneMandalDivCLs").attr("id","tehsilId"+cloneCount+"DivId");
	e.find(".cloneMandalDivCLs").addClass("locationCls"+cloneCount);

	e.find(".cloneMandalCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].tehsilId');
	e.find(".cloneMandalCls").attr("id",'tehsilId'+cloneCount);
	//e.find(".cloneErrCandidateMandalCls").attr("id",'cloneErrCandidateMandalId'+cloneCount);
	e.find(".cloneMandalCls").attr("onChange",'getVillageWard('+cloneCount+');');
	e.find(".cloneMandalCls").attr("attr_val",cloneCount);
	
	e.find(".cloneVillageDivCls").attr("id","villageId"+cloneCount+"DivId");
	e.find(".cloneVillageDivCls").addClass("locationCls"+cloneCount);
	
	e.find(".cloneVillageCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].villageId');
	e.find(".cloneVillageCls").attr("id",'villageId'+cloneCount);
	//e.find(".cloneErrCandidateVillageCls").attr("id",'cloneErrCandidateVillageId'+cloneCount);
	e.find(".cloneVillageCls").attr("attr_val",cloneCount);
	
	e.find(".cloneCandidateIdCls").attr("id","candidateId"+cloneCount);
	e.find(".cloneCandidateIdCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].appointCandidateId');
	
	e.find(".cloneCandiImageUrlCls").attr("id","candiImageUrlId"+cloneCount);
	e.find(".cloneCandiImageUrlCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].candiImageUrl');
	e.find(".closeIcon").attr("clone_block_count",cloneCount);
	
	e.find(".cloneCandidateTypeCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].candidateTypeId');
	e.find(".cloneCandidateTypeCls").attr("id",'candidateTypeSelId'+cloneCount);
	e.find(".cloneCandidateTypeCls").attr("attr_val",cloneCount);	
	//e.find(".cloneErrCandidateTypeCls").attr("id",'cloneErrCandidateTypeId'+cloneCount);
	
	e.find(".cloneCandidateTypeCls").attr("onChange",'getDesignationsByTypeForChange('+cloneCount+'," ");');
	
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
			
				//getAppointmentLabels();					
				getTotalAppointmentStatus();
				getAppointmentStatusCounts();
				getCandidateDesignation();
				getDistricts();
				getAppointmentCreatedUsers();
				getAppointmentStatusList();
				getAppointmentPriority();
				searchTypeRadioCls(1);
				getAllCandidateTypes();
				setcolorsForStatus();
				getRescheduledsCounts();
				
			}, 1000);
			getAppointmentsLabelStatus("onload");
	
			
	});
	getAppointmentUsersDtls();
	getLoginUserAppointmentUserType();
	
	//getCandidateDesignation();
	
	var globalLoginUSerAppointmentUserTypeId;
	function getLoginUserAppointmentUserType(){
		
		$.ajax({
			type : 'GET',
			url : 'getLoginUserAppointmentUserTypeAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){
			if(result != null){
				globalLoginUSerAppointmentUserTypeId = result.appointmentUserTypeId;
			}
		});
	}
	var SMSEnablingDetailsArray=[];
	getSMSEnablingDetailsForAllStatus(SMSEnablingDetailsArray);
	
	
	
	function savingAppointment(){
		clearAllValidationCls();
		
		var flag = validateSavingDetails();
		if(!flag){
			
			$("#appntCreateAjax").css("display","block");			
			$("#dateTypeText").val($('input[name=dateTypeRadio]:checked').val());
			var temp = $("#appointmentUserSelectBoxId option:selected").attr("attr_unique_code")+"_"+$("#appointmentUserSelectBoxId").val();
			$("#uniqueCode").val(temp);
			
			$("#appointmentUserId").val($("#appointmentUserSelectBoxId option:selected").val());
			
			var uploadHandler = {
				upload: function(o) {
					$("#appntCreateAjax").css("display","none");
					uploadResult = o.responseText;
					showStatus(uploadResult);
				}
			};
	
			YAHOO.util.Connect.setForm('saveAppointment',true);
			YAHOO.util.Connect.asyncRequest('POST','appointmentSavingAction.action',uploadHandler);
		}
	}
	
	$(document).on("click",".searchCls",function(){
			$("#savingStatusDivId").html('');
	});
	$(document).on("click",".advanceSearchCls",function(){
			$("#savingStatusDivId").html('');
	});
	$(document).on("click",".advancedSearchBtn",function(){
		$("#savingStatusDivId").html('');
	});

	function showStatus(myResult,num){
		
		if(myResult.indexOf("success") > -1){
			$("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Appointment Created Successfully.</span>");
			setTimeout(function(){
			$("#savingStatusDivId").html("");
			}, 5000);
			//$( ".closeIcon" ).trigger( "click" );
			$("#moreCandidatesDivId").html('');
			$( "#multiDate" ).multiDatesPicker("resetDates");
			$(".dateRadioCls").prop('checked', false);
			cloneCount = 0;
			saveFieldsEmpty();
		}else if (myResult.indexOf("fail") > -1){
			//setTimeout(function(){
			$("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Appointment Creation Failed. Please Try Again.</span>");
			//}, 1000);
		}else{
			$("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Not Eligible To Create Appointment.</span>");
		}
	}
	function saveFieldsEmpty(){
	
    	$("#createAppTypeListId").val(0);
		$("#createAppTypeListId").dropkick('reset');
		//$("#selectManualDateId").trigger('click');
		$(".disableCls").attr('disabled',false); 
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
			str1+='<option value="0">Select District</option>';
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
	
	
	
$("#toTimeId").datetimepicker({format: 'LT'})	
$("#fromTimeId").datetimepicker({format: 'LT'})

$("#modalDateId").daterangepicker({singleDatePicker:false});	
$('#modalDateId').val(moment().format('MM/DD/YYYY') + ' - ' + moment().format('MM/DD/YYYY'));	
$("#mngAppntmntsDtPckrId").daterangepicker({singleDatePicker:true});
$(".daterangepicker_end_input").find("input").addClass("form-control");
$(".daterangepicker_start_input").find("input").addClass("form-control");
$("#mngAppntmntsDtPckrId").val("");
//$("#multiDate").multiDatesPicker({numberOfMonths: [1,2],minDate:0
//})
$("#dashboardSelectDateIds").daterangepicker({opens:"left", "parentEl": ".todayBlock",ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }});
//$('#dashboardSelectDateIds').val(moment().format('MM/DD/YYYY') + ' - ' + moment().format('MM/DD/YYYY'));
	$(".ranges").find("ul").prepend('<li class="activeCls">Total</li>');
	$(".ranges").find("ul li").removeClass("active");
	$(".ranges li:nth-child(1)").addClass("active");
	$(document).on("click",".activeCls",function(){
	$(".daterangepicker").css("display","none");
	
	}); 
$(document).on("click","#dashboardSelectDateIds",function(){
	$(".todayBlock").find(".daterangepicker").css("display","block");	
	//$(".show-calendar").css("display","none");	
	
	}); 
$(".todayBlock").find(".daterangepicker .ranges").addClass("rangesDashboard")
$(document).on("click",".rangesDashboard ul li",function(){
	$(".rangesDashboard ul li").find("ul li").removeClass("active");
	$(this).addClass("active");
	
	var selectedDay=$(this).html().trim();
	if(selectedDay == 'Total'){
		$("#dashboardSelectDateIds").val('');
	}
	
	 if(selectedDay != 'Custom'){
		getCandidCountsByStatesAction(); 
		getCandiCountsByLocations();
	 }
});
//$("#appointmentDateSlotId").daterangepicker({singleDatePicker:true,minDate:new Date()});
//$('#appointmentDateSlotId').val(moment().format('MM/DD/YYYY'));
//$("#appointmentDateSlotHeadingId").daterangepicker({singleDatePicker:true});


//$('#appointmentDateSlotHeadingId').val(moment().format('MM/DD/YYYY'));
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
	 
	
  
  $(document).on("click",".dateRadioCls",function(){
	  $("#multiDate").val("");
  });
  
  function showHideSearch(type)
  {
	  if(type == "search")
	  {
		  $(".searchCls").show();
		  $(".advanceSearchCls").hide();
		  $("#cadreCommitteeDiv_chosen").hide();
		  $(".levelShowCls").hide();
		  $(".stateShowCls").hide();
		  
	  }
	  else
	  {
		 $(".advanceSearchCls").show();  
		  $(".searchCls").hide();
		  $("#advanceSearchTypeId").val(0);
		   $("#advanceSearchTypeId").dropkick('reset');
		  $(".chosen-choices").css("display","none");
		    $("#cadreCommitteeDiv_chosen").show();
		    
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
	  var aptUserId = 0;
		  aptUserId = $("#appointmentUserSelectBoxId").val();
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			aptUserId:aptUserId
		  }
		$("#apptmemberDetailsDiv").html('');
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
								
								str+='<div class="col-md-6 col-md-offset-1">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
										str+='</div>';
										str+='<div class="media-body">';
										
							
										if(result[i].constituency !=null && result[i].constituency.length>0 ){
											<c:choose>
											<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS')}">
											if(result[i].id != null && result[i].id > 0){
												if(result[i].candidateType=="voter"){
												 str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div>';
												}else{
													str+='<a  target="_blank" data-toggle="tooltip" data-placement="top" title="Cadre Details" style="cursor:pointer;" href="cadreDetailsAction.action?cadreId='+result[i].id+'"><div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div></a>';
												}
											}
											else
											str+='<span style="color:#34A7C1;">'+result[i].name+'</span> ';
											</c:when>
											<c:otherwise>
											str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span></div>';
											</c:otherwise>
											</c:choose>
											
										}else{
											str+='<div style="color:#34A7C1;">'+result[i].name+'</div>';
										}
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p ><span><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].mobileNo+'</span>';
										}else{
											str+='<p><span><i class="fa fa-mobile" style="font-size:15px"></i>   - </span>';
										}
										
										if(result[i].designation !=null && result[i].designation.length>0){
											
												str+='<span style="margin-left:10px;"> '+result[i].designation+'</span></p>';
										}else{
											
											//str+='<span style="margin-left:10px;">Designation: - </span></p>';
											
											 if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
												 if(result[i].candidateType == 'cadre'){
													str+='<span style="margin-left:10px;"> - Cadre</span></p>'; 
												 }else{
													 str+='<span style="margin-left:10px;"> - </span></p>';
												 }
											}else{
												str+='<span style="margin-left:10px;"> - </span></p>';
											} 
										}
										str+='</div>';
									str+='</div>';
								str+='</div>';
							  
								if(result[i].appointmentCandidateId != null && result[i].appointmentCandidateId > 0){
									
									str+='<div class="col-md-1 m_top10"><a  title="Click here to View '+result[i].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].appointmentCandidateId+'" attr-name="'+result[i].name+'" attr-designation="'+result[i].designation+'" attr-mobile="'+result[i].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a></div>&nbsp;&nbsp;';
									
								}
								
								
							if(result[i].designation==null){
								result[i].designation = "";
							}
							if(result[i].aptExists == false && result[i].appointmentCandidateId != null){
								str+='<div class="col-md-1   m_top10" attr_id="'+result[i].id+'" >';
							}
							else{
								str+='<div class="col-md-1 col-xs-offset-1 m_top10" attr_id="'+result[i].id+'" >';
							}
							if(result[i].aptExists == false)
							{
								str+='<div class="btn btn-success btn-sm" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Create Appointment Request"> &nbsp;SELECT</label></div>';	
							}								
							else{
								str+='<label data-toggle="tooltip"  data-placement="top" title="This Candidate Already in '+result[i].aptName+' Appointment with '+result[i].aptStatus+' Status: you can not addtion to another Appointment"> ';
								str+=''+result[i].aptName+' - '+result[i].aptStatus+'';
								str+='</label>';
							}
							
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
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		/* $(document).on("click",".historyShowModalBtn",function(){
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
			$("#appCandidateNameId").html(''+name+' ('+mobile+')');
			getAppointStatusOverviewforCandidate(id);
			getAppointmentHistoryForCandidate(id);
		}); */
		applyPagination();
	}
	
	function getSMSEnablingDetailsForAllStatus(SMSEnablingDetailsArray){
		<c:if test="${not empty smsStatusEnabledList}">  
			 <c:forEach var="apptStatus" items="${smsStatusEnabledList}">
			   var obj = { statusId : '${apptStatus.id}', SMSStatus : '${apptStatus.name}' }
			   SMSEnablingDetailsArray.push(obj);
			</c:forEach>
		 </c:if>
	}
	function getCorrespondingEnablingStatusByStatusId(statusId){
		
		if(SMSEnablingDetailsArray != null && SMSEnablingDetailsArray.length > 0)
		{
			for(var i in SMSEnablingDetailsArray)
			{   
				if(SMSEnablingDetailsArray[i].statusId == statusId){
					
				   return SMSEnablingDetailsArray[i].SMSStatus;	
				}
					
			}
		}
	}
	</script>
	<script>
	
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
				var villageId =0;
				var wardId =0;
				if(result.villageId !=null){
					villageId = ''+result.villageId.toString().substring(1)+'';
				}
				if(result.wardId !=null){
					wardId = ''+result.wardId.toString().substring(1)+'';
				}
				
				var temp=parseInt(cloneCount)-1;
				
				$('#candidateNameId'+temp).val(name);
				$('#block'+temp).attr("attr_blk",closeId1);
				$('#mobileNoId'+temp).val(mobile);
				$('#voterCardNoID'+temp).val(votercardno);
				
				if(membershipno!= 'null'){
				   $('#membershipNumId'+temp).val(membershipno);
				   $('#membershipNumId'+temp).prop("readonly",true);	
				}
			  
				var candidateType1;
				$('#designationSelId'+temp+' option').each(function() {
					
					if($(this).text().toUpperCase() == ''+designation.toUpperCase()+'') {
						
						candidateType1 = $(this).attr("typeId");
						if(designation != null){
							getDesignationsByType(candidateType1,'designationSelId'+temp);
							$('#candidateTypeSelId'+temp).val(candidateType1);
							var selectcc = new Dropkick('#candidateTypeSelId'+temp);
							selectcc.refresh();
						}
					}
			  });
			 
				var selectx = new Dropkick('#designationSelId'+temp);
				selectx.refresh();
					
			    if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
					
					if( candidateType == 'voter'){
						
						if(designation == null || designation.length == 0)
						{
						$('#candidateTypeSelId'+temp).val(4);
						var selectcc = new Dropkick('#candidateTypeSelId'+temp);
				        selectcc.refresh();
						popDesignation = "Other";
				        getDesignationsByType(4,'designationSelId'+temp);
				        $('#othrCnddtDsgntnBlckId'+temp).show();
						}
						else
						{
							getDesignationsByType(candidateType1,'designationSelId'+temp);
							$('#candidateTypeSelId'+temp).val(candidateType1);
							var selectcc = new Dropkick('#candidateTypeSelId'+temp);
							selectcc.refresh();
						}
						
					}else if( candidateType == 'cadre'){
						
						if(designation == null || designation.length == 0)
						{
						$('#candidateTypeSelId'+temp).val(3);
			 	        var selectcc = new Dropkick('#candidateTypeSelId'+temp);
				        selectcc.refresh();
				        popDesignation = "Cadre";
				        getDesignationsByType(3,'designationSelId'+temp);
						}
						else
						{
							getDesignationsByType(candidateType1,'designationSelId'+temp);
							$('#candidateTypeSelId'+temp).val(candidateType1);
							var selectcc = new Dropkick('#candidateTypeSelId'+temp);
							selectcc.refresh();
						}
					
					}
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
						var villageWardId = result.villWardList[i].locationId.toString().substring(1);
						str+='<option value="'+villageWardId+'">'+result.villWardList[i].locationName+'</option>';
					}
					$('#villageId'+temp).html(str);
					var select = new Dropkick('#villageId'+temp);
					select.refresh();
					}
					if(villageId !=null && villageId>0){
						$('#villageId'+temp).val(villageId);
					}					
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
						var villageWardId = result.villWardList[i].locationId.toString().substring(1);
						
						str+='<option value="'+villageWardId+'">'+result.villWardList[i].locationName+'</option>';
					}
					$('#villageId'+temp).html(str);
					var select = new Dropkick('#villageId'+temp);
					select.refresh();
					}
					if(wardId !=null && wardId>0){
						$('#villageId'+temp).val(wardId);
					}
					
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
   
  
	$("#searchStrId").keypress(function(e) {
		if(e.which == 13) {
			getSearchDetails('false');
		 }
     });
	 var searchJobj;
	function getSearchDetails(isCheckParticlurUser)
	{
		
		searchJobj;
		
		//$("#upcomingAppointMentId").html("");
		//$("#inprogreessAppointMentId").html("");
		//$("#completedAppointMentId").html("");
		$("#searchApptmntDivId").empty();
		$(".appointmentSettings").show();
		var createdBy =$("#appointmentcreatedBy").val();
		
		var statusArray=[];
		
		if(isCheckParticlurUser == true){
			
			if( globalLoginUSerAppointmentUserTypeId == 1){
				var statusId = 1; 
				statusArray.push(statusId);
			}
		}else{
			var statusId =$("#selectStatusId").val();
			if(statusId !=null && statusId>0){
				statusArray.push(statusId);
			}
		}
		var appointmentUserId =$("#appointmentUserSelectBoxId").val();
		var searchStr=$("#searchStrId").val().trim();
		var strDate='';
		var endDate=''; 
		var datesArry = $("#dashboardSelectDateIds").val();
		if(datesArry !=null && datesArry !=undefined && datesArry.length>0){
			strDate=datesArry.split("-")[0];
			endDate=datesArry.split("-")[1];		
		}
		
		var jsObj={
			createdBy : createdBy,
			appointmentUserId:appointmentUserId,
			searchStr:searchStr,
			strDate:strDate,
			endDate:endDate,
			statusId:statusId,
			task:""			
		}
		
		var jsObj1={
			createdBy : createdBy,
			appointmentUserId:appointmentUserId,
			searchStr:searchStr,
			strDate:strDate,
			endDate:endDate,
			statusArray:statusArray,
			type:'',
			task:""
			
		}
		$("#srhPrcssngImgId").show();
		searchJobj = jsObj;
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj1)}
			}).done(function(result){
				$("#srhPrcssngImgId").hide();
				buildAppointmentSearchResult(result,statusId," ");				
			})
	}
	
	$(document).on("click",".messageIcon",function() {		
		$(".errorCls").html("");
	});
	$(document).on("click",".appointmentStatus",function() {
		
		var apptId = $(this).attr("appointmentid");
		var updateDivId=$("#appointmentSettingsBLockId"+apptId).attr("id");
		var processImgId=$("#prcssngImgFrUpdtId"+apptId).attr("id");
		
		$("#errSpanId"+apptId).html("");
		
		var validCheck =false;
		var statusChange=false;
		
		var smsCheck = false;
		var smsText = '';
		if($("#smsChkId"+apptId).is(':checked')){
			
			if($("#smsTextId"+apptId).val().trim() == "" || $("#smsTextId"+apptId).val() == null || $("#smsTextId"+apptId).val().length == 0){
				$("#errSpanId"+apptId).html("Please Enter SMS Text.");
				return;	
			}else{
				validCheck = true;
				
				smsCheck = true;
				smsText = $("#smsTextId"+apptId).val().trim();
			}
		}
		
		var commentTxt = '';
		if($("#comentChkId"+apptId).is(':checked')){
			
			if($("#commentTxtId"+apptId).val().trim() == "" || $("#commentTxtId"+apptId).val() == null || $("#commentTxtId"+apptId).val().length == 0){
				$("#errSpanId"+apptId).html("Please Enter Comment.");
				return;	
			}else{
				validCheck = true;
				commentTxt = $("#commentTxtId"+apptId).val().trim();
			}
		}
		
		var statusId   = $("#appointmentStatus"+apptId).val();
		var statusText = $("#appointmentStatus"+apptId).find("option:selected").text();
		
		if( statusId > 0 ){
			statusChange = true;
			validCheck = true;
		}
		
		if(!validCheck){
			$("#errSpanId"+apptId).html("Select Status Or Comment Or Send SMS");
			return;
		}
		
		$("#"+processImgId).show();
		
		var currentStatusId = $(this).attr("appointmentStatusId");
		var currentUpdateButton = $(this);
		
		var jsObj={
			appointmentId : apptId,
			date : '',
			time : '',
			smsCheck : smsCheck,
			smsText:smsText,
			statusId:statusId,
			currentStatusId:currentStatusId,
			commentTxt:commentTxt,
			apptuserId : $("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			
				  url      :   "updateAppointmentStatusAction.action",
				  type     :   "POST",
				  datatype :   "json",
				  data     :  { task:JSON.stringify(jsObj) },
				  success  :  function (result) {
								
								$("#"+processImgId).hide();
								if(result != null && result.message=="success"){
									$(".msgDiv"+apptId).html("Updated Successfully.").css("color","green");
									setTimeout(function(){$("#"+updateDivId).hide();},2000);
									setTimeout(function(){$(".msgDiv"+apptId).html("");},2000);
									
									$("#smsChkId"+apptId).attr("checked",false);
									$("#comentChkId"+apptId).attr("checked",false);
									
									$("#commentTxtId"+apptId).attr("placeholder", "Please Enter Comment...").val("");
									$("#smsTextId"+apptId).attr("placeholder", "Please Enter Sms...").val("");
									
									$("#commentTxtId"+apptId).hide();
									$("#smsTextId"+apptId).hide();
									
									// Refresh the block
									if(statusChange){
										
										$(currentUpdateButton).attr("appointmentstatusid",statusId);
										$(currentUpdateButton).closest('.manageAppViewPanelClass').find('.settingClass').find('span').html(statusText);
										$(currentUpdateButton).closest('.manageAppViewPanelClass').find('.settingClass').find('.settingsIcon').attr("attr_appt_status_id",statusId);
										
										var apptSelectBoxId = $("#appointmentStatus"+apptId).attr("id")
										
										if( getCorrespondingEnablingStatusByStatusId(statusId) == "Y" ){
											$('#smsEnabledDivId'+apptId).show();
										}else{
											$('#smsEnabledDivId'+apptId).hide();
										}
										
										$('#scheduledTimeId'+apptId).remove();
										getUpdatedStatusForaAppointment(statusId,apptSelectBoxId);
										getAppointmentStatusCounts();
										getTotalAppointmentStatus();
									}
									
									 //var ele = new Dropkick("#appointmentStatus"+apptId);
									// ele.select(0);
								}else{
									$(".msgDiv"+apptId).html("Please try Again.").css("color","red");
								}
							  },
				  error     : function (jqXHR, textStatus, errorThrown) {
					              $("#"+processImgId).hide();
								  if(jqXHR.status == 500){
									  $(".msgDiv"+apptId).html("Unexpected Error.Please Try Again..").css("color","red");
								  }else{
									 alert('Unexpected error.');
								  }
							  }
			 });
			 
		
	});
	
	$(document).on("click",".updateAll",function() {
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
	
	$(document).on("change",".status",function(){
		
		var val = $(this).val();
		var apptId = $(this).attr("attr_appt_id");
		
		if( getCorrespondingEnablingStatusByStatusId(val) == "Y" ){
			$('#smsEnabledDivId'+apptId).show();
		}else{
			$('#smsEnabledDivId'+apptId).hide();
		}
	});
	
	function buildAppointmentSearchResult(result,statusId,statusType)
	{
		exportToExcel(result,statusId,statusType);
	    setcolorsForStatus();
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		if(statusType=="singleStatus"){
			if(result[0].appointmentStatus!=null){
			str+='<h4 class="text-success">'+result[0].appointmentStatus.toUpperCase() +' APPOINTMENTS <input type="button"  value ="EXPORT EXCEL" class="exportToExcelCls btn btn-xs btn-info col-md-offset-6" style="padding: 6px;"></input></h4>';	
			}
		}else if(statusType=="totalApproved"){
			 str+='<h4 class="text-success">TOTAL APPROVED APPOINTMENTS <input type="button"  value ="EXPORT EXCEL" class="exportToExcelCls btn btn-xs btn-info col-md-offset-6" style="padding: 6px;"></input></h4>';
		}else{
			str+='<h4 class="text-success">TOTAL APPOINTMENTS<input type="button"  value ="EXPORT EXCEL" class="exportToExcelCls btn btn-xs btn-info col-md-offset-6" style="padding: 6px;"></input></h4>';
		}
		
		str+='<div class="updateAppointment arrow_box">';
			str+='<label class="radio-inline">';
		str+='<input type="radio" value="6" name="CompletedRadio1" class="statusAllCompleted" checked>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="7" name="CompletedRadio1" class="statusAllCompleted">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 CompletedSmsText" ></textarea>';
		str+='<span class="msgDiv2Completed"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="Completed">UPDATE APPOINTMENT</button>';
		str+='</div>';
		str+='<table id="searchApptmntdatatable" class="tablepaddingremove">';
		str+='<thead><th></th><th></th></thead>';
		if(result != null)
		{
			var xindex = 0;
			
			for(var i in result)
			{ 
			if( xindex == 0)
			{
				str+='<tr style="background:#f2f2f2">';
			}
				str+='<td style="vertical-align:top;width:50%">';
					
					str+='<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">';
					str+='<div class="panel panel-default manageAppViewPanelClass m_top5">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="settingClass"><i  attr_span_popup_id='+result[i].appointmentId+' attr_appt_status_id='+result[i].statusId+' class="glyphicon glyphicon-cog settingsIcon pull-right" title="Appointment Status Update" data-toggle="tooltip" data-placement="top"></i>ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							var colorstatus = result[i].appointmentStatus;
							var color = getColorCodeByStatus(colorstatus);
							str+='<span style="font-weight:bold;color:'+color+'" id="statusSpanId'+result[i].appointmentId+'">'+result[i].appointmentStatus+'</span>';
							if(result[i].date != "" && result[i].statusId == 3){
							str+='<span id="scheduledTimeId'+result[i].appointmentId+'" class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].date+'&nbsp;&nbsp;</span>';
							}
							str+='</p>';
							str+='<div class="appointmentSettingsBLock arrow_box" id="appointmentSettingsBLockId'+result[i].appointmentId+'">';
							str+='<label>Select Appointment Status</label><span style="color:red;" id="errSpanId'+result[i].appointmentId+'"></span>';
								 str+='<select attr_appt_id='+result[i].appointmentId+' class="status'+result[i].appointmentId+' status" id="appointmentStatus'+result[i].appointmentId+'" style="box-shadow:none;margin-top:0px;padding:0px;">';
								str+='</select>';
								
							str+='<div class="row m_top10">';
							str+='<div class="col-xs-12">';
							str+='<label class="checkbox-inline" style="margin-left: 0px;">';
							str+='<input type="checkbox" attr_cmmnt_chckbx_id='+result[i].appointmentId+' value="2"  name="upcomeRadio" id="comentChkId'+result[i].appointmentId+'" class="comment'+result[i].appointmentId+' status showCmmtBox">Add Comment &nbsp;&nbsp;';
							str+='</label>';
							str+='<textarea placeholder="Please Enter Comment..." cols="35" rows="2" class="commentTextCls'+result[i].appointmentId+' form-control" id="commentTxtId'+result[i].appointmentId+'" style="display:none;padding:8px;"></textarea>';
							str+='</div>';
							
							if (getCorrespondingEnablingStatusByStatusId(result[i].statusId) == "Y" ){
								str+='<div id="smsEnabledDivId'+result[i].appointmentId+'" class="col-xs-12">';
							}else{
								str+='<div id="smsEnabledDivId'+result[i].appointmentId+'" class="col-xs-12" style="display:none">';
							}
								str+='<label class="checkbox-inline" style="margin-left: 0px;">';
								str+='<input type="checkbox" attr_sms_chckbx_id='+result[i].appointmentId+' value="3"  name="upcomeRadio" id="smsChkId'+result[i].appointmentId+'" class="smsCheckedCls'+result[i].appointmentId+' showSmsBox" >Send Sms &nbsp;&nbsp;';
								str+='</label>';
								
								str+='<textarea placeholder="Please Enter Sms..." class=" m_top10 form-control  smsTextCls'+result[i].appointmentId+'" id="smsTextId'+result[i].appointmentId+'" style="display:none;"></textarea>';
								
								str+='</div>';
							
							
							str+='</div>';
						
    						str+='<span class="msgDiv'+result[i].appointmentId+'"></span>';
							str+='<img id="prcssngImgFrUpdtId'+result[i].appointmentId+'" style="display:none;" src="images/search.gif">';
							str+='<button class="btn btn-block btn-success m_top10 appointmentStatus" appointmentStatusId='+result[i].statusId+' appointmentId='+result[i].appointmentId+' >UPDATE APPOINTMENT</button>';
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
						str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
						str+='</div>';
						str+='<div class="media-body">';
						
						
						var name = result[i].subList[j].name;
						<c:choose>
							<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS')}">
							
							if(result[i].subList[j].tdpCadreId != null && result[i].subList[j].tdpCadreId > 0)
							{
								
								str+='<a  target="_blank" data-toggle="tooltip" data-placement="top" title="Cadre Details" style="cursor:pointer;display:inline-block;" href="cadreDetailsAction.action?cadreId='+result[i].subList[j].tdpCadreId+'"><div style="color:#34A7C1;">'+name+'</div></a>';
							}
							else{
								str+='<span>'+name+'</span> ';
							}
							
							</c:when>
							<c:otherwise>
							    str+='<span style="color:#34A7C1;">'+name+'</span>';
							</c:otherwise>
						</c:choose>		
						
						var candDesignation = result[i].subList[j].candDesignation;
						var location = result[i].subList[j].constituency;
						var candiDesignationBuild = "";
						
						if(candDesignation!=null && candDesignation.length>0){
							candiDesignationBuild =  candDesignation;
							if(location!=null && location.length>0){
								candiDesignationBuild = candiDesignationBuild + " - " + location ;
							}
						}
						
						
						if(result[i].subList[j].id != null && result[i].subList[j].id > 0){
								str+='<a style="display:inline-block;" title="Appointments History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn pull-right"  style="cursor:pointer;" attr-id="'+result[i].subList[j].id+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><img src="dist/Appointment/img/view-Appt-History-icon.png"  alt="ViewApptHistory" style="height:16px;cursor:pointer;margin-right:5px;"/></a>&nbsp;&nbsp;';
						}
						str+='<p> '+candiDesignationBuild+'</p>';
						if(globalLoginUSerAppointmentUserTypeId != 1){

							if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
								str+='<p><i class="fa fa-mobile" style="font-size:15px"></i>  '+result[i].subList[j].mobileNo+'</p>';
							}
							if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else if(result[i].subject!=null && result[i].subject.length>0){
							  str+='<p class="" >Purpose:'+result[i].subject+' </p>';
							}
							
						}else{
							if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else if(result[i].subject!=null && result[i].subject.length>0){
							  str+='<p  class="">Purpose:'+result[i].subject+' </p>';
							}
							
						}
						
					
						str+='</div>';
						str+='</div>';
						//multiple
						
						str+='</li>';
						
						}
						str+='</ul>';
						
					str+='<div class="m_top10">';
					//str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					
					
					
					//Preferable Dates Scenario start
					str+='<div class=" m_top10">';
							
							if(result[i].apptpreferableDates != null){	
							str+='<p class="b_top1 pad_5"><b>REQUESTED DATES : </b>';							
								str+='<span >'+result[i].apptpreferableDates+'</span></p>';
							}else if(result[i].apptpreferableDates == null && result[i].maxDate != null && result[i].minDate != null && result[i].dateType != null && result[i].dateType.trim() != ""){
								str+='<p class="b_top1 pad_5"><b>REQUESTED DATES : </b>';
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+')</span></p>';
							}
							
							if(result[i].requestedDate !=null && result[i].requestedDate.length>0){
							str+='<p class="b_top1 pad_5"> <b>CREATED DATE : </b>';								
								str+='<span >'+result[i].requestedDate+'</span></p>';
							}
					
						
					//Preferable Dates Scenario end
					
					if (getCorrespondingEnablingStatusByStatusId(result[i].statusId) == "Y" ){
						
						str+='<img src="dist/Appointment/img/message.png" id="enabledSMSId'+result[i].appointmentId+'" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" />';
					}else{
						str+='<img src="dist/Appointment/img/message.png" id="enabledSMSId'+result[i].appointmentId+'" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" style="display:none"/>';
					}
					
					  str+='<img src="dist/Appointment/img/reqHistoryicon+.png" class="pull-right statusTrackingModalbtn" attr-id='+result[i].appointmentId+' attr-aptName='+result[i].appointmentUniqueId+' alt="ViewReqHistory" style="height:16px;cursor:pointer;margin-right:5px;" title="Appointment Requested History" data-toggle="tooltip" data-placement="top" />'; 
					str+='<span attr_appt_id='+result[i].appointmentId+' class="glyphicon glyphicon-info-sign pull-right appointmentAllDetailsModel" aria-hidden="true" title="Appointment Complete Information" data-toggle="tooltip" data-placement="top" style="cursor:pointer; margin-right: 10px;"></span>';
					str+='</p>';
					
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					
					str+='</div>';
					str+='</div>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</td>';
				xindex++;
				if(result.length-1 == i){
					if(xindex % 2 == 1){
						str+='<td></td>';
						str+='</tr>';
					}
				}
				 if( xindex == 2){
					str+='</tr>';
					xindex = 0;
				} 
			
			}
			
		}
		else
		{
			flag = false;
		}
		
		if(flag == false)
		{
			$(".completedSetting").hide();
			str+='No Data Available';	
		}
		$("#searchApptmntDivId").html(str);
		$('#searchApptmntdatatable').dataTable({
							"aaSorting": [],
							"iDisplayLength" : 10	,
							 "bDestroy": true,
							 "bFilter": false,
							"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]		
						});
		$("#searchApptmntdatatable_length").addClass("pull-right")
		$("#searchApptmntdatatable_wrapper").css("margin-top","-15px")
		$('[data-toggle="tooltip"]').tooltip();
		if(flag == false)
		$(".completedSetting").hide();
	}
	
		$(document).on("click",".showCmmtBox",function(){
				 var commentCheckBoxId=$(this).attr("attr_cmmnt_chckbx_id");
			     var commentBoxId=$("#commentTxtId"+commentCheckBoxId).attr("id");
				 $("#"+commentBoxId).show();
			if($(this).prop("checked") == false){
				 $("#"+commentBoxId).hide();
				 $("#"+commentBoxId).attr("placeholder", "Please Enter Comment...").val("");
			}
		});
		$(document).on("click",".showSmsBox",function(){
			   var smsCheckBoxId=$(this).attr("attr_sms_chckbx_id");
			   var smsBoxId=$("#smsTextId"+smsCheckBoxId).attr("id");
			  	$("#"+smsBoxId).show();
			if($(this).prop("checked") == false){
				$("#"+smsBoxId).hide();
				$("#"+smsBoxId).attr("placeholder", "Please Enter Sms...").val("");
			}
		});
		$(document).on("click",".statusTrackingModalbtn",function(){
			$(".statusTrackingModal").modal('show');
			var appontmntId = $(this).attr("attr-id");
			var aptName = $(this).attr("attr-aptName");
			getStatusTrackingDetls(appontmntId,aptName);
		});
</script>
<script>
	
	$(document).on("click",".createAppReqCls",function(){
		
		clearAllValidationCls();
		clearFields();
		//clear create appointment fields after changing the tab  
		 $("#moreCandidatesDivId").html('');
		 $( "#multiDate" ).multiDatesPicker("resetDates");
		 $(".dateRadioCls").prop('checked', false);
		 cloneCount = 0;
 		 saveFieldsEmpty();
		 //end
		$("#errDigitsId").html('');
		$("#searchValueId").val('');
		$("#searchTypeId").val(0);
		 $("#advanceSearchTypeId").val(0); 
		 $("#advanceSearchValueId").val('');
		$("#createAppTypeListId").val(0);
		$("#errorDivId").html('');
		$("#cadreCommitteeDiv_chosen").hide();
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
		$("#confirmAppointmentsDivId").html("");
		$("#tablePluginId").html("");
		$("#tablePluginDateId").html("");
		$("#pluginTableId").html("");
		$("#timeSlotsWarnId").html("");
		//setting default time format
		 $("#fromTimeId").val("00:00 AM");
		 $("#toTimeId").val("00:00 PM");
	});
	

var userTypeId = 0;
getAppointmentStatus();
function getAppointmentStatus(){
		
	var jsObj = {};
	$.ajax({
		type : 'GET',
		url : 'getAppointmentStatusByUserIdAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result != null){
			var statusObj = [];
			userTypeId = result[0].districtid;
			for(var i in result){
				statusObj.push({"id":result[i].id,"name":result[i].name});
			}
		}
	});     
}

</script>
<script>

function getUpdatedStatusForaAppointment(currentStatusId,apptSelectBoxId){
	
	$('#'+apptSelectBoxId).find('option').remove();
    $('#'+apptSelectBoxId).append('<option value="0">Select Status</option>');
	
	
		<c:forEach items="${appointmentStatusList}" var="status">

		var userTpid = parseInt(${status.typeId});
		var fromStatusId = parseInt(${status.statusId});
		var tostatusid = parseInt(${status.toStatusId});
		var toStts = '${status.toStatus}';
		
			if(userTpid == userTypeId && fromStatusId == currentStatusId){				
				if(tostatusid != 3){
					$('#'+apptSelectBoxId).append('<option value="'+tostatusid+'">'+toStts+'</option>');
				}
			}	
		</c:forEach>
		
		$('#'+apptSelectBoxId).dropkick();
		var ele = new Dropkick('#'+apptSelectBoxId);
		ele.refresh();
		
		
}
$(document).on("click",".dashboardPanelCollapse",function(){
	$(".dashboardPanelCollapseBody").collapse("toggle");
	$(this).find("i").toggleClass("glyphicon-minus-sign");
});
</script>
</body>
</html>