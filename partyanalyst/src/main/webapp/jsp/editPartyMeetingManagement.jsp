<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>EditPartyMeetingManagement</title>
      <link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      <link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css"/>
      <link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css"/>
      <link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
      <link href="daterangepicker/bootstrap-datetimepicker.css" type="text/css" rel="stylesheet"/>
      <link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
  getUrlVars()
		function getUrlVars()
		{
			var vars = [], hash;
			var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			for(var i = 0; i < hashes.length; i++)
			{
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		}
	var meetingId = getUrlVars()["meetingId"];

		getPartyMeetingDeatilesForMeetingEdit()
		function getPartyMeetingDeatilesForMeetingEdit() {
			var jsObj = {
				partyMeetingMainTypeId: meetingId
			}
			$.ajax({
				type: "GET",
				url: "getPartyMeetingDeatilesForMeetingEditAction.action",
				data: {
					task: JSON.stringify(jsObj)
				}
			}).done(function(results) {
				if (results != null) {
				   inviteeDetails(results);
					//buildInveeteeDetailsTable(results);
					//buildTabUserDetails(results, meetingId);
					// getPartyMeetingTabUserDetailsAction(meetingId)
				}
			});
		}
		function inviteeDetails(results) {
			var type = "model";
			$("#modalMeetingId").val(results[0].id);
			$("#meetingNameModelId").attr('value', results[0].name);
			$("#startDateModelId").attr('value', results[0].startDate);
			$("#endDateModelId").attr('value', results[0].endDate);
			getDistrictsAction(1, type, results[0].districtId);
			$("#meetingLevelModelId option[value=" + results[0].meetingTypeId + "]").attr('selected', 'selected');
			$("#meetingTypeSubTypeModelId option[value=" + results[0].mandalTwnDivisionId + "]").attr('selected', 'selected');
			$("#meetingTypeModelId option[value=" + results[0].meetingMainTypeId + "]").attr('selected', 'selected');
			var districtId = $("#districtModelId").val();
			$("#constituencyModelId").html('');
			getConstituencyAction(districtId, type, results[0].constituencyId, results[0].teshilId, results[0].panchayatId);
			var meetingMainTypeId = $("#meetingTypeModelId").val();

			var meetSubSeVar = results[0].mandalTwnDivisionId;
			getMeetingSubTypeAction(meetingMainTypeId, type, meetSubSeVar);
		}
		function getConstituencyAction(districtId, type, id, theshilId, panchayatId) {
				$('#constituency').html(" ");
				var jsObj = {
					districtId: districtId
				}
				$.ajax({
					type: "GET",
					url: "getConstituenciesForADistrictAjaxAction.action",
					data: {
						task: JSON.stringify(jsObj)
					}
				}).done(function (results) {
					$('#constituencySpinnerId').hide();
					for (var i in results) {
						if (type == null) {
							$("#constituency").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
						} else {

							$("#constituencyModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
							$("#constituencyModelId option[value=" + id + "]").attr('selected', 'selected');

						}
					}
					if (type == "model") {
						var constituency = $("#constituencyModelId").val();
						getMandalBasedOnConstituencyAction(constituency, type, theshilId, panchayatId);

					}
				});
			}
		function getDistrictsAction(stateId, model, id) {
			$("#district").html("");
			var jsObj = {
				stateId: stateId
			}
			$.ajax({
				type: "GET",
				url: "getDistrictsAction.action",
				data: {
					task: JSON.stringify(jsObj)
				}
			}).done(function(results) {
				$('#districtSpinnerId').hide();
				for (var i in results) {
					if (model == null) {
						$("#district").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					} else {
						$("#districtModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
						$("#districtModelId option[value=" + id + "]").attr('selected', 'selected');
					}
				}
			});
		}
    
		function getMeetingSubTypeAction(mainMeetingTypeId, model, selId) {
			$("#meetingTypeSubTypeId").html("");
			var jsObj = {
				partyMeetingMainTypeId: mainMeetingTypeId
			}
			$.ajax({
				type: "GET",
				url: "getMeetingSubTypeAction.action",
				data: {
					task: JSON.stringify(jsObj)
				}
			}).done(function(results) {
				for (var i in results) {
					if (model == null) {
						$("#meetingTypeSubTypeId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					} else {
						$("#meetingTypeSubTypeModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					}

				}
				if (model = "model") {
					$("#meetingTypeSubTypeModelId option[value=" + selId + "]").attr('selected', 'selected');
				}

			});
		}
		function getMandalBasedOnConstituencyAction(constituencyId, type, theshilId, panchayatId) {
			$('#mandal').html(" ");
			var jsObj = {
				constituencyId: constituencyId
			}
			$.ajax({
				type: "GET",
				url: "getMandalBasedOnConstituencyAction.action",
				data: {
					task: JSON.stringify(jsObj)
				}
			}).done(function(results) {
				$('#mandalSpinnerId').hide();
				for (var i in results) {
					if (type == null) {
						$("#mandal").append('<option value=' + 0 + '>' + "Select Mandal" + '</option>');
						$("#mandal").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					} else {
						$("#mandalModelId").append('<option value=' + 0 + '>' + "Select Mandal" + '</option>');
						$("#mandalModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');

					}
				}
				if (type == "model") {
					$("#mandalModelId option[value=" + theshilId + "]").attr('selected', 'selected');
					var mandalId = $("#mandalModelId").val();
					getPanchayatWardByMandalAction(mandalId, constituencyId, type, panchayatId)
				}
			});
		}
		function getPanchayatWardByMandalAction(mandalId, constituencyId, type, panchayatId) {
			$('#village').html(" ");
			var jsObj = {
				constituencyId: constituencyId,
				mandalId: mandalId
			}
			$.ajax({
				type: "GET",
				url: "getPanchayatWardByMandalsAction.action",
				data: {
					task: JSON.stringify(jsObj)
				}
			}).done(function(results) {
				$('#villageSpinnerId').hide();
				for (var i in results) {
					if (type == null) {
						$("#village").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					} else {
						$("#villageModelId").append('<option value=' + 0 + '>' + "Select Village" + '</option>');
						$("#villageModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					}
				}
				if (type == "model") {

					$("#villageModelId option[value=" + panchayatId + "]").attr('selected', 'selected');
				}
			});
		}
		  getPartyMeetingLevels()
			function getPartyMeetingLevels() {
				var jsObj = {}
				$.ajax({
					type: "GET",
					url: "getPartyMeetingLevelsAction.action",
					data: {
						task: JSON.stringify(jsObj)
					}
				}).done(function(results) {
					for (var i in results) {
						$("#meetingLevelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
						$("#meetingLevelModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
					}
				});
			}
</script> 
   </head>
   <body>
<body>
<div id="meetingEditModelId">
         <div class="panel panel-default">
         <div class="panel-heading">
            <h4 class="panel-title">Edit Party Meeting </h4>
         </div>
         <div class="panel-body">
					 <div class="panel panel-default">
                       <div class="panel-body">
                        <div class="row">
                           <div class="col-sm-4">
                              <label for="designation">Meeting Name:</label>
                              <input type="text" class="form-control" id="meetingNameModelId" name="partyMeetingVO.name"/>
                           </div>
                           <input type="hidden" value="" id="modalMeetingId" name="partyMeetingVO.partyMeetingId" required/>
                           <div class="col-sm-4">
                              <label for="startDate">Start Date:</label>
                              <input type="text" class="form-control startDateCls"  id="startDateModelId" name="partyMeetingVO.startDate" />
                           </div>
                           <div class="col-sm-4">
                              <label for="endDate">End Date:</label>
                              <input type="text" class="form-control endDateCls"  id="endDateModelId" name="partyMeetingVO.endDate" />
                           </div>
                           <div class="col-sm-4">
                              <label for="meetingType">Meeting Main Type:</label>
                              <select class="form-control" id="meetingTypeModelId" name="meetingType"  >
                                 <option value="0">Select Meeting Type</option>
                              </select>
                           </div>
                           <div class="col-sm-4">
                              <label for="meetingType2">Meeting Sub-Type:</label>
                              <select class="form-control" id="meetingTypeSubTypeModelId" name="partyMeetingVO.partyMeetingTypeId" >
                                 <option value="4">Select Meeting Type2</option>
                              </select>
                           </div>
                           <div class="col-sm-4">
                              <label for="meetingLevel">Meeting Level:</label>
                              <select class="form-control meetingLevels" id="meetingLevelModelId" name="partyMeetingVO.meetingLevelId">
                                 <option value="selected">Select Meeting Level</option>
                              </select>
                           </div>
                           </div>
                           <h4 class="panel-title" >Location:</h4>
						   <div class="row">
                           <div class="col-sm-4">
                              <label for="state">State:</label>
                              <select class="form-control" id="stateModelId" name="partyMeetingVO.stateId">
                                 <option value="">Select State</option>
                                 <option value="1" selected="selected">Andhra Pradesh</option>
                                 <option value="36">Telangana</option>
                              </select>
                           </div>
                           <div class="col-sm-4">
                              <label for="district">District:</label>
                              <select class="form-control" id="districtModelId" name="partyMeetingVO.districtId">
                                 <option value="0">Select District</option>
                              </select>
                           </div>
                           <div class="col-sm-4">
                              <label for="constituency">Constituency:</label>
                              <select class="form-control" id="constituencyModelId" name="partyMeetingVO.constituencyId">
                                 <option value="0">Select Constituency</option>
                              </select>
                           </div>
                           <div class="col-sm-4">
                              <label for="mandal">Mandal:</label>
                              <select class="form-control" id="mandalModelId" name="partyMeetingVO.tehsilId">
                                 <option value="0">Select Mandal</option>
                              </select>
                           </div>
                           <div class="col-sm-4">
                              <label for="village">Village:</label>
                              <select class="form-control" id="villageModelId" name="partyMeetingVO.villageId">
                                 <option value="selected">Select Village</option>
                              </select>
                           </div>
						   
						   </div>
						 </div>
						   </div>
						
				 <div class="panel panel-default">
         <div class="panel-heading">
		  <div class="row">
		 <div class="col-sm-11">
            <h4 class="panel-title">Tab User Details</h4>
			</div>
			 <div class="col-sm-1">
                  <span id="editTabUserTabButton" data-toggle="collapse" data-target="#editTabUserExpandCollapseId" class="tabUserExpandCollapse">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
         </div>
		 </div>
         <div class="panel-body" id="editTabUserExpandCollapseId">
             <div id="tabUserTableDivId"></div> 
			</div>
			</div>
			
				
		<div class="panel panel-default">
         <div class="panel-heading">
            <div class="row">
               <div class="col-sm-11">
                  <h4 class="panel-title m_top5">Attended Invitiees</h4>
               </div>
               <div class="col-sm-1">
                  <span id="attendedInvitieesTabButton" data-toggle="collapse" data-target="#attendedInvitieesTable" class="attendedInvitieesTabExpandCollapse">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
            </div>
         </div>
         <div class="panel-body" id="attendedInvitieesTable">
         </div>
      </div>
	  
	  
	  <div class="panel panel-default">
         <div class="panel-heading">
            <div class="row">
               <div class="col-sm-11">
                  <h4 class="panel-title m_top5">Non-Attended Invitiees</h4>
               </div>
               <div class="col-sm-1">
                  <span id="nonAttendedInvitieesTabButton" data-toggle="collapse" data-target="#nonAttendedInvitieesTable" class="nonAttendedInvitieesTabExpandCollapse">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
            </div>
         </div>
         <div class="panel-body" id="nonAttendedInvitieesTable">
         </div>
      </div>
				
								 
				<div>
				<div id="meetingInveteeTableId"></div>
               <div class="form-group">
                  <h4 class="panel-title"> ADD INVITEES : </h4>
               </div>
               <div class="form-group">
                  <label for="textArea">Please Enter MemberShip Id's In Comma Separator:</label>
                  <textarea id="editTextAreaId" class="form-control" cols="50" rows="4"></textarea>
               </div>
            </div>
								
                              
						   
						   
                           <div id="tabUserIdDetailsModal"></div>
                          <!--  <div class="col-sm-6">
                              <ul style="list-style-type: none;">
                                 <li  id="genarateModelXmlId" class="btn btn-danger">
                                    Download Invitees
                                 </li>
                                 <li><span class="col-sm-offset-1">
                                    <input type="file" id="partyMeetingId" multiple="multiple"  name="files[]" class="m_top20" required/>
                                    <span>
                                 </li>
                              </ul>
                           </div> -->
						   
				 <div class="form-group">
                 <button type="button" class="btn btn-danger" id="genarateModelXmlId">Download Invitees</button>
               </div>     
              <form name="editPartyMeetingExcelUploadName" id="editPartyMeetingExcelUploadId">			
               <div class="form-group">
                  <input type="file" id="editPartyMeetingId" multiple="multiple"  name="files[]" class="m_top20" required/>
               </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-success" id="editMeetingDetailsSubmitId">Submit</button>
						 </form>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                     </div>
                
               </div>
            </div>
         </div>
      </div>
	  </div>
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





</body>
</html>