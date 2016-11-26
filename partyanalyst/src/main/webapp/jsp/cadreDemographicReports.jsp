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
.tableHeaderStyle thead th{ padding:5px !important;font-size:12px !important}
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
						<div id="ageWiseSummaryDetails"></div>
						<div id="ageWiseDistrictDetails"></div>
						<div id="excelDivageWiseDistrictDetails" style="display:none;"></div>
						<div id="ageWiseConstituencyDetails" ></div>
						<div id="excelDivageWiseConstituencyDetails" style="display:none;"></div>
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
<script type="text/javascript">
	
	onLoadCalls();
	function onLoadCalls(){
		getAgeWiseTdpCadreSummaryReport(1);
		getDistrictWisegeWiseTdpCadreCounts(1,0,"district");
		getConstituencyWisegeWiseTdpCadreCounts(1,0,"constituency");
	}
	
	$(document).on("click",".stateCls",function(){
			var stateId = $(this).val();
			getAgeWiseTdpCadreSummaryReport(stateId);
			getDistrictWisegeWiseTdpCadreCounts(stateId,0,"district");
			getConstituencyWisegeWiseTdpCadreCounts(stateId,0,"constituency");
	});
	
	//Over All Report Start
	function getAgeWiseTdpCadreSummaryReport(stateId){
		 $("#ageWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId
					}
		
		$.ajax({
			type : 'POST',
			url : 'ageWiseTdpCadreSummaryReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#ageWiseSummaryDetails").html('');
			buildAgeWiseTdpCadreSummaryReport(result,stateId);
		});
	}
	
	function buildAgeWiseTdpCadreSummaryReport(result,stateId){
		var str='';
		if(result !=null && result.subList !=null && result.subList.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">overall age wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">overall age wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					str+='<table class="table table-bordered" id="overaAllAgeWiseTableExcelDivId">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">age range</th></b>';
								str+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2014 cadre %</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre %</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b class="f_14">Renewal %</br>(From 2016)</b></th>';
								str+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str+='<th class="text-capital "><b class="f_14">New cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>'+result.subList[i].name+'</td>';
								if(result.subList[i].previousCadreCount !=null && result.subList[i].previousCadreCount>0){
									str+='<td>'+result.subList[i].previousCadreCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].previousCadrePercent !=null && result.subList[i].previousCadrePercent>0){
									str+='<td>'+result.subList[i].previousCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].cadreCount !=null && result.subList[i].cadreCount>0){
									str+='<td>'+result.subList[i].cadreCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].cadrePercent !=null && result.subList[i].cadrePercent>0){
									str+='<td>'+result.subList[i].cadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].renewalCadre !=null && result.subList[i].renewalCadre>0){
									str+='<td>'+result.subList[i].renewalCadre+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].previousCadreRenewalPercent !=null && result.subList[i].previousCadreRenewalPercent>0){
									str+='<td>'+result.subList[i].previousCadreRenewalPercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].renewalCadrePercent !=null && result.subList[i].renewalCadrePercent>0){
									str+='<td>'+result.subList[i].renewalCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].newCadre !=null && result.subList[i].newCadre>0){
									str+='<td>'+result.subList[i].newCadre+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].newCadrePercent !=null && result.subList[i].newCadrePercent>0){
									str+='<td>'+result.subList[i].newCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							str+='</tr>';
						}
						str+='<tr style="background-color:#efefef">';
							str+='<th><b>Grand Total</b></th>';
							if(result.previousCadreTotalCount !=null && result.previousCadreTotalCount>0){
								str+='<th><b>'+result.previousCadreTotalCount+'</b></th>';
								str+='<th><b>100</th>';
							}else{
								str+='<th> - </th>';
								str+='<th> - </th>';
							}
							
							if(result.cadreTotalCount !=null && result.cadreTotalCount>0){
								str+='<th><b>'+result.cadreTotalCount+'</b></th>';
								str+='<th><b>100</b></th>';
							}else{
								str+='<th> - </th>';
								str+='<th> - </th>';
							}
							if(result.renewalCadreTotalCount !=null && result.renewalCadreTotalCount>0){
								str+='<th><b>'+result.renewalCadreTotalCount+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.previousCadreRenewalPercent !=null && result.previousCadreRenewalPercent>0){
								str+='<th><b>'+result.previousCadreRenewalPercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.renewalCadrePercent !=null && result.renewalCadrePercent>0){
								str+='<th><b>'+result.renewalCadrePercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.newCadreTotalCount !=null && result.newCadreTotalCount>0){
								str+='<th><b>'+result.newCadreTotalCount+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.newCadrePercent !=null && result.newCadrePercent>0){
								str+='<th><b>'+result.newCadrePercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							
						str+='</tr>';
						str+='</tbody>';
						str+='</table>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#ageWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#ageWiseSummaryDetails").html(str);
	}
	
	$(document).on("click","#overAllReportExcel",function(){
		generateExcelReportForOverAllAgeWise();	
	});
	
	function generateExcelReportForOverAllAgeWise(){
		tableToExcel(overaAllAgeWiseTableExcelDivId, 'OVERALL AGE WISE REPORT');        
	}
	
	//Over All Report End
	
	//District Wise Report Start
	function getDistrictWisegeWiseTdpCadreCounts(stateId,districtId,searchType){
		 $("#ageWiseDistrictDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId,
					 searchType:searchType
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLocationWisegeWiseTdpCadreCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#ageWiseDistrictDetails").html('');
			 buildDistrictWisegeWiseTdpCadreCounts(result,stateId);
			
		});
	}
	
	function buildDistrictWisegeWiseTdpCadreCounts(result,stateId){
		
		var str='';
		var str1='';
		if(result !=null && result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">district age wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">district age wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="districtWiseExcelReport" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					
					
					str+='<table class="table table-bordered tableHeaderStyle districtWiseDataTableId" id="" >';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b>DISTRICT</th></b>';
								str+='<th class="text-capital " ><b>Age Range</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str+='<th class="text-capital "><b>Renewal</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str+='<th class="text-capital "><b>New Cadre</b></th>';
								str+='<th class="text-capital "><b>New Cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							
								
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str+='<tr>';
										str+='<td>'+result[i].name+'</td>';
										str+='<td>'+result[i].subList[j].name+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].newCadre+'</td>';
										str+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str+='</tr>';
									}
								} 
								
							
						}
						
						str+='</tbody>';
						str+='</table>';
						//for fetching all records start
						str1+='<table class="table table-bordered tableHeaderStyle " id="districtWiseTableExcelDivId" >';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b>DISTRICT</th></b>';
								str1+='<th class="text-capital " ><b>Age Range</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>Renewal</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str1+='<th class="text-capital "><b>New Cadre</b></th>';
								str1+='<th class="text-capital "><b>New Cadre %</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
							
								
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str1+='<tr>';
										str1+='<td>'+result[i].name+'</td>';
										str1+='<td>'+result[i].subList[j].name+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].newCadre+'</td>';
										str1+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str1+='</tr>';
									}
								} 
								
							
						}
						
						str1+='</tbody>';
						str1+='</table>';
						//for fetching all records end
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#ageWiseDistrictDetails").html("NO DATA AVAILABLE");
		}
		
		$("#ageWiseDistrictDetails").html(str);
		$("#excelDivageWiseDistrictDetails").html(str1);
		$('.districtWiseDataTableId').DataTable();
	}
	
	$(document).on("click","#districtWiseExcelReport",function(){
		generateExcelReportDistrictWiseAgeReport();	
	});
	
	function generateExcelReportDistrictWiseAgeReport(){
		tableToExcel(districtWiseTableExcelDivId, 'DISTRICT AGE WISE REPORT');        
	}
	//District Wise Report End
	
	
	//Constituency Wise Report Start
	function getConstituencyWisegeWiseTdpCadreCounts(stateId,districtId,searchType){
		
		 $("#ageWiseConstituencyDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId,
					 searchType:searchType
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLocationWisegeWiseTdpCadreCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#ageWiseConstituencyDetails").html('');
			 buildConstituencyWisegeWiseTdpCadreCounts(result,stateId);
			
		});
	}
	
	function buildConstituencyWisegeWiseTdpCadreCounts(result,stateId){
		
		var str='';
		var str1='';
		if(result !=null && result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">constituency age wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">constituency age wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="constituencyWiseExcelReport" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					
					
					str+='<table class="table table-bordered tableHeaderStyle constituencyWiseDataTableId" id="" >';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b>District</th></b>';
								str+='<th class="text-capital "><b>Constituency</th></b>';
								str+='<th class="text-capital " ><b>Age Range</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str+='<th class="text-capital "><b>Renewal</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str+='<th class="text-capital "><b>New Cadre</b></th>';
								str+='<th class="text-capital "><b>New Cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							
								
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str+='<tr>';
										str+='<td>'+result[i].superlocationName+'</td>';
										str+='<td>'+result[i].name+'</td>';
										str+='<td>'+result[i].subList[j].name+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].newCadre+'</td>';
										str+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str+='</tr>';
									}
								} 
								
							
						}
						
						str+='</tbody>';
						str+='</table>';
						
						//for fetching all records start
						str1+='<table class="table table-bordered tableHeaderStyle " id="constituencyWiseTableExcelDivId" >';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b>DISTRICT</th></b>';
								str1+='<th class="text-capital " ><b>Age Range</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>Renewal</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str1+='<th class="text-capital "><b>New Cadre</b></th>';
								str1+='<th class="text-capital "><b>New Cadre %</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str1+='<tr>';
										str1+='<td>'+result[i].name+'</td>';
										str1+='<td>'+result[i].subList[j].name+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].newCadre+'</td>';
										str1+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str1+='</tr>';
									}
								} 
						}
						str1+='</tbody>';
						str1+='</table>';
						//for fetching all records end
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#ageWiseConstituencyDetails").html("NO DATA AVAILABLE");
		}
		
		$("#ageWiseConstituencyDetails").html(str);
		$("#excelDivageWiseConstituencyDetails").html(str1);
		$('.constituencyWiseDataTableId').DataTable();
	}
	
	$(document).on("click","#constituencyWiseExcelReport",function(){
		generateExcelReportConstituencyWiseAgeReport();	
	});
	
	function generateExcelReportConstituencyWiseAgeReport(){
		tableToExcel(constituencyWiseTableExcelDivId, 'CONSTITUENCY AGE WISE REPORT'); 
	}
	//Constituency Wise Report End
	
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