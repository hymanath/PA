var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var alreadyCandidateId=[];
var searchCandidateIds=[];
var diffArr=[];
var commonArr=[];
var globalWorkTypeCount=0;
setTimeout(function(){ 
	$(".chosen-select").chosen();
	//$('#self').trigger('click');
	buildSelfAndRepresenteeDetails("self");
}, 2000);

$("#dateRangePickerMGNF").daterangepicker({
	singleDatePicker: true,
	locale: {
	  format: 'DD-MM-YYYY'
	},
	
});
$('#dateRangePickerMGNF').on('apply.daterangepicker', function(ev, picker) {
});	
$(document).on("click",".removeWorkCls",function(){
	var divIdStr = $(this).attr('attr_id');
	$('#'+divIdStr+'').remove();
});
$(document).on("click",".selfRepresenceCls",function(){
	if($(this).is(":checked")){
		var typeVal =  $(this).attr("attr_type")
		if(typeVal == "self"){
			$("#representDetailsDivId").html('');
			alreadyCandidateId=[]
			globalWorkTypeCount='';
			globalWorkTypeCount=0;
			buildSelfAndRepresenteeDetails(typeVal)
		}else if(typeVal == "represent"){
			$("#selfDetailsDivId").html('');
			alreadyCandidateId=[]
			globalWorkTypeCount='';
			globalWorkTypeCount=0;
			buildSelfAndRepresenteeDetails(typeVal)
			getAllDistrictsListInState();
			getPetitionDesignationList();
		}
	}
});

//Disignation Build
function getPetitionDesignationList(){
    $("#designationsId").html('');
	  var json = {
		   searchType:"refCandidateDesignations"// all/refCandidateDesignations/petitionGivenRefCandidateDesignations
	  };
	$.ajax({              
		type:'POST',    
		url: 'getPetitionDesignationList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#designationsId").append('<option value="0">Select Designation</option>');
			for(var i in result){
				$("#designationsId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#designationsId").trigger('chosen:updated');
	});	
}
//District Build
function getAllDistrictsInState(typeVal,counterId,typeChange){
	//alert(typeChange)
	var searchType="all";
	var searchId =0;
	if(typeChange == "Inner"){
		$("#districtInnerId"+typeVal+counterId).html('');
	}else{
		if(typeVal=="popup"){
			$("#districtCandId").html('');
			searchType = "refCandidate";
				searchId = $('#designationsId').val();
		}else if(counterId == null || counterId ==""){
			$("#district"+typeVal+counterId).html('');
		}else if(counterId !="" && parseInt(counterId)>=0){
			$("#districtId"+typeVal+counterId).html('');
		}
	}
	var json = {
		  stateId:"1",
		  searchType:searchType,
		  searchId:searchId
		}
	$.ajax({                
		type:'POST',    
		url: 'getAllDistrictsInState',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){	
			
			if(typeChange == "Inner"){
				$("#districtInnerId"+typeVal+counterId).append('<option value="0">Select District</option>');
			}else{
				if(typeVal== "popup"){
					$("#districtCandId").append('<option value="0">All</option>');
				}else if(counterId == null || counterId == ""){		
					$("#district"+typeVal+counterId).append('<option value="0">Select District</option>');
				}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#districtId"+typeVal+counterId).append('<option value="0">Select District</option>');
				}
			}
			for(var i in result){				
				
				if(typeChange == "Inner"){
						
					$("#districtInnerId"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}else{
					if(typeVal=="popup"){
						$("#districtCandId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}else if(counterId == null || counterId ==""){		
						$("#district"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}else  if(counterId !="" && parseInt(counterId)>=0){
						$("#districtId"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
			}
		}
		
		
		if(typeChange == "Inner"){
			$("#districtInnerId"+typeVal+counterId).trigger('chosen:updated');
		}else{
			if(typeVal=="popup"){
				$("#districtCandId").trigger('chosen:updated');
			}
			else if(counterId == null || counterId == ""){
				$("#district"+typeVal+counterId).trigger('chosen:updated');
			}		
				
			else if(counterId != "" && parseInt(counterId) >= 0){
				$("#districtId"+typeVal+counterId).trigger('chosen:updated');
			}
				
		}
			
		
		
	});	
}
//Constituency Build
function getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,typeChange){
	  $("#constituencyCanId").html('');
	   var searchType= "all";
		var searchId=0;
		if(typeChange == "Inner"){
			 $("#constituencyInnerId"+typeVal+counterId).html('');
		}else{
			if(typeVal== "popup"){
				searchType = "refCandidate";
				searchId = $('#designationsId').val();
			}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#constituencyId"+typeVal+counterId).html('');
			}
		}
	 
	  var json = {
		  districtId:levelVal,
		  searchType:typeVal,
		  searchId:searchId
		}
	$.ajax({                
		type:'POST',    
		url: 'getConstituencyNamesByDistrictId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 
			 if(typeChange == "Inner"){
				  $("#constituencyInnerId"+typeVal+counterId).append('<option value="0">Select Constituency</option>');
			 }else{
				 $("#constituencyId"+typeVal+counterId).append('<option value="0">Select Constituency</option>');
			 }
			
			 $("#constituencyCanId").append('<option value="0">All</option>');
			for(var i in result){
				
				if(typeChange == "Inner"){
					$("#constituencyInnerId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				}else{
					$("#constituencyId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
					$("#constituencyCanId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				}
				
				
			}
		}
		
		if(typeChange == "Inner"){
			$("#constituencyInnerId"+typeVal+counterId).trigger('chosen:updated');
		}else{
			$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
			$("#constituencyCanId").trigger('chosen:updated');
		}
		
		
	});	
}

function getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal,typeChange){
	    $("#mandalId"+typeVal+counterId).html('');
		if(typeChange == "Inner"){
			$("#mandalInnerId"+typeVal+counterId).html('');
		}
	    
		
	  var searchType="all";
	  var searchId=0;
			if(typeVal=="popup"){
				searchType = "refCandidate";
				searchId = $('#designationsId').val();
			}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#mandalId"+typeVal+counterId).html('');
			}
		
	  var json = {
		  constituencyId:levelVal,
		  searchType:"all",
		  searchId:searchId
		}        
	$.ajax({                
		type:'POST',    
		url: 'getTehsilsAndLocalElectionBodyForConstituencyId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#mandalId"+typeVal+counterId).append('<option value="0">Select Mandal</option>');
			 if(typeChange == "Inner"){
				 $("#mandalInnerId"+typeVal+counterId).append('<option value="0">Select Mandal</option>');
			 }
			 
			
			for(var i in result){
				var tehsilId = result[i].key;
				var levelId = tehsilId.toString().substr(1, 4);
				$("#mandalId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				if(typeChange == "Inner"){
					$("#mandalInnerId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
				
				
				
			}
		}
		$("#mandalId"+typeVal+counterId).trigger('chosen:updated');
		if(typeChange == "Inner"){
			$("#mandalInnerId"+typeVal+counterId).trigger('chosen:updated');
		}
		
		
	});	
}

function buildSelfAndRepresenteeDetails(typeVal){
	$("#"+typeVal+"DetailsDivId").html(spinner);
	var str='';
	if(typeVal == "represent"){
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="font_weight text-capital f_22">Representee Details:</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';
				str+='<label>VOTER ID</label>';
				str+='<input type="text"  name="voterCardNo"  value=""  class="form-control m_top10 height45" id="voterId'+typeVal+'" placeholder="Enter Voter ID">';
				
			str+='</div>';
			
			str+='<div class="col-sm-3">';
				str+='<label>NAME</label>';//$("#name"+typeVal+"Err").html("<h5>Please Enter Name</h5>");
				str+='<input type="text"  name="name"  value="" class="form-control m_top10 height45" id="name'+typeVal+'" placeholder="Enter Name">';
				
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>MOBILE NO</label>';
				str+='<input type="text" name="mobileNO" maxlength="10" value=""  class="form-control m_top10 height45" id="mobileNumber'+typeVal+'" placeholder="Enter Mobile Number">';
				
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>EMAIL-ID</label>';
				str+='<input type="text" name="email"   value="" class="form-control m_top10 height45" id="emailId'+typeVal+'" placeholder="Enter E-mail ID">';
				
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>DISTRICT</label>';
				str+='<select   name="addressVO.districtId" class="form-control chosen-select m_top10" id="district'+typeVal+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
				
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>CONSTITUENCY</label>';
				str+='<select  name="addressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+typeVal+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
				
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>MANDAL/MUNCI.</label>';
				str+='<select   name="addressVO.tehsilId"  class="form-control chosen-select m_top10" id="mandal'+typeVal+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
				
			str+='</div>';
			str+='<div class="col-sm-3">';	
			str+='<label>REPRESENTEE DESIGNATION.</label>';
			str+='<select   name="addressVO.tehsilId"  class="form-control chosen-select m_top10" id="designation'+typeVal+'">';
				str+='<option value="0">Select Designation</option>';
			str+='</select>';
			
		str+='</div>';
		str+='</div>';
	}
	
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				str+='<div id="candidateDetails'+typeVal+'DivId"></div>';
			str+='</div>';
		str+='</div>';		
			
			if(typeVal=='self'){
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+typeVal+'">ADD SELF MEMBER</button>';
							
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}else{
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+typeVal+'">ADD REFERRAL</button>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
		
		
		str+='<div class="row m_top10">';
				str+='<div class="col-sm-8">';
					str+='<label>COMPLETE WORK DISCRIPTION <span class="starColor">*</span><span id="completeWorkNameId'+typeVal+'"></span></label>';
					str+='<input type="text"  name="worksList[0].workName"  value="" class="form-control m_top5 height45" id="name'+typeVal+'" placeholder="Enter Name">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>NO OF WORKS <span class="starColor">*</span><span id="noOfWorksId'+typeVal+'"></span></label>';
						str+='<input   name="worksList[0].noOfWorks"   type="text" class="form-control m_top5 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work" onkeyUp="enableWorks(this.value,\'workDetailsDivId'+typeVal+'\',\''+typeVal+'\');">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>WORKS IN COST (in Lakh) <span class="starColor">*</span><span id="workCostId'+typeVal+'"></span></label>';
					str+='<input type="text"  name="worksList[0].estimateCost" class="form-control m_top5 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost">';
				str+='</div>';
		str+='</div>';
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
					str+='<h4 class="panel-title f_18"> UPLOAD WORK DOCUMENTS </h4>';
					str+='<input type="file"  attr_name="worksList[0]" name="" attr_image_tyep="projImage"  id="projectDocUpload'+typeVal+'" multiple="multiple" class="m_top20"/>';
			str+='</div>';
		str+='</div>';	
		
		str+='<div class="row">';
			str+='<div id="workDetailsDivId'+typeVal+'" style="display:none;"></div>';
		str+='</div>';
		
	str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
					//str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+typeVal+'">ADD REFERRAL</button>';
					str+='<div class="col-sm-12 m_top20"><span class="addLocationCss m_top20 saveRepresentRequestDetails" style="cursor:pointer;background-color:green;" attr_type="'+typeVal+'">SAVE DETAILS</span><span id="savingDetailsSpinner"></span><span class="col-sm-offset-4" id="statusMsgAppntReqt"></span></div>';
			str+='</div>';
		str+='</div>';
		
	$("#"+typeVal+"DetailsDivId").html(str);
	initializeFileProjDoc(typeVal);
	buildTemplateWorkDetails(typeVal);
}

$(document).on("change","#districtCandId",function(){
	var levelVal = $(this).val();
	getConstituencyNamesByDistrictId(levelVal,"","popup"); 
	
});

$(document).on("change","#designationsId",function(){	
	$("#districtCandDivId").hide();
	$("#constituencyCandDivId").hide();
	var value = $(this).val();
	if(parseInt(value)>0){
		if (value== 11 || value== 4){
			$("#constituencyCanId").html("");
			$("#candidateDetailsDivId").html('');
			$("#constituencyCandDivId").show();
			getConstituencyNamesByDistrictId(0,0,'popup',"");
		}else if (value== 16 || value== 2){
			$("#constituencyCandDivId").hide();
			$("#districtCandDivId").show();
			getAllDistrictsInState("popup","");
		}else{
			$("#constituencyCandDivId").show();
			$("#districtCandDivId").show();
			var typeVal = $(this).attr("attr_type");
			$(".selectionSearchDetailsCls").attr("attr_type",typeVal);
			getAllDistrictsInState("popup","");
			if(typeVal == "self"){
				$("#candidateDetailsDivId").html('');
				
			}else{
				$("#candidateDetailsDivId").html('');
				
			}
		}
	}
});
//Search Modal
$(document).on("click",".searchCandidateCls",function(){
	var typeVal = $(this).attr("attr_type");
	$(".selectionSearchDetailsCls").attr("attr_type",typeVal);
	if(typeVal == "self"){
		$("#candidateDetailsDivId").html('');
		$("#candidateSearchModelDivId").modal("show");
		getPetitionDesignationList();
	}else{
		$("#candidateDetailsDivId").html('');
		$("#candidateSearchModelDivId").modal("show");
		getPetitionDesignationList();
	}
});

$(document).on("click",".selectionSearchDetailsCls",function(){
	 $("#desiganationIErrDivd").html("");
	var locationValue=0;
	var locationLevelId=0;
	var desiganationId = $("#designationsId").val();
	if(desiganationId == null || desiganationId ==0){
		$("#desiganationIErrDivd").html("<h5>Please select desiganation</h5>");
		return;
	}
	var districtCandId = $("#districtCandId").val();
	locationValue=districtCandId;
	if(districtCandId !=null && districtCandId > 0){
		locationLevelId=3;
	}
	var constituencyCanId = $("#constituencyCanId").val();
	if(constituencyCanId !=null && constituencyCanId > 0){
		locationValue=constituencyCanId;
		locationLevelId=4;
	}
	var typeVal = $(this).attr("attr_type");
	searchCandidateIds=[];
	diffArr=[];
	commonArr=[];
	//alreadyCandidateId=[]
	
	if (desiganationId== 4 || desiganationId== 11){
		locationLevelId=10;
	}
		
	getPetitionReferredMemberDetails(desiganationId,locationLevelId,locationValue,typeVal);
	
});

function getPetitionReferredMemberDetails(desiganationId,locationLevelId,locationValue,typeVal){
    $("#candidateDetailsDivId").html(spinner);
	  var json = {
		 deptId:desiganationId,
		 locationLevelId:locationLevelId,
		 locationValue:locationValue 
		}           
	$.ajax({              
		type:'POST',    
		url: 'getPetitionReferredMemberDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildPetitionReferredMemberDetails(result,typeVal);
		}else{
			$("#candidateDetailsDivId").html("No Data Available");
		}
	});	
}

var refCandCount=0;
$(document).on("click",".candidateAddedView",function(){
	
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	var representeeType='SELF';
	if(typeVal=='representee'){
		representeeType='REPRESENTEE';
	}
		
	alreadyCandidateId.push(parseInt(candidateId));
	
	$("#candidateDetails"+typeVal+"DivId").append($("#candidatesAppendDiv"+candidateId).html());
	
	$("#candidateDetails"+typeVal+"DivId").find(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-3").addClass("col-sm-2");
	$("#candidateDetails"+typeVal+"DivId").find("#fileUpload"+typeVal+candidateId).show();
	
	$("#fileUpload"+typeVal+candidateId).append('<div class="col-sm-4" style="margin-top:-20px;"><label>REFERAL LETTER</label><input type="file"   attr_name="referList['+refCandCount+']" name="" attr_image_tyep="refImage"  id="mainBlockFileUpload'+candidateId+''+typeVal+'" multiple="multiple" class=""/></div>');
	if(representeeType =='SELF'){
		$("#candidateDetails"+typeVal+"DivId").append('<input type="hidden" id="petitionRef'+refCandCount+'" name="refCandidateId" value="'+candidateId+'" />');	
	}
	else if(representeeType =='REPRESENTEE'){
		$("#candidateDetails"+typeVal+"DivId").append('<input type="hidden" id="petitionRef'+refCandCount+'" name="referList['+refCandCount+'].refCandidateId" value="'+candidateId+'" />');	
	}
	refCandCount=refCandCount+1;
	$(this).parent().find(".representation-selected").addClass("display_block");
	$("#candidatesAppendDiv"+candidateId).find("#candidate"+typeVal+candidateId).removeClass("candidateAddedView");
	
	$("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").removeClass("candidateAddedView");
	$("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").css("cursor","auto");
	$(".showRemoveIcon").attr("attr_candidateId",candidateId)
	$("#candidateDetails"+typeVal+"DivId").find(".showRemoveIcon").show();
	initializeFileUploadMainBlock(typeVal,candidateId);
});

$(document).on("click",".ccccc",function(){
	
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	
	$("#candidateDetails"+typeVal+"DivId").find("#candidate"+typeVal+candidateId).remove();
	$("#candidatesAppendDiv"+candidateId).find(".representation-selected").removeClass("display_block");
	$("#candidatesAppendDiv"+candidateId).find(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-2").addClass("col-sm-3");
	
	/* alert($("#candidatesAppendDiv"+candidateId).find(".representation-selected").html())
	$("#candidatesAppendDiv"+candidateId).find(".representation-selected").removeClass("display_block");
	
	$("#candidatesAppendDiv"+candidateId).find(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-2").addClass("col-sm-3");
	$("#candidatesAppendDiv"+candidateId).find("#fileUpload"+typeVal+candidateId).hide(); */
	
	
	
	setTimeout(function(){ 
		$("#candidatesAppendDiv"+candidateId).find(".bgColorCandidatesView").addClass("candidateAddedView")
	}, 2000);
	
	var itemtoRemove = parseInt(candidateId);
	alreadyCandidateId.splice($.inArray(itemtoRemove, alreadyCandidateId),1);
	return;
});
$(document).on("click",".showRemoveIcon",function(){
	var typeVal = $(this).attr("attr_type");
	var candidateId = $("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").attr("attr_candidateId");
	
	$(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-2").addClass("col-sm-3");
	//$("#candidateDetails"+typeVal+"DivId").find(".showRemoveIcon").hide();
	$("#candidateDetails"+typeVal+"DivId").find("#candidate"+typeVal+candidateId).remove();
	var itemtoRemove = parseInt(candidateId);
	alreadyCandidateId.splice($.inArray(itemtoRemove, alreadyCandidateId),1);
});
function buildPetitionReferredMemberDetails(result,typeVal){
	var str='';
	for(var i in result){
		searchCandidateIds.push(result[i].referrerCandidateId)
	}
	$.grep(searchCandidateIds, function(el) {
		if ($.inArray(el, alreadyCandidateId) == -1){
			diffArr.push(el);
		}else if($.inArray(el, alreadyCandidateId ) !== -1){
			commonArr.push(el);
		} 
			
	});
	//console.log(diffArr)
	//console.log(commonArr)
	//console.log(alreadyCandidateId) 
	str+='<div  class="col-sm-12 table-responsive">';
		str+='<table style="width:100%" class="table table-condensed table_padding" id="candidatesTab">';	
			str+='<thead>';
				str+='<tr>';
					str+='<th></th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
		if(alreadyCandidateId !=null && alreadyCandidateId.length>0){
				for(var i in result){
				 
					for(var j in commonArr){
						
						if(commonArr[j] == result[i].referrerCandidateId){
							str+='<tr><td>';
							str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'" style="position:relative;">';
							str+='<div class="bgColorCandidatesView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
							
								str+='<div class="row">';
										str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidateId="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"></i></div>';
										
										str+='<div class="representation-selected display_block" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove ccccc" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff" ></i></span></div>';
										
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Name:</h5>';
												str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
												str+='<h5 class="m_top10 font_weight">Designation</h5>';
												str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
												str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'">'+result[i].candidateAddressVO.assemblyName+'  Constituency,</h5>';
												str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">'+result[i].candidateAddressVO.districtName+'  District,</h5>';
											str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
										str+='<div class="nameAddressCss">';
											str+='<h5 class="font_weight">Party:</h5>';
											str+='<h5 class="m_top5">Telugu Desam Party</h5>';
											str+='<h5 class="m_top10 font_weight">Contact Details</h5>';
											if(result[i].petitionMemberVO.emailId != null && result[i].petitionMemberVO.emailId.length > 0){
												str+='<h5 class="text_bold m_top10" >Email-id:  '+result[i].petitionMemberVO.emailId+'</h5>';
											}else{
												str+='<h5 class="text_bold m_top10">Email-id: -</h5>';
											}
											if(result[i].petitionMemberVO.mobileNo != null && result[i].petitionMemberVO.mobileNo.length > 0){
												str+='<h5 class="m_top5">Contact No : '+result[i].petitionMemberVO.mobileNo+'</h5>';
											}else{
												str+='<h5 class="m_top5">Contact No : -</h5>';
											}
											str+='<h5 class="m_top5">'+result[i].candidateAddressVO.districtName+' District.</h5>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Address:</h5>';
										/*		str+='<h5 class="m_top5">H No :</h5>';
												str+='<h5 class="m_top10">14-48-20/58, Kothapet</h5>';
												str+='<h5 class="text_bold m_top10">Sangadigunta, Kothapeta,</h5>';
												str+='<h5 class="m_top5">Guntur,</h5>';
												str+='<h5 class="m_top5">Andhra Pradesh 522001.</h5>';
												*/
												str+='<h5 class="m_top5">Not Available</h5>';
											str+='</div>';
									str+='</div>';
									str+='<div id="fileUpload'+typeVal+''+result[i].referrerCandidateId+'" style="display:none"></div>';
								str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</td></tr>';
						}
					}		
				
			}
			
			for(var i in result){
				
					for(var j in diffArr){
						if(diffArr[j] == result[i].referrerCandidateId){
							str+='<tr><td>';
							str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'" style="position:relative;">';
							str+='<div class="bgColorCandidatesView candidateAddedView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
							
								str+='<div class="row">';
									str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidateId="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"></i></div>';
									
										str+='<div class="representation-selected" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove ccccc" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff" ></i></span></div>';
										
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Name:</h5>';
												str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
												str+='<h5 class="m_top10 font_weight">Designation</h5>';
												str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
												str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'">'+result[i].candidateAddressVO.assemblyName+'  Constituency,</h5>';
												str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">'+result[i].candidateAddressVO.districtName+'  District,</h5>';
											str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
										str+='<div class="nameAddressCss">';
											str+='<h5 class="font_weight">Party:</h5>';
											str+='<h5 class="m_top5">Telugu Desam Party</h5>';
											str+='<h5 class="m_top10 font_weight">Contact Details</h5>';
											if(result[i].petitionMemberVO.emailId != null && result[i].petitionMemberVO.emailId.length > 0){
												str+='<h5 class="text_bold m_top10" >Email-id:  '+result[i].petitionMemberVO.emailId+'</h5>';
											}else{
												str+='<h5 class="text_bold m_top10">Email-id: -</h5>';
											}
											if(result[i].petitionMemberVO.mobileNo != null && result[i].petitionMemberVO.mobileNo.length > 0){
												str+='<h5 class="m_top5">Contact No : '+result[i].petitionMemberVO.mobileNo+'</h5>';
											}else{
												str+='<h5 class="m_top5">Contact No : -</h5>';
											}
											str+='<h5 class="m_top5">'+result[i].candidateAddressVO.districtName+' District.</h5>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Address:</h5>';
										/*		str+='<h5 class="m_top5">H No :</h5>';
												str+='<h5 class="m_top10">14-48-20/58, Kothapet</h5>';
												str+='<h5 class="text_bold m_top10">Sangadigunta, Kothapeta,</h5>';
												str+='<h5 class="m_top5">Guntur,</h5>';
												str+='<h5 class="m_top5">Andhra Pradesh 522001.</h5>';
												*/
												str+='<h5 class="m_top5">Not Available</h5>';
											str+='</div>';
									str+='</div>';
									str+='<div id="fileUpload'+typeVal+''+result[i].referrerCandidateId+'" style="display:none;"></div>';
								str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</td></tr>';
						}
					}
				
			} 
		}else{
			for(var i in result){
				str+='<tr><td>';
				 str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'" style="position:relative;">';
					str+='<div class="bgColorCandidatesView candidateAddedView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
					
					str+='<div class="row">';
					str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidateId="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"></i></div>';
					str+='<div class="representation-selected" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove ccccc" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff" ></i></span></div>';
					str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
							str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
							str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
					str+='</div>';
					str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
						str+='<div class="nameAddressCss">';
							str+='<h5 class="font_weight">Name:</h5>';
							str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
							str+='<h5 class="m_top10 font_weight">Designation</h5>';
							str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
							str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'">'+result[i].candidateAddressVO.assemblyName+'  Constituency,</h5>';
							str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">'+result[i].candidateAddressVO.districtName+'  District,</h5>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
						str+='<div class="nameAddressCss">';
							str+='<h5 class="font_weight">Party:</h5>';
							str+='<h5 class="m_top5">Telugu Desam Party</h5>';
							str+='<h5 class="m_top10 font_weight">Contact Details</h5>';
							if(result[i].petitionMemberVO.emailId != null && result[i].petitionMemberVO.emailId.length > 0){
								str+='<h5 class="text_bold m_top10" >Email-id:  '+result[i].petitionMemberVO.emailId+'</h5>';
							}else{
								str+='<h5 class="text_bold m_top10">Email-id: -</h5>';
							}
							if(result[i].petitionMemberVO.mobileNo != null && result[i].petitionMemberVO.mobileNo.length > 0){
								str+='<h5 class="m_top5">Contact No : '+result[i].petitionMemberVO.mobileNo+'</h5>';
							}else{
								str+='<h5 class="m_top5">Contact No : -</h5>';
							}
							str+='<h5 class="m_top5">'+result[i].candidateAddressVO.districtName+' District.</h5>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
						str+='<div class="nameAddressCss">';
							str+='<h5 class="font_weight">Address:</h5>';
					/*		str+='<h5 class="m_top5">H No :</h5>';
							str+='<h5 class="m_top10">14-48-20/58, Kothapet</h5>';
							str+='<h5 class="text_bold m_top10">Sangadigunta, Kothapeta,</h5>';
							str+='<h5 class="m_top5">Guntur,</h5>';
							str+='<h5 class="m_top5">Andhra Pradesh 522001.</h5>';
							*/
							str+='<h5 class="m_top5">Not Available</h5>';
						str+='</div>';
					str+='</div>';
						str+='<div id="fileUpload'+typeVal+''+result[i].referrerCandidateId+'" style="display:none;"></div>';
					str+='</div>';
					
					str+='</div>';
				 str+='</div>';
				 str+='</td></tr>';
			}
		}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
	$("#candidateDetailsDivId").html(str);
	$('#candidatesTab').dataTable({
		"paging":   true,
		"info":     false,
		"searching": true,
		"autoWidth": true,
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]		
	});	
}

function buildTemplateWorkDetails(typeVal){
	var str='';
	str+='<div class="col-sm-12" id="mainWorkDivId'+globalWorkTypeCount+'0">';
		str+='<div style="border:3px solid #dddddd;padding:10px;">';
			str+='<div  cloned_block_'+typeVal+'='+globalWorkTypeCount+'>';
				str+='<h3 class="panel-title f_22"></h3>';
				
				str+='<div class="row m_top10" >';
				//str+='<div class="pull-right removeWorkCls" attr_id="mainWorkDivId'+globalWorkTypeCount+'0" attr_type="self" attr_candidateid="1" style="cursor:pointer;margin-top: -30px"><i class="glyphicon glyphicon-remove"></i></div>';				
					str+='<div class="col-sm-3">';
							str+='<label>DEPARTMENT <span class="starColor">*</span><span class="WorkTypeWiseDepartmentId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
							str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].deptId"  class="form-control chosen-select m_top10 validateCls"  id="WorkTypeWiseDepartmentId'+typeVal+''+globalWorkTypeCount+'0" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0" attr_select_type="selectbox">';
							str+='</select>';
						str+='</div>';
					
					str+='<div class="col-sm-3">';
						str+='<label>SUBJECT <span class="starColor">*</span><span class="subjectId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
						str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].subjectId"  class="form-control chosen-select m_top10 validateCls"  id="subjectId'+typeVal+''+globalWorkTypeCount+'0" onChange=getPetitionSubSubjectList(this.value,"subSubjectId'+typeVal+''+globalWorkTypeCount+'","0") attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0" attr_select_type="selectbox">';
							str+='<option value="0">Select Subject</option>';
						str+='</select>';
					str+='</div>';
					
					str+='<div class="col-sm-3">';
						str+='<label>SUB-SUBJECT <span class="starColor">*</span><span class="subSubjectId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
						str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].subSubjectId"  class="form-control chosen-select m_top10 validateCls"  id="subSubjectId'+typeVal+''+globalWorkTypeCount+'0" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0" attr_select_type="selectbox">';
							str+='<option value="0">Select Sub Subject</option>';
						str+='</select>';
					str+='</div>';
								
				str+='</div>';
				
				str+='<div class="row m_top10" id="workDivId'+globalWorkTypeCount+'0">';
				//str+='<div class="pull-right removeWorkCls" attr_id="workDivId'+globalWorkTypeCount+'0" attr_type="self" attr_candidateid="1" style="margin-right: 15px;cursor:pointer"><i class="glyphicon glyphicon-remove"></i></div>';
					str+='<div class="col-sm-12">';
						str+='<div class="bg_color_view">';
							str+='<h3 class="panel-title f_16 font_weight">WORK 1</h3>';
							str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<label> WORK TYPE <span class="starColor">*</span><span class="workTypeId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].workTypeId"  class="form-control chosen-select m_top10 validateCls" id="workTypeId'+typeVal+''+globalWorkTypeCount+'0" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
											str+='<option value="0">Select Work Type</option>';
										str+='</select>';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>WORK IN COST (in Lakh) <span class="starColor">*</span><span class="appendWorkCost'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<input type="text"  name="worksList['+globalWorkTypeCount+'].subWorksList[0].estimateCost" class="form-control m_top5 height45 validateCls" id="appendWorkCost'+typeVal+''+globalWorkTypeCount+'0" placeholder="Enter Work Cost" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<input type="text"  name="worksList['+globalWorkTypeCount+'].subWorksList[0].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsId'+typeVal+''+globalWorkTypeCount+'0" placeholder="Enter" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>eOFFICE-ID <span class="starColor">*</span><span class="appendEofficeId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<input type="text"  name="worksList['+globalWorkTypeCount+'].subWorksList[0].eOfficeId" class="form-control m_top5 height45 validateCls" id="appendEofficeId'+typeVal+''+globalWorkTypeCount+'0" placeholder="Enter" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
									str+='</div>';
							str+='</div>';
							str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<label>LOCATION LEVEL <span class="starColor">*</span><span class="locationLevelId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+' validateCls" id="locationLevelId'+typeVal+''+globalWorkTypeCount+'0" attr_counterval="'+globalWorkTypeCount+'0" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
											str+='<option value="0">Select Level</option>';
											str+='<option value="3">District</option>';
											str+='<option value="4">Constituency</option>';
											str+='<option value="5">Mandal</option>';
										str+='</select>';
									str+='</div>';
									
									str+='<div class="col-sm-2 districtCls'+typeVal+''+globalWorkTypeCount+'0" style="display:none">';
										str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+typeVal+''+globalWorkTypeCount+'0" attr_counterval="'+globalWorkTypeCount+'0" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
											str+='<option value="0">Select District</option>';
										str+='</select>';
									str+='</div>';
									
									str+='<div class="col-sm-2 constituencyCls'+typeVal+''+globalWorkTypeCount+'0" style="display:none">';
										str+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<select   name="worksList['+globalWorkTypeCount+'].subWorksList[0].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+typeVal+''+globalWorkTypeCount+'0" attr_counterval="'+globalWorkTypeCount+'0" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
											str+='<option value="0">Select Constituency</option>';
										str+='</select>';
									str+='</div>';
									
									str+='<div class="col-sm-2 mandalCls'+typeVal+''+globalWorkTypeCount+'0" style="display:none">';
										str+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+typeVal+''+globalWorkTypeCount+'0"></span></label>';
										str+='<select  name="worksList['+globalWorkTypeCount+'].subWorksList[0].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+typeVal+''+globalWorkTypeCount+'0" attr_counterval="'+globalWorkTypeCount+'0" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+globalWorkTypeCount+'" attr_inner_count="0">';
											str+='<option value="0">Select Mandal</option>';
										str+='</select>';
									str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					var innerWorkTypeCount=globalWorkTypeCount+1;
					str+='<div class="appendInnerBlocks'+typeVal+''+globalWorkTypeCount+''+innerWorkTypeCount+'"></div>';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<span class="addLocationCss m_top10 pull-right cloned_Inner_Element" style="cursor:pointer;" attr_type="'+typeVal+'" attr_counterval="'+globalWorkTypeCount+'" main_work_count="0" inner_work_count="'+innerWorkTypeCount+'">ADD WORK </span>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
					
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="appendWorkDetailsDiv'+typeVal+'"></div>';
	str+='</div>';
	var addWorkTypeCountMain=globalWorkTypeCount+1;
	str+='<div class="row">';
		str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" block-clone-counter-'+typeVal+'="'+addWorkTypeCountMain+'" style="cursor:pointer;" block-clone-'+typeVal+'="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">ADD WORK TYPE </span></div>';
	str+='</div>';	
	
	$("#workDetailsDivId"+typeVal).html(str);
	$(".chosen-select").chosen();
	
	getSubjectPetitionsDepartmentList(typeVal,globalWorkTypeCount,0);
	getPetitionSubjectList('subjectId',typeVal,globalWorkTypeCount,0);
	getWorkTypeList('workTypeId',typeVal,globalWorkTypeCount,0);
	globalWorkTypeCount =globalWorkTypeCount+1;
		
}
$(document).on("change",".locationLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var changeType = $(this).attr("attr_type_change");

	if(levelVal == 3){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('');
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType);
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).hide();
			$(".mandalCls"+typeVal+counterId).hide();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType);
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).hide();
			$(".mandalInnerCls"+typeVal+counterId).hide();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 4){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('');
			 $("#districtId"+typeVal+counterId).trigger("chosen:updated");
			 getAllDistrictsInState(typeVal,counterId,changeType);
			  $("#constituencyId"+typeVal+counterId).html('');
			  $("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			  $(".districtCls"+typeVal+counterId).show();
			  $(".constituencyCls"+typeVal+counterId).show();
			  $(".mandalCls"+typeVal+counterId).hide();
			  $(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType);
			$("#constituencyInnerId"+typeVal+counterId).html('');
			 $("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			 $(".districtInnerCls"+typeVal+counterId).show();
			 $(".constituencyInnerCls"+typeVal+counterId).show();
			 $(".mandalInnerCls"+typeVal+counterId).hide();
			 $(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 5){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('');
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType);
			$("#constituencyId"+typeVal+counterId).html('');
			$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalId"+typeVal+counterId).html('');
			$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).show();
			$(".mandalCls"+typeVal+counterId).show();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType);
			$("#constituencyInnerId"+typeVal+counterId).html('');
			$("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalInnerId"+typeVal+counterId).html('');
			$("#mandalInnerId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).show();
			$(".mandalInnerCls"+typeVal+counterId).show();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else{
			if(changeType == "main"){
				$(".districtCls"+typeVal+counterId).hide();
				$(".constituencyCls"+typeVal+counterId).hide();
				$(".mandalCls"+typeVal+counterId).hide();
				$(".panchayatCls"+typeVal+counterId).hide();
			}else{
				$(".districtInnerCls"+typeVal+counterId).hide();
				$(".constituencyInnerCls"+typeVal+counterId).hide();
				$(".mandalInnerCls"+typeVal+counterId).hide();
				$(".panchayatInnerCls"+typeVal+counterId).hide();
			}
		
	}
});
$(document).on("change",".districtLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var typeChange = $(this).attr("attr_type_change");
	 
	getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,typeChange);
	
});
$(document).on("change",".constituencyLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var typeChange = $(this).attr("attr_type_change");
	getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal,typeChange);
	
});
function  enableWorks(value,divId,typeVal){
	$(".appendWorkDetailsDiv"+typeVal).html('');	
	$('#'+divId+'').hide()
	if(parseInt(value)>0){
		$('#'+divId+'').show();
	}
}
$(document).on("click",".cloned_Element",function(){
	var typeVal = $(this).attr("attr_type");
	var workCount = $("#noofWork"+typeVal).val();
	var counterappendId = $(this).attr("block-clone-counter-"+typeVal+"");
	var counterId = $(this).attr("block-clone-counter-"+typeVal+"");
		counterId = parseInt(counterId) + 1;
		
	var blockId = $(this).attr("block-clone-"+typeVal+"");
	//if(counterId <= parseInt(workCount)){
		$(".appendWorkDetailsDiv"+typeVal+"").append(clonedTemplate(blockId,'clone',counterId,typeVal,counterappendId));
		$(".chosen-select").chosen({width:'100%'});
		$("[block-clone-"+typeVal+"="+blockId+"]").attr("block-clone-counter-"+typeVal+"",counterId);
		globalWorkTypeCount = parseInt(globalWorkTypeCount)+1;
		getSubjectPetitionsDepartmentList(typeVal,counterappendId,blockId);
		getPetitionSubjectList('subjectId',typeVal,counterappendId,blockId);
		getWorkTypeList('workTypeId',typeVal,counterappendId,blockId);
		
	//}
});

function clonedTemplate(blockId,type,counterId,typeVal,counterappendId){
	
	var clonedTemplate='';
	// here for evary new work  adding based on diff dept /subject/sub subject wise the subworksList should be start from ZERO ,but globalWorkTypeCount is same
	//var tempcounterId = counterId;
	//counterId = 0;
	var tempcounterId=1;
	clonedTemplate+='<div class="col-sm-12" cloned_block_'+typeVal+'='+counterappendId+' id="mainWorkDivId'+counterappendId+''+blockId+'">';
	clonedTemplate+='<div class="pull-right removeWorkCls" attr_id="mainWorkDivId'+counterappendId+''+blockId+'" attr_type="self" attr_candidateid="1"><i class="glyphicon glyphicon-remove"></i></div>';
		clonedTemplate+='<div style="border:3px solid #dddddd;padding:10px;">';
			clonedTemplate+='<div  cloned_block_'+typeVal+'='+counterappendId+'>';
				//clonedTemplate+='<h3 class="panel-title f_22">WORK TYPE '+counterId+'</h3>';
				
				clonedTemplate+='<div class="row m_top10" >';
				
					clonedTemplate+='<div class="col-sm-3">';
							clonedTemplate+='<label>DEPARTMENT <span class="starColor">*</span><span class="WorkTypeWiseDepartmentId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
							clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].deptId"  class="form-control chosen-select m_top10 validateCls"  id="WorkTypeWiseDepartmentId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
							clonedTemplate+='</select>';
						clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="col-sm-3">';
						clonedTemplate+='<label>SUBJECT <span class="starColor">*</span><span class="subjectId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
						clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].subjectId"  class="form-control chosen-select m_top10 validateCls"  id="subjectId'+typeVal+''+counterappendId+''+blockId+'" onChange=getPetitionSubSubjectList(this.value,"subSubjectId'+typeVal+''+counterappendId+'","'+blockId+'") attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
							clonedTemplate+='<option value="0">Select Subject</option>';
						clonedTemplate+='</select>';
					clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="col-sm-3">';
						clonedTemplate+='<label>SUB-SUBJECT <span class="starColor">*</span><span class="subSubjectId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
						clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].subSubjectId"  class="form-control chosen-select m_top10 validateCls" id="subSubjectId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
							clonedTemplate+='<option value="0">Select Sub Subject</option>';
						clonedTemplate+='</select>';
					clonedTemplate+='</div>';
								
				clonedTemplate+='</div>';
				
				clonedTemplate+='<div class="row m_top10" id="workDivId'+counterappendId+''+blockId+'" >';
				//clonedTemplate+='<div class="pull-right removeWorkCls" attr_id="workDivId'+globalWorkTypeCount+''+counterId+'" attr_type="self" attr_candidateid="1" style="margin-top: -50px;cursor:pointer"><i class="glyphicon glyphicon-remove"></i></div>';
					clonedTemplate+='<div class="col-sm-12">';
						clonedTemplate+='<div class="bg_color_view">';
							clonedTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK TYPE <span class="starColor">*</span><span class="workTypeId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].workTypeId"  class="form-control chosen-select m_top10 validateCls" id="workTypeId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Work Type</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK IN COST (in Lakh) <span class="starColor">*</span><span class="appendWorkCost'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+blockId+'].estimateCost" class="form-control m_top5 height45 validateCls" id="appendWorkCost'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter Work Cost" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+blockId+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>eOFFICE-ID <span class="starColor">*</span><span class="appendEofficeId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+blockId+'].eOfficeId" class="form-control m_top5 height45 validateCls" id="appendEofficeId'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
									clonedTemplate+='</div>';
							clonedTemplate+='</div>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>LOCATION LEVEL <span class="starColor">*</span><span class="locationLevelId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+' validateCls" id="locationLevelId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Level</option>';
											clonedTemplate+='<option value="3">District</option>';
											clonedTemplate+='<option value="4">Constituency</option>';
											clonedTemplate+='<option value="5">Mandal</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									
									clonedTemplate+='<div class="col-sm-2 districtCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
										clonedTemplate+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select District</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									
									clonedTemplate+='<div class="col-sm-2 constituencyCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
										clonedTemplate+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<select   name="worksList['+counterappendId+'].subWorksList['+blockId+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Constituency</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									
									clonedTemplate+='<div class="col-sm-2 mandalCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
										clonedTemplate+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Mandal</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
							clonedTemplate+='</div>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="appendInnerBlocks'+typeVal+''+globalWorkTypeCount+''+tempcounterId+'"></div>';
					clonedTemplate+='<div class="col-sm-12">';
						clonedTemplate+='<div class="pull-right">';
							clonedTemplate+='<span class="addLocationCss m_top10 pull-right cloned_Inner_Element" style="cursor:pointer;" attr_type="'+typeVal+'" attr_counterval="'+counterId+''+counterappendId+'" main_work_count="'+globalWorkTypeCount+'" inner_work_count="1" >ADD WORK  </span>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';					
				clonedTemplate+='</div>';					
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	//counterId = tempcounterId;
	tempcounterId=tempcounterId+1;
	return clonedTemplate;
	
}
$(document).on("click",".cloned_Inner_Element",function(){
	var typeVal = $(this).attr("attr_type");
	var counterId = $(this).attr("attr_counterval");	
	var mainWorkCount = $(this).attr("main_work_count");
	var innerWorkCount = $(this).attr("inner_work_count");
	$(this).attr("inner_work_count",parseInt(innerWorkCount)+1);
	var conterInnerVal = parseInt(innerWorkCount)+1
	
	
	$(".appendInnerBlocks"+typeVal+mainWorkCount+innerWorkCount).append(clonedInnerTemplate('clone',counterId,typeVal,mainWorkCount,innerWorkCount,conterInnerVal));
	$(".chosen-select").chosen({width:'100%'});
	getWorkTypeList('workTypeInnerId',typeVal,mainWorkCount,innerWorkCount);
});
function clonedInnerTemplate(type,counterId,typeVal,mainWorkCount,innerWorkCount,conterInnerVal){
	var clonedInnerTemplate='';
	clonedInnerTemplate+='<div class="m_top10" >';
		clonedInnerTemplate+='<div class="col-sm-12 m_top10" >';
		clonedInnerTemplate+='<div class="pull-right removeWorkCls" attr_id="workDivId'+globalWorkTypeCount+''+innerWorkCount+'" attr_type="self" attr_candidateid="1"><i class="glyphicon glyphicon-remove"></i></div>';
			clonedInnerTemplate+='<div class="bg_color_view" id="workDivId'+globalWorkTypeCount+''+innerWorkCount+'">';
				clonedInnerTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
				clonedInnerTemplate+='<div class="row m_top10" >';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK TYPE  <span class="starColor">*</span><span class="workTypeInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].workTypeId"  class="form-control chosen-select m_top10 validateCls" id="workTypeInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Work Type</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK IN COST (in Lakh) <span class="starColor">*</span><span class="appendWorkCostInner'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].estimateCost" class="form-control m_top5 height45 validateCls" id="appendWorkCostInner'+typeVal+''+mainWorkCount+''+innerWorkCount+'" placeholder="Enter Work Cost" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>eOFFICE-ID <span class="starColor">*</span><span class="appendEofficeInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].eOfficeId" class="form-control m_top5 height45 validateCls" id="appendEofficeInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='<div class="row m_top10">';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>LOCATION LEVEL <span class="starColor">*</span><span class="locationLevelInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+' validateCls" id="locationLevelInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" attr_counterval="'+mainWorkCount+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Level</option>';
								clonedInnerTemplate+='<option value="3">District</option>';
								clonedInnerTemplate+='<option value="4">Constituency</option>';
								clonedInnerTemplate+='<option value="5">Mandal</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 districtInnerCls'+typeVal+''+mainWorkCount+''+innerWorkCount+'" style="display:none">';
							clonedInnerTemplate+='<label>DISTRICT <span class="starColor">*</span><span class="districtInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" attr_counterval="'+mainWorkCount+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select District</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 constituencyInnerCls'+typeVal+''+mainWorkCount+''+innerWorkCount+'" style="display:none">';
							clonedInnerTemplate+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select   name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" attr_counterval="'+mainWorkCount+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Constituency</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 mandalInnerCls'+typeVal+''+mainWorkCount+''+innerWorkCount+'" style="display:none">';
							clonedInnerTemplate+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+mainWorkCount+'].subWorksList['+innerWorkCount+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalInnerId'+typeVal+''+mainWorkCount+''+innerWorkCount+'" attr_counterval="'+mainWorkCount+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+mainWorkCount+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Mandal</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
			clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='<div class="appendInnerBlocks'+typeVal+''+mainWorkCount+''+conterInnerVal+'"></div>';
		return clonedInnerTemplate;
}

function getAllDistrictsListInState(){	
	$("#districtrepresent").html('');
	var json = {
		  stateId:"1",
		  searchType:"all",
		  searchId:0
		}
	$.ajax({                
		type:'POST',    
		url: 'getAllDistrictsInState',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#districtrepresent").append('<option value="0">Select District</option>');
				for(var i in result){				
					$("#districtrepresent").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
			$("#districtrepresent").trigger('chosen:updated');
	});	
}

$(document).on("change","#districtrepresent",function(){
	var levelVal = $(this).val();
	getConstituencyNamesBiDistrictId(levelVal); 
	
});


function getConstituencyNamesBiDistrictId(levelVal){
	  $("#constituencyrepresent").html('');
	  $("#constituencyrepresent").append('<option value="0">Select Constituency</option>');	
	  $("#constituencyrepresent").trigger('chosen:updated');
	   var searchType= "all";		
	  var json = {
		  districtId:levelVal,
		  searchType:"all",
		  searchId:0
		}
	$.ajax({                
		type:'POST',    
		url: 'getConstituencyNamesByDistrictId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){	
			for(var i in result){
				$("#constituencyrepresent").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#constituencyrepresent").trigger('chosen:updated');		
	});	
}

$(document).on("change","#constituencyrepresent",function(){
	var levelVal = $(this).val();
	getTehsilsAndLocalElectionBodiForConstituencyId(levelVal); 
	
});
function getTehsilsAndLocalElectionBodiForConstituencyId(levelVal){
	  $("#mandalrepresent").html('');	
	  $("#mandalrepresent").append('<option value="0">Select Mandal</option>');	
	  $("#mandalrepresent").trigger('chosen:updated');	
	  var json = {
		  constituencyId:levelVal,
		  searchType:"all",
		  searchId:0
		}        
	$.ajax({                
		type:'POST',    
		url: 'getTehsilsAndLocalElectionBodyForConstituencyId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){			 		
			for(var i in result){
					var tehsilId = result[i].key;
					var levelId = tehsilId.toString().substr(1, 4);
					if(result[i].electionType != null){
						$("#mandalrepresent").append('<option value="'+levelId+'">'+result[i].value+' '+result[i].electionType+'</option>');
					}else{
						$("#mandalrepresent").append('<option value="'+levelId+'">'+result[i].value+'</option>');
					}
			}
		}
		$("#mandalrepresent").trigger('chosen:updated');
	});	
}

function getSubjectPetitionsDepartmentList(typeVal,count,innerCount){
	 $("#WorkTypeWiseDepartmentId"+typeVal+""+count+innerCount+"").html('');
	   var json = { 
		  searchType:"all" // all/petitionGivenDepts
	  };     
	$.ajax({              
		type:'POST',    
		url: 'getPmDepartmentList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 	$("#WorkTypeWiseDepartmentId"+typeVal+""+count+innerCount+"").append('<option value="0">Select Department</option>');
			for(var i in result){
				$("#WorkTypeWiseDepartmentId"+typeVal+""+count+innerCount+"").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#WorkTypeWiseDepartmentId"+typeVal+""+count+innerCount+"").trigger('chosen:updated');
	});	
}



function getPetitionSubjectList(divId,typeVal,counterId,innerCount){
	 $("#"+divId+""+typeVal+""+counterId+innerCount+"").html('');
	var json = {};
	$.ajax({              
		type:'POST',    
		url: 'getPmSubjectList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#"+divId+""+typeVal+""+counterId+innerCount+"").html('<option value="0">Select Subject</option>');
			for(var i in result){
				$("#"+divId+""+typeVal+""+counterId+innerCount+"").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#"+divId+""+typeVal+""+counterId+innerCount+"").trigger('chosen:updated');
	});	
}


function getPetitionSubSubjectList(subjectId,divId,innerCount){
	
$("#"+divId+innerCount).html('');
	var json = {
		subjectId : subjectId
	};
	$.ajax({              
		type:'POST',    
		url: 'getPmSubSubjectList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#"+divId+innerCount).append('<option value="0">Select Sub Subject</option>');
			for(var i in result){
				$("#"+divId+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#"+divId+innerCount).trigger('chosen:updated');
	});	
}
//workTypeInnerId, workTypeId
function getWorkTypeList(divId,typeVal,count,innerCount){
	//alert("#"+divId+typeVal+count);
	 $("#"+divId+typeVal+count+innerCount).html('<option value="0">Select Work Type</option>');
	 $("#"+divId+typeVal+count+innerCount).trigger('chosen:updated');
    var json = {
     
   };
 $.ajax({              
  type:'POST',    
  url: 'getWorkTypeList',
  dataType: 'json',
  data : JSON.stringify(json),
  beforeSend :   function(xhr){
   xhr.setRequestHeader("Accept", "application/json");
   xhr.setRequestHeader("Content-Type", "application/json");
  }
 }).done(function(result){
  if(result !=null && result.length>0){
   
   for(var i in result){
     $("#"+divId+typeVal+count+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
   }
  }
   $("#"+divId+typeVal+count+innerCount).trigger('chosen:updated');
 }); 
}

$(document).on("click",".saveRepresentRequestDetails",function(){
	var typeVal = $(this).attr("attr_type");
	
	var completeWorkName='';
	var noofWorks='';
	var workCost='';
	var WorkTypeWiseDepartmentId='';
	var subjectId='';
	var subSubjectId='';
	
	var workTypeId='';
	var appendWorkCost='';
	var appendWorkDetailsId='';
	var appendEofficeId='';
	var locationLevelId='';
	var districtId='';
	var constituencyId='';
	var mandalId='';
	
	var workTypeInnerId='';
	var appendWorkCostInner='';
	var appendWorkDetailsInnerId='';
	var appendEofficeInnerId='';
	var locationLevelInnerId='';
	var districtInnerId='';
	var constituencyInnerId='';
	var mandalInnerId='';
	
	var flag = true;
	
	completeWorkName = $("#name"+typeVal).val();
	noofWorks = $("#noofWork"+typeVal).val();
	workCost = $("#workCost"+typeVal).val();
	
	
	
	if(completeWorkName == undefined || completeWorkName == "undefined" || completeWorkName.trim() == '' || completeWorkName == null){
		$("#completeWorkNameId"+typeVal).html("<h5 style='color:red;'>Please Enter Work Name</h5>");
		return;
	}else{
		$("#completeWorkNameId"+typeVal).html("");
	}
	if(noofWorks == undefined || noofWorks == "undefined" || noofWorks === undefined || noofWorks.trim() == '' || noofWorks == null){
		$("#noOfWorksId"+typeVal).html("<h5 style='color:red;'>Please Enter No Of Works</h5>");
		return;
	}else{
		$("#noOfWorksId"+typeVal).html("");
	}
	if(workCost == undefined || workCost == "undefined" || workCost === undefined || workCost.trim() == '' || workCost == null){
		$("#workCostId"+typeVal).html("<h5 style='color:red;'>Please Enter Work Cost</h5>");
		return;
	}else{
		$("#workCostId"+typeVal).html("");
	}
	
	$(".validateCls").each(function(){
		var selectType = $(this).attr("attr_select_type")
		var mainCount = $(this).attr("attr_main_count")
		var innerCount = $(this).attr("attr_inner_count")
		var appendInnerType = $(this).attr("attr_type_change")
		
	
	
		
		//if(selectType == "selectbox"){
			WorkTypeWiseDepartmentId = $("#WorkTypeWiseDepartmentId"+typeVal+mainCount+innerCount).val();
			subjectId = $("#subjectId"+typeVal+mainCount+innerCount).val();
			subSubjectId = $("#subSubjectId"+typeVal+mainCount+innerCount).val();
			
			workTypeId = $("#workTypeId"+typeVal+mainCount+innerCount).val();
			appendWorkCost = $("#appendWorkCost"+typeVal+mainCount+innerCount).val();
			appendWorkDetailsId = $("#appendWorkDetailsId"+typeVal+mainCount+innerCount).val();
			appendEofficeId = $("#appendEofficeId"+typeVal+mainCount+innerCount).val();
			locationLevelId = $("#locationLevelId"+typeVal+mainCount+innerCount).val();
			districtId = $("#districtId"+typeVal+mainCount+innerCount).val();
			constituencyId = $("#constituencyId"+typeVal+mainCount+innerCount).val();
			mandalId = $("#mandalId"+typeVal+mainCount+innerCount).val();
			
			
			workTypeInnerId = $("#workTypeInnerId"+typeVal+mainCount+innerCount).val();
			appendWorkCostInner = $("#appendWorkCostInner"+typeVal+mainCount+innerCount).val();
			appendWorkDetailsInnerId = $("#appendWorkDetailsInnerId"+typeVal+mainCount+innerCount).val();
			appendEofficeInnerId = $("#appendEofficeInnerId"+typeVal+mainCount+innerCount).val();
			locationLevelInnerId = $("#locationLevelInnerId"+typeVal+mainCount+innerCount).val();
			districtInnerId = $("#districtInnerId"+typeVal+mainCount+innerCount).val();
			constituencyInnerId = $("#constituencyInnerId"+typeVal+mainCount+innerCount).val();
			mandalInnerId = $("#mandalInnerId"+typeVal+mainCount+innerCount).val();
			
				if(WorkTypeWiseDepartmentId == 0 || WorkTypeWiseDepartmentId == null || WorkTypeWiseDepartmentId == ''){
					$(".WorkTypeWiseDepartmentId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Department</h5>");
					flag = false;
				}else{
					$(".WorkTypeWiseDepartmentId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(subjectId == 0 || subjectId == null || subjectId == ''){
					$(".subjectId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Subject</h5>");
					flag = false;
				}else{
					$(".subjectId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(subSubjectId == 0 || subSubjectId == null || subSubjectId == ''){
					$(".subSubjectId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Sub Subject</h5>");
					flag = false;
				}else{
					$(".subSubjectId"+typeVal+mainCount+innerCount).html("");
				}
				
			if(appendInnerType == "main"){
				
				if(workTypeId == 0 || workTypeId == null || workTypeId == ''){
					$(".workTypeId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Work Type</h5>");
					flag = false;
				}else{
					$(".workTypeId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(appendWorkCost == 0 || appendWorkCost == null || appendWorkCost == ''){
					$(".appendWorkCost"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter Work Cost</h5>");
					flag = false;
				}else{
					$(".appendWorkCost"+typeVal+mainCount+innerCount).html("");
				}
				
				if(appendWorkDetailsId == 0 || appendWorkDetailsId == null || appendWorkDetailsId == ''){
					$(".appendWorkDetailsId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter Work Details</h5>");
					flag = false;
				}else{
					$(".appendWorkDetailsId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(appendEofficeId == 0 || appendEofficeId == null || appendEofficeId == ''){
					$(".appendEofficeId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter eOFFICE-ID</h5>");
					flag = false;
				}else{
					$(".appendEofficeId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(locationLevelId == 0 || locationLevelId == null || locationLevelId == ''){
					$(".locationLevelId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Location Type</h5>");
					flag = false;
				}else{
					$(".locationLevelId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(locationLevelId == 3){
					if(districtId == 0 || districtId == null || districtId == ''){
						$(".districtId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select District</h5>");
						flag = false;
					}else{
						$(".districtId"+typeVal+mainCount+innerCount).html("");
					}
				}else if(locationLevelId == 4){
					if(districtId == 0 || districtId == null || districtId == ''){
						$(".districtId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select District</h5>");
						flag = false;
					}else{
						$(".districtId"+typeVal+mainCount+innerCount).html("");
					}
					
					if(constituencyId == 0 || constituencyId == null || constituencyId == ''){
						$(".constituencyId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Constituency</h5>");
						flag = false;
					}else{
						$(".constituencyId"+typeVal+mainCount+innerCount).html("");
					}
				}else if(locationLevelId == 5){
					if(districtId == 0 || districtId == null || districtId == ''){
						$(".districtId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select District</h5>");
						flag = false;
					}else{
						$(".districtId"+typeVal+mainCount+innerCount).html("");
					}
					
					if(constituencyId == 0 || constituencyId == null || constituencyId == ''){
						$(".constituencyId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Constituency</h5>");
						flag = false;
					}else{
						$(".constituencyId"+typeVal+mainCount+innerCount).html("");
					}
					
					if(mandalId == 0 || mandalId == null || mandalId == ''){
						$(".mandalId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Mandal</h5>");
						flag = false;
					}else{
						$(".mandalId"+typeVal+mainCount+innerCount).html("");
					}
				}
			}
			
			if(appendInnerType == "Inner"){
				
				if(workTypeInnerId == 0 || workTypeInnerId == null || workTypeInnerId == ''){
					$(".workTypeInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Work Type</h5>");
					flag = false;
				}else{
					$(".workTypeId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(appendWorkCostInner == 0 || appendWorkCostInner == null || appendWorkCostInner == ''){
					$(".appendWorkCostInner"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter Work Cost</h5>");
					flag = false;
				}else{
					$(".appendWorkCostInner"+typeVal+mainCount+innerCount).html("");
				}
				
				if(appendWorkDetailsInnerId == 0 || appendWorkDetailsInnerId == null || appendWorkDetailsInnerId == ''){
					$(".appendWorkDetailsInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter Work Details</h5>");
					flag = false;
				}else{
					$(".appendWorkDetailsInnerId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(appendEofficeInnerId == 0 || appendEofficeInnerId == null || appendEofficeInnerId == ''){
					$(".appendEofficeInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter eOFFICE-ID</h5>");
					flag = false;
				}else{
					$(".appendEofficeInnerId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(locationLevelInnerId == 0 || locationLevelInnerId == null || locationLevelInnerId == ''){
					$(".locationLevelInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Location Type</h5>");
					flag = false;
				}else{
					$(".locationLevelInnerId"+typeVal+mainCount+innerCount).html("");
				}
				
				if(locationLevelInnerId == 3){
					if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
						$(".districtInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select District</h5>");
						flag = false;
					}else{
						$(".districtInnerId"+typeVal+mainCount+innerCount).html("");
					}
				}else if(locationLevelInnerId == 4){
					if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
						$(".districtInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select District</h5>");
						flag = false;
					}else{
						$(".districtInnerId"+typeVal+mainCount+innerCount).html("");
					}
					
					if(constituencyInnerId == 0 || constituencyInnerId == null || constituencyInnerId == ''){
						$(".constituencyInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Constituency</h5>");
						flag = false;
					}else{
						$(".constituencyInnerId"+typeVal+mainCount+innerCount).html("");
					}
				}else if(locationLevelInnerId == 5){
					if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
						$(".districtInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select District</h5>");
						flag = false;
					}else{
						$(".districtInnerId"+typeVal+mainCount+innerCount).html("");
					}
					
					if(constituencyInnerId == 0 || constituencyInnerId == null || constituencyInnerId == ''){
						$(".constituencyInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Constituency</h5>");
						flag = false;
					}else{
						$(".constituencyInnerId"+typeVal+mainCount+innerCount).html("");
					}
					
					if(mandalInnerId == 0 || mandalInnerId == null || mandalInnerId == ''){
						$(".mandalInnerId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Mandal</h5>");
						flag = false;
					}else{
						$(".mandalInnerId"+typeVal+mainCount+innerCount).html("");
					}
				}
			}
			
			
			
			
			
			
			
		//}
		
	});
	if(flag == false){
		return;
	}
	
	
	
	$("#savingDetailsSpinner").html(spinner)
	 var formData = new FormData();
	$('#adminProfileForm input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			//debugger;
			if (typeof id !== typeof undefined && id !== false) {
				if(text=='text' || text=='hidden'){
					var name = $('#'+id+'').attr('name');
					//alert($('#'+id+'').val());
					formData.append(name, $('#'+id+'').val());
				}else if(text=='radio'){
					if($('#'+id+'').is(':checked')){
						var name = $('#'+id+'').attr('name');
						formData.append(name, $('#'+id+'').val());
					}
				}else if(text=='file'){
					var name = $('#'+id+'').attr('attr_name');//attr_image_tyep="refImage"  
					//var imageType = $('#'+id+'').attr('attr_image_tyep');
					if(this.files !=null && this.files.length>0){
							for(var i = 0; i < this.files.length; i++){
							//	alert(name+".fileList["+i+"]");
								formData.append(name+".fileList["+i+"]", this.files[i]);
								
							//alert(i)
							//console.log(this.files[i]);
							/*if(imageType == 'refImage'){
								formData.append(name+".fileList["+i+"]", this.files[i]);
								//formData.append("filesList["+i+"]", this.files[i]);
							}else if(imageType == 'projImage'){
								//formData.append("workFilesList["+i+"]", this.files[i]);
								formData.append(name+".fileList["+i+"]", this.files[i]);
							}
							*/
						}
					}
				}
			}			
		}
	);
	$('#adminProfileForm textarea').each(
		  function(){			  
			var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
				var name = $('#'+id+'').attr('name');
				formData.append(name, $('#'+id+'').val());
			}
		}
	);
	
	$('#adminProfileForm select').each(
		  function(){			  
				var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
			}
		}
	);
	
	  $.ajax({
			url: $("#adminProfileForm").attr("action"),
			data: formData,
			type: "POST",               
			processData: false,
			contentType: false,
			success: function(result) {
				$("#savingDetailsSpinner").html('')
				if(result!=null){
				  if(result.responseCode == "0"){
					   $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Saved Successfully</h3></center>").fadeOut(4000);
					  
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); window.location.reload(); $(".defaultCheckCls").prop("checked",true)},6000);
						 
				  }else{
					  $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Failed..Try Later</h3></center>").fadeOut(4000);
					  setTimeout(function () {
						 
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				  }
				}else{
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Failed..Try Later</h3></center>").fadeOut(4000);
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				 }
				 
				
			},
			error: function(request,error) { 
				$("#savingDetailsSpinner").html('')
				alert("error");				
			}
     });	 

});

function getParliamentIdsByConstituencyList(){
	var json = {};
	$.ajax({              
		type:'POST',    
		url: 'getParliamentIdsByConstituencyList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#constituencyCanId").append('<option value="0">All</option>');
			for(var i in result){
				$("#constituencyCanId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#constituencyCanId").trigger('chosen:updated');
	});	
}

function validation(typeVal){
	var completeWorkName='';
	var noofWorks='';
	var workCost='';
	var phonePattern= /^\d{0-9}$/;
	
	completeWorkName = $("#name"+typeVal).val();
	noofWorks = $("#noofWork"+typeVal).val();
	workCost = $("#workCost"+typeVal).val();
	alert(completeWorkName)
	alert(noofWorks)
	alert(workCost)
	
	if(completeWorkName == undefined || completeWorkName == "undefined" || completeWorkName === undefined){
		$("#completeWorkNameId"+typeVal).html("<h5>Please Enter Work Name</h5>");
		return;
	}
	if(noofWorks == undefined || noofWorks == "undefined" || noofWorks === undefined){
		$("#noofWork"+typeVal).html("<h5>Please Enter Work Name</h5>");
		return;
	}
	if(workCost == undefined || workCost == "undefined" || workCost === undefined){
		$("#workCost"+typeVal).html("<h5>Please Enter Work Name</h5>");
		return;
	}
	
}

function getPetitionDesignationList(){
	$("#designationrepresent").html('');
	  var json = {
		   searchType:"all"// all/refCandidateDesignations/petitionGivenRefCandidateDesignations
	  };
	$.ajax({              
		type:'POST',    
		url: 'getPetitionDesignationList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
				$("#designationrepresent").append('<option value="0">Select Designation</option>');
			for(var i in result){
				$("#designationrepresent").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#designationrepresent").trigger('chosen:updated');
	});	

}