
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
	<style>
	 .fa-plus-square-o,.fa-minus-square-o{
	cursor:pointer;
}
</style>
   </head>
   <body>
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
                  <option value="0">Select Meeting Type2</option>
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
                  <option value="">Select State</option>
                  <option value="1" selected="selected">Andhra Pradesh</option>
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
		 <div class="row">
			<div class="panel panel-default">
				<div class="panel-heading" style="background-color:#ffff;margin-color:#ffff;">
				<div class="row">
					<div class="col-md-10"><h4>Sessions</h4></div>
					<div class="col-md-2">
						<button class='btn btn-primary addButForModel' >Add Sessions 
						<i class='fa fa-plus' aria-hidden='true'></i>
						</button>
						</div>
					
					</div>
				</div>
				<div class="panel-body" id="sessionPanelBodyId"  >
					<div id="sessionModelId"></div>
				</div>
		 </div>
		 </div>
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
                   <h3>  <span id="editTabUserTabButton" data-toggle="collapse" data-target="#editTabUserExpandCollapseId" class="tabUserExpandCollapse">
                     <i class="fa fa-plus-square-o"></i>
                     </span></h3>
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
                     <h3><span id="attendedInvitieesTabButton" data-toggle="collapse" data-target="#attendedInvitieesTable" class="attendedInvitieesTabExpandCollapse">
                     <i class="fa fa-plus-square-o"></i>
                     </span></h3>
                  </div>
               </div>
            </div>
            <div class="panel-body" id="attendedInvitieesTable">
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
                     <h3><span id="nonAttendedInvitieesTabButton" data-toggle="collapse" data-target="#nonAttendedInvitieesTable" class="nonAttendedInvitieesTabExpandCollapse">
                     <i class="fa fa-plus-square-o"></i>
                     </span></h3>
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
                  <button type="button" class="btn btn-primary" id="editMeetingId">Edit Meeting</button>
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
    getAttendanceForMeeting(meetingId);
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
                inviteeDetails(results);
                buildInveeteeDetailsTable(results);
                //buildTabUserDetails(results, meetingId);
                // getPartyMeetingTabUserDetailsAction(meetingId)
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
            //getMeetingSubTypeAction(meetingMainTypeId, selId, "model");
        }
    });
}		

	  function inviteeDetails(results) {
        var type = "model";
        $("#modalMeetingId").val(results[0].id);
        $("#editMeetingNameId").attr('value', results[0].name);
        $("#startDateModelId").attr('value', results[0].conductedDate);
        $("#endDateModelId").attr('value', results[0].updatedTime);
        getDistrictsAction(1, type, results[0].districtId);
        $("#meetingLevelModelId option[value=" + results[0].meetingTypeId + "]").attr('selected', 'selected');
		getMeetingLevels($("#meetingLevelModelId").val());
        $("#meetingTypeSubTypeModelId option[value=" + results[0].mandalTwnDivisionId + "]").attr('selected', 'selected');
        $("#meetingTypeModelId option[value=" + results[0].meetingMainTypeId + "]").attr('selected', 'selected');
        var districtId = $("#districtModelId").val();
        $("#constituencyModelId").html('');
        getConstituencyAction(districtId, type, results[0].constituencyId, results[0].teshilId, results[0].panchayatId);
        var meetingMainTypeId = $("#meetingTypeModelId").val();

        var meetSubSeVar = results[0].mandalTwnDivisionId;
        getMeetingSubTypeAction(meetingMainTypeId, type, meetSubSeVar);
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
            });
    $('.endDateCls').datetimepicker({
    });

    $('.timeCls').datetimepicker({
    format: 'HH:mm'
    });

    function buildInveeteeDetailsTable(results) {
        var str = "";
        str += "<table id='inviteeTavbleId' class='table-bordered'>";
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
				if(results[i].invetteList[j].partyName !=null && results[i].invetteList[j].partyName.length >0){
					str += "<td>" + results[i].invetteList[j].partyName + "</td>";
				}else{
					 str += "<td>-</td>";
				}
                str += "<td>" + results[i].invetteList[j].membershipNo + "</td>";
				if(results[i].invetteList[j].mobileNumber !=null && results[i].invetteList[j].mobileNumber.length >0){
					str += "<td>" + results[i].invetteList[j].mobileNumber + "</td>";
				}else{
					 str += "<td>-</td>";
				}
                str += "</tr>";
            }
        }
        str += "</tbody>";
        str += "<table>";
        $("#meetingInveteeTableId").html(str);
        $("#meetingInveteeTableId").hide();
        $("#inviteeTavbleId").hide();
        //$("#inviteeTavbleId").dataTable(); 

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
                /*
			for (var i in results) {
			
                if (model == null) {
                    $('#tabusermobilenumberId').html("  " + results[i].constituencyName);
                    $('#tabuserDistrictId').html("  " + results[i].name);  
                } else { 
                    $('#tabusermobilenumberModelId').html("  " + results[i].constituencyName);
                    $('#tabuserDistrictModelId').html("  " + results[i].name);
                }

            }*/
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
        //$("#tabUserEditMeetingTableDivId").html(str);
        // $("#tabUserIdDetailsModal").html(str);
        $(".tabUserTableCls").dataTable();
        if (meetingId != null) {
            getPartyMeetingTabUserDetailsAction(meetingId);
        }
    }

    function getPartyMeetingTabUserDetailsAction(partyMeetingId) {
        //$("#tabUserDetailsModelDivId").html('');
        //alert(partyMeetingId);
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

  
	
function getMeetingLevels(meetingLevelId){
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
        $("#districtModelId").show();
        $("#constituencyModelDivId").show();
        $("#mandalModelDivId").hide();
        $("#villageModelDivId").hide();
    }
    if (meetingLevelId == 4 || meetingLevelId == 5 || meetingLevelId == 6) {
        $("#stateModelDivId").show();
        $("#districtModelId").show();
        $("#constituencyModelDivId").show();
        $("#mandalModelDivId").show();
        $("#villageModelDivId").hide();
    }
    if (meetingLevelId == 7 || meetingLevelId == 8) {
        $("#stateModelDivId").show();
        $("#districtModelId").show();
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

 function getDistrictsAction(stateId, model, id) {
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
                if (model == null) {
                    $("#districtModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
                } else {
                    $("#districtModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
                    $("#districtModelId option[value=" + id + "]").attr('selected', 'selected');
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
    //getPartyMeetingsTabUserNameByDistrict(districtId);
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
                $("#mandalModelId option[value=" + theshilId + "]").attr('selected', 'selected');
                var mandalId = $("#mandalModelId").val();
                getPanchayatWardByMandalAction(mandalId, constituencyId, editType, panchayatId)
            }
        });
    }
	$(document).on("change", "#constituencyModelId", function() {
    var constituencyId = $('#constituencyModelId').val();
    getMandalBasedOnConstituencyAction(constituencyId);
});

$(document).on("change", "#mandalModelId", function() {
    var mandalId = $('#mandalModelId').val();
    getPanchayatWardByMandalAction(mandalId);
});

   function getPanchayatWardByMandalAction(mandalId,  type, panchayatId) {
        $('#villageModelId').html(" ");
		$("#villageModelId").append('<option value=' + 0 + '>' + "Select Village" + '</option>');
        var jsObj = {
            mandalId: mandalId
        }
        $.ajax({
            type: "GET",
            url: "getPanchayatWardByMandalsAction.action",
            data: {task: JSON.stringify(jsObj)}
        }).done(function(results) {
			if(results !=null){
				for (var i in results) {
					$("#villageModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
				}
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



    function getAttendanceForMeeting(meetingId) {
		
        $("#nonAttendedInvitieesDivId").html(' ');
        $("#attendceOfInviteeDivId").html(' ');
        var jsObj = {
            partyMeetingId: meetingId
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
            buildNotAttendacInveiteesTable(results);
            //buildAttendaceOfNoneInveiteesTable(results);

            for (i in results) {

            }
        });

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
            for (var i in results.attendanceList) {
				
                str += "<tr>";
                str += "<td><img src='https://www.mytdp.com/images/cadre_images/" + results.attendanceList[i].imagePathStr + "' style='height:50px;width:50px;'></td>";
                str += "<td>" + results.attendanceList[i].name + "</td>";
				if(results.attendanceList[i].partyName !=null && results.attendanceList[i].partyName.length >0){
					str += "<td>" + results.attendanceList[i].partyName + "</td>";
				}else{
					 str += "<td>-</td>";
				}
				if(results.attendanceList[i].membershipNo !=null && results.attendanceList[i].membershipNo.length >0){
					str += "<td>" + results.attendanceList[i].membershipNo + "</td>";
				}else{
					 str += "<td>-</td>";
				}
                //str+="<td>"+results.attendanceList[i].membershipNo+"</td>";
                str += "<td>" + results.attendanceList[i].mobileNumber + "</td>";
				if(results.attendanceList[i].question !=null && results.attendanceList[i].question.length >0){
					str += "<td>" + results.attendanceList[i].question + "</td>";
				}else{
					str+="<td>-</td>";
				}
                str += "</tr>";
            }
            str += "</tbody>";
            str += "<table>";
            $("#attendceOfInviteeDivId").html(str);
            $("#attendaceOfInviteeTableId").dataTable();
        }


        function buildNotAttendacInveiteesTable(results) {
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
            str += "</tr>";
            str += "<thead>";
            str += "<tbody class='text-center'>";
            for (var i in results.notAttendanceList) {
                str += "<tr>";
                str += "<td><img src='https://www.mytdp.com/images/cadre_images/" + results.notAttendanceList[i].imagePathStr + "' alter='No Image' style='height:50px;width:50px;'></td>";
                str += "<td>" + results.notAttendanceList[i].name + "</td>";
				if(results.notAttendanceList[i].partyName !=null && results.notAttendanceList[i].partyName.length >0){
					str += "<td>" + results.notAttendanceList[i].partyName + "</td>";
				}else{
					str+="<td>-</td>";
				}
				if(results.notAttendanceList[i].membershipNo !=null && results.notAttendanceList[i].membershipNo.length >0){
					str += "<td>" + results.notAttendanceList[i].membershipNo + "</td>";
				}else{
					str+="<td>-</td>";
				}
				if(results.notAttendanceList[i].mobileNumber !=null && results.notAttendanceList[i].mobileNumber.length >0){
					str += "<td>" + results.notAttendanceList[i].mobileNumber + "</td>";
				}else{
					str+="<td>-</td>";
				}
                str += "<td><input class='nonAttenededComment' type='text' id='" + results.notAttendanceList[i].id + "' placeholder='Enter Comment'/></td>";
			    str += "<td  class='text-center'><input type='checkBox' class='nonattendedCheckBoxCls' value='" + results.notAttendanceList[i].id + "'></td>";
                str += "</tr>";
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
        if ($(this).find("i").hasClass("fa-plus-square-o")) {
            $(this).find("i").removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
            $('#nonAttendedInvitieesTable').show();

        } else {
            $(this).find("i").addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
            $('#nonAttendedInvitieesTable').hide();
        }
    });

    $('#attendedInvitieesTable').hide();
    $('#attendedInvitieesTabButton').on("click", function() {
        if ($(this).find("i").hasClass("fa-plus-square-o")) {
            $(this).find("i").removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
            $('#attendedInvitieesTable').show();

        } else {
            $(this).find("i").addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
            $('#attendedInvitieesTable').hide();
        }
    });


    $('#editTabUserExpandCollapseId').hide();
    $('#editTabUserTabButton').on("click", function() {
        if ($(this).find("i").hasClass("fa-plus-square-o")) {
            $(this).find("i").removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
            $('#editTabUserExpandCollapseId').show();

        } else {
            $(this).find("i").addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
            $('#editTabUserExpandCollapseId').hide();
        }
    });

    $(document).on("click", "#genarateModelXmlId", function() {
        generateExcelReport();
    });

    function generateExcelReport() {
        tableToExcel("inviteeTavbleId", 'Tab user wise Registration Report');
    }




    $(document).on("click", "#editMeetingId", function() {
		//alert($('.nonAttenededComment').val().length);
		var q=0;
		 var cadreIdAndComment="";
		$('.nonAttenededComment').each(function() {
           cadreIdAndComment+=$(this).attr('id')+","+$(this).val();
		   if(cadreIdAndComment.length>0 && $("#editPartyMeetingId").val().length == 0 && $("#editTextAreaId").val().length == 0){
		   var dynamicElement = "<input type='hidden' value=" + $(this).attr('id') + " name='partyMeetingVO.tdpCadreIds[" + q + "]'/>";
            $('#modalBodyId').append(dynamicElement);
            q = q + 1;
		   }
        });	
		var r=0;
		 var nonattendedChecked="";
		$(".nonattendedCheckBoxCls:checked").each(function(index, elem) {
			nonattendedChecked+=$(elem).val();
			if(nonattendedChecked.length>0 && $("#editPartyMeetingId").val().length == 0 && $("#editTextAreaId").val().length == 0){
            var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.atrPoints[" + r + "]'/> ";
            $('#modalBodyId').append(dynamicElement);
            r = r + 1;
			}
        })
	   if(cadreIdAndComment.length > 0 && $("#editPartyMeetingId").val().length == 0 && $("#editTextAreaId").val().length == 0)
	   {
		   alert(123)
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
                }, 3000);


            }
        };
			
        $("#tdpCadreDetailsModalId").modal('hide');
		 createHiddenFields();
        YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
        YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);
	   }
        else if ($("#editTextAreaId").val().length > 0 && $("#editPartyMeetingId").val().length > 0) {
            $(".resultStatus").modal('show');
            $('#submitSuccessId').html("");
            $('#submitSuccessId').append("<p>Please upload csv file or use TextArea to enter Membership Id's</p>");
            setTimeout(function() {
                $(".resultStatus").modal('hide');
            }, 3000);
        } else if ($("#editPartyMeetingId").val().length > 0) {
            callActionMethod();
        } else if ($("#editTextAreaId").val().length > 0) {
            var textAreaIds = $("#editTextAreaId").val();
            var memebershipIds = textAreaIds.split(",");
            $("#tdpCadreDetailsModalId").modal('show');
            getTdpCadreDetailsForInveetMeeting(memebershipIds);
            createHiddenFields();
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
        YAHOO.util.Connect.setForm('editPartyMeetingExcelUploadName', true);
        YAHOO.util.Connect.asyncRequest('POST', 'getMemberShipIdsFromExcelAction.action', uploadHandler);


    }



    function createHiddenFields() {
		$("#hiddenPartyMeetingId").val(meetingId);
        $("#hiddenMeetingNameId").val($("#editMeetingNameId").val());
		/*var startDate=$("#startDateModelId").val().split(".0");
		var endDate=$("#endDateModelId").val().split(".0");
		var stDate="";
		for(i in startDate){
			stDate+=startDate[i];
		}
	     stDate=stDate.replace("-", "/");
		 stDate=stDate.replace("-", "/");
		 stDate=stDate.replace("-", "/");
		 
		var enDate="";
		for(i in endDate){
		  enDate+=endDate[i];
			
		}
		enDate=enDate.replace("-", "/");
		enDate=enDate.replace("-", "/");
		enDate=enDate.replace("-", "/");
		 
		
        $("#hiddenStartDateId").val(stDate);
        $("#hiddenEndDateId").val(enDate);*/
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
        $("#hiddenMandalId").val($("#mandalModelId").val());
        $("#hiddenvillageId").val($("#villageModelId").val());



        var startTime = [];
        var endTime = [];
        var lateTime = [];
        var sessionTypeId = [];

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
		var p=0;
		$('.nonAttenededComment').each(function() {
           var cadreIdAndComment=$(this).attr('id')+","+$(this).val();
		   if(cadreIdAndComment.length>10){
		    var dynamicElement = "<input type='hidden' value=" + cadreIdAndComment + " name='partyMeetingVO.cadreWithComments[" + p + "]'/>";

            $('#modalBodyId').append(dynamicElement);
            p = p + 1;
		   }
        });	
    }


	
	 $(document).on("click", "#savingMeetingWithInviteesID", function() {
        var m = 0;
        $(".checkCls:checked").each(function(index, elem) {
            var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.tdpCadreIds[" + m + "]'/>";
            $('#modalBodyId').append(dynamicElement);
            m = m + 1;
        });
		var u=0;
        $(".nonattendedCheckBoxCls:checked").each(function(index, elem) {
            var dynamicElement = "<input type='hidden' value=" + $(elem).val() + " name='partyMeetingVO.atrPoints[" + u + "]'/> ";
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
                }, 3000);


            }
        };
        YAHOO.util.Connect.setForm('meetingDetailsWithInviteesSubmit', true);
        YAHOO.util.Connect.asyncRequest('POST', 'savepartymeetingDetailsAction.action', uploadHandler);

    });
	
	
	
	
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
	
	
	
	
	
	getSessionsForModelByMeetingId(meetingId);
function getSessionsForModelByMeetingId(partyMeetingId) {
    
    var sessionInModel=" ";
    
    
var selectedSessionModel=[];
    console.log(partyMeetingId);
    
    var jsObj = {
      partyMeetingId:partyMeetingId
    }
    $.ajax({
      type: "GET",
      url: "getSessionsDetailsByMeetingIdAction.action",
      data: {
        task: JSON.stringify(jsObj)
      }
    }).done(function (results) {
      for (i in results) {
		  
        
        sessionInModel+="<div class='deleteSessionsForModel'><div class='col-sm-4'><label for='session'>Session:</label>"+
"<select class='form-control sessionTypeIdForModel' id='sessionIdFOrModel'><option value='0'>Select Session</option></select></div>"+
"<div class='col-sm-2'><label for='startDate'>Start Time:</label><input type='text' class='form-control timeCls startTimeForModel' id='startTime'  value="+results[i].startDateStr+"></div>"+
"<div class='col-sm-2'><label for='startDate'>End Time:</label><input type='text' class='form-control timeCls endTimeForModel' id='endTime' value="+results[i].endDateStr+"></div>"+
"<div class='col-sm-2'><label for='startDate'>Late Time:</label><input type='text' class='form-control timeCls lateTimeForModel' id='lateTime' value="+results[i].meetingLevelStr+"></div>";

  sessionInModel=sessionInModel+"<div class='col-sm-2 '><button class='btn btn-danger deletSessionBtncls delButForModel' id="+results[i].id+" style='margin-top:20px;'>Delete Sessions <i class='glyphicon glyphicon-trash' aria-hidden='true'></i></button></div></div>";
/*
if(i==0)
{
  sessionInModel=sessionInModel+"<div class='col-sm-2'><button class='btn btn-primary addButForModel' style='margin-top:20px;'>Add Sessions <i class='fa fa-plus' aria-hidden='true'></i></button></div></div>";
}
else if(i>=0){
  sessionInModel=sessionInModel+"<div class='col-sm-2 '><button class='btn btn-danger deletSessionBtncls delButForModel' id="+results[i].id+" style='margin-top:20px;'>Delete Sessions <i class='glyphicon glyphicon-trash' aria-hidden='true'></i></button></div></div>";
   
}      */
        selectedSessionModel.push(results[i].name);
      }
      
      
      $('#sessionModelId').append(sessionInModel);
      $('.delButForModel').on("click")
      var sessionTypeIdForModel=$('.sessionTypeIdForModel');
      var startTimeForModel=$('.startTimeForModel');
      var endTimeForModel=$('.endTimeForModel');
      var lateTimeForModel=$('.lateTimeForModel');
      addSessionsListForModel(sessionTypeIdForModel,selectedSessionModel);
      addDateTimePickerForModel(startTimeForModel,endTimeForModel,lateTimeForModel);
      
      console.log(results);
    });
    
  }


function addSessionsListForModel(idModel,dataOfSelected){
	
    var jsObj = {}
    var selectedId=[];

    $.ajax({
		  type: "GET",
		  url: "getAllSessionTypeAction.action",
		  data: {
			task: JSON.stringify(jsObj)
		}
    }).done(function (results) {

       for (var i in results) {
			for (var j in dataOfSelected){
				if(dataOfSelected[j]==results[i].name){
					selectedId.push(results[i].id);
				}
			}
				if(dataOfSelected[0]=="extraSession"){
					$(idModel).append('<option value=' + results[i].id +'>' + results[i].name + '</option>');
				}else {
					$(idModel).append('<option value=' + results[i].id +'>' + results[i].name + '</option>');
				}
		}
		
        if(dataOfSelected[0]=="extraSession"){
			$(idModel).val(0);
		} else{
			var index=0;
			$(idModel).each(function(){
				$(this).val(selectedId[index])
				index++;
			});
		}
 
      });
    
    
    
  }
  //delet sessions in editing meetings
  $(document).on("click",".deletSessionBtncls",function(){
	  var meetingSessionId=$(this).attr("id");
	  if(meetingSessionId !=null && meetingSessionId >0){
		deletePartyMeetingSession(meetingSessionId);
	  }
  });
    function deletePartyMeetingSession(meetingSessionId) {

alert(meetingSessionId);
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
         alert(results.message);

            }
        });
    }


  function addDateTimePickerForModel(stfm,etfm,ltfm)
  {
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
  
  
  
  $(document).on("click",".addButForModel", function () {
		console.log($('.deleteSessionsForModel').length);
		//$("#sessionPanelBodyId").show();
		var numberOfSessions=$('.deleteSessionsForModel').length;
		if($('.deleteSessionsForModel').length<=3){
				$('#sessionModelId').append("<div class='deleteSessionsForModel'><div class='col-sm-4'><label for='session'>Session:</label>"+
			"<select class='form-control sessionTypeIdForModel' id='sessionIdFOrModel"+numberOfSessions+"'><option value=''>Select Session</option></select></div>"+
			"<div class='col-sm-2'><label for='startTimeForNewEditModel'>Start Time:</label><input type='text' class='form-control timeCls startTimeForModel' id='startTime'  value=' '></div>"+
			"<div class='col-sm-2'><label for='endTimeForNewEditModel'>End Time:</label><input type='text' class='form-control timeCls endTimeForModel' id='endTime' value=' '></div>"+
			"<div class='col-sm-2'><label for='lateTimeForNewEditModel'>Late Time:</label><input type='text' class='form-control timeCls lateTimeForModel' id='lateTime' value=' '></div><div class='col-sm-2 '><button class='btn btn-danger delButForModel' style='margin-top:20px;'>Delete Sessions <i class='glyphicon glyphicon-trash' aria-hidden='true'></i></button></div></div>");
		}
		  var startTimeForModel=$('.startTimeForModel');
		  var endTimeForModel=$('.endTimeForModel');
		 var lateTimeForModel=$('.lateTimeForModel');
		var sessionTypeIdForNewEditModel=$('#sessionIdFOrModel'+numberOfSessions);
		var extraSession=[];
		extraSession.push("extraSession");
		 addSessionsListForModel(sessionTypeIdForNewEditModel,extraSession);

		addDateTimePickerForModel(startTimeForModel,endTimeForModel,lateTimeForModel);

    });
	
	$(document).on("click", ".delButForModel", function () {
		$(this).parent().parent().remove();
  });
  
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