<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAHANADU VISITORS INFO</title>

 <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	 <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
	 
<script src="dist/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="dist/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/js/dataTables.responsive.js" type="text/javascript"></script>
	 
<link href="dist/eventDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<link href="dist/MultiSelect/css/component.css" rel="stylesheet" type="text/css">
<script src="dist/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>
<script src="dist/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/Date/daterangepicker.js" type="text/javascript"></script>
<style>
	header.eventsheader {
   
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;

    background-origin: border-box;
    background-repeat: no-repeat;
 
    height: 71px;
	
}
</style>
</head>
<body>
<section class="container">
	<div class="well well-sm" style="background:#C0C0C0">
		<div class="row">
		  <div class="col-md-4" style="margin-top:5px;">
			 <h4 class="m_0 text-capitalise">mahanadu visitors info dashboard</h4>
		  </div>
		  <div class="col-md-4 col-md-offset-2">
			<div id="mahanaduEventDashBoardLinkId" style="display:none">
			  <button id="mahanaduLinkId" type="button" class="btn btn-primary pull-right">ENTRY/EXIT DASHBOARD</button>
			</div>
			<div class="refreshButton">
			  <span  class="text">Last Updated Time <br/><span id="timeUpdationId"></span>&nbsp;&nbsp;&nbsp;</span>
			  <a onclick="" title=" Page Refresh" class="refreshIcon" style="cursor:pointer">
				<span class="glyphicon glyphicon-refresh"></span>
			  </a>
			</div>
			 
			<!--<a  class="btn btn-xs btn-success btn-block dataSynchClass" title=" Data Synch..">
			  <span class="" style="font-size: 15px;"> Sync
				<img src="images/ajaxImg2.gif" id="syncAjaxImage1" style="height:20px;width:20px;display:none;"/>
			  </span>
			 </a>-->
			</div>
			<div class="col-md-2">
				<select class="form-control" id="mainEventSelectId">
					<option value="0">Select Event</option>
					<option value="7" >Mahanadu 2015</option>
					<option value="30" selected>Mahanadu 2016</option>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body">
					<h4 class="m_0">TOTAL VISITORS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="totalVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body">
					<h4 class="m_0">INVITEES AS VISITORS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="inviteesAsVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body">
					<h4 class="m_0">NOT INVITEES AS VISITORS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="nonInviteesAsVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body">
					<h4 class="m_0">VISITORS PRESENT IN CAMPUS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="visitorsPresentInCampusId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body">
					<h4 class="m_0">INVITEES PRESENT IN  VISITORS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="inviteesPresentInVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body">
					<h4 class="m_0">NON INVITEES PRESENT IN  VISITORS</h4>
					<h3 id="nonInviteesPresentInVisitorsId">0</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">HOUR WISE VISITORS</h4>
					<p class="font-10 fontColor">Last updated On: 29-05-2016 03:00 AM</p>
				</div>
				<div class="panel-body pad_0" style="height:440px">
				 <div id="visitorsTableId"></div>
					<!--<table class="table table-condensed tableHourWise">
						<thead>
							<th>TIME STATUS</th>
							<th>DAY - 1</th>
							<th>DAY - 2</th>
							<th>DAY - 3</th>
						</thead>
						<tbody>
							<tr>
								<td>Above 8 Hours</td>
								<td>6461</td>
								<td>1948</td>
								<td>0</td>
							</tr>
						</tbody>
					</table>-->
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">HOUR WISE VISITORS</h4>
					<p class="font-10 fontColor">Last updated On: 29-05-2016 03:00 AM</p>
				</div>
				<div class="panel-body">
					<div id="hoursWiseVisitors" style="height:400px;width:100%;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">DAY WISE UNIQUE AND REVISIT SUMMARY</h4>
				</div>
				<div class="panel-body pad_8" style="height:167px">
				 <div id="daysSummaryUniqueTableId"></div>
					<!--<table class="table tableDayWiseRevisit table-condensed">
						<tr>
							<td><span class="countNo">1</span></td>
							<td>Total Unique <br/>6734</td>
							<td>Only 1st Day<br/>6265</td>
							<td>Revisits<br/>-</td>
						</tr>
						<tr>
							<td><span class="countNo">1</span></td>
							<td>Total Unique <br/>6734</td>
							<td>Only 1st Day<br/>6265</td>
							<td>Revisits<br/>-</td>
						</tr>
						<tr>
							<td><span class="countNo">1</span></td>
							<td>Total Unique <br/>6734</td>
							<td>Only 1st Day<br/>6265</td>
							<td>Revisits<br/>-</td>
						</tr>
					</table>-->
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">DAY WISE VISIT SUMMARY</h4>
				</div>
				<div class="panel-body">
				<div id="totalVisitorsDtlsId"></div>
					<!--<p class="m_0">1 DAY VISITORS<span class="pull-right" id="frstDyVstrsId">100%</span></p>
					<div class="progress progressNewCustom">
					  <div class="progress-bar progress-bar-info" id="progressBarInfoId" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
						<span class="sr-only" id="prgrssBrVstrsInfoId">80% Complete</span>
					  </div>
					</div>
					<p class="m_0">2 DAY'S VISITORS<span class="pull-right" id="scndDyVstrsId">60%</span></p>
					<div class="progress progressNewCustom">
					  <div class="progress-bar progress-bar-success " id="progressBarSuccessId" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						<span class="sr-only" id="prgrssBrVstrsSccssId">60% Complete</span>
					  </div>
					</div>
					<p class="m_0">3 DAY'S VISITORS<span class="pull-right" id="thrdDyVstrsId">60%</span></p>
					<div class="progress progressNewCustom">
					  <div class="progress-bar progress-bar-warning" role="progressbar" id="progressBarWarningId" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						<span class="sr-only" id="prgrssBrVstrsWrnngId">60% Complete</span>
					  </div>
					</div>-->
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-8">
							<h4 class="panel-title">DISTRICT WISE MEMBERS IN CAMPUS NOW & DAY WISE COUNT</h4>
						</div>
						<div class="col-md-4">
							<div class="tsSwitch">
								<input type="checkbox" name="tsSwitch" class="tsSwitch-checkbox" id="tsSwitch" checked>
								<label class="tsSwitch-label" for="tsSwitch">
									<span class="tsSwitch-inner"></span>
									<span class="tsSwitch-switch"></span>
								</label>
							</div>
							<div class="otherStates">
								<input type="checkbox" name="otherStates" class="otherStates-checkbox" id="otherStates" checked>
								<label class="otherStates-label" for="otherStates">
									<span class="otherStates-inner"></span>
									<span class="otherStates-switch"></span>
								</label>
							</div>
							<div class="apSwitch">
								<input type="checkbox" name="apSwitch" class="apSwitch-checkbox" id="apSwitch" checked>
								<label class="apSwitch-label" for="apSwitch">
									<span class="apSwitch-inner"></span>
									<span class="apSwitch-switch"></span>
								</label>
							</div>
						</div>
					</div>
					
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed tableNewDistWise">
							<thead>
								<tr>
									<th>DISTRICT NAME</th>
									<th>NOW IN CAMPUS</th>
									<th>DAY - 1</th>
									<th>DAY - 2</th>
									<th>DAY - 3</th>
								</tr>
							</thead>
							<tr>
								<td>Ananthapur</td>
								<td>500</td>
								<td>100</td>
								<td>100</td>
							</tr>
							<tr>
								<td>Ananthapur</td>
								<td>500</td>
								<td>100</td>
								<td>100</td>
							</tr>
							<tr>
								<td>Ananthapur</td>
								<!--<td>300<div style="display:inline-block" data-sparkline="71, 78, 39, 66 "></div></td>-->
								<td>500</td>
								<td>100</td>
								<td>100</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</section>
<script src="https://code.highcharts.com/highcharts.js"></script>
	
<script type="text/javascript">
 
 function buildVisitorsDtlsGraph(result){
	  var finalDataArr=[];
	  var daysArr=["Day - 1","Day - 2","Day - 3"];
	  for(var i in result){
		   var jsonObj={name:daysArr[i],data:[result[i].above8hrs,result[i].seventoeight,result[i].sixtoseven,result[i].fivetosix,result[i].fourtofive,result[i].threetofour,result[i].twotothree,result[i].onetotwo,result[i].halfanhour,result[i].belowhalfanhour]};
		   finalDataArr.push(jsonObj);
	  }
	  buildHighChart(finalDataArr);
  }
  	function buildHighChart(finalDataArr){
			$('#hoursWiseVisitors').highcharts({
				chart: {
					type: 'area'
				},
				title: {
					text: ' '
				},
				subtitle: {
					text: ' '
				},
				xAxis: {
					categories: ['Above 8 Hours', '7 to 8 Hours', '6 to 7 Hours', '5 to 6 Hours', '4 to 5 Hours', '3 to 4 Hours', '2 to 3 Hours','1 to 2 Hours','1/2 to 1 Hours','< 1/2 Hour'],
					tickmarkPlacement: 'on',
					title: {
						enabled: false
					}
				},
				yAxis: {
					title: {
						text: ''
					},
					labels: {
						formatter: function () {
							return ((this.value / 1000)+" K");
						}
					}
				},
				tooltip: {
					shared: true,
					valueSuffix: ' '
				},
				plotOptions: {
					area: {
						stacking: 'normal',
						lineColor: '#666666',
						lineWidth: 1,
						marker: {
							lineWidth: 1,
							lineColor: '#666666'
						}
					}
				},
				series:finalDataArr
			});
		}
		
	/* $(function () {
		/**
		 * Create a constructor for sparklines that takes some sensible defaults and merges in the individual
		 * chart options. This function is also available from the jQuery plugin as $(element).highcharts('SparkLine').
		 */
		/*Highcharts.SparkLine = function (a, b, c) {
			var hasRenderToArg = typeof a === 'string' || a.nodeName,
				options = arguments[hasRenderToArg ? 1 : 0],
				defaultOptions = {
					chart: {
						renderTo: (options.chart && options.chart.renderTo) || this,
						backgroundColor: null,
						borderWidth: 0,
						type: 'area',
						margin: [2, 0, 2, 0],
						width: 120,
						height: 20,
						style: {
							overflow: 'visible'
						},
						skipClone: true
					},
					title: {
						text: ''
					},
					credits: {
						enabled: false
					},
					xAxis: {
						labels: {
							enabled: false
						},
						title: {
							text: null
						},
						startOnTick: false,
						endOnTick: false,
						tickPositions: []
					},
					yAxis: {
						endOnTick: false,
						startOnTick: false,
						labels: {
							enabled: false
						},
						title: {
							text: null
						},
						tickPositions: [0]
					},
					legend: {
						enabled: false
					},
					tooltip: {
						backgroundColor: null,
						borderWidth: 0,
						shadow: false,
						useHTML: true,
						hideDelay: 0,
						shared: true,
						padding: 0,
						positioner: function (w, h, point) {
							return { x: point.plotX - w / 2, y: point.plotY - h };
						}
					},
					plotOptions: {
						series: {
							animation: false,
							lineWidth: 1,
							shadow: false,
							states: {
								hover: {
									lineWidth: 1
								}
							},
							marker: {
								radius: 1,
								states: {
									hover: {
										radius: 2
									}
								}
							},
							fillOpacity: 0.25
						},
						column: {
							negativeColor: '#910000',
							borderColor: 'silver'
						}
					}
				};

			options = Highcharts.merge(defaultOptions, options);

			return hasRenderToArg ?
				new Highcharts.Chart(a, options, c) :
				new Highcharts.Chart(options, b);
		};

		var start = +new Date(),
			$tds = $('div[data-sparkline]'),
			fullLen = $tds.length,
			n = 0;

		// Creating 153 sparkline charts is quite fast in modern browsers, but IE8 and mobile
		// can take some seconds, so we split the input into chunks and apply them in timeouts
		// in order avoid locking up the browser process and allow interaction.
		function doChunk() {
			var time = +new Date(),
				i,
				len = $tds.length,
				$td,
				stringdata,
				arr,
				data,
				chart;

			for (i = 0; i < len; i += 1) {
				$td = $($tds[i]);
				stringdata = $td.data('sparkline');
				arr = stringdata.split('; ');
				data = $.map(arr[0].split(', '), parseFloat);
				chart = {};

				if (arr[1]) {
					chart.type = arr[1];
				}
				$td.highcharts('SparkLine', {
					series: [{
						data: data,
						pointStart: 1
					}],
					tooltip: {
						headerFormat: '<span style="font-size: 10px">' + $td.parent().find('th').html() + ', Q{point.x}:</span><br/>',
						pointFormat: '<b>{point.y}.000</b> USD'
					},
					chart: chart
				});

				n += 1;

				// If the process takes too much time, run a timeout to allow interaction with the browser
				if (new Date() - time > 500) {
					$tds.splice(0, i + 1);
					setTimeout(doChunk, 0);
					break;
				}

				// Print a feedback on the performance
				if (n === fullLen) {
					$('#result').html('Generated ' + fullLen + ' sparklines in ' + (new Date() - start) + ' ms');
				}
			}
		}
		doChunk();

	}); */
	</script>
  <script type="text/javascript">
   
   $( "#mainEventSelectId" ).change(function() {
	  getmainEventsChange();
	   
   });
    function getmainEventsChange(){	 
		getSubEventsOfEvent();		
		  setTimeout(function(){
			getDaysUniqueAndRevisitSummary();	
            getTodayTotalVisitors(); 
            getDetails();		
            getDayWiseVisitSummary();	
			}, 2000);
    } 
    $(document).ready(function(){
		 setTimeout(function(){
			getDaysUniqueAndRevisitSummary();	
            getTodayTotalVisitors(); 
            getDetails();		
            getDayWiseVisitSummary();			
		}, 2000);
	});
	
	getSubEventsOfEvent();
	 var globalMainEntryId = 0 ;
	  function getSubEventsOfEvent(){
		 var eventId = $("#mainEventSelectId").val();
		  var jsObj={
			  eventId : eventId
		  }
		   $.ajax({
			  type:'GET',
			  url: 'getSubEventsOfEventAction.action',
			  data :{task:JSON.stringify(jsObj)}
          }).done(function(result){			  
			  if(result !=null && result.length>0){
				  for(var i in result){
					  if(result[i].name == "Main Entry"){
						 globalMainEntryId = result[i].id;
					  }
				  }
			  }			  
		  });
	  }
 
 function getDaysUniqueAndRevisitSummary(){

 var subEvents1 = [];
	var stateId =0;
	var eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1 = [globalMainEntryId]; // -- MAHANADU MAIN ENTRY
	 }
	 var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:0,
			subEvents : subEvents1,			
		}	
		
		$.ajax({
          type:'GET',
          url: 'getDaysUniqueAndRevisitSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result!=null && result.length>0){
				buildDaysUniqueAndRevisitSummary(result);
			}
		});
 }
  
  function buildDaysUniqueAndRevisitSummary(result){
	
	var str='';
	  str+='<table class="table tableDayWiseRevisit table-condensed">';
	  for(var i in result){
		  str+='<tr>';
		    str+='<td><span class="countNo">'+(parseInt(i)+1)+'</span></td>';
			str+='<td>Total Unique <br/>'+result[i].total+'</td>';
			str+='<td>Only 1st Day<br/>'+result[i].oneDayCount+'</td>';
			str+='<td>Revisits<br/>'+result[i].revisitCount+'</td>';
	     str+'</tr>';
	  }
	 str+='</table>';
	 $("#daysSummaryUniqueTableId").html(str);
  }
  function getTodayTotalVisitors(){
	  
	  var eventId = $("#mainEventSelectId").val();
	   var jObj = {
			eventId:eventId
		}	
		
		$.ajax({
          type:'GET',
          url: 'getTodayTotalVisitorsInfoAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result!=null){
				  $("#totalVisitorsId").html(result.totalVisitors);
				  $("#inviteesAsVisitorsId").html(result.totalInviteeVisitors);
				  $("#nonInviteesAsVisitorsId").html(result.totalNonInviteeVisitors);
				  $("#visitorsPresentInCampusId").html(result.currentVisitors);
				  $("#inviteesPresentInVisitorsId").html(result.currentInviteeVisitors);
				  $("#nonInviteesPresentInVisitorsId").html(result.currentNonInviteeVisitors);
			}
		});
  }
    function getDetails(){
    	 var  eventId = $("#mainEventSelectId").val();
		   var jsObj={
			   eventId:eventId
		   }
		  $.ajax({
			  type:'GET',
			  url: 'getTodayTotalAndCurrentUsersInfo.action',
			  data :{task:JSON.stringify(jsObj)}
          }).done(function(result){
			  if(result!=null && result.length>0){
				  buildVisitorsResultByTime(result);
				  buildVisitorsDtlsGraph(result);
			  }
	      });
	
	  var needInvitees_a = false;
	 function buildVisitorsResultByTime(result){
		 var str='';
		 str+='<table class="table table-condensed tableHourWise" cellspacing="0" width="100%">';
                            str+='<thead>';
                                str+='<tr>';
                                  str+='  <th class="back-white">TIME STATUS</th>';
								  for(var i in result){
									  var reqi = parseInt(i)+1;
									  if(needInvitees_a){
										  str+='<th colspan="3" class="text-center table-color1">DAY '+reqi+'</th>';
									  }else{
                                         str+='<th class="text-center table-color1">DAY '+reqi+'</th>';
									  }
								  }
                                str+='</tr>';
                                str+=' <tr>';
                                str+=' 	<th class="back-white"></th>';
									for(var i in result){
                                    //str+='<th class="color-black table-color2">ATTENDED</th>';
										if(needInvitees_a){
											str+='<th class="color-black table-color2">INVITEES</th>';
											str+='<th class="color-black table-color2">NON INVITEES</th>';
										}
									 }
                                str+='</tr></thead>';
                            
                            str+='<tbody>';
                                str+="<tr>";
				  str+="   <td class='back-white'>Above 8 Hours</td>";
				  for(var i in result){
					  if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].above8hrs != null){
						str+="   "+result[i].above8hrs+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  if(needInvitees_a){
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].above8hrsInv != null){
							str+="   "+result[i].above8hrsInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].above8hrsInv != null && result[i].above8hrs != null){
							str+="   "+result[i].above8hrs-result[i].above8hrsInv+"</td>";
						  }else if(result[i].above8hrs != null){
							 str+="   "+result[i].above8hrs+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  }
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>7 to 8 Hours</td>";
				  for(var i in result){
					     if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].seventoeight != null){
						str+=""+result[i].seventoeight+"</td>";
					  }else{
						  str+="0</td>";
					  }
					  
					    if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].seventoeightInv != null){
							str+="   "+result[i].seventoeightInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].seventoeightInv != null && result[i].seventoeight != null){
							str+="   "+result[i].seventoeight-result[i].seventoeightInv+"</td>";
						  }else if(result[i].seventoeight != null){
							 str+="   "+result[i].seventoeight+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						}
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>6 to 7 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].sixtoseven != null){
						str+="  "+result[i].sixtoseven+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  
					  if(needInvitees_a){  
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].sixtosevenInv != null){
							str+="   "+result[i].sixtosevenInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].sixtosevenInv != null && result[i].sixtoseven != null){
							str+="   "+result[i].sixtoseven-result[i].sixtosevenInv+"</td>";
						  }else if(result[i].sixtoseven != null){
							 str+="   "+result[i].sixtoseven+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  }
					  
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>5 to 6 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].fivetosix != null){
						str+="   "+result[i].fivetosix+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					    if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fivetosixInv != null){
							str+="   "+result[i].fivetosixInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fivetosixInv != null && result[i].fivetosix != null){
							str+="   "+result[i].fivetosix-result[i].fivetosixInv+"</td>";
						  }else if(result[i].fivetosix != null){
							 str+="   "+result[i].fivetosix+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  
						}
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>4 to 5 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
						
					  if(result[i].fourtofive != null){
						str+="  "+result[i].fourtofive+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  if(needInvitees_a){  
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fourtofiveInv != null){
							str+="   "+result[i].fourtofiveInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fourtofiveInv != null && result[i].fourtofive != null){
							str+="   "+result[i].fourtofive-result[i].fourtofiveInv+"</td>";
						  }else if(result[i].fourtofive != null){
							 str+="   "+result[i].fourtofive+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  
					  } 
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>3 to 4 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].threetofour != null){
						str+="   "+result[i].threetofour+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					    if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].threetofourInv != null){
							str+="   "+result[i].threetofourInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].threetofourInv != null && result[i].threetofour != null){
							str+="   "+result[i].threetofour-result[i].threetofourInv+"</td>";
						  }else if(result[i].threetofour != null){
							 str+="   "+result[i].threetofour+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  
						}
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>2 to 3 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].twotothree != null){
						str+="   "+result[i].twotothree+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					 
					  if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].twotothreeInv != null){
							str+="   "+result[i].twotothreeInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].twotothreeInv != null && result[i].twotothree != null){
							str+="   "+result[i].twotothree-result[i].twotothreeInv+"</td>";
						  }else if(result[i].twotothree != null){
							 str+="   "+result[i].twotothree+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  }  
					  
					  
					  
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>1 to 2 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].onetotwo != null){
						 str+="   "+result[i].onetotwo+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  if(needInvitees_a){  
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].onetotwoInv != null){
							str+="   "+result[i].onetotwoInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].onetotwoInv != null && result[i].onetotwo != null){
							str+="   "+result[i].onetotwo-result[i].onetotwoInv+"</td>";
						  }else if(result[i].onetotwo != null){
							 str+="   "+result[i].onetotwo+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  } 
					  
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>1/2 to 1 Hours</td>";
				  for(var i in result){
					
					  if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].halfanhour != null){
						 str+="   "+result[i].halfanhour+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					    if(needInvitees_a){
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].halfanhourInv != null){
							str+="   "+result[i].halfanhourInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].halfanhourInv != null && result[i].halfanhour != null){
							str+="   "+result[i].halfanhour-result[i].halfanhourInv+"</td>";
						  }else if(result[i].halfanhour != null){
							 str+="   "+result[i].halfanhour+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						}
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'> < 1/2 Hour</td>";
				  for(var i in result){
				
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].belowhalfanhour != null){
						 str+="   "+result[i].belowhalfanhour+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					    if(needInvitees_a){
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].belowhalfanhourInv != null){
							str+="   "+result[i].belowhalfanhourInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].belowhalfanhourInv != null && result[i].belowhalfanhour != null){
							str+="   "+result[i].belowhalfanhour-result[i].belowhalfanhourInv+"</td>";
						  }else if(result[i].belowhalfanhour != null){
							 str+="   "+result[i].belowhalfanhour+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						}
				  }
    				  str+="</tr>";
                            str+="</tbody>";
                        str+="</table>";
					
					$("#visitorsTableId").html(str);
			   }
	 }
  function getDayWiseVisitSummary(){
	var subEvents1 = [];
	var stateId =0;
	 var eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1 = [globalMainEntryId]; // -- MAHANADU MAIN ENTRY
	 }

	 var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:0,
			subEvents : subEvents1,			
		}	
		
		$.ajax({
          type:'GET',
          url: 'getDayWiseVisitSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		  if(result!=null && result.length>0){
			buildTotalVisitorsResult(result);
		  }
		});
}
var progressBarClsArr=["progress-bar-info","progress-bar-success","progress-bar-warning"];
function buildTotalVisitorsResult(result){
	
	  var totalVisitorsCount=0;
	  if(result[0].totalVisitorsCount!=null && result[0].totalVisitorsCount>0){
		  totalVisitorsCount=result[0].totalVisitorsCount;
	  }
	 var str='';
	for(var i in result){
		 var number = parseInt(i)+1;
		 var prcntgCmpltd=(result[i].total)*100/totalVisitorsCount;
			if(number>1){
			str+="<p class='m_0'>"+(parseInt(i)+1)+" DAY'S VISITORS &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+result[i].total+" <span class='pull-right'>"+prcntgCmpltd+"%</span></p>";
			}else{
		    str+="<p class='m_0'>"+(parseInt(i)+1)+" DAY VISITORS &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+result[i].total+"<span class='pull-right'>"+prcntgCmpltd+"%</span></p>";
			}
			str+="<div class='progress progressNewCustom'>";
			   str+="<div class='progress-bar "+progressBarClsArr[i]+"' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width:"+prcntgCmpltd+"% '>";
				 str+="<span class='sr-only'>"+result[i].total+" Complete</span>";
			  str+=" </div>";
			str+="</div>";
	 } 
	 $("#totalVisitorsDtlsId").html(str);
}
   </script>
</body>
</html>