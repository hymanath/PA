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
<link href="dist/eventDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">


<style>
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
</style>
</head>

<body>
<!--<header  class="eventsheader">
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/eventDashboard/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/eventDashboard/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:32px;"></p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/eventDashboard/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					   <li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>-->
 <div class="themeControll">
 <!--<h4 class="m_bottom0">
 	<form class="me-select display-style">
        <ul id="me-select-list">
            <li><input id="cb36" name="cb11" type="checkbox">
                <label for="cb36" class="m_0 collapse-select"><span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#" aria-controls="collapseOne" class="col-drop-head">All Events</a></span></label></li>
        </ul>
	</form>
  </h4>-->
  <div class="linkinner"> 
      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" ></div>
  </div>
  <p class="tbtn"> <i class="glyphicon glyphicon-filter"></i> FILTERS</p>
</div>   
    

<section>
	<div class="container">	
	<div id="mahanaduEventDashBoardLinkId" class="row" style="display:none"> <a id="mahanaduLinkId" class="puu-right" style="cursor:pointer">MAHANADU ENTRY/EXIT DASHBOARD</a></div>
	<div class="row" style="padding:5px;">
		
		<div class="col-md-3 col-xs-offset-7">
        	   <div id="reportrange" class="pull-right calendar-style">
                  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                  <span>April 1, 2015 - April 30, 2015</span> <b class="caret"></b>
               </div>
        </div>
		
		<div class="col-xs-1 pull-right">
		     <a onclick="insertIntermediateData();" class="btn btn-xs btn-success btn-block">
				<span class="" style="font-size: 15px;"> Sync
					<img src="images/ajaxImg2.gif" id="syncAjaxImage" style="height:20px;width:20px;display:none;"/>
				</span>
			 </a>
		</div>
		
		<div class="col-xs-1 pull-right">
		     <a class="btn btn-md btn-success btn-block" onclick="callingDefaultCalls();">
				<span class="glyphicon glyphicon-refresh"></span>
			</a>
		</div>
	
    </div>
		
		
		
		
		

	<div class="row">
    	<div class="col-md-4 col-sm-6 col-xs-12" style="">
        	<div class="panel panel-default panel-custom-green">
              <div class="panel-heading">overall events status </div>
              <div class="panel-body"  id="overAllEventDiv" style="height: 371px;">    
			  

              </div>
			
            </div>
        </div>
		
        <div class="col-md-8 col-xs-12 col-sm-6">
		<div id="rangeSliderDiv" style="display:none;">
		<div id="slider"></div>
		<p>
		<input id="amount" type="text" style="border: none; font-weight: bold;background-color:transparent;" readonly>
		</p>
		</div>
	<div id="hourWiseerrorDiv" style="width: 100%; height: 400px; margin: 0px auto -66px;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;text-align:center;margin-top:10px;padding-top:165px;"></div>
        		<div id="hourWiseContainer" style="width: 100%; height: 400px; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;margin-top:0px;display:none;"></div>
					<div id="dayWiseContainer" style="width: 100%; height:356px; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;margin-top:10px;display:none;"></div>
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
	
	
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default panel-custom-default m_0">
					<div class="panel-heading">
						
						<div class="row">
							<div class="col-md-4">
								<select id="distEventId" style="margin-top:-5px;" class="eventCls form-control" onChange="getLocationWiseCountBySubEvents(3);getLocationWiseCountBySubEvents(4);">
									<option value="0"> All Events</option>
								</select>
							</div>
							<div class="col-md-2 col-md-offset-2">
								<div class="onoffswitch pull-right">
									<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
									<label class="onoffswitch-label" for="myonoffswitch">
										<span class="onoffswitch-inner"></span>
										<span class="onoffswitch-switch"></span>
									</label>
								</div>
							</div>
						</div>
						
					</div>
					<div class="panel-body" style="background-color:#f2f2f2">
						
						<div class="row m_top6">
							<div class="col-md-6">
								<div class="panel panel-default m_0">
									<div class="panel-heading">
									<p class="m_0 display-style" id="districtHeading">AP DISTRICT WISE</p>
									
									
									</div>
									<div class="panel-body pad_5" style="min-height:300px;">				
											
											<center><img id="distAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
										 <div id="districtTableId"> </div>
									</div>
								</div>
								
							</div>
							<div class="col-md-6">
								<div class="panel panel-default m_0">
									<div class="panel-heading">
									<p class="m_0 display-style" id="constiHeading">AP CONSTITUENCY WISE</p>
									<div class="onoffswitch pull-right" style="display:none;">
										<input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" disabled id="myonoffswitch1" checked>
										<label class="onoffswitch-label" for="myonoffswitch1">
											<span class="onoffswitch-inner"></span>
											<span class="onoffswitch-switch"></span>
										</label>
									</div>
									</div>
									<div class="panel-body pad_5" style="min-height:400px;">
									<!--<select id="constiEventId" class="eventCls form-control" style="margin-top:-5px;" onChange="getLocationWiseCountBySubEvents(4)"><option value="0"> All Events</option></select>-->
									<center><img id="constAjax" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
										
									   <div id="constiTableId"></div>
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
str+='<li><input id="mainEvent'+result[i].id+'" name="cb11" type="checkbox" onclick="handalClick('+result[i].id+')" class="maineventCls" value="'+result[i].id+'" checked>';
}
else
{
str+='<li><input id="mainEvent'+result[i].id+'" name="cb11" type="checkbox" onclick="handalClick('+result[i].id+')" class="maineventCls" value="'+result[i].id+'">';
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
str+='<li><input id="check'+result[i].subList[j].id+'" name="cb11" onclick="checkParent(\''+result[i].id+'\',\''+result[i].subList[j].id+'\')" type="checkbox" class="subeventCls subeventCls'+result[i].id+'" value="'+result[i].subList[j].id+'" checked>';
str+=' <label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+result[i].subList[j].name+'</span></label></li>';
}
else
{
str+='<li><input id="check'+result[i].subList[j].id+'" name="cb11" onclick="checkParent(\''+result[i].id+'\',\''+result[i].subList[j].id+'\')" type="checkbox" class="subeventCls subeventCls'+result[i].id+'" value="'+result[i].subList[j].id+'" ">';
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
str+='<button class="btn btn-block btn-default btn-custom" onclick="eventUpdate();">UPDATE</button>';
$("#accordion").html(str);
eventUpdate();
}

 $(document).ready(function() {

                  var cb = function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('D MMMM, YYYY') + ' - ' + end.format('D MMMM, YYYY'));
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
                    opens: 'left',
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

                  $('#reportrange span').html(moment().format('D MMMM, YYYY') + ' - ' + moment().format('D MMMM, YYYY'));

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
	eventUpdate();
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





function getLocationWiseVisitorsCount(eventId,stateId,reportLevelId)
{

if(reportLevelId == 3){
	$("#distAjax").show();
	$("#districtTableId").html("");
	}
	else{
	$("#constAjax").show();
	$("#constiTableId").html("");
	}
	var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:reportLevelId,
			subEvents : subEvents,
			startDate : startDate,
			endDate : endDate
		
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{				
				buildDistrictTable(result,reportLevelId)	
			}
	});
}
function buildDistrictTable(result,reportLevelId){
	if(reportLevelId == 3)
	{
		$("#distAjax").hide();
	}
	else
	{
		$("#constAjax").hide();
	}
	var str='';
	if(reportLevelId == 3){
    str+='<div class="scrollDiv"><table  class="display" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	}else{
	str+='<div class="scrollDiv1"><table  class="display" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	}
	

	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>DISTRICT</th>';
	}else{
	str+='<th>CONSTITUENCY</th>';
	}
	str+='<th>VOTERS</th>';
    str+='<th>CADERS</th>';
	str+='<th>INVITEES</th>';
	str+='<th>NON <br/>INVITEES</th>';
	str+='<th>Total<br/> Attendences</th>';
    str+='</tr></thead>';
    str+='<tbody>';
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
	}
	
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
    var str='';
	var total = 0;
	for(var i in result)
       {  
		 if( result[i].name != "Visitors in Campus")
		    total = total + result[i].invitees + result[i].nonInvitees;
       }
	str+='<div class="text-center">';
	str+='<h3 class="display-style m_top0">TOTAL VISITS-</h3>';
	str+=' <h1 class="display-style m_top0">'+total+'</h1>';
	str+=' </div>';
	str+='<div class="text-center" style="margin-top: -5px; margin-bottom: 5px;">[ Unique Visitors  -'+result[0].total +' ]</div>';
	str+=' <div class="scrollDiv2">';
	str+='<hr class=""/>	';
	var dataArr=[] ;
	var colorsarr = new Array();
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
			  
			  str+=' <span class="pull-left">'+result[i].name+'</span>  <br/><span style="font-size:16px;">';
			    if(validCount != null && validCount > 0){
					 str+='[ Valid  - <a style="cursor:pointer;color:#fff;text-decoration:none" title="Click To See Valid Visitors Details" onClick="getSubEventMembers('+result[i].id+',0,'+count+',\''+result[i].name+'\');getStateWiseOverview('+result[i].id+')">'+validCount+'</a> ]';
				}else{
					 str+='[ Valid  - '+validCount+' ]';
				}
			    str+='[ Invalid  - '+invalidCount+' ]';
			  str+='</span>';
			  
			  if(count >0 && result[i].id !=null){    
			      str+='  <span class="pull-right label-custom" style="margin-top: -15px;"><a style="cursor:pointer;" title="Click To See Visitors Details" onClick="getSubEventMembers('+result[i].id+',0,'+count+',\''+result[i].name+'\');getStateWiseOverview('+result[i].id+')">'+count+'</a></span>';
			  }
			  else{
			     str+='  <span class="pull-right label-custom" style="margin-top: -15px;">'+count+'</span>';
			  }
			  
		}else{
			
			  str+=' <span class="pull-left">'+result[i].name+'</span>';
			  if(count >0 && result[i].id !=null){    
			  str+='  <span class="pull-right label-custom"><a style="cursor:pointer;" title="Click To See Visitors Details" onClick="getSubEventMembers('+result[i].id+',0,'+count+',\''+result[i].name+'\');getStateWiseOverview('+result[i].id+')">'+count+'</a></span>';  
			  }
			  else{
			  str+='  <span class="pull-right label-custom">'+count+'</span>';
			  }
		}
		
		str+=' <br/>';
		str+=' <hr class="m_top10"/>';
		str+=' <br/>';
		 if( result[i].name != "Visitors in Campus"){
		 var arr = [];
		arr.push(result[i].name,count);
		dataArr.push(arr);
		 }
	}
	  if( result != null && result.length > 0 ){
		 var isMahanaduEvent = result[0].mahanaduEvent;
		 if(isMahanaduEvent){
			  str+='<div id="registrationDiv"   style="background: rgb(0, 176, 125) none repeat scroll 0% 0%; margin-top: -5px;" >';
		       str+='<button   type="button" class="btn btn-info btn-block" onclick="showHide();" id="registrationbtn">Show Registraion Details</button>';
		       str+='<div id="RegistrationCntDiv" style="display:none;margin-top: 10px; color: rgb(255, 255, 255); padding: 10px 20px;"></div>';
		      str+='</div>';
			  
			  $("#mahanaduEventDashBoardLinkId").show();
		 }else{
			  $("#mahanaduEventDashBoardLinkId").hide();			 
		 }
	  } 
	 
	$("#overAllEventDiv").html(str);

		
	$('.scrollDiv2').slimScroll({
	height: '260px'
	});
	buildPieChart(dataArr,total,colorsarr);
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
 function eventUpdate(){

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

 if($('#myonoffswitch').is(":checked")){
	getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
	getLocationWiseVisitorsCount(parentEventId,1,4);
 }else{
	$('#myonoffswitch').trigger("click");
    getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
    getLocationWiseVisitorsCount(parentEventId,1,4);
}
 countDetailsCalls();


showConst = true;
showHide();
//getRegistrationsCnt();
}

}
	function locationWiseCalls(){
		
		if($('#myonoffswitch').is(":checked")){
			getLocationWiseVisitorsCountForDistrict(parentEventId,1,3);
			getLocationWiseVisitorsCount(parentEventId,1,4);
	    }else{
			getLocationWiseVisitorsCountForDistrict(parentEventId,36,3);
			getLocationWiseVisitorsCount(parentEventId,36,4);
		}
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
	}
	
	
 function ChangeUrl(title, url) {
    if (typeof (history.pushState) != "undefined") {
        var obj = { Title: title, Url: url };
        history.pushState(obj, obj.Title, obj.Url);
    } else {
        alert("Browser does not support HTML5.");
    }
}


function insertIntermediateData()
{   

    $("#syncAjaxImage").show();
	ajaxProcessing();
	var jObj = {
			
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
			 $("#syncAjaxImage").hide();
			 closeDialogue();
			 callingDefaultCalls();
			 locationWiseCalls();
			 
         },
          error:function() { 
		  closeDialogue();
           console.log('error', arguments);
         }
    });

}
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
		
				areaChartDataArr = new Array();
				areaChartNamesArr = new Array();
				dayWiseArr = new Array();
				var colorsarr = new Array();
				if(result != null && result.length > 0)
				{
					dayWiseArr = result;
				for(var j in result[0].subList){
						if(startDate == endDate)				
					    areaChartNamesArr.push(result[0].subList[j].name);	
						else{
						
						var dateString = result[0].subList[j].name;
						var date = new Date(result[0].subList[j].name);
						var month       = monthNames[date.getMonth()];
						var day         = dateString.substring(8,10);
						areaChartNamesArr.push(month+"-" +day);	
						}
						}
				}
				if(result != null && result.length > 0)		
				for(var i in result)
				{	
				var color = getColorCodeByEvent(result[i].id);	
					var areaChartDataArrInner = new Array();
					for(var j in result[i].subList){	
					  //  areaChartNamesArr.push(result[0].subList[j].name);					
						areaChartDataArrInner.push(result[i].subList[j].cadreCount);
					}
					var obj1={
						name:result[i].name,
						data:areaChartDataArrInner
					}
					colorsarr.push(color);
					areaChartDataArr.push(obj1);
					
				}
		
		if(startDate == endDate)	
		{
		$( "#rangeSliderDiv" ).css("display","none");
		buildHourWiseChart(colorsarr);
		}
		else{
			sliderForDateRange();
			$( "#rangeSliderDiv" ).css("display","block");
		}
		});
}
var dateRange;	
function sliderForDateRange()
{

var flag = false;
var newstartdate = startDate.split("/").reverse().join("-");
var newenddate = endDate.split("/").reverse().join("-");
var minDate = new Date(newstartdate);
var maxDate = new Date(newenddate);
$( "#slider" ).slider({range: true,
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
if(!flag)
	{
	var range = formatDate(maxDate);
	var month       = monthNames[maxDate.getMonth()];
	var day         = range.substring(8,10);	
	$( "#amount" ).val( "Date : " +month+"-" +day +"");
	dateRange=maxDate
	;
	buildHourWiseChartforslider(dateRange);
	}

$('.ui-slider-handle').tooltip();
  //
  // Add labels to slider whose values 
  // are specified by min, max and whose
  // step is set to 1
  //
 /* var total = 0;
  var startTime = minDate.getTime(), endTime = maxDate.getTime();
 for(loopTime = startTime; loopTime < endTime; loopTime += 86400000)
{
total = total + 1;
}
var i = 0;
for(loopTime = startTime; loopTime < endTime; loopTime += 86400000)
{
       
      var loopDay=new Date(loopTime)
     //  minDate = new Date(newDate);
	   var text = formatDate(loopDay);
	    var el = $('<label>'+(text)+'</label>').css('left',(i/total*100)+'');
		$( "#slider" ).append(el);
		i++;
    }
*/


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

if(dayWiseArr == null || dayWiseArr.length == 0)
{
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
				
				areaChartDataArr1 = new Array();
				areaChartNamesArr1 = new Array();
				
				var colorsarr = new Array();
				if(dayWiseArr != null && dayWiseArr.length > 0)
				{
				
				for(var j in dayWiseArr[0].subList){
						
						//var monthNames = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
						var dateString = dayWiseArr[0].subList[j].name;
						var date = new Date(dayWiseArr[0].subList[j].name);
								if(date<=compareDate)
								{
									var month       = monthNames[date.getMonth()];
									var day         = dateString.substring(8,10);
									areaChartNamesArr1.push(month+"-" +day);
								}							
						}
						
				}
				
				if(dayWiseArr != null && dayWiseArr.length > 0)		
				for(var i in dayWiseArr)
				{	
				var color = getColorCodeByEvent(dayWiseArr[i].id);	
					var areaChartDataArrInner = new Array();
					for(var j in dayWiseArr[i].subList){	
					 	var date1 = new Date(dayWiseArr[i].subList[j].name);
					 if(date1<=compareDate)
					areaChartDataArrInner.push(dayWiseArr[i].subList[j].cadreCount);
					}
					var obj1={
						name:dayWiseArr[i].name,
						data:areaChartDataArrInner
					}
					colorsarr.push(color);
					
					areaChartDataArr1.push(obj1);
					
				}
				
				
if(areaChartDataArr1 == null || areaChartDataArr1.length == 0)
{
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
				for(var i in result){
				
				$(".eventCls").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');			
				}
	});

}
function getLocationWiseCountBySubEvents(reportLevelId){
var subEvents1 = [];
var stateId =0;
var subIds =0;
subIds = $("#distEventId").val();
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

 if(subIds == 0){
 subEvents1 = subEvents;
 }else{
 subEvents1.push(subIds);
 }
 if($('#myonoffswitch').is(":checked"))
 {
 stateId = 1;
 }else{
 stateId = 36;
 }
 
var jObj = {
			eventId:parentEventId,			
			stateId:stateId,
			reportLevelId:reportLevelId,
			subEvents : subEvents1,
			startDate : startDate,
			endDate : endDate
		
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(reportLevelId == 3){
			$("#distAjax").show();
			
			}
			else{
			$("#constAjax").show();
			
			}

			if(result != null)
			{	
				
				buildDistrictTable(result,reportLevelId)	
			}
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
			str+=' <span class="pull-left">Mahanadu Registration</span>';
			str+='  <span class="pull-right label-custom">'+result.total+'</span>';
			str+=' <br/>';
			str+=' <hr class="m_top10"/>';
			str+=' <span class="pull-left">Card Reprint</span>';
			str+='  <span class="pull-right label-custom">'+result.reprintCnt+'</span>';
			str+=' <br/>';
			$("#RegistrationCntDiv").html(str);
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
}
function getLocationWiseVisitorsCountForDistrict(eventId,stateId,reportLevelId)
{
if(reportLevelId == 3){
	$("#distAjax").show();
	$("#districtTableId").html("");
	}else{
	$("#constAjax").show();
	$("#constiTableId").html("");
	}
	var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:reportLevelId,
			subEvents : subEvents,
			startDate : startDate,
			endDate : endDate
		
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountForDistrictAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{				
				buildDistrictTable(result,reportLevelId)	
			}
	});
}

$(document).on('click','#mahanaduLinkId',function(){
	
	var eventId = 0;
	$(".maineventCls").each(function(){
		if($(this).is(":checked")){
			eventId = $(this).val();
		}
	});
	
	var value =  window.open("http://mytdp.com/mahanaduCadreVisitInfoAction.action?eventId="+eventId+"",'_blank');
	//var value =  window.open("http://localhost:8080/PartyAnalyst/mahanaduCadreVisitInfoAction.action?eventId="+eventId+"",'_blank');
	
}); 

</script>
</body>
</html>
