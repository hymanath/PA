<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<title>Location wise Grevince Reports</title>

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
<link rel="stylesheet" href="dist/sliderbar/bootstrap-slider.css">
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
.getAlertDtlsOnCategoryWise{color: #337ab7;}
.getAlertDtlsCls{color: #337ab7;}
.getAlertDtlsOnDateWise{color: #337ab7;}
.panchayatDataCls{cursor:pointer;}
.daterangeClorCls{cursor:pointer;}
</style>
</head>
<body>           
		<nav>
			<div class=" bg-gov-dark">
				<div class="container">  
					<div class="row">
						<div id="menu1" class="col-sm-3">       
								<ul class="nav navbar-nav">        
									<li> <a  attr_range_val="month" class="daterangeClorCls rangeTypeCls">MONTH</a> </li>  
									<li> <a attr_range_val="week" class="daterangeClorCls rangeTypeCls" >WEEK</a> </li>
									<li> <a  attr_range_val="day" class="daterangeClorCls rangeTypeCls  dateColorCls">DAY</a> </li>
								</ul>
						</div>
						<div class="col-sm-3"> 
							<div class="input-group dateRangePickerCls m_top10" style="margin-right: 15px">
								<input type="text" class="form-control" style="width:180px;height: 37px;background-color: #95989a;color: #f0f8ff;" id="reportrange">
								<span class="input-group-addon" style="background-color: #95989a;border-left: 1px solid #f0f8ff;">
									<i class="glyphicon glyphicon-calendar" ></i>
								</span>
							</div>
						</div>
						<div class="col-sm-3 m_top10">
							<select id="selectMediaId" class="form-control" onChange="getMediaInformation();">
								<option value="0">All</option>
								<c:forEach items="${idNameVOList[0].subList1}"  var="category">
									<option value="${category.id}">${category.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-3 m_top10">
							<select id="selecDepartmentId" class="form-control"  onChange="getDepartmentInformation();" style="width:70%">
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
				<div class="col-md-2">
					<div style="padding-top: 80px">
						<h4 class="text-center issue-title">AVERAGE ISSUE <br> PENDING DAYS</h4>
						<h3 class=" text-center issue-title"><span id="averageIssueId"></span></h3>
					</div>
				</div>
				<div class="col-md-8">   
					<div>
						<h1 class="text-center">
							<div id="statusWiseAlertCntId" style="min-width:10px; height: 400px; margin: 0 auto display:none"> </div>
						</h1>
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
	   <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading headingColor">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor" id="cadreGrievanceTitle" style="display:block;"> Alert Efficiency 
								<!--<span style="margin-left:300px;font-size:13px;" class="fontColor">AVERAGE ISSUE PENDING DAYS : 
								<span id="issuePendingCntId"></span></span>-->
							<!--<span class="pull-right"><input id="proposalId" onClick="getCadreGreivienceEfficiency()" class="form-check-input" type="checkbox" value="Include" checked>   
							<span style="font-size:12px;">Include Proposal</span></span>-->
							</h4>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6 pull-right">
							<input id="sliderValue" data-slider-id="sliderValue" type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="2"/>&nbsp;&nbsp;&nbsp;
							<span>Value : <span id="getSliderVal"></span></span>
						</div> 
					</div>
				</div>
				<div class="panel-body">
					<div class="col-md-12 col-xs-12 col-sm-12 ">
						<div class="row">
							<div class="col-md-3 col-xs-12 col-sm-4" style="width: auto;">
								<label>Select Status</label>
								<select class="chosenSelect grievanceEffOnchange" multiple id="statusId">
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
		   <div class="panel panel-default">
				<div class="panel-heading headingColor">
					<div class="row">
					
						<div class="col-md-10 m_top5">   
							<h4 class="panel-title text-capital fontColor">Category Wise Grievance Report </h4>  
						</div>
						<div class="col-md-2">
							<button class="btn btn-success btn-sm" onclick="generateExcel3();">
								<i class="glyphicon glyphicon-download-alt"></i> Download
							</button>   
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div  class="row">  
						<div class="col-md-12">
							<div class="table-responsive m_top20" id="CategoryWiseGrivenaceTableId"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading headingColor">
					<div  class="row">
						<div class="col-md-10 m_top5">   
							<h4 class="panel-title text-capital fontColor">Location wise Grievance Report </h4>  
						</div>
						<div class="col-md-2">
							<button class="btn btn-success btn-sm" onclick="generateExcel1();">
								<i class="glyphicon glyphicon-download-alt"></i> Download
							</button>   
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div  class="row">
						<div class="col-md-12">
							<div class="table-responsive m_top20" id="grivenaceTableId"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading headingColor">
					<div class="row">
						<div class="col-md-8 m_top5">   
							<h4 class="panel-title text-capital fontColor">Date wise Grievance Report </h4>
						</div>
						<div class="col-md-2">
							<select id="selectDistrictId" class="form-control " onChange="getDistrintInformation();"></select>
						</div>
						<div class="col-md-2">
							<button class="btn btn-success btn-sm" onclick="generateExcel2();">
								<i class="glyphicon glyphicon-download-alt"></i> Download
							</button>   
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div  class="row">
						<div class="col-md-12">
							<div class="table-responsive m_top20" id="dayWiseGrivenaceTableId"></div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<input type="hidden" id="dateRangeId" value="day"></input> 
      <!-- Modal -->
	<div class="modal fade" id="bellowLvlLocId" role="dialog">  
		<div class="modal-dialog" role="document" style="width:90%;">
		  <!-- Modal content-->
		  <div class="modal-content">
			<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			  <h4 class="modal-title" id="bellowLvlLocationId">Modal Header</h4>
			</div>
			<div class="modal-body" id="tehsilTableId"></div>   
			<div class="modal-footer">
			  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		  </div>
		  
		</div>
	</div>
<!-- Modal -->
   <div class="modal fade" id="grievanceDtlsModalId" role="dialog">
    <div class="modal-dialog" style="width:85%">
      <div class="modal-content">
        <div class="modal-header">
          <button id="removeClassModal" type="button" class="close" data-dismiss="modal">&times;</button>
         <div id="grivenaceModalHeedingId"> </div> 
         <div id="grivancHeadinId"> </div> 
        </div>
        <div class="modal-body">
            <div class="table-responsive" id="totalAlertDistricTableId"> </div>
			<div id="grevinceDetailsId"  class="table-responsive"></div>
        </div>
      </div>
    </div>
  </div>
<div class="modal fade" id="alertManagementPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:90%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body modal-insurance">
				<div id="filter" style="display:none;"></div>
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
				<div id="filter"></div>
				<div id="alertManagementPopupBody1"></div>
			</div>
			<div class="modal-footer">
				<button type="button closeSecondModal" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
  </div>
</div>
  <div class="modal" tabindex="-1" role="dialog" id="cdrModelDivId">
	  <div class="modal-dialog modal-lg">       
		<div class="modal-content" >
			<div class="modal-body">
				<div id="rightSideExpandView">
				</div>     
			</div>
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="myModalShowNew">
	<div class="modal-dialog modal-lg" role="document" style="width:90%">
		<div class="modal-content">
			<div id="myModalShowNewId"></div>
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
<script src="js/grievanceReport/grievanceReport.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystemNewUpload.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="alertDepartment/js/newAlertUserManagementDetail.js" type="text/javascript"></script>
<script src="dist/sliderbar/bootstrap-slider.js" type="text/javascript"></script>
<!-- Custom Script Files Data End-->
<script type="text/javascript">
google.load("elements", "1", {
	packages: "transliteration"
});
</script>
<script type="text/javascript">
	var windowUrl = window.location.href;
	var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursDetailsAction")));
	wurl = wurl.replace("/PartyAnalyst","");

</script>
<script>
$(".chosenSelect").chosen({width:'100%'})
$("#selecDepartmentId").chosen()
$("#selectMediaId").chosen()
$("#selecDepartmentId").trigger("chosen:updated")
$("#selectMediaId").trigger("chosen:updated")
getSliderDetails();
var sliderVa1;
function getSliderDetails(){
		var slider = new Slider('#sliderValue', {
	   formatter: function(value) {
		   $("#getSliderVal").text(value);
		   sliderVa1=value;
		 return 'Current value: ' + value;
	   }
	});
	
	$( "#sliderValue" ).mouseup(function() {
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
