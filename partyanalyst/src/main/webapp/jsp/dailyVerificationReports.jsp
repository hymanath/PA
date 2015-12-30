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
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/dailyVerificationReports.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

		<style>
		#trigger{
  font-size: 12px !important;
    height: 23px;
    line-height: 15px;
    padding: 3px;
}
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
			
			.datepicker{
				cursor:text !important;
				
			}
			
			.bigPictureCls{
				height:128px !important;
			}
			
		</style>	
	
  </head>
  
  <body>
  <script>
 
var sRegionId = 0;
var internalRegionId = 0;
jQuery( document ).ready(function( $ ) {
  $('#boothId').multiselect({
	  noneSelectedText:"Select Booth(s)"});
});
$( document ).ready(function() {
/* $('#boothId').multiselect({
	  noneSelectedText:"Select Booth(s)"}); */
	$('.datepicker').datepicker({dateFormat: 'dd-mm-yy',minDate: '14-07-2014',maxDate: new Date()}); 
	$(".toDatepicker").datepicker("setDate", new Date());
	/*$(".fromDatepicker").datepicker("setDate", '14-07-2014');*/
	
	$("#dailyDateFromDate").datepicker("setDate", new Date());
	$("#teamDetailsFromDate").datepicker("setDate", new Date());	
	$("#internalVerificationFromDate").datepicker("setDate", '14-07-2014');
	$("#qcVerificationFromDate").datepicker("setDate", '14-07-2014');
	
	getDataAvalaiableConstituencyes();
	getThirdAvaliableConstituencyes();
});
  </script> 
		<div class="container">
		<div class="row">
			<div class="span12 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a class="highlight selected" id="dashboardReportTab" onclick="showHideReportTabs(this.id);"> Dashboard </a></li>
					<li><a class="highlight" id="verificationReportTab" onclick="showHideReportTabs(this.id);"> Constituency Report</a></li>
					<li><a class="highlight" id="boothWiseReportsTab" onclick="showHideReportTabs(this.id);">  Booth Wise Report</a></li>
					<li><a class="highlight" id="thirdpPartyReportTab" onclick="showHideReportTabs(this.id);">QC Report</a></li>
					<li><a class="highlight" id="userTrackingReportTab" onclick="showHideReportTabs(this.id);"> User Tracking Report</a></li>
					<!--<li><a class="highlight selected" id="stateWiseReportTab" onclick="showHideReportTabs(this.id);"> State Wise Report </a></li>-->
					<!--<li><a class="highlight" id="dataCollectorTab" value="1" onclick="showHideReportTabs(this.id);"> Data Collector Report </a></li>-->
					<!--<li><a class="highlight" id="verificationReportTab" onclick="showHideReportTabs(this.id);"> Verification Report</a></li>-->
					
					

				</ul>
			</div>
		</div>
  
	
  
  
  <div class="">
  
 <!----- CTP DASHBOARD START  -->
		<div class="row" id="dashBoardDiv">	
			<div class="">
			<div class="span12">
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Caste Collection Dashboard <span id="totalState" style="display:none" class="pull-right btn-group   pull-right"><a onClick="getEntaieSelectionDetails(0,this.id)" id="all" class="btn btn-mini btn-success entaieSelection">ALL</a>|<a  onClick="getEntaieSelectionDetails(1,this.id)" id="ap" class="btn btn-mini entaieSelection ">AP</a>|<a  onClick="getEntaieSelectionDetails(2,this.id)" id="ts" class="btn btn-mini entaieSelection">TS</a></span></h4>						
						
						
							<div class="row-fluid m_top10">
							<!----Data Verification (right)---->
							<div class="span6 wiget-yellow" style="height: 282px;">	
								<h4 class="text-right">Today Data Collection Details</h4>
									<div class="text-right"> <span class="pull-right btn-group   pull-right"><a onClick="getTeamCollectedDetailsSummaryReport(0,this.id)" id="allCollecetdId" class="btn btn-mini todayCollectdDetailsCls btn-success">ALL</a>|<a  onClick="getTeamCollectedDetailsSummaryReport(1,this.id)" id="apCollecetdId" class="btn btn-mini todayCollectdDetailsCls btn-success">AP</a>|<a  onClick="getTeamCollectedDetailsSummaryReport(2,this.id)" id="tsCollecetdId" class="btn btn-mini todayCollectdDetailsCls">TS</a></span> </div>
								<input type="hidden" value="0" id="dailyDateRangeHidden"></input>
								
								<div class="row-fluid span10" style="font-weight:bold;margin-top: -20px;">

									<div class="span4">
										<span> From Date : </span>
										<input type="text" readonly=true class="span10 offset2 datepicker fromDatepicker" placeholder="From Date" id="dailyDateFromDate">
									</div>

									<div class="span4">									
										<span> To Date : </span>
										<input type="text" readonly=true  class="span10  datepicker toDatepicker" placeholder="To Date" id="dailtDateToDate">
									</div>
									
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getTodayTeamDetailsbtDeates();" style="margin-top: 15px;">Submit</a>
									</div>	
									
								</div>
							<!--	
								<div class="row-fluid span10">
									<div class="input-append span4">
										<input type="text" readonly=true class="span10 offset2 datepicker fromDatepicker" placeholder="From Date" id="dailyDateFromDate">
									</div>
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10  datepicker toDatepicker" placeholder="To Date" id="dailtDateToDate">
									</div>	
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getTodayTeamDetailsbtDeates();" style="margin-left: -15px; margin-top: -5px;">Submit</a>
									</div>									
								</div>
							-->	
								<!--<div class="row-fluid text-center">
									<P CLASS="label">Today: Date</p>
								</div>-->
								
								<div class="row-fluid">
									<div class="well well-small span4  text-center">
										<a onClick="getConstituencyWiseTeamCollecetdDetails(1)"><h4 style = "cursor: pointer;" id="dcDetailsId"><img src="./images/icons/search.gif" ></img></h4></a>
										<p class="">Data Collection </br>Completed</p>										
									</div>
									<div class="well well-small span4 text-center">
										<a onClick="getConstituencyWiseTeamCollecetdDetails(4)"><h4 style = "cursor: pointer;" id="dvDetailsId"><img src="./images/icons/search.gif" ></img></h4></a>
										<p>Data Verification </br>Completed</p>
									</div>
									<div class="well well-small span4 text-center">
										<a onClick="getConstituencyWiseTeamCollecetdDetails(10)"><h4 style = "cursor: pointer;" id="qcDetailsId"><img src="./images/icons/search.gif" ></img></h4></a>
										<p>Quality Check </br>Completed</p>
									</div>							
								</div>
								
							</div>	<!----Data Verification---->	
							<!-------->
							<div class="span6 wiget-yellow"  style="height: 282px;">	
								<h4 class="text-right">Today Team Details  </h4>
								<div class="text-right"><span class="pull-right btn-group   pull-right"><a  class="btn btn-mini todayTeamDetailsCls" id="allTeamId" onClick="getTodayTeamDetails(0,this.id)">ALL</a><a onClick="getTodayTeamDetails(1,this.id)" id= "apTeamId" class="btn btn-mini todayTeamDetailsCls">AP</a>|<a onClick="getTodayTeamDetails(2,this.id)" class="btn btn-mini todayTeamDetailsCls" id="tsTeamId">TS</a></span>
								</div>
								<input type="hidden" value ="0" id="todatTeamHidden"></input>
							
							<div class="row-fluid span10" style="font-weight:bold;margin-top: -20px;">

									<div class="span4">
										<span> From Date : </span>
										<input type="text" readonly=true  class="span10 offset2 datepicker fromDatepicker" placeholder="From Date" id="teamDetailsFromDate">
									</div>

									<div class="span4">									
										<span> To Date : </span>
										<input type="text" readonly=true  class="span10 datepicker toDatepicker" placeholder="To Date" id="teamDetailsToDate">
									</div>
									
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getTodayTeamDetailsBtDates();" style="margin-top: 15px;">Submit</a>
									</div>	
									
								</div>
								
						<!--
							<div class="row-fluid span10">
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10 offset2 datepicker fromDatepicker" placeholder="From Date" id="teamDetailsFromDate">
										<!--<span class="add-on"><i class="icon-calendar"></i></span>-->
						<!--			</div>
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10 datepicker toDatepicker" placeholder="To Date" id="teamDetailsToDate">
										<!--<span class="add-on"><i class="icon-calendar"></i></span>-->
						<!--			</div>	
									
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getTodayTeamDetailsBtDates();" style="margin-left: -15px; margin-top: -5px;">Submit</a>
									</div>
								</div>
						-->		
								<div class="row-fluid">
									<div class="well well-small span4  text-center">
										<h4  id="dcMembers" ><img src="./images/icons/search.gif" ></img></h4>
										<p class="">Data </br> Collectors</p>										
									</div>
									<div class="well well-small span4 text-center">
										<h4  id="dvMembers" ><img src="./images/icons/search.gif" ></img></h4>
										<p>Data </br> Verifiers</p>
									</div>
									<div class="well well-small span4 text-center">
										<h4  id="qcMembers" ><img src="./images/icons/search.gif" ></img></h4></a>
										<p>Quality </br> Check</p>
									</div>
								</div>							
							</div>	<!-------->
							
							
						</div>
						
						<!-----Data Collection & Verification Main Div---->
						<div class="row-fluid m_top20" >
							<!----Data Collection (left)------>
							<div class="span12 wiget-yellow" style="height: 550px;">	
								<h4 class="text-right">Big Picture
								</h4>
								<div class="row-fluid">
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="totConsti"><img src="./images/icons/search.gif" ></h4>
										<p>Total </br> Constituencies</p>
									</div>
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="startedConsti"><img src="./images/icons/search.gif" ></h4>
										<p>Started </br> Constituencies</p>
									</div>
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="processConsti"><img src="./images/icons/search.gif" ></h4>
										<p>Processing </br>Constituencies</p>
									</div>
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="CompletedConsti"><img src="./images/icons/search.gif" ></h4>
										<p>Completed </br>Constituencies</p>
									</div>							

									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="wmConstituencyes"><img src="./images/icons/search.gif" ></img></h4>
										<p>Verification Completed </br> Constituencies</p>
									</div>	
									
									<!--<div class="well well-small span2 text-center">
										<h4 id="TotalcasteCount"><img src="./images/icons/search.gif" ></img></h4>
										<p>Total Caste <br> Collected Count</p>
									</div>-->
										
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="dcConstituencyPerc"><img src="./images/icons/search.gif" ></img></h4>
										<p>Data Collection <br> Completed </br> Constituencies %</p>
									</div>	
									
								</div>
								<div class="row-fluid">
								
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="TodaycasteCount"><img src="./images/icons/search.gif" ></img></h4>
										<p>Today Caste </br>  Collected </br> Count </p>
									</div>	
									
									<div class="well well-small span2  text-center bigPictureCls">
										<h4 id="totalVoters"><img src="./images/icons/search.gif" ></img></h4>
										<p>Target </br> Voters</p>
									</div>
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="dcVoters"><img src="./images/icons/search.gif" ></img></h4>
										<p>Data Collection <br> Completed </br> Voters</p>
									</div> 
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="wmVoters"><img src="./images/icons/search.gif" ></img></h4>
										<p>Verification Completed </br> Voters</p>
									</div>
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="dcVotersPerc"><img src="./images/icons/search.gif" ></img></h4>
										<p>Data Collection <br> Completed </br> Voters %</p>
									</div> 
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="totalBooths"><img src="./images/icons/search.gif" ></img></h4>
										<p>Target </br>Booths</p>
									</div>									
									
									
									
									<!--<div class="well well-small span2 text-center">
										<h4 id="totalConstituencyes"><img src="./images/icons/search.gif" ></img></h4>
										<p>Target </br>Constituencies</p>
									</div>		-->					
									<!--<div class="well well-small span2 text-center">
										<h4 id="dcConstituecyes"><img src="./images/icons/search.gif" ></img></h4>
										<p>Data Collection <br> Completed Constituencies</p>
									</div>	-->	
																	
								</div>
								<div class="row-fluid">
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="dcBooths"><img src="./images/icons/search.gif" ></img></h4>
										<p>Data Collection <br> Completed </br> Booths</p>
									</div>
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="wmBooths"><img src="./images/icons/search.gif" ></img></h4>
										<p>Verification Completed </br>  Booths</p>
									</div>
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="dcBoothsPerc"><img src="./images/icons/search.gif" ></img></h4>
										<p>Data Collection <br> Completed </br> Booths %</p>
									</div>
									
																
									
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="qcVoters"><img src="./images/icons/search.gif" ></img></h4>
										<p>Quality Check</br> Completed </br> Voters</p>
									</div>
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="qcBooths"><img src="./images/icons/search.gif" ></img></h4>
										<p>Quality Check</br>Completed </br>Booths</p>
									</div>
									<div class="well well-small span2 text-center bigPictureCls">
										<h4 id="qcConstituencyes"><img src="./images/icons/search.gif" ></img></h4>
										<p>Quality Check</br>Completed Constituencies</p>
									</div>							
								</div>
								
								
								<!--<div class="row-fluid">
								<div class="well well-small span10 offset1  ">									
									Planned Vs Actual Status  <span class="pull-right badge">5214</span>
								</div>						
								</div>-->
							</div><!----Data Collection End------>
							
						
						</div><!-----Data Collection & Verification Main Div---->	
						
					
						
						<div class="row-fluid m_top20">
						
							<div class="span6 wiget-yellow">	
								<h4 class="text-right">Internal Verification
								
								</h4>
								<div class="text-right"> <span class="btn-group  pull-right"><a href="javascript:{setInternalVerificationRegionLevel(0,'allRegionsId1')}" class="btn btn-mini selectedCls1 btn-success" id="allRegionsId1">ALL</a><a href="javascript:{setInternalVerificationRegionLevel(2,'apRegionsId1')}" class="btn btn-mini selectedCls1" id="apRegionsId1">AP</a><a href="javascript:{setInternalVerificationRegionLevel(1,'tsRegionsId1')}" class="btn btn-mini selectedCls1" id="tsRegionsId1">TS</a></span>
								</div>
								
								
							<div class="row-fluid span10" style="font-weight:bold;margin-top: -20px;">

									<div class="span4">
										<span> From Date : </span>
										<input type="text" readonly=true  class="span10 datepicker offset2 fromDatepicker" placeholder="From Date" id="internalVerificationFromDate">
									</div>

									<div class="span4">									
										<span> To Date : </span>
										<input type="text" readonly=true  class="span10 datepicker toDatepicker" placeholder="To Date" id="internalVerificationToDate">
									</div>
									
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getInternalVerificationSummaryBtDates();" style="margin-top: 15px;">Submit</a>
									</div>	
									
								</div>
								
								
							<!--	
								<div class="row-fluid span10">
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10 datepicker offset2 fromDatepicker" placeholder="From Date" id="internalVerificationFromDate">
									</div>
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10 datepicker toDatepicker" placeholder="To Date" id="internalVerificationToDate">
									</div>
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getInternalVerificationSummaryBtDates()" style="margin-left: -15px; margin-top: -5px;">Submit</a>
									</div>									
								</div>
							-->	
								<div class="row-fluid">
									<div class="well well-small span4  text-center">
										<h4 id="verifiedCount"><img src="./images/icons/search.gif" ></img></h4>
										<p class="">Verified <br/> Records</p>										
									</div>
									<div class="well well-small span4 text-center">
										<h4 id="matchedCount"><img src="./images/icons/search.gif" ></img></h4>
										<p>Valid <br/> Records</p>
									</div>
									<div class="well well-small span4 text-center">
										<h4 id="unMatchedCount"><img src="./images/icons/search.gif" ></img></h4>
										<p>Corrected <br/> Records</p>
									</div>							
								</div>
								<div class="row-fluid">
									<div class="well well-small span12">									
										Redo Records <span class="pull-right badge"><span id="redoBoothDetails">0</span></span>
									</div>						
								</div>								
							</div>	<!-------->
							<!-------->
							<div class="span6 wiget-yellow">	
								<h4 class="text-right ">QC Verification </h4>
								<div class="text-right"><span class="pull-right btn-group  pull-right"><a  class="btn btn-mini qcCollectedDetailsCls" id="allQcCollecetdId" onClick="getQcVerificationSummaryReport(0,this.id)">ALL</a><a onClick="getQcVerificationSummaryReport(1,this.id)" id= "apQcColledcetdId" class="btn btn-mini qcCollectedDetailsCls">AP</a>|<a onClick="getQcVerificationSummaryReport(2,this.id)" class="btn btn-mini qcCollectedDetailsCls" id="tsQcCollectedId">TS</a></span></div>
								<input type="hidden" value="0" id="qcVerificationHidden"></input>
								
								
							<div class="row-fluid span10" style="font-weight:bold;margin-top: -20px;">

									<div class="span4">
										<span> From Date : </span>
										<input type="text" readonly=true  class="span10 offset2 datepicker fromDatepicker" placeholder="From Date" id="qcVerificationFromDate">
									</div>

									<div class="span4">									
										<span> To Date : </span>
										<input type="text" readonly=true  class="span10 datepicker toDatepicker" placeholder="To Date" id="qcVerificationToDate">
									</div>
									
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getQcVerificationSummaryReportbtDeates();" style="margin-top: 15px;">Submit</a>
									</div>	
									
								</div>
								
								
								<!-- 
								<div class="row-fluid span10">
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10 offset2 datepicker fromDatepicker" placeholder="From Date" id="qcVerificationFromDate">
									</div>
									<div class="input-append span4">
										<input type="text" readonly=true  class="span10 datepicker toDatepicker" placeholder="To Date" id="qcVerificationToDate">
									</div>	
									<div class="span4 m_top10">
										<a class="btn btn-mini" onClick="getQcVerificationSummaryReportbtDeates();" style="margin-left: -15px; margin-top: -5px;">Submit</a>
									</div>										
								</div>
								-->
								
								<div class="row-fluid">
									<div class="well well-small span4  text-center">
										<h4 id="verifiedRecords"><img src="./images/icons/search.gif" ></img></h4>
										<p class="">Records <br/> Verified</p>										
									</div>
									<div class="well well-small span4 text-center">
										<h4  id="qcMatched" ><img src="./images/icons/search.gif" ></img></h4>
										<p>Matched <br/> Identified</p>
									</div>
									<div class="well well-small span4 text-center">
										<h4 id="qcUnMatched" ><img src="./images/icons/search.gif" ></img></h4></a>
										<p>Records <br/> Collected</p>
									</div>
								</div>							
							</div>	<!-------->
							
						</div>
						<div class="row-fluid " >
				
							<div class="span12  m_top20  widgetservey" id="buldingConstituenciesDivId"></div>
							 <img id="popupImgid1" src="./images/icons/search.gif" alt="Processing Image" style="display:block;margin-left:410px"/>	 
						</div>
						<div class="row-fluid ">
							<div class="span12 m_top20 widgetservey">
								<h4>Districts Started</h4>
									<ul class="inline unstyled Constituency-name-nav" id="districtStarted">	
										SURVEY NOT STARTED IN ANY DISTRICT
									</ul>
							</div>
						</div>
						
						<div class="row-fluid ">
							<div class="span12 m_top20 widgetservey">
								<h4>Districts Completed </h4>
								<ul class="inline unstyled Constituency-name-nav" id="districtCompleted">	
									SURVEY NOT COMPLETED IN ANY DISTRICT
								</ul>
							</div>
						</div>
					</div>
				</div>	<!----Constituency details main Div End---->
				
			</div>
		</div>
		<div id="constituencyWiseQcTable"></div>
		<div id="boothWiseQcTable"></div>
		</div>
		
  <!----- CTP DASHBOARD END  -->
    <!----- CTP Verification report START  -->
		<div class="row" id="verificationReportDiv">
		<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4>Constituency Wise Summary Report</h4>
							<img id="verificationImg" src="./images/Loading-data.gif" alt="Processing Image" class="offset5" 
							style="width:70px;height:60px;display:none;"/>
							<div class="row">
						<div id="verifiedDiv" class="span12"></div>
						</div>
			</div>
			</div>
          </div>   
	    </div>
  <!----- CTP Verification report END  -->

		<div class="row" id="verifierReportId" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="titleId"></h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
								<div class="offset1">
									<div class="row-fluid">
										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font> : 
												<select id="constituencyId" onChange="getBoothsDetailsByConstituencyId(this.value,'boothId'),getCasteCollectedDatesByConstituencyId(this.value);"><option value ="0">Select Constituency</option></select>
										</div>
										<div class="span3">
											Select Booth <font class="requiredFont">*</font> : 
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
										<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
								<img id="dateAjaxImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReportByConstituencyIdAndUserType()">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
                             <div id="dayWiseReportDiv1" class="clearCls" style="overflow-x:scroll;"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="mainajaximg" style="width:70px;height:60px;display:none;"/>
					</div>
				</div>
			</div>	
				
			</div>
		</div>
		
		<div class="row" id="thirdpPartyReport" style="display:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>QC READY FOR REVIEW BOOTHS</h4>
								<img src='images/Loading-data.gif' class="offset5"  id="thirdPartyAjaxImg" style="width:70px;height:60px;display:none;"/>
								<div class="row">
									<div id="errorDivForThirdParty" class="span8 offset4"></div>
								</div>
								
								<div id="constSummary"></div>
								<div id="constSummarySub"></div>
								<div id="boothsSummary"></div>
								<div id="commentsDiv1"></div>
								
								<div class="row offset4" style="margin-top:35px;">								
									<div class="row-fluid">										
										<div class="span3">
											 Constituency <font class="requiredFont">*</font></div>
											<div class="span4">
											<select id="constituencyForThirdParty" onChange="getBoothsDetailsByConstituencyId(this.value,'boothId'),getCasteCollectedDatesByConstituencyId(this.value);"><option value ="0">Select Constituency</option></select>
											
										</div>												
									</div>
								</div>
								<div class="row text-center m_top10"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getFinalReportWithTP()">SUBMIT</button></div>
								<img src='images/Loading-data.gif' class="offset5"  id="thirdPartyAjax" style="width:70px;height:60px;display:none;"/>
								<div id="FinalReportWithTPId"></div></br></br>
								<div id="CommentsDiv"></div>								
					</div>						
                </div>
			</div>
		</div>
		
		
		<div class="row" id="boothWiseDtlsId" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="titleId"></h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
								<div class="offset3">
									<div class="row-fluid">
										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font> : 
											<select id="bconstituencyId"><option value="0">Select Constituency</option></select>
										</div>
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getBoothWiseDetails()">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
                           <div id="boothWiseStatusDtls" class="clearCls" ></div>
							<img src='images/Loading-data.gif' class="offset5"  id="bmainajaximg" style="width:70px;height:60px;display:none;"/>
					</div>
				</div>
			</div>	
				
			</div>
		</div>
		<div  style="display:none;margin-top:20px;" id="surveyUserTrackingId">
		<jsp:include page="surveyUserTracking.jsp" flush="true"/>	
	</div>

</div>
</div>
<script>
var finalRes = null;
var userTypeVal;
showHideReportTabs('dashboardReportTab');
getTotalCasteCounts();

function getVerifierReportCounts()
{
	$("#verificationImg").show();
var jobj = {
			     task:"verifierReport"
	  	       }
		$.ajax({
	          type:'GET',
	          url: 'getVerifierReportAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
	    }).done(function(result){
		    $("#verificationImg").hide();
		    buildVerifierReport(result);
		});
	
}
function buildVerifierReport(result)
{

 var str ='';
 str+='<table class=" m_top20 table table-bordered table-hover table-striped">';
 str+='<thead class="alert alert-success">';
 str+='<th>Constituency</th>';
 str+='<th>Total Voters</th>';
 str+='<th>Total Booths</th>';
 str+='<th>WM-DC Verified Booths</th>';
 str+='<th>WM-DC Verified Voters</th>';
 str+='<th>DV Total Booths</th>';
  str+='</thead>';
 str+='<tbody>';
 for(var i in result)
 {
 str+='<tr>';
str+='<td>'+result[i].name+'</td>';
str+='<td>'+result[i].totalVoters+'</td>';
str+='<td>'+result[i].totalBooths+'</td>';
str+='<td>'+result[i].dcVerifiedBooths+'</td>';
str+='<td>'+result[i].dcTotalVoters+'</td>';
str+='<td>'+result[i].dvTotalBooths+'</td>';
 str+='</tr>';
 }
 str+='</tbody>';
 str+='</table>';
 $("#verifiedDiv").html(str);
}
$(".highlight").click(function()
{
	$('#titleId').html("");
	$(".highlight").removeClass("selected");
	var val= $(this).attr("value");
	
	if(val == 1){
		$('#titleId').html("Data Collector Report");		
		$("#dataCollectorTab").addClass("selected");
		userTypeVal = 1;
	}
	else if(val == 2){
		$('#verifiertitleId').html("Verifier Report");
		$("#verifierReportTab").addClass("selected");
		userTypeVal=4;
	}
	
	else if(val == 3){
		$('#wmtitleId').html("Web Monitoring Report ");
		//$("#thirdPartyReportTab").addClass("selected");
		$("#wmReportTab").addClass("selected");
				$("#wmReportDiv").show();
		userTypeVal= val;
	}
	else
	{
		$(".highlight").removeClass("selected");
		$(this).addClass("selected");
	}
})


getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies();
function setRegionLevel(regionId,divId)
{
	$('.selectedCls').removeClass('btn-success');
	$('#'+divId+'').addClass('btn-success');

  sRegionId = regionId;
}
function openConstituencyWiseWindow(userTypeId,searchType)
{
	var fromDate = $('#teamDetailsFromDate').val();
	var toDate   = $('#teamDetailsToDate').val();
	if(fromDate == '')
	{
		fromDate = 'null';
	}
	if(toDate == '')
	{
		toDate = 'null';
	}
	var startDate = "";
	var endDate = "";
	window.open("surveyConstituencieOverview.action?constituencyId=1&startDate="+startDate+"&endDate="+endDate+"&regionId="+sRegionId+"&userTypeId="+userTypeId+"&task="+searchType+"", "_blank");
}

function setInternalVerificationRegionLevel(iRegionId,divId)
{
	$('#verifiedCount').html('<img src="./images/icons/search.gif" ></img>');
	$('#matchedCount').html('<img src="./images/icons/search.gif" ></img>');
	$('#unMatchedCount').html('<img src="./images/icons/search.gif" ></img>');
	internalRegionId = iRegionId;
	$('.selectedCls1').removeClass('btn-success');
	$('#'+divId+'').addClass('btn-success');
	getInternalVerificationSummary(internalRegionId,divId);
}
</script>
</body>
</html>