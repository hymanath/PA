var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});

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
var globalFileUpload='';
var globalWorkFileUpload='';
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
function getPanchayatsByTehsilId(levelVal,counterId,typeVal,typeChange){
	    $("#panchayatId"+typeVal+counterId).html('');
		if(typeChange == "Inner"){
			$("#panchayatInnerId"+typeVal+counterId).html('');
		}
	    
		
	  var searchType="all";
	  var searchId=0;
			if(typeVal=="popup"){
				searchType = "refCandidate";
				searchId = $('#designationsId').val();
			}else if(counterId !="" && parseInt(counterId)>=0){
				 $("#panchayatId"+typeVal+counterId).html('');
			}
		
	  var json = {
		  constituencyId:levelVal,
		  searchType:"all",
		  searchId:searchId
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
			 $("#panchayatId"+typeVal+counterId).append('<option value="0">Select Mandal</option>');
			 if(typeChange == "Inner"){
				 $("#panchayatInnerId"+typeVal+counterId).append('<option value="0">Select Mandal</option>');
			 }
			for(var i in result){
				var tehsilId = result[i].key;
				var levelId = tehsilId.toString().substr(1, 5);
				$("#panchayatId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				if(typeChange == "Inner"){
					$("#panchayatInnerId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		$("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
		if(typeChange == "Inner"){
			$("#panchayatInnerId"+typeVal+counterId).trigger('chosen:updated');
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
	
	$(".candidateDetails"+typeVal+"DivId").append($("#candidatesAppendDiv"+candidateId).html());
	
	$(".candidateDetails"+typeVal+"DivId").find(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-3").addClass("col-sm-2");
	$(".candidateDetails"+typeVal+"DivId").find("#fileUpload"+typeVal+candidateId).show();
	
	$("#fileUpload"+typeVal+candidateId).append('<div class="col-sm-4" style="margin-top:-20px;"><label>REFERAL LETTER</label><input type="file"   attr_name="referList['+refCandCount+']" name="" attr_image_tyep="refImage"  id="editFileUpload'+candidateId+''+typeVal+'" multiple="multiple" class=""/></div>');
	if(representeeType =='SELF'){
		$(".candidateDetails"+typeVal+"DivId").append('<input type="hidden" id="petitionRef'+refCandCount+'" name="refCandidateId" value="'+candidateId+'" />');	
	}
	else if(representeeType =='REPRESENTEE'){
		$(".candidateDetails"+typeVal+"DivId").append('<input type="hidden" id="petitionRef'+refCandCount+'" name="referList['+refCandCount+'].refCandidateId" value="'+candidateId+'" />');	
	}
	refCandCount=refCandCount+1;
	$(this).parent().find(".representation-selected").addClass("display_block");
	$("#candidatesAppendDiv"+candidateId).find("#candidate"+typeVal+candidateId).removeClass("candidateAddedView");
	
	$(".candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").removeClass("candidateAddedView");
	$(".candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").css("cursor","auto");
	$(".showRemoveIcon").attr("attr_candidateId",candidateId)
	$(".candidateDetails"+typeVal+"DivId").find(".showRemoveIcon").show();
	initializeEditFileUploadMainBlock(typeVal,candidateId);
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
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(".candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").attr("attr_candidateId");
	
	$(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-2").addClass("col-sm-3");
	//$("#candidateDetails"+typeVal+"DivId").find(".showRemoveIcon").hide();
	$(".candidateDetails"+typeVal+"DivId").find("#candidate"+typeVal+candidateId).remove();
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
		
	}else if(levelVal == 6){
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
			$(".panchayatCls"+typeVal+counterId).show();
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
			$(".panchayatInnerCls"+typeVal+counterId).show();
		}
	}
		else{
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
$(document).on("change",".mandalLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	var typeChange = $(this).attr("attr_type_change");
	getPanchayatsByTehsilId(levelVal,counterId,typeVal,typeChange);
	
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
		getSubjectPetitionsDepartmentList(typeVal,counterappendId,blockId,"change");
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
	getWorkTypeList('workTypeInnerId',typeVal,counterId,innerWorkCount,"change");
});

function clonedTemplate(blockId,type,counterId,typeVal,counterappendId){
	
	var clonedTemplate='';
	// here for evary new work  adding based on diff dept /subject/sub subject wise the subworksList should be start from ZERO ,but globalWorkTypeCount is same
	//var tempcounterId = counterId;
	//counterId = 0;
	var tempcounterId=1;
	clonedTemplate+='<div class="col-sm-12" cloned_block_'+typeVal+'='+counterappendId+' id="mainWorkDivId'+counterappendId+''+blockId+'">';
	clonedTemplate+='<div class="pull-right removeWorkCls" attr_id="mainWorkDivId'+counterappendId+''+blockId+'" attr_type="'+typeVal+'" attr_candidateid="1"><i class="glyphicon glyphicon-remove"></i></div>';
		clonedTemplate+='<div style="border:3px solid #dddddd;padding:10px;">';
			clonedTemplate+='<div  cloned_block_'+typeVal+'='+counterappendId+'>';
				//clonedTemplate+='<h3 class="panel-title f_22">WORK TYPE '+counterId+'</h3>';
				
				clonedTemplate+='<div class="row m_top10" >';
				
					clonedTemplate+='<div class="col-sm-3">';
							clonedTemplate+='<label>DEPARTMENT <span class="starColor">*</span><span class="WorkTypeWiseDepartmentId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
							clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].deptId"  class="form-control chosen-select m_top10 validateCls "  id="WorkTypeWiseDepartmentId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
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
							//clonedTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK TYPE <span class="starColor">*</span><span class="workTypeId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<select  name="worksList['+counterappendId+'].subWorksList['+blockId+'].workTypeId"  class="form-control chosen-select m_top10 validateCls" id="workTypeId'+typeVal+''+counterappendId+''+blockId+'" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
											clonedTemplate+='<option value="0">Select Work Type</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK IN COST  <span class="starColor">*</span><span class="appendWorkCost'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+blockId+'].estimateCost" class="form-control m_top5 height45 validateCls amountCls" id="appendWorkCost'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter Work Cost" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'"  onkeyUp="checkIsNumber(this.id,this.value);validateAmount(this.value,this.id,\''+typeVal+'\')">';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'"></span></label>';
										clonedTemplate+='<input type="text"  name="worksList['+counterappendId+'].subWorksList['+blockId+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsId'+typeVal+''+counterappendId+''+blockId+'" placeholder="Enter" attr_main_count="'+counterappendId+'" attr_inner_count="'+blockId+'">';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>eOFFICE-ID </label>';
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
			clonedInnerTemplate+='<div class="bg_color_view row " id="workDivId'+counterId+''+innerWorkCount+'">';
				//clonedInnerTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
				clonedInnerTemplate+='<div class="row m_top10" >';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK TYPE  <span class="starColor">*</span><span class="workTypeInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<select  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].workTypeId"  class="form-control chosen-select m_top10 validateCls validateInnerCls" id="workTypeInnerId'+typeVal+''+counterId+''+innerWorkCount+'" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
								clonedInnerTemplate+='<option value="0">Select Work Type</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK IN COST <span class="starColor">*</span><span class="appendWorkCostInner'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].estimateCost" class="form-control m_top5 height45 validateCls amountCls" id="appendWorkCostInner'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter Work Cost" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'"  onkeyUp="checkIsNumber(this.id,this.value);;validateAmount(this.value,this.id,\''+typeVal+'\')">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'"></span></label>';
							clonedInnerTemplate+='<input type="text"  name="worksList['+counterId+'].subWorksList['+innerWorkCount+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsInnerId'+typeVal+''+counterId+''+innerWorkCount+'" placeholder="Enter" attr_main_count="'+counterId+'" attr_inner_count="'+innerWorkCount+'">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>eOFFICE-ID </label>';
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
	//$("#"+result.representationType+"DetailsDivId").html(str);
   var json = {
       petitionId:"1872"
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
	globalFileUpload='';
	globalWorkFileUpload='';
	if(result.representationType == "SELF"){
		$("#self").prop("checked",true);
		$(".RepresenteeHideShow").hide();
		$(".selfHideShow").show();
	}else{
		$("#Representee").prop("checked",true);
		$(".selfHideShow").hide();
		$(".RepresenteeHideShow").show();
	}
	$('#endorsmentNo').val(result.endorsmentNo);
	$('#endorsmentDate').val(result.endorsmentDate);
	$('#representationDate').val(result.representationdate);
	$('#representationType').val(result.representationType);

	if(result.representationType == "REPRESENTEE"){
		for(var i in result.representeeDetailsList){
			str+='<div class="row ">';

				str+='<div class="col-sm-3">';
					str+='<h6>VOTER CARD NO</h6>';
					str+='<input type="text"   name="voterCardNo"  value="'+result.representeeDetailsList[i].voterCardNo+'"  class="form-control m_top10 height45" id="voterId'+result.representationType+'" placeholder="Enter Voter ID">';
				str+='</div>';
				/*str+='<div class="col-sm-1">';
					str+='<label></label>';
					str+='<input type="button" class="btn btn-warning btn-md m_top10" id="getVoterDetailsId" attr_type="'+result.representationType+'" value="Get Details" ></input>';
				str+='</div>';
				*/
				str+='<div class="col-sm-3">';
					str+='<h6>NAME</h6>';
					str+='<input type="text"  name="name"  value="'+result.representeeDetailsList[i].name+'" class="form-control m_top10 height45" id="name'+result.representationType+'" placeholder="Enter Name">';
str+='<span class="ErrCls" id="nameErr'+result.representationType+'"></span>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h6>MOBILE NO</h6>';
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
					str+='<h6>DISTRICT</h6>';
					str+='<select   name="addressVO.districtId" class="form-control chosen-select m_top10" id="district'+result.representationType+'">';
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
					str+='<h6>CONSTITUENCY</h6>';
					str+='<select  name="addressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+result.representationType+'">';
						for(var c in result.representeeDetailsList[0].addressVO.constituencyList){
							if(result.representeeDetailsList[0].addressVO.constituencyList[c].key == result.representeeDetailsList[i].addressVO.assemblyId){
								str+='<option value="'+result.representeeDetailsList[0].addressVO.constituencyList[c].key+'">'+result.representeeDetailsList[0].addressVO.constituencyList[c].value+'</option>';
							}else{
								str+='<option value="'+result.representeeDetailsList[0].addressVO.constituencyList[c].key+'">'+result.representeeDetailsList[0].addressVO.constituencyList[c].value+'</option>';
							}
						}
						
					str+='</select>';
					
				str+='</div>';
				str+='<div class="col-sm-3">';	
					str+='<h6>MANDAL/MUNCI.</h6>';
					str+='<select   name="addressVO.tehsilId"   class="form-control chosen-select m_top10" id="mandal'+result.representationType+'">';
						for(var c in result.representeeDetailsList[0].addressVO.mandalsList){
							if(result.representeeDetailsList[0].addressVO.mandalsList[c].key == result.representeeDetailsList[i].addressVO.tehsilId){
								str+='<option value="'+result.representeeDetailsList[0].addressVO.mandalsList[c].key+'">'+result.representeeDetailsList[0].addressVO.mandalsList[c].value+'</option>';
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
						for(var c in result.representeeDetailsList[0].addressVO.panchaytsList){
							if(result.representeeDetailsList[0].addressVO.panchaytsList[c].key == 233269){
								str+='<option value="'+result.representeeDetailsList[0].addressVO.panchaytsList[c].key+'">'+result.representeeDetailsList[0].addressVO.panchaytsList[c].value+'</option>';
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
				str+='<h6>REPRESENTEE DESIGNATION.</h6>';
				str+='<select   name="representeeDesignationId"  class="form-control chosen-select m_top10" id="designation'+result.representationType+'">';
				if(globalDesignationList != null && globalDesignationList.length>0){
					for(var dig in globalDesignationList){
						if(globalDesignationList[dig].id == result.representeeDetailsList[i].addressVO.districtId){
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
				str+='<input type="hidden" id="existingPetitionId'+result.representationType+'" value="'+result.petitionId+'" name="existingPetitionId"/>';
			str+='</div>';
			str+='</div>';
		}
		
	}
	
	str+='<div class="row">';
		
		for(var i in result.referDetailsList){
			globalFileUpload = result.referDetailsList;
			alreadyCandidateId.push(result.referDetailsList[i].refCandidateId)
				str+='<div class="col-sm-12">';
				str+='<div class="candidateDetails'+result.representationType+'DivId">';
				
					str+='<div class="bgColorCandidatesView" attr_type='+result.representationType+' attr_candidateId='+result.referDetailsList[i].refCandidateId+' id="candidate'+result.representationType+''+result.referDetailsList[i].refCandidateId+'">';
					str+='<div id="candidatesAppendDiv'+result.referDetailsList[i].refCandidateId+'" style="position:relative;">';
						str+='<div class="row">';
						if(result.representationType == "REPRESENTEE" || result.representationType == "REPRESENT"){
							str+='<input id="petitionRef0" class="refCandidatesCls" name="refCandidateId" value="'+result.referDetailsList[i].refCandidateId+'" type="hidden">';
						}else{
							str+='<input id="petitionRef0" class="refCandidatesCls" name="referList['+i+'].refCandidateId" value="'+result.referDetailsList[i].refCandidateId+'" type="hidden">';
						}
						
							str+='<div class="pull-right showRemoveIcon" attr_type='+result.representationType+'><i class="glyphicon glyphicon-remove"></i></div>';
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
									
										str+='<h5 class="m_top5">'+result.referDetailsList[i].addressVO.assemblyName+' CONSTITUENCY,</h5>';
										str+='<h5 class="m_top5">'+result.referDetailsList[i].addressVO.districtName+' DISTRICT, '+result.referDetailsList[i].addressVO.stateName+'</h5>';
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
									if(result.referDetailsList[i].candidateAddressVO != null && result.referDetailsList[i].candidateAddressVO.stateName != null && result.referDetailsList[i].candidateAddressVO.stateName.length>0){
										
										
										str+='<h5 class="m_top5">CONSTITUENCY  : '+result.referDetailsList[i].candidateAddressVO.assemblyName+' </h5>';
										str+='<h5 class="m_top5"> DISTRICT  : '+result.referDetailsList[i].candidateAddressVO.districtName+' ,</h5> <h5  class="m_top5"> STATE : '+result.referDetailsList[i].candidateAddressVO.stateName+'</h5>';
									}else{
										str+='<h5 class="m_top5">Not Available</h5>';
									}
									
										
									
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-4" style="margin-top:-20px;">';
								str+='<label>REFERAL LETTER</label>';
								if(result.referDetailsList[i].fileNamesList !=null && result.referDetailsList[i].fileNamesList.length >0){
									str+='<input type="file"  attr_file_existing_size="'+result.referDetailsList[i].fileNamesList.length+'" attr_name="referList[0]" name="[]"  attr_image_tyep="refImage" id="editFileUpload'+result.referDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
								}else{
									str+='<input type="file"  attr_file_existing_size="0" attr_name="referList[0]" name="[]"  attr_image_tyep="refImage" id="editFileUpload'+result.referDetailsList[i].refCandidateId+''+result.representationType+'" multiple="multiple" class=""/>';
								}
								
								str+='<div class="viewLetterCss">';
									str+='<h5 class="font_weight viewDocumentsModalCls" style="color:#fff;" attr_candidateId="'+result.referDetailsList[i].refCandidateId+'" attr_name="'+result.referDetailsList[i].name+'" attr_type ="'+result.representationType+'" attr_view_type="candidateDoc"><i class="fa fa-file-text" aria-hidden="true" style="font-size: 16px;"></i>  VIEW REFERRAL LETTER</h5>';
								str+='</div>';	
								
							str+='</div>';
						str+='</div>';
					
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		
			str+='<div class="col-sm-12">';
				str+='<div class="candidateDetails'+result.representationType+'DivId"></div>';
			str+='</div>';
	str+='</div>';
	
		if(result.representationType == "SELF"){
			if(!$(".candidateDetails"+result.representationType+"DivId").find(".bgColorCandidatesView")){
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+result.representationType+'">ADD MEMBER</button>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
			
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
		
		str+='<div class="row m_top10">';
				str+='<div class="col-sm-8">';
					str+='<label>COMPLETE WORK DESCRIPTION <span class="starColor">*</span><span id="completeWorkNameId'+result.representationType+'"></span></label>';
					str+='<input type="text"  name="worksList[0].workName"  value="'+result.grievanceDescription+'" class="form-control m_top5 height45" id="workName'+result.representationType+'" placeholder="Enter Name">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>NO OF WORKS <span class="starColor">*</span><span id="noOfWorksId'+result.representationType+'"></span></label>';
						str+='<input   name="worksList[0].noOfWorks" value="'+result.noOfWorks+'"  type="text" class="form-control m_top5 height45" id="noofWork'+result.representationType+'" placeholder="Enter No Of Work"  onkeyUp="checkIsNumber(this.id,this.value);">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>WORKS IN COST (in Lakh) <span class="starColor">*</span><span id="workCostId'+result.representationType+'"></span></label>';
					str+='<input type="text"  name="worksList[0].estimateCost" value="'+result.estimateCost+'"  class="form-control m_top5 height45" id="workCost'+result.representationType+'" placeholder="Enter Work Cost"  onkeyUp="checkIsNumber(this.id,this.value);">';
				str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-8">';
					str+='<h4 class="panel-title f_18"> UPLOAD WORK DOCUMENTS </h4>';
					if(result.fileList !=null && result.fileList.length >0){
						str+='<input type="file"  attr_file_existing_size="'+result.fileList.length+'" attr_name="worksList[0]" name="[]"  attr_image_tyep="projImage" id="editprojectDocUpload'+result.representationType+'" multiple="multiple" class=""/>';
					}else{
						str+='<input type="file"  attr_file_existing_size="0" attr_name="worksList[0]" name="[]"  attr_image_tyep="projImage" id="editprojectDocUpload'+result.representationType+'" multiple="multiple" class=""/>';
					}

				//str+='<input type="file"  attr_name="worksList[0]" name="" attr_image_tyep="projImage"  id="editprojectDocUpload'+result.representationType+'" multiple="multiple" class="m_top20"/>';

			str+='</div>';
			str+='<div class="col-sm-4">';
				str+='<div class="viewLetterCss" style="margin-top: 40px; height: 115px;cursor:pointer;">';
					str+='<h4 class="font_weight viewDocumentsModalCls" style="color:#fff;" attr_name="WORK DOCUMENTS" attr_type ="'+result.representationType+'" attr_view_type="mainDoc"><i class="fa fa-file-text" aria-hidden="true" style="font-size: 25px;"></i><br/>  <p class="m_top20" style="color:#fff;">VIEW WORK DOCUMENTS</p></h4>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		globalWorkFileUpload = result.fileList
		
	//work Details
	str+='<div class="row">';
	var addWorkTypeCountMain=0;
	var innerWorkTypeCount=0;
		for(var i in result.subWorksList){
			
				str+='<div class="col-sm-12 m_top20" id="mainWorkDivId'+i+''+globalWorkTypeCount+'">';
				if(i !=0){
					str+='<div class="pull-right removeWorkCls" attr_id="mainWorkDivId'+i+''+globalWorkTypeCount+'" attr_type="self'+result.representationType+'"><i class="glyphicon glyphicon-remove"></i></div>';
				}
					
					
					str+='<div style="border:3px solid #dddddd;padding:10px;">';
						str+='<div>';
							str+='<h3 class="panel-title f_22"></h3>';
							
							str+='<div class="row m_top10" >';
								str+='<div class="col-sm-3">';
										str+='<label>DEPARTMENT : <span class="starColor">*</span><span class="WorkTypeWiseDepartmentId'+result.representationType+''+i+''+globalWorkTypeCount+'"></span></label>';
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
											for(var sd in result.subWorksList[i].subWorksList[0].subjectsList){
												if(result.subWorksList[i].subWorksList[0].subjectsList[sd].key == result.subWorksList[i].subjectId){
													str+='<option value='+result.subWorksList[i].subWorksList[0].subjectsList[sd].key+' selected>'+result.subWorksList[i].subWorksList[0].subjectsList[sd].value+'</option>';
												}else{
													str+='<option value='+result.subWorksList[i].subWorksList[0].subjectsList[sd].key+'>'+result.subWorksList[i].subWorksList[0].subjectsList[sd].value+'</option>';
												}
											}
												
											
									str+='</select>';
								str+='</div>';
								
								str+='<div class="col-sm-3">';
									str+='<label>SUB-SUBJECT <span class="starColor">*</span><span class="subSubjectId'+result.representationType+''+i+''+globalWorkTypeCount+'"></span></label>';
									str+='<select  name="worksList['+i+'].subWorksList['+globalWorkTypeCount+'].subSubjectId"  class="form-control chosen-select m_top10 validateCls"  id="subSubjectId'+result.representationType+''+i+''+globalWorkTypeCount+'" attr_main_count="'+i+'" attr_inner_count="'+globalWorkTypeCount+'" attr_select_type="selectbox">';
										for(var sd in result.subWorksList[i].subWorksList[0].subSubjectsList){
												if(result.subWorksList[i].subWorksList[0].subSubjectsList[sd].key == result.subWorksList[i].subSubjectId){
													str+='<option value='+result.subWorksList[i].subWorksList[0].subSubjectsList[sd].key+' selected>'+result.subWorksList[i].subWorksList[0].subSubjectsList[sd].value+'</option>';
												}else{
													str+='<option value='+result.subWorksList[i].subWorksList[0].subSubjectsList[sd].key+'>'+result.subWorksList[i].subWorksList[0].subSubjectsList[sd].value+'</option>';
												}
											}
									str+='</select>';
								str+='</div>';
											
							str+='</div>';
							
							//add work individual append
							
							for(var j in result.subWorksList[i].subWorksList){
								str+='<div class="row">';
								
								str+='<div class="m_top10" id="workDivId'+i+''+j+'">';
										str+='<div class="col-sm-12">';
											if(j !=0){
												str+='<div class="pull-right removeWorkCls" attr_id="workDivId'+i+''+j+'" attr_type="self" attr_candidateid="1"><i class="glyphicon glyphicon-remove"></i></div>';
											}
											str+='<div class="bg_color_view">';
												//str+='<h3 class="panel-title f_16 font_weight">WORK 1</h3>';
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
															str+='<label>WORK IN COST <span class="starColor">*</span><span class="appendWorkCost'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<input type="text"  value="'+result.subWorksList[i].subWorksList[j].estimateCost+'" name="worksList['+i+'].subWorksList['+j+'].estimateCost" class="form-control m_top5 height45 validateCls amountCls" id="appendWorkCost'+result.representationType+''+i+''+j+'" placeholder="Enter Work Cost" attr_main_count="'+i+'" attr_inner_count="'+j+'"  onkeyUp="checkIsNumber(this.id,this.value);;validateAmount(this.value,this.id,\''+result.representationType+'\')">';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<label>WORK DETAILS <span class="starColor">*</span><span class="appendWorkDetailsId'+result.representationType+''+i+''+j+'"></span></label>';
															str+='<input type="text"  value="'+result.subWorksList[i].subWorksList[j].workName+'"  name="worksList['+i+'].subWorksList['+j+'].grievanceDescription" class="form-control m_top5 height45 validateCls" id="appendWorkDetailsId'+result.representationType+''+i+''+j+'" placeholder="Enter" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<label>eOFFICE-ID </label>';
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
																	str+='<option value="5" selected>Mandal/Munci./Corp.</option>';
																	str+='<option value="6">Panchayat</option>';
																}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 4){
																	str+='<option value="3">District</option>';
																	str+='<option value="4" selected>Constituency</option>';
																	str+='<option value="5">Mandal/Munci./Corp.</option>';
																	str+='<option value="6">Panchayat</option>';
																}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 3){
																	str+='<option value="3" selected>District</option>';
																	str+='<option value="4">Constituency</option>';
																	str+='<option value="5">Mandal/Munci./Corp.</option>';
																	str+='<option value="6">Panchayat</option>';
																}else if(result.subWorksList[i].subWorksList[j].locationScopeId == 6){
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
																
																
																
															str+='</select>';
														str+='</div>';
														if(result.subWorksList[i].subWorksList[j].locationScopeId == 6){
																
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
																
																str+='<div class="col-sm-2 panchayatCls'+result.representationType+''+i+''+j+'" style="display:none">';
																	str+='<label>PANCHAYAT<span class="starColor">*</span><span class="panchayatId'+result.representationType+''+i+''+j+'"></span></label>';
																	str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.panchayatId"  class="form-control chosen-select m_top10 panchayatLevelChange validateCls" id="panchayatId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																		str+='<option value="0">Select Panchayat</option>';
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
																	for(var c in result.subWorksList[i].subWorksList[j].addressVO.constituencyList){
																		if(result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key == result.subWorksList[i].subWorksList[j].addressVO.assemblyId){
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																		}else{
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key+'">'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																		}
																	}
																	
																str+='</select>';
															str+='</div>';
															
															str+='<div class="col-sm-2 mandalCls'+result.representationType+''+i+''+j+'">';
																str+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	for(var c in result.subWorksList[i].subWorksList[j].addressVO.mandalsList){
																		if(result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].key == result.subWorksList[i].subWorksList[j].addressVO.tehsilId){
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																		}else{
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].key+'">'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																		}
																	}
																	
																str+='</select>';
															str+='</div>';
															
															str+='<div class="col-sm-2 panchayatCls'+result.representationType+''+i+''+j+'">';
																str+='<label>PANCHAYAT<span class="starColor">*</span><span class="panchayatId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.panchayatId"  class="form-control chosen-select m_top10 panchayatLevelChange validateCls" id="panchayatId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	for(var c in result.subWorksList[i].subWorksList[j].addressVO.panchaytsList){
																		if(result.subWorksList[i].subWorksList[j].addressVO.panchaytsList[c].key == result.subWorksList[i].subWorksList[j].addressVO.panchayatId){
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.panchaytsList[c].key+'" selected>'+result.subWorksList[i].subWorksList[j].addressVO.panchaytsList[c].value+'</option>';
																		}else{
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.panchaytsList[c].key+'">'+result.subWorksList[i].subWorksList[j].addressVO.panchaytsList[c].value+'</option>';
																		}
																	}
																	
																str+='</select>';
															str+='</div>';
															
															
																}
															}
															
															else if(result.subWorksList[i].subWorksList[j].locationScopeId == 5){
																
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
																	for(var c in result.subWorksList[i].subWorksList[j].addressVO.constituencyList){
																		if(result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key == result.subWorksList[i].subWorksList[j].addressVO.assemblyId){
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																		}else{
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key+'">'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																		}
																	}
																	
																str+='</select>';
															str+='</div>';
															
															str+='<div class="col-sm-2 mandalCls'+result.representationType+''+i+''+j+'">';
																str+='<label>MANDAL/MUNCI. <span class="starColor">*</span><span class="mandalId'+result.representationType+''+i+''+j+'"></span></label>';
																str+='<select  name="worksList['+i+'].subWorksList['+j+'].addressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange validateCls" id="mandalId'+result.representationType+''+i+''+j+'" attr_counterval="'+i+''+j+'" attr_type="'+result.representationType+'" attr_type_change="main" attr_main_count="'+i+'" attr_inner_count="'+j+'">';
																	for(var c in result.subWorksList[i].subWorksList[j].addressVO.mandalsList){
																		if(result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].key == result.subWorksList[i].subWorksList[j].addressVO.tehsilId){
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].key+'" selected>'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																		}else{
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].key+'">'+result.subWorksList[i].subWorksList[j].addressVO.mandalsList[c].value+'</option>';
																		}
																	}
																	
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
																	for(var c in result.subWorksList[i].subWorksList[j].addressVO.constituencyList){
																		if(result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key == result.subWorksList[i].subWorksList[j].addressVO.assemblyId){
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key+'" selected>'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																		}else{
																			str+='<option value="'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].key+'">'+result.subWorksList[i].subWorksList[j].addressVO.constituencyList[c].value+'</option>';
																		}
																	}
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
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
				//str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+typeVal+'">ADD REFERRAL</button>';
				str+='<div class="col-sm-12 m_top20"><span class="addLocationCss m_top20 saveRepresentRequestDetails" style="cursor:pointer;background-color:green;" attr_type="'+result.representationType+'">UPDATE DETAILS</span><span id="savingDetailsSpinner"></span><span class="col-sm-offset-4" id="statusMsgAppntReqt"></span></div>';
		str+='</div>';
	str+='</div>';
		
			$("#"+result.representationType+"DetailsDivId").html(str);
			
			for(var i in result.referDetailsList){
				initializeEditFileUploadMainBlock(result.representationType,result.referDetailsList[i].refCandidateId);
			}
			initializeEditFileProjDoc(result.representationType);
			$(".chosen-select").chosen();
			
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
	  $("#constituencyREPRESENTEE").append('<option value="0">Select Constituency</option>');	
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
	  $("#mandalREPRESENTEE").append('<option value="0">Select Mandal</option>');	
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


function getPetitionDesignationLst(typeVal){

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
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseFloat(value) <=0){
				$('#Err'+fieldId+'').html("Invalid estimation cost entered. Please check once.");
				return;
			}else{
				enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
			}
		}
	});
	
	if(enteredAmount>estimationAmount){
		$('#Err'+fieldId+'').html("Total estimation cost reached. Please check once.");
		$('#'+fieldId+'').val('');
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
			if(result.imageBase64String != null && result.imageBase64String.length > 0 && result.imageBase64String !='null'){
				$("#repImagePathId"+typeVal).val("http://www.mytdp.com/images/cadre_images/"+result.imageBase64String);
			  }else  if(result.imagePath != null && result.imagePath.length > 0 && result.imagePath !='null'){
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
			 $("#districtREPRESENTEE").append('<option value="0">Select District</option>');
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
	  $("#constituencyREPRESENTEE").append('<option value="0">Select Constituency</option>');	
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
	  $("#mandalREPRESENTEE").append('<option value="0">Select Mandal</option>');	
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
	  $("#panchayatREPRESENTEE").append('<option value="0">Select Panchayat</option>');	
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
$(document).on("click",".removeWorkCls",function(){
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
	completeWorkName = $("#workName"+typeVal).val();
	noofWorks = $("#noofWork"+typeVal).val();
	workCost = $("#workCost"+typeVal).val();
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
		}else{
			$('#nameErr'+typeVal+'').html("");
		}
		if(repMobileNo == undefined || repMobileNo == "undefined" || repMobileNo.trim() == '' || repMobileNo == null){
			$('#mobileNumberErr'+typeVal+'').html("<h5 style='color:red;'>Please enter  mobile no.</h5>");
			$('#saveButtonId').show();
		}else if(parseInt(repMobileNo.trim().length) <10 || parseInt(repMobileNo.trim().length) >10 || parseInt(repMobileNo.trim().length) !=10){
			$('#mobileNumberErr'+typeVal+'').html("<h5 style='color:red;'>Please enter valid mobile no.</h5>");
			$('#saveButtonId').show();
		}else{
			$('#mobileNumberErr'+typeVal+'').html("");
		}
		if(repEmail != undefined && repEmail != "undefined" && repEmail.trim() != '' && repEmail != null){
			 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			 if (!filter.test(repEmail.trim())) {
				$('#emailIdErr'+typeVal+'').html("<h5 style='color:red;'>Please enter valid email address.</h5>");
				$('#saveButtonId').show();
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
		if(repPanchayatId == 0 || repPanchayatId == null || repPanchayatId == ''){
			$("#panchayatErr"+typeVal+'').html("<h5 style='color:red;'>Please select  panchayat.</h5>");
			$('#saveButtonId').show();
			flag = false;
		}else{
			$("#panchayatErr"+typeVal+'').html("");
		}
		if(repdesignation == 0 || repdesignation == null || repdesignation == ''){
			$("#designationErr"+typeVal+'').html("<h5 style='color:red;'>Please select  designation.</h5>");
			$('#saveButtonId').show();
			flag = false;
		}else{
			$("#designationErr"+typeVal+'').html("");
		}		
	}
	
	/*var totalRefCount=0;
	$('#refCandidatesErr').html('');
	$('.refCandidatesCls').each(function(){
		var value = $(this).val();
		
		if(value != undefined && value != "undefined" && value.trim() != '' && value != null && parseInt(value)>0)
			totalRefCount = parseInt(totalRefCount)+1;
	});
	
	if(totalRefCount == undefined || totalRefCount == "undefined" || totalRefCount == null || parseInt(totalRefCount) == 0 ){
		flag = false;
		if(typeVal =='represent' || typeVal =='representee')
			$('#refCandidatesErr').html('Please add atleast one referral details.');
		else 
			$('#refCandidatesErr').html('Please add Self member details.');
		
	}*/
	
	if(completeWorkName == undefined || completeWorkName == "undefined" || completeWorkName.trim() == '' || completeWorkName == null){
		$("#completeWorkNameId"+typeVal).html("<h5 style='color:red;'>Please enter work name</h5>");
		$('#saveButtonId').show();
		flag = false;
		//return;
	}else{
		$("#completeWorkNameId"+typeVal).html("");
	}
	if(noofWorks == undefined || noofWorks == "undefined" || noofWorks === undefined || noofWorks.trim() == '' || noofWorks == null){
		$("#noOfWorksId"+typeVal).html("<h5 style='color:red;'>Please enter no of works</h5>");
		$('#saveButtonId').show();
		flag = false;
		//return;
	}else{
		$("#noOfWorksId"+typeVal).html("");
	}
	if(workCost == undefined || workCost == "undefined" || workCost === undefined || workCost.trim() == '' || workCost == null){
		$("#workCostId"+typeVal).html("<h5 style='color:red;'>Please enter work cost</h5>");
		$('#saveButtonId').show();
		flag = false;
		//return;
	}else{
		$("#workCostId"+typeVal).html("");
	}
	/*
	var estimationWorksCount = $('#noofWork'+typeVal+'').val();
	if((parseInt(estimationWorksCount)>parseInt(globalInnerWorksCount)) || (parseInt(estimationWorksCount)<parseInt(globalInnerWorksCount))){ 
		alert("Max no of works data not matched. Please check once.");
		flag = false;
	}
	*/
	var enteredAmount =parseFloat(0.0);
	var estimationAmount= parseFloat($('#workCost'+typeVal+'').val());
	$(".amountCls").each(function(){
		var value = $(this).val();
		if(value!= null && value.length>0){
			if(parseFloat(value) <=0){	
				$('#Err'+fieldId+'').html("Invalid estimation cost entered. Please check once.");			
				flag = false;
			}else{
				enteredAmount = parseFloat(enteredAmount)+parseFloat(value);
			}
		}
	});
	if((enteredAmount<estimationAmount) || (enteredAmount>estimationAmount)){
		alert("Work wise total estimation cost not matched. Please check once.");
		flag = false;
	}
	

	
	$(".validateCls").each(function(){
		
		
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
/*
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
				}*/
				/*if(panchayatId == 0 || panchayatId == null || panchayatId == ''){
					$(".panchayatId"+typeVal+mainCount+innerCount).html("<h5 style='color:red;'>Please Select Panchayat</h5>");
					flag = false;
				}else{
					$(".panchayatId"+typeVal+mainCount+innerCount).html("");
				}*/
			}
		}
	});

	$(".validateInnerCls").each(function(){
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
			
			if(appendWorkCostInner == 0 || appendWorkCostInner == null || appendWorkCostInner == ''){
				$(".appendWorkCostInner"+typeVal+mainCountIn+innerCountIn).html("<h5 style='color:red;'>Please Enter Work Cost</h5>");
				flag = false;
			}else{
				$(".appendWorkCostInner"+typeVal+mainCountIn+innerCountIn).html("");
			}
			
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
		return;
	}

	//$("#savingDetailsSpinner").html(spinner)
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
	
	//console.log(formData);
	//return;
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
					  
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); 
						window.location.reload(); 
						$(".defaultCheckCls").prop("checked",true)},6000);
						 
				  }else{
					  $('#saveButtonId').show();
					  $("#statusMsgAppntReqt").html("<center><h3 style='color: red;margin-top:-25px;'>Application Failed..Try Later</h3></center>").fadeOut(4000);
					  setTimeout(function () {
						 
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				  }
				}else{
					  $('#saveButtonId').show();
					setTimeout(function () {
						 $("#statusMsgAppntReqt").html("<center><h3 style='color: red;margin-top:-25px;'>Application Failed..Try Later</h3></center>").fadeOut(4000);
						}, 500);
						setTimeout(function() {$('html, body').animate({scrollTop:0}, 5000); },5000);
				 }
				 
				
			},
			error: function(request,error) { 
				$("#savingDetailsSpinner").html('')
				alert("Error occured while updating details.Pelase check once any required data missing to fill.Then try again.");	
				$('#saveButtonId').show();				
			}
     });	 

});
$(document).on("click",".viewDocumentsModalCls",function(){
	$("#candidateUploadFileModelDivId").modal("show");
	
	var candidateId = $(this).attr("attr_candidateId")
	var candidateName = $(this).attr("attr_name")
	var type = $(this).attr("attr_type")
	var viewType = $(this).attr("attr_view_type")
	$("#candidateNameId").html(candidateName);
	setTimeout(function(){ 
		buildAlreadyUploadDocumentsView(candidateId,type,candidateName,viewType);
	}, 1000);
	
});
function buildAlreadyUploadDocumentsView(candidateId,type,candidateName,viewType){
	
	var str='';
		str+='<div class="col-sm-12">';
		str+='<div class="alreadyUploadFilesCss">';
		if(viewType == "candidateDoc"){
			for(var i in globalFileUpload){
				if(candidateId == globalFileUpload[i].refCandidateId){	
					for(var j in globalFileUpload[i].fileNamesList){
						
						//str+='<input id="mainBlockFileUpload2'+type+'" name="referList['+i+'].fileList['+j+']" value="'+result.referDetailsList[i].fileNamesList[j].value+'" attr_image_tyep="refImage" type="hidden">';
								var scanCopySpl = globalFileUpload[i].fileNamesList[j].value.split("."); 
								var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
									str+='<div class="col-sm-4">';
										if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
											str+='<div class="viewImageCss m_top20">';
										}else{
											str+='<div class="viewImageCss">';
										}
										if(scanCopyExt =="pdf"){
											str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
												str+='<div class="mouse-over">Expand</div>';
												str+='<object data="'+globalFileUpload[i].fileNamesList[j].value+'" type="application/pdf" width="100%" height="200px;"></object>';
											str+='</a>';
											str+='<div id="inline'+i+''+j+'" style="width:100%;display: none;">';
												str+='<object data="'+globalFileUpload[i].fileNamesList[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
											str+='</div>';
											
										}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
											str+='<a class="fancyboxView" href="#inline'+i+''+j+'">';
												str+='<img src="'+globalFileUpload[i].fileNamesList[j].value+'"  width="100%" height="200px;"></img>';
											str+='</a>';
											str+='<div id="inline'+i+''+j+'" style="width:100%;display: none;">';
												str+='<img src="'+globalFileUpload[i].fileNamesList[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
											str+='</div>';
										}else{
											str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+globalFileUpload[i].fileNamesList[j].value+'\')">Here</a> To View Document</b>';
										}
							
								str+='</div>';
							str+='</div>';
						}
						
			
				}
			}
		}else{
		
				for(var j in globalWorkFileUpload){
						var scanCopySpl = globalWorkFileUpload[j].value.split("."); 
						var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
							str+='<div class="col-sm-4">';
								if((scanCopyExt !="pdf") && (scanCopyExt != "jpeg" || scanCopyExt !=  "jpg"  || scanCopyExt !=  "gif"  || scanCopyExt !=  "bmp"  || scanCopyExt !=  "png")){
									str+='<div class="viewImageCss m_top20">';
								}else{
									str+='<div class="viewImageCss">';
								}
								if(scanCopyExt =="pdf"){
									str+='<a class="fancyboxView" href="#inline'+j+'">';
										str+='<div class="mouse-over">Expand</div>';
										str+='<object data="'+globalWorkFileUpload[j].value+'" type="application/pdf" width="100%" height="200px;"></object>';
									str+='</a>';
									str+='<div id="inline'+j+'" style="width:100%;display: none;">';
										str+='<object data="'+globalWorkFileUpload[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
									str+='</div>';
									
								}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
									str+='<a class="fancyboxView" href="#inline'+j+'">';
										str+='<img src="'+globalWorkFileUpload[j].value+'"  width="100%" height="200px;"></img>';
									str+='</a>';
									str+='<div id="inline'+j+'" style="width:100%;display: none;">';
										str+='<img src="'+globalWorkFileUpload[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
									str+='</div>';
								}else{
									str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+globalWorkFileUpload[j].value+'\')">Here</a> To View Document</b>';
								}
					
						str+='</div>';
					str+='</div>';
				}
			
		}
			
	str+='</div>';
	str+='</div>';
	$("#candidateUploadDFiesDivId").html(str);
	$(".fancyboxView").fancybox();
}
			

