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
<title>District Collector</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCoreDashBoard/img/fevicon.png">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/jquery.filer.css"  type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css"  type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<link href="alertDepartment/css/customLess.less" rel="stylesheet" type="text/less">
<!--<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">-->
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.7.2/less.min.js"></script>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
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
.pointerCls{
	cursor:pointer  !important;
}
.slick-next{ margin-right: 15px;}
.slick-prev{ margin-left: 15px;}
</style>
</head>
<body>
<div  class="AMS AUMS">
	<div class="themeControll">
	<div class="linkinner"> 
		<input type="text" class="form-control" id="alertIdSearch" placeholder="Alert ID"/>
	</div>
	<p class="tbtn"> <i class="glyphicon glyphicon-search"></i></p>
</div>
	<section class="m_top20">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12 panelDropDown"> 
					
					<span class="settingsIcon pull-right" style="margin-top:7px;background-color:#4a5863; color: #ffffff">
								<i class="fa fa-gears" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
					</span>
					<div class="settingsBlockDropDown notesArrow documentCloseClass" >
						<i class="glyphicon glyphicon-remove setClose pull-right"></i>
						<!--<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12"> 
								<label class="checkbox-inline"><input type="checkbox" class="selectAllCls " checked />Select All</label>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12"> 
								<label class="checkbox-inline"><input type="checkbox" class="newsPaperListCls checkedSelected" checked  />Print Media</label>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12"> 
								<label class="checkbox-inline"><input type="checkbox" class="chanelListCls checkedSelected" checked />Electronic Media</label>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12"> 
								<label class="checkbox-inline"><input type="checkbox" class="callcenterCls checkedSelected"  checked />Call Center</label>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12"> 
								<label class="checkbox-inline"><input type="checkbox" class="socialMediaCls checkedSelected"  checked />Social Media</label>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12  m_top10"> 
								<input class="btn btn-success btn-sm" value="APPLY" type="button" onClick="onLoadCallsAMU();" />
							</div>
						</div>-->
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-6 pad_right0 m_top20">
								<ul class="nav nav-tabs navTabsSettings" role="tablist">
									<li role="presentation" class="active text-capital"><a href="#mediaAlerts" aria-controls="mediaAlerts" role="tab" data-toggle="tab">Media Type</a></li>
									<li role="presentation" class="text-capital"><a href="#callCenterAlerts" aria-controls="callCenterAlerts" role="tab" data-toggle="tab">Call Center</a></li>
									<li role="presentation" class="text-capital"><a href="#socialMediaType" aria-controls="socialMediaType" role="tab" data-toggle="tab">Social Media Type</a></li>
									<li role="presentation" class="text-capital"><a href="#mondayGrievanceType" aria-controls="mondayGrievanceType" role="tab" data-toggle="tab">Monday Grievance Type</a></li>
									<li role="presentation" class="text-capital"><a href="#janmabhoomiType" aria-controls="janmabhoomiType" role="tab" data-toggle="tab">Janmabhoomi Type</a></li>
									<li role="presentation" class="text-capital"><a href="#specialGrievanceType" aria-controls="specialGrievanceType" role="tab" data-toggle="tab">Special Grievance Type</a></li>
									<li role="presentation" class="text-capital"><a href="#generalGrievanceType" aria-controls="generalGrievanceType" role="tab" data-toggle="tab">General Grievance Type</a></li>
									<li role="presentation" class="text-capital"><a href="#alertSeverityType" aria-controls="alertSeverityType" role="tab" data-toggle="tab">Alert Severity</a></li>
									<li role="presentation" class="text-capital"><a href="#alertStatusType" aria-controls="alertStatusType" role="tab" data-toggle="tab">Alert Status</a></li>
									<li role="presentation" class="text-capital"><a href="#alertSubTaskType" aria-controls="alertSubTaskType" role="tab" data-toggle="tab">Alert Sub Task Status</a></li>
								</ul>
							</div>
							<div class="col-md-8 col-xs-12 col-sm-6 pad_left0 pad_right4">
								<div class="tab-content navTabsSettingsContent">
									<div role="tabpanel" class="tab-pane active" id="mediaAlerts">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Media Type</h4>
										<hr style ="margin-bottom:0px;" />
										<div class="">
											<ul class="settingsUl">
												<li>
													<label><input type="checkbox" class="selectAllPaperCls" checked />Select All</label>
												</li>
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="newsPaperListCls checkedSelected" checked  />Print Media</label>
												</li>
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="chanelListCls checkedSelected" checked />Electronic Media</label>
												</li>
											</ul>
										</div>
									</div>
									
									<div role="tabpanel" class="tab-pane" id="callCenterAlerts">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Call Center</h4>
										<hr style ="margin-bottom:0px;" />
										<div class="scrollerBlockDepartments">
											<ul class="settingsUl">
												<li>
													<label><input type="checkbox" class="selectAllcallcenterCls" checked />Select All</label>
												</li>
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="callcenterCls checkedSelected" checked />Call Center</label>
												</li>
											</ul>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="socialMediaType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Social Media Type</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllMediaType" checked />Select All</label>
											</li>
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="socialMediaCls checkedSelected" checked />Social Media</label>
												</li>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="mondayGrievanceType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Monday Grievance Type</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllMondayGrievanceType" checked />Select All</label>
											</li>
											<li>
												<label><input type="checkbox" class="mondayGrievanceCls checkedSelected" checked />
													Monday Grievance</label>
												</li>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="janmabhoomiType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Janmabhoomi Type</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllJanmabhoomiType" checked />Select All</label>
											</li>
												<li>
													<label><input type="checkbox" class="janmabhoomiCls checkedSelected" checked />
													Janmabhoomi Type</label>
												</li>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="specialGrievanceType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Special Grievance Type</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllSpecialGrievanceType checkedSelected" checked />Select All</label>
											</li>
												<li>
													<label><input type="checkbox" class="specialGrievanceCls" checked />
													Special Grievance Type</label>
												</li>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="generalGrievanceType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select General Grievance Type</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllGeneralGrievanceType" checked />Select All</label>
											</li>
												<li>
													<label><input type="checkbox" class="generalGrievanceCls checkedSelected" checked />
													General Grievance Type</label>
												</li>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="alertSeverityType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Severity Type</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllSeverityType" checked />Select All</label>
											</li>
											<c:forEach items="${alertSeverityList}"  var="severityType">
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="severityTypeCls checkedSelected" checked  attr_val="${severityType.id}" /><span>${severityType.name}</span></label>
												</li>
											</c:forEach>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="alertStatusType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Alert Status</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllStatusType" checked />Select All</label>
											</li>
											<c:forEach items="${alertStatusList}"  var="alertStatusType">
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="statusTypeCls checkedSelected" checked  attr_val="${alertStatusType.id}"/>${alertStatusType.name}</label>
												</li>
											</c:forEach>
										</ul>
									</div>
									<div role="tabpanel" class="tab-pane" id="alertSubTaskType">
										<h4 class="text-capital pad_5" style="color:#99A0A5;">Select Alert Sub Task Status</h4>
										<hr style ="margin-bottom:0px;" />
										<ul class="settingsUl">
											<li>
												<label><input type="checkbox" class="selectAllSubTaskType" checked />Select All</label>
											</li>
											<c:forEach items="${govtAlertSubTaksStatusList}"  var="alertSubTaskType">
												<li>
													<label class="checkbox-inline"><input type="checkbox" class="subTaskTypeCls checkedSelected" checked attr_val="${alertSubTaskType.id}" />${alertSubTaskType.name}</label>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							  
							</div>
							<div class="col-md-8 col-md-offset-4 col-xs-12 col-sm-9 col-sm-offset-3 m_top10">
								<input class="btn btn-success btn-sm" value="APPLY" type="button" onClick="onLoadCallsAMU();" />
							</div>
						</div>
					</div>
					<div class="input-group dateRangePickerCls m_top5 pull-right" style="margin-right: 15px">
						<input type="text" class="form-control" style="width:180px" id="dateRangePickerAUM">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top10">
							<div class="panel panel-default">
								<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">Alert Source Wise - Details</h4>
								</div>
								<div class="panel-body">
									<div id="alertSourceWiseDetilsDivId"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel panel-default">
								<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">Proposal Overview</h4>
								</div>
								<div class="panel-body">
									<div id="financialAssistanceDetilsDivId"></div>
								</div>
							</div>
						</div>
					</div>
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-12 ">
								<div class="panel panel-default panelheights">
								  <div class="panel-heading headingColor">
									<h4 class="panel-title text-capital fontColor">My Alerts</h4>
								  </div>
								  <div class="panel-body">
									<div id="myAlertsDivID"></div>
								  </div>
								</div>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-12 ">
								<div class="panel panel-default panelheights">
								  <div class="panel-heading headingColor">
									<h4 class="panel-title text-capital fontColor">My Sub Tasks</h4>
								  </div>
								  <div class="panel-body">
									<div id="mySubTasksDivID"></div>
								  </div>
								</div>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-12">
								<div class="panel panel-default panelheights">
								  <div class="panel-heading headingColor">
									<h4 class="panel-title text-capital fontColor">Assigned Sub Tasks</h4>
								  </div>
								  <div class="panel-body">
									<div id="assignedSubTasksDivID"></div>
								  </div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="panel panel-default">
									<div class="panel-heading headingColor">
										<div class="row">
											<div class="col-md-8 col-xs-12 col-sm-8">
												<h4 class="panel-title text-capital fontColor">COLLECTORATE Alerts</h4>
											</div>
											<div class="col-md-4 col-xs-12 col-sm-4">
												<ul class="alertType-subordinate pull-right">       
													<li  attr_type="task" class="alertSubOridanateCls">Alerts</li>
													<li attr_type="subTask" class="subTaskSubOrdinate">Sub Tasks</li>
												</ul>
											</div>
										</div>
									</div>
									<div class="panel-body">  
										<div id="subordinateAlertsDivId"></div>
									</div>
								</div>
							</div>    
						</div>
						<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12"> 	
							<div class="panel panel-default">
								<div class="panel-heading headingColor ">
									<div class="row">
										<div class="col-md-4 col-xs-12 col-sm-4">
											<h4 class="panel-title text-capital fontColor">District Level - Department Wise</h4>
										</div>
										<div class="col-md-8 col-xs-12 col-sm-8">
											<ul class="switch-btn-alertType pull-right">
												<li  attr_type="alert" class="alertTrigger">Alerts</li>
												<li class="subTaskTrigger" attr_type="subTask">Sub Tasks</li>
											</ul>
											<ul class="switch-btn pull-right activeCls">
												<li attr_type="status" attr_department_type="statuswise" class="active">status overview</li>
												<li attr_type="scopeLevel" attr_department_type="scopewise" class="" >location level</li>
												<li attr_type="alertCategory" attr_department_type="alertSource" class="" >Alert Source</li>
												<li attr_type="filterView" attr_department_type="filterView" class="" id="filterViewId" >Filter</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-12 col-xs-12 col-md-12 ">
											<div class="col-md-2 col-xs-12 col-sm-4">
												<ul class="list-inline activeUlCls  constituencyUl sortingDivCls">
												<li class="active " attr_sorting_type="Decending">
													<i class="glyphicon glyphicon-sort-by-attributes" ></i>
												</li>
												<li class="" attr_sorting_type="Ascending">
													<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>
												</li>
												<li class="" attr_sorting_type="AlphabeticalAscending">
													A-Z
												</li>
												<li class="" attr_sorting_type="AlphabeticalDescending">
													Z-A
												</li>
												</ul>
											</div>
											<div class="col-sm-4 col-xs-12 col-md-4">
												<select class="form-control" id="govtDepartmentsId" >
												</select>
											</div>
											<div class="col-sm-4 col-xs-12 col-md-3 pull-right locationLevelNamesCls" >
												<select class="form-control" id="locationLevelNamesId" >
												</select>
											</div>
										</div>
										<div class="col-sm-12 col-xs-12 col-md-12 ">
											<div id="filterViewBodyId" style="display:none"></div>
										</div>
											<div id="departmentWiseGraphViewDetails"></div>
									</div>
								</div>
								
							</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-xs-12 col-md-12 ">
								<div id="departmentWiseLocationBlockId" ></div>
							</div>
						</div>
					</div>
				</div>
			</div>			
		</div>
	</section>
<div class="modal fade" id="alertManagementPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:95%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body modal-insurance">
				<!--<div id="filter" style="display:none;"></div>-->
				<div id="alertManagementPopupBody"></div>
			</div>
		</div>
  </div>
</div>
<div class="modal fade" id="alertManagementPopup1" tabindex="-2" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:90%;">
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
<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="alertDepartment/js/jquery.hotkeys.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystemNewUpload.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
<script src="alertDepartment/js/newAlertDistrictCollector.js" type="text/javascript"></script>
<script src="alertDepartment/js/newAlertUserManagementDetail.js" type="text/javascript"></script>
<script src="alertDepartment/js/newAlertFilter.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

var globalDepartmentId;
var overAllAlertIds =[];
var totalCoutAlertIds =[];
var globalUserLevelId;
var globalUserLevelValues = [];	
var globalgovtDeptDesigOffcrIds=[];
var	globalgovtOfficerIds=[];
var globalDesignationId;
var globalDepartmentName;
var newspapersGlobalArr =[];
var channelGlobalArr =[];
var callCenterGlobalArr=[];
var globalDepartmentIdsArr=[];
var globalsocialMediaTypeIdsArr = [];
var globalAlertSeverityIdsArr = [];
var globalAlertStatusIdsArr = [];
var globalAlertSubTaskStatusIdsArr = [];
/*new four categories global variable name.*/
var globalMondayGrievanceTypeIdsArr = [];
var globalJanmabhoomiTypeIdsArr = [];
var globalSpecialGrievanceTypeIdsArr = [];
var globalGeneralGrievanceTypeIdsArr = [];
google.load("elements", "1", {
	packages: "transliteration"
});
$(document).on("click",".settingsIcon",function(e){
	$(this).closest(".panelDropDown").find(".settingsBlockDropDown").toggle();
	e.stopPropagation();
});
$(document).on("click",".setClose",function(){
	$(this).closest(".settingsBlockDropDown").hide();
});
$(document).on("click",function(){
	$(".documentCloseClass").hide();
});
$(document).on("click",".documentCloseClass",function(e){
	e.stopPropagation();
});
onLoadPrintAndChannel();
onLoadCallcenter();
onLoadSocialMedia();
onLoadAlertSeverity();
onLoadAlertStatus();
onLoadAlertSubTaskStatus();
onLoadMondayGrievanceType();
onLoadJanmabhoomiType();
onLoadSpecialGrievanceType();
onLoadGeneralGrievanceType();
$(document).on("click",".selectAllPaperCls",function(){
	if($(this).prop('checked')) {
		$(".newsPaperListCls").prop('checked', true);
		$(".chanelListCls").prop('checked', true);
		onLoadPrintAndChannel();

	}else{
		$(".newsPaperListCls").prop('checked', false);
		$(".chanelListCls").prop('checked', false);
		
		newspapersGlobalArr=[];
		channelGlobalArr=[];
	}
	
});
$(document).on("click",".selectAllcallcenterCls",function(){
	if($(this).prop('checked')) {
		$(".callcenterCls").prop('checked', true);
		onLoadCallcenter();

	}else{
		$(".callcenterCls").prop('checked', false);
		callCenterGlobalArr=[];
	}
	
});
$(document).on("click",".selectAllMediaType",function(){
	if($(this).prop('checked')) {
		$(".socialMediaCls").prop('checked', true);
		onLoadSocialMedia();

	}else{
		$(".socialMediaCls").prop('checked', false);
		globalsocialMediaTypeIdsArr = [];
	}
	
});
$(document).on("click",".selectAllSeverityType",function(){
	if($(this).prop('checked')) {
		$(".severityTypeCls").prop('checked', true);
		onLoadAlertSeverity();

	}else{
		$(".severityTypeCls").prop('checked', false);
		globalAlertSeverityIdsArr = [];
	}
	
});
$(document).on("click",".selectAllStatusType",function(){
	if($(this).prop('checked')) {
		$(".statusTypeCls").prop('checked', true);
		onLoadAlertStatus();

	}else{
		$(".statusTypeCls").prop('checked', false);
		globalAlertStatusIdsArr = [];
	}
	
});
$(document).on("click",".selectAllSubTaskType",function(){
	if($(this).prop('checked')) {
		$(".subTaskTypeCls").prop('checked', true);
		onLoadAlertSubTaskStatus();

	}else{
		$(".subTaskTypeCls").prop('checked', false);
		globalAlertSubTaskStatusIdsArr = [];
		
	}
	
});
$(document).on("click",".selectAllMondayGrievanceType",function(){
	if($(this).prop('checked')) {
		$(".mondayGrievanceCls").prop('checked', true);
		onLoadMondayGrievanceType();

	}else{
		$(".mondayGrievanceCls").prop('checked', false);
		globalMondayGrievanceTypeIdsArr=[];
		
	}
});
$(document).on("click",".selectAllJanmabhoomiType",function(){
	if($(this).prop('checked')) {
		$(".janmabhoomiCls").prop('checked', true);
		onLoadJanmabhoomiType();

	}else{
		$(".janmabhoomiCls").prop('checked', false);
		globalJanmabhoomiTypeIdsArr=[];
		
	}
});
$(document).on("click",".selectAllSpecialGrievanceType",function(){
	if($(this).prop('checked')) {
		$(".specialGrievanceCls").prop('checked', true);
		onLoadSpecialGrievanceType();

	}else{
		$(".specialGrievanceCls").prop('checked', false);
		globalSpecialGrievanceTypeIdsArr=[];
		
	}
});
$(document).on("click",".selectAllGeneralGrievanceType",function(){
	if($(this).prop('checked')) {
		$(".generalGrievanceCls").prop('checked', true);
		onLoadGeneralGrievanceType();

	}else{
		$(".generalGrievanceCls").prop('checked', false);
		globalGeneralGrievanceTypeIdsArr=[];
		
	}
});
function onLoadPrintAndChannel(){
	newspapersGlobalArr=[];
	channelGlobalArr=[];
	$(".newsPaperListCls").each(function(){
	if($(this).is(":checked"))
	{
		<c:forEach var="newsPaper" items="${newsPaperList}">
			var newspapersGlobalObj = '${newsPaper.id}'
			newspapersGlobalArr.push(newspapersGlobalObj)
		 </c:forEach>
	}
});

$(".chanelListCls").each(function(){
	if($(this).is(":checked"))
	{
		 <c:forEach items="${chanelListNew}"  var="channels">
			var channelGlobalObj = '${channels.id}'
			channelGlobalArr.push(channelGlobalObj)
		 </c:forEach>
	}
});

}
function onLoadCallcenter(){
	callCenterGlobalArr =[];
	$(".callcenterCls").each(function(){
	if($(this).is(":checked"))
	{
		
		<c:forEach items="${alertCallCenterTypeIdList}"  var="callCenterType">
			var callCenterObj = '${callCenterType.id}'
			callCenterGlobalArr.push(callCenterObj)
		 </c:forEach>
		 
	}
});
}
function onLoadSocialMedia(){
	globalsocialMediaTypeIdsArr = [];
	$(".socialMediaCls").each(function(){
	if($(this).is(":checked"))
	{
		 <c:forEach items="${socailMediaTypeList}"  var="socailMediaType">
			var socialGlobalObj = '${socailMediaType.id}'
			globalsocialMediaTypeIdsArr.push(socialGlobalObj)
		 </c:forEach>
	}
});
}
function onLoadAlertSeverity(){
	globalAlertSeverityIdsArr=[];
	$(".severityTypeCls").each(function(){
		if($(this).is(":checked"))
		{
			globalAlertSeverityIdsArr.push($(this).attr("attr_val"));
		}
	});
} 
function onLoadAlertStatus(){
	globalAlertStatusIdsArr=[];
	$(".statusTypeCls").each(function(){
		if($(this).is(":checked"))
		{
			globalAlertStatusIdsArr.push($(this).attr("attr_val"));
		}
	});
}
function onLoadAlertSubTaskStatus(){
	globalAlertSubTaskStatusIdsArr=[];
	$(".subTaskTypeCls").each(function(){
		if($(this).is(":checked"))
		{
			globalAlertSubTaskStatusIdsArr.push($(this).attr("attr_val"));
		}
	});
}
function onLoadMondayGrievanceType(){
	globalMondayGrievanceTypeIdsArr = [];
	$(".mondayGrievanceCls").each(function(){
	if($(this).is(":checked"))
	{
		var mondayGrievanceObj='';
		<c:forEach items="${mondayGrievanceTypeList}"  var="mondayGrievanceType">
			mondayGrievanceObj = '${mondayGrievanceType.id}'
			globalMondayGrievanceTypeIdsArr.push(mondayGrievanceObj)
		 </c:forEach>
	}
});
}
function onLoadJanmabhoomiType(){
	globalJanmabhoomiTypeIdsArr = [];
	$(".janmabhoomiCls").each(function(){
	if($(this).is(":checked"))
	{
		var janmabhoomiTypeObj='';
		<c:forEach items="${janmabhoomiTypeList}"  var="janmabhoomiType">
			janmabhoomiTypeObj = '${janmabhoomiType.id}'
			globalJanmabhoomiTypeIdsArr.push(janmabhoomiTypeObj)
		 </c:forEach>
	}
});
}
function onLoadSpecialGrievanceType(){
	globalSpecialGrievanceTypeIdsArr = [];
	$(".specialGrievanceCls").each(function(){
	if($(this).is(":checked"))
	{
		var specialGrievanceTypeObj='';
		<c:forEach items="${specialGrievanceTypeList}"  var="specialGrievanceType">
			specialGrievanceTypeObj = '${specialGrievanceType.id}'
			globalSpecialGrievanceTypeIdsArr.push(specialGrievanceTypeObj)
		 </c:forEach>
	}
});
}
function onLoadGeneralGrievanceType(){
	globalGeneralGrievanceTypeIdsArr = [];
	$(".generalGrievanceCls").each(function(){
	if($(this).is(":checked"))
	{
		var generalGrievanceTypeObj='';
		<c:forEach items="${generalGrievanceTypeList}"  var="generalGrievanceType">
			generalGrievanceTypeObj = '${generalGrievanceType.id}'
			globalGeneralGrievanceTypeIdsArr.push(generalGrievanceTypeObj)
		 </c:forEach>
	}
});
}
$(document).on("click",".checkedSelected",function(){
		onLoadPrintAndChannel();
		onLoadCallcenter();
		onLoadSocialMedia();
		onLoadAlertSeverity();
		onLoadAlertStatus();
		onLoadAlertSubTaskStatus();
		onLoadMondayGrievanceType();
		onLoadJanmabhoomiType();
		onLoadSpecialGrievanceType();
		onLoadGeneralGrievanceType();
});
onLoadCallsAMU();
</script>
</body>
</html>