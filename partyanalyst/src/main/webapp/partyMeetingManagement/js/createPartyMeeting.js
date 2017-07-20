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
    getPartyMeetingLevels();
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

});
$('.endDateCls').datetimepicker({

});

$('.timeCls').datetimepicker({
    format: 'HH:mm'
});


$(document).on("change", ".meetingLevels", function() {
    $("#locationDivId").show();
    //$('#mandalSpinnerId').show();
    var meetingLevelId = $('.meetingLevels').val();
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
    if (meetingLevelId == 0) {
        $("#stateDivId").hide();
        $("#districtDivId").hide();
        $("#constituencyDivId").hide();
        $("#mandalDivId").hide();
        $("#villageDivId").hide();
    }
    //getMandalBasedOnConstituencyAction(constituencyId);  mandalDivId villageDivId
});

$(document).on("change", "#meetingTypeId", function() {
    var mainMeetingTypeId = $("#meetingTypeId").val();
    getMeetingSubTypeAction(mainMeetingTypeId);
});
$(document).on("change", "#district", function() {
    $('#constituencySpinnerId').show();
    var districtId = $('#district').val();
    getPartyMeetingsTabUserNameByDistrict(districtId);
    getConstituencyAction(districtId);
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
});



function getMeetingSubTypeAction(mainMeetingTypeId,model, selId) {
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
getPartyMeetingsTabUserNameByDistrict();

function getPartyMeetingsTabUserNameByDistrict(model) {

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
    $("#tabUserTableDivId").html(str);
    // $("#tabUserIdDetailsModal").html(str);
    $(".tabUserTableCls").dataTable();
    if (meetingId != null) {
        getPartyMeetingTabUserDetailsAction(meetingId);
    }
}

function getCadrePartyMeetngDeatils(levelDivId, meetingLevelId, userFromDate, userToDate, startFromResult) {
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

function buildMeetingDetailsTable(results,partiMeetingLevelId,levelDivId,jsObj){
	var str="";
	str+="<table id='meetingTableId"+levelDivId+"' class='table table-bordered'>";
		str+="<thead class='text-capital'>";
			str+="<tr>"
				str+="<th>meeting name</th>";
				str+="<th>Level</th>";
				str+="<th>type</th>";
				str+="<th>District Name</th>";
				str+="<th>Constincy Name</th>";
				str+="<th>Mandal</th>";
				str+="<th>Village</th>";
				str+="<th>Date</th>";
				str+="<th>Action</th>";
			str+="</tr>"
		str+="</thead>";
		str+="<tbody>";
		for(var i in results){
			str+="<tr>"
					str+="<td>"+results[i].meetingName+"</td>";
					str+="<td>"+results[i].remarks+"</td>";
					str+="<td>"+results[i].meetingType+"</td>";
				if(results[i].districtName != null){
					str+="<td>"+results[i].districtName+"</td>";
				}else{
					str+="<td>-</td>";
				}
				if(results[i].constituencyName != null){
					str+="<td>"+results[i].constituencyName+"</td>";
				}else{
					str+="<td>-</td>";
				}
				if(results[i].teshilName != null){
					str+="<td>"+results[i].teshilName+"</td>";
				}else{
					str+="<td>-</td>";
				}
			
				if(results[i].name != null){
					str+="<td>"+results[i].name+"</td>";
				}else{
					str+="<td>-</td>";
				}
					str+="<td>"+results[i].updatedTime+"</td>";
					str+="<td class='text-center'>";
					if(results[i].flage=="true"){
						str+="<i id="+results[i].id+" class='glyphicon glyphicon-edit meetingEditCls'   title='Edit' style='padding-right:10px;cursor:pointer;'></i>";
					}
					if(results[i].isCondacted=="Y"){
						str+="<i id="+results[i].id+" class='glyphicon glyphicon-trash' style='display:none;'></i>";
					}else{
						str+="<i id="+results[i].id+" class='glyphicon glyphicon-trash'></i>";
					}
					str+="</td>";
			str+="</tr>"
		}
		str+="</tbody>";
	str+="</table>";
	$("#meetingTableId").html(str);
	var itemsCount=+results[0].totalCount;
	var maxResults=jsObj.maxIndex;
	if(jsObj.startFromResult==0){
		$("#paginationDivId").html('');
		$("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			
			onPageClick: function(pageNumber, event) {
				var startFromResult2=(pageNumber-1)*10;
				getCadrePartyMeetngDeatils(jsObj.levelDivId,jsObj.partyMeetingMainTypeId,jsObj.startDateStr,jsObj.endDateStr,startFromResult2); 
			}
		});
	}
	$("#meetingTableId").html(str);
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
    //$("#districtModelId").html("");
    $("#modalMeetingId").val(results[0].id);
    $("#meetingNameModelId").attr('value', results[0].name);
    $("#startDateModelId").attr('value', results[0].startDate);
    $("#endDateModelId").attr('value', results[0].endDate);
    getDistrictsAction(1, type, results[0].districtId);
    $("#meetingLevelModelId option[value=" + results[0].meetingTypeId + "]").attr('selected', 'selected');
    $("#meetingTypeSubTypeModelId option[value=" + results[0].mandalTwnDivisionId + "]").attr('selected', 'selected');
    //alert(results[0].districtId);
    //$("#districtModelId option[value="+results[0].districtId+"]").attr('selected','selected');
    $("#meetingTypeModelId option[value=" + results[0].meetingMainTypeId + "]").attr('selected', 'selected');
    var districtId = $("#districtModelId").val();
    $("#constituencyModelId").html('');
    getConstituencyAction(districtId, type, results[0].constituencyId, results[0].teshilId, results[0].panchayatId);
    var meetingMainTypeId = $("#meetingTypeModelId").val();
    var meetSubSeVar = results[0].mandalTwnDivisionId;
    getMeetingSubTypeAction(meetingMainTypeId, type, meetSubSeVar);


    //getPartyMeetingsTabUserNameByDistrict(districtId,type);

    console.log(results);
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

				getPartyMeetingsTabUserNameByDistrict($("#districtModelId").val(), type);
			}
		});
	}

$("#addSession").on("click", function() {
    //$(".formIdFind").html(' ');
    //count++;
    var numberOfSessionTags = $("#createSession > .delSession").length;
    if (numberOfSessionTags < 3) {
		 $("#sessionId"+numberOfSessionTags).html(' ');
        var addSessions =

            "<div class='row delSession'>" +
            "<div class='col-sm-4'>" +
            "<label for='session'>Session:</label>" +
            "<select class='form-control formIdFind sessionTypeId' id='sessionId"+numberOfSessionTags+"' required >" +
            "<option value=''>Select Session</option>" +
            "</select> </div> <div class='col-sm-2'><label for='startDate'>Start Time:</label>" +
            "<input type='text' class='form-control stTime startTime' id='startTime' required/>" +
            "</div> <div class='col-sm-2'><label for='startDate'>End Time:</label>" +
            "<input type='text' class='form-control edTime endTime' id='endTime' required/>" +
            "</div><div class='col-sm-2'><label for='startDate'>Late Time:</label>" +
            "<input type='text' class='form-control ltTime lateTime' id='lateTime' required/>" +
            "</div><div class='col-sm-2'>" +
            "<button id='deleteButton' type='button' class='btn btn-primary removeButtonId' style='margin-top:20px;'>Delete Session" +
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
        console.log($('.formIdFind').attr('id'));
        var jsObj = {}

        $.ajax({
            type: "GET",
            url: "getAllSessionTypeAction.action",
            data: {
                task: JSON.stringify(jsObj)
            }
        }).done(function(results) {
            
            for (var i in results) {

                console.log("result" + results[i].name);
                $("#sessionId"+numberOfSessionTags).append('<option value=' + results[i].id + '>' + results[i].name + '</option>');
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
    /*$(document).on("click",".meetingDelete",function(){
	  var meetingId=$(this).attr('id');
	 var jsObj = {
		   partyMeetingId:"683705"
	  }
      $.ajax({
        type: "GET",
        url: "deletePartyMeetingDatailsAction.action",
        data: {task:JSON.stringify(jsObj)}
      }).done(function(results) {
		buildInveeteeDetailsTable(results);
     });
	   
	 
  });*/
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
    // alert(123)
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

$(document).on("click",".meetingEditCls",function(){
		 var meetingId = $(this).attr('id');
		var redirectWindow=window.open('editPartyMeetingManagementAction.action?meetingId='+meetingId+'');
        
	});
	