 var globalLevelObj =  {"1":"STATE","2":"ZONE","3":"REGION","4":"CIRCLE","5":"DISTRICT","6":"DIVISION","7":"SUB DIVISION","8":"MANDAL","9":"MUNICIPALITY","10":"PANCHAYAT"};
 var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");



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
		
		onLoadCallsAMU();
	});
	
	
	function onLoadCallsAMU(){
			var newsPaperIdLen = newspapersGlobalArr.length;
			var channelIdLen = channelGlobalArr.length;
			var callCenterIdLen = callCenterGlobalArr.length;
			if(newsPaperIdLen == 0 && channelIdLen == 0 && callCenterIdLen == 0){
				alert("Please Select Atleast One Option.");   
				return;
			}
		
			$(".documentCloseClass").hide();
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
	
	//var userId=19601;
    var jObj ={
      //userId:userId,
	  startDate:currentFromDate,
	  endDate:currentToDate,
	  paperIdArr:newspapersGlobalArr,
	  chanelIdArr:channelGlobalArr,
	  callCenterArr:callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'getDistrictOfficerAlertsCountViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#myAlertsDivID").html('');
		$("#mySubTasksDivID").html('');
		$("#assignedSubTasksDivID").html('');
		if(result !=null){
			globalDepartmentId = result.departmentId;
			globalDepartmentName = result.deptName;
			globalgovtDeptDesigOffcrId = result.govtDeptDesigOffcrIds[0];
			globalgovtOfficerId = result.govtOfficerIds[0];
			globalDesignationId = result.designationId;
			globalUserLevelId = result.levelId;
			if(result.levelValues != null && result.levelValues.length > 0)
				globalUserLevelValues=result.levelValues;
			
			getDepartmentDetailsByDepartment(result.departmentId,result.deptName);
		
		}
		buildDistrictOfficerAlertsCountView(result);
		//getDepartmentLevels1();
    });
}



function buildDistrictOfficerAlertsCountView(result){
	
	
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0){
				if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0 && result.list1[0].todayCount>0){
					totalCoutAlertIds.push(result.list1[0].todayAlertIds);
				str+='<p class="pad_5 todayCountCls" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count="'+result.list1[0].todayCount+'" attr_alert_type="alert">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
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
				str+='<p class="pad_5 overAllCount" attr_overCunt="'+overAllAlertIds+'" attr_name="OVERALL" attr_total_count="'+result.list1[0].overAllCnt+'" attr_alert_type="alert">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
				}
			  else{
				  str+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list1[0].overAllCnt+'</span></p>';
			  }
			}else{
				str+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str+='<div class="scrollerAlert">';
				str+='<div id="myAlertGraphView"></div>';
			str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#myAlertsDivID").html(str);
	}else{
		$("#myAlertsDivID").html("No Data Available");
	}
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var mainArrTempAT=[];
		var namesArrAT=[];
		var countAT = [];
		
		for(var i in result.list1){
			
			var uniqCnt = {};
			
			var totalAlertCnt = result.list1[0].overAllCnt;
			namesArrAT.push(result.list1[i].name);
			var tempArrAT = {"y":result.list1[i].count,color:result.list1[i].color,"extra":result.list1[i].id+"-"+result.list1[i].name+"-"+result.list1[i].count};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].count),color:"#D3D3D3","extra":result.list1[i].id+"-"+result.list1[i].name+"-"+result.list1[i].count};
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
										var stateImpactInfo = (this.extra).split("-");
										 var StatusId = stateImpactInfo[0];
										var StatusName = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										
										 if(totalAlertCnt == 0){
											return;  
										 }
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"alert");
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
		}else{
			 $('#myAlertGraphView').html("No Data Available")
		}

			
		
		if(result !=null && result.list2 !=null && result.list2.length>0){
			var str1='';
		str1+='<div class="row">';
			str1+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0){
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0 && result.list2[0].todayCount>0){
				  totalCoutAlertIds.push(result.list2[0].todayAlertIds);
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count= "'+result.list2[0].todayCount+'" attr_alert_type="mySubTasks">TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].todayCount+'</span></p>';
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str1+='<hr class="m_0"/>';
			if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0){
				overAllAlertIds = [];
			 if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0 && result.list2[0].overAllCnt>0){
				overAllAlertIds.push(result.list2[0].overAllAlertIds);
				str1+='<p class="pad_5 overAllCount" attr_overCunt="'+overAllAlertIds+'" attr_name ="OVERALL" attr_total_count = "'+result.list2[0].overAllCnt+'" attr_alert_type="mySubTasks">OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].overAllCnt+'</span></p>';
			 }else{
				 str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }
			}else{
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str+='<div class="scrollerSubTask">';
				str1+='<div id="mySubTasksGraphView" ></div>';
			str1+='</div>';
			str1+='</div>';
		str1+='</div>';
		$("#mySubTasksDivID").html(str1);
	}else{
		$("#mySubTasksDivID").html("No Data Available");
	}
	
	
if(result !=null && result.list2 !=null && result.list2.length>0){
		var mainArrTempST=[];
		var namesArrST=[];
		var countST = [];
		
		for(var i in result.list2){
			
			var uniqCnt = {};
			
			var totalAlertCnt = result.list2[0].overAllCnt;
			namesArrST.push(result.list2[i].name);
			var tempArrST = {"y":result.list2[i].count,color:result.list2[i].color,"extra":result.list2[i].id+"-"+result.list2[i].name+"-"+result.list2[i].count};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list2[i].count),color:"#D3D3D3","extra":result.list2[i].id+"-"+result.list2[i].name+"-"+result.list2[i].count};
			countST.push(uniqCnt);
			
			mainArrTempST.push(tempArrST);
		}
	
	if(namesArrST.length > 4)
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
				categories: namesArrST,
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
										var stateImpactInfo = (this.extra).split("-");
										 var StatusId = stateImpactInfo[0];
										var StatusName = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										
										 if(totalAlertCnt == 0){
											return;  
										 }
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"mySubTasks");
									}
								}
							}
				        }
				
				},
			series: [{
				
				data: countST,
					
			},
			{
				name: "Number of alerts",
				 data: mainArrTempST,
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
			
		
		if(result !=null && result.list3 !=null && result.list3.length>0){
				var str2='';
		str2+='<div class="row">';
			str2+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0){
				if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0 && result.list3[0].todayCount>0){
					totalCoutAlertIds.push(result.list3[0].todayAlertIds);
				str2+='<p class="pad_5 todayCountCls" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count= "'+result.list3[0].todayCount+'" attr_alert_type="myAssignedSubTasks" >TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list3[0].todayCount+'</span></p>';
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
				str2+='<p class="pad_5 overAllCount" attr_overCunt="'+overAllAlertIds+'" attr_name="OVERALL" attr_total_count="'+result.list3[0].overAllCnt+'" attr_alert_type="myAssignedSubTasks" >OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list3[0].overAllCnt+'</span></p>';
				}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str2+='<div class="scrollerAssSubTask">';
				str2+='<div id="assignedSubTasksGraphView" ></div>';
				str2+='</div>';
			str2+='</div>';
		str2+='</div>';
		$("#assignedSubTasksDivID").html(str2);
	}else{
		$("#assignedSubTasksDivID").html("No Data Available");
	}
	
	
	if(result !=null && result.list3 !=null && result.list3.length>0){
		var mainArrTemp=[];
		var namesArr=[];
		var countAST = [];
		
		for(var i in result.list3){
			
			var uniqCnt = {};
			var totalAlertCnt = result.list3[0].overAllCnt;
			namesArr.push(result.list3[i].name);
			var tempArr = {"y":result.list3[i].count,color:result.list3[i].color,"extra":result.list3[i].id+"-"+result.list3[i].name+"-"+result.list3[i].count};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list3[i].count),color:"#D3D3D3","extra":result.list3[i].id+"-"+result.list3[i].name+"-"+result.list3[i].count};
			countAST.push(uniqCnt);
			
			mainArrTemp.push(tempArr);
		}
	
	if(namesArr.length > 4)
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
				categories: namesArr,
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
										var stateImpactInfo = (this.extra).split("-");
										 var StatusId = stateImpactInfo[0];
										var StatusName = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										
										 if(totalAlertCnt == 0){
											return;  
										 }
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"myAssignedSubTasks");
									}
								}
							}
				        }
				
				},
			series: [{
				
				data: countAST,
					
			},
			{
				name: "Number of alerts",
				 data: mainArrTemp,
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
			$('#assignedSubTasksGraphView').html("No Data Available")
		}
		
		var maxHeight = 0;

		$(".panelheights").each(function(){
		   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
		});
		$(".panelheights").height(maxHeight);
}
	
	
	

$(document).on("click",".overAllCount",function(){
	$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
	
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count");
	var alertType = $(this).attr("attr_alert_type");
	//alertIdArr=($(this).attr("attr_overcunt").split(','));
	var govtDepDesigOffcrIds = [globalgovtDeptDesigOffcrId];
	var govtOfficerIds = [globalgovtOfficerId];
	var jObj = {
		govtDepDesigOffcrIds : govtDepDesigOffcrIds,
		govtOfficerIds : govtOfficerIds,
		countType: "overAll",
		alertType : alertType,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		callCenterArr:callCenterGlobalArr,
		fromDate:currentFromDate,
		toDate:currentToDate		
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
	$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	var alertType = $(this).attr("attr_alert_type");
	var govtDepDesigOffcrIds = [globalgovtDeptDesigOffcrId];
	var govtOfficerIds = [globalgovtOfficerId];
	var jObj = {
		govtDepDesigOffcrIds : govtDepDesigOffcrIds,
		govtOfficerIds : govtOfficerIds,
		countType: "today",
		alertType : alertType,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		callCenterArr:callCenterGlobalArr,
		fromDate:currentFromDate,
		toDate:currentToDate
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

$(document).on("click","#filterViewId",function(){
	$("#statusOvrvwId").hide();
	$("#filterViewBodyId").show();
	filerterViewBody()
	assignUser1();
	getDepartmentWiseLevelsDistrictOfficer();
	getStatus();
});

function getDepartmentDetailsByDepartment(departmentId,departmentName){ 
$("#departmentWiseLocationBlockId").html(spinner);
   var jsObj ={
		departmentId:departmentId,
		designationType:"levelWiseOfficer"
    }
    $.ajax({
    type:'GET',                        
    url: 'getDepartmentDetailsByDepartmentAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		$("#departmentWiseLocationBlockId").html('');
       buildDepartmentDetailsByDepartmentss(result,departmentId,departmentName)
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
							str+='<h4 class="panel-title text-capital fontColor">SUBORDINATE - ALERTS OVERVIEW ('+departmentName+')</h4>';
						str+='</div>';
						str+='<div class="col-md-6 col-xs-12 col-sm-4">';
							str+='<ul class="switch-btn pull-right">';
								str+='<li class="active" attr_type="statuswise" attr_department_id="'+result[i].id+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">status overview</li>';
								str+='<li attr_type="scopewise"  attr_department_id="'+result[i].id+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">location level</li>';
								str+='<li attr_type="alertSource"  attr_department_id="'+result[i].id+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Alert Source</li>';
								str+='<li id="filterViewId">Filter</li>';
								str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-4 ">';
							str+='<ul class="switch-btn-alertType pull-right">';
								str+='<li  attr_type="alert" class="active" attr_department_id="'+result[i].id+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Alerts</li>';
								str+='<li attr_type="subTask" attr_department_id="'+result[i].id+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Sub Tasks</li>';
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
											str+='<li class="active sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
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
		filerterViewBody()
		
		   
		var deptObj = result[0];
		 if(deptObj.subList1 != null && deptObj.subList1.length > 0){
			 for(var i in deptObj.subList1){
				getStateThenGovtDeptScopeWiseAlertCount(deptObj.id,deptObj.subList1[i].id,"statuswise","alert","levelWiseGraphView","count","desc",0,0,"Default");
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
		var parentIdStr = $(this).attr("attr_level_idstr").split(',');
		var alertType =  $(this).attr("attr_type");
		var searchType = getsearchType();
		
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var sortingType='';
		var orderType='';
		var districtLevelId = $(this).attr("attr_district_level_id");
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		$('.sortingCls'+departmentId+parentIdStr).each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType = $(this).attr("attr_order_type");
			 }
		});
		if($("#filterViewId").hasClass("active") == true)
		{
			filerterViewBody()
			assignUser1();
			getDepartmentWiseLevelsDistrictOfficer();
			getStatus();
			
		}
		for(var i in parentIdStr){
				for(var j in subLevelIdStr){
				    $("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).html('');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[j]]+'</option>');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).trigger('chosen:updated');
			}
		}
		
		for(var i in parentIdStr){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentIdStr[i],searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default");
			getLocationBasedOnDepartmentLevel(departmentId,parentIdStr[i],districtLevelId);
			
		}
		
		
		
		
});
	
$(document).on("click",".switch-btn li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		//$("#filterMainDiv").show();
		//assignUser1();
		var departmentId = $(this).attr("attr_department_id");
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
		if(searchType == 'statuswise' || searchType == 'scopewise')
		{
			$("#statusOvrvwId").show();
			$("#filterViewBodyId").hide();
		}
		for(var i in parentIdStr){
				for(var j in subLevelIdStr){
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).html('');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[j]]+'</option>');
					$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).trigger('chosen:updated');
			}
		}
		
		
		for(var i in parentIdStr){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentIdStr[i],searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default");
			getLocationBasedOnDepartmentLevel(departmentId,parentIdStr[i],districtLevelId);
			
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
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).trigger('chosen:updated');
		}
		
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default");
		getLocationBasedOnDepartmentLevel(departmentId,parentId,districtLevelId);
});
function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,alertType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType){
	$("#"+divId+departmentId+parentGovtDepartmentScopeId).html(spinner);
	/* var group='status';

	if(searchType == "scopewise" && parentGovtDepartmentScopeId == 1){
		group = "overview";
	} */
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
		subLevels:subLevels,
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
		buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType);
	});
}

function buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType){
	
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
										
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId)
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
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',\'"+result[index].id+"\',0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].stateId+"\',\'"+parentGovtDepartmentScopeId+"\',0)");	
					}else{
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0)");
					}
				
				});
				
		}else{
			if(actionType=="Default"){
			   $("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();//Scope Level DivId hiding if data is not available
			}else{
		       $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12">No Data Available</div>');
			   $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style')
			   $("#"+divId+departmentId+parentGovtDepartmentScopeId).css('height',"25px;");
			}
		}
	}
	else if(searchType == "scopewise" && parentGovtDepartmentScopeId != 1){
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
										var alertCategoryId = 0;
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId)
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
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',1,\'"+parentGovtDepartmentScopeId+"\',0)");	
					}else{
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0)");
					}
					
				});
		}else{
			if(actionType=="Default"){
			  $("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();//Scope Level DivId hiding if data is not available
			}else{
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			  $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","25px"); 
			}
			//$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();
			/* $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","25px"); */
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
										var alertCategoryId = 0;
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId)
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
				$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',\'"+result[0].subList[index].id+"\',0,\'"+result[0].subList[index].name+"\',\'"+result[0].subList[index].count+"\',\'"+result[0].id+"\',\'"+parentGovtDepartmentScopeId+"\',0)");
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
		
		if(childLevelId > 0){
		 getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue);	
		}
		
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,subLevelId,locationValue,"Change");
		
		
	}); 
	
function getLocationBasedOnDepartmentLevel(departmentId,parentScopeId,districtLevelId){
	  $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).html('');
	  	
		var alertType = getAlertType();
		
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
	
function getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId){
  $("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
       var alertType = getAlertType();
      var subLevels = [];
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
		subLevels:subLevels
		
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
function getDistrictLevelDeptWiseAlertClick(StatusId,name,totalCount,clickType)
{
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
	   var govtDepDesigOffcrIds = [globalgovtDeptDesigOffcrId];
	   var govtOfficerIds = [globalgovtOfficerId];
		var jsObj = {
		govtDepDesigOffcrIds : govtDepDesigOffcrIds,
		govtOfficerIds : govtOfficerIds,
		statusId : StatusId,
		formDate:currentFromDate, 
		toDate: currentToDate,
		clickType:clickType, //
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseAlertClickAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,name,totalCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
}

function assignUser1()
{
	var str='';
	str+='<div id="filterMainDiv">';
		str+='<div class="row" >';
		
				str+='<div id="filterDivId">';  
					str+='<div class="col-sm-12">';
						str+='<div id="assignErrorDivId" class="text-danger"></div>';
					str+='</div>';
					 str+='<div class="col-sm-3 m_top10">';
						str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
						str+='<select  class="chosenSelect" id="departmentSelectedId">';
							str+='<option value="0">Select Department</option>';
							str+='<option value="'+globalDepartmentId+'" selected>'+globalDepartmentName+'</option>';
						str+='</select>';
					str+='</div>'; 
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
						str+='<select  class="chosenSelect" id="locationLevelSelectedId" name="alertAssigningVO.levelId">	';
						str+='</select>';
					str+='</div>';
					str+='<div id="parentLevelDivId"> </div>';
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Status<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgStusId"></span></label>';
						str+='<select  class="chosenSelect" id="statusSelectedId">';
							str+='<option value="0">All</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>';
						str+='<select  id="designationsWiseId" class="chosenSelect">';
							str+='<option value="0">All</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Priority<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
						str+='<select class="chosenSelect" id="PriorityId" >	';
							//str+='<option value="0">Select Priority</option>';
							str+='<option value="0" selected>All</option>';
							str+='<option value="1">High</option>';
							str+='<option value="2">Medium</option>';
							str+='<option value="3">Low</option>';
						str+='</select>';
					str+='</div>';
					/* str+='<div class="col-sm-3 m_top10">';
						str+='<label>Date<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDateId"></span></label>';
						str+='<div class="input-group">';
							str+='<span class="input-group-addon">';
								str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='</span>';
							str+='<input type="text" class="form-control" id="dateRangeId"/>';
						str+='</div>';
					str+='</div>'; */
				str+='</div>';
				
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-9 m_top20">';
					str+='<label class="checkbox-inline"><input type="checkbox" name="lagDays" id="lagDaysId" checked/>Lag Days</label>';
					str+='<div id="tourSlider"></div>';
					str+='<label class="checkbox-inline pull-right"><input type="checkbox" name="MoreDays" id="moreDaysId"/>More than 365 Days</label>';
				str+='</div>';
				str+='<div class="col-sm-3" style="margin-top: 40px;">';
					str+='<button class="btn btn-primary btn-sm text-capital subOrdinateFilterAlertsCls"  type="button">get Details</button>';
				str+='</div>';
			str+='</div>';
				
			/* 
			str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
			str+='<button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">getDeatils</button>';
			str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">';
			str+='<span class="text-success" id="assignSuccess"></span>'; */
		
		str+='</div>';
	str+='</div>';
	$("#assignedUser1").html(str);
	$(".chosenSelect").chosen({width:'100%'});
	$("#dateRangeId").daterangepicker({
		singleDatePicker : true
	});
	$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 0, max: 365},defaultValues:{min: 0, max: 180}});
}
$(document).on("click","#lagDaysId",function(){
	if($("#lagDaysId").is(':checked') == true)
	{
		$("#tourSlider").rangeSlider({
			bounds:{min: 0, max: 365},
			defaultValues:{min: 0, max: 180}
		});
		$("#moreDaysId").closest("label").show();
		$("#moreDaysId").closest("label").show();
	}else{
		$("#tourSlider").rangeSlider({
			bounds: {min: 0, max: 1}
		});
		$("#moreDaysId").closest("label").hide();
	}
});
$(document).on("click","#moreDaysId",function(){
	if($("#moreDaysId").is(':checked') == false)
	{
		$("#tourSlider").rangeSlider({
			bounds:{min: 0, max: 365},
			defaultValues:{min: 0, max: 180}
		});
	}else{
		$("#tourSlider").rangeSlider({
			bounds: {min: 0, max: 1}
		});
	}
});


function getDepartmentWiseLevelsDistrictOfficer(){
	
	$("#locationLevelSelectedId").html('');
	var jsObj = {
		departmentId : globalDepartmentId
	}
	$.ajax({
      type:'GET',
      url: 'getlevlsForDepartmntAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentWiseLevelsDistrictOfficer(result);
		}
	});
	
}
function buildDepartmentWiseLevelsDistrictOfficer(result){
	
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}	
	$("#locationLevelSelectedId").html(str);
	$("#locationLevelSelectedId").trigger("chosen:updated");
		
}
$(document).on('change', '#locationLevelSelectedId', function(){
	
	getChildLevelValuesForSubTask1($(this).attr('id'));
	$("#designationsWiseId").html("<option value='0'>All</option>")
	$("#designationsWiseId").trigger('chosen:updated');
});

function getChildLevelValuesForSubTask1(buildId){
	
	var levelId = $("#"+buildId+"").val();
	
	var jsObj = {
		departmentId : globalDepartmentId,
		levelId : levelId,
		parentLevelId : globalUserLevelId,
		parentLevelValueArr : globalUserLevelValues
		
	}
	$.ajax({
      type:'GET',
      url: 'getChildLevelValuesForSubTaskAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildChildLevelValuesForSubTask1(result,buildId);
			
		}
			
	});
}
 
function buildChildLevelValuesForSubTask1(result,buildId){
	
	var buildId="parentLevelDivId";
	if(result !=null && result.length>0){
	var str='';		
		for(var i in result){
			if(i<result.length-1){
				str+='<div class="col-sm-3 m_top10">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect dynamicSelectList childValCls" attr_dyna_name="'+result[i].name+'" id="locationSubLevelSelectedId'+result[i].id+'" onchange="getGovtSubLevelInfo1('+globalDepartmentId+','+result[i].id+')"  attr_dynamic_levelid="'+result[i].id+'"><option value="">All</option></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-3 m_top10">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select class="chosenSelect locationCls1 childValCls" id="locationSubLevelSelectedId'+result[i].id+'" attr_dynamic_levelid="'+result[i].id+'"><option value="">All</option></select>';
				str+='</div>';
			}
		}
		
		$("#"+buildId+"").html(str);
	}else{
		$("#"+buildId+"").html('');
		$("#"+buildId+"").trigger('chosen:updated');
	}
	$(".chosenSelect").chosen();
	if(result !=null && result.length>0){
	for(var i in result){
		var levelId = result[i].id;
		if(result[i].idnameList !=null && result[i].idnameList.length>0){
			var newStr='';
			newStr+='<option value="0">All</option>';
			//newStr+='<option value="0">Select '+result[i].name+'</option>';
			for(var j in result[i].idnameList){
				 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
			}			
			$("#locationSubLevelSelectedId"+levelId+"").html(newStr);
			$("#locationSubLevelSelectedId"+levelId+"").trigger("chosen:updated");
		}else{
			$("#locationSubLevelSelectedId"+levelId+"").html('');
			$("#locationSubLevelSelectedId"+levelId+"").append('<option value="0">All</option>');
			$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
		}
	}
	}
	
}

$(document).on('change','.locationCls1', function(evt, params) {
		designationsByDepartments();
		
});
$(document).on("change","#statusSelectedId",function(){
	designationsByDepartments();
});
function getStatus(){
	$("#statusSelectedId").html('');
	var type = $(".switch-btn-alertType li.active").attr("attr_type")
	if(type == 'alert')
	{
		type = 'alerts'
	}
	var jsObj = {
		type : type //alerts or subTask
	}
	$.ajax({
      type:'GET',
      url: 'getStatusByTypeAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildStatusForLevls(result);
		}
	});
}
function buildStatusForLevls(result){
	var str='';	
	//str+='<option value="0">Select Status</option>';
	str+='<option value="0">All</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}	
	$("#statusSelectedId").html(str);
	$("#statusSelectedId").trigger("chosen:updated");
}

function getGovtSubLevelInfo1(departmentId,levelId){
	$("#designationsWiseId").html("<option value='0'>All</option>");
	$("#designationsWiseId").trigger("chosen:updated");	
	
	//$("#locationSubLevelSelectedId"+levelId+"").addClass("hasActive");
	/* var locaionLevelValues=$("#locationSubLevelSelectedId"+levelId+"").val();	
	
	if(locaionLevelValues == "" || locaionLevelValues == 0){
		if($(".dynamicSelectList").hasClass("hasActive")){
			
		alert($(".dynamicSelectList").closest("#parentLevelDivId").find('div').html());
		}
		
	
	} */
	
	 var locaionLevelValues=$("#locationSubLevelSelectedId"+levelId+"").val();
	var jsObj = {
		departmentId : departmentId,
		levelId :levelId,
		levelValue:locaionLevelValues
	}
	$.ajax({
      type:'GET',
      url: 'getGovtSubLevelInfoAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildGovtSubLevelInfoAction1(result,levelId);
		}
			
	});
}
function buildGovtSubLevelInfoAction1(result,levelId){
		if(result !=null && result.idnameList !=null && result.idnameList.length>0){
			var str='';
			str+='<option value="0">All</option>';
			//str+='<option value="0">Select '+result.name+'</option>';
			for(var i in result.idnameList){
				str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
			}
			$("#locationSubLevelSelectedId"+result.id+"").html(str);
			$("#locationSubLevelSelectedId"+result.id+"").trigger("chosen:updated");
			
		}else{
			var impactLevel = $("#locationLevelSelectedId").val();
			if(impactLevel == 5){
				if(levelId == 5)
				{
					$("#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}
			}else if(impactLevel == 6){
				if(levelId == 5)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}else if(levelId == 6)
				{
					$("#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}
				alert(2)
			}else if(impactLevel == 7){
				if(levelId == 5)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
				}else if(levelId == 6)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}
				alert(3)
			}else if(impactLevel == 8){
				alert(4)
				if(levelId == 5)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
				}else if(levelId == 6)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
				}else if(levelId == 7)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}
			}
			 
			/* $("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
			$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated'); */
			
		}
		
		
	
}
function designationsByDepartments()
{
	$("#designationsWiseId").empty();
	$("#designationsWiseId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectedId").chosen().val();
	var deprtmntId = globalDepartmentId;
	var levelValue = $(".locationCls1").chosen().val();
	var jsObj = {
		departmentId	: deprtmntId,
		levelId			: LevelId,
		levelValue			: levelValue
	}
	$.ajax({
      type:'GET',
      url: 'getDesignationsByDepartmentNewAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		//str+='<option value="0">Select Designation</option>';
		str+='<option value="0">All</option>';
		if(result != null && result.length >0){
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#designationsWiseId").html(str);
		$("#designationsWiseId").trigger("chosen:updated");
	});
}
$(document).on("click",".subOrdinateFilterAlertsCls",function(){
	getSubOrdinateFilterAlertsOverview();
});


function getSubOrdinateFilterAlertsOverview(){
	$("#getSubOrdinateFilterAlertsOverview").html(spinner);

	var lvelIdsArr =[];
	var deginaIdsArr = [];
	var levelValuesArr = [];
	var deptIdsArr = [];
	var statusIdsArr = [];
	
	if(globalDepartmentId != null && globalDepartmentId != 0){
		deptIdsArr.push(globalDepartmentId);
	}

	var fromDays = 0;
	var toDays = 0;
	var lagChckedValue =$("#lagDaysId").is(':checked') ? 1 : 0;
	var checkedValue;
		if(lagChckedValue == 1){
			 fromDays = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html();
			 toDays = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html();
			lagChckedValue = "true";
		}else{
			lagChckedValue = "false";
		}
		
	var moreDAysChckedValue =$("#moreDaysId").is(':checked') ? 1 : 0;
 	  if(moreDAysChckedValue == 1){
		   fromDays = 0;
		   toDays = 0;
		   lagChckedValue = "true";
			moreDAysChckedValue = "true";
		}else{
			moreDAysChckedValue = "false";
		}
	
	if($("#locationLevelSelectedId").val() == null  || $("#locationLevelSelectedId").val() == 0)
	{
		
		$("#assignErrorDivId").html("Please select impact level");
		return;
	}else{
		lvelIdsArr.push($("#locationLevelSelectedId").val());
	}
	
	var dynamicLocationValue=$('.dynamicSelectList').val();
	var hasError=false;
	var displayName = "";
	if (typeof(dynamicLocationValue) != "undefined"){
		
		
		$('.dynamicSelectList').each(function(){
			if(!hasError){
				displayName = $(this).attr('attr_dyna_name');
				var id =  $(this).attr('id');
				var dynamicLocationValue=$('#'+id+'').val();
				if(dynamicLocationValue == null )
				{
					hasError=true;
					$("#assignErrorDivId").html("Please select "+displayName+". ");
					return;
				}			
			}else{
				return;
			}
		});	
		if(hasError)
			return;
		
	}
		var childLevelVals =[];	
		var childLevelId = 0; //0
		$('.childValCls').each(function(){
			childLevelVals =[];	
			var dynamicLevlId = $(this).attr('attr_dynamic_levelid');
			var id =  $(this).attr('id');
			var dynamicLocationValue=$('#'+id+'').val();
			
			if(dynamicLocationValue != null &&  dynamicLocationValue != 0)
				{
					childLevelId =dynamicLevlId;
					childLevelVals.push(dynamicLocationValue);
				}
		});
	
	hasError=false;
	if(!hasError){
		var locationValue=$(".locationCls1").val();
		if(locationValue == null)
		{
			$("#assignErrorDivId").html("Please select Location. ");
			return;
		}
	}
	if($("#designationsWiseId").val() == null)
	{
		$("#assignErrorDivId").html("Please select designation");
		return;
	}
	if($("#statusSelectedId").val() == null)
	{
		$("#assignErrorDivId").html("Please select Status");
		return;
	}
	if($("#PriorityId").val() == null )
	{
		$("#assignErrorDivId").html("Please select Status");
		return;
	}
	var priorityId = $("#PriorityId").val();
	if($("#designationsWiseId").val() != null && $("#designationsWiseId").val() != 0){
		deginaIdsArr.push($("#designationsWiseId").val());
	}

	if($("#statusSelectedId").val() != null && $("#statusSelectedId").val() != 0){
		statusIdsArr.push($("#statusSelectedId").val());
	}
	var alertType = getAlertType();
	$("#assignErrorDivId").html(' ');
	var jsObj = {
	  
		fromDate : currentFromDate,
		toDateStr : currentToDate,
		govtLevelIds :lvelIdsArr , //locationLevelSelectedId //impact level
		locationValues : [], //locationSubLevelSelectedId
		desigIds : deginaIdsArr,		 //designationsWiseId	
		statusIds : statusIdsArr,      //statusSelectedId
		deptIds : deptIdsArr,			//departmentIds
		priorityId : priorityId,				//PriorityId
		childLevelId:childLevelId,
		childLevelVals:childLevelVals,
		alertType : alertType,
		isLagChkd : lagChckedValue, // true
		isMoreThanYrChkd : moreDAysChckedValue, //check true or false true - 0,0 --- false (particular values)
		lagStartCnt : fromDays,
		lagEndCnt : toDays,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr
    
	}
	$.ajax({
		type:'GET',
		url: 'getSubOrdinateFilterAlertsOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSubOrdinateFilterAlertsOverview(result);
		}else{
			$("#getSubOrdinateFilterAlertsOverview").html("NO DATA");
		}
	});
}
function buildSubOrdinateFilterAlertsOverview(result)
{
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table-condensed">';
			str+='<thead style="background-color:#ccc;">';
				str+='<th>Level</th>';
				str+='<th>Location</th>';
				str+='<th>Designation</th>';
				str+='<th>Total</th>';
				for(var i in result[0].list1[0].list2[0].subList1)
				{
					str+='<th>'+result[0].list1[0].list2[0].subList1[i].name+'</th>';
				}
				
			str+='</thead>';
				for(var i in result)
				{
					
					for(var j in result[i].list1)
					{
						
						for(var k in result[i].list1[j].list2)
						{
							str+='<tr>';
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].list1[j].name+'</td>';
									str+='<td>'+result[i].list1[j].list2[k].name+'</td>';
									
									str+='<td>'+result[i].list1[j].list2[k].count+'</td>';
							for(var l in result[i].list1[j].list2[k].subList1)
							{
								
									str+='<td>'+result[i].list1[j].list2[k].subList1[l].count+'</td>';
								
							}
							str+='</tr>';
						}
					}
				}
			
		str+='</table>';
	str+='</div>';
	$("#getSubOrdinateFilterAlertsOverview").html(str);
}

