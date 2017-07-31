
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
   </head>
   <body>
    <input type="hidden" id="refresh" value="no">
      <div class="container">
         <div class="panel panel-default">
            <div class="panel-heading">
               <h4 class="panel-title">Edit Party Meeting </h4>
            </div>
            <div class="panel-body">
               <div class="row">
                  <div class="col-sm-4">
                     <label for="designation">Meeting Name:</label>
                     <input type="text" class="form-control" id="editMeetingNameId"/>
                  </div>
                  <input type="hidden" value="" id="modalMeetingId"/>
                  <div class="col-sm-4">
                     <label for="startDate">Start Date:</label>
                     <input type="text" class="form-control startDateCls"  id="startDateModelId" />
                  </div>
                  <div class="col-sm-4">
                     <label for="endDate">End Date:</label>
                     <input type="text" class="form-control endDateCls"  id="endDateModelId"/>
                  </div>
                  <div class="col-sm-4">
                     <label for="meetingType">Meeting Main Type:</label>
                     <select class="form-control" id="meetingTypeModelId">
                        <option value="0">Select Meeting Type</option>
                     </select>
                  </div>
                  <div class="col-sm-4">
                     <label for="meetingType2">Meeting Sub-Type:</label>
                     <select class="form-control" id="meetingTypeSubTypeModelId">
                        <option value="4">Select Meeting Type2</option>
                     </select>
                  </div>
                  <div class="col-sm-4">
                     <label for="meetingLevel">Meeting Level:</label>
                     <select class="form-control meetingLevels" id="meetingLevelModelId">
                        <option value="0">Select Meeting Level</option>
                     </select>
                  </div>
               </div>
               <div class="row">
                  <div class="col-sm-12">
                     <h4 class="panel-title" >Location:</h4>
                  </div>
                  <div id="stateModelDivId" class="col-sm-4" style="display:none;">
                     <label for="state">State:</label>
                     <select class="form-control" id="stateModelId">
                        <option value="0">Select State</option>
                        <option value="1">Andhra Pradesh</option>
                        <option value="36">Telangana</option>
                     </select>
                  </div>
                  <div id="districtModelDivId" class="col-sm-4" style="display:none;">
                     <label for="district">District:</label>
                     <select class="form-control" id="districtModelId">
                        <option value="0">Select District</option>
                     </select>
                  </div>
                  <div id="constituencyModelDivId" class="col-sm-4" style="display:none;">
                     <label for="constituency">Constituency:</label>
                     <select class="form-control" id="constituencyModelId">
                        <option value="0">Select Constituency</option>
                     </select>
                  </div>
                  <div id="mandalModelDivId" class="col-sm-4" style="display:none;">
                     <label for="mandal">Mandal:</label>
                     <select class="form-control" id="mandalModelId">
                        <option value="0">Select Mandal</option>
                     </select>
                  </div>
                  <div id="villageModelDivId" class="col-sm-4" style="display:none;">
                     <label for="village">Village:</label>
                     <select class="form-control" id="villageModelId">
                        <option value="0">Select Village</option>
                     </select>
                  </div>
               </div>
            </div>
         </div>
         <div class="panel panel-default">
            <div class="panel-heading" style="background-color:#ffff;margin-color:#ffff;">
               <div class="row">
                  <div class="col-md-10">
                     <h4>Sessions</h4>
                  </div>
                  <div class="col-md-2">
                     <button class='btn btn-primary addButForModel' >Add Sessions 
                     <i class='fa fa-plus' aria-hidden='true'></i>
                     </button>
                  </div>
               </div>
            </div>
            <div class="panel-body" >
               <div id="sessionModelId"></div>
            </div>
         </div>
         <div class="panel panel-default">
            <div class="panel-body">
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
                        <div class="col-sm-9">
                           <h4 class="panel-title m_top5">Attended Invitiees</h4>
                        </div>
						<div class="col-sm-2">
						 <div id="attendanceSessionWiseDivId">
                        <select class="form-control" id="attendanceSessionWiseId">
                        <option value="0">Select Session</option>
                        </select>
                        </div>
						</div>
                        <div class="col-sm-1">
                           <span id="attendedInvitieesTabButton" data-toggle="collapse" data-target="#attendedInvitieesTable" class="attendedInvitieesTabExpandCollapse">
                           <i class="glyphicon glyphicon-plus"></i>
                           </span>
                        </div>
                     </div>
                  </div>
                  <div class="panel-body" id="attendedInvitieesTable">
				   <div id="attendceOfInviteeSpinnerDivId" class="m_top10"></div>
                     <div id="attendceOfInviteeDivId"></div>
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
                     <div id="nonAttendedInvitieesDivId"></div>
                  </div>
               </div>
               <div id="addInviteeDivId">
                  <div id="meetingInveteeTableId"></div>
                  <div class="form-group">
                     <h4 class="panel-title"> ADD INVITEES : </h4>
                  </div>
                  <div class="form-group">
                     <label for="textArea">Please Enter MemberShip Id's In Comma Separator:</label>
                     <textarea id="editTextAreaId" class="form-control" cols="50" rows="4"></textarea>
                  </div>
               </div>
               <div class="form-group">
                  <button type="button" class="btn btn-danger" id="genarateModelXmlId">Download Invitees</button>
               </div>
               <form name="editPartyMeetingExcelUploadName" id="editPartyMeetingExcelUploadId">
                  <div class="form-group">
                     <input type="file" id="editPartyMeetingId" multiple="multiple"  name="files[]" class="m_top20" required/>
                  </div>
                  <div class="form-group pull-right">
                     <div class="col-md-4 col-xs-12 col-sm-4">
                        <button type="button" class="btn btn-primary" id="editMeetingId">Submit Meeting</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
      <!-- modal for invitees-->
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
						<div id="inviterAlredyExistDivId"></div>
                        <div id="tdpCadreDetailsTableDivId"></div>
                        <div id="inveeteenotAvableId"></div>
                        <input type="hidden" id="hiddenPartyMeetingId" value="1" name="partyMeetingVO.partyMeetingId"/>
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

<script type="text/javascript">
    getUrlVars();
var spinner = '<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'
function getUrlVars() {
    var vars = [],
        hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

var meetingId = getUrlVars()["meetingId"];

getAttendanceForMeeting(meetingId,"0");
getPartyMeetingDeatilesForMeetingEdit();

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
            padrtyMeetingEditDetails(results);
            buildInveeteeDetailsTable(results);
            
        }
    });
}

getMeetingMainTypeAction()

function getMeetingMainTypeAction(selId, type) {
    var jsObj = {}
    $.ajax({
        type: "GET",
        url: "getMeetingMainTypeAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (var i in results) {
            $("#meetingTypeId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            $("#meetingTypeModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
        }
        if (type == "model") {
            meetingMainTypeId = $("#meetingTypeModelId").val();
        }
    });
}

function padrtyMeetingEditDetails(results) {
    var type = "model";
    $("#modalMeetingId").val(results[0].id);
    $("#editMeetingNameId").attr('value', results[0].name);
    $("#startDateModelId").attr('value', results[0].conductedDate);
    $("#endDateModelId").attr('value', results[0].updatedTime);
	$("#stateModelId option[value=" + results[0].stateName + "]").attr('selected', 'selected');
    getDistrictsAction(results[0].stateName, type, results[0].districtId);
    $("#meetingLevelModelId option[value=" + results[0].meetingTypeId + "]").attr('selected', 'selected');
    getMeetingLevels($("#meetingLevelModelId").val());
    $("#meetingTypeSubTypeModelId option[value=" + results[0].mandalTwnDivisionId + "]").attr('selected', 'selected');
    $("#meetingTypeModelId option[value=" + results[0].meetingMainTypeId + "]").attr('selected', 'selected');
    var districtId = $("#districtModelId").val();
    $("#constituencyModelId").html('');
    getConstituencyAction(results[0].districtId, type, results[0].constituencyId, results[0].teshilId, results[0].panchayatId);
    var meetingMainTypeId = $("#meetingTypeModelId").val();

    var meetSubSeVar = results[0].mandalTwnDivisionId;
    getMeetingSubTypeAction(meetingMainTypeId, type, meetSubSeVar);
	if(results[0].flage !="true"){
		$("#addInviteeDivId").hide();
		$("#editPartyMeetingId").hide();
		
	}
}

function getMeetingSubTypeAction(mainMeetingTypeId, model, selId) {
    $("#meetingTypeSubTypeModelId").html("");
    $("#meetingTypeSubTypeModelId").append('<option value=' + 0 + '>' + "Select Meeting Sub-Type" + '</option>');
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
        console.log(results)
        for (var i in results) {
            $("#meetingTypeSubTypeModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
        }
        if (model = "model") {
            $("#meetingTypeSubTypeModelId option[value=" + selId + "]").attr('selected', 'selected');
        }

    });
}

$('.startDateCls').datetimepicker({
	format:'YYYY-MM-DD HH:mm:ss'
});
$('.endDateCls').datetimepicker({
	format:'YYYY-MM-DD HH:mm:ss'
});

$('.timeCls').datetimepicker({
    format: 'HH:mm'
});

function buildInveeteeDetailsTable(results) {
    var str = "";
    str += "<table id='inviteeTavbleId' class='table-bordered' style='display:none;'>";
    str += "<thead>";
    str += "<tr>";
    str += "<th>S.NO</th>";
    str += "<th>Name</th>";
    str += "<th>Desiganation</th>";
    str += "<th>Membership No</th>";
    str += "<th>Mobile No</th>";
    str += "</tr>";
    str += "<thead>";
    str += "<tbody>";
    for (var i in results) {
        for (var j in results[i].invetteList) {
            var sNo = parseInt(j) + parseInt(1);
            str += "<tr>";
            str += "<td>" + sNo + "</td>";
            str += "<td >" + results[i].invetteList[j].name + "</td>";
            if (results[i].invetteList[j].partyName != null && results[i].invetteList[j].partyName.length > 0) {
                str += "<td>" + results[i].invetteList[j].partyName + "</td>";
            } else {
                str += "<td>-</td>";
            }
            str += "<td>" + results[i].invetteList[j].membershipNo + "</td>";
            if (results[i].invetteList[j].mobileNumber != null && results[i].invetteList[j].mobileNumber.length > 0) {
                str += "<td>" + results[i].invetteList[j].mobileNumber + "</td>";
            } else {
                str += "<td>-</td>";
            }
            str += "</tr>";
        }
    }
    str += "</tbody>";
    str += "<table>";
    $("#meetingInveteeTableId").html(str);
}

getPartyMeetingLevels();
getPartyMeetingsTabUserNameByDistrict();

function getPartyMeetingsTabUserNameByDistrict() {

    var jsObj = {

    }
    $.ajax({
        type: "GET",
        url: "getPartyMeetingsTabUserNameByDistrictAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        if (results != null) {
            buildTabUserDetails(results, meetingId);
        }

    });
}

function buildTabUserDetails(results, meetingId) {
    var str = '';
    str += "<table id='tabUserTableId' class='table table-bordered tabUserTableCls'>";

    str += "<thead>";
    str += "<tr >";
    str += "<th  class='text-captal text-center'>User Name</th>";
    str += "<th class='text-captal text-center'>Mobile Number</th>";
    str += "<th class='text-captal text-center'>District Name</th>";
    str += "<th class='text-captal text-center'>Check Box</th>";

    str += "</tr>";
    str += "<thead>";
    str += "<tbody>";
    for (var i in results) {
        str += "<tr>";
        str += "<td class='text-center'>" + results[i].meetingName + "</td>";
        str += "<td class='text-center'>" + results[i].constituencyName + "</td>";
        str += "<td class='text-center'>" + results[i].name + "</td>";
        str += "<td  class='text-center'><input type='checkBox' class='tabUserCheckBoxCls' value=" + results[i].id + "></td>";
        str += "</tr>";
    }
    str += "</tbody>";
    str += "</table>";
    $("#tabUserTableDivId").html(str);
    $(".tabUserTableCls").dataTable();
    if (meetingId != null) {
        getPartyMeetingTabUserDetailsAction(meetingId);
    }
}

function getPartyMeetingTabUserDetailsAction(partyMeetingId) {
    var jsObj = {
        partyMeetingId: partyMeetingId
    }
    $.ajax({
        type: "GET",
        url: "getPartyMeetingTabUserDetailsAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (var i in results) {
            $("input[value=" + results[i].id + "]").attr('checked', 'checked');
            $("#tabUserTableDivId").append("<ul class='list-inline'><li>User Name:" + results[i].name + "</li><li>District Name:" + results[i].constituencyName + "</li><li>Mobile Number:" + results[i].remarks + "</li></ul>");
        }
    });

}



function getMeetingLevels(meetingLevelId) {
    if (meetingLevelId == 1 || meetingLevelId == 9) {
        $("#stateModelDivId").show();
        $("#districtModelDivId").hide();
        $("#constituencyModelDivId").hide();
        $("#mandalModelDivId").hide();
        $("#villageModelDivId").hide();
    }
    if (meetingLevelId == 2) {
        $("#stateModelDivId").show();
        $("#districtModelDivId").show();
        $("#constituencyModelDivId").hide();
        $("#mandalModelDivId").hide();
        $("#villageModelDivId").hide();
    }
    if (meetingLevelId == 3) {
        $("#stateModelDivId").show();
        $("#districtModelDivId").show();
        $("#constituencyModelDivId").show();
        $("#mandalModelDivId").hide();
        $("#villageModelDivId").hide();
    }
    if (meetingLevelId == 4 || meetingLevelId == 5 || meetingLevelId == 6) {
        $("#stateModelDivId").show();
        $("#districtModelDivId").show();
        $("#constituencyModelDivId").show();
        $("#mandalModelDivId").show();
        $("#villageModelDivId").hide();
    }
    if (meetingLevelId == 7 || meetingLevelId == 8) {
        $("#stateModelDivId").show();
        $("#districtModelDivId").show();
        $("#constituencyModelDivId").show();
        $("#mandalModelDivId").show();
        $("#villageModelDivId").show();
    }
    if (meetingLevelId == 0) {
        $("#stateModelDivId").hide();
        $("#districtModelDivId").hide();
        $("#constituencyModelDivId").hide();
        $("#mandalModelDivId").hide();
        $("#villageModelDivId").hide();
    }
}

function getConstituencyAction(districtId, editType, id, theshilId, panchayatId) {
    $('#constituencyModelId').html(" ");
    var jsObj = {
        districtId: districtId
    }
    $.ajax({
        type: "GET",
        url: "getConstituenciesForADistrictAjaxAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (var i in results) {
            if (editType == null) {
                $("#constituencyModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            } else {

                $("#constituencyModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
                $("#constituencyModelId option[value=" + id + "]").attr('selected', 'selected');

            }
        }
        if (editType == "model") {
            var constituency = $("#constituencyModelId").val();
            getMandalBasedOnConstituencyAction(constituency, editType, theshilId, panchayatId);

        }
    });
}

function getDistrictsAction(stateId, editType, selectedDistrictId) {
    $("#districtModelId").html("");
    $("#districtModelId").append('<option value=' + 0 + '>' + "Select District" + '</option>');
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
        for (var i in results) {
            if (editType == null) {
                $("#districtModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            } else {
                $("#districtModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
                $("#districtModelId option[value=" + selectedDistrictId + "]").attr('selected', 'selected');
            }
        }
    });
}



$(document).on("change", "#meetingTypeModelId", function() {
    var mainMeetingTypeId = $("#meetingTypeModelId").val();
    getMeetingSubTypeAction(mainMeetingTypeId);

});

$(document).on("change", "#districtModelId", function() {
    var districtId = $('#districtModelId').val();
    getConstituencyAction(districtId);
});

$(document).on("change", "#constituencyModelId", function() {
    var constituencyId = $('#constituencyModelId').val();
    getMandalBasedOnConstituencyAction(constituencyId);
});


function getMandalBasedOnConstituencyAction(constituencyId, editType, theshilId, panchayatId) {
    $('#mandalModelId').html(" ");
    $("#mandalModelId").append('<option value=' + 0 + '>' + "Select Mandal" + '</option>');
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

            $("#mandalModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');

        }
        if (editType == "model") {
            $("#mandalModelId option[value=" + "2"+theshilId + "]").attr('selected', 'selected');
            var mandalId = $("#mandalModelId").val();
            getPanchayatWardByMandalAction(mandalId, constituencyId, editType, panchayatId)
        }
    });
}

$(document).on("change", "#mandalModelId", function() {
    var mandalId = $('#mandalModelId').val();
    getPanchayatWardByMandalAction(mandalId);
});

function getPanchayatWardByMandalAction(mandalId, constituencyId, type, panchayatId) {
    $('#villageModelId').html(" ");
    $("#villageModelId").append('<option value=' + 0 + '>' + "Select Village" + '</option>');
    var jsObj = {
        mandalId: mandalId
    }
    $.ajax({
        type: "GET",
        url: "getPanchayatWardByMandalsAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {

        for (var i in results) {
            $("#villageModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');

        }
        if (type == "model") {
            $("#villageModelId option[value=" + panchayatId + "]").attr('selected', 'selected');
        }
    });
}

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



function getAttendanceForMeeting(meetingId,bydefaultSessionId) {

   // $("#nonAttendedInvitieesDivId").html(' ');
    $("#attendceOfInviteeDivId").html(' ');
	$("#attendceOfInviteeDivId").html(spinner);
    var jsObj = {
        partyMeetingId: meetingId,
		partyMeetingSessionId:bydefaultSessionId
    }
    $.ajax({
        type: "GET",
        url: "getAttendanceForMeetingActoin.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        console.log(results);
        buildAttendaceOfInveiteesTable(results);
        //buildNotAttendacInveiteesTable(results);
        
    });
}
	getNoneAttenedInvettesForMeeting(meetingId,"0");
function getNoneAttenedInvettesForMeeting(meetingId,bydefaultSessionId) {

    $("#nonAttendedInvitieesDivId").html(' ');
    //$("#attendceOfInviteeDivId").html(' ');
    var jsObj = {
        partyMeetingId: meetingId,
		partyMeetingSessionId:bydefaultSessionId
    }
    $.ajax({
        type: "GET",
        url: "getAttendanceForMeetingActoin.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        console.log(results);
       // buildAttendaceOfInveiteesTable(results);
        buildNotAttendacInveiteesTable(results);
       
    });
}
    function buildAttendaceOfInveiteesTable(results) {

        var str = "";
        str += "<table id='attendaceOfInviteeTableId' class='table-bordered'>";
        str += "<thead class='text-capital'>";
        str += "<tr>";
        str += "<th class='text-center'>Image</th>";
        str += "<th class='text-center'>Name</th>";
        str += "<th class='text-center'>Desiganation</th>";
        str += "<th class='text-center'>Membership No</th>";
        str += "<th class='text-center'>Mobile No</th>";
        str += "<th class='text-center'>Comment</th>";
        str += "</tr>";
        str += "<thead>";
        str += "<tbody class='text-center'>";
		if(results != null){
        for (var i in results.attendanceList) {

            str += "<tr>";
            str += "<td><img src='https://www.mytdp.com/images/cadre_images/" + results.attendanceList[i].imagePathStr + "' style='height:50px;width:50px;'></td>";
            str += "<td>" + results.attendanceList[i].name + "</td>";
            str += "<td>" + results.attendanceList[i].partyName + "</td>";
            str += "<td>" + results.attendanceList[i].membershipNo + "</td>";
            //str+="<td>"+results.attendanceList[i].membershipNo+"</td>";
            str += "<td>" + results.attendanceList[i].mobileNumber + "</td>";
            if (results.attendanceList[i].question != null && results.attendanceList[i].question.length > 0) {
                str += "<td>" + results.attendanceList[i].question + "</td>";
            } else {
                str += "<td>-</td>";
            }
            str += "</tr>";
        }
		}
        str += "</tbody>";
        str += "<table>";
        $("#attendceOfInviteeDivId").html(str);
        $("#attendaceOfInviteeTableId").dataTable();
    }


    function buildNotAttendacInveiteesTable(results) {
		//getSessionsForAttendedInviteesByMeetingId(meetingId,"notEmpty","true");
		console.log(seesionResults);
        var str = "";
        str += "<table id='nonAttendedInvitieestableId' class='table-bordered'>";
        str += "<thead class='text-capital'>";
        str += "<tr>";
        str += "<th class='text-center'>Image</th>";
        str += "<th class='text-center'>Name</th>";
        str += "<th class='text-center'>Desiganation</th>";
        str += "<th class='text-center'>Membership No</th>";
        str += "<th class='text-center'>Mobile No</th>";
        str += "<th class='text-center'>Comment</th>";
        str += "<th class='text-captal text-center'>Add Attendance</th>";
		 str += "<th class='text-captal text-center'>Select Session</th>";
        str += "</tr>";
        str += "<thead>";
        str += "<tbody class='text-center'>";
		if(results != null){
        for (var i in results.notAttendanceList) {
            str += "<tr style='font-size:12px;'>";
            str += "<td><img src='https://www.mytdp.com/images/cadre_images/" + results.notAttendanceList[i].imagePathStr + "' style='height:50px;width:50px;'></td>";
            str += "<td>" + results.notAttendanceList[i].name + "</td>";
            str += "<td>" + results.notAttendanceList[i].partyName + "</td>";
            str += "<td>" + results.notAttendanceList[i].membershipNo + "</td>";
            //str+="<td>"+results.notAttendanceList[i].membershipNo+"</td>";
            str += "<td>" + results.notAttendanceList[i].mobileNumber + "</td>";
            str += "<td><input class='nonAttenededComment' type='text' id='" + results.notAttendanceList[i].id + "' placeholder='Enter Comment'/></td>";
	        str += "<td><select class='form-control addAttendanceSessionId'><option value='0'>Select Village</option>";
			for(var j in seesionResults){
           str+="<option value='"+ seesionResults[j].id + "'>" + seesionResults[j].name + "</option>";
		   }
			str+="</select></td>";
		    str += "<td  class='text-center'><input type='checkBox' class='nonattendedCheckBoxCls' value='" + results.notAttendanceList[i].id + "'></td>";

			str += "</tr>";
        }
		}
        str += "</tbody>";
        str += "<table>";
        $("#nonAttendedInvitieesDivId").html(str);
        $("#nonAttendedInvitieestableId").dataTable();
    }

    function buildAttendaceOfNoneInveiteesTable(results) {
        var str = "";
        str += "<table id='attendaceForNoneInviteeTableId' class='table-bordered'>";
        str += "<thead class='text-capital'>";
        str += "<tr>";
        str += "<th class='text-center'>Image</th>";
        str += "<th class='text-center'>Name</th>";
        str += "<th class='text-center'>Desiganation</th>";
        str += "<th class='text-center'>Membership No</th>";
        str += "<th class='text-center'>Mobile No</th>";
        str += "</tr>";
        str += "<thead>";
        str += "<tbody class='text-center'>";
        for (var i in results.nonInviteeAttendancList) {
            str += "<tr>";
            str += "<td><img src='https://www.mytdp.com/images/cadre_images/" + results.nonInviteeAttendancList[i].imagePathStr + "' style='height:50px;width:50px;'></td>";
            str += "<td>" + results.nonInviteeAttendancList[i].name + "</td>";
            str += "<td>" + results.nonInviteeAttendancList[i].partyName + "</td>";
            str += "<td>" + results.nonInviteeAttendancList[i].membershipNo + "</td>";
            //str+="<td>"+results.nonInviteeAttendancList[i].membershipNo+"</td>";
            str += "<td>" + results.nonInviteeAttendancList[i].mobileNumber + "</td>";
            str += "</tr>";
        }
        str += "</tbody>";
        str += "<table>";
        $("#attendceOfNoneInviteeDivId").html(str);
        $("#attendaceForNoneInviteeTableId").dataTable();
    
}



$(document).on("change", "#meetingLevelModelId", function() {
    var meetingLevelId = $('#meetingLevelModelId').val();
    getMeetingLevels(meetingLevelId);
});

$(document).on("change", "#stateModelId", function() {
    var stateId = $('#stateModelId').val();
    getDistrictsAction(stateId);
});
$('#nonAttendedInvitieesTable').hide();
$('#nonAttendedInvitieesTabButton').on("click", function() {
    if ($(this).find("i").hasClass("glyphicon-plus")) {
        $(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
        $('#nonAttendedInvitieesTable').show();

    } else {
        $(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
        $('#nonAttendedInvitieesTable').hide();
    }
});

$('#attendedInvitieesTable').hide();
$('#attendanceSessionWiseDivId').hide();
$('#attendedInvitieesTabButton').on("click", function() {
    if ($(this).find("i").hasClass("glyphicon-plus")) {
        $(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
        $('#attendedInvitieesTable').show();
        $('#attendanceSessionWiseDivId').show();
    } else {
        $(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
        $('#attendedInvitieesTable').hide();
		$('#attendanceSessionWiseDivId').hide();
    }
});


$('#editTabUserExpandCollapseId').hide();
$('#editTabUserTabButton').on("click", function() {
    if ($(this).find("i").hasClass("glyphicon-plus")) {
        $(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
        $('#editTabUserExpandCollapseId').show();

    } else {
        $(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
        $('#editTabUserExpandCollapseId').hide();
    }
});

$(document).on("click", "#genarateModelXmlId", function() {
    generateExcelReport();
});

function generateExcelReport() {
    tableToExcel("inviteeTavbleId", 'Tab user wise Registration Report');
}


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
$(document).on("click", "#editMeetingId", function() {
    $('#validationId').html("");
   var availableSessions=[];
    $('.sessionTypeIdForModel').each(function() {
	   availableSessions.push($('option:selected', $(this)).text());    
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
 $('.startTimeForModel').each(function() {
	 if($(this).val().length > 1){
	     completeStartTime.push($(this).val());	
		 var startArr=$(this).val().split(":");
		 strtTimeHours.push(parseInt(startArr[0]));
		 startTimeMins.push(parseInt(startArr[1]));
		
	 }		 
    });

var completeEndTime=[];
	 $('.endTimeForModel').each(function() {
		  if($(this).val().length > 1){
		 completeEndTime.push($(this).val());
		 var endArr=$(this).val().split(":");
		  endTimeHours.push(parseInt(endArr[0]));
		 endTimeMins.push(parseInt(endArr[1]));
		  }		 
    });

	 var completeLateTime=[];
    $('.lateTimeForModel').each(function() {
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


  if(duplicateSessions.length > 0 ||
	 duplicateStartTime.length > 0 || duplicateEndTime.length>0 || duplicateLateTime.length>0 || errorCount >0){
      $(".validationStatus").modal('show');
	  if(duplicateSessions.length > 0){
      $('#validationId').append("<p>Please Avoid Duplicate Sessions</p><p>"+duplicateSessions+"</p>");
	  }
	 /* if(notValidLateTime.length > 0){
      $('#validationId').append("<p>Late Time should be B/w Start and End Time</p><p>"+notValidLateTime+"</p>");
	  } */
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
	 
	else{
    if ($("#editPartyMeetingId").val().length == 0 && $("#editTextAreaId").val().length == 0) {
  
  var r = 0;
    $(".nonattendedCheckBoxCls:checked").each(function(index, elem) {
	    var sessionId=($(this).closest("td").prev("td").find("select").val());
		var cadreIdWithSessonId=$(elem).val()+","+sessionId;
            var dynamicElement = "<input type='hidden' value='" + cadreIdWithSessonId + "' name='partyMeetingVO.atrPoints[" + r + "]'/> ";
            $('#modalBodyId').append(dynamicElement);
            r = r + 1;
    })
	   var uploadHandler = {
            upload: function(o) {
                uploadResult = o.responseText;
                console.log(uploadResult);
                $(".resultStatus").modal('show');
                $('#submitSuccessId').html("");
                $('#submitSuccessId').append("<p>DATA SAVED SUCESSFULLY</p>");
                setTimeout(function() {
                    $(".resultStatus").modal('hide');
					var redirectWindow=window.open('editPartyMeetingManagementAction.action?meetingId='+meetingId+'','_self');
                }, 3000);


            }
        };  

        $("#tdpCadreDetailsModalId").modal('hide');
        createHiddenFields();
        YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
        YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);
    } else if ($("#editTextAreaId").val().length > 0 && $("#editPartyMeetingId").val().length > 0) {
        $(".resultStatus").modal('show');
        $('#submitSuccessId').html("");
        $('#submitSuccessId').append("<p>Please upload csv file or use TextArea to enter Membership Id's</p>");
        setTimeout(function() {
            $(".resultStatus").modal('hide');
			$("#editTextAreaId").val('');
			$("#editPartyMeetingId").val('');
            //var redirectWindow=window.open('editPartyMeetingManagementAction.action?meetingId='+meetingId+'','_self');
        }, 3000);
    } else if ($("#editPartyMeetingId").val().length > 0) {
        callActionMethod();
    } else if ($("#editTextAreaId").val().length > 0) {
        var textAreaIds = $("#editTextAreaId").val();
        var memebershipIds = textAreaIds.split(",");
        $("#tdpCadreDetailsModalId").modal('show');
    
		getInviteeExistingAndNotExistingOfMeetingDetails(memebershipIds,meetingId);
        createHiddenFields();
    } 
	}

    //}
});
function getInviteeExistingAndNotExistingOfMeetingDetails(memebershipIds,meetingId){
		
		$("#tdpCadreDetailsTableDivId").html('');
		$("#inveeteenotAvableId").html('');
		$("#inviterAlredyExistDivId").html('');
			var jsObj = {
				memberShipNos: memebershipIds,
				meetingId:meetingId
			}
			$.ajax({
				type: "GET",
				url: "getInviteeExistingAndNotExistingOfMeetingDetailsAction.action",
				data: {
					task: JSON.stringify(jsObj)
				}
			}).done(function(results) {
					
					buildInveeteeCreateDatails(results);
			
				
				 
			   if(results.enrollmentYears !=null && results.enrollmentYears.length >0){
				
				   showAlredyExistingInviteemembers(results);
			
			   }
			   if(results.attendanceList[0].enrollmentYears  !=null && results.attendanceList[0].enrollmentYears .length >0){
					showNotExistingTdpCadreMembershipNumbers(results);
			   }
			});
	}

function callActionMethod() {
    var uploadHandler = {
        upload: function(o) {
            uploadResult = o.responseText;

            uploadResult = uploadResult.replace("<pre>", "");
            uploadResult = uploadResult.replace("</pre>", "");
            uploadResult = uploadResult.replace(",\"", "");
            uploadResult = uploadResult.replace("\"", "");
            uploadResult = uploadResult.replace(/\"/g, "");
            uploadResult = uploadResult.replace("<pre style=word-wrap: break-word; white-space: pre-wrap;>", "");
            console.log(uploadResult);
            if (uploadResult == "Please upload csv format") {
                $('#submitSuccessId').html("");
                $(".resultStatus").modal('show');
                $('#submitSuccessId').append("<p>Please upload csv format</p>");
                setTimeout(function() {
                    $(".resultStatus").modal('hide');
					var redirectWindow=window.open('editPartyMeetingManagementAction.action?meetingId='+meetingId+'','_self');
                }, 3000);

            } else {
                $("#tdpCadreDetailsModalId").modal('show');
                var memeberShipArr = uploadResult.split(",");
                getInviteeExistingAndNotExistingOfMeetingDetails(memeberShipArr,meetingId)
                createHiddenFields();
            }
        }

    }
    YAHOO.util.Connect.setForm('editPartyMeetingExcelUploadName', true);
    YAHOO.util.Connect.asyncRequest('POST', 'getMemberShipIdsFromExcelAction.action', uploadHandler);


}



function createHiddenFields() {
    $("#hiddenPartyMeetingId").val(meetingId);
    $("#hiddenMeetingNameId").val($("#editMeetingNameId").val());
    $("#hiddenStartDateId").val($("#startDateModelId").val());
    $("#hiddenEndDateId").val($("#endDateModelId").val());
    $("#hiddenMeetingTypeSubTypeId").val($("#meetingTypeSubTypeModelId").val());
    $("#hiddenMeetingLevelId").val($("#meetingLevelModelId").val());
    $("#hiddenStateId").val($("#stateModelId").val());
    $("#hiddenDistrictId").val($("#districtModelId").val());
    if ($("#constituency").val() == 0) {
        $("#constituencyModelId").val("");
    } else {
        $("#hiddenConstituencyId").val($("#constituencyModelId").val());
    }
	 var trimTeshil=$("#mandalModelId").val();
	 var removedTeshil = trimTeshil.substring(1); 
    $("#hiddenMandalId").val(removedTeshil);
    $("#hiddenvillageId").val($("#villageModelId").val());


    var i = 0;
    var j = 0;
    var k = 0;
    var l = 0;
    $('.startTimeForModel').each(function() {
        var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.startTimeList[" + i + "]'/>";

        $('#modalBodyId').append(dynamicElement);
        i = i + 1;
    });
    $('.endTimeForModel').each(function() {
        var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.endTimeList[" + j + "]'/> ";
        $('#modalBodyId').append(dynamicElement);
        j = j + 1;
    });
    $('.lateTimeForModel').each(function() {
        var dynamicElement = "<input type='hidden' value=" + $(this).val() + " name='partyMeetingVO.lateTimeList[" + k + "]'/> ";

        $('#modalBodyId').append(dynamicElement);
        k = k + 1;

    });
    $('.sessionTypeIdForModel').each(function() {
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
    var p = 0;
    $('.nonAttenededComment').each(function() {
        var cadreIdAndComment = $(this).attr('id') + "," + $(this).val();
        var dynamicElement = "<input type='hidden' value=' " + cadreIdAndComment + " ' name='partyMeetingVO.cadreWithComments[" + p + "]'/>";

        $('#modalBodyId').append(dynamicElement);
        p = p + 1;

    });
}



$(document).on("click", "#savingMeetingWithInviteesID", function() {
    var m = 0;
    $(".checkCls:checked").each(function(index, elem) {
        var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.tdpCadreIds[" + m + "]'/>";
        $('#modalBodyId').append(dynamicElement);
        m = m + 1;
    });
    var u = 0;
    $(".nonattendedCheckBoxCls:checked").each(function(index, elem) {
		 var sessionId=($(this).closest("td").prev("td").find("select").val());
		var cadreIdWithSessonId=$(elem).val()+","+sessionId;
        var dynamicElement = "<input type='hidden' value='" + cadreIdWithSessonId + "' name='partyMeetingVO.atrPoints[" + r + "]'/> ";
        $('#modalBodyId').append(dynamicElement);
        u = u + 1;
    })
    var uploadHandler = {
        upload: function(o) {
            uploadResult = o.responseText;
            console.log(uploadResult);
            $('#closeModalId').click();
            $(".resultStatus").modal('show');
            $('#submitSuccessId').html("");
            $('#submitSuccessId').append("<p>DATA SAVED SUCESSFULLY</p>");
            setTimeout(function() {
                $(".resultStatus").modal('hide');
                var redirectWindow=window.open('editPartyMeetingManagementAction.action?meetingId='+meetingId+'','_self');
            }, 3000);


        }
    };
    YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
    YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);

});




function getTdpCadreDetailsForInveetMeeting(memeberShipArr) {

    $("#tdpCadreDetailsTableDivId").html('');
    $("#inveeteenotAvableId").html('');
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
        if (results != null && results[0].membershipNo != null && results[0].membershipNo.length > 0) {
            buildInveeteeCreateDatails(results);
        }
        if (results[0].enrollmentYears != null && results[0].enrollmentYears.length > 0) {
            showNotExistingTdpCadreMembershipNumbers(results);
        }
    });
}
var cadreTable = '';

function buildInveeteeCreateDatails(results) {
	$("#inviteeTableId").html(" ");
    var str = '';
    str += "<h5 class='pull-right'>Select All<input type='checkBox' id='checkAllCheckBoxId'></h5>";
    str += "<table id='inviteeTableId' class='table table-bordered'>";
    str += "<thead class='text-capital text-center'>";
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
   
	if( results.attendanceList !=null && results.attendanceList.length >0 && results.attendanceList[0].membershipNo !=null && results.attendanceList[0].membershipNo.length > 0){
        for (var i in results.attendanceList) {
		
            str += "<tr>";
			if(results.attendanceList[i].membershipNo !=null && results.attendanceList[i].membershipNo.length >0){
				str += "<td class='text-center'>" + results.attendanceList[i].membershipNo + "</td>";
			}
		
            str += "<td class='text-center'><img src='https://www.mytdp.com/images/cadre_images/" +results.attendanceList[i].imagePathStr + "' style='width:50px;height:50px;' ></img></td>";
			
			
            str += "<td class='text-center'>" + results.attendanceList[i].name + "</td>";
			if(results.attendanceList[i].partyName !=null && results.attendanceList[i].partyName.length >0){
				str += "<td class='text-center'>" + results.attendanceList[i].partyName + "</td>";
			}else{
				   str += "<td class='text-center'>-</td>";
			}
            str += "<td class='text-center'>" + results.attendanceList[i].mobileNumber + "</td>";
            str += "<th class='text-center'><input  class='checkCls' type='checkBox' value=" + results.attendanceList[i].id + " /></th>";
            
			str += "</tr>";
			
        }
    }
    str += "</tbody>";
    str += "</table>";
    $("#tdpCadreDetailsTableDivId").html(str);
    cadreTable = $('#inviteeTableId').dataTable();
	$(".checkCls").prop("checked", true);
	$("#checkAllCheckBoxId").prop("checked", true);

}
function showAlredyExistingInviteemembers(results){
	 var str2 = '';
    str2 += "<div style='margin-top:10px;'><h5>These MemberShip Numbers Are Already Exist </h5></div>";
    str2 += "<div style='margin-top:20px;'><h5>" + results.enrollmentYears + "</h5></div>";
    $("#inviterAlredyExistDivId").html(str2);
}
function showNotExistingTdpCadreMembershipNumbers(results) {

    var str2 = '';
    str2 += "<div style='margin-top:10px;'><h5>These MemberShip Numbers Are Not Exist </h5></div>";
    str2 += "<div style='margin-top:20px;'><h5>" + results.attendanceList[0].enrollmentYears + "</h5></div>";
    $("#inveeteenotAvableId").html(str2);
}
$(document).on("click","#checkAllCheckBoxId", function() {
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
		$("#checkAllCheckBoxId").prop("checked", false);
	}else{
		$("#checkAllCheckBoxId").prop("checked", true);
	}
});



getSessionsForModelByMeetingId(meetingId);

function getSessionsForModelByMeetingId(partyMeetingId) {

    var sessionInModel = " ";


    var selectedSessionModel = [];
    console.log(partyMeetingId);
    var dbIds = [];
    var dynamicId = [];
    var jsObj = {
        partyMeetingId: partyMeetingId
    }
    $.ajax({
        type: "GET",
        url: "getSessionsDetailsByMeetingIdAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        var numberOfSessions = $('.deleteSessionsForModel').length;
        for (i in results) {

            sessionInModel += "<div class='deleteSessionsForModel'><div class='col-sm-4'><label for='session'>Session:</label>" +
                "<select class='form-control sessionTypeIdForModel' id='sessionIdFOrModel" + numberOfSessions + "'><option value='0'>Select Session</option></select></div>" +
                "<div class='col-sm-2'><label for='startDate'>Start Time:</label><input type='text' class='form-control timeCls startTimeForModel' id='startTime'  value=" + results[i].startDateStr + "></div>" +
                "<div class='col-sm-2'><label for='startDate'>End Time:</label><input type='text' class='form-control timeCls endTimeForModel' id='endTime' value=" + results[i].endDateStr + "></div>" +
                "<div class='col-sm-2'><label for='startDate'>Late Time:</label><input type='text' class='form-control timeCls lateTimeForModel' id='lateTime' value=" + results[i].meetingLevelStr + "></div>";

            sessionInModel = sessionInModel + "<div class='col-sm-2 '><button class='btn btn-danger deletSessionBtncls delButForModel' id=" + results[i].id + " style='margin-top:20px;'>Delete Sessions <i class='glyphicon glyphicon-trash' aria-hidden='true'></i></button></div></div>";
            dbIds.push(results[i].id);
            dynamicId.push("sessionIdFOrModel" + numberOfSessions);

            selectedSessionModel.push(results[i].name);
            numberOfSessions = numberOfSessions + 1;;
        }


        $('#sessionModelId').append(sessionInModel);
        //$('.delButForModel').on("click")
        var sessionTypeIdForModel = $('.sessionTypeIdForModel');
        var startTimeForModel = $('.startTimeForModel');
        var endTimeForModel = $('.endTimeForModel');
        var lateTimeForModel = $('.lateTimeForModel');
        var existing = "existing";
        for (var sess in selectedSessionModel) {
            var sessId = [];
            sessId.push(selectedSessionModel[sess]);
            var selecteddbIds = [];
            selecteddbIds.push(dbIds[sess]);
            addSessionsListForModel(dynamicId[sess], sessId, selecteddbIds, existing);
        }
        addDateTimePickerForModel(startTimeForModel, endTimeForModel, lateTimeForModel);

        console.log(results);
    });

}

var seesionResults={};
getSessionsForAttendedInviteesByMeetingId(meetingId);
function getSessionsForAttendedInviteesByMeetingId(partyMeetingId) {
    var jsObj = {
        partyMeetingId: partyMeetingId
		
    }
    $.ajax({
        type: "GET",
        url: "getSessionsDetailsByMeetingIdAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (i in results) {
         $("#attendanceSessionWiseId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
		}
		seesionResults=results;
    });

}

$(document).on("change", "#attendanceSessionWiseId", function() {
    var sessionId = $("#attendanceSessionWiseId").val();
    getAttendanceForMeeting(meetingId,sessionId);

});

function addSessionsListForModel(idModel, dataOfSelected, dbIds, existing) {

        var jsObj = {}
        var selectedId = [];

        $.ajax({
            type: "GET",
            url: "getAllSessionTypeAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {

            //$(idModel).html(" ");

            for (var allSessions in results) {

                for (var existingSession in dataOfSelected) {
                    if (existing == "existing") {

                        if (dataOfSelected[existingSession] == results[allSessions].name) {
                            $("#" + idModel).append('<option value=' + dbIds[0] + '>' + dataOfSelected[0] + '</option>');
                            $("#" + idModel).val(dbIds[0]);
                        } else {
                            $("#" + idModel).append('<option value=' + results[allSessions].id + '>' + results[allSessions].name + '</option>');
                        }
                    }
                }

                if (dataOfSelected[0] == "extraSession") {
                    $("#" + idModel).append('<option value=' + results[allSessions].id + '>' + results[allSessions].name + '</option>');
                }

            }

            if (dataOfSelected[0] == "extraSession") {
            }
        });



    }
  
function addDateTimePickerForModel(stfm, etfm, ltfm) {
    $(stfm).datetimepicker({
        format: 'HH:mm'
    });
    $(etfm).datetimepicker({
        format: 'HH:mm'
    });
    $(ltfm).datetimepicker({
        format: 'HH:mm'
    });

}



$(document).on("click", ".addButForModel", function() {
    console.log($('.deleteSessionsForModel').length);
    var numberOfSessions = $('.deleteSessionsForModel').length;
    if ($('.deleteSessionsForModel').length <= 3) {
        $('#sessionModelId').append("<div class='deleteSessionsForModel'><div class='col-sm-4'><label for='session'>Session:</label>" +
            "<select class='form-control sessionTypeIdForModel' id='sessionIdFOrModel" + numberOfSessions + "'><option value=''>Select Session</option></select></div>" +
            "<div class='col-sm-2'><label for='startTimeForNewEditModel'>Start Time:</label><input type='text' class='form-control timeCls startTimeForModel' id='startTime'  value=' '></div>" +
            "<div class='col-sm-2'><label for='endTimeForNewEditModel'>End Time:</label><input type='text' class='form-control timeCls endTimeForModel' id='endTime' value=' '></div>" +
            "<div class='col-sm-2'><label for='lateTimeForNewEditModel'>Late Time:</label><input type='text' class='form-control timeCls lateTimeForModel' id='lateTime' value=' '></div><div class='col-sm-2 '><button class='btn btn-danger delButForModel' style='margin-top:20px;'>Delete Sessions <i class='glyphicon glyphicon-trash' aria-hidden='true'></i></button></div></div>");
    }
    var startTimeForModel = $('.startTimeForModel');
    var startTimeForModel = $('.startTimeForModel');
    var endTimeForModel = $('.endTimeForModel');
    var lateTimeForModel = $('.lateTimeForModel');
    var sessionTypeIdForNewEditModel = "sessionIdFOrModel" + numberOfSessions;
    var extraSession = [];
    extraSession.push("extraSession");
    addSessionsListForModel(sessionTypeIdForNewEditModel, extraSession);

    addDateTimePickerForModel(startTimeForModel, endTimeForModel, lateTimeForModel);

});
/*
	$(document).on("click", ".delButForModel", function () {
    $(this).parent().parent().remove();
  });*/

$(document).on("click", ".delButForModel", function() {
    var sessionId = $(this).attr("id");
    if (sessionId == null) {
        $(this).parent().parent().remove();
    } else {
        deletesessionconformation(sessionId,this);
    }
});

function deletesessionconformation(meetingSessionId,id) {

    var ask = confirm("Do You want to delete");
    if (ask == true) {
        var jsObj = {
            meetingSessionId: meetingSessionId
        }
        $.ajax({
            type: "GET",
            url: "deletePartyMeetingSessionAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {
            if (results != null) {
				$(id).parent().parent().remove();
				getSessionsForAttendedInviteesByMeetingId(meetingId);
				getAttendanceForMeeting(meetingId,"0");
                getNoneAttenedInvettesForMeeting(meetingId,"0");
            } 

        });
    }
	
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