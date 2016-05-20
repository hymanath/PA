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
			  <span  class="text">Last Updated Time <span id="timeUpdationId"></span></span>
			  <a onclick="" title=" Page Refresh" class="refreshIcon" style="cursor:pointer">
				<span class="glyphicon glyphicon-refresh"></span>
			  </a>
			</div>
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
				<div class="panel-body panelDefault">
					<h4 class="m_0">TOTAL VISITORS</h4>
					<h3 id="totalVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">INVITEES AS VISITORS</h4>
					<h3 id="inviteesAsVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">NOT INVITEES AS VISITORS</h4>
					<h3 id="nonInviteesAsVisitorsId">0</h3>
					 <center><img id="tdyVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">VISITORS PRESENT IN CAMPUS</h4>
					<h3 id="visitorsPresentInCampusId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">INVITEES PRESENT IN  VISITORS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="inviteesPresentInVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default"><!--<i class="glyphicon glyphicon-refresh pull-right refreshIconPanel" title="page refresh" id="ttlTdyVstrsId"></i>-->
				<div class="panel-heading">
					<h4 class="panel-title">TODAY</h4>
				</div>
				<div class="panel-body panelDefault">
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
					<h4 class="panel-title">HOUR WISE VISITORS<i class="glyphicon glyphicon-refresh pull-right hrWseVstrsCls refreshIconPanel" title="page refresh" id="hrWiseVstrsId"></i></h4>
					<p class="font-10 fontColor">Last updated On: <span id="hourWiseTableTimeId"></span></p>
				</div>
				<div class="panel-body pad_0" style="height:370px">
				<div><center><img id="hrWiseVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
				 <div id="visitorsTableId"></div>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">HOUR WISE VISITORS<i class="glyphicon glyphicon-refresh pull-right hrWseVstrsCls refreshIconPanel" title="page refresh" id="hrWiseVstrsGraphId"></i></h4>
					<p class="font-10 fontColor">Last updated On: <span id="hourWiseGraphTimeId"></span></p>
				</div>
				<div class="panel-body">
				  <div><center><img id="hrWiseVstrsHghChrtPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
					<div id="hoursWiseVisitors" style="height:325px;width:100%;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default panel-custom-default" style="height:230px;">
				<div class="panel-heading">
					<h4 class="panel-title">DAY WISE UNIQUE AND REVISIT SUMMARY<i class="glyphicon glyphicon-refresh pull-right refreshIconPanel" title="page refresh" id="refreshDaysWiseRevisitId"></i></h4>
				</div>
				<div class="panel-body pad_8">
				<div><center><img id="dayWsUnquVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
				 <div id="daysSummaryUniqueTableId"></div>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default panel-custom-default" style="height:230px;">
				<div class="panel-heading">
					<h4 class="panel-title">DAY WISE VISIT SUMMARY<i class="glyphicon glyphicon-refresh pull-right refreshIconPanel" title="page refresh" id="rfrshDyWsPrgrssRvstId"></i></h4>
				</div>
				<div class="panel-body">
				<div><center><img id="dayWsVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
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
							<div class="apSwitch">
								<input type="checkbox" value="1" name="apSwitch" class="apSwitch-checkbox" id="apSwitch" checked>
								<label class="apSwitch-label" for="apSwitch">
									<span class="apSwitch-inner"></span>
									<span class="apSwitch-switch"></span>
								</label>
							</div>
							<div class="tsSwitch">
								<input type="checkbox" value="36" name="tsSwitch" class="tsSwitch-checkbox" id="tsSwitch" checked>
								<label class="tsSwitch-label" for="tsSwitch">
									<span class="tsSwitch-inner"></span>
									<span class="tsSwitch-switch"></span>
								</label>
							</div>
							<div class="otherStates">
								<input type="checkbox" value="0" name="otherStates" class="otherStates-checkbox" id="otherStates" >
								<label class="otherStates-label" for="otherStates">
									<span class="otherStates-inner"></span>
									<span class="otherStates-switch"></span>
								</label>
							</div>
							
						</div>
					</div>
					
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed tableNewDistWise" id="distWiseTableId">
							<!--<thead>
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
								<td>500</td>
								<td>100</td>
								<td>100</td>
							</tr>-->
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</section>
<script src="https://code.highcharts.com/highcharts.js"></script>
	
<script type="text/javascript">

 var maxHeight = 0;
$(".panelDefault").each(function(){
   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
});
$(".panelDefault").height(maxHeight);	
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
			getDistrictWiseMembersCountInCampus();
			}, 2000);
    } 
	
	$(document).on("click",".refreshButton",function(){
		var currentdate = new Date(); 
		var datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() ;

		$("#timeUpdationId").html(datetime);
		getSubEventsOfEvent();		
		  setTimeout(function(){
			getDaysUniqueAndRevisitSummary();	
            getTodayTotalVisitors(); 
            getDetails();		
            getDayWiseVisitSummary();
			getDistrictWiseMembersCountInCampus();
			}, 2000);
	});
	
    $(document).ready(function(){
		
		var currentdate = new Date(); 
		var datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() ;

		$("#timeUpdationId").html(datetime);

		 setTimeout(function(){
			getDaysUniqueAndRevisitSummary();	
            getTodayTotalVisitors(); 
            getDetails();		
            getDayWiseVisitSummary();			
			getDistrictWiseMembersCountInCampus();
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
		$("#daysSummaryUniqueTableId").html(' ');
		$("#dayWsUnquVstrsPrcssngImgId").show();
		$.ajax({
          type:'GET',
          url: 'getDaysUniqueAndRevisitSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$("#dayWsUnquVstrsPrcssngImgId").hide();
			if(result!=null && result.length>0){
				buildDaysUniqueAndRevisitSummary(result);
			}else{
				$("#daysSummaryUniqueTableId").html("<h5> No Data Availabel.</h5>");
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
	  	
	$("#tdyVstrsPrcssngImgId").show();

   	var eventId = $("#mainEventSelectId").val();
	   var jObj = {
			eventId:eventId
		}
		
		$.ajax({
          type:'GET',
          url: 'getTodayTotalVisitorsInfoAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$("#tdyVstrsPrcssngImgId").hide();
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
		  
		  $("#visitorsTableId").html(' ');
		  $("#hoursWiseVisitors").html(' ');
		  $("#hrWiseVstrsPrcssngImgId").show();
		  $("#hrWiseVstrsHghChrtPrcssngImgId").show();
		  var currentdate = new Date(); 
		
		var datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() ;

		$("#hourWiseTableTimeId").html(datetime);
		$("#hourWiseGraphTimeId").html(datetime);
		
    	 var  eventId = $("#mainEventSelectId").val();
		   var jsObj={
			   eventId:eventId
		   }
		  $.ajax({
			  type:'GET',
			  url: 'getTodayTotalAndCurrentUsersInfo.action',
			  data :{task:JSON.stringify(jsObj)}
          }).done(function(result){
			    $("#hrWiseVstrsPrcssngImgId").hide();
				$("#hrWiseVstrsHghChrtPrcssngImgId").hide();
			  if(result!=null && result.length>0){
				  buildVisitorsResultByTime(result);
				  buildVisitorsDtlsGraph(result);
			  }else{
				  $("#visitorsTableId").html("<h5> No Data Availabel.</h5>");
				  $("#hoursWiseVisitors").html("<h5> No Data Availabel.</h5>");
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
                               // str+=' 	<th class="back-white"></th>';
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
	 $("#totalVisitorsDtlsId").html(' ');
     $("#dayWsVstrsPrcssngImgId").show();
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
		  $("#dayWsVstrsPrcssngImgId").hide();
		  if(result!=null && result.length>0){
			buildTotalVisitorsResult(result);
		  }else{
			$("#totalVisitorsDtlsId").html("<h5> No Data Availabel.</h5>");
		  }
		});
}
var progressBarClsArr=["progress-bar-info","progress-bar-success","progress-bar-warning"];
function buildTotalVisitorsResult(result){
	
	  var totalVisitorsCount=0;
	  for(var i in result){
		  totalVisitorsCount=totalVisitorsCount+result[i].total;
	  }
	 var str='';
	for(var i in result){
		 var number = parseInt(i)+1;
		 var prcntgCmpltd=(result[i].total)*100/totalVisitorsCount;
			if(number>1){
			str+="<p class='m_0'>"+(parseInt(i)+1)+" DAY'S VISITORS &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+result[i].total+" <span class='pull-right'>"+prcntgCmpltd.toFixed(2)+"%</span></p>";
			}else{
		    str+="<p class='m_0'>"+(parseInt(i)+1)+" DAY VISITORS &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+result[i].total+"<span class='pull-right'>"+prcntgCmpltd.toFixed(2)+"%</span></p>";
			}
			str+="<div class='progress progressNewCustom'>";
			   str+="<div class='progress-bar "+progressBarClsArr[i]+"' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width:"+prcntgCmpltd.toFixed(2)+"% '>";
				 str+="<span class='sr-only'>"+result[i].total+" Complete</span>";
			  str+=" </div>";
			str+="</div>";
	 } 
	 $("#totalVisitorsDtlsId").html(str);
}
	
	$(document).on("click",".hrWseVstrsCls",function(){
	 	getDetails();
  	});
  	$(document).on("click","#rfrshDyWsPrgrssRvstId",function(){
		 getDayWiseVisitSummary();
  	});
  	$(document).on("click","#refreshDaysWiseRevisitId",function(){
	  getDaysUniqueAndRevisitSummary();
  	});
  	/* $(document).on("click","#ttlTdyVstrsId",function(){
	  getTodayTotalVisitors();
  	});
   */
	$(document).on("click","#tsSwitch",function(){
		getDistrictWiseMembersCountInCampus();
	});
	
	$(document).on("click","#otherStates",function(){
		getDistrictWiseMembersCountInCampus();
	});
	
	$(document).on("click","#apSwitch",function(){
		getDistrictWiseMembersCountInCampus();
	});
	
	function getDistrictWiseMembersCountInCampus(){
		var stateIds = [];
		$("#distWiseTableId").html('<img src="images/Loading-data.gif" style="width:70px;height:60px;"/>');
		if($("#tsSwitch").is(":checked")){
			stateIds.push($("#tsSwitch").val());
		}
		
		if($("#otherStates").is(":checked")){
			stateIds.push($("#otherStates").val());
		}
		
		if($("#apSwitch").is(":checked")){
			stateIds.push($("#apSwitch").val());
		}
		
		if(stateIds.length > 0){
			var jObj = {
				eventId:30,			
				stateIds:stateIds
			}	
			
			$.ajax({
			  type:'GET',
			  url: 'getDistrictWiseMembersCountInCampusAction.action',
			  data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				var str = '';
				str+='<thead>';
				str+='<th>District Id</th>';
				str+='<th>Name</th>';
				str+='<th>Now In Campus</th>';
				str+='<th>Total</th>';
				str+='</thead>';
				if(result != null && result.subList != null && result.subList.length > 0){
					str+='<tbody>';
					for( var  i in result.subList ){
						if(result.subList[i].total != null && result.subList[i].total > 0 && result.subList[i].cadreCount != null && result.subList[i].cadreCount > 0){
							str+='<tr>';
							str+='<td>'+result.subList[i].id+'</td>';
							str+='<td>'+result.subList[i].name+'</td>';
							str+='<td>'+result.subList[i].cadreCount+'</td>';
							str+='<td>'+result.subList[i].total+'</td>';
							str+='</tr>';
						}
					}
					str+='</tbody>';
				}else{
					str+='<tr><td colespan="4">No Data Available.</td></tr>';
				}
				$("#distWiseTableId").html(str);
			});
		}else{
			$("#distWiseTableId").html("<h5> No Data Availabel.</h5>");
		}
	}
   </script>
</body>
</html>