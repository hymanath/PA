/*global Function and variables Start*/
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var globalStateId = 1;  
var globalNewsPaperIdArr = [];
var globalChannelIdArr = [];
var globalDepartmentIdArr = [];
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

/* OnLoad Calls Start*/
onLoadInitialisations();
onLoadCalls();

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
	/*alert Assigned Part Start*/
	$(document).on('change', '#locationLevelSelectId', function(){
		getParentLevelsOfLevel();
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	$(document).on('change', '#departmentsId', function(){
		getDepartmentLevels();
	});
	$(document).on("click","#assignOfficerId",function(){
		/* if(!fieldsValidation())
		{
			return;
		} */
		
		$("#assiningLdngImg").show();
		$("#assignOfficerId").hide();
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				displayStatus(uploadResult);
			}
		};
		

		YAHOO.util.Connect.setForm('alertAssignForm',true);
		YAHOO.util.Connect.asyncRequest('POST','assigningAlertToOfficerNewAction.action',uploadHandler); 
		
		
	});
	$(document).on("click","#assignOfficerSubTaskId",function(){
		/* if(!fieldsValidation())
		{
			return;
		} */
		
		$("#assiningLdngImgSubTask").show();
		$("#assignOfficerSubTaskId").hide();
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				displayStatusSubTask(uploadResult);
			}
		};
		

		YAHOO.util.Connect.setForm('alertAssignSubTaskForm',true);
		YAHOO.util.Connect.asyncRequest('POST','assigningSubTaskToOfficerAction.action',uploadHandler); 
	});
	
	$(document).on('change','#designationsId', function(evt, params) {
		var designationId = $(this).val();
		officersByDesignationAndLevel(designationId)
	});
	
	/*Alert Assigned Part ENd*/
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
	$(document).on("click","#updateStatusChange",function(){
		var jsObj ={
			alertId : 11346,
			statusId : $('input[name=statusChange]:checked', '#updateStatusChangeBody').val(),
			comment: $("#updateStatusChangeComment").val()
		}
		$.ajax({
			type:'GET',
			url: 'updateAlertStatusCommentAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				alert("status updated successfully")
			}else{
				alert("try again")
			}
		});
	});
	$(document).on("click","#priorityChangeSaveId",function(){
		
		var jsObj ={
			alertId : 11346,
			priorityId : $('input[name=alert-status-change-list]:checked', '.alert-status-change-list').val(),
		}
		$.ajax({
			type:'GET',
			url: 'updateAlertPriorityAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				alert("status updated successfully")
				$("#priorityBodyId").html($('input[name=alert-status-change-list]:checked', '.alert-status-change-list').val());
			}else{
				alert("try again")
			}
		});
	});
	$(document).on("click","#commentChangeId",function(){
		var comment = $("#alertCommentId").val();
		if(comment == null || comment.trim() == "")
		{
			alert("please enter comment");
			return;
		}
		var jsObj ={
			alertId : 11346,
			comment : comment
		}
		$.ajax({
			type:'GET',
			url: 'updateCommentAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				alert("status updated successfully")
			}else{
				alert("try again")
			}
		});
			
	});
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
		var newsPaperIdLen = globalNewsPaperIdArr.length;
		var channelIdLen = globalChannelIdArr.length;
		if(newsPaperIdLen == 0 && channelIdLen == 0){
			alert("Please Select Atleast One Newspaper or Channel.");   
			return;
		}
		var departmentIdLen = globalDepartmentIdArr.length;
		if(departmentIdLen == 0){
			alert("Please Select Atleast One Department.");
			return;
		}    
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
		getStatusWiseAlertOverviewCnt();
		getLevelWiseAlertOverviewCnt();
		getDepartmentWiseAlertOverviewCnt('status','0');
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
		getAlertDtlsBasedOnStatusClick(statusId,statusName,statuscount)
	});
	$(document).on("click",".getLocationDtlsCls",function(){
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
		getTotalAlertByStatusThenDept(statusId,statusName,statuscount);
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
		getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount);
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


function onLoadCalls()
{
	responsiveTabs();
	
	getStatusWiseAlertOverviewCnt();
	getLevelWiseAlertOverviewCnt();
	getDepartmentWiseAlertOverviewCnt('status','0');
	
	getDepartmentStatus();
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

/*Default Image*/
function setDefaultImage(img){
    img.src = "images/User.png";
}
/*Default Image*/
/*global Function and variables End*/
/* Status OverView Start*/
function getStatusWiseAlertOverviewCnt()
{
	$("#statusOverview").html(spinner);
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
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str);
	$("#statusOverViewTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
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
function getLevelWiseAlertOverviewCnt()
{
	$("#levelWiseAlertOverview").html(spinner);
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
							str+='<td style="cursor:pointer;" class="getLocationDtlsCls" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseAlertOverview").html(str);
	$("#levelWiseAlertOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
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
	var deptIdArr = globalDepartmentIdArr;
    var paperIdArr = globalNewsPaperIdArr;
    var chanelIdArr = globalChannelIdArr;
	
	
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : deptIdArr,  
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,
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
			buildDepartmentWiseAlertOverviewCnt(result,type);
		}else{
			$("#departmentWiseAlertOverviewCnt").html("NO DATA AVAILABLE");
		}
		console.log(result);
    });
}
function buildDepartmentWiseAlertOverviewCnt(result,type)
{
	var str='';
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		for(var i in result)
		{
			str+='<div class="departmentScroll'+i+'">';
				str+='<div class="row">';
					str+='<div class="col-md-7 col-xs-12 col-sm-4 m_top10">';
						str+='<ul style="list-style:none;" class="textAlignDepartment dynamicHeightApply'+i+'">';
						if(result[i].name !=null && result[i].name.length > 40){
							if(type== 'status')
							{
								str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,40)+'...</span> <span style="cursor:pointer;" class="pull-right getTotalAlertByStatusThenDept" attr_status_id="'+result[i].id+'" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';  
							}else if(type == 'department')
							{
								str+='<li><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,40)+'...</span> <span style="cursor:pointer;" class="pull-right getTotalAlertBylocationLvlThenDept" attr_status_id="'+result[i].id+'" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';  
							}
							
						}else{
							if(type== 'status')
							{
								str+='<li>'+result[i].name+' <span style="cursor:pointer;" class="pull-right getTotalAlertByStatusThenDept" attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';
							}else if(type == 'department')
							{
								str+='<li>'+result[i].name+' <span style="cursor:pointer;" class="pull-right getTotalAlertBylocationLvlThenDept" attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" >'+result[i].alertCnt+'</span></li>';
							}
							
						}
						str+='</ul>';
					str+='</div>';
					str+='<div class="col-md-5 col-xs-12 col-sm-4">';
						str+='<div id="departmentStatusGraph'+i+'"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
	str+='</div>';
	$("#departmentWiseAlertOverviewCnt").html(str);
	var departmentStatusOvrVwArr = [];
	var departmentStatus = [];
	for(var i in result)
	{
		var dynamicHeight;
		$(".dynamicHeightApply"+i).each(function(){
			dynamicHeight = $(this).find("li").length;
			if(result.length == 1){
				dynamicHeight = (dynamicHeight*60)+"px";
			}else if(result.length < 5){
				dynamicHeight = (dynamicHeight*42)+"px";
			}else{
				dynamicHeight = (dynamicHeight*31)+"px";
			}
			
		});
		$("#departmentStatusGraph"+i).css("height",dynamicHeight);
		departmentStatus.push(result[i].name);
		departmentStatus.push(result[i].percentage);
		departmentStatusOvrVwArr.push(departmentStatus)
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
	
}
function getAlertDtlsBasedOnStatusClick(statusId,statusName,statuscount){ 
	$("#alertManagementPopupBody").html(spinner);
	var deptIdArr = globalDepartmentIdArr;
	var paperIdArr = globalNewsPaperIdArr;
	var chanelIdArr = globalChannelIdArr;

    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : deptIdArr,  
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,                 
		statusId : statusId                                
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

function getTotalAlertByStatusThenDept(statusId,statusName,statuscount){ 
	$("#alertManagementPopupBody").html(spinner);
	var deptIdArr = globalDepartmentIdArr;
	var paperIdArr = globalNewsPaperIdArr;
	var chanelIdArr = globalChannelIdArr;
	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : [],  
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,                 
		statusId : 0,
		deptId : 49       
	}
	$.ajax({
		type:'GET',
		url: 'getTotalAlertByStatusThenDeptAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result)
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
}
function getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount){ 
	$("#alertManagementPopupBody").html(spinner);
	var deptIdArr = globalDepartmentIdArr;
	var paperIdArr = globalNewsPaperIdArr;
	var chanelIdArr = globalChannelIdArr;

	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : deptIdArr,  
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,                 
		statusId : statusId,
		govtDeptScopeId : 5,    
		deptId : 49
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
function getTotalAlertBylocationLvl(statusId,statusName,statuscount){ 
	$("#alertManagementPopupBody").html(spinner);
	var deptIdArr = globalDepartmentIdArr;
	var paperIdArr = globalNewsPaperIdArr;
	var chanelIdArr = globalChannelIdArr;

	var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		deptIdArr : deptIdArr,  
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,                 
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
function viewAlertHistory()
{
	$("#alertManagementPopupBody1").html(spinner)
	var jsObj ={
		alertId : 11346
	}
	$.ajax({
		type:'GET',
		url: 'viewAlertHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			alertHistory(result);
		}else{
			$("#alertManagementPopupBody1").html("NO DATA AVAILABLE")
		}
	});
}


/*Alert Assigning Part*/



function getDepartmentLevels(){
	
	var jsObj = {
		departmentId : 49
	}
	$.ajax({
      type:'GET',
      url: 'getDepartmentLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentLevels(result);
		}
	});
	
}
function buildDepartmentLevels(result){
	
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	
	$("#locationLevelSelectId").html(str);
	$("#locationLevelSelectId").trigger("chosen:updated");
}


function getParentLevelsOfLevel(){
	departmentId = 49;
	var jsObj = {
		departmentId : departmentId,
		levelId : $("#locationLevelSelectId").val()
	}
	$.ajax({
      type:'GET',
      url: 'getParentLevelsOfLevelAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildParentLevelsOfLevel(result,departmentId);
		}
	});
}
function buildParentLevelsOfLevel(result,departmentId){
	var str='';
		
		for(var i in result){
			if(i<result.length-1){
				str+='<div class="col-sm-6">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-6">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
				str+='</div>';
			}
			
		}
	
	$("#parentLevelDivId").html(str);
	$(".chosenSelect").chosen();
	
	for(var i in result){
		
		if(result[i].idnameList !=null && result[i].idnameList.length>0){
			var newStr='';		
			newStr+='<option value="0">Select '+result[i].name+'</option>';
			for(var j in result[i].idnameList){
				 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
			}			
			$("#locationSubLevelSelectId"+result[i].id+"").html(newStr);
			$("#locationSubLevelSelectId"+result[i].id+"").trigger("chosen:updated");
		}
	}
	
}
function getGovtSubLevelInfo(departmentId,levelId){
	
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");	
	
	var levelValue=$("#locationSubLevelSelectId"+levelId+"").val();	
	
	var jsObj = {
		departmentId : departmentId,
		levelId :levelId,
		levelValue:levelValue
	}
	$.ajax({
      type:'GET',
      url: 'getGovtSubLevelInfoAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildGovtSubLevelInfoAction(result);
		}
			
	});
}
function buildGovtSubLevelInfoAction(result){
	
	var str='';
	if(result !=null){		
		if(result.idnameList !=null && result.idnameList.length>0){
			str+='<option value="0">Select '+result.name+'</option>';
			for(var i in result.idnameList){
				str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
			}
		}
		
		$("#locationSubLevelSelectId"+result.id+"").html(str);
		$("#locationSubLevelSelectId"+result.id+"").trigger("chosen:updated");
	}
	
}



function designationsByDepartment()
{
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId").chosen().val();
	var deprtmntId = $("#departmentsId").chosen().val();
	var levelValue = $(".locationCls").chosen().val();
	
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
		str+='<option value="0">Select Designation</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#designationsId").html(str);
		$("#designationsId").trigger("chosen:updated");
	});
}

function officersByDesignationAndLevel(designationId)
{
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId").chosen().val()
	var LevelValue = $(".locationCls").chosen().val()
	
	var jsObj = {
		levelId				: LevelId,
		levelValue			: LevelValue,
		designationId		: designationId
	}
	$.ajax({
      type:'GET',
      url: 'getOfficersByDesignationAndLevelNewAction.action',
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


function displayStatus(result)
{
	var result = (String)(result);
	if(result.search('success') != -1){
		$("#assiningLdngImg").hide();
		$("#assignOfficerId").show();
		alert("Alert Assigned Successfully.");
		$("#assignSuccess").html('Alert Assigned Successfully')
		location.reload();
	}else{
		alert("Please Try Again.");
		$("#assignSuccess").addClass("text-danger");
		$("#assignSuccess").html('Try Again');
	}	
}
function displayStatusSubTask(result)
{
	var result = (String)(result);
	if(result.search('success') != -1){
		$("#assiningLdngImgSubTask").hide();
		$("#assignOfficerSubTaskId").show();
		alert("Alert Assigned Successfully.");
		$("#assignSuccessSubTask").html('Alert Assigned Successfully')
		location.reload();
	}else{
		alert("Please Try Again.");
		$("#assignSuccessSubTask").addClass("text-danger");
		$("#assignSuccessSubTask").html('Try Again');
	}	
}