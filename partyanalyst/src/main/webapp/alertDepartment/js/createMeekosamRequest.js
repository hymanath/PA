var profileData = '';
onLoadCalls();
onLoadClicks();
function onLoadClicks()
{
	$(".selectChosen").chosen();
	$(document).on("change","#districts",function(){
		getAllMandalsByDistrictID($(this).val(),'');
	});
	$(document).on("change","#mandals",function(){
		getAllPanchayatByMandalId($(this).val(),'')
	});
	$(document).on("change","#panchayats",function(){
		getAllHamletByPanchayatID($(this).val(),'')
	});
}
function onLoadCalls()
{
	//getMeekosamCasteCategoryList();
	getMeekosamArgeeCategoryList();
	//getMeekosamAnnualIncomeList();
	searchPetitionerDetailsByVoterNoAadharNoMobileNo();
	getMeekosamOccupationList();
}
function setDefaultImage(img){
    img.src = "images/User.png";
}
	
function getAllDistrictByStateId(stateId,districtId){
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
			$("#districtListId").html('');
			for(var i in result){	
				$('#districts').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#districts").trigger("chosen:updated");
			$("#districts").val(districtId).trigger("chosen:updated");
		}
	});
}
function getAllMandalsByDistrictID(districtId,tehsilId){
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
			$("#mandals").html('');
			$('#mandals').append('<option value="0">Select One Mandal or Town</option>');
			for(var i in result){ 
				$('#mandals').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#mandals").trigger("chosen:updated");
			$("#mandals").val(tehsilId).trigger("chosen:updated");
		}
	});
}

var LocationType = "";
function getAllPanchayatByMandalId(tehsilId,panchayatId){
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
			$("#panchayats").html('');
			$('#panchayats').append('<option value="0">Select One Panchayat or Ward</option>');
			for(var i in result){			
				$('#panchayats').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#panchayats").trigger("chosen:updated");
			$("#panchayats").val(panchayatId).trigger("chosen:updated");
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
		str+='<label>Annaul Income</label>';
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
	getAllDistrictByStateId(1,districtId);
	getAllMandalsByDistrictID(districtId,tehsilId);
	getAllPanchayatByMandalId(tehsilId,panchayatId);
	getAllHamletByPanchayatID(panchayatId,hamletId)
}