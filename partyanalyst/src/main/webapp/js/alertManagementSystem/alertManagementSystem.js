/*global Function and variables Start*/
var currentFromDate = moment().subtract(1,"month").format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var globalStateId = 1;  
$("#dateRangePicker").daterangepicker({
	opens: 'left',
	startDate: currentFromDate,
	endDate: currentToDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
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
$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	currentFromDate = picker.startDate.format('DD/MM/YYYY');
	currentToDate = picker.endDate.format('DD/MM/YYYY');
	totalAlertGroupByStatusForGovt()
	totalAlertGroupByStatusThenDepartment()
	getDistrictWiseTotalForAlertOverview()
	getDistrictTotalForAlertStatus()
});
$(".chosenSelect").chosen({width:'100%'});
onLoadCalls();
function onLoadCalls()
{
	totalAlertGroupByStatusForGovt()
	totalAlertGroupByStatusThenDepartment()
}
$(document).on("click",".settingsIcon",function(){
	$(".settingsBlockDropDown").show();
});
/*global Function and variables End*/
/* Status OverView Start*/
function totalAlertGroupByStatusForGovt()
{
	$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39];
    var paperIdArr = [1,2,3,4,5,7,8,10,11,12,13,14,15,16,17,18];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusForGovtAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildTotalAlertGroupByStatusForGovt(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
}
function buildTotalAlertGroupByStatusForGovt(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div id="totalAlertGroupByStatusForGovt"></div>';
			str+='<div id="statusOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].count;
						str+='<tr>';
							str+='<td>'+result[i].status+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsCls" attr_status_id="'+result[i].statusId+'">'+result[i].count+'</td>';
							str+='<td>'+result[i].statusPercent+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str);
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].statusPercent;
		statusName = result[i].status;
		var cnt = result[i].count;
		var stsId = result[i].statusId;
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		var chart = new Highcharts.Chart({
			colors: ['#FE9900','#0B9614','#8E4654','#F25C81'],
			chart: {
				renderTo: 'totalAlertGroupByStatusForGovt',
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
					return "Total Alerts - "+cnt+"";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: true,
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
					depth: 100,
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
		},
	
		function(chart) { // on complete
			var textX = chart.plotLeft + (chart.plotWidth  * 0.5);
			var textY = chart.plotTop  + (chart.plotHeight * 0.5);

			var span = '<span id="pieChartInfoText" style="position:absolute; text-align:center;">';
			span += '<span style="font-size: 18px">TOTAL</span><br>';
			span += '<span style="font-size: 14px;cursor:pointer;" class="getDtlsCls" attr_status_id="0">'+totalAlert+'</span>';
			span += '</span>';

			$("#statusOverViewTotal").append(span);
			span = $('#pieChartInfoText');
			span.css('left', textX + (span.width() * -0.5));
			span.css('top', textY + (span.height() * -0.5));
		});
	});
}
/* Status OverView End*/
/* DEPARTMENT WISE STATUS OVERVIEW START*/
function totalAlertGroupByStatusThenDepartment()
{
	$("#departmentWiseStatusOvrVw").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
    var deptIdArr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39];
    var paperIdArr = [1,2,3,4,5,7,8,10,11,12,13,14,15,16,17,18];
    var chanelIdArr = [1,2,3,4,5,6,7];
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusThenDepartmentAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertGroupByStatusThenDepartment(result);   
		}else{
			$("#departmentWiseStatusOvrVw").html('NO DATA AVAILABLE')
		}
    });
}
function buildtotalAlertGroupByStatusThenDepartment(result)
{
	var str='';
	str+='<ul class="nav nav-tabs" role="tablist">';
	for(var i in result)
	{
		if(i == 0)
		{
			str+='<li role="presentation" class="active text-capital"><a href="#'+result[i].status+'" aria-controls="'+result[i].status+'" role="tab" data-toggle="tab">'+result[i].status+'</a></li>';
		}else{
			str+='<li role="presentation" class="text-capital"><a href="#'+result[i].status+'" aria-controls="'+result[i].status+'" role="tab" data-toggle="tab">'+result[i].status+'</a></li>';
		}
	}
	str+='</ul>';
	str+='<div class="tab-content">';
	for(var i in result)
	{
		if(i == 0)
		{
			str+='<div role="tabpanel" class="tab-pane active" id="'+result[i].status+'">';
		}else{
			str+='<div role="tabpanel" class="tab-pane" id="'+result[i].status+'">';
		}
		
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="m_top20">DEPARTMENTS WISE</h4>';
					str+='<div class="row">';
						str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
							str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply'+i+'">';
							for(var j in result[i].subList1)
							{
								if(result[i].subList1[j].category !=null && result[i].subList1[j].category.length > 40){
									str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].subList1[j].category+'">'+result[i].subList1[j].category.substring(0,40)+'...</span> <span style="cursor:pointer;" class="pull-right getDtlsCls" attr_status_id="'+result[i].statusId+'" attr_dept_id="'+result[i].subList1[j].categoryId+'">'+result[i].subList1[j].categoryCount+'</span></li>';  
								}else{//swadhin
									str+='<li>'+result[i].subList1[j].category+'  <span style="cursor:pointer;" class="pull-right getDtlsCls" attr_status_id="'+result[i].statusId+'" attr_dept_id="'+result[i].subList1[j].categoryId+'">'+result[i].subList1[j].categoryCount+'</span></li>';
								}
								
							}//class="getDtlsCls" attr_status_id="'+result[i].statusId+'"
							str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-5 col-xs-12 col-sm-4">';
							str+='<div id="departmentStatusGraph'+i+'"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';	
	}
	str+='</div>';
	$("#departmentWiseStatusOvrVw").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	
	for(var i in result)
	{
		var dynamicHeight;
		$(".dynamicHeightApply"+i).each(function(){
			dynamicHeight = $(this).find("li").length;
			dynamicHeight = (dynamicHeight*40)+"px";
		});
		$("#departmentStatusGraph"+i).css("height",dynamicHeight);
		var dynamicWidth = $("#departmentStatusGraph0").parent().width();
		$("#departmentStatusGraph"+i).css("width",dynamicWidth)
		var departmentStatusOvrVwArr = [];
		for(var j in result[i].subList1)
		{
			var departmentStatus = [];
			departmentStatus.push(result[i].subList1[j].category)
			departmentStatus.push(result[i].subList1[j].statusPercent)
			departmentStatusOvrVwArr.push(departmentStatus)
		}
		$('#departmentStatusGraph'+i).highcharts({
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
				categories: '',
				labels: {
				enabled: false,
					
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
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}%</b><br/>'
			},
			plotOptions: {
				bar: {
					dataLabels: {
						enabled: true,
						formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.y,2) + '%';
							}
						}
					}
				}
			},
			legend: {
				enabled: false,
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'top',
				x: -40,
				y: 80,
				floating: true,
				borderWidth: 1,
				backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
				shadow: true
			},
			
			series: [{
				 name: '',
				 colorByPoint: true,
				 data: departmentStatusOvrVwArr
			}]
		});
	}
	
}

/* DEPARTMENT WISE STATUS OVERVIEW END*/

/* Total Alerts Modal Start CLick Based*/
$(document).on("click",".getDtlsCls",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	var deptIdArr = [];
	var alertStatusId = $(this).attr("attr_status_id");
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39];
	}
	
    var paperIdArr = [1,2,3,4,5,7,8,10,11,12,13,14,15,16,17,18];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  statusId : alertStatusId       
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertByStatusAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertsModalTabId(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
});
function getData(count, alertStatusId){ 
	$("#totalAlertsModalTabId").html('');
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'  
	});
	var deptIdArr = [];
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39];
	}
	
    var paperIdArr = [1,2,3,4,5,7,8,10,11,12,13,14,15,16,17,18];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  statusId : alertStatusId       
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertByStatusAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertsModalTabId(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
}	
function buildtotalAlertsModalTabId(result){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var str='';
	if($(window).width() < 500)
	{
		str+='<div class="table-responsive" >';
	}
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="dataTableTotalAlerts">';
			str+='<thead>';
				str+='<th>Alert Source</th>';
				str+='<th>Alert Title</th>';
				str+='<th>Created Date</th>';
				str+='<th>Last Updated Date</th>';
				str+='<th>Current Status</th>'	 
				str+='<th>LAG Days</th>';
				str+='<th>Alert Level</th>';
				str+='<th>Location</th>';
				str+='<th></th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					if(result[i].source != null && result[i].source.length > 0){
						str+='<td><strong>'+result[i].source+'</strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].title != null && result[i].title.length > 0){
						str+='<td><strong>'+result[i].title+'</strong></td>';         
					}else{
						str+='<td> - </td>';     
					}
					if(result[i].createdDate != null && result[i].createdDate.length > 0){
						str+='<td>'+result[i].createdDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].updatedDate != null && result[i].updatedDate.length > 0){
						str+='<td>'+result[i].updatedDate+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].status != null && result[i].status.length > 0){
						str+='<td>'+result[i].status+'</td>';      
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].interval != null){
						str+='<td>'+(parseInt(result[i].interval)-parseInt(1))+'</td>';            
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].alertLevel != null && result[i].alertLevel.length > 0){
						str+='<td>'+result[i].alertLevel+'</td>';               
					}else{
						str+='<td> - </td>';  
					}
					if(result[i].location != null && result[i].location.length > 0){
						str+='<td>'+result[i].location+'</td>';        
					}else{
						str+='<td> - </td>';        
					}
					str+='<td><button class="btn btn-success alertDetailsModalCls" attr_alert_Id="'+result[i].id+'">Alert Details</button></td>';      
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	if($(window).width() < 500)
	{
		str+='</div>';
	}
	$("#totalAlertsModalTabId").html(str);
	$("#dataTableTotalAlerts").dataTable();
}
/* Total Alerts Modal End CLick Based*/

/* Alert Details Modal Start*/
$(document).on("click",".alertDetailsModalCls",function(){
	$("#alertDetailsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	
	var alertId = $(this).attr("attr_alert_Id");
	$("#hiddenAlertId").val(alertId);   //3725
	getAlertData(alertId);
	getInvolvedMembersDetilas(alertId);
	getAlertStatusCommentsTrackingDetails();
	getDepartmentLevels();
	assignedOfficersDetailsForAlert(alertId);
});
function getAlertData(alertId)
{
	$("#alertCandidateDataId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj =
	{
		alertId  :alertId,
		task : ""
	}
	$.ajax({
			  type:'GET',
			  url: 'getAlertsDataAction.action',
			  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null)
		{
			buildAlertData(result);
		}
	});
}
function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId = result[i].categoryId;
		
		$("#typeId").html('<b>'+result[i].alertType+'</b>');
		$("#createdDate").html('<b>'+result[i].date+'</b>');
		$("#alertStatus").html('<b>'+result[i].status+'</b>');
		if(severityTdId ==1){
			$("#severityTdId").show();
		}else{
			$("#severityTdId").hide();
		}
		$(".severityIdColorCls").addClass('<b>'+result[i].severity+'</b>');
		$("#severityId").html('<b>'+result[i].severity+'</b>');
		$("#levelId").html('<b>'+result[i].regionScope+'</b>');
		
		var location ='';
			
		if(result[i].locationVO.stateId !=null){
			location +='<b>'+result[i].locationVO.state+' , ';
		}
		if(result[i].locationVO.districtId !=null){
			location +=''+result[i].locationVO.districtName+' District, ';
		}
		if(result[i].locationVO.constituencyId !=null){
			location +=''+result[i].locationVO.constituencyName+' Constituency, ';
		}
		
		if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
			location +=''+result[i].locationVO.localEleBodyName+' Municipality, ';
		}
		if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
			location +=''+result[i].locationVO.tehsilName+' Mandal, ';
		}
		if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
			location +=''+result[i].locationVO.wardName+' Ward, ';
		}
		if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
			location +=''+result[i].locationVO.villageName+' Panchayat </b>';
		}
		
		$("#LocationId").html(''+location+'');
		
		$("#titleId,#mainTitleId").html('<b>'+result[i].title+'</b>');
		
		$("#descriptionId").html('<b>'+result[i].desc+'</b>');
		
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			$(".imageUrlUlCls").html("<li class='articleDetailsCls' attr_articleId="+result[i].alertCategoryTypeId+" style='cursor:pointer'><img src='http://mytdp.com/NewsReaderImages/"+result[i].imageUrl+"' style='width: 150px; height: 150px;'></img></li>");
			$("#imageUrlTrId").show();
		}else{
			$(".imageUrlUlCls").html("");
			$("#imageUrlTrId").hide();
		}
		
		//buildAlertCandidateData(result[i].subList,result[i].categoryId);
	}
}

function getInvolvedMembersDetilas(alertId){
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		  type:'GET',
		  url: 'getInvolvedMembersInAlertAction.action',
		  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildAlertCandidateData(result);
	});
}
function buildAlertCandidateData(result,categoryId)
{

	var str='';
	
	if(result == null || result.length == 0)
	{
		$("#alertCandidateDataId").html('No Involved Members..');
		return;
	}
	
	/*if(categoryId !=null && categoryId>1){
		for(var i in result)
		{
			
			str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10" style="padding: 3px;">';
				str+='<div class="media" style="border:1px solid #ddd;height:100px">';
					str+='<div class="media-left">';						
					if(result[i].image !=null && result[i].image.length>0){
						str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}else{
						str+=' <img src="dist/Appointment/img/thumb.jpg"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}					   					  
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						//str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					str+='  <p><b class="text-capital text-danger">Designation : </b>'+result[i].committeePosition+' </p>';
					 // str+='  <p>'+result[i].mobileNo+'</p>';
					 // str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success" style="margin-top: 7px;">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger" style="margin-top: 7px;">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral" style="margin-top: 7px;">N</span>';
					  }
					  
					  if(result[i].organization !=null){
						  str+='<p><b class="text-capital text-danger">Organization</b> : '+result[i].organization+'</p>';
					  }
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><b class="text-capital text-danger">Membership No </b> : '+result[i].membershipNo+'</p>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';

		}
	//}else{*/
		for(var i in result)
		{
			str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10" style="padding: 3px;">';
				str+='<div class="media" style="border:1px solid #ddd;height:100px">';
					/*str+='<div class="media-left">';
					   str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
				   str+=' </div>';*/
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   str+=' <p class="text-capital"><b>Organization: '+result[i].status+'</b></p>';
					   /*if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
							str+='<b><p class="text-capital">'+result[i].electionType+" "+result[i].committeeName+' Committee '+result[i].committeePosition+'</b></p>';
						if(result[i].designation != null && result[i].designation != "")
							str+='<b><p class="text-capital">'+result[i].designation+'</p></b>';*/
					  str+='  <p>'+result[i].source+'</p>';
					 // str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  
					  if(result[i].dateStr !=null && result[i].dateStr.length>0){
						  str+='<p><a>'+result[i].dateStr+'</a></p>';
					  }
					  /*if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success" style="margin-top: 7px;">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger" style="margin-top: 7px;">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral" style="margin-top: 7px;">N</span>';
					  }*/
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';
		}
	//}
	$("#involvedCandidatesCnt").html(result.length);	
	$("#alertCandidateDataId").html(str);
}
function getAlertStatusCommentsTrackingDetails()
{
	var alertId = $("#hiddenAlertId").val();
	$("#alertCommentsDivIdNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj={
				alertId:alertId,
				task:""
			}
	$.ajax({
	  type : 'GET',
	  url : 'getStatusWiseCommentsTrackingAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result != null && result.length > 0)
			alertComments(result);	
		else
			$("#alertCommentsDivIdNew").html("");
	});
	
}
function alertComments(result)
{
	var docName = '';
	var extName = [];
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){  
		$(".cadreAlertCommentsDivId").show();
		str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px;">';
	}
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
		statusId = result[i].statusId;
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
			if(length == i)    
			{
				str+='<a role="button" class="alertCommentColapse" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
			}else{
				str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="false" aria-controls="collapse'+i+'">';
			}
			if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';    
				str+='</h4>';
			}else{
				str+='<h4 class="panel-title">'+result[i].status+'';
					str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';
				str+='</h4>';
			} 
				str+='</a>';  
			str+='</div>';
			if(length == i)  
			{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
			}else{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
			}
				str+='<div class="panel-body" style="padding:5px;">';
					str+='<div class="row">';
						for(var j in result[i].govtDeptList){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date
								var dateArr = date.split("-");
								var year = dateArr[0];
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';
										str+='<div class="arrow_box_left">';
										//for(var k in result[i].sublist2[j].sublist)
										//{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:</span><br>';
												//str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												//for(var l in result[i].govtDeptList[j].sublist[k])
												//{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].govtDeptList[j].source+'<br>';
												//}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].govtDeptList[j].comment+'</p>';
												/*if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';          
													}
													str+='</ul>';              
												}*/
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].govtDeptList[j].name+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										//}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}           
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#alertCommentsDivIdNew").html(str)
}
function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}
$(document).on("click",".alertDetailsModalClose",function(){
	setTimeout(function(){
		$("body").addClass("modal-open")
	},500);
});
function assignedOfficersDetailsForAlert(alertId)
{
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
      type:'GET',
      url: 'getAssignedOfficersDetailsForAlertAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildAssignedOfficersDetailsForAlert(result);
	});
}
function buildAssignedOfficersDetailsForAlert(result)
{
	var str='';
	str+='<ul class="assignedOfficersUl">';
	for(var i in result)
	{
		str+='<li>';
			str+='<div class="media">';
				str+='<div class="media-left">';
					str+='<img src="" alt="" class="media-object"/>';
				str+='</div>';
				str+='<div class="media-body">';
					str+='<p><b>'+result[i].name+'</b></p>';
					str+='<p><b><i>'+result[i].department+'</i></b></p>';
					str+='<p>'+result[i].mobileNo+'</p>';
					str+='<p>'+result[i].designation+'</p>';
				str+='</div>';
			str+='</div>';
		str+='</li>';
	}
	str+='</ul>';
	$("#assignedOfficersId").html(str);
}
function getDepartmentLevels()
{
	var jsObj = {}
	$.ajax({
      type:'GET',
      url: 'getDepartmentLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<select name="alertAssigningVO.levelId" class="chosenSelect" id="locationLevelSelectId">	';
			str+='<option value="0">Select Level</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		str+='</select>';
		$("#locationLevelId").html(str);
		$(".chosenSelect").chosen();
	});
}
$(document).on('change','#locationLevelSelectId', function(evt, params) {
	var levelId = $(this).val();
	locationsBasedOnLevel(levelId)
});
function locationsBasedOnLevel(levelId)
{
	$("#constituencyLevelDiv").hide();
	$("#mndlMuncLevelDiv").hide();
	
	var jsObj = {
		levelId : levelId
	}
	$.ajax({
      type:'GET',
      url: 'getLocationsBasedOnLevelAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		if(levelId <= 4){
			str+='<option value="0">Select Location</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#locationsId").html(str);
			$("#locationsId").trigger("chosen:updated");
		}
		else{
			$("#constituencyLevelDiv").show();
			str+='<option value="0">Select Constituency</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#constLvlId").html(str);
			$("#constLvlId").trigger("chosen:updated");
		}
	});
}
$(document).on('change','#constLvlId', function() {
	$("#mndlMuncLevelDiv").hide();
	var levelId = $("#locationLevelSelectId").val();
	var constId = $(this).val();
	if(levelId == 5 || levelId == 6)
		getMandalsByConstituency(constId,levelId);
	else if(levelId == 7 || levelId == 8)
		getLebsByConstituency(constId,levelId);
});
function getMandalsByConstituency(constId,levelId){
	var jsObj = {
		constituencyId : constId
	}
	$.ajax({
      type:'GET',
      url: 'getMandalsForConstituencyAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			var str='';
			if(levelId == 6){
				$("#mndlMuncLevelDiv").show();
				str+='<option value="0">Select Mandal</option>';
				for(var i in result)
				{
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#mndlMuncLvlId").html(str);
				$("#mndlMuncLvlId").trigger("chosen:updated");
			}
			else{
				str+='<option value="0">Select Location</option>';
				for(var i in result)
				{
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#locationsId").html(str);
				$("#locationsId").trigger("chosen:updated");
			}
		}
	});
}

function getLebsByConstituency(constId,levelId){
	var jsObj = {
		constituencyId : constId
	}
	$.ajax({
      type:'GET',
      url: 'getLebsForConstituencyAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			var str='';
			if(levelId == 8){
				$("#mndlMuncLevelDiv").show();
				str+='<option value="0">Select Muncipality</option>';
				for(var i in result)
				{
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#mndlMuncLvlId").html(str);
				$("#mndlMuncLvlId").trigger("chosen:updated");
			}
			else{
				str+='<option value="0">Select Location</option>';
				for(var i in result)
				{
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#locationsId").html(str);
				$("#locationsId").trigger("chosen:updated");
			}
		}
	});
}

$(document).on('change','#mndlMuncLvlId', function() {
	var mandalId = $(this).val();
	var constituencyId = $("#constLvlId").val();
	getVillageAndWardsByMandal(mandalId,constituencyId);
});

function getVillageAndWardsByMandal(mandalId,constituencyId){
	var jsObj = {
		constituencyId : constituencyId,
		mandalId : mandalId
	}
	$.ajax({
      type:'GET',
      url: 'getPanchayatsMandalIdAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			var str = '';
			str+='<option value="0">Select Location</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#locationsId").html(str);
			$("#locationsId").trigger("chosen:updated");
		}
	});
}

$(document).on('change','#locationsId', function(evt, params) {
	departmentsByAlert()
});
function departmentsByAlert()
{
	var alertId = $("#hiddenAlertId").val();
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
      type:'GET',
      url: 'getDepartmentsByAlertAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<option val="0">Select Departments</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#departmentsId").html(str);
		$("#departmentsId").trigger("chosen:updated");
	});
}
$(document).on('change','#departmentsId', function(evt, params) {
	var departmentId = $(this).val();
	designationsByDepartment(departmentId)
});
function designationsByDepartment(departmentId)
{
	var LevelId = $("#locationLevelSelectId").chosen().val()
	
	var jsObj = {
		departmentId	: departmentId,
		levelId			: LevelId,
	}
	$.ajax({
      type:'GET',
      url: 'getDesignationsByDepartmentAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<option value="0">Select Designation</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#designationsId").html(str);
		$("#designationsId").trigger("chosen:updated");
	});
}
$(document).on('change','#designationsId', function(evt, params) {
	var designationId = $(this).val();
	officersByDesignationAndLevel(designationId)
});
function officersByDesignationAndLevel(designationId)
{
	var LevelId = $("#locationLevelSelectId").chosen().val()
	var LevelValue = $("#locationsId").chosen().val()
	
	var jsObj = {
		levelId				: LevelId,
		levelValue			: LevelValue,
		designationId		: designationId
	}
	$.ajax({
      type:'GET',
      url: 'getOfficersByDesignationAndLevelAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<option value="0">Select Officer</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#officerNamesId").html(str);
		$("#officerNamesId").trigger("chosen:updated");
	});
}
$(document).on("click","#assignOfficerId",function(){

	var uploadHandler = {
		upload: function(o) {
			uploadResult = o.responseText;
			displayStatus(uploadResult);
		}
	};

	YAHOO.util.Connect.setForm('alertAssignForm',true);
	YAHOO.util.Connect.asyncRequest('POST','assigningAlertToOfficerAction.action',uploadHandler); 
	
});

function displayStatus(myResult){
	
	var result = (String)(myResult);
	if(result.search('success') != -1){
		getAlertStatusCommentsTrackingDetails();
		alert("Alert Assigned Successfully.");
		$("#alertStatus").html('Notified');
		/*$("#uploadClarificationFileId0").val('');
		$("#extraClarificationUploadFileDiv").html('');
		$(".ClearFileCls").hide();  
		fileNo = 0;*/
	}else{
		alert("Please Try Again.");
	}
}
/* Alert Details Modal End*/

/* Departments Complete Overview Start*/
function buildDistrictWiseArticleRelatedToProblem(result){
	var str='';
	var distWiseArticlesRelated = [];
	str+='<div id="comaprisonDistrictWiseArticle" style="height:150px;"></div>';
	for(var i in result){
		var obj1 = {
			name: result[i].name,
			y: result[i].posPercent,
			extra:result[i].posCount
		};
		distWiseArticlesRelated.push(obj1);
	}
	$("#districtWiseArticleRelatedToProblem").html(str)
	$(function () {
		 $("#comaprisonDistrictWiseArticle").highcharts({
			colors: ['#AA3732'],
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
				
				type: 'category',
				labels: {
							formatter: function() {
								return this.value.toString().substring(0, 10)+'...';
							},
							
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
					enabled:false
				}
			},
			legend: {
				enabled: false
			},
			
					
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.y,1) + '%';
							}
						}
					  
					}
				}
			},

			tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}% - {point.extra}</b>'
			},

			series: [{
				name: 'Completed',
				data: distWiseArticlesRelated
			}],
		 
		});
	});

}
getTotalAlertGroupByStatusForOneDept();
function getTotalAlertGroupByStatusForOneDept(){
	
    var paperIdArr = [];      
    var chanelIdArr = [];     
    var jsObj ={
      fromDate:'10/02/2017',
      toDate:'10/02/2017',
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr       
    }  
    $.ajax({
      type:'GET',
      url: 'getTotalAlertGroupByStatusForOneDeptAction.action',
      data: {task :JSON.stringify(jsObj)}    
    }).done(function(result){
		
    });
}
getTotalAlertGroutByDeptThenStatus();
function getTotalAlertGroutByDeptThenStatus(){
	
    var paperIdArr = [];                      
    var chanelIdArr = [];              
    var jsObj ={
      fromDate:'10/02/2017',
      toDate:'10/02/2017',
      stateId : globalStateId,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr       
    }
    $.ajax({
      type:'GET',    
      url: 'getTotalAlertGroutByDeptThenStatusAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		
    });
}
getDistrictWiseTotalForAlertOverview()
getDistrictTotalForAlertStatus()
function getDistrictWiseTotalForAlertOverview(){
	$("#statusWiseTotalDiv").html("");
	var deptIdArr = [];
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = [];
	}
	
     var paperIdArr = [2,8,11];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      newsPaperIdArr : paperIdArr,
      newChanelIdArr : chanelIdArr    
    }
	$.ajax({
	  type : 'GET',
	  url : 'getDistrictWiseTotalAlertsForAlertAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
			buildStatusWiseTotalAlerts(result)
	});
	
}
function getDistrictTotalForAlertStatus()
{
	var deptIdArr = [];
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = [1,2,3,4];
	}
	
     var paperIdArr = [2,8,11];
    var chanelIdArr = [1,2,3,4,5,6,7];
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      newsPaperIdArr : paperIdArr,
      newChanelIdArr : chanelIdArr     
    }
	$.ajax({
	  type : 'GET',
	  url : 'getStatusWiseDistrictTotalForAlertAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
			
	});
	
}
function buildStatusWiseTotalAlerts(result){
	var totalAlerts = 0;
	 var str='';
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<th></th>';
					str+='<th class="bg_EE">Total</th>';
					for(var k in result[0].govtDeptList){
						str+='<th>'+result[0].govtDeptList[k].department+'</th>';
					}
			str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<td class="bg_D8">Total Alerts</td>';
						var totalCount=0;
						var totalElecCount =0;
						for(var j in result[0].govtDeptList){
							totalElecCount=totalElecCount+result[0].govtDeptList[j].elecCnt;
						}
						var totalPrintCount =0;
						for(var k in result[0].govtDeptList){
							totalPrintCount = totalPrintCount+result[0].govtDeptList[k].printCnt;
						}
						totalCount = totalPrintCount+totalElecCount;
						str+='<td class="bg_D8">'+totalCount+'</td>';
						for(var i in result[0].govtDeptList){
							str+='<th>'+result[0].govtDeptList[i].count+'</th>';
						}
					str+='</tr>';
					 str+='<tr>';
						str+='<td>Print Media Alerts</td>';
						str+='<td class="bg_EE">'+totalPrintCount+'</td>';
						for(var k in result[0].govtDeptList){
							str+='<th>'+result[0].govtDeptList[k].printCnt+'</th>';
						}
					str+='</tr>';
					str+='<tr>';
						str+='<td>Electronic Media Alerts</td>';
						str+='<td class="bg_EE">'+totalElecCount+'</td>';
						for(var j in result[0].govtDeptList){
							str+='<th>'+result[0].govtDeptList[j].elecCnt+'</th>';
						}
					str+='</tr>'; 
				str+='</tbody>';
			str+='</table>';
			str+='<button class="btn btn-default btnBorder pull-right"></button>';
		$("#statusWiseTotalDiv").html(str);
} 
/* Departments Complete Overview End*/