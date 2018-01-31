var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
/* $("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menuCls").toggle();
}); */
/* $(document).on("click",function(){
	$(".menu-data-cls").hide();
}); */

var GlWrkTypeId = 2; // default new proposal
var GlLocationLvelId = 0;
var GlDistrctId = 0;
var GlConsttuencyId = 0;
var GlMndalId = 0;
var GlPnchayatId = 0;

var globalRepresentType='';
var globalWorkTypeCount=0;
var globalDepartmentsList='';
var globalSubjectList='';
var globalSubSubjectsList='';
var globalWorkTypeList='';
var globaldistrictList='';
var globalDesignationList=[];
var alreadyCandidateId=[];
var searchCandidateIds=[];
var diffArr=[];
var commonArr=[];
var refCandCount=0;
var globalInnerWorksCount =0;
var maxWorksCount =0;
var maxCost =0;
var coveringDocs=[];
var globalStatusArr=[];
var selectdWorksArr=[];
var departmentSelectArr=[];
var isOldData="Y";
$("#representationDate,#endorsmentDate").daterangepicker({
	singleDatePicker: true,
	maxDate:new Date(),
	locale: {
	  format: 'DD-MM-YYYY'
	},
	
});
$('#representationDate,#endorsmentDate').on('apply.daterangepicker', function(ev, picker) {
});


getSubjectPetitionsDepartmentList("onload");
getWorkTypeList("","","","","onload");
getAllDistrictsInState("","","","onload");
getPetitionDesignationLst('REPRESENTEE');
//District Build
function getAllDistrictsInState(typeVal,counterId,typeChange,type){
	//alert(typeChange)
	var searchType="all";
	var searchId =0;
	if(typeChange == "Inner"){
		$("#districtInnerId"+typeVal+counterId).html('');
		$("#districtInnerId"+typeVal+counterId).html('<option value="0">Select District</option>');
		$("#districtInnerId"+typeVal+counterId).trigger('chosen:updated');
	}else{
		if(typeVal=="popup"){
			$("#districtCandId").html('');
			$("#districtCandId").html('<option value="0">All</option>');
			$("#districtCandId").trigger('chosen:updated');
			searchType = "refCandidate";
			searchId = $('#designationsId').val();
		}else if(counterId == null || counterId ==""){
			$("#district"+typeVal+counterId).html('');
			$("#district"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#district"+typeVal+counterId).trigger('chosen:updated');
		}else if(counterId !="" && parseInt(counterId)>=0){
			$("#districtId"+typeVal+counterId).html('');
			$("#districtId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtId"+typeVal+counterId).trigger('chosen:updated');
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
			if(type == "onload"){
				globaldistrictList = result;
			}	
			if(typeChange == "Inner"){
				$("#districtInnerId"+typeVal+counterId).html('<option value="0">Select District</option>');
			}else{
				if(typeVal== "popup"){
					$("#districtCandId").html('<option value="0">All</option>');
				}else if(counterId == null || counterId == ""){		
					$("#district"+typeVal+counterId).html('<option value="0">Select District</option>');
				}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#districtId"+typeVal+counterId).html('<option value="0">Select District</option>');
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
		/*
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
		}*/
		
		if(typeChange == "Inner"){
			$("#districtInnerId"+typeVal+counterId).trigger('chosen:updated');
			if(GlDistrctId != null && GlDistrctId !='undefined'){
				$("#districtInnerId"+typeVal+counterId).val(GlDistrctId);
				$("#districtInnerId"+typeVal+counterId).trigger('chosen:updated');
			}
		}else{
			if(typeVal=="popup"){
				$("#districtCandId").trigger('chosen:updated');
			}
			else if(counterId == null || counterId == ""){
				$("#district"+typeVal+counterId).trigger('chosen:updated');
				if(GlDistrctId != null && GlDistrctId !='undefined'){
					$("#district"+typeVal+counterId).val(GlDistrctId);
					$("#district"+typeVal+counterId).trigger('chosen:updated');
				}
			}	
			else if(counterId != "" && parseInt(counterId) >= 0){
				$("#districtId"+typeVal+counterId).trigger('chosen:updated');
				if(GlDistrctId != null && GlDistrctId !='undefined'){
					$("#districtId"+typeVal+counterId).val(GlDistrctId);
					$("#districtId"+typeVal+counterId).trigger('chosen:updated');
				}
			}
		}
		
	});	
	
}
//Constituency Build
function getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,typeChange){
	$("#constituencyCanId").html('');
	$("#constituencyCanId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
	$("#constituencyCanId"+typeVal+counterId).trigger('chosen:updated');
			  
	   var searchType= "all";
		var searchId=0;
		if(typeChange == "Inner"){
			 $("#constituencyInnerId"+typeVal+counterId).html('');
			  $("#constituencyInnerId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			  $("#constituencyInnerId"+typeVal+counterId).trigger('chosen:updated');
		}else{
			if(typeVal== "popup"){
				searchType = "refCandidate";
				searchId = $('#designationsId').val();
			}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#constituencyId"+typeVal+counterId).html('');
				 $("#constituencyId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
				$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
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
				  $("#constituencyInnerId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			 }else{
				 $("#constituencyId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			 }
			
			 $("#constituencyCanId").html('<option value="0">All</option>');
			for(var i in result){
				
				if(typeChange == "Inner"){
					$("#constituencyInnerId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				}else{
					$("#constituencyId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
					$("#constituencyCanId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				}
				
				
			}
		}
		/*
		if(typeChange == "Inner"){
			$("#constituencyInnerId"+typeVal+counterId).trigger('chosen:updated');
		}else{
			$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
			$("#constituencyCanId").trigger('chosen:updated');
		}
		*/
		
		if(typeChange == "Inner"){
			$("#constituencyInnerId"+typeVal+counterId).trigger('chosen:updated');
			if(GlConsttuencyId != null && GlConsttuencyId !='undefined'){
				$("#constituencyInnerId"+typeVal+counterId).val(GlConsttuencyId);
				$("#constituencyInnerId"+typeVal+counterId).trigger('chosen:updated');
			}
		}else{
			$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
			if(GlConsttuencyId != null && GlConsttuencyId !='undefined'){
				$("#constituencyId"+typeVal+counterId).val(GlConsttuencyId);
				$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
			}			
			$("#constituencyCanId").trigger('chosen:updated');
		}
		
	});	
}
function getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal,typeChange){
	    $("#mandalId"+typeVal+counterId).html('');
		if(typeChange == "Inner"){
			$("#mandalInnerId"+typeVal+counterId).html('');
			$("#mandalInnerId"+typeVal+counterId).html('<option value="0">Select Mandal</option>');
			$("#mandalInnerId"+typeVal+counterId).trigger('chosen:updated');				  
		}
	    
		
	  var searchType="all";
	  var searchId=0;
			if(typeVal=="popup"){
				searchType = "refCandidate";
				searchId = $('#designationsId').val();
			}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#mandalId"+typeVal+counterId).html('');
				  $("#mandalId"+typeVal+counterId).html('<option value="0">Select Mandal</option>');
				  $("#mandalId"+typeVal+counterId).trigger('chosen:updated');
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
			 $("#mandalId"+typeVal+counterId).html('<option value="0">Select Mandal</option>');
			 if(typeChange == "Inner"){
				 $("#mandalInnerId"+typeVal+counterId).html('<option value="0">Select Mandal</option>');
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
	/*	$("#mandalId"+typeVal+counterId).trigger('chosen:updated');
		if(typeChange == "Inner"){
			$("#mandalInnerId"+typeVal+counterId).trigger('chosen:updated');
		}*/
		
		
		
		if(typeChange == "Inner"){
			$("#mandalInnerId"+typeVal+counterId).trigger('chosen:updated');
			if(GlMndalId != null && GlMndalId !='undefined'){
				$("#mandalInnerId"+typeVal+counterId).val(GlMndalId);
				$("#mandalInnerId"+typeVal+counterId).trigger('chosen:updated');
			}
		}else{
			$("#mandalId"+typeVal+counterId).trigger('chosen:updated');
			if(GlMndalId != null && GlMndalId !='undefined'){
				$("#mandalId"+typeVal+counterId).val(GlMndalId);
				$("#mandalId"+typeVal+counterId).trigger('chosen:updated');
			}
		}
		
	});	
}
function getSubjectPetitionsDepartmentList(type){
	$(".loadingCls").html(spinner);
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
			globalDepartmentsList = result;
		}
		if(type=="onload"){
			
			//getPetitionDetails();
		}
	});	
}

function getPetitionSubjectList(subjectId,divId,innerCount,type){
	if(type=="change"){
		$("#"+divId+innerCount).html('');
		$("#"+divId+innerCount).html('<option value="0">Select Sub Subject</option>');
		$("#"+divId+innerCount).trigger('chosen:updated');
	}
	var json = {
		deptId:subjectId
	};
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
			globalSubjectList = result;
			if(type=="change"){
				 $("#"+divId+innerCount).html('<option value="0">Select Sub Subject</option>');
				for(var i in result){
					$("#"+divId+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		if(type=="change"){
			$("#"+divId+innerCount).trigger('chosen:updated');
		}
		
		
	});	
}
function getPetitionSubSubjectList(subjectId,divId,innerCount,type){
	if(type=="change"){
		$("#"+divId+innerCount).html('');
		 $("#"+divId+innerCount).html('<option value="0">Select Sub Subject</option>');
		 $("#"+divId+innerCount).trigger('chosen:updated');
	}
	
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
			globalSubSubjectsList = result;
			if(type=="change"){
				 $("#"+divId+innerCount).html('<option value="0">Select Sub Subject</option>');
				for(var i in result){
					$("#"+divId+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		if(type=="change"){
			$("#"+divId+innerCount).trigger('chosen:updated');
		}
		
		
	});	
}
function getWorkTypeList(divId,typeVal,count,innerCount,type){
	
	$("#"+divId+typeVal+count+innerCount).html('<option value="0">Select Work Type</option>');
	 $("#"+divId+typeVal+count+innerCount).trigger('chosen:updated');
	 
	 var json = {};
	 
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
			 if(type=="onload"){
				globalWorkTypeList =result; 
			}
			
			for(var i in result){
			 $("#"+divId+typeVal+count+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
		   }
		 }
		  $("#"+divId+typeVal+count+innerCount).trigger('chosen:updated');
			if(GlWrkTypeId != null && GlWrkTypeId !='undefined')
				$("#"+divId+typeVal+count+innerCount).val(GlWrkTypeId);
			$("#"+divId+typeVal+count+innerCount).trigger('chosen:updated');
	 }); 
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
		}else if (value== 16 || value== 2  || value ==7){
			$("#constituencyCandDivId").hide();
			$("#districtCandDivId").show();
			getAllDistrictsInState("popup","","","change");
		}else{
			$("#constituencyCandDivId").show();
			$("#districtCandDivId").show();
			var typeVal = $(this).attr("attr_type");
			$(".selectionSearchDetailsCls").attr("attr_type",typeVal);
			getAllDistrictsInState("popup","","","change");
			if(typeVal == "self"){
				$("#candidateDetailsDivId").html('');
			}else{
				$("#candidateDetailsDivId").html('');
			}
		}
	}
});
$(document).on("click",".searchCandidateCls",function(){
	var typeVal = $(this).attr("attr_type");
	$(".selectionSearchDetailsCls").attr("attr_type",typeVal);

	 $('#districtCandDivId').show();
	 $('#constituencyCandDivId').show();
	 $("#designationsId").html('<option value="0">Select Designation</option>');
	 $("#districtCandId").html('<option value="0">Select District</option>');
	 $("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	 $("#designationsId").trigger("chosen:updated");
	 $("#districtCandId").trigger("chosen:updated");
	 $("#constituencyCanId").trigger("chosen:updated");
	 
	if(typeVal == "SELF"){
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
	
	
	if (desiganationId== 4 || desiganationId== 11){
		locationLevelId=10;
	}
	searchCandidateIds=[];
	diffArr=[];
	commonArr=[];	
	getPetitionReferredMemberDetails(desiganationId,locationLevelId,locationValue,typeVal);
	
});


$(document).on("click",".candidateAddedView",function(){
	 
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	
	alreadyCandidateId.push(parseInt(candidateId));
	
	$(".candidateDetails"+typeVal+"DivId").append($("#candidatesAppendDiv"+candidateId).html());
	
	$(".candidateDetails"+typeVal+"DivId").find(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-3").addClass("col-sm-2");
	$(".candidateDetails"+typeVal+"DivId").find("#fileUpload"+typeVal+candidateId).show();
	
	
	
	if(typeVal =='SELF'){
		$(".candidateDetails"+typeVal+"DivId").append('<input type="hidden" id="petitionSelfRef'+refCandCount+'" name="selfReferList['+refCandCount+'].refCandidateId" value="'+candidateId+'" />');
		$("#fileUpload"+typeVal+candidateId).append('<div class="col-sm-4" style="margin-top:-20px;"><label>REFERAL LETTER</label><input type="file"   attr_name="selfReferList['+refCandCount+']" name="" attr_image_tyep="refImage"  id="editFileUpload'+candidateId+''+typeVal+'" multiple="multiple" class=""/></div>');
		//$('.searchCandidateCls').hide();
	}
	else if(typeVal =='REPRESENTEE'){
		$("#fileUpload"+typeVal+candidateId).append('<div class="col-sm-4" style="margin-top:-20px;"><label>REFERAL LETTER</label><input type="file"   attr_name="referList['+refCandCount+']" name="" attr_image_tyep="refImage"  id="editFileUpload'+candidateId+''+typeVal+'" multiple="multiple" class=""/></div>');
		
		$(".candidateDetails"+typeVal+"DivId").append('<input type="hidden" id="petitionRef'+refCandCount+'" name="referList['+refCandCount+'].refCandidateId" value="'+candidateId+'" />');	
	}
	refCandCount=refCandCount+1;
	$(this).parent().find(".representation-selected").addClass("display_block");
	$("#candidatesAppendDiv"+candidateId).find("#candidate"+typeVal+candidateId).removeClass("candidateAddedView");
	
	$(".candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").removeClass("candidateAddedView");
	$(".candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").css("cursor","auto");
	$(".showRemoveIcon").attr("attr_candidateId",candidateId)
	$(".candidateDetails"+typeVal+"DivId").find(".showRemoveIcon").show();
	initializeEditFileUploadMainBlock(typeVal,candidateId,"editFileUpload");
});

$(document).on("click",".ccccc",function(){
	
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	
	$(".candidateDetails"+typeVal+"DivId").find("#candidate"+typeVal+candidateId).remove();
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
	if(!confirm("Are you sure want to delete this referral memeber?"))
		return;
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidate_id");
	refCandCount=refCandCount-1;
	$(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-2").addClass("col-sm-3");
	
	$(".candidateDetails"+typeVal+"DivId").find("#candidate"+typeVal+candidateId).remove();
	$(".candidateDetails"+typeVal+"DivId").find(".candidate"+typeVal+candidateId).remove();
	var itemtoRemove = parseInt(candidateId);
	alreadyCandidateId.splice($.inArray(itemtoRemove, alreadyCandidateId),1);

	/* if(typeVal=='SELF'){
		$(".candidateDetails"+typeVal+"DivId").html('');
		$('.searchCandidateCls').show();
	} */
	
});

$(document).on("click",".removeFileCls",function(){
	if(!confirm("Are you sure want to delete this work document?"))
		return;
	var divId = $(this).attr("attr_id");
	$("#"+divId+"").remove();
	$("#existing"+divId+"").remove();
	$("#existingDocument"+divId+"").remove();
});

$(document).on("change",".locationLevelChange",function(){

	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var changeType = $(this).attr("attr_type_change");	
	var workId = $(this).attr("attr_work_id");
	if(changeType == "main"){
		if(workId != null && workId != undefined && workId != 'undefined')
			;
		else{
			//$("#districtId"+typeVal+counterId).html('<option value="0"> Select District </option>');
			//$("#constituencyId"+typeVal+counterId).html('<option value="0"> Select constituency</option>');
			//$("#mandalId"+typeVal+counterId).html('<option value="0"> Select Mandal/Munci/Corp.</option>');
			//$("#panchayatId"+typeVal+counterId).html('<option value="0"> Select Panchayat</option>');
		}
	}else{
		if(workId != null && workId != undefined && workId != 'undefined')
			;
		else{
			//$("#districtInnerId"+typeVal+counterId).html('<option value="0"> Select District </option>');
			//$("#constituencyInnerId"+typeVal+counterId).html('<option value="0"> Select constituency</option>');
			//$("#mandalInnerId"+typeVal+counterId).html('<option value="0"> Select Mandal/Munci/Corp.</option>');
			//$("#panchayatInnerId"+typeVal+counterId).html('<option value="0"> Select Panchayat</option>');
		}
	}
	
	if(levelVal == 3){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).hide();
			$(".mandalCls"+typeVal+counterId).hide();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);

			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).hide();
			$(".mandalInnerCls"+typeVal+counterId).hide();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 4){
		if(changeType == "main"){
			 $("#districtId"+typeVal+counterId).trigger("chosen:updated");
			 if(workId != null && workId != undefined && workId != 'undefined')
				 ;
		 	else
				 getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);

			  $("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			  $(".districtCls"+typeVal+counterId).show();
			  $(".constituencyCls"+typeVal+counterId).show();
			  $(".mandalCls"+typeVal+counterId).hide();
			  $(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);
			 $("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			 $(".districtInnerCls"+typeVal+counterId).show();
			 $(".constituencyInnerCls"+typeVal+counterId).show();
			 $(".mandalInnerCls"+typeVal+counterId).hide();
			 $(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 5){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);
			$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).show();
			$(".mandalCls"+typeVal+counterId).show();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);
			$("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalInnerId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).show();
			$(".mandalInnerCls"+typeVal+counterId).show();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 6){
			if(changeType == "main"){
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);
			$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).show();
			$(".mandalCls"+typeVal+counterId).show();
			$(".panchayatCls"+typeVal+counterId).show();
			}else{
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			if(workId != null && workId != undefined && workId != 'undefined')
				;
			else
				getAllDistrictsInState(typeVal,counterId,changeType);
			if(GlConsttuencyId != null && GlConsttuencyId>0)
				getConstituencyNamesByDistrictId(GlDistrctId,counterId,typeVal,changeType);
			$("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#panchayatInnerId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).show();
			$(".mandalInnerCls"+typeVal+counterId).show();
			$(".panchayatInnerCls"+typeVal+counterId).show();
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
	var locationLevelId = $('#locationLevelId'+typeVal+''+counterId+'').val();
	if(typeChange == 'Inner')
		locationLevelId = $('#locationLevelInnerId'+typeVal+''+counterId+'').val();	
	if(parseInt(levelVal)>0 && parseInt(locationLevelId)>3)
		getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,typeChange);
	
});

$(document).on("change",".constituencyLevelChange",function(){

	
		var levelVal = $(this).val();
		var counterId = $(this).attr("attr_counterval");
		var typeVal = $(this).attr("attr_type");
		var typeChange = $(this).attr("attr_type_change");
		var locationLevelId = $('#locationLevelId'+typeVal+''+counterId+'').val();
		if(typeChange == 'Inner')
			locationLevelId = $('#locationLevelInnerId'+typeVal+''+counterId+'').val();	
		if(parseInt(levelVal)>0 && parseInt(locationLevelId)>4)
			getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal,typeChange);
		$(this).removeAttr('attr_load_type');
	
});

$(document).on("change",".mandalLevelChange",function(){
	var levelVal = $(this).val();
	levelVal=levelVal.toString().substr(1,levelVal.length);
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var typeChange = $(this).attr("attr_type_change");	
	var locationLevelId = $('#locationLevelId'+typeVal+''+counterId+'').val();
	if(typeChange == 'Inner')
		locationLevelId = $('#locationLevelInnerId'+typeVal+''+counterId+'').val();	
	if(parseInt(levelVal)>0 && parseInt(locationLevelId)==6)
		getPanchayats(levelVal,counterId,typeVal,typeChange);
	
});

/*
$(document).on("change",".locationLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var changeType = $(this).attr("attr_type_change");

	
	if(levelVal == 3){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).hide();
			$(".mandalCls"+typeVal+counterId).hide();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).hide();
			$(".mandalInnerCls"+typeVal+counterId).hide();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 4){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('<option value="0">Select District</option>');
			 $("#districtId"+typeVal+counterId).trigger("chosen:updated");
			 getAllDistrictsInState(typeVal,counterId,changeType,"change");
			  $("#constituencyId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			  $("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			  $(".districtCls"+typeVal+counterId).show();
			  $(".constituencyCls"+typeVal+counterId).show();
			  $(".mandalCls"+typeVal+counterId).hide();
			  $(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$("#constituencyInnerId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			 $("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			 $(".districtInnerCls"+typeVal+counterId).show();
			 $(".constituencyInnerCls"+typeVal+counterId).show();
			 $(".mandalInnerCls"+typeVal+counterId).hide();
			 $(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 5){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$("#constituencyId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalId"+typeVal+counterId).html('<option value="0">Select Mandal/Munci/Corp</option>');
			$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).show();
			$(".mandalCls"+typeVal+counterId).show();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$("#constituencyInnerId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			$("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalInnerId"+typeVal+counterId).html('<option value="0">Select Mandal/Munci/Corp</option>');
			$("#mandalInnerId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).show();
			$(".mandalInnerCls"+typeVal+counterId).show();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 6){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$("#constituencyId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalId"+typeVal+counterId).html('<option value="0">Select Mandal/Munci/Corp</option>');
			$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
			$("#panchayatId"+typeVal+counterId).html('<option value="0">Select Panchayat</option>');
			$("#panchayatId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).show();
			$(".mandalCls"+typeVal+counterId).show();
			$(".panchayatCls"+typeVal+counterId).show();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('<option value="0">Select District</option>');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$("#constituencyInnerId"+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			$("#constituencyInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#mandalInnerId"+typeVal+counterId).html('<option value="0">Select Mandal/Munci/Corp</option>');
			$("#mandalInnerId"+typeVal+counterId).trigger("chosen:updated");
			$("#panchayatInnerId"+typeVal+counterId).html('<option value="0">Select Panchayat</option>');
			$("#panchayatInnerId"+typeVal+counterId).trigger("chosen:updated");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).show();
			$(".mandalInnerCls"+typeVal+counterId).show();
			$(".panchayatInnerCls"+typeVal+counterId).show();
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
*//*
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
$(document).on("change",".mandalLevelChange",function(){
	var levelVal = $(this).val();
	levelVal=levelVal.toString().substr(1,levelVal.length);
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var typeChange = $(this).attr("attr_type_change");
	getPanchayats(levelVal,counterId,typeVal,typeChange);
	
});*/
$(document).on("click",".cloned_Element",function(){
	var typeVal = $(this).attr("attr_type");

	var estimationWorksCount = $('#noofWork'+typeVal+'').val();
	
	/*
	var enteredAmount =parseInt(0);
	var estimationAmount= parseInt($('#workCost'+typeVal+'').val());
	$(".amountCls").each(function(){
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseInt(value) <=0){
				alert("Invalid estimation cost entered. Please check once.");
				return;
			}else{
				enteredAmount = parseInt(enteredAmount)+parseInt(value);
			}
		}
	});
	
	if(enteredAmount>=estimationAmount){
		alert("Total estimation cost reached. Please check once.");
		return;
	}
	*/
	
	var enteredAmount =parseFloat(0.0);
	$(".amountCls").each(function(){
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseFloat(value) <=0){	
				//alert("Invalid estimation cost entered. Please check once.");
				//return;
			}else{
				enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
			}
		}
	});
	
	var estimationAmount= $('#workCost'+typeVal+'').val();
	if(estimationAmount != null && estimationAmount !='' && estimationAmount.length>0 && parseFloat(estimationAmount)>0){
		if(enteredAmount>=estimationAmount){
			//alert("Total estimation cost reached. Please check once.");
			//return;
		}
	}
	if(parseInt(estimationWorksCount)<=parseInt(globalInnerWorksCount)){
		alert("Max no of works data entered. Please check once.");
		return;
	}
	
	
	
	 var changeType = "main";
	/* getting previous work details */
	/*
		var mandalIds =0;
		
	  
	   var tempCounterId = $(this).attr("block-clone-"+typeVal+"");
	   var mainWorkCount = tempCounterId; 
	   var innerWorkCount = globalInnerWorksCount;
		var existingFieldsInnerWorkCount = parseInt(innerWorkCount)-1;
		alert("mainWorkCount  :"+mainWorkCount);
		alert("innerWorkCount  :"+innerWorkCount);
		alert("existingFieldsInnerWorkCount  :"+existingFieldsInnerWorkCount);
		for(var i=existingFieldsInnerWorkCount;(i>=0 && i<=existingFieldsInnerWorkCount);i--){
			if(i == 0){
				alert('#workTypeId'+typeVal+''+mainWorkCount+''+i);
				GlWrkTypeId = $('#workTypeId'+typeVal+''+mainWorkCount+''+i).val();
				
				GlLocationLvelId = $('#locationLevelId'+typeVal+''+mainWorkCount+''+i).val();
				GlDistrctId = $('#districtId'+typeVal+''+mainWorkCount+''+i).val();
				GlConsttuencyId = $('#constituencyId'+typeVal+''+mainWorkCount+''+i).val();
				GlMndalId = $('#mandalId'+typeVal+''+mainWorkCount+''+i).val();
				if(GlMndalId != null && GlMndalId !='undefined')
				  mandalIds = GlMndalId.toString().substr(1,GlMndalId.length-1);
				GlPnchayatId = $('#panchayatId'+typeVal+''+mainWorkCount+''+i).val();	
			}else{
				alert(" i > "+i);//workTypeInnerIdself11
				alert('#workTypeInnerId'+typeVal+''+mainWorkCount+''+i);
				GlWrkTypeId = $('#workTypeInnerId'+typeVal+''+mainWorkCount+''+i).val();
				GlLocationLvelId = $('#locationLevelInnerId'+typeVal+''+mainWorkCount+''+i).val();
				GlDistrctId = $('#districtInnerId'+typeVal+''+mainWorkCount+''+i).val();
				GlConsttuencyId = $('#constituencyInnerId'+typeVal+''+mainWorkCount+''+i).val();
				GlMndalId= $('#mandalInnerId'+typeVal+''+mainWorkCount+''+i).val();
				if(GlMndalId != null && GlMndalId !='undefined')
					mandalIds = GlMndalId.toString().substr(1,GlMndalId.length-1);
				GlPnchayatId = $('#panchayatInnerId'+typeVal+''+mainWorkCount+''+i).val();
			}
			if(parseInt(GlLocationLvelId)>0){
				break;
			}
		}
		*/
		/* auto locations populating */
		
	var workCount = $("#noofWork"+typeVal).val();
	var counterappendId = $(this).attr("block-clone-counter-"+typeVal+"");
	var counterId = $(this).attr("block-clone-counter-"+typeVal+"");
		counterId = parseInt(counterId) + 1;
		//if(parseInt(counterappendId)>0)
			counterappendId = parseInt(counterappendId)-1;
		
	var blockId = $(this).attr("block-clone-"+typeVal+"");
	globalWorkTypeCount = parseInt(globalWorkTypeCount)+1;
	globalInnerWorksCount=parseInt(globalInnerWorksCount)+1;
	$(".appendWorkDetailsDiv"+typeVal+"").append(clondTemplate(0,'clone',counterId,typeVal,counterappendId,"No",0));
	$(".chosen-select").chosen({width:'100%'});
	$('workTypeId'+typeVal+''+counterappendId+'0').trigger('chosen:updated');
	$("[block-clone-"+typeVal+"="+blockId+"]").attr("block-clone-counter-"+typeVal+"",counterId);
	
		getAllDistrictsInState(typeVal,counterappendId+"0",changeType);
		if(GlLocationLvelId != null && GlLocationLvelId !='undefined' && GlLocationLvelId == 4){
			getAllDistrictsInState(typeVal,counterappendId+"0",changeType);
			getConstituencyNamesByDistrictId(GlDistrctId,counterappendId+"0",typeVal,changeType);
		}else if(GlLocationLvelId != null && GlLocationLvelId !='undefined' && GlLocationLvelId == 5){
			getAllDistrictsInState(typeVal,counterappendId+"0",changeType);
			getConstituencyNamesByDistrictId(GlDistrctId,counterappendId+"0",typeVal,changeType);
			getTehsilsAndLocalElectionBodyForConstituencyId(GlConsttuencyId,counterappendId+"0",typeVal,changeType);
		}else if(GlLocationLvelId != null && GlLocationLvelId !='undefined' && GlLocationLvelId == 6){
			getAllDistrictsInState(typeVal,counterappendId+"0",changeType);
			getConstituencyNamesByDistrictId(GlDistrctId,counterappendId+"0",typeVal,changeType);
			getTehsilsAndLocalElectionBodyForConstituencyId(GlConsttuencyId,counterappendId+"0",typeVal,changeType);
			getPanchayats(mandalIds,counterappendId+"0",typeVal,changeType);
		}
	
});

function clondTemplate(blockId,type,counterId,typeVal,counterappendId,isNew,locationLvelId){

	var defaultValue=0;
	var clonedTemplate='';
	var tempcounterId=1;
	clonedTemplate+='<div class="col-sm-12 mainWorkDivCls " cloned_block_'+typeVal+'='+counterappendId+' id="mainWorkDivId'+counterappendId+''+blockId+'">';
	if(isNew =="Yes")
		clonedTemplate+='<div class="pull-right " attr_inner_works_Count="1" id="manageInnerWorks'+globalWorkTypeCount+'"  attr_id="mainWorkDivId'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_candidateid="1"></div>';
	else
		clonedTemplate+='<div class="pull-right removeWorkCls" id="manageInnerWorks'+globalWorkTypeCount+'" attr_inner_works_Count="1" attr_id="mainWorkDivId'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_candidateid="1"><i class="glyphicon glyphicon-remove" style="cursor:pointer;" title="Remove Work(s)"></i></div>';
	
		clonedTemplate+='<div style="border:3px solid #dddddd;padding:10px;">';
			clonedTemplate+='<div  cloned_block_'+typeVal+'='+counterappendId+'>';
				//clonedTemplate+='<h3 class="panel-title f_22">WORK TYPE '+counterId+'</h3>';
				
				clonedTemplate+='<div class="row m_top10" >';
				
					clonedTemplate+='<div class="col-sm-3">';
							clonedTemplate+='<label>DEPARTMENT <span class="starColor">*</span></label>';
							clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].deptId"  class="form-control chosen-select m_top10 validateCls OnchangeDeptCls"  id="WorkTypeWiseDepartmentId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'" onChange=getPetitionSubjectList(this.value,"subjectId'+typeVal+''+counterappendId+'","'+blockId+'","change")>';
							clonedTemplate+='<option value="0"> Select Department </option>';
							if(globalDepartmentsList != null && globalDepartmentsList.length>0){
								for(var x in globalDepartmentsList){
									clonedTemplate+='<option value="'+globalDepartmentsList[x].key+'">'+globalDepartmentsList[x].value+'</option>';
								}
							}
							clonedTemplate+='</select><br><span class="WorkTypeWiseDepartmentId'+typeVal+''+counterappendId+''+blockId+'"></span>';
						clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="col-sm-3">';
						clonedTemplate+='<label>SUBJECT <span class="starColor">*</span></label>';
						clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].subjectId"  class="form-control chosen-select m_top10 validateCls subjctOnchangeCls"  id="subjectId'+typeVal+''+counterappendId+''+blockId+'" onChange=getPetitionSubSubjectList(this.value,"subSubjectId'+typeVal+''+counterappendId+'","'+blockId+'","change") attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'" >';
							clonedTemplate+='<option value="0">Select Subject</option>';
						clonedTemplate+='</select><br><span class="subjectId'+typeVal+''+counterappendId+''+blockId+'"></span>';
					clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="col-sm-3">';
						clonedTemplate+='<label>SUB-SUBJECT <span class="starColor">*</span></label>';
						clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].subSubjectId"  class="form-control chosen-select m_top10 validateCls subSubjectReFreshCls'+counterappendId+' subSubjctOnchangeCls" id="subSubjectId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
							clonedTemplate+='<option value="0">Select Sub Subject</option>';
						clonedTemplate+='</select><br><span class="subSubjectId'+typeVal+''+counterappendId+''+blockId+'"></span>';
					clonedTemplate+='</div>';
								
				clonedTemplate+='</div>';
				
				clonedTemplate+='<div class="row m_top10 " id="workDivId'+counterappendId+''+blockId+'" >';
					clonedTemplate+='<div class="col-sm-12">';
						clonedTemplate+='<div class="bg_color_view">';
							//clonedTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK TYPE <span class="starColor">*</span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].workTypeId"  class="form-control chosen-select m_top10 validateCls newValidateCls workTypeReFreshCls'+counterappendId+'" id="workTypeId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
										clonedTemplate+='<option value="0"> Select Work Type </option>';
										for(var w in globalWorkTypeList){
											if(globalWorkTypeList[w].key == 2 || globalWorkTypeList[w].value=='New Proposal')
												clonedTemplate+='<option value='+globalWorkTypeList[w].key+' selected>'+globalWorkTypeList[w].value+'</option>';
											else
												clonedTemplate+='<option value='+globalWorkTypeList[w].key+'>'+globalWorkTypeList[w].value+'</option>';
										}
										clonedTemplate+='</select><br><span class="workTypeId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK IN COST  (in Lakhs)</label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].estimateCost" class="form-control m_top5 height45 validateCls amountCls workCostReFreshCls'+counterappendId+'" id="appendWorkCost'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter Work Cost" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'"  onkeyUp="checkIsNumber(this.id,this.value);validateAmount(this.value,this.id,\''+typeVal+'\')"><br><span class="appendWorkCost'+typeVal+''+counterappendId+''+blockId+' ErrCls"></span>';
									clonedTemplate+='</div>';
									/*clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK DETAILS <span class="starColor">*</span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].grievanceDescription" class="form-control m_top5 height45 validateCls workDetailsReFreshCls'+counterappendId+'" id="appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'"> <br><span class="  ErrCls appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';*/
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>eOFFICE-ID </label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].eOfficeId" class="form-control m_top5 height45 validateCls officerIdReFreshCls'+counterappendId+'" id="appendEofficeId'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
									clonedTemplate+='</div>';
							clonedTemplate+='</div>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-12">';
										clonedTemplate+='<label>WORK DETAILS <span class="starColor">*</span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].grievanceDescription" class="form-control m_top5  validateCls workDetailsReFreshCls'+counterappendId+'" id="appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter work details..." attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'"  style="height:100px;"/> <br><span class="  ErrCls appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
								clonedTemplate+='</div>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>LOCATION LEVEL <span class="starColor">*</span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+' validateCls newValidateCls locationLevelReFreshCls'+counterappendId+'" id="locationLevelId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Level</option>';
											if(locationLvelId == 3 || locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
												clonedTemplate+='<option value="3" selected>District</option>';
											else
												clonedTemplate+='<option value="3">District</option>';
											if(locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
												clonedTemplate+='<option value="4" selected>Constituency</option>';
											else
												clonedTemplate+='<option value="4">Constituency</option>';
											if(locationLvelId == 5 || locationLvelId == 6)
												clonedTemplate+='<option value="5" selected>Mandal/Muncipality/Corporation</option>';
											else
												clonedTemplate+='<option value="5" >Mandal/Muncipality/Corporation</option>';
											if(locationLvelId == 6)
												clonedTemplate+='<option value="6" selected>Panchayat</option>';
											else
												clonedTemplate+='<option value="6">Panchayat</option>';
											
										clonedTemplate+='</select><br><span class="locationLevelId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
									if(locationLvelId == 3 || locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
										clonedTemplate+='<div class="col-sm-2 districtCls'+typeVal+''+counterappendId+''+blockId+'" style="">';
									else 
										clonedTemplate+='<div class="col-sm-2 districtCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
									
										clonedTemplate+='<label>DISTRICT <span class="starColor">*</span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select District</option>';
										clonedTemplate+='</select><br><span class="districtId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
									if(locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
										clonedTemplate+='<div class="col-sm-2 constituencyCls'+typeVal+''+counterappendId+''+blockId+'" style="">';
									else
										clonedTemplate+='<div class="col-sm-2 constituencyCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
									
										clonedTemplate+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
										clonedTemplate+='<select   name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conChangeCls conRefreshCls'+counterappendId+''+blockId+'" id="constituencyId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Constituency</option>';
										clonedTemplate+='</select><br><span class="constituencyId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
									if(locationLvelId == 5 || locationLvelId == 6)
										clonedTemplate+='<div class="col-sm-2 mandalCls'+typeVal+''+counterappendId+''+blockId+'" style="">';
									else
										clonedTemplate+='<div class="col-sm-2 mandalCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
										clonedTemplate+='<label>MANDAL/MUNCI. <span class="starColor">*</span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+counterappendId+''+blockId+' madalChangeCls" id="mandalId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Mandal</option>';
										clonedTemplate+='</select><br><span class="mandalId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
									if(locationLvelId == 6)
										clonedTemplate+='<div class="col-sm-2 panchayatCls'+typeVal+''+counterappendId+''+blockId+'">';
									else
										clonedTemplate+='<div class="col-sm-2 panchayatCls'+typeVal+''+counterappendId+''+blockId+'" style="display:none">';
										clonedTemplate+='<label>PANCHAYAT <span class="starColor">*</span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+defaultValue+'].addressVO.panchayatId"  class="form-control chosen-select m_top10  validateCls panchayatRefreshCls'+counterappendId+''+blockId+'" id="panchayatId'+typeVal+''+counterappendId+''+blockId+'" attr_counterval="'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Panchayat</option>';
										clonedTemplate+='</select><br><span class="panchayatId'+typeVal+''+counterappendId+''+blockId+'"></span>';
									clonedTemplate+='</div>';
									
							clonedTemplate+='</div>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="appendInnerBlocks'+typeVal+''+counterappendId+''+tempcounterId+'"></div>';
					clonedTemplate+='<div class="col-sm-12">';
						clonedTemplate+='<div class="pull-right">';
							clonedTemplate+='<span class="addLocationCss m_top10 pull-right cloned_Inner_Element" style="cursor:pointer;" attr_type="'+typeVal+'" attr_counterval="'+counterappendId+'" main_work_count="'+(parseInt(globalWorkTypeCount)-1)+'" inner_work_count="1" attr_id="manageInnerWorks'+globalWorkTypeCount+'">ADD WORK  </span>';
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
	var estimationWorksCount = $('#noofWork'+typeVal+'').val();
	
	/*
	var enteredAmount =parseInt(0);
	var estimationAmount= parseInt($('#workCost'+typeVal+'').val());
	$(".amountCls").each(function(){
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseInt(value) <=0){
				alert("Invalid estimation cost entered. Please check once.");
				return;
			}else{
				enteredAmount = parseInt(enteredAmount)+parseInt(value);
			}
		}
	});
	
	if(enteredAmount>=estimationAmount){
		alert("Total estimation cost reached. Please check once.");
		return;
	}
	*/
	
	var enteredAmount =parseFloat(0.0);
	$(".amountCls").each(function(){
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseFloat(value) <=0){	
				//alert("Invalid estimation cost entered. Please check once.");
				//return;
			}else{
				enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
			}
		}
	});
	
	var estimationAmount= $('#workCost'+typeVal+'').val();
	if(estimationAmount != null && estimationAmount !='' && estimationAmount.length>0 && parseFloat(estimationAmount)>0){
		if(enteredAmount>=estimationAmount){
			//alert("Total estimation cost reached. Please check once.");
			//return;
		}
	}
	
	if(parseInt(estimationWorksCount)<=parseInt(globalInnerWorksCount)){
		alert("Max no of works data entered. Please check once.");
		return;
	}
	
	
	
	
	/* getting previous work details */
	
		var mandalIds =0;
		
		var changeType = "Inner";
		var counterId = $(this).attr("attr_counterval");	
		var mainWorkCount = $(this).attr("main_work_count");
		var innerWorkCount = $(this).attr("inner_work_count");
		var existingFieldsInnerWorkCount = parseInt(innerWorkCount)-1;
		for(var i=existingFieldsInnerWorkCount;(i>=0 && i<=existingFieldsInnerWorkCount);i--){
				if(i == 0){
					GlWrkTypeId = $('#workTypeId'+typeVal+''+mainWorkCount+''+i).val();
					GlLocationLvelId = $('#locationLevelId'+typeVal+''+mainWorkCount+''+i).val();
					GlDistrctId = $('#districtId'+typeVal+''+mainWorkCount+''+i).val();
					GlConsttuencyId = $('#constituencyId'+typeVal+''+mainWorkCount+''+i).val();
					GlMndalId = $('#mandalId'+typeVal+''+mainWorkCount+''+i).val();
					if(GlMndalId != null && GlMndalId !='undefined')
					  mandalIds = GlMndalId.toString().substr(1,GlMndalId.length-1);
					GlPnchayatId = $('#panchayatId'+typeVal+''+mainWorkCount+''+i).val();
				}else{
					GlWrkTypeId = $('#workTypeInnerId'+typeVal+''+mainWorkCount+''+i).val();
					GlLocationLvelId = $('#locationLevelInnerId'+typeVal+''+mainWorkCount+''+i).val();
					GlDistrctId = $('#districtInnerId'+typeVal+''+mainWorkCount+''+i).val();
					GlConsttuencyId = $('#constituencyInnerId'+typeVal+''+mainWorkCount+''+i).val();
					GlMndalId = $('#mandalInnerId'+typeVal+''+mainWorkCount+''+i).val();
					if(GlMndalId != null && GlMndalId !='undefined')
						mandalIds = GlMndalId.toString().substr(1,GlMndalId.length-1);
					GlPnchayatId = $('#panchayatInnerId'+typeVal+''+mainWorkCount+''+i).val();
					
				}
				if(parseInt(GlLocationLvelId)>0){
					break;
				}
			//}
		}
		
		/* auto locations populating */
		
	$(this).attr("inner_work_count",parseInt(innerWorkCount)+1);
	var conterInnerVal = parseInt(innerWorkCount)+1
	var mangageWorksId = $(this).attr('attr_id');
	var attr_inner_works_Count = $('#'+mangageWorksId+'').attr("attr_inner_works_Count");
	
	$('#'+mangageWorksId+'').attr("attr_inner_works_Count",parseInt(attr_inner_works_Count)+1);
	
		globalInnerWorksCount = parseInt(globalInnerWorksCount)+1;
		//alert(".appendInnerBlocks"+typeVal+counterId+innerWorkCount);
		$(".appendInnerBlocks"+typeVal+counterId+innerWorkCount).append(clonedInnerTemplate('clone',counterId,typeVal,mainWorkCount,innerWorkCount,conterInnerVal,mangageWorksId,GlLocationLvelId));
		$(".chosen-select").chosen({width:'100%'});
		//getWorkTypeList('workTypeInnerId',typeVal,counterId,innerWorkCount,"change");
		
		getAllDistrictsInState(typeVal,counterId+''+innerWorkCount,changeType);
		if(GlLocationLvelId != null && GlLocationLvelId !='undefined' && GlLocationLvelId == 4){
			getAllDistrictsInState(typeVal,counterId+''+innerWorkCount,changeType);
			getConstituencyNamesByDistrictId(GlDistrctId,counterId+''+innerWorkCount,typeVal,changeType);
		}else if(GlLocationLvelId != null && GlLocationLvelId !='undefined' && GlLocationLvelId == 5){
			getAllDistrictsInState(typeVal,counterId+''+innerWorkCount,changeType);
			getConstituencyNamesByDistrictId(GlDistrctId,counterId+''+innerWorkCount,typeVal,changeType);
			getTehsilsAndLocalElectionBodyForConstituencyId(GlConsttuencyId,counterId+''+innerWorkCount,typeVal,changeType);
		}else if(GlLocationLvelId != null && GlLocationLvelId !='undefined' && GlLocationLvelId == 6){
			getAllDistrictsInState(typeVal,counterId+''+innerWorkCount,changeType);
			getConstituencyNamesByDistrictId(GlDistrctId,counterId+''+innerWorkCount,typeVal,changeType);
			getTehsilsAndLocalElectionBodyForConstituencyId(GlConsttuencyId,counterId+''+innerWorkCount,typeVal,changeType);
			getPanchayats(mandalIds,counterId+''+innerWorkCount,typeVal,changeType);
		}
});


function clonedInnerTemplate(type,counterId,typeVal,mainWorkCount,innerWorkCount,conterInnerVal,parentRemoveClsId,locationLvelId){ 
	var clonedInnerTemplate='';
	clonedInnerTemplate+='<div class="m_top10" style="padding: 15px;">';
		clonedInnerTemplate+='<div class="col-sm-12 m_top10" >';
		clonedInnerTemplate+='<div class="pull-right  removeInnerWorkCls" id="'+parentRemoveClsId+'" attr_id="workDivId'+counterId+''+innerWorkCount+'" attr_type="self" attr_candidateid="1"><i class="glyphicon glyphicon-remove" style="cursor:pointer;" title="Remove this Work" ></i></div>';
			clonedInnerTemplate+='<div class="bg_color_view row " id="workDivId'+counterId+''+innerWorkCount+'">';
				//clonedInnerTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
				clonedInnerTemplate+='<div class="row m_top10" >';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK TYPE  <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].workTypeId"  class="form-control chosen-select m_top10 validateCls validateInnerCls newValidateInnerCls workTypeReFreshCls'+counterId+'" id="workTypeInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0"> Select Work Type </option>';//kk
									for(var w in globalWorkTypeList){
										if(globalWorkTypeList[w].key == 2 )
											clonedInnerTemplate+='<option value='+globalWorkTypeList[w].key+' selected>'+globalWorkTypeList[w].value+'</option>';
										else
											clonedInnerTemplate+='<option value='+globalWorkTypeList[w].key+'>'+globalWorkTypeList[w].value+'</option>';
									}
							clonedInnerTemplate+='</select><br><span class="workTypeInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK IN COST  (in Lakhs)</label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].estimateCost" class="form-control m_top5 height45 validateCls amountCls workCostReFreshCls'+counterId+'" id="appendWorkCostInner'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter Work Cost" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'"  onkeyUp="checkIsNumber(this.id,this.value);;validateAmount(this.value,this.id,\''+typeVal+'\')"><br><span class="  ErrCls appendWorkCostInner'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
						/*clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK DETAILS <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].grievanceDescription" class="form-control m_top5 height45 validateCls workDetailsReFreshCls'+counterId+'" id="appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'"><br><span class=" ErrCls appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';*/
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>eOFFICE-ID </label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].eOfficeId" class="form-control m_top5 height45 validateCls officerIdReFreshCls'+counterId+'" id="appendEofficeInnerId'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='<div class="row m_top10">';
						clonedInnerTemplate+='<div class="col-sm-12">';
							clonedInnerTemplate+='<label>WORK DETAILS <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].grievanceDescription" class="form-control m_top5  validateCls workDetailsReFreshCls'+counterId+'" id="appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter work details..." attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'"  style="height:100px;"/><br><span class=" ErrCls appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='<div class="row m_top10">';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>LOCATION LEVEL <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+' validateCls locationLevelReFreshCls'+counterId+'" id="locationLevelInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Level</option>';
								if(locationLvelId == 3 || locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
									clonedInnerTemplate+='<option value="3" selected>District</option>';
								else
									clonedInnerTemplate+='<option value="3">District</option>';
								if(locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
									clonedInnerTemplate+='<option value="4" selected>Constituency</option>';
								else
									clonedInnerTemplate+='<option value="4">Constituency</option>';
								if(locationLvelId == 5 || locationLvelId == 6)
									clonedInnerTemplate+='<option value="5" selected>Mandal/Muncipality/Corporation</option>';
								else
									clonedInnerTemplate+='<option value="5" >Mandal/Muncipality/Corporation</option>';
								if(locationLvelId == 6)
									clonedInnerTemplate+='<option value="6" selected>Panchayat</option>';
								else
									clonedInnerTemplate+='<option value="6">Panchayat</option>';
							clonedInnerTemplate+='</select> <br><span class="locationLevelInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
						
						if(locationLvelId == 3 || locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
							clonedInnerTemplate+='<div class="col-sm-2 districtInnerCls'+typeVal+''+counterId+''+innerWorkCount+'">';
						else 
							clonedInnerTemplate+='<div class="col-sm-2 districtInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
						
						
							clonedInnerTemplate+='<label>DISTRICT <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select District</option>';
							clonedInnerTemplate+='</select><br><span class="districtInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
						
						if(locationLvelId == 4 || locationLvelId == 5 || locationLvelId == 6)
							clonedInnerTemplate+='<div class="col-sm-2 constituencyInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" >';
						else
							clonedInnerTemplate+='<div class="col-sm-2 constituencyInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
							
						
							clonedInnerTemplate+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<select   name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conChangeCls conRefreshCls'+counterId+''+innerWorkCount+'" id="constituencyInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Constituency</option>';
							clonedInnerTemplate+='</select><br><span class="constituencyInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
						
						if(locationLvelId == 5 || locationLvelId == 6)
							clonedInnerTemplate+='<div class="col-sm-2 mandalInnerCls'+typeVal+''+counterId+''+innerWorkCount+'">';
						else
							clonedInnerTemplate+='<div class="col-sm-2 mandalInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
							
						
							clonedInnerTemplate+='<label>MANDAL/MUNCI./Corp. <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+counterId+''+innerWorkCount+' madalChangeCls" id="mandalInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Mandal</option>';
							clonedInnerTemplate+='</select><br><span class="mandalInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
						
						if(locationLvelId == 6)
							clonedInnerTemplate+='<div class="col-sm-2 panchayatInnerCls'+typeVal+''+counterId+''+innerWorkCount+'">';
						else
							clonedInnerTemplate+='<div class="col-sm-2 panchayatInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
							
							clonedInnerTemplate+='<label>PANCHAYAT <span class="starColor">*</span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.panchayatId"  class="form-control chosen-select m_top10  validateCls  panchayatRefreshCls'+counterId+''+innerWorkCount+'" id="panchayatInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="main" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Panchayat</option>';
							clonedInnerTemplate+='</select><br><span class="panchayatInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span>';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
			clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='<div class="appendInnerBlocks'+typeVal+''+counterId+''+conterInnerVal+'">';
		return clonedInnerTemplate;
}
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

function buildPetitionReferredMemberDetails(result,typeVal){
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
	
	var str='';
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
										str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidate_id="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove" style="cursor:pointer;" title="Remove Candidate" ></i></div>';
										
										str+='<div class="representation-selected display_block" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove ccccc" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff"   title="Remove Candidate"></i></span></div>';
										/*/
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										*/
										if(result[i].petitionMemberVO.imagePath != null && result[i].petitionMemberVO.imagePath.length>0){
											str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="'+result[i].petitionMemberVO.imagePath+'" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										}else{
											str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										}
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Name:</h5>';
												str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
												str+='<h5 class="m_top10 font_weight">Designation</h5>';
												str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
												if(result[i].candidateAddressVO.assemblyName != null && result[i].candidateAddressVO.assemblyName.length>0)
													str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'"> Constituency : '+result[i].candidateAddressVO.assemblyName+'  ,</h5>';
												if(result[i].candidateAddressVO.districtName != null && result[i].candidateAddressVO.districtName.length>0)
													str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">District : '+result[i].candidateAddressVO.districtName+'  ,</h5>';
												if(result[i].candidateAddressVO.stateName != null && result[i].candidateAddressVO.stateName.length>0)
													str+='<h5 class="m_top5" attr_state_id="'+result[i].candidateAddressVO.districtId+'">State : '+result[i].candidateAddressVO.stateName+'  ,</h5>';
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
											//str+='<h5 class="m_top5">'+result[i].candidateAddressVO.districtName+' District.</h5>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight"> Address:</h5>';
												if(result[i].candidateNativeAddressVO != null){
													//str+='<h5 class="m_top5">H No :</h5>';
													if(result[i].candidateNativeAddressVO.panchayatName != null && result[i].candidateNativeAddressVO.panchayatName.length>0)
														str+='<h5 class="m_top10">Panchayat : '+result[i].candidateNativeAddressVO.panchayatName+' </h5>';
													if(result[i].candidateNativeAddressVO.tehsilName != null && result[i].candidateNativeAddressVO.tehsilName.length>0)
														str+='<h5 class="text_bold m_top10"> Mandal/Munci.: '+result[i].candidateNativeAddressVO.tehsilName+' </h5>';
													if(result[i].candidateNativeAddressVO.districtName != null && result[i].candidateNativeAddressVO.districtName.length>0)
														str+='<h5 class="m_top5">District: '+result[i].candidateNativeAddressVO.districtName+' </h5>';
													if(result[i].candidateNativeAddressVO.stateName != null && result[i].candidateNativeAddressVO.stateName.length>0)
														str+='<h5 class="m_top5"> State :'+result[i].candidateNativeAddressVO.stateName+' </h5>';
												}else{
													str+='<h5 class="m_top5">Not Available</h5>';
												}
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
							str+='<div class="bgColorCandidatesView candidateAddedView" class="candidatesAppendDiv'+result[i].referrerCandidateId+'" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
							
								str+='<div class="row">';
									str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidate_id="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove Candidate" ></i></div>';
									
										str+='<div class="representation-selected" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove ccccc" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff"   title="Remove Candidate"></i></span></div>';
										
										/*str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										*/
										if(result[i].petitionMemberVO.imagePath != null && result[i].petitionMemberVO.imagePath.length>0){
											str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="'+result[i].petitionMemberVO.imagePath+'" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										}else{
											str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
												str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
										str+='</div>';
										}
										str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Name:</h5>';
												str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
												str+='<h5 class="m_top10 font_weight">Designation</h5>';
												str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
												if(result[i].candidateAddressVO.assemblyName != null && result[i].candidateAddressVO.assemblyName.length>0)
													str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'"> Constituency : '+result[i].candidateAddressVO.assemblyName+'  ,</h5>';
												if(result[i].candidateAddressVO.districtName != null && result[i].candidateAddressVO.districtName.length>0)
													str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">District : '+result[i].candidateAddressVO.districtName+'  ,</h5>';
												if(result[i].candidateAddressVO.stateName != null && result[i].candidateAddressVO.stateName.length>0)
													str+='<h5 class="m_top5" attr_state_id="'+result[i].candidateAddressVO.districtId+'">State : '+result[i].candidateAddressVO.stateName+'  ,</h5>';
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
											//str+='<h5 class="m_top5">'+result[i].candidateAddressVO.districtName+' District.</h5>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
											str+='<div class="nameAddressCss">';
												str+='<h5 class="font_weight">Address:</h5>';
										if(result[i].candidateNativeAddressVO != null){
													//str+='<h5 class="m_top5">H No :</h5>';
													if(result[i].candidateNativeAddressVO.panchayatName != null && result[i].candidateNativeAddressVO.panchayatName.length>0)
														str+='<h5 class="m_top10"> Panchayat : '+result[i].candidateNativeAddressVO.panchayatName+' Panchayat</h5>';
													if(result[i].candidateNativeAddressVO.tehsilName != null && result[i].candidateNativeAddressVO.tehsilName.length>0)
														str+='<h5 class="text_bold m_top10"> Mandal/Munci. : '+result[i].candidateNativeAddressVO.tehsilName+'  </h5>';
													if(result[i].candidateNativeAddressVO.districtName != null && result[i].candidateNativeAddressVO.districtName.length>0)
														str+='<h5 class="m_top5">District : '+result[i].candidateNativeAddressVO.districtName+' </h5>';
													if(result[i].candidateNativeAddressVO.stateName != null && result[i].candidateNativeAddressVO.stateName.length>0)
														str+='<h5 class="m_top5">State : '+result[i].candidateNativeAddressVO.stateName+' </h5>';
												}else{
													str+='<h5 class="m_top5">Not Available</h5>';
												}
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
				 str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'"  style="position:relative;">';
					str+='<div class="bgColorCandidatesView candidateAddedView" class="candidatesAppendDiv'+result[i].referrerCandidateId+'" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
					
					str+='<div class="row">';
					str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidate_id="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove Candidate"></i></div>';
					str+='<div class="representation-selected" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove ccccc" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff"  title="Remove Candidate"></i></span></div>';
					if(result[i].petitionMemberVO.imagePath != null && result[i].petitionMemberVO.imagePath.length>0){
						str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
							str+='<img src="'+result[i].petitionMemberVO.imagePath+'" class="imageCss"></img>';
							str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
					str+='</div>';
					}else{
						str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
							str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
							str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
					str+='</div>';
					}
					/*str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
							str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
							str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
					str+='</div>';*/
					str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
						str+='<div class="nameAddressCss">';
							str+='<h5 class="font_weight">Name:</h5>';
							str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
							str+='<h5 class="m_top10 font_weight">Designation</h5>';
							str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
							if(result[i].candidateAddressVO.assemblyName != null && result[i].candidateAddressVO.assemblyName.length>0)
							str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'"> Constituency : '+result[i].candidateAddressVO.assemblyName+'  ,</h5>';
						if(result[i].candidateAddressVO.districtName != null && result[i].candidateAddressVO.districtName.length>0)
							str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">District : '+result[i].candidateAddressVO.districtName+'  ,</h5>';
						if(result[i].candidateAddressVO.stateName != null && result[i].candidateAddressVO.stateName.length>0)
							str+='<h5 class="m_top5" attr_state_id="'+result[i].candidateAddressVO.stateId+'">State : '+result[i].candidateAddressVO.stateName+'  ,</h5>';
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
							//str+='<h5 class="m_top5">'+result[i].candidateAddressVO.districtName+' District.</h5>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-3 addRemoveCol'+typeVal+''+result[i].referrerCandidateId+'">';
						str+='<div class="nameAddressCss">';
							str+='<h5 class="font_weight">Address:</h5>';
					if(result[i].candidateNativeAddressVO != null){
							//str+='<h5 class="m_top5">H No :</h5>';
							if(result[i].candidateNativeAddressVO.panchayatName != null && result[i].candidateNativeAddressVO.panchayatName.length>0)
								str+='<h5 class="m_top10">Panchayat : '+result[i].candidateNativeAddressVO.panchayatName+' </h5>';
							if(result[i].candidateNativeAddressVO.tehsilName != null && result[i].candidateNativeAddressVO.tehsilName.length>0)
								str+='<h5 class="text_bold m_top10">Mandal/Munci. : '+result[i].candidateNativeAddressVO.tehsilName+'  </h5>';
							if(result[i].candidateNativeAddressVO.districtName != null && result[i].candidateNativeAddressVO.districtName.length>0)
								str+='<h5 class="m_top5">District : '+result[i].candidateNativeAddressVO.districtName+' </h5>';
							if(result[i].candidateNativeAddressVO.stateName != null && result[i].candidateNativeAddressVO.stateName.length>0)
								str+='<h5 class="m_top5">State : '+result[i].candidateNativeAddressVO.stateName+' </h5>';
						}else{
							str+='<h5 class="m_top5">Not Available</h5>';
						}
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
function getPetitionDetails(){
	//$("#"+result.representationType+"DetailsDivId").html(str);
	$('#representationDate,#endorsmentDate').val('');
   var json = {
       petitionId:petitionId,//	"1872",
       pageType:"editPage"
    };
  $.ajax({              
    type:'POST',    
    url: 'setPmRepresenteeDataToResultView',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  $(".loadingCls").html('');
    if(result !=null){
		globalRepresentType = result.representationType;
		buildPetitionDetails(result);
		
	}
  });
}

function buildPetitionDetails(result){
	var str='';
	if(result.representationType == "SELF"){
		$("#self").prop("checked",true);
		$(".RepresenteeHideShow").hide();
		$(".selfHideShow").show();
	}else{
		$("#Representee").prop("checked",true);
		$(".selfHideShow").hide();
		$(".RepresenteeHideShow").show();
	}
	isOldData = result.isOldData;
	
	if(isOldData == 'Y'){
		$('#endorsmentNo').val(result.endorsmentNo);
		$('#endorsmentDate').val(result.endorsmentDate);
		
		$('#endorsementDivId').show();
		$('#endorsementDateDivId').show();
	}
	
	$('#representationDate').val(result.representationdate);
	$('#representationType').val(result.representationType);
	str+='<input type="hidden" id="existingPetitionId'+result.representationType+'" value="'+result.petitionId+'" name="existingPetitionId"/>';
	coveringDocs = result.coveringLetterPathsList;
	if(result.coveringLetterPathsList !=null && result.coveringLetterPathsList.length>0){
		//$("#viewCoveringLettersDivId").html('<p class="viewDivId pull-right docsViewCls" attr_docs="covering" style="cursor:pointer;margin-right: 30px;margin-top: 10px"><i class="fa fa-file-text" aria-hidden="true"></i> VIEW COVERING LETTER </p>')
	}
	str+='<input type="hidden" id="saveType" value=""  name="saveType"/>';
	if(result.representationType == "REPRESENTEE"){
		for(var i in result.representeeDetailsList){
			str+='<div class="row ">';

				str+='<div class="col-sm-3">';
					str+='<h6>VOTER CARD NO</h6>';
					if(result.representeeDetailsList[i].voterCardNo != null && result.representeeDetailsList[i].voterCardNo !='undefined')
						str+='<input type="text"   name="voterCardNo"  value="'+result.representeeDetailsList[i].voterCardNo+'"  class="form-control m_top10 height45" id="voterId'+result.representationType+'" placeholder="Enter Voter ID">';
					else
						str+='<input type="text"   name="voterCardNo"  value=""  class="form-control m_top10 height45" id="voterId'+result.representationType+'" placeholder="Enter Voter ID">';
				str+='</div>';
				/*str+='<div class="col-sm-1">';
					str+='<label></label>';
					str+='<input type="button" class="btn btn-warning btn-md m_top10" id="getVoterDetailsId" attr_type="'+result.representationType+'" value="Get Details" ></input>';
				str+='</div>';
				*/
				str+='<div class="col-sm-3">';
					str+='<h6>NAME<span class="" style="color:red;">*</span></h6>';
					str+='<input type="text" readOnly="true" name="name" value="'+result.representeeDetailsList[i].name+'" class="form-control m_top10 height45" id="name'+result.representationType+'" placeholder="Enter Name">';
					str+='<span class="ErrCls" id="nameErr'+result.representationType+'"></span>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h6>MOBILE NO<span class="" style="color:red;">*</span></h6>';
					str+='<input type="text" name="mobileNO"  maxlength="10" value="'+result.representeeDetailsList[i].mobileNO+'"  class="form-control m_top10 height45" id="mobileNumber'+result.representationType+'" placeholder="Enter Mobile Number" onkeyUp="checkIsNumber(this.id,this.value);">';	
					str+='<span class="ErrCls" id="mobileNumberErr'+result.representationType+'" ></span>';				
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h6>EMAIL-ID</h6>';
					str+='<input type="text" name="email"    value="'+result.representeeDetailsList[i].email+'" class="form-control m_top10 height45" id="emailId'+result.representationType+'" placeholder="Enter E-mail ID">';	
					str+='<span class="ErrCls" id="emailIdErr'+result.representationType+'"></span>';				
				str+='</div>';
				
			str+='</div>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';	
					str+='<h6>DISTRICT<span class="" style="color:red;">*</span></h6>';
					str+='<select   name="addressVO.districtId" class="form-control chosen-select m_top10" id="district'+result.representationType+'">';
					str+='<option value="0"> Select District </option>';
						for(var dis in globaldistrictList){
							if(globaldistrictList[dis].id == result.representeeDetailsList[i].addressVO.districtId){
								str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
							}else{
								str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
							}
						}
					str+='</select>';
				str+='<span class="ErrCls" id="districtErr'+result.representationType+'"></span>';
				str+='</div>';
				str+='<div class="col-sm-3">';	
					str+='<h6>CONSTITUENCY<span class="" style="color:red;">*</span></h6>';
					str+='<select  name="addressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+result.representationType+'">';
					str+='<option value="0"> Select Constituency </option>';
						for(var c in result.representeeDetailsList[0].addressVO.constituencyList){
							if(result.representeeDetailsList[0].addressVO.constituencyList[c].key == result.representeeDetailsList[i].addressVO.assemblyId){
								str+='<option value="'+result.representeeDetailsList[0].addressVO.constituencyList[c].key+'" selected>'+result.representeeDetailsList[0].addressVO.constituencyList[c].value+'</option>';
							}else{
								str+='<option value="'+result.representeeDetailsList[0].addressVO.constituencyList[c].key+'">'+result.representeeDetailsList[0].addressVO.constituencyList[c].value+'</option>';
							}
						}
						
					str+='</select>';
					
				str+='</div>';
				str+='<div class="col-sm-3">';	
					str+='<h6>MANDAL/MUNCI<span class="" style="color:red;">*</span></h6>';
					str+='<select   name="addressVO.tehsilId"   class="form-control chosen-select m_top10" id="mandal'+result.representationType+'">';
					str+='<option value="0"> Select Mandal/Munci./Corp. </option>';
						for(var c in result.representeeDetailsList[0].addressVO.mandalsList){
							if(result.representeeDetailsList[0].addressVO.mandalsList[c].key == result.representeeDetailsList[i].addressVO.tehsilId){
								str+='<option value="'+result.representeeDetailsList[0].addressVO.mandalsList[c].key+'" selected>'+result.representeeDetailsList[0].addressVO.mandalsList[c].value+'</option>';
							}else{
								str+='<option value="'+result.representeeDetailsList[0].addressVO.mandalsList[c].key+'">'+result.representeeDetailsList[0].addressVO.mandalsList[c].value+'</option>';
							}
						}
					str+='</select>';
				str+='<span class="ErrCls"  id="constituencyErr'+result.representationType+'"></span>';
				str+='</div>';
			
				str+='<div class="col-sm-3">';	
					str+='<h6>PANCHAYAT</h6>';
					str+='<select   name="addressVO.panchayatId"  class="form-control chosen-select m_top10" id="panchayat'+result.representationType+'">';
					str+='<option value="0"> Select Panchayat </option>';
						for(var c in result.representeeDetailsList[0].addressVO.panchaytsList){
							if(result.representeeDetailsList[0].addressVO.panchaytsList[c].key == result.representeeDetailsList[0].addressVO.panchayatId){
								str+='<option value="'+result.representeeDetailsList[0].addressVO.panchaytsList[c].key+'" selected>'+result.representeeDetailsList[0].addressVO.panchaytsList[c].value+'</option>';
							}else{
								str+='<option value="'+result.representeeDetailsList[0].addressVO.panchaytsList[c].key+'">'+result.representeeDetailsList[0].addressVO.panchaytsList[c].value+'</option>';
							}
						}
					str+='</select>';
					str+='<span class="ErrCls" id="panchayatErr'+result.representationType+'"></span>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';	
				str+='<h6>REPRESENTEE DESIGNATION<span class="" style="color:red;">*</span></h6>';
				str+='<select   name="representeeDesignationId"  class="form-control chosen-select m_top10" id="designation'+result.representationType+'">';
				str+='<option value="0"> Select Designation </option>';
				if(globalDesignationList != null && globalDesignationList.length>0){
					for(var dig in globalDesignationList){
						if(globalDesignationList[dig].key == result.representeeDetailsList[i].designationId){
							str+='<option value="'+globalDesignationList[dig].key+'" selected>'+globalDesignationList[dig].value+'</option>';
						}else{
							str+='<option value="'+globalDesignationList[dig].key+'">'+globalDesignationList[dig].value+'</option>';
						}
					}
				}					
				str+='</select>';
				str+='<span class="ErrCls"  id="designationErr'+result.representationType+'"></span>';
				str+='<input type="hidden" id="repTdpCadreId'+result.representationType+'" value="'+result.representeeDetailsList[i].tdpCadreId+'" name="tdpCadreId"/>';
				str+='<input type="hidden" id="repImagePathId'+result.representationType+'" value="'+result.representeeDetailsList[i].candidatePath+'" name="repImagePath"/>';
			str+='</div>';
			str+='</div>';
		}
		
	}
	if(result.representationType == "SELF"){
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<h4>REPRESENTEE DETAILS</h4>';
			str+='<div class="candidateRepresenteeDetails'+result.representationType+'DivId">';
			for(var i in result.representeeDetailsList){
				str+='<div class="bgColorCandidatesView" attr_type='+result.representationType+' attr_candidateId='+result.representeeDetailsList[i].refCandidateId+' id="candidate'+result.representationType+''+result.representeeDetailsList[i].refCandidateId+'">';
				
				if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT"){
					//str+='<input id="existinfRefDetails'+i+'" class="refCandidatesCls" name="referList['+i+'].refCandidateId" value="'+result.representeeDetailsList[i].refCandidateId+'" type="hidden">';
				}else{							
					str+='<input id="existinfRefDetails'+i+''+j+'" class="refCandidatesCls" name="refCandidateId" value="'+result.representeeDetailsList[i].refCandidateId+'" type="hidden">';
							if(result.representeeDetailsList[i].fileNamesList !=null && result.representeeDetailsList[i].fileNamesList.length>0){
								for(var j in result.representeeDetailsList[i].fileNamesList){
									str+='<input type="hidden" id="existingrefDocument'+i+''+j+'" name="referList['+i+'].fileNamesList['+j+'].value" value="'+result.representeeDetailsList[i].fileNamesList[j].value+'">';	
								}
							}
							
				}
						
					str+='<div class="row">';
						str+='<div class="col-sm-2">';
							if(result.representeeDetailsList[i].candidatePath != null && result.representeeDetailsList[i].candidatePath.length>0)
								str+='<img src="'+result.representeeDetailsList[i].candidatePath+'" class="imageCss"></img>';
							else 
								str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
							if(result.representeeDetailsList[i].partyName != null && result.representeeDetailsList[i].partyName.length>0)
								str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/'+result.representeeDetailsList[i].partyName+'.PNG" class="smallerImg"></img></span>';
							else
								str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
						str+='</div>';
						
						str+='<div class="col-sm-2">';
									str+='<div class="nameAddressCss">';
										str+='<h5 class="font_weight">Name:</h5>';
										str+='<h5 class="m_top5">'+result.representeeDetailsList[i].name+'</h5>';
										str+='<h5 class="m_top10 font_weight">Designation</h5>';
										str+='<h5 class="text_bold m_top10">'+result.representeeDetailsList[i].designation+'</h5>';
										if(result.representeeDetailsList[i].candidateAddressVO.assemblyName != null && result.representeeDetailsList[i].candidateAddressVO.assemblyName.length>0)
											str+='<h5 class="m_top5">'+result.representeeDetailsList[i].candidateAddressVO.assemblyName+' Constituency,</h5>';
										if(result.representeeDetailsList[i].candidateAddressVO.districtName != null && result.representeeDetailsList[i].candidateAddressVO.districtName.length>0)
											str+='<h5 class="m_top5">District :'+result.representeeDetailsList[i].candidateAddressVO.districtName+' , ';
										if(result.representeeDetailsList[i].candidateAddressVO.stateName != null && result.representeeDetailsList[i].candidateAddressVO.stateName.length>0)
											str+='<h5> State :'+result.representeeDetailsList[i].candidateAddressVO.stateName+'</h5>';
									str+='</div>';
							str+='</div>';
						
							str+='<div class="col-sm-2">';
								str+='<div class="nameAddressCss">';
									str+='<h5 class="font_weight">Party:</h5>';
									str+='<h5 class="m_top5">'+result.representeeDetailsList[i].partyName+'</h5>';
									str+='<h5 class="m_top10 font_weight">Contact Details</h5>';
									str+='<h5 class="text_bold m_top10">Email-id: '+result.representeeDetailsList[i].email+'</h5>';
									str+='<h5 class="m_top5">Contact No : '+result.representeeDetailsList[i].mobileNO+'</h5>';
								str+='</div>';
							str+='</div>';
						
							 str+='<div class="col-sm-2">';
								str+='<div class="nameAddressCss">';
									str+='<h5 class="font_weight">Address:</h5>';
							if(result.representeeDetailsList[i].candidateNativeAddressVO != null && result.representeeDetailsList[i].candidateNativeAddressVO.stateName != null && result.representeeDetailsList[i].candidateNativeAddressVO.stateName.length>0){
								if(result.representeeDetailsList[i].candidateNativeAddressVO.panchayatName != null && result.representeeDetailsList[i].candidateNativeAddressVO.panchayatName.length>0)
										str+='<h5 class="m_top5">Panchayat  : '+result.representeeDetailsList[i].candidateNativeAddressVO.panchayatName+' </h5>';
								if(result.representeeDetailsList[i].candidateNativeAddressVO.tehsilName != null && result.representeeDetailsList[i].candidateNativeAddressVO.tehsilName.length>0)
										str+='<h5 class="m_top5">Mandal/Munci.  : '+result.representeeDetailsList[i].candidateNativeAddressVO.tehsilName+' </h5>';
								if(result.representeeDetailsList[i].candidateNativeAddressVO.assemblyName != null && result.representeeDetailsList[i].candidateNativeAddressVO.assemblyName.length>0)
										str+='<h5 class="m_top5">Constituency  : '+result.representeeDetailsList[i].candidateNativeAddressVO.assemblyName+' </h5>';
								if(result.representeeDetailsList[i].candidateNativeAddressVO.districtName != null && result.representeeDetailsList[i].candidateNativeAddressVO.districtName.length>0)
										str+='<h5 class="m_top5"> District  : '+result.representeeDetailsList[i].candidateNativeAddressVO.districtName+' ,</h5> ';
									
								if(result.representeeDetailsList[i].candidateNativeAddressVO.stateName != null && result.representeeDetailsList[i].candidateNativeAddressVO.stateName.length>0)
										str+='<h5 class="m_top5"> State : '+result.representeeDetailsList[i].candidateNativeAddressVO.stateName+'</h5>';
							}else{
										str+='<h5 class="m_top5">Not Available</h5>';
								}		
									
								str+='</div>';
							str+='</div>'; 
							str+='<div class="col-sm-4">';
								//str+='<label>REFERAL LETTER</label>';
								
								//str+='<input type="file"    name="[]"  attr_image_tyep="refImage" id="editRepresenteeFileUpload'+result.representeeDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
								
								str+='<input type="file"    attr_name="referList['+i+']"  name="[]"  attr_image_tyep="refImage" id="editRepresenteeFileUpload'+result.representeeDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
								
							str+='</div>';
						str+='</div>';
						
						var isFileAvailable=false;
						for(var j in result.representeeDetailsList[i].fileNamesList){
							var scanCopySpl = result.representeeDetailsList[i].fileNamesList[j].value.split("."); 
							var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
							if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png" ||scanCopyExt =="pdf"){
								isFileAvailable= true;
							}
						}
						
						if(isFileAvailable){
							str+='<div class="alreadyUploadFilesCss">';
							if(result.representationType == "self" || result.representationType == "SELF")
								str+='<h4> SELF REFERRAL DOCUMENTS BY : <b> '+result.representeeDetailsList[i].name+' </b></h4>';
							else
								str+='<h4> REFERRAL DOCUMENTS BY : <b> '+result.representeeDetailsList[i].name+' </b></h4>';
							
								str+='<div class="row">';
									for(var j in result.representeeDetailsList[i].fileNamesList){
										var scanCopySpl = result.representeeDetailsList[i].fileNamesList[j].value.split("."); 
										var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
										if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png" || scanCopyExt =="pdf"){
												str+='<div class="col-sm-2" id="refDocument'+i+''+j+'">';
												
													if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
														str+='<div class="viewImageCss m_top20">';
													}else{
														str+='<div class="viewImageCss">';
													}
													str+='<div class="pull-right removeFileCls" attr_id="refDocument'+i+''+j+'" style="margin-right: 15px;cursor:pointer"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove refer document"></i></div>';
													if(scanCopyExt =="pdf"){
														str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
															str+='<div class="mouse-over">Expand</div>';
															str+='<object data="'+result.representeeDetailsList[i].fileNamesList[j].value+'" type="application/pdf" width="100%" height="100px;"></object>';
														str+='</a>';
														str+='<div id="inline'+i+''+j+'" style="width:100%;display: none;">';
															str+='<object data="'+result.representeeDetailsList[i].fileNamesList[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
														str+='</div>';
														
													}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
														str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
															str+='<img src="'+result.representeeDetailsList[i].fileNamesList[j].value+'"  width="100%" height="100px;"></img>';
														str+='</a>';
														str+='<div id="inline'+i+''+j+'" style="width:100%;display: none;">';
															str+='<img src="'+result.representeeDetailsList[i].fileNamesList[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
														str+='</div>';
													}else{
														str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+result.representeeDetailsList[i].fileNamesList[j].value+'\')">Here</a> To View Document</b>';
													}
										
											str+='</div>';
										}
									str+='</div>';
							
								}
							str+='</div>';
						}
						str+='</div>';
					str+='</div>';
					
					str+='</div>';
				str+='</div>';
			}
			str+='</div>';
		str+='</div>';
	str+='</div>';	
	}
	
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<h4>REERRAL DETAILS</h4>';
				str+='<div class="candidateDetails'+result.representationType+'DivId">';
		for(var i in result.referDetailsList){
			alreadyCandidateId.push(result.referDetailsList[i].refCandidateId);
				refCandCount=refCandCount+1;
					str+='<div class="bgColorCandidatesView" attr_type='+result.representationType+' attr_candidateId='+result.referDetailsList[i].refCandidateId+' id="candidate'+result.representationType+''+result.referDetailsList[i].refCandidateId+'">';
					
					str+='<div id="candidatesAppendDiv'+result.referDetailsList[i].refCandidateId+'" style="position:relative;">';
						str+='<div class="row">';
						if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT"){
							str+='<input id="existinfRefDetails'+i+'" class="refCandidatesCls" name="referList['+i+'].refCandidateId" value="'+result.referDetailsList[i].refCandidateId+'" type="hidden">';
							
							if(result.referDetailsList[i].fileNamesList !=null && result.referDetailsList[i].fileNamesList.length>0){
								for(var j in result.referDetailsList[i].fileNamesList){
									str+='<input type="hidden" id="existingrefDocument'+i+''+j+'" name="referList['+i+'].fileNamesList['+j+'].value" value="'+result.referDetailsList[i].fileNamesList[j].value+'">';	
								}
							}
						
						}else{							
							//str+='<input id="existinfRefDetails'+i+''+j+'" class="refCandidatesCls" name="refCandidateId" value="'+result.referDetailsList[i].refCandidateId+'" type="hidden">';
							
							str+='<input id="existinfSelfRefDetails'+i+'" class="refCandidatesCls" name="selfReferList['+i+'].refCandidateId" value="'+result.referDetailsList[i].refCandidateId+'" type="hidden">';
							
							if(result.referDetailsList[i].fileNamesList !=null && result.referDetailsList[i].fileNamesList.length>0){
								for(var j in result.referDetailsList[i].fileNamesList){
									str+='<input type="hidden" id="existingDocumentselfReferList'+i+''+j+'" name="selfReferList['+i+'].fileNamesList['+j+'].value" value="'+result.referDetailsList[i].fileNamesList[j].value+'">';	
								}
							}
						}
						
							//if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT"){
								str+='<div class="pull-right showRemoveIcon" attr_type='+result.representationType+' attr_candidate_id="'+result.referDetailsList[i].refCandidateId+'"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove Candidate"></i></div>';
							//}
								str+='<div class="col-sm-2">';
									if(result.referDetailsList[i].candidatePath != null && result.referDetailsList[i].candidatePath.length>0)
										str+='<img src="'+result.referDetailsList[i].candidatePath+'" class="imageCss"></img>';
									else 
										str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
									if(result.referDetailsList[i].partyName != null && result.referDetailsList[i].partyName.length>0)
										str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/'+result.referDetailsList[i].partyName+'.PNG" class="smallerImg"></img></span>';
									else
										str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
								str+='</div>';
							
								str+='<div class="col-sm-2">';
									str+='<div class="nameAddressCss">';
										str+='<h5 class="font_weight">Name:</h5>';
										str+='<h5 class="m_top5">'+result.referDetailsList[i].name+'</h5>';
										str+='<h5 class="m_top10 font_weight">Designation</h5>';
										str+='<h5 class="text_bold m_top10">'+result.referDetailsList[i].designation+'</h5>';
										if(result.referDetailsList[i].candidateAddressVO.assemblyName != null && result.referDetailsList[i].candidateAddressVO.assemblyName.length>0)
											str+='<h5 class="m_top5">'+result.referDetailsList[i].candidateAddressVO.assemblyName+' Constituency,</h5>';
										if(result.referDetailsList[i].candidateAddressVO.districtName != null && result.referDetailsList[i].candidateAddressVO.districtName.length>0)
											str+='<h5 class="m_top5">District :'+result.referDetailsList[i].candidateAddressVO.districtName+' , ';
										if(result.referDetailsList[i].candidateAddressVO.stateName != null && result.referDetailsList[i].candidateAddressVO.stateName.length>0)
											str+='<h5> State :'+result.referDetailsList[i].candidateAddressVO.stateName+'</h5>';
									str+='</div>';
							str+='</div>';
						
							str+='<div class="col-sm-2">';
								str+='<div class="nameAddressCss">';
									str+='<h5 class="font_weight">Party:</h5>';
									str+='<h5 class="m_top5">'+result.referDetailsList[i].partyName+'</h5>';
									str+='<h5 class="m_top10 font_weight">Contact Details</h5>';
									str+='<h5 class="text_bold m_top10">Email-id: '+result.referDetailsList[i].email+'</h5>';
									str+='<h5 class="m_top5">Contact No : '+result.referDetailsList[i].mobileNO+'</h5>';
								str+='</div>';
							str+='</div>';
						
							str+='<div class="col-sm-2">';
								str+='<div class="nameAddressCss">';
									str+='<h5 class="font_weight">Address:</h5>';
							if(result.referDetailsList[i].candidateNativeAddressVO != null && result.referDetailsList[i].candidateNativeAddressVO.stateName != null && result.referDetailsList[i].candidateNativeAddressVO.stateName.length>0){
								if(result.referDetailsList[i].candidateNativeAddressVO.panchayatName != null && result.referDetailsList[i].candidateNativeAddressVO.panchayatName.length>0)
										str+='<h5 class="m_top5">Panchayat  : '+result.referDetailsList[i].candidateNativeAddressVO.panchayatName+' </h5>';
								if(result.referDetailsList[i].candidateNativeAddressVO.tehsilName != null && result.referDetailsList[i].candidateNativeAddressVO.tehsilName.length>0)
										str+='<h5 class="m_top5">Mandal/Munci.  : '+result.referDetailsList[i].candidateNativeAddressVO.tehsilName+' </h5>';
								if(result.referDetailsList[i].candidateNativeAddressVO.assemblyName != null && result.referDetailsList[i].candidateNativeAddressVO.assemblyName.length>0)
										str+='<h5 class="m_top5">Constituency  : '+result.referDetailsList[i].candidateNativeAddressVO.assemblyName+' </h5>';
								if(result.referDetailsList[i].candidateNativeAddressVO.districtName != null && result.referDetailsList[i].candidateNativeAddressVO.districtName.length>0)
										str+='<h5 class="m_top5"> District  : '+result.referDetailsList[i].candidateNativeAddressVO.districtName+' ,</h5> ';
									
								if(result.referDetailsList[i].candidateNativeAddressVO.stateName != null && result.referDetailsList[i].candidateNativeAddressVO.stateName.length>0)
										str+='<h5 class="m_top5"> State : '+result.referDetailsList[i].candidateNativeAddressVO.stateName+'</h5>';
							}else{
										str+='<h5 class="m_top5">Not Available</h5>';
								}		
									
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-4">';
								//str+='<label>REFERAL LETTER</label>';
								
								if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT"){
									str+='<input type="file"  attr_name="referList['+i+']" name="[]"  attr_image_tyep="refImage" id="editFileUpload'+result.referDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
								}else{
									str+='<input type="file"  attr_name="selfReferList['+i+']" name="[]"  attr_image_tyep="refImage" id="editFileUpload'+result.referDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
								}

								
							str+='</div>';
						str+='</div>';
					
					var isSelfFileAvailable=false;
						for(var j in result.referDetailsList[i].fileNamesList){
							var scanCopySpl = result.referDetailsList[i].fileNamesList[j].value.split("."); 
							var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
							if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png" ||scanCopyExt =="pdf"){
								isSelfFileAvailable= true;
							}
						}
						
						if(isSelfFileAvailable){
							str+='<div class="alreadyUploadFilesCss">';
							//if(result.representationType == "self" || result.representationType == "SELF")
								//str+='<h4> REFERRAL DOCUMENTS BY : <b> '+result.referDetailsList[i].name+' </b></h4>';
							//else
								str+='<h4> REFERRAL DOCUMENTS BY : <b> '+result.referDetailsList[i].name+' </b></h4>';
							
								str+='<div class="row">';
									for(var j in result.referDetailsList[i].fileNamesList){
										var scanCopySpl = result.referDetailsList[i].fileNamesList[j].value.split("."); 
										var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
										if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png" || scanCopyExt =="pdf"){
											if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT")
												str+='<div class="col-sm-2" id="refDocument'+i+''+j+'">';
											else
												str+='<div class="col-sm-2" id="selfReferList'+i+''+j+'">';
												
													if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
														str+='<div class="viewImageCss m_top20">';
													}else{
														str+='<div class="viewImageCss">';
													}
													if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT")
														str+='<div class="pull-right removeFileCls" attr_id="refDocument'+i+''+j+'" style="margin-right: 15px;cursor:pointer"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove refer document"></i></div>';
													else
														str+='<div class="pull-right removeFileCls" attr_id="selfReferList'+i+''+j+'" style="margin-right: 15px;cursor:pointer"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove refer document"></i></div>';
													
													if(scanCopyExt =="pdf"){
														str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
															str+='<div class="mouse-over">Expand</div>';
															str+='<object data="'+result.referDetailsList[i].fileNamesList[j].value+'" type="application/pdf" width="100%" height="100px;"></object>';
														str+='</a>';
														str+='<div id="inline'+i+''+j+'" style="width:100%;display: none;">';
															str+='<object data="'+result.referDetailsList[i].fileNamesList[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
														str+='</div>';
														
													}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
														str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
															str+='<img src="'+result.referDetailsList[i].fileNamesList[j].value+'"  width="100%" height="100px;"></img>';
														str+='</a>';
														str+='<div id="inline'+i+''+j+'" style="width:100%;display: none;">';
															str+='<img src="'+result.referDetailsList[i].fileNamesList[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
														str+='</div>';
													}else{
														str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+result.referDetailsList[i].fileNamesList[j].value+'\')">Here</a> To View Document</b>';
													}
										
											str+='</div>';
										}
									str+='</div>';
							
								}
							str+='</div>';
						}
						str+='</div>';
					str+='</div>';
					str+='</div>';
		}
		
		str+='</div>';
			str+='</div>';
		str+='</div>';
		
			/*str+='<div class="col-sm-12">';
				str+='<div class="candidateDetails'+result.representationType+'DivId"></div>';
			str+='</div>';*/
	str+='</div>';
	
		if(result.representationType == "SELF"){
			//if(!$(".candidateDetails"+result.representationType+"DivId").find(".bgColorCandidatesView")){
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<button type="button"  class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+result.representationType+'">ADD REFERAL</button>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		//	}
			
		}else if(result.representationType == "REPRESENTEE"){
			str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+result.representationType+'">ADD REFERAL</button>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		}
	

	//candidate Search End		
		maxWorksCount =result.noOfWorks;
		maxCost =result.estimateCost;
		str+='<div class="row m_top10">';
				str+='<div class="col-sm-8">';
					str+='<label>PETITION DESCRIPTION <span class="starColor">*</span></label>';
					str+='<input type="text"  name="worksList[0].workName"  value="'+result.grievanceDescription+'" class="form-control m_top5 height45" id="workName'+result.representationType+'" placeholder="Enter Name"><br><span id="completeWorkNameId'+result.representationType+'"></span>';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>NO OF WORKS <span class="starColor">*</span></label>';
						str+='<input   name="worksList[0].noOfWorks" value="'+result.noOfWorks+'"  type="text" class="form-control m_top5 height45" id="noofWork'+result.representationType+'" placeholder="Enter No Of Work"  onkeyUp="rebuildWorkDetails(this.id,this.value,\'total work \',\''+result.representationType+'\');"><br><span id="noOfWorksId'+result.representationType+'"></span>';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>WORKS IN COST (in Lakhs) </label>';
					str+='<input type="text"  name="worksList[0].estimateCost" readOnly="true" value="'+result.estimateCost+'"  class="form-control m_top5 height45" id="workCost'+result.representationType+'" placeholder="Cost auto calculates "  onkeyUp="checkIsNumber(this.id,this.value);"><br><span id="workCostId'+result.representationType+'"></span>';
				str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
					str+='<h4 class="panel-title f_18"> UPLOAD PETITION DOCUMENTS </h4>';
					if(result.fileList !=null && result.fileList.length >0){
						str+='<input type="file"  attr_file_existing_size="'+result.fileList.length+'" attr_name="worksList[0]" name="[]"  attr_image_tyep="projImage" id="editprojectDocUpload'+result.representationType+'" multiple="multiple" class=""/>';
					}else{
						str+='<input type="file"  attr_file_existing_size="0" attr_name="worksList[0]" name="[]"  attr_image_tyep="projImage" id="editprojectDocUpload'+result.representationType+'" multiple="multiple" class=""/>';
					}

			str+='</div>';
		str+='</div>';
		
		var isWorkFileAvailable=false;
			for(var i in result.fileList){
				var scanCopySpl = result.fileList[i].value.split("."); 
				var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase());  
				if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png" ||scanCopyExt =="pdf"){
					isWorkFileAvailable= true;
				}
			}
			
			if(isWorkFileAvailable){
				str+='<div class="alreadyUploadFilesCss">';
					str+='<h4> PETITION DOCUMENTS : </h4>';
					str+='<div class="row">';
						for(var i in result.fileList){
							
							var scanCopySpl = result.fileList[i].value.split("."); 
							var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
							
								str+='<div class="col-sm-2" id="workDocument'+i+'" >';
								str+='<input id="existingworkDocument'+i+'" name="fileNamesList['+i+'].value" value="'+result.fileList[i].value+'" attr_image_tyep="projImage" type="hidden">';
								if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png" || scanCopyExt =="pdf"){
									
									if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
										str+='<div class="viewImageCss m_top20">';
									}else{
										str+='<div class="viewImageCss m_top20">';
									}
									str+='<div class="pull-right removeFileCls" attr_id="workDocument'+i+'" style="margin-right: 15px;cursor:pointer"><i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove work document"></i></div>';
									if(scanCopyExt =="pdf"){
										str+='<a class="fancyboxView" href="#inlineMain'+i+''+j+'">';
											str+='<div class="mouse-over">Expand</div>';
											
											str+='<object data="'+result.fileList[i].value+'" type="application/pdf" width="100%" height="100px;"></object>';
										str+='</a>';

										str+='<div id="inlineMain'+i+''+j+'" style="width:100%;display: none;">';
											str+='<object data="'+result.fileList[i].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
										str+='</div>';
										
									}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
										str+='<a class="fancyboxView" href="#inlineMain'+i+''+j+'">';
											str+='<img src="'+result.fileList[i].value+'"  width="100%" height="100px;"></img>';
										str+='</a>';
										str+='<div id="inlineMain'+i+''+j+'" style="width:100%;display: none;">';
											str+='<img src="'+result.fileList[i].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
										str+='</div>';
									}else{
										str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+result.fileList[i].value+'\')">Here</a> To View Document</b>';
									}
								}				
							str+='</div>';
						str+='</div>';
				
					}
				str+='</div>';
			str+='</div>';
		}
	
	//work Details
	str+='<div class="row" id="workDetailsDiv">';
	for(var i in result.statusList){
		globalStatusArr=result.statusList;
	}
	var addWorkTypeCountMain=0;
	var innerWorkTypeCount=0;
	
		for(var i in result.subWorksList){
			str+='<div class="col-sm-12">';
			str+='<div class="panel-group" id="accordionEdit'+i+'">';
				str+='<div class="panel panel-default panel-blue">';
					str+='<div class="panel-heading" id="headingEdit'+i+'" style="padding:10px;">';
						if(i==0){
							str+='<a role="button" class="panelCollapseIcon"  data-toggle="collapse" data-parent="#accordionEdit'+i+'" href="#collapseEdit'+i+'" aria-expanded="true" aria-controls="collapseEdit'+i+'">';
						}else{
							str+='<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordionEdit'+i+'" href="#collapseEdit'+i+'" aria-expanded="true" aria-controls="collapseEdit'+i+'">';
						}
						
						if(result.subWorksList[i].endorsmentNo !=null && result.subWorksList[i].endorsmentNo !=0){
							str+='<h4 class="panel-title text-capital">Endorsed - '+result.subWorksList[i].endorsmentNo+'</h4>';
						}else{
							str+='<h4 class="panel-title text-capital">Pending Endorsed</h4>';
						}
						str+='</a>';
					 str+='</h4>';
					str+='</div>';
					if(i==0){
						str+='<div id="collapseEdit'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingEdit'+i+'">';
					}else{
						str+='<div id="collapseEdit'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEdit'+i+'">';
					}
					  str+='<div class="panel-body" style="background-color: #F7F7F7;">';
								for(var k in result.subWorksList[i].subWorksList){
									var departmentWiseSubWorks = result.subWorksList[i].subWorksList;
									//if(globalWorkTypeCount >0)
										//globalWorkTypeCount = globalWorkTypeCount-1;	
									str+='<div class="m_top20 mainWorkDivCls " id="mainWorkDivId'+i+''+globalWorkTypeCount+'">';
									if(k !=0){
										str+='<div class="pull-right removeWorkCls m_top20"  id="manageInnerWorks'+globalWorkTypeCount+'" attr_inner_works_Count="'+departmentWiseSubWorks[k].length+'"  attr_id="mainWorkDivId'+k+''+globalWorkTypeCount+'" attr_type="self'+result.representationType+'">';
										str+='<i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove work(s)"></i>';
									}else{
										str+='<div class="pull-right m_top20"  id="manageInnerWorks'+globalWorkTypeCount+'" attr_inner_works_Count="'+departmentWiseSubWorks[k].subWorksList.length+'"  attr_id="mainWorkDivId'+k+''+globalWorkTypeCount+'" attr_type="self'+result.representationType+'">';
									}
										str+='</div>';
										
										str+='<div style="border:3px solid #dddddd;padding:10px;background-color:#fff;">';
											str+='<div>';
												str+='<h3 class="panel-title f_22"></h3>';
													
												str+='<div class="row m_top10" >';
													str+='<div class="col-sm-3">';
															str+='<label>DEPARTMENT : <span class="starColor">*</span></label>';
															str+='<select  name="worksList['+k+'].subWorksList['+globalWorkTypeCount+'].deptId"  class="form-control chosen-select m_top10 validateCls OnchangeDeptCls"  id="WorkTypeWiseDepartmentId'+result.representationType+''+k+''+globalWorkTypeCount+'" attr_main_count="'+k+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox" onChange=getPetitionSubjectList(this.value,"subjectId'+result.representationType+''+k+'","'+globalWorkTypeCount+'","change")>';
															str+='<option value="0"> Select Department </option>';
																for(var d in globalDepartmentsList){
																	if(globalDepartmentsList[d].key == departmentWiseSubWorks[k].deptId){
																		str+='<option value='+globalDepartmentsList[d].key+' selected>'+globalDepartmentsList[d].value+'</option>';
																	}else{
																		str+='<option value='+globalDepartmentsList[d].key+'>'+globalDepartmentsList[d].value+'</option>';
																	}
																}
															str+='</select><br><span class="WorkTypeWiseDepartmentId'+result.representationType+''+k+''+globalWorkTypeCount+'"></span>';
														str+='</div>';
													
													str+='<div class="col-sm-3">';
														str+='<label>SUBJECT <span class="starColor">*</span></label>';
														str+='<select  attr_load_type="onload" name="worksList['+k+'].subWorksList['+globalWorkTypeCount+'].subjectId"  class="form-control chosen-select m_top10 validateCls subjctOnchangeCls"  id="subjectId'+result.representationType+''+k+''+globalWorkTypeCount+'" onChange=getPetitionSubSubjectList(this.value,"subSubjectId'+result.representationType+''+k+'","'+globalWorkTypeCount+'","change") attr_main_count="'+k+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox">';
														str+='<option value="0"> Select Subject </option>';
														str+='<option value='+departmentWiseSubWorks[k].subWorksList[0].subjectId+' selected>'+departmentWiseSubWorks[k].subWorksList[0].subject+'</option>';
															/*	for(var sd in departmentWiseSubWorks[k].subWorksList[0].subjectsList){
																	if(departmentWiseSubWorks[k].subWorksList[0].subjectsList[sd].key == departmentWiseSubWorks[k].subjectId){
																		str+='<option value='+departmentWiseSubWorks[k].subWorksList[0].subjectsList[sd].key+' selected>'+departmentWiseSubWorks[k].subWorksList[0].subjectsList[sd].value+'</option>';
																	}else{
																		str+='<option value='+departmentWiseSubWorks[k].subWorksList[0].subjectsList[sd].key+'>'+departmentWiseSubWorks[k].subWorksList[0].subjectsList[sd].value+'</option>';
																	}
																}
																	
																*/
														str+='</select><br><span class="subjectId'+result.representationType+''+k+''+globalWorkTypeCount+'"></span>';
													str+='</div>';
													
													str+='<div class="col-sm-3">';
														str+='<label>SUB-SUBJECT <span class="starColor">*</span><span class="subSubjectId'+result.representationType+''+k+''+globalWorkTypeCount+'"></span></label>';
														str+='<select   attr_load_type="onload" name="worksList['+k+'].subWorksList['+globalWorkTypeCount+'].subSubjectId"  class="form-control chosen-select m_top10 validateCls subSubjectReFreshCls'+k+' subSubjctOnchangeCls"  id="subSubjectId'+result.representationType+''+k+''+globalWorkTypeCount+'" attr_main_count="'+k+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox">';
														str+='<option value="0"> Select Sub Subject </option>';
														str+='<option value='+departmentWiseSubWorks[k].subWorksList[0].subSubjectId+' selected>'+departmentWiseSubWorks[k].subWorksList[0].subSubject+'</option>';
														/*	for(var sd in departmentWiseSubWorks[k].subWorksList[0].subSubjectsList){
																	if(departmentWiseSubWorks[k].subWorksList[0].subSubjectsList[sd].key == departmentWiseSubWorks[k].subSubjectId){
																		str+='<option value='+departmentWiseSubWorks[k].subWorksList[0].subSubjectsList[sd].key+' selected>'+departmentWiseSubWorks[k].subWorksList[0].subSubjectsList[sd].value+'</option>';
																	}else{
																		str+='<option value='+departmentWiseSubWorks[k].subWorksList[0].subSubjectsList[sd].key+'>'+departmentWiseSubWorks[k].subWorksList[0].subSubjectsList[sd].value+'</option>';
																	}
																}
																*/
														str+='</select>';
														str+='</div>';
														/*if(departmentWiseSubWorks.length != null && departmentWiseSubWorks.length <=1){
															alert("edit if")
														}else {
														str+='<div class="col-sm-3">';
														str+='<h5 class="pull-right">';
															str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT ALL</span> <input type="checkbox" id="inlineCheckbox1" value="" class="workStatusSelectedAllCls" style="margin-top: 2px;" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
															str+='</label>';
															str+='</h5>';
													str+='</div>';
												 str+='</div>';
												}*/
		
												//add work individual append
												
												for(var j in departmentWiseSubWorks[k].subWorksList){
													globalInnerWorksCount = parseInt(globalInnerWorksCount)+1;
														
													str+='<div class="row">';
													
													str+='<div class="m_top10" id="workDivId'+k+''+j+'">';
															str+='<div class="col-sm-12">';
															if(j !=0){
																	str+='<h5 class="pull-right">';
																	
																	/* str+='<span style="margin-right: 5px;">';
																		str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																			//str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT</span>'; 
																			if(result.subWorksList[i].endorsmentNo !=null && result.subWorksList[i].endorsmentNo !=0){
																				str+='<input type="checkbox" id="" value="'+departmentWiseSubWorks[k].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: 2px;" attr_department_id="'+departmentWiseSubWorks[k].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
																			}else{
																				str+='<input type="checkbox" id="" value="'+departmentWiseSubWorks[k].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: -14px;margin-left: 56px;" attr_department_id="'+departmentWiseSubWorks[k].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
																			}	
																			
																		str+='</label>'; */
																	str+='</span>';
																	str+='<span class="removeInnerWorkCls" id="manageInnerWorks'+globalWorkTypeCount+'" attr_id="workDivId'+k+''+j+'" attr_type="self" attr_candidateid="1" style="background-color:#ccc;padding:4px;top:2px;position:relative;">';
																	str+='<i class="glyphicon glyphicon-remove"  style="cursor:pointer;" title="Remove work"></i></span>';
																	str+='</h5>';
																}else{
																	str+='<h5 class="pull-right">';
																	str+='<span  attr_id="workDivId'+k+''+j+'" attr_type="self" attr_candidateid="1"></span>';
																	/*str+='<span>';
																		str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																			str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT</span>'; 
																			if(result.subWorksList[i].endorsmentNo !=null && result.subWorksList[i].endorsmentNo !=0){
																				str+='<input type="checkbox" id="" value="'+departmentWiseSubWorks[k].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: 2px;" attr_department_id="'+departmentWiseSubWorks[k].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
																			}else{
																				str+='<input type="checkbox" id="" value="'+departmentWiseSubWorks[k].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: -14px;margin-left: 56px;" attr_department_id="'+departmentWiseSubWorks[k].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
																			}	
																			
																		str+='</label>';
																	str+='</span>';*/
																	str+='</h5>';
																}
																
																str+='<div class="bg_color_view">';
																	
																	//str+='<h3 class="panel-title f_16 font_weight">WORK 1</h3>';
																	str+='<input type="hidden" id="workIdDetails'+result.representationType+''+k+''+j+'" name="worksList['+k+'].subWorksList['+j+'].workId" value="'+departmentWiseSubWorks[k].subWorksList[j].workId+'" />';
																	str+='<div class="row m_top10">';
																			str+='<div class="col-sm-3">';
																				str+='<label> WORK TYPE <span class="starColor">*</span></label>';
																				str+='<select  name="worksList['+k+'].subWorksList['+j+'].workTypeId"  class="form-control chosen-select m_top10 validateCls newValidateCls workTypeReFreshCls'+k+'" id="workTypeId'+result.representationType+''+k+''+j+'" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																				str+='<option value="0"> Select Work Type </option>';
																					for(var w in globalWorkTypeList){
																							if(globalWorkTypeList[w].key == departmentWiseSubWorks[k].subWorksList[j].workTypeId ){
																								str+='<option value='+globalWorkTypeList[w].key+' selected>'+globalWorkTypeList[w].value+'</option>';
																							}else{
																								str+='<option value='+globalWorkTypeList[w].key+'>'+globalWorkTypeList[w].value+'</option>';
																							}
																						}
																				str+='</select><br><span class="workTypeId'+result.representationType+''+k+''+j+'"></span>';
																			str+='</div>';
																			str+='<div class="col-sm-3">';
																				str+='<label>WORK IN COST (in Lakhs) </label>';
																				str+='<input type="text"  value="'+departmentWiseSubWorks[k].subWorksList[j].estimateCost+'" name="worksList['+k+'].subWorksList['+j+'].estimateCost" class="form-control m_top5 height45 validateCls amountCls workCostReFreshCls'+k+'" id="appendWorkCost'+result.representationType+''+k+''+j+'" placeholder="Enter Work Cost" attr_main_count="'+k+'" attr_inner_count="'+j+'"  onkeyUp="checkIsNumber(this.id,this.value);;validateAmount(this.value,this.id,\''+result.representationType+'\')"><br><span class="  ErrCls appendWorkCost'+result.representationType+''+k+''+j+'"></span>';
																			str+='</div>';
																			/*str+='<div class="col-sm-3">';
																				str+='<label>WORK DETAILS <span class="starColor">*</span></label>';
																				str+='<input type="text"  value="'+departmentWiseSubWorks[k].subWorksList[j].workName+'"  name="worksList['+k+'].subWorksList['+j+'].grievanceDescription" class="form-control m_top5 height45 validateCls workDetailsReFreshCls'+k+'" id="appendWorkDetailsId'+result.representationType+''+k+''+j+'" placeholder="Enter" attr_main_count="'+k+'" attr_inner_count="'+j+'"><br><span class=" ErrCls appendWorkDetailsId'+result.representationType+''+k+''+j+'"></span>';
																			str+='</div>';*/
																			str+='<div class="col-sm-3">';
																				str+='<label>eOFFICE-ID </label>';
																				str+='<input type="text"  value="'+departmentWiseSubWorks[k].subWorksList[j].eOfficeId+'" name="worksList['+k+'].subWorksList['+j+'].eOfficeId" class="form-control m_top5 height45 validateCls officerIdReFreshCls'+k+'" id="appendEofficeId'+result.representationType+''+k+''+j+'" placeholder="Enter" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																			str+='</div>';
																	str+='</div>';
																	str+='<div class="row m_top10">';
																			str+='<div class="col-sm-12">';
																				str+='<label>WORK DETAILS <span class="starColor">*</span></label>';
																				str+='<input type="text"  value="'+departmentWiseSubWorks[k].subWorksList[j].workName+'"  name="worksList['+k+'].subWorksList['+j+'].grievanceDescription" class="form-control m_top5  validateCls workDetailsReFreshCls'+k+'" id="appendWorkDetailsId'+result.representationType+''+k+''+j+'" placeholder="Enter work details..." attr_main_count="'+k+'" attr_inner_count="'+j+'"  style="height:100px;"/><br><span class=" ErrCls appendWorkDetailsId'+result.representationType+''+k+''+j+'"></span>';
																			str+='</div>';
																	str+='</div>';
																	str+='<div class="row m_top10">';
																			str+='<div class="col-sm-3">';
																				str+='<label>LOCATION LEVEL <span class="starColor">*</span></label>';
																				str+='<select  name="worksList['+k+'].subWorksList['+j+'].locationScopeId" attr_work_id="'+departmentWiseSubWorks[k].subWorksList[j].workId+'" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+result.representationType+' validateCls newValidateCls locationLevelReFreshCls'+k+'" id="locationLevelId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0">Select Level</option>';
																					
																					if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 3){
																						str+='<option value="3" selected>District</option>';
																						str+='<option value="4">Constituency</option>';
																						str+='<option value="5">Mandal/Munci./Corp.</option>';
																						str+='<option value="6">Panchayat</option>';
																					}else if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 4){
																						str+='<option value="3">District</option>';
																						str+='<option value="4" selected>Constituency</option>';
																						str+='<option value="5">Mandal/Munci./Corp.</option>';
																						str+='<option value="6">Panchayat</option>';
																					}else if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 5){
																						str+='<option value="3">District</option>';
																						str+='<option value="4">Constituency</option>';
																						str+='<option value="5" selected>Mandal/Munci./Corp.</option>';
																						str+='<option value="6">Panchayat</option>';
																					}else if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 6){
																						str+='<option value="3" >District</option>';
																						str+='<option value="4">Constituency</option>';
																						str+='<option value="5">Mandal/Munci./Corp.</option>';
																						str+='<option value="6" selected >Panchayat</option>';
																					}else{
																						str+='<option value="3">District</option>';
																						str+='<option value="4">Constituency</option>';
																						str+='<option value="5">Mandal/Munci./Corp.</option>';
																						str+='<option value="6">Panchayat</option>';
																					}
																				str+='</select> <br><span class="locationLevelId'+result.representationType+''+k+''+j+'"></span>';
																			str+='</div>';
																			
																			if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 3){
																					str+='<div class="col-sm-2 districtCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>DISTRICT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select District </option>';
																						for(var dis in globaldistrictList){
																							if(globaldistrictList[dis].id == departmentWiseSubWorks[k].subWorksList[j].addressVO.districtId){
																								str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																							}else{
																								str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																							}
																						}
																							
																						str+='</select><br><span class="districtId'+result.representationType+''+k+''+j+'"></span>';
																					str+='</div>';
																					
																					str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
																					str+='<select   name="worksList['+k+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conChangeCls conRefreshCls'+k+''+j+'" id="constituencyId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Constituency </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.assemblyId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="constituencyId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 mandalCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>MANDAL/MUNCI./CORP. <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+k+''+j+' madalChangeCls" id="mandalId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Mandal/Munci/Corp </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="mandalId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 panchayatCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>PANCHAYAT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.panchayatId"  class="form-control chosen-select m_top10  validateCls panchayatRefreshCls'+k+''+j+'" id="panchayatId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Panchayat </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="panchayatId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																			}else if (departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 4){
																				str+='<div class="col-sm-2 districtCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>DISTRICT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select District </option>';
																						for(var dis in globaldistrictList){
																							if(globaldistrictList[dis].id == departmentWiseSubWorks[k].subWorksList[j].addressVO.districtId){
																								str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																							}else{
																								str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																							}
																						}
																							
																						str+='</select><br><span class="districtId'+result.representationType+''+k+''+j+'"></span>';
																					str+='</div>';
																					
																					str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
																					str+='<select    attr_load_type="onload"   name="worksList['+k+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conChangeCls conRefreshCls'+k+''+j+'" id="constituencyId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Constituency </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.assemblyId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="constituencyId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 mandalCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>MANDAL/MUNCI./CORP. <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+k+''+j+' madalChangeCls" id="mandalId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Mandal/Munci/Corp </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="mandalId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 panchayatCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>PANCHAYAT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.panchayatId"  class="form-control chosen-select m_top10  validateCls panchayatRefreshCls'+k+''+j+'" id="panchayatId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Panchayat </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="panchayatId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																			}else if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 5){
																				str+='<div class="col-sm-2 districtCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>DISTRICT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select District </option>';
																						for(var dis in globaldistrictList){
																							if(globaldistrictList[dis].id == departmentWiseSubWorks[k].subWorksList[j].addressVO.districtId){
																								str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																							}else{
																								str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																							}
																						}
																							
																						str+='</select><br><span class="districtId'+result.representationType+''+k+''+j+'"></span>';
																					str+='</div>';
																					
																					str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
																					str+='<select     attr_load_type="onload"  name="worksList['+k+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conChangeCls conRefreshCls'+k+''+j+'" id="constituencyId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Constituency </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.assemblyId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="constituencyId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 mandalCls'+result.representationType+''+k+''+j+'">';
																					str+='<label>MANDAL/MUNCI./CORP. <span class="starColor">*</span></label>';
																					str+='<select    attr_load_type="onload"  name="worksList['+k+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+k+''+j+' madalChangeCls" id="mandalId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Mandal/Munci/Corp </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="mandalId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 panchayatCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>PANCHAYAT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.panchayatId"  class="form-control chosen-select m_top10  validateCls panchayatRefreshCls'+k+''+j+'" id="panchayatId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Panchayat </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="panchayatId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																			}else if(departmentWiseSubWorks[k].subWorksList[j].locationScopeId == 6){
																				str+='<div class="col-sm-2 districtCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>DISTRICT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select District </option>';
																						for(var dis in globaldistrictList){
																							if(globaldistrictList[dis].id == departmentWiseSubWorks[k].subWorksList[j].addressVO.districtId){
																								str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																							}else{
																								str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																							}
																						}
																							
																						str+='</select><br><span class="districtId'+result.representationType+''+k+''+j+'"></span>';
																					str+='</div>';
																					
																					str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+k+''+j+'" >';
																					str+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
																					str+='<select    attr_load_type="onload"   name="worksList['+k+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conChangeCls conRefreshCls'+k+''+j+'" id="constituencyId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Constituency </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.assemblyId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="constituencyId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 mandalCls'+result.representationType+''+k+''+j+'">';
																					str+='<label>MANDAL/MUNCI./CORP. <span class="starColor">*</span></label>';
																					str+='<select    attr_load_type="onload"  name="worksList['+k+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+k+''+j+' madalChangeCls" id="mandalId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Mandal/Munci/Corp </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="mandalId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 panchayatCls'+result.representationType+''+k+''+j+'">';
																					str+='<label>PANCHAYAT <span class="starColor">*</span></label>';
																					str+='<select    attr_load_type="onload"  name="worksList['+k+'].subWorksList['+j+'].addressVO.panchayatId"  class="form-control chosen-select m_top10  validateCls panchayatRefreshCls'+k+''+j+'" id="panchayatId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Panchayat </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.panchayatId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="panchayatId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																			}else {
																				str+='<div class="col-sm-2 districtCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>DISTRICT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls districtRefreshCls" id="districtId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select District </option>';
																						for(var dis in globaldistrictList){
																							if(globaldistrictList[dis].id == departmentWiseSubWorks[k].subWorksList[j].addressVO.districtId){
																								str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																							}else{
																								str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																							}
																						}
																							
																						str+='</select><br><span class="districtId'+result.representationType+''+k+''+j+'"></span>';
																					str+='</div>';
																					
																					str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>CONSTITUENCY <span class="starColor">*</span></label>';
																					str+='<select   name="worksList['+k+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls conRefreshCls'+k+''+j+' conChangeCls" id="constituencyId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Constituency </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.assemblyId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="constituencyId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 mandalCls'+result.representationType+''+k+''+j+'" style="display:none;"> ';
																					str+='<label>MANDAL/MUNCI./CORP. <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls mandalRefreshCls'+k+''+j+' madalChangeCls" id="mandalId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Mandal/Munci/Corp </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.tehsilId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																							}
																						}
																						
																					str+='</select><br><span class="mandalId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																				
																				str+='<div class="col-sm-2 mandalCls'+result.representationType+''+k+''+j+'" style="display:none;">';
																					str+='<label>PANCHAYAT <span class="starColor">*</span></label>';
																					str+='<select  name="worksList['+k+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls panchayatRefreshCls'+k+''+j+'" id="mandalId'+result.representationType+''+k+''+j+'" attr_counterval="'+k+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+k+'" attr_inner_count="'+j+'">';
																					str+='<option value="0" > Select Panchayat </option>';
																						for(var c in departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList){
																							if(departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].key == departmentWiseSubWorks[k].subWorksList[j].addressVO.panchayatId){
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].key+'" selected>'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].value+'</option>';
																							}else{
																								str+='<option value="'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].key+'">'+departmentWiseSubWorks[k].subWorksList[j].addressVO.panchaytsList[c].value+'</option>';
																							}
																						}
																					str+='</select><br><span class="mandalId'+result.representationType+''+k+''+j+'"></span>';
																				str+='</div>';
																			}
																				
																	str+='</div>';
																str+='</div>';
															str+='</div>';
															innerWorkTypeCount=departmentWiseSubWorks[k].subWorksList.length;
															
														str+='</div>';	
														str+='</div>';	
														}
													str+='<div class="appendInnerBlocks'+result.representationType+''+k+''+innerWorkTypeCount+'"></div>';	
													str+='<div class="row">';	
													str+='<div class="col-sm-12">';
														str+='<div class="pull-right" style="margin-top:20px;margin-bottom:5px;">';
														
														if(result.subWorksList[i].endorsmentNo == null || result.subWorksList[i].endorsmentNo =='' || (result.subWorksList[i].endorsmentNo !='undefined' && result.subWorksList[i].endorsmentNo.trim.length==0))
														
															//str+='<span class="addLocationCss m_top10  updateStatusChangeCls" style="cursor:pointer;margin-left: 20px;" attr_total_works="'+departmentWiseSubWorks[k].subWorksList.length+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">UPDATE STATUS</span>';
															
															str+='<span class="addLocationCss  m_top10  cloned_Inner_Element" style="cursor:pointer; margin-left:20px;" attr_type="'+result.representationType+'" attr_counterval="'+k+'" main_work_count="'+k+'" inner_work_count="'+innerWorkTypeCount+'" attr_id="manageInnerWorks'+globalWorkTypeCount+'">ADD WORK </span>';
															
															//str+='<span class="addLocationCss  editAddCommentCls m_top10 " style="cursor:pointer;margin-left:20px;" attr_type="'+result.representationType+'" attr_counterval="'+k+'" main_work_count="'+k+'" inner_work_count="'+innerWorkTypeCount+'" attr_id="manageInnerWorks'+globalWorkTypeCount+'">ADD COMMENT </span>';
															
														str+='</div>';
													str+='</div>';
												str+='</div>';
												//append add work individual End
										str+='</div>';
									str+='</div>';
									str+='</div>';
								
								addWorkTypeCountMain=departmentWiseSubWorks.length+1;
								globalWorkTypeCount = parseInt(globalWorkTypeCount)+1;
							}
						str+='</div>';
					str+='</div>';					 
				str+='</div>';
			str+='</div>';
			str+='</div>';
			
			
			
		}	
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="appendWorkDetailsDiv'+result.representationType+'"></div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" block-clone-counter-'+result.representationType+'="'+addWorkTypeCountMain+'" style="cursor:pointer;" block-clone-'+result.representationType+'="'+globalWorkTypeCount+'" attr_type="'+result.representationType+'">ADD WORK TYPE </span></div>';
		str+='</div>';//5555 globalWorkTypeCount
		/*
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
						str+='Edit Reason : <span class="" style="color:red;">*</span> <span class="ErrCls" id="ErrpetitionEditRemark"></sapn>';
						str+='<textarea class="form-control" rows="3" id="" name="remarks" id="petitionEditRemark"></textarea>';
				str+='</div>';
			str+='</div>';
		*/
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
				//str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+typeVal+'">ADD REFERRAL</button>';
				str+='<div class="col-sm-12 m_top20"><span id="saveButtonId" class="addLocationCss m_top20 saveRepresentRequestDetails" style="cursor:pointer;background-color:green;" attr_type="'+result.representationType+'">UPDATE DETAILS</span><span id="savingDetailsSpinner"></span><span class="col-sm-offset-4" id="statusMsgAppntReqt"></span><span class="ErrCls" id="refCandidatesErr"></span></div>';
		str+='</div>';
	str+='</div>';
		
			$("#"+result.representationType+"DetailsDivId").html(str);
			
			for(var i in result.referDetailsList){
				initializeEditFileUploadMainBlock(result.representationType,result.referDetailsList[i].refCandidateId,"editFileUpload");
			}
			for(var i in result.representeeDetailsList){
				initializeEditFileUploadMainBlock(result.representationType,result.representeeDetailsList[i].refCandidateId,"editRepresenteeFileUpload");
			}
			initializeEditFileProjDoc(result.representationType);
			$(".chosen-select").chosen();
			$(".fancyboxView").fancybox();
			$("#designationREPRESENTEE").trigger('chosen:updated');
}
function openDoc(docmnt){
	 window.open(docmnt);
}


$(document).on("change","#districtREPRESENTEE",function(){
	var levelVal = $(this).val();
	getConstituencyNamesBiDistrictId(levelVal); 
	
});


function getConstituencyNamesBiDistrictId(levelVal){
	  $("#constituencyREPRESENTEE").html('');
	  $("#constituencyREPRESENTEE").html('<option value="0">Select Constituency</option>');	
	  $("#constituencyREPRESENTEE").trigger('chosen:updated');
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
				$("#constituencyREPRESENTEE").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#constituencyREPRESENTEE").trigger('chosen:updated');		
	});	
}

$(document).on("change","#constituencyREPRESENTEE",function(){
	var levelVal = $(this).val();
	getTehsilsAndLocalElectionBodiForConstituencyId(levelVal); 
	
});
function getTehsilsAndLocalElectionBodiForConstituencyId(levelVal){
	  $("#mandalREPRESENTEE").html('');	
	  $("#mandalREPRESENTEE").html('<option value="0">Select Mandal</option>');	
	  $("#mandalREPRESENTEE").trigger('chosen:updated');	
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
					var levelId = tehsilId;//tehsilId.toString().substr(1, 4);
					if(result[i].electionType != null){
						$("#mandalREPRESENTEE").append('<option value="'+levelId+'">'+result[i].value+' '+result[i].electionType+'</option>');
					}else{
						$("mandalREPRESENTEE").append('<option value="'+levelId+'">'+result[i].value+'</option>');
					}
			}
		}
		$("#mandalREPRESENTEE").trigger('chosen:updated');
	});	
}


function getPanchayats(levelVal,counterId,typeVal,typeChange){
	
	$("#panchayatId").html('');
	   var searchType= "all";
		var searchId=0;
		if(typeChange == "Inner"){
			  $("#panchayatInnerId"+typeVal+counterId).html('<option value="0">Select Panchayat</option>');
			  $("#panchayatInnerId"+typeVal+counterId).trigger('chosen:updated');
		}else{
			if(typeVal== "popup"){
				searchType = "refCandidate";
				searchId = $('#designationsId').val();
			}else if(counterId !="" && parseInt(counterId)>=0){
				// $("#panchayatId"+typeVal+counterId).html('');
				 $("#panchayatId"+typeVal+counterId).html('<option value="0">Select Panchayat</option>');
				  $("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
			}
		}
	  var json = {
		  tehsilId:levelVal,
		  searchType:"all",
		  searchId:0
		}        
	$.ajax({                
		type:'POST',    
		url: 'getPanchayatsByTehsilId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 if(typeChange == "Inner"){
				  $("#panchayatInnerId"+typeVal+counterId).html('<option value="0">Select Panchayat</option>');
			 }else{
				 $("#panchayatId"+typeVal+counterId).html('<option value="0">Select Panchayat</option>');
			 }
			// $("#panchayatId").html('<option value="0">All</option>');
			for(var i in result){	
				if(typeChange == "Inner"){
					$("#panchayatInnerId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}else{
					$("#panchayatId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
					$("#panchayatId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		/*if(typeChange == "Inner"){
			$("#panchayatInnerId"+typeVal+counterId).trigger('chosen:updated');
		}else{
			$("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
			$("#panchayatId").trigger('chosen:updated');
		}*/
		
		if(typeChange == "Inner"){
			$("#panchayatInnerId"+typeVal+counterId).trigger('chosen:updated');
			if(GlPnchayatId != null && GlPnchayatId !='undefined'){
				$("#panchayatInnerId"+typeVal+counterId).val(GlPnchayatId);
				$("#panchayatInnerId"+typeVal+counterId).trigger('chosen:updated');
			}
		}else{
			if(counterId !="" && parseInt(counterId)>=0){
				$("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
				if(GlPnchayatId != null && GlPnchayatId !='undefined'){
					$("#panchayatId"+typeVal+counterId).val(GlPnchayatId);
					$("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
				}
			}else{
				$("#panchayatId").trigger('chosen:updated');
			}
		}
		
	});	
}

function getParliamentIdsByConstituencyList(){
	 $("#constituencyCanId").html('<option value="0">All</option>');
	 $("#constituencyCanId").trigger('chosen:updated');
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
			 $("#constituencyCanId").html('<option value="0">All</option>');
			for(var i in result){
				$("#constituencyCanId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#constituencyCanId").trigger('chosen:updated');
	});	
}


function getPetitionDesignationLst(typeVal){
$('#representationDate,#endorsmentDate').val('');
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
			globalDesignationList=result;
		}
		getPetitionDetails();
	});	

}



function validateAmount(value,fieldId,typeVal){
	
	$('.ErrCls').html('');
	var enteredAmount =parseFloat(0.0);
	var estimationAmount= parseFloat($('#workCost'+typeVal+'').val());
	$(".amountCls").each(function(){
		var id = $(this).attr('id');
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseFloat(value) <=0){
				//$('.'+id+'').html("Invalid estimation cost entered. Please check once.");
				//return;
			}else{
				enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
			}
		}else{
			//$('.'+id+'').html("Invalid estimation cost entered. Please check once.");
			//	return;
		}
	});
	
	if(enteredAmount>estimationAmount){
		//$('.'+fieldId+'').html("Total estimation cost reached. Please check once.");
		//$('#'+fieldId+'').val('');
	}
	$('#workCost'+typeVal+'').val('');
	if(parseFloat(enteredAmount)>0){
		$('#workCost'+typeVal+'').val(enteredAmount);
	}
}


$(document).on("click","#getVoterDetailsId",function(){
	var typeVal=$(this).attr("attr_type");
	var voterId=$("#voterId"+typeVal).val();
	getRegistrationPersonDetails(voterId,typeVal);
});


function getRegistrationPersonDetails(voterId,typeVal){
			  
	$("#name"+typeVal).val('');
	$("#mobileNumber"+typeVal).val('');
	$("#emailId"+typeVal).val('');
	$("#repTdpCadreId"+typeVal).val('');
	$('#district'+typeVal).val(0);
	$('#constituency'+typeVal).val(0);
	$('#mandal'+typeVal).val(0);
	$('#panchayat'+typeVal).val(0);
	$("#repImagePathId"+typeVal).val('');
	$("#district"+typeVal).trigger('chosen:updated');
	$("#constituency"+typeVal).trigger('chosen:updated');
	$("#mandal"+typeVal).trigger('chosen:updated');
	$("#panchayat"+typeVal).trigger('chosen:updated');
	 
  var json = {
			voterId:voterId,
			familyVoterId:"0",
			tdpCadreId:"0",
			status:""

			};
  
	$.ajax({              
		type:'POST',    
		url: 'getRegistrationPersonDetails',
		dataType: 'json',  
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		error: function (xhr, status, err) {
            //alert("Local error callback.");
        },
	}).done(function(result){
		if(result != null ){
			if(result.lastName != null && result.lastName !='null'){
				$("#name"+typeVal).val(result.lastName);
			}
			if(result.mobileNumber != null && result.mobileNumber !='null'){
				$("#mobileNumber"+typeVal).val(result.mobileNumber);
			}
			if(result.email != null && result.email !='null'){
				$("#emailId"+typeVal).val(result.email);
			}
			if(result.tdpCadreId != null && result.tdpCadreId !='null' && parseInt(result.tdpCadreId)>0){
				$("#repTdpCadreId"+typeVal).val(result.tdpCadreId);
			}
			/*if(result.imageBase64String != null && result.imageBase64String.length > 0 && result.imageBase64String !='null'){
				$("#repImagePathId"+typeVal).val("http://www.mytdp.com/images/cadre_images/"+result.imageBase64String);
			  }else  if(result.imagePath != null && result.imagePath.length > 0 && result.imagePath !='null'){
				$("#repImagePathId"+typeVal).val(result.imagePath);
			  }*/
			 if(result.imagePath != null && result.imagePath.length > 0 && result.imagePath !='null'){
				$("#repImagePathId"+typeVal).val(result.imagePath);
			}	
			if(result.districtId != null){
				getAllDistrictsListInState(result.districtId);
			}
			if(result.constituencyId != null){
				getConstituencyNamesBiDistrictId(result.districtId,result.constituencyId);
			}
			var maandalId2='';
			if(result.mandalId != null){
				maandalId2=result.mandalId.toString().substring(1,result.mandalId.length);
				getTehsilsAndLocalElectionBodiForConstituencyId(result.constituencyId,"2"+maandalId2);
			}else if(result.localElectionBodyId != null){
					maandalId2=result.localElectionBodyId.toString().substring(1,result.localElectionBodyId.length);
				getTehsilsAndLocalElectionBodiForConstituencyId(result.constituencyId,"1"+maandalId2);
			}
			var villageId="2"+result.villageId.toString().substring(1,result.villageId.length);
			if(result.villageId != null	){
				getPanchayatsByTehsilId(maandalId2,villageId); 
			}
		}else{
			alert("No data available with this Voter Card No.Please check once.");
		}
	}).error(function(){
		alert("No data available with this Voter Card No.Please check once.");
	});	
 
}


function getAllDistrictsListInState(districtId){	
	$("#districtREPRESENTEE").html('');
	$("#districtREPRESENTEE").html('<option value="0">Select District</option>');
	$("#districtREPRESENTEE").trigger('chosen:updated');
	
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
			 $("#districtREPRESENTEE").html('<option value="0">Select District</option>');
				for(var i in result){
					if(districtId == result[i].id){
						$("#districtREPRESENTEE").append('<option value="'+result[i].id+'" selected>'+result[i].name+' </option>');
					}else{
						$("#districtREPRESENTEE").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
			}
			$("#districtREPRESENTEE").trigger('chosen:updated');
	});	
}

function getConstituencyNamesBiDistrictId(levelVal,constincyId){
	  $("#constituencyREPRESENTEE").html('');
	  $("#constituencyREPRESENTEE").html('<option value="0">Select Constituency</option>');	
	  $("#constituencyREPRESENTEE").trigger('chosen:updated');
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
				if(constincyId == result[i].locationId){
					$("#constituencyREPRESENTEE").append('<option value="'+result[i].locationId+'" selected>'+result[i].locationName+' </option>');
				}else{
					$("#constituencyREPRESENTEE").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				}
			}
		}
		$("#constituencyREPRESENTEE").trigger('chosen:updated');		
	});	
}

$(document).on("change","#constituencyrepresent",function(){
	var levelVal = $(this).val();
	getTehsilsAndLocalElectionBodiForConstituencyId(levelVal,""); 
	
});
function getTehsilsAndLocalElectionBodiForConstituencyId(levelVal,mandalId){
	  $("#mandalREPRESENTEE").html('');	
	  $("#mandalREPRESENTEE").html('<option value="0">Select Mandal</option>');	
	  $("#mandalREPRESENTEE").trigger('chosen:updated');	
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
					var levelId = tehsilId;//tehsilId.toString().substr(1, 4);
					if(result[i].electionType != null){
						if(mandalId == levelId){
							$("#mandalREPRESENTEE").append('<option value="'+levelId+'" selected>'+result[i].value+' '+result[i].electionType+'</option>');
						}else{
							$("#mandalREPRESENTEE").append('<option value="'+levelId+'">'+result[i].value+' '+result[i].electionType+'</option>');
						}
					}else{
						if(mandalId == levelId){
							$("#mandalREPRESENTEE").append('<option value="'+levelId+'" selected>'+result[i].value+'</option>');
						}else{
							$("#mandalREPRESENTEE").append('<option value="'+levelId+'">'+result[i].value+'</option>');
						}
					}
			}
		}
		$("#mandalREPRESENTEE").trigger('chosen:updated');
	});	
}
$(document).on("change","#mandalrepresent",function(){
	var levelVal = $(this).val();
	levelVal=levelVal.toString().substring(1,levelVal.length);
	getPanchayatsByTehsilId(levelVal,""); 
	
});

function getPanchayatsByTehsilId(levelVal,panchayatId){
	  $("#panchayatREPRESENTEE").html('');	
	  $("#panchayatREPRESENTEE").html('<option value="0">Select Panchayat</option>');	
	  $("#panchayatREPRESENTEE").trigger('chosen:updated');	
	  var json = {
		  tehsilId:levelVal,
		  searchType:"all",
		  searchId:0
		}        
	$.ajax({                
		type:'POST',    
		url: 'getPanchayatsByTehsilId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){			 		
			for(var i in result){
				if(panchayatId == result[i].key){
					$("#panchayatREPRESENTEE").append('<option value="'+result[i].key+'" selected>'+result[i].value+' </option>');
				}else{
					$("#panchayatREPRESENTEE").append('<option value="'+result[i].key+'" >'+result[i].value+' </option>');
				}
			}
		}
		$("#panchayatREPRESENTEE").trigger('chosen:updated');
	});	
}


function checkIsNumber(id,value){
	 if(isNaN(value)){
		$('#'+id+'').val('');
	 }else {
		 ;
	 }
}

$(document).on("click",".removeInnerWorkCls",function(){	
	if(!confirm("Are you sure want to delete this work details?"))
		return;
	
	var divIdStr = $(this).attr('attr_id');
	var mangageWorksId = $(this).attr('id');
	var attr_inner_works_Count = $('#'+mangageWorksId+'').attr("attr_inner_works_Count");	
	$('#'+mangageWorksId+'').attr("attr_inner_works_Count",parseInt(attr_inner_works_Count)-1);
	
	$('#'+divIdStr+'').remove();
	globalInnerWorksCount = parseInt(globalInnerWorksCount)-1;
	$(this).remove();
});

$(document).on("click",".removeWorkCls",function(){	
	if(!confirm("Are you sure want to delete this all work details?"))
		return;
	var innerWorksCount=$(this).attr('attr_inner_works_Count');	
	//alert(innerWorksCount);
	globalInnerWorksCount = parseInt(globalInnerWorksCount)-parseInt(innerWorksCount);
	var divIdStr = $(this).attr('attr_id');
	$('#'+divIdStr+'').remove();
	$(this).remove();
});

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
	var panchayatId='';
	
	var workTypeInnerId='';
	var appendWorkCostInner='';
	var appendWorkDetailsInnerId='';
	var appendEofficeInnerId='';
	var locationLevelInnerId='';
	var districtInnerId='';
	var constituencyInnerId='';
	var mandalInnerId='';
	var panchayatInnerId='';
	
	var flag = true;
	$('#saveButtonId').hide();
	$('.ErrCls').html('');
	
		var enteredAmount =parseFloat(0.0);
		$(".amountCls").each(function(){
			var id = $(this).attr('id');
			var value = $(this).val();
			if(value!= null && value.length>0){
				if(parseFloat(value) <=0){	
					$('#Err'+id+'').html("Invalid estimation cost entered. Please check once.");
				}else{
					enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
				}
			}
		});
		
		var estimationAmount= $('#workCost'+typeVal+'').val();
		if(estimationAmount != null && estimationAmount !='' && estimationAmount.length>0 && parseFloat(estimationAmount)>0){
			if((enteredAmount<estimationAmount) || (enteredAmount>estimationAmount)){
				//alert("Work wise total estimation cost not matched. Please check once.");
				//flag = false;
			}
		}else{
			//$('#workCost'+typeVal+'').val(enteredAmount);
		}
		$('#workCost'+typeVal+'').val(enteredAmount);
	
	if(isOldData =='N'){	
			if(typeVal =='REPRESENT' || typeVal =='REPRESENTEE'){
				var repName=$('#name'+typeVal+'').val();
				var repMobileNo=$('#mobileNumber'+typeVal+'').val();
				var repEmail=$('#emailId'+typeVal+'').val();
				var repDistrictId=$('#district'+typeVal+'').val();
				var repCostituencyId=$('#constituency'+typeVal+'').val();
				var repTehsilId=$('#mandal'+typeVal+'').val();
				var repPanchayatId=$('#panchayat'+typeVal+'').val();
				var repdesignation= $('#designation'+typeVal+'').val();
				
				if(repName == undefined || repName == "undefined" || repName.trim() == '' || repName == null){
					$('#nameErr'+typeVal+'').html("<h5 style='color:red;'>Please enter  name</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$('#nameErr'+typeVal+'').html("");
				}
				if(repMobileNo == undefined || repMobileNo == "undefined" || repMobileNo.trim() == '' || repMobileNo == null){
					$('#mobileNumberErr'+typeVal+'').html("<h5 style='color:red;'>Please enter  mobile no.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else if(parseInt(repMobileNo.trim().length) <10 || parseInt(repMobileNo.trim().length) >10 || parseInt(repMobileNo.trim().length) !=10){
					$('#mobileNumberErr'+typeVal+'').html("<h5 style='color:red;'>Please enter valid mobile no.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$('#mobileNumberErr'+typeVal+'').html("");
				}
				if(repEmail != undefined && repEmail != "undefined" && repEmail.trim() != '' && repEmail != null){
					 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
					 if (!filter.test(repEmail.trim())) {
						$('#emailIdErr'+typeVal+'').html("<h5 style='color:red;'>Please enter valid email address.</h5>");
						$('#saveButtonId').show();
						flag = false;
					 }
				}else{
					$('#emailIdErr'+typeVal+'').html("");
				}
				
				if(repDistrictId == 0 || repDistrictId == null || repDistrictId == ''){
					$("#districtErr"+typeVal+'').html("<h5 style='color:red;'>Please select  district.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$("#districtErr"+typeVal+'').html("");
				}
				if(repCostituencyId == 0 || repCostituencyId == null || repCostituencyId == ''){
					$("#constituencyErr"+typeVal+'').html("<h5 style='color:red;'>Please select  constituency.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$("#constituencyErr"+typeVal+'').html("");
				}
				if(repTehsilId == 0 || repTehsilId == null || repTehsilId == ''){
					$("#mandalErr"+typeVal+'').html("<h5 style='color:red;'>Please select  mandal/munci.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$("#mandalErr"+typeVal+'').html("");
				}
				/*if(repPanchayatId == 0 || repPanchayatId == null || repPanchayatId == ''){
					$("#panchayatErr"+typeVal+'').html("<h5 style='color:red;'>Please select  panchayat.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$("#panchayatErr"+typeVal+'').html("");
				}*/
				if(repdesignation == 0 || repdesignation == null || repdesignation == ''){
					$("#designationErr"+typeVal+'').html("<h5 style='color:red;'>Please select  designation.</h5>");
					$('#saveButtonId').show();
					flag = false;
				}else{
					$("#designationErr"+typeVal+'').html("");
				}		
			}
			
			
			var totalRefCount=0;
			$('#refCandidatesErr').html('');
			$('.refCandidatesCls').each(function(){
				var value = $(this).val();
				
				if(value != undefined && value != "undefined" && value.trim() != '' && value != null && parseInt(value)>0)
					totalRefCount = parseInt(totalRefCount)+1;
			});
			
			if(totalRefCount == undefined || totalRefCount == "undefined" || totalRefCount == null || parseInt(totalRefCount) == 0 ){
				flag = false;
				if(typeVal =='REPRESENT' || typeVal =='REPRESENTEE')
					$('#refCandidatesErr').html('Please add atleast one referral details.');
				else 
					$('#refCandidatesErr').html('Please add Self member details.');
				
			}
			/*
			var enteredAmount =parseFloat(0.0);
			$(".amountCls").each(function(){
				var id = $(this).attr('id');
				var value = $(this).val();
				if(value!= null && value.length>0){
					if(parseFloat(value) <=0){	
						$('#Err'+id+'').html("Invalid estimation cost entered. Please check once.");
					}else{
						enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
					}
				}
			});
			
			var estimationAmount= $('#workCost'+typeVal+'').val();
			if(estimationAmount != null && estimationAmount !='' && estimationAmount.length>0 && parseFloat(estimationAmount)>0){
				if((enteredAmount<estimationAmount) || (enteredAmount>estimationAmount)){
					//alert("Work wise total estimation cost not matched. Please check once.");
					//flag = false;
				}
			}else{
				//$('#workCost'+typeVal+'').val(enteredAmount);
			}
			$('#workCost'+typeVal+'').val(enteredAmount);*/
			completeWorkName = $("#workName"+typeVal).val();
			noofWorks = $("#noofWork"+typeVal).val();	
			workCost = $("#workCost"+typeVal).val();
			
			if(completeWorkName == undefined || completeWorkName == "undefined" || completeWorkName.trim() == '' || completeWorkName == null){
				$("#completeWorkNameId"+typeVal).html("<h5 style='color:red;'>Please enter work name</h5>");
				$('#saveButtonId').show();
				flag = false;
			}else{
				$("#completeWorkNameId"+typeVal).html("");
			}
			if(noofWorks == undefined || noofWorks == "undefined" || noofWorks === undefined || noofWorks.trim() == '' || noofWorks == null){
				$("#noOfWorksId"+typeVal).html("<h5 style='color:red;'>Please enter no of works</h5>");
				$('#saveButtonId').show();
				flag = false;
			}else{
				$("#noOfWorksId"+typeVal).html("");
			}
			/*if(workCost == undefined || workCost == "undefined" || workCost === undefined || workCost.trim() == '' || workCost == null){
				
				$("#workCostId"+typeVal).html("<h5 style='color:red;'>Please enter work cost</h5>");
				$('#saveButtonId').show();
				flag = false;
				//return;
			}else{
				$("#workCostId"+typeVal).html("");
			}*/
			
			var estimationWorksCount = $('#noofWork'+typeVal+'').val();
			if((parseInt(estimationWorksCount)>parseInt(globalInnerWorksCount)) || (parseInt(estimationWorksCount)<parseInt(globalInnerWorksCount))){ 
				alert("Max no of works data not matched. Please check once.");
				flag = false;
			}
			
			$(".newValidateCls").each(function(){
				
				
				var appendInnerType = $(this).attr("attr_type_change")
				var mainCount = $(this).attr("attr_main_count")
				var innerCount = $(this).attr("attr_inner_count")
				
				WorkTypeWiseDepartmentId = $("#WorkTypeWiseDepartmentId"+typeVal+mainCount+innerCount).val();
				subjectId = $("#subjectId"+typeVal+mainCount+innerCount).val();
				subSubjectId = $("#subSubjectId"+typeVal+mainCount+innerCount).val();
				
				workTypeId = $("#workTypeId"+typeVal+mainCount+innerCount).val();
				appendWorkCost = $("#appendWorkCost"+typeVal+mainCount+innerCount).val();
				appendWorkDetailsId = $("#appendWorkDetailsId"+typeVal+mainCount+innerCount).val();
				appendEofficeId = $("#appendEofficeId"+typeVal+mainCount+innerCount).val();
				
				
				if(parseInt(innerCount) == 0){

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
					if(workTypeId == 0 || workTypeId == null || workTypeId == ''){
						$(".workTypeId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Work Type</h5>");
						flag = false;
					}else{
						$(".workTypeId"+typeVal+mainCount+innerCount).html("");
					}			
					/*if(appendWorkCost == 0 || appendWorkCost == null || appendWorkCost == ''){
						$(".appendWorkCost"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter Work Cost</h5>");
						flag = false;
					}else{
						$(".appendWorkCost"+typeVal+mainCount+innerCount).html("");
					}*/	
					if(appendWorkDetailsId == 0 || appendWorkDetailsId == null || appendWorkDetailsId == ''){
						$(".appendWorkDetailsId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter Work Details</h5>");
						flag = false;
					}else{
						$(".appendWorkDetailsId"+typeVal+mainCount+innerCount).html("");
					}
					
					/*if(appendEofficeId == 0 || appendEofficeId == null || appendEofficeId == ''){
						$(".appendEofficeId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Enter eOFFICE-ID</h5>");
						flag = false;
					}else{
						$(".appendEofficeId"+typeVal+mainCount+innerCount).html("");
					}
					*/
				}
				if(appendInnerType == "main"){
					var mainCountMain = $(this).attr("attr_main_count")
					var innerCountMain = $(this).attr("attr_inner_count")
				
					locationLevelId = $("#locationLevelId"+typeVal+mainCountMain+innerCountMain).val();
					districtId = $("#districtId"+typeVal+mainCountMain+innerCountMain).val();
					constituencyId = $("#constituencyId"+typeVal+mainCountMain+innerCountMain).val();
					mandalId = $("#mandalId"+typeVal+mainCountMain+innerCountMain).val();
					panchayatId = $("#panchayatId"+typeVal+mainCountMain+innerCountMain).val();
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
					}else if(locationLevelId == 6){

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
						if(panchayatId == 0 || panchayatId == null || panchayatId == ''){
							$(".panchayatId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Panchayat</h5>");
							flag = false;
						}else{
							$(".panchayatId"+typeVal+mainCount+innerCount).html("");
						}
					}
				}
			});

			$(".newValidateInnerCls").each(function(){
					var mainCountIn = $(this).attr("attr_main_count");
					var innerCountIn = $(this).attr("attr_inner_count");

					workTypeInnerId = $("#workTypeInnerId"+typeVal+mainCountIn+innerCountIn).val();
					appendWorkCostInner = $("#appendWorkCostInner"+typeVal+mainCountIn+innerCountIn).val();
					appendWorkDetailsInnerId = $("#appendWorkDetailsInnerId"+typeVal+mainCountIn+innerCountIn).val();
					appendEofficeInnerId = $("#appendEofficeInnerId"+typeVal+mainCountIn+innerCountIn).val();
					
					locationLevelInnerId = $("#locationLevelInnerId"+typeVal+mainCountIn+innerCountIn).val();
					districtInnerId = $("#districtInnerId"+typeVal+mainCountIn+innerCountIn).val();
					constituencyInnerId = $("#constituencyInnerId"+typeVal+mainCountIn+innerCountIn).val();
					mandalInnerId = $("#mandalInnerId"+typeVal+mainCountIn+innerCountIn).val();
					panchayatInnerId = $("#panchayatInnerId"+typeVal+mainCountIn+innerCountIn).val();
					
					
					if(workTypeInnerId == 0 || workTypeInnerId == null || workTypeInnerId == ''){
						$(".workTypeInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Work Type</h5>");
						flag = false;
					}else{
						$(".workTypeInnerId"+typeVal+mainCountIn+innerCountIn).html("");
					}
					/*
					if(appendWorkCostInner == 0 || appendWorkCostInner == null || appendWorkCostInner == ''){
						$(".appendWorkCostInner"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Enter Work Cost</h5>");
						flag = false;
					}else{
						$(".appendWorkCostInner"+typeVal+mainCountIn+innerCountIn).html("");
					}
					*/
					if(appendWorkDetailsInnerId == 0 || appendWorkDetailsInnerId == null || appendWorkDetailsInnerId == ''){
						$(".appendWorkDetailsInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Enter Work Details</h5>");
						flag = false;
					}else{
						$(".appendWorkDetailsInnerId"+typeVal+mainCountIn+innerCountIn).html("");
					}
					
					/*if(appendEofficeInnerId == 0 || appendEofficeInnerId == null || appendEofficeInnerId == ''){
						$(".appendEofficeInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Enter eOFFICE-ID</h5>");
						flag = false;
					}else{
						$(".appendEofficeInnerId"+typeVal+mainCountIn+innerCountIn).html("");
					}
					*/
					if(locationLevelInnerId == 0 || locationLevelInnerId == null || locationLevelInnerId == ''){
						$(".locationLevelInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Location Type</h5>");
						flag = false;
					}else{
						$(".locationLevelInnerId"+typeVal+mainCountIn+innerCountIn).html("");
					}
					
					if(locationLevelInnerId == 3){
						if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select District</h5>");
							flag = false;
							
						}else{
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}
					}else if(locationLevelInnerId == 4){
						if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select District</h5>");
							flag = false;
							
						}else{
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}

						if(constituencyInnerId == 0 || constituencyInnerId == null || constituencyInnerId == ''){
							$(".constituencyInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Constituency</h5>");
								
							flag = false;
						}else{
							$(".constituencyInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}
					}else if(locationLevelInnerId == 5){

						if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select District</h5>");
							flag = false;
							
						}else{
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}

						if(constituencyInnerId == 0 || constituencyInnerId == null || constituencyInnerId == ''){
							$(".constituencyInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Constituency</h5>");
								
							flag = false;
						}else{
							$(".constituencyInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}
						if(mandalInnerId == 0 || mandalInnerId == null || mandalInnerId == ''){
							$(".mandalInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Mandal</h5>");
							flag = false;
								
						}else{
							$(".mandalInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}
					}else if(locationLevelInnerId == 6){

						if(districtInnerId == 0 || districtInnerId == null || districtInnerId == ''){
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select District</h5>");
							flag = false;
							
						}else{
							$(".districtInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}

						if(constituencyInnerId == 0 || constituencyInnerId == null || constituencyInnerId == ''){
							$(".constituencyInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Constituency</h5>");
								
							flag = false;
						}else{
							$(".constituencyInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}
						if(mandalInnerId == 0 || mandalInnerId == null || mandalInnerId == ''){
							$(".mandalInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Mandal</h5>");
							flag = false;
								
						}else{
							$(".mandalInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}
						/*if(panchayatInnerId == 0 || panchayatInnerId == null || panchayatInnerId == ''){
							$(".panchayatInnerId"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Select Panchayat</h5>");
							flag = false;
						}else{
							$(".panchayatInnerId"+typeVal+mainCountIn+innerCountIn).html("");
						}*/
					}
			});	
			
			
			if(flag == false){
				$('#saveButtonId').show();
				//return;
			}
	}
	
	/*var editRemark = $('#petitionEditRemark').val();
	if(editRemark == null || editRemark.length == 0){
		$('#ErrpetitionEditRemark').html('Edit Reason Required.');
		flag = false
	}
	
	if(flag == false){
		$('#saveButtonId').show();
		return;
	}
	*/
	$('#saveButtonId').hide();	
	$("#savingDetailsSpinner").html(spinner)
	
	 var formData = new FormData();
	// formData.append("saveType","test");
	$('#adminProfileForm input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			//debugger;
			if (typeof id !== typeof undefined && id !== false) {
				if(text=='text' || text=='hidden'){
					var name = $('#'+id+'').attr('name');
					if(formData.get(name) == null || formData.get(name) == 'undefined')
						formData.append(name, $('#'+id+'').val());
				}else if(text=='radio'){
					if($('#'+id+'').is(':checked')){
						var name = $('#'+id+'').attr('name');
						if(formData.get(name) == null || formData.get(name) == 'undefined')
							formData.append(name, $('#'+id+'').val());
					}
				}else if(text=='file'){
					var name = $('#'+id+'').attr('attr_name');//attr_image_tyep="refImage"  
					//var imageType = $('#'+id+'').attr('attr_image_tyep');
					if(this.files !=null && this.files.length>0){
							for(var i = 0; i < this.files.length; i++){
							//	alert(name+".fileList["+i+"]");
							if(formData.get(name) == null || formData.get(name) == 'undefined')
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
				if(formData.get(name) == null || formData.get(name) == 'undefined')
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
					if(formData.get(name) == null || formData.get(name) == 'undefined')
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
					   $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Updation Successfully</h3></center>").fadeOut(4000);
					  
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 3000)}); 
						window.close(); 
						//$(".defaultCheckCls").prop("checked",true)},6000);
						 
				  }else{
					  $('#saveButtonId').show();
					  $("#statusMsgAppntReqt").html("<center><h3 style='color: red;margin-top:-25px;'>Application Updation Failed..Try Later</h3></center>").fadeOut(4000);
					  setTimeout(function () {
						 
						}, 500);
						//setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				  }
				}else{
					  $('#saveButtonId').show();
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h3 style='color: red;margin-top:-25px;'>Application Updation Failed..Try Later</h3></center>").fadeOut(4000);
						}, 500);
						//setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				 }
				 
				
			},
			error: function(request,error) { 
				$("#savingDetailsSpinner").html('')
				console.log(request);
				console.log(error);
				alert("Error occured while updating details.Pelase check once any required data missing to fill.Then try again.");	
				$('#saveButtonId').show();				
			}
     });	 

});


//Disignation Build
function getPetitionDesignationList(){
    $("#designationsId").html('');
	 $("#designationsId").html('<option value="0">Select Designation</option>');
	 $("#designationsId").trigger('chosen:updated');
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
			 $("#designationsId").html('<option value="0">Select Designation</option>');
			for(var i in result){
				$("#designationsId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#designationsId").trigger('chosen:updated');
	});	
}

function rebuildWorkDetails(id,value,type,reprType){
	checkIsNumber(id,value);
	/*	
	if(value>0){
		
		if(!confirm("Are you sure want to change "+type+" Details ?")){
			
			$('#'+id+'').val('');
			return;
		}
		globalInnerWorksCount =1;
		globalWorkTypeCount = 0;
		$('#workDetailsDiv').html('');
		$('.mainWorkDivCls').html('');
		$("#workDetailsDiv").append(clondTemplate(1,'clone','',reprType,0,"Yes"));
		$(".chosen-select").chosen();
		globalWorkTypeCount = 1;
		$('.addLocationCss').attr('block-clone-counter-'+reprType.toLowerCase()+'',0);
		setTimeout(function(){
			$('#WorkTypeWiseDepartmentId'+reprType+'00').trigger('chosen:updated');
			$('#subjectId'+reprType+'00').trigger('chosen:updated');
			$('#subSubjectId'+reprType+'00').trigger('chosen:updated');
			$('#workTypeId'+reprType+'00').trigger('chosen:updated');
			$('#locationLevelId'+reprType+'00').trigger('chosen:updated');
			$('#districtId'+reprType+'00').trigger('chosen:updated');
			$('#constituencyId'+reprType+'00').trigger('chosen:updated');
			$('#mandalId'+reprType+'00').trigger('chosen:updated');
			$('#panchayatId'+reprType+'00').trigger('chosen:updated');
		},2000);
		
	}
	*/
}
$(document).on("click",".docsViewCls",function(){
	$("#docsModalDivId").modal("show");
	var docsList = [];
	var str="";
	$("#viewDocumentHeading").html("Covering Letter")
	docsList = coveringDocs;
	if(docsList != null && docsList.length >0){
			for(var j in docsList){
				var scanCopySpl = docsList[j].value.split("."); 
				var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
					str+='<div class="col-sm-6">';
						str+='<div class="viewImageCss">';
						if(scanCopyExt =="pdf"){
							str+='<a class="fancyboxView" href="#inline'+j+'">';
							str+='<div class="mouse-over">Expand</div>';
								str+='<object data="'+docsList[j].value+'" type="application/pdf" width="100%"height="300px;"></object>';
							str+='</a>';
							str+='<div id="inline'+j+'" style="width:100%;display: none;">';
								str+='<object data="'+docsList[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
							str+='</div>';
							
						}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
							str+='<a class="fancyboxView" href="#inline'+j+'">';
								str+='<img src="'+docsList[j].value+'"  width="100%" height="300px;"></img>';
							str+='</a>';
							str+='<div id="inline'+j+'" style="width:100%;display: none;">';
								str+='<img src="'+docsList[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
							str+='</div>';
						}else{
							str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+docsList[j].value+'\')">Here</a> To View Document</b>';
						}
			
				str+='</div>';
			str+='</div>';
		}
	}

	$("#docsViewModalId").html(str);
	$(".fancyboxView").fancybox();
});

$(document).on("change",".OnchangeDeptCls",function(){
	var outerCount=$(this).attr('attr_main_count');
	$(".subSubjectReFreshCls"+outerCount).html('<option value="0">Select Sub Subject</option>');
	$(".subSubjectReFreshCls"+outerCount).trigger('chosen:updated');
	//reFreshValues(outerCount);
});
$(document).on("change",".subjctOnchangeCls",function(){
	var outerCount=$(this).attr('attr_main_count');
	$(".subSubjectReFreshCls"+outerCount).html('<option value="0">Select Sub Subject</option>');
	$(".subSubjectReFreshCls"+outerCount).trigger('chosen:updated');
	//reFreshValues(outerCount);
});
$(document).on("change",".subSubjctOnchangeCls",function(){
	var outerCount=$(this).attr('attr_main_count');
	//reFreshValues(outerCount);
});
function reFreshValues(outerCount){
	$(".workTypeReFreshCls"+outerCount).val(0);
	$(".workTypeReFreshCls"+outerCount).trigger('chosen:updated');
	$(".workCostReFreshCls"+outerCount).val('');//
	$(".workDetailsReFreshCls"+outerCount).val(''); 
	$(".officerIdReFreshCls"+outerCount).val('');
	$(".locationLevelReFreshCls"+outerCount).val(0);
	$(".locationLevelReFreshCls"+outerCount).trigger('chosen:updated');
	$(".locationLevelReFreshCls"+outerCount).trigger("change");
}
$(document).on("change",".districtRefreshCls",function(){
	var outerCount=$(this).attr('attr_main_count');
	var innerCount=$(this).attr('attr_inner_count');
	$(".conRefreshCls"+outerCount+innerCount).html('<option value="0">Select Constituency</option>');
	$(".conRefreshCls"+outerCount+innerCount).trigger('chosen:updated');
	$(".mandalRefreshCls"+outerCount+innerCount).html('<option value="0">Select Mandal/Munci/Corp..</option>');
	$(".mandalRefreshCls"+outerCount+innerCount).trigger('chosen:updated');
	$(".panchayatRefreshCls"+outerCount+innerCount).html('<option value="0">Select Panchayat </option>');
	$(".panchayatRefreshCls"+outerCount+innerCount).trigger('chosen:updated');
});//
$(document).on("change",".conChangeCls",function(){
	var outerCount=$(this).attr('attr_main_count');
	var innerCount=$(this).attr('attr_inner_count');
	$(".mandalRefreshCls"+outerCount+innerCount).html('<option value="0">Select Mandal/Munci/Corp.. </option>');
	$(".mandalRefreshCls"+outerCount+innerCount).trigger('chosen:updated');
	$(".panchayatRefreshCls"+outerCount+innerCount).html('<option value="0">Select Panchayat </option>');
	$(".panchayatRefreshCls"+outerCount+innerCount).trigger('chosen:updated');
});
$(document).on("change",".madalChangeCls",function(){ 
	var outerCount=$(this).attr('attr_main_count');
	var innerCount=$(this).attr('attr_inner_count');
	$(".panchayatRefreshCls"+outerCount+innerCount).html('<option value="0">Select Panchayat </option>');
	$(".panchayatRefreshCls"+outerCount+innerCount).trigger('chosen:updated');
});
 $(document).on("click",".workStatusSelectedAllCls",function(){
	var endorsNo = $(this).attr("attr_enrorsNo");
	if($(this).is(":checked")){
		$(".checkbox"+endorsNo).prop("checked",true);
	}else{
		$(".checkbox"+endorsNo).prop("checked",false);
	}
 });
 
 $(document).on("click",".updateStatusChangeCls",function(){
	 $("#coveringLetterGenerator").html("");
	var totalWorks = $(this).attr("attr_total_works");
	var enrorsNo = $(this).attr("attr_enrorsNo");
	 $('#remarkIdErr').html("");
	$("#fileUploadDiv").hide();
	$("#commentsDivId").show();
	$("#leadDivId").hide();
	$("#grantDivId").hide();
	$("#assignOfficerDivId").hide();
	$("#assignDesignationDivId").hide();
	$("#endorsementDivId").hide();
	$("#endorsmentNo").val('');
	$("#remarksId").val('');
	$("#leadId").html('');
	$("#leadId").html('<option value="0"> SELECT LEAD </option>');
	$("#leadId").trigger("chosen:updated");
	$("#grantId").html('');
	$("#grantId").html('<option value="0">SELECT GRANT UNDER</option>');
	$("#grantId").trigger("chosen:updated");
    $("#assignToId").html('<option value ="0">SELECT DEPARTMENT</option>');
	$("#officerId").html('<option value ="0">SELECT OFFICER NAME</option>');
	$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');	
	//$("#statusChangeDivId").show();
	selectdWorksArr=[];
	$(".workStatusUpdateCls").each(function(){
		if($(this).is(":checked")){
			selectdWorksArr.push($(this).val());
			departmentSelectArr.push($(this).attr("attr_department_id"));
		}
	});
	
	if(selectdWorksArr.length == 0){
		alert("Please select atleast one work to update.");
		return ;
	}
	$('#endorsWorksId').html("Save Details");
	var notSeleWorks = totalWorks - selectdWorksArr.length;
	
	$("#statusChangeId").html('');
	if(globalStatusArr !=null && globalStatusArr.length>0){
		 $("#statusChangeId").append('<option value="0" attr_next_status_id="0" >Select Status</option>');
		for(var i in globalStatusArr){
			var nextStatusId=6;
			if(globalStatusArr[i].key == 1){
				
				nextStatusId=6;
			}	
			else if(globalStatusArr[i].key == 6){
				
				nextStatusId=7;
			}
			else if(globalStatusArr[i].key == 7){
				
				nextStatusId=3;
			}
			else if(globalStatusArr[i].key == 3){
				nextStatusId=8;
			}
			else if(globalStatusArr[i].key == 4){
				nextStatusId=4;
			}
			else if(globalStatusArr[i].key == 5){
				nextStatusId=5;	
			}
			if(enrorsNo !=null && enrorsNo>0){
				if(globalStatusArr[i].key !=1){
					if(globalStatusArr[i].key == 6)
						$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Upload action copy </option>');
					else if(globalStatusArr[i].key == 7)
						$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Upload detailed report </option>');
					else if(globalStatusArr[i].key == 3)
						$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Final approval </option>');
					else if(globalStatusArr[i].key == 4)
						$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Look for next year </option>');
					else if(globalStatusArr[i].key == 5)
						$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Not possible  </option>');
				}
			}else{
				if(isOldData=='N'){
					if(globalStatusArr[i].key == 1)
						$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Endorse petition </option>');
				}
				else if(globalStatusArr[i].key == 6)
					$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Upload action copy </option>');
				else if(globalStatusArr[i].key == 7)
					$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Upload detailed report </option>');
				else if(globalStatusArr[i].key == 3)
					$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Final approval </option>');
				else if(globalStatusArr[i].key == 4)
					$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Look for next year </option>');
				else if(globalStatusArr[i].key == 5)
					$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'"> Not possible  </option>');				
			}
		}
	}
	$("#statusChangeId").chosen();
	$("#statusChangeId").trigger('chosen:updated');
	$("#endorseMentHeadingId").html("");
	$("#endorseMentModalDivId").modal("show");
	if(enrorsNo != null && parseInt(enrorsNo)>0)
		$("#endorseMentHeadingId").html("Endorsed No - "+enrorsNo+"");
	
	
	$("#totalWorksId").html(totalWorks)
	$("#selectdWorksId").html(selectdWorksArr.length)
	$("#notSeleWorksId").html(notSeleWorks)
	
});	
$(document).on("change","#statusChangeId",function(){
	var statusId = $(this).val();
	var nextStatusId = $('option:selected', this).attr('attr_next_status_id') ;
	$('#nextStatusId').val(0);
	if(nextStatusId != null && nextStatusId>0)
		$('#nextStatusId').val(nextStatusId);
	if(statusId == 1){
     $('#WorkEndorsementNoErr').html(' ');
	 $('#leadIdErr').html('');
	 $('#grantIdErr').html('');
	 $('#assignToIdErr').html('');
	 $('#officerIdErr').html('');
	 $('#remarkIdErr').html('');
		$("#letterNameId").html("COVERING");
		$("#endorsentDivId").show();
		$("#commentsDivId").show();
		$("#leadDivId").show();
		$("#grantDivId").show();
		$("#saveBtnId").hide();
		$("#assignOfficerDivId").show();
		$("#assignDesignationDivId").show();
		$("#fileUploadDiv").hide();
		$("#fileUploadIdDiv").show();
		$(".saveEnable").prop("disabled", true);
		$("#buttonNameId").html("Forward")
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');
		initializeSingleUploadDocument("uploadEndorsementDocId");
		getPmBriefLeadList();
		getPmGrantList();
		getLoginUserAccessSubDeptDesignationDetail(departmentSelectArr);
		$('.saveEnable').attr('data-toggle', 'tooltip');
		$('.saveEnable').attr('title', 'Please generate the covering letter.');
		//$('[data-toggle="tooltip"]').tooltip();
		//$('.saveEnable').removeAttr('title');
		$("#coveringLetterGenerator").html("");
	}else if(statusId == 6){
		$("#saveBtnId").show();
		  $('#WorkEndorsementNoErr').html(' ');
		$("#remarksId").val('');
		 $('#remarkIdErr').html('');
		$("#letterNameId").html("ACTION COPY");
		$("#commentsDivId").show();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").show();
		$("#assignDesignationDivId").show();
		$("#endorsentDivId").hide();
		$("#buttonNameId").html("Forward")
		$(".saveEnable").prop("disabled", false);
		$('.saveEnable').removeAttr('title');
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');
		initializeSingleUploadDocument("uploadEndorsementDocId");
		$("#fileUploadDiv").show();
		$("#fileUploadIdDiv").hide();
		getLoginUserAccessSubDeptDesignationDetail(departmentSelectArr);
		$("#coveringLetterGenerator").html("");
	}else if(statusId == 7){
		$("#saveBtnId").show();
		$("#remarkIdErr").html('');
		$("#remarksId").val('');
		$("#letterNameId").html("DETAILED REPORT");
		$("#fileUploadDiv").show();
		$("#fileUploadIdDiv").hide();
		$("#commentsDivId").show();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").hide();
		$("#assignDesignationDivId").hide();
		$("#endorsentDivId").hide();
		$('.saveEnable').removeAttr('title');
		$(".saveEnable").prop("disabled", false);
		$("#buttonNameId").html("Save Details")
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');
		initializeSingleUploadDocument("uploadEndorsementDocId");
		$("#coveringLetterGenerator").html("");
	}else if(statusId == 3 || statusId == 4 || statusId == 5){
		$("#saveBtnId").show();
		$("#remarkIdErr").html('');
		$("#remarksId").val('')
		$("#letterNameId").html("");
		$("#fileUploadDiv").hide();
		$("#fileUploadIdDiv").hide();
		$("#commentsDivId").show();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").hide();
		$("#assignDesignationDivId").hide();
		$("#endorsentDivId").hide();
		$("#buttonNameId").html("Save Details")
		$(".saveEnable").prop("disabled", false);
		$('.saveEnable').removeAttr('title');
		$("#coveringLetterGenerator").html("");
	}
	else if(statusId == 0){
		$("#saveBtnId").show();
		 $('#remarkIdErr').html('');
		$("#letterNameId").html("");
		$("#fileUploadDiv").hide();
		$("#fileUploadIdDiv").hide();
		$("#commentsDivId").show();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").hide();
		$("#assignDesignationDivId").hide();
		$("#endorsentDivId").hide();
		$("#endorsmentNo").val('');
		$("#remarksId").val('');
		$("#leadId").html('');
		$(".saveEnable").prop("disabled", false);
		$('.saveEnable').removeAttr('title');
		$('.saveEnable').tooltip({disabled: true});
		$("#leadId").html('<option value="0"> SELECT LEAD </option>');
		$("#leadId").trigger("chosen:updated");
		$("#grantId").html('');
		$("#grantId").html('<option value="0">SELECT GRANT UNDER</option>');
		$("#grantId").trigger("chosen:updated");
		$("#assignToId").html('<option value ="0">SELECT DEPARTMENT</option>');
		$("#officerId").html('<option value ="0">SELECT OFFICER NAME</option>');
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');	
		$("#coveringLetterGenerator").html("");
		return;
	}
});	


function getPmBriefLeadList(){
	 $("#leadId").html('');
	 $("#leadId").html('<option value="0"> SELECT LEAD </option>');
   var json = {
      
    };
  $.ajax({              
    type:'POST',    
    url: 'getPmBriefLeadList',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  if(result !=null && result.length>0){
			 $("#leadId").html('<option value="0">SELECT LEAD</option>');
			for(var i in result){
				$("#leadId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#leadId").trigger('chosen:updated');
	});	 
}

function getPmGrantList(){
	 $("#grantId").html('');
	 $("#grantId").html('<option value="0">SELECT GRANT UNDER</option>');
   var json = {
      
    };
  $.ajax({              
    type:'POST',    
    url: 'getPmGrantList',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  if(result !=null && result.length>0){
			 $("#grantId").html('<option value="0">SELECT GRANT UNDER</option>');
			for(var i in result){
				$("#grantId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#grantId").trigger('chosen:updated');
	});	 
}

function getLoginUserAccessSubDeptDesignationDetail(selectedDeptIdsArr){
	 $("#assignToId").html('');
	 $("#assignToId").html('<option value="0">SELECT DESIGNATION </option>');
	
 var json = {
	 
	 deptIdsList : selectedDeptIdsArr
	}           
$.ajax({              
	type:'POST',    
	url: 'getLoginUserAccessSubDeptDesignationDetail',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
}).done(function(result){
	if(result !=null && result.length>0){
		$("#assignToId").html('<option value ="0">SELECT DESIGNATION </option>');
		for(var i in result){
				$("#assignToId").append('<option value ="'+result[i].key+'">'+result[i].value+'</option>');
		}
	}
	$("#assignToId").trigger('chosen:updated');
});	
}

$(document).on('change','.popUpChangesCls',function(){
	var onChangeValue = $(this).val();
	getDeptDesignationOfficerDetail(onChangeValue)
})

function getDeptDesignationOfficerDetail(onChangeValue){
	$("#officerId").html('');
	$("#officerId").html('<option value ="0">SELECT OFFICER NAME </option>');
	var deptDesignationId = onChangeValue;
 var json = {
	deptDesignationId : deptDesignationId
	}           
$.ajax({              
	type:'POST',    
	url: 'getDeptDesignationOfficerDetail',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
}).done(function(result){
	if(result != null && result.length >0){
		$("#officerId").html('<option value ="0">SELECT OFFICER NAME</option>');
		for(var i in result){
			$("#officerId").append('<option value ="'+result[i].key+'">'+result[i].value+'</option>');
		}
	}
	$("#officerId").trigger('chosen:updated');
});	
}


function endorsingSubWorksAndAssigningToOfficer(){
      var flag = false;
     //$('#endorsementNoErr').html(' ');
	 $('#WorkEndorsementNoErr').html(' ');
	 $('#leadIdErr').html('');
	 $('#grantIdErr').html('');
	 $('#assignToIdErr').html('');
	 $('#officerIdErr').html('');
	 $('#remarkIdErr').html('');
	 $('#statusIdErrStr').html(' ');
	 
	
	    var endorsementId =$("#workEndorsmentNo").val();
	     var leadIdValue = $("#leadId").val();
	     var grantIdValue = $("#grantId").val();
		 //var assignToIdValue = $("#assignToId").val();
        //var officerIdValue = $("#officerId").val();
	     var remarksId =$("#remarksId").val();
	    var statusId = $("#statusChangeId").val();
	
	if(statusId == 0){
		if(remarksId == 0 || remarksId == '' || remarksId == null || remarksId.trim().length == 0){
			$('#remarkIdErr').html("<h5 style='color:red;'>Comment is required</h5>");
			   flag = true ; 
		 }else{
			 $('#remarkIdErr').html("");
		 }
	}else if(statusId == 1){
		 
		  if(endorsementId == 0 || endorsementId == '' || endorsementId == null || endorsementId.trim().length == 0){
			   $('#WorkEndorsementNoErr').html("<h5 style='color:red;'>Endosment no is required</h5>");
		        flag =true;
	       }else{
			   $('#WorkEndorsementNoErr') .html("");
		   }
	 
		  if(leadIdValue == null || leadIdValue ==0){
			 $('#leadIdErr').html("<h5 style='color:red;'>Please select brief lead</h5>");
				flag =true; 
			}else{
				  $('#leadIdErr').html("");
		   }
		 if(grantIdValue == null || grantIdValue ==0){
			 $('#grantIdErr').html("<h5 style='color:red;'>Please select grant</h5>");
		     flag =true ; ;
		 }else{
			  $('#grantIdErr').html("");
		 }
		 
	    /* if(assignToIdValue == null || assignToIdValue==0){
			$('#assignToIdErr').html("<h5 style='color:red;'>Please select assign to</h5>");
			flag =true ; 
		 }else{
			 $('#assignToIdErr').html("");
		 }
		 
		 if(officerIdValue == null || officerIdValue==0){
			 $('#officerIdErr').html("<h5 style='color:red;'>Please select officer name</h5>");
			 flag = true ; 
		 }else{
			  $('#officerIdErr').html(""); 
		 } */
		 
		if(remarksId == 0 || remarksId == '' || remarksId == null || remarksId.trim().length == 0){
			$('#remarkIdErr').html("<h5 style='color:red;'>Comment is required</h5>");
			   flag = true ; 
		 }else{
			 $('#remarkIdErr').html("");
		 }
	 }else if(statusId == 6){
		
		  /* if(assignToIdValue == null || assignToIdValue==0){
			$('#assignToIdErr').html("<h5 style='color:red;'>Please select assign to</h5>");
			   flag =true ; 
		  }else{
			 $('#assignToIdErr').html("");
		  }
		 if(officerIdValue == null || officerIdValue==0){
			 $('#officerIdErr').html("<h5 style='color:red;'>Please select officer name</h5>");
			    flag = true ; 
		 }else{
			  $('#officerIdErr').html(""); 
		 } */
		if(remarksId == 0 || remarksId == '' || remarksId == null || remarksId.trim().length == 0){
			$('#remarkIdErr').html("<h5 style='color:red;'>Comment is required</h5>");
			   flag = true ; 
		 }else{
			 $('#remarkIdErr').html("");
		 }
		 
	}else if(statusId == 7 || statusId == 3  || statusId == 4  || statusId == 5){
		  var remarksId =$("#remarksId").val();
		 if(remarksId == 0 || remarksId == '' || remarksId == null || remarksId.trim().length == 0){
			$('#remarkIdErr').html("<h5 style='color:red;'>Comment is required</h5>");
			   flag = true ; 
		 }else{
			 $('#remarkIdErr').html("");
		 }
	}
	
	
	if(flag==true)
		return;
	
	   //$('#endorsWorksId').hide();
	   var endorsementNO="";
	   var formData = new FormData();
	   $('#endorsingSubWorksId input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			//debugger;
			if (typeof id !== typeof undefined && id !== false) {
				if(text=='text' || text=='hidden'){
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
					
					if(name=="petitionId")
						petitionId = $('#'+id+'').val();
					else if(name=="endorsementNO")
						endorsementNO = $('#'+id+'').val();
						
				}else if(text=='radio'){
					if($('#'+id+'').is(':checked')){
						var name = $('#'+id+'').attr('name');
						formData.append(name, $('#'+id+'').val());
					}
				}else if(text=='file'){
					if(this.files !=null && this.files.length>0){
						for(var i = 0; i < this.files.length; i++){
								formData.append("filesList["+i+"]", this.files[i]);
						}
					}
				}
			}			
		}
	);
	
	$('#endorsingSubWorksId textarea').each(
		  function(){			  
			var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
				var name = $('#'+id+'').attr('name');
				formData.append(name, $('#'+id+'').val());
			}
		}
	);
	
	$('#endorsingSubWorksId select').each(
		  function(){			  
				var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
			}
		}
	);
	if(selectdWorksArr !=null && selectdWorksArr.length>0){
			for(var i = 0; i < selectdWorksArr.length; i++){
				formData.append("workIds["+i+"]", selectdWorksArr[i]);
				if($("#nextStatusId").val()==6){
					if(formData.get('statusType') == null || formData.get('statusType') == undefined || formData.get('statusType') == 'undefined')
						formData.append("statusType", "COVERING LETTER");
				}else if($("#nextStatusId").val()==7){
					if(formData.get('statusType') == null || formData.get('statusType') == undefined || formData.get('statusType') == 'undefined')
						formData.append("statusType", "ACTION COPY");
				}else if($("#nextStatusId").val()==3){
					if(formData.get('statusType') == null || formData.get('statusType') == undefined || formData.get('statusType') == 'undefined')
						formData.append("statusType", "DETAILED REPORT");
				}
		}
	}
	//formData.append("petitionId", petitionId);
$.ajax({
			url: $("#endorsingSubWorksId").attr("action"),
			data: formData,
			type: "POST",               
			processData: false,
			contentType: false,
			success: function(result) {
				$("#savingDetailsSpinner").html('');
				
					if(result!=null){
					  if(result.exceptionMsg == "SUCCESS"){
						 setTimeout(function () {
						$("#ajaxImageId").html("<center><h4 style='color: green;'>Please Wait Work(s) details updated successfully</h4></center>").fadeOut(5000);
						}, 500);
						   setTimeout(function () {
						 $("#endorseMentModalDivId").modal("hide");
						}, 1000);
							$(".saveEnable").prop("disabled", true);
						 
						  //$("#endorseMentModalDivId").modal("hide");
							window.location.reload(); 
						 // $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Saved Successfully</h3></center>").fadeOut(4000);
					  }else{
						  $('#endorsWorksId').show();
					  }
					}else{
					 $('#endorsWorksId').show();
					}
			},
			error: function(request,error) { 
				$("#savingDetailsSpinner").html('');
				//console.log(request);
				//console.log(error);
				setTimeout(function () {
						$("#ajaxImageId").html("<center><h4 style='color: green;'>Error occured while updating details.Pelase check once any required data missing to fill.Then try again.</h4></center>").fadeOut(3000);
						}, 500);
						$(".saveEnable").prop("disabled", false);
				$('#endorsWorksId').show();				
			}
     });
 }	
function generateCoveringLetterForPetition(){
	//$('#endorsWorksId').hide();
	 
	var flag = false;
	$('#WorkEndorsementNoErr').html('');
	$('#leadIdErr').html('');
	$('#grantIdErr').html(''); 
	
	var endorsementId =$("#workEndorsmentNo").val();
	var leadIdValue = $("#leadId").val();
	var grantIdValue = $("#grantId").val();
	var statusId = $("#statusChangeId").val();
	  
	if(statusId == 1){
		if(endorsementId == 0 || endorsementId == '' || endorsementId == null || endorsementId.trim().length == 0){
			   $('#WorkEndorsementNoErr').html("<h5 style='color:red;'>Endosment no is required</h5>");
		        flag =true;
		}else{
		   $('#WorkEndorsementNoErr') .html("");
		}
	    if(leadIdValue == null || leadIdValue ==0){
			$('#leadIdErr').html("<h5 style='color:red;'>Please select brief lead</h5>");
				flag =true; 
			}else{
				$('#leadIdErr').html("");
		}
		/* if(grantIdValue == null || grantIdValue ==0){
			$('#grantIdErr').html("<h5 style='color:red;'>Please select grant</h5>");
		    flag =true ;
		}else{
			 $('#grantIdErr').html("");
		} */
	}
	

	if(flag==true)
	return; 
	 
	 var  schemeIdsListArr =[];
	   var endorsementNO="";
	   var petitionId=0;
	   var formData = new FormData();
	   $('#endorsingSubWorksId input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			//debugger;
			if (typeof id !== typeof undefined && id !== false) {
				if(text=='text' || text=='hidden'){
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
					if(name=="petitionId")
						petitionId = $('#'+id+'').val();
					else if(name=="endorsementNO")
						endorsementNO = $('#'+id+'').val();
				}else if(text=='radio'){
					if($('#'+id+'').is(':checked')){
						var name = $('#'+id+'').attr('name');
						formData.append(name, $('#'+id+'').val());
					}
				}else if(text=='file'){
					if(this.files !=null && this.files.length>0){
						for(var i = 0; i < this.files.length; i++){
								formData.append("filesList["+i+"]", this.files[i]);
						}
					}
				}
			}			
		}
	);
	
	var leadId = $("#leadId").val();
	var grantId = $("#grantId").val();
	if(selectdWorksArr !=null && selectdWorksArr.length>0){
			for(var i = 0; i < selectdWorksArr.length; i++){
				schemeIdsListArr.push(selectdWorksArr[i]);
		}
	}
 
var json = {
   pageId :petitionId,//petitionId
   schemeIdsList:schemeIdsListArr,//subWorkIds
   leadName:leadId,
   groupName:grantId ,
	endValue:endorsementNO,//endorsmentNo
	pType:"viewPage",
	type:"COVERING LETTER"
  }           
 $.ajax({              
  type:'POST',    
  url: 'generateCoveringLetterForPetition',
  dataType: 'json',
  data : JSON.stringify(json),
  beforeSend :   function(xhr){
   xhr.setRequestHeader("Accept", "application/json");
   xhr.setRequestHeader("Content-Type", "application/json");
  }
 }).done(function(result){
	 $("#coverLetterPath").val("");
	 if(result !=null){
		 var str='';
		 var scanCopySpl = result.exceptionMsg.split("."); 
			var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
			str+='<div  class="row">';
			str+='<div class="col-sm-4 m_top10">';
				str+='<div class="viewImageCss">';
				if(scanCopyExt =="pdf"){
					str+='<a class="fancyboxView" href="#inlineddd">';
					str+='<div class="mouse-over">Expand</div>';
						str+='<object data="'+result.exceptionMsg+'" type="application/pdf" width="100%"height="100px;"></object>';
						
					str+='</a>';
					str+='<div id="inlineddd" style="width:100%;display: none;">';
						str+='<object data="'+result.exceptionMsg+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
						
					str+='</div>';
					$("#coverLetterPath").val(result.exceptionMsg);
					//$("#saveBtnId").show();
					$(".saveEnable").prop("disabled", false);
				}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
					str+='<a class="fancyboxView" href="#inlineddds">';
						str+='<img src="'+result.exceptionMsg+'"  width="100%" height="100px;"></img>';
						
					str+='</a>';
					str+='<div id="inlineddds" style="width:100%;display: none;">';
						str+='<img src="'+result.exceptionMsg+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
						
					str+='</div>';
					$("#coverLetterPath").val(result.exceptionMsg);
					$("#saveBtnId").show();
				}else{
					str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+result.exceptionMsg+'\')">Here</a> To View Document</b>';
					$("#coverLetterPath").val(result.exceptionMsg);
					$("#saveBtnId").show();
				}

		str+='</div>';
	str+='</div>';
	
	$("#coveringLetterGenerator").html(str);
	$(".fancyboxView").fancybox();
	 }
	
	
 }); 
}
$(document).on('click','.editAddCommentCls',function(){
	
	var totalWorks = $(this).attr("attr_total_works");
	var enrorsNo = $(this).attr("attr_enrorsNo");

	
	selectdWorksArr=[];
	$(".workStatusUpdateCls").each(function(){
		if($(this).is(":checked")){
			selectdWorksArr.push($(this).val());
			departmentSelectArr.push($(this).attr("attr_department_id"));
		}
	});
	if(selectdWorksArr.length == 0){
		alert("Please select atleast one work to update.");
		return ;
	}
	$("#remarksId").val('');
	$("#endorseMentHeadingId").html("Comment ");
	$("#remarkIdErr").html('');
	$("#totalWorkEditDivId").hide();
	$("#statusChangeDivId").hide();
	$("#endorsentDivId").hide();
	$("#leadDivId").hide();
	$("#grantDivId").hide();
	$("#assignDesignationDivId").hide();
	$("#assignOfficerDivId").hide();
	$("#fileUploadDiv").hide();
	$("#commentsDivId").show();
	$("#saveBtnId").show();
	
	$("#endorseMentModalDivId").modal("show");
	
	$('#endorsWorksId').show();
	$('#endorsWorksId').html("Save Details");
	
});
