<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Training Camp Feedback Details </title>
	<link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />

	<link href="training/dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
	<!-- <link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/css/jquery.circliful.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css"> -->
<style type="text/css">
.add-button{background:#ccc;padding:3px;border-radius:50%}
.m_top30{margin-top:30px}
.add-plus{cursor:pointer}
.glyphicon-trash
{
	cursor:pointer
}

.add-plus
{
	background-color:#ccc;
	color:#666;
	padding:5px;
	border-radius:50%;
}
.addRemove
{
	background-color:#ccc;
	color:#666;
	padding:5px;
	border-radius:50%;
	cursor:pointer
}
header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
.mandatory{
color: red !important;
}
</style>
</head>
<body>
<!--<header class="eventsheader">
	<!--<img src="dist/img/header.jpg" width="100%" alt="">-->
	<!--<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:32px;"></p>               
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
					  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN')}">
								
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
								<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN')}">
								<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN')}">
							<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
						    </c:if>				
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS')}">
							<li><a href="committeeDashBoardAction.action"><span>COMMITTEES DASHBOARD</span></a> </li>
							<li><a href="meetingList.action"><span>Party Meeting ATR & MOM</span></a> </li>
					    </c:if>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
</header>-->
<!--  accordian start-->
<main style="margin-top: 40px;">
	<div class="container">
		
    	<div class="row">
        	<div class="col-md-12 m_top10">
           	  <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"></h4>
                    </div>
                    <div class="panel-body">
						  <div class="row">
                            	<div class="col-md-6">
                                	<label class="radio-inline"><input type="radio" class="radio-inline" name="radio" value="feedback" checked="checked" onclick="refreshDetails();">Feedback</label>
                                	<label class="radio-inline"><input type="radio" class="radio-inline" name="radio" value="attendance" onclick="refreshDetails();">Attendance</label>
                                </div>
                          </div>
						<div class="row m_top10">
						<div class="col-md-3">
								<label>Enrollment Year : </label>
								<select class="form-control" id="enrollmentId" onchange="rebuildProgrammesDetails(this.value)">
									<option value="0">Select Enrollment Year</option>
									<option value="4" selected>2016-2018</option>
									<!--<option value="3">2014-2016</option>-->
								</select>
								<div class="mandatory" id="enrollmentSelErrDivId"></div>
							</div>
							<div class="col-md-3">
								<label>Program</label>
								<div id="programSelDivId"></div>
								<div class="mandatory" id="programSelErrDivId"></div>
							</div>
							<div class="col-md-3">
								<label>Center</label>
								<div id="campSelDivId"></div>
								<select class="form-control" id="centerId" onchange="getBatchesForCentre(this.value)">
									<option value="0">Select Center</option>
								</select>
								<div class="mandatory" id="centerSelErrDivId"></div>
							</div>
							<div class="col-md-3" id="batchDisplayDiv">
								<label>Batch</label>
								<div id="batchSelDivId"></div>
								<select class="form-control" id="batchId">
									<option value="0">Select Batch</option>
								</select>
								<div class="mandatory" id="batchSelErrDivId"></div>
							</div>
							<div class="col-md-2 m_top30">
								<button  type="button" id="submitId" class="btn btn-success btn-sm btn-block text-bold" onclick="getFeedBackOrAttendanceDetails();">Submit</button>
							</div>
							<div class="col-md-2 m_top30" id="exportExcelDivId" style="display:none; margin-left: 800px;">
								<span class="btn btn-info 	excelId form-inline" onclick="exportToExcel()" display:inline-block;> Export To Excel </span>
							</div>
						</div>
						<div id="checkBoxDiv" class="m_top20"  style="display:none;margin-left: -88px;">
							<div class="row">
								<div class="col-md-10 col-md-offset-1" style="background: rgb(204, 204, 204) none repeat scroll 0% 0%;width:1025px">
									<div class="col-md-2">
										<label class="checkbox"><input type="checkbox" value="selectAll" id="selecctall">Select All</label>
									</div>
									<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="isFamilyUpdated" id="isFamilyUpdatedId" class="checkbox">IsFamily Updated</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input type="checkbox" value="achievements" id="acheivementsId" class="checkbox">Achievements</label>
									</div>
									<div class="col-md-3">
											<label class="checkbox"><input type="checkbox" value="communicationSkills" id="communicationSkillsCheckId" class="checkbox">Communication Skills</label>
										</div>
									<div class="col-md-2">
										<label class="checkbox"><input type="checkbox" value="leaderShipLevel" id="leadershipLevelCheckId" class="checkbox">LeaderShip Level</label>
									</div>
								</div>
							</div>
							<div style="" class="row">
								<div class="col-md-10 col-md-offset-1" style="background: rgb(204, 204, 204) none repeat scroll 0% 0%;width:1025px">
									<div class="">										
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="whatsUpUsing" id="whatsupUsingId" class="checkbox">WhatsUp Using</label>
										</div>
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="whatsUpSharing" id="whatsupSharingId" class="checkbox">WhatsUp Sharing</label>
										</div>
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="facebookKnown" id="facebookKnownId" class="checkbox">Facebook Known</label>
										</div>
									
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="leaderShipSkills" id="leadershipSkillsCheckId" class="checkbox">LeaderShip Skills</label>
										</div>
										
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="smartPhoneUsing" id="smartPhoneUsingId" class="checkbox">SmartPhone Using</label>
										</div>										
								  </div>
								</div>
							</div>
							<div style="" class="row">
								<div class="col-md-10 col-md-offset-1" style="background: rgb(204, 204, 204) none repeat scroll 0% 0%;width:1025px">
									<div class="">		
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="goals" id="goalsId" class="checkbox">Goals</label>
										</div>	
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="health" id="healthCheckId" class="checkbox">Health</label>
										</div>
										<div class="col-md-2">
											<label class="checkbox"><input type="checkbox" value="feedBackAnswer" id="feedBackAnswerChkId" class="checkbox">FeedBack Answers</label>
										</div>
										<div class="col-md-3">
											<label class="checkbox"><input type="checkbox" value="feedBackDocuments" id="feedBackDocumentsChkId" class="checkbox">FeedBack Documents</label>
										</div>
										<div class="col-md-3">
											<label class="checkbox"><input type="checkbox" value="feedBackMarks" id="feedBackMarksChkId" class="checkbox" checked>FeedBack Marks</label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-10 col-md-offset-1">
									<div class="col-md-4 col-md-offset-4 m_top10">
										<button onclick="getFeedBackOrAttendanceDetails();" class="btn btn-success btn-block  text-bold" id="checkButtonId" type="button"> Get details </button>
									</div>
								</div>
							</div>
						</div>
						<!--<div class="m_top20" id="checkBoxDiv" style="display:none">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-2">
										<label class="checkbox"><input id="selecctall" type="checkbox" value="selectAll">Select All</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="isFamilyUpdatedId" type="checkbox" checked="true" value="isFamilyUpdated">IsFamily Updated</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="acheivementsId" type="checkbox" value="achievements">Achievements</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="goalsId" type="checkbox" value="goals">Goals</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="leadershipLevelCheckId" type="checkbox" value="leaderShipLevel">LeaderShip Level</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="communicationSkillsCheckId" type="checkbox" value="communicationSkills">Communication Skills</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="leadershipSkillsCheckId" type="checkbox" value="leaderShipSkills">LeaderShip Skills</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="healthCheckId" type="checkbox" value="health">Health</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="smartPhoneUsingId" type="checkbox" value="smartPhoneUsing">SmartPhone Using</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="whatsupUsingId" type="checkbox" value="whatsUpUsing">WhatsUp Using</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="whatsupSharingId" type="checkbox" value="whatsUpSharing">WhatsUp Sharing</label>
									</div>
									<div class="col-md-2">
										<label class="checkbox"><input class="checkbox" id="facebookKnownId" type="checkbox" value="facebookKnown">Facebook Known</label>
									</div>
									<div class="col-md-2">
										<button type="button" id="checkButtonId" class="btn btn-success btn-block btn-sm  text-bold" onclick="getFeedBackOrAttendanceDetails();"> Get details </button>
									</div>
								</div>
								
							</div>
						</div>-->
						<div id="dayWiseAttendDivId" style="display:none;margin-top:10px;"></div>
						<center><img id="ajaxImage" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:45px;display:none;margin-top:20px"/></center>
                    	<div id="attendanceDiv" style="display:none"></div>
						<div id="exportExcelAttendanceDiv" style="display:none"></div>
						<div class="panel-group m_top20" id="accordion" role="tablist" aria-multiselectable="true"></div>
					</div>	
               </div>          
			</div>			  
        </div>                 
	</div>					  
</main>
<!--  accordian start-->

                  <!-- pop up modal start-->
<!--				  
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">UPDATE USER</h4>
      </div>
      <div id="modalBodyId"></div>
      <div class="modal-footer" id="modalFooterId">
        
      </div>
    </div>
  </div>
</div>
-->

                  <!-- pop up modal end -->

<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script>
	<!--<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>-->
	<script src="training/dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="js/highcharts/js/highcharts.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/blockui.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<!-- <script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script> -->

	
	<script>
	

function exportToExcel()
{
	 tableToExcel('exportExcelAttendanceTableId', 'Batch Attendence Details');
}
 

   $(document).ready(function() {
    $('#selecctall').click(function(event) { 	//on click 
        if(this.checked) { // check select status
            $('.checkbox').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.checkbox').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
    });
    
});
	$(document).ready(function(){
    });

	//$("#mainheading").html(" TRAINING CAMP FEEDBACK DETAILS ");
	
	     //global variables
		 var leaderShipLevelArray = [];
		 var communicationSkillsArray = [];
		 var leaderShipSkillsArray = [];
		 var healthStatusArray = [];
		 var globalDateCount=0;
	     //on load calls.
		 getPrograms();
	     getAllStatusForCadre();
		
		 function getAllStatusForCadre()
		 {
			$.ajax({
			  type:'POST',
			  url :'getAllStatusForCadreAction.action',
			  data:{},
		    }).done(function(result){
			    populateData(result);
			});
		}
		function populateData(result){
		  if(result!=null){
		    leaderShipLevelArray=result.leadershiplevelslist;
			communicationSkillsArray=result.communicationsSkillslist;
			leaderShipSkillsArray=result.leadershipSkillslist;
			healthStatusArray=result.healthStatuslist;
		  }
		}
		
		function buildCadreDetails(results,checkBoxArray){
		   var str='';
		   for(var i in results){
		     str+='<div class="panel panel-default">'
				str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
                   str+='<h4 class="panel-title">'+results[i].name+'</h4>';
                str+='</div>';                
                  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
                      str+='<table class="table table-bordered m_0 temptable" style="font-size:12px">';
                      str+='<thead>';
					  str+='<th>Image</th>';
                      str+='<th>Name</th><th>Mobile</th><th>Constituency</th><th>Committee</th>';
					  if(checkBoxArray[0]){
						str+='<th>IsFamilyUpdated</th>';
					  }
					  if(checkBoxArray[1]){
						str+='<th>Achievements</th>';
					  }
					  if(checkBoxArray[2]){
						str+='<th>Goals</th>';
					  }
					  if(checkBoxArray[3]){
						str+='<th>Leadership <br/>Level</th>';
					  }
					  if(checkBoxArray[4]){
						str+='<th>Communication<br/> Skills</th>';
					  }
					  if(checkBoxArray[5]){
						str+='<th>Leadership Skills</th>';
					  }
					  if(checkBoxArray[6]){
						str+='<th>Health</th>';
					  }
					  if(checkBoxArray[7]){
						str+='<th>SmartPhone Using</th>';
					  }
					  if(checkBoxArray[8]){
						str+='<th>Whatsapp Using</th>';
					  }
					  if(checkBoxArray[9]){
						str+='<th>Whatsapp Sharing</th>';
					  }
					  if(checkBoxArray[10]){
						str+='<th>Facebook Known</th>';
					  }
					  if(checkBoxArray[11]){
						str+='<th>FeedBack Answers</th>';
					  }
					  if(checkBoxArray[12]){
						str+='<th>FeedBack Documents</th>';
					  }
					  if(checkBoxArray[13]){
						str+='<th>Marks</th>';
					  }
					  /*str+='<th>IsFamilyUpdated</th>'
					  str+='<th>Achievements</th>'
					  str+='<th>Goals</th>'
					  str+='<th>Leadership <br/>Level</th>'
					  str+='<th>Communication<br/> Skills</th>'
					  str+='<th>Leadership Skills</th>'
					  str+='<th>Health</th>'
					  str+='<th>SmartPhone Using</th>'
					  str+='<th>Whatsapp Using</th>'
					  str+='<th>Whatsapp Sharing</th>'
					  str+='<th>Facebook Known</th>'*/
					  str+='<th>Update</th>';
                      str+='</thead>';                 
                     for(var j in results[i].subList){
					   str+='<tr>';
					   if(results[i].subList[j].image != null){
						   str+='<td><img src="images/cadre_images/'+results[i].subList[j].image+'" style="height:40px" class="img-reponsive"></td>'
					   }
					   else{
						   str+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive" ></td>'
					   }
					   str+='<td><a target="_blank" href="tdpCadreFamilyUpdationAction.action?task='+results[i].subList[j].id+'" title="Click here to update Cadre Profile.">'+results[i].subList[j].name+'</a>';
					   
					   <c:if test="${fn:contains(sessionScope.USER.isAdmin, 'true' )}">
						str+='<br><br><span style="color:#83BCE1;font-weight:bold;"><a target="_blank" href="cadreDetailsAction.action?cadreId='+results[i].subList[j].id+'" title="Click here to view Cadre Profile.">'+results[i].subList[j].memberShipId+'</a></span></td>';
					   </c:if>
					   
					   <c:if test="${fn:contains(sessionScope.USER.isAdmin, 'false' )}">
						str+='<br><br>'+results[i].subList[j].memberShipId+'</td>';
					   </c:if>
					   
					   str+='<td>'+results[i].subList[j].mobileno+'</td>'
					   str+='<td>'+results[i].subList[j].constituency+'</td>';
					   if(results[i].subList[j].committeeLevel.trim().length==0){
						   str+='<td> - ';
						   
						   if(results[i].subList[j].committeeType.trim().length==0){
						   }
						   else if(results[i].subList[j].committeeType == "Main"){
							    str+='<p><b>Designation</b> : '+results[i].subList[j].committeeRole+'</p>';
						   }
						   else{
							   str+='<p><b>Designation</b> : '+results[i].subList[j].committeeType+'- '+results[i].subList[j].committeeRole+'</p>';
						   }
						   
						  str+='</td>';
						   
					   }else{
						   str+='<td>'+results[i].subList[j].committeeLevel+' Committee ('+results[i].subList[j].position+') ';
						   
						   if(results[i].subList[j].committeeType.trim().length==0){
						   }
						   else if(results[i].subList[j].committeeType == "Main"){
							    str+='<p><b>Designation</b> : '+results[i].subList[j].committeeRole+'</p>';
						   }
						   else{
							   str+='<p><b>Designation</b> : '+results[i].subList[j].committeeType+'- '+results[i].subList[j].committeeRole+'</p>';
						   }
						   
						   str+='</td>';
					   }
					  /*  if(results[i].subList[j].committeeType.trim().length==0){
						   str+='<td> - </td>';
					   }else{
						   str+='<td>'+results[i].subList[j].committeeType+'- '+results[i].subList[j].committeeRole+'</td>';
					   } */
					   if(checkBoxArray[0]){
						   str+='<td>'+results[i].subList[j].familyUpdted+'</td>';
					   }
					  if(checkBoxArray[1]){
						if(results[i].subList[j].achievements){
					     str+='<td><span class="text-center achievmentsCls">Yes</span></td>';
					   }else{
					    str+='<td><span class="text-center achievmentsCls">No</span></td>';
					   }
					  }
					  if(checkBoxArray[2]){
						if(results[i].subList[j].goals){
						  str+='<td><span class="text-center goalsCls">Yes</span></td>';
					   }else{
						str+='<td><span class="text-center goalsCls">No</span></td>';
					   }
					  }
					  if(checkBoxArray[3]){
						if(results[i].subList[j].leaderShipLevels){					
						  str+='<td><span class="text-center leadershipLvlsCls">Yes</span></td>';
					   }else{
						   str+='<td><span class="text-center leadershipLvlsCls">No</span></td>';					    
					   }
					  }
					  if(checkBoxArray[4]){
						if(results[i].subList[j].communicationSkills){
							str+='<td><span class="text-center communicationSklsCls">Yes</span></td>';					     
					   }else{
						   str+='<td><span class="text-center communicationSklsCls">No</span></td>';					    
					   }
					  }
					  if(checkBoxArray[5]){
						if(results[i].subList[j].leaderShipSkills){
							str+='<td><span class="text-center leadershipSklsCls">Yes</span></td>';						     
					   }else{
						   str+='<td><span class="text-center leadershipSklsCls">No</span></td>';						   
					   }
					  }
					  if(checkBoxArray[6]){
						if(results[i].subList[j].health){
							str+='<td><span class="text-center healthCls">Yes</span></td>';						     
					   }else{
						   str+='<td><span class="text-center healthCls">No</span></td>';						    
					   }
					  }
					  if(checkBoxArray[7]){
						if(results[i].subList[j].smartphoneExist){
							str+='<td><span class="text-center smartphoneCls">Yes</span></td>';						     
					   }else{
					    str+='<td><span class="text-center smartphoneCls">No</span></td>';	
					   }
					  }
					  if(checkBoxArray[8]){
						if(results[i].subList[j].whatsappUsing){
							str+='<td><span class="text-center whatsappUsingCls">Yes</span></td>';						    
					   }else{
						   str+='<td><span class="text-center whatsappUsingCls">No</span></td>';						
					   }
					  }
					  if(checkBoxArray[9]){
						if(results[i].subList[j].whatsappSharing){
							str+='<td><span class="text-center whatsappSharingCls">Yes</span></td>';					     
					   }else{
						   str+='<td><span class="text-center whatsappUsingCls">No</span></td>';					  
					   }
					  }
					  if(checkBoxArray[10]){
						if(results[i].subList[j].facebookUsing){
							str+='<td><span class="text-center facebookUsingCls">Yes</span></td>';							 
					   }else{
						   str+='<td><span class="text-center facebookUsingCls">No</span></td>';						   
					   }
					  }
					  if(checkBoxArray[11]){
						str+='<td><span class="text-center facebookUsingCls">'+results[i].subList[j].feedBackAnswersCount+'</span></td>';							 
					  }
					  if(checkBoxArray[12]){
						str+='<td><span class="text-center facebookUsingCls">'+results[i].subList[j].feedBackDocumentsCount+'</span></td>';							 
					  }
					  if(checkBoxArray[13]){
						 if(results[i].subList[j].marks != null){
							str+='<td><span class="text-center facebookUsingCls">'+results[i].subList[j].marks+'</span></td>';							  
						 }else{
							 str+='<td><span class="text-center facebookUsingCls">-</span></td>';
						 }
						
					  }
					  /* if(results[i].subList[j].familyUpdted=='Yes'){
						   str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else if(results[i].subList[j].familyUpdted=='No'){
						  str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>' 
					   }
					   if(results[i].subList[j].achievements){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center achievmentsCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center achievmentsCls"></i></td>'
					   }
					   if(results[i].subList[j].goals){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center goalsCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center goalsCls"></i></td>'
					   }  
					   if(results[i].subList[j].leaderShipLevels){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center leadershipLvlsCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center leadershipLvlsCls"></i></td>'
					   }
					   if(results[i].subList[j].communicationSkills){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center communicationSklsCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center communicationSklsCls"></i></td>'
					   }
					   if(results[i].subList[j].leaderShipSkills){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center leadershipSklsCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center leadershipSklsCls"></i></td>'
					   }
					   if(results[i].subList[j].health){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center healthCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center healthCls"></i></td>'
					   }
					   
					   if(results[i].subList[j].smartphoneExist){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center smartphoneCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center smartphoneCls"></i></td>'
					   }
					   if(results[i].subList[j].whatsappUsing){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center whatsappUsingCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center whatsappUsingCls"></i></td>'
					   }
					   if(results[i].subList[j].whatsappSharing){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center whatsappSharingCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center whatsappSharingCls"></i></td>'
					   }
					   if(results[i].subList[j].facebookUsing){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center facebookUsingCls"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center facebookUsingCls"></i></td>'
					   }*/
					  if(results[i].isFeedbackUpdatable=='Y')
					     str+='<td><button  type="button" id="updateId'+results[i].subList[j].id+''+results[i].id+'" class="btn btn-success btn-xs updateClass updateLeaderShipSkills"   attr-cadreId='+results[i].subList[j].id+' attr-batchId='+results[i].id+'>UPDATE</button></td>';
                       else
						   str+='<td><button  disabled type="button" id="updateId'+results[i].subList[j].id+''+results[i].id+'" class="btn btn-success btn-xs updateClass updateLeaderShipSkills"   attr-cadreId='+results[i].subList[j].id+' attr-batchId='+results[i].id+'>UPDATE</button></td>';
					   str+='</tr>';
                    }
					str+='</table>';
                  str+='</div>';
				  str+='</div>';
              str+='</div>';
		   }
		   $("#ajaxImage").hide();
		   $("#accordion").html(str);
		   
		   <!--$(".temptable").dataTable();-->
		   $(".temptable").dataTable({"aaSorting": [[ 1, "desc" ]],
		   "aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]});
		}
		
	
	 $(document).on('click','.updateClass',function(){
	   <!--$.blockUI({image: src="./images/Optimizing Please Wait.gif"});-->
       var tdpCadreId=$(this).attr('attr-cadreId');
	   var batchId=$(this).attr('attr-batchId');
	   var enrollmentYearId = $('#enrollmentId').val();	
	   var jsObj={tdpCadreId:tdpCadreId,batchId:batchId,enrollmentYearId:enrollmentYearId}
	   $.ajax({
		  type:'POST',
		  url :'getDetailsForACadreAction.action',
		  data:{task:JSON.stringify(jsObj)},
	   }).done(function(results){
	      if(results!=null){
		    buildingData(results,tdpCadreId,batchId);
		  }
	   });
	   
     });
	 
   function myFunction(){
	var str='';
	str+='<div class="row" id="list" >'
		str+='<div class="col-md-11">'
			str+='<input class="form-control txtbox achievmentCls m_top10" type="text">'
		str+='</div>'
		str+='<div class="col-md-1 ">'
			str+='<i class="glyphicon glyphicon-minus m_top10 add-plus trash"></i>'
		str+='</div>'
	str+='</div>'
	$("#addMoreDiv").append(str);
	}
	
   function myDateFunction(){
   globalDateCount=globalDateCount+1;
   var str='';
   str+='<div class="row dateListClass" id="dateList'+globalDateCount+'" attr-id="'+globalDateCount+'">'
		str+='<div class="col-md-6">'
			str+='<input class="form-control datetxtbox goalsTxtCls m_top10" type="text" id="goalsClass'+globalDateCount+'" attr-id="'+globalDateCount+'">'
			str+='<div class="mandatory" id="goalErrDivId'+globalDateCount+'"></div>'
		str+='</div>'
		str+='<div class="col-md-4">'
			str+='<div class="input-group date reportrange datetxtboxD m_top10">';
				str+='<input type="text"  class="form-control goalsDateClass"  id="goalsDateId'+globalDateCount+'" attr-id="'+globalDateCount+'"/>';
				str+='<span class="input-group-addon">';
					str+='<span class="glyphicon glyphicon-calendar"></span>';
				str+='</span>';
			str+='</div>';
			str+='<div class="mandatory" id="dateErrDivId'+globalDateCount+'"></div>'
		str+='</div>'
		str+='<div class="col-md-1">'
			str+='<i class="glyphicon glyphicon-minus add-plus datetrash m_top10"></i>'
		str+='</div>'
    str+='</div>'
	$("#addMoreDateDiv").append(str);
	
	var datePickerId ="#goalsDateId"+globalDateCount;
	$(datePickerId).datetimepicker({format: "MM/DD/YYYY"});
   }
   function buildingData(results,tdpCadreId,batchId){
   var datePkrIds = [];
   var str=''
   str+='<div id="popupdivId" class="modal-body">';
		str+='<div class="row">'
			str+='<div class="col-md-12">'
				str+='<div class="table-responsive">'
				str+='<table class="table table-bordered m_0" style="font-size:12px">'
					
					str+='<tr>'
						str+='<td></td>'
						str+='<td>Name</td>'
						str+='<td>Designation</td>'
						str+='<td>Mobile No</td>'
						str+='<td>Constituency</td>'
						str+='<td>District</td>'
						str+='<td>Trained On</td>'
					str+='</tr>'
					str+='<tr>'
					    if(results.image==null || results.image.trim().length<=0)
						 str+='<td><img src="dist/img/profile-img.png" class="img-responsive" style="width:30px;"></td>'
						else
						str+='<td><img src="images/cadre_images/'+results.image+'" class="img-responsive" style="width:30px;"></td>'
						str+='<td>'+results.name+'</td>'
						if(results.designation == null || results.designation.trim().lenght == 0){
							str+='<td>-</td>'
						}else{
							str+='<td>'+results.designation+'</td>'
						}
						str+='<td>'+results.mobileno+'</td>'
						str+='<td>'+results.constituency+'</td>'
						str+='<td>'+results.districtName+'</td>'
						str+='<td>'+results.programName+'</td>'
					str+='</tr>'
					
				str+='</table>'
				str+='</div>'
			str+='</div>'
		str+='</div>'
		//Achievements
        str+='<div class="row m_top20">'
			str+='<div class="col-md-12">'
				str+='<label>Achievements</label>'
				
				if(results.achievementsList!=null && results.achievementsList.length>0){
				   str+='<div id="addMoreDiv">'
				   for(var i in results.achievementsList){
				    str+='<div class="row">'
					str+='<div class="col-md-11">'
						str+='<input class="form-control txtbox achievmentCls m_top10" type="text" attr-id="'+results.achievementsList[i].id+'" value="'+results.achievementsList[i].name+'">'
					str+='</div>'
					str+='<div class="col-md-1 ">'
						str+='<i class="glyphicon glyphicon-minus m_top10 add-plus trash"></i>'
					str+='</div>'
				    str+='</div>'
				   }
				   str+='</div>'
				}else{
				   str+='<div id="addMoreDiv">'
				   str+='</div>'
				}
				
				str+='<div class="row m_top10">'
					str+='<div class="col-md-11">'
						str+='<input class="form-control achievmentCls" type="text">'
					str+='</div>'
					str+='<div class="col-md-1">'
						str+='<i class="glyphicon glyphicon-plus m_top10 add-plus" onclick="myFunction();"></i>'
					str+='</div>'
				str+='</div>'
				
			str+='</div>'
        str+='</div>'
		//Goals
        str+='<div class="row m_top20">'
			str+='<div class="col-md-12">'
				str+='<div class="col-md-6">'
				str+='<label>Goals</label>'
				str+='</div>'
				str+='<div class="col-md-4">'
				str+='<label>Date </label>'
				str+='</div>'
				
				if(results.goalsList!=null && results.goalsList.length>0){
				  str+='<div id="addMoreDateDiv">'
				  for(var i in results.goalsList){
				   globalDateCount=globalDateCount+1;
				      str+='<div class="row dateListClass" id="dateList'+globalDateCount+'" attr-id="'+globalDateCount+'">'
						str+='<div class="col-md-6">'
							str+='<input class="form-control datetxtbox goalsTxtCls m_top10" id="goalsClass'+globalDateCount+'" attr-id="'+globalDateCount+'" type="text" value="'+results.goalsList[i].name+'">'
							str+='<div class="mandatory" id="goalErrDivId'+globalDateCount+'"></div>'
						str+='</div>'
						str+='<div class="col-md-4">'
							str+='<div class="input-group date reportrange datetxtboxD m_top10">';
								str+='<input type="text" class="form-control goalsDateClass" id="goalsDateId'+globalDateCount+'" attr-id="'+globalDateCount+'" value="'+results.goalsList[i].dateString+'"/>';
								str+='<span class="input-group-addon">';
									str+='<span class="glyphicon glyphicon-calendar"></span>';
								str+='</span>';
							str+='</div>';
							str+='<div class="mandatory" id="dateErrDivId'+globalDateCount+'"></div>'
						str+='</div>'
						str+='<div class="col-md-1">'
							str+='<i class="glyphicon glyphicon-minus add-plus datetrash m_top10"></i>'
						str+='</div>'
				      str+='</div>'
					  datePkrIds.push("#goalsDateId"+globalDateCount);
					  
				  }
				  str+='</div>'
				}else{
				  str+='<div id="addMoreDateDiv">'
				  str+='</div>'
				}
				
				str+='<div class="row m_top10 dateListClass" id="dateList0" attr-id="0">'
					str+='<div class="col-md-6">'
						str+='<input class="form-control goalsTxtCls" id="goalsClass0" attr-id="0" type="text">'
						str+='<div class="mandatory" id="goalErrDivId0"></div>'
					str+='</div>'
					str+='<div class="col-md-4">'
						str+='<div class="input-group date reportrange">';
							str+='<input type="text" class="form-control goalsDateClass" id="goalsDateId0" attr-id="0" />';
							str+='<span class="input-group-addon">';
								str+='<span class="glyphicon glyphicon-calendar"></span>';
							str+='</span>';
						str+='</div>';
						str+='<div class="mandatory" id="dateErrDivId0"></div>'
					str+='</div>'
					
					str+='<div class="col-md-1">'
						str+='<i class="glyphicon glyphicon-plus add-plus" onclick="myDateFunction();"></i>'
					str+='</div>'
				str+='</div>'
				datePkrIds.push("#goalsDateId0");
		str+='</div>'
		str+='</div>'
		
        str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Leadership Level</label><span class="mandatory">*</span>'
				str+='<div class="mandatory" id="leadershipLvlErrDivId"></div>';
				str+='<select class="form-control" id="leadershipLevelId">'
				str+='<option value="0">Select</option>'
                for(var i in leaderShipLevelArray){
				  if(results.leaderShipLevelId==leaderShipLevelArray[i].id)
				    str+='<option value="'+leaderShipLevelArray[i].id+'" selected>'+leaderShipLevelArray[i].name+'</option>'
				  else
                  str+='<option value="'+leaderShipLevelArray[i].id+'">'+leaderShipLevelArray[i].name+'</option>'
				}				
               str+=' </select>'
			str+='</div>'
        str+='</div>'
		
        str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Communication Skills</label><span class="mandatory">*</span>'
				str+='<div class="mandatory" id="commSkillsErrDivId"></div>';
				str+='<select class="form-control" id="communicationSkillsId">'
                str+='<option value="0">Select</option>'
                 for(var i in communicationSkillsArray){
				  if(results.communicationSkillsStatusId==communicationSkillsArray[i].id)
				    str+='<option value="'+communicationSkillsArray[i].id+'" selected>'+communicationSkillsArray[i].name+'</option>'
				  else
                  str+='<option value="'+communicationSkillsArray[i].id+'">'+communicationSkillsArray[i].name+'</option>'
				}	    
                str+='</select>'
			str+='</div>'
        str+='</div>'
		
        str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Leadership Skills</label><span class="mandatory">*</span>'
				str+='<div class="mandatory" id="leadershipSkillsErrDivId"></div>';
				str+='<select class="form-control" id="leaderShipSkillsId">'
                str+='<option value="0">Select</option>'
                for(var i in leaderShipSkillsArray){
				  if(results.leaderShipSkillsStatusId==leaderShipSkillsArray[i].id)
				    str+='<option value="'+leaderShipSkillsArray[i].id+'" selected>'+leaderShipSkillsArray[i].name+'</option>'
				  else
                  str+='<option value="'+leaderShipSkillsArray[i].id+'">'+leaderShipSkillsArray[i].name+'</option>'
				}	    
                str+='</select>'
			str+='</div>'
        str+='</div>'
		
        str+='<div class="row">';
        	str+='<div class="col-md-12">';
            	str+='<label>Health</label><span class="mandatory">*</span>';
				str+='<div class="mandatory" id="healthErrDivId"></div>';
				str+='<select class="form-control" id="healthId">'
                str+='<option value="0">Select</option>';
                for(var i in healthStatusArray){
				  if(results.healthStatusId==healthStatusArray[i].id)
				    str+='<option value="'+healthStatusArray[i].id+'" selected>'+healthStatusArray[i].name+'</option>'
				  else
                  str+='<option value="'+healthStatusArray[i].id+'">'+healthStatusArray[i].name+'</option>'
				}	   
               str+='</select>';
			   
			 str+='</div>'
        str+='</div>'
		
		  str+='<div class="row">';
		  str+='<div class="col-md-4">';
		  str+='<label>Health Card </label>';
		  str+='</div>';
		  str+='</div>';
		  str+='<div class="row" id="healthCardCntaddDiv">';
		  str+='</div>';
		  str+='<div class="col-md-4 m_top10 ">';
		  str+='<input type="file" class="healthCardattachment" />';
		  str+='</div>';
		  str+='<div class="col-md-1 m_top10">';
		  str+='<i class="glyphicon glyphicon-plus m_top10 add-plus healthCardadd" onclick="addHealthCard();"></i>';
		  str+='</div>';
		  
		
		
		<!-- Adding -->
		
		 str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Do You Have Smart phone(Android or IPhone or Windows)?</label>'
				str+='<select class="form-control" id="smartPhoneId" onChange="disHideShow()">'
                str+='<option value="select">Select</option>'
				if(results.smartphone=='Y')
				  str+='<option value="Y" selected>Yes</option>'
			    else
				  str+='<option value="Y">Yes</option>'
			    if(results.smartphone=='N')
				 str+='<option value="N" selected>No</option>'
			    else
				 str+='<option value="N">No</option>'
               str+='</select>'
			 str+='</div>'
        str+='</div>'
		
		if(results.smartphone=='Y'){
			str+='<div id="whatsappDivId">';
		}else
		 str+='<div id="whatsappDivId" style="display:none">';
		
		 str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Do you know about Whatsapp?</label>'
				str+='<select class="form-control" id="whatsappId">'
                str+='<option value="select">Select</option>'
				
                if(results.whatsapp=='Y')
				  str+='<option value="Y" selected>Yes</option>'
			    else
				  str+='<option value="Y">Yes</option>'
			    if(results.whatsapp=='N')
				 str+='<option value="N" selected>No</option>'
			    else
				 str+='<option value="N">No</option>'
				
               str+='</select>'
			 str+='</div>'
        str+='</div>'
		
		
		 str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Do you share the party information to cadre,people in your region?</label>'
				str+='<select class="form-control" id="whatsappShareId">'
                str+='<option value="select">Select</option>'
				 if(results.whatsappShare=='Y')
				  str+='<option value="Y" selected>Yes</option>'
			    else
				  str+='<option value="Y">Yes</option>'
			    if(results.whatsappShare=='N')
				 str+='<option value="N" selected>No</option>'
			    else
				 str+='<option value="N">No</option>'
			 
               str+='</select>'
			 str+='</div>'
        str+='</div>'
	  str+='</div>';
		
		 str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Do you know about Facebook?</label>'
				str+='<select class="form-control" id="facebookId">'
                str+='<option value="select">Select</option>'
                 if(results.facebook=='Y')
				  str+='<option value="Y" selected>Yes</option>'
			    else
				  str+='<option value="Y">Yes</option>'
			    if(results.facebook=='N')
				 str+='<option value="N" selected>No</option>'
			    else
				 str+='<option value="N">No</option>'
               str+='</select>'
			 str+='</div>'
        str+='</div>'
		
	
		<!-- Adding -->
		
		
		
		str+='<div class="row">'
			str+='<div class="col-md-12">'
				str+='<label>Comments</label>'
				if(results.remarks!=null)
				  str+='<textarea class="form-control" id="commentsId">'+results.remarks+'</textarea>'
				else
				 str+='<textarea class="form-control" id="commentsId"></textarea>'
			str+='</div>'
		str+='</div>'
      str+='</div>'
	  
	$("#modalBodyId").html(str);
	
	for(var i in datePkrIds){
		var datePickerId =datePkrIds[i];
		var dpIdVal = $(datePickerId).val();
		$(datePickerId).datetimepicker({
            format: "MM/DD/YYYY"
        });
		if(dpIdVal!=""){
			$(datePickerId).val(dpIdVal)
		}
	}
	
	
	var str1='';
	str1+='<span id="updatedId" style="display:none" class="text-success pull-left">Updated Successfully...</span>';
	str1+='<span id="notUpdatedId" style="display:none" class="text-success pull-left">Sorry..Details Are Not Updated...</span>';
	str1+='<span id="processingId" style="display:none" class="text-danger pull-left"><span><img id="ajaxImage1" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:15px;display:none;"/></span>Please Wait While Updating...</span>';
    str1+='<button type="button" class="btn btn-default" data-dismiss="modal" id="closePopUp">Close</button>';
    str1+='<button type="button" class="btn btn-primary" onclick="saveAllDetails('+tdpCadreId+','+batchId+');">Save</button>';
	$("#modalFooterId").html(str1);
	$.unblockUI();
	$("#myModal").modal("show");
   }
   
   function disHideShow(){
	   
	   var smartPhoneId=$("#smartPhoneId").val();
	   if(smartPhoneId=='Y'){
		   $('#whatsappDivId').show();
	   }else{
		  $('#whatsappId').val('select');
		  $('#whatsappShareId').val('select');
		  $('#whatsappDivId').hide(); 
	   }
   }
   
   
   var existDeleteAchieveArray=[];
	$(document).on('click', '.trash', function(){
	   if($(this).attr('attr-id')){
		   existDeleteAchieveArray.push($(this).attr('attr-id'));
		}
	   $(this).parent().parent().remove();
		   
    });

	
	
	$(document).on('click', '.datetrash', function(){
	   
	   $(this).parent().parent().remove();
		   
    });
	
	$(document).on('click', '.healthCardminus', function(){
	   
	   $(this).parent().parent().remove();
		   
    });
	
	/*  $('body').on('click','.goalsDateClass', function() {
		
			$(this).datetimepicker();
		});
 */
	
   function saveAllDetails(tdpCadreId,batchId)
   {
		$("#leadershipLvlErrDivId").html('');
		$("#commSkillsErrDivId").html('');
		$("#leadershipSkillsErrDivId").html('');
		$("#healthErrDivId").html('');
   
       //feedbacks   
	   var leaderShipLevel = $("#leadershipLevelId").val();
	   var communicationSkills = $("#communicationSkillsId").val();
	   var leaderShipSkills = $("#leaderShipSkillsId").val();
	   var health = $("#healthId").val();
	   var comments = $("#commentsId").val();
	   
	   var smartPhoneId=$("#smartPhoneId").val();
       var whatsappId=$("#whatsappId").val();
	   var whatsappShareId=$("#whatsappShareId").val();
       var facebookId=$("#facebookId").val();
	 
	  
	   //Achievements.
	   var achieveArray=[];
	   $(".achievmentCls").each(function(){
	      if($(this).val().trim().length>0){
	        achieveArray.push($(this).val());
		  }
	   });
	    //goals
	   var goalArray=[];
	   var flag=false;
	   $(".dateListClass").each(function(){
	      var id=$(this).attr("attr-id");
		  var goal=$("#goalsClass"+id).val();
		  var date=$("#goalsDateId"+id).val();
		  
		  $("#dateErrDivId"+id).html('');
		  $("#goalErrDivId"+id).html('');
		  
		  if(goal.trim().length>0 && date.trim().length<=0){
			$("#dateErrDivId"+id).html("Please Give Date");
			flag=true;
			return flag;
		  }
		  if(goal.trim().length<=0 && date.trim().length>0){
			$("#goalErrDivId"+id).html("Please Give Goal");
			flag=true;
			return flag;
		  }
		  
		  if(goal.trim().length>0 && date.trim().length>0){
			 var goalObject;
			// goalObject.goal = goal; 
			// goalObject.date = date;
			goalObject = goal+"-" +date;
			 goalArray.push(goalObject);
			 }
		  
           /* if(goal.trim().length>0){
			  if(date.trim().length<=0){
				  alert("Enter Date.");
				  return;
			  }
		  }
         if(date.trim().length>0){
			 if(goal.trim().length<=0){
				 alert("Enter Goal.");
				 return;
			 }
		 }  */
		 /*var goalObject = new Object();
         goalObject.goal = goal; 
         goalObject.date = date;
         goalArray.push(goalObject);*/
	   });
	   
	   if(flag){
		return;
	   }
	   
	   if(leaderShipLevel == 0){
	        $("#leadershipLvlErrDivId").html("Please Select Leadership Level");
			return;
	   }
	   if(communicationSkills == 0){
	        $("#commSkillsErrDivId").html("Please Select Communication Skills");
			return;
	   }
	   if(leaderShipSkills == 0){
	        $("#leadershipSkillsErrDivId").html("Please Select Leadership Skills");
			return;
	   }
	   if(health == 0){
	        $("#healthErrDivId").html("Please Select Health Level");
			return;
	   }
	   
	   $("#processingId").show();
	$("#ajaxImage1").show();
	 var formData = new FormData();  
	  var inputs = $(".healthCardattachment");
	 $.each(inputs, function (obj, v) {
        var file = v.files[0];
  
	 formData.append("image",file);
	})
	
	 formData.append('leaderShipLevel',leaderShipLevel);
	 formData.append('achieveArray',achieveArray);
	 formData.append('goalArray',goalArray);
	 formData.append('communicationSkills',communicationSkills);
	 formData.append('leaderShipSkills',leaderShipSkills);
	 formData.append('health',health);
	 formData.append('comments',comments);
	 formData.append('tdpCadreId',tdpCadreId);
	 formData.append('batchId',batchId);
	 formData.append('smartPhoneId',smartPhoneId);
	 formData.append('whatsappId',whatsappId);
	 formData.append('whatsappShareId',whatsappShareId);
	 formData.append('facebookId',facebookId);
	
	  /* var jsObj=
	   {	
			achieveArray:achieveArray,
			goalArray:goalArray,
			leaderShipLevel:leaderShipLevel,
			communicationSkills:communicationSkills,
			leaderShipSkills:leaderShipSkills,
			health:health,
			comments:comments,
			tdpCadreId:tdpCadreId,
			batchId:batchId,
			smartPhoneId:smartPhoneId,
			whatsappId:whatsappId,
            whatsappShareId:whatsappShareId,
            facebookId:facebookId,
		}*/
		
		$.ajax({
		  type:'POST',
		  processData: false,  // tell jQuery not to process the data
          contentType: false,  // tell jQuery not to set contentType
		  data: formData,
		  url :'saveDetailsOfCadreAction.action',
		// data:{task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 1){
				setTimeout(function(){
				  $("#closePopUp").trigger("click");
				}, 1000);
				 $("#processingId").hide();
				 $("#ajaxImage1").hide();
				 $("#updatedId").show();
				 
                 
				  if(result.achievements){
				  $("#updateId"+tdpCadreId+batchId).parent().parent().find(".achievmentsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center achievmentsCls"></i>'); 
				 }
				 else{
				 $("#updateId"+tdpCadreId+batchId).parent().parent().find(".achievmentsCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center achievmentsCls"></i>');
				} 
				if(result.goals){
                 $("#updateId"+tdpCadreId+batchId).parent().parent().find(".goalsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center goalsCls"></i>');
				 }else{
				 $("#updateId"+tdpCadreId+batchId).parent().parent().find(".goalsCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center goalsCls"></i>');
			     }
                $("#updateId"+tdpCadreId+batchId).parent().parent().find(".leadershipLvlsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center leadershipLvlsCls"></i>');				 
			    $("#updateId"+tdpCadreId+batchId).parent().parent().find(".communicationSklsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center communicationSklsCls"></i>');
				$("#updateId"+tdpCadreId+batchId).parent().parent().find(".leadershipSklsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center leadershipSklsCls"></i>');
				$("#updateId"+tdpCadreId+batchId).parent().parent().find(".healthCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center healthCls"></i>');
				
				$("#updateId"+tdpCadreId+batchId).parent().parent().find(".leadershipLvlsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center leadershipLvlsCls"></i>');				 
			    $("#updateId"+tdpCadreId+batchId).parent().parent().find(".communicationSklsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center communicationSklsCls"></i>');
				$("#updateId"+tdpCadreId+batchId).parent().parent().find(".leadershipSklsCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center leadershipSklsCls"></i>');
				$("#updateId"+tdpCadreId+batchId).parent().parent().find(".healthCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center healthCls"></i>');
				
				if(smartPhoneId=='select'){
					$("#updateId"+tdpCadreId+batchId).parent().parent().find(".smartphoneCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center smartphoneCls"></i>');
                    $("#updateId"+tdpCadreId+batchId).parent().parent().find(".whatsappUsingCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center whatsappUsingCls"></i>');
                    $("#updateId"+tdpCadreId+batchId).parent().parent().find(".whatsappSharingCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center whatsappSharingCls"></i>');					
				}else{
					$("#updateId"+tdpCadreId+batchId).parent().parent().find(".smartphoneCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center smartphoneCls"></i></i>');
					if(whatsappId=='select'){
						 $("#updateId"+tdpCadreId+batchId).parent().parent().find(".whatsappUsingCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center whatsappUsingCls"></i>');
					}else{
						$("#updateId"+tdpCadreId+batchId).parent().parent().find(".whatsappUsingCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center whatsappUsingCls"></i>');
					}
					if(whatsappShareId=='select'){
						 $("#updateId"+tdpCadreId+batchId).parent().parent().find(".whatsappSharingCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center whatsappSharingCls"></i>');
					}else{
						$("#updateId"+tdpCadreId+batchId).parent().parent().find(".whatsappSharingCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center whatsappSharingCls"></i>');
					}
				}
				if(facebookId=='select'){
					$("#updateId"+tdpCadreId+batchId).parent().parent().find(".facebookUsingCls").parent().html('<i class="glyphicon glyphicon-remove text-danger text-center facebookUsingCls"></i>');
				}else{
						$("#updateId"+tdpCadreId+batchId).parent().parent().find(".facebookUsingCls").parent().html('<i class="glyphicon glyphicon-ok text-success text-center facebookUsingCls"></i>');
				}
				
			}else{
				$("#processingId").hide();
				 $("#ajaxImage1").hide();
				 $("#notUpdatedId").show();
			}
		});
   }
   
   function getBatchesByProgramAndCenter()
   {
	  $("#programSelErrDivId").html('');
	  $("#centerSelErrDivId").html('');
	  $("#batchSelErrDivId").html('');
	  
	  var programId = $("#programId").val();
	  var centerId = $("#centerId").val();
	  var batchId  = $("#batchId").val();
	  
	  if(programId == 0){
		$("#programSelErrDivId").html("Please Select Any Program");
		return;
	  }
	  if(centerId == 0){
		$("#centerSelErrDivId").html("Please Select Any Center");
		return;
	  }
		if(batchId == 0){
				$("#batchSelErrDivId").html("Please Select Any Batch");
				return;
		}
	  
	  $("#checkBoxDiv").show();
	  var checkBoxArray;
	  
	  var isFamilyUpdated = document.getElementById("isFamilyUpdatedId").checked;
	  var achievements = document.getElementById("acheivementsId").checked;
	  var goals = document.getElementById("goalsId").checked;
	  var leadershipLevel = document.getElementById("leadershipLevelCheckId").checked;
	  var communicationSkills = document.getElementById("communicationSkillsCheckId").checked;
	  var leadershipSkills = document.getElementById("leadershipSkillsCheckId").checked;
	  var health = document.getElementById("healthCheckId").checked;
	  var smartPhoneUsing = document.getElementById("smartPhoneUsingId").checked;
	  var whatsupUsing = document.getElementById("whatsupUsingId").checked;
	  var whatsupSharing = document.getElementById("whatsupSharingId").checked;
	  var facebookKnown = document.getElementById("facebookKnownId").checked;
	  var feedBackAnswer = document.getElementById("feedBackAnswerChkId").checked;
	  var feedBackDocuments = document.getElementById("feedBackDocumentsChkId").checked;
	  var feedBackMarks = document.getElementById("feedBackMarksChkId").checked;
	  
	  checkBoxArray = [isFamilyUpdated,achievements,goals,leadershipLevel,communicationSkills,leadershipSkills,health,smartPhoneUsing,whatsupUsing,whatsupSharing,facebookKnown,feedBackAnswer,feedBackDocuments,feedBackMarks];
	  
	  $("#ajaxImage").show();
	  $("#accordion").html('');
	  	  
		getDayWiseAttendnenceForBatch();
		  
	  var jsObj=
	  {
		 programId:programId,
		 centerId:centerId,
		 batchId:batchId,
		 enrollmentYearId:$('#enrollmentId').val()		 
	  }
	  
	 $.ajax({
		type:'POST',
		url :'getAllBatchesByProgramAndCenterAction.action',
		data:{task:JSON.stringify(jsObj)},
	 }).done(function(results){
		 if(results!=null && results.length>0){
		     buildCadreDetails(results,checkBoxArray);
		}else{
			$("#ajaxImage").hide();
			 $("#accordion").html("No Data Available...");
		    //alert("NO Data Available.");
		}
	});
   }
   
     function getPrograms(){
			 $.ajax({
			  type:'POST',
			  url :'getProgramsAction.action',
			  data:{},
		    }).done(function(result){
				
			  <c:if test="${fn:contains(sessionScope.USER.isAdmin, 'true' )}">
				
				var str='';
				str+='<select class="form-control" id="programId">';
				str+='<option value="0">Select Program</option>';
				if(result.simpleVOList1!=null &&result.simpleVOList1.length>0){
                 for(var i in result.simpleVOList1){
					 if(parseInt(result.simpleVOList1[i].id) == 8)
						str+='<option value="'+result.simpleVOList1[i].id+'">'+result.simpleVOList1[i].name+'</option>';
                  }
                }
               str+='</select>';				
			$("#programSelDivId").html(str);
			
			$('#centerId').find('option').remove();
			$('#centerId').append('<option value="0">Select Center</option>');
			if(result.simpleVOList2!=null &&result.simpleVOList2.length>0){
			 for(var i in result.simpleVOList2){
			   $('#centerId').append('<option value="'+result.simpleVOList2[i].id+'">'+result.simpleVOList2[i].name+'</option>');
			  }
			}
			  </c:if>
			  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT' )}">
				var str='';
				str+='<select class="form-control" id="programId" onChange="getCampsByProgramAndUser()">';
				str+='<option value="0">Select Program</option>';
				if(result.simpleVOList1!=null &&result.simpleVOList1.length>0){
                 for(var i in result.simpleVOList1){
					 	 if(parseInt(result.simpleVOList1[i].id) == 8)
							str+='<option value="'+result.simpleVOList1[i].id+'">'+result.simpleVOList1[i].name+'</option>';
                  }
                }
               str+='</select>';				
			$("#programSelDivId").html(str);
				
		      </c:if>	
			});
		 }
	  function getCampsByProgramAndUser(){
		 
		  $('#centerId').find('option').remove();
          $('#centerId').append('<option value="0">Select Center</option>');
		  var campProgramId=$("#programId option:selected").val();
		  if(campProgramId==0){
			  return;
		  }
		  var jsObj={campProgramId:campProgramId}
	      $.ajax({
		    type:'POST',
		    url :'getCampsByProgramAndUserAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(results){
			   
			  if(results!=null && results.length>0){
				  for(var i in results){
					 $('#centerId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
				  }
			  }
			  
		  });
	  }
	  healthCardCnt = 0;
	  function addHealthCard()
	  {
		
		  var str ='';
		 
		  str+='<div class="row" id="healthCardCnt"+healthCardCnt+"">';
		  str+='<div class="col-md-4 m_top10 ">';
		  str+='<input type="file" class="healthCardattachment" />';
		  str+='</div>';
		  str+='<div class="col-md-1 m_top10">';
		  str+='<i class="glyphicon glyphicon-minus add-plus healthCardminus m_top10"></i>';
		  str+='</div>';
		  $("#healthCardCntaddDiv").append(str);
		   healthCardCnt ++;
	  }
	  </script>
	  <script>
	  
	  function getBatchesForCentre(campId){
	  
			$("#exportExcelDivId").hide();
			$('#batchId').find('option').remove();
			$('#batchId').append('<option value="0">Select Batch</option>');
			
			var programId = $("#programId").val();
			var campId = $("#centerId").val();
				  
		  var jsObj={
			  programId :programId,
			  campId :campId
		  }
		  $.ajax({
		    type:'POST',
		    url :'getBatchesForCentreAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(results){
			  if(results !=null){
					for(var i in results){
				   if(results[i].id == 0){
					  $("#batchId").append('<option value='+results[i].id+'>ALL</option>');
				   }else{
					  $("#batchId").append('<option value='+results[i].id+'>'+results[i].name+'</option>');
				   }
				 }
			  }
		  });
	  }
	  
	  function getCadreAttendneceByBatch(batchId){
		  var programId = $("#programId").val();
		  var centerId = $("#centerId").val();
		  var batchId  = $("#batchId").val();
		  if(programId == 0){
			$("#programSelErrDivId").html("Please Select Any Program");
			return;
		  }
		  if(centerId == 0){
			$("#centerSelErrDivId").html("Please Select Any Center");
			return;
		  }
		  if(batchId == 0){
				$("#batchSelErrDivId").html("Please Select Any Batch");
				  return;
			}
		  $("#batchSelErrDivId,#centerSelErrDivId,#programSelErrDivId").html('');
		  $("#ajaxImage").show();
		  $("#attendanceDiv").html('');
		  $("#attendanceDiv").show();
		  $("#exportExcelAttendanceDiv").html('');
	 
		  getDayWiseAttendnenceForBatch();
		  
		  var jsObj={
			  batchId : batchId,
			  enrollmentYearId:$('#enrollmentId').val()	
		  }
		  $.ajax({
		    type:'POST',
		    url :'getDateWiseAttendedAndAbsentCandidatesAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(results){
			  if(results !=null){
				  $("#ajaxImage").hide();
				  buildDateWiseAttendedAndAbsentCandidatesDetails(results);
			  }
		  });
	  }
	  
function buildDateWiseAttendedAndAbsentCandidatesDetails(results)
{
	var str='';
	str+='<div class="table-responsive m_top10">';
		str+='<table id="attendanceTableId" class="table table-bordered attendanceTable">';
			str+='<thead class="bg_d">';
				str+='<th>Image</th>';
				str+='<th>Name</th>';
				str+='<th>Mobile</th>';
				str+='<th>Committee</th>';
				str+='<th>Constituency</th>';
				var dates = results[0].simpleVoList;
				if(dates != null && dates.length > 0){
					var day = 1;
					for(var i in results[0].simpleVoList){
						var k = Number(day) + Number(i);
						str+='<th>Day '+k+' ('+results[0].simpleVoList[i].dateString+')</th>';
					}
				}else{
					str+='<th>Day 1</th>';
					str+='<th>Day 2</th>';
					str+='<th>Day 3</th>';
				}
			str+='</thead>';
			for(var i in results){
				str+='<tr>';
					if(results[i].image != null){
						str+='<td><img src="images/cadre_images/'+results[i].image+'" style="height:40px" class="img-reponsive"></td>';
					}else{
						str+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>';
					}
					
					if(results[i].cadreId !=null && results[i].cadreId > 0){
						str+='<td><a target="_blank" href="tdpCadreFamilyUpdationAction.action?task='+results[i].cadreId+'"  title="Click here to update Cadre Profile.">'+results[i].firstName+'</a>';
	<c:if test="${fn:contains(sessionScope.USER.isAdmin, 'true' )}">  
	str+='<br><br><span style="color:#83BCE1;font-weight:bold;"><a target="_blank" href="cadreDetailsAction.action?cadreId='+results[i].cadreId+'" title="Click here to view Cadre Profile.">'+results[i].membershipNoStr+'</a></span></td>';
						
	</c:if>
						<c:if test="${fn:contains(sessionScope.USER.isAdmin, 'false' )}">
						str+='<br>'+results[i].membershipNoStr+'</td>';
						</c:if>
					}
					else{
						str+='<td>'+results[i].firstName+'</td>';
					}
					str+='<td>'+results[i].mobileNo+'</td>';
					if(results[i].designationLocation !=null && results[i].designationLocation !=""){
						str+='<td>'+results[i].designationLocation+'';
							if(results[i].designation !=null && results[i].designation != ""){
							str+='<p>'+results[i].designation+'</p>';
							}
						str+='</td>';
					}else{
						str+='<td>-';
						if(results[i].designation !=null && results[i].designation != ""){
							str+='<p>'+results[i].designation+'</p>';
							}
						str+='</td>';
					}
					
					
					str+='<td>'+results[i].constituencyName+'</td>';
					for(var j in results[i].simpleVoList){
						str+='<td style="text-align:center">'+results[i].simpleVoList[j].isAttended+'</td>';
						/*if(results[i].simpleVoList[j].isAttended == "Attended"){
							str+='<td><input type="checkbox" checked="true"></td>';
						}else{
							str+='<td><input type="checkbox"></td>';
						}*/
					}
				str+='</tr>';
			}
			
		str+='</table>';
	str+='</div>';
	$("#attendanceDiv").html(str);
	$("#exportExcelDivId").show();
	
	$(".attendanceTable").dataTable();
	
	var str1='';
	str1+='<div class="table-responsive m_top10">';
		str1+='<table id="exportExcelAttendanceTableId" class="table table-bordered exportExcelAttendanceTable">';
			str1+='<thead class="bg_d">';
				//str1+='<th>Image</th>';
				str1+='<th>Name</th>';
				str1+='<th>Mobile</th>';
				str1+='<th>Committee</th>';
				str1+='<th>Designation</th>';
				str1+='<th>Constituency</th>';
				var dates = results[0].simpleVoList;
				if(dates != null && dates.length > 0){
					var day = 1;
					for(var i in results[0].simpleVoList){
						var k = Number(day) + Number(i);
						str1+='<th>Day '+k+' ('+results[0].simpleVoList[i].dateString+')</th>';
					}
				}else{
					str1+='<th>Day 1</th>';
					str1+='<th>Day 2</th>';
					str1+='<th>Day 3</th>';
				}
			str1+='</thead>';
			for(var i in results){
				str1+='<tr>';
					/*if(results[i].image != null){
						str1+='<td><img src="images/cadre_images/'+results[i].image+'" style="height:40px" class="img-reponsive"></td>';
					}else{
						str1+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>';
					}*/
					
					if(results[i].cadreId !=null && results[i].cadreId > 0){
						str1+='<td>'+results[i].firstName+'</td>'
					}
					else{
						str1+='<td>'+results[i].firstName+'</td>';
					}
					str1+='<td>'+results[i].mobileNo+'</td>';
					str1+='<td>'+results[i].designationLocation+'</td>';
					str1+='<td>'+results[i].designation+'</td>';
					str1+='<td>'+results[i].constituencyName+'</td>';
					for(var j in results[i].simpleVoList){
						str1+='<td style="text-align:center">'+results[i].simpleVoList[j].isAttended+'</td>';
						/*if(results[i].simpleVoList[j].isAttended == "Attended"){
							str+='<td><input type="checkbox" checked="true"></td>';
						}else{
							str+='<td><input type="checkbox"></td>';
						}*/
					}
				str1+='</tr>';
			}
			
		str1+='</table>';
	str1+='</div>';
	$("#exportExcelAttendanceDiv").html(str1);
}

function getFeedBackOrAttendanceDetails()
{
	$("#exportExcelDivId").hide();
	var val = $('input:radio[name=radio]:checked').val();
	
	
	if(val == "feedback"){
		getBatchesByProgramAndCenter();
	}
	if(val == "attendance"){
		var batchId = $("#batchId").val();
		getCadreAttendneceByBatch(batchId);
	}
	
	
}
</script>
<script>

function refreshDetails()
{
	$("#exportExcelDivId").hide();
	$("#attendanceDiv").html('');
	$("#dayWiseAttendDivId").html("");
	$("#accordion").html('');
	$("#programSelErrDivId").html('');
	$("#centerSelErrDivId").html('');
	$("#batchSelErrDivId").html('');
	$("#checkBoxDiv").hide();
	
	
//	$('#batchId').find('option').remove();
//	$('#batchId').append('<option value="0">Select Batch</option>');
	
	$('#batchId').val('0');
	$('#centerId').val('0');
	$('#programId').val('0');
	
	var val = $('input:radio[name=radio]:checked').val();
	/*if(val == "feedback"){
		$("#batchDisplayDiv").hide();
	}
	if(val == "attendance"){
		$("#batchDisplayDiv").show();
	} */
	
	
}

function getDayWiseAttendnenceForBatch(){
	 var batchId  = $("#batchId").val();
	  var center  =	$("#centerId option:selected").text();
	 
	   $("#dayWiseAttendDivId").html('');
	   $("#dayWiseAttendDivId").show();
	 var enrollmentYearIdsList =[];	 
	 enrollmentYearIdsList.push($('#enrollmentId').val());
	 var programIdsList=[];
	  programIdsList.push($('#programId').val());
	 var jsObj={
		 batchId : batchId,
		 enrollmentYearIdsList:enrollmentYearIdsList,
		 programIdsList:programIdsList,
		 //enrollmentYearId:$('#enrollmentId').val()
	 }
	  $.ajax({
		    type:'POST',
		    url :'getDayWiseAttendnenceForBatchAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(result){
			  if(result !=null){
				  buildDayWiseAttendnenceForBatch(result,center);
			  }
		  });
}
function buildDayWiseAttendnenceForBatch(result,center){
	if(result!=null){
				var str='';
				str+='<table class="table table-bordered">';
				str+='<thead class="bg_d">';
				str+='<tr>';
				str+='<th>Center</th>';
				str+='<th>Batch</th>';
				str+='<th>Day 1 Count</th>';
				str+='<th>Day 2 Count</th>';
				
				var flag=false;
				if(result.simpleVOList1 !=null && result.simpleVOList1.length>0 && 
					 result.simpleVOList1.length>2){						
						flag = true;			
				}
				if(flag){
					str+='<th>Day 3 Count</th>';
				}
				
				str+='<th>1 Day Attended Members</th>';
				str+='<th>2 Days Attended Members</th>';
				if(flag){
					str+='<th>3 Days Attended Members</th>';
				}
				
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
					str+='<td>'+center+'</td>';
					str+='<td>'+result.batchName+'</td>';
					for(var j in result.simpleVOList1){
						if(result.simpleVOList1[j].total!=null){
							str+='<td>'+result.simpleVOList1[j].total+' - IA <br/>'+result.simpleVOList1[j].nonInviteeAttendedCount+' - NIA</td>'
						}else{
							str+='<td>0</td>'
						}
					}
					if(result.day1Count !=null){
						str+='<td>'+result.day1Count+' - IA<br/>'+result.oneDayNIACount+' - NIA</td>';
					}else{
						str+='<td>0</td>';
					}
					if(result.day2Count !=null){
						str+='<td>'+result.day2Count+' - IA<br/>'+result.twoDaysNIACount+' - NIA</td>';
					}else{
						str+='<td>0</td>';
					}
					if(flag){
						if(result.day3Count !=null){
						str+='<td>'+result.day3Count+' - IA<br/>'+result.threeDaysNIACount+' - NIA</td>';
						}else{
							str+='<td>0</td>';
						}
					}
					str+='</tr>';
				    
				str+='</tbody>';
				str+='</table>';
				$("#dayWiseAttendDivId").html(str);
			}else{
				$("#dayWiseAttendDivId").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
			}
}
	  $(document).on("click",".updateLeaderShipSkills",function(){
		 
		  var cadreId=$(this).attr("attr-cadreId");
		  var batchId=$(this).attr("attr-batchId");
		   var campId=$("#centerId").val();
		    var programId=$("#programId").val();
			
		var redirectWindow=window.open('updateLeaderShipAction.action?cadreId='+cadreId+'&batchId='+batchId+'&campId='+campId+'&programId='+programId+'&enrollmentYearId='+$('#enrollmentId').val()+'','_blank');
	});
	
	function rebuildProgrammesDetails(enrollmentYearId){
		$('#programId').find('option').remove();
		$('#programId').append('<option value="0"> Select Program </option>');
		if(enrollmentYearId == 4){
			$('#programId').append('<option value="8"> Leadership Skills - 2016 - 18 </option>');
		}else if(enrollmentYearId == 3){
			$('#programId').append('<option value="1"> Leadership Skills - 2014 - 16 </option>');
		} 
	}
	$(document).on("click","#submitId",function(){
		$('#acheivementsId, #communicationSkillsCheckId,#leadershipLevelCheckId,#whatsupUsingId,#leadershipSkillsCheckId,#smartPhoneUsingId,#goalsId,#healthCheckId,#feedBackAnswerChkId,#feedBackDocumentsChkId,#whatsupSharingId,#facebookKnownId').removeAttr('checked');
	});
	</script>
	<script>
var tableToExcel = (function() {
 var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
return function(table, name) {
if (!table.nodeType) table = document.getElementById(table)
var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
window.location.href = uri + base64(format(template, ctx))
}
})()
	</script>
</body>
</html>