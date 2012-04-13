<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Tracking Analysis</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<style type="text/css">
#userTrackingMainDiv
{
margin-left: auto;
margin-right: auto;
float: none;
width:995px;
}
#headerImageCenterDiv
{
 background-image: url("images/icons/constituencyManagement/header_body_blue.png");
 color:#FFFFFF;
 text-align: center;
 width: 240px;
 height:30px;
}
.f2
{
border:2px solid #CFD6DF;
margin-left: 13px;
margin-top: 14px;
width: 950px;
}
#headerImageCenterSpan
{
top: 5px;
position: relative;
}
#visitedUserSearch_head
{
background:#EEF4F6;
}
</style>
<script type="text/javascript">

function showDates(){

if(document.getElementById("betweendates").checked == true)
document.getElementById("showDates").style.display = "block";
else
	document.getElementById("showDates").style.display = "none";

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
		<td><input type="radio" name="dates" value="today" checked="true" onclick="">
		<font color="navy"><b>&nbsp;ToDay</b></font></td>
		<td style="padding-left:10px;"><input type="radio" name="dates" value="thisweek" id="thisweek" onclick="">
		<font color="navy"><b>&nbsp;This Week</b></font>
		</td>
		<td style="padding-left:10px;"><input type="radio" value="thismonth" name="dates" id="thismonth" onclick="">
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

	</fieldset>
</div>


</div>
</body>
</html>