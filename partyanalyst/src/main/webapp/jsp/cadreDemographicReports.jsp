<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cadre Demographic Reports</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Rating/bootstrap-rating.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.text-capital{
	
	text-transform:uppercase;
}
.f_18{
	font-size: 18px !important;
}
.font_weight{
	
	font-weight:bold;
}
.f_14{
	font-size:14px;
}
.f_13{
	font-size:12px;
}
.tableHeaderStyle thead th{ padding:5px !important;font-size:12px !important;}
</style>
</head>
<body>  
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">  
				<div class="panel panel-default panelNewCustom">
					<div class="panel-heading">
						 <h2 class="panel-title text-capital text-center f_18">cadre Demographic Reports</h2>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="col-md-10 col-xs-12 col-sm-8">
									<label class="radio-inline">
									  <input type="radio" name="inlineRadioOptions" id="ageWiseReport" value="option11" checked="checked"><h4 class="text-capital">age wise report</h4>
									</label>
									<label class="radio-inline">
									  <input type="radio" name="inlineRadioOptions" id="casteWiseReport" value="option22" ><h4 class="text-capital">caste Wise Report</h4>
									</label>
									<label class="radio-inline">
									  <input type="radio" name="inlineRadioOptions" id="genderWiseReport" value="option33" ><h4 class="text-capital"> gender Wise Report</h4>
									</label>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-4">
									<label class="radio-inline">
									  <input type="radio" name="aptsradio" id="apCheckedCls" value="1" checked="true" class="stateCls"><h4 class="text-capital">AP</h4>
									</label>
									<label class="radio-inline">
									  <input type="radio" name="aptsradio" id="tsCheckedCls" value="36" class="stateCls"><h4 class="text-capital">TS</h4>
									</label>
								</div>
							</div>
						</div>
						<div id="ageWiseSummaryDetails" class="ageWiseBlock"></div>
						<div id="ageWiseDistrictDetails" class="ageWiseBlock"></div>
						<div id="excelDivageWiseDistrictDetails" style="display:none;"></div>
						<div id="ageWiseConstituencyDetails" class="ageWiseBlock"></div>
						<div id="excelDivageWiseConstituencyDetails" style="display:none;"></div>
						
						<div id="overAllCasteWiseSummaryDetails" class="casteWiseBlock" style="display:none;"></div>
						<div id="CasteCategoryStateWiseSummaryDetails" class="casteWiseBlock" style="display:none;"></div>
						<div id="CasteCategoryDistrictWiseSummaryDetails" class="casteWiseBlock" style="display:none;"></div>
						<div id="excelDivGCasteCategoryDistrictWiseSummaryDetails" class="" style="display:none;"></div>
						<div id="CasteCategoryConstituencyWiseSummaryDetails" class="casteWiseBlock" style="display:none;"></div>
						<div id="excelDivGCasteCategoryConstituencyWiseSummaryDetails" class="" style="display:none;"></div>
						
						<div id="overAllGenderWiseSummaryDetails" class="genderWiseBlock" style="display:none;"></div>
						<div id="genderWiseDistrictDetails" class="genderWiseBlock" style="display:none;"></div>
						<div id="genderWiseConstituencyDetails" class="genderWiseBlock" style="display:none;"></div>
						<div id="excelDivGenderWiseConstituencyDetails" class="" style="display:none;"></div>
					</div>  
				</div>	
			</div>
	</div>


<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Rating/bootstrap-rating.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/cadreDemographicReports.js" type="text/javascript"></script>
<script type="text/javascript">
	
	onLoadCalls();
	function onLoadCalls(){
		getAgeWiseTdpCadreSummaryReport(1);
		getDistrictWisegeWiseTdpCadreCounts(1,0,"district");
		getConstituencyWisegeWiseTdpCadreCounts(1,0,"constituency");
	}
	
	$(document).on("click",".stateCls",function(){
			var stateId = $(this).val();
			//age Wise Calls
			getAgeWiseTdpCadreSummaryReport(stateId);
			getDistrictWisegeWiseTdpCadreCounts(stateId,0,"district");
			getConstituencyWisegeWiseTdpCadreCounts(stateId,0,"constituency");
			//caste Wise Calls
			getCasteCategoryWiseTdpCadreSummaryReport(stateId);
			getstateWiseTdpCadreCasteCounts(stateId);
			//getdistrictWiseTdpCadreCasteCounts(stateId,0);
			//getConstituencyWiseTdpCadreCasteCountsAction(stateId,0);
			//Gender Wise Calls
			getstateWiseCadreGenderCounts(stateId);
			getDistrictWiseCadreGenderCounts(stateId,0,"district");
			getConstituencyWiseCadreGenderCounts(stateId,0,"constituency");
	});
	
	$(document).on("click","#ageWiseReport",function(){
		 var stateId = ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateId = $(this).val();
		  }
		});
		$(".ageWiseBlock").show();
		$(".casteWiseBlock").hide();
		$(".genderWiseBlock").hide();
		
		getAgeWiseTdpCadreSummaryReport(stateId);
		getDistrictWisegeWiseTdpCadreCounts(stateId,0,"district");
		getConstituencyWisegeWiseTdpCadreCounts(stateId,0,"constituency");
		
	});	
	$(document).on("click","#casteWiseReport",function(){
		 var stateId = ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateId = $(this).val();
		  }
		});
		
		$(".ageWiseBlock").hide();
		$(".casteWiseBlock").show();
		$(".genderWiseBlock").hide();
		getCasteCategoryWiseTdpCadreSummaryReport(stateId);
		getstateWiseTdpCadreCasteCounts(stateId);
		//getdistrictWiseTdpCadreCasteCounts(stateId,0);
		//getConstituencyWiseTdpCadreCasteCountsAction(stateId,0);
		
	});	
	$(document).on("click","#genderWiseReport",function(){
		 var stateId = ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateId = $(this).val();
		  }
		});
		
		$(".ageWiseBlock").hide();
		$(".casteWiseBlock").hide();
		$(".genderWiseBlock").show();
		getstateWiseCadreGenderCounts(stateId);
		getDistrictWiseCadreGenderCounts(stateId,0,"district");
		getConstituencyWiseCadreGenderCounts(stateId,0,"constituency");
		
	});	
	
	
	
</script> 
<script>
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