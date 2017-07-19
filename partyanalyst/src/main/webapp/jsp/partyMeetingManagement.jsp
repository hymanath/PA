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
	  <style>
	  .glyphicon-plus,.glyphicon-minus{
		  cursor:pointer;
	  }
	  </style>
   </head>
   <body>
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
                  <span id="partyMeetingTabButton" data-toggle="collapse" data-target="#partyMeetingTable" class="cadrePartyMeetingTabExpandCollapse">
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
                  <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="3" level_attr="constituencylevel"><a href="#constituencylevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Constituencyl Level</a></li>
                  <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="4" level_attr="mandallevel"><a href="#mandallevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Mandal Level</a></li>
                  <li role="presentation"   class="meetingLevelCls"  meeting_levelId_attr="7" level_attr="villagelevel"><a href="#villagelevelDivId" aria-controls="settings" role="tab" data-toggle="tab">Village Level</a></li>
               </ul>
               <div class="tab-content">
                  <div id="meetingTableId" class="m_top10"></div>
                  <div id="paginationDivId" class="m_top20"></div>
               </div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading">
            <h4 class="panel-title">Create Meeting </h4>
         </div>
         <div class="panel-body">
            <div class="row">
               <div class="col-sm-4">   
                  <label for="designation">Meeting Name:</label>
                  <input type="text" class="form-control" id="meetingName" name="meetingName"/>
               </div>
               <div class="col-sm-4">
                  <label for="startDate">Start Date:</label>
                  <input type="text" class="form-control startDateCls" id="startDate" name="startDate"/>
               </div>
               <div class="col-sm-4">
                  <label for="endDate">End Date:</label>
                  <input type="text" class="form-control endDateCls" id="endDate" name="endDate"/>
               </div>
               <div class="col-sm-4">
                  <label for="meetingType">Meeting Main Type:</label>
                  <select class="form-control" id="meetingTypeId">
                     <option value="">Select Meeting Type</option>
                  </select>
               </div>
               <div class="col-sm-4">
                  <label for="meetingType2">Meeting Sub-Type:</label>
                  <select class="form-control" id="meetingTypeSubTypeId" name="partyMeetingTypeId" >
                     <option value="">Select Meeting Type2</option>
                  </select>
               </div>
               <div class="col-sm-4">
                  <label for="meetingLevel">Meeting Level:</label>
                  <select class="form-control meetingLevels" id="meetingLevelId" name="meetingLevelId" >
                     <option value="0">Select Meeting Level</option>
                  </select>
               </div>
            </div>
            <div class="row" id="locationDivId" style="display:none;">
               <div class="col-sm-12">
                  <h4 class="panel-title" >Location:</h4>
               </div>
               <div id="stateDivId" class="col-sm-4" style="display:none;">
                  <label for="state">State:</label>
                  <select class="form-control" id="state" name="stateId" >
                     <option value="">Select State</option>
                     <option value="1">Andhra Pradesh</option>
                     <option value="36">Telangana</option>
                  </select>
               </div>
               <div id="districtDivId" class="col-sm-4" style="display:none;">
                  <label for="district">District:</label>
                  <span id="districtSpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="district" name="districtId" >
                     <option value="">Select District</option>
                  </select>
               </div>
               <div id="constituencyDivId" class="col-sm-4" style="display:none;">
                  <label for="constituency">Constituency:</label>
                  <span id="constituencySpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="constituency" name="constituencyId" >
                     <option value="">Select Constituency</option>
                  </select>
               </div>
               <div id="mandalDivId" class="col-sm-4" style="display:none;">
                  <label for="mandal">Mandal/ Town/ Division:</label>
                  <span id="mandalSpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="mandal" name="tehsilId">
                     <option value="">Select Mandal</option>
                  </select>
               </div>
               <div id="villageDivId" class="col-sm-4" style="display:none;">
                  <label for="village">Village / Ward:</label>
                  <span id="villageSpinnerId" ><i class="fa fa-spinner fa-spin"></i></span>
                  <select class="form-control" id="village" name="villageId">
                     <option value="">Select Village</option>
                  </select>
               </div>
            </div>
            <div class="row">
               <div class="col-sm-4">
                  <label for="session">Session:</label>
                  <select class="form-control sessionTypeId" id="sessionId">
                     <option value="">Select Session</option>
                  </select>
               </div>
               <div class="col-sm-2">
                  <label for="startDate">Start Time:</label>
                  <input type="text" class="form-control timeCls startTime" id="startTime"  />
               </div>
               <div class="col-sm-2">
                  <label for="startDate">End Time:</label>
                  <input type="text" class="form-control timeCls endTime" id="endTime"/>
               </div>
               <div class="col-sm-2">
                  <label for="startDate">Late Time:</label>
                  <input type="text" class="form-control timeCls lateTime" id="lateTime"/>
               </div>
               <div class="col-sm-2" style="margin-top:20px;">
                  <button type="button" class="btn btn-primary" id="addSession">Add Session
                  <i class="fa fa-plus" aria-hidden="true"></i>
                  </button>
               </div>
            </div>
            <div id="createSession"></div>
         </div>
      </div>
      <div class="panel panel-default">
         <div class="panel-heading">
		  <div class="row">
		 <div class="col-sm-11">
            <h4 class="panel-title">Tab User Details</h4>
			</div>
			 <div class="col-sm-1">
                  <span id="tabUserTabButton" data-toggle="collapse" data-target="#tabUserExpandCollapseId" class="tabUserExpandCollapse">
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
               <!-- style="marging-bottom : 20px;" -->
               <!--
                  <a class="btn btn-danger" href="http://localhost:8080/PartyAnalyst/test.csv">Download Template</a>
                  <form action="downloadInviteesTemplateAction.action" id="downloadInviteees"
                  value=""/> -->
               <div class="form-group">
                  <form action="downloadInviteesTemplateAction.action" method="get">
                     <input type="submit" class="btn btn-danger" value="Download Template">
                  </form>
               </div>
            </div>
            <!--   <button type="button" class="btn btn-danger" id="downloadTemplateId">Download Template</button>  -->
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
                        <div id="tdpCadreDetailsTableDivId"></div>
                        <div id="inveeteenotAvableId"></div>
                        <input type="hidden"  id="hiddenMeetingNameId" value="" name="partyMeetingVO.name"/>
                        <input type="hidden"  id="hiddenStartDateId" value="" name="partyMeetingVO.startDate"/>
                        <input type="hidden"  id="hiddenEndDateId" value="" name="partyMeetingVO.endDate"/>
                        <input type="hidden"  id="hiddenMeetingTypeSubTypeId" value="" name="partyMeetingVO.partyMeetingTypeId"/>
                        <input type="hidden"  id="hiddenMeetingLevelId" value="" name="partyMeetingVO.meetingLevelId"/>
                        <input type="hidden"  id="hiddenStateId" value="" name="partyMeetingVO.stateId"/>
                        <input type="hidden"  id="hiddenDistrictId" value="" name="partyMeetingVO.districtId"/>
                        <input type="hidden"  id="hiddenConstituencyId" value="" name="partyMeetingVO.constituencyId"/>   
                        <input type="hidden"  id="hiddenMandalId" value="" name="partyMeetingVO.tehsilId"/>
                        <input type="hidden"  id="hiddenvillageId" value="" name="partyMeetingVO.villageId"/>
                        <input type="hidden" id="tabuserModelId" value="1" name="partyMeetingVO.insertedById"/> 
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
	  <!-- modal for edit meeting details-->
      <div class="modal fade" id="meetingEditModelId" role="dialog" style="width:100%;">
         <div class="modal-dialog" style="width:80%">
            <div class="container">
               <div class="modal-content">
                  <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                     <h4 class="modal-title">Meeting Details</h4>
                  </div>
                  <form name="editMeetingDetailsSubmitFormName" id="editMeetingDetailsSubmitFormId">
                     <div class="modal-body">
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
						   <!-- <div id="sessionModelId"></div> -->
						   
						   
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
             <div id="tabUserEditMeetingTableDivId"></div> 
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
			<div id="attendceOfInviteeDivId"></div>
         </div>
      </div>
	<!--  <div class="panel panel-default">
         <div class="panel-heading">
            <div class="row">
               <div class="col-sm-11">
                  <h4 class="panel-title m_top5">Attended None-Invitiees</h4>
				  
               </div>
               <div class="col-sm-1">
                  <span id="attendedNoneInvitieesTabButton" data-toggle="collapse" data-target="#attendedNoneInvitieesTable" class="attendedNoneInvitieesTabExpandCollapse">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
            </div>
         </div>
         <div class="panel-body" id="attendedNoneInvitieesTable">
			<div id="attendceOfNoneInviteeDivId"></div>
         </div>
      </div>    -->
	  
	  
	  <div class="panel panel-default">
         <div class="panel-heading">
            <div class="row">
               <div class="col-sm-11">
                  <h4 class="panel-title m_top5">Not-Attended Invitiees</h4>
               </div>
               <div class="col-sm-1">
                  <span id="nonAttendedInvitieesTabButton" data-toggle="collapse" data-target="#nonAttendedInvitieesTable" class="nonAttendedInvitieesTabExpandCollapse">
                  <i class="glyphicon glyphicon-plus"></i>
                  </span>
               </div>
            </div>
         </div>
         <div class="panel-body" id="nonAttendedInvitieesTable">
			<div id="nonAttendedInvitieesDivId"></div>
         </div>
      </div>
				
								 
				<div>
				<div id="meetingInveteeTableId"></div>
               <div class="form-group">
                  <h4 class="panel-title"> ADD INVITEES : </h4>
               </div>
               <div class="form-group">
                  <label for="textArea">Please Enter MemberShip Id's In Comma Separator:</label>
                  <textarea id="textAreaId" class="form-control" cols="50" rows="4"></textarea>
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
               <div class="form-group">
                  <input type="file" id="editPartyMeetingIdUpload" multiple="multiple"  name="files[]" class="m_top20" required/>
               </div>
						   
                        
                     
                     <div class="modal-footer">
                        <button type="button" class="btn btn-success" id="editMeetingDetailsSubmitId">Submit</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                     </div>
                  </form>
               </div>
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
<script type="text/javascript" src="partyMeetingManagement/js/createPartyMeeting.js"></script>
<script type="text/javascript">
    $('#constituencySpinnerId').hide();
    $('#mandalSpinnerId').hide();
    $('#villageSpinnerId').hide();
    $('#districtSpinnerId').hide();





    $(document).on("click", "#applicatioSubmitId", function() {

        console.log(memebershipIds);

        console.log($("#partyMeetingId").val().length);
        if ($("#textAreaId").val().length > 0 && $("#partyMeetingId").val().length > 0) {
            $(".resultStatus").modal('show');
            $('#submitSuccessId').html("");
            $('#submitSuccessId').append("<p>Please upload csv file or use TextArea to enter Membership Id's</p>");
            setTimeout(function() {
                $(".resultStatus").modal('hide');
            }, 3000);
        } else if ($("#partyMeetingId").val().length > 0) {
            callActionMethod();
        } else if ($("#textAreaId").val().length > 0) {
            alert("text");
            var textAreaIds = $("#textAreaId").val();
            var memebershipIds = textAreaIds.split(",");
            $("#tdpCadreDetailsModalId").modal('show');
            getTdpCadreDetailsForInveetMeeting(memebershipIds);
            createHiddenFields();
        } else {

        }

        //}
    });

    function callActionMethod() {
        var uploadHandler = {
            upload: function(o) {
                uploadResult = o.responseText;

                uploadResult = uploadResult.replace("<pre>", "");
                uploadResult = uploadResult.replace("</pre>", "");
                uploadResult = uploadResult.replace(",\"", "");
                uploadResult = uploadResult.replace("\"", "");
                //uploadResult = uploadResult.replace(",\"","");
                uploadResult = uploadResult.replace(/\"/g, "");
                //uploadResult = uploadResult.replace(/\"/g, "");
                uploadResult = uploadResult.replace("<pre style=word-wrap: break-word; white-space: pre-wrap;>", "");
                console.log(uploadResult);
                if (uploadResult == "Please upload csv format") {
                    $('#submitSuccessId').html("");
                    $(".resultStatus").modal('show');
                    $('#submitSuccessId').append("<p>Please upload csv format</p>");
                    setTimeout(function() {
                        $(".resultStatus").modal('hide');
                    }, 3000);

                } else {
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
        $("#hiddenStartDateId").val($("#startDate").val());
        $("#hiddenEndDateId").val($("#endDate").val());
        $("#hiddenMeetingTypeSubTypeId").val($("#meetingTypeSubTypeId").val());
        $("#hiddenMeetingLevelId").val($("#meetingLevelId").val());
        $("#hiddenStateId").val($("#state").val());
        $("#hiddenDistrictId").val($("#district").val());
        if ($("#constituency").val() == 0) {
            $("#hiddenConstituencyId").val("");
        } else {
            $("#hiddenConstituencyId").val($("#constituency").val());
        }
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

            $('#modalBodyId').append(dynamicElement);
            i = i + 1;
        });
        $('.endTime').each(function() {
            var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.endTimeList[" + j + "]'/> ";
            $('#modalBodyId').append(dynamicElement);
            j = j + 1;
        });
        $('.lateTime').each(function() {
            var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.lateTimeList[" + k + "]'/> ";

            $('#modalBodyId').append(dynamicElement);
            k = k + 1;

        });
        $('.sessionTypeId').each(function() {
            var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.sessionTypeId[" + l + "]'/> ";
            $('#modalBodyId').append(dynamicElement);

            l = l + 1;
        });

        var tabUserIdArr = [];
        var n = 0;
        $(".tabUserCheckBoxCls:checked").each(function(index, elem) {
            var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.attendedList[" + n + "]'/> ";
            $('#modalBodyId').append(dynamicElement);
            n = n + 1;
        })
    }

    function getTdpCadreDetailsForInveetMeeting(memeberShipArr) {


        var jsObj = {
            memberShipNos: memeberShipArr
        }
        $.ajax({
            type: "GET",
            url: "getTdpCadreDetailsForInveetMeetingAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {
            if (results != null) {
                buildInveeteeCreateDatails(results);

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
        for (var i in results) {
            str += "<tr>";
            str += "<td>" + results[i].membershipNo + "</td>";
            str += "<td><img src='https://www.mytdp.com/images/cadre_images/" + results[i].imagePathStr + "' style='width:50px;height:50px;' ></img></td>";
            str += "<td>" + results[i].name + "</td>";
            str += "<td>" + results[i].partyName + "</td>";
            str += "<td>" + results[i].mobileNumber + "</td>";
            str += "<th><input  class='checkCls' type='checkBox' value=" + results[i].id + " /></th>";
            str += "</tr>";
        }
        str += "</tbody>";
        str += "</table>";
        $("#tdpCadreDetailsTableDivId").html(str);
        cadreTable = $('#inviteeTableId').dataTable();
        for (var i in results[0].enrollmentYears) {
            var str2 = '';
            str2 += "<h5>" + results[0].enrollmentYears + "</h5>"
            $("#inveeteenotAvableId").html(str2);
        }
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

    $(document).on("click", "#editMeetingDetailsSubmitId", function() {

        var uploadHandler = {
            upload: function(o) {
                uploadResult = o.responseText;
            }
        };

        YAHOO.util.Connect.setForm('editMeetingDetailsSubmitFormName', true);
        YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsWithExcelAction.action', uploadHandler);

    });

    $(document).on("click", ".checkAllCheckBoxCls", function() {
        if ($(this).is(":checked")) {
            $(".checkCls").prop("checked", true);
        } else {
            $(".checkCls").prop("checked", false);
        }
    });

    $(document).on("click", "#savingMeetingWithInviteesID", function() {

        caderIdArr = [];
        var dataOfServerResponse;
        var m = 0;
        $(".checkCls:checked").each(function(index, elem) {
            var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.tdpCadreIds[" + m + "]'/>";
            $('#modalBodyId').append(dynamicElement);
            m = m + 1;
        });

        var uploadHandler = {
            upload: function(o) {
                uploadResult = o.responseText;
                console.log(uploadResult);
                $('#closeModalId').click();
                $(".resultStatus").modal('show');
                $('#submitSuccessId').append("<p>DATA SAVED SUCESSFULLY</p>");
                setTimeout(function() {
                    $(".resultStatus").modal('hide');
                }, 3000);


            }
        };
        console.log(uploadHandler);
        YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
        YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);

    });
	
	$(document).on("click", "#genarateModelXmlId", function() {
    generateExcelReport();
});

    function generateExcelReport() {
        tableToExcel("inviteeTavbleId", 'Tab user wise Registration Report');
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
