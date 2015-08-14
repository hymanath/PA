<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CALLS List</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<style type="text/css">
.filters-div
{
	background-color:#CCC;
	padding:10px;
}
</style>
</head>
<body>
<header>
	<img src="dist/img/header.jpg" width="100%">
</header>
<main>
	<div class="container">
    	<div class="row">
        	<section>
            	<div class="col-md-12">
                	<div class="panel panel-default">
                    	<div class="panel-heading">
                        	<h4 class="panel-title" id="titleId" style="text-transform: uppercase;">UNDIALLED CALLS LIST
                            <!--	<span class="pull-right">
                                	<select>
                                    	<option>Not Dialled Calls</option>
                                    </select>
                                </span>-->
								<span class="pull-right">
									<button class="btn btn-success btn-xs">FILTERS</button>
								</span>
                            </h4>
                        </div>
                        <div class="panel-body">
							<div class="filters-div">
								<div class="row">
									<div class="col-md-12">
										<h4 class="m_0">
											COMMITTEE LOCATION
											<span class="pull-right"><i class="glyphicon glyphicon-remove remove-filters" style="font-size:16px;background-color:#E6E6E6;padding:7px;top:-10px;right:-10px;cursor:pointer" onclick="setDefault();"></i></span>
											<hr class="m_0" style="border-color:#666"/>
										</h4>
									</div>
									<div class="col-md-3 m_top10">
										<label>District</label>
										<select class="form-control" id="districtId" onchange="getConstituenciesByDistrict();">
											<option value="0">Select</option>
										</select>
									</div>
									<div class="col-md-3 m_top10">
										<label>Constituency</label>
										<select class="form-control" id="constituencyId" onchange="getMandalsByConstiteuncy();">
											<option value="0">Select</option>
										</select>
									</div>
									<div class="col-md-3 m_top10">
										<label>Mandal</label>
										<select class="form-control" id="mandalId" onchange="getVillagesByMandal();">
											<option value="0">Select</option>
										</select>
									</div>
									<div class="col-md-3 m_top10">
										<label>Village</label>
										<select class="form-control" id="villageId">
											<option value="0">Select</option>
										</select>
									</div>
								</div>
								<div class="row m_top20">
									<div class="col-md-6">
										<div class="row">
											<div class="col-md-12">
												<h4 class="m_0">
													ROLE WISE
													<hr class="m_0" style="border-color:#666"/>
												</h4>
											</div>
											<div class="col-md-6 m_top10">
												<label>Committee Level</label>
												<select class="form-control" id="committeLevelId">
													<option value="0">Select</option>
													<option value="1">State</option>
													<option value="2">District</option>
													<!--<option value="3">Constituency</option>
													<option value="4">Parliament</option>-->
													<option value="5">Mandal</option>
													<option value="6">Panchayat</option>
													<!--<option value="7">Booth</option>
													<option value="8">Incharge</option>-->
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row">
											<div class="col-md-12">
												<h4 class="m_0">
													CALL STATUS WISE 
													<hr class="m_0" style="border-color:#666"/>
												</h4>
											</div>
											<div class="col-md-6 m_top10">
												<label>Status Type</label>
												<select class="form-control" id="callstatusSelect">
													<option value="0">Select</option>
													<option value="dialed">Dialled</option>
													<option value="busy">Switch off /User Busy / Not Ans</option>
													<option value="undialed">UN Dialled</option>
													<option value="callback">Call Back</option>
													<option value="interested">Interested</option>
													<option value="later">Later</option>
													<option value="notInterested">Not Interested</option>
													
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row m_top20">
									<div class="col-md-12">
										<button class="btn btn-success" onclick="getFilterWiseDetails();">GET FILTER DETAILS</button>
									</div>
								</div>
							</div>
                        	<div id="memberInfoDiv">
                            	
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</main>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header  bg_d">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">&nbsp;</h4>
      </div>
      <div class="modal-body">
	  <div id="messageDiv"></div>
      		<div class="row">
            	<section>
                	<div class="col-md-12" id="callStatusDiv">
                           
                    </div>
                    <div class="Answered-div ">
                        <div class="col-md-12 m_top20 clearDiv">
                            <label>Call Back Remarks</label>
                            <ul class="callback-remarks">
                            	<textarea class="form-control clearEl" id="remarks"></textarea>
                            </ul>
                        </div>
                        <div class="col-md-4 m_top20 clearDiv">
                            <label>Select Program</label>
                            <select class="form-control" id="programId" disabled onchange="getCampsForProgram();">
                               <option value="0">Select</option>
                            </select>
                        </div>
                        <div class="col-md-4 m_top20 clearDiv">
                            <label>Select Training Camp</label>
                            <select class="form-control" id="campId" disabled onchange="getSchedulesForCamp();">
                                  <option value="0">Select</option>
                            </select>
                        </div>
                        <div class="col-md-4 m_top20 clearDiv">
                            <label>Select Schedule</label>
                            <select class="form-control" id="scheduleId" disabled onchange="getBatchesForSchedule();">
                                  <option value="0">Select</option>
                            </select>
                        </div>
                        <div class="col-md-4 m_top20 clearDiv">
                            <label>Select Batch</label>
                            <select class="form-control" id="batchId">
                                 <option value="0">Select</option> 
                            </select>
                        </div>
                        <div class="col-md-12 m_top20 clearDiv" id="scheduleStatusDiv">
                           
                        </div>
                        <div class="col-md-12 m_top20 clearDiv">
                            <button class="btn btn-success" onclick="updateCadreStatus();">Update Status</button>
							<img id="ajaxImage" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:30px;display:none;"/>
                        </div>
                    </div>
                    <div class="callback-div disnone">
                    	<div class="col-md-4 m_top20">
                        	<label>Call Back Type</label>
                            <select class="form-control" id="callBackTypeId">
								<option value="0">Select CallBack Type</option>
                            	<option value="6">Call Back - Busy</option>
								<option value="7">Call Back - Confirm Later</option>
								
                            </select>
                        </div>
                        
                        <div class="col-md-4 m_top20">
                        	<label>Call Back Date & Time</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                <input class="form-control clearEl" id="CallbackTime">
                            </div>
                        </div>
                    	<div class="col-md-4 m_top20">
                        	<label>Call Back Date & Time</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                <input class="form-control clearEl" id="timepicker4">
                            </div>
                        </div>
                        <div class="col-md-12 m_top20">
                            <label>Remarks</label>
                            <textarea class="form-control clearEl" id="callbackremarks"></textarea>
                        </div>
                        <div class="col-md-12 m_top20 clearDiv">
                            <button class="btn btn-success" onclick="updateCallBackCadreStatus();">Update Status</button>
							<img id="ajaxImage1" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:30px;display:none;"/>
                        </div>
                    </div>
                    <div class="switchoff-div disnone">
                    	<div class="col-md-12 m_top20">
                            <button class="btn btn-success" onclick="updateCadreStatus();">Update Status</button>
							<img id="ajaxImage2" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:30px;display:none;"/>
                        </div>
                    </div>
                </section>
            </div>
      </div>
    </div>
  </div>
</div>
<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">
var purposeId = '${purposeId}';
var programId = '${programId}';
var campId = '${campId}';
var scheduleId = '${scheduleId}';
var status = '${status}';
var batchId = '${batchId}';
var statusType = '${statusType}';
var today = '${today}';
/*$('.callback input:checkbox').change(function(){
     if($(this).is(":checked")) {
        $('.Answered-div').addClass("disnone");
		$('.callback-div').removeClass("disnone");
    } else {
        $('.Answered-div').removeClass("disnone");
		$('.callback-div').addClass("disnone");
    }
});
$('.switchoff input:checkbox').change(function(){
     if($(this).is(":checked")) {
        $('.Answered-div').addClass("disnone");
		$('.callback-div').addClass("disnone");
		$('.switchoff-div').removeClass("disnone");
		
    } else {
        $('.Answered-div').addClass("disnone");
		$('.callback-div').addClass("disnone");
		$('.switchoff-div').addClass("disnone");
    }
});*/
$(document).ready(function() {
$(".filters-div").hide();
$(".filters-button").click(function(){
	$(".filters-div").show();
});
$(".remove-filters").click(function(){
	$(".filters-div").hide();
});


filters-button
$('#CallbackTime').daterangepicker({ singleDatePicker: true,timePicker: false,locale: {
            format: 'MM/DD/YYYY'
        } }, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  });
});
$('#timepicker4').datetimepicker({format: 'LT'});
$("#titleId").html(" ${status} CALLS LIST <span class='pull-right'><button class='btn btn-success btn-xs filters-button'  >FILTERS</button></span>");

function getMemberDetails()
{
$("#memberInfoDiv").html('<img id="ajaxImage" src="./images/Loading-data.gif" alt="Processing Image" style="margin-left:70px;height:60px;"/>');
if(batchId == "")
batchId = 0;
if(today == null)
	today = '';

var jObj={
		purposeId : purposeId,
		programId:programId,
		campId:campId,
		scheduleId:scheduleId,
		status:status,
		batchId :batchId,
		statusType:statusType,
		toDayDate : today,
		task:"scheduleWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getScheduleCallMemberDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			buildScheduleCallMemberDetailsCount(result,jObj);
		   });	
}

function buildScheduleCallMemberDetailsCount(result,jObj)
{
var purpose ;
var callFor ;
if(jObj.purposeId == 1)
purpose = "Invitation";
else if(jObj.purposeId == 2)
purpose = "Confirmation";
else if(jObj.purposeId == 3)
purpose = "Batch Change";
if(jObj.batchId == 0)
callFor = "Schedule";
else
callFor = "Batch";
var str='';

if(result.subList != null && result.subList .length > 0)
{
str+='<table class="table table-bordered m_top20">';
str+='<thead class="bg_d">';
str+='<th>Image</th>';
str+='<th>Name</th>';
str+='<th>Age</th>';
str+='<th>Committee Roll Position</th>';
str+='<th>Contact Number</th>';
str+='<th>Calling For</th>';
str+='<th>District</th>';
str+='<th>Constituency</th>';
str+='<th>Remarks</th>';
str+='<th>Status</th>';
str+='<th>Update</th>';
str+='</thead>';
for(var i in result.subList)
{
str+='<tr>';
str+='<td><img style="height:50px;width:50px;" class="media-object img-border profile-image img-circle" src="http://mytdp.com/images/cadre_images/'+result.subList[i].image+'" onerror="setDefaultImage(this);" alt="Profile Image"></td>';
str+='<td>'+result.subList[i].name+'</td>';
if(result.subList[i].age == null)
result.subList[i].age ='';
str+='<th>'+result.subList[i].age+'</th>';
if(result.subList[i].role == null)
result.subList[i].role = '';
str+='<td>'+result.subList[i].role+'</td>';
str+='<td>'+result.subList[i].mobileNumber+'</td>';
str+='<td>'+callFor+' '+purpose+'</td>';
str+='<td>'+result.subList[i].location+'</td>';
str+='<td>'+result.subList[i].constituency+'</td>';
str+='<td>'+result.subList[i].remarks+'</td>';
str+='<td>'+result.subList[i].status+'</td>';
str+='<td>'
str+='<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal" onclick="setCadreInfo(\''+result.subList[i].id+'\',\''+result.subList[i].inviteeId+'\',\''+result.subList[i].inviteeCallerId+'\');">Update Status</button>';
str+='</td>';
str+='</tr>';
}

str+='</table>';
}
else{
str+='No Data Available...';
}
$("#memberInfoDiv").html(str);

}
var tdpCadreId;
var inviteeId;
var inviteeCallerId;
function setCadreInfo(cadreId,inviteId,inviteCallerId)
{
tdpCadreId = cadreId;
inviteeId = inviteId;
inviteeCallerId = inviteCallerId;
ClearDiv();
}
function setDefaultImage(img){
	  img.src = "dist/img/profileimage.png";
   }
   function getPrograms()
   {
   var jObj={
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getAllProgramsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildPrograms(result);
		   });	
   }
   function buildPrograms(result)
   {
   var str = '';
  $('#programId').find('option:not(:first)').remove();
    for(var i in result)
	{
	if(result[i].id == programId)
	$("#programId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
	}
   
   }
   
    function getCampsForProgram()
   {
   //var programId = $("#programId").val();
   var jObj={
		programId:programId,
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getCampsByProgramIdAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildCamps(result);
		   });	
   }
   function buildCamps(result)
   {
   var str = '';
   $('#campId').find('option:not(:first)').remove();
    for(var i in result)
	{
	if(result[i].id == campId)
	$("#campId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
	}
   
   }
	function getSchedulesForCamp()
   {
   //var campId = $("#campId").val();
   var jObj={
		campId:campId,
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getSchedulesByCampIdAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildSchedules(result);
		   });	
   }
   function buildSchedules(result)
   {
   var str = '';
     $('#scheduleId').find('option:not(:first)').remove();
    for(var i in result)
	{
	if(result[i].id == scheduleId)
	$("#scheduleId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
	}
   }
   
   function getBatchesForSchedule()
   {
  // var scheduleId = $("#scheduleId").val();
   var jObj={
		scheduleId:scheduleId,
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getBatchesByScheduleIdAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildBatches(result);
		   });	
   }
   function buildBatches(result)
   {
   var str = '';
     $('#batchId').find('option:not(:first)').remove();
    for(var i in result)
	{
	if(result[i].id == batchId)
	$("#batchId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
	else
	$("#batchId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
   }
   
   function getCallStatusList()
   {
  
   var jObj={
	
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getCallStatusListAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildCallStatus(result);
		   });	
   }
   function buildCallStatus(result)
   {
   var str ='';
	for(var i in result)
	{
	
	 str+='<label class="checkbox-inline">';
	 str+='<input type="checkbox" id="callstatus'+result[i].id+'" name="callStatus" class="callstatuscheckbox" value="'+result[i].id+'" onclick="showHideCallStatus(\''+result[i].id+'\')">'+result[i].name+'';
	 str+='</label>';
	}
	str+='<label class="checkbox-inline callback">';
    str+='<input type="checkbox" name="callback"  id="callstatus0" name="callStatus" class="callstatuscheckbox" value="0" onclick="showHideCallStatus(0)"> Call Back';
    str+='</label>';
       $("#callStatusDiv").html(str);                    
   }
  
   
   function showHideCallStatus(id)
   {
	//ClearDiv();
	 if(id == 0)
	 {
	
     $('.Answered-div').hide();
	$('.callback-div').show();
	$('.switchoff-div').hide();
	}
	else if(id == 1)
	{
	$('.Answered-div').show();;
	$('.callback-div').hide();
	$('.switchoff-div').hide();
	}
	else
	{
	$('.Answered-div').hide();
	$('.callback-div').hide();
	$('.switchoff-div').show();
	}
	$(".callstatuscheckbox").prop( "checked", false );
	 $("#callstatus"+id).prop( "checked", true );
	 
  
   }
     function getScheduleStatusList()
   {
  
   var jObj={
	
		task:"programs"
		};
		$.ajax({
			  type:'POST',
			  url: 'getScheduleStatusListAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				buildScheduleStatus(result);
		   });	
   }
   function buildScheduleStatus(result)
   {
   var str ='';
	for(var i in result)
	{
	if(result[i].name.indexOf("Call Back") ==-1)
	{
	 str+='<label class="checkbox-inline">';
	 str+='<input type="checkbox" name="scheduleStatus" class="scheduleStatuscehckbox" value="'+result[i].id+'">'+result[i].name+'';
	 str+='</label>';
	 }
	 }
       $("#scheduleStatusDiv").html(str);                    
   }
 
 function ClearDiv()
 {
 $(".clearEl").val('');
 $("#batchId").val(0);
 $("#callBackTypeId").val(0);
 
 }
  function updateCadreStatus()
   {
   var str = '';
   var flag = false;
   $("#messageDiv").html("");
   var scheduleStatusId  = 0;
   var callstatusId = 0;
   var dataArray = new Array();
   var ramarks = $("#remarks").val();
   var batchId = $("#batchId").val();
   $(".scheduleStatuscehckbox").each(function()
   {
	if($(this).is(":checked"))
     scheduleStatusId = $(this).val();
   });
   $(".callstatuscheckbox").each(function()
   {
   if($(this).is(":checked"))
	callstatusId = $(this).val();
   });
   if(callstatusId == 0)
	   {
		str+='<font color="red">Select Call Status</font><br/>';;
		flag = true;
	   }
   if(callstatusId == 1)
   {
   
		if(ramarks.length == 0)
	   {
		str+='<font color="red">Remarks  Required</font><br/>';
		flag = true;
	   }
		if(batchId == 0)
	   {
		str+='<font color="red">Select  Batch</font><br/>';;
		flag = true;
	   }
		
		if(scheduleStatusId == 0)
	   {
		str+='<font color="red">Select Schedule Status</font><br/>';;
		flag = true;
	   }
   }
   if(flag == true)
   {
   $("#messageDiv").html(str);
   return;
   }
   if(callstatusId == 1)
   {
	$("#ajaxImage").show();
  }
  else
   {
	$("#ajaxImage2").show();
  }
  
  
   $("#messageDiv").html("");
   var obj = {
   batchId : batchId,
   ramarks : ramarks,
   callstatusId : callstatusId,
   scheduleStatusId:scheduleStatusId,
   inviteeId:inviteeId,
   inviteeCallerId:inviteeCallerId,
   tdpCadreId:tdpCadreId,
   status:"callstatus"

   }
   dataArray.push(obj);
   var jObj={
		dataArray : dataArray,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'updateCadreStatusForTrainingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
					$("#messageDiv").html("Status Updated Successfully").css("color","green");
					$("#myModal").modal('hide');
					 if(callstatusId == 1)
					   {
						$("#ajaxImage").show();
					  }
					  else
					   {
						$("#ajaxImage2").show();
					  }
		   })
    
   }
   
    function updateCallBackCadreStatus()
   {
   var str ='';
   var flag = false;
   $("#messageDiv").html("");
   var callstatusId = 0;
   var dataArray = new Array();
   var ramarks = $("#callbackremarks").val().trim();
   var callBackTypeId = $("#callBackTypeId").val();
   var Date = $("#CallbackTime").val();
   var time =$("#timepicker4").val();
   if(ramarks.length == 0)
   {
    str+='<font color="red">Remarks  Required</font><br/>';
	flag = true;
   }
    if(callBackTypeId == 0)
   {
    str+='<font color="red">Select  Call Back Type</font><br/>';
	flag = true;
   }
    if(Date.length == 0)
   {
    str+='<font color="red">Date Required</font><br/>';
	flag = true;
   }
    if(time.length == 0)
   {
    str+='<font color="red">Time Required</font><br/>';
	flag = true;
   }
   if(flag == true)
   {
   $("#messageDiv").html(str);
   return;
   }
   $("#messageDiv").html('');
   $("#ajaxImage1").show();
   var obj = {
   ramarks : ramarks,
   inviteeId:inviteeId,
   inviteeCallerId:inviteeCallerId,
   tdpCadreId:tdpCadreId,
   scheduleStatusId:callBackTypeId,
   callbackDate:Date,
   time:time,
   status:"callBackStatus"

   }
   dataArray.push(obj);
   var jObj={
		dataArray : dataArray,
		task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'updateCallBackCadreStatusForTrainingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				$("#messageDiv").html("Status Updated Successfully").css("color","green");
				$("#myModal").modal('hide');
				$("#ajaxImage1").hide();
		   })
    
   }
   
   function getCallerAgentDistricts()
   {
   $('#districtId').find('option:not(:first)').remove();
   var jObj={
	task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'getCallerAgentDistrictsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				if(result != null && result.length > 0)
				for(var i in result)
				$("#districtId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		   })
   }
   
      function getConstituenciesByDistrict()
   {
   var id = $("#districtId").val();
   $('#constituencyId').find('option:not(:first)').remove();
   if(id == 0)
   return;
   
   var jObj={
   id:id,
	task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'getCallerAgentConstituenciesByDistrictAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				if(result != null && result.length > 0)
				for(var i in result)
				$("#constituencyId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		   })
   }
         function getMandalsByConstiteuncy()
   {
   $('#mandalId').find('option:not(:first)').remove();
   var id = $("#constituencyId").val();
   if(id == 0)
   return;
   
   var jObj={
   id:id,
	task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'getCallerAgentMandalsByConstiteuncyAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
				if(result != null && result.length > 0)
				for(var i in result)
				$("#mandalId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		   })
   }
    function getVillagesByMandal()
   {
   $('#villageId').find('option:not(:first)').remove();
   var id = $("#mandalId").val();
   if(id == 0)
   return;
   
   var jObj={
   id:id,
	task:""
		};
		$.ajax({
			  type:'POST',
			  url: 'getCallerAgentVillagesByMandalAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 	
			  
				if(result != null && result.length > 0)
				for(var i in result)
				$("#villageId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		   })
   }
   
   function getFilterWiseDetails()
   {
   var typeOfStatus = "";
   $("#memberInfoDiv").html('<img id="ajaxImage" src="./images/Loading-data.gif" alt="Processing Image" style="margin-left:70px;height:60px;"/>');
   var districtId = $("#districtId").val();
   var constituencyId = $("#constituencyId").val();
   var mandalId = $("#mandalId").val();
   var villageId = $("#villageId").val();
   var status1 = $("#callstatusSelect").val();
   var committeLevelId = $("#committeLevelId").val();

	if(status1 == 'busy' || status1 == 'dialed' || status1 == 'undialed')
	{
		typeOfStatus = "callStatus"	;
		
	}
	else
	{
	typeOfStatus ="scheduleCallStatus";	
	
	}

	if(batchId == "")
	batchId = 0;
	if(today == null)
	today = '';
	var jObj={
		purposeId : purposeId,
		programId:programId,
		campId:campId,
		scheduleId:scheduleId,
		status:status1,
		batchId :batchId,
		statusType:typeOfStatus,
		toDayDate : "",
		districtId:districtId,
		constituencyId:constituencyId,
		mandalId:mandalId,
		villageId:villageId,
		committeeLevelId:committeLevelId,
		task:"filterWiseCount"
		};
		$.ajax({
			  type:'POST',
			  url: 'getScheduleCallMemberDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			buildScheduleCallMemberDetailsCount(result,jObj);
		   });	
   }
   function setDefault()
   {
   $("#districtId").val(0);
   $("#constituencyId").val(0);
   $("#mandalId").val(0);
   $("#villageId").val(0);
   $("#committeLevelId").val(0);
   $("#callstatusSelect").val(0);
   
   }
   
</script>
<script>
getMemberDetails();
getPrograms();
getCampsForProgram();
getSchedulesForCamp();
getBatchesForSchedule();
getCallStatusList();
getScheduleStatusList();
ClearDiv();
getCallerAgentDistricts();
</script>
</body>
</html>