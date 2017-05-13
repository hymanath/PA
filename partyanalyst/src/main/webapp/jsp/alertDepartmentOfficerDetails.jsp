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
</style>
</head>
<body>           
	<nav>
		<div class=" bg-gov-dark">
			<div class="container">  
				<div class="row">
					<div id="menu1" class="col-md-6">       
						<div class="col-md-12">
							<ul class="nav navbar-nav">        
								<!--<li> <a href="#" attr_range_val="month" class="daterangeClorCls rangeTypeCls">MONTH</a> </li>  
								<li> <a href="#" attr_range_val="week" class="daterangeClorCls rangeTypeCls" >WEEK</a> </li>
								<li> <a href="#" attr_range_val="day" class="daterangeClorCls rangeTypeCls  dateColorCls">DAY</a> </li>-->
								<li style="padding:14px 16px;color:#95989A;"> 
									<div id="reportrange"> 
										&nbsp; <span>Custom Date Range</span>
									</div>
								</li>
							</ul>
						</div>     
					</div>
					<div class="col-md-3 m_top10">
						<select id="selectMediaId"class="selectpicker" onChange="getMediaInformation();">
							<option value="0">All</option>
							<option value="4">Call Center</option>         
							<option value="5">Social Media</option>            
							<option value="2">Print Media</option>
							<option value="3">Electronic Media</option>
						</select>
					</div>
					<div class="col-md-3">
						<select id="selecDepartmentId" class="selectpicker"  onChange="getDepartmentInformation();">
							<c:forEach items="${idNameVOList}"  var="department">
								<option value="${department.id}">${department.name}</option>
							</c:forEach>
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
		<div id="allLocationLevelDivId"></div>
	</section>
	<input type="hidden" id="dateRangeId" value="day"></input>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>

<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/alertDepartmentOfficerDetails/alertDepartmentOfficerDetails.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript">
	var windowUrl = window.location.href;
	var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursDetailsAction")));
	wurl = wurl.replace("/PartyAnalyst","");

</script>
<script>
$(".chosenSelect").chosen({width:'100%'})
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
