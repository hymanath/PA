<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> GOVT GRIEVANCE </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
	
	
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
        	<div class="panel panel-default">
            	<div class="panel-heading bg_cc"   style="height:50px;" >
                	<h4 class="panel-title" >GOVERNMENT GRIEVANCE 
						<button class="btn btn-success pull-right" onclick="showDashboard();"> Dashboard </button>
						<button class="btn btn-primary  pull-right" style="margin-right:5px"  onclick="showNewGrievance();" > + New Grievance </button> 
					</h4>
					
                </div>
				<div class="col-md-4 col-xs-12 col-sm-6 pull-right" id="dateDivId" style="display:none;margin-bottom:10px">
				<span class="input-group pull-right dateRangePickerClsForEvents m_top10" expand-block-date="events" style="width:200px;">
						<input type="text" id="dateRangeIdForEvents" style="width:180px" class="form-control" onChange="getGrievanceSummary()"/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</span>
				</div>
				<div class="" id="newGrevanceDivId">
					<form id="saveGrievanceAlertForm" name="saveGrievanceAlertForm" enctype="multipart/form-data" method="POST">
						<div class="panel-body bg_EF">
							<div class="row">
								<div class="col-sm-3 m_top10" >
									<label>Complaint Given By<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgCallerTypeId"></span></label>
									<select class="chosen" id="callerTypeId" disabled>
										<option value="1">CITIZEN</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Grievance Related To<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgIssueTypeId"></span></label>
									<select class="chosen" id="issueTypeId" disabled>
										<option value="1">Drinking Water</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Grievance Entry Category<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgEntrySourceId"></span></label>
									<select class="chosen" id="entrySourceId" disabled>
										<option value="1">Toll Free Number</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDeptId"></span></label>
									<select class="chosenSelect" id="departmentsId" disabled>  
										<option value="49">RWS</option>
									</select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">caller details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Mobile No<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgMobileNoId"></span></label>
									<input type="text" id="mobileNoId" class="form-control" name="grievanceAlertVO.mobileNo"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgNameId"></span></label>
									<input type="text" id="nameId" class="form-control" name="grievanceAlertVO.name"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Email ID<!--<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgEmailId"></span>--></label>
									<input type="text" id="emailId" class="form-control" placeholder="abc@xyz.com" name="grievanceAlertVO.emailId"/>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Address<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgAddressId"></span></label>
									<input type="text" id="addressId" class="form-control" name="grievanceAlertVO.address"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12">
									<h4 class="text-success text-capital m_top10">location details</h4>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Grievance Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLevelId"></span></label>
									<select class="chosen" id="alertlevelId" onchange="disableByGrievLevel();" disabled>
										<!--<option value="0">Select Location Level</option>
										<option value="1">Central</option>
										<option value="2">State</option>
										<option value="3">District</option>
										<option value="4">Constituency</option>
										<option value="5">Mandal/Muncipality</option>
										<option value="6">Village/Ward</option>-->
										<option value="7" selected>Habitation</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="stateDiv">
									<label>State<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgStateId"></span></label>
									<select class="chosen" id="stateId" onChange="getDistrictsForReferPopup();" name="grievanceAlertVO.stateId">
										<!--<option value="0">Select State</option>-->
										<option value="1" selected>Andhra Pradesh</option>
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
										<option value="0">Select Mandaly</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
									<label>Village<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgVillageId"></span></label>
									<select class="chosen" id="referpanchayatId" onchange="getHamletss();" name="grievanceAlertVO.panchayatId">
										<option value="0">Select Village</option>
									</select>
								</div>
								<div class="col-sm-3 m_top10" id="villageDiv" style="display:block;">
									<label>Habitation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgHamletId"></span></label>
									<select class="chosen" id="hamletId" name="grievanceAlertVO.hamletId">
										<option value="0">Select Habitation</option>
									</select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">grievance details</h4>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-8 m_top10">
									<label>Grievance Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgTitleId"></span></label>
									<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
								</div>
								<!--<div class="col-sm-4 m_top10">
									<label>Complaint No</label>
									<input type="text" class="form-control" id="complaintNo" name="grievanceAlertVO.complaintNo"/>
								</div>-->
								<div class="col-sm-12 m_top10">
									<label>Grievance Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDescId"></span></label>
									<label class="radio-inline">
										<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
									</label>
									<label class="radio-inline">
										<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 m_top10">
									<textarea class="form-control" id="alertdescriptionId" name="grievanceAlertVO.description"></textarea>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">assign alert to department officer</h4>
								</div>
								  
								<div class="col-sm-3 m_top10">
									<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLvlId"></span></label>
									<select  class="chosenSelect" id="locationLevelSelectId" disabled>  
										<option></option>
									</select>
								</div>
								<div id="parentLevelDivId"> </div>

								<div class="col-sm-3 m_top10">
									<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDesgId"></span></label>
									<select name="grievanceAlertVO.designationId" id="designationsId" class="chosenSelect">
										<option></option>  
									</select>
								</div>
								<div class="col-sm-3 m_top10">
									<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgOffcrId"></span></label>
									<select name="grievanceAlertVO.govtOfficerId" id="officerNamesId" class="chosenSelect">
										<option></option>
									</select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 m_top10">
									<h4 class="text-success text-capital">upload document linking to this alert</h4>
									<div class="block  m_top10">
										<input type="file" id="uploadFileId0" name="imageForDisplay"/>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-4 m_top25">
									<input type="button" class="btn btn-success btn-block text-capital" value="create grievance request" onclick="createGrievanceAlert();"></input>
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
						
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.callerTypeId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.issueTypeId"/>
						<input type="hidden" class="form-control" value="1" name="grievanceAlertVO.entrySourceId"/>
						<input type="hidden" class="form-control" value="49" name="grievanceAlertVO.departmentId"/>
						<input type="hidden" class="form-control" value="8" name="grievanceAlertVO.levelId"/>
					</form>
				</div>
				<div id="dashboardGrevanceDivId" style="display:none;">
					<div id="summaryDiv"></div>
					<div id="alertDataDivId"></div>
				<div>
            </div>
        </div>
    </div>
</div>

<!-- PopUp -->
<div class="modal fade" id="updateAlertModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">FeedbackStatus Updation</h4>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
       <div id="buildUpdateDivId"></div>
	   <div class="row">
		   <div class="col-sm-4">
			   <label>Comment</label>
			   <textarea id="comntId" style="width:350px; height:90px;"></textarea>
		   </div>
		    <div class="col-sm-3">
			   <label>FeedBack Status :</label>
			  <select id="feedbackStatusList" class="form-control">
				<option value="0"> Select FeedbackStatus</option>
			  </select>
		   </div>
		   <div class="col-sm-4">
				<button type="button" class="btn btn-success" onClick="saveAlertStatusDetails();" style="margin-top:22px;">Save</button>
				<span id="saveMsgId"></span>
		   </div>
	   </div>
      </div>
    </div>
  </div>
</div>

<!-- Hidden Variables -->
<input type="hidden" id="hiddenAlertId"></input>
<input type="hidden" id="hiddenSourceId"></input>
<input type="hidden" id="hiddenStatusId"></input>


<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script>

$(".chosen").chosen({
	width : '100%'
});

// Load the Google Transliterate API
google.load("elements", "1", {
	packages: "transliteration"
});

//getAlertIssueTypes();
//getAlertCallerTypes();
$("#dateRangeIdForEvents").daterangepicker({
	opens: 'left',
	startDate: moment().subtract(1, 'month').startOf('month'),
	endDate: moment().subtract(1, 'month').endOf('month'),
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});
function getAlertType(){
	$("#alertTypeId").html('');
	var jsObj ={
		task:""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertTypeAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$('#alertTypeId').append('<option value="0"> Select Alert Type </option>');
		if(result != null){
			for(var i in result){			
				$('#alertTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#alertTypeId").trigger('chosen:updated');	  
	});
}

function getAlertImpactScope(){
	$("#alertImpactId").html('');
	var jsObj ={
		task:""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertImpactScopeAction.action',
		data: {task :JSON.stringify(jsObj)}
   }).done(function(result){
		$('#alertImpactId').append('<option value="0"> Select Alert Impact </option>');
		if(result != null){
			for(var i in result){			
				$('#alertImpactId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#alertImpactId").trigger('chosen:updated');
	});
}

function getAlertsource(){
	$("#alertSourceId").html('');
	var jsObj ={
		userId : loginUserId,
		task:""
	}
	$.ajax({
		type:'GET',
		url: 'getAlertSourceForUserAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		$('#alertSourceId').append('<option value="0"> Select Alert Source </option>');
		if(result != null){
			for(var i in result){			
				$('#alertSourceId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#alertSourceId").trigger('chosen:updated');	  
	});
}

getDistrictsForReferPopup();
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
				if(result[i].id > 0)
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$("#referdistrictId").html(str);
			$("#referdistrictId").trigger('chosen:updated');
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village</option>');
			$("#referpanchayatId").trigger('chosen:updated');
			$("#hamletId").html('<option value="0">Select Habitation</option>');
			$("#hamletId").trigger('chosen:updated');
		}else{
			//for dist
			$("#referdistrictId").html('<option value="0">Select District</option>');
			$("#referdistrictId").trigger('chosen:updated');     
			//for const
			//$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			//$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Village</option>');
			$("#referpanchayatId").trigger('chosen:updated');  
			$("#hamletId").html('<option value="0">Select Habitation</option>');
			$("#hamletId").trigger('chosen:updated');
		} 
	});  
  }
 /*function getConstituenciesBydistrictForReferPopup(){
	 var districtId = $("#referdistrictId").val();
	 $("#designationId").empty();
	 $("#designationId").trigger('chosen:updated');
	 $("#officerId").empty();
	 $("#officerId").trigger('chosen:updated');
	 $("#villageDiv").hide();
	var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var constiStr='';
			if(result != null && result.length > 0){
				constiStr +='<option value="0">Select Assembly</option>';
				for(var i in result){
					if(result[i].id > 0)
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#referconstituencyId").html(constiStr);
				$("#referconstituencyId").trigger('chosen:updated');
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated');           
			}else{
				//for const
				$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
				$("#referconstituencyId").trigger('chosen:updated');
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
			}
		});
 }*/

 function getMandalsByConstituencyForReferPopup(){
	 var districtId = $('#referdistrictId').val();
	 
	 var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getAllMandalsByDistrictIDAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">Select Mandal</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				$("#refermandalNameId").html(mandalStr);
				$("#refermandalNameId").trigger('chosen:updated');                
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
			}else{
				//for mandal/municipality
				$("#refermandalNameId").html('<option value="0">Select Mandal</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
			}
		});
 }
 
 function getPanchayatsForReferPopup(){
	
	 $("#referpanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId').val();
	 var  type = $("#refermandalNameId option:selected").text();
	 		   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
	  var jsObj={
				mandalId :mandalId,
				type:type,
				task:""
				
			}
	 $.ajax({
				type:"POST",
				url :"getPanchayatAndWardDetailsAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Village</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#referpanchayatId").html(panchyatStr);
					$("#referpanchayatId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#referpanchayatId").html('<option value="0">Select Village</option>');
					$("#referpanchayatId").trigger('chosen:updated'); 
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
	
	if(type == "mandal"){
		
		var jsObj={
				panchayatId :panchayatId,
				task:""
			}
	 $.ajax({
				type:"POST",
				url :"getHamletsForPanchayatAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Habitation</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#hamletId").html(panchyatStr);
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#hamletId").html('<option value="0">Select Habitation</option>');
					$("#hamletId").trigger('chosen:updated'); 
				}     
		});
	}
}

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

function getAlertIssueTypes(){
	var jsObj={
		task:""
	}
	$.ajax({
		type:"POST",
		url :"getAlertIssueTypesAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str +='<option value="0">Select Issue Type</option>';
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#issueTypeId").html(str);
			$("#issueTypeId").trigger('chosen:updated');
		}
	});
}

function disableByGrievLevel(){
	var alertLevelId = $("#alertlevelId").val();
	if(alertLevelId == 2){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").hide();
		$("#constituencyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 3){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 4){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 5){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").hide();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 6){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
		$("#villageDiv").hide();
	}
	else if(alertLevelId == 7){
		$("#stateId").val(1).trigger('chosen:updated');
		$("#referdistrictId").empty().trigger('chosen:updated');
		$("#referconstituencyId").empty().trigger('chosen:updated');
		$("#refermandalNameId").empty().trigger('chosen:updated');
		$("#referpanchayatId").empty().trigger('chosen:updated');
		$("#hamletId").empty().trigger('chosen:updated');
		
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constituencyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
		$("#villageDiv").show();
	}
}

</script>
<script type="text/javascript">

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

function createGrievanceAlert()
{
	$(".errorMsgClas").html('');
	//var validate = true;
	
  var  level=$("#alertlevelId").val();
  var  state=$("#stateId").val();
  var  district=$("#referdistrictId").val();
  //var  assembly=$("#referconstituencyId").val();
  var  mandal=$("#refermandalNameId").val();
  var  panchayat=$("#referpanchayatId").val();
  var  village = $("#hamletId").val();
  
  var title=$("#alertTitleId").val().trim();
  var description=$("#alertdescriptionId").val().trim();
  
  var callerType = $("#callerTypeId").val();
  var issueType = $("#issueTypeId").val();
  var entrySource = $("#entrySourceId").val();
  
  var name = $("#nameId").val().trim();
  var address = $("#addressId").val().trim();
  var mobileNo = $("#mobileNoId").val().trim();
  var email = $("#emailId").val().trim();
  
  var department = $("#departmentsId").val();
  var assignLevelId = $("#locationLevelSelectId").val();
  var levelValue = $(".locationCls").val();
  var designation = $("#designationsId").val();
  var officer = $("#officerNamesId").val();
  
  
  if(mobileNo.length==0 ||mobileNo==''){
		$("#errMsgMobileNoId").html(" Please enter MobileNo ");
		return;
	}
	if(mobileNo.length != 10){
		$("#errMsgMobileNoId").html(" Please enter Valid MobileNO ");
		return;
	}
	if(mobileNo.length > 0){
		var numericExpression = /^[0-9]+$/;
		if(!mobileNo.match(numericExpression)){
			$('#errMsgMobileNoId').html('Enter Numerics Only.');
			return;
		}
	}
	if(name.length==0 ||name==''){
		$("#errMsgNameId").html(" Please enter Name ");
		return;
	}
	if(address.length==0 ||address==''){
		$("#errMsgAddressId").html(" Please enter Address ");
		return;
	}
	
  
  if(level==0)
  {
     $("#errMsgLevelId").html(" Please select level ");
  }
  
  if(level==2)
  {
    if(state==0)               
      {
      $("#errMsgStateId").html(" Please select state ");
          return;
    }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      $("#errMsgStateId").html(" Please select state ");
          return;
    }
    if(district==0)
    {
      $("#errMsgDistrictId").html(" Please select District ");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        $("#errMsgStateId").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please select District ");
            return;
      }
    /*if(assembly==0)
    {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
    }
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);*/
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId1 option:selected").text();
    if(state==0)
      {
        $("#errMsgStateId").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please select District ");
            return;
      }
    /*if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
    if(mandal==0)
    {
      $("#errMsgMandalId").html(" Please select Mandal/ Municipality ");
          return;
    }
	$("#locationLevelValhidden").val(mandal);
		if(mandalName.indexOf('Mandal') == -1)
		$("#locationLevelIdhidden").val(7);
	else
		$("#locationLevelIdhidden").val(5);
		
  }
  if(level==7)
  {
	if(state==0)
      {
        $("#errMsgStateId").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please select District ");
            return;
      }
     /* if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
      if(mandal==0)
     {
      $("#errMsgMandalId").html(" Please select Mandal ");
          return;
     }
     if(panchayat==0)
     {
    $("#errMsgVillageId").html(" Please select Village ");
        return;
     }
	 if(village==0)
     {
    $("#errMsgHamletId").html(" Please select Habitation ");
        return;
     }
	 $("#locationLevelValhidden").val(village);
		 $("#locationLevelIdhidden").val(11);
    }
	if(level==6)
  {
	   var panchayatName = $("#referpanchayatId1 option:selected").text();
    if(state==0)
      {
        $("#errMsgStateId").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errMsgDistrictId").html(" Please select District ");
            return;
      }
      /*if(assembly==0)
     {
      $("#errMsgConstId").html(" Please select Assembly ");
          return;
     }*/
    
      if(mandal==0)
     {
      $("#errMsgMandalId").html(" Please select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errMsgVillageId").html(" Please select Panchayat ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
	
	 if(title.length==0 || title=='')
	  {
		$("#errMsgTitleId").html(" Please enter Alert Title.  ");
			return;
	  }
  
	if(description.length==0 ||description==''){
		$("#errMsgDescId").html(" Please enter description ");
		return;
	}
	
	if(assignLevelId == 0){
		$("#errMsgLvlId").html(" Select Level ");
		return;
	}
	if(levelValue == 0){
		$("#errMsgLocationId").html(" Select Location ");
		return;
	}
	if(designation == 0){
		$("#errMsgDesgId").html(" Select Designation ");
		return;
	}
	if(officer == 0){
		$("#errMsgOffcrId").html(" Select Officer ");
		return;
	}
	
	if(callerType == 0){
		$("#errMsgCallerTypeId").html(" Select Caller Type ");
		return;
	}
	if(issueType == 0){
		$("#errMsgIssueTypeId").html(" Select Issue Type ");
		return;
	}
	if(entrySource == 0){
		$("#errMsgEntrySourceId").html(" Select Entry Category ");
		return;
	}
	
  if(department == 0){
		$("#errMsgDeptId").html(" Select Department ");
		return;
	}
	
	
	//if(validate){
		$("#creatingLdngImg").show();
		
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				
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
		
		YAHOO.util.Connect.setForm('saveGrievanceAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveGrievanceAlertAction.action',uploadHandler);
	//}
	
}

function clearFields(){
	$("#alertlevelId").val(0).trigger('chosen:updated');
	$("#departmentsId").val(1).trigger('chosen:updated');
	$("#entrySourceId").val(0).trigger('chosen:updated');
	$("#telugu").prop("checked", true);
	
	$("#alertTitleId").val('');
	$("#alertdescriptionId").val('');
	$("#nameId").val('');
	$("#addressId").val('');
	$("#mobileNoId").val('');
	$("#emailId").val('');
	$("#uploadFileId0").val('');
}
getDepartmentLevels();
	var loginUserId = "${sessionScope.USER.registrationID}";
	/* Assign */
	$(".chosenSelect").chosen({width:'100%'})
	$(document).on('change', '#locationLevelSelectId', function(){
		getParentLevelsOfLevel();
	});
	$(document).on('change','.locationCls', function(evt, params) {
		designationsByDepartment();
	});
	$(document).on('change', '#departmentsId', function(){
		getDepartmentLevels();
	});
	$(document).on('change','#designationsId', function(evt, params) {
		var designationId = $(this).val();
		officersByDesignationAndLevel(designationId)
	});
	function getDepartmentLevels(){
		
		var jsObj = {
			departmentId : 49
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
		
		$("#locationLevelSelectId").val(8);
		$("#locationLevelSelectId").trigger("chosen:updated");
		setTimeout(function(){
			getParentLevelsOfLevel();
		},1000);
	}


	function getParentLevelsOfLevel(){
		departmentId = 49;
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
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLocationId"></span></label>';
						str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
					str+='</div>';
				}else{
					str+='<div class="col-sm-3 m_top10">';
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
		var deprtmntId = $("#departmentsId").chosen().val();
		var levelValue = $(".locationCls").chosen().val();
		
		var jsObj = {
			departmentId	: deprtmntId,
			levelId			: LevelId,
			levelValue			: levelValue
		}
		$.ajax({
		  type:'GET',
		  url: 'getDesignationsByDepartmentNewAction.action',
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
		  url: 'getOfficersByDesignationAndLevelNewAction.action',
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

function getAlertDetails(alertStatusId)
	{
		/* var datesArr =[]; 
		if(datesArr != null){
			startDate = datesArr[0];
			endDate = datesArr[1];
		}
		var mobileNo;
		var statusId; */
		$('#alertDataDivId').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		 var dateArray = dates.split("-");
		 if(dateArray != null){
			 fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		var jObj = {
			alertStatusId : alertStatusId,
			mobileNo : '',
			fromDate :fromDateStr,//"12/04/2016",
			toDate : toDateStr//"12/04/2017"
			
		}
		$.ajax({
		  type:'GET',
		  url: 'getAlertDetailsByStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertDetails(result);
			}
		});
}

function getAlertCallerDetails(alertId)
	{
		$("#comntId").val('');
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
//saveAlertStatusDetails();
function saveAlertStatusDetails()
	{
		var comment = $("#comntId").val();
		var feedBackStatus =$("#feedbackStatusList").val();
		var alertId = $("#hiddenAlertId").val();
		var sourceId = $("#hiddenSourceId").val();
		var statusId = $("#hiddenStatusId").val();
		
		var jObj = {
			alertId : alertId,
			comment : comment,
			alertStatusId : statusId,
			alertFeedBackStatusId : feedBackStatus,
			alertSourceId : sourceId
		}
		$.ajax({
		  type:'GET',
		  url: 'saveAlertStatusAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result == "success"){
				$("#saveMsgId").html("<span style='color:green;'>Updated Successfully..</span>");
			}else{
				$("#saveMsgId").html("<span style='color:green;'>Updation failed.Please try again.</span>");
			}
			setTimeout(function(){ 
				 $("#updateAlertModalDivId").modal('hide');
			},1500);
		});
}
getFeedBackStatusDetails();
function getFeedBackStatusDetails()
	{
		var jObj = {
			}
		$.ajax({
		  type:'GET',
		  url: 'getAlertFeedBackStatusDetailsAction.action',
		  data: {task :JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null)
			{
				for(var i in result)
					$('#feedbackStatusList').append('<option value="'+result[i].id+'">'+result[i].status+'</option>');
			}
		});
}	
	
function buildAlertDetails(result){
	var str = '';
	
	str+='<table class="table table-bordered ">';
		str+='<thead>';
			str+='<th>Title</th>';
			str+='<th>Alert Level</th>';
			str+='<th>Created Time</th>'; 
		str+='</thead>';
		str+='<tbody>';
		for( var i in result){
			str+='<tr>';
				str+='<td>'+result[i].title+'</td>';
				str+='<td>'+result[i].locationName+'</td>';
				str+='<td>'+result[i].createdTime+'<button type="button"  class="btn btn-success updateAlertCls" attr_alert_id ="'+result[i].alertId+'" attr_alert__source_id ="'+result[i].alertSourceId+'" attr_alert__status_id ="'+result[i].statusId+'">Update</button></td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#alertDataDivId").html(str);
}
$(document).on("click",".updateAlertCls",function(){
	var alertId = $(this).attr("attr_alert_id");
	var sourceId =$(this).attr("attr_alert__source_id");
	var statusId =$(this).attr("attr_alert__status_id");
	getAlertCallerDetails(alertId);
	$("#hiddenAlertId").val(alertId);
	$("#hiddenSourceId").val(sourceId);
	$("#hiddenStatusId").val(statusId);
});	
	
function buildAlertCallerDetails(result){
	var str= '';
		str+='<div class="panel panel-default m_top10">';
			str+='<div class="panel-body">';
			  str+='<ul>';
				for(var i in result){
				str+='<li> Updated By : '+result[i].name+' - '+result[i].mobileNo+'</li>';
					//str+='<h4>'+result[i].address+'</h4></li>';
				}
			  str+='</ul>';
			str+='</div>';
		str+='</div>';
$("#buildUpdateDivId").html(str);
}



function showDashboard(){
		$("#dateDivId").show();
		getGrievanceSummary();
		$('#dashboardGrevanceDivId').show();
		$('#newGrevanceDivId').hide();
	}
	function showNewGrievance(){
		$('#dashboardGrevanceDivId').hide();
		$('#newGrevanceDivId').show();
	}
	function getGrievanceSummary(){
		$('#summaryDiv').html('<center><img id="" style="width:50px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
		var fromDateStr;
		var toDateStr;
		
		 var dates = $('#dateRangeIdForEvents').val();
		  var dateArray = dates.split("-");
		  if(dateArray != null){
			  fromDateStr = dateArray[0];
			 toDateStr = dateArray[1];
		  }
		  
		var jsObj ={
			startDate:fromDateStr,//'01/04/2017',
			endDate:toDateStr,//'01/05/2017',
			mobileNo:'',
			status:"",
			deptId:0,
			task:""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertCallerDetails.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				buildGrievanceSummary(result);
			}	  
		});
	}
	
	function buildGrievanceSummary(result){
		if(result != null && result.length>0){
			var str='';
			if(result[0].statusList != null && result[0].statusList.length>0) {
				str+='<table  class="table table-bordered"  style="text-align:center;">';
					str+='<thead>';
						str+='<th> TOTAL </th>';
						var totalCount =0;
						for(var k in result[0].statusList){
							str+='<th> '+result[0].statusList[k].status+' </th>';
							if(result[0].statusList[k].count != null)
								totalCount=totalCount+parseInt(result[0].statusList[k].count);
						}
					str+='</thead>';
					str+='<tbody>';
					str+='<tr>';
						str+='<td> '+totalCount+' </td>';
						for(var k in result[0].statusList){
							if(result[0].statusList[k].count != null)
								str+='<td> <a style="color:green;font-weight:bold;" href="javascript:{getAlertDetails('+result[0].statusList[k].alertStatusId+')}"> '+result[0].statusList[k].count+' </a> </td>';
							else
									str+='<td> - </td>';
						}
					str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			}
			
			$('#summaryDiv').html(str);
		}
	}
</script>
</body>
</html>