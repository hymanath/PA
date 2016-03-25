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
											<div class="col-md-6">
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
										</div>
									</div>
								</div>
							</div>
                            <div class="row">
                            	<div class="col-md-4">
                                	<div class="upcomingAppointments heightAdjust" id="upcomingAppointMentId" >
                                    
                                    </div>
                                </div>
                                <div class="col-md-4 pad_0 " >
                                	<div class="inprogressAppointments heightAdjust" id="inprogreessAppointMentId">
                                    
                                    </div>
                                </div>
                                <div class="col-md-4 ">
                                	<div class="completedAppointments heightAdjust" id="completedAppointMentId">
                                    	</div>
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
                                            	<label>Search By Membership No/ Phone No/ Voter ID</label>
                                                <input type="text" class="form-control" id="searchValueId">
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
												</div>
												<div class="col-md-8 m_top10">
													<label class="radio-inline">
														<input type="radio" class="dateRadioCls" checked name="dateTypeRadio" value="multipleDates">Select Preferrable Dates
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
														<input type="text" class="form-control" id="multiDate" name="appointmentVO.appointmentDates">
													</div>
												</div>
												<div class="col-md-6 m_top10">
													
													
												</div>
												<div class="col-md-12 m_top10">
													<label>Appointment Reason</label>
													<textarea class="form-control" name="appointmentVO.reason"></textarea>
												</div>
												
												
											</div>
											
										</div>
										<div style="margin-top: 50px;"><img id="checkboxMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
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
										</div>
										<input type="hidden" id="dateTypeText" name="appointmentVO.appointmentPreferableTimeType">
										<input type="hidden" id="uniqueCode" name="appointmentVO.uniqueCode">
									</form>
									
									<div class="block cloneBlock addattrid" style="display:none;">
										<div class="row">
											<span class="closeIcon"><i class="glyphicon glyphicon-remove"></i></span>
											<div class="col-md-4 m_top10">
												<label>Name</label>
												<input type="text" class="form-control cloneNameCls">
											</div>
											<div class="col-md-4 m_top10">
												<label>Designation</label>
												<select class="cloneDesignationCls">
													<option value="0">Select Designation</option>
													
												</select>
											</div>
											<div class="col-md-4 m_top10">
												<label>Contact Number</label>
												<input type="text" class="form-control cloneMobileCls">
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
											</div>
											<div class="col-md-4 m_top10">
												<label>Membership Number</label>
												<input type="text" class="form-control cloneMembershipNumCls">
											</div>
											<div class="col-md-4 m_top10">
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
												<select class="cloneDistrictCls">
													<option value="0">select dist</option>
													<!--<option value="14">test dist1</option>-->
												</select>
											</div>
											<div class="col-md-4 cloneConstDivCls" style="display:none;">
												<label>Select Constituency</label>
												<select class="cloneConstituencyCls" >
													<option value="0">select const</option>
													<!--<option value="142">test const1</option>-->
												</select>
											</div>
											<div class="col-md-4 cloneMandalDivCLs" style="display:none;">
													<label>Select Mandal/Muncilpality</label>
													<select class="cloneMandalCls">
														<option value="0">Select Mandal</option>
													</select>
												</div>
												<div class="col-md-4 cloneVillageDivCls" style="display:none;">
													<label>Select Village/Ward</label>
													<select class="cloneVillageCls">
														<option value="0">Select VILLAGE</option>
													</select>
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
													<button class="btn btn-success btn-block">EXPORT TO EXCEL</button>
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
                                            <div class="col-md-4">
                                            	<label>Designation<span style='color:red'> &nbsp * </span><span style='color:red' id="appDesigErrId"></span></label>
                                                <select class="designationListCls errClearCls" id="manageAppDesigId"></select>
                                            </div>
                                            <div class="col-md-4">
                                            	<label>Appointment Priority Type<span style='color:red'> &nbsp * </span><span style='color:red' id="appPrrtyErrTypId"></span></label>
                                                <select class="manageAppTypeCls errClearCls" id="manageAppTypeId"></select>
                                            </div>
                                            <div class="col-md-4">
                                            	<label>Appointment Status<span style='color:red'> &nbsp * </span><span style='color:red' id="appStatusErrId"></span></label>
                                                <select class="manageAppStatusCls errClearCls" id="manageAppStatusId"></select>
                                            </div>
                                          <div class="col-md-4 m_top10">
                                            	<label>Select District<span style='color:red' >&nbsp *</span><span style='color:red' id="appDistErrId"></span></label>
                                                <select id="manageAppDistId" class="dropkickClass">
                                                	<option value="0">Select District</option>
                                                </select>
                                          </div>
                                          <div class="col-md-4 m_top10">
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
                                                <select class="dropkickClass">
                                                	<option>Feb-28_29-Appointments</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                            	<button class="btn btn-success btn-block">VIEW</button>
                                            </div>
                                        </div>
                                    </div>
								</div>
							</div>
                            <div class="row">
                            	<div class="col-md-4">
                                	<div class="block">
                                    	<ul class="confirmAppointments">
                                        	<li>
                                            	<div class="row">
                                                    <div class="col-md-12">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                <span class="colorStatus green"></span>
                                                            </div>
                                                            <div class="media-body">
                                                                <p>Laxman - Cadre</p>
                                                                <p>Contact Number: 9848012345</p>
                                                                <p>Designation: MLC</p>
                                                                <p>Last Visit:</p>
                                                                <p>Subject</p>
                                                            </div>
                                                        </div>
                                                        <table class="table table-bordered table-condensed">
                                                            <tr>
                                                                <td><h4>20</h4><p>Requests</p></td>
                                                                <td><h4>20</h4><p>Confirmed</p></td>
                                                                <td><h4>20</h4><p>Rescheduled</p></td>
                                                                <td><h4>20</h4><p>Cancelled</p></td>
                                                            </tr>
                                                        </table>
                                                        <p class="m_top10"><b>NEW REQUESTED DATES</b></p>
                                                        <p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                            	<div class="row">
                                                    <div class="col-md-12">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                <span class="colorStatus green"></span>
                                                            </div>
                                                            <div class="media-body">
                                                                <p>Laxman - Cadre</p>
                                                                <p>Contact Number: 9848012345</p>
                                                                <p>Designation: MLC</p>
                                                                <p>Last Visit:</p>
                                                                <p>Subject</p>
                                                            </div>
                                                        </div>
                                                        <table class="table table-bordered table-condensed">
                                                            <tr>
                                                                <td><h4>20</h4><p>Requests</p></td>
                                                                <td><h4>20</h4><p>Confirmed</p></td>
                                                                <td><h4>20</h4><p>Rescheduled</p></td>
                                                                <td><h4>20</h4><p>Cancelled</p></td>
                                                            </tr>
                                                        </table>
                                                        <p class="m_top10"><b>NEW REQUESTED DATES</b></p>
                                                        <p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                            	<div class="row">
                                                    <div class="col-md-12">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                <span class="colorStatus green"></span>
                                                            </div>
                                                            <div class="media-body">
                                                                <p>Laxman - Cadre</p>
                                                                <p>Contact Number: 9848012345</p>
                                                                <p>Designation: MLC</p>
                                                                <p>Last Visit:</p>
                                                                <p>Subject</p>
                                                            </div>
                                                        </div>
                                                        <table class="table table-bordered table-condensed">
                                                            <tr>
                                                                <td><h4>20</h4><p>Requests</p></td>
                                                                <td><h4>20</h4><p>Confirmed</p></td>
                                                                <td><h4>20</h4><p>Rescheduled</p></td>
                                                                <td><h4>20</h4><p>Cancelled</p></td>
                                                            </tr>
                                                        </table>
                                                        <p class="m_top10"><b>NEW REQUESTED DATES</b></p>
                                                        <p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                	<div class="block">
                                    	<h4 class="text-success">
                                        	CREATE APPOINTMENT TIME SLOT
                                            <button class="btn btn-success pull-right">VIEW BOOKED TIME SLOTS</button>
                                        </h4>
                                        <div class="row">
                                        	<div class="col-md-12">
                                            	 <div class="confirmAppointmentBlock">
                                                    <div class="row">
                                                    	<div class="col-md-12">
                                                        	<div class="drophere"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
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
                                                            <button class="btn btn-success m_top25">SET</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	<div class="col-md-12">
                                            	<ul class="searchConfirm">
                                                	<li>
                                                    	<div class="row">
                                                        	<div class="col-md-5 col-md-offset-7 font12">
                                                            	<span>
                                                                	<i class="glyphicon glyphicon-calendar"></i>28-feb-2016
                                                                </span>&nbsp;
                                                                <span class="text-success">
                                                                	<i class="glyphicon glyphicon-time"></i>
                                                                    2:18 AM to 3:18 PM
                                                                </span>&nbsp;
                                                            	<i class="glyphicon glyphicon-cog settingsIconConfirm"></i>
                                                            </div>
                                                        	<div class="col-md-12">
                                                            	<div class="media">
                                                                    <div class="media-left">
                                                                        <img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <p>Laxman - Cadre</p>
                                                                        <p>Contact Number: 9848012345</p>
                                                                        <p>Appointment SUB</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12">
                                                            	<p class="font12 m_top10">
                                                                    <i>Appt Created By: Rakesh</i>
                                                                    <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
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
<script type="text/javascript">
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
	$(document).on("click","body",function(e){
		$(".updateAppointment").hide()
		$(".appointmentSettingsBLock").hide()
		$(".messageBlock").hide()		
		});
	var maxHeight = 0;

	$(".heightAdjust").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".heightAdjust").height(maxHeight);

	$(document).on("click",".settingsIcon",function(e){
		$(".updateAppointment").hide()
		$(".messageBlock").hide()
		$(".appointmentSettingsBLock").hide()
		e.stopPropagation()
		$(this).parent().parent().parent().find(".appointmentSettingsBLock ").show()
	})
	$(document).on("click",".messageIcon",function(e){
		$(".updateAppointment").hide()
		$(".messageBlock").hide()
		$(".appointmentSettingsBLock").hide()
		e.stopPropagation()
		$(this).parent().parent().find(".messageBlock").show()
	})
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
	e.find(".cloneNameCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].name');
	e.find(".cloneNameCls").attr("id",'candidateNameId'+cloneCount);
	e.find(".cloneDesignationCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].designationId');
	e.find(".cloneDesignationCls").attr("id",'designationSelId'+cloneCount);
	e.find(".cloneDesignationCls").attr("attr_val",cloneCount);
	e.find(".cloneMobileCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].mobileNo');
	e.find(".cloneMobileCls").attr("id",'mobileNoId'+cloneCount);
	e.find(".cloneMembershipNumCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].membershipNum');
	e.find(".cloneMembershipNumCls").attr("id",'membershipNumId'+cloneCount);
	e.find(".cloneVoterIdCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].voterCardNo');
	e.find(".cloneVoterIdCls").attr("id",'voterCardNoID'+cloneCount);
	e.find(".cloneLocationScopeCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].locationScopeId');
	//e.find(".cloneDistrictCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].districtId');
	//e.find(".cloneConstituencyCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].constituencyId');
	 
	 
	e.find(".cloneLocationScopeCls").attr("id",'locationScopeSelId'+cloneCount);
	e.find(".cloneLocationScopeCls").attr("attr_val",cloneCount);
	e.find(".cloneLocationScopeCls").attr("id","locationScopeSelId"+cloneCount);
	e.find(".cloneLocationScopeCls").attr("onChange","showhideLocationBoxes("+cloneCount+")");
	
	e.find(".cloneDistDivCls").attr("id","districtId"+cloneCount+"DivId");
	e.find(".cloneDistDivCls").addClass("locationCls"+cloneCount);
	
	
	e.find(".cloneDistrictCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].districtId');
	e.find(".cloneDistrictCls").attr("id",'districtId'+cloneCount);
	e.find(".cloneDistrictCls").attr("onChange",'getConstituencies('+cloneCount+');');
	e.find(".cloneDistrictCls").attr("attr_val",cloneCount);
	
	e.find(".cloneConstDivCls").attr("id","constituencyId"+cloneCount+"DivId");
	e.find(".cloneConstDivCls").addClass("locationCls"+cloneCount);
	
	e.find(".cloneConstituencyCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].constituencyId');
	e.find(".cloneConstituencyCls").attr("id",'constituencyId'+cloneCount);
	e.find(".cloneConstituencyCls").attr("onChange",'getMandamMuncipalties('+cloneCount+');');
	e.find(".cloneConstituencyCls").attr("attr_val",cloneCount);
	
	e.find(".cloneMandalDivCLs").attr("id","tehsilId"+cloneCount+"DivId");
	e.find(".cloneMandalDivCLs").addClass("locationCls"+cloneCount);

	e.find(".cloneMandalCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].tehsilId');
	e.find(".cloneMandalCls").attr("id",'tehsilId'+cloneCount);
	e.find(".cloneMandalCls").attr("onChange",'getVillageWard('+cloneCount+');');
	e.find(".cloneMandalCls").attr("attr_val",cloneCount);
	
	e.find(".cloneVillageDivCls").attr("id","villageId"+cloneCount+"DivId");
	e.find(".cloneVillageDivCls").addClass("locationCls"+cloneCount);
	
	e.find(".cloneVillageCls").attr("name",'appointmentVO.basicInfoList['+cloneCount+'].villageId');
	e.find(".cloneVillageCls").attr("id",'villageId'+cloneCount);
	e.find(".cloneVillageCls").attr("attr_val",cloneCount);

	
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
			 $(".designationListCls").dropkick();
			 var select = new Dropkick("#designationListId");
			select.refresh(); 
			
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
	
	function showStatus(myResult,num){
		var result = (String)(myResult);
		var resultAray = result.split(',');
		
		if(result.search('success') != -1){
			alert("Appointment Created Failed. Please Try Again");
		}else{
			alert("Appointment Created Successfully.");
		}
			
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

			$("#districtId0").html(str);
			$(".cloneDistrictCls").html(str);
			
			$("#districtId0").dropkick();
			var select = new Dropkick("#districtId0");
			select.refresh();
			
			
			
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
						str+='<button class="btn btn-success btn-xs">STATUS</button>';
						str+='<button class="btn btn-success btn-xs lblDltCls">DELETE</button>';
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
	     labelId=$(this).parents("tr").find("td:eq(0)").attr("attr_label_id");
		 labelName=$(this).parents("tr").find("td:eq(0)").attr("attr_label_name");
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
$("#multiDate").multiDatesPicker({numberOfMonths: [1,2]})
$("#dashboardSelectDateIds").daterangepicker({opens:"left"});
$("#appointmentDateSlotId").daterangepicker({singleDatePicker:true});
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
		getAppntmntSearchDetails();
		$(".addattrid").hide();
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
				var temp=parseInt(cloneCount)-1;
					$('#candidateNameId'+temp).val(name);
					$('#block'+temp).attr("attr_blk",closeId1);
					$('#mobileNoId'+temp).val(mobile);
					$('#designationSelId'+temp).val(desg);
					$('#voterCardNoID'+temp).val(votercardno);
					$('#membershipNumId'+temp).val(membershipno);
					
					$('#locationScopeSelId'+temp).val(lctscpid);
					$('#locationScopeSelId'+temp).trigger("click");
					var select = new Dropkick('#locationScopeSelId'+temp);
						select.refresh();
					
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
			appointmentlabelId : appointmentlabelId
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
				  $("#appointmentRequestedMembersId").html("<div><p style='color:green;font-size:20px'>No Data available.</p></div>")	
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
						
						str+='<p>Subject : '+result[i].subject+'</p>';
						str+='<p>Priority Type : '+result[i].priority+'</p>';
						str+='<p>Requested Date : '+result[i].dateString+'</p>';
						str+='<p>Status : '+result[i].status+'</p>';
						str+='<p>Requested Dates : '+result[i].apptpreferableDates+'</p>';
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
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
												str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
												str+='<p>Last Visit:</p>';
												//str+='<p>Appt Type  '+result[i].subList[j].priority+'</p>';												
											str+='</div>';
										str+='</div>';
										str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
										str+='<table class="table table-bordered">';
											str+='<tr>';
												str+='<td><h4>'+result[i].subList[j].requestCount+'</h4><p>Requests</p></td>';
												for(var k in result[i].subList[j].statusList){
													str+='<td><h4>'+result[i].subList[j].statusList[k].actualCount+'</h4><p>'+result[i].subList[j].statusList[k].name+'</p></td>';
												}
											str+='</tr>';
										str+='</table>';
										//str+='<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>';
										//str+='<p><span>28-feb-2016,05-mar-2016,10-mar-2016</span></p>';
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
													str+='<td> - </td>';
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
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Appointments Added To Label Successfully</h4></center>").fadeOut(2000);
						}, 5000);
				  }
				}else{
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h4 style='margin-top: -22px;color: green;'>Updation Failed..Try Later</h4></center>").fadeOut(2000);
						}, 5000);
					
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
									str+='</div>';
								str+='</div>';
								str+='<p>Subject : '+result[i].reason+'</p>';
								str+='<p>Priority Type : '+result[i].priority+'</p>';
								
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
													str+='<div class="col-md-3">';
														str+='<label>Update Status</label>';
														str+='<select class="form-control">';
															str+='<option>Cancelled</option>';
														str+='</select>';
													str+='</div>';
												  str+='<div class="col-md-2">';
														str+='<select class="form-control m_top25">';
															str+='<option>Tentitive</option>';
															str+='<option>Next 2 weeks</option>';
														str+='</select>';
												  str+='</div>';
													
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
			  $(".appointmentsViewDivCls").html("<div class='col-md-12'><div class='block'><h4 class='text-success' style='margin-bottom:10px;'>"+labelName +" MEMBERS</h4><div><p style='color:green;font-size:20px'>No Data available.</p></div></div></div>");	
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
						
					str+='<p>Subject : '+result[i].subject+'</p>';
					str+='<p>Priority Type : '+result[i].priority+'</p>';
					str+='<p>Requested Date : '+result[i].dateString+'</p>';
					
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
												str+='<p>'+result[i].subList[j].name+' - Cadre</p>';
												str+='<p>Contact Number: '+result[i].subList[j].mobileNo+'</p>';
												str+='<p>Designation: '+result[i].subList[j].designation+'</p>';
												str+='<p>Constituency : '+result[i].subList[j].constituency+'</p>';
												str+='<p>Last Visit:</p>';
												//str+='<p>Appt Type  '+result[i].subList[j].priority+'</p>';												
											str+='</div>';
										str+='</div>';
										str+='<h4 class="m_top10"><b>PREVIOUS APPOINTMENT SNAPSHOT</b></h4>';
										str+='<table class="table table-bordered">';
											str+='<tr>';
												str+='<td><h4>'+result[i].subList[j].requestCount+'</h4><p>Requests</p></td>';
												for(var k in result[i].subList[j].statusList){
													str+='<td><h4>'+result[i].subList[j].statusList[k].actualCount+'</h4><p>'+result[i].subList[j].statusList[k].name+'</p></td>';
												}
											str+='</tr>';
										str+='</table>';
										if(result[i].apptpreferableDates != null){
											str+='<h4 class="m_top10"><b>NEW REQUESTED DATES</b></h4>';
											str+='<p><span>'+result[i].apptpreferableDates+'</span></p>';
										}
											
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
													str+='<td> - </td>';
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
				  str+='</div>';
				str+='</div>';
			}
		
			str+='</div>';
			str+='</div>';
		
		$(".appointmentsViewDivCls").html(str)  
	}
	 
	function getSearchDetails()
	{
		
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
		str+='<h4 class="text-success">UPCOMING APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings">';
		str+='</h4>';
		str+='<div class="updateAppointment arrow_box">';
		str+='<label class="checkbox-inline">';
		str+='<input type="checkbox">Reschedule';
		str+='</label>';
		str+='<label class="checkbox-inline">';
		str+='<input type="checkbox">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10"></textarea>';
		str+='<button class="btn btn-block btn-success">UPDATE APPOINTMENT</button>';
		str+='</div>';
		str+='<ul>';
		if(result != null)
		{
			for(var i in result)
			{
				if(result[i].scheduleType == "UpCome")
				{
					flag = true;
					str+='<li>';
					str+='<p class="font12">';
					str+='<span class="pull-left text-danger"></span>';
					str+='<span class="pull-right text-success">';
					str+='<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].time+' &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
					str+='<div class="appointmentSettingsBLock arrow_box">';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Reschedule';
					str+='</label>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Cancel';
					str+='</label>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Not Attended';
					str+='</label>';
					str+='<label>Select Location</label>';
					str+='<select>';
					str+='<option>Hyderbad</option>';
					str+='</select>';
					str+='<label>Select Date</label>';
					str+='<div class="input-group inputSearch">';
					str+='<span class="input-group-addon">';
					str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='<span class="caret"></span>';
					str+='</span>';
					str+='<input type="text" class="form-control">';
					str+='</div>';
					str+='<div class="input-group inputSearch">';
					str+='<span class="input-group-addon">';
					str+='<i class="glyphicon glyphicon-time"></i>';
					str+='<span class="caret"></span>';
					str+='</span>';
					str+='<input type="text" class="form-control">';
					str+='</div>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Send SMS';
					str+='</label>';
					str+='<textarea class="form-control"></textarea>';
					str+='<button class="btn btn-block btn-success">UPDATRE APPOINTMENT</button>';
					str+='</div>';
					str+='<div class="media">';
					str+='<div class="media-left">';
					str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
					str+='</div>';
					str+='<div class="media-body">';
					str+='<p>'+result[i].name+'</p>';
					str+='<p>Contact Number: '+result[i].mobileNo+'</p>';
					str+='<p>'+result[i].subject+'</p>';
					str+='</div>';
					str+='</div>';
					str+='<p class="font12 m_top10">';
					str+='<i>Appt Created By: '+result[i].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<textarea class="form-control"></textarea>';
					str+='<button class="btn btn-success btn-block">SEND SMS</button>';
					str+='</div>';
					str+='</li>';

				}
			}
			str+='</ul>';
		}
		else
		{
			str+='No Data';	
		}
		
		if(flag == false)
		{
			str+='No Data';	
		}
		$("#upcomingAppointMentId").html(str);
	}
	
	
	function buildInprogressResult(result)
	{
		var str = '';
		var flag = false;
		str+='<h4 class="text-success">INPROGRESS APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings">';
		str+='</h4>';
		str+='<div class="updateAppointment arrow_box">';
		str+='<label class="checkbox-inline">';
		str+='<input type="checkbox">Reschedule';
		str+='</label>';
		str+='<label class="checkbox-inline">';
		str+='<input type="checkbox">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10"></textarea>';
		str+='<button class="btn btn-block btn-success">UPDATE APPOINTMENT</button>';
		str+='</div>';
		str+='<ul>';
		if(result != null)
		{
			for(var i in result)
			{
				if(result[i].scheduleType == "InProgress")
				{
					flag = true;
					str+='<li>';
					str+='<p class="font12">';
					str+='<span class="pull-left text-danger"></span>';
					str+='<span class="pull-right text-success">';
					str+='<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].time+' &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
					str+='<div class="appointmentSettingsBLock arrow_box">';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Reschedule';
					str+='</label>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Cancel';
					str+='</label>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Not Attended';
					str+='</label>';
					str+='<label>Select Location</label>';
					str+='<select>';
					str+='<option>Hyderbad</option>';
					str+='</select>';
					str+='<label>Select Date</label>';
					str+='<div class="input-group inputSearch">';
					str+='<span class="input-group-addon">';
					str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='<span class="caret"></span>';
					str+='</span>';
					str+='<input type="text" class="form-control">';
					str+='</div>';
					str+='<div class="input-group inputSearch">';
					str+='<span class="input-group-addon">';
					str+='<i class="glyphicon glyphicon-time"></i>';
					str+='<span class="caret"></span>';
					str+='</span>';
					str+='<input type="text" class="form-control">';
					str+='</div>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Send SMS';
					str+='</label>';
					str+='<textarea class="form-control"></textarea>';
					str+='<button class="btn btn-block btn-success">UPDATRE APPOINTMENT</button>';
					str+='</div>';
					str+='<div class="media">';
					str+='<div class="media-left">';
					str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
					str+='</div>';
					str+='<div class="media-body">';
					str+='<p>'+result[i].name+'</p>';
					str+='<p>Contact Number: '+result[i].mobileNo+'</p>';
					str+='<p>'+result[i].subject+'</p>';
					str+='</div>';
					str+='</div>';
					str+='<p class="font12 m_top10">';
					str+='<i>Appt Created By: '+result[i].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<textarea class="form-control"></textarea>';
					str+='<button class="btn btn-success btn-block">SEND SMS</button>';
					str+='</div>';
					str+='</li>';

				}
			}
			str+='</ul>';
		}
		else
		{
			str+='No Data';	
		}
		
		if(flag == false)
		{
			str+='No Data';	
		}
		$("#inprogreessAppointMentId").html(str);
	}
	
	
	function buildCompletedResult(result)
	{
		var str = '';
		var flag = false;
		str+='<h4 class="text-success">COMPLETED APPOINTMENTS ';
		str+='<img src="dist/Appointment/img/subMenu.png" class="appointmentSettings">';
		str+='</h4>';
		str+='<div class="updateAppointment arrow_box">';
		str+='<label class="checkbox-inline">';
		str+='<input type="checkbox">Reschedule';
		str+='</label>';
		str+='<label class="checkbox-inline">';
		str+='<input type="checkbox">Cancel';
		str+='</label>';
		str+='<textarea class="form-control m_top10"></textarea>';
		str+='<button class="btn btn-block btn-success">UPDATE APPOINTMENT</button>';
		str+='</div>';
		str+='<ul>';
		if(result != null)
		{
			for(var i in result)
			{
				if(result[i].scheduleType == "Completed")
				{
					flag = true;
					str+='<li>';
					str+='<p class="font12">';
					str+='<span class="pull-left text-danger"></span>';
					str+='<span class="pull-right text-success">';
					str+='<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;'+result[i].time+' &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>';
					str+='<div class="appointmentSettingsBLock arrow_box">';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Reschedule';
					str+='</label>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Cancel';
					str+='</label>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Not Attended';
					str+='</label>';
					str+='<label>Select Location</label>';
					str+='<select>';
					str+='<option>Hyderbad</option>';
					str+='</select>';
					str+='<label>Select Date</label>';
					str+='<div class="input-group inputSearch">';
					str+='<span class="input-group-addon">';
					str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='<span class="caret"></span>';
					str+='</span>';
					str+='<input type="text" class="form-control">';
					str+='</div>';
					str+='<div class="input-group inputSearch">';
					str+='<span class="input-group-addon">';
					str+='<i class="glyphicon glyphicon-time"></i>';
					str+='<span class="caret"></span>';
					str+='</span>';
					str+='<input type="text" class="form-control">';
					str+='</div>';
					str+='<label class="checkbox-inline">';
					str+='<input type="checkbox">Send SMS';
					str+='</label>';
					str+='<textarea class="form-control"></textarea>';
					str+='<button class="btn btn-block btn-success">UPDATRE APPOINTMENT</button>';
					str+='</div>';
					str+='<div class="media">';
					str+='<div class="media-left">';
					str+='<img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">';
					str+='</div>';
					str+='<div class="media-body">';
					str+='<p>'+result[i].name+'</p>';
					str+='<p>Contact Number: '+result[i].mobileNo+'</p>';
					str+='<p>'+result[i].subject+'</p>';
					str+='</div>';
					str+='</div>';
					str+='<p class="font12 m_top10">';
					str+='<i>Appt Created By: '+result[i].createdBy+'</i>';
					str+='<img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>';
					str+='<div class="messageBlock arrow_box">';
					str+='<textarea class="form-control"></textarea>';
					str+='<button class="btn btn-success btn-block">SEND SMS</button>';
					str+='</div>';
					str+='</li>';

				}
			}
			str+='</ul>';
		}
		else
		{
			str+='No Data';	
		}
		
		if(flag == false)
		{
			str+='No Data';	
		}
		$("#completedAppointMentId").html(str);
	}
</script>
<script>

</script>
</body>
</html>