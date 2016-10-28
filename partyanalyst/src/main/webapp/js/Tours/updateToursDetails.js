	function savingApplication(){
		var flag = true;
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
		
		console.log(uploadResult);     
		console.log(111);
	}
	getDesigationList();
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
	$(document).on("change","#designationSlctBxId",function(){
		var designationId = $(this).val();
		 if(designationId != null && designationId > 0){
			 getCandidateList(designationId);
		 }
	});
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
	$(document).on("change","#memberSlctBxId",function(){
		var candidateId = $(this).val();
		 if(candidateId != null && candidateId > 0){
			//$(".otherMemberBlockCls").hide();
			//$(".showDivCls").show();
			//$(".hideProfileDivCls").show();
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
	$("#selectedMemberDtslDivId").html(' ');
	$(".showDivCls").hide();
	$("#profileProcessingImgId").show();
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
		
		var locationList = result.subList;
	     if(locationList != null && locationList.length > 0){
			 for(var i in locationList){
				 if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==1){
					 if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own DIstrict");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#ownLabelId").html("Incharge DIstrict");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
					    $(".inchageDivCls").show(); 
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==2){
					 if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own Parliament");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#ownLabelId").html("Incharge Parliament");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
					   $(".inchageDivCls").show(); 
					 }
				 }else if(locationList[i].locationScopeId != null && locationList[i].locationScopeId==3){
					  if(locationList[i].type != null && locationList[i].type=="Own"){
						 $("#ownLabelId").html("Own Assembly");
						 $("#ownLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#ownLocationScopeValue").attr("value",locationList[i].locationValue);
						 $(".ownDivCls").show();
					 }else if(locationList[i].type != null && locationList[i].type=="Incharge"){
						$("#ownLabelId").html("Incharge Assembly");
						 $("#inchageLocationScopeId").attr("value",locationList[i].locationScopeId);
						 $("#inchageownLocationScopeValue").attr("value",locationList[i].locationValue);
					   $(".inchageDivCls").show(); 
					 }
				 }
			 }
		 }
	}
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}
	$(document).on("click","#profileCheckboxId",function(){
		if($(this).is(':checked')){
			$(".showDivCls").show();
		}else{
			$(".showDivCls").hide();
		}
	});
	function getConstituenciesList(){ 
	var jsObj = { 
			 stateId : 1
			}
		$.ajax({
			type : 'POST',
			url : 'getConstituenciesListAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#constituencySelectBoxId").empty();
				$("#constituencySelectBoxId").append("	<option value='0'>Select Constituency</option>");
				for(var i in result){
				 $("#constituencySelectBoxId").append("<option value="+result[i].id+">"+result[i].name+"</option>");	
				}
				 $("#constituencySelectBoxId").append("<option value='-1'>Other Name</option>");	
				$(".selectChosen").trigger("chosen:updated");
			}
		});
	}
	 $(document).on("change","#constituencySelectBoxId",function(){
		$("#constituencyErrorId").html(' '); 
	 });
	 $(document).on("click",".searchRadioBtnCls",function(){
		  var type = $(this).attr("attr_type");
		  $("#searchValueInputBoxId").attr("placeholder","Enter "+type);	 
	 });
	 $(document).on("click","#searchMemberBtnId",function(){
		 var locationId = $("#constituencySelectBoxId").val();
		 var searchValue = $("#searchValueInputBoxId").val();
		 var designationId = $("#designationSlctBxId").val();
		 var searchType;
		 if(locationId == 0){
		  $("#constituencyErrorId").html("Please Select Constituency.");
          return;		  
		 }
		 if(searchValue == null || searchValue.trim().length==0){
		 $("#constituencyErrorId").html("Please Enter Search Value.");
         return;		 
		 }
		 $("#constituencyErrorId").html(' ');
		  $(".searchRadioBtnCls").each(function() {
		  if($(this).is(":checked")){
			 var type = $(this).attr("attr_type");
			 if(type=="MemberShip Number"){
				searchType="MemberShipNo" ;
			 }else if(type=="Mobile Number"){
				searchType="MobileNo" ; 
			 }else {
				searchType=type ;  
			 }
		  }
	   });
	   //getSearchMembersDetailsAction(locationId,searchType,searchValue,designationId);
	 });
	function getSearchMembersDetailsAction(locationId,searchType,searchValue,designationId){ 
	var jsObj = { 
			 locationId : locationId,
			 searchType : searchType,
			 searchValue : searchValue,
			 designationId :designationId
			}
		$.ajax({
			type : 'POST',
			url : 'getSearchMembersDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			 buildSearchProfileDetails(result);	
			}
		});
	}
	function buildSearchProfileDetails(result){
		$(".showDivCls").show();
		$(".hideProfileDivCls").show();
		var str='';
			str+='<ul class="list-inline">';
			for(var i in result){
				str+='<li>';
				str+='<div class="panel panel-default panelProfile">';
					str+='<div class="panel-body">';
						str+='<img src="http://mytdp.com/images/cadre_images/'+result[i].image+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img>';
						str+='<p>'+result[i].name+'</p>';
						str+='<p>'+result[i].memberShipNo+'</p>';
						str+='<p>'+result[i].mobileNo+'</p>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<label class="checkbox-inline">';
							str+='<input attr_location_scope_str="'+result[i].subList+'" id="profileCheckboxId" type="checkbox"/>Select Profile';
						str+='</label>';
					str+='</div>';
				str+='</div>';
			str+='</li>';	
			}
	        str+='</ul>';
		$("#selectedMemberDtslDivId").html(str);
	}
	