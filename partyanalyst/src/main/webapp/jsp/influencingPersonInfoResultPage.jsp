<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Influencing Person Complete Profile</title>

<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
<!-- YUI Dependency files (End) -->

<style type="text/css">
	
	body
	{
		background-color:#FFFFFF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}
	
	#cadreInfo_head
	{
		color:#8E320A;
		font-size:17px;
		font-weight:bold;
		text-align:center;
		text-decoration:underline;
		padding:5px;
		margin-top:10px;
	}
	
	.cadreProfileInfoTable th
	{
		color:#652D2D;
		padding:4px;
		text-align:left;
	}

	.cadreProfileInfoTable td
	{
		color:#31383C;
		padding:4px;
		text-align:left;
	}
	
	.tableHeadingDiv
	{
		color:#1C3752;
		font-size:15px;
		font-weight:bold;
		margin-left:0;
		padding:6px;
		text-decoration:underline;
	}
	
	#cadreInfo_footer
	{
		margin-right:10px;
		padding:10px;
		text-align:right;
	}
	fieldset {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;		
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		padding:5px;
		font-weight:bold;
	}

</style>
<script type="text/javascript">
var influencingPersonId = '${influencingPersonId}';


</script>
</head>

<body>
	<div id="cadreInfo_head"> ${influencingPeopleBeanVO.firstName} ${influencingPeopleBeanVO.lastName} Complete Profile </div>

	<fieldset>
	<legend>Personal Details</legend>
	<table width="100%" border="0">
		<tr>
			<td width="70%" valign="top">
			<table width="100%" class="cadreProfileInfoTable">
				<tr>
					<th style="width:125px">First Name </th>
					<th> : </th>
					<td>${influencingPeopleBeanVO.firstName}</td>
				</tr>
				<tr>
					<th style="width:125px">Last Name</th>
					<th>:</th>
					<td>${influencingPeopleBeanVO.lastName}</td>
				</tr>
				<tr>
					<th style="width:125px">Middle Name</th>
					<th>:</th>
					<td>${influencingPeopleBeanVO.middleName}</td>
				</tr>
				<tr>
					<th style="width:125px">Father/Spouse Name</th>
					<th>:</th>
					<td>${influencingPeopleBeanVO.fatherOrSpouseName}</td>
				</tr>
				<tr>
					<th style="width:125px">Gender</th>
					<th>:</th>
					<td>${influencingPeopleBeanVO.gender}</td>
				</tr>	
		</table>
			</td>
			<td width="50%" valign="top">
			<img height="110px" width="130px" src="images/icons/indexPage/human.jpg">
			</td>
		</tr>
	</table>
	</fieldset>
	
	<fieldset>
	<legend>Contact Details</legend>
	<table width="100%" class="cadreProfileInfoTable" border="0">
		<tr>
			<th style="width:125px">Mobile</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.mobile}</td>
		</tr>
		<tr>
			<th style="width:125px">Email</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.email}</td>
		</tr>
		<tr>
			<th style="width:125px">House No</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.houseNo}</td>
		
			<th style="width:125px">Street Name</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.streetName}</td>
		</tr>
		<tr>
			<th style="width:125px">State</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.stateName}</td>
		
			<th style="width:125px">District</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.districtName}</td>
		</tr>
		<tr>
			<th style="width:125px">Constituency</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.constituencyName}</td>
		
			<th style="width:125px">Mandal/Municipality</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.mandalName}</td>
		</tr>
		<tr>
			<th style="width:125px">Village/Ward</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.wardOrHamletName}</td>
		
			<th style="width:125px">Pincode</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.pincode}</td>
		</tr>
		<tr>
			<th style="width:125px">Booth</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.boothName}</td>
		</tr>
	</table>
	</fieldset>

	<fieldset>
	<legend>Social Status</legend>
	<table width="100%" class="cadreProfileInfoTable" border="0">
		<tr>
			<th style="width:125px">Occupation</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.occupationType}</td>
			
			<th style="width:125px">Party</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.partyName}</td>
		</tr>
		<tr>
			<th style="width:125px">Cast</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.castType}</td>
			
			<th style="width:125px">Position</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.positionType}</td>
		</tr>
		<tr>
			<th style="width:125px">Influence Range</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.influencingRangeName}</td>
			
			<th style="width:125px">Influencing Location</th>
			<th>:</th>
			<td>${influencingPeopleBeanVO.influencingRangeScope}</td>
		</tr>
		</table>
	</fieldset>

</body>
</html>