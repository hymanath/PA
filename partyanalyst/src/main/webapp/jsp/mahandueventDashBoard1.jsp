<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">

<title>Event</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
<link href="dist/eventDashboard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/custom1.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">


<style>
.tableC thead th {color:#00B17D !important;font-size: 11px !important;}
.tableC tr td{font-size:12px !important;padding:4px !important;}
.tableC tr:nth-child(odd) td
{
	background:#F1F6F9 !important
}
.tableC tr:nth-child(even) td
{
	background:#EFF3F4 !important
}
.errorDiv{}
 #slider label {

 width: 20px;
 margin-top: 20px;
 margin-left: -10px;
 text-align: center;
 font-size:8px;
}
.label-custom a {
    color: #fff !important;
    text-decoration: none;
}
.otherstate li {
    border: 1px solid #ccc;
    margin: 2px;
    padding: 5px;
	width:216px;
}

    header.eventsheader {  
    background:url("dist/eventDashboard/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}

#processingDialogue{heigth:100% !important;}
.prev, .next {width:45px !important}
.customclass{left:50% !important;margin-left:-70px;border:none !important;background:none !important}
.customclass1{border:none !important;background:none !important}
#mainheading{font-size:21px !important}
.refreshButton
{
	background:#5cb85c;
	border:1px solid #333;
	color:#fff; 
	border-radius: 5px; 
	padding:2px 6px;
	width: 180px;
	float:right;
	text-align:center
}
.refreshButton .text
{
	font-size: 11px; 
	line-height: 11px; 
	border-right: 1px solid rgb(51, 51, 51); 
	display: inline-block;
}
.refreshButton .refreshIcon
{
	padding:2px 8px;
	color:#fff;
	position:relative;
	top:-2px;
	font-size:15px;
}
#mahanaduLinkId , #mahanaduLinkId:hover , #mahanaduLinkId:active , #mahanaduLinkId:focus
{
	margin-left:5px;
	padding:4px 12px;
	background:#00B17D;
	border:1px solid #00B17D;
}
.errorColorAppy{ color: rgb(255, 0, 0);font-size: 12px;font-weight: bold;margin-left: -70px;text-shadow: none}
.text-capitalize{text-transform: uppercase;}
.pointer {
    cursor: pointer;
}
.col-md-offset-6-manually{
	margin-left: 53%;
}
</style>
</head>

<body>
 <div class="themeControll">
  <div class="linkinner"> 
      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" ></div>
  </div>
  <p class="tbtn"> <i class="glyphicon glyphicon-filter"></i> FILTERS</p>
</div>   
<section>
	<div class="container">	
	
	<div class="well well-sm" style="background:#999">
		<div class="row">
			<div class="col-md-3">
				 <div id="reportrange" class="pull-right calendar-style">
				  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
				  <span>April 1, 2015 - April 30, 2015</span> <b class="caret"></b>
			   </div>
			</div>
			<div class="col-md-9">
				<div id="mahanaduEventDashBoardLinkId" style="display:none">
					<button id="mahanaduLinkId" type="button" class="btn btn-primary pull-right">ENTRY/EXIT DASHBOARD</button>
				</div>
				<div class="refreshButton">
					<span  class="text">Last Updated Time <br/><span id="timeUpdationId"></span>&nbsp;&nbsp;&nbsp;</span>
					<a onclick="callingDefaultCalls();" title=" Page Refresh" class="refreshIcon" style="cursor:pointer">
						<span class="glyphicon glyphicon-refresh"></span>
					</a>
				</div>
				 
				<!--<a  class="btn btn-xs btn-success btn-block dataSynchClass" title=" Data Synch..">
					<span class="" style="font-size: 15px;"> Sync
						<img src="images/ajaxImg2.gif" id="syncAjaxImage1" style="height:20px;width:20px;display:none;"/>
					</span>
				 </a>-->
			</div>
		</div>
	</div>
		

	<div class="row">
    	<div class="col-md-4 col-sm-6 col-xs-12 ">
        	<div class="panel panel-default panel-custom-green firstBlock" style="background:#00b07d">
              <div class="panel-heading">overall events status </div>
              <div id="overAllEventDiv">    
			  

              </div>
			
            </div>
        </div>
		
        <div class="col-md-8 col-xs-12 col-sm-6 ">
			<div class="firstBlock">
				<div id="rangeSliderDiv" style="display:none;">
					<div id="slider"></div>
					<p>
						<input id="amount" type="text" style="border: none; font-weight: bold;background-color:transparent;" readonly>
					</p>
				</div>
				<div id="hourWiseerrorDiv" style="width: 100%; height:382px; margin: 0px auto -66px;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;text-align:center;margin-top:10px;padding-top:165px;"></div>
				<div id="hourWiseContainer" style="width: 100%; height:382px; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;margin-top:0px;display:none;"></div>
				<div id="dayWiseContainer" style="width: 100%; height:382px; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;margin-top:10px;display:none;"></div>
			</div>
        </div>

    </div>
	<div class="row">
		<div class="col-md-5 col-xs-12 col-sm-6">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">total event visits</div>
				<div class="panel-body">
					<center><img id="donutChartAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
					<div id="donutchart" style="width: 100%; height: 100%; margin: 0 auto;border-radius:5px"></div>
				</div>
			</div>
		</div>
		<div class="col-md-7 col-xs-12 col-sm-6">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">event wise repeated members count</div>
				<div class="panel-body">
					<center><img id="columnChartAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
					<div id="columnchart" style="width: 100%; height: 100%; margin: 0 auto;border-radius:5px;"></div>
				</div>
			</div>
		</div>
	</div>
	<!--start-->
	<div class="row" id="stateDivsId" style="display:none;">
      <div class="col-md-3">
          <div class="panel panel-default panel-custom-green">
              <div class="panel-heading">
                  <h4 class="panel-title">overall<span class=" text-italic f_11 colorBlack">[ap,ts,other states]</span></h4>
                </div>
				<div class="panel-body   panelBlocksHeightSet">
				<center><img id="allStatesAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
					<div id="allStateDetails"></div>
				</div>
            </div>
        </div>
        <div class="col-md-3">
          <div class="panel panel-default panel-custom-red ">
              <div class="panel-heading">
                  <h4 class="panel-title">andhra pradesh</h4>
                </div>
                <div class="panel-body   panelBlocksHeightSet">
					<center><img id="apStateAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
					<div id="apStateDetails"></div>
                 
                </div>
            </div>
        </div>
	  <div class="col-md-3">
		  <div class="panel panel-default panel-custom-coffee ">
			  <div class="panel-heading ">
				  <h4 class="panel-title">telangana</h4>
				</div>
				<div class="panel-body   panelBlocksHeightSet">
					<center><img id="tsStateAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
						<div id="tsStateDetails"></div>
				</div>
			</div>
		</div>
	 <div class="col-md-3">
		  <div class="panel panel-default panel-custom-other ">
			  <div class="panel-heading">
				  <h4 class="panel-title">other states</h4>
				</div>
				<div class="panel-body   panelBlocksHeightSet" >
					<center><img id="otherStateAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
						<div id="otherStateDetails"></div>
				</div>
			</div>
		</div>
    </div>
	<div>
	<div class="row">
	<div class="col-md-12">
	  <div class="panel panel-default panel-custom-default m_0">
		  <div class="panel-heading" style="padding: 10px;">
			  <h4 class="panel-title m_0">PUBLIC REPRESENTATIVE ATTENDEE COUNT</h4>
			</div>
			<div class="panel-body" style="background-color:#f2f2f2">
				<center><img id="publicRepresentativeAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
				<div id="publicRepresentativeDiv"></div>
			</div>
		</div>
	</div>
	
	</div>
	</div>
	<!-- END-->
		<div class="row m_top10">
			<div class="col-md-12">
				<div class="panel panel-default panel-custom-default m_0">
					<div class="panel-heading">
						<div class="row">
						
							<!--<div class="col-md-3">
								<h4>EVENTS STATE WISE</h4>
							</div>
							<div class="col-md-4 m_top10">
								<div class="onoffswitch pull-left">
									<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
									<label class="onoffswitch-label" for="myonoffswitch">
										<span class="onoffswitch-inner"></span>
										<span class="onoffswitch-switch"></span>
									</label>
								</div>
								<div class="onoffswitch pull-left">
									<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
									<label class="onoffswitch-label" for="myonoffswitch">
										<span class="onoffswitch-inner"></span>
										<span class="onoffswitch-switch"></span>
									</label>
								</div>
								<div class="onoffswitch pull-left">
									<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
									<label class="onoffswitch-label" for="myonoffswitch">
										<span class="onoffswitch-inner"></span>
										<span class="onoffswitch-switch"></span>
									</label>
								</div>
							</div>
							<div class="col-md-2">
								<div class="refreshButton">
									<span  class="text">Last Updated Time <br/><span id="timeUpdationId"></span>&nbsp;&nbsp;&nbsp;</span>
									<a onclick="callingDefaultCalls();" title=" Page Refresh" class="refreshIcon" style="cursor:pointer">
										<span class="glyphicon glyphicon-refresh"></span>
									</a>
								</div>
							</div>
							<div class="col-md-3 col-md-offset-2 m_top6">
								<select id="distEventId" style="margin-top:-5px;" class="eventCls form-control" onChange="getLocationWiseCountBySubEvents(3);getLocationWiseCountBySubEvents(4);">
									<option value="0"> All Events</option>
								</select>
							</div>-->
							
						</div>
						<div class="row">
							<div class="col-md-2" style="width: 216px;">
								<h4 style="font-weight: bold;">EVENTS STATE WISE</h4>
							</div>
						
							<div class="col-md-1 " style="margin-top: 6px;">
								<!--<div class="col-md-1 " style="margin-top: 6px;">
									<div class="onoffswitch pull-right">
										<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
										<label class="onoffswitch-label" for="myonoffswitch">
											<span class="onoffswitch-inner"></span>
											<span class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>-->
								<div class="apSwitch">
									<input type="checkbox" name="apSwitch" class="apSwitch-checkbox checkedSwitch" value="1" id="apSwitch" checked >
									<label class="apSwitch-label" for="apSwitch">
										<span class="apSwitch-inner"></span>
										<span class="apSwitch-switch"></span>
									</label>
								</div>
							</div>
							<div class="col-md-1 " style="margin-top: 6px;">
								<div class="tsSwitch">
									<input type="checkbox" name="tsSwitch" class="tsSwitch-checkbox checkedSwitch" value="36" id="tsSwitch" >
									<label class="tsSwitch-label" for="tsSwitch">
										<span class="tsSwitch-inner"></span>
										<span class="tsSwitch-switch"></span>
									</label>
								</div>
							</div>
							<div class="col-md-1 " style="margin-top: 6px;">
								<div class="otherStates">
									<input type="checkbox" name="otherStates" class="otherStates-checkbox checkedSwitch" value="0" id="otherStates" >
									<label class="otherStates-label" for="otherStates">
										<span class="otherStates-inner"></span>
										<span class="otherStates-switch"></span>
									</label>
									<span class="errorColorAppy" id="showErrorMsg"></span>
								</div>

							</div>
							
							<div class="col-md-3 pull-right" style="padding: 5px;">
								<select id="distEventId" style="margin-top:-5px;" class="eventCls form-control" onChange="getLocationWiseCountBySubEvents(3);getLocationWiseCountBySubEvents(4);">
									<option value="0"> All Events</option>
								</select>
							</div>
							
							<div class="refreshButton">
								<span  class="text" style="border-right:none;text-shadow:none;">Last Updated Time <br/><span id="lastUpdatedTimeId"></span>&nbsp;&nbsp;&nbsp;</span>
							</div>
							<!--<div class="col-xs-4">
							  <span style="font-size: 12px;">Last Updated Time: &nbsp;&nbsp;<span id="lastUpdatedTimeId" style="padding: 5px; font-size: 11px;" class="label label-primary"></span></span>
							</div> -->
							
							<!--<div id="updateDataDivId" style="margin-top: 5px; margin-left: -83px;display:none;" class="col-xs-1 ">
								<a style="" title="Click Here To Get Updated Data" class="btn btn-xs btn-success dataSynchClass" >
									<span style="font-size: 13px; margin-top: 0px;" class="">Update Data
										<img style="height:20px;width:20px;display:none;" id="syncAjaxImage" src="images/ajaxImg2.gif">
									</span>
								</a>
							</div>-->
							
						</div>
						
					</div>
					<div class="panel-body" style="background-color:#f2f2f2">
						
						<div class="row m_top6">
							<div class="col-md-12">
								<div class="panel panel-default m_0">
									<div class="panel-heading">
									<p class="m_0 display-style" id="districtHeading">DISTRICT WISE </p>
									<button class="btn btn-success btn btn-xs col-md-offset-7" id="districtExcelBtnId" onclick="generateExcel()" style="display:none;">Export Excel</button>
									
									</div>
									<div class="panel-body" style="padding:0px;">				
											 
											<center><img id="distAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
										<div id="districtTableId"> </div>
									</div>
								</div>
								
							</div>
							<div class="col-md-12 m_top20">
								<div class="panel panel-default m_0">
									<div class="panel-heading">
									<p class="m_0 display-style" id="constiHeading">CONSTITUENCY WISE</p>
									  <button class="btn btn-success btn btn-xs col-md-offset-6-manually" id="constituecnyExcelBtnId" onclick="generateExcel1()" style="display:none;">Export Excel</button>
									<div class="onoffswitch pull-right" style="display:none;">
										<input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" disabled id="myonoffswitch1" checked>
										<label class="onoffswitch-label" for="myonoffswitch1">
											<span class="onoffswitch-inner"></span>
											<span class="onoffswitch-switch"></span>
										</label>
									</div>
									</div>
									<div class="panel-body" style="padding:0px;">
									<!--<select id="constiEventId" class="eventCls form-control" style="margin-top:-5px;" onChange="getLocationWiseCountBySubEvents(4)"><option value="0"> All Events</option></select>-->
									<center><img id="constAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
										<div id="constiTableId" ></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
        
    <!--<div class="row">
    	<div class="col-md-12">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="constiHeading">AP CONSTITUENCY WISE</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" id="myonoffswitch1" checked>
                    <label class="onoffswitch-label" for="myonoffswitch1">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body">
                   <div id="constiTableId"></div>
                </div>
            </div>
        </div>
    </div>-->
	
	<div id="eventMembersDialog" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h4 class="panel-header text-center"></h4>
					  </div>
					  <div id="stateWiseOverView"></div>
						<div id="eventMembersDiv" style="margin-top:15px;padding:15px;margin-bottom:10px;"></div>
						<div id="paginationDivId"  style="margin-top:0px;margin-left:20px;"></div>
				</div>
			</div>
    </div>
	<div id="processingDialogue"></div>
	</div>
</section>

<div class="modal fade" id="syncModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lm" role="document">
    <div class="modal-content">
      <div class="modal-body">
        Sync in Processing Please Wait...
      </div>
    </div>
  </div>
</div>
<div class="modal fade openmodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="popupId">
  <div class="modal-dialog modal-lg modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><b>Candidate Details</b></h4>
      </div>
     <div class="modal-body pad_0">
	 <div id="showModelConstcy"></div>
	  </div>
	  </div>
	  </div>
	  </div>
	  
<script  src="js/eventDashboard.js" type="text/javascript"></script>
<script src="dist/eventDashboard/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/eventDashboard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/eventDashboard/js/highcharts.js" type="text/javascript"></script>
<script src="dist/eventDashboard/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="dist/eventDashboard/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/eventDashboard/js/dataTables.responsive.js" type="text/javascript"></script>
 <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> 
  <script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
  <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<script type="text/javascript">


var parentEventId = '${eventId}';

var subEvents = [];
var startDate;
var endDate;
var dataRetrievingType = "intermediate"; // intermediate dynamic


function getEvents()
{
var jObj = {
eventId : parentEventId,
task:"getEvents"
}
$.ajax({
          type:'GET',
          url: 'getEventsForUserAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{				
				buildFilterEvents(result);	
			}
	});

}
function buildFilterEvents(result)
{

var str ='';
for(var i in result)
{
str+='<div class="panel panel-default border_0">';

str+='<div class="panel-heading collapse-head" role="tab" id="heading'+result[i].id+'">';
str+='<h5 class="panel-title">';
str+='<form class="me-select display-style">';
str+='<ul id="me-select-list" style="list-style:none;">';
if(parentEventId == result[i].id)
{
str+='<li><input id="mainEvent'+result[i].id+'" name="cb11" attr_formatedStartDate="'+result[i].formateEventStartDate+'" attr_formatedEndDate="'+result[i].formateEventEndDate+'" attr_startDate="'+result[i].eventStartDate+'" attr_endDate="'+result[i].eventEndDate+'" type="checkbox" onclick="handalClick('+result[i].id+')" class="maineventCls" value="'+result[i].id+'" checked>';
}
else
{
str+='<li><input id="mainEvent'+result[i].id+'" name="cb11" attr_formatedStartDate="'+result[i].formateEventStartDate+'" attr_formatedEndDate="'+result[i].formateEventEndDate+'" attr_startDate="'+result[i].eventStartDate+'" attr_endDate="'+result[i].eventEndDate+'"  type="checkbox" onclick="handalClick('+result[i].id+')" class="maineventCls" value="'+result[i].id+'">';
}
str+='<label for="cb11" class="m_0 collapse-select"><span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-controls="collapse'+result[i].id+'" class="col-drop-head " id="eventText'+result[i].id+'">'+result[i].name+'</a></span></label></li>';
str+=' </ul>';
str+='</form>';
str+=' <a data-toggle="collapse" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-expanded="true" aria-controls="collapse'+result[i].id+'">';
str+='<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>';
str+=' </a>';
str+=' </h5>';
str+=' </div>';
if(parentEventId == result[i].id)
str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
else
str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
str+='<div class="panel-body collapse-body">';
str+='<form class="me-select display-style">';
str+='<ul id="me-select-list" style="list-style:none;">';

 for(var j in result[i].subList)
 {
if(parentEventId == result[i].id)
{
str+='<li><input id="check'+result[i].subList[j].id+'" attr_label="'+result[i].subList[j].name+'" name="cb11" onclick="checkParent(\''+result[i].id+'\',\''+result[i].subList[j].id+'\')" type="checkbox" class="subeventCls subeventCls'+result[i].id+'" value="'+result[i].subList[j].id+'" checked>';
str+=' <label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+result[i].subList[j].name+'</span></label></li>';
}
else
{
str+='<li><input id="check'+result[i].subList[j].id+'" attr_label="'+result[i].subList[j].name+'" name="cb11" onclick="checkParent(\''+result[i].id+'\',\''+result[i].subList[j].id+'\')" type="checkbox" class="subeventCls subeventCls'+result[i].id+'" value="'+result[i].subList[j].id+'" ">';
str+=' <label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+result[i].subList[j].name+'</span></label></li>';
}
}
str+=' </ul>';
str+=' </form>';
str+=' </div>';
str+=' </div>';
str+=' </div>';
}
str+='<span id="errormsg" class="errorDiv"></span>';
str+='<button class="btn btn-block btn-default btn-custom"   onclick="eventUpdate()">UPDATE</button>';
$("#accordion").html(str);
eventUpdate();
}

var dpCurentDate;
var formatedDpCurentDate;
 $(document).ready(function() {

                  var cb = function(start, end, label) {
                    //alert(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('D MMMM,YYYY') + ' - ' + end.format('D MMMM,YYYY'));
					
                    //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
                  }

                  var optionSet1 = {
                    startDate: moment(),
                    endDate: moment(),
                    minDate: '01/01/2012',
                   // maxDate: '31/12/2025',
                    showDropdowns: true,
                    showWeekNumbers: true,
                    timePicker: false,
                    timePickerIncrement: 1,
                    timePicker12Hour: true,
                    opens: 'right',
                    buttonClasses: ['btn btn-default btn-custom'],
                    applyClass: 'btn-small btn-primary',
                    cancelClass: 'btn-small',
                    format: 'DD/MM/YYYY',
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
                    startDate: moment(),
                    endDate: moment(),
                    opens: 'right',
                    ranges: {
                       'Today': [moment(), moment()],
                       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                       'This Month': [moment().startOf('month'), moment().endOf('month')],
                       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    }
                  };

                  $('#reportrange span').html(moment().format('D MMMM,YYYY') + ' - ' + moment().format('D MMMM,YYYY'));

                  $('#reportrange').daterangepicker(optionSet1, cb);

                  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
                  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
                  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
                    console.log("apply event fired, start/end dates are " 
                      + picker.startDate.format('D MMMM, YYYY') 
                      + " to " 
                      + picker.endDate.format('D MMMM, YYYY')
                    ); 
                  });
                  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

                  $('#options1').click(function() {
                    $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
                  });

                  $('#optip-ons2').click(function() {
                    $('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
                  });

                  $('#destroy').click(function() {
                    $('#reportrange').data('daterangepicker').remove();
                  });
				startDate = $(".dp_startDate").val();
				endDate = $(".dp_endDate").val();
				dpCurentDate = moment().format('D MMMM,YYYY');
				formatedDpCurentDate = moment().format('DD/MM/YYYY');
				 $('[data-toggle="tooltip"]').tooltip()
				
				if(parentEventId == 7 || parentEventId == 30){
					$("#stateDivsId").show();
				}else{
					$("#stateDivsId").hide();
				}
				 
			   });

	function handalClick(eventId)
	{
				
				$(".maineventCls").prop('checked', false);
				$(".subeventCls").prop('checked', false);
				$("#mainEvent"+eventId).prop('checked', true);
				$(".subeventCls"+eventId).prop('checked', true);
				
	}
				
/*var myVar=setInterval(function(){myTimer()},1000);
function myTimer() {
    var d = new Date();
    document.getElementById("time").innerHTML = d.toLocaleTimeString();
}*/

 $(document).on('click','.applyBtn',function(){
	eventUpdate('dateChange');
 });
function checkParent(eventId,subEventId)
{

$(".maineventCls").prop('checked', false);
$("#mainEvent"+eventId).prop('checked', true);

$(".maineventCls").each(function(){
	$(".subeventCls").each(function(){
			if($(this).hasClass("subeventCls"+eventId))
			{
					if($("#check"+subEventId).is(":checked"))
					$("#check"+subEventId).prop('checked', true);
			}
			else{
			$(this).prop('checked', false);
			}
		})
	});
}

	function stateWiseEventAttendeeCounts(){
		$(".panelBlocksHeightSet").removeAttr("style");
		 clearAllStateDetails();
		$("#allStatesAjax").show();
		$("#apStateAjax").show();
		$("#tsStateAjax").show();
		$("#otherStateAjax").show();
			var jsObj = {
				startDate    :startDate,
				endDate      :endDate,
				parentEventId:parentEventId,
				subEventIds : subEvents
			}
			$.ajax({
			type : 'GET',
			url : 'stateWiseEventAttendeeCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){ 
			$("#allStatesAjax").hide();
			$("#apStateAjax").hide();
			$("#tsStateAjax").hide();
			$("#otherStateAjax").hide();
				
				buildAllStateDetails(result);
				buildStateWiseBlocks(result.apStateVO,"apStateDetails");
                buildStateWiseBlocks(result.tsStateVO,"tsStateDetails");
                buildStateWiseBlocks(result.otherStatesVO,"otherStateDetails");
				
				panelBlocksHeightSet();
			});   
			
	}

	function buildAllStateDetails(result){
		var str='';
		if(result.allStatesVO !=null && result.allStatesVO.attendees !=null && result.allStatesVO.attendees > 0){
			if(result.allStatesVO.attendees ==0 || result.allStatesVO.attendees == null){
				 str+='<h3 class="m_0 text-center"> - </h3>';
			}else{
				 str+='<h3 class="m_0 text-center">'+result.allStatesVO.attendees+'</h3>';
			}
			 
			  str+='<h4 class="m_0 text-center">TOTAL UNIQUE VISITORS</h4>';
				str+='<table class="table table-condensed tableStatus m_top16">';
				  str+='<tr>';
					  str+='<td colspan="4" class="f_14 colorBlack">DAYWISE</td>';
					str+='</tr>';
				 
				  for( var i = 0; i < result.allStatesVO.subList.length; i++){
					  if(result.allStatesVO.subList[i].dataExist){
						  var dayCount = '0'+(i+1);
						   str+='<tr>';
						  str+='<td><span class="dayCount">'+dayCount+'</span></td>';
						  if(result.allStatesVO.subList[i].attendees == 0 || result.allStatesVO.subList[i].attendees == null ){
								str+='<td class="f_12">TOTAL<br/> - </td>';
							}else{
								str+='<td class="f_12">TOTAL<br/>'+result.allStatesVO.subList[i].attendees+'</td>';
							}
							if(result.allStatesVO.subList[i].invitees == 0 || result.allStatesVO.subList[i].invitees == null ){
								str+='<td class="f_12">Invitees<br/> - </td>';
							}else{
								str+='<td class="f_12">Invitees<br/>'+result.allStatesVO.subList[i].invitees+'</td>';
							}
							if(result.allStatesVO.subList[i].nonInvitees == 0 || result.allStatesVO.subList[i].nonInvitees == null ){
								str+='<td class="f_12">Non-Invitees<br/> - </td>';
							}else{
								str+='<td class="f_12">Non-Invitees<br/>'+result.allStatesVO.subList[i].nonInvitees+'</td>';
							}
						  str+='</tr>';
					  }
				  }
				
				str+='</table>';
				if( result.allStatesVO.calcPercantage != null && result.allStatesVO.calcPercantage.trim().length>0){
					str+='<div class="progress progressWhite">';
					  str+='<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:'+result.allStatesVO.calcPercantage+'%;">';
					  str+='</div>';
					str+='</div>';
					str+='<p class="text-center m_top10 ">'+result.allStatesVO.dateString1+' Count is <span class="f_13 " style="font-weight:bold">'+result.allStatesVO.calcPercantage+' % </span>'+result.allStatesVO.highOrlow+' than '+result.allStatesVO.dateString2+'</p>';
				}else{
					str+='<div class="progress progressWhite">';
					  str+='<div class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="" style="width:0px;">';
					  str+='</div>';
					str+='</div>';
					str+='<p class="text-center"  style="margin-top: 37px;">  </p>';
				}
		
		
		
		$("#allStateDetails").html(str)
	}
	else{
		$("#allStateDetails").html("<p style='margin-top: 30px; text-align: center;height:249px;'>NO DATA AVAILABLE</p>");
	}
}

 function buildStateWiseBlocks(stateVO,buildDivId){
	var str='';
		if(stateVO !=null && stateVO.attendees != null && stateVO.attendees > 0 ){
			str+='<table class="table table-condensed tableStatus m_0" style="border-top:0px;">';
			  str+='<tr>';
					if(stateVO.attendees == 0 || stateVO.attendees == null){
					  str+='<td style="width: 94px;"><p class="f_12 m_0">Total Unique Visitors</p><h4 class="m_0"> - </h4></td>';
					  }else{
						  str+='<td style="width: 94px;"><p class="f_12 m_0">Total Unique Visitors</p><h4 class="m_0">'+stateVO.attendees+'</h4></td>';
					  }
					  if(stateVO.invitees == 0 || stateVO.invitees == null){
						 str+='<td><p class="f_12 m_0">Invitees</p><h4 class="m_0"> - </h4></td>';
					  }else{
						str+='<td><p class="f_12 m_0">Invitees</p><h4 class="m_0">'+stateVO.invitees+'</h4></td>';
					  }
					  if(stateVO.nonInvitees == 0 || stateVO.nonInvitees == null){
						str+='<td><p class="f_12 m_0">Non Invitees</p><h4 class="m_0"> - </h4></td>';
					  }else{
						str+='<td><p class="f_12 m_0">Non Invitees</p><h4 class="m_0">'+stateVO.nonInvitees+'</h4></td>';
					  }
				str+='</tr>';
			str+='</table>';
			str+='<table class="table table-condensed tableStatus ">';
			  str+='<tr>';
				  str+='<td colspan="4" class="f_14 colorBlack">DAYWISE</td>';
				str+='</tr>';
				 for( var i = 0; i < stateVO.subList.length; i++){
					 if(stateVO.subList[i].dataExist){
						  var dayCount = '0'+(i+1);
						  str+='<tr>';
							  str+='<td><span class="dayCount">'+dayCount+'</span></td>';
								if(stateVO.subList[i].attendees == 0 || stateVO.subList[i].attendees == null ){
									str+='<td class="f_12">TOTAL<br/> - </td>';
								}else{
									str+='<td class="f_12">TOTAL<br/>'+stateVO.subList[i].attendees+'</td>';
								}
								if(stateVO.subList[i].invitees == 0 || stateVO.subList[i].invitees == null ){
									str+='<td class="f_12">Invitees<br/> - </td>';
								}else{
									str+='<td class="f_12">Invitees<br/>'+stateVO.subList[i].invitees+'</td>';
								}
								if(stateVO.subList[i].nonInvitees == 0 || stateVO.subList[i].nonInvitees == null ){
									str+='<td class="f_12">Non-Invitees<br/> - </td>';
								}else{
									str+='<td class="f_12">Non-Invitees<br/>'+stateVO.subList[i].nonInvitees+'</td>';
								}
							str+='</tr>';
					 }
				} 
			str+='</table>';
			if( stateVO.calcPercantage != null && stateVO.calcPercantage.trim().length>0){
					str+='<div class="progress progressWhite">';
					  str+='<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: '+stateVO.calcPercantage+'%;">';
					  str+='</div>';
					str+='</div>';
					str+='<p class="text-center m_top10 ">'+stateVO.dateString1+' Count is <span class="f_13" style="font-weight:bold">'+stateVO.calcPercantage+' % </span>'+stateVO.highOrlow+' than '+stateVO.dateString2+'</p>';
				}else{
					str+='<div class="progress progressWhite">';
					  str+='<div class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="" style="width:0px;">';
					  str+='</div>';
					str+='</div>';
					str+='<p class="text-center"  style="margin-top: 37px;">  </p>';
				}
						
		
		$("#"+buildDivId).html(str);
	}
	else{
		$("#"+buildDivId).html("<p style='text-align: center;margin-top:30px;'>NO DATA AVAILABLE</p>");
	}
	
}

function clearAllStateDetails(){
	$("#allStateDetails").html('');
	$("#apStateDetails").html('');
	$("#tsStateDetails").html('');
	$("#otherStateDetails").html('');
	
}
function getLocationWiseVisitorsCount(eventId,reportLevelId)
{
	$("#constAjax").show();
	$("#constiTableId").html("");
	
	var statesSelectedArray = [];
	$( ".checkedSwitch").each(function( index ){
		if($(this).prop('checked')){
			var stateChecked=$(this).val();
			statesSelectedArray.push(stateChecked);
		}
	});
	var stateType;
	if( statesSelectedArray!=null && statesSelectedArray.length == 3){
		stateType = 'All';
	}else{
		stateType='particular';
	}
	
	var subeventsLocal=[];
	if($("#distEventId").val()==0){
		subeventsLocal = subEvents;
	}else{
		subeventsLocal.push($("#distEventId").val());
	}
	
	var jObj = {
			eventId:eventId,			
			stateIds:statesSelectedArray,
			stateType : stateType,
			reportLevelId:reportLevelId,
			subEvents : subeventsLocal,
			startDate : startDate,
			endDate : endDate,
		    dataRetrievingType : dataRetrievingType,
			parentEventId:parentEventId,
			eventType : "parentEvent"
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountAction1.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{				
				buildConstTable(result,reportLevelId)	
			}
	});
}
function getLocationWiseVisitorsCountForDistrict(eventId,reportLevelId)
{
    
	$("#distAjax").show();
	$("#districtTableId").html("");
	
	var statesSelectedArray = [];
	$( ".checkedSwitch").each(function( index ){
		if($(this).prop('checked')){
			var stateChecked=$(this).val();
			statesSelectedArray.push(stateChecked);
		}
	});
	
	var stateType;
	if( statesSelectedArray!=null && statesSelectedArray.length == 3){
		stateType = 'All';
	}else{
		stateType='particular';
	}
	
	var subEventsLocal = [];
	
	if($("#distEventId").val()==0){
		subEventsLocal = subEvents;
	}else{
		subEventsLocal.push($("#distEventId").val());
	}
	
	var jObj = {
			eventId:eventId,			
			stateIds:statesSelectedArray,
			stateType : stateType,
			reportLevelId:reportLevelId,
			subEvents : subEventsLocal,
			startDate : startDate,
			endDate : endDate,
		    dataRetrievingType : dataRetrievingType, 
            parentEventId:parentEventId,
			eventType : "parentEvent"			
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountForDistrictAction1.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{				
				buildDistrictTable(result,reportLevelId)	
			}
	});
}
function getLocationWiseCountBySubEvents(reportLevelId){
var subEvents1 = [];
var stateId =0;
var subIds =0;
subIds = $("#distEventId").val();
$("#showErrorMsg").html('');
/*if(reportLevelId ==3){
  subIds = $("#distEventId").val();
 }
 else{
  subIds = $("#constiEventId").val();
 }*/

if(reportLevelId == 3){
	$("#distAjax").show();
	$("#districtTableId").html("");
	}
	else{
	$("#constAjax").show();
	$("#constiTableId").html("");
	}

 var eventType;
 if(subIds == 0){
	subEvents1 = subEvents;
	eventType ="parentEvent";
 }else{
	subEvents1.push(subIds);
	eventType ="childEvent";
 }
   /* if($('#myonoffswitch').is(":checked"))
 {
 stateId = 1;
 }else{
 stateId = 36;
 }  */
 
 //validation
 
 var statesSelectedArray = [];
	$( ".checkedSwitch").each(function( index ){
		if($(this).prop('checked')){
			var stateChecked=$(this).val();
			statesSelectedArray.push(stateChecked);
		}
	});
	var length = statesSelectedArray.length;
	if(length == 0){
		$("#showErrorMsg").html("Please Select State")
	}
	var stateType;
	if( statesSelectedArray!=null && statesSelectedArray.length == 3){
		stateType = 'All';
	}else{
		stateType='particular';
	}
 
 
 
 
var jObj = {
			eventId:parentEventId,			
			stateIds:statesSelectedArray,
			stateType : stateType,
			reportLevelId:reportLevelId,
			subEvents : subEvents1,
			startDate : startDate,
			endDate : endDate,
		    dataRetrievingType : dataRetrievingType, 
			eventType : eventType,
			parentEventId:parentEventId
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountAction1.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(reportLevelId == 3){
			     $("#distAjax").show();
				 	buildDistrictTable(result,reportLevelId) 
			}
			else{
			   $("#constAjax").show();
			  	buildConstTable(result,reportLevelId);
			}
			
	});

}
function buildDistrictTable(result,reportLevelId){
	$("#distAjax").hide();
	
	$('#lastUpdatedTimeId').html(result[0].lastUpdatedDate);
	
	
	var str='';
	str+='<div >';
	if(result[0].locationName != "NO DATA"){
			str+='<table class="table tableC table-condensed table-bordered " style="border-bottom:none" id="datatableId" >';
			str+='<thead style="background:#EFF3F4">';
			str+='<tr>';
			str+='<th rowspan="2" style="vertical-align:middle" width="40px !important;"># ID</th>';
			str+='<th rowspan="2" style="vertical-align:middle">DISTRICT NAME</th>';
			str+='<th rowspan="2" style="vertical-align:middle">TOTAL INVITEES</th>';
			str+='<th rowspan="2" style="vertical-align:middle">TOTAL ATTENDED</th>';
			str+='<th rowspan="2" style="vertical-align:middle">INVITEES ATTENDED</th>';
			str+='<th rowspan="2" style="vertical-align:middle">NON INVITEES ATTENDED</th>';
			
			var dataExist = {};
			
			for(var i in result[0].subList){
				if(result[0].subList[i].totalDaydataExist == true){
					str+='<th class="text-center text-capitalize" colspan="3">'+result[0].subList[i].name+' ATTENDED</th>';
				}
			}
			
		str+='</tr>';
		str+='<tr>';
			for(var j in result[0].subList){
				if(result[0].subList[j].totalDaydataExist == true){
					str+='<th>Total</th>';
					str+='<th>Invitees</th>';
					str+='<th>Non Invitees</th>';
				}
			}
		str+='</tr>';
		str+='</thead>';
		str+='<tbody class="scrollLength">';
		for(var j in result){
			str+='<tr>';
			if(result[j].id ==0 || result[j].id == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center" >'+result[j].id+'</td>';
			}
			if(result[j].name == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td >'+result[j].name+'</td>';
			}
			if(result[j].inviteesCalled == 0 || result[j].inviteesCalled == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].inviteesCalled+'</td>';
			}
			if(result[j].attendees == 0 || result[j].attendees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].attendees+' <span>('+result[j].attendeePercantage+'%)</span></td>';
			}
			
			if(result[j].invitees == 0 || result[j].invitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].invitees+' <span>('+result[j].inviteePercantage+'%)</span></td>';
			}
			if(result[j].nonInvitees == 0 || result[j].nonInvitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].nonInvitees+' <span>('+result[j].nonInviteePercantage+'%)</span></td>';
			}
			
			for(var l in result[j].subList){
				
				if(result[0].subList[l].totalDaydataExist == true){
					
					if(result[j].subList[l].attendees ==0 || result[j].subList[l].attendees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						str+='<td class="text-center">'+result[j].subList[l].attendees+'</td>';
					}
					if(result[j].subList[l].invitees ==0 || result[j].subList[l].invitees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						 str+='<td class="text-center">'+result[j].subList[l].invitees+'</td>';
					}
				   if(result[j].subList[l].nonInvitees ==0 || result[j].subList[l].nonInvitees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						str+='<td class="text-center">'+result[j].subList[l].nonInvitees+'</td>';
					}
				}
			}
			str+='</tr>';
		}

		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		$("#districtTableId").html(str);
		$("#districtExcelBtnId").show();
	}else{
		$("#districtTableId").html("<p style='margin-top: 30px; text-align: center;'>NO DATA AVAILABLE</p>");
	}
	$('#datatableId').DataTable({
        "paging":   false,
        "info":     false,
		"searching": true,
		"autoWidth": true,
		"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"ordering": false
    });
	$("#datatableId_filter").css("margin-top","-29px");
	
	var Scrollerlength = $(".scrollLength").find("tr").length;
	
	if(Scrollerlength >=11){
		$("#datatableId").css("display","block");
		$("#datatableId").css("height","350px");
		$("#datatableId").css("overflow-y","scroll");
		$("#datatableId").css("overflow-x","scroll");
	}else{
		
		$("#datatableId").css("height","auto");
		
	}
	/* if(reportLevelId == 3)
	{
		$("#distAjax").hide();
	}
	else
	{
		$("#constAjax").hide();
	}
	
	$('#lastUpdatedTimeId').html(result[0].lastUpdatedDate);
	
	if( dataRetrievingType == "intermediate"){
	   $('#updateDataDivId').show();
	}else{
		$('#updateDataDivId').hide();
	}
	
	var str='';
	if(reportLevelId == 3){
    str+='<div class="scrollDiv"><table  class="display tableC" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	}else{
	str+='<div class="scrollDiv1"><table  class="display tableC" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	}
	

	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>District</th>';
	}else{
	str+='<th>Constituency</th>';
	}
	
	str+='<th>Voters</th>';
    str+='<th>Cadres</th>';
	str+='<th style="width: 68px;">Invitees</th>';
	str+='<th>Non <br/>Invitees</th>';
	str+='<th>Total<br/> Attended</th>';
    str+='</tr></thead>';
    str+='<tbody>';
	
	if(result[0].locationName != "NO DATA"){
		for(var i in result){
		
			str+='<tr>';
			str+='<td>'+result[i].name+'</td>';
			str+='<td>'+result[i].voterCount+'</td>';
			str+='<td>'+result[i].cadreCount+'</td>';
			
			var inviteesCount = result[i].invitees;
			var nonInviteesCount = result[i].nonInvitees;
			var totalAttendees = inviteesCount + nonInviteesCount;
			
			
			str+='<td>'+inviteesCount+'</td>';
			str+='<td>'+nonInviteesCount+'</td>';
			str+='<td>'+totalAttendees+'</td>';
			str+='</tr>';
      }   
  }
	                            
	str+='</tbody></table></div>';
	if(reportLevelId == 3){
	  $("#districtTableId").html(str);
	}
	else{
	  $("#constiTableId").html(str);
	}
	$('#table'+reportLevelId).DataTable({
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "24%", "targets": 0 }]
    });
	if(reportLevelId == 3){
	$('.scrollDiv').slimScroll({
	height: '350px'
	});
	}
	else{
	$('.scrollDiv1').slimScroll({
	height: '350px'
	});
	} */
	
}
function buildConstTable(result,reportLevelId){
	$("#constAjax").hide();
	$('#lastUpdatedTimeId').html(result[0].lastUpdatedDate);
	var str='';
	str+='<div class="">';
	if(result[0].locationName != "NO DATA"){
		str+='<table class="table tableC table-condensed table-bordered" style="border-bottom:none" id="constituencyDataTableId">';
		str+='<thead style="background:#EFF3F4">';
		str+='<tr>';
		str+='<th rowspan="2" style="vertical-align:middle;" width="40px !important;"># ID</th>';
		str+='<th rowspan="2" style="vertical-align:middle;">DISTRICT</th>';
		str+='<th rowspan="2" style="vertical-align:middle;">CONSTITUENCY</th>';
		str+='<th rowspan="2" style="vertical-align:middle">TOTAL INVITEES</th>';
		str+='<th rowspan="2" style="vertical-align:middle;">TOTAL ATTENDED</th>';
		str+='<th rowspan="2" style="vertical-align:middle;">INVITEES ATTENDED</th>';
		str+='<th rowspan="2" style="vertical-align:middle;">NON INVITEES ATTENDED</th>';
	
			for(var i in result[0].subList){
				if(result[0].subList[i].totalDaydataExist == true){
					str+='<th class="text-center text-capitalize" colspan="3">'+result[0].subList[i].name+' ATTENDED</th>';
				}
				
			}
		str+='</tr>';
		str+='<tr>';
			for(var j in result[0].subList){
				if(result[0].subList[j].totalDaydataExist == true){
					str+='<th>Total</th>';
					str+='<th>Invitees</th>';
					str+='<th>Non Invitees</th>';
				}
			}
		str+='</tr>';
		str+='</thead>';
		str+='<tbody class="scrollLength">';
		
		for(var j in result){
			str+='<tr>';
			if(result[j].id == 0 || result[j].id == null){
				str+='<td class="text-center" > - </td>';
			}else{
				str+='<td class="text-center" >'+result[j].id+'</td>';
			}
			if(result[j].districtName == null){
				str+='<td > - </td>';
			}else{
				str+='<td >'+result[j].districtName+'</td>';
			}
			if(result[j].name == null ){
				str+='<td width="60px !important"> - </td>';
			}else{
				str+='<td width="60px !important">'+result[j].name+'</td>';
			}
			if(result[j].inviteesCalled ==0 || result[j].inviteesCalled == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].inviteesCalled+'</td>';
			}
			if(result[j].attendees ==0 || result[j].attendees == null){
				str+='<td class="text-center">-</td>';
			}else{
				str+='<td class="text-center">'+result[j].attendees+' <span>('+result[j].attendeePercantage+'%)</span></td>';
			}
			
			if(result[j].invitees ==0 || result[j].invitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].invitees+' <span>('+result[j].inviteePercantage+'%)</span></td>';
			}
			if(result[j].nonInvitees ==0 || result[j].nonInvitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].nonInvitees+' <span>('+result[j].nonInviteePercantage+'%)</span></td>';
			}
			
			for(var l in result[j].subList){
				
				if(result[0].subList[l].totalDaydataExist == true){
					
					if(parseInt(result[j].subList[l].attendees) == 0 || result[j].subList[l].attendees == null){
						
						str+='<td class="text-center"> - </td>';
					}else{
						str+='<td class="text-center">'+result[j].subList[l].attendees+'</td>';
					}
					if(parseInt(result[j].subList[l].invitees) == 0 || result[j].subList[l].invitees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						 str+='<td class="text-center">'+result[j].subList[l].invitees+'</td>';
					}
		 	       if(parseInt(result[j].subList[l].nonInvitees) == 0 || result[j].subList[l].nonInvitees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						str+='<td class="text-center">'+result[j].subList[l].nonInvitees+'</td>';
					}
			    }
			}
			
			str+='</tr>';
		}
	
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		$("#constiTableId").html(str);
		$("#constituecnyExcelBtnId").show();
	}else{
		$("#constiTableId").html("<p style='margin-top: 30px; text-align: center;'>NO DATA AVAILABLE</p>");
	}
	$('#constituencyDataTableId').DataTable({
		 "paging":   false,
		 "info":     false,
		 "searching": true,
		 "autoWidth": true,
		"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"ordering": false
		
    });
	$("#constituencyDataTableId_filter").css("margin-top","-29px");
	var Scrollerlength = $(".scrollLength").find("tr").length;
	if(Scrollerlength >=11){
		$("#constituencyDataTableId").css("display","block");
		$("#constituencyDataTableId").css("height","380px");
		$("#constituencyDataTableId").css("overflow-y","scroll");
		$("#constituencyDataTableId").css("overflow-x","scroll");
		
	}else{
		$("#constituencyDataTableId").css("height","auto");
		
	}
	/* if(reportLevelId == 3)
	{
		$("#distAjax").hide();
	}
	else
	{
		$("#constAjax").hide();
	}
	
	$('#lastUpdatedTimeId').html(result[0].lastUpdatedDate);
	
	if( dataRetrievingType == "intermediate"){
	   $('#updateDataDivId').show();
	}else{
		$('#updateDataDivId').hide();
	}
	
	var str='';
	if(reportLevelId == 3){
    str+='<div class="scrollDiv"><table  class="display tableC" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	}else{
	str+='<div class="scrollDiv1"><table  class="display tableC" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	}
	

	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>District</th>';
	}else{
	str+='<th>Constituency</th>';
	}
	
	str+='<th>Voters</th>';
    str+='<th>Cadres</th>';
	str+='<th style="width: 68px;">Invitees</th>';
	str+='<th>Non <br/>Invitees</th>';
	str+='<th>Total<br/> Attended</th>';
    str+='</tr></thead>';
    str+='<tbody>';
	
	if(result[0].locationName != "NO DATA"){
		for(var i in result){
		
			str+='<tr>';
			str+='<td>'+result[i].name+'</td>';
			str+='<td>'+result[i].voterCount+'</td>';
			str+='<td>'+result[i].cadreCount+'</td>';
			
			var inviteesCount = result[i].invitees;
			var nonInviteesCount = result[i].nonInvitees;
			var totalAttendees = inviteesCount + nonInviteesCount;
			
			
			str+='<td>'+inviteesCount+'</td>';
			str+='<td>'+nonInviteesCount+'</td>';
			str+='<td>'+totalAttendees+'</td>';
			str+='</tr>';
      }   
  }
	                            
	str+='</tbody></table></div>';
	if(reportLevelId == 3){
	  $("#districtTableId").html(str);
	}
	else{
	  $("#constiTableId").html(str);
	}
	$('#table'+reportLevelId).DataTable({
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "24%", "targets": 0 }]
    });
	if(reportLevelId == 3){
	$('.scrollDiv').slimScroll({
	height: '350px'
	});
	}
	else{
	$('.scrollDiv1').slimScroll({
	height: '350px'
	});
	} */
	
}
	$("#myonoffswitch").click(function(){
		$("#distEventId").val(0);
		if($('#myonoffswitch').is(":checked")){
			
		$('#myonoffswitch1').prop('checked', true);
		$("#districtHeading").html("AP DISTRICT WISE");
		$("#constiHeading").html("AP CONSTITUENCY WISE");
		getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
		getLocationWiseVisitorsCount(parentEventId,1,4);
		}else{
			
		$('#myonoffswitch1').prop('checked', false);
		$("#districtHeading").html("TS DISTRICT WISE");	
		$("#constiHeading").html("TS CONSTITUENCY WISE");
		getLocationWiseVisitorsCountForDistrict(parentEventId,36,3);
		getLocationWiseVisitorsCount(parentEventId,36,4);
		}
});
$("#myonoffswitch1").click(function(){
	if($('#myonoffswitch1').is(":checked")){
	getLocationWiseVisitorsCount(parentEventId,1,4);
	$("#constiHeading").html("AP CONSTITUENCY WISE");
	}else{
	getLocationWiseVisitorsCount(parentEventId,36,4);
	$("#constiHeading").html("TS CONSTITUENCY WISE");
	}
});



function getSubEventDetails(parentEventId){
	
	$('#overAllEventDiv').html('<center><img id="ajaxImage" src="images/Loading-data.gif" style="width:70px;height:60px;margin-top:100px;"/></center>');
	$('#donutchart').html("");
	$("#donutChartAjax").show();
	
	var jObj = {
			parentEventId:parentEventId,	
			subEvents : subEvents,
			startDate : startDate,
			endDate   :  endDate
		}	
		
		$.ajax({
          type:'GET',
          url: 'getSubEventDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		$("#donutChartAjax").hide();
		
		buildStartingPrograms(result);
		
	});
}
function buildStartingPrograms(result){
	
	$('#timeUpdationId').html(result[0].lastUpdatedDate);
	
    var str='';
	var total = 0;
	
	for(var i in result){ 
	   if( result[i].name != "Visitors in Campus")
		  total = total + result[i].invitees + result[i].nonInvitees;
    }
	var elementsLength = result.length;
	str+='<div style="background:#00A064;padding:3px 7px;color:#fff;">';
	str+='<h5 style="font-weight:bold">TOTAL UNIQUE VISITORS<span class="pull-right">'+result[0].total +'</span></h5>';
	str+='<h5 style="margin-top:2px;font-weight:bold">TOTAL UNIQUE INVITEES ATTENDED<span class="pull-right">'+result[0].uniqueInviteeVisitorsAttended+'</span></h5>';
	str+='<h5 style="margin-top:2px;font-weight:bolder;font-size:13px;">TOTAL UNIQUE NON-INVITEES ATTENDED <span class="pull-right">'+result[0].uniqueNonInviteeVisitorsAttended+'</span></h5>';
	str+='<h5 style="margin-top:2px;font-weight:bold">TOTAL VISITS <span class="pull-right">'+total+'</span></h5>';
	str+='</div>';
	str+='<div class="panel-body" >';
	str+=' <div class="scrollDiv2" style="background:rgba(0, 176, 125,0.4) ">';
	//str+='<hr class="m_0"/>	';
	var dataArr=[] ;
	var colorsarr = new Array();
	str+='<table class="table table-condensed" style="margin-top:10px;margin-bottom:0px">'
	
	for(var i in result)
	{	
		var color = getColorCodeByEvent(result[i].id);	
		if(color == null)
		{
			colorsarr.push('#A54423');
		}
		colorsarr.push(color);
		var count = result[i].invitees + result[i].nonInvitees;
		var entryName = result[i].name;
		
		if(entryName == "Pre Entry"){
			  
			  var validCount   = result[i].validCount;
			  var invalidCount = result[i].inValidCount;
			  str+='<tr>';
			  str+='<td>';
			  str+=' <p>'+result[i].name+'<span style="font-size:12px;">(';
			    if(validCount != null && validCount > 0){
					 str+=' Valid  - <a style="cursor:pointer;color:#fff;text-decoration:none" title="Click To See Valid Visitors Details" onClick="getSubEventMembers('+result[i].id+',0,'+count+',\''+result[i].name+'\');getStateWiseOverview('+result[i].id+')">'+validCount+'</a> |';
				}else{
					 str+=' Valid  - '+validCount+' ';
				}
			    str+=' Invalid  - '+invalidCount+' ';
			  str+=')</span></p>';
			  str+='<ul class="spanBorderRight">';
				for(var j in result[i].subList){
					if(result[i].subList[j].attendees !=null && result[i].subList[j].attendees > 0){
						str+='<li>'+result[i].subList[j].name+'&nbsp;:&nbsp;'+result[i].subList[j].attendees+'</li>';  
					}else{
						str+='<li>'+result[i].subList[j].name+' : -  </li>'; 
					}
					
				} 
			  str+='</ul>';
			  str+='</td>';
			  str+='<td>';
			  if(count >0 && result[i].id !=null){    
			      str+='  <span class="pull-right label-custom" style="width:45px;text-align:center"><a style="cursor:pointer;" title="Click To See Visitors Details" onClick="getSubEventMembers('+result[i].id+',0,'+count+',\''+result[i].name+'\');getStateWiseOverview('+result[i].id+')">'+count+'</a></span>';
			  }
			  else{
			     str+='  <span class="pull-right label-custom"  style="width:45px;text-align:center">'+count+'</span>';
			  }
			  str+='</td>';
			  str+='</tr>';
			  
			  
		}else{
			  str+='<tr>';
			  str+='<td style="padding:7px 5px">';
			  str+=' <p class="m_0">'+result[i].name+'</p>';
			  str+='<ul class="spanBorderRight">';
			    for(var j in result[i].subList){
					if(result[i].subList[j].attendees !=null && result[i].subList[j].attendees > 0){
						str+='<li>'+result[i].subList[j].name+'&nbsp;:&nbsp;'+result[i].subList[j].attendees+'</li>';  
					}else{
						str+='<li>'+result[i].subList[j].name+' : -  </li>'; 
					}
				} 
			  str+='</ul>';
			  str+='</td>';
			  str+='<td>';
			  if(count >0 && result[i].id !=null){    
			  str+='  <span class="pull-right label-custom"  style="width:45px;text-align:center"><a style="cursor:pointer;" title="Click To See Visitors Details" onClick="getSubEventMembers('+result[i].id+',0,'+count+',\''+result[i].name+'\');getStateWiseOverview('+result[i].id+')">'+count+'</a></span>';  
			  }
			  else{
			  str+='  <span class="pull-right label-custom"  style="width:45px;text-align:center">'+count+'</span>';
			  }
			  str+='</td>';
			  str+='</tr>';
		}
		
		//str+=' <br/>';
		//str+=' <hr class="m_0"/>';
		//str+=' <br/>';
		 if( result[i].name != "Visitors in Campus"){
		 var arr = [];
		arr.push(result[i].name,count);
		dataArr.push(arr);
		 }
	}
	
	str+='</table>';
	str+='</div>';
	
	  if( result != null && result.length > 0 ){
		 var isMahanaduEvent = result[0].mahanaduEvent;
		 if(isMahanaduEvent){
			  str+='<div id="registrationDiv"   style="background: rgb(0, 176, 125) none repeat scroll 0% 0%; margin-top: 5px;" >';
		       str+='<button   type="button" class="btn btn-info btn-block" onclick="showHide();" ><span id="registrationbtn">Show Registraion Details</span></button>';
			    str+='<span id="RegistrationCntDiv" style="display:none;display:inline-block;height:"></span>';
		       //str+='<div  style="display:none;margin-top: 10px; color: rgb(255, 255, 255); padding: 10px 20px;"></div>';
		      str+='</div>';
			  
			  $("#mahanaduEventDashBoardLinkId").show();
		 }else{
			  $("#mahanaduEventDashBoardLinkId").hide();			 
		 }
	  } 
	 str+='</div>'; 
	$("#overAllEventDiv").html(str);
	
	if(elementsLength > 4)
	{
		$('.scrollDiv2').slimScroll({
			height: '200px'
		});
	}
	
	buildPieChart(dataArr,total,colorsarr);
	
	heightSet();
}
function heightSet()
{
	$(".firstBlock").removeAttr("style")
	var maxHeight = 0;
	$(".firstBlock").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".firstBlock	").height(maxHeight);
}

function panelBlocksHeightSet()
{
	var maxHeight = 0;
	$(".panelBlocksHeightSet").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".panelBlocksHeightSet").height(maxHeight);
}

function buildPieChart(dataArr,total,colorsarr)
{


if(total == 0)
{
dataArr =[];
$('#donutchart').html("No Data Available").addClass("errorDiv");
return;
}
else
$('#donutchart').removeClass("errorDiv");
	Highcharts.setOptions({
	 colors:colorsarr
		 });
    $('#donutchart').highcharts({
        chart: {
			margin: [-80, 0,0 , 0],
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 20
            }
        },
         plotOptions: {
            pie: {
				size:'74%',
                innerSize: 200,
                depth: 20,
				showInLegend: true,			
				dataLabels: {
                        enabled: false
                  }
             
            }
        },
			legend: {
				enabled: true,
                align: 'center',
                x: 10,
                verticalAlign: 'bottom',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },	
		series: [{
            name : 'Visits',
            data: dataArr
        }]/*
		  series: [{
            name: 'Lunch',
            data: [10000, 3000, 5000, 30000, 40000, 6000, 8000, 1000, 4000, 9000, 500, 12000]
        }]*/
 
});
}

 function eventUpdate(checkDateChange){
	
	 if(checkDateChange != 'dateChange')
	 updateDatesButton();
	 
     $("#errorDiv").html("");
     subEvents = [];
	 var flag = true;
	 var errStr ='';
	 
	$(".maineventCls").each(function(){
    
		if($(this).is(":checked")){
			flag = false;
			parentEventId = $(this).val();
			$(".subeventCls").each(function(){
			if($(this).is(":checked"))
			subEvents.push($(this).val());
			})
		}
    });
	
	$(".maineventCls").each(function(){
		if($(this).is(":checked")){
			if($(this).val()==7 || $(this).val()==30){
				$("#stateDivsId").show();
			}else{
				$("#stateDivsId").hide();
			}
		}
	});	
	
	var evntName = $("#eventText"+parentEventId).text();
	var title = (evntName + ' Event').toUpperCase();
	var url = "eventDashboardAction.action?eventId="+parentEventId+"";
	ChangeUrl(''+title+'', ''+url+'');

    $("#mainheading").html(''+title+'');
	if(subEvents.length == 0){
		errStr +='<br/>Select atleast one event';
	    $("#errormsg").html(errStr);
	}
	startDate = $(".dp_startDate").val();
	endDate = $(".dp_endDate").val();
	getSubEvents();

	if(errStr.length == 0)
{


$(".themeControll").removeClass("active");
setcolorsForEvents();

/*  if($('#myonoffswitch').is(":checked")){
	getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
	getLocationWiseVisitorsCount(parentEventId,1,4);
 }else{
	$('#myonoffswitch').trigger("click");
    getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
    getLocationWiseVisitorsCount(parentEventId,1,4);

} */
   /*  var statesSelectedArray = [];
	$( ".checkedSwitch").each(function( index ){
		if($(this).prop('checked')){
			var stateChecked=$(this).val();
			statesSelectedArray.push(stateChecked);
		}
	}); */
 locationWiseCalls();
 
 countDetailsCalls();
stateWiseEventAttendeeCounts();
getPublicrepresentatives();

showConst = true;
showHide();
//getRegistrationsCnt();
}

}
 function updateDatesButton(){
	
   $( ".maineventCls").each(function( index ){
	  
     if($(this).prop('checked')){
		 
          var startDate = $(this).attr("attr_startDate");
		  var endDate = $(this).attr("attr_endDate");
		  
		   var formatedStartDate = $(this).attr("attr_formatedStartDate");
			var formatedEndDate = $(this).attr("attr_formatedEndDate");
		 
		 if( startDate != null && startDate.trim().length > 0 && 
		     endDate != null && endDate.trim().length > 0 )
		  {
			//start.format('D/MM/YYYY') + ' - ' + end.format('D/MM/YYYY')
			  $('#reportrange span').html(''+formatedStartDate+' - '+formatedEndDate+' ');	
			  $('#reportrange').data('daterangepicker').setStartDate(startDate);
			  $('#reportrange').data('daterangepicker').setEndDate(endDate);
			  
		  }else{
				$('#reportrange span').html(''+dpCurentDate+' - '+dpCurentDate+' ');	
			    $('#reportrange').data('daterangepicker').setStartDate(formatedDpCurentDate);
				$('#reportrange').data('daterangepicker').setEndDate(formatedDpCurentDate);
				
			  
		  }
      }
  }); 
 } 


	function locationWiseCalls(){
		
		/* if($('#myonoffswitch').is(":checked")){
			getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
			getLocationWiseVisitorsCount(parentEventId,1,4);
	    }else{
			getLocationWiseVisitorsCountForDistrict(parentEventId,36,3);
			getLocationWiseVisitorsCount(parentEventId,36,4);
		} */
		getLocationWiseVisitorsCountForDistrict(parentEventId,3);
		getLocationWiseVisitorsCount(parentEventId,4);
	}
	 
	function countDetailsCalls(){
		
		getSubEventDetails(parentEventId);
		getSubEventDetailsHourWise(parentEventId);
		getEventMemberCount(parentEventId);
	}

    function  callingDefaultCalls(){
		setcolorsForEvents();
		countDetailsCalls();
		locationWiseCalls();
		stateWiseEventAttendeeCounts();
	}
	
	
 function ChangeUrl(title, url) {
    if (typeof (history.pushState) != "undefined") {
        var obj = { Title: title, Url: url };
        history.pushState(obj, obj.Title, obj.Url);
    } else {
        alert("Browser does not support HTML5.");
    }
}

$(document).on("click",".dataSynchClass",function(){
	 
	 var ajaxImg = $(this).find('img').attr("id");
	 
	$("#"+ajaxImg).show();
	
	ajaxProcessing();
	var jObj = {
			parentEventId:parentEventId,
			subEvents : subEvents,
			startDate : startDate,
			endDate : endDate
		}
	
	$.ajax({
          type:'POST',
          url: 'insertDataintoEventInfoAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jObj)},

          success: function(result){ 
			 $("#"+ajaxImg).hide();
			 //closeDialogue();
			 //callingDefaultCalls();
			 locationWiseCalls();
			
			 
         },
          error:function() { 
		  closeDialogue();
           console.log('error', arguments);
         }
    });
	   
 });

var areaChartDataArr  = [];
var areaChartNamesArr =[];
var dayWiseArr =[];
var monthNames = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];

function getSubEventDetailsHourWise(parentEventId){
$("#dayWiseContainer").html("");
$('#hourWiseerrorDiv').html('<center><img id="hourWiseAjaxImage" src="images/Loading-data.gif" style="width:70px;height:60px;"/></center>');
$("#hourWiseContainer").html("");

	var jObj = {
			parentEventId:parentEventId,
			subEvents : subEvents,
			startDate : startDate,
			endDate : endDate
		}	
		
		$.ajax({
          type:'GET',
          url: 'getHourWiseSubEventsCountAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		//$("#hourWiseAjaxImage").hide();
				
				
				dayWiseArr = new Array();
				
				//get chart dates or hours.
				areaChartNamesArr = new Array();
				if(result != null && result.length > 0)
				{
					dayWiseArr = result;
				    for(var j in result[0].subList){
						if(startDate == endDate){
							areaChartNamesArr.push(result[0].subList[j].name);	
						}
						else{
							var dateString = result[0].subList[j].name;
							var date = new Date(result[0].subList[j].name);
							var month       = monthNames[date.getMonth()];
							var day         = dateString.substring(8,10);
							areaChartNamesArr.push(month+"-" +day);	
						}
					}
				}
				
				//get chart data values for date  or hour .
				 areaChartDataArr = new Array();
				 var colorsarr = new Array();
				if(result != null && result.length > 0)		
				for(var i in result)
				{	
				    var color = getColorCodeByEvent(result[i].id);	
					var areaChartDataArrInner = new Array();
					for(var j in result[i].subList){
						areaChartDataArrInner.push(result[i].subList[j].cadreCount);
					}
					var obj1={
						name:result[i].name,
						data:areaChartDataArrInner
					}
					colorsarr.push(color);
					areaChartDataArr.push(obj1);
					
				}
		
		if(startDate == endDate){
            $( "#rangeSliderDiv" ).css("display","none");
		    buildHourWiseChart(colorsarr);
		}else{
			 sliderForDateRange();
			 $( "#rangeSliderDiv" ).css("display","block");
		}
		});
}
var dateRange;	
function sliderForDateRange()
{
	
	//GET GIVEN DATES IN DATE RANGE PICKER
	var newstartdate = startDate.split("/").reverse().join("-");
	var newenddate = endDate.split("/").reverse().join("-");
	var minDate = new Date(newstartdate);
	var maxDate = new Date(newenddate);
	
	
   var flag = false;
 $( "#slider" ).slider({
	
	range: true,
    max: Math.floor((maxDate.getTime() - minDate.getTime()) / 86400000),
    step: 1,
    slide: function( event, ui ) {
        flag =true;
        var date = new Date(minDate.getTime());
        date.setDate(date.getDate() + ui.values[0]);
		$('.startDate').val($.datepicker.formatDate('mm/dd/yy', date));
		date = new Date(minDate.getTime());
		date.setDate(date.getDate() + ui.values[1]);
		$('.endDate').val($.datepicker.formatDate('mm/dd/yy', date));
		
	    var range = formatDate(date);
	    var month       = monthNames[date.getMonth()];
	    var day         = range.substring(8,10);			
        $( "#amount" ).val( "date : " +month+"-" +day +"");

		dateRange=date;
        buildHourWiseChartforslider(dateRange);
    },
    change: function( event, ui ) {

		 flag =true;
		 var date = new Date(minDate.getTime());
		 date.setDate(date.getDate() + ui.values[0]);
		 $('.startDate').val($.datepicker.formatDate('mm/dd/yy', date));
		 date = new Date(minDate.getTime());
		 date.setDate(date.getDate() + ui.values[1]);
		 $('.endDate').val($.datepicker.formatDate('mm/dd/yy', date));
		 
		 var range = formatDate(date);
		 var month       = monthNames[date.getMonth()];
		 var day         = range.substring(8,10);			
		 $( "#amount" ).val( "date : " +month+"-" +day +"");
		 
		dateRange=date;
		buildHourWiseChartforslider(dateRange);
    }
  });
  
  if(!flag){
	  
	var range = formatDate(maxDate);
	var month       = monthNames[maxDate.getMonth()];
	var day         = range.substring(8,10);	
	$( "#amount" ).val( "Date : " +month+"-" +day +"");
	dateRange=maxDate;
	buildHourWiseChartforslider(dateRange);
  }

  $('.ui-slider-handle').tooltip();
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function buildHourWiseChartforslider(dateRange){

	if(dayWiseArr == null || dayWiseArr.length == 0){
	  $("#hourWiseerrorDiv").css("display","block");
	  $('#hourWiseerrorDiv').html("No Data Available").addClass("errorDiv").css("height","359px");;
	  $('#dayWiseContainer').css("display","none");
	  return;
	}
	
	$("#hourWiseerrorDiv").css("display","none");
	$('#hourWiseContainer').css("display","none");
	$('#dayWiseContainer').css("display","block");
	
    var range = formatDate(dateRange);
	var compareDate = new Date(range);
	
	
	//get chart dates.
	areaChartNamesArr1 = new Array();
	if(dayWiseArr != null && dayWiseArr.length > 0){
	   for(var j in dayWiseArr[0].subList){
		 var dateString = dayWiseArr[0].subList[j].name;
		 var date = new Date(dayWiseArr[0].subList[j].name);
			if(date<=compareDate){
				var month       = monthNames[date.getMonth()];
				var day         = dateString.substring(8,10);
				areaChartNamesArr1.push(month+"-" +day);
			}							
	   }	
	}
	
	//get chart data values for date.
	var colorsarr = new Array();
    areaChartDataArr1 = new Array();	
	if(dayWiseArr != null && dayWiseArr.length > 0){
		for(var i in dayWiseArr){
			var color = getColorCodeByEvent(dayWiseArr[i].id);	
			var areaChartDataArrInner = new Array();
			for(var j in dayWiseArr[i].subList){	
			   var date1 = new Date(dayWiseArr[i].subList[j].name);
			   if(date1<=compareDate){
				  areaChartDataArrInner.push(dayWiseArr[i].subList[j].cadreCount); 
			   }
			}
			var obj1={
				name:dayWiseArr[i].name,
				data:areaChartDataArrInner
			}
			colorsarr.push(color);
			areaChartDataArr1.push(obj1);
		}
	}		
				
    if(areaChartDataArr1 == null || areaChartDataArr1.length == 0){

		$("#hourWiseerrorDiv").css("display","block");
		$('#hourWiseerrorDiv').html("No Data Available").addClass("errorDiv");
		return;
     }

     $("#hourWiseerrorDiv").css("display","none");
	 
     Highcharts.setOptions({
	  colors:colorsarr
	 });
	 
    $('#dayWiseContainer').highcharts({
		chart: {
            type: 'area'
        },
		title: {
            text: 'Visits',
            x: -100 //center
        },
		legend: {
            layout: 'vertical',
            align: 'center',
            verticalAlign: 'top',
            borderWidth: 0,
			enabled: true,
			x: 150 //center
        },
        xAxis: {
            categories: areaChartNamesArr1
        },
        yAxis: {
            title: {
                text: 'Visits'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ' Visits'
        },
        series:  areaChartDataArr1
       
    });

}
function buildHourWiseChart(colorsarr){

if(areaChartDataArr == null || areaChartDataArr.length == 0)
{
$("#hourWiseerrorDiv").css("display","block");
$('#hourWiseerrorDiv').html("No Data Available").addClass("errorDiv").css("height","390px");
$('#hourWiseContainer').css("display","none");
return;
}
$('#hourWiseContainer').css("display","block");
$('#dayWiseContainer').css("display","none");
$("#hourWiseerrorDiv").css("display","none");
Highcharts.setOptions({
	 colors:colorsarr
		 });
 $('#hourWiseContainer').highcharts({
		chart: {
            type: 'area'
        },
		title: {
            text: 'Visits',
            x: -100 //center
        },
		legend: {
            layout: 'vertical',
            align: 'center',
            verticalAlign: 'top',
            borderWidth: 0,
			enabled: true,
			x: 150 //center
        },
        xAxis: {
            categories: areaChartNamesArr
        },
        yAxis: {
            title: {
                text: 'Visits'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ' Visits'
        },
        series:  areaChartDataArr
        
       
    });

}

function getEventMemberCount(parentEventId){
$('#columnchart').html("");
$('#columnChartAjax').show();
	var jObj = {
			parentEventId:parentEventId,			
		    subEvents : subEvents,
			startDate : startDate,
			endDate : endDate
		}	
		
		$.ajax({
          type:'GET',
          url: 'getEventMemberCountAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		$('#columnChartAjax').hide();
		buildEventMemberCount(result);
	});
}
/* DONUT CHART */
function buildEventMemberCount(result){
var colorsarr = new Array();
if(result == null || result.length == 0)
{
$('#columnchart').html("No Data Available").addClass("errorDiv");
return;
}
else
{
$('#columnchart').removeClass("errorDiv");
var xaxis = [];
var dataArr = [];
for(var i in result)
{
var color = getColorCodeByEvent(result[i].id);
colorsarr.push(color);	
xaxis.push(result[i].name);
var data=[];
for(var j in result[i].subList)
data.push(result[i].subList[j].total)
	var obj = {
	name : result[i].name,
	data : data
	}
dataArr.push(obj);
}

Highcharts.setOptions({
	 colors:colorsarr
		 });
    $('#columnchart').highcharts({
        chart: {
            type: 'column'
        },
        
        xAxis: {
            categories: xaxis
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            },
            stackLabels: {
                enabled: false,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
				enabled: true,
                align: 'center',
                x: 10,
                verticalAlign: 'bottom',
                y: 20,
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },	
        tooltip: {
            formatter: function () {
				
				if(this.x ==  this.series.name)
				{

					 return '<b>  ' + this.x + '</b>' + ': Total ' + this.y + '<br/>';
				}
				else
				{
                return '<b>' + this.x + '<br/></b> <b>and ' +
                    this.series.name + '</b>: ' + this.y + '<br/>';
				}
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                    style: {
                        textShadow: '0 0 3px black'
                    }
                }
            },
			
	        series: {
	            pointWidth: 50
	       
			}
        },
        series:dataArr
    });
}
}

</script>
<script type="text/javascript">

getEvents();

$(".tbtn").click(function(){
    $(".themeControll").toggleClass("active");
});
function getSubEvents()
{
	
		var jObj = {
			eventId:parentEventId,			
			task:"getSubEvents"
		}	
		
		$.ajax({
          type:'GET',
          url: 'getSubEventsByParentEventAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$(".eventCls").find('option').remove();
			$(".eventCls").append('<option value="0">All Events</option>');	
				//for(var i in result){
				//$(".eventCls").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');			
				//}
				
			$(".maineventCls").each(function(){
				if($(this).is(":checked")){
					$(".subeventCls").each(function(){
					if($(this).is(":checked"))
						$(".eventCls").append('<option value="'+$(this).val()+'">'+$(this).attr("attr_label")+'</option>');
					});
				}
			});
		});

}


function getSubEventMembers(eventId,startIndex,count,name){

		
			$('#eventMembersDialog').find('h4').html('');
		$('#dialogueHead').html('');
		var jObj = {
			eventId:eventId,
			startDate : startDate,
			endDate : endDate,
			startIndex:startIndex,
			
			maxIndex : 25
		}	
		
		$.ajax({
          type:'GET',
          url: 'getSubEventMemberDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			var str="";
			str+="<div>";
			str+="<table class='table table-bordered' id='tableId'><thead>";
			str+="<tr>";
							
			str+="<th>NAME</th>";
			str+="<th>IMAGE</th>";
			str+="<th>MOBILE NUMBER</th>";
			str+="<th>CONSTITUENCY</th>";	
			str+="<th>INVITEE</th>";	
			str+="</tr>";
			str+="</thead>";
			str+="<tbody>";
			for(var i in result){
				str+="<tr>";
				
				str+="<td>"+result[i].name+"</td>";
				 str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].locationName+'"  style="height:50px;width:50px;"/></td>';
				str+="<td>"+result[i].mobileNo+"</td>";
				str+="<td>"+result[i].desc+"</td>";
				str+="<td>"+result[i].locationType+"</td>";
				str+="</tr>";
			}
			str+="<tbody>";
			str+="</table>";	
			str+='</div>';
			$("#eventMembersDiv").html(str);
			$("#tableId").dataTable({
				responsive: true,
				"info":     false,
				"searching": false,
				"sDom": '<"top"i>rt<"bottom"flp><"clear">',
				"bPaginate": false,
				"bLengthChange": false,
				"bAutoWidth": false,
				'iDisplayLength': 25
			});
			var maxResults=jObj.maxIndex;
	   
	     if(jObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: count,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*25;
					getSubEventMembers(eventId,num,count,name);
				
			}
		});
	}	
			$('#eventMembersDialog').find('h4').html('<span class="text-uppercase" id="dialogueHead">'+name+' EVENT VISITORS DETAILS</span>');
		
			$("#eventMembersDialog").modal("show");		
		});
}

function getStateWiseOverview(eventId){
		$("#stateWiseOverView").html('');
		var jObj = {
			eventId:eventId,
			startDate : startDate,
			endDate : endDate
			
			
		}	
		
		$.ajax({
          type:'GET',
          url: 'getSubEventMemberCountAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			var str = '';
			/*str+='<table>';
			str+='<tr>';
			str+='<th>State</th>';
			str+='<th>Count</th>';
			str+='</tr>';
			for(var i in result)
			  {
				str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].total+'</td>';
				str+='</tr>';
			  }
			str+='</table>';*/
			var total = 0;
			for(var i in result)
			  {
				 total = result[i].total+ total;
			  }
			str+=' <h5 class="text-center" style="margin-bottom: 0px;"> TOTAL COUNT  '+total+'</h5>';
			
			str+='<ul class="list-inline otherstate" style="margin-left: 10px;">';
			for(var i in result)
			  {
			str+='<li>'+result[i].name+' <span class="badge  pull-right"> '+result[i].total+'</span></li>';
			  }
    
			str+='</ul>';
  
		$("#stateWiseOverView").html(str);
			
		});
}

function ajaxProcessing()
{
$("#syncModal").modal("show");
setTimeout(function(){
	$("#syncModal").modal("hide");
},2000)

/*$("#processingDialogue").dialog({
	  
		//width:300,
		//height:100,
		autoOpen: false,
		modal: true,
		position:'center',
		resizable: false,
		closeOnEscape: false
			
});
$('#processingDialogue').siblings('div.ui-dialog-titlebar').hide();
$('#processingDialogue').closest('.ui-dialog').addClass("customclass");
$('#processingDialogue').addClass("customclass1");
$('#processingDialogue').removeClass("ui-widget-content ui-dialog-content");
//$(".ui-dialog-titlebar").hide();
// $(".ui-dialog").addClass("customclass");
//$('.ui-widget-content').css({'border':'none','background':'none'});
//$('.ui-dialog-content').dialog("option","position","center");
$('.ui-dialog').css({left:378 ,width:512});
$('#processingDialogue').closest('.ui-icon').css("display","none");
 $("#processingDialogue").dialog('open').html("<div style='margin-top:330px;'><img src='images/icons/loading.gif' style=''/></div>");
 */
}
function closeDialogue()
{
$( "#processingDialogue" ).dialog('close');
}

function getRegistrationsCnt()
{


	$("#RegistrationCntDiv").html('<center><img id="img" src="images/icons/loading.gif" style="width:30px;height:30px;"/></center>');
		var jObj = {
			
			startDate : startDate,
			endDate : endDate
		}	
		
		$.ajax({
          type:'GET',
          url: 'getCadreRegistrationsCnt.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			 $("#RegistrationCntDiv").html('');
			var str = '';
			str+='<table class="table table-condensed m_0" style="margin-top:3px;">';
				str+='<tr>';
					str+='<td style="padding:2px 5px">Cadre Registration<span class="pull-right label-custom">'+result.total+'</span></td>';
				str+='</tr>';
				str+='<tr>';
					str+='<td style="padding:2px 5px">Cards Reprint<span class="pull-right label-custom">'+result.reprintCnt+'</span></td>';
				str+='</tr>';
			str+='</table>';
			$("#RegistrationCntDiv").html(str);
			heightSet();
			});
}

var showConst=false;
function showHide()
{	
		if(showConst) {
			
			$("#RegistrationCntDiv").hide();
			$(".slimScrollBar").css("top","0px !important");
			$("#registrationbtn").html('Show Registration Details'); 
			showConst=false;
		}
		else {
			
			$("#RegistrationCntDiv").css("display","block");
			getRegistrationsCnt();
			$("#registrationbtn").html('Hide Registration Details');
			$(".slimScrollBar").css("top","127px !important");
			showConst=true;	
		}
		heightSet();
}


$(document).on('click','#mahanaduLinkId',function(){
	
	var eventId = 0;
	$(".maineventCls").each(function(){
		if($(this).is(":checked")){
			eventId = $(this).val();
		}
	});
	
	//var value =  window.open("mahanaduCadreVisitInfoAction.action?eventId="+eventId+"",'_blank');
	var value =  window.open("mahanaduCadreVisitNewInfoAction.action",'_blank');
	//var value =  window.open("http://localhost:8080/PartyAnalyst/mahanaduCadreVisitInfoAction.action?eventId="+eventId+"",'_blank');
	
});


 $(document).on('click','.checkedSwitch',function(){
	 $("#showErrorMsg").html("")
		var statesArray =[];
		 $( ".checkedSwitch").each(function( index ){
			if($(this).prop('checked')){
				var stateChecked=$(this).val();
				statesArray.push(stateChecked);
			}
		});	 
	
	var statesArrayLength = statesArray.length;
	
	
	if(statesArrayLength == 0){
		$("#showErrorMsg").html("Please Select State")
		return;
		
	}else{
		locationWiseCalls();
	}
	
}); 
function generateExcel(){
	tableToExcel(datatableId, 'District Wise Report');
}
function generateExcel1(){
	tableToExcel(constituencyDataTableId, 'Constituency Wise Report');
}
</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()

  $(document).on('click','.publcRepAttnds',function(){
	  //alert($(this).attr("attr_day"))
	 //$("#popupId").modal("show");
	 var inviteeType = $(this).attr("attr_inviteeType");
	 var desigId = $(this).attr("attr_desigId");
	 var day = $(this).attr("attr_day");
	 var eventId = $(this).attr("attr_event");
	 var jObj = {
			 designationId  : desigId,
			 inviteeType 	: inviteeType,
			 day            : day,
			 eventId		: eventId
		}	
		
		 $.ajax({
          type:'POST',
          url: 'getCandidateDetailsAction.action',
		   data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			var str='';
			if(result != null && result.length > 0){
				str+='<table class="table table-bordered">';
				str+='<thead>';
				str+='<th>IMAGE</th>';
				str+='<th>NAME</th>';
				str+='<th>DESIGNATION</th>';
				str+='<th>CONSTITUENCY</th>';
				str+='<th>ATTENDED</th>';
				str+='</thead>';
				for(var i in result){
					str+='<tr>';
					str+='<td><img src="'+result[i].image+'" class="img-responsive" /></td>';
					str+='<td>'+result[i].candidateName+'</td>';
					str+='<td>'+result[i].designation+'</td>';
					str+='<td>'+result[i].stateName+'</td>';
					if(result[i].status){
						str+='<td>Yes</td>';
					}else if(!result[i].status){
						str+='<td>No</td>';
					}
					str+='</tr>';
				}
				str+='</table>';
			}else{
				str+='No Date Availabel.';
			}
			$("#showModelConstcy").html(str);
			$("#popupId").modal("show");
		 });
}); 

function getPublicrepresentatives(){
		
		$("#publicRepresentativeDiv").html('');
		$("#publicRepresentativeAjax").show();
			var jsObj = {
				startDate    :startDate,
				endDate      :endDate,
				parentEventId      :parentEventId,
				subEventIds : subEvents
			}
			$.ajax({
			type : 'GET',
			url : 'getPublicrepresentativesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){ 
			$("#publicRepresentativeAjax").hide();
			buildPublicrepresentativesCount(result)
			});   
			
	}
	
	function buildPublicrepresentativesCount(result){
		
		var str='';
		if(result !=null && result.length>0){
			str+='<table class="table table-bordered" style="background: rgb(239, 243, 244) none repeat scroll 0px 0px;">';
			  str+='<tr>';
				str+='<td rowspan="2"></td>';
				str+='<td class="text-center text-capitalize" style="vertical-align:middle" rowspan="2">Total Invitees</td>';
				for(var i in result[0].subList){
					if(result[0].subList[i].totalDaydataExist == true){
						str+='<th class="text-center text-capitalize" colspan="2">'+result[0].subList[i].name+'</th>';
					}
				}	
			  str+='</tr>';
			  str+='<tr>';
				for(var i in result[0].subList){
					if(result[0].subList[i].totalDaydataExist == true){
						str+='<td class="text-center text-capitalize" >Attended</td>';
						str+='<td class="text-center text-capitalize" >Not Attended</td>';
					}
				}
			  str+='</tr>';
			  	for(var j in result){
					str+='<tr>';
						str+='<td>'+result[j].name+'</td>';
						if(result[j].invitees == null || result[j].invitees ==0){
							str+='<td class="text-center text-capitalize" > - </td>';
						}else{
							str+='<td class="text-center text-capitalize" ><a style="cursor:pointer;" attr_inviteeType="total" attr_desigId="'+result[j].id+'" attr_event="30" attr_day="" class="publcRepAttnds">'+result[j].invitees+'</a></td>';
						}
						
						for(var l in result[j].subList){
							if(result[0].subList[l].totalDaydataExist == true){
								if(result[j].subList[l].attended == null || result[j].subList[l].attended == 0){
									str+='<td class="text-center text-capitalize" > - </td>';
								}else{
									str+='<td class="text-center text-capitalize" ><a style="cursor:pointer;" attr_inviteeType="attendee" attr_desigId="'+result[j].id+'" attr_event="30" attr_day="'+result[j].subList[l].dateStr+'" class="publcRepAttnds">'+result[j].subList[l].attended+'</a></td>';
								}
								if(result[j].subList[l].notAttended == null || result[j].subList[l].notAttended == 0){
									str+='<td class="text-center text-capitalize" > - </td>';
								}else{
									str+='<td class="text-center text-capitalize" ><a style="cursor:pointer;" attr_inviteeType="notAttendee" attr_desigId="'+result[j].id+'" attr_event="30" attr_day="'+result[j].subList[l].dateStr+'" class="publcRepAttnds">'+result[j].subList[l].notAttended+'</a></td>';
								}
								
							}
						}
					str+='</tr>';
				}
			
			str+='</table>';
			$("#publicRepresentativeDiv").html(str);
		}else{
			$("#publicRepresentativeDiv").html("NO DATA AVAILABLE");
		}
			
		
	} 
</script>
</body>
</html>
