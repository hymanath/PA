var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3){
	wurl = url.substr(0,(url.indexOf(".in")+3));
}
var actionUrl = '';
var alertStatusGlobalId = 0; 
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
		var path = "../images/"+$(this).attr("attr_path");
		window.open(path);
	});
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
	$(document).on("click",".filtersSwitch li",function(){
		var text = $(this).html();
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		if(text == "Classic")
		{
			$(".classicView").show();
			$(".premiumView").hide();
		}else if(text == "Premium")
		{
			$(".classicView").hide();
			$(".premiumView").show();
		}
	});
	$(document).on("click",".displayImgCls",function(){
		var articleId= $(this).attr("attr_articleId");
		$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopup1 .modal-footer").html(' ');
		var imgSrc = $(this).attr('src');
		$('#alertManagementPopupBody1').html('<img class=" img-responsive m_top20" attr_articleId="" src="../images/'+imgSrc+'" style="width: 999px; height: 500px;"/>');
		
		$('#alertManagementPopupHeading').html(' Sub Task Uploaded Attachment');
	});
	$(document).bind('keydown',"Shift+s", function assets() {
		$('#displaySubTasksli').trigger("click");
		return false;
	});
	$(document).bind('keydown',"Shift+x", function assets() {
		$('.closeCls').trigger("click");
		return false;
	});
	
	$(document).on("click",".articleDetailsCls",function(){
		var articleId= $(this).attr("attr_articleId");
		$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopup1 .modal-footer").html(' ');
		getTotalArticledetails(articleId);
	});
	$(document).on("click","#displaySubTasksli",function(){
		$('#main_alert_block').hide();
		$('#alert-block-commentId').hide();
		$('#sub_task_block').show();
		$('.assign-user').show();
		$('.assign-subtask').hide();
		$('.subTaskDueDate').html('Due Date');
		
	});
	$(document).on("click",".changeStatsCls",function(){
		$(".alert-status-change").prop("checked",false);
		$(this).prop("checked",true);
		$('#propasalupdateStatusChangeBody').hide();
		$('#updateStatusChangeBody').hide();
		if($(this).is(':checked')){
			$('#updateStatusChangeBody').show();
		}
	});
	$(document).on("click",".propasalchangeStatsCls",function(){
		var alertId = $(this).attr("attr_alert_id");
		$(".alert-status-change").prop("checked",false);
		$(this).prop("checked",true);
		$('#propasalupdateStatusChangeBody').hide();
		$('#updateStatusChangeBody').hide();
		if($(this).is(':checked')){
			$('#propasalupdateStatusChangeBody').show();
		}
	});
	$(document).on("click",".subTaskCls",function(){
		var subAlertId = $(this).attr('attr_sub_alert_Id');
		var alertId = $(this).attr('attr_alert_id');
		$('#main_alert_block').hide();
		$('#docAttachmentId').html('');
		$("#impactLevel,#priorityBodyId,#displaySubTasksliId,#displayDueDate1,#displayPriority,#mainBlockStates").hide();
		$('#sub_tasls_View_alert_block,#subAlertDetails,#subBlockStates,#displayDueDate3,#displayStatusId1').show();
		$('.commentChangeCls').attr('subalertid',''+subAlertId+'');
		getSubTaskFullDetailsAction(subAlertId,alertId);
		getSubTaskCommetDtls(subAlertId,alertId);
		$('#uploadBtnId').attr('subalertid',subAlertId);
		$('#alertHiddenId').val(subAlertId);
		initializeFile();
	});
	$(document).on("click","#uploadBtnId",function(){
		
		
		var alertId = $(this).attr("attr_alert_id");
		var subTaskId = $(this).attr("subalertid");
		var urlStr='uploadDocumentsForAlertAction.action';
		var formName='uploadAttachment';
		var errorDiv='errorDiv';
		if(subTaskId != null && subTaskId.length>0){
			urlStr='uploadDocumentsForSubTaskAction.action';
			formName='uploadAttachment1';
			errorDiv='errorDiv1';
		}
		var totalImages=0;
		$('.jFiler-item').each(function(){
			totalImages = totalImages+1;			
		});
		if(totalImages == 0){
			$('.'+errorDiv+'').html(' Please upload atleast one file.');
			return;
		}
		
		$('.imagesUploadSpinner').show();
		$(".uploadBtnIdCls").hide();
		
		var uploadHandler = { 
			upload: function(o) {
				var uploadResult = o.responseText;
				$('.jFiler-item').html('');
				$('.imagesUploadSpinner').hide();
				$(".uploadBtnIdCls").show();
				if(subTaskId != null && subTaskId.length>0){
					showSbmitSubTaskStatusNew(uploadResult,alertId,subTaskId);
				}else if(formName='uploadAttachment')
					showSbmitStatusNew(uploadResult,alertId);
					
			}
		};
		YAHOO.util.Connect.setForm(formName,true);  
		YAHOO.util.Connect.asyncRequest('POST',urlStr,uploadHandler);
		
	});
	$(document).on("click",".closeCls",function(){
		var className = $(this).attr('attr_class');
		if(className=='sub_task_block'){
			$('.'+className+'').hide();
			$('#main_alert_block').show();
			$('#alert-block-commentId').show();
			$('.subTaskTitle').val('');
			$('.subTaskDueDate').html('Due Date');
		}else if(className == 'assign-subtask')
		{
			$('.'+className+'').hide();
		}
	});

	$(document).on("click","#displayAssignIconId",function(){
		$('.assign-user-body').show();       
	});

	$(document).on('change', '#departmentsId1', function(){
		var deptId = $(this).val();
		
		if(deptId == globalSubTaskDeptId){
			getDepartmentSubLevels(deptId);
		}else{
			getDepartmentLevelsForSubTask(deptId);
		}
			
	});

	$(document).on('change', '#locationLevelSelectId1', function(){
		
		var deptId = $("#departmentsId1").val();
		if(deptId == globalSubTaskDeptId){
			getChildLevelValuesForSubTask();
		}else{
			getParentLevelsOfLevelForSubTask();
		}
		
			
	});

	$(document).on("click",".filters-cancel",function(){
		$("#filter").hide();
	});
	$(document).on("click",".filters-apply",function(){
		$("#filter").hide();
		var impactLevelArr =[];
		var priorityArr =[];
		var alertSourceArr =[];
		var printMediaArr =[];
		var electronicMediaArr=[];
		var blockName = '';
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statusCount = $(this).attr("attr_status_count");
		var departmentId = $(this).attr("attr_department_id");
		$(".filters-list").each(function(){
			blockName = $(this).attr("filters-list");
			/* if(blockName == 'electronicMedia')
			{
				var  id = '';
				$("[filters-list="+blockName+"] li.active").each(function(){
					id = $(this).attr("attr_id");
					electronicMediaArr.push(id);
				});
				
			}else if(blockName == 'printMedia')
			{
				var  id = '';
				$("[filters-list="+blockName+"] li.active").each(function(){
					id = $(this).attr("attr_id");
					printMediaArr.push(id);
				});
			}else  */
			if(blockName == 'priority')
			{
				var  id = '';
				$("[filters-list="+blockName+"] li.active").each(function(){
					id = $(this).attr("attr_id");
					priorityArr.push(id);
				});
			}else if(blockName == 'impactLevel')
			{
				var  id = '';
				$("[filters-list="+blockName+"] li.active").each(function(){
					id = $(this).attr("attr_id");
					impactLevelArr.push(id);
				});
			}else if(blockName == 'alertSourceType')
			{
				var  id = '';
				$("[filters-list="+blockName+"] li.active").each(function(){
					id = $(this).attr("attr_id");
					alertSourceArr.push(id);
					var value = $(this).text();
					/* if(value == "")
					{
						electronicMediaArr.push(id)
					} */
					
				});
			}
		})
		
		getAlertDtlsBasedOnStatusFilterClick(statusName,statusCount,impactLevelArr,priorityArr,alertSourceArr,printMediaArr,electronicMediaArr);
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
		var dynamicLocationValue=$('.assigningDynamicSelectList').val();
		var hasError=false;
		var displayName = "";
		if (typeof(dynamicLocationValue) != "undefined"){
			
			
			$('.assigningDynamicSelectList').each(function(){
				if(!hasError){
					displayName = $(this).attr('attr_dyna_name');
					var id =  $(this).attr('id');				
					var dynamicLocationValue=$('#'+id+'').val();
					if(dynamicLocationValue == null || dynamicLocationValue == "" || dynamicLocationValue == 0)
					{
						hasError=true;
						$("#assignErrorDivId").html("Please select "+displayName+". ");
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
			var locationValue=$('.assingLocationCls').val();
			if(locationValue == null || locationValue == "" || locationValue == 0)
			{
				$("#assignErrorDivId").html("Please select Location. ");
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
	
	$(document).on("click",".alertStatusCls",function(){
		$("#errMsgAprAmuntId").html('');
		$("#approvedAmountId").val('');
		alertStatusGlobalId= $(this).attr("attr_id");
		if(alertStatusGlobalId == 10){
			$("#rejinderRespnseId").hide();
		}
		var statusSelectedName = $("#radio-"+alertStatusGlobalId).parent().find('span').html();
		if(statusSelectedName == "Proposal"){
			$(".proposalAppendBlockDivCls").show();
		}else{
			$(".proposalAppendBlockDivCls").hide();
		}
		if(statusSelectedName == "Rejoinder"){
			$(".rejoinderDivCls").show();
		}else{
			$(".rejoinderDivCls").hide();
		}
	});
	$(document).on("click",".alertStatusAmountCls",function(){
		alertStatusGlobalId= $(this).attr("attr_id");
		if(globalPrposalCategoryId == 1){
			if(alertStatusGlobalId == 3){
				$(".alertStatusAmountInputCls").show();
			}else{
				$(".alertStatusAmountInputCls").hide();
			}
		}
		
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
		if(statusId == null || statusId==undefined || statusId==""){
				alert("Please Select Status");
			    return;
		}
		if(comment == null || comment.trim() == "")
		{
			alert("Please Enter Comment");
			return;
		}
		
		if(statusId == null || statusId =='')
			statusId=globalStatusId;
		 
		$("#updateStatusChangeAjaxSymbol").html(spinner);
		
			var jsObj ={
				alertId : alertId,
				statusId : statusId,
				subTaskId:subTaskId,
				comment: comment
			}
		//1111
		var callURL = 'updateAlertStatusCommentAction.action';
		if(subTaskId != null && subTaskId>0){
			callURL = 'updateSubTaskStatusCommentAction.action';
		}
		$.ajax({
			type:'POST',
			url: callURL,
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			
			$("#updateStatusChangeAjaxSymbol").html('');
			if(result != null && result.exceptionMsg == 'success')
			{
				$("#updateStatusChangeMsg").html("status updated successfully");
				$("#commentPostingSpinner").html("status updated successfully");
				
				if(subTaskId == null || subTaskId.length == 0){
					getCommentsForAlert(alertId);
					
				}else{
					getSubAlertsDetails(alertId,subTaskId);
				}
				
				setTimeout(function(){
					$("#commentPostingSpinner").html(" ");
					$("#updateStatusChangeMsg").html("status not updated successfully,Pls try again");
					$('#alertManagementPopup1').modal('hide');
					
					
				},1500);
				rightSideExpandView(alertId);
					setTimeout(function(){
						$("[expanded-block='block1']").show().css("transition"," ease-in, width 0.7s ease-in-out");
					},750);
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
				$('.alert-status-change-list').hide();
				alert("Priority Updated Successfully.");
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
			statusId:0 // not updating status here.
		}
		
		var callURL = 'updateCommentAction.action';
		if(subTaskId != null && subTaskId>0)
			callURL = 'updateSubTaskCommentAction.action';
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
				//alert(subTaskId);
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
	$(document).on("click","#alertCommentId1",function(e){
		e.stopPropagation()
	});
	
	$(document).on("click","div.assign-user",function(e){
		e.stopPropagation()
		$(".assign-subtask").show();
	});
	
	$(document).on("click",".glyphicon-user",function(e){
		e.stopPropagation()
		$(".assign-subtask").show();
	});
	
	$(document).on("click",".panel-border-white,.panel-border-white textarea,#alertCommentId1",function(e){
		e.stopPropagation();
		e.preventDefault();
	});
	
	$(document).on("click",function(e){
		e.stopPropagation()
		$("div.comment-area").show();
		$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea,.assign-user-body,.alert-status-change-list,.alert-status-attachment").hide();
		$(".panel-border-white textarea").val('');
	});
	$(document).on("click","[basic-switch] li",function(){
		$(this).addClass("active");
	});
	$(document).on("click","[filer-selection]",function(e){
		e.stopPropagation();
		var blockName = $(this).closest("ul").attr("filters-list");
		var value = $(this).closest("li").text();
		var valueId = $(this).closest("li").attr("attr_id");
		var $this = $(this);
		var selectionType = $(this).attr("filer-selection");
		$(this).closest("li").removeClass("active");
		$(this).remove();
		if(blockName == 'alertSourceType')
		{
			if(value == 'Print Media')
			{
				$("[filters-list-title='printMedia']").hide();
				$("[filters-list='printMedia']").html('');
			}else if(value == 'Electronic Media')
			{
				$("[filters-list-title='electronicMedia']").hide();
				$("[filters-list='electronicMedia']").html('');
			}
		}else if(blockName == 'locLevel')
		{
			if(value == 'VILLAGE' || value == 'PANCHAYAT'|| value == 'MANDAL'|| value == 'CONSTITUENCY'|| value == 'DISTRICT'|| value == 'STATE')
			{
				$("[filters-list-title='statesLevel'],[filters-list-title='districtsLevel'],[filters-list-title='consLevel'],[filters-list-title='mandalLevel'],[filters-list-title='panLevel'],[filters-list-title='villageLevel'],[filters-list='statesLevel'],[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel'],[filters-list='villageLevel']").hide();
				$("[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel'],[filters-list='villageLevel']").html(' ');
			}
		}
	});
	$(document).on("click",".assign-subtask,.arrow_box_top,#departmentsId1",function(e){
		e.stopPropagation();
	});
	$(document).on("click","[filters-list] li",function(e){
		e.stopPropagation();
		
		var blockName = $(this).closest("ul").attr("filters-list");
		if(blockName == 'locLevel')
		{
			$("[filters-list="+blockName+"] li").removeClass("active").find("span.remove").remove();
			$("[filters-list='statesLevel'] li").removeClass("active").find("span.remove").remove();
			$("[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel'],[filters-list='villageLevel']").html(' ');
			$(this).addClass("active");
			$("[filters-list-title='statesLevel'],[filters-list-title='districtsLevel'],[filters-list-title='consLevel'],[filters-list-title='mandalLevel'],[filters-list-title='panLevel'],[filters-list-title='villageLevel'],[filters-list='statesLevel'],[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel'],[filters-list='villageLevel']").hide();
			$(".scroller-district,.scroller-mandal,.scroller-cons,.scroller-panchayat,.scroller-village").removeAttr("style");
			
		}else if(blockName == 'statesLevel' || blockName == 'districtsLevel' || blockName == 'consLevel' || blockName == 'mandalLevel' || blockName == 'panLevel' || blockName == 'villageLevel'){
			var impactLevel = $("[filters-list='locLevel'] li.active").attr("attr_levelVal");
			if(impactLevel != blockName)
			{
				$("[filters-list="+blockName+"] li").removeClass("active").find("span.remove").remove();
				$(this).addClass("active");
			}else{
				$(this).addClass("active");
			}
		}else{
			$(this).addClass("active");
		}
		
		var locLevel = $("[filters-list='locLevel'] li.active").attr("attr_level");
		var value = $(this).html();
		var valueId = $(this).attr("attr_id");
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
			if(blockName == 'alertSourceType')
			{
				if(value == 'Print Media')
				{
					$("[filters-list-title='printMedia']").show();
					var str='';
					for(var i in printMediaFilters)
					{
						str+='<li attr_id='+printMediaFilters[i].id+'>'+printMediaFilters[i].name+'</li>'
					}
						
					$("[filters-list='printMedia']").html(str);
				}else if(value == 'Electronic Media')
				{
					var str='';
					$("[filters-list-title='electronicMedia']").show();
					for(var i in electronicMediaFilters)
					{
						str+='<li attr_id='+electronicMediaFilters[i].id+'>'+electronicMediaFilters[i].name+'</li>'
					}
						
					$("[filters-list='electronicMedia']").html(str);
				}
				
			}else if(blockName == 'alertStatus' || blockName == 'alertSubtaskStatus'){
				if(valueId == 'All')
				{
					$("[filters-list='"+blockName+"'] li").removeClass("active").find("span.remove").remove();
					$("[filters-list='"+blockName+"'] li").addClass("active").append("<span class='remove' filer-selection='true'><i class='glyphicon glyphicon-remove'></i></span>")
				}
			}else if(blockName == 'alertDepartments')
			{
				if(valueId == 'All')
				{
					$("[filters-list='"+blockName+"'] li").removeClass("active").find("span.remove").remove();
					$("[filters-list='"+blockName+"'] li").addClass("active").append("<span class='remove' filer-selection='true'><i class='glyphicon glyphicon-remove'></i></span>")
					var str = '';
					for(var i in scopesListFilters)
					{
						str+='<li attr_id='+scopesListFilters[i].id+'>'+scopesListFilters[i].name+'</li>'
					}
					$("[filters-list='impactLevel']").html(str);
				}else{
					$("[filters-list="+blockName+"] li").removeClass("active").find("span.remove").remove();
					$(this).addClass("active").append("<span class='remove' filer-selection='true'><i class='glyphicon glyphicon-remove'></i></span>")
					getlevlsForDepartmnt(valueId);
				}
				
			}else if(blockName == 'locLevel')
			{
				if(locLevel == 'state' )
				{
					$("[filters-list-title='statesLevel'],[filters-list='statesLevel']").show();
				}else if(locLevel == 'district' )
				{
					$("[filters-list-title='statesLevel'],[filters-list='statesLevel'],[filters-list-title='districtsLevel'],[filters-list='districtsLevel']").show();
				}else if(locLevel == 'constituency' )
				{
					$("[filters-list-title='statesLevel'],[filters-list='statesLevel'],[filters-list-title='districtsLevel'],[filters-list='districtsLevel'],[filters-list-title='consLevel'],[filters-list='consLevel']").show();
				}else if(locLevel == 'mandal' )
				{
					$("[filters-list-title='statesLevel'],[filters-list-title='districtsLevel'],[filters-list-title='consLevel'],[filters-list-title='mandalLevel'],[filters-list='statesLevel'],[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel']").show();
				}else if(locLevel == 'panchayat' )
				{
					$("[filters-list-title='statesLevel'],[filters-list-title='districtsLevel'],[filters-list-title='consLevel'],[filters-list-title='mandalLevel'],[filters-list-title='panLevel'],[filters-list='statesLevel'],[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel']").show();
				}else if(locLevel == 'village')
				{
					$("[filters-list-title='statesLevel'],[filters-list-title='districtsLevel'],[filters-list-title='consLevel'],[filters-list-title='mandalLevel'],[filters-list-title='panLevel'],[filters-list-title='villageLevel'],[filters-list='statesLevel'],[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel'],[filters-list='villageLevel']").show();
					
				}
				
			}else if(blockName == 'statesLevel')
			{
				if(value == 'ANDHRA PRADESH')
				{
					if(locLevel == 'district' || locLevel == 'constituency' || locLevel == 'mandal' || locLevel == 'panchayat'|| locLevel == 'village')
					{
						getDistrictsListForState('1')
					}
				}
			}else if(blockName == 'districtsLevel')
			{
				if(locLevel == 'constituency' || locLevel == 'mandal'|| locLevel == 'panchayat'|| locLevel == 'village' )
				{
					getConstituenciesForADistrictAjax(valueId)
				}
			}else if(blockName == 'consLevel')
			{
				if(locLevel == 'mandal' || locLevel == 'panchayat'|| locLevel == 'village')
				{
					getMandalDetailsByConstituency(valueId)
				}
				
			}else if(blockName == 'mandalLevel')
			{
				if(locLevel == 'panchayat' || locLevel == 'village')
				{
					getPanchayatWardByMandal(valueId)
				}
				
			}else if(blockName == 'panLevel')
			{
				if(locLevel == 'village')
				{
					getHamletsForPanchayat(valueId)
				}
				
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
				$("#alertManagementPopup1 .modal-footer").html(' ');
				viewAlertHistory(alertId);
			}else if(status == 'alertStatus'){
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopupHeading").html('ALERT STATUS HISTORY');
				$("#alertManagementPopup1 .modal-footer").html(' ');
				getAlertStatusHistory(alertId);
			}else if(status == 'alertStatus1'){// sub task status popup
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopup1 .modal-footer").html(' ');
				$("#alertManagementPopupHeading").html('SUB TASK STATUS HISTORY');
				getSubTaskStatusHistory(subalertid,alertId);				
			}else if(status == 'alertStatusChange'){
				$(this).find('ul').toggle();
			}else if(status == 'task'){
				statusBody(status);
			}else if(status == 'subTask'){
				statusBody(status);
			}else if(status == 'attachment'){
				var filerKit = $("#imageId").prop("jFiler");
				filerKit.reset();
				$(this).find('.alert-status-attachment').toggle();
			}
		}
	});

	$(document).on("click","[expand-icon]",function(){
        var expandBlockName = $(this).attr("expand-icon");
		var alertId = $(this).attr("attr_alertId");
		//var statusId = $(this).attr("attr_statusId");
		$("[expand-icon]").closest("li").removeClass("active");
		$("[expand-icon]").removeClass("text-primary");
		$(this).addClass("text-primary");
		$(this).closest("li").addClass("active");
		rightSideExpandView(alertId);
		glStr = '';
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
		if(window.location.hostname == "localhost")
		{
			actionUrl = window.location.pathname.split("/")[2]; //local
		}else{
			actionUrl = window.location.pathname; //live
		}
		
		 if(actionUrl == "alertDistOfficeManagement.action" || actionUrl == "alertDistManagement.action" || actionUrl == "alertUserManagementAction.action")
		{
			setTimeout(function(){
				$("[filters-list='locLevel']").closest(".row").hide();
			},500);
			
		}
		$("#filter").toggle();
	});

	$(document).on("keypress","#alertIdSearch",function(e){
		var alertId =  $("#alertIdSearch").val();
		if(e.which == 13) {
			getSearchAlertsDtls(alertId)
		}
	});
	$(document).on("click",".alertIdSpecialSearch",function(e){
		var alertId = $(this).attr("attr_alertId");
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html("<div class='row'><div id='rightSideExpandView'></div></div>");
		rightSideExpandView(alertId);
		//popUpFilter('heading','','','','','','','');
		$(".flipview-btn,.filters-icon").hide();
		var alertId = '';
		$("#modalHeadingTotal").html("Alert Status");
		setTimeout(function(){
			$("[expanded-block='block1']").addClass("col-sm-12").removeClass("col-sm-8 pad_left0");
			$("[expanded-block='block1']").show();
		},750);
	});
	$(".tbtn").click(function(){
		$(".themeControll").toggleClass("active");
	});
	
	$(document).on("click","#updateStatusChangeId",function(){
		
		//$('input[name=statusChange]:checked', '#updateStatusChangeBody').val()
		var comment = $("#updateStatusChangeComment").val()
		var alertId = $(this).attr("attr_alert_id");
		var subTaskId = $(this).attr("subTaskId");
		var proposalCategoryId =0;
		 var amount = 0;
		 var rejoinderActionId = 0;
		if(alertStatusGlobalId == 13){
			 proposalCategoryId= $("input[name=statusChekBx]:checked").val();
			 amount = $("#amountId").val();
		}
		if(alertStatusGlobalId == 10){
			 rejoinderActionId= $("input[name=RejoinderChekBx]:checked").val();
			 var imageVal = $("#rejoinderAttachmentId").val();
		}
		
		if(subTaskId != null && subTaskId>0){
			comment = $("#updateStatusChangeComment1").val()
		}
		if(alertStatusGlobalId != null && alertStatusGlobalId==0){
			alert("Please Select Status");
			return;
		}
		
		if(alertStatusGlobalId == 13){
			if(typeof proposalCategoryId == "undefined"){
				alert("Please Check Any Proposal Category");
				return;
			}
			if(proposalCategoryId == 1){
				if(amount == 0){
				alert("Please Enter Amount");
				return;
			 }
				 var numericExpression = /^[0-9]+$/;
				if(!$('#amountId').val().match(numericExpression)){
					$("#errMsgAmuntId").html('<span style="color:red">Please Enter Numeric Value Only.....</span>');
					return;
				}else{
					$("#errMsgAmuntId").html('');
				}
			}
		}
		if(alertStatusGlobalId == 10){
			if(typeof rejoinderActionId == "undefined"){
				alert("Please Check Any Rejoinder Action");
				return;
			}
			if(imageVal == ''){
				alert("Please Browse File");
				return;
			 }
		}
		if(comment == null || comment.trim() == "")
		{
			alert("Please Enter Comment");
			return;
		}
		
		/* if(statusId == null || statusId =='')
			statusId=globalStatusId;
		 */
		$("#updateStatusChangeAjaxSymbol").html(spinner);
		var jsObj ={
				alertId : alertId,
				statusId : alertStatusGlobalId,
				subTaskId:subTaskId,
				comment: comment,
				proposalCategoryId : proposalCategoryId,
				proposalAmount : amount,
				rejoinderActionTypeId : rejoinderActionId
			}
			
		if(subTaskId != null && subTaskId>0){
			var jsObj ={
				alertId : alertId,
				statusId : alertStatusGlobalId,
				subTaskId:subTaskId,
				comment: comment
			}
		}
		/* if($("#displayStatusId #statusId").html() == 'Proposal'){
			var jsObj ={
				alertId : alertId,
				proposalStatusId : alertStatusGlobalId
			}	
		} */
			
		//1111
	
		var callURL = 'updateAlertStatusCommentAction.action';
		if(subTaskId != null && subTaskId>0){
			callURL = 'updateSubTaskStatusCommentAction.action';
		}
		/* if($("#displayStatusId #statusId").html() == 'Proposal'){
			callURL = 'updateProposalStatusFrAlertAction.action';
		} */
		$.ajax({
			type:'POST',
			url: callURL,
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(alertStatusGlobalId == 10){
				uploadAttachmentsFrRejinderStatus();
			} 
			setTimeout(function(){
			 $("#updateStatusChangeAjaxSymbol").html('');
				if(result != null && result.exceptionMsg == 'success' && result.message == null)
				{
					$("#updateStatusChangeMsg").html("status updated successfully");
					$("#commentPostingSpinner").html("status updated successfully");
					
					if(subTaskId == null || subTaskId.length == 0){
						getCommentsForAlert(alertId);
						
					}else{
						getSubAlertsDetails(alertId,subTaskId);
					}
					
					setTimeout(function(){
						$("#commentPostingSpinner").html(" ");
						$("#updateStatusChangeMsg").html("status not updated successfully,Pls try again");
						$('#alertManagementPopup1').modal('hide');
						
						
					},1500);
					rightSideExpandView(alertId);
						setTimeout(function(){
							$("[expanded-block='block1']").show().css("transition"," ease-in, width 0.7s ease-in-out");
						},750);
				}else if(result.message != null && result.message == "Already In ProposalStatus"){
					alert("This Alert Already Have ProposalStatus");
				}else{
					alert("try again");
				}
			},2000);
			
		});
	});
	$(document).on("click",".proposalCheckbox",function(){
		$("#errMsgAmuntId").html('');
		$('#amountId').val('');
		$(".proposalCheckbox").prop("checked",false);
		$(this).prop("checked",true);
		var value = $(this).val();
		if(value == 1)
		{
			$(".amountCls").toggle();
		}else{
			$(".amountCls").hide();
		}
	});
}
/*Default Image*/
	
	
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
						//str+='<p class="text-primary text-capitalize">'+result[i].userName+'</p>';
						//str+='<p class="text-muted text-capitalize">-<u>'+result[i].designation+'</u></p>';
						str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].userName+' </span></p>';
						str+='<p class="text-primary text-capitalize"><u> Designation:  <span style="color:black;"> '+result[i].designation+' </span></u></p>';
						str+='<p class="text-primary text-capitalize"><u> Location:  <span style="color:black;"> '+result[i].location+' </span></u></p>';
						//str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+result[i].deptName+' </span></p>';
						
						if(result[i].deptName != null && result[i].deptName.length > 0){
							var deptArr = result[i].deptName.split(",");
							if(deptArr.length > 3){
								str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+deptArr[0]+","+deptArr[1]+","+deptArr[2]+'... </span></p>';
							}else{
								str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+result[i].deptName+' </span></p>';
							}   
						}
						
						//str+='<p class="text-primary text-capitalize">Dept Name: '+result[i].mobileNO+'</p>';
					str+='</td>';
					str+='<td>'+result[i].comment+'</td>';
				str+='</tr>';
			}  
			
		str+='</table>';
		
		
		
		$("#alertManagementPopup1 .modal-footer").show();
		$("#alertManagementPopup1 .modal-footer").html(' ');
		$("#alertManagementPopup1 .modal-dialog").css("width","60%")
		$("#alertManagementPopupBody1").html(str);
	}else{
		$("#alertManagementPopupBody1").html("NO DATA")
	}
	    //santosh
		if(isAdmin == "false"){
			//if(globalUserType != "same"){
				str1+='<div class="text-left" id="changeStatudCheckBoxId">';     
					
					if(subTaskStatusChangAvailable)
					{
						str1+='<label class="checkbox-inline">';
							str1+='<input type="checkbox" attr_alert_id="'+alertId+'" subTaskId="'+subTaskId+'" class="alert-status-change changeStatsCls" /> I Want to change alert Status';
						str1+='</label>';
						/* if($("#displayStatusId #statusId").html() == 'Proposal')
						{
							str1+='<label class="checkbox-inline">';
								str1+='<input type="checkbox" attr_alert_id="'+alertId+'" subTaskId="'+subTaskId+'" class="alert-status-change propasalchangeStatsCls" /> Do You Want To Change Propasal Status'
							str1+='</label>';
						} */
					}
					str1+='<div  id="updateStatusChangeBody" style="display:none;">'+subTaskglStr+'</div>';
					str1+='<div  id="propasalupdateStatusChangeBody" style="display:none;">'+subTaskglPropasalStr+'</div>';
				str1+='</div>';
			//}
		}  
		$("#alertManagementPopup1 .modal-footer").html(str1);
		//$("#alertManagementPopup1 .modal-footer").html(' '); // commented by srishailam pittala
		var options = {
	  sourceLanguage:
		  google.elements.transliteration.LanguageCode.ENGLISH,
	  destinationLanguage:
		  [google.elements.transliteration.LanguageCode.TELUGU],
	  shortcutKey: 'alt+t',
	  transliterationEnabled: true
	};
	var control = new google.elements.transliteration.TransliterationControl(options);
	//control.makeTransliteratable(['updateStatusChangeComment1']);
	
}
function showSbmitSubTaskStatusNew(uploadResult,alertId,subAlertId){
	if(uploadResult !=null && uploadResult.search("success") != -1){
		getSubAlertsDetails(alertId,subAlertId);
	}
}

function setDefaultImage(img){
    img.src = "images/User.png";
}


function viewAlertHistory(alertId)
{
	$("#alertManagementPopupBody1").html(spinner)
	var jsObj ={
		alertId : alertId,
		task:"task"
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
	departmentId = $("#departmentsId").val();
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
					str+='<select  class="chosenSelect assigningDynamicSelectList" attr_dyna_name="'+result[i].name+'" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-6">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					if(tempbuildId =='locationLevelSelectId1')
						str+='<select  class="chosenSelect locationsCls assingLocationCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
					else
						str+='<select  class="chosenSelect locationCls assingLocationCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
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
		$("#assignSuccess").html('Alert Assigned Successfully');
		$("#departmentsId").val(0);
		$("#departmentsId").trigger("chosen:updated");
		$("#locationLevelSelectId").html('');
		$("#locationLevelSelectId").append('<option value="0">Select Level</option>');
		$("#locationLevelSelectId").trigger("chosen:updated");
		$("#designationsId").html('');
		$("#designationsId").append('<option value="0">Select Designation</option>');
		$("#designationsId").trigger("chosen:updated");
		$("#officerNamesId").html('');
		$("#officerNamesId").append('<option value="0">Select Officer</option>');
		$("#officerNamesId").trigger("chosen:updated");
		$("#parentLevelDivId").html('');
		$(".assign-user-body").hide();
		$("#assignSuccess").hide();
		/* setTimeout(function(){              
			location.reload();
		},500); */
	}else{
		alert("Please Try Again.");  
		$("#assignSuccess").addClass("text-danger");
		$("#assignSuccess").html('Try Again');
	}	
}

function getAlertStatusHistory(alertId){
	alertStatusGlobalId = 0;
	
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
var globalEntitlement= "";
function getStatusCompletionInfo(alertId){
	isStatusAvailable=true;
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
		$("#updateStatusChangeBody").html('');
		$("#statusDtlsDiv").html('');
		$('#displayStatusId,#displayAssignIconId,#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority,#historyId').hide();
		$('#displayStatusId').attr('status-icon-block','alertStatus');
		$('#docAttachmentId').hide();	
		
		if(result != null && result.length>0){
			var buildTypeStr = result[0].applicationStatus.split('-')[0].trim();
			globalUserType = buildTypeStr;
			var sttatusId = result[0].applicationStatus.split('-')[1].trim();
			globalStatusId = sttatusId; 
			
			if(result.length  == 1)
				isStatusAvailable=false;
			
			if(result[0].idnameList != null && result[0].idnameList.length > 0)
			{
				var str='';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons"><i class="fa fa-volume-control-phone fa-2x"></i></div>';
					str+='<div class="col-sm-11">';
						str+='<h3>Caller Details </h3>';
						str+='<ul class="list-inline slickSlider">';
						for(var  j in result[0].idnameList)
						{
							str+='<li style="padding:0px 8px;margin:0px 5px;border:1px solid #ddd;">';
								str+='<table class="table table-condensed">';
									str+='<tr>';
										str+='<td>Name</td>';
										str+='<td>: '+result[0].idnameList[j].callerName+'</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Mobile No</td>';
										str+='<td>: '+result[0].idnameList[j].mobileNo+'</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Caller</td>';
										str+='<td>: '+result[0].idnameList[j].userType+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</li>';
						}
						str+='</ul>';
						
					str+='</div>';
				str+='</div>';
				$("#callerDetailsDIv").html(str);
				if(result[0].idnameList.length > 3)
				{
					$('.slickSlider').slick({
						slide: 'li',
						slidesToShow: 3,
						slidesToScroll: 1,
						infinite: false,
						swipe:false,
						touchMove:false,
						variableWidth: false
					});
				}
				
			}
			//srujana
			/*if(result[0].petitionerList != null && result[0].petitionerList.length > 0)
			{
				var str='';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons"><i class="fa fa-volume-control-phone fa-2x"></i></div>';
					str+='<div class="col-sm-11">';
						str+='<h3>Petitioner Details </h3>';
						str+='<ul class="list-inline slickSlider">';
						for(var  j in result[0].petitionerList)
						{
							str+='<li style="padding:0px 8px;margin:0px 5px;border:1px solid #ddd;">';
								str+='<table class="table table-condensed">';
									str+='<tr>';
										str+='<td>Name</td>';
										str+='<td>: '+result[0].petitionerList[j].name+'</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Relative Name</td>';
										str+='<td>: '+result[0].petitionerList[j].relativeName+'</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Age</td>';
										str+='<td>: '+result[0].petitionerList[j].age+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</li>';
						}
						str+='</ul>';
						
					str+='</div>';
				str+='</div>';
				$("#callerDetailsDIv").html(str);
				if(result[0].idnameList.length > 3)
				{
					$('.slickSlider').slick({
						slide: 'li',
						slidesToShow: 3,
						slidesToScroll: 1,
						infinite: false,
						swipe:false,
						touchMove:false,
						variableWidth: false
					});
				}
				
			}*/
			
			/* var buildTypeStr = result[0].applicationStatus.split('-')[0].trim();
			globalUserType = buildTypeStr;
			var sttatusId = result[0].applicationStatus.split('-')[1].trim();
			globalStatusId = sttatusId; */ 
			$('#historyId').show();
			var entitlement = result[0].positionName;
			globalEntitlement = entitlement;
			if(result[0].dueDateStr != null && result[0].dueDateStr.trim().length>0){
				$('.modal-date').html(result[0].dueDateStr)
				$('.modal-date1').html(result[0].dueDateStr)
			}else{
				$('#displayDueDate2').hide();
				$('#displayDueDate1').hide();
			}
			
			if(buildTypeStr=='own'){  
				$('#displayStatusId,#displaySubTaskli,#displaySubTasksliId').show();	
				$('#docAttachmentId').show();	
				$('#displayDueDate1').show();
				$('#displayDueDate2').hide();
				
				
				
				if(globalStatusId == 12 ){ // closed
					isStatusAvailable=false;
					$('#displaySubTasksliId,#docAttachmentId').hide();
				}else {
					isStatusAvailable=true;
				}				
			}
			else if(buildTypeStr=='subUser'){	
				$('#displaySubTasksliId').hide();		
				$('#displayDueDate1').hide();
				$('#displayStatusId').show();
				$('#displayStatusId').removeAttr('status-icon-block');
				
				$('#displayDueDate2,#displayPriority').show();
				
				// closed-12, completed-4, reopen-11
				if( globalStatusId == 12 || globalStatusId == 4 || globalStatusId == 11){
					isStatusAvailable=true;
				}
				if(globalStatusId != 12){ // for not closed status alerts 
					$('#displaySubTasksliId,#docAttachmentId').show();					
				}
			}else if(buildTypeStr=='same'){ 
				$('#displaySubTasksliId,#docAttachmentId,#displayPriority').show();
				$('#displayStatusId').show();       
				$('#displayDueDate1').show(); 
				isStatusAvailable=false;
			}
			else if(buildTypeStr=='other'){
				$('#displaySubTasksliId').hide();				
				$('#displayDueDate1').hide();				
				$('#displayDueDate2').hide();				
				$('#displayStatusId').show();
				isStatusAvailable=false;				
			}
			if((sttatusId == 1  || sttatusId == 8 || sttatusId==9) && result[0].userStatus != null && result[0].userStatus =='admin'){
				$('#displayAssignIconId').show();
				$('#docAttachmentId').show();	
				 assignUser(alertId);
				 
			}
		
			if(result[0].userStatus != null && result[0].userStatus =="admin"){
				isAdmin = "true";
				//$('#displayStatusId').attr('status-icon-block','');
				$('#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority').hide(); 
				if(sttatusId !=1)
					$('#docAttachmentId').show(); 
			}else{
				$('#displayStatusId').attr('status-icon-block','alertStatus');
				isAdmin = "false";
			}
			
			if(isAdmin=='false'){				
				$('#displayStatusId').attr('status-icon-block','alertStatus');
			}
			alertStatus(result,alertId);	

			if(globalStatusId == 12 || globalStatusId ==8 || globalStatusId ==9  ){ // closed || Wrongly Mapped Designation || Wrongly Mapped Department
				$('#displaySubTasksliId,#docAttachmentId,#displayPriority,#displayDueDate2').hide();
				$('#displayDueDate1').show();
			}
			 if(globalStatusId ==8 || globalStatusId ==9){
				$("#departDivId").show();
			} 
			
			//alert(" isStatusAvailable :"+isStatusAvailable);
		}else{
			$('#displayAssignIconId').show();
			$('#displayStatusId').show();
			$('#displaySubTasksliId').hide();  
			$('#docAttachmentId').hide();  
		}	
		setTimeout(function(){
			$("body").addClass("modal-open");
		},1000);
	});
}
var control;
var lang;
function rightSideExpandView(alertId)
{
	$("#rightSideExpandView").html(spinner);
    $(".assignedUser1").html('');
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
							str+='<div class="col-sm-8 pull-right" style="">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									/*  str+='<li data-toggle="tooltip" data-placement="top" title="Departments" id="departDivId" style="display:none;">';
										str+='<span class=""></span><span id="mainDeprtmntId" attr_alert_id="'+alertId+'"><i class="fa fa-empire" aria-hidden="true"></i></span>';
									str+='</li>'; */
									
									str+='<li status-icon-block="alertStatus" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon" id="statusIdColor"></span><span id="statusId">Pending</span>';
									str+='</li>';
									
									str+='<li data-toggle="tooltip" data-placement="top" title="Present Proposal Status" id="proposalId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon"></span><span id="presntPrposalstatusId"></span>';
									str+='</li>';
								
									str+='<li data-toggle="tooltip" data-placement="top" title="Present Rejoinder Status" id="rejoinderId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon"></span><span id="presntRejoinderstatusId"></span>';
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
										str+='<i attr_class="alert-status-attachment" class="glyphicon glyphicon-remove pull-right attachmentCloseCls" ></i>';
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/>';
											str+='<input type="hidden" name="alertId" value="'+alertId+'" subAlertId=""  id="alertHiddenId"/>';
											str+='<button class="btn btn-primary btn-sm text-capital uploadBtnIdCls" attr_alert_id="'+alertId+'" type="button" id="uploadBtnId" subAlertId="" >upload</button>';
											str+='<span id="ErrorMsg"></span>';
											str+='<span id="imagesUploadSpinner" class="imagesUploadSpinner" style="height:50px;width:50px"></span>';
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
								str+='<div class="assignedUser1"></div>';
							str+='</div>';
							str+='<div class="col-sm-8  pull-right" >';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									str+='<li status-icon-block="alertStatus1" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId1" style="" > ';
										str+='<span class="status-icon arrow-icon" id="statusId1Color"></span><span id="statusId1">Pending</span>';
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
										str+='<i attr_class="alert-status-attachment" class="glyphicon glyphicon-remove pull-right attachmentCloseCls" ></i>';
											str+='<input type="file" name="imageForDisplay" class="form-control m_top20" id="imageId"/>';
											str+='<input type="hidden" name="subTaskId" value="'+alertId+'" subAlertId=""  id="alertHiddenId"/>';
											str+='<button class="btn btn-primary btn-sm text-capital uploadBtnIdCls " attr_alert_id="'+alertId+'" type="button" id="uploadBtnId" subAlertId=""  >upload</button>';
											str+='<span id="ErrorMsg1"></span>';
											str+='<span id="imagesUploadSpinner" class="imagesUploadSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
										str+='</form>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div id="main_alert_block">';
						str+='<div class="panel-body" >';
							str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
							str+='</p>';
							str+='<div id="callerDetailsDIv"></div>';
							str+='<div id="statusDtlsDiv"></div>';
							str+='<div id="alertDetails"></div>';
							str+='<div id="articleAttachment"></div>';
							str+='<div id="alertCategory"></div>';
							str+='<div id="rejoinderAttachments"></div>';
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
											str+='<label class="radio-inline" style="margin-bottom: 5px;">';
												str+='<input type="radio" value="te" name="language1" class="lang" id="telugu1" checked="true" onclick="languageChangeHandler1();"/>Telugu';
											str+='</label>';
											str+='<label class="radio-inline" style="margin-bottom: 5px;">';
												str+='<input type="radio" value="en" name="language1" class="lang" id="eng1" onclick="languageChangeHandler1();"/>English';
											str+='</label>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<div class="comment-area">Comment Here</div>';
											str+='<textarea class="form-control comment-area" id="alertCommentId1" placeholder="Comment here..."></textarea>';
										str+='</div>';
										str+='<div class="panel-footer text-right">';
											str+='<button class="btn btn-primary comment-btn commentChangeCls" attr_alert_id="'+alertId+'"  subAlertId="" id="commentChangeId">Save  </button>';
											str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				
				
				str+='<div id="sub_tasls_View_alert_block" style="display:none">';
						str+='<div id="mainAlertTitle"></div>';
						str+='<div class="panel-body">';
							//str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								//str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH </span></span>';
							str+='</p>';
							str+='<div id="subAlertDetails"></div>';
							str+='<div id="subArticleAttachment"></div>';
							str+='<div id="subAlertComments"></div>';
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
											str+='<label class="radio-inline" style="margin-bottom: 5px;">';
												str+='<input type="radio" value="te" name="language" class="lang" id="telugu" checked onclick="languageChangeHandler();"/>Telugu';
											str+='</label>';
											str+='<label class="radio-inline" style="margin-bottom: 5px;">';
												str+='<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English';
											str+='</label>';
											//str+='<p><label class="radio-inline"><input type="radio" name="commentLang"/>English</label><label class="radio-inline"><input type="radio" name="commentLang"/>Telugu</label></p>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<div class="comment-area">Comment Here</div>';
											str+='<textarea class="form-control comment-area" id="alertCommentId2" placeholder="Comment here..."></textarea>';
										str+='</div>';
										str+='<div class="panel-footer text-right">';
											str+='<button class="btn btn-primary comment-btn commentChangeCls" attr_alert_id="'+alertId+'"   subAlertId=""  id="commentChangeId">Save  </button>';
											str+='<span id="commentPostingSpinner" style="height:50px;width:50px"></span>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div id="sub_task_block"  class="sub_task_block" style="display:none;" >';
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
									  str+='<input type="text" id="subTaskTitleId" class="form-control subTaskTitle" placeholder="Enter Title Here" name="alertAssigningVO.title"/>';
									str+='</div>';
									str+='<div class="col-sm-4">';
									str+='<ul class="list-icons list-inline pull-right">';
										str+='<li class="list-icons-calendar" style="display:table-cell">';
											str+=' <i class="glyphicon glyphicon-calendar"></i><span  data-toggle="tooltip" data-placement="top" title="due date" class="modal-date2 subTaskDueDate" style="" > Due Date </span>';
											str+='<input id="hiddenDueDate1" type="hidden" value="" name="alertAssigningVO.dueDate" />';
										str+='</li>';
										str+='<li style="display:table-cell">';
											str+='<div class="assign-user">';
											str+='<div class="departmentsForSubTask" attr_alert_id="'+alertId+'" onclick="getAssignUIAttributes('+alertId+');"><i class="glyphicon glyphicon-user pointerCls assigningCls"></i> </div></div><div id="assignUIAttributesId" > </div>';
										str+='</li>';
									str+='</ul>';
									str+='</div>';
								  str+='</div>';
								str+='</li>';
								str+='</form>';
							  str+='</ul>';
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
	//google.setOnLoadCallback(onLoad);
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
	getCommentsForAlert(alertId);
	alertDeptmentExistInLogin(alertId);


	lang = $("input[name=language]:checked").val();
	var options = {
		sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
		destinationLanguage:[''+lang+''],
		shortcutKey: 'alt+t',
		transliterationEnabled: true
	};
	// Create an instance on TransliterationControl with the required options.
	control = new google.elements.transliteration.TransliterationControl(options);
	// Enable transliteration in the textbox with id 'descrptionId'.
	if ($('#alertCommentId2').length){
		control.makeTransliteratable(['alertCommentId2']);
	}
	if ($('#alertCommentId1').length){
		control.makeTransliteratable(['alertCommentId1']);
	}
}

function buildAssignUIAttributes(alertId){
	var str='';
	str+='<div class="assign-subtask" style="width:600px;position:absolute;right:0;top:33px">';
		str+='<div class="arrow_box_top" >';
			str+='<div>';
				str+='<div class="row">';  
					str+='<div class="col-sm-12">';
						str+='<i attr_class="assign-subtask" class="glyphicon glyphicon-remove pull-right closeCls" ></i>';
						str+='<div id="assignErrorDivId1" style="color:red;"></div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId1"></span></label>';
						str+='<select class="chosenSelect" id="assignDepartmentId1">';
						str+='</select>'; 
					str+='</div>';
					str+='<div class="col-sm-6">';
								str+='<label>Sub Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="departmentsId1" name="alertAssigningVO.departmentId">	';
									str+='<option value="0">Select Sub Department</option>';
									//str+='<option value="49">RWS</option>';
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


function getSubAlertsDetails(alertId,subAlertId){
	$('#main_alert_block').hide();
	$("#impactLevel,#priorityBodyId,#displaySubTasksliId,#displayDueDate1,#displayPriority,#mainBlockStates").hide();
	$('#sub_tasls_View_alert_block,#subAlertDetails,#subBlockStates,#displayDueDate3,#displayStatusId1').show();
	$('.commentChangeCls').attr('subalertid',''+subAlertId+'');
	$('#docAttachmentId1').attr('subalertid',''+subAlertId+'');
	$('#uploadBtnId').attr('subalertid',''+subAlertId+'');
	getSubTaskFullDetailsAction(subAlertId,alertId);
	getSubTaskCommetDtls(subAlertId,alertId);
	
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
function getSubTaskCommetDtls(subAlertId,alertId){
	$("#alertGeneralComments").html(spinner);
	var jsObj ={
		alertId  :subAlertId,
		task:"subTask"  
	}
	$.ajax({
		type:'GET',
		url: 'viewAlertHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertGeneralComments").html('');
		if(result != null && result.length > 0)
		{
			buildSubTaskCommetDtls(result);
		}else{
			$("#alertGeneralComments").html("NO DATA");
		}
	});
}	

var subTaskglStr='';
var subTaskglPropasalStr='';
var subTaskStatusChangAvailable=true;
function buildSubTaskAlertDataNew(result,alertId,subAlertId)
{
	
	var str='';
	var str1='';
	subTaskStatusChangAvailable=true;
	$('#docAttachmentId1').show();
	str+='<div class="row m_top20">';
		for(var i in result)
		{
			if(i==0){
				
				var subTaskBuildType=result[0].userType;
				var statusId = result[0].statusId;	
				$('#displayStatusId1').attr('subalertid',subAlertId);
				//alert(" subTaskBuildType :"+subTaskBuildType)
				if(subTaskBuildType=="other"){
					$('#docAttachmentId1').hide();
					subTaskStatusChangAvailable=false;
				}
				else if(subTaskBuildType=='assignedTo'){
					if(statusId != 7 )
						subTaskStatusChangAvailable=true;
					else
						subTaskStatusChangAvailable=false;	
				}
				else if(subTaskBuildType=='assignedBy'){	
					if( statusId == 3 || statusId == 6 || statusId == 7 )// 3 - completed, 6-
						subTaskStatusChangAvailable=true;
					else
						subTaskStatusChangAvailable=false;					
				}  
				
				
				$("#statusId1Color").attr('style','background-color:'+result[0].color+'');
				$("#statusId1").html(result[0].status);
				$(".modal-date1").html(result[0].dueDateStr);
				
					var str='';
					var str6='';
					var splitNameArr = result[0].name.split(" ");
					var value = "";
					if(splitNameArr != null && splitNameArr.length>1)
						value = splitNameArr[1];
					else
						value = splitNameArr[0];  
					
					str6+='<div class="media">';
					if(result[0].assignedOfficerStr != null){
						str6+='<div class="media-left">';
						str6+='<span class="icon-name icon-primary">'+result[0].assignedOfficerStr.substring(0,2)+'</span>';
						str6+='</div>';
					}
						str6+='<div class="media-body">';
							str6+='<p> SUB TASK ASSIGN TO: <i class="fa fa-level-down "></i></p> ';
							//str6+='<p>'+result[0].assignedOfficerStr+' - '+result[0].deptName+'</p>';
						if(result[0].assignedOfficerStr != null)
							str6+='<p>'+result[0].assignedOfficerStr+'</p>';
							str6+='<p><i class="glyphicon glyphicon-phone"></i> '+result[0].mobileNo+'</p>';
							str6+='<p><b>Location :</b>'+result[0].callerName+'</p>';
							str6+='<p><b> Dept:</b> '+result[0].boardName+'</p>';
							//str6+='<p><b>Location :</b>'+result[0].positionName+'</p>';
							str6+='<p></p>';
						str6+='</div>';
					str6+='</div>';
					$(".assignedUser1").html(str6);
					
				str="";
				str+='<div class="col-sm-1 text-center body-icons">';
				str+='<i class="fa fa-check fa-2x"></i>';
				str+='</div>';
				str+='<div class="col-sm-11">';
					str+='<h3> <u> Sub Task </u> : '+result[i].title+'</h3>';
					str+='<p class="m_top10">'+result[i].description+'</p>';
					
					str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Created Date : '+result[i].dateStr+'</small>';
					str+='&nbsp;&nbsp;<small><i class="fa fa-calendar"></i> Due Date  : '+result[i].dueDateStr+'</small></p>';
					if(result[i].assignedByOfficerStr != null && result[i].assignedByOfficerStr.length > 0)
					{
						str+='<b> Assigned By: </b>'+result[i].assignedByOfficerStr+'&nbsp;&nbsp;&nbsp;';
					}
					if(result[i].deptName != null && result[i].deptName.length > 0)
					{
						str+='<i class="glyphicon "></i><b> Dept Name: </b>'+result[i].deptName+'</br>';
					}
					if(result[i].designation != null && result[i].designation.length > 0)
					{
						str+='<i class="glyphicon"></i><b> Designation :</b>'+result[i].designation+'&nbsp;&nbsp;&nbsp;';
					}
					/*if(result[i].mobileNO != null && result[i].mobileNO.length > 0)
					{
						str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].mobileNO+'</p>';
					}
					*/
					if(result[i].positionName != null && result[i].positionName.length > 0)
					{
						str+='<i class="glyphicon"></i><b> Location : </b>'+result[i].positionName+'</p>';
					}
				str+='</div>';
				
				str1='';
				
				if(result[i].subList != null && result[i].subList.length>0){
					str1+='<div class="row m_top20">';
						str1+='<div class="col-sm-1 text-center body-icons">';
							str1+='<i class="fa fa-paperclip fa-2x"></i>';
						str1+='</div>';
						str1+='<div class="col-sm-11">';
							str1+='<h4 class="text-muted text-capital"> Sub Task Attachments:  </h4>';	
							str1+='<div class="row">';
							for(var k in result[i].subList){
								str1+='<div class="col-sm-3">';
									str1+='<img class="displayImgCls img-responsive m_top20" attr_articleId="" src="../images/'+result[i].subList[k]+'" style="width: 100px; height: 100px;cursor:pointer"/>';
								str1+='</div>';
							}
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
				}
				$("#subArticleAttachment").html(str1);
				/* if(result[i].commentList != null && result[i].commentList.length>0){    
					str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-comments-o fa-2x"></i>';
					str+='</div>';
					str+='<div class="col-sm-11">';
						str+='<h4 class="text-muted text-capital m_top10">Sub Tasks Comments</h4>';	
						for(var k in result[i].commentList){
							str+='<div class="media">';	
							str+='<div class="media-left">';
								if(result[i].commentList[k].userName != null && result[i].commentList[k].userName.length > 0)
								{
									str+='<span class="icon-name icon-primary text-capital">'+result[i].commentList[k].userName.substring(0,2)+'</span>';
								}else{
									str+='<span class="icon-name icon-primary">ME</span>';
								}
								str+='</div>';
								str+='<div class="media-body">';
											if(result[i].commentList[k].comment != null && result[i].commentList[k].comment.length > 0)
											{
												str+='<p class="m_top5">'+result[i].commentList[k].comment+'</p>';
											}
											
											if(result[i].commentList[k].date != null && result[i].commentList[k].date.length > 0)
											{
												str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].commentList[k].date+'</p>';
											}
											if(result[i].commentList[k].userName != null && result[i].commentList[k].userName.length > 0)
											{
												//str+='<p class="m_top5"> Updated By: '+result[i].commentList[k].userName+'</p>';
												str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].commentList[k].userName+' </span></p>';
											}
											if(result[i].commentList[k].designation != null && result[i].commentList[k].designation.length > 0)
											{
												//str+='<p class="m_top5"><i class="glyphicon"></i> Designation :'+result[i].commentList[k].designation+'</p>';
												str+='<p class="text-primary text-capitalize">Designation: <span style="color:black;">'+result[i].commentList[k].designation+' </span></p>';
											}
											if(result[i].commentList[k].location != null && result[i].commentList[k].location.length > 0)
											{
												//str+='<p class="m_top5"><i class="glyphicon"></i> Location :'+result[i].commentList[k].location+'</p>';
												str+='<p class="text-primary text-capitalize">Location: <span style="color:black;">'+result[i].commentList[k].location+' </span></p>';
											}
											if(result[i].commentList[k].deptName != null && result[i].commentList[k].deptName.length > 0)
											{
												//str+='<p class="m_top5"><i class="glyphicon "></i> Dept Name: '+result[i].commentList[k].deptName+'</p>';
												str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;">'+result[i].commentList[k].deptName+' </span></p>';
											}
									str+='</div>';
							str+='</div>';
						}
					str+='</div>';
				str+='</div>';
				
				} */			
				str1="";
				str1+='<div class="panel-body" style="font-weight:bold;font-size:15px"> <i class="fa fa-long-arrow-left fa-2x " style="cursor:pointer;margin-right:15px;margin-top:5px" aria-hidden="true" expand-icon="block1" attr_alertId="'+alertId+'" title="Back to Main Alert View."></i>  <span style="margin-top:-5px">';
				if(result[i].description.length>80)
					str1+=''+result[i].mainTitle +'... </span></div>';
				else
					str1+=''+result[i].mainTitle+'... </span></div>';
				
				$("#mainAlertTitle").html(str1);
					
				
				
			}	

				subTaskglStr='';
				subTaskglPropasalStr='';
				var str1='';
				// var str2='';
					str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body">';
						str1+='<div class="panel-heading">';
							str1+='<div class="row">';
								for(var i in result)
								{
									
									str1+='<div class="col-sm-4">';
										str1+='<div class="radioStyling">';
									
										if(result[0].status != null && result[0].status.trim() ==result[i].name.trim())
										{
											str1+='<input type="radio" value="'+result[i].id +'" name="statusChange" checked id="radio-'+result[i].id+'"/>';
										}else{
											str1+='<input type="radio" value="'+result[i].id +'" name="statusChange" id="radio-'+result[i].id+'"/> ';
										}
										str1+='<label class="radio-inline" for="radio-'+result[i].id+'"><span class="radio">'+result[i].name+'</span></label>';
										str1+='</div>';
									str1+='</div>';
								}
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="panel-body pad_0">';
							str1+='<textarea class="form-control" id="updateStatusChangeComment1" placeholder="Comment.."></textarea>';
						str1+='</div>';
					str1+='</div>';
					str1+='<button class="btn btn-primary btn-sm text-capital" subTaskId="'+subAlertId+'" attr_alert_id="'+alertId+'" id="updateStatusChange">update</button>';
					str1+='<span id="updateStatusChangeAjaxSymbol"></span>';
					str1+='<span id="updateStatusChangeMsg"></span>';
				
				subTaskglStr=str1;
				//subTaskglPropasalStr=str2;
		}
	str+='</div>';
		$("#subAlertDetails").html(str);
}





function saveSubTask(mainAlertId){
	
	if($(".subTaskTitle").val() == null || $(".subTaskTitle").val() == "")
	{
		$("#assignErrorDivId1").html("Please enter title");
		return;
	}
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
			if(result[0].categoryId == 5){
				buildSocialMediaImage(result[0].documentList);
			}
		}else{
			$("#alertDetails").html("NO DATA AVAILABLE...");
		}
	});
}
  
  var globalProposalStatus;
  var globalRejoinderStatus;
function buildAlertDataNew(result)
{
	var str='';
	var str1='';
	$("#statusId").html(result[0].status);
	if($("#displayStatusId #statusId").html() == 'Proposal'){
		$("#proposalId").show();
		globalProposalStatus = result[0].committeeName;
		$("#presntPrposalstatusId").html(result[0].committeeName);
	}
	if($("#displayStatusId #statusId").html() == 'Rejoinder'){
		$("#rejoinderId").show();
		globalRejoinderStatus = result[0].comment;
		$("#presntRejoinderstatusId").html(result[0].comment);
		//globalRejoinderStatus = result[0].comment;
	}
	$("#impactLevel").html(result[0].regionScope);
	if(result[0].severity != null)
	{
		$("#priorityBodyId").html(result[0].severity);
	}
	$("#statusIdColor").css("background-color",result[0].statusColor);
	if(result[0].dueDate != null && result[0].dueDate.length>0)
	{
		$('.modal-date').data('daterangepicker').setStartDate(result[0].dueDate);
		$('.modal-date').data('daterangepicker').setEndDate(result[0].dueDate);
		if(result[0].dueDate != null && result[0].dueDate.length>0){
			$('.modal-date').html(result[0].dueDate);
			$('.modal-date1').html(result[0].dueDate);
		}
	}else{
			$('#displayDueDate2').hide();
			$('#displayDueDate1').hide();
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
	/*str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-paperclip fa-2x"></i>';
		str+='</div>';*/
		str+='<div class="col-sm-11">';
			for(var i in result.documentList)
			{
				str+='<img class="articleDetailsCls img-responsive m_top20" src="../Reports/'+result.documentList[i]+'" style="width: 150px; height: 150px;cursor:pointer"/>';
			}
		str+='</div>';
	str+='</div>';
	str1+='<div class="row m_top20">';
	
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			if(result[i].imageUrl != null){
				str1+='<div class="col-sm-4">';
					str1+='<h4 class="text-muted text-capital">article attachment</h4>';
					str1+='<img class="articleDetailsCls img-responsive m_top20" attr_articleId='+result[i].alertCategoryTypeId+' src="../NewsReaderImages/'+result[i].imageUrl+'" style="width: 150px; height: 150px;cursor:pointer"/>';
				str1+='</div>';
				str1+='<div class="col-sm-7" id="existingDocsDivId"></div>';
			}else{
				str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
			}
		}else{
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
		}
		
	str1+='</div>';
	
	var str2='';
	
	str2+='<div class="row m_top20">';
	
		if(result[i].rejinderDocList !=null && result[i].rejinderDocList.length>0){
			str2+='<div class="col-sm-1 text-center body-icons">';
				str2+='<i class="fa fa-reply-all fa-2x"></i>';
			str2+='</div>';
			str2+='<div class="col-sm-11">';
				str2+='<div class="bg_EE">';
					for(var j in result[i].rejinderDocList)
					{
						str2+='<div class="media">';
							str2+='<div class="media-left">';
							if(j == 0){
								str2+='<i class="fa fa-commenting-o fa-2x" aria-hidden="true" style="color:orange"></i>';
							}else if(j == 1 && result[i].rejinderDocList[j].subList1 != null && result[i].rejinderDocList[j].subList1.length > 0 ){
								str2+='<i class="fa fa-commenting fa-2x" aria-hidden="true" style="color:red"></i>';
							}
							str2+='</div>';
							str2+='<div class="media-body">';
								if(j == 0 && result[i].rejinderDocList[j].subList1 != null){
									str2+='<p><span style="color:orange">'+result[i].rejinderDocList[j].name+'</span></p>';
								}else if(j == 1 && result[i].rejinderDocList[j].subList1 != null && result[i].rejinderDocList[j].subList1.length > 0 ){
									str2+='<p><span style="color:red">'+result[i].rejinderDocList[j].name+'</span></p>';
								}
								
								for(var k in result[i].rejinderDocList[j].subList1)
								{
									 var nameArr = result[i].rejinderDocList[j].subList1[k].name.split('/');
									 var name = nameArr[3];
									str2+='<p><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;&nbsp;<a href="http://www.mytdp.com/images/"'+result[i].rejinderDocList[j].subList1[k].name+' style="cursor:pointer;">'+name+'</a>&nbsp;&nbsp;(<i class="fa fa-calendar" aria-hidden="true"></i> &nbsp;'+result[i].rejinderDocList[j].subList1[k].date1+')</p>';
								}
								
							str2+='</div>';
						str2+='</div>';
					}
					
				str2+='</div>';
			str2+='</div>';
		}
		
	str2+='</div>';
	
	$("#alertDetails").html(str);
	$("#articleAttachment").html(str1);
	$("#rejoinderAttachments").html(str2);
	
}
function buildSocialMediaImage(result){
	$("#existingDocsDivId").html('');
	var str='';
	
	if(result !=null && result.length>0){
		str+='<ul class="list-inline imageShowOpen">';
			for(var i in result){
				str+='<span style="background-color: gray; display: inline-block; border-radius: 5px; height: 8px; width: 8px;"></span><li class="" style="margin-top:25px;" attr_doc_id="'+i+'"  attr_path="'+result[i]+'">';
					str+='<img src="../images/'+result[i]+'" style="width: 100px; height: 100px;cursor:pointer" />';
					//str+='<a target="_blank" href="http://www.mytdp.com/images/'+result[i].name+'" style="cursor:pointer;">'+result[i].name+'</a>';
				str+='</li>&nbsp;&nbsp;';
			}
		str+='</ul>';
		}
		setTimeout(function(){
			$("#existingDocsDivId").html(str);	
		},1000);
		
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
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
	}).then(function(result){
		//$("#alertDetails").append(str);
	});
}

var globalSubTaskDeptId=0;
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
				globalSubTaskDeptId=result[i].id;
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
	
		
		for(var i in result)
		{
			if(result[i].attachementsList != null && result[i].attachementsList.length>0){
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-level-down fa-2x"></i>';
					str+='</div>';
					
					str+='<div class="col-sm-11">';
						str+='<h4 class="text-muted text-capital"> My Sub Tasks : </h4>';
						str+='<ul class="assign-subtask-list m_top20">';
						for(var k in result[i].attachementsList){
							str+='<li class="assigned subTaskCls " style="cursor:pointer;margin-left: 5px" attr_sub_alert_Id="'+result[i].attachementsList[k].alertId+'" attr_alert_id="'+alertId+'">';
									str+='<div class="row">';
										str+='<div class="col-sm-1">';
											str+='<i class="glyphicon glyphicon-ok"></i>';
										str+='</div>';
										str+='<div class="col-sm-10" >';
											str+='<p>'+result[i].attachementsList[k].title+' ';
											
											str+='</p>';
											str+='<small class="pull-right">DEPT : <span style="color: #60bbfd;">'+result[i].attachementsList[k].deptName+'</span> DESIGNATION : <span style="color: #60bbfd;">'+result[i].attachementsList[k].designation+'</span> Location : <span style="color: #60bbfd;">'+result[i].attachementsList[k].location+'</span> </small>';
										str+='</div>';
										str+='<div class="col-sm-1">';
											str+='<span class="icon-name icon-primary" style="background-color: '+result[i].attachementsList[k].color+'" title="'+result[i].attachementsList[k].status+'"></span>';
											/* str+='<ul class="list-icons list-inline">';
												str+='<li> <span class="status-icon arrow-icon" id="statusIdColor" style="background-color: '+result[i].attachementsList[k].color+'" title="'+result[i].attachementsList[k].status+'"></span> </li>';
											str+='</ul>'; */
										str+='</div>';
									str+='</div>';
							str+='</li>';
						}
						str+='</ul>';
					str+='</div>';
				str+='</div>';
				
			}
			if(result[i].commentList != null && result[i].commentList.length>0){
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-level-down fa-2x"></i>';
					str+='</div>';
				str+='<div class="col-sm-11 ">';
					str+='<h4 class="text-muted text-capital"> Sub Tasks involved : </h4>';
					str+='</div>';
					str+='<div class="col-sm-11 col-sm-offset-1">';
						str+='<ul class="assign-subtask-list m_top20">';
						for(var k in result[i].commentList){
							str+='<li class="assigned subTaskCls " style="cursor:pointer;" attr_sub_alert_Id="'+result[i].commentList[k].alertId+'" attr_alert_id="'+alertId+'">';
									str+='<div class="row">';
										str+='<div class="col-sm-1">';
											str+='<i class="glyphicon glyphicon-ok"></i>';
										str+='</div>';
										str+='<div class="col-sm-10" >';
											str+='<p>'+result[i].commentList[k].title+'';
											
											str+='</p>';
											str+='<small class="pull-right">DEPT : <span style="color: #60bbfd;">'+result[i].commentList[k].deptName+'</span> DESIGNATION : <span style="color: #60bbfd;">'+result[i].commentList[k].designation+'</span> Location : <span style="color: #60bbfd;">'+result[i].commentList[k].location+'</span> </small>';
										str+='</div>';
										str+='<div class="col-sm-1">';
											str+='<span class="icon-name icon-primary" id="statusIdColor" style="background-color: '+result[i].commentList[k].color+'"  title="'+result[i].commentList[k].status+'"></span>';
											/* str+='<ul class="list-icons list-inline">';
												str+='<li> <span class="status-icon arrow-icon" id="statusIdColor" style="background-color: '+result[i].commentList[k].color+'"  title="'+result[i].commentList[k].status+'"></span> </li>';
											str+='</ul>'; */
											//str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
										//	str+='<span class="icon-name icon-primary"></span>';
											//str+='<span class="label label-default">...</span>';
										str+='</div>';
									str+='</div>';
							str+='</li>';
						}
						str+='</ul>';
					str+='</div>';
				
			}
		}	
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
	$("#alertGeneralComments").html(spinner);
	var jsObj ={
		alertId  :alertId,
		task:"task"
	}
	$.ajax({
		type:'GET',
		url: 'viewAlertHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$("#alertGeneralComments").html('');
		if(result != null && result.length > 0)
		{
			buildCommentsForAlert(result);
		}else{
			$("#alertGeneralComments").html("NO DATA");
		}
	});
}
 var globalPropCategory;
var	globalPropReqAunt;	
var	globalPrposalCategoryId;	
function buildCommentsForAlert(result)
{
	/*var str='';
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
							//str+='<p class="m_top5"> Updated By: '+result[i].userName+'</p>';
							str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].userName+' </span></p>';
						}
						if(result[i].designation != null && result[i].designation.length > 0)
						{
							//str+='<p class="m_top5"><i class="glyphicon"></i> Designation :'+result[i].designation+'</p>';
							str+='<p class="text-primary text-capitalize">Designation: <span style="color:black;">'+result[i].designation+' </span></p>';
						}
						if(result[i].location != null && result[i].location.length > 0)
						{
							//str+='<p class="m_top5"><i class="glyphicon"></i> Location :'+result[i].location+'</p>';
							str+='<p class="text-primary text-capitalize">Location: <span style="color:black;">'+result[i].location+' </span></p>';
						}
						if(result[i].deptName != null && result[i].deptName.length > 0)
						{
							//str+='<p class="m_top5"><i class="glyphicon "></i> Dept Name: '+result[i].deptName+'</p>';
							str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;">'+result[i].deptName+' </span></p>';
						}
						/*if(result[i].mobileNO != null && result[i].mobileNO.length > 0)
						{
							str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].mobileNO+'</p>';
						}
						*/
					/*str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#alertGeneralComments").html(str);*/
	
	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-road fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital">complete alert history</h4>';
		str+='<ul class="alert-myfoot m_top10" style="list-style:outside none none">';
		for(var i in result){
			
			str+='<li>';
			str+='<span class="alert-history-date"  style="background-color: lightpink;padding: 3px;border-radius: 5px;" >'+result[i][0].trackingDate+'</span>';
			for(var j in result[i]){
					if(result[i][j].actionType == 'Assigning'){     
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Assigned BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>    </p>';
						
					}else if(result[i][j].actionType == 'Attachment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						str+='<p><span class="alert-history-body text-capital"><a target="_blank"  href="../images/'+result[i][j].document+'" width="25%" style="margin-left: 25px;" class="m_top5" >'+result[i][j].document+'</a></span></p>';       
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">'+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';     
					}else if(result[i][j].actionType == 'Due Date'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Changed Date </span> : '+result[i][j].dueDate+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';  
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
						
					}else if(result[i][j].actionType == 'Priority'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Priority </span> : '+result[i][j].severty+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Status Change'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						
						 str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Status </span> :';
						if(result[i][j].status == 'Proposal'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Status </span> :'+result[i][j].proposalStatus+'</p>';
								globalPrposalCategoryId = result[i][j].categoryId;
							 if(result[i][j].categoryId == 1 ){
								 globalPropCategory = result[i][j].category;
								globalPropReqAunt = result[i][j].amount;
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'';
								if(result[i][j].proposalStatus == 'Proposal Accept'){
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/-';
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px">Approved Amount </span> :'+result[i][j].approvedAmount+'/-</p>';
								}else{
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/- </p>';
								}
							}else{
								globalPropCategory = result[i][j].category;
								globalPropReqAunt = result[i][j].amount;
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'</p>';
							}
						}else if(result[i][j].status == 'Rejoinder'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Rejoinder Status </span> :'+result[i][j].rejinderStatus+'</p>';
						}else {
							str+=''+result[i][j].status+'</p>';
						} 
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Comment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Feedback Status'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						if(result[i][j].alertFeedbackStatus != null && result[i][j].alertFeedbackStatus != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Feed back Status </span>: '+result[i][j].alertFeedbackStatus+'</p>'; 
						}
						if(result[i][j].alertCallerName != null && result[i][j].alertCallerName != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Caller Name </span>: '+result[i][j].alertCallerName+'</p>';
						}
						if(result[i][j].status != null && result[i][j].status != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Alert Status </span>: '+result[i][j].status+'</p>';
						}
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}
			}
		str+='</li>';	
		
		}
		str+='</ul>';
	str+='</div>';
	//$("#alertManagementPopup1 .modal-dialog").css("width","60%")
	$("#alertGeneralComments").html(str);
//}
}


function getTotalArticledetails(articleId){
	
	$("#alertManagementPopupBody1,#alertManagementPopupHeading").html(spinner);
	$.ajax({
		  type : 'GET',      
		  	url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
			//url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
	}).then(function(results){
		var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","CORP-GMC","Ward","NATIONAL","INTERNATIONAL","MUNICIPALITY"];
		var result = results[0];
		var str = '';
		var heading = '';
		heading+='<h4 class="modal-title" id="myModalLabel">';
			heading+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
			heading+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
		heading+='</h4>';
		str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<img class="mainImage"  src="../NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
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
											str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="../NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
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
				str+='<span style="background-color: gray; display: inline-block; border-radius: 5px; height: 8px; width: 8px;"></span><li class="" style="margin-top:25px;" attr_doc_id="'+result[i].id+'"  attr_path="'+result[i].name+'" id="imageAttachmentOpen'+result[i].id+'" >';
					str+='<img src="../images/'+result[i].name+'" style="width: 100px; height: 100px;cursor:pointer" />';
					//str+='<a target="_blank" href="http://www.mytdp.com/images/'+result[i].name+'" style="cursor:pointer;">'+result[i].name+'</a>';
				str+='</li>&nbsp;&nbsp;';
				
			}
			str+='</ul>';
			$("#existingDocsDivId").html(str);
		}
    });
}

var printMediaFilters = [];
var electronicMediaFilters = [];
var scopesListFilters = [];
function popUpFilter(type,result,statusName,statuscount,statusId,departmentId,alertTaskType,statusIdsMulti)
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
					str1+='<li class="filters-icon"><i class="glyphicon glyphicon-filter "></i></li>';
					str1+='<li data-dismiss="modal" class="closeFirstModal" aria-label="Close"><i class="glyphicon glyphicon-remove"></i></li>';
				str1+='</ul>';
				str1+='<ul class="flipview-btn filtersSwitch pull-right" style="border:1px solid #ddd;">';
					str1+='<li class="active">Classic</li>';
					str1+='<li>Premium</li>';
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
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="locLevel"><b>Location Level</b></h5>';
						str+='<ul class="filters-list" filters-list="locLevel">';
							str+='<li attr_level="state" attr_levelVal="statesLevel" attr_id="1">STATE</li>';
							str+='<li attr_level="district" attr_levelVal="districtsLevel" attr_id="2">DISTRICT</li>';
							str+='<li attr_level="constituency" attr_levelVal="consLevel" attr_id="3">CONSTITUENCY</li>';
							str+='<li attr_level="mandal" attr_levelVal="mandalLevel" attr_id="4">MANDAL</li>';
							str+='<li attr_level="panchayat" attr_levelVal="panLevel" attr_id="5">PANCHAYAT</li>';
							str+='<li attr_level="village" attr_levelVal="villageLevel" attr_id="6">VILLAGE</li>';
						str+='</ul>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border" filters-list-title="statesLevel">';
						str+='<h5 class="text-capitalize" ><b>State Level</b></h5>';
						str+='<div class="scroller-state">';
							str+='<ul class="filters-list" filters-list="statesLevel">';
								str+='<li attr_id="1">ANDHRA PRADESH</li>';
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border" filters-list-title="districtsLevel">';
						str+='<h5 class="text-capitalize m_top10" ><b>District Level</b></h5>';
						str+='<div class="scroller-district">';
							str+='<ul class="filters-list" filters-list="districtsLevel"></ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border" filters-list-title="consLevel">';
						str+='<h5 class="text-capitalize m_top10" ><b>Constituency Level</b></h5>';
						str+='<div class="scroller-cons">';
							str+='<ul class="filters-list" filters-list="consLevel"></ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border" filters-list-title="mandalLevel">';
						str+='<h5 class="text-capitalize m_top10" ><b>Mandal Level</b></h5>';
						str+='<div class="scroller-mandal">';
							str+='<ul class="filters-list" filters-list="mandalLevel"></ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="col-sm-12">';
					str+='<div class="block-border" filters-list-title="panLevel">';
						str+='<h5 class="text-capitalize m_top10" ><b>Panchayat Level</b></h5>';
						str+='<div class="scroller-panchayat">';
							str+='<ul class="filters-list" filters-list="panLevel"></ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border" filters-list-title="villageLevel">';
						str+='<h5 class="text-capitalize m_top10" ><b>Village Level</b></h5>';
						str+='<div class="scroller-village">';
							str+='<ul class="filters-list" filters-list="villageLevel"></ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="alertStatus"><b>alert status</b></h5>';
						str+='<div class="scroller-alertStatus">';
							str+='<ul class="filters-list" filters-list="alertStatus">';
								str+='<li attr_id="All">All</li>';
								if(alertTaskType == 'alertSource'){
									for(var i in result.alertStatusList){
										if(statusIdsMulti !=null && statusIdsMulti.length>0){
											for(var k in statusIdsMulti){
												if(result.alertStatusList[i].id == statusIdsMulti[k]){
													str+='<li class="active" attr_id='+result.alertStatusList[i].id+'>'+result.alertStatusList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
												}
											}
											
										} 
									}
									
									var differenceSttausIds=[];
									var a = 0;	
									var myArr=[];
									var IdsStr="";	
									 for(var i in result.alertStatusList){
											myArr.push(result.alertStatusList[i].id)
											IdsStr=i==0?result.alertStatusList[i].id:IdsStr+","+result.alertStatusList[i].id;
											
									}
									var string1 = IdsStr,
										strx1   = string1.split(',');
										array1  = [];

									array1 = array1.concat(strx1);
									
									$.grep(array1, function(el) {
										if ($.inArray(el, statusIdsMulti) == -1) differenceSttausIds.push(el);
										a++;
									});	
									for(var i in result.alertStatusList){
										if(differenceSttausIds !=null && differenceSttausIds.length>0){
											for(var k in differenceSttausIds){
												if(result.alertStatusList[i].id == differenceSttausIds[k]){
													str+='<li  attr_id='+result.alertStatusList[i].id+'>'+result.alertStatusList[i].name+'</li>';
												}
											}
											
										} 
									}
								}else{
									for(var i in result.alertStatusList){
										str+='<li  attr_id='+result.alertStatusList[i].id+'>'+result.alertStatusList[i].name+'</li>';
									}
								}
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="alertSubtaskStatus"><b>alert subtask status</b></h5>';
						str+='<div class="scroller-alertSubtask">';
							str+='<ul class="filters-list" filters-list="alertSubtaskStatus">';
								str+='<li attr_id="All">All</li>';
								if(alertTaskType == 'mainAlertSubTask'){
									for(var i in result.alertSubTaskStatusList){
										if(statusIdsMulti !=null && statusIdsMulti.length>0){
											for(var k in statusIdsMulti){
												if(result.alertSubTaskStatusList[i].id == statusIdsMulti[k]){
													str+='<li class="active" attr_id='+result.alertSubTaskStatusList[i].id+'>'+result.alertSubTaskStatusList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
												}
											}
											
										} 
									}
									
									var differenceSubTaskSttausIds=[];
									var a = 0;	
									var mySubTaskArr=[];
									var subTaskIdsStr="";	
									 for(var i in result.alertSubTaskStatusList){
											mySubTaskArr.push(result.alertSubTaskStatusList[i].id)
											subTaskIdsStr=i==0?result.alertSubTaskStatusList[i].id:subTaskIdsStr+","+result.alertSubTaskStatusList[i].id;
											
									}
									var string1 = subTaskIdsStr,
										strx1   = string1.split(',');
										array1  = [];

									array1 = array1.concat(strx1);
									
									$.grep(array1, function(el) {
										if ($.inArray(el, statusIdsMulti) == -1) differenceSubTaskSttausIds.push(el);
										a++;
									});	
									for(var i in result.alertSubTaskStatusList){
										if(differenceSubTaskSttausIds !=null && differenceSubTaskSttausIds.length>0){
											for(var k in differenceSubTaskSttausIds){
												if(result.alertSubTaskStatusList[i].id == differenceSubTaskSttausIds[k]){
													str+='<li  attr_id='+result.alertSubTaskStatusList[i].id+'>'+result.alertSubTaskStatusList[i].name+'</li>';
												}
											}
											
										} 
									}
								}
								
								else{
									for(var i in result.alertSubTaskStatusList){
										str+='<li attr_id='+result.alertSubTaskStatusList[i].id+'>'+result.alertSubTaskStatusList[i].name+'</li>';
									}
								}
								
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="alertDepartments"><b>alert departments</b></h5>';
						str+='<div class="scroller-alertDepartments">';
							str+='<ul class="filters-list" filters-list="alertDepartments">';
								str+='<li attr_id="All">All</li>';
								for(var i in result.alertDepartMentList)
								{
									if(departmentId == result.alertDepartMentList[i].id)
									{
										str+='<li class="active" attr_id='+result.alertDepartMentList[i].id+'>'+result.alertDepartMentList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
									}else{
										str+='<li attr_id='+result.alertDepartMentList[i].id+'>'+result.alertDepartMentList[i].name+'</li>';
									}
								}
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="impactLevel"><b>Impact Level</b></h5>';
						str+='<div class="scopes-list">';
							str+='<ul class="filters-list" filters-list="impactLevel">';
							
								for(var i in result.scopesList){
									scopesListFilters.push({"name":result.scopesList[i].name,"id":result.scopesList[i].id})
								}
								if(alertTaskType == "locationLevel"){
									for(var i in result.scopesList){
										if(statusId == result.scopesList[i].id){
											str+='<li class="active" attr_id='+result.scopesList[i].id+'>'+result.scopesList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
										}
									}
								}
								if(departmentId != "" &&  alertTaskType == "alertSource" || alertTaskType == "mainAlertSubTask" || alertTaskType == "alertCategory"){
									
									for(var i in result.scopesList){
										if(statusId == result.scopesList[i].id){
											str+='<li class="active" attr_id='+result.scopesList[i].id+'>'+result.scopesList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
										}
									}
								}
								
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top20">';	
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="priority"><b>priority</b></h5>';
						str+='<ul class="filters-list" filters-list="priority">';
						var severityIdsArr=[];
						$('.severityTypeCls').each(function(i, obj){
							 if($(this).is(":checked")){
								 severityIdsArr.push($(this).attr("attr_val"))
							 }
						});
						if(result.severityList !=null && result.severityList.length>0){
							for(var j in result.severityList){
								if(severityIdsArr !=null && severityIdsArr.length>0){
									for(var k in severityIdsArr){
										if(result.severityList[j].id == severityIdsArr[k]){	
											str+='<li class="active" attr_id='+result.severityList[j].id+'>'+result.severityList[j].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
										}
									}
								}
									
							}
							
							var differenceSeverityIds=[];
								var a = 0;	
								var mySeverityArr=[];
								var severityIdsStr="";	
								 for(var i in result.severityList){
										mySeverityArr.push(result.severityList[i].id)
										severityIdsStr=i==0?result.severityList[i].id:severityIdsStr+","+result.severityList[i].id;
										
								}
								var string1 = severityIdsStr,
									strx1   = string1.split(',');
									array1  = [];

								array1 = array1.concat(strx1);
								
								$.grep(array1, function(el) {
									if ($.inArray(el, severityIdsArr) == -1) differenceSeverityIds.push(el);
									a++;
								});	
								for(var i in result.severityList){
									if(differenceSeverityIds !=null && differenceSeverityIds.length>0){
										for(var k in differenceSeverityIds){
											if(result.severityList[i].id == differenceSeverityIds[k]){
												str+='<li  attr_id='+result.severityList[i].id+'>'+result.severityList[i].name+'</li>';
											}
										}
										
									} 
								}
						}
						str+='</ul>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="alertSourceType"><b>alert source type</b></h5>';
						str+='<ul class="filters-list" filters-list="alertSourceType">';
						
							if(alertTaskType == 'alertCategory'){
								for(var i in result.categoryList){
										if(statusIdsMulti !=null && statusIdsMulti.length>0){
											for(var k in statusIdsMulti){
												if(result.categoryList[i].id == statusIdsMulti[k]){
													str+='<li class="active" attr_id='+result.categoryList[i].id+'>'+result.categoryList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
												}
											}
											
										} 
									}
									
									var differenceCategoryIds=[];
									var a = 0;	
									var myCateArr=[];
									var cateIdsStr="";	
									 for(var i in result.categoryList){
											myCateArr.push(result.categoryList[i].id)
											cateIdsStr=i==0?result.categoryList[i].id:cateIdsStr+","+result.categoryList[i].id;
											
									}
									var string1 = cateIdsStr,
										strx1   = string1.split(',');
										array1  = [];

									array1 = array1.concat(strx1);
									
									$.grep(array1, function(el) {
										if ($.inArray(el, statusIdsMulti) == -1) differenceCategoryIds.push(el);
										a++;
									});	
									for(var i in result.categoryList){
										if(differenceCategoryIds !=null && differenceCategoryIds.length>0){
											for(var k in differenceCategoryIds){
												if(result.categoryList[i].id == differenceCategoryIds[k]){
													str+='<li  attr_id='+result.categoryList[i].id+'>'+result.categoryList[i].name+'</li>';
												}
											}
											
										} 
									}
							}else if(departmentId == "" && statusId !="" && alertTaskType == 'alertSource'){
							
								for(var i in result.categoryList){
									if(statusId == result.categoryList[i].id){
										str+='<li class="active" attr_id='+result.categoryList[i].id+'>'+result.categoryList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
									}else{
										str+='<li  attr_id='+result.categoryList[i].id+'>'+result.categoryList[i].name+'</li>';
									}
								}
							}else{
								
								for(var i in result.categoryList)
								{
									if(	$(".socialMediaCls").is(":checked") && result.categoryList[i].name == "Social Media" || 
										$(".newsPaperListCls").is(":checked") && result.categoryList[i].name == "Print Media" || 
										$(".chanelListCls").is(":checked") && result.categoryList[i].name == "Electronic Media" || 
										$(".callcenterCls").is(":checked") && result.categoryList[i].name == "Call Center" || 
										$(".mondayGrievanceCls").is(":checked") && result.categoryList[i].name == "Monday Grievance"|| 
										$(".janmabhoomiCls").is(":checked") && result.categoryList[i].name == "Janmabhoomi"|| 
										$(".specialGrievanceCls").is(":checked") && result.categoryList[i].name == "Special Grievance - SC/ST"|| 
										$(".generalGrievanceCls").is(":checked") && result.categoryList[i].name == "General Grievance")
									{
										str+='<li class="active" attr_id='+result.categoryList[i].id+'>'+result.categoryList[i].name+'<span class="remove" filer-selection="true"><i class="glyphicon glyphicon-remove"></i></span></li>';
									}else{
										str+='<li attr_id='+result.categoryList[i].id+'>'+result.categoryList[i].name+'</li>';
									}
								}
							}
							
						str+='</ul>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12 m_top10">';
					str+='<div class="block-border">';
						str+='<h5 class="text-capitalize" filters-list-title="lagDays"><b>Lag Days</b><small class="clear" filer-selection-clear="lagDays">(Clear All)</small></h5>';
						str+='<label class="checkbox-inline"><input type="checkbox" name="lagDays" id="lagDaysId" checked/>Lag Days</label>';
						str+='<div id="tourSlider"></div>';
						str+='<label class="checkbox-inline pull-right"><input type="checkbox" class="yearCheckbox"/>More than 365 Days</label>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-3 m_top20">';
					str+='<button class="btn btn-primary btn-block filters-apply" attr_status_count="'+statuscount+'" attr_status_name="'+statusName+'" attr_status_id ="'+statusId+'" attr_department_id = "'+departmentId+'" type="button">APPLY FILTERS</button>';
				str+='</div>';
				str+='<div class="col-sm-3 m_top20">';
					str+='<button class="btn btn-default btn-block filters-cancel">CANCEL</button>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#filter").html(str);
		$("#tourSlider").rangeSlider();
		$("#tourSlider").rangeSlider("destroy");
		setTimeout(function(){
			$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 0, max: 365},defaultValues:{min: 0, max: 180}});
		},2000);
		
		$("[filters-list-title='statesLevel'],[filters-list-title='districtsLevel'],[filters-list-title='consLevel'],[filters-list-title='mandalLevel'],[filters-list-title='panLevel'],[filters-list-title='villageLevel'],[filters-list='statesLevel'],[filters-list='districtsLevel'],[filters-list='consLevel'],[filters-list='mandalLevel'],[filters-list='panLevel'],[filters-list='villageLevel']").hide();
		
		 if(statusId == 0 || statusId == "" && departmentId !=null && departmentId>0){
			$("[filters-list='alertDepartments'] li").each(function(){
				if($(this).hasClass("active"))
				{
					$(this).trigger("click");
				}
				
			});
		} 
		
	}
}
function buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount)
{
	
	var str='';
	popUpFilter('heading','','','','','','');
	var alertId = '';
	$("#modalHeadingTotal").html("Total "+statusName+' - '+statuscount);
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10" expand-main="false">';
			str+='<div class="classicView">';
				str+='<table style="background-color:#fff;" id="dataTable" class="table table-bordered ">';
					str+='<thead>';
						str+='<th></th>';
						str+='<th>Id</th>';
						str+='<th>Title</th>';
						str+='<th><span class="channel-name">Source</span></th>';
						str+='<th><span class="location-name">Location</span></th>';
						str+='<th><span class="channel-name">Status</span></th>';
						str+='<th><span class="channel-name">Ofcr Name</span></th>';
						str+='<th><span class="channel-name">Ofcr Designation</span></th>';
						str+='<th><span class="channel-name">Lag Days</span></th>';
						str+='<th>Subtask <i class="fa fa-level-down"></i></th>';
						str+='<th></th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result)
					{
						for(var j in result[i].subList)
						{
							str+='<tr>';
								str+='<td>';
									
									if(result[i].subList[j].severtyColor != null)
									{
										str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:'+result[i].subList[j].severtyColor+';margin-right:3px;"></i>';
									}else{
										str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:#eee;margin-right:3px;"></i>';
									}
									
								str+='</td>';
								str+='<td> ';
									if(result[i].subList[j].id != null)
									{
										str+=''+result[i].subList[j].id+'</td>';
									}else{
										str+='-</td>';
									}
									
								str+='<td>';
									if(result[i].subList[j].title != null)
									{
										str+='<span class="alert-title" data-toggle="tooltip" data-placement="top" title="'+result[i].subList[j].title+'">'+result[i].subList[j].title+'</span>';
									}else{
										str+='<span class="alert-title" data-toggle="tooltip" data-placement="top" title="-">-</span>';
									}
								str+='</td>';
								str+='<td>';
									if(result[i].subList[j].source != null)
									{
										str+='<span data-toggle="tooltip" class="channel-name" data-placement="top" title="'+result[i].subList[j].source+'">'+result[i].subList[j].source+'</span>';
									}else{
										str+='<span data-toggle="tooltip" class="channel-name" data-placement="top" title="-">-</span>';
									}
								str+='</td>';
								
								str+='<td>';
									if(result[i].subList[j].location != null)
									{
										str+='<span data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].location+'">'+result[i].subList[j].location+'</span>';
									}else{
										str+='<span data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].subList[j].status != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].status+'">'+result[i].subList[j].status+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].subList[j].problem != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].problem+'">'+result[i].subList[j].problem+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].subList[j].relatedTo != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].relatedTo+'">'+result[i].subList[j].relatedTo+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].subList[j].interval != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].interval+'">'+result[i].subList[j].interval+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].subList[j].subTaskCount != null && result[i].subList[j].subTaskCount > 0)
									{
										str+='<span class="arrow-icon" style="margin:0px 4px" attr_alertId="'+result[i].subList[j].id+'" expand-icon="block1">';
											str+=''+result[i].subList[j].subTaskCount+'';
										str+='</span>';
									}else{
										str+='-';
									}
								str+='</td>';
								str+='<td>';
									if(result[i].subList[j].id != null && result[i].subList[j].id > 0)
									{
										str+='<span class="arrow-icon pull-right alertIdCls" attr_statusId="'+result[i].subList[j].statusId+'" attr_alertId="'+result[i].subList[j].id+'" expand-icon="block1">';
											str+='<i class="glyphicon glyphicon-menu-right"></i>';
										str+='</span>';

									}else{
										str+='-';
									}
									
								str+='</td>';
							str+='</tr>';
						}
					}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			str+='<div class="panel-group panel-white panel-left premiumView" style="display:none" id="accordion" role="tablist" aria-multiselectable="true">';
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
	$("#dataTable").dataTable();
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
			str+='<p> ASSIGN TO: <i class="fa fa-level-down "></i></p> ';
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
	var alertId = $(this).attr("attr_alertId");
	//var statusId = $("[attr_alertid="+alertId+"]").attr("attr_statusid");
	var statusId =$(".alertIdCls").attr("attr_statusid");
	
	if(statusId == 8 || statusId == 9)
	{
		getAllMainDepartments('assignDepartmentId');
	}
});

function assignUser(alertId)
{
	var str='';
	str+='<div class="assign-user">';
		str+='<ul class="list-icons list-inline">';
			str+='<li id="displayAssignIconId" attr_alertId="'+alertId+'" style="display:none;">';
				str+='<i class="glyphicon glyphicon-user"></i>Click To Assignee  ';
			str+='</li>';
		str+='</ul>';
		str+='<div class="assign-user-body" style="display:none">';
			str+='<form id="alertAssign" name="alertAssignForm">';
				str+='<div class="arrow_box_top">';
					str+='<div>';
						str+='<div class="row">';  
							str+='<div class="col-sm-12">';
								str+='<div id="assignErrorDivId" class="text-danger text-capitalize" style="margin-bottom:10px;"></div>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="assignDepartmentId" name="alertAssigningVO.mainDepartmentId">';
									str+='<option value="0">Select Department</option>';
									//str+='<option value="49">RWS</option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Sub Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	';
									str+='<option value="0">Select Sub Department</option>';
									//str+='<option value="49">RWS</option>';
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
	
	getDepartmentDetailsOfAlert(alertId);
}

function getDepartmentDetailsOfAlert(alertId)
{
	var jsObj={
		alertId : alertId
	}
	$.ajax({   
		type:'GET',
		url:'getDepartmentDetailsOfAlertAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		for(var i in result){
			
			if(result[i].name !=null && result[i].name.length>0){
				var newStr='';		
				newStr+='<option value="0">Select Department</option>';
				newStr+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				$("#assignDepartmentId").html(newStr);
				$("#assignDepartmentId").trigger("chosen:updated");
			}
		}	
	});
}
function alertHistory(result)
{
	
	var str='';
	for(var i in result){
			str+='<ul class="alert-history m_top10" style="list-style:outside none none">';
			str+='<span class="alert-history-date"  style="background-color: lightpink;padding: 3px;border-radius: 5px;" >'+result[i][0].trackingDate+'</span>';
			
			for(var j in result[i]){
				str+='<li>';
			
				str+='<span class="alert-history-time" >'+result[i][j].trackingTime+'</span>';
					if(result[i][j].actionType == 'Assigning'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Assigned BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>    </p>';
						
					}else if(result[i][j].actionType == 'Attachment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'</p>'; 
						str+='<p><span class="alert-history-body text-capital"><a target="_blank" href="../images/'+result[i][j].document+'" width="25%" style="margin-left: 25px;" class="m_top5" >'+result[i][j].document+'</a></span></p>';
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';      
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Due Date'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Changed Date </span> : '+result[i][j].dueDate+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>'; 
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Priority'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  </p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Priority </span> : '+result[i][j].severty+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>'; 
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Status Change'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Status </span> :';
						if(result[i][j].status == 'Proposal'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Status </span> :'+result[i][j].proposalStatus+'</p>';
							 if(result[i][j].categoryId == 1 ){
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'';
								if(result[i][j].proposalStatus == 'Proposal Accept'){
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/-';
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px">Approved Amount </span> :'+result[i][j].approvedAmount+'/-</p>';
								}else{
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/- </p>';
								}
							}else{
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'</p>';
							}
						 }else if(result[i][j].status == 'Rejoinder'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Rejoinder Status </span> :'+result[i][j].rejinderStatus+'</p>';
						}else {
							str+=''+result[i][j].status+'</p>';
						} 
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Comment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span> '; 
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Feedback Status'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						if(result[i][j].alertFeedbackStatus != null && result[i][j].alertFeedbackStatus != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Feed back Status </span>: '+result[i][j].alertFeedbackStatus+'</p>'; 
						}
						
						if(result[i][j].status != null && result[i][j].status != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Alert Status </span>: '+result[i][j].status+'</p>';
						}
						if(result[i][j].alertCallerName != null && result[i][j].alertCallerName != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Caller Name </span>: '+result[i][j].alertCallerName+'</p>';
						}
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}
				
					
				str+='</li>';	
			}
		
		str+='</ul>';
		}
		
	/* for(var i in result)
	{
		str+='<ul class="alert-history">';
			str+='<span class="alert-history-date"  style="background-color: lightpink;padding: 3px;border-radius: 5px;" >'+result[i].date+'</span>';
			if(result[i].timeList != null && result[i].timeList.length > 0)
			{
				for(var j in result[i].timeList)
				{
					str+='<li>';
						str+='<span class="alert-history-time" >'+result[i].timeList[j].date+'</span>';
						
						if(result[i].timeList[j].statusList != null && result[i].timeList[j].statusList.length > 0)
						{
							var actionType = result[i].timeList[j].statusList[0].alertTrackingActionType;
							if(actionType != null && actionType.length>0)
								str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;" ><span class="status-icon arrow-icon"></span>Action : '+actionType+'</p>';
							
							if(actionType == 'Comment'){									
								if(result[i].timeList[j].commentList != null && result[i].timeList[j].commentList.length > 0)
								{
									for(var k in result[i].timeList[j].commentList)
									{
										str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Comment </span>: '+result[i].timeList[j].commentList[k].strList+'</p>';
									}
								}
								
							}
							else if(actionType == 'Attachment'){
								if(result[i].timeList[j].attachementsList != null && result[i].timeList[j].attachementsList.length > 0)
								{
									for(var k in result[i].timeList[j].attachementsList)
									{
										if(result[i].timeList[j].attachementsList[k].strList != null && result[i].timeList[j].attachementsList[k].strList.length>0){
											for(var h in result[i].timeList[j].attachementsList[k].strList){
												str+='<span class="alert-history-body text-capital"><img src="http://www.mytdp.com/images/'+result[i].timeList[j].attachementsList[k].strList[h]+'" width="25%" style="margin-left: 25px;" class="m_top5" /></span>';
											}
										}
									}
								}
							}
							else if(actionType == 'Due Date'){
								if(result[i].timeList[j].dueDateList != null && result[i].timeList[j].dueDateList.length > 0)
								{
									for(var l in result[i].timeList[j].dueDateList)
									{
										if(result[i].timeList[j].dueDateList[l].strList != null && result[i].timeList[j].dueDateList[l].strList.length>0){
											for(var c in result[i].timeList[j].dueDateList[l].strList)
												str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Changed Date </span> : '+result[i].timeList[j].dueDateList[l].strList[c]+'</p>';
										}
										
									}										
								}  
							}
							else if(actionType == 'Priority'){
								if(result[i].timeList[j].priorityList != null && result[i].timeList[j].priorityList.length > 0)
								{
									for(var l in result[i].timeList[j].priorityList)
									{
										if(result[i].timeList[j].priorityList[l].strList != null && result[i].timeList[j].priorityList[l].strList.length>0){
											for(var c in result[i].timeList[j].priorityList[l].strList)
												str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Priority </span> : '+result[i].timeList[j].priorityList[l].strList[c]+'</p>';
										}
										
									}										
								}
							}
							
								
								if(result[i].timeList[j].statusList != null && result[i].timeList[j].statusList.length>0 && result[i].timeList[j].statusList[0].strList != null && result[i].timeList[j].statusList[0].strList.length > 0)
								{
									if(actionType == 'Status Change'){
										str+='<p class="alert-history-status m_top20 text-capital ">  <span style="margin-left: 20px">  STATUS </span> :  <span style="font-size:10px;">'+result[i].timeList[j].statusList[0].strList+' </span></p>';
										
										
									}
								}
								if(result[i].timeList[j].commentList != null && result[i].timeList[j].commentList.length>0 && result[i].timeList[j].commentList[0].strList != null && result[i].timeList[j].commentList[0].strList.length > 0)
								{										
									if(actionType == 'Status Change'){
										str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i].timeList[j].commentList[0].strList+'</p>';
									}
								}
									
							}
							
							if(result[i].timeList[j].statusList[0].strList != null && result[i].timeList[j].statusList[0].strList.length > 0)
							{
								for(var l in result[i].timeList[j].statusList[0].strList)
								{
									
									str+='<p class=" alert-history-user m_top20 text-capital ">';
									if(result[i].timeList[j].statusList[0].updatedUserName != null && result[i].timeList[j].statusList[0].updatedUserName.length>0){
										str+=' <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i].timeList[j].statusList[0].updatedUserName+'  </span>';
									}
									if(result[i].timeList[j].statusList[0].deptName != null && result[i].timeList[j].statusList[0].deptName.length>0){
										str+=' , <span style="color:slategrey;font-weight:bold;margin-left: 25px">  Dept </span>: <span style="font-size:10px"> '+result[i].timeList[j].statusList[0].deptName+'  </span>';
									}
									if(result[i].timeList[j].statusList[0].designation != null && result[i].timeList[j].statusList[0].designation.length>0){
										str+=', <span style="color:slategrey;font-weight:bold;margin-left: 25px"> designation </span>: <span style="font-size:10px"> '+result[i].timeList[j].statusList[0].designation+' </span>';
									}
									if(result[i].timeList[j].statusList[0].mobileNO != null && result[i].timeList[j].statusList[0].mobileNO.length>0){
										//str+=', <span style="color:slategrey;font-weight:bold;margin-left: 25px"> , Mobile No </span>: '+result[i].timeList[j].statusList[0].mobileNO+'';
									}
									str+='</p>';
								}
							}
							
					str+='</li>';
				}
			}
		str+='</ul>';
	} */
	
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
				str+='<th >Date</th>';
				str+='<th>Status</th>';
				str+='<th>Updated By</th>';
				str+='<th>Comments</th>';
			str+='</thead>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td style="width:100px;">'+result[i].date+'</td>';
					str+='<td>'+result[i].status+'</td>';
					str+='<td>';
					if(result[i].designation == "Admin"){
						str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].userName+' </span></p>';
						str+='<p class="text-primary text-capitalize"><u> Designation:  <span style="color:black;"> '+result[i].designation+' </span></u></p>';
					}else{
						str+='<p class="text-primary text-capitalize">Updated By: <span style="color:black;">'+result[i].userName+' </span></p>';
						str+='<p class="text-primary text-capitalize"><u> Designation:  <span style="color:black;"> '+result[i].designation+' </span></u></p>';
						str+='<p class="text-primary text-capitalize"><u> Location:  <span style="color:black;"> '+result[i].location+' </span></u></p>';
						
						if(result[i].deptName != null && result[i].deptName.length > 0){
							var deptArr = result[i].deptName.split(",");
							if(deptArr.length > 3){
								str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+deptArr[0]+","+deptArr[1]+","+deptArr[2]+'... </span></p>';
							}else{
								str+='<p class="text-primary text-capitalize">Dept Name: <span style="color:black;"> '+result[i].deptName+' </span></p>';
							}
						}
						
					}
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
		$("#alertManagementPopupBody1").html(" NO HISTORY AVAILABLE...")
	}
	$("#alertManagementPopup1 .modal-footer").html(' ');
	
	if(isAdmin == "false"){
		if(globalUserType != "other"){
			str1+='<div class="text-left" id="changeStatudCheckBoxId">'; 
			if(globalUserType != "same"){
				if(isStatusAvailable){
					str1+='<label class="checkbox-inline changeStateCls">';
						str1+='<input type="checkbox" attr_alert_id="'+alertId+'" class="alert-status-change changeStatsCls" id="proposalCheckBxId"/> I Want to change alert Status';
					str1+='</label>';  
					}
			}
				if($("#displayStatusId #statusId").html() == 'Proposal'){
							if(globalEntitlement != null && globalEntitlement == "true"){
								if(globalDeprtStatus == 'true'){
									str1+='<label class="checkbox-inline propStatusChangeCls">';
											str1+='<input type="checkbox" attr_alert_id="'+alertId+'" class="alert-status-change propasalchangeStatsCls" /> Do You Want To Change Proposal Status';	
										str1+='</label>';  
									}
								}
							}
				if(globalUserType != "same"){
					str1+='<div  id="updateStatusChangeBody" style="display:none;">'+glStr+'</div>';
				}
				str1+='<div  id="propasalupdateStatusChangeBody" style="display:none;">'+globalPropasalStr+'</div>';
				
			str1+='</div>';
			$("#alertManagementPopup1 .modal-footer").html(str1);	
		}
	}
		
	if(globalStatusId == 8 || globalStatusId == 9){
		$("#changeStatudCheckBoxId").hide(); 
    }
	
	if(globalStatusId == 13 && globalUserType == 'own')//Hide changeStatusBlock when it is in proposal status
	{
		if(!globalEntitlement){
			if(globalProposalStatus == 'Proposal Pending'){
				//$("#alertManagementPopup1 .modal-footer").hide();
				$(".propStatusChangeCls").hide();
				$(".changeStateCls").hide();
			}else if(globalProposalStatus == 'Proposal Rejected' || globalProposalStatus == 'Proposal Accept'){
				//$("#alertManagementPopup1 .modal-footer").show();
				$(".changeStateCls").show();
			}
		}else{
			if(globalProposalStatus == 'Proposal Pending'){
				$(".propStatusChangeCls").show();
				$(".changeStateCls").hide();
			}else if(globalProposalStatus == 'Proposal Rejected' || globalProposalStatus == 'Proposal Accept'){
				$(".propStatusChangeCls").hide();
				$(".changeStateCls").show();
			}
			//$("#alertManagementPopup1 .modal-footer").show();
			//$("#alertManagementPopup1 .modal-footer").show();
		}
		
	}
	if(globalStatusId == 10 ){
		alertStatusGlobalId = 10;
		$("#changeStatusDivId").hide();
		if( globalRejoinderStatus == 'Rejoinder Request'){
			$(".rejoinderDivCls").show();
			$("#rejinderRespnseId").show();
		}else{
			//$(".rejoinderDivCls").hide();
			$("#changeStatudCheckBoxId").hide();
		}
	}
	
	
	var options = {
	  sourceLanguage:
		  google.elements.transliteration.LanguageCode.ENGLISH,
	  destinationLanguage:
		  [google.elements.transliteration.LanguageCode.TELUGU],
	  shortcutKey: 'alt+t',
	  transliterationEnabled: true
	};
	var control = new google.elements.transliteration.TransliterationControl(options);
	//control.makeTransliteratable(['updateStatusChangeComment']);
}
 
 

function getFilterSectionAlertDetails(statusName,statuscount,globalDepartmentIdsArr,statusId,departmentId,alertType,statusIdsMulti){
	$("#filter").hide();
	var jsObj={
		deptIdArr : globalDepartmentIdsArr
	}
	$.ajax({   
		type:'GET',
		url:'getFilterSectionAlertDetailsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		//$("#alertManagementPopup .modal-header").html('');
		$("#filter").html('');
		popUpFilter('body',result,statusName,statuscount,statusId,departmentId,alertType,statusIdsMulti);
		
	});
}
//swadhin
var glStr='';
var globalPropasalStr='';
function alertStatus(result,alertId)
{
	glStr='';
	globalPropasalStr='';
	var str1='';
	var str=''; 
		str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body">';
			str1+='<div class="panel-heading" style="margin-left: 20px;">';
				str1+='<div class="row" id="changeStatusDivId">';
				for(var i in result)
				{
					
					str1+='<div class="col-sm-4">';
						str1+='<div class="radioStyling">';
							if(globalStatusId == parseInt(result[i].id))
							{
								str1+='<input class="alertStatusCls" attr_id="'+result[i].id+'" type="radio" name="group1" id="radio-'+result[i].id+'">';
							}else
							{
								str1+='<input class="alertStatusCls" attr_id="'+result[i].id+'" type="radio" name="group1" id="radio-'+result[i].id+'">';
							}
							str1+='<label for="radio-'+result[i].id+'"><span class="radio" >'+result[i].name+'</span></label>';
						str1+='</div>';
					str1+='</div>';
				}				
				str1+='</div>';
			str1+='</div>';
			str1+='<div class="panel panel-default proposalAppendBlockDivCls" style="display:none;background-color:#ededed">';
				str1+='<div class="panel-heading" style="background-color:#ededed;padding-left:15px;">';
					str1+='<h4 class="panel-title">Proposal Information</h4>';
				str1+='</div>';
				str1+='<div class="panel-body" style="background-color:#ededed">';
					str1+='<div class="row">';
						str1+='<div class="col-sm-12">';
							str1+='<div class="m_top10">';
								str1+='<label class="checkbox-inline">';
								  str1+='<input type="checkbox" class="proposalCheckbox" value="1" name="statusChekBx">Financial Assistance<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<label class="checkbox-inline">';
								  str1+='<input type="checkbox" class="proposalCheckbox" value="2" name="statusChekBx">Policy Decision Required<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<label class="checkbox-inline">';
								  str1+='<input type="checkbox" class="proposalCheckbox" value="3" name="statusChekBx">Others<span style="color:red">*</span>';
								str1+='</label>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="col-sm-4">';
							str1+='<div class="input-group amountCls m_top20" style="display:none;">';
								str1+='<span class="input-group-addon">';
									str1+='<i class="fa fa-inr"></i>';
								str1+='</span>';
								str1+='<input type="text" class="form-control" placeholder="Enter Amount" id="amountId">';
							str1+='</div>';
							str1+='<span id="errMsgAmuntId"></span>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
			
			 str1+='<div class="panel panel-default rejoinderDivCls" style="display:none;background-color:#ededed">';
				str1+='<div class="panel-heading" style="background-color:#ededed;padding-left:15px;">';
					str1+='<h4 class="panel-title">Rejoinder Status</h4>';
				str1+='</div>';
				str1+='<div class="panel-body" style="background-color:#ededed" >';
					str1+='<div class="row">';
						str1+='<div class="col-sm-12">';
							str1+='<div class="m_top10">';
								str1+='<label class="checkbox-inline" id="rejinderReqId">';
								  str1+='<input type="checkbox" class="rejoinderCheckbox" value="1" name="RejoinderChekBx">Rejoinder Request<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<label class="checkbox-inline" id="rejinderRespnseId">';
								  str1+='<input type="checkbox" class="rejoinderCheckbox" value="2" name="RejoinderChekBx">Rejoinder Response<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<form id="alertAssignAttachemntFrRejoinderStatusId" name="uploadAttachementFrRejoinderStatus">';
									str1+='<input type="file" id="rejoinderAttachmentId" name="imageForDisplay" style="margin-top:21px; margin-left:35px;">';
									str1+='<input type="hidden" value="'+alertId+'" name="alertVO.alertId">';
								str1+='</form>';
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>'; 

			
			str1+='<div class="panel-body pad_0 m_top20">';
				str1+='<textarea class="form-control" id="updateStatusChangeComment" placeholder="Comment.."></textarea>';
			str1+='</div>';
		str1+='</div>';

	
	str1+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" subTaskId="" id="updateStatusChangeId">update</button>';
	str1+='<span id="updateStatusChangeAjaxSymbol"></span>';
	str1+='<span id="updateStatusChangeMsg"></span>';
	
		str+='<div class="col-sm-12">';
			str+='<div style="padding:10px;background-color:#ddd">';
			if(globalPropCategory != null){
				str+='<p><strong>Proposal Category </strong>:'+globalPropCategory+'</p>';
			}
			if(globalPropReqAunt != null && globalPropReqAunt.length > 0){
				str+='<p class="m_top5"><strong>Requested Amount </strong>:'+globalPropReqAunt+'/- </p>';
			}
			str+='</div>';	
		str+='</div>';	
		str+='<div class="col-sm-4">';
			str+='<div class="radioStyling">';
				str+='<input class="alertStatusCls alertStatusAmountCls" attr_id="3" type="radio" name="group1" id="radio-1">';
				str+='<label for="radio-1"><span class="radio">Proposal Accept<span style="color:red;"> *</span></span></label>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4">';
			str+='<div class="radioStyling ">';
				str+='<input class="alertStatusCls alertStatusAmountCls" attr_id="2" type="radio" name="group1" id="radio-2">';
				str+='<label for="radio-2"><span class="radio">Proposal Reject<span style="color:red;"> *</span></span></label>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<div class="input-group m_top5 alertStatusAmountInputCls" style="display:none;">';
				str+='<span class="input-group-addon">';
					str+='<i class="fa fa-inr"></i>';
				str+='</span>';
				str+='<input type="text" class="form-control" placeholder="Enter Approved Amount" id="approvedAmountId">';
			str+='</div>';
			str+='<span id="errMsgAprAmuntId"></span>';
		str+='</div>'; 
	   str+='<div class="panel-body pad_0">';
		str+='<textarea class="form-control m_top10" id="acceptedStatusChangeComment" placeholder="Comment.."></textarea>';
	str+='</div>';
	str+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" subTaskId="" id="updatePrposalStatsId">update</button>';
	str1+='<span id="updateProposalStatusChangeMsg"></span>';
	glStr=str1;
	globalPropasalStr=str;
	//$("#updateStatusChangeBody").html(str1);
	
}
function getAssignUIAttributes(alertId){
	getGovtAllDepartmentDetails();
	buildAssignUIAttributes(alertId);
}
function getGovtAllDepartmentDetails(){
	
	$("#assignDepartmentId1").html('');
	var jsObj={
		
	}
	$.ajax({   
		type:'GET',
		url:'getGovtAllDepartmentDetailsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			$("#assignDepartmentId1").append("<option value='0'>Select Department</option>")
			for(var i in result){
				$("#assignDepartmentId1").append("<option value="+result[i].id+">"+result[i].name+"</option>")
			}
			
			
		}
		$("#assignDepartmentId1").chosen();
			$("#assignDepartmentId1").trigger("chosen:updated");
	});
}

/* var globalUserLevelId;
var globalUserLevelValues = [];	 */
function getDepartmentSubLevels(deptId){
	
	$("#designationsId1").empty();
	$("#designationsId1").trigger("chosen:updated");
	$("#officerNamesId1").empty();
	$("#officerNamesId1").trigger("chosen:updated");
	
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

function getChildLevelValuesForSubTask(){
	var departmentId = $("#departmentsId1").val();
		
	var jsObj = {
		departmentId : departmentId,
		levelId : $("#locationLevelSelectId1").val(),
		parentLevelId : globalUserLevelId,
		parentLevelValueArr : globalUserLevelValues
		
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
				str+='<div class="col-sm-6">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect dynamicSelectList" attr_dyna_name="'+result[i].name+'" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-6">';
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
function getAlertDtlsBasedOnStatusFilterClick(statusName,statusCount,impactLevelArr,priorityArr,alertSourceArr,printMediaArr,electronicMediaArr){
	$("#alertManagementPopupBody").html(spinner);
	var fromDays = $("#tourSlider").rangeSlider("min").toFixed(0);
	var toDays = $("#tourSlider").rangeSlider("max").toFixed(0);
	var statusIdArr = [];
	var subStatusIdArr = [];
	$("[filters-list='alertStatus'] li.active").each(function(){
		var value = $(this).attr("attr_id");
			statusIdArr.push(value);
		if ($.inArray('All', statusIdArr) != -1)
		{
		 statusIdArr.shift();
		}
		
		
	});
	$("[filters-list='alertSubtaskStatus'] li.active").each(function(){
		var value = $(this).attr("attr_id");
		subStatusIdArr.push(value);
		if ($.inArray('All', subStatusIdArr) != -1)
		{
		 subStatusIdArr.shift();
		}
		
	});
	var levelId = $("[filters-list='locLevel'] li.active").attr("attr_id");
	var levelValues = [];
	if(levelId == '1')
	{
		$("[filters-list='statesLevel'] li.active").each(function(){
			levelValues.push($(this).attr("attr_id"));
		});
	}else if(levelId == '2')
	{
		$("[filters-list='districtsLevel'] li.active").each(function(){
			levelValues.push($(this).attr("attr_id"));
		});
	}else if(levelId == '3')
	{
		$("[filters-list='consLevel'] li.active").each(function(){
			levelValues.push($(this).attr("attr_id"));
		});
	}else if(levelId == '4')
	{
		$("[filters-list='mandalLevel'] li.active").each(function(){
			levelValues.push($(this).attr("attr_id"));
		});
	}else if(levelId == '5')
	{
		$("[filters-list='panLevel'] li.active").each(function(){
			levelValues.push($(this).attr("attr_id"));
		});
	}else if(levelId == '6')
	{
		$("[filters-list='villageLevel'] li.active").each(function(){
			levelValues.push($(this).attr("attr_id"));
		});
	}else{
		levelId =0;
	}
	var isMoreThanYrChkd = '';
	if($("#yearCheckbox").is(":checked") == true)
	{
		isMoreThanYrChkd = "true"
		fromDays = 0;
		toDays = 0;
	}else{
		isMoreThanYrChkd = "false"
	}
	var departmentsIds = [];
	$("[filters-list='alertDepartments'] li").each(function(){
		if($(this).hasClass("active"))
		{
			var value = $(this).attr("attr_id");
			departmentsIds.push(value);
			if ($.inArray('All', departmentsIds) != -1){
				departmentsIds.shift();
			}
			
		}
	});
	if(departmentsIds.length < 0)
	{
		departmentsIds = globalDepartmentIdsArr;
	}
	
	var jsObj ={
		fromDate:     		 currentFromDate,
		toDate:       		 currentToDate,
		stateId :     		 1,
		deptIdArr :     	 departmentsIds,  
		paperIdArr :     	 newspapersGlobalArr,
		chanelIdArr :     	 channelGlobalArr, 
		callCenterArr :   	 callCenterGlobalArr,    
		statusId :       	 statusIdArr,
		impactLevelArr :  	 impactLevelArr,
		priorityArr :    	 priorityArr,
		alertSourceArr:    	 alertSourceArr,
		printMediaArr :    	 printMediaArr,
		electronicMediaArr:  electronicMediaArr,
		startDay :			 fromDays,
		endDay :			 toDays,
		levelId :			 levelId,
		levelValues :		 levelValues,
		subTaskStatusIdList: subStatusIdArr,
		isMoreThanYrChkd: 	 "false",
		isLagChkd : "false",
		filterSocialMediaIdsArr : [],
		filterCallCenterArr :[],
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr,
		mondayGrievanceTypeIdsArr:globalMondayGrievanceTypeIdsArr,
		janmabhoomiTypeIdsArr:globalJanmabhoomiTypeIdsArr,
		specialGrievanceTypeIdsArr:globalSpecialGrievanceTypeIdsArr,
		generalGrievanceTypeIdsArr:globalGeneralGrievanceTypeIdsArr
    }
    $.ajax({
		type:'GET',
		url: 'getTotalAlertByStatusNewAction1.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,statusCount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
    });
}

function getDepartmentLevelsForSubTask(deptId){
	
	$("#designationsId1").empty();
	$("#designationsId1").trigger("chosen:updated");
	$("#officerNamesId1").empty();
	$("#officerNamesId1").trigger("chosen:updated");
	
	var jsObj = {
		departmentId : deptId
	}
	$.ajax({
      type:'GET',
      url: 'getDepartmentLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentSubLevels(result);
		}
	});
	
}

function getParentLevelsOfLevelForSubTask(){
	var departmentId = $("#departmentsId1").val();
	var levelId = $("#locationLevelSelectId1").val();
	
	$("#designationsId1").empty();
	$("#designationsId1").trigger("chosen:updated");
	$("#officerNamesId1").empty();
	$("#officerNamesId1").trigger("chosen:updated");
	
	var jsObj = {
		departmentId : departmentId,
		levelId : levelId
	}
	$.ajax({
      type:'GET',
      url: 'getParentLevelsOfLevelAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildChildLevelValuesForSubTask(result,departmentId);
		}
	});
}

function getDistrictsListForState(id)
{
	var jsObj ={
		stateId : id
	}
	$.ajax({
		type:'GET',
		url: 'getDistrictsListForStateAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			var str = '';
			for(var i in result)
			{
				str+='<li attr_id='+result[i].id+'>'+result[i].name+'</li>'
			}
			$("[filters-list='districtsLevel']").html(str);
		}
	});
}
function getMandalDetailsByConstituency(id)
{
	var jsObj ={
		constituencyId : id
	}
	$.ajax({
		type:'GET',
		url: 'getMandalDetailsByConstituencyAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			var str = '';
			for(var i in result)
			{
				str+='<li attr_id='+result[i].locationId+'>'+result[i].locationName+'</li>'
			}
			$("[filters-list='mandalLevel']").html(str);
			
		}
	});
}
function getPanchayatWardByMandal(id)
{
	var jsObj ={
		mandalId		: id,
		constituencyId 	: $("[filters-list='mandalLevel'] li.active").attr("attr_id")
	}
	$.ajax({
		type:'GET',
		url: 'getPanchayatWardByMandalAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			var str = '';
			for(var i in result)
			{
				str+='<li attr_id='+result[i].locationId+'>'+result[i].locationName+'</li>'
			}
			$("[filters-list='panLevel']").html(str);
		}
	});
}
function getHamletsForPanchayat(id)
{
	var jsObj ={
		panchayatId		: id,
	}
	$.ajax({
		type:'GET',
		url: 'getHamletsForPanchayatAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			var str = '';
			for(var i in result)
			{
				str+='<li attr_id='+result[i].id+'>'+result[i].name+'</li>'
			}
			$("[filters-list='villageLevel']").html(str);
		}
	});
}
function getlevlsForDepartmnt(id)
{
	var jsObj ={
		departmentId	: id,
	}
	$.ajax({
		type:'GET',
		url: 'getlevlsForDepartmntAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			var str = '';
			for(var i in result)
			{
				str+='<li attr_id='+result[i].id+'>'+result[i].name+'</li>'
			}
			$("[filters-list='impactLevel']").html(str);
		}
	});
}

function getConstituenciesForADistrictAjax(id)
{
	var jsObj ={
		districtId : id
	}
	$.ajax({
		type:'GET',
		url: 'getConstituenciesForADistrictAjaxAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			var str = '';
			for(var i in result)
			{
				str+='<li attr_id='+result[i].id+'>'+result[i].name+'</li>'
			}
			$("[filters-list='consLevel']").html(str);
		}
	});
}
function languageChangeHandler(){
	var lang1 = $("input[name=language]:checked").val();
	if(lang1 =="en"){
		control.disableTransliteration();
	}else{
		control.enableTransliteration();
		control.setLanguagePair(
		google.elements.transliteration.LanguageCode.ENGLISH,
		lang1);
	}
}
function languageChangeHandler1(){
	var lang2 = $("input[name=language1]:checked").val();
	if(lang2 =="en"){
		control.disableTransliteration();
	}else{
		control.enableTransliteration();
		control.setLanguagePair(
		google.elements.transliteration.LanguageCode.ENGLISH,
		lang1);
	}
}
function buildSubTaskCommetDtls(result){
	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-road fa-2x m_top20"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital m_top20">complete sub task history</h4>';              
		str+='<ul class="alert-myfoot m_top10" style="list-style:outside none none">';
		for(var i in result){
			
			str+='<li>';
			str+='<span class="alert-history-date"  style="background-color: lightpink;padding: 3px;border-radius: 5px;" >'+result[i][0].trackingDate+'</span>';
			for(var j in result[i]){
				
					if(result[i][j].actionType == 'Assigning'){     
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Assigned BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>    </p>';
						
					}else if(result[i][j].actionType == 'Attachment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						str+='<p><span class="alert-history-body text-capital"><a target="_blank"  href="../images/'+result[i][j].document+'" width="25%" style="margin-left: 25px;" class="m_top5" >'+result[i][j].document+'</a></span></p>';       
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">'+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';     
					}else if(result[i][j].actionType == 'Due Date'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Changed Date </span> : '+result[i][j].dueDate+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';  
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
						
					}else if(result[i][j].actionType == 'Priority'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Priority </span> : '+result[i][j].severty+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Status Change'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Status </span> : '+result[i][j].status+'</p>';
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Comment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}
			}
		str+='</li>';	
		
		}
		str+='</ul>';
	str+='</div>';
	$("#subAlertComments").html(str);
}

function getSearchAlertsDtls(alertId){
	$("#alertIdSearch").val('');
	var jsObj ={
		alertId:alertId 
	}
	$.ajax({
		type:'GET',         
		url: 'getSearchAlertsDtlsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$(".tbtn").trigger("click");
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		if(result != null){
			var alertId =result;
			$("#alertManagementPopupBody").html("<div class='row'><div id='rightSideExpandView'></div></div>");
			rightSideExpandView(alertId);
			popUpFilter('heading','','','','','','','');
			$(".flipview-btn,.filters-icon").hide();
			var alertId = '';
			$("#modalHeadingTotal").html("Alert Status");
			setTimeout(function(){
				$("[expanded-block='block1']").addClass("col-sm-12").removeClass("col-sm-8 pad_left0");
				$("[expanded-block='block1']").show();
			},750);
		}else{
			$("#alertManagementPopupBody").html("NO ACCESS");
		}
	});
}
$(document).on("click",".linkedArticlesClickId",function(){	 
	var temp=$(this).attr('src');
	$(this).attr('src',$(".mainImage").attr('src'));
	$(".mainImage").attr('src',temp);
});
$(document).on('change', '#assignDepartmentId', function(){
		var deptId = $(this).val();
		getSubDepartmentsFrPrntDept(deptId,$(this).attr('id'));
});
function getSubDepartmentsFrPrntDept(deptId,buildId){
	
	var jsObj = {
			parntDeptId : deptId
	}
	$.ajax({
      type:'GET',
      url: 'getSubDeptsFrParentDeptAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildSubDepartmentsFrPrntDept(result,buildId);
		}
	});
	
}
function buildSubDepartmentsFrPrntDept(result,tempBuildId){
	var buildId='departmentsId';
	if(tempBuildId =='assignDepartmentId1')
		buildId='departmentsId1';
	var str='';	
	str+='<option value="0">Select Sub Department</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	
	$("#"+buildId+"").html(str);
	$("#"+buildId+"").trigger("chosen:updated");
}
$(document).on('change', '#assignDepartmentId1', function(){
		var deptId = $(this).val();
		getSubDepartmentsFrPrntDept(deptId,$(this).attr('id'));
});
$(document).on("click","#mainDeprtmntId",function(){
	var alertId = $(this).attr("attr_alert_id");
	buildMainDepartmentsPopup(alertId);
	getPresentAssignedDepartmentOfAlert(alertId);
});

function buildMainDepartmentsPopup(alertId){
	$("#alertDeprtmntPopup").modal('show');
	$("#alertDepartmentsPopupBody").html(spinner)
	var str = '';
	str+='<div class="row">';
	str+='<div class="col-sm-6">';
		str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
		str+='<select class="chosenSelect" id="newDepartmentId">';
			str+='<option value="0">Select Department</option>';
			//str+='<option value="49">RWS</option>';
		str+='</select>';
	str+='</div>';
	str+='</div>';
	//str+='<div class="col-sm-2">';
		$("#saveButtonAssignDept").html('<button class="btn btn-default saveBtnCls" attr_alert_id="'+alertId+'">SAVE</button>')
		
		//str+='<button class="btn btn-sm saveBtnCls" attr_alert_id="'+alertId+'">SAVE</button>';
	//str+='</div>';
	$("#alertDepartmentsPopupBody").html(str);
	getAllMainDepartments('newDepartmentId');
}

function getAllMainDepartments(divId)
{
	var jsObj={
	}
	$.ajax({   
		type:'GET',
		url:'getAllMainDepartmentsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
			if(result !=null && result.length>0){
				var newStr='';
				newStr+='<option value="0">Select Department</option>';
				for(var i in result){
				    newStr+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#"+divId).html(newStr);
				$("#"+divId).chosen();
				$("#"+divId).trigger("chosen:updated");
			}
		
	});
}

$(document).on("click",".saveBtnCls",function(){
	var alertId =$(this).attr("attr_alert_id");
	var newDeptId = $("#newDepartmentId").val();
	
	if(newDeptId == null || newDeptId == 0 || newDeptId == "undefined"){
		$("#assignDeptSuccessMsg").html("Please Select Department");
	}else{
		$("#assignDeptSuccessMsg").html("");
	}

	var jsObj={
		alertId : alertId,
		newDeptId : newDeptId
	}
	
	$.ajax({   
		type:'GET',
		url:'changeDepartmentStatusToAlertAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result == 'success')
		{
			$("#assignDeptSuccessMsg").html('Assign Department Updated Successfully');
			setTimeout(function(){
				$( "#assignDeptSuccessMsg" ).fadeOut( "slow", function() {});
				$("#alertDeprtmntPopup").modal('hide');
				rightSideExpandView(alertId);
				setTimeout(function(){
					$("[expanded-block='block1']").show();
				},750);
			},1500);
			  
		}else{
			alert("Please try again");
		}
	});
});

function getPresentAssignedDepartmentOfAlert(alertId){
	$("#presentDeptId").html("");
	var jsObj={
		alertId : alertId
	}	
	$.ajax({   
		type:'GET',
		url:'getPresentAssignedDepartmentOfAlertAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			$("#presentDeptId").html("<label>Present Deparment</label> : "+result[0].name+"");
		}
		
	});
}

$(document).on("click","#updatePrposalStatsId",function(){
	$("#updateProposalStatusChangeMsg").html(spinner);
	var alertId =$(this).attr("attr_alert_id");
	var comment =$("#acceptedStatusChangeComment").val();
	var approvedAmount = 0;
	if(alertStatusGlobalId == 3)
	approvedAmount=$("#approvedAmountId").val();

	if(alertStatusGlobalId != null && alertStatusGlobalId==0){
		alert("Please Select Status");
		return;
	}
	if(globalPrposalCategoryId == 1){
		if(alertStatusGlobalId == 3){
			if(approvedAmount != null && approvedAmount==0){
			alert("Please Enter Approved Amount");
			return;
		 }
		 var numericExpression = /^[0-9]+$/;
			if(!$('#approvedAmountId').val().match(numericExpression)){
				$("#errMsgAprAmuntId").html('<span style="color:red">Please Enter Numeric Value Only.....</span>');
				return;
			}else{
				$("#errMsgAprAmuntId").html('');
			}
		}
	}
	
	if(comment != null && comment==0){
		alert("Please Enter Comment");
		return;
	}
	$("#updatePrposalStatsId").hide();
	if(alertStatusGlobalId == 3){
		var cnfRes = confirm("This Alert Is  Proposal Accepted .Then it will be moved to Action In Progress Status also. ");
		if(cnfRes == true){
			var jsObj={
				alertId : alertId,
				proposalStatusId : alertStatusGlobalId,
				comment : comment,
				approvedAmount : approvedAmount
			}	
	
		$.ajax({   
			type:'GET',
			url:'updateProposalStatusFrAlertAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result == "success"){
				alert("Proposal Status Updated Successfully");
				getCommentsForAlert(alertId);
				setTimeout(function(){
						//$("#commentPostingSpinner").html(" ");
						//$("#updateStatusChangeMsg").html("status not updated successfully,Pls try again");
						$('#alertManagementPopup1').modal('hide');
						
					},1500);
					rightSideExpandView(alertId);
						setTimeout(function(){
							$("[expanded-block='block1']").show().css("transition"," ease-in, width 0.7s ease-in-out");
						},750);
				}else{
					alert("try again");
				}
			});
		  }
		}else{
			var jsObj={
					alertId : alertId,
					proposalStatusId : alertStatusGlobalId,
					comment : comment,
					approvedAmount : approvedAmount
			}	
	
		$.ajax({   
			type:'GET',
			url:'updateProposalStatusFrAlertAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result == "success"){
				alert("Proposal Status Updated Successfully");
				getCommentsForAlert(alertId);
				setTimeout(function(){
						//$("#commentPostingSpinner").html(" ");
						//$("#updateStatusChangeMsg").html("status not updated successfully,Pls try again");
						$('#alertManagementPopup1').modal('hide');
						
					},1500);
					rightSideExpandView(alertId);
						setTimeout(function(){
							$("[expanded-block='block1']").show().css("transition"," ease-in, width 0.7s ease-in-out");
						},750);
				}else{
					alert("try again");
				}
		});
	}
});
var globalDeprtStatus;
function alertDeptmentExistInLogin(alertId){
	var jsObj={
		alertId : alertId,
		proposalStatusId : alertStatusGlobalId
	}	
	$.ajax({   
		type:'GET',
		url:'alertDeptmentExistInLoginAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){
			globalDeprtStatus = result;
		}
	});
}

 function uploadAttachmentsFrRejinderStatus(){
		var uploadHandler = { 
			upload: function(o) {
				var uploadResult = o.responseText;
				}
		};
		YAHOO.util.Connect.setForm('uploadAttachementFrRejoinderStatus',true);  
		YAHOO.util.Connect.asyncRequest('POST','uploadDocumentsForRejoinderStatusAction.action',uploadHandler);
		
};
$(document).on("click",".rejoinderCheckbox",function(){
		$(".rejoinderCheckbox").prop("checked",false);
		$(this).prop("checked",true);
});