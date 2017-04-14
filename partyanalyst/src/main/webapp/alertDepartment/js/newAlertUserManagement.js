var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var deptIdArr =[];
var paperIdArr =[];
var chanelIdArr =[];
var departmentIdsGlob=[];
var electronicIdArr =[];
onLoadCalls();


function onLoadCalls(){
	getIASOfficerMyAlertsCountMainView();
	getIASOfficerMySubTasksCountView();
	getIASOfficerMyAssignedSubTasksCountView();
	
}

$("#dateRangePickerAUM").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
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
	var dates= $("#dateRangePickerAUM").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePickerAUM").val('All');
	}

	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerAUM").val('All');
		}
		onLoadCalls();
	});
	
function getIASOfficerMyAlertsCountMainView(){

    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAlertsCountMainViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildIASOfficerMyAlertsCountMainView(result);
		
    });
}
function buildIASOfficerMyAlertsCountMainView(result){
	if(result !=null){
		departmentIdsGlob.push(result.departmentId);
	}
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		for(var i in result.list1){
			if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
					str+='<div class="row">';
					str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList2[i].count+'</span></p>';
								str+='</td>';
								str+='</tr>';
							}
					str+='</tbody>';
					str+='</table>';
				str+='</div>';	
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">COMPLETED</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
								str+='</td>';
								str+='</tr>';
							}
					str+='</tbody>';
					str+='</table>';
					str+='<div id="myAlertGraphView" style="height:250px"></div>';
				str+='</div>';	
				str+='</div>';	
				$("#myAlertsDivID").html(str);
			}else{
				$("#myAlertsDivID").html("No Data Available");
			}
		}
	}else{
		$("#myAlertsDivID").html("No Data Available");
	}
	
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var mainArrTempAT=[];
			var namesArrAT=[];
			var countAT = [];
			for(var i in result.list1){
				if(result.list1[i].subList3 !=null && result.list1[i].subList3.length>0){
					for(var j in result.list1[i].subList3){
							var uniqCnt = {};
							var totalAlertCnt = result.list1[0].overAllCnt;
							namesArrAT.push(result.list1[i].subList3[j].name);
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3"};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
					}
					
					$('#myAlertGraphView').highcharts({
							
							chart: {
								type: 'bar'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
							 min: 0,
								 gridLineWidth: 0,
								 minorGridLineWidth: 0,
								categories: namesArrAT,
								labels: {
								enabled: true,
									
								}
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								},
								labels: {
									enabled: false,
										
									},
								
							},
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';

										$.each(this.points, function () {
										if(this.series.name != "Series 1")  
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
											this.y/* +' - ' +
											(Highcharts.numberFormat(this.percentage,1)+'%'); */
									});

									return s;
								},
								shared: true
							},
							
							legend: {
								verticalAlign:'top',
								enabled: false
							},
							plotOptions: {
									bar: {
										stacking: 'percent',  
										pointWidth: 25,
										gridLineWidth: 15
									},
								
								},
							series: [{
								
								data: countAT,
									
							},
							{
								name: "Number of alerts",
								 data: mainArrTempAT,
								colorByPoint: true,
								 dataLabels: {
									useHTML: true,
									align: 'left',
									
									enabled: true,
									style: {
										fontWeight: 'bold',
										color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
									},
									 formatter: function() {
										return '<span style="position: absolute;"><br/>'+Highcharts.numberFormat(this.percentage,2)+'%'+' '+'('+this.y+')</span>';
									} 
									
								}  
								
							}]
					}); 
		
				}else{
					$('#myAlertGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#myAlertGraphView').html("No Data Available")
		}
}
function getIASOfficerMySubTasksCountView(){

    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMySubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildIASOfficerMySubTasksCountView(result);
    });
}

function buildIASOfficerMySubTasksCountView(result){
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		for(var i in result.list1){
			if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
					str+='<div class="row">';
					str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList2[i].count+'</span></p>';
								str+='</td>';
								str+='</tr>';
							}
					str+='</tbody>';
					str+='</table>';
				str+='</div>';	
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">COMPLETED</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList2[i].completedCnt+'</span></p>';
								str+='</td>';
								str+='</tr>';
							}
					str+='</tbody>';
					str+='</table>';
					str+='<div id="mySubTasksGraphView" style="height:250px"></div>';
				str+='</div>';	
				str+='</div>';	
				$("#mySubTasksDivID").html(str);
			}else{
				$("#mySubTasksDivID").html("No Data Available");
			}
		}
	}else{
		$("#mySubTasksDivID").html("No Data Available");
	}
	
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var mainArrTempAT=[];
			var namesArrAT=[];
			var countAT = [];
			for(var i in result.list1){
				if(result.list1[i].subList3 !=null && result.list1[i].subList3.length>0){
					for(var j in result.list1[i].subList3){
							var uniqCnt = {};
							var totalAlertCnt = result.list1[0].overAllCnt;
							namesArrAT.push(result.list1[i].subList3[j].name);
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3"};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
					}
					
					$('#mySubTasksGraphView').highcharts({
							
							chart: {
								type: 'bar'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
							 min: 0,
								 gridLineWidth: 0,
								 minorGridLineWidth: 0,
								categories: namesArrAT,
								labels: {
								enabled: true,
									
								}
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								},
								labels: {
									enabled: false,
										
									},
								
							},
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';

										$.each(this.points, function () {
										if(this.series.name != "Series 1")  
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
											this.y/* +' - ' +
											(Highcharts.numberFormat(this.percentage,1)+'%'); */
									});

									return s;
								},
								shared: true
							},
							
							legend: {
								verticalAlign:'top',
								enabled: false
							},
							plotOptions: {
									bar: {
										stacking: 'percent',  
										pointWidth: 25,
										gridLineWidth: 15
									},
								
								},
							series: [{
								
								data: countAT,
									
							},
							{
								name: "Number of alerts",
								 data: mainArrTempAT,
								colorByPoint: true,
								 dataLabels: {
									useHTML: true,
									align: 'left',
									
									enabled: true,
									style: {
										fontWeight: 'bold',
										color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
									},
									 formatter: function() {
										return '<span style="position: absolute;"><br/>'+Highcharts.numberFormat(this.percentage,2)+'%'+' '+'('+this.y+')</span>';
									} 
									
								}  
								
							}]
					}); 
		
				}else{
					$('#mySubTasksGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#mySubTasksGraphView').html("No Data Available")
		}
}
function getIASOfficerMyAssignedSubTasksCountView(){
    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAssignedSubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildIASOfficerMyAssignedSubTasksCountView(result)
    });
}
function buildIASOfficerMyAssignedSubTasksCountView(result){
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		for(var i in result.list1){
			if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
					str+='<div class="row">';
					str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList2[i].count+'</span></p>';
								str+='</td>';
								str+='</tr>';
							}
					str+='</tbody>';
					str+='</table>';
				str+='</div>';	
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">COMPLETED</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList2[i].completedCnt+'</span></p>';
								str+='</td>';
								str+='</tr>';
							}
					str+='</tbody>';
					str+='</table>';
					str+='<div id="mySubTasksGraphView" style="height:250px"></div>';
				str+='</div>';	
				str+='</div>';	
				$("#assignedSubTasksDivID").html(str);
			}else{
				$("#assignedSubTasksDivID").html("No Data Available");
			}
		}
	}else{
		$("#assignedSubTasksDivID").html("No Data Available");
	}
	
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var mainArrTempAT=[];
			var namesArrAT=[];
			var countAT = [];
			for(var i in result.list1){
				if(result.list1[i].subList3 !=null && result.list1[i].subList3.length>0){
					for(var j in result.list1[i].subList3){
							var uniqCnt = {};
							var totalAlertCnt = result.list1[0].overAllCnt;
							namesArrAT.push(result.list1[i].subList3[j].name);
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3"};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
					}
					
					$('#mySubTasksGraphView').highcharts({
							
							chart: {
								type: 'bar'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
							 min: 0,
								 gridLineWidth: 0,
								 minorGridLineWidth: 0,
								categories: namesArrAT,
								labels: {
								enabled: true,
									
								}
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								},
								labels: {
									enabled: false,
										
									},
								
							},
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';

										$.each(this.points, function () {
										if(this.series.name != "Series 1")  
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
											this.y/* +' - ' +
											(Highcharts.numberFormat(this.percentage,1)+'%'); */
									});

									return s;
								},
								shared: true
							},
							
							legend: {
								verticalAlign:'top',
								enabled: false
							},
							plotOptions: {
									bar: {
										stacking: 'percent',  
										pointWidth: 25,
										gridLineWidth: 15
									},
								
								},
							series: [{
								
								data: countAT,
									
							},
							{
								name: "Number of alerts",
								 data: mainArrTempAT,
								colorByPoint: true,
								 dataLabels: {
									useHTML: true,
									align: 'left',
									
									enabled: true,
									style: {
										fontWeight: 'bold',
										color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
									},
									 formatter: function() {
										return '<span style="position: absolute;"><br/>'+Highcharts.numberFormat(this.percentage,2)+'%'+' '+'('+this.y+')</span>';
									} 
									
								}  
								
							}]
					}); 
		
				}else{
					$('#mySubTasksGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#mySubTasksGraphView').html("No Data Available")
		}
}


//State Level view
stateLevelDeptOfficerStatusOverview();
stateLevelDeptOfficerLocationLevelOverview();

function stateLevelDeptOfficerStatusOverview(){
	
	var deptIdArr =[];
    var paperIdArr =[];
    var chanelIdArr =[];
    var jsObj ={
      fromDate:"01/01/1997",
      toDate:"01/01/2027",
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerStatusOverViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildstateLevelDeptOfficerStatusOverview(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
}

function buildstateLevelDeptOfficerStatusOverview(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="totalAlertGroupByStatusForGovt" style="height:300px"></div>';
			str+='<div id="statusOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsCls" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str);
	$("#statusOverViewTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		$("#totalAlertGroupByStatusForGovt").highcharts({
			chart: {
				type: 'pie',
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		});
	});
}
function stateLevelDeptOfficerLocationLevelOverview(){
	
	var deptIdArr =[];
    var paperIdArr =[];
    var chanelIdArr =[];
    var jsObj ={
      fromDate:"01/01/1997",
      toDate:"01/01/2027",
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0)
		{
			buildstateLevelDeptOfficerLocationLevelOverviewt(result);
		}else{
			$("#levelWiseAlertOverview").html("NO DATA AVAILABLE");
		}
    });
}

function buildstateLevelDeptOfficerLocationLevelOverviewt(result)
{
	var str='';
	var totalAlert = 0;
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="levelWiseAlertOverviewCnt" style="height:300px"></div>';
			str+='<div id="levelWiseAlertOverviewCntTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getTotalAlertBylocationLvl" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseAlertOverview").html(str);
	$("#levelWiseAlertOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	$(function() {
		$("#levelWiseAlertOverviewCnt").highcharts({
			chart: {
				type: 'pie',
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		});
	});
}

$(document).on("click",".subTaskViewDts",function(){
		stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick();
});

function stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(){
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : departmentIdsGlob,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(result);
    });
}

function buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="totalAlertGroupByStatusWiseSubTask" style="height:300px"></div>';
			str+='<div id="statusWiseSubTaskOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsCls" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusWiseSubTasksOverview").html(str);
	$("#statusWiseSubTaskOverViewTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		$("#totalAlertGroupByStatusWiseSubTask").highcharts({
			chart: {
				type: 'pie',
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		});
	});
}

$(document).on("click",".subTaskLocViewDts",function(){
		stateLevelDeptOfficerLocationLevelOverviewBySubTasks();
});

function stateLevelDeptOfficerLocationLevelOverviewBySubTasks(){

    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : departmentIdsGlob,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewBySubTasksAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerLocationLevelOverviewBySubTasks(result)
    });
}

function buildstateLevelDeptOfficerLocationLevelOverviewBySubTasks(result)
{
	var str='';
	var totalAlert = 0;
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="levelWiseSubTaskAlertOverviewCnt" style="height:300px"></div>';
			str+='<div id="levelWiseSubTaskAlertOverviewCntTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getTotalAlertBylocationLvl" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseSubTasksAlertOverview").html(str);
	$("#levelWiseSubTaskAlertOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	$(function() {
		$("#levelWiseSubTaskAlertOverviewCnt").highcharts({
			chart: {
				type: 'pie',
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		});
	});
}

//stateLevelDeptOfficerDepartmentWiseAlertsView();
function stateLevelDeptOfficerDepartmentWiseAlertsView(){
	//$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr =[];
    var paperIdArr =[];
    var chanelIdArr =[];
    var jsObj ={
      fromDate:"01/01/1997",
      toDate:"01/01/2027",
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
    });
}
//getAllDistrictDetails();
function getAllDistrictDetails(){
	var jsObj ={		
	}
	$.ajax({
		type:'GET',
		url: 'getAllDistrictDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}
//getAllDivisionDetails();
function getAllDivisionDetails(){
	var jsObj ={
		districtId : 5,
	}
	$.ajax({
		type:'GET',
		url: 'getAllDivisionDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}
//getAllSubDivisionDetails();
function getAllSubDivisionDetails(){
	var jsObj ={
			divisionId : 20 ,
	}
	$.ajax({
		type:'GET',
		url: 'getAllSubDivisionDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}


function getStatusWiseForStateLevel(){
	var jsObj={
				fromDateStr:currentFromDate,
				toDateStr:currentToDate,
				stateId : 1,
				printIdArr : printIdArr,
				electronicIdArr : electronicIdArr,		
				govtDepartmentId : 0,
				parentGovtDepartmentScopeId : 1,
				sortingType : "",
				order : "",
				alertType : "",
				districtWorkLocationId : 0,
				divisionWorkLocationId : 0,
				subDivisionWorkLocationId : 0,
				group : ""
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){	
		
	});
}
function getStatusWiseForZoneLevel(){
	
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId : 0,
			parentGovtDepartmentScopeId : 2,
			sortingType : "",
			order : "",
			alertType : "",
			districtWorkLocationId : 0,
			divisionWorkLocationId : 0,
			subDivisionWorkLocationId : 0,
			group : ""
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}

function getStatusWiseForDistrictLevel(){
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId : 0,
			parentGovtDepartmentScopeId : 5,
			sortingType : "",
			order : "",
			alertType : "",
			districtWorkLocationId : 0,
			divisionWorkLocationId : 0,
			subDivisionWorkLocationId : 0,
			group : ""
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}

function getStatusWiseForDivisionLevel(){
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId : 0,
			parentGovtDepartmentScopeId : 6,
			sortingType : "",
			order : "",
			alertType : "",
			districtWorkLocationId : 0,
			divisionWorkLocationId : 0,
			subDivisionWorkLocationId : 0,
			group : ""
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}

function getStatusWiseForSubDivisionLevel(){
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId : 0,
			parentGovtDepartmentScopeId : 7,
			sortingType : "",
			order : "",
			alertType : "",
			districtWorkLocationId : 0,
			divisionWorkLocationId : 0,
			subDivisionWorkLocationId : 0,
			group : ""
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){		
	});
}