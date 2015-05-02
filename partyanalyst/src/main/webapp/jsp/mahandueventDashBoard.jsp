<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Party Office Event</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">


<style>
.errorDiv{color:red;}
</style>
</head>

<body>
<header  class="eventsheader">
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                <p class="header-text display-style">PARTY OFFICE EVENT</p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					  
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>
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
      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >
              
                </div>
  </div>
  <p class="tbtn"> <i class="glyphicon glyphicon-filter"></i> FILTERS</p>
</div>   
    

<section class="container">
		
	<div class="row" style="">
		
		<div class="col-md-3 col-md-offset-8">
        	   <div id="reportrange" class="pull-right calendar-style">
                  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                  <span>April 1, 2015 - April 30, 2015</span> <b class="caret"></b>
               </div>
        </div>
		<div class="col-md-1">
			<a class="btn btn-md btn-success btn-block" onclick="insertIntermediateData();">
				<span class="glyphicon glyphicon-refresh"></span>
			</a>
		</div>
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
		
        		<div id="hourWiseContainer" style="width: 100%; height: 100%; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;"></div>
        </div>

    </div>
	<div class="row">
		<div class="col-md-5 col-xs-12 col-sm-6">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">total event visits</div>
				<div class="panel-body">
					<div id="donutchart" style="width: 100%; height: 100%; margin: 0 auto;border-radius:5px"></div>
				</div>
			</div>
		</div>
		<div class="col-md-7 col-xs-12 col-sm-6">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">event wise repeated members count</div>
				<div class="panel-body">
					<div id="columnchart" style="width: 100%; height: 100%; margin: 0 auto;border-radius:5px;"></div>
				</div>
			</div>
		</div>
	</div>
        <div class="row m_top10">
        <div class="col-md-6">
        	<!--<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                	Today Starting soon Programmes
                </div>
                <div class="panel-body ScrollDiv1">
                	<p class="m_0 text-bold">NTR PHOTO GALLERY</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger">Programme Time: 9:00 am To 5:00 pm</p>
                    <hr class="m_top10"/>
                    <p class="m_0 text-bold">BLOOD DONATION</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger">Programme Time: 9:00 am To 5:00 pm</p>
                    <hr class="m_top10"/>
                    <p class="m_0 text-bold">CHANDRABABU NAIDU SPEECH</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger">Programme Time: 9:00 am To 5:00 pm</p>
                    <hr class="m_top10"/>
                    <p class="m_0 text-bold">CHANDRABABU NAIDU SPEECH</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger m_bottom0">Programme Time: 9:00 am To 5:00 pm</p>
                </div>
            </div>-->
			<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="districtHeading">AP DISTRICT WISE</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                    <label class="onoffswitch-label" for="myonoffswitch">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body" >
                     <div id="districtTableId"></div>
                </div>
            </div>
			
        </div>
        <div class="col-md-6">
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
</section>
<footer>
	<div class="text-center">
    	All &copy; 2015 Telugu Desam Party
    </div>
</footer>
<script  src="js/eventDashboard.js" type="text/javascript"></script>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/js/highcharts.js" type="text/javascript"></script>
<script src="dist/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="dist/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/js/dataTables.responsive.js" type="text/javascript"></script>
 <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
<script type="text/javascript">


var parentEventId = 0;
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
if(i == 0)
{
str+='<li><input id="mainEvent'+result[i].id+'" name="cb11" type="radio" onclick="handalClick(this.id)" class="maineventCls" value="'+result[i].id+'" checked>';
}
else
{
str+='<li><input id="mainEvent'+result[i].id+'" name="cb11" type="radio" onclick="handalClick(this.id)" class="maineventCls" value="'+result[i].id+'">';
}
str+='<label for="cb11" class="m_0 collapse-select"><span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-controls="collapse'+result[i].id+'" class="col-drop-head ">'+result[i].name+'</a></span></label></li>';
str+=' </ul>';
str+='</form>';
str+=' <a data-toggle="collapse" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-expanded="true" aria-controls="collapse'+result[i].id+'">';
str+='<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>';
str+=' </a>';
str+=' </h5>';
str+=' </div>';
if(i == 0)
str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
else
str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
str+='<div class="panel-body collapse-body">';
str+='<form class="me-select display-style">';
str+='<ul id="me-select-list" style="list-style:none;">';
 for(var j in result[i].subList)
 {
str+='<li><input id="'+result[i].subList[j].id+'" name="cb11" type="checkbox" class="subeventCls" value="'+result[i].subList[j].id+'" ">';
str+=' <label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+result[i].subList[j].name+'</span></label></li>';
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
                    maxDate: '31/12/2015',
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
				
               });

	function handalClick(id)
	{
				
				$(".maineventCls").prop('checked', false);
				$(".subeventCls").prop('checked', false);
				$("#"+id).prop('checked', true);
	}
				
/*var myVar=setInterval(function(){myTimer()},1000);
function myTimer() {
    var d = new Date();
    document.getElementById("time").innerHTML = d.toLocaleTimeString();
}*/

/*function checkParent(id)
{

$(".maineventCls").prop('checked', false);
$("#"+id).prop('checked', true);
$(".maineventCls").each(function(){

	if($(this).is(":checked") ==  false)
	{
	alert('aa');
		$(".subeventCls").each(function(){
			if($(this).is(":checked")){
			alert('bb');
				$(this).prop('checked', false);
				}
				else{
				$(this).prop('checked', true);
				}
		})
	}
	
});


}
*/

function eventUpdate()
{
$("#errorDiv").html("");
     subEvents = [];
	 var flag = true;
	 var errStr ='';
	 
	$(".maineventCls").each(function(){
    
	if($(this).is(":checked"))
	{
		flag = false;
		parentEventId = $(this).val();
		$(".subeventCls").each(function(){
		if($(this).is(":checked"))
		subEvents.push($(this).val());
		})
	}

});
	if(subEvents.length == 0)
	errStr +='<br/>Select atleast one event';
	
	$("#errormsg").html(errStr);
startDate = $(".dp_startDate").val();
endDate = $(".dp_endDate").val();
alert(errStr.length)
if(errStr.length == 0)
{
setcolorsForEvents();
getLocationWiseVisitorsCount(parentEventId,1,3);
getLocationWiseVisitorsCount(parentEventId,1,4);
getSubEventDetails(parentEventId);
getSubEventDetailsHourWise(parentEventId);
getEventMemberCount(parentEventId);
}

}


function getLocationWiseVisitorsCount(eventId,stateId,reportLevelId)
{
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
	var str='';
	str+='<div class="scrollDiv"><table  class="display" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>DISTRICT</th>';
	}else{
	str+='<th>CONSTITUENCY</th>';
	}
	str+='<th>VOTERS</th>';
    str+='<th>CADRES</th>';
	str+='<th>INVITEES</th>';
	str+='<th>NON INVITEES</th>';
    str+='</tr></thead>';
    str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].voterCount+'</td>';
		str+='<td>'+result[i].cadreCount+'</td>';
		str+='<td>'+result[i].invitees+'</td>';
		str+='<td>'+result[i].nonInvitees+'</td>';
		str+='</tr>';
    }                               
	str+='</tbody></table></div>';
	if(reportLevelId == 3)
	$("#districtTableId").html(str);
	else
	$("#constiTableId").html(str);
	$('#table'+reportLevelId).DataTable( {
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "30%", "targets": 0 }]
    } );
	
	$('.scrollDiv').slimScroll({
	height: '350px'
	});
}
$("#myonoffswitch").click(function(){
	if($('#myonoffswitch').is(":checked")){
	getLocationWiseVisitorsCount(1,1,3);
	$("#districtHeading").html("AP DISTRICT WISE");
	}else{
	getLocationWiseVisitorsCount(1,36,3);
	$("#districtHeading").html("TS DISTRICT WISE");
	}
});
$("#myonoffswitch1").click(function(){
	if($('#myonoffswitch1').is(":checked")){
	getLocationWiseVisitorsCount(1,1,4);
	$("#constiHeading").html("AP CONSTITUENCY WISE");
	}else{
	getLocationWiseVisitorsCount(1,36,4);
	$("#constiHeading").html("TS CONSTITUENCY WISE");
	}
});



function getSubEventDetails(parentEventId){

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
		buildStartingPrograms(result);
	});
}
function buildStartingPrograms(result){
    var str='';
	var total = 0;
	for(var i in result)
       {        
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
		colorsarr.push(color);
		str+=' <span class="pull-left">'+result[i].name+'</span>';
		var count = result[i].invitees + result[i].nonInvitees;
		str+='  <span class="pull-right label-custom">'+count+'</span>';
		str+=' <br/>';
		str+=' <hr class="m_top10"/>';
		
		 var arr = [];
		arr.push(result[i].name,count);
		dataArr.push(arr);
	}
	str+=' </div>';
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

function insertIntermediateData()
{
	$.ajax({
          type:'POST',
          url: 'insertDataintoEventInfoAction.action',
          dataType: 'json',
          data: {},

          success: function(result){ 
			 location.reload(); 
         },
          error:function() { 
           console.log('error', arguments);
         }
    });

}
var areaChartDataArr  = [];
var areaChartNamesArr =[];
function getSubEventDetailsHourWise(parentEventId){
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
				areaChartDataArr = new Array();
				areaChartNamesArr = new Array();
				var colorsarr = new Array();
				
				for(var i in result)
				{	
				var color = getColorCodeByEvent(result[i].id);	
					var areaChartDataArrInner = new Array();
					for(var j in result[i].subList){	
					    areaChartNamesArr.push(result[i].subList[j].name);					
						areaChartDataArrInner.push(result[i].subList[j].cadreCount);
					}
					var obj1={
						name:result[i].name,
						data:areaChartDataArrInner
					}
					colorsarr.push(color);
					areaChartDataArr.push(obj1);
					
				}
		console.log(areaChartNamesArr);
		buildHourWiseChart(colorsarr);
		});
}

function buildHourWiseChart(colorsarr){
console.log(areaChartDataArr);
if(areaChartDataArr == null || areaChartDataArr.length == 0)
{
$('#hourWiseContainer').html("No Data Available").addClass("errorDiv");
return;
}
else
$('#hourWiseContainer').removeClass("errorDiv");
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
		buildEventMemberCount(result);
	});
}
/* DONUT CHART */
function buildEventMemberCount(result){
if(result == null || result.length == 0)
{
$('#columnchart').html("No Data Available").addClass("errorDiv");
return;
}
else
$('#columnchart').removeClass("errorDiv");
var xaxis = [];
var dataArr = [];
for(var i in result)
{
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

console.log(dataArr)


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
                enabled: true,
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
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '<br/>' +
                    'Total: ' + this.point.stackTotal;
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

</script>
</body>
​<style>
    header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
</style>
<script>
getEvents();
$(".tbtn").click(function(){
    $(".themeControll").toggleClass("active");
});
</script>
</html>
