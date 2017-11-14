var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalFromDate = moment().format("YYYY-MM")+'-01';
var globalToDate = moment().format("YYYY-MM-DD");

var fromDate = moment().format("YYYY-MM")+'-01';
var toDate = moment().format("YYYY-MM-DD");

var globalDeptCode = "27001701024";
var todayDate = moment().format("YYYY-MM-DD");

$("#dateRangeId").val(moment().format("YYYY-MM"));
$("#dateRangePopupId").val(moment().format("YYYY-MM"));
 
var globalDatewiseEmployeeAttendanceArray ;
var globalEmployeeId='';
 
getBioMetricDashboardOverViewDtls();
getEmployeeAttendenceTimePeriodWise();
onLoadCalls();
function onLoadCalls(){
	getDateWiseEmployeeAttendenceDetails();
	getEmployeeWiseAttendenceCount();
}

/* $("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
}); */



$("#dateRangeId").datetimepicker({
	format: 'YYYY-MM',
	viewMode:'months'
	
});
$('#dateRangeId').on('dp.change', function(e){ 
	globalFromDate = e.date.format("YYYY-MM")+'-01';
	var fromDateMonth = e.date.format("MM");
	if(fromDateMonth ==  moment().format("MM")){
		globalToDate = moment().format("YYYY-MM-DD");
	}else{
		globalToDate = e.date.endOf('month').format("YYYY-MM-DD");
	}
	onLoadCalls();
});
$("#dateRangePopupId").datetimepicker({
	format: 'YYYY-MM',
	viewMode:'months'
	
});
$('#dateRangePopupId').on('dp.change', function(e){ 
     fromDate = e.date.format("YYYY-MM")+'-01';
	 var fromDateMonth = e.date.format("MM");
	if(fromDateMonth ==  moment().format("MM")){
		toDate = moment().format("YYYY-MM-DD");
	}else{
		toDate = e.date.endOf('month').format("YYYY-MM-DD");
	}
	 getIndividualEmployeeAttendenceDetails(globalEmployeeId,fromDate,toDate);
});
function getBioMetricDashboardOverViewDtls(){
	$("#empOverViewBlockId").html(spinner);
	var json = {
		deptCode:globalDeptCode
	}
	$.ajax({                
		type:'POST',    
		url: 'getBioMetricDashboardOverViewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildEmpOverView(result);
		} else {
			$("#empOverViewBlockId").html("NO DATA AVAILABLE.");
		}
		
	});	
}
function buildEmpOverView(result){
	var str='';
	var employeeAttendanceOverView=[{name:'TOTAL EMPLOYEES',color:'#CFCFCF'},{name:'PRESENT',color:'#BCF4E9'},{name:'ABSENT',color:'#FFDBDB'},{name:'BIOMETRIC DEVICES',color:'#FFE6B5'}];
	
	for(var i in employeeAttendanceOverView){
			str+='<div class="col-sm-3" >';
				str+='<h5 class="font_weight">'+employeeAttendanceOverView[i].name+'</h5>';
				str+='<div class="media mediaMainBlockCss m_top10" style="border:1px solid '+employeeAttendanceOverView[i].color+';">';
					str+='<div class="media-left" >';
						if(employeeAttendanceOverView[i].name == "TOTAL EMPLOYEES"){
							if (result.totalCount > 0) {
								str+='<h5 class="m_top5 nameAlignmentCss employeeTypeCls" attr_type = "totalEmployee" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';cursor:pointer;"><span>Information Technology</span><span class="pull-right">'+result.totalCount+'</span></h5>';
								str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
							} else {
									str+='<h5 class="m_top5 nameAlignmentCss" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';"><span>Information Technology</span><span class="pull-right">'+result.totalCount+'</span></h5>';
								   str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
							}
						}else if(employeeAttendanceOverView[i].name == "PRESENT"){
							if (result.presentCount > 0) {
								str+='<h5 class="m_top5 nameAlignmentCss employeeTypeCls" attr_type = "todayPresentEmployee" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';cursor:pointer;"><span>Information Technology</span><span class="pull-right">'+result.presentCount+'</span></h5>';
							   str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
							} else {
								str+='<h5 class="m_top5 nameAlignmentCss" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';"><span>Information Technology</span><span class="pull-right">'+result.presentCount+'</span></h5>';
							     str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
							}
							
						}else if(employeeAttendanceOverView[i].name == "ABSENT"){
							if (result.absentCount > 0) {
									str+='<h5 class="m_top5 nameAlignmentCss employeeTypeCls" attr_type = "absentEmployee" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';cursor:pointer;"><span>Information Technology</span><span class="pull-right">'+result.absentCount+'</span></h5>';
									str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
							} else {
									str+='<h5 class="m_top5 nameAlignmentCss" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';"><span>Information Technology</span><span class="pull-right">'+result.absentCount+'</span></h5>';
									str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
					
							}
						}else if(employeeAttendanceOverView[i].name == "BIOMETRIC DEVICES"){
							str+='<h5 class="m_top5 nameAlignmentCss" style="border-bottom: 1px solid '+employeeAttendanceOverView[i].color+';"><span>Information Technology</span><span class="pull-right">'+result.bioMetricDeviceCount+'</span></h5>';
							str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">0</span></h5>';
						}
						
					str+='</div>';
					str+='<div class="media-body media_customBody" style="background-color:'+employeeAttendanceOverView[i].color+';">';
						if(employeeAttendanceOverView[i].name == "TOTAL EMPLOYEES"){
							if (result.totalCount > 0) {
								str+='<h3 class="text-center employeeTypeCls" attr_type = "totalEmployee" style="cursor:pointer;">'+result.totalCount+'</h3>';
							} else {
								str+='<h3 class="text-center ">'+result.totalCount+'</h3>';	
							}
						}else if(employeeAttendanceOverView[i].name == "PRESENT"){
							  if (result.presentCount > 0) {
								str+='<h3 class="text-center employeeTypeCls" attr_type = "todayPresentEmployee" style="cursor:pointer;" >'+result.presentCount+'</h3>';
							   } else {
								str+='<h3 class="text-center ">'+result.presentCount+'</h3>';	
							   }
						}else if(employeeAttendanceOverView[i].name == "ABSENT"){
							  if (result.absentCount > 0) {
								str+='<h3 class="text-center employeeTypeCls" attr_type = "absentEmployee" style="cursor:pointer;">'+result.absentCount+'</h3>';
							   } else {
								str+='<h3 class="text-center ">'+result.absentCount+'</h3>';	
							   }
						}else if(employeeAttendanceOverView[i].name == "BIOMETRIC DEVICES"){
								str+='<h3 class="text-center ">'+result.bioMetricDeviceCount+'</h3>';
						}
						
						str+='<h5 class="text-center m_top10">'+employeeAttendanceOverView[i].name+'</h5>';
					str+=' </div>';
					
				str+='</div>';
			str+='</div>';
	}
	
	$("#empOverViewBlockId").html(str);
}


function getEmployeeAttendenceTimePeriodWise(){
	$("#inTimeStatisticsDivId").html(spinner);
	  var json = {
			deptCode:globalDeptCode,
			todayDate:todayDate
		}
	$.ajax({                
		type:'POST',    
		url: 'getEmployeeAttendenceTimePeriodWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildEmployeeAttendenceTimePeriodWise(result);
		} else {
			$("#inTimeStatisticsDivId").html("NO DATA AVAILABLE.");
		}
	});	
}

function buildEmployeeAttendenceTimePeriodWise(result){
	var categoryNamesArr=[];
	var dataArr=[];
	
	for(var i in result){
		var countArr=[];
		categoryNamesArr.push(result[i].name);
		countArr.push(result[i].totalCount)
		dataArr.push(countArr)	
	}
	
	$('#inTimeStatisticsDivId').highcharts({
		 colors: ['#009FD1'],
		chart: {
			type: 'column'
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
			categories: categoryNamesArr,
			title: {
				text: null
			}
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
			
		},
		tooltip: {
			useHTML:true,
			formatter: function () {
				return '<b>' + this.x + '</b><br/>' +
					this.y;
			}
		},
		plotOptions: {
			column: {
				pointWidth: 30,
				gridLineWidth: 20
				
			},
			series: {
					cursor: 'pointer',
					point: {
					events: {
						click: function (event) {
							var timePeriod = (event.point.category);
							 getTimePeriodWiseEmployeeAttendenceDetails(timePeriod);
						}
					}
				}
			 }
		},
		legend: {
			enabled: true,
			
		},
		series:[{
			name: 'Information Technologies',
			data: dataArr,

			dataLabels: {
				enabled: true,
				color: '#000',
				align: 'canter',
				formatter: function() {
					return '<span>'+this.y+'</span>';
				} 
			}
		}]
	});
}

function getDateWiseEmployeeAttendenceDetails(){
	$("#lastDaysStatisticsDivId").html(spinner);
	var json = {
		fromDate:globalFromDate,
		toDate:globalToDate,
		deptCode:globalDeptCode
	}
	$.ajax({                
		type:'POST',    
		url: 'getDateWiseEmployeeAttendenceDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 var noOfDays = result.length;
			  globalDatewiseEmployeeAttendanceArray = result;
			  $("#daysReportHeadingId").html("LAST "+noOfDays+" DAYS");
			  buildDateWiseEmployeeAttendenceDetails(result);
		} else {
			$("#lastDaysStatisticsDivId").html("NO DATA AVAILABLE");
		}
	});	
}
function buildDateWiseEmployeeAttendenceDetails(result){
	var categoryLastDaysArr=[];
	var empDetailsObjArr=[];
	var absentDataArr = [];
	var presentDataArr = [];
	var sundayDataArr = [];
	var holiDayDataArr = [];
	var optionalHolidayDataArr = [];
	for(var i in result){
		 categoryLastDaysArr.push(result[i].order);
		 presentDataArr.push(result[i].presentCount);
		 if(result[i].type == "GENERAL Holiday"){
			 absentDataArr.push(0);
		     sundayDataArr.push(0);
			 holiDayDataArr.push(result[i].totalCount);
		     optionalHolidayDataArr.push(0);
		}else if(result[i].type == "OPTIONAL Holiday"){
			 absentDataArr.push(0);
		     sundayDataArr.push(0);
			 holiDayDataArr.push(0);
		     optionalHolidayDataArr.push(result[i].totalCount);
		}else if (result[i].dayOfWeek == "Sunday" || result[i].dayOfWeek =="Saturday"){
			 absentDataArr.push(0);
		     sundayDataArr.push(result[i].totalCount);
			 holiDayDataArr.push(0);
		     optionalHolidayDataArr.push(0);
		}else {
			 absentDataArr.push(result[i].absentCount);
		     sundayDataArr.push(0);
			 holiDayDataArr.push(0);
		     optionalHolidayDataArr.push(0);
		}
	}
	
	$('#lastDaysStatisticsDivId').highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: categoryLastDaysArr
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			}
		},
		tooltip: {
			formatter: function () {
				var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					if(this.series.name != "Series 1")  {
					   if (this.series.name == "SATURDAY OR SUNDAY" || this.series.name == "OPTIONAL HOLIDAY" || this.series.name == "HOLIDAY")	{
                       s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + ':</b> ';
						  return s;
					   }	
						s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +this.y;		//+' - ' +(Highcharts.numberFormat(this.percentage,1)+'%')			   
					}
					 
				});

				return s;
			},
			shared: true
		},
		plotOptions: {
			column: {
				stacking: 'percent'
			},
			series: {
					cursor: 'pointer',
					point: {
					events: {
						click: function (event) {
							var order = (event.point.category);
							var type = (this.series.name);
							 if (type == "SATURDAY OR SUNDAY" || type == "OPTIONAL HOLIDAY" || type == "HOLIDAY"){
								 return;
							 } else {
								 var empType = "";
								  if (type == "PRESENT") {
									  empType = "presentEmployee";
								  } else if (type == "ABSENT") {
									  empType = "absentEmployee";
								  }
							 }
							 getDateWisePresentEmployeeDetails(order,empType,type);
						}
					}
				}
			 }
		},
		series: [{
			name: 'ABSENT',
			data: absentDataArr,
			color:"#FF7171"
		}, {
			name: 'PRESENT',
			data: presentDataArr,
			color:"#00D1B5"
		}, {
			name: 'SATURDAY OR SUNDAY',
			data: sundayDataArr,
			color:"#626262"
		}, {
			name: 'HOLIDAY',
			data: holiDayDataArr,
			color:"#FFAA00"
		}, {
			name: 'OPTIONAL HOLIDAY',
			data: optionalHolidayDataArr,
			color:"#009FD1"
		}]
	});
}

function getEmployeeWiseAttendenceCount(){
	 $("#attandanceOverviewDetailsId").html(spinner);
	var json = {
		fromDate:globalFromDate,
		toDate:globalToDate,
		deptCode:globalDeptCode
	}
	$.ajax({                
		type:'POST',    
		url: 'getEmployeeWiseAttendenceCount',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null && result.length > 0) {
			 buildEmployeeWiseAttendanceDetails(result);
		} else {
			 $("#attandanceOverviewDetailsId").html("NO DATA AVAILABLE.");
		}
	});	
}
function buildEmployeeWiseAttendanceDetails(result) {
		 var str = '';
		 str +='<div class="table-responsive">';
		 str += '<table class="table table-employeeAtten" id="employeeWiseAttendanceDataTbleId">';
		  str +='<thead>';
			  str +='<th>EMPLOYEE NAME</th>';
			  str +='<th>DESIGNATION</th>';
			  str +='<th>Total Working Days</th>';
			  str +='<th>Absent Total</th>';
		  str +='<th>Present Total</th>';
		  if(result[0].subList1 != null && result[0].subList1.length > 0) {
					for(var j in result[0].subList1) {
							str +='<th>'+result[0].subList1[j].name+'</th>';
					}
				}
		  str+'</thead>';
		 str+='<tbody>';
		 for (var i in result) {
			 str+='<tr>'
				str +='<td><a class="employeePopUpCls" attr_empId="'+result[i].empId+'" attr_empName="'+result[i].name+'">'+result[i].name+'</a></td>';
				str +='<td>'+result[i].designation+'</td>';
				str +='<td>'+result[i].totalWorkingDays+'</td>';
				str +='<td>'+result[i].absentCount+'</td>';
				str +='<td>'+result[i].presentCount+'</td>';
				if (result[i].subList1 != null && result[i].subList1.length > 0) {
					for(var j in result[i].subList1) {
						  str +='<td>'+result[i].subList1[j].totalCount+'</td>';
					}
				}
			 str+='</tr>';
		 }
		 str+='</tbody>';
		 str+='</table>';
	str +='</div>';
	$("#attandanceOverviewDetailsId").html(str);
	$("#employeeWiseAttendanceDataTbleId").dataTable();
}
function getIndividualEmployeeAttendenceDetails(empId,fromDate,toDate){
	$("#empDetailsModalDivId").html(spinner);
	$("#empInTimeGraphModalDivId").html(spinner);
	$("#empLastDaysStatisticsDivId").html(spinner);
	var json = {
		fromDate:fromDate,
		toDate:toDate,
		empId:empId,
		deptCode:globalDeptCode
	}
	$.ajax({                
		type:'POST',    
		url: 'getIndividualEmployeeAttendenceDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildIndividualEmployeeAttendenceDetails(result);
		}else{
			$("#empDetailsModalDivId").html("No Data Available");
			$("#empInTimeGraphModalDivId").html("No Data Available");
			$("#empLastDaysStatisticsDivId").html("No Data Available");
		}
	});	
}
$(document).on("click",".employeePopUpCls",function(){
		var empId = $(this).attr("attr_empId")
		var empName = $(this).attr("attr_empName");
		 globalEmployeeId = empId;
		$("#openModalDiv").modal("show");
		$("#bioMetricTitleId").html("EMPLOYEE NAME <h4 class='m_top10'>"+empName+"</h4>");
		$("#dateRangePopupId").val(globalFromDate.split("-")[0]+"-"+globalFromDate.split("-")[1]);
		getIndividualEmployeeAttendenceDetails(empId,globalFromDate,globalToDate);
});
function buildIndividualEmployeeAttendenceDetails(result){
	var str='';
	
			str+='<div class="col-sm-4 m_top10 pad_left0 pad_right0">';
				str+='<div class="pad_border">';
					str+='<div class="row">';
					str+='<div class="col-sm-12">';	
						str+='<div class="col-sm-9">';
							str+='<h5 class="text-capital">Total Working Days <span class="pull-right background_pad f-18" style="background-color:#EBEBEB">'+result.totalWorkingDays+'</span></h5>';
							str+='<h5 class="m_top30 text-capital">NON WORKING DAYS <span class="pull-right background_pad f-18" style="background-color:#EBEBEB">'+result.nonWorkingDays+'</span></h5>';
						str+='</div>';
						var totalWorkingDays = result.totalWorkingDays+result.nonWorkingDays
						str+='<div class="col-sm-3 bioCountCss" style="background-color:#EBEBEB">';
							str+='<h3 class="font_weight m_top20">'+totalWorkingDays+'</h3>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
			str+='</div>';
		
			str+='<div class="col-sm-4 m_top10 pad_left0 pad_right0">';
				str+='<div class="pad_border">';
					str+='<div class="row">';
					str+='<div class="col-sm-12">';	
						str+='<div class="col-sm-9">';
							str+='<h5 class="text-capital">WORKING DAYS PRESENT <span class="pull-right background_pad f-18" style="background-color:#00D1B5;color:#fff">'+result.presentCount+'</span></h5>';
							str+='<h5 class="m_top30 text-capital">NON WORKING DAYS PRESENT<span class="pull-right background_pad f-18" style="background-color:#00D1B5;color:#fff">'+result.nonWorkingDayPresent+'</span></h5>';
						str+='</div>';
						var totalPresentWorkingDays = result.presentCount+result.nonWorkingDayPresent
						str+='<div class="col-sm-3 bioCountCss" style="background-color:#00D1B5;color:#fff">';
							str+='<h3 class="font_weight m_top20">'+totalPresentWorkingDays+'</h3>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
			str+='</div>';
		
			str+='<div class="col-sm-4 m_top10 pad_left0 pad_right0">';
				str+='<div class="pad_border">';
					str+='<div class="row">';
					str+='<div class="col-sm-12">';	
					str+='<div class="col-sm-9">';
						str+='<h5 class="text-capital m_top30">ABSENT DAYS</h5>';
					str+='</div>';
					str+='<div class="col-sm-3 bioCountCss" style="background-color:#FF7171;color:#fff">';
						str+='<h3 class="font_weight m_top20">'+result.absentCount+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				str+='</div>';
			str+='</div>';
			
	$("#empDetailsModalDivId").html(str);
	var categoryNamesArr=[];
	var dataArr=[];
	
	if (result.subList2 != null && result.subList1.length > 0) {
		 for(var i in result.subList2){
			var countArr=[];
			categoryNamesArr.push(result.subList2[i].name);
			countArr.push(result.subList2[i].totalCount)
			dataArr.push(countArr)	
	   }
	   
	   
		$('#empInTimeGraphModalDivId').highcharts({
			 colors: ['#009FD1'],
			chart: {
				type: 'column'
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
				categories: categoryNamesArr,
				title: {
					text: null
				}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				
			},
			tooltip: {
				useHTML:true,
				formatter: function () {
					return '<b>' + this.x + '</b><br/>' +
						this.y;
				}
			},
			plotOptions: {
				column: {
					pointWidth: 30,
					gridLineWidth: 20
					
				}
			},
			legend: {
				enabled: true,
				
			},
			series:[{
				name: 'Information Technologies',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'canter',
					formatter: function() {
						return '<span>'+this.y+'</span>';
					} 
				}
			}]
		});
	} else {
		$("#empInTimeGraphModalDivId").html("NO DATA AVAILABLE.");
	}
	
	if (result.subList1 != null && result.subList1.length > 0) {
		var noOfDays = result.subList1.length;
		 $("#individaualEmpdaysReportHeadingId").html("LAST "+noOfDays+" DAYS");
			var categoryLastDaysEmpArr=[];
			var absentDataEmpArr = [];
			var presentDataEmpArr = [];
			var sundayDataEmpArr = [];
			var holiDayDataEmpArr = [];
			var optionalHolidayDataEmpArr = [];
		for(var i in result.subList1){
			categoryLastDaysEmpArr.push(result.subList1[i].order);
			presentDataEmpArr.push(result.subList1[i].presentCount);
			if (result.subList1[i].type == "GENERAL Holiday"){
				 absentDataEmpArr.push(0);
				 sundayDataEmpArr.push(0);
				 holiDayDataEmpArr.push(result.subList1[i].totalCount);
				 optionalHolidayDataEmpArr.push(0);
			}else if(result.subList1[i].type == "OPTIONAL Holiday"){
				 absentDataEmpArr.push(0);
				 sundayDataEmpArr.push(0);
				 holiDayDataEmpArr.push(0);
				 optionalHolidayDataEmpArr.push(result.subList1[i].totalCount);
			}else if(result.subList1[i].dayOfWeek == "Sunday" || result.subList1[i].dayOfWeek=="Saturday"){
				 absentDataEmpArr.push(0);
				 sundayDataEmpArr.push(result.subList1[i].totalCount);
				 holiDayDataEmpArr.push(0);
				 optionalHolidayDataEmpArr.push(0);
			}else{
				 absentDataEmpArr.push(result.subList1[i].absentCount);
				 sundayDataEmpArr.push(0);
				 holiDayDataEmpArr.push(0);
				 optionalHolidayDataEmpArr.push(0);
			}
		}
	
		if((absentDataEmpArr !=null && absentDataEmpArr.length>0) && (presentDataEmpArr !=null && presentDataEmpArr.length>0) && (sundayDataEmpArr !=null && sundayDataEmpArr.length>0) && (holiDayDataEmpArr !=null && holiDayDataEmpArr.length>0) && (optionalHolidayDataEmpArr !=null && optionalHolidayDataEmpArr.length>0)){
			$('#empLastDaysStatisticsDivId').highcharts({
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: categoryLastDaysEmpArr
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					}
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							if(this.series.name != "Series 1")  {
							   if (this.series.name == "SATURDAY OR SUNDAY" || this.series.name == "OPTIONAL HOLIDAY" || this.series.name == "HOLIDAY")	{
							   s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + ':</b> ';
								  return s;
							   }	
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +this.y;//+' - ' +(Highcharts.numberFormat(this.percentage,1)+'%');					   
							}
					 
				        });

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent'
					}
				},
				series: [{
					name: 'ABSENT',
					data: absentDataEmpArr,
					color:"#FF7171"
				}, {
					name: 'PRESENT',
					data: presentDataEmpArr,
					color:"#00D1B5"
				}, {
					name: 'SATURDAY OR SUNDAY',
					data: sundayDataEmpArr,
					color:"#626262"
				}, {
					name: 'HOLIDAY',
					data: holiDayDataEmpArr,
					color:"#FFAA00"
				}, {
					name: 'OPTIONAL HOLIDAY',
					data: optionalHolidayDataEmpArr,
					color:"#009FD1"
				}]
			});
		}else{
			$('#empLastDaysStatisticsDivId').html("No Data Available");
			$('#empLastDaysStatisticsDivId').removeAttr("style");
		}
		} else {
			 $('#empLastDaysStatisticsDivId').html("No Data Available");
			 $('#empLastDaysStatisticsDivId').removeAttr("style");
		}
		
}

//click functionality

$(document).on("click",".employeeTypeCls",function() {
  var type = $(this).attr("attr_type");
  $("#employeeDetaislHeadingId").html("Employee Details.")
   $("#empDetailsModalId").modal("show");
   getEmployeeDetailsByEmployeeType(type);
});


function getEmployeeDetailsByEmployeeType(type){
	   $("#employeeDetailsDivId").html(spinner);
	  var json = {
			  deptCode:globalDeptCode,
			  employeeType:type
		}
	$.ajax({                
		type:'POST',    
		url: 'getEmployeeDetailsByEmployeeType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null && result.length > 0) {
			buildEmployeeDetial(result,"overAll");
		} else {
			 $("#employeeDetailsDivId").html("NO DATA AVAILABLE.");
		}
	});	
}
function getTimePeriodWiseEmployeeAttendenceDetails(timePeriod){
	  $("#empDetailsModalId").modal("show");
	  $("#employeeDetailsDivId").html(spinner);
	  $("#employeeDetaislHeadingId").html(timePeriod+"&nbsp&nbspEMPLOYEE ATTENDANCE DETAILS.");
	  var json = {
			  deptCode:globalDeptCode,
			  timePeriod:timePeriod
		}
	$.ajax({                
		type:'POST',    
		url: 'getTimePeriodWiseEmployeeAttendenceDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null && result.length > 0) {
			buildEmployeeDetial(result,"timePeriod");
		} else {
			 $("#employeeDetailsDivId").html("NO DATA AVAILABLE.");
		}
	});	
}
function getDateWisePresentEmployeeDetails(order,empType,type){
	 $("#empDetailsModalId").modal("show");
	  $("#employeeDetailsDivId").html(spinner);
	  $("#employeeDetaislHeadingId").html(type+"&nbsp&nbspEMPLOYEE DETAILS.");
	  var resultType = ""
	  if (type == "ABSENT") {
		  resultType = "overAll";
	  } else if (type == "PRESENT") {
		  resultType = "PRESENT";
	  }
	  
	 var fromDateStr ="";
	 var toDateStr ="";
	for(var i in globalDatewiseEmployeeAttendanceArray){
		if (globalDatewiseEmployeeAttendanceArray[i].order == order) {
			fromDateStr = globalDatewiseEmployeeAttendanceArray[i].name;
			toDateStr = globalDatewiseEmployeeAttendanceArray[i].name;
		}
	}
	  var json = {
			  deptCode:globalDeptCode,
			  employeeType:empType,
			  fromDate:fromDateStr,
			  toDate:toDateStr,
		}
	$.ajax({                
		type:'POST',    
		url: 'getDateWisePresentEmployeeDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null && result.length > 0) {
			buildEmployeeDetial(result,resultType);
		} else {
			 $("#employeeDetailsDivId").html("NO DATA AVAILABLE.");
		}
	});	
}

function buildEmployeeDetial(result,type) {
	var str = '';
         str +='<div class="table-responsive">';
	     str += '<table class="table table-bordered" id="employeeDetailsDataTableId">';
		  str +='<thead>';
			  str +='<th>EMPLOYEE NAME</th>';
			  str +='<th>DESIGNATION</th>';
			  str +='<th>EMP_TYPE</th>';
			  if (type == "overAll") {
				   str +='<th>DEPARTMENT</th>';
			  } else if (type == "PRESENT") {
				   str +='<th>DATE</th>';
			       str +='<th>IN TIME</th>';  
			  } else if (type == "timePeriod" ) {
				   str +='<th>DEPARTMENT</th>';
				   str +='<th>DATE</th>';
			       str +='<th>IN TIME</th>';  
			  }
		  str +'</thead>';
		 str +='<tbody>';
		 for (var i in result) {
			 str +='<tr>'
			    str +='<td>'+result[i].name+'</td>';
			    str +='<td>'+result[i].designation+'</td>';
			    str +='<td>'+result[i].type+'</td>';
				 if (type == "overAll") {
				 	if (result[i].deptName != null && result[i].deptName.length > 0) {
					str +='<td>'+result[i].deptName+'</td>';
					} else {
					  str +='<td>-</td>';	
					}
			  } else if (type == "PRESENT") {
				    if (result[i].date != null && result[i].date.trim().length > 0) {
					     str +='<th>'+result[i].date.substring(0,10)+'</th>';	 
					 } else {
						  str +='<th> - </th>';
					 }
				     str +='<th>'+result[i].time+'</th>';  
			  } else if (type == "timePeriod" ) {
				  	if (result[i].deptName != null && result[i].deptName.length > 0) {
					  str +='<td>'+result[i].deptName+'</td>';
					} else {
					  str +='<td>-</td>';	
					}
				   if (result[i].date != null && result[i].date.trim().length > 0) {
					     str +='<th>'+result[i].date.substring(0,10)+'</th>';	 
					 } else {
						  str +='<th> - </th>';
					 }
				     str +='<th>'+result[i].time+'</th>';  
			  }
			 str+='</tr>';
		 }
		 str+='</tbody>';
		 str+='</table>';
	str +='</div>';
	$("#employeeDetailsDivId").html(str);
	$("#employeeDetailsDataTableId").dataTable();
}
