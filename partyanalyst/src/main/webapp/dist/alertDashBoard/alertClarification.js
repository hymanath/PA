$(document).on("click","#isClarificationRequiredChckBxId",function(){
	$("#isClarificationNotRequiredChckBxId").prop("checked",false);
		if($(this).prop("checked") == true){
			$(".hideUpdateBlockCls").show();
		}else if($(this).prop("checked") == false){
			$(".hideUpdateBlockCls").hide();
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
			$(".hideUpdateBlockCls").hide();
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
		$(this).closest(".panelHeights").removeAttr("style")
		fileNo = fileNo+1;
		var c = $(".cloneFileCls").clone(true);
		c.removeAttr("style");
		//c.attr("id","uploadFileId"+fileNo);
		c.attr("name","files");
		c.removeAttr("class").addClass("btn btn-mini");
		$("#extraClarificationUploadFileDiv").append(c);
	});
	
	$(document).on("click","#updateVerificationStatusBtnId",function(){
		$("#alertIdForClarification").val(alertId);
		var commment = $(".commentCls").val();
		 if(commment != null && commment.trim().length == 0){
		     alert("Please Enter Comments");
             return;			 
		 }
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
			$(".commentCls").val(' ');
			$("#uploadClarificationFileId0").val(' ');
			$("#extraClarificationUploadFileDiv").html(' ');
			// getAlertVerificationDetails();
		}else{
			alert("Please Try Again.");
		}
	}
	function getAletDelt(){
		getAlertVerificationDetails();
	}
	getAlertVerificationDetails();
	function getAlertVerificationDetails(){
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
				 if(result != null){
				  buildAlertVerificationStatusRlst(result);
				 }
		  }); 
	 }
	 function buildAlertVerificationStatusRlst(result){
		 if(result.alertActionTypeStatusId != null && result.alertActionTypeStatusId > 0){
			$("#clarificationStatusId").attr("value",result.alertActionTypeStatusId); 
			$("#verificationStatusSlctBxId").val(result.alertActionTypeStatusId);
		 }else{
			 $("#clarificationStatusId").attr("value",0); 
		     return;			
		 }
		 $("#alertStatusHeadingId").html("<h4>Status:<span style='green'>"+result.actionTypeStatus+"</span></h4>");
		 userWiseAccessiblilityFunction();
		   var str = '';
		  if(result.conversationList != null && result.conversationList.length > 0){
			  for(var i in result.conversationList){
				 str+='<h4 class="text-capital">'+result.conversationList[i].heading+'</h4>';
				 if(result.conversationList[i].comments != null && result.conversationList[i].comments.length > 0){
				   str+='<p>'+result.conversationList[i].comments+'</p>';	 
				 }
		 	 var documentList = result.conversationList[i].documentList;
			if(documentList != null && documentList.length > 0){
					 str+='<h4 class="text-capital m_top20">Attachments</h4>';
			         str+='<ul class="attachmentsBlock">';
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
						str+='<li id="showAlertVerificationPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;"><i class="glyphicon glyphicon-paperclip"></i>'+attachment+'</li>';
				}
			str+='</ul>';
			}
			 if(result.conversationList[i].name != null && result.conversationList[i].name.length > 0){
			   str+='<p class="text-right">Created By:'+result.conversationList[i].name+'('+result.conversationList[i].updateTime+'&nbsp'+result.conversationList[i].time+')</p>';  	 
			 }
		  }
	   $("#converSationDtlsDivId").html(str);
	 }
	 }
	  /* Show Hide Functionality Based on user login */
	  function  userWiseAccessiblilityFunction(){
		  var entitleArrs = [];
		  var strArrs = entilementStr.split(",");
				for(var i=0;i<strArrs.length;i++){
					if(i==0){
						entitleArrs.push(strArrs[i].split("[")[1]);
					}else if(i==(strArrs.length-1))
						entitleArrs.push(strArrs[i].split("]")[0]);
					else
						entitleArrs.push(strArrs[i]);
				}
		  
		 for(var i=0;i<entitleArrs.length;i++){
				if(entitleArrs[i].trim()=="ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT"){ // info cell user
					var statusId = $("#clarificationStatusId").val();
					if(statusId > 0){
						$(".hideUpdateBlockCls").show();
						$("#verificationCreationHeadingId").hide();
					    $("#alertStatusHeadingId").show();
					    $(".verificationStatusCls").show();						
					}
				}
			}

		  for(var i=0;i<entitleArrs.length;i++){
			if(entitleArrs[i].trim()=="CREATE_ALERT_ENTITLEMENT"){ // program committee
				var statusId = $("#clarificationStatusId").val();
				if(statusId > 0){
			    	$("#alertStatusHeadingId").show();	
					$(".hideUpdateBlockCls").show();
					$("#verificationCreationHeadingId").hide();
				}else{
				   $("#verificationCreationHeadingId").show();		
				   $("#alertStatusHeadingId").hide();
				}
			}
		}	  
	  }
	  
	  $(document).on('click','#showAlertVerificationPdfId',function(){
		//$("#cdrModelId").modal("show");
		var dbFilePath = $(this).attr("attr_filePath");    
     	var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#alertDocumentModalId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open('http://ieee802.org/secmail/docIZSEwEqHFr.doc','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				$("#alertDocumentModalId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				$("#alertDocumentModalId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
				$("#alertDocumentModalId").modal("show");
				str += '<iframe src="https://docs.google.com/gview?url=http://mytdp.com/Reports/tour_documents/'+dbFilePath+'&embedded=true" frameborder="0" style="width: 100%; height: 500px;">';
				str += '</iframe>';
			}
			if(extName.trim()=="xls" || extName.trim()=="xlsx"){      
				window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}            
			$("#alertDocumentBodyId").html(str);
			$("#alertDocumentModalId").attr("isModalOpened","true");
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
		