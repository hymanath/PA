var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalWorkTypeCount=1;
setTimeout(function(){ 
	buildSelfAndRepresenteeDetails("self")
	//getAllDistrictsInState("self",1);
	getPetitionDepartmentList("self")
	
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
			$("#representDetailsDivId").html('');
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
			//getAllDistrictsInState(typeVal,1);
			getPetitionDepartmentList(typeVal)
		}else if(typeVal == "represent"){
			$("#selfDetailsDivId").html('');
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
			getAllDistrictsInState(typeVal,"");
			getPetitionDepartmentList(typeVal)
		}
	}
	
	
});

function buildSelfAndRepresenteeDetails(typeVal){
	
	$("#"+typeVal+"DetailsDivId").html(spinner);
	
	var str='';
	if(typeVal == "represent"){
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';
				str+='<label>Name</label>';//$("#name"+typeVal+"Err").html("<h5>Please Enter Name</h5>");
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
			/*str+='<div class="col-sm-3">';	
				str+='<label>Village</label>';
				str+='<select  name="candidateAddressVO.panchayatId"  class="form-control chosen-select m_top10" id="panchayat'+typeVal+'">';
					str+='<option value="0">Select Panchayat</option>';
				str+='</select>';
				str+='<div id="panchayat'+typeVal+'Err"></div>';
			str+='</div>';*/
		str+='</div>';
	}else{
		
	}
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<div id="candidateDetails'+typeVal+'DivId"></div>'
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h4 class="searchCss searchCandidateCls" attr_type="'+typeVal+'"><i class="fa fa-search" aria-hidden="true" style="font-size:20px;"></i> Click to Search Details to the Candidate</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="panel-title f_22">REFERRAL LETTER</h3>';
			str+='<input type="file"   name="filesList"   id="update_TourFileId2'+typeVal+'" multiple="multiple" class="m_top20"/>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="panel-title f_22">WORK TYPE DETAILS</h3>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-6">';
					str+='<label>Name of the Work</label>';
					str+='<input  name="workName"  type="text" class="form-control m_top10 height45" id="nameofWork'+typeVal+'" placeholder="Enter Name Of Work">';
					str+='<div id="nameofWork'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>No of Works</label>';
					str+='<input   name="noOfWorks"   type="text" class="form-control m_top10 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work" onkeyUp="enableWorks(this.value,\'workDetailsDivId'+typeVal+'\');">';
					str+='<div id="noofWork'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Work Cost (Est. Cost in Lakh)</label>';
					str+='<input type="text"  name="estimationCost" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost">';
					str+='<div id="workCost'+typeVal+'Err"></div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';
					str+='<label>Subject</label>';
					str+='<select  name="subjectId"  class="form-control chosen-select m_top10" id="subjectId'+typeVal+'">';
						str+='<option value="0">Select Subject</option>';
					str+='</select>';
					str+='<div id="subjectId'+typeVal+'Err"></div>';
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
			str+='<div class="row">';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
					str+='<textarea name="projectDescription"  class="form-control m_top20" rows="7" id="textArea'+typeVal+'"></textarea>';
					str+='<div id="textArea'+typeVal+'Err"></div>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_22">PROJCT DOCUMENTS UPLOAD</h3>';
					str+='<input type="file"  name="workFilesList"  id="projectDocUpload'+typeVal+'" multiple="multiple" class="m_top20"/>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<div id="workDetailsDivId'+typeVal+'"  style="display:none;"></div>';
		str+='</div>';
	str+='</div>';
	
	$("#"+typeVal+"DetailsDivId").html(str);
	initializeFile(typeVal);	
	initializeFileProjDoc(typeVal);
	$(".chosen-select").chosen();
	buildTemplateWorkDetails(typeVal);
}

function buildTemplateWorkDetails(typeVal){
	var str='';
	str+='<div  cloned_block_'+typeVal+'='+globalWorkTypeCount+'>';
	
		str+='<div class="m_top10">';
			str+='<h3 class="panel-title f_22">WORK No 1</h3>';
			str+='<div class="row m_top10">';
					str+='<div class="col-sm-3">';
						str+='<label>Select Type</label>';
						str+='<select  name="worksList[0].deptId"  class="form-control chosen-select m_top10 workTypeCls'+typeVal+'" id="workTypeId'+typeVal+''+globalWorkTypeCount+'">';
							str+='<option value="0">Select Work Type</option>';
						str+='</select>';
						str+='<div class="m_top10"  id="workTypeId'+typeVal+''+globalWorkTypeCount+'Err"></div>';
					str+='</div>';
				str+='</div>';
		str+='</div>';
	
	
		str+='<div class="m_top10">';
			str+='<h3 class="panel-title f_22">LOCATION</h3>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-3">';
						str+='<label>Location Level</label>';
						str+='<select  name="worksList[0].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select Level</option>';
							str+='<option value="3">District</option>';
							str+='<option value="4">Constituency</option>';
							str+='<option value="5">Mandal</option>';
							str+='<option value="6">Panchayat</option>';
						str+='</select>';
						str+='<div class="m_top10"  id="locationLevelId'+typeVal+''+globalWorkTypeCount+'Err"></div>';
					str+='</div>';
					str+='<div class="col-sm-2 districtCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
						str+='<label>District</label>';
						str+='<select  name="worksList[0].candidateAddressVO.districtId"  class="form-control chosen-select m_top10 districtLevelChange" id="districtId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select District</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-2 constituencyCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
						str+='<label>Constituency</label>';
						str+='<select   name="worksList[0].candidateAddressVO.assemblyId"  class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select Constituency</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-2 mandalCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
						str+='<label>Mandal</label>';
						str+='<select  name="worksList[0].candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10 mandalLevelChange" id="mandalId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select Mandal</option>';
						str+='</select>';
					str+='</div>';
					/*str+='<div class="col-sm-2 panchayatCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
						str+='<label>Panchayat</label>';
						str+='<select  name="worksList[0].candidateAddressVO.panchayatId"  class="form-control chosen-select m_top10 panchayatLevelChange" id="panchayatId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select Panchayat</option>';
						str+='</select>';
					str+='</div>';*/
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
				str+='<textarea  name="worksList[0].projectDescription"  class="form-control m_top20 workNoTextAreaValidCls'+typeVal+'" rows="4" id="textArea'+typeVal+'1"></textarea>';
				str+='<div class="m_top10"  id="textArea'+typeVal+'1Err"></div>';
			str+='</div>';
		str+='</div>';
			
		str+='<div class="row">';
			str+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
		str+='</div>';
		
	str+='</div>';
	
	str+='<div class="appendDiv'+typeVal+'"></div>';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" right-block-clone-counter-'+typeVal+'="'+globalWorkTypeCount+'" style="cursor:pointer;" right-block-clone-'+typeVal+'="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">ADD WORK LOCATION</span></div>';
	str+='</div>';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right cloned_Element" style="cursor:pointer;" onclick="saveRepresentRequestDetails()"> UPDATE DETAILS </span></div>';
	str+='</div>';
/*	str+='<div class="row">';//cloned_Element
		str+='<div class="col-sm-12"><span id="referelSubmitBtnId" class="btn btn-success btn-md m_top10 " style="cursor:pointer;" > Submit</span></div>';
	str+='</div>';  */
	
	$("#workDetailsDivId"+typeVal).html(str);
	$(".chosen-select").chosen();
	globalWorkTypeCount =globalWorkTypeCount+1;
}
$(document).on("click",".cloned_Element",function(){
	var typeVal = $(this).attr("attr_type");
	var workCount = $("#noofWorkself").val();
	var counterId = $(this).attr("right-block-clone-counter-"+typeVal+"");
		counterId = parseInt(counterId) + 1;
		
	var blockId = $(this).attr("right-block-clone-"+typeVal+"");
	if(counterId <= parseInt(workCount)){
		$("[cloned_block_"+typeVal+"="+blockId+"]").parent().find(".appendDiv"+typeVal+"").append(clonedTemplate(blockId,'clone',counterId,typeVal));
		$(".chosen-select").chosen({width:'100%'});
		$("[right-block-clone-"+typeVal+"="+blockId+"]").attr("right-block-clone-counter-"+typeVal+"",counterId);
		getAllDistrictsInState(typeVal,counterId);
	}
});

function clonedTemplate(blockId,type,counterId,typeVal){
	
	var clonedTemplate='';
	clonedTemplate+='<div cloned_block_'+typeVal+'='+counterId+'>';
	
	clonedTemplate+='<div class="m_top10">';
	clonedTemplate+='<h3 class="panel-title f_22">WORK No '+counterId+'</h3>';
		clonedTemplate+='<div class="row m_top10">';
			clonedTemplate+='<div class="col-sm-4">';
				clonedTemplate+='<label>Select Type</label>';
				clonedTemplate+='<select name="worksList['+counterId+'].deptId"  class="form-control chosen-select m_top10 workTypeCls'+typeVal+'" id="workTypeId'+typeVal+''+counterId+'">';
				//clonedTemplate+='<select   class="form-control chosen-select m_top10" id="workTypeId'+typeVal+''+counterId+'">';
					clonedTemplate+='<option value="0">Select Work Type</option>';
				clonedTemplate+='</select>';
				clonedTemplate+='<div class="m_top10"  id="workTypeId'+typeVal+''+counterId+'Err"></div>';
			clonedTemplate+='</div>';
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	
	
		clonedTemplate+='<div class="m_top10">';
		clonedTemplate+='<h3 class="panel-title f_22">LOCATION</h3>';
			clonedTemplate+='<div class="row m_top10">';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>Location Level</label>';
					clonedTemplate+='<select name="worksList['+counterId+'].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange locationLevelValidCls'+typeVal+'" id="locationLevelId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'">';
//					clonedTemplate+='<select  name="worksList['+counterId+'].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange" id="locationLevelId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'">';
							clonedTemplate+='<option value="0">Select Level</option>';
							clonedTemplate+='<option value="3">District</option>';
							clonedTemplate+='<option value="4">Constituency</option>';
							clonedTemplate+='<option value="5">Mandal</option>';
							clonedTemplate+='<option value="6">Panchayat</option>';
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
					/*clonedTemplate+='<div class="col-sm-2 panchayatCls'+typeVal+''+counterId+'" style="display:none">';
						clonedTemplate+='<label>Panchayat</label>';
						clonedTemplate+='<select  name="worksList['+counterId+'].candidateAddressVO.panchayatId"   class="form-control chosen-select m_top10 panchayatLevelChange" id="panchayatId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
							clonedTemplate+='<option value="0">Select Panchayat</option>';
						clonedTemplate+='</select>';
					clonedTemplate+='</div>';
					*/
			clonedTemplate+='</div>';
		clonedTemplate+='</div>';
		
			clonedTemplate+='<div class="row m_top20">';
				clonedTemplate+='<div class="col-sm-12">';
					clonedTemplate+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
					clonedTemplate+='<textarea   name="worksList[0].projectDescription" class="form-control m_top20 workNoTextAreaValidCls'+typeVal+'" rows="4" id="textArea'+typeVal+''+counterId+'"></textarea>';
					clonedTemplate+='<div class="m_top10"  id="textArea'+typeVal+''+counterId+'Err"></div>';
					//clonedTemplate+='<textarea   name="worksList[0].projectDescription" class="form-control m_top20" rows="4" id="textArea'+typeVal+''+counterId+'"></textarea>';
				clonedTemplate+='</div>';
			clonedTemplate+='</div>';
			
		clonedTemplate+='<div class="row">';
			clonedTemplate+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	getSubjectPetitionsDepartmentList(typeVal,counterId);
	return clonedTemplate;
	
}

$(document).on("change",".locationLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	
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
});

$(document).on("change",".districtLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	
	getConstituencyNamesByDistrictId(levelVal,counterId,typeVal);
	
});
$(document).on("change",".constituencyLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal);
	
});
$(document).on("change",".mandalLevelChange",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	var typeVal = $(this).attr("attr_type");
	getPanchayatsByTehsilId(levelVal,counterId,typeVal);
	
});

$(document).on("change","#districtrepresent",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getConstituencyNamesByDistrictId(levelVal,1,"represent");
	
});
$(document).on("change","#constituencyrepresent",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,1,"represent");
	
});
$(document).on("change","#mandalrepresent",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getPanchayatsByTehsilId(levelVal,1,"represent");
	
});
$(document).on("change","#districtCandId",function(){
	var levelVal = $(this).val();
	var counterId = $(this).attr("attr_counterval");
	getConstituencyNamesByDistrictId(levelVal,"","");
	
});
function getAllDistrictsInState(typeVal,counterId){
	
	if((counterId =="" || counterId.trim().length==0) && (counterId=="" || counterId.trim().length==0)){
		$("#districtCandId").html('');
	}else if(counterId == null || counterId ==""){
		$("#district"+typeVal+counterId).html('');
	}else if(counterId !="" && parseInt(counterId)>0){
		$("#districtId"+typeVal+counterId).html('');
	}
	
	var json = {
		  stateId:"1"
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
			if((counterId =="" || counterId.trim().length==0) && (counterId=="" || counterId.trim().length==0)){
				$("#districtCandId").append('<option value="0">All</option>');
			}else if(counterId == null || counterId ==""){		
				$("#district"+typeVal+counterId).append('<option value="0">Select District</option>');
			}else if(counterId !="" && parseInt(counterId)>0){
			 $("#districtId"+typeVal+counterId).append('<option value="0">Select District</option>');
			}
		 
			for(var i in result){				
				if((counterId =="" || counterId.trim().length==0) && (counterId=="" || counterId.trim().length==0)){
					$("#districtCandId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}else if(counterId == null || counterId ==""){		
					$("#district"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}else  if(counterId !="" && parseInt(counterId)>0){
					$("#districtId"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		}
		if((counterId =="" || counterId.trim().length==0) && (counterId=="" || counterId.trim().length==0)){
			$("#districtCandId").trigger('chosen:updated');
		}
		else if(counterId == null || counterId =="")		
			$("#district"+typeVal+counterId).trigger('chosen:updated');
		else if(counterId !="" && parseInt(counterId)>0)
			$("#districtId"+typeVal+counterId).trigger('chosen:updated');
				
		//$("#districtCandId").trigger('chosen:updated');
	});	
}

function getConstituencyNamesByDistrictId(levelVal,counterId,typeVal){
	  $("#constituencyId"+typeVal+counterId).html('');
	  $("#constituencyrepresent").html('');
	  $("#constituencyCanId").html('');
	  var json = {
		  districtId:levelVal
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
			 $("#constituencyId"+typeVal+counterId).append('<option value="0">Select Constituency</option>');
			 $("#constituencyrepresent").append('<option value="0">Select Constituency</option>');
			 $("#constituencyCanId").append('<option value="0">All</option>');
			for(var i in result){
				$("#constituencyId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				$("#constituencyrepresent").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				$("#constituencyCanId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
		$("#constituencyrepresent").trigger('chosen:updated');
		$("#constituencyCanId").trigger('chosen:updated');
	});	
}

function getTehsilsAndLocalElectionBodyForConstituencyId(levelVal,counterId,typeVal){
		$("#mandalId"+typeVal+counterId).html('');
		$("#mandalrepresent").html('');
	  var json = {
		  constituencyId:levelVal
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
			 $("#mandalrepresent").append('<option value="0">Select Mandal</option>');
			for(var i in result){
				var tehsilId = result[i].key;
				var levelId = tehsilId.toString().substr(1, 4);
				$("#mandalId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				if(result[i].electionType != null){
					$("#mandalrepresent").append('<option value="'+levelId+'">'+result[i].value+'-'+result[i].electionType+'</option>');
				}else{
					$("#mandalrepresent").append('<option value="'+levelId+'">'+result[i].value+'</option>');
				}
				
			}
		}
		$("#mandalId"+typeVal+counterId).trigger('chosen:updated');
		$("#mandalrepresent").trigger('chosen:updated');
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
			url: $("#adminProfileForm").attr("action"),
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

function getPanchayatsByTehsilId(levelVal,counterId,typeVal){
	$("#panchayatId"+typeVal+counterId).html('');
	$("#panchayatrepresent").html('');
	  var json = {
		  tehsilId:levelVal
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
			 $("#panchayatId"+typeVal+counterId).append('<option value="0">Select Panchayat</option>');
			 $("#panchayatrepresent").append('<option value="0">Select Panchayat</option>');
			for(var i in result){
				$("#panchayatId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				$("#panchayatrepresent").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
		$("#panchayatrepresent").trigger('chosen:updated');
	});	
}
function getPetitionDepartmentList(typeVal){
    $("#subjectId"+typeVal).html('');
	$("#departmentId"+typeVal).html('');
	$("#workTypeId"+typeVal+"1").html('');
	  var json = {
		
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
			 $("#subjectId"+typeVal).append('<option value="0">Select Subject</option>');
			 $("#departmentId"+typeVal).append('<option value="0">Select Department</option>');
			 $("#workTypeId"+typeVal+"1").append('<option value="0">Select Subject</option>');
			 
			for(var i in result){
				$("#subjectId"+typeVal).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				$("#departmentId"+typeVal).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				$("#workTypeId"+typeVal+"1").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#subjectId"+typeVal).trigger('chosen:updated');
		$("#departmentId"+typeVal).trigger('chosen:updated');
		$("#workTypeId"+typeVal+"1").trigger('chosen:updated');
	});	
}


function getSubjectPetitionsDepartmentList(typeVal,count){
	 $("#workTypeId"+typeVal+""+count+"").html('');
	  var json = {}           
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
			 	$("#workTypeId"+typeVal+""+count+"").append('<option value="0">Select Subject</option>');
			for(var i in result){
				$("#workTypeId"+typeVal+""+count+"").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#workTypeId"+typeVal+""+count+"").trigger('chosen:updated');
	});	
}

function getPetitionDesignationList(){
    $("#designationsId").html('');
	  var json = {};
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
	//getAllDistrictsInState("","");
	if(typeVal == "self"){
		$("#candidateDetailsDivId").html('');
		$("#candidateSearchModelDivId").modal("show");
		getPetitionDesignationList();
		//getAllDistrictsInState("","");
	}else{
		$("#candidateDetailsDivId").html('');
		$("#candidateSearchModelDivId").modal("show");
		getPetitionDesignationList();
		//getAllDistrictsInState("","");
	}
});

$(document).on("change","#designationsId",function(){	
	
	var value = $(this).val();
	if(parseInt(value)>0){
		var typeVal = $(this).attr("attr_type");
		$(".selectionSearchDetailsCls").attr("attr_type",typeVal);
		getAllDistrictsInState("","");
		if(typeVal == "self"){
			$("#candidateDetailsDivId").html('');
			//$("#candidateSearchModelDivId").modal("show");
			//getPetitionDesignationList();
			//getAllDistrictsInState("","");
		}else{
			$("#candidateDetailsDivId").html('');
			//$("#candidateSearchModelDivId").modal("show");
			//getPetitionDesignationList();
			//getAllDistrictsInState("","");
		}
	}
});

$(document).on("click",".selectionSearchDetailsCls",function(){
	var locationValue=0;
	var locationLevelId=0;
	var desiganationId = $("#designationsId").val();
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
$(document).on("click",".bgColorCandidates",function(){
	
	$(this).addClass("activeCandidate");
	var typeVal = $(this).attr("attr_type");
	if(typeVal == "self"){
		//$("#candidateDetails"+typeVal+"DivId").html('');
		$("#candidateDetails"+typeVal+"DivId").append($("#candidateDetailsDivId").html());
		$("#candidateDetails"+typeVal+"DivId").parent().find(".bgColorCandidates").removeClass("activeCandidate")
	}else{
		//$("#candidateDetails"+typeVal+"DivId").html('');
		$("#candidateDetails"+typeVal+"DivId").append($("#candidateDetailsDivId").html());
		$("#candidateDetails"+typeVal+"DivId").parent().find(".bgColorCandidates").removeClass("activeCandidate")
	}
	
	
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
		var str='';
	for(var i in result){
	str+='<div class="col-sm-12">';
		str+='<div class="bgColorCandidates" attr_type='+typeVal+'>';
			str+='<div class="row">';
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
	str+='</div>';
	}
	$("#candidateDetailsDivId").html(str);
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
function  enableWorks(value,divId){	
	$('#'+divId+'').hide();
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
			alert(errDivId2);
		var districtVal=$("#"+errDivId2).closest('.districtLevelChange').val();
			alert(districtVal);
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

})

