<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Center</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
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
                                                    <li><h1 class="m_0">01</h1></li>
                                                </ul>
                                                <p class="m_0">Training Programs</p>
                                            </td>
                                            <td>
                                            	<ul class="list-inline m_0">
                                                	<li>Total</li>
                                                    <li><h1 class="m_0">01</h1></li>
                                                </ul>
                                                <p class="m_0">Training Center</p>
                                            </td>
                                            <td>
                                            	<div class="display-style">
                                            	<h1 class="m_0">4000</h1>                                                <p class="m_0">Total Members</p>
                                                </div>
                                                <ul class="display-style ul-top-block m_0">
                                                	<li class="text-danger">UPCOMING -<span class="pull-right"> 200</span></li>
                                                    <li class="text-yellow">RUNNING - <span class="pull-right">300</span></li>
                                                    <li class="text-success">COMPLETED - <span class="pull-right">200</span></li>
                                                </ul>
                                            </td>
                                            <td>
                                            	<ul class="list-inline m_0">
                                                	<li><div id="donutchart" style="height: 66px;width:100px;"></div></li>
                                                    <li>
                                                    	<h4 class="m_0">TOTAL TRAINING BATCHES - 40</h4>
                                                        <ul class="list-inline">
                                                            <li class="text-danger">UPCOMING BATCHES // </li>
                                                            <li class="text-yellow">RUNNING - 300 // </li>
                                                            <li class="text-success">COMPLETED - 200</li>
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
                                        	<div class="table-responsive table-scroll">
                                        		<table class="table table-bordered m_0">
                                            	<thead class="bg_d">
                                                	<th>Training Program Name</th>
                                                    <th>Training Center Name</th>
                                                    <th>Total<br/> Batches</th>
                                                    <th class="text-danger">Upcoming<br/> Batches</th>
                                                    <th class="text-warning">Training<br/>Running</th>
                                                    <th class="text-success">Completed <br/>Batches</th>
                                                    <th class="text-muted">Total <br/>Members</th>
                                                </thead>
                                                <tbody>
                                                	<tr>
                                                    	<td rowspan="4">Leadership Skills</td>
                                                        <td>SVV Batch Center</td>
                                                        <td>12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-danger">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-warning">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-success">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-muted">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    </tr>
                                                    <tr>
                                                        <td>SVV Batch Center</td>
                                                        <td>12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-danger">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-warning">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-success">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-muted">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    </tr>
                                                    <tr>
                                                        <td>SVV Batch Center</td>
                                                        <td>12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-danger">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-warning">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-success">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-muted">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    </tr>
                                                    <tr>
                                                        <td>SVV Batch Center</td>
                                                        <td>12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-danger">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-warning">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-success">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                        <td class="text-muted">12<p class="font-10 m_0">(1200 Members)</p></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                           	</div>
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
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="dist/scroll/jquery.mCustomScrollbar.js"></script>
<script type="text/javascript" src="dist/scroll/jquery.mousewheel.js"></script>
<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">

$(function () {
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
                ['Interested', 157],
                ['Not Interested', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
	
	getTrainingCenterDetailsBasedOnDates();
});

 $("#mainheading").html(" TRAINING CENTER DASHBOARD ");
 
function getTrainingCenterDetailsBasedOnDates(){
  
	var jsObj=
	{				
	}
    $.ajax({
          type:'GET',
          url: 'getTrainingCenterDetailsBasedOnDatesAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
	   if(result.completed.programDetails!=null && result.completed.programDetails.length>0){
						
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
			for(var i in result.completed.programDetails){
				var myResult = result.completed.programDetails;
				str+="<tr>";
					str+="<td rowspan="+result.completed.completedBatchIds.length+">"+myResult[i].programName+"</td>";
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
			str+='</tbody>';
			str+='</table>';
			
			$("#completedTrainingPrograms").html(str);
		}else{
			$("#completedTrainingPrograms").html("No Completed Training Programs Are Available");
		}
		
		if(result.running.programDetails!=null && result.running.programDetails.length>0){
						
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
			for(var i in result.running.programDetails){
				var myResult = result.running.programDetails;
				str+="<tr>";
					str+="<td rowspan="+result.completed.runningBatchIds.length+">"+myResult[i].programName+"</td>";
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
			str+='</tbody>';
			str+='</table>';
			
			$("#runningTrainingPrograms").html(str);
		}else{
			$("#runningTrainingPrograms").html("No Runnig Training Programs Are Available");
		}
		
		if(result.upcoming.programDetails!=null && result.upcoming.programDetails.length>0){
						
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
			for(var i in result.upcoming.programDetails){
				var myResult = result.upcoming.programDetails;
				str+="<tr>";
					str+="<td rowspan="+result.completed.upComingBatchIds.length+">"+myResult[i].programName+"</td>";
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
			str+='</tbody>';
			str+='</table>';
			
			$("#upComingTrainingPrograms").html(str);
		}else{
			$("#upComingTrainingPrograms").html("No Upcomming Training Programs Are Available");
		}
   });
}
getAttendedCountForBatchesByLocation();
getInvitedAttendedCadreCountByBatchIds();

	function getAttendedCountForBatchesByLocation(){
		
		$('#partyDesgWisedivId').html('');
		var jObj={
			startDateString:'2015-08-20',
			endDateString:'2015-09-04',
			stateId:'1'
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
		  startDateString:'2015-08-20',
		  endDateString:'2015-09-04',
		  stateId:'1'
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