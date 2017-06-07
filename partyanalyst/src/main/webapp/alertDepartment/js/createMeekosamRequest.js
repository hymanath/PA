var profileData = '';
onLoadCalls();
onLoadClicks();
function onLoadClicks()
{
	$("#petitionerDOBId").datetimepicker({
		format:'DD/MM/YYYY',
		viewMode: 'years',
		maxDate : moment().subtract(18,'years')
	});
	
	$(".selectChosen").chosen();
	$(document).on("change","#districts",function(){
		getAllMandalsByDistrictID($(this).val(),0,'mandals');
	});
	$(document).on("change","#mandals",function(){
		getAllPanchayatByMandalId($(this).val(),0,'panchayats')
	});
	$(document).on("change","#panchayats",function(){
		getAllHamletByPanchayatID($(this).val(),'')
	});
	$(document).on("change","#districtsPetitionerId",function(){
		getAllMandalsByDistrictID($(this).val(),0,'mandalsPetitionerId');
	});
	$(document).on("change","#mandalsPetitionerId",function(){
		getAllPanchayatByMandalId($(this).val(),0,'villagePetitionerId')
	});
	$(document).on("change","#departmentId",function(){
		getSubDeptsFrParentDept($(this).val());
		if($(this).val() == '33')
		{
			$("#revenueDeptSplBlock").show();
		}else{
			$("#revenueDeptSplBlock").hide();
		}
	});
	$(document).on('change', '#locationLevelSelectId', function(){
		getParentLevelsOfLevel($("#subDepartmentSelectId").val());
	});
	$(document).on('change', '#subDepartmentSelectId', function(){
		getDepartmentLevels($(this).val());
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	$(document).on('change','#designationsId', function(evt, params) {
		var designationId = $(this).val();
		officersByDesignationAndLevel(designationId)
	});
	$(document).on("change","#locationDistrictId",function(){
		getAllMandalsByDistrictID($(this).val(),0,'locationMandalId');
	});
	$(document).on("change","#locationMandalId",function(){
		getAllPanchayatByMandalId($(this).val(),0,'locationVillageId')
	});
	$(document).on("change","#districtsReferralId",function(){
		getPublicReresentativesByTypeAndDistrict();
	});
	
	var globalIncrement = 0;
	$(document).on("click","#addOneMorePetitionerId",function(){
		$("#petitionerTableId").show();
		var str='';
		str+='<tr>';
			str+='<td><input type="hidden" value="'+$("#districtsPetitionerId option:selected").val()+'" name="meekosamGrievanceVO.landDetailsList['+globalIncrement+'].districtId"/>'+$("#districtsPetitionerId option:selected").text()+'</td>';
			str+='<td><input type="hidden" value="'+$("#mandalsPetitionerId option:selected").val()+'" name="meekosamGrievanceVO.landDetailsList['+globalIncrement+'].mandalId"/>'+$("#mandalsPetitionerId option:selected").text()+'</td>';
			str+='<td><input type="hidden" value="'+$("#villagePetitionerId option:selected").val()+'" name="meekosamGrievanceVO.landDetailsList['+globalIncrement+'].villageId"/>'+$("#villagePetitionerId option:selected").text()+'</td>';
			str+='<td><input type="hidden" value="'+$("#surveyNoPetitionerId").val()+'" name="meekosamGrievanceVO.landDetailsList['+globalIncrement+'].surveyNO"/>'+$("#surveyNoPetitionerId").val()+'</td>';
			str+='<td><input type="hidden" value="'+$("#landInAcresPetitionerId").val()+'" name="meekosamGrievanceVO.landDetailsList['+globalIncrement+'].landInAcres"/>'+$("#landInAcresPetitionerId").val()+'</td>';
			str+='<td><input type="hidden" value="'+$("#landInCentPeitionerId").val()+'" name="meekosamGrievanceVO.landDetailsList['+globalIncrement+'].landInCents"/>'+$("#landInCentPeitionerId").val()+'</td>';
			str+='<td><i class="deletePetitionerRow glyphicon glyphicon-trash" style="cursor:pointer"></i></td>';
		str+='</tr>';
		$("#petitionerTableId").append(str);
		var resetIds = ['districtsPetitionerId','mandalsPetitionerId','villagePetitionerId']
		var resetTextIds = ['surveyNoPetitionerId','landInAcresPetitionerId','landInCentPeitionerId']
		for(var i in resetIds)
		{
			$("#"+resetIds[i]).val('').trigger("chosen:updated");
		}
		for(var i in resetTextIds)
		{
			$("#"+resetTextIds[i]).val(' ');
		}
		globalIncrement = globalIncrement+1;
	});
	$(document).on("click",".deletePetitionerRow",function(){
		$(this).closest("tr").remove();
		if($("#petitionerTableId tr").length > 1)
		{
			$("#petitionerTableId").show();
		}else{
			$("#petitionerTableId").hide();
		}
			
	});
	$(document).on("click",".buildProfileData",function(){
		var ArrPosition = $(this).attr("attr_id")
		$(".buildProfileData").prop("checked",false);
		$(this).prop("checked",true);
		buildProfileData(ArrPosition);
		var petitionerId = $(this).attr("attr_petitionerId");
		$("#hiddenPetitionerId").val(petitionerId);
	});
	
}
function onLoadCalls()
{
	//getAllMainDepartments();
	getAllDistrictByStateId(1,0,"districtsPetitionerId");
	getAllDistrictByStateId(1,0,"districtsReferralId");
	//searchPetitionerDetailsByVoterNoAadharNoMobileNo();
	getAllPublicRepresentativeTypes();
	getAllDistrictByStateId(1,'','districts');
	getMeekosamArgeeCategoryList(0);
	getMeekosamOccupationList(0);
	getMeekosamCasteCategoryList(0);
	getMeekosamAnnualIncomeList(0)
}
function setDefaultImage(img){
    img.src = "images/User.png";
}
	
function getAllDistrictByStateId(stateId,districtId,id){
	var jobj = {
		stateId : stateId  
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictForGrievanceRequestAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		if(result != null && result.length > 0){
			$("#"+id).html('');
			for(var i in result){	
				$('#'+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#"+id).trigger("chosen:updated");
			$("#"+id).val(districtId).trigger("chosen:updated");
		}
	});
}
function getAllMandalsByDistrictID(districtId,tehsilId,id){
	//var districtId = $('#districts').val();
	var jsObj={
		districtId :districtId
	}
	$.ajax({
		type:"POST",
		url :"getAllMandalByDistrictIdAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			$("#"+id).html('');
			$('#'+id).append('<option value="0">Select Mandal/Muncipality</option>');
			for(var i in result){ 
				$('#'+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#"+id).trigger("chosen:updated");
			$("#"+id).val(tehsilId).trigger("chosen:updated");
		}
	});
}

var LocationType = "";
function getAllPanchayatByMandalId(tehsilId,panchayatId,id){
	var mandalTypeId = tehsilId.substring(0, 1);
	var mandalId = tehsilId.substring(1);
	if(mandalTypeId==1){
		LocationType = "Mandal";
	}else{
		LocationType = "muncipality";
	}
	var jsObj={
		mandalId :mandalId,
		type:LocationType,
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getPanchayatDetailsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			$("#"+id).html('');
			$('#'+id).append('<option value="0">Select Panchayat/Ward</option>');
			for(var i in result){			
				$('#'+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#"+id).trigger("chosen:updated");
			$("#"+id).val(panchayatId).trigger("chosen:updated");
		}
	});
}
function getAllHamletByPanchayatID(panchayatId,hamletId){
	if(LocationType=="muncipality"){
		$("#habitationId").html('');
		return;
	}
	//var panchayatId = $('#panchayats').val();
	var jsObj={
		panchayatId :panchayatId
	}
	$.ajax({
		type:"POST",
		url :"getAllHamletByPanchayatIDAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			$("#habitationId").html('');
			$('#habitationId').append('<option value="0">Select Hamlet</option>');
			for(var i in result){			
				$('#habitationId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#habitationId").trigger("chosen:updated");
			$("#habitationId").val(hamletId).trigger("chosen:updated");
		}
	});
}

function getMeekosamOccupationList(OccupationId){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamOccupationListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildMeekosamOccupationList(result,OccupationId);
		}
	});
}
function buildMeekosamOccupationList(result,OccupationId){
	$("#occupationListId").empty().trigger("chosen:updated");
	$('#occupationListId').append('<option value="0">Select Occupation</option>');
	for(var i in result){			
		$('#occupationListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
	$("#occupationListId").trigger("chosen:updated");
	$("#occupationListId").val(OccupationId).trigger("chosen:updated");
}

function getMeekosamCasteCategoryList(selectedId){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamCasteCategoryListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildRadioBtns(result,selectedId,'casteDataId')
	});
}
function buildRadioBtns(result,selectedId,id)
{
	var str='';
	for(var i in result){
		str+='<label class="radio-inline">';
			if(result[i].id == selectedId)
			{
				str+='<input type="radio" class="'+id+'" id="'+result[i].id+'" checked/>'+result[i].name+'';
			}else{
				str+='<input type="radio" class="'+id+'" id="'+result[i].id+'"/>'+result[i].name+'';
			}
			
		str+='</label>';
	}
	$("#"+id).html(str);
}
function getMeekosamArgeeCategoryList(CategoryId){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamArgeeCategoryListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildMeekosamArgeeCategoryList(result,CategoryId);
		}
	});
}
function buildMeekosamArgeeCategoryList(result,CategoryId){
	$("#argeeCategoryListId").empty().trigger("chosen:updated");
	$('#argeeCategoryListId').append('<option value="0">Select Argee Category</option>');
	for(var i in result){			
		$('#argeeCategoryListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
	$("#argeeCategoryListId").trigger("chosen:updated");
	$("#argeeCategoryListId").val(CategoryId).trigger("chosen:updated");
}

function getMeekosamAnnualIncomeList(selectedId){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamAnnualIncomeListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildRadioBtns(result,selectedId,'annaulIncomeDataId')
	});
}
function saveMeekosamPetitionerDetails(){
	var uploadHandler = {
		upload: function(o) {
			uploadResult = o.responseText;
		}
	};   
	YAHOO.util.Connect.setForm('meekosamPetitionerDetails',true);
	YAHOO.util.Connect.asyncRequest('POST','saveMeekosamPetitionerDetailsAction.action',uploadHandler);

}

function searchPetitionerDetailsByVoterNoAadharNoMobileNo(){
	$("#errorMsgSearchId").html("");
	$("#searchPetitionerDetailsByVoterNoAadharNoMobileNo").html("");
	var type = '';
	
	$(".typeOfSearch").each(function(){
		if($(this).is(':checked'))
		{
			type = $(this).val();
		}
	});
	var searchValue = $(".searchValue").val().trim();
	if(searchValue == '' || searchValue.length == 0){
		$("#errorMsgSearchId").html("Enter Aadhar or MobileNo or VoterCardNo");
		return;
	}
	
	var jsObj={
		cardNo 	:	searchValue, //"7207785117",
		type	:	type
	}
	$.ajax({
		type:"POST",
		url :"searchPetitionerDetailsByVoterNoAadharNoMobileNoAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		profileData = result;
		buildSearchPetitionerDetailsByVoterNoAadharNoMobileNo(result);
	});
}
function buildSearchPetitionerDetailsByVoterNoAadharNoMobileNo(result)
{
	var str='';
	str+='<p>Search Results (found '+result.length+' Results)</p>';
	str+='<ul class="list-inline slickSlider meekosam-profiles m_top10">';
		for(var i in result)
		{
			str+='<li>';
				str+='<div class="media">';
					str+='<div class="media-left">';
						str+='<img src="alertDepartment/img/6.png" onerror="setDefaultImage(this);" class="media-object"/>';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<p><i class="fa fa-user"></i> '+result[i].name+'</p>';
						str+='<p><i class="fa fa-mobile-phone"></i> '+result[i].mobileNo+'</p>';
						str+='<p><img src="alertDepartment/img/aadhar.png" style="display:inline-block;width: 12px;"> '+result[i].aadharNo+'</p>';
						str+='<p><i class="fa fa-id-badge"></i> '+result[i].voterCardNo+'</p>';
					str+='</div>';
				str+='</div>';
				str+='<input type="checkbox" name="profile" attr_id="'+i+'" attr_petitionerId="'+result[i].petitionerId+'" class="pull-right buildProfileData"/>';//profile
			str+='</li>';
		}
	str+='</ul>';
	$("#searchPetitionerDetailsByVoterNoAadharNoMobileNo").html(str);
	if(result.length > 1)
	{
		$('.slickSlider').slick({
			slide: 'li',
			slidesToShow: 4,
			slidesToScroll: 2,
			infinite: false,
			swipe:false,
			touchMove:true,
			variableWidth: false
		});
	}
}
function buildProfileData(i)
{
	
	var str='';
	var districtId = profileData[i].districtId;
	var tehsilId = profileData[i].tehsil;
	var panchayatId = profileData[i].panchayatId;
	var hamletId = profileData[i].hamletId;
	var casteId = profileData[i].meekosamCasteCategoryId;
	var meekosamAnnualIncomeId = profileData[i].meekosamAnnualIncomeId;
	var meekosamOccupationId = profileData[i].meekosamOccupationId;
	var meekosamArgeeCategoryId = profileData[i].meekosamArgeeCategoryId;
	var genderId = profileData[i].gender;
	
	str+='<div class="col-sm-12 m_top20">';
		str+='<h4 class="text-success text-capital">about petitioner</h4>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Name <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetNameId"></span></label>';
		str+='<input type="text" value="'+profileData[i].name+'" class="form-control" id="petitionerNameId" name="meekosamGrievanceVO.petitionerName"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Relative Name <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetRelNameId"></span></label>';
		str+='<input type="text" value="'+profileData[i].relativeName+'" class="form-control" id="petitionerRelativeNameId" name="meekosamGrievanceVO.petitionerRelativeName"/>';
	str+='</div>';
	str+='<div class="col-sm-2 m_top10">';
		str+='<label>Gender <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetGenId"></span></label>';
		str+='<select class="selectChosen" id="petitionerGenderId" name="meekosamGrievanceVO.petitionerGender">';
			str+='<option value="0">Select Gender</option>';
			str+='<option value="Male">Male</option>';
			str+='<option value="Female">Female</option>';
		str+='</select>';
		/*str+='<input type="text" value="'+profileData[i].gender+'" class="form-control" id="petitionerGenderId" placeholder="Male/Female" name="meekosamGrievanceVO.petitionerGender"/>';*/
	str+='</div>';
	str+='<div class="col-sm-2 m_top10">';
		str+='<label>Date Of Birth <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetDOBId"></span></label>';
		str+='<div class="input-group">';
			str+='<span class="input-group-addon">';
				str+='<i class="glyphicon glyphicon-calendar"></i>';
			str+='</span>';
			str+='<input type="text" value="'+profileData[i].dateOfBirth+'" class="form-control" id="petitionerDOBId" name="meekosamGrievanceVO.petitionerDOB"/>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-2 m_top10">';
		str+='<label>Age <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetAgeId"></span></label>';
		if(profileData[i].age != null && profileData[i].age > 0)
			str+='<input type="text" value="'+profileData[i].age+'" class="form-control" id="petitionerAgeId" name="meekosamGrievanceVO.petitionerAge"/>';
		else
			str+='<input type="text" class="form-control" id="petitionerAgeId" name="meekosamGrievanceVO.petitionerAge"/>';
	str+='</div>';
	str+='<div class="col-sm-2 m_top10">';
		str+='<label>Phone Number <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetMobNoId"></span></label>';
		str+='<input type="text" value="'+profileData[i].mobileNo+'" class="form-control" id="petitionerMobileNO" name="meekosamGrievanceVO.petitionerMobileNo"/>';
	str+='</div>';
	str+='<div class="col-sm-2 m_top10">';
		str+='<label>Voter Number </label>';
		str+='<input type="text" value="'+profileData[i].voterCardNo+'" class="form-control" id="petitionerVoterId" name="meekosamGrievanceVO.petitionerVoterCardNo"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Aadhar Number </label>';
		str+='<input type="text" value="'+profileData[i].aadharNo+'" class="form-control" id="petitionerAadharId" name="meekosamGrievanceVO.petitionerAadharCardNo"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Email</label>';
		str+='<input type="text" value="'+profileData[i].emailId+'" class="form-control" id="petitionerEmailId" name="meekosamGrievanceVO.petitionerEmailId"/>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top20">';
		str+='<h4 class="text-success text-capital">location details</h4>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>District <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetDistrictId"></span></label>';
		str+='<select class="selectChosen" attr_districtId="'+districtId+'" id="districts" name="meekosamGrievanceVO.petitionerDistrictId"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Mandal <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetMandalId"></span></label>';
		str+='<select class="selectChosen" id="mandals" name="meekosamGrievanceVO.petitionerTehsilId"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Village/Town <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetVillageId"></span></label>';
		str+='<select class="selectChosen" id="panchayats" name="meekosamGrievanceVO.petitionerPanchayatId"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Habitation</label>';
		str+='<select class="selectChosen" id="habitationId" name="meekosamGrievanceVO.petitionerHamletId"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>House No <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetHouseNOId"></span></label>';
		str+='<input type="text" value="'+profileData[i].houseNo+'" class="form-control" id="houseNo" name="meekosamGrievanceVO.petitionerHouseNO"/>';
	str+='</div>';
	str+='<div class="col-sm-12">';
		str+='<div class="row">';
			str+='<div class="col-sm-5 m_top20">';
				str+='<h4 class="text-success text-capital">Caste Information <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetCasteId"></span></h4>';
				str+='<div class="panel panel-default m_top10">';
					str+='<div class="panel-body">';
						str+='<div id="casteDataId"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top20">';
		str+='<h4 class="text-success text-capital">Other</h4>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top20">';
		str+='<label>Occupation <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetOccupationId"></span></label>';
		str+='<select class="selectChosen" id="occupationListId" name="meekosamGrievanceVO.petitionerOccupation"><option value="0">Select Occupation</option></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top20">';
		str+='<label>Argee Category <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetArgeeId"></span></label>';
		str+='<select class="selectChosen" id="argeeCategoryListId" name="meekosamGrievanceVO.petitionerArgeeCategory"><option value="0">Select Argee Category</option></select>';
	str+='</div>';
	str+='<div class="col-sm-4 m_top20">';
		str+='<label>Annual Income <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgPetIncomeId"></span></label>';
		str+='<div class="panel panel-default">';
			str+='<div class="panel-body">';
				str+='<div id="annaulIncomeDataId"></div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#buildProfileData").html(str);
	$(".selectChosen").chosen();
	$("#petitionerDOBId").datetimepicker({
		format:'DD/MM/YYYY',
		viewMode: 'years',
		maxDate : moment().subtract(18,'years')
	});
	$("#petitionerGenderId").val(genderId).trigger("chosen:updated");
	getAllDistrictByStateId(1,districtId,'districts');
	getAllMandalsByDistrictID(districtId,tehsilId,'mandals');
	getAllPanchayatByMandalId(tehsilId,panchayatId,'panchayats');
	getAllHamletByPanchayatID(panchayatId,hamletId);
	getMeekosamArgeeCategoryList(meekosamArgeeCategoryId);
	getMeekosamOccupationList(meekosamOccupationId);
	getMeekosamCasteCategoryList(casteId);
	getMeekosamAnnualIncomeList(meekosamAnnualIncomeId);
}
function getAllMainDepartments(){
	$('#departmentId').empty();
	$('#departmentId').trigger('chosen:updated');
	
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getAllMainDepartmentsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$('#departmentId').append('<option value="0">Select Department</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$('#departmentId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		$('#departmentId').trigger('chosen:updated');
	});
}

function getIssueTypesForDepartment(){
	$('#issueTypeId').empty();
	$('#issueTypeId').trigger('chosen:updated');
	
	var deptId = $("#departmentId").val();
	var jsObj={
		departmentId : deptId
	}
	$.ajax({
		type:"POST",
		url :"getMeekosamIssueTypeListByDeptAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$('#issueTypeId').append('<option value="0">Select Issue Type</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$('#issueTypeId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		$('#issueTypeId').trigger('chosen:updated');
	});
}

$(document).on("change",".issueTypeClass",function(){
	var issueType = $(this).val();
	var value = $(this).attr("attr_val");
	var divId = $(this).attr("attr_divId");
	var id = $(this).attr("attr_id");
	
	if(value == 0)
		$(".issueClass").hide();
	
	$('#'+id).empty();
	$('#'+id).trigger('chosen:updated');
	
	var jsObj={
		parentIssueTypeId : issueType
	}
	$.ajax({
		type:"POST",
		url :"getMeekosamSubIssueTypeListForParentIssueTypeAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$('#'+id).append('<option value="0">Select Issue SubType</option>');
		if(result != null && result.length > 0){
			$("#"+divId).show();
			for(var i in result){
				$('#'+id).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		else{
			getDynamicValuesForIssue(issueType);
		}
		$('#'+id).trigger('chosen:updated');
	});
});

/*function getIssueSubTypes(id,divId,val){
	if(val == 0)
		$(".issueClass").hide();
	$('#'+id).empty();
	$('#'+id).trigger('chosen:updated');
	
	var issueType = $("#issueTypeId").val();
	var jsObj={
		parentIssueTypeId : issueType
	}
	$.ajax({
		type:"POST",
		url :"getMeekosamSubIssueTypeListForParentIssueTypeAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$('#issueSubTypeId').append('<option value="0">Select Issue SubType</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$('#issueSubTypeId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		$('#issueSubTypeId').trigger('chosen:updated');
	});
}*/

function getDynamicValuesForIssue(issueType){
	$("#buildPetitionerData").html('');
	//var issueType = $("#issueSubTypeId").val();
	var jsObj={
		issueTypeId : issueType
	}
	$.ajax({
		type:"POST",
		url :"getAllDynamicFieldsAndDataForIsueTypeAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildDynamicValuesForIssue(result);
		}
		
	});
}
function buildDynamicValuesForIssue(result)
{
	var str='';
	for(var i in result)
	{
		str+='<h4 class="panel-title m_top20"><strong>'+result[i].issueType+'</strong></h4>';
		str+='<table class="table table-bordered m_top20" style="background-color:#fff;">';
		for(var j in result[i].subList)
		{
			if(result[i].subList[j].issueFielsType == 'text' || result[i].subList[j].issueFielsType == 'textarea')
			{
				str+='<tr>';
					str+='<td>';
						str+='<div class="col-sm-12">';
							str+='<label>'+result[i].subList[j].issueField+'</label>';
						str+='</div>';
					str+='</td>';
					str+='<td>';
						str+='<div class="col-sm-12">';
							str+='<input type="'+result[i].subList[j].issueFielsType+'" attr_id="'+result[i].subList[j].id+'" id="'+result[i].subList[j].issueFieldId+'" class="form-control" name="petitioner'+result[i].subList[j].issueFielsType+'"/>';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}
			if(result[i].subList[j].issueFielsType == 'checkbox' || result[i].subList[j].issueFielsType == 'radio')
			{
				str+='<tr>';
					if(result[i].subList[j].subList.length > 0)
					{
						
						str+='<td>';
							str+='<div class="col-sm-12">';
								str+='<h4 class="panel-title">'+result[i].subList[j].issueField+'</h4>';
							str+='</div>';
						str+='</td>';
						str+='<td>';
							str+='<div class="col-sm-12">';
								str+='<div class="row">';
								for(var k in result[i].subList[j].subList)
								{
									str+='<div class="col-sm-4">';
										str+='<label class="'+result[i].subList[j].issueFielsType+'-inline">';
											str+='<input type="'+result[i].subList[j].issueFielsType+'" name="petitioner'+result[i].subList[j].issueFielsType+''+result[i].subList[j].id+'" class="petitioner'+result[i].subList[j].issueFielsType+'" attr_id="'+result[i].subList[j].id+'" id="'+result[i].subList[j].subList[k].issueRelationDataId+'"/>'+result[i].subList[j].subList[k].name+'';
										str+='</label>';
									str+='</div>';
								}
								str+='</div>';
							str+='</div>';
						str+='</td>';
					}else{
						str+='<td colspan="2">';
							str+='<label class="'+result[i].subList[j].issueFielsType+'-inline">';
								str+='<input type="'+result[i].subList[j].issueFielsType+'" name="petitioner'+result[i].subList[j].issueFielsType+''+result[i].subList[j].id+'" class="petitioner'+result[i].subList[j].issueFielsType+'" attr_id="'+result[i].subList[j].id+'" id="0"/>'+result[i].subList[j].issueField+'';
							str+='</label>';
						str+='</td>';
					}
				str+='</tr>';	
				
			}
			if(result[i].subList[j].issueFielsType == 'selectBox')
			{
				str+='<tr>';	
					str+='<td>';
						str+='<div class="col-sm-12">';
							str+='<h4 class="panel-title">'+result[i].subList[j].issueField+'</h4>';
						str+='</div>';
					str+='</td>';
					str+='<td>';
						str+='<div class="col-sm-12">';
							str+='<select class="selectChosen" attr_id="'+result[i].subList[j].id+'" name="petitioner'+result[i].subList[j].issueFielsType+'">';
							for(var k in result[i].subList[j].subList)
							{
								str+='<option value="'+result[i].subList[j].subList[k].issueRelationDataId+'">'+result[i].subList[j].subList[k].name+'</option>';
							}
							str+='</select>';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}
			if(result[i].subList[j].issueFielsType == 'calender')
			{
				str+='<tr>';
					str+='<td>';
						str+='<div class="col-sm-12">';
							str+='<h4 class="panel-title">'+result[i].subList[j].issueField+'</h4>';
						str+='</div>';
					str+='</td>';
					str+='<td>';
						str+='<div class="col-sm-12">';
							str+='<div class="input-group">';
								str+='<span class="input-group-addon">';
									str+='<i class="glyphicon glyphicon-calendar"/>';
								str+='</span>';
								str+='<input type="text" class="form-control datePickerPetitioner" attr_id="'+result[i].subList[j].id+'" name="petitioner'+result[i].subList[j].issueFielsType+'" class="form-control"/>';
							str+='</div>';
						str+='</div>';
					str+='</td>';
				str+='</tr>';
			}
		}
		str+='</table>';
	}
	$("#buildPetitionerData").html(str);
	$(".selectChosen").chosen();
	$(".datePickerPetitioner").datetimepicker({
		format:"DD/MM/YYYY"
	})
}

function getSubDeptsFrParentDept(departmentId){
	var jobj = {
		parntDeptId : departmentId
	}
	$.ajax({
		type : 'GET',
		url : 'getSubDeptsFrParentDeptAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		if(result != null && result.length > 0){
			$("#subDepartmentSelectId").html('');
			$('#subDepartmentSelectId').append('<option value="0">Select Sub Department</option>')
			for(var i in result){	
				$('#subDepartmentSelectId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#subDepartmentSelectId").trigger("chosen:updated");
		}
	});
}
function getParentLevelsOfLevel(departmentId){
	
	var jsObj = {
		departmentId : departmentId,
		levelId : $("#locationLevelSelectId").val()
	}
	$.ajax({
	  type:'GET',
	  url: 'getParentLevelsOfLevelAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildParentLevelsOfLevel(result,departmentId);
		}
	});
}
function buildParentLevelsOfLevel(result,departmentId){
	var str='';
		
		for(var i in result){
			if(i<result.length-1){
				str+='<div class="col-sm-4 m_top10">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;"></span></label>';
					str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-4 m_top10">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClass" style="color:#FF4C64;" id="errMsgAssignLocationId"></span></label>';
					str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'"  name="meekosamGrievanceVO.assignLevelValue"></select>';
				str+='</div>';
			}
			
		}
	
	$("#parentLevelDivId").html(str);
	$(".chosenSelect").chosen({width:'100%'});
	
	for(var i in result){
		
		if(result[i].idnameList !=null && result[i].idnameList.length>0){
			var newStr='';		
			newStr+='<option value="0">Select '+result[i].name+'</option>';
			for(var j in result[i].idnameList){
				 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
			}			
			$("#locationSubLevelSelectId"+result[i].id+"").html(newStr);
			$("#locationSubLevelSelectId"+result[i].id+"").trigger("chosen:updated");
		}
	}
	
	$("#locationSubLevelSelectId1").val(1);
	$("#locationSubLevelSelectId1").trigger('chosen:updated');
	$("#locationSubLevelSelectId1").trigger("change");
}

function getDepartmentLevels(subDepartmentId){
		
	var jsObj = {
		departmentId : subDepartmentId
	}
	$.ajax({
	  type:'GET',
	  url: 'getDepartmentLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentLevels(result);
		}
	});
	
}
function buildDepartmentLevels(result){
	
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	
	$("#locationLevelSelectId").html(str);
	$("#locationLevelSelectId").trigger("chosen:updated");
}
function getGovtSubLevelInfo(departmentId,levelId){
		
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");	
	
	var levelValue=$("#locationSubLevelSelectId"+levelId+"").val();	
	
	var jsObj = {
		departmentId : departmentId,
		levelId :levelId,
		levelValue:levelValue
	}
	$.ajax({
	  type:'GET',
	  url: 'getGovtSubLevelInfoAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null){
			buildGovtSubLevelInfoAction(result);
		}
			
	});
}
function buildGovtSubLevelInfoAction(result){
	
	var str='';
	if(result !=null){		
		if(result.idnameList !=null && result.idnameList.length>0){
			str+='<option value="0">Select '+result.name+'</option>';
			for(var i in result.idnameList){
				str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
			}
		}
		
		$("#locationSubLevelSelectId"+result.id+"").html(str);
		$("#locationSubLevelSelectId"+result.id+"").trigger("chosen:updated");
	}
	
}
function designationsByDepartment()
{
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId").chosen().val();
	var deprtmntId = $("#subDepartmentSelectId").chosen().val();
	var levelValue = $(".locationCls").chosen().val();
	
	var jsObj = {
		departmentId	: deprtmntId,
		levelId			: LevelId,
		levelValue		: levelValue
	}
	$.ajax({
	  type:'GET',
	  url: 'getDesignationsByDepartmentNewAction.action',			//getOldDesignationsByDepartmentNewAction
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		str+='<option value="0">Select Designation</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#designationsId").html(str);
		$("#designationsId").trigger("chosen:updated");
	});
}
function officersByDesignationAndLevel(designationId)
{
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId").chosen().val()
	var LevelValue = $(".locationCls").chosen().val()
	
	var jsObj = {
		levelId				: LevelId,
		levelValue			: LevelValue,
		designationId		: designationId
	}
	$.ajax({
	  type:'GET',
	  url: 'getOfficersByDesignationAndLevelNewAction.action',				//getOldOfficersByDesignationAndLevelNewAction
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		str+='<option value="0">Select Officer</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#officerNamesId").html(str);
		$("#officerNamesId").trigger("chosen:updated");
	});
}
function displayLevelsByLevelId(){
	var levelId = $("#alertLocLevelId").val();
	if(levelId == 0){
		$("#locDisDivId").hide();
		$("#locManDivId").hide();
		$("#locVilDivId").hide();
	}
	else if(levelId == 1){
		$("#locDisDivId").show();
		$("#locManDivId").hide();
		$("#locVilDivId").hide();
	}
	else if(levelId == 2){
		$("#locDisDivId").show();
		$("#locManDivId").show();
		$("#locVilDivId").hide();
	}
	else if(levelId == 3){
		$("#locDisDivId").show();
		$("#locManDivId").show();
		$("#locVilDivId").show();
	}
	getAllDistrictByStateId(1,0,"locationDistrictId")
}
function getAllPublicRepresentativeTypes()
{
	$("#referredTypeId").empty();
	$("#referredTypeId").trigger("chosen:updated");
	
	var jsObj = {
		
	}
	$.ajax({
	  type:'GET',
	  url: 'getAllPublicRepresentativeTypesAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		str+='<option value="0">Select Referred Type</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#referredTypeId").html(str);
		$("#referredTypeId").trigger("chosen:updated");
	});
}
function getPublicReresentativesByTypeAndDistrict()
{
	$("#referredNameId").empty();
	$("#referredNameId").trigger("chosen:updated");
	var refTypeId = $("#referredTypeId").chosen().val()
	var districtId = $("#districtsReferralId").chosen().val()
	
	var jsObj = {
		typeId				: refTypeId,
		districtId			: districtId
	}
	$.ajax({
	  type:'GET',
	  url: 'getPublicReresentativesByTypeAndDistrictAction.action',
	  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		str+='<option value="0">Select Candidate</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#referredNameId").html(str);
		$("#referredNameId").trigger("chosen:updated");
	});
}

function saveGrievanceInfo(){
	
	
	/*var str=''
	var increment = 0;
	$("input[name='petitionerradio']").each(function(){
		if($(this).is(":checked") == true)
		{
			var data = $(this).attr("id");
			var relationId = $(this).attr("attr_id");
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationDataId" value="'+data+'"/>';
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
			increment = increment+1;
		}
	})
	$("input[name='petitionercheckbox']").each(function(){
		if($(this).is(":checked") == true)
		{
			var data = $(this).attr("id");
			var relationId = $(this).attr("attr_id");
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationDataId" value="'+data+'"/>';
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
			increment = increment+1;
		}
	})
	$("input[name='petitionercalender']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueDataStr" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	})
	$("input[name='petitionertext']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueDataStr" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	});
	$("input[name='petitionertextarea']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueDataStr" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	});
	$("select[name='petitionerselectBox']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationDataId" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	});
	$("#dynamicDataDivId").html(str);
	return;
	*/
	
	$(".errorMsgClass").html("");
	//alert(1);
	var alertTitle = $("#alertTitleId").val().trim();
	//alert(2);
	var alertDesc = $("#alertdescriptionId").val().trim();
	var alertLevelId = $("#alertLocLevelId").val();
	var alertDistrict = $("#locationDistrictId").val();
	var alertMandal = $("#locationMandalId").val();
	var alertVillage = $("#locationVillageId").val();
	
	var category = $("#categoryId").val();
	var department = $("#departmentId").val();
	var issueType = $("#issueTypeId").val();
	//var issueSubType = $("#issueSubTypeId").val();
	//alert(3);
	var petitionerName = $("#petitionerNameId").val().trim();
	var petitionerRelativeNAme = $("#petitionerRelativeNameId").val().trim();
	var petitionerGender = $("#petitionerGenderId").val();
	var petitionerDOB = $("#petitionerDOBId").val().trim();
	var petitionerAge = $("#petitionerAgeId").val().trim();
	//alert(4);
	var petitionerMobileNO = $("#petitionerMobileNO").val().trim();
	var petitionerVoter = $("#petitionerVoterId").val().trim();
	var petitionerAadhar = $("#petitionerAadharId").val().trim();
	var petitionerhouseNo = $("#houseNo").val().trim();
	var petitionerdistricts = $("#districts").val();
	var petitionermandals = $("#mandals").val();
	var petitionerpanchayats = $("#panchayats").val();
	var caste = $("input[type='radio'].casteDataId:checked").attr("id");
	var occupation = $("#occupationListId").val();
	var argeeCategory = $("#argeeCategoryListId").val();
	var annaulIncome = $("input[type='radio'].annaulIncomeDataId:checked").attr("id");
	
	var referredBy = $("#referredTypeId").val();
	var referDistrict = $("#districtsReferralId").val();
	var referName = $("#referredNameId").val();
	
	var subDepartment = $("#subDepartmentSelectId").val();
	var assignLevelId = $("#locationLevelSelectId").val();
	var assignLevelValue = $(".locationCls").val();
	var designation = $("#designationsId").val();
	var officer = $("#officerNamesId").val();
	
	
	if(petitionerName == '' || petitionerName.length == 0){
		$("#errMsgPetNameId").html("Enter Name");
		return;
	}
	if(petitionerRelativeNAme == '' || petitionerRelativeNAme.length == 0){
		$("#errMsgPetRelNameId").html("Enter Relative Name");
		return;
	}
	if(petitionerGender == 0){
		$("#errMsgPetGenId").html("Select Gender");
		return;
	}
	if(petitionerDOB == '' || petitionerDOB.length == 0){
		$("#errMsgPetDOBId").html("Enter DateOfBirth");
		return;
	}
	if(petitionerAge == '' || petitionerAge.length == 0){
		$("#errMsgPetAgeId").html("Enter Age");
		return;
	}
	if(petitionerMobileNO.length==0 || petitionerMobileNO==''){
		$("#errMsgPetMobNoId").html(" Please Enter MobileNo ");
		return;
	}
	if(petitionerMobileNO.length != 10){
		$("#errMsgPetMobNoId").html(" Please Enter Valid MobileNO ");
		return;
	}
	if(petitionerMobileNO.length > 0){
		var numericExpression = /^[0-9]+$/;
		if(!petitionerMobileNO.match(numericExpression)){
			$('#errMsgPetMobNoId').html('Enter Numerics Only.');
			return;
		}
	}
	/*if(petitionerVoter == '' || petitionerVoter.length == 0){
		$("#errMsgPetVoterId").html("Enter VoterNo");
		return;
	}
	if(petitionerAadhar == '' || petitionerAadhar.length == 0){
		$("#errMsgPetAadharId").html("Enter AadharNo");
		return;
	}*/
	if(petitionerhouseNo == '' || petitionerhouseNo.length == 0){
		$("#errMsgPetHouseNOId").html("Enter HouseNO");
		return;
	}
	if(petitionerdistricts == 0){
		$("#errMsgPetDistrictId").html("Select District");
		return;
	}
	if(petitionermandals == 0){
		$("#errMsgPetMandalId").html("Select Mandal/Muncipality");
		return;
	}
	if(petitionerpanchayats == 0){
		$("#errMsgPetVillageId").html("Select Village/Ward");
		return;
	}
	if(caste == 0){
		$("#errMsgPetCasteId").html("Select Caste");
		return;
	}
	if(occupation == 0){
		$("#errMsgPetOccupationId").html("Select Occupation");
		return;
	}
	if(argeeCategory == 0){
		$("#errMsgPetArgeeId").html("Select Argee Category");
		return;
	}
	if(annaulIncome == 0){
		$("#errMsgPetIncomeId").html("Select Annual Income");
		return;
	}
	
	if(category == 0){
		$("#errMsgCategoryId").html("Select Category");
		return;
	}
	if(department == 0){
		$("#errMsgDepartmentId").html("Select Department");
		return;
	}
	if(issueType == 0){
		$("#errMsgIssueTypeId").html("Select IssueType");
		return;
	}
	/*if(issueSubType == 0){
		$("#errMsgIssueSubTypeId").html("Select Issue SubType");
		return;
	}*/
	
	if(alertLevelId == 0){
		$("#errMsgAlertLevelId").html("Select Level");
		return;
	}
	if(alertLevelId == 1){
		if(alertDistrict == 0){
			$("#errMsgAlertDistId").html("Select District");
			return;
		}
		$("#hiddenAlertLocationLevelId").val(3);
		$("#hiddenAlertLocationValueId").val(alertDistrict);
	}
	if(alertLevelId == 2){
		if(alertDistrict == 0){
			$("#errMsgAlertDistId").html("Select District");
			return;
		}
		if(alertMandal == 0){
			$("#errMsgAlertMandId").html("Select Mandal");
			return;
		}
		
		if(alertMandal.substring(0,1) == 1)
			$("#hiddenAlertLocationLevelId").val(5);
		else
			$("#hiddenAlertLocationLevelId").val(7);
		$("#hiddenAlertLocationValueId").val(alertMandal);
	}
	if(alertLevelId == 3){
		if(alertDistrict == 0){
			$("#errMsgAlertDistId").html("Select District");
			return;
		}
		if(alertMandal == 0){
			$("#errMsgAlertMandId").html("Select Mandal/Muncipality");
			return;
		}
		if(alertVillage == 0){
			$("#errMsgAlertVillId").html("Select Village/Ward");
			return;
		}
		
		if(alertMandal.substring(0,1) == 1)
			$("#hiddenAlertLocationLevelId").val(6);
		else
			$("#hiddenAlertLocationLevelId").val(8);
		$("#hiddenAlertLocationValueId").val(alertVillage);
	}
	if(alertTitle == '' || alertTitle.length == 0){
		$("#errMsgAlertTitleId").html("Enter Alert Title");
		return;
	}
	if(alertDesc == '' || alertDesc.length == 0){
		$("#errMsgAlertDescId").html("Enter Alert Description");
		return;
	}
	
	/*if(referredBy == 0){
		$("#errMsgReferredById").html("Select Referred By");
		return;
	}
	if(referDistrict == 0){
		$("#errMsgReferDistId").html("Select District");
		return;
	}
	if(referName == 0){
		$("#errMsgReferNameId").html("Select Refer Person");
		return;
	}*/
	
	if(subDepartment == 0){
		$("#errMsgSubDeptId").html("Select SubDepartment");
		return;
	}
	if(assignLevelId == 0){
		$("#errMsgLevelId").html("Select Location Level");
		return;
	}
	if(assignLevelValue == 0){
		$("#errMsgAssignLocationId").html("Select Location");
		return;
	}
	if(designation == 0){
		$("#errMsgDesignationId").html("Select Designation");
		return;
	}
	if(officer == 0){
		$("#errMsgOfficerId").html("Select Officer");
		return;
	}
	
	
	var str=''
	var increment = 0;
	$(".petitionerradio").each(function(){
		if($(this).is(":checked") == true)
		{
			var data = $(this).attr("id");
			var relationId = $(this).attr("attr_id");
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationDataId" value="'+data+'"/>';
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
			increment = increment+1;
		}
	})
	$(".petitionercheckbox").each(function(){
		if($(this).is(":checked") == true)
		{
			var data = $(this).attr("id");
			var relationId = $(this).attr("attr_id");
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationDataId" value="'+data+'"/>';
			str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
			increment = increment+1;
		}
	})
	$("input[name='petitionercalender']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueDataStr" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	})
	$("input[name='petitionertext']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueDataStr" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	});
	$("input[name='petitionertextarea']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueDataStr" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	});
	$("select[name='petitionerselectBox']").each(function(){
		var data = $(this).val();
		var relationId = $(this).attr("attr_id");
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationDataId" value="'+data+'"/>';
		str+='<input type="hidden" name="meekosamGrievanceVO.dynamicDataList['+increment+'].issueRelationId" value="'+relationId+'"/>';
		increment = increment+1;
	});
	$("#dynamicDataDivId").html(str);
	$("#hiddenPetitionerCasteId").val(caste);
	$("#hiddenPetitionerIncomeId").val(annaulIncome);
	
	$("#creatingLdngImg").show();
	$("#createMeekosamId").hide();
	
	var uploadHandler = {
		upload: function(o) {
			uploadResult = o.responseText;
			$("#createMeekosamId").show();
			if(uploadResult.indexOf("success") !=-1)
			{
			 $("#successmsg").html("Alert Created And Assigned Successfully ").css("color","green");	
			 $("#creatingLdngImg").hide();
			 setTimeout(function(){ 
				$("#successmsg").html("");
				clearFields();
				location.reload();
			 }, 1000);
			}else{  
				$("#successmsg").html("Exception Occured..Try Again...").css("color","red");	
				$("#creatingLdngImg").hide();
				setTimeout(function(){ 
					$("#successmsg").html("");
				}, 1000);
			}  
			return false;
		}
	};
	
	YAHOO.util.Connect.setForm('saveMeekosamGrievanceForm',true);
	YAHOO.util.Connect.asyncRequest('POST','saveMeekosamGrievanceAction.action',uploadHandler);
}

function clearFields(){
	$("#alertTitleId").html("");
	$("#alertdescriptionId").html("");
	$("#categoryId").val(0).trigger("chosen:updated");
}