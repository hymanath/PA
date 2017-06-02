var profileData = '';
onLoadCalls();
onLoadClicks();
function onLoadClicks()
{
	$(".selectChosen").chosen();
	$(document).on("change","#districts",function(){
		getAllMandalsByDistrictID($(this).val(),'','mandals');
	});
	$(document).on("change","#mandals",function(){
		getAllPanchayatByMandalId($(this).val(),'','panchayats')
	});
	$(document).on("change","#panchayats",function(){
		getAllHamletByPanchayatID($(this).val(),'')
	});
	$(document).on("change","#districtsPetitionerId",function(){
		getAllMandalsByDistrictID($(this).val(),'','mandalsPetitionerId');
	});
	$(document).on("change","#mandalsPetitionerId",function(){
		getAllPanchayatByMandalId($(this).val(),'','villagePetitionerId')
	});
	$(document).on("change","#departmentId",function(){
		getSubDeptsFrParentDept($(this).val());
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
	
	$(document).on("click","#addOneMorePetitionerId",function(){
		var str='';
		str+='<tr>';
			str+='<td>'+$("#districtsPetitionerId option:selected").text()+'</td>';
			str+='<td>'+$("#mandalsPetitionerId option:selected").text()+'</td>';
			str+='<td>'+$("#villagePetitionerId option:selected").text()+'</td>';
			str+='<td>'+$("#surveyNoPetitionerId").val()+'</td>';
			str+='<td>'+$("#landInAcresPetitionerId").val()+'</td>';
			str+='<td>'+$("#landInCentPeitionerId").val()+'</td>';
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
	});
	$(document).on("click",".deletePetitionerRow",function(){
		$(this).closest("tr").remove();
	});
}
function onLoadCalls()
{
	getAllMainDepartments();
	getAllDistrictByStateId(1,'',"districtsPetitionerId")
	//getMeekosamCasteCategoryList();
	getMeekosamArgeeCategoryList();
	//getMeekosamAnnualIncomeList();
	searchPetitionerDetailsByVoterNoAadharNoMobileNo();
	getMeekosamOccupationList();
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
		url : 'getDistrictsForStateAction.action',
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
			$('#'+id).append('<option value="0">Select One Mandal or Town</option>');
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
			$('#'+id).append('<option value="0">Select One Panchayat or Ward</option>');
			for(var i in result){			
				$('#'+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#"+id).trigger("chosen:updated");
			$("#"+id).val(panchayatId).trigger("chosen:updated");
		}
	});
}
function buildPanchayatList(result){
	
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
			$('#habitationId').append('<option value="0">Select One Hamlet</option>');
			for(var i in result){			
				$('#habitationId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#habitationId").trigger("chosen:updated");
			$("#habitationId").val(hamletId).trigger("chosen:updated");
		}
	});
}

function getMeekosamOccupationList(){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamOccupationListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildMeekosamOccupationList(result);
		}
	});
}
function buildMeekosamOccupationList(result){
	$("#occupationListId").html('');
	$('#occupationListId').append('<option value="0">Select One Occupation</option>');
	for(var i in result){			
		$('#occupationListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
}

function getMeekosamCasteCategoryList(){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamCasteCategoryListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		for(var i in result){
			alert(result[i].name);
		}
	});
}

function getMeekosamArgeeCategoryList(){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamArgeeCategoryListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildMeekosamArgeeCategoryList(result);
		}
	});
}
function buildMeekosamArgeeCategoryList(result){
	$("#ArgeeCategoryListId").html('');
	$('#ArgeeCategoryListId').append('<option value="0">Select One Argee Category</option>');
	for(var i in result){			
		$('#ArgeeCategoryListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
}

function getMeekosamAnnualIncomeList(){
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getMeekosamAnnualIncomeListAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		for(var i in result){
			alert(result[i].name);
		}
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
	var type = '';
	
	$(".typeOfSearch").each(function(){
		if($(this).is(':checked'))
		{
			type = $(this).val();
		}
	});
	var searchValue = $(".searchValue").val();
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
						str+='<p><i class="fa fa-address-card"></i> '+result[i].aadharNo+'</p>';
						str+='<p><i class="fa fa-address-card-o"></i> '+result[i].voterCardNo+'</p>';
					str+='</div>';
				str+='</div>';
				str+='<input type="checkbox" name="profile" onclick="buildProfileData('+i+');" class="pull-right"/>';//profile
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
	var districtId = profileData[i].districtId
	var tehsilId = profileData[i].tehsil
	var panchayatId = profileData[i].panchayatId
	var hamletId = profileData[i].hamletId
	str+='<div class="col-sm-12 m_top20">';
		str+='<h4 class="panel-title text-capital">about petitioner</h4>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Name</label>';
		str+='<input type="text" value="'+profileData[i].name+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Relative Name</label>';
		str+='<input type="text" value="'+profileData[i].relativeName+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Gender</label>';
		str+='<input type="text" value="'+profileData[i].gender+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Age</label>';
		str+='<input type="text" value="'+profileData[i].age+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Phone Number</label>';
		str+='<input type="text" value="'+profileData[i].mobileNo+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Voter Number</label>';
		str+='<input type="text" value="'+profileData[i].voterCardNo+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Aadhar Number</label>';
		str+='<input type="text" value="'+profileData[i].aadharNo+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Email</label>';
		str+='<input type="text" value="'+profileData[i].emailId+'" class="form-control"/>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top20">';
		str+='<h4 class="panel-title text-capital">about petitioner</h4>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>District</label>';
		str+='<select class="selectChosen" attr_districtId="'+districtId+'" id="districts"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Mandal</label>';
		str+='<select class="selectChosen" id="mandals"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Village/Town</label>';
		str+='<select class="selectChosen" id="panchayats"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>Habitation</label>';
		str+='<select class="selectChosen" id="habitationId"></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top10">';
		str+='<label>House No</label>';
		str+='<input type="text" value="'+profileData[i].houseNo+'" class="form-control" id="houseNo"/>';
	str+='</div>';
	str+='<div class="col-sm-12">';
		str+='<div class="row">';
			str+='<div class="col-sm-4 m_top20">';
				str+='<h4 class="panel-title">Caste Information</h4>';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-body">';
						str+='<label class="radio-inline">';
							str+='<input type="radio"/>SC';
						str+='</label>';
						str+='<label class="radio-inline">';
							str+='<input type="radio"/>ST';
						str+='</label>';
						str+='<label class="radio-inline">';
							str+='<input type="radio"/>BC';
						str+='</label>';
						str+='<label class="radio-inline">';
							str+='<input type="radio"/>Minority';
						str+='</label>';
						str+='<label class="radio-inline">';
							str+='<input type="radio"/>Others';
						str+='</label>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top20">';
		str+='<h4 class="panel-title">Other</h4>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top20">';
		str+='<label>Occupation</label>';
		str+='<select class="selectChosen" ><option>Select Occupation</option></select>';
	str+='</div>';
	str+='<div class="col-sm-3 m_top20">';
		str+='<label>Annual Income</label>';
		str+='<div class="panel panel-default">';
			str+='<div class="panel-body">';
				str+='<label class="radio-inline">';
					str+='<input type="radio"/> < 60,000';
				str+='</label>';
				str+='<label class="radio-inline">';
					str+='<input type="radio"/> 60000-75000';
				str+='</label>';
				str+='<label class="radio-inline">';
					str+='<input type="radio"/> >75000';
				str+='</label>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#buildProfileData").html(str);
	$(".selectChosen").chosen();
	getAllDistrictByStateId(1,districtId,'districts');
	getAllMandalsByDistrictID(districtId,tehsilId,'mandals');
	getAllPanchayatByMandalId(tehsilId,panchayatId,'panchayats');
	getAllHamletByPanchayatID(panchayatId,hamletId)
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

function getIssueSubTypes(){
	$('#issueSubTypeId').empty();
	$('#issueSubTypeId').trigger('chosen:updated');
	
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
}

function getDynamicValuesForIssue(){
	$("#buildPetitionerData").html('');
	var issueType = $("#issueSubTypeId").val();
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
		str+='<div class="row m_top10">';
		for(var j in result[i].subList)
		{
			if(result[i].subList[j].issueFielsType == 'TextBox')
			{
				str+='<div class="col-sm-3">';
					str+='<label>'+result[i].subList[j].issueField+'</label>';
					str+='<input type="text" id="'+result[i].subList[j].issueFieldId+'" class="form-control"/>';
				str+='</div>';
			}
			if(result[i].subList[j].issueFielsType == 'Radio')
			{
				str+='<div class="col-sm-12 m_top10">';
					str+='<h4 class="panel-title">'+result[i].subList[j].issueField+'</h4>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-12">';
							for(var k in result[i].subList[j].subList)
							{
								str+='<label class="radio-inline">';
									str+='<input type="radio" name="petitionerRadio" id="'+result[i].subList[j].subList[k].id+'"/>'+result[i].subList[j].subList[k].name+'';
								str+='</label>';
							}
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
			if(result[i].subList[j].issueFielsType == 'CheckBox')
			{
				str+='<div class="col-sm-12 m_top10">';
					str+='<h4 class="panel-title">'+result[i].subList[j].issueField+'</h4>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-12">';
							for(var k in result[i].subList[j].subList)
							{
								str+='<label class="checkbox-inline">';
									str+='<input type="checkbox" name="petitionerCheckbox" id="'+result[i].subList[j].subList[k].id+'"/>'+result[i].subList[j].subList[k].name+'';
								str+='</label>';
							}
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
		}
		str+='</div>';
	}
	$("#buildPetitionerData").html(str);
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
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
					str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-4 m_top10">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
					str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="grievanceAlertVO.levelValue" ></select>';
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
	var deprtmntId = $("#departmentId").chosen().val();
	var levelValue = $(".locationCls").chosen().val();
	
	var jsObj = {
		departmentId	: deprtmntId,
		levelId			: LevelId,
		levelValue		: levelValue
	}
	$.ajax({
	  type:'GET',
	  url: 'getOldDesignationsByDepartmentNewAction.action',
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
	  url: 'getOldOfficersByDesignationAndLevelNewAction.action',
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