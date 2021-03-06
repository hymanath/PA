<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<!--<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>-->
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
   <script type="text/javascript" src="https://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
	<!--<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>-->
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"></script>
	
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!--<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">-->
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>

<link rel="stylesheet" href="dist/css/bootstrap.css">

<title>Voters Analysis</title>


<style type="text/css">
ul{padding:0px !important;}
/*.disableClass{
background-color:#AED1DB;
opacity:0.66;
}*/
.error{
	color:red;
}

#amount{width:90%;text-align:center;}

#casteDivs li>a {
	display: block;
    height: 2em;
    line-height: 2em;
    padding: 0 1.5em;
    text-decoration: none;
}
#casteDivs ul {
    position: absolute;
    display: none;
	z-index: 999;
}
#casteDivs li:hover ul {
    display: block;
}
.dataTables_wrapper{
	padding-top: 19px !important;
}

#emprtyData
{

color:red;

}


.mybtn
{
color:#FFFFFF !important;
}

.wid
{
width: 199px !important;
word-wrap: break-word;
height: 34px;
}
.marg1
{
margin-top: 5px !important;
font-size:12px;
color:#0088CC;
font-family:Arial;
}

.marg
{
margin-top: 3px !important;
}
.locationErrorMsg{
	color:red;
}

.newssources{
 background-color:#97DFEB;
 padding:6px;
 margin-left:2px;
 border-radius: 5px 5px 5px 5px;
 display:table;
}
#buildNewSources{display:table;}
#voterBasicInfoTable td{
	text-align:center;

}
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
	/*padding: 0 0 0 26px;*/
	background: url("js/jtransform/checkbox.png");
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
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#000;
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


table.gridtable1 {
	font-family: Verdana,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable1 th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #DBEBFF;
}
table.gridtable1 td {
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
	 //margin-top: -15px;
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
  
 }
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }
 .checked1{background-position: 0 -50px ! important;}
   /*
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
}*/
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
 padding-bottom:40px;

}
#partyWiseJqTable, #subLevelTable,#impfamilydatatable,#votersBasicInfoSubDivForAgeWiseDetls table,#votersBasicInfoSubDivForLclCastSts table,#votersBasicInfoSubDivForImpFam table,#impFamilesBasicSubDetails table,#impFamDtls table,#votersBasicInfoSubDiv table,#localCastStatsTabContent_body table,#localCastStatsTabContent_subbody1 table,#impFamilesBasicSubDetailsForHamlet table,#impFamilesnfoForHamletByBooth table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#votersByLocationTabContentDiv_body table,#InfluencingPeopleDetailsDiv table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#partyWiseJqTable tr:nth-child(even),#subLevelTable tr:nth-child(even),#impfamilydatatable tr:nth-child(even),#votersBasicInfoSubDivForAgeWiseDetls table tr:nth-child(even),#votersBasicInfoSubDivForLclCastSts table tr:nth-child(even),#votersBasicInfoSubDivForImpFam table tr:nth-child(even),#impFamDtls table tr:nth-child(even),#impFamilesBasicSubDetails table tr:nth-child(even),#votersBasicInfoSubDiv table tr:nth-child(even),#localCastStatsTabContent_body table  tr:nth-child(even),#localCastStatsTabContent_subbody1 table tr:nth-child(even),#votersByLocationTabContentDiv_body table tr:nth-child(even),#InfluencingPeopleDetailsDiv table tr:nth-child(even),#impFamilesBasicSubDetailsForHamlet table tr:nth-child(even),#impfamilydatatable1 table
tr:nth-child(even),#impFamilesnfoForHamletByBooth  table tr:nth-child(even){background:#EdF5FF;}

#partyWiseJqTable td,#subLevelTable td,#impfamilydatatable td,#impfamilydatatable1 td,#votersBasicInfoSubDivForAgeWiseDetls table td,#votersBasicInfoSubDivForLclCastSts table td,#votersBasicInfoSubDivForImpFam table td,#impFamDtls table td,#impFamilesBasicSubDetails table td,#impFamilesnfoForHamletByBooth td , #votersBasicInfoSubDiv table td,#localCastStatsTabContent_body table td,#localCastStatsTabContent_subbody1 table td,#votersByLocationTabContentDiv_body table td,#InfluencingPeopleDetailsDiv table td,#impFamilesBasicSubDetailsForHamlet table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#partyWiseJqTable th,#subLevelTable th,#impfamilydatatable th,#impfamilydatatable1 th,#votersBasicInfoSubDivForAgeWiseDetls table th,#votersBasicInfoSubDivForLclCastSts table th,#votersBasicInfoSubDivForImpFam table th,#impFamDtls table th,#impFamilesBasicSubDetails table th,#impFamilesnfoForHamletByBooth table th , #votersBasicInfoSubDiv table th,#localCastStatsTabContent_body table th,#localCastStatsTabContent_subbody1 table th,#votersByLocationTabContentDiv_body table th,#votersByPanchayatTabContentDiv_body table th,#InfluencingPeopleDetailsDiv table th,#impFamilesBasicSubDetailsForHamlet table th{
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

#votersBasicInfoSubDivForAgeWiseDetls table th a,#votersBasicInfoSubDivForLclCastSts table th a,#votersBasicInfoSubDivForImpFam table th a,#impFamDtls table th a,#impFamilesBasicSubDetails table th a,#impFamilesnfoForHamletByBooth table th a ,#votersBasicInfoSubDiv table th a,#localCastStatsTabContent_body table th a,#localCastStatsTabContent_subbody1 table th a,#votersByLocationTabContentDiv_body table th a,#votersByPanchayatTabContentDiv_body table th a,#InfluencingPeopleDetailsDiv table th a,{
color:#333333;
}

#votersByPanchayatTabContentDiv_body,#votersByLocationTabContentDiv_body,#InfluencingPeopleDetailsDiv
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
/*"te
table.impTableDiv {background-color:transparent;border-collapse:collapse;width:100%;}
table.impTableDiv th, table.impTableDiv td {text-align:center;border:1px solid black;padding:5px;}
table.impTableDiv th {background-color:AntiqueWhite;}
table.impTableDiv td:first-child {width:50%;} */

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
#impfamilydatatable,#impfamilydatatable1{
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
		font-size:1.1em;
		font-family:arial;
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
.noteDiv {
    margin-bottom: 18px;
    margin-top: 29px;
}
#impfamilydatatable th{
  padding:5px;
}
.localCastDetailsHeadingDiv,#partyWiseTitle,#partyBasicInfoStatsTabNewTitle{ 
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
    width: 400px;
    }
	#localCastChatDiv,#partyWiseChatDiv{clear: both;
    float: right;
     width: 500px;}
	 .partyWiseDetailsMainDiv{ 
    clear: both;
    display: table;
     margin-left: 10px;
    
    width: 926px;}
	#localCastDetailsDiv p,#partyWiseDetailsDiv p{font-size:13px;font-weight:bold;}
	#createNewGroupId:hover,#flagId:hover{background:#F61D50;color:#FFF;}
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

	#votersByLocationTabContentDiv_body table th,#InfluencingPeopleDetailsDiv table th {padding:5px !important;}
	#votersByLocationTabContentDiv_body,#InfluencingPeopleDetailsDiv{padding-left: 2px !important; }
	#votersByLocationTabContentDiv_body table td ,#InfluencingPeopleDetailsDiv table td{padding-right:3px !important;}
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
    width: 550px;}
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
/*	#previousEleVotingTrendsDiv p span {display: table-cell;}
	#prevVotTrendHeadingSpan{background:#06ABEA;color:#FFF;
	font-weight:bold;font-size:15px;
	border-radius: 3px;
    padding: 3px 10px;}
	#previousEleVotingTrendsDiv {margin-top: 30px;margin-bottom:25px;width:96%;}
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
    padding: 8px 8px 8px 10px;
}*/
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
.customMenu {display:none;width:102%;clear:both; margin: 5px -20px;
    padding: 5px 11px;background:#3198B6;}
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
		width:80%;
  }
  #votersDiv1,#votersDiv2,#casteDiv{
    display:none;
  }
  #partyWiseDetailsDiv{
     margin-left:42px;
  }
  #showAjaxImgForNews{ 
    margin-left: 340px;
    margin-top: 110px;
   }
 #problemsShowDIV h6 {
    color: #005580;
    margin-bottom: 10px;
}
#problemsShowDIV p {
    margin-left: 16px;
}
#problemsShowDIV .widget-block {
    display: inline-block;
    width: 100%;
	background:#FAFAFA;
}
#problemsShowDIV .widget {
    margin-bottom: 10px;
	background: none repeat scroll 0 0 #FAFAFA;
    border-top: 5px solid #000000;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
    margin: 5px 0 20px;
    padding: 0 20px 20px;
    position: relative;
}

#cnstHeading,#votersTitle ,#cnstHeading2{
	background:#E6E6E6;
    border-radius: 6px 6px 6px 6px;
    color: #000000;
    font-size: 18px;
    font-weight: bold;
    height: 25px;
    padding: 9px;
    text-align: center;
    width: 98%;
}

#problemsShowDIV h6{margin:0px;padding:0px;font-size:15px; text-decoration:none; font-weight:bold;text-transform:capitalize;}
#problemsShowDIV a,#problemsShowDIV span{text-decoration:none;font-size:12px;}
#problemsShowDIV .widget-block:nth-child(2n+1){background:#fff;}
.leftmargin{margin-left:10px;padding:4px;}
.pagenationStyle{
  background-color:#2E9AFE;color:#fff;padding:5px;border-radius:3px;
}
.paginatorElmtClass a{padding:5px;}
.table thead.info th,.impFamilesMainDiv th,#censusTab th{background:#d9edf7; color:#454545;}
#tableDiv1 th{text-align:center;width:14%;}
#tableDiv1 td{text-align:center;}
.whitegloss h5.whitegloss{margin: 0px -20px; padding: 10px 10px 10px 20px;clear:both;}
.FamiliyList li{margin:5px;font-weight:bold;font-size:14px;padding:6px;width:100%;}
#voterDetailsNote{
  margin-bottom:5px;
}
#MainHeading span{font-family:verdana,font-weight:bold;clear:both;display: block;font-size: 14px;margin:1px;}
.widget h4, h2{font-family:arial;}
#localCastStatsTabContent_header span{padding:10px;clear:both;display:block;}
/*#subHeading{ color: #4682B4;background:#EEEEEE;margin-left:-4px;border-radius:5px;}*/
#subHeading{color: #4682B4;}
.crossVotingTableCls{width:100%;margin-top:20px;margin-bottom:15px;}

/* #crossVotingReportDiv table{border: 1px solid #D3D3D3;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    padding: 10px;
    width: 100%;}
#crossVotingReportDiv table th{
	background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
	}

#crossVotingReportDiv table td{
	 color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}

#crossVotingReportDiv table tr:nth-child(2n-1) {
    background: none repeat scroll 0 0 #EDF5FF;
}
*/
.crossVotingHeadingCls{margin-top: 10px; margin-bottom: 20px;}
.crossVotingHeadingCls span{background: none repeat scroll 0 0 #06ABEA;
    border-radius: 3px 3px 3px 3px;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    padding: 3px 15px;}
		
	#votersInfoAjaxImg{right:2px;bottom:16px;position:absolute;}

#voterAgeAngGenderwiseDetailsNoteInPercent .table th, .table td{padding:6px;}

#voterAgeAngGenderwiseDetailsInPercent th{text-align:center;}
#ageAndgenderWiseDetails th,#agewiseDetails th,#tableDiv th{text-align:center;}
#crossVotingReportBtn{margin-left: 25px;}
#electionYearsForCrossVoting{margin-right: 5px;}
#previousEleVotingTrendsDiv{
	width:100%;
  overflow-x:scroll;
}

#voterCasteAjaxImg{clear: both; display: block; margin-left: auto; margin-right: auto; float: none;}
.dd_menu {padding:0px; margin:0; list-style-type:none;}
.dd_menu li {float:left;display:inline;}
.dd_menu li a {padding:0px 20px; display:block; color:#fff; text-decoration:none; font:12px arial, verdana, sans-serif; font-weight: bold;}
.dd_menu li:hover a {text-decoration:underline;}

.dd_menu ul {position:absolute; left:-9999px; top:-9999px; list-style-type:none;}
.dd_menu li:hover {position:relative; background:none;}
.dd_menu li:hover ul {left:-160px; top:15px; padding:3px; border:1px solid grey; width:208px;}
.dd_menu li:hover ul li {border:none;}
.dd_menu li:hover ul li a {height:18px;color:#003366; padding:5px 0px; display:block; font-size:11px; width:208px; line-height:18px; text-indent:5px; color:#444; background:#d0e0ea; text-decoration:none; border:1px solid transparent;}
.dd_menu li:hover ul li a:hover {height:18px; background:#c4d8e6; color:#003366; border:solid 1px #444;}

#partyGraphButtonId
{
float: right;
    margin-right: -548px;
    margin-top: -4px;
}
.yui-skin-sam .yui-dt-liner {
    margin: 0;
    padding: 4px 10px;
    width: 65px;
}
/* start anil*/
 #tabContainer {
	/*width:300px;
	padding:15px;
	background-color:#2e2e2e;*/
	-moz-border-radius: 4px;
	border-radius: 4px; 
} 

.tabs{
	height:30px;
}
.muncipalitytabs{
height:30px;
}

.tabs > ul{
	font-size: 1em;
	list-style:none;
}
.muncipalitytabs > ul{
	font-size: 1em;
	list-style:none;
}

.tabs > ul > li{
	margin:0 2px 0 0;
	padding:7px 10px;
	display:block;
	float:left;
	color:#FFF;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
	-moz-border-radius-topleft: 4px;
	-moz-border-radius-topright: 4px;
	-moz-border-radius-bottomright: 0px;
	-moz-border-radius-bottomleft: 0px;
	border-top-left-radius:4px;
	border-top-right-radius: 4px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 0px; 
	background: #C9C9C9; /* old browsers */
	background: -moz-linear-gradient(top, #0C91EC 0%, #257AB6 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, 
		color-stop(0%,#0C91EC), color-stop(100%,#257AB6)); /* webkit */
}


.muncipalitytabs > ul > li{
	margin:0 2px 0 0;
	padding:7px 10px;
	display:block;
	float:left;
	color:#FFF;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
	-moz-border-radius-topleft: 4px;
	-moz-border-radius-topright: 4px;
	-moz-border-radius-bottomright: 0px;
	-moz-border-radius-bottomleft: 0px;
	border-top-left-radius:4px;
	border-top-right-radius: 4px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 0px; 
	background: #C9C9C9; /* old browsers */
	background: -moz-linear-gradient(top, #0C91EC 0%, #257AB6 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#0C91EC), color-stop(100%,#257AB6)); /* webkit */
}

.tabs > ul > li:hover{
	background: #FFFFFF; /* old browsers */
	background: -moz-linear-gradient(top, #FFFFFF 0%, #F3F3F3 10%, #F3F3F3 50%, #FFFFFF 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFFFFF), color-stop(10%,#F3F3F3), color-stop(50%,#F3F3F3), color-stop(100%,#FFFFFF)); /* webkit */
	cursor:pointer;
	color: #333;
}

.muncipalitytabs > ul > li:hover{
	background: #FFFFFF; /* old browsers */
	background: -moz-linear-gradient(top, #FFFFFF 0%, #F3F3F3 10%, #F3F3F3 50%, #FFFFFF 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFFFFF), color-stop(10%,#F3F3F3), color-stop(50%,#F3F3F3), color-stop(100%,#FFFFFF)); /* webkit */
	cursor:pointer;
	color: #333;
}
#tabHeader_1
{
margin-left:10px;
}

.tabs > ul > li.tabActiveHeader{
	 background: #FFFFFF; /* old browsers */
	/*background: -moz-linear-gradient(top, #FFFFFF 0%, #F3F3F3 10%, #F3F3F3 50%, #FFFFFF 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFFFFF), color-stop(10%,#F3F3F3), color-stop(50%,#F3F3F3), color-stop(100%,#FFFFFF)); */ /* webkit */
     	cursor:pointer;
	color: #333;
}
.muncipalitytabs > ul > li.tabActiveHeader{
	 background: #FFFFFF; /* old browsers */
	/*background: -moz-linear-gradient(top, #FFFFFF 0%, #F3F3F3 10%, #F3F3F3 50%, #FFFFFF 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFFFFF), color-stop(10%,#F3F3F3), color-stop(50%,#F3F3F3), color-stop(100%,#FFFFFF)); */ /* webkit */
     	cursor:pointer;
	color: #333;
}
/*
.tabscontent {
	-moz-border-radius-topleft: 0px;
	-moz-border-radius-topright: 4px;
	-moz-border-radius-bottomright: 4px;
	-moz-border-radius-bottomleft: 4px;
	border-top-left-radius: 0px;
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
	border-bottom-left-radius: 4px; 
	padding:10px 10px 25px;
	background: #FFFFFF; /* old browsers */
	background: -moz-linear-gradient(top, #FFFFFF 0%, #FFFFFF 90%, #e4e9ed 100%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFFFFF), color-stop(90%,#FFFFFF), color-stop(100%,#e4e9ed)); /* webkit */
	margin:0;
	color:#333;
}*/
.dataTables_length
{
	margin-left:16px;
}
.table-bordered {
    border-collapse: separate;
}
.ui-widget {
font-family: verdana;
}

.btn-mini {
    border-radius: 3px 3px 3px 3px;
    font-size: 10.5px;
    margin-left: 63px;
    padding: 1px 6px;
}
.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 6px 0;
    margin-left: 183px;
    margin-top: 48px;
    white-space: nowrap;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
    
}
</style>
<style>



#sse2
{
    /*You can decorate the menu's container, such as adding background images through this block*/
    background-color: #0088CC;
    height: 38px;
    padding: 15px;
    border-radius: 6px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
}
#sses2
{
    margin:0 auto;/*This will make the menu center-aligned. Removing this line will make the menu align left.*/
}
#sses2 ul 
{ 
    position: relative;
    list-style-type: none;
    float:left;
    padding:0;margin:0;
}
#sses2 li
{
    float:left;
    list-style-type: none;
    padding:0;margin:0;background-image:none;
}
/*CSS for background bubble*/
#sses2 li .highlight
{
    background: #003366 url("./styles/images/rak.gif") no-repeat center bottom;; 
    top:0px;
    height:30px;
    border-radius: 8px;
    -moz-border-radius: 8px;
    -webkit-border-radius: 8px;
    z-index: 1;
    position: absolute;
    overflow:hidden;
}
#sses2 li a
{
    height:30px;
    padding-top: 8px;
    margin: 0 20px;/*used to adjust the distance between each menu item. Now the distance is 20+20=40px.*/
    color: #fff;
    font: normal 14px arial;
    text-align: center;
    text-decoration: none;
    float: left;
    display: block;
    position: relative;
    z-index: 2;
}
highlight11
{
    background: #003366 url("./styles/images/rak.gif") no-repeat center bottom ;
    top:0px;
    height:30px;
    border-radius: 8px;
    -moz-border-radius: 8px;
    -webkit-border-radius: 8px;
    z-index: 1;
    position: absolute;
    overflow:hidden;
}

li > p{
color:#2A4F97;
}
#censusTab td{color:#000;}
#censusReportDiv h4{
    font-size: 14px;
    color:#000;
	font-size:14px;
}
#censusTab{clear: both;margin-bottom: 10px;
    margin-top: 8px;}
	#censusReportMainDiv{padding-bottom:1px;}

.votersInfoCls{background: #003366;  border-radius: 8px;
    padding-left: 15px;
    padding-right: 15px;}

	#sses2 ul{margin-left: 210px;}
	#castPopupShowBtn{margin-right: 3px;}
	#customGroupAgeLink{margin-right: 10px;}
	.middleNav{width:223px;}
	#customGroupsDiv,#voterGroupsDiv,.middleNav,#forHide{display:table;}
	
	#partialBoothsDiv{
	 background-attachment: scroll;
    background-clip: border-box;

    background-image: none;
    background-origin: padding-box;
    background-position: 0 0;
    background-repeat: repeat;
    background-size: auto auto;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    font-weight: bold;
    margin-top: 0;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 5px;
	}
	
	#partialBoothMainDiv ul, #partialBoothMainDiv ol, #partialBoothMainDiv li {
    color: #000000;
    font-weight: normal;
    list-style-type: decimal;
	}
</style>

</SCRIPT>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
var populateConstituencyId = '${constituencyId}';
var selType = '${type}';
var populateMandalId = '${mandalId}';
var populatePanchayatId = '${panchayatId}';
var populateBoothId = '${boothId}';
	var votersLimitExist = false;
	var pattern= /^(hamlet|customWard)$/i;
	var  muncipalityPattern = /MUNCIPALITY/i;
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

$(document).ready(function(){


$(".votersInfoLi2").on("click","mouseover",function(){
	
	$(".votersInfoLi2").removeClass("votersInfoCls");
	$(this).addClass("votersInfoCls");

});


	$('#constituencyList').val(0);
	$("#newsCountDiv").css("display","none");
	$("#problemsCountDiv").css("display","none");
	$("#InfluencingPeopleCountDiv").css("display","none");
$(".dateField").live("click", function(){
$(this).datepicker({
dateFormat: "dd/mm/yy",
changeMonth: true,
changeYear: true,
maxDate: new Date()

}).datepicker("show");
});
$('.castDivs').hover(
    function () {
        $(this).css({"background-color":"slategrey"});
    }, 
    function () {
        $(this).css({"background-color":"#49AFCD"});
    }
);
});


</script>
</head>
<body>
<div style="min-height: 385px;">
<div id="problemOuterDiv">
<div id="problemInnerDiv">
</div>
</div>

<div id="influencePeopleOuterDiv" >
  <div id="influencePeopleInnerDiv" style="border: 1px solid black;border-radius: 4px 4px 4px 4px;margin-top: 11px;padding: 10px;display:none;"></div>
  <div id="totalCountId" style="display:none;"></div>
  <div id="searchResultsDiv"  class="yui-skin-sam yui-dt-sortable" style="display:none;border: 1px solid black;border-radius: 4px 4px 4px 4px;margin-top: 11px;padding: 10px;"></div>
</div>

<!--DISPLAY NEWS GALLARIES START-->
 <div id="showContentDiv">
    <div id="showAjaxImgForNews" style="display:none"><img src="images/icons/goldAjaxLoad.gif"/></div>
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
<div id="votersouterDiv" class="widget green">
<!--<fieldset>-->

<div id='MainHeading'><h3 style="font-size: 19.5px;font-family:verdana;"><b>GET YOUR CONSTITUENCY'S COMPLETE INFORMATION UP TO BOOTH LEVEL.</b></h3><h4 style="font-weight:normal;border:0px;text-transform:none;">Please select your Constituency and Publication Date to view Constituency Wise Analysis.</h4></div>

<div id="categoeryCreationDiv" style="float:right;">
	
	<a href="javascript:{}" class="btn btn-default" id="createNewGroupId" onclick="openNewWindow();">Create Custom Groups</a>
	<a href="javascript:{}" class="btn btn-default" id="flagId" onclick="openFlagWindow();">Flag Maintenance</a>
</div>
<div id="AlertMsg"></div>
<div id="ConstituencyDiv" class="selectDiv">
	 Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDate();getConstituencyResults(this.value);getUserVoterCategories();"/> &nbsp;&nbsp;

		
	 Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
	<img id="publicationAjaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;float:right;margin-right:165px;"/>
	
	 <div id="refreshImg"  style="display:none;float:right;margin-right:160px;">
	<a id="refreshId" href="javaScript:{refreshConstituencyData();}"><img src="./images/icons/refreshImg.png" alt="Processing Image" title="Click Here To See Constituency Level Data"/></a>
	</div>
	</div>
	
	
	
	<div id="ajaxImageDiv" style="display:none; position: absolute; right: 2px;top:200px;
    z-index: 100;"><img src="./images/icons/search.gif" alt="Processing Image"/> </div>
	<!--</fieldset>-->

</div>

	<div id="constituencyInfo">
		<div id="constituencyResults"></div>
		<div id="constituencyPageElectionImgDiv"></div>
	</div>
<!-- <div id="defaultWidth" style="min-height:300px;"></div>
for  body3 start    result  -->
<div id="votersDiv3" >





<div id='votersHeaderTopDiv3' style="clear:both;position:relative;">
		<div class="widget blue">
		
		<h4 id="reportLevelheading" class=""> </h4>
		
		
 
 <div id="reportLevelCountDiv"> 
 </div>
 
 
 <div class="ShowHideLevelMenu" id="showHideDiv" style="display:none;">
<a class="btn" href="javascript:{}" id="ShowMenu">Show Menu <i class="icon-chevron-down"></i></a>
 </div>
 
 <div class="customMenu" id="CustomMenu"><!-- Menu Start-->

<!-- Left Nav -->
<div class="span3 leftNav col-md-3">
<div class="leftNav-Mandals"> <h5 class="breadcrumb" style="margin-bottom:0px;">Mandals</h5>
<ul id="leftNav-Mandals-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
</div>
<div class="leftNav-Municipalities"> <h5 class="breadcrumb" style="margin-bottom:0px;">Municipalities</h5>
<ul id="leftNav-Municipalities-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
</div>
</div>
<!-- Left Nave End-->
 <!-- Middle Nav -->
<div class="span3 middleNav col-md-3" style="margin-left:10px;display:none;">
	<div class="middleNav-Panchayats">
		<h5 class="breadcrumb" style="margin-bottom:0px;">Panchayat(s)</h5>
		<ul id="middleNav-Panchayats-list" class="nav nav-list bs-docs-sidenav nav-stacked"></ul>
	</div>
	<div class="middleNav-Wards">
		<h5 class="breadcrumb" style="margin-bottom:0px;">Ward(s)</h5>
		<ul id="middleNav-Wards-list" class="nav nav-list bs-docs-sidenav nav-stacked">	
		
		
		</ul>
	</div>
</div>
<!-- Middle End-->


<!-- Middle Nav -->
<div class="span3 col-md-3" style="display:none;margin-left:10px;box-shadow:0px 1px 2px #aaa;background:#fff;border-radius:2px;" id="customGroupsDiv">
	<div class="middleNav-Panchayats middleNav-CustomGroups">
		<h5 class="breadcrumb" style="margin-bottom:0px;">Custom Voter Group (s)</h5>
		<ul id="middleNav-CustomGroups-list" class="nav nav-list bs-docs-sidenav nav-stacked disableClass">		
		
		 </ul>
	</div>
	
</div>
<img src="./images/icons/search.gif" id="voterGroupMandalAjaxImage" style="display:none;"/>
<!-- Middle End-->


<!-- Right Nav -->
<div class="col-md-6">
<div id="tabContainer"  >
  <div class="tabs">
      <ul>
	     <li id="tabHeader_1"  >
           
			     <p>Hamlet(s)</p>
			   <!--  <ul id="rightNav-Booths-list" class="anils"></ul> -->
	      
       </li>
	  
	  <li id="tabHeader_2" >
      
			<p>Booth(s)</p>
			 <!--  <ul id="rightNav-Booths-list" class="anils"></ul> -->
	
	</li>

     </ul>
  </div>
</div>
 <div class="tabscontent">
 <div class="span6 col-md-6 rightNav" id="tabpage_1">
 <ul id="rightNav-Booths-list1" class="anils"></ul>
 </div>
 <div class="span6 col-md-6 rightNav" id="tabpage_2">
 <ul id="rightNav-Booths-list2" class="anils"></ul>
 </div>
 </div>
 </div>
 <!--<div class="span6 rightNav" id="tabpage_3">
 <ul id="rightNav-Booths-list"></ul>
 </div> -->

 <!-- MODIFIED BY SAMBA START -->
 <!--<div class="span6 rightNav" id="forHide">
	<div class="rightNav-Booths">
			<h5 class="breadcrumb" style="margin-bottom:0px;">Booth(s)</h5>
			<ul id="rightNav-Booths-list"></ul>
	</div>
	
</div>-->
<div class="col-md-6">
<div id="tabContainer1"  style="display:none;">
  <div class="muncipalitytabs">
      <ul>
	     <li id="tabHeader_3" style="margin-left:10px;" class="tabActiveHeader">           
			<p>Booth(s)</p>	  
	  <li id="tabHeader_4">      
		  <p>Custom Voter Group(s)</p>	
	  </li>
     </ul>
	 <img src="./images/icons/search.gif" id="voterGroupMuncipalityAjaxImage" style="display:none;"/>
  </div>
</div>

<div class="span6 rightNav" id="forHide" style="display:none;">
	<div class="rightNav-Booths">
			<!--<h5 class="breadcrumb" style="margin-bottom:0px;">Booth(s)</h5>-->
			<ul id="rightNav-Booths-list"></ul>
	</div>
	
</div>
</div>

<div class="span6 rightNav" id="voterGroupsDiv" style="display:none;">
	<div class="rightNav-cutomGroups" style="display:none;">
			<!--<h5 class="breadcrumb" style="margin-bottom:0px;">Booth(s)</h5>-->
			<ul id="rightNav-votergroups-list">
			</ul>
	</div>
	
</div>

 <!-- MODIFIED BY SAMBA END -->


<!-- RIGHT End-->

</div><!-- Menu End-->

	</div>
	

	
 
 <!--<h5 id="reportTopLevelheading" style="margin-left:15px;"></h5>-->
<div id='votersHeaderDiv3' class="widget green" style="display:none;">
	<div class="votersWidgetMainHeader"><span id="votersTitle-unused" class="votersWidgetHeader_span"> </span></div>
	</div>

<div id="votersBasicInfoBtnDiv" style="background:#f0f0f0" > 
	<span></span>
	<span value="Mandal Wise Voters Info" id="votersShareBtn1"></span>

	<span value="Mandal Wise Voters Info" id="votersShareBtnForCstmVtrGrp"></span>
	<span style="margin:7px;">
		<span id="cnstHeading2" class="cutomVoterGroup" style="background:#f0f0f0;border-radius:0px;text-align:center;position:relative;display:none;">CUSTOM VOTER GROUP WISE VOTERS INFO<span id="votersShareBtnsForCstmVtrGrp" class="btn" title="Click Here to know Custom Voter Group Wise Voters Info " style="margin-left: 15px;font-weight:bold;">Show</span></span>
	</span>
</div>  
<div id='votersMainOuterDiv3' class="" style="display:none;border-top:none;">
<!--<div id="votersTitle"><h2  style="width:102%;padding:10px;"></h2></div>-->
<div id="reportLevelCountDiv1" style="clear:both;padding:10px 0px;"></div>
<div id="partialBoothMainDiv" class="widget blue whitegloss" style="display:none;">
	  <div id="partialBoothDiv"></div>
	</div>

<div id="votersBasicInfoMainDiv" style="display:none;">
	<div id="votersBasicInfoMsgDiv"></div>
	<div id="votersBasicInfoSubChartDiv"></div>
	</br>
	<div id="assAndUnass"></div>
	<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable"></div>
	
</div>
 
  
 <div id="crossVotingMainDiv" style="display:none;">
	 <div class="crossVotingHeadingCls">
	   <span>Cross Voting Report</span>
	 </div>
	 <div id="crossVotingErrorMsgDiv"></div>
	 <div> 
	   Election Year : <select id="electionYearsForCrossVoting"></select>
	   <img src="./images/icons/search.gif" id="crossVotingEleyearAjaximg" style="display:none"/>
	   Party : <select id="PartySelect">
	           <option value="0">Select </option>
	           </select>
	   <img src="./images/icons/search.gif" id="crossVotingPartyAjaximg" style="display:none"/>
	   <input id="crossVotingReportBtn" type="button" value="cross Voting Report" class="btn btn-info" />
	 </div>
		
  </div> 

<!-- Not in USe -->

<div id="scrollToHere"></div>	
<h5 id="reportLevelheading1" style="display:none;"></h5> <!-- Not in USe -->
<div id="selectedBoothInfo" style="color:#000000;padding-bottom:5px;" class="widget blue whitegloss"></div>
<div class="widget blue" id="cadre-block">
<div class="widget-block" style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;">
<h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;">Cadre Information</h4>
<div id="cadreCount" style="margin-top:10px;"></div>
</div></div>
<!--<div class="widget blue">
<!--<div class="widget-block" style="margin-top: 0px; clear: both; display: block; height: 35px;padding-bottom:1px;">
<h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:500px;color: black;">News Glance,Problems and Influencing People Details</h4>
<a id="ShowConstMenu1" class="btn pull-right" style="margin-top:-43px;margin-right: 28px;" href="javascript:{}" >Show<i class="icon-chevron-up"></i></a>

<div id="allAjaxImg" style="display:none;float:right;margin-top:-27px;">
	<img src="./images/icons/search.gif" /></div>

</div>


	<div id="newsCountDiv" class="widget blue whitegloss" style="display: inline-block; width: 101%;color:#000000; margin-top: 51px;"></div>-->
	
	   <!--<div id="newsDisplayOuterDiv">
		<div id="newsDisplayDiv" style=""></div>
	   </div>-->
	  <!--<div id="custom_paginator_class" class="paginatorElmtClass" style="margin-top:10px;margin-left:20px;margin-bottom: 30px;"></div> -->
	<!-- <div id="problemAjaxImg" style="display:none;float:right;">
	<img src="./images/icons/search.gif" /></div>
	
	<div id="problemsCountDiv" class="widget blue whitegloss">
	
	</div>

	<div id="InfluencingPeopleAjaxImg" style="display:none;float:right;">
	<img src="./images/icons/search.gif" /></div>
	<div id="InfluencingPeopleCountDiv" class="widget blue whitegloss" style="">
	 </div>
	 
	 </div>-->
	<div id="censusReportMainDiv" class="widget blue whitegloss">
	  <div id="censusReportDiv"></div>
	</div>



	<div id="problemPopUp" style="background:#EEEEEE;">
	
	<div id="problemsShowDIV">
	 
	</div>

	<div id="custom_paginator_class" class="paginatorElmtClass" style="margin-top:10px;margin-left:20px;margin-bottom: 30px;"></div>
		
	</div>
	<!--<div id="InfluencingPeopleAjaxImg" style="display:none;float:right;">
	<img src="./images/icons/search.gif" /></div>
	<div id="InfluencingPeopleCountDiv" class="widget blue whitegloss">
	 </div>-->
	<div id="influencyPopupDiv">
	<div id="InfluencingPeopleDetailsDiv" class="yui-skin-sam yui-dt-sortable"></div>
	 </div>


	 <div  id="votersBasicInformationDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;">Voters Basic Information</h4>
	<img src="./images/icons/search.gif" alt="Processing Image" id="basicDetailsAjax" style="display:none;position:absolute;top:20px;right:20px;"/>
	<div id="votersBasicInfoDiv1"></div>
	<div id="votersBasicInfoDiv"></div>
	<div id="votersInfoMoreShowHide" style="display:none;"><span class="btn btn-info pull-right" href="javaScript:{};" onclick="getVotersDetails();" style="margin-top:5px;">Click here for Voters Details</span></div>



  </div>

  <div id="votersCountVaryDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;">View Added / Deleted Voter info Between Publications</h4>
	<div style="margin-top:10px;" class='breadcrumb'>
		Previous Publication Date<font class="requiredFont">*</font> <select id="prevpublicationDateIdsList" class="selectWidth" style="width:172px;height:25px;" name="prevpublicationDateList" >
		</select>
		
		Present Publication Date<font class="requiredFont">*</font> <select id="prespublicationDateIdsList" class="selectWidth" style="width:172px;height:25px;" name="prespublicationDateList">
		</select>
		<span class="btn" id='voterDetailedReportId'>View Detailed Report</span>
	<div id="errorMsgDiv"></div>	<!--onClick="getModifiedVotersCountBetweenPublications(previousPublication,presentPublicationId,locationValue,locationType)"-->
	</div>
	
	<span id="votersCountModifyAjaxDiv" style="display:none;position:absolute;top:10px;right:2px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
  </div>

	   <div id="previousEleVotingTrendsDiv1" class="widget blue whitegloss" style="display: none; width: 96%; color: rgb(0, 0, 0);">
	   <div style="float: right; margin-top: 11px; margin-bottom: -30px;"><img src="images/icons/search.gif" id="previousEleAjaxImg" style="display:none;"/></div>

	   <div id="previousEleVotingTrendsDiv" class="widget-block" style="margin-top:-6px;clear:both;"></div>


	   <div id="previousEleVotingTrendsChatDiv"></div>
	   </div>
	<!--<div id="showHideLink" style="float:right;margin-right:15px;"><a href="javaScript:{showHideDiv();}">Show/Hide News Details</a></div>-->
 	<div id="newsDiv" style="clear:both;">
  	</div>

	<div id="casteDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
	<h4 class=""  id="localCastStatsTabContentTitle" ></h4>
	<!--<input type="button" id="lclCastStsShowBasicInfo" class="buttonStyle" value="View Basic Voter Details" style="margin-top:5px;"/>
    <div id="votersbasicinfoForLclCastSts" >
        <div id="votersBasicInfoDivForLclCastSts"></div>
	    <div id="votersBasicInfoSubChartDivForLclCastSts"></div>
	    <div id="votersBasicInfoSubDivForLclCastSts" class="yui-skin-sam yui-dt-sortable"></div>	
    </div>-->
	
	<span id="castewiseAjaxDiv" style="display: block; position:absolute;top:10px;right:20px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
	<div id='LocalCastDiv' style="margin-bottom:65px;">
	<!--<div id ="localCastStatsTabContentTitle" ></div>-->
	<div style="margin-left:695px;">
	<ul id="casteDivs">
    <li>
		<input type="button" value="Refresh Caste And Party Information" class="btn btn-info" style="width:240px;margin-top: 10px;"/>		
        <ul>
		<li><a class="castDivs" href="javascript:{}" onclick="getUpdatedCastePartyInfo();" style="background:#49AFCD;width:200px;color: rgb(255, 255, 255);"><span style="margin-left:-15px;" title="click here to get latest updated Caste and Party Information">Get Updated  Caste And Party Info</span></a>
		</li>
         <li><a id="permanentlyUpdateDiv" class="castDivs" href="javascript:{}"onclick="permanentlyUpdateCastePartyInfo();" style="background:#49AFCD;width:200px;color: rgb(255, 255, 255);"><span style="margin-left:-15px;" title="click here to get latest Caste and Party Information and Save this data">Update Caste And Party Info</span></a>
		 </li>            
        </ul>
    </li>    
	</ul>
	</div>
	
	<!--
	<div style="float:right;">
	   <div id="permanentlyUpdateButtonDiv" ><input id="permanentlyUpdateDiv" type="button" onclick="permanentlyUpdateCastePartyInfo();" value="Update Caste And Party Info" class="btn btn-info" /></div>
	   <div id="casteRefreshButtonDiv" style="margin-top: 10px;"><input type="button" onclick="getUpdatedCastePartyInfo();" value="Get Updated Caste And Party Info" class="btn btn-info" /></div>
	</div>
	-->
	<div id="localCastDetailsHeadingDiv" class="localCastDetailsHeadingDiv" style="margin-bottom: 0px;"></div>
	<!-- 
	<div id='localCastStatsTabContent_header' style="width:48%;float:left;"></div>
	<div class="castDetailsMainDiv" style="width:48%;float:left;">
		<div id="localCastDetailsDiv" style="margin-bottom:0px;"></div>
		<div id="localCastChatDiv"></div>
	</div>
	-->	
	<div id="castInfoDiv">
	<table style="width:950px;">
	 <tr>
	 <td style="width: 308px;">
	<div id='localCastStatsTabContent_header' ></div>
	</td><td style="width:180px;">
	<span id="localCastDetailsDiv" ></span>
	</td><td style="width:450px;">
	<span id="localCastChatDiv"  style="position:inherit;max-width:450px;"></span>
	</td>
	</tr>
	</table>
	</div>
	<div id="castContainerChart">
		<div><input type='button' id='hideshow' value='Hide/Show Caste Wise Analysis Chart' class="btn btn-inverse pull-right" style="margin-top:10px;margin-bottom:10px;margin-right:10px;"></div>
		<div id="castContainerChartInner" style="border:1px solid;">
		<div id="rangeSliderDiv" style="width:500px;margin-left:auto;margin-right:auto;border:1px solid #ccc;padding:5px 20px;margin-top:50px;" >
			<h5 style="text-align:center;">Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
			<div id="slider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a>
			</div>
				<p style="padding-bottom:2px;">
					<input type="text" id="amount" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
				</p>
		</div>
		<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto;"></div>
		</div>
	</div>
	<div id='localCastStatsTabContent_body' class="yui-skin-sam yui-dt-sortable" style="margin-top:10px;margin-bottom:20px;">	</div>
	<div id='castTab'></div>
	<div id='localityTab'></div>

	<span id="" class="cutomVoterGroup" style="display:none;"><input id="customGroupCasteInfo" type="button" class="btn btn-info" value="Custom Voter Group Wise Caste Info " style="margin-top:7px;margin-bottom:5px;margin-left:429px;float:left;" onclick="showCasteWiseDetailsForCustomVoterGroup();"/></span>




<span id="castPartyPopupShowBtn"><input id="castPopupShowBtn" type="button" class="btn btn-info" value="View More Details About Caste Statistics and Party Wise Voters Details" style="float:right;margin-top:7px;margin-bottom:5px;" onclick="showCastPartyWindow();"/></span>
	</div>
	</div>
	
	<!--<div  id="votersBasicInformationDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;">Voters Basic Information</h4>
	<img src="./images/icons/search.gif" alt="Processing Image" id="basicDetailsAjax" style="display:none;position:absolute;top:20px;right:20px;"/>
	<div id="votersBasicInfoDiv1"></div>
	<div id="votersBasicInfoDiv"></div>
	<div id="votersInfoMoreShowHide" style="display:none;"><span class="btn btn-info pull-right" href="javaScript:{};" onclick="getVotersDetails();" style="margin-top:5px;">Click here for voters details</span></div>

  </div>-->

 <!-- <div id="votersCountVaryDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;">View Added / Deleted Voter info Between Publications</h4>
	<div style="margin-top:10px;" class='breadcrumb'>
		Previous Publication Date<font class="requiredFont">*</font> <select id="prevpublicationDateIdsList" class="selectWidth" style="width:172px;height:25px;" name="prevpublicationDateList" >
		</select>
		
		Present Publication Date<font class="requiredFont">*</font> <select id="prespublicationDateIdsList" class="selectWidth" style="width:172px;height:25px;" name="prespublicationDateList">
		</select>
		<span class="btn" id='voterDetailedReportId'>View Detailed Report</span>
	<div id="errorMsgDiv"></div>-->	<!--onClick="getModifiedVotersCountBetweenPublications(previousPublication,presentPublicationId,locationValue,locationType)"-->
	<!--</div>
	
	<span id="votersCountModifyAjaxDiv" style="display:none;position:absolute;top:10px;right:2px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
  </div>-->
<!--<div id="votersCountVaryDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;">Voters Count Vary between Publication Dates</h4>
	<div style="margin-top:10px;" class='breadcrumb'>
		Previous Publication Date<font class="requiredFont">*</font> <select id="prevpublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="prevpublicationDateList" >
		</select>
		
		Present Publication Date<font class="requiredFont">*</font> <select id="prespublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="prespublicationDateList">
		</select>
		<span class="btn" id='detailModifiedVoters'>View Detailed Report</span>
		<!--onClick="getModifiedVotersCountBetweenPublications(previousPublication,presentPublicationId,locationValue,locationType)"
	</div>
	<div id='VoterDiff' style="margin-left:auto;margin-right:auto;width:400px;" class="breadcrumb">
		<span>Added Voters </span>-<span class='btn' id='addedVtrs'></span>
		<span>Deleted Voters </span>-<span class='btn' id='delVtrs'></span>
	</div>
	<span id="votersCountModifyAjaxDiv" style="display:none;position:absolute;top:10px;right:2px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
  </div>-->
	<!--<div id="previousEleVotingTrendsDiv"></div>-->
	<div style="margin-bottom: 20px;" id="revenueVillageWiseElecResults" class="widget blue">
	   <div id="revenueVillageWiseElecIdTitle" style="font-weight:bold;margin-bottom:10px;">Panchayat Wise Results In </div>
	   Select Election Year : <select id="revenueVillageWiseElecId"></select><input style="margin-left:5px;" type="button" onclick="openwindowForPanchayatsToShowCast();" class="btn btn-info" value="View Panchayt Wise Election Results & Castes"/><input style="margin-left:5px;" type="button" onclick="openwindowForPanchayatsToShow();" class="btn btn-info" value="View Panchayat Wise Election Results"/>
	</div>
	<!-- <div id="votersBasicInfoSubChartDiv" style="border:1px solid black"></div>
	</br>
	<div id="votersBasicInfoSubDiv" style="border:1px solid black" class="yui-skin-sam yui-dt-sortable"></div>-->

<div id="votersOuterDiv1" style="display:none;">
<div id="imgDescriptionDiv" style="margin-bottom: 10px">
<b style="margin-left: 5px">Influencing People</b>:<img title="Politician" alt="Politicion" src="./images/icons/influencing.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Cadre</b>:<img title="Cadre" alt="Politicion" src="./images/icons/cadre.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Politician</b>:<img title="Politician" alt="Politicion" src="./images/icons/politican.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<div id="errorMessageDiv"></div>
</div>
 <div id="votersInnerDiv1" style="height:500px;overflow-y:scroll;">
	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
 </div>
</div>



					
<div id="flagWiseVotersCountDiv" class="widget blue whitegloss" style="color:#222222;display:none;"></div>

	<!--<div  id="votersByPanchayatTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>-->
	<div id="votersDiv4" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;margin-top:0px;">
					<h4 id="AgeWisetitle" class="" > </h4>
					<a id="refreshIdAge" href="javaScript:{refreshAgeWiseData();}" style="float:right;margin-top: -28px;"><img src="./images/icons/refreshImg.png" alt="Processing Image" title="Click Here To See Age Wise Voters Details"/></a>
					<!-- for  body4 start    result  -->

<div id='votersMainOuterDiv4' class="widget-block whitegloss" style="display:inline-block;width: 100%;">
	

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


<span id="agewiseAjaxDiv" style="display:none;position:absolute;bottom:20px;right:2px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
<div id="voterDetailsNote1" class=""></div>

<div id="tableDiv1"></div>
<input id="ageLinkForHamletBooths1" type="button" value="View Booth Wise Age Details" onclick="showAllAgewiseDetailsForHamlet();" class="btn btn-info" style="display:none;float:right;margin-left:10px;" />
<span id="ageLinkForHamletBooths" style="display:none;float:right;margin-top:10px;margin-left:3px;"><a href="javaScript:{showAllAgewiseDetailsForHamlet()}"  class="btn btn-info">View Booth Wise Age Details</a></span>

<span id="cutomVoterGroupAgeWise" class="cutomVoterGroup" style="margin-left:426px;diaplay:none;float:left;"><a href="javaScript:{showAllAgewiseDetailsForCustomVoterGroup()}"  style="margin-top:10px;" class="btn btn-info">Custom Voter Group Wise Age Details</a></span>
<span id="ageLink" style="display:none;float:right;margin-top:10px;"><a href="javaScript:{showAllAgewiseDetails()}"  class="btn btn-info">View More Details</a></span>
<span id="customGroupAgeLink" style="display:none;float:right;margin-top:10px;"><a href="javaScript:{getcustomGroupAgeInfo()}"  class="btn btn-info">View Voter Group Wise Age Details</a></span>

<div style="display:none;"> <!-- HIDING THE POPUP -->
<div id="ageWiseVotersDetailsOuterDiv">

	<!--<div id='ageWiseInfoDiv' class=""  style="height:500px;overflow-y:auto;">-->
	<div id='ageWiseInfoDiv' class=""  style="height:500px;">
	<br><br>
	<div id="ageWiseVotersBasicInfoSubChartDiv" style="margin-left:100px;" ></div>

	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterDetailsNote" class="noteDiv thumbnail breadcrumb" style="display:none;text-align:center;"></div>
	<div id="tableDiv" style="padding:10px;display:none;overflow-x:scroll" class="voterDetails"></div>
	</div>

	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterAgewiseDetailsNote" class="noteDiv thumbnail breadcrumb" style="text-align:center;color:#3F3636;"></div>
	<div id="agewiseDetails" style="padding:10px;overflow-x:scroll;" class="voterDetails"></div>
	</div>
	
	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterAgeAngGenderwiseDetailsNote" class="noteDiv thumbnail breadcrumb" style="text-align:center;"></div>
	<div id="ageAndgenderWiseDetails" style="padding:10px;overflow-x:scroll;" class="voterDetails"> </div>
	</div>

	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterAgeAngGenderwiseDetailsNoteInPercent" class="noteDiv thumbnail breadcrumb" style="text-align:center;"></div>
	<div id="voterAgeAngGenderwiseDetailsInPercent" style="overflow-x:scroll;padding:10px;" class="voterDetails"></div>
	</div>
	
	<div id="AgeWiseNoteDiv" style="border: 1px solid #D3D3D3;
    display: block;margin-bottom: 14px;margin-left: 9px;    margin-top: 33px;padding: 19px;width: 851px;"></div>

</div>
</div> </div><!-- Hidden Popup-->

<!-- for  body 4 end    result  -->

</div>
					
					
</div>




<!-- for  body 1 start    result  
<HR>-->
<div id="votersDiv1" >
<!--
<div id='votersHeaderDiv1'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="impFamiliesTitle" class="votersWidgetHeader_span" style="top:11px;"> </span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
		
	</div> -->

<div id='votersMainOuterDiv1' class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
<h4 class=""  id="impFamiliesTitle" ></h4>
	 <!--<input type="button" id="impFamShowBasicInfo" class="buttonStyle" value="View Basic Voter Details" style="margin-top:5px;"/>
     <div id="votersbasicinfoForImpFam" >
        <div id="votersBasicInfoDivForImpFam"></div>
	    <div id="votersBasicInfoSubChartDivForImpFam"></div>
	    <div id="votersBasicInfoSubDivForImpFam" class="yui-skin-sam yui-dt-sortable"></div>	
     </div>-->
	 <span id="ImpFamwiseAjaxDiv" style="display: none; position:absolute;bottom:32px;right:2px;float:right;"><img alt="Processing Image" src="./images/icons/search.gif"></span>	

     	<div id="ImportantFamiliesDiv">
	<div id ="impFamilesBasicDetails"></div>
	</br>
	  <!-- <div><input id="impFamiliesMoreInfoButn" type="button" value="View More Details" onclick="getImpFamiliesVotersToShow();" class="btn btn-info" style="float:right;"/>-->
	  
	<div>

	<input id="impFamiliesForHamletsByBooth" type="button" value="View Hamlet Wise Family Details " onclick="getImpFamiliesVotersForHamletBooth1();" class="btn btn-info" style="display:none;"/>
	
		<!-- <input id="impFamiliesForBooths" type="button" value="View Booth Wise Family Details" onclick="getImpFamiliesVotersToShowForBooth();" class="btn btn-info" style="display:none;float:right;margin-left:3px;" />-->
	
	<input id="impFamiliesForBooths" type="button" value="View Booth Wise Family Details" onclick="getImpFamiliesVotersForBooth1();" class="btn btn-info" style="display:none;float:right;margin-left:3px;" />
	
	<input id="customVoterGroupFamilyDtls" type="button" value="Custom Voter Group Wise Family Details" onclick="showFamilyWiseDetailsForCustomVoterGroup();" class="btn btn-info cutomVoterGroup" style="margin-left:400px;display:none;"/>
     <input id="impFamiliesMoreInfoButn" type="button" value="View More Details" onclick="getImpFamiliesVoters();" class="btn btn-info" style="float:right;"/>
	  <!-- <input id="" type="button" value="Test" onclick="impFamilesAllInfoForHamletPopUp();" class="btn btn-info" style="float:right;"/> -->
	   
	   
	   </div>

	   <div id="impFamilesnfoForHamletByBooth" style="display:none;">
       <div id ="impFamilesBasicInfoSubChartDivForHamletsByBooths" style="border:1px solid black"></div>
			<div id ="emprtyData"></div>
		<!--<div id = "assigAndUnassig"></div>-->
		<div id ="impFamilesBasicSubDetailsForHamletByBoothTitle" style="margin-top:20px;margin-bottom:8px;color:steelblue;"></div>	
		<div id ="impFamilesBasicSubDetailsForHamletByBooth" style="border:1px solid black"></div>
		<div id="descriptionDiv1" ></div>
		<div id="impFamPancBothDtlsAgxImgForHamletByBooth" style="display:none;margin-top:15px;text-align:center;"><img src="images/icons/goldAjaxLoad.gif"/></div>
		<div id="importantFamiliesForBooth"></div>
	   </div>
             
       <div id="impFamilesAllInfoForHamletPopUp" style="display:none;">
        <div id ="impFamilesBasicInfoForHamletSubChartDiv" style="border:1px solid black"></div>
			<div id ="emprtyData"></div>
		<div id = "assigAndUnassig"></div>
		<div id ="impFamilesBasicSubDetailsForHamletTitle" style="margin-top:20px;margin-bottom:8px;color:steelblue;"></div>	
		<div id ="impFamilesBasicSubDetailsForHamlet" style="border:1px solid black"></div>
		<div id="descriptionDiv1" ></div>
		<div id="impFamPancBothDtlsAgxImgForHamlet" style="display:none;margin-top:10px;"><img src="images/icons/goldAjaxLoad.gif"/></div>
		<div id="impFamPancBothDtlsForHamlet"></div>
	   </div>


	   <div id="impFamilesAllInfoPopUp" style="display:none;">
        <div id ="impFamilesBasicInfoSubChartDiv" style="border:1px solid black"></div>
		<div id ="impFamilesBasicSubDetailsTitle" ></div>
	
		<div id ="impFamilesBasicSubDetails" style="border:1px solid black"></div>
		<div id="descriptionDiv" ></div>
		<div id="impFamPancBothDtlsAgxImg" style="display:none;margin-left:361px;margin-top:10px;"><img src="images/icons/goldAjaxLoad.gif"/></div>
		<div id="impFamPancBothDtls"></div>
	   </div>
		
		<div id="NoteDiv" class="breadcrumb"></div>
		
		<div id="impFamDtlsOuterPopUp" style="display:none;">
		   <div id="impFamDtlsTitle"></div>

		   <h5>Check Required Fields To Show</h5>

          <div id="impFamilySelectedDetails" style="padding:10px 0 10px 27px;border:1px solid #c3c3c3;border-radius:5px;">

           

			<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="genderId">Gender</input></label>

			<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="ageId">Age</input></label>

			 <label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="guardianNameId" >House No</input></label>

		    <label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="guardianNameId" >Guardian Name</input></label>

		    <label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="relationShipId">Relation Ship</input></label>
			
			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="mobileId  ">Mobile No</input></label>

			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="casteId">Caste</input></label>

			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="partyId">Party</input></label>

			<!--<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes" id="moneyId">Money</input></label>-->
<div id="impFamilySelectedDetails1"></div>

			<a class="btn" href="javaScript:{checkForAttributesToDisplay();}">Show Details</a>
		  </div>

           
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable"></div>
	       <div class="buttonsTop"><span class="buttonLeft"><input class="btn" onclick="selectAll('familyMemberCheck')" type="button" value="Select All" /></span><span class="buttonLeft"><input onclick="deSelectAll('familyMemberCheck')" class="btn" type="button" value="De Select All" /></span><span class="buttonLeft"><input class="btn" type="button" value="Edit" onclick="getAllVoterFamiliesForEdit();"/></span></div>
		   <div id="multipleVoterFamiliesEditDiv"></div>
		</div>

	</div>

</div><!-- for  body 1 end    result  -->

</div><!-- for  body 1 end -->

<!-- for  body 2 start    result  -->
<div id="votersDiv2" >
<!--
<div id='votersHeaderDiv2'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="localCastStatsTabContentTitle" class="votersWidgetHeader_span" style="top:11px;"> </span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div> -->
<div id='votersMainOuterDiv2'  class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;background:#fff;">
<h4 class="" id="partywiseCasteTitle" ></h4>
<div id='partyBasicInfoStatsTabNewTitle' style="clear:both;"></div>
	<div id='partyBasicInfoStatsTab' style="clear:both;"></div>
	<div id="partyWiselocalcastDiv" style="margin-top:20px;margin-bottom:50px;"></div>
	<div id='partyWiseLocalCastStatsTab' class="yui-skin-sam yui-dt-sortable" style="display:none;">	</div>
	

	<div id="localCastStatsVotersPopUpDiv" style="display:none;">
	    <div id ="localCastStatsVotersTitle" ></div>
	    <div id='localCastStatsTabContent_subbody1'  class="yui-skin-sam yui-dt-sortable"></div>
	</div>
	<div style="clear:both;">
	  <span id="castPartyNewPopupShowBtn"><input type="button" id="PartyWiseCastInfoBtn" class="btn btn-info" value="View Castes Vs Party Analysis" style="float:left;margin-top:7px;margin-bottom:5px;" onclick="getPartyWiseCastInfo();"/></span>
	  	 <!-- <span id="castPartyPopupShowBtn"><input type="button" class="btn btn-info" value="View More Details About Caste Statistics and Party Wise Voters Details" style="float:right;margin-top:7px;margin-bottom:5px;" onclick="ShowCastPartyPopupDiv();"/></span>
	  
	  <span id="castPartyPopupShowBtn"><input id="castPopupShowBtn" type="button" class="btn btn-info" value="View More Details About Caste Statistics and Party Wise Voters Details" style="float:right;margin-top:7px;margin-bottom:5px;" onclick="showCastPartyWindow();"/></span>-->
	</div>

	<!-- user voter categories div -->
	
	<div id="castPartyPopupDiv" style="display:none"> 
	 <div id="getLatestCastsSubcategoryWise"  style="float:right;"><input type="button" onclick="getLatestCastsSubcategoryWise();" value="Get Updated Caste Info" class="btn btn-info" /></div>
	<div style="margin-top: 10px; margin-bottom: 15px;"><img id="voterCasteAjaxImg" src="./images/icons/goldAjaxLoad.gif" style=" clear: both; display:none;"/></div>
		
		 <div id='localCastStatsTabContent_subbody'></div>	
		<br>
		
	</div>	
		<br><br>
	

	


</div>
</div><!-- for  body 2 end    result  -->


<div id="categoriesDiv" class="widget whitegloss" style="background:#fff;display:none;"></div>

</div><!-- for  body 2 end >

<!-- for  body 3 end    result  -->
</div>
</div><!-- body 3 end -->


</div>

</div>
<div id="partyGraphDiv" style="display:none">
<div id="partyWiseDetailsHeadingDiv" style="clear:both;" class="localCastDetailsHeadingDiv"></div>
	    <div id='partyWise_header'></div>
		
	    <div class="partyWiseDetailsMainDiv">
		<table style="width:100%;"><tr>
		  <td><div id="partyWiseDetailsDiv"></div></td>
		  <td><div id="partyWiseChatDiv"></div></td></tr></table></div>

</div>
<div id="NoResultsDialogue"><span style="display:none;">No Data Found</span></div>
<!-- main div  End-->
</div>
<form id="getAllVoterFamiliesForEditForm" method="post" action="getMultipleFamilesInfoForEditAction.action" name="getAllVoterFamiliesForEditForm">
	   <input type="hidden" name="task" id="getAllVoterFamiliesForEditFormValues" />
</form>
<form id="getAllVoterFamiliesInfoForEditForm" method="post" action="getMultipleFamilesInfoForEditAction.action" name="getAllVoterFamiliesInfoForEditForm">
	   <input type="hidden" name="task" id="getAllVoterFamiliesInfoForEditFormValues" />
</form>
</div>
<script type="text/javascript">
//Created by sasi to open new window
	$('#detailModifiedVoters').click(function(){
		window.open($(this).attr('url'));		
	});

	function getCastInfoForsubLevel(id,publicationId,type,resultFor)
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
		
		if(true)
		{
		var jsObj=
		{		
				type:type,	
				id:id,
				typeName:typeName,
				publicationDateId:publicationId,
				constituencyId:$("#constituencyList").val(),
                buildType:buildType,
				resultFor:resultFor,
                queryType:"sub",
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
function openFlagWindow(){
	var urlStr="votersFlagAction.action";
	var updateBrowser = window.open(urlStr,"createflag","scrollbars=yes,height=600,width=850,left=200,top=200");	
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


 if(forGroupInMuncipality == true) {
	 showAllAgewiseDetailsForCustomVoterGroup();
	 return false;
 }
   //callCorrespondingAjaxCall('all');
   constituencyId = $("#constituencyList").val();
   publicationYear = publicationYear;
   if(maintype == "constituency")
   {
	   var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&name="+mainname+"&retrieveType='all'&type='constituency'","newBrowser"+maintype,"width=700,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
   }
   else if(maintype == "mandal")
   {
		var startNumber = mainreqid.substring(0,1);
		mandalId = mainreqid.substring(1);

		if(startNumber == "1" && forGroupInMuncipality == true)
 	    {
			var reqBrowser = 
		 window.open("getCustomVoterGroupAgeDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&locationValue="+mandalId+"&locationName=fdfdsfsd&areaType=URBAN","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			 reqBrowser.focus();

		}else{

		var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&mandalId="+mandalId+"&name="+mainname+"&retrieveType='all'&&startNumber="+startNumber+"&type='mandal'","newBrowser"+maintype,"width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
		}
   }
   else if(maintype == "panchayat")
   {
		
		var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&panchayatId="+mainreqid+"&buildType="+buildType+"&name="+mainname+"&retrieveType='all'&type='panchayat'","newBrowser"+maintype,"width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
   }
   else if(maintype == "ward")
   {
		
		var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&panchayatId="+mainreqid+"&buildType="+buildType+"&name="+mainname+"&retrieveType='all'&type='ward'","newBrowser"+maintype,"width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
   }
   else if(pattern.test(maintype))
   {
	
		var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&panchayatId="+mainreqid+"&buildType="+buildType+"&name="+mainname+"&retrieveType='all'&type='"+maintype+"LocalArea'","newBrowser"+maintype,"width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
   }
   
    else if(maintype == "booth")
   {
		
		var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&panchayatId="+mainreqid+"&buildType="+buildType+"&name="+mainname+"&retrieveType='all'&type='boothHamlets'","newBrowser"+maintype,"width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
   }
}
function showAllAgewiseDetailsForHamlet(){
   //callCorrespondingAjaxCall('all');
   constituencyId = $("#constituencyList").val();
   publicationYear = publicationYear;

   if(maintype == "hamlet" || maintype == "customWard" || maintype == "mandal")
   {     var subType ="";
         var  myId="";
   if(maintype == "hamlet")
		subType = "hamletBooths";
		else if(maintype == "customWard")
		subType = "wardBooths";
		if(maintype == "mandal"){
		  subType = "muncipalityWards";
		  myId = mainreqid.substring(1);
		  }else
		  myId = mainreqid;
		var reqBrowser = window.open("ageWiseVoterDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&publicationYear="+publicationYear+"&panchayatId="+myId+"&buildType="+buildType+"&name="+mainname+"&retrieveType='all'&type='"+subType+"'","ageWiseVoterDetails","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	   reqBrowser.focus();
   }
   
}
   function openwindowForPanchayatsToShow(){
		var selElectionId = $("#revenueVillageWiseElecId").val();
		var selElecyear = $("#revenueVillageWiseElecId option:selected").text() ;
		//var brow1 = window.open("panchayatWiseElectionResultsAction.action?mandalId="+mainreqid.slice(1)+"&electionId="+selElectionId+"&mandalName="+mainname.replace("MANDAL","")+"&electionType=Assembly&electionYear="+selElecyear+"&resultFor=panchayats","browser2","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		var reqBrowser = window.open("panchayatWiseMandalElectionInfoAction.action?MANDAL_ID="+mainreqid.slice(1)+"&election_id="+selElectionId+"","newBrowser"+mainreqid.slice(1),"width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		reqBrowser.focus();
}

function openwindowForPanchayatsToShowCast(){
		//var brow1 = window.open("panchayatWiseElectionResultsAction.action?mandalId="+mainreqid.slice(1)+"&electionId="+selElectionId+"&mandalName="+mainname.replace("MANDAL","")+"&electionType=Assembly&electionYear="+selElecyear+"&resultFor=panchayats","browser2","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		var reqBrowser = window.open("mandalRevenueVillagesElecInputAction.action?tehsilId="+mainreqid.slice(1),"newBrowsser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		reqBrowser.focus();
}
function showBasicAgewiseDetails(){
   callCorrespondingAjaxCall('brief');
}


/*function showAllAgewiseDetails(){
   callCorrespondingAjaxCall('all');
}*/
	function getElectionYearsAjaxAction(){
	    var jObj=
	{
		electionTypeId:2,
		constituencyId:$("#constituencyList").val(),
		task:"getElectionYearsForPanchayat"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAllElectionsInAConsti.action?save="+rparam;	

	callAjax(jObj,url);
	
	}

		
function getConstituencyResults(conId,consName){
showHideNewsProblems();
var cnstncy=$('#constituencyList :selected').text();
$('#constituencyInfo').val('');
$('#constituencyInfo').css('display','block');
	var jsObj = {
			conId:conId,
			cnstncy:cnstncy,
			task:"getConstituencyResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/assemblyWiseResultAction.action?"+rparam;
	callAjax(jsObj, url);
}

function scrollToNewsDiv(){
 $('html,body').animate({scrollTop: $("#scrollToHere").offset().top}, 2000);

}

function scrollToMandals(){

 $('html,body').animate({scrollTop: 250}, 2000);
}
function getImpFamiliesVoters()
{
	var mainreqid = $("#constituencyList").val();

    if(forGroupInMuncipality == false) 
	{
		var urlstr = "voterFamilyInfoAction.action?buildType="+buildType+"&publicationDateId="+impFamlpublicationDateId+"&id="+impFamlId+"&type="+impFamltype+"&typename="+impFamltypename+"&maintype="+maintype+"&constituencyId="+mainreqid+"&publicationYear="+publicationYear+" "

		var browser1 = window.open(urlstr,"familyWiseDetail"+impFamlId,"scrollbars=yes,height=600,width=700,left=200,top=200");
		
		browser1.focus();

	}else
	showFamilyWiseDetailsForCustomVoterGroup();
}


function getImpFamiliesVotersForBooth()
{
	
var mainreqid = $("#constituencyList").val();
var urlstr = "voterFamilyInfoAction.action?publicationDateId="+impFamlpublicationDateId+"&id="+impFamlId+"&type=hamlet&maintype="+maintype+"&constituencyId="+mainreqid+"&publicationYear="+publicationYear+"&buildType="+buildType+" "
var browser1 = window.open(urlstr,"familyWiseDetail"+impFamlId,"scrollbars=yes,height=600,width=700,left=200,top=200");	
browser1.focus();
	}

	function getImpFamiliesVotersForBooth1()
	{var dest = "";
if(maintype == "mandal" && muncipalityPattern.test(myCorpType) )
dest = "muncipalityWards";
 else 
dest = "booth";
var mainreqid = $("#constituencyList").val()
var urlstr = "voterFamilyInfoAction.action?publicationDateId="+impFamlpublicationDateId+"&id="+impFamlId+"&type="+maintype+"&maintype="+maintype+"&constituencyId="+mainreqid+"&publicationYear="+publicationYear+"&buildType="+buildType+"&requestFor="+dest
var browser1 = window.open(urlstr,"familyWiseDetail"+dest,"scrollbars=yes,height=600,width=700,left=200,top=200");	
browser1.focus();
	}

	function callMethodToGetData(){
	  var str ='';
	   $("#categoriesErrMsgDiv").html("");
	   $('.categorycheckbox').each(function() {
	        if($(this).is(':checked')){
		       str = str+","+$(this).val();
            }
        });
		if(str.length == 0){
		   $("#categoriesErrMsgDiv").html("Please Check atleast one custom attribute to analyse");
		  return;
		}
		str = str.slice(1);
		$("#categoriesAjximgMsgDiv").show();
	   var jsObj = {
	        attributeIds:str,
			locationType:maintype,
			locationId:mainreqid,
			constituencyId:$("#constituencyList").val(),
			publicationId:mainpublicationId,
			task:"getCategoryWiseDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getCategoryWiseDetailsAction.action?"+rparam;
		callAjax(jsObj, url);
	}
	
	function buildCategoryWiseDetails(results,jsObj){
	 $("#categoriesAjximgMsgDiv").hide();
	  if(results == "notLogged"){
	    openDialogForLoginWindow();
	    return;
	  }
	 var str = "";
	  if(results != null && results.length >0){
	      str+= "<div style='margin-bottom:5px;'><b style='font-size:13px;'>Total Voters : </b>"+results[0].totalVoters+"</div>";
	     for(var i in results){
		  if(results[i].partyWisevoterCastInfoVOList != null && results[i].partyWisevoterCastInfoVOList.length > 0){
			   str+="<div style='border:1px solid #d3d3d3;padding:5px 5px 31px;margin-bottom:20px;border-radius: 4px 4px 4px 4px;'>";
			   str+= "<h2 id='subHeading' style='margin-left:-5px;width:97%;'><b>"+results[i].name+" Attribute Wise Voters Analysis</b></h2>";
			   str+= "<div style='margin-top:5px;'><span><b style='font-size:13px;'>"+results[i].name+" assigned voters : </b>"+results[i].partyWiseAssignedVoters+"  </span>&nbsp;&nbsp;<span><b style='font-size:13px;'>   "+results[i].name+" not assigned voters : </b>"+results[i].partyWiseNotAssignedVoters+"</span></div>";
			   
			   
			   str+="<table class='table table-bordered table-striped table-hover' style='margin-bottom: 5px; margin-top: 7px;'>";
			   str+="<thead class='info'>"
			   str+="  <tr>";
			   str+="     <th>"+results[i].name+"</th>";
			   str+="     <th>Total Voters</th>";
			   str+="     <th>Male Voters</th>";
			   str+="     <th>Female Voters</th>";
			   str+="     <th>Voters %</th>";
			   str+="  </tr>";
			   str+="</thead>"
			   for(var j in results[i].partyWisevoterCastInfoVOList){
			     var obj = results[i].partyWisevoterCastInfoVOList[j];
				  str+="  <tr>";
			      str+="     <td>"+obj.name+"</td>";
			      str+="     <td>"+obj.totalVoters+"</td>";
			      str+="     <td>"+obj.maleVoters+"</td>";
			      str+="     <td>"+obj.femaleVoters+"</td>";
			      str+="     <td>"+obj.partyPercentage+"</td>";
			      str+="  </tr>";
			   }
			   str+="</table>";
			   if(jsObj.locationType != "booth" && jsObj.locationType != "hamlet" && results[i].partyWiseAssignedVoters > 0)
			   str+="<div style='float:right;'><input type='button' class='btn btn-success' value='View More Details' onclick='getCategorySubDetails("+results[i].id+","+jsObj.constituencyId+");' /></div>";
			   str+="</div>";
		   }
		 }
		 $("#categoriesValuesDiv").html(str);
	  }
	}


	
function getAgewiseInfoForVoterCategory(){
	  var str ='';
	  // $("#categoriesErrMsgDiv").html("");
	   $('.categorycheckbox').each(function() {
	        if($(this).is(':checked')){
		       str = str+","+$(this).val();
            }
        });
		/*if(str.length == 0){
		   $("#categoriesErrMsgDiv").html("Please Check atleast one custom attribute to analyse");
		  return;
		}*/
		str = str.slice(1);
		//$("#categoriesAjximgMsgDiv").show();
	   var jsObj = {
	        attributeIds:str,
			locationType:maintype,
			locationId:mainreqid,
			constituencyId:$("#constituencyList").val(),
			publicationId:mainpublicationId,
			task:"getAgeWiseWiseDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getAgeWiseWiseDetailsAction.action?"+rparam;
		callAjax(jsObj, url);
	}



	
	function getCategorySubDetails(attrId,constituencyId){
		var str ='';
	 
	  /* $('.categorycheckbox').each(function() {
	        if($(this).is(':checked')){
		       str = str+","+$(this).val();
            }
        });
		
		str = str.slice(1);*/

	   var urlstr = "categorySubWiseVoterDetailsAction.action?id="+attrId+"&retrieveType="+maintype+"&locationId="+mainreqid+"&constituencyId="+constituencyId+"&publicationId="+mainpublicationId+"&task=getCategoryWiseSubDetails&attributeIds=["+attrId+"]&";



     var browser1 = window.open(urlstr,"categorySubWiseVoterDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
	   /*var jsObj = {
	        attributeId:attrId,
			locationType:maintype,
			locationId:mainreqid,
			constituencyId:constituencyId,
			publicationId:mainpublicationId,
			task:"getCategoryWiseSubDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getCategoryWiseDetailsAction.action?"+rparam;
		callAjax(jsObj, url);*/
	}

	function getAllSubDetails(){
		if(typeofMuncipality == "GHMC Greater Municipal Corp")
			isGhmc = true;
		else
			isGhmc = false;

	   var constiId = $("#constituencyList").val();
	   if(maintype !="panchayat")
		{
	   var urlstr = "subRegionsWiseAnalysisAction.action?id="+mainreqid+"&publicationDateId="+mainpublicationId+"&type="+maintype+"&publicationYear="+publicationYear+"&buildType=&constituencyId="+constiId+"&typeName="+mainname+"&ghmc="+isGhmc;
		}
	if(maintype =="panchayat")
		{
		var tehsilId = $("#middleNav-Panchayats-list a .checkbox").closest("a").attr("data-mandalid");
		tehsilId = tehsilId.substring(1);
	   var urlstr = "subRegionsWiseAnalysisAction.action?id="+mainreqid+"&publicationDateId="+mainpublicationId+"&type="+maintype+"&publicationYear="+publicationYear+"&buildType="+buildType+"&constituencyId="+constiId+"&typeName="+mainname+"&mandalId="+tehsilId;
		}
     var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
	}
	function refreshConstituencyData(){
		showHideNewsProblems();
		$('#publicationDateList').trigger('change')
	}

	function refreshAgeWiseData(){
		callCorrespondingAjaxCall('brief');
	
	}
	$(document).ready(function(){
		if(populateConstituencyId != null && populateConstituencyId != "" && populateConstituencyId > 0)
		{
			
			$('#constituencyList').val(populateConstituencyId);
			getPublicationDate();
			getConstituencyResults(populateConstituencyId);
			getUserVoterCategories();
		}
		if(selType == "constituency")
		{
			$('#constituencyList').val(populateConstituencyId);
			getPublicationDate();
			getConstituencyResults(populateConstituencyId);
			getUserVoterCategories();
		}
		else if(selType == "mandal")
		{
		
		}
		else if(selType == "panchayat")
		{
		
		}
		else if(selType == "booth")
		{
		
		}
	});

function showAllAgewiseDetailsForCustomVoterGroup()
{
    var areaType = "RURAL";
	if(mainreqid.substring(0,1) == "1")
		areaType = "URBAN";

	var locationValue = mainreqid.substring(1);
		var reqBrowser = 
		// window.open("getCustomVoterGroupAgeDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&locationValue="+mandalId+"&locationName=fdfdsfsd&areaType=URBAN","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");

	window.open("getCustomVoterGroupAgeDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&locationValue="+locationValue+"&locationName=&areaType="+areaType+"&groupName="+mainname+"","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			 reqBrowser.focus();
}


function showCasteWiseDetailsForCustomVoterGroup()
{
	var areaType = "RURAL";
	if(mainreqid.substring(0,1) == "1")
		areaType = "URBAN";

	var locationValue = mainreqid.substring(1);
	var reqBrowser = 
		// window.open("customGroupWiseCasteVotersDetails.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&locationValue="+mandalId+"&locationName=fdfdsfsd&areaType=URBAN","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");


	window.open("customGroupWiseCasteVotersDetails.action?locationValue="+locationValue+"&areaType="+areaType+"&groupName="+mainname+"","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
   
    reqBrowser.focus();
	
}

function showFamilyWiseDetailsForCustomVoterGroup()
{
	var reqBrowser = 
		// window.open("getCustomVoterGroupsFamilyDetailsAction.action?constituencyId="+constituencyId+"&publicationDateId="+mainpublicationId+"&locationValue="+mandalId+"&locationName=fdfdsfsd&areaType=URBAN","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");

	 window.open("getCustomVoterGroupsFamilyDetailsAction.action?constituencyId="+constituencyId+"&locationValue="+mandalId+"&groupName="+mainname+"","newBrowser","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			 reqBrowser.focus();
	
}

function getFlagWiseVotersCount(id,publicationId,type)
{
		$("#flagWiseVotersCountDiv").html('');
	 var subType ="";
	var muncipalityType = $("#middleNav-Wards-list a .checkbox").closest("a").attr("data-wardid");
 if(muncipalityType != null)
	subType = "wardBooths";
 
 var jsObj = {
	      
			locationType:type,
			subType:subType,
			locationId:id,
			constituencyId:$("#constituencyList").val(),
			publicationId:publicationId,
			task:"getFlagWiseVotersDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getFlagWiseVotersCountAction.action?"+rparam;
		callAjax(jsObj, url);
}
</script>
</body>
</html>