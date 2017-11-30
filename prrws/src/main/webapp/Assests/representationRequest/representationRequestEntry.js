var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalWorkTypeCount=1;
setTimeout(function(){ 
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
			$("#representDetailsDivId").html('');
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
		}else if(typeVal == "represent"){
			$("#selfDetailsDivId").html('');
			globalWorkTypeCount='';
			globalWorkTypeCount=1;
			buildSelfAndRepresenteeDetails(typeVal)
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
				str+='<input type="text" class="form-control m_top10 height45" id="name'+typeVal+'" placeholder="Enter Name">';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Mobile Number</label>';
				str+='<input type="text" class="form-control m_top10 height45" id="mobileNumber'+typeVal+'" placeholder="Enter Mobile Number">';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>E-mail ID</label>';
				str+='<input type="text" class="form-control m_top10 height45" id="emailId'+typeVal+'" placeholder="Enter E-mail ID">';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<label>Voter ID</label>';
				str+='<input type="text" class="form-control m_top10 height45" id="voterId'+typeVal+'" placeholder="Enter Voter ID">';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';	
				str+='<label>District</label>';
				str+='<select class="form-control chosen-select m_top10" id="district'+typeVal+'">';
					str+='<option value="0">Select District</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Constituency</label>';
				str+='<select class="form-control chosen-select m_top10" id="constituency'+typeVal+'">';
					str+='<option value="0">Select Constituency</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Mandal</label>';
				str+='<select class="form-control chosen-select m_top10" id="mandal'+typeVal+'">';
					str+='<option value="0">Select Mandal</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3">';	
				str+='<label>Village</label>';
				str+='<select class="form-control chosen-select m_top10" id="village'+typeVal+'">';
					str+='<option value="0">Select Village</option>';
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
			str+='<input type="file" id="update_TourFileId2'+typeVal+'" multiple="multiple"  name="files[]" class="m_top20"/>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="panel-title f_22">WORK TYPE DETAILS</h3>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-6">';
					str+='<label>Name of the Work</label>';
					str+='<input type="text" class="form-control m_top10 height45" id="nameofWork'+typeVal+'" placeholder="Enter Name Of Work">';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>No of Works</label>';
					str+='<input type="text" class="form-control m_top10 height45" id="noofWork'+typeVal+'" placeholder="Enter No Of Work">';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Work Cost (Est. Cost in Lakh)</label>';
					str+='<input type="text" class="form-control m_top10 height45" id="workCost'+typeVal+'" placeholder="Enter Work Cost">';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-3">';
					str+='<label>Subject</label>';
					str+='<select class="form-control chosen-select m_top10" id="subjectId'+typeVal+'">';
						str+='<option value="0">Select Subject</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Department</label>';
					str+='<select class="form-control chosen-select m_top10" id="departmentId'+typeVal+'">';
						str+='<option value="0">Select Department</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Previous Petition</label>';
					str+='<select class="form-control chosen-select m_top10" id="previousPetitionId'+typeVal+'">';
						str+='<option value="0">Select Previous Petition</option>';
					str+='</select>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<label>Previous Petition No (Endt NO)</label>';
					str+='<input type="text" class="form-control m_top10 height45" id="workCost'+typeVal+'" value="101/20/11/2017">';
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
					str+='<textarea class="form-control m_top20" rows="7" id="textArea'+typeVal+'"></textarea>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<h3 class="panel-title f_22">PROJCT DOCUMENTS UPLOAD</h3>';
					str+='<input type="file" id="projectDocUpload'+typeVal+'" multiple="multiple"  name="files[]" class="m_top20"/>';
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
						str+='<select class="form-control chosen-select m_top10" id="workTypeId'+typeVal+'1">';
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
						str+='<select class="form-control chosen-select m_top10" id="locationLevelId'+typeVal+'1">';
							str+='<option value="0">Select Subject</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>District</label>';
						str+='<select class="form-control chosen-select m_top10" id="districtId'+typeVal+'1">';
							str+='<option value="0">Select Department</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Constituency</label>';
						str+='<select class="form-control chosen-select m_top10" id="constituencyId'+typeVal+'1">';
							str+='<option value="0">Select Previous Petition</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Mandal</label>';
						str+='<select class="form-control chosen-select m_top10" id="mandalId'+typeVal+'1">';
							str+='<option value="0">Select Department</option>';
						str+='</select>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
				str+='<textarea class="form-control m_top20" rows="4" id="textArea'+typeVal+'1"></textarea>';
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
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="locationLevelId'+typeVal+''+counterId+'">';
						clonedTemplate+='<option value="0">Select Subject</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>District</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="districtId'+typeVal+''+counterId+'">';
						clonedTemplate+='<option value="0">Select Department</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>Constituency</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="constituencyId'+typeVal+''+counterId+'">';
						clonedTemplate+='<option value="0">Select Previous Petition</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>Mandal</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="mandalId'+typeVal+''+counterId+'">';
						clonedTemplate+='<option value="0">Select Department</option>';
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