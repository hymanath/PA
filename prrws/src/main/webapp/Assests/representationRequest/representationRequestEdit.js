var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalWorkTypeCount=0;
var globalDepartmentsList='';
var globalSubjectList='';
var globalSubSubjectsList='';
var globalWorkTypeList='';
var globaldistrictList='';
var alreadyCandidateId=[];
var searchCandidateIds=[];
var diffArr=[];
var commonArr=[];
getSubjectPetitionsDepartmentList();
getWorkTypeList();
getAllDistrictsInState("","","","onload");
//District Build
function getAllDistrictsInState(typeVal,counterId,typeChange,type){
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
			if(type == "onload"){
				globaldistrictList = result;
			}
	
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
function getSubjectPetitionsDepartmentList(){
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
		getPetitionSubjectList(0,"","","onload")
	});	
}

function getPetitionSubjectList(subjectId,divId,innerCount,type){
	if(type=="change"){
		$("#"+divId+innerCount).html('');
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
				 $("#"+divId+innerCount).append('<option value="0">Select Sub Subject</option>');
				for(var i in result){
					$("#"+divId+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		if(type=="change"){
			$("#"+divId+innerCount).trigger('chosen:updated');
		}else{
			getPetitionSubSubjectList(0,"","","onload")
		}
		
		
	});	
}
function getPetitionSubSubjectList(subjectId,divId,innerCount,type){
	if(type=="change"){
		$("#"+divId+innerCount).html('');
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
				 $("#"+divId+innerCount).append('<option value="0">Select Sub Subject</option>');
				for(var i in result){
					$("#"+divId+innerCount).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		if(type=="change"){
			$("#"+divId+innerCount).trigger('chosen:updated');
		}
		if(type=="onload"){
			getPetitionDetails();
		}
		
	});	
}
function getWorkTypeList(){
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
			globalWorkTypeList =result; 
		 }
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
		}else if (value== 16 || value== 2){
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
	
	$("#fileUpload"+typeVal+candidateId).append('<div class="col-sm-4" style="margin-top:-20px;"><label>REFERAL LETTER</label><input type="file"   attr_name="referList['+refCandCount+']" name="" attr_image_tyep="refImage"  id="editFileUpload'+candidateId+''+typeVal+'" multiple="multiple" class=""/></div>');
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
	initializeEditFileUploadMainBlock(typeVal,candidateId);
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
$(document).on("change",".locationLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var changeType = $(this).attr("attr_type_change");

	if(levelVal == 3){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('');
			$("#districtId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$(".districtCls"+typeVal+counterId).show();
			$(".constituencyCls"+typeVal+counterId).hide();
			$(".mandalCls"+typeVal+counterId).hide();
			$(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
			$(".districtInnerCls"+typeVal+counterId).show();
			$(".constituencyInnerCls"+typeVal+counterId).hide();
			$(".mandalInnerCls"+typeVal+counterId).hide();
			$(".panchayatInnerCls"+typeVal+counterId).hide();
		}
		
	}else if(levelVal == 4){
		if(changeType == "main"){
			$("#districtId"+typeVal+counterId).html('');
			 $("#districtId"+typeVal+counterId).trigger("chosen:updated");
			 getAllDistrictsInState(typeVal,counterId,changeType,"change");
			  $("#constituencyId"+typeVal+counterId).html('');
			  $("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
			  $(".districtCls"+typeVal+counterId).show();
			  $(".constituencyCls"+typeVal+counterId).show();
			  $(".mandalCls"+typeVal+counterId).hide();
			  $(".panchayatCls"+typeVal+counterId).hide();
		}else{
			$("#districtInnerId"+typeVal+counterId).html('');
			$("#districtInnerId"+typeVal+counterId).trigger("chosen:updated");
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
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
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
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
			getAllDistrictsInState(typeVal,counterId,changeType,"change");
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
		//getWorkTypeList('workTypeId',typeVal,counterappendId,blockId);
		
	//}
});
$(document).on("click",".cloned_Inner_Element",function(){
	var typeVal = $(this).attr("attr_type");
	var counterId = $(this).attr("attr_counterval");	
	var mainWorkCount = $(this).attr("main_work_count");
	var innerWorkCount = $(this).attr("inner_work_count");
	$(this).attr("inner_work_count",parseInt(innerWorkCount)+1);
	var conterInnerVal = parseInt(innerWorkCount)+1
	
	$(".appendInnerBlocks"+typeVal+counterId+innerWorkCount).append(clonedInnerTemplate('clone',counterId,typeVal,mainWorkCount,innerWorkCount,conterInnerVal));
	$(".chosen-select").chosen({width:'100%'});
	//getWorkTypeList('workTypeInnerId',typeVal,mainWorkCount,innerWorkCount);
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
function clonedInnerTemplate(type,counterId,typeVal,mainWorkCount,innerWorkCount,conterInnerVal){
	var clonedInnerTemplate='';
	clonedInnerTemplate+='<div class="m_top10" >';
		clonedInnerTemplate+='<div class="col-sm-12 m_top10" >';
		clonedInnerTemplate+='<div class="pull-right removeWorkCls" attr_id="workDivId'+counterId+''+innerWorkCount+'" attr_type="self" attr_candidateid="1"><i class="glyphicon glyphicon-remove"></i></div>';
			clonedInnerTemplate+='<div class="bg_color_view row" id="workDivId'+counterId+''+innerWorkCount+'">';
				clonedInnerTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
				clonedInnerTemplate+='<div class="row m_top10" >';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK TYPE  <span class="starColor">*</span><span class="workTypeInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].workTypeId"  class="form-control chosen-select m_top10 validateCls" id="workTypeInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Work Type</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK IN COST (in Lakh) <span class="starColor">*</span><span class="appendWorkCostInner'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].estimateCost" class="form-control m_top5 height45 validateCls" id="appendWorkCostInner'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter Work Cost" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>eOFFICE-ID <span class="starColor">*</span><span class="appendEofficeInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].eOfficeId" class="form-control m_top5 height45 validateCls" id="appendEofficeInnerId'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='<div class="row m_top10">';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>LOCATION LEVEL <span class="starColor">*</span><span class="locationLevelInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+' validateCls" id="locationLevelInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Level</option>';
								clonedInnerTemplate+='<option value="3">District</option>';
								clonedInnerTemplate+='<option value="4">Constituency</option>';
								clonedInnerTemplate+='<option value="5">Mandal</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 districtInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
							clonedInnerTemplate+='<label>DISTRICT <span class="starColor">*</span><span class="districtInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select District</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 constituencyInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
							clonedInnerTemplate+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select   name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Constituency</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 mandalInnerCls'+typeVal+''+counterId+''+innerWorkCount+'" style="display:none">';
							clonedInnerTemplate+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_counterval="'+counterId+''+innerWorkCount+'" attr_type="'+typeVal+'" attr_type_change="Inner" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Mandal</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
			clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='<div class="appendInnerBlocks'+typeVal+''+counterId+''+conterInnerVal+'"></div>';
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
function getPetitionDetails(){
   var json = {
       petitionId:"1778"
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
    if(result !=null){
		buildPetitionDetails(result);
		
	}
  });
}

function buildPetitionDetails(result){
	var str='';
	
	if(result.representationType == "SELF"){
		$("#self").prop("checked",true);
	}else{
		$("#Representee").prop("checked",true);
	}
	if(result.representationType == "REPRESENTEE"){
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="font_weight text-capital f_22">Representee Details:</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-2">';
				str+='<label>VOTER ID</label>';
				str+='<input type="text"  name="voterCardNo"  value=""  class="form-control m_top10 height45" id="voterId'+result.representationType+'" placeholder="Enter Voter ID">';
				
			str+='</div>';
			str+='<div class="col-sm-1">';
				str+='<label></label>';
				str+='<input type="button" class="btn btn-success btn-md m_top20" id="getVoterDetailsId" value="Get Details" ></input>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>NAME</label>';
				str+='<input type="text"  name="name"  value="" class="form-control m_top10 height45" id="name'+result.representationType+'" placeholder="Enter Name">';
				
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>MOBILE NO</label>';
				str+='<input type="text" name="mobileNO" maxlength="10" value=""  class="form-control m_top10 height45" id="mobileNumber'+result.representationType+'" placeholder="Enter Mobile Number">';
				
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>EMAIL-ID</label>';
				str+='<input type="text" name="email"   value="" class="form-control m_top10 height45" id="emailId'+result.representationType+'" placeholder="Enter E-mail ID">';
				
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>DISTRICT</label>';
				str+='<select   name="addressVO.districtId" class="form-control chosen-select m_top10" id="district'+result.representationType+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
				
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>CONSTITUENCY</label>';
				str+='<select  name="addressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+result.representationType+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
				
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>MANDAL/MUNCI.</label>';
				str+='<select   name="addressVO.tehsilId"  class="form-control chosen-select m_top10" id="mandal'+result.representationType+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
				
			str+='</div>';
			str+='<div class="col-sm-3">';	
			str+='<label>REPRESENTEE DESIGNATION.</label>';
			str+='<select   name="representeeDesignationId"  class="form-control chosen-select m_top10" id="designation'+result.representationType+'">';
				str+='<option value="0">Select Designation</option>';
			str+='</select>';
			
		str+='</div>';
		str+='</div>';
	}
	
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
		for(var i in result.referDetailsList){
			alreadyCandidateId.push(result.referDetailsList[i].refCandidateId)
				str+='<div  style="position:relative;">';
					str+='<div class="bgColorCandidatesView">';
						str+='<div class="row">';
							str+='<div class="pull-right showRemoveIcon"><i class="glyphicon glyphicon-remove"></i></div>';
								str+='<div class="col-sm-2">';
									str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
									str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
								str+='</div>';
							
								str+='<div class="col-sm-2">';
									str+='<div class="nameAddressCss">';
										str+='<h5 class="font_weight">Name:</h5>';
										str+='<h5 class="m_top5">'+result.referDetailsList[i].name+'</h5>';
										str+='<h5 class="m_top10 font_weight">Designation</h5>';
										str+='<h5 class="text_bold m_top10">'+result.referDetailsList[i].designation+'</h5>';
									
										str+='<h5 class="m_top5">'+result.referDetailsList[i].addressVO.assemblyName+' constituency,</h5>';
										str+='<h5 class="m_top5">'+result.referDetailsList[i].addressVO.districtName+' District, '+result.referDetailsList[i].addressVO.stateName+'</h5>';
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
									str+='<h5 class="m_top5">Not Available</h5>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-4" style="margin-top:-20px;">';
								str+='<label>REFERAL LETTER</label>';
								str+='<input type="file"   name="" attr_image_tyep="refImage" id="editFileUpload'+result.referDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
							str+='</div>';
						str+='</div>';
					
							str+='<div class="alreadyUploadFilesCss">';
								str+='<h4>Already Uploaded Documents</h4>';
								str+='<div class="row">';
									for(var j in result.referDetailsList[i].fileNamesList){
										var scanCopySpl = result.referDetailsList[i].fileNamesList[j].value.split("."); 
										var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
											str+='<div class="col-sm-2">';
												if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
													str+='<div class="viewImageCss m_top20">';
												}else{
													str+='<div class="viewImageCss">';
												}
												if(scanCopyExt =="pdf"){
													str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
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
									str+='</div>';
							
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		
			str+='<div class="col-sm-12">';
				str+='<div id="candidateDetails'+result.representationType+'DivId"></div>';
			str+='</div>';
	str+='</div>';
	
		
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
			str+='<div class="pull-right">';
				str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+result.representationType+'">ADD MEMBER</button>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	//candidate Search End		
		
		str+='<div class="row m_top10">';
				str+='<div class="col-sm-8">';
					str+='<label>COMPLETE WORK DISCRIPTION <span class="starColor">*</span><span id="completeWorkNameId'+result.representationType+'"></span></label>';
					str+='<input type="text"  name="worksList[0].workName"  value="'+result.grievanceDescription+'" class="form-control m_top5 height45" id="name'+result.representationType+'" placeholder="Enter Name">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>NO OF WORKS <span class="starColor">*</span><span id="noOfWorksId'+result.representationType+'"></span></label>';
						str+='<input   name="worksList[0].noOfWorks" value="'+result.noOfWorks+'"  type="text" class="form-control m_top5 height45" id="noofWork'+result.representationType+'" placeholder="Enter No Of Work">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>WORKS IN COST (in Lakh) <span class="starColor">*</span><span id="workCostId'+result.representationType+'"></span></label>';
					str+='<input type="text"  name="worksList[0].estimateCost" value="'+result.estimateCost+'"  class="form-control m_top5 height45" id="workCost'+result.representationType+'" placeholder="Enter Work Cost">';
				str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
					str+='<h4 class="panel-title f_18"> UPLOAD WORK DOCUMENTS </h4>';
					str+='<input type="file"  attr_name="worksList[0]" name="" attr_image_tyep="projImage"  id="editprojectDocUpload'+result.representationType+'" multiple="multiple" class="m_top20"/>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="alreadyUploadFilesCss">';
			str+='<h4>Already Uploaded Documents</h4>';
			str+='<div class="row">';
				for(var i in result.fileList){
					var scanCopySpl = result.fileList[i].value.split("."); 
					var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
					
						str+='<div class="col-sm-2">';
							if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
								str+='<div class="viewImageCss m_top20">';
							}else{
								str+='<div class="viewImageCss m_top20">';
							}
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
				
					str+='</div>';
				str+='</div>';
		
			}
		str+='</div>';
	str+='</div>';
	
	//work Details
	str+='<div class="row">';
	var addWorkTypeCountMain=0;
	var innerWorkTypeCount=0;
		for(var i in result.subWorksList){
				str+='<div class="col-sm-12 m_top20" id="mainWorkDivId'+i+''+globalWorkTypeCount+'">';
					str+='<div style="border:3px solid #dddddd;padding:10px;">';
						str+='<div>';
							str+='<h3 class="panel-title f_22"></h3>';
							
							str+='<div class="row m_top10" >';
								str+='<div class="col-sm-3">';
										str+='<label>DEPARTMENT <span class="starColor">*</span><span class="WorkTypeWiseDepartmentId'+result.representationType+''+i+''+globalWorkTypeCount+'"></span></label>';
										str+='<select  name="worksList['+i+'].subWorksList['+globalWorkTypeCount+'].deptId"  class="form-control chosen-select m_top10 validateCls"  id="WorkTypeWiseDepartmentId'+result.representationType+''+i+''+globalWorkTypeCount+'" attr_main_count="'+i+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox" onChange=getPetitionSubjectList(this.value,"subjectId'+result.representationType+''+i+'","'+globalWorkTypeCount+'","change")>';
											for(var d in globalDepartmentsList){
												if(globalDepartmentsList[d].key == result.subWorksList[i].deptId){
													str+='<option value='+globalDepartmentsList[d].key+' selected>'+globalDepartmentsList[d].value+'</option>';
												}else{
													str+='<option value='+globalDepartmentsList[d].key+'>'+globalDepartmentsList[d].value+'</option>';
												}
											}
										str+='</select>';
									str+='</div>';
								
								str+='<div class="col-sm-3">';
									str+='<label>SUBJECT <span class="starColor">*</span><span class="subjectId'+result.representationType+''+i+''+globalWorkTypeCount+'"></span></label>';
									str+='<select  name="worksList['+i+'].subWorksList['+globalWorkTypeCount+'].subjectId"  class="form-control chosen-select m_top10 validateCls"  id="subjectId'+result.representationType+''+i+''+globalWorkTypeCount+'" onChange=getPetitionSubSubjectList(this.value,"subSubjectId'+result.representationType+''+i+'","'+globalWorkTypeCount+'","change") attr_main_count="'+i+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox">';
										for(var d in globalSubjectList){
												if(globalSubjectList[d].key == result.subWorksList[i].subjectId){
													str+='<option value='+globalSubjectList[d].key+' selected>'+globalSubjectList[d].value+'</option>';
												}else{
													str+='<option value='+globalSubjectList[d].key+'>'+globalSubjectList[d].value+'</option>';
												}
											}
									str+='</select>';
								str+='</div>';
								
								str+='<div class="col-sm-3">';
									str+='<label>SUB-SUBJECT <span class="starColor">*</span><span class="subSubjectId'+result.representationType+''+i+''+globalWorkTypeCount+'"></span></label>';
									str+='<select  name="worksList['+i+'].subWorksList['+globalWorkTypeCount+'].subSubjectId"  class="form-control chosen-select m_top10 validateCls"  id="subSubjectId'+result.representationType+''+i+''+globalWorkTypeCount+'" attr_main_count="'+i+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox">';
									
										str+='<option value='+result.subWorksList[i].subSubjectId+'>'+result.subWorksList[i].subSubject+'</option>';
										/* for(var d in globalSubSubjectsList){
												if(globalSubSubjectsList[d].key == result.subWorksList[i].subSubjectId){
													str+='<option value='+globalSubSubjectsList[d].key+' selected>'+globalSubSubjectsList[d].value+'</option>';
												}else{
													str+='<option value='+globalSubSubjectsList[d].key+'>'+globalSubSubjectsList[d].value+'</option>';
												}
											} */
									str+='</select>';
								str+='</div>';
											
							str+='</div>';
							
							//add work individual append
							
							for(var j in result.subWorksList[i].subWorksList){
								str+='<div class="row">';
								str+='<div class="m_top10" id="workDivId'+i+''+j+'">';
										str+='<div class="col-sm-12">';
											str+='<div class="bg_color_view">';
												str+='<h3 class="panel-title f_16 font_weight">WORK 1</h3>';
												str+='<div class="row m_top10">';
														str+='<div class="col-sm-3">';
															str+='<label> WORK TYPE <span class="starColor">*</span><span class="workTypeId'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<select  name="worksList['+i+'].subWorksList['+j+'].workTypeId"  class="form-control chosen-select m_top10 validateCls" id="workTypeId'+result.representationType+''+i+''+j+'" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																for(var w in globalWorkTypeList){
																		if(globalWorkTypeList[w].key == result.subWorksList[i].subWorksList[j].workTypeId){
																			str+='<option value='+globalWorkTypeList[w].key+' selected>'+globalWorkTypeList[w].value+'</option>';
																		}else{
																			str+='<option value='+globalWorkTypeList[w].key+'>'+globalWorkTypeList[w].value+'</option>';
																		}
																	}
															str+='</select>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<label>WORK IN COST (in Lakh) <span class="starColor">*</span><span class="appendWorkCost'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<input type="text"  value="'+result.subWorksList[i].subWorksList[j].estimateCost+'" name="worksList['+i+'].subWorksList['+j+'].estimateCost" class="form-control m_top5 height45 validateCls" id="appendWorkCost'+result.representationType+''+i+''+j+'" placeholder="Enter Work Cost" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsId'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<input type="text"  value="'+result.subWorksList[i].subWorksList[j].workName+'"  name="worksList['+i+'].subWorksList['+j+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsId'+result.representationType+''+i+''+j+'" placeholder="Enter" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<label>eOFFICE-ID <span class="starColor">*</span><span class="appendEofficeId'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<input type="text"  value="'+result.subWorksList[i].subWorksList[j].eOfficeId+'" name="worksList['+i+'].subWorksList['+j+'].eOfficeId" class="form-control m_top5 height45 validateCls" id="appendEofficeId'+result.representationType+''+i+''+j+'" placeholder="Enter" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
														str+='</div>';
												str+='</div>';
												str+='<div class="row m_top10">';
														str+='<div class="col-sm-3">';
															str+='<label>LOCATION LEVEL <span class="starColor">*</span><span class="locationLevelId'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<select  name="worksList['+i+'].subWorksList['+j+'].locationScopeId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+result.representationType+' validateCls" id="locationLevelId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																str+='<option value="0">Select Level</option>';
																if(result.subWorksList[i].subWorksList[j].locationScopeId == 5){
																	str+='<option value="3">District</option>';
																	str+='<option value="4">Constituency</option>';
																	str+='<option value="5" selected>Mandal</option>';
																}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 4){
																	str+='<option value="3">District</option>';
																	str+='<option value="4" selected>Constituency</option>';
																	str+='<option value="5">Mandal</option>';
																}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 3){
																	str+='<option value="3" selected>District</option>';
																	str+='<option value="4">Constituency</option>';
																	str+='<option value="5">Mandal</option>';
																}else{
																	str+='<option value="3">District</option>';
																	str+='<option value="4">Constituency</option>';
																	str+='<option value="5">Mandal</option>';
																}
																
																
																
															str+='</select>';
														str+='</div>';
															if(result.subWorksList[i].subWorksList[j].locationScopeId == 5){
																
																if(result.subWorksList[i].subWorksList[j].addressVO.tehsilId == null || result.subWorksList[i].subWorksList[j].addressVO.tehsilId == ""){
																	str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'" style="display:none">';
																	str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		str+='<option value="0">Select District</option>';
																	str+='</select>';
																str+='</div>';
																
																	str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+i+''+j+'" style="display:none">';
																	str+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select   name="worksList['+i+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		str+='<option value="0">Select Constituency</option>';
																	str+='</select>';
																str+='</div>';
																str+='<div class="col-sm-2 mandalCls'+result.representationType+''+i+''+j+'" style="display:none">';
																	str+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		str+='<option value="0">Select Mandal</option>';
																	str+='</select>';
																str+='</div>';
															
																}else{
																	str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'" >';
																	str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		for(var dis in globaldistrictList){
																			if(globaldistrictList[dis].id == result.subWorksList[i].subWorksList[j].addressVO.districtId){
																				str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																			}else{
																				str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																			}
																		}
																		
																	str+='</select>';
																str+='</div>';
																
																str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+i+''+j+'" >';
																str+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select   name="worksList['+i+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.assemblyId+'">'+result.subWorksList[i].subWorksList[j].addressVO.assemblyName+'</option>';
																str+='</select>';
															str+='</div>';
															
															str+='<div class="col-sm-2 mandalCls'+result.representationType+''+i+''+j+'">';
																str+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.tehsilId+'">'+result.subWorksList[i].subWorksList[j].addressVO.tehsilName+'</option>';
																str+='</select>';
															str+='</div>';
															
																}
															}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 4){
																if(result.subWorksList[i].subWorksList[j].addressVO.assemblyId == null || result.subWorksList[i].subWorksList[j].addressVO.assemblyId == ""){
																	str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'" style="display:none">';
																	str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		str+='<option value="0">Select District</option>';
																	str+='</select>';
																str+='</div>';
																
																str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+i+''+j+'" style="display:none">';
																str+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select   name="worksList['+i+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="0">Select Constituency</option>';
																str+='</select>';
															str+='</div>';
															
																}else{
																	str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'">';
																	str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		for(var dis in globaldistrictList){
																			if(globaldistrictList[dis].id == result.subWorksList[i].subWorksList[j].addressVO.districtId){
																				str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																			}else{
																				str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																			}
																		}
																	str+='</select>';
																str+='</div>';
																
																str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+i+''+j+'">';
																str+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select   name="worksList['+i+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.assemblyId+'">'+result.subWorksList[i].subWorksList[j].addressVO.assemblyName+'</option>';
																str+='</select>';
															str+='</div>';
															
																}
																
															}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 3){
																if(result.subWorksList[i].subWorksList[j].addressVO.districtId == null || result.subWorksList[i].subWorksList[j].addressVO.districtId == ""){
																	str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'" style="display:none">';
																	str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		str+='<option value="0">Select District</option>';
																	str+='</select>';
																str+='</div>';
																}else{
																	str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'">';
																	str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		for(var dis in globaldistrictList){
																			if(globaldistrictList[dis].id == result.subWorksList[i].subWorksList[j].addressVO.districtId){
																				str+='<option value="'+globaldistrictList[dis].id+'" selected>'+globaldistrictList[dis].name+'</option>';
																			}else{
																				str+='<option value="'+globaldistrictList[dis].id+'">'+globaldistrictList[dis].name+'</option>';
																			}
																		}
																	str+='</select>';
																str+='</div>';
																}
															}else{
																str+='<div class="col-sm-2 districtCls'+result.representationType+''+i+''+j+'" style="display:none">';
																str+='<label>DISTRICT <span class="starColor">*</span><span class="districtId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange validateCls" id="districtId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="0">Select District</option>';
																str+='</select>';
															str+='</div>';
															
															str+='<div class="col-sm-2 constituencyCls'+result.representationType+''+i+''+j+'" style="display:none">';
																str+='<label>CONSTITUENCY <span class="starColor">*</span><span class="constituencyId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select   name="worksList['+i+'].subWorksList['+j+'].addressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange validateCls" id="constituencyId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="0">Select Constituency</option>';
																str+='</select>';
															str+='</div>';
															
															str+='<div class="col-sm-2 mandalCls'+result.representationType+''+i+''+j+'" style="display:none">';
																str+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	str+='<option value="0">Select Mandal</option>';
																str+='</select>';
															str+='</div>';
															}
														
														
														
												str+='</div>';
											str+='</div>';
										str+='</div>';
										innerWorkTypeCount=result.subWorksList[i].subWorksList.length;
										
									str+='</div>';	
									str+='</div>';	
									}
								str+='<div class="appendInnerBlocks'+result.representationType+''+i+''+innerWorkTypeCount+'"></div>';	
								str+='<div class="row">';	
								str+='<div class="col-sm-12">';
									str+='<div class="pull-right">';
										str+='<span class="addLocationCss m_top10 pull-right cloned_Inner_Element" style="cursor:pointer;" attr_type="'+result.representationType+'" attr_counterval="'+i+'" main_work_count="0" inner_work_count="'+innerWorkTypeCount+'">ADD WORK </span>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							//append add work individual End
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			addWorkTypeCountMain=result.subWorksList.length+1;
		}	
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="appendWorkDetailsDiv'+result.representationType+'"></div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" block-clone-counter-'+result.representationType+'="'+addWorkTypeCountMain+'" style="cursor:pointer;" block-clone-'+result.representationType+'="'+globalWorkTypeCount+'" attr_type="'+result.representationType+'">ADD WORK TYPE </span></div>';
		str+='</div>';
		
			$("#"+result.representationType+"DetailsDivId").html(str);
			
			for(var i in result.referDetailsList){
				initializeEditFileUploadMainBlock(result.representationType,result.referDetailsList[i].refCandidateId);
			}
			initializeEditFileProjDoc(result.representationType);
			$(".chosen-select").chosen();
			$(".fancyboxView").fancybox();
}
function openDoc(docmnt){
	 window.open(docmnt);
}