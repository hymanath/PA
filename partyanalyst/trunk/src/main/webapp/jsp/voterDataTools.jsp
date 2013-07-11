<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> --%>

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
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
 <script type="text/javascript" src="js/voterDataTools/voterDataTools.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">


<title>Voter Data Tools</title>

<style type="text/css">
#voterDataMainDiv{margin: 28px auto 20px; float: none; width: 990px;}
#casteAssignedDiv{padding-bottom: 20px;}
#casteAssignedRadioDiv{margin-top: 16px; margin-bottom: 12px;}
.casteAssignedRadioCls{margin-right: 3px;}
#casteAssignedBtn{margin-left: 25px;}
#totalVotersDiv{margin-bottom: 18px;margin-top: 24px; text-align: center;}
#casteAssignedVoters th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#casteAssignedVoters td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
#casteErrorMsgDiv{margin-top: 8px;color:red;}
#sublevel{margin-top:10px;}
#sublevel th{background:#CDE6FC;}
.widget h4 {font-family: arial;color:#000;}
.f2 {margin-right: 15px;}
#voterInfoHeading{margin-bottom: 15px;}
#errorMsgDiv{color:red;}
</style>
</head>
<body>
<div id="voterDataMainDiv">
  
 <!-- Hamlet Assigned Voters Start -->

 <div class="widget blue">
   <div id="errorMsgDiv"></div>
   <div id="ConstituencyDiv" class="selectDiv" style="margin-top:10px;">
	 Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getHamletPublicationDate();"/> &nbsp;&nbsp;

		
	 Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		 
  </div>
  <div id="ajaxImgId" style="display:none;margin-right:427px;float:right;"><img src="./images/icons/search.gif" alt="Processing Image"/></div>

  <div style="margin-top:10px;margin-bottom:10px;text-align: center;clear:both;">
    <input type="button" style="position: absolute:top: 50%;" value="Get Hamlet Assigned Info" class="btn btn-success" onclick="getSubLevelInfo();" />

    
 </div>
<br>

 </div>
<div class="widget blue" id="subLevelDataId" style="display:none;">
   <div id="subLevelInfo" style="overflow-x:scroll;">
   </div>
</div>
 <!-- Hamlet End -->
  <!-- caste Assigned/Not Assigned Div Start -->

 <div id="casteAssignedDiv" class="widget blue">
  <h4>Caste Assigned Voter Details in Booth or Panchayat</h4>
  <div id="casteErrorMsgDiv"></div>
    <div id="casteConstituencyDiv" class="selectDiv" style="margin-top:10px;">
	 Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="casteConstituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDateForCaste();"/> &nbsp;&nbsp;

		
	 Publication Date<font class="requiredFont">*</font> <select id="castePublicationDateList" class="selectWidth" style="width:172px;height:25px;">
		</select>
	
	<img src="./images/icons/search.gif" alt="Processing Image" id="CasteAjaxImgId" style="display:none;" />
	
	<span style="display: none;" id="casteAssignedVotersHideAndShow"><a id="casteAssignedVotersHideMenu" class="btn" href="javascript:{}">Hide<i class="icon-chevron-up"></i></a></span>

	</div>
    
	
   <div id="casteAssignedRadioDiv">
     <input type="radio" name="casteAssignedRadio" class="casteAssignedRadioCls" value="booth" checked="true" style="margin-top: 0px;"/> Booth 
	 <input type="radio" name="casteAssignedRadio" class="casteAssignedRadioCls" value="panchayat" style="margin-top: 0px;"/> Panchayat
	 <input type="radio" name="casteAssignedRadio" class="casteAssignedRadioCls" value="ward" style="margin-top: 0px;"/> Ward
     <input type="button" id="casteAssignedBtn" value="Get Caste Assigned Info" class="btn btn-info" onclick="getCasteAssignedInfo();" />
   </div>
  
  <div id="casteAssignedVotersInnerDiv"></div>

</div>
 <!-- caste Assigned/Not Assigned Div End -->


</div>
</body>
</html>