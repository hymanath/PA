<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>


 <!--<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

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
  <link rel="stylesheet" type="text/css" href="styles/voterAnalysisStyles/voterAnalysisStyles.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="js/customVoterGroupAnalysis.js"></script>
	 <script type="text/javascript" src="js/commonVoterDetails.js"></script>
  
   <style>
   #mainDiv{
	border: 1px solid #C3C3C3;
    border-radius: 4px 4px 4px 4px;
    margin-left: 20px;
    margin-right: 20px;
	}
  #votersByLocationTabContentDiv_body
{
	border: 1px solid #C3C3C3;
    border-radius: 4px 4px 4px 4px;
	width:925px;
	margin-left:15px;
}
#voterTypeId
{
	margin-bottom: 10px;
	color: black;
    font-family: verdana;
    font-size: 15px;
	margin-left: 20px;
    margin-right: 20px;
	margin-top: 10px;
}
#votersCountId
{
	color: black;
    font-family: verdana;
    font-size: 15px;
	 margin-bottom: 10px;
	 margin-left: 20px;
    margin-right: 20px;
	margin-top: 10px;
}

   </style>
</head>
<body>


<div id="mainDiv">

<div id="votersCountId"> fdghfghjg</div>
<div id="voterTypeId"></div>
<div id="atterHeadingDiv" style="display:none;margin-left:20px"><h5>Check Required Fields To Show</h5></div>
<div id="impFamilySelectedDetails" style="padding:10px 0 10px 27px;border:1px solid #c3c3c3;border-radius:5px; display:none;margin-left:20px;margin-right: 20px;height:100px;">
		
<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="snoId">S NO</input></label>
		
<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="nameId">Name</input></label>

<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="voterId">Voter Id</input></label>

<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="boothNoId">Booth NO</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="genderId" >Gender</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="ageId" >Age</input></label>

<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="houseNoId">House NO</input></label>
			
<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="typeId">Type</input></label>
			
<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="actionsId">Actions</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="casteId" value="cast" >Caste</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="partyId" value="party" >Party</input></label>


<!--<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes" id="moneyId">Money</input></label>-->
<div id="impFamilySelectedDetails1"></div>

<div style="float:right;margin-top:-2px;margin-right:10px;"><a class="btn" href="javaScript:{checkForAttributesToDisplay();}">Show Details</a></div>
</div>

<div id="influencePeopleOuterDiv" >
  <div id="influencePeopleInnerDiv" style="border: 1px solid black;border-radius: 4px 4px 4px 4px;margin-top: 11px;padding: 10px;display:none;"></div>
  <div id="totalCountId" style="display:none;"></div>
  <div id="searchResultsDiv"  class="yui-skin-sam yui-dt-sortable" style="display:none;border: 1px solid black;border-radius: 4px 4px 4px 4px;margin-top: 11px;padding: 10px;"></div>
</div>
<div id="votersInnerDiv1" style="height:auto">
<div id="imgDescriptionDiv" style="margin-top: 30px; display:none;margin-left:20px;float:left;">
<b style="margin-left: 5px">Influencing People</b>:<img title="Influencing People" alt="Influencing People" src="./images/icons/influencing.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Cadre</b>:<img title="Cadre" alt="Cadre" src="./images/icons/cadre.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Politician</b>:<img title="Politician" alt="Politicion" src="./images/icons/politican.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<div id="errorMessageDiv"></div>
</div>

<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable yui-dt table table-bordered table-striped table-hover" style="display:block;">
</div>

</div>
<script type="text/javascript">
var customVoterGroupId ='${customVoterGroupId}';

var limit = 100;
var startIndex= "${startIndex}";
var publicationDateId = '${publicationDateId}';


function callAjax(jsObj,url)
{
	
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
		if (jsObj.task == "getUserCategories")
			{
				buildCategories(myResults);
			}
			else if(jsObj.task == "getVotersCount")
			{
				
			   buildVotersCount(myResults,jsObj)
			}
			   else if (jsObj.task == "ckeckForVoterId")
			{
				buildVoterToSelectedType(myResults,jsObj);
			}
			else if (jsObj.task == "getInfluencingPeopleCount")
			{
				buildVoterTypeDetails(myResults,jsObj);
			}	
			}catch (e)
			{
							     
			}  
 		},
 		scope : this,
		failure : function( o ) 
		{
			//alert( "Failed to load result" + o.status + " " + o.statusText);
		}
	   };

 	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function buildVotersCount(myResults,jsObj)
{
	
	if(myResults != null)
	{
		 $('#votersCountId').html('<span id="totCount">Total Voters : </span><span><b style="color:navy">'+myResults.totalVoters+'</b></span><span id="maleCount" style="margin-left: 10px;">Male Voters : </span><span><b style="color:navy">'+myResults.maleVoters+' </b></span><span id="femaleCount" style="margin-left: 10px;">Female Voters : </span><span><b style="color:navy">'+myResults.femaleVoters+'</b></span>'); 
		
	}
	//getInfluencingPeopleCount(jsObj.customVoterGroupId);
}
function getPeopleData(customgrpId,publicationId,type)
{
	
	var reqBrowser = window.open("influencingCadrePoliticianDisplayWindowAction.action?customVoterGroupId="+customgrpId+"&publicationDateId="+publicationId+"&btnName="+type+"&","newBrowser","width=1080,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		reqBrowser.focus();
	
}

</script>
<script type="text/javascript">

getVotersCount();
getInfluencingPeopleCount(1);
getUserCategories();
getVoterInfoForACustomGroup();

</script>
</body>
</html>