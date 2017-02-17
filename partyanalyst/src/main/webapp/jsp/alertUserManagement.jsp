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
<div  class="AMS AUMS">
	
	<section class="m_top20">
		<div class="container">
			<div class="row">
				<div class="input-group dateRangePickerCls m_top5 pull-right">
					<input type="text" class="form-control " style="width:180px" id="dateRangePickerAUM">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style">
					<div class="col-md-9 col-xs-12 col-sm-4">
						<div class="panel panel-default">
							<div class="panel-heading headingColor">
								<h4 class="panel-title text-capital fontColor">My Alerts - Status Overview</h4>
							</div>
							<div class="panel-body">
								<div id="alertStatusOverview"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-4">
						<div class="panel panel-default">
							<div class="panel-body" style="height: 370px;">
								<div style="text-align: center; margin-top: 40px;">
									<img src="newCoreDashBoard/img/govtAlertBill.png"/>
								</div>
								<div id="overDepartmentWiseAlertOverview"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style">
					<div class="panel panel-default">
						<div class="panel-heading headingColor">
							<h4 class="panel-title text-capital fontColor">Department Wise Alert Status</h4>
						</div>
						<div class="panel-body">
							<div id="departmentWiseAlertGraphDiv"></div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style detailedBlockShow" style="display:none;">
					<div class="panel panel-default">
						<div class="panel-heading headingColor">
							<h4 class="panel-title text-capital fontColor">Detailed Information</h4>
						</div>
						<div class="panel-body">
							<div id="detailedInfoBlockDiv"></div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-4">
										<select class="form-control" id="alertStatusDivId" attr_type="alertStatus">
											<option value="0">Show All (Notified/Progress/Completed...)</option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-12">
										<div class="input-group dateRangePickerCls m_top5">
												<input type="text" class="form-control" style="width:230px;" id="dateRangePickerDistrictAlertBlock">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-calendar"></i>
												</span>
											</div>
									</div>
									<div class="col-md-3 col-xs-12 col-xs-6">
										<button type="button" class="btn btn-success getDetailsForDistrictWiseAlerts" style="background-color:#016500; font-weight: bold;">Get Details</button>
									</div>
								</div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div class="row">
									<div id="districtAlertStatusInfo" ></div>
								</div>
							</div>
						</div>
					</div>
				</div>
					<div class="col-md-12 col-xs-12 col-sm-12 bg_Style ">
					<div class="panel panel-default">
						<div class="panel-heading headingColor">
							<h4 class="panel-title text-capital fontColor">Subordinate - Alerts Overview</h4>
						</div>
						<div class="panel-body">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="row">
								<div class="col-md-3 col-xs-12 col-xs-6">
									<label>Select Designation</label>
									<select class="form-control chosen-select" id="designationDivId">
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-xs-6">
									<label>Select Level</label>
									<select class="form-control chosen-select" id="subOrdianatelevelId">
									<option value="0">Select Level</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-xs-6 m_top20">
									<div class="input-group dateRangePickerCls m_top5 pull-right">
										<input type="text" class="form-control " style="width:180px" id="dateRangePickerSubOrdinateBlock">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
									</div>
								</div>
								<div class="col-md-3 col-xs-12 col-xs-6 m_top20">
									<button type="button" class="btn btn-success getDetailsForSubOrdinate" style="background-color:#016500; font-weight: bold;">Get Details</button>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="row">
								<div id="mandalWiseDetailsSubOrdBlock"></div>
							</div>
						</div>
						</div>
					</div>
				</div>
				
			</div>	
		</div>
	</section>
<!--Main Div End-->

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
			<form id="alertAssign" name="alertAssignForm">
				<!--<div class="row m_top20">
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
				</div>-->
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
										<label>Change Status</label>
										<select class="chosenSelect" id="changeStatusId" attr_type="changeStatus"><option value="0">Select Status</option></select>
									</div>
									<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
										<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/><span style="color:red;" id="errMsgImgId"></span>
									</div>
									<div class="col-md-4 col-xs-12 col-sm-6">
										<button class="btn btn-success btn-block text-capital m_top20" id="assignOfficerId" type="button">update alert </button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--<input type="hidden" id="hiddenAlertId" name="alertAssigningVO.alertId"></input>-->
			</form>
		  </div>
		</div>
	  </div>
	</div>
	<!-- Alert Details Modal End-->
	
<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystem.js" type="text/javascript"></script>

<script type="text/javascript">
initializeFile();
$(".chosenSelect").chosen({width:'100%'});
google.load("elements", "1", {
	packages: "transliteration"
 }); 
 
var control;
var lang;

function languageChangeHandler(){
	   var lang1 = $("input[name=Lang]:checked").val();
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
function onLoad() {
	   lang = $("input[name=Lang]:checked").val();
		var options = {
			sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage:[''+lang+''],
			shortcutKey: 'alt+t',
			transliterationEnabled: true
		};
		// Create an instance on TransliterationControl with the required options.
		control = new google.elements.transliteration.TransliterationControl(options);
		// Enable transliteration in the textbox with id 'descrptionId'.
	 	if ($('#alertDescId').length){
			control.makeTransliteratable(['alertDescId']);
		}
   }
/*global Function and variables Start*/
var currentFromDate = moment().subtract(1,"month").format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var detailedfromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var detailedtoDate=moment().endOf('year').format("DD/MM/YYYY");

var globalStateId = 36;  
var departmentIdsList="";
var lvlValueGlobal="";
var paperIdArr = [];
var chanelIdArr = [];

$("#dateRangePickerAUM").daterangepicker({
	opens: 'left',
	startDate: currentFromDate,
	endDate: currentToDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
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
$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
	currentFromDate = picker.startDate.format('DD/MM/YYYY');
	currentToDate = picker.endDate.format('DD/MM/YYYY');
	getTotalAlertGroupByStatusForOneDept();
	getTotalAlertGroutByDeptThenStatus();
});

$("#dateRangePickerSubOrdinateBlock, #dateRangePickerDistrictAlertBlock").daterangepicker({
	opens: 'left',
	startDate: detailedfromDate,
	endDate: detailedtoDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'All':[moment().subtract(20, 'years').startOf('year'), moment().endOf('year')],
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
var dates= $("#dateRangePickerSubOrdinateBlock, #dateRangePickerDistrictAlertBlock").val();

if(dates == "01/01/1997 - 31/12/2017"){
	$("#dateRangePickerSubOrdinateBlock, #dateRangePickerDistrictAlertBlock").val('ALL');
}

$(document).on("click",".ranges li",function(){	
	var selectedDay=$(this).html().trim();
		if(selectedDay == "All"){
			$("#dateRangePickerDetailedBlock").val('ALL');
			$("#dateRangePickerDistrictAlertBlock").val('ALL');
			$("#dateRangePickerSubOrdinateBlock").val('ALL');
		}
			
});


getTotalAlertGroupByStatusForOneDept();
getTotalAlertGroutByDeptThenStatus();

getDeptIdAndNameListForUser();

getAlertStatusForUser();
getStatusWiseAlertDetails(0);
getAssignedDesignationsForUser();

$(document).on("click",".getDetailsForDistrictWiseAlerts",function(){	
	var statusId=$("#alertStatusDivId").val();
	getStatusWiseAlertDetails(statusId);
			
});


$(document).on("change","#designationDivId",function(){	
	var designnationId=$("#designationDivId").val();
	getSubLevelsForUser(designnationId);
			
});
$(document).on("click",".getDetailsForSubOrdinate",function(){	
	var designnationId=$("#designationDivId").val();
	var levelId=$("#subOrdianatelevelId").val();
	var levelText=$("#subOrdianatelevelId option:selected").html();
	getSubOrdinatesAlertsOverView(designnationId,levelId,levelText);
			
});

$(document).on("click",".detailedBlockDiv",function(){
	$(".detailedBlockShow").show();
	var departmentId = $(this).attr("attr_department_id");
	getGovtDeptLevelForDeptAndUser(departmentId);
	getAlertCountLocationWiseThenStatusWise(departmentId,3);
	
});	 

$(document).on("change","#departmentId",function(){
	var departmentId = $(this).val();
	getGovtDeptLevelForDeptAndUser(departmentId);
});
function getDeptIdAndNameListForUser(){
	
    var jObj ={
      userId:1,
    }
    $.ajax({
      type:'GET',
      url: 'getDeptIdAndNameListForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		 if(result != null && result.length > 0){
			departmentIdsList=result;
		}
    });
}	
function getGovtDeptLevelForDeptAndUser(departmentId)
{
	$('#levelId').html('');
	var userId=1;
    var jObj ={
      departmentId:departmentId,
	  userId:userId
    }
    $.ajax({
      type:'GET',
      url: 'getGovtDeptLevelForDeptAndUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		var str='';
		 if(result != null && result.length > 0){
			str+='<option value="0">Select level</option>';
			for(var i in result){
				lvlValueGlobal=result;
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			
		}
		$('#levelId').html(str);
    });
}

function getAlertStatusForUser(){
	
	var typeC = $("#changeStatusId").attr("attr_type")
	
	var jObj ={
     
    }
    $.ajax({
      type:'GET',
      url: 'getAlertStatusForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildChangeStatusDetails(result);
			buildAlertStatusDetails(result);
		}	
			
	});
}
function buildChangeStatusDetails(result){
	var str = '';
		str+='<option value="0">Select Status</option>';
	for(var i in result){
		if(result[i].id != null && result[i].id > 1){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
			
	}
	$("#changeStatusId").html(str);
	$("#changeStatusId").trigger("chosen:updated");
}
function buildAlertStatusDetails(result){
	var str = '';
		str+='<option value="0">Show All (Notified/Progress/Completed...)</option>';
	for(var i in result){
		if(result[i].id != null && result[i].id > 1){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
			
	}
	$("#alertStatusDivId").html(str);
	$("#alertStatusDivId").trigger("chosen:updated");
	
}
function getAssignedDesignationsForUser(){
	
	var jObj ={
     
    }
    $.ajax({
      type:'GET',
      url: 'getAssignedDesignationsForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		var str = '';
		if(result !=null && result.length>0){
			str+='<option value="0">Select Designation</option>';
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].designation+'</option>';
			}
		}
			
		$("#designationDivId").html(str);
		$("#designationDivId").trigger("chosen:updated");
	});
}

function getSubLevelsForUser(designnationId)
{
	
	var jObj ={
		designationId : designnationId
    }
    $.ajax({
      type:'GET',
      url: 'getSubLevelsForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		var str = '';
		if(result !=null && result.length>0){
			str+='<option value="0">Select Level</option>';
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
			
		$("#subOrdianatelevelId").html(str);
		$("#subOrdianatelevelId").trigger("chosen:updated");
	});
}
function getTotalAlertGroupByStatusForOneDept()
{
	$("#alertStatusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
    var jObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusForOneDeptAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#alertStatusOverview").html('');
		 if(result != null && result.length > 0){
			buildTotalAlertGroupByStatusForOneDept(result);
		}else{
			$("#alertStatusOverview").html('NO DATA AVAILABLE')
		} 
    });
}


function buildTotalAlertGroupByStatusForOneDept(result){
	var str='';
	var totalAlert = 0;
		str+='<div class="col-md-5 col-xs-12 col-sm-12">';
			str+='<div id="totalAlertGroupByStatusForOneDeptDiv"></div>';
			str+='<div id="TotalAlertsView"></div>';						
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-4" style="margin-top:25px;">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<tr>';
						str+='<th style="font-size: 16px;"><b>Status</b></th>';
						str+='<th style="font-size: 16px;"><b>Total</b></th>';
						str+='<th style="font-size: 16px;"><b>%</b></th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					totalAlert+=result[i].count;
					str+='<tr>';
						str+='<td><span class="colorSpecify" style="background-color:'+result[i].color+';"></span>&nbsp;&nbsp;'+result[i].status+'</td>';
						str+='<td>'+result[i].count+'</td>';
						str+='<td>'+result[i].statusPercent+'</td>';
					str+='</tr>';
				}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	$("#alertStatusOverview").html(str);
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].statusPercent;
		statusName = result[i].status;
		var cnt = result[i].count;
		var color=result[i].color;
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,
			color:color
			
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		var chart = new Highcharts.Chart({
			//colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
			chart: {
				renderTo: 'totalAlertGroupByStatusForOneDeptDiv',
				type: 'pie',
				height:'300',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			 tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "Total Alerts - "+cnt+"";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: true,
						formatter: function() {
							return Math.round(this.y,2) + ' %';
						},
						distance: -15,
						color:'black'
					},
					/* point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					} */
				},
				pie: {
					innerSize: 130,
					depth: 95,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		},
	
		function(chart) { // on complete
			var textX = chart.plotLeft + (chart.plotWidth  * 0.5);
			var textY = chart.plotTop  + (chart.plotHeight * 0.5);

			var span = '<span id="pieChartInfoText" style="position:absolute; text-align:center;">';
			span += '<span style="font-size: 18px">TOTAL</span><br>';
			span += '<span style="font-size: 14px;" >'+totalAlert+'</span>';
			span += '</span>';

			$("#TotalAlertsView").append(span);
			span = $('#pieChartInfoText');
			span.css('left', textX + (span.width() * -0.5));
			span.css('top', textY + (span.height() * -0.5));
		});
	});	
}

function getTotalAlertGroutByDeptThenStatus()
{
	$("#overDepartmentWiseAlertOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#departmentWiseAlertGraphDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr       

    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroutByDeptThenStatusAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#overDepartmentWiseAlertOverview").html('');
		$("#departmentWiseAlertGraphDiv").html('');
		if(result != null && result.length > 0){
			buildTotalAlertGroutByDeptThenStatus(result);
		}else{
			$("#overDepartmentWiseAlertOverview").html('NO DATA AVAILABLE');
			$("#departmentWiseAlertGraphDiv").html('NO DATA AVAILABLE');
		} 
    });
}
function buildTotalAlertGroutByDeptThenStatus(result){
	
	var str='';
	var str1='';
	var totalAlertCount=0;
	for(var i in result){
		totalAlertCount+=result[i].count;
	}
	str+='<div class="m_top10">';
		str+='<p style="font-size:20px;text-align:center">Hi Officer</p>';
		str+='<p style="font-size:20px;text-align:center">You Have <span style="font-size:30px;">'+totalAlertCount+'</span> New Alerts</p>';
	str+='</div>';
	str+='<div class="m_top10">';
		str+='<table class="table tableBorLefRig">';
			str+='<tbody>';
			for(var j in result){
				str+='<tr>';
					str+='<th>'+result[j].status+'</th>';
					str+='<th style="text-align:center"><span class="colorStyleAlert">'+result[j].count+'</span></th>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#overDepartmentWiseAlertOverview").html(str);
	
	str1+='<div class="row">';
	for(var i in result){
		 if($(window).width() < 500){
			 str1+='<div class="col-md-4 col-xs-12 col-sm-12 thumbnail" style="margin-left: 10px;">';
		 }else{
			  str1+='<div class="col-md-4 col-xs-12 col-sm-12 thumbnail" style="margin-left: 10px;width:32%">';
		 }
		
		str1+='<h4 style="text-align:center;color:#777;">'+result[i].status+'</h4>';
		str1+='<div id="departmentWiseDiv'+i+'"  ></div>';
		str1+='<div id="TotalAlertsDepView'+i+'"></div>';	
		str1+='<table class="table tableGraph">';
			str1+='<thead>';
				str1+='<tr>';
					str1+='<th style="font-size: 16px;"><b>Status</b></th>';
					str1+='<th style="font-size: 16px;"><b>Total</b></th>';
					str1+='<th style="font-size: 16px;"><b>%</b></th>';
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				if(result[i].subList1 !=null && result[i].subList1.length>0){
					
					for(var j in result[i].subList1){
						str1+='<tr>';
							str1+='<td><span class="colorSpecify" style="background-color:'+result[i].subList1[j].color+';"></span>&nbsp;&nbsp;'+result[i].subList1[j].category+'</td>';
							str1+='<td>'+result[i].subList1[j].categoryCount+'</td>';
							str1+='<td>'+result[i].subList1[j].statusPercent+'</td>';
						str1+='</tr>';
					}
				
				}
		str1+='</tbody>';
		str1+='</table>';
		str1+='<div class="m_top20" style="text-align:center;"><button type="button" class="btn btn-default btn-sm buttonCustomStyle detailedBlockDiv" attr_department_id="'+result[i].statusId+'">Detailed Information</button></div>';	
		str1+='</div>';	
	}
	str1+='</div>';
	$("#departmentWiseAlertGraphDiv").html(str1);
	
	for(var i in result){
		var deptStatusOverviewArr =[];
		var alertCount = result[i].count;
			if(result[i].subList1 !=null && result[i].subList1.length>0){
					for(var j in result[i].subList1){
						statusPercent = result[i].subList1[j].statusPercent;
						statusName = result[i].subList1[j].status;
						var cnt = result[i].subList1[j].count;
						var color=result[i].subList1[j].color;
						var obj = {
							name: statusName,
							y:statusPercent,
							count:alertCount,
							color:color
							
						}
						deptStatusOverviewArr.push(obj);
			
					}
			
			
			}
	
		$(function() {
			var chart = new Highcharts.Chart({
				//colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
				chart: {
					renderTo: 'departmentWiseDiv'+i+'',
					type: 'pie',
					height:'250',
					width:'320',
					options3d: {
						enabled: true,
						alpha: 25
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				 tooltip: {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "Total Alerts - "+cnt+"";
					}  
				}, 
				plotOptions: {
					series: {
						dataLabels: {
							enabled: true,
							formatter: function() {
								return Math.round(this.y,2) + ' %';
							},
							distance: -15,
							color:'black'
						},
						/* point:{
							events:{
								click:function(){
									getData(this.count,this.sts);     
								}
							}
						} */
					},
					pie: {
						innerSize: 150,
						depth: 90,
						dataLabels:{
							enabled: false,
							  formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,2)+ '%';
									}
								} 
						},
						showInLegend: false
					},
				},
				series: [{
					data: deptStatusOverviewArr
				}]
			},
		
			function(chart) { // on complete
				var textX = chart.plotLeft + (chart.plotWidth  * 0.5);
				var textY = chart.plotTop  + (chart.plotHeight * 0.5);

				var span = '<span id="pieChartInfoText1'+i+'" style="position:absolute; text-align:center;">';
				span += '<span style="font-size: 18px">TOTAL</span><br>';
				span += '<span style="font-size: 14px;" >'+alertCount+'</span>';
				span += '</span>';

				$("#TotalAlertsDepView"+i).append(span);
				span = $('#pieChartInfoText1'+i);
				span.css('left', textX + (span.width() * -0.5));
				span.css('top', textY + (span.height() * -0.5));
			});
		});	
}	

}
	function getAlertCountLocationWiseThenStatusWise(departmentId,lvlValue){
		$("#districtWiseDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var jObj ={
			
		  fromDate:detailedfromDate,
		  toDate:detailedtoDate,
		  stateId:globalStateId,
		  govtDepartmentId:departmentId,
		  lvlValue:lvlValue,
		  paperIdArr:paperIdArr,
		  chanelIdArr:chanelIdArr
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertCountLocationWiseThenStatusWiseAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			$("#districtWiseDetailsBlock").html('');
			buildAlertCountLocationWiseThenStatusWise(result,departmentId,lvlValue);
			
		});
		
	}
	
	function buildAlertCountLocationWiseThenStatusWise(result,departmentId,lvlValue){
		
		var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="row">';
					str+='<div class="col-md-3 col-xs-12 col-xs-6">';
						str+='<label>Select Department</label>';
						str+='<select class="form-control chosen-select" id="departmentId">';
							str+='<option value="0">Select Department</option>';
							if(departmentIdsList != null && departmentIdsList.length>0){
								for(var s in departmentIdsList){
									if(departmentIdsList[s].id == departmentId){
										str+='<option value="'+departmentIdsList[s].id+'" selected="selected">'+departmentIdsList[s].name+'</option>';
									}
									else{
										str+='<option value="'+departmentIdsList[s].id+'">'+departmentIdsList[s].name+'</option>';
									}
								}
							}
						str+='</select>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-xs-6">';
						str+='<label>Select Level</label>';
						str+='<select class="form-control chosen-select" id="levelId">';
						str+='<option value="0"> Select Level </option>';
						if(lvlValueGlobal != null && lvlValueGlobal.length>0){
								for(var s in lvlValueGlobal){
									if(lvlValueGlobal[s].id == lvlValue){
										str+='<option value="'+lvlValueGlobal[s].id+'" selected="selected">'+lvlValueGlobal[s].name+'</option>';
									}
									else{
										str+='<option value="'+lvlValueGlobal[s].id+'">'+lvlValueGlobal[s].name+'</option>';
									}
								}
							}
						str+='</select>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-xs-6 m_top20">';
						str+='<div class="input-group dateRangePickerCls m_top5 pull-right">';
							str+='<input type="text" class="form-control " style="width:180px" id="dateRangePickerDetailedBlock">';
							str+='<span class="input-group-addon">';
								str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='</span>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-xs-6 m_top20">';
						str+='<button type="button" class="btn btn-success getDetailsClick" style="background-color:#016500; font-weight: bold;">Get Details</button>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<div class="row">';
					str+='<div id="districtWiseDetailsBlock"><div>';
				str+='</div>';
			str+='</div>';
		$("#detailedInfoBlockDiv").html(str);
		
			$("#dateRangePickerDetailedBlock").daterangepicker({
				opens: 'left',
				startDate: detailedfromDate,
				endDate: detailedtoDate,
				locale: {
				  format: 'DD/MM/YYYY'
				},
				ranges: {
					'All':[moment().subtract(20, 'years').startOf('year'), moment().endOf('year')],
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
			var dates= $("#dateRangePickerDetailedBlock").val();
			
			if(dates == "01/01/1997 - 31/12/2017"){
				$("#dateRangePickerDetailedBlock").val('ALL');
			}
			
		 if(result != null && result.length > 0){	
		var str1='';
		str1+='<table class="table detailedTableStyle">';
			str1+='<thead>';
				str1+='<tr>';
				str1+='<th>District Name</th>';
				str1+='<th>Total Alerts</th>';
					if(result[0].subList1 !=null && result[0].subList1.length>0){
						
						for(var j in result[0].subList1){
							if(j != 0)
							{
								str1+='<th style="background-color:'+result[0].subList1[j].color+' !important;">'+result[0].subList1[j].category+'</th>';
							}
							
							
						}
						
					}
				str1+='<th></th>';	
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td>'+result[i].status+'</td>';
						str1+='<td>'+result[i].count+'</td>';
						if(result[i].subList1 !=null && result[i].subList1.length>0){
							
							for(var j in result[i].subList1){
								if(j != 0)
								{
									str1+='<td style="background-color:'+result[i].subList1[j].color+' !important;">'+result[i].subList1[j].categoryCount+'</td>';
								}
							}
						}
							str1+='<td><button type="button" class="btn btn-success btn-xs">View Details</button></td>';
					str1+='</tr>';
				}
			str1+='</tbody>';
		str1+='</table>';
		$("#districtWiseDetailsBlock").html(str1);
	}else{
		$("#districtWiseDetailsBlock").html("No Data Available");
	}
	}
	$(document).on("click",".getDetailsClick",function(){
		$("#districtWiseDetailsBlock").html('');
		var departmentId = $("#departmentId").val();
		var levelId = $("#levelId").val();
		getAlertCountLocationWiseThenStatusWise(departmentId,levelId);
		
	});
	
function getStatusWiseAlertDetails(statusId){
	$("#districtAlertStatusInfo").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var jObj ={
      fromDate : detailedfromDate,
      toDate : detailedtoDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
      statusId : statusId       

    }
    $.ajax({
      type:'GET',
      url: 'getStatusWiseAlertDetailsAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#districtAlertStatusInfo").html('');
		buildStatusWiseAlertDetails(result);
	});
}

function buildStatusWiseAlertDetails(result){
	
	if(result != null && result.length > 0){
		var str1='';
			str1+='<div class="table-responsive">';
			str1+='<table class="table detailedTableStyle">';
				str1+='<thead>';
					str1+='<tr>';
						str1+='<th>Alert Severity</th>';
						str1+='<th>Alert Title</th>';
						str1+='<th>Alert Created Date</th>';
						str1+='<th>Last Updated Date</th>';
						str1+='<th>Lag Days</th>';
						str1+='<th>Current Status</th>';
						str1+='<th></th>';
					str1+='</tr>';
					str1+='</thead>';
					str1+='<tbody>';
					for(var i in result){
						str1+='<tr>';
							str1+='<td>';
							str1+='<div class="row">';
							str1+='<div class="col-md-1 col-xs-12 col-xs-1">';
							if(result[i].severityStr == "Medium"){
								str1+='<p class="circleAlertStatus" style="border:3px solid #ff8800"></p>';
							}
							if(result[i].severityStr == "High"){
								str1+='<p class="circleAlertStatus" style="border:3px solid #ff0c0c"></p>';
							}
							if(result[i].severityStr == "Low"){
								str1+='<p class="circleAlertStatus" style="border:3px solid #4a5865"></p>';
							}
								
							str1+='</div>';
							str1+='<div class="col-md-1 col-xs-12 col-xs-1">';
								str1+='<p>'+result[i].severityStr+'</p>';
							str1+='</div>';
							
							str1+='</div>';
							str1+='</td>';
							str1+='<td>';
								if(result[i].title !=null && result[i].title.length>25){
										str1+='<p  style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].title+'" >'+result[i].title.substring(0,25)+'...</p>';
								}else{
									str1+='<p>'+result[i].title+' </p>';
								}
							str1+='</td>';
							str1+='<td>'+result[i].date1+'</td>';
							str1+='<td>'+result[i].date2+'</td>';
							str1+='<td>'+result[i].noOfDays+'</td>';
							str1+='<td><span class="colorSpecify" style="background-color:'+result[i].color+'"></span> '+result[i].status+'</td>';
							str1+='<td><button type="button" class="btn btn-success btn-xs alertDetailsModalCls" attr_alert_Id="'+result[i].alertId+'" attr_status_id="'+result[i].statusId+'">Alert Details</button></td>';
						str1+='</tr>';
					}
						
					str1+='</tbody>';
				
			str1+='</table>';
			str1+='</div>';
		
		$("#districtAlertStatusInfo").html(str1);	
		
	}else{
		$("#districtAlertStatusInfo").html("No Data Available");	
	}
	$('[data-toggle="tooltip"]').tooltip();
	
}


function getSubOrdinatesAlertsOverView(designnationId,levelId,levelText)
{
	$("#mandalWiseDetailsSubOrdBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
		designationId : designnationId,
		levelId : levelId,
		fromDate : detailedfromDate,
		toDate : detailedtoDate
    }
    $.ajax({
      type:'GET',
      url: 'getSubOrdinatesAlertsOverViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#mandalWiseDetailsSubOrdBlock").html('');
		buildSubOrdinatesAlertsOverView(result,levelText);
	});
}


	function buildSubOrdinatesAlertsOverView(result,levelText){
		
		if(result != null && result.length > 0){	
			var str1='';
			str1+='<table class="table detailedTableStyle">';
				str1+='<thead>';
					str1+='<tr>';
					str1+='<th>'+levelText+'</th>';
					str1+='<th>Total Alerts</th>';
						if(result[0].govtDeptList !=null && result[0].govtDeptList.length>0){
							
							for(var j in result[0].govtDeptList){
									str1+='<th style="background-color:'+result[0].govtDeptList[j].color+' !important;">'+result[0].govtDeptList[j].name+'</th>';
								
							}
							
						}
					str1+='<th></th>';	
					str1+='</tr>';
				str1+='</thead>';
				str1+='<tbody>';
					for(var i in result){
						str1+='<tr>';
							str1+='<td>'+result[i].name+'</td>';
							str1+='<td>'+result[i].count+'</td>';
							if(result[i].govtDeptList !=null && result[i].govtDeptList.length>0){
								
								for(var j in result[i].govtDeptList){
										str1+='<td style="background-color:'+result[i].govtDeptList[j].color+' !important;">'+result[i].govtDeptList[j].count+'</td>';
								}
							}
								str1+='<td><button type="button" class="btn btn-success btn-xs">View Details</button></td>';
						str1+='</tr>';
					}
				str1+='</tbody>';
			str1+='</table>';
			$("#mandalWiseDetailsSubOrdBlock").html(str1);
		}else{
			$("#mandalWiseDetailsSubOrdBlock").html("No Data Available");
		}
	}

	/* Alert Details Modal Start*/
$(document).on("click",".alertDetailsModalCls",function(){
	$("#alertDetailsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	
	
	var alertId = $(this).attr("attr_alert_Id");
	var alrtStsId = $(this).attr("attr_status_id");
	if(alrtStsId != null && alrtStsId == 1)
		$("#alertAssign").show();
	
	$("#hiddenAlertId").val(alertId);   //3725
	getAlertData(alertId);
	getInvolvedMembersDetilas(alertId);
	getAlertStatusCommentsTrackingDetails(alertId);
	
});
function getAlertData(alertId)
{
	$("#alertCandidateDataId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj =
	{
		alertId  :alertId,
		task : ""
	}
	$.ajax({
			  type:'GET',
			  url: 'getAlertsDataAction.action',
			  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null)
		{
			buildAlertData(result);
		}
	});
}
function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId = result[i].categoryId;
		
		$("#typeId").html('<b>'+result[i].alertType+'</b>');
		$("#createdDate").html('<b>'+result[i].date+'</b>');
		$("#alertStatus").html('<b>'+result[i].status+'</b>');
		if(severityTdId ==1){
			$("#severityTdId").show();
		}else{
			$("#severityTdId").hide();
		}
		$(".severityIdColorCls").addClass('<b>'+result[i].severity+'</b>');
		$("#severityId").html('<b>'+result[i].severity+'</b>');
		$("#levelId").html('<b>'+result[i].regionScope+'</b>');
		
		var location ='';
			
		if(result[i].locationVO.stateId !=null){
			location +='<b>'+result[i].locationVO.state+' ';
		}
		if(result[i].locationVO.districtId !=null){
			location +=' , '+result[i].locationVO.districtName+' District ';
		}
		if(result[i].locationVO.constituencyId !=null){
			location +=' , '+result[i].locationVO.constituencyName+' Constituency ';
		}
		
		if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
			location +=' , '+result[i].locationVO.localEleBodyName+' Municipality ';
		}
		if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
			location +=' , '+result[i].locationVO.tehsilName+' Mandal ';
		}
		if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
			location +=' , '+result[i].locationVO.wardName+' Ward ';
		}
		if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
			location +=' , '+result[i].locationVO.villageName+' Panchayat </b>';
		}
		
		$("#LocationId").html(''+location+'');
		
		$("#titleId,#mainTitleId").html('<b>'+result[i].title+'</b>');
		
		$("#descriptionId").html('<b>'+result[i].desc+'</b>');
		
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			$(".imageUrlUlCls").html("<li class='articleDetailsCls' attr_articleId="+result[i].alertCategoryTypeId+" style='cursor:pointer'><img src='http://mytdp.com/NewsReaderImages/"+result[i].imageUrl+"' style='width: 150px; height: 150px;'></img></li>");
			$("#imageUrlTrId").show();
		}else{
			$(".imageUrlUlCls").html("");
			$("#imageUrlTrId").hide();
		}
		
		//buildAlertCandidateData(result[i].subList,result[i].categoryId);
	}
}

function getInvolvedMembersDetilas(alertId){
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		  type:'GET',
		  url: 'getInvolvedMembersInAlertAction.action',
		  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result == null || result.length == 0)
		{
			$("#alertCandidateDataId").html('No Involved Members..');
		}else{
			buildAlertCandidateData(result);
		}
	});
}
function buildAlertCandidateData(result,categoryId)
{

	var str='';
	
	for(var i in result)
	{
		str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10">';
			str+='<div class="media" style="border:1px solid #ddd;padding:8px;">';
				str+='<div class="media-left">';
					str+=' <img src="'+result[i].image+'" class="media-object img-circle"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;height:50px;"/>';
				str+=' </div>';
				str+=' <div class="media-body">';
					if(result[i].impactLevelId == 1)
					{
						str+=' <span class="label label-success pull-right" style="margin-top: 7px;">+ Ve</span>'; 
					}else if(result[i].impactLevelId == 2){
						str+=' <span class="label label-danger pull-right" style="margin-top: 7px;">- Ve</span>';
					}else{
						str+=' <span class="label label-neutral pull-right" style="margin-top: 7px;">N</span>';
					}
					if(result[i].name != null && result[i].name != "")
						str+=' <p class="text-capital"><span class="text-muted">Name :</span> <b>'+result[i].name+'</b></p>';
					str+=' <p class="text-capital"><span class="text-muted">Department: </span><b>'+result[i].status+'</b></p>';
					if(result[i].designation != null && result[i].designation != "")
					{
						str+='<p class="text-capital"><span class="text-muted">Designation</span>'+result[i].designation+'</p>';
					}
					str+='  <p>'+result[i].source+'</p>';
					if(result[i].dateStr !=null && result[i].dateStr.length>0){
						str+='<p><a>'+result[i].dateStr+'</a></p>';
					}
				str+='  </div>';
			str+='</div>';
	   str+=' </div>';
	}
	$("#involvedCandidatesCnt").html(result.length);	
	$("#alertCandidateDataId").html(str);
}
function getAlertStatusCommentsTrackingDetails(alertId)
{
	//var alertId = $("#hiddenAlertId").val();
	$("#alertCommentsDivIdNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj={
				alertId:alertId,
				task:""
			}
	$.ajax({
	  type : 'GET',
	  url : 'getStatusWiseCommentsTrackingAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result != null && result.length > 0)
			alertComments(result);	
		else
			$("#alertCommentsDivIdNew").html("");
	});
	
}
function alertComments(result)
{
	var docName = '';
	var extName = [];
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){  
		$(".cadreAlertCommentsDivId").show();
		str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px;">';
	}
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
		statusId = result[i].statusId;
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
			if(length == i)    
			{
				str+='<a role="button" class="alertCommentColapse" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
			}else{
				str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="false" aria-controls="collapse'+i+'">';
			}
			if(result[i].govtDeptList != null && result[i].govtDeptList.length > 0){
				if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';    
				str+='</h4>';
				}else{
					str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';
					str+='</h4>';
				} 
			}else{
				if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i>';    
				str+='</h4>';
				}else{
					str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-hourglass"></i>';
					str+='</h4>';
				} 
			}
			
				str+='</a>';  
			str+='</div>';
			if(length == i)  
			{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
			}else{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
			}
				str+='<div class="panel-body" style="padding:5px;">';
					str+='<div class="row">';
						
					if(result[i].govtDeptList != null && result[i].govtDeptList.length > 0){
						for(var j in result[i].govtDeptList){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].govtDeptList[j].dateStr
								var dateArr = date.split("-");
								var year = dateArr[0];
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';
										str+='<div class="arrow_box_left">';
										//for(var k in result[i].sublist2[j].sublist)
										//{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:</span><br>';
												//str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												//for(var l in result[i].govtDeptList[j].sublist[k])
												//{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].govtDeptList[j].source+'<br>';
												//}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].govtDeptList[j].comment+'</p>';
												/*if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';          
													}
													str+='</ul>';              
												}*/
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].govtDeptList[j].name+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										//}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}
					}else{
						str+='<div class="col-md-12 col-xs-12 col-sm-12"><div  style="height:200px;background-color:#EEE"><h4 class="panel-title text-capital text-center" style="padding-top:80px !important;">please assign alert to officer</h4></div></div>';
					}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#alertCommentsDivIdNew").html(str)
}
function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}

</script>
</body>
</html>