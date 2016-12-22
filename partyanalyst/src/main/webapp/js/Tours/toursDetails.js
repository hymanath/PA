function getCandidateList(designationId){ 
		var jsObj = {     
			designationId : designationId
		}
		$.ajax({
			type : 'POST',
			url : 'getCandidateListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#memberSlctBxId").empty();
			$("#memberSlctBxId").append("  <option value='0'>Select Name</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#memberSlctBxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
				// $("#memberSlctBxId").append("<option value='-1'>Other Name</option>");  
			}
			$(".selectChosen").trigger("chosen:updated");
		});
	}
	$(document).on("change","#designationSlctBxId",function(){
		var designationId = $(this).val();
		$("#selectedMemberDtslDivId").html(' ');
		$(".showDivCls").hide();
		 if(designationId != null && designationId > 0){
			 getCandidateList(designationId);
		 }
	});
	$(document).on("change","#memberSlctBxId",function(){
		var candidateId = $(this).val();
		 if(candidateId != null && candidateId > 0){
			//$(".otherMemberBlockCls").hide();
			//$(".showDivCls").show();
			//$(".hideProfileDivCls").show();
			$(".ownDivCls").hide();
			$(".inchageDivCls").hide();
			 getCandiateSearchDetails(candidateId);
		 }else if(candidateId == -1){
			//$(".otherMemberBlockCls").show();
			//$(".showDivCls").hide();
			//$(".hideProfileDivCls").hide();
			 //$("#searchValueInputBoxId").val(' ');
			 //$("#searchValueInputBoxId").attr("placeholder","Enter MemberShip Number");	
			getConstituenciesList();
		 }
	});
	function getCandiateSearchDetails(candidateId){
    $("#selectProfileId").show();		
	$("#selectedMemberDtslDivId").html(' ');
	$(".showDivCls").hide();
	var jsObj = { 
			 candidateId : candidateId
			}
		$.ajax({
			type : 'POST',
			url : 'getCandiateDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#profileProcessingImgId").hide();
			
			if(result != null){ 
				buildSelectedProfileRslt(result);
			}
		});
	}
	function buildSelectedProfileRslt(result){
		var str='';
		//str+='<h4 class="panel-title text-capital">selected profile</h4>';
			str+='<ul class="list-inline">';
			str+='<li>';
				str+='<div class="panel panel-default panelProfile">';
					str+='<div class="panel-body">';
						str+='<img src="http://mytdp.com/images/cadre_images/'+result.image+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img>';
						str+='<p>'+result.name+'</p>';
						str+='<p>'+result.memberShipNo+'</p>';
						str+='<p>'+result.mobileNo+'</p>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<label class="checkbox-inline">';
							str+='<input id="profileCheckboxId" type="checkbox"/>Select Profile';
						str+='</label>';
					str+='</div>';
					
				str+='</div>';
			str+='</li>';
            str+='</ul>';
		$("#selectedMemberDtslDivId").html(str);
		$("#selectedProfileId").show();
		var locationList = result.subList;
	     if(locationList != null && locationList.length > 0){
			 $("#inchargeSelectBoxId").removeAttr("name");
			 $("#inchageLocationScopeId").removeAttr("name");
			 $("#inchageownLocationScopeValue").removeAttr("name");
			 
			 $("#ownSelectBoxId").removeAttr("name");
			 $("#ownLocationScopeId").removeAttr("name");
			 $("#ownLocationScopeValue").removeAttr("name");
			 
			 $("#ownLocationScopeId").removeAttr("value");
			 $("#ownLocationScopeValue").removeAttr("value");
			 
			 $("#inchageLocationScopeId").removeAttr("value");
			 $("#inchageownLocationScopeValue").removeAttr("value");
			 
			 $("#ownSelectBoxId").removeClass("isActive");
			 $("#inchargeSelectBoxId").removeClass("isActive");             
 
			 for(var i in locationList){
				 if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==1){
					 if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own DIstrict");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#ownSelectBoxId").attr("name","toursInputVO.ownTours");
						 $("#ownLocationScopeId").attr("name","toursInputVO.ownLocationScopeId");
						 $("#ownLocationScopeValue").attr("name","toursInputVO.ownLocationId");
						 $("#ownSelectBoxId").addClass("isActive");
						 
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge DIstrict");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#inchargeSelectBoxId").attr("name","toursInputVO.inchargeTours");
						 $("#inchageLocationScopeId").attr("name","toursInputVO.inchargeLocationScopeId");
						 $("#inchageownLocationScopeValue").attr("name","toursInputVO.inchargeLocationId");
						 $("#inchargeSelectBoxId").addClass("isActive");
					    $(".inchageDivCls").show();   
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==2){
					 if(locationList[i].type != null && locationList[i].type=="Own"){  
						 $("#ownLabelId").html("Own Parliament");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#ownSelectBoxId").attr("name","toursInputVO.ownTours");
						 $("#ownLocationScopeId").attr("name","toursInputVO.ownLocationScopeId");
						 $("#ownLocationScopeValue").attr("name","toursInputVO.ownLocationId");
						 $("#ownSelectBoxId").addClass("isActive");
						 $(".ownDivCls").show();    
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge Parliament");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#inchargeSelectBoxId").attr("name","toursInputVO.inchargeTours");
						 $("#inchageLocationScopeId").attr("name","toursInputVO.inchargeLocationScopeId");
						 $("#inchageownLocationScopeValue").attr("name","toursInputVO.inchargeLocationId");
						 $("#inchargeSelectBoxId").addClass("isActive");
						 
						$(".inchageDivCls").show(); 
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==3){
					  if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own Assembly");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#ownSelectBoxId").attr("name","toursInputVO.ownTours");
						 $("#ownLocationScopeId").attr("name","toursInputVO.ownLocationScopeId");
						 $("#ownLocationScopeValue").attr("name","toursInputVO.ownLocationId");
						 $("#ownSelectBoxId").addClass("isActive");
						 
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#inchargeLableId").html("Incharge Assembly");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
						 
						 $("#inchargeSelectBoxId").attr("name","toursInputVO.inchargeTours");
						 $("#inchageLocationScopeId").attr("name","toursInputVO.inchargeLocationScopeId");
						 $("#inchageownLocationScopeValue").attr("name","toursInputVO.inchargeLocationId");
						 $("#inchargeSelectBoxId").addClass("isActive");
						 
						$(".inchageDivCls").show();     
					 }
				 }
			 }
		 }
	}
	getDesigationList(); //default call 
	function getDesigationList(){ 	
		var jsObj = { }
		$.ajax({
			type : 'POST',
			url : 'getDesigationListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#designationSlctBxId").empty();
				$("#designationSlctBxId").append("<option value='0'>Select Designation Level</option>");
				for(var i in result){
				 $("#designationSlctBxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");	
				}
			}
			$(".selectChosen").trigger("chosen:updated");
		});
	}
	getAllTourCategorys();
	function getAllTourCategorys(){ 
		var jsObj = {     
			
		}
		$.ajax({
			type : 'POST',
			url : 'getAllTourCategorysAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourCategoryId").empty();
			$("#tourCategoryId").append("  <option value='0'>Select Category</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tourCategoryId").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
				// $("#memberSlctBxId").append("<option value='-1'>Other Name</option>");  
			}
			$(".selectChosen").trigger("chosen:updated");
		});
	}
	getAllTourTypes()
	function getAllTourTypes(){ 
		var jsObj = {     
			
		}
		$.ajax({
			type : 'POST',
			url : 'getAllTourTypesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourTypeId").empty();
			$("#tourTypeId").append("  <option value='0'>Select TourTypes</option>");
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tourTypeId").append("<option value="+result[i].id+">"+result[i].name+"</option>");  
				}
				// $("#memberSlctBxId").append("<option value='-1'>Other Name</option>");  
			}
			$(".selectChosen").trigger("chosen:updated");
		});
	}
	function savingApplication(){
		$("#errMnthId").html("");
		$("#errYearId").html("");
		$(".textErrCls").html("");
		$("#errFileId").html("");
		var flag = true;
		var filerKit = $("#update_TourFileId2").prop("jFiler");
		var monthValue = $("#monthSelectBoxId").val();
		if(monthValue == 0){
			$("#errMnthId").html("Please Select Month.");
			return;
		}
		var yearValue = $("#yearId").val();
		if(yearValue == 0){  
			$("#errYearId").html("Please Select Year.");
			return;  
		}
		$(".isActive").each(function(){
			var value = $(this).val();
			var bool = $.isNumeric(value);
			if(bool == false){
				var errId = $(this).attr("attr_err_id");
				$("#"+errId).html("Please Enter Numbers.");
				flag = false;
				return false;      
			}  
		});
		
		var childEleCount = $(".jFiler-items-list").children().length;
		if(childEleCount > 1){
			$("#errFileId").html("Please Select Only One Document."); 
			filerKit.reset();   
			flag = false;         			
		}
		
		if(flag == false){
			return;  
		}
		var uploadHandler = { 
			upload: function(o) {
				$("#savingAjaxImg").css("display","none");
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('submitApplication',true);  
		YAHOO.util.Connect.asyncRequest('POST','savingTourDtlsApplicationAction.action',uploadHandler);
	}
	function showSbmitStatus(uploadResult){
			$(".clearFieldCls").val(' ');
			$("#monthSelectBoxId").val(0);
			$("#yearId").val(0);    
			$("#update_TourFileId2").val('');        
			$(".selectChosen").trigger("chosen:updated");
			var filerKit = $("#update_TourFileId2").prop("jFiler");
			//if(uploadResult != null && uploadResult == '<pre>"Saved"</pre>'){
			setTimeout(function () {
			$("#successSpanId").html("<center style='color: green; font-size: 16px;'>Saved Successfully</center>").fadeOut(3000);
			}, 500);  
			$("#successSpanId").show();
			$("#successSpanId").html("");       
			filerKit.reset();
			/* }else{
				setTimeout(function () {      
					$("#successSpanId").html("<center style='color: red; font-size: 16px;'>Failed</center>").fadeOut(3000);
					}, 500);  
					$("#successSpanId").show();
					$("#successSpanId").html("");          
			} */
				
	}
	$(document).on("click","#profileCheckboxId",function(){
		if($(this).is(':checked')){
			$(".showDivCls").show();
		}else{
			$(".showDivCls").hide();
		}
	});

