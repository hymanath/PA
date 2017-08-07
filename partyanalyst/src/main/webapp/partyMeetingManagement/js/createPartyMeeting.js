var userFromDate = moment().startOf('month').format("DD/MM/YYYY");
var userToDate = moment().endOf('month').format("DD/MM/YYYY");
var spinner = '<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'
var startFromResult = 0;
var resultStartsGlobalavar = 0;
onLoadCalls();

function onLoadCalls() {
    var levelDivId = 'all';
    var meetingLevelId = "0";
    getCadrePartyMeetngDeatils(levelDivId, meetingLevelId, userFromDate, userToDate, startFromResult);
    getMeetingMainTypeAction();
    //getPartyMeetingLevels();
    getAllSessionType();

}


$('#daterangePickerId').daterangepicker({
    opens: 'left',
    startDate: userFromDate,
    endDate: userToDate,
    locale: {
        format: 'DD/MM/YYYY'
    },
    ranges: {
        'All': [moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
        'Today': [moment(), moment()],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
        'Last 3 Months': [moment().subtract(3, 'month'), moment()],
        'Last 6 Months': [moment().subtract(6, 'month'), moment()],
        'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
        'This Month': [moment().startOf('month'), moment()],
        'This Year': [moment().startOf('Year'), moment()]
    }
});
$('#daterangePickerId').on('apply.daterangepicker', function(ev, picker) {
    userFromDate = picker.startDate.format('DD/MM/YYYY');
    userToDate = picker.endDate.format('DD/MM/YYYY');

    var levelDivId = $('.active').attr('level_attr');
    var meetingLevelId = $('.active').attr('meeting_levelId_attr');
    getCadrePartyMeetngDeatils(levelDivId, meetingLevelId, userFromDate, userToDate, startFromResult);
});
$('.startDateCls').datetimepicker({
	format:'DD-MM-YYYY',
    minDate:new Date()
});
$('.endDateCls').datetimepicker({
   format:'DD-MM-YYYY',
   minDate:new Date()
});

$('.timeCls').datetimepicker({
    format: 'HH:mm'
});
getStates();
function getStates(){
	$("#state").html(" ");	
	$("#state").append('<option value=' + " " + '>' + "Select State" + '</option>');
	$("#state").append('<option value=' + "1" + '>' + "Andhra Pradesh" + '</option>');
	$("#state").append('<option value=' + "36" + '>' + "Telangana" + '</option>');
}
$(document).on("change", ".meetingLevels", function() {
    $("#locationDivId").show();
    //$('#mandalSpinnerId').show();
    var meetingLevelId = $('.meetingLevels').val();
	if(meetingLevelId == ""){
		 $("#locationDivId").hide();
	}
   showHideLocation(meetingLevelId);
    //getMandalBasedOnConstituencyAction(constituencyId);  mandalDivId villageDivId
});

function showHideLocation(meetingLevelId){
	 if (meetingLevelId == 1 || meetingLevelId == 9) {
        $("#stateDivId").show();
        $("#districtDivId").hide();
        $("#constituencyDivId").hide();
        $("#mandalDivId").hide();
        $("#villageDivId").hide();
    }
    if (meetingLevelId == 2) {
        $("#stateDivId").show();
        $("#districtDivId").show();
        $("#constituencyDivId").hide();
        $("#mandalDivId").hide();
        $("#villageDivId").hide();
    }
    if (meetingLevelId == 3) {
        $("#stateDivId").show();
        $("#districtDivId").show();
        $("#constituencyDivId").show();
        $("#mandalDivId").hide();
        $("#villageDivId").hide();
    }
    if (meetingLevelId == 4 || meetingLevelId == 5 || meetingLevelId == 6) {
        $("#stateDivId").show();
        $("#districtDivId").show();
        $("#constituencyDivId").show();
        $("#mandalDivId").show();
        $("#villageDivId").hide();
    }
    if (meetingLevelId == 7 || meetingLevelId == 8) {
        $("#stateDivId").show();
        $("#districtDivId").show();
        $("#constituencyDivId").show();
        $("#mandalDivId").show();
        $("#villageDivId").show();
    }
    if (meetingLevelId == 0 || meetingLevelId=="") {
		getStates();
		//$("#state").html(" ");
        $("#district").html(" ");
        $("#constituency").html(" ");
        $("#mandal").html(" ");
        $("#village").html(" ");
		
		//$("#state").append('<option value=' + " " + '>' + "Select State" + '</option>');
        $("#district").append('<option value=' + " " + '>' + "Select District" + '</option>');
        $("#constituency").append('<option value=' + " " + '>' + "Select Constituency" + '</option>');
        $("#mandal").append('<option value=' + " " + '>' + "Select Mandal" + '</option>');
        $("#village").append('<option value=' + " " + '>' + "Select Village" + '</option>'); 
		
        $("#stateDivId").hide();
        $("#districtDivId").hide();
        $("#constituencyDivId").hide();
        $("#mandalDivId").hide();
        $("#villageDivId").hide();
    }
}
$(document).on("change", "#meetingTypeId", function() {
    var mainMeetingTypeId = $("#meetingTypeId").val();
	$("#meetingLevelId").html("");
	$("#meetingLevelId").append('<option value=' + " " + '>' + "Select Meeting Level" + '</option>');
    showHideLocation(0);
    getMeetingSubTypeAction(mainMeetingTypeId);
});
$(document).on("change", "#district", function() {
    $('#constituencySpinnerId').show();
    var districtId = $('#district').val();
    getPartyMeetingsTabUserNameByDistrict(districtId);
    getConstituencyAction(districtId);
	 $("#mandal").html(" ");
     $("#village").html(" ");
     $("#mandal").append('<option value=' + " " + '>' + "Select Mandal" + '</option>');
     $("#village").append('<option value=' + " " + '>' + "Select Village" + '</option>'); 	
});
$(document).on("click", ".meetingLevelCls", function() {

    resultStartsGlobalavar = 0;
    startFromResult = resultStartsGlobalavar;
    var levelDivId = $('.active').attr('level_attr');
    var meetingLevelId = $(this).attr('meeting_levelId_attr');

    getCadrePartyMeetngDeatils(levelDivId, meetingLevelId, userFromDate, userToDate, startFromResult);
});

$(document).on("click", ".paginate_button", function() {
    var levelDivId = $('.active').attr('level_attr');
    var meetingLevelId = $('.active').attr('meeting_levelId_attr');
    startFromResult = resultStartsGlobalavar;
    getCadrePartyMeetngDeatils(levelDivId, meetingLevelId, userFromDate, userToDate, startFromResult);
    resultStartsGlobalavar = startFromResult + 9;

});
$(document).on("change", "#constituency", function() {
    $('#mandalSpinnerId').show();
    var constituencyId = $('#constituency').val();
    getMandalBasedOnConstituencyAction(constituencyId);
	$("#village").html(" ");
    $("#village").append('<option value=' + " " + '>' + "Select Village" + '</option>'); 		 
			 
});
$(document).on("change", "#mandal", function() {
    $('#villageSpinnerId').show();
    var mandalId = $('#mandal').val();
    var constituencyId = $('#constituency').val();
    getPanchayatWardByMandalAction(mandalId, constituencyId);
});
$(document).on("change", "#state", function() {
    $('#districtSpinnerId').show();
    var stateId = $('#state').val();
    getDistrictsAction(stateId);
	 $("#mandal").html(" ");
	 $("#constituency").html(" ");
     $("#village").html(" ");
     $("#constituency").append('<option value=' + " " + '>' + "Select Constituency" + '</option>');
     $("#mandal").append('<option value=' + " " + '>' + "Select Mandal" + '</option>');
     $("#village").append('<option value=' + " " + '>' + "Select Village" + '</option>'); 	
});



function getMeetingSubTypeAction(mainMeetingTypeId, model, selId) {
    $("#meetingTypeSubTypeId").html("");
    $("#meetingTypeSubTypeId").append('<option value=' + 0 + '>' + "Select Meeting Sub-type" + '</option>');
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
getPartyMeetingsTabUserNameByDistrict()

function getPartyMeetingsTabUserNameByDistrict(type) {

    var jsObj = {

    }
    $.ajax({
        type: "GET",
        url: "getPartyMeetingsTabUserNameByDistrictAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (var i in results) {
            if (results != null) {
                buildTabUserDetails(results);
                if (type == null) {
                    $('#tabusermobilenumberId').html("  " + results[i].constituencyName);
                    $('#tabuserDistrictId').html("  " + results[i].name);
                    //$('#tabuserId').val(results[i].id);    
                } else {
                    //$('#tabuserModelId').val(results[i].id);
                    $('#tabusermobilenumberModelId').html("  " + results[i].constituencyName);
                    $('#tabuserDistrictModelId').html("  " + results[i].name);
                }

            }
        }

    });
}

function buildTabUserDetails(results, meetingId) {
    var str = '';
	str += " <div class='scrollit'>";
    str += "<table id='tabUserTableId' class='table table-bordered tabUserTableCls'>";

    str += "<thead>";
    str += "<tr>";
    str += "<th>User Name</th>";
    str += "<th>Mobile Number</th>";
    str += "<th>District Name</th>";
    str += "<th>Check Box</th>";

    str += "</tr>";
    str += "<thead>";
    str += "<tbody>";
    for (var i in results) {
        str += "<tr>";
        str += "<td>" + results[i].meetingName + "</td>";
        str += "<td>" + results[i].constituencyName + "</td>";
        str += "<td>" + results[i].name + "</td>";
        str += "<td><input type='checkBox' class='tabUserCheckBoxCls' value=" + results[i].id + "></td>";
        str += "</tr>";
    }
    str += "</tbody>";
    str += "</table>";
	str += "</div>";
    $("#tabUserTableDivId").html(str);
    // $("#tabUserIdDetailsModal").html(str);
   /* $(".tabUserTableCls").dataTable({
			paging:false,
			scrollY:300
	});*/
    if (meetingId != null) {
        getPartyMeetingTabUserDetailsAction(meetingId);
    }
}



function getCadrePartyMeetngDeatils(levelDivId, meetingLevelId, userFromDate, userToDate, startFromResult) {
	    
	$("#paginationCountDivId").html("");
	$("#meetingTableId").html("");
    $("#meetingTableId").html(spinner)
   $("#paginationDivId").hide();
    var partiMeetingLevelId = meetingLevelId;
    var jsObj = {
        startDateStr: userFromDate,
        endDateStr: userToDate,
        partyMeetingMainTypeId: partiMeetingLevelId,
        startFromResult: startFromResult,
        maxIndex: "10"

    }
    $.ajax({
        type: "GET",
        url: "getCadrePartyMeetngDeatilsAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        $("#paginationDivId").show();
        buildMeetingDetailsTable(results, partiMeetingLevelId, levelDivId, jsObj);
    });
}


function buildMeetingDetailsTable(results, partiMeetingLevelId, levelDivId, jsObj) {
	
    var str = "";
    str += "<table id='meetingTableId" + levelDivId + "' class='table table-bordered'>";
    str += "<thead class='text-capital'>";
    str += "<tr>"
    str += "<th class='text-center text-capital'>meeting name</th>";
    str += "<th class='text-center text-capital'>Level</th>";
    str += "<th class='text-center text-capital'> meeting type</th>";
    str += "<th class='text-center text-capital'> State</th>";
    str += "<th class='text-center text-capital'>District </th>";
    str += "<th class='text-center text-capital'>Constituency </th>";
    str += "<th class='text-center text-capital'>Mandal</th>";
    str += "<th class='text-center text-capital'>Village</th>";
    str += "<th class='text-center text-capital'>Date</th>";
    str += "<th class='text-center text-capital'>Action</th>";
    str += "</tr>"
    str += "</thead>";
    str += "<tbody>";
   if(results == null){
   $("#paginationDivId").hide();
  }
   else{
    for (var i in results) {


        str += "<tr>"
        str += "<td class='text-center text-capitalize'>" + results[i].meetingName.toLowerCase() + "</td>";
        str += "<td class='text-center text-capitalize'>" + results[i].remarks.toLowerCase() + "</td>";
        str += "<td class='text-center'>" + results[i].meetingType + "</td>";
        if (results[i].stateName != null && results[i].stateName != "") {
            str += "<td class='text-center'>" + results[i].stateName + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
        }
        if (results[i].districtName != null && results[i].districtName != "") {
            str += "<td class='text-center'>" + results[i].districtName + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
        }
        if (results[i].constituencyName != null && results[i].constituencyName != "") {
            str += "<td class='text-center text-capitalize'>" + results[i].constituencyName.toLowerCase() + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
        }
       if(results[i].teshilName != "" || results[i].name != ""){
        if (results[i].teshilName != null && results[i].teshilName != "") {
            str += "<td class='text-center'>" + results[i].teshilName + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
        }
        if (results[i].name != null && results[i].name != "") {
            str += "<td class='text-center text-capitalize'>" + results[i].name.toLowerCase() + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
        }
    }else{
      
      if (results[i].mandalTwnDivision != null && results[i].mandalTwnDivision != "") {
       str += "<td class='text-center'>" + results[i].mandalTwnDivision + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
        }
    if (results[i].wardName != null && results[i].wardName != "") {
       str += "<td class='text-center text-capitalize'>" + results[i].wardName.toLowerCase() + "</td>";
        } else {
            str += "<td class='text-center'> - </td>";
      }
    }
        str += "<td class='text-center'>" + results[i].updatedTime + "</td>";
        if (results[i].flage != null || results[i].isCondacted != "y") {
            str += "<td class='text-center'>";
            if (results[i].flage == "true") {
                str += "<i id=" + results[i].id + " class='glyphicon glyphicon-edit meetingEditCls'   title='Edit Meeting' style='padding-right:10px;cursor:pointer;'></i>";
            }
            if (results[i].isCondacted != "Y") {
				str += "<i id=" + results[i].id + " class='glyphicon glyphicon-trash deleteMeeting' title='Delete Meeting' style='cursor:pointer;'></i>";
               
            }
        }
        str += "</td>";
        str += "</tr>"

    }
}
    str += "</tbody>";
    str += "</table>";
    $("#meetingTableId").html(str);
	$("#meetingTableId"+levelDivId).dataTable({
		 paging: false,
		 bInfo: false
	});
    var itemsCount = +results[0].totalCount;
    var maxResults = jsObj.maxIndex;
    if (jsObj.startFromResult == 0) {
        $("#paginationDivId").html('');
        $("#paginationDivId").pagination({
            items: itemsCount,
            itemsOnPage: maxResults,
            cssStyle: 'light-theme',

            onPageClick: function(pageNumber, event) {
                var startFromResult2 = (pageNumber - 1) * 10;
                getCadrePartyMeetngDeatils(jsObj.levelDivId, jsObj.partyMeetingMainTypeId, jsObj.startDateStr, jsObj.endDateStr, startFromResult2);
            }
        });
    }
    $("#paginationCountDivId").html("<h4>Total Meetings : "+results[0].totalCount+"</h4>");
}

function getMandalBasedOnConstituencyAction(constituencyId, type, theshilId, panchayatId) {
    $('#mandal').html(" ");
    $("#mandal").append('<option value=' + 0 + '>' + "Select Mandal" + '</option>');
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

                $("#mandal").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            } else {
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
    $("#villageModelId").append('<option value=' + 0 + '>' + "Select Village" + '</option>');
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
                $("#village").append('<option value=' + results[i].id + '>' + results[i].name.toLowerCase() + '</option>');
            } else {

                $("#villageModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            }
        }
        if (type == "model") {

            $("#villageModelId option[value=" + panchayatId + "]").attr('selected', 'selected');
        }
    });
}




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
            getMeetingSubTypeAction(meetingMainTypeId, selId, "model");
        }
    });
}


$(document).on("change", "#meetingTypeSubTypeId", function() {
	var subTypeId=$("#meetingTypeSubTypeId").val();
   getPartyMeetingLevels(subTypeId);
   showHideLocation(0);
});
function getPartyMeetingLevels(subTypeId) {
	$("#meetingLevelId").html(' ');
	$("#meetingLevelId").append('<option value=' + " " + '>' + "Select Meeting Level" + '</option>');
    var jsObj = {
		partyMeetingMainTypeId:subTypeId
	}
    $.ajax({
        type: "GET",
        url: "getPartyMeetingLevelsAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (var i in results) {
            $("#meetingLevelId").append('<option value=' + results[i].id + '>' + results[i].name.toLowerCase() + '</option>');
            $("#meetingLevelModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
        }
    });
}

function getAllSessionType() {
    var jsObj = {}

    $.ajax({
        type: "GET",
        url: "getAllSessionTypeAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (var i in results) {
            $("#sessionId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            $("#sessionModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
        }

    });

}

function getDistrictsAction(stateId, model, id) {
    $("#district").html("");
    $("#district").append('<option value=' + 0 + '>' + "Select District" + '</option>');
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


function buildInveeteeDetailsTable(results) {
    var str = "";
    str += "<table id='inviteeTavbleId' class='table-bordered'>";
    str += "<thead>";
    str += "<tr>";
    str += "<th>S.NO</th>";
    str += "<th>Name</th>";
    str += "<th>Desiganation</th>";
    str += "<th>Membership No</th>";
    str += "<th>Invitees No</th>";
    str += "<th>Mobile No</th>";
    str += "</tr>";
    str += "<thead>";
    str += "<tbody>";
    for (var i in results) {
        for (var j in results[i].subList) {
            var sNo = parseInt(j) + parseInt(1);
            str += "<tr>";
            str += "<td>" + sNo + "</td>";
            str += "<td>" + results[i].subList[j].name + "</td>";
            var str2 = '';
            str += "+<td>";
            for (var k in results[i].subList[j].inviteeList) {
                str2 = results[i].subList[j].inviteeList[k];
                // str+="<td>"+results[i].subList[j].inviteeList[k]+"</td>";
                str += str2 + ', ';
            }

            // str+="+<td>"str2+"</td>";

            str += "</td>";
            str += "<td>" + results[i].subList[j].mandalTwnDivision + "</td>";
            str += "<td>" + results[i].subList[j].inviteeId + "</td>";
            str += "<td>" + results[i].subList[j].remarks + "</td>";
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
$(document).on("change", "#districtModelId", function() {
    $('#tabuserModelId').html('');
    $('#tabusermobilenumberModelId').html('');
    $('#tabuserDistrictModelId').html('');
    var districtId = $("#districtModelId").val();

    $('#constituencyModelId').show();
    $("#constituencyModelId").html('');
    getConstituencyAction(districtId, "NotNull");
    getPartyMeetingsTabUserNameByDistrict(districtId, "NotNull");
});
$(document).on("change", "#constituencyModelId", function() {
    $('#constituencyModelId').show();
    var constituencyId = $('#constituencyModelId').val();
    $("#mandalModelId").html('');
    getMandalBasedOnConstituencyAction(constituencyId, "notNull");
});
$(document).on("change", "#mandalModelId", function() {
    $('#constituencyModelId').show();
    var constituencyId = $('#constituencyModelId').val();
    var mandalId = $('#mandalModelId').val();
    $("#villageModelId").html('');
    getPanchayatWardByMandalAction(mandalId, constituencyId, "notNull");
});
$(document).on("change", "#meetingTypeModelId", function() {
    var mainMeetingTypeId = $("#meetingTypeModelId").val();
    $("#meetingTypeSubTypeModelId").html(" ");
    getMeetingSubTypeAction(mainMeetingTypeId, "model");
});

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
    //var count = 0;
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
    }).done(function(results) {
        $('#constituencySpinnerId').hide();
        for (var i in results) {
            if (type == null) {
                $("#constituency").append('<option value=' + results[i].id + '>' + results[i].name.toLowerCase() + '</option>');
            } else {

                $("#constituencyModelId").append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
                $("#constituencyModelId option[value=" + id + "]").attr('selected', 'selected');

            }
        }
        if (type == "model") {
            var constituency = $("#constituencyModelId").val();
            getMandalBasedOnConstituencyAction(constituency, type, theshilId, panchayatId);

            getPartyMeetingsTabUserNameByDistrict($("#districtModelId").val(), type);
        }
    });
}

$("#addSession").on("click", function() {
    //$(".formIdFind").html(' ');
    //count++;
    var numberOfSessionTags = $("#createSession > .delSession").length;
    if (numberOfSessionTags < 3) {
        $("#sessionId" + numberOfSessionTags).html(' ');
        var addSessions =

            "<div class='row delSession'>" +
            "<div class='col-sm-4'>" +
            "<label for='session'>Session:</label>" +
            "<select class='form-control formIdFind sessionTypeId' id='sessionId" + numberOfSessionTags + "' name='partySession" + numberOfSessionTags + "' data-rule-required='true' data-msg-required='Please Select Session'>" +
            "<option value=''>Select Session</option>" +
            "</select> </div> <div class='col-sm-2'><label for='startDate'>Start Time:</label>" +
            "<input type='text' class='form-control stTime startTime' id='startTime" + numberOfSessionTags + "' name='partyStartTime" + numberOfSessionTags + "' data-rule-required='true' data-msg-required='Please Enter Start Time'/>" +
            "</div> <div class='col-sm-2'><label for='startDate'>End Time:</label>" +
            "<input type='text' class='form-control edTime endTime' id='endTime" + numberOfSessionTags + "' name='partyEndTime" + numberOfSessionTags + "' data-rule-required='true' data-msg-required='Please Enter End Time'/>" +
            "</div><div class='col-sm-2'><label for='startDate'>Late Time:</label>" +
            "<input type='text' class='form-control ltTime lateTime' id='lateTime" + numberOfSessionTags + "' name='partyLateTime" + numberOfSessionTags + "' data-rule-required='true' data-msg-required='Please Enter Late Time'/>" +
            "</div><div class='col-sm-2'>" +
            "<button id='deleteButton' type='button' class='btn btn-danger removeButtonId' style='margin-top:20px;'>Delete Session" +
            "<i class='glyphicon glyphicon-trash '></i></button></div></div>";
        $('#createSession').append(addSessions);
        $('.stTime').datetimepicker({
            format: 'HH:mm'
        });

        $('.edTime').datetimepicker({
            format: 'HH:mm'
        });
        $('.ltTime').datetimepicker({
            format: 'HH:mm'
        });
        var jsObj = {}

        $.ajax({
            type: "GET",
            url: "getAllSessionTypeAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {

            for (var i in results) {
                $("#sessionId" + numberOfSessionTags).append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
            }

        });

    }
    $('.removeButtonId').on("click", function() {
        $(this).parent().parent().remove();
    });
});

function getPartyMeetingTabUserDetailsAction(partyMeetingId) {
        $("#tabUserDetailsModelDivId").html('');
        var jsObj = {
            partyMeetingId: "21"
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
                $("#tabUserDetailsModelDivId").append("<ul class='list-inline'><li>User Name:" + results[i].name + "</li><li>District Name:" + results[i].constituencyName + "</li><li>Mobile Number:" + results[i].remarks + "</li></ul>");
            }
        });

    }
    
getSessionsDetailsByMeetingId()

function getSessionsDetailsByMeetingId() {
    var jsObj = {
        partyMeetingId: "445220"
    }
    $.ajax({
        type: "GET",
        url: "getSessionsDetailsByMeetingIdAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (i in results) {

        }
    });
}
$(document).on("click", "#editTabUserModelId", function() {
    getPartyMeetingsTabUserNameByDistrict("model");
});
$(document).on("click", "#downloadTemplateId", function() {
    $.ajax({
        type: "GET",
        url: "downloadInviteesTemplateAction.action",
    }).done(function(results) {

    });
});
$('#showHideDatePickerId').hide();
$('#partyMeetingTable').hide();
$('#partyMeetingTabButton').on("click", function() {
    if ($(this).find("i").hasClass("glyphicon-plus")) {
        $(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
        $('#showHideDatePickerId').show();
        $('#partyMeetingTable').show();

    } else {
        $(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
        $('#showHideDatePickerId').hide();
        $('#partyMeetingTable').hide();
    }
});

$('#tabUserTableDivId').hide();
$('#tabUserTabButton').on("click", function() {
    if ($(this).find("i").hasClass("glyphicon-plus")) {
        $(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
        $('#tabUserTableDivId').show();

    } else {
        $(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
        $('#tabUserTableDivId').hide();
    }
});

$('#attendedInvitieesTable').hide();
$('#attendedInvitieesTabButton').on("click", function() {
    if ($(this).find("i").hasClass("glyphicon-plus")) {
        $(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
        $('#attendedInvitieesTable').show();

    } else {
        $(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
        $('#attendedInvitieesTable').hide();
    }
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

$(document).on("click", "#inviteeDetailsId", function() {
    getTdpCadreDetailsForPartyMeetingAction();

});

function getTdpCadreDetailsForPartyMeetingAction() {
    var jsObj = {
        partyMeetingId: "445220"
    }
    $.ajax({
        type: "GET",
        url: "getTdpCadreDetailsForPartyMeetingAction.action",
        data: {
            task: JSON.stringify(jsObj)
        }
    }).done(function(results) {
        for (i in results) {

        }
    });
}

$(document).on("click", ".meetingEditCls", function(){ 
    var meetingId = $(this).attr('id');
    //var redirectWindow = window.open('editPartyMeetingManagementAction.action?meetingId=' + meetingId + '');
	var redirectWindow=window.open('editPartyMeetingManagementAction.action?meetingId='+meetingId+'','_self');

});

$(document).ready(function(e) {
    var $input = $('#refresh');
    $input.val() == 'yes' ? location.reload(true) : $input.val('yes');
});

$(document).on("click", ".deleteMeeting", function() {
    var meetingId = $(this).attr("id");
  if (meetingId != null) {
 deletePartyMeetingConformation(meetingId,this)
    } 
});
function deletePartyMeetingConformation(meetingId,id) {

    var ask = confirm("Do You want to delete");
    if (ask == true) {
        var jsObj = {
            partyMeetingId: meetingId
        }
        $.ajax({
            type: "GET",
            url: "deletePartyMeetingDatailsAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {
            if (results != null) {
		$(id).parents('tr').remove();
		    $(".resultStatus").modal('show');
             $('#submitSuccessId').html("");
             $('#submitSuccessId').append("<p>Meeting Deleted Successfully</p>");
             setTimeout(function() {
                 $(".resultStatus").modal('hide');
             }, 3000);

            } 

        });
		
    }
  
}


$(document).on("change", ".sessionTypeId", function() {
	 var sessionId=$(this).attr('id');
	var id=$(this).val();
   var jsObj = {
            partyMeetingSessionId: id
        }
        $.ajax({
            type: "GET",
            url: "getPartyMeetingSessionAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {
			if(results == null && sessionId == "sessionId"){
                $("#startTime").val("");
				$("#endTime").val("");
				$("#lateTime").val("");
               }
			   else if(results == null && sessionId != "sessionId"){
                var position=sessionId.substr(9, 1);
				$("#startTime"+position).val("");
				$("#endTime"+position).val("");
				$("#lateTime"+position).val("");
               }
			else if(results != null && sessionId == "sessionId"){
				$("#startTime").val(results[0].startTime.substr(11, 5));
				$("#endTime").val(results[0].endTime.substr(11, 5));
				$("#lateTime").val(results[0].lateTime.substr(11, 5));
				$("#startTime").valid();
				$("#endTime").valid();
				$("#lateTime").valid();
			}
			else{
				var position=sessionId.substr(9, 1);
				$("#startTime"+position).val(results[0].startTime.substr(11, 5));
				$("#endTime"+position).val(results[0].endTime.substr(11, 5));
				$("#lateTime"+position).val(results[0].lateTime.substr(11, 5));
				
				$("#startTime"+position).valid();
				$("#endTime"+position).valid();
				$("#lateTime"+position).valid();
			}
         
        }); 
});