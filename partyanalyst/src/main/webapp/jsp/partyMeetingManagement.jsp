<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>CadrePartyMeetingManagement</title>
      <link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      <link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css"/>
      <link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css"/>
      <link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
      <link href="daterangepicker/bootstrap-datetimepicker.css" type="text/css" rel="stylesheet"/>
      <link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	  <link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
	   <style>
 label.error{
	 color: red;
	 margin-left:8px;
 }
 table.dataTable tbody th, table.dataTable tbody td {
    padding: 8px 1px;
}
select {
  text-transform:capitalize
}
.scrollit {
    overflow:scroll;
    height:350px;
}
 </style>
   </head>
   <body>
   <input type="hidden" id="refresh" value="no">
      <div class="container">
      <div class="panel panel-default">
         <div class="panel-heading">
            <div class="row">
               <div class="col-sm-8">
                  <h4 class="panel-title m_top5">View Meetings</h4>
               </div>
               <div class="col-sm-3">
                  <div id="showHideDatePickerId">
                     <div class="input-group">
                        <span class="input-group-addon">
                        <i class="glyphicon glyphicon-calendar"></i>
                        </span>
                        <input type="text" class="form-control pull-right"  name="daterangePickerId" id="daterangePickerId"/>
                     </div>
                  </div>
               </div>
               <div class="col-sm-1">
                  <span id="partyMeetingTabButton" data-toggle="collapse" data-target="#partyMeetingTable" class="cadrePartyMeetingTabExpandCollapse" style="cursor:pointer;">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
            </div>
         </div>
         <div class="panel-body" id="partyMeetingTable">
            <ul class="nav nav-tabs" role="tablist">
               <li role="presentation"  class="meetingLevelCls active" meeting_levelId_attr="0" level_attr="all"><a href="#all" id="home" aria-controls="home" role="tab" data-toggle="tab">All</a></li>
               <li role="presentation"  class="meetingLevelCls" meeting_levelId_attr="1" level_attr="statelevel"><a href="#statelevelDivId" aria-controls="profile" role="tab" data-toggle="tab">State Level</a></li>
               <li role="presentation" class="meetingLevelCls"  meeting_levelId_attr="2" level_attr="districtlevel"><a href="#districtlevelDivId" aria-controls="messages" role="tab" data-toggle="tab">District Level</a></li>
			   <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="5" level_attr="townevel"><a href="#townlevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Town Level</a></li>
               <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="3" level_attr="constituencylevel"><a href="#constituencylevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Constituency Level</a></li>
			   <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="6" level_attr="divisionlevel"><a href="#divisionlevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Division Level</a></li>
			   <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="4" level_attr="mandallevel"><a href="#mandallevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Mandal Level</a></li>              
			   <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="7" level_attr="villagelevel"><a href="#villagelevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Village Level</a></li>
               <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="8" level_attr="wardlevel"><a href="#wardlevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Ward Level</a></li>

            </ul>
            <div class="tab-content">
               <div id="meetingTableId" class="m_top10"></div>
			    <div id="paginationCountDivId" class="m_top20 pull-right"></div>
               <div id="paginationDivId" class="m_top20"></div> 	  
            </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading">
            <h4 class="panel-title">Create Meeting </h4>
         </div>
         <div class="panel-body">
		 <form id="meetingValidationId">
            <div class="row">
               <div class="col-sm-4">   
                  <label for="designation">Meeting Name:</label>
                  <input type="text" class="form-control" id="meetingName" name="partyMeetingName" data-rule-required="true" data-msg-required="Please Enter Meeting Name"/>
               </div>
               <div class="col-sm-4">
                  <label for="startDate">Start Date:</label>
                  <input type="text" class="form-control startDateCls" id="startDate" name="partyStartDate" data-rule-required="true" data-msg-required="Please Enter Start Date"/>
               </div>
               <div class="col-sm-4">
                  <label for="endDate">End Date:</label>
                  <input type="text" class="form-control endDateCls" id="endDate" name="partyEndDate" data-rule-required="true" data-msg-required="Please Enter End Date"/> 
               </div>
               <div class="col-sm-4">
                  <label for="meetingType">Meeting Main Type:</label>
                  <select class="form-control" id="meetingTypeId" name="partyMeetingMainType" data-rule-required="true" data-msg-required="Please Select Meeting Main Type">
                     <option value="">Select Meeting Type</option> 
                  </select>
               </div>
               <div class="col-sm-4">
                  <label for="meetingSubType">Meeting Sub-Type:</label>
                  <select class="form-control" id="meetingTypeSubTypeId" name="partyMeetingSubType" data-rule-required="true" data-msg-required="Please Select Meeting Sub Type">
                     <option value="">Select Meeting Sub-Type</option>
                  </select>
               </div>
               <div class="col-sm-4">
                  <label for="meetingLevel">Meeting Level:</label>
                  <select class="form-control meetingLevels" id="meetingLevelId" name="partyMeetingLevel" data-rule-required="true" data-msg-required="Please Select Meeting Level">
                     <option value="">Select Meeting Level</option>
                  </select>
               </div>
            </div>
            <div class="row" id="locationDivId" style="display:none;">
               <div class="col-sm-12">
                  <h4 class="panel-title" >Location:</h4>
               </div>
               <div id="stateDivId" class="col-sm-4" style="display:none;">
                  <label for="state">State:</label>
                  <select class="form-control" id="state" name="partyState" data-rule-required="true" data-msg-required="Please Select State">
                     <option value="">Select State</option>
                  </select>
               </div>
               <div id="districtDivId" class="col-sm-4" style="display:none;">
                  <label for="district">District:</label>
                  <span id="districtSpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="district" name="partyDistrict" data-rule-required="true" data-msg-required="Please Select District">
                     <option value="">Select District</option>
                  </select>
               </div>
               <div id="constituencyDivId" class="col-sm-4" style="display:none;">
                  <label for="constituency">Constituency:</label>
                  <span id="constituencySpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="constituency" name="partyConstituency" data-rule-required="true" data-msg-required="Please Select Constituency">
                     <option value="">Select Constituency</option>
                  </select>
               </div>
               <div id="mandalDivId" class="col-sm-4" style="display:none;">
                  <label for="mandal">Mandal/ Town/ Division:</label>
                  <span id="mandalSpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="mandal" name="partyTehsil" data-rule-required="true" data-msg-required="Please Select Mandal">
                     <option value="">Select Mandal</option>
                  </select>
               </div>
               <div id="villageDivId" class="col-sm-4" style="display:none;">
                  <label for="village">Village / Ward:</label>
                  <span id="villageSpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="village" name="partyVillage" data-rule-required="true" data-msg-required="Please Select Village">
                     <option value="">Select Village</option>
                  </select>
               </div>
            </div>
            <div class="row">
               <div class="col-sm-4">
                  <label for="session">Session:</label>
                  <select class="form-control sessionTypeId" id="sessionId" name="partySession" data-rule-required="true" data-msg-required="Please Select Session">
                     <option value="">Select Session</option>
                  </select>
               </div>
               <div class="col-sm-2">
                  <label for="startDate">Start Time:</label>
                  <input type="text" class="form-control timeCls startTime" id="startTime"  name="partyStartTime" data-rule-required="true" data-msg-required="Please Enter Start Time"/>
               </div>
               <div class="col-sm-2">
                  <label for="startDate">End Time:</label>
                  <input type="text" class="form-control timeCls endTime" id="endTime"  name="partyEndTime" data-rule-required="true" data-msg-required="Please Enter End Time"/>
               </div>
               <div class="col-sm-2">
                  <label for="startDate">Late Time:</label>
                  <input type="text" class="form-control timeCls lateTime" id="lateTime" name="partyLateTime" data-rule-required="true" data-msg-required="Please Enter Late Time"/>
               </div>
               <div class="col-sm-2" style="margin-top:20px;">
                  <button type="button" class="btn btn-primary" id="addSession">Add Session
                  <i class="fa fa-plus" aria-hidden="true"></i>
                  </button>
               </div>
            </div>
            <div id="createSession"></div>
			</form>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading">
            <div class="row">
               <div class="col-sm-11">
                  <h4 class="panel-title">Tab User Details</h4>
               </div>
               <div class="col-sm-1">
                  <span id="tabUserTabButton" data-toggle="collapse" data-target="#tabUserExpandCollapseId" class="tabUserExpandCollapse" style="cursor:pointer;">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
            </div>
         </div>
         <div class="panel-body">
            <div id="tabUserTableDivId"></div>
            <div>
               <div class="form-group">
                  <h4 class="panel-title"> ADD INVITEES : </h4>
               </div>
               <div class="form-group">
                  <label for="textArea">Please Enter MemberShip Id's In Comma Separator:</label>
                  <textarea id="textAreaId" class="form-control" cols="50" rows="4"></textarea>
               </div>
               <div class="form-group">
                  <form action="downloadInviteesTemplateAction.action" method="get">
                     <input type="submit" class="btn btn-danger" value="Download Template">
                  </form>
               </div>
            </div>
            <form name="meetingDetailsSubmit" id="meetingDetailsSubmit">
               <div class="form-group">
                  <input type="file" id="partyMeetingId" multiple="multiple"  name="files[]" class="m_top20" required/>
               </div>
               <div class="form-group pull-right">
                  <div class="col-md-4 col-xs-12 col-sm-4">
                     <button type="button" class="btn btn-primary" id="applicatioSubmitId">Invitee Details</button>
                  </div>
               </div>
            </form>
         </div>
      </div>
	</div>
      <form name="meetingDetailsWithInviteesSubmit" id="meetingDetailsWithInviteesSubmitId">
         <div class="container" >
            <div class="modal fade" id="tdpCadreDetailsModalId" role="dialog" >
               <div class="modal-dialog" style="width:80%;">
                  <!-- Modal content-->
                  <div class="modal-content" >
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">TDP MEMBERS DETAILS</h4>
                     </div>
                     <div class="modal-body" id="modalBodyId"> 
					
					 <div id="tdpCadreTableId" class="m_top10"></div>
                        <div id="tdpCadreDetailsTableDivId"></div>
                        <div id="inveeteenotAvableId"></div>
						
                        <input type="hidden"  id="hiddenMeetingNameId" value="" name="partyMeetingVO.name"/>
                        <input type="hidden"  id="hiddenStartDateId" value="" name="partyMeetingVO.startDateStr"/>
                        <input type="hidden"  id="hiddenEndDateId" value="" name="partyMeetingVO.endDateStr"/>
                        <input type="hidden"  id="hiddenMeetingTypeSubTypeId" value="" name="partyMeetingVO.partyMeetingTypeId"/>
                        <input type="hidden"  id="hiddenMeetingLevelId" value="" name="partyMeetingVO.meetingLevelId"/>
                        <input type="hidden"  id="hiddenStateId" value="" name="partyMeetingVO.stateId"/>
                        <input type="hidden"  id="hiddenDistrictId" value="" name="partyMeetingVO.districtId"/>
                        <input type="hidden"  id="hiddenConstituencyId" value="" name="partyMeetingVO.constituencyId"/>   
                        <input type="hidden"  id="hiddenMandalId" value="" name="partyMeetingVO.tehsilId"/>
                        <input type="hidden"  id="hiddenvillageId" value="" name="partyMeetingVO.villageId"/>
                        <input type="hidden" id="tabuserModelId" value="1" name="partyMeetingVO.insertedById"/> 
						<div id="hiddenFieldsId"></div>						
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-success" id="savingMeetingWithInviteesID">Submit</button>
                        <button type="button" id="closeModalId"class="btn btn-default" data-dismiss="modal">Close</button>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </form>
      <!-- modal for status result-->
      <div class="modal fade resultStatus" id="myModal" role="dialog">
         <div class="modal-dialog modal-sm">
            <div class="modal-content">
               <div class="modal-body">
                  <div id="submitSuccessId"></div>
               </div>
            </div>
         </div>
      </div>
	  
	   <div class="modal fade validationStatus" id="validationModalId" role="dialog">
         <div class="modal-dialog modal-sm">
            <div class="modal-content">
               <div class="modal-body">
                  <div id="validationId"></div>
               </div>
            </div>
         </div>
      </div>
	  
      <!-- kk bbbb-->
      <script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>
<script type="text/javascript" src="js/yahoo/animation-min.js"></script>
<script type="text/javascript" src="js/yahoo/container-min.js"></script>
<script type="text/javascript" src="js/yahoo/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>
<script type="text/javascript" src="js/yahoo/get-min.js"></script>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jqueryvalidation/jquery.validate.js"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="daterangepicker/bootstrap-datetimepicker.min.js"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="partyMeetingManagement/js/createPartyMeeting.js"></script>
<script type="text/javascript">
 $('#constituencySpinnerId').hide();
 $('#mandalSpinnerId').hide();
 $('#villageSpinnerId').hide();
 $('#districtSpinnerId').hide();

function validSessionLateTime(strtTimeHours,endTimeHours,lateTimeHours,startTimeMins,endTimeMins,lateTimeMins){
		var count=0;
		for(var i in lateTimeHours){
		if(lateTimeHours[i] < strtTimeHours[i])
			{
			  $('#validationId').append("<p>late time hours should be more than start time  ="+lateTimeHours[i]+": "+lateTimeMins[i]+"</p>");
			  count++;
			}else if(lateTimeHours[i] == strtTimeHours[i]){
				if(lateTimeMins[i] <= startTimeMins[i] ){
					$('#validationId').append("<p>late time minutes should be more then start time = "+lateTimeHours[i]+": "+lateTimeMins[i]+"</p>");
					count++;
				}
				
			}
			if( lateTimeHours[i] > endTimeHours[i] ){
			$('#validationId').append("<p>late time hours should be less than end time = "+lateTimeHours[i]+": "+lateTimeMins[i]+"</p>");
			count++;
			}else if(lateTimeHours[i] == endTimeHours[i]){
				if(lateTimeMins[i] >= endTimeMins[i]){
					$('#validationId').append("<p>late time minutes should be less than end = "+lateTimeHours[i]+": "+lateTimeMins[i]+"</p>");
					count++;
				}
			}
				if( strtTimeHours[i] > endTimeHours[i] ){
			$('#validationId').append("<p>start time hours should be less than end time = "+lateTimeHours[i]+": "+lateTimeMins[i]+"</p>");
			count++;
			}else if(strtTimeHours[i] == endTimeHours[i]){
				if(strtTimeHours[i] >= endTimeMins[i]){
					$('#validationId').append("<p>start time mints should be less than end = "+lateTimeHours[i]+": "+lateTimeMins[i]+"</p>");
					count++;
				}
			}
		}
		
        return count;
	}

 $(document).on("click", "#applicatioSubmitId", function() {
	 $("#hiddenFieldsId").html(' ');
   var availableSessions=[];
    $('.sessionTypeId').each(function() {
		if(!$('option:selected', $(this)).text() =="Select Session"){
	   availableSessions.push($('option:selected', $(this)).text());
		}
    });
	
	var duplicateSessions = availableSessions.reduce(function(acc, el, i, arr) {
  if (arr.indexOf(el) !== i && acc.indexOf(el) < 0) acc.push(el); return acc;
}, []);

var strtTimeHours=[];
var startTimeMins=[];
var endTimeHours=[];
var endTimeMins=[];
var lateTimeHours=[];
var lateTimeMins=[];

   var completeStartTime=[];
 $('.startTime').each(function() {
	 if($(this).val().length > 1){
	     completeStartTime.push($(this).val());
         var startArr=$(this).val().split(":");
		 strtTimeHours.push(parseInt(startArr[0]));
		 startTimeMins.push(parseInt(startArr[1]));		 
	 }		 
    });
var completeEndTime=[];
	 $('.endTime').each(function() {
		  if($(this).val().length > 1){
		 completeEndTime.push($(this).val());
		 var endArr=$(this).val().split(":");
		 endTimeHours.push(parseInt(endArr[0]));
		 endTimeMins.push(parseInt(endArr[1]));
		  }		 
    });
	
	 var completeLateTime=[];
    $('.lateTime').each(function() {
		 if($(this).val().length > 1){
		 completeLateTime.push($(this).val());
		  var lateArr=$(this).val().split(":");
		  lateTimeHours.push(parseInt(lateArr[0]));
		  lateTimeMins.push(parseInt(lateArr[1]));
		 }
    });

	var errorCount=validSessionLateTime(strtTimeHours,endTimeHours,lateTimeHours,startTimeMins,endTimeMins,lateTimeMins);
	
     var duplicateStartTime = completeStartTime.reduce(function(acc, el, i, arr) {
  if (arr.indexOf(el) !== i && acc.indexOf(el) < 0) acc.push(el); return acc;
}, []);

var duplicateEndTime = completeEndTime.reduce(function(acc, el, i, arr) {
  if (arr.indexOf(el) !== i && acc.indexOf(el) < 0) acc.push(el); return acc;
}, []);

var duplicateLateTime = completeLateTime.reduce(function(acc, el, i, arr) {
  if (arr.indexOf(el) !== i && acc.indexOf(el) < 0) acc.push(el); return acc;
}, []);

if ($('#meetingValidationId').valid() && duplicateSessions.length == 0 &&
	 duplicateStartTime.length == 0 && duplicateEndTime.length==0 && duplicateLateTime.length==0 && errorCount ==0) {
		  if ($("#textAreaId").val().length > 0 && $("#partyMeetingId").val().length > 0) {
         $(".resultStatus").modal('show');
         $('#submitSuccessId').html("");
         $('#submitSuccessId').append("<p>Please upload csv file or use TextArea to enter Membership Id's</p>");
         setTimeout(function() {
             $(".resultStatus").modal('hide');
			 $("#textAreaId").val('');
			 $("#partyMeetingId").val('');
         }, 3000);
     } 
	 else if($("#partyMeetingId").val().length > 0) {
         callActionMethod();
     }
	 else if($("#textAreaId").val().length > 0) {
         var textAreaIds = $("#textAreaId").val();
         var memebershipIds = textAreaIds.split(",");
         $("#tdpCadreDetailsModalId").modal('show');
         getTdpCadreDetailsForInveetMeeting(memebershipIds);
         createHiddenFields();
     } 
	 else if($("#partyMeetingId").val().length == 0 && $("#textAreaId").val().length == 0) {
		    createHiddenFields();
			var uploadHandler = {
             upload: function(o) {
             uploadResult = o.responseText;
             $('#closeModalId').click();
             $(".resultStatus").modal('show');
             $('#submitSuccessId').html("");
             $('#submitSuccessId').append("<p>Meeting Created Successfully</p>");
             setTimeout(function() {
                 $(".resultStatus").modal('hide');
				 var redirectWindow=window.open('cadrePartyMeetingManagementAction.action','_self');
             }, 3000);


         }
     };
     YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
     YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);
     }
	 else{
	 }
}
  if(duplicateSessions.length > 0 ||
	 duplicateStartTime.length > 0 || duplicateEndTime.length>0 || duplicateLateTime.length>0 || errorCount >0){
      $(".validationStatus").modal('show');
	  $('#validationId').html("");
	  if(duplicateSessions.length > 0){
      $('#validationId').append("<p>Please Avoid Duplicate Sessions</p><p>"+duplicateSessions+"</p>");
	  }
	  if(duplicateStartTime.length > 0){
       $('#validationId').append("<p>Please Avoid Duplicate StartTime</p><p>"+duplicateStartTime+"</p>");
	  }
	  if(duplicateEndTime.length > 0){
       $('#validationId').append("<p>Please Avoid Duplicate EndTime</p><p>"+duplicateEndTime+"</p>");
	  }
	  if(duplicateLateTime.length > 0){
       $('#validationId').append("<p>Please Avoid Duplicate LateTime</p><p>"+duplicateLateTime+"</p>");
	  }
          setTimeout(function() {
      $(".validationStatus").modal('hide');
       }, 3000);
	 }

 });

 function callActionMethod() {
     var uploadHandler = {
         upload: function(o) {
			  $('#submitSuccessId').html("");
             uploadResult = o.responseText;
              
             uploadResult = uploadResult.replace("<pre>", "");
             uploadResult = uploadResult.replace("</pre>", "");
             uploadResult = uploadResult.replace(",\"", "");
             uploadResult = uploadResult.replace("\"", "");
             uploadResult = uploadResult.replace(/\"/g, "");
             uploadResult = uploadResult.replace("<pre style=word-wrap: break-word; white-space: pre-wrap;>", "");
             if (uploadResult == "Please Upload Csv or Excel Format") {
                 $(".resultStatus").modal('show');
                 $('#submitSuccessId').append("<p>Please Upload Csv Format</p>");
                 setTimeout(function() {
                     $(".resultStatus").modal('hide');
					  $("#partyMeetingId").val('');
                 }, 3000);

             }
			 
               else if(uploadResult == "Please upload Excel in given Format"){
				  $(".resultStatus").modal('show');
                 $('#submitSuccessId').append("<p>Please upload Excel in given Format</p>");
                 setTimeout(function() {
                     $(".resultStatus").modal('hide');
					  $("#partyMeetingId").val('');
                 }, 3000);
			 }
			 else if(uploadResult == "Please Enter Membership Ids"){
				  $(".resultStatus").modal('show');
                 $('#submitSuccessId').append("<p>Please Enter Membership Ids</p>");
                 setTimeout(function() {
                     $(".resultStatus").modal('hide');
					  $("#partyMeetingId").val('');
                 }, 3000);
			 }
			 else {
                 $("#tdpCadreDetailsModalId").modal('show');
                 var memeberShipArr = uploadResult.split(",");
                 getTdpCadreDetailsForInveetMeeting(memeberShipArr);
                 createHiddenFields();
             }
         }

     }
     YAHOO.util.Connect.setForm('meetingDetailsSubmit', true);
     YAHOO.util.Connect.asyncRequest('POST', 'getMemberShipIdsFromExcelAction.action', uploadHandler);


 }

 function createHiddenFields() {
     $("#hiddenMeetingNameId").val($("#meetingName").val());
		
	var splitedStartDate=$("#startDate").val().split("-");	
	var onlyStartDate=splitedStartDate[2]+"-"+splitedStartDate[1]+"-"+splitedStartDate[0];  
    $("#hiddenStartDateId").val(onlyStartDate);
  
	var splitedEndDate=$("#endDate").val().split("-");
	var onlyEndDate=splitedEndDate[2]+"-"+splitedEndDate[1]+"-"+splitedEndDate[0];  
    $("#hiddenEndDateId").val(onlyEndDate);
	
     $("#hiddenMeetingTypeSubTypeId").val($("#meetingTypeSubTypeId").val());
     $("#hiddenMeetingLevelId").val($("#meetingLevelId").val());
     $("#hiddenStateId").val($("#state").val());
     $("#hiddenDistrictId").val($("#district").val());
     if ($("#constituency").val() == 0) {
         $("#hiddenConstituencyId").val("");
     } else {
         $("#hiddenConstituencyId").val($("#constituency").val());
     }
	 var trimTeshil=$("#mandal").val();
	 $("#hiddenMandalId").val($("#mandal").val());
     $("#hiddenvillageId").val($("#village").val());



     var startTime = [];
     var endTime = [];
     var lateTime = [];
     var sessionTypeId = [];

     var i = 0;
     var j = 0;
     var k = 0;
     var l = 0;
     $('.startTime').each(function() {
         var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.startTimeList[" + i + "]'/>";

         $('#hiddenFieldsId').append(dynamicElement);
         i = i + 1;
     });
     $('.endTime').each(function() {
         var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.endTimeList[" + j + "]'/> ";
         $('#hiddenFieldsId').append(dynamicElement);
         j = j + 1;
     });
     $('.lateTime').each(function() {
         var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.lateTimeList[" + k + "]'/> ";

         $('#hiddenFieldsId').append(dynamicElement);
         k = k + 1;

     });
     $('.sessionTypeId').each(function() {
         var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.sessionTypeId[" + l + "]'/> ";
         $('#hiddenFieldsId').append(dynamicElement);

         l = l + 1;
     });

     var tabUserIdArr = [];
     var n = 0;
     $(".tabUserCheckBoxCls:checked").each(function(index, elem) {
         var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.attendedList[" + n + "]'/> ";
         $('#hiddenFieldsId').append(dynamicElement);
         n = n + 1;
     })
 }

 function getTdpCadreDetailsForInveetMeeting(memeberShipArr) {
	  $("#tdpCadreTableId").html("");
     $("#tdpCadreTableId").html(spinner);
     $("#tdpCadreDetailsTableDivId").html('');
     $("#inveeteenotAvableId").html('');
     var jsObj = {
         memberShipNos: memeberShipArr
     }
     $.ajax({
         type: "POST",
         url: "getTdpCadreDetailsForInveetMeetingAction.action",
         data: {
             task: JSON.stringify(jsObj)
         }
     }).done(function(results) {
         if (results != null && results[0].membershipNo != null && results[0].membershipNo.length > 0) {
             buildInveeteeCreateDatails(results);
         }
         if (results != null && results[0].enrollmentYears != null && results[0].enrollmentYears.length > 0) {
             showNotExistingTdpCadreMembershipNumbers(results);
         }

     });
 }
 var cadreTable = '';

 function buildInveeteeCreateDatails(results) {
	
     var str = '';
     str += "<h5 class='pull-right'>Select All<input type='checkBox' class='checkAllCheckBoxCls'></h5>";
     str += "<table id='inviteeTableId' class='table table-bordered'>";
     str += "<thead>";
     str += "<tr>";
     str += "<th>Membership Number</th>";
     str += "<th>Image</th>";
     str += "<th>Name</th>";
     str += "<th>Desiganation</th>";
     str += "<th>Mobile Number</th>";
     str += "<th>Check Box</th>";

     str += "</tr>";
     str += "<thead>";
     str += "<tbody>";
     if (results[0].membershipNo != null && results[0].membershipNo.length > 0) {
         for (var i in results) {
             str += "<tr>";
             str += "<td class='text-center'>" + results[i].membershipNo + "</td>";
             str += "<td class='text-center'><img src='https://www.mytdp.com/images/cadre_images/" + results[i].imagePathStr + "' style='width:50px;height:50px;' ></img></td>";
             str += "<td class='text-center'>" + results[i].name + "</td>";
             str += "<td class='text-center'>" + results[i].partyName + "</td>";
             str += "<td class='text-center'>" + results[i].mobileNumber + "</td>";
             str += "<th><input  class='checkCls' type='checkBox' value=" + results[i].id + " /></th>";
             str += "</tr>";
         }
		  $("#tdpCadreTableId").html("");
     }
     str += "</tbody>";
     str += "</table>";
     $("#tdpCadreDetailsTableDivId").html(str);
     cadreTable = $('#inviteeTableId').dataTable({
		 paging:false,
		 scrollY:"400"
	 });
     $(".checkCls").prop("checked", true);
	 $(".checkAllCheckBoxCls").prop("checked", true);
 }

 function showNotExistingTdpCadreMembershipNumbers(results) {
     var str2 = '';
     str2 += "<div style='margin-top:10px;'><h5>These MemberShip Numbers Are Not Exist </h5></div>";
     str2 += "<div style='margin-top:20px;'><h5>" + results[0].enrollmentYears + "</h5></div>";
     $("#inveeteenotAvableId").html(str2);
     $("#tdpCadreTableId").html("");
 }

 function showSbmitStatusNew(uploadResult) { //success
         if (uploadResult != null && uploadResult.length < 7) {
             $("#statusModalId").click();
             $("#excelUploadStatusId").html(uploadResult);
         } else {
             $("#statusModalId").click();
             $("#excelUploadStatusId").html(uploadResult);
         }
     }

 $(document).on("click", ".checkAllCheckBoxCls", function() {
     if ($(this).is(":checked")) {
         $(".checkCls").prop("checked", true);
     } else {
         $(".checkCls").prop("checked", false);
     }
 });
$(document).on("click",".checkCls",function(){
	var uncheckArr=[];
	 $(".checkCls").each(function() {
		 if (!$(this).is(":checked")){
				uncheckArr.push("false");
		 }
    });
	if(uncheckArr.length > 0){
		$(".checkAllCheckBoxCls").prop("checked", false);
	}else{
		$(".checkAllCheckBoxCls").prop("checked", true);
	}
});
 $(document).on("click", "#savingMeetingWithInviteesID", function() {
     var m = 0;
     $(".checkCls:checked").each(function(index, elem) {
         var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.tdpCadreIds[" + m + "]'/>";
         $('#hiddenFieldsId').append(dynamicElement);
         m = m + 1;
     });

     var uploadHandler = {
         upload: function(o) {
             uploadResult = o.responseText;
             $('#closeModalId').click();
             $(".resultStatus").modal('show');
             $('#submitSuccessId').html("");
             $('#submitSuccessId').append("<p>Meeting Created successfully</p>");
             setTimeout(function() {
                 $(".resultStatus").modal('hide');
				 var redirectWindow=window.open('cadrePartyMeetingManagementAction.action','_self');
             }, 3000);


         }
     };
     YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
     YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);

 });



 $(document).on("click", "#genarateModelXmlId", function() {
     generateExcelReport();
 });

 function generateExcelReport() {
     tableToExcel("inviteeTavbleId", 'Tab user wise Registration Report');
 }




 $(document).on("click", "#editMeetingDetailsSubmitId", function() {
     if ($("#editTextAreaId").val().length > 0 && $("#editPartyMeetingId").val().length > 0) {
         $(".resultStatus").modal('show');
         $(".meetingEditModelId").modal('hide');
         $('#submitSuccessId').html("");
         $('#submitSuccessId').append("<p>Please upload csv file or use TextArea to enter Membership Id's</p>");
         setTimeout(function() {
              $(".resultStatus").modal('hide');
         }, 3000);
     } else if ($("#editPartyMeetingId").val().length > 0) {
         editCallActionMethod();
     } else if ($("#editTextAreaId").val().length > 0) {
         var editTextAreaIds = $("#editTextAreaId").val();
         var memebershipIds = editTextAreaIds.split(",");
         $("#tdpCadreDetailsModalId").modal('show');
         getTdpCadreDetailsForInveetMeeting(memebershipIds);
         createHiddenFields();
     } else {

     }
 });

 function editCallActionMethod() {
         var uploadHandler = {
             upload: function(o) {
                 uploadResult = o.responseText;
                 uploadResult = uploadResult.replace("<pre>", "");
                 uploadResult = uploadResult.replace("</pre>", "");
                 uploadResult = uploadResult.replace(",\"", "");
                 uploadResult = uploadResult.replace("\"", "");
                 uploadResult = uploadResult.replace(/\"/g, "");

                 uploadResult = uploadResult.replace("<pre style=word-wrap: break-word; white-space: pre-wrap;>", "");
                 if (uploadResult == "Please Upload Csv or Excel Format") {
                     $('#submitSuccessId').html("");
                     $(".resultStatus").modal('show');
                     $('#submitSuccessId').append("<p>Please Upload Csv</p>");
                     setTimeout(function() {
                         $(".resultStatus").modal('hide');
                     }, 3000);

                 } else {
                     $("#tdpCadreDetailsModalId").modal('show');
                     var memeberShipArr = uploadResult.split(",");
                     getTdpCadreDetailsForInveetMeeting(memeberShipArr);

                 }
             }
         };

         YAHOO.util.Connect.setForm('editPartyMeetingExcelUploadName', true);
         YAHOO.util.Connect.asyncRequest('POST', 'getMemberShipIdsFromExcelAction.action', uploadHandler);
     }
	 </script>
	 <script>
     var tableToExcel = (function() {
         var uri = 'data:application/vnd.ms-excel;base64,',
             template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>',
             base64 = function(s) {
                 return window.btoa(unescape(encodeURIComponent(s)))
             },
             format = function(s, c) {
                 return s.replace(/{(\w+)}/g, function(m, p) {
                     return c[p];
                 })
             }
         return function(table, name) {
             if (!table.nodeType) table = document.getElementById(table)
             var ctx = {
                 worksheet: name || 'Worksheet',
                 table: table.innerHTML
             }
             window.location.href = uri + base64(format(template, ctx))
         }
     })()
</script>  
   </body>
</html>
