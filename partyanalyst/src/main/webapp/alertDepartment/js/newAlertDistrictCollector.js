var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var globalAlertSourceColorObj =  {"Manual":"#E54BB3","Print Media":"#69BC6E","Electronic Media":"#8D69C8","Call Center":"#EFC000","Facebook":"#00ABED","Twitter":"#F7776C","Social Media":"#05ABHY"};	 


	function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getLevelType(){
		 var levelType = ''; 
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  levelType = $(this).attr("attr_type");
			 }
		});
		return levelType;
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
			var newsPaperIdLen = paperIdArr.length;
			var channelIdLen = chanelIdArr.length;
			var callCenterIdLen = callCenterArr.length;
			if(newsPaperIdLen == 0 && channelIdLen == 0 && callCenterIdLen == 0){
				alert("Please Select Atleast One Option.");   
				return;
			}
		 var alertType = getAlertType();
		 $(".documentCloseClass").hide();
		 getDistrictOfficerAlertsCountView();
		 getGovtDepartmentDetails();
		 
	}

	
	function getDistrictOfficerAlertsCountView(){
		$("#myAlertsDivID").html(spinner);
		$("#mySubTasksDivID").html(spinner);
		$("#assignedSubTasksDivID").html(spinner);
		var jObj ={
		  startDate:currentFromDate,
		  endDate:currentToDate,
		  paperIdArr:paperIdArr,
		  chanelIdArr:chanelIdArr,
		  callCenterArr:callCenterArr
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
				str+='<p class="pad_5 todayCountCls" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count= "'+result.list1[0].todayCount+'" attr_alert_type="alert">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
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
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt="'+totalCoutAlertIds+'" attr_name="TODAY" attr_total_count="'+result.list2[0].todayCount+'" attr_alert_type="mySubTasks">TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].todayCount+'</span></p>';
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
				str1+='<p class="pad_5 overAllCount" attr_overCunt="'+overAllAlertIds+'" attr_name="OVERALL" attr_total_count="'+result.list2[0].overAllCnt+'" attr_alert_type="mySubTasks">OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].overAllCnt+'</span></p>';
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
	
	var jObj = {
		govtDepDesigOffcrIds : globalgovtDeptDesigOffcrIds,
		govtOfficerIds : globalgovtOfficerIds,
		countType: "overAll",
		alertType : alertType,
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		callCenterArr:callCenterArr,
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
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	var alertType = $(this).attr("attr_alert_type");
	
	var jObj = {
		govtDepDesigOffcrIds : globalgovtDeptDesigOffcrIds,
		govtOfficerIds : globalgovtOfficerIds,
		countType: "today",
		alertType : alertType,
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		callCenterArr:callCenterArr,
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

function getDistrictLevelDeptWiseAlertClick(StatusId,name,totalCount,clickType)
{
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
		
		
		var jsObj = {
			govtDepDesigOffcrIds : globalgovtDeptDesigOffcrIds,
			govtOfficerIds : globalgovtOfficerIds,
			statusId : StatusId,
			formDate:currentFromDate, 
			toDate: currentToDate,
			clickType:clickType, //
			paperIdArr:paperIdArr,
			chanelIdArr:chanelIdArr,
			callCenterArr:callCenterArr
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

function getAlertSourceWiseAlert(departmentIdsForAlertSoutceArr)
{
	 
	$("#alertSourceWiseDetilsDivId").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
	  deptIdArr : departmentIdsForAlertSoutceArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  callCenterArr : callCenterArr,
	  userType :"districtCollector"
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
function getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,alertStatusId){
	
	$("#alertManagementPopupBody").html('')
	
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
      deptIdArr : departmentIdsForAlertSoutceArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  callCenterArr : callCenterArr,
	  alertCategoryId:alertCategoryId,
	  userType :"districtCollector",
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
function getGovtDepartmentDetails(){
		$("#govtDepartmentsId").html('');
		$.ajax({
		  type:'GET',
		  url: 'getGovtDepartmentDetailsAction.action',
		  data: {}
		}).done(function(result){
			if(result !=null && result.length>0){
				departmentIdsForAlertSoutceArr=[];
				$("#govtDepartmentsId").append('<option value="0">Select Department</option>');
				for(var i in result){
					departmentIdsForAlertSoutceArr.push(result[i].id);
					$("#govtDepartmentsId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
			var alertType = getAlertType();
			getAlertSourceWiseAlert(departmentIdsForAlertSoutceArr);
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
			 deptArr = departmentIdsForAlertSoutceArr;
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
			deptArr = departmentIdsForAlertSoutceArr
		}
		var jObj = {
			startDate: currentFromDate,
			fromDate: currentToDate,
			type:alertType,
			deptArr:deptArr,
			sortingType:sortingType,
			levelId:levelId,
			paperIdArr:paperIdArr,
			chanelIdArr:chanelIdArr,
			callCenterArr:callCenterArr
			
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
	$("#govtDepartmentsId").val(0);
	$( "#locationLevelNamesId" ).val(0);
	if(selectedLevelType == "status"){
		$(".locationLevelNamesCls").show();
		getGovtDeptScopeDetails(0);
		getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,0,0,"departmentWiseGraphViewDetails"); 
	}else if(selectedLevelType == "scopeLevel"){
		$(".locationLevelNamesCls").hide();
		getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,0,selectedLevelType,0,"departmentWiseGraphViewDetails");
	}else if(selectedLevelType == "alertCategory"){
		$(".locationLevelNamesCls").show();
		getGovtDeptScopeDetails(0);
		getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,0,selectedLevelType,0,"departmentWiseGraphViewDetails");
	}
			
			
});
$(document).on("click",".switch-btn-alertType li",function(){
	$(this).parent("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var alertType = $(this).attr("attr_type");
	var selectedLevelType = getLevelType();
	var sortingType = getSortingType();
	$("#govtDepartmentsId").val(0);
	if(selectedLevelType == "status"){
		$(".locationLevelNamesCls").show();
		getGovtDeptScopeDetails(0);
		getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,0,0,"departmentWiseGraphViewDetails"); 
	}else if(selectedLevelType == "scopeLevel"){
		$(".locationLevelNamesCls").hide();
		getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,0,selectedLevelType,0,"departmentWiseGraphViewDetails");
	}else if(selectedLevelType == "alertCategory"){
		$(".locationLevelNamesCls").show();
		getGovtDeptScopeDetails(0);
		getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,0,selectedLevelType,0,"departmentWiseGraphViewDetails");
	}
});

$(document).on("click",".sortingDivCls li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var sortingType = $(this).attr("attr_sorting_type");
			var selectedLevelType = getLevelType();
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
			var selectedLevelType = getLevelType();
			var alertType = getAlertType();
			var sortingType = getSortingType();
			var departmentId = $(this).val();
			//var locationLevelId = $("#locationLevelNamesId").val();
			 var locationLevelId = 0;
			if(selectedLevelType == "status"){
				getGovtDeptScopeDetails(departmentId);
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,locationLevelId,"departmentWiseGraphViewDetails"); 
			}else if(selectedLevelType == "scopeLevel"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,0,"departmentWiseGraphViewDetails");
			}else if(selectedLevelType == "alertCategory"){
				getGovtDeptScopeDetails(departmentId);
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId,selectedLevelType,locationLevelId,"departmentWiseGraphViewDetails");
			}
});	
		
		$(document).on("change","#locationLevelNamesId",function(){
			var locationLevelId = $(this).val();
			var departmentId = $("#govtDepartmentsId").val();
			var alertType = getAlertType();
			var selectedLevelType = getLevelType();
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
		deptArr = departmentIdsForAlertSoutceArr
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
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		callCenterArr:callCenterArr,
		resultType:selectedLevelType,
		subLevelArr:locationLevelIdArr,
		
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
					mainJosnObjArr.push({name:'Social Media',data:socialMediaArr,color:"#05ABHY"});  
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
		var alertType = getAlertType();
		var jObj = {
			deptId: departmentId,
			levelId: levelId,
			statusId:statusId,
			type:alertType,
			startDate:currentFromDate,
			endDate:currentToDate,
			desigDeptOfficerId:0,
			officerId:0,
			paperIdArr:paperIdArr,
			chanelIdArr:chanelIdArr,
			callCenterArr:callCenterArr,
			alertCategoryId:alertCategoryId
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