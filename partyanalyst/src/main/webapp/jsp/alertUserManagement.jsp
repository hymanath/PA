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
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="row">
										<div class="col-md-9 col-xs-12 col-sm-4">
											<div class="panel panel-default">
												<div class="panel-heading headingColor">
													<h4 class="panel-title text-capital fontColor">My Alerts - Status Overview</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-5 col-xs-12 col-sm-12">
															<div id="totalAlertGroupByStatusForOneDeptDiv"></div>
															<div id="TotalAlertsView"></div>	
														</div>
														<div class="col-md-6 col-xs-12 col-sm-4" style="margin-top:25px;">
															<div id="alertStatusOverview"></div>
														</div>
													</div>
													
													
												</div>
											</div>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-4">
											<div class="panel panel-default">
												<div class="panel-body" style="height: 370px;">
													<div class="alertImage">
														<img src="newCoreDashBoard/img/govtAlertBill.png" alt="govt alerts"/>
													</div>
													<div id="overDepartmentWiseAlertOverview"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							<!-- 1st Screen -->
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_ENTITLEMENT' ) }">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="panel panel-default">
										<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">Department Wise Alert Status</h4>
										</div>
										<div class="panel-body">
											<div id="departmentWiseAlertGraphDiv"></div>
										</div>
									</div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 detailedBlockShow" style="display:none;">
									<div class="panel panel-default">
										<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">Detailed Information</h4>
										</div>
										<div class="panel-body">
											<div id="detailedInfoBlockDiv"></div>
										</div>
									</div>
								</div>
							</c:if>
				
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_DISTRICT_OFFICER_ENTITLEMENT' ) }">
							
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="panel panel-default">
										<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">District Level - Departments Wise Overview</h4>
										</div>
										<div class="panel-body">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="row">
													<div class="col-md-3 col-xs-12 col-sm-12">
														<div class="input-group">
															<input type="text" class="form-control" id="dateRangePickerDistrictLevelDeptBlock">
															<span class="input-group-addon">
																<i class="glyphicon glyphicon-calendar"></i>
															</span>
														</div>
													</div>
													<div class="col-md-3 col-xs-12 col-xs-6">
														<button type="button" class="btn btn-success getDetailsForDistrictLevelDeptAlerts" style="background-color:#016500; font-weight: bold;">Get Details</button>
													</div>
												</div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<div id="districtLevelDeptOverview" ></div>
											</div>
										</div>
									</div>
								</div>
							
							</c:if>
				
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'GOVT_DEPARTMENT_DISTRICT_OFFICE_ENTITLEMENT' ) }">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<div class="row">
													<div class="col-md-3 col-xs-12 col-sm-4">
														<select class="chosenSelect" id="alertStatusDivId" attr_type="alertStatus">
															<option value="0">Show All (Notified/Progress/Completed...)</option>
														</select>
													</div>
													<div class="col-md-3 col-xs-12 col-sm-12">
														<div class="input-group">
															<input type="text" class="form-control" id="dateRangePickerDistrictAlertBlock">
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
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="panel panel-default">
										<div class="panel-heading headingColor">
											<h4 class="panel-title text-capital fontColor">Subordinate - Alerts Overview</h4>
										</div>
										<div class="panel-body">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<div class="row">
												<div class="col-md-3 col-xs-12 col-xs-6">
													<label>Select Designation</label>
													<select class="chosenSelect" id="designationDivId">
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-xs-6">
													<label>Select Level</label>
													<select class="chosenSelect" id="subOrdianatelevelId">
													<option value="0">Select Level</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-xs-6 m_top20">
													<div class="input-group m_top5 pull-right">
														<input type="text" class="form-control" id="dateRangePickerSubOrdinateBlock">
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
								</c:if>
								
							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</section>
<!--Main Div End-->
</div>
<!-- Alert Details Modal Start-->
	<div class="modal fade" id="alertDetailsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
	  <div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
		  <div class="modal-header bg_CC">
			<button type="button" class="close alertDetailsModalClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title fontColor text-capital"></h4>
			<p id="mainTitleId"></p>
		  </div>
		  <div class="modal-body" style="padding-bottom:0px;">
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
							<td colspan="2">
								<p class="text-capital"><span class="text-muted ">Article  </span> :
								<ul class="list-inline imageUrlUlCls"></ul>
							</td>
							<td colspan="6" style="display:none" id="alertGroupAttachId">
								<p class="text-capital"><span class="text-muted ">Grouped Articles  </span> :
								<div id="alertGroupAttachUlId" style="border-left: 1px solid #ccc"></div>
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
			<div id="alerAssignDivId" style="display:none;">
				<form id="alertAssign" name="alertAssignForm">
					<div class="row" style="background-color: #ccc;padding-top: 20px;box-shadow: 0px 0px 25px rgba(0,0,0,0.8);">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading headingColor">
									<h4 class="panel-title text-capital">update alert information</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<label class="checkbox-inline">
												<input type="checkbox" class="notMyDepartment"/>This Alert is not releated to my department
											</label>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
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
											<span style="color:red;" id="errMsgStsId"></span>
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
	</div>
	<!-- Alert Details Modal End-->
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
	
<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystem.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="alertDepartment/js/alertUserManagement.js" type="text/javascript"></script>

<script type="text/javascript">
initializeFile();
$(".chosenSelect").chosen({width:'100%'});
$(document).on("click",function(){
	$(".ranges ul li:first-child").hasClass("active")
});
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
   
function setDefaultImage(img){
    img.src = "images/User.png";
}

</script>
</body>
</html>