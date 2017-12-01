var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalWorkTypeCount=1;
setTimeout(function(){ 
	buildSelfAndRepresenteeDetails("self")
	getAllDistrictsInState("self",1);
	
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
			getAllDistrictsInState("self",1);
		}else if(typeVal == "represent"){
			$("#selfDetailsDivId").html('');
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
			getAllDistrictsInState(typeVal,1);
		}
	}
	
	
});

function buildSelfAndRepresenteeDetails(typeVal){
	
	$("#"+typeVal+"DetailsDivId").html(spinner);
	
	var str='';
	if(typeVal == "represent"){
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';
				str+='<label>Name</label>';
				str+='<input type="text"  name="petitionMemberVO.name"  class="form-control m_top10 height45" id="name'+typeVal+'" placeholder="Enter Name">';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Mobile Number</label>';
				str+='<input type="text" name="petitionMemberVO.mobileNo" class="form-control m_top10 height45" id="mobileNumber'+typeVal+'" placeholder="Enter Mobile Number">';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>E-mail ID</label>';
				str+='<input type="text" name="petitionMemberVO.emailId" class="form-control m_top10 height45" id="emailId'+typeVal+'" placeholder="Enter E-mail ID">';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Voter ID</label>';
				str+='<input type="text"  name="petitionMemberVO.voterCardNo" class="form-control m_top10 height45" id="voterId'+typeVal+'" placeholder="Enter Voter ID">';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>District</label>';
				str+='<select   name="candidateAddressVO.districtId" class="form-control chosen-select m_top10" id="district'+typeVal+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Constituency</label>';
				str+='<select  name="candidateAddressVO.assemblyId"   class="form-control chosen-select m_top10" id="constituency'+typeVal+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Mandal</label>';
				str+='<select   name="candidateAddressVO.tehsilId"  class="form-control chosen-select m_top10" id="mandal'+typeVal+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Village</label>';
				str+='<select  name="candidateAddressVO.panchayatId"  class="form-control chosen-select m_top10" id="panchayat'+typeVal+'">';
					str+='<option value="0">Select Panchayat</option>';
				str+='</select>';
			str+='</div>';
		str+='</div>';
	}
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h4 class="searchCss"><i class="fa fa-search" aria-hidden="true" style="font-size:20px;"></i> Click to Search Details to the Candidate</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="panel-title f_22">REFERRAL LETTER</h3>';
			str+='<input type="file"   name="filesList[]"   id="update_TourFileId2'+typeVal+'" multiple="multiple" class="m_top20"/>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="panel-title f_22">WORK TYPE DETAILS</h3>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-6">';
					str+='<label>Name of the Work</label>';
					str+='<input  name="workName"  type="text" class="form-control m_top10 height45" id="nameofWork'+typeVal+'" placeholder="Enter Name Of Work">';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>No of Works</label>';
					str+='<input   name="noOfWorks"   type="text" class="form-control m_top10 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work">';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Work Cost (Est. Cost in Lakh)</label>';
					str+='<input type="text"  name="estimationCost" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost">';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';
					str+='<label>Subject</label>';
					str+='<select  name="subjectId"  class="form-control chosen-select m_top10" id="subjectId'+typeVal+'">';
						str+='<option value="0">Select Subject</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Department</label>';
					str+='<select  name="deptId"  class="form-control chosen-select m_top10" id="departmentId'+typeVal+'">';
						str+='<option value="0">Select Department</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Previous Petition</label>';
					str+='<select  name="isPreviousPetition"  class="form-control chosen-select m_top10" id="previousPetitionId'+typeVal+'">';
						str+='<option value="0">Select Previous Petition</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Previous Petition No (Endt NO)</label>';
					str+='<input  name="previousPetitionRefNo"  type="text" class="form-control m_top10 height45" id="workCost'+typeVal+'" value="101/20/11/2017">';
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
					str+='<textarea name="projectDescription"  class="form-control m_top20" rows="7" id="textArea'+typeVal+'"></textarea>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_22">PROJCT DOCUMENTS UPLOAD</h3>';
					str+='<input type="file"  name="workFiles[]"  id="projectDocUpload'+typeVal+'" multiple="multiple" class="m_top20"/>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<div id="workDetailsDivId'+typeVal+'"></div>';
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
						str+='<select  name="worksList[0].deptId"  class="form-control chosen-select m_top10" id="workTypeId'+typeVal+''+globalWorkTypeCount+'">';
							str+='<option value="0">Select Work Type</option>';
						str+='</select>';
					str+='</div>';
				str+='</div>';
		str+='</div>';
	
	
		str+='<div class="m_top10">';
			str+='<h3 class="panel-title f_22">LOCATION</h3>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-3">';
						str+='<label>Location Level</label>';
						str+='<select  name="worksList[0].locationLevelId" class="form-control chosen-select m_top10 locationLevelChange" id="locationLevelId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select Level</option>';
							str+='<option value="3">District</option>';
							str+='<option value="4">Constituency</option>';
							str+='<option value="5">Mandal</option>';
							str+='<option value="6">Panchayat</option>';
						str+='</select>';
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
					str+='<div class="col-sm-2 panchayatCls'+typeVal+''+globalWorkTypeCount+'" style="display:none">';
						str+='<label>Panchayat</label>';
						str+='<select  name="worksList[0].candidateAddressVO.panchayatId"  class="form-control chosen-select m_top10 panchayatLevelChange" id="panchayatId'+typeVal+''+globalWorkTypeCount+'" attr_counterval="'+globalWorkTypeCount+'" attr_type="'+typeVal+'">';
							str+='<option value="0">Select Mandal</option>';
						str+='</select>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
				str+='<textarea  name="worksList[0].projectDescription"  class="form-control m_top20" rows="4" id="textArea'+typeVal+'1"></textarea>';
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
	
	$("#workDetailsDivId"+typeVal).html(str);
	$(".chosen-select").chosen();
	globalWorkTypeCount =globalWorkTypeCount+1;
}
$(document).on("click",".cloned_Element",function(){
	var typeVal = $(this).attr("attr_type")
	var counterId = $(this).attr("right-block-clone-counter-"+typeVal+"");
		counterId = parseInt(counterId) + 1;
		
	var blockId = $(this).attr("right-block-clone-"+typeVal+"");
	
	$("[cloned_block_"+typeVal+"="+blockId+"]").parent().find(".appendDiv"+typeVal+"").append(clonedTemplate(blockId,'clone',counterId,typeVal));
	$(".chosen-select").chosen({width:'100%'});
	$("[right-block-clone-"+typeVal+"="+blockId+"]").attr("right-block-clone-counter-"+typeVal+"",counterId);
	getAllDistrictsInState(typeVal,counterId);
});

function clonedTemplate(blockId,type,counterId,typeVal){
	
	var clonedTemplate='';
	clonedTemplate+='<div cloned_block_'+typeVal+'='+counterId+'>';
	
	clonedTemplate+='<div class="m_top10">';
	clonedTemplate+='<h3 class="panel-title f_22">WORK No '+counterId+'</h3>';
		clonedTemplate+='<div class="row m_top10">';
			clonedTemplate+='<div class="col-sm-4">';
				clonedTemplate+='<label>Select Type</label>';
				clonedTemplate+='<select class="form-control chosen-select m_top10" id="workTypeId'+typeVal+''+counterId+'">';
					clonedTemplate+='<option value="0">Select Work Type</option>';
				clonedTemplate+='</select>';
			clonedTemplate+='</div>';
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	
	
		clonedTemplate+='<div class="m_top10">';
		clonedTemplate+='<h3 class="panel-title f_22">LOCATION</h3>';
			clonedTemplate+='<div class="row m_top10">';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>Location Level</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10 locationLevelChange" id="locationLevelId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'">';
							clonedTemplate+='<option value="0">Select Level</option>';
							clonedTemplate+='<option value="3">District</option>';
							clonedTemplate+='<option value="4">Constituency</option>';
							clonedTemplate+='<option value="5">Mandal</option>';
							clonedTemplate+='<option value="6">Panchayat</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-2 districtCls'+typeVal+''+counterId+'" style="display:none">';
					clonedTemplate+='<label>District</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10 districtLevelChange" id="districtId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
						clonedTemplate+='<option value="0">Select District</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-2 constituencyCls'+typeVal+''+counterId+'" style="display:none">';
					clonedTemplate+='<label>Constituency</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10 constituencyLevelChange" id="constituencyId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
						clonedTemplate+='<option value="0">Select Constituency</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-2 mandalCls'+typeVal+''+counterId+'" style="display:none">';
					clonedTemplate+='<label>Mandal</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10 mandalLevelChange" id="mandalId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
						clonedTemplate+='<option value="0">Select Mandal</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
					clonedTemplate+='<div class="col-sm-2 panchayatCls'+typeVal+''+counterId+'" style="display:none">';
						clonedTemplate+='<label>Panchayat</label>';
						clonedTemplate+='<select class="form-control chosen-select m_top10 panchayatLevelChange" id="panchayatId'+typeVal+''+counterId+'" attr_counterval="'+counterId+'" attr_type="'+typeVal+'" style="display:none;">';
							clonedTemplate+='<option value="0">Select Panchayat</option>';
						clonedTemplate+='</select>';
					clonedTemplate+='</div>';
					
			clonedTemplate+='</div>';
		clonedTemplate+='</div>';
		
			clonedTemplate+='<div class="row m_top20">';
				clonedTemplate+='<div class="col-sm-12">';
					clonedTemplate+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
					clonedTemplate+='<textarea class="form-control m_top20" rows="4" id="textArea'+typeVal+''+counterId+'"></textarea>';
				clonedTemplate+='</div>';
			clonedTemplate+='</div>';
			
		clonedTemplate+='<div class="row">';
			clonedTemplate+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	
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
function getAllDistrictsInState(typeVal,counterId){
	$("#districtId"+typeVal+counterId).html('');
	$("#districtrepresent").html('');
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
			 $("#districtId"+typeVal+counterId).append('<option value="0">Select District</option>');
			 $("#districtrepresent").append('<option value="0">Select District</option>');
			for(var i in result){
				$("#districtId"+typeVal+counterId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				$("#districtrepresent").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
			}
		}
		$("#districtId"+typeVal+counterId).trigger('chosen:updated');
		$("#districtrepresent").trigger('chosen:updated');
	});	
}

function getConstituencyNamesByDistrictId(levelVal,counterId,typeVal){
	  $("#constituencyId"+typeVal+counterId).html('');
	  $("#constituencyrepresent").html('');
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
			for(var i in result){
				$("#constituencyId"+typeVal+counterId).append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
				$("#constituencyrepresent").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#constituencyId"+typeVal+counterId).trigger('chosen:updated');
		$("#constituencyrepresent").trigger('chosen:updated');
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
				$("#mandalId"+typeVal+counterId).append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
				$("#mandalrepresent").append('<option value="'+result[i].key+'">'+result[i].value+' </option>');
			}
		}
		$("#mandalId").trigger('chosen:updated');
		$("#mandalrepresent").trigger('chosen:updated');
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
				$("#panchayatrepresent").append('<option value="'+result[i].locationId+'">'+result[i].locationName+' </option>');
			}
		}
		$("#panchayatId"+typeVal+counterId).trigger('chosen:updated');
		$("#panchayatrepresent").trigger('chosen:updated');
	});	
}

getPetitionReferredMemberDetails("1","3","11");
function getPetitionReferredMemberDetails(desiganationId,locationLevelId,locationValue){
    
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
	
	});	
}

function getPetitionDesignationList(){
    
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
		console.log(result);
	});	
}


function getPetitionDepartmentList(){
    
	  var json = {
		 
		}           
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
		console.log(result);
	});	
}

