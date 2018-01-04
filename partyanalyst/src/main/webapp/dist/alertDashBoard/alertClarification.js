     getAlertVerificationStatus();
	 getAlertVerificationUsers();
	 function getAlertVerificationStatus(){
		var jsObj={
			actionTypeId:1
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAlertVerificationStatusAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 if(result != null && result.length > 0){
					 var str='';
					 for(var i in result){
					   str+='<option value="'+result[i].alertActionTypeStatusId+'">'+result[i].actionTypeStatus+'</option>';
					 }
					$("#verificationStatusSlctBxId").html(str);
				 } 
				 getAlertVerificationDetails();
		  }); 
	 }
	 
	function getAlertVerificationUsers(){
		var jsObj={
			verificationUserTypeId:2
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAlertVerificationUsersAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
			 if(result != null && result.length > 0){
					 var str='';
					 str+='<option value="0">Select Verification Assigned User</option>';
					 for(var i in result){
					   str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
					 }
					$("#verificationUserSeletBoxId").html(str);
				 } 
		  }); 
	 }

$(document).on("click","#isClarificationRequiredChckBxId",function(){
	$("#isClarificationNotRequiredChckBxId").prop("checked",false);
		if($(this).prop("checked") == true){
			$(".hideUpdateBlockCls").show();
			$("#menuUp").show();
			$("#menuDown").hide();
		}else if($(this).prop("checked") == false){
			fileNo = 0;
			$(".commentCls").val('');
			$("#uploadClarificationFileId0").val('');
			$("#extraClarificationUploadFileDiv").html('');
			$(".ClearFileCls").hide();  
			$(".hideUpdateBlockCls").hide();
			$("#menuDown").show();
			$("#menuUp").hide();
		}
});
    $(document).on("change","#verificationStatusSlctBxId",function(){
		var statusId = $(this).val();
		if(statusId > 0){
		  $("#clarificationStatusId").attr("value",statusId);
		}
		
	})
	$(document).on("click","#isClarificationNotRequiredChckBxId",function(){
		$("#isClarificationRequiredChckBxId").prop("checked",false);
		if($(this).prop("checked") == true){
			fileNo = 0;
			$(".hideUpdateBlockCls").hide();
			$("#menuDown").show();
			$("#menuUp").hide();
			$(".commentCls").val('');
			$("#uploadClarificationFileId0").val('');
			$(".ClearFileCls").hide();  
			$("#extraClarificationUploadFileDiv").html('');
		}else if($(this).prop("checked") == false){
		}

   });
   
  $(document).on("click","#uploadFileCheckBoxId",function(){
		if($(this).prop("checked") == true){
			$(".uploadAttachmentDivCls").show();
		}else if($(this).prop("checked") == false){
			$(".uploadAttachmentDivCls").hide();
		}
   });
   
	var fileNo=0;
	$(document).on("click","#addClarificationFile",function(){
		//$(this).closest(".panelHeights").removeAttr("style")
		fileNo = fileNo+1;
		//var c = $(".cloneFileCls").clone(true);
		//c.removeAttr("style");
		//c.attr("id","uploadFileId"+fileNo);
		//c.attr("name","files");
		//c.removeAttr("class").addClass("btn btn-mini");
		$("#extraClarificationUploadFileDiv").append('<li id="cloned'+fileNo+'"><input type="file" name="files" class="btn btn-mini cloneFileCls"/><span class="closeIcon" attr_id="'+fileNo+'">x</span></li>');
	});
	$(document).on("click",".closeIcon",function(){
		var id = $(this).attr("attr_id");
		$("#cloned"+id).remove();
	});
	$(document).on("change","#uploadClarificationFileId0",function(){
		var fileSize = $(this).val();
		 if(fileSize != null && fileSize != undefined && fileSize.trim().length > 0){
			 $(".ClearFileCls").show();
		 }
	});
	$(document).on("click",".ClearFileCls",function(){
		$("#uploadClarificationFileId0").val('');
		$(".ClearFileCls").hide();  
	});
	$(document).on("click","#updateVerificationStatusBtnId",function(){
		$("#alertIdForClarification").val(alertId);
		
		/* Validation Start */
		var commment = $(".commentCls").val();
		 if(commment != null && commment.trim().length == 0){
		     alert("Please Enter Comments");
             return;			 
		 }
		 var assignedUserId = $("#verificationUserSeletBoxId").val();
		 var statusId = $("#clarificationStatusId").val();
		 if (statusId == 0) {
			  if (assignedUserId == 0) {
				     alert("Please Select Verification Assigned User.");
                      return;	 
			  }
		 }
		 /*Validation End */
		 $("#updateVerificationStatusBtnId").attr('disabled','disabled');
	 	var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					displayStatus(uploadResult);
				}
			};

		YAHOO.util.Connect.setForm('updateVerificationStatusFormAction',true);
		YAHOO.util.Connect.asyncRequest('POST','updateAlertVerficationStausAction.action',uploadHandler);
	});
	
	function displayStatus(myResult){
	
		var result = (String)(myResult);
		if(result.search('success') != -1){
			getAletDelt();
			alert("Alert Verification Updated Successfully.");
			$(".commentCls").val('');
			$("#uploadClarificationFileId0").val('');
			$("#extraClarificationUploadFileDiv").html('');
		    $(".ClearFileCls").hide();  
			fileNo = 0;
			$("#updateVerificationStatusBtnId").removeAttr('disabled');
		}else{
			$("#updateVerificationStatusBtnId").removeAttr('disabled');
			alert("Please Try Again.");
		}
	}
	function getAletDelt(){
		getAlertVerificationDetails();
	}
	function getAlertVerificationDetails(){
		$(".hideVarificationStatusCls").show();
		 $(".notificationHeadingCls").hide();
		$("#converSationDtlsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={
			alertId:alertId
		  }
		  	$.ajax({
				type : 'POST',
				url : 'getAlertVerificationDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				 $("#converSationDtlsDivId").html(' ');
				  buildAlertVerificationStatusRlst(result);
		  }); 
	 }
	 function buildAlertVerificationStatusRlst(result){
		 if(result.alertActionTypeStatusId != null && result.alertActionTypeStatusId > 0){
			$("#clarificationStatusId").attr("value",result.alertActionTypeStatusId); 
			$("#verificationStatusSlctBxId").val(result.alertActionTypeStatusId);
		 }else{
			 $("#clarificationStatusId").attr("value",0); 
		 }
		 $("#alertStatusHeadingId").html("STATUS : <span style='color:#2C9C74'>"+result.actionTypeStatus+" <i class='glyphicon glyphicon-ok'></i></span>");
		 userWiseAccessiblilityFunction();
		   var str = '';
		  if(result.conversationList != null && result.conversationList.length > 0){
			  for(var i in result.conversationList){
				  if(i == 0)
				  {
					str+='<p class="text-capital  m_top10" style="font-weight:bold;font-size:15px;color: #3e3e3e;">'+result.conversationList[i].heading+'</p>';  
				  }else{
					  str+='<p class="text-capital  m_top10" style="border-top:2px dashed #AEAEAE;padding-top:10px;font-weight:bold;font-size:15px;color: #3e3e3e;">'+result.conversationList[i].heading+'</p>';
				  }
				 
				 if(result.conversationList[i].comments != null && result.conversationList[i].comments.length > 0){
				   str+='<p style="color:rgba(0,0,0,0.6);font-size:13px;">'+result.conversationList[i].comments+'</p>';	 
				 }
		 	 var documentList = result.conversationList[i].documentList;
			if(documentList != null && documentList.length > 0){
					 str+='<p style="font-size:14px;" class="m_top10"><span style="font-size:15px;font-weight:bold;">Attachments</span> <small>(Click on link to view attachments)</small></p>';
			         str+='<ul class="attachmentsBlock list-inline">';
				var order = 0;
				for(var k in documentList){
					order = order+1;
					var fullName = documentList[k];
					var nameArr = fullName.split(".");
					var type = nameArr[1];	
					var orderStr='';
					    if(k<9){
							orderStr ="0"+order;
						}else{
						  orderStr = order;	
						}
					    var attachment = orderStr+'&nbspAttachment.'+type;
						str+='<li id="showAlertVerificationPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;"><i class="glyphicon glyphicon-paperclip"></i><span class="border"> '+attachment+' </span></li>, ';
				}
			str+='</ul>';
			}
			 if(result.conversationList[i].name != null && result.conversationList[i].name.length > 0){
			   str+='<p class="text-right" style="color:#9F88BA;font-size:12px;font-weight: bold;">Created By : <span class="text-capital">'+result.conversationList[i].name+'</span>( '+result.conversationList[i].updateTime+'&nbsp'+result.conversationList[i].time+' )</p>';  	 
			 }
		  }
		  str+='<hr class="m_top10" style="border-top: 1px solid #ccc;">';
	   $("#converSationDtlsDivId").html(str);
	 }
	 }
	  /* Show Hide Functionality Based on user login */
	  function  userWiseAccessiblilityFunction(){
		  var entitleArrs = [];
		 // <c:if test="${sessionScope.USER.isAdmin != 'true'}">
		  var strArrs = entilementStr.split(",");
				for(var i=0;i<strArrs.length;i++){
					if(i==0){
						entitleArrs.push(strArrs[i].split("[")[1]);
					}else if(i==(strArrs.length-1))
						entitleArrs.push(strArrs[i].split("]")[0]);
					else
						entitleArrs.push(strArrs[i]);
				}
		  var isAdmin="true";
		   for(var i=0;i<entitleArrs.length;i++){
			   if(entitleArrs[i].trim()=="true"){
                 isAdmin = "false";
                }
		   }
		  if(isAdmin == "true"){
				 for(var i=0;i<entitleArrs.length;i++){
						if(entitleArrs[i].trim()=="ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT"){ // info cell user
						
							$("#verificationUserSeletBoxId").hide();
							
							var statusId = $("#clarificationStatusId").val();
							if(statusId > 0){
								$(".hideUpdateBlockCls").show();
								$("#verificationCreationHeadingId").hide();
								$("#alertStatusHeadingId").show();
								$(".verificationStatusCls").show();						
							}else{
								$("#verificationCreationHeadingId").hide();
								$("#alertStatusHeadingId").hide();
								$(".hideVarificationStatusCls").hide();
					
							}
						}
					}

				  for(var i=0;i<entitleArrs.length;i++){
					if(entitleArrs[i].trim()=="CREATE_ALERT_ENTITLEMENT"){ // program committee
						var statusId = $("#clarificationStatusId").val();
						$(".hideVarificationStatusCls").show();
						if(statusId > 0){
							$("#alertStatusHeadingId").show();	
							$("#verificationUserSeletBoxId").hide();	
							$(".hideUpdateBlockCls").show();
							$("#verificationCreationHeadingId").hide();
							if(statusId == 2){
							 $(".notificationHeadingCls").show();	
							}
						}else{
							$("#verificationUserSeletBoxId").show();
						   $("#verificationCreationHeadingId").show();		
						   $("#alertStatusHeadingId").hide();
						}
					}
				}	  	  
		  }
		
	  }
	  
	  $(document).on('click','#showAlertVerificationPdfId',function(){
		//$("#cdrModelId").modal("show");
		var dbFilePath = $(this).attr("attr_filePath");    
     	var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		
		window.open('/Reports/tour_documents/'+dbFilePath,'_blank');
	});
		
		