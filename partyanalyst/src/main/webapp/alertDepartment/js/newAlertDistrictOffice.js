 var globalLevelObj =  {"1":"STATE","2":"ZONE","3":"REGION","4":"CIRCLE","5":"DISTRICT","6":"DIVISION","7":"SUB DIVISION","8":"MANDAL","9":"MUNICIPALITY","10":"PANCHAYAT"};
 var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var globaldepartmentsArrForFilterView=[];


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
	function getAlertTypeForSubordinate(){
		 var alertType = ''; 
		$('.alertType-subordinate li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }    
		});
		return alertType;
	}
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
			var alertTypeForSubordinate = getAlertTypeForSubordinate();
			getBellowDistrictOfficerAlertsCountView(alertTypeForSubordinate);
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
			var deptObj = {id : globalDepartmentId,name: globalDepartmentName}
				globaldepartmentsArrForFilterView.push(deptObj);
		
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
	
	
	
$(document).on("click",".alertType-subordinate li",function(){
	$(this).parent("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var alertType = getAlertTypeForSubordinate();    
	getBellowDistrictOfficerAlertsCountView(alertType);
});
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
								str+='<li class="active" attr_type="statuswise" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">status overview</li>';
								str+='<li attr_type="scopewise"  attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">location level</li>';
								str+='<li attr_type="alertSource"  attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" attr_level_idstr="'+levelIdStr+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">Alert Source</li>';
								str+='<li id="filterViewId" attr_type="filterView" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" >Filter</li>';
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
		filerterViewBody()
		
		   
		var deptObj = result[0];
		
		 if(deptObj.subList1 != null && deptObj.subList1.length > 0){
			 for(var i in deptObj.subList1){
				getStateThenGovtDeptScopeWiseAlertCount(deptObj.id,deptObj.subList1[i].id,"statuswise","alert","levelWiseGraphView","count","desc",0,0,"Default",0);
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
			filerterViewBody();
			assignUser1(globaldepartmentsArrForFilterView);
			
			
			
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
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentIdStr[i],searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default",locationLevelId);
			getLocationBasedOnDepartmentLevel(departmentId,parentIdStr[i],districtLevelId);
			
		}
		
		
		
		
});
	
$(document).on("click",".switch-btn li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var searchType =  $(this).attr("attr_type");
		var departmentId = $(this).attr("attr_department_id");
		var departmentName = $(this).attr("attr_department_name");
		if(searchType == "statuswise" || searchType == "scopewise" || searchType == "alertSource"){
			var parentIdStr = $(this).attr("attr_level_idstr").split(',');
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
			
				
				$("#statusOvrvwId").show();
				$("#filterViewBodyId").hide();
			
			
			for(var i in parentIdStr){
					for(var j in subLevelIdStr){
						$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).html('');
						$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[j]]+'</option>');
						$("#locationNamesId"+departmentId+parentIdStr[i]+subLevelIdStr[j]).trigger('chosen:updated');
						$("#locationLevelNamesId"+departmentId+parentIdStr[i]).val(0)
						$("#locationLevelNamesId"+departmentId+parentIdStr[i]).trigger('chosen:updated')
				}
			}
			
			var locationLevelId=0;
			for(var i in parentIdStr){
				getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentIdStr[i],searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default",0);
				getLocationBasedOnDepartmentLevel(departmentId,parentIdStr[i],districtLevelId);
				
			}
	}else{
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
		$("#locationLevelNamesId"+departmentId+parentId).val(0)
		$("#locationLevelNamesId"+departmentId+parentId).trigger('chosen:updated')
		var locationLevelId=0;
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"Default",0);
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
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,0,0,"change",locationLevelId);
		getLocationBasedOnDepartmentLevel(departmentId,levelId,districtLevelId);
});
function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,alertType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,locationLevelId){
	$("#"+divId+departmentId+parentGovtDepartmentScopeId).html(spinner);
	/* var group='status';

	if(searchType == "scopewise" && parentGovtDepartmentScopeId == 1){
		group = "overview";
	} */
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
		 $("#locationLevelNamesId"+departmentId+levelId).val(0)
		 $("#locationLevelNamesId"+departmentId+levelId).trigger('chosen:updated')
		 
		
		if(childLevelId > 0){
		 getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue);	
		}
		
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,alertType,"levelWiseGraphView",sortingType,orderType,subLevelId,locationValue,"Change",0);
		
		
	}); 
	
function getLocationBasedOnDepartmentLevel(departmentId,parentScopeId,districtLevelId){
	  $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).html('');
	  	
		var alertType = getAlertType();
		var subLevelArr = [];
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
   var locationLevelIdClickArr=[];
	 if(locationLevelId == null || locationLevelId == 0){
			 locationLevelIdClickArr =[];
		 }else{
			  locationLevelIdClickArr.push(locationLevelId);
		 }
  
  $("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
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


//getBellowDistrictOfficerAlertsCountView("task");
function getBellowDistrictOfficerAlertsCountView(task){
	$("#subordinateAlertsDivId").html(spinner);
		var paperIdArr = [];   
		var chanelIdArr = [];
		var callCenterArr = [];
		
		var jsObj = { 
			fromDate:currentFromDate, 
			toDate: currentToDate,
			paperIdArr:newspapersGlobalArr,
			chanelIdArr:channelGlobalArr,
			callCenterArr:callCenterGlobalArr,
			task:task, 
			sortingType:"Descending"  
		}
		$.ajax({
			type:'GET',
			url: 'getBellowDistrictOfficerAlertsCountViewAction.action',
			data: {task :JSON.stringify(jsObj)}  
		}).done(function(result){
			$("#subordinateAlertsDivId").html('');
			if(result != null && result.length > 0){   
				$("#collectorateVidId").show();            
				if(task=="task"){
					buildBellowDistrictOfficerAlertsCountViewForTask(result)
				}else{
					buildBellowDistrictOfficerAlertsCountViewForSubTask(result)
				}
			}else{
				$("#subordinateAlertsDivId").html("No Data Available");
			}
		});
}
function buildBellowDistrictOfficerAlertsCountViewForSubTask(result){
		if(result !=null && result.length>0){
			var locationNamesArrDistrictOverView=[];
				 var notifiedArr = [];//1
				 var actionInProgessArr = [];//2
				 var completedArr = [];//3
				 var actionNotRequiredArr = [];//4
				 var unabletoResolveArr = [];//5
				 var reopenArr = [];//6       
				 var closedArr = [];//7
				
			for(var i in result){
				 locationNamesArrDistrictOverView.push(result[i].name)
				if(result[i].subList1 !=null &&  result[i].subList1.length>0){
					for(var j in result[i].subList1){  
						 if(result[i].subList1[j].id==1){
							 notifiedArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==2){
							 actionInProgessArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==3){
							 completedArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==4){
							 actionNotRequiredArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==5){
							 unabletoResolveArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==6){
							 reopenArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==7){
							 closedArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}
					}
				}
			var mainJosnObjArrDistrictOverview = [];
				
				if(notifiedArr != null && notifiedArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Notified',data:notifiedArr,color:"#EFA5B6"});  
				}
				if(actionInProgessArr != null && actionInProgessArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Action In Progess',data:actionInProgessArr,color:"#FFCB7F"});  
				}
				if(completedArr != null && completedArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Completed',data:completedArr,color:"#4d9b66"});  
				}
				if(actionNotRequiredArr != null && actionNotRequiredArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Action Not Required',data:actionNotRequiredArr,color:"#9698C8"});  
				}
				
				if(unabletoResolveArr != null && unabletoResolveArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Rejoinder',data:unabletoResolveArr,color:"#82CA9C"});  
				}
				if(reopenArr != null && reopenArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Reopen',data:reopenArr,color:"#C9AC82"});  
				}
				if(closedArr != null && closedArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Closed',data:closedArr,color:"#C9AC82"});  
				}
		
		
			
			var heightOfDiv = locationNamesArrDistrictOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#subordinateAlertsDivId").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'subordinateAlertsDivId';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
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
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDistrictOverView
				}
				var plotOptions =  {
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
										var desigDeptOfficerIdId = value[0];
										var statusId = value[1];
										var name = value[2];
										var totalCount = value[3];
										var officerId = value[4];
										getBellowDistrictOfficerAlertsDtls(desigDeptOfficerIdId,officerId,name,totalCount,statusId);
									}       
								}
							}
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
			}
			$.each($('#subordinateAlertsDivId').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
				$(this).attr("style","cursor:pointer;");       
				$(this).attr("class","gettotalAlertsForSubordinateCls");    
				$(this).attr("attr_desig_id",result[index].id);         
				$(this).attr("attr_officer_id",result[index].govtOfficerId);         
				$(this).attr("attr_officer_name",result[index].name);	
				$(this).attr("attr_total_count",result[index].count);
			});
		}else{
			$("#subordinateAlertsDivId").html('No Data Available');
		}
}
function buildBellowDistrictOfficerAlertsCountViewForTask(result){
		if(result !=null && result.length>0){
			var locationNamesArrDistrictOverView=[];
				 var notifiedArr = [];//2
				 var actionInProgessArr = [];//3
				 var completedArr = [];//4	
				 var actionNotRequiredArr = [];//6
				 var duplicateArr = [];//7
				 var wronglyMappedDesignationArr = [];//8
				 var wronglyMappedDepartmentArr = [];//9
				 var rejoinderArr = [];//10
				 var reopenArr = [];//11
				 var closedArr = [];//12
				
			for(var i in result){
				 locationNamesArrDistrictOverView.push(result[i].name)
				if(result[i].subList1 !=null &&  result[i].subList1.length>0){
					for(var j in result[i].subList1){
						 if(result[i].subList1[j].id==2){
							 notifiedArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==3){
							 actionInProgessArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==4){
							 completedArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==6){
							 actionNotRequiredArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==7){
							 duplicateArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==8){
							 wronglyMappedDesignationArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==9){
							 wronglyMappedDepartmentArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==10){
							 rejoinderArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==11){
							 reopenArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}else if(result[i].subList1[j].id==12){
							 closedArr.push({"y":result[i].subList1[j].count,"extra":result[i].id+"-"+result[i].subList1[j].id+"-"+result[i].subList1[j].name+"-"+result[i].subList1[j].count+"-"+result[i].govtOfficerId});
						}
					}
					
				}
			
			
			
			var mainJosnObjArrDistrictOverview = [];
				
				if(notifiedArr != null && notifiedArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Notified',data:notifiedArr,color:"#EFA5B6"});  
				}
				if(actionInProgessArr != null && actionInProgessArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Action In Progess',data:actionInProgessArr,color:"#FFCB7F"});  
				}
				if(completedArr != null && completedArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Completed',data:completedArr,color:"#4d9b66"});  
				}
				if(actionNotRequiredArr != null && actionNotRequiredArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Action Not Required',data:actionNotRequiredArr,color:"#9698C8"});  
				}
				if(duplicateArr != null && duplicateArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Duplicate',data:duplicateArr,color:"#DEC6E0"});  
				}
				if(wronglyMappedDesignationArr != null && wronglyMappedDesignationArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Wrongly Mapped Designation',data:wronglyMappedDesignationArr,color:"#FE9900"});  
				}
				if(wronglyMappedDepartmentArr != null && wronglyMappedDepartmentArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Wrongly Mapped Department',data:wronglyMappedDepartmentArr,color:"#0C9514"});  
				}
				if(rejoinderArr != null && rejoinderArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Rejoinder',data:rejoinderArr,color:"#82CA9C"});  
				}
				if(reopenArr != null && reopenArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Reopen',data:reopenArr,color:"#C9AC82"});  
				}
				if(closedArr != null && closedArr.length > 0){
					mainJosnObjArrDistrictOverview.push({name:'Closed',data:closedArr,color:"#C9AC82"});  
				}
		
		
			
			var heightOfDiv = locationNamesArrDistrictOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#subordinateAlertsDivId").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'subordinateAlertsDivId';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
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
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDistrictOverView
				}
				var plotOptions =  {
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
										var desigDeptOfficerIdId = value[0];
										var statusId = value[1];
										var name = value[2];
										var totalCount = value[3];
										var officerId = value[4];
										getBellowDistrictOfficerAlertsDtls(desigDeptOfficerIdId,officerId,name,totalCount,statusId);
									}       
								}
							}
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
			}
			$.each($('#subordinateAlertsDivId').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
				$(this).attr("style","cursor:pointer;");    
				$(this).attr("class","gettotalAlertsForSubordinateCls");    
				$(this).attr("attr_desig_id",result[index].id);         
				$(this).attr("attr_officer_id",result[index].govtOfficerId);         
				$(this).attr("attr_officer_name",result[index].name);	
				$(this).attr("attr_total_count",result[index].count);
			});
		}else{
			$("#subordinateAlertsDivId").html('No Data Available');
		}
}
$(document).on("click",".gettotalAlertsForSubordinateCls",function(){
	var designationId = $(this).attr("attr_desig_id");
	var officerId = $(this).attr("attr_officer_id");
	var officerName = $(this).attr("attr_officer_name");
	var totalCount = $(this).attr("attr_total_count");
	getBellowDistrictOfficerAlertsDtls(designationId,officerId,officerName,totalCount,0);
});
function getBellowDistrictOfficerAlertsDtls(designationOfficerId,officerId,officerName,totalCount,statusId){
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		var alertType = getAlertTypeForSubordinate();
		var jObj = {
			statusId:statusId,
			task:alertType,
			fromDate:currentFromDate,      
			toDate:currentToDate,
			desigDeptOfficerId:designationOfficerId,
			officerId:officerId,
			paperIdArr:newspapersGlobalArr,  
			chanelIdArr:channelGlobalArr,
			callCenterArr:callCenterGlobalArr
		}
	$.ajax({
      type:'GET',
      url: 'getBellowDistrictOfficerAlertsDtlsAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,officerName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
	
}