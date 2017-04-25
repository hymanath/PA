<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location Wise Grivance Report</title>
<!-- Bootstrap -->
	<!--<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">-->
	<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
	<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link rel="stylesheet" href="dist/css/font-awesome.css">
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	
	
	<style>
		.m_top20{margin-top:20px}
		.liStyle li{padding-left:15px !important}
		
		.tableStyled thead {border-left:1px solid #ddd !important;border-top:1px solid #ddd !important;border-right:1px solid #ddd !important;padding:8px !important}
		.tableStyled tbody{border-left:1px solid #ddd !important;border-top:1px solid #ddd !important;border-right:1px solid #ddd !important;padding:8px !important;background-color:#D9EDF6 !important}
		
		.tableStyledM tbody{border-left:1px solid #ddd !important;border-top:1px solid #ddd !important;border-right:1px solid #ddd !important;padding:8px !important;background-color:#fff !important}
		.tableStyledP tbody{border-left:1px solid #ddd !important;border-top:1px solid #ddd !important;border-right:1px solid #ddd !important;padding:8px !important;background-color:#e5fafc !important}
		.tableStyledH tbody{border-left:1px solid #ddd !important;border-top:1px solid #ddd !important;border-right:1px solid #ddd !important;padding:8px !important;background-color:#e6f1f2 !important}
		
		.tableStyledM {background-color:#fff !important;padding:5px!important}
		.tableStyledP {background-color:#e5fafc !important;padding:5px!important}
		<!--.tableStyledH {background-color:#e6f1f2 !important;padding:5px!important}-->
		
		.tableStyledM thead{background-color:#fff !important;padding:5px!important}
		.tableStyledP thead{background-color:#e5fafc !important;padding:5px!important}
		.tableStyledH thead{background-color:#fff !important;padding:5px!important}
		
		#efficiencyId td{font-weight:bold;}
	</style>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12" style="padding: 20px;">	
			<div class="row">
				<!--<div class="col-md-12 col-xs-12 col-sm-6">
					<h4 class="panel-title" >LOCATION WISE GRIEVANCE SUMMARY REPORT <a class="btn btn-success pull-right" href="agentWiseReportAction.action" style="margin-top: -8px;" > User Tracking View  </a></h4>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="panel panel-default m_top10">
						<div class="panel-body bg_EF">
							<div id="summaryDivId"></div>
						</div>
					</div>
				</div>-->
				
					
				<!--<div class="col-md-5 col-xs-12 col-sm-6">
				<ul class="list-inline m_top20 liStyle daterangeValuesCls">
					<li class="btn btn-success" attr_date_type="Today">Today</li>
					<li attr_date_type="ThisWeek">This&nbsp;Week</li>
					<li attr_date_type="LastWeek">Last&nbsp;Week</li>
					<li attr_date_type="ThisMonth">This&nbsp;Month</li>
					<li attr_date_type="LastMonth">Last&nbsp;Month</li>
				</ul>
				</div>-->
				
				<!--<div class="col-md-3 col-xs-12 col-sm-6" >
				<ul class="list-inline m_top20" style="border: 1px solid rgb(221, 221, 221); border-radius: 61px; height: 36px; width: 123px;">
					<li class="btn btn-success" style="border-radius: 12px; padding: 7px; margin-left: -3px;">RURAL</li>
					<li class="" style="border-radius: 12px;">URBAN</li>
				</ul>
				</div>-->
			</div>
		</div>
	</div>
	<div class="row">
		
		<div class="col-md-12 col-xs-12 col-sm-6">
			<h4 class="m_0" id="cadreGrievanceTitle" style="display:block;"> Alert Efficiency 
			<div style="text-align:right"><input id="proposalId" onClick="getCadreGreivienceEfficiency()" class="form-check-input" type="checkbox" value="Include" checked>   
			<span style="font-size:12px;">Include Proposal</span></div>
			</h4>
			<div id="efficiencyId"></div>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-6 m_top20">
		
			<h4>LOCATION WISE GRIEVANCE REPORT <a class="btn btn-success pull-right btn-sm" href="agentWiseReportAction.action" style="margin-top: -8px;" > User Tracking View  </a></h4>
		</div>
		<div class="col-md-1 col-xs-12 col-sm-4 pull-right m_top20">
					<div class="input-group dateRangePickerCls m_top5 pull-right" >
						<input type="text" class="form-control" style="width:180px" id="dateRangePickerAUM" onChange="getAlertStatusCount('districtDivId','district',0,'','')";/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
				<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">
				<div class="panel-body bg_EF">
					<div id="districtDivId"></div>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>

<div class="modal fade" id="locationReportModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document" style="width:85%;">
    <div class="modal-content customModal">
      <div class="modal-header">
       <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalHeadingId"></h4>
      </div>
      <div class="panel-body">
		<div id="buildLocationReportDivId">
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       <!--<button type="button" class="btn btn-primary" onClick="saveAlertStatusDetails();" >Save changes</button>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- Modal for Alert -->
<div class="modal" tabindex="-1" role="dialog" id="cdrModelDivId">
  <div class="modal-dialog modal-lg">       
	<div class="modal-content" style="border-radius:0px">
	  <div class="modal-header" style="background-color:#999999">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title">Alert Details</h4>  
	  </div>
	  <div class="modal-body"> 
		<div class="row">
		<span>
			<img src='images/Loading-data.gif' style="border-right:1px solid #00B17D;display:none;" id="imgPrcsingId"/>
	   </span>
			<div class="table-responsive" >
				<table class="table table-bordered tableCategory">
					<tr>
						<td style="vertical-align: top;display:none;" id="alertTypeTrId">
							<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;type</b></span></p>
							<p><span  id="typeId"></span></p>
						</td>
						<td style="vertical-align: top;display:none;" id="alertCreatedTrId">
							<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;created&nbsp;date</b></span></p>
							<p><span  id="createdDate"></span></p>
						</td>
						<td style="vertical-align: top;display:none;" id="alertStatusTrId">
							<p class="text-capital"><span class="text-muted"><b>Alert&nbsp;status</b></span></p>
							<p><span id="alertStatus"></span></p>
						</td>
						<td id="severityTdId" style="vertical-align: top;display:none;" >
							<p class="text-capital"><span class="text-muted"><b>severity</b></span></p>
							<p><span class="circle severityIdColorCls"></span><span  id="severityId">Critical</span></p>
						</td>
						<td style="vertical-align: top;display:none;" id="alertImLvelId">
							<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;impact&nbsp;level</b></span></p>
							<p style="text-transform: lowercase;"><span  id="levelId"></span></p>
						</td>
						<td style="vertical-align: top;display:none;" id="locationTrId">
							<p class="text-capital"><span class="text-muted "><b>Alert&nbsp;location</b></span></p>
							<p><span  id="LocationId"></span></p>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="display:none;" id="titleTrId">
							<p class="text-capital"><span class="text-muted "><b>Title</b></span></p>
							<p><span  id="titleId"></span></p>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="display:none;" id="descTrId">
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
					<tr style="display:none" id="groupArticlesId">
						<td colspan="8">
						<p class="text-capital"><span class="text-muted ">Grouped Articles  </span> :
							<ul class="list-inline imageUrlUlCls groupArticlesCls"></ul>
						</td>
					</tr>
					<tr id="existingDocsTrId" style="display:none;">
						<td id="existingDocsTdId" colspan="8"></td>
					</tr>
					<!--<tr>
						<td colspan="8">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
						<form id="alertDocs" name="alertDocs">
							<h4 class="m_0 text-success font_weight" style="">UPLOAD SCAN COPY</h4>  
							<input type="file" id="alertScanCopyId" multiple="multiple"  name="files[]" class="m_top20"/>
							<input type="hidden" id="alertHiddenId" name="alertId">
							<p id="errFileId" class="text-center text-danger"></p>
							<center><button type="button" style="width:38%" class="btn btn-success" id="uploadAlertDocBtnId">Upload</button></center>
						</form>										
						</div>
						</td>
					</tr>-->
				</table>
			</div>
						
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10" style="display:none;" id="commnetsTrId">
				<h4 class="panel-title text-capital">alert status tracking comments</h4>
				
				<div id="alertCommentsDivIdNew"></div>
				<!--<div  id="alertCommentsDiv"></div>-->
			</div>	
		</div>
	  </div>
	  <div class="modal-footer">     
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	</div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

<input type="hidden" id="hidenLocationId"></input>
<input type="hidden" id="hidenLocationName"></input>


<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script type="text/javascript">
var currentFromDate=moment().format("DD/MM/YYYY");
var currentToDate=moment().format("DD/MM/YYYY");
$("#dateRangePickerAUM").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			
			'Today' : [moment(), moment()],         
			'Yesterday' : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],               
			'This Month': [moment().startOf('month'), moment()],          
			'Last 30 Day': [moment().subtract(29, 'days'), moment()],    
			'Last 1 Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 3 Months': [moment().subtract(3, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 6 Months': [moment().subtract(6, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'This Year': [moment().startOf('Year'), moment()],
			'Last 1 Year': [moment().subtract(1, 'Year').startOf('Year'), moment().subtract(1, 'Year').endOf('Year')], 
		}
	});
	
	
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');
		
	});
	$(document).on("click",".daterangeValuesCls li",function(){
		var date = $(this).attr("attr_date_type");
			var momentDate = '';
			if(date == 'Today')
			{
				currentFromDate = moment().format('DD-MM-YYYY');
				currentToDate = moment().format('DD-MM-YYYY');
			}else if(date == 'ThisWeek'){
				currentFromDate = moment().subtract(1,'week').format('DD-MM-YYYY');
				currentToDate = moment().format('DD-MM-YYYY');
			}else if(date == 'LastWeek'){
				currentFromDate = moment().subtract(1, 'week').startOf('week'), moment().subtract(1, 'week').endOf('week');
				currentToDate = moment().format('DD-MM-YYYY');
			}else if(date == 'ThisMonth'){
				currentFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
				currentToDate = moment().format('DD-MM-YYYY');
			}else if(date == 'LastMonth'){
				currentFromDate = moment().subtract(1, 'month');
				currentToDate = moment().format('DD-MM-YYYY');
			}
			$(".liclsChange").removeClass("active");
			$(".addActiveCls").addClass("active");
		
	});	
getAlertStatusCount("districtDivId","district",0,"","");
function getAlertStatusCount(divId,type,locationId,locatinTrId,locatinName){
	$("#"+divId).html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
	
	var locatinId;
	var locationType;
	$("#"+locatinTrId).show();
	//$("#"+locatinTrId).html("<img src='images/Loading-data.gif'/>");
	if(type == "district"){
		locatinId=0;
		locationType = "";
	}else if(type == "mandals" || type == "panchayat" || type == "hamlets"){
		locatinId=locationId;
		locationType = type;
	}
	
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangePickerAUM').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
	
	var jObj = {
		locationId : locatinId,
		locationType : locationType,
		searchType : "rural",
		startDate :fromDateStr,//'12/04/2017',
		endDate :toDateStr//'14/04/2017' ,
		
	}
	$.ajax({
		type:'POST',
		url: 'getAlertStatusCountAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildAlertStatusDetails(result,divId,type,locatinName);
		}else{
			$("#"+divId).html("NO DATA AVAILABLE");
		}
	});
}	

function buildAlertStatusDetails(result,divId,type,locatinName){
	var str="";
	var total = 0;
	if(type == "mandals"){
		str+='<div class="tableStyledM">';
		str+='<h4>'+locatinName.toUpperCase()+' DISTRICT - MANDAL WISE<span class="pull-right clearInnerTableCls" attr_div_id="'+divId+'"><i class="glyphicon glyphicon-remove" style="font-size: 15px;" title="Close"></i></span></h4>';
	}	
	else if(type == "panchayat"){
		str+='<div class="tableStyledP">';
		str+='<h4>'+locatinName.toUpperCase()+' MANDAL - PANCHAYAT WISE<span class="pull-right clearInnerTableCls" attr_div_id="'+divId+'"><i class="glyphicon glyphicon-remove" style="font-size: 15px;" title="Close"></i></span></h4>';
	}	
	else if(type == "hamlets"){
		str+='<div class="tableStyledH">';
		str+='<h4>'+locatinName.toUpperCase()+' PANCHAYAT - HABITATION WISE<span class="pull-right clearInnerTableCls" attr_div_id="'+divId+'"><i class="glyphicon glyphicon-remove" style="font-size: 15px;" title="Close"></i></span></h4>';
	}else{
		str+='<div class="tableStyled">';
	}
	
		str+='<table class="table" >';
		str+='<thead>';
		str+='<tr>';
	if(type == "mandals"){
		//str+='<div class="tableStyledM">';
		str+='<th  style="text-align:center;width:160px;height:20px;">Mandal/Munci. Name</th>';
	}	
	else if(type == "panchayat"){
		str+='<th  style="text-align:center;width:160px;height:20px;">Panchayat Name</th>';
	}	
	else if(type == "hamlets"){
		str+='<th  style="text-align:center;width:160px;height:20px;">Hamlet Name</th>';
	}else{
		str+='<th  style="text-align:center; width:160px;height:20px;">District Name</th>';
	}
	
			
			str+='<th  style="text-align:center">Total</th>';
			if(result[0].list != null && result[0].list.length > 0){
				for(var i in result[0].list)
				str+='<th  style="text-align:center">'+result[0].list[i].name+'</th>';
			}
		str+='</tr>';	
		str+='</thead>';
		
		str+='<tbody>';
			for(var i in result){
				total = total+result[i].totalCount;
				if(type == "district"){
					str+='<tr class="districtttCls info" id="mandalWiseTrId'+result[i].id+'">';
					str+='<td><span id="mandalWiseTrId'+result[i].id+'First" class="districtCls glyphicon glyphicon-plus districtPlusCls" style="cursor:pointer;text:bold;" attr_divId="mandalWiseDivId'+result[i].id+'" attr_type="mandals" attr_locaton_id="'+result[i].id+'" attr_trId="mandalWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				else if(type == "mandals"){
					str+='<tr class="mandallls success" id="panchayatWiseTrId'+result[i].id+'" >';
					str+='<td ><span id="panchayatWiseTrId'+result[i].id+'First" class="districtCls glyphicon glyphicon-plus mandalCls" style="cursor:pointer;text:bold;" attr_divId="panchayatWiseDivId'+result[i].id+'" attr_type="panchayat" attr_locaton_id="'+result[i].id+'" attr_trId="panchayatWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				else if(type == "panchayat"){
					str+='<tr  class="hamlettsCls warning"  id="hamletWiseTrId'+result[i].id+'"  >';
					str+='<td ><span id="hamletWiseTrId'+result[i].id+'First" class="districtCls glyphicon glyphicon-plus panchayatCls" style="cursor:pointer;text:bold;" attr_divId="hamletWiseDivId'+result[i].id+'" attr_type="hamlets" attr_locaton_id="'+result[i].id+'" attr_trId="hamletWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				else if(type == "hamlets"){
					str+='<tr class="danger">';
					str+='<td ><span class="districtCls" style="cursor:pointer;text:bold;" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				if(type == "district")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="district" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0 attr_status="">'+result[i].totalCount+'</span></td>';
				else if(type == "mandals")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="tehsil" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0 attr_status="">'+result[i].totalCount+'</span></td>';
				else if(type == "panchayat")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="panchayat" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0 attr_status="">'+result[i].totalCount+'</span></td>';
				else if(type == "hamlets")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="hamlet" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0 attr_status="" >'+result[i].totalCount+'</span></td>';				
				
				if(result[i].list != null && result[i].list.length > 0){
					for(var j in result[i].list){
						if(result[i].list[j].count != null && result[i].list[j].count != 0){
							if(type == "district")
								str+='<td style="text-align:center"><span attr_location_id="'+result[i].id+'" attr_location_type="district" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[i].list[j].id+'" attr_status="'+result[i].list[j].name+'">'+result[i].list[j].count+'</span></td>';
							else if(type == "mandals")
								str+='<td style="text-align:center"><span attr_location_id="'+result[i].id+'" attr_location_type="tehsil" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[i].list[j].id+'" attr_status="'+result[i].list[j].name+'">'+result[i].list[j].count+'</span></td>';
							else if(type == "panchayat")
								str+='<td style="text-align:center"><span attr_location_id="'+result[i].id+'" attr_location_type="panchayat" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[i].list[j].id+'" attr_status="'+result[i].list[j].name+'">'+result[i].list[j].count+'</span></td>';
							else if(type == "hamlets")
								str+='<td style="text-align:center"><span attr_location_id="'+result[i].id+'" attr_location_type="hamlet" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[i].list[j].id+'" attr_status="'+result[i].list[j].name+'">'+result[i].list[j].count+'</span></td>';
						}
						else
							str+='<td style="text-align:center;"> - </td>';
					}
				}
				str+='</tr>';
				if(type == "district"){
					str+='<tr id="dmandalWiseTrId'+result[i].id+'" class="districttCls" style="display:none;">';
						str+='<td colspan="15">';
							str+='<div id="mandalWiseDivId'+result[i].id+'" attr_parent_id="dmandalWiseTrId'+result[i].id+'" attr_super_parent_div_id="mandalWiseTrId'+result[i].id+'"></div>'
						str+='</td>';
					str+='</tr>';
				}
				else if(type == "mandals"){
					str+='<tr id="dpanchayatWiseTrId'+result[i].id+'"  class="mandallsCls" style="display:none;">';
						str+='<td colspan="15">';
							str+='<div id="panchayatWiseDivId'+result[i].id+'" attr_parent_id="dpanchayatWiseTrId'+result[i].id+'" attr_super_parent_div_id="panchayatWiseTrId'+result[i].id+'"></div>'
						str+='</td>';
					str+='</tr>';
				}
				else if(type == "panchayat"){
					str+='<tr id="dhamletWiseTrId'+result[i].id+'"  class="hamletsCls" style="display:none;">';
						str+='<td colspan="15">';
							str+='<div id="hamletWiseDivId'+result[i].id+'" attr_parent_id="dhamletWiseTrId'+result[i].id+'" attr_super_parent_div_id="hamletWiseTrId'+result[i].id+'"></div>'
						str+='</td>';
					str+='</tr>';
				}
			}
				str+='<tr>';
				str+='<td>Total</td>';
				if(type == "district")
					str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="" attr_location_name="" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0>'+total+'</span></td>';
				else if(type == "mandals")
					str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="district" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0>'+total+'</span></td>';
				else if(type == "panchayat")
					str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="tehsil" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0>'+total+'</span></td>';
				else if(type == "hamlets")
					str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="panchayat" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0>'+total+'</span></td>';
				else
					str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="hamlet" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id=0>'+total+'</span></td>';
				if(result[0].subList != null && result[0].subList.length > 0){
					for(var i in result[0].subList){
						if(result[0].subList[i].count != null && result[0].subList[i].count != 0){
							if(type == "district")
								str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="" attr_location_name="" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[0].subList[i].id+'">'+result[0].subList[i].count+'</td>';
							else if(type == "mandals")
								str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="district" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[0].subList[i].id+'">'+result[0].subList[i].count+'</span></td>';
							else if(type == "panchayat")
								str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="tehsil" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[0].subList[i].id+'">'+result[0].subList[i].count+'</span></td>';
							else if(type == "hamlets")
								str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="panchayat" attr_location_name=0 style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[0].subList[i].id+'">'+result[0].subList[i].count+'</span></td>';				
							else
								str+='<td style="text-align:center"><span attr_location_id=0 attr_location_type="hamlet" attr_location_name="Total" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls" attr_status_id="'+result[0].subList[i].id+'">'+result[0].subList[i].count+'</span></td>';
							}
						else
							str+='<td style="text-align:center;"> - </td>';
					}
				}
				str+='</tr>';
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#"+divId).html(str);
	
}

$(document).on("click",".districtttCls",function(){
	$('.districttCls').hide();
	var id = $(this).attr('id');
	$('#d'+id+'').show();
});


$(document).on("click",".mandallls",function(){
	$('.mandallsCls').hide();
	var id = $(this).attr('id');
	$('#d'+id+'').show();
});



$(document).on("click",".hamlettsCls",function(){
	$('.hamletsCls').hide();
	var id = $(this).attr('id');
	$('#d'+id+'').show();
});
	
$(document).on("click",".districtPlusCls",function(){
	var divId = $(this).attr("attr_divId");
	 if($(this).hasClass("glyphicon-plus") == true){
		var type = $(this).attr("attr_type");
		var locationId = $(this).attr("attr_locaton_id");
		var locatinTrId = $(this).attr("attr_trId");
		var locatinName = $(this).attr("attr_location_name");
		$("#hidenLocationId").val(locationId);
		$("#hidenLocationName").val(locatinName);
		getAlertStatusCount(divId,type,locationId,locatinTrId,locatinName);
		$(".districtPlusCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$("#"+divId).html("");
		var parentTrId = $(this).attr("attr_trid");
		$("#d"+parentTrId).hide();
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
} 
});
$(document).on("click",".mandalCls",function(){
	var divId = $(this).attr("attr_divId");
	 if($(this).hasClass("glyphicon-plus") == true){
		 var type = $(this).attr("attr_type");
		var locationId = $(this).attr("attr_locaton_id");
		var locatinTrId = $(this).attr("attr_trId");
		var locatinName = $(this).attr("attr_location_name");
		$("#hidenLocationId").val(locationId);
		$("#hidenLocationName").val(locatinName);
		getAlertStatusCount(divId,type,locationId,locatinTrId,locatinName);
		$(".mandalCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$("#"+divId).html("");
		var parentTrId = $(this).attr("attr_trid");
		$("#d"+parentTrId).hide();
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
} 
});
$(document).on("click",".panchayatCls",function(){
	var divId = $(this).attr("attr_divId");
	 if($(this).hasClass("glyphicon-plus") == true){
		  var type = $(this).attr("attr_type");
		var locationId = $(this).attr("attr_locaton_id");
		var locatinTrId = $(this).attr("attr_trId");
		var locatinName = $(this).attr("attr_location_name");
		$("#hidenLocationId").val(locationId);
		$("#hidenLocationName").val(locatinName);
		getAlertStatusCount(divId,type,locationId,locatinTrId,locatinName);
		$(".panchayatCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$("#"+divId).html("");
		var parentTrId = $(this).attr("attr_trid");
		$("#d"+parentTrId).hide();
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
} 
});
			
/* $(document).on("click",".districtCls",function(){
	var divId = $(this).attr("attr_divId");
	var type = $(this).attr("attr_type");
	var locationId = $(this).attr("attr_locaton_id");
	var locatinTrId = $(this).attr("attr_trId");
	var locatinName = $(this).attr("attr_location_name");
	$("#hidenLocationId").val(locationId);
	$("#hidenLocationName").val(locatinName);
	getAlertStatusCount(divId,type,locationId,locatinTrId,locatinName);
}); */
$(document).on("click",".alertDetailsCls",function(){
	var locationId = $(this).attr("attr_location_id");
	var locatinType = $(this).attr("attr_location_type");
	var locationName = $(this).attr("attr_location_name");
	var statusId = $(this).attr("attr_status_id");
	var status = $(this).attr("attr_status");
	getGovtGrievanceAlertDetails(locationId,locatinType,locationName,statusId,status);
})
function getGovtGrievanceAlertDetails(locationId,locatinType,locationName,statusId,status){
		$("#locationReportModalDivId").modal('show');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangePickerAUM').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  if(locationId == 0){
			  locationId =$("#hidenLocationId").val();
		  }
		  if(locationId == ""){
			  locationId =0;
		  }
		  if(locationName == ""){
			  locationName = "Total";
		  }
		  if(locationName == 0){
			  locationName = $("#hidenLocationName").val();;
		  }
		  
      	$('#buildLocationReportDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');		  
		var jsObj ={
			mobileNo: "",
			locatoinType:locatinType,
			locationId : locationId,
			fromDate : fromDateStr,
			toDate : toDateStr,
			alertStatusId : statusId
		}
		$.ajax({
			type:'GET',
			url: 'getGovtGrievanceAlertDetailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGovtGrievanceAlertDetails(result,locationName,locatinType,status);
			}else{
			 $("#buildLocationReportDivId").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>"); 
		  }	  
		});
	}
	
	function buildGovtGrievanceAlertDetails(result,locationName,locatinType,status){
	var str = '';
	//$("#modalHeadingId").html('++')
	$("#modalHeadingId").html(locationName.toUpperCase()+" "+locatinType.toUpperCase()+" ALERT DETAILS");
	str+='<div class="table-responsive">';
	str +='<table class="table bg_ED " id="partMeetingModalData">'; 
	str +='<thead>';
	str +='<th style="text-transform: uppercase;"> Date</th>';
	str +='<th style="text-transform: uppercase;"> Time</th>';
	str +='<th style="text-transform: uppercase;"> Location</th>';
	str +='<th style="text-transform: uppercase;"> Title</th>';
	str +='<th style="text-transform: uppercase;"> Discription</th>';
	str +='<th style="text-transform: uppercase;"> Related To</th>';
	str +='<th style="text-transform: uppercase;"> Problem</th>';
	str +='<th style="text-transform: uppercase;"> Status</th>';
	str +='<th style="text-transform: uppercase;"> Caller Name</th>';
	str +='<th style="text-transform: uppercase;"> Mobile No</th>';
	str +='</thead>';
	str +='<tbody>';
	for(var i in result){		
			str+='<tr>';
		if(result[i].date != null){
			str+='<td style="text-transform: uppercase;">'+result[i].date+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].time != null){
			str+='<td style="text-transform: uppercase;">'+result[i].time+'</td>';
		
		}else{
			str+='<td>-</td>';
		}
		if(result[i].tehsil != null){
			var locationName ="";
			if(result[i].district != null && result[i].district.length>0)
				locationName = locationName+" "+result[i].district+" District,<br> ";
			if(result[i].assembly != null && result[i].assembly.length>0)
				locationName = locationName+" "+result[i].assembly+" Assembly ,<br> ";
			if(result[i].tehsil != null && result[i].tehsil.length>0)
				locationName = locationName+" "+result[i].tehsil+" Mandal,<br> ";
			if(result[i].panchayat != null && result[i].panchayat.length>0)
				locationName = locationName+" "+result[i].panchayat+" Panchayat,<br> ";
			if(result[i].hamlet != null && result[i].hamlet.length>0)
				locationName = locationName+" "+result[i].hamlet+" Hamlet , <br>";
			if(result[i].leb != null && result[i].leb.length>0)
				locationName = locationName+" "+result[i].leb+" Munci/Corp/Greater City, <br>";
			if(result[i].ward != null && result[i].ward.length>0)
				locationName = locationName+" "+result[i].ward+"  ";
			
			str+='<td style="">'+locationName+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].title != null){
			str+='<td class="descAlertCls" style="cursor:pointer;" attr_alert_status="'+status+'" attr_alert_id="'+result[i].alertId+'"><strong><u>'+result[i].title+'</u></strong></td>';
			//<td style="text-transform: uppercase;" id="">'+result[i].title+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].description != null){
			str+='<td style="text-transform: uppercase;">'+result[i].description+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].relatedTo != null){
			str+='<td style="text-transform: uppercase;">'+result[i].relatedTo+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].problem != null){
			str+='<td style="text-transform: uppercase;">'+result[i].problem+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].status != null){
			str+='<td style="text-transform: uppercase;">'+result[i].status+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].createdBy != null){
			str+='<td style="text-transform: uppercase;">'+result[i].createdBy+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].mobileNo != null && result[i].mobileNo != ""){
			str+='<td style="text-transform: uppercase;">'+result[i].mobileNo+'</td>';
		}else{
			str+='<td>-</td>';
		}
		str+='</tr>';
		
		}
    str +='</tbody>';
	str +='</table>';
	str +='</div>';
	$("#buildLocationReportDivId").html(str); 
	$("#partMeetingModalData").dataTable();
	
}
 
 //function getAlertData()
//{
$(document).on("click",".descAlertCls",function(){
	var alertId = $(this).attr("attr_alert_id");
	getAlertData(alertId);
	getAlertStatusCommentsTrackingDetails(alertId);
	getDocumentsForAlert(alertId);
});
	
function getAlertData(alertId){	
	$("#typeId").html('');
	$("#severityId").html('');
	$("#createdDate").html('');
	$("#levelId").html('');
	$("#LocationId").html('');
	$("#statusId").html('');
	$("#descriptionId").html("");
	$("#titleId").html('');
	$("#alertStatus").html('');
	$(".imageUrlUlCls").html("");
	$("#cdrModelDivId").modal('show');
	$("#imgPrcsingId").show();
	
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
		$("#imgPrcsingId").hide();
		$("#alertTypeTrId").show();
		$("#alertCreatedTrId").show();
		$("#alertStatusTrId").show();
		$("#alertImLvelId").show();
		$("#locationTrId").show();
		$("#titleTrId").show();
		$("#descTrId").show();
		if(result != null)
		{
			buildAlertData(result);
			$("#groupArticlesId").hide();
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
		}
	});
//});	
	
}
function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId=result[i].categoryId;
		if(result[i].category !=null)
			$("#typeId").html('<b>'+result[i].alertType+'</b>');
			$("#severityId").html('<b>'+result[i].severity+'</b>');
			if(severityTdId ==1){
				$("#severityTdId").show();
			}else{
				$("#severityTdId").hide();
			}
			$(".severityIdColorCls").addClass('<b>'+result[i].severity+'</b>');
			$("#createdDate").html('<b>'+result[i].date+'</b>');
			$("#levelId").html('<b>'+result[i].regionScope+'</b>');
			var location ='';
			/* if(result[i].regionScope == "STATE")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p>';
			}
			else if(result[i].regionScope == "DISTRICT")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p>';
			}
			
			else if(result[i].regionScope == "CONSTITUENCY")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p>';
			}
			else if(result[i].regionScope == "MANDAL" || result[i].regionScope == "MUNICIPAL-CORP-GMC" )
			{
				
					if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T : '+result[i].locationVO.localEleBodyName+' </p>';	
				}
				else{
					location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M : '+result[i].locationVO.tehsilName+'</p>';
				}
			}
			
			else if(result[i].regionScope == "VILLAGE" || result[i].regionScope == "WARD" )
			{
			
				if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T : '+result[i].locationVO.localEleBodyName+' </p><p>W : '+result[i].locationVO.wardName+'</p>';	
				}
				else{
					location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M : '+result[i].locationVO.tehsilName+'</p><p> P : '+result[i].locationVO.villageName+'</p>';
				}
				
			}	 */
			
			if(result[i].locationVO.stateId !=null){
				location +='<b>'+result[i].locationVO.state+' , ';
			}
			if(result[i].locationVO.districtId !=null){
				location +=''+result[i].locationVO.districtName+' District, ';
			}
			if(result[i].locationVO.constituencyId !=null){
				location +=''+result[i].locationVO.constituencyName+' Constituency, ';
			}
			
			if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
				location +=''+result[i].locationVO.localEleBodyName+' Municipality, ';
			}
			if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
				location +=''+result[i].locationVO.tehsilName+' Mandal, ';
			}
			if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
				location +=''+result[i].locationVO.wardName+' Ward, ';
			}
			if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
				location +=''+result[i].locationVO.villageName+' Panchayat, </b>';
			}
			if(result[i].locationVO.hamletName !=null && result[i].locationVO.hamletName.length>0){
				location +=''+result[i].locationVO.hamletName+' Hamlet </b>';
			}
			
			
			
			$("#LocationId").html(''+location+'');
			$("#statusId").val(''+result[i].statusId+'');
			  $("#statusId").dropkick('refresh')
			$("#descriptionId").html('<b>'+result[i].desc+'</b>');
			$("#titleId").html('<b>'+result[i].title+'</b>');
			$("#alertStatus").html('<b>'+result[i].status+'</b>');
			
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
function alertComments(result)
{
	$("#commnetsTrId").show();
	var docName = '';
	var extName = [];
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){  
		$(".cadreAlertCommentsDivId").show();
		str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px;">';
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
			if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';    
				str+='</h4>';
			}else{
				str+='<h4 class="panel-title">'+result[i].status+'';
					str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';
				str+='</h4>';
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
						for(var j in result[i].sublist2){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date
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
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].sublist2[j].sublist[k][l].cadreName+'<br>';
												}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}           
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	/* var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable to Resolve","6":"Action Not Required"};
	statusId = statusId + 1;
	for(var i = statusId ; i <= 6 ; i++){
		str+='<div class="panel panel-default" style="cursor:no-drop;pointer-events: none;">';
		str+='<div class="panel-heading" role="tab" id="headingThree" style="cursor:no-drop;pointer-events: none;background-color:rgba(162, 134, 192,0.4)">';
		str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="cursor:no-drop;pointer-events: none;color:rgba(0,0,0,0.4)">';
		str+='<h4 class="panel-title">'+statusArr[i]+'</h4>';
		str+='</a>';     
		str+='</div>';
		str+='<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">';
		str+='</div>';
		str+='</div>';
	}   */      
	$("#cadreAlertCommentsDivIdNew").html(str);
	}else{
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
		statusId = result[i].statusId;
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
			if(result[i].currentSts == result[i].status)  
			{
				str+='<a role="button" class="alertCommentColapse" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
				str+='<h4 class="panel-title">'+result[i].status+'';
					str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';
				str+='</h4>';
			}else{
				str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="false" aria-controls="collapse'+i+'">';
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';    
				str+='</h4>';
			}
		
				str+='</a>';  
			str+='</div>';
			if(result[i].currentSts == result[i].status)
			{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
			}else{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
			}
				str+='<div class="panel-body" style="padding:5px;">';
					str+='<div class="row">';
						for(var j in result[i].sublist2){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date
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
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].sublist2[j].sublist[k][l].cadreName+'<br>';
												}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';          
													}
													str+='</ul>';              
												}
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}           
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	/* var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable to Resolve","6":"Action Not Required"};
	statusId = statusId + 1;
	for(var i = statusId ; i <= 6 ; i++){
		str+='<div class="panel panel-default" style="cursor:no-drop;pointer-events: none;">';
		str+='<div class="panel-heading" role="tab" id="headingThree" style="cursor:no-drop;pointer-events: none;background-color:rgba(162, 134, 192,0.4)">';
		str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="cursor:no-drop;pointer-events: none;color:rgba(0,0,0,0.4)">';
		str+='<h4 class="panel-title">'+statusArr[i]+'</h4>';
		str+='</a>';     
		str+='</div>';
		str+='<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">';
		str+='</div>';
		str+='</div>';
	}   */      
	$("#alertCommentsDivIdNew").html(str)
	}
}
function getAlertStatusCommentsTrackingDetails(alertId)
	{
		$("#alertCommentsDivIdNew").html('');
		$("#alertCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			alertId:alertId,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAlertStatusCommentsTrackingDetails.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			alertComments(result);
			//buildAlertCommentsForTracking(result);
		});
		
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

function getDocumentsForAlert(alertId){
	$("#existingDocsTdId").html('');
		var jsObj={
			alertId : alertId
		}  
					
		$.ajax({
			type : 'GET',
			url : 'getDocumentsForAlertAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#existingDocsTdId").html("");
			if(result != null && result.length > 0){
				var str='';
				str+='<p class="text-muted "><b>DOCUMENTS</b></p>';
				str+='<ul class="list-inline">';
				var orderNumber = 0;
					for(var i in result){
						orderNumber = orderNumber+1;
						var fullName = result[i].name;
						var nameArr = fullName.split(".");  
						var type = nameArr[1];	
						var orderNoStr='';
							if(i<9){
								orderNoStr ="0"+orderNumber;
							}else{
							  orderNoStr = orderNumber;	
							}
					    var attachment = orderNoStr+'&nbspAttachment.'+type;
					
						str+='<li id="document'+result[i].id+'" ><a href="/Reports/'+result[i].name+'" target="_blank" style="color:#000"><i class="glyphicon glyphicon-paperclip"></i><span class="border"> '+attachment+' </span></a></li>, ';
					}
				str+='</ul>';
				$("#existingDocsTdId").html(str);
				$("#existingDocsTrId").show();
			}
		});
	}
	
	$(document).on ("click",".clearInnerTableCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).html("");
		var parentDivId = $("#"+divId).attr("attr_parent_id");
		$("#"+parentDivId).hide();
		var superParentId = $("#"+divId).attr("attr_super_parent_div_id");
		$("#"+superParentId+"First").removeClass("glyphicon-minus").addClass("glyphicon-plus");
	});
	
	getCadreGreivienceEfficiency();
function getCadreGreivienceEfficiency(){
	$("#efficiencyId").html("");
	$("#efficiencyId").html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
    
	var deptIds=[];
	var sourceIds =[];
	var includeProposal = $("#proposalId").prop('checked');;

	deptIds.push(49);
	sourceIds.push(1);
	sourceIds.push(2);
	sourceIds.push(3);
    var jobj = {
      
      daysArr : [5,10,30,60,90,180,365],
	  deptIds :deptIds,
	  sourceIds:sourceIds,
      includeProposal : includeProposal
    }
    $.ajax({
      type : "POST",
      url  : "getAlertEfficiencyListAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jobj)},
    }).done(function(result){
      if(result!=null){
				var str = "";
				str +="<table class='table table-bordered bg-white' style='margin-bottom:20px;'>";
					str +="<tbody>";
						str +="<tr>";
							for(var i in result){
								str+="<td>"+result[i].effcncyType+"</td>";
							}
							str +="</tr>";
							str +="<tr>";
							for(var i in result){
								if(result[i].clrFrEffcncy=="red"){
									str+="<td class='text-danger'>"+result[i].effcncyPrcnt+" %</td>";
								}else{
									str+="<td class='text-success'>"+result[i].effcncyPrcnt+" %</td>";
								}
							}
						str +="</tr>";
					str +="</tbody>";
				str +="</table>";
			}
			
			$("#efficiencyId").html(str);
    });
}

</script>
</body>
</html>