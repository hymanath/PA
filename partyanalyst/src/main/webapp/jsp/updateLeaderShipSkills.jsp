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
<title>Training Camp Main Dashboard</title>
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
footer
{
	padding:30px;
	background-color:#5c2d25;
	color:#fff;
}

</style>
</head>
<body>
<header class="eventsheader">
	<!--<img src="dist/img/header.jpg" width="100%" alt="">-->
	<div class="container">
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
</header>
<div class="container">
<div id="modalBodyId" style="margin-top:50px;"></div>
</div>
<footer>
		<p class="text-center">All &copy; Telugu Desam Party </p>
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
	
	
	
<script type="text/javascript">
var leaderShipLevelArray = [];
		 var communicationSkillsArray = [];
		 var leaderShipSkillsArray = [];
		 var healthStatusArray = [];
		 var globalDateCount=0;
		 
		 $("#goalsDateId0").datetimepicker({format: "MM/DD/YYYY"});
		 
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
		
		
		
	$(document).on('click', '.healthCardminus', function(){
	   
	   $(this).parent().parent().remove();
		   
    });
	
var cadreId= '${param.cadreId}';
var batchId = '${param.batchId}';
function myDateFunction(){
   globalDateCount=globalDateCount+1;
   var str='';
   str+='<div class="row dateListClass" id="dateList'+globalDateCount+'" attr-id="'+globalDateCount+'">'
		str+='<div class="col-md-7">'
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

	$("#mainheading").html("TRAINING CAMP MAIN DASHBOARD");
	
getLeaderShipDetails();
function getLeaderShipDetails()
{
	
	var str ='';
	
	var jObj = {
		tdpCadreId : cadreId,
		batchId : batchId,
		
	}
		$.ajax({
	          type:'POST',
	          url: 'getDetailsForACadreAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)}
	         }).done(function(result){
				  if(result!=null){
		    buildingData(result,cadreId,batchId);
		  }
			});
	}

	function buildingData(results,tdpCadreId,batchId){
		 var datePkrIds = [];
   var str=''
   str+='<div id="popupdivId">';
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading">';	
				str+='<h4 class="panel-title">TITLE</h4>';
			str+='</div>';
		str+='<div class="panel-body">';
		str+='<div class="row">'
			str+='<div class="col-md-12">'
				str+='<div class="table-responsive">'
				str+='<table class="table table-bordered m_0">'
					
					str+='<thead class="bg_d">'
						str+='<th></th>'
						str+='<th>Name</th>'
						str+='<th>Designation</th>'
						str+='<th>Mobile No</th>'
						str+='<th>Constituency</th>'
						str+='<th>District</th>'
						str+='<th>Trained On</th>'
					str+='</thead>'
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
				str+='<div class="col-md-7">'
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
						str+='<div class="col-md-7">'
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
					str+='<div class="col-md-7">'
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
        	str+='<div class="col-md-6 m_top10">'
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
        
        	str+='<div class="col-md-6 m_top10">'
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
        
		
			str+='<div class="row">';
        	str+='<div class="col-md-4 m_top10">'
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
        
        	str+='<div class="col-md-4 m_top10">';
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
			str+='<div class="col-md-12 m_top10">';
			str+='<div class="row">';
		    str+='<div class="col-md-12">';
		    str+='<label>Health Card </label>';
		  
		  
		 
			if(results.healthCardAttachments!=null && results.healthCardAttachments.length>0){
				str +='<div class="row">';
				for(var i in results.healthCardAttachments)
				{
					str +='<div class="col-md-3 m_top10">';
						str +='<a target="_blank" href="tdp_cadre_health_cards'+results.healthCardAttachments[i].name+'">'+results.healthCardAttachments[i].name+'</a>';
					str +='</div>';
				}
				str +='</div>';
			}
	
		  str+='<div class="row">';
		  str+='<div class="col-md-2 m_top10 ">';
		  str+='<input type="file" class="healthCardattachment" />';
		  str+='</div>';
		  str+='<div class="col-md-1">';
		  str+='<i class="glyphicon glyphicon-plus m_top10 add-plus healthCardadd" onclick="addHealthCard();"></i>';
		  str+='</div>';
		  str+='</div>';
			
		  str+='</div>';
		  str+='</div>';
		  str+='</div>';
		  str+='</div>';
		  
		   str+='<div class="row" id="healthCardCntaddDiv">';
		  str+='</div>';
		<!-- Adding -->
		
		 str+='<div class="row">'
        	str+='<div class="col-md-6 m_top10">'
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
        
		
		if(results.smartphone=='Y'){
			str+='<div id="whatsappDivId">';
		}else
		 str+='<div id="whatsappDivId" style="display:none">';
		
		
        	str+='<div class="col-md-6 m_top10">'
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
        str+='</div>'
		
		
		 str+='<div class="row">'
        	str+='<div class="col-md-6 m_top10">'
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
        
	  
        	str+='<div class="col-md-6 m_top10">'
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
			str+='<div class="col-md-12 m_top10">'
				str+='<label>Comments</label>'
				if(results.remarks!=null)
				  str+='<textarea class="form-control" id="commentsId">'+results.remarks+'</textarea>'
				else
				 str+='<textarea class="form-control" id="commentsId"></textarea>'
			str+='</div>'
		str+='</div>'
      
	  
	
	
	for(var i in datePkrIds){
		var datePickerId = datePkrIds[i];
		var dpIdVal = $(datePickerId).val();
		$(datePickerId).datetimepicker({
            format: "MM/DD/YYYY"
        });
		if(dpIdVal!=""){
			$(datePickerId).val(dpIdVal)
		}
	}
	
	
	
	str+='<span id="updatedId" style="display:none" class="text-success pull-left">Updated Successfully...</span>';
	str+='<span id="notUpdatedId" style="display:none" class="text-success pull-left">Sorry..Details Are Not Updated...</span>';
	str+='<span id="processingId" style="display:none" class="text-danger pull-left"><span><img id="ajaxImage1" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:15px;display:none;"/></span>Please Wait While Updating...</span>';
    str+='<div class="row">';
	str+='<div class="col-md-12 m_top20">';
	str+='<button style="margin-right:10px;" type="button" class="btn btn-primary pull-right" onclick="saveAllDetails('+tdpCadreId+','+batchId+');">Save</button>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	$("#modalBodyId").html(str);
	$("#goalsDateId0").datetimepicker({format: "MM/DD/YYYY"});
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
  healthCardCnt = 0;
	  function addHealthCard()
	  {
		
		  var str ='';
		 
		  str+='<div class="col-md-2 m_top10 ">';
		  str+='<div id="healthCardCnt"+healthCardCnt+"">';
		  str+='<input type="file" class="healthCardattachment" />';
		  str+='</div>';
		  str+='</div>';
		  str+='<div class="col-md-1">';
		  str+='<i class="glyphicon glyphicon-minus add-plus healthCardminus m_top10"></i>';
		  str+='</div>';
		  
		  $("#healthCardCntaddDiv").append(str);
		   healthCardCnt ++;
	  }

</script>
</body>
</html>	