<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <html>
  <head>	
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>
<script src="js/maps/googleMap.js"></script>
		<style>
		
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.username thead tr:nth-child(2){ background:#eee;}		
			.username td:first-child{ min-width: 200px; }		
			.username th small{ font-size:11px; }				
			.username th{ text-align:center; }
			
			.requiredFont{
				color:red;
				font-size:13px;
			}

			#errorDiv{
				font-weight:bold;
				color:red;
			}
			
			#retunMsg
			{
			 font-weight:bold;
			 color:green;
			}
			.ui-multiselect{
				width:200px !important;
				}
				#constituencyId{
				width:200px !important;
				}
			
			.survey_nav{height:40px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:40px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 12px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			.requiredFont{
				color:red;
				font-size:13px;
			}
			
			.highlight{
			cursor: pointer;
			}
			#FinalReportWithTPTableId th{ text-align:center;}
			
			
			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
			.wiget-yellow-normal{background:#ffcc00; border:1px solid #ccc; width:100%; padding:10px;}
			.wiget-yellow-normal h3{font-size:16px;border-bottom:2px solid #eee; line-height:20px;padding:5px; }
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			
			.Constituency-name-nav li a{width:165px;line-height:40px;color:#333333;display:block; background:#eee;padding:10px; border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 5px;text-decoration:none; font-size:14px; display: inline-table;float:left;}
			.Constituency-name-nav li a:hover{color:#333333;border:1px solid #ffcc00; box-shadow:0px 0px 3px #ccc;}
			
			.wiget-yellow-normal:hover{ box-shadow: 0 -1px 5px rgb(51, 51, 51);}
			.Constituency-name-nav li a:hover{color:#333333;border:1px solid #ffcc00;  background-color:rgb(204,204,204);}
		</style>	
	
</head>
<body>
  <div class="container">
	<div class="row">
		<div class="span12" align="center">
			<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide"  id="ajaxImg"/>	
		</div>
		<div class="span12">
			<div class="row-fluid">
				<div class="span12 widgetservey_Red m_top20"  id="constituencyWiseReport1" style="display:none;">
					<h4> CONSTITUENCY WISE ${task} REPORT </h4>		
					
					<div id="constituencyWiseReport"></div>					
				</div>		
			</div>
		</div>
		<div class="span12" align="center">
			<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide"  id="boothAjaxImg"/>	
		</div>
		<div class="span12">
			<div class="row-fluid">
				<div class="span12 widgetservey_Red m_top20" id="boothWiseReport1" style="display:none;">
					<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide offset6"  id="bAjaxImg"/>	
						<div id="boothWiseReport"></div>					
				</div>		
			</div>
		</div>
		
		<div class="span12">
			<div class="row-fluid">
				<div class="span12 widgetservey_Red m_top20" id="boothWiseStatusDtls1" style="display:none;">
					<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide offset6"  id="vAjaxImg"/>
					<div id="votersReport"></div>	

					<div id="boothWiseStatusDtls"></div>
					
				</div>		
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span12" >
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20" id="weathermap" style="display:none;">
					<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide offset6"  id="mapAhax"/>
						<h4 id="heading">SURVEY DETAILS</h4>
						<div id="map" style="width: 100%; height: 400px;cursor: pointer;"></div>
					</div>
				</div>
			</div>
		</div>
		
	</div>	
	</div>



<script>
var reportType = '${constituencyId}';
if(reportType == 1)
{
	getConstituencyWiseCasteCollectionDetails('${regionId}','${userTypeId}');
}
else if(reportType == 2)
{
	$('#ajaxImg').show();
	getConstituencyWiseTeamCollecetdDetails('${regionId}','${userTypeId}','${fromDate}','${toDate}');
}
else if(reportType == 3)
{
	$('#ajaxImg').show();
	getConstituencyWiseQcVerificationSummary('${regionId}','${userTypeId}','${fromDate}','${toDate}')
}
else if(reportType == 4)
{
	$('#ajaxImg').show();
	getConstituencyWiseTeamDetails('${regionId}','${userTypeId}','${fromDate}','${toDate}')
}

function getConstituencyWiseTeamCollecetdDetails(type,stateId,fromDate,toDate)
{
	var jsObj = {
		type : type,
		stateId : stateId,
		fromDate : fromDate , 
		toDate : toDate
	}
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseTeamCollectedSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
			if(result != null)
			{
				buildTeamConstituencyWiseDetailsSummary(result,type,stateId,fromDate,toDate);
			}
		});	
		
}

function buildTeamConstituencyWiseDetailsSummary(result,strTypr,stateId,fromDate,toDate)
{
	$('#constituencyWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped" id="constiTeamTable">';
	str += '<thead  class="alert alert-success">';
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Booths</th>';
	str += '<th>CollectedVoters</th>';
	str += '<th>Map</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a style="cursor: pointer;" onClick="getBoothWiseTeamCollecetdDetails('+result[i].dcVotersCount+',\''+strTypr+'\','+stateId+',\''+result[i].dcPercentage+'\')">'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '<td>'+result[i].verifierVotersCount+'</td>';
		str += '<td><a onClick="getUserCollectedLocations('+result[i].dcVotersCount+',0,null,\''+result[i].dcPercentage+'\',\'constituency\')"><img src="images/DC.png" style="height:30px;"></img></a></td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#constituencyWiseReport').html(str);
	$('#constituencyWiseReport1').css('display','block');
	$('#constiTeamTable').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#ajaxImg').hide();
}


function getConstituencyWiseQcVerificationSummary(type,stateId,fromDate,toDate)
{
	var strTypr = '';
	if(type == 0)
	{
		strTypr = "null";
	}
	else if(type == 1)
	{
		strTypr = "Y";
	}
	else
	{
		strTypr = "N";
	}
	var jsObj = {
		type : strTypr,
		stateId : stateId,
		fromDate : fromDate,
		toDate : toDate
	}
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseQcVerificationSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			 {
				buildQcConstituencyWiseSummary(result,strTypr,stateId,fromDate,toDate);
			 }
		});	
}

function buildQcConstituencyWiseSummary(result,strTypr,stateId,fromDate,toDate)
{
	$('#constituencyWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped " id="boothWiseTeamTable">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Booths</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a  style="cursor: pointer;" onClick="getBoothWiseQcVerificationSummary('+result[i].dcVotersCount+',\''+strTypr+'\','+stateId+',\''+result[i].dcPercentage+'\')">'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '<td>'+result[i].verifierVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#constituencyWiseReport').html(str);
	$('#constituencyWiseReport1').css('display','block');
	$('#boothWiseTeamTable').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#ajaxImg').hide();;
}
function getBoothWiseQcVerificationSummary(constituencyId,type,stateId,name)
{
	$('#boothWiseReport').html('');
	$('html, body').animate({
        scrollTop: $("#boothWiseReport").offset().top
    }, 2000);

	$('#boothAjaxImg').show();
	var fromDate = '${fromDate}';
	var toDate = '${toDate}';
	var jsObj = {
		constituencyId : constituencyId,
		type : type,
		stateId : stateId,
		fromDate : fromDate,
		toDate : toDate
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseQcVerificationSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildQcBoothWiseQCSummary(result,name);
			}
			else
			{
				$('#boothAjaxImg').hide();
			}
		});	
}

function buildQcBoothWiseQCSummary(result,name)
{

	var str = '';
	str += '<h4>'+name+' Constituency Wise Booth Details</h4>';
	str += '<table class="table table-bordered m_top20 table-hover table-striped" id="constiQcTable">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Survey User</th>';
	str += '<th>Mobile No</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].dcPercentage+'</td>';
		str += '<td>'+result[i].verifierPercentage+'</td>';
		str += '<td>'+result[i].qcPercentage+'</td>';
		str += '<td>'+result[i].verifierVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#boothWiseReport').html(str);
	$('#boothWiseReport1').css('display','block');
	$('#constiQcTable').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#boothAjaxImg').hide();
}

function getBoothWiseTeamCollecetdDetails(constituencyId,surveyUserTypeId,stateId,name)
{
	$('#boothWiseReport').html('');
	$('html, body').animate({
        scrollTop: $("#boothWiseReport").offset().top
    }, 2000);
	$('#boothAjaxImg').show();
	var fromDate = '${fromDate}';
	var toDate = '${toDate}';
	var jsObj = {
		constituencyId : constituencyId,
		surveyUserTypeId : surveyUserTypeId,
		stateId : stateId,
		fromDate : fromDate,
		toDate : toDate
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildBoothWiseTeamCollecetedDetails(result,name);
			}
			else
			{
				$('#boothAjaxImg').hide();
			}
		});	

}

function buildBoothWiseTeamCollecetedDetails(result,name)
{
	$('#boothWiseReport').html('');
	var str = '';
	str += '<h4>'+name+' Constituency Wise Booth Details</h4>';
	str += '<table class="table table-bordered m_top20 table-hover table-striped" id="boothQcTable">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Survey User</th>';
	str += '<th>Mobile No</th>';
	str += '<th>Voters</th>';
	str += '<th>Map</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].dcPercentage+'</td>';
		str += '<td>'+result[i].qcPercentage+'</td>';
		str += '<td>'+result[i].verifierPercentage+'</td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '<td><a style="cursor: pointer;" onClick="getUserCollectedLocations('+result[i].dcVotersCount+','+result[i].verifierVotersCount+',\''+result[i].dcPercentage+'\',\''+name+'\',\'booth\')"><img src="images/DC.png" style="height:30px;"></img></a></td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#boothWiseReport').html(str);
	$('#boothWiseReport1').css('display','block');
	$('#boothQcTable').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#boothAjaxImg').hide();
}
function getConstituencyWiseTeamDetails(type,stateId,fromDate , toDate)
{
	var jsObj = {
		type : type,
		stateId :stateId,
		fromDate : fromDate,
		toDate : toDate
	}
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
			if(result != null)
			{
				buildTeamConstituencyWiseSummary(result,type,stateId,fromDate , toDate);
			}
		});	
		
}

function buildTeamConstituencyWiseSummary(result,strTypr,stateId,fromDate,toDate)
{
	$('#constituencyWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped " id="constiTeamCollectedTable">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Booths</th>';
	str += '<th>Users</th>';
	str += '<th>Map</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a style="cursor: pointer;" onClick="getBoothWiseTeamDetails('+result[i].qcVotersCount+',\''+strTypr+'\','+stateId+',\''+result[i].dcPercentage+'\')">'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].dcBoothsCount+'</td>';
		str += '<td>'+result[i].dcConstituencysCount+'</td>';
		str += '<td><a onClick="getUserCollectedLocations('+result[i].qcVotersCount+',0,null,\''+result[i].dcPercentage+'\',\'constituency\')"><img src="images/DC.png" style="height:30px;"></img></a></td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#constituencyWiseReport').html(str);
	$('#constituencyWiseReport1').css('display','block');
	$('#constiTeamCollectedTable').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#ajaxImg').hide();
}

function getBoothWiseTeamDetails(constituencyId,surveyUserTypeId,stateId,name)
{
	$('#boothWiseReport').html('');
	$('html, body').animate({
        scrollTop: $("#boothWiseReport").offset().top
    }, 2000);

	$('#boothAjaxImg').show();
	var fromDate = '${fromDate}';
	var toDate = '${toDate}';
	var jsObj = {
		constituencyId : constituencyId,
		surveyUserTypeId : surveyUserTypeId,
		stateId : stateId,
		fromDate : fromDate,
		toDate : toDate
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildBoothWiseTeamDetails(result,name);
			}
		});	
}
function buildBoothWiseTeamDetails(result,name)
{
	var str = '';
	str += '<h4>'+name+' Constituency Booth Wise Details</h4>';
	str += '<table class="table table-bordered m_top20 table-hover table-striped" id="boothTeamCollectedTable">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Survey User</th>';
	str += '<th>Mobile No</th>';
	str += '<th>Collected Voters</th>';
	str += '<th>Map</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].dcPercentage+'</td>';
		str += '<td>'+result[i].qcPercentage+'</td>';
		str += '<td>'+result[i].verifierPercentage+'</td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '<td><a style="cursor: pointer;" onClick="getUserCollectedLocations('+result[i].dcVotersCount+','+result[i].verifierVotersCount+',\''+result[i].dcPercentage+'\',\''+name+'\',\'booth\')"><img src="images/DC.png" style="height:30px;"></img></a></td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#boothWiseReport').html(str);
	$('#boothWiseReport1').css('display','block');
	$('#boothTeamCollectedTable').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#boothAjaxImg').hide();
}


function getConstituencyWiseCasteCollectionDetails(regionId,userTypeId)
{
	$('#constituencyWiseReport').html('');
	$('#ajaxImg').show();

	$.ajax({
		type:'GET',
		url: 'getCasteCollectedDetails.action',
		dataType: 'json',
		data: {regionId:regionId,userTypeId:userTypeId,startDate:'${startDate}',endDate:'${endDate}'},
	}).done(function(result){
			buildConstituencyWiseDetails(result,userTypeId);
	});
}
function buildConstituencyWiseDetails(result,userTypeId)
{
	var str ='';

	//str+='<h4 class=""> CONSTITUENCY WISE CASTE COLLECTION DETAILS </h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="constnDtls">';
	str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>Constituency Name</th>';
	  str+='<th>Total Booths</th>';
	  str+='<th>Total Voters</th>';
	  str+='<th>Completed Booths</th>';
	  str+='<th>Completed Voters</th>';
	 str+='</tr>';
	str+='</thead>';

    str+='<tbody>';
	$.each(result,function(index,value){
	 str+='<tr>';
	  str+='<td><a href="javascript:{getConstituencySurveyDetails('+value.locationId+','+userTypeId+',\''+value.locationName+'\')}">'+value.locationName+'</a></td>';
	  str+='<td>'+value.totalBooths+'</td>';
	  str+='<td>'+value.totalVoters+'</td>';
	  str+='<td>'+value.collectedBoothsCount+'</td>';
	  str+='<td>'+value.collectedVotersCount+'</td>';
	 str+='</tr>';

	});
	str+='</tbody>';
	str+='</table>';

	$('#constituencyWiseReport').html(str);
$('#constituencyWiseReport1').css('display','block');
	$('#constnDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});

	$('#ajaxImg').hide();

}

function getConstituencySurveyDetails(constituencyId,userTypeId,locationName)
{
	$('#boothWiseReport,#votersReport').html('');
	//$('#boothHeading').show();
	 $('html, body').animate({
        scrollTop: $("#boothWiseReport").offset().top
    }, 2000);
	$('#boothAjaxImg').show();

	$.ajax({
		type:'GET',
		url: 'getSurveyDetailsByConstituencyId.action',
		dataType: 'json',
		data: {constituencyId:constituencyId,userTypeId:userTypeId},
	}).done(function(result){
			buildConstituencySurveyDetails(result,userTypeId,locationName);
	});
}
function buildConstituencySurveyDetails(result,userTypeId,locationName)
{
	var str = '';

	str+='<h4 class="">'+locationName+' CONSTITUENCY BOOTH WISE CASTE COLLECTION DETAILS</h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="boothDtls">';
	 str+='<thead class="alert alert-success">';
	  str+='<tr>';
	   str+='<th>Booth No</th>';
	   str+='<th>Total Voters</th>';
	   str+='<th>Collected Voters</th>';
	   str+='<th>Users</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
	  $.each(result,function(index,value){
		  str+='<tr>';
			str+='<td><a href="javascript:{getBoothWiseCollectedcasteDetails('+value.locationId+','+value.locationName+','+userTypeId+')}">'+value.partNo+'</a></td>';
			str+='<td>'+value.totalVoters+'</td>';
			str+='<td>'+value.collectedVotersCount+'</td>';
			str+='<td>'+value.name+'</td>';
		  str+='</tr>';
	  });
	 str+='</tbody>';
	str+='</table>';

	$('#boothWiseReport').html(str);
	$('#boothWiseReport1').css('display','block');
	$('#boothDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#boothAjaxImg').hide();

}

function getBoothWiseCollectedcasteDetails(boothId,boothNo,userTypeId)
{
	$('#votersReport').html('');
	$('#vAjaxImg').show();
    $('html, body').animate({
		scrollTop: $("#votersReport").offset().top
	}, 2000);

	$.ajax({
		type:'GET',
		url: 'getBoothWiseCollectedcasteDetails.action',
		dataType: 'json',
		data: {constituencyId:boothId,userTypeId:userTypeId},
	}).done(function(result){
			if(userTypeId != 0)
  			  buildBoothWiseCasteCollectedDetails(result,boothNo);
			else
			  buildBoothWiseVerifiedCasteCollectedDetails(result,boothNo);
			  $('#vAjaxImg').hide();
			 // $('#votersReport').scrollTo(500);		
	});

}
function buildBoothWiseCasteCollectedDetails(result,boothNo)
{
	$('#boothWiseStatusDtls1').css('display','block');
	var str ='';

	str+='<h4 class="">COLLECTED CASTE DETAILS FOR BOOTH-'+boothNo+'</h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="votersDtls">';
	 str+='<thead class="alert alert-success">';
	  str+='<tr>';
	   str+='<th>Voter Name</th>';
	   str+='<th>Relative Name</th>';
	   str+='<th>Gender</th>';
	   str+='<th>Age</th>';
	   str+='<th>House No</th>';
	   str+='<th>Caste</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		  str+='<tr>';
		    str+='<td>'+value.voterName+'</td>';
			str+='<td>'+value.relativeName+'</td>';
			str+='<td>'+value.gender+'</td>';
			str+='<td>'+value.age+'</td>';
			str+='<td>'+value.houseNo+'</td>';
			str+='<td>'+value.casteName+'</td>';
		  str+='</tr>';

	 });

	 str+='</tbody>';
	str+='</table>';
	$('#votersReport').html(str);
	$('#boothWiseStatusDtls1').css('display','block');
   $('#votersDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});

}

function buildBoothWiseVerifiedCasteCollectedDetails(result,boothNo)
{
	var str ='';
	$('#boothWiseStatusDtls1').css('display','block');
	str+='<h4 class="">COLLECTED CASTE DETAILS FOR BOOTH-'+boothNo+'</h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="votersDtls">';
	 str+='<thead class="alert alert-success">';
	  str+='<tr>';
	   str+='<th>Voter Name</th>';
	   str+='<th>Relative Name</th>';
	   str+='<th>Gender</th>';
	   str+='<th>Age</th>';
	   str+='<th>House No</th>';
	   str+='<th>Collected Caste</th>';
	   str+='<th>Verified Caste</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		  str+='<tr>';
		    str+='<td>'+value.voterName+'</td>';
			str+='<td>'+value.relativeName+'</td>';
			str+='<td>'+value.gender+'</td>';
			str+='<td>'+value.age+'</td>';
			str+='<td>'+value.houseNo+'</td>';

			if(value.dcCasteName != null)
 			 str+='<td>'+value.dcCasteName+'</td>';
			else
			 str+='<td>-</td>';

			if(value.dcCorrectedCasteName != null)
 			 str+='<td>'+value.dcCorrectedCasteName+'</td>';
			else
			 str+='<td>-</td>';
		  str+='</tr>';

	 });

	 str+='</tbody>';
	str+='</table>';
	$('#votersReport').html(str);

	$('#votersDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
}


function getUserCollectedLocations(boothId , surveyUserId,boothNo,constituency,type)
{	
	$('#mapDiv').show();
	$('#mapAhax').show();
	$('#weathermap').show();
	var fromDate = '${fromDate}';
	var toDate = '${toDate}';
	var jsObj = {
		boothId : boothId,
		surveyUserId : surveyUserId,
		fromDate : fromDate,
		toDate : toDate,
		type : type
	}
	$.ajax({
			type:'GET',
			url: 'getUserWiseCollecetionDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildUserLocationDetails(result,boothNo,constituency,type);
			}
		});	
}
var campus = {
"type": "Point",
"crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
"features": []
}
var polyline = '';
function buildUserLocationDetails(result,boothNo,constituency,type)
{

	$('#weathermap').html('');
	$('html, body').animate({
        scrollTop: $("#weathermap").offset().top
    }, 2000);
	var headStr = '';
	if(type == '')
	{
		headStr = " "+constituency+" Constituency Booth - "+boothNo+" Caste Collected Locations";
	}
	else
	{
		headStr = " "+constituency+" Constituency  Caste Collected Locations";
	}
	document.getElementById('weathermap').innerHTML = "<h4>"+headStr+"</h4><div class='span12 m_top20 widgetservey' id='map' style='height:500px'></div>";
	
	$.each(result,function(index,value){
	if(value.desc != null && value.name != null)
	{
		var voterDetails = 
		{ "type": "Feature",
		"properties": {},
			"geometry": { "type": "Polygon", "coordinates": [[[value.name,value.desc]]] }
		};
		campus.features.push(voterDetails);
	}
	});
	var zoomSize = '';
	if(type == 'booth')
	{
		zoomSize = 15;
	}
	else
	{
		zoomSize = 10;
	}
	var map = new L.Map('map').setView(new L.LatLng(result[0].desc,result[0].name), zoomSize);
	var osm = new L.TileLayer('http://{s}.tile.osmosnimki.ru/kosmo/{z}/{x}/{y}.png');
	var mpn = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
	var qst = new L.TileLayer('https://otile1.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png', {attribution:'Tiles Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="https://developer.mapquest.com/content/osm/mq_logo.png">'}).addTo(map);
	

	map.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	var link = new L.Control.Permalink();
	map.addControl(link);
	map.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	 ));
	L.geoJson(campus, {
		
		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,
		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}
	}).addTo(map);
	
	for(var i in result)
	{
		if(result[i].desc != null && result[i].name != null)
		{
			var iconImg = 'images/DC.png';		
			var icon = L.icon({
			iconUrl: iconImg,

			iconSize:     [30, 30], // size of the icon
			shadowSize:   [10, 10], // size of the shadow
			iconAnchor:   [10, 10], // point of the icon which will correspond to marker's location
			shadowAnchor: [4, 62],  // the same for the shadow
			popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
		   });
			var markers = new L.Marker([result[i].desc,result[i].name],{icon: icon});
			//var popuoContent = "<table class='table table-bordered m_top20 table-hover table-striped username'><tr><td>Name : </td><td>"+result[i].name+"</td></tr>";
			map.addLayer(markers);	
		}
		
	}
	$('#mapAhax').hide();
}
function onEachFeature(feature, layer)
{    
}
</script>
<script>
//boothWiseStatusDetailsByConstituency();
function boothWiseStatusDetailsByConstituency()
{
	$.ajax({
		type:'GET',
		url: 'getAllBoothsStatusDetailsByConstituencyId.action',
		dataType: 'json',
		data: {constituencyId:217},
	}).done(function(result){
		buildBoothWiseStatusDetails(result);
	});
}
function buildBoothWiseStatusDetails(result)
{
	var str ='';

    str+='<div class="span3 offset1">';
	str+='<table class="table table-bordered m_top20 table-hover table-striped">';
	 str+='<thead class="alert alert-success">';
      str+='<tr>';
	    str+='<th>Boot No</th>';
		str+='<th>DC</th>';
		str+='<th>DV</th>';
		str+='<th>QC</th>';
		str+='<th>WM-DC</th>';
		str+='<th>WM-DV</th>';
      str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		 str+='<tr>';
		    str+='<td>'+value.partNo+'</td>';
			str+='<td>'+value.dcCompleted+'</td>';
			str+='<td>'+value.dvCompleted+'</td>';
			str+='<td>'+value.qcCompleted+'</td>';
			str+='<td>'+value.wmDcCompleted+'</td>';
			str+='<td>'+value.wmDvCompleted+'</td>';
		 str+='</tr>';
	 });
	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#boothWiseStatusDtls').html(str);
	$('#boothWiseStatusDtls1').css('display','block');
}

</script>
</body>
</html>