<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Center</title>
<link href="training/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="training/dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="training/dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
.panel-group .panel
{
	margin-top:0px !important;
}
.panel .panel-default
{
	border-width:1px !important
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
</style>
</head>
<body>
<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;"></p>               
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
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
            	<div class="panel panel-default">
                	<div class="panel-heading bg_c">
                    	<h4 class="panel-title">
                        	TRAINING CENTER MAIN DASHBOARD
							<span class="pull-right" style="margin-top: -8px;">
								<div class="input-group" >
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									<input class="form-control" id="selectDate">
								</div>
							</span>
                        </h4>
                    </div>
                    <div class="panel-body">
                    	<section>
                        	<div class="row">
                            	<div class="col-md-12">
                                	<table class="table table-bordered">
                                    	<tr>
                                        	<td>
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0" id="totalTariningPrograms"></h1></li>
                                                </ul>
                                                <p class="m_0">Training Programs</p>
                                            </td>
                                            <td>
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0" id="totalTrainingCenters"></h1></li>
                                                </ul>
                                                <p class="m_0">Training Center</p>
                                            </td>
                                            <td>
                                            	<div class="display-style">
                                            	<h1 class="m_0" id="totalMembers"></h1> 
												<p class="m_0">Total Members</p>
                                                </div>
                                                <ul class="display-style ul-top-block m_0">
                                                	<li class="text-danger">UPCOMING -<span class="pull-right" id="upcomingMembers"> </span></li>
                                                    <li class="text-yellow">RUNNING - <span class="pull-right" id="runningMembers"></span></li>
                                                    <li class="text-success">COMPLETED - <span class="pull-right" id="completedMembers"></span></li>
                                                </ul>
                                            </td>
                                            <td>
                                            	<ul class="list-inline m_0">
                                                	<li><div id="donutchart" style="height: 66px;width:100px;"></div></li>
                                                    <li>
                                                    	<h4 class="m_0">TOTAL TRAINING BATCHES - <span id="totalTrainingBatches"></span></h4>
                                                        <ul class="list-inline">
                                                            <li class="text-danger">UPCOMING BATCHES - <span id="upcomingBatches"></span> // </li>
                                                            <li class="text-yellow">RUNNING - <span id="runningBatches"></span> // </li>
                                                            <li class="text-success">COMPLETED - <span id="completedBatches"></span></li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            	
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </section>
                    	<section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">ALL TRAINING PROGRAM WISE DETAILS</h4>
                                        </div>
                                        <div class="panel-body pad_0">
                                        	<div class="table-responsive table-scroll" id="allProgramWiseDetailsDIv"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">UPCOMING TRAINING PROGRAM DETAILS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="upComingTrainingPrograms">
                                        	<!--<table class="table table-bordered m_0">
                                            	<thead class="bg_d">
                                                	<th>TRAINING PROGRAM NAME</th>
                                                    <th>TRAINING CENTER</th>
                                                    <th>TRAINING SCHEDULE</th>
                                                    <th>BATCH NAME</th>
                                                    <th>TRAINING ON</th>
                                                    <th>MEMBERS ACCEPTED</th>
                                                </thead>
                                                <tbody>
                                                	<tr>
                                                    	<td rowspan="5">Leadership Skills</td>
                                                        <td rowspan="3">EWK Center</td>
                                                        <td rowspan="2">Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>GPN Center</td>
                                                        <td>Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>

                                                    <tr>
                                                    	<td>SVV Center</td>
                                                        <td>Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td class="text-danger">95</td>
                                                    </tr>

                                                </tbody>
                                            </table>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">CURRENTLY RUNNING TRAINING PROGRAMS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="runningTrainingPrograms">
                                        	<!--<table class="table table-bordered m_0">
                                            	<thead class="bg_d">
                                                	<th>TRAINING PROGRAM NAME</th>
                                                    <th>TRAINING CENTER</th>
                                                    <th>TRAINING SCHEDULE</th>
                                                    <th>BATCH NAME</th>
                                                    <th>TRAINING ON</th>
                                                    <th>MEMBERS ATTENDED</th>
                                                </thead>
                                                <tbody>
                                                	<tr>
                                                    	<td rowspan="5">Leadership Skills</td>
                                                        <td rowspan="3">EWK Center</td>
                                                        <td rowspan="2">Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>GPN Center</td>
                                                        <td>Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>

                                                    <tr>
                                                    	<td>SVV Center</td>
                                                        <td>Oct 01-15</td>
                                                        <td>EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td class="text-danger">95</td>
                                                    </tr>

                                                </tbody>
                                            </table>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section>
                            <div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-bold">COMPLETED TRAINING PROGRAMS</h4>
                                        </div>
                                        <div class="panel-body pad_0" id="completedTrainingPrograms">
                                        	<!--<table class="table table-bordered m_0">
                                            	<thead class="bg_d">
                                                	<th>TRAINING PROGRAM NAME</th>
                                                    <th>TRAINING CENTER</th>
                                                    <th>TRAINING SCHEDULE</th>
                                                    <th>BATCH NAME</th>
                                                    <th>TRAINING ON</th>
                                                    <th>MEMBERS ACCEPTED</th>
                                                </thead>
                                                <tbody>
                                                	<tr>
                                                    	<td rowspan="5" class="text-underline">Leadership Skills</td>
                                                        <td rowspan="3" class="text-underline">EWK Center</td>
                                                        <td rowspan="2">Oct 01-15</td>
                                                        <td class="text-underline">EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-underline">EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Oct 01-15</td>
                                                        <td class="text-underline">EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>
                                                    <tr>
                                                    	<td class="text-underline">GPN Center</td>
                                                        <td>Oct 01-15</td>
                                                        <td class="text-underline">EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td>95</td>
                                                    </tr>

                                                    <tr>
                                                    	<td class="text-underline">SVV Center</td>
                                                        <td>Oct 01-15</td>
                                                        <td class="text-underline">EWK_Oct_01_03</td>
                                                        <td>01/Oct/2015-03/Oct/2015</td>
                                                        <td class="text-danger">95</td>
                                                    </tr>

                                                </tbody>
                                            </table>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
						
                        <section>
                        	<div class="row">
                            	<div class="col-md-12">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title">
                                            	PARTY DESIGNATION WISE MEMBERS PARTICIPATED - COMPLETED TRAINING PROGRAMS
                                            </h4>
                                        </div>
										
                                        <div class="panel-body pad_0">
										  <img id="partyDesgWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
										  <div id="partyDesgWisedivId"></div>
											
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
						
                        <section>
                        	<div class="row">
                            	<div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">DISTRICTS WISE TOTAL MEMBERS ACCEPTED</h4>
                                        </div>
                                        <div class="panel-body pad_0">
										  <img id="distWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
										  <div id="distWiseDivId"></div>
                                        </div>
                                    </div>
                                	
                                </div>
                                <div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">CONSTITUENCY WISE TOTAL MEMBERS ACCEPTED</h4>
                                        </div>
										
                                        <div class="panel-body pad_0">
										  <img id="constWiseImg" src="images/ajaxImg2.gif" style="width:45px;height:45px;margin-left:45%">
										  <div id="constWiseDivId"></div>
                                        </div>
										
										
                                    </div>
                                </div>
                            </div>
                        </section>
						
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>
<script src="training/dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="training/dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="training/dist/scroll/jquery.mCustomScrollbar.js"></script>
<script type="text/javascript" src="training/dist/scroll/jquery.mousewheel.js"></script>
<script src="training/dist/DateRange/moment.js" type="text/javascript"></script>
<script src="training/dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="training/dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">

$(function () {
	var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
	$('#selectDate span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
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
	   //'Today': [moment(), moment()],
	   //'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   //'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   '30 Days': [moment().subtract(29, 'days'), moment()],
	   '60 Days': [moment().subtract(60, 'days'), moment()],
	   '180 Days': [moment().subtract(180, 'days'), moment()],
	   '365 Days': [moment().subtract(365, 'days'), moment()],
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
  $('#selectDate span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#selectDate').daterangepicker(optionSet1, cb);

  $('#selectDate').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#selectDate').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#selectDate').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#selectDate').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
	
	getTrainingCenterDetailsBasedOnDates();
	getAttendedCountForBatchesByLocation();
	getInvitedAttendedCadreCountByBatchIds();
});

 $("#mainheading").html(" TRAINING CENTER DASHBOARD ");
 
function getTrainingCenterDetailsBasedOnDates(){
  
	var jsObj=
	{	
		selectedDate : $("#selectDate").val()
	}
    $.ajax({
          type:'GET',
          url: 'getTrainingCenterDetailsBasedOnDatesAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result!=null && result.completed!=null && result.completed.programDetails!=null && result.completed.programDetails.length>0){
		  var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	
			
			var obj={};
			
			//for programwise batch count
			for(var i in result.completed.programDetails){
				var temp=0;;
				var myResult = result.completed.programDetails;
				if(result.completed.completedBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.completed.programDetails){
				var myResult = result.completed.programDetails;
				if(result.completed.completedBatchIds!=null){
					str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+">"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+">"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].completedMemberCount!=null){
											str+='<td>'+schedule.batchDetails[m].completedMemberCount+'</td>';
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#completedTrainingPrograms").html(str);
		}else{
			$("#completedTrainingPrograms").html("No Completed Training Programs Are Available");
		}
		
		if(result!=null && result.running!=null && result.running.programDetails!=null && result.running.programDetails.length>0){
						
			var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	
			
			var obj={};
			//for programwise batch count
			for(var i in result.running.programDetails){
				var temp=0;;
				var myResult = result.running.programDetails;
				if(result.running.runningBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.running.programDetails){
				var myResult = result.running.programDetails;
				str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+">"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+">"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].runningMemberCount!=null){
											str+='<td>'+schedule.batchDetails[m].runningMemberCount+'</td>';
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#runningTrainingPrograms").html(str);
		}else{
			$("#runningTrainingPrograms").html("No Runnig Training Programs Are Available");
		}
		
		if(result!=null && result.upcoming!=null && result.upcoming.programDetails!=null && result.upcoming.programDetails.length>0){
			var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>TRAINING PROGRAM NAME</th>';
			str+='<th>TRAINING CENTER</th>';
			str+='<th>TRAINING SCHEDULE</th>';
			str+='<th>BATCH NAME</th>';
			str+='<th>TRAINING ON</th>';
			str+='<th>MEMBERS ACCEPTED</th>';
			str+='</thead>';
			str+='<tbody>';	

			var obj={};
			//for programwise batch count
			for(var i in result.upcoming.programDetails){
				var temp=0;;
				var myResult = result.upcoming.programDetails;
				if(result.upcoming.upComingBatchIds!=null){
					for(var j in myResult[i].campDetails){
						for(var k in myResult[i].campDetails[j].scheduleDetails){
							var schedule = myResult[i].campDetails[j].scheduleDetails[k];
							for(var m in schedule.batchDetails){
								temp=temp+1;
							}
						}
					}
				}
				obj[""+myResult[i].programId+""]=temp;
			}
			
			for(var i in result.upcoming.programDetails){
				var myResult = result.upcoming.programDetails;
				if(result.upcoming.upComingBatchIds!=null){
					str+="<tr>";
					str+="<td rowspan="+obj[myResult[i].programId]+">"+myResult[i].programName+"</td>";
					for(var j in myResult[i].campDetails){
						var campspan=myResult[i].campDetails[j].cmpBatchCount;
						str+="<td rowspan="+campspan+">"+myResult[i].campDetails[j].campName+"</td>";
							for(var k in myResult[i].campDetails[j].scheduleDetails){
								var schedule = myResult[i].campDetails[j].scheduleDetails[k];
								str+="<td rowspan="+schedule.batchDetails.length+">"+schedule.scheduleCode+"</td>";
									for(var m in schedule.batchDetails){
										str+="<td>"+schedule.batchDetails[m].batchName+"</td>";
										str+='<td>'+schedule.batchDetails[m].batchDates+'</td>';
										if(schedule.batchDetails[m].upCommingMemberCount!=null){
											str+='<td>'+schedule.batchDetails[m].upCommingMemberCount+'</td>';
										}else{
											str+='<td>0</td>';
										}
										
										str+="</tr>";
									}
								str+="</tr>";
							}
						str+="</tr>";
					}
				}
				
				
			}
			str+='</tbody>';
			str+='</table>';
			
			$("#upComingTrainingPrograms").html(str);
		}else{
			$("#upComingTrainingPrograms").html("No Upcomming Training Programs Are Available");
		}
		
		
		if(result!=null && result.completed!=null && result.completed.programWiseDetails!=null && result.completed.programWiseDetails.length>0){
			var totalMembersGlob=0,upcomingBatchesGlob=0,compltedBatchesGlob=0,runningBatchesGlob=0,upcomingMemGlob=0,runningMemGlob=0,compltedMemGlob=0,totalTrainingBatchesGlob=0;
			var str='';
			
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<th>Training Program Name</th>';
			str+='<th>Training Center Name</th>';
			str+='<th>Total<br/> Batches</th>';
			str+='<th class="text-danger">Upcoming<br/> Batches</th>';
			str+='<th class="text-warning">Training<br/>Running</th>';
			str+='<th class="text-success">Completed <br/>Batches</th>';
			str+='<th class="text-muted">Total <br/>Members</th>';
			str+='</thead>';
			str+='<tbody>';
			$("#totalTariningPrograms").html(result.completed.programWiseDetails.length);
			$("#totalTrainingCenters").html(result.completed.totalTrainingCenters);
			for(var i in result.completed.programWiseDetails){
				str+='<tr>';
				str+='<td rowspan='+result.completed.programWiseDetails[i].campDetails.length+'>'+result.completed.programWiseDetails[i].programName+'</td>';
				if(result.completed.programWiseDetails[i].campDetails!=null && result.completed.programWiseDetails[i].campDetails.length>0){
					for(var j in result.completed.programWiseDetails[i].campDetails){
						str+='<td>'+result.completed.programWiseDetails[i].campDetails[j].campName+'</td>';
						if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails.length>0){
							for(var k in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails){
								
								var TotalBatches=0,TotalMembers=0;
								
								TotalBatches=TotalBatches+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length;
								totalTrainingBatchesGlob=totalTrainingBatchesGlob+TotalBatches;
								str+='<td>'+TotalBatches+'</td>';
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length>0){
									var temp=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails[l].memberCount;
									}
									TotalMembers=TotalMembers+temp;
									upcomingMemGlob=upcomingMemGlob+temp;
									upcomingBatchesGlob=upcomingBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length;
									str+='<td>'+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].upcomingDetails.length+'('+temp+')</td>';
									
								}else{
									str+='<td>-</td>';
								}
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length>0){
									var temp=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails[l].memberCount;
									}
									TotalMembers=TotalMembers+temp;
									runningMemGlob=runningMemGlob+temp;
									runningBatchesGlob=runningBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length;
									str+='<td>'+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].runningDetails.length+'('+temp+')</td>';
								}else{
									str+='<td>-</td>';
								}
								
								if(result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails!=null && result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length>0){
									var temp=0;
									for(var l in result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails){
										temp=temp+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails[l].memberCount;
									}
									TotalMembers=TotalMembers+temp;
									compltedMemGlob=compltedMemGlob+temp;
									compltedBatchesGlob=compltedBatchesGlob+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length;
									str+='<td>'+result.completed.programWiseDetails[i].campDetails[j].scheduleDetails[k].completedDetails.length+'('+temp+')</td>';
								}else{
									str+='<td>-</td>';
								}
								totalMembersGlob=totalMembersGlob+TotalMembers;
								str+='<td>'+TotalMembers+'</td>';
								
							}
						}	
						str+='</tr>';
					}
				}
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#totalMembers").html(totalMembersGlob);
			$("#upcomingMembers").html(upcomingMemGlob);
			$("#runningMembers").html(runningMemGlob);
			$("#completedMembers").html(compltedMemGlob);
			$("#totalTrainingBatches").html(totalTrainingBatchesGlob);
			$("#upcomingBatches").html(upcomingBatchesGlob);
			$("#runningBatches").html(runningBatchesGlob);
			$("#completedBatches").html(compltedBatchesGlob);
			$("#allProgramWiseDetailsDIv").html(str);
			
			Highcharts.setOptions({
				colors: ['#40b5bf', '#999967', '#089bf8', '#ac69ae' , '#cccccc']
			});
			$('#donutchart').highcharts({
				chart: {
					type: 'pie',
					backgroundColor: 'transparent',
					options3d: {
						enabled: false,
						alpha: 30
					}
				},
				legend: {
						enabled: true,
						align: 'right',
						verticalAlign: 'right',
						floating: false,
						backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					},
				plotOptions: {
					pie: {
						innerSize: 50,
						depth: 10,
						dataLabels: {
							enabled: false,
						}
					}, 
				},
				
				series: [{
					data: [
						['Upcoming', upcomingBatchesGlob],
						['Running', runningBatchesGlob],
						['Completed', compltedBatchesGlob],
					]
				}]
			});
		}else{
			$("#allProgramWiseDetailsDIv").html("No Data Found");
		}
   });
}

	function getAttendedCountForBatchesByLocation(){
		
		$('#partyDesgWisedivId').html('');
		var jObj={
			selectedDate : $("#selectDate").val()
	     }
		$.ajax({
		   type:'POST',
		   url :'getAttendedCountForBatchesByLocationAction.action',
		   data: {task:JSON.stringify(jObj)},
		}).done(function(result){
			  $('#partyDesgWiseImg').hide();
			  if(result!=null && result.length>0){
				  var str='';
				  str+='<table class="table table-bordered m_0">';
				  for(var i in result){
				    str+='<tr>';
                      str+='<td>'+result[i].name.toUpperCase()+'  LEVEL COMMITTEE MEMBERS</td>';
                      str+='<td>'+result[i].districtid+' ATTENDED</td>';
                      str+='</tr>';
				  }
				 str+='</table>';
				 $('#partyDesgWisedivId').html(str);
			  }else{
				   $('#partyDesgWisedivId').html('NO DATA AVAILABLE');
			  }
	    });
	}
	
	function getInvitedAttendedCadreCountByBatchIds(){
		
		$('#distWiseDivId').html('');
		$('#constWiseDivId').html('');
		var jObj={
		  selectedDate : $("#selectDate").val()
	     }
		$.ajax({
		   type:'POST',
		   url :'getInvitedAttendedCadreCountByBatchIdsAction.action',
		    data: {task:JSON.stringify(jObj)},
		}).done(function(result){
		       $('#distWiseImg').hide(); 
			   $('#constWiseImg').hide(); 
			   
		 	   if(result!=null && result.simpleVOList1!=null && result.simpleVOList1.length>0){
				   buildDistData(result.simpleVOList1);
			   }else{
				   $('#distWiseDivId').html('<p style="text-align:center;">NO DATA AVAILABLE</p>');
			   }
			   
			   if(result!=null && result.simpleVOList2!=null && result.simpleVOList2.length>0){
				  buildConstData(result.simpleVOList2);
			   }else{
				  $('#constWiseDivId').html('<p style="text-align:center;">NO DATA AVAILABLE</p>');
			   }
	    });
	}
	function buildDistData(results){
		
		var str='';
		str+='<table class="table table-bordered m_0">';
			str+='<thead>';
			str+='<th>DISTRICTS NAME</th>';
			str+='<th>INVITED</th>';
			str+='<th>ATTENDED</th>';
			str+='</thead>';
			str+='<tbody>';
		for(var i in results){
			str+='<tr>';
				str+='<td>'+results[i].name+'</td>';
				str+='<td>'+results[i].total+'</td>';
				str+='<td>'+results[i].count+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#distWiseDivId').html(str);
	}
	function buildConstData(results){
		
		var str='';
		str+='<table class="table table-bordered m_0">';
			str+='<thead>';
			str+='<th>CONSTITUENCY NAME</th>';
			str+='<th>INVITED</th>';
			str+='<th>ATTENDED</th>';
			str+='</thead>';
			str+='<tbody>';
		for(var i in results){
			str+='<tr>';
				str+='<td>'+results[i].name+'</td>';
				str+='<td>'+results[i].total+'</td>';
				str+='<td>'+results[i].count+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#constWiseDivId').html(str);
	}
</script>
</body>
</html>	