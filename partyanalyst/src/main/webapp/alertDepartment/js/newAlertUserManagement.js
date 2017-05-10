 var globalLevelObj =  {"1":"STATE","2":"ZONE","3":"REGION","4":"CIRCLE","5":"DISTRICT","6":"DIVISION","7":"SUB DIVISION","8":"MANDAL","9":"MUNICIPALITY","10":"PANCHAYAT"};
 var globalAlertSourceColorObj =  {"Manual":"#E54BB3","Print Media":"#69BC6E","Electronic Media":"#8D69C8","Call Center":"#EFC000","Facebook":"#00ABED","Twitter":"#F7776C","Social Media":"#05ABHY"};	 
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var globaldepartmentsArrForFilterView=[];
onLoadCallsForState(); 
$(document).on("click",".filterSubmitBtnCls",function(){
		var newsPaperIdLen = newspapersGlobalArr.length;
		var channelIdLen = channelGlobalArr.length;
		var callCenterIdLen = callCenterGlobalArr.length;
		if(newsPaperIdLen == 0 && channelIdLen == 0 && callCenterIdLen == 0){
			alert("Please Select Atleast One Option.");   
			return;
		}
		
			$(".documentCloseClass").hide();
			
	onLoadCallsForState();
});

	function getDeptDetails(){
		var jsObj = {};
		$.ajax({
		  type:'GET',
		  url: 'getDeptDetailsAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null){
				globalUserLevelId = result.levelId;
				if(result.todayAlertIds != null && result.todayAlertIds.length > 0)
				  globalDesignationId = result.todayAlertIds[0];
			   
				
				globalDepartmentIdsArr=result.deptIds;
				if(globalDepartmentIdsArr != null && globalDepartmentIdsArr.length == 1){
					//stateUser
					
					$("#deptWiseAlertsDiv").hide();
					//$(".applyStyleStatus").css("margin-top","35px");
					getDepartmentDetailsByDepartment(globalDepartmentIdsArr[0],result.departmentNames[0]);
				}else{
					$(".applyStyleStatus").removeAttr('style');
				}
				if(result.levelValues != null && result.levelValues.length > 0)
					globalUserLevelValues=result.levelValues;
				
				if(result.govtOfficerIds != null && result.govtOfficerIds.length > 0)
				  globalOfficerIds = result.govtOfficerIds;
			  
			  if(result.govtDeptDesigOffcrIds != null && result.govtDeptDesigOffcrIds.length > 0)
				  globalGovtDeptDesigOffcrIds = result.govtDeptDesigOffcrIds;
			
			}
		
			getIASOfficerMyAlertsCountMainView();
			getIASOfficerMySubTasksCountView();
			getIASOfficerMyAssignedSubTasksCountView();
			getStatusAndLocationWiseDetailsBlock();
			stateLevelDeptOfficerStatusOverview();
			stateLevelDeptOfficerLocationLevelOverview();
			stateLevelDeptOfficerDepartmentWiseAlertsView();
			getAlertSourceWiseAlert();
		});
	}

function onLoadCallsForState(){
	getDeptDetails();
	
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
		
		onLoadCallsForState();
	});
	
function getIASOfficerMyAlertsCountMainView(){
	$("#myAlertsDivID").html(spinner);
    var jsObj ={ 
		fromDate:currentFromDate,
        toDate:currentToDate,
		paperIdArr : newspapersGlobalArr,
        chanelIdArr :channelGlobalArr,
	    callCenterArr:callCenterGlobalArr
	};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAlertsCountMainViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#myAlertsDivID").html('');
		buildIASOfficerMyAlertsCountMainView(result);
		
    });
}
function buildIASOfficerMyAlertsCountMainView(result){
	
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		str+='<div class="row">';
		for(var i in result.list1){
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
				if(result.list1[i].subList1 !=null && result.list1[i].subList1.length>0){
					for(var j in result.list1[i].subList1){
						str+='<tr>';
						str+='<td>';
						if(result.list1[i].subList1[j].count !=null && result.list1[i].subList1[j].count>0){
							str+='<p class="pad_3">'+result.list1[i].subList1[j].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_dept_id ="'+result.list1[i].subList1[j].id+'" attr_type="alert" attr_dept_name ="'+result.list1[i].subList1[j].name+'" attr_count ="'+result.list1[i].subList1[j].count+'" attr_search_type="today">'+result.list1[i].subList1[j].count+'</span></p>';
						}
						str+='</td>';
						str+='</tr>';
					}
					
				}else{
						str+='<p class="pad_3">No Data Available</p>';	
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">OVERALL</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
						if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								if(result.list1[i].subList2[j].count !=null && result.list1[i].subList2[j].count>0){
									str+='<p class="pad_3">'+result.list1[i].subList2[j].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_type="alert" attr_dept_id ="'+result.list1[i].subList2[j].id+'" attr_dept_name ="'+result.list1[i].subList2[j].name+'" attr_count ="'+result.list1[i].subList2[j].count+'" attr_search_type="completed">'+result.list1[i].subList2[j].count+'</span></p>';
								}
								str+='</td>';
								str+='</tr>';
							}
						}else{
							str+='<p class="pad_3">No Data Available</p>';
						}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';	
					str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<div class="scrollerAlert">';
					str+='<div id="myAlertGraphView" ></div>';
					str+='</div>';	
					str+='</div>';	
		}
			str+='</div>';
		$("#myAlertsDivID").html(str);
	}/* else{
		$("#myAlertsDivID").html("No Data Available");
	}	 */	
	
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
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color,"extra":result.departmentId+"-"+result.list1[i].subList3[j].id+"-"+result.list1[i].subList3[j].name+"-"+result.list1[i].subList3[j].count};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3","extra":result.departmentId+"-"+result.list1[i].subList3[j].id+"-"+result.list1[i].subList3[j].name+"-"+result.list1[i].subList3[j].count};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
					}
					if(namesArrAT.length > 4)
					{
						$(".scrollerAlert").mCustomScrollbar({setHeight:'270px'});
					}else{
						$(".scrollerAlert").removeAttr('style');
						$(".scrollerAlert").mCustomScrollbar({setHeight:'auto'});
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
									series: {
										cursor: 'pointer',
										point: {
										events: {
												click: function () {
													var value = (this.extra).split("-");
													var departmentId = value[0];
													var statusId = value[1];
													var statusName = value[2];
													var statuscount = value[3];
													var levelId =0;
													var distLocationId =0;
													var type ="alert";
													var searchType ="completed";
													 if(statuscount == 0){
														return;  
													 }  
													getTotalAlertCountDetails(departmentId,statusId,levelId,type,statusName,statuscount,searchType,'OverAll');
												}
											}
										}
									}
								
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
					$.each($('#myAlertGraphView').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
						$(this).attr("style","cursor:pointer;");    
						$(this).attr("onclick","getTotalAlertCountDetails(\'"+result.departmentId+"\',\'"+result.list1[i].subList3[index].id+"\',0,'alert',\'"+result.list1[i].subList3[index].name+"\',\'"+result.list1[i].subList3[index].count+"\','completed','OverAll')");
					});
					
				}else{
					$('#myAlertGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#myAlertGraphView').html("No Data Available")
		}
		
		var maxHeight = 0;

		$(".panelheightsAlert").each(function(){
		   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
		});
		$(".panelheightsAlert").height(maxHeight);
		
}
function getIASOfficerMySubTasksCountView(){
$("#mySubTasksDivID").html(spinner);
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		paperIdArr : newspapersGlobalArr,
        chanelIdArr :channelGlobalArr,
	    callCenterArr:callCenterGlobalArr
	};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMySubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#mySubTasksDivID").html('');
		buildIASOfficerMySubTasksCountView(result);
    });
}

function buildIASOfficerMySubTasksCountView(result){
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		str+='<div class="row">';
		for(var i in result.list1){
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
				if(result.list1[i].subList1 !=null && result.list1[i].subList1.length>0){
					for(var j in result.list1[i].subList1){
						str+='<tr>';
						str+='<td>';
						if(result.list1[i].subList1[j].count !=null && result.list1[i].subList1[j].count>0){
							str+='<p class="pad_3">'+result.list1[i].subList1[j].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_type="subTask" attr_dept_id ="'+result.list1[i].subList1[j].id+'" attr_dept_name ="'+result.list1[i].subList1[j].name+'" attr_count ="'+result.list1[i].subList1[j].count+'" attr_search_type="today">'+result.list1[i].subList1[j].count+'</span></p>';
							
							
						}
							
						str+='</td>';
						str+='</tr>';
					}
					
				}else{
					str+='<p class="pad_3">No Data Available</p>';
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">OVERALL</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
						if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								if(result.list1[i].subList2[j].count !=null && result.list1[i].subList2[j].count>0){
									str+='<p class="pad_3">'+result.list1[i].subList2[j].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_type="subTask" attr_dept_id ="'+result.list1[i].subList2[j].id+'" attr_dept_name ="'+result.list1[i].subList2[j].name+'" attr_count ="'+result.list1[i].subList2[j].count+'" attr_search_type="completed">'+result.list1[i].subList2[j].count+'</span></p>';
								}
									
								str+='</td>';
								str+='</tr>';
							}
						}else{
							str+='<p class="pad_3">No Data Available</p>';
						}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';	
					str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<div class="scrollerSubTask">';
					str+='<div id="mySubTasksGraphView" ></div>';
					str+='</div>';	
					str+='</div>';	
		}
			str+='</div>';
		$("#mySubTasksDivID").html(str);
	}/* else{
		$("#mySubTasksDivID").html("No Data Available");
	} */
	
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
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color,"extra":result.departmentId+"-"+result.list1[i].subList3[j].id+"-"+result.list1[i].subList3[j].name+"-"+result.list1[i].subList3[j].count};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3","extra":result.departmentId+"-"+result.list1[i].subList3[j].id+"-"+result.list1[i].subList3[j].name+"-"+result.list1[i].subList3[j].count};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
					}
					if(namesArrAT.length > 4)
					{
						$(".scrollerSubTask").mCustomScrollbar({setHeight:'270px'});
					}else{
						$(".scrollerSubTask").removeAttr('style');
						$(".scrollerSubTask").mCustomScrollbar({setHeight:'auto'});
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
									series: {
										cursor: 'pointer',
										point: {
										events: {
												click: function () {
													var value = (this.extra).split("-");
													var departmentId = value[0];
													var statusId = value[1];
													var statusName = value[2];
													var statuscount = value[3];
													var levelId =0;
													var distLocationId =0;
													var type ="subTask";
													var searchType ="completed";
													 if(statuscount == 0){
														return;  
													 }  
													getTotalAlertCountDetails(departmentId,statusId,levelId,type,statusName,statuscount,searchType,'OverAll');
												}
											}
										}
									}
								
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
					$.each($('#mySubTasksGraphView').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
						$(this).attr("style","cursor:pointer;");    
						$(this).attr("onclick","getTotalAlertCountDetails(\'"+result.departmentId+"\',\'"+result.list1[i].subList3[index].id+"\',0,'subTask',\'"+result.list1[i].subList3[index].name+"\',\'"+result.list1[i].subList3[index].count+"\','completed','OverAll')");
					});
		
				}else{
					$('#mySubTasksGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#mySubTasksGraphView').html("No Data Available")
		}
		var maxHeight = 0;

		$(".panelheightsSub").each(function(){
		   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
		});
		$(".panelheightsSub").height(maxHeight);
}
function getIASOfficerMyAssignedSubTasksCountView(){
	$("#assignedSubTasksDivID").html(spinner);
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		paperIdArr : newspapersGlobalArr,
        chanelIdArr :channelGlobalArr,
	    callCenterArr:callCenterGlobalArr
	};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAssignedSubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#assignedSubTasksDivID").html('');
		buildIASOfficerMyAssignedSubTasksCountView(result)
    });
}
function buildIASOfficerMyAssignedSubTasksCountView(result){
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		str+='<div class="row">';
		for(var i in result.list1){
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
				if(result.list1[i].subList1 !=null && result.list1[i].subList1.length>0){
					for(var j in result.list1[i].subList1){
						str+='<tr>';
						str+='<td>';
						if(result.list1[i].subList1[j].count !=null && result.list1[i].subList1[j].count>0){
							str+='<p class="pad_3">'+result.list1[i].subList1[j].name+'<span class="pull-right badge alertCountCls " attr_type="assignSubTask"  style="cursor:pointer;" attr_dept_id ="'+result.list1[i].subList1[j].id+'" attr_dept_name ="'+result.list1[i].subList1[j].name+'" attr_count ="'+result.list1[i].subList1[j].count+'" attr_search_type="today">'+result.list1[i].subList1[j].count+'</span></p>';
						}
							
						str+='</td>';
						str+='</tr>';
					}
					
				}else{
						str+='<p class="pad_3">No Data Available</p>';
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">OVERALL</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
						if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								if(result.list1[i].subList2[j].count !=null &&  result.list1[i].subList2[j].count>0){
									str+='<p class="pad_3">'+result.list1[i].subList2[j].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_type="assignSubTask"  attr_dept_id ="'+result.list1[i].subList2[j].id+'" attr_dept_name ="'+result.list1[i].subList2[j].name+'" attr_count ="'+result.list1[i].subList2[j].count+'" attr_search_type="completed">'+result.list1[i].subList2[j].count+'</span></p>';
								}
									
								str+='</td>';
								str+='</tr>';
							}
						}else{
								str+='<p class="pad_3">No Data Available</p>';
						}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';	
					
					str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<div class="scrollerAssSubTask">';
					str+='<div id="assignedSubTasksGraphView" ></div>';
					str+='</div>';
					str+='</div>';
		}
			str+='</div>';
		$("#assignedSubTasksDivID").html(str);
	}/* else{
		$("#assignedSubTasksDivID").html("No Data Available");
	} */
	
	
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
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color,"extra":result.departmentId+"-"+result.list1[i].subList3[j].id+"-"+result.list1[i].subList3[j].name+"-"+result.list1[i].subList3[j].count};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3","extra":result.departmentId+"-"+result.list1[i].subList3[j].id+"-"+result.list1[i].subList3[j].name+"-"+result.list1[i].subList3[j].count};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
					}
					if(namesArrAT.length > 4)
					{
						$(".scrollerAssSubTask").mCustomScrollbar({setHeight:'270px'});
					}else{
						$(".scrollerAssSubTask").removeAttr('style');
						$(".scrollerAssSubTask").mCustomScrollbar({setHeight:'auto'});
					}
					$('#assignedSubTasksGraphView').highcharts({
							
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
									series: {
										cursor: 'pointer',
										point: {
										events: {
												click: function () {
													var value = (this.extra).split("-");
													var departmentId = value[0];
													var statusId = value[1];
													var statusName = value[2];
													var statuscount = value[3];
													var levelId =0;
													var distLocationId =0;
													var type ="assignSubTask";
													var searchType ="completed";
													 if(statuscount == 0){
														return;  
													 }  
													getTotalAlertCountDetails(departmentId,statusId,levelId,type,statusName,statuscount,searchType,"OverAll");
												}
											}
										}
									}
								
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
					
					$.each($('#assignedSubTasksGraphView').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
						$(this).attr("style","cursor:pointer;");    
						$(this).attr("onclick","getTotalAlertCountDetails(\'"+result.departmentId+"\',\'"+result.list1[i].subList3[index].id+"\',0,'assignSubTask',\'"+result.list1[i].subList3[index].name+"\',\'"+result.list1[i].subList3[index].count+"\','completed','OverAll')");
					});
					
				}else{
					$('#assignedSubTasksGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#assignedSubTasksGraphView').html("No Data Available")
		}
		var maxHeight = 0;

	$(".panelheightsAss").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".panelheightsAss").height(maxHeight);
}
function stateLevelDeptOfficerStatusOverview(){
	$("#statusOverview").html(spinner);
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr :channelGlobalArr,
	  callCenterArr:callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerStatusOverViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#statusOverview").html('');
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
	var pendingCount =0;
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="totalAlertGroupByStatusForGovt" style="height:300px"></div>';
			str+='<div id="statusOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div class="scrollerDivCls">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{
							var statusName = result[i].name;
							if(statusName == "Pending"){
								pendingCount = result[i].alertCnt;
							}		
						totalAlert+=result[i].alertCnt;
						if(result[i].id == 1){
							continue;
						}
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_status_type="overall" attr_type="alert" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'" attr_level_id="0" >'+result[i].alertCnt+'</td>';
							if(result[i].percentage > 0){
							str+='<td>'+result[i].percentage+'%</td>';	
							}else{
								str+='<td> </td>';
							}
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str); 
	
	$("#statusOverViewTotal").html("<h4 style='font-size: 16px;'><span class='overAllAlertCls' attr_department_id ='0' attr_alert_type='' attr_level_type='status' attr_result_type='alert' style='cursor:pointer;' attr_total_alert_count='"+totalAlert+"'>TOTAL -  "+totalAlert+"</span>  <span style='cursor:pointer;margin-left: 10px;' class='getDtlsAlertsCls' attr_status_type ='overall' attr_type='alert' attr_status_name ='Pending' attr_status_count = '"+pendingCount+"' attr_status_id ='1' attr_level_id ='0'>PENDING -  "+pendingCount+"</span></h4>");
	
	var statusOverviewArr =[];
	if(result.length > 6)
	{
		$(".scrollerDivCls").mCustomScrollbar({setHeight:'300px'});
	}
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
	
	$("#levelWiseAlertOverview").html(spinner);
	
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr :channelGlobalArr,
	  callCenterArr:callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#levelWiseAlertOverview").html('');
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
		str+='<div class="scrollerDivClsLevel">';
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
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_status_type="overall" attr_type="alert" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"   attr_status_id="0" attr_level_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseAlertOverview").html(str);
	
	if(result.length > 6)
	{
		$(".scrollerDivClsLevel").mCustomScrollbar({setHeight:'300px'});
	}
	
	$("#levelWiseAlertOverviewCntTotal").html("<h4 class='text-center overAllAlertCls' attr_department_id ='0' attr_alert_type='' attr_level_type='level' attr_result_type='alert' style='cursor:pointer;' attr_total_alert_count='"+totalAlert+"'>TOTAL "+totalAlert+"</h4>")
	var locationOverviewArr =[];
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
		locationOverviewArr.push(obj);
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
				data: locationOverviewArr
			}]
		});
	});
}

$(document).on("click",".subTaskViewDts",function(){
		stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick();
});

function stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(){
	
	$("#statusWiseSubTasksOverview").html(spinner);
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr :channelGlobalArr,
	  callCenterIdsArr:callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#statusWiseSubTasksOverview").html('');
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
		str+='<div class="scrollerStatusSubTask">';
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
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].status+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_status_type="overall" attr_type="subTask" attr_status_name="'+result[i].status+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].statusId+'" attr_level_id="0" >'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#statusWiseSubTasksOverview").html(str);
	
	if(result.length > 6)
	{
		$(".scrollerStatusSubTask").mCustomScrollbar({setHeight:'300px'});
	}
	$("#statusWiseSubTaskOverViewTotal").html("<h4 class='text-center overAllAlertCls' attr_department_id ='0' attr_alert_type='' attr_level_type='' attr_result_type='subTask' style='cursor:pointer;' attr_total_alert_count='"+totalAlert+"'>TOTAL "+totalAlert+"</h4>")
	var statusSubTaskOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].status;
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
		statusSubTaskOverviewArr.push(obj);
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
				data: statusSubTaskOverviewArr
			}]
		});
	});
}

$(document).on("click",".subTaskLocViewDts",function(){
		stateLevelDeptOfficerLocationLevelOverviewBySubTasks();
});

function stateLevelDeptOfficerLocationLevelOverviewBySubTasks(){
$("#levelWiseSubTasksAlertOverview").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr :channelGlobalArr,
	  callCenterIdsArr : callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewBySubTasksAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#levelWiseSubTasksAlertOverview").html('');
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
		str+='<div class="scrollerlocationSubTask">';
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
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_status_type="overall" attr_type="subTask"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="0" attr_level_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseSubTasksAlertOverview").html(str);
	if(result.length > 6)
	{
		$(".scrollerlocationSubTask").mCustomScrollbar({setHeight:'300px'});
	}
	$("#levelWiseSubTaskAlertOverviewCntTotal").html("<h4 class='text-center overAllAlertCls' attr_alert_type='' attr_level_type=' ' attr_department_id ='0' attr_result_type='subTask' style='cursor:pointer;' attr_total_alert_count='"+totalAlert+"'>TOTAL "+totalAlert+"</h4>")
	var locationSubTaskOverviewArr =[];
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
		locationSubTaskOverviewArr.push(obj);
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
				data: locationSubTaskOverviewArr
			}]
		});
	});
}


function stateLevelDeptOfficerDepartmentWiseAlertsView(){
	
	
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr :channelGlobalArr,
	  callCenterArr:callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerDepartmentWiseAlertsView(result);
		
    });
}
//ara
function buildstateLevelDeptOfficerDepartmentWiseAlertsView(result){
	var str='';
	if(result !=null && result.length>0){
		str+='<ul class="slickApply list-inline">';
		for(var i in result){
			var totalAlert = 0;
			var statusNamesArr=[];
			if(result[i].subList2 !=null && result[i].subList2.length>0){
					for(var k in result[i].subList2){
						totalAlert+=result[i].subList2[k].alertCnt;
						statusNamesArr.push(result[i].subList2[k].name);
					}
			}
			str+='<li class="col-sm-4">';
				str+='<div class="panel panel-default">';
					str+='<div class="pad_5">';
							str+='<h4 class="panel-title text-capital fontColor">'+result[i].name+'</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<div class="panel-group" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
							str+='<div class="panel panel-default">';
								str+='<div class="" role="tab" id="headingOne'+i+'" style="padding: 15px;">';
									str+='<a class ="collapseIconForIAS" role="button" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
										str+=' <h4 class="panel-title"> ALERTS</h4></a>';
							str+='</div>';
							str+='<div id="collapseOne'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+i+'">';
								str+='<div class="panel-body">';
									str+='<div id="departmentWiseAlertGraphViewId'+result[i].id+'" style="height:250px"></div>';
										str+='<h4 class="text-center overAllAlertCls" style="cursor:pointer;" attr_alert_type="assigned"  attr_level_type="status" attr_result_type="alert"  attr_total_alert_count="'+totalAlert+'" attr_department_id ="'+result[i].id+'" >TOTAL - '+totalAlert+'</h4>';
										str+='<div class="departmentWiseScrollerAlert">';
										str+='<table class="table tableGraph">';
											str+='<thead>';
												str+='<th>Status</th>';
												str+='<th>Total</th>';
												str+='<th>%</th>';
											str+='</thead>';
											str+='<tbody>';
									if(result[i].subList2 !=null && result[i].subList2.length>0){
										for(var j in result[i].subList2){
											str+='<tr>';
												str+='<td><span class="label" style="background-color:'+result[i].subList2[j].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].subList2[j].name+'</td>';
												
												str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_department_id ="'+result[i].id+'" attr_type="alert"  attr_status_name="'+result[i].subList2[j].name+'" attr_status_count="'+result[i].subList2[j].alertCnt+'"  attr_status_id="'+result[i].subList2[j].id+'" attr_level_id="0" >'+result[i].subList2[j].alertCnt+'</td>';
												
												str+='<td>'+result[i].subList2[j].percentage+'%</td>';
											str+='</tr>';
											
										}
									}
									str+='</tbody>';  
										str+='</table>';
								str+='</div>';	  
								str+='</div>';	  
							str+='</div>';	  
						str+='</div>';			
					
							str+='<div class="panel panel-default">';
								str+='<div class="" role="tab" id="headingTwo'+i+'" style="padding: 15px;">';
									str+='<a class ="collapsed collapseIconForIAS departmentSubTask" attr_department_id = '+result[i].id+' role="button" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseTwo'+i+'" aria-expanded="true" aria-controls="collapseTwo'+i+'">';
										str+=' <h4 class="panel-title"> SUB TASK</h4></a>';
							str+='</div>';
							str+='<div id="collapseTwo'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo'+i+'">';
								str+='<div class="panel-body">';
									str+='<div id="departmentWiseSubTaskDetailsId'+result[i].id+'"></div>';
									str+='<div id="departmentWiseSubTaskTableView'+result[i].id+'"></div>';
								str+='</div>';	  
							str+='</div>';	  
						str+='</div>';			
					str+='</div>';
				str+='</div>';
				
				str+='<div class="" style="text-align:center;"><button type="button" class="btn btn-default btn-sm buttonCustomStyle detailedBlockDiv" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'">Detailed Information</button></div>';
			str+='</div>';
			str+='</li>';
		}
		str+='</ul>';
	}
	$("#departmentWiseAlertsDetailsId").html(str);
	$('.slickApply').slick({
	   slide: 'li',
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  infinite: false,
	  swipe:false,
	 touchMove:false,
	  variableWidth: false
	});
	if(statusNamesArr.length > 6)
	{
		$(".departmentWiseScrollerAlert").mCustomScrollbar({setHeight:'300px'});
	}
	
	if(result !=null && result.length>0){
		var deptStatusOverviewArr =[];
		var departmentIdForStatus='';
		for(var i in result)
		{
			departmentIdForStatus = result[i].id;
			if(result[i].subList2 !=null && result[i].subList2.length>0){
				for(var j in result[i].subList2){
					statusPercent = result[i].subList2[j].percentage;
					statusName = result[i].subList2[j].name;
					var cnt = result[i].subList2[j].alertCnt;
					var stsId = result[i].subList2[j].id;
					var colorsId = result[i].subList2[j].color
					//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
					
					var obj = {
						name: statusName,
						y:statusPercent,
						count:cnt,   
						sts:stsId,
						color:colorsId
					}
					deptStatusOverviewArr.push(obj);
				}
			}
		
		
		
			$("#departmentWiseAlertGraphViewId"+result[i].id).highcharts({
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
					data: deptStatusOverviewArr
				}]
			});
		}
		
	}else{
		$("#departmentWiseAlertGraphViewId"+departmentIdForStatus).html("No Data Available")
	}
	
}

$(document).on("click",".departmentSubTask",function(){
	var departmentId = $(this).attr("attr_department_id");
	stateLevelDepartmentWiseSubTaskDetails(departmentId);
});	
function stateLevelDepartmentWiseSubTaskDetails(departmentId){
	
	var deptIdStrArr = [];
	deptIdStrArr.push(departmentId);
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : deptIdStrArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr :channelGlobalArr,
	  callCenterIdsArr : callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickss(result,departmentId);
    });
}

function buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickss(result,departmentId)
{
	var str='';
	if(result !=null && result.length>0){
		var totalAlert = 0;
			for(var i in result)
			{
				
				totalAlert+=result[i].alertCnt;
			}
			str+='<h4 class="text-center overAllAlertCls" style="cursor:pointer;"  attr_alert_type="" attr_level_type="" attr_result_type="subTask"  attr_total_alert_count="'+totalAlert+'" attr_department_id ="'+departmentId+'">TOTAL - '+totalAlert+'</h4>';
			str+='<div class="departmentWiseSubTaskScroller">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].status+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_department_id = "'+departmentId+'" attr_type="subTask"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].statusId+'" attr_level_id="0"  >'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
			str+='</div>';
	}
	$("#departmentWiseSubTaskTableView"+departmentId).html(str);
	if(result.length > 6)
	{
		$(".departmentWiseScrollerAlert").mCustomScrollbar({setHeight:'300px'});
	}
	var departmentstatusSubTaskOverviewArr =[];
	if(result !=null && result.length>0){
		for(var i in result)
		{
			statusPercent = result[i].percentage;
			statusName = result[i].status;
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
			departmentstatusSubTaskOverviewArr.push(obj);
		}
	
	
		$("#departmentWiseSubTaskDetailsId"+departmentId).highcharts({
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
				data: departmentstatusSubTaskOverviewArr
			}]
		});
	}else{
		$("#departmentWiseSubTaskDetailsId"+departmentId).html("No Data Available")
	}
}

$(document).on("click",".alertCountCls",function(){
  var deptId = $(this).attr("attr_dept_id");
  var deptName = $(this).attr("attr_dept_name");
  var count = $(this).attr("attr_count");
  var type = $(this).attr("attr_type");
  var searchType = $(this).attr("attr_search_type");
  getTotalAlertCountDetails(deptId,0,0,type,deptName,count,searchType)
});
function getTotalAlertCountDetails(departmentId,statusId,levelId,type,statusName,statuscount,searchType,resultType){
  $("#alertManagementPopupBody").html('')
  
    $("#alertManagementPopup").modal({
      show: true,
      keyboard: false,
      backdrop: 'static'
    });
	var deptArr = [];
	 if(resultType != null && resultType=="OverAll"){
		 deptArr = globalDepartmentIdsArr;
	 }else{
	   deptArr.push(departmentId);	 
	 }
    $("#alertManagementPopupBody").html(spinner);
    var jObj = {
		  deptIdsArr: deptArr,//status and location 0
		  statusId:statusId,
		  type:type,
		  officerIdsArr:globalOfficerIds,
		  desigDeptOfficerIdsArr:globalGovtDeptDesigOffcrIds,
		  searchType:searchType,
		  fromDate:currentFromDate,
		  toDate:currentToDate
    }
  $.ajax({
      type:'GET',
      url: 'getStateLevelAlertclickViewAction.action',
    data: {task :JSON.stringify(jObj)}
    }).done(function(result){
    if(result != null && result.length > 0){
      $("#totalAlertsModalTabId").html('');
      buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
    }else{
      $("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
    }
  });
  
}
	
//click functionality
$(document).on("click",".getDtlsAlertsCls",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var type = $(this).attr("attr_type");
		var levelId = $(this).attr("attr_level_id");
		var departmentIds = $(this).attr("attr_department_id");
		var resultType = $(this).attr("attr_status_type");
		var departmentIdsArr = [];
		 if(resultType != null && resultType=="overall"){
			 departmentIdsArr = globalDepartmentIdsArr;
		 }else{
		     departmentIdsArr.push(departmentIds);
		 }
		
		getTotalAlertCountDetailsForStatusAndLocationView(departmentIdsArr,levelId,statusId,type,statusName,statuscount,"","");
	});
	//santosh
  $(document).on("click",".overAllAlertCls",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var deptArr = [];
		var resultType = $(this).attr("attr_result_type");
		var deptId = $(this).attr("attr_department_id");
		var alertType = $(this).attr("attr_alert_type");
		var levelType = $(this).attr("attr_level_type");
		var totalAlertCnt = $(this).attr("attr_total_alert_count");
		if(deptId !=null && deptId==0){
		   	deptArr=globalDepartmentIdsArr;
		}else{
			deptArr.push(deptId);
		}
		getTotalAlertCountDetailsForStatusAndLocationView(deptArr,0,0,resultType,"Total",totalAlertCnt,levelType,alertType);
	 });
//click functioality...
function getTotalAlertCountDetailsForStatusAndLocationView(departmentIdsArr,levelId,statusId,type,statusName,statuscount,levelType,alertType){
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
	
		//callCenterArr.push(1);
		var jObj = {
			departmentIdsArr: departmentIdsArr,
			levelId: levelId, 
			statusId:statusId,
			type:type,
			startDate:currentFromDate,
			endDate:currentToDate,
			desigDeptOfficerId:0,
			officerId:0,
			paperIdArr:newspapersGlobalArr,
			chanelIdArr:channelGlobalArr,
			callCenterArr:callCenterGlobalArr,
			stateId :1,
			levelType:levelType,
			alertType:alertType
			
		}
	$.ajax({
      type:'GET',
      url: 'getStateLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
	
}

$(document).on("click",".detailedBlockDiv",function(){
	var departmentId = $(this).attr("attr_department_id");
	var departmentName = $(this).attr("attr_department_name");
	
	getDepartmentDetailsByDepartment(departmentId,departmentName);
	
});	
function getDepartmentDetailsByDepartment(departmentId,departmentName){ 
$("#departmentWiseLocationBlockId").html(spinner);
   var jsObj ={
		departmentId:departmentId,
		designationType:""
    }
    $.ajax({
    type:'GET',                        
    url: 'getDepartmentDetailsByDepartmentAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		$("#departmentWiseLocationBlockId").html('');
      buildDepartmentDetailsByDepartmentss(result,departmentId,departmentName);
    }); 
}


function buildDepartmentDetailsByDepartmentss(result,departmentId,departmentName){
		
	if(result !=null && result.length>0){
		var str='';
		for(var i in result){
				var levelIdStr = '';
				var subLevelIdStr = '';
				var districtLevelId ='';
				var childLevelIdsStr='';
				for(var j in result[i].subList1){
				   if(j == 0){
					    levelIdStr = result[i].subList1[j].id;
					   
				   }else{
					    levelIdStr = levelIdStr+','+result[i].subList1[j].id;
					   
				   }
				  for(var k in result[i].subList1[j].subList1){
					  if(k == 0){
						  subLevelIdStr = result[i].subList1[j].subList1[k].id;
						  districtLevelId = result[i].subList1[j].subList1[0].id;
						   childLevelIdsStr = result[i].subList1[j].subList1[k].childLevelId;
					  }else{
						   subLevelIdStr = subLevelIdStr+','+result[i].subList1[j].subList1[k].id;
						   childLevelIdsStr = childLevelIdsStr+','+result[i].subList1[j].subList1[k].childLevelId;
					  }
							
				   } 
				}
				
					
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading headingColor ">';
					str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<h4 class="panel-title text-capital fontColor">'+departmentName+'</h4>';
						str+='</div>';
						str+='<div class="col-md-6 col-xs-12 col-sm-4">';
							str+='<ul class="switch-btn pull-right">';
								str+='<li class="active" attr_type="statuswise" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">status overview</li>';
								str+='<li attr_type="scopewise"  attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">location level</li>';
								str+='<li attr_type="alertSource"  attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Alert Source</li>';
								str+='<li id="filterViewId" attr_type="filterView" attr_department_name="'+result[i].name+'" attr_department_id="'+result[i].id+'">Filter</li>';
							str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-4 ">';
							str+='<ul class="switch-btn-alertType pull-right">';
								str+='<li  attr_type="alert" class="active" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Alerts</li>';
								str+='<li attr_type="subTask" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Sub Tasks</li>';
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			
					
				str+='<div class="panel-body" style="display:none;" id="filterViewBodyId"></div>';
					
				str+='<div class="panel-body" id="statusOvrvwId">';
						if(result[i].subList1 !=null && result[i].subList1.length>0){
							for(var j in result[i].subList1){
								str+='<div class="col-md-12 col-xs-12 col-sm-12" id="departmentWiseBlocks'+result[i].id+''+result[i].subList1[j].id+'">';
								str+='<h4>'+result[i].subList1[j].name+'</h4>';
								
									str+='<div class="row m_top20">';
										str+='<div class="col-md-2 col-xs-12 col-sm-12">';
											str+='<ul class="list-inline activeUlCls  constituencyUl locationAndStatusWiseSorting">';
											str+='<li class="active sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'"> ';
												str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
											str+='</li>';
											str+='<li class="sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="count" attr_order_type="asc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'" attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
												str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
											str+='</li>';
											str+='<li class="sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="name" attr_order_type="asc"  attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'" attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
												str+='A-Z';
											str+='</li>';
											str+='<li class="sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="name" attr_order_type="desc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"   attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
												str+='Z-A';
											str+='</li>';
											str+='</ul>';
										str+='</div>';
										
								
									if(result[i].subList1[j].subList1 !=null && result[i].subList1[j].subList1.length>0){
											for(var k in result[i].subList1[j].subList1){
												str+='<div class="col-sm-4 col-xs-12 col-md-2">';
													str+='<select class="form-control districtWiseOnChange" id="locationNamesId'+result[i].id+''+result[i].subList1[j].id+''+result[i].subList1[j].subList1[k].id+'" attr_department_id = "'+result[i].id+'" attr_level_id="'+result[i].subList1[j].id+'" attr_sublevel_id="'+result[i].subList1[j].subList1[k].id+'" attr_child_id="'+result[i].subList1[j].subList1[k].childLevelId+'" >';
														str+='<option value="0">SELECT '+result[i].subList1[j].subList1[k].name+' </option>';
													str+='</select>';
												str+='</div>';
											}
										}
										if(result[i].subList1[j].idnameList !=null && result[i].subList1[j].idnameList.length>0){
												str+='<div class="col-sm-4 col-xs-12 col-md-2 pull-right locationLevelWiseDivCls">';
															str+='<select class="form-control locationLevelWiseOnChange" id="locationLevelNamesId'+result[i].id+''+result[i].subList1[j].id+'" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'" attr_sublevel_id="'+subLevelIdStr+'" attr_child_id = "'+childLevelIdsStr+'" attr_district_level_id = "'+districtLevelId+'">';
																str+='<option value="0">ALL</option>';
																for(var l in result[i].subList1[j].idnameList){
																	str+='<option value="'+result[i].subList1[j].idnameList[l].id+'">'+result[i].subList1[j].idnameList[l].name+' </option>';
																}	
															str+='</select>';
														str+='</div>';
											}
										str+='</div>';
											str+='<div class="row m_top20">';	
												str+='<div class="col-sm-12">';
													str+='<div class="scollerDiv'+result[i].id+''+result[i].subList1[j].id+'">';
														str+='<div id="levelWiseGraphView'+result[i].id+''+result[i].subList1[j].id+'"></div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
							}
						}
						
						
					str+='</div>';//panelbody
			}
			
		str+='</div>';
		
		$("#departmentWiseLocationBlockId").html(str);
		var deptObj = result[0];
		
		 if(deptObj.subList1 != null && deptObj.subList1.length > 0){
			 for(var i in deptObj.subList1){
				getStateThenGovtDeptScopeWiseAlertCount(deptObj.id,deptObj.subList1[i].id,"statuswise","alert","levelWiseGraphView","count","desc",0,0,"Default","Other",0);
				$("#locationLevelNamesId"+deptObj.id+deptObj.subList1[i].id).chosen();
				if(deptObj.subList1[i].subList1 !=null && deptObj.subList1[i].subList1.length>0){
					for(var j in deptObj.subList1[i].subList1){
						$("#locationNamesId"+deptObj.id+deptObj.subList1[i].id+deptObj.subList1[i].subList1[j].id).chosen();
						if(j==0){
							getLocationBasedOnDepartmentLevel(deptObj.id,deptObj.subList1[i].id,deptObj.subList1[i].subList1[0].id);
						}
					}
				}
			 }
		 }
		 
	}else{
		$("#departmentWiseLocationBlockId").html("No Data Available");
	}
	
}


function filerterViewBody()
{
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			str+='<div id="assignedUser1"></div>';
		str+='</div>	';
		str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			str+='<div id="getSubOrdinateFilterAlertsOverview"></div>';
		str+='</div>';
	str+='</div>';
	$("#filterViewBodyId").html(str);
}

	function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getsearchType(){
		 var searchType = ''; 
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		return searchType;
	}

$(document).on("click",".switch-btn-alertType li",function(){
	 
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var departmentName = $(this).attr("attr_department_name");
		var parentIdStr = $(this).attr("attr_level_idstr").split(',');
		var alertType =  $(this).attr("attr_type");
		var searchType = getsearchType();
		
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var sortingType='';
		var orderType='';

		$('.sortingCls'+departmentId+parentIdStr).each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType = $(this).attr("attr_order_type");
			 }
		});
		
		 if($("#filterViewId").hasClass("active") == true)
		{
			var deptObj = {id : departmentId,name: departmentName}
			globaldepartmentsArrForFilterView.push(deptObj);
			filerterViewBody()
			assignUser1(globaldepartmentsArrForFilterView);
			
			
		}
		
		var districtLevelId = $(this).attr("attr_district_level_id");
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		if(searchType == "statuswise" || searchType == "alertSource"){
		  $(".locationLevelWiseDivCls").show();
		}else{
		  $(".locationLevelWiseDivCls").hide();
		}
		
		for(var i in parentIdStr){
				for(var j in subLevelIdStr){
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).html('');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[j]]+'</option>');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).trigger('chosen:updated');
					$("#locationLevelNamesId"+departmentId+parentIdStr[i]).val(0);
					$("#locationLevelNamesId"+departmentId+parentIdStr[i]).trigger('chosen:updated')
			}
		}
		var locationLevelId=0;
		for(var i in parentIdStr){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentIdStr[i],searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Other",locationLevelId);
			getLocationBasedOnDepartmentLevel(departmentId,parentIdStr[i],districtLevelId);
			
			
		}
		
		
		
		
});
	
$(document).on("click",".switch-btn li",function(){
	   
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var departmentName = $(this).attr("attr_department_name");
	if(searchType == "statuswise" || searchType == "scopewise" || searchType == "alertSource"){
		var parentIdStr = $(this).attr("attr_level_idstr").split(',');
		var searchType =  $(this).attr("attr_type");
		var alertType = getAlertType();
		
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var sortingType='';
		var orderType='';
		
		$('.sortingCls'+departmentId+parentIdStr).each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType = $(this).attr("attr_order_type");
			 }
		});
		var districtLevelId = $(this).attr("attr_district_level_id");
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		if(searchType == "statuswise" || searchType == "alertSource"){
		  $(".locationLevelWiseDivCls").show();
		}else{
		  $(".locationLevelWiseDivCls").hide();
		}
		
		for(var i in parentIdStr){
				for(var j in subLevelIdStr){
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).html('');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[j]]+'</option>');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).trigger('chosen:updated');
					$("#locationLevelNamesId"+departmentId+parentIdStr[i]).val(0);
					$("#locationLevelNamesId"+departmentId+parentIdStr[i]).trigger('chosen:updated')
			}
			
		}
		
		var locationLevelId=0;
		for(var i in parentIdStr){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentIdStr[i],searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Other",locationLevelId);
			getLocationBasedOnDepartmentLevel(departmentId,parentIdStr[i],districtLevelId);
			
			
		}
		$("#statusOvrvwId").show();
		$("#filterViewBodyId").hide();
	}else{
		var deptObj1 = {id : departmentId,name: departmentName}
		globaldepartmentsArrForFilterView.push(deptObj1)
	
		filerterViewBody()
		assignUser1(globaldepartmentsArrForFilterView);
		
		$("#statusOvrvwId").hide();
		$("#filterViewBodyId").show();
		
	}
});

$(document).on("click",".locationAndStatusWiseSorting li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var parentId = $(this).attr("attr_parent_id");
		
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var searchType = getsearchType();
		var alertType = getAlertType();
		var districtLevelId = $(this).attr("attr_district_level_id");
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		if(searchType == "statuswise" || searchType == "alertSource"){
		  $(".locationLevelWiseDivCls").show();
		}else{
		  $(".locationLevelWiseDivCls").hide();
		}
		
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).trigger('chosen:updated');
		}
		$("#locationLevelNamesId"+departmentId+parentId).val(0);
		$("#locationLevelNamesId"+departmentId+parentId).trigger('chosen:updated')
		var locationLevelId=0;
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Sorting",locationLevelId);
		getLocationBasedOnDepartmentLevel(departmentId,parentId,districtLevelId);
});
$(document).on("change",".locationLevelWiseOnChange",function(){
		var departmentId = $(this).attr("attr_department_id");
		var levelId = $(this).attr("attr_parent_id");
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var districtLevelId = $(this).attr("attr_district_level_id");
		var sortingType='';
		var orderType='';
		var alertType = getAlertType();
		$('.sortingCls'+departmentId+levelId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType = $(this).attr("attr_order_type");
			 }
		});
		var searchType = getsearchType();
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+levelId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+levelId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+levelId+subLevelIdStr[i]).trigger('chosen:updated');
			
		}
			
		var locationLevelId =0;
		locationLevelId = $("#locationLevelNamesId"+departmentId+levelId).val();
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Change","Sorting",locationLevelId);
		getLocationBasedOnDepartmentLevel(departmentId,levelId,districtLevelId);
});

function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,alertType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,selectionType,locationLevelId){
	$("#"+divId+departmentId+parentGovtDepartmentScopeId).html(spinner);
	
	 if(parentGovtDepartmentScopeId == 1 && selectionType != "Sorting"){
		 orderType = "Default";
	 }
	 var locationLevelIdArr=[];
	 
	 if(locationLevelId == null || locationLevelId == 0){
		 locationLevelIdArr =[];
	 }else{
		 locationLevelIdArr.push(locationLevelId);
	 }
   var jsObj={
		fromDateStr:currentFromDate,
		toDateStr:currentToDate,
		stateId : 1,
		printIdArr : newspapersGlobalArr,
		electronicIdArr : channelGlobalArr,		
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentGovtDepartmentScopeId,
		sortingType :sortingType,
		order :orderType,
		alertType :alertType,
		districtWorkLocationId : 0,
		divisionWorkLocationId : 0,
		subDivisionWorkLocationId : 0,
		group :"status",
		subLevels:locationLevelIdArr,
		chanelIdArr:callCenterGlobalArr,
		searchType:searchType,
		filterParentScopeId :filterParentScopeId,
		filterScopeValue:filterScopeValue
	}
    $.ajax({
    type:'GET',         
    url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#"+divId+departmentId+parentGovtDepartmentScopeId).html('');
		buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId);
	});
}

function buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId){
	
	
	if(searchType == "statuswise" || searchType == "alertSource" ){
		var alertType = getAlertType();
		$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).show();
		if(result !=null && result.length>0){
			if(searchType == "statuswise"){
				var statusNamesArr=[];
				var pendingAlertArr = [];
				 var notifiedAlertArr = [];
				 var actionInProgessAlertArr = [];
				 var completedAlertArr = [];
				 var unblTRslvAlertArr = [];
				 var actionNotRequiredAlertArr = [];
				 var duplicateAlertArr = [];
				 var WronglyMappedDesignationArr = [];
				 var WronglyMappedDepartmentArr = [];
				 var RejoinderArr = [];
				 var Incomplete = [];
				 var Closed = [];
				 var Proposal = [];
					for(var i in result){
						
						 statusNamesArr.push(result[i].name)
						
						if(result[i].subList !=null &&  result[i].subList.length>0){
							for(var j in result[i].subList){
									if(alertType == "alert"){
										
										if(result[i].subList[j].id==1){
											pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId}); 
										}else if(result[i].subList[j].id==2){
											 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==3){
											 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==4){
											 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==5){
											 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==6){
											 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==7){
											 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}
										else if(result[i].subList[j].id==8){
											 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==9){
											 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==10){
											 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==11){
											 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==12){
											 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==13){
											 Proposal.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}
									}else if(alertType == "subTask"){
										
										if(result[i].subList[j].id==1){
											 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==2){
											 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
										}else if(result[i].subList[j].id==3){
											 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==5){
											 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==4){
											 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==6){
											 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}else if(result[i].subList[j].id==7){
											 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
										}
									}
								 
								
								
								
							}
							
						}
					
					
					var mainJosnObjArr = [];
					   if(pendingAlertArr != null && pendingAlertArr.length > 0){
						mainJosnObjArr.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
					  }
					   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
						mainJosnObjArr.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
					  }
					  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
						mainJosnObjArr.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
					  }
					  if(completedAlertArr != null && completedAlertArr.length > 0){
						mainJosnObjArr.push({name:'Completed',data:completedAlertArr,color:"#4d9b66"});  
					  }
					  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
						mainJosnObjArr.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
					  }
					  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
						mainJosnObjArr.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
					  }
					  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
						mainJosnObjArr.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
					  }
					   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
						mainJosnObjArr.push({name:'Wrongly Mapped Designation',data:WronglyMappedDesignationArr,color:"#FE9900"});  
					  }
					   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
						mainJosnObjArr.push({name:'Wrongly Mapped Department',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
					  }
					   if(RejoinderArr != null && RejoinderArr.length > 0){
						mainJosnObjArr.push({name:'Rejoinder',data:RejoinderArr,color:"#82CA9C"});  
					  } if(Incomplete != null && Incomplete.length > 0){
						mainJosnObjArr.push({name:'Reopen',data:Incomplete,color:"#C9AC82"});  
					  }if(Closed != null && Closed.length > 0){
						mainJosnObjArr.push({name:'Closed',data:Closed,color:"#ababab"});  
					  }if(Proposal != null && Proposal.length > 0){
						mainJosnObjArr.push({name:'Proposal',data:Proposal,color:"#5a8476"});  
					  }
				
				}
				
			}else if(searchType == "alertSource"){
				
				 var statusNamesArr=[];
				 var manualArr=[];
			     var printMediaArr = [];
				 var electronicMediaArr = [];
				 var callCenterArr = [];
				 var socialMediaArr = [];
				 
				 for(var i in result){
					
					 statusNamesArr.push(result[i].name)
					
					if(result[i].subList !=null &&  result[i].subList.length>0){
						for(var j in result[i].subList){
								
							 if(result[i].subList[j].id==1){
								 manualArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId}); 
							}else if(result[i].subList[j].id==2){
								 printMediaArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}else if(result[i].subList[j].id==3){
								 electronicMediaArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}else if(result[i].subList[j].id==4){
								 callCenterArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}else if(result[i].subList[j].id==5){
								 socialMediaArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}

							
						}
						
					}
				
				 var mainJosnObjArr = [];
				
				   if(manualArr != null && manualArr.length > 0){
					mainJosnObjArr.push({name:'Manual',data:manualArr,color:"#E54BB3"});  
				  }
				   if(printMediaArr != null && printMediaArr.length > 0){
					mainJosnObjArr.push({name:'Print Media',data:printMediaArr,color:"#69BC6E"});  
				  }
				  if(electronicMediaArr != null && electronicMediaArr.length > 0){
					mainJosnObjArr.push({name:'Electronic Media',data:electronicMediaArr,color:"#8D69C8"});  
				  }
				  if(callCenterArr != null && callCenterArr.length > 0){
					mainJosnObjArr.push({name:'Call Center',data:callCenterArr,color:"#EFC000"});  
				  }
				  if(socialMediaArr != null && socialMediaArr.length > 0){
					mainJosnObjArr.push({name:'Social Media',data:socialMediaArr,color:"#05ABHY"});  
				  }
				  
			
				}
				 
			}
			
			
			
			
			
			var heightOfDiv = statusNamesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height",heightOfDiv);
				$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).mCustomScrollbar({setHeight:'600px'})
			}else{
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","auto");
				$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style')
				$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).mCustomScrollbar('destroy');
			  }
			
			
			
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).highcharts({
					chart: {
						type: 'bar',
						backgroundColor:'transparent'
					
					},
					title: {
						text: null
					},
					subtitle: {
						text: null
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: statusNamesArr
					},
					yAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
						title: {
							text: null
						},
						labels: {
							enabled:false
						},
						stackLabels: {
							//useHTML: true,
							//align: 'left',
							enabled: true,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
							},
							formatter: function() {
							
								//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
								//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
								return (this.total);
							} 
						
						}
					
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
					plotOptions: {
						bar: {
							stacking: 'normal',
							pointWidth: 30,
							gridLineWidth: 15
						},series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var statusId = '';
										var statusName = value[1];
										var totalCount = value[2];
										var levelId='';
										var locationValue='';
										if(parentGovtDepartmentScopeId == 1){
											locationValue=value[4];
											levelId =value[3]; 
										}else{
											locationValue=value[3];
											levelId = 0;
										}
										var alertCategoryId='';
										if(searchType == "alertSource"){
											alertCategoryId = value[0];
											statusId = 0;
										}else if(searchType == "statuswise"){
											alertCategoryId = 0;
											statusId = value[0];
										}
										
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,locationLevelId)
									}
								}
							}
				        }
					},
					legend: {
						verticalAlign:'top',
						enabled: true
					},
					series: mainJosnObjArr
				});
		
			
			
				 $.each($("#"+divId+departmentId+parentGovtDepartmentScopeId).find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;"); 
					if(parentGovtDepartmentScopeId == 1){
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',\'"+result[index].id+"\',0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].stateId+"\',\'"+parentGovtDepartmentScopeId+"\',0,\'"+locationLevelId+"\')");	
					}else{
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,\'"+locationLevelId+"\')");
					}
				
				});
				
		}else{
			if(actionType=="Default"){
			   $("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();//Scope Level DivId hiding if data is not available
			}else{
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).removeAttr('style')
		       $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12">No Data Available</div>');
			   $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style')
			   $("#"+divId+departmentId+parentGovtDepartmentScopeId).css('height',"25px;");
			}
		}
	}else if(searchType == "scopewise" && parentGovtDepartmentScopeId != 1){
		$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).show();
		if(result !=null && result.length>0){
			
			     var locationNamesArr=[];
				 var stateArr = [];
				 var goneArr = [];
				 var regionArr = [];
				 var circleArr = [];
				 var districtArr = [];
				 var divisionArr = [];
				 var subDivisionArr = [];
				 var mandalArr = [];
				 var municipalityArr = [];
				 var panchayatArr = [];
				 
			for(var i in result){
				 locationNamesArr.push(result[i].name)
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			
			var mainJosnObjLocArr = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjLocArr.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjLocArr.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjLocArr.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjLocArr.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjLocArr.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjLocArr.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjLocArr.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjLocArr.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjLocArr.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjLocArr.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		}
			
			var heightOfDiv = locationNamesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height",heightOfDiv);
				$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).mCustomScrollbar({setHeight:'600px'});
			}else{
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","auto");
				$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style')
				$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).mCustomScrollbar('destroy');
			  }
			
			
			
			$("#"+divId+departmentId+parentGovtDepartmentScopeId).highcharts({
					chart: {
						type: 'bar',
						backgroundColor:'transparent'
					
					},
					title: {
						text: null
					},
					subtitle: {
						text: null
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: locationNamesArr
					},
					yAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
						title: {
							text: null
						},
						labels: {
							enabled:false
						},
						stackLabels: {
							//useHTML: true,
							//align: 'left',
							enabled: true,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
							},
							formatter: function() {
							
								//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
								//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
								return (this.total);
							} 
						
						}
					
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
					plotOptions: {
						bar: {
							stacking: 'normal',
							pointWidth: 30,
							gridLineWidth: 15
						},series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var levelId=value[0];
										var locationValue='';
										if(parentGovtDepartmentScopeId == 1){
											locationValue=1;
										}else{
											locationValue=value[3];
										}
										var alertCategoryId =0;
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,0)
									}
								}
							}
				        }
					},
					legend: {
						verticalAlign:'top',
						enabled: true
					},
					series: mainJosnObjLocArr
				});
			
				$.each($("#"+divId+departmentId+parentGovtDepartmentScopeId).find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					 if(parentGovtDepartmentScopeId == 1){
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',1,\'"+parentGovtDepartmentScopeId+"\',0,0)");	
					}else{
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,0)");
					}
					
				});
		}else{
			if(actionType=="Default"){
			  $("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();
			}else{
				$("#"+divId+departmentId+parentGovtDepartmentScopeId).removeAttr('style')
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			  $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","25px"); 
			}
		}
	}else if(searchType == "scopewise" && parentGovtDepartmentScopeId == 1){
		$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).show();
		if(result !=null && result.length>0){
				var mainlocationArr =[];
					var nmaesArr =[];
					var colorArr=[];
				if(result[0].subList !=null && result[0].subList.length>0){
					for(var j in result[0].subList){
						var tempArr = {"y":result[0].subList[j].count,color:result[0].subList[j].severtyColor,"extra":result[0].subList[j].id+"-"+result[0].subList[j].name+"-"+result[0].subList[j].count+"-"+result[0].id};
						
						nmaesArr.push(result[0].subList[j].name);
						mainlocationArr.push(tempArr);
					}
					
				}

			$("#"+divId+departmentId+parentGovtDepartmentScopeId).highcharts({
				
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
					categories: nmaesArr,
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
					 pointFormat: '<b>{point.y}</b>',
					 shared:true
				},
				
				legend: {
					verticalAlign:'top',
					enabled: false
				},
				plotOptions : {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
						series: {
								cursor: 'pointer',
								point: {
								events: {
										click: function () {
										var value = (this.extra).split("-");
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var levelId=value[0];
										var locationValue=value[3];
										var alertCategoryId =0;
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,0)
									}
									}
								}
							}
					},
					
				series: [{
					 name: '',
					 data: mainlocationArr,
					 colorByPoint:true,
					 dataLabels: {
							enabled: true,
							 format: '{point.y}', // one decimal
						}
					 
				}]
			});
			 $.each($("#"+divId+departmentId+parentGovtDepartmentScopeId).find(".highcharts-xaxis-labels").find("text"),function(index,item){   
				$(this).attr("style","cursor:pointer;");    
				$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',\'"+result[0].subList[index].id+"\',0,\'"+result[0].subList[index].name+"\',\'"+result[0].subList[index].count+"\',\'"+result[0].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,0)");
			});
		}else{
			$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();
			/* $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12">No Data Available</div>')
			$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			$("#"+divId+departmentId+parentGovtDepartmentScopeId).css('height',"25px;") */
		}
	}
}
$(document).on("change",".districtWiseOnChange",function(){
		var departmentId = $(this).attr("attr_department_id");
		var levelId = $(this).attr("attr_level_id");
		var subLevelId = $(this).attr("attr_sublevel_id");
		var childLevelId = $(this).attr("attr_child_id");
		
		var locationValue = $("#locationNamesId"+departmentId+levelId+subLevelId).val();
		var sortingType='';
		var orderType='';

		$('.sortingCls'+departmentId+levelId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType = $(this).attr("attr_order_type");
			 }
		});
		var searchType = getsearchType();
		var alertType = getAlertType();
		 $("#locationLevelNamesId"+departmentId+levelId).val(0);
		 $("#locationLevelNamesId"+departmentId+levelId).trigger('chosen:updated')
		 
		
		if(childLevelId > 0){
		 getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue);	
		}
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,subLevelId,locationValue,"Change","Other",0);
		
		
	}); 
	
function getLocationBasedOnDepartmentLevel(departmentId,parentScopeId,districtLevelId){
	  $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).html('');
	
		var alertType = getAlertType();
		var subLevelArr =[];
		 var jsObj ={
		  fromDate:currentFromDate,
		  toDate:currentToDate,
		  stateId : 1,
		  paperIdArr : newspapersGlobalArr,
		  chanelIdArr : channelGlobalArr,
		  callCenterArr : callCenterGlobalArr,      
		  govtDepartmentId : departmentId,
		  parentGovtDepartmentScopeId : districtLevelId,
		  alertType:alertType,
		  subLevelArr:subLevelArr
		}
		$.ajax({
		type:'GET',                  
		url: 'getLocationBasedOnDepartmentLevelAction.action',
		data: {task :JSON.stringify(jsObj)}     
		}).done(function(result){
		  if(result !=null && result.length>0){
			$("#locationNamesId"+departmentId+parentScopeId+districtLevelId).append('<option value="0">SELECT '+globalLevelObj[districtLevelId]+'</option>');
			for(var i in result){
			  $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
			}
		  }
		   $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).chosen();
		   $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).trigger("chosen:updated");
		 
		});    
	}
	
	function getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue){
	$("#locationNamesId"+departmentId+levelId+childLevelId).html('');
	
	var alertType = getAlertType();
	 	var jsObj ={
			fromDate:currentFromDate,
			toDate:currentToDate,
			stateId : 1,
			paperIdArr : newspapersGlobalArr,
			chanelIdArr : channelGlobalArr,
			callCenterArr : callCenterGlobalArr,			
			govtDepartmentId : departmentId,
			parentGovtDepartmentScopeId : subLevelId,
			parentGovtDepartmentScopeValue:locationValue,
			childLevelId:childLevelId,
			alertType:alertType,
		}
		$.ajax({
		type:'GET',                  
		url: 'getChildLocationBasedOnParentLocationAction.action',
		data: {task :JSON.stringify(jsObj)}     
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#locationNamesId"+departmentId+levelId+childLevelId).append('<option value="0">SELECT '+globalLevelObj[childLevelId]+'</option>');
				for(var i in result){
					$("#locationNamesId"+departmentId+levelId+childLevelId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
			$("#locationNamesId"+departmentId+levelId+childLevelId).chosen();
			$("#locationNamesId"+departmentId+levelId+childLevelId).trigger("chosen:updated");
		});    
	}
	
function getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,locationLevelId){
  $("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
   
	var locationLevelIdClickArr=[];
	 if(locationLevelId == null || locationLevelId == 0){
			 locationLevelIdClickArr =[];
		 }else{
			  locationLevelIdClickArr.push(locationLevelId);
		 }
	var alertType = getAlertType();
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentGovtDepartmentScopeId,
		deptScopeId : levelId,    
		statusId:statusId,   
		callCenterArr:callCenterGlobalArr,
		locationValue : locationValue,
		alertType:alertType,
		alertCategoryId:alertCategoryId,
		subLevels:locationLevelIdClickArr
		
    }
    $.ajax({
    type:'GET',                        
    url: 'getAlertDetailsBasedOnLocationAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
     if(result != null && result.length > 0){
		$("#totalAlertsModalTabId").html('');
		buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
	}else{
		$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
	}
    }); 
}

function getStatusAndLocationWiseDetailsBlock(){
	var str='';
	str+='<div class="col-sm-6 applyStyleStatus">';
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading headingColor">';
				str+='<h4 class="panel-title text-capital fontColor">status overview</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default">';
					str+='<div class="" role="tab" id="headingOne" style="padding: 15px;">';
					 
						str+='<a class ="collapseIconForIAS" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
						str+=' <h4 class="panel-title"> ALERTS</h4>';
						
						str+='</a>';
					
					str+='</div>';
					str+='<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">';
					  str+='<div class="panel-body">';
						str+='<div id="statusOverview"></div>';
					  str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="panel panel-default">';
					str+='<div class="" role="tab" id="headingTwo" style="padding: 15px;">';
					
						str+='<a class="collapsed collapseIconForIAS subTaskViewDts" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">';
						 str+='<h4 class="panel-title">';
						  str+='SUB TASKS</h4>';
						str+='</a>';
					  
					str+='</div>';
					str+='<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">';
					  str+='<div class="panel-body">';
						str+='<div id="statusWiseSubTasksOverview"></div>';
					 str+=' </div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
				
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-6">';
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading headingColor">';
				str+='<h4 class="panel-title text-capital fontColor">location level overview</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="panel-group" id="accordionL" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default">';
						str+='<div class="" role="tab" id="headingOneL" style="padding: 15px;">';
						 
							str+='<a class ="collapseIconForIAS" role="button" data-toggle="collapse" data-parent="#accordionL" href="#collapseOneL" aria-expanded="true" aria-controls="collapseOneL">';
							 str+='<h4 class="panel-title"> ALERTS</h4>';
							
							str+='</a>';
						  
						str+='</div>';
						str+='<div id="collapseOneL" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOneL">';
						  str+='<div class="panel-body">';
							str+='<div id="levelWiseAlertOverview"></div>';
						 str+=' </div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="panel panel-default">';
						str+='<div class="" role="tab" id="headingTwoL" style="padding: 15px;">';
						
							str+='<a class="collapsed collapseIconForIAS subTaskLocViewDts" role="button" data-toggle="collapse" data-parent="#accordionL" href="#collapseTwoL" aria-expanded="false" aria-controls="collapseTwoL">';
							  str+='<h4 class="panel-title">';
							  str+='SUB TASKS</h4>';
							str+='</a>';
						  
						str+='</div>';
						str+='<div id="collapseTwoL" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwoL">';
						  str+='<div class="panel-body">';
							str+='<div id="levelWiseSubTasksAlertOverview"></div>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#statusAndLocationWiseDetialsDiv").html(str);
}

/*Alert Source Start */

function getAlertSourceWiseAlert()
{
	 
	$("#alertSourceWiseDetilsDivId").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr : channelGlobalArr,
	  callCenterArr : callCenterGlobalArr,
	  userType :"stateLevel"
    }
    $.ajax({
      type:'GET',
      url: 'getAlertSourceWiseAlertAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#alertSourceWiseDetilsDivId").html('');
	   if(result !=null && result.length>0){
		   buildAlertSouceWiseDetails(result);
	   }
    });
}

function buildAlertSouceWiseDetails(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-2 col-xs-12 col-sm-4">';
			str+='<div id="alertSourceWiseGraphView" style="height:270px"></div>';
			str+='<div id="alertSourceWiseTotal"></div>';
		str+='</div>';
	
		 str+='<div class="col-md-2 col-xs-12 col-sm-4" style="margin-top:30px">';
			str+='<div class="scrollerDivCls">';
				str+='<table class="table tableGraph">';
					
					str+='<tbody>';
						for(var i in result)
						{	
							totalAlert+=result[i].alertCnt;
							str+='<tr>';
								str+='<td><span class="label" style="background-color:'+globalAlertSourceColorObj[result[i].name.trim()]+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
								str+='<td style="cursor:pointer;" onclick="getAlertDtlsByAlertSource(\''+result[i].name+'\','+result[i].alertCnt+','+result[i].id+',0);" class="alertSourceCls" attr_alert_source_name="'+result[i].name+'" attr_alert_count="'+result[i].alertCnt+'" attr_source_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
								
							str+='</tr>';
						}
					str+='</tbody>';  
				str+='</table>';
			str+='</div>';
		str+='</div>'; 
		str+='<div class="col-md-8 col-xs-12 col-sm-4" >';
			str+='<div id="alertSourceWisebarGraphView" style="height:270px"></div>';
		str+='</div>';
	str+='</div>';
	$("#alertSourceWiseDetilsDivId").html(str);
	var str2='';
	var statusName = "Total"
	str2+='<h4 style="cursor:pointer;" class="text-center alertSourceCls" onclick="getAlertDtlsByAlertSource(\''+statusName+'\','+totalAlert+',0,0);" attr_alert_source_name="Total" attr_alert_count='+totalAlert+' attr_source_id="0">TOTAL '+totalAlert+'</h4>';
	//$("#alertSourceWiseTotal").html("<h4  class='text-center alertSourceCls' style='cursor:pointer;' onclick='getAlertDtlsByAlertSource(\'Total'\ ,"+totalAlert+",0);' attr_alert_source_name='Total' attr_alert_count="+totalAlert+" attr_source_id='0'>TOTAL "+totalAlert+"</h4>");
	$("#alertSourceWiseTotal").html(str2);
	var statusOverviewArrss =[];
	if(result.length > 6)
	{
		$(".scrollerDivCls").mCustomScrollbar({setHeight:'300px'});
	}
	for(var i in result)
	{
		
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = globalAlertSourceColorObj[result[i].name.trim()];
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArrss.push(obj);
	}
	
	
		$("#alertSourceWiseGraphView").highcharts({
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
					innerSize: 80,
					depth: 120,
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
				data: statusOverviewArrss
			}]
		});
	
		if(result !=null  && result.length>0){
				var mediaNamesArr=[];
				var pendingAlertArr = [];
				var notifiedAlertArr = [];
				var actionInProgessAlertArr = [];
				var completedAlertArr = [];
				var unblTRslvAlertArr = [];
				var actionNotRequiredAlertArr = [];
				var duplicateAlertArr = [];
				var WronglyMappedDesignationArr = [];
				var WronglyMappedDepartmentArr = [];
				var RejoinderArr = [];
				var Incomplete = [];
				var Closed = [];
				var Proposal = [];
				
			for(var i in result){
				
				 mediaNamesArr.push(result[i].name)
				if(result[i].subList2 !=null && result[i].subList2.length>0){
					
					for(var j in result[i].subList2){
						
						if(result[i].subList2[j].id==1){
								pendingAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id}); 
							}else if(result[i].subList2[j].id==2){
								 notifiedAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==3){
								 actionInProgessAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==4){
								 completedAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==5){
								 unblTRslvAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==6){
								 actionNotRequiredAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==7){
								 duplicateAlertArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}
							else if(result[i].subList2[j].id==8){
								 WronglyMappedDesignationArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==9){
								 WronglyMappedDepartmentArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==10){
								 RejoinderArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==11){
								 Incomplete.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==12){
								 Closed.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==13){
								 Proposal.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}
					}
				
				
				var mainMediaJosnObjArr = [];
					   if(pendingAlertArr != null && pendingAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
					  }
					   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
					  }
					  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
					  }
					  if(completedAlertArr != null && completedAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Completed',data:completedAlertArr,color:"#4d9b66"});  
					  }
					  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
					  }
					  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
					  }
					  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
						mainMediaJosnObjArr.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
					  }
					   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
						mainMediaJosnObjArr.push({name:'Wrongly Mapped Designation',data:WronglyMappedDesignationArr,color:"#FE9900"});  
					  }
					   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
						mainMediaJosnObjArr.push({name:'Wrongly Mapped Department',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
					  }
					   if(RejoinderArr != null && RejoinderArr.length > 0){
						mainMediaJosnObjArr.push({name:'Rejoinder',data:RejoinderArr,color:"#82CA9C"});  
					  } if(Incomplete != null && Incomplete.length > 0){
						mainMediaJosnObjArr.push({name:'Reopen',data:Incomplete,color:"#C9AC82"});  
					  }if(Closed != null && Closed.length > 0){
						mainMediaJosnObjArr.push({name:'Closed',data:Closed,color:"#ababab"});  
					  }if(Proposal != null && Proposal.length > 0){
						mainMediaJosnObjArr.push({name:'Proposal',data:Proposal,color:"#5a8476"});  
					  }
				}
			}
			
				$('#alertSourceWisebarGraphView').highcharts({
					chart: {
						type: 'bar',
						backgroundColor:'transparent'
					
					},
					title: {
						text: null
					},
					subtitle: {
						text: null
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: mediaNamesArr
					},
					yAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
						title: {
							text: null
						},
						labels: {
							enabled:false
						},
						stackLabels: {
							//useHTML: true,
							//align: 'left',
							enabled: true,
							style: {
								fontWeight: 'bold',
								color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
							},
							formatter: function() {
							
								//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
								//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
								return (this.total);
							} 
						
						}
					
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
					plotOptions: {
						bar: {
							stacking: 'normal',
							pointWidth: 30,
							gridLineWidth: 15
						},series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var alertCategoryId=value[3]; 
										
										getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,statusId);
										
									}
								}
							}
				        }
					},
					legend: {
						verticalAlign:'top',
						enabled: true
					},
					series: mainMediaJosnObjArr
				});
		
			
			
				 $.each($('#alertSourceWisebarGraphView').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;"); 
						$(this).attr("onclick","getAlertDtlsByAlertSource(\'"+result[index].name+"\',\'"+result[index].alertCnt+"\',\'"+result[index].id+"\',0)");
					
				
				});
		}
}
function getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,alertStatusId)
{$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr : channelGlobalArr,
	  callCenterArr : callCenterGlobalArr,
	  alertCategoryId:alertCategoryId,
	  userType :"stateLevel",
	   alertStatusId:alertStatusId
    }
    $.ajax({
      type:'POST',
      url: 'getAlertDtlsByAlertSourceAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
	    if(result != null && result.length > 0){
		$("#totalAlertsModalTabId").html('');
		buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
	}else{
		$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
	}
    });
}
/*Alert Source End*/