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
		.tableStyledH {background-color:#e6f1f2 !important;padding:5px!important}
		
		.tableStyledM thead{background-color:#fff !important;padding:5px!important}
		.tableStyledP thead{background-color:#e5fafc !important;padding:5px!important}
		.tableStyledH thead{background-color:#e6f1f2 !important;padding:5px!important}
		
		
	</style>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12" style="padding: 20px;">	
			
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-6">
					<h4 class="panel-title" >LOCATION WISE GRIEVANCE REPORT  <a class="btn btn-success pull-right" href="agentWiseReportAction.action" style="margin-top: -8px;" > User Tracking View  </a> </h4>
				</div>
				<div class="col-md-1 col-xs-12 col-sm-4 pull-right m_top20">
						<div class="input-group dateRangePickerCls m_top5 pull-right" >
							<input type="text" class="form-control" style="width:180px" id="dateRangePickerAUM" onChange="getAlertStatusCount('districtDivId','district',0,'','')";/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
				</div>
				
					
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
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">
				<div class="panel-body bg_EF">
					<div id="districtDivId"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
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
		}
	});
}	

function buildAlertStatusDetails(result,divId,type,locatinName){
	var str="";
	
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
	
		str+='<table class="table table-condensed" >';
		str+='<thead>';
		str+='<tr>';
			str+='<th  style="text-align:center;">Location Name</th>';
			str+='<th  style="text-align:center">Total</th>';
			if(result[0].list != null && result[0].list.length > 0){
				for(var i in result[0].list)
				str+='<th  style="text-align:center">'+result[0].list[i].name+'</th>';
			}
		str+='</tr>';	
		str+='</thead>';
		
		str+='<tbody>';
			for(var i in result){
				if(type == "district"){
					str+='<tr class="districtttCls" id="mandalWiseTrId'+result[i].id+'">';
					str+='<td ><span class="districtCls" style="cursor:pointer;text:bold;" attr_divId="mandalWiseDivId'+result[i].id+'" attr_type="mandals" attr_locaton_id="'+result[i].id+'" attr_trId="mandalWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">'+result[i].name+'</span></td>';
				}
				else if(type == "mandals"){
					str+='<tr class="mandallls" id="panchayatWiseTrId'+result[i].id+'" >';
					str+='<td ><span class="districtCls" style="cursor:pointer;text:bold;" attr_divId="panchayatWiseDivId'+result[i].id+'" attr_type="panchayat" attr_locaton_id="'+result[i].id+'" attr_trId="panchayatWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">'+result[i].name+'</span></td>';
				}
				else if(type == "panchayat"){
					str+='<tr  class="hamlettsCls"  id="hamletWiseTrId'+result[i].id+'"  >';
					str+='<td ><span class="districtCls" style="cursor:pointer;text:bold;" attr_divId="hamletWiseDivId'+result[i].id+'" attr_type="hamlets" attr_locaton_id="'+result[i].id+'" attr_trId="hamletWiseTrId'+result[i].id+'" attr_location_name="'+result[i].name+'">'+result[i].name+'</span></td>';
				}
				else if(type == "hamlets"){
					str+='<tr>';
					str+='<td ><span class="districtCls" style="cursor:pointer;text:bold;" attr_location_name="'+result[i].name+'">'+result[i].name+'</span></td>';
				}
				str+='<td style="text-align:center;">'+result[i].totalCount+'</td>';
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
</script>
</body>
</html>