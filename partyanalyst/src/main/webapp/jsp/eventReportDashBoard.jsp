<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Event Demographic Analysis</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
<link href="dist/eventDashboard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/custom1.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/eventDashboard/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<style>
.tableC thead th {color:#00B17D !important;font-size: 11px !important;}
.tableG thead th {color:#00B17D !important;font-size: 11px !important;}
.tableC tr td{font-size:12px !important;padding:4px !important;}
.tableC tr:nth-child(odd) td
{
	background:#F1F6F9 !important
}
.tableC tr:nth-child(even) td
{
	background:#EFF3F4 !important
}
.label-custom a {
    color: #fff !important;
    text-decoration: none;
}
.prev, .next {width:45px !important}
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
.col-md-offset-6-manually{
	margin-left: 53%;
}
</style>
</head>
<body>
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
			<div class="col-md-7">
				<div class="refreshButton">
					<span  class="text">Last Updated Time <br/><span id="timeUpdationId"></span>&nbsp;&nbsp;&nbsp;</span>
					<a title=" Page Refresh" class="refreshIcon" style="cursor:pointer">
						<span class="glyphicon glyphicon-refresh"></span>
					</a>
				</div>
			</div>
			<div class="col-md-2">
				<select style="height:32px;display:inline-block;width:150px;" class="form-control" id="mainEventSelectId">
					<option value="0">Select Event</option>
					<option value="7">Mahanadu 2015</option>
					<option value="30" selected>Mahanadu 2016</option>
				</select>
			</div>
		</div>
	</div>
    </div>
		<div class="row m_top10">
			<div class="col-md-12">
				<div class="panel panel-default panel-custom-default m_0">
					<div class="panel-body" style="background-color:#f2f2f2">
						<div class="row m_top6">
							<div class="col-md-12 m_top20">
							   <div class="col-md-12">
								<div class="panel panel-default m_0">
									<div class="panel-heading">
									<p class="m_0 display-style" id="casteWiseHeadingId">Sub Caste Wise Analysis</p>
								    <button class="btn btn-success btn btn-xs col-md-offset-7" id="casteExcelExpBtnId" onclick="generateExcelReportForCaste()" style="display:none;">Export Excel</button>
									</div>
									<div class="panel-body" style="padding:0px;">				
											<center><img id="cstWstblPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
										<div id="casteWiseTableId"> </div>
									</div>
								</div>
							   </div>
							</div>
						</div>
						<div class="row m_top6">
							<div class="col-md-12 m_top20">
							   <div class="col-md-12">
								<div class="panel panel-default m_0">
									<div class="panel-heading">
									<p class="m_0 display-style" id="casteWiseHeadingId">Age Wise Analysis </p>
								    <button class="btn btn-success btn btn-xs col-md-offset-7" id="ageWsExcelExpBtnId" onclick="generateExcelReportForAge()" style="display:none;">Export Excel</button>
									</div>
									<div class="panel-body" style="padding:0px;">				
										<center><img id="ageWstblPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
										<div id="ageWiseTableId"> </div>
									</div>
								</div>
							   </div>
							</div>
						</div>
					   <div class="col-md-12 m_top20">
						  <div class="panel panel-default m_0">
							<div class="panel-heading">
							<p class="m_0 display-style" id="genderWiseHeadingId">Gender Wise Analysis</p>
							<button class="btn btn-success btn btn-xs col-md-offset-7" id="genderWiseExcelExpBtnId" onclick="generateExcelReportForGender()" style="display:none;">Export Excel</button>
							</div>
							<div class="panel-body" style="padding:0px;">				
								<center><img id="genderWisePrcssngImgId" src="images/Loading-data.gif" style="display:none;width:65px;height:60px;"/></center>
								<div id="genderWiseTableId"> </div>
							</div>
						 </div>
					   </div>   
                      <div id="casteWiseExportToExcelTableId" style="display:none;"></div>					   
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
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
//var parentEventId = '${eventId}';
 var parentEventId;
 var subEvents = [];
	var startDate;
	var endDate;
 
   $(document).on("change","#mainEventSelectId",function(){
	   parentEventId = $("#mainEventSelectId").val();
       if(parentEventId != null && parentEventId !=0){
	   subEvents=[];
	   $("#casteExcelExpBtnId").hide();
	   $("#genderWiseExcelExpBtnId").hide();
	   $("#ageWsExcelExpBtnId").hide();
	    getEventDateAndSubEvent();
	   }	   
   });
	parentEventId = $("#mainEventSelectId").val();		
	
	getEventDateAndSubEvent();
	function getEventDateAndSubEvent()
	{ 
	var jObj = {
	eventId : parentEventId
	}
	$.ajax({
			  type:'GET',
			  url: 'getEventDateAndSubEventAction.action',
			  data : {task:JSON.stringify(jObj)} 
			}).done(function(result){
			  if(result!=null){	 
				  updateDatesButton(result.eventStartDate,result.eventEndDate,result.eventStartDate,result.eventEndDate)
				  var subEventList=result.subList;
				   if(subEventList !=null && subEventList.length >0){
					   for(var i in subEventList){
						 subEvents.push(subEventList[i].id);
					   }
				   }
			  }
			  allCalls();
		});
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
			   });
			   
 function updateDatesButton(startDate,endDate,formatedStartDate,formatedEndDate){
	
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
</script>
<script type="text/javascript">
 $(document).on('click','.applyBtn',function(){
	allCalls();
 });
 $(document).on("click",".refreshIcon",function(){
	allCalls();
 }); 
function allCalls(){
    var startDate = $(".dp_startDate").val();
    var endDate = $(".dp_endDate").val();
	 casteCategoryWiseEventAttendeeCounts(startDate,endDate);
     getCasteWiseEventAttendeeCounts(startDate,endDate);
	 getAgeWiseEventAttendeeCounts(startDate,endDate);
	 getGenderWiseEventAttendeeCounts(startDate,endDate);
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
function getCasteWiseEventAttendeeCounts(startDate,endDate)
	{	
        	$("#casteWiseTableId").html(' ');
			$("#casteWiseExportToExcelTableId").html(' ');
	       $("#cstWstblPrcssngImgId").show();
	         var jsObj = {
					startDate    :startDate,
					endDate      :endDate,
					parentEventId:parentEventId,
					subEvents : subEvents
				}
			$.ajax({
	          type:'GET',
	          url: 'getCasteWiseEventAttendeeCountsAction.action',
			  data : {task:JSON.stringify(jsObj)} ,
	        }).done(function(result){
				$("#cstWstblPrcssngImgId").hide();
				if(result != null){
				 buildCasteWiseRslt(result,"");
				 buildCasteWiseRslt(result,"exportToExcel");
				}
		});
	}
function buildCasteWiseRslt(result,status){
	
	$('#timeUpdationId').html(result[0].lastUpdatedDate);
	var str='';
	str+='<div >';
	if(result[0].locationName != "NO DATA"){
		  if(status!=null && status.length>0 && status=="exportToExcel"){
			str+='<table class="table tableC table-condensed table-bordered " style="border-bottom:none" id="exportToExcelTblId">';
			}else{
			str+='<table class="table tableC table-condensed table-bordered " style="border-bottom:none" id="casteDatatblId" >';
			}
			str+='<thead style="background:#EFF3F4">';
			str+='<tr>';
			str+='<th rowspan="2" style="vertical-align:middle">CASTE NAME</th>';
			str+='<th rowspan="2" style="vertical-align:middle">TOTAL CADRES</th>';
			str+='<th rowspan="2" style="vertical-align:middle">TOTAL INVITED</th>';
			str+='<th rowspan="2" style="vertical-align:middle">TOTAL ATTENDED</th>';
			str+='<th rowspan="2" style="vertical-align:middle">TOTAL ATTENDED % </th>';
			str+='<th rowspan="2" style="vertical-align:middle">INVITEES ATTENDED</th>';
			str+='<th rowspan="2" style="vertical-align:middle">INVITEES ATTENDED %</th>';
			str+='<th rowspan="2" style="vertical-align:middle">NON INVITEES ATTENDED</th>';
			str+='<th rowspan="2" style="vertical-align:middle">NON INVITEES ATTENDED %</th>';
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
			if(result[j].name == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td >'+result[j].name+'</td>';
			}
			if(result[j].totalCadre !=null && result[j].totalCadre >0){
				str+='<td class="text-center">'+result[j].totalCadre+'</td>';
			}else{
			  str+='<td class="text-center"> 0 </td>';
			}
			if(result[j].inviteesCalled == 0 || result[j].inviteesCalled == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].inviteesCalled+'</td>';
			}
			if(result[j].attendees == 0 || result[j].attendees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].attendees+'</td>';
			}
			if(result[j].attendeePercantage == 0 || result[j].attendeePercantage == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].attendeePercantage+' %</td>';
			} 
			if(result[j].invitees == 0 || result[j].invitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].invitees+'</td>';
			}
			if(result[j].inviteePercantage == 0 || result[j].inviteePercantage == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].inviteePercantage+' %</td>';
			} 
			if(result[j].nonInvitees == 0 || result[j].nonInvitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].nonInvitees+'</span></td>';
			}
			if(result[j].nonInviteePercantage == 0 || result[j].nonInviteePercantage == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].nonInviteePercantage+' %</td>';
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
	if(status!=null && status.length>0 && status=="exportToExcel"){
		$("#casteWiseExportToExcelTableId").html(str);
	}else{
		$("#casteWiseTableId").html(str);
		$("#casteExcelExpBtnId").show();
	}
	}else{
		$("#casteWiseTableId").html("<p style='margin-top: 30px; text-align: center;'>NO DATA AVAILABLE</p>");
	}
	if(status!=null && status.length>0 && status =="exportToExcel"){}else{
	   	$('#casteDatatblId').DataTable({
        "paging":   true,
        "info":     false,
		"searching": true,
		"autoWidth": true,
		"order": [ 4, 'desc' ],
		"aLengthMenu": [[10,20,30,50, 100, -1], [10,20,30,50, 100, "All"]]
    });	
   }
}
function generateExcelReportForCaste(){
	tableToExcel(exportToExcelTblId, 'Caste Wise Report');
}
function getAgeWiseEventAttendeeCounts(startDate,endDate){
	
	$("#ageWiseTableId").html("");  
	$("#ageWstblPrcssngImgId").show();
    var jsObj = {
		startDate    :startDate,
		endDate      :endDate,
		parentEventId:parentEventId,
		subEvents : subEvents
	}
	$.ajax({
        type:'GET',
        url: 'getAgeWiseEventAttendeeCountsAction.action',
		data : {task:JSON.stringify(jsObj)} ,
        }).done(function(result){ 
		$("#ageWstblPrcssngImgId").hide();
			if(result != null)
			{				
				buildAgeWiseCadreCountTable(result)	
			}
	});
}
function buildAgeWiseCadreCountTable(result){
	
	var str='';
	str+='<div >';
	if(result[0].locationName != "NO DATA"){
		str+='<table class="table tableC table-condensed table-bordered " style="border-bottom:none" id="ageDatatblId" >';
		str+='<thead style="background:#EFF3F4">';
		str+='<tr>';
		str+='<th rowspan="2" style="vertical-align:middle">AGE RANGE</th>';
		str+='<th rowspan="2" style="vertical-align:middle">TOTAL CADRES</th>';
		str+='<th rowspan="2" style="vertical-align:middle">TOTAL INVITED</th>';
		str+='<th rowspan="2" style="vertical-align:middle">TOTAL ATTENDED</th>';
		str+='<th rowspan="2" style="vertical-align:middle">TOTAL ATTENDED %</th>';
		str+='<th rowspan="2" style="vertical-align:middle">INVITEES ATTENDED</th>';
		str+='<th rowspan="2" style="vertical-align:middle">INVITEES ATTENDED %</th>';
		str+='<th rowspan="2" style="vertical-align:middle">NON INVITEES ATTENDED</th>';
		str+='<th rowspan="2" style="vertical-align:middle">NON INVITEES ATTENDED %</th>';
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
			if(result[j].name == null){
				str+='<td class="text-center"> - </td>';
			}else{
				if(result[j].name == 'Young Voters'){
					str+='<td >18-22('+result[j].name+')</td>';
				}else{
					str+='<td >'+result[j].name+'</td>';
				}
			}
			if(result[j].totalCadre !=null && result[j].totalCadre >0){
				str+='<td class="text-center">'+result[j].totalCadre+'</td>';
			}else{
				str+='<td class="text-center"> 0 </td>';
			}
			if(result[j].inviteesCalled == 0 || result[j].inviteesCalled == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].inviteesCalled+'</td>';
			}
			if(result[j].attendees == 0 || result[j].attendees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].attendees+'</td>';
			}
			if(result[j].attendeePercantage == 0 || result[j].attendeePercantage == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].attendeePercantage+' %</td>';
			} 
			if(result[j].invitees == 0 || result[j].invitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].invitees+'</td>';
			}
			if(result[j].inviteePercantage == 0 || result[j].inviteePercantage == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].inviteePercantage+' %</td>';
			} 
			if(result[j].nonInvitees == 0 || result[j].nonInvitees == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].nonInvitees+'</td>';
			}
			if(result[j].nonInviteePercantage == 0 || result[j].nonInviteePercantage == null){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+result[j].nonInviteePercantage+' %</td>';
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
		$("#ageWiseTableId").html(str);
		$("#ageWsExcelExpBtnId").show();
		$('#ageDatatblId').DataTable({
        "paging":   false,
        "info":     false,
		"searching": false,
		"autoWidth": true,
		"order": [ 4, 'desc' ]
    });
	}else{
		$("#ageWiseTableId").html("<p style='margin-top: 30px; text-align: center;'>NO DATA AVAILABLE</p>");
	}
}
function generateExcelReportForAge(){
	tableToExcel(ageDatatblId, 'Age Wise Report');
}
function getGenderWiseEventAttendeeCounts(startDate,endDate)
{			
	 $("#genderWiseTableId").html(' ');
	 $("#genderWisePrcssngImgId").show();
         var jsObj = {
				startDate    :startDate,
				endDate      :endDate,
				parentEventId      :parentEventId,
				subEvents : subEvents
			}
		$.ajax({
          type:'GET',
          url: 'getGenderWiseEventAttendeeCountsAction.action',
		  data : {task:JSON.stringify(jsObj)} ,
        }).done(function(result){
        	 $("#genderWisePrcssngImgId").hide();
			if(result != null)
			{				
				buildGenderWiseLocationDetails(result);	
			}
	});
}

function buildGenderWiseLocationDetails(result) {
	 var str='';
	  str+='<div class="table-responsive">';
		str+='<table class="table tableG table-condensed table-bordered" id="gndrWsExprtTExclTblId">';
			str+='<thead>';
				str+='<tr>';
				    str+='<th rowspan="3">TOTAL MALE CADRE</th>';
					str+='<th rowspan="3">TOTAL FEMALE CADRE</th>';
					str+='<th rowspan="3">TOTAL MALE INVITED</th>';
					str+='<th rowspan="3">TOTAL FEMALE INVITED</th>';
					str+='<th colspan="2" rowspan="2">TOTAL ATTENDED</th>';
					str+='<th rowspan="2" colspan="2">INVITEES ATTENDED</th>';
					str+='<th colspan="2" rowspan="2">NON-INVITEES ATTENDED</th>';
			
			var daysList=result.subMap;
				    if(daysList !=null){
					   for(var i in daysList){
						str+='<th colspan="6" class="text-center"> '+daysList[i].name+' ATTENDED</th>';
						
					    }
					 }
				str+='</tr>';
				str+='<tr>';
				 	if(daysList !=null){
					  for(var i in daysList){
					 str+='<th colspan="2">Total </th>';
					 str+='<th colspan="2">Invitees </th>';
					 str+='<th colspan="2">Non Invitees</th>';
					  }
					}
				str+='</tr>';
				str+='<tr>';
					str+='<th>M</th>';
					str+='<th>F</th>';
					str+='<th>M</th>';
					str+='<th>F</th>';
					str+='<th>M</th>';
					str+='<th>F</th>';
					 if(daysList !=null){
					   for(var i in daysList){
					    str+='<th>M</th>';
						str+='<th>F</th>';
						str+='<th>M</th>';
						str+='<th>F</th>';
						str+='<th>M</th>';
						str+='<th>F</th>';
					  }
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			str+='<tr>';
			 	if(result.totalMaleCadre !=null && result.totalMaleCadre>0){
					str+='<td>'+result.totalMaleCadre+'</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.totalFemaleCadre !=null && result.totalFemaleCadre>0) {
					str+='<td>'+result.totalFemaleCadre+'</td>';
				}else{
					str+='<td> - </td>';
				}
				if(result.maleInviteesCalled !=null && result.maleInviteesCalled>0){
					str+='<td>'+result.maleInviteesCalled+'</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.femaleInviteesCalled !=null && result.femaleInviteesCalled>0) {
					str+='<td>'+result.femaleInviteesCalled+'</td>';
				}else{
					str+='<td> - </td>';
				}
				if(result.maleAttendees !=null && result.maleAttendees>0) {
					str+='<td>'+result.maleAttendees+' <span>('+result.maleAttendeePercantage+'%)</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.femaleAttendees != null && result.femaleAttendees>0){
					str+='<td>'+result.femaleAttendees+' <span>('+result.femaleAttendeePercantage+'%)</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.maleInvitees != null && result.maleInvitees>0) {
					str+='<td>'+result.maleInvitees+' <span>('+result.maleInviteePercantage+'%)</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.femaleInvitees != null && result.femaleInvitees>0){
					str+='<td>'+result.femaleInvitees+' <span>('+result.femaleInviteePercantage+'%)</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.maleNonInvitees != null && result.maleNonInvitees>0){
					str+='<td>'+result.maleNonInvitees+' <span>('+result.maleNonInviteePercantage+'%)</td>';
				}else {
					str+='<td> - </td>';
				}
				if(result.femaleNonInvitees !=null && result.femaleNonInvitees>0) {
					
				str+='<td>'+result.femaleNonInvitees+' <span>('+result.femaleNonInviteePercantage+'%)</td>';
				}else {
					str+='<td> - </td>';
				}
			   var daysList=result.subMap;
			   if(daysList !=null){
				   for(var i in daysList){
					if(daysList[i].maleAttendees != null && daysList[i].maleAttendees>0){
						str+='<td>'+daysList[i].maleAttendees+' </td>';
					}else{
					str+='<td> 0 </td>';
					}
					if(daysList[i].femaleAttendees != null && daysList[i].femaleAttendees>0){
						str+='<td>'+daysList[i].femaleAttendees+' </td>';
					}else{
					str+='<td> 0 </td>';
					}
			       if(daysList[i].maleInvitees != null && daysList[i].maleInvitees>0){
						str+='<td>'+daysList[i].maleInvitees+' </td>';
					}else{
					str+='<td> 0 </td>';
					}
					if(daysList[i].femaleInvitees != null && daysList[i].femaleInvitees>0){
						str+='<td>'+daysList[i].femaleInvitees+' </td>';
					}else{
					str+='<td> 0 </td>';
					}
					if(daysList[i].maleNonInvitees != null && daysList[i].maleNonInvitees>0){
						str+='<td>'+daysList[i].maleNonInvitees+' </td>';
					}else{
					str+='<td> 0 </td>';
					}
					if(daysList[i].femaleNonInvitees != null && daysList[i].femaleNonInvitees>0){
						str+='<td>'+daysList[i].femaleNonInvitees+' </td>';
					}else{
					str+='<td> 0 </td>';
					}
				   }
			   }
		 	str+='</tr>';
			str+='</tbody>'
		str+='</table>';
	  str+='</div>';
	$("#genderWiseTableId").html(str);
	$("#genderWiseExcelExpBtnId").show();
}
function generateExcelReportForGender(){
	tableToExcel(gndrWsExprtTExclTblId, 'Gender Wise Report');
}
function casteCategoryWiseEventAttendeeCounts(startDate,endDate){
	     
		 var jsObj = {
				startDate    :startDate,
				endDate      :endDate,
				parentEventId:parentEventId,
				subEvents : subEvents
			}
		$.ajax({
          type:'GET',
          url: 'casteCategoryWiseEventAttendeeCountsAction.action',
		  data : {task:JSON.stringify(jsObj)} ,
        }).done(function(result){
 	});
}
</script>
</body>
</html>
