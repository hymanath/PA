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
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					    <!-- <li><a href="callCenterTrainingAgent.action"><span>CALLERS DASHBOARD</span></a> </li>-->
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
</header>
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
						<div class="row m_top10">
							<div class="col-md-3">
								<label>Program</label>
								<div id="programSelDivId"></div>
								<div class="mandatory" id="programSelErrDivId"></div>
							</div>
							<div class="col-md-3">
								<label>Center</label>
								<div id="campSelDivId"></div>
								<select class="form-control" id="centerId">
									<option value="0">Select Center</option>
								</select>
								<div class="mandatory" id="centerSelErrDivId"></div>
							</div>
							<div class="col-md-2 m_top30">
								<button  type="button" id="submitId" class="btn btn-success btn-xs text-bold" onclick="getBatchesByProgramAndCenter();">Submit</button>
							</div>
						</div>
						<center><img id="ajaxImage" src="./images/ajaxImg2.gif" alt="Processing Image" style="height:45px;display:none;margin-top:20px"/></center>
                    	<div class="panel-group m_top20" id="accordion" role="tablist" aria-multiselectable="true"></div>
					</div>	
               </div>          
			</div>			  
        </div>                 
	</div>					  
</main>
<!--  accordian start-->

                  <!-- pop up modal start-->
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
	<!-- <script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script> -->

	
	<script type="text/javascript">
	
   
	$(document).ready(function(){
    });

	$("#mainheading").html("TRAINING CAMP MAIN DASHBOARD");
	
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
		
		function buildCadreDetails(results){
		   var str='';
		   for(var i in results){
		     str+='<div class="panel panel-default">'
				str+='<div class="panel-heading" role="tab" id="heading'+i+'">'
                   str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
                   str+='<h4 class="panel-title">'+results[i].name+'<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>'
                   str+='</a>';               
                str+='</div>';                
                str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">'
                  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
                      str+='<table class="table table-bordered m_0" style="font-size:12px">'
                      str+='<thead>';
					  str+='<th></th>';
                      str+='<th>Name</th><th>Mobile</th><th>Constituency</th><th>Achievements</th><th>Goals</th>'
					  str+='<th>Leadership <br/>Level</th><th>Communication<br/> Skills</th> <th>Leadership Skills</th> <th>Health</th> <th></th>';
                      str+='</thead>';                 
                      for(var j in results[i].subList){
					   str+='<tr>';
					   if(results[i].subList[j].image != null){
						   str+='<td><img src="images/cadre_images/'+results[i].subList[j].image+'" style="height:40px" class="img-reponsive"></td>'
					   }
					   else{
						   str+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>'
					   }
					   str+='<td>'+results[i].subList[j].name+'</td>'
					   str+='<td>'+results[i].subList[j].mobileno+'</td>'
					   str+='<td>'+results[i].subList[j].constituency+'</td>'
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
					   if(results[i].isFeedbackUpdatable=='Y')
					     str+='<td><button  type="button" id="updateId'+results[i].subList[j].id+''+results[i].id+'" class="btn btn-success btn-xs updateClass"   attr-cadreId='+results[i].subList[j].id+' attr-batchId='+results[i].id+'>UPDATE</button></td>'
                       else
						   str+='<td><button  disabled type="button" id="updateId'+results[i].subList[j].id+''+results[i].id+'" class="btn btn-success btn-xs updateClass"   attr-cadreId='+results[i].subList[j].id+' attr-batchId='+results[i].id+'>UPDATE</button></td>'
					   str+='</tr>';
                    }
					str+='</table>'
                  str+='</div>'
				  str+='</div>'
                str+='</div>'
              str+='</div>'
		   }
		   $("#ajaxImage").hide();
		   $("#accordion").html(str);
		}
		
	
	 $(document).on('click','.updateClass',function(){
	   $.blockUI({image: src="./images/Optimizing Please Wait.gif"});
       var tdpCadreId=$(this).attr('attr-cadreId');
	   var batchId=$(this).attr('attr-batchId');
	   var jsObj={tdpCadreId:tdpCadreId,batchId:batchId}
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
		
        str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Health</label><span class="mandatory">*</span>'
				str+='<div class="mandatory" id="healthErrDivId"></div>';
				str+='<select class="form-control" id="healthId">'
                str+='<option value="0">Select</option>'
                for(var i in healthStatusArray){
				  if(results.healthStatusId==healthStatusArray[i].id)
				    str+='<option value="'+healthStatusArray[i].id+'" selected>'+healthStatusArray[i].name+'</option>'
				  else
                  str+='<option value="'+healthStatusArray[i].id+'">'+healthStatusArray[i].name+'</option>'
				}	   
               str+='</select>'
			 str+='</div>'
        str+='</div>'
		
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
			 var goalObject = new Object();
			 goalObject.goal = goal; 
			 goalObject.date = date;
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
	   
	   var jsObj=
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
            facebookId:whatsappShareId
		}
		
		$.ajax({
		  type:'POST',
		  url :'saveDetailsOfCadreAction.action',
		  data:{task:JSON.stringify(jsObj)},
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
	  
	  var programId = $("#programId").val();
	  var centerId = $("#centerId").val();
	  
	  if(programId == 0){
		$("#programSelErrDivId").html("Please Select Any Program");
		return;
	  }
	  if(centerId == 0){
		$("#centerSelErrDivId").html("Please Select Any Center");
		return;
	  }
	  
	  $("#ajaxImage").show();
	  $("#accordion").html('');
	  	  
	  var jsObj=
	  {
		 programId:programId,
		 centerId:centerId
	  }
	  
	 $.ajax({
		type:'POST',
		url :'getAllBatchesByProgramAndCenterAction.action',
		data:{task:JSON.stringify(jsObj)},
	 }).done(function(results){
		 if(results!=null && results.length>0){
		     buildCadreDetails(results);
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
	</script>
</body>
</html>