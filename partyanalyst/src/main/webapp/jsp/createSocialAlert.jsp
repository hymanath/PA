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
									<label>Social Media Type<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCategoryId"></span></label>
									<select class="chosenSelect" id="socialMediaTypeId" name="grievanceAlertVO.socialMediaTypeId" >  
										<option value="0">Select Type</option>
										<option value="5">Facebook</option> 
										<option value="6">Twitter</option> 
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDeptId"></span></label>
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
									<label>Mobile No<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMobileNoId"></span></label>
									<input id="mobileNoId" class="form-control" name="grievanceAlertVO.mobileNo" style="width: 230px" type="text"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgNameId"></span></label>
									<input type="text" id="nameId" class="form-control" name="grievanceAlertVO.name"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Account ID</label>
									<input type="text" id="emailId" class="form-control" placeholder="email/mobileNo" name="grievanceAlertVO.accountId"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Address<span style="color:red">*</span>&nbsp;&nbsp; </label>
									<input type="text" id="addressId" class="form-control" name="grievanceAlertVO.address"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12">
									<h4 class="text-capital m_top10">location details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLevelId"></span></label>
									<select class="chosen" id="alertlevelId" onchange="disableByGrievLevel();" >
										<option value="0">Select Location Level</option>
										<option value="1">Central</option>
										<option value="2">State</option>
										<option value="3">District</option>
										<option value="4">Constituency</option>
										<option value="5">Mandal/Muncipality</option>
										<option value="6">Village/Ward</option>
										<option value="7">Habitation</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="stateDiv">
									<label>State<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgStateId"></span></label>
									<select class="chosen" id="stateId" onChange="getDistrictsForReferPopup();" name="grievanceAlertVO.stateId" >
										<option value="0">Select State</option>
										<option value="1" >Andhra Pradesh</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="districtDiv" style="display:block;">
									<label>District<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDistrictId"></span></label>
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
								<div class="col-sm-3 m_top10" id="mandalDiv" style="display:block;">
									<label>Mandal<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMandalId"></span></label>
									<select class="chosen" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="grievanceAlertVO.mandalId">
										<option value="0">Select Mandal</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
									<label>Village/Panchayat<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
									<select class="chosen" id="referpanchayatId" onchange="getHamletss();" name="grievanceAlertVO.panchayatId">
										<option value="0">Select Village/Panchayat</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="villageDiv" style="display:block;">
									<label>Habitation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
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
						<input type="hidden" class="form-control" id="locationLevelIdhidden" name="grievanceAlertVO.locationLevelId"/>
						
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
</script>
</body>
</html>