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
	$(document).on('click', '.imageShowOpen li', function(){
		var id = $(this).attr("attr_doc_id");
		var path = "http://mytdp.com/"+$(this).attr("attr_path");
		window.open(path);
	});
	$(document).on("change","#divisionDistWiseLevelsDivId",function(){
		var defaultDepartmentID = $(this).attr("attr_department_id")
			getDivisionIdListForDivisionFilter(defaultDepartmentID);
	});
	$(document).on('change', '#locationLevelSelectId', function(){
		getParentLevelsOfLevel();
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	$(document).on('change', '#departmentsId', function(){
		var deptId = $(this).val();
		getDepartmentLevels(deptId);
	});
	$(document).on("click",".closeSecondModal",function(){
		setTimeout(function(){
			$("body").addClass("modal-open")
		},1000);
	});
	$(document).on("click",".articleDetailsCls",function(){
		var articleId= $(this).attr("attr_articleId");
		$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		getTotalArticledetails(articleId);
	});
	$(document).on("click","#uploadBtnId",function(){
		var alertId = $(this).attr("attr_alert_id");
		var uploadHandler = { 
			upload: function(o) {
				uploadResult = o.responseText;
				showSbmitStatusNew(uploadResult,alertId);
			}
		};
		YAHOO.util.Connect.setForm('uploadAttachment',true);  
		YAHOO.util.Connect.asyncRequest('POST','uploadDocumentsForAlertAction.action',uploadHandler);
		$("#uploacFilesBtnId").attr("disabled","disabled");
	});
	$(document).on("click","#assignOfficerId",function(){
		if($("#departmentsId").val() == null || $("#departmentsId").val() == "" || $("#departmentsId").val() == 0)
		{
			$("#assignErrorDivId").html("please select department");
			return;
		}
		if($("#locationLevelSelectId").val() == null || $("#locationLevelSelectId").val() == "" || $("#locationLevelSelectId").val() == 0)
		{
			$("#assignErrorDivId").html("please select impact level");
			return;
		}
		if($("#locationLevelSelectId").val() == 1)
		{
			if($("#locationSubLevelSelectId1").val() == null || $("#locationSubLevelSelectId1").val() == "" || $("#locationSubLevelSelectId1").val() == 0)
			{
				$("#assignErrorDivId").html("please select State");
				return;
			}
		}
		if($("#locationLevelSelectId").val() == 5)
		{
			if($("#locationSubLevelSelectId1").val() == null || $("#locationSubLevelSelectId1").val() == "" || $("#locationSubLevelSelectId1").val() == 0)
			{
				$("#assignErrorDivId").html("please select State");
				return;
			}
			if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
			{
				$("#assignErrorDivId").html("please select location");
				return;
			}
		}
		if($("#locationLevelSelectId").val() == 6)
		{
			if($("#locationSubLevelSelectId1").val() == null || $("#locationSubLevelSelectId1").val() == "" || $("#locationSubLevelSelectId1").val() == 0)
			{
				$("#assignErrorDivId").html("please select State");
				return;
			}
			if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
			{
				$("#assignErrorDivId").html("please select district");
				return;
			}
			if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
			{
				$("#assignErrorDivId").html("please select location");
				return;
			}
		}
		if($("#locationLevelSelectId").val() == 7)
		{
			if($("#locationSubLevelSelectId1").val() == null || $("#locationSubLevelSelectId1").val() == "" || $("#locationSubLevelSelectId1").val() == 0)
			{
				$("#assignErrorDivId").html("please select State");
				return;
			}
			if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
			{
				$("#assignErrorDivId").html("please select district");
				return;
			}
			if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
			{
				$("#assignErrorDivId").html("please select division");
				return;
			}
			if($("#locationSubLevelSelectId7").val() == null || $("#locationSubLevelSelectId7").val() == "" || $("#locationSubLevelSelectId7").val() == 0)
			{
				$("#assignErrorDivId").html("please select location");
				return;
			}
		}
		if($("#locationLevelSelectId").val() == 8)
		{
			if($("#locationSubLevelSelectId1").val() == null || $("#locationSubLevelSelectId1").val() == "" || $("#locationSubLevelSelectId1").val() == 0)
			{
				$("#assignErrorDivId").html("please select State");
				return;
			}
			if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
			{
				$("#assignErrorDivId").html("please select district");
				return;
			}
			if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
			{
				$("#assignErrorDivId").html("please select division");
				return;
			}
			if($("#locationSubLevelSelectId7").val() == null || $("#locationSubLevelSelectId7").val() == "" || $("#locationSubLevelSelectId7").val() == 0)
			{
				$("#assignErrorDivId").html("please select sub division");
				return;
			}
			if($("#locationSubLevelSelectId8").val() == null || $("#locationSubLevelSelectId8").val() == "" || $("#locationSubLevelSelectId8").val() == 0)
			{
				$("#assignErrorDivId").html("please select location");
				return;
			}
		}
		if($("#designationsId").val() == null || $("#designationsId").val() == "" || $("#designationsId").val() == 0)
		{
			$("#assignErrorDivId").html("please select designation");
			return;
		}
		if($("#officerNamesId").val() == null || $("#officerNamesId").val() == "" || $("#officerNamesId").val() == 0)
		{
			$("#assignErrorDivId").html("please select officer name");
			return;
		}
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
		//$('input[name=statusChange]:checked', '#updateStatusChangeBody').val()
		var comment = $("#updateStatusChangeComment").val()
		var alertId = $(this).attr("attr_alert_id");
		if(comment == null || comment.trim() == "")
		{
			alert("please enter comment");
			return;
		}
		var jsObj ={
			alertId : alertId,
			statusId : $('input[name=statusChange]:checked', '#updateStatusChangeBody').val(),
			comment: comment
		}
		$.ajax({
			type:'GET',
			url: 'updateAlertStatusCommentAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				alert("status updated successfully")
				getAlertStatusHistory(alertId);
			}else{
				alert("try again")
			}
		});
	});
	$(document).on("click","#priorityChangeSaveId",function(){
		
		var jsObj ={
			alertId : $(this).attr("attr_alert_id"),
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
				$("#priorityBodyId").html($('input[name=alert-status-change-list]:checked', '.alert-status-change-list').attr("attr_value"));
			}else{
				alert("try again")
			}
		});
	});
	$(document).on("click","#commentChangeId",function(){
		$("#commentPostingSpinner").html(spinner);
		var comment = $("#alertCommentId").val();
		var alertId = $(this).attr("attr_alert_id")
		if(comment == null || comment.trim() == "")
		{
			alert("please enter comment");
			return;
		}
		var jsObj ={
			alertId : alertId,
			comment : comment
		}
		$.ajax({
			type:'POST',
			url: 'updateCommentAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				$("#commentPostingSpinner").html("status updated successfully");
				$("div.comment-area").show();
				$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea").hide();
				$(".panel-border-white textarea").val('');
				getCommentsForAlert(alertId);
				setTimeout(function(){
					$("#commentPostingSpinner").html(" ");
				},1000);
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
	$(document).on("click",".getTotalAlertBylocationLvl",function(){
		$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		getTotalAlertBylocationLvl(statusId,statusName,statuscount)
	});
	$(document).on("click",".getTotalAlertBylocationLvlThenDept",function(){
		$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount,departmentId)
	});
	$(document).on("click",".getTotalAlertByStatusThenDept",function(){
		$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		getTotalAlertByStatusThenDept(statusId,statusName,statuscount,departmentId)
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
	getDeptNamesForMultiLevel();
	
	
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
							str+='<td style="cursor:pointer;" class="getTotalAlertBylocationLvl" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
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
function getTotalAlertByStatusThenDept(statusId,statusName,statuscount,departmentId){ 
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
function viewAlertHistory(alertId)
{
	$("#alertManagementPopupBody1").html(spinner)
	var jsObj ={
		alertId : alertId
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



function getDepartmentLevels(deptId){
	
	var jsObj = {
		departmentId : deptId
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
		$("#assignSuccess").html('Alert Assigned Successfully')
		setTimeout(function(){
			location.reload();
		},500);
	}else{
		alert("Please Try Again.");
		$("#assignSuccess").addClass("text-danger");
		$("#assignSuccess").html('Try Again');
	}	
}

function getAlertStatusHistory(alertId){
	var jsObj ={
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getAlertStatusHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		alertStatusHistory(result,alertId);
	});
}
function getStatusCompletionInfo(alertId){
	$("#updateStatusChangeBody").html(spinner);
	var jsObj ={
		alertId : alertId,
		levelValue: 1
	}
	$.ajax({
		type:'GET',
		url: 'getStatusCompletionInfoAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		alertStatus(result,alertId);
		
	});
}
function rightSideExpandView(alertId)
{
    $("#rightSideExpandView").html(spinner);
	var str='';
	str+='<div class="col-sm-8 pad_left0" expanded-block="block1" style="display: none;">';
		str+='<div class="panel-right">';
			str+='<div style="box-shadow:0px 0px 2px 2px rgba(0,0,0,0.2)">';
				str+='<i class="glyphicon glyphicon-remove pull-right"  expanded-close="block1"></i>';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser"></div>';
							str+='</div>';
							str+='<div class="col-sm-8">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									str+='<li status-icon-block="alertStatus" attr_alert_id="'+alertId+'" data-toggle="tooltip" data-placement="top" title="alert status">';
										str+='<span class="status-icon arrow-icon" id="statusIdColor"></span><span id="statusId">Pending</span>';
									str+='</li>';
									str+='<li class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date">DUe date</span>';
									str+='</li>';
									str+='<li status-icon-block="alertStatusChange" data-toggle="tooltip" data-placement="top" title="status change">';
										str+='<i class="glyphicon glyphicon-cog"></i>';
										str+='<ul class="alert-status-change-list arrow_box_top" style="display:none;">';
											str+='<li>high <input type="radio" name="alert-status-change-list" value="1" attr_value="high" class="pull-right priorityRadioCls" /></li>';
											str+='<li>medium <input type="radio" name="alert-status-change-list" attr_value="medium" value="2" class="pull-right priorityRadioCls" /></li>';
											str+='<li>low <input type="radio" name="alert-status-change-list" attr_value="low" value="3" class="pull-right priorityRadioCls" /></li>';
											str+='<li><button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" id="priorityChangeSaveId">SET</button></li>';
										str+='</ul>';
									str+='</li>';
									str+='<li status-icon-block="alertHistory" attr_alert_id="'+alertId+'">';
										str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
									str+='</li>';
									str+='<li status-icon-block="attachment" attr_alert_id="'+alertId+'">';
										str+='<i class="glyphicon glyphicon-paperclip" data-toggle="tooltip" data-placement="top" title="Attachments"></i>';
										str+='<form name="uploadAttachment" method="post" id="uploadAttachment">';
										str+='<div class="alert-status-attachment arrow_box_top" style="display:none;">';
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/>';
											str+='<input type="hidden" name="alertId" value="'+alertId+'" id="alertHiddenId"/>';
											str+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" type="button" id="uploadBtnId">upload</button>';
										str+='</div>';
										str+='</form>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
							str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
						str+='</p>';
						str+='<div id="alertDetails"></div>';
						str+='<div id="articleAttachment"></div>';
						str+='<div id="alertCategory"></div>';
						str+='<div id="alertSubtask"></div>';
						str+='<div id="alertComments"></div>';
						str+='<div id="alertGeneralComments"></div>';
						str+='<div status-body="task" class="m_top20"></div>';
						str+='<div status-body="subTask" class="m_top20"></div>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<div class="row">';
							str+='<div class="col-sm-1 text-center">';
								str+='<span class="icon-name icon-primary">Ra</span>';
							str+='</div>';
							str+='<div class="col-sm-11">';
								str+='<div class="panel panel-default panel-border-white">';
									str+='<div class="panel-heading">';
										str+='<p>(Press Alt+t toggle between Telugu & English)</p>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<div class="comment-area">Comment Here</div>';
										str+='<textarea class="form-control comment-area" id="alertCommentId" placeholder="Comment here..."></textarea>';
									str+='</div>';
									str+='<div class="panel-footer text-right">';
										str+='<button class="btn btn-primary comment-btn" attr_alert_id="'+alertId+'" id="commentChangeId">Save</button>';
										str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#rightSideExpandView").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	initializeFile();
	dateRangePicker(alertId);
	assignedOfficersDetailsForAlert(alertId);
	departmentsByAlert(alertId);
	getAlertData(alertId);
	var options = {
	  sourceLanguage:
		  google.elements.transliteration.LanguageCode.ENGLISH,
	  destinationLanguage:
		  [google.elements.transliteration.LanguageCode.TELUGU],
	  shortcutKey: 'alt+t',
	  transliterationEnabled: true
	};
	var control = new google.elements.transliteration.TransliterationControl(options);
	control.makeTransliteratable(['alertCommentId']);
}
google.load("elements", "1", {
	packages: "transliteration"
});
function assignedOfficersDetailsForAlert(alertId)
{
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getAssignedOfficersDetailsAction.action',
	data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildAssignedOfficersDetailsForAlert(result);
		}else{
			assignUser(alertId);
		}
		
	});
}
function getAlertData(alertId)
{
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
		getAlertCategortByAlert(alertId);
		//getInvolvedMembersDetilas(alertId);
		getSubTaskInfoForAlert(alertId);
		getCommentsForAlert(alertId);
		getDocumentsForAlert(alertId);
		if(result != null && result.length > 0){
			buildAlertDataNew(result)
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
		}else{
			$("#alertDetails").html("NO DATA");
		}
	});
}
  
  
function buildAlertDataNew(result)
{
	var str='';
	var str1='';
	$("#statusId").html(result[0].status);
	$("#impactLevel").html(result[0].regionScope);
	if(result[0].severity != null)
	{
		$("#priorityBodyId").html(result[0].severity);
	}
	$("#statusIdColor").css("background-color",result[0].statusColor);
	if(result[0].dueDate != null)
	{
		$('.modal-date').data('daterangepicker').setStartDate(result[0].dueDate);
		$('.modal-date').data('daterangepicker').setEndDate(result[0].dueDate);
		$('.modal-date').html(result[0].dueDate);
	}
	
	//priorityRadioCls
	if(result[0].severityId != null && result[0].severityId > 0){
		$("input[name=alert-status-change-list][value='"+result[0].severityId+"']").prop("checked",true);
	}
	
	str+='<div class="row m_top20">';
		for(var i in result)
		{
			str+='<div class="col-sm-1 text-center body-icons">';
				str+='<i class="fa fa-check fa-2x"></i>';
			str+='</div>';
			str+='<div class="col-sm-11">';
				str+='<h3>'+result[i].title+'</h3>';
				str+='<p class="m_top10">'+result[i].desc+'</p>';
				str+='<p class="m_top10"><small> <i class="fa fa-map-marker"></i> '+result[i].locationVO.state+','+result[i].locationVO.districtName+','+result[i].locationVO.constituencyName+','+result[i].locationVO.wardName+','+result[i].locationVO.villageName+'</small></p>';
				str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Created : '+result[i].date+'</small></p>';
			str+='</div>';
		}
	str+='</div>';
	str1+='<div class="row m_top20">';
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			if(result[i].imageUrl != null){
				str1+='<div class="col-sm-4">';
					str1+='<h4 class="text-muted text-capital">article attachment</h4>';
					str1+='<img class="articleDetailsCls img-responsive m_top20" attr_articleId='+result[i].alertCategoryTypeId+' src="http://mytdp.com/NewsReaderImages/'+result[i].imageUrl+'" style="width: 150px; height: 150px;cursor:pointer"/>';
				str1+='</div>';
				str1+='<div class="col-sm-7" id="existingDocsDivId"></div>';
			}else{
				str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
			}
			
		}
	str1+='</div>';
	$("#alertDetails").html(str);
	$("#articleAttachment").html(str1);
	
}
function getAlertCategortByAlert(alertId){
	$("#categoryId").html('');
	var jsObj =
	{
		alertId  :alertId
	}
	$.ajax({
	  type:'GET',
	  url: 'getAlertCategoryByAlertAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			var str='';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-1 text-center body-icons">';
					str+='<i class="fa fa-tags fa-2x"></i>';
				str+='</div>';
				str+='<div class="col-sm-11">';
					str+='<h4 class="text-muted text-capital">category</h4>';
					str+='<p class="m_top20"><span class="label label-default label-category">'+result+'</span></p>';
				str+='</div>';
			str+='</div>';
			
			$("#alertCategory").append(str);
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
		if(result != null && result.length > 0)
		{
			buildAlertCandidateData(result);
		}
	});
}
function buildAlertCandidateData(result,categoryId)
{

	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-users fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital">involved members details</h4>';
			for(var i in result)
			{
				
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
				if(result[i].impactLevelId == 1)
				{
					str+=' <p class="label label-success" style="margin-top: 7px;">Positive</p>'; 
				}else if(result[i].impactLevelId == 2){
					str+=' <p class="label label-danger" style="margin-top: 7px;">Negative</p>';
				}else{
					str+=' <p class="label label-neutral" style="margin-top: 7px;">Neutral</p>';
				}
			}
		str+='</div>';
	str+='</div>';
	
	$("#alertDetails").append(str);
}
function getGroupedArticlesInfo(articleId)
{
	$.ajax({
		  type : 'GET',      
		  //url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		  url: "http://mytdp.com/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
	}).then(function(result){
		console.log(result);
		//$("#alertDetails").append(str);
	});
}

function departmentsByAlert(alertId){
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getDepartmentsByAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		str+='<p class="m_top20">';
			for(var i in result)
			{
				str+='<span class="label label-default label-category">'+result[i].name+'</span>';
			}
		str+='</p>';
		$("#alertDetails").append(str);
	});
}
function getSubTaskInfoForAlert(alertId){
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		type:'GET',
		url: 'getSubTaskInfoForAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildSubTaskInfoForAlert(result);
		}
	});
}
function buildSubTaskInfoForAlert(result)
{
	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-level-down fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital">subtask</h4>';
			str+='<ul class="assign-subtask-list m_top20">';
				for(var i in result)
				{
					str+='<li class="assigned">';
						str+='<div class="row">';
							str+='<div class="col-sm-1">';
								str+='<i class="glyphicon glyphicon-ok"></i>';
							str+='</div>';
							str+='<div class="col-sm-9">';
								str+='<p>'+result[i].userName+'</p>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
								str+='<span class="icon-name icon-primary"></span>';
								str+='<span class="label label-default">...</span>';
							str+='</div>';
						str+='</div>';
					str+='</li>';
				}
			str+='</ul>';
		str+='</div>';
	str+='</div>';
	$("#alertSubtask").html(str);
}
function getCommentsForAlert(alertId){
	$("#alertGeneralComments").html(spinner);
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		type:'GET',
		url: 'getCommentsForAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		if(result != null && result.length > 0)
		{
			buildCommentsForAlert(result);
		}else{
			$("#alertGeneralComments").html("NO DATA");
		}
	});
}
function buildCommentsForAlert(result)
{
	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-comments-o fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital">comments</h4>';
			for(var i in result)
			{
				str+='<div class="media">';
					str+='<div class="media-left">';
						if(result[i].userName != null && result[i].userName.length > 0)
						{
							str+='<span class="icon-name icon-primary text-capital">'+result[i].userName.substring(0,2)+'</span>';
						}else{
							str+='<span class="icon-name icon-primary">ME</span>';
						}
					str+='</div>';
					str+='<div class="media-body">';
						if(result[i].userName != null && result[i].userName.length > 0)
						{
							str+='<p class="m_top5">'+result[i].userName+'</p>';
						}
						if(result[i].attachementsList != null && result[i].attachementsList.length > 0)
						{
							str+='<p class="m_top5">Attachments : '+result[i].attachementsList+'</p>';
						}
						if(result[i].date != null && result[i].date.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].date+'</p>';
						}
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#alertGeneralComments").html(str);
}
function getDeptNamesForMultiLevel(){   
  $.ajax({
      type:'GET',
      url: 'getDeptListForMultiLvlAction.action',
    data: {}
    }).done(function(result){
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
					var defaultDepartmentID = result[i].id;
					if(i == 0)
					{
						str+='<a role="button" class="collapseIconForMulti departmentLevelWiseDetails" attr_departmentId="'+result[i].id+'" data-toggle="collapse" data-parent="#departmentOverview" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
						str+='<h4 class="panel-title fontColor" >'+result[i].name+'<span style="margin-left:20px"></span>';
						  str+='</h4>';
						str+='</a>';
					}else{
						str+='<a role="button" class="collapsed collapseIconForMulti departmentLevelWiseDetails" attr_departmentId="'+result[i].id+'"  data-toggle="collapse" data-parent="#departmentOverview" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
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
					  str+='<div class="col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid rgb(211, 211, 211);">';
							str+='<div class="row">';
								str+='<div class="col-md-4 col-xs-12 col-sm-12">';
									str+='<h4>STATE LEVEL</h4>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-12 pull-right">';
									str+='<ul class="switch_btn_multiLevel stateWiseInterChange">';
										str+='<li  attr_type="overview" attr_department_id = "'+result[i].id+'">overview</li>';
										str+='<li class="active" attr_type="status" attr_department_id = "'+result[i].id+'">status</li>';
									str+='</ul>';
								str+='</div>';
							str+='</div>';
							str+='<div id="stateLevelGraphStausWise"></div>';
						str+='</div>';
						
						str+='<div class="col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid rgb(211, 211, 211);">';
							str+='<div class="row">';
								
								str+='<div class="col-md-9 col-xs-12 col-sm-12">';
									str+='<h4>DISTRICT LEVEL</h4>';
								str+='</div>';
								
								str+='<div class="col-md-3 col-xs-12 col-sm-12 pull-right">';
									str+='<ul class="switch_btn_multiLevel districtWiseInterChange">';
										str+='<li  attr_type="overview" attr_department_id = "'+result[i].id+'">overview</li>';
										str+='<li class="active" attr_type="status" attr_department_id = "'+result[i].id+'">status</li>';
									str+='</ul>';
								str+='</div>';
								
								
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="row">';
									
									str+='<div class="col-md-2 col-xs-12 col-sm-12">';
									str+='<ul class="list-inline activeUlCls  constituencyUl districtWiseSorting">';
									str+='<li class="active " attr_sorting_type="count" attr_order_type="desc" attr_department_id = "'+result[i].id+'">';
										str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
									str+='</li>';
									str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id = "'+result[i].id+'">';
										str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
									str+='</li>';
									str+='<li class="" attr_sorting_type="name" attr_order_type="asc"  attr_department_id = "'+result[i].id+'">';
										str+='A-Z';
									str+='</li>';
									str+='<li class="" attr_sorting_type="name" attr_order_type="desc"  attr_department_id = "'+result[i].id+'">';
										str+='Z-A';
									str+='</li>';
									str+='</ul>';
								str+='</div>';
								
								
								str+='<div class="col-sm-12 col-xs-12 col-md-2">';
									str+='<select class="form-control districtWiseSortingApplyGraph" id="districtWiseLevelsDivId" attr_department_id = "'+result[i].id+'" >';
									str+='</select>';
								str+='</div>';
								
								str+='</div>';
								str+='</div>';
								
							str+='</div>';
							
							str+='<div id="districtLevelGraphStausWise"></div>';
						str+='</div>';
						
						str+='<div class="col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid rgb(211, 211, 211);">';
							str+='<div class="row">';
								str+='<div class="col-md-9 col-xs-12 col-sm-12">';
									str+='<h4>DIVISION LEVEL</h4>';
								str+='</div>';
								
								str+='<div class="col-md-3 col-xs-12 col-sm-12 pull-right">';
									str+='<ul class="switch_btn_multiLevel divisionWiseInterChange">';
										str+='<li attr_type="overview" attr_department_id = "'+result[i].id+'">overview</li>';
										str+='<li class="active"  attr_type="status" attr_department_id = "'+result[i].id+'">status</li>';
									str+='</ul>';
								str+='</div>';
								
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="row">';
									
									str+='<div class="col-md-2 col-xs-12 col-sm-12">';
									str+='<ul class="list-inline activeUlCls  constituencyUl divisionWiseSorting">';
									str+='<li class="active " attr_sorting_type="count" attr_order_type="desc" attr_department_id = "'+result[i].id+'">';
										str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
									str+='</li>';
									str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id = "'+result[i].id+'">';
										str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
									str+='</li>';
									str+='<li class="" attr_sorting_type="name" attr_order_type="asc"  attr_department_id = "'+result[i].id+'">';
										str+='A-Z';
									str+='</li>';
									str+='<li class="" attr_sorting_type="name" attr_order_type="desc"  attr_department_id = "'+result[i].id+'">';
										str+='Z-A';
									str+='</li>';
									str+='</ul>';
								str+='</div>';
								
								
								str+='<div class="col-sm-12 col-xs-12 col-md-2">';
									str+='<select class="form-control " id="divisionDistWiseLevelsDivId" attr_department_id = "'+result[i].id+'" >';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-sm-12 col-xs-12 col-md-2">';
									str+='<select class="form-control divisionWiseSortingApplyGraph" id="divisionWiseLevelsDivId" attr_department_id = "'+result[i].id+'" >';
									str+='<option value="0">Select Division</option>';
										str+='</select>';
								str+='</div>';
								
							str+='</div>';
							str+='</div>';
							
							str+='</div>';
							
							str+='<div id="divisionLevelGraphStausWise"></div>';
						str+='</div>';
						
						
						
						str+='<div class="col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid rgb(211, 211, 211);">';
							str+='<div class="row">';
								str+='<div class="col-md-9 col-xs-12 col-sm-12">';
									str+='<h4>SUB-DIVISION LEVEL</h4>';
								str+='</div>';
								
								str+='<div class="col-md-3 col-xs-12 col-sm-12 pull-right">';
									str+='<ul class="switch_btn_multiLevel subDivisionWiseInterChange">';
										str+='<li attr_type="overview" attr_department_id = "'+result[i].id+'">overview</li>';
										str+='<li class="active"  attr_type="status" attr_department_id = "'+result[i].id+'">status</li>';
									str+='</ul>';
								str+='</div>';
								
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="row">';
									
									str+='<div class="col-md-2 col-xs-12 col-sm-12">';
									str+='<ul class="list-inline activeUlCls  constituencyUl subDivisionWiseSorting">';
									str+='<li class="active " attr_sorting_type="count" attr_order_type="desc" attr_department_id = "'+result[i].id+'">';
										str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
									str+='</li>';
									str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id = "'+result[i].id+'">';
										str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
									str+='</li>';
									str+='<li class="" attr_sorting_type="name" attr_order_type="asc"  attr_department_id = "'+result[i].id+'">';
										str+='A-Z';
									str+='</li>';
									str+='<li class="" attr_sorting_type="name" attr_order_type="desc"  attr_department_id = "'+result[i].id+'">';
										str+='Z-A';
									str+='</li>';
									str+='</ul>';
								str+='</div>';
								
								str+='<div class="col-sm-4 col-xs-12 col-md-2">';
									str+='<select class="form-control locationWiseSubDiviDistOnChange" id="SubDivisionDistNamesId" attr_department_id = "'+result[i].id+'">';
									
									str+='</select>';
								str+='</div>';
								str+='<div class="col-sm-4 col-xs-12 col-md-2">';
									str+='<select class="form-control locationWiseSubDiviDiviOnChange" id="SubDivisionDiviNamesId" attr_department_id = "'+result[i].id+'">';
									str+='<option value="0">Select Division</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-sm-4 col-xs-12 col-md-2">';
									str+='<select class="form-control locationWiseSubDiviOnChange" id="SubDivisionNamesId" attr_department_id = "'+result[i].id+'">';
									str+='<option value="0">Select SubDivision</option>';
									str+='</select>';
								str+='</div>';
								
							str+='</div>';
							str+='</div>';
							
							str+='</div>';
							
							str+='<div id="subDivisionLevelGraphStausWise"></div>';
						str+='</div>';
						
						
						
					  str+='</div>';
					str+='</div>';
				  str+='</div>';
				}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#levelWiseDepartmentDetailsId").html(str);
		
		//state Level
		getStateThenGovtDeptScopeWiseAlertCountForStateLevel(defaultDepartmentID,"status");
		getStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(defaultDepartmentID,"status","count","desc",0);
		getStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(defaultDepartmentID,"status","count","desc",0,0);
		getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(defaultDepartmentID,"status","count","desc",0,0,0);
		getDistIdListForDistFilter(defaultDepartmentID);
		getDistIdListForDivisionFilter(defaultDepartmentID);  	
		getDistrictIdListForSubDivisionFilter(defaultDepartmentID); 		
		
		
	}else{
		$("#levelWiseDepartmentDetailsId").html("No Data Available");
	}
	
}
function getTotalArticledetails(articleId){
	
	$("#alertManagementPopupBody1,#alertManagementPopupHeading").html(spinner);
	$.ajax({
		  type : 'GET',      
		  //url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		  url: "http://mytdp.com/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
	}).then(function(results){
		var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
		var result = results[0];
		var str = '';
		var heading = '';
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

		$("#alertManagementPopupBody1").html(str);
		$("#alertManagementPopupHeading").html(heading)
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

	
function getgroupType(){
		 var groupType = ''; 
		$('.stateWiseInterChange li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  groupType = $(this).attr("attr_type");
			 }
		});
		return groupType;
	}
	
	function getgroupTypeDistrict(){
		 var groupType = ''; 
		$('.districtWiseInterChange li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  groupType = $(this).attr("attr_type");
			 }
		});
		return groupType;
	}
	function getgroupTypeDivision(){
		 var groupTypeDiv = ''; 
		$('.divisionWiseInterChange li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  groupTypeDiv = $(this).attr("attr_type");
			 }
		});
		return groupTypeDiv;
	}
	function getgroupTypeSubDivision(){
		 var groupTypeSubDiv = ''; 
		$('.subDivisionWiseInterChange li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  groupTypeSubDiv = $(this).attr("attr_type");
			 }
		});
		return groupTypeSubDiv;
	}
	function getDistrictWiseSorting(){
		 var districtSortingType = ''; 
		 var districtOrderType = ''; 
		$('.districtWiseSorting li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  districtSortingType = $(this).attr("attr_sorting_type");
			  districtOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			districtSortingType : districtSortingType,
			districtOrderType :districtOrderType
			};
	}
	
	function getDivisionWiseSorting(){
		 var divisionSortingType = ''; 
		 var divisionOrderType = ''; 
		$('.divisionWiseSorting li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  divisionSortingType = $(this).attr("attr_sorting_type");
			  divisionOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			divisionSortingType : divisionSortingType,
			divisionOrderType :divisionOrderType
			};
	}
	
	function getSubDivision(){
		 var subSortingType = ''; 
		 var subOrderType = ''; 
		$('.locationWiseSortingSubDivision li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  subSortingType = $(this).attr("attr_sorting_type");
			  subOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			subSortingType : subSortingType,
			subOrderType :subOrderType
			};
	}
		
$(document).on("click",".departmentLevelWiseDetails",function(){
		var departmentId = $(this).attr("attr_departmentId");
		getStateThenGovtDeptScopeWiseAlertCountForStateLevel(departmentId,"status");
		getStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(departmentId,"status","count","desc",0);
		getStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(departmentId,"status","count","desc",0,0);
		getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,"status","count","desc",0,0,0);
		
		
});

$(document).on("click",".stateWiseInterChange li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var groupType = getgroupType();
		getStateThenGovtDeptScopeWiseAlertCountForStateLevel(departmentId,groupType);
});

$(document).on("click",".districtWiseInterChange li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var groupType = getgroupTypeDistrict();
		var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
		var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
		var districtId = $("#districtWiseLevelsDivId").val();
		getStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(departmentId,groupType,sortingType,orderType,districtId);
		
		
});

$(document).on("click",".districtWiseSorting li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var sortingType = $(this).attr("attr_sorting_type");
			var departmentId = $(this).attr("attr_department_id");
			var orderType = $(this).attr("attr_order_type");
			var groupType = getgroupTypeDistrict();
			var districtId = $("#districtWiseLevelsDivId").val();
			getStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(departmentId,groupType,sortingType,orderType,districtId);
			
});

$(document).on("change",".districtWiseSortingApplyGraph",function(){
			var departmentId = $(this).attr("attr_department_id");
			var groupType = getgroupTypeDistrict();
			var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
			var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
			var districtId = $("#districtWiseLevelsDivId").val();
			getStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(departmentId,groupType,sortingType,orderType,districtId);
			
});

//Division
$(document).on("click",".divisionWiseInterChange li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var groupType = getgroupTypeDivision();
		var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
		var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
		getStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(departmentId,groupType,sortingType,orderType,0,0);
});

$(document).on("click",".divisionWiseSorting li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var sortingType = $(this).attr("attr_sorting_type");
			var departmentId = $(this).attr("attr_department_id");
			var orderType = $(this).attr("attr_order_type");
			var groupType = getgroupTypeDivision();
			//var districtId = $("#districtWiseLevelsDivId").val();
			
			getStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(departmentId,groupType,sortingType,orderType,0,0);
			
});

$(document).on("change",".divisionWiseSortingApplyGraph",function(){
			var departmentId = $(this).attr("attr_department_id");
			var groupType = getgroupTypeDivision();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			var districtWorkLocationId = $("#divisionWiseLevelsDivId").val();
			var divisionWorkLocationId = $("#divisionDistWiseLevelsDivId").val();
			getStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(departmentId,groupType,sortingType,orderType,districtWorkLocationId,divisionWorkLocationId);
			
});

// Sub-Division
$(document).on("click",".subDivisionWiseInterChange li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var departmentId = $(this).attr("attr_department_id");
		var groupType = getgroupTypeSubDivision();
		var sortingType = getSubDivision().subSortingType; // 'value1'
		var orderType = getSubDivision().subOrderType; // 'value2'
		getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,groupType,sortingType,orderType,0,0,0);
		
		
});
$(document).on("click",".subDivisionWiseSorting li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			
			var sortingType = $(this).attr("attr_sorting_type");
			var departmentId = $(this).attr("attr_department_id");
			var orderType = $(this).attr("attr_order_type");
			
			var groupType = getgroupTypeSubDivision();
			
			getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,groupType,sortingType,orderType,0,0,0);
			
});
$(document).on("change",".locationWiseSubDiviDistOnChange",function(){
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			
			var groupType = getgroupTypeSubDivision();
			var departmentId = $(this).attr("attr_department_id");
			
			var districtId =$("#SubDivisionDistNamesId").val();
			getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,groupType,sortingType,orderType,districtId,0,0);
			getDivisionIdListForSubDivisionFilter(departmentId,districtId);  
			
		});
		$(document).on("change",".locationWiseSubDiviDiviOnChange",function(){
			
			var departmentId = $(this).attr("attr_department_id");
			
			
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var groupType = getgroupTypeSubDivision();
			
			getSubDivisionIdListForSubDivisionFilter(departmentId,districtId,districtDivisionId);  
			getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,groupType,sortingType,orderType,districtId,districtDivisionId,0);
		});
		$(document).on("change",".locationWiseSubDiviOnChange",function(){
			var departmentId = $(this).attr("attr_department_id");
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var districtSubDivisionId =$("#SubDivisionNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
				var groupType = getgroupTypeSubDivision();
			
			getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,groupType,sortingType,orderType,districtId,districtDivisionId,districtSubDivisionId);
			
		});
//status Wise MultiLevel Blocks Ajax Calls Start
//state Level
function getStateThenGovtDeptScopeWiseAlertCountForStateLevel(departmentId,groupType){
  var paperIdArr = globalNewsPaperIdArr;
  var chanelIdArr = globalChannelIdArr;

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : globalStateId,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,  
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 1, 
	districtWorkLocationId : 0,  
	divisionWorkLocationId : 0,       
	subDivisionWorkLocationId : 0,	
    sortType : "count",
    order : "desc",
	group:groupType,
	alertType:"alert",
	searchType:"statusWise"
	
    }
    $.ajax({
    type:'GET',         
    url: 'getStateThenGovtDeptScopeWiseAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildStateThenGovtDeptScopeWiseAlertCountForStateLevel(result,groupType);
    });
}

function buildStateThenGovtDeptScopeWiseAlertCountForStateLevel(result,groupType){
	
	if(groupType == "status"){
		if(result !=null && result.length>0){
			var locationNamesArrState=[];
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
			for(var i in result){
				
				 locationNamesArrState.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==11){
							 Incomplete.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==12){
							 Closed.push(result[i].subList[j].count);
						}
						
						
						
					}
					
				}
			}
			
			var mainJosnObjArrState = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrState.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrState.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#stateLevelGraphStausWise").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrState
				var id = 'stateLevelGraphStausWise';
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
					categories: locationNamesArrState
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
		}else{
			$("#stateLevelGraphStausWise").html('No Data Available');
		}
	}else if(groupType == "overview"){
		
		if(result !=null && result.length>0){
			
			
				var mainlocationArr =[];
				//var mainlocationArr1 =[];
					var nmaesArr =[];
					var colorArr=[];
				if(result[0].subList !=null && result[0].subList.length>0){
					for(var j in result[0].subList){
						//var tempArr =[];
						var tempArr = {"y":result[0].subList[j].count,color:result[0].subList[j].severtyColor};
						
						//tempArr.push(result[0].subList[j].name)
						//tempArr.push(result[0].subList[j].count)
						//colorArr.push(result[0].subList[j].severtyColor)
						
						nmaesArr.push(result[0].subList[j].name);
						mainlocationArr.push(tempArr);
						//mainlocationArr.push(result[0].subList[j].count);
						//mainlocationArr1.push(mainlocationArr)
						
						
						
					}
					
				}

			console.log(mainlocationArr)
			//console.log(mainlocationArr1)
		$('#stateLevelGraphStausWise').highcharts({
			
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
				/* stackLabels: {
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
						return (this.y);
					} 
					
				} */
			},
			tooltip: {
				 pointFormat: '<b>{point.y}</b>',
				 shared:true
			},
			
			legend: {
				verticalAlign:'top',
				enabled: false
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
		}
	}
	
}


//District Level
function getStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(departmentId,groupType,sortType,order,districtId){
  var paperIdArr = globalNewsPaperIdArr;
  var chanelIdArr = globalChannelIdArr;

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : globalStateId,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,  
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 5, 
	districtWorkLocationId : districtId,  
	divisionWorkLocationId : 0,       
	subDivisionWorkLocationId : 0,	
    sortType : sortType,
    order : order,
	group:groupType,
	alertType:"alert",
	searchType:"statusWise"
	
    }
    $.ajax({
    type:'GET',         
    url: 'getStateThenGovtDeptScopeWiseAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,groupType,departmentId);
    });
}

function buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,groupType,departmentId){
	
	
	if(groupType == "status"){
		
		if(result !=null && result.length>0){
			var districtIdCCC;      
			var districtNameCCC;
			var totalCountCCC;
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
				 var Incomplete = [];
				 var Closed = [];
				
			for(var i in result){
				districtIdCCC = result[i].id;
				districtNameCCC = result[i].name;
				totalCountCCC = result[i].totalCount;
				 locationNamesArrDistrict.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
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
				mainJosnObjArrDistrict.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrDistrict.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrDistrict.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#districtLevelGraphStausWise").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrict
				var id = 'districtLevelGraphStausWise';
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
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=0;
										getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,departmentId);
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
				$.each($('#districtLevelGraphStausWise').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldistrictCls");    
					$(this).attr("attr_district_id",districtIdCCC);         
					$(this).attr("attr_district_name",districtNameCCC);	
					$(this).attr("attr_total_count",totalCountCCC);	
					$(this).attr("attr_department_Id",departmentId);	
				}); 
		}else{
			$("#districtLevelGraphStausWise").html('No Data Available');
		}
	}else if(groupType == "overview"){
		var districtIdDDD;      
		var districtNameDDD;
		var totalCountDDD;
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
				districtIdDDD = result[i].id;
				districtNameDDD = result[i].name;
				totalCountDDD = result[i].totalCount;
				 locationNamesArrDistrictOverView.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						
						
						
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
				$("#districtLevelGraphStausWise").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'districtLevelGraphStausWise';
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
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=value[0];
										
										getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,departmentId);
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
				$.each($('#districtLevelGraphStausWise').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldistrictCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCountDDD);
					$(this).attr("attr_department_Id",departmentId);					
				}); 
		}else{
			$("#districtLevelGraphStausWise").html('No Data Available');
		}
	}
	
}


	function getDistIdListForDistFilter(departmentId){
		
	 $("#districtWiseLevelsDivId").html('');
	 var groupType = getgroupTypeDistrict();
	  var paperIdArr = globalNewsPaperIdArr;
	  var chanelIdArr = globalChannelIdArr;

		var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,    
		govtDepartmentId : departmentId,
		parentGovtDepartmentScopeId : 5,
		group : groupType,//overview
		alertType:"alert",
		searchType:"statusWise"
		}
		$.ajax({
		type:'GET',                  
		url: 'getDistIdListForDistFilterAction.action',
		data: {task :JSON.stringify(jsObj)}     
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#districtWiseLevelsDivId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#districtWiseLevelsDivId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});    
	}


//Division Level
function getStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(departmentId,groupType,sortType,order,districtWorkLocationId,divisionWorkLocationId){
  var paperIdArr = globalNewsPaperIdArr;
  var chanelIdArr = globalChannelIdArr;

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : globalStateId,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,  
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 6, 
	districtWorkLocationId : districtWorkLocationId,  
	divisionWorkLocationId : divisionWorkLocationId,       
	subDivisionWorkLocationId : 0,	
    sortType : sortType,
    order : order,
	group:groupType,
	alertType:"alert",
	searchType:"statusWise"
	
    }
    $.ajax({
    type:'GET',         
    url: 'getStateThenGovtDeptScopeWiseAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,groupType,departmentId);
    });
}

function buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,groupType,departmentId){
	var districtId;
	var districtName;
	var totalCount;
	
	if(groupType == "status"){
		if(result !=null && result.length>0){
			var locationNamesArrDivision=[];
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
			for(var i in result){
				districtId = result[i].id;
				districtName = result[i].name;
				totalCount = result[i].totalCount;
				
				 locationNamesArrDivision.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						
						
						
					}
					
				}
			}
			
			var mainJosnObjArrDivision = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrDivision.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrDivision.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#divisionLevelGraphStausWise").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDivision
				var id = 'divisionLevelGraphStausWise';
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
					categories: locationNamesArrDivision
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
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=0;
										getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,departmentId);
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
				$.each($('#divisionLevelGraphStausWise').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);
					$(this).attr("attr_department_Id",departmentId);					
				});
		}else{
			$("#divisionLevelGraphStausWise").html('No Data Available');
		}
	}else if(groupType == "overview"){
		
		if(result !=null && result.length>0){
			
			var locationNamesArrDivisionOverView=[];
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
				districtId = result[i].id;
				districtName = result[i].name;
				totalCount = result[i].totalCount;
				 locationNamesArrDivisionOverView.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						
						
						
					}
					
				}
			}
			
			
			var mainJosnObjArrDivisionOverview = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		
			
			var heightOfDiv = locationNamesArrDivisionOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#divisionLevelGraphStausWise").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDivisionOverview
				var id = 'divisionLevelGraphStausWise';
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
					categories: locationNamesArrDivisionOverView
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
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=value[0];
										
										getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,departmentId);
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
				$.each($('#divisionLevelGraphStausWise').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);
					$(this).attr("attr_department_Id",departmentId);	
				});
		}else{
			$("#divisionLevelGraphStausWise").html('No Data Available');
		}
	}
	
}	

//Sub Division Level
function getStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(departmentId,groupType,sortingType,orderType,districtId,districtDivisionId,districtSubDivisionId){
  var paperIdArr = globalNewsPaperIdArr;
  var chanelIdArr = globalChannelIdArr;

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : globalStateId,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,  
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 7, 
	districtWorkLocationId : districtId,  
	divisionWorkLocationId : districtDivisionId,       
	subDivisionWorkLocationId : districtSubDivisionId,	
    sortType : sortingType,
    order : orderType,
	group:groupType,
	alertType:"alert",
	searchType:"statusWise"
	
    }
    $.ajax({
    type:'GET',         
    url: 'getStateThenGovtDeptScopeWiseAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(result,groupType,departmentId);
    });
}

function buildStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(result,groupType,departmentId){
	var districtId;
	var districtName;
	var totalCount;
	if(groupType == "status"){
		if(result !=null && result.length>0){
			var locationNamesArrsubDivision=[];
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
			for(var i in result){
				districtId = result[i].id;
				districtName = result[i].name;
				totalCount = result[i].totalCount;
				 locationNamesArrsubDivision.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						
						
						
					}
					
				}
			}
			
			var mainJosnObjArrsubDivision = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrsubDivision.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrsubDivision.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#subDivisionLevelGraphStausWise").css("height",heightOfDiv);
			}else{
				$("#subDivisionLevelGraphStausWise").css("height","auto");
			}
			
			
			var data = mainJosnObjArrsubDivision
				var id = 'subDivisionLevelGraphStausWise';
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
					categories: locationNamesArrsubDivision
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
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=0;
										getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,departmentId);
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
				$.each($('#subDivisionLevelGraphStausWise').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotalsubdivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);
					$(this).attr("attr_department_Id",departmentId);
				}); 
		}else{
			$("#subDivisionLevelGraphStausWise").html('No Data Available');
		}
	}else if(groupType == "overview"){
		
		if(result !=null && result.length>0){
			
			var locationNamesArrsubDivisionOverView=[];
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
				districtId = result[i].id;
				districtName = result[i].name;
				totalCount = result[i].totalCount;
				 locationNamesArrsubDivisionOverView.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count});
						}
						
						
						
					}
					
				}
			}
			
			
			var mainJosnObjArrsubDivisionOverview = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		
			
			var heightOfDiv = locationNamesArrsubDivisionOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#subDivisionLevelGraphStausWise").css("height",heightOfDiv);
			}else{
				$("#subDivisionLevelGraphStausWise").css("height","auto");
			}
			
			
			var data = mainJosnObjArrsubDivisionOverview
				var id = 'subDivisionLevelGraphStausWise';
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
					categories: locationNamesArrsubDivisionOverView
				}
				var plotOptions =  {
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
										var scopeId=value[0];
										
										getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,departmentId);
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
				$.each($('#subDivisionLevelGraphStausWise').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotalsubdivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);
					$(this).attr("attr_department_Id",departmentId);	
				}); 
		}else{
			$("#subDivisionLevelGraphStausWise").html('No Data Available');
		}
	}
	
}

function getDistIdListForDivisionFilter(departmentId){
	$("#divisionDistWiseLevelsDivId").html('');
  var paperIdArr = globalNewsPaperIdArr;
  var chanelIdArr = globalChannelIdArr;
var groupType = getgroupTypeDivision();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : globalStateId,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 6,
	group : groupType,
	alertType:"alert",
	searchType:"statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDistIdListForDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#divisionDistWiseLevelsDivId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#divisionDistWiseLevelsDivId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}


function getDivisionIdListForDivisionFilter(departmentId){
	$("#divisionWiseLevelsDivId").html('');
	var districtId = $("#divisionDistWiseLevelsDivId").val();
		var groupType = getgroupTypeDivision();
  var paperIdArr = globalNewsPaperIdArr;
  var chanelIdArr = globalChannelIdArr;

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : globalStateId,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 6,
	districtWorkLocationId : districtId, //district id here
	group : groupType,
	alertType:"alert",
	searchType:"statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDivisionIdListForDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#divisionWiseLevelsDivId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#divisionWiseLevelsDivId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}	
function showSbmitStatusNew(uploadResult,alertId){
	if(uploadResult !=null && uploadResult.search("success") != -1){
		getDocumentsForAlert(alertId);
	}
}
function getDocumentsForAlert(alertId){
	$("#existingDocsDivId").html("");
	var jsObj ={
		alertId:alertId 
    }
    $.ajax({
    type:'GET',         
    url: 'getDocumentsForAlertsAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="text-muted text-capital">alert attachment</h4>';
			str+='<ul class="list-inline imageShowOpen">';
			for(var i in result){
				str+='<li class="" attr_doc_id="'+result[i].id+'"  attr_path="'+result[i].name+'" id="imageAttachmentOpen'+result[i].id+'" >';
					str+='<img src="http://mytdp.com/'+result[i].name+'" style="width: 60px; height: 60px;cursor:pointer" />';
				str+='</li>';
			}
			str+='</ul>';
			$("#existingDocsDivId").html(str);
		}
    });
}

//sub division wise district names
function getDistrictIdListForSubDivisionFilter(departmentId){
	$("#SubDivisionDistNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];
var groupType = getgroupTypeSubDivision();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 7,
	group : groupType,
	alertType:"alert",
	searchType:"statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDistrictIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#SubDivisionDistNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#SubDivisionDistNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

//sub division wise division names 
function getDivisionIdListForSubDivisionFilter(departmentId,districtId){
	
	$("#SubDivisionDiviNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];
var groupType = getgroupTypeSubDivision();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 7,
  districtWorkLocationId : districtId,//district id here, default 0
  group : groupType,
 alertType:"alert",
	searchType:"statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDivisionIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
				for(var i in result){
					$("#SubDivisionDiviNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

//sub division  wise sub division names 
function getSubDivisionIdListForSubDivisionFilter(departmentId,districtId,districtDivisionId){
	
	$("#SubDivisionNamesId").html('');
	var paperIdArr = [];
	var chanelIdArr = [];
	var groupType = getgroupTypeSubDivision();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 7,
  districtWorkLocationId :districtId, //district id here ,default 0
  divisionWorkLocationId :districtDivisionId, //division id here ,default 0
  group : groupType,
  alertType:"alert",
searchType:"statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getSubDivisionIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
    if(result !=null && result.length>0){
				$("#SubDivisionNamesId").append('<option value="0">Select SubDivision</option>');
				for(var i in result){
					$("#SubDivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

function getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,departmentId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    var paperIdList =[];
    var chanelIdList =[];
	var groupType = getgroupTypeDistrict();
	var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
	var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
	var districtId =  $("#districtWiseLevelsDivId").val();
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:5,
    govtDepartmentId:departmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:0,
    subDivisionWorkLocationId:0,
    group:groupType,
     alertType:"alert",
	searchType:"statusWise",
	statusId : statusId,
	govtDeprtMentScopeId : scopeId
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			if(result != null && result.length > 0){
				$("#totalAlertsModalTabId").html('');
				buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
			}else{
				$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
			}
		});
  
}
$(document).on("click",".getTotaldistrictCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	var departmentId = $(this).attr("attr_department_Id");
	getlevelAndStatusWiseClickForDistrict(0,districtName,totalCount,0,departmentId)
});

function getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,departmentId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    var paperIdList =[];
    var chanelIdList =[];
	var groupType = getgroupTypeDivision();
	var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
	var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
	var districtId =  $("#divisionDistWiseLevelsDivId").val();
	var divisionId =  $("#divisionWiseLevelsDivId").val();
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:6,
    govtDepartmentId:departmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:divisionId,
    subDivisionWorkLocationId:0,
    group:groupType,
     alertType:"alert",
	searchType:"statusWise",
	statusId : statusId,
	govtDeprtMentScopeId : scopeId
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			if(result != null && result.length > 0){
				$("#totalAlertsModalTabId").html('');
				buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
			}else{
				$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
			}
		});
  
}
$(document).on("click",".getTotaldivisionCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	var departmentId = $(this).attr("attr_department_Id");
	getlevelAndStatusWiseClickForDivision(0,districtName,totalCount,0,departmentId)
});

function getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,departmentId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    var paperIdList =[];
    var chanelIdList =[];
	var groupType = getgroupTypeSubDivision();
	var sortingType = getSubDivision().subSortingType; // 'value1'
	var orderType = getSubDivision().subOrderType; // 'value2'
	var districtId =  $("#SubDivisionDistNamesId").val();
	var divisionId =  $("#SubDivisionDiviNamesId").val();
	var subdivisionId =  $("#SubDivisionNamesId").val();
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:7,
    govtDepartmentId:departmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:divisionId,
    subDivisionWorkLocationId:subdivisionId,
    group:groupType,
    alertType:"alert",
	searchType:"statusWise",
	statusId : statusId,
	govtDeprtMentScopeId : scopeId
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			if(result != null && result.length > 0){
				$("#totalAlertsModalTabId").html('');
				buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
			}else{
				$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
			}
		});
  
}
$(document).on("click",".getTotalsubdivisionCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	var departmentId = $(this).attr("attr_department_Id");
	
	getlevelAndStatusWiseClickForSubDivision(0,districtName,totalCount,0,departmentId)
});