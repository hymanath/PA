<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Calls List</title>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>

<link href="training/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="training/dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="training/dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<!--<link href="training/dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">-->
<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
<link href="training/dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

<style type="text/css">
body{font-size:13px !important;}
.filters-div
{
	background-color:#CCC;
	padding:10px;
}
 header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}

footer
{
	background-color:#5c2d25;
	padding:30px;
	color:#fff
}
.light-theme a, .light-theme span{
  float: none !important;
}
</style>
</head>
<body>
<!--<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<!--<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;">TRAINING PROGRAM</p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					  <!-- <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <li><a href="callCenterTrainingAgentDashBoard.action"><span>CALLERS DASHBOARD</span></a> </li>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>-->
<main>
  <section style="margin-top:40px;">
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
													<option value="0">All</option>
													<option value="dialed">Dialled</option>
													<option value="busy">User Busy</option>
													<option value="Switchoff">Switch off</option>
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
										<button class="btn btn-success" onclick="getFilterWiseDetails(0);">GET FILTER DETAILS</button>
									</div>
								</div>
							</div>
                        	<div id="memberInfoDiv" class="table-responsive table-condensed">
                            	
                            </div>
							<div id="paginationDivId"></div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
	</section>
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
						<div class="col-md-12 m_top20 clearDiv" id="scheduleStatusDiv">
                           
                        </div>
                        <div class="col-md-4 m_top20 clearDiv batchDiv" >
                            <label>Select Batch</label>
                            <select class="form-control" id="batchId" onchange="getMaxNumberForBatch();">
                                 <option value="0">Select</option> 
                            </select>
                        </div>
                        						
						<div class="col-md-2 m_top20 batchDiv">
					  <label>Max Members</label>
						  <input type="text" disabled="" class="form-control" id="batchMaxNo">
						</div>


					<div class="col-md-2 m_top20 batchDiv">
					  <label>Need Members</label>
					  <input type="text" disabled="" class="form-control" id="batchavailCnt">
					</div>
					<div id="laterSelDiv" style="display:none">
						<div class="col-md-4 m_top20">
                        	<label>Call Back Date</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                <input class="form-control clearEl" id="laterCallbackTime">
                            </div>
                        </div>
                    	<div class="col-md-4 m_top20">
                        	<label>Call Back Time</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                <input class="form-control clearEl" id="laterTimepicker4">
                            </div>
                        </div>
                        <div class="col-md-12 m_top20">
                            <label> Call Back Remarks</label>
                            <textarea class="form-control clearEl" id="laterCallbackremarks"></textarea>
                        </div>
						</div>
						<div class="col-md-12 m_top20 clearDiv" id="remarksDiv">
                             <label>Remarks <span style="color:red;"> * </span></label>
                            <ul class="callback-remarks">
                            	<textarea class="form-control remarkEle" id="remarks"></textarea>
                            </ul>
                        </div>
						<!-- <div class="col-md-12 m_top20 clearDiv" id="scheduleStatusDiv">
                           
                        </div> -->
						
						<!--<div id="laterSelDiv" style="display:none">
						<div class="col-md-4 m_top20">
                        	<label>Call Back Date</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                <input class="form-control clearEl" id="laterCallbackTime">
                            </div>
                        </div>
                    	<div class="col-md-4 m_top20">
                        	<label>Call Back Time</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                <input class="form-control clearEl" id="laterTimepicker4">
                            </div>
                        </div>
                        <div class="col-md-12 m_top20">
                            <label> Call Back Remarks</label>
                            <textarea class="form-control clearEl" id="laterCallbackremarks"></textarea>
                        </div>
						</div>-->
						
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
                        	<label>Call Back Date</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                <input class="form-control clearEl" id="CallbackTime">
                            </div>
                        </div>
                    	<div class="col-md-4 m_top20">
                        	<label>Call Back Time</label>
                            <div class="input-group">
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                <input class="form-control clearEl" id="timepicker4">
                            </div>
                        </div>
                        <div class="col-md-12 m_top20">
                            <label> Call Back Remarks <span style="color:red;"> * </span></label>
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
<!-- Modal -->
<div class="modal fade" id="myModalRemarks" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remarks</h4>
      </div>
      <div class="modal-body" id="remarkContentDiv">
        Body
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1"></h4>
		<div class="row">
			<h5 style="margin-left: 10px;"><span>Designation : <span id="designationId"></span></span><span class="pull-right" style="margin-right: 10px;">Location : <span id="locationId"></span></span></h5>
			
		</div>
	</div>
      <div class="modal-body">
        <div class="row">
		<div id="errMobileNumberDiv" class="text-danger" style="margin-left: 10px;"></div>
		<div id="successMobileNumberDiv" class="text-success" style="margin-left: 10px;"></div>
			<div class="col-md-4">
				<label>Mobile Number</label>
				<input class="form-control" type="text" id="updatedMobileId">
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-md-4">
				<button class="btn btn-success" id="updateMblNoId" style="display:none;" type="button" onclick="updateMobileNumberForCadre();">UPDATE MOBILE NUMBER</button>
			</div>
		</div>
		<div id="mobileUpdationDiv"></div>
		<center><img id="ajaxImageForMobileUpdation" src="./images/Loading-data.gif" alt="Processing Image" style="margin-left:70px;height:60px;display:none;"/></center>
	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary">Save changes</button>-->
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="upadateCallerModalDivId" style="display:none;">
			  <div class="modal-dialog">
				<div class="modal-content">
				
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="addModalTitleId" style="color:red;"><span id="addModalId"></span>Update Caller Feedback Details</h4>
				  </div>
				  
				  <div class="modal-body" id="upadateCallerModalBodyId">
					<div class="row">
					<div id="errorUpCallId" style="color:red"></div>
					<input type="hidden" id="hiddenTdpCadreId"/>
						<div class="col-md-4">
						<label>Call Purpose:</label>
						<select class="form-control" id="callPurposeId">
							</select>
						</div>
						<div class="col-md-4">
						<label>Call Status:</label>
						<select class="form-control" id="callStatusId">
							</select>
						</div>
						<div class="col-md-4">
						<label>Call Response:</label>
						<select class="form-control" id="callSupportTypId">
							</select>
						</div>
						<div class="col-md-12">
							<label>Description:</label>
							<textarea id="descriptionId" class="form-control"></textarea>
						</div>
					</div>
				  </div>
				  
				  <div class="modal-footer">
					<span class="pull-left" id="upadateCallerSuccessId"></span>
					<span id="updatefooterNameId"></span>
					<button type="button" class="btn btn-default btn-sm" id="closeButtonId" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary btn-sm" id="updateButtonId" onclick="updateCallerFeedBackDetails()">Save</button>
				  </div>
				  
				</div>
			  </div>
</div>
<!--<footer>
	<p class="text-center">All &copy; 2015 Telugu Desam Party</p>
</footer>-->
<script src="training/dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="training/dist/js/bootstrap.js" type="text/javascript"></script>
<!--<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>-->
<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
	
<script src="training/dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="js/cadre_response.js/cadre_response.js" type="text/javascript"></script>
<script type="text/javascript">
var purposeId = '${purposeId}';
var programId = '${programId}';
var campId = '${campId}';
var scheduleId = '${scheduleId}';
var status = '${status}';
var batchId = '${batchId}';
var statusType = '${statusType}';
var today = '${today}';
var campCallerId = '${campCallerId}';
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
/*
 var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
	$('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }
  var optionSet1 = {
	startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
  $('#CallbackTime span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#CallbackTime').daterangepicker(optionSet1, cb);

  $('#CallbackTime').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#CallbackTime').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#CallbackTime').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#CallbackTime').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
});
*/
$('#CallbackTime').daterangepicker({singleDatePicker: true,timePicker: false,minDate: new Date(),locale: {
            format: 'MM/DD/YYYY'
			
        } }, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  });
  
$('#laterCallbackTime').daterangepicker({singleDatePicker: true,timePicker: false,minDate: new Date(),locale: {
            format: 'MM/DD/YYYY'
			
        } }, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  });
 
});

$('#timepicker4').datetimepicker({format: 'LT'});
$('#laterTimepicker4').datetimepicker({format: 'LT'});
var type ;
if(purposeId == 1)
type = "Schedule";
else
type = "Batch";
$("#titleId").html(" <span id='titleText'>"+type+" ${status} CALLS LIST </span><span class='pull-right'><button class='btn btn-success btn-xs filters-button'  >FILTERS</button></span>");

function getMemberDetails(startIndex)
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
		campCallerId:campCallerId,
		startIndex:startIndex,
		maxIndex:20,
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
var membersList;
function buildScheduleCallMemberDetailsCount(result,jObj)
{

membersList;
var type ;
if(purposeId == 1)
type = "Schedule";
else
type = "Batch";
if(jObj.task == 'scheduleWiseCount')
{
$("#titleText").html(" "+type+" ${status} CALLS LIST");
}
else
{
$("#titleText").html(" "+type+" CALLS LIST");
}
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
membersList = result.subList;
str+='<table class="table table-bordered m_top20 table-condensed" style="font-size:12px;">';
str+='<thead class="bg_d">';
str+='<th>Image</th>';
str+='<th>Name</th>';
str+='<th>Age</th>';
str+='<th>Committee Roll Position</th>';
str += '<th>Level</th>';
str += '<th>Location</th>';
//str+='<th>Contact Number</th>';
str+='<th>Calling For</th>';
str+='<th>District</th>';
str+='<th>Constituency</th>';
str+='<th>Remarks</th>';
str+='<th>Status</th>';
str+='<th>Call<br/>Status</th>';
str+='<th>Update</th>';
//str+='<th>Caller FeedBack</th>';
str+='</thead>';
for(var i in result.subList)
{
str+='<tr>';
str+='<td><img style="height:50px;width:50px;" class="media-object img-border profile-image img-circle" src="images/cadre_images/'+result.subList[i].image+'" onerror="setDefaultImage(this);" alt="Profile Image"></td>';
if(result.subList[i].id !=null && result.subList[i].id>0){
	<c:if test="${sessionScope.USER.isAdmin == 'true'}">
		str+='<td><a style="cursor:pointer;" attr_cadreId = "'+result.subList[i].id+'" class="cadreDetailsCls">'+result.subList[i].name+'</a>';
	</c:if>
	<c:if test="${sessionScope.USER.isAdmin != 'true'}">
		str+='<td>'+result.subList[i].name+'';
	</c:if>
	
}
else{
	str+='<td>'+result.subList[i].name+'';
}

if(result.subList[i].mobileNumber != null)
 str += ' <br/>'+result.subList[i].mobileNumber+'';
 str+='&nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-edit" title="Click Here To Update Mobile Number"style="cursor:pointer" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal1" onclick="getCommitteeMembersDetailsForMobileUpdation(\''+result.subList[i].locationId+'\',\''+result.subList[i].locationType+'\',\''+result.subList[i].basicCommitteeTypeId+'\',\''+result.subList[i].name+'\',\''+result.subList[i].role+'\',\''+result.subList[i].roleCategory+'\',\''+result.subList[i].committeeLocation+'\',\''+result.subList[i].id+'\');"></i>';
 str +='</td>';
if(result.subList[i].age == null)
result.subList[i].age ='';
str+='<th>'+result.subList[i].age+'</th>';
if(result.subList[i].role == null)
result.subList[i].role = '';
str+='<td>'+result.subList[i].role+'</br>'+result.subList[i].roleCategory+' Committee </td>';
if(result.subList[i].committeeLocation != null && result.subList[i].committeeLocation.trim().length > 0)
{

	var arr = result.subList[i].committeeLocation.split(" ");
	var level = arr[arr.length-1];
	var location = "";
	for(var j in arr)
	{
	   if(j != arr.length-1)
	     location+= arr[j];
	}
	str += '<td>'+level+'</td>';
	str += '<td>'+location+'</td>';
}
else{

	str += '<td> </td>';
	str += '<td> </td>';
}
str+='<td>'+callFor+' '+purpose+'</td>';
str+='<td>'+result.subList[i].location+'</td>';
str+='<td>'+result.subList[i].constituency+'</td>';
if(result.subList[i].subList != null && result.subList[i].subList.length > 0)
{
str+='<td><i title="View Remarks" class="glyphicon glyphicon-list-alt" style="cursor:pointer"  type="button" data-toggle="modal" data-target="#myModalRemarks" onclick="showRemarks(\''+result.subList[i].id+'\');"></i></td>';
}
else
{
str+='<td></td>';
}

str+='<td>'+result.subList[i].status+'</td>';
str+='<td>'+result.subList[i].callStatus+'</td>';

if((result.subList[i].trainingCampBatch != null && result.subList[i].trainingCampBatch > 0)  && jObj.purposeId == 1)
{
str+='<td></td>';
}
else{
str+='<td>'
str+='<button type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal" onclick="setCadreInfo(\''+result.subList[i].id+'\',\''+result.subList[i].inviteeId+'\',\''+result.subList[i].inviteeCallerId+'\',\''+result.subList[i].trainingCampBatch+'\');populateFields(\''+result.subList[i].status+'\');">Update Status</button>';
str+='</td>';
}
//str+='<td ><div class="updateBtnCls" attr_tdp_cadre_id="'+result.subList[i].id+'"><i class="glyphicon glyphicon-ok pull-right" style="padding:4px;border-radius:50%;background:#ccc;color:#FFFFFF;color:green;right:25px;cursor:pointer"></i></div></td>';

str+='</tr>';
}

str+='</table>';
}
else{
str+='No Data Available...';
}
$("#memberInfoDiv").html(str);
  var itemsCount=result.totalCount;
	    var maxResults=jObj.maxIndex;
	   
	     if(jObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*20;
				if(jObj.task == 'scheduleWiseCount')
				getMemberDetails(num);
				else
				getFilterWiseDetails(num);
				
			}
		});
	}
}
function showRemarks(cadreId)
{
$("#remarkContentDiv").html('<img src="./images/Loading-data.gif" alt="Processing Image" style="margin-left:70px;height:60px;"/>');
var str ='';
var flag = false;
if(membersList != null && membersList.length > 0)
{
	for(var i in membersList)
	{
      if(cadreId == membersList[i].id)
	  {
	
		str+='<table class="table table-bordered">';
		str+='<tr>';
		str+='<th>Remark</th>';
		str+='<th>Date</th>';
		str+='</tr>';
	    for(var j=0;j<membersList[i].subList.length;j++)
		{
		console.log(membersList.subList)
			flag = true;
			str+='<tr>';
			str+='<td>'+ membersList[i].subList[j].name+'</td>';
			str+='<td>'+ membersList[i].subList[j].image+'</td>';
			str+='</tr>';
		}
		str+='</table>';
	  }
	}
}

if(!flag)
$("#remarkContentDiv").html('No Remarks');
else
$("#remarkContentDiv").html(str);
}
var tdpCadreId;
var inviteeId;
var inviteeCallerId;
var GbatchId;
function setCadreInfo(cadreId,inviteId,inviteCallerId,trainingCampBatchId)
{
tdpCadreId;
inviteeId;
inviteeCallerId;
GbatchId:
tdpCadreId = cadreId;
inviteeId = inviteId;
inviteeCallerId = inviteCallerId;
ClearDiv();
$(".callstatuscheckbox").prop( "checked", false );
$(".scheduleStatuscehckbox").prop( "checked", false );
   $('.Answered-div').show();;
   $('.callback-div').hide();
	$('.switchoff-div').hide();
	$("#messageDiv").html("");
	$("#ajaxImage").hide();
	
	if(trainingCampBatchId != null && trainingCampBatchId > 0)
	{
		$("#batchId").val(trainingCampBatchId);
		GbatchId = trainingCampBatchId;
	}

}
function populateFields(status)
{
	 $(".scheduleStatuscehckbox").each(function()
   {
		if($(this).attr("attr-text") == status)
		$(this).prop( "checked", true);
   });
   if(status == "Interested" && ${purposeId} == 2)
	{
 if(GbatchId > 0)
	   {
	    $(".batchDiv").show();
	   	$("#batchId").val(GbatchId);
		getMaxNumberForBatch();
		}
	}
	else
  showHideBatch();
 
}
function setDefaultImage(img){
	  img.src = "dist/img/profile.png";
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
	  /*if(result[i].id == batchId)
	   $("#batchId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
	   else*/
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
 
	 ClearDiv();
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
      var callPurposeId = '${purposeId}';
   console.log(callPurposeId);
	for(var i in result)
	{
		if(callPurposeId == 1)
		{
			if(i>0 && i<9 && result[i].name.indexOf("Call Back") ==-1 && result[i].id !=9)
			{
			 str+='<label class="checkbox-inline">';
			 str+='<input type="radio" name="scheduleStatus" class="scheduleStatuscehckbox" value="'+result[i].id+'" attr-text="'+result[i].name+'" onclick="showHideBatch();">'+result[i].name+'';
			 str+='</label>';
			}
		}
		else
		{
		
			if(i>0 && i<10 && i != 3 && result[i].name.indexOf("Call Back") ==-1 && result[i].id !=8  && result[i].id !=9)
			{
			
			 str+='<label class="checkbox-inline">';
			 str+='<input type="radio" name="scheduleStatus" class="scheduleStatuscehckbox" value="'+result[i].id+'" attr-text="'+result[i].name+'" onclick="showHideBatch();">'+result[i].name+'';
			 str+='</label>';
			}
		}
		
	 }
       $("#scheduleStatusDiv").html(str);                    
   }
 
 function ClearDiv()
 {
 $(".clearEl").val('');
 var callPurposeId = '${purposeId}';
 if(callPurposeId == 1)
 $("#batchId").val(0);
 $("#callBackTypeId").val(0);
 $(".remarkEle").val('');
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
    var callPurposeId = '${purposeId}';
	var laterCallBackDate = $("#laterCallbackTime").val();
	var laterCallBackTime = $("#laterTimepicker4").val();
	var laterRemarks = $("#laterCallbackremarks").val().trim();
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
	   if(scheduleStatusId != 3){
		   if(ramarks.length == 0)
		   {
			str+='<font color="red">Remarks  Required</font><br/>';
			flag = true;
		   }
		  
		   if(batchId == 0 && scheduleStatusId == 4 && callPurposeId ==1)
		   {
			str+='<font color="red" class="batcherr">Select  Batch</font><br/>';;
			flag = true;
		   }
		   
		   if(batchId == 0 && callPurposeId == 2 && scheduleStatusId == 10)
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
   status:"callstatus",
   laterCallBackDate:laterCallBackDate,
   laterCallBackTime:laterCallBackTime,
   laterRemarks:laterRemarks,
   callPurposeId:callPurposeId
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
				if(result.resultCode == 2)			  
				{
				$("#messageDiv").html("Batch full..").css("color","red");
					 if(callstatusId == 1)
					   {
						$("#ajaxImage").hide();
					  }
					  else
					   {
						$("#ajaxImage2").hide();
					  }
				}
				else{			  
					$("#messageDiv").html("Status Updated Successfully").css("color","green");
					$("#myModal").modal('hide');
					getMemberDetails(0);
					 if(callstatusId == 1)
					   {
						$("#ajaxImage").hide();
					  }
					  else
					   {
						$("#ajaxImage2").hide();
					  }
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
    var callPurposeId = '${purposeId}';
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
   callPurposeId:callPurposeId,
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
				getMemberDetails(0);
				
		   })
    
   }
   
   function getCallerAgentDistricts()
   {
   $('#districtId').find('option:not(:first)').remove();
   var jObj={
	   campCallerId:campCallerId,
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
   campCallerId:campCallerId,
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
    campCallerId:campCallerId,
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
    campCallerId:campCallerId,
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
   
   function getFilterWiseDetails(startIndex)
   {
   var typeOfStatus = "";
   $("#memberInfoDiv").html('<img id="ajaxImage" src="./images/Loading-data.gif" alt="Processing Image" style="margin-left:70px;height:60px;"/>');
   var districtId = $("#districtId").val();
   var constituencyId = $("#constituencyId").val();
   var mandalId = $("#mandalId").val();
   var villageId = $("#villageId").val();
   var status1 = $("#callstatusSelect").val();
   var committeLevelId = $("#committeLevelId").val();

	if(status1 == 'busy' || status1 == 'dialed' || status1 == 'undialed' || status1 == 'Switchoff')
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
		campCallerId:campCallerId,
		startIndex:startIndex,
		maxIndex:20,
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
   
   $(document).on("click",".cadreDetailsCls",function(){
		var cadreId=$(this).attr("attr_cadreId");
		var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');
	});
  function showHideBatch()
  {
	$("#laterSelDiv").hide();
	$("#remarksDiv").show();
	  var scheduleStatusId;
	   $(".scheduleStatuscehckbox").each(function()
	   {
		if($(this).is(":checked"))
		 scheduleStatusId = $(this).val();
		  
	   });
	   
	   if(scheduleStatusId == 3){
			$("#laterSelDiv").show();
			$("#remarksDiv").hide();
	   }
	   
	   
	   if(scheduleStatusId == 4 || scheduleStatusId == 10)
	   {
	   $(".batchDiv").show();
	   if(GbatchId > 0)
	   {
	   
	   	$("#batchId").val(GbatchId);
		getMaxNumberForBatch();
		}
	   }
	   else
	   {
	    $("#batchId").val(0);
	    $(".batchDiv").hide();
		$(".batcherr").html('');
	   }
  }
  
  function getMaxNumberForBatch()
  {
   $("#batchMaxNo").val('');
    $("#batchavailCnt").val('');
	
	var scheduleStatusId  = 0;
	var callPurpose ="";
  $(".scheduleStatuscehckbox").each(function()
   {
	if($(this).is(":checked"))
     scheduleStatusId = $(this).val();
   });
	
  if(scheduleStatusId == 10){
	  callPurpose="confirmation";
  }
  else if(scheduleStatusId == 4){
	  callPurpose="invitation";
  }
  var batchId = $("#batchId").val();
  if(batchId == 0)
  return;
  var jObj = {
  batchId : batchId,
  scheduleStatusId : scheduleStatusId,
  callPurpose : callPurpose,
  task:""
  }
  $.ajax({
			  type:'POST',
			  url: 'getMaxNumberForBatchAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)},
			  }).done(function(result){ 			  
			buildMaxNumberForBatch(result);
		   });	
  }
  function buildMaxNumberForBatch(result)
  {
  $("#batchMaxNo").val(result.totalCount);
  var cnt = result.totalCount - result.availableCount;
  $("#batchavailCnt").val(cnt);
  }
  
</script>
<script type="text/javascript">
var globalCadreId;
function getCommitteeMembersDetailsForMobileUpdation(locationId,locationType,basicCommitteeTypeId,name,role,roleCategory,locationLevel,tdpCadreId)
{
	globalCadreId = tdpCadreId;
	
	$("#errMobileNumberDiv").html('');
	$("#successMobileNumberDiv").html('');
	$("#updatedMobileId").val('');
	$("#myModalLabel1").html(name);
	var designation = roleCategory+" Committee "+role;
	$("#designationId").html(designation);
	$("#locationId").html(locationLevel);
	
	$("#mobileUpdationDiv").html('');
	$("#updateMblNoId").hide();
	$("#ajaxImageForMobileUpdation").show();
	var jObj={
		locationId:locationId,
		locationType:locationType,
		basicCommitteeTypeId:basicCommitteeTypeId,
		type:"committeembrs"
		};
		$.ajax({
		  type:'POST',
		  url: 'gettingCadreDetailsAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				if(result != null && result.length > 0){
					buildMobileUpdationDetails(result,roleCategory,locationLevel);
				}else{
					$("#ajaxImageForMobileUpdation").hide();
					$("#mobileUpdationDiv").html("NO DATA AVAILABLE...");
				}
		  });
	
}

function buildMobileUpdationDetails(result,roleCategory,locationLevel)
{
	var heading = locationLevel+" "+roleCategory+" Committee Members";
	var str='';
	str+='<h4>'+heading+'</h4>';
	str+='<div class="row">';
			str+='<div class="col-md-12 m_top10">';
				str+='<table class="table table-bordered">';
					str+='<thead>';
						//str+='<th></th>';
						str+='<th>Image</th>';
						str+='<th>Name</th>';
						str+='<th>Designation</th>';
						str+='<th>Mobile Number</th>';
					str+='</thead>';
					for(var i in result)
					{
						str+='<tr>';
							//str+='<td><input type="checkbox"</td>';
							str+='<td><img style="height:50px;width:50px;" class="media-object img-border profile-image img-circle" src="images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" alt="Profile Image"></td>';
							str+='<td>'+result[i].name+'</td>';
							str+='<td>'+result[i].role+'</td>';
							str+='<td>'+result[i].mobileNo+'</td>';
						str+='</tr>';
					}
				str+='</table>';
			str+='</div>';
		str+='</div>';
		
		$("#ajaxImageForMobileUpdation").hide();
		$("#mobileUpdationDiv").html(str);
		$("#updateMblNoId").show();
}

function updateMobileNumberForCadre()
{
	$("#errMobileNumberDiv").html('');
	$("#successMobileNumberDiv").html('');
	var updatedMobileNo = $("#updatedMobileId").val();
	
	if(isNaN(updatedMobileNo) || updatedMobileNo.indexOf(" ") != -1){
		$("#errMobileNumberDiv").html("Please Enter Numbers Only...");
		return;
	}
   if(updatedMobileNo.trim().length < 10){
		$("#errMobileNumberDiv").html("Please Enter Valid Mobile Number...");
		return;
   }
	
	var jObj={
		tdpCadreId:globalCadreId,
		mobileNo:updatedMobileNo
		};
		$.ajax({
		  type:'POST',
		  url: 'updateMobileNumberForCadreAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				if(result != null){
					if(result.resultCode == 0){
						if(result.message == "SUCCESS"){
							$("#successMobileNumberDiv").html("Mobile Number Is Successfully Updated...");
							// $("#myModal1").modal("close");
						}else{
							$("#errMobileNumberDiv").html("Invalid Mobile Number...");
						}
					}else{
						$("#errMobileNumberDiv").html("Sorry,Mobile Number Is Not Updated...");
					}
				}
		  });
}

</script>
<script>
getMemberDetails(0);
getPrograms();
getCampsForProgram();
getSchedulesForCamp();
getBatchesForSchedule();
getCallStatusList();
getScheduleStatusList();
ClearDiv();
getCallerAgentDistricts();
/* $(document).on("click",".updateBtnCls",function(){
	
}); */
</script>
</body>
</html>