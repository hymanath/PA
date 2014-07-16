<page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />



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
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 4px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			.requiredFont{
				color:red;
				font-size:13px;
			}
			
			.highlight{
			cursor: pointer;
			}
		</style>	
	
  </head>
  
  <body>
  <script>
  
  $( document ).ready(function() {
$('#boothId').multiselect({
	  noneSelectedText:"Select Booth(s)"});
});
  </script> 
		<div class="container">
		<div class="row">
			<div class="span12 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a class="highlight selected" id="stateWiseReportTab" onclick="showHideReportTabs(this.id);"> State Wise Report </a></li>
					<li><a class="highlight" id="dataCollectorTab" value="1" onclick="showHideReportTabs(this.id);"> Data Collector Report </a></li>
					<li><a class="highlight" id="verifierReportTab" value="2" onclick="showHideReportTabs(this.id);">Verifier Report</a></li>
					<li><a class="highlight" id="thirdPartyReportTab" value="3" onclick="showHideReportTabs(this.id);"> Third Party Report </a></li>
					<li><a class="highlight" id="userTrackingReportTab" onclick="showHideReportTabs(this.id);"> User Tracking Report</a></li>
					<li><a class="highlight" id="comparisonReportTab" onclick="showHideReportTabs(this.id);"> Comparison Report </a></li>

				</ul>
			</div>
		</div>
  
  
  
  
  <div class="container">
		<!---- Survey monitoring---->		
		<div class="row" id="verifierReportId">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4 id="titleId"></h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
								<div class="offset1">
									<div class="row-fluid">
										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font>
											<!--<select id="constituencyId" onChange="getBoothsDetailsByConstituencyId(this.value)"></select>-->
												<s:select theme="simple"  name="constituency" id="constituencyId"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsByConstituencyId(this.value)"/>
										</div>
										<div class="span3">
											<!--User Type <font class="requiredFont">*</font>
											<select class="input-block-level" id = "userType"> <option value="0">Select User Type</option></select>-->
											Select Booth 
											<select class="input-block-level" id = "boothId" multiple="true"> <option value="0">Select Booth</option></select></div>
											<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
								<img id="boothImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
							
										</div>
										<div class="span2">
											From Date <font class="requiredFont">*</font>
											<div class="input-append">
											<input type="text" placeholder="From Date..." class="input-block-level date" id="fromDate" readonly>
											</div>
										</div>
										<div class="span2">
											To Date <font class="requiredFont">*</font>
											<div class="input-append">
											 <input type="text" placeholder="To Date..." class="input-block-level date" id="toDate" readonly>
											</div>
										</div>
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
						<!--	<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReport()">SUBMIT</button></div>-->
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReportByConstituencyIdAndUserType()">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
                             <div id="dayWiseReportDiv1" class="clearCls"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="mainajaximg" style="width:70px;height:60px;display:none;"/>
					</div>
				</div>
				
				<div class="row-fluid " >
				
					<div class="span12 m_top20 widgetservey" id="reportDiv" style="display:none;" class="clearCls">
					
						<h4 id="heading">Verifier Report For Daily Verification </br><small style="color:#333;padding-bottom:10px;display:inline-block;">  From 26 - June - 2014 to 3 - July - 2014 </small> </h4>
							
						<div class="row-fluid" style=" height:300px;">
						<div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							
						</div>
							
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container" style="display:none;margin-top:20px;" id="surveyUserTrackingId">
		<jsp:include page="surveyUserTracking.jsp" flush="true"/>	
	</div>
	
	<div class="container" style="display:none;margin-top:20px;" id="comparisonReportId">
		<jsp:include page="surveyMonitoring.jsp" flush="true"/>	
	</div>
	
	<div class="container" style="display:none;margin-top:20px;" id="stateWiseReportId">
		<jsp:include page="surveyDashBoard.jsp" flush="true"/>	
	</div>
	<script>
/*	function getBoothDetails()
{
	var jObj =
	{
	 constituencyId:$('#constituencyId').val()
	}
	$.ajax({
			type:'GET',
			url: 'getBoothsByConstituency.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
			$("#boothId option").remove();
			$("#boothId").append("<option value='0'>Select Booth</option>");
			for(var i in result){
                $("#boothId").append("<option value="+result.id+">"+result.partiesInMandal[i].name+"</option>");
			}
	});
		
}*/
//getconstituencies();
function getconstituencies()
{


	var jsObj =
	{
	
	task : "getConstituencies"
	}
	$.ajax({
	type:'GET',
	url: 'getsurveyuserConstituenciesAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){

	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
		
	$("#constituencyId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	
	});
}
$(function() {
	$(".date").datepicker({ 
	dateFormat: 'dd-mm-yy',
   }).datepicker('setDate', new Date());
  
});

	
function getUserTypes(divId)
{
	var jsObj =
	{

	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserTypeAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}

	});
}
function getDayWiseReport()
{

$("#dayWiseReportDiv").html('');
$("#heading").html('');
	var constituencyId = $("#constituencyId").val();
	var userTypeId = $("#userType").val();
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var heading = $( "#userType option:selected" ).text();
	if(constituencyId == 0)
	{
		$("#errorDiv").html("Please Select Constituency").css("color","red");
		return;
	}
	if(userTypeId == 0)
	{
		$("#errorDiv").html("Please Select User Type").css("color","red");
		return;
	}
	if(startDate.length == 0 || endDate.length == 0)
	{
		$("#errorDiv").html("Please Select From Date").css("color","red");
		return;
	}
	if( endDate.length == 0)
	{
		$("#errorDiv").html("Please Select To Date").css("color","red");
		return;
	}
	if(startDate.length > 0 && endDate.length > 0 )
	{		    
		  var dt1  = parseInt(startDate.substring(0,2),10);
		  var mon1 = parseInt(startDate.substring(3,5),10);
		  var yr1  = parseInt(startDate.substring(6,10),10);
		  var dt2  = parseInt(endDate.substring(0,2),10);
		  var mon2 = parseInt(endDate.substring(3,5),10);
		  var yr2  = parseInt(endDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorDiv').html("From Date should be Less Than To Date");
		 return;
		}
	}
	$("#mainajaximg").show();
	var jObj =
	{
	 constituencyId:constituencyId,
     userTypeId:userTypeId,
	 startDate:startDate,
	 endDate:endDate,
	heading:heading

	};
	$.ajax({
			type:'GET',
			url: 'getDayWisereportDetailsByConstituencyId.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				$("#mainajaximg").hide();
				$("#reportDiv").css("display","block");
						buildDayWiseReport(result,heading,startDate,endDate);
				
		});
}

function buildDayWiseReport(result,heading,startDate,endDate)
{
var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

var dt1 = startDate.split("-").reverse().join("-");
var d = new Date(dt1);
var strdate= d.getDate() + '-' +  months[d.getMonth()] + '-' + d.getFullYear();

var dt2 = endDate.split("-").reverse().join("-");
var d1 = new Date(dt2);
var enddate= d1.getDate() + '-' +  months[d1.getMonth()] + '-' + d1.getFullYear();
if(strdate < enddate)
	{
	$("#heading").html(''+heading+'  Report For Daily Verification  </br><small style="color:#333;padding-bottom:10px;display:inline-block;">  From '+strdate+' to '+enddate+' </small> ');
	}

	else
	{
	$("#heading").html(''+heading+'  Report For Daily Verification  </br><small style="color:#333;padding-bottom:10px;display:inline-block;"> '+strdate+' </small> ');
	}
	$("#errorDiv").html("");
	if(result.length == 0)
	 $('#dayWiseReportDiv').html('<font style="color:red;">No data available....</font>');
	 else
	 {
  var str = '';

  str+='<table class="table table-bordered m_top20 table-hover table-striped offset3" style="width:422px;">';
  str+='<thead class="alert alert-success">';
   str+='<tr>';
    str+='<th>UserName</th>';
	$.each(result[0].subList,function(index,value){
      str+='<th>'+value.surveyDate1+'</th>';
	});
   str+='</tr>';
  str+='</thead>';
  str+='<tbody>';
  
    $.each(result,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.userName+'</td>';
		   $.each(value.subList,function(index1,value1){
			    str+='<td><a href="javascript:{getDayWiseReportDetailsOfUser('+value.userid+',\''+value.surveyDate1+'\')}">'+value1.count+'</a></td>';
		   });
		str+='</tr>';
	});
  
  str+='</tbody>';
  str+='</table>';

  $('#dayWiseReportDiv').html(str);
  }
}


function getDayWiseReportDetailsOfUser(userId,surveyDate)
{
	
  $('#boothWiseCountDivId').html('');
 // var startDate = $("#fromDate").val();
	//var endDate = $("#toDate").val();
	var jObj =
	{
	 userId:userId,
	 startDate:surveyDate
	// endDate:endDate
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseUserSamplesDetailsByDates.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				buildUserBoothWiseCountDetails(result);
		});
}


function buildUserBoothWiseCountDetails(result)
{
  var str ='';

  str+='<table class="table table-bordered m_top20 table-hover table-striped username">';
   str+='<thead class="alert alert-success">';
    str+='<tr>';
	  str+='<th>Booth No</th>';
	  str+='<th>Total</th>';
	  str+='<th>Completed</th>';
	str+='</tr>';
   str+='</thead>';	  
   str+='<tbody>';
    $.each(result,function(index,value){
		str+='<tr>';
		str+='<td>'+value.partNo+'</td>';
		str+='<td>'+value.totalVoters+'</td>';
		str+='<td>'+value.count+'</td>';
		str+='</tr>';
	});
   str+='</tbody>';
  str+='</table>';

  $('#boothWiseCountDivId').html(str);
  $('#boothWiseCountDivId').dialog({
			
			autoOpen: true,
			show: "blind",
			width: 500,
            title:'Booth Details', 			
			modal: true,
			height:"auto",
			hide: "explode"
		});
}


	</script>
	<script>	
	getUserTypes('userType');
	</script>
<script>

function getDayWiseReportByConstituencyIdAndUserType()
{
$('#dayWiseReportDiv1,#retunMsg,#errorDiv').html('');
	var constituencyId = $("#constituencyId").val();
	//var userTypeId = $("#userType").val();
	var userTypeId = userTypeVal;
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	var heading = $( "#userType option:selected" ).text();

	var errorStr ="";

	if(constituencyId == 0)
		errorStr += 'Please select constituency<br>';


if(startDate.length == 0 || endDate.length == 0)
	{
		errorStr += 'Please Select From Date<br>';
	}
	if( endDate.length == 0)
	{
		errorStr += 'Please Select To Date<br>';
	}
	if(startDate.length > 0 && endDate.length > 0 )
	{		    
		  var dt1  = parseInt(startDate.substring(0,2),10);
		  var mon1 = parseInt(startDate.substring(3,5),10);
		  var yr1  = parseInt(startDate.substring(6,10),10);
		  var dt2  = parseInt(endDate.substring(0,2),10);
		  var mon2 = parseInt(endDate.substring(3,5),10);
		  var yr2  = parseInt(endDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
			errorStr += 'From Date should be Less Than To Datee';
		}
	}

	if(errorStr.length >0)
	{
        $('#errorDiv').html(errorStr);
		return;
	}

	
	var jObj =
	{
	  constituencyId:constituencyId,
      userTypeId:userTypeId,
	  startDate:startDate,
	  endDate:endDate,
	  heading:heading,
      boothIds:[]
 	};
	
	$('#mainajaximg').show();

	 jObj.boothIds= $('#boothId').val();

	  $.ajax({
			type:'GET',
			url: 'getDayWiseReportByConstituencyIdAndUserType.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				buildDayWiseReportByUserType(result);				
		});
}
function buildDayWiseReportByUserType(result)
{
$('#mainajaximg').hide();
	if(result == null || result.length == 0)
	{
		$('#retunMsg').html("NO DATA AVILABLE");
		return;
	}

	 var str = '';

   str+='<table class="table table-bordered m_top20 table-hover table-striped username">';
    str+='<thead class="alert alert-success">';
	 str+='<tr>';
	  str+='<th>UserName</th>';
  	  str+='<th>Booth No</th>';
	  str+='<th>Total Voters</th>';

	    $.each(result[0].subList,function(index,value){
          str+='<th>'+value.surveyDate+'</th>';
		});

	 str+='</tr>';
	str+='</thead>';

   str+='<tbody>'
   $.each(result,function(index,value){
    str+='<tr>';
	   str+='<td>'+value.userName+'</td>';
   	   str+='<td>'+value.partNo+'</td>';
	   str+='<td>'+value.totalVoters+'</td>';
	     $.each(value.subList,function(index1,value1){
			   str+='<td>'+value1.count+'</td>';
		 });

		
    str+='</tr>';

   });
   	str+='<tr>';
	str+='<td><b>Total</b></td>';
	str+='<td></td>';
	str+='<td></td>';
	$.each(result[0].subList,function(index,value){
          str+='<td><b>'+value.total+'</b></td>';
		});
	str+='</tr>';
   str+='</tbody>';
   str+='</table>';

 $('#dayWiseReportDiv1').html(str);

}

function getBoothsDetailsByConstituencyId(constituencyId)
{
	$("#boothImage").show();
	var jObj =
	{
	  constituencyId:constituencyId     
	};

	 $.ajax({
			type:'GET',
			url: 'getBoothDetailsByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){				
				//buildDayWiseReportByUserType(result);		
				$("#boothImage").hide();
				$('#boothId').find('option').remove();

				$.each(result,function(index,value){
					$('#boothId').append('<option value="'+value.boothId+'">Booth - '+value.partNo+'</option>');
				});
				
				$('#boothId').multiselect('refresh');

		});	
}

function showHideReportTabs(id)
{
	$(".clearCls").html("");
	$("#surveyMonitoringConstituencyId,#surveyMonitoringBoothId,#userTrackingConstituencyId,#constituencyId").val(0);	
	$("#boothId").find("option").remove();
	if(id == "dataCollectorTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").hide();
		$("#verifierReportId").show();
		$('#boothId').multiselect('refresh');
	}

	else if(id == "verifierReportTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").hide();
		$("#verifierReportId").show();
		$('#boothId').multiselect('refresh');
	}

	else if(id == "thirdPartyReportTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").hide();
		$("#verifierReportId").show();
		$('#boothId').multiselect('refresh');
	}
	else if(id == "userTrackingReportTab")
	{
		$("#verifierReportId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").hide();
		$("#surveyUserTrackingId").show();
	}
	else if(id == "comparisonReportTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#verifierReportId").hide();
		$("#stateWiseReportId").hide();
		$("#comparisonReportId").show();
	}
	
	else if(id == "stateWiseReportTab")
	{
		$("#surveyUserTrackingId").hide();
		$("#verifierReportId").hide();
		$("#comparisonReportId").hide();
		$("#stateWiseReportId").show();
	}

}
var userTypeVal;
$(".highlight").click(function()
{
	$('#titleId').html("");
	$(".highlight").removeClass("selected");
	var val= $(this).attr("value");
	
	if(val == 1){
		$('#titleId').html("Data Collector Report");		
		$("#dataCollectorTab").addClass("selected");
		userTypeVal = val;
	}
	else if(val == 2){
		$('#titleId').html("Verifier Report");
		$("#verifierReportTab").addClass("selected");
		userTypeVal=val;
	}
	
	else if(val == 3){
		$('#titleId').html("Third Party Report");
		$("#thirdPartyReportTab").addClass("selected");
		userTypeVal= val;
	}
	else
	{
		$(".highlight").removeClass("selected");
		$(this).addClass("selected");
	}
})
showHideReportTabs('stateWiseReportTab');
</script>
</body>
</html>