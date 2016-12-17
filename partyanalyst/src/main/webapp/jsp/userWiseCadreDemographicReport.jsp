<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cadre Demographic Reports</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
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
						 <h2 class="panel-title text-capital text-center f_18"><span id="loggedInUserName"></span> - cadre Demographic Reports</h2>
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
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="stateWiseCasteDetails" class="casteWiseBlock" style="display:none;"></div>
								<div id="stateWiseCasteDetailsExcelReport" class="" style="display:none;"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="districtWiseCasteDetails" class="casteWiseBlock" style="display:none;"></div>
								<div id="districtWiseCasteDetailsExcelReport" class="" style="display:none;"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="constituencyWiseCasteDetails" class="casteWiseBlock" style="display:none;"></div>
								<div id="constituencyWiseCasteDetailsExcelReport" class="" style="display:none;"></div>
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
    </div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/userWiseCadreDemographicReport.js" type="text/javascript"></script>
<script src="dist/sliderbar/bootstrap-slider.js" type="text/javascript"></script>
 <script type="text/javascript">
	var loggedInUser = '${USER.registrationID}';
    var loggedInUserName = '${UserName}';
	var accessType = '${idAndNameVO.name}';
	
	var districtArray = getdistrictsList();
	var constituencyArray = getConstituenciesList();
	var locationArray =  finalLocationIds(accessType ,districtArray , constituencyArray );
	
	$("#loggedInUserName").html(loggedInUserName);
	onLoadCalls();
	
	function onLoadCalls(){
		//AGE WISE  CALLS.
		getAgeWiseTdpCadreSummaryReport();
		if(accessType.toUpperCase() == "DISTRICT" || accessType.toUpperCase() == "DISTRICTANDCONSTITUENCY"){
			getDistrictWisegeWiseTdpCadreCounts();
		}
		getConstituencyWisegeWiseTdpCadreCounts();
	}
	//AGE WISE  CALLS.
	$(document).on("click","#ageWiseReport",function(){
		
		$(".ageWiseBlock").show();
		$(".casteWiseBlock").hide();
		$(".genderWiseBlock").hide();
		
		getAgeWiseTdpCadreSummaryReport();
		if(accessType.toUpperCase() == "DISTRICT" || accessType.toUpperCase() == "DISTRICTANDCONSTITUENCY"){
			getDistrictWisegeWiseTdpCadreCounts();
		}
		getConstituencyWisegeWiseTdpCadreCounts();
		
	});	
	
	//CASTE WISE BASIC CALLS.
	$(document).on("click","#casteWiseReport",function(){
		$(".ageWiseBlock").hide();
		$(".casteWiseBlock").show();
		$(".genderWiseBlock").hide();
		getCasteCategoryWiseTdpCadreSummaryReport();
		getstateWiseTdpCadreCasteCounts();
		if(accessType.toUpperCase() == "DISTRICT" || accessType.toUpperCase() == "DISTRICTANDCONSTITUENCY"){
			getdistrictWiseTdpCadreCasteCounts();
		}
		getConstituencyWiseTdpCadreCasteCountsAction();           
		
	});
	
	//gender wise calls
	$(document).on("click","#genderWiseReport",function(){
		
		$(".ageWiseBlock").hide();
		$(".casteWiseBlock").hide();
		$(".genderWiseBlock").show();
		getGenderSummaryCounts();
		if(accessType.toUpperCase() == "DISTRICT" || accessType.toUpperCase() == "DISTRICTANDCONSTITUENCY"){
		  getDistrictWiseCadreGenderCounts();
		}
		getConstituencyWiseCadreGenderCounts();
		
	});	
	
	function finalLocationIds(accessType ,districtArray ,constituencyArray ){
		if(accessType != null && accessType.trim().length > 0){
			if(accessType.toUpperCase() == "CONSTITUENCY" || accessType.toUpperCase() == "DISTRICTANDCONSTITUENCY" ){
				return constituencyArray;
			}else if(accessType.toUpperCase() == "DISTRICT"  ){
				return districtArray;
			}
		}
	}
	function getdistrictsList(){
		var distIds = [];
		<c:if test="${idAndNameVO.distIdList != null && fn:length(idAndNameVO.distIdList) gt 0}">  
		   <c:forEach var="distId" items="${idAndNameVO.distIdList}"> 
			  distIds.push(${distId});
		  </c:forEach>
		 </c:if> 
		 return distIds;
	 }
	 function getConstituenciesList(){
		 var constitIds= [];
		 <c:if test="${idAndNameVO.constIdList != null && fn:length(idAndNameVO.constIdList) gt 0}">  
		   <c:forEach var="constId" items="${idAndNameVO.constIdList}"> 
			  constitIds.push(${constId});
		  </c:forEach>
		 </c:if>
		 return constitIds;
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