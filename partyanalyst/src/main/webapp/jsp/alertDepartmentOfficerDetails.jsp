<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<title>Officer Wise Alert Reports</title>

<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCo#18A75AashBoard/img/fevicon.png">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/jquery.filer.css"  type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css"  type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="dist/sliderbar/bootstrap-slider.css">
<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/grievanceReport.css" type="text/css"/>
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<style>
.dateColorCls{
	background-color:#FFCF2D;
}
.table thead th , .table tr td
{
	font-size:12px !important;
}
.m_top10
{
	margin-top:10px !important;
}
.m_top5
{
	margin-top:5px
}
#selectMediaId_chosen a{background-color: #95989a;color: #f0f8ff;}
#selectMediaId_chosen .chosen-drop{background-color: #95989a !important;color: #f0f8ff !important;}
#selecDepartmentId_chosen a{background-color: #95989a;color: #f0f8ff;}
#selecDepartmentId_chosen .chosen-drop{background-color: #95989a !important;color: #f0f8ff !important;}
</style>
</head>
<body>  
  <nav>
			<div class=" bg-gov-dark">
				<div class="container">  
					<div class="row">
						
						<div class="col-sm-2 pull-right "> 
							<div class="input-group dateRangePickerCls m_top10" style="margin-right: 15px">
								<input type="text" class="form-control" style="width:180px;height: 37px;background-color: #95989a;color: #f0f8ff;" id="reportrange">
								<span class="input-group-addon" style="background-color: #95989a;border-left: 1px solid #f0f8ff;">
									<i class="glyphicon glyphicon-calendar" ></i>
								</span>
							</div>
						</div>
						<div class="col-sm-4 m_top10 pull-right">
							<select id="selecDepartmentId" class="form-control"  onChange="getDepartmentInformation();" style="width:70%">
								<c:forEach items="${idNameVOList}"  var="department">
									<option value="${department.id}">${department.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-2 m_top10 pull-right ">
							<select id="selectMediaId" class="form-control" onChange="getMediaInformation();">
								<option value="0">All</option>
								<option value="4">Call Center</option>     
								<option value="2">Print Media</option>
								<option value="3">Electronic Media</option>
								<option value="5">Social Media</option>
							</select>
						</div>
						
					</div>
				</div>
			</div>
		</nav>
		
	<div class="container-fluid bg-gov-dark">
		<div class="row">
			<div class="col-md-10">      
				<div class="row">
					<div class="col-md-7">
						<div id="statusWiseAlertCntId" style=" height: 400px; margin: 0 auto display:none"> </div>
					</div>
					<div class="col-md-3">
						<div id="feedbackWiseAlertCntId" style=" height: 400px; margin: 0 auto display:none"> </div>
					</div>
					<div class="col-md-2">
						<div id="reopenAlertCntId" style="min-width:10px;height: 400px; margin: 0 auto display:none"> </div>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<div style="padding-top: 80px">
					<h4 class="text-center issue-title total">TOTAL</h4>
					<h1 style="font-size: 6em ;" class=" text-center issue-title"><span style="cursor:pointer;" id="totalAlertCountId"></span></h1>
				</div>
			</div>
		</div>
	</div>
	<section class="container m_top20">
		<div class="row" >             
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading headingColor">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor" id="cadreGrievanceTitle" style="display:block;">Alert Efficiency</h4>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6 pull-right">
							<input id="sliderValueOficer" data-slider-id="sliderValueOficer" type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="2"/>&nbsp;&nbsp;&nbsp;
							<span>Value : <span id="getSliderVal"></span></span>
						</div> 
					</div>
				</div>
				<div class="panel-body">
					<div class="col-md-12 col-xs-12 col-sm-12 ">
						<div class="row">
							<div class="col-md-3 col-xs-12 col-sm-4" style="width: auto;">
								<label>Select Status</label>
								<select class="chosenSelect grievanceEffOnchange" multiple id="grievanceStatusId">
									<option value="1">Pending</option>
									<option value="2">Notified</option>
									<option value="3">Action In Progess</option>
									<option value="4" selected>Completed</option>
									<option value="5">Unable to Resolve</option>
									<option value="6">Action Not Required</option>
									<option value="7">Duplicate</option>
									<option value="8">Wrongly Mapped Designation</option>
									<option value="9">Wrongly Mapped Department</option>
									<option value="10">Rejoinder</option>
									<option value="11">Reopen</option>
									<option value="12" selected>Closed</option>
									<option value="13">Proposal</option>
								</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-4 pull-right">
									<h4 class="panel-title">Total Alert</h4>
									<h3 id="totalHeadingRangeCount"></h3>
								</div>
							</div>
							<div class="row">
								<div id="efficiencyId" class="m_top20"></div>
								<div id="efficiencyRangeId" class="m_top20"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="allLocationLevelDivId"></div>
	</section>
	<input type="hidden" id="dateRangeId" value="day"></input>
<div class="modal fade" id="alertManagementPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body modal-insurance">
				<div id="filter"></div>
				<div id="alertManagementPopupBody"></div>
			</div>
		</div>
  </div>
</div>
<div class="modal fade" id="alertManagementPopup1" tabindex="-2" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:85%;">
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
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="alertDepartment/js/jquery.hotkeys.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/sliderbar/bootstrap-slider.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="js/alertDepartmentOfficerDetails/alertDepartmentOfficerDetails.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystemNewUpload.js" type="text/javascript"></script>
<script src="alertDepartment/js/newAlertUserManagementDetail.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	var windowUrl = window.location.href;
	var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursDetailsAction")));
	wurl = wurl.replace("/PartyAnalyst","");

</script>
<script>
google.load("elements", "1", {
	packages: "transliteration"
});
$(".chosenSelect").chosen({width:'100%'})
$("#selecDepartmentId").chosen()
$("#selectMediaId").chosen()
$("#selecDepartmentId").trigger("chosen:updated")
$("#selectMediaId").trigger("chosen:updated")
getSliderDetails();
var sliderVa1;
function getSliderDetails(){
		var slider = new Slider('#sliderValueOficer', {
	   formatter: function(value) {
		   $("#getSliderVal").text(value);
		   sliderVa1=value;
		 return 'Current value: ' + value;
	   }
	});
	
	$( "#sliderValueOficer" ).mouseup(function() {
		getCadreGreivienceEfficiency(sliderVa1);
	});
}
function generateExcel1(){
	tableToExcel('grievanceReportTableId', 'Grievance Report');
}
function generateExcel2(){
	tableToExcel('dayWiseGrievanceReportTableId', 'Grievance Report Day Wise');
}
function generateExcel3(){
	tableToExcel('CatWiseGrievanceReportTableId', 'Grievance Report');
}
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>
