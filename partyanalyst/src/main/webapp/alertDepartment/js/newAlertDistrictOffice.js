var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
onLoadCallsAMU();
	
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
		
		getDistrictOfficerAlertsCountView();
	});
	
	function onLoadCallsAMU(){
		getDistrictOfficerAlertsCountView();
	}
	function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip)
	{
		'use strict';
		
		$('#'+id).highcharts({
			chart: type,
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: xAxis,
			yAxis: yAxis,
			tooltip: tooltip,
			plotOptions: plotOptions,
			legend: legend,
			series: data
		});
	}
	
	
	function getDistrictOfficerAlertsCountView(){
	$("#myAlertsDivID").html(spinner);
	$("#mySubTasksDivID").html(spinner);
	$("#assignedSubTasksDivID").html(spinner);
	var fromDateStr= "";
	var toDateStr= "";
	//var userId=19601;
    var jObj ={
      //userId:userId,
	  startDate:currentFromDate,
	  endDate:currentToDate
    }
    $.ajax({
      type:'GET',
      url: 'getDistrictOfficerAlertsCountViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#myAlertsDivID").html('');
		$("#mySubTasksDivID").html('');
		$("#assignedSubTasksDivID").html('');
		buildDistrictOfficerAlertsCountView(result);
    });
}
var overAllAlertIds =[];
var totalCoutAlertIds =[];
var userLevelId;
var userLevelValues = [];
function buildDistrictOfficerAlertsCountView(result){
	var str='';
	if(result !=null){
		userLevelId = result.levelId;
		if(result.levelValues != null && result.levelValues.length > 0)
			userLevelValues=result.levelValues;
	}
		if(result !=null && result.list1 !=null && result.list1.length>0){
		str+='<div class="row">';
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0){
				if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0 && esult.list1[0].todayCount>0){
					totalCoutAlertIds.push(result.list1[0].todayAlertIds);
				str+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list1[0].todayCount+'">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
				}
			else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list1[0].todayCount+'</span></p>';
			}
			}else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str+='<hr class="m_0"/>';
			if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0){
				if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0 && result.list1[0].overAllCnt>0){
					overAllAlertIds.push(result.list1[0].overAllAlertIds);
				str+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list1[0].overAllCnt+'">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
				}
			  else{
				  str+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list1[0].overAllCnt+'</span></p>';
			  }
			}else{
				str+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str+='<div id="myAlertGraphView" style="height:250px"></div>';
			str+='</div>';
		str+='</div>';
	}
	$("#myAlertsDivID").html(str);
	
	var mainStatusArr=[];
	var count = [];
	var percArr = [];
	if(result !=null && result.list1 !=null && result.list1.length>0){
		for(var i in result.list1){
			var statusArr=[];
			var statusNamesArr=[];
			var uniqCnt = {};
			var color;
			var totalAlertCnt = result.list1[0].overAllCnt;
			statusArr.push(result.list1[i].count);
			statusNamesArr.push(result.list1[i].name);
			percArr.push(result.list1[i].perc);
			color=result.list1[i].color;
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].count),color:"#D3D3D3"};
			count.push(uniqCnt);
			var obj1={
				data: count    
			}
			var obj={
						name: 'No.of Alerts',
						data: statusArr,
						color: color
					}
			
			mainStatusArr.push(obj1);
			mainStatusArr.push(obj);
			
		}
	}
	
		var data = mainStatusArr
			var id = 'myAlertGraphView';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
				
			}
			var legend = {
				enabled: false,
			}
			var yAxis = {
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
					alertPerc: percArr,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
					} 
					
				}
				
			}
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArr
			}
			var plotOptions =  {
	             series: {
					stacking: 'normal',
					pointWidth: 30,
					gridLineWidth: 15
				}
	        }
			var tooltip = {
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
			};
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip); 

			
		var str1='';
		if(result !=null && result.list2 !=null && result.list2.length>0){
		str1+='<div class="row">';
			str1+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0){
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0 && result.list2[0].todayCount>0){
				  totalCoutAlertIds.push(result.list2[0].todayAlertIds);
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' >TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str1+='<hr class="m_0"/>';
			if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0){
			 if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0 && result.list2[0].overAllCnt>0){
				overAllAlertIds.push(result.list2[0].overAllAlertIds);
				str1+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+'>OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }else{
				 str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }
			}else{
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str1+='<div id="mySubTasksGraphView" style="height:250px"></div>';
			str1+='</div>';
		str1+='</div>';
	}
	$("#mySubTasksDivID").html(str1);
	
	var mainStatusArrST=[];
	var countST = [];
	var percArrST = [];
	if(result !=null && result.list2 !=null && result.list2.length>0){
		for(var i in result.list2){
			var statusArrST=[];
			var statusNamesArrST=[];
			var colorST;
			var uniqCnt = {};
			var totalAlertCnt = result.list2[0].overAllCnt;
			statusArrST.push(result.list2[i].count);
			statusNamesArrST.push(result.list2[i].name);
			percArrST.push(result.list2[i].perc);
			colorST=result.list2[i].color;
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list2[i].count),color:"#D3D3D3"};
			countST.push(uniqCnt);
			var obj1={
				data: countST    
			}
			var obj={
						name: 'No.of Alerts',
						data: statusArrST,
						color: colorST
					}
			
			mainStatusArrST.push(obj1);
			mainStatusArrST.push(obj);
			
		}
	}
	
		var dataST = mainStatusArrST
			var idST = 'mySubTasksGraphView';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
				
			}
			var legend = {
				enabled: false,
			}
			var yAxisST = {
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
					alertPerc: percArrST,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
					} 
					
				}
				
			}
			var xAxisST = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArrST
			}
			
			
			highcharts(idST,type,xAxisST,yAxisST,legend,dataST,plotOptions,tooltip); 
			
			var str2='';
		if(result !=null && result.list3 !=null && result.list3.length>0){
		str2+='<div class="row">';
			str2+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0){
				if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0 && esult.list3[0].todayCount>0){
					totalCoutAlertIds.push(result.list3[0].todayAlertIds);
				str2+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' >TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
				}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str2+='<hr class="m_0"/>';
			if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0){
				if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0 && result.list3[0].overAllCnt>0){
					overAllAlertIds.push(result.list3[0].overAllAlertIds);
				str2+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+'>OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str2+='<div id="assignedSubTasksGraphView" style="height:250px"></div>';
			str2+='</div>';
		str2+='</div>';
	}
	$("#assignedSubTasksDivID").html(str2);
	
	var mainStatusArrAST=[];
	var countAST = [];
	var percArrAST = [];
	if(result !=null && result.list3 !=null && result.list3.length>0){
		for(var i in result.list3){
			var statusArrAST=[];
			var statusNamesArrAST=[];
			var colorAST;
			var uniqCnt = {};
			var totalAlertCnt = result.list3[0].overAllCnt;
			statusArrAST.push(result.list3[i].count);
			statusNamesArrAST.push(result.list3[i].name);
			percArrAST.push(result.list3[i].perc);
			colorAST = result.list3[i].color;
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list3[i].count),color:"#D3D3D3"};
			countAST.push(uniqCnt);
			var obj1={
				data: countAST    
			}
			var obj={
						name: 'No.of Alerts',
						data: statusArrAST,
						color: colorAST
					}
			
			mainStatusArrAST.push(obj1);
			mainStatusArrAST.push(obj);
			
		}
	}
	
		var dataAST = mainStatusArrAST
			var idAST = 'assignedSubTasksGraphView';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
				
			}
			var legend = {
				enabled: false,
			}
			var yAxisAST = {
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
					alertPerc: percArrAST,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
					} 
					
				}
				
			}
			var xAxisAST = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArrAST
			}
			
			
			highcharts(idAST,type,xAxisAST,yAxisAST,legend,dataAST,plotOptions,tooltip); 
}

//getSubOrdinateAlertsOverview();
function getSubOrdinateAlertsOverview(){
	//var userId="";
	var fromDateStr= "";
	var toDateStr= "";
	var govtScopeIds=[];
	var locationValues=[];
	var desigIds=[];
	var priorityId="";
	
	var jObj = {
	fromDateStr : currentFromDate, 
	toDateStr : currentToDate,
	govtScopeIds : govtScopeIds,
	locationValues : locationValues,
	desigIds : desigIds,
	priorityId : priorityId
	}
	$.ajax({
	      type:'GET',
	      url: 'getSubOrdinateAlertsOverviewAction.action',
		  data: {task :JSON.stringify(jObj)}
	    }).done(function(result){
		});
 }
//getSubOrdinateLevel();
function getSubOrdinateLevel(){
	var designationId =0;
	var jObj = {
		designationId : designationId
	}
	$.ajax({
      type:'GET',
      url: 'getSubOrdinateLevelAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
	});
	
}


function viewAlertHistory()
{
	$("#alertManagementPopupBody1").html(spinner)
	var jsObj ={
		alertId : 11346
	}
	$.ajax({
		type:'GET',
		url: 'viewAlertHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			alertHistory(result);
		}else{
			$("#alertManagementPopupBody1").html("NO DATA AVAILABLE")
		}
	});
}

$(document).on("click",".overAllCount",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	alertIdArr.push(parseInt($(this).attr("attr_overCunt")));
	var jObj = {
		alertIdArr: alertIdArr		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelOfficerClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
});
$(document).on("click",".todayCountCls",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	alertIdArr.push(parseInt($(this).attr("attr_todayCunt")));
	var jObj = {
		alertIdArr: alertIdArr		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelOfficerClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
});
getDistrictOfficerGraphicalRepresentation(5);
getDistrictOfficerGraphicalRepresentation(6);
getDistrictOfficerGraphicalRepresentation(7);
function getDistrictOfficerGraphicalRepresentation(parentScopeId){
	
		//var userId="";
		var fromDateStr= "";
		var toDateStr= "";
		var locationValues=[];
		var desigIds=[];
		var priorityId=0;
		var paperIdArr =[];
		var chanelIdArr =[];
		var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		parentGovtDepartmentScopeId:parentScopeId,
		govtDepartmentId:49,
		sortType:"count",
		order:"asc",
		searchType:"scopes",//locationWise:scopes,StatusOverview:status
		alertType:"alert"
		}
		$.ajax({
		      type:'GET',
		      url: 'getStateThenGovtDeptScopeWiseAlertCountStatusAction.action',
			  data: {task :JSON.stringify(jObj)}
		    }).done(function(result){
			});
	
}
//getDistrictLevelDeptWiseFlterClick();
function getDistrictLevelDeptWiseFlterClick()
{
	
	var jsObj = {
		deptId : 49,
		levelId : 5,
		statusId : 7
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		//buildAssignedOfficersDetailsForAlert(result);
	});
}
//getDistrictLevelWiseClick();
function getDistrictLevelWiseClick()
{
	var levelValues = [];
	  //levelValues.push(7);
	var fromDateStr= "";
	var toDateStr= "";
	var jsObj = {
		stateId : 1,
		fromDate : currentFromDate,
		toDate : currentToDate,
		govtDepartmentId : 49,
		parentGovtDepartmentScopeId : 5,
		govtDeptWorkLocId : 8,
		statusId : 0,
		childGovtScopeId : 8,
		status : "alert",
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelWiseClickAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		
	});
}


