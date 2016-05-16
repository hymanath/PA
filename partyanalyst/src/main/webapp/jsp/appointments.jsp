
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
<style type="text/css">
.tableC thead th , .tableC tr td{font-size:11px;padding:4px !important;}
.tablepaddingremove thead th , .tablepaddingremove tr td{padding:0px !important;}
.SelectPosition li{padding:5px;width:100%;border-top:1px solid #ccc;border-left:1px solid #ccc;border-right:1px solid #ccc;list-style:none;cursor:pointer;}
.daterangepicker_end_input{padding:0px !important;}
.SelectPosition li:last-child{border-bottom:1px solid #ccc;}
.SelectPositionScroll{width: 100%;height: 140px;overflow: auto;}
.SelectPosition{padding:0px;margin-top:10px;}
.m_left16{margin-left: 16px !important;}
.validateClr{color:red;}
.ui-widget-header{background:#fff !important;border:1px solid #fff !important;color:#333;font-weight:400;}
.m_top5{margin-top:5px;}
.closeIcon{background:#ccc;color:#666;padding:5px;position:absolute;right:0px;top:0px;cursor:pointer;}
.block{position:relative;}
.m_top10{margin-top:10px;}
.prev,.next{width:60px !important;}
#upcomingAppointments,#inprogreessAppointMentId,#completedAppointMentId {height:36px;}
.tableAppointment thead th{background: #f2f2f2 none repeat scroll 0 0;border: 1px solid #fff !important;padding-left: 8px !important;font-weight: normal;}
.removetopborder td{border-top: none !important;}
.addwidth{width:250px !important;}
.advanceNameCls{heigth:30px;}
.displayrow ul li{display: inline;padding:7px !important;}
.alignmentprefrabledates{border: 1px solid rgb(221, 221, 221) !important;padding: 8px !important;margin-right: -3px !important;}

.tableCol tr td
{
	border-right:5px solid #fff !important;
	background:#ddd
}
.tableCol tr:nth-child(3) td
{
	border-bottom:5px solid #fff !important;
}
.bg_f2{background:#f2f2f2}
.bg_e6{background:#e6e6e6}
.pad_5{padding:2px 5px;}
.pad_10{padding:8px !important;}
.font10{font-size:10px;}

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
												<h2 class="m_top10 text-success">TODAY APPOINTMENTS</h2>
												<table class="table table-condensed tableAppointment" style="font-size:20px;" id="todayAppointmentsId">
													<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="todayAptLoadingId"></center></div>
												</table>
											</div>
											<div class="col-md-6">
												<div id="LineChart" style="width:500px;height:300px;"></div>
												<div  style="text-align: center;">Total Appointments - <span id="totalApptStatusCounts"></span></div>
											</div>
										</div>
									</div>
									<!-- EXPORT TO EXCEL TABLE ID-->
									<div style='display:none' id="appntmntCnddtDtlsTblId"></div>
									
									<table class="table table-bordered bg_ff m_top10">
										<tr>
											<td>
												
											</td>
											<td>
												<table class="table removetopborder">
													<tr>
														
														<td>
															
														</td>
													</tr>
												</table>
                                            	
                                            </td>
										</tr>
									</table>
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
								
								<!-- Hide show Divs -->
								<span class="pull-right timeSlotHideShowMainCls">
													<i class="glyphicon glyphicon-plus-sign timeSlotHideShowCls" title="view TimeSlot" style="margin-right:17px;cursor:pointer;font-size:20px;"></i>
								</span>
								
								  <!--  TIME SLOT --> 
									<div class="col-md-12 changeClass changeTimeSlotClass" style="display:none">
										<div class="block">
											<div class="row">
												<div class="col-md-12">
													<h4 class="text-capitalize text-success">Select Date To View Appointment Time Slot</h4>
												</div>
												
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
													</div>
												</div>
												
												<div class="col-md-2 m_top10">
													<input class="btn btn-success" type="button" value="Submit" id="timeSlotButtonId" onClick="getTimeSlotsForADayByAppytUserId();getAllScheduledApptsByDate()"/>
												<img src="images/search.gif" style="display:none;" id="ajaxImgFortimeSlotButtonId"/>
												</div>
												<div class="col-md-4 m_top10" style="color:red;font-size:16px;" id="timeSlotErrMsgId">
												</div>
												<div class="col-md-12 m_top10" id="timeSlotDatesBuildId">
												</div>
												
												
											</div>
										</div>
									</div>
																	
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
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	str+='<tr class= "text-center" style="font-weight:bold;"><td>Today Appointments</td><td>'+totalAppts+'</td></tr>';	
	$.each(result.statusList,function(index,value){	
	var color = getColorCodeByStatus(value.status);
		if(value.subList !=null && value.subList.length >0 ){
			
			str+='<tr style="color:'+color+';">';
		
			str+='<td><i style="cursor:pointer" class="glyphicon glyphicon-chevron-down changeIcon parentStatusClass pull-right"></i>'+value.status+'</td>';
			var todayStatusArr = value.clickIds;
			if(value.totalCount == 0){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="todayappointmentStatusCls text-center" attr_status_type="totalApproved" attr_todayStatusArr= "'+todayStatusArr+'" style="cursor:pointer;">'+value.totalCount+'</td>';
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
					str+='<td style="background:#f8f8f8;cursor:pointer;" class="todayappointmentStatusCls text-center" attr_todayStatusArr= "'+clickAray+'" >'+value.subList[i].totalCount+'</td>';
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
				str+='<td class="todayappointmentStatusCls text-center" attr_todayStatusArr= "'+todayStatusArr+'" style="cursor:pointer">'+value.totalCount+'</td>';
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
	function buildAppointmentStatusList(result){
			for(var i in result){
				$("#selectStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$(".dropkickClass").dropkick();
			var select = new Dropkick("#selectStatusId");
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
		
		getSearchDetails(true);
	}
	$(document).on("click",".MngeAppntmntCls",function(){
		$(".commonDivCls").hide();
		$("#selectStsForLabelId").val(1);
		getLabelDtls();
		getAppointmentStatusOverview();
	});
	$('#appointmentUserSelectBoxId').change(function(){
		getLabelDtls();
		getAppointmentStatusOverview();
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
							str+='<button class="btn btn-success btn-xs deleteAppointments" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" title="Delete Appointments Of '+result[i].labelName+'" style="margin-right: 5px;" id="delApptsScrollBarId">Del Appts</button>';
						}else{
							str+='<button class="btn btn-success btn-xs deleteAppointments" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" disabled style="margin-right: 5px;">Del Appts</button>';
						}
						
						
						str+='<i class="glyphicon glyphicon-remove lblDltCls" title="Delete Label '+result[i].labelName+'" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" style="color:red;cursor:pointer;"></i>';
						str+='<img src="images/search.gif" style="display:none;" id="ajaxImgForViewId'+i+'"></img>';
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
				  str+='</table>';
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
   
   $(document).on("click","#searchAppointmentdetailsId",function(){
	  getAppointmentsBySearchCriteria($(this).attr("attr_label_name"));
  });
  function clearAppointmentsSearchFields(){
	  $("#appDesigErrId,#appPrrtyErrTypId,#appStatusErrId,#appDistErrId,#appConstErrId").html('');  
  }
  </script>
  <script>
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
			dateType:radioValue,
			apptUserId:$("#appointmentUserSelectBoxId").val()
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
			
		 for(var i in result){
			  str+='<div class="panel panel-default manageAppViewPanelClass m_top10">';
				str+='<div class="panel-heading pad_5">';
				        if(result[i].labeled){
							str+='<div class="row">';
								str+='<div class="col-md-2">';
									str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
								str+='</div>';
								str+='<div class="col-md-2">';
									if(result[i].priority !=null && result[i].priority.length>0){
										str+='<span>Priority : '+result[i].priority+'</span>';
									}else{
										str+='<span>Priority : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-3">';
									if(result[i].dateString !=null && result[i].dateString.length>0){
										str+='<span>Request Created Date : '+result[i].dateString.split(" ")[0]+'</span>';
									}else{
										str+='<span>Request Created Date : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-2 col-md-offset-3">';
									str+='<span>Current Status : '+result[i].status+'</span>';
									str+='<span data-toggle="tooltip" data-placement="top" title="Check this to assign a label" class="requestedCheckbox"><input class="appointmentcheckBoxClass" type="checkbox" value="'+result[i].appointmentId+'" checked></span>';
								str+='</div>';
							str+='</div>';
						}else{
							str+='<div class="row">';
								str+='<div class="col-md-2">';
									str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
								str+='</div>';
								str+='<div class="col-md-2">';
									if(result[i].priority !=null && result[i].priority.length>0){
										str+='<span>Priority : '+result[i].priority+'</span>';
									}else{
										str+='<span>Priority : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-3">';
									if(result[i].dateString !=null && result[i].dateString.length>0){
										str+='<span>Request Created Date : '+result[i].dateString.split(" ")[0]+'</span>';
									}else{
										str+='<span>Request Created Date : - </span>';
									}
								str+='</div>';
								str+='<div class="col-md-2 col-md-offset-3">';
									str+='<span>Current Status : '+result[i].status+'</span>';
									str+='<span data-toggle="tooltip" data-placement="top" title="Check this to assign a label"  class="requestedCheckbox"><input class="appointmentcheckBoxClass" type="checkbox" value="'+result[i].appointmentId+'" ></span>';
								str+='</div>';
							str+='</div>';
						}
						
						if(result[i].subject !=null && result[i].subject.length>0){
							str+='<p>Purpose : '+result[i].subject+'</p>';
						}else{
							str+='<p>Purpose : - </p>';
						}
					
				str+='</div>';
				  str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-4">';
										str+='<div class="media">';
											str+='<div class="media-left">';
											str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
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
											str+='</div>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-md-8">';
									
									//history modal start
									 if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="Appointments History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 			
									//history modal end
									
										str+='<table class="table table-bordered table-condensed m_top10">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
											str+='<th>APPOINTMENT ID</th>';
											str+='<th>CREATED DATE</th>';
										    str+='<th>APPOINTMENT PREFERABLE DATES</th>';
											str+='<th>STATUS</th>';
												
											str+='</thead>';
											str+='<tbody>';
											
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													
													str+='<td>'+result[i].subList[j].subList[l].aptUniqueCode+'</td>';
													
													
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
													str+='<th>APPOINTMENT ID</th>';
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
						
						str+='<div class=" m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES : </b></span>';
							
							if(result[i].apptpreferableDates != null){							
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].dateType != null && result[i].dateType.trim()!="" && result[i].maxDate != null && result[i].minDate != null){
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+') </span>';
							}else{
								str+='<span> - </span>';
							}							
						str+='</div>';
						
				  str+='</div>';
				str+='</div>';
		 }
		  str+='<button class="btn btn-success" id="updateLabelId" >Assign To Label</button>';
		  str+=' <span id="statusMsgAppntReqt"></span>';
		 str+='<div ><center ><img style="display: none;margin-top: -30px;" src="images/icons/loading.gif" id="updateMemberAjax"></center></div>';
		 str+='</div>';
		
		 $("#appointmentRequestedMembersId").html(str);
		 $('[data-toggle="tooltip"]').tooltip();
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
																str+='<img class="media-object thumbnail" src="'+result[i].basicInfoList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
															str+='</div>';
															str+='<div class="media-body">';
																str+='<p>'+result[i].basicInfoList[j].name+'';
																if(result[i].basicInfoList[j].membershipNum != null)
																	str+='- Cadre</p>';
																else
																	str+='</p>';
																if(result[i].basicInfoList[j].mobileNo !=null && result[i].basicInfoList[j].mobileNo.length>0){
																	str+='<p>Contact Number: '+result[i].basicInfoList[j].mobileNo+'</p>';
																}else{
																	str+='<p>Contact Number: - </p>';
																}
																
																str+='<p>Designation: '+result[i].basicInfoList[j].designation+'</p>';
															str+='</div>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</li>';
										}
									}
									
								str+='</ul>';
								str+='<p class="m_top10">Appt Created By: '+result[i].userName+' &nbsp;&nbsp;&nbsp;&nbsp; <img src="dist/Appointment/img/message.png" class="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top"></p>';
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
		var dynamicViewAjaxId=$(this).parent().find("img").attr("id");
		
		$("#"+dynamicViewAjaxId).show();
		$(".commonDivCls").hide();
		
		var labelName = $(this).attr("attr_label_name");
		
		var jsObj={
			labelId : $(this).attr("attr_label_id"),
			callFrom : "",
			apptuserId:$("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#"+dynamicViewAjaxId).hide();
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
			  buildViewResult(result,labelName,jsObj.labelId,jsObj);
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
			callFrom : "print",
			labelName:labelName,
			apptuserId:$("#appointmentUserSelectBoxId").val()
		}
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
				buildViewResult(result,labelName,labelId,jsObj);
				}else{
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	}

	function buildViewResult(result,labelName,labelId,jsObj){
		var i = 0;
		var str='';
			str+='<div class="col-md-12">';
			str+='<div class="block">';
			str+='<table id="viewAllMembersId">';
			str+='<thead><th></th></thead>';
			str+='<tbody>';
			if(result[0].pdfPath != null && jsObj.callFrom == "print")
			str+='<a  id="pdffBtn" class="text-success" style="margin-bottom:10px;float:right;color:#fff;" value="Download" href="appointmentPdf/'+result[0].pdfPath+'" download  target_blank>Download</a>';
			str+='<input type="button" class="text-success" style="margin-bottom:10px;float:right;color:#fff;" value="Print" onClick="printMembersForView(\''+labelId+'\',\''+labelName+'\');"></input>';
			str+='<h4 class="text-success" style="margin-bottom:10px;">'+labelName +' MEMBERS</h4>';
			
			for(var i in result){
				str+='<tr><td>';
				str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading pad_5">';
				    str+='<div class="row">';
						str+='<div class="col-md-2">';
							str+='<span>Appointment ID: '+result[i].aptUniqueCode+'</span>';
						str+='</div>';
						str+='<div class="col-md-2">';
							if(result[i].priority !=null && result[i].priority.length>0){
								str+='<p>Priority : '+result[i].priority+'</p>';
							}else{
								str+='<p>Priority : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-3">';
							if(result[i].dateString !=null && result[i].dateString.length>0){
								str+='<p>Request Created Date : '+result[i].dateString.split(" ")[0]+'</p>';
							}else{
								str+='<p>Request Date : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-2 col-md-offset-3">';
							//str+='<input type="button" class="text-danger" value="Print" onClick="printMembersForView(\''+labelId+'\',\''+labelName+'\');"></input>';
							str+='<span class="requestedCheckboxPanel text-danger">'+result[i].status+'</span>';
						str+='</div>';
					str+='</div>';
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}else{
						str+='<p>Purpose : - </p>';
					}	
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									
									str+='<div class="col-md-4">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
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
											str+='</div>';
										str+='</div>';
										
											
									str+='</div>';
									str+='<div class="col-md-8">';									
									//history modal start
									 if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="Appointments History"  data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time m_top20" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 
									
									str+='<div class=" displayrow " style="margin-top: -25px;">';
										
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<ul class="row">';
											str+='<li  style=" width: 116px;padding: 8px !important;" class="col-xs-2 alignmentprefrabledates">APPOINTMENT ID</li>';
											str+='<li	 style="width: 115px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">CREATED DATE</li>';
											str+='<li  style="width:315px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">APPOINTMENT PREFERABLE DATES</li>';
											str+='<li  style="padding: 8px !important;width: 110px;"class="col-xs-1 alignmentprefrabledates">STATUS</li>';
											str+='</ul>';
											for(var l in result[i].subList[j].subList){
												
												str+='<ul class="row datespadding" style="margin-top: -10px; margin-bottom: 0px;">';
												str+='<li style="width: 116px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">'+result[i].subList[j].subList[l].aptUniqueCode+'</li>';
												str+='<li style="width: 115px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">'+result[i].subList[j].subList[l].dateString+'</li>';
												if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId >1){
														str+='<li  style="width: 315px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">'+result[i].subList[j].subList[l].dateType+' : <span>'+result[i].subList[j].subList[l].minDate+' - '+result[i].subList[j].subList[l].maxDate+'</span></li>';
													}else if(result[i].subList[j].subList[l].dateTypeId !=null && result[i].subList[j].subList[l].dateTypeId ==1){
														str+='<li  style="width:315px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">'+result[i].subList[j].subList[l].apptpreferableDates+'</li>';
													}else{
														str+='<li style="width:315px;padding: 8px !important;"  class="col-xs-3 alignmentprefrabledates">-</li>';
													}
													
												if(result[i].subList[j].subList[l].status !=null){
														str+='<li  style="padding: 8px ! important; width: 110px;" class="col-xs-1 alignmentprefrabledates">'+result[i].subList[j].subList[l].status+'</li>';
													}else{
														str+='<li  style="padding: 8px ! important; width: 110px;" class="col-xs-1 alignmentprefrabledates">-</li>';
													}		
												
												str+='</ul>';
											}
										}else{
														str+='<ul class="row">';
														str+='<li  style=" width: 116px;padding: 8px !important;" class="col-xs-2 alignmentprefrabledates">APPOINTMENT ID</li>';
														str+='<li	 style="width: 119px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">CREATED DATE</li>';
														str+='<li  style="width:238px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">APPOINTMENT PREFERABLE DATES</li>';
														str+='<li  style="padding: 8px !important;width: 117px;"class="col-xs-1 alignmentprefrabledates">STATUS</li>';
														str+='</ul>';
												
													str+='<ul class="row">';
														str+='<li  style=" width: 116px;padding: 8px !important;" class="col-xs-2 alignmentprefrabledates">No Data Available</li>';
														str+='<li	 style="width: 119px;padding: 8px !important;" class="col-xs-1 alignmentprefrabledates">No Data Available</li>';
														str+='<li  style="width:238px;padding: 8px !important;" class="col-xs-3 alignmentprefrabledates">No Data Available</li>';
														str+='<li  style="padding: 8px !important;width: 117px;"class="col-xs-1 alignmentprefrabledates">No Data Available</li>';
														str+='</ul>';
											}
										str+='</div>';
	
									//history modal end
										
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						str+='<div class=" m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES :</b></span>';
							if(result[i].apptpreferableDates != null && result[i].dateTypeId == 1){
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].apptpreferableDates == null && result[i].dateTypeId > 1){ 
								str+='<span>'+result[i].dateType+' ( '+ result[i].minDate +' to '+result[i].maxDate+')</span>';
							}else{
								str+='<span> - </span>';
							}
							
						str+='</div>';
					
						
				  str+='</div>';
				str+='</div>';
				str+='</td></tr>';
					
			}
			str+='</div>';
			str+='</div>';
			str+='</tbody></table>';
			
		$(".appointmentsViewDivCls").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		$("#viewAllMembersId").dataTable();
		
	}
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
							str+='<textarea  placeholder="Please Enter Comment..." cols="35" rows="2" class="commentTextCls'+result[i].appointmentId+'" id="commentTxtId'+result[i].appointmentId+'" style="display:none;padding:8px;"></textarea>';
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
	
	$(document).on("click",".cnfrmaptsCls",function(){
		
	   $("#appointmentDateSlotHeadingId").daterangepicker({singleDatePicker:true});
       $('#appointmentDateSlotHeadingId').val(moment().format('MM/DD/YYYY'));
	   $("#appointmentDateSlotId").daterangepicker({singleDatePicker:true,minDate:new Date()});
       $('#appointmentDateSlotId').val(moment().format('MM/DD/YYYY'));
	   
		$(".changeClass").removeClass("col-md-8");
		$(".changeClass").addClass("col-md-12");
		getAppointmentStatusOverview();	
		//Set Button disabling
		$('#setTimeSlotBtnId').attr('disabled',true);

		//clearing the Div Area and setting default time format		
		$("#confirmAppointmentBlockDropId").empty();
		$("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
		   $("#fromTimeId").val("00:00 AM");
		   $("#toTimeId").val("00:00 PM");
		   $("#commentTxt").val("");
	
    	getTimeSlotsForADayByAppytUserId();
		getAllScheduledApptsByDate();
		
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
		$("#confirmAppointmentsAjaxImg").show();
		var jsObj={
			labelId : $("#appointmentLabelToGetSlotsId").val(),
			callFrom : "timeSlot",
			apptuserId:$("#appointmentUserSelectBoxId").val()
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
			  $("#confirmAppointmentsAjaxImg").hide();
			  $("#confirmAppointmentsDivId").html("<div class='col-md-4'><div class='block'><div><p style='color:green;font-size:20px'>No Data available.</p></div></div></div>");	
			}
		});
	}
	
	function buildLabelResult(result,labelName){
		
		setcolorsForStatus();
		var i = 0;
		var str='';
		
			str+='<div class="col-md-4 block  m_top30" >';
			str+='<div >';
			str+='<table id="confirmAppointmentsdt"  >';
			str+='<thead>';
			str+='<th></th>';
			str+='</thead>';
			str+='<tbody id="dragId" >';
		for(var i in result){
			str+='<tr class="newClass manageAppViewPanelClass" >';
			str+='<td>';
				str+='<div class="panel panel-default appointmentCls" attr_appointment_id='+result[i].appointmentId+'>';//manageAppViewPanelClass
				str+='<div class="panel-heading">';
				    str+='<div class="row">';
						str+='<div class="col-md-12">';
						var color = getColorCodeByStatus(result[i].status);
						str+='<span class="requestedCheckboxPanel text-danger" style="margin-right:25px;color:'+color+'">'+result[i].status+'</span>';
						str+='<span class="requestedCheckboxPanel hidelabel"><i class="glyphicon glyphicon-remove"></i></span>';
						str+='</div>';
					str+='</div>';
					str+='<b>ID: '+result[i].aptUniqueCode+'</b>&nbsp;&nbsp;&nbsp;';
					if(result[i].priority !=null && result[i].priority.length>0){
						str+='<p>Priority : '+result[i].priority+'</p>';
					}					
					if(result[i].dateString !=null && result[i].dateString.length>0){
						str+='<p>Created Date : '+result[i].dateString.split(" ")[0]+'</p>';
					}
					
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}
					
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
												if(result[i].subList[j].imageUrl != null && result[i].subList[j].imageUrl.length > 0)
													str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
												else
													str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" onerror="setDefaultImage(this);" alt="Candidate Image">';
											//	str+='<span class="colorStatus green"></span>';
											str+='</div>';
											str+='<div class="media-body">';
												str+='<p>'+result[i].subList[j].name+'</p>';
												
												if(result[i].subList[j].mobileNo !=null && result[i].subList[j].mobileNo.length>0){
													str+='<p> <i class="fa fa-mobile" style="font-size:15px"></i> &nbsp;'+result[i].subList[j].mobileNo+'</p>';
												}
												
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
												if(result[i].subList[j].candidateTypeId !=null && 
													result[i].subList[j].candidateTypeId >0 && result[i].subList[j].candidateTypeId != 1)
														if(result[i].subList[j].constituency!=null && result[i].subList[j].constituency.trim().length>0){
															str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
														}				
											str+='</div>';
										str+='</div>';
										//history modal start
										if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="click here to view '+result[i].subList[j].name+' history"  data-toggle="tooltip" data-placement="top"class="historyshowmodalbtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 
									str+='</div>';
								str+='</div>';
							str+='</li>';
						}
						str+='<div class="m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES :</b></span>';
							
							if(result[i].apptpreferableDates != null){							
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].dateType != null && result[i].dateType.trim() != "" && result[i].minDate != null && result[i].maxDate != null){
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+')</span>';
							}else{
								str+='<span> - </span>';
							}							
							str+='<img src="dist/Appointment/img/reqHistoryicon+.png" class="pull-right statusTrackingModalbtn" attr-id='+result[i].appointmentId+' attr-aptName='+result[i].aptUniqueCode+' alt="ViewReqHistory" style="height:16px;cursor:pointer;margin-right:5px;" title="Appointment Requested History" data-toggle="tooltip" data-placement="top"/>';
							
						str+='</div>';
						 
					str+='</ul>';	
				  str+='</div>';
				str+='</div>';
				str+='</td>';
				str+='</tr>';
			}
				
			str+='</tbody>';
			str+='</table>'
			str+='</div>';
			str+='</div>';
		$("#confirmAppointmentsAjaxImg").hide();
		$("#confirmAppointmentsDivId").html(str);
		$('#confirmAppointmentsdt').DataTable({
			responsive: true,
			"info":     false,
			"bSearching": true,
			 "scrollY":   "625px",	
			"bPaginate": false,
			"bLengthChange": false,
			"bAutoWidth": false,
			
		});
		$(".custom-scroll-ins").mCustomScrollbar();
		$(".mCSB_dragger_bar").css("background-color","#000");
		$(document).on("click",".historyshowmodalbtn",function(){
			$("#appCandidateNameId").html('');
			$(".historyShowModal").modal("show");
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
		Sortable.create(dragId,{
			  filter: '.js-remove',
			  onFilter: function (evt) {
				evt.item.parentNode.removeChild(evt.item);
			  },
			  setData: function (dataTransfer, dragEl) {
				dataTransfer.setData('Text', dragEl.textContent);
			  },
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
		setTimeout(function(){ $(".updateChangeClass").addClass("col-md-6"); }, 500);
			
		//var appointmentId = $("#appointmentLabelToGetSlotsId").val();		
		var appointmentId = $("#confirmAppointmentBlockDropId div").attr("attr_appointment_id");
		var date = $("#appointmentDateSlotId").val();
		var fromTime = $("#fromTimeId").val().trim();
		var toTime = $("#toTimeId").val().trim();
		
		//Saving
		setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,"save",0,$("#commentTxt").val().trim());
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
		str1+='<th colspan="4">6a</th>';
		str1+='<th colspan="4">7</th>';
		str1+='<th colspan="4">8</th>';
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
		str1+='<th colspan="4">9</th>';
		str1+='<th colspan="4">10</th>';
		str1+='</thead>';
		if(result.listOfTimePairPerDate!=null && result.listOfTimePairPerDate.length>0){
			for(var i in result.listOfTimePairPerDate){
			str1+='<tr id="'+i+'"class="borderSlot">';
			for(var unique=0;unique<=63;unique++){
				str1+='<td id="'+i+''+unique+'"></td>';
			}
			str1+='</tr>';
			}
			$("#tablePluginId").html(str1);
			for(var i in result.listOfTimePairPerDate){
				for(var j in result.listOfTimePairPerDate[i]){
					var start=((result.listOfTimePairPerDate[i])[j])[0];
					var end = ((result.listOfTimePairPerDate[i])[j])[1];
					var startIdForHour=start.substr(11,2);
					var startIdForMin=start.substr(14,2);
					var startId=(startIdForHour-6)*4;
				
					startId= startId+(startIdForMin/15);
					var strtDividedVleForMnte=startIdForMin%15;
					   if(strtDividedVleForMnte>=8){
						   startId=startId+1;
					   }
					var endIdForHour=end.substr(11,2);
					var endIdForMin=end.substr(14,2);
					var endId=(endIdForHour-6)*4;
					
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
			for(var unique=0;unique<=63;unique++){
				str1+='<td></td>';
			}
			str1+='</tr>';
			$("#tablePluginId").html(str1);
		}
		
	}
	function clearAllValidationCls(){
		//$(".errorAptCls").html('');
		//$(".errorSpdCls").html('');
		//$(".errorArCls").html('');
		$(".errorCandidateMainDivCls").html('');
		$(".cloneErrCandidateNameCls").html('');
		$(".cloneErrCandidateDesgCls").html('');
		//$(".cloneErrCandidateMobileCls").html('');
		//$(".cloneErrCandidateLcScopeCls").html('');
		//$(".cloneErrCandidateDistrictCls").html('');
		//$(".cloneErrCandidateConstCls").html('');
		//$(".cloneErrCandidateMandalCls").html('');
		//$(".cloneErrCandidateVillageCls").html('');
		//$(".cloneErrCandidateTypeCls").html('');
		$(".cloneErrCandidateMemShipCls").html('');
	}
	
	//Required validation For Appointment Creation
	function validateSavingDetails(){
	
		var isErrAvailable=false;
		//var prType = $("#createAppTypeListId").val();
		var selectDate = $(".multiDateCls").val();
		var validateReason=$("#appointmentReasonId").val();
		
		<!-- Priority type,Prefereble Dates and Reason Validation -->
		/* if(prType == null || prType ==0 || prType == undefined){
			$(".errorAptCls").html("Please Select AppointmentType");
			isErrAvailable=true;
		}	 */	
		/* if(selectDate == null || selectDate.length<=0 || selectDate == undefined){
			if($("#selectManualDateId").is(":checked")){
				$(".errorSpdCls").html("Please Select Appointment Date(s)");
				isErrAvailable=true;				
			}			
		} */
		/* if(validateReason ==null || validateReason.length<=0 || validateReason == undefined || validateReason==""){
			$(".errorArCls").html("Please Specify The Reason");	
			isErrAvailable=true;				
		} */
		
		
		if (isEmpty($('#moreCandidatesDivId'))) {
			$(".errorCandidateMainDivCls").html("Please Add Candidate");	
			isErrAvailable=true;				
		}else{
			$(".validateCls").each(function(i){
				i = $(this).attr("attr_count");
				var nameValue=$("#candidateNameId"+i).val();
				 if(nameValue ==null || nameValue.length<=0 || nameValue == undefined || typeof nameValue === "undefined" || nameValue.trim() == ""){
					  isErrAvailable=true;
					  $("#cloneErrCandidateNameId"+i+"").html("Please enter Name");
				 }
				 var canTypeValue=$("#candidateTypeSelId"+i).val();
				 if(canTypeValue>0){
					 var desgValue=$("#designationSelId"+i).val();
					  if(desgValue ==null || desgValue ==0 || desgValue == undefined || desgValue ==""){
					  $("#cloneErrCandidateDesgId"+i).html("Please Select Designation");
					  isErrAvailable=true;	
				      } 
				 }
				/*  var desgValue=$("#designationSelId"+i).val();
				 if(desgValue ==null || desgValue ==0 || desgValue == undefined || desgValue ==""){
					  $("#cloneErrCandidateDesgId"+i).html("Please Select Designation");
					  isErrAvailable=true;	
				 } */
				 
				 <!-- MobileNo Validation-->
				 
				 /*var mobileValue=$("#mobileNoId"+i).val();
				  if(mobileValue ==null || mobileValue.length ==0 || mobileValue == undefined || mobileValue ==""){
					  $("#cloneErrCandidateMobileId"+i).html("Please enter Mobile No");
					  isErrAvailable=true;
				 } 	
				else if(mobileValue.length != 10 || isNaN(mobileValue)){		
					$("#cloneErrCandidateMobileId"+i).html("Please enter Valid Mobile Number");
					isErrAvailable=true;
				}*/
				
				 /* var canTypeValue=$("#candidateTypeSelId"+i).val();
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
				} */
				<!-- Location Scope validation -->	
				
			 /* var locationScopeValue=$("#locationScopeSelId"+i).val();
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
				 } */
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
			
			str+='<p>Priority : '+result[i].priority+'</p>';
			str+='<p>Purpose : '+result[i].subject+'</p>';
			
			str+='</div>';
			str+='<div class="panel-body pad_5">';
			str+='<ul class="confirmSearchUl" style="list-style: none;padding:0px">';
			for(var j in result[i].subList){
				str+='<li>';
					str+='<div class="row">';
							str+='<div class="col-md-7">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
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
			str+='<p class="m_top10">';
			str+="<i>Appt Created By: "+result[i].userName+"</i>";
			str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" ></p>';
			str+='</div>';
			str+='</div>';
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';
		$("#appointmentMembersId").html(str);
        $(".appntmntCnddteUpdtDtRngPckrCls").daterangepicker({singleDatePicker:true});		
		$('.appntmntCnddteUpdtDtRngPckrCls').val(moment().format('MM/DD/YYYY'));		
		$(".appntmntCnddteUpdtFrmTmCls").datetimepicker({format:"LT"});
	 	$(".appntmntCnddteUpdtTotmCls").datetimepicker({format:"LT"});
	    $('#appntmntMmbrsTblId').dataTable({
			"iDisplayLength": 2,
		});
	}
	
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
	
	 $(document).on("click","#setDfltTmFrmtId",function(){
	    //setting default time format
	    var timeArr=[];
		timeArr = $(this).closest("tr").find("#timeSpnCls").text().split("to");
		var date = $(this).closest("tr").find("#dateSpnCls").text();
		var dateArr=date.split("-");
		
		$(this).closest("tr").find(".appntmntCnddteUpdtDtRngPckrCls").val(dateArr[1]+"/"+dateArr[2]+"/"+dateArr[0]);
		$(this).closest("tr").find(".appntmntCnddteUpdtFrmTmCls").val(timeArr[0].split(":")[0].trim()+":"+timeArr[0].split(":")[1].trim());
		$(this).closest("tr").find(".appntmntCnddteUpdtTotmCls").val(timeArr[1].split(":")[0].trim()+":"+timeArr[1].split(":")[1].trim());
	 });
	$(".dateRadioCls").click(function(){		
		if($("#selectManualDateId").is(":checked")){
			$("#multiDate").multiDatesPicker({numberOfMonths: [1,2],minDate:0})
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
			callFrom : "",
			apptuserId:$("#appointmentUserSelectBoxId").val()
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
				str+='<div class="panel-heading pad_5">';
				    str+='<div class="row">';
						str+='<div class="col-md-2">';
							str+='<span>Appointment Id: '+result[i].aptUniqueCode+'</span>';
						str+='</div>';
						str+='<div class="col-md-2">';
							if(result[i].priority !=null && result[i].priority.length>0){
								str+='<p>Priority : '+result[i].priority+'</p>';
							}else{
								str+='<p>Priority : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-3">';
							if(result[i].dateString !=null && result[i].dateString.length>0){
								str+='<p>Request Created Date : '+result[i].dateString.split(" ")[0]+'</p>';
							}else{
								str+='<p>Request Created Date : - </p>';
							}
						str+='</div>';
						str+='<div class="col-md-3 pull-right">';
							str+='<span class="requestedCheckbox" data-toggle="tooltip" data-placement="top" title="Check this to delete appointments"><input type="checkbox" value="'+result[i].appointmentId+'" class="deleteAppointmentChckCls"></input></span>';
							if(result[i].status != null){
								str+='<span>Current Status : '+result[i].status+'</span>';
							}else{
								str+='<span>Current Status : - </span>';
							}	
						str+='</div>';
					str+='</div>';
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Purpose : '+result[i].subject+'</p>';
					}else{
						str+='<p>Purpose : - </p>';
					}
									
					
				str+='</div>';
				str+='<div class="panel-body pad_5">';
					for(var j in result[i].subList){
						str+='<ul class="viewAppointmentRequestedMembers">';
							str+='<li>';
								str+='<div class="row">';
									str+='<div class="col-md-6">';
									str+='<div class="col-md-6">';
										str+='<div class="media">';
											str+='<div class="media-left">';
												str+='<img class="media-object thumbnail" src="'+result[i].subList[j].imageUrl+'" onerror="setDefaultImage(this);" alt="Candidate Image">';
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
											str+='</div>';
										str+='</div>';
										
									str+='</div>';
									str+='<div class="col-md-6">';
									
									//history modal start
									 if(result[i].subList[j].candidateId != null && result[i].subList[j].candidateId > 0){
											str+='<a  title="Click here to View '+result[i].subList[j].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].subList[j].candidateId+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a>&nbsp;&nbsp;';
									} 			
									//history modal end
									
										str+='<table class="table table-bordered m_top10 table-condensed">';
										if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
											str+='<thead>';
												str+='<th>APPOINTMENT ID</th>';
												str+='<th>CREATED DATE</th>';
												str+='<th>APPOINTMENT PREFERABLE DATES</th>';
												str+='<th>STATUS</th>';
											str+='</thead>';
											str+='<tbody>';
												for(var l in result[i].subList[j].subList){
													str+='<tr>';
													
													str+='<td>'+result[i].subList[j].subList[l].aptUniqueCode+'</td>';
													
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
													str+='<th>APPOINTMENT ID</th>';
													str+='<th>CREATED DATE</th>';
													str+='<th>APPOINTMENT PREFERABLE DATES</th>';
													str+='<th>STATUS</th>';
													str+='</thead>';
												
													str+='<tr>';
													str+='<td  colspan="3"><center>No Data Available</center></td>';
													str+='</tr>';
											}
											
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
						str+='</ul>';
						}
						
						
						str+='<div class=" m_top10">';
							str+='<span style="font-size: 16px;"><b>REQUESTED DATES : </b></span>';
							
							if(result[i].apptpreferableDates != null){							
								str+='<span>'+result[i].apptpreferableDates+'</span>';
							}else if(result[i].apptpreferableDates == null && result[i].maxDate != null && result[i].minDate != null && result[i].dateType != null && result[i].dateType.trim() != ""){
								str+='<span>'+result[i].dateType+' ('+result[i].minDate+' to '+result[i].maxDate+')</span>';
							}else{
								str+='<span> - </span>';
							}						
						str+='</div>';
							
				  str+='</div>';
				str+='</div>';
			}
			str+='<button class="btn btn-success" attr_label_id="'+labelId+'" id="deleteMultipleAppointmentsId">Delete Appointments</button>';
			str+='<div id="deleteAppointmentErrDivId"></div>';
			str+='</div>';
			str+='</div>';
		
		$(".appointmentsDeleteDivCls").html(str)  
		$('[data-toggle="tooltip"]').tooltip()
	}
	
    function showHideBySearchType()
	{
		
		//setToDefaultAdvancedSearch();
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			
			if(selectVal == 2)
			{
				$(".advancePRCls").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$(".advanceprcls").show();
				$("#cadreCommitteeDiv_chosen").hide();
				$(".stateShowCls").show();
				$(".levelShowCls").show();
				setToDefaultAdvancedSearch();
				$("#advanceDesignationId").css("display","none");
				getPublicRepresentsDetails();
				//disableByLevel();
				
			}
			else if(selectVal == 3)
			{
				$(".advancePRCls").hide();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").show();
				$(".locationsFilterCls").show();
				$(".advanceprcls").hide();
				$(".stateShowCls").show();
				$(".levelShowCls").show();
				$(".advanceprclsDiv").hide();
				$("#cadreCommitteeDiv_chosen").show();
				$("#cadreCommitteeDiv").css("display","none");
				$(".chosen-choices").css("display","block");
				getCommitteeRoles();
				$(".referRolesCheck").removeAttr("checked");
				setToDefaultAdvancedSearch();
				//disableByLevel();
			}
			else if(selectVal == 1)
			{
				$(".stateShowCls").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").show();
				$(".levelShowCls").show();
				$(".advancePRCls").hide();
				$("#cadreCommitteeDiv_chosen").hide();
				$("#referCommitteeDiv").hide();
				clearNameSearchTypeFields();
				
			}
			else
			{
				$(".levelShowCls").hide();
				$(".stateShowCls").hide();
				$(".advanceprcls").show();
				$(".advanceNameCls").show();
				$(".advancePRCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$("#advanceSearchValueId").val("");
				$(".advanceprclsDiv").hide();
			}
				disableByLevel();
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
	   }
	 
	  function  clearNameSearchTypeFields(){
		  clearLevelField();
		  clearStateField();
	  }
	   function clearLevelField(){
		  $("#levelId").val(0);	
		  $("#levelId").dropkick('reset'); 
	   }
	   function clearStateField(){
		 $("#stateId").val(0);	
		 $("#stateId").dropkick('reset');
	   }
	   
	function setToDefaultAdvancedSearch()
	{	
			$("#advanceDesignationId").val(0);
			$("#advanceDesignationId").dropkick('reset');
			
			$("#levelId").val(0);	
			$("#levelId").dropkick('reset');
			
			$("#stateId").val(0);	
			$("#stateId").dropkick('reset');
			
			$("#referCommitteeId").val(0);	
			$("#referCommitteeId").dropkick('reset');
			
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
					getLabelDtls();
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
		stateId = $("#stateId").val();
		 if(advanceSearchType==0){
			 errorStr='Please Select Search Type';
			 $("#errorDivId").html(errorStr);
		 }
		  var aptUserId = 0;
		  aptUserId = $("#appointmentUserSelectBoxId").val();
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
			 /*if(searchValue == 0 || searchValue.length ==0)
			 {
				 errorStr='Please Select Designation';
				 $("#errorDivId").html(errorStr);
				 return;
			 }*/
		}
		else if(advanceSearchType == 3)
		{
			 searchType = "CadreCommittee";
				$("#cadreCommitteeDiv option:selected").each(function ()
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
		 
		 var panchayatName =  $("#referpanchayatId selected:option").text();
		 if($("#referpanchayatId").val() > 0){
			if(panchayatName.indexOf('WARD') == -1)
		panchayatId = "1"+$("#referpanchayatId").val();
		
		}
		
		 levelId  = $("#levelId").val();
		if(levelId == 10)
			level = "state";
		if(levelId == 11)
			level = "district";
		if(levelId == 5)
			level = "mandal";
		if(levelId == 6)
			level = "village";
		if(levelId == 1)
			level = "constituency";
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
		
	
	
		if(errorStr.length >0)
       {
	  $('#errorDivId').html(errorStr);
	  return ;
       } 
	   
	//Party Commitee Members	
	if(advanceSearchType !=null && advanceSearchType == 3){
		$("#cadreCommitteeDiv option:selected").each(function ()
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
		var desgnaValue = $("#advanceDesignationId").val();
		if(desgnaValue > 0)
		statusArr.push(desgnaValue);
		committeeId = "0";	
	}
		if(errorStr.length>0){
			$("#errorDivId").html(errorStr);
			return;
		}
		$("#searchMemberAjax").css("display","block");
	
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
			stateId:stateId,
			levelStr:levelStr,
			aptUserId:aptUserId
		}
		$("#apptmemberDetailsDiv").html('');
		  	$.ajax({
				type : 'POST',
				url : 'getAppntmntAdvancedSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#searchMemberAjax").css("display","none");
				$("#apptmemberDetailsDiv").html("");
				if(result !=null && result.length>0){
				buildapptmemberDetails(result);
				
				}else{
					$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
				}
		  }); 
	}
		$(document).on("click",".btnClassChange",function(){
			var selectedValue = $(this).attr("attr_val");
			$("#apptmemberDetailsDiv").html("");
			searchTypeRadioCls(selectedValue);
			
		})
	function searchTypeRadioCls(selected)
	{
		
		$(".clearCls").val("");
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
		$(".btnClassChange").each(function(){
			if($(this).hasClass("btnActive"))
			{
			
				var selected = $(this).attr("attr_val");
				
				if(selected == 1)
				{
					getDetailsBySrch();
				}
				else
				{
					getAdvancedSearchDetails();
				}
			}
		})
		
		
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
		getSearchDetails(false);
	});
	$( "#selectStsForLabelId" ).change(function() {
		getLabelDtls();
		
	});
	
	function buildDesgnationBlockForOtherCandidate(candidateTypeId,cnt){
    	var  othrCnddtDsgntnBlckId=$("#othrCnddtDsgntnBlckId"+cnt).attr("id");
			if(candidateTypeId==4){
				$("#"+othrCnddtDsgntnBlckId).show();
			}else{
				$("#"+othrCnddtDsgntnBlckId).hide();
			}
	}
	$(document).on("click",".cloneDesignationSpanCls",function(){
	    $(".designationCls").val(" ");
		$(".designationErrCls").html(" ");
		$(".statusCls").html(" ");
		var cloneId=$(this).attr("attr_coneId");
		$(".saveDesignationCls").attr("id",cloneId);
	    $("#blockForOtherCandidateModalId").modal("show");
 	});
	function getDesignationsByTypeForChange(cnt,status)
	{
	  var typeId = $("#candidateTypeSelId"+cnt).val();
	  if(status.trim()!="other"){
		  buildDesgnationBlockForOtherCandidate(typeId,cnt);
	   }
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
				buildDesignationForCreateApp1(result,"designationSelId"+cnt,status);
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
				buildDesignationForCreateApp1(result,selectId," ");
			}
			
		});
	}
	
	function buildDesignationForCreateApp1(result,selectId,status){
		 $("#"+selectId+"  option").remove();
		 $('#'+selectId).append('<option value="0">Select Designation</option>');
		for(var i in result){
			if(popDesignation == result[i].name)
			$('#'+selectId).append('<option value='+result[i].id+' typeId='+result[i].orderId+' selected="true">'+result[i].name+'</option>');
		else
			$('#'+selectId).append('<option value='+result[i].id+' typeId='+result[i].orderId+'>'+result[i].name+'</option>');
		 }
		 var listIndex=result.length;
		 if(status.trim()=="other"){
		  var select = new Dropkick('#'+selectId);
		  select.refresh();
		  select.select(listIndex);
		 }else{
		  var select = new Dropkick('#'+selectId);
		   select.refresh();
		 }
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
		
		/*$(".errorDivFrTmSltUpdtId").html(' ');
		if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("Please Specify the From Time");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("Please Specify the To Time");
			return;
		}
		if(!((Date.parse(date+" "+fromTime)>=Date.parse(date+" "+"6:00 AM")) && (   	Date.parse(date+" "+toTime)<=Date.parse(date+" "+"10:00 PM")))){	
			 $(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("From time and to time should be between 6:00 AM to 10:00 PM.");
			 return;
		 } 
		if(!(Date.parse(date+" "+toTime) > Date.parse(date+" "+fromTime))){
			 $(this).closest("tr").find(".errorDivFrTmSltUpdtId").html("To Time should be greater than From Time.");
			 return;
		 } */
	    setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,"update",timeSlotId,"");
	});	
	function setTimeSlotForAppointment(appointmentId,date,fromTime,toTime,type,timeSlotId,commentTxt){
		
		var errorMsgId='';
		if(type == "save"){
			$("#errorDivForTimeSlotId").show();
		    $("#errorDivForTimeSlotId").html(" ");
			
			errorMsgId = $("#errorDivForTimeSlotId");
		}else if(type == "update"){
		   $("#updateTimeSlotMsgShow").html('');
		   
		   errorMsgId =  $("#updateTimeSlotMsgShow");
		}
		
		//Validations For Time Slot Creation
		
		   if(appointmentId ==null || appointmentId <=0 || appointmentId ==undefined){
			$(errorMsgId).html("<span style='color:red;font-size:14px'>Please Specify the Appointment</span>");
			return;
		  }   
		
		/*if(fromTime ==null || fromTime.length ==0 || fromTime == undefined){
			$(errorMsgId).html("<span style='color:red;font-size:14px'>Please Specify the From Time</span>");
			return;
		}if(toTime ==null || toTime.length ==0 || toTime == undefined){
			$(errorMsgId).html("<span style='color:red;font-size:14px'>Please Specify the To Time</span>");
			return;
		}
		
		 
		if(!((Date.parse(date+" "+fromTime)>=Date.parse(date+" "+"6:00 AM")) && (   	Date.parse(date+" "+toTime)<=Date.parse(date+" "+"10:00 PM")))){
			 $(errorMsgId).html("<span style='color:red;font-size:14px'>From time and to time should be between 6:00 AM to 10:00 PM.</span>");
			 return;
		 } 
		if(!(Date.parse(date+" "+toTime) > Date.parse(date+" "+fromTime))){
			 $(errorMsgId).html("<span style='color:red;font-size:14px'>To Time should be greater than From Time.</span>");
			 return;
		 }*/
		
		if(type=="save"){
			$("#ajaxImgForTimeSlotId").css("display","inline-block");
		}
		 
		var jsObj={
			appointmentId : appointmentId,
			date : date,
			fromTime : fromTime,
			toTime : toTime,
			type :type,
			timeSlotId:timeSlotId,
			commentTxt:commentTxt,
			apptUserId : $("#appointmentUserSelectBoxId").val()
		}
		
		$.ajax({
			type : 'POST',
			url : 'setTimeSlotForAppointmentAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(type=="save"){
				$("#errorDivForTimeSlotId").show();
			    $("#ajaxImgForTimeSlotId").css("display","none");
			}else if(type=="update"){
				$("#updateTimeSlotMsgShow").html('');
			}
			if(result != null && result.exceptionMsg != null && result.exceptionMsg == "success"){
				
				if(type=="save"){
					
					  $("#errorDivForTimeSlotId").html("<p style='color:green;font-size:20px'>Saved Successfully</p>");
					  setTimeout('$("#errorDivForTimeSlotId").hide()', 2000);
					  $('html, body').animate({scrollTop: $("#errorDivForTimeSlotId").offset().top}, 2000);
					  $("#confirmAppointmentBlockDropId").empty();
					  $("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
					  //setting default time format
					   $("#fromTimeId").val("00:00 AM");
					   $("#toTimeId").val("00:00 PM");
					   $("#commentTxt").val("");
					    //Reinitializing date range picker after setting time slot for appointment
					    $("#appointmentDateSlotId").daterangepicker({singleDatePicker:true,minDate:new Date()});
                        $('#appointmentDateSlotId').val(moment().format('MM/DD/YYYY')); 
						 
					    getAllScheduledApptsByDate();
					    getAppointmentStatusOverview();
					   
			   }else if(type=="update"){
				   
				     $("#updateTimeSlotMsgShow").html("<p style='color:green;font-size:20px'>Appointment TimeSlot Updated Successfully</p>");
					 setTimeout('$(".appointmentTimeSlotModalpopup").modal("hide")', 2000);
					 
					 //set updated values.
					 
					 var attrId = $("#updateSettingApptId"+appointmentId);
					 $(attrId).attr("attr_date",date);      
					 $(attrId).attr("attr_from_time",fromTime);
					 $(attrId).attr("attr_to_time",toTime);
					 var dateArr=date.split("/");
					// var dateAndtime = date;//+"  "+fromTime+' to '+toTime;
					 $('#updateApptTimeSlotDateAndTimeId'+appointmentId).html(dateArr[2]+"-"+dateArr[0]+"-"+dateArr[1]);
					 
				}
			}else{
				  if(type=="save"){
					  $("#errorDivForTimeSlotId").html("<p style='color:red;font-size:20px'>Failure,Some problem occured while creating time slot.</p>");
				      $('html, body').animate({scrollTop: $("#errorDivForTimeSlotId").offset().top}, 2000); 
				  }else if(type=="update"){
					  $("#updateTimeSlotMsgShow").html("<p style='color:red;font-size:20px'>Failure,Some problem occured while Updating time slot.</p>");
				  }
				  
			}
		});
		
	}
	function clearUpdateTimeSlotModalPopUp(){
		
		 $('#appointmentDateSlotModalId').val('');
		 $('#fromTimeModalId').val('');
		 $('#toTimeModalId').val('');
		 $('#showCommentTxt').val('');
		 
		 $('#hiddenTimeSlotModalId').val('');
		 $('#hiddenTimeSlotApptModalId').val('');
		 
		 $("#updateTimeSlotMsgShow").html('');
	}
	
	 $(document).on("click","#addApptsId",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});
	
	$(document).on("click",".viewMembersClass",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});
	$(document).on("click","#delApptsScrollBarId",function(){
		 setTimeout(function() {$('html, body').animate({scrollTop:2000}, 3000); },0);
	});
</script>

<script>

function getDistrictsForReferPopup()
{
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
		else if(levelId == 1)
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
				$(".constiCls").show();
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
		//$(".addCandidateTypeCls option").remove(); 
		$("#candidateType option").remove(); 
			$(".cloneCandidateTypeCls").append('<option value="0">Select Candidate Type</option>'); 
			$("#candidateType").append('<option value="select">Select Candidate Type</option>');
			$("#candidateType").append('<option value="0" selected>All</option>');
			for(var i in result){
				$(".cloneCandidateTypeCls").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$("#candidateType").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#candidateType").chosen();
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
				var str ='';
				str +='<option id="0" attr_value="All"  >All</option>';
					for(var i in result){
						str +='<option value="'+result[i].id+'" attr_value="'+result[i].name+'"  >'+result[i].name+'</option>';
					}
				
				$("#cadreCommitteeDiv").html(str);
				$("#cadreCommitteeDiv").chosen();
				$("#cadreCommitteeDiv").trigger("chosen:updated");
				$("#cadreCommitteeDiv_chosen").addClass("m_top20");
				$("#cadreCommitteeDiv_chosen").addClass("addwidth");
				});			  
      }
     
			$(document).on("click",".refreshBlockDiv",function(e){
				$("#LineChart").html('');
				$("#searchStrId").attr("placeholder", "Name or MobileNumber").val("");
				$("#selectStatusId").val(0);
				getTotalAppointmentStatus();
				$(".daterangepicker").hide();
				//getAppointmentUsersDtls();
				getAppointmentStatusCounts();
				getSearchDetails(true);
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
		   $("#approvedStatus").html('');
		   $("#approvedStatus").html('0');
		   $("#notAttendedStatus").html('');
		   $("#notAttendedStatus").html('0');
		   $("#cancelledStatus").html('');
		   $("#cancelledStatus").html('0');
		   $("#reScheduledStatus").html('');
		   $("#reScheduledStatus").html('0');
		   
    	var aptUserId = $("#appointmentUserSelectBoxId").val();
    	var jsObj={
				appointmentUserId : aptUserId,
    			task:""
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAppointmentStatusOverviewAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
    		   if(result!=null && result.length>0){
				   buildAppointmentStatusOverAllView(result);
			   }
    	   });	
	  }
	  function buildAppointmentStatusOverAllView(result)
	  {
			var total = 0;
		 for(var i in result)
		  {
			 if(result[i].id == 2 || result[i].id == 10 || result[i].id == 5 || result[i].id == 8)
			 {
				 total = total + result[i].availableCount;
			 }				 
		  }
		  if(total>0){
			  $("#AllStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId="0">'+total+'</a>');	
		  }else{
			  $("#AllStatus").html('<a style="color:white">'+total+'</a>');	
		  }
		 for(var i in result)
		  {
			
			  if(result[i].id == 2)
			  { 
				  $("#approvedStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
			  }
			  if(result[i].id == 10)
			  {
				  $("#notAttendedStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
				  
				 // $("#notAttendedStatus").html(''+result[i].availableCount+''); 
			  }
			 if(result[i].id == 5)
			  {
		  
				   $("#cancelledStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
				 // $("#cancelledStatus").html(''+result[i].availableCount+''); 
			  }
			  if(result[i].id == 8)
			  {
				  $("#reScheduledStatus").html('<a class="confirmViewClass" style="cursor:pointer;color:white" attr_statusId='+result[i].id+'>'+result[i].availableCount+'</a>');
				  
				 // $("#reScheduledStatus").html(''+result[i].availableCount+''); 
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
		  $("#aptCandidateHistorystatusOverViewDiv").html('<img src="images/search.gif" />');
	    	var jsObj={
	    			appointmentCandidateId:id,
					apptUserId:$("#appointmentUserSelectBoxId").val(),
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
			str+='<p>Total Appointment Requested - '+total+'</p>';
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
			//$("#buildCommentsForHistoryView").html('<img src="images/search.gif" />');
	    	var jsObj={
	    			appointmentCandidateId:id,
					apptUserId:$("#appointmentUserSelectBoxId").val(),
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAppointmentHistoryForCandidateAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAppointmentHistoryForCandidate(result);
		  buildAppointmentCommentsForViewHistory(result);
		  });
	  }
	  
	function buildAppointmentHistoryForCandidate(result)
	{
		var str='';
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="aptCandidateHistorydatatable">';
		str+='<thead>';
		str+='<th>ID</th>';
		str+='<th>PURPOSE</th>';
		str+='<th>CREATED ON</th>';
		str+='<th>PREFERED DATE</th>';
		str+='<th>SCHEDULED DATE</th>';
		str+='<th>STATUS</th>';
		str+='<th></th>';
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
		str+='<td><img onclick="getAppointCommentsForTracking(\''+result[i].id+'\',\''+result[i].uniqueCode+'\')" style="height:16px;cursor:pointer;margin-right:5px;" title="View Status History" attr-aptname="'+result[i].uniqueCode+'" attr-id="'+result[i].id+'" class="pull-right " src="dist/Appointment/img/reqHistoryicon+.png">';
		str+='</tr>';	
		}
		str+='</tbody>';
		str+='</table>';
		str+='<div id="appointmentCommentsDiv" class="m_top30"></div>';
		$("#aptCandidateHistoryDiv").html(str);	
	     $('#aptCandidateHistorydatatable').DataTable();
	}
	function getAppointCommentsForTracking(id,name)
	{
		$("#appointmentCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			appntmntId:id,
					
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAppointmentStatusCommentsTrackingDetails.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAppointCommentsForTracking(result,name);
		});
		
	}
	
	
	function buildAppointCommentsForTracking(result,aptName)
	{
			var str='';
	
	str+='<h4>'+aptName+' Appointment Status Tracking </h4>';
	if(result == null || result.length == 0)
		$("#appointmentCommentsDiv").html('No Data Available');
	str+='<ul class="apptStatusTracking">';
	for(var i in result){
		
		str+='<li>';
			str+='<div class="arrow_box">';
			if(result[i].id == 1)
			str+='<p> <span class="text-success"></span> Appointment Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
				else
				str+='<p>Appointment status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
			
				if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0)
				{
					str+='<u style="font-size:15px;">Comments</u>';
					for(var j in result[i].commentsList)
					{
					
					str+='<p>'+result[i].commentsList[j]+'</p>';	
					}
				}
				
			str+='</div>';
		str+='</li>';	
	}
	str+='</ul>';
	$("#appointmentCommentsDiv").html(str);
	 $('html,body').animate({
        scrollTop: $("#appointmentCommentsDiv").offset().top},
        'slow');


	}

	
	function buildAppointmentCommentsForViewHistory(result){
		
		var str ='';
		str+='<div class="row">';
		str+='<div class="col-xs-12">';
		str+='<div class="row">';
		str+='<div class="col-md-3" style="font-size: 18px;font-weight:bold;"><span style="font-weight: bold;"><i class="glyphicon glyphicon-comment"></i> &nbsp;&nbsp;</span>Comments</div>';
		str+='</div>';
		str+='<table class="table table-bordered table-condensed" id="commentsdatatable">';
		str+='<thead>';
		str+='<th style="padding:0px"></th>';
		str+='</thead>';
		str+='<tbody>';
		
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].commentlist != null && result[i].commentlist.length>0){
					/* for(var j in result[i].commentlist){
						str+='<tr>';
						str+='<td>';
						str+='<div class="row">';
						str+='<div class="col-md-3" style="font-size: 11px;"><span style="font-weight: bold;">Appointment ID :&nbsp;&nbsp;</span>'+result[i].commentlist[j].uniqueCode+'</div>';
						str+='<div class="col-md-3" style="font-size: 11px;"><span style="font-weight: bold;">Status :&nbsp;&nbsp;</span>'+result[i].commentlist[j].status+'</div>';
						str+='<div class="col-md-3" style="font-size: 11px;"><span style="font-weight: bold;">Date :&nbsp;&nbsp;</span>'+result[i].commentlist[j].createdOn+'</div>';
						str+='</div>';
						str+='<p style="margin-top: 10px;">'+result[i].commentlist[j].comment+'</p>';
						str+='<p class="text-bold" style="margin-top: 10px;">Comment By - '+result[i].commentlist[j].user+'</p>';
						str+='</td>';
						str+='</tr>';
					} */
				}
				
			}
		}
		
		
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		//$("#buildCommentsForHistoryView").html(str);
		  $('#commentsdatatable').DataTable();
	}
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
			
				  url      :   "sendSMSForAppointmtAction.action",
				  type     :   "POST",
				  datatype :   "json",
				  data     :  { task:JSON.stringify(jsObj) },
				  success  :  function (result) {
								  $(".msgDiv1"+appointmentId).html("Sms Sent Successfully").css("color","green");
								  setTimeout(function(){ $(".msgDiv1"+appointmentId).html(""); },2000); 
								  $(".sendSms"+appointmentId).val('');
							  },
				  error     : function (jqXHR, textStatus, errorThrown) {
								  if(jqXHR.status == 500){
									  $(".msgDiv1"+appointmentId).html("Unexpected Error.Please Try Again..").css("color","red");
								  }else{
									 alert('Unexpected error.');
								  }
							  }
			 }); 
			
	});
	 function clearFields(){
		$("#multiDate").val("");
		$("#appointmentReasonId").val(""); 
		$("#candidateNameId0").val(""); 
		/* $(".cloneCandidateTypeCls").val(0); 
		$(".cloneDesignationCls ").val(0); */
		$("#mobileNoId0").val(""); 	
		$("#voterCardNoID0").val(""); 
		$("#membershipNumId0").val(""); 
		/* $(".cloneLocationScopeCls").val(0); 
		$(".cloneDistrictCls").val(0); 
		$(".cloneConstituencyCls").val(0);  
		$(".cloneMandalCls").val(0);
		$(".cloneVillageCls").val(0);  */ 		
	 }
	
	function getPublicRepresentativeWiseAppointmentCnt(){
		
		var appointmntCandteId=$("#appointmentUserSelectBoxId").val();
		var jsObj = {
			apontntCnditeId:appointmntCandteId,
			task:""
		}
	$.ajax({
		type : 'GET',
		url : 'getPublicRepresentativeWiseAppointmentCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 

		if(result != null){
			buildPublicRepresentativeWiseAppointmentCnt(result,jsObj);
		}
		
	});     
}
function buildPublicRepresentativeWiseAppointmentCnt(result,jsObj)
{
   var str='';
    str+='<div class="block">';
      str+='<div class="row">';
        str+='<div class="col-md-12">';
          str+='<h4 class="text-capitalize text-success">public representative wise appointments</h4>';
          str+='<table class="table table-bordered" id="dataTablePublicId">';
            str+='<thead>';
              str+='<tr>';
                str+='<th></th>';
                str+='<th class="text-capitalize text-center" colspan="2">total</th>';
                str+='<th class="text-capitalize text-center" colspan="2">requested</th>';
                str+='<th class="text-capitalize text-center" colspan="2">appointment scheduled</th>';
              str+='</tr>';
              str+='<tr>';
              // str +='<th></th>';
                str+='<th class="text-capitalize">role</th>';
                str+='<th class="text-capitalize">total</th>';
                str+='<th class="text-capitalize">unique</th>';
                str+='<th class="text-capitalize">total</th>';
                str+='<th class="text-capitalize">unique</th>';
                str+='<th class="text-capitalize">total</th>';
                str+='<th class="text-capitalize">unique</th>';
              str+='</tr>';
            str+='</thead>';
            str+='<tbody>';
            for(var i in result){
              str+='<tr>';
              str+='<td>'+result[i].role+'</td>';
              if(result[i].total>0)
              {
              str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'total\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].total+'</a></td>';
              }
              else
              {
                str+='<td>'+result[i].total+'</td>';
              }
              if(result[i].uniquecnt>0)
              {
              str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'total\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniquecnt+'</a></td>';
              }
              else
              {
              str+='<td>'+result[i].uniquecnt+'</td>';  
              }
              if(result[i].requestedCnt>0)
              {
              str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Request\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].requestedCnt+'</a></td>';
              }
              else
              {
                str+='<td>'+result[i].requestedCnt+'</td>';
              }
              if(result[i].uniqueRequestedCnt>0)
              {
              str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Request\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniqueRequestedCnt+'</a></td>';
              }
              else
              {
                str+='<td>'+result[i].uniqueRequestedCnt+'</td>';
              }
              if(result[i].scheduledCnt>0)
              {
              str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Schedule\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].scheduledCnt+'</a></td>';
              }
              else
              {
              str+='<td>'+result[i].scheduledCnt+'</td>';  
              }
              if(result[i].uniqueScheduledCnt>0)
              {


			str+='<td><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'PR\',\'unique\',\'Schedule\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniqueScheduledCnt+'</a></td>';
              }
              else
              {
                  str+='<td>'+result[i].uniqueScheduledCnt+'</td>';  
              }
                
              str+='</tr>';
            }
            str+='</tbody>';
          str+='</table>';
        str+='</div>';
      str+='</div>';
    str+='</div>';
    $("#advanceDshAppointmentPrWiseDiv").html(str);
    
    $("#dataTablePublicId").DataTable({	  
	  });
}
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
</script>
<script>

function getStatusTrackingDetls(appontmntId,aptName){
	
	var jsObj={
		appntmntId : appontmntId
	}
	$.ajax({
		type : 'POST',
		url : 'getAppointmentStatusCommentsTrackingDetails.action',
		dataType : 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			apptTrackingStatus(result,aptName);
		}else{
			$("#apptStatusTracking").html("<center>No Data Available</center>")
		}
	});
}


function apptTrackingStatus(result,aptName)
{
	setcolorsForStatus();
	var str='';
	$("#statusTrackingName").html(''+aptName+' Appointment Status Tracking')
		str+='<ul class="apptStatusTracking">';
	for(var i in result){
		var color = getColorCodeByStatus(result[i].status);
		str+='<li>';
			str+='<div class="arrow_box_left">';
			if(result[i].id == 1)
			str+='<p> <span class="text-success"></span> Appointment Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
				else
				str+='<p>Appointment status changed to <span class="" style="color:'+color+'"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
				if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0)
				{
					str+='<u style="font-size:15px;">Comments</u>';
					for(var j in result[i].commentsList)
					{
					
					str+='<p>'+result[i].commentsList[j]+'</p>';	
					}
				}
				
			str+='</div>';
		str+='</li>';	
	}
	str+='</ul>';
	$("#apptStatusTracking").html(str)
}

$(document).on("click",".confirmViewClass",function(){
	var statusId = $(this).attr("attr_statusId");
	getSerchDetailsByStatus(statusId);
});
function getSerchDetailsByStatus(statusId){
	//getAppointmentsBySearchCriteria();
	
		 var fromDate='';
		var toDate='';
		/*var dateStr = $("#addMembersFromDateId").val(); 
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
		 } */
		 
		 //clearing drag drop time slot div 
		 $("#confirmAppointmentBlockDropId").empty();
		 $("#confirmAppointmentBlockDropId").html("<h4 class='deleteTag'>DROP HERE</h4>");
		 var jsObj={
			designationId:0,
			priorityId:0,
			statusId:statusId,
			districtId:0,
			constituencyid:0,
			appointmentlabelId : 0,
			fromDate :fromDate,
			toDate:toDate,
			selUserId:$("#appointmentUserSelectBoxId").val(),
			candidateTypeId:0,
			dateType:2,
			apptUserId:$("#appointmentUserSelectBoxId").val()
		  }
		  $.ajax({
				type : 'POST',
				url : 'getAppointmentsBySearchCriteriaAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildLabelResult(result,labelName);
			});

}
$(document).on("click",".appointmentStatusCls",function(){
	
	 var statusType=$(this).attr("attr_status_type");
	  if(statusType==undefined || statusType==" " || statusType==null){
		  statusType='singleStatus';
	  }
	 $('html, body').animate({
		scrollTop: $('.showTimeSlotsCls').offset().top
	}, 2000);
	
	var statusArray =[];
	 $(this).each(function(){
		 var statusIds= $(this).attr("attr_statusArrId");
		 if(statusIds.indexOf(',') !== -1){
			 statusArray = statusIds.split(",");
		 }else{
			 statusArray.push( statusIds );
		 }	
	 });
	
	getappointmentStatusDetails(statusArray,null,statusType);			
	
});

function  getappointmentStatusDetails(statusArray,type,statusType){
	
	if(type ==null){
		type='';
	}
	//clearing the searchAppointment div
	$("#searchApptmntDivId").empty();
	$("#srhPrcssngImgId").show();
	var jsObj={
			createdBy : 0,
			appointmentUserId:$("#appointmentUserSelectBoxId").val(),
			searchStr:'',
			strDate:'',
			endDate:'',
			statusArray:statusArray,
			type:type,
			task:""			
		}
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#srhPrcssngImgId").hide();
				buildAppointmentSearchResult(result," ",statusType);		
			})
}

  $(document).on("click",".todayappointmentStatusCls",function(){
	
	 var statusType=$(this).attr("attr_status_type");
	  if(statusType==undefined || statusType==" " || statusType==null){
		  statusType='singleStatus';
	  }
	 $('html, body').animate({
		scrollTop: $('.showTimeSlotsCls').offset().top
	}, 2000);

	var statusArray =[];
	 $(this).each(function(){
		 var statusIds= $(this).attr("attr_todayStatusArr");
		 if(statusIds.indexOf(',') !== -1){
			 statusArray = statusIds.split(",");
		 }else{
			 statusArray.push( statusIds );
		 }	
	 });
	getappointmentStatusDetails(statusArray,"today",statusType);			
	
}); 

function getTimeSlotsForADayByAppytUserId(){
	
	$('#timeSlotErrMsgId').html('');
	$('#timeSlotDatesBuildId').html('');
	$("#ajaxImgFortimeSlotButtonId").show();
	var  dateStr       = $('#appointmentDateSlotHeadingId').val();
	var  appntmntId   =  $("#appointmentUserSelectBoxId option:selected").val();
	if(dateStr.trim().length <= 0){
		$('#timeSlotErrMsgId').html('Please Select Date');
		return;
	}
	
	var jsObj={
			dateStr : dateStr,
			appntmntId:appntmntId
		}
		
		  	$.ajax({
				type : 'POST',
				url : 'getTimeSlotsForADayByAppytUserIdAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#ajaxImgFortimeSlotButtonId").hide();
				timeSlotTableBuilding(result,dateStr);
			})
	
}

function timeSlotTableBuilding(result,dateStr){
	
	var str='';
	str+='<div class="pluginTable">';
			str+='<ul class="row">';
			
				str+='<li class="col-md-2 col-xs-4 col-sm-2 m_top10">';
					str+='<table class="table tablePluginDate m_top20">';
						str+='<tr>';
							str+='<td class="text-center" style="height:29px;background-color:none;">'+dateStr+'</td>';
						str+='</tr>';
					str+='</table>';
				str+='</li>';
				
				str+='<li class="col-md-10 col-xs-8 col-sm-10">';
				str+='<table class="table table-bordered tablePlugin">';
					
						str+='<thead>';
							str+='<th colspan="4">6a</th>';
							str+='<th colspan="4">7</th>';
							str+='<th colspan="4">8</th>';
							str+='<th colspan="4">9</th>';
							str+='<th colspan="4">10</th>';
							str+='<th colspan="4">11</th>';
							str+='<th colspan="4">12p</th>';
							str+='<th colspan="4">1</th>';
							str+='<th colspan="4">2</th>';
							str+='<th colspan="4">3</th>';
							str+='<th colspan="4">4</th>';
							str+='<th colspan="4">5</th>';
							str+='<th colspan="4">6</th>';
							str+='<th colspan="4">7</th>';
							str+='<th colspan="4">8</th>';
							str+='<th colspan="4">9</th>';
							str+='<th colspan="4">10</th>';
						str+='</thead>';
				
	                   str+='<tr class="borderSlot">';
						for(var unique=0;unique<=63;unique++){
				          str+='<td id="'+unique+'"></td>';
			            }	
	                  str+='</tr>';
	
					$('#timeSlotDatesBuildId').html(str);
					
					if(result!=null && result.length>0){
					
					for(var i in result){
					 
						var start=result[i].startDate;
						var end =result[i].endDate;
						var startIdForHour=start.substr(11,2);
						var startIdForMin=start.substr(14,2);
						var startId=(startIdForHour-6)*4;
					
						startId= startId+(startIdForMin/15);
						var strtDividedVleForMnte=startIdForMin%15;
						   if(strtDividedVleForMnte>=8){
							   startId=startId+1;
						   }
						var endIdForHour=end.substr(11,2);
						var endIdForMin=end.substr(14,2);
						var endId=(endIdForHour-6)*4;
						
						endId= endId+(endIdForMin/15);
						var endDividedVleForMnte=endIdForMin%15;
						   if(endDividedVleForMnte>=8){
							   endId=endId+1;
						   }
						endId=endId-1;  
						for(var start=startId;start<=endId;start++){
							$("#"+start).addClass("bookedSlots");
						}
				}
		    }
	}
	function getAllScheduledApptsByDate(){

	
	var  dateStr       = $('#appointmentDateSlotHeadingId').val();
	var  apptUserId   =  $("#appointmentUserSelectBoxId option:selected").val();
	
	var jsObj={
			dateStr : dateStr,
			apptUserId:apptUserId
		}
		
		  	$.ajax({
				type : 'POST',
				url : 'getAllScheduledApptsByDateAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildAppointmentMembersDetails(result);
			})
	}
	function buildAppointmentMembersDetails(result){
		
		setcolorsForStatus();
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		
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
		str+='<div><h4 class="text-success">APPOINTMENT TIMESLOT UPDATION</h4></div>';
		if(result != null)
		{
			var xindex = 0;
			
			for(var i in result)
			{ 
			if( xindex % 3 == 0)
			{
				str+='<div class="row m_top10">';
			}
					str+='<div class=" col-md-6 col-xs-12 updateChangeClass" >';
					str+='<div class="panel panel-default manageAppViewPanelClass m_top5">';
						str+='<div class="panel-heading bg_ff pad_5">';
							str+='<p class="settingClass" style="font-size:10px;cursor:pointer;"><i  attr_span_popup_id='+result[i].appointmentId+' attr_appt_status_id='+result[i].statusId+' attr_date='+result[i].formatDate+' attr_from_time="'+result[i].time+'" attr_to_time="'+result[i].toTime+'"  attr_comment="'+result[i].subject+'" attr_timeSlotId="'+result[i].apptTimeSlotId+'" class="glyphicon glyphicon-cog updateAppointmentClass  pull-right" id="updateSettingApptId'+result[i].appointmentId+'"  title="Appointment Status Update" data-toggle="tooltip" data-placement="top" ></i>ID: '+result[i].appointmentUniqueId+'&nbsp;&nbsp;&nbsp;';
							var color = getColorCodeByStatus(result[i].appointmentStatus);
							str+='<span style="font-weight:bold;color:'+color+'" id="statusSpanId'+result[i].appointmentId+'">'+result[i].appointmentStatus+'</span>';
							
							if(result[i].date != "" && result[i].statusId == 3){
								var dateAndtime = result[i].date;//+"  "+result[i].time+' to '+result[i].toTime;
								
								str+='<span class="pull-right"><span class="text-success"><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;<span id="updateApptTimeSlotDateAndTimeId'+result[i].appointmentId+'">'+dateAndtime+'</span></span> &nbsp;</span>';
							}
							
							str+='</p>';
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
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].subList[j].mobileNo+'';
						if(result[i].subList[j].id != null && result[i].subList[j].id > 0){
								str+='<a style="display:inline-block;" title="Appointments History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn pull-right"  style="cursor:pointer;" attr-id="'+result[i].subList[j].id+'" attr-name="'+result[i].subList[j].name+'" attr-designation="'+result[i].subList[j].designation+'" attr-mobile="'+result[i].subList[j].mobileNo+'"><img src="dist/Appointment/img/view-Appt-History-icon.png"  alt="ViewApptHistory" style="height:16px;cursor:pointer;margin-right:5px;"/></a>&nbsp;&nbsp;';
						}
						str+='</p>';
						str+='</div>';
						str+='</div>';
						//multiple
						
						str+='</li>';
						
						}
						str+='</ul>';
						if(result[i].subject!=null && result[i].subject.length>35){
							  str+='<p class="" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subject+'" >Purpose: '+result[i].subject.substring(0,35)+'...</p>';
							}else{
							  str+='<p class="" style="margin-left: 52px; margin-top: -6px;">Purpose:'+result[i].subject+' </p>';
							}
					str+='<p class="m_top10">';
					str+='<i>Appt Created By: '+result[i].subList[j].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon" title="Send Sms" data-toggle="tooltip" data-placement="top" />';
					 
					  str+='<img src="dist/Appointment/img/reqHistoryicon+.png" class="pull-right statusTrackingModalbtn" attr-id='+result[i].appointmentId+' attr-aptName='+result[i].appointmentUniqueId+' alt="ViewReqHistory" style="height:16px;cursor:pointer;margin-right:5px;" title="Appointment Requested History" data-toggle="tooltip" data-placement="top"/>'; 
					str+='</p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<span class="errorCls msgDiv1'+result[i].appointmentId+'"></span>';
					str+='<textarea class="form-control sendSms'+result[i].appointmentId+'" ></textarea>';
					str+='<button class="btn btn-success btn-block sendsms" value="'+result[i].appointmentId+'">SEND SMS</button>';
					str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				//}
				if(xindex % 3 == 2)
				{
					str+='</div>';
				}
				
				if(result.length-1 == xindex && xindex % 3 != 2)
				{
					str+='</div>';
				}	
			xindex++;
			}
			
		}
		else
		{
			flag = false;
			//str+='No Data';	
		}
		
		if(flag == false)
		{
			$(".completedSetting").hide();
			str+='No Data Available';	
		}
		$("#appointmentMembersDivId").html(str);
		
		$('[data-toggle="tooltip"]').tooltip();
		if(flag == false){
		   $(".completedSetting").hide();
		}
			
		}
	$(document).on("click",".updateAppointmentClass",function(e){
		
		
		$(".appointmentTimeSlotModalpopup").modal("show");
		
		var appId             =  $(this).attr("attr_span_popup_id");
		var scheduledDate     =  $(this).attr("attr_date");
		var fromScheduledTime =  $(this).attr("attr_from_time");
		var toScheduledTime   =  $(this).attr("attr_to_time");
		var commentTxt        =  $(this).attr("attr_comment");
		var appntTimeSlotId   =  $(this).attr("attr_timeSlotId");
	
    	showModalForappointmentTimeSlot();
		 
		 //clear the values
		clearUpdateTimeSlotModalPopUp();
		  
		//initlization dates
		$("#appointmentDateSlotModalId").daterangepicker({singleDatePicker:true,minDate:new Date()});
		$("#toTimeModalId").datetimepicker({format: 'LT'})	
		$("#fromTimeModalId").datetimepicker({format: 'LT'})
		
		// set the values
		$('#appointmentDateSlotModalId').val(scheduledDate);
		//$('#fromTimeModalId').val(fromScheduledTime);
		//$('#toTimeModalId').val(toScheduledTime);
		$('#fromTimeModalId').val("00:00 AM");
		$('#toTimeModalId').val("00:00 PM");
		$('#showCommentTxt').val();
		$('#hiddenTimeSlotModalId').val(appntTimeSlotId);
		$('#hiddenTimeSlotApptModalId').val(appId);
		
	})
	
	$(document).on("click","#updateTimeSlotForApptId",function(e){
		
		setTimeSlotForAppointment( $('#hiddenTimeSlotApptModalId').val() ,$('#appointmentDateSlotModalId').val(),$('#fromTimeModalId').val(),$('#toTimeModalId').val(),'update',$('#hiddenTimeSlotModalId').val(),$('#showCommentTxt').val());
	})
	
	function showModalForappointmentTimeSlot(){
		
		var str='';
		
	 str+='<div class="row m_top20">';
		 str+='<div class="col-md-12">';
			 str+='<div style="background:#F3f3f3;margin:0px -10px;padding:12px 0px;" class="row">';
			 
				 str+='<div id="" class="validateClr m_left16"></div>';
				 
				 str+='<div class="col-md-4">';
					 str+='<label>Select Date</label>';
					 str+='<div class="input-group inputSearch">';
						 str+='<span class="input-group-addon">';
							 str+='<i class="glyphicon glyphicon-calendar"></i>';
							 str+='<span class="caret"></span>';
						 str+='</span>';
						 str+='<input type="text" class="form-control" id="appointmentDateSlotModalId">';
					 str+='</div>';
				 str+='</div>';
				 
				 str+='<div class="col-md-3">';
					 str+='<label>From Time</label>';
					 str+='<div class="input-group inputSearch">';
						 str+='<span class="input-group-addon">';
							 str+='<i class="glyphicon glyphicon-time"></i>';
							 str+='<span class="caret"></span>';
						 str+='</span>';
						 str+='<input type="text" id="fromTimeModalId" class="form-control" style="width: 86px;">';
					 str+='</div>';
				 str+='</div>';
				 
				 str+='<div class="col-md-3">';
					 str+='<label>To Time</label>';
					 str+='<div class="input-group inputSearch">';
						 str+='<span class="input-group-addon">';
							 str+='<i class="glyphicon glyphicon-time"></i>';
							 str+='<span class="caret"></span>';
						 str+='</span>';
						 str+='<input type="text" id="toTimeModalId" class="form-control" style="width: 87px;">';
					 str+='</div>';
				 str+='</div>';
				 
				 str+='<div class="col-md-8 m_top10">';
				 str+='<textarea  placeholder="Please Enter Comment..." cols="40" rows="2" id="showCommentTxt"></textarea>';
				 str+='</div>';
				 str+='<div class="col-md-4">';
					 str+='<button class="btn btn-success m_top25" id="updateTimeSlotForApptId">SET</button>';
				 str+='</div>';
				 
				   //hidden variable for time slotId
				  str+='<input type="hidden" id="hiddenTimeSlotModalId" class="form-control">';
				  str+='<input type="hidden" id="hiddenTimeSlotApptModalId" class="form-control">';
				  str+='<input type="hidden" id="hiddenTimeSlotApptModalId" class="form-control">';
				  
			 str+='</div>';
		 str+='</div>';
		
	 str+='</div>';
	 
	 $("#appointmentTimeSlotModal").html(str);
	}
	
	$(document).on("click",".confirmViewClass",function(e){
		
		$(".changeClass").removeClass("col-md-12");
		$(".changeClass").addClass("col-md-8");
		 
		$(".updateChangeClass").addClass("col-md-6"); 
		
	})
	  $(document).on("click","#timeSlotButtonId",function(e){
		setTimeout(function(){$(".updateChangeClass").addClass("col-md-6");
		}, 500);
	})  
	
	
	$(".saveDesignationCls").click(function(){
		var designation=$(".designationCls").val().trim();
		var cnt=$(this).attr("id");
		 if(designation==" " || designation==undefined || designation.length==0){
			 $(".designationErrCls").html("Please Enter Designation.");
		 }else{
			 saveDesignationForOtherCandidate(designation,cnt); 
		 }
	});
	function saveDesignationForOtherCandidate(designation,cnt){
		
		var jsObj={
			designation:designation,
			candidateTypeId:4
		}
		$.ajax({
			type : 'POST',
			url : 'saveDesignationForOtherCandidateAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
				if(result.exceptionMsg=="Success"){
					$(".statusCls").html("<p style='color:green;'>Designation Saved Successfully.</p>");
					 setTimeout(function(){$(".statusCls").html("");}, 2000);
					 setTimeout(function(){$("#blockForOtherCandidateModalId").modal("hide");}, 2000);
					 getDesignationsByTypeForChange(cnt,"other");
				}else if(result.exceptionMsg=="exist"){
					$(".statusCls").html("<p style='color:red;text-center'>Designation already exist.</p>");
				}else{
				  $(".statusCls").html("<p style='color:red;text-center'>Error occured try again.</p>");
				}
			}
		});		
	}
	function buildLevels()
	{
		var searchType = $("#advanceSearchTypeId").val();
		var str='';
		 $("#levelId").find('option').remove();
		  str+='<option value="10">State</option>';
		  str+='<option value="11">District</option>';
		  if(searchType != 3)
		  str+='<option value="1">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#levelId").append(str);
		    $("#levelId").dropkick();
			 var select = new Dropkick("#levelId");
			 select.refresh();
	}
 function getLevelByDesignation()
 {
	 
	  $("#levelId").find('option').remove();
	   var stateGrpIds = ["6","23","7","12","16","22"];
	 var distGrpIds = ["1","9","11"];
	 var mandalGrpIds =["13","3","4","5","17","18","19","20","21"];
	 var constiGrpIds =["2","8","10",];
	 var designationId =$("#advanceDesignationId").val();
	
	 var str ='';
	  if(jQuery.inArray(designationId, stateGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		
		 $("#levelId").append(str);
	 }
	else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		 $("#levelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, distGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		  str+='<option value="1">Constituency</option>';
		 $("#levelId").append(str);
	 }
	 else if(jQuery.inArray(designationId, mandalGrpIds ) > -1)
	 {
		 str+='<option value="10">State</option>';
		 str+='<option value="11">District</option>';
		 str+='<option value="5">Mandal/Muncipality</option>';
		 $("#levelId").append(str);
	 }
	 
	else
	 {
		  str+='<option value="10">State</option>';
		  str+='<option value="11">District</option>';
		   str+='<option value="1">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#levelId").append(str);
	 }
	   $("#levelId").dropkick();
			 var select = new Dropkick("#levelId");
			 select.refresh();
			 		disableByLevel();
	 	
 }
buildLevels();

$(document).on("click",".timeSlotHideShowCls",function(){
	$(".changeTimeSlotClass").toggle();

	if($(this).hasClass("glyphicon-plus-sign")){
		$(this).removeClass("glyphicon-plus-sign");
		$(this).addClass("glyphicon-minus-sign");
	}else{
		$(this).removeClass("glyphicon-minus-sign");
		$(this).addClass("glyphicon-plus-sign");
	}
});
$(document).on("click",".btnClassChange",function(){
	$(".btnClassChange").removeClass("btnActive");
	$(this).addClass("btnActive");
});
	function buildTotalAppointmentStatusForNew(result){
			var str='';
		   var totalApptCount =0;
			if(result!=null && result.length>0){
				for(var i in result){
					totalApptCount = totalApptCount + result[i].statusCount;
				}
			}
			str+='<table class="table table-bordered text-center b_border" style="font-size: 18px; text-transform: uppercase; color: rgb(255, 255, 255);">';
			str+='<tbody>';
			str+='<tr>';
				str+='<td colspan="7" style="background: rgba(10, 37, 63,0.5) none repeat scroll 0px 0px;">TOTAL APPOINTMENTS- '+totalApptCount+'</td>';
			str+='</tr>';
			str+='<tr></tr>';
			str+='<tr>';
			if(result !=null){
				for(var i in result){
					var color = getColorCodeByStatus(result[i].status);
					if(result[i].appointmentStatusId !=2){
						if(result[i].statusCount == 0){
						str+='<td><span style="color: '+color+'">'+result[i].status+' <br><span style="font-weight: bold; font-size: 28px ! important;">'+result[i].statusCount+'</span></span></td>';
						}else{
						 var statusArr= result[i].clickIds;
						 str+='<td class="appointmentStatusCls" attr_statusArrId ="'+statusArr+'"><span style="color: '+color+';cursor:pointer">'+result[i].status+' <br><span style="font-weight: bold; font-size: 28px ! important;">'+result[i].statusCount+'</span></span></td>';
					  }
					
					}else if(result[i].appointmentStatusId ==2){
						var statusArr= result[i].clickIds;
						 str+='<td><span style="color: '+color+';cursor:pointer">'+result[i].status+' - <span style="font-weight: bold; font-size: 28px ! important;"><span class="appointmentStatusCls" attr_status_type="totalApproved" attr_statusArrId ="'+statusArr+'">'+result[i].statusCount+'</span></span>';
						
						str+='<table style="font-size: 12px; color: rgb(51, 51, 51);" class="table table-border"><tbody>';
						str+='<tr>';
						
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
							var internalColor = getColorCodeByStatus(result[i].subList[j].status)		
								if(result[i].subList[j].statusCount == 0){
								str+='<td style="background: '+internalColor+'"> '+result[i].subList[j].status+' - '+result[i].subList[j].statusCount+'</td>';
								}else{
								var statusArr= result[i].subList[j].clickIds;
								 str+='<td class="appointmentStatusCls" attr_statusArrId ="'+statusArr+'" style="background: '+internalColor+';cursor:pointer"> '+result[i].subList[j].status+' - '+result[i].subList[j].statusCount+'</td>';	
								}					
							}						
						}
						str+='</tr>';
						str+='</tbody></table></span></td>';
					}
				}			
			}
			str+='</tr>';
		str+='</tbody>';
		str+='</table>';
			
			$("#newStatusBuildingId").html(str);
			
	}
	



$(document).on("click",".appointmentAllDetailsModel",function(e){
	
	$("#buildAppointmentAllDetailsDiv").html('');
	$("#appointmentAllDetailsDiv").modal("show");
	$("#buildAppointmentAllDetailsDiv").html('<img class="col-xs-offset-6" src="images/search.gif" style="width: 30px; height: 30px;"/>');
	
	var appointmentId = $(this).attr("attr_appt_id");
		
		var jsObj={
			appointmentId:appointmentId
		}
		
		$.ajax({
			type : 'POST',
			url : 'getTotalAppointmentDetailsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#buildAppointmentAllDetailsDiv").html('');
			buildappointmentAllDetails(result);
		});	
 })
	
	
	function emptyCheck(value){
		if(value != null && value.trim().length>0){
			return true;
		}
		return false;
	}
	
	function buildappointmentAllDetails(result){
		
		 setcolorsForStatus();
	var str='';
	
	//Header
	
	str+='<div class="modal-header" style="background:#CCCCCC;padding-bottom:0px;">';
		str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
			str+='<div class="row">';
			
			  str+='<div class="col-md-7">';
				 str+='<h4 class="modal-title text-capitalize text-success text-capitalize" ><img style="width: 30px; height: 31px;" src="dist/Appointment/img/i_icon.png"/> appointment request information</h4>';
			  str+='</div>';
			  var color = getColorCodeByStatus(result.status);
			  str+='<div class="col-md-4" style="background:url(dist/Appointment/img/apptDetailsStrip.png) no-repeat; padding:11px;margin-top:-21px">';
				  str+='<p style="margin-top: 5px;text-align: center;">Appointment Status: <span  style="color:'+color+'">'+result.status+'</span></p>';
				  if(result.statusId==3){
					  var dateTime = result.date //+"  "+result.fromTime+" to "+result.toTime;
					  str+='<p class="text-success" style="text-align:center;"><i class="glyphicon glyphicon-time"></i>'+ dateTime   +'</p>';
				  }
			  str+='</div>';
			  
		str+='</div>';
      str+='</div>';
	  
	  //body
      str+='<div class="modal-body">';
		  str+='<div class="row">';
		  
		   str+=' <div class="col-md-12">';
			  str+='<div class="row" style="background:#8BA4A9;margin-top:-15px;padding:10px">';
			  
				str+='<div class="col-md-4">';
				 str+=' <label> ID : <b>'+result.uniqueId+'</b></label>';
				str+='</div>';
				
			   str+=' <div class="col-md-4">';
			   if( emptyCheck(result.priority) ){
				 str+='  <label>Priority Type : <b>'+result.priority+'</b></label>';  
			   }
			   str+=' </div>';
			   
			   str+=' <div class="col-md-4">';
				str+='  <label>Created Date : <b>'+result.createdDate+'</b></label>';
				str+='</div>';
				
			  str+='</div>';
			str+='</div>';
			
			var requestedDates;
			if(result.appointmentScheduleVO.apptpreferableDates!= null){
				requestedDates = result.appointmentScheduleVO.apptpreferableDates;
			}else if(result.appointmentScheduleVO.dateType != null && result.appointmentScheduleVO.dateType.trim()!="" && result.appointmentScheduleVO.maxDate != null && result.appointmentScheduleVO.minDate != null){
				requestedDates = result.appointmentScheduleVO.dateType+'('+result.appointmentScheduleVO.minDate+' to '+result.appointmentScheduleVO.maxDate+')';
			}
			if(emptyCheck(requestedDates)){
				str+='<div class="col-md-12 m_top10">';
			      str+=' <label>Requested Dates : <b> '+requestedDates+'</b></label>';
		        str+=' </div>';
			}
		 
			 str+='<div class="col-md-12 m_top10">';
			// str+=' <p class="text-success"  style="font-weight: bold;">APPOINTMENT PURPOSE</p>';
			 str+='<h5 class="text-success" style="font-weight: bold;">APPOINTMENT PURPOSE<label class="pull-right" style="border-radius:20px;" data-toggle="tooltip" data-placement="top" title="Check to edit appointment purpose"><input type="checkbox" id="editCheckBoxId"/></label></h5>';
			 str+='<textarea class="form-control appntmntRsnCls" rows="2" attr_reason=\''+result.reason+'\' readonly>'+result.reason+'</textarea>';
			 str+='</div>';
			 str+='<div style="text-center;margin-left:20px" id="updateReasonStatus"></div>';
		     str+='<div class="col-md-offset-10 col-md-2 m_top10">';
			 str+='<button type="button" class="btn btn-success" style="border-radius:20px;" attr_apppointment_id='+result.appointmentId+' id="updateAppBtnReasonId" disabled>Update</button>';
			str+='</div>';
			
			str+='<div class="col-xs-12  m_top10">';
			str+='<h4 class="panel-title text-success "  style="font-weight: bold;">';
				str+='<span style=" margin-left: 15px;"">PROFILE DETAILS</span>';
				str+='<span class="col-xs-offset-4">LOCATION DETAILS</span>';
			str+='</h4>';
			
			if(result.subList !=null && result.subList.length > 0){
				
				for( var i in result.subList){
					str+='<div class="row">';
					str+='<div class="col-md-6 m_top10">';
					str+=' <div class="media">';
					
						 str+='<div class="media-left">';
							str+='<img class="media-object thumbnail" src="'+result.subList[i].imageURL+'" style="height:80px;" onerror="setDefaultImage(this);" alt="Candidate Image" >';
						 str+='</div>';
						 
						 str+='<div class="media-body">';
							  str+='<p><span>'+result.subList[i].candidateName+'</span> </p>';
							  
							  var candDesignation = result.subList[i].candDesignation;
							  var location = result.subList[i].location;
							  var candiDesignationBuild = "";

								if(candDesignation!=null && candDesignation.length>0){
									candiDesignationBuild =  candDesignation;
									if(location!=null && location.length>0){
										candiDesignationBuild = candiDesignationBuild + " - " + location ;
									}
								}
								if(emptyCheck(candiDesignationBuild)){
									str+='<span>'+candiDesignationBuild+'</span>'
								}
							  
							  if( emptyCheck(result.subList[i].mobileNo) ){
								 str+='<p>Phone : <span>'+result.subList[i].mobileNo+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].memberShipId) ){
								 str+='<p>Membership Id : <span>'+result.subList[i].memberShipId+'</span></p>'; 
							  }
							  
							  if( emptyCheck(result.subList[i].voterCardNo) ){
								  if(emptyCheck(result.subList[i].voterConstituency)){
									str+='<p>Voter Card No : <span>'+result.subList[i].voterCardNo+' ('+result.subList[i].voterConstituency +' )</span></p>';   
								  }else{
									  str+='<p>Voter Card No : <span>'+result.subList[i].voterCardNo+'</span></p>'; 
								  }
								 
							  }
						 str+='</div>';
				  str+='</div>';
				 str+='</div>';
			 
				   str+='<div class=" col-md-6 m_top10">';
					
						str+='<div class="m_top10">';
						     if( emptyCheck(result.subList[i].state) ){
								 str+='<p>State : <span>'+result.subList[i].state+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].district) ){
								 str+='<p>District : <span>'+result.subList[i].district+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].constituency) ){
								 str+='<p>Constituency : <span>'+result.subList[i].constituency+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].tehsil) ){
								 str+='<p>Mandal : <span>'+result.subList[i].tehsil+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].localElectionBody) ){
								 str+='<p>Muncipality : <span>'+result.subList[i].localElectionBody+'</span></p>'; 
							  }
							   if( emptyCheck(result.subList[i].village) ){
								 str+='<p>Village : <span>'+result.subList[i].village+'</span></p>'; 
							  }
							  if( emptyCheck(result.subList[i].ward) ){
								 str+='<p>Ward : <span>'+result.subList[i].ward+'</span></p>'; 
							  }
							
						str+='</div>';
				  str+='</div>';
				  str+='</div>';
				}
			}
			
		 str+=' </div>';
		 
		 str+='</div>';
     str+=' </div>';
     str+=' <div class="modal-footer">';
     str+=' <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>';
     str+=' </div>';
	
	    $("#buildAppointmentAllDetailsDiv").html(str);
	}
	
	$(document).on("click","#updateAppBtnReasonId",function(){
		var appointmentId=$(this).attr("attr_apppointment_id");
		var updatedReason=$(".appntmntRsnCls").val();
		
		var jsObj={
			appointmentId:appointmentId,
			updatedReason:updatedReason
		}
		 $.ajax({
			 type:'POST',
			 url :'updateAppointmentReasonAction.action',
			 dataType:'json',
			 data: {task:JSON.stringify(jsObj)}
		 }).done(function(result){
			 if(result!=null && result!=0){
				 if(result.exceptionMsg="success"){
					$("#updateReasonStatus").html("<p style='color:green'>Appointment purpose updated successfully.");  
					setTimeout(function(){$("#updateReasonStatus").html("");},2000);
					  //clear fields after updating
					  $('#editCheckBoxId').attr('checked', false);
					  $(".appntmntRsnCls").attr("readonly",true);
		              $("#updateAppBtnReasonId").attr("disabled",true);
					  $(".appntmntRsnCls").attr("attr_reason",updatedReason);
				 }else{
					 $("#updateReasonStatus").html("<p style='color:red'>Error occured ...Try again.");  
				 }
			 }
		 })
	});
	$(document).on("click","#editCheckBoxId",function(){
		 var reason=$(".appntmntRsnCls").attr("attr_reason");
		if($(this).is(':checked')){
			$(".appntmntRsnCls").removeAttr("readonly");
	        $("#updateAppBtnReasonId").removeAttr("disabled");
		}else{
		    $(".appntmntRsnCls").attr("readonly",true);
		    $("#updateAppBtnReasonId").attr("disabled",true);
		    $(".appntmntRsnCls").val(' ');
		    $(".appntmntRsnCls").val(reason);
		}
    });

	 function exportToExcel(result,statusId,statusType){
		 var str='';
		 if(result!=null && result.length>0){
			 str+='<table class="table table-bordered text-center b_border" id="ExprtTExclappntmntCnddtDtlsTblId">';
					 str+='<thead>';
					     str+='<th>Appointment Unique Id</th>';
						 str+='<th>Candidate Name</th>';
						 str+='<th>Contact No</th>';
						 str+='<th>Designation</th>';
						 str+='<th>Location</th>';
						 str+='<th>Constituency</th>';						 
						 str+='<th>Last Appointment Status</th>';
						 str+='<th>Last Visit Date</th>';
						 str+='<th>Total Appointments Requested</th>';
						 str+='<th>Total Completed Appointments</th>';
					 str+='</thead>'; 
					str+='<tbody>';
			 for(var i in result){
				 var candidateList=result[i].subList;
				 if(candidateList!=null && candidateList.length>0){
					 for(var i in candidateList){
					 str+='<tr>';//AppointmentUniqueId
					   if(candidateList[i].appointmentUniqueId!=null && candidateList[i].appointmentUniqueId.length>0){
						str+='<td>'+candidateList[i].appointmentUniqueId+'</td>';
						}else{
							str+='<td> - </td>';
						}
						  if(candidateList[i].name!=null && candidateList[i].name.length>0){
								str+='<td>'+candidateList[i].name+'</td>';
						  }else{
							  str+='<td>-</td>';
						  }
						 if(candidateList[i].mobileNo!=null && candidateList[i].mobileNo.length>0){
								 str+='<td>'+candidateList[i].mobileNo+'</td>';
						}else{
								 str+='<td>-</td>';
						}
						 if(candidateList[i].designation!=null && candidateList[i].designation.length>0){
							  str+='<td>'+candidateList[i].designation+'</td>';
						 }else{
							  str+='<td>-</td>';
						 }
						 if(candidateList[i].apptCandiTypeId!=null && candidateList[i].apptCandiTypeId==1){
						  if(candidateList[i].constituency!=null && candidateList[i].constituency.length>0){
							  str+='<td>'+candidateList[i].constituency+'</td>';
						   }else{
							  str+='<td>-</td>';
						   }
						 }else{
							  str+='<td>-</td>';
						 }
						 
						 if(candidateList[i].addressConstituency!=null && candidateList[i].addressConstituency.length>0){
							  str+='<td>'+candidateList[i].addressConstituency+'</td>';
						 }else{
							  str+='<td>-</td>';
						 }					    
						 if(candidateList[i].candidateLastUpdatedStatus!=null && candidateList[i].candidateLastUpdatedStatus.length>0){
							   str+='<td>'+candidateList[i].candidateLastUpdatedStatus+'</td>';
						 }else{
							   str+='<td>-</td>';
						 } 
						if(candidateList[i].candidateLastVisitDate!=null && candidateList[i].candidateLastVisitDate.length>0){
							  str+='<td>'+candidateList[i].candidateLastVisitDate+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(candidateList[i].totalRequestedAppCount!=null && candidateList[i].totalRequestedAppCount>0){
							 str+='<td>'+candidateList[i].totalRequestedAppCount+'</td>';
						}else{
							 str+='<td>-</td>';
						}
						if(candidateList[i].totalCompletedAppCount!=null && candidateList[i].totalCompletedAppCount>0){
							  str+='<td>'+candidateList[i].totalCompletedAppCount+'</td>';
						}else{
							  str+='<td>-</td>';
						}
					str+='</tr>';
					}
				
				 }
			 }
			  str+='</tbody>';
			str+='</table>';
		 }
		 $("#appntmntCnddtDtlsTblId").html(str);
	 }	 


 $(document).on('click', '.exportToExcelCls', function(){
   generateAppntmntCnddtDtlsToExcel();
 });
  function generateAppntmntCnddtDtlsToExcel()
  {	
		tableToExcel('ExprtTExclappntmntCnddtDtlsTblId', 'Appointment Candidates Report');
  }
</script>

</body>
</html>