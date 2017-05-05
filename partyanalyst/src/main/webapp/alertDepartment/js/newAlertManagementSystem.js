/*global Function and variables Start*/
 var globalLevelObj =  {"1":"STATE","2":"ZONE","3":"REGION","4":"CIRCLE","5":"DISTRICT","6":"DIVISION","7":"SUB DIVISION","8":"MANDAL","9":"MUNICIPALITY","10":"PANCHAYAT"};
var globalAlertSourceColorObj =  {"Manual":"#E54BB3","Print Media":"#69BC6E","Electronic Media":"#8D69C8","Call Center":"#EFC000","Facebook":"#00ABED","Twitter":"#F7776C","Social Media":"#05ABHY"};	 
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var globalStateId = 1;  
var globalNewsPaperIdArr = [];
var globalChannelIdArr = [];
var globalDepartmentIdArr = [];
var globalUserLevelId=0;
var globalUserLevelValues = [0];
var globalDesignationId=0;
var globalCallCenterArr = [];
var subLevels = [];
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

/* OnLoad Calls Start*/
onLoadInitialisations();
onLoadCalls111();

/* OnLoad Calls ENd*/

/* Global Filter Arreys End*/
function onLoadInitialisations()
{
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
	$(".callcenterCls").each(function(){
		if($(this).is(":checked"))
		{
			globalCallCenterArr.push($(this).attr("attr_val"));
		}
	});
	
	$(document).on("click",".switch-btn li",function(){
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var type= $(this).attr("attr_type");
		if(type == 'status')
		{
			getDepartmentStatus();
			getDepartmentWiseAlertOverviewCnt('status','0');
		}else if(type == 'department'){
			getDepartmentScope();
			getDepartmentWiseAlertOverviewCnt('department','0');
		}
	});
	
	$(document).on("click",".filtersSubmitDivId",function(){
		globalNewsPaperIdArr = [];
		globalChannelIdArr = [];
		globalDepartmentIdArr = [];
		globalCallCenterArr = [];
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
		$(".callcenterCls").each(function(){
			if($(this).is(":checked"))
			{
				globalCallCenterArr.push($(this).attr("attr_val"));
			}
		});
		var newsPaperIdLen = globalNewsPaperIdArr.length;
		var channelIdLen = globalChannelIdArr.length;
		var callCenterIdLen = globalCallCenterArr.length;
		
		if(newsPaperIdLen == 0 && channelIdLen == 0 && callCenterIdLen == 0){
			alert("Please Select Atleast One Option.");   
			return;
		}
		/* if(globalNewsPaperIdArr.length == 0 && globalChannelIdArr.length == 0){
			globalNewsPaperIdArr.push(0);
			globalChannelIdArr.push(0);
		} */
		var departmentIdLen = globalDepartmentIdArr.length;
		if(departmentIdLen == 0){
			alert("Please Select Atleast One Department.");
			return;
		} 
			$(".documentCloseClass").hide();
		onLoadCalls111();
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
	$(document).on("click",".selectAllcallcenterCls",function(){
		if($(this).prop('checked')) {
			$(".callcenterCls").prop('checked', true);
		}else{
			$(".callcenterCls").prop('checked', false);
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
	var dates= $("#dateRangePicker").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('All');
	}

	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePicker").val('All');
		}
		onLoadCalls111();
	});
	$(".chosenSelect").chosen({width:'100%'});
	$(document).on("click",".getDtlsCls",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		getAlertDtlsBasedOnStatusClick(statusId,statusName,statuscount);
		getFilterSectionAlertDetails(statusId,statusName,statuscount);
	});
	$(document).on("click",".getTotalAlertBylocationLvl",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		getTotalAlertBylocationLvl(statusId,statusName,statuscount);
		getFilterSectionAlertDetails(statusId,statusName,statuscount);
	});
	$(document).on("click",".totalAlertCls",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var resultType = $(this).attr("attr_result_type");
		var totalAlertCnt = $(this).attr("attr_total_alert_count");
		if(resultType != null && resultType == "statusWise"){
			getAlertDtlsBasedOnStatusClick(0,"Alert",totalAlertCnt)
		}else{
			getTotalAlertBylocationLvl(0,"Alert",totalAlertCnt);
		}
	});
	
	$(document).on("click",".getTotalAlertBylocationLvlThenDept",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount,departmentId);
		getFilterSectionAlertDetails(statusId,statusName,statuscount);
	});
	$(document).on("click",".getTotalAlertByStatusThenDept",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		getTotalAlertByStatusThenDept(statusId,statusName,statuscount,departmentId);
		getFilterSectionAlertDetails(statusId,statusName,statuscount);
	});
	
}

function responsiveTabs()
{
	var $this = $(this);
	var $windowWidth = $(window).width();
	if($windowWidth < 768)
	{
		$('[role="tabListMobile"]').show();
		$('[role="tablist"]').hide();
	}else{
		$('[role="tabListMobile"]').hide();
		$('[role="tablist"]').show();
	}
	
	$(document).on("change","[role='tabListMobile']",function(){
		var id = $('option:selected', this).attr('tab_id');
		$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
		$("#"+id).addClass("active");
	});
}


	
function onLoadCalls111(){	
	responsiveTabs();
	getStatusWiseAlertOverviewCnt();
	getLevelWiseAlertOverviewCnt();
	getDepartmentWiseAlertOverviewCnt('status','0');
	getDepartmentStatus();
	getDeptNamesForMultiLevel();
	getAlertSourceWiseAlert();
	
	
}

$(window,document).on('resize', function(){
	responsiveTabs();
});
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


function getStatusWiseAlertOverviewCnt()
{
	$("#statusOverview").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : globalDepartmentIdArr,  
      paperIdArr : globalNewsPaperIdArr,
      chanelIdArr : globalChannelIdArr,
	  callCenterArr : globalCallCenterArr
    }
    $.ajax({
      type:'GET',
      url: 'getStatusWiseAlertOverviewCntAction.action',
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
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="totalAlertGroupByStatusForGovt" style="height:300px"></div>';
			str+='<div id="statusOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div class="scrollerDivCls">';
				str+='<table class="table tableGraph">';
					str+='<thead>';
						str+='<th>Status</th>';
						str+='<th>Total</th>';
						str+='<th>%</th>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result)
						{	
							totalAlert+=result[i].alertCnt;
							str+='<tr>';
								str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
								str+='<td style="cursor:pointer;" class="getDtlsCls" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
								if(result[i].percentage > 0){
								str+='<td>'+result[i].percentage+'%</td>';	
								}else{
								str+='<td> - </td>';	
								}
							str+='</tr>';
						}
					str+='</tbody>';  
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str);
	$("#statusOverViewTotal").html("<h4  class='text-center totalAlertCls' attr_result_type='statusWise' style='cursor:pointer;' attr_total_alert_count='"+totalAlert+"'>TOTAL "+totalAlert+"</h4>");//santosh
	var statusOverviewArr =[];
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
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		$("#totalAlertGroupByStatusForGovt").highcharts({
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
					innerSize: 130,
					depth: 180,
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
		});
	});
}
/* Status OverView End*/

/*Alert Level Wise Start*/
function getLevelWiseAlertOverviewCnt()
{
	$("#levelWiseAlertOverview").html(spinner);
	
	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : globalDepartmentIdArr,  
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr,
		callCenterArr : globalCallCenterArr
	}
	$.ajax({
		type:'GET',
		url: 'getLevelWiseAlertOverviewCntAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildLevelWiseAlertOverviewCnt(result);
		}else{
			$("#levelWiseAlertOverview").html("NO DATA AVAILABLE");
		}
	});
}
function buildLevelWiseAlertOverviewCnt(result)
{
	var str='';
	var totalAlert = 0;
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="levelWiseAlertOverviewCnt" style="height:300px"></div>';
			str+='<div id="levelWiseAlertOverviewCntTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div class="scrollerDivClsLevel">';
				str+='<table class="table tableGraph">';
					str+='<thead>';
						str+='<th>Level</th>';
						str+='<th>Total</th>';
						str+='<th>%</th>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result)
						{	
							totalAlert+=result[i].alertCnt;
							str+='<tr>';
								str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
								str+='<td style="cursor:pointer;" class="getTotalAlertBylocationLvl" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
								str+='<td>'+result[i].percentage+'%</td>';
							str+='</tr>';
						}
					str+='</tbody>';  
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseAlertOverview").html(str);
	if(result.length > 6)
	{
		$(".scrollerDivClsLevel").mCustomScrollbar({setHeight:'300px'});
	}
	$("#levelWiseAlertOverviewCntTotal").html("<h4 class='text-center totalAlertCls' style='cursor:pointer;' attr_result_type='levelWise' attr_total_alert_count='"+totalAlert+"'>TOTAL "+totalAlert+"</h4>")
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	$(function() {
		$("#levelWiseAlertOverviewCnt").highcharts({
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
					innerSize: 130,
					depth: 180,
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
		});
	});
}
/*Alert Level End */

function getDepartmentStatus()
{
	$("#departmentStatus").html(spinner)
    var jsObj ={
    }
    $.ajax({
      type:'GET',
      url: 'getDepartmentStatusAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0)
		{
			buildDepartmentStatus(result,'status');
		}else{
			$("#departmentStatus").html("NO DATA AVAILABLE");
		}
    });
}

function buildDepartmentStatus(result,type)
{
	var str='';
	str+='<select class="form-control" role="tabListMobile"  onchange="getDepartmentWiseAlertOverviewCnt(\''+type+'\',$(this).val())">';
		str+='<option tab_id="all" value="0">ALL</option>';
		for(var i in result)
		{
			str+='<option tab_id="#departmentList'+result[i].id+'" value="'+result[i].id+'">'+result[i].name+'</option>';
		}
	str+='</select>';
	
	str+='<ul class="nav nav-tabs departmentList" role="tablist">';
		str+='<li class="active"><a onclick="getDepartmentWiseAlertOverviewCnt(\''+type+'\',0)" href="#all" aria-controls="all" role="tab" data-toggle="tab">ALL</a></li>';
	for(var i in result)
	{
		str+='<li role="presentation"><a onclick="getDepartmentWiseAlertOverviewCnt(\''+type+'\','+result[i].id+')" href="#departmentList'+result[i].id+'" aria-controls="departmentList'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'</a></li>';
	}
	str+='</ul>';
	$("#departmentStatus").html(str);
	responsiveTabs();
}
function getDepartmentScope()
{
	$("#departmentStatus").html(spinner);	
    var jsObj ={
    }
    $.ajax({
      type:'GET',
      url: 'getDepartmentScopeAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0)
		{
			buildDepartmentStatus(result,'department');
		}else{
			$("#departmentStatus").html("NO DATA AVAILABLE");
		}
    });
}

function getDepartmentWiseAlertOverviewCnt(type,id)
{
	var alertStatusIdArr = [];
	var deptScopeLevelIdArr = [];
	if(type== 'status')
	{
		if(id > 0)
		{
			alertStatusIdArr.push(id);
		}
	}else if(type == 'department')
	{
		if(id > 0)
		{
			deptScopeLevelIdArr.push(id);
		}
	}
	$("#departmentWiseAlertOverviewCnt").html(spinner);
	
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : globalDepartmentIdArr,  
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr,
		callCenterArr : globalCallCenterArr,
		alertStatusIdArr:alertStatusIdArr,
		deptScopeLevelIdArr:deptScopeLevelIdArr,
		resultType:type
    }
    $.ajax({
		type:'GET',
		url: 'getDepartmentWiseAlertOverviewCntAction.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0)
		{
			buildDepartmentWiseAlertOverviewCnt(result,type,id);
		}else{
			$("#departmentWiseAlertOverviewCnt").html("NO DATA AVAILABLE");
		}
    });
}
function buildDepartmentWiseAlertOverviewCnt(result,type,id)
{
	var str='';
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
			str+='<div class="departmentScroll">';
				str+='<div class="row">';
					str+='<div class="col-sm-7 m_top10">';
						str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply">';
						for(var i in result)
						{
							if(result[i].name !=null && result[i].name.length > 40){
								if(type== 'status')
								{
									str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,40)+'...</span> <span style="cursor:pointer;" class="pull-right getTotalAlertByStatusThenDept" attr_department_id="'+id+'" attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';  
								}else if(type == 'department'){
									str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,40)+'...</span> <span style="cursor:pointer;" class="pull-right getTotalAlertBylocationLvlThenDept" attr_department_id="'+id+'" attr_status_id="'+result[i].id+'"   attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';  
								}
								
							}else{
								if(type== 'status')
								{
									str+='<li>'+result[i].name+' <span style="cursor:pointer;" class="pull-right getTotalAlertByStatusThenDept" attr_department_id="'+id+'"  attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';
								}else if(type == 'department'){
									str+='<li>'+result[i].name+' <span style="cursor:pointer;" class="pull-right getTotalAlertBylocationLvlThenDept"  attr_department_id="'+id+'" attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';
								}
								
							}
							
						}
						str+='</ul>';
					str+='</div>';
					str+='<div class="col-sm-5">';
						str+='<div id="departmentStatusGraph"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#departmentWiseAlertOverviewCnt").html(str);
	var scrollApply = result.length;
	if( scrollApply > 10)
	{
		$(".departmentScroll").mCustomScrollbar({
			setHeight: '500px'
		});
	}
	
	var departmentStatusOvrVwArr = [];
	
	for(var i in result)
	{
		var departmentStatus = [];
		var dynamicHeight;
		$(".dynamicHeightApply").each(function(){
			dynamicHeight = $(this).find("li").length;
			if(result.length == 1){
				dynamicHeight = (dynamicHeight*60)+"px";
			}else if(result.length < 5){
				dynamicHeight = (dynamicHeight*42)+"px";
			}else{
				dynamicHeight = (dynamicHeight*31)+"px";
			}
			
		});
		$("#departmentStatusGraph").css("height",dynamicHeight);
		departmentStatus.push(result[i].name);
		departmentStatus.push(result[i].percentage);
		departmentStatusOvrVwArr.push(departmentStatus)
	}	
		$('#departmentStatusGraph').highcharts({
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
				enabled:false,
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
function getAlertDtlsBasedOnStatusClick(statusId,statusName,statuscount){ 
	$("#alertManagementPopupBody").html(spinner);
	var impactLevelArr =[];
     var   priorityArr =[];
     var   alertSourceArr =[];
     var  printMediaArr =[];
     var  electronicMediaArr=[];

    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : globalDepartmentIdArr,  
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr, 
		callCenterArr : globalCallCenterArr,		
		statusId : statusId ,
		impactLevelArr :impactLevelArr,
        priorityArr :priorityArr,
        alertSourceArr:alertSourceArr,
        printMediaArr :printMediaArr,
        electronicMediaArr:electronicMediaArr
    }
    $.ajax({
		type:'GET',
		url: 'getTotalAlertByStatusNewAction.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
    });
}
function getTotalAlertBylocationLvl(statusId,statusName,statuscount){ 
	$("#alertManagementPopupBody").html(spinner);
	
	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : globalDepartmentIdArr,  
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr,
		callCenterArr : globalCallCenterArr,		
		statusId : 0,
		govtDeptScopeId : statusId
	}
	$.ajax({
		type:'GET',       
		url: 'getTotalAlertBylocationLvlAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
} 
function getTotalAlertByStatusThenDept(statusId,statusName,statuscount,departmentId){ 
	$("#alertManagementPopupBody").html(spinner);
	
	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : globalDepartmentIdArr,  
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr, 
		callCenterArr : globalCallCenterArr,	
		statusId : departmentId,
		deptId : statusId       
	}
	$.ajax({
		type:'GET',
		url: 'getTotalAlertByStatusThenDeptAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
}  
function getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount,departmentId){ 
	$("#alertManagementPopupBody").html(spinner);
	
	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : globalDepartmentIdArr,  
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr,
		callCenterArr : globalCallCenterArr,		
		statusId : 0,
		govtDeptScopeId : departmentId,    
		deptId : statusId
	}
	$.ajax({
		type:'GET',       
		url: 'getTotalAlertBylocationLvlThenDeptAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
		}else{  
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
}
	
	
function getDeptNamesForMultiLevel(){ 
$("#levelWiseDepartmentDetailsId").html(spinner);
  $.ajax({
      type:'GET',
      url: 'getDeptListForMultiLvlAction.action',
    data: {}
    }).done(function(result){
		$("#levelWiseDepartmentDetailsId").html('');
		buildDeptNamesForMultiLevel(result);
	});
}

function buildDeptNamesForMultiLevel(result){
	if(result !=null && result.length>0){
		var str='';
		 str+='<div class="scrollerBlock">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="panel-group" id="departmentOverview" role="tablist" aria-multiselectable="true">';
				for(var i in result){
				  str+='<div class="panel panel-default">';
					str+='<div class="panel-heading headingColor" role="tab" id="headingOne'+i+'">';
					
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
					
					if(i == 0)
					{
					
						str+='<a role="button" class="collapseIconForMulti departmentLevelWiseDetails" attr_level_idstr='+levelIdStr+' attr_departmentId="'+result[i].id+'"  attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'" data-toggle="collapse" data-parent="#departmentOverview" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
						str+='<h4 class="panel-title fontColor" >'+result[i].name+'<span style="margin-left:20px"></span>';
						  str+='</h4>';
						str+='</a>';
					}else{
						str+='<a role="button" class="collapsed collapseIconForMulti departmentLevelWiseDetails" attr_level_idstr='+levelIdStr+'  attr_departmentId="'+result[i].id+'"  attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'" data-toggle="collapse" data-parent="#departmentOverview" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
						  str+='<h4 class="panel-title fontColor">'+result[i].name+'<span style="margin-left:20px"></span>';
						  str+='</h4>';
						str+='</a>';
					}
					str+='</div>';
					if(i == 0)
					{
						str+='<div id="collapseOne'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+i+'">';
					}else{
						str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
					}
					
					  str+='<div class="panel-body">';
					 
						if(result[i].subList1 !=null && result[i].subList1.length>0){
							for(var j in result[i].subList1){
								
								 str+='<div class="col-md-12 col-xs-12 col-sm-12" id="departmentWiseBlocks'+result[i].id+''+result[i].subList1[j].id+'">';
									str+='<div class="row m_top20">';
										str+='<div class="col-md-9 col-xs-12 col-sm-12">';
											str+='<h4>'+result[i].subList1[j].name+'</h4>';
										str+='</div>';
										
										str+='<div class="col-md-4 col-xs-12 col-sm-12 pull-right">';
											str+='<ul class="switch_btn_multiLevel locationAndStatusWiseInterchange">';
												str+='<li  class="statusChangeCls'+result[i].id+''+result[i].subList1[j].id+'" attr_type="scopeWise"  attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">location</li>';
												str+='<li class="active addActiveCls statusChangeCls'+result[i].id+''+result[i].subList1[j].id+'" attr_type="statusWise" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">status</li>';
												str+='<li class="statusChangeCls'+result[i].id+''+result[i].subList1[j].id+'" attr_type="alertSource" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">AlertSource</li>';
											str+='</ul>';
										str+='</div>';
									str+='</div>';
									
										str+='<div class="row m_top20">';
											str+='<div class="col-md-2 col-xs-12 col-sm-12">';
												str+='<ul class="list-inline activeUlCls  constituencyUl locationAndStatusWiseSorting">';
												str+='<li class="active sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
													str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
												str+='</li>';
												str+='<li class="sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="count" attr_order_type="asc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'" attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
													str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
												str+='</li>';
												str+='<li class="sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="name" attr_order_type="asc"  attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
													str+='A-Z';
												str+='</li>';
												str+='<li class="sortingCls'+result[i].id+''+result[i].subList1[j].id+'" attr_sorting_type="name" attr_order_type="desc" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'" attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">';
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
						
					  str+='</div>';
					 
					 str+='</div>';	
				}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#levelWiseDepartmentDetailsId").html(str);
		var deptObj = result[0];
		 if(deptObj.subList1 != null && deptObj.subList1.length > 0){
			 for(var i in deptObj.subList1){
				getStateThenGovtDeptScopeWiseAlertCount(deptObj.id,deptObj.subList1[i].id,"statusWise","levelWiseGraphView","count","desc",0,0,"Default","Other");
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
		$("#levelWiseDepartmentDetailsId").html("No Data Available");
	}
}
	$(document).on("click",".departmentLevelWiseDetails",function(){
		var levelIdsStr = $(this).attr("attr_level_idstr").split(',');
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		var departmentId = $(this).attr("attr_departmentId");
		var districtLevelId = $(this).attr("attr_district_level_id");
		$(".locationAndStatusWiseInterchange").find("li").removeClass("active");
		$(".locationAndStatusWiseInterchange").find(".addActiveCls").addClass("active");
		
		for(var i in levelIdsStr){
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelIdsStr[i],"statusWise","levelWiseGraphView","count","desc",0,0,"Default","Other");
			getLocationBasedOnDepartmentLevel(departmentId,levelIdsStr[i],districtLevelId);
			
		}
		
	});

$(document).on("click",".locationAndStatusWiseInterchange li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var parentId = $(this).attr("attr_parent_id");
		var searchType =  $(this).attr("attr_type");
		
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var sortingType='';
		var orderType='';

		$('.sortingCls'+departmentId+parentId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType = $(this).attr("attr_order_type");
			 }
		});
		var districtLevelId = $(this).attr("attr_district_level_id");
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).trigger('chosen:updated');
			
		}
		
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Sorting");
		getLocationBasedOnDepartmentLevel(departmentId,parentId,districtLevelId);
		
		
});

$(document).on("click",".locationAndStatusWiseSorting li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var parentId = $(this).attr("attr_parent_id");
		
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var searchType = '';
		$('.statusChangeCls'+departmentId+parentId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		var districtLevelId = $(this).attr("attr_district_level_id");
		var childLevelIdsStr = $(this).attr("attr_child_id").split(',');
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).trigger('chosen:updated');
			
		}
	
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Sorting");
		getLocationBasedOnDepartmentLevel(departmentId,parentId,districtLevelId);
		
});


function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,selectionType){
	$("#"+divId+departmentId+parentGovtDepartmentScopeId).html(spinner);
	 if(parentGovtDepartmentScopeId == 1 && selectionType != "Sorting"){
		 orderType = "Default";
	 }
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		paperIdArr : globalNewsPaperIdArr,
		chanelIdArr : globalChannelIdArr,
		callCenterArr : globalCallCenterArr,
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentGovtDepartmentScopeId, 
		districtWorkLocationId : 0,  
		divisionWorkLocationId : 0,       
		subDivisionWorkLocationId : 0,	
		sortType : sortingType,
		order : orderType,
		group:"status",
		alertType:"alert",
		searchType:searchType,
		subLevels:subLevels,
		filterParentScopeId :filterParentScopeId,
		filterScopeValue:filterScopeValue
    }
    $.ajax({
    type:'GET',         
    url: 'getStateThenGovtDeptScopeWiseAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#"+divId+departmentId+parentGovtDepartmentScopeId).html('');
		buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType);
	});
}

function buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType){
	
	
	if(searchType == "statusWise" || searchType == "alertSource"){
		$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).show();
		if(result !=null && result.length>0){
			
			
			if(searchType == "statusWise"){
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
							}else if(result[i].subList[j].id==8){
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
				  } 
				  if(Incomplete != null && Incomplete.length > 0){
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
										}else if(searchType == "statusWise"){
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
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			  $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","25px"); 
			}
		}
	}else if(searchType == "scopeWise" && parentGovtDepartmentScopeId != 1){
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
						}else if(result[i].subList[j].id==8){
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
		}
	}else if(searchType == "scopeWise" && parentGovtDepartmentScopeId == 1){
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
			$("#"+divId+departmentId+parentGovtDepartmentScopeId).css('height',"25px;") */
		}
	}
}

	function getLocationBasedOnDepartmentLevel(departmentId,parentScopeId,districtLevelId){
	  $("#locationNamesId"+departmentId+parentScopeId+districtLevelId).html('');
	   var searchType = '';
		$('.statusChangeCls'+departmentId+parentScopeId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		
		 var jsObj ={
		  fromDate:currentFromDate,
		  toDate:currentToDate,
		  stateId : globalStateId,
		  paperIdArr : globalNewsPaperIdArr,
		  chanelIdArr : globalChannelIdArr,
		  callCenterArr : globalCallCenterArr,      
		  govtDepartmentId : departmentId,
		  parentGovtDepartmentScopeId : districtLevelId,
		  alertType:"alert",
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
		var searchType = '';
		$('.statusChangeCls'+departmentId+levelId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		if(childLevelId > 0){
		 getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue);	
		}
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,"levelWiseGraphView",sortingType,orderType,subLevelId,locationValue,"Change","Sorting");
		
		
	}); 	
	
	function getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue){
	$("#locationNamesId"+departmentId+levelId+childLevelId).html('');
	 	var jsObj ={
			fromDate:currentFromDate,
			toDate:currentToDate,
			stateId : globalStateId,
			paperIdArr : globalNewsPaperIdArr,
			chanelIdArr : globalChannelIdArr,
			callCenterArr : globalCallCenterArr,			
			govtDepartmentId : departmentId,
			parentGovtDepartmentScopeId : subLevelId,
			parentGovtDepartmentScopeValue:locationValue,
			childLevelId:childLevelId,
			alertType:"alert",
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
   
 
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr:globalNewsPaperIdArr,
		chanelIdArr:globalChannelIdArr,
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : parentGovtDepartmentScopeId,
		deptScopeId : levelId,    
		statusId:statusId,   
		callCenterArr:globalCallCenterArr,
		locationValue : locationValue,
		alertType:"alert",
		alertCategoryId:alertCategoryId
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

/*Alert Source Start */

function getAlertSourceWiseAlert()
{
	 
	$("#alertSourceWiseDetilsDivId").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : globalDepartmentIdArr,  
      paperIdArr : globalNewsPaperIdArr,
      chanelIdArr : globalChannelIdArr,
	  callCenterArr : globalCallCenterArr,
	  userType :"admin"
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
		str+='<div class="col-md-3 col-xs-12 col-sm-4">';
			str+='<div id="alertSourceWiseGraphView" style="height:270px"></div>';
			str+='<div id="alertSourceWiseTotal"></div>';
		str+='</div>';
	
		 str+='<div class="col-md-3 col-xs-12 col-sm-6" style="margin-top:30px">';
			str+='<div class="scrollerDivCls">';
				str+='<table class="table tableGraph">';
					
					str+='<tbody>';
						for(var i in result)
						{	
							totalAlert+=result[i].alertCnt;
							str+='<tr>';
								str+='<td><span class="label" style="background-color:'+globalAlertSourceColorObj[result[i].name.trim()]+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
								str+='<td style="cursor:pointer;" onclick="getAlertDtlsByAlertSource(\''+result[i].name+'\','+result[i].alertCnt+','+result[i].id+');" class="alertSourceCls" attr_alert_source_name="'+result[i].name+'" attr_alert_count="'+result[i].alertCnt+'" attr_source_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
								
							str+='</tr>';
						}
					str+='</tbody>';  
				str+='</table>';
			str+='</div>';
		str+='</div>'; 
		str+='<div class="col-md-6 col-xs-12 col-sm-6" >';
			str+='<div id="alertSourceWisebarGraphView" style="height:270px"></div>';
		str+='</div>';
	str+='</div>';
	$("#alertSourceWiseDetilsDivId").html(str);
	var str2='';
	var statusName = "Total"
	str2+='<h4 style="cursor:pointer;" class="text-center alertSourceCls" onclick="getAlertDtlsByAlertSource(\''+statusName+'\','+totalAlert+',0);" attr_alert_source_name="Total" attr_alert_count='+totalAlert+' attr_source_id="0">TOTAL '+totalAlert+'</h4>';
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
					innerSize: 130,
					depth: 180,
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
			var mainArrTempAT=[];
			var namesArrAT=[];
			var countAT = [];
			for(var i in result){
				var uniqCnt = {};
							//var totalAlertCnt = result[i].alertCnt;
							namesArrAT.push(result[i].name);
							var tempArrAT = {"y":result[i].alertCnt,color:globalAlertSourceColorObj[result[i].name.trim()],"extra":result[i].name+"-"+result[i].alertCnt+"-"+result[i].id};
							var uniqCnt = {"y":parseInt(totalAlert)-parseInt(result[i].alertCnt),color:"#D3D3D3","extra":result[i].name+"-"+result[i].alertCnt+"-"+result[i].id};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
			}		
					$('#alertSourceWisebarGraphView').highcharts({
							
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
													var value = (this.extra).split("-");
													var statusName = value[0];
													var alertCount = value[1];
													var alertSourceId = value[2];
													 if(alertCount == 0){
														return;  
													 }  
													getAlertDtlsByAlertSource(statusName,alertCount,alertSourceId);
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
					$.each($('#alertSourceWisebarGraphView').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
						$(this).attr("style","cursor:pointer;");    
						$(this).attr("onclick","getAlertDtlsByAlertSource(\'"+result[index].name+"\',\'"+result[index].alertCnt+"\',\'"+result[index].id+"\')");
					});
			
		}else{
			 $('#alertSourceWisebarGraphView').html("No Data Available")
		}
}
function getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId)
{$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : globalDepartmentIdArr,  
      paperIdArr : globalNewsPaperIdArr,
      chanelIdArr : globalChannelIdArr,
	  callCenterArr : globalCallCenterArr,
	  alertCategoryId:alertCategoryId,
	  userType :"admin"
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