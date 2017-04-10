<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alert </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
	
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
            	<div class="panel-heading bg_cc">
                	<h4 class="panel-title">NEW GRIEVANCE REQUEST</h4>
                </div>
				<form id="saveGrievanceAlertForm" name="saveGrievanceAlertForm" enctype="multipart/form-data" action="saveGrievanceAlertAction.action" method="POST">
					<div class="panel-body bg_EF">
						<div class="row">
							<div class="col-sm-12">
								<h4 class="text-success text-capital m_top10">location details</h4>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Alert Location Level</label>
								<select class="chosen" id="alertlevelId" onchange="disableByGrievLevel();">
									<option value="0">Select Location Level</option>
									<option value="1">Central</option>
									<option value="2">State</option>
									<option value="3">District</option>
									<option value="4">Constituency</option>
									<option value="5">Mandal/Muncipality</option>
									<option value="6" selected>Village/Ward</option>
									<option value="7">Hamlet</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10" id="stateDiv">
								<label>State</label>
								<select class="chosen" id="stateId" onChange="getDistrictsForReferPopup();" name="grievanceAlertVO.stateId">
									<option value="0">Select State</option>
									<option value="1">Andhra Pradesh</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10" id="districtDiv" style="display:block;">
								<label>District</label>
								<select class="chosen" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup();" name="grievanceAlertVO.districtId">
									<option value="0">Select District</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10" id="constituencyDiv" style="display:block;">
								<label>Constituency</label>
								<select class="chosen" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();" name="grievanceAlertVO.constituencyId">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10" id="mandalDiv" style="display:block;">
								<label>Mandal/Muncipality</label>
								<select class="chosen" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="grievanceAlertVO.mandalId">
									<option value="0">Select Mandal/Muncipality</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10" id="panchayatDiv" style="display:block;">
								<label>Village/Ward</label>
								<select class="chosen" id="referpanchayatId" onchange="getHamletss();" name="grievanceAlertVO.panchayatId">
									<option value="0">Select Village/Ward</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10" id="villageDiv" style="display:none;">
								<label>Hamlet</label>
								<select class="chosen" id="hamletId" name="grievanceAlertVO.hamletId">
									<option value="0">Select Hamlet</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<h4 class="text-success text-capital">grievance details</h4>
							</div>
							<div class="col-sm-8 m_top10">
								<label>Alert Title</label>
								<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
							</div>
							<div class="col-sm-4 m_top10">
								<label>Complaint No</label>
								<input type="text" class="form-control" id="complaintNo" name="grievanceAlertVO.complaintNo"/>
							</div>
							<div class="col-sm-12 m_top10">
								<label>Alert Description : </label>
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
							<div class="col-sm-3 m_top10">
								<label>Complaint Given By</label>
								<select class="chosen" id="callerTypeId" name="grievanceAlertVO.callerTypeId">
									<option value="0">Select Caller Type</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Grievance Related To</label>
								<select class="chosen" id="issueTypeId" name="grievanceAlertVO.issueTypeId">
									<option value="0">Select Issue Type</option>
								</select>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Grievance Entry Category</label>
								<select class="chosen" id="entrySourceId" name="grievanceAlertVO.entrySourceId">
									<option value="0">Select Entry Source</option>
									<option value="1">Toll Free Number</option>
									<option value="2">Others</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 m_top10">
								<h4 class="text-success text-capital">caller details</h4>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Name</label>
								<input type="text" id="nameId" class="form-control" name="grievanceAlertVO.name"/>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Address</label>
								<input type="text" id="addressId" class="form-control" name="grievanceAlertVO.address"/>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Mobile No</label>
								<input type="text" id="mobileNoId" class="form-control" name="grievanceAlertVO.mobileNo"/>
							</div>
							<div class="col-sm-3 m_top10">
								<label>Email ID</label>
								<input type="text" id="emailId" class="form-control" placeholder="abc@xyz.com" name="grievanceAlertVO.emailId"/>
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
							<div class="col-sm-12 m_top10">
								<h4 class="text-success text-capital">assign alert to department officer</h4>
							</div>
							  
							<div class="col-sm-4">
								<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>
								<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">  
									<option value="0">Select Department</option>
									<option value="49">RWS</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>
								<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">  
									<option></option>
								</select>
							</div>
							<div id="parentLevelDivId"> </div>

							<div class="col-sm-4">
								<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>
								<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">
									<option></option>  
								</select>
							</div>
							<div class="col-sm-4">
								<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId"></span></label>
								<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">
									<option></option>
								</select>
							</div>
								  
								  <input type="hidden" id="hiddenAlertId" value="'+alertId+'" name="alertAssigningVO.alertId"/>
								
							  
							<div class="panel-footer text-right pad_5 border_1 bg_EE">
							  <button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">assign alert</button>
							  <img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">
							  <span class="text-success" id="assignSuccess"></span>
							</div>
							</form>
						</div>
						<div class="row">
							<div class="col-sm-4 m_top25">
								<button class="btn btn-success btn-block text-capital" onclick="createGrievanceAlert();">create grievance request</button>
							</div>
						</div>
					</div>
					<input type="hidden" class="form-control" id="locationLevelValhidden" name="grievanceAlertVO.locationValue" />
					<input type="hidden" class="form-control" id="locationLevelIdhidden" name="grievanceAlertVO.locationLevelId" />
				</form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>
$(".chosen").chosen({
	width : '100%'
});

// Load the Google Transliterate API
google.load("elements", "1", {
	packages: "transliteration"
});

getAlertIssueTypes();
getAlertCallerTypes();

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
			$("#referconstituencyId").html('<option value="0">Select Assembly</option>');
			$("#referconstituencyId").trigger('chosen:updated');
			//for mandal/municipality
			$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
			$("#refermandalNameId").trigger('chosen:updated');
			//for panchayat
			$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
			$("#referpanchayatId").trigger('chosen:updated');     
		}else{
			//for dist
			$("#referdistrictId").html('<option value="0">Select District</option>');
			$("#referdistrictId").trigger('chosen:updated');     
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
  }
 function getConstituenciesBydistrictForReferPopup(){
	 var districtId = $("#referdistrictId").val();
	 $("#departmentId").val(0).trigger('chosen:updated');
	 $("#designationId").empty();
	 $("#designationId").trigger('chosen:updated');
	 $("#officerId").empty();
	 $("#officerId").trigger('chosen:updated');
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
 }

 function getMandalsByConstituencyForReferPopup(){
	 var constituencyId = $('#referconstituencyId').val();
	 $("#departmentId").val(0).trigger('chosen:updated');
	 $("#designationId").empty();
	 $("#designationId").trigger('chosen:updated');
	 $("#officerId").empty();
	 $("#officerId").trigger('chosen:updated');
	var jobj = {
		constituencyId : constituencyId
	}
		$.ajax({
			type : "POST",
			url  : "getMandalsByConstituencyAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				
			    mandalStr +='<option value="0">Select Mandal/ Municipality</option>';
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
				$("#refermandalNameId").html('<option value="0">Select Mandal/Muncipality</option>');
				$("#refermandalNameId").trigger('chosen:updated');
				//for panchayat
				$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
				$("#referpanchayatId").trigger('chosen:updated'); 
			}
		});
 }
 
 function getPanchayatsForReferPopup(){
	$("#departmentId").val(0).trigger('chosen:updated');
	$("#designationId").empty();
	 $("#designationId").trigger('chosen:updated');
	 $("#officerId").empty();
	 $("#officerId").trigger('chosen:updated');
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
				url :"getPanchayatDetailsAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				var panchyatStr='';
				if(result!=null && result.length>0){
					panchyatStr +='<option value="0">Select Panchayat</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#referpanchayatId").html(panchyatStr);
					$("#referpanchayatId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#referpanchayatId").html('<option value="0">Select Panchayat</option>');
					$("#referpanchayatId").trigger('chosen:updated'); 
				}     
		});
}

function getHamletss(){
	$("#departmentId").val(0).trigger('chosen:updated');
	$("#designationId").empty();
	 $("#designationId").trigger('chosen:updated');
	 $("#officerId").empty();
	 $("#officerId").trigger('chosen:updated');
	 $("#hamletId").find('option').not(':first').remove();
	 var panchayatId = $('#referpanchayatId').val();
	 var  type = $("#refermandalNameId option:selected").text();
			   
	 if(type.indexOf("Mandal") == -1) 
		type = "muncipality" ;
	else
		type = "mandal" ; 
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
					panchyatStr +='<option value="0">Select Village</option>';
					for(var i in result){
						if(result[i].id > 0)
							panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					$("#hamletId").html(panchyatStr);
					$("#hamletId").trigger('chosen:updated');
				}else{
					//for panchayat
					$("#hamletId").html('<option value="0">Select Village</option>');
					$("#hamletId").trigger('chosen:updated'); 
				}     
		});
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
		$("#stateId").val(0).trigger('chosen:updated');
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
		$("#stateId").val(0).trigger('chosen:updated');
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
		$("#stateId").val(0).trigger('chosen:updated');
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
		$("#stateId").val(0).trigger('chosen:updated');
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
		$("#stateId").val(0).trigger('chosen:updated');
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
		$("#stateId").val(0).trigger('chosen:updated');
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

function getDesignations(){
	$("#officerId").empty();
	$("#officerId").trigger('chosen:updated');
	
	var departmentId = $("#departmentId").val();
	var levelId = $("#alertlevelId").val();
	var levelVal = 0;
	if(levelId == 2){
		levelVal = $("#stateId").val();
	}
	else if(levelId == 3){
		levelVal = $("#referdistrictId").val();
		levelId = 11;
	}
	else if(levelId == 4){
		levelVal = $("#referconstituencyId").val();
	}
	else if(levelId == 5){
		levelVal = $("#refermandalNameId").val();
		var mandalName = $("#refermandalNameId").text();
		if(mandalName.indexOf('Mandal') == -1)
			levelId = 14;
		else
			levelId = 13;
	}
	else if(levelId == 6){
		levelVal = $("#referpanchayatId").val();
		levelId = 15;
	}
	else if(levelId == 7){
		levelVal = $("#hamletId").val();
		levelId = 15;
	}
	
	var jsObj={
		departmentId : departmentId,
		levelId : levelId,
		levelValue : levelVal
	}
	$.ajax({
		type:"POST",
		url :"getDesignationsByDepartmentAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str;
		str+='<option value="0">Select Designation</option>';
		if(result!=null && result.length>0){
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
		}
		$("#designationId").html(str);
		$("#designationId").trigger('chosen:updated');
	});
}

function getOfficers(){
	var designationId = $("#designationId").val();
	var levelId = $("#alertlevelId").val();
	var levelVal = 0;
	if(levelId == 2){
		levelVal = $("#stateId").val();
	}
	else if(levelId == 3){
		levelVal = $("#referdistrictId").val();
		levelId = 11;
	}
	else if(levelId == 4){
		levelVal = $("#referconstituencyId").val();
	}
	else if(levelId == 5){
		levelVal = $("#refermandalNameId").val();
		var mandalName = $("#refermandalNameId").text();
		if(mandalName.indexOf('Mandal') == -1)
			levelId = 14;
		else
			levelId = 13;
	}
	else if(levelId == 6){
		levelVal = $("#referpanchayatId").val();
		levelId = 15;
	}
	else if(levelId == 7){
		levelVal = $("#referpanchayatId").val();
		levelId = 15;
	}
	
	var jsObj={
		designationId : designationId,
		levelId : levelId,
		levelValue : levelVal
	}
	$.ajax({
		type:"POST",
		url :"getOfficersByDesignationAndLevelAction.action",
		 dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		var str;
		str+='<option value="0">Select Officer</option>';
		if(result!=null && result.length>0){
			for(var i in result){
				if(result[i].id > 0)
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
		}
		$("#officerId").html(str);
		$("#officerId").trigger('chosen:updated');
	});
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
  var  level=$("#alertlevelId").val();
  var  state=$("#stateId").val();
  var  district=$("#referdistrictId").val();
  var  assembly=$("#referconstituencyId").val();
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
  
  var department = $("#departmentId").val();
  var designation = $("#designationId").val();
  var officer = $("#officerId").val();
  
  if(level==0)
  {
     $("#errorDiv1").html(" Please select level ");
  }
  
  if(level==2)
  {
    if(state==0)               
      {
      $("#errorDiv1").html(" Please select state ");
          return;
    }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      $("#errorDiv1").html(" Please select state ");
          return;
    }
    if(district==0)
    {
      $("#errorDiv1").html(" Please select District ");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
    if(assembly==0)
    {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
    }
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId1 option:selected").text();
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
    if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
    if(mandal==0)
    {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
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
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
      if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
      if(mandal==0)
     {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errorDiv1").html(" Please select Panchayat ");
        return;
     }
	 if(village==0)
     {
    $("#errorDiv1").html(" Please select Village ");
        return;
     }
	 $("#locationLevelValhidden").val(village);
		 $("#locationLevelIdhidden").val(9);
    }
	if(level==6)
  {
	   var panchayatName = $("#referpanchayatId1 option:selected").text();
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
      if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
      if(mandal==0)
     {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errorDiv1").html(" Please select Panchayat ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
	
	 if(title.length==0 ||title=='')
	  {
		$("#errorDiv1").html(" Please enter Alert Title.  ");
			return;
	  }
  
	if(description.length==0 ||description==''){
		$("#errorDiv1").html(" Please enter description ");
		return;
	}
	  
	$("#createAlertajaxImg").html('<img src="images/search.gif"/>');
	var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					$("#createAlertajaxImg").html('');
					if(uploadResult.indexOf("success") !=-1)
					{
					 $("#errorDiv1").html("Alert Created Successfully ").css("color","green");	
					 setTimeout(function(){ $("#errorDiv1").html("");
					 }, 1000);
					}else if(uploadResult.indexOf("edit") !=-1){  
						$("#errorDiv1").html("Alert Edited Successfully ").css("color","green");	
						setTimeout(function(){ $("#errorDiv1").html("");
						}, 1000);
					}  
					clearFields();
					return false;
				}
			};

		YAHOO.util.Connect.setForm('saveGrievanceAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveGrievanceAlertAction.action',uploadHandler);
	}

	function clearFields(){
		$("#extraUploadFileDiv").html("");
		$("#uploadFileId0").val("");
		$("#extraClarificationUploadFileDiv li").each(function(){
			$(this).remove();                   
		});
		$("#totalFileListId li").each(function(){
			$(this).remove();                             
		});
		$("#apptmemberDetailsDiv").html("");
		$(".membersBlock").html("");
		$("#assignedMembers").html("");
		$(".assignedMembersBlock").html("");
		$("#involvedCandidatesDiv").hide();	
		
		showHideBySearchType();	
		
		involvedCadreIds =[];
		$("#involvedMembers").html('(0 - Members added)');
		
		$("#alertTitleId").val("");
		$(".alertclearCls").val("");
		$(".clearCls").val("");
		$("#alertCategory").val(0);
		
		$("#advanceSearchTypeId").val(0);
		var select = new Dropkick("#advanceSearchTypeId");
		select.refresh();	
		
		$("#stateId1").val(0);
		var select = new Dropkick("#stateId1");
		select.refresh();
		
		$("#alertTypeId").val(0);
		var select = new Dropkick("#alertTypeId");
		select.refresh();
		
		$("#alertCategory").val(0);
		var select = new Dropkick("#alertCategory");
		select.refresh();
		
		$("#alertSeverityId").val(0);  
		var select = new Dropkick("#alertSeverityId");
		select.refresh();
		
		$("#alertlevelId1").val(2);
		var select = new Dropkick("#alertlevelId1");
		select.refresh();
		
		$("#alertSourceId").val(0);
		var select = new Dropkick("#alertSourceId");
		select.refresh();
		
		$("#alertImpactId").val(0);
		var select = new Dropkick("#alertImpactId");
		select.refresh();
	}
		
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
					str+='<div class="col-sm-4">';
						str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
						str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
					str+='</div>';
				}else{
					str+='<div class="col-sm-4">';
						str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
						str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
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

</script>
</body>
</html>