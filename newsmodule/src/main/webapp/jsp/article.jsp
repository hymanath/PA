<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> TDP News Portal </title>
<!--<script type="text/javascript" src="js/jquery.js"></script>-->
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>

  <link rel="stylesheet" href="js/ui/1.10.3/smoothness/jquery1.10.3-ui.css" />

  <script src="js/ui/1.10.3/jquery-ui.js"></script>
    <script src="js/jquery-1.8.2.js"></script>	
    <script src="js/ui/1.9.0-themes-base/jquery-ui.js"></script>
 
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	 <SCRIPT type="text/javascript" src="js/specialPage/specialPage.js"></SCRIPT>
	
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
<!-- JQuery files (Start) -->

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
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->


<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/article.js"></script>

<!-- JQuery files (End) -->
<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- keywords -->
    <link rel="stylesheet" type="text/css" href="styles/autoSuggest.css"> 
	<script type="text/javascript" src="js/jquery.autoSuggest.js"></script>
	<script type="text/javascript" src="js/jquery.autoSuggest.minified.js"></script>
	<script type="text/javascript" src="js/jquery.autoSuggest.packed.js"></script>

<!-- keywords -->
<link  rel="stylesheet" type="text/css" href="styles/partyManagement/partyManagement.css"/>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="js/blockui.js"></script>
<style type="text/css">
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
 
 .enadu
{
font-family: eFont;
font-size:20px;
}
#candidateListForPartyImg{margin-left:300px;}

#userTrackingDetails table,#profileManagementMainOuterDiv4 table,#reportsDiv table,#locationWiseNewsDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#userTrackingDetails table tr:nth-child(even),#profileManagementMainOuterDiv4 table tr:nth-child(even),#reportsDiv table tr:nth-child(even),#locationWiseNewsDiv table tr:nth-child(even){background:#EdF5FF;}
#userTrackingDetails table td,#profileManagementMainOuterDiv4 table td,#reportsDiv table td,#locationWiseNewsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#userTrackingDetails table th,#profileManagementMainOuterDiv4 table th,#reportsDiv table th,#locationWiseNewsDiv table th{
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
#profileManagementMainOuterDiv4{
	font-family : arial;
	font-size: 13px;
    margin-top:-20px;
	padding: 10px 10px 10px 0px;
}

#reportsDiv,#locationWiseNewsDiv
{
	font-family : arial;
	font-size: 13px;
    margin-top: -32px;
	padding: 10px 10px 10px 15px;
}
#profileManagementMainOuterDiv4 table th a{
color:#333333;
}
.newsLinkCls{ color: #0088CC;}
#totalSelectedNewsCount{float: none;
    margin-left: auto;
    margin-right: auto;
    text-align: left;
    width: 901px;font-weight:bold;}
	/*#selectLevel{float: none;
    margin-left: auto;
    margin-right: auto;
	width: 901px;font-weight:bold;}*/
#selectedNewsCount{color:red;}
.createNewCandidate{width: 20px; height: 20px;cursor:pointer;}
.m_topN65{margin-top: -24px;}
form{
border:1px solid #C5C5C5;
}
#reportGenaratorSpanCLS{color:red;}
#newsReportBtnDiv{text-align: center;}
.ui-multiselect{margin-top:10px;width:222px !important;}

.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 13px 312px;
    white-space: nowrap;
}
.form-actions{
   margin-bottom: -11px;
}
.form-actions input {
    margin: 0 12px;
}
h2{
  margin-bottom: -38px;
}
.yui-skin-sam .yui-dt-liner{
	padding:4px 0px;
}
#gettrackdtlsId{
    margin-bottom: 5px;
    margin-left: 440px;
    margin-top: 7px;
}
</style>
</head>
<script type="text/javascript">

</script>

<body>
<br>
<!-- For Heading -->
<div>
<input type="button" value="submit" onclick="getTotalNewsWithPagination();" class="btn btn-info"/>
   </div>
   <br/>
     <!--<div class="span4" style="float:right;">
    
 <input type="button" value="Add Response" onclick="addToNewsResponse()" class="btn btn-info"/>
 </div>
 <br/>-->
 
 
 <div id="errorMsgNewsDiv"></div>

  <!--<div id="totalSelectedNewsCount">Total Selected News Count: <span id="selectedNewsCount"></span></div>-->

<div id="successMsg" style="float: left; margin-left: 60px; margin-top: 12px;"></div>
<div id='profileManagementMainOuterDiv4' style="display:block">
	<div id='profileManagementHeaderDiv4' class="row-fluid">

		
	
</div>
<div id='newsGallaryDiv' class="divInfo">
	 </div>		

<!-- for  body 4  result  end -->

<div id="ajaxImg" style="display:none;margin-left:300px;margin-top:30px;"><img src="images/icons/goldAjaxLoad.gif"></img></div>
</div>


</body>
</html>
