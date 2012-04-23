<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Tracking Analysis</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/problemManagement/problemManagement.js"></script>

<style type="text/css">
.ui-dialog .ui-dialog-content{
	font-size:12px;
}
.visTable td, .performanceTab td{	
    text-align:center;
    background-color:AliceBlue;
	width:150px;
	padding:5px 0px 5px 0px;
}
.visTable, .performanceTab, .userFlowTable{
    border-collapse:separate;
	margin-left:auto;
	margin-right:auto;
}
.visTable th{
	text-align:center;
	width:330px;
	background-color:PowderBlue;
	font-weight:bold;
}
.performanceTab th{
	background-color:#DADCF5;
	text-align:center;
	font-weight:bold;
}
.userFlowTable td{
	text-align:center;   
	padding:5px 0px 5px 0px;
}
#userTrackingMainDiv{
	margin-left: auto;
	margin-right: auto;
	float: none;
	width:995px;
}
#headerImageCenterDiv{
	 background-image: url("images/icons/constituencyManagement/header_body_blue.png");
	 color:#FFFFFF;
	 text-align: center;
	 width: 240px;
	 height:30px;
}
.f2{
	border:2px solid #CFD6DF;
	margin-left: 13px;
	margin-top: 14px;
	width: 950px;
	margin-bottom: 20px;
}
#headerImageCenterSpan{
	top: 5px;
	position: relative;
}
#visitedUserSearch_head{
	background:#EEF4F6;
}
.tinyDateCal {
    position: absolute;
}
.VDtableClass th {
	background:#EEEEEE;
	font-weight:bold;
}

.VDtableClass tr:nth-child(2n+1) {
    background:#EDF5FF;
}
.VDtableClass{
 border:1px solid #CCCCCC;
 padding: 1px;
}
.headingStyle{
  background: none repeat scroll 0 0 #21B2ED;
  color: #FFF;
  font-weight: bold;
  border-radius: 2px;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 4px;
  padding-bottom: 4px;
  background:#21B2ED;
}
#headingStyle{
background: none repeat scroll 0 0 #06ABEA;
    border-left-width: 3px;
    color: #FFFFFF;
    font-weight: bold;
    padding: 4px 10px;
    border-radius: 2px;
    font-size: 14px;
	 width: 365px;
    text-align: center;
	
}
</style>
<script type="text/javascript">
var userPageFlowList = null;
$(document).ready(function(){
	getUserDetails('getUniqueVisitorsAction.action?');		
});
function showDates(){
	$("#errMsgDiv").html("");
	$("#headingStyle").css("display", "none");
	$("#visitorsDetailsDiv").css("display", "none");

	if(!($("#betweendates").is(':checked'))){
		$("#datesDiv").css("height","0px");
		$("#searchDiv").css("display", "none");
		getUserDetails('getUniqueVisitorsAction.action?');
	}
	else{
		$("#searchDiv").css("display", "block");
	}
		
	if(document.getElementById("betweendates").checked == true){
		$("#datesDiv").css("height","50px");
		document.getElementById("showDates").style.display = "block";
	}
	else
		document.getElementById("showDates").style.display = "none";
}
 function showAjaxImg(divId){
	 document.getElementById(divId).style.display = 'inline-block';
 }

 function hideAjaxImg(divId){
	 document.getElementById(divId).style.display = 'none';
 }
function getUserDetails(actionUrl){
	var task='';
	var fromDate='';
	var toDate='';
	if($("#today").is(':checked')){
		task='byTodayDate';
	}
	else if($("#thisweek").is(':checked')){
		task='byThisWeek';
	}
	else if($("#thismonth").is(':checked')){
		task='byThisMonth';
	}
	else if($("#betweendates").is(':checked')){
		task='betweendates';
		fromDate = $("#fromDate").val();
		toDate =  $("#toDate").val();
	}
	var jsObj={
		task:task,
		fromDate: fromDate,
		toDate: toDate,
		url:actionUrl
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
    var url = actionUrl+rparam;	
    callAjax(jsObj,url);
}
 
function callAjax(jsObj, url){
	var myResults;
	var callback = {			
		success : function( o ) {
			hideAjaxImg('searchAjaxImgSpan');
			try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
				if(jsObj.task == "byTodayDate"){
					displayUserDetails(myResults);
				}
				else if(jsObj.task == "byThisWeek"){
					displayUserDetails(myResults);
				}
				else if(jsObj.task == "byThisMonth"){
					displayUserDetails(myResults);
				}
				else if(jsObj.task == "betweendates"){
					displayUserDetails(myResults);
				}
				else if(jsObj.task == "betweendatesVisitorsDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "todayVisitorsDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisMonthVisitorsDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisWeekVisitorsDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "todayUserDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisWeekUserDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisMonthUserDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "betweendatesUserDetails"){
					userPageFlowList = myResults;
					showBetweendatesVisitorsDetails(myResults);
				}
			}
			catch(e){
				alert("Invalid JSON result" + e);  
			}
		},
		scope:this,
		failure : function( o ) {
     		//alert( "Failed to load result" + o.status + " " + o.statusText);
        }
    };
	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function displayUserDetails(myResults){
	for(var i=0;i<4;i++){
		$("#totVis"+i).html(myResults[i].uniqueVisitors);
		$("#totPag"+i).html(myResults[i].totalNoOfPagesAccessed);
		$("#avgPag"+i).html(myResults[i].avgNoOfPagesAccessed);
		$("#totTime"+i).html(myResults[i].totalTimeSpent);
		$("#avgTime"+i).html(myResults[i].avgTimeSpent);		
	}
	displaySiteAnalytics(myResults[4]);
}
function getMoreVisitorDetails()
{
	$("#visitorsDetailsDiv").css("display", "block");

  if(document.getElementById("today").checked == true)
	{
	  getVisitorDetails("todayVisitorsDetails","","");

	  headingDetails("Today's Total Visitors Details"); 
	 }
  if(document.getElementById("thisweek").checked == true)
	{
	  getVisitorDetails("thisWeekVisitorsDetails","","");

		headingDetails("Total Visitors Details of this week");
	  
	}
  if(document.getElementById("thismonth").checked == true)
	{
	  getVisitorDetails("thisMonthVisitorsDetails","","");

	  headingDetails("Total Visitors Details of this month");
	}
  if(document.getElementById("betweendates").checked == true)
	{
    if($("#fromDate").val()!="" && $("#toDate").val()!=""){
		$("#errMsgDiv").html("");
	  var fromDate = "";
	  var toDate = "";
	  fromDate = document.getElementById("fromDate").value;
	  toDate = document.getElementById("toDate").value;
	  getVisitorDetails("betweendatesVisitorsDetails",fromDate,toDate);
	  headingDetails('Total Visitor Details between '+fromDate+' and '+toDate);
	}
	else{
		$("#errMsgDiv").html("<center><font style='color:red; font-size:12px;'>Please Select From Date and To Date</font><center>");
	}
	}
}
function checkDateFields(){
	if($("#fromDate").val()!="" && $("#toDate").val()!=""){
  		$("#errMsgDiv").html("");
		showAjaxImg('searchAjaxImgSpan');
		getUserDetails('getUniqueVisitorsAction.action?');
	}
	else{
		$("#errMsgDiv").html("<center><font style='color:red; font-size:12px;'>Please Select From Date and To Date</font><center>");
	}	
}
function getVisitorDetails(task , fromDate , toDate)
{
   var timeST = new Date().getTime();
   var jsObj = 
	   {
		fromDate : fromDate,
		toDate   : toDate,
		task     : task
	   }
	var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getVisitorsMoreDetailsAction.action?"+rparam;
	callAjax(jsObj, url);	
}

function getMoreDetails(userType)
{
	$("#visitorsDetailsDiv").css("display", "block");
 if(document.getElementById("today").checked == true)
	{
	  getUserMoreDetails("todayUserDetails","","",userType);
	  if(userType == null)
	    headingDetails("Today's Guest Visitors Details");
	  if(userType == "FREE_USER")
	    headingDetails("Today's Free Users Details");
	  if(userType == "PARTY_ANALYST_USER")
		headingDetails("Today's Customers Details");
	}
  if(document.getElementById("thisweek").checked == true)
	{
	  getUserMoreDetails("thisWeekUserDetails","","",userType);
	  if(userType == null)
	     headingDetails("Guest Visitors Details of this week");
	  if(userType == "FREE_USER")
         headingDetails("Free Users Details of this week");
	  if(userType == "PARTY_ANALYST_USER")
		  headingDetails("Customers Details of this week");

	}
  if(document.getElementById("thismonth").checked == true)
	{
	  getUserMoreDetails("thisMonthUserDetails","","",userType);
	  if(userType == null)
		  headingDetails("Guest Visitors Details of this month");
	  if(userType == "FREE_USER")
		  headingDetails("Free Users Details of this month");
	  if(userType == "PARTY_ANALYST_USER")
		  headingDetails("Customers Details of this month");
	}
  if(document.getElementById("betweendates").checked == true)
	{
	  if($("#fromDate").val()!="" && $("#toDate").val()!=""){
  			$("#errMsgDiv").html("");
		  var fromDate = "";
		  var toDate = "";
		  fromDate = document.getElementById("fromDate").value;
		  toDate = document.getElementById("toDate").value;
		  getUserMoreDetails("betweendatesUserDetails",fromDate,toDate,userType);
		  if(userType == null)
			  headingDetails('Guest Visitors Details between '+fromDate+' and '+toDate);
		  if(userType == 'FREE_USER')
			  headingDetails('Free Users Details between '+fromDate+' and '+toDate);
		  if(userType == 'PARTY_ANALYST_USER')
			  headingDetails('Customers Details between '+fromDate+' and '+toDate);
	  }
	  else{
		$("#errMsgDiv").html("<center><font style='color:red; font-size:12px;'>Please Select From Date and To Date</font><center>");
	  }
		
	}
}

function getUserMoreDetails(task , fromDate , toDate , userType)
{
var timeST = new Date().getTime();
   var jsObj = 
	   {
		fromDate : fromDate,
		toDate   : toDate,
		userType : userType,
		task     : task
	   }
	var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getUserMoreDetailsAction.action?"+rparam;
	callAjax(jsObj, url);	
}

function showBetweendatesVisitorsDetails(result)
{
if(result.length == 0)
{

document.getElementById("visitorsDetailsDiv").innerHTML = "No Records Found";
return;
}

var visitorsDetailsDiv = document.getElementById("visitorsDetailsDiv");
var str='';

str +='<table width="100%" class="VDtableClass">';
str +='<tr>';
if(result[0].remoteAddress != null)
str +='<th width="15%">Host Name</th>';

if(result[0].userName != null)
str +='<th width="15%">User Name</th>';

str +='<th width="18%">No.Of Pages</th>';
str +='<th width="15%">Time Spent</th>';
str +='<th width="26%">Landing Page</th>';
str +='<th width="26%">Exit Page</th>';
str +='</tr>';

for(var i=0; i<result.length; i++)
{
str +='<tr style="text-align:center;">';
if(result[i].remoteAddress != null)
  str +='<td  width="15%">'+result[i].remoteAddress+'</td>';

if(result[i].remoteAddress == null && result[i].userName != null)
str +='<td width="15%"><a href="javascript:{}" onclick="displayUserPageFlow('+i+')">'+result[i].userName+'</a></td>';

str +='<td  width="10%">'+result[i].noOfPages+'</td>';
str +='<td  width="15%">'+result[i].spentTime+'</td>';
str +='<td  width="40%">'+result[i].landingPage+'</td>';
str +='<td  width="40%">'+result[i].exitPage+'</td>';

str += '</tr>';
}
str +='</table>';

visitorsDetailsDiv.innerHTML = str;
}

function displayUserPageFlow(index){
	var userFlow;
	var title;
	for(var i=0;i<userPageFlowList.length;i++){
		if(i==index){
			userFlow=userPageFlowList[i].urlTimeVOList;
			var userName=userPageFlowList[i].userName;
			var loginDate=userFlow[0].loginDate;
			title="Page flow of "+userName+" on "+loginDate;
		}
	}
	var str='';
	str+='<table class="userFlowTable">';
	for(var i=0; i<userFlow.length;i++){
		str+='<tr>';
		str+='<td style="background-color:AliceBlue; width:350px;text-align:center;">'+userFlow[i].pageUrl+'</td>';
		str+='<td style="width:360px;text-align:left;"><strong>Time Spent: </strong>'+userFlow[i].timeSpent;
		if(userFlow[i].accessCount!=null){
			str+='<br/><b>(</b>Page accessed for <strong>'+userFlow[i].accessCount+'</strong> times, between <strong>'+userFlow[i].fromTime+'</strong> and <strong>'+userFlow[i].toTime+')</strong>';			
		}	
		str+='</td>';
		if(userFlow.length>1 && i!=userFlow.length-1){
			str+="</tr><tr><td style='text-align:center'><img src='images/constituencyPage/blue-down-arrow.png'></img></td></tr>";
		}
	}
	
	str+='</tr>';
	str+='</table>';
	$("#userPageFlowDivInner").html(str);
	$("#userPageFlowDivOuter").dialog({
		resizable:false,
		title:title,
		show:'slide',
		modal:true ,
		width:'auto',
		height:'auto'	
	});
}
function headingDetails(text)
{
document.getElementById("headingStyle").style.display = 'block';
document.getElementById("headingStyle").innerHTML = text;
}
function displaySiteAnalytics(result){
	var landingCtr=0;
	var exitCtr=0;
	var bounceCtr=0;

	for(var p in result.landingPageMap){
		landingCtr++;
	}
	for(var p in result.exitPageMap){
		exitCtr++;
	}
	for(var p in result.bouncePageMap){
		bounceCtr++;
	}

	if(landingCtr!=0){
		var str='';
		str+='<table class="performanceTab" id="landingPage">';
		str+='<tr>';
		str+='<th style="width:330px;">Landing Page</th>';
		str+='<th style="width:100px;">Lead Rate</th>';	
		str+='</tr>';
		for(var p in result.landingPageMap){
			str+='<tr><td>'+p+'</td><td>'+result.landingPageMap[p]+'</td></tr>';
		}
		str+='</table>';
		$("#landingPageDiv").html(str);
	}
	else
		$("#landingPageDiv").html("");
	
	if(exitCtr!=0){
		var str='';
		str+='<table class="performanceTab" id="exitPage">';
		str+='<tr>';
		str+='<th style="width:330px;">Exit Page</th>';
		str+='<th style="width:100px;">Exit Rate</th>';	
		str+='</tr>';
		for(var p in result.exitPageMap){
			str+='<tr><td>'+p+'</td><td>'+result.exitPageMap[p]+'</td></tr>';
		}
		str+='</table>';
		$("#exitPageDiv").html(str);
	}
	else
		$("#exitPageDiv").html("");

	if(bounceCtr!=0){
		var str='';
		str+='<table class="performanceTab" id="bouncePage">';
		str+='<tr>';
		str+='<th style="width:330px;">Bounce Page</th>';
		str+='<th style="width:100px;">Bounce Rate</th>';	
		str+='</tr>';
		for(var p in result.bouncePageMap){
			str+='<tr><td>'+p+'</td><td>'+result.bouncePageMap[p]+'</td></tr>';
		}
		str+='</table>';
		$("#bouncePageDiv").html(str);
	}
	else
		$("#bouncePageDiv").html("");
}
</script>
</head>
<body>
<div id="userTrackingMainDiv">
<div style="background:#FFFFFF;padding-top:2px;">
	<fieldset class="f2">
	<div style="margin: 12px 0px 15px 340px;">
		<table border="0" cellpadding="0" cellspacing="0">          
			<tr>
			   <td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
			   <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">User Tracking Analysis</span></div></td>
			   <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
			 </tr>
		</table>
	</div>
	<div id="visitedUserSearch_head">
		<table align="center" style="padding-top: 15px;padding-bottom: 10px;">
		<tr>
		<td><input type="radio" name="dates" value="today" id="today" checked="true" onclick="showAjaxImg('searchAjaxImgSpan'),showDates()">
		<font color="navy"><b>&nbsp;Today</b></font></td>
		<td style="padding-left:10px;"><input type="radio" name="dates" value="thisweek" id="thisweek" onclick="showAjaxImg('searchAjaxImgSpan'),showDates()">
		<font color="navy"><b>&nbsp;This Week</b></font>
		</td>
		<td style="padding-left:10px;"><input type="radio" value="thismonth" name="dates" id="thismonth" onclick="showAjaxImg('searchAjaxImgSpan'),showDates()">
		<font color="navy"><b>&nbsp;This Month</b></font>
		</td> 
		<td style="padding-left:10px;"><input type="radio" value="betweendates" name="dates" id="betweendates" onclick="showDates()" />
		<font color="navy"><b>&nbsp;Between Dates</b></font>
		</td>
		<td style="padding-left:10px;"><span id="searchAjaxImgSpan" style="display:inline-block;"><img src="images/icons/search.gif"  width="18px" height="18px;"></img></span>
		</td>
		</tr>
		</table>
		<div id="datesDiv">
		<div id="showDates" style="display:none">
		<table align="center">
			<tr>
			<td><font color="#4B74C6"><b>From Date</b></font></td>
			<td><input type="text" id="fromDate" name="fromDate" readonly="readonly" size="15">
			<div class="yui-skin-sam"><div id="fromDate_Div" class="tinyDateCal"></div></div>
			</td>
			<td valign="top">										
				<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('fromDate_Div','fromDate','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
			</td>	
			<td style="padding-left: 50px;"><font color="#4B74C6"><b>To Date</b></font></td>
			<td><input type="text" id="toDate" name="toDate" readonly="readonly" size="15">
			<div class="yui-skin-sam"><div id="toDate_Div" class="tinyDateCal"></div></div>
			</td>
			<td valign="top">										
				<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('toDate_Div','toDate','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>								
			</td>	
			</tr>
		</table>
		</div>
		<div id="errMsgDiv"></div>
		</div>
		<div id="searchDiv" style="padding:10px 0px 10px 0px;display:none;">
		<center>
			<a href="javascript:{}" onclick="checkDateFields();"><img src="images/search_button.jpg"/></a>
		</center>
		</div>
	</div>
	<div id="uniqueVisitorsDiv" style="margin-top:10px;">
	<table class="visTable">
		<tr>
			<th style="background-color:#DADCF5;"></th>
			<th>Total no. of Unique Visitors</th>
			<th>Total no. of Pages Accessed</th>
			<th>Average no. of Pages Accessed</th>
			<th>Total Time spent on the Site</th>
			<th>Average Time spent on the Site</th>
			<th style="background-color:#DADCF5;"></th>
		</tr>
		<tr>
			<th>Total Visitors</th>
			<td><div id="totVis3"></div></td>
			<td><div id="totPag3"></div></td>
			<td><div id="avgPag3"></div></td>
			<td><div id="totTime3"></div></td>
			<td><div id="avgTime3"></div></td>
			<td style="background-color:PowderBlue;"><a href="#headingStyle" onclick="getMoreVisitorDetails()">More Details</a></td>
		</tr>
		<tr>
			<th>Free Users</th>
			<td><div id="totVis0"></div></td>
			<td><div id="totPag0"></div></td>
			<td><div id="avgPag0"></div></td>
			<td><div id="totTime0"></div></td>
			<td><div id="avgTime0"></div></td>
			<td style="background-color:PowderBlue;"><a href="#headingStyle" onclick="getMoreDetails('FREE_USER')">More Details</a></td>
		</tr>
		<tr>
			<th>Customers</th>
			<td><div id="totVis1"></div></td>
			<td><div id="totPag1"></div></td>
			<td><div id="avgPag1"></div></td>
			<td><div id="totTime1"></div></td>
			<td><div id="avgTime1"></div></td>
			<td style="background-color:PowderBlue;"><a href="#headingStyle" onclick="getMoreDetails('PARTY_ANALYST_USER')">More Details</a></td>
		</tr>
		<tr>
			<th>Guest Visitors</th>
			<td><div id="totVis2"></div></td>
			<td><div id="totPag2"></div></td>
			<td><div id="avgPag2"></div></td>
			<td><div id="totTime2"></div></td>
			<td><div id="avgTime2"></div></td>
			<td style="background-color:PowderBlue;"><a href="#headingStyle" onclick="getMoreDetails(null)">More Details</a></td>
		</tr>
	</table>
	</div>
	<div id="landingPageDiv" style="margin-top:10px;"></div>
	<div id="bouncePageDiv" style="margin-top:10px;"></div>
	<div id="exitPageDiv" style="margin-top:10px;"></div>

	<div  style="margin-top: 30px; margin-left: 6px;"><span id="headingStyle" style="display:none;"></span></div>
	<div style="background: #FFF;margin-top:15px;margin-bottom: 5px;padding-bottom:20px;margin-left: 5px;">
		<div id="visitorsDetailsDiv" class=""></div>
	</div>
	<div id="userPageFlowDivOuter">
		<div id="userPageFlowDivInner"></div>
	</div>
	</fieldset>
	</div>
</div>
</body>
</html>