<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Activity</title>
	<link href="dist/activity/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<link href='dist/activity/css/activity.custom.css' rel='stylesheet' type='text/css'>
	<link href='dist/activity/Date/daterangepicker-bs3.css' rel='stylesheet' type='text/css'>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>	
	<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 
	<!--<script type="text/javascript" src="js/bootstrap.js" ></script> -->
	 <script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
	 <script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
	 
	
	 <!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 
	
 <style type="text/css">
	.m_top10{margin-top:10px}
	.input-g1 .form-control{border-radius:0px;border-left:0px}
	.input-g1 .input-group-addon{border-radius:0px;background:#fff;}
	.starMark{font:15px;color:red;}

 </style>
</head>
<body>
	<form method="POST" enctype="multipart/form-data" name="uploadInsureeDetailsForm" action="saveActivityDetailsAction.action">
<div class="container">


	<div class="row">
   		<div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH TO UPDATE PROGRAM ACTIVITIES 
						<!--<span class="pull-right" >
							<div class="input-group col-md-12" style="margin-top:-8px">
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								<input type="text" class="searchDateCls form-control" />
							</div>
						</span>-->
					</h4>
					
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-3">
                        	<img src="img/searchicon.png" style="border-right:1px solid #00B17D">
                        </div>
                        <div class="col-md-9">
							<div class="row">
								<div class="col-md-9" id="ErrDiv" style="color:#E6211E;">
								</div>
							</div>
                        	<div class="row">
							<div class="col-md-4"><span class="starMark">*</span>
                                	<label>Activity Type</label>
                                    <s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="surveyTypeId" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
                            </div>
							<div class="col-md-4"><span class="starMark">*</span>
								<label>Activity Level</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="surveyTypeId" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
							</div>
							<div class="col-md-4"><span class="starMark">*</span>
								<label> Activity Name </label>
								<select id="ActivityList" class="form-control" name="activityVO.activityLevelId">
									<option value="0"> Select Activity </option>
								</select>
							</div>
								<!--
                                <div class="col-md-12 m_top10">
                                	<label class="radio-inline">
                                    	<input type="radio">Constituency
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Mandal/ Town / Division
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Panchayat/ Ward
                                    </label>
                                </div>
								-->
								<div class="row">
							<div class="col-md-4 m_top10" id="districtDivId" style="display:none;"><span class="starMark">*</span>
								<label>District</label>
								<select id="districtList" class="form-control" name="activityVO.districtId" >
									
								</select>
							</div>	
							<div class="col-md-4 m_top10" id="constituencyDivId"  style="display:none;"><span class="starMark">*</span>
								<label>Constituency</label>
								<select id="constiList" class="form-control" onchange="getMunciMandalsList(this.value)" name="activityVO.constituencyId" >
									
								</select>
							</div>
							<div class="col-md-4 m_top10" id="mandalDivId" style="display:none;">
								<label >Mandal/ Town/ Division</label>
								<select id="mandalsList" class="form-control" onchange="getPanchayatWardByMandal(this.value);">
									<option value="0"> Mandal/ Town/ Division</option>
								</select>
								<span > <img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg"></span>
							</div>
							<div class="col-md-4 m_top10" id="panchayatDivId" style="display:none;">
								<label>Panchayat/ Ward</label>
								<select id="villageWardsList" class="form-control">
									<option value="0"> Select Panchayat/ Ward</option>
								</select>
							</div>
							</div>
							<div class="row">
						   <div class="col-md-3 m_top10" style="float:right;">
								<button id="searchId" class="btn btn-block btn-custom btn-success" type="button" onclick="getLocationDetailsForActivity('','');">SEARCH</button>
							</div>
							</div>
								
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12" >
		<div class="panel panel-default panel-custom" id="assemblydivId" style="display:none">
		  <div class="panel-heading">
			<!--<h4 class="panel-title">ASSEMBLY CONSTITUENCY WISE ACTIVITIES -  <small style="text-transform: uppercase;"><b>${sessionScope.UserName}</b></small></h4> -->
			<h4 class="panel-title" id="constituencyHeadingId" style="display:none"> </h4>
			<h4 class="panel-title" id="districtHeadingId" style="display:none"> DISTRICT WISE ACTIVITIES </h4>
		  </div>
		   <div class="panel-body">
			<div id="buildAssConsActivity"></div>
		   </div>
		
		</div>
		<div class="panel panel-default panel-custom" id="resultsDiv" style="display:none;">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="font-40" id="constncyId">SEARCH RESULTS  </span><span class="font-12" id="headingId"> - Activity Name(Activity level)</span>
                    <span class="pull-right">
                    	<label class="checkbox-inline">
							<span>
								<input type="radio" checked="checked" id="allId" onclick="getLocationDetailsForActivity('','');" name="radio1">All
							</span>
							<span>
								<input type="radio" id="conductedId" onclick="getLocationDetailsForActivity('','');" name="radio1">Show Conducted Locations
							</span>
							<span  style="margin-left:30px;">
								<input type="radio" id="notConductedId" onclick="getLocationDetailsForActivity('','');" name="radio1">Show Not Conducted Locations
							</span>
							<!--<span  style="margin-left:30px;">
								<input type="button" class="btn btn-success btn-xs" value="Get Details" onclick="getLocationDetailsForActivity('','');">
							</span>-->
							
                        </label>
                    </span>
                    </h4>
                </div>
                <div class="panel-body">
                	<div>
                      <!-- Nav tabs -->
                      <!--<ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Planed</a></li>
                       <!-- <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Conducted</a></li>
                        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Lately Conducted</a></li>
                        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Not Conducted</a></li>
                        <button class="btn btn-sm btn-custom btn-success pull-right">SEARCH</button>
                        <li class="pull-right">
                        	<div class="input-group input-g">
                            	<input type="text" class="form-control">
                                <span class="input-group-addon">
                                	<i class="glyphicon glyphicon-search"></i>
                                </span>
                                
                            </div>
                        </li>
                      </ul>-->
                    
                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home" style="margin-top:10px;"></div>
						          
                        <div role="tabpanel" class="tab-pane" id="profile">...</div>
                        <div role="tabpanel" class="tab-pane" id="messages">...</div>
                        <div role="tabpanel" class="tab-pane" id="settings">...</div>
                      </div>
                    
                    </div>
                </div>
            </div>
			<!---Start  Assembly wise Activity--->
		
		<!--- Assembly wise Activity End--->
		</div>
    </div>
	</form>
	
</div>

<div id="dialogSummaryDistsrict" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h3 class="panel-header text-center"></h3>
					  </div>
						<div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
						<center><img class="text-center" id="dataLoadingImg" src="images/Loading-data.gif" style="display:none;"/></center>
				</div>
			</div>
    </div>

	<!-- questions modal start-->
	
	<div class="modal fade" id="questionsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
		<div class="modal-content" style="border-radius:0px;">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Questionnaire</h4>
		  </div>
		  <div class="modal-body">
			<div id="questionsDivBodyId"></div>
		  </div>
		  <div class="modal-footer" id="questionsDivFooterId">
			<!--<button type="button" id="saveResult" class="btn btn-custom btn-success">Save</button>-->
		  </div>
		</div>
	  </div>
	</div>
	
	<!-- questions modal end -->
							
							
<script src="dist/activity/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activity/js/custom.js" type="text/javascript"></script>
<script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>

<script>
$(document).ready(function(){
	//$('.searchDateCls').daterangepicker();
	$('.applyBtn').click(function(){
		
		/*var startDate = $("input[name=daterangepicker_start]").val();
		var endDate =  $("input[name=daterangepicker_end]").val();*/
	var startDate = "";
	var endDate = "";
	var dates=$('.searchDateCls ').val();
		if(dates != null && dates.trim().length > 0){
			var dateArray=dates.split("to");
		  startDate=dateArray[0];
		  endDate=dateArray[1];
		}
		getLocationDetailsForActivity(startDate,endDate);
		//alert(startDate);
	});
});
var fromTypeGlob;
$(function () {
	var cb = function(start, end, label) {
	//console.log(start.toISOString(), end.toISOString(), label);
	//$('.searchDateCls').html(start.format('D MMMM, YYYY')- + ' - ' + end.format('D MMMM, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }
  var optionSet1 = {
	//startDate: moment().startOf('month'),
	//endDate: moment().endOf('month'),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   //'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   //'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [ moment().subtract(30, 'days'),moment()],
	   'Lat 60 Days': [moment().subtract(60, 'days'),moment()],
	   'Last 180 Days': [moment().subtract(6, 'months'),moment()],
	   'Last 365 Days': [moment().subtract(1, 'year'),moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   //'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',
	format: 'DD/MM/YYYY',
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
  //$('.searchDateCls').val(moment().format('DD/MM/YYYY') + ' - ' + moment().format('DD/MM/YYYY'));

  $('.searchDateCls').daterangepicker(optionSet1, cb);

  $('.searchDateCls').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('.searchDateCls').on('hide.daterangepicker', function() { console.log("hide event fired"); });
});
/*
function getActivityNames()
{
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseAsOfNowDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
		});
		
}
*/
function submitForm(){
	var uploadHandler = {
		upload: function(result) {
			console.log(result);
			uploadResult = result.responseText;
			var myResult = (String)(uploadResult);
			
			if(myResult.search('success') != -1){
				alert("Successfully UPdated");
				/*var startDate = $("input[name=daterangepicker_start]").val();
				var endDate =  $("input[name=daterangepicker_end]").val();*/
			var startDate = "";
			var endDate = "";
			
			var dates=$('.searchDateCls ').val();
				if(dates != null && dates.trim().length > 0){
					var dateArray=dates.split("to");
				  startDate=dateArray[0];
				  endDate=dateArray[1];
				}
				getLocationDetailsForActivity(startDate,endDate);
			}else{
			}
		},
		error: function(){
			console.log('upload error');
		}
		};
	YAHOO.util.Connect.setForm('uploadInsureeDetailsForm',true);
	YAHOO.util.Connect.asyncRequest('POST','saveActivityDetailsAction.action',uploadHandler);
}

	
function getActivityNames()
{
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	
	$("#constituencyDivId").hide();
	$("#mandalDivId").hide();
	$("#panchayatDivId").hide();
	$("#districtDivId").hide();
	$("#constiList").val(0);
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	
	var activityLevelId = $('#activityLevelList').val();
	
	if(activityLevelId == 1){
		$("#constituencyDivId").show();
		$("#mandalDivId").show();
		$("#panchayatDivId").show();
	}
	else if(activityLevelId == 2){
		$("#constituencyDivId").show();
		$("#mandalDivId").show();
	}
	else if(activityLevelId == 3){
		$("#districtDivId").show();
	}
	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
		});
		
}


function getUserAccessConstituencyList()
{
	$('#constiList').find('option').remove();
	$('#constiList').append('<option value="0"> Select Constituency</option>');	
	var jObj = {
			task:"getUserAccessConstituencyList",
			stateId:1
		};
		
		$.ajax({
          type:'GET',
          url: 'getUserAccessListConstituency.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.hamletVoterInfo != null && result.hamletVoterInfo.length >0)
			{
				for(var i in result.hamletVoterInfo)
					$('#constiList').append('<option value="'+result.hamletVoterInfo[i].id+'">'+result.hamletVoterInfo[i].name+'</option>');
			}
		});
		
}

function getUserAccessDistrictList()
{
	$('#districtList').find('option').remove();
	$('#districtList').append('<option value="0"> Select District</option>');	
	var jObj = {
		};
		
		$.ajax({
          type:'GET',
          url: 'getUserAccessDistrictListAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#districtList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		});
		
}

function getMunciMandalsList(constituencyId)
{
	$('#mandalsList').find('option').remove();
	$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	$('#villageWardsList').find('option').remove();
	$('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	var jObj = {
			task:"getUserAccessConstituencyList",
			locationId:constituencyId
		};
		$("#procesingImg").show();
		$.ajax({
          type:'GET',
          url: 'getMandalCorporationsByConstituencyAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.length >0)
			{
				$("#procesingImg").hide();
				for(var i in result)
					$('#mandalsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			}
		});
		
}

function getPanchayatWardByMandal(mandalId){
		     $('#villageWardsList').find('option').remove();
			 $('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');	
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
				if(result != null && result.length >0)
				{
					for(var i in result)
						$('#villageWardsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
				}
		});	
			
	}
	
function buildingResults(result,locationName){
	 
	var str = '';
	
			str+='<table class="table table-bordered" id="constiTableId">';
		str+='<thead>';
		str+='<th style="width:50px;"> </th>';
		str+='<th style="padding-left: 72px;"> MEMBER </th>';
		str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
		str+='<th style="padding-left: 19px;"> AGE </th>';
		str+='<th style="padding-left: 19px;"> GENDER </th>';
		str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
		str+='<th style="padding-left: 19px;"> Update Mobile No </th>';
		str+='</thead>';
		for(var i in result){
		 str+='<tr>';
		str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="http://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
		
		 str+=' </td>';
		 str+='<td> '+result[i].name+' ';
		 //if(basicCmmtyId != 1){
		 if(result[i].commiteeName!=null){
			 str+='<br>'+result[i].commiteeName+' - ';
		 }else{
			 str+='<br>';
		 }
		/* }
		else{
			 str+='<br>';
		 }*/
		 
		  //if(basicCmmtyId == 1){
		 if(result[i].role!=null){
			 str+=' '+result[i].role+'';
		 }else{
			 str+='';
		 }
		str+=' <br/> <span> Constituency : '+result[i].constituencyName+' </span>';
		str+=' <br/> <span> MemberShipNo : '+result[i].id+' </span>';
		//str+=' <br/> <span> MemberShipNo : <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'"> '+result[i].id+' </a> </span>';
		  str+=' </td>';	  
		str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
		 // str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].voterCardNo+' </td>';
		 str+='<td><input type="button" class="btn btn-custom btn-success" value="Update Mobile No" id="editBtnId'+i+'" onclick="showHide('+i+');" name="Edit">';
		 str+='</br>'
		 str+='<div id="errMobileId'+i+'" style="color:red"></div>';
		 str+='<input type="text" class="m_top10" disabled maxlength="10" id="updatemobileNo'+i+'" value="'+result[i].mobileNo+'" />';
		 str+='</br>'
		 str+='<input type="button" class="btn btn-custom btn-success m_top10" name="save" onclick="updateMobileNumber(\''+i+'\',\''+result[i].id+'\')" id="saveBtnId'+i+'" style="display:none" value="Save Mobile No" />';
		 str+='<div id="successMobileId'+i+'" style="color:green"></div>';
		 str+='</td>';
		 str+='</tr>';
		 
		}
	   str+='</tbody>';
	   str+='</table>';
	
	$("#dataLoadingImg").hide();
	$("#cadreDetailsDiv").html(str);
	
}

function showHide(index){
	
	$("#saveBtnId"+index).show();
	$('#updatemobileNo'+index).removeAttr('disabled');
	$("#editBtnId"+index).hide();
}

function updateMobileNumber(index,tdpCadreId){
	
	$("#successMobileId"+index).html("");
	$("#errMobileId"+index).html("");
	
	var mobileNo = $("#updatemobileNo"+index).val();
	if(isNaN(mobileNo) || mobileNo.trim().length != 10){
		$("#errMobileId"+index).html("Enter Valid Number");
		return;
	}
	
	var jObj={
		tdpCadreId:tdpCadreId,
		mobileNo:mobileNo
	};
	$.ajax({
	  type:'POST',
	  url: 'updateMobileNumberForCadreAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
			if(result != null){
				if(result.resultCode == 0){
					if(result.message == "SUCCESS"){
						$("#successMobileId"+index).html("Mobile Number Is Successfully Updated...");
					}else{
						$("#errMobileId"+index).html("Invalid Mobile Number...");
					}
				}else{
					$("#errMobileId"+index).html("Sorry,Mobile Number Is Not Updated...");
				}
			}
	  });
}

function getLocationDetailsForActivity(startDate,endDate)
{
	
	var activityTypeId =$('#activityTypeList').val();
	var activityLevelId =$('#activityLevelList').val();
	var ActivityId =$('#ActivityList').val();
	var constituencyId =$('#constiList').val();
	
	$('#ErrDiv').html("");
	var errStr ='';
	if(activityTypeId == null || activityTypeId == 0)
	{
		errStr+="Please Select Activity Type.";
	}
	else if(activityLevelId == null || activityLevelId == 0)
	{
		errStr+="Please Select Activity Level.";
	}
	else if(ActivityId == null || ActivityId == 0)
	{
		errStr+="Please Select Activity .";
	}
	else if(constituencyId == null || constituencyId == 0)
	{
		
		if(activityLevelId == 1 || activityLevelId == 2){
		errStr+="Please Select Constituency.";
		}
	}
	
	if(errStr!= null && errStr.length>0){
		$('#ErrDiv').html(errStr);
		return;
	}
	else
	{
		$('#resultsDiv').show();	
		$('#home').html("<img src='images/Loading-data.gif'/>");	
			if(startDate.trim().length == 0)
			{
				/*startDate = $("input[name=daterangepicker_start]").val();
				endDate =  $("input[name=daterangepicker_end]").val();*/
				var dates=$('.searchDateCls ').val();
				if(dates != null && dates.trim().length > 0){
					var dateArray=dates.split("to");
				  startDate=dateArray[0];
				  endDate=dateArray[1];
				}
			}	
			
			var searchBy="Panchayat";
			var locationId = $('#villageWardsList').val();	
			if(locationId == 0)
			{
				locationId = $('#mandalsList').val();
				searchBy = "mandal";
				if(locationId == 0)
				{
					locationId = $('#constiList').val();
					searchBy = "Constituency";
				}
			}
			
			var value = "all";
			if($("#all").is(':checked'))
			{
				value = "all";
			}
			else{
				if($("#notConductedId").is(':checked'))
				value = "notConducted";
			if($("#conductedId").is(':checked'))
				value = "conducted";
			}
			
			
			var jObj = {
				startDate:startDate,
				endDate:endDate,
				checkedValue:value,
				activityScopeId:$('#ActivityList').val(),
				activityLevelId:activityLevelId,
				searchBy:searchBy,
				locationId:locationId,
				locationId:locationId,
				task:"getLocationDetailsForActivity"
			};		
			$.ajax({
				  type:'GET',
				  url: 'getLocationDetailsForActivity.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){			
					//console.log(result);
					var str='';
					if( result!= null)
					{
						str+='<table class="table table-bordered bg_ff" id="locationsTab">';
						str+='<thead>';
						str+='<tr>';
						//str+='<th>CONSTITUENCY</th>';
						if(activityLevelId == 2)
							str+='<th style="background-color:#00B17D; color:#fff;">MANDAL/ TOWN/ DIVISION</th>';
						else if(activityLevelId == 1)					
							str+='<th style="background-color:#00B17D; color:#fff;">PANCHAYAT/ WARD</th>';
						
						str+='<th style="background-color:#00B17D; color:#fff;">PLANNED DATE</th>';
						str+='<th style="background-color:#00B17D; color:#fff;">CONDUCTED DATE</th>';
						//str+='<th>PRESIDENT</th>';
						//str+='<th>GENERAL SECRETARY</th>';
						str+='<th style="background-color:#00B17D; color:#fff;">COMMITTEE MEMBERS</th>';
						str+='</tr>';
						str+='</thead>';
						
						if(result.result != null && result.result.length>0){
							for(var i in result.result)
							{
								str+='<tr>';
								//str+='<td></td>';
								str+='<input type="hidden" value="'+activityLevelId+'" name="activityVO.activityVoList['+i+'].locationLevel">';
								str+='<input type="hidden" value="'+result.result[i].locationId+'" name="activityVO.activityVoList['+i+'].locationValue">';
								str+='<td> '+result.result[i].locationName+'</td>';
								str+='<td  style="text-align:center;">';
								str+='<div class="input-g1 input-group">';
									str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
									if(result.result[i].planedDate != null)
										str+=''+result.result[i].planedDate+'';
									else
										str+='<input type="text" class="dateCls form-control"  name="activityVO.activityVoList['+i+'].plannedDate" value=""/>';
								str+='</div></td>';
								str+='<td  style="text-align:center;">';
								str+='<div class="input-g1 input-group">';
									str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
									if(result.result[i].conductedDate != null)
										str+=''+result.result[i].conductedDate+'';
									else
										str+='<input type="text" class="dateCls form-control" name="activityVO.activityVoList['+i+'].conductedDate" value=""/>';
								str+='</div></td>';
								/*
								if(result.result[i].hamletsOfTownship != null && result.result[i].hamletsOfTownship.length>0)
								{
									if(result.result[i].hamletsOfTownship.length >= 1)
										str+='<td  style="text-align:center;"> '+result.result[i].hamletsOfTownship[0].name+'<br>'+result.result[i].hamletsOfTownship[0].partno+' </td>';
									if(result.result[i].hamletsOfTownship.length >= 2)
										str+='<td  style="text-align:center;"> '+result.result[i].hamletsOfTownship[1].name+'<br>'+result.result[i].hamletsOfTownship[1].partno+' </td>';
								}else{
									str+='<td  style="text-align:center;"> - </td>';
									str+='<td  style="text-align:center;"> - </td>';
								}
								*/
								str+='<td style="text-align:center;">';
								str+='<input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result.result[i].locationId+',\''+result.result[i].locationName+'\');"/>&nbsp;&nbsp;';
								str+='<input type="button" value="Update Questionnaire" attr_location_Value="'+result.result[i].locationId+'" class="btn btn-success btn-xs" id="updateQBtnId"/>';
								
								str+='<input type="button" value="Upload Images" attr_location_Value="'+result.result[i].locationId+'" attr_location_Name=\''+result.result[i].locationName+'\' class="btn btn-success btn-xs" id="uploadImagesId" style="margin-left: 5px;"/>';
								
								str+='</td>';
								str+='</tr>';
							}
							str+='</table>';
						}
					}
					$('#home').html(str);
					$('#home').append(' <div><input type="button" value="UPDATE DETAILS" class="btn btn-custom btn-success" onclick="submitForm();"/></div>');
					$('.dateCls').daterangepicker({singleDatePicker:true,format: 'DD/MM/YYYY'});
					
					$("#locationsTab").dataTable({
					"iDisplayLength": 20,
					"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});
					$("#locationsTab").removeClass("dataTable");
					$("#constncyId").html(''+$("#constiList option:selected").text()+' constituency ');
					$('#headingId').html(' '+$("#activityLevelList option:selected").text()+' - '+$("#ActivityList option:selected").text()+'');
				});	
	}
		
}


function gettingCadreDetails(locationId,locationName){	
	
	$("#cadreDetailsDiv").html('');
	$('#dialogSummaryDistsrict').find('h3').html('<span>'+locationName+'  '+$("#activityLevelList option:selected").text()+' Main Committee Members </span>');
	$("#dialogSummaryDistsrict").modal("show");
	$("#dataLoadingImg").show();
	
	locationId = ""+locationId+"";
	var firstChar = locationId.substr(0,1);
	//console.log(firstChar);
	 locationId = locationId.slice(1);
	 
	 
	var locationTypeId = $('#activityLevelList').val();
	var activityLevellId = $('#activityLevelList').val();
	
	var locationType = 5;
	if(activityLevellId == 2)
	{
		if(firstChar == 2)
			locationType = 5;
		else if(firstChar == 3)
			locationType = 7;
		else if(firstChar == 1)
			locationType = 9;	
	}
	else if(activityLevellId == 1)
	{
		if(firstChar == 1)
			locationType = 6;
		else if(firstChar == 2)
			locationType = 8;
	}
	
		 var jsObj={
		         locationId:locationId,locationType:locationType,basicCommitteeTypeId:1
		       };
			   
		 $.ajax({
			type : "GET",
			url : "getComitteeMembersInfoInActivityAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			if(result != null && result.length > 0){
				buildingResults(result,locationName);
			}else{
				$("#dataLoadingImg").hide();
				$("#cadreDetailsDiv").html('NO DATA AVAILABLE...');
			}
		});
	}
	
getUserAccessConstituencyList();
getUserAccessDistrictList();
 
 $("#searchId").click(function(){
 
	var activityTypeId =$('#activityTypeList').val();
	var activityLevelId =$('#activityLevelList').val();
	var ActivityId =$('#ActivityList').val();
	var districtId =$('#districtList').val();
	
	
	//$('#ErrDiv').html("");
	var errStr ='';
	if(activityTypeId == null || activityTypeId == 0)
	{
		errStr+="Please Select Activity Type.";
	}
	else if(activityLevelId == null || activityLevelId == 0)
	{
		errStr+="Please Select Activity Level.";
	}
	else if(ActivityId == null || ActivityId == 0)
	{
		errStr+="Please Select Activity .";
	}
	else if(activityLevelId >= 3 && (districtId == null || districtId == 0))
	{
	 if(activityLevelId == 3){
		errStr+="Please Select District.";
	}
		}
	
	if(errStr!= null && errStr.length>0){
		$('#ErrDiv').html(errStr);
		return;
	}
	 
	 $("#districtHeadingId").hide();
	 $("#constituencyHeadingId").hide();
    $('#assemblydivId').show();
	$("#buildAssConsActivity").html("<img style='margin-left: 390px;' src='images/Loading-data.gif'/>");
	var startDate = "";
	var endDate = "";
	var levelId = $("#activityLevelList option:selected").val();
	
	var dates=$('.searchDateCls ').val();
		if(dates != null && dates.trim().length > 0){
			var dateArray=dates.split("to");
		  startDate=dateArray[0];
		  endDate=dateArray[1];
		}
	
    var jsObj={   startDate:startDate,    //$("input[name=daterangepicker_start]").val(),
                  endDate:endDate,     //$("input[name=daterangepicker_end]").val(),
                  activityScopeId:$('#ActivityList').val(),
                  activityLevelId:$("#activityLevelList option:selected").val(),
				  levelId:levelId,
                  stateId:1
              };
       
     $.ajax({
      type : "GET",
      url : "asemblyConstWiseActivitiesAction.action",
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
       if(result!=null && result.length>0){
			buildAsemblyConstWiseActivities(result,levelId);
       }else{
         $("#buildAssConsActivity").html("NO DATA AVAILABLE...");
       }
     
    });   
   });

  function buildAsemblyConstWiseActivities(result,levelId){
    
	if(levelId == 3)
		$("#districtHeadingId").show();
	else
		$("#constituencyHeadingId").show();
	 
    var str ='';
    str+='<table class="table table-bordered table-responsive bg_ff dataTableDiv">';
          str+='<thead>';
            str+='<tr role="row">';
			if(levelId == 3)
				str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center"> DISTRICT </th>';
			else
				str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">ASSEMBLY CONSTITUENCY </th>';
              //str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">ASSEMBLY CONSTITUENCY </th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center">TOTAL ACTIVITIES</th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >PLANNED ACTIVITIES</th>';
			  str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;"  class="text_center">NOT PLANNED ACTIVITIES</th>';
              str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;"  class="text_center"> CONDUCTED ACTIVITIES</th>';
			  str+='<th style="background-color: rgb(0, 177, 125); color:#fff;cursor:pointer;" class="text_center" >NOT EXECUTED ACTIVITIES</th>';
            str+='</tr>';
          str+='</thead>';
        str+='<tbody>';
          for(var i in result){
            str+='<tr class="text_center">';
              
                str+='<td >'+result[i].name+'</td>';
             
              if(result[i].totalCount !=null){
                str+='<td >'+result[i].totalCount+'</td>';
              }else{
                str+='<td> 0 </td>';
              }
              if(result[i].plannedCount !=null){
                str+='<td >'+result[i].plannedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
			  if(result[i].notPlannedCount !=null){
                str+='<td >'+result[i].notPlannedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
              if(result[i].conductedCount !=null){
                str+='<td >'+result[i].conductedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
			  if(result[i].nonConductedCount !=null){
                str+='<td >'+result[i].nonConductedCount+'</td>';
              }else{
                str+='<td > 0 </td>';
              }
              str+='</tr>';
          }
        
        str+='</tbody>';
    str+='</table>';
    
    $("#buildAssConsActivity").html(str);
   
     $(".dataTableDiv").dataTable({
		"iDisplayLength": 10,
		"aLengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]]
	 });
	  $(".dataTableDiv").removeClass("dataTable");
	  $("#constituencyHeadingId").html(''+$("#activityLevelList option:selected").text()+' ASSEMBLY CONSTITUENCY WISE ACTIVITIES');
  }
  
	
	$(document).on("click","#updateQBtnId",function(){	
		var scopeId = $("#ActivityList").val();
		var locationValue = $(this).attr("attr_location_Value");
		if(scopeId==null || scopeId==0){
			alert("Please Select Activity Name");
			return false;
		}else{
			var jsObj={   
				scopeId : scopeId
            };
       
			$.ajax({
				type : "GET",
				url : "getQuestionnaireForScopeAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#questionsModal").modal("show");
				var str='';
				if(result!=null && result.activityVoList!=null && result.activityVoList.length>0){
					for(var i in result.activityVoList){
						str+='<div class="row">'
						str+='<div class="col-md-12 m_top10">';
							str+='<label>'+result.activityVoList[i].question+' ? </label><br/>';
						str+='</div>';
						str+='<div class="col-md-4">';

						if(result.activityVoList[i].optionsList!=null && result.activityVoList[i].optionsList.length>0){
							if(result.activityVoList[i].optionTypeId==1){
								str+='<select class="form-control selectedVal" attr_type="selectbox" attr_qid="'+result.activityVoList[i].questionId+'">';
								str+='<option value="0">Select Option </option>';
								for(var j in result.activityVoList[i].optionsList){
									str+='<option value="'+result.activityVoList[i].optionsList[j].optionId+'">'+result.activityVoList[i].optionsList[j].option+'</option>';
								}
								str+='</select>';
							}
							else if(result.activityVoList[i].optionTypeId==2){
								for(var j in result.activityVoList[i].optionsList){
									str+='&nbsp;&nbsp;<label><input type="checkbox" attr_type="ckeckBox" name="result'+result.activityVoList[i].questionId+'" class="selectedVal" attr_qid="'+result.activityVoList[i].questionId+'" value="'+result.activityVoList[i].optionsList[j].optionId+'"/>&nbsp;&nbsp;'+result.activityVoList[i].optionsList[j].option+'</label>';
								}
							}
							else if(result.activityVoList[i].optionTypeId==3){
									str+='&nbsp;&nbsp;<label><input type="text" name="result'+result.activityVoList[i].questionId+'" class="selectedVal" attr_qid="'+result.activityVoList[i].questionId+'" /></label>';
							}
						}
						str+='</div>';
						str+='</div>';
					}
					$("#questionsDivFooterId").html('<button type="button" id="saveResult" class="btn btn-custom btn-success" attr_location_Value="'+locationValue+'">Save</button>');
				}else{
					str+='<h4>No Data Found.</h4>';
				}
				$("#questionsDivBodyId").html(str);
			});
		}
	  
  });

	$(document).on("click","#saveResult",function(){
		var resultArr=[];
		$(".selectedVal").each(function(){
		var value='';
		var remarks='';
			if($(this).attr("attr_type")=="selectbox"){
				var key=$(this).attr("attr_qid");
				value=$(this).val();
			}
			if($(this).attr("attr_type")=="ckeckBox"){
				if(this.checked)
					value = this.value;			
			}
			else{
				remarks = $(this).val();
				value = "3";
			}
			 
			if(value != null && value.length>0)
			{
				var obj={
				questionId : $(this).attr("attr_qid"),
				optionId : value,
				remarks: remarks,
				count:0,
				others:" "
				};
				resultArr.push(obj);
			}	
				
		});
		 
		 var jsObj={
		         activityScopeId : $("#ActivityList").val(),
				 activityLevelId : $("#activityLevelList").val(),
				 activityLevelValue : $(this).attr("attr_location_Value"),
				 responseArray : resultArr
		       };
			  
		 $.ajax({
			type : "GET",
			url : "saveActivityQuestionnaireDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
		});
		//console.log(resultArr);
	});
	
	$(document).on("click","#uploadImagesId",function(){
		
		var locationValue = $(this).attr("attr_location_Value");
		var activityLevel = $("#activityLevelList").val();
		var activityScopeId = $("#ActivityList").val();
		var locationName = $(this).attr("attr_location_Name");
		
		
		
		var locationTypeId = $('#activityLevelList').val();
		var activityLevellId = $('#activityLevelList').val();
		
		var locationLevelId = $('#activityLevelList').val();
		
	    if(activityLevellId == 3 || activityLevellId == 4){}
		else{
			var firstChar = locationValue.substr(0,1);
			//locationLevelId = 5;
			if(activityLevellId == 2)
		    {
				if(firstChar == 2)
					locationLevelId = 5;//mandal
				else if(firstChar == 3)
					locationLevelId = 8;//division
				else if(firstChar == 1)
					locationLevelId = 7;//localEleBody	
			}
			else if(activityLevellId == 1)
			{
				if(firstChar == 1)
					locationLevelId = 6;//panchayat
				else if(firstChar == 2)
					locationLevelId = 8;//ward
			}
		}
		
		window.open('eventFieUploadAction.action?activityScopeId='+activityScopeId+'&locationValue='+locationValue+'&activityLevel='+locationLevelId+'&locationName='+locationName+'','_blank');
	});
	
	$(document).on("change","#activityTypeList",function(){
		getActivityNames();
	});
</script>
</body>
</html>