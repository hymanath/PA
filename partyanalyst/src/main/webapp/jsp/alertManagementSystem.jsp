<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government Core DashBoard</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCoreDashBoard/img/fevicon.png">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/jquery.filer.css"  type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css"  type="text/css" rel="stylesheet"/>
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
	<!--<header>
		<nav class="navbar navbar-default navbarHeader">
		  <div class="container">
			<!-- Brand and toggle get grouped for better mobile display 
			<div class="navbar-header">
			  <a class="navbar-brand" href="#"><img src="newCoreDashBoard/img/APLOGO.jpg" class="img-responsive"/></a>
			</div>
		  </div><!-- /.container-fluid 
		</nav>
	</header>-->
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
													<div class="settingsBlockDropDown notesArrow documentCloseClass">
														<i class="glyphicon glyphicon-remove setClose pull-right"></i>
														<div class="row">
															<div class="col-md-4 col-xs-12 col-sm-6 pad_right0 m_top20">
																<ul class="nav nav-tabs navTabsSettings" role="tablist">
																	<li role="presentation" class="active text-capital"><a href="#printMediaAlerts" aria-controls="printMediaAlerts" role="tab" data-toggle="tab">Print Media</a></li>
																	<li role="presentation" class="text-capital"><a href="#electronicMediaAlerts" aria-controls="electronicMediaAlerts" role="tab" data-toggle="tab">Electronic Media</a></li>
																	<li role="presentation" class="text-capital"><a href="#departmentAlerts" aria-controls="departmentAlerts" role="tab" data-toggle="tab">Department</a></li>
																</ul>
															</div>
															<div class="col-md-8 col-xs-12 col-sm-6 pad_left0 pad_right4">
																<div class="tab-content navTabsSettingsContent">
																	<div role="tabpanel" class="tab-pane active" id="printMediaAlerts">
																		<h4 class="text-capital pad_5" style="color:#99A0A5;">Select News Papers</h4>
																		<hr style ="margin-bottom:0px;" />
																		<div class="">
																			<ul class="settingsUl">
																				<li>
																					<label><input type="checkbox" class="selectAllPaperCls" checked />Select All</label>
																				</li>
																				<c:forEach items="${newsPaperList}"  var="newsPaper">
																					<li>
																						<label><input type="checkbox" class="newsPaperListCls" checked attr_val="${newsPaper.id}"/>
																						${newsPaper.name}</label>
																					</li>
																				</c:forEach>
																			</ul>
																		</div>
																	</div>
																	<div role="tabpanel" class="tab-pane" id="electronicMediaAlerts">
																		<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Tv Channels</h4>
																		<hr style ="margin-bottom:0px;" />
																		<ul class="settingsUl">
																			<li>
																				<label><input type="checkbox" class="selectAllChannelsCls" checked />Select All</label>
																			</li>
																			<c:forEach items="${chanelList}"  var="channels">
																				<li>
																					<label><input type="checkbox" class="chanelListCls" checked attr_val="${channels.id}"/>
																					${channels.name}</label>
																				</li>
																			</c:forEach>
																		</ul>
																	</div>
																	<div role="tabpanel" class="tab-pane" id="departmentAlerts">
																		<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Departments</h4>
																		<hr style ="margin-bottom:0px;" />
																		<div class="scrollerBlockDepartments">
																			<ul class="settingsUl">
																				<li>
																					<label><input type="checkbox" class="selectAlldepartmentsCls" checked/>Select All</label>
																				</li>
																				<c:forEach items="${deptList}"  var="departments">
																					<li>
																						<label><input type="checkbox" class="departmentsCls" checked attr_val="${departments.id}"/>
																						${departments.name}</label>
																					</li>
																				</c:forEach>
																			</ul>
																		</div>
																	</div>
																</div>
															  
															</div>
															<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3 m_top10">
																<button type="button" class="btn btn-success filtersSubmitDivId">Get Details</button>
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
								<div id="alertDepartmentWise"></div>
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
			<h4 class="modal-title text-capital">Total alerts</h4>   
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
			<h4 class="modal-title fontColor text-capital"></h4>
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
			<form id="alertAssign" name="alertAssignForm" style="display:none;">
				<div class="row m_top20">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading headingColor">
								<h4 class="panel-title text-capital" id="assignedOfcrCountId"></h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12 col-xs-12 col-sm-12">
										<div id="assignedOfficersId"></div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgDeptId"></span></label>
										<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	
											<option></option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgLvlId"></span></label>
										<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">	
											<option></option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6" id="constituencyLevelDiv" style="display:none;">
										<label>Constituency</label>
										<select class="chosenSelect" id="constLvlId">	
											<option></option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6" id="mndlMuncLevelDiv" style="display:none;">
										<label>Mandal/Muncipality</label>
										<select class="chosenSelect" id="mndlMuncLvlId">	
											<option></option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgLocationId"></span></label>
										<select class="chosenSelect" id="locationsId" name="alertAssigningVO.levelValue">	
											<option></option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgDesgId"></span></label>
										<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">
										<option></option>	
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-6">
										<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgOffcrId"></span></label>
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
										<span style="color:red;" id="errMsgCmntId"></span>
										<label>
											Comments
										<span style="color:red">*</span>&nbsp;&nbsp;</label>
										<label class="radio-inline">
											<input type="radio" name="Lang" value="te" class="lang" id="telugu" onclick="languageChangeHandler();" checked="true"/>Telugu
										</label>
										<label class="radio-inline">
											<input type="radio" name="Lang" value="en" class="lang" id="eng" onclick="languageChangeHandler();"/>English
										</label>
										<textarea class="form-control m_top10" name="alertAssigningVO.comment" placeholder="alert tracking comments" id="alertDescId"></textarea>
										
									</div>
									<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
										<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/><span style="color:red;" id="errMsgImgId"></span>
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
<!-- modal  For Article -->
<div class="modal fade" id="myModalShowNew">
	<div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeArticlePopup" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<div id="linkedDocHeader"></div>
			</div>
			<div class="modal-body">
				<div id="linkedDocBody"></div>
			</div>
		</div>
	</div>
			
</div>	
<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystem.js" type="text/javascript"></script>

<!-- Custom Script Files Data Start-->
<script src="alertDepartment/js/alertManagementSystem.js" type="text/javascript"></script>
<!-- Custom Script Files Data End-->

</body>
</html>