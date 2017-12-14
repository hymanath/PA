var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var alreadyCandidateId=[];
var searchCandidateIds=[];
var diffArr=[];
var commonArr=[];
getPetitionDetails();
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
	if(result.representationType == "REPRESENT"){
		
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
					
					str+='<div class="row">';
						for(var j in result.referDetailsList[i].fileNamesList){
							str+='<div class="col-sm-2">';
								str+='<div class="">';
									str+='<h4>'+result.referDetailsList[i].fileNamesList[j].value+'</h4>';
								str+='</div>';
							str+='</div>';
						}
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
		
		str+='<div class="row">';
			for(var i in result.fileList){
				str+='<div class="col-sm-2">';
					str+='<div class="">';
						str+='<h4>'+result.fileList[i].value+'</h4>';
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
		
		$("#"+result.representationType+"DetailsDivId").html(str);
		for(var i in result.referDetailsList){
			initializeEditFileUploadMainBlock(result.representationType,result.referDetailsList[i].refCandidateId);
		}
		initializeEditFileProjDoc(result.representationType);
}
function getPetitionDesignationList(){
	$("#designationsId").html('');
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
				$("#designationsId").append('<option value="0">Select Designation</option>');
			for(var i in result){
				$("#designationsId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#designationsId").trigger('chosen:updated');
	});	

}
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
$(document).on("change","#districtCandId",function(){
	var levelVal = $(this).val();
	getConstituencyNamesByDistrictId(levelVal,"","popup"); 
	
});

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