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
<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
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
						<li role="presentation"  class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><img src="dist/Appointment/img/dashboard.png">Dashboard</a></li>
						<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="createAppReqCls"><img src="dist/Appointment/img/createappointment.png">Create Appointment Request</a></li>
						<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab" class="MngeAppntmntCls"><img src="dist/Appointment/img/manageappointments.png">Manage APpointments</a></li>
						<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab"><img src="dist/Appointment/img/confirmappointments.png">Confirm APpointments</a></li>
					  </ul>
					  <!-- Tab panes -->
					  <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<table class="table table-bordered bg_ff m_top10">
										<tr>
											<td>
												<h4 class="panel-title">TODAY APPOINTMENTS</h4>
												<table class="table table-condensed tableAppointment" id="todayAppointmentsId">
													
												</table>
											</td>
											<td>
												<table class="table">
													<tr>
														<td>
															<h4>TOTAL APPOINTMENTS</h4>
															<ul class="columnChartUl" id="totalAppointmentsId">
																
															</ul>
														</td>
														<td>
															<div id="LineChart" style="width:500px;height:200px;"></div>
														</td>
													</tr>
												</table>
                                            	
                                            </td>
										</tr>
									</table>
								</div>
							</div>
							<div class="row m_top10">
								<div class="col-md-12">
									<div class="todayBlock ">
										<div class="row m_top10">
											<div class="col-md-4">
												<label>Search</label>
												<div class="input-group inputSearch">
													<input class="form-control" type="text" id="searchStrId">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-search"></i>
													</span>
												</div>
											</div>
											<div class="col-md-3">
                                            	<label>Appointment Created By</label>
                                                <select class="dropkickClass" id="appointmentcreatedBy">
                                                	<option value="0">All</option>
                                                </select>
                                            </div>
											<div class="col-md-3">
                                            	<label>Select Date</label>
                                                <div class="input-group inputSearch">
                                                	<span class="input-group-addon">
                                                    	<i class="glyphicon glyphicon-calendar"></i>
                                                        <span class="caret"></span>
                                                    </span>
                                                    <input type="text" class="form-control" id="dashboardSelectDateIds">                                               
												</div>
                                            </div>
											<div  class="col-md-2 m_top25" style="float: right;">
                                            	<button id="" class="btn btn-success btn-block showTimeSlotsCls" onclick="getSearchDetails();">VIEW</button>
                                            </div>
										</div>
									</div>
									
										
								</div>
							</div>
                            <div class="row">
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
                                    	<h4 class="text-success">SEARCH MEMBER TO CREATE APPOINTMENT REQUESTED</h4>
                                        <div class="row">
                                        	<div class="col-md-4 pad_right0">
                                            	<label>Select Location</label>
                                                <input type="text" class="form-control"/>
                                                <ul class="searchOptions">
                                                	<li></li>
                                                </ul>
                                            </div>
											<div class="col-md-2">
												<label>Search Type</label>
                                                <select class="dropkickClass"  id="searchTypeId">
													<option value="0">Select Search Type</option>
													<option value="mobileno">MobileNo</option>
													<option value="mebershipno">MembershipNo</option>
													<option value="votercardno">VoterIdCardNo</option>
												</select>
											</div>
                                            <div class="col-md-4 pad_0">
                                            	<label>Search By Membership No/ Phone No/ Voter ID<span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" id="searchValueId">
												<span id="errDigitsId" class="full-right" style="color:red;"></span>
                                            </div>
                                            <div class="col-md-2 pad_left0">
                                            	<button class="btn btn-block btn-success m_top25 getDetailsBySrch">SEARCH MEMBER</button>
                                            </div>
												<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
                                        </div>
                                        <div class="row m_top25">
                                        	<div id="apptmemberDetailsDiv"></div>
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
													<label>Appointment Priority Type</label>
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
														<input type="radio" class="dateRadioCls" name="dateTypeRadio" value="nextWeek">Next Week
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
														<input type="text" class="form-control multiDateCls" id="multiDate" name="appointmentVO.appointmentDates">
													</div>
													<div class="errorSpdCls validateClr"></div>
												</div>
												<div class="col-md-6 m_top10">
													
													
												</div>
												<div class="col-md-12 m_top10">
													<label>Appointment Reason</label>
													<textarea class="form-control" id="appointmentReasonId" name="appointmentVO.reason"></textarea>
													<div class="errorArCls validateClr"></div>
												</div>
												
												
											</div>
											
										</div>
										<div style="margin-top: 50px;"><img id="checkboxMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
										<div class="errorCandidateMainDivCls validateClr"></div>
										<div id="showapptDetails">
											<!--<div class="block" >
												<div class="row">
													<div class="col-md-4 m_top10">
														<label>Name</label>
														<input type="text" class="form-control" id="populateCandidateName" name="appointmentVO.basicInfoList[0].name">
														</div>
													<div class="col-md-4 m_top10">
														<label>Designation</label>
														<select  name="appointmentVO.basicInfoList[0].designationId"  class=" " id="designationListId">
															<option value="0">Select Designation</option>
														</select>
													</div>
													<div class="col-md-4 m_top10">
														<label>Contact Number</label>
														<input type="text" class="form-control" id="populateMobileNo" name="appointmentVO.basicInfoList[0].mobileNo">
													</div>
												</div>
												<div class="row">
													<div class="col-md-4 m_top10">
														<label>Voter ID</label>
														<input type="text" class="form-control" name="appointmentVO.basicInfoList[0].voterCardNo">
													</div>
													<div class="col-md-4 m_top10">
														<label>Membership Number</label>
														<input type="text" class="form-control" name="appointmentVO.basicInfoList[0].membershipNum">
													</div>
													<div class="col-md-4 m_top10">
														<label>Location Scope</label>
														<select name="appointmentVO.basicInfoList[0].locationScopeId" attr_val="0" class="regionScopeCls dropkickClass" id="locationScopeSelId0" onChange="showhideLocationBoxes(0);">
															<option value="0">Select Scope</option>
															<option value="3">DISTRICT</option>
															<option value="4">CONSTITUENCY</option>
															<option value="5">MANDAL</option>
															<option value="6">VILLAGE</option>
															<option value="7">MUNICIPAL-CORP-GMC</option>
															<option value="8">WARD</option>
														</select>
													</div>
												</div>
												<div class="row m_top10">
													<div class="col-md-4 locationCls0" id="districtId0DivId" style="display:none;">
														<label>Select District</label>
														<select name="appointmentVO.basicInfoList[0].districtId" class="dropkickClass scopeClearAllCls0" id="districtId0" onChange="getConstituencies(0);">
															<option value="0">Select District</option>
														</select>
													</div>
													<div class="col-md-4 locationCls0" id="constituencyId0DivId" style="display:none;">
														<label>Select Constituency</label>
														<select name="appointmentVO.basicInfoList[0].constituencyId" class="dropkickClass scopeClearAllCls0" id="constituencyId0" onChange="getMandamMuncipalties(0);">
															<option value="0">Select Constituency</option>
														</select>
													</div>
													<div class="col-md-4 locationCls0" id="tehsilId0DivId" style="display:none;">
														<label>Select Mandal/Muncipality</label>
														<select name="appointmentVO.basicInfoList[0].tehsilId" class="dropkickClass scopeClearAllCls0" id="tehsilId0" onChange="getVillageWard(0);">
															<option value="0">Select Mandal</option>
														</select>
													</div>
													<div class="col-md-4 locationCls0" id="villageId0DivId" style="display:none;">
														<label>Select Village/Ward</label>
														<select name="appointmentVO.basicInfoList[0].villageId" class="dropkickClass scopeClearAllCls0" id="villageId0">
															<option value="0">Select VILLAGE</option>
														</select>
													</div>
													
												</div>
											</div>-->
											<div id="moreCandidatesDivId"></div>
												<div class="row m_top10">
													<div class="col-md-4 col-md-offset-8">
														<p style="cursor:pointer;float:right" id="addOneBlock">
															Add One More Candidate
															<i class="glyphicon glyphicon-plus-sign text-success"></i>
														</p>
													</div>
												</div>
										</div>
										<div class="row">
											<div class="col-md-6 m_top25">
												<button class="btn btn-success btn-block" type="button" onClick="savingAppointment();">CREATE APPOINTMENT</button>
											</div>
											<div class="col-md-6 m_top25" id="savingStatusDivId"></div>
										</div>
										<input type="hidden" id="dateTypeText" name="appointmentVO.appointmentPreferableTimeType">
										<input type="hidden" id="uniqueCode" name="appointmentVO.uniqueCode">
									</form>
									
									<div class="block cloneBlock addattrid" style="display:none;">
										<input type="hidden" class="cloneCandidateIdCls"/>
										<div class="row">
											<span class="closeIcon"><i class="glyphicon glyphicon-remove"></i></span>
											<div class="col-md-4 m_top10">
												<label>Name</label>
												<input type="text" class="form-control cloneNameCls">
												<div class="cloneErrCandidateNameCls validateClr"></div>
											</div>
											<div class="col-md-4 m_top10">
												<label>Designation</label>
												<select class="cloneDesignationCls">
													<option value="0">Select Designation</option>
													
												</select>
												<div class="cloneErrCandidateDesgCls validateClr"></div>
											</div>
											<div class="col-md-4 m_top10">
												<label>Contact Number</label>
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
												<label>Location Scope</label>
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
												<label>Select District</label>
												<select class="cloneDistrictCls ">
													<option value="0">select dist</option>
													<!--<option value="14">test dist1</option>-->
												</select>
												<div class="cloneErrCandidateDistrictCls validateClr"></div>
											</div>
											<div class="col-md-4 cloneConstDivCls" style="display:none;">
												<label>Select Constituency</label>
												<select class="cloneConstituencyCls " >
													<option value="0">select const</option>
													<!--<option value="142">test const1</option>-->
												</select>
												<div class="cloneErrCandidateConstCls validateClr"></div>
											</div>
											<div class="col-md-4 cloneMandalDivCLs" style="display:none;">
													<label>Select Mandal/Muncilpality</label>
													<select class="cloneMandalCls ">
														<option value="0">Select Mandal</option>
													</select>
													<div class="cloneErrCandidateMandalCls validateClr"></div>
												</div>
												<div class="col-md-4 cloneVillageDivCls" style="display:none;">
													<label>Select Village/Ward</label>
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
                                                	<button class="btn btn-success btn-block"  data-toggle="modal" data-target=".bs-example-modal-sm">CREATE APPOINTMENT LABEL</button>
                                                </div>
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
								<!--Swadhin-->
								<div class="panel panel-default m_top10 panelWhite" id="appointmentReqBlock">
									<div class="panel-heading b_bottom0">
										<h4 class="text-success">ALL OPPOINTMENT REQUEST MEMBER
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
                                	<div class="block">
                                    	<div class="row">
										
										<div class="col-md-3">
											<label>From Date</label>
											<div class="input-group inputSearch">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-calendar"></i>
													<span class="caret"></span>
												</span>
												<input type="text" class="form-control" id="addMembersFromDateId">
											</div>
										</div>
										
										<div class="col-md-3">
											<label>To Date</label>
											<div class="input-group inputSearch">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-calendar"></i>
													<span class="caret"></span>
												</span>
												<input type="text" class="form-control" id="addMembersToDateId">
											</div>
										</div>
										
                                            <div class="col-md-3">
                                            	<label>Designation<span style='color:red'> &nbsp * </span><span style='color:red' id="appDesigErrId"></span></label>
                                                <select class="designationListCls errClearCls" id="manageAppDesigId"></select>
                                            </div>
											
                                            <div class="col-md-3">
                                            	<label>Appointment Priority Type<span style='color:red'> &nbsp * </span><span style='color:red' id="appPrrtyErrTypId"></span></label>
                                                <select class="manageAppTypeCls errClearCls" id="manageAppTypeId"></select>
                                            </div>
                                            <div class="col-md-3">
                                            	<label>Appointment Status<span style='color:red'> &nbsp * </span><span style='color:red' id="appStatusErrId"></span></label>
                                                <select class="manageAppStatusCls errClearCls" id="manageAppStatusId"></select>
                                            </div>
                                          <div class="col-md-3 m_top10">
                                            	<label>Select District<span style='color:red' >&nbsp *</span><span style='color:red' id="appDistErrId"></span></label>
                                                <select id="manageAppDistId" class="dropkickClass">
                                                	<option value="0">Select District</option>
                                                </select>
                                          </div>
                                          <div class="col-md-3 m_top10">
                                            	<label>Select Constituency<span style='color:red'>&nbsp *</span><span style='color:red' id="appConstErrId"></span></label>
                                                <select id="manageAppConstId" class="dropkickClass">
                                                	<option value="0">Constituency Name</option>
                                                </select>
                                          </div>
                                          <!--<div class="col-md-12 m_top10">
                                            	<label class="checkbox-inline">
                                                	<input type="checkbox" class="checkboxCls" id="mnthChckbxId" value="currentMonth">This Month(Any Date)
                                                </label>
                                                <label class="checkbox-inline">
                                                	<input type="checkbox" class="checkboxCls " id="anyDtChckbxId" value="anyDate">Any Date
                                                </label><span style='color:red' id="checkBoxErrId">
												
                                          </div>-->
                                          <div class="col-md-2">
                                            	<button class="btn btn-success btn-block" style="margin-top: 33px;" id="searchAppointmentdetailsId" >VIEW</button>
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
                            <div class="row appointmentsViewDivCls commonDivCls" style="display:none;">
                            	<!--<div class="col-md-12">
                                	<div class="block">
                                    	<h4 class="text-success">VIEWING (FEB-27_28 APPOINTMENT-REQUEST) LABEL</h4>
										<div id="appointmentManage"></div>
										<ul class="viewAppointmentRequestedMembers">
											<li>
												<div class="row">
													<div class="col-md-12">
														<span class="requestedCheckbox text-success">
															Appointment Fixed
														</span>
													</div>
													<div class="col-md-6">
														<div class="media">
															<div class="media-left">
																<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
																<span class="colorStatus green"></span>
															</div>
															<div class="media-body">
																<p>Laxman - Cadre</p>
																<p>Contact Number: 9848012345</p>
																<p>Designation: MLC</p>
																<p>Constituency</p>
																<p>Last Visit:</p>
																<p>Appt Type</p>
																<p>Subject</p>
															</div>
														</div>
														<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>
														<table class="table table-bordered">
															<tr>
																<td><h4>20</h4><p>Requests</p></td>
																<td><h4>20</h4><p>Confirmed</p></td>
																<td><h4>20</h4><p>Rescheduled</p></td>
																<td><h4>20</h4><p>Cancelled</p></td>
															</tr>
														</table>
														<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>
														<p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>
													</div>
													<div class="col-md-6">
														<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>
														<table class="table table-bordered m_top10">
															<thead>
														  <th>Appt Last Requested Date</th>
																<th colspan="2">Appt Status</th>
															</thead>
															<tr>
																<td>28-feb-2016</td>
																<td>Fixed</td>
																<td>Appt Fixed on 28-feb-2016 10:30AM</td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
														</table>
													</div>
												</div>
											</li>
											<li>
												<div class="row">
													<div class="col-md-12">
														<span class="requestedCheckbox text-danger">
															Cancelled
														</span>
													</div>
													<div class="col-md-6">
														<div class="media">
															<div class="media-left">
																<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
																<span class="colorStatus blue"></span>
															</div>
															<div class="media-body">
																<p>Laxman - Cadre</p>
																<p>Contact Number: 9848012345</p>
																<p>Designation: MLC</p>
																<p>Constituency</p>
																<p>Last Visit:</p>
																<p>Appt Type</p>
																<p>Subject</p>
															</div>
														</div>
														<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>
														<table class="table table-bordered">
															<tr>
																<td><h4>20</h4><p>Requests</p></td>
																<td><h4>20</h4><p>Confirmed</p></td>
																<td><h4>20</h4><p>Rescheduled</p></td>
																<td><h4>20</h4><p>Cancelled</p></td>
															</tr>
														</table>
														<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>
														<p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>
													</div>
													<div class="col-md-6">
														<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>
														<table class="table table-bordered m_top10">
															<thead>
														  <th>Appt Last Requested Date</th>
																<th colspan="2">Appt Status</th>
															</thead>
															<tr>
																<td>28-feb-2016</td>
																<td>Fixed</td>
																<td>Appt Fixed on 28-feb-2016 10:30AM</td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
														</table>
													</div>
												</div>
											</li>
											<li>
												<div class="row">
													<div class="col-md-12">
														<span class="requestedCheckbox text-danger">
															Cancelled
													  </span>
													</div>
													<div class="col-md-6">
														<div class="media">
															<div class="media-left">
																<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
																<span class="colorStatus violet"></span>
															</div>
															<div class="media-body">
																<p>Laxman - Cadre</p>
																<p>Contact Number: 9848012345</p>
																<p>Designation: MLC</p>
																<p>Constituency</p>
																<p>Last Visit:</p>
																<p>Appt Type</p>
																<p>Subject</p>
															</div>
														</div>
														<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>
														<table class="table table-bordered">
															<tr>
																<td><h4>20</h4><p>Requests</p></td>
																<td><h4>20</h4><p>Confirmed</p></td>
																<td><h4>20</h4><p>Rescheduled</p></td>
																<td><h4>20</h4><p>Cancelled</p></td>
															</tr>
														</table>
														<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>
														<p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>
													</div>
													<div class="col-md-6">
														<h4>PREVIOUS APPOINTMENT REQUEST DETAILS</h4>
														<table class="table table-bordered m_top10">
															<thead>
														  <th>Appt Last Requested Date</th>
																<th colspan="2">Appt Status</th>
															</thead>
															<tr>
																<td>28-feb-2016</td>
																<td>Fixed</td>
																<td>Appt Fixed on 28-feb-2016 10:30AM</td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
															<tr>
																<td>8-feb-2016</td>
																<td>Waiting</td>
																<td></td>
															</tr>
														</table>
													</div>
												</div>
											</li>
									  </ul>
                                    </div>
                                </div>-->
                            </div>
                            <div class="row appointmentsUpdateDivCls commonDivCls" style="display:none;">
                            	<div class="col-md-12">
                                	<div class="block">
                                    	<h4 class="text-success" style="margin-bottom:10px;">UPDATE LABEL MEMBER ( <span id="updateLabelNameSpanId"></span> )</h4>
										<div id="updateAppointmentsForLabelDivId"></div>
                                     </div>
                                </div>
                            </div>
						</div>
						<div role="tabpanel" class="tab-pane" id="settings">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
                                	<div class="block">
                                    	<div class="row">
                                        	<div class="col-md-6">
                                            	<label>Select Appointment Label</label>
												<div id="timeSlotsErrId" class="text-danger"></div>
                                                <select class="dropkickClass" id="appointmentLabelToGetSlotsId">
                                                	<option>Feb-28_29-Appointments</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                            	<button class="btn btn-success btn-block showTimeSlotsCls m_top24" id="showTimeSlotsId">VIEW</button>
                                            </div>
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
											<button class="btn btn-success pull-right">VIEW BOOKED TIME SLOTS</button>
										</h4>
										<div class="row">
											<div class="col-md-12">
												<div class="row">
													<div class="col-md-12">
														<div id="timeSlotsWarnId"></div>
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
																	<label>From Time</label>
																	<div class="input-group inputSearch">
																		<span class="input-group-addon">
																			<i class="glyphicon glyphicon-time"></i>
																			<span class="caret"></span>
																		</span>
																		<input type="text" id="fromTimeId" class="form-control">
																	</div>
																</div>
																<div class="col-md-3">
																	<label>To Time</label>
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
																</div>
															</div>
														</div>
														
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12" id="appointmentMembersId"></div>
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
<script src="dist/2016DashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/js/AppointmentScreenBootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
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
        evt.item.parentNode.removeChild(evt.item);
		if($("#confirmAppointmentBlockDropId").has( ".manageAppViewPanelClass" ))
		{
			$("#confirmAppointmentBlockDropId").append("<h4 class='deleteTag'>DROP HERE</h4>")
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
		$("#confirmAppointmentBlockDropId").find(".deleteTag").remove();
		$("#confirmAppointmentBlockDropId").css("height","");
	  },
      onUpdate: function (evt){ console.log('onUpdate.editable:', [evt.item, evt.from]);},
      onRemove: function (evt){ console.log('onRemove.editable:', [evt.item, evt.from]); },
      onStart:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
      onSort:function(evt){ console.log('onStart.editable:', [evt.item, evt.from]);},
      onEnd: function(evt){ console.log('onEnd.editable:', [evt.item, evt.from]);}
  });
  
/* Drag and Drop END */
var jsonObj = [];
var color = ["#2095F1","#4BAF4F","#3F51B5","#00BBD4","#A86FC5","#FE9601"];
var flag = false;
function buildJSONForAppStatus(result){
	for(var i in result.overAllStatusList){
		if(result.overAllStatusList[i].totalCount>0)
			flag = true;
		jsonObj.push({"name":result.overAllStatusList[i].status,"y":result.overAllStatusList[i].totalCount,"color":color[i%6]});
	}
	if(flag==true)  
	buildChartForAppStatus();
}

getTotalAppointmentStatus();
function getTotalAppointmentStatus(){
	$.ajax({
		type : 'GET',
		url : 'getStatusWiseCountsOfAppointmentsAction.action',
		dataType : 'json',
		data : {}  
	}).done(function(result){ 
		if(result != null){
			buildTotalAppointmentStatusForToday(result);
			buildJSONForAppStatus(result);
			buildTotalAppointmentStatus(result);
		}
		
	});     
}
function buildTotalAppointmentStatus(result){
	for(var i in result.overAllStatusList){
	$("#totalAppointmentsId").append('<li style="color:'+color[i%6]+'"><span class="columnChart" style="background:'+color[i%6]+'"></span>'+result.overAllStatusList[i].status+' - '+result.overAllStatusList[i].totalCount+'</li>');
	}
}

function buildTotalAppointmentStatusForToday(result){
	$.each(result.statusList,function(index,value){
	$("#todayAppointmentsId").append('<tr style="color:'+color[index%6]+'"> <td>'+value.status+'</td><td>'+value.totalCount+'</td> </tr>');
	});
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
function buildChartForAppStatus() {
    // Create the chart
    $('#LineChart').highcharts({
        chart: {
            type: 'column',
			backgroundColor: 'transparent' 
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ' '
        },
        xAxis: {
			title: {
                text: ' ',
            },
            type: 'category',
			lineWidth: 1,
		   minorGridLineWidth: 0,
		   lineColor: '#ddd',
					
		   labels: {
			   enabled: true
		   },
		   minorTickLength: 0,
		   tickLength: 0
        },
        yAxis: {
            title: {
                text: ' ',
            },
			
			lineWidth: 0,
		   minorGridLineWidth: 0,
		   lineColor: 'transparent',
					
		   labels: {
			   enabled: true
		   },
		   minorTickLength: 0,
		   tickLength: 0

        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: false,
                    format: '{point.y:.1f}%'
                }
            },
			
        },
		legend: {
			enabled:false
		},
        series: [{
            colorByPoint: true,
            data: jsonObj
        }],
    });
}
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
	
	e.removeClass("cloneBlock");
	$("#moreCandidatesDivId").append(e);
	
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
	var Uncheckpop = $(this).attr("attr_close");
	$("#"+Uncheckpop).attr('checked', false);
});
$(".dropkickClass").dropkick();
	//swadin functions
	$(document).ready(function(){
		
		
	});
	getCandidateDesignation();
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
			
			$("#manageAppDesigId  option").remove();
			$("#manageAppDesigId").append('<option value="0">Select Designation</option>');
			for(var i in result){
				$("#designationListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$("#manageAppDesigId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				$(".cloneDesignationCls").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			 /* $(".designationListCls").dropkick();
			 var select = new Dropkick("#designationListId");
			select.refresh(); */ 
			$("#manageAppDesigId").dropkick();
			var select1 = new Dropkick("#manageAppDesigId");
			select1.refresh(); 
			
			
			
	} 
	
	$(document).ready(function(){
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
	});
	function buildAppointmentStatusList(result){
			$("#manageAppStatusId  option").remove();
			$("#manageAppStatusId").append('<option value="0">Select Appointment Status</option>');
			for(var i in result){
				$("#manageAppStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$(".manageAppStatusCls").dropkick();
			var select = new Dropkick("#manageAppStatusId");
			select.refresh();
	}
	$(document).ready(function(){
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
	});
	function buildAppointmentPriorityList(result){
		$("#manageAppTypeId  option").remove();
		$("#manageAppTypeId").append('<option value="0">Select Priority</option>');
		$("#createAppTypeListId  option").remove();
		$("#createAppTypeListId").append('<option value="0">Select Appointment Type</option>');
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
			$("#dateTypeText").val($('input[name=dateTypeRadio]:checked').val());
			var temp = $("#appointmentUserSelectBoxId option:selected").attr("attr_unique_code")+"_"+$("#appointmentUserSelectBoxId").val();
			$("#uniqueCode").val(temp);
			
			var uploadHandler = {
				upload: function(o) {
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
			$("#savingStatusDivId").html("<sapn style='font-size:18px;color:green;'>Appointment Created Successfully.</span>");
			$( ".closeIcon" ).trigger( "click" );
			saveFieldsEmpty();
		}else{
			$("#savingStatusDivId").html("<sapn style='font-size:16px;color:red;'>Appointment Creation Failed. Please Try Again.</span>");
			
		}
			
	}
	
	function saveFieldsEmpty(){
		$("#createAppTypeListId").val(0);
		$("#multiDate").html('');
		$("#appointmentReasonId").html('');		
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
	
	getDistricts();
	//var distArr=[];
	function getDistricts(){
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForAppointmentsAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){
			var str='';
			str+='<option value="0">Select District</option>';
			if(result != null && result.length > 0){
				for(var i in result){
					// var obj={id:result[i].id,value:result[i].name};
					 //distArr.push(obj);
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';	
				}
			}
			$("#manageAppDistId").html(str);
			$("#manageAppDistId").dropkick();
			var select1 = new Dropkick("#manageAppDistId");
			select1.refresh();
			$(".cloneDistrictCls").html(str);
			
			
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
				str+='<option value="0">Select Constituency</option>';
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
		var jsObj ={
					constId:constId
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
	
		var jobj = {
			labelName	:	$("#labelNameId").val(),
			fromDate	:	fromDate,
			toDate		:	toDate		
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
				}
		  });     
	});
		 getAppointmentUsersDtls();
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
		getLabelDtls("all");
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
		getLabelDtls("all");
	});
	
	/*Get label details based on selected date.*/
	$(document).on("change","#mngAppntmntsDtPckrId",function(){
	   getLabelDtls("Change");
	});
	function getLabelDtls(callType){
		
		var slctDate="";
		/* if(callType=="all"){
			slctDate="";
		}else if(callType=="change"){
			slctDate=$("#mngAppntmntsDtPckrId").val();
		} */
		
		var appntmntUsrId=$("#appointmentUserSelectBoxId").val();
		
		var jsObj={
			currentDate:slctDate,
			apptmntUsrId:appntmntUsrId
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
				 str+='<th>APPOINTMENT LABEL NAME</th>';
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
						str+='<button class="btn btn-success btn-xs viewMembersClass" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'">VIEW</button>';
						str+='<button class="btn btn-success btn-xs addMembersClass">ADD MEMBERS</button>';
						str+='<button class="btn btn-success btn-xs updateLableAppointmentsCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'">UPDATE</button>';
						str+='<button class="btn btn-success btn-xs labelStatusCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'" attr_status="'+result[i].status+'" attr_status_id="'+result[i].statusId+'">STATUS</button>';
						str+='<button class="btn btn-success btn-xs lblDltCls" attr_label_name="'+result[i].labelName+'" attr_label_id="'+result[i].labelId+'">DELETE</button>';
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
$("#mngAppntmntsDtPckrId").daterangepicker({singleDatePicker:true})
$("#mngAppntmntsDtPckrId").val("");
$("#multiDate").multiDatesPicker({numberOfMonths: [1,2],minDate:0})
$("#dashboardSelectDateIds").daterangepicker({opens:"left"});
$("#appointmentDateSlotId").daterangepicker({singleDatePicker:true});
$("#addMembersFromDateId,#addMembersToDateId").daterangepicker({singleDatePicker:true});



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
						str+='<p>Subject       :'+result[i].reason+'</p>';
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
  
  
	$(document).on("click",".getDetailsBySrch",function(){
		if($("#searchTypeId").val()==0){
			 $("#errDigitsId").html("Please Select Mobile Number Or VoterCardNo or MemberShipId");
			return;
		}else if($("#searchValueId").val().trim()=="" || $("#searchValueId").val().trim()==null){
			 $("#errDigitsId").html("plase Enter Valid MobileNumber"); 
			return;
		}
		else if($("#searchTypeId").val()=="mobileno"){
			var searchValue=$("#searchValueId").val();
		      var digits=/^(\+\d{1,3}[- ]?)?\d{10}$/;
			
			if(searchValue == null || searchValue.length==0 || searchValue.length == ""){
				 $("#errDigitsId").html("Please Enter 10 Digits."); 
				return;
			
			}else if($("#searchValueId").val().trim().length<10){
				$("#errDigitsId").html("Mobile Number Should be 10 Digits");
				return;
			}else{
				getAppntmntSearchDetails();
				$(".addattrid").hide();
			}
		}
		$("#errDigitsId").html(" ");
	}); 
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
			for(var i in result){
					str+='<div class="col-md-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li>';
							str+='<div class="row">';
								str+='<div class="col-md-7">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
										str+='</div>';
										str+='<div class="media-body">';
										if(result[i].candidateType !=null && result[i].candidateType.length>0){
											str+='<p >'+result[i].name+' - '+result[i].candidateType+'</p>';
										}else{
											str+='<p>'+result[i].name+'</p>';
										}
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p >Contact Number: '+result[i].mobileNo+'</p>';
										}else{
											str+='<p>Contact Number: - </p>';
										}
										if(result[i].designation !=null && result[i].designation.length>0){
												str+='<p >Designation: '+result[i].designation+'</p>';
										}else{
											str+='<p>Designation: - </p>';
										}
										if(result[i].constituency !=null && result[i].constituency.length>0){
												str+='<p>Constituency: '+result[i].constituency+'</p>';
										}else{
											str+='<p>Constituency: - </p>';
										}
										   str+='<p>Recent Appt History: 20-feb-2016</p>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							   str+='<div class="col-md-2">';
									str+='<p class="m_top10"><a href="#" class="text-success">View Appt History</a></p>';
								str+='</div>';
								str+='<div class="col-md-2">';
									str+='<p class="m_top10"><a href="#" class="text-success">View/Edit Profile</a></p>';
								str+='</div>';
								
								str+='<div class="col-md-1 m_top10" attr_id='+result[i].id+'   attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile='+result[i].mobileNo+' attr_desg="'+result[i].designation+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'">';
									str+='<input type="checkbox" class="apptDetailsDiv" id="block'+result[i].id+'" attr_id=block'+result[i].id+'>';
								str+='</div>';
								
							str+='</div>';
						str+='</li>';
					 
					str+='</ul>';
				str+='</div>';
			}
		}
		
		$("#apptmemberDetailsDiv").html(str);
	}
	
	 $(document).on("click",".apptDetailsDiv",function(){
		 
		 
		 if($(this).is(':checked')){
			 $("#checkboxMemberAjax").css("display","block");
				$(this).attr("clone_block_count",cloneCount);
				var Uncheck = $(this).attr("attr_id");
				$(".closeIcon").attr("attr_close",Uncheck);
				
				$("#addOneBlock").trigger("click");
				var temp = cloneCount-1;
				$("#candidateId"+temp).val($(this).parent().attr("attr_id"));
			 var candidateType = $(this).parent().attr("attr_candidatetype");
			 var id = $(this).parent().attr("attr_id");
			 
			 var name = $(this).parent().attr("attr_name");
			 var mobile = $(this).parent().attr("attr_mobile");
			 var desg = $(this).parent().attr("attr_desg");
			 var membershipno = $(this).parent().attr("attr_memberShipNo");
			 var votercardno = $(this).parent().attr("attr_voterCardNo");
			 
			 var closeId1 = $(this).attr("attr_id");
		
		
		var jsObj={
			candidateType:candidateType,
			id:id
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getCandidateWiseDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 $("#checkboxMemberAjax").css("display","none");
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
					$('#designationSelId'+temp).val(desg);
					$('#voterCardNoID'+temp).val(votercardno);
					$('#membershipNumId'+temp).val(membershipno);
					
					$('#locationScopeSelId'+temp).val(lctscpid);
					$('#districtId'+temp).val(distId);
					$('#constituencyId'+temp).val(consId);
					$('#tehsilId'+temp).val(tehsilId);
					$('#villageId'+temp).val(villageId);
					
					var select = new Dropkick('#locationScopeSelId'+temp);
						select.refresh();
					var select2 = new Dropkick('#districtId'+temp);
						select2.refresh();
					showhideLocationBoxes(temp);
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
	  getAppointmentsBySearchCriteria();
  });
  function clearAppointmentsSearchFields(){
	  $("#appDesigErrId,#appPrrtyErrTypId,#appStatusErrId,#appDistErrId,#appConstErrId").html('');  
  }
  function getAppointmentsBySearchCriteria(){
		  
		  clearAppointmentsSearchFields();
		  $("#appointmentRequestedMembersId").html('');  
		  
		 var fromDate =  $("#addMembersFromDateId").val();
		 var toDate =    $("#addMembersToDateId").val();
		 
		 var designationId=$("#manageAppDesigId").val();
		 var priorityId= $("#manageAppTypeId").val();
		 var statusId=$("#manageAppStatusId").val();
		 var districtId = $("#manageAppDistId").val();
		 var constituencyId = $("#manageAppConstId").val();
		 
		 if(designationId==0){
		  $("#appDesigErrId").html("Select Designation.");
           return;		  
		 }
		  
		 if(priorityId==0){
		  $("#appPrrtyErrTypId").html("Select Priority Type.");
           return;		  
		 }
		 
		 if(statusId==0){
		  $("#appStatusErrId").html("Select Appointment Status.");
           return;		  
		 }
		 if(districtId==0){
		  $("#appDistErrId").html("Select District.");
           return;		  
		 }
		 if(constituencyId==0){
		  $("#appConstErrId").html("Select Constituency.");
           return;		  
		 }
    	var jsObj={
			designationId:designationId,
			priorityId:priorityId,
			statusId:statusId,
			districtId:districtId,
			constituencyid:constituencyId,
			appointmentlabelId : appointmentlabelId,
			fromDate :fromDate,
			toDate:toDate
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentsBySearchCriteriaAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#appointmentRequestedMembersId").show();
				if(result!=null && result!=0){
				  buidResult(result);
				}else{
				  $("#appointmentRequestedMembersId").html("<center><p style='color:green;font-size:20px'>No Data available.</p></center>")	
				}
		  }); 
	 }
  function buidResult(result){
		 var i = 0;
		 var str='';
		  str+='<div class="block">';
			 str+='<h4 class="text-success">APPOINTMENT REQUESTED MEMBERS</h4>';
			  str+='<center><img id="apptRqstMemberAjax" src="images/icons/loading.gif" style="display:none;"/></center>';
			 
		 for(var i in result){
			
			  str+='<div class="panel panel-default manageAppViewPanelClass">';
				str+='<div class="panel-heading">';
				        if(result[i].labeled){
							str+='<span><input class="appointmentcheckBoxClass pull-right" type="checkbox" value="'+result[i].appointmentId+'" checked></span>';
						}else{
							str+='<span><input class="appointmentcheckBoxClass pull-right" type="checkbox" value="'+result[i].appointmentId+'" ></span>';
						}
						if(result[i].subject !=null && result[i].subject.length>0){
							str+='<p>Subject : '+result[i].subject+'</p>';
						}else{
							str+='<p>Subject : - </p>';
						}if(result[i].priority !=null && result[i].priority.length>0){
							str+='<p>Priority Type : '+result[i].priority+'</p>';
						}else{
							str+='<p>Priority Type : - </p>';
						}if(result[i].dateString !=null && result[i].dateString.length>0){
							str+='<p>Requested Date : '+result[i].dateString+'</p>';
						}else{
							str+='<p>Requested Date : - </p>';
						}if(result[i].status !=null && result[i].status.length>0){
							str+='<p>Status : '+result[i].status+'</p>';
						}else{
							str+='<p>Status : - </p>';
						}
						/* if(result[i].apptpreferableDates !=null && result[i].apptpreferableDates.length>0){
							str+='<p>Requested Dates : '+result[i].apptpreferableDates+'</p>';
						}else{
							str+='<p>Requested Dates : - </p>';
						} */
					
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
                                            
                                            if(result[i].subList[j].lastVisit !=null && result[i].subList[j].lastVisit.trim().length>0){
												str+='<p>Last Visit: '+result[i].subList[j].lastVisit+'</p>';
											}else{
												str+='<p>Last Visit: - </p>';
											}
												
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
		  
		  $("#updateMemberAjax").css("display","block");
		  var appointmentsArray = [];
		  $('.appointmentcheckBoxClass').each(function(){
			  if ($(this).is(':checked')){
				  appointmentsArray.push( $(this).val() );
			  }
          });
		  
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
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Appointments Added To Label Successfully</h4></center>").fadeOut(6000);
						}, 500);
				  }
				}else{
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(6000);
						}, 500);
					
				 }
		  }); 
		  
	  }
	  
	var noOfRow = 5;
	$(document).on("click","#viewAllAppointmentId",function(){
		var startIndex = 0;
		var maxIndex = noOfRow;
		viewAllAppointment(startIndex,maxIndex);
	});
	function viewAllAppointment(startIndex,maxIndex){
		var jsObj={
				startIndex:startIndex,
				maxIndex:maxIndex
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
		str+='<th>LAST APPOINTMENT DATE</th>';
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
		if(total> noOfRow){
			$("#paginationDivId").pagination({
			items: total,
			itemsOnPage: noOfRow,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*noOfRow;
				viewAllAppointment(num,noOfRow);
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
										str+='<span class="requestedCheckboxPanel text-danger">'+result[i].status+'</span>';
									str+='<div class="col-xs-5">';
										str+='<p>Subject : '+result[i].reason+'</p>';
										str+='<p>Priority Type : '+result[i].priority+'</p>';
									str+='</div>';
									str+='<div class="col-xs-7">';
									str+='<div class="col-xs-5">';
											str+='<label>Update Status</label>';
											str+='<select class="form-control"  id="upadteAppntStatus" attr_appnt_id="'+result[i].appointmentId+'">';
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
								str+='<div class="row">';
									str+='<div class="col-md-12">';
									  str+='<p class="pull-right">Appt Created By: '+result[i].userName+' &nbsp;&nbsp;&nbsp;&nbsp; <img src="dist/Appointment/img/message.png" class="message"></p>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}
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
			labelId : $(this).attr("attr_label_id")
		}
		
		$.ajax({
			type : 'POST',
			url : 'viewAppointmentsOfALableAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".appointmentsViewDivCls").show();
			if(result!=null && result!=0){
			  buildViewResult(result,labelName);
			}else{
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><center><p style='color:green;font-size:20px'>No Data available.</p></center></div></div>");	
			}
		});		
	});
	
	function buildViewResult(result,labelName){
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
						str+='<span class="requestedCheckboxPanel text-danger">'+result[i].status+'</span>';
						str+='</div>';
					str+='</div>';
					if(result[i].subject !=null && result[i].subject.length>0){
						str+='<p>Subject : '+result[i].subject+'</p>';
					}else{
						str+='<p>Subject : - </p>';
					}if(result[i].priority !=null && result[i].priority.length>0){
						str+='<p>Priority Type : '+result[i].priority+'</p>';
					}else{
						str+='<p>Priority Type : - </p>';
					}if(result[i].dateString !=null && result[i].dateString.length>0){
						str+='<p>Requested Date : '+result[i].dateString+'</p>';
					}else{
						str+='<p>Requested Date : - </p>';
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
			task:""
			
		}
		searchJobj = jsObj;
		  	$.ajax({
				type : 'POST',
				url : 'getAppointmentSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				buildUpcomingResult(result);
				buildInprogressResult(result);
				buildCompletedResult(result);
			})
	}
	function buildUpcomingResult(result)
	{
		var str = '';
		var flag = false;
		str+='<div class="upcomingAppointments heightAdjust">';
		str+='<h4 class="text-success">UPCOMING APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings upcomingSetting">';
		str+='</h4>';
		
		str+='<div class="updateAppointment arrow_box">';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="5" name="upcomeRadio1" class="statusAllupcome" checked>Reschedule';
		str+='</label>';
		str+='<label class="radio-inline">';
		str+='<input type="radio" value="3" name="upcomeRadio1" class="statusAllupcome">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10 upcomeSmsText" ></textarea>';
		str+='<span class="msgDiv2upcome"></span>';
		str+='<button class="btn btn-block btn-success updateAll" value="upcome">UPDATE APPOINTMENT</button>';
		str+='</div>';
		if(result != null)
		{
			for(var i in result)
			{
				if(result[i].scheduleType == "UpCome")
				{
					str+='<div class="panel panel-default manageAppViewPanelClass m_top20">';
						str+='<div class="panel-heading">';
							str+='<p class="font12"><span class="pull-left text-danger">'+result[i].appointmentStatus+'</span><span class="pull-right text-success">';
							str+='<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].time+' &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
							str+='<p class="m_top20">Sub: '+result[i].subject+'</p>';
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
							str+='</div>';
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
						str+='<div class="media-body">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
						str+='<p></p>';
						str+='</div>';
						str+='</div>';
						str+='</li>';
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
	function buildInprogressResult(result)
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
		str+='<ul>';
		if(result != null)
		{
			for(var i in result)
			{
				if(result[i].scheduleType == "InProgress")
				{
					str+='<div class="panel panel-default manageAppViewPanelClass m_top20">';
						str+='<div class="panel-heading">';
							str+='<p class="font12"><span class="pull-left text-danger">'+result[i].appointmentStatus+'</span><span class="pull-right text-success">';
							str+='<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].time+' &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
							str+='<p class="m_top20">Sub: '+result[i].subject+'</p>';
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
							str+='</div>';
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
						str+='<div class="media-body">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
						str+='<p></p>';
						str+='</div>';
						str+='</div>';
						str+='</li>';
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
			str+='</ul>';
		str+='</div>';
		$("#inprogreessAppointMentId").html(str);
		if(!flag)
			$(".inprogressSetting").hide();	
		
	}
	
	
	function buildCompletedResult(result)
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
		str+='<ul>';
		if(result != null)
		{
			for(var i in result)
			{
				if(result[i].scheduleType == "Completed")
				{
					str+='<div class="panel panel-default manageAppViewPanelClass m_top20">';
						str+='<div class="panel-heading">';
							str+='<p class="font12"><span class="pull-left text-danger">'+result[i].appointmentStatus+'</span><span class="pull-right text-success">';
							str+='<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].time+' &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
							str+='<p class="m_top20">Sub: '+result[i].subject+'</p>';
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
							str+='</div>';
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
						str+='<div class="media-body">';
						str+='<p>'+result[i].subList[j].name+'</p>';
						str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
						str+='<p></p>';
						str+='</div>';
						str+='</div>';
						str+='</li>';
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
		if(flag == false)
		$(".completedSetting").hide();
	}
</script>
<script>

	//getAppointmentLabelsAction
	 getAppointmentLabels();
	function getAppointmentLabels(){
		$.ajax({
		type : 'GET',
		url : 'getAppointmentLabelsAction.action',
		dataType : 'json',
		data : {}  
		}).done(function(result){ 
		if(result!=null && result!=0){
			buildAppointmentLabel(result);
		}
		
	});     
	}
	function buildAppointmentLabel(result){
		$("#appointmentLabelToGetSlotsId  option").remove();
			$("#appointmentLabelToGetSlotsId").append('<option value="0">Select Appointment Status</option>');
			for(var i in result){
				$("#appointmentLabelToGetSlotsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#appointmentLabelToGetSlotsId").dropkick();
			var select = new Dropkick("#appointmentLabelToGetSlotsId");
			select.refresh();
	} 
	$("#pluginTableId").hide();
	$("#showTimeSlotsId").click(function(){
		
		//View Details Of Appointments call Balu
		getViewAppointmentsOfALable();
		
		
		$("#timeSlotsErrId").html("");
		//get appointments of a lable
		getAppointmentsOfALabel();
		var appointmentLabelId = $("#appointmentLabelToGetSlotsId").val();
		if(appointmentLabelId==0){
			$("#timeSlotsErrId").html("please select a label");
			return;
		}
		var jsObj = {
		appointmentLabelId:appointmentLabelId
	}
	$.ajax({
		type : 'GET',
		url : 'getTimeSlotsDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
		if(result.listOfTimePairPerDate != null && result.listOfTimePairPerDate.length!=0 ){
			$("#pluginTableId").show();
			buildTimeSlotsTable(result);
			$(".changeClass").removeClass("col-md-12")
			$(".changeClass").addClass("col-md-8")
			$("#timeSlotsWarnId").hide();
		}else{
			$("#pluginTableId").hide();
			$("#timeSlotsWarnId").show();
			$("#timeSlotsWarnId").css("color","green").html("No slot is available");
		}
		});
		var user = $("#appointmentUserSelectBoxId").text();
		//alert(user);
	});
	
	function getAppointmentsOfALabel(){
		var jsObj={
			labelId : $("#appointmentLabelToGetSlotsId").val()
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
			
				str+='<div class="panel panel-default manageAppViewPanelClass" attr_appointment_id='+result[i].appointmentId+'>';
				str+='<div class="panel-heading">';
				    str+='<div class="row">';
						str+='<div class="col-md-12">';
						str+='<span class="requestedCheckboxPanel text-danger" style="margin-right:25px">'+result[i].status+'</span>';
						str+='<span class="requestedCheckboxPanel hidelabel"><i class="glyphicon glyphicon-remove"></i></span>';
						str+='</div>';
					str+='</div>';
						
					str+='<p>Subject : '+result[i].subject+'</p>';
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
		$("#confirmAppointmentsDivId").html(str)
		Sortable.create(dragId,{
			  filter: '.js-remove',
			  onFilter: function (evt) {
				evt.item.parentNode.removeChild(evt.item);
			  },
			  setData: function (dataTransfer, dragEl) {
				dataTransfer.setData('Text', dragEl.textContent);
			  },
			  //group: "QuestionnOptions",
			  group: { name: "confirmAppointmentsBlock", put: false, pull: ['clone'] },
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
				   str+='<div class="m_top10">';
				  str+='<p id="updateStatusMsg"></p>';
				  str+='</div>';
				  str+='</div>';
				str+='</div>';
			 str+='</div>';
		 str+='</div>';
		 getAppointmentsLabelStatus();
		  $("#buildAppntmntStsTblId").html(str);
		  $("#myModalId").modal("show");
	}
	
	function getAppointmentsLabelStatus(){
		var jsObj={
		}
		$.ajax({
			type : 'POST',
			url : 'getAppointmentsLabelStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#selectStsId").append('<option value="0">Select Status</option>');
		if(result!=null && result.length>0){
			   for(var i in result){
					$("#selectStsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
		   }
		});
	}

	$(document).on("click","#updateStsBttnId",function(){
		
		var labelId = $(this).attr("attr_label_id");
		var attrlabelstatusId = $(this).attr("attr_label_status_id");
		var labelstatusId = $("#selectStsId").val();
		if(attrlabelstatusId == labelstatusId){
			alert("Both Status are Same.,Please Select Another Status");
		}else{
			updateAppointmentsLabelStatus(labelId,labelstatusId);
			
		}
		
	});
	function updateAppointmentsLabelStatus(labelId,labelstatusId){
		
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
					  setTimeout(function () {
						$("#updateStatusMsg").html("<center><h4 style='color: green;'>Status Updated Successfully</h4></center>").fadeOut(3000);
						}, 500);
						setTimeout(function () {
						$("#myModalId").modal("hide");
						}, 4000);
				  }
				}else{
					setTimeout(function () {
						$("#updateStatusMsg").html("<center><h4 style='color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(3000);
						}, 500);
					
				 }
		});
	}
	$(document).on("change","#upadteAppntStatus",function(){
		var memberAppntId = $(this).attr("attr_appnt_id");
		var updateAppntStatusId = $(this).val();
		updateMemberAppointmentsStatus(memberAppntId,updateAppntStatusId);
		$("#appointmentStatusMsg").css("display","block");
	});	
	function updateMemberAppointmentsStatus(memberAppntId,updateAppntStatusId){
		
	
		var jsObj={
				memberAppntId:memberAppntId,   
				updateAppntStatusId:updateAppntStatusId
		}
		$.ajax({
			type : 'POST',
			url : 'updateMemberAppointmentsStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null && result!=0){
				if(result.message=="success"){
					setTimeout(function () {
					$("#appointmentStatusMsg"+memberAppntId).html("<center style='color: green; font-size: 16px;'>Appointment Updated Successfully</center>").fadeOut(3000);
					}, 500); 
				}
			}else{
			setTimeout(function () {
				$("#appointmentStatusMsg"+memberAppntId).html("<center style='color: green; font-size: 16px;'>Updation Failed..Try Later</center>").fadeOut(3000);
				}, 500);
			
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
		var fromTime = $("#fromTimeId").val();
		var toTime = $("#toTimeId").val();
		
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
		});
	});
function buildTimeSlotsTable(result){
		var str='';
		str+='<tr>';
		str+='<td class="text-center">';
		str+='<i class="glyphicon glyphicon-triangle-top"></i>';
		str+='</td>';
		str+='</tr>';
		for(var i in result.listOfTimePairPerDate){
			str+='<tr>';
			
			str+='<td class="text-center">'+(((result.listOfTimePairPerDate[i])[0])[0]).substr(0,10)+'</td>';
			str+='</tr>';
		}
		str+='<tr>';
		str+='<td class="text-center">';
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
				//alert((result.listOfTimePairPerDate[i])[j]);
				var start=((result.listOfTimePairPerDate[i])[j])[0];
				var end = ((result.listOfTimePairPerDate[i])[j])[1];
				var startIdForHour=start.substr(11,2);
				var startIdForMin=start.substr(14,2);
				var startId=(startIdForHour-8)*4;
				if(startIdForMin=="00"){
					startId=startId+0;
				}
				if(startIdForMin=="15"){
					startId=startId+1;
				}
				if(startIdForMin=="30"){
					startId=startId+2;
				}
				if(startIdForMin=="45"){
					startId=startId+3;
				}
				var endIdForHour=end.substr(11,2);
				var endIdForMin=end.substr(14,2);
				var endId=(endIdForHour-8)*4;
				if(endIdForMin=="00"){
					endId=endId+0;
				}
				if(endIdForMin=="15"){
					endId=endId+1;
				}
				if(endIdForMin=="30"){
					endId=endId+2;
				}
				if(endIdForMin=="45"){
					endId=endId+3;
				}
				endId=endId-1;
				for(var start=startId;start<=endId;start++){
					$("#"+i+""+start).addClass("bookedSlots");
				}
			}
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
					  $("#cloneErrCandidateDesgId"+i).html("Please enter Designation");
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
		for(var i in result){
			str+='<div class="panel panel-default manageAppViewPanelClass m_top10">';
			str+='<div class="panel-heading">';
			str+='<i class="glyphicon glyphicon-cog settingsIconConfirm pull-right"></i>';
			str+='<span class="text-success pull-right">';
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
			/* if(toTime>12){
				to
			} */
			if(secondHour>12){
				secondHour = secondHour-12;
				secondMean = "PM";
			}else{
				secondMean = "AM";
			}
			str+=''+firstHour+' : '+firstMin+' '+firstMean +' to '+secondHour+' : '+secondMin+' '+secondMean +'';
			str+='</span>&nbsp;';
			str+='<span class="pull-right">';
			str+='<i class="glyphicon glyphicon-calendar"></i> '+result[i].fromDateStr.substr(0,10)+'</span>&nbsp;';
			
			str+='<p>Subject: '+result[i].subject+'</p>';
			str+='<p>Priority Type: '+result[i].priority+'</p>';
			str+='</div>';
			str+='<div class="panel-body pad_5">';
			str+='<ul class="confirmSearchUl" style="list-style: none;">';
			for(var j in result[i].subList){
				str+='<li>';
				str+='<div class="row">';
				str+='<div class="col-md-12">';
				str+='<div class="media">';
				str+='<div class="media-left">';
				str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
				str+='</div>';
				str+='<div class="media-body">';
				str+='<p>'+result[i].subList[j].name+' - '+result[i].subList[j].designation+'</p>';
				str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
				//str+='<p>Appointment SUB</p>';
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
		}
		$("#appointmentMembersId").html(str);
	}
</script>
</body>
</html>