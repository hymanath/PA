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
	.bg_cc
	{
		background-color:#ccc !important;	
	}
	.tableStyleAlign thead th {font-size:12px !important;padding:0px !important}
	.tableStyleAlign tr td{font-size:12px !important;padding:0px !important}
	
	</style>
	</head>  	
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default m_top10">
			
				<!--<div class="panel-heading bg_cc"   style="height:50px;" >
                	<h4 class="panel-title" >GOVERNMENT GRIEVANCE 
						<button class="btn btn-success pull-right" onclick="showDashboard();"> Dashboard </button>
						<button class="btn btn-primary  pull-right" style="margin-right:5px"  onclick="showNewGrievance();" > + New Grievance </button> 
					</h4>
					
                </div>-->
				<div class="panel-heading bg_cc">
					<div class="row">
						<div class="col-sm-6">
							<h4 class="panel-title" >GOVERNMENT GRIEVANCE </h4>
						</div>
						<div class="col-sm-6">
							<ul class="switch-btn pull-right">
								<li class="active" onclick="showNewGrievance();" >+ New Grievance</li>
								<li onclick="showDashboard();" attr_type="status">Dashboard</li>
							</ul>
						</div>
					</div>
				</div>
				
			
				<div class="" id="newGrevanceDivId">
					<form id="saveSocialAlertForm" name="saveSocialAlertForm" enctype="multipart/form-data" method="POST">
						<div class="panel-body bg_EF">
							<div class="row">
								<div class="col-sm-3 m_top10">
									<label>Social Media Type&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCategoryId"></span></label>
									<select class="chosenSelect" id="socialMediaTypeId" name="grievanceAlertVO.socialMediaTypeId" >  
										<option value="0">Select Type</option>
										<option value="1">Facebook</option> 
										<option value="2">Twitter</option> 
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
									<select class="chosen" id="alertlevelId" onchange="disableBySocialMediaReqLevel();" >
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
								<div class="col-sm-12 m_top10">
									<h4 class="text-capital">grievance details</h4>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-8 m_top10">
									<label>Grievance Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgTitleId"></span></label>
									<label class="radio-inline" style="margin-bottom: 5px;">
										<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
									</label>
									<label class="radio-inline" style="margin-bottom: 5px;">
										<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
									</label>
									<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
								</div>
								<div class="col-sm-12 m_top10">
									<label>Grievance Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDescId"></span></label>
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
				
				<div id="dashboardGrevanceDivId" class="panel-body" style="display:none;">
					<div class="row">
						<div class="col-sm-3 m_top10" style="display:none;">
							<label>Social Media Type&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCategoryNewId"></span></label>
							<select class="chosenSelect" id="socialMediaTypeNewId">  
								<option value="0">Select Type</option>
								<option value="1">Facebook</option> 
								<option value="2">Twitter</option> 
							</select>
						</div>
						<div class="col-sm-3 m_top10">
							<label>Department&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDeptNewId"></span></label>
							<select class="chosenSelect" id="departmentsNewId" onChange="getSocialAlertDetails();getSocialAlertFeedbackDetails()">  
								<option value="0">Select Department</option>
							</select>
						</div>
						<div class="col-sm-3 m_top10" id="dateDivId" style="display:none;">
							<label>DateRange&nbsp;<span style="color:red">*</span></label>
							<div class="input-group pull-right dateRangePickerClsForEvents">
								<input type="text" id="dateRangeIdForEvents" class="form-control" onChange="getSocialAlertDetails();getSocialAlertFeedbackDetails();"/>
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</div>
						</div>
					</div>
					<div id="summaryDiv"></div>
					<div id="feedbackSummaryDivId"></div>
					<div class="row" id="verificationStatusMainDivId" style="display:none" >
						<div class="col-sm-3 m_top10">
							<label>VERIFICATION STATUS&nbsp;<span style="color:red">*</span><span class="errorMsgClas" style="color:#FF4C64;" id=""></span></label>
							<select class="chosenSelect" id="verificationStatusId">  
								<option value="0">Select Status</option>
								<option value="Y">Approved</option>
								<option value="N">Rejected</option>
							</select>
						</div>					
					</div>
					<div class="m_top20 table-responsive" id="alertDataDivId"></div>
				
				</div>
    </div>
</div>
<div class="modal fade" id="updateAlertModalDivId" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content customModal">
      <div class="modal-header">
       <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Feedback Status Updation</h4>
      </div>
      <div class="panel-body" style="padding:10px;background-color: lightgrey;">
		<div id="buildUpdateDivId">
		</div>
		
		<div class="col-md-12 col-xs-12 col-sm-12  m_top10">
			  <label><span id="feedbackLabelId"></span><span style="color:red">*</span><span id="statusErrMsgId" style="color:red"></span></label><br>
			  <select id="feedbackStatusList" class="form-control" >
				<option value="0"> </option>
			  </select>

			  <span id="reOpenSpanId" style="display:none"><input id="reopenCheckboxId" type="checkbox" value="11"/> Reopen </span>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">			
				<span id="saveMsgId"></span>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<span id="updateAlertFooterSpanId"></span>
        <!--<button type="button" class="btn btn-primary updateAlertFooterCls" >Save</button>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<input type="hidden" id="hiddenAlertId"></input>
<input type="hidden" id="hiddenSourceId"></input>
<input type="hidden" id="hiddenStatusId"></input>

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
function showNewGrievance(){
	$('#dateDivId').hide();
	$('#dashboardGrevanceDivId').hide();
	$('#newGrevanceDivId').show();
	$("#socialMediaTypeId,#departmentsId").val(0);
	$("#socialMediaTypeId,#departmentsId,#verificationStatusId").trigger('chosen:updated');
	
	
	
}
$("#dateRangeIdForEvents").daterangepicker({
	opens: 'right',
	startDate: moment().subtract('month').startOf('month'),
	endDate: moment().subtract('month').endOf('month'),
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'This Month': [moment().startOf('month'), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   
	   'This Year': [moment().startOf('Year'), moment()]
	}
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
	}
	if($("#departmentsId").val() == "undefined" || $("#departmentsId").val() == 0){
		alert("Please Select Department.");
		return;
	}
	if($("#mobileNoId").val().trim() == "" || $("#mobileNoId").val() == "undefined"){
		alert("Please Enter Mobile Number.");
		return;
	}
	if($("#mobileNoId").val().trim().length != 10){
		alert("Please Enter Valid Mobile Number.");
		return;
	}
	if($("#nameId").val().trim() == "" || $("#nameId").val() == "undefined"){
		alert("Please Enter Name.");
		return;
	}
	if($("#accountId").val().trim() == "" || $("#accountId").val() == "undefined"){
		alert("Please Enter AccountId.");
		return;
	}
	
	
	/* if($("#alertlevelId").val() == 0){
		alert("Please Select Alert Location.");
		return;
	}
	if($("#alertlevelId").val() == 2){
		if($("#stateId").val() == 0){
			alert("Please Select State.");
			return;
		}
		$("#locationLevelValhidden").val($("#stateId").val());
	}
	if($("#alertlevelId").val() == 3){
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
	} */
	
	var level = $("#alertlevelId").val();
	var  state=$("#stateId").val();
	var  district=$("#referdistrictId").val();
	var  mandal=$("#refermandalNameId").val();
	var  panchayat=$("#referpanchayatId").val();
	
	
if(level==0)
  {
     //$("#errorDiv1").html(" Please select level ");
	 alert("Please select level.");
	 return;
  }  
  if(level==2)
  {
    if(state==0)               
      {
      //$("#errorDiv1").html(" Please select state ");
	  alert("Please Select State.");
          return;
     }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      //$("#errorDiv1").html(" Please select state ");
	  alert("Please Select State.");
          return;
    }
    if(district==0)
    {
      //$("#errorDiv1").html(" Please select District ");
	   alert("Please select District.");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        //$("#errorDiv1").html(" Please select state ");
		 alert("Please select state.");
            return;
      }
    if(district==0)
      {
        //$("#errorDiv1").html(" Please select District ");
		alert("Please select District.");
            return;
      }
   
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId option:selected").text();
    if(state==0)
      {
        //$("#errorDiv1").html(" Please select state ");
			alert("Please select state.");
            return;
      }
    if(district==0)
      {
        //$("#errorDiv1").html(" Please select District ");
			alert("Please select District.");
            return;
      }
   /*  if(assembly==0)
     {
      //$("#errorDiv1").html(" Please select Assembly ");
		alert("Please select Assembly.");
          return;
     } */
    
    if(mandal==0)
    {
      //$("#errorDiv1").html(" Please select Mandal/ Municipality ");
		alert("Please select Mandal/ Municipality.");
          return;
    }
	$("#locationLevelValhidden").val(mandal);
		if(mandalName.indexOf('Mandal') == -1)
			$("#locationLevelIdhidden").val(7);
		else
			$("#locationLevelIdhidden").val(5);		
  }
  if(level==6)
  {
	 var panchayatName = $("#referpanchayatId option:selected").text();
    if(state==0)
      {
       // $("#errorDiv1").html(" Please select state ");
			alert(" Please select state");
            return;
      }
    if(district==0)
      {
       // $("#errorDiv1").html(" Please select District ");
			alert(" Please select District");
            return;
      }
     /*  if(assembly==0)
     {
      //$("#errorDiv1").html(" Please select Assembly ");
	  alert(" Please select Assembly");
          return;
     } */
    
      if(mandal==0)
     {
      //$("#errorDiv1").html(" Please select Mandal/ Municipality ");
		alert(" Please select Mandal/ Municipality");
          return;
     }
     if(panchayat==0)
     {
		//$("#errorDiv1").html(" Please select Panchayat ");
		alert(" Please select Panchayat ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
	
	 if(level==7)
	{
		
		if(state==0)
		{
			alert(" Please select state");
				return;
		}
		else if(district==0)
		{
			alert(" Please select District");
            return;
		}
		else if(mandal==0)
		{
			alert(" Please select Mandal");
          return;
		}
		else if(panchayat == 0){
			alert("Please Select Panchayat.");
			return;
		}
		else if($("#hamletId").val() == 0){
			alert("Please Select Habitation.");
			return;
		}
		$("#locationLevelIdhidden").val(11);
		$("#locationLevelValhidden").val($("#hamletId").val());
		
	}

	if($("#alertTitleId").val().trim() == "" || $("#alertTitleId").val() == "undefined"){
		alert("Please Enter Title.");
		return;
	}else if($("#alertdescriptionId").val().trim() == "" || $("#alertdescriptionId").val() == "undefined"){
		alert("Please Enter Description.");
		return;
	}
	
	$("#creatingLdngImg").show();
	$("#createAlertId").hide();
	
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
	/* var result = (String)(myResult);
	if(result.search('SUCCESS') != -1){
		alert("Alert Creation Failed, Please Try Again.");
	}else{
		alert("Alert Created Successfully.");
		window.location.reload(true);
	} */
	
		$("#createAlertId").show();
		if(uploadResult.indexOf("success") !=-1)
		{
		 $("#successmsg").html("Alert Created And Assigned Successfully ").css("color","green");	
		 $("#creatingLdngImg").hide();
		 setTimeout(function(){ 
			$("#successmsg").html("");
			clearFields();
			window.location.reload(true);
		 }, 1000);
		}else{  
			$("#successmsg").html("Exception Occured..Try Again...").css("color","red");	
			$("#creatingLdngImg").hide();
			setTimeout(function(){ 
				$("#successmsg").html("");
			}, 1000);
		} 
	
}

getAllDepartments();
function getAllDepartments(){
	$("#departmentsId,#departmentsNewId").html("");
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
		$("#departmentsId,#departmentsNewId").html(str);
		$("#departmentsId,#departmentsNewId").trigger('chosen:updated');
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

function showDashboard(){	
		$("#dateDivId").show();		
		$("#departmentsNewId").val(0);
		$("#departmentsNewId").trigger('chosen:updated');		
		
		getSocialAlertDetails();
		getSocialAlertFeedbackDetails();
		$('#dashboardGrevanceDivId').show();
		$('#newGrevanceDivId').hide();
	}
	
function getSocialAlertDetails(){
	    $('#alertDataDivId').html('');
		$('#summaryDiv').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  var deptId = $("#departmentsNewId").val();
		var jsObj ={
			startDate:fromDateStr,//'dd/MM/YYYY',
			endDate:toDateStr,
			mobileNo:'',
			status:"",
			deptId:deptId,
			task:""
		}
		$.ajax({
			type:'GET',
			url: 'getSocialAlertCallerDetailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				$('#summaryDiv').html('');
				//buildGrievanceSummary(result);
				buildSocialAlertDetails(result);
			}	  
		});
	}
	
	function buildGrievanceSummary(result){
		if(result != null && result.length>0){
			var str='';
			var total = 0;
			if(result[0].statusList != null && result[0].statusList.length>0) {
				str+='<div style="background-color: lightgrey; font-weight: bold; margin-top: 55px; margin-bottom: 5px; border-radius: 5px; text-align: center; text-transform: uppercase; font-size: 15px;">  Grievance Details </div>';
				str+='<div class="table-responsive">';
				str+='<table  class="table table-bordered "  style="text-align:center;" id="tabDetails">';
					str+='<thead>';
						str+='<th  style="text-align:center"> Total  </th>';
						var totalCount =0;
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId)
							str+='<th  style="text-align:center" > '+result[0].statusList[0].statusList[k].status+' </th>';
						}
						//str+='<th  style="text-align:center">TOTAL COUNT </th>';
					
					str+='</thead>';
					str+='<tbody>';
						
						//str+='<th  style="text-align:center" > TOTAL </th>';
						
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId ){
								if(result[0].statusList[0].statusList[k].totalCount != null && parseInt(result[0].statusList[0].statusList[k].totalCount)>0){
									total = total+result[0].statusList[0].statusList[k].totalCount;
								}
							}
						}
						
						
						
						str+='<td  style="text-align:center" ><a class="alertDetailsCls" attr_statusId ="0" attr_status="0" attr_feedbackSId="0" style="color:green;font-weight:bold;" href="javascript:{getAlertDetails(0,0,0)}"> '+total+'</a></td>';
						
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId ){
								if(result[0].statusList[0].statusList[k].totalCount != null && parseInt(result[0].statusList[0].statusList[k].totalCount)>0){
									total = total+result[0].statusList[0].statusList[k].totalCount;
									//str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" class="alertDetailsCls" attr_statusId = '+result[0].statusList[0].statusList[k].alertStatusId+' attr_status="'+result[0].statusList[0].statusList[k].status+'" attr_feedbackSId='0' href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].totalCount+' </a> </td>';
									str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" class="alertDetailsCls" attr_statusId = '+result[0].statusList[0].statusList[k].alertStatusId+' attr_status="'+result[0].statusList[0].statusList[k].status+'" attr_feedbackSId="0" href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].totalCount+' </a> </td>';
								}
								else if(result[0].statusList[0].statusList[k].count != null && parseInt(result[0].statusList[0].statusList[k].count)>0){
									str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" class="alertDetailsCls" attr_statusId = '+result[0].statusList[0].statusList[k].alertStatusId+' attr_status="'+result[0].statusList[0].statusList[k].status+'" attr_feedbackSId="0" href="javascript:{getAlertDetails('+result[0].statusList[0].statusList[k].alertStatusId+',\''+result[0].statusList[0].statusList[k].status+'\',0)}"> '+result[0].statusList[0].statusList[k].count+' </a> </td>';
								}
								else{
									str+='<td  style="text-align:center" >  -  </td>';
								}
								}
							}
							//str+='<td  style="text-align:center" ><a class="alertDetailsCls" attr_statusId ="0" attr_status="0" attr_feedbackSId="0" style="color:green;font-weight:bold;" href="javascript:{getAlertDetails(0,0,0)}"> '+total+'</a></td>';
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
				str+='</div>';
			}
			
			$('#summaryDiv').html(str);
			
		}
	}
	
	function buildSocialAlertDetails(result){
		if(result != null && result.length>0){
			var str='';
			var total = 0;
			if(result[0].statusList != null && result[0].statusList.length>0) {
				str+='<div style="background-color: lightgrey; font-weight: bold; margin-top: 55px; margin-bottom: 5px; border-radius: 5px; text-align: center; text-transform: uppercase; font-size: 15px;">  Grievance Details </div>';
				str+='<div class="table-responsive">';
				str+='<table  class="table table-bordered "  style="text-align:center;" id="tabDetails">';
					str+='<thead>';
						str+='<th style="text-align:center">   </th>';
						str+='<th  style="text-align:center"> Total  </th>';
						var totalCount =0;
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId)
							str+='<th  style="text-align:center" > '+result[0].statusList[0].statusList[k].status+' </th>';
						}
					
					str+='</thead>';
					str+='<tbody>';
						
						for(var i in result[0].statusList){
							if(result[0].statusList[i].statusList !=null && result[0].statusList[i].statusList.length>0){															
								//Total Of SocialMediaType
								var totalCount  = 0;
								for(var j in result[0].statusList[i].statusList){
									if(result[0].statusList[i].statusList[j].count !=null){
										totalCount = totalCount + result[0].statusList[i].statusList[j].count;
									}
								}
								
								str+='<tr>';
									str+='<td>'+result[0].statusList[i].status+'</td>';
									
									if(totalCount !=null && totalCount>0){
										str+='<td  style="text-align:center" ><a class="alertDetailsCls" attr_statusId ="0" attr_status="0" attr_feedbackSId="0" attr_smTypeId='+result[0].statusList[i].alertStatusId+' style="color:green;font-weight:bold;" href="javascript:{getAlertDetails(0,0,0,\''+result[0].statusList[i].alertStatusId+'\',\'status\')}"> '+totalCount+'</a></td>';
									}else{
										str+='<td> - </td>';
									}
									
									
									
									for(var j in result[0].statusList[i].statusList){
										if(result[0].statusList[i].statusList[j].count !=null){
											str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" class="alertDetailsCls" attr_statusId = '+result[0].statusList[i].statusList[j].alertStatusId+' attr_status="'+result[0].statusList[i].statusList[j].status+'" attr_feedbackSId="0" attr_smTypeId='+result[0].statusList[i].alertStatusId+' href="javascript:{getAlertDetails('+result[0].statusList[i].statusList[j].alertStatusId+',\''+result[0].statusList[i].statusList[j].status+'\',0,\''+result[0].statusList[i].alertStatusId+'\',\'status\')}"> '+result[0].statusList[i].statusList[j].count+' </a> </td>';
										}else{
											str+='<td> - </td>';
										}										
									}
								str+='</tr>';								
							}
						}
																
					str+='</tbody>';
				str+='</table>';
				str+='</div>';
			}
			
			$('#summaryDiv').html(str);
			
		}
	}
	
	
	var globalAlertStatusId=0;
	var globalAlertStatus=0;
	$(document).on("change",".alertDetailsCls",function(){
		
		var statusId = $(this).attr("attr_statusId");
		var status = $(this).attr("attr_status");
		var feedbackStatusId = $(this).attr("attr_feedbackSId");
		
		globalAlertStatusId = statusId;
		globalAlertStatus = status;
		
	}); 
	
	/* $(document).on("change","#verificationStatusId",function(){
		getAlertDetails(globalAlertStatusId,globalAlertStatus,0);
	}); */
	
	function getAlertDetails(alertStatusId,status,feedbackStattusId,smTypeId,buildType)
	{		
		/* if(alertStatusId !=null && alertStatusId == 14){
			$("#verificationStatusMainDivId").show();
		}else{
			$("#verificationStatusMainDivId").hide();
			$("#verificationStatusId").val(0);
			$("#verificationStatusId").trigger('chosen:updated');
		} */
		
	
		$('#alertDataDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		 var dateArray = dates.split("-");
		 if(dateArray != null){
			 fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  var deptId  = $("#departmentsNewId").val();
		var jObj = {
			alertStatusId : alertStatusId,
			mobileNo : '',
			fromDate :fromDateStr,//"12/04/2016",
			toDate : toDateStr,//"12/04/2017"
			feedbackStatusId:feedbackStattusId,
			deptId :deptId,
			categoryId:5,
			smTypeId:smTypeId
		}
		$.ajax({
		  type:'GET',
		  url: 'getSocialAlertDetailsByStatusAction.action',//getAlertDetailsByStatusAction
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertDetails(result,status,alertStatusId,buildType);
			}
		});
}
function buildAlertDetails(result,status,alertStatusId,buildType){
	var str = '';
	if(status == 0)
		status = "Total";
		str+='<div style="background-color: lightgrey; font-weight: bold; margin-top: 5px; margin-bottom: 5px; border-radius: 5px; text-align: center; text-transform: uppercase; font-size: 15px;">  '+status+' Status Grievance Details </div>';
	//str+='<div class="table-responsive">';	
	str+='<table class="table table-bordered tableStyleAlign table-condensed" style="text-align:center;" id="tabbDetails" >';
		str+='<thead>';
			str+='<th  style="text-align:center">TITLE</th>';
			str+='<th  style="text-align:center">DEPARTMENT</th>';			
			str+='<th  style="text-align:center">SOCIAL MEDIA TYPE</th>';
			str+='<th  style="text-align:center">IMPACT LEVEL</th>';
			str+='<th  style="text-align:center">LOCATION</th>';
			str+='<th  style="text-align:center"> CREATED ON</th>'; 
			str+='<th  style="text-align:center"> CALLER DETAILS</th>'; 
			str+='<th  style="text-align:center"> CREATED BY</th>'; 
			str+='<th  style="text-align:center"> FEEDBACK STATUS  </th>'; 
			str+='<th  style="text-align:center"> VERIFIED STATUS  </th>'; 
			if(buildType !=null && buildType == "status"){
				if(status != 0){
					if(alertStatusId !=null && alertStatusId>0 && alertStatusId == 14 || alertStatusId == 4 || alertStatusId == 12){
						str+='<th  style="text-align:center"> UPDATE STATUS  </th>'; 
					}				
				}
			}
			
				
		str+='</thead>';
		str+='<tbody>';
		for( var i in result){
			var locationName ="";
			if(result[i].district != null && result[i].district.length>0)
				locationName = locationName+" "+result[i].district+" District,<br> ";
			if(result[i].constituency != null && result[i].constituency.length>0)
				locationName = locationName+" "+result[i].constituency+" Assembly ,<br> ";
			
			if(result[i].tehsil != null){
				if(result[i].tehsil != null && result[i].tehsil.length>0)
					locationName = locationName+" "+result[i].tehsil+" Mandal,<br> ";
				if(result[i].panchayat != null && result[i].panchayat.length>0)
					locationName = locationName+" "+result[i].panchayat+" Panchayat,<br> ";
				if(result[i].hamlet != null && result[i].hamlet.length>0)
					locationName = locationName+" "+result[i].hamlet+" Hamlet , <br>";
			}
			else{
				if(result[i].leb != null && result[i].leb.length>0)
					locationName = locationName+" "+result[i].leb+" Munci/Corp/Greater City, <br>";
				if(result[i].ward != null && result[i].ward.length>0)
					locationName = locationName+" "+result[i].ward+"  ";
			}
			
			str+='<tr>';
				str+='<td>'+result[i].title+'</td>';
				str+='<td>'+result[i].deptName+'</td>';
				str+='<td>'+result[i].smType+'</td>';
				str+='<td>'+result[i].locationName+'</td>';
				str+='<td style="">'+locationName+'</td>';
				str+='<td>'+result[i].createdTime+'</td>';
				str+='<td>'+result[i].name+'<br><i class="glyphicon glyphicon-phone"></i>:'+result[i].mobileNo+'</td>';
				str+='<td>'+result[i].userName+'</td>';
				str+='<td>';
				if(result[i].feedbackStatus != null){
					str+=' '+result[i].feedbackStatus+' <br>';
				}
			str+='</td>';
			str+='<td>';
			if(result[i].verifyStatus !=null && result[i].verifyStatus.length>0){	
				if(result[i].verifyStatus == "Y")
					str+=' Approved ';
				else
					str+=' Rejected ';
			}
			str+='</td>';
			
			
			if(buildType !=null && buildType == "status"){
				if(status != 0){
					
					if(alertStatusId !=null && alertStatusId>0 && alertStatusId == 14){
						str+='<td>';
							str+='<button class="btn btn-success updateAlertVerifyCls btn-xs btn-mini m_top10" attr_alert_id ="'+result[i].alertId+'" attr_alert_source_id ="'+result[i].alertSourceId+'" attr_alert_status_id ="'+result[i].statusId+'">Verify</button>';
						str+='</td>';
					}else if(alertStatusId !=null && alertStatusId>0 && alertStatusId == 4 || alertStatusId == 12){
						str+='<td>';
							str+='<button class="btn btn-success updateAlertNewCls btn-xs btn-mini m_top10" attr_alert_id ="'+result[i].alertId+'" attr_alert_source_id ="'+result[i].alertSourceId+'" attr_alert_status_id ="'+result[i].statusId+'">Update</button>';
						str+='</td>';
					}
					
				}
			}
			
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		//str+='</div>';
		$("#alertDataDivId").html(str);
		$('#tabbDetails').dataTable({
				"iDisplayLength": 20,
				"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			});
		$('#tabbDetails').removeClass("dataTable")
		
}

var globalChangeStatusType="";
$(document).on("click",".updateAlertNewCls",function(){ 

	globalChangeStatusType = "feedback" ;
	getFeedBackStatusDetails();
	
	$("#reOpenSpanId").hide();

	var alertId = $(this).attr("attr_alert_id");
	var sourceId =$(this).attr("attr_alert_source_id");
	var statusId =$(this).attr("attr_alert_status_id");
	getAlertCallerDetails(alertId);
	$("#hiddenAlertId").val(alertId);
	$("#hiddenSourceId").val(sourceId);
	$("#hiddenStatusId").val(statusId);
	
	$("#updateAlertFooterSpanId").html('<button type="button" attr_statusId='+statusId+' class="btn btn-primary updateAlertFooterCls" >Save</button>');
	
	
});	
$(document).on("click",".updateAlertVerifyCls",function(){ 

	globalChangeStatusType = "vefify" ;
	buildVerifiedStatusDetails();
	
	$("#reOpenSpanId").hide();
	
	var alertId = $(this).attr("attr_alert_id");
	var sourceId =$(this).attr("attr_alert_source_id");
	var statusId =$(this).attr("attr_alert_status_id");
	getAlertCallerDetails(alertId);
	$("#hiddenAlertId").val(alertId);
	$("#hiddenSourceId").val(sourceId);
	$("#hiddenStatusId").val(statusId);
	
	$("#updateAlertFooterSpanId").html('<button type="button" attr_statusId='+statusId+' class="btn btn-primary updateAlertFooterCls" >Save</button>');
	
});	

function getAlertCallerDetails(alertId)
	{
		$("#comntId").val('');
		$("#saveMsgId").html('');
		$("#feedbackStatusList").val(0);
		$("#updateAlertModalDivId").modal('show');
		var jObj = {
			alertId : alertId
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertCallerDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null){
				buildAlertCallerDetails(result);
			}
		});
	}
function buildAlertCallerDetails(result){
	var str= '';
			
				for(var i in result){
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Given By : </span> <span>'+result[i].name+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Mobile No : </span> <span>'+result[i].mobileNo+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					str+='<p><span>Created On : </span> <span>'+result[i].date1+'</span></p>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>Title : </span> <span>'+result[i].title+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h5><span>description : </span> <span>'+result[i].desc+'</span></h5>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12  m_top10">';
					str+='<label>Comment<span style="color:red">*</span></label><span id="commentErrMsgId" style="color:red"></span><br>';
					str+='<textarea id="comntId" rows="3" style="width: 799px;"></textarea>';
					str+='</div>';
				}			 		
		
$("#buildUpdateDivId").html(str);
}


function getFeedBackStatusDetails()
	{
		$('#feedbackStatusList').empty().trigger('chosen:updated');
		$("#feedbackLabelId").html('');
		
		var jObj = {
			}
		$.ajax({
		  type:'GET',
		  url: 'getAlertFeedBackStatusDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			var str='';
			$("#feedbackLabelId").html("Feedback Status : ");
			if(result != null)
			{
				str+='<option value="0">Select Status</option>';
				for(var i in result){
					str+='<option value="'+result[i].id+'">'+result[i].status+'</option>';
				}
				$('#feedbackStatusList').html(str);
					
			}
		});
}
function buildVerifiedStatusDetails()
	{
		$("#feedbackLabelId").html("Verified Status : ");
		$('#feedbackStatusList').empty().trigger('chosen:updated');;
		$('#feedbackStatusList').append('<option value="0">Select Status</option><option value="Y">Approved</option><option value="N">Rejected</option>');
	}

$(document).on('click','.updateAlertFooterCls',function(){
	var statusId = $(this).attr("attr_statusId"); 
	if(statusId !=null && statusId>0 && statusId ==4 || statusId ==12){	
		saveAlertStatusDetails();
	}else if(statusId !=null && statusId>0 && statusId ==14){
		changeVeificationStatusDetails();
	}
});
	
function saveAlertStatusDetails()
	{
		var comment = $("#comntId").val();
		var feedBackStatus =$("#feedbackStatusList").val();
		var alertId = $("#hiddenAlertId").val();
		var sourceId = $("#hiddenSourceId").val();
		var statusId = $("#hiddenStatusId").val();	
	  var newAlertStatusId = $('#reopenCheckboxId').prop('checked') ? $('#reopenCheckboxId').val() : 0;
		
		$("#commentErrMsgId").html('');
		$("#statusErrMsgId").html('');
		
		if(comment == null || comment.trim().length == 0 || comment == "undefined" ){
			$("#commentErrMsgId").html("Please Enter Comment");
			return;
		}
		if(feedBackStatus == null || feedBackStatus == 0 || feedBackStatus == "undefined" ){
			$("#statusErrMsgId").html("Please Select Anyone ");
			return;
		}
				
		var jObj = {
			alertId : alertId,
			comment : comment,
			alertStatusId : statusId,
			alertFeedBackStatusId : feedBackStatus,
			alertSourceId : sourceId,
			newAlertStatusId:newAlertStatusId
		}
		$.ajax({
		  type:'GET',
		  url: 'saveAlertFeedbackStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result == "success"){
				showDashboard();
				$("#saveMsgId").html("<span style='color:green;'>Updated Successfully..</span>");
			}else{
				$("#saveMsgId").html("<span style='color:green;'>Updation failed.Please try again.</span>");
			}
			setTimeout(function(){ 
				 $("#updateAlertModalDivId").modal('hide');
			},1500);
		});
}
function changeVeificationStatusDetails(){
		var alertId = $("#hiddenAlertId").val();
		var statusId = $("#hiddenStatusId").val();
		var comment = $("#comntId").val();
		var verifyStatus =$("#feedbackStatusList").val();
		
		$("#commentErrMsgId").html('');
		$("#statusErrMsgId").html('');
		
		if(comment == null || comment.trim().length == 0 || comment == "undefined" ){
			$("#commentErrMsgId").html("Please Enter Comment");
			return;
		}
		if(verifyStatus == null || verifyStatus == 0 || verifyStatus == "undefined" ){
			$("#statusErrMsgId").html("Please Select Anyone ");
			return;
		}		
		var jObj = {
			alertId : alertId,
			comment : comment,
			alertStatusId : 1,
			verifiedStatus:verifyStatus
		}
		$.ajax({
		  type:'GET',
		  url: 'changeVeificationStatusDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result == "success"){
				showDashboard();
				$("#saveMsgId").html("<span style='color:green;'>Updated Successfully..</span>");
			}else{
				$("#saveMsgId").html("<span style='color:green;'>Updation failed.Please try again.</span>");
			}
			setTimeout(function(){ 
				 $("#updateAlertModalDivId").modal('hide');
			},1500);
		});
}
function clearFields(){
	$("#alertlevelId").val(0).trigger('chosen:updated');
	$("#departmentsId").val(0).trigger('chosen:updated');
	$("#socialMediaTypeId").val(0).trigger('chosen:updated');
	$("#telugu").prop("checked", true);
	
	$("#alertTitleId","#alertdescriptionId","#nameId,#addressId,#mobileNoId,#accountId,#emailId,#uploadFileId0").val('');
}
$(document).on("click",".switch-btn li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
});
	function getSocialAlertFeedbackDetails(){
	    $('#alertDataDivId').html('');
		$('#feedbackSummaryDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  var deptId = $("#departmentsNewId").val();
		var jsObj ={
			startDate:fromDateStr,//'dd/MM/YYYY',
			endDate:toDateStr,
			mobileNo:'',
			deptId:deptId,
			task:""
		}
		$.ajax({
			type:'GET',
			url: 'getSocialAlertFeedBackDetailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			 if(result != null){
				$('#feedbackSummaryDivId').html('');
				buildSocialAlertFeedBackDetails(result);
			}  
		});
	}
	
	function buildSocialAlertFeedBackDetails(result){
		if(result != null && result.length>0){
			var str='';
			var total = 0;
			if(result[0].statusList != null && result[0].statusList.length>0) {
				str+='<div style="background-color: lightgrey; font-weight: bold; margin-top: 55px; margin-bottom: 5px; border-radius: 5px; text-align: center; text-transform: uppercase; font-size: 15px;">  Feedback Details </div>';
				str+='<div class="table-responsive">';
				str+='<table  class="table table-bordered "  style="text-align:center;" id="tabDetails">';
					str+='<thead>';
						str+='<th style="text-align:center">   </th>';
						str+='<th  style="text-align:center"> Total  </th>';
						var totalCount =0;
						for(var k in result[0].statusList[0].statusList){
							if(result[0].statusList[0].statusList[k].alertStatusId)
							str+='<th  style="text-align:center" > '+result[0].statusList[0].statusList[k].status+' </th>';
						}
					
					str+='</thead>';
					str+='<tbody>';
						
						for(var i in result[0].statusList){
							if(result[0].statusList[i].statusList !=null && result[0].statusList[i].statusList.length>0){															
								//Total Of SocialMediaType
								var totalCount  = 0;
								for(var j in result[0].statusList[i].statusList){
									if(result[0].statusList[i].statusList[j].count !=null){
										totalCount = totalCount + result[0].statusList[i].statusList[j].count;
									}
								}
								
								str+='<tr>';
									str+='<td>'+result[0].statusList[i].status+'</td>';
									if(totalCount !=null && totalCount>0){
										str+='<td  style="text-align:center" ><a class="alertDetailsCls" attr_statusId ="0" attr_status="0" attr_feedbackSId="0" attr_smTypeId='+result[0].statusList[i].alertStatusId+' style="color:green;font-weight:bold;" href="javascript:{getAlertDetails(0,0,0,\''+result[0].statusList[i].alertStatusId+'\',\'feedback\')}"> '+totalCount+'</a></td>';
									}else{
										str+='<td> - </td>';
									}
									
									for(var j in result[0].statusList[i].statusList){
										if(result[0].statusList[i].statusList[j].count !=null){
											str+='<td  style="text-align:center" > <a style="color:green;font-weight:bold;" class="alertDetailsCls" attr_statusId = "0" attr_status="'+result[0].statusList[i].statusList[j].status+'" attr_feedbackSId="'+result[0].statusList[i].statusList[j].alertStatusId+'" attr_smTypeId='+result[0].statusList[i].alertStatusId+' href="javascript:{getAlertDetails(0,\''+result[0].statusList[i].statusList[j].status+'\',\''+result[0].statusList[i].statusList[j].alertStatusId+'\',\''+result[0].statusList[i].alertStatusId+'\',\'feedback\')}"> '+result[0].statusList[i].statusList[j].count+' </a> </td>';
										}else{
											str+='<td> - </td>';
										}										
									}
								str+='</tr>';								
							}
						}
																
					str+='</tbody>';
				str+='</table>';
				str+='</div>';
			}
			
			$('#feedbackSummaryDivId').html(str);
			
		}
	}
	
	$(document).on("change","#feedbackStatusList",function(){
		var feedbackId = $("#feedbackStatusList").val();
		
		$('#reopenCheckboxId').attr('checked', false);
		
		if(globalChangeStatusType !=null && globalChangeStatusType.trim().length>0 &&
		globalChangeStatusType == "feedback" && feedbackId ==2 || feedbackId == 3){
			$("#reOpenSpanId").show();
		}else{
			$("#reOpenSpanId").hide();
		}
	});
	
</script>
</body>
</html>