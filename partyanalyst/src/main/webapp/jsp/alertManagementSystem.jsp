<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government Core DashBoard</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCoreDashBoard/img/fevicon.png">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>

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

<style type="text/css">
.eventsheader
{
	display:none;
}
</style>
</head>
<body>
<div  class="AMS">
	<header>
		<nav class="navbar navbar-default navbarHeader">
		  <div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
			  <a class="navbar-brand" href="#"><img src="newCoreDashBoard/img/APLOGO.jpg" class="img-responsive"/></a>
			</div>
		  </div><!-- /.container-fluid -->
		</nav>
	</header>
	<section class="m_top20">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<h4></h4>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-4">
									<div class="panel panel-default">
										<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">status overview</h4>
										</div>
										<div class="panel-body">
											<div id="statusOverview"></div>
										</div>
									</div>
								</div>
								<div class="col-md-8 col-xs-12 col-sm-8">
									<div class="panel panel-default panelCustom">
										<div class="panel-heading headingColor">
											<div class="row">
												<div class="col-md-7 col-xs-12 col-sm-7">
													<h4 class="panel-title text-capital fontColor m_top5">department wise status overview</h4>
												</div>
												<div class="col-md-5 col-xs-12 col-sm-5">
													<span class="settingsIcon pull-right">
														<i class="fa fa-gears" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
													</span>
													<div class="settingsBlockDropDown settingsArrow">
														<i class="glyphicon glyphicon-remove newsSetClose pull-right"></i>
														<div class="row">
															<div class="col-md-4 col-xs-12 col-sm-6 pad_right0 m_top20">
																<ul class="nav nav-tabs navTabsSettings" role="tablist">
																	<li role="presentation" class="active text-capital"><a href="#printMediaAlerts" aria-controls="EditionsEmn" role="tab" data-toggle="tab">Print Media</a></li>
																	<li role="presentation" class="text-capital"><a href="#electronicMediaAlerts" aria-controls="impactScopeEmn" role="tab" data-toggle="tab">Electronic Media</a></li>
																	<li role="presentation" class="text-capital"><a href="#departmentAlerts" aria-controls="impactScopeEmn" role="tab" data-toggle="tab">Department</a></li>
																</ul>
															</div>
															<div class="col-md-8 col-xs-12 col-sm-6 pad_left0 pad_right4">
																<div class="tab-content navTabsSettingsContent">
																	<div role="tabpanel" class="tab-pane active" id="printMediaAlerts">
																		<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Publication</h4>
																		<hr style ="margin-bottom:0px;" />
																		<div class="">
																			<ul class="settingsUlEmn" id="emnNewsChannelsUlId">
																				<c:forEach items="${newsPaperList}"  var="newsPaper">
																					<li attr_val="${newsPaper.id}">${newsPaper.name}</option></li>
																				</c:forEach>
																			</ul>
																		</div>
																	</div>
																	<div role="tabpanel" class="tab-pane" id="electronicMediaAlerts">
																		<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Impact</h4>
																		<hr style ="margin-bottom:0px;" />
																		<div class="">
																			<ul class="settingsUlEmn">
																				<li>
																					<label class="checkbox-inline">
																						<input type="checkbox" value="0" id="impactSelectAllIdEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Select All</h5></div>
																					</label>
																				</li>
																				<li>
																					<label class="checkbox-inline">
																						<input type="checkbox" value="1" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">State</h5></div>
																					</label>
																				</li>
																				<li>												
																					<label class="checkbox-inline">
																						<input type="checkbox" value="2" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">District</h5></div>
																					</label>	
																				</li>	
																				<li>
																					<label class="checkbox-inline">
																						<input type="checkbox" value="3" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Constituency</h5></div>
																					</label>
																				</li>
																				<li>
																					<label class="checkbox-inline">
																					<input type="checkbox" value="4" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Parliament</h5></div>
																					</label>
																				</li>	
																				<li>
																					<label class="checkbox-inline">
																						<input type="checkbox" value="5" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Mandal</h5></div>
																					</label>
																				</li>
																				<li>
																					<label class="checkbox-inline">
																						<input type="checkbox" value="6" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Panchayat</h5></div>
																					</label>
																				</li>
																				<li>
																					<label class="checkbox-inline">
																						<input type="checkbox" value="8" class="impactCheckClsEmn" checked>
																						<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;">Municipality/Corporation</h5></div>
																					</label>	
																				</li>	
																			</ul>
																		</div>
																	</div>
																	<div role="tabpanel" class="tab-pane active" id="departmentAlerts">
																		<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Publication</h4>
																		<hr style ="margin-bottom:0px;" />
																		<div class="">
																			<ul class="settingsUlEmn" id="emnNewsChannelsUlId"></ul>
																		</div>
																	</div>
																</div>
															  
															</div>
															<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3">
																<button type="button" class="btn btn-success filtersSubmitDivIdEmn">Get Details</button>
															</div>
														</div>
													</div>
													<div class="input-group dateRangePickerCls m_top5">
														<input type="text" class="form-control" style="width:180px" id="dateRangePicker">
														<span class="input-group-addon">
															<i class="glyphicon glyphicon-calendar"></i>
														</span>
													</div>
												</div>
											</div>
											
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-12 col-xs-12 col-sm-12">
													<div id="departmentWiseStatusOvrVw"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="panel panel-default panelCustom">
										<div class="panel-heading headingColor">
											<h4 class="panel-title fontColor"></h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-4 col-xs-12 col-sm-4">
													<h4 class="panel-title text-capital">district wise total alerts</h4>
												</div>
												<div class="col-md-8 col-xs-12 col-sm-8">
													<ul class="list-inline activeUlCls">
														<li>Overview</li>
														<li>Status</li>
													</ul>
												</div>
												<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
													<div id=""></div>
												</div>
												<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
													<table class="table table-bordered">
														<thead>
															<th></th>
															<th class="bg_EE">Total</th>
															<th>Pending</th>
															<th>Notified</th>
														</thead>
														<tbody>
															<tr>
																<td class="bg_D8">Total Alerts</td>
																<td class="bg_D8"></td>
																<td class="bg_D8"></td>
																<td class="bg_D8"></td>
															</tr>
															<tr>
																<td>Print Media Alerts</td>
																<td class="bg_EE"></td>
																<td></td>
																<td></td>
															</tr>
														</tbody>
													</table>
													<button class="btn btn-default btnBorder pull-right"></button>
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
	</section>
	<!-- TOtal Alerts Modal Start-->
	<div class="modal fade" id="totalAlertsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
		  <div class="modal-header bg_CC">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title text-capital" id="titleId">Total alerts</h4>   
		  </div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="totalAlertsModalTabId"></div>
					</div>
				</div>
			</div>
		</div> 
	  </div>
	</div>
	<!-- TOtal Alerts Modal End-->
	<!-- Alert Details Modal Start-->
	<div class="modal fade" id="alertDetailsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
		  <div class="modal-header bg_CC">
			<button type="button" class="close alertDetailsModalClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title fontColor text-capital">Alert Title</h4>
			<p id="mainTitleId"></p>
		  </div>
		  <div class="modal-body">
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12">
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
					</table>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-4 col-xs-12 col-sm-6" style="border-right:1px solid #ddd;">
					<h4 class="panel-title text-capital"><b>involved members in this alert</b>
					<span id="involvedCandidatesCnt" class="pull-right">0</span></h4>
					<div class="involvedMembersUl" id="alertCandidateDataId"></div>  
				</div>  
				<div class="col-md-8 col-xs-12 col-sm-6">
					<h4 class="panel-title text-capital">alert status tracking comments</h4>
					<div id="alertCommentsDivIdNew"></div>
				</div>
			</div>
			<form id="alertAssign" name="alertAssignForm">
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading headingColor">
								<h4 class="panel-title text-capital">assigned officers - 01</h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12 col-xs-12 col-sm-12">
										<div id="assignedOfficersId"></div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Location Level</label>
										<div id="locationLevelId"></div>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Location</label>
										<select class="chosenSelect" id="locationId" name="alertAssigningVO.levelValue">	
											<option></option>
										</select>
									</div>
									<div class="col-md-2 col-xs-12 col-sm-6">
										<label>Department</label>
										<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	
											<option></option>
										</select>
									</div>
									<div class="col-md-2 col-xs-12 col-sm-6">
										<label>Designation</label>
										<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">	
											<option></option>
										</select>
									</div>
									<div class="col-md-2 col-xs-12 col-sm-6">
										<label>Officer Name</label>
										<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">
											<option></option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading headingColor">
								<h4 class="panel-title text-capital">update alert information</h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12 col-xs-12 col-sm-12">
										<label>
											Comments
										</label>
										<label class="radio-inline">
											<input type="radio" name="Lang"/>Telugu
										</label>
										<label class="radio-inline">
											<input type="radio" name="Lang"/>English
										</label>
										<textarea class="form-control m_top10" name="alertAssigningVO.comment" placeholder="alert tracking comments"></textarea>
										<input type="file" name="imageForDisplay" class="form-control m_top20"/>
									</div>
									<div class="col-md-4 col-xs-12 col-sm-6">
										<button class="btn btn-success btn-block text-capital m_top20" id="assignOfficerId" type="button">assign alert to designated officer</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="hiddenAlertId" name="alertAssigningVO.alertId"></input>
			</form>
		  </div>
		</div>
	  </div>
	</div>
	<!-- Alert Details Modal End-->
</div>
<!--Main Div End-->

<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/alertManagementSystem/alertManagementSystem.js" type="text/javascript"></script>
</body>
</html>