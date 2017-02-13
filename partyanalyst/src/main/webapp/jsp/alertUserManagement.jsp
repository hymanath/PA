<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government Core DashBoard</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="govtCoreDashBoard/img/fevicon.png">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>

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
	<header>
		<nav class="navbar navbar-default navbarHeader">
		  <div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
			  <a class="navbar-brand" href="#"><img src="newCoreDashBoard/img/APLOGO.jpg" class="img-responsive"/></a>
			</div>
		  </div><!-- /.container-fluid -->
		</nav>
	</header>
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
<script src="js/alertManagementSystem/alertUserManagementSystem.js" type="text/javascript"></script>

<script type="text/javascript">
/*global Function and variables Start*/
var currentFromDate = moment().subtract(1,"month").format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var globalStateId = 36;  
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
						str+='<td><span class="colorSpecify" style="background-color:#fff;"></span>'+result[i].status+'</td>';
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
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt
			
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		var chart = new Highcharts.Chart({
			colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
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
							str1+='<td><span class="colorSpecify" style="background-color:#fff;"></span>'+result[i].subList1[j].category+'</td>';
							str1+='<td>'+result[i].subList1[j].categoryCount+'</td>';
							str1+='<td>'+result[i].subList1[j].statusPercent+'</td>';
						str1+='</tr>';
					}
				
				}
		str1+='</tbody>';
		str1+='</table>';	
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
						var obj = {
							name: statusName,
							y:statusPercent,
							count:alertCount
							
						}
						deptStatusOverviewArr.push(obj);
			
					}
			
			
			}
	
		$(function() {
			var chart = new Highcharts.Chart({
				colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
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
</script>
</body>
</html>