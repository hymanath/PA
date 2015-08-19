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
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<!-- <link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/css/jquery.circliful.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css"> -->
<style type="text/css">
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
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
           	  <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"></h4>
                    </div>
                    <div class="panel-body">
                    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"></div>
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
	<!-- <script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script> -->

	
	<script type="text/javascript">
	
   
	$(document).ready(function() {
   // var cb = function(start, end, label) {
	// console.log(start.toISOString(), end.toISOString(), label);
  // }

  // var optionSet1 = {
	// startDate: moment().subtract(29, 'days'),
	// endDate: moment(),
	// minDate: '01/01/2012',
	// maxDate: '12/31/2015',
	//dateLimit: { days: 60 },
	// showDropdowns: true,
	// showWeekNumbers: true,
	// timePicker: true,
	// timePickerIncrement: 1,
	// timePicker12Hour: true,
	// ranges: {
	   // 'Today': [moment(), moment()],
	   // 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   // 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   // 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   // 'Next 30 Days': [moment(),moment().add(29, 'days'), moment()],
	   // 'This Month': [moment().startOf('month'), moment().endOf('month')],
	   // 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	// },
	// opens: 'left',
	// buttonClasses: ['btn btn-default'],
	// applyClass: 'btn-small btn-primary newsSubmitBtn',
	// cancelClass: 'btn-small',
	// format: 'MM/DD/YYYY',
	// separator: ' to ',
	// locale: {
		// applyLabel: 'Submit',
		// cancelLabel: 'Clear',
		// fromLabel: 'From',
		// toLabel: 'To',
		// customRangeLabel: 'Custom',
		// daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		// monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		// firstDay: 1
	// }
  // };

  // var optionSet2 = {
	// startDate: moment().subtract(7, 'days'),
	// endDate: moment(),
	// opens: 'left',
	// ranges: {
	   // 'Today': [moment(), moment()],
	   // 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   // 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   // 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   // 'This Month': [moment().startOf('month'), moment().endOf('month')],
	   // 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	// }
  // };

  // $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  // $('#reportrange').daterangepicker(optionSet1, cb);

  // $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  // $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  // $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	// console.log("apply event fired, start/end dates are " 
	  // + picker.startDate.format('MMMM D, YYYY') 
	  // + " to " 
	  // + picker.endDate.format('MMMM D, YYYY')
	// ); 
  // });
  // $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

  // $('#options1').click(function() {
	// $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
  // });

  // $('#options2').click(function() {
	// $('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
  // });

  // $('#destroy').click(function() {
	// $('#reportrange').data('daterangepicker').remove();
  // });
  
 });

		 
$("#mainheading").html("TRAINING CAMP MAIN DASHBOARD");	
	
		
	
	/*$(document).on('click', '.datetrash', function(){
        
		var divId = $(this).attr("attr_txt");
		var divDId = $(this).attr("attr_date_txt");
        $("#"+divId).remove();
		$("#"+divDId).remove();
        $(this).remove();
		
		var minuteId = $(this).attr("add-plus");
		
		var jsObj =    {minuteId : minuteId}
       
        
		
    });*/
   
	// var mainDivCount=1;
    // function myFunction() {
        // mainDivCount = parseInt(mainDivCount)+1;
        // var c = $("#list").clone(true);
            // c.removeAttr("style");
            // c.attr("id","list"+mainDivCount)
            // c.find(".txtbox").attr("id","minutes"+mainDivCount);
            // c.find(".trash").attr("attr_txt","minutes"+mainDivCount);
			// c.find(".trash").attr("attr_minuteId","0");
			
        // $("#addMoreDiv").append(c);
    // }
	
	/* var mainDateDivCount=1;
	function myDateFunction() {
        mainDateDivCount = parseInt(mainDateDivCount)+1;
        var c = $("#dateList").clone(true);
            c.removeAttr("style");
			//c.attr("class","reportRange"+mainDateDivCount);
            c.attr("id","dateList"+mainDateDivCount)
            c.find(".datetxtbox").attr("id","minutes"+mainDateDivCount);
			c.find(".datetxtboxD").attr("id","Dateminutes"+mainDateDivCount);
            c.find(".datetrash").attr("attr_txt","minutes"+mainDateDivCount);
			c.find(".datetrash").attr("attr_date_txt","Dateminutes"+mainDateDivCount);
			c.find(".datetrash").attr("attr_minuteId","0");
			
        $("#addMoreDateDiv").append(c);
		//datetxtClsCount = parseInt(datetxtClsCount)+1;
		//dateClsCount = parseInt(dateClsCount)+1;
    } */
	
	     //global variables
		 var leaderShipLevelArray = [];
		 var communicationSkillsArray = [];
		 var leaderShipSkillsArray = [];
		 var healthStatusArray = [];
		 var globalDateCount=0;
	     //on load calls.
	     getAllStatusForCadre();
		 getTdpCadreDetailsforASchedule(1);
		 
		 function getAllStatusForCadre(scheduleId)
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
			console.log(leaderShipLevelArray);
		  }
		}
		 function getTdpCadreDetailsforASchedule(scheduleId)
		 { 
			var jsObj={scheduleId:scheduleId }
			$.ajax({
			  type:'POST',
			  url :'getTdpCadreDetailsforAScheduleAction.action',
			  data:{task:JSON.stringify(jsObj)},
		    }).done(function(results){
			    if(results!=null && results.length>0){
				     buildCadreDetails(results);
				}else{
				    alert("NO Data Available.");
				}
			});
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
						   str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+results[i].subList[j].image+'" style="height:40px" class="img-reponsive"></td>'
					   }
					   else{
						   str+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>'
					   }
					   str+='<td>'+results[i].subList[j].name+'</td>'
					   str+='<td>'+results[i].subList[j].mobileno+'</td>'
					   str+='<td>'+results[i].subList[j].constituency+'</td>'
					   if(results[i].subList[j].achievements){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].goals){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }  
					   if(results[i].subList[j].leaderShipLevels){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].communicationSkills){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].leaderShipSkills){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].health){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   str+='<td><button  type="button" id="updateId" class="btn btn-success btn-xs"   attr-cadreId='+results[i].subList[j].id+' attr-batchId='+results[i].id+'>UPDATE</button></td>'
                       str+='</tr>';
                    }
					str+='</table>'
                  str+='</div>'
				  str+='</div>'
                str+='</div>'
              str+='</div>'
		   }
		   $("#accordion").html(str);
		}
		
	
	 $(document).on('click','#updateId',function(){
	   
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
   str+='<div class="row" id="dateList">'
		str+='<div class="col-md-6">'
			str+='<input class="form-control datetxtbox goalsTxtCls m_top10" type="text" id="goalsClass'+globalDateCount+'" attr-id="'+globalDateCount+'">'
		str+='</div>'
		str+='<div class="col-md-4">'
			str+='<div class="input-group date reportrange datetxtboxD m_top10">';
				str+='<input type="text" class="form-control goalsDateClass"  id="goalsDateClass'+globalDateCount+'" attr-id="'+globalDateCount+'"/>';
				str+='<span class="input-group-addon">';
					str+='<span class="glyphicon glyphicon-calendar"></span>';
				str+='</span>';
			str+='</div>';
		str+='</div>'
		str+='<div class="col-md-1">'
			str+='<i class="glyphicon glyphicon-minus add-plus datetrash m_top10"></i>'
		str+='</div>'
    str+='</div>'
	$("#addMoreDateDiv").append(str);
	
   }
   function buildingData(results,tdpCadreId,batchId){
   
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
						str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+results.image+'" class="img-responsive" style="width:30px;"></td>'
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
				str+='<label>Date & TIme</label>'
				str+='</div>'
				
				if(results.goalsList!=null && results.goalsList.length>0){
				  str+='<div id="addMoreDateDiv">'
				  for(var i in results.goalsList){
				   globalDateCount=globalDateCount+1;
				      str+='<div class="row" id="dateList">'
						str+='<div class="col-md-6">'
							str+='<input class="form-control datetxtbox goalsTxtCls m_top10" id="goalsClass'+globalDateCount+'" attr-id="'+globalDateCount+'" type="text" value="'+results.goalsList[i].name+'">'
						str+='</div>'
						str+='<div class="col-md-4">'
							str+='<div class="input-group date reportrange datetxtboxD m_top10">';
								str+='<input type="text" class="form-control goalsDateClass" id="goalsDateClass'+globalDateCount+'" attr-id="'+globalDateCount+'" value="'+results.goalsList[i].date+'"/>';
								str+='<span class="input-group-addon">';
									str+='<span class="glyphicon glyphicon-calendar"></span>';
								str+='</span>';
							str+='</div>';
						str+='</div>'
						str+='<div class="col-md-1">'
							str+='<i class="glyphicon glyphicon-minus add-plus datetrash m_top10"></i>'
						str+='</div>'
				      str+='</div>'
				  }
				  str+='</div>'
				}else{
				  str+='<div id="addMoreDateDiv">'
				  str+='</div>'
				}
				
				str+='<div class="row m_top10">'
					str+='<div class="col-md-6">'
						str+='<input class="form-control goalsTxtCls" id="goalsClass0" attr-id="0" type="text">'
					str+='</div>'
					str+='<div class="col-md-4">'
						str+='<div class="input-group date reportrange">';
							str+='<input type="text" class="form-control goalsDateClass" id="goalsDateClass0" attr-id="0" />';
							str+='<span class="input-group-addon">';
								str+='<span class="glyphicon glyphicon-calendar"></span>';
							str+='</span>';
						str+='</div>';
					str+='</div>'
					
					str+='<div class="col-md-1">'
						str+='<i class="glyphicon glyphicon-plus add-plus" onclick="myDateFunction();"></i>'
					str+='</div>'
				str+='</div>'
				
		str+='</div>'
		str+='</div>'
		
        str+='<div class="row">'
        	str+='<div class="col-md-12">'
            	str+='<label>Leadership Level</label>'
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
            	str+='<label>Communication Skills</label>'
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
            	str+='<label>Leadership Skills</label>'
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
            	str+='<label>Health</label>'
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
	
	var str1='';
    str1+='<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>';
    str1+='<button type="button" class="btn btn-primary" onclick="saveAllDetails('+tdpCadreId+','+batchId+');">Save</button>';
	$("#modalFooterId").html(str1);
	$("#myModal").modal("show");
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
	
	 $('body').on('click','.goalsDateClass', function() {
		
			$(this).datetimepicker();
		});

	
   function saveAllDetails(tdpCadreId,batchId)
   {
       //feedbacks   
	   var leaderShipLevel = $("#leadershipLevelId").val();
	   var communicationSkills = $("#communicationSkillsId").val();
	   var leaderShipSkills = $("#leaderShipSkillsId").val();
	   var health = $("#healthId").val();
	   var comments = $("#commentsId").val();
	   
	   //Achievements.
	   var achieveArray=[];
	   $(".achievmentCls").each(function(){
	      if($(this).val().trim().length>0){
	        achieveArray.push($(this).val());
		  }
	   });
	   var jsObj=
	   {	
			achieveArray:achieveArray,
			leaderShipLevel:leaderShipLevel,
			communicationSkills:communicationSkills,
			leaderShipSkills:leaderShipSkills,
			health:health,
			comments:comments,
			tdpCadreId:tdpCadreId,
			batchId:batchId
		}
		
		$.ajax({
		  type:'POST',
		  url :'saveDetailsOfCadreAction.action',
		  data:{task:JSON.stringify(jsObj)},
		}).done(function(result){
			
		});
   }
	</script>
</body>
</html>