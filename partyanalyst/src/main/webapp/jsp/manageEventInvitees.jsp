
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Committee Member Details</title>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="dist/js/classie.js" type="text/javascript"></script>
<script src="dist/js/selectFx.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>

<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/normalize.css" rel="stylesheet" type="text/css">
<link href="dist/css/cs-skin-slide.css" rel="stylesheet" type="text/css">
<link href="dist/css/cs-select.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
<link href="dist/MultiSelect/css/component.css" rel="stylesheet" type="text/css">
<link href="dist/css/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">


<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<!---daterangepicker---->
<link rel="stylesheet" type="text/css" media="all" href="daterangepicker/daterangepicker-bs3.css" />
	<!-- time picker -->
<link rel="stylesheet" type="text/css" media="all" href="daterangepicker/bootstrap-datetimepicker.min.css" />
	
	

<script src="dist/js/fm.scrollator.jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/exportexcel.js"></script>

<style>

*
{
	outline:none !important;
}
.bootstrap-datetimepicker-widget
{
	z-index:9999 !important;
}
.ui-dialog
{
	z-index:100000 !important;
}
.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover ,
.nav-tabs>li>a:hover
{
	background:#01b6ad;
	color:#fff;
}
.nav-tabs>li>a
{
	color:#000
}
#exampleInputName2 ,#createeventdate
{
	height:auto !important;
}
.calendar-date  .prev , .calendar-date  .next
{
	background:none !important;
}

.publickRepracentative li:first-child {
    background: none repeat scroll 0 0 #000;
    color: #fff;
    display: block;
}
.publickRepracentative li {
    background: none repeat scroll 0 0 #e9e9e9;
    border: 1px solid #ccc;
    margin: 2px;
    padding: 4px 8px;
}

.paginate_disabled_previous,.paginate_enabled_previous,.paginate_enabled_next{
   padding-bottom: 10px;
}
.prev, .next {width:70px !important}
.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > th,
.table-bordered > tfoot > tr > th,
.table-bordered > thead > tr > td,
.table-bordered > tbody > tr > td,
.table-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}

.table-yellow-bordered > thead > tr > th,
.table-yellow-bordered > tbody > tr > th,
.table-yellow-bordered > tfoot > tr > th,
.table-yellow-bordered > thead > tr > td,
.table-yellow-bordered > tbody > tr > td,
.table-yellow-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}
	.circle-text{
		line-height: 180px; font-size: 34px; top: 32px;  left: 0px; !important
	}
	.circle-info-half{
		line-height: 225px; left: 0px;  top: 34px;!important
	}
	.highlight{background-color: #fff !important;
	border-color: #ccc !important;
	color: #333 !important;}
	.navbar-nav > li > a {text-decoration:none;}
		a:hover {text-decoration:none;}
		.multiLevelLiA a{text-transform: uppercase;color:black;}
		body {
    background: url("") repeat scroll 0 0 !important;
}
#constiTableId tr.odd td.sorting_1,#districtTableId tr.odd td.sorting_1{
    background-color: #d3d3d3 !important;
}
#constiTableId tr.even td.sorting_1 , #districtTableId tr.even td.sorting_1{
    background-color: #fafafa !important;
}
#constiTableId tr.odd,#districtTableId tr.odd {
    background-color: #f3f3f3 !important;
}

.height-auto{height:auto !important;}
.height-0{height:0px !important;}

.cs-options li span {
    font-size: 65%;
    font-weight: 500;
	color:#FFDB2B;
}

.col-drop-head{font-size: 15px; font-weight: bold;}
.form-control
{
	height:34px !important;
}

</style>
<link href="dist/datetime/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
</head>


<body>

<section>
	<div class="m_top10">
    	<div class="row">
            <div class="col-md-12 col-xs-12">
            	
                <div role="tabpanel">
                <div class="panel panel-default">
                <div class="panel-heading">
                  <!-- Nav tabs -->
                  <ul class="nav nav-tabs" role="tablist">
				<!--	<li role="presentation" class=""><a href="#inviteInvitees" aria-controls="createevent" role="tab" data-toggle="tab" class="eachTabPressCls">Invite Invitees </a></li>-->
                    <li role="presentation" class="active"><a href="#createevent" aria-controls="createevent" role="tab" data-toggle="tab" class="eachTabPressCls">Create Event</a></li>
                    <li role="presentation"><a href="#eventsettings" aria-controls="eventsettings" role="tab" data-toggle="tab" class="eachTabPressCls">Event Settings</a></li>
                    <li role="presentation"><a href="#createuser" aria-controls="createuser" role="tab" data-toggle="tab" class="eachTabPressCls">Create Tab Users For Event Management</a></li>
					<li role="presentation"><a href="#updateEvent" aria-controls="createuser" role="tab" data-toggle="tab" class="eachTabPressCls">Update Event </a></li>
					<!-- <li role="presentation"><a href="#assignEvents" aria-controls="assignEvebt" role="tab" data-toggle="tab">Assign Event </a></li> -->
                  </ul>
                </div>
                <div class="panel-body">
                  <!-- Tab panes -->
                  <div class="tab-content">
				  <div role="tabpanel" class="tab-pane " id="inviteInvitees">
                   

<div aria-multiselectable="true" role="tablist" id="" class="panel-group">
      <div class="panel panel-default" style="margin-top: 25px;">

          <div class="panel-body">
							
				<div class="col-md-3" id="levelDivId">
					<section>
						<label class="select-label">Level</label> 
						<select class="cs-select cs-skin-slide" id="levelId">
						   <option value="0" >ALL</option>							  
						</select>
					</section>
				</div>
				
				
				<div class="col-md-3 locationsCls" id="districtDiv">
					<section>
							<label class="select-label">District</label>
							<select class="cs-select cs-skin-slide" id="districtId">
							   <option value="0" >ALL</option>
							  
							</select>
					</section>
				</div>
				
				<div class="col-md-2 locationsCls" id="constituencyDiv">
					<section>
							<label class="select-label">Assembly</label>
							<select class="cs-select cs-skin-slide">
								<option value="0" >ALL</option>
							   
							</select>
					</section>
				</div>
				<div class="col-md-2 locationsCls" id="mandalDiv">
					<section>
							<label class="select-label">Mandal/Muncipality</label>
							<select class="cs-select cs-skin-slide">
								<option value="0">ALL</option>
													  
							   
							</select>
					</section>
				</div>
				<div class="col-md-2 locationsCls" id="panchayatDiv">
					<section>
							<label class="select-label">Panchayat</label>
							<select class="cs-select cs-skin-slide">
								<option value="0" >ALL</option>
							   
							</select>
					</section>
				</div>
				
				<div class="col-md-12 m_top20">
				
					  
					  <label class="checkbox-inline">
						<input type="radio" id="cadreCommitteeId" name="searchBy" checked="true" value="2" onclick="getDetails('cadreCommitteeId');"> Cadre Committee 
					  </label>
					  <label class="checkbox-inline">
						<input type="radio" id="publicRepresentativesId" name="searchBy" value="1" onclick="getDetails('publicRepresentativesId');">  Public Repracentative
					  </label>
					  <!-- <label class="checkbox-inline">
						<input type="checkbox" id="groupId" value="3" onclick="getDetails('groupId');"> Existing Groups
					  </label>
					  -->
				  
				</div>
			   
				<div class="col-md-4 m_top20" style="background-color:#FFF;" id="cadreCommitteeDiv">
				
					<div  id="committeeDiv">
					<label class="select-label" style="margin-left:0px "> From  Cadre Committee </label>
					<select class="cs-select cs-skin-slide">
						<option value="0" >ALL</option>
					</select>
					</div>
					
					<div>
						<form class="me-select">
							<ul id="me-select-list" class="">
								<li class="">
									<input id="checkAll" class="" type="checkbox" name="cb10" >
									<label class="m_0" style="padding:0px 10px 0px 45px;margin-top:10px;" for="cb10">
										<span class="text-col-head" id="checkText">Select All</span>
									</label>
								</li>
							</ul>
						</form>
					<hr class="m_0" style="border-top:1px solid #01b6ad;">
					<form class="me-select" id="rolesForm">
						
					</form>
				  </div>
					<!--<div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>-->
				</div>
				
				<div class="col-md-4 m_top20" style="background-color:#FFF;display:none;" id="representativesDiv">
					<div  id="committeeDiv">					
					<label class="select-label" style="margin-left:0px "> From Public Representatives </label>					
					</div>
					
					<div>
					<form class="me-select">
					<ul id="me-select-list" class="">
					<li class="">
					<input id="checkPRAll" class="" type="checkbox" name="cb10" >
					<label class="m_0" style="padding:0px 10px 0px 45px;margin-top:10px;" for="cb10">
					<span class="text-col-head" id="checkText">Select All</span>
					</label>
					</li>
					</ul>
						</form>
					<hr class="m_0" style="border-top:1px solid #01b6ad;">
					<form class="me-select" id="representativesForm">
						
					</form>
				  </div>
					<!--<div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>-->
				</div>
				
				<div class="col-md-4 m_top20" style="background-color:#FFF;display:none;" id="existingGroupsDiv" >
					<div  id="committeeDiv">
					<label class="select-label" style="margin-left:0px ">From  Existing Groups  </label>
					<select class="cs-select cs-skin-slide">
						<option value="0" >ALL</option>
					</select>
					</div>
					
					<div>
					<form class="me-select">
					<ul id="me-select-list" class="">
					<li class="">
					<input id="checkEGAll" class="" type="checkbox" name="cb10" >
					<label class="m_0" style="padding:0px 10px 0px 45px;margin-top:10px;" for="cb10">
					<span class="text-col-head" id="checkText">Select All</span>
					</label>
					</li>
					</ul>
						</form>
					<hr class="m_0" style="border-top:1px solid #01b6ad;">
					<form class="me-select" id="rolesForm">
						
					</form>
				  </div>
					<!--<div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>-->
				</div>
				
				<div class="col-md-8 m_top20" id="buildSelectionBlockDiv" style="display:none;">
						<div class="border-box" >
							
							<div class="panel panel-default" id="stateLevelId" style="display:none;">
								<div class="panel-heading"  style="background:#01B6AD;">
									From Cadre Committee 
								</div>								
							</div>
							
							<div class="panel panel-default" id="stateLevelCommitteId" style="display:none;">
								<div class="panel-heading">
									State Level
								</div>
								<div class="panel-body">
									<div class="panel-group" id="CSaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>
							
							<div class="panel panel-default" id="districtLevelCommitteId" style="display:none;">
								<div class="panel-heading">
									District Level
								</div>
								<div class="panel-body">
									<div class="panel-group" id="CDaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>
							
							<div class="panel panel-default" id="mandalLevelCommitteId" style="display:none;">
								<div class="panel-heading">
									Mandal/Muncipality Level
								</div>
								<div class="panel-body">
									<div class="panel-group" id="CMaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>
							
							<div class="panel panel-default" id="villageLevelCommitteId" style="display:none;">
								<div class="panel-heading">
									Village/Ward Level
								</div>
								<div class="panel-body">
									<div class="panel-group" id="CVaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>	
							<div class="panel panel-default" id="stateLevelHeadingId" style="display:none;">
								<div class="panel-heading" style="background:#01B6AD;">
									From Public Representatives
								</div>								
							</div>
							
							<div class="panel panel-default" id="stateLevelPRId" style="display:none;">
								<div class="panel-heading">
									State Level 
								</div>
								<div class="panel-body">
									<div class="panel-group" id="PRSaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>
							
							<div class="panel panel-default" id="districtLevelPRId" style="display:none;">
								<div class="panel-heading">
									District Level
								</div>
								<div class="panel-body">
									<div class="panel-group" id="PRDaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>
							
							<div class="panel panel-default" id="mandalLevelPRId" style="display:none;">
								<div class="panel-heading">
									Mandal/Municipality Level 
								</div>
								<div class="panel-body">
									<div class="panel-group" id="PRMaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>
							
							<div class="panel panel-default" id="villageLevelPRId" style="display:none;">
								<div class="panel-heading">
									Village/Ward Level 
								</div>
								<div class="panel-body">
									<div class="panel-group" id="PRVaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
								</div>
							</div>	
							<div class="panel panel-default" id="">
								<div class="panel-heading">
									<button onclick="getMembersDetails(0,'none');" class="btn btn-success btn-xs"> Get Details </button>
								</div>
							</div>
						</div>
				</div>
				
				<div class="col-md-12 m_top20" id="candidateDetailsDiv" style="display:none;">
						
						<div class="border-box">
							<!--<div class="row">
								<div id="countDiv" style="display:none"></div>
							</div>-->
							<div class="panel panel-default">
							
							<div class="row">
								
								<div class="col-md-3" id="publicRepresentsId" style="display:none"></div>
								<div class="col-md-3" id="stateMembersUl" style="display:none"></div>
								<div class="col-md-3" id="districtMembersUl" style="display:none"></div>
								<div class="col-md-3" id="mandalMembersUl" style="display:none"></div>
								<div class="col-md-3" id="villageMembersUl" style="display:none" ></div>
							</div>	
							<hr style="border: 1px solid; color: #01b6ad;">
							 <div class="row">
								<div class="col-md-12 col-xs-12 col-md-12">
								<center><img id="summaryAjax" style="width:100px;height:80px;display:none;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>
								<div id="topPaginationDivId" class="paginationDivId" style="margin-top:10px;width:600px;margin-left:380px;"></div>
								<div id="buildSearchDetailsStateId"  class="panel panel-success" style="margin-top: 40px;"></div>	
								</div>
								<div id="paginationDivId" class="paginationDivId" style="width:600px;margin-left:380px;"></div>
							</div>
							
							
							
							
							<div class="download-box display-style" style="display:none;">
								<div class="display-style">
									<span class="text-italic text-bold" style="padding-left:10px">Download Now</span><br>
									<div style="padding-top:10px">
										<div class="download-image display-style">
											<img src="dist/images/pdf.png">
											<span>PDF</span>
										</div>
										<div class="download-image display-style">
											<img src="dist/images/word.png">
											<span>Word</span>
										</div>
										<div class="download-image display-style">
											<a href="javascript:{exportConstituencyToExcel('searchTableId');}" ><img src="dist/images/excel.png" >
											<span >Excel</span></a>
										</div>
									</div>
								</div>
								<img src="dist/images/or.png">
								<div class="pull-right" style="padding-top:10px;padding-left:10px">
									<span>ADD TO GROUP</span><br/>
										<div class="input-group pull-right" style="width:300px;">
										  <input type="text" id="groupNameId" class="form-control" placeholder="Enter Group Name" aria-describedby="basic-addon2" style="height: 33px;"/>
										  <span class="input-group-addon" id="addGroupId" style="background-color:#01b6ad;color:#fff;cursor:pointer" onclick="getMembersDetails();">ADD TO GROUP</span>
										</div><br/><br/>
									<span class="viewlink">Click To View Existed group Names</span>    
								</div>
							</div>
							
						</div>
						
						</div>
				</div>
				  </div>
			  </div>
		
    </div>

	<!---<div class="well br_0 container m_top30" style="background-color:#fff">
    	<h3 class="m_0 text-left">COMMITTEE MEMBER DETAILS <span aria-hidden="true" class="rotate pull-right glyphicon glyphicon-menu-down"></span></h3>
		
    </div> ---->
	<div id="smsDialogueBoxDiv">
		<div id="smsMessageDiv" style="display:none;"> 				
		</div>
	</div>
	
	<div id="inviteMembersDiv">
		<div id="dialogueForInviteId" class="text-center" style="display:none;"> 	
			
		</div>
		</div>

	<div id="eventCretionDialogDiv">
		<div id="eventCretionDiv" class="text-center" style="display:none;"> 	
			
		</div>
		</div>
	
	
                   </div>
				 <!--  <div role="tabpanel" class="tab-pane " id="assignEvents">
                        <div class="row">
							<div class="col-md-4">
								<label>Event Name</label>
								 <select class="form-control" id="EventsListEdit">
									<option value="0"> Select Event </option>
								  </select>
							</div>
							<div class="col-md-4">
								<label> Select User </label>
								<select class="form-control" id="usersList">
									<option value="0"> Select User </option>
								</select>
							</div>							
						</div>
						
                        <div class="col-md-3 col-md-offset-5 m_top10" style="margin-bottom:10px;">
                        	<button class="btn btn-primary">ASSIGN EVENT</button>
                        </div>
                    </div>  -->
					<div role="tabpanel" class="tab-pane " id="updateEvent" style="overflow:hidden;">
                        <div class="row">
						<div id="errorDivIdEdit" style="color:red;margin-left:15px;"></div>
						<div class="col-md-4">
                        	<label>Event Name</label>
                        	 <select class="form-control clearSelectAtUpdationEvent" id="EventsListEdit">
                              </select>
                        </div>
                        <div class="col-md-4">
                        	<label>Description</label>
                            <input class="form-control clearFieldsAtUpdationEvent" type="textarea" id="descriptionEdit"></input>
                        </div>
						<div class="col-md-4">
                                <label>Entry Limit</label>
                                <input class="form-control clearFieldsAtUpdationEvent" id="entryLimitEdit"/>
                            </div>
						</div>
						<div class="row">
                        <div class="col-md-4 m_top10">
                        	<label>Event Start Date &amp; Time</label>
                        	<div class="form-group">
                                <div class='input-group date' >
                                    <input type='text' class="form-control eventstart clearFieldsAtUpdationEvent" id="startDateEdit"/>
                                    <span class="input-group-addon" style="cursor:default;">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                       <div class="col-md-4 m_top10">
                        	<label>Event Start Time In A Day</label>
                        	<div class="form-group">
                                <div class='input-group date' >
                                    <input type='text' class="form-control eventdaytime clearFieldsAtUpdationEvent" id="startTimeEdit"/>
                                    <span class="input-group-addon" style="cursor:default;">
                                        <span class="glyphicon glyphicon-time"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 m_top10">
                        	<label>Event End Time In A Day</label>
                        	<div class="form-group">
                                <div class='input-group date' >
                                    <input type='text' class="form-control eventendtime clearFieldsAtUpdationEvent" id="endTimeEdit"/>
                                    <span class="input-group-addon" style="cursor:default;">
                                        <span class="glyphicon glyphicon-time"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
						</div>
						
                        <div class="row">    
						 
                        	<div class="col-md-3 m_top10" style="">
                            	<label>Server Work Mode</label><br/>
                                <label class="radio-inline col-md-1">
                                  <input type="radio" name="serverWorkModeEdit" id="serverWorkMode1Edit" value="ONLINE" checked="true" class="updateEventServerModeCls clearRadioOfCreateEventEdit"> Online 
                                </label>
                                <label class="radio-inline col-md-1"  style="margin-left: 29px">
                                  <input type="radio" name="serverWorkModeEdit" id="serverWorkMode2Edit" value="OFFLINE" class="updateEventServerModeCls"> Offline	
                                </label>
                            </div>
                            <div class="col-md-2 m_top10">
                            	<label>Tab Work Mode</label><br/>
                                <label class="radio-inline col-md-1">
                                  <input type="radio" name="tabWorkModeEdit" id="tabWorkMode1Edit" value="ONLINE" checked="true" class="clearRadioOfCreateEventEdit"> Online 
                                </label>
                                <label class="radio-inline col-md-1"  style="margin-left: 29px">
                                  <input type="radio" name="tabWorkModeEdit" id="tabWorkMode2Edit" value="OFFLINE"> Offline	
                                </label>
                            </div>
							<div class="col-md-4 btn-info m_top10" style="padding:10px;border-radius: 5px;">
                                <label class="radio-inline" style="font-weight:bold;">
                                  <input type="radio" name="inlineRadioOptionsEdit" id="inlineRadio1Edit" value="1" checked="true" onclick="updateEventListEdit(1,'mainEventsDivEdit');"> Main Event
                                </label>
                                  <label class="radio-inline" style="font-weight:bold;">
                                  <input type="radio" name="inlineRadioOptionsEdit" id="inlineRadio2Edit" value="2" onclick="updateEventListEdit(2,'mainEventsDivEdit');"> Sub Event
                                </label>
                            </div>   
							<div class="col-md-4 " style="display:none;" id="mainEventsDivEdit">
                            	<label>Select Main Event</label>
                                <select class="form-control" id="evnetsListEdit">
                                    <option></option>
                                </select>
                            </div>							
                        </div>	 
                      
                        <div class="col-md-3 col-md-offset-5 m_top10" style="margin-bottom:10px;">
                        	<button class="btn btn-primary" onClick="creatNewEvent('updateEvent')">CREATE EVENT</button>
                        </div>
					<!--<span style="color:red" id="errorDivIdEdit"></span>-->
                    </div>
                    <div role="tabpanel" class="tab-pane active" id="createevent" style="overflow:hidden;">
                        <div class="row">
						<div id="errorDivId" style="color:red;margin-left:15px;"></div>
						<div class="col-md-4">
                        	<label>Event Name</label>
                        	<input type="text" class="form-control clearCreateEventFields" id="eventNameId">
                        </div>
                        <div class="col-md-4">
                        	<label>Description</label>
                            <input class="form-control clearCreateEventFields" type="textarea" id="description"></input>
                        </div>
						<div class="col-md-4">
                                <label>Entry Limit</label>
                                <input class="form-control clearCreateEventFields" id="entryLimit"/>
                            </div>
						</div>
						<div class="row">
                        <div class="col-md-4 m_top10">
                        	<label>Event Start Date &amp; Time</label>
                        	<div class="form-group">
                                <div class='input-group date' >
                                    <input type='text' class="form-control eventstart clearCreateEventFields" id="startDate"/>
                                    <span class="input-group-addon" style="cursor:default;">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                       <div class="col-md-4 m_top10">
                        	<label>Event Start Time In A Day</label>
                        	<div class="form-group">
                                <div class='input-group date' >
                                    <input type='text' class="form-control eventdaytime clearCreateEventFields" id="startTime"/>
                                    <span class="input-group-addon" style="cursor:default;">
                                        <span class="glyphicon glyphicon-time"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 m_top10">
                        	<label>Event End Time In A Day</label>
                        	<div class="form-group">
                                <div class='input-group date' >
                                    <input type='text' class="form-control eventendtime clearCreateEventFields" id="endTime"/>
                                    <span class="input-group-addon" style="cursor:default;">
                                        <span class="glyphicon glyphicon-time"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
						</div>
						
                        <div class="row">    
						 
                        	<div class="col-md-2 m_top10" style="">
                            	<label>Server Work Mode</label><br/>
                                <label class="radio-inline  col-md-1">
                                  <input type="radio" name="serverWorkMode" id="serverWorkMode1" value="ONLINE" checked="true" class="newEventServerModeCls clearRadioOfCreateEvent"> Online 
                                </label>
                                <label class="radio-inline  col-md-1" style="margin-left: 29px">
                                  <input type="radio" name="serverWorkMode" id="serverWorkMode2" value="OFFLINE" class="newEventServerModeCls"> Offline	
                                </label>
                            </div>
                            <div class="col-md-2 m_top10">
                            	<label>Tab Work Mode</label><br/>
                                <label class="radio-inline  col-md-1">
                                  <input type="radio" name="tabWorkMode" id="tabWorkMode1" value="ONLINE" checked="true" class="clearRadioOfCreateEvent"> Online 
                                </label>
                                <label class="radio-inline  col-md-1"  style="margin-left: 29px">
                                  <input type="radio" name="tabWorkMode" id="tabWorkMode2" value="OFFLINE"> Offline	
                                </label>
                            </div>
							<div class="col-md-4 btn-info m_top10" style="padding:10px;border-radius: 5px;">
                                <label class="radio-inline" style="font-weight:bold;">
                                  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1" checked="true" onclick="updateEventList(1,'mainEventsDiv');"> Main Event
                                </label>
                                  <label class="radio-inline" style="font-weight:bold;">
                                  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2"  onclick="updateEventList(2,'mainEventsDiv');"> Sub Event
                                </label>
                            </div>   
							<div class="col-md-4 " style="display:none;" id="mainEventsDiv">
                            	<label>Select Main Event</label>
                                <select class="form-control" id="mainEvenmtsLits">
                                    <option></option>
                                </select>
                            </div>							
                        </div>	 
                      
                        <div class="col-md-3 col-md-offset-5 m_top10" style="margin-bottom:10px;">
                        	<button class="btn btn-primary" onclick="creatNewEvent('newEvent')">CREATE EVENT</button>
                        </div>
						<!--<span style="color:red" id="errorDivId"></span>-->
                    </div>
                    <div role="tabpanel" class="tab-pane" id="eventsettings" style="overflow:hidden;">
                    	<div class="row">
                            <div class="col-md-6">
                                <label>Select Event</label>
                                <select class="form-control clearEventSetFieldsCls" id="evnetsList">
                                    <option></option>
                                </select>
                            </div>
						</div>
						<div class="row m_top10" class="eventSetRFIDdivCls">
                            <div class="col-md-3">
                                <label>RFID Operation</label>
                                <select class="form-control clearEventSetFieldsCls rfidCls" textId="regTextId" sectorId ="sectorsList" blockId="blockList" id="rfidList">
                                    <option value="0"> Select RFID Operation</option>
                                    <option value="READ"> READ</option>
                                    <option value="WRITE"> WRITE</option>
                                    
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label>Reg Text</label>
									<input class="form-control clearEventSetTextFieldsCls" id="regTextId"/>
                            </div>
                        

                        	 <div class="col-md-3">
                                <label>Sector No</label>
                               <select class="form-control clearEventSetFieldsCls" id="sectorsList">
                                    <option value="0"> Select Sector No</option>
                                    <option value="1"> 1</option>
                                    <option value="2"> 2</option>
                                    <option value="3"> 3</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label>Block No</label>
                                 <select class="form-control clearEventSetFieldsCls" id="blockList">
                                    <option value="0"> Select Block No </option>
                                    <option value="1"> 1</option>
                                    <option value="2"> 2</option>
                                    <option value="3"> 3</option>
                                </select>
                            </div>
							<div class="col-md-1" style="cursor:pointer;color:green;">
								<span class="glyphicon glyphicon-plus divIncrementCls" style="margin-top: 30px;"></span>
							</div>
						</div>
						
						<!-- adding same div (Like Cloning)  -->
							<div class="row" id="eventSetRFIDdivClsAdd"></div>
						<!-- /adding div  -->
						
                        <div class="col-md-3 col-md-offset-5 m_top10">
                        	<button class="btn btn-primary" id="savingEventSetttings">SAVE</button>
                        </div>
                    </div>
					
					
                    <div role="tabpanel" class="tab-pane" id="createuser" style="overflow:hidden;">
                    	<div class="row">
						<div id="errorDivForUser" style="margin-left:15px;" ><b>Note :</b><span style="color:green;"> Password Should Be AlphaNumeric And Minimum of 8 characters.</span></div>
                        	<div class="col-md-6">
                            	<span style="color:red">*</span><label>First Name</label><span class="userErrorDetailsCls" id="firstNameErrorId" style="color:red;"></span>
                                <input class="form-control clearFiedsForUser" type="text" id="firstName">
                            </div>
                            <div class="col-md-6">
                            	<label>Last Name</label>
                                <input class="form-control clearFiedsForUser" type="text" id="lastNmae">
                            </div>
                            <div class="col-md-6">
                            	<span style="color:red">*</span><label>Mobile No</label><span class="userErrorDetailsCls" id="mobileNoErrorId" style="color:red;"></span>
                                <input class="form-control clearFiedsForUser" type="text" id="mobileno">
                            </div>
                            <div class="col-md-6">
                            	<span style="color:red">*</span><label>User Name</label><span class="userErrorDetailsCls" id="userNameErrorId" style="color:red;"></span>
                                <input class="form-control clearFiedsForUser" type="text" id="userName">
                            </div>
                            <div class="col-md-6">
                            	<span style="color:red">*</span><label>Password</label><span class="userErrorDetailsCls" id="passwordErrorId" style="color:red;"></span>
                                <input class="form-control clearFiedsForUser" type="password" id="passwordId">
                            </div>
                            <div class="col-md-3 col-md-offset-5 m_top10">
                        	<button class="btn btn-primary" id="careateANewUserForEvents">CREATE USER</button>
                        </div>
                        </div>
                    </div>
                  </div>
                </div>
                </div>
                </div> 
           </div> 
        </div>
   </div>  
</section>

<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<!--moment -->
	<script type="text/javascript" src="daterangepicker/moment.js"></script>
	<!--daterangepicker -->
	<script type="text/javascript" src="daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript" src="daterangepicker/bootstrap-datetimepicker.min.js"></script>
	
<script type="text/javascript">
               $(document).ready(function() {
				   $('.eventstart').daterangepicker(null, function(start, end, label) {
                   console.log(start.toISOString(), end.toISOString(), label);
                 });
				   $('#myTab a').click(function (e) {
					  e.preventDefault()
					  $(this).tab('show')
					})
			   });
			    $(function () {
               
				$('.eventdaytime').datetimepicker({
					format: "LT"
                });
				$('.eventendtime').datetimepicker({
					format: "LT"
                });
            });
</script>


<script src="dist/js/classie.js" type="text/javascript"></script>
<script src="dist/js/selectFx.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>
<script src="dist/js/fm.scrollator.jquery.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	
	<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination_1.css"/>
	
		<script>
			(function() {
				[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
					new SelectFx(el);
				} );
				
				$(".cs-placeholder").click(function(){
					$(".scrollbar").scrollator({
					zIndex: '10000',
					});
					
				});
	//$(".scrollbar").niceScroll({touchbehavior:false,cursorcolor:"#FFF",cursoropacitymax:0.7,cursorwidth:6,autohidemode:true,zindex:"12000",oneaxismousemode:false});
						
				/*Inputs Multi Select*/
				var selList = document.getElementById( 'me-select-list' ),
					items = selList.querySelectorAll( 'li' );
				
				// fill the initial checked elements (mozilla)
				[].slice.call( items ).forEach( function( el ) {
					el.parentNode.className = el.checked ? 'selected' : '';
				} );

				function checkUncheck( el ) {
					el.parentNode.className = el.checked ? '' : 'selected';
					el.checked = !el.checked;
				}

				new magicSelection( selList.querySelectorAll( 'li > input[type="checkbox"]' ), {
					onSelection : function( el ) { checkUncheck( el ); },
					onClick : function( el ) {
						el.parentNode.className = el.checked ? 'selected' : '';
					}
				} );
			
				
			})();
			
		</script>
<script type="text/javascript">

 var locationsArr = new Array();
 var glstateId = 0;
 var gldistrictId = 0;
 function getDistricts(){
	  $("#districtId").find('option').remove();
	 
     var jsObj=
		{				
				stateId:1,
				stateTypeId :1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForUserAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   var str ='';
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
   if(result != null)
   {
	   if(result.length == 11)
	   {
		   glstateId=2;
	   }
	   else if(result.length == 14)
	   {
		   glstateId=1;
	   }
	   else if(result.length == 2)
	   {
		   gldistrictId=result[1].id;
	   }	   
   }
			str+='<section>';
          str+='<label class="select-label">District</label>';
		  str+=' <div class="cs-select cs-skin-slide distSlide" tabindex="0" onclick="selectChange(\'distSlide\')">';
		  str+='<span class="cs-placeholder distName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar distList">';
		   str+='<li data-value="0" data-option="" class="distEle"><span>ALL</span></li>';
		   //str+='<li data-value="AP" data-option="" class="distEle"><span>AP DISTRICTs </span></li>';
		  // str+='<li data-value="TS" data-option="" class="distEle"><span>TS DISTRICTS </span></li>';
		   
		  for(var i in result)
		  {
		   if(result[i].id > 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="distEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="districtId">';
		  for(var i in result)
		  {
		   if(result[i].id > 0)
          str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		  }
		 str+='</select></div></section>';
		$("#districtDiv").html(str);
		
		/*str+='<select class="cs-select cs-skin-slide">';
       str+='<option value="district">District</option>';
       str+='<option value="mandal">Mandal</option>';
       str+='</select>';
	   str+='</div>';*/
	   
	   /*if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }*/
	 
   });
  }

  var isEntered=false;
  function getConstituenciesForDistricts(districtId){
  
  if(!isEntered)
  {
	isEntered = true;
	  setDefault();
		$("#constituencyId").find('option').remove();
		//var districtId =  $("#districtId").val();
		var jsObj=
	   {				
					districtId:districtId,
					elmtId:"districtList_d",
					type:"default",
					task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesForADistrictAjaxAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   isEntered = false;
	   var str='';
		   str+='<section>';
			  str+='<label class="select-label">Assembly</label>';
			  str+=' <div class="cs-select cs-skin-slide constiSlide" tabindex="0" onclick="selectChange(\'constiSlide\')">';
			  str+='<span class="cs-placeholder constiName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar constiList">';
			  str+='<li data-value="0" data-option="" class="constiEle"><span>ALL</span></li>';
			  for(var i in result)
			  {
			   if(result[i].id > 0)
			  str+='<li data-value="'+result[i].id+'" data-option="" class="constiEle"><span>'+result[i].name+'</span></li>';
			  }
			  str+='</ul>';
			  str+='</div><select class="cs-select cs-skin-slide" id="constituencyId">';
			  for(var i in result)
			  {
			   if(result[i].id > 0)
			  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			  }
			 str+='</select></div></section>';
			$("#constituencyDiv").html(str);
		
		 /*for(var i in result){
		   if(result[i].id == 0){
			  $("#constituencyId").append('<option value='+result[i].id+'>Select Constituency</option>');
		   }else{
			  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }*/
	   });
  }
	
  }

  function getTehsils(constiId){
	  if(!isEntered)
	  {
		isEntered = true;
		  $("#mandalId").find('option').remove();
			//var constiId =  $("#constituencyId").val();

			  if(constiId == 0){
				return;
			  }
			  var jsObj=
				{
					id:constiId,
					task:"subRegionsInConstituency",
					taskType:"",
					selectElementId:"",
					address:"",
					areaType:"null",
					constId:constiId				
				}
			$.ajax({
				  type:'GET',
				  url: 'locationsHierarchiesAjaxAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   isEntered = false;
		   var str='';
			   str+='<section>';
				  str+='<label class="select-label">Mandal/Muncipality</label>';
				  str+=' <div class="cs-select cs-skin-slide mandalSlide" tabindex="0" onclick="selectChange(\'mandalSlide\')">';
				  str+='<span class="cs-placeholder mandalName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar mandalList">';
				   str+='<li data-value="0" data-option="" class="mandalEle"><span>ALL</span></li>';
				  for(var i in result)
				  {
				  if(result[i].id > 0)
				  str+='<li data-value="'+result[i].id+'" data-option="" class="mandalEle"><span>'+result[i].name+'</span></li>';
				  }
				  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="mandalId">';
				  for(var i in result)
				  {
				   if(result[i].id > 0)
				  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				  }
				 str+='</select></div></section>';
				$("#mandalDiv").html(str);
			
			/* for(var i in result){
			   if(result[i].id == 0){
				  $("#mandalId").append('<option value='+result[i].id+'>Select Mandal</option>');
			   }else{
				  $("#mandalId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }*/
		   });	
	  }
  }
    function getPanchayats(mandalId){
		
		if(!isEntered)
		{
			isEntered = true;
			 $("#panchayatId").find('option').remove();
			//var mandalId =  $("#mandalId").val();
			  if(mandalId == 0){
				return;
			  }
			  var jsObj={
						mandalId :mandalId
					}
			 $.ajax({
						type:"POST",
						url :"getPanchayatDetailsAction.action",
						 dataType: 'json',
						data: {task:JSON.stringify(jsObj)}
					}).done(function(result){
						isEntered = false;
			  var str='';
			   str+='<section>';
				  str+='<label class="select-label">Panchayat</label>';
				  str+=' <div class="cs-select cs-skin-slide panchayatSlide" tabindex="0" onclick="selectChange(\'panchayatSlide\')">';
				  str+='<span class="cs-placeholder panchayatName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar panchayatList">';
				  str+='<li data-value="0" data-option="" class="panchayatEle"><span>ALL</span></li>';
				  for(var i in result)
				  {
				   if(result[i].id > 0)
				  str+='<li data-value="'+result[i].id+'" data-option="" class="panchayatEle"><span>'+result[i].name+'</span></li>';
				  }
				  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="panchayatId">';
				  for(var i in result)
				  {
					if(result[i].id > 0)
				  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				  }
				 str+='</select></div></section>';
				$("#panchayatDiv").html(str);
				
			/* $("#panchayatId").append('<option value="0">Select Panchayat/Ward</option>');
			 for(var i in result){
			   $("#panchayatId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }*/
		   });
		}
  } 

    
 /* function getdistricts(){
	var selState = $("#stateId").val();
	var jsObj={
			stateid:selState
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			 $("#districtId").append('<option value="0">Select District</option>');
			 for(var i in result){
			   $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }
	   });	
	
  }*/

    function getCommittees(){
	isEntered=false;
	var jsObj={
			task:"allCommittess"
		}
		$.ajax({
			  type:'GET',
			  url: 'getAllCommitteesAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	     var str='';
	   str+='<section>';
          str+='<label class="select-label">From  Cadre Committee</label>';
		  str+=' <div class="cs-select cs-skin-slide committeeSlide" tabindex="0" onclick="selectChange(\'committeeSlide\')">';
		  str+='<span class="cs-placeholder committeeName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar comitteeList">';
		  str+='<li data-value="0" data-option="" class="committeeEle"><span value="0">ALL</span></li>';
		  for(var i in result)
		  {
		  if(result[i].id > 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="committeeEle"><span value="'+result[i].id+'">'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="committeeId">';
		  for(var i in result)
		  {
		   if(result[i].id > 0)
          str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		  }
		 str+='</select></div></section>';
		$("#committeeDiv").html(str);
			 /*$("#committeeId").append('<option value="0">Select Committee</option>');
			 for(var i in result){
			   $("#committeeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }*/
	   });	
	
  }
    function getCommitteeRoles(){
    	
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		str+='<ul id="me-select-list" >';
			for(var i in result)
			{
				str+='<li><input id="role'+result[i].id+'" name="'+result[i].name+'" type="checkbox" value="'+result[i].id+'" class="roleCheck" onclick="addCommitteeDivs(\'roleCheck\',\'CadreMembers\');"><label for="cb10"  style="padding:0px 10px 0px 45px;margin-top:10px;"><span class="roleName">'+result[i].name+'</span></li>';
			}
           str+=' </ul>';
		    $("#rolesForm").html(str);
    	   });	
		  
    	
      }
	  
	  
  function setDefault()
  {
	  $("#constituencyId").find('option').remove();
	  $("#mandalId").find('option').remove();
	  $("#panchayatId").find('option').remove();
	  $("#constituencyId").append('<option value=0>Select Constituency</option>');
	  $("#mandalId").append('<option value=0>Select Mandal</option>');
	  $("#panchayatId").append('<option value=0>Select Panchayat</option>');
  }
  /*$(".distSlide div").click(function(){
			alert('a');
//$(this).addClass("cs-active");
			});*/
			function selectChange(divEle)
			{

			$(".roleCheck").prop('checked', false);
			$("#checkAll").prop('checked', false);
			
			$(".rolePRCheck").prop('checked', false);
			$("#checkPRAll").prop('checked', false);
			
			$(".scrollbar").scrollator({
					zIndex: '10000',
					});
			if($("."+divEle).hasClass("cs-active"))
				$("."+divEle).removeClass("cs-active");
			else
				$("."+divEle).addClass("cs-active");
		
			$(".stateEle").click(function(e)
			 {			
				$(".stateName").html($(this).text());
				$(".stateName").attr("value",$(this).attr("data-value"));
				
				$(".stateEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				
				var levelId = $(".stateName").attr('value');
				//8888
				//$('.locationsCls').hide();
			/*	if(levelId == 10)
				{
					clearData('constituencyDiv');
					clearData('mandalDiv');
					clearData('panchayatDiv');
				}
				else if(levelId == 11)
				{
					$('#districtDiv').show();
					
					clearData('constituencyDiv');
					clearData('mandalDiv');
					clearData('panchayatDiv');
				}
				else if(levelId == 5)
				{
					$('#districtDiv').show();
					$('#constituencyDiv').show();
					$('#mandalDiv').show();
					
					clearData('constituencyDiv');
					clearData('mandalDiv');
					clearData('panchayatDiv');
				}
				else if(levelId == 6)
				{
					$('#districtDiv').show();
					$('#constituencyDiv').show();
					$('#mandalDiv').show();
					$('#panchayatDiv').show();
					
					clearData('constituencyDiv');
					clearData('mandalDiv');
					clearData('panchayatDiv');
				}
				*/
			  });
			   
			 $(".distEle").click(function(e)
			 {	

				$(".distName").html($(this).text());
				$(".distName").attr("value",$(this).attr("data-value"));
				$(".distName").attr("name",$(this).text());
				$(".distEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				getConstituenciesForDistricts($(this).attr("data-value"));
			  });
			  $(".constiEle").click(function()
				{	
				$(".constiName").html($(this).text());
				$(".constiName").attr("value",$(this).attr("data-value"));
				$(".constiEle").removeClass('cs-selected');
				$(".constiName").attr("name",$(this).text());
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				 getTehsils($(this).attr("data-value"));
			  });
			    $(".mandalEle").click(function()
				{	
				$(".mandalName").html($(this).text());
				$(".mandalName").attr("value",$(this).attr("data-value"));
				$(".mandalEle").removeClass('cs-selected');
				$(".mandalName").attr("name",$(this).text());
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				 getPanchayats($(this).attr("data-value"));
			  });
			  $(".panchayatEle").click(function()
				{	
				$(".panchayatName").html($(this).text());
				$(".panchayatEle").removeClass('cs-selected');
				$(".panchayatName").attr("value",$(this).attr("data-value"));
				$(".panchayatName").attr("name",$(this).text());
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value")); committeeSlide
				
			  });
			   $(".committeeEle").click(function()
				{						
					$(".committeeName").html($(this).text());
					$(".committeeName").attr("value",$(this).attr("data-value"));
					$(".committeeEle").removeClass('cs-selected');
					$(this).addClass('cs-selected');
					//alert($(this).attr("data-value"));
				
			  });
			 
			}
			$(document).ready(function()
			{
				
				  $('#createeventdate').daterangepicker(null, function(start, end, label) {
					//console.log(start.toISOString(), end.toISOString(), label);
				  });
  
				//$('.locationsCls').hide();
				$("#checkAll").click(function()
				{
					if($(this).is(":checked"))
					{
						$(".roleCheck").prop('checked', true);
						$("#checkText").html("UnSelect All");
							addCommitteeDivs('roleCheck','CadreMembers');
					}
					else{
						$(".roleCheck").prop('checked', false);
						$("#checkText").html("Select All");
					}
				});
				
				$("#checkPRAll").click(function()
				{
					
					if($(this).is(":checked"))
					{
						$(".rolePRCheck").prop('checked', true);
						$("#checkText").html("UnSelect All");
							addCommitteeDivs('rolePRCheck','publicRepresentatives');
					}
					else{
						$(".rolePRCheck").prop('checked', false);
						$("#checkText").html("Select All");
					}
				});
		});
		
		$(document).on('click', function (e) {
					if ($(e.target).closest(".cs-skin-slide").length === 0) {
					 $(".cs-skin-slide").removeClass("cs-active");;
				}
							
			});
			
			var totalCommiteCnt = 0;
			var roleArr = new Array();
			var selCommiteeArr = new Array();
			function addCommitteeDivs(className,memberType)
			{
				var levelId = $(".stateName").attr('value');
				var committeeLevl = '';
				var locationLevel = 'State Level';
				var levelValue = 0;
				var divId = '';
				var locationName = '';
				$('#buildSelectionBlockDiv').show();
				if(levelId == 10)
				{
					if(memberType =='CadreMembers')
					{
						divId='CSaccordion';
						$('#stateLevelCommitteId').show();
						$('#buildSelectionBlockDiv').show();
						$('#stateLevelId').show();
					}
					else if(memberType == 'publicRepresentatives')
					{
						divId='PRSaccordion';
						$('#stateLevelPRId').show();
						$('#buildSelectionPRBlockDiv').show();
						$('#stateLevelHeadingId').show();
					}
					
					levelValue = 0;
					locationLevel = "State Level";
					
				}
				else if(levelId == 11)
				{
					if(memberType =='CadreMembers')
					{
						divId='CDaccordion';
						$('#districtLevelCommitteId').show();	
						$('#buildSelectionBlockDiv').show();
						$('#stateLevelId').show();
					}
					else if(memberType == 'publicRepresentatives')
					{
						divId='PRDaccordion';
						$('#districtLevelPRId').show();	
						$('#buildSelectionPRBlockDiv').show();
						$('#stateLevelHeadingId').show();
					}					
					levelValue = $(".distName").attr("value");	
									
				}
				else if(levelId == 5)
				{
					if(memberType =='CadreMembers')
					{
						divId='CMaccordion';
						$('#mandalLevelCommitteId').show();	
						$('#buildSelectionBlockDiv').show();
						$('#stateLevelId').show();
					}
					else if(memberType == 'publicRepresentatives')
					{
						divId='PRMaccordion';
						$('#mandalLevelPRId').show();
						$('#buildSelectionPRBlockDiv').show();	
						$('#stateLevelHeadingId').show();						
					}				
					levelValue = $(".mandalName").attr("value");
					
				}
				else if(levelId == 6)
				{
					if(memberType =='CadreMembers')
					{
						divId='CVaccordion';
						$('#villageLevelCommitteId').show();
						$('#buildSelectionBlockDiv').show();
						$('#stateLevelId').show();
					}
					else if(memberType == 'publicRepresentatives')
					{
						divId='PRVaccordion';
						$('#villageLevelPRId').show();
						$('#buildSelectionPRBlockDiv').show();
						$('#stateLevelHeadingId').show();
					}	
				}	
		
			var districtId = $(".distName").attr("value");		
			var constituencyId = $(".constiName").attr("value");
			var mandalId = $(".mandalName").attr("value");
			var panchayatId = $(".panchayatName").attr("value");

			if(typeof districtId == 'undefined')
			{
				districtId = 0;
			}
			if(typeof constituencyId == 'undefined')
			{
				constituencyId = 0;
			}
			if(typeof mandalId == 'undefined')
			{
				mandalId = 0;
			}
			
			if(typeof panchayatId == 'undefined')
			{
				panchayatId = 0;
			}
			if(typeof levelValue == 'undefined')
			{
				levelValue = 0;
			}
			
			if( panchayatId != 0)
			{
				var areaName = $(".mandalName").attr("name");
				locationName = $(".panchayatName").attr("name");
				locationLevel = locationName+" Village - "+areaName+"";	
			}
			else if( mandalId != 0)
			{
				var areaName = $(".distName").attr("name");
				locationName = $(".mandalName").attr("name");
				locationLevel = locationName+" - "+areaName+" Assembly";		
			}
			else if(constituencyId != 0)
			{
				var areaName = $(".distName").attr("name");
				locationName = $(".constiName").attr("name");
				locationLevel = locationName+" Assembly - "+areaName+" District";	
			}
			else if( districtId != 0)
			{	
				locationName = $(".distName").attr("name");
				locationLevel = locationName+" District";
			}
			else if(districtId == 0){
				if(levelId == 10)
				{
					locationLevel = " State Level";
				}
			}
		
			$(".toggleCls").removeClass("in");

			if(memberType =='CadreMembers')
			{
					var commite = $(".committeeName").html();
					var commiteId = $(".committeeName").attr("value");
					var selCommiteeId = commiteId;
					commiteId = levelId+""+commiteId+""+districtId+""+constituencyId+""+mandalId+""+panchayatId+""+levelValue;
					
					$("#"+commiteId+"Div").remove();
					
					var str ='';
					
					str+='<div class="panel panel-default border_0 commiteHeadDiv" id="'+commiteId+'Div">';
					
					str+='<div class="panel-heading collapse-head" role="tab" id="headingComm'+commiteId+'">';
					str+='<h4 class="panel-title">';
					str+='<form class="me-select display-style">';//collapse-select
					str+='<ul id="me-select-list">';
					str+='<li>';//<input id="cb11" name="cb11" type="checkbox" class="addedcommite"/>';
					str+='<span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapseComm'+commiteId+'" aria-controls="collapseComm'+commiteId+'" class="col-drop-head" onClick="toggleDiv(\'collapseComm'+commiteId+'\')">'+commite+' ('+locationLevel+') <a href="javascript:{deleteCommite(\''+commiteId+'Div\',\'COMMITTEE\');}" title="Click here to Remove Committee Details."> <i class="glyphicon glyphicon-trash"></i></a></a></span></li>';
					str+=' </ul>';
					str+=' </form>';
					str+='<a data-toggle="collapse" data-parent="#accordion" href="#collapseComm'+commiteId+'" aria-expanded="true" aria-controls="collapseComm'+commiteId+'" onClick="toggleDiv(\'collapseComm'+commiteId+'\')">';
					str+='<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>';
				  str+='</a>';
					str+='</h4>';  
				  str+='</div>';
				  str+='<div id="collapseComm'+commiteId+'" class="panel-collapse collapse toggleCls in" role="tabpanel" aria-labelledby="headingComm'+commiteId+'" >';
				  str+='<div class="panel-body">';
				  str+='<form class="me-select display-style">';
				  str+='<ul id="me-select-list">';
			  
			   var commRoleIdsArr = new Array();
			  $("."+className).each(function()
					{
					 if($(this).is(":checked")) 
					 {
						  var roleId = $(this).val();
						  var role = $(this).attr("name");
						  str+=' <li><input  class="checkedCls" name="cb11" checked="true" type="checkbox" id="comm'+commiteId+'role'+roleId+'" committeeId = "'+selCommiteeId+'" value="'+roleId+'" locationLevelId="'+levelId+'" districtId="'+districtId+'" constiId="'+constituencyId+'" mandalId="'+mandalId+'" villageId ="'+panchayatId+'">';
						  str+='<label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+role+'</label></li>';
						  
						    commRoleIdsArr.push(roleId);
					 }
			   });	   
			 
			 
			var isExist = false;
			if(selCommiteeArr.length>0)
			{				
				for(var k in selCommiteeArr)
				{
					if(selCommiteeArr[k].fromType == 'FromCommittee')
					{
						if(selCommiteeArr[k].commiteeId == selCommiteeId)
						{
							if(selCommiteeArr[k].levelId == levelId)
							{
								if(selCommiteeArr[k].levelValue == levelValue)
								{								
									isExist = true;
									selCommiteeArr[k].rolesArr = commRoleIdsArr;
								}								
							}
						}
						
						
					}
					else
					{
						isExist = true;
					}						
				}
			}
			
			
			if(!isExist)
			{
				var commteArrObj =
				   {
					   fromType:"FromCommittee",
					   commiteeId:selCommiteeId,
					   rolesArr : commRoleIdsArr,
					   levelId:levelId,
					   levelValue:levelValue
				   };
			   selCommiteeArr.push(commteArrObj);
			}
			
	   
				   str+='</ul></form>';
				  str+='</div>';
				 str+=' </div>';
				 
			   str+='</div>';
			  $("#"+divId+"").append(str);
			}
			else
			{
				
					var commite = "PR";
					var commiteId = "PR";
					var selCommiteeId = "PR";
					commiteId = levelId+""+commiteId+""+districtId+""+constituencyId+""+mandalId+""+panchayatId+""+levelValue;
					
					$("#"+commiteId+"Div").remove();
					
					var str ='';
					
					str+='<div class="panel panel-default border_0 commiteHeadDiv" id="'+commiteId+'Div">';
					
					str+='<div class="panel-heading collapse-head" role="tab" id="headingComm'+commiteId+'">';
					str+='<h4 class="panel-title">';
					str+='<form class="me-select display-style">';//collapse-select click 
					str+='<ul id="me-select-list">';
					str+='<li>';//<input id="cb11" name="cb11" type="checkbox" class="addedcommite"/>';
					str+='<span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapseComm'+commiteId+'" aria-controls="collapseComm'+commiteId+'" class="col-drop-head" onClick="toggleDiv(\'collapseComm'+commiteId+'\')">'+locationLevel+' <a href="javascript:{deleteCommite(\''+commiteId+'Div\',\'PR\');}" title="Click here to Remove Committee Details."> <i class="glyphicon glyphicon-trash"></i></a></a></span></li>';
					str+=' </ul>';
					str+=' </form>';
					str+='<a data-toggle="collapse" data-parent="#accordion" href="#collapseComm'+commiteId+'" aria-expanded="true" aria-controls="collapseComm'+commiteId+'" onClick="toggleDiv(\'collapseComm'+commiteId+'\')">';
					str+='<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>';
				  str+='</a>';
					str+='</h4>';  
				  str+='</div>';
				  str+='<div id="collapseComm'+commiteId+'" class="panel-collapse collapse toggleCls in" role="tabpanel" aria-labelledby="headingComm'+commiteId+'" >';
				  str+='<div class="panel-body">';
				  str+='<form class="me-select display-style">';
				  str+='<ul id="me-select-list">';
			  
			   var commRoleIdsArr = new Array();
			  $("."+className).each(function()
					{
					 if($(this).is(":checked")) 
					 {
						  var roleId = $(this).val();
						  var role = $(this).attr("name");
						  str+=' <li><input  class="checkedCls" name="cb11" checked="true" type="checkbox" id="comm'+commiteId+'role'+roleId+'" committeeId = "'+selCommiteeId+'" value="'+roleId+'" locationLevelId="'+levelId+'" districtId="'+districtId+'" constiId="'+constituencyId+'" mandalId="'+mandalId+'" villageId ="'+panchayatId+'">';
						  str+='<label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+role+'</label></li>';
						  
						   commRoleIdsArr.push(roleId);
					 }
			   });	   
			 
				   str+='</ul></form>';
				  str+='</div>';
				 str+=' </div>';
				 
			   str+='</div>';
			  $("#"+divId+"").append(str);
			  
			  $("#candidateDetailsDiv").show();
			
			var isExist = false;
			if(selCommiteeArr.length>0)
			{
				for(var k in selCommiteeArr)
				{
					if(typeof selCommiteeArr[k] != 'undefined' && selCommiteeArr[k].fromType == 'FromPublicRepresentative')
					{
						if(selCommiteeArr[k].levelId == levelId)
						{
							if(selCommiteeArr[k].levelValue == levelValue)
							{
								if(selCommiteeArr[k].commiteeId == selCommiteeId)
								{
									isExist = true;
									selCommiteeArr[k].rolesArr = commRoleIdsArr;
								}
								
							}
						}
						else
						{
							isExist = true;
						}
						
					}
				}
			}
			
			
			if(!isExist)
			{
			 var commteArrObj =
			   {
				   fromType:"FromPublicRepresentative",
				   commiteeId:selCommiteeId,
				   rolesArr : commRoleIdsArr,
				   levelId:levelId,
				   levelValue:levelValue
			   };
			   selCommiteeArr.push(commteArrObj);
			}
			}

  //getMembersDetails(0);
}
	
	function createGroup(index)
	{	

		 var jsObj = globalJsonArr[index];
		var groupName = $('#groupNameId'+index+'').val();
		var groupJsObj =
		{
			searchType:"New Group",
			groupName:groupName,
			eventId :jsObj.eventId,
			committeeLevelId : jsObj.committeeLevelId,
			committeeLevelValue:jsObj.committeeLevelValue,
			committeeIdsArr : jsObj.committeeIdsArr,
			stateId:jsObj.stateId,
			districtId:jsObj.districtId,
			constituencyId:jsObj.constituencyId,
			mandalId:jsObj.mandalId,
			panchayatId:jsObj.panchayatId,
			startIndex:jsObj.startIndex,
			maxIndex : jsObj.maxIndex,	
			sortBy:jsObj.sortBy,
					
		};

		$.ajax({
          type:'GET',
          url: 'getinviteesMembersDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(groupJsObj)}
		   }).done(function(result){
			   if(result != null && result.length>0)
			   {
					if(result[0].responseCode == 0)
					{
						alert("Group Successfully Created...");
						$('#groupNameId'+index+'').val('');
					}
					else if(result[0].responseCode == 2)
					{
							$('#'+divId+'').html(" <span style='margin-left:500px ;font-weight:bold;'> Group Name already exist...</span>");
					}
					else
					{							
							alert(" No data available in "+locationLevel+" Level");
					}
			   }
			   else
			   { 
					alert("<span style='margin-left:500px ;font-weight:bold;'> Group Name already exist...</span>");
				}
			}
			);
			
	}
	
	var globalJsonArr = new Array();
	function getMembersDetails(startIndex,actionText)
	{
		$('#candidateDetailsDiv').show();
		isEntered=false;
		var submitArr = new Array();
		var committeeIdsArr = new Array();
		
		var stateArr = new Array();
		var districtArr = new Array();
		var mandalArr = new Array();
		var villageArr = new Array();
		var eventId = 0;
		//debugger;
		if(actionText == 'invite')
		{
			eventId = $('#inviteEventsList').val();
			if(eventId == 0)
			{
				alert('Please Select Event.');
				return;
			}
		}
		
		$('#summaryAjax').show();
		$(".paginationDivId").hide();
		$('.checkedCls').each(function(){
			if($(this).is(':checked'))
			{	

				var levelId = $(this).attr('locationlevelid');
				var levelValue =0;
				var selectedLevel ='state';
				
				var districtId = $(this).attr('districtid');
				var constituencyId = $(this).attr('constiid');
				var mandalId = $(this).attr('mandalid');
				var panchayatId = $(this).attr('villageid');	
				
				var roleId = $(this).attr('value');
				var committeeId = $(this).attr('committeeid');
				var isCommitteeExist = true;
				var pushArr = stateArr;
				
				var finalDistrictId = 0;
				var finalConstiteuncyId = 0;
				var finalMandalId = 0;
				var finalPanchayatId =0;
				
				
				if(panchayatId != 0)
				{
					finalPanchayatId = panchayatId;
				}
				if(mandalId != 0)
				{
					finalMandalId = mandalId;
				}
				if(constituencyId != 0)
				{
					finalConstiteuncyId = constituencyId;
				}
				if(districtId != 0)
				{
					finalDistrictId = districtId;
				}
				
				if(panchayatId != 0)
				{
					levelValue = panchayatId;
					selectedLevel ="village";
				}
				else if(mandalId != 0)
				{
					levelValue = mandalId;
					selectedLevel ="mandal";
				}
				else if(constituencyId != 0)
				{
					levelValue = constituencyId;
					selectedLevel ="constituency";
				}
				else if(districtId != 0)
				{
					levelValue = districtId;
					selectedLevel ="district";
				}
								
				if(levelId == 10)
				{
					pushArr = stateArr;					
				}
				else if(levelId == 11)
				{
					pushArr = districtArr;
				}
				else if(levelId == 5)
				{				
					pushArr = mandalArr;
				}
				else if(levelId == 6)
				{				
					pushArr = villageArr;
				}	
				
				var searchTypeStr ='CadreCommittee';
				if(committeeId == 'PR')
				{
					searchTypeStr ='PublicRepresentatives';
				}
				if(pushArr.length>0)
				{
					//debugger;
					for(var i in pushArr)
					{		
						if(pushArr[i].levelId == levelId)
						{				
							if(pushArr[i].levelValue == levelValue)
							{
								if(pushArr[i].committeeId == committeeId)
								{
									isCommitteeExist = false;
									rolesIdsArr = pushArr[i].rolesIds;
									rolesIdsArr.push(roleId);
								}
							}
						}
					}
				}	
				
				if(isCommitteeExist)				
				{
					var rolesIdsArr = new Array();
						rolesIdsArr.push(roleId);
						var committeeObj = {
						searchType:searchTypeStr,
						committeeId : committeeId,
						levelId:levelId,
						districtId:finalDistrictId,
						constituencyId:finalConstiteuncyId,
						mandalId:finalMandalId,
						panchayatId:finalPanchayatId,
						levelValue:levelValue,
						selectedLevel:selectedLevel,
						rolesIds:rolesIdsArr
						}
					pushArr.push(committeeObj);
				}				
			}		
		});

		if(stateArr.length>0)
		{
			var stateObj ={
			levelStr:"state",
			levelArr:stateArr
			};
			submitArr.push(stateObj);
		}
		
		if(districtArr.length>0)
		{
			var districtObj ={
			levelStr:"district",
			levelArr:districtArr
			};
			submitArr.push(districtObj);
		}
		
		
		if(mandalArr.length>0)
		{
			var mandalObj ={
			levelStr:"mandal",
			levelArr:mandalArr
			};
			
		submitArr.push(mandalObj);
		}
		
		
		if(villageArr.length>0)
		{
			var villageObj ={
			levelStr:"village",
			levelArr:villageArr
			};
			submitArr.push(villageObj);
		}
		
		var jsObj =
		{
			searchType:"getDetails",
			stateId:glstateId,
			actionType:actionText,
			stateStr:"AP",
			groupName:"",
			eventId :eventId,
			submitArr:submitArr,
			startIndex:startIndex,
			maxIndex : 100					
		};
		var divId = 'buildSearchDetailsStateId';
		$('#'+divId+'').html('');
		if(startIndex == 0)
		{
			$('#countDiv').html('');
			$('#publicRepresentsId').html('');
			$('#mandalMembersUl').html('');
			$('#villageMembersUl').html('');
			$('#stateMembersUl').html('');
			$('#districtMembersUl').html('');
		}
			
		
		$('html, body').animate({scrollTop:$('#summaryAjax').offset().top}, 'slow');
		$.ajax({
          type:'GET',
          url: 'getinviteesMembersDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   $('#summaryAjax').hide();
			   isReturn = true;
			   if(result != null && result.length>0 && result[0].cadreComitteeVOList != null && result[0].cadreComitteeVOList.length>0)
			   {	
					
				if(actionText == 'none')
				{
					buildSearchDetails(result[0].cadreComitteeVOList,result[0].cadreSearchList,divId,'',jsObj,result[0].totalCount,result);
				}
				else if(result != null && result.length>0 && result[0].responseCode == 0)
				{
					alert('Selected Members Invited Successfully...');
					 $("#dialogueForInviteId").css('display','none');
					 $("#inviteMembersDiv").dialog('close');
				}
			   }
			else{
				$('#'+divId+'').html('<span style="font-weight:bold;text-align:center;">No Data Available...</span>');
			}			   
		   });
	}
	var indexValue=0;
	//9999
	function buildSearchDetails(result,countResult,divId,locationLevel,jsObj,totalCnt,totalresult)
	{		

		var str ='';
		$(".paginationDivId").show();
		$("#candidateDetailsDiv").show();
		var count=0;
		//console.log("result.length  :"+result.length);
		if(result != null && result.length>0)
		{				
				str+='<div class="panel-heading">  GROUP MEMBERS DETAILS ';
				str+='<div style="padding-top:10px;padding-left:10px" class="pull-right">';
				//str+='<div style="width:300px;margin-top:-18px;margin-right:-10px" class="input-group pull-right">';
				//str+='<input type="text" style="height: 35px;" aria-describedby="basic-addon2" placeholder="Enter Group Name" class="form-control" id="groupNameId'+indexValue+'"/>';
				//str+='<span style="background-color: rgb(1, 182, 173); color: rgb(255, 255, 255); cursor: pointer;" id="addGroupId" class="input-group-addon" onclick="createGroup('+indexValue+')">ADD TO GROUP</span>';
				str+='<span style="margin-top: -10px; cursor: pointer;margin-right: 15px;" id="createEventBtn" class="btn btn-xs btn-success " onclick="createNewEvent();"> CREATE EVENT </span>';
				//str+='<button style="margin-top: -10px; cursor: pointer;" id="inviteBtn" class="btn btn-xs btn-success " onclick="eventsForUser()" data-toggle="modal" data-target="#commdetailspop"> INVITE MEMBERS </button>';
				//str+='</div><br><br>';
				//str+='<span class="viewlink">Click To View Existed group Names</span>    ';
				str+='</div>';
				
				//str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{dispatchAddressDetails();}">Download Address Patches</a>';
												
				//str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{sendSmsForCandidtes(\'allContacts'+locationLevel+'Cls\',\'contacts'+locationLevel+'Cls\');}" style="margin-right: 10px;">Send SMS</a>';
				str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{exportConstituencyToExcel(\'searchTableId'+divId+'\',\''+locationLevel+'\');}" style="margin-right: 15px;"> Export Excel </a>';
				
				str+='</div>';
				
				str+='<table class="table table-bordered " id="searchTableId'+divId+'" style="width: 1024px ! important; font-size: 11px; font-weight: bold;">';
				str+='<thead>';
				str+='<tr>';
				str+='<th> <input type="checkbox" checked="true" value="" class="allContacts'+locationLevel+'Cls" onclick="checkallCheckBoxes(\'allContacts'+locationLevel+'Cls\',\'contacts'+locationLevel+'Cls\');"/> SELECT ALL </th>';
				str+='<th> DISTRICT </th>';
				str+='<th> CONSTITUENCY </th>';
				str+='<th> MANDAL/MUNCIPALITY</th>';
				str+='<th> CANDIDATE NAME	</th>';
				str+='<th> COMMITTEE </th>';
				str+='<th> DESIGNATION	</th>';
				str+='<th> MOBILE NO </th>';
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				
				for(var i in result)
				{											
					str+='<tr>';
						str+='<td> <input type="checkbox" checked="true" value="'+result[i].mobileNo+'" class="contacts'+locationLevel+'Cls" id="contacts'+locationLevel+'Id'+i+'" onclick="checkCheckBoxes(\'contacts'+locationLevel+'\','+i+',\'allContacts'+locationLevel+'Cls\');"/> </td>';
						str+='<td> '+result[i].address+' </td>';
						str+='<td> '+result[i].constituency+' </td>';
						if(result[i].tehsil != null)
							str+='<td> '+result[i].tehsil+' </td>';
						else
							str+='<td style="text-align:center;"> - </td>';
						
						str+='<td> '+result[i].cadreName+' </td>';
						if(result[i].committeeName != null)
							str+='<td> '+result[i].committeeName+' ('+result[i].electionType+')</td>';
						else 
							str+='<td style="text-align:center;"> - </td>';
						if(result[i].committeePosition != null)
							str+='<td> '+result[i].committeePosition+'</td>';
						else if(result[i].mobileType != null)
							str+='<td> '+result[i].mobileType+'</td>';
						else 
							str+='<td style="text-align:center;"> - </td>';
						
						
						str+='<td> '+result[i].mobileNo+' </td>';
					str+='</tr>';					
				}
				
				str+='</tbody>';
				str+='</table>';
				
		var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $(".paginationDivId").pagination({
			items: totalCnt,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*100;
				getMembersDetails(num,'none');
				
			}
		});
	}
			$('#'+divId+'').html(str);
			$("#searchTableId"+divId+"").dataTable({
				"iDisplayLength": 50,
				"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			});			
		}
		
		$('html, body').animate({scrollTop:$('#'+divId+'').offset().top}, 'slow');
		
		var publicRepresentsCount = 0;
		if(countResult != null && countResult.length>0)
		{
			for(var j in countResult)
			{
				publicRepresentsCount = publicRepresentsCount+countResult[j].totalCount;
			}
		}
			
		if(jsObj.startIndex == 0)	
		{
			if(publicRepresentsCount >0)
			{
				if(countResult != null && countResult.length>0)
				{
					str ='';
					str+='<h5></h5>';
					str+='<ul class="publickRepracentative" >';
					if(countResult != null && countResult.length>0)
					{
						str+='<li> <span style="font-weight:bold;"> Public Representatives ('+publicRepresentsCount+') </span> </li>';
						for(var j in countResult)
						{					
							if(countResult[j].totalCount != 0){							
								str+='<li> <span style="font-weight:bold">'+countResult[j].cadreName+' </span> : '+countResult[j].totalCount+'</li>';
							}							
						}

					}
					str+='</ul>';
					
					$('#publicRepresentsId').html(str);
					$('#publicRepresentsId').show();
				}
			}	
			
			//$('#countsDiv1').html('');
		}
		
		//totalresult
		
		var mandalsArr = totalresult[0].tdpCadreDetailsList;
		var mandalCadreCount = 0;publicRepresentsCount
		if(mandalsArr != null && mandalsArr.length>0)
		{
			for(var j in mandalsArr)
			{
				mandalCadreCount = mandalCadreCount+mandalsArr[j].totalCount;
			}
		}

		if(jsObj.startIndex == 0)	
		{
			if(mandalCadreCount >0)
			{
				str ='';
				str+='<h5></h5>';
				str+='<ul class="publickRepracentative" >';
				if(mandalsArr != null && mandalsArr.length>0)
				{
					str+='<li> <span style="font-weight:bold;"> Mandal/Municipality Level Committee Members ('+mandalCadreCount+') </span> </li>';
					for(var j in mandalsArr)
					{					
						if(mandalsArr[j].totalCount != 0){							
							str+='<li> <span style="font-weight:bold">'+mandalsArr[j].cadreName+' </span> : '+mandalsArr[j].totalCount+'</li>';
							
						}							
					}

				}
				str+='</ul>';
				
				$('#mandalMembersUl').html(str);
				$('#mandalMembersUl').show();
			}
		}

		var locationsWiseCountArr = totalresult[0].voterSearchList;	
		if(locationsWiseCountArr != null)
		{
			var villagesArr = locationsWiseCountArr[0].voterSearchList;
			//console.log("lenght :  "+villagesArr.lenght);
			var villageCadreCount = 0; 
			if(villagesArr != null && villagesArr.length>0)
			{
				for(var j in villagesArr)
				{
					villageCadreCount = villageCadreCount+villagesArr[j].totalCount;
				}
			}

			if(jsObj.startIndex == 0)	
			{
				if(villageCadreCount >0)
				{
					str ='';
					str+='<h5></h5>';
					str+='<ul class="publickRepracentative"  >';
					if(villagesArr != null && villagesArr.length>0)
					{
						str+='<li> <span style="font-weight:bold;"> Village/Ward Level Committee Members ('+villageCadreCount+') </span> </li>';
						for(var j in villagesArr)
						{					
							if(villagesArr[j].totalCount != 0){							
								str+='<li> <span style="font-weight:bold">'+villagesArr[j].cadreName+' </span> : '+villagesArr[j].totalCount+'</li>';
								
							}							
						}

					}
					str+='</ul>';
					
					$('#villageMembersUl').html(str);
					$('#villageMembersUl').show();
				}
			}
			
			var statesArr = locationsWiseCountArr[0].tdpCadreDetailsList;
			var statesCadreCount = 0; 
			if(statesArr != null && statesArr.length>0)
			{
				for(var j in statesArr)
				{
					statesCadreCount = statesCadreCount+statesArr[j].totalCount;
				}
			}

			if(jsObj.startIndex == 0)	
			{
				if(statesCadreCount >0)
				{
					str ='';
					str+='<h5></h5>';
					str+='<ul class="publickRepracentative" >';
					if(statesArr != null && statesArr.length>0)
					{
						str+='<li> <span style="font-weight:bold;"> State Level Committee Members ('+statesCadreCount+') </span> </li>';
						for(var j in statesArr)
						{					
							if(statesArr[j].totalCount != 0){							
						str+='<li> <span style="font-weight:bold">'+statesArr[j].cadreName+' </span> : '+statesArr[j].totalCount+'</li>';
								
							}							
						}

					}
					str+='</ul>';
					
					$('#stateMembersUl').html(str);
					$('#stateMembersUl').show();
				}
			}
			
			var districtsArr = locationsWiseCountArr[0].cadreSearchList;
			var districtCadreCount = 0; 
			if(districtsArr != null && districtsArr.length>0)
			{
				for(var j in districtsArr)
				{
					districtCadreCount = districtCadreCount+districtsArr[j].totalCount;
				}
			}

			if(jsObj.startIndex == 0)	
			{
				if(districtCadreCount >0)
				{
					str ='';
					str+='<h5></h5>';
					str+='<ul class="publickRepracentative" >';
					if(districtsArr != null && districtsArr.length>0)
					{
						str+='<li> <span style="font-weight:bold;"> State Level Committee Members ('+districtCadreCount+') </span> </li>';
						for(var j in districtsArr)
						{					
							if(districtsArr[j].totalCount != 0){							
								str+='<li> <span style="font-weight:bold">'+districtsArr[j].cadreName+' </span> : '+districtsArr[j].totalCount+'</li>';
								
							}							
						}

					}
					str+='</ul>';
					
					$('#districtMembersUl').html(str);
					$('#districtMembersUl').show();
				}
			}
			
			var totalMembersCount = districtCadreCount + statesCadreCount + villageCadreCount + mandalCadreCount + publicRepresentsCount;
			if(totalMembersCount >0)
			{
				str+='<span class="text-capital m_0 text-center text-head"> You are selected committee with members</span>';
				str+='<div class="text-center">';
				//str+='<span class="display-style text-italic box-subhead">Total Selected Committees:<span class="count-color">1202</span></span>';
				str+='<span class="display-style text-italic box-subhead"> Selected Total Members : <span class="count-color">'+totalMembersCount+'</span></span>';
				str+='</div>';
				
				
				$('#countDiv').html(str);
				$('#countDiv').show();
			}
			
		}
		
		
		indexValue =indexValue+1;
	}
	
	var isReturn = true;
	function deleteCommite(removeDivId,type)
	{
		$('#'+removeDivId+'').html('');
		$('#'+removeDivId+'').remove();
		
		var isCMembersAvail = true;
		var isPRMembersAvail = true;
		var isAllDisabled = false;
		//COMMITTEE , PR
		if(type.trim() == 'COMMITTEE')
		{
			var divLength = $('#CSaccordion').html().trim().length;
			var str='';
			if(divLength == 0)
			{
				$('#stateLevelCommitteId').css('display','none');
				str+='S';
			}
			
			divLength = $('#CDaccordion').html().trim().length;
			if(divLength == 0)
			{
				$('#districtLevelCommitteId').css('display','none');
				str+='D';
			}
			
			divLength = $('#CMaccordion').html().trim().length;
			if(divLength == 0)
			{
				$('#mandalLevelCommitteId').css('display','none');
				str+='M';
			}
			
			divLength = $('#CVaccordion').html().trim().length;
			if(divLength == 0)
			{
				$('#villageLevelCommitteId').css('display','none');
				str+='V';
			}
			
			if(str.trim().length >0 && str == 'SDMV')
			{				
				$('#stateLevelId').css('display','none');
				isCMembersAvail = false;
			}			
		}
		
		if(type.trim() == 'PR')
		{
			var divLength = $('#PRSaccordion').html().trim().length;
			var str='';
			if(divLength == 0)
			{
				$('#stateLevelPRId').css('display','none');
				str+='S';
			}
			
			divLength = $('#PRDaccordion').html().trim().length;
			if(divLength == 0)
			{
				$('#districtLevelPRId').css('display','none');
				str+='D';
			}
			
			divLength = $('#PRMaccordion').html().trim().length;
			if(divLength == 0)
			{
				$('#mandalLevelPRId').css('display','none');
				str+='M';
			}
			
			divLength = $('#PRVaccordion').html().trim().length;
			if(divLength == 0)
			{
				$('#villageLevelCommitteId').css('display','none');
				str+='V';
			}
			
			if(str.trim().length >0 && str == 'SDMV')
			{				
				$('#stateLevelHeadingId').css('display','none');
				isPRMembersAvail = false;
			}			
		}
		
		if(!isCMembersAvail)
		{
			if(!isPRMembersAvail)
			{
				$('#buildSelectionBlockDiv').hide();
				$('#candidateDetailsDiv').hide();
				
				$(".roleCheck").prop('checked', false);
				$("#checkAll").prop('checked', false);
				
				$(".rolePRCheck").prop('checked', false);
				$("#checkPRAll").prop('checked', false);
				
				isAllDisabled = true;
			}
		}
		
		if(isReturn && !isAllDisabled && (isCMembersAvail || isPRMembersAvail))
		{
			isReturn = false;
			//getMembersDetails(0,'none');
		}
		
	}
	function toggleDiv(id)
		{
		
			var height = $("#"+id).css('height').match(/\d+/);
			$(".toggleCls").removeClass("height-auto");
			$(".toggleCls").removeClass("in");
			
			if(height == 0 || $("#"+id).hasClass('height-0'))
			{
			$("#"+id).addClass("height-auto").addClass("in");
			
			$("#"+id).removeClass("height-0").addClass("in");
			
			}
			else
			{
				$("#"+id).removeClass("height-auto").addClass("in");
				$("#"+id).addClass("height-0").addClass("in");
				
			}
		}
		
		function checkCheckBoxes(selectedId,index,neddtoChangeCls)
		{
			var isChecked = $('#'+selectedId+'Id'+index+'').is(':checked');
			if(isChecked)
			{
				var atleastOneNotChecked=false;
				$('.'+selectedId+'Cls').each(function(){
					var isAllChecked = $(this).is(':checked');
					if(!isAllChecked && !atleastOneNotChecked)
					{
						atleastOneNotChecked = true;
					}
				});
				
				if(atleastOneNotChecked)
				{
					$('.'+neddtoChangeCls+'').removeProp('checked');
				}
				else
				{
					$('.'+neddtoChangeCls+'').prop('checked', 'checked');
				}
			}
			else
			{
				$('.'+neddtoChangeCls+'').removeProp('checked');
			}
		}
		
		function checkallCheckBoxes(selectedCld,neddtoChangeCls)
		{	
			if($('.'+selectedCld+'').is(':checked'))
			{
				$('.'+neddtoChangeCls+'').prop('checked', 'checked');
			}
			else
			{
				$('.'+neddtoChangeCls+'').removeProp('checked');
			}
			
		}
		function exportConstituencyToExcel(divId,level)
		{
			tableToExcel(divId, ' '+level+' Level Group Details ');
		}
		
		function sendSms(messageBoxId,allCls, individualCls)
		{
			
			var sms = $('#'+messageBoxId+'').val();
			$("#smsMessageDiv").hide();
			$("#smsDialogueBoxDiv").dialog('close');
			if(confirm('Are you want to send SMS?'))
			{
				var mobileNumbersArr = new Array();
				$('.'+individualCls+'').each(function(){
					
					if($(this).is(":checked"))
					{
						var mobileNo = $(this).val();
						if(mobileNo == 10 || mobileNo == 12)
							mobileNumbersArr.push(mobileNo);
					}
				});				
				
				var myArr=new Array();
				myArr.push(9581434970);
				var jsObj ={					
					mobileNumbersArr:myArr,
					message:sms
				};
				//console.log(jsObj);
				 $.ajax({
					  type:'GET',
					  url: 'sendSmsForInviteesAction.action',
					  dataType: 'json',
					  data: {task:JSON.stringify(jsObj)}
			   }).done(function(result){
				   console.log(result);
			   });
			   
			}
		}
		function sendSmsForCandidtes(allCls, individualCls)
		{			
			$("#smsMessageDiv").show();
			$("#smsMessageDiv").html('Enter Message : <input type="textarea" maxlength="50" id="messaggeId" placeholder="Enter Message for SMS " style="height:50px;"/><input type="button" id="smsButton" value="Send SMS" onclick="sendSms(\'messaggeId\',\''+allCls+'\',\''+individualCls+'\')" class="btn btn-success  btn-xs" style="margin-left: 200px;margin-top: 10px"/>');
			$('#messaggeId').val('');
			$("#smsDialogueBoxDiv").dialog({
				title:" Invitation through SMS ",
				width:400,
				height:150
			});
	
		}
	
	function dispatchAddressDetails()
	{
		alert(" Address Details Report....");
	}
	
	function getDetails(divId)
	{
		$('#representativesDiv').hide();
		$('#cadreCommitteeDiv').hide();
		$('#existingGroupsDiv').hide();
		if(divId =='publicRepresentativesId')
		{
			$('#representativesDiv').show();
		}
		else if(divId =='cadreCommitteeId')
		{
			$('#cadreCommitteeDiv').show();
		}
		else if(divId == 'groupId')
		{
			$('#existingGroupsDiv').show();
		}
	}
	
	function buildLevel()
	{
		if(gldistrictId != 0)
		  {
			  var str='';
				str+='<section>';
				  str+='<label class="select-label">Level</label>';
				  str+=' <div class="cs-select cs-skin-slide stateSlide" tabindex="0" onclick="selectChange(\'stateSlide\')">';
				  
				  str+='<span class="cs-placeholder stateName" value="11">District</span>';
				  str+='<div class="cs-options">';
					  str+='<ul class="scrollbar stateList">';
					  // str+='<li data-value="10" data-option="" class="stateEle"><span>State</span></li>';
					   str+='<li data-value="11" data-option="" class="stateEle"><span>District</span></li>';
					   str+='<li data-value="5" data-option="" class="stateEle"><span>Mandal/Town/Division </span></li>';
					   str+='<li data-value="6" data-option="" class="stateEle"><span>Village/Ward</span></li>';
					  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="levelId">';
					str+='<option value="11">District</option>';
					str+='<option value="5">Mandal/Muncipality</option>';
					str+='<option value="6">Village/Ward</option>';
				 str+='</select></div></section>';
				 
				$("#levelDivId").html(str);
		  }
		  else
		  {
			  var str='';
				str+='<section>';
				  str+='<label class="select-label">Level</label>';
				  str+=' <div class="cs-select cs-skin-slide stateSlide" tabindex="0" onclick="selectChange(\'stateSlide\')">';
				  
				  str+='<span class="cs-placeholder stateName" value="10">State</span>';
				  str+='<div class="cs-options">';
					  str+='<ul class="scrollbar stateList">';
					   str+='<li data-value="10" data-option="" class="stateEle"><span>State</span></li>';
					   str+='<li data-value="11" data-option="" class="stateEle"><span>District</span></li>';
					   str+='<li data-value="5" data-option="" class="stateEle"><span>Mandal/Muncipality </span></li>';
					   str+='<li data-value="6" data-option="" class="stateEle"><span>Village/Ward</span></li>';
					  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="levelId">';
					str+='<option value="10">State</option>';
					str+='<option value="11">District</option>';
					str+='<option value="5">Mandal/Muncipality</option>';
					str+='<option value="6">Village/Ward</option>';
				 str+='</select></div></section>';
				 
				$("#levelDivId").html(str);
		  }
		
	}
	
	function getPublicRepresentsDetails(){
    	
    	var jsObj={
    			task:"publicRepresentatives"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getPublicRepresentativeTypes.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		str+='<ul id="me-select-list" >';
			for(var i in result)
			{ //checkPRText
				str+='<li><input id="role'+result[i].id+'" name="'+result[i].name+'" type="checkbox" value="'+result[i].id+'" class="rolePRCheck" onclick="addCommitteeDivs(\'rolePRCheck\',\'publicRepresentatives\');"><label for="cb10"  style="padding:0px 10px 0px 45px;margin-top:10px;"><span class="roleName">'+result[i].name+'</span></li>';
			}
           str+=' </ul>';
		    $("#representativesForm").html(str);
    	   });	
		  
    	
      }
	  
	   function eventsGroups(){
    	
    	var jsObj={
    			task:"eventGroups"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getPartyEventGroups.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		str+='<ul id="me-select-list" >';
			for(var i in result)
			{
				str+='<li><input id="role'+result[i].id+'" name="'+result[i].name+'" type="checkbox" value="'+result[i].id+'" class="roleCheck" onclick="addCommitteeDivs(\'roleCheck\');"><label for="cb10"  style="padding:0px 10px 0px 45px;margin-top:10px;"><span class="roleName">'+result[i].name+'</span></li>';
			}
           str+=' </ul>';
		    $("#groupsForm").html(str);
    	   });	
      }
	  
	   function eventsForUser(){
    	
		
		$('#mainEventsList').find('option').remove();
		$('#mainEvenmtsLits').find('option').remove();
		$('#EventsListEdit').find('option').remove();
		$('#evnetsList').find('option').remove();
		$('#evnetsListEdit').find('option').remove();
		
		$('#mainEventsList').append('<option value="0"> Select Event </option>');
		$('#mainEvenmtsLits').append('<option value="0"> Select Event </option>');
		//$('#EventsListEdit').append('<option value="0"> Select Event </option>');
		$('#evnetsList').append('<option value="0"> Select Event </option>');
		$('#evnetsListEdit').append('<option value="0"> Select Event </option>');
		  var jsObj={
    			task:"eventGroups"
    		};
    		$.ajax({
    			  type:'GET',
    			  url: 'getEventsForUser.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
			   $('.modal-backdrop').remove();
					if(result != null)
					{
						for(var i in result)
						{
							$('#mainEventsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
							$('#mainEvenmtsLits').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
							
							$('#EventsListEdit').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
							$('#evnetsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
							$('#evnetsListEdit').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}						
					}
				});
				
    	
      }
	
	  function creatNewEvent(type)
	  {	
	  
			//getting Date And Time
			  //eventstart
			  var dateArray=[];
			  var startDateForEvent='';
			  var endDateForEvent='';
			  var startTime='';
			  var endTime='';
			  
			var eventName ='';
			var description = '';		
			var entryLimit = '';
			var serverMode = '';
			var tabMode = '';
			var mainEventId ='';
			var eventId = 0; 
			  
			  
	  
			/* var eventName = $('#eventNameId').val();
			var description = $('#description').val();		
			var entryLimit = $('#entryLimit').val();
			 var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			var serverMode = $("input[name='serverWorkMode']:checked").val();
			var tabMode = $("input[name='tabWorkMode']:checked").val();
			var mainEventId = $('#mainEvenmtsLits').val();
			var eventId = 0; */
		  /* $('#errorDivId').html('');

			  if(eventName == null || eventName.trim().length==0)
			  {
				  $('#errorDivId').html('Event name required.');
				  return;
			  }
			  else 	if(description == null || description.trim().length==0)
			  {
				  $('#errorDivId').html('Description is required.');
				  return;
			  } */
			  
			  //modified for date and time for saving and updating
			  if(type == 'newEvent')
			  {
				   dateArray = $("#startDate").val().split("-");
			 
					  startDateForEvent=dateArray[0];
					  endDateForEvent=dateArray[1];
					   startTime = $('#startTime').val();
					   endTime = $('#endTime').val();
					   
					  eventName = $('#eventNameId').val();
					   description = $('#description').val();		
					  entryLimit = $('#entryLimit').val();
					  serverMode = $("input[name='serverWorkMode']:checked").val();
					  tabMode = $("input[name='tabWorkMode']:checked").val();
					  
					 if($("#inlineRadio1").is(":checked")){
						  mainEventId=0;
					 }
					  else if($("#inlineRadio2").is(":checked")){
						  mainEventId = $('#mainEvenmtsLits').val();
					  }
					  eventId = 0;
					   
			  }
			  
			  else if(type == 'updateEvent'){
				  
						dateArray = $("#startDateEdit").val().split("-");
				  
						  eventId=$("#EventsListEdit option:selected").val();
					      description = $('#descriptionEdit').val();		
						  entryLimit = $('#entryLimitEdit').val();
						  startDateForEvent=dateArray[0];
						  endDateForEvent=dateArray[1];
						  startTime = $('#startTimeEdit').val();
						  endTime = $('#endTimeEdit').val();
						  serverMode = $("input[name='serverWorkModeEdit']:checked").val();
						  tabMode = $("input[name='tabWorkModeEdit']:checked").val();
						 if($("#inlineRadio1Edit").is(":checked")){
							  mainEventId=0;
						 }
						  else if($("#inlineRadio2Edit").is(":checked")){
							  mainEventId = $('#evnetsListEdit').val();
						  }
			  }
			  $('#errorDivId').html('');
			  $('#errorDivIdEdit').html('');

			  if(type == 'newEvent'){
				if(eventName == null || eventName.trim().length==0)
				  {
					  $('#errorDivId').html('Event name required.');
					  return;
				  }
				 else if(description == null || description.trim().length==0)
				  {
					  $('#errorDivId').html('Description is required.');
					  return;
				  }
			  }else if(type == 'updateEvent'){
				  if(eventId ==null || eventId ==0 || eventId ==undefined){
					   $('#errorDivIdEdit').html('please select any event.');
					  return;
				  }
				 else if(description == null || description.trim().length==0)
				  {
					  $('#errorDivIdEdit').html('Description is required.');
					  return;
				  }
			  }
			  
			  
			   var jsObj={
					mainEventId:mainEventId,
					eventId :eventId,
					eventName:eventName,
					description:description,
					entryLimit:entryLimit,
					startDate:startDateForEvent,
					endDate:endDateForEvent,
					startTime:startTime,
					endTime:endTime,
					serverMode:serverMode,
					tabMode:tabMode,
					actionType:type					
				};
				
				console.log(jsObj);
			

				$.ajax({
					  type:'GET',
					  url: 'createORUpdateANewEvent.action',
					  data: {task:JSON.stringify(jsObj)}
			   }).done(function(result){
				   console.log(result);
				   if(result.resultCode==0){
				   if(type == 'newEvent'){
					   alert("Event Created Successfully");
				   }
				   else{
					   alert("Event Updated Successfully");
				   }
				   $(".clearCreateEventFields").val('');
				   $("#serverWorkMode1Edit").prop("checked", true);
				   $("#tabWorkMode1Edit").prop("checked", true);
				   $('#mainEvenmtsLits').append('<option value="0"> Select Event </option>');
				   }else{
					   alert("Problem Occured While Creating");
				   }
			   });

	  }
	  function clearData(divId)
	  {
		  /*
		   var str='';
		   str+='<section>';
			  //str+='<label class="select-label">Assembly</label>';
			  str+=' <div class="cs-select cs-skin-slide '+location+'Slide" tabindex="0" onclick="selectChange(\''+location+'Slide\')">';
			  str+='<span class="cs-placeholder '+location+'Name"" value="0">ALL</span><div class="cs-options"><ul class="scrollbar '+location+'List">';
			  str+='<li data-value="0" data-option="" class="'+location+'Ele"><span>ALL</span></li>';
			  str+='</ul>';
			 str+='</section>';
			$("#"+divId+"").html(str);*/
	  }
	  
	 
	 function updateEventList(id,divId)
	 {
		  $('#'+divId+'').hide();
		  $('#mainEvenmtsLits').val(0);
		 if(id==2)
		 {
			   $('#'+divId+'').show();
		 }
	 }
	 function updateEventListEdit(id,divId)
	 {
		  $('#'+divId+'').hide();
		  $('#evnetsListEdit').val(0);
		 if(id==2)
		 {
			   $('#'+divId+'').show();
		 }
	 }
	 
	 function updateEventSettings()
	 {
		 var eventId = $('#evnetsList').val();
		 var rfidOperation = $('#rfidList').val();
		 var regTextId = $('#regTextId').val();
		 var sectorId = $('#sectorsList').val();
		 var blockId = $('#blockList').val();
		 
		 var jsObj={
					eventId:eventId,
					rfidOperation:rfidOperation,
					
				};
				
				console.log(jsObj);
				

				$.ajax({
					  type:'GET',
					  url: 'createORUpdateANewEvent.action',
					  data: {task:JSON.stringify(jsObj)}
			   }).done(function(result){
				   console.log(result);
			   });
	 }
	 
	/*  $("#inlineRadio2Edit").is(":checked"){
		 $("#evnetsListEdit").val(0);
		 $("#mainEventsDivEdit").show();
	 }
	 $("#inlineRadio1Edit").is(":checked"){
		 $("#mainEventsDivEdit").hide();
	 } */
	 
</script>
<script>
getDistricts();
getCommittees();
getCommitteeRoles();
buildLevel();
eventsForUser();
eventsGroups();
getPublicRepresentsDetails();

//getMembersDetails();


$(document).on("click","#careateANewUserForEvents",function(){
	
	$(".userDetailsCls").html('');
	
	var firstName=$("#firstName").val();
	var lastName=$("#lastNmae").val();
	var mobileNo=$("#mobileno").val();
	var userName=$("#userName").val();
	var password=$("#passwordId").val();
	
	var errorAlert=false;

	if(firstName =='' || firstName == null || firstName ==undefined){
		$("#firstNameErrorId").html(' Please enter First Name.');
		errorAlert=true;
	}
	if(mobileNo =='' || mobileNo ==null ){
		$("#mobileNoErrorId").html(' Please enter Mobile No.');
		errorAlert=true;
	}else if(mobileNo !='' && mobileNo !=null){
		var returnvalue=validatePhone(mobileNo);
		if(!returnvalue){
			$("#mobileNoErrorId").html(' Invalid Mobile No.');
			errorAlert=true;
		}		
	}
	if(userName =='' || userName ==null || userName ==undefined ){
		$("#userNameErrorId").html(' Please enter UserName.');
		errorAlert=true;
	}
	if(password =='' || password ==null || password ==undefined ){
		$("#passwordErrorId").html(' Please enter passWord.');
		errorAlert=true;
	}
	else if(password !='' && password !=undefined && password !=null){
		var passwordIsValid=passwordValidation(password);
		if(!passwordIsValid){
			$("#passwordErrorId").html(' Please enter valid Password.');
			errorAlert=true;
		}
	}
	if(errorAlert){
		return;
	}
	var jsObj={
		firstName:firstName,
		lastName:lastName,
		mobileNo :mobileNo,
		userName :userName,
		password :password
	}
	$.ajax({
          type:'POST',
          url: 'careateANewUserForEvents.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
	   $("#userErrorDetailsCls").html('');
	   
	   if(result.resultCode==0){
		   alert("created User Successfully.");
		   $("#clearFiedsForUser").val('');
	   }
	   else{
		   alert("problem Occured While saving.")
	   }
	   
   });
});
  /* function Validate() {
        var mobile = document.getElementById("mobile").value;
        var pattern = /^\d{10}$/;
        if (pattern.test(mobile)) {
            alert("Your mobile number : " + mobile);
            return true;
        }
        alert("It is not valid mobile number.input 10 digits number!");
        return false;
    } */
   
  function validatePhone(txtPhone) {
    var filter = /^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/;
    if (filter.test(txtPhone)) {
        return true;
    }
    else {
        return false;
    }
  }
  function passwordValidation(input) {
    var reg = /^[^%\s]{8,}/;
    var reg2 = /[a-zA-Z]/;
    var reg3 = /[0-9]/;
    return reg.test(input) && reg2.test(input) && reg3.test(input);
}
clearFields();
function clearFields(){
	$(".clearFiedsForUser").val('');
	$(".userErrorDetailsCls").html('');
	
	//for create and update event
		$("#mainEventsDiv").hide();
		$("#mainEventsDivEdit").hide();
		$("#errorDivId").html('');
		
		//for eventSettings
		
		$(".clearEventSetFieldsCls").val(0);
		$(".clearEventSetTextFieldsCls").val('');

}
$(document).on("click",".eachTabPressCls",function(){
	{
		//for user creation
		$(".clearFiedsForUser").val('');
		$(".userErrorDetailsCls").html('');
		
		//for create and update event(clearing fields at event Creation) 
		$(".clearCreateEventFields").val('');
		$("#mainEventsDiv").hide();
		$("#errorDivId").html('');
		$(".clearRadioOfCreateEvent").prop("checked", true);
		$("#inlineRadio1").prop("checked", true);
		

		$("#mainEventsDivEdit").hide();
		$(".clearFieldsAtUpdationEvent").val(0);
		$(".clearSelectAtUpdationEvent").val('');
		$(".clearRadioOfCreateEventEdit").prop("checked",true);
		$("#inlineRadio1Edit").prop("checked",true);
		
		
		//for eventSettings
		
		$(".clearEventSetFieldsCls").val(0);
		$(".clearEventSetTextFieldsCls").val('');
		
	}
});

mainCount=0;
var buildCount= 1;

	$(".divIncrementCls").click(function(){
		 /* if(mainCount == 3){
			$("#incrementedDiv"+mainCount).html('');
			return;
		}  */
		if(buildCount <3)
		{
			mainCount=mainCount+1;
			buildCount=buildCount+1;
			var str='';
				str+='<div id="incrementedDiv'+mainCount+'">';
				str+='<div class="col-md-3">';
                                str+='<label>RFID Operation</label>';
                                str+='<select class="form-control rfidCls" id="rfidListAdd'+mainCount+'" textId="regTextIdAdd'+mainCount+'" sectorId ="sectorsListAdd'+mainCount+'" blockId="blockListAdd'+mainCount+'">';
                                    str+='<option value="0"> Select RFID Operation</option>';
                                    str+='<option value="READ"> READ</option>';
                                    str+='<option value="WRITE"> WRITE</option>';
                                str+='</select>';
                            str+='</div>';
                            str+='<div class="col-md-3">';
                                str+='<label>Reg Text</label>';
									str+='<input class="form-control" id="regTextIdAdd'+mainCount+'"/>';
                            str+='</div>';
                        

                        	 str+='<div class="col-md-3">';
                                str+='<label>Sector No</label>';
                               str+='<select class="form-control" id="sectorsListAdd'+mainCount+'">';
                                    str+='<option value="0"> Select Sector No</option>';
                                    str+='<option value="1"> 1</option>';
                                    str+='<option value="2"> 2</option>';
                                    str+='<option value="3"> 3</option>';
                                str+='</select>';
                            str+='</div>';
                             str+='<div class="col-md-2">';
                                 str+='<label>Block No</label>';
                                  str+='<select class="form-control" id="blockListAdd'+mainCount+'">';
                                     str+='<option value="0"> Select Block No </option>';
                                    str+='<option value="1"> 1</option>';
                                     str+='<option value="2"> 2</option>';
                                     str+='<option value="3"> 3</option>';
                                 str+='</select>';
                             str+='</div>';
							 
						 str+='<div class="col-md-1" style="cursor:pointer;color:red;">';
							str+='<span class="glyphicon glyphicon-minus divDecrementClsAdd" attr_removeDivId="'+mainCount+'"  style="margin-top: 30px;"></span>';
						 str+='</div>';
						 str+='</div>';
						 
					$("#eventSetRFIDdivClsAdd").append(str);
		}
					//mainCount=mainCount+1;
	});

	$(document).on("click",".divDecrementClsAdd",function(){
		
		var removeDivId=$(this).attr("attr_removeDivId");
		buildCount = buildCount-1;
		$("#incrementedDiv"+removeDivId).html('');
		// mainCount = mainCount-1; 
	});
	
	//saving Event Settings	
	$(document).on("click","#savingEventSetttings",function(){
		var eventId = $('#evnetsList').val();
		
		//var RfidCount=$("#rfidList").find("option").length;
		var rfidActionsArr = new Array();
		
		$('.rfidCls').each(function(){
			var rfidListId = $(this).attr('id');
			var textId = $(this).attr('textid');
			var sectorId = $(this).attr('sectorid');
			var blickId = $(this).attr('blockid');
			
			var settingObj = {
				rfid: $('#'+rfidListId+'').val(),
				regText:$('#'+textId+'').val(),
				sectorNo:$('#'+sectorId+'').val(),
				blockNo:$('#'+blickId+'').val()		
			};
			
			rfidActionsArr.push(settingObj);
		});
		
		var jsObj=
		{				
			eventId:eventId,
			rfidActionsArr:rfidActionsArr,
			actionType:"save"
		}
		$.ajax({
			  type:'GET',
			  url: 'updateEventsSettings.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result.resultCode==0)
		   {
			    alert("Saved Successfully");
				$('#eventsettings').append('<option value="0"> Select Event </option>');
				$('#rfidList').append('<option value="0">Select RFID Operation</option>');
				$('#sectorsList').append('<option value="0">Select Sector No</option>');
				$('#blockList').append('<option value="0">Select Block No</option>');
				$(".clearEventSetTextFieldsCls").val('');
				
		   }
		   else{
			   alert("error occured");
		   }
	   });
	   
	});	
	
	
	$("#evnetsList").change(function(){
		
		var eventId=$("#evnetsList").val();
		var jsObj={
			eventId:eventId
		}
		$.ajax({
			  type:'POST',
			  url: 'prePopulatingValuesOfEvents.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   });
		
	});
	
				
</script>

</body>
</html>

