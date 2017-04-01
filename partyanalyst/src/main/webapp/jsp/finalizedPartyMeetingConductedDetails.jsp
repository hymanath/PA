<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Party Meetings - ATR & MOM</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<!--<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">-->
<!--<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">-->
<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="training/dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>


<style>
.attachmentsUpload
{
	list-style:none;
}
.attachmentsUpload li
{
	display:block;
	background-color:#ddd;
	padding:8px;
	position:relative;
}
.attachmentsUpload li input
{
	width:80%;
}
.attachmentsUpload li .closeIcon , .attachmentsUpload li .clearFileCls
{
	font-size:16px;
	cursor:pointer;
	position:absolute;
	top:0px;
	right:10px;
	background-color:#CCC;
	border-radius:50%;
	padding: 0px 5px;
	font-weight: bold;
}
.panelWidthCols
{
	width:20%;
}
.panelWidthCols .panel-body .table thead th , .panelWidthCols .panel-body .table tr td
{
	padding:3px !important;
}
#meetingTableId thead th , #meetingTableId tr td
{
	font-size:12px !important
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
table thead th , table tbody tr td{text-align:center !important}
h1,h2,h3,h4,h5,h6,p,ul,table
{
	margin:0px !important;
}
.panelMeetings
{
	border-radius:0px;
}
.panelMeetings .panel-heading
{
	background:#fff;
	border-radius:0px;
	padding:5px;
	border:1px solid #ddd;
}
.panelMeetings .panel-heading .panel-title
{
	margin-top:15px;
}
.panelMeetings .panel-body
{
	padding:0px;
}
.text-capital
{
	text-transform:uppercase
}
.tableMeetings
{
	margin:0px;
}
.tableMeetings thead th
{
	font-size:11px;
	background:#FFF7CC;
}
.tableMeetings tr:first-child td:first-child
{
	color:#FFC501;
	font-size:18px;
	vertical-align:middle;
}
.tableMeetings tr:first-child td:nth-child(2)
{
	font-size:16px;
	color:#4A8CED
}
.tableMeetings tr:first-child td:nth-child(3)
{
	color:#F34F1A;
	font-size:16px
}
.block
{
	border:1px solid #ddd;
	padding:15px;
}
.graphList
{
	padding-left:10px;
}
.graphList li
{
	list-style:none;
	padding:2px;
	font-size:14px;
}
.iconPlanned
{
	background:#FEC601;
	height:10px;
	width:10px;
	display:inline-block;
	margin-right:3px;
	border-radius:50%;
}
.iconConducted
{
	background:#458AE9;
	height:10px;
	width:10px;
	display:inline-block;
	margin-right:3px;
	border-radius:50%;
}
.iconNotC
{
	background:#F64911;
	height:10px;
	width:10px;
	display:inline-block;
	margin-right:3px;
	border-radius:50%;
}
.m_top10
{
	margin-top:10px;
}
textarea { resize:none; }
.arrow_box_left {
    background: #fff none repeat scroll 0 0;
    border: 1px solid #ccc;
    padding: 8px;
    position: relative;
}
.arrow_box_left::after, .arrow_box_left::before {
    border: medium solid transparent;
    content: " ";
    height: 0;
    pointer-events: none;
    position: absolute;
    right: 100%;
    top: 15px;
    width: 0;
}
.arrow_box_left::after {
    border-color: rgba(221, 221, 221, 0) #fff rgba(221, 221, 221, 0) rgba(221, 221, 221, 0);
    border-width: 10px;
    margin-top: -10px;
}
.arrow_box_left::before {
    border-color: rgba(221, 221, 221, 0) #ccc rgba(221, 221, 221, 0) rgba(221, 221, 221, 0);
    border-width: 11px;
    margin-top: -11px;
}
.apptStatusTracking {
    border-left: 2px solid #ccc;
    margin-left: 8px;
    padding: 0;
    position: relative;
}
.apptStatusTracking li {
    list-style: outside none none;
    margin-left: 22px;
    margin-top: 3px;
}
.apptStatusTracking li::before {
    background: #ccc none repeat scroll 0 0;
    border-radius: 50%;
    content: " ";
    display: block;
    height: 15px;
    left: -9px;
    margin-top: 9px;
    position: absolute;
    width: 15px;
}
.uploadCssDiv {padding: 10px; margin-top: 10px; background: rgb(229, 229, 229) none repeat scroll 0% 0%;text-align:left}
.cloneFileCls {padding: 10px; margin-top: 10px; background: rgb(229, 229, 229) none repeat scroll 0% 0%;text-align:left}

.updatedCountStyle{
	font-weight:bold;
	color:green;
	text-decoration: underline;
	cursor: pointer;
	}
</style>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- YUI Dependency files (End) -->

</head>
<body>
<main>
	<div class="container">
    	<div class="row">
        	<section>
            	<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
            	
                <div class="panel-body">
                	<div class="row">
						<div class="col-md-4 col-sm-4 col-xs-12" style="margin-bottom:15px;left:750px;">
							<!--<div class="block">-->
								<label >Select Date</label>
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i><span class="caret"></span></span>
									<input type="text" class="form-control" id="datePickerBlockId">
									<span></span>
								</div>
							<!--</div>-->
                        </div>
                    </div>
				
                	<div class="panel panel-default" style="border:1px solid #ddd !important">
                    	<div class="panel-heading">
                        	<h4 class="panel-title">Conflict Committee Meeting Status Updates</h4>
                        </div>
                        <div class="panel-body">
                        	<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-3">
									<label>Meeting Level</label>
									<span id="meetingLocationErrorMessage" style="color: red;"></span>
										<select class="form-control" id="meetingLocationLevel"></select>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForMeetingsList" style="width:20px;height:20px;display:none;"/>
								</div>	
								
                            	<div class="col-md-4 col-xs-12 col-sm-3">
								
									<label>Select Meeting Name/Type Of Meeting</label>
									<select class="form-control" id="typeOfMeeting">
										<option> Select Meeting Type </option>
									</select>
									<span id="typeofMeetingErrorMessage" style="color: red;"></span>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgFortypeOfMeeting" style="width:20px;height:20px;display:none;"/>
									
                                </div>	
								<div class="col-md-4 col-xs-12 col-sm-4" id="stateShowId" style="display:none;">
										<label>State</label>
										<span id="stateErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="statesDivId">
										<!--<option>Select State</option>-->
										</select>
								</div>
							</div>
							<div class="row m_top10">
								<div class="col-md-3 col-xs-12 col-sm-6" id="DistrictShowId" style="display:none;">
										<label>District</label>
										<span id="districtErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="districtId">
										<!--<option>Select District</option>-->
										</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6" id="ConstShowId" style="display:none;">
										<label>Constituency</label>
										<span id="ConsErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="constituencyId" name="constBox">
										<!--<option>Select Constituency</option>-->
										</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6" id="ManTwnDivShowId" style="display:none;">
										<label>Mandal/Town/Division</label>
										<span id="ManErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="manTowDivId">
										<option>Select Mandal/Town/Division</option>
										</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6" id="VillWardShowId" style="display:none;">
									<label>Village/Ward</label>
										<span id="VillErrorMSgShow" style="color: red;"></span>
									<select class="form-control" id="villWardId">
									<option>Select Village/Ward</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6" id="statusShowId" style="display:none;">
									<label>Status</label>
									<select class="form-control" id="statusSelId">
									<option value="maybe">May Be</option>
									<option value="no">No</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-md-3 col-sm-6" >
									<button class="btn btn-success btn-sm btn-block" style="margin-top:25px;" id="viewMeetings">View </button>
									<div id="partyMettingStatusId"><span><img src="images/search.gif" style="display:none;"  id="partyMettingStatusIdImg"/></span>	
								</div>
								<div class="col-md-1" style="height: 44px; width: 10px;">
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForDist" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
								</div>
								<div class="col-md-1" style="height: 44px; width: 10px;">
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForcons" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
								</div>
								
								<div class="col-md-1" style="height: 44px; width: 10px;">
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForman" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
								</div>
                            </div>
							<div class="row m_top10" >
							
							
							
							
							
							
                        </div>
					 </div>
					<div class="row m_top20" style="padding:10px;">
						<div class="col-md-12" id="summaryDivId" style="display:none;">
							<h4 style="margin-bottom: 20px ! important;">CONFLICTS MEETINGS SUMMARY</h4>
							<table class="table table-bordered">
								<thead>
									<th>TOTAL MEETINGS (MAYBE)</th>
									<th>PENDING</th>
									<th>UPDATED</th>
									<th>YES</th>
									<th>NO</th>
									<th>DOCUMENTS</small></th>
								</thead>
								<tbody>
									<tr>
										<td id="totalMaybeCount">0</td>
										<td id="pendingCount">0</td>
										<td> <span class="updatedCountStyle updatedCountCls" id="updatedCount">0</span></td>
										<td id="thirdPartyYesCount">0</td>
										<td id="thirdPartyNoCount">0</td>
										<td id="documentsCount">0</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="col-md-12 m_top10" id="summaryNoDivId" style="display:none;">
							<h4 style="margin-bottom: 20px ! important;">NO STATUS MEETINGS SUMMARY</h4>
							<table class="table table-bordered">
								<thead>
									<th>TOTAL MEETINGS (NO)</th>
									<th>PENDING</th>
									<th>UPDATED</th>
								</thead>
								<tbody>
									<tr>
										<td id="totalNoCount">0</td>
										<td id="pendingNoCount">0</td>
										<td> <span class="updatedCountStyle updatedCountCls" id="updatedNoCount">0</span></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="col-md-12" style="margin-top:35px;">
							<center><img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForResults" style="margin-left:0px;margin-top: 30px;width:20px;height:20px;display:none;"/></center><div id="meetingDetailsTableId"></div>
						</div>
					</div>
                </div>
            </section>
        </div>
    </div>
</main>
<div class="modal fade" id="updateFinlizMtngBtnModal"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:60%">
  <form id="meetingsForm" name="meetingsForm">
    <div class="modal-content">
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="statusTitleId">UPDATE STATUS DETAILS:</h4>
      </div>
      <div class="modal-body" id="updateFinlizMtngBtnBody">
		<div id="errorId" style="color:red;"></div>
			<div class="input-group" id="updateDivId">
				<div class="row">
					<div class="col-md-4 col-xs-12 col-sm-6">	
						<label>Membership No<span class="text-danger"></span> :</label> <input type="text" class="form-control" id="memebershipId" name="updateMemberShipNumber"/><br>
					</div>
					<div class="col-md-4 col-xs-12 col-sm-6">	
						<label>Name<span class="text-danger">*</span> :</label> <input type="text" class="form-control" id="nameId" name="updateName"/><br>					
					</div>
					<div class="col-md-4 col-xs-12 col-sm-6">	
						<label>Mobile No<span class="text-danger">*</span> :</label> <input type="text" class="form-control" id="mobileNoId" name="updateMobileNumber" maxlength="10"/>
					</div>
				</div>
				<div class="row m_top20">
					<div class="col-md-12 col-xs-12 col-sm-6">	
						<label>Remark<span class="text-danger">*</span> :</label>
						<textarea type="text" class="form-control" id="remarkId" name="updateRemarks"></textarea>
					</div>
					<div class="col-md-4 col-xs-12 col-sm-6 m_top20" id="statusDivId" style="display:none;">
						<label>Conducted Status<span class="text-danger">*</span> :</label>
						<select class="form-control" id="statusId" name="updateStatusId">
							<option value="0">Select</option>
							<option value="Y">Yes</option>
							<option value="N">No</option>
						 </select>	
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-xs-12 col-sm-6 fileUploadDivId" style="margin-top: 20px;display:none;">
						<label>Add Attachments<span class="text-danger">*</span></label>
						<ul class="attachmentsUpload" id="extraUploadFileDiv">  
							<li>
								<input type="file" class="btn btn-mini cloneFileCls" name="imageForDisplay" id="uploadFileId0"  />
								<span class="clearFileCls" style="display:none;cursor:pointer;">x</span>  
							</li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-xs-12 col-sm-12 fileUploadDivId">
						<button type="button" title="Add Another File" class="btn btn-primary btn-sm" id="addFile"><i class="glyphicon glyphicon-plus"></i></button>
					</div>
				</div>
				<input type="hidden" value="" id="hiddenFinalizeMeetingId" name="updateFinalyzeMeetingId"/>			 
				<input type="hidden" value="" id="hiddenFinalizeMeetingStatus"/>			 
			</div>
		
      </div>
	  <div id="statusDetailsDivId" style="color:green;"></div>
	  <img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForUpdateMeetings" style="width:20px;height:20px;display:none;"/>
      <div class="modal-footer" style="padding:5px;">
	  <button type="button" class="btn btn-success" id="saveDetailsBtnId">SAVE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>        
      </div>
    </div>
	</form>
  </div>
</div>
<div class="modal fade" id="conflictsModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width:60%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Updated Details</h4>
      </div>
      <div class="modal-body">
        <div id="commentsBlock"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="commentsModalId" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document" style="width:60%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="mdlHeadingId">COMMENTS</h4>
      </div>
      <div class="modal-body">
        <div id="commentsDivId"></div>
      </div>
    </div>
  </div><!-- /.modal-dialog -->
</div>

<!--<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>-->
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<!--<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>-->
<script type="text/javascript">

 google.load("elements", "1", {
	  packages: "transliteration"
});

function onLoad() {
  var options = {
	  sourceLanguage:
		  google.elements.transliteration.LanguageCode.ENGLISH,
	  destinationLanguage:
		  [google.elements.transliteration.LanguageCode.TELUGU],
	  shortcutKey: 'alt+t',
	  transliterationEnabled: true
  };
  var control = new google.elements.transliteration.TransliterationControl(options);
	control.makeTransliteratable(['remarkId']);
}
google.setOnLoadCallback(onLoad);

$("#datePickerBlockId").daterangepicker({
	opens:'left',
	applyClass: 'btn-small btn-primary datePickerBlockCls',
	ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		 'Last 3 Months': [moment().subtract(3, 'month'),moment()],
		 'Last 6 Months': [moment().subtract(6, 'month'),moment()],
		 'Last 1 Year': [moment().subtract(1, 'year'),moment()]
		 }
});

//$("#datePickerBlockId").val(moment().subtract(29, 'days').format("MM/DD/YYYY") +'-'+ moment().format("MM/DD/YYYY"));
$("#datePickerBlockId").val(moment().startOf('month').format("MM/DD/YYYY") +'-'+ moment().endOf('month').format("MM/DD/YYYY"));

//Adding Class To "ranges" Class
$(document).find(".datePickerBlockCls").closest(".ranges").addClass("datePickerRangesCls");

$("#dateValueId").daterangepicker({singleDatePicker: true,maxDate: moment()})
getUserAccessLocationDetails();
	
	var stateArr = [];
	var distArr = [];
	var parlArr = [];
	var assmblyArr = [];
	var firstMeetingId;
	function getUserAccessLocationDetails(){
	    var jsObj={				
			task:"getUserAccessLocationDetails"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getUserAccessLocationDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#meetingLocationLevel").append('<option value="0">Select Location Type</option>');
		   var rslt = result.userAccessLevelList;
			if(rslt!=null && rslt.length>0){
				var isAddedMandal = false;
				var isAddedVillage = false;
				for(var i in rslt){
			if( rslt[i].levelId >1 && rslt[i].levelId >2){
					if(rslt[i].levelId ==4 || rslt[i].levelId ==5 || rslt[i].levelId ==6){
						var locationLevel = " Mandal/ Town/ Division";
						if(!isAddedMandal){
							$("#meetingLocationLevel").append('<option value="'+rslt[i].levelId+'">'+locationLevel+'</option>');
						}
						isAddedMandal = true;
					}else if(rslt[i].levelId ==7 || rslt[i].levelId ==8){
						locationLevel = " Village/ Ward";
						if(!isAddedVillage){
							$("#meetingLocationLevel").append('<option value="'+rslt[i].levelId+'">'+locationLevel+'</option>');
						}
						isAddedVillage = true;
					}else{
						$("#meetingLocationLevel").append('<option value="'+rslt[i].levelId+'">'+rslt[i].name+'</option>');
					}
					
				}
			}
		}
			$("#searchDataImgForMeetingsList").hide();
			
			var rsltValues = result.userAccessLevelValuesList;
			if(rsltValues!=null && rsltValues.length>0){
				for(var i in rsltValues){
					if(rsltValues[i].levelId==1){
						stateArr = rsltValues[i].levelValues;
					}else if(rsltValues[i].levelId ==2){
						distArr = rsltValues[i].levelValues;
					}else if(rsltValues[i].levelId ==3){
						parlArr = rsltValues[i].levelValues;
					}else if(rsltValues[i].levelId ==4){
						assmblyArr = rsltValues[i].levelValues;
					}
				}
			}
			
			/*if(distArr.length==0 && parlArr.length==0){
				//$('#meetingLocationLevel').val('1').trigger('change');
				$("#statesDivId").val(stateArr[0]).trigger('change');
				setTimeout(function(){
					//$('#typeOfMeeting').val(firstMeetingId).trigger('change');
					//$("#viewMeetings").trigger( "click" );
				},1000); 
			} else if(stateArr.length>0 && distArr.length>0 && parlArr.length==0){
				//$('#meetingLocationLevel').val('2').trigger('change');
				$('#statesDivId').val(stateArr[0]).trigger('change');
				setTimeout(function(){
					$('#typeOfMeeting').val(firstMeetingId).trigger('change');
					$('#districtId').val(distArr[0]).trigger('change');
					//$("#viewMeetings").trigger( "click" );
					
				},1000); 
				
			}else if(parlArr.length>0){
				//$('#meetingLocationLevel').val('3').trigger('change');
				$('#statesDivId').val(stateArr[0]).trigger('change');
				setTimeout(function(){
					$('#typeOfMeeting').val(firstMeetingId).trigger('change');
					$('#districtId').val(distArr[0]).trigger('change');
					
				},1000); 
				
				setTimeout(function(){
					$('select[name="constBox"] option:eq(2)').attr('selected', 'selected');
					//$("#viewMeetings").trigger( "click" );
				},2000);
			} */
		});
	}
	

	   /* $('#fromDate').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		//console.log(start.toISOString(), end.toISOString(), label);
	  }); 
	  
	  $('#endDate').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		//console.log(start.toISOString(), end.toISOString(), label);
	  });  */
	  
	  var cb = function(start, end, label) {
		 //console.log(start.toISOString(), end.toISOString(), label);
		  $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		 }

		 var optionSet1 = {
		 startDate: moment().subtract(29, 'days'),
		 endDate: moment(),
		 minDate: '01/01/2012',
		 maxDate: '12/31/2018',
		 //dateLimit: { days: 60 },
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
			 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			 'Last 3 Months': [moment().subtract(3, 'month'),moment()],
			 'Last 6 Months': [moment().subtract(6, 'month'),moment()],
			 'Last 1 Year': [moment().subtract(1, 'year'),moment()]
		 },
		 opens: 'left',
		 buttonClasses: ['btn btn-default'],
		 applyClass: 'btn-small btn-primary newsSubmitBtn',
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

		 var optionSet2 = {
		 startDate: moment().subtract(7, 'days'),
		 endDate: moment(),
		 opens: 'left',
		 ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		 }
		 };

		 $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

		 $('#reportrange').daterangepicker(optionSet1, cb);

		 //$('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
		 //$('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
		 /* $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
		 console.log("apply event fired, start/end dates are " 
		 + picker.startDate.format('MMMM D, YYYY') 
		 + " to " 
		 + picker.endDate.format('MMMM D, YYYY')
		 ); 
		 }); */
		 /* $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); }); */

   
   //$("#mainheading").html(" PARTY MEETINGS - MOM & ATR ");
</script>
<script>
	function getTheMeetingLevelDetails(){
		$("#searchDataImgForMeetingsList").show();
		var jsObj =	{}
		
		$.ajax(
		{
			type: "POST",
			url:"getTheMeetingLevelDetailsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#meetingLocationLevel").append('<option value="0">Select Location Type</option>');
			if(result!=null && result.length>0){
				for(var i in result){
				$("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
				}
			}
			$("#searchDataImgForMeetingsList").hide();
		});
	}
	
	$( "#typeOfMeeting" ).change(function() {
		$("#typeofMeetingErrorMessage").html("");
	});
	
	$( "#meetingLocationLevel" ).change(function() {
		//default Date
		$('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
		
		var locationLevelsArr=[];
	 $("#meetingLocationErrorMessage").html("");
	  $("#searchDataImgFortypeOfMeeting").show();
	  
	  var locationLevelId = $(this).val();
	  if($(this).val() == 4){
		  locationLevelsArr.push(4);
		  locationLevelsArr.push(5);
		  locationLevelsArr.push(6);
	  }/* else if($(this).val() == 7){
		  locationLevelsArr.push(7);
		  locationLevelsArr.push(8);
	  } */else{
		  locationLevelsArr.push(locationLevelId);
	  }
	  
		var jsObj =	{locationLevelsArr:locationLevelsArr}
		
		$.ajax(
		{
			type: "POST",
			url:"getMeetingTypesNewAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#typeOfMeeting  option").remove();
			$("#typeOfMeeting").append('<option value="0">Select Meeting Type</option>');
			if(result!=null && result.length>0){
				firstMeetingId=result[0].id;
				for(var i in result){
					if(i == 0)
						$("#typeOfMeeting").append('<option value="'+result[i].id+'" selected>'+result[i].meetingType+'</option>');
					else
						$("#typeOfMeeting").append('<option value="'+result[i].id+'">'+result[i].meetingType+'</option>');
				}
			}
			
			$("#searchDataImgFortypeOfMeeting").hide();
		});
		
	});
	
	$("#viewMeetings").click(function() {
		$("#summaryDivId").hide();
		$("#summaryNoDivId").hide();
		$("#meetingDetailsTableId").html("");
				
		if($("#meetingLocationLevel").val()==0){
			$("#meetingLocationErrorMessage").html("Please Select Meeting Location");
			return;
		}else {
			$("#meetingLocationErrorMessage").html('');
		}
		
		var locationLevel = $("#meetingLocationLevel").val();
		
		if($("#typeOfMeeting").val()==0){
			$("#typeofMeetingErrorMessage").html("Please Select Meeting");
			//alert("Pleas Select Meeting");
			return;
		}
		else{
			$("#typeofMeetingErrorMessage").html('');
		}
		var meetingType = $("#typeOfMeeting").val();
		
		var stateId=[];
		var districtId=[];
		var constituencyId=[];
		var mandalTownDivisonId=[];
		var villageWardId =[];
		
		
		if(locationLevel == 5 || locationLevel == 6){
			locationLevel = 4;
		}
		if(locationLevel == 7 || locationLevel == 8){
			locationLevel = 5;
		}
		
		
		
		if(locationLevel == 1){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				
				return;
			}else{
				$("#stateErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
			}
			//$("#searchDataImgForResults").show();
		}
		
		
		if(locationLevel == 2){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}
			else{
				$("#stateErrorMSgShow").html('');
			}
			 if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}
			else{
				
				$("#districtErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
			}
			//$("#searchDataImgForResults").show();
		}
		
		
		if(locationLevel==3){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}
			else{
				$("#stateErrorMSgShow").html('');
			}
			if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}
			else{
				$("#districtErrorMSgShow").html('');
			}
			if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				$("#ConsErrorMSgShow").html("Please Select Constituency");
				//alert("Please Select Constituency");
				return;
			}else{
				
				$("#ConsErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
				constituencyId = getStateDistrictAssemblySelection("constituency");
			}
			//$("#searchDataImgForResults").show();
		}	
		
		
		if(locationLevel==4){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}else{
				$("#stateErrorMSgShow").html('');
			}
			if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}else{
				$("#districtErrorMSgShow").html('');
			}
			if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				$("#ConsErrorMSgShow").html("Please Select Constituency");
				//alert("Please Select Constituency");
				return;
			}else{
				$("#ConsErrorMSgShow").html('');
			}
			if($("#manTowDivId").val()=="" || $("#manTowDivId").val()=="Select Mandal/Town/Division"){
				$("#ManErrorMSgShow").html("Please Select Mandal/Town/Division");
				//alert("Please Select Mandal/Town/Division");
				return;
			}else{
				$("#ManErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
				constituencyId = getStateDistrictAssemblySelection("constituency");
				mandalTownDivisonId.push($("#manTowDivId").val());
			}
			//$("#searchDataImgForResults").show();
		}
		
		
		if(locationLevel==5){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}else{
				$("#stateErrorMSgShow").html('');
			}
			if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}else{
				$("#districtErrorMSgShow").html('');
			}
			if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				$("#ConsErrorMSgShow").html("Please Select Constituency");
				//alert("Please Select Constituency");
				return;
			}else{
				$("#ConsErrorMSgShow").html('');
			}
			if($("#manTowDivId").val()=="" || $("#manTowDivId").val()=="Select Mandal/Town/Division"){
				$("#ManErrorMSgShow").html("Please Select Mandal/Town/Division");
				//alert("Please Select Mandal/Town/Division");
				return;
			}else{
				$("#ManErrorMSgShow").html('');
			}
			if($("#villWardId").val()=="" || $("#villWardId").val()=="Select Village/Ward"){
				$("#VillErrorMSgShow").html("Please Select Village/Ward");
				//alert("Please Select Village/Ward");
				return;
			}else{
				
				$("#VillErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
				constituencyId = getStateDistrictAssemblySelection("constituency");
				mandalTownDivisonId.push($("#manTowDivId").val());
				villageWardId.push($("#villWardId").val());
			}
			//$("#searchDataImgForResults").show();
		}
		
		$("#villWardId").change(function(){
			$("#VillErrorMSgShow").html();
		});
		
		/* var startDate = $(".newsSubmitBtn").closest(".range_inputs").find(".dp_startDate").val();
		var endDate = $(".newsSubmitBtn").closest(".range_inputs").find(".dp_endDate").val(); */
		var dates = $("#datePickerBlockId").val();
		var startDate = "";
		var endDate ="";
		if(dates !=null && dates.length>0){
			startDate = dates.split("-")[0];
			endDate =dates.split("-")[1];
		}
		
		var status = $("#statusSelId").val();
		
		$("#searchDataImgForResults").show();
		var jsObj =	{
						meetingType:meetingType,
						locationLevel:locationLevel,
						sateId:stateId,
						districtId:districtId,
						constituencyId:constituencyId,
						mandalTownDivisonId:mandalTownDivisonId,
						villageWardId:villageWardId,
						startDate:startDate,
						endDate:endDate,
						mayBe : status
					}
					
		$.ajax(
		{
			type: "POST",
			url:"getFinalAllMeetingsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#searchDataImgForResults").hide();
			if(status != null && (status == "maybe" || status == "no")){
				if(status == "maybe")
					$("#summaryDivId").show();
				else
					$("#summaryNoDivId").show();
			}else{
				$("#summaryDivId").show();
				$("#summaryNoDivId").show();
			}
			buildFinalMeeting(result,status);
		});
	});
function buildFinalMeeting(result,status){
		var str ='';
		
		var mayBeTtl = 0;
		var pendgCnt = 0;
		var updatdCnt = 0;
		var yesCnt = 0;
		var noCnt = 0;
		var docsCnt = 0;
		
		var noTtl = 0;
		var noPendngCnt = 0;
		var noUpdCnt = 0;
	if(result != null && result.length>0){
		docsCnt = result[0].count;
			str+='<h4 style="text-transform:uppercase;margin-bottom: 20px ! important;">'+status+' status meetings details</h4>';
			str+='<table class="table table-bordered" id="meetingsTable">';
				str+='<thead style="background-color:#ccc;">';
					str+='<th>DISTRICT</th>';
					str+='<th>CONSTITUENCY</th>';
					str+='<th>MEETING LOCATION</th>';
					str+='<th>MEETING NAME</th>';
					//str+='<th>CONDUCTED (updated by info cell?)</th>';
					//str+='<th>CONDUCTED (updated by ivr?)</th>';
					str+='<th>UPDATE STATUS</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].districtName+'</td>';
						str+='<td>'+result[i].constName+'</td>';
						str+='<td>'+result[i].location+'</td>';
						str+='<td>'+result[i].meetingName+'</td>';
						/*if(result[i].isConducted != null && result[i].isConducted =="Y"){
							str+='<td>YES</td>';
						}else if(result[i].isConducted != null && result[i].isConducted =="N"){
							str+='<td>NO</td>';
						}else{
							str+='<td class="text-center">-</td>';
						}
						
						if(result[i].conductedDate != null && result[i].conductedDate =="Y"){
							str+='<td>YES</td>';
						}else if(result[i].conductedDate != null && result[i].conductedDate =="N"){
							str+='<td>NO</td>';
						}else{
							str+='<td class="text-center">-</td>';
						}*/
						
						str+='<td>';
						str+=' <i class="glyphicon glyphicon-edit updateCls" data-toggle="tooltip" data-placement="bottom" style="margin-right: 10px;cursor:pointer;" title="Click here to Update Status" id="updateStatus'+i+'Id" attr_meeting_Loc_Id="'+result[i].partyMeetingId+'" attr_status="'+result[i].stsStr+'"></i>';
						//str+='<button class="btn btn-success updateCls" id="updateStatus'+i+'Id" attr_meeting_Loc_Id="'+result[i].partyMeetingId+'">UPDATE</button>';
						if(result[i].iscommentsAvailable != null && result[i].iscommentsAvailable == "true" ){
							str+=' <i class="glyphicon glyphicon-comment commentsCls" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:black;" id="commentsId" title="Click here to view comment details" attr_meeting_Loc_Id="'+result[i].partyMeetingId+'" ></i>';
						}
					str+='</td>';
					str+='</tr>';
					
					if(result[i].stsStr != null && result[i].stsStr == "maybe")
						mayBeTtl = mayBeTtl+1;
					else
						noTtl = noTtl+1;
					
					if(result[i].stsStr != null && result[i].stsStr == "maybe"){
						if(result[i].iscommentsAvailable != null && result[i].iscommentsAvailable == "true"){
							updatdCnt = updatdCnt + 1;
							if(result[i].thirdPartyStatus != null && result[i].thirdPartyStatus == "Y")
								yesCnt = yesCnt + 1;
							else if(result[i].thirdPartyStatus != null && result[i].thirdPartyStatus == "N")
								noCnt = noCnt + 1;
						}
					}
					else{
						if(result[i].iscommentsAvailable != null && result[i].iscommentsAvailable == "true")
							noUpdCnt = noUpdCnt + 1;
					}
				}
				pendgCnt = mayBeTtl - updatdCnt;
				noPendngCnt = noTtl - noUpdCnt;
					}else{
						str+='No Meetings Found';
					}
				str+='</tbody>';
			str+='</table>';
		$("#meetingDetailsTableId").html(str);
		$('#meetingsTable').dataTable({
			"iDisplayLength": 10,
				"aLengthMenu": [[10,50,100, 200, 500, -1], [10,50,100, 200, 500, "ALL"]]			
			});
			
			if(status != null && (status == "maybe" || status == "no")){
				if(status == "maybe"){
					$("#totalMaybeCount").html(mayBeTtl);
					$("#pendingCount").html(pendgCnt);
					$("#updatedCount").html(updatdCnt);
					$("#thirdPartyYesCount").html(yesCnt);
					$("#thirdPartyNoCount").html(noCnt);
					$("#documentsCount").html(docsCnt);
				}
				else{
					$("#totalNoCount").html(noTtl);
					$("#pendingNoCount").html(noPendngCnt);
					$("#updatedNoCount").html(noUpdCnt);
				}
			}else{
				$("#totalMaybeCount").html(mayBeTtl);
				$("#pendingCount").html(pendgCnt);
				$("#updatedCount").html(updatdCnt);
				$("#thirdPartyYesCount").html(yesCnt);
				$("#thirdPartyNoCount").html(noCnt);
				$("#documentsCount").html(docsCnt);
				$("#totalNoCount").html(noTtl);
				$("#pendingNoCount").html(noPendngCnt);
				$("#updatedNoCount").html(noUpdCnt);
			}
	
}
	function getStateDistrictAssemblySelection(forLocation){
		var resultArr = [];
		if(forLocation=="state"){
			var state = $("#statesDivId").val();
			var stateId = [];
			if(state==0){
				if(stateArr.length>0){
					stateId = stateArr;
				}else{
					stateId.push(1);
					stateId.push(36);
				}
			}else{
				stateId.push(state);
			}
			resultArr = stateId;
		}else if(forLocation=="district"){
			var district = $("#districtId").val();
			var districtId = [];
			if(district==0){
				if(distArr.length>0){
					districtId = distArr;
				}else{
					districtId.push(0);
				}
			}else{
				districtId.push(district);
			}
			resultArr = districtId;
			
			//getMandalsForDistrictId(districtId);
			//getVillagesForDistrictId(districtId);
		}else if(forLocation=="constituency"){
			var assembly = $("#constituencyId").val();
			var constituencyId = [];
			if(assembly==0){
				if(assmblyArr.length>0){
					constituencyId = assmblyArr;
				}else{
					constituencyId.push(0);
				}
			}else{
				constituencyId.push(assembly);
			}
			
			resultArr = constituencyId;
		}
		return resultArr;
		
	}
	function getDistrictsForStates(state){
		$("#searchDataImgForDist").show();
		
		$("#districtId  option").remove();
		//$("#districtId").append('<option value="">Select District</option>');
		$("#constituencyId  option").remove();
		$("#constituencyId").append('<option value="0">ALL</option>');
		$("#manTowDivId  option").remove();
		$("#manTowDivId").append('<option value="0">ALL</option>');
	   var jsObj=
	   {				
					stateId:state,
					elmtId:"districtList_d",
					type:"default",
					task:"getDistrictsForState"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getNewDistrictsOfStateSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForDist").hide();
		    $("#districtId").append('<option value="0">ALL</option>');
			
			for(var i in result){
				if(distArr.length>0){
					if($.inArray(result[i].id, distArr) > -1){
					$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				   }
				}else{
					 $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			//meetingLevelWiseHideShow();
			//getConstituenciesForDistricts('');
			/* setTimeout(
			function(){		
				getMandalVillageDetails(4);
				setTimeout(
			function(){						
				getMandalVillageDetails(5);
			}, 1000);
			
			}, 2000); */
	   });
	}
	function getConstituenciesForDistricts(district){
	   
	   $("#manTowDivId  option").remove();
		$("#manTowDivId").append('<option value="0">ALL</option>');
		$("#villWardId  option").remove();
		$("#villWardId").append('<option value="0">ALL</option>');
	   
		$("#constituencyId  option").remove();
		//$("#constituencyId").append('<option value="">Select Constituency</option>');
		var stateId = $("#statesDivId").val();
		
		var distArrTemp = [];
		if(district==0){
			distArrTemp = distArr; //8888
		}else{
			distArrTemp.push(district);
		}
		 $("#searchDataImgForcons").show();
		var jsObj={				
			districtId:distArrTemp,
			stateId:stateId,
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForcons").hide();
		   $("#constituencyId").append('<option value="0">ALL</option>');
			for(var i in result){
			   if(assmblyArr.length>0){
				   if($.inArray(result[i].locationId, assmblyArr) > -1){
						$("#constituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
			   }
				else{
					$("#constituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
				
			}
			
		});
	}
	$( "#statesDivId" ).change(function() {
		$("#stateErrorMSgShow").html("");
		getDistrictsForStates(this.value);
	});
	$( "#districtId" ).change(function() {
		$("#districtErrorMSgShow").html("");
		getConstituenciesForDistricts(this.value);
	});
	//Location Showing and Hiding
		$("#meetingLocationLevel").change(function(){
			getDistrictsForStates(1);
			$("#districtId").val($("#districtId option:first").val());
			$("#constituencyId").val($("#constituencyId option:first").val());
			$("#manTowDivId").val($("#manTowDivId option:first").val());
			$("#villWardId").val($("#villWardId option:first").val());

				 $("#statesDivId").html("");
				
				//$("#statesDivId").append('<option value=""> Select State </option>');
				$("#statesDivId").append('<option value=0> ALL </option>');
				
				if($.inArray(1, stateArr) > -1){
					$("#statesDivId").append('<option value=1 selected> AndhraPradesh </option>');
				}
				if($.inArray(36, stateArr) > -1){
					$("#statesDivId").append('<option value=36> Telangana </option>');
				} 
				<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					$(".stateCls").each(function(){
						if($(this).prop('checked')==true){
					
						 if($(this).val()==1){
							$("#statesDivId").html("");
							$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
							getDistrictsForStates($( "#statesDivId" ).val());
						}else if($(this).val()==36){
							$("#statesDivId").html("");
							$("#statesDivId").append('<option value=36> Telangana </option>');
							getDistrictsForStates($( "#statesDivId" ).val());
						}else if($(this).val()==0){
							$("#statesDivId").html("");
							$("#statesDivId").append('<option value=0> All </option>');
							$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
							$("#statesDivId").append('<option value=36> Telangana </option>');
							getDistrictsForStates(this.value);
							}
						}
					});
				</c:if>	

meetingLevelWiseHideShow();	
});			
			
		 $("#constituencyId").change(function(){
			$("#ConsErrorMSgShow").html("");
			getMandalVillageDetails(4);
		}); 
		$("#manTowDivId").change(function(){
					$("#ManErrorMSgShow").html("");
				if($("#manTowDivId").val() !=null && $("#manTowDivId").val().length>0 && $("#manTowDivId").val()>0){
					getMandalVillageDetails(5);
				}
			
		});
function getMandalVillageDetails(locationLevel){
		//$("#manTowDivId  option").remove();
		//$("#villWardId option").remove();
		var stateId = $("#statesDivId").val();
		var districtId = $("#districtId").val();
		var constituencyId = $("#constituencyId").val();
		var distArrTemp = [];
		if(districtId==0){
			distArrTemp = distArr;
		}else{
			distArrTemp.push(districtId);
		}
		
		var mandalId = "0";
		if(locationLevel==4){
			$("#manTowDivId").html("");
		}
		if(locationLevel==5){
			mandalId = $("#manTowDivId").val();
			//$("#manTowDivId").html("")
			if(mandalId >0){
				$("#VillWardShowId").show();
				$("#villWardId").html("");
			}else{
				$("#VillWardShowId").hide();
			}
		}
		var assmblyArrTemp = [];
		if(constituencyId==0){
			//assmblyArrTemp = assmblyArr;	
			var divId = "#manTowDivId";
			//divId = "#villWardId";//teja
			//$(divId).html("");
			if(locationLevel ==5){
				  divId = "#villWardId";
			}
		}/* else if($("#manTowDivId").val()==null || $("#manTowDivId").val() == 0){
			$("#villWardId  option").remove();
			$("#villWardId").append('<option value="">ALL</option>');
			assmblyArrTemp.push(constituencyId);
		} */
		else{
			assmblyArrTemp.push(constituencyId);
		}
		$("#searchDataImgForman").show();
	   var jsObj={				
			stateId : stateId,
			districtId : distArrTemp,
			constituencyId : assmblyArrTemp,//228
			mandalId : mandalId,
			locationLevel : locationLevel,
			task:""
		}
		
		$.ajax({
			  type:'GET',
			  url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForman").hide();
			   var divId = "#manTowDivId";
				  if(locationLevel ==5){					  
				  divId = "#villWardId";
				 }  
			$(divId).append("<option value='0'>ALL</option>");
			for(var i in result){
				$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
			if(locationLevel ==4)
					 getMandalVillageDetails(5);
		});
	}
		
	function updateMeeting(partyMeetingId){
		window.open('meetingDetailsList.action?partyMeetingId='+partyMeetingId+'', '_blank');
		//alert(meetingType+"--"+meetingLocationLevel);
	}
	
	$(document).on('click','.getSummary', function() {
	  var fromType = $(this).attr("attr_type");
	  var jsObj =	{
			meetingId : $(this).attr("attr_meetingId"),
			type:$(this).attr("attr_type")
			}
			
		$.ajax({
			type: "POST",
			url:"getSummaryForAMeetingAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(fromType=="momText"){
				if(result!=null && result.minutes!=null && result.minutes.length>0){
					var str='';
					for(var i in result.minutes){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result.minutes[i]+"</div>";
					}
					$("#modelBody").html(str);
				}
				
				$("#modelTitle").html(result.subName);
				$("#myModal").modal('toggle');
			}else if(fromType=="atrText"){
				if(result!=null && result.atrPoints!=null && result.atrPoints.length>0){
					var str='';
					for(var i in result.atrPoints){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result.atrPoints[i]+"</div>";
					}
					$("#modelBody").html(str);
				}
				
				$("#modelTitle").html(result.subName);
				$("#myModal").modal('toggle');
			}else{
				if(result!=null && result.docsList!=null && result.docsList.length>0){
					var str='';
					for(var i in result.docsList){
						str+='<div style="border-bottom:1px dashed; padding:5px;">';
						str+='<a target="_tab" href="https://mytdp.com/DocFiles/'+result.docsList[i].path+'">'+result.docsList[i].name+'</a>';
						str+='</div>';
					}
					$("#modelBody").html(str);
				}
				$("#modelTitle").html(result.docsList[0].subName);
				$("#myModal").modal('toggle');
			}
			
		});
	 
  });

</script>
<script type="text/javascript">
$(function () {
    /* $('.districtMeetings').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: false,
                alpha: 15
            },
			backgroundColor: 'transparent'
        },
		legend: {
                enabled: false,
                align: 'center',
                x: 10,
                verticalAlign: 'left',
                y: 20,
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 40,
                depth: 35,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
            data: [
                ['Nara Lokesh Meeting with Mandal Presidents', 1000],
                ['Lunch Section Party Office', 3000],
                ['Lokesh Meeting', 1000]
            ]
        }]
    }); */
});

$(document).on("click",".datePickerBlockCls",function(){
	getLevelWiseMeetingDetails();
});
$(document).on("click", ".datePickerRangesCls li",function(){
	var selc_text = $(this).text();
		if(selc_text!="Custom Range"){				
				getLevelWiseMeetingDetails();
		}	
});
//getLevelWiseMeetingDetails();
function getLevelWiseMeetingDetails(){
	
		$("#levelBuildingId").html('');
	
		var value = $("#datePickerBlockId").val();
		var fromDate = "";
		var toDate ="";
		if(value !=null && value.length>0){
			fromDate = value.split("-")[0];
			toDate =value.split("-")[1];
		}
		var stateId = 0;
		$("#loadingImgForLevelId").show();
		$(".stateCls").each(function(){
			if($(this).prop('checked')==true){
			stateId = $(this).val();	
			}
		});
		var jsObj={
			fromDate:fromDate,
			toDate	:toDate,
			stateId : stateId
		}
		
		$.ajax({
			type: "POST",
			url:"getLevelWiseMeetingDetailsAction.action",
			data:{task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#loadingImgForLevelId").hide();
			var str='';
			if(result !=null && result.length>0){
				var totalPlanned=0;
				var totalConducted=0;
				var totalNotConducted=0;
				var noOfBlocks = result.length;
				var totalNotUpdated = 0;
				for(var i in result){
					totalPlanned=totalPlanned+result[i].invitedCount+result[i].nonInviteeCount+result[i].attendedCount;
					totalConducted= totalConducted + result[i].invitedCount;
					totalNotConducted= totalNotConducted + result[i].nonInviteeCount;
					totalNotUpdated= totalNotUpdated + result[i].attendedCount;
					if(noOfBlocks == 3)
					{
						str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					}else if(noOfBlocks == 4)
					{
						str+='<div class="col-md-3 col-xs-12 col-sm-6">';
					}else if(noOfBlocks == 5)
					{
						str+='<div class="col-md-3 col-xs-12 col-sm-6 panelWidthCols">';
					}else if(noOfBlocks == 6)
					{
						str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					}
					
					
                       	  str+='<div class="panel panel-default panelMeetings">';
                        	  str+='<div class="panel-heading">';
                        	    str+='<div class="row">';
                                	str+='<div class="col-md-6 col-xs-12 col-sm-7">';
                                    	str+='<h4 class="panel-title text-capital">'+result[i].name+' level meetings</h4>';
                                    str+='</div>';
                                    str+='<div class="col-md-6 col-xs-12 col-sm-5">';
										str+='<div class="meetings'+i+'" style="height:60px;">ssss'+i+'</div>';
                                    str+='</div>';
                                str+='</div>';
                      	      str+='</div>';
                        	  str+='<div class="panel-body">';
                              	str+='<table class="table table-bordered tableMeetings">';
                                	str+='<thead>';
                                    	str+='<th>Planned</th>';
                                        str+='<th>Conducted</th>';
                                        str+='<th>Not Conducted</th>';
                                    str+='</thead>';
                                    str+='<tbody>';
                                    	str+='<tr>';
										var plannedCount=result[i].invitedCount+result[i].nonInviteeCount+result[i].attendedCount;
                                        	str+='<td rowspan="2">'+plannedCount+'</td>';
                                            str+='<td>'+result[i].invitedCount+'</td>';
                                            str+='<td>'+result[i].nonInviteeCount+'</td>';
                                        str+='</tr>';
										
										//calculation
										
										var conductedPerc =0;
										var notConductedPerc=0;
										if(plannedCount !=null && plannedCount>0){
											if(result[i].invitedCount >0){
												conductedPerc = ((result[i].invitedCount/plannedCount)*100).toFixed(2);
											}
											if(result[i].nonInviteeCount >0){
												notConductedPerc = ((result[i].nonInviteeCount/plannedCount)*100).toFixed(2);
											}
										}
										
										
                                        str+='<tr>';
                                        	str+='<td>'+conductedPerc+'%</td>';
                                           str+=' <td>'+notConductedPerc+'%</td>';
                                        str+='</tr>';
										var notUpdatedPerc = 0;
										if(result[i].attendedCount >0){
												notUpdatedPerc = ((result[i].attendedCount/plannedCount)*100).toFixed(2);
											}
										str+='<tr><td class="text-muted text-center" colspan="3">Not Updated : <span class="text-warning">'+result[i].attendedCount+'   ('+notUpdatedPerc+'%)</span></td></tr>';
                                    str+='</tbody>';
                                str+='</table>';
								//str+='<p class="text-muted text-center">demo : 9</p>';
                             str+=' </div>';
                      	 str+=' </div>';
                       str+=' </div>';
				}				
				
				
				
				$("#tPlannedId").html(totalPlanned);
				$("#tConductedId").html(totalConducted);
				$("#tNotConductedId").html(totalNotConducted);
				$("#totalMeetingsId").html(totalPlanned);
				
				var conductPerc =0 ;
				var notConductPerc = 0;
				var notUpdatePerc = 0;
				if(totalPlanned !=null && totalPlanned>0){
					if(totalConducted >0){
						conductPerc = ((totalConducted/totalPlanned)*100).toFixed(2);
					}
					if(totalNotConducted>0){
						 notConductPerc= ((totalNotConducted/totalPlanned)*100).toFixed(2);
					}
					if(totalNotUpdated>0){
						 notUpdatePerc= ((totalNotUpdated/totalPlanned)*100).toFixed(2);
					}
				}
				$("#tConductedPercId").html(''+conductPerc+' %');
				$("#tNotConductedPercId").html(''+notConductPerc+' %');
				$("#tNotUpadtedPercId").html(''+notUpdatePerc+' %');
				
				$("#levelBuildingId").html(str);
				
				//High Charts Building Start
				/*for(var i in result){
					
					var plannedCount=result[i].invitedCount+result[i].nonInviteeCount+result[i].attendedCount;
					
					$('.meetings'+i+'').highcharts({
						colors: ['#fec601', '#458ae9', '#f64911'],
						chart: {
							type: 'pie',
							options3d: {
								enabled: false,
								alpha: 15
							},
							backgroundColor: 'transparent'
						},
						tooltip: {
							useHTML: true,
							borderWidth: 0,
							style: {
								padding: 0
							}
						},
						legend: {
								enabled: false,
								align: 'center',
								x: 10,
								verticalAlign: 'left',
								y: 20,
								floating: false,
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
						plotOptions: {
							pie: {
								innerSize: 40,
								depth: 35,
								dataLabels: {
									enabled: false,
								}
							}, 
						},
						
						series: [{
							name: " ",
							data: [
								['Planned', plannedCount],
								['Conducted', result[i].invitedCount],
								['Not Conducted', result[i].nonInviteeCount]
							]
						}]
					});					
				}*/
				
				
					
					/*$('.totalMeetings').highcharts({
						colors: ['#fec601', '#458ae9', '#f64911'],
						chart: {
							type: 'pie',
							options3d: {
								enabled: false,
								alpha: 15
							},
							margin:'0',
							padding:'0',
							backgroundColor: 'transparent'
						},
						legend: {
								enabled: false,
								align: 'center',
								x: 10,
								verticalAlign: 'left',
								y: 20,
								floating: false,
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
						plotOptions: {
							pie: {
								innerSize: 40,
								depth: 35,
								dataLabels: {
									enabled: false,
								}
							}, 
						},
						tooltip: {
							useHTML: true,
							borderWidth: 0,
							style: {
								padding: 0
							}
						},
						series: [{
							name: " ",
							data: [
								['Planned', totalPlanned],
								['Conducted', totalConducted],
								['Not Conducted', totalNotConducted]
							]
						}]
					});*/
					
					//High Chart Building End
				
			}
		});
}
 function getMandalsForDistrictId(){
	 $("#manTowDivId  option").remove();
	var districtId = $("#districtId").val();
			var districtId = [];
			if(districtId==0){
				if(distArr.length>0){
					districtId = distArr;
				}else{
					districtId.push(0);
				}
			}else{
				districtId.push(districtId);
			}
			districtId = districtId;
			var constituencyId=[];
	 var jsObj={
			stateId:0,			
			districtId :districtId,
			mandalId:0,
			locationLevel:4,
			constituencyId:constituencyId
		}
		$.ajax({
			type:"POST",
			url :"getSubLevelLctnsForConstituencyAndMandalAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#manTowDivId").append("<option value='0'>ALL</option>");
			   for(var i in result){
				   $("#manTowDivId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
   });	
  }
function getVillagesForDistrictId(){
	$("#villWardId  option").remove();
	 var districtId = $("#districtId").val();
			var districtId = [];
			if(districtId==0){
				if(distArr.length>0){
					districtId = distArr;
				}else{
					districtId.push(0);
				}
			}else{
				districtId.push(districtId);
			}
			districtId = districtId;
			var constituencyId=[];
	 	var jsObj={
			stateId:0,			
			districtId :districtId,
			mandalId:0,
			locationLevel:5,
			constituencyId:constituencyId
		}
		$.ajax({
			type:"POST",
			url :"getSubLevelLctnsForConstituencyAndMandalAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){$("#villWardId").append("<option value='0'>ALL</option>");
				for(var i in result){
				   $("#villWardId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
   });	
 }
 var levelId = '';
		$("#loadingImgForLevelId").show();
		$(".stateCls").each(function(){
			if($(this).prop('checked')==true){
			levelId = $(this).val();	
			}
		});
 /* function handleFunctions(){
	getLevelWiseMeetingDetails(); 
		$("#loadingImgForLevelId").show();
		$(".stateCls").each(function(){
			if($(this).prop('checked')==true){
				 if($(this).val()==1){
					$("#statesDivId").html("");
					//$("#statesDivId").append('<option value=1> All </option>');
					$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
					getDistrictsForStates($( "#statesDivId" ).val());
					meetingLevelWiseHideShow();
				}else if($(this).val()==36){
					$("#statesDivId").html("");
					//$("#statesDivId").append('<option value=36> All </option>');
					$("#statesDivId").append('<option value=36> Telangana </option>');
					getDistrictsForStates($( "#statesDivId" ).val());
					meetingLevelWiseHideShow();
				}else if($(this).val()==0){
					$("#statesDivId").html("");
					$("#statesDivId").append('<option value=0> All </option>');
					$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
					$("#statesDivId").append('<option value=36> Telangana </option>');
					getDistrictsForStates($( "#statesDivId" ).val());
					meetingLevelWiseHideShow();
				}
			}
		});
 } */
$(document).on("click",".updateCls",function(){
	$("#statusDetailsDivId").html("");
	$("#statusDivId").hide();
	$("#updateFinlizMtngBtnModal").modal("show");
	var meetingId = $(this).attr("attr_meeting_Loc_Id");
	var status = $(this).attr("attr_status");
	if(status != null && status == "maybe")
		$("#statusDivId").show();
	$("#hiddenFinalizeMeetingId").val(meetingId);
	$("#hiddenFinalizeMeetingStatus").val(status);
	$("#uploadFileId0").val("");
	$(".cloneFileCls").val("");
	$(".fileUploadDivId").hide();
	
	$("#memebershipId").val("");
	$("#nameId").val("");
	$("#mobileNoId").val("");
	$("#remarkId").val("");
	$("#statusId").val(0);
	$("#uploadFileId0").val("")
	$('#errorId').html('');
});
$(document).on("click","#saveDetailsBtnId",function(){
	$('#errorId').html('');
		var errorStr = '';
	var partyMeetingId = $("#hiddenFinalizeMeetingId").val();
	var membershipId = $("#memebershipId").val();
	var name = $("#nameId").val();
	var mobileNo = $("#mobileNoId").val();
	var remark = $("#remarkId").val();
	var updateStatus = $("#statusId").val().trim();
	var upladFile = $("#uploadFileId0").val();

	if(updateStatus == "Y"){
		if(upladFile == null || upladFile.trim().length == 0)
				errorStr += "Attachment is Required.<br>";
		/*$('.cloneFileCls').each(function(){
			var uploadFile = $(this).val();
			if(uploadFile == null || uploadFile.trim().length == 0)
				errorStr += "Attachment is Required.<br>";
		});*/
	}
	
	if(name ==0){
	  	  errorStr += "Name is required<br>";
	}
	var numericExpression = /^[0-9]+$/;
	if(mobileNo.trim().length != 10 || mobileNo.trim().length > 10 ){
	  	  errorStr += "Mobile No is required<br>";
	}
	else if(!mobileNo.match(numericExpression))
		errorStr += "Enter Valid MobileNO<br>";
	if(remark ==0){
	 	  errorStr += "Remark is required<br>";
	}
	if(status != null && status == "maybe"){
			if(updateStatus ==0){
				errorStr += "Status is required<br>";
		}
	}else{
		updateStatus = 0;
	}
	
	
	if(errorStr.length >0)
	{
	  $('#errorId').html(errorStr);
	  return ;
	}
  var r=confirm("Are you sure you want to update the status.. ");
  if (r)	
  {
	  $("#searchDataImgForUpdateMeetings").show();
	  var uploadHandler = {
		upload: function(o) {
		    uploadResult = o.responseText;
			showMeetingsStatusDocsResult(uploadResult);
		}
	};

	YAHOO.util.Connect.setForm('meetingsForm',true);
	YAHOO.util.Connect.asyncRequest('POST','saveFinalizedMeetingDetailsAction.action',uploadHandler); 
	/*var jsObj =	{
		partyMeetingId:partyMeetingId,
		memberType:"",
		membershipId:membershipId,
		name:name,
		mobileNo:mobileNo,
		remark:remark,
		statusId:updateStatus,
		updatedBy :"IC"
	}
		$.ajax({
			type: "POST",
			url:"saveFinalizedMeetingDetailsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
				if(result.message == "SUCCESS"){
					$("#statusDetailsDivId").html(" Saved successfully...");
					$("#memebershipId").val("");
					$("#nameId").val("");
					$("#mobileNoId").val("");
					$("#remarkId").val("");
					$("#statusId").val("");
				}else{
					$("#statusDetailsDivId").html(" Error occured try again...");
				}
		});*/
  }
});

function showMeetingsStatusDocsResult(myResult){
	$("#updateAlertajaxImg").html('');
	$("#searchDataImgForUpdateMeetings").hide();
		var result = (String)(myResult);
		if(result.search('SUCCESS') != -1){
			$("#statusDetailsDivId").html(" Saved successfully...");
			$("#memebershipId").val("");
			$("#nameId").val("");
			$("#mobileNoId").val("");
			$("#remarkId").val("");
			$("#statusId").val(0);
			$(".cloneFileCls").val("");
			$("#uploadFileId0").val("");
			setTimeout(function(){$("#updateFinlizMtngBtnModal").modal('hide');}, 1500);
		}else{
			$("#statusDetailsDivId").html(" Error occured try again...");
		}
	}
$(document).on("click",".commentsCls",function(){
	$("#commentsModalId").modal('show');
	$("#commentsDivId").html("");
	var partymeetingId  = $(this).attr("attr_meeting_Loc_Id");
	var jsObj = {
		partyMeetingId :partymeetingId
	}
		$.ajax({
			type: "POST",
			url:"getCommentsMeetingDetailsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
				buildCommentsMeetingDetailsAction1(result);
		});
});
function buildCommentsMeetingDetailsAction1(result)
{
	var str='';
	if(result !=null && result.length>0){
	str+='<ul class="apptStatusTracking">';
	for(var i in result){
	if(result[i].memberShipNo != null && result[i].memberShipNo !=""){
	str+='<li id="commentli'+i+'">';
	str+='<div class="arrow_box_left">';
	str+='<span class="m_0"><strong>CREATED BY:</strong> '+result[i].name+'  <strong>MOBILE NO:</strong>'+result[i].mobileNo+' MEMBERSHIP NO:<strong style="color:green">'+result[i].memberShipNo+'</strong> <strong> TIME:</strong> '+result[i].insertedtime+' </span>';
	str+='<div class="m_0" id="remarksId'+i+'">'+result[i].remarks+'</div>';
	str+='</div>';
	str+='</li>';
	}else{
		str+='<li id="commentli'+i+'">';
	str+='<div class="arrow_box_left">';
	str+='<span class="m_0"><strong>CREATED BY:</strong> '+result[i].name+'  <strong>MOBILE NO:</strong>'+result[i].mobileNo+' <strong> TIME:</strong> '+result[i].insertedtime+' </span>';
	str+='<div class="m_0" id="remarksId'+i+'">'+result[i].remarks+'</div>';
	str+='</div>';
	str+='</li>';
	}
	}
	str+='</ul>';
	$("#commentsDivId").html(str);
	}else{
	   $("#commentsDivId").html(" No comments available...");
	}
}
 function meetingLevelWiseHideShow(){
	 $("#statusShowId").show();
	 if($("#meetingLocationLevel").val()== 2 ){
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").hide();
				$("#ManTwnDivShowId").hide();
				$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 3){
				
				getConstituenciesForDistricts("");
				
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").hide();
				//$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 4 || $("#meetingLocationLevel").val()== 5 || $("#meetingLocationLevel").val()== 6){
				getConstituenciesForDistricts("");
					
				/* <c:if test="${sessionScope.USER.isAdmin == 'true'}">
				setTimeout(function(){getMandalVillageDetails(4);}, 2000);
				</c:if> */ 
				
				<c:if test="${sessionScope.USER.isAdmin == 'false'}">
				setTimeout(function(){getMandalsForDistrictId();}, 2000);
				</c:if>
				
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").show();
				$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 7 || $("#meetingLocationLevel").val()== 8){
				
				getConstituenciesForDistricts("");
				/* start */
				
				 <!-- <c:if test="${sessionScope.USER.isAdmin == 'true'}">
				getMandalVillageDetails(4);
				</c:if>  -->
				/* end */
				
				<!--<c:if test="${sessionScope.USER.isAdmin == 'false'}">
				getMandalsForDistrictId();
				getVillagesForDistrictId();
				</c:if>-->
				
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").show();
				<!--$("#VillWardShowId").show();-->
				
			}
			else if($("#meetingLocationLevel").val()== 1){
				
				$("#stateShowId").show();
				$("#DistrictShowId").hide();
				$("#ConstShowId").hide();
				$("#ManTwnDivShowId").hide();
				$("#VillWardShowId").hide();
			}
		
}
</script>
<script>
$(document).on("change","#statusId",function(){
	$(".fileUploadDivId").hide();
	var value = $(this).val();
	if(value == "Y"){
		$(".fileUploadDivId").show();
	}
});

var fileNo=0;
$(document).on("click","#addFile",function(){
	fileNo = fileNo+1;
	$("#extraUploadFileDiv").append('<li id="cloned'+fileNo+'"><input type="file" id="uploadFileId'+fileNo+'" name="imageForDisplay" class="btn btn-mini cloneFileCls"/><span class="closeIcon" style="cursor:pointer;" attr_id="'+fileNo+'">x</span></li>');
});
$(document).on("click",".clearFileCls",function(){
	$("#uploadFileId0").val("");
	$(".clearFileCls").hide();  		
});
$(document).on("click",".closeIcon",function(){  
	var positionId = $(this).attr("attr_id");
	$("#cloned"+positionId).remove();
});
$(document).on("click","#uploadFileId0",function(){
	$(".clearFileCls").show();
});
function refreshMeetingStatus(){
	getinsertDataInToPartyMeetingStatus();
}
function getinsertDataInToPartyMeetingStatus(){
	  $("#partyMettingStatusIdImg").show();
		$.ajax({
			type : 'POST',
			url : 'insertDataInToPartyMeetingStatusTableAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#partyMettingStatusIdImg").hide();
			if(result != null && result.message == "success" ){
				$("#partyMettingStatusId").html("updated  partyMettings status")
			}else{
				$("#partyMettingStatusId").html("Exception Occurred Please Try again")
			}
			
		});	
	}
	
$(document).on("click",".updatedCountCls",function(){
	$("#conflictsModalId").modal('show');
		$("#commentsBlock").html('');
		
	    var dates=$("#datePickerBlockId").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates != undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		
		var levelId = $("#meetingLocationLevel").val();
		var status = $("#statusSelId").val();
		
		var jsObj ={ 
		             levelId : levelId,
					 startDate : fromDateStr,
					 endDate : toDateStr,
					 status : status
				  }
		$.ajax({
			type : 'POST',
			url : 'getUpdationDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0)
				buildCommentsMeetingDetailsAction(result);
			else
				$("#commentsBlock").html("No Data Available.");
		});
	
});

function buildCommentsMeetingDetailsAction(result)
{
	
	var str='';
	str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result){
		
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="headingOne'+i+'">';
			str+='<div class="row">';
				str+='<div class="col-md-8 col-sm-12 col-xs-8">';
					str+='<h4 class="panel-title">';
					str+='<a role="button" class="meetingsConflictsCls" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne" attr_div_id="meetingDetailsDivId'+i+'" attr_meeting_id="'+result[i].partyMeetingId+'">'+result[i].partyMeetingName+' </a></h4>';
				str+='</div>';
				str+='<div class="col-md-4 col-sm-12 col-xs-4">';
					str+='<span class="pull-right"><small> Created By : </small>'+result[i].name+'</span>';
				str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
			  str+='<div class="panel-body">';
				str+='<div id="meetingDetailsDivId'+i+'"></div>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#commentsBlock").html(str);

	
}

$(document).on("click",".meetingsConflictsCls",function(){
	var meetingId = $(this).attr("attr_meeting_id");
	var divId = $(this).attr("attr_div_id");
	$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
	//$('.documentSlickApply').slick('unslick');
	var jsObj ={ 
		partyMeetingId : meetingId
	}
	$.ajax({
		type : 'POST',
		url : 'getDocumentListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildDocumentListDetails(result,divId);
	});
});

function buildDocumentListDetails(result,divId){
	var str='';
	if(result !=null && result.partyMeetingVOList !=null && result.partyMeetingVOList.length>0){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		
			for(var i in result.partyMeetingVOList){
				str+='<div class="arrow_box_left">';
			if(result.partyMeetingVOList[i].meetingLevel != null && result.partyMeetingVOList[i].meetingLevel.length > 0){
				str+='<span class="m_0"><strong>UPDATED BY:</strong> '+result.partyMeetingVOList[i].subName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <strong>MOBILE NO:</strong>'+result.partyMeetingVOList[i].mobileNo+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MEMBERSHIP NO:<strong style="color:green">'+result.partyMeetingVOList[i].meetingLevel+'</strong></br> <strong>Remarks:&nbsp;</strong> '+result.partyMeetingVOList[i].memberStatus+'<span class="pull-right"><strong> TIME:</strong> '+result.partyMeetingVOList[i].insertedTime+'</span></span>';
			}else{
				str+='<span class="m_0"><strong>UPDATED BY:</strong> '+result.partyMeetingVOList[i].subName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <strong>MOBILE NO:</strong>'+result.partyMeetingVOList[i].mobileNo+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br> <strong>Remarks:&nbsp;</strong> '+result.partyMeetingVOList[i].memberStatus+'<span class="pull-right"><strong> TIME:</strong> '+result.partyMeetingVOList[i].insertedTime+'</span></span>';
			}
				str+='</div>';
				str+='<ul class="list-inline documentSlickApply1">';
				if(result.partyMeetingVOList[i].docmentsList !=null && result.partyMeetingVOList[i].docmentsList.length>0){
					for(var j in result.partyMeetingVOList[i].docmentsList){
						
						str+='<li><img class="image-responsive thumbnail" src="https://www.mytdp.com/'+result.partyMeetingVOList[i].docmentsList[j]+'" style="width:800px;height:400px;"></li>';
												
					}
				}
				str+='</ul>';
			}
		str+='</div>';
		$("#"+divId).html(str);
		$(".documentSlickApply1").slick({
				 slide: 'li',
				 slidesToShow: 1,
				 slidesToScroll: 1,
				 infinite: false,
				 variableWidth: true
			});
	}
	
}

</script>
</body>
</html>