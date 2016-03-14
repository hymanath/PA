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
<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/TimePicker/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css">
<link href="dist/Appointment/MultiDatePicker/css/jquery-ui.css" rel="stylesheet" type="text/css">
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
</style>
</head>
<body>
<div class="container">
<div class="row">
  <div class="col-md-12 col-xs-12 col-sm-12">
	<div class="panel panel-default panelCustom1">
				<div class="panel-heading">
					<h4 class="panel-title">APPOINTMENTS</h4>
				</div>
				<div class="panel-body">
					<div>
					  <ul class="nav nav-tabs navTabsCustom" role="tablist">
						<li role="presentation"  class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><img src="dist/Appointment/img/dashboard.png">Dashboard</a></li>
						<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" class="createAppReqCls"><img src="dist/Appointment/img/createappointment.png">Create Appointment Request</a></li>
						<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab"><img src="dist/Appointment/img/manageappointments.png">Manage APpointments</a></li>
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
												<table class="table table-condensed tableAppointment">
													<tr class="text-success">
														<td>Completed</td>
														<td>14</td>
													</tr>
													<tr style="color:#7BC2BB">
														<td>Inprogress</td>
														<td>14</td>
													</tr>
													<tr style="color:#9358B6">
														<td>Upcoming</td>
														<td>14</td>
													</tr>
													<tr class="text-warning">
														<td>Not Attended</td>
														<td>14</td>
													</tr>
													<tr style="color:#A32D2D">
														<td>Rescheduled</td>
														<td>14</td>
													</tr>
													<tr class="text-danger">
														<td>Cancelled</td>
														<td>14</td>
													</tr>
												</table>
											</td>
											<td>
												<table class="table">
													<tr>
														<td>
															<h4>TOTAL APPOINTMENTS</h4>
															<ul class="columnChartUl">
																<li style="color:#3F51B5"><span class="columnChart" style="background:#3F51B5"></span>Pending Requests - 500</li>
																<li style="color:#2095F1"><span class="columnChart" style="background:#2095F1;"></span>Cancelled - 100</li>
																<li style="color:#4BAF4F"><span class="columnChart "  style="background:#4BAF4F;"></span>Confirmed - 100</li>
																<li style="color:#00BBD4"><span class="columnChart "  style="background:#00BBD4"></span>Completed - 100</li>
																<li style="color:#A86FC5"><span class="columnChart "  style="background:#A86FC5;"></span>Upcoming - 100</li>
																<li style="color:#FE9601"><span class="columnChart "  style="background:#FE9601;"></span>Not Attended - 10</li>
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
													<input class="form-control" type="text">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-search"></i>
													</span>
												</div>
											</div>
											<div class="col-md-3">
                                            	<label>Appointment Created By</label>
                                                <select>
                                                	<option>All</option>
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
                                	<div class="upcomingAppointments heightAdjust">
                                    	<h4 class="text-success">UPCOMING APPOINTMENTS
                                        <img src="dist/Appointment/img/subMenu.png" class="appointmentSettings">
                                        </h4>
                                        <div class="updateAppointment arrow_box">
                                        	<label class="checkbox-inline">
                                            	<input type="checkbox">Reschedule
                                            </label>
                                            <label class="checkbox-inline">
                                            	<input type="checkbox">Cancel
                                            </label>
                                            <textarea class="form-control m_top10"></textarea>
                                            <button class="btn btn-block btn-success">UPDATE APPOINTMENT</button>
                                        </div>
                                        <ul>
                                        	<li>
                                            	<p class="font12">
													<span class="pull-left text-danger">Cancelled</span>
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                    <label>Select Location</label>
                                                    <select>
                                                    	<option>Hyderbad</option>
                                                    </select>
                                                    <label>Select Date</label>
                                                    <div class="input-group inputSearch">
                                                    	<span class="input-group-addon">
                                                        	<i class="glyphicon glyphicon-calendar"></i>
                                                            <span class="caret"></span>
                                                        </span>
                                                        <input type="text" class="form-control">
                                                    </div>
                                                    <div class="input-group inputSearch">
                                                    	<span class="input-group-addon">
                                                        	<i class="glyphicon glyphicon-time"></i>
                                                            <span class="caret"></span>
                                                        </span>
                                                        <input type="text" class="form-control">
                                                    </div>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Send SMS
                                                    </label>
                                                    <textarea class="form-control"></textarea>
                                                    <button class="btn btn-block btn-success">UPDATRE APPOINTMENT</button>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
													<span class="pull-left text-danger">Rescheduled(29-feb-2016 3:05 AM)</span>
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-4 pad_0 ">
                                	<div class="inprogressAppointments heightAdjust">
                                    	<h4 class="text-success">INPROGRESS APPOINTMENTS
                                        <img src="dist/Appointment/img/subMenu.png" class="appointmentSettings">
                                        </h4>
                                        <div class="updateAppointment arrow_box">
                                        	<label class="checkbox-inline">
                                            	<input type="checkbox">Reschedule
                                            </label>
                                            <label class="checkbox-inline">
                                            	<input type="checkbox">Cancel
                                            </label>
                                            <textarea class="form-control m_top10"></textarea>
                                            <button class="btn btn-block btn-success">UPDATE APPOINTMENT</button>
                                        </div>
                                        <ul>
                                        	<li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
                                                <div class="appointmentSettingsBLock arrow_box">
                                                	<label class="checkbox-inline">
                                                    	<input type="checkbox">Reschedule
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Cancel
                                                    </label>
                                                    <label class="checkbox-inline">
                                                    	<input type="checkbox">Not Attended
                                                    </label>
                                                </div>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
													<button class="btn btn-success btn-block">SEND SMS</button>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-4 ">
                                	<div class="completedAppointments heightAdjust">
                                    	<h4 class="text-success">COMPLETED APPOINTMENTS
                                       	 <img src="dist/Appointment/img/subMenu.png" class="appointmentSettings">
                                        </h4>
                                        <div class="updateAppointment arrow_box">
                                        	<label class="checkbox-inline">
                                            	<input type="checkbox">Reschedule
                                            </label>
                                            <label class="checkbox-inline">
                                            	<input type="checkbox">Cancel
                                            </label>
                                            <textarea class="form-control m_top10"></textarea>
                                            <button class="btn btn-block btn-success">UPDATE APPOINTMENT</button>
                                        </div>
                                        <ul>
                                        	<li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                            <li>
                                            	<p class="font12">
                                                	<span class="pull-right text-success">
                                                    	<i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;2:30 PM &nbsp;<i class="glyphicon glyphicon-cog settingsIcon"></i></span></p>
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
                                                <p class="font12 m_top10">
                                                <i>Appt Created By: Rakesh</i>
                                                <img src="dist/Appointment/img/message.png" class="messageIcon" alt="messageIcon"></p>
                                                <div class="messageBlock arrow_box">
                                                	<textarea class="form-control"></textarea>
                                                </div>
                                            </li>
                                        </ul>
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
                                                <select>
													<option>A</option>
												</select>
											</div>
                                            <div class="col-md-4 pad_0">
                                            	<label>Search By Membership No/ Phone No/ Voter ID</label>
                                                <input type="text" class="form-control">
                                            </div>
                                            <div class="col-md-2 pad_left0">
                                            	<button class="btn btn-block btn-success m_top25">SEARCH MEMBER</button>
                                            </div>
                                        </div>
                                        <div class="row m_top25">
                                        	<div class="col-md-12">
                                            	<ul class="createAppointmentSearch">
                                                	<li>
                                                    	<div class="row">
                                                        	<div class="col-md-7">
                                                            	<div class="media">
                                                                    <div class="media-left">
                                                                        <img class="media-object thumbnailSearch thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <p>Laxman - Cadre</p>
                                                                        <p>Contact Number: 9848012345</p>
                                                                        <p>Designation: MLC</p>
                                                                        <p>Constituency</p>
                                                                        <p>Recent Appt History: 20-feb-2016</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View Appt History</a></p>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View/Edit Profile</a></p>
                                                            </div>
                                                            <div class="col-md-1 m_top10">
                                                            	<input type="checkbox">
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                    	<div class="row">
                                                        	<div class="col-md-7">
                                                            	<div class="media">
                                                                    <div class="media-left">
                                                                        <img class="media-object thumbnailSearch thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <p>Laxman - Cadre</p>
                                                                        <p>Contact Number: 9848012345</p>
                                                                        <p>Designation: MLC</p>
                                                                        <p>Constituency</p>
                                                                        <p>Recent Appt History: 20-feb-2016</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View Appt History</a></p>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View/Edit Profile</a></p>
                                                            </div>
                                                            <div class="col-md-1 m_top10">
                                                            	<input type="checkbox">
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                    	<div class="row">
                                                        	<div class="col-md-7">
                                                            	<div class="media">
                                                                    <div class="media-left">
                                                                        <img class="media-object thumbnailSearch thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <p>Laxman - Cadre</p>
                                                                        <p>Contact Number: 9848012345</p>
                                                                        <p>Designation: MLC</p>
                                                                        <p>Constituency</p>
                                                                        <p>Recent Appt History: 20-feb-2016</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View Appt History</a></p>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View/Edit Profile</a></p>
                                                            </div>
                                                            <div class="col-md-1 m_top10">
                                                            	<input type="checkbox">
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                    	<div class="row">
                                                        	<div class="col-md-7">
                                                            	<div class="media">
                                                                    <div class="media-left">
                                                                        <img class="media-object thumbnailSearch thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <p>Laxman - Cadre</p>
                                                                        <p>Contact Number: 9848012345</p>
                                                                        <p>Designation: MLC</p>
                                                                        <p>Constituency</p>
                                                                        <p>Recent Appt History: 20-feb-2016</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View Appt History</a></p>
                                                            </div>
                                                            <div class="col-md-2">
                                                            	<p class="m_top10"><a href="#" class="text-success">View/Edit Profile</a></p>
                                                            </div>
                                                            <div class="col-md-1 m_top10">
                                                            	<input type="checkbox">
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        
                                    </div>
									<div class="block">
										<div class="row">
											<div class="col-md-4 m_top10">
												<label>Appointment Type</label>
												<select class="manageAppTypeCls" id="createAppTypeListId">
													<option><span class="colorStatus green"></span>High</option>
												</select>
											</div>
											<div class="col-md-8 m_top10">
												<label class="radio-inline">
													<input type="radio" checked name="dateTypeRadio">Select Preferrable Dates
												</label>
												<label class="radio-inline">
													<input type="radio" name="dateTypeRadio">Next Week
												</label>
												<label class="radio-inline">
													<input type="radio" name="dateTypeRadio">Next Month(Any Date)
												</label>
												<label class="radio-inline">
													<input type="radio" name="dateTypeRadio">This Week
												</label>
												<div class="input-group inputSearch m_top5">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-calendar"></i>
														<span class="caret"></span>
													</span>
													<input type="text" class="form-control" id="multiDate">
												</div>
											</div>
											<div class="col-md-6 m_top10">
												
												
											</div>
											<div class="col-md-12 m_top10">
												<label>Appointment Reason</label>
												<textarea class="form-control"></textarea>
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
									<div class="block">
										<div class="row">
											<div class="col-md-4 m_top10">
												<label>Name</label>
												<input type="text" class="form-control">
											</div>
											<div class="col-md-4 m_top10">
												<label>Designation</label>
												<select class="designationListCls" id="designationListId">
													<option>A</option>
												</select>
											</div>
											<div class="col-md-4 m_top10">
												<label>Contact Number</label>
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row">
											<div class="col-md-4 m_top10">
												<label>Location</label>
												<input type="text" class="form-control">
											</div>
											<div class="col-md-4 m_top10">
												<label>Voter ID</label>
												<input type="text" class="form-control">
											</div>
											<div class="col-md-4 m_top10">
												<label>Membership Number</label>
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="row m_top10">
											<div class="col-md-4">
												<label>Location Scope</label>
												<select>
													<option>A</option>
												</select>
											</div>
											<div class="col-md-4">
												<label>Select District</label>
												<select>
													<option>A</option>
												</select>
											</div>
											<div class="col-md-4">
												<label>Select Constituency</label>
												<select>
													<option>A</option>
												</select>
											</div>
										</div>
										
										
									</div>
									<div id="moreCandidatesDivId"></div>
									<div class="row m_top10">
										<div class="col-md-4 col-md-offset-8">
											<p style="cursor:pointer;float:right" id="addOneBlock">
												Add One More Candidate
												<i class="glyphicon glyphicon-plus-sign text-success"></i>
											</p>
										</div>
									</div>
									<div class="block cloneBlock" style="display:none;">
										<div class="row">
											<span class="closeIcon"><i class="glyphicon glyphicon-remove"></i></span>
											<div class="col-md-4 m_top10">
												<label>Name</label>
												<input type="text" class="form-control cloneNameCls">
											</div>
											<div class="col-md-4 m_top10">
												<label>Designation</label>
												<select class="cloneDesignationCls">
													<option>A</option>
												</select>
											</div>
											<div class="col-md-4 m_top10">
												<label>Contact Number</label>
												<input type="text" class="form-control cloneMobileCls">
											</div>
										</div>
										<div class="row">
											<div class="col-md-4 m_top10">
												<label>Location</label>
												<input type="text" class="form-control cloneLocationCls">
											</div>
											<div class="col-md-4 m_top10">
												<label>Voter ID</label>
												<input type="text" class="form-control cloneVoterIdCls">
											</div>
											<div class="col-md-4 m_top10">
												<label>Membership Number</label>
												<input type="text" class="form-control cloneMembershipNumCls">
											</div>
										</div>
										<div class="row m_top10">
											<div class="col-md-4">
												<label>Location Scope</label>
												<select class="cloneLocationScopeCls">
													<option>A</option>
												</select>
											</div>
											<div class="col-md-4">
												<label>Select District</label>
												<select class="cloneDistrictCls">
													<option>A</option>
												</select>
											</div>
											<div class="col-md-4">
												<label>Select Constituency</label>
												<select class="cloneConstituencyCls">
													<option>A</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 m_top25">
											<button class="btn btn-success btn-block">CREATE APPOINTMENT</button>
										</div>
								    </div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="messages">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
								  <div class="panel panel-default m_top10 panelWhite">
                                    	<div class="panel-heading">
                                        	<div class="row">
                                            	<div class="col-md-6">
                                                	<h4 class="panel-title text-success">VIEW CREATED APPOINTMENT LABEL</h4>
                                                </div>
                                                <div class="col-md-3">
                                                	<button class="btn btn-success btn-block">VIEW ALL APPOINTMENT REQUESTS</button>
                                                </div>
                                                <div class="col-md-3">
                                                	<button class="btn btn-success btn-block"  data-toggle="modal" data-target=".bs-example-modal-sm">CREATE APPOINTMENT LABEL</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                        	<div class="table-responsive">
                                                <table class="table table-condensed bg_ff">
                                                    <thead>
                                                  <th>APPOINTMENT LABEL NAME</th>
                                                        <th>TOTAL</th>
                                                        <th>FIXED</th>
                                                        <th>WAITING</th>
                                                        <th>CANCELLED</th>
                                                        <th>LABEL STATUS</th>
                                                        <th></th>
                                                    </thead>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Inprogress</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Completed</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Inprogress</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Completed</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Inprogress</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Completed</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Inprogress</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Feb-28_29 Appointment-Request</td>
                                                        <td>30</td>
                                                        <td>15</td>
                                                        <td>10</td>
                                                        <td>05</td>
                                                        <td>Completed</td>
                                                        <td>
                                                            <button class="btn btn-success btn-xs">VIEW</button>
                                                            <button class="btn btn-success btn-xs">ADD MEMBERS</button>
                                                            <button class="btn btn-success btn-xs">UPDATE</button>
                                                            <button class="btn btn-success btn-xs">STATUS</button>
                                                            <button class="btn btn-success btn-xs">DELETE</button>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                    </div>
                                  </div>
							  </div>
							</div>
                            <div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
                                	<div class="block">
                                    	<div class="row">
                                            <div class="col-md-4">
                                            	<label>Designation</label>
                                                <select class="designationListCls" id="manageAppDesigId">
                                                	<option>All</option>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                            	<label>Appointment Type</label>
                                                <select class="manageAppTypeCls" id="manageAppTypeId">
                                                	<option>High</option>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                            	<label>Appointment Status</label>
                                                <select class="manageAppStatusCls" id="manageAppStatusId">
                                                	<option>Waiting</option>
                                                </select>
                                            </div>
                                          <div class="col-md-4 m_top10">
                                            	<label>Select District</label>
                                                <select>
                                                	<option>District Name</option>
                                                </select>
                                          </div>
                                          <div class="col-md-4 m_top10">
                                            	<label>Select Constituency</label>
                                                <select>
                                                	<option>Constituency Name</option>
                                                </select>
                                          </div>
                                          <div class="col-md-12 m_top10">
                                            	<label class="checkbox-inline">
                                                	<input type="checkbox">This Month(Any Date)
                                                </label>
                                                <label class="checkbox-inline">
                                                	<input type="checkbox">Any Date
                                                </label>
                                          </div>
                                          <div class="col-md-2 m_top10">
                                            	<button class="btn btn-success btn-block">VIEW</button>
                                          </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          <div class="row">
                            	<div class="col-md-12">
                                	<div class="block">
                                    	<h4 class="text-success">APPOINTMENT REQUESTED MEMBERS</h4>
                                    	<ul class="appointmentRequestedMembers">
                                            <li>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                    	<span class="requestedCheckbox">
	                                                        <input type="checkbox">
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
                                                    	<span class="requestedCheckbox">
	                                                        <input type="checkbox">
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
                                                    	<span class="requestedCheckbox">
	                                                        <input type="checkbox">
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
                                        <button class="btn btn-success">UPDATE LABEL</button>
                                    </div>
                                </div>
                          </div>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="block">
                                    	<h4 class="text-success">VIEWING (FEB-27_28 APPOINTMENT-REQUEST) LABEL</h4>
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
                                </div>
                            </div>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="block">
                                    	<h4 class="text-success">UPDATE LABEL MEMBER(FEB-28_29 APPOINTMENT-REQUEST)</h4>
                                        <ul class="updateLabelMembers">
                                            <li>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <span class="requestedCheckbox text-danger">
                                                            Cancelled
                                                        </span>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                            </div>
                                                            <div class="media-body">
                                                                <p>Laxman - Cadre</p>
                                                                <p>Contact Number: 9848012345</p>
                                                                <p>Designation: MLC</p>
                                                                <p>Appt Subject</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label>Update Status</label>
                                                        <select>
                                                        	<option>Cancelled</option>
                                                        </select>
                                                    </div>
                                                  <div class="col-md-3">
                                                        <select class="m_top25">
                                                        	<option>Tentitive</option>
                                                            <option>Next 2 weeks</option>
                                                        </select>
                                                  </div>
                                                    <div class="col-md-12">
                                                   	  <p class="pull-right">Appt Created By: Rakesh &nbsp;&nbsp;&nbsp;&nbsp; <img src="dist/Appointment/img/message.png" class="message"></p>
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
                                                    <div class="col-md-4">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <img class="media-object thumbnail" src="dist/Appointment/img/thumb.jpg" alt="...">
                                                            </div>
                                                            <div class="media-body">
                                                                <p>Laxman - Cadre</p>
                                                                <p>Contact Number: 9848012345</p>
                                                                <p>Designation: MLC</p>
                                                                <p>Appt Subject</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label>Update Status</label>
                                                        <select>
                                                        	<option>Cancelled</option>
                                                        </select>
                                                    </div>
                                                  <div class="col-md-3">
                                                        <select class="m_top25">
                                                        	<option>Tentitive</option>
                                                            <option>Next 2 weeks</option>
                                                        </select>
                                                  </div>
                                                    <div class="col-md-12">
                                                   	  <p class="pull-right">Appt Created By: Rakesh &nbsp;&nbsp;&nbsp;&nbsp; <img src="dist/Appointment/img/message.png" class="message"></p>
                                                    </div>
                                                </div>
                                            </li>
                                            <button class="btn btn-success m_top25">UPDATE LABEL</button>
                                        </ul>
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
                                                <select>
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
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label>From Time</label>
                                                            <div class="input-group inputSearch">
                                                                <span class="input-group-addon">
                                                                    <i class="glyphicon glyphicon-calendar"></i>
                                                                    <span class="caret"></span>
                                                                </span>
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label>To Time</label>
                                                            <div class="input-group inputSearch">
                                                                <span class="input-group-addon">
                                                                    <i class="glyphicon glyphicon-calendar"></i>
                                                                    <span class="caret"></span>
                                                                </span>
                                                                <input type="text" class="form-control">
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
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body" style="padding:25px;">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<label>Enter New Label Name</label>
		<input type="text" class="form-control">
		<button class="btn btn-success btn-block m_top10">CREATE NEW LABEL</button>
	  </div>
    </div>
  </div>
</div>
<script src="dist/2016DashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/TimePicker/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="dist/Appointment/MultiDatePicker/js/jquery-ui-1.11.1.js" type="text/javascript"></script>
<script src="dist/Appointment/MultiDatePicker/js/jquery-ui.multidatespicker.js" type="text/javascript"></script>
<script type="text/javascript">
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
$(function () {
    // Create the chart
    $('#LineChart').highcharts({
        chart: {
            type: 'column',
			backgroundColor: 'transparent' 
        },
        title: {
            text: ' '
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
            data: [{
                name: "Cancelled",
                y: 56.33,
				color:"#2095F1"
            }, {
                name: "Confirmed",
                y: 24.03,
				color:"#4BAF4F"
            }, {
                name: "Pending Requests",
                y: 10.38,
				color:"#3F51B5"
            }, {
                name: "Completed",
                y: 4.77,
				color:"#00BBD4"
            }, {
                name: "Upcoming",
                y: 8.91,
				color:"#A86FC5"
            }, {
                name: "Not Attended",
                y: 2.2,
				color:"#FE9601"
            }]
        }],
    });
});
$("#multiDate").multiDatesPicker({numberOfMonths: [1,2]})
$("#dashboardSelectDateIds").daterangepicker({opens:"left"});
$(document).on("click","#addOneBlock",function(){
	var e = $(".cloneBlock").clone();
	e.css("display","block");
	e.find(".cloneNameCls").addClass("candidateNameCls");
	e.find(".cloneDesignationCls").addClass("candidateDesignationCls");
	e.find(".cloneMobileCls").addClass("candidateMobileCls");
	e.find(".cloneLocationCls").addClass("candidateLocationCls");
	e.find(".cloneMembershipNumCls").addClass("candidateMembershipNumCls");
	e.find(".cloneVoterIdCls").addClass("candidateVoterIdCls");
	e.find(".cloneLocationScopeCls").addClass("candidateLocationScopeCls");
	e.find(".cloneDistrictCls").addClass("candidateDistrictCls");
	e.find(".cloneConstituencyCls").addClass("candidateConstituencyCls");
	e.removeClass("cloneNameCls");
	e.removeClass("cloneDesignationCls");
	e.removeClass("cloneMobileCls");
	e.removeClass("cloneLocationCls");
	e.removeClass("cloneMembershipNumCls");
	e.removeClass("cloneVoterIdCls");
	e.removeClass("cloneLocationScopeCls");
	e.removeClass("cloneDistrictCls");
	e.removeClass("cloneConstituencyCls");
	e.removeClass("cloneBlock");
	$("#moreCandidatesDivId").append(e);
});

$(document).on("click",".closeIcon",function(){
	$(this).parent().parent().remove();
});
$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : 'getCandidateDesignation.action',
		dataType : 'json',
		date : {}
	}).done(function(result){ 
		if(result != null && result.length > 0){
			//app-appointment
			buildDesignationForCreateApp(result);
			buildDesignationForManageApp(result);

		}
		
	});
	
});
function buildDesignationForCreateApp(result){
		$("#designationListId  option").remove();
		$("#designationListId").append('<option value="0">Select Designation</option>');
		for(var i in result){
			$("#designationListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$(".designationListCls").dropkick();
		var select = new Dropkick("#designationListId");
		select.refresh();
} 
function buildDesignationForManageApp(result){
		$("#manageAppDesigId  option").remove();
		$("#manageAppDesigId").append('<option value="0">Select Designation</option>');
		for(var i in result){
			$("#manageAppDesigId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$(".designationListCls").dropkick();
		var select = new Dropkick("#manageAppDesigId");
		select.refresh();
}
$(document).ready(function(){
	$.ajax({
		type : 'GET',
		url : 'getAppointmentStatusList.action',
		dataType : 'json',
		date : {}
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
		date : {}
	}).done(function(result){ 
		if(result != null && result.length > 0){
			buildAppointmentPriorityList(result);
			buildPriorityForManageAppointment(result);
		}
		
	});
});
function buildAppointmentPriorityList(result){
		$("#manageAppTypeId  option").remove();
		$("#manageAppTypeId").append('<option value="0">Select Appointment Type</option>');
		for(var i in result){
			$("#manageAppTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$(".manageAppTypeCls").dropkick();
		var select = new Dropkick("#manageAppTypeId");
		select.refresh();
}
function buildPriorityForManageAppointment(result){
		$("#createAppTypeListId  option").remove();
		$("#createAppTypeListId").append('<option value="0">Select Appointment Type</option>');
		for(var i in result){
			$("#createAppTypeListId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$(".manageAppTypeCls").dropkick();
		var select = new Dropkick("#createAppTypeListId");
		select.refresh();
}
</script>
</body>
</html>
			
				