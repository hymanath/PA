<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government Core DashBoard</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCoreDashBoard/img/fevicon.png">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/jquery.filer.css"  type="text/css" rel="stylesheet"/>
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css"  type="text/css" rel="stylesheet"/>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
<!-- YUI Dependency files (End) -->

<style type="text/css">
.eventsheader
{
	display:none;
}
</style>
</head>
<body>
<div  class="AMS AUMS">
	<!--<header>
		<nav class="navbar navbar-default navbarHeader">
		  <div class="container">
			<!-- Brand and toggle get grouped for better mobile display 
			<div class="navbar-header">
			  <a class="navbar-brand" href="#"><img src="newCoreDashBoard/img/APLOGO.jpg" class="img-responsive"/></a>
			</div>
		  </div><!-- /.container-fluid 
		</nav>
	</header>-->
	<section class="m_top20">
		<div class="container">
			<div class="row">
				<div class="input-group dateRangePickerCls m_top5 pull-right">
					<input type="text" class="form-control " style="width:180px" id="dateRangePickerAUM">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style">
					<div class="col-md-9 col-xs-12 col-sm-4">
						<div class="panel panel-default">
							<div class="panel-heading headingColor">
								<h4 class="panel-title text-capital fontColor">My Alerts - Status Overview</h4>
							</div>
							<div class="panel-body">
								<div id="alertStatusOverview"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-4">
						<div class="panel panel-default">
							<div class="panel-body" style="height: 370px;">
								<div style="text-align: center; margin-top: 40px;">
									<img src="newCoreDashBoard/img/govtAlertBill.png"/>
								</div>
								<div id="overDepartmentWiseAlertOverview"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style">
					<div class="panel panel-default">
						<div class="panel-heading headingColor">
							<h4 class="panel-title text-capital fontColor">Department Wise Alert Status</h4>
						</div>
						<div class="panel-body">
							<div id="departmentWiseAlertGraphDiv"></div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 bg_Style detailedBlockShow" style="display:none;">
					<div class="panel panel-default">
						<div class="panel-heading headingColor">
							<h4 class="panel-title text-capital fontColor">Detailed Information</h4>
						</div>
						<div class="panel-body">
							<div id="detailedInfoBlockDiv"></div>
						</div>
					</div>
				</div>
				<form id="alertAssign" name="alertAssignForm">
					<!--<div class="row m_top20">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading headingColor">
									<h4 class="panel-title text-capital" id="assignedOfcrCountId"></h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<div id="assignedOfficersId"></div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgDeptId"></span></label>
											<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	
												<option></option>
											</select>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgLvlId"></span></label>
											<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">	
												<option></option>
											</select>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6" id="constituencyLevelDiv" style="display:none;">
											<label>Constituency</label>
											<select class="chosenSelect" id="constLvlId">	
												<option></option>
											</select>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6" id="mndlMuncLevelDiv" style="display:none;">
											<label>Mandal/Muncipality</label>
											<select class="chosenSelect" id="mndlMuncLvlId">	
												<option></option>
											</select>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgLocationId"></span></label>
											<select class="chosenSelect" id="locationsId" name="alertAssigningVO.levelValue">	
												<option></option>
											</select>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgDesgId"></span></label>
											<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">
											<option></option>	
											</select>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:red;" id="errMsgOffcrId"></span></label>
											<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">
												<option></option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>-->
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading headingColor">
									<h4 class="panel-title text-capital">update alert information</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<span style="color:red;" id="errMsgCmntId"></span>
											<label>
												Comments
											<span style="color:red">*</span>&nbsp;&nbsp;</label>
											<label class="radio-inline">
												<input type="radio" name="Lang" value="te" class="lang" id="telugu" onclick="languageChangeHandler();" checked="true"/>Telugu
											</label>
											<label class="radio-inline">
												<input type="radio" name="Lang" value="en" class="lang" id="eng" onclick="languageChangeHandler();"/>English
											</label>
											<textarea class="form-control m_top10" name="alertAssigningVO.comment" placeholder="alert tracking comments" id="alertDescId"></textarea>
											
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
											<label>Change Status</label>
											<select class="chosenSelect" id="changeStatusId"></select>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
											<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/><span style="color:red;" id="errMsgImgId"></span>
										</div>
										<div class="col-md-4 col-xs-12 col-sm-6">
											<button class="btn btn-success btn-block text-capital m_top20" id="assignOfficerId" type="button">update alert </button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--<input type="hidden" id="hiddenAlertId" name="alertAssigningVO.alertId"></input>-->
				</form>
			</div>	
		</div>
	</section>
<!--Main Div End-->

<!-- Scripts-->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dragAndDropPhoto/js/jquery.filer.js" type="text/javascript"></script>
<script src="dragAndDropPhoto/js/alertManagementSystem.js" type="text/javascript"></script>

<script type="text/javascript">
initializeFile();
$(".chosenSelect").chosen({width:'100%'});

/*global Function and variables Start*/
var currentFromDate = moment().subtract(1,"month").format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var detailedfromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");;
var detailedtoDate=moment().endOf('year').format("DD/MM/YYYY");;

var globalStateId = 36;  
var departmentIdsList="";
var lvlValueG="";
$("#dateRangePickerAUM").daterangepicker({
	opens: 'left',
	startDate: currentFromDate,
	endDate: currentToDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'Today' : [moment(), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});
$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
	currentFromDate = picker.startDate.format('DD/MM/YYYY');
	currentToDate = picker.endDate.format('DD/MM/YYYY');
	getTotalAlertGroupByStatusForOneDept();
	getTotalAlertGroutByDeptThenStatus();
});

var paperIdArr = [];
var chanelIdArr = [];
getTotalAlertGroupByStatusForOneDept();
getTotalAlertGroutByDeptThenStatus();
getDeptIdAndNameListForUser();
function getTotalAlertGroupByStatusForOneDept()
{
	$("#alertStatusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
    var jObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusForOneDeptAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#alertStatusOverview").html('');
		 if(result != null && result.length > 0){
			buildTotalAlertGroupByStatusForOneDept(result);
		}else{
			$("#alertStatusOverview").html('NO DATA AVAILABLE')
		} 
    });
}


function buildTotalAlertGroupByStatusForOneDept(result){
	var str='';
	var totalAlert = 0;
		str+='<div class="col-md-5 col-xs-12 col-sm-12">';
			str+='<div id="totalAlertGroupByStatusForOneDeptDiv"></div>';
			str+='<div id="TotalAlertsView"></div>';						
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-4" style="margin-top:25px;">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<tr>';
						str+='<th style="font-size: 16px;"><b>Status</b></th>';
						str+='<th style="font-size: 16px;"><b>Total</b></th>';
						str+='<th style="font-size: 16px;"><b>%</b></th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					totalAlert+=result[i].count;
					str+='<tr>';
						str+='<td><span class="colorSpecify" style="background-color:'+result[i].color+';"></span>&nbsp;&nbsp;'+result[i].status+'</td>';
						str+='<td>'+result[i].count+'</td>';
						str+='<td>'+result[i].statusPercent+'</td>';
					str+='</tr>';
				}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	$("#alertStatusOverview").html(str);
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].statusPercent;
		statusName = result[i].status;
		var cnt = result[i].count;
		var color=result[i].color;
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,
			color:color
			
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		var chart = new Highcharts.Chart({
			//colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
			chart: {
				renderTo: 'totalAlertGroupByStatusForOneDeptDiv',
				type: 'pie',
				height:'300',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			 tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "Total Alerts - "+cnt+"";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: true,
						formatter: function() {
							return Math.round(this.y,2) + ' %';
						},
						distance: -15,
						color:'black'
					},
					/* point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					} */
				},
				pie: {
					innerSize: 130,
					depth: 95,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		},
	
		function(chart) { // on complete
			var textX = chart.plotLeft + (chart.plotWidth  * 0.5);
			var textY = chart.plotTop  + (chart.plotHeight * 0.5);

			var span = '<span id="pieChartInfoText" style="position:absolute; text-align:center;">';
			span += '<span style="font-size: 18px">TOTAL</span><br>';
			span += '<span style="font-size: 14px;" >'+totalAlert+'</span>';
			span += '</span>';

			$("#TotalAlertsView").append(span);
			span = $('#pieChartInfoText');
			span.css('left', textX + (span.width() * -0.5));
			span.css('top', textY + (span.height() * -0.5));
		});
	});	
}

function getTotalAlertGroutByDeptThenStatus()
{
	$("#overDepartmentWiseAlertOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#departmentWiseAlertGraphDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr       

    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroutByDeptThenStatusAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#overDepartmentWiseAlertOverview").html('');
		$("#departmentWiseAlertGraphDiv").html('');
		if(result != null && result.length > 0){
			buildTotalAlertGroutByDeptThenStatus(result);
		}else{
			$("#overDepartmentWiseAlertOverview").html('NO DATA AVAILABLE');
			$("#departmentWiseAlertGraphDiv").html('NO DATA AVAILABLE');
		} 
    });
}
function buildTotalAlertGroutByDeptThenStatus(result){
	
	var str='';
	var str1='';
	var totalAlertCount=0;
	for(var i in result){
		totalAlertCount+=result[i].count;
	}
	str+='<div class="m_top10">';
		str+='<p style="font-size:20px;text-align:center">Hi Officer</p>';
		str+='<p style="font-size:20px;text-align:center">You Have <span style="font-size:30px;">'+totalAlertCount+'</span> New Alerts</p>';
	str+='</div>';
	str+='<div class="m_top10">';
		str+='<table class="table tableBorLefRig">';
			str+='<tbody>';
			for(var j in result){
				str+='<tr>';
					str+='<th>'+result[j].status+'</th>';
					str+='<th style="text-align:center"><span class="colorStyleAlert">'+result[j].count+'</span></th>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#overDepartmentWiseAlertOverview").html(str);
	
	str1+='<div class="row">';
	for(var i in result){
		 if($(window).width() < 500){
			 str1+='<div class="col-md-4 col-xs-12 col-sm-12 thumbnail" style="margin-left: 10px;">';
		 }else{
			  str1+='<div class="col-md-4 col-xs-12 col-sm-12 thumbnail" style="margin-left: 10px;width:32%">';
		 }
		
		str1+='<h4 style="text-align:center;color:#777;">'+result[i].status+'</h4>';
		str1+='<div id="departmentWiseDiv'+i+'"  ></div>';
		str1+='<div id="TotalAlertsDepView'+i+'"></div>';	
		str1+='<table class="table tableGraph">';
			str1+='<thead>';
				str1+='<tr>';
					str1+='<th style="font-size: 16px;"><b>Status</b></th>';
					str1+='<th style="font-size: 16px;"><b>Total</b></th>';
					str1+='<th style="font-size: 16px;"><b>%</b></th>';
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				if(result[i].subList1 !=null && result[i].subList1.length>0){
					
					for(var j in result[i].subList1){
						str1+='<tr>';
							str1+='<td><span class="colorSpecify" style="background-color:'+result[i].subList1[j].color+';"></span>&nbsp;&nbsp;'+result[i].subList1[j].category+'</td>';
							str1+='<td>'+result[i].subList1[j].categoryCount+'</td>';
							str1+='<td>'+result[i].subList1[j].statusPercent+'</td>';
						str1+='</tr>';
					}
				
				}
		str1+='</tbody>';
		str1+='</table>';
		str1+='<div class="m_top20" style="text-align:center;"><button type="button" class="btn btn-default btn-sm buttonCustomStyle detailedBlockDiv" attr_department_id="'+result[i].statusId+'">Detailed Information</button></div>';	
		str1+='</div>';	
	}
	str1+='</div>';
	$("#departmentWiseAlertGraphDiv").html(str1);
	
	for(var i in result){
		var deptStatusOverviewArr =[];
		var alertCount = result[i].count;
			if(result[i].subList1 !=null && result[i].subList1.length>0){
					for(var j in result[i].subList1){
						statusPercent = result[i].subList1[j].statusPercent;
						statusName = result[i].subList1[j].status;
						var cnt = result[i].subList1[j].count;
						var color=result[i].subList1[j].color;
						var obj = {
							name: statusName,
							y:statusPercent,
							count:alertCount,
							color:color
							
						}
						deptStatusOverviewArr.push(obj);
			
					}
			
			
			}
	
		$(function() {
			var chart = new Highcharts.Chart({
				//colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
				chart: {
					renderTo: 'departmentWiseDiv'+i+'',
					type: 'pie',
					height:'250',
					width:'320',
					options3d: {
						enabled: true,
						alpha: 25
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				 tooltip: {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "Total Alerts - "+cnt+"";
					}  
				}, 
				plotOptions: {
					series: {
						dataLabels: {
							enabled: true,
							formatter: function() {
								return Math.round(this.y,2) + ' %';
							},
							distance: -15,
							color:'black'
						},
						/* point:{
							events:{
								click:function(){
									getData(this.count,this.sts);     
								}
							}
						} */
					},
					pie: {
						innerSize: 150,
						depth: 90,
						dataLabels:{
							enabled: false,
							  formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,2)+ '%';
									}
								} 
						},
						showInLegend: false
					},
				},
				series: [{
					data: deptStatusOverviewArr
				}]
			},
		
			function(chart) { // on complete
				var textX = chart.plotLeft + (chart.plotWidth  * 0.5);
				var textY = chart.plotTop  + (chart.plotHeight * 0.5);

				var span = '<span id="pieChartInfoText1'+i+'" style="position:absolute; text-align:center;">';
				span += '<span style="font-size: 18px">TOTAL</span><br>';
				span += '<span style="font-size: 14px;" >'+alertCount+'</span>';
				span += '</span>';

				$("#TotalAlertsDepView"+i).append(span);
				span = $('#pieChartInfoText1'+i);
				span.css('left', textX + (span.width() * -0.5));
				span.css('top', textY + (span.height() * -0.5));
			});
		});	
}	

}

function getDeptIdAndNameListForUser()
{
	
    var jObj ={
      userId:1,
    }
    $.ajax({
      type:'GET',
      url: 'getDeptIdAndNameListForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		 if(result != null && result.length > 0){
			departmentIdsList=result;
		}
    });
}

	$(document).on("click",".detailedBlockDiv",function(){
		$(".detailedBlockShow").show();
		var departmentId = $(this).attr("attr_department_id");
		getGovtDeptLevelForDeptAndUser(departmentId);
		getAlertCountLocationWiseThenStatusWise(departmentId,3);
		
	});	 

	$(document).on("change","#departmentId",function(){
		var departmentId = $(this).val();
		getGovtDeptLevelForDeptAndUser(departmentId);
	});
	
function getGovtDeptLevelForDeptAndUser(departmentId)
{
	$('#levelId').html('');
	var userId=1;
    var jObj ={
      departmentId:departmentId,
	  userId:userId
    }
    $.ajax({
      type:'GET',
      url: 'getGovtDeptLevelForDeptAndUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		var str='';
		 if(result != null && result.length > 0){
			str+='<option value="0">Select level</option>';
			for(var i in result){
				lvlValueG=result;
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			
		}
		$('#levelId').html(str);
    });
}
	function getAlertCountLocationWiseThenStatusWise(departmentId,lvlValue){
		$("#districtWiseDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		
		var jObj ={
			
		  fromDate:detailedfromDate,
		  toDate:detailedtoDate,
		  stateId:globalStateId,
		  govtDepartmentId:departmentId,
		  lvlValue:lvlValue,
		  paperIdArr:paperIdArr,
		  chanelIdArr:chanelIdArr
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertCountLocationWiseThenStatusWiseAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			$("#districtWiseDetailsBlock").html('');
			buildAlertCountLocationWiseThenStatusWise(result,departmentId,lvlValue);
			
		});
		
	}
	
	function buildAlertCountLocationWiseThenStatusWise(result,departmentId,lvlValue){
		
		var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="row">';
					str+='<div class="col-md-3 col-xs-12 col-xs-6">';
						str+='<label>Select Department</label>';
						str+='<select class="form-control chosen-select" id="departmentId">';
							str+='<option value="0">Select Department</option>';
							if(departmentIdsList != null && departmentIdsList.length>0){
								for(var s in departmentIdsList){
									if(departmentIdsList[s].id == departmentId){
										str+='<option value="'+departmentIdsList[s].id+'" selected="selected">'+departmentIdsList[s].name+'</option>';
									}
									else{
										str+='<option value="'+departmentIdsList[s].id+'">'+departmentIdsList[s].name+'</option>';
									}
								}
							}
						str+='</select>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-xs-6">';
						str+='<label>Select Level</label>';
						str+='<select class="form-control chosen-select" id="levelId">';
						str+='<option value="0"> Select Level </option>';
						if(lvlValueG != null && lvlValueG.length>0){
								for(var s in lvlValueG){
									if(lvlValueG[s].id == lvlValue){
										str+='<option value="'+lvlValueG[s].id+'" selected="selected">'+lvlValueG[s].name+'</option>';
									}
									else{
										str+='<option value="'+lvlValueG[s].id+'">'+lvlValueG[s].name+'</option>';
									}
								}
							}
						str+='</select>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-xs-6 m_top20">';
						str+='<div class="input-group dateRangePickerCls m_top5 pull-right">';
							str+='<input type="text" class="form-control " style="width:180px" id="dateRangePickerDetailedBlock">';
							str+='<span class="input-group-addon">';
								str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='</span>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-xs-6 m_top20">';
						str+='<button type="button" class="btn btn-success getDetailsClick" style="background-color:#016500; font-weight: bold;">Get Details</button>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<div class="row">';
					str+='<div id="districtWiseDetailsBlock"><div>';
				str+='</div>';
			str+='</div>';
		$("#detailedInfoBlockDiv").html(str);
		
			$("#dateRangePickerDetailedBlock").daterangepicker({
				opens: 'left',
				startDate: detailedfromDate,
				endDate: detailedtoDate,
				locale: {
				  format: 'DD/MM/YYYY'
				},
				ranges: {
					'All':[moment().subtract(20, 'years').startOf('year'), moment().endOf('year')],
					'Today' : [moment(), moment()],
				   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
				   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
				   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
				   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
				   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
				   'This Month': [moment().startOf('month'), moment()],
				   'This Year': [moment().startOf('Year'), moment()]
				}
			});
			var dates= $("#dateRangePickerDetailedBlock").val();
			
			if(dates == "01/01/1997 - 31/12/2017"){
				$("#dateRangePickerDetailedBlock").val('ALL');
			}
			
		 if(result != null && result.length > 0){	
		var str1='';
		str1+='<table class="table detailedTableStyle">';
			str1+='<thead>';
				str1+='<tr>';
				str1+='<th>District Name</th>';
				str1+='<th>Total Alerts</th>';
					if(result[0].subList1 !=null && result[0].subList1.length>0){
						
						for(var j in result[0].subList1){
							if(j != 0)
							{
								str1+='<th style="background-color:'+result[0].subList1[j].color+' !important;">'+result[0].subList1[j].category+'</th>';
							}
							
							
						}
						
					}
				str1+='<th></th>';	
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td>'+result[i].status+'</td>';
						str1+='<td>'+result[i].count+'</td>';
						if(result[i].subList1 !=null && result[i].subList1.length>0){
							
							for(var j in result[i].subList1){
								if(j != 0)
								{
									str1+='<td style="background-color:'+result[i].subList1[j].color+' !important;">'+result[i].subList1[j].categoryCount+'</td>';
								}
							}
						}
							str1+='<td><button type="button" class="btn btn-success btn-xs">View Details</button></td>';
					str1+='</tr>';
				}
			str1+='</tbody>';
		str1+='</table>';
		$("#districtWiseDetailsBlock").html(str1);
	}else{
		$("#districtWiseDetailsBlock").html("No Data Available");
	}
	}
	$(document).on("click",".getDetailsClick",function(){
		$("#districtWiseDetailsBlock").html('');
		var departmentId = $("#departmentId").val();
		var levelId = $("#levelId").val();
		getAlertCountLocationWiseThenStatusWise(departmentId,levelId);
		
	});
	$(document).on("click",".ranges li",function(){	
		var selectedDay=$(this).html().trim();
			if(selectedDay == "All")
			$("#dateRangePickerDetailedBlock").val('ALL');
	});

getAlertStatusForUser();
getStatusWiseAlertDetails();

//getAssignedDesignationsForUser();
//getSubLevelsForUser();
//getSubOrdinatesAlertsOverView();

function getStatusWiseAlertDetails()
{
	
	var jObj ={
      fromDate : currentFromDate,
      toDate : currentToDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr       

    }
    $.ajax({
      type:'GET',
      url: 'getStatusWiseAlertDetailsAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		
	});
}

function getAlertStatusForUser()
{
	
	var jObj ={
     
    }
    $.ajax({
      type:'GET',
      url: 'getAlertStatusForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0)
			buildDeptWiseStatus(result);
	});
}
function buildDeptWiseStatus(result){
	var str = '';
	str+='<option value="0">Select Status</option>';
	for(var i in result){
		if(result[i].id != null && result[i].id > 1)
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	$("#changeStatusId").html(str);
	$("#changeStatusId").trigger("chosen:updated");
}

function getAssignedDesignationsForUser()
{
	
	var jObj ={
     
    }
    $.ajax({
      type:'GET',
      url: 'getAssignedDesignationsForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		
	});
}

function getSubLevelsForUser()
{
	
	var jObj ={
		designationId : 1
    }
    $.ajax({
      type:'GET',
      url: 'getSubLevelsForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		
	});
}

function getSubOrdinatesAlertsOverView()
{
	
	var jObj ={
		designationId : 1,
		levelId : 4,
		fromDate : currentFromDate,
		toDate : currentToDate
    }
    $.ajax({
      type:'GET',
      url: 'getSubOrdinatesAlertsOverViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		
	});
}

google.load("elements", "1", {
	packages: "transliteration"
 }); 
 
var control;
var lang;

function languageChangeHandler(){
	   var lang1 = $("input[name=Lang]:checked").val();
		if(lang1 =="en"){
			control.disableTransliteration();
		}else{
			control.enableTransliteration();
			control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
		}
	}
	
google.setOnLoadCallback(onLoad);
function onLoad() {
	   lang = $("input[name=Lang]:checked").val();
		var options = {
			sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage:[''+lang+''],
			shortcutKey: 'alt+t',
			transliterationEnabled: true
		};
		// Create an instance on TransliterationControl with the required options.
		control = new google.elements.transliteration.TransliterationControl(options);
		// Enable transliteration in the textbox with id 'descrptionId'.
	 	if ($('#alertDescId').length){
			control.makeTransliteratable(['alertDescId']);
		}
   }	
</script>
</body>
</html>