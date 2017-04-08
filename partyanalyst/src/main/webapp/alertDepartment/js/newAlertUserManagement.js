var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
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
		var alertType = getAlertType();
		getDistrictOfficerAlertsCountView();
		getDistrictLevelDeptWiseLocationLevelView(alertType);
	});
	
	function onLoadCallsAMU(){
		var alertType = getAlertType();
		getDistrictOfficerAlertsCountView();
		getDistrictLevelDeptWiseLocationLevelView(alertType);
	}
	
	$(document).on("click",".switch-btn li",function(){
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var type= $(this).attr("attr_type");
		if(type == 'filter')
		{
			$(".departmentlocationShow").hide();
			$(".departmentStatusShow").hide();
			$(".departmentAlertCountShow").show();
			
			var alertType = getAlertType();
			getDistrictLevelDeptWiseFilterViewDetails(alertType);
		}else if(type == "subTask"){
			$(".departmentlocationShow").hide();
			$(".departmentStatusShow").hide();
			$(".departmentAlertCountShow").show();
			var alertType = getAlertType();
			getDistrictLevelDeptWiseFilterViewDetails(alertType);
		}else if(type == "status"){
			$(".departmentlocationShow").hide();
			$(".departmentStatusShow").show();
			$(".departmentAlertCountShow").hide();
			var alertType = getAlertType();
			getDistrictLevelDeptWiseStatusOverView(alertType);
		}else if(type == "location"){
			$(".departmentlocationShow").show();
			$(".departmentStatusShow").hide();
			$(".departmentAlertCountShow").hide();
			var alertType = getAlertType();
			getDistrictLevelDeptWiseLocationLevelView(alertType);
		}
	});
	
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
	var userId=19601;
    var jObj ={
      userId:userId
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

function buildDistrictOfficerAlertsCountView(result){
	var str='';
		if(result !=null && result.list1 !=null && result.list1.length>0){
		str+='<div class="row">';
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0){
				str+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list1[0].todayCount+'</span></p>';
			}else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str+='<hr class="m_0"/>';
			if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0){
				str+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list1[0].overAllCnt+'</span></p>';
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
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str1+='<hr class="m_0"/>';
			if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0){
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
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
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
			}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str2+='<hr class="m_0"/>';
			if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0){
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
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
						str1+='<td class="distWiseDeptCls" attr_dept_id='+result[i].id+' attr_level_id='+result[i].subList2[j].id+' style="cursor:pointer;" >'+result[i].categoryCount+'</td>';
						if(result[i].subList2 !=null && result[i].subList2.length>0){
							
							for(var j in result[i].subList2){
								str1+='<td class="distWiseDeptCls" attr_dept_id='+result[i].id+' attr_level_id='+result[i].subList2[j].id+' style="cursor:pointer;">'+result[i].subList2[j].count+'</td>';
								
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
	
	
	
function getDistrictLevelDeptWiseLocationLevelView(alertType){
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptId:0
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseLocationLevelViewAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		buildDistrictLevelDeptWiseLocationLevelView(result);
	});
}

function buildDistrictLevelDeptWiseLocationLevelView(result){
	var mainlocationArr=[];
	
	if(result !=null && result.length>0){
		for(var i in result){
			
			var locationNamesArr=[];
				locationNamesArr.push(result[i].name)
			if(result[i].subList2 !=null &&  result[i].subList2.length>0){
				for(var j in result[i].subList2){
					var locationArr=[];
					var locationColorArr;
					locationArr.push(result[i].subList2[j].count);
					locationColorArr = result[i].subList2[j].color
					var obj={
								name: result[i].subList2[j].name,
								data: locationArr,
								color:locationColorArr
							}
					
					mainlocationArr.push(obj);
					
				}
			}
		}
		
		
	}
	
		var data = mainlocationArr
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
				categories: locationNamesArr
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
}

function getDistrictLevelDeptWiseStatusOverView(alertType){
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptId:0
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseStatusOverViewAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		buildDistrictLevelDeptWiseStatusOverView(result);
	});
}

function buildDistrictLevelDeptWiseStatusOverView(result){
	var mainlStatusArr=[];
	
	if(result !=null && result.length>0){
		for(var i in result){
			
			var locStatusNamesArr=[];
				locStatusNamesArr.push(result[i].name)
			if(result[i].subList2 !=null &&  result[i].subList2.length>0){
				for(var j in result[i].subList2){
					var locStatusArr=[];
					var locStatusColorArr;
					locStatusArr.push(result[i].subList2[j].count);
					locStatusColorArr = result[i].subList2[j].color
					var obj={
								name: result[i].subList2[j].name,
								data: locStatusArr,
								color:locStatusColorArr
							}
					
					mainlStatusArr.push(obj);
					
				}
			}
		}
		
		
	}
	
		var data = mainlStatusArr
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
				categories: locStatusNamesArr
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
}


//getSubOrdinateAlertsOverview();
function getSubOrdinateAlertsOverview(){
	var userId="";
	var fromDateStr= "";
	var toDateStr= "";
	var govtScopeIds=[];
	var locationValues=[];
	var desigIds=[];
	var priorityId="";
	
	var jObj = {
	userId : userId,
	fromDateStr : fromDateStr, 
	toDateStr : toDateStr,
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
$(document).on("click",".distWiseDeptCls",function(){
	var deptId = $(this).attr("attr_dept_id");
	var levelId = $(this).attr("attr_level_id");
	var jObj = {
		deptId: deptId,
	    levelId: levelId
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
	});
});
