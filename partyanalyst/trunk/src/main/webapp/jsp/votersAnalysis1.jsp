<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
  <title>Voters analysis</title>

<style type="text/css">
.radio {
	height: 25px;
	width: 19px;
	clear:left;
	float:left;
	margin: 0 0 3px;
	padding: 0 0 0 26px;
	background: url("js/jtransform/radio.png");
	background-repeat:no-repeat;
	cursor: default;
}
.checkbox {
	height: 25px;
	width: 19px;
	clear:left;
	float:left;
	margin: 0 0 3px;
	padding: 0 0 0 26px;
	background: url("js/jtransform/checkbox.png") no-repeat;
	cursor: default;
	text-align:left;
}
.checkbox input,.radio input {
	display: none;
}
.checkbox input.show,.radio input.show {
	display: inline;
}
.selected {
	background-position: 0 -52px;
}
#MainDiv{
margin-left:auto;
margin-right:auto;
width:980px;
}
table.gridtable {
	font-family: arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #DBEBFF;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

#votersBasicInfoDivSub{
    background-color: #EDF5FF;
    font-size: 15px;
    margin-top: 5px;
    text-align: center;
	height: 25px;
}	

#votersBasicInfoSubDiv{
  margin-left: 0px;
  padding:10px;
  font-family : arial;
}

#votersBasicInfoSubChartDiv{
  margin-left: 0px;
}
.pull-right{
	 margin-top: -15px;
}

.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
	.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
.buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
	height:24px;
	
}
#votersBasicInfoDivSub{font-family:verdana;}
#impFamShowBasicInfo,#lclCastStsShowBasicInfo,
#ageWiseDetlsShowBasicInfo
{
	margin-right:10px;
}
 #votersouterDiv{
	 margin-left: auto;
	margin-right: auto;
    width: 980px;
 }
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-family: verdana;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }

 fieldset,#votersMainOuterDiv1,#votersMainOuterDiv2,
	 #votersMainOuterDiv3,#votersMainOuterDiv4{
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
}
#votersMainOutertopDiv3{
-moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
	margin-bottom: 10px;
	height:100px;
}
p {
    color: #333333;
    font-size: 11px;
    line-height: 18px;
	font-family: verdana;
}
.divInfo
{
 background-color:#FFFFFF;
  border-top: 1px solid #B3C1CE;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;

}
#partyWiseJqTable, #subLevelTable,#impfamilydatatable,#votersBasicInfoSubDivForAgeWiseDetls table,#votersBasicInfoSubDivForLclCastSts table,#votersBasicInfoSubDivForImpFam table,#impFamilesBasicSubDetails table,#impFamDtls table,#votersBasicInfoSubDiv table,#localCastStatsTabContent_body table,#localCastStatsTabContent_subbody1 table,#votersByLocationTabContentDiv_body table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#partyWiseJqTable tr:nth-child(even),#subLevelTable tr:nth-child(even),#impfamilydatatable tr:nth-child(even),#votersBasicInfoSubDivForAgeWiseDetls table tr:nth-child(even),#votersBasicInfoSubDivForLclCastSts table tr:nth-child(even),#votersBasicInfoSubDivForImpFam table tr:nth-child(even),#impFamDtls table tr:nth-child(even),#impFamilesBasicSubDetails table tr:nth-child(even),#votersBasicInfoSubDiv table tr:nth-child(even),#localCastStatsTabContent_body table  tr:nth-child(even),#localCastStatsTabContent_subbody1 table tr:nth-child(even),#votersByLocationTabContentDiv_body table tr:nth-child(even){background:#EdF5FF;}

#partyWiseJqTable td,#subLevelTable td,#impfamilydatatable td,#votersBasicInfoSubDivForAgeWiseDetls table td,#votersBasicInfoSubDivForLclCastSts table td,#votersBasicInfoSubDivForImpFam table td,#impFamDtls table td,#impFamilesBasicSubDetails table td,#votersBasicInfoSubDiv table td,#localCastStatsTabContent_body table td,#localCastStatsTabContent_subbody1 table td,#votersByLocationTabContentDiv_body table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#partyWiseJqTable th,#subLevelTable th,#impfamilydatatable th,#votersBasicInfoSubDivForAgeWiseDetls table th,#votersBasicInfoSubDivForLclCastSts table th,#votersBasicInfoSubDivForImpFam table th,#impFamDtls table th,#impFamilesBasicSubDetails table th,#votersBasicInfoSubDiv table th,#localCastStatsTabContent_body table th,#localCastStatsTabContent_subbody1 table th,#votersByLocationTabContentDiv_body table th,#votersByPanchayatTabContentDiv_body table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}

#votersBasicInfoSubDivForAgeWiseDetls table th a,#votersBasicInfoSubDivForLclCastSts table th a,#votersBasicInfoSubDivForImpFam table th a,#impFamDtls table th a,#impFamilesBasicSubDetails table th a,#votersBasicInfoSubDiv table th a,#localCastStatsTabContent_body table th a,#localCastStatsTabContent_subbody1 table th a,#votersByLocationTabContentDiv_body table th a,#votersByPanchayatTabContentDiv_body table th a{
color:#333333;
}

#votersByPanchayatTabContentDiv_body,#votersByLocationTabContentDiv_body
{
	font-family : arial;
	font-size: 13px;
    margin-top: 20px;
	padding: 10px 10px 10px 15px;
}

#impFamPancBothDtls,#impFamDtls{
 margin-top:12px;
 margin-left: auto;
    margin-right: auto;
    width:100%;
}
table.impTableDiv {background-color:transparent;border-collapse:collapse;width:100%;}
table.impTableDiv th, table.impTableDiv td {text-align:center;border:1px solid black;padding:5px;}
table.impTableDiv th {background-color:AntiqueWhite;}
table.impTableDiv td:first-child {width:50%;}

table.votersPrevCountTableDiv {background-color:transparent;border-collapse:collapse;width:100%;}
table.votersPrevCountTableDiv th, table.votersPrevCountTableDiv td {text-align:center;border:1px solid black;padding:5px;}
table.votersPrevCountTableDiv th {background-color:#CDE6FC;}

table.dataTable tr.odd {
    background-color: #ffffff;
}
table.dataTable tr.even {
    background-color:#EdF5FF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #ffffff;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #EdF5FF;
}


#impFamilesBasicSubDetailsTitle{
  font-weight:bold;
}
#impFamDtlsTitle,#impFamilesBasicSubDetailsTitle,#impFamPancBothDtlstitle{
   font-size: 16px;
    margin-left: 0px;
    margin-top: 35px;
}
#impfamilydatatable_filter{
	 margin-left: 0px;
	 
}
#impfamilydatatable{
 font-size: 11px;
}
#impFamilesBasicSubDetails{
  margin-left: 5px;
}
#impFamilesBasicInfoSubChartDiv{
   margin-left:0px;
}
#impFamPancBothDtls{
  margin-top:25px;
}
#localCastStatsTabContent_subbody caption,#localCastStatsTabContent_body caption,#subLevelTitle{
    color: #000;
    font-size: 13px;
  
    font-style: normal;
	font-family: verdana,sans-serif;
   }
.votersWidgetHeader
	{
		background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
		height:30px;
	}

	.votersWidgetMainHeader
	{
		background-image:url("images/icons/districtPage/header_body.png");
		height:36px;
	}
	.votersWidgetHeader_span
	{
		position:relative;
		top:8px;
		left:10px;
		color:#4B74C6;
		font-weight:bold;
		font-size:14px;
		font-family:verdana;
	}
	#constituencyList,#mandalField,
	#panchayatField,#reportLevel,
	#pollingStationField{width:160px;height:25px;}
	#sublevelHeading,#impFamilesBasicSubDetailsTitle,#partyWiseLocalCastStatsTabTitle,#impFamPancBothDtlstitle,#impFamDtlsTitle{
		color:steelblue;
		font-size:13px;
		font-family:verdana;
		margin-bottom: 20px;
		font-weight: bold;
	}
#impFamShowBasicInfo,#lclCastStsShowBasicInfo,#ageWiseDetlsShowBasicInfo{
   float:right;
}
#votersbasicinfoForImpFam,#votersbasicinfoForLclCastSts,#votersbasicinfoForAgeWiseDetls{
  border:1px solid #3d3d3d;
   margin-top: 32px;
   padding:10px;
   margin-bottom:5px;
}
.basicVotersInfoDiv{border: 1px solid #CCCCCC;
border-radius: 5px 5px 5px 5px;
float: none;
margin: 13px auto 12px;
padding: 7px;
width: 860px;}
#partyWiseJqTable_info,#partyWiseJqTable_paginate,#subLevelTable_info,#subLevelTable_paginate,#impfamilydatatable_info,#impfamilydatatable_paginate{
	font-family: verdana;
    font-size: 12px;
    margin-top: 12px;
	
}
.requiredFont{
	color:red;
	font-size:13px;
}
.noteDiv{
   margin-bottom: -21px;
   margin-top: 15px;
}
#impfamilydatatable th{
  padding:5px;
}
.localCastDetailsHeadingDiv,#partyWiseTitle{ 
    border-radius: 3px;
    color: #FFFFFF;
    font-family: verdana;
    font-size: 15px;
    font-weight: bold;
    margin-bottom: 14px;
    margin-top: 5px;
    padding: 4px;width:400px;}
    #localCastDetailsDiv{clear: both;
    margin-bottom: -138px;
    margin-top: 0;
    padding-top: 0;
    width: 400px;padding-left: 29px;
    padding-top: 20px;}
	#localCastChatDiv,#partyWiseChatDiv{clear: both;
    float: right;
     width: 500px;}
	 .partyWiseDetailsMainDiv{ border: 1px solid #CCCCCC;
    clear: both;
    display: table;
     margin-left: 10px;
    
    width: 926px;}
	#localCastDetailsDiv p,#partyWiseDetailsDiv p{font-size:13px;font-weight:bold;}
	#createNewGroupId:hover{background:#F61D50;color:#FFF;}
	#openProblemEditFormId{cursor:pointer;}
	.descriptionInnerDiv{margin-left: 6px; font-size: 13px; line-height: 1.7em;}
	.descriptionInnerDiv span{margin-right: 5px;}
	.buttonsTop{
	  margin-top:20px;
	  width:880px;
	  text-align: center;
	}
	.buttonLeft{
	  margin-left:10px;
	}
	.ui-widget-header{
	background: none repeat scroll 0 0 #4285F4;
	color:#fff;
	}
	.ui-widget-overlay{
	  opacity: 0.8;
	}
	#ImportantFamiliesDiv{
	  margin-bottom: 20px;
	}
	.widthStyle{
	  width:100px !important;
	}
	.votersEditTitle{
	    margin-top:5px;
	    color: steelblue;
		font-family: verdana;
		font-size: 13px;
		font-weight: bold;
		margin-bottom: 10px;
	}

	#votersByLocationTabContentDiv_body table th {padding:5px !important;}
	#votersByLocationTabContentDiv_body {padding-left: 2px !important; }
	#votersByLocationTabContentDiv_body table td {padding-right:3px !important;}
	.localCastStatsVotersTitle{border-radius: 3px 3px 3px 3px;
    color: #FFFFFF !important;
    font-family: verdana;
    font-size: 15px !important;
    font-weight: bold;
    padding: 5px;
    width: 650px;}
	#sublevelHeading{background: none repeat scroll 0 0 #06ABEA;
    border-radius: 3px;
    color: #FFFFFF;
    font-size: 14px;
    margin-top: 8px;
    padding: 4px;
    width: 465px;}
	.localCastStatsVotersTitle{background:#06ABEA;}
	.imageSize{
	   height:11px;
	   width:11px;
	}
	#partyWiseLocalCastStatsTab table th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    text-align: left;
   }
   #partyWiseLocalCastStatsTab table td {
    color: #676A67;
    font: small-caption;
  }	
	#previousEleVotingTrendsDiv p span {display: table-cell;}
	#prevVotTrendHeadingSpan{background:#06ABEA;color:#FFF;
	font-weight:bold;font-size:15px;
	border-radius: 3px;
    padding: 3px 10px;}
	#previousEleVotingTrendsDiv {margin-top: 30px;margin-bottom:25px;}
	#previousEleVotingTrendsDiv p{height:25px;}
	#previousEleVotingTrendsDiv table{border:1px solid #cdcdcd;}
	#previousEleVotingTrendsDiv table th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#previousEleVotingTrendsDiv table td {
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
.selectedLink{
	  background-color:#5e5e5e;
	  color:#fff;
	  padding:2px;

  }
.pr-cont-sec
{
	margin-left: 10px;
}

.pft-sec {
    color: #777777;
    float: left;
    font-size: 20px;
    line-height: 24px;
    padding: 32px 0 16px 21px;
    width: 244px;
}
.popupcontainer {		
    	background-color: #FFFFFF;
    	margin: 9px auto 10px;
    	max-width: 780px;
    	padding: 10px;
		box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
}
.main-mbg {
    -moz-border-radius: 6px 6px 6px 6px;
	border-radius :6px;
    background-color: #06ABEA;
    clear: both;
    color: #FFFFFF;
    float: left;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    margin-bottom: 5px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 974px;
}
.profile-left-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll left top transparent;
    display: inline-block;
    float: left;
    margin-right: 2px;
    padding-top: 5px;
    width: 204px;
}
.profile-mid-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -211px top transparent;
    display: inline-block;
    float: left;
    padding-top: 5px;
    position: relative;
    width: 441px;
}
.f2{margin-right:15px;}
.badge-add{padding:10px;border-radius:50%;background:#ABCFF3;color:#222222;}

body {
    color: #000000;
}
#previousEleVotingTrendsDiv tabel tr th{color:#000;}
#previousEleVotingTrendsDiv table tr:nth-child(2n-1) {
    background: none repeat scroll 0 0 #EDF5FF;
}
h5{font-family : Arial;}
.customMenu {display:none;width:100%;clear:both; margin: 5px -15px;
    padding: 5px 15px;background:#3198B6;}
.customMenu .leftNav, .customMenu .middleNav,.customMenu .rightNav{background:#fff;border-radius:2px;box-shadow:0px 1px 2px #aaa;}
.customMenu .rightNav{width:490px;margin-left:10px;}
.customMenu .leftNav{margin-left:0px;}
.bs-docs-sidenav{margin:0px;}
.customMenu .leftNav li{display:block;}
.customMenu .middleNav li{display:block;}
.middleNav-Wards{display:none;}
.customMenu .rightNav li{margin:10px;display:inline-block;float:left;}
.nav-stacked a.hoverclass{background:#5e5e5e;}
.leftNav-Municipalities{margin-top:25px;}
/*
.customMenu .leftNav ul li{display:inline-block;clear:both;width:100%;}
.customMenu .leftNav ul li a{display:inline-block;clear:both;width:100%;padding:5px;border:1px solid #ccc;}*/
.bs-docs-sidenav > li > a { padding: 4px 14px;padding-left:2px;cursor:pointer;cursor:hand;}
.ShowHideLevelMenu{position:absolute;top:38px;right:10px;}
</style>
<style>
.background{background:#fff;}
/*.bs-docs-sidenav li {position:relative;}
.bs-docs-sidenav li .popover-cust{display:none;position:absolute;left:165px;top:-26px;}
.bs-docs-sidenav li:hover .popover-cust{display:block;}*/

  #localCastDetailsDiv{
		font-family: verdana;
		font-size: 13px;
		font-weight: bold;
  }
  #votersDiv1,#votersDiv2{
    display:none;
  }
  #partyWiseDetailsDiv{
     margin-left:42px;
  }
</style>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
	var votersLimitExist = false;
var Localization = { <%
			
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			
			String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
			String locationAlert = rb.getString("validLocation");
		  %> }
var locationDetails={
				constituencyArr:[],
					};

<c:forEach var="constituency" items="${constituencyList}">
	var ob={
			id:'${constituency.id}',
			value:'${constituency.name}'
			};
locationDetails.constituencyArr.push(ob);
</c:forEach>		

</script>
</head>
<body>
<!--DISPLAY NEWS GALLARIES START-->
 <div id="showContentDiv">
	<div id="showContentDivInnerDiv"></div>
	<div id="showContentDivInnerDiv"></div>
 </div>
	<div id="videoGallaryPopUpDiv"></div>
	<div id="emailAlertDiv"></div>
	<div id="sendMessageDiv">
    <div id="constituencySelectDiv"/>
	</div>
	<div id="logInDiv"></div>
<!--DISPLAY NEWS GALLARIES END-->
<br><br>
<div id="MainDiv">
<div id="votersouterDiv">
<fieldset>
<div style="color:#707070;font-weight:bold;font-size:13px; font-family: verdana;">Please select from the following list boxes to view detailed statistics by Assembly/mandal/Panchayat/Polling station level</div><br><div id="categoeryCreationDiv" style="float:right;">
	
	<a href="javascript:{}" class="btn" id="createNewGroupId" onclick="openNewWindow();">Create Custom Groups</a>
</div>

<div id="AlertMsg" style="font-family: verdana;
    font-size: 13px;"></div>
<div id="ConstituencyDiv" class="selectDiv">
	Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDate();"/> &nbsp;&nbsp;

		
	Select Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		
	</div>
	
	
	
	<div id="ajaxImageDiv" style="float:right;margin-right:90px;display:none;"><img src="./images/icons/search.gif" alt="Processing Image"/> </div>
	</fieldset>

</div>
<!-- <div id="defaultWidth" style="min-height:300px;"></div>
for  body3 start    result  -->
<div id="votersDiv3" >





<div id='votersHeaderTopDiv3' style="clear:both;position:relative;">
		<div class="breadcrumb">
		
		<h4 id="reportLevelheading" class=""> </h4>
		
		
 
 <div id="reportLevelCountDiv"> 
 </div>
 
 
 <div class="ShowHideLevelMenu" id="showHideDiv" style="display:none;">
<a class="btn" href="javascript:{}" id="ShowMenu">Show Menu <i class="icon-chevron-down"></i></a>
 </div>
 
 <div class="customMenu" id="CustomMenu"><!-- Menu Start-->

<!-- Left Nav -->
<div class="span3 leftNav">
<div class="leftNav-Mandals"> <h5 class="breadcrumb" style="margin-bottom:0px;">Mandals</h5>
<ul id="leftNav-Mandals-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
</div>
<div class="leftNav-Municipalities"> <h5 class="breadcrumb" style="margin-bottom:0px;">Municipalities</h5>
<ul id="leftNav-Municipalities-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
</div>
</div>
<!-- Left Nave End-->
 <!-- Middle Nav -->
<div class="span3 middleNav" style="margin-left:10px;">
	<div class="middleNav-Panchayats">
		<h5 class="breadcrumb" style="margin-bottom:0px;">Panchayat(s)</h5>
		<ul id="middleNav-Panchayats-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
	</div>
	<div class="middleNav-Wards">
		<h5 class="breadcrumb" style="margin-bottom:0px;">Ward(s)</h5>
		<ul id="middleNav-Wards-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
	</div>
</div>
<!-- Middle End-->


<!-- Right Nav -->
<div class="span6 rightNav">
	<div class="rightNav-Booths">
			<h5 class="breadcrumb" style="margin-bottom:0px;">Booth(s)</h5>
			<ul id="rightNav-Booths-list"></ul>
	</div>
</div>
<!-- RIGHT End-->

</div><!-- Menu End-->

	</div>
	
	
 
 <!--<h5 id="reportTopLevelheading" style="margin-left:15px;"></h5>-->
<div id='votersHeaderDiv3' style="clear:both;display:none;">
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="votersTitle" class="votersWidgetHeader_span" style="top:11px;"> </span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div>
    
<div id='votersMainOuterDiv3' style="display:none;">
 <h5 id="reportLevelheading1" style="margin-left:15px;"></h5>
<div id="reportLevelCountDiv1" style="clear:both;"></div><br>
	<div id="newsCountDiv"></div>
	   <div id="newsDisplayOuterDiv">
		<div id="newsDisplayDiv" style="clear:both;margin:1px 0px 0px 8px;border-top:1px solid #c3c3c3;"></div>
	   </div>
	<div id="previousEleVotingTrendsDiv" style="clear:both;"></div>
	<!--<div id="showHideLink" style="float:right;margin-right:15px;"><a href="javaScript:{showHideDiv();}">Show/Hide News Details</a></div>-->
 	<div id="newsDiv" style="clear:both;">
  	</div>
	<div id="votersBasicInfoDiv"></div>
	<div id="previousEleVotingTrendsDiv"></div>
	<div id="votersBasicInfoSubChartDiv" style="border:1px solid black"></div>
	</br>
	<div id="votersBasicInfoSubDiv" style="border:1px solid black" class="yui-skin-sam yui-dt-sortable"></div>

<div id="votersInfoMoreShowHide" style="display:none;"><a href="javaScript:{};" onclick="getVotersDetails();">Click here for voters details</a></div>
<div id="votersOuterDiv1" style="display:none;">
 <div id="votersInnerDiv1" style="height:500px;overflow-y:scroll;">
	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
 </div>
</div>

	<!--<div  id="votersByPanchayatTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>-->
	
<!-- for  body 3 end    result  -->
</div>
</div><!-- body 3 end -->
</div>
<!-- for  body4 start    result  -->
<div id="votersDiv4" style="display:none;">
<div id='votersHeaderDiv4'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="AgeWisetitle" class="votersWidgetHeader_span" style="top:11px;"> </span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div>
<div id='votersMainOuterDiv4' style="height:300px;">
	

	<!-- <input type="button" id="ageWiseDetlsShowBasicInfo" class="buttonStyle" value="View Basic Voter Details" style="margin-top:5px;"/>
     <div id="votersbasicinfoForAgeWiseDetls" >
        <div id="votersBasicInfoDivForAgeWiseDetls"></div>
	    <div id="votersBasicInfoSubChartDivForAgeWiseDetls"></div>
	    <div id="votersBasicInfoSubDivForAgeWiseDetls" class="yui-skin-sam yui-dt-sortable"></div>	
     </div>-->

<!--<div id="processingDialogOuterDiv">
  <div id="processingDialogInnerDiv">
  </div>
</div>-->

<!--<a href="javaScript:{showBasicAgewiseDetails()}">Click here to get Agewise Details</a>-->
<span id="agewiseAjaxDiv" style="display:none;float:right;margin-left:30px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
<div id="voterDetailsNote1" class="noteDiv"></div>

<div id="tableDiv1" style="margin-left:35px;padding:10px;" class="voterDetails"></div>
<span id="ageLink" style="display:none;float:right;"><b><a href="javaScript:{showAllAgewiseDetails()}">View more details</a></b></span>


<div id="ageWiseVotersDetailsOuterDiv">

	<div id='ageWiseInfoDiv' class=""  style="height:500px;overflow-y:auto;">
	<br><br>
	<div id="ageWiseVotersBasicInfoSubChartDiv" style="margin-left:100px;" ></div>

	<div id="voterDetailsNote" class="noteDiv" style="display:none;"></div>

	<div id="tableDiv" style="margin-left:35px;padding:10px;display:none;" class="voterDetails"></div>

	<div id="voterAgewiseDetailsNote" class="noteDiv"></div>
	<div id="agewiseDetails" style="margin-left:35px;padding:10px;" class="voterDetails"></div>

	<div id="voterAgeAngGenderwiseDetailsNote" class="noteDiv"></div>
	<div id="ageAndgenderWiseDetails" style="margin-left:35px;padding:10px;" class="voterDetails"> </div>


	<div id="voterAgeAngGenderwiseDetailsNoteInPercent" class="noteDiv"></div>

	<div id="voterAgeAngGenderwiseDetailsInPercent" style="margin-left:35px;padding:10px;" class="voterDetails"></div>
	<div id="AgeWiseNoteDiv" style="border: 1px solid #d3d3d3;padding:5px;margin-left:45px;width:851px;"></div>

	
  </div>
</div>

<!-- for  body 4 end    result  -->

</div>
</div>


<!-- for  body 1 start    result  -->
<HR>
<div id="votersDiv1" >
<div id='votersHeaderDiv1'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="impFamiliesTitle" class="votersWidgetHeader_span" style="top:11px;"> </span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
		
	</div>

<div id='votersMainOuterDiv1'>
	 <!--<input type="button" id="impFamShowBasicInfo" class="buttonStyle" value="View Basic Voter Details" style="margin-top:5px;"/>
     <div id="votersbasicinfoForImpFam" >
        <div id="votersBasicInfoDivForImpFam"></div>
	    <div id="votersBasicInfoSubChartDivForImpFam"></div>
	    <div id="votersBasicInfoSubDivForImpFam" class="yui-skin-sam yui-dt-sortable"></div>	
     </div>-->
     	<div id="ImportantFamiliesDiv" class="divInfo">
	<div id ="impFamilesBasicDetails"></div>
	</br>
	   <div><input id="impFamiliesMoreInfoButn" type="button" value="View More Details" onclick="getImpFamiliesVotersToShow();" class="btn" style="float:right;"/></div>
	   <div id="impFamilesAllInfoPopUp" style="display:none;">
        <div id ="impFamilesBasicInfoSubChartDiv" style="border:1px solid black"></div>
		<div id ="impFamilesBasicSubDetailsTitle" ></div>
	
		<div id ="impFamilesBasicSubDetails" style="border:1px solid black"></div>
		<div id="descriptionDiv" ></div>
		<div id="impFamPancBothDtls"></div>
	   </div>
		<br>
		
		<br>
		<div id="NoteDiv" style="border: 1px solid #d3d3d3;padding:5px;margin-left:5px;width:920px;"></div>
		
		<div id="impFamDtlsOuterPopUp" style="display:none;">
		   <div id="impFamDtlsTitle"></div>
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable" style="border:1px solid black;width: -moz-fit-content;margin-top:10px;"></div>
	       <div class="buttonsTop"><span class="buttonLeft"><input class="btn" onclick="selectAll('familyMemberCheck')" type="button" value="Select All" /></span><span class="buttonLeft"><input onclick="deSelectAll('familyMemberCheck')" class="btn" type="button" value="De Select All" /></span><span class="buttonLeft"><input class="btn" type="button" value="Edit" onclick="getAllVoterFamiliesForEdit();"/></span></div>
		   <div id="multipleVoterFamiliesEditDiv"></div>
		</div>

	</div>

</div><!-- for  body 1 end    result  -->

</div><!-- for  body 1 end -->
<!-- for  body 2 start    result  -->
<div id="votersDiv2" >
<div id='votersHeaderDiv2'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="localCastStatsTabContentTitle" class="votersWidgetHeader_span" style="top:11px;"> </span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div>
<div id='votersMainOuterDiv2'>
	<!--<input type="button" id="lclCastStsShowBasicInfo" class="buttonStyle" value="View Basic Voter Details" style="margin-top:5px;"/>
    <div id="votersbasicinfoForLclCastSts" >
        <div id="votersBasicInfoDivForLclCastSts"></div>
	    <div id="votersBasicInfoSubChartDivForLclCastSts"></div>
	    <div id="votersBasicInfoSubDivForLclCastSts" class="yui-skin-sam yui-dt-sortable"></div>	
    </div>-->
	<div id='LocalCastDiv' class="divInfo">
	
	<!--<div id ="localCastStatsTabContentTitle" ></div>-->
	
		<div id='localCastStatsTabContent_header'></div>
		<div id="localCastDetailsHeadingDiv" class="localCastDetailsHeadingDiv" style="margin-bottom: 0px;"></div>
	<div class="castDetailsMainDiv">
		<div id="localCastDetailsDiv" style="margin-bottom:0px;"></div>
		<div id="localCastChatDiv"></div>
	</div><br>
	<div id='localCastStatsTabContent_body' class="yui-skin-sam yui-dt-sortable">	</div><br>
	<div id='partyWiseLocalCastStatsTab' class="yui-skin-sam yui-dt-sortable" style="margin-top:25px;">	</div>
	<div id='localCastStatsTabContent_subbody'></div><br><br>
	<div id="localCastStatsVotersPopUpDiv" style="display:none;">
	    <div id ="localCastStatsVotersTitle" ></div>
	    <div id='localCastStatsTabContent_subbody1'  class="yui-skin-sam yui-dt-sortable"></div><br>
	</div>
	<div id="castPartyPopupShowBtn"><input type="button" class="btn" value="View More Details" style="float:right;" onclick="ShowCastPartyPopupDiv();"/></div>
	<div id="castPartyPopupDiv" style="display:none">   
		<div id="partyWiseDetailsHeadingDiv" class="localCastDetailsHeadingDiv"></div>
	    <div id='partyWise_header'></div>
		
	    <div class="partyWiseDetailsMainDiv">
		<table style="width:100%;"><tr>
		  <td><div id="partyWiseDetailsDiv"></div></td>
		  <td><div id="partyWiseChatDiv"></div></td></tr></table></div><br>
		<div id="partyWiselocalcastDiv"></div>
	</div>	
		<br><br>
	

	


</div>
</div><!-- for  body 2 end    result  -->
</div><!-- for  body 2 end >





<!-- main div  End-->
</div>
<script type="text/javascript">
function getCastInfoForsubLevel(id,publicationId,type)
	{
	document.getElementById('localCastStatsVotersTitle').innerHTML='';
	document.getElementById('localCastStatsTabContent_subbody').innerHTML='';
	document.getElementById('localCastStatsTabContent_subbody1').innerHTML='';
	var typeName=mainname;
	/*var publicationDateId = $("#publicationDateList").val();
	var level = $("#reportLevel").val();
	var type = '';
	var id='';
	var typeName='';
	var mandalId='';
	var validflag =0;
	var str ='';
	var flag = true;
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	typeName = $("#constituencyList :selected").text();
	if(id == 0 || id == null)
	{
	flag =false;
	}
	}
	else if(level == 2 ||id == null){
	type = 'mandal';
	id = $("#mandalField").val();
	var mandalText = $('#mandalField :selected').text();
	var validflag= mandalText.search("MUNCIPALITY");
	typeName = $("#mandalField :selected").text();
	if(id == 0)
	{
	flag =false;
	}
	else if(validflag != -1)
	{
	flag =false;
	}
	}
	else if(level == 3 || id == null){
	type = 'panchayat';
	id = $("#panchayatField").val();
	mandalid = $("#mandalField").val();
	typeName = $("#panchayatField :selected").text();
	var mandalText = $('#mandalField :selected').text();
	 validflag= mandalText.search("MUNCIPALITY");
	 if(mandalid == 0)
		{
		 flag =false;
		}
	else if(validflag != -1)
		{
		flag=false;
		}
	  else if(id == 0)
		{
		flag =false;
		}
		
	}
	else if(level == 4 || id == null)
	{
	
		return false;
	}
	if(publicationDateId == 0|| publicationDateId == null)
		{
		flag =false;
		}*/
		if(type == "mandal"){

		 if(locationValue.charAt(0) == "1"){
			 return;
		  }
		}
		if(true)
		{
		var jsObj=
		{		
				type:type,	
				id:id,
				typeName:typeName,
				publicationDateId:publicationId,	
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
		}
}

function openNewWindow(){
	var urlStr="votersCategoeryAction.action";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}
function showHideDiv(){
  $('#newsDiv').toggle('slow');
 }


function showAgewiseDetails(){
		callCorrespondingAjaxCall();

}

function getVotersDetails(){
		getVotersData();
}


function showBasicAgewiseDetails(){
   callCorrespondingAjaxCall('brief');
}


function showAllAgewiseDetails(){
   callCorrespondingAjaxCall('all');
}
</script>
</body>
</html>