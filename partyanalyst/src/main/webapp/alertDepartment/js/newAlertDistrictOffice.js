var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
onLoadCallsAMU();
var globalDepartmentId;
var overAllAlertIds =[];
var totalCoutAlertIds =[];
var globalUserLevelId;
var globalUserLevelValues = [];	
var globalgovtDeptDesigOffcrId;
var	globalgovtOfficerId;
var globalDistrictId;
var globalDivisionId;
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
	
	function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getSearchType(){
		 var searchType = ''; 
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		return searchType;
	}


	function getDistrictWiseSorting(){
		 var districtSortingType = ''; 
		 var districtOrderType = ''; 
		$('.locationWiseSortingDistrict li').each(function(i, obj){
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
		$('.locationWiseSortingDivision li').each(function(i, obj){
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
	
	function onLoadCallsAMU(){
			getDistrictOfficerAlertsCountView();
		
		//status and location wise click start
		$(document).on("click",".switch-btn li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			
			var searchType = $(this).attr("attr_type");
			
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,"alert","desc","count",0);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,"alert","desc","count",0,0);
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,"alert","desc","count",0,0,0);
		});
		$(document).on("click",".switch-btn-alertType li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var alertType = $(this).attr("attr_type");
			var searchType = getSearchType();
			
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,"desc","count",0);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0);
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0,0);
			
		});
		//status and location wise click start
		
		//district wise graph click start
		$(document).on("click",".locationWiseSortingDistrict li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,0);
		});
		
		$(document).on("change",".locationWiseSortingDist",function(){
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
			var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
			var districtId =  $("#DistrictNamesId").val();
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId);
		});
		
		//district wise graph click End
		
		//division wise graph click start
		$(document).on("click",".locationWiseSortingDivision li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,0,0);
		});
		$(document).on("change",".locationWiseDiviDistOnChange",function(){
			var districtId = $(this).val();
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
				
			getDivisionIdListForDivisionFilter(globalDepartmentId,districtId);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,0);
			
		});
		$(document).on("change",".locationWiseDiviOnChange",function(){
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			
			var districtId =  $("#DivisionDistNamesId").val();
			var districtDivisionId =  $("#DivisionNamesId").val();
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId);
		});
		
		//division wise graph click End
		
		//sub-division wise graph click start
		$(document).on("click",".locationWiseSortingSubDivision li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,0,0,0);
		});
		$(document).on("change",".locationWiseSubDiviDistOnChange",function(){
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			var districtId =$("#SubDivisionDistNamesId").val();
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,0,0);
			getDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId);  
		});
		$(document).on("change",".locationWiseSubDiviDiviOnChange",function(){
			
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			getSubDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId,districtDivisionId);  
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,0);
		});
		$(document).on("change",".locationWiseSubDiviOnChange",function(){
		
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var districtSubDivisionId =$("#SubDivisionNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,districtSubDivisionId);
			
		});
		//sub-division wise graph click end
		
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
	
		/*alert Assigned Part End*/
	}
	
	/*alert Assigned Part Start*/
	/*Default Image*/
	function setDefaultImage(img){
		img.src = "images/User.png";
	}
	
	function viewAlertHistory(alertId){
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
		levelValue : globalUserLevelValues[0],
		designationId : 589,
		levelId : globalUserLevelId
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
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageIdDO"/>';
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
	initializeFileDO();
	dateRangePicker(alertId);
	assignedOfficersDetailsForAlert(alertId);
	departmentsByAlert(alertId);
	getAlertData(alertId);
	getStatusCompletionInfo(alertId);
	/* var options = {
	  sourceLanguage:
		  google.elements.transliteration.LanguageCode.ENGLISH,
	  destinationLanguage:
		  [google.elements.transliteration.LanguageCode.TELUGU],
	  shortcutKey: 'alt+t',
	  transliterationEnabled: true
	};
	var control = new google.elements.transliteration.TransliterationControl(options);
	control.makeTransliteratable(['alertCommentId']); */
}

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

/*alert Assigned Part End*/

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
	var fromDateStr= "";
	var toDateStr= "";
	//var userId=19601;
    var jObj ={
      //userId:userId,
	  startDate:currentFromDate,
	  endDate:currentToDate
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
			buildDistrictOfficerAlertsCountView(result);
		}
		
		getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,"scopeWise","alert","desc","count",0);
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,"scopeWise","alert","desc","count",0,0);
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,"scopeWise","alert","desc","count",0,0,0);
		getDistIdListForDistFilter(globalDepartmentId);
		getDistIdListForDivisionFilter(globalDepartmentId);
		getDistrictIdListForSubDivisionFilter(globalDepartmentId); 
    });
}



function buildDistrictOfficerAlertsCountView(result){
	var str='';
	if(result !=null){
		globalUserLevelId = result.levelId;
		if(result.levelValues != null && result.levelValues.length > 0)
			globalUserLevelValues=result.levelValues;
	}
		if(result !=null && result.list1 !=null && result.list1.length>0){
		str+='<div class="row">';
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0){
				if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0 && esult.list1[0].todayCount>0){
					totalCoutAlertIds.push(result.list1[0].todayAlertIds);
				str+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list1[0].todayCount+'" attr_alert_type="alert">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
				}
			else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list1[0].todayCount+'</span></p>';
			}
			}else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str+='<hr class="m_0"/>';
			if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0){
				overAllAlertIds = [];
				if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0 && result.list1[0].overAllCnt>0){
					overAllAlertIds.push(result.list1[0].overAllAlertIds);
				str+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list1[0].overAllCnt+'" attr_alert_type="alert">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
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
	}
	$("#myAlertsDivID").html(str);
	
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

			
		var str1='';
		if(result !=null && result.list2 !=null && result.list2.length>0){
		str1+='<div class="row">';
			str1+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0){
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0 && result.list2[0].todayCount>0){
				  totalCoutAlertIds.push(result.list2[0].todayAlertIds);
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_alert_type="mySubTasks">TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
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
				str1+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_alert_type="mySubTasks">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }else{
				 str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }
			}else{
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str1+='<div id="mySubTasksGraphView" style="height:250px"></div>';
			str1+='</div>';
		str1+='</div>';
	}
	$("#mySubTasksDivID").html(str1);
	
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
		
			
			var str2='';
		if(result !=null && result.list3 !=null && result.list3.length>0){
		str2+='<div class="row">';
			str2+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0){
				if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0 && esult.list3[0].todayCount>0){
					totalCoutAlertIds.push(result.list3[0].todayAlertIds);
				str2+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_alert_type="myAssignedSubTasks" >TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
				}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str2+='<hr class="m_0"/>';
			if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0){
				overAllAlertIds = [];
				if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0 && result.list3[0].overAllCnt>0){
					overAllAlertIds.push(result.list3[0].overAllAlertIds);
				str2+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_alert_type="myAssignedSubTasks">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str2+='<div id="assignedSubTasksGraphView" style="height:250px"></div>';
			str2+='</div>';
		str2+='</div>';
	}
	$("#assignedSubTasksDivID").html(str2);
	
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
		
			 
}
	
	
	//district Level Filter
	function getDistIdListForDistFilter(globalDepartmentId){
	 $("#DistrictNamesId").html('');
		var searchType = getSearchType();
		var alertType = getAlertType();
		var paperIdArr = [];
		var chanelIdArr = [];

		var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,    
		govtDepartmentId : globalDepartmentId,
		parentGovtDepartmentScopeId : 5,
		group : "status",
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
		}
		$.ajax({
		type:'GET',                  
		url: 'getDistIdListForDistFilterAction.action',
		data: {task :JSON.stringify(jsObj)}     
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#DistrictNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#DistrictNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});    
	}
	
	//division wise district names Filter
	function getDistIdListForDivisionFilter(globalDepartmentId){
	$("#DivisionDistNamesId").html('');
	var searchType = getSearchType();
	var alertType = getAlertType();
	var paperIdArr = [];
	var chanelIdArr = [];
	
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 6,
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDistIdListForDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#DivisionDistNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#DivisionDistNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

	//division wise division names 
	function getDivisionIdListForDivisionFilter(globalDepartmentId,districtId){
	$("#DivisionNamesId").html('');
	
		var searchType = getSearchType();
		var alertType = getAlertType();
		var paperIdArr = [];
		var chanelIdArr = [];

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 6,
	districtWorkLocationId : districtId, //district id here
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDivisionIdListForDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#DivisionNamesId").append('<option value="0">Select Division</option>');
				for(var i in result){
					$("#DivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

	
//sub division wise district names
function getDistrictIdListForSubDivisionFilter(globalDepartmentId){
	$("#SubDivisionDistNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];

  var searchType = getSearchType();
		var alertType = getAlertType();
		
		
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 7,
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
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
function getDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId){
	
	$("#SubDivisionDiviNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];
var searchType = getSearchType();
		var alertType = getAlertType();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 7,
  districtWorkLocationId : districtId,//district id here, default 0
  group : "status",
  alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
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
function getSubDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId,districtDivisionId){
	
	$("#SubDivisionNamesId").html('');
	var paperIdArr = [];
	var chanelIdArr = [];
	var searchType = getSearchType();
		var alertType = getAlertType();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 7,
  districtWorkLocationId :districtId, //district id here ,default 0
  divisionWorkLocationId :districtDivisionId, //division id here ,default 0
  group : "status",
  alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
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
//For District Level
function getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId){
	
	$("#districtLevelSubOrdinarteDetails").html(spinner);
    var paperIdList =[];
    var chanelIdList =[];
    
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:5,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:0,
    subDivisionWorkLocationId:0,
    group:"status",//status only
    alertType:alertType,//alertType=(alert/subTask)
  	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			$("#districtLevelSubOrdinarteDetails").html('')
			buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,searchType)
      });
  
}

function buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,searchType){
	
	if(searchType == "statusWise"){
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
				 var Incomplete = [];
				 var Closed = [];
				
			for(var i in result){
				locationNamesArrDistrict.push(result[i].name)
				globalDistrictId =result[i].id;
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
				$("#districtLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrict
				var id = 'districtLevelSubOrdinarteDetails';
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
										var distrctId = value[3];
										getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,distrctId);
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
				/* $.each($('#districtLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldistrictCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);	
				});  */
		}else{
			$("#districtLevelSubOrdinarteDetails").html('No Data Available');
		}
	}else if(searchType == "scopeWise"){
		
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
				globalDistrictId =result[i].id;
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
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
				$("#districtLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'districtLevelSubOrdinarteDetails';
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
										var districtId = value[3]
										getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,districtId);
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
				/* $.each($('#districtLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldistrictCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);	
				});  */
				
		}else{
			$("#districtLevelSubOrdinarteDetails").html('No Data Available');
		}
		
		
	}
	
}
//For Division Level
function getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId){
	
	$("#divisionLevelSubOrdinarteDetails").html(spinner);
    var paperIdList =[];
    var chanelIdList =[];
    
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:6,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:districtDivisionId,
    subDivisionWorkLocationId:0,
    group:"status",//status only
    alertType:alertType,//alertType=(alert/subTask)
  	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			$("#divisionLevelSubOrdinarteDetails").html('');
			buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,searchType);
      });
  
}


function buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,searchType){
	if(searchType == "statusWise"){
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
				 locationNamesArrDivision.push(result[i].name)
				globalDivisionId = result[i].name;
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
				$("#divisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDivision
				var id = 'divisionLevelSubOrdinarteDetails';
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
										var divisionId =value[3];
										getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId);
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
				$.each($('#divisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);	
				}); 
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}else if(searchType == "scopeWise"){
		
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
				 locationNamesArrDivisionOverView.push(result[i].name)
				globalDivisionId =result[i].id;
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
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
				$("#divisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDivisionOverview
				var id = 'divisionLevelSubOrdinarteDetails';
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
										var divisionId =value[3];
										getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId);
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
				/* $.each($('#divisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotaldivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);	
				});  */
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}
//For Sub Division Level
function getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,districtSubDivisionId){
	
	$("#SubdivisionLevelSubOrdinarteDetails").html(spinner);
	
    var paperIdList =[];
    var chanelIdList =[];
   
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:7,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:districtDivisionId,
    subDivisionWorkLocationId:districtSubDivisionId,
    group:"status",//status only
    alertType:alertType,//alertType=(alert/subTask)
  	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			$("#SubdivisionLevelSubOrdinarteDetails").html('');
			 buildStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(result,searchType)
      });
  
}

function buildStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(result,searchType){
	if(searchType == "statusWise"){
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
				 locationNamesArrsubDivision.push(result[i].name)
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
				$("#SubdivisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrsubDivision
				var id = 'SubdivisionLevelSubOrdinarteDetails';
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
										var subDivisionId = value[3];
										getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId);
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
				/* $.each($('#subdivisionlevelsubordinartedetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","gettotalsubdivisioncls");    
					$(this).attr("attr_district_id",districtid);         
					$(this).attr("attr_district_name",districtname);	
					$(this).attr("attr_total_count",totalcount);	
				});  */
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}else if(searchType == "scopeWise"){
		
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
				 locationNamesArrsubDivisionOverView.push(result[i].name)
				
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
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
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
				$("#SubdivisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrsubDivisionOverview
				var id = 'SubdivisionLevelSubOrdinarteDetails';
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
										var subDivisionId = value[3];
										getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId);
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
				/* $.each($('#SubdivisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					$(this).attr("class","getTotalsubdivisionCls");    
					$(this).attr("attr_district_id",districtId);         
					$(this).attr("attr_district_name",districtName);	
					$(this).attr("attr_total_count",totalCount);	
				});  */
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}

$(document).on("click",".overAllCount",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count");
	var alertType = $(this).attr("attr_alert_type");
	//alertIdArr=($(this).attr("attr_overcunt").split(','));
	var jObj = {
		govtDepDesigOffcrId : globalgovtDeptDesigOffcrId,
		govtOfficerId : globalgovtOfficerId,
		countType: "overAll",
		alertType : alertType		
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
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	//alertIdArr.push(parseInt($(this).attr("attr_todayCunt")));
	var alertType = $(this).attr("attr_alert_type");
	var jObj = {
		govtDepDesigOffcrId : globalgovtDeptDesigOffcrId,
		govtOfficerId : globalgovtOfficerId,
		countType: "today",
		alertType : alertType
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

function getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,districtId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    var paperIdList =[];
    var chanelIdList =[];
	var searchType = getSearchType();
	var alertType = getAlertType();
	var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
	var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
	
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:5,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:0,
    subDivisionWorkLocationId:0,
    group:"status",
    alertType:alertType,
    searchType :searchType,
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
/* $(document).on("click",".getTotaldistrictCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	
	getlevelAndStatusWiseClickForDistrict(0,districtName,totalCount,0,districtId)
}); */

function getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId){
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    var paperIdList =[];
    var chanelIdList =[];
	var searchType = getSearchType();
	var alertType = getAlertType();
	var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
	var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:6,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:globalDistrictId,
    divisionWorkLocationId:divisionId,
    subDivisionWorkLocationId:0,
    group:"status",
    alertType:alertType,
    searchType :searchType,
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
/* $(document).on("click",".getTotaldivisionCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	
	getlevelAndStatusWiseClickForDivision(0,districtName,totalCount,0,distrctId)
}); */

function getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    var paperIdList =[];
    var chanelIdList =[];
	var searchType = getSearchType();
	var alertType = getAlertType();
	var sortingType = getSubDivision().subSortingType; // 'value1'
	var orderType = getSubDivision().subOrderType; // 'value2'
	
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:7,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:globalDistrictId,
    divisionWorkLocationId:0,
    subDivisionWorkLocationId:subDivisionId,
    group:"status",
    alertType:alertType,
    searchType :searchType,
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
/* $(document).on("click",".getTotalsubdivisionCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	
	getlevelAndStatusWiseClickForSubDivision(0,districtName,totalCount,0)
}); */


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
		govtDeptDesigOffceId : globalgovtDeptDesigOffcrId,
		govtOffceId : globalgovtOfficerId,
		statusId : StatusId,
		formDate:currentFromDate, 
		toDate: currentToDate,
		clickType:clickType //
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

