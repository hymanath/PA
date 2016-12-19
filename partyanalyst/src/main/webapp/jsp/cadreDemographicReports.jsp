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
<link rel="stylesheet" href="dist/sliderbar/bootstrap-slider.css">
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
									  <input type="radio" name="inlineRadioOptions" id="ageWiseReport" value="age" checked="checked" class="searchReportCls"><h4 class="text-capital">age wise report</h4>
									</label>
									<label class="radio-inline">
									  <input type="radio" name="inlineRadioOptions" id="casteWiseReport" value="caste" class="searchReportCls"><h4 class="text-capital">caste Wise Report</h4>
									</label>
									<label class="radio-inline">
									  <input type="radio" name="inlineRadioOptions" id="genderWiseReport" value="gender" class="searchReportCls"><h4 class="text-capital"> gender Wise Report</h4>
									</label>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-4">
									<label class="radio-inline">
									  <input type="radio" name="aptsradio" id="apCheckedCls" value="1" attr_state_name ="AP" checked="true" class="stateCls"><h4 class="text-capital">AP</h4>
									</label>
									<label class="radio-inline">
									  <input type="radio" name="aptsradio" id="tsCheckedCls" value="36" attr_state_name ="TS" class="stateCls"><h4 class="text-capital">TS</h4>
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="ageWiseSummaryDetails" class="ageWiseBlock"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="ageWiseDistrictDetails" class="ageWiseBlock"></div>
								<div id="excelDivageWiseDistrictDetails" style="display:none;"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12">	
								<div id="ageWiseConstituencyDetails" class="ageWiseBlock"></div>
								<div id="excelDivageWiseConstituencyDetails" style="display:none;"></div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="overAllCasteWiseSummaryDetails" class="casteWiseBlock" style="display:none;"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 casteWiseBlock" style="display:none;">
								<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
									<div class="panel panel-default">
										 <div class="panel-heading" style="background-color: #f3f3f3 ! important;">
											  <div class="row">
													<div class="col-md-6 col-xs-12 col-sm-4">
														<h3 class="panel-title text-capital"><span id="aptsIdForStateWiseCaste"></span> state - caste wise report </h3></h3>
													</div>
													<div class="col-md-4 col-xs-12 col-sm-2">
														<input id="stateWiseSlider" data-slider-id="stateWiseSlider" type="text" data-slider-min="0" data-slider-max="10" data-slider-step="0.5" data-slider-value="0.5"/>
														<span>Caste Percentage : <span id="stateSliderValue"></span></span>
													</div>
													<div class="col-md-2 col-xs-12 col-sm-2">
														<button class="btn btn-success btn-xs" id="stateWiseCasteExcelReportId" >Export To Excel</button></h4>
													</div>
												</div>
											</div>
										<div class="panel-body">
											<div id="stateWiseCasteDetails" ></div>
											<div id="stateWiseCasteDetailsExcelReport" class="" style="display:none;"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 casteWiseBlock" style="display:none;">
								<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
									<div class="panel panel-default">
										  <div class="panel-heading" style="background-color: #f3f3f3 ! important;">
											  <div class="row">
													<div class="col-md-6 col-xs-12 col-sm-4">
														<h3 class="panel-title text-capital"><span id="aptsIdFordistrictWiseCaste"></span> District - caste wise report </h3>
													</div>
													 <div class="col-md-4 col-xs-12 col-sm-2">
														<input id="districtWiseSlider" data-slider-id="districtWiseSlider" type="text" data-slider-min="0" data-slider-max="10" data-slider-step="0.5" data-slider-value="0.5"/>
														<span>Caste Percentage : <span id="districtSliderValue"></span></span>
													</div>
													
													<div class="col-md-2 col-xs-12 col-sm-2">
														<button class="btn btn-success btn-xs" id="districtWiseCasteExcelReportId" >Export To Excel</button></h4>
													</div>
											  </div>
										  </div>
										  <div class="panel-body">
											<div id="districtWiseCasteDetails" ></div>
											<div id="districtWiseCasteDetailsExcelReport" class="" style="display:none;"></div>
										  </div>
									</div>
								</div>
								
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 casteWiseBlock" style="display:none;">
								<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
									<div class="panel panel-default">
										 <div class="panel-heading" style="background-color: #f3f3f3 ! important;">
											  <div class="row">
												<div class="col-md-6 col-xs-12 col-sm-4">
													<h3 class="panel-title text-capital"><span id="aptsIdForConsWiseCaste"></span> CONSTITUENCY - WISE CASTE REPORT</h3>
												</div>
												 <div class="col-md-4 col-xs-12 col-sm-2">
													<input id="constituencyWiseSlider" data-slider-id="constituencyWiseSlider" type="text" data-slider-min="0" data-slider-max="10" data-slider-step="0.5" data-slider-value="0.5"/>
													<span>Caste Percentage : <span id="constituencySliderValue"></span></span>
												</div> 
												<div class="col-md-2 col-xs-12 col-sm-2">
													<button class="btn btn-success btn-xs" id="constituencyWiseCasteExcelReportId" >Export To Excel</button></h4>
												</div>
											  </div>
										  </div>
										  <div class="panel-body">
											<div id="constituencyWiseCasteDetails"></div>
											<div id="constituencyWiseCasteDetailsExcelReport" class="" style="display:none;"></div>
										  </div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="overAllGenderWiseSummaryDetails" class="genderWiseBlock" style="display:none;"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="genderWiseDistrictDetails" class="genderWiseBlock" style="display:none;"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="genderWiseConstituencyDetails" class="genderWiseBlock" style="display:none;"></div>
								<div id="excelDivGenderWiseConstituencyDetails" class="" style="display:none;"></div>
							</div>
						</div>
						
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
<script src="dist/sliderbar/bootstrap-slider.js" type="text/javascript"></script>
<script type="text/javascript">
	
	onLoadCalls();
	function onLoadCalls(){
		getAgeWiseTdpCadreSummaryReport(1);
		getDistrictWisegeWiseTdpCadreCounts(1,0,"district");
		getConstituencyWisegeWiseTdpCadreCounts(1,0,"constituency");
	}
	
	function getReportType(){
		 var searchReportValue = ''; 
		$('.searchReportCls').each(function(i, obj){
          if($(this).is(':checked')){
			  searchReportValue = $(this).val();
		  }
		});
		return searchReportValue;
	}
	
	$(document).on("click",".stateCls",function(){
			var stateId = $(this).val();
			var searchReportValue=getReportType();
		
			if(searchReportValue == "age"){
				//age Wise Calls
				getAgeWiseTdpCadreSummaryReport(stateId);
				getDistrictWisegeWiseTdpCadreCounts(stateId,0,"district");
				getConstituencyWisegeWiseTdpCadreCounts(stateId,0,"constituency");
			}
			if(searchReportValue == "caste"){
				//caste Wise Calls
				getCasteCategoryWiseTdpCadreSummaryReport(stateId);
				getstateWiseTdpCadreCasteCounts(stateId,0.5);//default range 1.0  
				getdistrictWiseTdpCadreCasteCounts(stateId,0,0.5);//default range 1.0  
				getConstituencyWiseTdpCadreCasteCountsAction(stateId,0,0.5);//default range 1.0
				
				 $("#stateSliderValue").text(0.5);
				 $("#districtSliderValue").text(0.5);
				 $("#constituencySliderValue").text(0.5);
				 
				 $("#stateWiseSlider").remove();
				  stateWiseSliderBar();
				 $("#districtWiseSlider").remove();
				 districtWiseSliderBar();
				 $("#constituencyWiseSlider").remove();
				 constituencySliderBar();
			 
			}
			if(searchReportValue == "gender"){
				//Gender Wise Calls
				getstateWiseCadreGenderCounts(stateId);
				getDistrictWiseCadreGenderCounts(stateId,0,"district");
				getConstituencyWiseCadreGenderCounts(stateId,0,"constituency");
			}
			
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
		 $("#stateSliderValue").text(0.5);
		 $("#districtSliderValue").text(0.5);
		 $("#constituencySliderValue").text(0.5);
		 
		 $("#stateWiseSlider").remove();
		  stateWiseSliderBar();
		 $("#districtWiseSlider").remove();
		 districtWiseSliderBar();
		 $("#constituencyWiseSlider").remove();
		 constituencySliderBar();
		 
		getCasteCategoryWiseTdpCadreSummaryReport(stateId);
		getstateWiseTdpCadreCasteCounts(stateId,0.5);//default range 1.0    
		getdistrictWiseTdpCadreCasteCounts(stateId,0,0.5);//default range 1.0  
		getConstituencyWiseTdpCadreCasteCountsAction(stateId,0,0.5);//default range 1.0  
		
		
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
	
	stateWiseSliderBar();
	districtWiseSliderBar();
	constituencySliderBar();
	function stateWiseSliderBar(){
		var stateId = ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateId = $(this).val();
		  }
		});
		
		var stateSliderval;
			var slider = new Slider('#stateWiseSlider', {
		   formatter: function(value) {
			   stateSliderval=value;
			   $("#stateSliderValue").text(value);
			 return 'Current value: ' + value;
		   }
		});
		
		$( "#stateWiseSlider" ).mouseup(function() {
			getstateWiseTdpCadreCasteCounts(stateId,stateSliderval);//default range 1.0    
		});
	}
	function districtWiseSliderBar(){	
	var stateId = ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateId = $(this).val();
		  }
		});
		
		var districtSliderVal;
		var slider = new Slider('#districtWiseSlider', {
		   formatter: function(value) {
			   $("#districtSliderValue").text(value);
			   districtSliderVal=value;
			 return 'Current value: ' + value;
		   }
		});
	
		$( "#districtWiseSlider" ).mouseup(function() {
			getdistrictWiseTdpCadreCasteCounts(stateId,0,districtSliderVal);//default range 1.0  
		});
	}
	function constituencySliderBar(){
		var stateId = ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateId = $(this).val();
		  }
		});
		
		var constituencySliderVa1;
			var slider = new Slider('#constituencyWiseSlider', {
		   formatter: function(value) {
			   $("#constituencySliderValue").text(value);
			   constituencySliderVa1=value;
			 return 'Current value: ' + value;
		   }
		});
		
		$( "#constituencyWiseSlider" ).mouseup(function() {
			getConstituencyWiseTdpCadreCasteCountsAction(stateId,0,constituencySliderVa1);//default range 1.0  
		});
		
	}
	
	function getState(){
		 var stateName= ''; 
		$('.stateCls').each(function(i, obj){
          if($(this).is(':checked')){
			  stateName = $(this).attr("attr_state_name");
		  }
		});
		return stateName;
	}
	
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