/*global Function and variables Start*/
var globalLevelObj =  {"1":"STATE","2":"ZONE","3":"REGION","4":"CIRCLE","5":"DISTRICT","6":"DIVISION","7":"SUB DIVISION","8":"MANDAL","9":"MUNICIPALITY","10":"PANCHAYAT","11":"WARD","12":"GMC","13":"CLUSTER"};
var globalFinancialAssColorObj = {"Financial Assistance Required":"#B59AE9","Policy Decision Required":"#6EB6F0","Others":"#3FE2CD"}; 
var globalAlertSourceColorObj =  {"Manual":"#E54BB3","Print Media":"#69BC6E","Electronic Media":"#8D69C8","Call Center":"#EFC000","Facebook":"#00ABED","Twitter":"#F7776C","Social Media":"#00ABED","Monday Grievance":"#FA8072","Janmabhoomi":"#00FF00","Special Grievance - SC/ST":"#0000FF","General Grievance":"#808000"};	 
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
 
var globalStateId = 1;  
var newspapersGlobalArr = [];
var channelGlobalArr = [];
var globalDepartmentIdsArr = [];
var globalUserLevelId=0;
var globalUserLevelValues = [0];
var globalDesignationId=0;
var callCenterGlobalArr = [];
var globalsocialMediaTypeIdsArr = [];
var subLevels = [];
var globalAlertSeverityIdsArr = [];
var globalAlertStatusIdsArr = [];
var globalAlertSubTaskStatusIdsArr = [];
var globalDepartmentObj;
/*new four categories global variable name.*/
var globalMondayGrievanceTypeIdsArr = [];
var globalJanmabhoomiTypeIdsArr = [];
var globalSpecialGrievanceTypeIdsArr = [];
var globalGeneralGrievanceTypeIdsArr = [];
var singleAndMultipleStatusIds=[];
//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><img src="alertDepartment/img/alert logo.png" alt="alert Logo"  class="alert-logo"/></div></div>';
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner-logo"><img src="alertDepartment/img/spinner/1.png" class="right-arrow"/><img src="alertDepartment/img/spinner/2.png" class="right-arrow1"/><img src="alertDepartment/img/spinner/3.png" class="left-arrow"/><img src="alertDepartment/img/spinner/4.png" class="left-arrow1"/><img src="alertDepartment/img/spinner/5.png" class="main-icon"/></div></div></div>';

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
			newspapersGlobalArr.push($(this).attr("attr_val"));
		}
	});
	$(".chanelListCls").each(function(){
		if($(this).is(":checked"))
		{
			channelGlobalArr.push($(this).attr("attr_val"));
		}
	});
	$(".departmentsCls").each(function(){
		if($(this).is(":checked"))
		{
			globalDepartmentIdsArr.push($(this).attr("attr_val"));
		}
	});
	$(".callcenterCls").each(function(){
		if($(this).is(":checked"))
		{
			callCenterGlobalArr.push($(this).attr("attr_val"));
		}
	});
	$(".socialMediaCls").each(function(){
		if($(this).is(":checked"))
		{
			globalsocialMediaTypeIdsArr.push($(this).attr("attr_val"));
		}
	});
	$(".mondayGrievanceCls").each(function(){
		if($(this).is(":checked"))
		{
			globalMondayGrievanceTypeIdsArr.push($(this).attr("attr_val"));
		}
	});
	$(".janmabhoomiCls").each(function(){
		if($(this).is(":checked"))
		{
			globalJanmabhoomiTypeIdsArr.push($(this).attr("attr_val"));
		}
	});
	$(".specialGrievanceCls").each(function(){
		if($(this).is(":checked"))
		{
			globalSpecialGrievanceTypeIdsArr.push($(this).attr("attr_val"));
		}
	});
	$(".generalGrievanceCls").each(function(){
		if($(this).is(":checked"))
		{
			globalGeneralGrievanceTypeIdsArr.push($(this).attr("attr_val"));
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
		newspapersGlobalArr = [];
		channelGlobalArr = [];
		globalDepartmentIdsArr = [];
		callCenterGlobalArr = [];
		globalsocialMediaTypeIdsArr = [];
		globalMondayGrievanceTypeIdsArr = [];
		globalJanmabhoomiTypeIdsArr = [];
		globalSpecialGrievanceTypeIdsArr = [];
		globalGeneralGrievanceTypeIdsArr = [];
		$(".newsPaperListCls").each(function(){
			if($(this).is(":checked"))
			{
				newspapersGlobalArr.push($(this).attr("attr_val"));
			}
		});
		$(".chanelListCls").each(function(){
			if($(this).is(":checked"))
			{
				channelGlobalArr.push($(this).attr("attr_val"));
			}
		});
		$(".departmentsCls").each(function(){
			if($(this).is(":checked"))
			{
				globalDepartmentIdsArr.push($(this).attr("attr_val"));
			}
		});
		$(".callcenterCls").each(function(){
			if($(this).is(":checked"))
			{
				callCenterGlobalArr.push($(this).attr("attr_val"));
			}
		});
		
	   $(".socialMediaCls").each(function(){
		if($(this).is(":checked"))
		{
			globalsocialMediaTypeIdsArr.push($(this).attr("attr_val"));
		}
	   });
	    $(".mondayGrievanceCls").each(function(){
		if($(this).is(":checked"))
		{
			globalMondayGrievanceTypeIdsArr.push($(this).attr("attr_val"));
		}
	   });
	    $(".janmabhoomiCls").each(function(){
		if($(this).is(":checked"))
		{
			globalJanmabhoomiTypeIdsArr.push($(this).attr("attr_val"));
		}
	   });
	    $(".specialGrievanceCls").each(function(){
		if($(this).is(":checked"))
		{
			globalSpecialGrievanceTypeIdsArr.push($(this).attr("attr_val"));
		}
	   });
	    $(".generalGrievanceCls").each(function(){
		if($(this).is(":checked"))
		{
			globalGeneralGrievanceTypeIdsArr.push($(this).attr("attr_val"));
		}
	   });
		var newsPaperIdLen = newspapersGlobalArr.length;
		var channelIdLen = channelGlobalArr.length;
		var callCenterIdLen = callCenterGlobalArr.length;
		var mediaTypeLenLen = globalsocialMediaTypeIdsArr.length;
		var mondayGrievanceTypeLen = globalMondayGrievanceTypeIdsArr.length;
		var janmabhoomiTypeLen = globalJanmabhoomiTypeIdsArr.length;
		var specialGrievanceTypeLen = globalSpecialGrievanceTypeIdsArr.length;
		var generalGrievanceTypeLen = globalGeneralGrievanceTypeIdsArr.length;
		
		if(newsPaperIdLen == 0 && channelIdLen == 0 && callCenterIdLen == 0 && mediaTypeLenLen==0 && mondayGrievanceTypeLen == 0 && janmabhoomiTypeLen == 0 && specialGrievanceTypeLen == 0 && generalGrievanceTypeLen == 0){
			alert("Please Select Atleast One Media Type.");   
			return;
		}
		var departmentIdLen = globalDepartmentIdsArr.length;
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
	$(document).on("click",".selectAllMediaType",function(){
		if($(this).prop('checked')) {
			$(".socialMediaCls").prop('checked', true);
		}else{
			$(".socialMediaCls").prop('checked', false);
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
	$(document).on("click",".selectAllMondayGrievanceType",function(){
		if($(this).prop('checked')) {
			$(".mondayGrievanceCls").prop('checked', true);
		}else{
			$(".mondayGrievanceCls").prop('checked', false);
		}
	});
	$(document).on("click",".selectAllJanmabhoomiType",function(){
		if($(this).prop('checked')) {
			$(".janmabhoomiCls").prop('checked', true);
		}else{
			$(".janmabhoomiCls").prop('checked', false);
		}
	});
	$(document).on("click",".selectAllSpecialGrievanceType",function(){
		if($(this).prop('checked')) {
			$(".specialGrievanceCls").prop('checked', true);
		}else{
			$(".specialGrievanceCls").prop('checked', false);
		}
	});
	$(document).on("click",".selectAllGeneralGrievanceType",function(){
		if($(this).prop('checked')) {
			$(".generalGrievanceCls").prop('checked', true);
		}else{
			$(".generalGrievanceCls").prop('checked', false);
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
		singleAndMultipleStatusIds=[];
		var stringIds = statusId,
			strx   = stringIds.split(',');
		singleAndMultipleStatusIds = singleAndMultipleStatusIds.concat(strx);
		getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,'','','alertSource',singleAndMultipleStatusIds);
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
		getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,statusId,'','locationLevel','');
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
		getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,'','','','');
	});
	
	$(document).on("click",".getTotalAlertBylocationLvlThenDept",function(){
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		$(".hiddenDiv").hide();
		$("#subDepartmentsBlockDiv"+statusId).toggle();
		var childDepts="";
		if(globalDepartmentObj != null && globalDepartmentObj.length > 0){
			for(var i in globalDepartmentObj){
				if(globalDepartmentObj[i].id==statusId){
					childDepts = globalDepartmentObj[i].subList1;
				}
			} //departmentId - > statusId
			  //statusId - > deptId
		}
		if(childDepts != null && childDepts.length > 0){
			/* $("#subLevelDeptDivId").html(spinner);
			$("#childDeptSummaryModalId").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
			}); */
			buildSubLevelDepartment(childDepts,departmentId,"department",statusId);
		}else{
			$("#totalAlertsModalTabId").html(spinner);
			$("#alertManagementPopup").modal({
				show: true,
				keyboard: false,
				backdrop: 'static'
			});
			getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount,departmentId);
			if(departmentId == 0){
				getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,departmentId,statusId,'');
			}else{
				getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,departmentId,statusId,'locationLevel');
			}
			
		}	
	});
	$(document).on("click",".getTotalAlertByStatusThenDept",function(){
		var childDeptValue = $(this).attr("attr_child_dept_value");
		
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		$(".hiddenDiv").hide();
		$("#subDepartmentsBlockDiv"+statusId).toggle();
		var childDepts="";
		if(globalDepartmentObj != null && globalDepartmentObj.length > 0){
			for(var i in globalDepartmentObj){
				if(globalDepartmentObj[i].id==statusId){
					childDepts = globalDepartmentObj[i].subList1;
				}
			} //departmentId - > statusId	
			  //statusId - > deptId
		}
		if(childDepts != null && childDepts.length > 0){
			/* $("#subLevelDeptDivId").html(spinner);
			$("#childDeptSummaryModalId").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
			}); */
			buildSubLevelDepartment(childDepts,departmentId,"status",statusId);
		}else{
			$("#totalAlertsModalTabId").html(spinner);
			$("#alertManagementPopup").modal({
				show: true,
				keyboard: false,
				backdrop: 'static'
			});
		 getTotalAlertByStatusThenDept(statusId,statusName,statuscount,departmentId);
		 if(departmentId == 0){
			  getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,departmentId,statusId,'');
		 }else{
			  getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,departmentId,statusId,'mainAlert');
		 }
		}
	});
	
	
	$(document).on("click",".getTotalAlertByStatusWisechildType",function(){
		
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		
			$("#totalAlertsModalTabId").html(spinner);
			$("#alertManagementPopup").modal({
				show: true,
				keyboard: false,
				backdrop: 'static'
			});
		 getTotalAlertByStatusThenDept(statusId,statusName,statuscount,departmentId);
		 singleAndMultipleStatusIds=[];
			var stringIds = departmentId,
			strx   = stringIds.split(',');
			singleAndMultipleStatusIds = singleAndMultipleStatusIds.concat(strx);
		 if(departmentId == 0){
			  getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,'',statusId,'','');
		 }else{
			  getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,'',statusId,'alertSource',singleAndMultipleStatusIds);
		 }
		
		
	});
	
	$(document).on("click",".getTotalAlertByLevelWisechildType",function(){
		
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
			$("#totalAlertsModalTabId").html(spinner);
			$("#alertManagementPopup").modal({
				show: true,
				keyboard: false,
				backdrop: 'static'
			});
			getTotalAlertBylocationLvlThenDept(statusId,statusName,statuscount,departmentId);
			if(departmentId == 0){
				getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,departmentId,statusId,'');
			}else{
				getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,departmentId,statusId,'locationLevel');
			}
		
	});
	
}
function buildSubLevelDepartment(deptObj,departmentId,type,statusId){
	$("#subDepartmentsBlockDiv"+statusId).html('');
	var str = '';
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<ul style="list-style:none;" class="textAlignDepartment">';
			for(var i in deptObj){
				if(deptObj[i].alertCnt > 0){
						if(type == "status"){
							str+='<li style="cursor:pointer;" class="getTotalAlertByStatusWisechildType" attr_status_id="'+deptObj[i].id+'"  attr_department_id="'+departmentId+'"  attr_status_name="'+deptObj[i].name+'" attr_status_count="'+deptObj[i].alertCnt+'"><span  data-toggle="tooltip" data-placement="top" title="'+deptObj[i].name+'">'+deptObj[i].name.substring(0,40)+'...</span>'; 
							str+='<span style="cursor:pointer;color:#ee935d;" class="pull-right" >'+deptObj[i].alertCnt+'</span></li>';
						}else if(type == "department"){
							str+='<li style="cursor:pointer;" class="getTotalAlertByLevelWisechildType" attr_status_id="'+deptObj[i].id+'"  attr_department_id="'+departmentId+'"  attr_status_name="'+deptObj[i].name+'" attr_status_count="'+deptObj[i].alertCnt+'"><span  data-toggle="tooltip" data-placement="top" title="'+deptObj[i].name+'">'+deptObj[i].name.substring(0,40)+'...</span>'; 
							str+='<span style="cursor:pointer;color:#ee935d;" class="pull-right" >'+deptObj[i].alertCnt+'</span></li>';
						}
						
				}
							
			}
			str+='</ul>';
		str+='</div>';
	str+='</div>';	
	$("#subDepartmentsBlockDiv"+statusId).html(str);
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
	getFinancialAssistanceAlertCntCategoryWise();	
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
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr : channelGlobalArr,
	  callCenterArr : callCenterGlobalArr,
	  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
	  mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
	  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	  specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	  generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
				str+='<table class="table tableGraph" id="dataTableTotalAlert">';
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
	$("#dataTableTotalAlert").dataTable({
		"paging":   false,
        "info":     false,
		"sDom":   '<"top"i>rt<"bottom"lp><"clear"f>'
	});
	$("#statusOverViewTotal").html("<h4 style='font-size: 16px;'><span class='totalAlertCls' attr_result_type='statusWise' style='cursor:pointer;' attr_total_alert_count='"+totalAlert+"'>TOTAL -  "+totalAlert+"</span></h4>");
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,
		callCenterArr : callCenterGlobalArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
				str+='<table class="table tableGraph" id="dataTableLevelWise">';
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
	$("#dataTableLevelWise").dataTable({
		"paging":   false,
        "info":     false,
		"sDom": 	'<"top"i>rt<"bottom"lp><"clear"f>'
	});
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
	globalDepartmentObj=""; 
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,
		callCenterArr : callCenterGlobalArr,
		alertStatusIdArr:alertStatusIdArr,
		deptScopeLevelIdArr:deptScopeLevelIdArr,
		resultType:type,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
    }
    $.ajax({
		type:'GET',
		url: 'getDepartmentWiseAlertOverviewCntAction.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		 globalDepartmentObj = result;
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
									str+='<li class="getTotalAlertByStatusThenDept" attr_department_id="'+id+'" attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" style="cursor:pointer;"><span  data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,40)+'...</span> <span  class="pull-right">'+result[i].alertCnt+'</span><div id="subDepartmentsBlockDiv'+result[i].id+'" style="display:none;" class="hiddenDiv"></div></li>';  
								}else if(type == 'department'){
									str+='<li class="getTotalAlertBylocationLvlThenDept" attr_department_id="'+id+'" attr_status_id="'+result[i].id+'"   attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" style="cursor:pointer;" ><span data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,40)+'...</span> <span  class="pull-right ">'+result[i].alertCnt+'</span><div id="subDepartmentsBlockDiv'+result[i].id+'" style="display:none;" class="hiddenDiv"></div></li>';  
								}
								
							}else{
								if(type== 'status')
								{
									str+='<li class="getTotalAlertByStatusThenDept" attr_department_id="'+id+'"  attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" style="cursor:pointer;">'+result[i].name+' <span  class="pull-right" >'+result[i].alertCnt+'</span><div id="subDepartmentsBlockDiv'+result[i].id+'" style="display:none;" class="hiddenDiv"></div></li>';
								}else if(type == 'department'){
									str+='<li class="getTotalAlertBylocationLvlThenDept" attr_department_id="'+id+'" attr_status_id="'+result[i].id+'"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" style="cursor:pointer;">'+result[i].name+' <span  class="pull-right" >'+result[i].alertCnt+'</span><div id="subDepartmentsBlockDiv'+result[i].id+'" style="display:none;" class="hiddenDiv"></div></li>';
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr, 
		callCenterArr : callCenterGlobalArr,		
		statusId : statusId ,
		impactLevelArr :impactLevelArr,
        priorityArr :priorityArr,
        alertSourceArr:alertSourceArr,
        printMediaArr :printMediaArr,
        electronicMediaArr:electronicMediaArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,
		callCenterArr : callCenterGlobalArr,		
		statusId : 0,
		govtDeptScopeId : statusId,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr, 
		callCenterArr : callCenterGlobalArr,	
		statusId : departmentId,
		deptId : statusId,
        socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,
		callCenterArr : callCenterGlobalArr,		
		statusId : 0,
		govtDeptScopeId : departmentId,    
		deptId : statusId,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
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
 var jsObj = {
	  deptIdArr : globalDepartmentIdsArr  
 }
  $.ajax({
      type:'GET',
      url: 'getDeptListForMultiLvlAction.action',
    data: {task :JSON.stringify(jsObj)}
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
										str+='<div class="col-sm-4">';
											str+='<h4>'+result[i].subList1[j].name+'</h4>';
										str+='</div>';
										
										str+='<div class="col-sm-8">';
											str+='<ul class="switch_btn_multiLevel locationAndStatusWiseInterchange pull-right">';
												str+='<li  class="statusChangeCls'+result[i].id+''+result[i].subList1[j].id+'" attr_type="scopeWise"  attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">location</li>';
												str+='<li class="active addActiveCls statusChangeCls'+result[i].id+''+result[i].subList1[j].id+'" attr_type="statusWise" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">status</li>';
												str+='<li class="statusChangeCls'+result[i].id+''+result[i].subList1[j].id+'" attr_type="officerOverview" attr_department_id="'+result[i].id+'" attr_parent_id="'+result[i].subList1[j].id+'"  attr_sublevel_id="'+subLevelIdStr+'" attr_district_level_id = "'+districtLevelId+'" attr_child_id = "'+childLevelIdsStr+'">OfficerView</li>';
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
						
					  str+='</div>';
					 
						str+='</div>';	
					str+='</div>';
				}
			str+='</div>';
		str+='</div>';
		$("#levelWiseDepartmentDetailsId").html(str);
		var deptObj = result[0];
		var locationLevelId =0;
		 if(deptObj.subList1 != null && deptObj.subList1.length > 0){
			 for(var i in deptObj.subList1){
				 var searchType = '';
					$('.statusChangeCls'+deptObj.id+deptObj.subList1[i].id).each(function(i, obj){
						 if($(this).hasClass('active')){
						  searchType = $(this).attr("attr_type");
						 }
					});
				if(searchType == "officerOverview"){
					getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(deptObj.id,deptObj.subList1[i].id,searchType,"levelWiseGraphView","count","desc",0,0,"Default","Other",locationLevelId);
					
				}else{
					getStateThenGovtDeptScopeWiseAlertCount(deptObj.id,deptObj.subList1[i].id,"statusWise","levelWiseGraphView","count","desc",0,0,"Default","Other",locationLevelId);
				}	
				
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
		
			
		var locationLevelId =0;
		for(var i in levelIdsStr){
			$('.statusChangeCls'+departmentId+levelIdsStr[i]).each(function(i, obj){
				 if($(this).hasClass('active')){
				  searchType = $(this).attr("attr_type");
				 }
			});
			$("#locationLevelNamesId"+departmentId+levelIdsStr[i]).chosen();
			if(searchType == "officerOverview"){
				getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(departmentId,levelIdsStr[i],searchType,"levelWiseGraphView","count","desc",0,0,"Default","Other",locationLevelId);
				
			}else{
				getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelIdsStr[i],"statusWise","levelWiseGraphView","count","desc",0,0,"Default","Other",locationLevelId);
			}	
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
		if(searchType == "statusWise" || searchType == "alertSource"){
			$(".locationLevelWiseDivCls").show();
		}else{
			$(".locationLevelWiseDivCls").hide();
		}
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+parentId+subLevelIdStr[i]).trigger('chosen:updated');
			
		}
		 $("#locationLevelNamesId"+departmentId+parentId).val(0);
		 $("#locationLevelNamesId"+departmentId+parentId).trigger('chosen:updated')
		var locationLevelId =0;
			
		if(searchType == "officerOverview"){
			getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(departmentId,parentId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Other",locationLevelId);
			
		}else{
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Other",locationLevelId);
		}	
		
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
		 $("#locationLevelNamesId"+departmentId+parentId).val(0);
		 $("#locationLevelNamesId"+departmentId+parentId).trigger('chosen:updated')
		var locationLevelId =0;
		if(searchType == "officerOverview"){
			getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(departmentId,parentId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Sorting",locationLevelId);
			
		}else{
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Default","Sorting",locationLevelId);
		}	
		
		getLocationBasedOnDepartmentLevel(departmentId,parentId,districtLevelId);
		
});

$(document).on("change",".locationLevelWiseOnChange",function(){
		var departmentId = $(this).attr("attr_department_id");
		var levelId = $(this).attr("attr_parent_id");
		var subLevelIdStr = $(this).attr("attr_sublevel_id").split(',');
		var districtLevelId = $(this).attr("attr_district_level_id");
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
		for(var i in subLevelIdStr){
			$("#locationNamesId"+departmentId+levelId+subLevelIdStr[i]).html('');
			$("#locationNamesId"+departmentId+levelId+subLevelIdStr[i]).append('<option value="0">SELECT '+globalLevelObj[subLevelIdStr[i]]+'</option>');
			$("#locationNamesId"+departmentId+levelId+subLevelIdStr[i]).trigger('chosen:updated');
			
		}
			
		var locationLevelId =0;
		locationLevelId = $("#locationLevelNamesId"+departmentId+levelId).val();
		getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,"levelWiseGraphView",sortingType,orderType,0,0,"Change","Sorting",locationLevelId);
		getLocationBasedOnDepartmentLevel(departmentId,levelId,districtLevelId);
});
function getStateThenGovtDeptScopeWiseAlertCount(departmentId,parentGovtDepartmentScopeId,searchType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,selectionType,locationLevelId){
	$("#"+divId+departmentId+parentGovtDepartmentScopeId).html(spinner);
	 if(parentGovtDepartmentScopeId == 1 && selectionType != "Sorting"){
		 orderType = "Default";
	 }
	 var locationLevelIdArr=[];
	 
	 if(locationLevelId == null || locationLevelId == 0){
		 locationLevelIdArr =[];
	 }else{
		 locationLevelIdArr.push(locationLevelId);
	 }
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : globalStateId,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,
		callCenterArr : callCenterGlobalArr,
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
		subLevels:locationLevelIdArr,
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
    url: 'getStateThenGovtDeptScopeWiseAlertCountAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#"+divId+departmentId+parentGovtDepartmentScopeId).html('');
		buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId);
	});
}

function buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId){
	
	
	if(searchType == "statusWise" || searchType == "alertSource" || searchType =="officerOverview"){
		$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).show();
		if(result !=null && result.length>0){
			
			
			if(searchType == "statusWise" || searchType == "officerOverview"){
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
				 
				 
				 var statusOverViewObj={};
					for(var i in result){
						if(result[i].subList != null && result[i].subList.length > 0){
							var inArr = [];
							for(var j in result[i].subList){
								if(result[i].subList[j].count !=null && result[i].subList[j].count>0)
									inArr.push(parseInt(result[i].subList[j].id));
							}
							statusOverViewObj[result[i].name]=inArr;
						}
						
					}
				for(var i in result){
					
					if(searchType == "officerOverview"){
						var LevelName='';
						var officerName=result[i].name;
						var locationName = result[i].location;
						var designation = result[i].designation;
						/* if(officerName == null || officerName == ""){
							LevelName = result[i].location+'/<br/>'+result[i].officerMobileNo
						}else if(locationName == null || locationName == ""){
							LevelName = result[i].name+'/<br/>'+result[i].officerMobileNo
						}else{
							LevelName = result[i].name+'/'+result[i].location+'/<br/>'+result[i].officerMobileNo
						} */
					   if(designation == null || designation == ""){
							LevelName = result[i].location+'/<br/>'+result[i].officerMobileNo;
						}else{
							LevelName = designation+'/'+result[i].location+'/<br/>'+result[i].officerMobileNo;
						}
						statusNamesArr.push(LevelName);
					}else{
						statusNamesArr.push(result[i].name)
					}
					
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
				 var mondayGrievanceArr=[];
				 var janmabhoomiArr=[];
				 var specialGrievanceArr=[];
				 var generalGrievanceArr=[];
					var statusOverViewObj={};
					for(var i in result){
						if(result[i].subList != null && result[i].subList.length > 0){
							var inArr = [];
							for(var j in result[i].subList){
								if(result[i].subList[j].count !=null && result[i].subList[j].count>0)
									inArr.push(parseInt(result[i].subList[j].id));
							}
							statusOverViewObj[result[i].name]=inArr;
						}
						
					}
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
										if(parentGovtDepartmentScopeId == 1 && searchType != "officerOverview"){
											locationValue=value[4];
											levelId =value[3]; 
										}else if(parentGovtDepartmentScopeId == 1 && searchType == "officerOverview"){
											locationValue=value[3];
											levelId = 0;
										}else{
											locationValue=value[3];
											levelId = 0;
										}
										var alertCategoryId='';
										if(searchType == "alertSource"){
											alertCategoryId = value[0];
											statusId = 0;
										}else if(searchType == "statusWise" || searchType == "officerOverview"){
											alertCategoryId = 0;
											statusId = value[0];
										}
										var multiStatusId=value[0]; 
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,locationLevelId,multiStatusId)
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
					if(parentGovtDepartmentScopeId == 1 && searchType != "officerOverview"){
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',\'"+result[index].id+"\',0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].stateId+"\',\'"+parentGovtDepartmentScopeId+"\',0,\'"+locationLevelId+"\',\'"+statusOverViewObj[result[index].name]+"\')");	
					}else if(parentGovtDepartmentScopeId == 1 && searchType == "officerOverview"){
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,\'"+locationLevelId+"\',\'"+statusOverViewObj[result[index].name]+"\')");
					}else{
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,\'"+locationLevelId+"\',\'"+statusOverViewObj[result[index].name]+"\')");
					}
				
				});
				
		}else{
			if(actionType=="Default"){
			 // $("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();//Scope Level DivId hiding if data is not available
			   $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			  $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			  //$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","25px"); 
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
				 var wardArr = [];
				 var gmcArr = [];
				 var clusterArr = [];
				 
				  var locationOverViewObj={};
					for(var i in result){
						if(result[i].subList != null && result[i].subList.length > 0){
							var inArr = [];
							for(var j in result[i].subList){
								if(result[i].subList[j].count !=null && result[i].subList[j].count>0)
									inArr.push(parseInt(result[i].subList[j].id));
							}
							locationOverViewObj[result[i].name]=inArr;
						}
						
					}
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
										var multilevelId =value[0]; 
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,0,multilevelId)
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
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',1,\'"+parentGovtDepartmentScopeId+"\',0,0,0)");	
					}else{
						$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',0,0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',\'"+result[index].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,0,\'"+locationOverViewObj[result[index].name]+"\')");
					}
					
				});
		}else{
			if(actionType=="Default"){
			  //$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();//Scope Level DivId hiding if data is not available
			  $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			   $(".scollerDiv"+departmentId+parentGovtDepartmentScopeId).removeAttr('style');
			  //$("#"+divId+departmentId+parentGovtDepartmentScopeId).css("height","25px"); 
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
										var multilevelId=value[0];
										getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,0,multilevelId)
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
				$(this).attr("onclick","getAlertDetailsBasedOnLocation(\'"+departmentId+"\',\'"+result[0].subList[index].id+"\',0,\'"+result[0].subList[index].name+"\',\'"+result[0].subList[index].count+"\',\'"+result[0].id+"\',\'"+parentGovtDepartmentScopeId+"\',0,0,\'"+result[0].subList[index].id+"\')");
			});
		}else{
			//$("#departmentWiseBlocks"+departmentId+parentGovtDepartmentScopeId).hide();
			 $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('<div class="col-md-12 col-xs-12 col-sm-12">No Data Available</div>')
			$("#"+divId+departmentId+parentGovtDepartmentScopeId).css('height',"25px;")
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
		var childLevelId=0;
		 if(districtLevelId != parentScopeId){
			 childLevelId = parentScopeId;
		 }
		 var reportType='';
			if(searchType == "officerOverview"){
				reportType = 'officerWiseAlert';
			}else{
				reportType = 'deptSubLevelWiseAlert';
			}
		var subLevelArr =[];
		 var jsObj ={
		  fromDate:currentFromDate,
		  toDate:currentToDate,
		  stateId : globalStateId,
		  paperIdArr : newspapersGlobalArr,
		  chanelIdArr : channelGlobalArr,
		  callCenterArr : callCenterGlobalArr,      
		  govtDepartmentId : departmentId,
		  parentGovtDepartmentScopeId : districtLevelId,
		  alertType:"alert",
		  subLevelArr:subLevelArr,
		  socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		  alertSeverityIdsArr:globalAlertSeverityIdsArr,
		  alertStatusIdsArr:globalAlertStatusIdsArr,
          alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
		  mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		  janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	      specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	      generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr,
	      reportType :reportType, //officerWiseAlert
	      childLevelId:childLevelId
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
		 $("#locationLevelNamesId"+departmentId+levelId).val(0);
		 $("#locationLevelNamesId"+departmentId+levelId).trigger('chosen:updated')
		 
		
		if(childLevelId > 0){
		 getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue);	
		}
		if(searchType == "officerOverview"){
			getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(departmentId,levelId,searchType,"levelWiseGraphView",sortingType,orderType,subLevelId,locationValue,"Change","Sorting",0);
		}else{
			getStateThenGovtDeptScopeWiseAlertCount(departmentId,levelId,searchType,"levelWiseGraphView",sortingType,orderType,subLevelId,locationValue,"Change","Sorting",0);
		}
		
		
		
		
	}); 	
	
	function getChildLocationBasedOnParentLocation(departmentId,levelId,subLevelId,childLevelId,locationValue){
	$("#locationNamesId"+departmentId+levelId+childLevelId).html('');
	var searchType = '';
		$('.statusChangeCls'+departmentId+levelId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		
	var reportType='';
		if(searchType == "officerOverview"){
			reportType = 'officerWiseAlert';
		}else{
			reportType = 'deptSubLevelWiseAlert';
		}
		
	 	var jsObj ={
			fromDate:currentFromDate,
			toDate:currentToDate,
			stateId : globalStateId,
			paperIdArr : newspapersGlobalArr,
			chanelIdArr : channelGlobalArr,
			callCenterArr : callCenterGlobalArr,			
			govtDepartmentId : departmentId,
			parentGovtDepartmentScopeId : subLevelId,
			parentGovtDepartmentScopeValue:locationValue,
			childLevelId:childLevelId,
			alertType:"alert",
			socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
			alertSeverityIdsArr:globalAlertSeverityIdsArr,
            alertStatusIdsArr:globalAlertStatusIdsArr,
            alertSubTaskStatusIdsArr:globalAlertSubTaskStatusIdsArr,
			mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		    janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
	        specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
	        generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr,
	        reportType :reportType, // officerWiseAlert
			resultLevelDeptScopeId:levelId
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

	
function getAlertDetailsBasedOnLocation(departmentId,levelId,statusId,statusName,totalCount,locationValue,parentGovtDepartmentScopeId,alertCategoryId,locationLevelId,multipleIds){
	
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
		var searchType = '';
		$('.statusChangeCls'+departmentId+parentGovtDepartmentScopeId).each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		var locationValId=0;
		var govtOfficerId=0;
		
		if(searchType == "officerOverview"){
			govtOfficerId = locationValue;
		}else{
			locationValId =locationValue;
		}
		singleAndMultipleStatusIds=[];
		var stringIds = multipleIds,
			strx   = stringIds.split(',');
		singleAndMultipleStatusIds = singleAndMultipleStatusIds.concat(strx);
		var locationChangeId='';
			var value = $(".locationLevelWiseOnChange").val();
			if(value == 0){
				locationChangeId = parentGovtDepartmentScopeId;
			}else{
				locationChangeId = $(".locationLevelWiseOnChange").val();
			}
			
			
			if(searchType == "statusWise" || searchType == "officerOverview"){
				if(parentGovtDepartmentScopeId == 1){
					getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,levelId,departmentId,'alertSource',singleAndMultipleStatusIds);
				}else{
					getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,locationChangeId,departmentId,'alertSource',singleAndMultipleStatusIds);
				}
			}else if(searchType == "scopeWise"){
				if(parentGovtDepartmentScopeId == 1){
					getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,levelId,departmentId,'locationLevel','');
				}else{
					getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,parentGovtDepartmentScopeId,departmentId,'locationLevel','');
				}
			
			}else if(searchType == "alertSource"){
				if(parentGovtDepartmentScopeId == 1){
					getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,levelId,departmentId,'alertCategory',singleAndMultipleStatusIds);
				}else{
					getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,locationChangeId,departmentId,'alertCategory',singleAndMultipleStatusIds);
				}
			
			}
		
		var locationLevelIdClickArr=[];
		if(locationLevelId == null || locationLevelId == 0){
			locationLevelIdClickArr =[];
		}else{
			locationLevelIdClickArr.push(locationLevelId);
		}
		var statusIdsArr = [];
		 if(statusId != null && statusId == 0){
			  statusIdsArr = globalAlertStatusIdsArr;
		 }else{
			  statusIdsArr.push(statusId);
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
		locationValue : locationValId,
		alertType:"alert",
		alertCategoryId:alertCategoryId,
		subLevels:locationLevelIdClickArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		alertSeverityIdsArr:globalAlertSeverityIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr,
		govtOfficerId:govtOfficerId
		
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
		deptIdArr : globalDepartmentIdsArr,  
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,
		callCenterArr : callCenterGlobalArr,
		userType :"admin",
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
	   }
    });
}

function buildAlertSouceWiseDetails(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-2 col-xs-12 col-sm-4">';
			str+='<div id="alertSourceWiseGraphView"></div>';
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
								str+='<td style="cursor:pointer;" onclick="getAlertDtlsByAlertSource(\''+result[i].name+'\','+result[i].alertCnt+','+result[i].id+',0,\'alertCategory\',0);" class="alertSourceCls" attr_alert_source_name="'+result[i].name+'" attr_alert_count="'+result[i].alertCnt+'" attr_source_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
								
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
	str2+='<h4 style="cursor:pointer;" class="text-center alertSourceCls" onclick="getAlertDtlsByAlertSource(\''+statusName+'\','+totalAlert+',0,0,\'alertSource\',0);" attr_alert_source_name="Total" attr_alert_count='+totalAlert+' attr_source_id="0">TOTAL '+totalAlert+'</h4>';
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
				
				var obj={};
				for(var i in result){
					if(result[i].subList2 != null && result[i].subList2.length > 0){
						var inArr = [];
						for(var j in result[i].subList2){
							if(result[i].subList2[j].alertCnt !=null && result[i].subList2[j].alertCnt>0)
								inArr.push(parseInt(result[i].subList2[j].id));
						}
						obj[result[i].name]=inArr;
					}
					
				}
				
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
										getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,statusId,alertSourceType,0);
										
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
						$(this).attr("onclick","getAlertDtlsByAlertSource(\'"+result[index].name+"\',\'"+result[index].alertCnt+"\',\'"+result[index].id+"\',0,\'alertSource\',\'"+obj[result[index].name]+"\')");
					
				
				});
		}
		
}
function getAlertDtlsByAlertSource(statusName,totalCount,alertCategoryId,alertStatusId,alertSourceType,statusIdsMulti)
{
	$("#alertManagementPopupBody").html('')
	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	$("#alertManagementPopupBody").html(spinner);
	/*Filter Block calls Satrt*/
	if(statusIdsMulti != 0){
		var stringIds = statusIdsMulti,
			strx   = stringIds.split(',');
		singleAndMultipleStatusIds = singleAndMultipleStatusIds.concat(strx);
		getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,alertCategoryId,'',alertSourceType,singleAndMultipleStatusIds);
	}else{
		if(statusName == "Total"){
			getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','','','');
		}else{
			if(alertStatusId !=0){
				singleAndMultipleStatusIds=[];
				var stringIds = alertStatusId,
					strx   = stringIds.split(',');
				singleAndMultipleStatusIds = singleAndMultipleStatusIds.concat(strx);
				
				getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,alertCategoryId,'',alertSourceType,singleAndMultipleStatusIds);
			}else{

				singleAndMultipleStatusIds=[];
				singleAndMultipleStatusIds.push(alertCategoryId)
				getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','',alertSourceType,singleAndMultipleStatusIds);
			}
			
		}
	}
	/*Filter Block calls End*/
	 var statusIdsArr = [];
	  if(alertStatusId != null && alertStatusId == 0){
		  statusIdsArr = globalAlertStatusIdsArr;
	  }else{
		  statusIdsArr.push(alertStatusId);
	  }
	
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : globalStateId,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : newspapersGlobalArr,
      chanelIdArr : channelGlobalArr,
	  callCenterArr : callCenterGlobalArr,
	  alertCategoryId:alertCategoryId,
	  userType :"admin",
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
/* Financial Assistance Block Start */

function getFinancialAssistanceAlertCntCategoryWise()
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
		   getDepartmentWiseProposalAlertCnt();
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
		str+='<div class="col-sm-4">';
			str+='<div class="row">';
				str+='<div class="col-sm-6">';
					str+='<div id="finanicialAssGraphView" style="height:290px"></div>';
				str+='</div>';
			
				 str+='<div class="col-sm-6" style="margin-top:30px">';
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
										str+='<td style="cursor:pointer;" onclick="getFinancialAssistanceAlertCntDtls(\''+result[i].name+'\','+result[i].alertCnt+','+result[i].id+',0,0);" class="alertSourceCls" attr_alert_source_name="'+result[i].name+'" attr_alert_count="'+result[i].alertCnt+'" attr_source_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
									str+='</tr>';
								}
							str+='</tbody>';  
						str+='</table>';
					str+='</div>';
				str+='</div>'; 
				str+='<div class="col-sm-12 m_top10">';
					str+='<table class="table table-bordered" style="background-color:#ccc;">';
						str+='<tr>';
							str+='<td rowspan="2" style="vertical-align:middle"><span>TOTAL PROPOSAL</span> - <span onclick="getFinancialAssistanceAlertCntDtls(\'PROPOSAL\','+totalAlert+',0,0,0);" style="cursor:pointer;">'+totalAlert+'</span></td>';
							str+='<td><span> FAR</span> - <i class="fa fa-inr" aria-hidden="true"></i>&nbsp'+totalfar+'/-</span></td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td><span> APPROVED</span> - <i class="fa fa-inr" aria-hidden="true"></i>&nbsp'+approvedAmount+'/-</span></td>';
						str+='</tr>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-md-8 col-xs-12 col-sm-4" style="box-shadow: -3px 0 3px 3px rgba(0, 0, 0, 0.4);">';
		str+='<div class="scollerDivFinancialAss">';
			str+='<div id="financialAssbarGraphView" ></div>';
		str+='</div>';
		str+='</div>';
		str+='<div id="proposalDepartmentsDivId" class="m_top10"></div>';
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
										
										getFinancialAssistanceAlertCntDtls(statusName,totalCount,categoryId,statusId,0);
										
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
						$(this).attr("onclick","getFinancialAssistanceAlertCntDtls(\'"+result[index].name+"\',\'"+result[index].alertCnt+"\',\'"+result[index].id+"\',0,0)");
					
				
				});
		}
}
function getFinancialAssistanceAlertCntDtls(statusName,totalCount,categoryId,statusId,departmentId)
{
	 
		$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		getFilterSectionAlertDetails(statusName,totalCount,globalDepartmentIdsArr,'','','','');
   
	var deptIds=[];
	  if(departmentId == 0){
		  deptIds = globalDepartmentIdsArr;
	  }else{
		   deptIds.push(departmentId)
	  }
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : deptIds,  
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
function getDepartmentWiseProposalAlertCnt()
{
	 
	$("#proposalDepartmentsDivId").html(spinner);
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
      url: 'getDepartmentWiseProposalAlertCntAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		 $("#proposalDepartmentsDivId").html('');
		if(result !=null && result.length>1){
			buildDepartmentWiseProposalAlertCnt(result);
		}else{
			//$("#proposalDepartmentsDivId").html("NO Data Available");
		}
		
    });
}

	function buildDepartmentWiseProposalAlertCnt(result){
		
		var str='';
			if(result !=null && result.length>0){
				str+='<div class="col-sm-12" style="position:relative;">';
					str+='<div style="border-top:1px dashed #333;margin-top:20px;">';
						str+='<h4 style="text-align: center; position: relative; top: -11px;"><span  class="text-capital" style="background-color:#fff;padding:0px 10px">Department Wise</span></h4>';
					str+='</div>';
					
						str+='<div class="row">';
							str+='<ul class="list-inline slickApplyFinal">';
							for(var i in result){
								str+='<li class="col-sm-4">';
									str+='<div class="panel panel-default">';
										str+='<div class="panel-body">';
											str+='<p>'+result[i].name+'</p>';
											str+='<div style="height:270px;" id="proposalDepartmentGraphWiseDiv'+result[i].id+'"></div>';
											
												str+='<table class="table table-bordered m_top20" style="background-color:#ccc;">';
													str+='<tr>';
														str+='<td rowspan="2" style="vertical-align:middle"><span>TOTAL PROPOSAL</span> - <span onclick="getFinancialAssistanceAlertCntDtls(\'PROPOSAL\','+result[i].alertCnt+',0,0,'+result[i].id+');" style="cursor:pointer;">'+result[i].alertCnt+'</span></td>';
														str+='<td><span> FAR</span> - <i class="fa fa-inr" aria-hidden="true"></i>&nbsp'+result[i].proposalAmount+'/-</span></td>';
													str+='</tr>';
													str+='<tr>';
														str+='<td><span> APPROVED</span> - <i class="fa fa-inr" aria-hidden="true"></i>&nbsp'+result[i].approvedAmount+'/-</span></td>';
													str+='</tr>';
												str+='</table>';
											
										str+='</div>';
									str+='</div>';
								 str+='</li>';
							}
							str+='</ul>';
						str+='</div>';
				str+='</div>';
			}
			$("#proposalDepartmentsDivId").html(str);
			$('.slickApplyFinal').slick({
				   slide: 'li',
				  slidesToShow: 3,
				  slidesToScroll: 1,
				  infinite: false,
				  swipe:false,
				 touchMove:false,
				  variableWidth: false
				});
			if(result !=null && result.length>0){
				
				var financialNamesArr=[];
				for(var i in result){
					var proposalPendingArr=[];
					var proposalRejectedArr = [];
					var proposalAcceptArr = [];
					if(result[i].subList2 !=null && result[i].subList2.length>0){
						for(var j in result[i].subList2){
							financialNamesArr.push(result[i].subList2[j].name);
							if(result[i].subList2[j].subList2 !=null && result[i].subList2[j].subList2.length>0){
								for(var k in result[i].subList2[j].subList2){
									
									if(result[i].subList2[j].subList2[k].id==1){
										proposalPendingArr.push({"y":result[i].subList2[j].subList2[k].alertCnt,"extra":result[i].subList2[j].subList2[k].id+"-"+result[i].subList2[j].subList2[k].name+"-"+result[i].subList2[j].subList2[k].alertCnt+"-"+result[i].subList2[j].id+"-"+result[i].id}); 
									}else if(result[i].subList2[j].subList2[k].id==2){
										 proposalRejectedArr.push({"y":result[i].subList2[j].subList2[k].alertCnt,"extra":result[i].subList2[j].subList2[k].id+"-"+result[i].subList2[j].subList2[k].name+"-"+result[i].subList2[j].subList2[k].alertCnt+"-"+result[i].subList2[j].id+"-"+result[i].id});
									}else if(result[i].subList2[j].subList2[k].id==3){
										 proposalAcceptArr.push({"y":result[i].subList2[j].subList2[k].alertCnt,"extra":result[i].subList2[j].subList2[k].id+"-"+result[i].subList2[j].subList2[k].name+"-"+result[i].subList2[j].subList2[k].alertCnt+"-"+result[i].subList2[j].id+"-"+result[i].id});
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
					}
		
				$("#proposalDepartmentGraphWiseDiv"+result[i].id).highcharts({
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
								categories: financialNamesArr
							},
							yAxis: {
								 min: 0,
								 gridLineWidth: 0,
								 minorGridLineWidth: 0,
								  stackLabels: {
									enabled: true,
									style: {
										fontWeight: 'bold',
										color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
									},
									formatter: function() {
											if (this.total === 0) {
												return null;
											} else {
												return (this.total);
											}
										
									} 
								
								},
									title: {
										text: ''
									},
									labels: {
										enabled: false,
											
										},
							},
							legend: {
								 align: 'center',
								x: -4,
								verticalAlign: 'bottom',
								y: 22,
								floating: false,
								itemDistance: 5,
								shadow: false
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
								column: {
									stacking: 'normal',
									dataLabels: {
										enabled: false,
										color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
									}
								},
								series: {
									cursor: 'pointer',
									point: {
									events: {
											click: function () {
												var value = (this.extra).split("-");
												var statusId = value[0];
												var statusName = value[1];
												var totalCount = value[2];
												var categoryId = value[3]; 
												var departmentId = value[4]; 
												getFinancialAssistanceAlertCntDtls(statusName,totalCount,categoryId,statusId,departmentId);
												
											}
										}
									}
								},
							},
							series: mainFinancialJosnObjArr
				});
				$.each($("#proposalDepartmentGraphWiseDiv"+result[i].id).find(".highcharts-xaxis-labels").find("text"),function(index,item){   
						$(this).attr("style","cursor:pointer;"); 
						$(this).attr("onclick","getFinancialAssistanceAlertCntDtls(\'"+result[i].subList2[index].name+"\',\'"+result[i].subList2[index].alertCnt+"\',\'"+result[i].subList2[index].id+"\',0,\'"+result[i].id+"\')");
						
					
				});
			}
		}
	}
/* Financial Assistance Block End  */
	
/* Officer Wise Alert */
	function getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(departmentId,parentGovtDepartmentScopeId,searchType,divId,sortingType,orderType,filterParentScopeId,filterScopeValue,actionType,selectionType,locationLevelId){
		$("#"+divId+departmentId+parentGovtDepartmentScopeId).html(spinner);
		 if(parentGovtDepartmentScopeId == 1 && selectionType != "Sorting"){
			orderType = "Default";
		}
	 
		 var locationLevelIdArr=[];
		 
		 if(locationLevelId == null || locationLevelId == 0){
			 locationLevelIdArr =[];
		 }else{
			 locationLevelIdArr.push(locationLevelId);
		 }
	    var jsObj ={
			fromDate:currentFromDate,
			toDate:currentToDate,
			stateId : globalStateId,
			paperIdArr : newspapersGlobalArr,
			chanelIdArr : channelGlobalArr,
			callCenterArr : callCenterGlobalArr,
			govtDepartmentId : departmentId,
			parentGovtDepartmentScopeId : parentGovtDepartmentScopeId, 
			sortType : sortingType,
			order : orderType,
			alertType:"alert",
			subLevels:locationLevelIdArr,
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
	    url: 'getOfficerWiseAlertCntBasedOnDepartmentScopeLevelAction.action',
	    data: {task :JSON.stringify(jsObj)}
	    }).done(function(result){
		   $("#"+divId+departmentId+parentGovtDepartmentScopeId).html('');
			buildStateThenGovtDeptScopeWiseAlertCount(result,departmentId,parentGovtDepartmentScopeId,searchType,divId,actionType,locationLevelId);
	    });
	}