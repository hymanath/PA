/*global Function and variables Start*/
var currentFromDate = moment().subtract(1,"month").format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");
var globalStateId = 1;  
var globalNewsPaperIdArr = [];
var globalChannelIdArr = [];
var globalDepartmentIdArr = [];
/* Global Filter Arreys Start*/

$(".newsPaperListCls").each(function(){
	if($(this).is(":checked"))
	{
		globalNewsPaperIdArr.push($(this).attr("attr_val"));
	}
});
$(".chanelListCls").each(function(){
	if($(this).is(":checked"))
	{
		globalChannelIdArr.push($(this).attr("attr_val"));
	}
});
$(".departmentsCls").each(function(){
	if($(this).is(":checked"))
	{
		globalDepartmentIdArr.push($(this).attr("attr_val"));
	}
});
/* OnLoad Calls Start*/
onLoadCalls();
initializeFile();
/* OnLoad Calls ENd*/

/* Global Filter Arreys End*/
$(document).on("click",".filtersSubmitDivId",function(){
	globalNewsPaperIdArr = [];
	globalChannelIdArr = [];
	globalDepartmentIdArr = [];
	$(".newsPaperListCls").each(function(){
		if($(this).is(":checked"))
		{
			globalNewsPaperIdArr.push($(this).attr("attr_val"));
		}
	});
	$(".chanelListCls").each(function(){
		if($(this).is(":checked"))
		{
			globalChannelIdArr.push($(this).attr("attr_val"));
		}
	});
	$(".departmentsCls").each(function(){
		if($(this).is(":checked"))
		{
			globalDepartmentIdArr.push($(this).attr("attr_val"));
		}
	});
	onLoadCalls();
});
$(document).on("click",".selectAlldepartmentsCls",function(){
	if($(this).prop('checked')) {
		$(".departmentsCls").prop('checked', true);
	}else{
		$(".departmentsCls").prop('checked', false);
	}
});
$(document).on("click",".selectAllChannelsCls",function(){
	if($(this).prop('checked')) {
		$(".chanelListCls").prop('checked', true);
	}else{
		$(".chanelListCls").prop('checked', false);
	}
});
$(document).on("click",".selectAllPaperCls",function(){
	if($(this).prop('checked')) {
		$(".newsPaperListCls").prop('checked', true);
	}else{
		$(".newsPaperListCls").prop('checked', false);
	}
});

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
	getDistrictTotalForAlertStatus('','')
});
$(".chosenSelect").chosen({width:'100%'});

function onLoadCalls()
{
	totalAlertGroupByStatusForGovt();
	totalAlertGroupByStatusThenDepartment();
	getDistrictWiseTotalForAlertOverview();
}
$(document).on("click",".settingsIcon",function(e){
	$(this).closest(".panel").find(".settingsBlockDropDown").toggle();
	e.stopPropagation();
});
$(document).on("click",".setClose",function(){
	$(this).closest(".settingsBlockDropDown").hide();
});
$(document).on("click",function(){
	$(".documentCloseClass").hide();
});
$(document).on("click",".documentCloseClass",function(e){
	e.stopPropagation();
});
$(".scrollerBlockDepartments").mCustomScrollbar({setHeight:'300px'});

/*Default Image*/
function setDefaultImage(img){
    img.src = "images/User.png";
}
/*Default Image*/
/*global Function and variables End*/
/* Status OverView Start*/
function totalAlertGroupByStatusForGovt()
{
	$("#statusOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr = globalDepartmentIdArr;
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
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
    var deptIdArr = globalDepartmentIdArr;
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
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
			str+='<li role="presentation" class="active text-capital"><a href="#alertStaTab'+i+'" aria-controls="alertStaTab'+i+'" role="tab" data-toggle="tab">'+result[i].status+'</a></li>';
		}else{
			str+='<li role="presentation" class="text-capital"><a href="#alertStaTab'+i+'" aria-controls="alertStaTab'+i+'" role="tab" data-toggle="tab">'+result[i].status+'</a></li>';
		}
	}
	str+='</ul>';
	str+='<div class="tab-content">';
	for(var i in result)
	{
		if(i == 0)
		{
			str+='<div role="tabpanel" class="tab-pane active" id="alertStaTab'+i+'">';
		}else{
			str+='<div role="tabpanel" class="tab-pane" id="alertStaTab'+i+'">';
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
			dynamicHeight = (dynamicHeight*36)+"px";
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
		deptIdArr = globalDepartmentIdArr;
	}
	
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
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
		deptIdArr = globalDepartmentIdArr;
	}
	
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
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
					str+='<td><button class="btn btn-success alertDetailsModalCls" attr_alert_Id="'+result[i].id+'" attr_status_id="'+result[i].statusId+'">Alert Details</button></td>';      
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
	fieldsEmpty();
	
	var alertId = $(this).attr("attr_alert_Id");
	var alrtStsId = $(this).attr("attr_status_id");
	if(alrtStsId != null && alrtStsId == 1)
		$("#alertAssign").show();
	
	$("#hiddenAlertId").val(alertId);   //3725
	getAlertData(alertId);
	getInvolvedMembersDetilas(alertId);
	getAlertStatusCommentsTrackingDetails();
	departmentsByAlert();
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
		if(result != null && result.length > 0){
			buildAlertData(result);
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
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
			location +='<b>'+result[i].locationVO.state+' ';
		}
		if(result[i].locationVO.districtId !=null){
			location +=' , '+result[i].locationVO.districtName+' District ';
		}
		if(result[i].locationVO.constituencyId !=null){
			location +=' , '+result[i].locationVO.constituencyName+' Constituency ';
		}
		
		if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
			location +=' , '+result[i].locationVO.localEleBodyName+' Municipality ';
		}
		if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
			location +=' , '+result[i].locationVO.tehsilName+' Mandal ';
		}
		if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
			location +=' , '+result[i].locationVO.wardName+' Ward ';
		}
		if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
			location +=' , '+result[i].locationVO.villageName+' Panchayat </b>';
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
function getGroupedArticlesInfo(articleId)
{
	$.ajax({
		  type : 'GET',      
		  //url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		  url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
	}).then(function(result){
		
		var str='';
		if(result !=null && result.length>0){
			$("#alertGroupAttachId").show();
			str+='<ul class="list-inline">';
			for(var i in result)
			{
				if(articleId != result[i].id){
					str+='<li class="articleImgDetailsCls" attr_articleId='+result[i].id+' style="cursor:pointer"><img src="http://mytdp.com/NewsReaderImages/'+result[i].name+'" style="width: 150px; height: 150px;margin-top:5px;"></img></li>';
				}
			}
			str+='</ul>';
			$("#alertGroupAttachUlId").html(str);
		}
	});
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
		if(result == null || result.length == 0)
		{
			$("#alertCandidateDataId").html('No Involved Members..');
		}else{
			buildAlertCandidateData(result);
		}
	});
}
function buildAlertCandidateData(result,categoryId)
{

	var str='';
	
	for(var i in result)
	{
		str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10">';
			str+='<div class="media" style="border:1px solid #ddd;padding:8px;">';
				str+='<div class="media-left">';
					str+=' <img src="'+result[i].image+'" class="media-object img-circle"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;height:50px;"/>';
				str+=' </div>';
				str+=' <div class="media-body">';
					if(result[i].impactLevelId == 1)
					{
						str+=' <span class="label label-success pull-right" style="margin-top: 7px;">+ Ve</span>'; 
					}else if(result[i].impactLevelId == 2){
						str+=' <span class="label label-danger pull-right" style="margin-top: 7px;">- Ve</span>';
					}else{
						str+=' <span class="label label-neutral pull-right" style="margin-top: 7px;">N</span>';
					}
					if(result[i].name != null && result[i].name != "")
						str+=' <p class="text-capital"><span class="text-muted">Name :</span> <b>'+result[i].name+'</b></p>';
					str+=' <p class="text-capital"><span class="text-muted">Department: </span><b>'+result[i].status+'</b></p>';
					if(result[i].designation != null && result[i].designation != "")
					{
						str+='<p class="text-capital"><span class="text-muted">Designation</span>'+result[i].designation+'</p>';
					}
					str+='  <p>'+result[i].source+'</p>';
					if(result[i].dateStr !=null && result[i].dateStr.length>0){
						str+='<p><a>'+result[i].dateStr+'</a></p>';
					}
				str+='  </div>';
			str+='</div>';
	   str+=' </div>';
	}
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
			if(result[i].govtDeptList != null && result[i].govtDeptList.length > 0){
				if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';    
				str+='</h4>';
				}else{
					str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].govtDeptList[0].dateStr+'</span>';
					str+='</h4>';
				} 
			}else{
				if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i>';    
				str+='</h4>';
				}else{
					str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-hourglass"></i>';
					str+='</h4>';
				} 
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
						
					if(result[i].govtDeptList != null && result[i].govtDeptList.length > 0){
						for(var j in result[i].govtDeptList){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].govtDeptList[j].dateStr
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
					}else{
						str+='<div class="col-md-12 col-xs-12 col-sm-12"><div  style="height:200px;background-color:#EEE"><h4 class="panel-title text-capital text-center" style="padding-top:80px !important;">please assign alert to officer</h4></div></div>';
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
	$("#assignedOfcrCountId").html('<span>assigned officers - '+result.length+' ');
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
/* function getDepartmentLevels()
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
} */
$(document).on('change','#locationLevelSelectId', function(evt, params) {
	var levelId = $(this).val();
	locationsBasedOnLevel(levelId);
	designationsByDepartment();	
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

/*$(document).on('change','#locationsId', function(evt, params) {
	designationsByDepartment();
}); */
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
		str+='<option value="0">Select Departments</option>';
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
	getLocationLevels(departmentId);
});
function designationsByDepartment()
{
	var LevelId = $("#locationLevelSelectId").chosen().val();
	var deprtmntId = $("#departmentsId").chosen().val();
	
	var jsObj = {
		departmentId	: deprtmntId,
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
if(!fieldsValidation())
	{
		return;
	}
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
		fieldsEmpty();
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
/* Alert DepartMents Wise*/
function getDistrictWiseTotalForAlertOverview(){
	$("#alertDepartmentWise").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var deptIdArr = [];
	var deptId = $(this).attr("attr_dept_id");
	if(deptId != null){
		deptIdArr.push(deptId);  
	}else{
		deptIdArr = globalDepartmentIdArr;
	}
	
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
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
function getDistrictTotalForAlertStatus(id,departmentId)
{
	
	var deptIDArr = [];
	deptIDArr.push(departmentId)
	
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIDArr,  
      newsPaperIdArr : paperIdArr,
      newChanelIdArr : chanelIdArr     
    }
	$.ajax({
	  type : 'GET',
	  url : 'getStatusWiseDistrictTotalForAlertAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		buildgetUserTypeWiseNewsForTopFiveStrongResults(result,id)
	});
	
}
$(document).on("click",".activeUlCls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var id = $(this).attr("attr_id");
	var value = $(this).attr("attr_value");
	if(value == 'overview')
	{
		$("#districtWiseOverViewAndStatusDiv"+id).hide();
		$("#departmentWiseOverView"+id).show();
	}else if(value == 'status'){
		var departmentId = $(this).attr("attr_dept_id");
		getDistrictTotalForAlertStatus(id,departmentId);
		$("#districtWiseOverViewAndStatusDiv"+id).show();
		$("#departmentWiseOverView"+id).hide();
	}
});

function buildStatusWiseTotalAlerts(result){
	var totalAlerts = 0;
	 var str='';
 	 str+='<div class="scrollerBlock">';
	 for(var i in result)
	 {
		 str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="panel panel-default panelCustom">';
				str+='<div class="panel-heading headingColor">';
					str+='<h4 class="panel-title fontColor">'+result[i].department+'</h4>';
				str+='</div>';
				str+='<div class="panel-body">';
					str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<h4 class="panel-title text-capital">district wise total alerts</h4>';
						str+='</div>';
						str+='<div class="col-md-8 col-xs-12 col-sm-8">';
							str+='<ul class="list-inline activeUlCls ">';
								str+='<li class="active" attr_id='+i+'  attr_value="overview">Overview</li>';
								str+='<li attr_id='+i+' attr_dept_id='+result[i].departmentId+' attr_value="status">Status</li>';
							str+='</ul>';
						str+='</div>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
							 str+='<div id="districtWiseOverViewAndStatusDiv'+i+'"></div>';
						str+='</div>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20" id="departmentWiseOverView'+i+'">';
							str+='<div id="overViewGraph'+i+'" style="height:130px"></div>';
						str+='</div>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
							str+='<table class="table table-bordered">';
								str+='<thead>';
									str+='<th></th>';
									str+='<th class="bg_EE">Total</th>';
									for(var k in result[i].govtDeptList){
										str+='<th>'+result[i].govtDeptList[k].department+'</th>';
									}
								str+='</thead>';
								str+='<tbody>';
									str+='<tr>';
										str+='<td class="bg_D8">Total Alerts</td>';
										var totalCount=0;
										var totalElecCount =0;
										for(var j in result[i].govtDeptList){
											totalElecCount=totalElecCount+result[i].govtDeptList[j].elecCnt;
										}
										var totalPrintCount =0;
										for(var k in result[i].govtDeptList){
											totalPrintCount = totalPrintCount+result[i].govtDeptList[k].printCnt;
										}
										for(var t in result[i].govtDeptList){
										}
										
										totalCount = totalPrintCount+totalElecCount;
										if(totalCount != null && totalCount!= 0){
											str+='<td class="bg_D8"><span class="totAlertsStsCls" attr_status_id="0" attr_dept_id ='+result[i].departmentId+' attr_type="">'+totalCount+'</span></td>';
										}else{
											str+='<td class="bg_D8">'+totalCount+'</td>';
										}
										for(var t in result[i].govtDeptList){
											if(result[i].govtDeptList[t].count != null && result[i].govtDeptList[t].count != 0){
												str+='<td><span class="totAlertsStsCls" attr_status_id="'+result[i].govtDeptList[t].departmentId+'" attr_dept_id ='+result[i].departmentId+' attr_type="Totals">'+result[i].govtDeptList[t].count+'</span></td>';	
											}else{
												str+='<td>'+result[i].govtDeptList[t].count+'</td>';
											}
										}
									str+='</tr>';
									 str+='<tr>';
										str+='<td>Print Media Alerts</td>';
										if(totalPrintCount != null && totalPrintCount!= 0){
											str+='<td class="bg_EE"><span class="totAlertsStsCls" attr_status_id="0" attr_dept_id ='+result[i].departmentId+' attr_type="PMedia" >'+totalPrintCount+'</span></td>';
										}else{
											str+='<td class="bg_EE">'+totalPrintCount+'</td>';
										}	
										for(var k in result[i].govtDeptList){
											if( result[i].govtDeptList[k].printCnt != null && result[i].govtDeptList[k].printCnt != 0){
													str+='<td><span class="totAlertsStsCls" attr_status_id="'+result[i].govtDeptList[k].departmentId+'" attr_dept_id ='+result[i].departmentId+' attr_type="PMedia" >'+result[i].govtDeptList[k].printCnt+'</span></td>';
											}else{
												str+='<td>'+result[i].govtDeptList[k].printCnt+'</td>';
											}
										}
									str+='</tr>';
									
									str+='<tr>';
										str+='<td>Electronic Media Alerts</td>';
										if(totalElecCount != null && totalElecCount !=0){
											str+='<td class="bg_EE"><span class="totAlertsStsCls" attr_status_id="0" attr_dept_id ='+result[i].departmentId+' attr_type="electronic">'+totalElecCount+'</span></td>';
										}else{
											str+='<td class="bg_EE">'+totalElecCount+'</td>';
										}
										for(var j in result[i].govtDeptList){
											if(result[i].govtDeptList[j].elecCnt != null && result[i].govtDeptList[j].elecCnt !=0){
												str+='<td><span class="totAlertsStsCls" attr_status_id="'+result[i].govtDeptList[j].departmentId+'" attr_dept_id ='+result[i].departmentId+' attr_type="electronic">'+result[i].govtDeptList[j].elecCnt+'</span></td>';
											}else{
												str+='<td>'+result[i].govtDeptList[j].elecCnt+'</td>';
											}	
										}
									str+='</tr>'; 
								str+='</tbody>';
							str+='</table>';
							str+='<button type="button" class="btn btn-default btn-sm m_top10 buttonCustomStyle detailedInfoBlockDiv pull-right" attr_departmentId="'+result[i].departmentId+'">Detailed Information</button>';
						str+='</div>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							str+='<div id="designationDetailedReport'+result[i].departmentId+'"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	 }
	str+='</div>';
	$("#alertDepartmentWise").html(str);
	if(result.length > 5)
	{
		$(".scrollerBlock").mCustomScrollbar({setHeight:'800px'});
	}
	for(var i in result)
	{		
		var alertStatusOverviewCount = [];
		var alertStatusOverview=[];
		for(var j in result[i].govtDepartmentVOList)
		{
			alertStatusOverview.push(result[i].govtDepartmentVOList[j].department)
			alertStatusOverviewCount.push(result[i].govtDepartmentVOList[j].count)
		}
		$("#overViewGraph"+i).highcharts({
				colors: ['#31AA74'],
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
					categories: alertStatusOverview,
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
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return (this.y);
								}
							}
						  
						}
					}
				},

				 tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								
								(this.y);
						});

						return s;
					},
					shared: true
				},

				series: [{
					name: 'Positive',
					data: alertStatusOverviewCount,
				}],
			 
			});

	}
} 
// District wise Total alerts overview and status Block Build Start
	function buildgetUserTypeWiseNewsForTopFiveStrongResults(result,id){
		var str='';
		
		if(result != null && result.length > 0){
			str+='<div class="statusOverviewGraph">';
				str+='<ul class="statusOverviewGraphUl">';
				for(var i in result){ //district
					str+='<li>';
					str+='<h4 class="panel-title">'+result[i].department+'</h4>';
					str+='<span>'+result[i].totalCount+'</span>';
					str+='<div id="statusOverview'+result[i].departmentId+''+id+'" style="height:130px;width:250px;"></div>';
					
					str+='</li>';
				}
				str+='</ul>';
			str+='</div>';
		}
		$("#districtWiseOverViewAndStatusDiv"+id).html(str);
		
		for(var i in result){
			var alertStatusCount = [];
			var alertStatus=[];
			for(var j in result[i].govtDepartmentVOList){
				alertStatus.push(result[i].govtDepartmentVOList[j].department)
				alertStatusCount.push(result[i].govtDepartmentVOList[j].count)
			}
			
			$("#statusOverview"+result[i].departmentId+''+id).highcharts({
				colors: ['#D33E39','#64C664'],
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
					categories: alertStatus,
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
									return (this.y);
								}
							}
						  
						}
					}
				},

				 tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},

				series: [{
					name: '',
					data: alertStatusCount,
					
				}],
			 
			});
		}
		
	}
/* Departments Complete Overview End*/

$(document).on("click",".articleDetailsCls",function(){
	var articleId= $(this).attr("attr_articleId");
	$("#myModalShowNew").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	getTotalArticledetails(articleId);
});
$(document).on("click",".closeArticlePopup",function(){
	setTimeout(function(){
		$("body").addClass("modal-open")
	},500);
});
function getTotalArticledetails(articleId){
	
	$("#linkedDocHeader,#linkedDocBody").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
	}).then(function(results){
		var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
		var result = results[0];
		var str = '';
		heading+='<h4 class="modal-title" id="myModalLabel">';
			heading+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
			heading+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
		heading+='</h4>';
		str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
				str+='<h4 class="panel-title text-success">Description</h4>';
				str+='<p class="m_0 f_14">'+result.description+'</p>';
			str+='</div>';
			str+='<div class="col-md-12">';
			if( result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
				/* Candidate*/
				str+='<div class="row ">';
					str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
											str+='<tr>';
												if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
												str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
												}
												str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td colspan="2">';
												var candidataExist = false;
												if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
												candidataExist = true; 
												str+=''+result.subList[i].fromList[j].candidateName;
												}
												if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
												candidataExist = true; 
												str+=' ('+result.subList[i].fromList[j].designation + ")";
												}
												if(!candidataExist){
												str+=' - ';
												}
												str+='</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td colspan="2">';
													if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
														str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
													}else{ 
														str+='<p class="m_0">Impact Level : - </p>';	
													}
													if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
														str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
													}else{ 
														str+='<p class="m_0">Category : - </p>';	
													}
													if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
														str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
													}else{ 
														str+='<p class="m_0">News Activity : - </p>';	
													}
													if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
														str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
													}else{ 
														str+='<p class="m_0">News type : - </p>';	
													}
													if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
													if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
														str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
													}else{ 
														str+='<p class="m_0">News Related : - </p>';	
													}
													if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
														str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
													}else{ 
														str+='<p class="m_0">Priority : - </p>';	
													}
													if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
														str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
													}else{ 
														str+='<p class="m_0">Solution : - </p>';	
													}
													}
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
						str+='</div>';//panel
					str+='</div>';//colmd6
					str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
							/* TO Table*/
							if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
								for( var j in result.subList[i].toList){
									str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
											if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
												str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
											}else{
												str+='<td> - </td>';
											}
												str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
											str+='<td colspan="2">';
											var candidataExist = false;
											if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
												str+=''+result.subList[i].toList[j].candidateName;
											}
											if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
											candidataExist = true; 
												str+=' ('+result.subList[i].toList[j].designation + ")";
											}
											if(!candidataExist){
												str+=' - ';
											}
											str+='</td>';
										str+='</tr>';
										str+='<tr>';
											str+='<td colspan="2">';

												if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
													str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
												}else{ 
													str+='<p class="m_0">Impact Level : - </p>';	
												}

												if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
													str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
												}else{ 
													str+='<p class="m_0">Category : - </p>';	
												}
												if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
													str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
												}else{ 
													str+='<p class="m_0">News Activity : - </p>';	
												}
												if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
													str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
												}else{ 
													str+='<p class="m_0">News type : - </p>';	
												}
												if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){

												if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
													str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
												}else{ 
													str+='<p class="m_0">News Related : - </p>';	
												}
												if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
													str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
												}else{ 
													str+='<p class="m_0">Priority : - </p>';	
												}
												if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
													str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
												}else{ 
													str+='<p class="m_0">Solution : - </p>';	
												}
												}
											str+='</td>';
										str+='</tr>';
									str+='</table>';
								}
							}

							str+='</div>';//panelbody
						str+='</div>';//panel
					str+='</div>';//colmd6

				str+='</div>';//row
				}
			}

			str+='</div>';//colmd12
		str+='</div>';//row
		/* Article Scope Location */
		str+='<div class="col-md-12">';
			str+='<div class="panel panel-default panelArticleGroup">';
				str+='<div class="panel-heading">';
					str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
				str+='</div>';
				str+='<div class="panel-body">';
					str+='<table class="table table-condensed">';
						str+='<tr>';
							str+='<td>Impact Scope : </td>';
							if(result.impactScopeId!=null){
								str+='<td>'+obj[result.impactScopeId]+'</td>';
							}else{
								str+='<td> - </td>';
							}
						str+='</tr>';
						str+='<tr>';
							str+='<td>Location : </td>';
							if(result.scopeLocation!=null){
								str+='<td>'+result.scopeLocation+'</td>';
							}else{
								str+='<td> - </td>';
							}
						str+='</tr>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row">';
			/*Lnking*/
			str+='<div class="col-md-6">';
				str+='<div class="panel panel-default panelArticleGroup">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						if( result.linkedList != null && result.linkedList.length > 1){
							str+='<div class="row">';
								for( var i in result.linkedList){
									if(result.linkedList[i].articleId !=articleId ){
										str+='<div class="col-md-4" style="margin-top:5px;">';
											str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
										str+='</div>';
									}
								}
							str+='</div>';
						}else{
							str+="<h5> No Linked Articles Available </h5>";
						}

					str+='</div>';
				str+='</div>';
			str+='</div>'; 
		str+='</div>';

		$("#linkedDocBody").html(str);
		$("#linkedDocHeader").html(heading)
	});    
}
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3){
		wurl = url.substr(0,(url.indexOf(".in")+3));
	}
google.load("elements", "1", {
	packages: "transliteration"
 }); 
 
var control;
var lang;

function languageChangeHandler(){
	   var lang1 = $("input[name=Lang]:checked").val();
		if(lang1 =="en"){
			control.disableTransliteration();
		}else{
			control.enableTransliteration();
			control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
		}
	}
	
google.setOnLoadCallback(onLoad);
function onLoad() {
	   lang = $("input[name=Lang]:checked").val();
		var options = {
			sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage:[''+lang+''],
			shortcutKey: 'alt+t',
			transliterationEnabled: true
		};
		// Create an instance on TransliterationControl with the required options.
		control = new google.elements.transliteration.TransliterationControl(options);
		// Enable transliteration in the textbox with id 'descrptionId'.
	 	if ($('#alertDescId').length){
			control.makeTransliteratable(['alertDescId']);
		}
   }	
   
function fieldsValidation(){
	$("#errMsgLvlId").html("");
	$("#errMsgLocationId").html("");
	$("#errMsgDeptId").html("");
	$("#errMsgDesgId").html("");
	$("#errMsgOffcrId").html("");
	$("#errMsgCmntId").html("");
	$("#errMsgImgId").html("");
	
	var deptId = $("#departmentsId").val();
	var levelId = $("#locationLevelSelectId").val();
	var locationId = $("#locationsId").val();
 	var designationId = $("#designationsId").val();
	var offcierId = $("#officerNamesId").val(); 
	var comments = $("#alertDescId").val();
	//var image = $("#imageId").val();
	
	if(deptId == 0){
		$("#errMsgDeptId").html("Select Department.");
		return false;
	}
	if(levelId == 0 ){
		$("#errMsgLvlId").html("Select Location Level");
		return false;
	}
	if(locationId == 0){
		$("#errMsgLocationId").html("Select Location.");
		return false;
	}
	if(designationId == 0){
		$("#errMsgDesgId").html("Select Designation.");
		return false;
	}
	if(offcierId == 0){
		$("#errMsgOffcrId").html("Select OfficerName.");
		return false;
	} 
	if(comments.length == 0){
		$("#errMsgCmntId").html("Enter Comments.");
		return false;
	}
	/* if( image == null || image.length == 0 ){
		$("#errMsgImgId").html("Attachment Required.");
		return false;
	} */
	
	return true;
}

function fieldsEmpty(){
	$("#departmentsId").val(0);
	$("#departmentsId").trigger("chosen:updated");
	$("#locationLevelSelectId").val(0);
	$("#locationLevelSelectId").trigger("chosen:updated");
	$("#constLvlId").empty();
	$("#constLvlId").trigger("chosen:updated");
	$("#mndlMuncLvlId").empty();
	$("#mndlMuncLvlId").trigger("chosen:updated");
	$("#locationsId").empty();
	$("#locationsId").trigger("chosen:updated");
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	$("#telugu").prop("checked", true);
	$("#alertDescId").val('');
	var filerKit = $("#imageId").prop("jFiler");
	filerKit.reset();
}

function getLocationLevels(departmentId){
	var jsObj = {
		departmentId : departmentId 
	}
	$.ajax({
      type:'GET',
      url: 'getLevelsByDeptIdAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<option value="0">Select Level</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#locationLevelSelectId").html(str);
		$("#locationLevelSelectId").trigger("chosen:updated");
	});
}
//District wise total alerts click action 
$(document).on("click",".totAlertsStsCls",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	var deptIdArr = [];
	var paperIdArr = [];
	var chanelIdArr = [];
	var alertStatusId = $(this).attr("attr_status_id");
	var deptId = $(this).attr("attr_dept_id");
		deptIdArr.push(deptId);  
	var type = $(this).attr("attr_type");
	if(type == "PMedia"){
		paperIdArr = [1,2,3,4,5,7,8,10,11,12,13,14,15,16,17,18];
	}else if(type == "electronic"){
		chanelIdArr = [1,2,3,4,5,6,7];
	}else if(type == "Totals"){
		paperIdArr = [1,2,3,4,5,7,8,10,11,12,13,14,15,16,17,18];
		chanelIdArr = [1,2,3,4,5,6,7];
	}
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr,
	  statusId : alertStatusId ,
		type   :  type   
    }
    $.ajax({
      type:'GET',
      url: 'getTotalAlertByStatusForDeptWiseClickAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildtotalAlertsModalTabId(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
});	
/* Departments Complete Overview End*/

$(document).on("click",".detailedInfoBlockDiv",function(){
	var departmentId= $(this).attr("attr_departmentId");
	getDesigAndStatusWiseAlertsCounts(departmentId);
});	

function getDesigAndStatusWiseAlertsCounts(departmentId){
	$("#designationDetailedReport"+departmentId).html(' ');
	$("#designationDetailedReport"+departmentId).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');

    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
    var jsObj ={
	  departmentId:departmentId,	
	  stateId : globalStateId,
      fromDate:currentFromDate,
      toDate:currentToDate,
      paperIdArr : paperIdArr,
      chanelIdArr : chanelIdArr    
    }
	$.ajax({
		type : 'GET',
		url : 'getDesigAndStatusWiseAlertsCountsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		buildDesigAndStatusWiseAlertsCounts(result,departmentId);
	});
	
}
function buildDesigAndStatusWiseAlertsCounts(result,departmentId)
{
	if(result != null && result.length > 0){	
		var str1='';
		str1+='<table class="table detailedTableStyle  m_top20" style="border:1px solid #ddd">';
			str1+='<thead>';
				str1+='<tr>';
				str1+='<th>Designation</th>';
				str1+='<th style="background-color:#f3f3f3">Total</th>';
					if(result[0].govtDeptList !=null && result[0].govtDeptList.length>0){
						for(var j in result[0].govtDeptList){
							
								str1+='<th>'+result[0].govtDeptList[j].name+'</th>';
							
						}
					}
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				var totalCount = 0
				var Notified = 0;
				var ActionInProgess = 0;
				var Completed = 0;
				var UnabletoResolve = 0;
				var ActionNotRequired = 0;
				var Duplicate = 0
				str1+='<tr>';
					for(var i in result){
						totalCount = totalCount + result[i].count;
						Notified = Notified + result[i].govtDeptList[0].count
						ActionInProgess = ActionInProgess + result[i].govtDeptList[1].count
						Completed = Completed + result[i].govtDeptList[2].count
						UnabletoResolve = UnabletoResolve + result[i].govtDeptList[3].count
						ActionNotRequired = ActionNotRequired + result[i].govtDeptList[4].count
						Duplicate = Duplicate + result[i].govtDeptList[5].count
					}
					str1+='<td style="background-color:#eee">TOTAL</td>';
					str1+='<td style="background-color:#eee">'+totalCount+'</td>';
					str1+='<td style="background-color:#eee">'+Notified+'</td>';
					str1+='<td style="background-color:#eee">'+ActionInProgess+'</td>';
					str1+='<td style="background-color:#eee">'+Completed+'</td>';
					str1+='<td style="background-color:#eee">'+UnabletoResolve+'</td>';
					str1+='<td style="background-color:#eee">'+ActionNotRequired+'</td>';
					str1+='<td style="background-color:#eee">'+Duplicate+'</td>';
				str1+='</tr>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td>'+result[i].name+'</td>';
						str1+='<td style="background-color:#f3f3f3">'+result[i].count+'</td>';
						if(result[i].govtDeptList !=null && result[i].govtDeptList.length>0){
							for(var j in result[i].govtDeptList){
								str1+='<td>'+result[i].govtDeptList[j].count+'</td>';
							}
						}
					str1+='</tr>';
				}
			str1+='</tbody>';
		str1+='</table>';
		$("#designationDetailedReport"+departmentId).html(str1);
	}else{
		$("#designationDetailedReport"+departmentId).html("No Data Available");
	}
}