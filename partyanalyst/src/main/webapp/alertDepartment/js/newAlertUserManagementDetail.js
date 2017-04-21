var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3){
wurl = url.substr(0,(url.indexOf(".in")+3));
}

onLoadCalls()
function onLoadCalls()
{
	"use strict";
	onLoadClicks();
}
function onLoadClicks()
{
	"use strict";
	
	
	
	/*alert Assigned Part Start*/
	$(document).on('click', '.imageShowOpen li', function(){
		var id = $(this).attr("attr_doc_id");
		var path = "http://mytdp.com/"+$(this).attr("attr_path");
		window.open(path);
	});
	/*$(document).on("change","#divisionDistWiseLevelsDivId",function(){
		var defaultDepartmentID = $(this).attr("attr_department_id")
			getDivisionIdListForDivisionFilter(defaultDepartmentID);
	});*/
	$(document).on('change', '#locationLevelSelectId', function(){
		
		getParentLevelsOfLevel($(this).attr('id'));
	});
	$(document).on('change','.locationsCls', function(evt, params) {
		designationsBySubTaskDepartment();
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	$(document).on('change', '#departmentsId', function(){
		var deptId = $(this).val();
		getDepartmentLevels(deptId,$(this).attr('id'));
	});
	$(document).on("click",".closeSecondModal",function(){
		setTimeout(function(){
			$("body").addClass("modal-open")
		},1000);
	});
	
	$(document).on("click",".displayImgCls",function(){
		var articleId= $(this).attr("attr_articleId");
		$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var imgSrc = $(this).attr('src');
		$('#alertManagementPopupBody1').html('<img class=" img-responsive m_top20" attr_articleId="" src="http://www.mytdp.com/images/'+imgSrc+'" style="width: 999px; height: 500px;"/>');
		
		$('#alertManagementPopupHeading').html(' Sub Task Uploaded Attachment');
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
	//document
	$(document).on("click","#uploadBtnId",function(){
		
		var alertId = $(this).attr("attr_alert_id");
		var subTaskId = $(this).attr("subalertid");
		var urlStr='uploadDocumentsForAlertAction.action';
		var formName='uploadAttachment';
		
		if(subTaskId != null && subTaskId.length>0){
			urlStr='uploadDocumentsForSubTaskAction.action';
			formName='uploadAttachment1';
		}
		$('#imagesUploadSpinner').show();
		var uploadHandler = { 
			upload: function(o) {
				var uploadResult = o.responseText;
				$('.jFiler-item').html('');
				$('#imagesUploadSpinner').hide();
				showSbmitSubTaskStatusNew(uploadResult,alertId,subTaskId);
				if(formName='uploadAttachment')
					showSbmitStatusNew(uploadResult,alertId);
				else
					showSbmitSubTaskStatusNew(uploadResult,alertId,subTaskId);
			}
		};
		YAHOO.util.Connect.setForm(formName,true);  
		YAHOO.util.Connect.asyncRequest('POST',urlStr,uploadHandler);
		$("#uploacFilesBtnId").attr("disabled","disabled");
	});
	
	function showSbmitSubTaskStatusNew(uploadResult,alertId,subAlertId){
		if(uploadResult !=null && uploadResult.search("success") != -1){
			getSubAlertsDetails(alertId,subAlertId)
		}
	}
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
		
		var uploadHandler = {
			upload: function(o) {
				var uploadResult = o.responseText;
				displayStatus(uploadResult);
			}
		};
		

		YAHOO.util.Connect.setForm('alertAssignForm',true);
		YAHOO.util.Connect.asyncRequest('POST','assigningAlertToOfficerNewAction.action',uploadHandler); 
	});

	$(document).on('change','#designationsId,#designationsId1', function(evt, params) {
		var designationId = $(this).val();
		officersByDesignationAndLevel(designationId,$(this).attr('id'));
	});
	
	
	
	$(document).on("click","#updateStatusChange",function(){
		//$('input[name=statusChange]:checked', '#updateStatusChangeBody').val()
		var comment = $("#updateStatusChangeComment").val()
		var alertId = $(this).attr("attr_alert_id");
		var statusId=$('input[name=statusChange]:checked', '#updateStatusChangeBody').val();
		var subTaskId = $(this).attr("subTaskId");
		
		if(subTaskId != null && subTaskId>0){
			comment = $("#updateStatusChangeComment1").val()
		}
		
		if(comment == null || comment.trim() == "")
		{
			alert("Please Enter Comment");
			return;
		}
		
		if(statusId == null || statusId =='')
			statusId=globalStatusId;
		
			var jsObj ={
				alertId : alertId,
				statusId : statusId,
				subTaskId:subTaskId,
				comment: comment
			}
		
		var callURL = 'updateAlertStatusCommentAction.action';
		if(subTaskId != null && subTaskId>0){
			callURL = 'updateSubTaskStatusCommentAction.action';
		}
		$.ajax({
			type:'POST',
			url: callURL,
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				$("#commentPostingSpinner").html("status updated successfully");
				$('#alertManagementPopup1').modal('hide');
				if(subTaskId == null || subTaskId.length == 0)
					getCommentsForAlert(alertId);
				else
					getSubAlertsDetails(alertId,subTaskId);
				
				setTimeout(function(){
					$("#commentPostingSpinner").html(" ");
				},1000);
			}else{
				alert("try again");
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
		var alertId = $(this).attr("attr_alert_id")
		var subTaskId = $(this).attr("subAlertId")

		var comment = $("#alertCommentId1").val();	
		if(subTaskId != null && subTaskId>0)
			comment = $("#alertCommentId2").val();
		
		if(comment == null || comment.trim() == "")
		{
			alert("Please enter comment. ");    
			return;                 
		}
		var jsObj ={  
			alertId : alertId,
			comment : comment,
			subTaskId:subTaskId,
			statusId:globalStatusId // not updating status here.
		}
		
		var callURL = 'updateAlertStatusCommentAction.action';
		if(subTaskId != null && subTaskId>0)
			callURL = 'updateSubTaskStatusCommentAction.action';
		$.ajax({
			type:'POST',
			url: callURL,
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				$("#commentPostingSpinner").html("status updated successfully");
				$("div.comment-area").show();
				$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea").hide();
				$(".panel-border-white textarea").val('');
				if(subTaskId == null || subTaskId.length == 0)
					getCommentsForAlert(alertId);
				else
					getSubAlertsDetails(alertId,subTaskId);
				
				setTimeout(function(){
					$("#commentPostingSpinner").html(" ");
				},1000);
			}else{
				alert("try again")
			}
		});
			
	});
	
	
	
	
	
	
	
	$(document).on("click",".alert-status-change",function(){
		var alertId = $(this).attr("attr_alert_id");

		if($(this).is(':checked'))
		{
			$(".alert-status-change-body").show();
			//getStatusCompletionInfo(alertId);
		}else{
			$(".alert-status-change-body").hide();
			//$("#updateStatusChangeBody").html(" ");
		}
	}); 
	$(document).on("click","div.comment-area",function(e){
		e.stopPropagation()
		$(this).hide();
		$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea").show();
	});
	$(document).on("click","div.assign-user",function(e){
		e.stopPropagation()
		$(this).find(".assign-user-body1").show();
	});
	
	$(document).on("click",".glyphicon-user",function(e){
		e.stopPropagation()
		$(".assign-user-body1").show();
	});
	
	$(document).on("click",".panel-border-white",function(e){
		e.stopPropagation()
	});
	
	$(document).on("click",function(e){
		e.stopPropagation()
		$("div.comment-area").show();
		$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea,.assign-user-body,.alert-status-change-list,.assign-subtask,.alert-status-attachment").hide();
		$(".panel-border-white textarea").val('');
	});
	$(document).on("click","[basic-switch] li",function(){
		$(this).addClass("active");
	});
	$(document).on("click","[filer-selection]",function(e){
		e.stopPropagation();
		var selectionType = $(this).attr("filer-selection");
		$(this).closest("li").removeClass("active");
		$(this).remove();
	});
	$(document).on("click",".assign-subtask",function(e){
		e.stopPropagation();
	});
	$(document).on("click","[filters-list] li",function(e){
		e.stopPropagation();
		
		var blockName = $(this).closest("ul").attr("filters-list");
		$(this).addClass("active");
		if(!$(this).find("span").hasClass("remove"))
		{
			$(this).append("<span class='remove' filer-selection='true'><i class='glyphicon glyphicon-remove'></i></span>")
		}
		if(blockName != null && blockName != undefined)
		{
			if(!$("[filters-list-title='all'] small").hasClass("clear"))
			{
				$("[filters-list-title='all']").append("<small class='clear' filer-selection-clear='all'>(Clear All)</small>")
			}
			if(!$("[filters-list-title="+blockName+"] small").hasClass("clear"))
			{
				$("[filters-list-title="+blockName+"]").append("<small class='clear' filer-selection-clear="+blockName+">(Clear All)</small>")
			}
			if(blockName == '')
			{
			}
		}
	});
	$(document).on("click",".assign-subtask-btn",function(e){
		e.stopPropagation();
		$(this).closest("li").find(".assign-subtask").show();
	});
	$(document).on("click","[filer-selection-clear]",function(e){
		e.stopPropagation();
		var blockName = $(this).attr("filer-selection-clear");
		if(blockName == 'all')
		{
			$("[filters-list]").find("span.remove").remove();
			$("[filters-list]").find("li").removeClass("active");
			$("[filters-list-title]").find("small").remove();
		}
		$("[filters-list="+blockName+"]").find("span.remove").remove();
		$("[filters-list="+blockName+"]").find("li").removeClass("active");
		$(this).remove();
	});
	$(document).on("click","[status-icon] li",function(e){
        e.stopPropagation();
		var status = $(this).attr("status-icon-block");		
		var alertId = $(this).attr("attr_alert_id");
		var subalertid = $(this).attr("subalertid");
		if(status != null && status != undefined)
		{
			if(status == 'alertHistory')
			{
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopupHeading").html('ALERT HISTORY')
				$("#alertManagementPopup1 .modal-footer").hide();
				viewAlertHistory(alertId);
			}else if(status == 'alertStatus'){
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopupHeading").html('ALERT STATUS HISTORY');
				
				if(subalertid != null && subalertid !='' && subalertid>0){
					getSubTaskStatusHistory(subalertid,alertId);
				}
				else
					getAlertStatusHistory(alertId);
				
			}else if(status == 'alertStatusChange'){
				$(this).find('ul').toggle();
			}else if(status == 'task'){
				statusBody(status);
			}else if(status == 'subTask'){
				statusBody(status);
			}else if(status == 'attachment'){
				$(this).find('.alert-status-attachment').toggle();
			}
		}
	});
	
	
function getSubTaskStatusHistory(subTaskId,alertId){
	var jsObj ={
		subTaskId : subTaskId
	}
	$.ajax({
		type:'GET',
		url: 'getSubTaskStatusHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		alertSubTaskStatusHistory(result,subTaskId,alertId);
	});
}
function alertSubTaskStatusHistory(result,subTaskId,alertId){

	var str='';
	var str1='';
	
	if(result != null && result.length>0)
	{
		str+='<table class="table border_1">';
			str+='<thead class="text-capitalize">';
				str+='<th>Date</th>';
				str+='<th>Status</th>';
				str+='<th>Updated By</th>';
				str+='<th>Comments</th>';
			str+='</thead>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td>'+result[i].date+'</td>';
					str+='<td>'+result[i].status+'</td>';
					str+='<td>';
						str+='<p class="text-primary text-capitalize">'+result[i].userName+'</p>';
						//str+='<p class="text-muted text-capitalize">-<u>'+result[i].designation+'</u></p>';
					str+='</td>';
					str+='<td>'+result[i].comment+'</td>';
				str+='</tr>';
			}  
			
		str+='</table>';
		
		
		
		$("#alertManagementPopup1 .modal-footer").show();
		
		$("#alertManagementPopup1 .modal-dialog").css("width","60%")
		$("#alertManagementPopupBody1").html(str);
	}else{
		$("#alertManagementPopupBody1").html("NO DATA")
	}
	
	if(isAdmin == "false"){
			if(globalUserType != "same"){
				str1+='<div class="text-left" id="changeStatudCheckBoxId">';     
					str1+='<label class="checkbox-inline">';
						str1+='<input type="checkbox" attr_alert_id="'+alertId+'" subTaskId="'+subTaskId+'" class="alert-status-change changeStatsCls" /> I Want to change alert Status';  
					str1+='</label>';  
					str1+='<div  id="updateStatusChangeBody" style="display:none;">'+subTaskglStr+'</div>';
				str1+='</div>';
			}
		}  
		$("#alertManagementPopup1 .modal-footer").html(str1);
}
	$(document).on("click","[expand-icon]",function(){
        var expandBlockName = $(this).attr("expand-icon");
		var alertId = $(this).attr("attr_alertId");
		$("[expand-icon]").closest("li").removeClass("active");
		$("[expand-icon]").removeClass("text-primary");
		$(this).addClass("text-primary");
		$(this).closest("li").addClass("active");
		rightSideExpandView(alertId);
		
		setTimeout(function(){
			$("[expanded-block="+expandBlockName+"]").show().css("transition"," ease-in, width 0.7s ease-in-out");
		},750);
		setTimeout(function(){
			$("#alertManagementPopup").scrollTop(0);
		},780);
		if($("[expand-main]").attr("expand-main") === 'false')
		{	
			$("[expand-main]").attr("expand-main","true");
			$("[expanded-channel]").attr("expanded-channel","true");
			$("[expand-main]").addClass("col-sm-4").removeClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
	});
    $(document).on("click","[expanded-close]",function(){
		var expandBlockName = $(this).attr("expanded-close");
		if($("[expand-main]").attr("expand-main") === 'true')
		{
			$("[expand-main]").attr("expand-main","false");
		}else{
			$("[expand-main]").attr("expand-main","true");
		}
		$("[expanded-block="+expandBlockName+"]").hide();
		$("[expand-main]").removeClass("col-sm-4").addClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
	});
	$(document).on("click",".filters-icon",function(){
		getFilterSectionAlertDetails();
	});
}
/*Default Image*/
function setDefaultImage(img){
    img.src = "images/User.png";
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
			$("#alertManagementPopupBody1").html("NO DATA AVAILABLE...")
		}
	});
}


/*Alert Assigning Part*/



function getDepartmentLevels(deptId,buildId){
	
	var jsObj = {
		departmentId : deptId
	}
	$.ajax({
      type:'GET',
      url: 'getDepartmentLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentLevels(result,buildId);
		}
	});
	
}
function buildDepartmentLevels(result,tempBuildId){
	var buildId='locationLevelSelectId';
	if(tempBuildId =='departmentsId1')
		buildId='locationLevelSelectId1';
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	
	$("#"+buildId+"").html(str);
	$("#"+buildId+"").trigger("chosen:updated");
	
	
}


function getParentLevelsOfLevel(buildId){
	departmentId = 49;
	var jsObj = {
		departmentId : departmentId,
		levelId : $("#"+buildId+"").val()
	}
	$.ajax({
      type:'GET',
      url: 'getParentLevelsOfLevelAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildParentLevelsOfLevel(result,departmentId,buildId);
		}
	});
}
function buildParentLevelsOfLevel(result,departmentId,tempbuildId){

	var buildId="parentLevelDivId";
	if(tempbuildId =='locationLevelSelectId1')
		buildId="parentLevelDivId1";
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
					if(tempbuildId =='locationLevelSelectId1')
						str+='<select  class="chosenSelect locationsCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
					else
						str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
				str+='</div>';
			}
		}
		
	$("#"+buildId+"").html(str);
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



function designationsBySubTaskDepartment()
{
	$("#designationsId1").empty();
	$("#designationsId1").trigger("chosen:updated");
	$("#officerNamesId1").empty();
	$("#officerNamesId1").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId1").chosen().val();
	var deprtmntId = $("#departmentsId1").chosen().val();
	var levelValue = $(".locationsCls").chosen().val();
	
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
		$("#designationsId1").html(str);
		$("#designationsId1").trigger("chosen:updated");
	});
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

function officersByDesignationAndLevel(designationId,tempBuildId)
{
	var buildId = 'officerNamesId';
	var LevelId = $("#locationLevelSelectId").chosen().val()
	var LevelValue = $(".locationCls").chosen().val()
	if(tempBuildId=="designationsId1"){
		buildId = 'officerNamesId1';
		LevelId = $("#locationLevelSelectId1").chosen().val()
		LevelValue = $(".locationsCls").chosen().val()
	}
	
	$("#"+buildId+"").empty();
	$("#"+buildId+"").trigger("chosen:updated");
	
	
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
		$("#"+buildId+"").html(str);
		$("#"+buildId+"").trigger("chosen:updated");
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
//swadhin
var isAdmin = "";
var globalUserType = "";
var globalStatusId = 0;
var isStatusAvailable=true;
function getStatusCompletionInfo(alertId){
	$("#updateStatusChangeBody").html(spinner);
	$("#statusDtlsDiv").html(spinner);
	var jsObj ={
		alertId : alertId,
		levelValue : globalUserLevelValues[0],
		designationId : 0,
		levelId : globalUserLevelId
	}
	$.ajax({
		type:'GET',
		url: 'getStatusCompletionInfoAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#statusDtlsDiv").html('');
		$('#displayStatusId,#displayAssignIconId,#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority,#historyId').hide();
		$('#displayStatusId').attr('status-icon-block','alertStatus');
		
		if(result != null && result.length>0){
			if(result.length == 1)
				isStatusAvailable=false;
			//displaySubTasksliId
			var buildTypeStr = result[0].applicationStatus.split('-')[0].trim();
			globalUserType = buildTypeStr;
			var sttatusId = result[0].applicationStatus.split('-')[1].trim();
			globalStatusId = sttatusId;  
			$('#historyId').show();
		
			if(buildTypeStr=='own'){  
				$('#displayStatusId,#displaySubTaskli,#displaySubTasksliId').show();	
				$('#displayDueDate1').show();
				$('#displayDueDate2').hide();  
			}
			else if(buildTypeStr=='subUser'){	
				$('#displaySubTasksliId').hide();		
				$('#displayDueDate1').hide();
				$('#displayStatusId').show();
				$('#displayStatusId').removeAttr('status-icon-block');
				
				$('#displayDueDate2,#displayPriority').show();
				
			}else if(buildTypeStr=='same'){ 
				$('#displaySubTasksliId').hide();
				$('#displayStatusId').show();       
				$('#displayDueDate1').show();                       
			}
			else if(buildTypeStr=='other'){
				$('#displaySubTasksliId').hide();				
				$('#displayStatusId').show();
				$('#displayDueDate1').show();                       
			}
			if((sttatusId == 1  || sttatusId == 8 || sttatusId==9) && result[0].userStatus != null && result[0].userStatus =='admin'){
				$('#displayAssignIconId').show();
				assignUser(alertId);
			}
			if(result[0].userStatus != null && result[0].userStatus =="admin"){
				isAdmin = "true";
				$('#displayStatusId').attr('status-icon-block','');
				$('#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority').hide(); 
				if(sttatusId !=1)
					$('#docAttachmentId').show(); 
			}else{
				$('#displayStatusId').attr('status-icon-block','alertStatus');
				isAdmin = "false";
			}
			
			if(isAdmin=='false'){
				$('#docAttachmentId').show(); 
				$('#displayStatusId').attr('status-icon-block','alertStatus');
			}
			
			alertStatus(result,alertId);			
		}else{
			$('#displayAssignIconId').show();
			$('#displayStatusId').show();
			$('#displaySubTasksliId').hide();  
			$('#docAttachmentId').hide();  
		}	
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
				
					str+='<div class="panel-heading" id="mainBlockStates">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser"></div>';
							str+='</div>';
							str+='<div class="col-sm-8">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									str+='<li status-icon-block="alertStatus" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon" id="statusIdColor"></span><span id="statusId">Pending</span>';
									str+='</li>';
									
									 str+='<li class="list-icons-down" data-toggle="tooltip" data-placement="top" title="Sub Task "  id="displaySubTasksliId" style="display:none;">';
										str+='<i class="fa fa-level-down" aria-hidden="true" id="displaySubTasksli"></i>';
									str+='</li>';  
									//	$(document).on("click","[status-icon] li",function(e){
									str+='<li id="displayDueDate1"  style="display:none;"  class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="Due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date1">Due date</span>';
									str+='</li>';
									
									str+='<li id="displayDueDate2"  style="display:none;"  class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="Due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date">Due date</span>';
									str+='</li>';
									

									
									 str+='<li id="displayPriority" style="display:none;" status-icon-block="alertStatusChange" data-toggle="tooltip" data-placement="top" title="pririty change">';
										str+='<i class="glyphicon glyphicon-cog"></i>';   
										str+='<ul class="alert-status-change-list arrow_box_top" style="display:none;">';
											str+='<li>high <input type="radio" name="alert-status-change-list" value="1" attr_value="high" class="pull-right priorityRadioCls" /></li>';
											str+='<li>medium <input type="radio" name="alert-status-change-list" attr_value="medium" value="2" class="pull-right priorityRadioCls" /></li>';
											str+='<li>low <input type="radio" name="alert-status-change-list" attr_value="low" value="3" class="pull-right priorityRadioCls" /></li>';
											str+='<li><button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" subAlertId=""  id="priorityChangeSaveId">SET</button></li>';
										str+='</ul>';
									str+='</li>';  
									
									
									str+='<li id="historyId" style="display:none;" status-icon-block="alertHistory" attr_alert_id="'+alertId+'" subAlertId="" >';
										str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
									str+='</li>';
									str+='<li id="docAttachmentId" status-icon-block="attachment" attr_alert_id="'+alertId+'" style="display:none;" subAlertId="" >';
										str+='<i class="glyphicon glyphicon-paperclip" data-toggle="tooltip" data-placement="top" title="Attachments"></i>';
										str+='<form name="uploadAttachment" method="post" id="uploadAttachment">';
										str+='<div class="alert-status-attachment arrow_box_top" style="display:none;">';
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/>';
											str+='<input type="hidden" name="alertId" value="'+alertId+'" subAlertId=""  id="alertHiddenId"/>';
											str+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" type="button" id="uploadBtnId" subAlertId="" >upload</button>';
											str+='<span id="imagesUploadSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
										str+='</form>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="panel-heading" id="subBlockStates" style="display:none;">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser1"></div>';
							str+='</div>';
							str+='<div class="col-sm-8">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									str+='<li status-icon-block="alertStatus1" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId1" style="" > ';
										str+='<span class="status-icon arrow-icon" id="statusIdColor"></span><span id="statusId1">Pending</span>';
									str+='</li>';
									
									str+='<li id="displayDueDate3"  style="display:none;"  class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="Due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date1">Due date</span>';
									str+='</li>';
									
									str+='<li id="displayDueDate4"  style="display:none;"  class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="Due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date">Due date</span>';
									str+='</li>';
									
									 str+='<li id="displayPriority1" style="display:none;" status-icon-block="alertStatusChange" data-toggle="tooltip" data-placement="top" title="pririty change">';
										str+='<i class="glyphicon glyphicon-cog"></i>';
										str+='<ul class="alert-status-change-list arrow_box_top" style="display:none;">';
											str+='<li>high <input type="radio" name="alert-status-change-list" value="1" attr_value="high" class="pull-right priorityRadioCls" /></li>';
											str+='<li>medium <input type="radio" name="alert-status-change-list" attr_value="medium" value="2" class="pull-right priorityRadioCls" /></li>';
											str+='<li>low <input type="radio" name="alert-status-change-list" attr_value="low" value="3" class="pull-right priorityRadioCls" /></li>';
											str+='<li><button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" id="priorityChangeSaveId" subAlertId="" >SET</button></li>';
										str+='</ul>';
									str+='</li>';  
									
									str+='<li id="historyId1" style="display:none;" status-icon-block="alertHistory" attr_alert_id="'+alertId+'">';
										str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
									str+='</li>';
									str+='<li id="docAttachmentId1" status-icon-block="attachment" attr_alert_id="'+alertId+'" style="display:none;" subAlertId="" >';
										str+='<i class="glyphicon glyphicon-paperclip" data-toggle="tooltip" data-placement="top" title="Attachments"></i>';
										str+='<form name="uploadAttachment1" method="post" id="uploadAttachment1">';
										str+='<div class="alert-status-attachment arrow_box_top" style="display:none;">';
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/>';
											str+='<input type="hidden" name="subTaskId" value="'+alertId+'" subAlertId=""  id="alertHiddenId"/>';
											str+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" type="button" id="uploadBtnId" subAlertId=""  >upload</button>';
											str+='<span id="imagesUploadSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
										str+='</form>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<span id="main_alert_block">';
						str+='<div class="panel-body" >';
							str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
							str+='</p>';
							str+='<div id="statusDtlsDiv"></div>';
							str+='<div id="alertDetails"></div>';
							str+='<div id="articleAttachment"></div>';
							str+='<div id="alertCategory"></div>';
							str+='<div id="alertSubtask"></div>';
							//str+='<div id="alertComments"></div>';
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
											str+='<textarea class="form-control comment-area" id="alertCommentId1" placeholder="Comment here..."></textarea>';
										str+='</div>';
										str+='<div class="panel-footer text-right">';
											str+='<button class="btn btn-primary comment-btn commentChangeCls" attr_alert_id="'+alertId+'"  subAlertId="" id="commentChangeId">Save</button>';
											str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</span>';
				
				str+='<span id="sub_tasls_View_alert_block" style="display:none">';
						str+='<div id="mainAlertTitle"></div>';
						str+='<div class="panel-body">';
							str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH </span></span>';
							str+='</p>';
							str+='<div id="subAlertDetails"></div>';
							str+='<div id="subArticleAttachment"></div>';
							str+='<div id="subAlertCategory"></div>';
							str+='<div id="subAlertSubtask"></div>';
							//str+='<div id="subAlertComments"></div>';
							str+='<div id="subAlertGeneralComments"></div>';
							//str+='<div status-body="task" class="m_top20"></div>';
							//str+='<div status-body="subTask" class="m_top20"></div>';
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
											str+='<textarea class="form-control comment-area" id="alertCommentId2" placeholder="Comment here..."></textarea>';
										str+='</div>';
										str+='<div class="panel-footer text-right">';
											str+='<button class="btn btn-primary comment-btn commentChangeCls" attr_alert_id="'+alertId+'"   subAlertId=""  id="commentChangeId">Save </button>';
											str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</span>';
				
				str+='<span id="sub_task_block"  class="sub_task_block" style="display:none;" >';
						str+='<i attr_class="sub_task_block" class="glyphicon glyphicon-remove pull-right closeCls" ></i>';
						str+='<div class="panel-body panel-heading ">';
							str+='<div class="row">';
								str+='<div style="margin-left:25px"><i class="fa fa-level-down fa-2x" aria-hidden="true"></i> <label style="font-size:20px;margin-left:10px"> ASSIGN SUB TASK </label></div>';
							str+=' </div>';
							str+='<div class="col-sm-12">';
							  str+='<ul class="assign-subtask-list m_top20">';
							  str+='<form id="subTaskAlertAssign" name="subTaslAlertAssignForm">';
								str+='<li class="new">';
								  str+='<div class="row">';
									str+='<div class="col-sm-1">';
									  str+='<i class="glyphicon glyphicon-plus"></i>';
									str+='</div>';
									str+='<div class="col-sm-7">';
									  str+='<input type="text" class="form-control subTaskTitle" name="alertAssigningVO.title"/>';
									str+='</div>';
									str+='<div class="col-sm-4" style="margin-top:11px;">';
									 str+='<span class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="due date" style="padding: 9px;border-radius: 20px;margin-left: 40px">';
										str+='<i class="glyphicon glyphicon-calendar"></i> <span class="modal-date2 subTaskDueDate" style="" > Due Date </span>';
										str+='<input id="hiddenDueDate1" type="hidden" value="" name="alertAssigningVO.dueDate" />';
									str+='</span>';
										str+='<span class="assign-user " >';
										str+='<span class="departmentsForSubTask" attr_alert_id="'+alertId+'" onclick="getAssignUIAttributes('+alertId+');"><i class="glyphicon glyphicon-user pointerCls assigningCls"   style="cursor:pointer;"></i> </span>';
											str+='<span id="assignUIAttributesId" > </span>';
											str+='</form>';
										str+='</div>';
										

									str+='</span>';
									
									  /*str+='<li class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="due date">';
										str+=' <i class="glyphicon glyphicon-calendar"></i><span class="modal-date">Due date</span>';
									str+='</li>';*/
									   str+='<i class="fa fa-user-circle  fa-2x " aria-hidden="true"></i> ';
									 // str+='<span class="icon-name icon-primary"></span>';
									  //str+='<span class="label label-default">...</span>';
									str+='</div>';
								  str+='</div>';
								str+='</li>';
								str+='</form>';
							  str+='</ul>';
							str+='</div>';

						str+='</div>';
					str+='</div>';
				str+='</span>';
				
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#rightSideExpandView").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	$(".chosenSelect").chosen({width:'100%'});
	
	$('.modal-date2').data('daterangepicker');
	
	$(function() {
		var start = moment();
		
		function cb(start) {
			$('.modal-date2').html(start.format('DD/MM/YYYY'));
		}
       
		$('.modal-date2').daterangepicker({
			startDate: start,
			singleDatePicker:true,
			locale:{
				format:"DD/MM/YYYY"
			}
			
		}, cb);		
	});
	
	initializeFile();
	dateRangePicker(alertId);
	assignedOfficersDetailsForAlert(alertId);
	departmentsByAlert(alertId);
	getAlertData(alertId);
	getStatusCompletionInfo(alertId);
	getGovtAllDepartmentDetails();
	buildAssignUIAttributes(alertId);
}

function buildAssignUIAttributes(alertId){
	var str='';
str+='<div class="assign-user-body1" style="display:none;left:-856px;right:0;position:absolute;">';
str+='<div class="arrow_box_top" >';
str+='<div>';
	str+='<div class="row">';  
		str+='<i attr_class="assign-user-body1" class="glyphicon glyphicon-remove pull-right closeCls" ></i>';
		str+='<div class="col-sm-12">';
			str+='<div id="assignErrorDivId1" style="color:red;"></div>';
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId1"></span></label>';
			str+='<select class="chosenSelect" id="departmentsId1" name="alertAssigningVO.departmentId">	';
			str+='</select>'; 
		str+='</div>';
		
		str+='<div class="col-sm-6">';
			str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId1"></span></label>';
			str+='<select  class="chosenSelect" id="locationLevelSelectId1" name="alertAssigningVO.levelId">	';
				str+='<option></option>';
			str+='</select>';
		str+='</div>';
		
		str+='<div id="parentLevelDivId1"> </div>';
		
		str+='<div class="col-sm-6">';
			str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId1"></span></label>';
			str+='<select name="alertAssigningVO.designationId" id="designationsId1" class="chosenSelect">';
				str+='<option></option>	';
			str+='</select>';
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId1"></span></label>';
			str+='<select name="alertAssigningVO.govtOfficerId" id="officerNamesId1" class="chosenSelect">';
				str+='<option></option>';
			str+='</select>';
		str+='</div>';
	str+='</div>';
	str+='<input type="hidden" id="hiddenAlertId1" value="'+alertId+'" name="alertAssigningVO.alertId"/>';
str+='</div>';
str+='</div>';
str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
	str+='<button class="btn btn-primary btn-sm text-capital" id="subTaskassignOfficerId" type="button" onclick="saveSubTask('+alertId+');">assign</button>';
	str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg1">';
	str+='<span class="text-success" id="assignSuccess"></span>';
str+='</div>';
$('#assignUIAttributesId').html(str);

$('[data-toggle="tooltip"]').tooltip();
	$(".chosenSelect").chosen({width:'100%'});
	
	$('.modal-date2').data('daterangepicker');
	
	$(function() {
		var start = moment();
		
		function cb(start) {
			$('.modal-date2').html(start.format('DD/MM/YYYY'));
		}
       
		$('.modal-date2').daterangepicker({
			startDate: start,
			singleDatePicker:true,
			locale:{
				format:"DD/MM/YYYY"
			}
			
		}, cb);		
	});
}
$(document).on("click",".subTaskCls",function(){
	var subAlertId = $(this).attr('attr_sub_alert_Id');
	var alertId = $(this).attr('attr_alert_id');
	$('#main_alert_block').hide();
	$('#docAttachmentId').html('');
	$("#impactLevel,#priorityBodyId,#displaySubTasksliId,#displayDueDate1,#displayPriority,#mainBlockStates").hide();
	$('#sub_tasls_View_alert_block,#subAlertDetails,#subBlockStates,#displayDueDate3').show();
	$('.commentChangeCls').attr('subalertid',''+subAlertId+'');
	getSubTaskFullDetailsAction(subAlertId,alertId);
	$('#uploadBtnId').attr('subalertid',subAlertId);
	$('#alertHiddenId').val(subAlertId);
	initializeFile();
});

function getSubAlertsDetails(alertId,subAlertId){
	$('#main_alert_block').hide();
	$("#impactLevel,#priorityBodyId,#displaySubTasksliId,#displayDueDate1,#displayPriority,#mainBlockStates").hide();
	$('#sub_tasls_View_alert_block,#subAlertDetails,#subBlockStates,#displayDueDate3,#displayStatusId1').show();
	$('.commentChangeCls').attr('subalertid',''+subAlertId+'');
	$('#docAttachmentId1').attr('subalertid',''+subAlertId+'');
	$('#uploadBtnId').attr('subalertid',''+subAlertId+'');
	getSubTaskFullDetailsAction(subAlertId,alertId)
}
function getSubTaskFullDetailsAction(subAlertId,alertId)
{
	$("#subAlertDetails").html(spinner);
	var jsObj =
	{
		alertId  :alertId,
		subTaskId:subAlertId,
		task : ""
	}
	$.ajax({
		type:'GET',
		url: 'getStatusCompletionInfoForSubTaskAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#subAlertDetails").html('');
		//getAlertCategortByAlert(alertId);
		//getInvolvedMembersDetilas(alertId);
		//getSubTaskInfoForAlert(alertId);
		//getCommentsForAlert(alertId);
		//getDocumentsForAlert(alertId);
		if(result != null && result.length > 0){
			//buildSubTasksInfoForAlert(result[0].subList1);
			buildSubTaskAlertDataNew(result,alertId,subAlertId)
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
		}else{
			$("#subAlertDetails").html("NO DATA AVAILABLE...");
		}
	});
}

var subTaskglStr='';
function buildSubTaskAlertDataNew(result,alertId,subAlertId)
{
	var str='';
	var str1='';
	
	str+='<div class="row m_top20">';
		for(var i in result)
		{
			if(i==0){
				
				$('#docAttachmentId1').show();
				$("#statusId1").html(result[0].status);
				$(".modal-date1").html(result[0].dueDateStr);
				
					var str='';
					var splitNameArr = result[0].name.split(" ");
					var value = "";
					if(splitNameArr != null && splitNameArr.length>1)
						value = splitNameArr[1];
					else
						value = splitNameArr[0];  
					
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<span class="icon-name icon-primary">'+result[0].assignedByOfficerStr+'</span>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<p>'+result[0].assignedOfficerStr+' - '+result[0].deptName+'</p>';
							str+='<p> - '+result[0].designation+'<br> (<i class="glyphicon glyphicon-phone"></i> '+result[0].mobileNo+')</p>';
							str+='<p></p>';
						str+='</div>';
					str+='</div>';
					$("#assignedUser1").html(str);
					
					
				str="";
				str+='<div class="col-sm-1 text-center body-icons">';
				str+='<i class="fa fa-check fa-2x"></i>';
				str+='</div>';
				str+='<div class="col-sm-11">';
					str+='<h3> <u> Sub Task </u> : '+result[i].title+'</h3>';
					str+='<p class="m_top10">'+result[i].description+'</p>';
					
					str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Created Date : '+result[i].dateStr+'</small></p>';
					str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Due Date  : '+result[i].dueDateStr+'</small></p>';
				str+='</div>';
				
				str1='';
				
				if(result[i].subList != null && result[i].subList.length>0){
					str1+='<div class="row m_top20">';
					str+='<h4 class="text-muted text-capital"> Sub Task Attachments:  </h4>';	
					for(var k in result[i].subList){
							str1+='<div class="col-sm-3">';
							str1+='<img class="displayImgCls img-responsive m_top20" attr_articleId="" src="http://www.mytdp.com/images/'+result[i].subList[k]+'" style="width: 100px; height: 100px;cursor:pointer"/>';
							str1+='</div>';
					}
					
				str1+='</div>';
				}
				

				$("#subArticleAttachment").html(str1);
				
				if(result[i].commentList != null && result[i].commentList.length>0){
					str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-comments-o fa-2x"></i>';
					str+='</div>';
					str+='<div class="col-sm-11">';
						str+='<h4 class="text-muted text-capital"> Sub Tasks Comments </h4>';						
							str+='<div class="media">';							
								str+='<div class="media-body">';
										for(var k in result[i].commentList){
											if(result[i].commentList[k].comment != null && result[i].commentList[k].comment.length > 0)
											{
												str+='<p class="m_top5">'+result[i].commentList[k].comment+'</p>';
											}
											
											if(result[i].commentList[k].date != null && result[i].commentList[k].date.length > 0)
											{
												str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].commentList[k].date+'</p>';
											}
										}
									str+='</div>';
							str+='</div>';
						
					str+='</div>';
				str+='</div>';
				
				}			
				str1="";
				str1+='<div class="panel-body" style="font-weight:bold;font-size:15px"> <i class="fa fa-long-arrow-left fa-2x " style="cursor:pointer;margin-right:15px;margin-top:5px" aria-hidden="true" expand-icon="block1" attr_alertId="'+alertId+'" title="Back to Main Alert View."></i>  <span style="margin-top:-5px">';
				if(result[i].description.length>80)
					str1+=''+result[i].description.substring(0,80) +'... </span></div>';
				else
					str1+=''+result[i].description+'... </span></div>';
				
				$("#mainAlertTitle").html(str1);
					
				
				if(result[i].userStatus=='alertOwnner'){
					$('#displayStatusId1').attr('status-icon-block','alertStatus');
					$('#displayStatusId1').attr('subalertid',subAlertId);
				}
			}	


			
				subTaskglStr='';
				var str1='';
				 
					str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body">';
						str1+='<div class="panel-heading">';
							for(var i in result)
							{
								if(i == result.length-1)
									str1+='<br>';
								str1+='<label class="radio-inline">';
									if(result[0].status != null && result[0].status.trim() ==result[i].name.trim())
										str1+='<input type="radio" value="'+result[i].id +'" name="statusChange" checked/> '+result[i].name+'';
									else
										str1+='<input type="radio" value="'+result[i].id +'" name="statusChange"/> '+result[i].name+'';
								str1+='</label>';
								
							}
							
						str1+='</div>';
						str1+='<div class="panel-body pad_0">';
							str1+='<textarea class="form-control" id="updateStatusChangeComment1" placeholder="Comment.."></textarea>';
						str1+='</div>';
					str1+='</div>';
				
				str1+='<button class="btn btn-primary btn-sm text-capital closeSecondModal" subTaskId="'+subAlertId+'" attr_alert_id="'+alertId+'" id="updateStatusChange">update</button>';
				subTaskglStr=str1;
		}
	str+='</div>';
	
		$("#subAlertDetails").html(str);
		
		
}

$(document).on("click",".closeCls",function(){
	
	
	var className = $(this).attr('attr_class');
	$('.'+className+'').hide();
	if(className=='sub_task_block'){
		$('#main_alert_block').show();
		$('#alert-block-commentId').show();
		$('.subTaskTitle').val('');
		$('.subTaskDueDate').html('Due Date');
	}
});

$(document).on("click","#displaySubTasksli",function(){
	$('#main_alert_block').hide();
	$('#alert-block-commentId').hide();
	$('#sub_task_block').show();
	$('.assign-user').show();
	$('.assign-user-body1').hide();
	$('.subTaskDueDate').html('Due Date');
	
});

function saveSubTask(mainAlertId){
	
	if($("#departmentsId1").val() == null || $("#departmentsId1").val() == "" || $("#departmentsId1").val() == 0)
		{
			$("#assignErrorDivId1").html("Please select department");
			return;
		}
		
		if($("#locationLevelSelectId1").val() == null || $("#locationLevelSelectId1").val() == "" || $("#locationLevelSelectId1").val() == 0)
		{
			$("#assignErrorDivId1").html("Please select impact level");
			return;
		}
		
		var dynamicLocationValue=$('.dynamicSelectList').val();
		var hasError=false;
		var displayName = "";
		if (typeof(dynamicLocationValue) != "undefined"){
			
			
			$('.dynamicSelectList').each(function(){
				if(!hasError){
					displayName = $(this).attr('attr_dyna_name');
					var id =  $(this).attr('id');				
					var dynamicLocationValue=$('#'+id+'').val();
					if(dynamicLocationValue == null || dynamicLocationValue == "" || dynamicLocationValue == 0)
					{
						hasError=true;
						$("#assignErrorDivId1").html("Please select "+displayName+". ");
						return;
					}				
				}else{
					return;
				}
			});	
			if(hasError)
				return;
			
		}
		hasError=false;
		if(!hasError){
			var locationValue=$('.locationsCls').val();
			if(locationValue == null || locationValue == "" || locationValue == 0)
			{
				$("#assignErrorDivId1").html("Please select Location. ");
				return;
			}
			
			if($("#designationsId1").val() == null || $("#designationsId1").val() == "" || $("#designationsId1").val() == 0)
			{
				$("#assignErrorDivId1").html("Please select designation");
				return;
			}
			if($("#officerNamesId1").val() == null || $("#officerNamesId1").val() == "" || $("#officerNamesId1").val() == 0)
			{
				$("#assignErrorDivId1").html("Please select officer name");
				return;
			}
			//$("#assiningLdngImg").show();
			//$("#assignOfficerId").hide();
			
			var textValue = $('.subTaskDueDate').text().trim();
			alert(textValue)
			if(textValue == null || textValue == "" || textValue == 0 || textValue == "Due Date")
			{
				$("#assignErrorDivId1").html("Please select Date");
				return;
			}else{
				$('#hiddenDueDate1').val(textValue);
			}
			
			
		
			$("#assiningLdngImg1").show();
			$("#subTaskAssignOfficerId").hide();
			var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					displaySubStatus(uploadResult,mainAlertId);
				}
			};

			YAHOO.util.Connect.setForm('subTaslAlertAssignForm',true);
			YAHOO.util.Connect.asyncRequest('POST','assigningSubTaskToOfficerAction.action',uploadHandler); 			
		}		
}

function displaySubStatus(result,mainAlertId)
{
	var result = (String)(result);
	if(result.search('success') != -1){
		$("#assiningLdngImg1").hide();
		$("#subTaskAssignOfficerId").show();
		$("#assignSuccess").html('Alert Assigned Successfully')
		setTimeout(function(){
			getSubTaskInfoForAlert(mainAlertId) ;
			$('#sub_task_block').hide();
			$('#main_alert_block').show();
			//location.reload();
		},500);
	}else{
		alert("Please Try Again.");
		$("#assiningLdngImg1").hide();
		$("#assignSuccess").addClass("text-danger");
		$("#assignSuccess").html('Try Again');
	}	
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
	$("#alertDetails").html(spinner);
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
		$("#alertDetails").html('');
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
			$("#alertDetails").html("NO DATA AVAILABLE...");
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
		$('.modal-date1').html(result[0].dueDate);
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
				str+='<p class="m_top10"><small> <i class="fa fa-map-marker"></i> '+result[i].locationVO.state+'(S),'+result[i].locationVO.districtName+'(D),'+result[i].locationVO.constituencyName+'(C),'+result[i].locationVO.tehsilName+'(M)'+result[i].locationVO.wardName+','+result[i].locationVO.villageName+'(P),'+result[i].locationVO.hamletName+'(H)</small></p>';
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
	$("#alertCategory").html(spinner);
	var jsObj =
	{
		alertId  :alertId
	}
	$.ajax({
	  type:'GET',
	  url: 'getAlertCategoryByAlertAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			$("#alertCategory").html('');
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
		  url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
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
	$("#alertSubtask").html(spinner);
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		type:'GET',
		url: 'getSubTaskInfoForAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertSubtask").html('');
		if(result != null && result.length > 0)
		{
			buildSubTaskInfoForAlert(result,alertId);
		}
	});
}
function buildSubTaskInfoForAlert(result,alertId)
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
					str+='<li class="assigned subTaskCls " style="cursor:pointer;" attr_sub_alert_Id="'+result[i].alertId+'" attr_alert_id="'+alertId+'">';
						str+='<div class="row">';
							str+='<div class="col-sm-1">';
								str+='<i class="glyphicon glyphicon-ok"></i>';
							str+='</div>';
							str+='<div class="col-sm-9" >';
								str+='<p>'+result[i].title+'</p>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								//str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
							//	str+='<span class="icon-name icon-primary"></span>';
								//str+='<span class="label label-default">...</span>';
							str+='</div>';
						str+='</div>';
					str+='</li>';
				}	
			str+='</ul>';
		str+='</div>';
	str+='</div>';
	
/*
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-comments-o fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital"> Sub Tasks Comments </h4>';
			for(var i in result)
			{
				str+='<div class="media">';
				
					str+='<div class="media-body">';
					if(result[i].commentList != null && result[i].commentList.length>0){
						for(var k in result[i].commentList){
							if(result[i].commentList[k].comment != null && result[i].commentList[k].comment.length > 0)
							{
								str+='<p class="m_top5">'+result[i].commentList[k].comment+'</p>';
							}
							
							if(result[i].commentList[k].date != null && result[i].commentList[k].date.length > 0)
							{
								str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].commentList[k].date+'</p>';
							}
						}
					}
						
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	*/ 
	$("#alertSubtask").html(str);
}
function getCommentsForAlert(alertId){
	//$("#alertGeneralComments").html(spinner);
	$("#alertGeneralComments").html(spinner);
	var jsObj ={
		alertId  :alertId
	}
	$.ajax({
		type:'GET',
		url: 'getCommentsForAlertAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertGeneralComments").html('');
		if(result != null && result.length > 0)
		{
			buildCommentsForAlert(result);
		}else{
			$("#alertGeneralComments").html("");
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
						if(result[i].comment != null && result[i].comment.length > 0)
						{
							str+='<p class="m_top5">'+result[i].comment+'</p>';
						}
						if(result[i].attachementsList != null && result[i].attachementsList.length > 0)
						{
							str+='<p class="m_top5">Attachments : '+result[i].attachementsList+'</p>';
						}
						if(result[i].date != null && result[i].date.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].date+'</p>';
						}
						if(result[i].userName != null && result[i].userName.length > 0)
						{
							str+='<p class="m_top5"> Updated By: '+result[i].userName+'</p>';
						}
						if(result[i].deptName != null && result[i].deptName.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon "></i> Dept Name: '+result[i].deptName+'</p>';
						}
						if(result[i].designation != null && result[i].designation.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon"></i> Designation :'+result[i].designation+'</p>';
						}
						/*if(result[i].mobileNO != null && result[i].mobileNO.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].mobileNO+'</p>';
						}
						*/
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
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
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
		str+='<div class="row">';
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
	$("#existingDocsDivId").html(spinner);
	var jsObj ={
		alertId:alertId 
    }
    $.ajax({
    type:'GET',         
    url: 'getDocumentsForAlertsAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		$("#existingDocsDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="text-muted text-capital">alert attachment</h4>';
			str+='<ul class="list-inline imageShowOpen">';
			for(var i in result){
				str+='<li class="" attr_doc_id="'+result[i].id+'"  attr_path="'+result[i].name+'" id="imageAttachmentOpen'+result[i].id+'" >';
					str+='<img src="http://localhost:8080/PartyAnalyst/images/'+result[i].name+'" style="width: 60px; height: 60px;cursor:pointer" />';
				str+='</li>';
			}
			str+='</ul>';
			$("#existingDocsDivId").html(str);
		}
    });
}

function popUpFilter(type,result)
{
	var str='';
	var str1='';
	if(type == 'heading')
	{
		str1+='<div class="row">';
			str1+='<div class="col-sm-8">';
				str1+='<h4 class="modal-title text-capital" id="modalHeadingTotal">total alerts</h4>';
			str1+='</div>';
			str1+='<div class="col-sm-4">';
				str1+='<ul class="list-icons pull-right list-inline">';
					str1+='<li><i class="glyphicon glyphicon-filter filters-icon"></i></li>';
					str1+='<li data-dismiss="modal" aria-label="Close"><i class="glyphicon glyphicon-remove"></i></li>';
				str1+='</ul>';
			str1+='</div>';
		str1+='</div>';
		$("#alertManagementPopup .modal-header").html(str1);
	}else if(type == 'body')
	{
		str+='<div class="filter-block">';
			str+='<div class="row">';
				str+='<div class="col-sm-8">';
					str+='<h4 filters-list-title="all" class="panel-title text-capital"><i class="glyphicon glyphicon-filter"></i> filters</h4>';	
				str+='</div>';
				str+='<div class="col-sm-4">';
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="impactLevel">Impact Level</h5>';
					str+='<ul class="filters-list" filters-list="impactLevel">';
						for(var i in result.scopesList)
						{
							str+='<li>'+result.scopesList[i].name+'</li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="priority">priority</h5>';
					str+='<ul class="filters-list" filters-list="priority">';
						for(var i in result.severityList)
						{
							str+='<li>'+result.severityList[i].name+'</li>';
						}
					str+='</ul>';
					str+='<h5 class="text-capitalize m_top20" filters-list-title="alertSourceType">alert source type</h5>';
					str+='<ul class="filters-list" filters-list="alertSourceType">';
						for(var i in result.categoryList)
						{
							str+='<li>'+result.categoryList[i].name+'</li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="printMedia">Level</h5>';
					str+='<ul class="filters-list" filters-list="printMedia">';
						for(var i in result.editionsList)
						{
							str+='<li>'+result.editionsList[i].name+'</li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="electronicMedia">Level</h5>';
					str+='<ul class="filters-list" filters-list="electronicMedia">';
						for(var i in result.tvNewsChannelList)
						{
							str+='<li>'+result.tvNewsChannelList[i].name+'</li>';
						}
					str+='</ul>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#filter").html(str);
	}
}
function buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount)
{
	var str='';
	popUpFilter('heading','');
	var alertId = '';
	$("#modalHeadingTotal").html("Total "+statusName+' - '+statuscount);
	str+='<div id="filter"></div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12" expand-main="false">';
			str+='<div class="panel-group panel-white panel-left" id="accordion" role="tablist" aria-multiselectable="true">';
			for(var i in result)
			{
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="heading'+result[i].id+'">';
					if(i == 0)
					{
						str+='<a role="button" data-toggle="collapse" class="collapseArrow" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-expanded="true" aria-controls="collapse'+result[i].id+'">';
					}else{
						str+='<a role="button" data-toggle="collapse" class="collapsed collapseArrow" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-expanded="true" aria-controls="collapse'+result[i].id+'">';
					}
						
							str+='<h4 class="panel-title">';
								str+=''+result[i].name+'';
								str+='<small> - '+result[i].createdDate+'</small>';
								str+='<small><span class="pull-right">1 - '+result[i].subList.length+' of About '+result[i].subList.length+'</span></small>';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					if(i == 0)
					{
						str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
					}else{
						str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
					}
					
						str+='<div class="panel-body pad_0">';
							str+='<div class="row">';
								str+='<div class="col-sm-12">';
									str+='<ul class="alerts-list">';
										for(var j in result[i].subList)
										{
											str+='<li>';
												str+='<div class="row">';
													str+='<div class="col-sm-7">';
														str+='<h4>';
															str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:'+result[i].subList[j].severtyColor+';margin-right:3px;"></i>';
															str+='<span class="alert-title">'+result[i].subList[j].title+'</span>';
															str+='<span class="label label-default channel-name" data-toggle="tooltip" data-placement="top" title="'+result[i].subList[j].source+'">'+result[i].subList[j].source+'</span>';
														str+='</h4>';
													str+='</div>';
													str+='<div class="col-sm-5">';
														str+='<span class="arrow-icon pull-right" attr_alertId="'+result[i].subList[j].id+'" expand-icon="block1">';
															str+='<i class="glyphicon glyphicon-menu-right"></i>';
														str+='</span>';
														alertId = result[i].subList[j].id;
														if(result[i].subList[j].subTaskCount > 0)
														{
															str+='<span class="arrow-icon pull-right" style="margin:0px 4px" attr_alertId="'+result[i].subList[j].id+'" expand-icon="block1">';
																str+='<i class="fa fa-level-down"></i> '+result[i].subList[j].subTaskCount+'';
															str+='</span>';
														}
														str+='<span class="status-icon pull-right" style="background-color:'+result[i].subList[j].statusColor+';margin-right:3px;"></span>';
														str+='<span class="location-name pull-right" data-toggle="tooltip" data-placement="top" title="'+result[i].subList[j].location+'">'+result[i].subList[j].location+'</span>';
													str+='</div>';
												str+='</div>';
											str+='</li>';
											
										}
									str+='</ul>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				  str+='</div>';
			}
			str+='</div>';
		str+='</div>';
		str+='<div id="rightSideExpandView"></div>';
	str+='</div>';
	$("#alertManagementPopupBody").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	getAlertData(alertId);
}

function dateRangePicker(alertId)
{
	$(function() {
		var start = moment();
		
		function cb(start) {
			$('.modal-date').html(start.format('DD/MM/YYYY'));
		}
       
		$('.modal-date').daterangepicker({
			startDate: start,
			singleDatePicker:true,
			locale:{
				format:"DD/MM/YYYY"
			}
			
		}, cb);
        
		//cb(start);
		
	});
	$('.modal-date').on('apply.daterangepicker', function(ev, picker) {
		selectedDate = picker.startDate.format('DD/MM/YYYY');
		var jsObj ={
			alertId : alertId,
			date:selectedDate
		}
		$.ajax({
			type:'GET',
			url: 'updateAlertDueDateAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				alert("date updated successfully")
			}else{
				alert("try again")
			}
		});
	});
}

function buildAssignedOfficersDetailsForAlert(result)
{
	var str='';
	var splitNameArr = result[0].name.split(" ");
	var value = "";
	if(splitNameArr != null && splitNameArr.length>1)
		value = splitNameArr[1];
	else
		value = splitNameArr[0];  
	
	str+='<div class="media">';
		/* str+='<div class="media-left">';
			str+='<span class="icon-name icon-primary">'+result[0].name.substring(0,1)+''+value.substring(0,1)+'</span>';
		str+='</div>'; */
		str+='<div class="media-body">';
			/* str+='<p> - '+result[0].designation+'<br> (<i class="glyphicon glyphicon-phone"></i> '+result[0].mobileNo+')</p>';
			str+='<p>Location : '+result[0].source+'</p>';
			
			str+='<p>'+result[0].name+' - '+result[0].department+'</p>'; */
			str+='<p>'+result[0].designation+' <br> <i class="glyphicon glyphicon-phone"></i> : '+result[0].mobileNo+'</p>';
			str+='<p>Location :  '+result[0].source+'</p>';			
			str+='<p>Dept : '+result[0].department+'</p>'; 
			
			str+='<p></p>';
		str+='</div>';
	str+='</div>';
	$("#assignedUser").html(str);
	$(".assign-user").hide();
}

$(document).on("click","#displayAssignIconId",function(){
	$('.assign-user-body').show();       
});

function assignUser(alertId)
{
	var str='';
	str+='<div class="assign-user">';
		str+='<ul class="list-icons list-inline">';
			str+='<li id="displayAssignIconId" style="display:none;">';
				str+='<i class="glyphicon glyphicon-user"></i>Click To Assignee  ';
			str+='</li>';
		str+='</ul>';
		str+='<div class="assign-user-body" style="display:none">';
			str+='<form id="alertAssign" name="alertAssignForm">';
				str+='<div class="arrow_box_top">';
					str+='<div>';
						str+='<div class="row">';  
							str+='<div class="col-sm-12">';
								str+='<div id="assignErrorDivId"></div>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	';
									str+='<option value="0">Select Department</option>';
									str+='<option value="49">RWS</option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
								str+='<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">	';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div id="parentLevelDivId"> </div>';
							
							str+='<div class="col-sm-6">';
								str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>';
								str+='<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">';
									str+='<option></option>	';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId"></span></label>';
								str+='<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
						str+='</div>';
						str+='<input type="hidden" id="hiddenAlertId" value="'+alertId+'" name="alertAssigningVO.alertId"/>';
					str+='</div>';
				str+='</div>';
			str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
				str+='<button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">assign</button>';
				str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">';
				str+='<span class="text-success" id="assignSuccess"></span>';
			str+='</div>';
			str+='</form>';
		str+='</div>';
	str+='</div>';
	$("#assignedUser").html(str);
	$(".chosenSelect").chosen({width:'100%'});
	$('#displayAssignIconId').show();
}
function alertHistory(result)
{
	var str='';
	for(var i in result)
	{
		str+='<ul class="alert-history">';
			str+='<span class="alert-history-date">'+result[i].date+'</span>';
			if(result[i].timeList != null && result[i].timeList.length > 0)
			{
				for(var j in result[i].timeList)
				{
					str+='<li>';
						str+='<span class="alert-history-time">'+result[i].timeList[j].date+'</span>';
						if(result[i].timeList[j].statusList != null && result[i].timeList[j].statusList.length > 0)
						{
							for(var k in result[i].timeList[j].statusList)
							{
								if(result[i].timeList[j].statusList[k].strList != null && result[i].timeList[j].statusList[k].strList.length > 0)
								{
									for(var l in result[i].timeList[j].statusList[k].strList)
									{
										str+='<p class="alert-history-status m_top20 text-capital"><span class="status-icon arrow-icon"></span>status: '+result[i].timeList[j].statusList[k].strList+'</p>';
									}
								}
							}
						}
						if(result[i].timeList[j].commentList != null && result[i].timeList[j].commentList.length > 0)
						{
							for(var k in result[i].timeList[j].commentList)
							{
								str+='<p class="alert-history-body m_top5 text-capital">'+result[i].timeList[j].commentList[k].strList+'</p>';
							}
						}
						if(result[i].timeList[j].statusList != null && result[i].timeList[j].statusList.length > 0)
						{
							for(var k in result[i].timeList[j].statusList)
							{
								str+='<p class="alert-history-user m_top5 text-capital">'+result[i].timeList[j].statusList[k].designation+'</p>';
								str+='<p class="alert-history-user m_top5 text-capital">'+result[i].timeList[j].statusList[k].userName+'</p>';
							}
						}
					str+='</li>';
				}
			}
		str+='</ul>';
	}
	
	$("#alertManagementPopup1 .modal-dialog").css("width","60%")
	$("#alertManagementPopupBody1").html(str);
}
//alert status 
function alertStatusHistory(result,alertId)
{

	var str='';
	var str1='';
	
	
	if(result != null && result.length>0)
	{
		str+='<table class="table border_1">';
			str+='<thead class="text-capitalize">';
				str+='<th>Date</th>';
				str+='<th>Status</th>';
				str+='<th>Updated By</th>';
				str+='<th>Comments</th>';
			str+='</thead>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td>'+result[i].date+'</td>';
					str+='<td>'+result[i].status+'</td>';
					str+='<td>';
						str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].userName+' </span></p>';
						str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+result[i].deptName+' </span></p>';						
						str+='<p class="text-primary text-capitalize"><u> Designation:  <span style="color:black;"> '+result[i].designation+' </span></u></p>';
						//str+='<p class="text-primary text-capitalize">Dept Name: '+result[i].mobileNO+'</p>';
					str+='</td>';
					str+='<td>'+result[i].comment+'</td>';
				str+='</tr>';
			}  
			
		str+='</table>';
		
		
		$("#alertManagementPopup1 .modal-footer").show();
	
		
		$("#alertManagementPopup1 .modal-dialog").css("width","60%")
		$("#alertManagementPopupBody1").html(str);
	}else{
		$("#alertManagementPopupBody1").html("NO DATA")
	}
	
	
	if(isAdmin == "false"){
		if(globalUserType != "same" && globalUserType != "other"){
			str1+='<div class="text-left" id="changeStatudCheckBoxId">';     
				str1+='<label class="checkbox-inline">';
				
				if(isStatusAvailable){
					str1+='<input type="checkbox" attr_alert_id="'+alertId+'" class="alert-status-change changeStatsCls" /> I Want to change alert Status';  
				}
				
				str1+='</label>';  
				str1+='<div  id="updateStatusChangeBody" style="display:none;">'+glStr+'</div>';
			str1+='</div>';
		}
	}
		$("#alertManagementPopup1 .modal-footer").html(str1);
	if(globalStatusId == 8 || globalStatusId == 9){
			$("#changeStatudCheckBoxId").hide();   
	}
	
}
 
 
$(document).on("click",".changeStatsCls",function(){
	$('#updateStatusChangeBody').hide();
	if($(this).is(':checked')){
		$('#updateStatusChangeBody').show();
	}
	
});

function getFilterSectionAlertDetails(){
	var jsObj={
		
	}
	$.ajax({   
		type:'GET',
		url:'getFilterSectionAlertDetailsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		popUpFilter('body',result);
	});
}
var glStr='';
function alertStatus(result,alertId)
{
	glStr='';
	var str1='';
	 
		str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body">';
			str1+='<div class="panel-heading">';
				for(var i in result)
				{
					if(i == result.length-1)
						str1+='<br>';
					str1+='<label class="radio-inline">';
						str1+='<input type="radio" value="'+result[i].id +'" name="statusChange"/> '+result[i].name+'';
					str1+='</label>';
					
				}
				
			str1+='</div>';
			str1+='<div class="panel-body pad_0">';
				str1+='<textarea class="form-control" id="updateStatusChangeComment" placeholder="Comment.."></textarea>';
			str1+='</div>';
		str1+='</div>';
	
	str1+='<button class="btn btn-primary btn-sm text-capital closeSecondModal" attr_alert_id="'+alertId+'" subTaskId="" id="updateStatusChange">update</button>';
	glStr=str1;
	//$("#updateStatusChangeBody").html(str1);
}
/*
$(document).on("click",".departmentsForSubTask",function(){
		//getGovtAllDepartmentDetails();
});
*/
function getAssignUIAttributes(alertId){
	getGovtAllDepartmentDetails();
	buildAssignUIAttributes(alertId);
}
function getGovtAllDepartmentDetails(){
	
	$("#departmentsId1").html('');
	var jsObj={
		
	}
	$.ajax({   
		type:'GET',
		url:'getGovtAllDepartmentDetailsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			$("#departmentsId1").append("<option value='0'>Select Department</option>")
			for(var i in result){
				$("#departmentsId1").append("<option value="+result[i].id+">"+result[i].name+"</option>")
			}
			
			
		}
		$("#departmentsId1").chosen();
			$("#departmentsId1").trigger("chosen:updated");
	});
}

$(document).on('change', '#departmentsId1', function(){
	var deptId = $(this).val();
	getDepartmentSubLevels(deptId);
	
});
/* var globalUserLevelId;
var globalUserLevelValues = [];	 */
function getDepartmentSubLevels(deptId){
	
	var jsObj = {
		departmentId : deptId,
		levelId:globalUserLevelId
	}
	$.ajax({
      type:'GET',
      url: 'getDepartmentSubLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentSubLevels(result);
		}
			
	});
	
}
function buildDepartmentSubLevels(result){
	
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	
	$("#locationLevelSelectId1").html(str);
	$("#locationLevelSelectId1").trigger("chosen:updated");
}

$(document).on('change', '#locationLevelSelectId1', function(){
	getChildLevelValuesForSubTask();
	
});
function getChildLevelValuesForSubTask(){
	var departmentId = $("#departmentsId1").val();
	var jsObj = {
		departmentId : departmentId,
		levelId : $("#locationLevelSelectId1").val(),
		parentLevelId : globalUserLevelId,
		parentLevelValue : globalUserLevelValues[0]
		
	}
	$.ajax({
      type:'GET',
      url: 'getChildLevelValuesForSubTaskAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildChildLevelValuesForSubTask(result,departmentId);
		}
			
	});
}
function buildChildLevelValuesForSubTask(result,departmentId){
	var str='';
		
		for(var i in result){
			if(i<result.length-1){
				str+='<div class="col-md-3 col-xs-12 col-sm-6">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect dynamicSelectList" attr_dyna_name="'+result[i].name+'" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-md-3 col-xs-12 col-sm-6">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect locationsCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
				str+='</div>';
			}
			
		}
	
	$("#parentLevelDivId1").html(str);
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