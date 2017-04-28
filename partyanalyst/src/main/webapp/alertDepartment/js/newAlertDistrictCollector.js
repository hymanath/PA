var overAllAlertIds =[];
var totalCoutAlertIds =[];
var globalUserLevelId;
var globalUserLevelValues = [];	
var globalgovtDeptDesigOffcrId;
var	globalgovtOfficerId;
var globalDesignationId;
var globalDepartmentId;
var paperIdArr = [];
var chanelIdArr = [];
var callCenterArr = [];
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");

onLoadCallsAMU();

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
		
		onLoadClicks();
		var alertType = getAlertType();
		getDistrictOfficerAlertsCountView();
		getDistrictLevelDeptWiseLocationLevelView(alertType,"Decending",0);
		getGovtDepartmentDetails();
		getGovtDeptScopeDetails();
	}
	function getGovtDepartmentDetails(){
		$("#govtDepartmentsLocId").html('');
		$.ajax({
		  type:'GET',
		  url: 'getGovtDepartmentDetailsAction.action',
		  data: {}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#govtDepartmentsLocId").append('<option value="0">Select Department</option>');
				for(var i in result){
					$("#govtDepartmentsLocId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});
		
	}
	function getGovtDeptScopeDetails(){
		$("#districtWiseLevelLocId").html('');
		$.ajax({
		  type:'GET',
		  url: 'getGovtDeptScopeDetailsAction.action',
		  data: {}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#districtWiseLevelLocId").append('<option value="0">Select Level</option>');
				for(var i in result){
					$("#districtWiseLevelLocId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});
		
	}
	function onLoadClicks()
	{
		$(document).on("click",".switch-btn li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var type= $(this).attr("attr_type");
			var alertType = getAlertType();
			var sortingType = getSortingType();
			var departmentId = $( "#govtDepartmentsLocId option:selected" ).val();
			if(type == 'filter')
			{
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").show();
				$(".sortingDivClsDept").hide();
				$(".sortingDivClsLevel").hide();
				getDistrictLevelDeptWiseFilterViewDetails(alertType);
			}else if(type == "status"){
				$(".sortingDivClsDept").show();
				$(".sortingDivClsLevel").show();
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").show();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(type == "location"){
				$(".sortingDivClsLevel").hide();
				$(".sortingDivClsDept").show();
				$(".departmentlocationShow").show();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
		});
		$(document).on("click",".switch-btn-alertType li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var alertType = $(this).attr("attr_type");
			var levelType = getLevelType();
			var sortingType = getSortingType();
			var departmentId = $( "#govtDepartmentsLocId option:selected" ).val();
			if(levelType == 'filter')
			{
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").show();
				$(".sortingDivCls").hide();
				getDistrictLevelDeptWiseFilterViewDetails(alertType);
			}else if(levelType == "status"){
				$(".sortingDivCls").show();
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").show();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(levelType == "location"){
				$(".sortingDivCls").show();
				$(".departmentlocationShow").show();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
		});
		$(document).on("click",".locationWiseSorting li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var sortingType = $(this).attr("attr_sorting_type");
			var levelType = getLevelType();
			var alertType = getAlertType();
			var departmentId = $( "#govtDepartmentsLocId option:selected" ).val();
			
			if(levelType == "status"){
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(levelType == "location"){
				
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
			
		});
		$(document).on("change",".locationWiseDeptOnChange",function(){
			var levelType = getLevelType();
			var alertType = getAlertType();
			var sortingType = getSortingType();
			var departmentId = $(this).val();
			var levelId = $("#districtWiseLevelLocId").val();
			if(levelType == "status"){
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,levelId); 
			}else if(levelType == "location"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
		});	
		
		$(document).on("change","#districtWiseLevelLocId",function(){
			var levelId = $(this).val();
			var departmentId = $("#govtDepartmentsLocId").val();
			getDistrictLevelDeptWiseStatusOverView("alert","Decending",departmentId,levelId); 
		});
		
		
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
    //  userId:userId,
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
			globalgovtDeptDesigOffcrId = result.govtDeptDesigOffcrIds[0];
			globalgovtOfficerId = result.govtOfficerIds[0];
			globalDesignationId = result.designationId;
			buildDistrictOfficerAlertsCountView(result);
		}
    });
}

function buildDistrictOfficerAlertsCountView(result){
	
	if(result !=null){
		globalUserLevelId = result.levelId;
		if(result.levelValues != null && result.levelValues.length > 0)
			globalUserLevelValues=result.levelValues;
	}
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
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"mySubTasks");
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

function getDistrictLevelDeptWiseFilterViewDetails(alertType){
	$("#departmentAlertCountDivId").html(spinner);
	
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFilterViewDetailsAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
			$("#departmentAlertCountDivId").html('');
			buildDistrictLevelDeptWiseFilterViewDetails(result);
	});

}

function buildDistrictLevelDeptWiseFilterViewDetails(result){
	
	if(result != null && result.length > 0){	
		var str1='';
		str1+='<table class="table detailedTableStyle" id="departmentAlertCountDT">';
			str1+='<thead>';
				str1+='<tr class="text-capital">';
				str1+='<th>Department</th>';
				str1+='<th>Total Alerts</th>';
					if(result[0].subList2 !=null && result[0].subList2.length>0){
						
						for(var j in result[0].subList2){
							str1+='<th>'+result[0].subList2[j].name+'</th>';
							
						}
						
					}
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td>'+result[i].name+'</td>';
						str1+='<td class="distWiseDeptFilterCls" attr_dept_name="'+result[i].name+'" attr_dept_id="'+result[i].id+'" attr_level_id="0" attr_status_id = "0" attr_count = "'+result[i].categoryCount+'" style="cursor:pointer;" >'+result[i].categoryCount+'</td>';
						if(result[i].subList2 !=null && result[i].subList2.length>0){
							
							for(var j in result[i].subList2){
								str1+='<td class="distWiseDeptFilterCls" attr_dept_name="'+result[i].name+'" attr_dept_id='+result[i].id+' attr_level_id='+result[i].subList2[j].id+'  attr_count ="'+result[i].subList2[j].count+'" attr_status_id = "0" style="cursor:pointer;">'+result[i].subList2[j].count+'</td>';
								
							}
						}
					str1+='</tr>';
				}
			str1+='</tbody>';
		str1+='</table>';
		$("#departmentAlertCountDivId").html(str1);
		
	}else{
		$("#departmentAlertCountDivId").html("No Data Available");
	}
		$("#departmentAlertCountDT").dataTable({
			"lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]]
		});
		
		
	}
	
function getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId){
	
	$("#departmentlocationCountDivId").html(spinner);
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptId:departmentId,
		sortingType:sortingType,
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		callCenterArr:callCenterArr
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseLocationLevelViewAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#departmentlocationCountDivId").html('');
		if(result !=null && result.length>0){
			buildDistrictLevelDeptWiseLocationLevelView(result);
		}else{
			$("#departmentlocationCountDivId").html("No Data Available");
		}
		
	});
}

function buildDistrictLevelDeptWiseLocationLevelView(result){
		if(result !=null && result.length>0){
			
			var locationNamesArrDistrictOverView=[];
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
				 locationNamesArrDistrictOverView.push(result[i].name)
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
						}
						else if(result[i].subList2[j].id==8){
							 mandalArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
						}else if(result[i].subList2[j].id==9){
							 municipalityArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
						}else if(result[i].subList2[j].id==10){
							 panchayatArr.push({"y":result[i].subList2[j].count,"extra":result[i].id+"-"+result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count});
						}
						
						
						
					}
					
				}
			
			
			
			var mainJosnObjArrDistrictOverview = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		
			
			var heightOfDiv = locationNamesArrDistrictOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#departmentlocationCountDivId").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'departmentlocationCountDivId';
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
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
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
										var departmentId = value[0];
										var levelId = value[1];
										var levelName = value[2];
										var totalCount = value[3];
										var statusId=0;
										getTotalAlertCountDetails(departmentId,levelId,statusId,levelName,totalCount);
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
				$.each($('#departmentlocationCountDivId').find(".highcharts-xaxis-labels").find("tspan"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","gettotalLocCls");    
					$(this).attr("attr_department_id",result[index].id);         
					$(this).attr("attr_department_name",result[index].name);	
					$(this).attr("attr_total_count",result[index].count);
					
				}); 
		}else{
			$("#departmentlocationCountDivId").html('No Data Available');
		}
}

function getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,levelId){
	$("#departmentStatusCountDivId").html(spinner);
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptId:departmentId,
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
		$("#departmentStatusCountDivId").html('');
		if(result !=null && result.length>0){
			buildDistrictLevelDeptWiseStatusOverView(result,levelId);
		}else{
			$("#departmentStatusCountDivId").html('<div class="col-xs-12">NO DATA AVAILABLE</div>');
		}
		
	});
}
function buildDistrictLevelDeptWiseStatusOverView(result,levelId){
	
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
				$("#departmentStatusCountDivId").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrict
				var id = 'departmentStatusCountDivId';
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
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDistrict
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
										var departmentId = value[0];
										var levelId = value[4];
										var statusName = value[2];
										var totalCount = value[3];
										var statusId=value[1];
										getTotalAlertCountDetails(departmentId,levelId,statusId,statusName,totalCount);
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
				$.each($('#departmentStatusCountDivId').find(".highcharts-xaxis-labels").find("tspan"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","gettotalLocCls");    
					$(this).attr("attr_department_id",result[index].id);         
					$(this).attr("attr_department_name",result[index].name);	
					$(this).attr("attr_total_count",result[index].count);
					
				}); 
		}else{
			$("#departmentStatusCountDivId").html('No Data Available');
		}
}
$(document).on("click",".overAllCount",function(){
	$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var paperIdArr = [];
		var chanelIdArr = [];
		var callCenterArr = [];
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count");
	var alertType = $(this).attr("attr_alert_type");
	//alertIdArr=($(this).attr("attr_overcunt").split(','));
	var jObj = {
		govtDepDesigOffcrId : globalgovtDeptDesigOffcrId,
		govtOfficerId : globalgovtOfficerId,
		countType: "overAll",
		alertType : alertType,
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		callCenterArr:callCenterArr		
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
		var paperIdArr = [];
		var chanelIdArr = [];
		var callCenterArr = [];
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	//alertIdArr.push(parseInt($(this).attr("attr_todayCunt")));
	var alertType = $(this).attr("attr_alert_type");
	var jObj = {
		govtDepDesigOffcrId : globalgovtDeptDesigOffcrId,
		govtOfficerId : globalgovtOfficerId,
		countType: "today",
		alertType : alertType,
		paperIdArr:paperIdArr,
		chanelIdArr:chanelIdArr,
		callCenterArr:callCenterArr
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

//click functioality...
function getTotalAlertCountDetails(departmentId,levelId,statusId,name,totalCount){
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
			callCenterArr:callCenterArr
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

$(document).on("click",".gettotalLocCls",function(){ 
	var departmentId = $(this).attr("attr_department_id");
	var departmentName = $(this).attr("attr_department_name");
	var totalCount = $(this).attr("attr_total_count");
	var selectedLevelId = $("#districtWiseLevelLocId").val();
	getTotalAlertCountDetails(departmentId,selectedLevelId,0,departmentName,totalCount);
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
		
		var paperIdArr = [];
		var chanelIdArr = [];
		var callCenterArr = [];
		
		var jsObj = {
		govtDeptDesigOffceId : globalgovtDeptDesigOffcrId,
		govtOffceId : globalgovtOfficerId,
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