
setTimeout(function(){ 
	initializeFile();	
	initializeFileProjDoc();
	$(".chosen-select").chosen();
	buildTemplateWorkDetails();
}, 2000);
 
$("#dateRangePickerMGNF").daterangepicker({
		singleDatePicker: true,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		
	});
$('#dateRangePickerMGNF').on('apply.daterangepicker', function(ev, picker) {
});	
var globalWorkTypeCount=1;
function buildTemplateWorkDetails(){
	var str='';
	str+='<div cloned_block=1>';
	
		str+='<div class="m_top10">';
			str+='<h3 class="panel-title f_22">WORK No 1</h3>';
			str+='<div class="row m_top10">';
					str+='<div class="col-sm-3">';
						str+='<label>Select Type</label>';
						str+='<select class="form-control chosen-select m_top10" id="workTypeId1">';
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
						str+='<select class="form-control chosen-select m_top10" id="locationLevelId1">';
							str+='<option value="0">Select Subject</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>District</label>';
						str+='<select class="form-control chosen-select m_top10" id="districtId1">';
							str+='<option value="0">Select Department</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Constituency</label>';
						str+='<select class="form-control chosen-select m_top10" id="constituencyId1">';
							str+='<option value="0">Select Previous Petition</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<label>Mandal</label>';
						str+='<select class="form-control chosen-select m_top10" id="mandalId1">';
							str+='<option value="0">Select Department</option>';
						str+='</select>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
				str+='<textarea class="form-control m_top20" rows="4" id="textArea1"></textarea>';
			str+='</div>';
		str+='</div>';
			
		str+='<div class="row">';
			str+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
		str+='</div>';
		
	str+='</div>';
	
	str+='<div class="appendDiv"></div>';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12"><span class="addLocationCss m_top10 pull-right" right-block-clone-counter="1" style="cursor:pointer;" right-block-clone="1">ADD WORK LOCATION</span></div>';
	str+='</div>';
	
	$("#workDetailsDivId").html(str);
	$(".chosen-select").chosen();
	globalWorkTypeCount =globalWorkTypeCount+1;
}
$(document).on("click","[right-block-clone]",function(){
	var counterId = $(this).attr("right-block-clone-counter");
		counterId = parseInt(counterId) + 1;
		
	var blockId = $(this).attr("right-block-clone");
	
	$("[cloned_block="+blockId+"]").parent().find(".appendDiv").append(clonedTemplate(blockId,'clone',counterId));
	$(".chosen-select").chosen({width:'100%'});
	$("[right-block-clone="+blockId+"]").attr("right-block-clone-counter",counterId);
});

function clonedTemplate(blockId,type,counterId){
	
	var clonedTemplate='';
	clonedTemplate+='<div cloned_block='+counterId+'>';
	
	clonedTemplate+='<div class="m_top10">';
	clonedTemplate+='<h3 class="panel-title f_22">WORK No '+counterId+'</h3>';
		clonedTemplate+='<div class="row m_top10">';
			clonedTemplate+='<div class="col-sm-4">';
				clonedTemplate+='<label>Select Type</label>';
				clonedTemplate+='<select class="form-control chosen-select m_top10" id="workTypeId'+counterId+'">';
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
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="locationLevelId'+counterId+'">';
						clonedTemplate+='<option value="0">Select Subject</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>District</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="districtId'+counterId+'">';
						clonedTemplate+='<option value="0">Select Department</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>Constituency</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="constituencyId'+counterId+'">';
						clonedTemplate+='<option value="0">Select Previous Petition</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
				clonedTemplate+='<div class="col-sm-3">';
					clonedTemplate+='<label>Mandal</label>';
					clonedTemplate+='<select class="form-control chosen-select m_top10" id="mandalId'+counterId+'">';
						clonedTemplate+='<option value="0">Select Department</option>';
					clonedTemplate+='</select>';
				clonedTemplate+='</div>';
			clonedTemplate+='</div>';
		clonedTemplate+='</div>';
		
			clonedTemplate+='<div class="row m_top20">';
				clonedTemplate+='<div class="col-sm-12">';
					clonedTemplate+='<h3 class="panel-title f_22">PROJECT DISCRIPTION <span class="f_12">(Work Details)</span></h3>';
					clonedTemplate+='<textarea class="form-control m_top20" rows="4" id="textArea'+counterId+'"></textarea>';
				clonedTemplate+='</div>';
			clonedTemplate+='</div>';
			
		clonedTemplate+='<div class="row">';
			clonedTemplate+='<div class="col-sm-12"><hr class="m_0" style="border: 3px solid rgb(221, 221, 221);"/></div>';
		clonedTemplate+='</div>';
	clonedTemplate+='</div>';
	
	return clonedTemplate;
	
}