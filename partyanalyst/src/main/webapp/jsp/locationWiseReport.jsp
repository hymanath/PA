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
			<h4 class="panel-title" >LOCATION WISE GRIEVANCE REPORT <a class="btn btn-success pull-right" href="agentWiseReportAction.action" style="margin-top: -8px;" > User Tracking View  </a></h4>
		</div>
		<div class="col-md-1 col-xs-12 col-sm-4 pull-right m_top20">
					<div class="input-group dateRangePickerCls m_top5 pull-right" >
						<input type="text" class="form-control" style="width:180px" id="dateRangePickerAUM" onChange="getAlertStatusCount('districtDivId','district',0,'','')";/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">
				<div class="panel-body bg_EF">
					<div id="districtDivId"></div>
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
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
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
		str+='<h4>'+locatinName.toUpperCase()+' DISTRICT - MANDAL WISE</h4>';
	}	
	else if(type == "panchayat"){
		str+='<div class="tableStyledP">';
		str+='<h4>'+locatinName.toUpperCase()+' MANDAL - PANCHAYAT WISE</h4>';
	}	
	else if(type == "hamlets"){
		str+='<div class="tableStyledH">';
		str+='<h4>'+locatinName.toUpperCase()+' PANCHAYAT - HABITATION WISE</h4>';
	}else{
		str+='<div class="tableStyled">';
	}
	
		str+='<table class="table" >';
		str+='<thead>';
		str+='<tr>';
	if(type == "mandals"){
		str+='<div class="tableStyledM">';
		str+='<th  style="text-align:center;">Mandal/Munci. Name</th>';
	}	
	else if(type == "panchayat"){
		str+='<th  style="text-align:center;">Panchayat Name</th>';
	}	
	else if(type == "hamlets"){
		str+='<th  style="text-align:center;">Hamlet Name</th>';
	}else{
		str+='<th  style="text-align:center;">District Name</th>';
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
					str+='<td ><span class="districtCls glyphicon glyphicon-plus districtPlusCls" style="cursor:pointer;text:bold;" attr_divId="mandalWiseDivId'+result[i].id+'" attr_type="mandals" attr_locaton_id="'+result[i].id+'" attr_trId="mandalWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				else if(type == "mandals"){
					str+='<tr class="mandallls success" id="panchayatWiseTrId'+result[i].id+'" >';
					str+='<td ><span class="districtCls glyphicon glyphicon-plus mandalCls" style="cursor:pointer;text:bold;" attr_divId="panchayatWiseDivId'+result[i].id+'" attr_type="panchayat" attr_locaton_id="'+result[i].id+'" attr_trId="panchayatWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				else if(type == "panchayat"){
					str+='<tr  class="hamlettsCls warning"  id="hamletWiseTrId'+result[i].id+'"  >';
					str+='<td ><span class="districtCls glyphicon glyphicon-plus panchayatCls" style="cursor:pointer;text:bold;" attr_divId="hamletWiseDivId'+result[i].id+'" attr_type="hamlets" attr_locaton_id="'+result[i].id+'" attr_trId="hamletWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				else if(type == "hamlets"){
					str+='<tr class="danger">';
					str+='<td ><span class="districtCls" style="cursor:pointer;text:bold;" attr_location_name="'+result[i].name+'">&nbsp;'+result[i].name+'</span></td>';
				}
				if(type == "district")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="district" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls">'+result[i].totalCount+'</td>';
				else if(type == "mandals")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="tehsil" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls">'+result[i].totalCount+'</td>';
				else if(type == "panchayat")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="panchayat" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls">'+result[i].totalCount+'</td>';
				else if(type == "hamlets")
					str+='<td style="text-align:center;"><span attr_location_id="'+result[i].id+'" attr_location_type="hamlet" attr_location_name="'+result[i].name+'" style="cursor:pointer;text-decoration:underline;" class="alertDetailsCls">'+result[i].totalCount+'</td>';				
				
				if(result[i].list != null && result[i].list.length > 0){
					for(var j in result[i].list){
						if(result[i].list[j].count != null && result[i].list[j].count != 0)
							str+='<td style="text-align:center">'+result[i].list[j].count+'</td>';
						else
							str+='<td style="text-align:center;"> - </td>';
					}
				}
				str+='</tr>';
				if(type == "district"){
					str+='<tr id="dmandalWiseTrId'+result[i].id+'" class="districttCls" style="display:none;">';
						str+='<td colspan="14">';
							str+='<div id="mandalWiseDivId'+result[i].id+'"></div>'
						str+='</td>';
					str+='</tr>';
				}
				else if(type == "mandals"){
					str+='<tr id="dpanchayatWiseTrId'+result[i].id+'"  class="mandallsCls" style="display:none;">';
						str+='<td colspan="14">';
							str+='<div id="panchayatWiseDivId'+result[i].id+'" ></div>'
						str+='</td>';
					str+='</tr>';
				}
				else if(type == "panchayat"){
					str+='<tr id="dhamletWiseTrId'+result[i].id+'"  class="hamletsCls" style="display:none;">';
						str+='<td colspan="14">';
							str+='<div id="hamletWiseDivId'+result[i].id+'" ></div>'
						str+='</td>';
					str+='</tr>';
				}
			}
				str+='<tr>';
				str+='<td>Total</td>';
				str+='<td>'+total+'</td>';
				if(result[0].subList != null && result[0].subList.length > 0){
					for(var i in result[0].subList){
						if(result[0].subList[i].count != null && result[0].subList[i].count != 0)
							str+='<td style="text-align:center">'+result[0].subList[i].count+'</td>';
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
	
			
$(document).on("click",".districtCls",function(){
	var divId = $(this).attr("attr_divId");
	var type = $(this).attr("attr_type");
	var locationId = $(this).attr("attr_locaton_id");
	var locatinTrId = $(this).attr("attr_trId");
	var locatinName = $(this).attr("attr_location_name");
	getAlertStatusCount(divId,type,locationId,locatinTrId,locatinName);
});
$(document).on("click",".alertDetailsCls",function(){
	var locationId = $(this).attr("attr_location_id");
	var locatinType = $(this).attr("attr_location_type");
	var locationName = $(this).attr("attr_location_name");
	getGovtGrievanceAlertDetails(locationId,locatinType,locationName);
})
function getGovtGrievanceAlertDetails(locationId,locatinType,locationName){
		$("#locationReportModalDivId").modal('show');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangePickerAUM').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
      	$('#buildLocationReportDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');		  
		var jsObj ={
			mobileNo: "",
			locatoinType:locatinType,
			locationId : locationId,
			fromDate : fromDateStr,
			toDate : toDateStr
		}
		$.ajax({
			type:'GET',
			url: 'getGovtGrievanceAlertDetailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGovtGrievanceAlertDetails(result,locationName,locatinType);
			}else{
			 $("#buildLocationReportDivId").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>"); 
		  }	  
		});
	}
	
	function buildGovtGrievanceAlertDetails(result,locationName,locatinType){
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
	str +='<th style="text-transform: uppercase;"> Created By</th>';
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
			str+='<td style="text-transform: uppercase;">'+result[i].title+'</td>';
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
		str+='</tr>';
		
		}
    str +='</tbody>';
	str +='</table>';
	str +='</div>';
	$("#buildLocationReportDivId").html(str); 
	$("#partMeetingModalData").dataTable();
	
	}
$(document).on("click",".districtPlusCls",function(){
	 if($(this).hasClass("glyphicon-plus") == true){
		$(".districtPlusCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
} 
});
$(document).on("click",".mandalCls",function(){
	 if($(this).hasClass("glyphicon-plus") == true){
		$(".mandalCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
} 
});
$(document).on("click",".panchayatCls",function(){
	 if($(this).hasClass("glyphicon-plus") == true){
		$(".panchayatCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
} 
});

//getCadreGreivienceEfficiency();
function getCadreGreivienceEfficiency(){
	$('#summaryDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');	
   
    var jobj = {
      
      daysArr : [7,30,60,90,180,365]
      
    }
    $.ajax({
      type : "POST",
      url  : "getStatusWiseAlertsCountSummeryAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jobj)},
    }).done(function(result){
      if(result != null && result.length > 0)
		buildCadreGreivienceEfficiencySummary(result);
	else
		$('#summaryDivId').html("NO DATA AVAILABLE.");
    });
}

function buildCadreGreivienceEfficiencySummary(result){
  var str="";
		str+='<table class="table" id="GreivienceEfficiencySummaryDatatable">';
		str+='<thead>';
		//str+='<tr>';
			str+='<th  style="text-align:center;">Status</th>';
			//str+='<th  style="text-align:center">Total</th>';
			if(result[1].effcncyRslts != null && result[1].effcncyRslts.length > 0){
				for(var i in result[1].effcncyRslts)
				str+='<th style="text-align:center">'+result[1].effcncyRslts[i].effcncyType+'</th>';
			}
		//str+='</tr>';	
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			if(result[i].id != 1){
				str+='<tr>';
					str+='<td>'+result[i].name+'<td>';
					if(result[i].effcncyRslts != null && result[i].effcncyRslts.length > 0){
						for(var j in result[i].effcncyRslts){
							if(result[i].effcncyRslts[j].effcncyAlerts != null)
								str+='<td>'+result[i].effcncyRslts[j].effcncyAlerts+'</td>';
							else
								str+='<td> - </td>';
						}
					}
					str+='</tr>';
			}
			
		}
			str+='</tbody>';
		str+='</table>';
		$("#summaryDivId").html(str);
		$("#GreivienceEfficiencySummaryDatatable").dataTable();
}
</script>
</body>
</html>