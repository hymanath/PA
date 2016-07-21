<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alerts </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<link href="dist/Appointment/custom.css" rel="stylesheet" type="text/css">
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<script src="dist/CreateAlert/createAlert.js" type="text/javascript"></script>
	<style type="text/css">
	    .onoffswitch {
        position: relative; width: 111px;
        -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
    }
    .onoffswitch-checkbox {
        display: none;
    }
    .onoffswitch-label {
        display: block; overflow: hidden; cursor: pointer;
        border-radius: 20px;
    }
    .onoffswitch-inner {
        display: block; width: 200%; margin-left: -100%;
        transition: margin 0.3s ease-in 0s;
    }
    .onoffswitch-inner:before, .onoffswitch-inner:after {
        display: block; float: left; width: 50%; height: 23px; padding: 0; line-height: 23px;
        font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
        box-sizing: border-box;
    }
    .onoffswitch-inner:before {
        content: "POSITIVE";
        padding-left: 10px;
        background-color: #34A7C1; color: #FFFFFF;
    }
    .onoffswitch-inner:after {
        content: "NEGATIVE";
        padding-right: 10px;
        background-color: #d9534f; color: #FFF;
        text-align: right;
    }
    .onoffswitch-switch {
        display: block; width: 18px; margin: 5px;
        background: #FFFFFF;
        position: absolute; top: 0; bottom: 0;
        right: 77px;
        border-radius: 20px;
        transition: all 0.3s ease-in 0s; 
		height:17px;
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
        margin-left: 0;
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
        right: 0px; 
    }
	</style>
	</head>  	
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default" style="border-radius:0px;">
				<div class="panel-heading" style="background:#ccc;border-radius:0px;">
					<h4 class="panel-title">POLITICAL ALERT</h4>
				</div>
				<div class="panel-body" style="background:#eee;">
					<div class="row">
						<div class="col-md-12">
					
				
				<form id="saveAlertForm" name="saveAlertForm" enctype="multipart/form-data" action="saveAlertAction.action" method="POST">
				<div>
					
					<!--<div class="row">
						<div class="col-md-6">
							<label>Search Candidates</label>
							<div class="input-group">
								<input type="text" class="form-control" id="candidateNameId"/>
								<span class="input-group-addon" onclick="getCandidateNameDetails()">
									<i class="glyphicon glyphicon-search" ></i>
								</span>
							</div>
						</div>
						
						<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none";/>
						<div class="col-md-3">
							<label>Select Candidates</label>
							<select class="dropkickClass"  id="candidatesNameListId" name="alertVO.candidateId">
							<option value="0">Select Candidate</option>	
							</select>
						</div>-->
						
						
						
						<div class="row m_top10">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">CREATE ALERT</h4>
									</div>
									<div class="panel-body">
										<div class="col-md-4 m_top10">
											<label>Alert Type</label>
											<select class="dropkickClass" name="alertVO.alertTypeId" id="alertTypeId">
												<option value="0">Select Alert</option>
											</select>
											
											
										</div>
										<div class="col-md-4 m_top10">
											<label>Alert Source</label>
											<select class="dropkickClass" name="alertVO.alertSourceId" id="alertSourceId">
												<option value="0">Select Alert Source</option>
											</select>
											
											
										</div>
									
										<div class="col-md-4 m_top10">
											<label>Alert Severity</label>
											<select class="dropkickClass" name="alertVO.severity" id="alertSeverityId">
												<option value="0">Select Alert Severity</option>
												<option value="1">High</option>
												<option value="2">Medium</option>
												<option value="3">Low</option>
											</select>
										</div>
										<div class="col-md-4 levelShowCls1 m_top10" >
											<label>Level</label>
											<select class="dropkickClass" id="alertlevelId1" attr-index="1" onchange="disableByLevel(1);" >
											<option value="2">State</option>
											 <option value="3">District</option>
											 <option value="4">Constituency</option>
											 <option value="5">Mandal/Muncipality</option>
											 <option value="6">Village/Ward</option>
											</select>
										</div>
										<div class="col-md-4 stateShowCls1 m_top10" >
											<label>State</label>
											 <select class="dropkickClass" class="stateCls" id="stateId1" onChange="getDistrictsForReferPopup(1);" name="alertVO.stateId">
											 <option value="0">Select State</option>
											 <option value="1">AP</option>
											 <option value="36">TS</option>
											 </select>
										</div>
								   
										<div class="col-md-4 locationsFilterCls distCls1 m_top10">
											 <label>District</label>
											 <select class="dropkickClass" id="referdistrictId1" onChange="getConstituenciesBydistrictForReferPopup(1);" name="alertVO.districtId">
											 <option value="0">Select District</option></select>
										</div>
										<div class="col-md-4 locationsFilterCls constiCls1 m_top10">
											<label>Assembly</label>
											<select class="dropkickClass" id="referconstituencyId1" onChange="getMandalsByConstituencyForReferPopup(1);" name="alertVO.constituencyId">
											<option value="0">Select Assembly</option>
											</select>
										</div>
										<div class="col-md-4 locationsFilterCls mandalCls1 m_top10">
											<label>Mandal/ Municipality</label>
											 <select class="dropkickClass" id="refermandalNameId1" onChange="getPanchayatsForReferPopup(1);" name="alertVO.tehsilId">
												<option value="0">Select Mandal/ Municipality</option>
											 </select>
										</div>
										<div class="col-md-4 locationsFilterCls panchayatCls1 m_top10">
											<label>Panchayat/Ward</label>
											<select class="dropkickClass" id="referpanchayatId1" name="alertVO.panchayatId">
											<option value="0">Select Panchayat/Ward</option>
											</select>
										</div>
										<div class="col-md-12">
										
											<div class="m_top10"><b>Select Language: </b> <input type="radio"  value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"> Telugu<input type="radio"  value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"> English </div>
										</div>
										<div class="col-md-6 m_top10" style="clear: both;">
										
										
											<label>Description</label>
											<textarea class="form-control alertclearCls" id="alertdescriptionId" name="alertVO.desc"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
					
				</div>
				
				<input type="hidden" class="form-control" id="locationLevelValhidden" name="alertVO.locationValue" />
				<input type="hidden" class="form-control" id="locationLevelIdhidden" name="alertVO.locationLevelId" />
				
				<div class="row">
					<div class="col-md-12">
					<div style="background:#fff">
					<div class="col-md-12 m_top10">
						<h3 class="panel-title text-success">SEARCH</h3>
					</div>
					<div class="col-md-3 advanceSearchCls">
						<label>Search Type</label>
						<select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();buildLevels();">
							<option value="0">Select Search Type</option>
							<option value="1">Name</option>
							<option value="mobileno">Mobile No</option>
							<option value="mebershipno">Membership No</option>
							<option value="votercardno">Voter Id Card No</option>
							<option value="2">Public Representative</option>
							<option value="3">Party Committee</option>
						</select>
					</div>
					
					
					 <div class="col-md-3 pad_0 advanceSearchCls advanceprclsDiv">
						<label class="advanceNameCls">Search By Name/Membership No*<span class="text-danger">*</span></label>
						<input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
						
					</div>
					<div class="col-md-3">
						<label class="advancePRCls">Search Designation</label>
						 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
							<option value="0">Select Designation</option>
						</select>
						<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
					</div>
					
						
						<div class="col-md-3 levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="alertlevelId" attr-index="0" onchange="disableByLevel('');" >
							<option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" class="stateCls" id="stateId" onChange="getDistrictsForReferPopup('');">
							 <option value="0">Select State</option>
							 <option value="1">AP</option>
							 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
							 <option value="0">Select District</option></select>
						</div>
						<div class="col-md-3 locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
							<option value="0">Select Assembly</option>
							</select>
						</div>
						<div class="col-md-3 locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" >
								<option value="0">Select Mandal/ Municipality</option>
							 </select>
						</div>
						<div class="col-md-3 locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" >
							<option value="0">Select Panchayat/Ward</option>
							</select>
						</div>
						
						
						
						<div>
									<div class="advanceCadreCommittee" id="referCommitteeDiv">
									 <div class="col-md-3">
										<label>Select Committee</label>
										<select id="referCommitteeId" class="dropkickClass" >
											<option value="0">All</option>
											<option value="1">Main</option><option value="2">Telugu Yuvatha</option>
											<option value="3">Telugu Mahila</option><option value="4">Telugu Rythu</option>
											<option value="17">Trade</option><option value="6">BC Cell</option><option value="7">SC Cell</option>
											<option value="8">ST Cell</option><option value="9">Minority Cell</option><option value="18">Christian</option>
											<option value="11">TNSF (Student Union)</option><option value="5">TNTUC</option><option value="15">TSNV (Technical Expert Cell)</option>
											<option value="10">Legal Cell</option><option value="16">Doctor Cell</option><option value="20">Kallu Geetha Karmikulu</option>
											<option value="21">Chenetha</option><option value="19">Telugu Rakshana Vedika</option><option value="14">TNUS ( Teachers Union) </option>
											<option value="12">Commercial Cell</option><option value="13">Cultural Cell</option>
										</select>
									  </div>
									</div> 
									<div >
										<div class="col-md-6">
											<select id="cadreCommitteeDiv" multiple class="advanceCadreCommittee" style="width:250px !important;"></select>
											<div id="representativesDiv"></div>
											<div id="referRoleErrorDiv"></div>
										</div>
									</div>
								</div></br>
								
								
				


											<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
						<div class="col-md-2">
							<button type="button" class="btn btn-block btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();" id="searchBtnId"  style="margin-top: 25px;display:none;">Search Member</button>
						</div>
						<div class="col-md-1">
							<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
						</div>
							<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
						
						<div class="row m_top25">
						<div class="col-md-12" id="clearSerchDivId">
							<div id="apptmemberDetailsDiv"></div>
						</div>
					</div>
						</div>
						</div>
						</div>
						<div class="row m_top10" id="involvedCandidatesDiv" style="display:none;">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">ADD INVOVLED CANDIDATES TO THIS ALERT</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="membersBlock col-md-12" style="display:none;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
				<div class="row">
					<div id="errorDiv1" class="col-md-12" style="font-weight:bold;color:red;font-size:15px;"></div>
						<div class="col-md-3" style="margin-top:10px">
						<input class="btn btn-primary btnNewCustom1" id="addThisalertId" onclick="createAlert();" type="button" value="CREATE ALERT" ></input>
						<!--<button  type="button" class="btn btn-primary">CREATE ALERT</button>-->
						</div>
					</div>
					
							</form>
								
						</div>
									 
					</div>
					</div>


				</div>
			</div>
		</div>
	</div>
		<!-- language convertion-->
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

// Load the Google Transliterate API
   google.load("elements", "1", {
         packages: "transliteration"
       });

</script> 
<script type="text/javascript">


 var control;
	var lang;
   function onLoad() {
	
       lang = $("input[name=language]:checked").val();
     var options = {
         sourceLanguage:
             google.elements.transliteration.LanguageCode.ENGLISH,
         destinationLanguage:
             [''+lang+''],
         shortcutKey: 'alt+t',
         transliterationEnabled: true
     };

     // Create an instance on TransliterationControl with the required
     // options.
     control =
         new google.elements.transliteration.TransliterationControl(options);

     // Enable transliteration in the textbox with id
     // 'descrptionId'.

	 	if ($('#alertdescriptionId').length){
control.makeTransliteratable(['alertdescriptionId']);
 

}
   }
   function languageChangeHandler() {
  
        var lang1 = $("input[name=language]:checked").val();
		if(lang1 =="en")
	   {
		control.disableTransliteration();
		}
		else
	   {
		   control.enableTransliteration();
           control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
			}
      }
 google.setOnLoadCallback(onLoad);
$(".dropkickClass").dropkick()
function getCandidatesByName(){
		var  CandidateName=$("#candidateNameId").val();
		var jsObj =
		        {
			CandidateName : CandidateName
		          }
		//$('#ajaxImage').show();
				$.ajax({
					  type:'GET',
					  url: 'getCandidatesByNameAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#candidatesNameListId').append('<option value="0"> Select Candidate </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#candidatesNameListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#candidatesNameListId").dropkick();
							var select1 = new Dropkick("#candidatesNameListId");
							select1.refresh();
					}
				  
				});
		}
		function getCandidateNameDetails()
		{
			
		 $("#errorDiv").html('');
		 var CandidateName=$("#candidateNameId").val();
		 if(CandidateName.trim().length<=3 || CandidateName ==""){
          $("#errorDiv").html('plz enter minimum 3 characters ');
	      return;
         }	
		 getCandidatesByName();
		}
		
function createAlert()
{
  var  alertType=$("#alertTypeId").val();
  var alertSourceId = $("#alertSourceId").val();
  var alertSeverityId = $("#alertSeverityId").val();
  var  level=$("#alertlevelId1").val();
  var  state=$("#stateId1").val();
  var  district=$("#referdistrictId1").val();
  var  assembly=$("#referconstituencyId1").val();
  var  mandal=$("#refermandalNameId1").val();
  var  panchayat=$("#referpanchayatId1").val();
 // var  candidate=$("#candidatesNameListId").val();
  //var  candidateName=$("#candidateNameId").val();
  var  description=$("#alertdescriptionId").val().trim();
   $("#errorDiv1").html('');
  $("#errorDiv1").css("color","red");
  $("#candidateId").val('');
  if(alertType==0)
  {
    $("#errorDiv1").html(" Please select Alert Type ");
        return;
  }
  if(alertSourceId==0)
  {
    $("#errorDiv1").html(" Please select Alert Source ");
        return;
  }
   if(alertSeverityId==0)
  {
    $("#errorDiv1").html(" Please select Alert Severity ");
        return;
  }
  
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
	var candidateId="";
	$(".memberDatacls").each(function(){
		if($(this).val() != null && $(this).val().length > 0)
		candidateId += $(this).val()+",";
	})
 /*if(candidateId==0 || candidateId.length == 0)
  {
    $("#errorDiv1").html(" Please select Candidate ");
        return;
  }*/
	var n=candidateId.lastIndexOf(",");
    candidateId=candidateId.substring(0,n) ;
	$("#candidateId").val(candidateId);
	

   if(description.length==0 ||description=='')
  {
    $("#errorDiv1").html(" Please select description ");
        return;
  }

var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					if(uploadResult.indexOf("success") !=-1)
					{
					 $("#errorDiv1").html(" Alert Created Successfully ").css("color","green");	
					}
					clearFields();
					disableByLevel(1);
					return false;
				}
			};

		YAHOO.util.Connect.setForm('saveAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveAlertAction.action',uploadHandler);
}

function clearFields()
{
	$(".alertclearCls").val("");
	 $("#alertTypeId").val(0);
	  var select = new Dropkick("#alertTypeId");
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
	 var select = new Dropkick("#stateId1");
				select.refresh();
				$("#apptmemberDetailsDiv").html("");
				$(".membersBlock").html("");
				$("#involvedCandidatesDiv").hide();
				
				
}
function getAlertType(){
		$("#alertTypeId").html('');
		var jsObj =
		        {
			task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertTypeAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertTypeId').append('<option value="0"> Select Alert Type </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertTypeId").dropkick();
							var select1 = new Dropkick("#alertTypeId");
							select1.refresh();
					}
				  
				});
		}
		
var loginUserId = "${sessionScope.USER.registrationID}";
function getAlertsource(){
		$("#alertSourceId").html('');
		var jsObj =
		        {
					userId : loginUserId,
				task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertSourceForUserAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertSourceId').append('<option value="0"> Select Alert Source </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertSourceId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertSourceId").dropkick();
							var select1 = new Dropkick("#alertSourceId");
							select1.refresh();
					}
				  
				});
		}
function buildapptmemberDetails(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead><th></th><th></th><th></th></thead>';
			str+='<tbody>';
			var xindex =0;
			for(var i in result){
				if( xindex == 0)
			{
				str+='<tr>';
			}
				
				
				str+='<td style="padding:0px !important;">';
					str+='<div class="col-md-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li>';
							str+='<div class="row">';
								
								str+='<div class="col-md-12">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
										str+='</div>';
										str+='<div class="media-body">';
										
							
										if(result[i].constituency !=null && result[i].constituency.length>0 ){
											<c:choose>
											<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS')}">
											if(result[i].id != null && result[i].id > 0){
												if(result[i].candidateType=="voter"){
												 str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div>';
												}else{
													str+='<a  target="_blank" data-toggle="tooltip" data-placement="top" title="Cadre Details" style="cursor:pointer;" href="cadreDetailsAction.action?cadreId='+result[i].id+'"><div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div></a>';
												}
											}
											else
											str+='<span style="color:#34A7C1;">'+result[i].name+'</span> ';
											</c:when>
											<c:otherwise>
											str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span></div>';
											</c:otherwise>
											</c:choose>
											
										}else{
											str+='<div style="color:#34A7C1;">'+result[i].name+'</div>';
										}
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p ><span><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].mobileNo+'</span>';
										}else{
											str+='<p><span><i class="fa fa-mobile" style="font-size:15px"></i>   - </span>';
										}
										
										if(result[i].designation !=null && result[i].designation.length>0){
											
												str+='<span style="margin-left:10px;"> '+result[i].designation+'</span></p>';
										}else{
											
											//str+='<span style="margin-left:10px;">Designation: - </span></p>';
											
											 if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
												 if(result[i].candidateType == 'cadre'){
													str+='<span style="margin-left:10px;"> - Cadre</span></p>'; 
												 }else{
													 str+='<span style="margin-left:10px;"> - </span></p>';
												 }
											}else{
												str+='<span style="margin-left:10px;"> - </span></p>';
											} 
										}
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="btn btn-success btn-sm col-md-4 col-md-offset-4 m_top10" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv candidatecls close'+result[i].id+'"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Select Candidate" attr-consti="'+result[i].constituency+'"> &nbsp;SELECT</label></div>';	
							  
								/*if(result[i].appointmentCandidateId != null && result[i].appointmentCandidateId > 0){
									
									str+='<div class="col-md-1 m_top10"><a  title="Click here to View '+result[i].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].appointmentCandidateId+'" attr-name="'+result[i].name+'" attr-designation="'+result[i].designation+'" attr-mobile="'+result[i].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a></div>&nbsp;&nbsp;';
									
								}
								
								
							if(result[i].designation==null){
								result[i].designation = "";
							}
							if(result[i].aptExists == false && result[i].appointmentCandidateId != null){
								str+='<div class="col-md-1   m_top10" attr_id="'+result[i].id+'" >';
							}
							else{
								str+='<div class="col-md-1 col-xs-offset-1 m_top10" attr_id="'+result[i].id+'" >';
							}
							if(result[i].aptExists == false)
							{
								str+='<div class="btn btn-success btn-sm" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Create Appointment Request"> &nbsp;SELECT</label></div>';	
							}								
							else{
								str+='<label data-toggle="tooltip"  data-placement="top" title="This Candidate Already in '+result[i].aptName+' Appointment with '+result[i].aptStatus+' Status: you can not addtion to another Appointment"> ';
								str+=''+result[i].aptName+' - '+result[i].aptStatus+'';
								str+='</label>';
							}
							
								str+='</div>';
								
							str+='</div>';*/
						str+='</li>';
					 
					str+='</ul>';
				str+='</div>';
					
				str+='</td>';
				xindex++;
				if(result.length-1 == i){
					if(xindex % 3 == 2){
						str+='<td></td>';
						str+='</tr>';
					}
					if(xindex % 3 == 1){
						str+='<td></td>';
						str+='<td></td>';
						str+='</tr>';
					}
				}
				 if( xindex == 3){
					str+='</tr>';
					xindex = 0;
				} 
			}
			str+='</tbody>';
			str+='</table>';
		}
		
		$("#apptmemberDetailsDiv").html(str);
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		
		applyPagination();
	}
getAlertType();
buildLevels();
showHideSearch("advanceSearch");
showHideBySearchType();
disableByLevel(1);
getAlertsource();

$("#apptmemberDetailsDiv").html("");

//showHideSearch("advanceSearch");
//showHideBySearchType();

</script>
</body>
</html>