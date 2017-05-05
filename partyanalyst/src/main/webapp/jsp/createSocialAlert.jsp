<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> GOVT SOCIAL ALERT </title>
	<!-- Bootstrap -->
	<!--<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">-->
	<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
	<!--<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">-->
	<!-- JQuery files (Start) -->
	
	<!--<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>-->
	<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
		<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<!-- YUI Dependency files (End) -->
	
	<style>
	label{
		font-weight : 400;
	}
	.block{
		padding:10px;
		background-color:#fff;
	}
	h4{
		font-weight : 600;
	}
	.m_top25{
		margin-top : 25px;
	}
	</style>
	</head>  	
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">

				<div class="" id="newGrevanceDivId">
					<form id="saveSocialAlertForm" name="saveSocialAlertForm" enctype="multipart/form-data" method="POST">
						<div class="panel-body bg_EF">
							<div class="row">
								<div class="col-sm-3 m_top10">
									<label>Social Media Type&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCategoryId"></span></label>
									<select class="chosenSelect" id="socialMediaTypeId" name="grievanceAlertVO.socialMediaTypeId" >  
										<option value="0">Select Type</option>
										<option value="5">Facebook</option> 
										<option value="6">Twitter</option> 
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Department&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDeptId"></span></label>
									<select class="chosenSelect" id="departmentsId" name="grievanceAlertVO.departmentId">  
										<option value="0">Select Department</option>
									</select>
								</div>
								
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-capital">Social details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Mobile No&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMobileNoId"></span></label>
									<input id="mobileNoId" onkeypress="return isNumber(event)" class="form-control" name="grievanceAlertVO.mobileNo" style="width: 230px" type="text"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Name&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgNameId"></span></label>
									<input type="text" id="nameId" class="form-control" name="grievanceAlertVO.name"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Account ID&nbsp;<span style="color:red">*</span></label>
									<input type="text" id="accountId" class="form-control" placeholder="email/mobileNo" name="grievanceAlertVO.accountId"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Email</label>
									<input type="text" id="emailId" class="form-control" name="grievanceAlertVO.emailId"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Address</label>
									<input type="text" id="addressId" class="form-control" name="grievanceAlertVO.address"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12">
									<h4 class="text-capital m_top10">location details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Location Level&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLevelId"></span></label>
									<select class="chosen" id="alertlevelId" name="grievanceAlertVO.locationLevelId" onchange="disableBySocialMediaReqLevel();" >
										<option value="0">Select Location Level</option>								<option value="2">State</option>
										<option value="3">District</option>
										<!--<option value="4">Constituency</option>-->
										<option value="5">Mandal/Muncipality</option>
										<option value="6">Village/Ward</option>
										<option value="7">Habitation</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="stateDiv" style="display:none;">
									<label>State&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgStateId"></span></label>
									<select class="chosen" id="stateId" onChange="getDistrictsForReferPopup();" name="grievanceAlertVO.stateId" >
										<option value="0">Select State</option>
										<option value="1" >Andhra Pradesh</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="districtDiv" style="display:none;">
									<label>District&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDistrictId"></span></label>
									<select class="chosen" id="referdistrictId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.districtId">
										<option value="0">Select District</option>
									</select>
								</div>
								<!--<div class="col-sm-3 m_top10" id="constituencyDiv" style="display:block;">
									<label>Constituency<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgConstId"></span></label>
									<select class="chosen" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.constituencyId">
										<option value="0">Select Constituency</option>
									</select>
								</div>-->
								<div class="col-sm-3 m_top10" id="mandalDiv" style="display:none;">
									<label>Mandal&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMandalId"></span></label>
									<select class="chosen" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="grievanceAlertVO.mandalId">
										<option value="0">Select Mandal</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:none;">
									<label>Village/Panchayat&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
									<select class="chosen" id="referpanchayatId" onchange="getHamletss();" name="grievanceAlertVO.panchayatId">
										<option value="0">Select Village/Panchayat</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="villageDiv" style="display:none;">
									<label>Habitation&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
									<select class="chosen" id="hamletId" name="grievanceAlertVO.hamletId">
										<option value="0">Select Habitation</option>
									</select>
								</div>
								<!--<div class="col-md-3 col-xs-12 col-sm-6" style="margin-left: 10px; margin-top: 45px;">
								 <a class="govtGrievanceCls" attr_id="location" style="cursor:pointer;">Click here to view Alerts in this location</a>
                                 </div>-->
							</div>							
							
							<div class="row">
								<div class="col-sm-8 m_top10">
									<label>Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgTitleId"></span></label>
									<label class="radio-inline" style="margin-bottom: 5px;">
										<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
									</label>
									<label class="radio-inline" style="margin-bottom: 5px;">
										<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
									</label>
									<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
								</div>
								<div class="col-sm-12 m_top10">
									<label>Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDescId"></span></label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 m_top10">
									<textarea class="form-control" id="alertdescriptionId" name="grievanceAlertVO.description"></textarea>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-capital">upload document linking to this alert</h4>
									<div class="block  m_top10">
										<input type="file" id="uploadFileId0" name="imageForDisplay"/>
										<button type="button" class="close closeFileCls"  style="margin-top:-26px;margin-right:700px" title="Click here to remove document" >x</button>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-4 m_top25">
									<input type="button" class="btn btn-success btn-block text-capital" value="create Social Alert" onclick="createSocialAlert();" id="createAlertId"></input>
								</div>
								<div class="col-sm-4">
									<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="creatingLdngImg"/>
								</div>
								<div class="col-sm-4">
									<span id="successmsg"></span>
								</div>
							</div>
						</div>
						<input type="hidden" class="form-control" id="locationLevelValhidden" name="grievanceAlertVO.locationValue"/>
						<!--<input type="hidden" class="form-control" id="locationLevelIdhidden" name="grievanceAlertVO.locationLevelId"/>-->
						
						<!--<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.callerTypeId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.issueTypeId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.entrySourceId"/>
						<input type="hidden" class="form-control" value="49" name="grievanceAlertVO.departmentId"/>
						<input type="hidden" class="form-control" value="5" name="grievanceAlertVO.levelId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.stateId"/>
						-->
					</form>
				</div>
    </div>
</div>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
 <script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
  <script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script>

$(".chosen").chosen({
	width : '100%'
});
$(".chosenSelect").chosen({width:'100%'})

// Load the Google Transliterate API
google.load("elements", "1", {
	packages: "transliteration"
});


function getAlertCallerTypes(){
	var jsObj={
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getAlertCallerTypesAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str +='<option value="0">Select Caller Type</option>';
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#callerTypeId").html(str);
			$("#callerTypeId").trigger('chosen:updated');
		}
	});
}

var control;
var lang;

function onLoad() {
   lang = $("input[name=language]:checked").val();
	var options = {
		sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
		destinationLanguage:[''+lang+''],
		shortcutKey: 'alt+t',
		transliterationEnabled: true
	};
	// Create an instance on TransliterationControl with the required options.
	control = new google.elements.transliteration.TransliterationControl(options);
	// Enable transliteration in the textbox with id 'descrptionId'.
	if ($('#alertTitleId').length){
		control.makeTransliteratable(['alertTitleId']);
	}
	if ($('#alertdescriptionId').length){
		control.makeTransliteratable(['alertdescriptionId']);
	}
	
}
function languageChangeHandler(){
   var lang1 = $("input[name=language]:checked").val();
	if(lang1 =="en"){
		control.disableTransliteration();
	}else{
		control.enableTransliteration();
		control.setLanguagePair(
		google.elements.transliteration.LanguageCode.ENGLISH,
		lang1);
	}
}
google.setOnLoadCallback(onLoad);

function disableBySocialMediaReqLevel(){
	var alertLevelId = $("#alertlevelId").val();
	if(alertLevelId == 0){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		$("#stateDiv,#districtDiv,#constituencyDiv,#mandalDiv,#panchayatDiv,#villageDiv").hide();	
	}else if(alertLevelId == 2){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv,#constituencyDiv,#mandalDiv,#panchayatDiv,#villageDiv").hide();	
	}else if(alertLevelId == 3){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv,#districtDiv").show();
		$("#constituencyDiv,#mandalDiv,#panchayatDiv,#villageDiv").hide();
	}else if(alertLevelId == 4){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv,#districtDiv,#constituencyDiv").show();
		$("#mandalDiv,#panchayatDiv,#villageDiv").hide();
	}else if(alertLevelId == 5){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv,#districtDiv,#constituencyDiv,#mandalDiv").show();
		$("#panchayatDiv,#villageDiv").hide();
	}else if(alertLevelId == 6){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv,#districtDiv,#constituencyDiv,#mandalDiv,#panchayatDiv").show();
		$("#villageDiv").hide();
	}else if(alertLevelId == 7){
		$("#stateId").val(0).trigger('chosen:updated');
		$("#referdistrictId,#referconstituencyId,#refermandalNameId,#referpanchayatId,#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv,#districtDiv,#constituencyDiv,#mandalDiv,#panchayatDiv,#villageDiv").show();
	}
}

function getDistrictsForReferPopup() {
	var stateId = $("#stateId").val();
	
	var jobj = {
		stateId : stateId  
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		var str='';
		
			str+='<option value="0">Select District</option>';
		if(result != null && result.length > 0){    
			for(var i in result){
				if(result[i].id > 0 && result[i].id != 517)
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#referdistrictId").html(str);
			$("#referdistrictId").trigger('chosen:updated');
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#referpanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
			$("#hamletId").trigger('chosen:updated');
			$("#modalDistrictId").html(str);
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalMandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#modalMandalNameId").trigger('chosen:updated');
			$("#modalPanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#modalPanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
			$("#hamletId").trigger('chosen:updated');
			
			 
		}else{
			//for dist
			$("#referdistrictId").html('<option value="0">Select District</option>');
			$("#referdistrictId").trigger('chosen:updated');   //srujana modalDistrictId  modalMandalNameId modalPanchayatId
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#referpanchayatId").trigger('chosen:updated');  
			$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
			$("#hamletId").trigger('chosen:updated');
			$("#modalDistrictId").html('<option value="0">Select District</option>');
			$("#modalDistrictId").trigger('chosen:updated');
			$("#modalMandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#modalMandalNameId").trigger('chosen:updated');
			$("#modalPanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
			$("#modalPanchayatId").trigger('chosen:updated'); 
		} 
	});  
  }
  
function getMandalsByConstituencyForReferPopup(){
	var districtId = $('#referdistrictId').val();
	 
	var url = "getAllMandalsByDistrictIDAction.action";
	var deptName = $("#departmentsId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getAllMandalsByDistrictIDAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getAllLebsByDistrictIDAction.action";
	}
	 
	 var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : url,
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">Select Mandal/Muncipality</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#refermandalNameId").html(mandalStr);
				$("#refermandalNameId").trigger('chosen:updated');                
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
				$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
				$("#hamletId").trigger('chosen:updated');
				$("#designationsId").html('<option value="0">Select Designation</option>');//empty();
				$("#designationsId").trigger("chosen:updated");
			}else{
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
				$("#referpanchayatId").trigger('chosen:updated');
				$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
				$("#hamletId").trigger('chosen:updated');				
			}
		});
 }
 
function getPanchayatsForReferPopup(){
	 //designationsByDepartment();
	
	 $("#referpanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId').val();
	 var  type = $("#refermandalNameId option:selected").text();
	 		   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	 var url = "getPanchayatAndWardDetailsAction.action";
	 var deptName = $("#departmentsId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getPanchayatAndWardDetailsAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getUrbanLocalitiesForMuncipalityAction.action";
	}
	
	  var jsObj={
				mandalId :mandalId,
				type:type,
				task:""
				
			}
	 $.ajax({
				type:"POST",
				url :url,
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Village/Urban Locality</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#referpanchayatId").html(panchyatStr);
					$("#referpanchayatId").trigger('chosen:updated');
					$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#referpanchayatId").html('<option value="0">Select Village/Urban Locality</option>');
					$("#referpanchayatId").trigger('chosen:updated');
					$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
					$("#hamletId").trigger('chosen:updated');					
				}     
		});
}
function getHamletss(){
	
	 $("#hamletId").find('option').not(':first').remove();
	 var panchayatId = $('#referpanchayatId').val();
	 var  type = $("#refermandalNameId option:selected").text();
			   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	
	var url = "getHamletsForPanchayatAction.action";
	 var deptName = $("#departmentsId option:selected").text();
	var locationType = deptName.split("-")[1];
	if(locationType != null && locationType == "Rural"){
		url = "getHamletsForPanchayatAction.action";
	}
	else if(locationType != null && locationType == "Urban"){
		url = "getUrbanBlocksForLocalityAction.action";
	}
	
	//if(type == "mandal"){
		
		var jsObj={
				panchayatId :panchayatId,
				task:""
			}
	 $.ajax({
				type:"POST",
				url :url,
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Habitation/Urban Block</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#hamletId").html(panchyatStr);
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#hamletId").html('<option value="0">Select Habitation/Urban Block</option>');
					$("#hamletId").trigger('chosen:updated'); 
				}     
		});
	//}
}

function createSocialAlert(){
	if($("#socialMediaTypeId").val() == "undefined" || $("#socialMediaTypeId").val() == 0){
		alert("Please Select Social Media Type.");
		return;
	}else if($("#departmentsId").val() == "undefined" || $("#departmentsId").val() == 0){
		alert("Please Select Department.");
		return;
	}else if($("#mobileNoId").val().trim() == "" || $("#mobileNoId").val() == "undefined"){
		alert("Please Enter Mobile Number.");
		return;
	}else if($("#mobileNoId").val().trim().length != 10){
		alert("Please Enter Valid Mobile Number.");
		return;
	}else if($("#nameId").val().trim() == "" || $("#nameId").val() == "undefined"){
		alert("Please Enter Name.");
		return;
	}else if($("#accountId").val().trim() == "" || $("#accountId").val() == "undefined"){
		alert("Please Enter AccountId.");
		return;
	}else if($("#alertlevelId").val() == 0){
		alert("Please Select Alert Location.");
		return;
	}else if($("#alertlevelId").val() == 2){
		if($("#stateId").val() == 0){
			alert("Please Select State.");
			return;
		}
		$("#locationLevelValhidden").val($("#stateId").val());
	}else if($("#alertlevelId").val() == 3){
		if($("#stateId").val() == 0){
			alert("Please Select State.");
			return;
		}else if($("#referdistrictId").val() == 0){
			alert("Please Select District.");
			return;
		}
		$("#locationLevelValhidden").val($("#referdistrictId").val());
	}else if($("#alertlevelId").val() == 5){
		if($("#stateId").val() == 0){
			alert("Please Select State.");
			return;
		}else if($("#referdistrictId").val() == 0){
			alert("Please Select District.");
			return;
		}else if($("#refermandalNameId").val() == 0){
			alert("Please Select Mandal.");
			return;
		}
		$("#locationLevelValhidden").val($("#refermandalNameId").val());
	}else if($("#alertlevelId").val() == 6){
		if($("#stateId").val() == 0){
			alert("Please Select State.");
			return;
		}else if($("#referdistrictId").val() == 0){
			alert("Please Select District.");
			return;
		}else if($("#refermandalNameId").val() == 0){
			alert("Please Select Mandal.");
			return;
		}else if($("#referpanchayatId").val() == 0){
			alert("Please Select Panchayat.");
			return;
		}
		$("#locationLevelValhidden").val($("#referpanchayatId").val());
	}else if($("#alertlevelId").val() == 7){
		if($("#stateId").val() == 0){
			alert("Please Select State.");
			return;
		}else if($("#referdistrictId").val() == 0){
			alert("Please Select District.");
			return;
		}else if($("#refermandalNameId").val() == 0){
			alert("Please Select Mandal.");
			return;
		}else if($("#referpanchayatId").val() == 0){
			alert("Please Select Panchayat.");
			return;
		}else if($("#hamletId").val() == 0){
			alert("Please Select Habitation.");
			return;
		}
		$("#locationLevelValhidden").val($("#hamletId").val());
	}
	
	if($("#alertTitleId").val().trim() == "" || $("#alertTitleId").val() == "undefined"){
		alert("Please Enter Title.");
		return;
	}else if($("#alertdescriptionId").val().trim() == "" || $("#alertdescriptionId").val() == "undefined"){
		alert("Please Enter Description.");
		return;
	}
	
	var uploadHandler = {
		upload: function(o) {
		  uploadResult = o.responseText;
		  showUploadStatus(uploadResult);  
		}
	};

	YAHOO.util.Connect.setForm('saveSocialAlertForm',true);
	YAHOO.util.Connect.asyncRequest('POST','saveSocialAlertAction.action',uploadHandler);
		
}

function showUploadStatus(myResult){
	var result = (String)(myResult);
	if(result.search('SUCCESS') != -1){
		alert("Alert Creation Failed, Please Try Again.");
	}else{
		alert("Alert Created Successfully.");
		window.location.reload(true);
	}
}

getAllDepartments();
function getAllDepartments(){
	$("#departmentsId").html("");
	var jsObj={}
	$.ajax({
		type:"POST",
		url :"getAllDeptsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str="";
		str+='<option value="0">Select Department</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#departmentsId").html(str);
		$("#departmentsId").trigger('chosen:updated');
	});
}
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
</script>
</body>
</html>