//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><img src="alertDepartment/img/alert logo.png" alt="alert Logo"  class="alert-logo"/></div></div>';
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner-logo"><img src="alertDepartment/img/spinner/1.png" class="right-arrow"/><img src="alertDepartment/img/spinner/2.png" class="right-arrow1"/><img src="alertDepartment/img/spinner/3.png" class="left-arrow"/><img src="alertDepartment/img/spinner/4.png" class="left-arrow1"/><img src="alertDepartment/img/spinner/5.png" class="main-icon"/></div></div></div>';
 var globalLevelObj =  {"1":"STATE","2":"ZONE","3":"REGION","4":"CIRCLE","5":"DISTRICT","6":"DIVISION","7":"SUB DIVISION","8":"MANDAL","9":"MUNICIPALITY","10":"PANCHAYAT","11":"WARD","12":"GMC","13":"CLUSTER"};
var globalFinancialAssColorObj = {"Financial Assistance Required":"#B59AE9","Policy Decision Required":"#6EB6F0","Others":"#3FE2CD"}; 
 var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var globalAlertSourceColorObj =  {"Manual":"#E54BB3","Print Media":"#69BC6E","Electronic Media":"#8D69C8","Call Center":"#EFC000","Facebook":"#00ABED","Twitter":"#F7776C","Social Media":"#00ABED","Monday Grievance":"#FA8072","Janmabhoomi":"#00FF00","Special Grievance - SC/ST":"#0000FF","General Grievance":"#808000"};	 
var globaldepartmentsArrForFilterView=[];
	
	function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getSelectedType(){
		 var levelType = ''; 
		 var departmentType='';
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  levelType = $(this).attr("attr_type");
			  departmentType=$(this).attr("attr_department_type");
			 }
		});
		return {
				levelType:levelType,
				departmentType:departmentType
		}
	}
	function getSortingType(){
		 var sortingType = ''; 
		$('.locationWiseSorting li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			 }
		});
		return sortingType;
	}
	function filerterViewBody(){
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
		var socialMediaIdLen = globalsocialMediaTypeIdsArr.length;
		var alertSeverityLen = globalAlertSeverityIdsArr.length;
		var mondayGrievanceTypeLen = globalMondayGrievanceTypeIdsArr.length;
		var janmabhoomiTypeLen = globalJanmabhoomiTypeIdsArr.length;
		var specialGrievanceTypeLen = globalSpecialGrievanceTypeIdsArr.length;
		var generalGrievanceTypeLen = globalGeneralGrievanceTypeIdsArr.length;
		
  	    var alertStatusId = globalAlertStatusIdsArr[0];
		 if(alertStatusId == 0){
		   globalAlertStatusIdsArr = [];
		 }
		 var alertSubTaskStatusId = globalAlertSubTaskStatusIdsArr[0];
		 if(alertSubTaskStatusId == 0){
		   globalAlertSubTaskStatusIdsArr = [];
		 }
		 
		 var alertStatusLen = globalAlertStatusIdsArr.length;
		 var subTaskLen = globalAlertSubTaskStatusIdsArr.length;
		 
		if(newsPaperIdLen == 0 && channelIdLen == 0 && callCenterIdLen ==0 && socialMediaIdLen == 0 && mondayGrievanceTypeLen == 0 && janmabhoomiTypeLen == 0 && specialGrievanceTypeLen == 0 && generalGrievanceTypeLen == 0){
			alert("Please Select Atleast One Media Type.");
			return;
		}
		if(alertStatusLen > 0){
			if(alertSeverityLen == 0){
				alert("Please Select Atleast One Alert Severity."); 
				return;
			}
		}
		if(alertStatusLen == 0 && subTaskLen == 0){
			alert("Please Select Atleast One Alert Status."); 
			return;
		}
		if(globalAlertSubTaskStatusIdsArr !=null && globalAlertSubTaskStatusIdsArr.length == 0){
			globalAlertSubTaskStatusIdsArr.push(0);
		}
		if(globalAlertStatusIdsArr !=null && globalAlertStatusIdsArr.length == 0){
			globalAlertStatusIdsArr.push(0);
		}
		
		 var alertType = getAlertType();
		 var statusId = globalAlertStatusIdsArr[0];
			var statusTaskStatusId = globalAlertSubTaskStatusIdsArr[0];
			if(statusId == 0 && statusTaskStatusId != 0){
				$(".switch-btn-alertType li").removeClass("active");
				$(".alertType-subordinate  li").removeClass("active");
				$(".subTaskTrigger").addClass("active");
				$(".subTaskSubOrdinate").addClass("active");
			}else{
				$(".switch-btn-alertType li").removeClass("active");
				$(".alertType-subordinate li").removeClass("active");
				$(".alertTrigger").addClass("active");
				$(".alertSubOridanateCls").addClass("active");
			}
			
		 
		 $(".documentCloseClass").hide();
		 getDistrictOfficerAlertsCountView();
		 getGovtDepartmentDetails();
		 var alertTypeForSubordinate = getAlertTypeForSubordinate();
		 getBellowDistrictOfficerAlertsCountView(alertTypeForSubordinate);
		 $("#departmentWiseLocationBlockId").html('');
	}

	function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip){
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
		var jObj ={
		  startDate:currentFromDate,
		  endDate:currentToDate,
		  paperIdArr:newspapersGlobalArr,
		  chanelIdArr:channelGlobalArr,
		  callCenterArr:callCenterGlobalArr,
	      socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		  alertSeverityIdsArr:globalAlertSeverityIdsArr,
          alertStatusIdsArr:globalAlertStatusIdsArr,
          alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		  mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		  specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		  generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
				globalgovtDeptDesigOffcrIds = result.govtDeptDesigOffcrIds;
				globalgovtOfficerIds = result.govtOfficerIds;
				globalDesignationId = result.designationId;
				globalUserLevelId = result.levelId;
				if(result.levelValues != null && result.levelValues.length > 0)
					globalUserLevelValues=result.levelValues;
				
				buildDistrictOfficerAlertsCountView(result);
			
			}
			
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
				str+='<p class="pad_5 todayCountCls" attr_alertTaskType="mainAlert" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count= "'+result.list1[0].todayCount+'" attr_alert_type="alert">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
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
				str+='<p class="pad_5 overAllCount" attr_alertTaskType="mainAlert" attr_overCunt="'+overAllAlertIds+'" attr_name="OVERALL" attr_total_count="'+result.list1[0].overAllCnt+'" attr_alert_type="alert">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
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
				str1+='<p class="pad_5 todayCountCls" attr_alertTaskType="mainAlert" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count="'+result.list2[0].todayCount+'" attr_alert_type="mySubTasks">TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].todayCount+'</span></p>';
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
				str1+='<p class="pad_5 overAllCount" attr_alertTaskType="mainAlert" attr_overCunt="'+overAllAlertIds+'" attr_name="OVERALL" attr_total_count="'+result.list2[0].overAllCnt+'" attr_alert_type="mySubTasks">OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].overAllCnt+'</span></p>';
			 }else{
				 str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }
			}else{
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str1+='<div id="mySubTasksGraphView" style="height:250px"></div>';
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
				str2+='<p class="pad_5 todayCountCls" attr_alertTaskType="subTask" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count= "'+result.list3[0].todayCount+'" attr_alert_type="myAssignedSubTasks" >TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list3[0].todayCount+'</span></p>';
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
				str2+='<p class="pad_5 overAllCount" attr_alertTaskType="subTask" attr_overCunt="'+overAllAlertIds+'" attr_name="OVERALL" attr_total_count="'+result.list3[0].overAllCnt+'" attr_alert_type="myAssignedSubTasks" >OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list3[0].overAllCnt+'</span></p>';
				}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str2+='<div id="assignedSubTasksGraphView" style="height:250px"></div>';
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
		
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count");
	var alertType = $(this).attr("attr_alert_type");
	var alertTaskType = $(this).attr("attr_alertTaskType");
	getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','','');
	
	var jObj = {
		govtDepDesigOffcrIds : globalgovtDeptDesigOffcrIds,
		govtOfficerIds : globalgovtOfficerIds,
		countType: "overAll",
		alertType : alertType,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr,
		fromDate:currentFromDate,
		toDate:currentToDate,
        socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
        alertSeverityIdsArr:globalAlertSeverityIdsArr,
        alertStatusIdsArr:globalAlertStatusIdsArr,
        alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	var alertType = $(this).attr("attr_alert_type");
	var alertTaskType = $(this).attr("attr_alertTaskType");
	getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','','');
	
	var jObj = {
		govtDepDesigOffcrIds : globalgovtDeptDesigOffcrIds,
		govtOfficerIds : globalgovtOfficerIds,
		countType: "today",
		alertType : alertType,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr,
		fromDate:currentFromDate,
		toDate:currentToDate,
        socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
        alertSeverityIdsArr:globalAlertSeverityIdsArr,
        alertStatusIdsArr:globalAlertStatusIdsArr,
        alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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



function getDistrictLevelDeptWiseAlertClick(StatusId,name,totalCount,clickType)
{
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		if(clickType == "alert"){
			getFilterSectionAlertDetails(name,totalCount,globalDepartmentIdsArr,StatusId,'','mainAlert');
		}else{
			getFilterSectionAlertDetails(name,totalCount,globalDepartmentIdsArr,StatusId,'','mainAlertSubTask');
		}
		 var statusIdsArr = [];
		  if(StatusId != null && StatusId == 0){
			  statusIdsArr = globalAlertStatusIdsArr;
		  }else{
			  statusIdsArr.push(StatusId);
		  }
		
		var jsObj = {
			govtDepDesigOffcrIds : globalgovtDeptDesigOffcrIds,
			govtOfficerIds : globalgovtOfficerIds,
			alertStatusIdsArr:statusIdsArr,
			formDate:currentFromDate, 
			toDate: currentToDate,
			clickType:clickType,
			paperIdArr:newspapersGlobalArr,
			chanelIdArr:channelGlobalArr,
			callCenterArr:callCenterGlobalArr,
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
            alertSeverityIdsArr:globalAlertSeverityIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
			janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
			specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
			generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr			
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

/*Alert Source Start */

function getAlertSourceWiseAlert(globalDepartmentIdsArr)
{
	 
	$("#alertSourceWiseDetilsDivId").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
	  deptIdArr : globalDepartmentIdsArr,  
      paperIdArr:newspapersGlobalArr,
	  chanelIdArr:channelGlobalArr,
	  callCenterArr:callCenterGlobalArr,
	  userType :"districtCollector",
	  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
	  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
	  alertSeverityIdsArr:globalAlertSeverityIdsArr,
      alertStatusIdsArr:globalAlertStatusIdsArr,
      alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
	  mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
	  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	  specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	  generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
    }
    $.ajax({
      type:'GET',
      url: 'getAlertSourceWiseAlertAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#alertSourceWiseDetilsDivId").html('');
	   if(result !=null && result.length>0){
		   buildAlertSouceWiseDetails(result);
	   }else{
		   $("#alertSourceWiseDetilsDivId").html('No Data Available.');
	   }
    });
}

function buildAlertSouceWiseDetails(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-2 col-xs-12 col-sm-4">';
			str+='<div id="alertSourceWiseGraphView" ></div>';
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
								str+='<td style="cursor:pointer;" onclick="getAlertDtlsByAlertSource(\''+result[i].name+'\','+result[i].alertCnt+','+result[i].id+',0,\'alertSource\');" class="alertSourceCls" attr_alert_source_name="'+result[i].name+'" attr_alert_count="'+result[i].alertCnt+'" attr_source_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
								
							str+='</tr>';
						}
					str+='</tbody>';  
				str+='</table>';
			str+='</div>';
		str+='</div>'; 
		str+='<div class="col-md-8 col-xs-12 col-sm-4" style="box-shadow: -3px 0 3px 3px rgba(0, 0, 0, 0.4);">';
		str+='<div class="scollerDivalertSourceWise">';
			str+='<div id="alertSourceWisebarGraphView"></div>';
		str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#alertSourceWiseDetilsDivId").html(str);
	var str2='';
	var statusName = "Total"
	str2+='<h4 style="cursor:pointer;" class="text-center alertSourceCls" onclick="getAlertDtlsByAlertSource(\''+statusName+'\','+totalAlert+',0,0,\'alertSource\');" attr_alert_source_name="Total" attr_alert_count='+totalAlert+' attr_source_id="0">TOTAL '+totalAlert+'</h4>';
	//$("#alertSourceWiseTotal").html("<h4  class='text-center alertSourceCls' style='cursor:pointer;' onclick='getAlertDtlsByAlertSource(\'Total'\ ,"+totalAlert+",0);' attr_alert_source_name='Total' attr_alert_count="+totalAlert+" attr_source_id='0'>TOTAL "+totalAlert+"</h4>");
	$("#alertSourceWiseTotal").html(str2);
	var statusOverviewArrss =[];
	if(result.length > 7)
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - <br/>"+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
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
			
				var heightOfDiv = mediaNamesArr.length ;
				if(heightOfDiv >7){
					$(".scollerDivalertSourceWise").mCustomScrollbar({setHeight:'300px'})
				}else{
					$("#alertSourceWisebarGraphView").css("height","auto");
					$(".scollerDivalertSourceWise").removeAttr('style')
					$(".scollerDivalertSourceWise").mCustomScrollbar('destroy');
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
										var alertSourceType = 'alertSource'
										getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,statusId,alertSourceType);
										
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
						$(this).attr("onclick","getAlertDtlsByAlertSource(\'"+result[index].name+"\',\'"+result[index].alertCnt+"\',\'"+result[index].id+"\',0,\'alertSource\')");
					
				
				});
		}
}
function getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,alertStatusId,alertSourceType){
	
	$("#alertManagementPopupBody").html('')

	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});

	$("#alertManagementPopupBody").html(spinner);
	
	if(statusName == "Total"){
		getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','','');
	}else{
		getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,alertCategoryId,'',alertSourceType);
	}
	
	  var statusIdsArr = [];
	  if(alertStatusId != null && alertStatusId == 0){
		  statusIdsArr = globalAlertStatusIdsArr;
	  }else{
		  statusIdsArr.push(alertStatusId);
	  }
	
    var jsObj ={
		fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
	  paperIdArr:newspapersGlobalArr,
	  chanelIdArr:channelGlobalArr,
	  callCenterArr:callCenterGlobalArr,
	  alertCategoryId:alertCategoryId,
	  userType :"districtCollector",
	  alertStatusIdsArr:statusIdsArr,
	  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
	  alertSeverityIdsArr:globalAlertSeverityIdsArr,
	  mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
	  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	  specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	  generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr  
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
function getGovtDepartmentDetails(){
		$("#govtDepartmentsId").html('');
		$.ajax({
		  type:'GET',
		  url: 'getGovtDepartmentDetailsAction.action',
		  data: {}
		}).done(function(result){
			if(result !=null && result.length>0){
				globaldepartmentsArrForFilterView =result;
				globalDepartmentIdsArr=[];
				$("#govtDepartmentsId").append('<option value="0">Select Department</option>');
				for(var i in result){
					globalDepartmentIdsArr.push(result[i].id);
					$("#govtDepartmentsId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
			var alertType = getAlertType();
			getAlertSourceWiseAlert(globalDepartmentIdsArr);
			getFinancialAssistanceAlertCntCategoryWise(globalDepartmentIdsArr);
			getGovtDeptScopeDetails(0);
			getDistrictLevelDeptWiseStatusOverView(alertType,"Decending",0,0,"departmentWiseGraphViewDetails"); 
		});
		
	}
	
	function getGovtDeptScopeDetails(departmentId){
		$("#locationLevelNamesId").html('');
		var deptArr = [];
		 if(departmentId != null && departmentId > 0){
			 deptArr.push(departmentId);
		 }else{
			 deptArr = globalDepartmentIdsArr;
		 }
		 var jObj ={
     	   deptArr:deptArr
		  }
		$.ajax({
		  type:'GET',
		  url: 'getGovtDeptScopeDetailsAction.action',
		  data:{task :JSON.stringify(jObj)}
		}).done(function(result){
			$("#locationLevelNamesId").append('<option value="0">Select Level</option>');
			if(result !=null && result.length>0){
				for(var i in result){
					$("#locationLevelNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});
		
	}

	function getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,levelId,divId){
		$("#"+divId).html(spinner);
		var deptArr = [];
		if(departmentId > 0){
				deptArr.push(departmentId);
		}else{
			deptArr = globalDepartmentIdsArr
		}
		var jObj = {
			startDate: currentFromDate,
			fromDate: currentToDate,
			type:alertType,
			deptArr:deptArr,
			sortingType:sortingType,
			levelId:levelId,
			paperIdArr:newspapersGlobalArr,
			chanelIdArr:channelGlobalArr,
			callCenterArr:callCenterGlobalArr,
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
			alertSeverityIdsArr:globalAlertSeverityIdsArr,
            alertStatusIdsArr:globalAlertStatusIdsArr,
            alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
			janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
			specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
			generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
			
		}
		$.ajax({
		  type:'GET',
		  url: 'getDistrictLevelDeptWiseStatusOverViewAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			$("#"+divId).html('');
			if(result !=null && result.length>0){
				buildDistrictLevelDeptWiseStatusOverView(result,levelId,divId);
			}else{
				$("#"+divId).html('<div class="col-xs-12">NO DATA AVAILABLE</div>');
			}
			
		});
}

function buildDistrictLevelDeptWiseStatusOverView(result,levelId,divId){
	var alertType = getAlertType();
	if(result !=null && result.length>0){
			    var locationNamesArrDistrict=[];
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
				 var Reopen = [];
				 var Closed = [];
				 var Proposal = [];
		for(var i in result){
				 locationNamesArrDistrict.push(result[i].name)
				if(result[i].subList2 !=null &&  result[i].subList2.length>0){
					for(var j in result[i].subList2){
							
						if(alertType == "alert"){
										if(result[i].subList2[j].id==1){
											pendingAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId}); 
										}else if(result[i].subList2[j].id==2){
											 notifiedAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==3){
											 actionInProgessAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==4){
											 completedAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==5){
											 unblTRslvAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==6){
											 actionNotRequiredAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==7){
											 duplicateAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}
										else if(result[i].subList2[j].id==8){
											 WronglyMappedDesignationArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==9){
											 WronglyMappedDepartmentArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==10){
											 RejoinderArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==11){
											 Reopen.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==12){
											 Closed.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}else if(result[i].subList2[j].id==13){
											 Proposal.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
										}
								
							}else if(alertType == "subTask"){
									if(result[i].subList2[j].id==1){
										 notifiedAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}else if(result[i].subList2[j].id==2){
										 actionInProgessAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}else if(result[i].subList2[j].id==3){
										 completedAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}else if(result[i].subList2[j].id==5){
										 unblTRslvAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}else if(result[i].subList2[j].id==4){
										 actionNotRequiredAlertArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}else if(result[i].subList2[j].id==6){
										 Reopen.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}else if(result[i].subList2[j].id==7){
										 Closed.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+levelId});
									}
								
							}
					}
					
				}
			
			
			var mainJosnObjArrDistrict = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrDistrict.push({name:'WronglyMappedDesignation',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrDistrict.push({name:'WronglyMappedDepartment',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Rejoinder',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Reopen != null && Reopen.length > 0){
				mainJosnObjArrDistrict.push({name:'Reopen',data:Reopen,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrDistrict.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }if(Proposal != null && Proposal.length > 0){
				mainJosnObjArrDistrict.push({name:'Proposal',data:Proposal,color:"#5a8476"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrDistrict.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#"+divId).css("height",heightOfDiv);
			}else{
				$("#"+divId).css("height","auto");
			}
			
		}	
			
			$("#"+divId).highcharts({
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
						categories: locationNamesArrDistrict,
						labels: {
							formatter: function() {
								return this.value.toString().substring(0, 25)+'...';
							},
							
						}
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
										var departmentId = value[0];
										var levelId = value[4];
										var statusName = value[2];
										var totalCount = value[3];
										var statusId=value[1];
										var alertCategoryId = 0;
										getTotalAlertCountDetails(departmentId,levelId,statusId,statusName,totalCount,alertCategoryId);
										
									}
								}
							}
				        }
					},
					legend: {
						verticalAlign:'top',
						enabled: true
					},
					series: mainJosnObjArrDistrict
				});
			
				 $.each($("#"+divId).find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");
					$(this).attr("onclick","getTotalAlertCountDetails(\'"+result[index].id+"\',\'"+levelId+"\',0,\'"+result[index].name+"\',\'"+result[index].count+"\',0)");					
				});
			
		}else{
			$("#"+divId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#"+divId).css("height","25px"); 
		}
}

$(document).on("click",".switch-btn li",function(){
	$(this).parent("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var selectedLevelType= $(this).attr("attr_type");
	var alertType = getAlertType();
	var sortingType = getSortingType();
	var departmentId = $("#govtDepartmentsId option:selected").val();
	var departmentName = $("#govtDepartmentsId option:selected").text();
	var departmentType = $(this).attr("attr_department_type");
	$( "#locationLevelNamesId" ).val(0);
	$("#departmentWiseLocationBlockId").html('');
		
		if(selectedLevelType == "status"){
			$(".sortingDivCls").show();
			$(".locationLevelNamesCls").show();
			$("#filterViewBodyId").hide();
			$("#departmentWiseGraphViewDetails").show();
			$("#govtDepartmentsId").show();
			getGovtDeptScopeDetails(0);
			getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0,"departmentWiseGraphViewDetails"); 
		}else if(selectedLevelType == "scopeLevel"){
			$(".sortingDivCls").show();
			$(".locationLevelNamesCls").hide();
			$("#filterViewBodyId").hide();
			$("#departmentWiseGraphViewDetails").show();
			$("#govtDepartmentsId").show();
			getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
		}else if(selectedLevelType == "alertCategory"){
			$(".sortingDivCls").show();
			$(".locationLevelNamesCls").show();
			$("#govtDepartmentsId").show();
			$("#filterViewBodyId").hide();
			$("#departmentWiseGraphViewDetails").show();
			getGovtDeptScopeDetails(0);
			getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
		}else if(selectedLevelType == "filterView"){
			filerterViewBody();
			assignUser1(globaldepartmentsArrForFilterView);
			$("#departmentWiseGraphViewDetails").hide();
			$("#filterViewBodyId").show();
			$("#govtDepartmentsId").hide();
			$(".locationLevelNamesCls").hide();
			$(".sortingDivCls").hide();
		}
		if(selectedLevelType != "filterView"){
			if(departmentId !=0){
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}	
		}	
		
		
			
});
$(document).on("click",".switch-btn-alertType li",function(){
	$(this).parent("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var alertType = $(this).attr("attr_type");
	var selectedLevelType = getSelectedType().levelType;
	var departmentType = getSelectedType().departmentType;
	var sortingType = getSortingType();
	//$("#govtDepartmentsId").val(0);
	var departmentId = $("#govtDepartmentsId option:selected").val();
	var departmentName = $("#govtDepartmentsId option:selected").text();
	
		if(selectedLevelType == "status"){
			$(".sortingDivCls").show();
			$(".locationLevelNamesCls").show();
			$("#govtDepartmentsId").show();
			getGovtDeptScopeDetails(0);
			getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0,"departmentWiseGraphViewDetails");
			if(departmentId !=0){
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}
		}else if(selectedLevelType == "scopeLevel"){
			$(".sortingDivCls").show();
			$(".locationLevelNamesCls").hide();
			$("#govtDepartmentsId").show();
			getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
			if(departmentId !=0){
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}
		}else if(selectedLevelType == "alertCategory"){
			$(".locationLevelNamesCls").show();
			$("#govtDepartmentsId").show();
			$(".sortingDivCls").show();
			getGovtDeptScopeDetails(0);
			getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
			if(departmentId !=0){
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}
		}
		

	
	if($("#filterViewId").hasClass("active") == true){
			filerterViewBody();
			assignUser1(globaldepartmentsArrForFilterView);
			$("#govtDepartmentsId").hide();
			$(".locationLevelNamesCls").hide();
			$(".sortingDivCls").hide();
	}
		
	
});

$(document).on("click",".sortingDivCls li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var sortingType = $(this).attr("attr_sorting_type");
			var selectedLevelType = getSelectedType().levelType;
			var alertType = getAlertType();
			var departmentId = $( "#govtDepartmentsId option:selected" ).val();
			var locationLevelId = $( "#locationLevelNamesId option:selected" ).val();
			
			if(selectedLevelType == "status"){
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,locationLevelId,"departmentWiseGraphViewDetails"); 
			}else if(selectedLevelType == "scopeLevel"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
			}else if(selectedLevelType == "alertCategory"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,locationLevelId,"departmentWiseGraphViewDetails");
			}
			
});
$(document).on("change","#govtDepartmentsId",function(){
			var selectedLevelType = getSelectedType().levelType;
			var departmentType = getSelectedType().departmentType;
			var alertType = getAlertType();
			var sortingType = getSortingType();
			var departmentId = $(this).val();
			var departmentName = $("#govtDepartmentsId option:selected").text();
			
			//var locationLevelId = $("#locationLevelNamesId").val();
			 var locationLevelId = 0;
			if(selectedLevelType == "status"){
				getGovtDeptScopeDetails(departmentId);
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,locationLevelId,"departmentWiseGraphViewDetails"); 
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}else if(selectedLevelType == "scopeLevel"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}else if(selectedLevelType == "alertCategory"){
				getGovtDeptScopeDetails(departmentId);
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,locationLevelId,"departmentWiseGraphViewDetails");
				getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType);
			}
			
			
			
			
});	
		
		$(document).on("change","#locationLevelNamesId",function(){
			var locationLevelId = $(this).val();
			var departmentId = $("#govtDepartmentsId").val();
			var alertType = getAlertType();
			var selectedLevelType = getSelectedType().levelType;
			var sortingType = getSortingType();
			if(selectedLevelType == "status"){
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,locationLevelId,"departmentWiseGraphViewDetails"); 
			}else if(selectedLevelType == "alertCategory"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,locationLevelId,"departmentWiseGraphViewDetails");
			}
			
		});
		
function getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,locationLevelId,divId){
	$("#"+divId).html(spinner);
	var deptArr = [];
	if(departmentId > 0){
			deptArr.push(departmentId);
	}else{
		deptArr = globalDepartmentIdsArr
	}
	
	var locationLevelIdArr = []
	if(locationLevelId == null || locationLevelId == 0){
			locationLevelIdArr = [];
	}else{
		locationLevelIdArr.push(locationLevelId);
	}
	
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptArr:deptArr,
		sortingType:sortingType,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr,
		resultType:selectedLevelType,
		subLevelArr:locationLevelIdArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		alertSeverityIdsArr:globalAlertSeverityIdsArr,
        alertStatusIdsArr:globalAlertStatusIdsArr,
        alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseLocationLevelViewAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#"+divId).html('');
		if(result !=null && result.length>0){
			buildDistrictLevelDeptWiseLocationLevelView(result,selectedLevelType,departmentId,divId,locationLevelId);
		}else{
			$("#"+divId).html("No Data Available");
		}
		
	});
}

	function buildDistrictLevelDeptWiseLocationLevelView(result,selectedLevelType,departmentId,divId,locationLevelId){
		if(result !=null && result.length>0){
			if(selectedLevelType == "scopeLevel"){
				var statusNamesArr=[];
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
				 var wardArr = [];
				 var gmcArr = [];
				 var clusterArr = [];
				 
				for(var i in result){
					statusNamesArr.push(result[i].name)
						if(result[i].subList2 !=null &&  result[i].subList2.length>0){
						for(var j in result[i].subList2){
							if(result[i].subList2[j].id==1){
								 stateArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==2){
								 goneArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==3){
								 regionArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==4){
								 circleArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==5){
								 districtArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==6){
								 divisionArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==7){
								 subDivisionArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==8){
								 mandalArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==9){
								 municipalityArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==10){
								 panchayatArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList[j].id==11){
								 wardArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList[j].id==12){
								 gmcArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList[j].id==13){
								 clusterArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							} 
						 	
						}
					
					}
			
			
			
					var mainJosnObjArr = [];
					   if(stateArr != null && stateArr.length > 0){
						mainJosnObjArr.push({name:'State',data:stateArr,color:"#957ADB"});  
					  }
					   if(goneArr != null && goneArr.length > 0){
						mainJosnObjArr.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
					  }
					  if(regionArr != null && regionArr.length > 0){
						mainJosnObjArr.push({name:'Region',data:regionArr,color:"#0065FE"});  
					  }
					  if(circleArr != null && circleArr.length > 0){
						mainJosnObjArr.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
					  }
					  if(districtArr != null && districtArr.length > 0){
						mainJosnObjArr.push({name:'District',data:districtArr,color:"#FE6603"});  
					  }
					  if(divisionArr != null && divisionArr.length > 0){
						mainJosnObjArr.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
					  }
					  if(subDivisionArr != null && subDivisionArr.length > 0){
						mainJosnObjArr.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
					  }
					   if(mandalArr != null && mandalArr.length > 0){
						mainJosnObjArr.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
					  }
					   if(municipalityArr != null && municipalityArr.length > 0){
						mainJosnObjArr.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
					  }
					   if(panchayatArr != null && panchayatArr.length > 0){
						mainJosnObjArr.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
					  } 
					  if(wardArr != null && wardArr.length > 0){
						mainJosnObjArr.push({name:'Ward',data:wardArr,color:"#975955"});  
					  } 
					  if(gmcArr != null && gmcArr.length > 0){
						mainJosnObjArr.push({name:'GMC',data:gmcArr,color:"#A05955"});  
					  } 
					  if(clusterArr != null && clusterArr.length > 0){
						mainJosnObjArr.push({name:'CLUSTER',data:clusterArr,color:"#06A247"});  
					  } 
		
				}
			}else if(selectedLevelType == "alertCategory"){
				 var statusNamesArr=[];
				 var manualArr=[];
			     var printMediaArr = [];
				 var electronicMediaArr = [];
				 var callCenterArr = [];
				 var socialMediaArr = [];
				 
				for(var i in result){
					
					 statusNamesArr.push(result[i].name)
					
					if(result[i].subList2 !=null &&  result[i].subList2.length>0){
						for(var j in result[i].subList2){
								
							 if(result[i].subList2[j].id==1){
								 manualArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count}); 
							}else if(result[i].subList2[j].id==2){
								 printMediaArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==3){
								 electronicMediaArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==4){
								 callCenterArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
							}else if(result[i].subList2[j].id==5){
								 socialMediaArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
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
					mainJosnObjArr.push({name:'Social Media',data:socialMediaArr,color:"#00ABED"});  
				  }
				  
			
				}
			}
			
			var heightOfDiv = statusNamesArr.length ;
				if(heightOfDiv >25){
					heightOfDiv = heightOfDiv * 36;
					$("#"+divId).css("height",heightOfDiv);
				}else{
					$("#"+divId).css("height","auto");
				}
				
			$("#"+divId).highcharts({
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
										var departmentId = value[0];
										var levelId ='';
										var statusId=0;
										var statusName = value[2];
										var totalCount = value[3];
										var alertCategoryId = '';
										if(selectedLevelType == "alertCategory"){
											alertCategoryId = value[1];
											levelId = locationLevelId;
										}else{
											alertCategoryId = 0;
											levelId = value[1];
										}
										
										getTotalAlertCountDetails(departmentId,levelId,statusId,statusName,totalCount,alertCategoryId);
										
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
			
				 $.each($("#"+divId).find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");
					if(selectedLevelType == "alertCategory"){
						$(this).attr("onclick","getTotalAlertCountDetails(\'"+result[index].id+"\',\'"+locationLevelId+"\',0,\'"+result[index].name+"\',\'"+result[index].count+"\',0)");
					}else{
						$(this).attr("onclick","getTotalAlertCountDetails(\'"+result[index].id+"\',0,0,\'"+result[index].name+"\',\'"+result[index].count+"\',0)");
					}	
					 	
					
				});	
				
		}else{
			$("#"+divId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#"+divId).css("height","25px"); 
		}
	}
	
	function getTotalAlertCountDetails(departmentId,levelId,statusId,name,totalCount,alertCategoryId){
		$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		if(statusId !=0){
			getFilterSectionAlertDetails(name,totalCount,globalDepartmentIdsArr,statusId,departmentId,'mainAlert');
		}else{
			getFilterSectionAlertDetails(name,totalCount,globalDepartmentIdsArr,'',departmentId,'');
		}
		
		
		var alertType = getAlertType();
		var statusIdsArr = [];
		if(alertType != null && alertType=="alert"){
			   if(statusId != null && statusId == 0){
				 statusIdsArr = globalAlertStatusIdsArr;
			  }else{
				statusIdsArr.push(statusId);
			  }
		  }else if(alertType=="subTask"){
			   if(statusId != null && statusId == 0){
				 statusIdsArr = globalAlertSubTaskStatusIdsArr;
			  }else{
				statusIdsArr.push(statusId);
			  } 
		  }
	  
		
		var jObj = {
			deptId: departmentId,
			levelId: levelId,
			alertStatusIdsArr:statusIdsArr,
			type:alertType,
			startDate:currentFromDate,
			endDate:currentToDate,
			desigDeptOfficerId:0,
			officerId:0,
			paperIdArr:newspapersGlobalArr,
			chanelIdArr:channelGlobalArr,
			callCenterArr:callCenterGlobalArr,
			alertCategoryId:alertCategoryId,
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
			alertSeverityIdsArr:globalAlertSeverityIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
			janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
			specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
			generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
		}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,name,totalCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
	
}
$(document).on("click",".alertType-subordinate li",function(){
	$(this).parent("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var alertType = getAlertTypeForSubordinate();  
	getBellowDistrictOfficerAlertsCountView(alertType);
});
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
			sortingType:"Descending",
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
			alertSeverityIdsArr:globalAlertSeverityIdsArr,
            alertStatusIdsArr:globalAlertStatusIdsArr,
            alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
			janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
			specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
			generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
		}
		$.ajax({
			type:'GET',
			url: 'getBellowDistrictOfficerAlertsCountViewAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			$("#subordinateAlertsDivId").html('');
			if(result != null && result.length > 0){
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
function getAlertTypeForSubordinate(){
	var alertType = ''; 
	$('.alertType-subordinate li').each(function(i, obj){
		if($(this).hasClass('active')){
			alertType = $(this).attr("attr_type");
		}    
	});
	return alertType;
}
function getBellowDistrictOfficerAlertsDtls(designationOfficerId,officerId,officerName,totalCount,statusId){
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','','');
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
			callCenterArr:callCenterGlobalArr,
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
			alertSeverityIdsArr:globalAlertSeverityIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
			janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
			specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
			generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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

$(document).on("click",".gettotalAlertsForSubordinateCls",function(){
	var designationId = $(this).attr("attr_desig_id");
	var officerId = $(this).attr("attr_officer_id");
	var officerName = $(this).attr("attr_officer_name");
	var totalCount = $(this).attr("attr_total_count");
	getBellowDistrictOfficerAlertsDtls(designationId,officerId,officerName,totalCount,0);
});

function getDepartmentDetailsByDepartment(departmentId,departmentName,departmentType,alertType){ 
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
       buildDepartmentDetailsByDepartmentss(result,departmentId,departmentName,departmentType,alertType)
    }); 
}


function buildDepartmentDetailsByDepartmentss(result,departmentId,departmentName,departmentType,alertType){
		
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
						str+='<h4 class="panel-title text-capital fontColor">Department Scope Wise Alerts - '+departmentName+'</h4>';
					str+='</div>';
				str+='<div class="panel-body" >';
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
					str+='</div>';
			}
			
		
		
		$("#departmentWiseLocationBlockId").html(str);
		   
		var deptObj = result[0];
		
		 if(deptObj.subList1 != null && deptObj.subList1.length > 0){
			 for(var i in deptObj.subList1){
				getStateThenGovtDeptScopeWiseAlertCount(deptObj.id,deptObj.subList1[i].id,departmentType,alertType,"levelWiseGraphView","count","desc",0,0,"Default",0);
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

$(document).on("click",".locationAndStatusWiseSorting li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var parentId = $(this).attr("attr_parent_id");
		
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var searchType = getSelectedType().departmentType;
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
		var searchType = getSelectedType().departmentType;
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
		filterScopeValue:filterScopeValue,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		alertSeverityIdsArr:globalAlertSeverityIdsArr,
        alertStatusIdsArr:globalAlertStatusIdsArr,
        alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
		$(".locationLevelWiseDivCls").show();
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
				 var mondayGrievanceArr=[];
				 var janmabhoomiArr=[];
				 var specialGrievanceArr=[];
				 var generalGrievanceArr=[];
				 
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
							}else if(result[i].subList[j].id==6){
								 mondayGrievanceArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}else if(result[i].subList[j].id==7){
								 janmabhoomiArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}else if(result[i].subList[j].id==8){
								 specialGrievanceArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
							}else if(result[i].subList[j].id==9){
								 generalGrievanceArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id+"-"+result[i].stateId});
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
					mainJosnObjArr.push({name:'Social Media',data:socialMediaArr,color:"#00ABED"});  
				  }
				  if(mondayGrievanceArr != null && mondayGrievanceArr.length > 0){
					mainJosnObjArr.push({name:'Monday Grievance',data:mondayGrievanceArr,color:"#FA8072"});  
				  }
				   if(janmabhoomiArr != null && janmabhoomiArr.length > 0){
					mainJosnObjArr.push({name:'Janmabhoomi',data:janmabhoomiArr,color:"#00FF00"});  
				  }
				   if(specialGrievanceArr != null && specialGrievanceArr.length > 0){
					mainJosnObjArr.push({name:'Special Grievance',data:specialGrievanceArr,color:"#0000FF"});  
				  }
				   if(generalGrievanceArr != null && generalGrievanceArr.length > 0){
					mainJosnObjArr.push({name:'General Grievance',data:generalGrievanceArr,color:"#808000"});  
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
		$(".locationLevelWiseDivCls").hide();
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
				 var wardArr = [];
				 var gmcArr = [];
				 var clusterArr = [];
				 
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
						}else if(result[i].subList[j].id==11){
							 wardArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 gmcArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==13){
							 clusterArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
			  if(wardArr != null && wardArr.length > 0){
				mainJosnObjLocArr.push({name:'Ward',data:wardArr,color:"#975955"});  
			  } 
			  if(gmcArr != null && gmcArr.length > 0){
				mainJosnObjLocArr.push({name:'GMC',data:gmcArr,color:"#A05955"});  
			  } 
			  if(clusterArr != null && clusterArr.length > 0){
				mainJosnObjLocArr.push({name:'CLUSTER',data:clusterArr,color:"#06A247"});  
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
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+locationLevelId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',1,\'"+parentGovtDepartmentScopeId+"\',0,0)");	
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
		$(".locationLevelWiseDivCls").hide();
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
		var searchType = getSelectedType().departmentType;
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
		  subLevelArr:subLevelArr,
		  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		  alertSeverityIdsArr:globalAlertSeverityIdsArr,
          alertStatusIdsArr:globalAlertStatusIdsArr,
          alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		  mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	      specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	      generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
			alertSeverityIdsArr:globalAlertSeverityIdsArr,
            alertStatusIdsArr:globalAlertStatusIdsArr,
            alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		    janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	        specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	        generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
	//alert(locationLevelId)
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
		if(statusId !=0){
			getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,statusId,departmentId,'mainAlert');
		}else{
			getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'',departmentId,'');
		}
       var alertType = getAlertType();
     
	  var statusIdsArr = [];
	  if(alertType != null && alertType=="alert"){
		   if(statusId != null && statusId == 0){
			 statusIdsArr = globalAlertStatusIdsArr;
		  }else{
			statusIdsArr.push(statusId);
		  }
	  }else if(alertType=="subTask"){
		   if(statusId != null && statusId == 0){
			 statusIdsArr = globalAlertSubTaskStatusIdsArr;
		  }else{
			statusIdsArr.push(statusId);
		  } 
	  }
	 
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentGovtDepartmentScopeId,
		deptScopeId : levelId,    
		alertStatusIdsArr:statusIdsArr, 
		callCenterArr:callCenterGlobalArr,
		locationValue : locationValue,
		alertType:alertType,
		alertCategoryId:alertCategoryId,
		subLevels:locationLevelIdClickArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		alertSeverityIdsArr:globalAlertSeverityIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
		
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
/* Financial Assistance Block Start */

function getFinancialAssistanceAlertCntCategoryWise(globalDepartmentIdsArr)
{
	 
	$("#financialAssistanceDetilsDivId").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr : channelGlobalArr,
	  callCenterArr : callCenterGlobalArr,
	  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
	  alertSeverityIdsArr:globalAlertSeverityIdsArr,
      alertStatusIdsArr:globalAlertStatusIdsArr,
      mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
	  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	  specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	  generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
    }
    $.ajax({
      type:'GET',
      url: 'getFinancialAssistanceAlertCntCategoryWiseAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
	    $("#financialAssistanceDetilsDivId").html('');
	   if(result !=null && result.length>0){
		   buildFinancialAssistanceDetails(result);
	   }else{
		   $("#financialAssistanceDetilsDivId").html('No Data Available.');
	   }
    });
}
function buildFinancialAssistanceDetails(result)
{
	var str='';
	var totalAlert = 0;
	var totalfar=0;
	var approvedAmount=0;
	str+='<div class="row">';
		str+='<div class="col-md-2 col-xs-12 col-sm-4">';
			str+='<div id="finanicialAssGraphView" ></div>';
		str+='</div>';
	
		 str+='<div class="col-md-2 col-xs-12 col-sm-4" style="margin-top:30px">';
			str+='<div class="scrollerDivCls">';
				str+='<table class="table tableGraph">';
					
					str+='<tbody>';
						for(var i in result)
						{	
							totalAlert+=result[i].alertCnt;
							totalfar = result[0].count;
							if(result[i].id == 1){
								if(result[i].subList2 !=null && result[i].subList2.length>0){
									for(var j in result[i].subList2){
										if(result[i].subList2[j].id == 3){
											approvedAmount = result[i].subList2[j].proposalAmount;
										}
									}
									
								}
							}
							
							
							str+='<tr>';
								str+='<td><span class="label" style="background-color:'+globalFinancialAssColorObj[result[i].name.trim()]+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
								str+='<td style="cursor:pointer;" onclick="getFinancialAssistanceAlertCntDtls(\''+result[i].name+'\','+result[i].alertCnt+','+result[i].id+',0);" class="alertSourceCls" attr_alert_source_name="'+result[i].name+'" attr_alert_count="'+result[i].alertCnt+'" attr_source_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='</tr>';
						}
					str+='</tbody>';  
				str+='</table>';
			str+='</div>';
		str+='</div>'; 
		str+='<div class="col-md-8 col-xs-12 col-sm-4" style="box-shadow: -3px 0 3px 3px rgba(0, 0, 0, 0.4);">';
		str+='<div class="scollerDivFinancialAss">';
			str+='<div id="financialAssbarGraphView" ></div>';
		str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-12 m_top10">';
				str+='<h4><span><span style="font-size: 16px;">TOTAL PROPOSAL</span> - <span onclick="getFinancialAssistanceAlertCntDtls(\'PROPOSAL\','+totalAlert+',0,0);" style="cursor:pointer;">'+totalAlert+'</span></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><span style="font-size: 16px;"> FAR</span> - <i class="fa fa-inr" aria-hidden="true"></i>'+totalfar+'/-</span>&nbsp;&nbsp;&nbsp; <span style="font-size: 16px;"> APPROVED AMOUNT</span> - <i class="fa fa-inr" aria-hidden="true"></i>'+approvedAmount+'/-</span></h4>';
		str+='</div>';
	str+='</div>';
	$("#financialAssistanceDetilsDivId").html(str);
	
	var financialMainArr =[];
	if(result.length > 7)
	{
		$(".scrollerDivCls").mCustomScrollbar({setHeight:'300px'});
	}
	for(var i in result)
	{
		
		financialPercent = result[i].percentage;
		financialName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = globalFinancialAssColorObj[result[i].name.trim()];
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: financialName,
			y:cnt,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		financialMainArr.push(obj);
	}
	
	
		$("#finanicialAssGraphView").highcharts({
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - <br/>"+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
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
				data: financialMainArr
			}]
		});
	
		if(result !=null  && result.length>0){
				var financialNamesArr=[];
				var proposalPendingArr=[];
				var proposalRejectedArr = [];
				var proposalAcceptArr = [];
				
				
			for(var i in result){
				
				 financialNamesArr.push(result[i].name)
				if(result[i].subList2 !=null && result[i].subList2.length>0){
					for(var j in result[i].subList2){
						if(result[i].subList2[j].id==1){
								proposalPendingArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id}); 
							}else if(result[i].subList2[j].id==2){
								 proposalRejectedArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}else if(result[i].subList2[j].id==3){
								 proposalAcceptArr.push({"y":result[i].subList2[j].alertCnt,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].alertCnt+"-"+result[i].id});
							}
					}
				
				var mainFinancialJosnObjArr = [];
					   if(proposalPendingArr != null && proposalPendingArr.length > 0){
						mainFinancialJosnObjArr.push({name:'Proposal Pending',data:proposalPendingArr,color:"#FEA723"});  
					  }
					   if(proposalRejectedArr != null && proposalRejectedArr.length > 0){
						mainFinancialJosnObjArr.push({name:'Proposal Rejected',data:proposalRejectedArr,color:"#F15A25"});  
					  }
					  if(proposalAcceptArr != null && proposalAcceptArr.length > 0){
						mainFinancialJosnObjArr.push({name:'Proposal Accept',data:proposalAcceptArr,color:"#82CA9C"});  
					  }
					  
				}
			}
				var heightOfDiv = financialNamesArr.length ;
				if(heightOfDiv >7){
					$(".scollerDivFinancialAss").mCustomScrollbar({setHeight:'300px'})
				}else{
					$("#financialAssbarGraphView").css("height","auto");
					$(".scollerDivFinancialAss").removeAttr('style')
					$(".scollerDivFinancialAss").mCustomScrollbar('destroy');
				  }
				$('#financialAssbarGraphView').highcharts({
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
						categories: financialNamesArr
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
										var categoryId=value[3]; 
										
										getFinancialAssistanceAlertCntDtls(statusName,totalCount,categoryId,statusId);
										
									}
								}
							}
				        }
					},
					legend: {
						verticalAlign:'top',
						enabled: true
					},
					series: mainFinancialJosnObjArr
				});
		
			
			
				 $.each($('#financialAssbarGraphView').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;"); 
						$(this).attr("onclick","getFinancialAssistanceAlertCntDtls(\'"+result[index].name+"\',\'"+result[index].alertCnt+"\',\'"+result[index].id+"\',0)");
					
				
				});
		}
}
function getFinancialAssistanceAlertCntDtls(statusName,totalCount,categoryId,statusId)
{
	 
		$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr);
   
	  
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr : channelGlobalArr,
	  callCenterArr : callCenterGlobalArr,
	  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
	  alertSeverityIdsArr:globalAlertSeverityIdsArr,
      alertStatusIdsArr:globalAlertStatusIdsArr,
      mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
	  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	  specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	  generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr,
	  propasalCategoryId:categoryId,
	  propasalStatusId:statusId
    }
    $.ajax({
      type:'GET',
      url: 'getFinancialAssistanceAlertCntDtlsAction.action',
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
/* Financial Assistance Block End  */