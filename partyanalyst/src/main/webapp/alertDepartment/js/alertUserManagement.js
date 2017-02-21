var currentFromDate = moment().subtract(1,"month").format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var detailedfromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var detailedtoDate=moment().endOf('year').format("DD/MM/YYYY");

var globalStateId = 1;  
var departmentIdsList="";
var lvlValueGlobal="";
var paperIdArr = [];
var chanelIdArr = [];

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
	//getTotalAlertGroupByStatusForOneDept();
	getTotalAlertByStatusForOfficer();  
	getTotalAlertGroutByDeptThenStatus();
	getTotalAlertByDeptForOfficer();
});
$('#dateRangePickerSubOrdinateBlock').on('apply.daterangepicker', function(ev, picker) {
	detailedfromDate =  picker.startDate.format('DD/MM/YYYY')
	if(picker.chosenLabel == 'All')
	{
		$("#dateRangePickerSubOrdinateBlock").val('ALL');
	}
	
});
$("#dateRangePickerDistrictAlertBlock").on('apply.daterangepicker', function(ev, picker) {
	if(picker.chosenLabel == 'All')
	{
		$("#dateRangePickerDistrictAlertBlock").val('ALL');
	}
});
$("#dateRangePickerDistrictLevelDeptBlock").on('apply.daterangepicker', function(ev, picker) {
	if(picker.chosenLabel == 'All')
	{
		$("#dateRangePickerDistrictAlertBlock").val('ALL');
	}
});
$("#dateRangePickerSubOrdinateBlock, #dateRangePickerDistrictAlertBlock, #dateRangePickerDistrictLevelDeptBlock").daterangepicker({
	opens: 'left',
	startDate: detailedfromDate,
	endDate: detailedtoDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'All':[moment(),moment()],
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
var dates= $("#dateRangePickerSubOrdinateBlock, #dateRangePickerDistrictAlertBlock, #dateRangePickerDistrictLevelDeptBlock").val();
var pickerDates = detailedfromDate+' - '+detailedtoDate
if(dates == pickerDates)
{
	$("#dateRangePickerSubOrdinateBlock, #dateRangePickerDistrictAlertBlock, #dateRangePickerDistrictLevelDeptBlock").val('ALL');
}


//getTotalAlertGroupByStatusForOneDept();
getTotalAlertByStatusForOfficer();       
getTotalAlertGroutByDeptThenStatus();
getTotalAlertByDeptForOfficer();
getDeptIdAndNameListForUser();
getTotalAlertGroutByDeptThenStatusDistritLevel();
getAlertStatusForUser();
getStatusWiseAlertDetails(0);
getAssignedDesignationsForUser();

$(document).on("click",".getDetailsForDistrictWiseAlerts",function(){	
	var statusId=$("#alertStatusDivId").val();
	getStatusWiseAlertDetails(statusId);
			
});
$(document).on("click",".getDetailsForDistrictLevelDeptAlerts",function(){	
	getTotalAlertGroutByDeptThenStatusDistritLevel();
});

$(document).on("change","#designationDivId",function(){	
	var designnationId=$("#designationDivId").val();
	getSubLevelsForUser(designnationId);
			
});
$(document).on("click",".getDetailsForSubOrdinate",function(){	
	var designnationId=$("#designationDivId").val();
	var subOrdianatelevelId=$("#subOrdianatelevelId").val();
	var subOrdianatelevelText=$("#subOrdianatelevelId option:selected").html();
	getSubOrdinatesAlertsOverView(designnationId,subOrdianatelevelId,subOrdianatelevelText);
			
});

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
function getDeptIdAndNameListForUser(){
	
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
function getGovtDeptLevelForDeptAndUser(departmentId)
{
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
			lvlValueGlobal=result;
			
		}
		
    });
}

function getAlertStatusForUser(){
	
	var typeC = $("#changeStatusId").attr("attr_type")
	
	var jObj ={
     
    }
    $.ajax({
      type:'GET',
      url: 'getAlertStatusForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildChangeStatusDetails(result);
			buildAlertStatusDetails(result);
		}	
			
	});
}
function buildChangeStatusDetails(result){
	var str = '';
		str+='<option value="0">Select Status</option>';
	for(var i in result){
		if(result[i].id != null && result[i].id > 1){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
			
	}
	$("#changeStatusId").html(str);
	$("#changeStatusId").trigger("chosen:updated");
}
function buildAlertStatusDetails(result){
	var str = '';
		str+='<option value="0">Show All (Notified/Progress/Completed...)</option>';
	for(var i in result){
		if(result[i].id != null && result[i].id > 1){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
			
	}
	$("#alertStatusDivId").html(str);
	$("#alertStatusDivId").trigger("chosen:updated");
	
}
function getAssignedDesignationsForUser(){
	
	var jObj ={
     
    }
    $.ajax({
      type:'GET',
      url: 'getAssignedDesignationsForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		var str = '';
		if(result !=null && result.length>0){
			str+='<option value="0">Select Designation</option>';
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].designation+'</option>';
			}
		}
			
		$("#designationDivId").html(str);
		$("#designationDivId").trigger("chosen:updated");
	});
}

function getSubLevelsForUser(designnationId)
{
	
	var jObj ={
		designationId : designnationId
    }
    $.ajax({
      type:'GET',
      url: 'getSubLevelsForUserAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		var str = '';
		if(result !=null && result.length>0){
			str+='<option value="0">Select Level</option>';
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
			
		$("#subOrdianatelevelId").html(str);
		$("#subOrdianatelevelId").trigger("chosen:updated");
	});
}
//first block ias
/* function getTotalAlertGroupByStatusForOneDept()
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
} */
  
function getTotalAlertByStatusForOfficer()
{ 
	var jsObj ={     
      fromDate:currentFromDate,     
      toDate:currentToDate       
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertByStatusForOfficerAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#alertStatusOverview").html('');
		 if(result != null && result.length > 0){
			buildTotalAlertByStatusForOfficer(result);
		}else{
			$("#alertStatusOverview").html('NO DATA AVAILABLE')
		} 
    });  
}

function buildTotalAlertByStatusForOfficer(result){
	var str='';
	var totalAlert = 0;
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
				str+='<td><span class="totalAlertDetails" attr_status_id="'+result[i].statusId+'" attr_department_id="0" attr_type="today">'+result[i].count+'</span></td>';
				str+='<td>'+result[i].statusPercent+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
		
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
			span += '<span style="font-size: 14px;" class="totalAlertDetails" attr_status_id="'+result[i].statusId+'" attr_department_id="0" attr_type="overall">'+totalAlert+'</span>';
			span += '</span>';

			$("#TotalAlertsView").append(span);
			span = $('#pieChartInfoText');
			span.css('left', textX + (span.width() * -0.5));
			span.css('top', textY + (span.height() * -0.5));
		});
	});	
}
 
function getTotalAlertByDeptForOfficer()
{ 
    var jsObj ={
      fromDate:'01/01/2017',     
      toDate:'20/02/2017'    
    }
    $.ajax({
      type:'GET',       
      url: 'getTotalAlertByDeptForOfficerAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#overDepartmentWiseAlertOverview").html('');
		$("#departmentWiseAlertGraphDiv").html('');
		if(result != null && result.length > 0){
			buildTotalAlertByDeptForOfficer(result);  
		}else{
			$("#overDepartmentWiseAlertOverview").html('NO DATA AVAILABLE');
			$("#departmentWiseAlertGraphDiv").html('NO DATA AVAILABLE');
		} 
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
		$("#departmentWiseAlertGraphDiv").html('');
		if(result != null && result.length > 0){
			buildTotalAlertGroutByDeptThenStatus(result);
		}else{
			$("#departmentWiseAlertGraphDiv").html('NO DATA AVAILABLE');
		} 
    });
}
function buildTotalAlertByDeptForOfficer(result){
	
	var str='';
	var str1='';
	var totalAlertCount=0;
	for(var i in result){
		totalAlertCount+=result[i].count;
	}
	str+='<div class="m_top10">';
		str+='<p style="font-size:20px;text-align:center">Hi Officer</p>';
		str+='<p style="font-size:20px;text-align:center">You Have <span style="font-size:30px;" class="totalAlertDetails" attr_status_id="0" attr_department_id="0" attr_type="overall">'+totalAlertCount+'</span> New Alerts</p>';
	str+='</div>';
	str+='<div class="m_top10 alertScroll">';
		str+='<table class="table tableBorLefRig">';
			str+='<tbody>';
			for(var j in result){
				str+='<tr>';
					if(result[j].status !=null && result[j].status.length>15){
						str+='<td><span data-toggle="tooltip" data-placement="top" title="'+result[j].status+'">'+result[j].status.substring(0,15)+'..</span></td>';
					}else{
						str+='<td>'+result[j].status+'</td>';
					}
					str+='<td style="text-align:center"><span class="colorStyleAlert totalAlertDetails" attr_status_id="0" attr_department_id="'+result[i].id+'" attr_type="overall">'+result[j].count+'</span></td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#overDepartmentWiseAlertOverview").html(str);
	if(result.length > 5){
		$(".alertScroll").mCustomScrollbar({setHeight:'200px'})
	}
}
function buildTotalAlertGroutByDeptThenStatus(result){	
	var str1 = '';
	str1+='<div class="row">';
		for(var i in result){
			str1+='<div class="col-md-4 col-xs-12 col-sm-12">';
				str1+='<div class="panel panel-default">';
					str1+='<div class="panel-body">';
						if(result[i].status !=null && result[i].status.length>20){
							str1+='<h4 style="text-align:center;color:#777;" data-toggle="tooltip" data-placement="top" title="'+result[i].status+'" >'+result[i].status.substring(0,20)+'..</h4>';
						}else{
							str1+='<h4 style="text-align:center;color:#777;">'+result[i].status+'</h4>';
						}
					
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
										if(result[i].subList1[j].categoryId != 1)
										{
											str1+='<tr>';
												str1+='<td><span class="colorSpecify" style="background-color:'+result[i].subList1[j].color+';"></span>&nbsp;&nbsp;'+result[i].subList1[j].category+'</td>';
												str1+='<td>'+result[i].subList1[j].categoryCount+'</td>';
												str1+='<td>'+result[i].subList1[j].statusPercent+'</td>';
											str1+='</tr>';
										}
										
									}
								}
						str1+='</tbody>';
						str1+='</table>';
						str1+='<div class="m_top20" style="text-align:center;"><button type="button" class="btn btn-default btn-sm buttonCustomStyle detailedBlockDiv" attr_department_id="'+result[i].statusId+'">Detailed Information</button></div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';	
		}
	str1+='</div>';
	$("#departmentWiseAlertGraphDiv").html(str1);
	$('[data-toggle="tooltip"]').tooltip();
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
				var textX = chart.plotLeft + (chart.plotWidth  * 0.6);
				var textY = chart.plotTop  + (chart.plotHeight * 0.6);

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
						str+='<select class="form-control chosen-select" id="levelDepartmentId">';
						str+='<option value="0"> Select Level </option>';
						str+='<option value="3">District</option>';
						str+='<option value="4">Constituency</option>';
						/* if(lvlValueGlobal != null && lvlValueGlobal.length>0){
								for(var s in lvlValueGlobal){
									if(lvlValueGlobal[s].id == lvlValue){
										str+='<option value="'+lvlValueGlobal[s].id+'" selected="selected">'+lvlValueGlobal[s].name+'</option>';
									}
									else{
										str+='<option value="'+lvlValueGlobal[s].id+'">'+lvlValueGlobal[s].name+'</option>';
									}
								}
							} */
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
		var levelId = $("#levelDepartmentId").val();
		getAlertCountLocationWiseThenStatusWise(departmentId,levelId);
		
	});
	
function getStatusWiseAlertDetails(statusId){
	$("#districtAlertStatusInfo").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var jObj ={
      fromDate : detailedfromDate,
      toDate : detailedtoDate,
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
      statusId : statusId       

    }
    $.ajax({
      type:'GET',
      url: 'getStatusWiseAlertDetailsAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#districtAlertStatusInfo").html('');
		buildStatusWiseAlertDetails(result);
	});
}

function buildStatusWiseAlertDetails(result){
	
	if(result != null && result.length > 0){
		var str1='';
			str1+='<div class="table-responsive">';
			str1+='<table class="table detailedTableStyle">';
				str1+='<thead>';
					str1+='<tr>';
						str1+='<th>Alert Severity</th>';
						str1+='<th>Alert Title</th>';
						str1+='<th>Alert Created Date</th>';
						str1+='<th>Last Updated Date</th>';
						str1+='<th>Lag Days</th>';
						str1+='<th>Current Status</th>';
						str1+='<th></th>';
					str1+='</tr>';
					str1+='</thead>';
					str1+='<tbody>';
					for(var i in result){
						str1+='<tr>';
							str1+='<td>';
							str1+='<div class="row">';
							str1+='<div class="col-md-1 col-xs-12 col-xs-1">';
							if(result[i].severityStr == "Medium"){
								str1+='<p class="circleAlertStatus" style="border:3px solid #ff8800"></p>';
							}
							if(result[i].severityStr == "High"){
								str1+='<p class="circleAlertStatus" style="border:3px solid #ff0c0c"></p>';
							}
							if(result[i].severityStr == "Low"){
								str1+='<p class="circleAlertStatus" style="border:3px solid #4a5865"></p>';
							}
								
							str1+='</div>';
							str1+='<div class="col-md-1 col-xs-12 col-xs-1">';
								str1+='<p>'+result[i].severityStr+'</p>';
							str1+='</div>';
							
							str1+='</div>';
							str1+='</td>';
							str1+='<td>';
								if(result[i].title !=null && result[i].title.length>25){
										str1+='<p  style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].title+'" >'+result[i].title.substring(0,25)+'...</p>';
								}else{
									str1+='<p>'+result[i].title+' </p>';
								}
							str1+='</td>';
							str1+='<td>'+result[i].date1+'</td>';
							str1+='<td>'+result[i].date2+'</td>';
							str1+='<td>'+result[i].noOfDays+'</td>';
							str1+='<td><span class="colorSpecify" style="background-color:'+result[i].color+'"></span> '+result[i].status+'</td>';
							str1+='<td><button type="button" class="btn btn-success btn-xs alertDetailsModalCls" attr_alert_Id="'+result[i].alertId+'" attr_status_id="'+result[i].statusId+'" attr_update="show">Alert Details</button></td>';
						str1+='</tr>';
					}
						
					str1+='</tbody>';
				
			str1+='</table>';
			str1+='</div>';
		
		$("#districtAlertStatusInfo").html(str1);	
		
	}else{
		$("#districtAlertStatusInfo").html("No Data Available");	
	}
	$('[data-toggle="tooltip"]').tooltip();
	
}


function getSubOrdinatesAlertsOverView(designnationId,subOrdianatelevelId,subOrdianatelevelText)
{
	$("#mandalWiseDetailsSubOrdBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
		designationId : designnationId,
		levelId : subOrdianatelevelId,
		fromDate : detailedfromDate,
		toDate : detailedtoDate
    }
    $.ajax({
      type:'GET',
      url: 'getSubOrdinatesAlertsOverViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#mandalWiseDetailsSubOrdBlock").html('');
		buildSubOrdinatesAlertsOverView(result,subOrdianatelevelText);
	});
}


	function buildSubOrdinatesAlertsOverView(result,subOrdianatelevelText){
		
		if(result != null && result.length > 0){	
			var str1='';
			str1+='<table class="table detailedTableStyle">';
				str1+='<thead>';
					str1+='<tr>';
					str1+='<th>'+subOrdianatelevelText+'</th>';
					str1+='<th>Total Alerts</th>';
						if(result[0].govtDeptList !=null && result[0].govtDeptList.length>0){
							
							for(var j in result[0].govtDeptList){
									str1+='<th style="background-color:'+result[0].govtDeptList[j].color+' !important;">'+result[0].govtDeptList[j].name+'</th>';
								
							}
							
						}
					str1+='<th></th>';	
					str1+='</tr>';
				str1+='</thead>';
				str1+='<tbody>';
					for(var i in result){
						str1+='<tr>';
							str1+='<td>'+result[i].name+'</td>';
							str1+='<td>'+result[i].count+'</td>';
							if(result[i].govtDeptList !=null && result[i].govtDeptList.length>0){
								
								for(var j in result[i].govtDeptList){
										str1+='<td style="background-color:'+result[i].govtDeptList[j].color+' !important;">'+result[i].govtDeptList[j].count+'</td>';
								}
							}
								str1+='<td><button type="button" class="btn btn-success btn-xs subOrdinateWiseAlertDetails" attr_location_id="'+result[i].id+'">View Details</button></td>';
						str1+='</tr>';
					}
				str1+='</tbody>';
			str1+='</table>';
			$("#mandalWiseDetailsSubOrdBlock").html(str1);
		}else{
			$("#mandalWiseDetailsSubOrdBlock").html("No Data Available");
		}
	}

$(document).on("click",".subOrdinateWiseAlertDetails",function(){
	var locationVal = $(this).attr("attr_location_id");
	var designnationId = $("#designationDivId").val();
	var levelId = $("#subOrdianatelevelId").val();
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	
	getSubOrdinateLocationWiseAlertDetails(designnationId,levelId,locationVal);
});

function getSubOrdinateLocationWiseAlertDetails(designnationId,levelId,locationVal){
	var jObj ={
		designationId : designnationId,
		levelId : levelId,
		levelValue : locationVal,
		fromDate : detailedfromDate,
		toDate : detailedtoDate
    }
    $.ajax({
      type:'GET',
      url: 'getSubOrdinateLocationWiseAlertDetailsAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildSubOrdinateLocationWiseAlertDetails(result,"hide");
		}
		
	});
}
	
function buildSubOrdinateLocationWiseAlertDetails(result,updateBlock){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var str='';
	if($(window).width() < 500)
	{
		str+='<div class="table-responsive" >';
	}
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="dataTableTotalAlerts">';
			str+='<thead>';
				str+='<th>Alert Source</th>';
				str+='<th>Alert Title</th>';
				str+='<th>Created Date</th>';
				str+='<th>Last Updated Date</th>';
				str+='<th>Current Status</th>'	 
				str+='<th>LAG Days</th>';
				str+='<th>Alert Level</th>';
				str+='<th>Location</th>';
				str+='<th></th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					if(result[i].source != null && result[i].source.length > 0){
						str+='<td><strong>'+result[i].source+'</strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].title != null && result[i].title.length > 0){
						str+='<td><strong>'+result[i].title+'</strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].createdDate != null && result[i].createdDate.length > 0){
						str+='<td>'+result[i].createdDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].updatedDate != null && result[i].updatedDate.length > 0){
						str+='<td>'+result[i].updatedDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].status != null && result[i].status.length > 0){
						str+='<td>'+result[i].status+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].interval != null){
						str+='<td>'+(parseInt(result[i].interval)-parseInt(1))+'</td>';            
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].alertLevel != null && result[i].alertLevel.length > 0){
						str+='<td>'+result[i].alertLevel+'</td>';               
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].location != null && result[i].location.length > 0){
						str+='<td>'+result[i].location+'</td>';        
					}else{
						str+='<td> - </td>';        
					}
					str+='<td><button class="btn btn-success alertDetailsModalCls" attr_alert_Id="'+result[i].id+'" attr_status_id="'+result[i].statusId+'" attr_update="'+updateBlock+'">Alert Details</button></td>';      
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	if($(window).width() < 500)
	{
		str+='</div>';
	}
	$("#totalAlertsModalTabId").html(str);
	$("#dataTableTotalAlerts").dataTable();
	$("#dataTableTotalAlerts").removeClass("dataTable");
}
	/* Alert Details Modal Start*/
$(document).on("click",".alertDetailsModalCls",function(){
	$("#errMsgCmntId").html("");
	$("#errMsgStsId").html("");
	
	$("#alertDetailsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	
	fieldsEmpty();
	
	var alertId = $(this).attr("attr_alert_Id");
	var alrtStsId = $(this).attr("attr_status_id");
	var updateBlk = $(this).attr("attr_update");
	if(updateBlk != null && updateBlk == "show")
		$("#alerAssignDivId").show();
	else
		$("#alerAssignDivId").hide();
	
	$("#hiddenAlertId").val(alertId);   //3725
	getAlertData(alertId);
	getInvolvedMembersDetilas(alertId);
	getAlertStatusCommentsTrackingDetails(alertId);
	
});
function getAlertData(alertId)
{
	$("#alertCandidateDataId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
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
		if(result != null && result.length > 0){
			buildAlertData(result);
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
		} 
	});
}
function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId = result[i].categoryId;
		
		$("#typeId").html('<b>'+result[i].alertType+'</b>');
		$("#createdDate").html('<b>'+result[i].date+'</b>');
		$("#alertStatus").html('<b>'+result[i].status+'</b>');
		if(severityTdId ==1){
			$("#severityTdId").show();
		}else{
			$("#severityTdId").hide();
		}
		$(".severityIdColorCls").addClass('<b>'+result[i].severity+'</b>');
		$("#severityId").html('<b>'+result[i].severity+'</b>');
		$("#levelId").html('<b>'+result[i].regionScope+'</b>');
		
		var location ='';
			
		if(result[i].locationVO.stateId !=null){
			location +='<b>'+result[i].locationVO.state+' ';
		}
		if(result[i].locationVO.districtId !=null){
			location +=' , '+result[i].locationVO.districtName+' District ';
		}
		if(result[i].locationVO.constituencyId !=null){
			location +=' , '+result[i].locationVO.constituencyName+' Constituency ';
		}
		
		if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
			location +=' , '+result[i].locationVO.localEleBodyName+' Municipality ';
		}
		if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
			location +=' , '+result[i].locationVO.tehsilName+' Mandal ';
		}
		if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
			location +=' , '+result[i].locationVO.wardName+' Ward ';
		}
		if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
			location +=' , '+result[i].locationVO.villageName+' Panchayat </b>';
		}
		
		$("#LocationId").html(''+location+'');
		
		$("#titleId,#mainTitleId").html('<b>'+result[i].title+'</b>');
		
		$("#descriptionId").html('<b>'+result[i].desc+'</b>');
		
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

function getInvolvedMembersDetilas(alertId){
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		  type:'GET',
		  url: 'getInvolvedMembersInAlertAction.action',
		  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result == null || result.length == 0)
		{
			$("#alertCandidateDataId").html('No Involved Members..');
		}else{
			buildAlertCandidateData(result);
		}
	});
}
function buildAlertCandidateData(result,categoryId)
{

	var str='';
	
	for(var i in result)
	{
		str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10">';
			str+='<div class="media" style="border:1px solid #ddd;padding:8px;">';
				str+='<div class="media-left">';
					str+=' <img src="'+result[i].image+'" class="media-object img-circle"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;height:50px;"/>';
				str+=' </div>';
				str+=' <div class="media-body">';
					if(result[i].impactLevelId == 1)
					{
						str+=' <span class="label label-success pull-right" style="margin-top: 7px;">+ Ve</span>'; 
					}else if(result[i].impactLevelId == 2){
						str+=' <span class="label label-danger pull-right" style="margin-top: 7px;">- Ve</span>';
					}else{
						str+=' <span class="label label-neutral pull-right" style="margin-top: 7px;">N</span>';
					}
					if(result[i].name != null && result[i].name != "")
						str+=' <p class="text-capital"><span class="text-muted">Name :</span> <b>'+result[i].name+'</b></p>';
					str+=' <p class="text-capital"><span class="text-muted">Department: </span><b>'+result[i].status+'</b></p>';
					if(result[i].designation != null && result[i].designation != "")
					{
						str+='<p class="text-capital"><span class="text-muted">Designation</span>'+result[i].designation+'</p>';
					}
					str+='  <p>'+result[i].source+'</p>';
					if(result[i].dateStr !=null && result[i].dateStr.length>0){
						str+='<p><a>'+result[i].dateStr+'</a></p>';
					}
				str+='  </div>';
			str+='</div>';
	   str+=' </div>';
	}
	$("#involvedCandidatesCnt").html(result.length);	
	$("#alertCandidateDataId").html(str);
}
function getAlertStatusCommentsTrackingDetails(alertId)
{
	//var alertId = $("#hiddenAlertId").val();
	$("#alertCommentsDivIdNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj={
				alertId:alertId,
				task:""
			}
	$.ajax({
	  type : 'GET',
	  url : 'getStatusWiseCommentsTrackingAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result != null && result.length > 0)
			alertComments(result);	
		else
			$("#alertCommentsDivIdNew").html("");
	});
	
}
function alertComments(result)
{
	var docName = '';
	var extName = [];
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){  
		$(".cadreAlertCommentsDivId").show();
		str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px;">';
	}
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
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
			if(result[i].govtDeptList != null && result[i].govtDeptList.length > 0){
				if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';    
				str+='</h4>';
				}else{
					str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';
					str+='</h4>';
				} 
			}else{
				if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i>';    
				str+='</h4>';
				}else{
					str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-hourglass"></i>';
					str+='</h4>';
				} 
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
						
					if(result[i].govtDeptList != null && result[i].govtDeptList.length > 0){
						for(var j in result[i].govtDeptList){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].govtDeptList[j].dateStr
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
										//for(var k in result[i].sublist2[j].sublist)
										//{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:</span><br>';
												//str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												//for(var l in result[i].govtDeptList[j].sublist[k])
												//{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].govtDeptList[j].source+'<br>';
												//}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].govtDeptList[j].comment+'</p>';
												/*if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';          
													}
													str+='</ul>';              
												}*/
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].govtDeptList[j].name+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										//}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}
					}else{
						str+='<div class="col-md-12 col-xs-12 col-sm-12"><div  style="height:200px;background-color:#EEE"><h4 class="panel-title text-capital text-center" style="padding-top:80px !important;">please assign alert to officer</h4></div></div>';
					}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#alertCommentsDivIdNew").html(str)
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

function getTotalAlertGroutByDeptThenStatusDistritLevel()
{
	
	$("#districtLevelDeptOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var jObj ={
		fromDate : detailedfromDate,
		toDate : detailedtoDate,
		stateId : globalStateId,
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr       

    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroutByDeptThenStatusAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#districtLevelDeptOverview").html('');
			buildDistrictLevelDepartmentDetails(result);
		
    });
}

function buildDistrictLevelDepartmentDetails(result){
	
	if(result != null && result.length > 0){	
		var str1='';
		str1+='<table class="table detailedTableStyle">';
			str1+='<thead>';
				str1+='<tr>';
				str1+='<th>Department Name</th>';
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
		$("#districtLevelDeptOverview").html(str1);
	}else{
		$("#districtLevelDeptOverview").html("No Data Available");
	}
	
}
$(document).on("click",".totalAlertDetails",function(){
	var statusId = $(this).attr("attr_status_id");
	var departmentId = $(this).attr("attr_department_id");
	var typeId = $(this).attr("attr_type");

	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	getTotalAlertDtls(statusId,departmentId,typeId);
});

function getTotalAlertDtls(statusId,departmentId,typeId)
{
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
		fromDate : detailedfromDate,
		toDate : detailedtoDate,
		statusId : statusId,
		deptId : departmentId,
		type : typeId      

    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertDtlsAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		buildSubOrdinateLocationWiseAlertDetails(result,"show");
    });
}

function getGroupedArticlesInfo(articleId)
{
	$.ajax({
		  type : 'GET',      
		  //url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		  url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
	}).then(function(result){
		
		var str='';
		if(result !=null && result.length>0){
			$("#alertGroupAttachId").show();
			str+='<ul class="list-inline">';
			for(var i in result)
			{
				if(articleId != result[i].id){
					str+='<li class="articleImgDetailsCls" attr_articleId='+result[i].id+' style="cursor:pointer"><img src="http://mytdp.com/NewsReaderImages/'+result[i].name+'" style="width: 150px; height: 150px;margin-top:5px;"></img></li>';
				}
			}
			str+='</ul>';
			$("#alertGroupAttachUlId").html(str);
		}
	});
}
$(document).on("click",".notMyDepartment",function(){
	if($(this).is(":checked"))
	{
		$("#changeStatusId").val('8').trigger("chosen:updated")
	}else{
		$("#changeStatusId").val('0').trigger("chosen:updated")
	}
	$("#changeStatusId").parent('.col-md-12').toggle();
});

$(document).on("click","#assignOfficerId",function(){
	$("#errMsgCmntId").html("");
	$("#errMsgStsId").html("");
	
	var notMyDepartment = $(".notMyDepartment").is(':checked');
	var comments = $("#alertDescId").val();
	var updateStatusId = $("#changeStatusId").val();
	
	if(comments.length == 0){
		$("#errMsgCmntId").html("Enter Comments.");
		return;
	}
	if(!(notMyDepartment)){
		if(updateStatusId == 0){
			$("#errMsgStsId").html("Select Status.");
			return;
		}
	}

	var uploadHandler = {
		upload: function(o) {
			uploadResult = o.responseText;
			displayStatus(uploadResult);
		}
	};

	YAHOO.util.Connect.setForm('alertAssignForm',true);
	YAHOO.util.Connect.asyncRequest('POST','updatingAlertInformationAction.action',uploadHandler); 
	
});

function displayStatus(myResult){
	var result = (String)(myResult);
	if(result.search('success') != -1){
		//getAlertStatusCommentsTrackingDetails();
		alert("Alert Updated Successfully.");
		//$("#alertStatus").html('Notified');
		fieldsEmpty();
		/*$("#uploadClarificationFileId0").val('');
		$("#extraClarificationUploadFileDiv").html('');
		$(".ClearFileCls").hide();  
		fileNo = 0;*/
	}else{
		alert("Please Try Again.");
	}
}

function fieldsEmpty(){
	$("#changeStatusId").val(0);
	$("#changeStatusId").trigger("chosen:updated");
	
	$("#alertDescId").val('');
	var filerKit = $("#imageId").prop("jFiler");
	filerKit.reset();
}

getTotalAlertDetailsGroupByDeptThenStatus();
function getTotalAlertDetailsGroupByDeptThenStatus()
{
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
		fromDate:detailedfromDate,
		  toDate:detailedtoDate,
		  stateId:globalStateId,
		  deptId:1,    
		  statusId:0,         
		  paperIdArr:paperIdArr,
		  chanelIdArr:chanelIdArr,
		type : "total"        

    }  
    $.ajax({
      type:'GET',
      url: 'getTotalAlertDetailsGroupByDeptThenStatusAction.action',
      data: {task :JSON.stringify(jObj)}   
    }).done(function(result){
		console.log(result);
    });
}
getAlertCountDetailsLocationWiseThenStatusWise();
function getAlertCountDetailsLocationWiseThenStatusWise()
{
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jObj ={
		fromDate:detailedfromDate,
		  toDate:detailedtoDate,
		  stateId:globalStateId,
		  govtDepartmentId:1,    
		  statusId:2,          
		  lvlValue:3,
		  paperIdArr:paperIdArr,
		  chanelIdArr:chanelIdArr,
		  locId:18                
    }  
    $.ajax({
      type:'GET',
      url: 'getAlertCountDetailsLocationWiseThenStatusWiseAction.action',
      data: {task :JSON.stringify(jObj)}   
    }).done(function(result){
		console.log(result);
    });
}
