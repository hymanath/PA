<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Tracking Analysis</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/problemManagement/problemManagement.js"></script>

<style type="text/css">
.visTable td{	
    text-align:center;
    background-color:AliceBlue;
	width:150px;
	padding:5px 0px 5px 0px;
}
.visTable{
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
	 width: 295px;
    text-align: center;
	
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	getUserDetails('getUniqueVisitorsAction.action?');	
});
function showDates(){
	if(!($("#betweendates").is(':checked')))
		getUserDetails('getUniqueVisitorsAction.action?');
	else{		
		if($("#fromDate").val()!="" && $("#toDate").val()!="")
			getUserDetails('getUniqueVisitorsAction.action?');
	}
	
	if(document.getElementById("betweendates").checked == true)
		document.getElementById("showDates").style.display = "block";
	else
		document.getElementById("showDates").style.display = "none";
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
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "todayVisitorsDetails"){
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisMonthVisitorsDetails"){
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisWeekVisitorsDetails"){
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "todayUserDetails"){
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisWeekUserDetails"){
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "thisMonthUserDetails"){
					showBetweendatesVisitorsDetails(myResults);
				}
				else if(jsObj.task == "betweendatesUserDetails"){
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
}
function getMoreVisitorDetails()
{
  if(document.getElementById("today").checked == true)
	{
	  getVisitorDetails("todayVisitorsDetails","","");

	  headingDetails("ToDay Total Visitors Details"); 
	 }
  if(document.getElementById("thisweek").checked == true)
	{
	  getVisitorDetails("thisWeekVisitorsDetails","","");

		headingDetails("This Week Total Visitors Details");
	  
	}
  if(document.getElementById("thismonth").checked == true)
	{
	  getVisitorDetails("thisMonthVisitorsDetails","","");

	  headingDetails("This Month Total Visitors Details");
	}
  if(document.getElementById("betweendates").checked == true)
	{
	  var fromDate = "";
	  var toDate = "";
	  fromDate = document.getElementById("fromDate").value;
	  toDate = document.getElementById("toDate").value;
	  getVisitorDetails("betweendatesVisitorsDetails",fromDate,toDate);
	  headingDetails(''+fromDate+' to '+toDate+' Total Visitor Details');
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
 if(document.getElementById("today").checked == true)
	{
	  getUserMoreDetails("todayUserDetails","","",userType);
	  if(userType == null)
	    headingDetails("ToDay Guest Visitors Details");
	  if(userType == "FREE_USER")
	    headingDetails("ToDay Free Users Details");
	  if(userType == "PARTY_ANALYST_USER")
		headingDetails("ToDay Customers Details");
	}
  if(document.getElementById("thisweek").checked == true)
	{
	  getUserMoreDetails("thisWeekUserDetails","","",userType);
	  if(userType == null)
	     headingDetails("This Week Guest Visitors Details");
	  if(userType == "FREE_USER")
         headingDetails("This Week Free Users Details");
	  if(userType == "PARTY_ANALYST_USER")
		  headingDetails("This Week Customers Details");

	}
  if(document.getElementById("thismonth").checked == true)
	{
	  getUserMoreDetails("thisMonthUserDetails","","",userType);
	  if(userType == null)
		  headingDetails("This Month Guest Visitors Details");
	  if(userType == "FREE_USER")
		  headingDetails("This Month Free Users Details");
	  if(userType == "PARTY_ANALYST_USER")
		  headingDetails("This Month Customers Details");
	}
  if(document.getElementById("betweendates").checked == true)
	{
	  var fromDate = "";
	  var toDate = "";
	  fromDate = document.getElementById("fromDate").value;
	  toDate = document.getElementById("toDate").value;
	  getUserMoreDetails("betweendatesUserDetails",fromDate,toDate,userType);
	  if(userType == null)
		  headingDetails(''+fromDate+' to '+toDate+' Guest Visitors Details');
	  if(userType == 'FREE_USER')
		  headingDetails(''+fromDate+' to '+toDate+' Free Users Details');
	  if(userType == 'PARTY_ANALYST_USER')
		  headingDetails(''+fromDate+' to '+toDate+' Customers Details');
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
str +='<td width="15%">'+result[i].userName+'</td>';
str +='<td  width="10%">'+result[i].noOfPages+'</td>';
str +='<td  width="15%">'+result[i].spentTime+' hours</td>';
str +='<td  width="40%">'+result[i].landingPage+'</td>';
str +='<td  width="40%">'+result[i].exitPage+'</td>';

str += '</tr>';
}
str +='</table>';

visitorsDetailsDiv.innerHTML = str;
}

function headingDetails(text)
{
document.getElementById("headingStyle").style.display = 'block';
document.getElementById("headingStyle").innerHTML = text;
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
		<td><input type="radio" name="dates" value="today" id="today" checked="true" onclick="showDates()">
		<font color="navy"><b>&nbsp;Today</b></font></td>
		<td style="padding-left:10px;"><input type="radio" name="dates" value="thisweek" id="thisweek" onclick="showDates()">
		<font color="navy"><b>&nbsp;This Week</b></font>
		</td>
		<td style="padding-left:10px;"><input type="radio" value="thismonth" name="dates" id="thismonth" onclick="showDates()">
		<font color="navy"><b>&nbsp;This Month</b></font>
		</td> 
		<td style="padding-left:10px;"><input type="radio" value="betweendates" name="dates" id="betweendates" onclick="showDates()" />
		<font color="navy"><b>&nbsp;Between Dates</b></font>
		</td>
		</tr>
		</table>

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
			<td style="background-color:#DADCF5;"><a href="javascript:{}" onclick="getMoreVisitorDetails()">More Details</a></td>
		</tr>
		<tr>
			<th>Free Users</th>
			<td><div id="totVis0"></div></td>
			<td><div id="totPag0"></div></td>
			<td><div id="avgPag0"></div></td>
			<td><div id="totTime0"></div></td>
			<td><div id="avgTime0"></div></td>
			<td style="background-color:#DADCF5;"><a href="javascript:{}" onclick="getMoreDetails('FREE_USER')">More Details</a></td>
		</tr>
		<tr>
			<th>Customers</th>
			<td><div id="totVis1"></div></td>
			<td><div id="totPag1"></div></td>
			<td><div id="avgPag1"></div></td>
			<td><div id="totTime1"></div></td>
			<td><div id="avgTime1"></div></td>
			<td style="background-color:#DADCF5;"><a href="javascript:{}" onclick="getMoreDetails('PARTY_ANALYST_USER')">More Details</a></td>
		</tr>
		<tr>
			<th>Guest Visitors</th>
			<td><div id="totVis2"></div></td>
			<td><div id="totPag2"></div></td>
			<td><div id="avgPag2"></div></td>
			<td><div id="totTime2"></div></td>
			<td><div id="avgTime2"></div></td>
			<td style="background-color:#DADCF5;"><a href="javascript:{}" onclick="getMoreDetails(null)">More Details</a></td>
		</tr>
	</table>
	</div>
	<div  style="margin-top: 30px; margin-left: 6px;"><span id="headingStyle" style="display:none;"></span><></div>
	<div style="background: #FFF;margin-top:15px;margin-bottom: 5px;padding-bottom:20px;margin-left: 5px;">
	<div id="visitorsDetailsDiv" class=""></div>
	</div>
	</fieldset>
	</div>
</div>
</div>
</body>
</html>