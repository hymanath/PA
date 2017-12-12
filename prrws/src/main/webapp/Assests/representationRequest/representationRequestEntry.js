var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var alreadyCandidateId=[];
var searchCandidateIds=[];
var diffArr=[];
var commonArr=[];
var globalWorkTypeCount=1;
setTimeout(function(){ 
	$(".chosen-select").chosen();
	buildSelfAndRepresenteeDetails("self")
}, 2000);

$("#dateRangePickerMGNF").daterangepicker({
	singleDatePicker: true,
	locale: {
	  format: 'DD-MM-YYYY'
	},
	
});
$('#dateRangePickerMGNF').on('apply.daterangepicker', function(ev, picker) {
});	
$(document).on("click",".selfRepresenceCls",function(){
	
	
	if($(this).is(":checked")){
		var typeVal =  $(this).attr("attr_type")
		if(typeVal == "self"){
			alreadyCandidateId=[]
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
		}else if(typeVal == "represent"){
			$("#selfDetailsDivId").html('');
			alreadyCandidateId=[]
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
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
	alert(typeChange)
	var searchType="all";
	
	if(typeChange == "Inner"){
		$("#districtInnerId"+typeVal+counterId).html('');
	}else{
		if(typeVal=="popup"){
			$("#districtCandId").html('');
			searchType = "refCandidate";
		}else if(counterId == null || counterId ==""){
			$("#district"+typeVal+counterId).html('');
		}else if(counterId !="" && parseInt(counterId)>0){
			$("#districtId"+typeVal+counterId).html('');
		}
	}
	var json = {
		  stateId:"1",
		  searchType:searchType,
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
			
			if(typeChange == "Inner"){
				$("#districtInnerId"+typeVal+counterId).append('<option value="0">Select District</option>');
			}else{
				if(typeVal== "popup"){
					$("#districtCandId").append('<option value="0">All</option>');
				}else if(counterId == null || counterId == ""){		
					$("#district"+typeVal+counterId).append('<option value="0">Select District</option>');
				}else if(counterId !="" && parseInt(counterId)>0){
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
					}else  if(counterId !="" && parseInt(counterId)>0){
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
				
			else if(counterId != "" && parseInt(counterId) > 0){
				$("#districtId"+typeVal+counterId).trigger('chosen:updated');
			}
				
		}
			
		
		
	});	
}
//Constituency Build
function getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,typeChange){
	  $("#constituencyCanId").html('');
	   var searchType= "all";
		
		if(typeChange == "Inner"){
			 $("#constituencyInnerId"+typeVal+counterId).html('');
		}else{
			if(typeVal== "popup"){
				searchType = "refCandidate";
			}else if(counterId !="" && parseInt(counterId)>0){
				 $("#constituencyId"+typeVal+counterId).html('');
				
			}
		}
	 
	  var json = {
		  districtId:levelVal,
		  searchType:typeVal,
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
			if(typeVal=="popup"){
				searchType = "refCandidate";
			}else if(counterId !="" && parseInt(counterId)>0){
				 $("#mandalId"+typeVal+counterId).html('');
			}
		
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
		
	}
	
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				str+='<div id="candidateDetails'+typeVal+'DivId"></div>';
			str+='</div>';
		str+='</div>';		
			
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
				str+='<div class="pull-right">';
					str+='<button type="button" class="btn btn-lg btn-success searchCandidateCls button_gray" attr_type="'+typeVal+'">ADD REFERRAL</button>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">';
				str+='<div class="col-sm-8">';
					str+='<label>COMPLETE WORK DISCRIPTION</label>';
					str+='<input type="text"  name=""  value="" class="form-control m_top5 height45" id="name'+typeVal+'" placeholder="Enter Name">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>No of Works</label>';
						str+='<input   name=""   type="text" class="form-control m_top5 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work" onkeyUp="enableWorks(this.value,\'workDetailsDivId'+typeVal+'\',\''+typeVal+'\');">';
				str+='</div>';
				str+='<div class="col-sm-2">';
					str+='<label>Work in Cost (in Lakh)</label>';
					str+='<input type="text"  name="" class="form-control m_top5 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost">';
				str+='</div>';
		str+='</div>';	
		
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
					str+='<h3 class="panel-title f_18">DOCUMENTS UPLOAD</h3>';
					str+='<input type="file"  name="workFilesList[]" attr_image_tyep="projImage"  id="projectDocUpload'+typeVal+'" multiple="multiple" class="m_top20"/>';
			str+='</div>';
		str+='</div>';	
		
		str+='<div class="row">';
			str+='<div id="workDetailsDivId'+typeVal+'" style="display:none;"></div>';
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
	$("#districtCandDivId").show();
	var value = $(this).val();
	if(parseInt(value)>0){
		if(value == 4){
			$("#constituencyCanId").html("");
			$("#candidateDetailsDivId").html('');
			getParliamentIdsByConstituencyList();
			$("#districtCandDivId").hide();
		}else{
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
$(document).on("click",".candidateAddedView",function(){
	
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	alreadyCandidateId.push(parseInt(candidateId));
	$(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-3").addClass("col-sm-2");
	$("#fileUpload"+typeVal+candidateId).show();
	$("#candidateDetails"+typeVal+"DivId").append($("#candidatesAppendDiv"+candidateId).html());
	$("#fileUpload"+typeVal+candidateId).append('<div class="col-sm-4" style="margin-top:-20px;"><label>REFERAL LETTER</label><input type="file"   name="filesList[]" attr_image_tyep="refImage"  id="mainBlockFileUpload'+candidateId+''+typeVal+'" multiple="multiple" class=""/></div>');
	
	$(this).parent().find(".representation-selected").addClass("display_block");
	$("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").removeClass("candidateAddedView");
	$("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").css("cursor","auto");
	$(".showRemoveIcon").attr("attr_candidateId",candidateId)
	$("#candidateDetails"+typeVal+"DivId").find(".showRemoveIcon").show();
	initializeFileUploadMainBlock(typeVal,candidateId);
});

$(document).on("click",".representation-remove",function(){
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	$(".addRemoveCol"+typeVal+candidateId).removeClass("col-sm-2").addClass("col-sm-3");
	$("#fileUpload"+typeVal+candidateId).hide();
	$("#candidatesAppendDiv"+candidateId).find(".representation-selected").removeClass("display_block");
	setTimeout(function(){ 
		$("#candidate"+typeVal+candidateId).find(".bgColorCandidatesView").addClass("candidateAddedView")
		$("#candidateDetails"+typeVal+"DivId").find("#candidate"+typeVal+candidateId).remove();
	}, 1500);
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
	console.log(diffArr)
	console.log(commonArr)
	console.log(alreadyCandidateId)
		if(alreadyCandidateId !=null && alreadyCandidateId.length>0){
				for(var i in result){
				 
					for(var j in commonArr){
						if(commonArr[j] == result[i].referrerCandidateId){
							str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'" style="position:relative;">';
							str+='<div class="bgColorCandidatesView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
							
								str+='<div class="row">';
										str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidateId="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"></i></div>';
										
										str+='<div class="representation-selected display_block" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff" ></i></span></div>';
										
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
						}
					}		
				
			}
			
			for(var i in result){
				
					for(var j in diffArr){
						if(diffArr[j] == result[i].referrerCandidateId){
							str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'" style="position:relative;">';
							str+='<div class="bgColorCandidatesView candidateAddedView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
							
								str+='<div class="row">';
									str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidateId="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"></i></div>';
									
										str+='<div class="representation-selected" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff" ></i></span></div>';
										
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
						}
					}
				
			} 
		}else{
			for(var i in result){
				 str+='<div id="candidatesAppendDiv'+result[i].referrerCandidateId+'" style="position:relative;">';
					str+='<div class="bgColorCandidatesView candidateAddedView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+' id="candidate'+typeVal+''+result[i].referrerCandidateId+'">';
					
					str+='<div class="row">';
					str+='<div class="pull-right showRemoveIcon" attr_type="'+typeVal+'"  attr_candidateId="'+result[i].referrerCandidateId+'" style="display:none;"><i class="glyphicon glyphicon-remove"></i></div>';
					str+='<div class="representation-selected" id="candidateRemove'+typeVal+''+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove" style="background-color: green; border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;color:#fff" ></i></span></div>';
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
				 
			}
		}
	$("#candidateDetailsDivId").html(str);
	
	
}

function buildTemplateWorkDetails(typeVal){
	var str='';
	str+='<div class="col-sm-12">';
		str+='<div style="border:3px solid #dddddd;padding:10px;">';
			str+='<div  cloned_block_'+typeVal+'='+globalWorkTypeCount+'>';
				str+='<h3 class="panel-title f_22">WORK TYPE 1</h3>';
				
				str+='<div class="row m_top10">';
				
					str+='<div class="col-sm-3">';
							str+='<label>Department</label>';
							str+='<select  name=""  class="form-control chosen-select m_top10  id="WorkTypeWiseDepartmentId'+typeVal+''+globalWorkTypeCount+'" )>';
							str+='</select>';
						str+='</div>';
					
					str+='<div class="col-sm-3">';
						str+='<label>Subject</label>';
						str+='<select  name=""  class="form-control chosen-select m_top10 subjecOnchngeCls"  id="subjectId'+typeVal+''+globalWorkTypeCount+'" onChange=getPmSubSubjectList(this.value,"subSubjectId'+typeVal+''+globalWorkTypeCount+'")>';
							str+='<option value="0">Select Subject</option>';
						str+='</select>';
					str+='</div>';
					
					str+='<div class="col-sm-3">';
						str+='<label>Sub Subject</label>';
						str+='<select  name=""  class="form-control chosen-select m_top10"  id="subSubjectId'+typeVal+''+globalWorkTypeCount+'">';
							str+='<option value="0">Select Sub Subject</option>';
						str+='</select>';
					str+='</div>';
								
				str+='</div>';
				
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-12">';
						str+='<div class="bg_color_view">';
							str+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
							str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<label>Select Type</label>';
										str+='<select  name=""  class="form-control chosen-select m_top10" id="workTypeId'+typeVal+''+globalWorkTypeCount+'" )>';
											//str+='<option value="0">Select Work Type</option>';
										str+='</select>';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>Work in Cost (in Lakh)</label>';
										str+='<input type="text"  name="" class="form-control m_top5 height45" id="appendWorkCost'+typeVal+''+globalWorkTypeCount+'" placeholder="Enter Work Cost">';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>Work Details</label>';
										str+='<input type="text"  name="" class="form-control m_top5 height45" id="appendWorkDetailsId'+typeVal+''+globalWorkTypeCount+'" placeholder="Enter">';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>eOffice ID</label>';
										str+='<input type="text"  name="" class="form-control m_top5 height45" id="appendEofficeId'+typeVal+''+globalWorkTypeCount+'" placeholder="Enter">';
									str+='</div>';
							str+='</div>';
							str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<label>Location Level</label>';
										str+='<select  name="worksList[0].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'" attr_type_change="main">';
											str+='<option value="0">Select Level</option>';
											str+='<option value="3">District</option>';
											str+='<option value="4">Constituency</option>';
											str+='<option value="5">Mandal</option>';
										str+='</select>';
									str+='</div>';
									
									str+='<div class="col-sm-2 districtCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
										str+='<label>District</label>';
										str+='<select  name="worksList[0].candidateAddressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange" id="districtId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'" attr_type_change="main">';
											str+='<option value="0">Select District</option>';
										str+='</select>';
									str+='</div>';
									
									str+='<div class="col-sm-2 constituencyCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
										str+='<label>Constituency</label>';
										str+='<select   name="worksList[0].candidateAddressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'" attr_type_change="main">';
											str+='<option value="0">Select Constituency</option>';
										str+='</select>';
									str+='</div>';
									
									str+='<div class="col-sm-2 mandalCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
										str+='<label>Mandal</label>';
										str+='<select  name="worksList[0].candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange" id="mandalId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'" attr_type_change="main">';
											str+='<option value="0">Select Mandal</option>';
										str+='</select>';
									str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="appendInnerBlocks'+typeVal+''+globalWorkTypeCount+'"></div>';
					str+='<div class="col-sm-12">';
						str+='<div class="pull-right">';
							str+='<span class="addLocationCss m_top10 pull-right cloned_Inner_Element" style="cursor:pointer;" attr_type="'+typeVal+'" attr_counterval="'+globalWorkTypeCount+'">ADD WORK</span>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
					
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="appendWorkDetailsDiv'+typeVal+'"></div>';
	str+='</div>';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" block-clone-counter-'+typeVal+'="'+globalWorkTypeCount+'" style="cursor:pointer;" block-clone-'+typeVal+'="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">ADD WORK TYPE</span></div>';
	str+='</div>';	
	
	$("#workDetailsDivId"+typeVal).html(str);
		getWorkTypeList(typeVal,globalWorkTypeCount);
		getPmSubjectList(typeVal,globalWorkTypeCount);
		getPmSubjectList("subjectId",typeVal,'1');
	$(".chosen-select").chosen();
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
	if(counterId <= parseInt(workCount)){
		$(".appendWorkDetailsDiv"+typeVal+"").append(clonedTemplate(blockId,'clone',counterId,typeVal,counterappendId));
		$(".chosen-select").chosen({width:'100%'});
		$("[block-clone-"+typeVal+"="+blockId+"]").attr("block-clone-counter-"+typeVal+"",counterId);
		//getAllDistrictsInState(typeVal,counterId);
	}
});

function clonedTemplate(blockId,type,counterId,typeVal,counterappendId){
	
	var clonedTemplate='';
	
	clonedTemplate+='<div class="col-sm-12" cloned_block_'+typeVal+'='+counterId+'>';
		clonedTemplate+='<div style="border:3px solid #dddddd;padding:10px;">';
			clonedTemplate+='<div  cloned_block_'+typeVal+'='+counterId+'>';
				clonedTemplate+='<h3 class="panel-title f_22">WORK TYPE 1</h3>';
				
				clonedTemplate+='<div class="row m_top10">';
				
					clonedTemplate+='<div class="col-sm-3">';
							clonedTemplate+='<label>Department</label>';
							clonedTemplate+='<select  name=""  class="form-control chosen-select m_top10  id="WorkTypeWiseDepartmentId'+typeVal+''+counterId+'" )>';
							clonedTemplate+='</select>';
						clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="col-sm-3">';
						clonedTemplate+='<label>Subject</label>';
						clonedTemplate+='<select  name=""  class="form-control chosen-select m_top10  id="subjectId'+typeVal+''+counterId+'" onChange=getPetitionSubSubjectList(this.value,"subSubjectId'+typeVal+''+counterId+'")>';
							clonedTemplate+='<option value="0">Select Subject</option>';
						clonedTemplate+='</select>';
					clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="col-sm-3">';
						clonedTemplate+='<label>Sub Subject</label>';
						clonedTemplate+='<select  name=""  class="form-control chosen-select m_top10  id="subSubjectId'+typeVal+''+counterId+'">';
							clonedTemplate+='<option value="0">Select Sub Subject</option>';
						clonedTemplate+='</select>';
					clonedTemplate+='</div>';
								
				clonedTemplate+='</div>';
				
				clonedTemplate+='<div class="row m_top10">';
					clonedTemplate+='<div class="col-sm-12">';
						clonedTemplate+='<div class="bg_color_view">';
							clonedTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>Select Type</label>';
										clonedTemplate+='<select  name=""  class="form-control chosen-select m_top10" id="workTypeId'+typeVal+''+counterId+'" )>';
											//clonedTemplate+='<option value="0">Select Work Type</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>Work in Cost (in Lakh)</label>';
										clonedTemplate+='<input type="text"  name="" class="form-control m_top5 height45" id="appendWorkCost'+typeVal+''+counterId+'" placeholder="Enter Work Cost">';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>Work Details</label>';
										clonedTemplate+='<input type="text"  name="" class="form-control m_top5 height45" id="appendWorkDetailsId'+typeVal+''+counterId+'" placeholder="Enter">';
									clonedTemplate+='</div>';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>eOffice ID</label>';
										clonedTemplate+='<input type="text"  name="" class="form-control m_top5 height45" id="appendEofficeId'+typeVal+''+counterId+'" placeholder="Enter">';
									clonedTemplate+='</div>';
							clonedTemplate+='</div>';
							clonedTemplate+='<div class="row m_top10">';
									clonedTemplate+='<div class="col-sm-3">';
										clonedTemplate+='<label>Location Level</label>';
										clonedTemplate+='<select  name="worksList[0].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="main">';
											clonedTemplate+='<option value="0">Select Level</option>';
											clonedTemplate+='<option value="3">District</option>';
											clonedTemplate+='<option value="4">Constituency</option>';
											clonedTemplate+='<option value="5">Mandal</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									
									clonedTemplate+='<div class="col-sm-2 districtCls'+typeVal+''+counterId+'" style="display:none">';
										clonedTemplate+='<label>District</label>';
										clonedTemplate+='<select  name="worksList[0].candidateAddressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange" id="districtId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="main">';
											clonedTemplate+='<option value="0">Select District</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									
									clonedTemplate+='<div class="col-sm-2 constituencyCls'+typeVal+''+counterId+'" style="display:none">';
										clonedTemplate+='<label>Constituency</label>';
										clonedTemplate+='<select   name="worksList[0].candidateAddressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="main">';
											clonedTemplate+='<option value="0">Select Constituency</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
									
									clonedTemplate+='<div class="col-sm-2 mandalCls'+typeVal+''+counterId+'" style="display:none">';
										clonedTemplate+='<label>Mandal</label>';
										clonedTemplate+='<select  name="worksList[0].candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange" id="mandalId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="main">';
											clonedTemplate+='<option value="0">Select Mandal</option>';
										clonedTemplate+='</select>';
									clonedTemplate+='</div>';
							clonedTemplate+='</div>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';
					
					clonedTemplate+='<div class="appendInnerBlocks'+typeVal+''+counterId+''+counterappendId+'"></div>';
					clonedTemplate+='<div class="col-sm-12">';
						clonedTemplate+='<div class="pull-right">';
							clonedTemplate+='<span class="addLocationCss m_top10 pull-right cloned_Inner_Element" style="cursor:pointer;" attr_type="'+typeVal+'" attr_counterval="'+counterId+''+counterappendId+'">ADD WORK</span>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';
					
				clonedTemplate+='</div>';
					
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	
	return clonedTemplate;
	
}
$(document).on("click",".cloned_Inner_Element",function(){
	var typeVal = $(this).attr("attr_type");
	var counterId = $(this).attr("attr_counterval");
	var counterinnerId =  parseInt(counterId) + 1;
	$(this).attr("attr_counterval",counterinnerId);
	
	
	$(".appendInnerBlocks"+typeVal+counterId+"").append(clonedInnerTemplate('clone',counterinnerId,typeVal));
	$(".chosen-select").chosen({width:'100%'});
	//getAllDistrictsInState(typeVal,counterId);	
	
});
function clonedInnerTemplate(type,counterId,typeVal){
	var clonedInnerTemplate='';
	clonedInnerTemplate+='<div class="m_top10">';
		clonedInnerTemplate+='<div class="col-sm-12 m_top10">';
			clonedInnerTemplate+='<div class="bg_color_view">';
				clonedInnerTemplate+='<h3 class="panel-title f_16 font_weight">WORK TYPE 1</h3>';
				clonedInnerTemplate+='<div class="row m_top10">';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>Select Type</label>';
							clonedInnerTemplate+='<select  name=""  class="form-control chosen-select m_top10" id="workTypeId'+typeVal+''+counterId+'" )>';
								//clonedInnerTemplate+='<option value="0">Select Work Type</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>Work in Cost (in Lakh)</label>';
							clonedInnerTemplate+='<input type="text"  name="" class="form-control m_top5 height45" id="appendWorkCostInner'+typeVal+''+counterId+'" placeholder="Enter Work Cost">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>Work Details</label>';
							clonedInnerTemplate+='<input type="text"  name="" class="form-control m_top5 height45" id="appendWorkDetailsInnerId'+typeVal+''+counterId+'" placeholder="Enter">';
						clonedInnerTemplate+='</div>';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>eOffice ID</label>';
							clonedInnerTemplate+='<input type="text"  name="" class="form-control m_top5 height45" id="appendEofficeInnerId'+typeVal+''+counterId+'" placeholder="Enter">';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='<div class="row m_top10">';
						clonedInnerTemplate+='<div class="col-sm-3">';
							clonedInnerTemplate+='<label>Location Level</label>';
							clonedInnerTemplate+='<select  name="worksList[0].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelInnerId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="Inner">';
								clonedInnerTemplate+='<option value="0">Select Level</option>';
								clonedInnerTemplate+='<option value="3">District</option>';
								clonedInnerTemplate+='<option value="4">Constituency</option>';
								clonedInnerTemplate+='<option value="5">Mandal</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 districtInnerCls'+typeVal+''+counterId+'" style="display:none">';
							clonedInnerTemplate+='<label>District</label>';
							clonedInnerTemplate+='<select  name="worksList[0].candidateAddressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange" id="districtInnerId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="Inner">';
								clonedInnerTemplate+='<option value="0">Select District</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 constituencyInnerCls'+typeVal+''+counterId+'" style="display:none">';
							clonedInnerTemplate+='<label>Constituency</label>';
							clonedInnerTemplate+='<select   name="worksList[0].candidateAddressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyInnerId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="Inner">';
								clonedInnerTemplate+='<option value="0">Select Constituency</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
						
						clonedInnerTemplate+='<div class="col-sm-2 mandalInnerCls'+typeVal+''+counterId+'" style="display:none">';
							clonedInnerTemplate+='<label>Mandal</label>';
							clonedInnerTemplate+='<select  name="worksList[0].candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange" id="mandalInnerId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" attr_type_change="Inner">';
								clonedInnerTemplate+='<option value="0">Select Mandal</option>';
							clonedInnerTemplate+='</select>';
						clonedInnerTemplate+='</div>';
				clonedInnerTemplate+='</div>';
			clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='</div>';
		clonedInnerTemplate+='<div class="appendInnerBlocks'+typeVal+''+counterId+'"></div>';
		return clonedInnerTemplate;
}
function getWorkTypeList(typeVal,count){
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
			 $("#workTypeId"+typeVal+count).append('<option value="0">Select Work Type</option>');
			for(var i in result){
				$("#workTypeId"+typeVal+count).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#workTypeId"+typeVal+count).trigger('chosen:updated');
	});	
}

function getPmSubjectList(divId,typeVal,counterId){
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
			
			 $("#"+divId+""+typeVal+""+counterId+"").html('<option value="0">All</option>');
			for(var i in result){
				$("#"+divId+""+typeVal+""+counterId+"").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#"+divId+""+typeVal+""+counterId+"").trigger('chosen:updated');
	});	
}

function getPmSubSubjectList(subjectId,divId){
	if(subjectId != null){
		subIdVal = subjectId;
	}
$("#"+divId).html('');
	var json = {
		subjectId : subIdVal
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
			 $("#"+divId).append('<option value="0">All</option>');
			for(var i in result){
				$("#"+divId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#"+divId).trigger('chosen:updated');
	});	
}
function getPmLeadDetailsList(typeVal){
	var json = {
		
	};
	$.ajax({              
		type:'POST',    
		url: 'getPmLeadDetailsList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#petitionLead").append('<option value="0">All</option>');
			for(var i in result){
				$("#petitionLead").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#petitionLead").trigger('chosen:updated');
	});	
}
function getPmBriefLeadList(typeVal){
			var json = {
				
			};
	$.ajax({              
		type:'POST',    
		url: 'getPmBriefLeadList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :  function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#petitionBriefLeadId").append('<option value="0">All</option>');
			for(var i in result){
				$("#petitionBriefLeadId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#petitionBriefLeadId").trigger('chosen:updated');
	});	
}
function getPmGrantList(){
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
			 $("#petitionGrantId").append('<option value="0">All</option>');
			for(var i in result){
				$("#petitionGrantId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#petitionGrantId").trigger('chosen:updated');
	});	
}
function getPmStatusList(){
	var json = {
		
	};
	$.ajax({              
		type:'POST',    
		url: 'getPmStatusList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#petitionStatusId").append('<option value="0">All</option>');
			for(var i in result){
				$("#petitionStatusId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#petitionStatusId").trigger('chosen:updated');
	});	
}
