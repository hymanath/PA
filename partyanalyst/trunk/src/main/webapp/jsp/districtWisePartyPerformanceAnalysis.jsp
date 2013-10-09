<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>District Wise Party Performance Analysis</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<!-- YUI Dependency Files-->
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
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->
	
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
	<script type="text/javascript" src="js/districtWisePartyPerformanceAnalysis.js"></script>
<style type="text/css">
.main-mbg {
    background-color: #06ABEA;
    border-radius: 7px 7px 7px 7px;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 977px;
}
table.searchresultsTable {
    border-collapse: collapse;
    border-color: #666666;
    border-width: 1px;
    color: #333333;
    font-family: verdana,arial,sans-serif;
    font-size: 11px;
    margin-top: 10px;
}
table.searchresultsTable th {
    background-color: #C4DEFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
}
table.searchresultsTable td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
}
table.searchresultsTable, table.searchresultsTable * td, table.searchresultsTable * th {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-style: solid;
    border-width: 1px;
}
.buttonClass {
    background-color: #F52B43;
    border-radius: 6px 6px 6px 6px;
    color: #FFFFFF;
    cursor: pointer;
    font-weight: bold;
    padding: 4px;
}
.yui-dt-label{
  font-weight:bold;
}
.buttonStyle {
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
}
.yui-skin-sam .yui-dt th .yui-dt-liner {width:auto;}
</style>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
</script>
</head>
<body>
<center>
<div style="width:998px;padding-left:5px;">
   <div style="padding-left:5px;"><div class="main-mbg">District Wise Party Performance</div></div>
   <div style="background-color:#FFFFFF;min-height:360px;">
         
   <div style="padding-top:10px;padding-left:100px;width:80%;text-align:center;">
     <div id="errorMessage"></div>
     <table>  
	  <tr>
		  <td><div id="showHideState"><b>&nbsp;&nbsp;Select State :</b>&nbsp;&nbsp;<select  id="stateListId"  onchange="getElectionYears('Assembly');"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId" onchange="getAllParties('main');" ><option value="0">Select State</option></select></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Party :</b></td>
		  <td>&nbsp;&nbsp;<select id="partiesIdDiv" multiple="multiple" style="width:100px;"><option value="0">Select Party</option></select></td>
		  <td><input type="button" class="buttonStyle" onclick="getDetails();"value="Search" ></td>
		  <td><span id="select_ImgSpan" style="padding-left:10px;padding-top:5px;display:none;"><img src="images/icons/partypositions.gif"></span></td>
      </tr>
	 </table>
	 <table>
	   <tr>
	     <td><span id="resultImgDiv" style="padding-left:268px;padding-top:5px;display:none;"><img src="images/icons/goldAjaxLoad.gif"></span></td>
	   </tr>
	 </table>
   </div>
   <div style="padding-bottom:30px;width:100%">
     <div class="yui-skin-sam" style="width:990px;padding-left:5px;padding-right:5px;overflow:auto;text-align:center;"  >
	   <div id="districtAnyHeading"></div>
	   <div class="yui-dt" id="districtAny"></div>
	 </div>
   </div>
   <div id="analysisTable" style="margin-left:180px;"></div>
   <div id="analysisTableDisplay" style="overflow:auto;"></div>
   <div style="margin-top:20px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px; margin-bottom: 23px;padding-right:5px;" id="districtChart" />
   </div>
   <div id="hideSelect" style="display:none;">
     <center><div style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;">View Party Wise Results In All Districts : </div></center>
     <center>
	     <div>
		    <table>
			   <tr>
			     <td><b>Select Parties : </b>&nbsp;&nbsp;</td>
			     <td><select id="parties" multiple="multiple" style="width:100px;" ></select></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <td><input type="button" class="buttonClass" value="View" onclick="getDataForChart('sub');" /></td>&nbsp;&nbsp;&nbsp;
				 <td><div id="searchImage" style="display:none;"><img src="images/icons/search.gif"></img width="18px" height="18px;"></div></td>
			   </tr>
			 </table>
		  </div>
	  </center>
   </div>
   <div style="margin-bottom:30px;margin-top:20px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px;padding-right:5px;" id="districtPartyChart" />
   </div>
   <div style="padding-bottom:30px;margin-left:0px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px;padding-right:5px;" id="districtPartyChartMore" />
   </div>
  </div>
</div>
   <script type="text/javascript">
	 getAllStates();
   </script>
   </center>
</body>
</html>