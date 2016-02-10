<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Committee Member Details</title>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link href="dist/Invitees/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/Invitees/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Invitees/css/normalize.css" rel="stylesheet" type="text/css">
<link href="dist/Invitees/css/cs-skin-slide.css" rel="stylesheet" type="text/css">
<link href="dist/Invitees/css/cs-select.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
<link href="dist/Invitees/MultiSelect/css/component.css" rel="stylesheet" type="text/css">
<link href="dist/Invitees/css/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">


<script src="dist/Invitees/js/classie.js" type="text/javascript"></script>
<script src="dist/Invitees/js/selectFx.js" type="text/javascript"></script>
<script src="dist/Invitees/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/Invitees/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>

<script src="dist/Invitees/js/fm.scrollator.jquery.js" type="text/javascript"></script>

	<!-- Bootstrap -->
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />	
    <script type="text/javascript" src="js/exportexcel.js"></script>


<script src="dist/Invitees/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/Invitees/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/Invitees/Date/daterangepicker.js" type="text/javascript"></script>

<script src="dist/Invitees/js/bootstrap.min.js" type="text/javascript"></script>

<style>
*
{
	outline:none !important;
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
#speakersTab tr.odd td.sorting_1,#districtTableId tr.odd td.sorting_1{
    background-color: #d3d3d3 !important;
}
#speakersTab tr.even td.sorting_1 , #districtTableId tr.even td.sorting_1{
    background-color: #fafafa !important;
}
#speakersTab tr.odd,#districtTableId tr.odd {
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
</style>
</head>

<body class="background-bg">



<div class="container m_top30" style="min-height:340px;">
<!--<div>
 <a href="manageEvents.action" title="Click Here To Manage Events" type="button" class="button btn btn-primary" style="background-color:#3897A5;cursor:pointer;font-weight:bold;padding:5px 48px 5px 48px;  margin-left: 900px;" >Manage Events</a>
</div> -->
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
				
				
				<div class="col-md-2 locationsCls" id="stateDiv">
					<section>
							<label class="select-label">State</label>
						<div class="cs-select cs-skin-rotate" onclick="getDistricts();">
	
				<div class="cs-options">
					<ul>
						<li data-option data-value="0" class="cs-selected"><span>ALL</span></li>
						<li data-option data-value="1" class="cs-selected"><span>Andhra Pradesh</span></li>
						<li data-option data-value="2"><span>Telangana</span></li>
						</ul>
				</div>
				<select class="cs-select cs-skin-slide" id="stateId">
					<option value="0" >ALL</option>
					 <option value="1" >Andhra Pradesh</option>
					 <option value="2" >Telangana</option>
				</select>
               </div>
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
							<label class="select-label" style="margin-top: 10px;">Village/Ward</label>
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
						<input type="radio" id="publicRepresentativesId" name="searchBy" value="1" onclick="getDetails('publicRepresentativesId');">  Public Representative
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
							
							<div class="panel panel-default" id="centralLevelCommitteId" style="display:none;">
								<div class="panel-heading">
									Central Level
								</div>
								<div class="panel-body">
									<div class="panel-group" id="CCaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
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
							
							<div class="panel panel-default" id="centralLevelPRId" style="display:none;">
								<div class="panel-heading">
									Central Level 
								</div>
								<div class="panel-body">
									<div class="panel-group" id="PRCaccordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
									</div>
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
									<button onclick="getMembersDetails(0,'none','none');" class="btn btn-success btn-xs"> Get Details </button>
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
								<div id="buildSearchDetailsStateId"  class="panel panel-success" style="margin-top: 40px; overflow:scroll;"></div>	
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
	
	</div>
<div class="modal fade" id="commdetailspop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="margin-left: 280px; width: 660px; height: 100%; overflow: hidden; background: none repeat scroll 0% 0% transparent; box-shadow: none; border: medium none;position:absolute;">
		  <div class="modal-dialog">
			<div class="modal-content" style="border:2px solid #01b6ad;border-radius:0px;">
			  <div class="modal-header" style="background:#;padding:10px">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>	
			  <div role="tabpanel">

		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active" ><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" style="padding:10px 15px !important;"> Invite Members	</a></li>
			<li role="presentation"><a href="#home"  aria-controls="home" role="tab" data-toggle="tab" style="padding:10px 15px !important"> Create Event </a></li>
		  </ul>
		  </div>
		  </div>
		  <div class="modal-body">
		  <!-- Tab panes -->
		  <div class="tab-content">
			<div role="tabpanel" class="tab-pane " id="home">

				<div id="errorDivId" style="color:#FE0000;" ></div>
				  <div class="form-group">
					<label for="exampleInputName2">Event Name</label>
					<input type="text" class="form-control" id="newEventId" style="height: 30px;">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail2">Event For</label>
					<select class="form-control" id="eventsList" >
						
					</select>
				  </div>
				
					<label for="exampleInputEmail1">Description</label><br/>
					<textarea class="form-control" id="descriptionId" maxlength="50"></textarea><br/>
					<form class="form-inline">
					  <div class="form-group">
						<label for="exampleInputEmail0">Date</label>
						<input type="email" class="form-control" id="createeventdate">
					  </div>
					  <div class="form-group">
						<label for="exampleInputEmail">Time</label>
						<input type="email" class="form-control">
					  </div>
					</form>
					
					</div>
				
			<div role="tabpanel" class="tab-pane active" id="profile">
				<select class="form-control" id="inviteEventsList">
				</select>
			
			</div>
		  </div>

			</div>
				  <div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="creatNewEvent();">Submit</button>
			  </div>


			</div>  
		</div>
		</div>

<script src="dist/Invitees/js/classie.js" type="text/javascript"></script>
<script src="dist/Invitees/js/selectFx.js" type="text/javascript"></script>
<script src="dist/Invitees/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/Invitees/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>
<script src="dist/Invitees/js/fm.scrollator.jquery.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<script src="dist/Invitees/Date/moment.min.js" type="text/javascript"></script>
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
	 var stateId = $("#stateId").val();
     var jsObj=
		{				
				stateId:stateId,
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
		  // glstateId=2;
		  glstateId=stateId;
	   }
	   else if(result.length == 14)
	   {
		//   glstateId=1;
		 glstateId=stateId;
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
				{
					if(stateId == 1)
					{
						if(result[i].id > 11)
						str+='<li data-value="'+result[i].id+'" data-option="" class="distEle"><span>'+result[i].name+'</span></li>';
					}
					else if(stateId == 2)
					{
						if(result[i].id < 11)
						str+='<li data-value="'+result[i].id+'" data-option="" class="distEle"><span>'+result[i].name+'</span></li>';
					}
					else
						str+='<li data-value="'+result[i].id+'" data-option="" class="distEle"><span>'+result[i].name+'</span></li>';
					
				}
				 
		}
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="districtId">';
		  for(var i in result)
		  {
		   if(result[i].id > 0)
		   {
			 if(stateId == 1)
					{
						if(result[i].id > 11)
							str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
					}	
				else if(stateId == 2)
				{
					if(result[i].id < 11)
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				else
				{
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
			
		   }
          
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
				
				$('#candidateDetailsDiv').hide();
				$('.paginationDivId').html('');
				$('#buildSearchDetailsStateId').html('');
				$('#publicRepresentsId').html('');
				$('#districtMembersUl').html('');
				$('#mandalMembersUl').html('');
				$('#stateMembersUl').html('');
				$('#villageMembersUl').html('');
				var stateId = $("#stateId").val();
				var levelId = $(".stateName").attr('value');
				var committeeLevl = '';
				
				var locationLevel = 'State Level';
				var levelValue = 0;
				var divId = '';
				var locationName = '';
				$('#buildSelectionBlockDiv').show();
				if(stateId == 1)
						locationLevel = "Andhra Pradesh State Level";
					else if(stateId == 2)
						locationLevel = "Telangana State Level";
					else
					locationLevel = "State Level";
					
				if(levelId == 12)
				{
					if(memberType =='CadreMembers')
					{
						divId='CCaccordion';
						$('#centralLevelCommitteId').show();
						$('#buildSelectionBlockDiv').show();
						$('#stateLevelId').show();
					}
					else if(memberType == 'publicRepresentatives')
					{
						divId='PRCaccordion';
						$('#centralLevelPRId').show();
						$('#buildSelectionPRBlockDiv').show();
						$('#stateLevelHeadingId').show();
					}
					
					levelValue = 0;
					
					/*if(stateId == 1)
						locationLevel = "Andhra Pradesh State Level";
					else if(stateId == 2)
						locationLevel = "Telangana State Level";
					else*/
					locationLevel = "Central Level";
					
				}
				else if(levelId == 10)
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
					
					if(stateId == 1)
						locationLevel = "Andhra Pradesh State Level";
					else if(stateId == 2)
						locationLevel = "Telangana State Level";
					else
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
				if(levelId == 12)
				{
				
					/*if(stateId == 1)
						locationLevel = "Andhra Pradesh State Level";
					else if(stateId == 2)
						locationLevel = "Telangana State Level";
					else*/
					locationLevel = "Central Level";
				}
				else if(levelId == 10)
				{
				
					if(stateId == 1)
						locationLevel = "Andhra Pradesh State Level";
					else if(stateId == 2)
						locationLevel = "Telangana State Level";
					else
					locationLevel = "State Level";
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
	function getMembersDetails(startIndex,actionText,reportType)
	{
		
		isEntered=false;
		var submitArr = new Array();
		var committeeIdsArr = new Array();
		
		var centralArr = new Array();
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
				//var pushArr = stateArr;
				var pushArr = centralArr;
				
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
								
				if(levelId == 12)
				{
					pushArr = centralArr;					
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

		if(centralArr.length>0)
		{
			var centralObj ={
			levelStr:"central",
			levelArr:centralArr
			};
			submitArr.push(centralObj);
		}
		
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
		var stateId = $("#stateId").val();
		var stateStr;
		if(stateId == 1)
			stateStr ="AP";
		else if(stateId == 2)
			stateStr ="TS";
		else
			stateStr ="ALL";
		//reportType:"EXPORTEXCEL";
		var jsObj =
		{
			reportType:reportType,
			searchType:"getDetails",
			//stateId:glstateId,
			stateId:stateId,
			actionType:actionText,
			stateStr:"AP",
			groupName:"",
			eventId :eventId,
			submitArr:submitArr,
			startIndex:startIndex,
			maxIndex : 100					
		};
		var divId = 'buildSearchDetailsStateId';
		if(reportType == 'none')
		{
			$('#candidateDetailsDiv').show();
			$('#summaryAjax').show();
			$(".paginationDivId").hide();
		
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
		}
		
		$.ajax({
          type:'GET',
          url: 'getinviteesMembersDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   $('#summaryAjax').hide();
			   isReturn = true;
			   if(result != null && result.length>0)
			   {
				if(reportType == 'EXPORTEXCEL')				   
				{
					window.open(result[0].imageURL);
				}
				else
				{
					
					if(result[0].cadreComitteeVOList != null && result[0].cadreComitteeVOList.length>0)
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
				//str+='<span style="margin-top: -10px; cursor: pointer;margin-right: 15px;" id="createEventBtn" class="btn btn-xs btn-success " onclick="createNewEvent();"> CREATE EVENT </span>';
				//str+='<button style="margin-top: -10px; cursor: pointer;" id="inviteBtn" class="btn btn-xs btn-success " onclick="eventsForUser()" data-toggle="modal" data-target="#commdetailspop"> INVITE MEMBERS </button>';
				//str+='</div><br><br>';
				//str+='<span class="viewlink">Click To View Existed group Names</span>    ';
				str+='</div>';
				
				//str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{dispatchAddressDetails();}">Download Address Patches</a>';
												
				//str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{sendSmsForCandidtes(\'allContacts'+locationLevel+'Cls\',\'contacts'+locationLevel+'Cls\');}" style="margin-right: 10px;">Send SMS</a>';
				str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{getMembersDetails(0,\'none\',\'EXPORTEXCEL\');}" style="margin-right: 15px;"> Export Excel </a>';
				
				str+='</div>';
				
				str+='<table class="table table-bordered " id="searchTableId'+divId+'" style="width: 1024px ! important; font-size: 11px; font-weight: bold;">';
				str+='<thead>';
				str+='<tr>';
				//str+='<th> <input type="checkbox" checked="true" value="" class="allContacts'+locationLevel+'Cls" onclick="checkallCheckBoxes(\'allContacts'+locationLevel+'Cls\',\'contacts'+locationLevel+'Cls\');"/> SELECT ALL </th>';
				str+='<th> DISTRICT_NO </th>';
				str+='<th> DISTRICT </th>';
				str+='<th> CONSTITUENCY_NO </th>';
				str+='<th> CONSTITUENCY </th>';
				str+='<th> MANDAL/MUNCIPALITY</th>';
				str+='<th> CANDIDATE NAME	</th>';
				str+='<th> COMMITTEE - DESIGNATION </th>';
				str+='<th> COMMITTEE LEVEL	</th>';
				str+='<th> MOBILE NO </th>';
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				
				for(var i in result)
				{											
					str+='<tr>';
						//str+='<td> <input type="checkbox" checked="true" value="'+result[i].mobileNo+'" class="contacts'+locationLevel+'Cls" id="contacts'+locationLevel+'Id'+i+'" onclick="checkCheckBoxes(\'contacts'+locationLevel+'\','+i+',\'allContacts'+locationLevel+'Cls\');"/> </td>';
						var disres ;
						if(result[i].address != null)
							disres = result[i].address.split("_");
						else
							disres =["",""];
						str+='<td style="text-align:center;"> '+disres[0]+' </td>';
						str+='<td> '+disres[1]+' </td>';
						//str+='<td> '+result[i].address+' </td>';
						var res;
						if(result[i].constituency != null)
							 res = result[i].constituency.split("_");
						 else
							 res =["",""];
						if(res[0] == "0")
						{
							str+='<td style="text-align:center;"> - </td>';
						}
						else
						{
							str+='<td style="text-align:center;"> '+res[0]+' </td>';
						}
						str+='<td> '+res[1]+' </td>';
						if(result[i].tehsil != null)
							str+='<td> '+result[i].tehsil+' </td>';
						else
							str+='<td style="text-align:center;"> - </td>';
						if(result[i].tdpCadreCommitteeId != null)
							str+='<td><a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].tdpCadreCommitteeId+'"> '+result[i].cadreName+' </a></td>';
						else
							str+='<td><a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].tdpCadreId+'"> '+result[i].cadreName+' </a></td>';
						
						if(result[i].committeeName != null){							
							str+='<td> '+result[i].committeeName+' ('+result[i].committeePosition+')</td>';		
						}	
						else if(result[i].mobileType != null)
						{
							str+='<td> '+result[i].mobileType+'</td>';
						}						
						else //ElectionType
						{
							str+='<td style="text-align:center;"> - </td>';
						}	

						
						if(result[i].electionType != null)
						{
							if(result[i].committeeLocation != null && result[i].committeeLocation.trim().length>0)
							{
								str+='<td> '+result[i].committeeLocation+' ('+result[i].electionType+')</td>';
							}
							else
							{
								str+='<td> '+result[i].electionType+'</td>';
							}
						}
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
				getMembersDetails(num,'none','none');
				
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
						str+='<li> <span style="font-weight:bold;"> District Level Committee Members ('+districtCadreCount+') </span> </li>';
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
		$('#candidateDetailsDiv').hide();
		$('.paginationDivId').html('');
		$('#buildSearchDetailsStateId').html('');
		$('#publicRepresentsId').html('');
		$('#districtMembersUl').html('');
		$('#mandalMembersUl').html('');
		$('#stateMembersUl').html('');
		$('#villageMembersUl').html('');
		$('#'+removeDivId+'').html('');
		$('#'+removeDivId+'').remove();
		
		var isCMembersAvail = true;
		var isPRMembersAvail = true;
		var isAllDisabled = false;
		//COMMITTEE , PR
		if(type.trim() == 'COMMITTEE')
		{
			var divLength = $('#CCaccordion').html().trim().length;
			var str='';
			if(divLength == 0)
			{
				$('#centralLevelCommitteId').css('display','none');
				str+='C';
			}
			
			divLength = $('#CSaccordion').html().trim().length;
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
			
			if(str.trim().length >0 && str == 'CSDMV')
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
				$('#centralLevelPRId').css('display','none');
				str+='C';
			}
			
			divLength = $('#PRSaccordion').html().trim().length;
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
			
			if(str.trim().length >0 && str == 'CSDMV')
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
				  
				  //str+='<span class="cs-placeholder stateName" value="10">State</span>';
				  str+='<span class="cs-placeholder stateName" value="12">Central</span>';
				  str+='<div class="cs-options">';
					  str+='<ul class="scrollbar stateList">';
					   str+='<li data-value="12" data-option="" class="stateEle"><span>Central</span></li>';
					   str+='<li data-value="10" data-option="" class="stateEle"><span>State</span></li>';
					   str+='<li data-value="11" data-option="" class="stateEle"><span>District</span></li>';
					   str+='<li data-value="5" data-option="" class="stateEle"><span>Mandal/Muncipality </span></li>';
					   str+='<li data-value="6" data-option="" class="stateEle"><span>Village/Ward</span></li>';
					  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="levelId">';
				    str+='<option value="12">Central</option>';
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
    	
		
		$('#eventsList').find('option').remove();
		$('#inviteEventsList').find('option').remove();
		$('#eventsList').append('<option value="0"> Select Event </option>');
		$('#inviteEventsList').append('<option value="0"> Select Event </option>');
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
							$('#eventsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
							$('#inviteEventsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}						
					}
				});
				
    	
      }
	  
	  function creatNewEvent()
	  {	
		
		  var inviteEventId = $('#inviteEventsList').val();
		  
		  if(inviteEventId == 0)
		  {
			  var eventName = $('#newEventId').val();
			  var description = $('#descriptionId').val();		
			  var date  = $('#createeventdate').val().split('-'); 
			  var startDate = date[0];
			  var endDate = date[1];
			  var mainEventId = $('#eventsList').val();
			  $('#errorDivId').html('');

			  if(eventName == null || eventName.trim().length==0)
			  {
				  $('#errorDivId').html('Event name required.');
				  return;
			  }
			  else 	if(description == null || description.trim().length==0)
			  {
				  $('#errorDivId').html('Description is required.');
				  return;
			  }
			  
			   var jsObj={
					eventName:eventName,
					description:description,
					startDate:startDate.trim(),
					endDate:endDate.trim(),				
					mainEventId:mainEventId
				};
				

				$.ajax({
					  type:'GET',
					  url: 'createNewEvent.action',
					  data: {task:JSON.stringify(jsObj)}
			   }).done(function(result){
				   console.log(result);
			   });
		  }
		  else
		  {
			  getMembersDetails(0,'invite','none');
		  }
		  
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
	  
	 $('#checkAll').click(function()
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
				
			
</script>
<script>
getDistricts();
getCommittees();
getCommitteeRoles();
buildLevel();
//eventsForUser();
eventsGroups();
getPublicRepresentsDetails();

//getMembersDetails();
</script>

</body>
</html>