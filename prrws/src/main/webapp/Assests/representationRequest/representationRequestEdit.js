var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalWorkTypeCount=1;
var globalMemberObject='';
var globalTypeVal='';

$('#dateRangePickerMGNF').on('apply.daterangepicker', function(ev, picker) {
});

getRepresentationRequestDetailsByRepresentationRequestId(781);
$(document).on("click",".selfRepresenceCls",function(){
	$("#districtCandId").html('<option value="0">Select District</option>');
	$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
	$(".chosen-select").trigger("chosen:updated");
	if($(this).is(":checked")){
		var typeVal =  $(this).attr("attr_type");
		//$("#workDetailsDivId"+typeVal).html("");
		//$("#workDetailsDivId"+typeVal).hide("");
		//$("#selfDetailsDivId").html("");
		//$("#representDetailsDivId").html("");
		//if(globalTypeVal == typeVal){
		//	$("#representeeEditDivId").html('');
			//$("#"+typeVal+"DetailsDivId").html(spinner);
			//getRepresentationRequestDetailsByRepresentationRequestId(680);
		//}else 
			if(typeVal == "self"){
			//$("#representDetailsDivId").html('');
			alreadyCandidateId='';
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			$("#representeeEditDivId").html('');
			$("#representeeOnLOadEditDivId").html('');
			//buildSelfAndRepresenteeDetails(typeVal)
			getAllDistrictsInState(typeVal,"");
			getPetitionDepartmentList("departmentId",typeVal)
			getPetitionSubjectList("subjectId",typeVal,"","")
		}else if(typeVal == "represent"){
			//$("#selfDetailsDivId").html('');
			//$("#representeeEditDivId").html(spinner);
			alreadyCandidateId='';
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			//buildSelfAndRepresenteeDetails(typeVal)
			represnteeDeatilsEdit(typeVal);
			getAllDistrictsInState(typeVal,"");
			getPetitionDepartmentList("departmentId",typeVal)
			getPetitionSubjectList("subjectId",typeVal,"","")
		}
	}
});
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
				str+='<label>Name</label>';
				str+='<input type="text"  name="petitionMemberVO.name"  value="" class="form-control m_top10 height45" id="name'+typeVal+'" placeholder="Enter Name">';
				str+='<div id="name'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Mobile Number</label>';
				str+='<input type="text" name="petitionMemberVO.mobileNo"  value=""  class="form-control m_top10 height45" id="mobileNumber'+typeVal+'" placeholder="Enter Mobile Number">';
				str+='<div id="mobileNumber'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>E-mail ID</label>';
				str+='<input type="text" name="petitionMemberVO.emailId"   value="" class="form-control m_top10 height45" id="emailId'+typeVal+'" placeholder="Enter E-mail ID">';
				str+='<div id="emailId'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Voter ID</label>';
				str+='<input type="text"  name="petitionMemberVO.voterCardNo"  value=""  class="form-control m_top10 height45" id="voterId'+typeVal+'" placeholder="Enter Voter ID">';
				str+='<div id="voterId'+typeVal+'Err"></div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>District</label>';
				str+='<select   name="candidateAddressVO.districtId" class="form-control chosen-select m_top10" id="district'+typeVal+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
				str+='<div id="district'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Constituency</label>';
				str+='<select  name="candidateAddressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+typeVal+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
				str+='<div id="constituency'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Mandal</label>';
				str+='<select   name="candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10" id="mandal'+typeVal+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
				str+='<div id="mandal'+typeVal+'Err"></div>';
			str+='</div>';
		str+='</div>';
	}
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="font_weight text-capital f_22">Self Candidate  Details:</h3>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<span class="pull-right showRemoveIcon" attr_type="'+typeVal+'" style="background-color: rgb(221, 221, 221); top: 8px; border-radius: 50%; border: 1px solid rgb(0, 0, 0); padding: 3px; position: relative; cursor: pointer;display:none;z-index:999;"><i class="glyphicon glyphicon-remove"></i></span>';
				str+='<div id="candidateDetails'+typeVal+'DivId"></div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h4 class="searchCss searchCandidateCls" attr_type="'+typeVal+'"><i class="fa fa-search" aria-hidden="true" style="font-size:20px;"></i> Click here to Search Candidate details </h4>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h4 class="f_18">REFERRAL LETTER</h4>';
			str+='<input type="file"   name="filesList[]" attr_image_tyep="refImage"  id="update_TourFileId2'+typeVal+'" multiple="multiple" class="m_top20"/>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="panel-title f_18">WORK TYPE DETAILS</h3>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';
					str+='<label>Subject</label>';
					str+='<select  name="subjectId"  class="form-control chosen-select m_top10 subjecOnchngeCls" id="subjectId'+typeVal+'">';
						str+='<option value="0">Select Subject</option>';
					str+='</select>';
					str+='<div id="subjectId'+typeVal+'Err"></div>';
				str+='</div>';
					str+='<div class="col-sm-3">';
					str+='<label>Sub Subject</label>';
					str+='<select  name="subSubjectId"  class="form-control chosen-select m_top10" id="subSubjectId'+typeVal+'">';
						str+='<option value="0">Select Sub Subject</option>';
					str+='</select>';
					str+='<div id="subSubjectId'+typeVal+'Err"></div>';
				str+='</div>';
			str+='</div>';			
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';
					str+='<label>Department</label>';
					str+='<select  name="deptId"  class="form-control chosen-select m_top10" id="departmentId'+typeVal+'">';
						str+='<option value="0">Select Department</option>';
					str+='</select>';
					str+='<div id="departmentId'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Previous Petition</label>';
					str+='<select  name="isPreviousPetition"  class="form-control chosen-select m_top10" id="previousPetitionId'+typeVal+'" onChange = getChangeValue(this.value)>';
						str+='<option value="0">Select Previous Petition</option>';
						str+='<option value="Y">YES</option>';
						str+='<option value="N">No</option>';
					str+='</select>';
					str+='<div id="previousPetitionId'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-3" style="display:none;" id = "previousPetitionRefId">';
					str+='<label>Previous Petition No (Endorsement NO)</label>';
					str+='<input  name="previousPetitionRefNo"  type="text" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="101/20/11/2017">';
					str+='<div id="workCost'+typeVal+'Err"></div>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-6">';
					str+='<label>Name of the Work</label>';
					str+='<input  name="workName"  type="text" class="form-control m_top10 height45" id="nameofWork'+typeVal+'" placeholder="Enter Name Of Work">';
					str+='<div id="nameofWork'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>No of Works</label>';
					str+='<input   name="noOfWorks"   type="text" class="form-control m_top10 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work" onkeyUp="enableWorks(this.value,\'workDetailsDivId'+typeVal+'\',\''+typeVal+'\');">';
					str+='<div id="noofWork'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Work Cost (Est. Cost in Lakh)</label>';
					str+='<input type="text"  name="estimationCost" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost">';
					str+='<div id="workCost'+typeVal+'Err"></div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_18 text-capital">grievance DISCRIPTION</h3>';
					str+='<textarea name="projectDescription"  class="form-control m_top20" rows="7" id="textArea'+typeVal+'"></textarea>';
					str+='<div id="textArea'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_18">PROJCT DOCUMENTS UPLOAD</h3>';
					str+='<input type="file"  name="workFilesList[]" attr_image_tyep="projImage"  id="projectDocUpload'+typeVal+'" multiple="multiple" class="m_top20"/>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div id="workDetailsDivId'+typeVal+'" style="display:none;"></div>';
	str+='</div>';
	
	$("#"+typeVal+"DetailsDivId").html(str);
	initializeFile(typeVal);	
	initializeFileProjDoc(typeVal);
	$(".chosen-select").chosen();
	buildTemplateWorkDetails(typeVal,"1","");
}

$(document).on("click",".cloned_Element",function(){
	
	var typeVal = $(this).attr("attr_type");
	var workCount = $("#noofWork"+typeVal).val();
	var counterId = $(this).attr("right-block-clone-counter-"+typeVal+"");
		counterId = parseInt(counterId) + 1;
	var blockId = $(this).attr("right-block-clone-"+typeVal+"");
	if(counterId <= parseInt(workCount)){
		$("[cloned_block_"+typeVal+"="+blockId+"]").parent().parent().find(".appendDiv"+typeVal+"").append(clonedTemplate(blockId,'clone',counterId,typeVal));
		$(".chosen-select").chosen({width:'100%'});
		$("[right-block-clone-"+typeVal+"="+blockId+"]").attr("right-block-clone-counter-"+typeVal+"",counterId);
		getAllDistrictsInState(typeVal,counterId);
		getPetitionSubjectList("workTypeId",typeVal,counterId,"");
	}
});
$(document).on("click","[remove-clone]",function(){
	var id = $(this).attr("remove-clone");
	var typeVal = $(this).attr("attr_type");
	$("[cloned_block_"+typeVal+"="+id+"]").remove();
});
function clonedTemplate(blockId,type,counterId,typeVal){
	
	var clonedTemplate='';
	clonedTemplate+='<div class="col-sm-12" cloned_block_'+typeVal+'='+counterId+'>';
		clonedTemplate+='<div style="border:3px solid #dddddd;padding:10px;">';
				clonedTemplate+='<div class="m_top10">';
				if(type == 'clone'){
					clonedTemplate+='<h3 class="panel-title f_22">WORK No '+counterId+' <i title="close" class="glyphicon glyphicon-remove remove-icon pull-right" remove-clone="'+counterId+'" attr_type="'+typeVal+'"></i></h3>';
				}else{
					clonedTemplate+='<h3 class="panel-title f_22">WORK No '+counterId+'</h3>';
				}
					clonedTemplate+='<div class="row m_top10">';
						clonedTemplate+='<div class="col-sm-3">';
							clonedTemplate+='<label>Select Subject</label>';
							clonedTemplate+='<select name="worksList['+counterId+'].deptId"  class="form-control chosen-select m_top10 workTypeCls'+typeVal+'" id="workTypeId'+typeVal+''+counterId+'" onChange=getPetitionSubSubjectList(this.value,"subWorkTypeId'+typeVal+''+counterId+'")>';
								clonedTemplate+='<option value="0">Select Subject</option>';
							clonedTemplate+='</select>';
							clonedTemplate+='<div class="m_top10"  id="workTypeId'+typeVal+''+counterId+'Err"></div>';
						clonedTemplate+='</div>';
						clonedTemplate+='<div class="col-sm-3">';
							clonedTemplate+='<label>Select Sub Subject</label>';
							clonedTemplate+='<select name="worksList['+counterId+'].deptId"  class="form-control chosen-select m_top10 subWorkTypeCls'+typeVal+'" id="subWorkTypeId'+typeVal+''+counterId+'">';
								clonedTemplate+='<option value="0">Select Subject</option>';
							clonedTemplate+='</select>';
							clonedTemplate+='<div class="m_top10"  id="subWorkTypeId'+typeVal+''+counterId+'Err"></div>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';
				clonedTemplate+='</div>';
					clonedTemplate+='<div class="m_top10">';
						clonedTemplate+='<div class="row m_top10">';
							clonedTemplate+='<div class="col-sm-3">';
								clonedTemplate+='<label>Location Level</label>';
								clonedTemplate+='<select name="worksList['+counterId+'].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'">';
										clonedTemplate+='<option value="0">Select Level</option>';
										clonedTemplate+='<option value="3">District</option>';
										clonedTemplate+='<option value="4">Constituency</option>';
										clonedTemplate+='<option value="5">Mandal</option>';
										//clonedTemplate+='<option value="6">Panchayat</option>';
								clonedTemplate+='</select>';
								clonedTemplate+='<div class="m_top10"  id="locationLevelId'+typeVal+''+counterId+'Err"></div>';
							clonedTemplate+='</div>';
							clonedTemplate+='<div class="col-sm-2 districtCls'+typeVal+''+counterId+'" style="display:none">';
								clonedTemplate+='<label>District</label>';
								clonedTemplate+='<select  name="worksList['+counterId+'].candidateAddressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange" id="districtId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
									clonedTemplate+='<option value="0">Select District</option>';
								clonedTemplate+='</select>';
								clonedTemplate+='<div class="m_top10"  id="districtId'+typeVal+''+counterId+'Err"></div>';
							clonedTemplate+='</div>';
							clonedTemplate+='<div class="col-sm-2 constituencyCls'+typeVal+''+counterId+'" style="display:none">';
								clonedTemplate+='<label>Constituency</label>';
								clonedTemplate+='<select  name="worksList['+counterId+'].candidateAddressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
									clonedTemplate+='<option value="0">Select Constituency</option>';
								clonedTemplate+='</select>';
							clonedTemplate+='</div>';
							clonedTemplate+='<div class="col-sm-2 mandalCls'+typeVal+''+counterId+'" style="display:none">';
								clonedTemplate+='<label>Mandal</label>';
								clonedTemplate+='<select   name="worksList['+counterId+'].candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange" id="mandalId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
									clonedTemplate+='<option value="0">Select Mandal</option>';
								clonedTemplate+='</select>';
							clonedTemplate+='</div>';
						clonedTemplate+='</div>';
					clonedTemplate+='</div>';
						clonedTemplate+='<div class="row m_top20">';
							clonedTemplate+='<div class="col-sm-12">';
								clonedTemplate+='<h3 class="panel-title f_18 text-capital">grievance DISCRIPTION</h3>';
								clonedTemplate+='<textarea   name="worksList[0].projectDescription" class="form-control m_top20 workNoTextAreaValidCls'+typeVal+'" rows="4" id="textArea'+typeVal+''+counterId+'"></textarea>';
								clonedTemplate+='<div class="m_top10"  id="textArea'+typeVal+''+counterId+'Err"></div>';
								//clonedTemplate+='<textarea   name="worksList[0].projectDescription" class="form-control m_top20" rows="4" id="textArea'+typeVal+''+counterId+'"></textarea>';
							clonedTemplate+='</div>';
						clonedTemplate+='</div>';
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	return clonedTemplate;
}
$(document).on("change",".locationLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	setLeveles(levelVal,counterId,typeVal);
});
function setLeveles(levelVal,counterId,typeVal){
	if(levelVal == 3){
		$("#districtId"+typeVal+counterId).html('');
		$("#districtId"+typeVal+counterId).trigger("chosen:updated");
		getAllDistrictsInState(typeVal,counterId);
		
		$(".districtCls"+typeVal+counterId).show();
		$(".constituencyCls"+typeVal+counterId).hide();
		$(".mandalCls"+typeVal+counterId).hide();
		$(".panchayatCls"+typeVal+counterId).hide();
	}else if(levelVal == 4){
		 $("#districtId"+typeVal+counterId).html('');
		 $("#districtId"+typeVal+counterId).trigger("chosen:updated");
		 getAllDistrictsInState(typeVal,counterId);
		 $("#constituencyId"+typeVal+counterId).html('');
		 $("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
		 
		$(".districtCls"+typeVal+counterId).show();
		$(".constituencyCls"+typeVal+counterId).show();
		$(".mandalCls"+typeVal+counterId).hide();
		$(".panchayatCls"+typeVal+counterId).hide();
	}else if(levelVal == 5){
		$("#districtId"+typeVal+counterId).html('');
		$("#districtId"+typeVal+counterId).trigger("chosen:updated");
		getAllDistrictsInState(typeVal,counterId);
		$("#constituencyId"+typeVal+counterId).html('');
		$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
		$("#mandalId"+typeVal+counterId).html('');
		$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
		
		$(".districtCls"+typeVal+counterId).show();
		$(".constituencyCls"+typeVal+counterId).show();
		$(".mandalCls"+typeVal+counterId).show();
		$(".panchayatCls"+typeVal+counterId).hide();
	}else if(levelVal == 6){
		$("#districtId"+typeVal+counterId).html('');
		$("#districtId"+typeVal+counterId).trigger("chosen:updated");
		getAllDistrictsInState(typeVal,counterId);
		$("#constituencyId"+typeVal+counterId).html('');
		$("#constituencyId"+typeVal+counterId).trigger("chosen:updated");
		$("#mandalId"+typeVal+counterId).html('');
		$("#mandalId"+typeVal+counterId).trigger("chosen:updated");
		$("#panchayatId"+typeVal+counterId).html('');
		$("#panchayatId"+typeVal+counterId).trigger("chosen:updated");
		
		$(".districtCls"+typeVal+counterId).show();
		$(".constituencyCls"+typeVal+counterId).show();
		$(".mandalCls"+typeVal+counterId).show();
		$(".panchayatCls"+typeVal+counterId).show();
	}else{
		$(".districtCls"+typeVal+counterId).hide();
		$(".constituencyCls"+typeVal+counterId).hide();
		$(".mandalCls"+typeVal+counterId).hide();
		$(".panchayatCls"+typeVal+counterId).hide();
	}
}
$(document).on("change",".districtLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");//ll
	getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,"","constituencyId");
	
});
$(document).on("change",".constituencyLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal,"","mandalId");
});
$(document).on("change",".mandalLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	//getPanchayatsByTehsilId(levelVal,counterId,typeVal);
});

$(document).on("change","#districtrepresent",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getConstituencyNamesByDistrictId(levelVal,"","represent","","constituency");
	
});
$(document).on("change","#constituencyrepresent",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,"","represent","","mandal");
	
});
$(document).on("change","#mandalrepresent",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getPanchayatsByTehsilId(levelVal,1,"represent");
	
});
$(document).on("change","#districtCandId",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getConstituencyNamesByDistrictId(levelVal,"","","","constituencyCanId");
});

function getAllDistrictsInState(typeVal,counterId,districtId,divId){
	var searchType="all";
	if(typeVal=="popup"){
		$("#districtCandId").html('');
		searchType = "refCandidate";
	}else if(counterId == null || counterId ==""){
		$("#district"+typeVal+counterId).html('');
	}else if(counterId !="" && parseInt(counterId)>0){
		$("#districtId"+typeVal+counterId).html('');
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
			if(typeVal=="popup"){
				$("#districtCandId").append('<option value="0">All</option>');
			}else if(counterId == null || counterId ==""){		
				$("#district"+typeVal+counterId).append('<option value="0">Select District</option>');
			}else if(counterId !="" && parseInt(counterId)>0){
			 $("#districtId"+typeVal+counterId).append('<option value="0">Select District</option>');
			}
			for(var i in result){				
				if(typeVal=="popup"){
					$("#districtCandId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}else if(counterId == null || counterId ==""){	
					if(result[i].id == districtId){				
						$("#district"+typeVal+counterId).append('<option value="'+result[i].id+'" selected>'+result[i].name+' </option>');
					}else{
						$("#district"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}else  if(counterId !="" && parseInt(counterId)>0){
					if(districtId == result[i].id){
						$("#districtId"+typeVal+counterId).append('<option value="'+result[i].id+'" selected>'+result[i].name+' </option>');
					}else{
						$("#districtId"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
			}
		}
		if(typeVal=="popup"){
			$("#districtCandId").trigger('chosen:updated');
		}
		else if(counterId == null || counterId =="")		
			$("#district"+typeVal+counterId).trigger('chosen:updated');
		else if(counterId !="" && parseInt(counterId)>0)
			$("#districtId"+typeVal+counterId).trigger('chosen:updated');
	});	
}
function getConstituencyNamesByDistrictId(levelVal,counterId,typeVal,conId,divId){
	
	var searchVal='';
	  $("#constituencyId"+typeVal+counterId).html('');
	  $("#constituencyrepresent").html('');
	  $("#constituencyCanId").html('');
	  if(typeVal =="refCandidate"){
		searchVal =="refCandidate"
	}else{
		$("#constituencyId"+typeVal+counterId).html('');
		$("#constituencyrepresent").html('');
		searchVal ="all";
	}	
	  var json = {
		  districtId:levelVal,
		   searchType:searchVal,
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
			 $("#"+divId+typeVal+counterId).html('<option value="0">Select Constituency</option>');
			for(var i in result){
				if(conId == result[i].locationId){
					$("#"+divId+''+typeVal+counterId).append('<option value="'+result[i].locationId+'" selected>'+result[i].locationName+' </option>');
				}else{
					$("#"+divId+''+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				}
			}
		}
		$("#"+divId+''+typeVal+counterId).trigger('chosen:updated');
	});	
}
function getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal,mandalId,divId){
		$("#mandalId"+typeVal+counterId).html('');//kkb
		$("#mandalrepresent").html('');
	  var json = {
		  constituencyId:levelVal,
		   searchId:0,
		   searchType:"all"
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
			 $("#"+divId+''+typeVal+counterId).html('<option value="0">Select Mandal</option>');
			for(var i in result){
				var tehsilId = result[i].key;
				var levelId = tehsilId.toString().substr(1, 4);
				if(result[i].electionType != null){
					if(mandalId == levelId){
						$("#"+divId+''+typeVal+counterId).append('<option value="'+levelId+'" selected>'+result[i].value+' '+result[i].electionType+'</option>');
					}else{
						$("#"+divId+''+typeVal+counterId).append('<option value="'+levelId+'">'+result[i].value+' '+result[i].electionType+'</option>');
					}
				}else{
					if(mandalId == levelId){
					$("#"+divId+''+typeVal+counterId).append('<option value="'+levelId+'" selected>'+result[i].value+'</option>');
					}else{
						$("#"+divId+''+typeVal+counterId).append('<option value="'+levelId+'">'+result[i].value+'</option>');
					}
				}	
			}
		}
		$("#"+divId+''+typeVal+counterId).trigger('chosen:updated');
	});	
}
function saveRepresentRequestDetails(){
	 var formData = new FormData();
	$('#adminProfileForm input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			
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
					var name = $('#'+id+'').attr('name');
					if(this.files !=null && this.files.length>0){
						//alert(name);
						//name = name.replace("[","");
						//name = name.replace("]","");
						//alert(name);
						for(var i in this.files){
							formData.append(name, this.files[i]);
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
	
	/*
	    $('#adminProfileForm input, #adminProfileForm select').each(
		  function(){
			console.log(formData);			  
			var input = $(this);
			var attr = input.attr('name');
			alert(attr);
			try{    
			   if (typeof attr !== typeof undefined && attr !== false) {
				   if(input.attr('type') == "file") {  
						//formData.append(input.attr('name'), this.files[0]); 
					}
				   else{           
				   alert(input.val());
						formData.append(input.attr('name'), input.val());
					}
			   }           
			}catch(e) {
			  console.log(e);
			}
		  });
	*/
	
/*	formData.append("petitionMemberVO.name","CBN");
	 formData.append("petitionMemberVO.memberType",2);
	 formData.append("petitionMemberVO.endorsmentDate","2017-12-01");
	 formData.append("petitionMemberVO.representationDate","2017-12-01");
	 formData.append("petitionMemberVO.mobileNo","9581434970");
	 formData.append("petitionMemberVO.emailId","srishailam@itgrids.com");
	 formData.append("petitionMemberVO.voterCardNo","WARDU765T");	 
	// formData.append("petitionMemberVO.filesList",$('input[type=file]'));
	 
	 formData.append("candidateAddressVO.stateId",2);
	 formData.append("candidateAddressVO.districtId",19);
	 formData.append("candidateAddressVO.parliamentId",495);
	 formData.append("candidateAddressVO.assemblyId",232);
	 formData.append("candidateAddressVO.tehsilId",2284);
	 formData.append("candidateAddressVO.panchayatId",21);
	 
	 //formData.append("petitionMemberVO.file",$('input[type=file]')[0].files[0]);
	 
	 formData.append("referrerCandidateId",2);
	 formData.append("filesList",$('input[type=file]')[0].files[0]); 
	 
	 formData.append("workName","test work details");
	 formData.append("noOfWorks",2);
	 formData.append("estimationCost",2000);
	 formData.append("subject","test work details");
	 formData.append("subjectId",2);
	 formData.append("deptId",2);
	 formData.append("isPreviousPetition","Y");
	 formData.append("previousPetitionRefNo",2);
	 formData.append("projectDescription",2);
	 
	 formData.append("worksList[0].deptId",2);
	 formData.append("worksList[0].locationLevelId",2);
	 formData.append("worksList[0].locationValue",2);
	 
	 formData.append("worksList[0].candidateAddressVO.stateId",2);
	 formData.append("worksList[0].candidateAddressVO.districtId",19);
	 formData.append("worksList[0].candidateAddressVO.parliamentId",495);
	 formData.append("worksList[0].candidateAddressVO.assemblyId",232);
	 formData.append("worksList[0].candidateAddressVO.tehsilId",183);
	 formData.append("worksList[0].candidateAddressVO.panchayatId",11111);
	 
	 */
	// return;
	 console.log(formData);
	  $.ajax({
			url: $("#adminProfileForm1").attr("action"),
			data: formData,
			type: "POST",               
			processData: false,
			contentType: false,
			success: function(ajaxresp) {
				alert("success");
				console.log(ajaxresp);
			},
			error: function(request,error) { 
				alert("error");				
			}
     });	 
}
function getPetitionDepartmentList(divId,typeVal,deptId){
	
    $("#subjectId"+typeVal).html('');
	$("#departmentId"+typeVal).html('');
	$("#workTypeId"+typeVal+"1").html('');
	  var json = {
		 searchType:"all"
		}           
	$.ajax({              
		type:'POST',    
		url: 'getPetitionDepartmentList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#departmentId"+typeVal).append('<option value="0">Select Department</option>');
			for(var i in result){
				if(deptId == result[i].key){
				$("#"+divId+""+typeVal).append('<option value="'+result[i].key+'" selected>'+result[i].value+' </option>');
				}else{
					$("#"+divId+""+typeVal).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
				$("#workTypeId"+typeVal+"1").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#departmentId"+typeVal).trigger('chosen:updated');
	});	
}
function getPetitionDesignationList(){
    $("#designationsId").html('');
	  var json = {
		  searchType:"refCandidateDesignations"
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
$(document).on("click",".searchCandidateCls",function(){
	var typeVal = $(this).attr("attr_type");
	$(".selectionSearchDetailsCls").attr("attr_type",typeVal);
		$("#districtCandId").html('<option value="0">Select District</option>');
		$("#constituencyCanId").html('<option value="0">Select Constituency</option>');
		$("#districtCandId").trigger("chosen:updated");
		$("#constituencyCanId").trigger("chosen:updated");
	if(typeVal == "self"){
		$("#candidateDetailsDivId").html('');
		$("#candidateSearchModelDivId").modal("show");
		getPetitionDesignationList();
		getAllDistrictsInState("","");
	}else{
		$("#candidateDetailsDivId").html('');
		$("#candidateSearchModelDivId").modal("show");
		getPetitionDesignationList();
		//getAllDistrictsInState("","");
	}
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
var alreadyCandidateId='';
$(document).on("click",".candidateAddedView",function(){
	
	var typeVal = $(this).attr("attr_type");
	if($("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").hasClass("memberAdded")){
		alert("Already Member Added")
	}else{
		$("#candidateDetails"+typeVal+"DivId").html('');
		$(".representation-selected").removeClass("display_block");
		var candidateId = $(this).attr("attr_candidateId");
		alreadyCandidateId = candidateId;
		
		$("#candidateDetails"+typeVal+"DivId").html($("#bgColorCandidates"+candidateId).html());
		$("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").addClass("memberAdded")
		$(this).parent().find(".representation-selected").addClass("display_block");
		$(this).removeClass("candidateAddedView");
		$(".showRemoveIcon").show();
	}
});
$(document).on("click",".representation-remove",function(){
	var typeVal = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	$("#bgColorCandidates"+candidateId).find(".representation-selected").removeClass("display_block");
	setTimeout(function(){ 
		$("#bgColorCandidates"+candidateId).find(".bgColorCandidatesView").addClass("candidateAddedView")
		$("#candidateDetails"+typeVal+"DivId").find(".bgColorCandidatesView").removeClass("memberAdded")
	}, 1500);
	
	return;
})
$(document).on("click",".showRemoveIcon",function(){
	var typeVal = $(this).attr("attr_type");
	$(this).remove();
	$("#candidateDetails"+typeVal+"DivId").html('');
	alreadyCandidateId='';
})

function buildPetitionReferredMemberDetails(result,typeVal){
	var str='';
	
	str+='<div class="col-sm-12">';	
	str+='<table style="width:100%" id="dataTableCandidateId">';
	str+='<thead>';
		str+='<tr>';
			str+='<th></th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		str+='<td>';
		str+='<div id="bgColorCandidates'+result[i].referrerCandidateId+'" style="position:relative;">';
		if(alreadyCandidateId == result[i].referrerCandidateId){
			str+='<div class="bgColorCandidatesView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+'>';
				//str+='<span class="pull-right"><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove" style="background-color: rgba(0, 0, 0, 0.4); border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;" ></i></span>';
				str+='<div class="row">';
					str+='<div class="representation-selected display_block" id="candidateRemove'+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove" style="background-color: rgba(0, 0, 0, 0.4); border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;" ></i></span></div>';
					str+='<div class="col-sm-3">';
							str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
							str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<div class="nameAddressCss">';
							str+='<h5 class="font_weight">Name:</h5>';
							str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
							str+='<h5 class="m_top10 font_weight">Designation</h5>';
							str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
							str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'">'+result[i].candidateAddressVO.assemblyName+'  Constituency,</h5>';
							str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">'+result[i].candidateAddressVO.districtName+'  District,</h5>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-3">';
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
					
					str+='<div class="col-sm-3">';
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
					str+='</div>';
					
				str+='</div>';
		}else{
			str+='<div class="bgColorCandidatesView candidateAddedView" attr_type='+typeVal+' attr_candidateId='+result[i].referrerCandidateId+'>';
				//str+='<span class="pull-right"><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove" style="background-color: rgba(0, 0, 0, 0.4); border-radius: 50%; cursor: pointer; font-size: 12px; padding: 3px;top:-7px;" ></i></span>';
				str+='<div class="row">';
				str+='<div class="representation-selected" id="candidateRemove'+result[i].referrerCandidateId+'">Member Selected <span><i attr_candidateId='+result[i].referrerCandidateId+' attr_type='+typeVal+' class="glyphicon glyphicon-remove representation-remove" style="background-color: rgba(0, 0, 0, 0.4); border-radius: 50%; cursor: pointer; font-size: 14px; padding: 5px;top:-3px;" ></i></span></div>';
				str+='<div class="col-sm-3">';
						str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss"></img>';
						str+='<span style="position: relative; left: -31px; top: -62px;"><img src="Assests/images/TDP.PNG" class="smallerImg"></img></span>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<div class="nameAddressCss">';
						str+='<h5 class="font_weight">Name:</h5>';
						str+='<h5 class="m_top5">'+result[i].petitionMemberVO.name+'</h5>';
						str+='<h5 class="m_top10 font_weight">Designation</h5>';
						str+='<h5 class="text_bold m_top10">'+result[i].petitionMemberVO.memberType+',</h5>';
						str+='<h5 class="m_top5" attr_assemblY_id="'+result[i].candidateAddressVO.assemblyId+'">'+result[i].candidateAddressVO.assemblyName+'  Constituency,</h5>';
						str+='<h5 class="m_top5" attr_district_id="'+result[i].candidateAddressVO.districtId+'">'+result[i].candidateAddressVO.districtName+'  District,</h5>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="col-sm-3">';
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
				
				str+='<div class="col-sm-3">';
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
				str+='</div>';
				
			str+='</div>';
		}
		
		
		
		str+='</div>';
		str+='</td>';
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#candidateDetailsDivId").html(str);
	$("#dataTableCandidateId").dataTable({
		"paging":   true,
		"info":     false,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
	$("#dataTableCandidateId").removeClass("dataTable");
	$("#dataTableCandidateId_wrapper").css("margin-top","20px")
}
getRepresentativeSearchWiseDetails();
function getRepresentativeSearchWiseDetails(){
 
  var searchValue = 9581434970
    var json = {
    filterType :"mobileNo",
	filterValue:searchValue
    }        
  $.ajax({                
    type:'POST',    
    url: 'getRepresentativeSearchWiseDetails',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
  
  }); 
}  
function  enableWorks(value,divId,typeVal){
	$(".appendDiv"+typeVal).html('');	
	//$('#'+divId+'').hide()
	if(parseInt(value)>0){
		$('#'+divId+'').show();
	}
}
function getChangeValue(keyValue){
	var type = keyValue;
	if(type =='Y' && type !=0) {
		$("#previousPetitionRefId").show();
	}else{
		$("#previousPetitionRefId").hide();
	}
}
$(document).on("click","#referelSubmitBtnId",function(){
	var typeVal = $('input[name=memberTypeRadio]:checked').attr("attr_type");
	
	$("#name"+typeVal+"Err").html("");
	$("#mobileNumber"+typeVal+"Err").html("");
	$("#emailId"+typeVal+"Err").html("");
	$("#voterId"+typeVal+"Err").html("");
	$("#district"+typeVal+"Err").html("");
	$("#constituency"+typeVal+"Err").html("");
	$("#mandal"+typeVal+"Err").html("");
	$("#panchayat"+typeVal+"Err").html("");
	
	$("#noofWork"+typeVal+"Err").html('');
	$("#nameofWork"+typeVal+"Err").html("");
	$("#workCost"+typeVal+"Err").html("");
	$("#subjectId"+typeVal+"Err").html("");
	$("#departmentId"+typeVal+"Err").html("");
	$("#previousPetitionId"+typeVal+"Err").html("");
	$("#previousPetitionId"+typeVal+"Err").html("");
	
	if(typeVal == 'represent'){
		
		var name = $("#name"+typeVal).val();
		var mobileNumber = $("#mobileNumber"+typeVal).val();
		var emailId = $("#emailId"+typeVal).val();
		var voterId = $("#voterId"+typeVal).val();
		var district = $("#district"+typeVal).val();
		var constituency = $("#constituency"+typeVal).val();
		var mandal = $("#mandal"+typeVal).val();
		var panchayat = $("#panchayat"+typeVal).val();
		
		var emialRegExp =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		var phonePattern= /^\d{10}$/;
		
		if(name == null || name.trim().length== 0 || name == 'undefined' || name=='' ){
			$("#name"+typeVal+"Err").html("<h5>Please Enter Name</h5>");
			return;
		}
		if(mobileNumber == null  || name == 'undefined' || mobileNumber=='' ){
			$("#mobileNumber"+typeVal+"Err").html("<h5>Please Enter mobile Number</h5>");
			return;
		}
			if(mobileNumber.length !=10 || !phonePattern.test(mobileNumber)){
			$("#mobileNumber"+typeVal+"Err").html("<h5>Invalid phone number</h5>"); 
			return;
			}
		if(emailId == null || emailId.trim().length == 0 || emailId == ''){
			$("#emailId"+typeVal+"Err").html("<h5>Please Enter email Id </h5>");
			return;
		}
		if(!emialRegExp.test(emailId)){
	    $("#emailId"+typeVal+"Err").html("<h5>Invalid  E-Mail Id!</h5>"); 
		return;
	}	
		if(voterId == null || voterId.trim().length== 0 || voterId == 'undefined' || voterId=='' ){
			$("#voterId"+typeVal+"Err").html("<h5>Please Enter voterId</h5>");
			return;
		}
		if(district == null || district <= 0){
			$("#district"+typeVal+"Err").html("<h5>Please Select District<h5>");
			return;
		}
		if(constituency == null || constituency <= 0){
			$("#constituency"+typeVal+"Err").html("<h5>Please Select Constituency<h5>");
			return;
		}
		if(mandal == null || mandal <= 0){
			$("#mandal"+typeVal+"Err").html("<h5>Please Select Mandal<h5>");
			return;
		}
		if(panchayat == null || panchayat <= 0){
			$("#panchayat"+typeVal+"Err").html("<h5>Please Select Panchayat<h5>");
			return;
		}
	}
	
	var nameOfWork = $("#nameofWork"+typeVal).val();
	var noOfWork = $("#noofWork"+typeVal).val();
	var WorkCost = $("#workCost"+typeVal).val();
	var subjectId = $("#subjectId"+typeVal).val();
	var departmentId = $("#departmentId"+typeVal).val();
	var previousPetitionId = $("#previousPetitionId"+typeVal).val();
	var previousPetitionId = $("#previousPetitionId"+typeVal).val();
	var textArea = $("#textArea"+typeVal).val();
	
	
	if(nameOfWork == null || nameOfWork.trim().length== 0 || nameOfWork == 'undefined' || nameOfWork=='' ){
		$("#nameofWork"+typeVal+"Err").html("<h5>Please Enter Work Name</h5>");
		return;
	}
	if(noOfWork == null || noOfWork.trim().length== 0 || noOfWork == 'undefined' || noOfWork=='' || isNaN(noOfWork)){
		$("#noofWork"+typeVal+"Err").html("<h5>Please Enter Number Of Works</h5>");
		return;
	}
	if(WorkCost == null || WorkCost.trim().length== 0 || WorkCost == 'undefined' || WorkCost=='' || isNaN(WorkCost) ){
		$("#workCost"+typeVal+"Err").html("<h5>Please Enter Work Cost</h5>");
		return;
	}
	if(subjectId == null || subjectId <= 0){
		$("#subjectId"+typeVal+"Err").html("<h5>Please Select Subject<h5>");
		return;
	}
	if(departmentId == null || departmentId <= 0){
		$("#departmentId"+typeVal+"Err").html("<h5>Please Select Department<h5>");
		return;
	}
	if(previousPetitionId == null || previousPetitionId <= 0){
		$("#previousPetitionId"+typeVal+"Err").html("<h5>Please Select Previous Petition<h5>");
		return;
	}
	if(previousPetitionId == null || previousPetitionId <= 0){
		$("#previousPetitionId"+typeVal+"Err").html("<h5>Please Select Previous Petition<h5>");
		return;
	}
	if(textArea == null || textArea.trim().length== 0 || textArea == 'undefined' || textArea=='' ){
		$("#textArea"+typeVal+"Err").html("<h5>Please Enter Project Discription</h5>");
		return;
	}
	
	$(".workTypeCls"+typeVal).each(function(){//locationLevelValidCls
		var workType=$(this).val();
		var allErrDivId=$(this).attr("id");
		$("#"+allErrDivId+"Err").html("");
		if(workType == null || workType <=0){
			var errDivId=$(this).attr("id");
			$("#"+errDivId+"Err").html("<h5>Please Select Work  Type</h5>");
			return;
		}
	});
	$(".locationLevelValidCls"+typeVal).each(function(){//locationLevelValidCls  workNoTextAreaValidCls
		var locationLevelVal=$(this).val();
		var allErrDivId=$(this).attr("id");
		$("#"+allErrDivId+"Err").html("");
		if(locationLevelVal == null || locationLevelVal ==0){
			var errDivId=$(this).attr("id");
			$("#"+errDivId+"Err").html("<h5>Please Select Location Level</h5>");
			return;
		}else{
			var errDivId2=$(this).attr("id");
			//alert(errDivId2);
		var districtVal=$("#"+errDivId2).closest('.districtLevelChange').val();
			//alert(districtVal);
		}
	});
	$(".workNoTextAreaValidCls"+typeVal).each(function(){//locationLevelValidCls  workNoTextAreaValidCls
		var workNoTextAreaVal=$(this).val();
		var allErrDivId=$(this).attr("id");
		$("#"+allErrDivId+"Err").html("");
		if(workNoTextAreaVal == null || workNoTextAreaVal.trim().length ==0 || workNoTextAreaVal == ''){
			var errDivId=$(this).attr("id");
			$("#"+errDivId+"Err").html("<h5>Please Enter Project Discription </h5>");
			return;
		}
	});

});


function getRepresentationRequestDetailsByRepresentationRequestId(representationMemberId){	
	$("#districtrepresent").html('');
	var json = {
		  representationMemberId:representationMemberId
		}
	$.ajax({                
		type:'POST',    
		url: 'getRepresentationRequestDetailsByRepresentationRequestId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
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

//getPetitionSubjectList();
function getPetitionSubjectList(divId,typeVal,counterId,subjectId){
	var json = {};
	$.ajax({              
		type:'POST',    
		url: 'getPetitionSubjectList',
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
				if(subjectId == result[i].key){
				$("#"+divId+""+typeVal+""+counterId+"").append('<option value="'+result[i].key+'" selected>'+result[i].value+' </option>');
				}else{
					$("#"+divId+""+typeVal+""+counterId+"").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		$("#"+divId+""+typeVal+""+counterId+"").trigger('chosen:updated');
	});	
}


function getPetitionSubSubjectList(subjectId,divId,suSubjectId){
$("#"+divId).html('<option value="0">Select Sub Subject</option>');
	var json = {
		subjectId : subjectId
	};
	$.ajax({              
		type:'POST',    
		url: 'getPetitionSubSubjectList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#"+divId).html('<option value="0">All</option>');
			for(var i in result){
				if(suSubjectId == result[i].key){
					$("#"+divId).append('<option value="'+result[i].key+'" selected>'+result[i].value+' </option>');
				}else{
					$("#"+divId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				}
			}
		}
		$("#"+divId).trigger('chosen:updated');
	});	
}
//getPetitionLeadDetailsList();
function getPetitionLeadDetailsList(){
	var json = {
		
	};
	$.ajax({              
		type:'POST',    
		url: 'getPetitionLeadDetailsList',
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
//getPetitionBriefLeadList();
function getPetitionBriefLeadList(){
			var json = {
				
			};
	$.ajax({              
		type:'POST',    
		url: 'getPetitionBriefLeadList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :  function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#constituency1CanId").append('<option value="0">All</option>');
			for(var i in result){
				$("#constituencyCa1nId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#constituencyC1anId").trigger('chosen:updated');
	});	
}

getPetitionGrantList();
function getPetitionGrantList(){
	var json = {
		
	};
	$.ajax({              
		type:'POST',    
		url: 'getPetitionGrantList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#constituencyCanId1").append('<option value="0">All</option>');
			for(var i in result){
				$("#constituencyCanId1").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#constituencyCanId1").trigger('chosen:updated');
	});	
}
//getPetitionStatusList();
function getPetitionStatusList(){
	var json = {
		
	};
	$.ajax({              
		type:'POST',    
		url: 'getPetitionStatusList',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			 $("#constituencyCanId1").append('<option value="0">All</option>');
			for(var i in result){
				$("#constituenc1yCanId").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#constituencyCanId1").trigger('chosen:updated');
	});	
}
$(document).on("change",".subjecOnchngeCls",function(){
	var typeVal='';
	if($("#self").is(':checked')){
		typeVal=$("#self").attr('attr_type');
	}else if($("#Representee").is(':checked')){
		typeVal=$("#Representee").attr('attr_type');
	}
	var subjectId=$("#subjectId"+typeVal).val();
	var divId='subSubjectId'+typeVal;
	getPetitionSubSubjectList(subjectId,divId);
});

	
function getRepresentationRequestDetailsByRepresentationRequestId(representationMemberId){  
  $("#districtrepresent").html('');
  var json = {
      representationMemberId:representationMemberId
    }
  $.ajax({                
    type:'POST',    
    url: 'getRepresentationRequestDetailsByRepresentationRequestId',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
	  globalMemberObject=result;
	  buildMemberDetails(result);
  });  
}

function buildMemberDetails(result){
	var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				str+='<div class="panel-group">';
					str+='<div class="panel panel-default panel-lightGreen">';
						str+='<div class="panel-heading">';
							str+='<h4 class="panel-title f_22" style="font-weight:normal !important;">REPRESENTATION REQUEST</h4>';
						str+='</div>';
						str+='<form action="saveRepresentRequestDetails" id="adminProfileForm" name="adminProfileFormName" enctype="multipart/form-data" method="post">';
							str+='<div class="panel-body">';
								str+='<div class="row">';
									str+='<div class="col-sm-12">';
										str+='<h3 class="panel-title f_22">REPRESENTEE DETAILS</h3>';
										
										str+='<div class="row m_top20">';
												str+='<h4>Representation Date</h4>';
												str+='<div class="col-sm-2">';
													str+='<div class="input-group inline-block m_top10">';
														str+='<span class="input-group-addon">';
															str+='<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>';
														str+='</span>';
														str+='<input type="text" name="petitionMemberVO.representationDate" class="form-control" id="dateRangePickerMGNF" value="'+result.petitionMemberVO.representationDate+'"/>';
													str+='</div>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4>Representation By</h4>';
													str+='<div class="row m_top10">';
														str+='<div class="col-sm-3">';
															str+='<div class="borederCss">';
																str+='<input type="radio" name="petitionMemberVO.memberType" value="SELF" id="self" attr_name="selfMemberType" class="selfRepresenceCls" attr_type="self" >';
																str+='<label><h5>SELF</span></h5>'
															str+='</div>';
														str+='</div>';
														str+='<div class="col-sm-6">';
															str+='<div class="borederCss">';
																str+='<input type="radio" name="petitionMemberVO.memberType"  value="REPRESENT"  id="Representee"  class="selfRepresenceCls" attr_type="represent" attr_name="representMemberType"  checked>';
																str+='<label><h5 class="text-capital">Representee</h5></label>';
															str+='</div>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="row m_top20">';
												str+='<div class="col-sm-12">';
													str+='<div id="representeeEditDivId"></div>';
													str+='</div>';
											str+='</div>';
											str+='<div class="row m_top20">';
												str+='<div class="col-sm-12">';
													str+='<div id="selfDetailsDivId"></div>';
													str+='</div>';
											str+='</div>';
											str+='<div class="row m_top20">';
												str+='<div class="col-sm-12">';
												str+='<div id="representDetailsDivId"></div>';
											str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</form>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#memberDetailsDivId").html(str);
		
		$("#dateRangePickerMGNF").daterangepicker({
			singleDatePicker: true,
			locale: {
				format: 'DD-MM-YYYY'
			}
		});
		if(result.representeeType=="SELF"){
			$("#self").prop("checked",true);
			globalTypeVal='self';
		}else{
			$("#Representee").prop("checked",true);
			globalTypeVal='represent';
		}
		buildSelfAndRepresenteeMemberEditDeatils(globalTypeVal,result)
}

function buildSelfAndRepresenteeMemberEditDeatils(typeVal,result){
	//$("#"+typeVal+"DetailsDivId").html(spinner);
	var str='';
	if(typeVal == "represent"){
	str+='<div id="representeeOnLOadEditDivId">';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="font_weight text-capital f_22">Representee Details:</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';
				str+='<label>Name</label>';//$("#name"+typeVal+"Err").html("<h5>Please Enter Name</h5>");
				str+='<input type="text"  name="petitionMemberVO.name"  value="'+result.petitionMemberVO.name+'" class="form-control m_top10 height45" id="name'+typeVal+'" placeholder="Enter Name" value="">';
				str+='<div id="name'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Mobile Number</label>';
				str+='<input type="text" name="petitionMemberVO.mobileNo"  value=""  class="form-control m_top10 height45" id="mobileNumber'+typeVal+'" placeholder="Enter Mobile Number">';
				str+='<div id="mobileNumber'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>E-mail ID</label>';
				str+='<input type="text" name="petitionMemberVO.emailId"   value="" class="form-control m_top10 height45" id="emailId'+typeVal+'" placeholder="Enter E-mail ID">';
				str+='<div id="emailId'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Voter ID</label>';
				str+='<input type="text"  name="petitionMemberVO.voterCardNo"  value=""  class="form-control m_top10 height45" id="voterId'+typeVal+'" placeholder="Enter Voter ID">';
				str+='<div id="voterId'+typeVal+'Err"></div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>District</label>';
				str+='<select   name="candidateAddressVO.districtId" class="form-control chosen-select m_top10" id="district'+typeVal+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
				str+='<div id="district'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Constituency</label>';
				str+='<select  name="candidateAddressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+typeVal+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
				str+='<div id="constituency'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Mandal</label>';
				str+='<select   name="candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10" id="mandal'+typeVal+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
				str+='<div id="mandal'+typeVal+'Err"></div>';
			str+='</div>';
			
		str+='</div>';
	str+='</div>';
	}
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="font_weight text-capital f_22">Self Candidate  Details:</h3>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<span class="pull-right showRemoveIcon" attr_type="'+typeVal+'" style="background-color: rgb(221, 221, 221); top: 8px; border-radius: 50%; border: 1px solid rgb(0, 0, 0); padding: 3px; position: relative; cursor: pointer;display:none;z-index:999;"><i class="glyphicon glyphicon-remove"></i></span>';
				str+='<div id="candidateDetails'+typeVal+'DivId"></div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h4 class="searchCss searchCandidateCls" attr_type="'+typeVal+'"><i class="fa fa-search" aria-hidden="true" style="font-size:20px;"></i> Click here to Search Candidate details </h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="m_top20" id="referelLetterDivId">';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h4 class="f_18">REFERRAL LETTER</h4>';
				str+='<input type="file"   name="filesList"   id="update_TourFileId2'+typeVal+'" multiple="multiple" class="m_top20"/>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="" id="parentWorkDetailsDivId">';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="panel-title f_18">WORK TYPE DETAILS</h3>';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-3">';
						str+='<label>Subject</label>';
						str+='<select  name="subjectId"  class="form-control chosen-select m_top10 subjecOnchngeCls" id="subjectId'+typeVal+'">';
							str+='<option value="0">Select Subject</option>';
						str+='</select>';
						str+='<div id="subjectId'+typeVal+'Err"></div>';
					str+='</div>';
						str+='<div class="col-sm-3">';
						str+='<label>Sub Subject</label>';
						str+='<select  name="subSubjectId"  class="form-control chosen-select m_top10" id="subSubjectId'+typeVal+'">';
							str+='<option value="0">Select Sub Subject</option>';
						str+='</select>';
						str+='<div id="subSubjectId'+typeVal+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Department</label>';
						str+='<select  name="deptId"  class="form-control chosen-select m_top10" id="departmentId'+typeVal+'">';
							str+='<option value="0">Select Department</option>';
						str+='</select>';
						str+='<div id="departmentId'+typeVal+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Previous Petition</label>';
						str+='<select  name="isPreviousPetition"  class="form-control chosen-select m_top10" id="previousPetitionId'+typeVal+'" onChange = getChangeValue(this.value)>';
							str+='<option value="0">Select Previous Petition</option>';
							str+='<option value="Y">YES</option>';
							str+='<option value="N">No</option>';
						str+='</select>';
						str+='<div id="previousPetitionId'+typeVal+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-3" style="display:none;" id = "previousPetitionRefId">';
						str+='<label>Previous Petition No (Endt NO)</label>';
						str+='<input  name="previousPetitionRefNo"  type="text" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="101/20/11/2017">';
						str+='<div id="workCost'+typeVal+'Err"></div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-6">';
						str+='<label>Name of the Work</label>';
						str+='<input  name="workName"  type="text" class="form-control m_top10 height45" id="nameofWork'+typeVal+'" placeholder="Enter Name Of Work" value="'+result.workName+'">';
						str+='<div id="nameofWork'+typeVal+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>No of Works</label>';
						str+='<input   name="noOfWorks"   type="text" class="form-control m_top10 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work" onkeyUp="enableWorks(this.value,\'workDetailsDivId'+typeVal+'\',\''+typeVal+'\');" value="'+result.noOfWorks+'">';
						str+='<div id="noofWork'+typeVal+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Work Cost (Est. Cost in Lakh)</label>';
						str+='<input type="text"  name="estimationCost" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost" value="'+result.estimationCost+'">';
						str+='<div id="workCost'+typeVal+'Err"></div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-6">';
						str+='<h3 class="panel-title f_18 text-capital">grievance DISCRIPTION</h3>';
						str+='<textarea name="projectDescription"  class="form-control m_top20" rows="7" id="textArea'+typeVal+'"></textarea>';
						str+='<div id="textArea'+typeVal+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						//str+='<h3 class="panel-title f_18">PROJCT DOCUMENTS UPLOAD</h3>';
						//str+='<input type="file"  name="workFilesList"  id="projectDocUpload'+typeVal+'" multiple="multiple" class="m_top20"/>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div id="workDetailsDivId'+typeVal+'" style="display:none;"></div>';
	str+='</div>';
	
	$("#"+typeVal+"DetailsDivId").html(str);
	initializeFile(typeVal);	
	initializeFileProjDoc(typeVal);
	$(".chosen-select").chosen();
	if(typeVal == "represent"){
		getAllDistrictsInState("represent","",result.petitionMemberVO.addressVO.districtId);
		getConstituencyNamesByDistrictId(result.petitionMemberVO.addressVO.districtId,"",typeVal,result.petitionMemberVO.addressVO.assemblyId,"constituency");
		getTehsilsAndLocalElectionBodyForConstituencyId(result.petitionMemberVO.addressVO.assemblyId,"",typeVal,"","mandal");
	}
	getPetitionSubjectList("subjectId",typeVal,"",result.subjectId);
	getPetitionSubSubjectList(result.subjectId,"subSubjectId"+typeVal,result.subjectId);
	getPetitionDepartmentList("departmentId",typeVal,result.deptId)
	$("#previousPetitionId"+typeVal).val(result.isPreviousPetition);
	$("#previousPetitionId"+typeVal).trigger('chosen:updated');
	buildChildWorkDeatils(result,typeVal);
	
}
function buildChildWorkDeatils(result,typeVal){
	$("#workDetailsDivId"+typeVal).html('');
	if(result.worksList != null && result.worksList.length > 0){
		$("#workDetailsDivId"+typeVal).show();
		for(var i in result.worksList){
			var countId=parseInt(i)+1;
			buildTemplateWorkDetails(typeVal,countId,result.worksList[i]);
			}
		}
	}
function buildTemplateWorkDetails(typeVal,countId,result){
	var str='';
	str+='<div class="" id="childWorkDetailsDivId">';
		str+='<div class="col-sm-12">';
			str+='<div style="border:3px solid #dddddd;padding:10px;">';
					str+='<div  cloned_block_'+typeVal+'='+countId+'>';
						str+='<div class="m_top10">';
							str+='<h3 class="panel-title f_22">WORK No 1</h3>';
							str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<label>Select Subject</label>';
										str+='<select  name="worksList[0].deptId"  class="form-control chosen-select m_top10 workTypeCls'+typeVal+'" id="workTypeId'+typeVal+''+countId+'" onChange=getPetitionSubSubjectList(this.value,"subWorkTypeId'+typeVal+''+countId+'")>';
											str+='<option value="0">Select Subject</option>';
										str+='</select>';
										str+='<div class="m_top10"  id="workTypeId'+typeVal+''+countId+'Err"></div>';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<label>Select Sub Subject</label>';
										str+='<select  name="worksList[0].deptId"  class="form-control chosen-select m_top10 subWorkTypeCls'+typeVal+'" id="subWorkTypeId'+typeVal+''+countId+'">';
											str+='<option value="0">Select Sub Subject</option>';
										str+='</select>';
										str+='<div class="m_top10"  id="subWorkTypeId'+typeVal+''+countId+'Err"></div>';
									str+='</div>';
								str+='</div>';
						str+='</div>';
						str+='<div class="m_top10">';
								str+='<div class="row m_top10">';
									str+='<div class="col-sm-3">';
										str+='<label>Location Level</label>';
										str+='<select  name="worksList[0].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelId'+typeVal+''+countId+'" attr_counterval="'+countId+'" attr_type="'+typeVal+'">';
											str+='<option value="0">Select Level</option>';
											str+='<option value="3">District</option>';
											str+='<option value="4">Constituency</option>';
											str+='<option value="5">Mandal</option>';
											//str+='<option value="6">Panchayat</option>';
										str+='</select>';
										str+='<div class="m_top10"  id="locationLevelId'+typeVal+''+countId+'Err"></div>';
									str+='</div>';
									str+='<div class="col-sm-2 districtCls'+typeVal+''+countId+'" style="display:none;">';
										str+='<label>District</label>';
										str+='<select  name="worksList[0].candidateAddressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange" id="districtId'+typeVal+''+countId+'" attr_counterval="'+countId+'" attr_type="'+typeVal+'">';
											str+='<option value="0">Select District</option>';
										str+='</select>';
									str+='</div>';
									str+='<div class="col-sm-2 constituencyCls'+typeVal+''+countId+'" style="display:none">';
										str+='<label>Constituency</label>';
										str+='<select   name="worksList[0].candidateAddressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyId'+typeVal+''+countId+'" attr_counterval="'+countId+'" attr_type="'+typeVal+'">';
											str+='<option value="0">Select Constituency</option>';
										str+='</select>';
									str+='</div>';
									str+='<div class="col-sm-2 mandalCls'+typeVal+''+countId+'" style="display:none">';
										str+='<label>Mandal</label>';
										str+='<select  name="worksList[0].candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange" id="mandalId'+typeVal+''+countId+'" attr_counterval="'+countId+'" attr_type="'+typeVal+'">';
											str+='<option value="0">Select Mandal</option>';
										str+='</select>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="row m_top20">';
							str+='<div class="col-sm-12">';
								str+='<h3 class="panel-title f_18">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
								str+='<textarea  name="worksList[0].projectDescription"  class="form-control m_top20 workNoTextAreaValidCls'+typeVal+'" rows="4" id="textArea'+typeVal+'1"></textarea>';
								str+='<div class="m_top10"  id="textArea'+typeVal+'1Err"></div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="row m_top20">';
						str+='<div class="appendDiv'+typeVal+'"></div>';
					str+='</div>';
					str+='<div class="row">';
						str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" right-block-clone-counter-'+typeVal+'="'+countId+'" style="cursor:pointer;" right-block-clone-'+typeVal+'="'+countId+'" attr_type="'+typeVal+'">ADD WORK LOCATION</span></div>';
					str+='</div>';	
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-12"><span class="addLocationCss m_top10 cloned_Element" style="cursor:pointer;background-color:green;" onclick="saveRepresentRequestDetails()"> SAVE DETAILS</span></div>';
	str+='</div>';

	$("#workDetailsDivId"+typeVal).append(str);
		$(".chosen-select").chosen();
		globalWorkTypeCount =globalWorkTypeCount+1;

	if(result !=null){
		if(result.locationLevelId !=null){
			$("#locationLevelId"+typeVal+''+countId).val(result.locationLevelId);
			$("#locationLevelId"+typeVal+''+countId).trigger('chosen:updated');
			setLeveles(result.locationLevelId,countId,typeVal);
		}
		///globalWorkTypeCount =globalWorkTypeCount+1;
		
		if(result.candidateAddressVO != null && result.candidateAddressVO.districtId){
			getAllDistrictsInState(typeVal,countId,result.candidateAddressVO.districtId);
		}
		if(result.candidateAddressVO && result.candidateAddressVO.districtId != null && result.candidateAddressVO.assemblyId ){
			getConstituencyNamesByDistrictId(result.candidateAddressVO.districtId,countId,typeVal,result.candidateAddressVO.assemblyId,"constituencyId");
		}
		if(result.subjectId != null){
			getPetitionSubjectList("workTypeId",typeVal,countId,result.subjectId);
		}
		if(result.subjectId != null){
			getPetitionSubSubjectList(result.subjectId,"subWorkTypeId"+typeVal+''+countId,result.subjectId);
		}
	}
}

function represnteeDeatilsEdit(typeVal){
	var str='';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="font_weight text-capital f_22">Representee Details:</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';
				str+='<label>Name</label>';//$("#name"+typeVal+"Err").html("<h5>Please Enter Name</h5>");
				str+='<input type="text"  name="petitionMemberVO.name"  value="" class="form-control m_top10 height45" id="name'+typeVal+'" placeholder="Enter Name" value="">';
				str+='<div id="name'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Mobile Number</label>';
				str+='<input type="text" name="petitionMemberVO.mobileNo"  value=""  class="form-control m_top10 height45" id="mobileNumber'+typeVal+'" placeholder="Enter Mobile Number">';
				str+='<div id="mobileNumber'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>E-mail ID</label>';
				str+='<input type="text" name="petitionMemberVO.emailId"   value="" class="form-control m_top10 height45" id="emailId'+typeVal+'" placeholder="Enter E-mail ID">';
				str+='<div id="emailId'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Voter ID</label>';
				str+='<input type="text"  name="petitionMemberVO.voterCardNo"  value=""  class="form-control m_top10 height45" id="voterId'+typeVal+'" placeholder="Enter Voter ID">';
				str+='<div id="voterId'+typeVal+'Err"></div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>District</label>';
				str+='<select   name="candidateAddressVO.districtId" class="form-control chosen-select m_top10" id="district'+typeVal+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
				str+='<div id="district'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Constituency</label>';
				str+='<select  name="candidateAddressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+typeVal+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
				str+='<div id="constituency'+typeVal+'Err"></div>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Mandal</label>';
				str+='<select   name="candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10" id="mandal'+typeVal+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
				str+='<div id="mandal'+typeVal+'Err"></div>';
			str+='</div>';
		str+='</div>';
		$("#representeeEditDivId").html(str);
		$(".chosen-select").chosen();
}