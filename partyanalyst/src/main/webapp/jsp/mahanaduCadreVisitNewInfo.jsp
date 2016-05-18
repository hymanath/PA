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
	.box-shadow-css
	{
	  box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75);
	  border-radius:0px;
	}
	.back-white
	{
		background:#fff !important;
	}
	.m_bottom10
	{
		margin-bottom:10px;
	}
	.color-black
	{
		color:#000 !important;
		font-size:10px
	}
	.table-color1
	{
		background:#F1F1F1
	}
	.table-color2
	{
		background:#F5FAFD;
	}
	
	table.dataTable thead th, table.dataTable thead td,table.dataTable tbody td{text-align:center;}
	.inviteesCkbCls{margin-top:0px !important;}
</style>
</head>
<body>
<section class="container">
	
	<div class="row">
    	<div class="col-md-12" style="margin-top:40px;">
        	<div class="panel panel-default box-shadow-css">
            	<div class="panel-heading" style="background:#00B07D;color:#ffffff;">
        			
					<div class="row">
						<div class="col-md-8">
							<h3 class="text-right m_0" style="color:#fff !important">MAHANADU VISITORS INFO</h3>
						</div>
						<div class="  col-md-2 col-md-offset-2">
						<select id="mainEventSelectId" class="form-control mainEventSelectCls pull-right">
							<option value="0">Select Event</option>
							<option value="7" >Mahanadu 2015</option>
							<option value="30" selected>Mahanadu 2016</option>
						</select>
						</div>
					</div>
                </div>
				<div><input style="display:none;" type="button" onclick="populate();" value="Update Latest Info"/></div>
                <div class="panel-body" style="padding:0px 15px">
                	<div class="col-md-6 m_0">
                    	<h4 class="pull-right">Today Total Visitors Attended : <span id="totalVisitors" >0</span></h4>
                    </div>
                    <div class="col-md-6 m_0" style="border-left:1px solid #ccc">
                    	<h4 class="pull-left">Present Visitors in Campus : <span id="currentVisitors">0</span>
						<span style="font-size:14px;color:#ccc;display:none;" class="inviteesSpanCls">[ Invitees:<span id="currentinvVisitors">0</span>
						Non Invitees:<span id="currentnoninvVisitors">0</span> ]</span></h4>
						
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-8">
        	<div class="panel panel-default box-shadow-css">
            	<div class="panel-heading " style=" min-height:38px; background:#00B07D">
                <p class="m_0 display-style"><i class="glyphicon glyphicon-calendar reservation" style="color:#ccc; font-size:18px;display:none;"></i></p>
                <div class="pull-left">
                    <span style="font-size:14px; font-weight:bold; background:#00B07D;color:#fff;">Last Updated On:<span id="lasteUpdtOn"> </span></span>
                    &nbsp;<i class="glyphicon glyphicon-refresh" onclick="getDetails();" style="color:#fff;cursor: pointer;"></i>
                </div>
               <!-- <div class="pull-right"><input type="checkbox" id="inviteesCkbCls_a" class="inviteesCkbCls"/> <span>Show Invitees & Non-Invitees</span></div>-->
				
				<!--  <div class="onoffswitch-invi pull-right">
                    <input type="checkbox" name="onoffswitch-invi" class="onoffswitch-checkbox-invi inviteesCkbCls" id="inviteesCkbCls_a" checked>
                    <label class="onoffswitch-label-invi" for="inviteesCkbCls_a">
                        <span class="onoffswitch-inner-invi"></span>
                        <span class="onoffswitch-switch-invi"></span>
                    </label>
                </div>-->
				
                </div>
                <div class="panel-body" style="padding:5px 15px">
				    <center><img id="resultTableDivAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
                    <div id="resultTableDiv">
                        
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
        	<div class="panel panel-default box-shadow-css" style="min-height: 245px;">
            	<div class="panel-heading m_bottom10" style="background:#00B07D;color:#ffffff;">
                	<span  style='font-size:14px;font-weight:bold;'>DAY'S UNIQUE AND REVISIT SUMMARY<i class="glyphicon glyphicon-refresh pull-right" style="color:#fff;cursor: pointer;" id="refreshUnqueRevisitId"></i></span>
                </div>
                <div class="panel-body" style="padding:0px;">
						<center><img id="daysSummaryIdAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
						
					   <div id="daysSummaryId"></div>
					</div>
            </div>
            <div class="panel panel-default box-shadow-css m_top10" style="min-height: 190px;">
            	<div class="panel-heading" style="background:#00B07D;color:#ffffff;">
                	<span  style='font-size:14px;font-weight:bold;'>DAY WISE VISIT SUMMARY<i class="glyphicon glyphicon-refresh pull-right" style="color:#fff;cursor: pointer;" id="refreshDayWiseId"></i></span>
                </div>
				<div class="panel-body" style="padding:0px;">
							<center><img id="ovrAlSummaryIdAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
							
						   <div id="ovrAlSummaryId"></div>
						</div>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-12">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="districtHeadingSummary">AP DISTRICT WISE SUMMARY</p>
				 <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox " id="myonoffswitchSummary" checked>
                    <label class="onoffswitch-label" for="myonoffswitchSummary">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
               <!--<div class="pull-right"><input type="checkbox" id="inviteesCkbCls_b" class="inviteesCkbCls"/> <span style="font-size:10px !important;">Show Invitees & Non-Invitees</span></div>-->
				
				<div class="onoffswitch-invi pull-right">
                    <input type="checkbox" name="onoffswitch-invi" class="onoffswitch-checkbox-invi inviteesCkbCls" id="inviteesCkbCls_b" checked>
                    <label class="onoffswitch-label-invi" for="inviteesCkbCls_b">
                        <span class="onoffswitch-inner-invi"></span>
                        <span class="onoffswitch-switch-invi"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body">
                    <div class="ScrollDiv">
					  <center><img id="distAjaxSummaryId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
					   <div id="districtTableSummaryId" style="margin-top: 10px;"> </div>
                        
                        </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-12">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="constiHeadingSummary">AP CONSTITUENCY WISE</p>
                 <div class="onoffswitch pull-right">
                     <input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" id="myonoffswitchSummary1" checked>
                     <label class="onoffswitch-label" for="myonoffswitchSummary1">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                     </label>
                </div>
				<!--<div class="pull-right"><input type="checkbox" id="inviteesCkbCls_c" class="inviteesCkbCls"/> <span style="font-size:10px !important;">Show Invitees & Non-Invitees</span></div>-->
				
				<div class="onoffswitch-invi pull-right">
                    <input type="checkbox" name="onoffswitch-invi" class="onoffswitch-checkbox-invi inviteesCkbCls" id="inviteesCkbCls_c" checked>
                    <label class="onoffswitch-label-invi" for="inviteesCkbCls_c">
                        <span class="onoffswitch-inner-invi"></span>
                        <span class="onoffswitch-switch-invi"></span>
                    </label>
                </div>
				
				
                </div>
                <div class="panel-body">
                    <div class="ScrollDiv">
					   <center><img id="constAjaxSummaryId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
					
                       <div id="constiTableSummaryId" style="margin-top:10px;"></div>
              
                        </div>
                </div>
            </div>
        </div>
    </div>
</section>
	
	<script type="text/javascript">
	  $(".dropdown-menu").html('<li><a href="eventDashboardAction.action?eventId=1">EVENTS DASHBOARD</a></li><li><a href="dashBoardAction.action">DASHBOARD</a></li><li><a href="newlogoutAction.action">LOGOUT</a></li>');
	  
	  var globalEventId = '${param.eventId}'
		if(globalEventId !=null && globalEventId>0){
			$("#mainEventSelectId").val(globalEventId);
		}
	  $(document).ready(function(){		  
		  getDetails();		  
		  setTimeout(function(){
			 getLocationWiseAttendeeSummaryCount(1,3,false);
			 getLocationWiseAttendeeSummaryCount(1,4,false);	
			 getDaysUniqueAndRevisitSummary();
			 getDayWiseVisitSummary();
			}, 2000);
	  });
	  
	  getSubEventsOfEvent();
	  var globalMainEntryId = 0 ;
	  function getSubEventsOfEvent(){
		  
		  eventId = $("#mainEventSelectId").val();
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
	  
	  $('input.inviteesCkbCls').removeAttr('checked');
	  
	  $(".inviteesCkbCls").click(function(){
		  
		  var inviteeId = $(this).attr("id");
		  //var isChecked = $("#"+inviteeId).is(':checked');
		 
		  if(inviteeId == "inviteesCkbCls_a"){
			  if(needInvitees_a){
				  needInvitees_a = false;
				  $(".inviteesSpanCls").hide();
			  }else{
				  needInvitees_a = true;
				  $(".inviteesSpanCls").show();
			  }
			  getDetails();
		  }
		  
		  if(inviteeId == "inviteesCkbCls_b"){
			   if(needInvitees_b){
				  needInvitees_b = false;
				  $(".inviteesSpanCls").hide();
			  }else{
				  needInvitees_b = true;
				  $(".inviteesSpanCls").show();
			  }
			  getLocationWiseAttendeeSummaryCount(1,3,true);
		  }
		  
		  if(inviteeId == "inviteesCkbCls_c"){
			   if(needInvitees_c){
				  needInvitees_c = false;
				  $(".inviteesSpanCls").hide();
			  }else{
				  needInvitees_c = true;
				  $(".inviteesSpanCls").show();
			  }
			  getLocationWiseAttendeeSummaryCount(1,4,true);
		  }
	  });
	  
	  var needInvitees_a = false;
	  var needInvitees_b = false;
	  var needInvitees_c = false;
	  
	  function getDetails(){
           $("#resultTableDivAjax").show();
		   $("#resultTableDiv").html("");
		   eventId = $("#mainEventSelectId").val();
		   var jsObj={
			   eventId:eventId
		   }
		  $.ajax({
			  type:'GET',
			  url: 'getTodayTotalAndCurrentUsersInfo.action',
			  data :{task:JSON.stringify(jsObj)}
          }).done(function(result){
			  $("#resultTableDivAjax").hide();
		       var str ="";
	           if(result != null && result.length > 0){
	        	   $("#totalVisitors").html(result[0].totalVisitors);
	        	   $("#currentVisitors").html(result[0].currentVisitors);
				   $("#lasteUpdtOn").html(result[result.length-1].lastUpdated);
				   $("#currentinvVisitors").html(result[0].currentInviteeVisitors);
				   if(result[0].currentInviteeVisitors != null && result[0].currentVisitors != null)
				     $("#currentnoninvVisitors").html(result[0].currentVisitors - result[0].currentInviteeVisitors);
				   else
					 $("#currentnoninvVisitors").html(0);
				   str+='<table class="display DataTableDiv1" cellspacing="0" width="100%">';
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
                                    str+='<th class="color-black table-color2">ATTENDED</th>';
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
						
						
						
			     
			   }
			   $("#resultTableDiv").html(str);
			      $('.DataTableDiv1').DataTable( {
					responsive: true,
					"paging":   false,
					"info":     false,
					"searching": false,
					"bSort" : false,
					"sDom": '<"top"i>rt<"bottom"flp><"clear">',
					"columnDefs": [
					{ "width": "25%", "targets": 0 }]
				} );
					
					$('.ScrollDiv1,.linkinner').slimScroll({
					height: '390px'
					});
					$('.ScrollDiv2').slimScroll({
					height: '260px'
					});
					$('.ScrollDiv').slimScroll({
					height: '375px'
					});
	      });
	  }
	  //getDetails();
	  function populate(){
		  $.ajax({
			  type:'GET',
			  url: 'populateMahanaduLatestInfoAction.action'
          }).done(function(result){
			  
		  });
	  }
	  
 $("#myonoffswitchSummary").click(function(){
	if($('#myonoffswitchSummary').is(":checked")){
		$('#myonoffswitchSummary1').prop('checked', true);
		$("#districtHeadingSummary").html("AP DISTRICT WISE");
		$("#constiHeadingSummary").html("AP CONSTITUENCY WISE");
		getLocationWiseAttendeeSummaryCount(1,3,false);
		getLocationWiseAttendeeSummaryCount(1,4,false);
	}else{
		$('#myonoffswitchSummary1').prop('checked', false);
		$("#districtHeadingSummary").html("TS DISTRICT WISE");	
		$("#constiHeadingSummary").html("TS CONSTITUENCY WISE");
		getLocationWiseAttendeeSummaryCount(36,3,false);
		getLocationWiseAttendeeSummaryCount(36,4,false);
	}
}); 
$("#myonoffswitchSummary1").click(function(){
	if($('#myonoffswitchSummary1').is(":checked")){
	getLocationWiseAttendeeSummaryCount(1,4,false);
	$("#constiHeadingSummary").html("AP CONSTITUENCY WISE");
	}else{
	getLocationWiseAttendeeSummaryCount(36,4,false);
	$("#constiHeadingSummary").html("TS CONSTITUENCY WISE");
	}
});


//getLocationWiseAttendeeSummaryCount(1,3,false);
//getLocationWiseAttendeeSummaryCount(1,4,false);
function getLocationWiseAttendeeSummaryCount(stateId,reportLevelId,fromChecked){
	
	var subEvents1 = [];
	var stateId =0;
	
	if(reportLevelId == 3){		
		$("#districtTableSummaryId").html("");//Clearing the District Div		
		$("#distAjaxSummaryId").show();
		$("#districtTableSummaryId").html("");
	}else{
		$("#constiTableSummaryId").html("");//Clearing the constituency Div
		$("#constAjaxSummaryId").show();
		$("#constiTableSummaryId").html("");
	}
	
	if(!fromChecked){
		//$("#daysSummaryIdAjax").show();
		//$("#ovrAlSummaryIdAjax").show();
		//$("#daysSummaryId").html("");
		//$("#ovrAlSummaryId").html("");
	}
	if($('#myonoffswitchSummary1').is(":checked")){
		stateId = 1;
	}else{
		stateId = 36;
	}
	
	 eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1 = [globalMainEntryId]; // -- MAHANADU MAIN ENTRY
	 }
	var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:reportLevelId,
			subEvents : subEvents1,
			//startDate : "27/05/2015",
			//endDate : "29/05/2015"
		
		}	
		
		$.ajax({
          type:'GET',
          url: 'getEventAttendeeSummaryListAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildLocationSummary(result,reportLevelId,fromChecked);
		});

}

function buildLocationSummary(result,reportLevelId,fromChecked){
	var subListLength = 0;
	var checkVar = needInvitees_b;
	
	if(reportLevelId == 4){
		checkVar = needInvitees_c;
	}
	
	if(reportLevelId == 3){
		$("#distAjaxSummaryId").hide();
	}else{
		$("#constAjaxSummaryId").hide();
	}
	
	if(!fromChecked){
		//$("#daysSummaryIdAjax").hide();
		//$("#ovrAlSummaryIdAjax").hide();
		
		var str_a = "";
		str_a += "<table class='table'>";
			str_a +="<thead>";
				str_a +="<tr style='background:#F0F0F0'>";
					str_a+="<th style='width:20%'></th>";
					str_a+="<th>TOTAL UNIQUE VISITS</th>";
					str_a+="<th>ONLY ONE DAY VISITS</th>";
					str_a+="<th>REVISITS</th>";
				str_a +="</tr>";
			str_a +="</thead>";
			str_a +="<tbody>";
				for(var i in result[0].datesList){
					str_a +="<tr>";
						str_a+="<td style='width:20%'> DAY-"+(parseInt(i)+1)+"</td>";
						str_a+="<td>"+result[0].datesList[i].total+"</td>";
						str_a+="<td>"+result[0].datesList[i].oneDayCount+"</td>";
						str_a+="<td>"+result[0].datesList[i].revisitCount+"</td>";
					str_a +="</tr>";
				}
			str_a +="</tbody>";
		str_a += "</table>";
		//$("#daysSummaryId").html(str_a);
		
		var str_b = "";
		str_b += "<table class='table'>";
			str_b +="<thead>";
				for(var i in result[0].hoursList){
					str_b +="<tr>";
						var number = parseInt(i)+1;
						if(number>1){
							str_b+="<th>"+(parseInt(i)+1)+" DAY'S VISITORS</th>";
						}else{
							str_b+="<th>"+(parseInt(i)+1)+" DAY VISITORS</th>";
						}
						
						str_b+="<th>"+result[0].hoursList[i].total+"</th>"
					str_b +="</tr>";
				}
			str_b +="</thead>";
		str_b += "</table>";
		//$("#ovrAlSummaryId").html(str_b);
	}
	var str='';
	if(reportLevelId == 3){
    str+='<div class="scrollDiv"><table  class="display" id="table'+reportLevelId+'Summary" cellspacing="0" width="100%"><thead>';
	}else{
	str+='<div class="scrollDiv1"><table  class="display" id="table'+reportLevelId+'Summary" cellspacing="0" width="100%"><thead>';
	}
	

	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th class="table-color1">DISTRICT</th>';
	}else{
	str+='<th class="table-color1">CONSTITUENCY</th>';
	}
	
	if(checkVar){
		str +='<th colspan="3" class="table-color1"> TOTAL VISITORS </th>';
	}else{
		str +='<th colspan="1" class="table-color1"> TOTAL VISITORS </th>';
	}
	
	subListLength = result[0].hoursList.length;
	for(var i in result[0].hoursList){
		var number = result[0].hoursList[i].id;
		if(number>1){
			if(checkVar){
				str +='<th  colspan="3" class="text-center table-color1"> '+result[0].hoursList[i].id+' DAYS VISITORS</th>';
			}else{
				str +='<th  colspan="1" class="text-center table-color1"> '+result[0].hoursList[i].id+' DAYS VISITORS</th>';
			}
			
		}else{
			if(checkVar){
				str +='<th  colspan="3" class="text-center table-color1"> '+result[0].hoursList[i].id+' DAY VISITORS</th>';
			}else{
				str +='<th  colspan="1" class="text-center table-color1"> '+result[0].hoursList[i].id+' DAY VISITORS</th>';
			}
			
		}
	}
	
    str+='</tr>';
	  str+='<tr>';
   	  str+=' <th class="color-black"></th>';
	  str+='<th class="color-black table-color1">ATTENDED</th>';
	  if(checkVar){
		str+='<th class="color-black table-color1 inviteesCls">INVITEES</th>';
		str+='<th class="color-black table-color1">NON INVITEES</th>';
	  }
	  for(var i in result[0].hoursList){
       str+='<th class="color-black table-color1">ATTENDED</th>';
	   if(checkVar){
		str+='<th class="color-black table-color1 inviteesCls">INVITEES</th>';
		str+='<th class="color-black table-color1">NON INVITEES</th>';
	   }
	  }
    str+='</tr>';
	 str+='</thead>';
    str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].locationName+'</td>';
		str+='<td>'+result[i].total+'</td>';
		if(checkVar){
			str+='<td class="inviteesCls">'+result[i].newCount+'</td>';
			var rCount = result[i].total-result[i].newCount;
			str+='<td>'+rCount+'</td>';
		}
		if(result[i].subList!=null && result[i].subList.length>0){
			for(var j in result[i].subList){
				str +='<td> '+result[i].subList[j].count+'</td>';
				if(checkVar){
					str +='<td class="inviteesCls"> '+result[i].subList[j].newCount+'</td>';
					var rqCount = result[i].subList[j].count - result[i].subList[j].newCount;
					str +='<td> '+rqCount+'</td>';
				}
			}
		}else{
			for(var i=0;i<subListLength;i++){
				str +='<td> - </td>';
				if(checkVar){
					str +='<td class="inviteesCls"> - </td>';
					str +='<td> - </td>';				
				}
			}
		}
		
		str+='</tr>';
    }                               
	str+='</tbody></table></div>';
	if(reportLevelId == 3){
	$("#districtTableSummaryId").html(str);
	}
	else{
	$("#constiTableSummaryId").html(str);
	}

	$('#table'+reportLevelId+'Summary').DataTable( {
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "25%", "targets": 0 }]
    } );
	/*if(reportLevelId == 3){
	$('.scrollDiv').slimScroll({
	height: '350px'
	});
	}
	else{
	$('.scrollDiv1').slimScroll({
	height: '370px'
	});
	}*/
	
}

$( "#mainEventSelectId" ).change(function() {
 getmainEventsChange();
}); 
  function getmainEventsChange(){	 
		getSubEventsOfEvent();		
		  setTimeout(function(){
				getDetails();
				getLocationWiseAttendeeSummaryCount(1,3,false);
				getLocationWiseAttendeeSummaryCount(1,4,false);
				getDaysUniqueAndRevisitSummary();
				getDayWiseVisitSummary();
			}, 3000);
   } 
	</script>
	<script type="text/javascript">
               /* $(document).ready(function() {
var stDate = 
                 $('.reservation').daterangepicker({ 
				       startDate:'27-05-2015',
					   endDate:'27-05-2015',
				       format: 'DD-MM-YYYY',
					   minDate: '27-05-2015',
					   maxDate: '29-05-2015', 
					  dateLimit: { days: 3 },
					  showWeekNumbers: false
					}, function(start, end, label) {
					});
               }); */
               
$(".tbtn").click(function(){
    $(".themeControll").toggleClass("active");
});
function getDaysUniqueAndRevisitSummary(){
	var subEvents1 = [];
	var stateId =0;
	 eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1 = [globalMainEntryId]; // -- MAHANADU MAIN ENTRY
	 }
	 $("#daysSummaryId").html('');
	 $("#daysSummaryIdAjax").show();
	 
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
			$("#daysSummaryIdAjax").hide();
			buildDaysUniqueAndRevisitSummary(result);
		});
}
function buildDaysUniqueAndRevisitSummary(result){
	var str_a = "";
	if(result !=null && result.length>0){
		
		str_a += "<table class='table m_0'>";
			str_a +="<thead>";
				str_a +="<tr style='background:#F0F0F0'>";
					str_a+="<th style='width:20%'></th>";
					str_a+="<th>TOTAL UNIQUE VISITS</th>";
					str_a+="<th>ONLY ONE DAY VISITS</th>";
					str_a+="<th>REVISITS</th>";
				str_a +="</tr>";
			str_a +="</thead>";
			str_a +="<tbody>";
				for(var i in result){
					str_a +="<tr>";
						str_a+="<td style='width:20%'> DAY-"+(parseInt(i)+1)+"</td>";
						str_a+="<td>"+result[i].total+"</td>";
						str_a+="<td>"+result[i].oneDayCount+"</td>";
						str_a+="<td>"+result[i].revisitCount+"</td>";
					str_a +="</tr>";
				}
			str_a +="</tbody>";
		str_a += "</table>";
	}
	$("#daysSummaryId").html(str_a);
	
}

function getDayWiseVisitSummary(){
	var subEvents1 = [];
	var stateId =0;
	 eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1 = [globalMainEntryId]; // -- MAHANADU MAIN ENTRY
	 }
	 $("#ovrAlSummaryId").html('');
	$("#ovrAlSummaryIdAjax").show();
	 
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
			$("#ovrAlSummaryIdAjax").hide();
			buildDayWiseVisitSummary(result);
		});
}
function buildDayWiseVisitSummary(result){
	var str_b = "";
	if(result !=null && result.length>0){
				
		str_b += "<table class='table'>";
			str_b +="<thead>";
				for(var i in result){
					str_b +="<tr>";
						var number = parseInt(i)+1;
						if(number>1){
							str_b+="<th>"+(parseInt(i)+1)+" DAY'S VISITORS</th>";
						}else{
							str_b+="<th>"+(parseInt(i)+1)+" DAY VISITORS</th>";
						}
						
						str_b+="<th>"+result[i].total+"</th>"
					str_b +="</tr>";
				}
			str_b +="</thead>";
		str_b += "</table>";		
	}
	$("#ovrAlSummaryId").html(str_b);	
}
$(document).on('click','#refreshUnqueRevisitId',function(){
	getDaysUniqueAndRevisitSummary();
});
$(document).on('click','#refreshDayWiseId',function(){
	getDayWiseVisitSummary();
});
 

</script>
</body>
</html>