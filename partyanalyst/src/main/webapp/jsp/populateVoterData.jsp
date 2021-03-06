<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- YUI Dependency files (Start) -->
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>

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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries.js"></script>
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
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <style type="text/css">
#MainDiv,#voterDataOuterDiv{
margin-left:auto;
margin-right:auto;
width:980px;
}
#MainDiv{margin-bottom: 20px; margin-top: 60px;}
fieldset{
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 20px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
	 padding-top: 30px;
	width: 844px;
   margin-left: auto; 
   margin-right: auto;
   float: none;"
}
.selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-family: verdana;
	 font-size: 12px;
	 margin-left:auto;
	 margin-right:auto;
	 font-weight:bold;
	 color:#333333;

 }
 .requiredFont{
 color:red;
 font-size:12px;
 }
#voterDataInsertDiv{text-align: center; margin-top: 50px;}
#errorMsgDiv,#votermodificationerrorMsgDiv
	{
	font-size: 14px;
    margin-left: 52px;color:red;}
		#ajaxImage{display: block;
    margin-left: 480px;
    margin-top: -22px;}
	.headingDiv {
     background: none repeat scroll 0 0 #06ABEA;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    float: none;
    font-family: arial;
    font-size: 17px;
    font-weight: bold;
    margin-bottom: 15px;
    margin-left: auto;
    margin-right: auto;
    padding: 3px;
    text-align: center;
    width: 360px;
}
#voterInfoHamletBoothChecked,#voterInfoLocalityChecked{margin-left: 11px;}

#hamletBoothChecked,#localityChecked{margin-left: 8px;}

</style>
</head>
<body>
<div id="MainDiv">
<div id="voterDataOuterDiv">
<div class="headingDiv">Populate Voters Data To Intermediate Tables</div>
 <fieldset>
    <div id="errorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="voterDataConstituencyList" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="ajaxLoad"><img src="images/icons/search.gif" /></span>
	
	<div style="margin-top: 20px;">
	 <span style="width:40px;">
	   <input type="checkbox" id="voterInfoHamletChecked" value="Hamlet" style="margin-top:-5px;"> Hamlet

	   <input type="checkbox" id="voterInfoHamletBoothChecked" value="Hamlet Booth" style="margin-top:-5px;"> Hamlet Booth

	   <input type="checkbox" id="voterInfoLocalityChecked" value="Locality" style="margin-top:-5px;"> Locality
	</span>
	</div>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="voterDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="voterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;" id="ajaxImage" />
		</div>
	</div>
	</div>
 </fieldset>
 
 
 
 
 
 
 <div class="headingDiv">District wise Populate Voters Data To Intermediate Tables</div>
 <fieldset>
    <div id="districterrorMsgDiv"></div>
	<div id="basicdistrictDiv"></div>
	<div id="districtDiv" class="selectDiv">
		Select District<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your District" name="districtList" id="voterDataDistrictList" list="districts" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="districtpublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		<option value="10">1-2-2014</option>
		<option value="9">1-1-2014</option>
		<option value="7">1-1-2013</option>
		<option value="8">1-2-2013</option>
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="ajaxLoad"><img src="images/icons/search.gif" /></span>
	

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="districtwisevoterDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="districtvoterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;" id="districtajaxImage" />
		</div>
	</div>
	</div>
 </fieldset>
 
 
 
</div>	
<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:500px;">Populate Voters Basic Info Data To Intermediate Tables</div>
 <fieldset>
    <div id="basicInfoErrorMsgDiv"></div>
	<div id="basicInfoConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="basicInfoConstituencyList" list="allConstituenciesList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="basicInfoPublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="basicInfoAjaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="voterBasicInfoInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="voterDataDeleteBtn"  onclick="deleteVoterBasicInfo();"/>
			<img src="./images/icons/search.gif" style="display:none;" id="basicInfoAjaxImage" />
		</div>
	</div>
 </fieldset>
</div>	
<!-- End voters Basic Info Div -->
<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:500px;">Populate Voting Trendz Info Data To Intermediate Tables</div>
 <fieldset>
    <div id="votingTrendzErrorMsgDiv"></div>
	<div id="votingTrendzConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="votingTrendzConstituencyList" list="allConstituenciesList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="votingTrendzPublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="votingTrendzAjaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="votingTrendzInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="votingTrendzDeleteBtn"  onclick="deleteVotingTendzInfo();"/>
			<img src="./images/icons/search.gif" style="display:none;" id="votingTrendzAjaxImage" />
		</div>
	</div>
 </fieldset>
</div>	
<!-- voters Caste Div -->
<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:413px;">Populate Caste Voters Data To Intermediate Tables</div>
 <fieldset>
    <div id="casterrorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="castconstituencyList" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="castpublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="castajaxLoad"><img src="images/icons/search.gif" /></span>
	 <div style="margin-top: 20px;">
		<span style="width:40px;">
		
		  <input type="checkbox" id="HamletChecked" value="Hamlet" style="margin-top:-5px;"> Hamlet
	     </span>&nbsp;&nbsp;<span>
		  <input type="checkbox" id="BoothChecked" value="Booth" style="margin-top:-5px;"> Booth
		 </span>
		 <input type="checkbox" id="hamletBoothChecked" value="Hamlet Booth" style="margin-top:-5px;"> Hamlet Booth
		<input type="checkbox" id="localityChecked" value="Locality" style="margin-top:-5px;"> Locality
		<input type="checkbox" id="customWardCheckedBox" value="Locality" style="margin-top:-5px;"> Custom Ward
	 </div>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="castvoterDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="castvoterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;" id="castajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	

<!-- voters Party Div -->
<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:413px;">Populate Party Voters Data To Intermediate Tables</div>
 <fieldset>
    <div id="partyerrorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="partyconstituencyList" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="partypublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="partyajaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="partyvoterDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="partyvoterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;" id="partyajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	


<!-- End -->


<!-- voters Modification Info Div -->
<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:450px;">Populate voters Modification Info To Intermediate Tables</div>
 <fieldset>
    <div id="votermodificationerrorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="votermodificationconstiId" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;
         <br>
		Select Publication Date<font class="requiredFont">*</font> <select id="votermodificationpublicationList" class="selectWidth" style="width:172px;height:25px;margin-top:10px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="votermodificationajaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="votermodificationDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="votermodificationvoterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;margin-left: 10px;" id="votermodificationajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	





<div class="headingDiv" style="width:450px;">District wise Populate voters Modification Info To Intermediate Tables</div>
 <fieldset>
    <div id="districtvotermodificationerrorMsgDiv"></div>
	<div id="basicvotermodificationdistrictDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select District<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:63px;" cssClass="selectWidth" label="Select Your Constituency" name="districtconstituencyList" id="districtvotermodificationconstiId" list="districts" listKey="id" listValue="name"/> &nbsp;&nbsp;
         <br>
		Select Publication Date<font class="requiredFont">*</font> <select id="districtvotermodificationpublicationList" class="selectWidth" style="width:172px;height:25px;margin-top:10px;" name="publicationDateList" >
		<option value="10">1-2-2014</option>
		<option value="9">1-1-2014</option>
		<option value="7">1-1-2013</option>
		<option value="8">1-2-2013</option>
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="districtvotermodificationajaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="districtvoterDataInsertDiv" style="margin-top:10px;">
			<input type="button" class="btn btn-info" value="Submit" id="districtvotermodificationDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="districtvotermodificationvoterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;margin-left: 10px;" id="districtvotermodificationajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	



<!-- voters Modification Info Div End -->

<!-- ConstituencyHierarchyInfo Div Start -->

<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:450px;">Constituency Hierarchy Info</div>
 <fieldset>
    <div id="conHierErrorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="constituencyHierarchyId" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="consHierarchyPublicationList" class="selectWidth" style="width:172px;height:25px;" name="consHierarchyPublicationList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="votermodificationajaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="conHierDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="conHierDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;margin-left: 10px;" id="votermodificationajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	

<!-- ConstituencyHierarchyInfo Div End -->

</div>

<script type="text/javascript">

function populateVoterData()
{
	var constituencyId = $("#constituencyList").val();
	var jsObj=
		{
				
			id:constituencyId,
			task:"task"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populateVoterDataAction.action?"+rparam;						
		callAjax(jsObj,url);
}

$(document).ready(function(){

	$('#errorMsgDiv').html('');
	$('#voterDataConstituencyList').change(function(){
var selectElmt = "publicationDateList";
	   var constituencyId = $('#voterDataConstituencyList').val();
	   if(constituencyId == 0)
	   {
		 $('#errorMsgDiv').html('Please Select Constituency.');
		 return;
	   }
	   $('#ajaxLoad').css('display','block');
	  var jsObj=
		{
			selectElmt:selectElmt,	
			selected:constituencyId,
			task:"getPublicationListforVoterData"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populatevoterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		

	});

	$('#basicInfoConstituencyList').change(function(){

	   var constituencyId = $('#basicInfoConstituencyList').val();
	   if(constituencyId == 0)
	   {
		 $('#basicInfoErrorMsgDiv').html('Please Select Constituency.');
		 return;
	   }
	   $('#basicInfoAjaxLoad').css('display','block');
	  var jsObj=
		{
				
			id:constituencyId,
			task:"getPublicationList",
			subtask : "voterBasicInfo",
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPublicationListForVoterDataAction.action?"+rparam;						
		callAjax(jsObj,url);
		

	});
	
	$('#votingTrendzConstituencyList').change(function(){
	var id = $("#votingTrendzConstituencyList").val();
	 var selectElmt = "votingTrendzPublicationDateList";
	  if(id == 0)
	  {
	   $('#votingTrendzErrorMsgDiv').html('Please Select Constituency.');
		return;
	  }
	
	 $('#votingTrendzAjaxLoad').css('display','block');
	 var jsObj=
	 {
		selected:id,
		selectElmt:selectElmt,
		task:"getPublicationDateForVotingTrendz"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "populatevoterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);
	});




  $("#constituencyList").change(function(){
	 var id = $("#constituencyList").val();
	  if(id == 0)
	  {
	   $("#errorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#ajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		task:"getPublicationDate"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "populatevoterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});

	$("#castconstituencyList").change(function(){
	 var id = $("#castconstituencyList").val();
	 var selectElmt = "castpublicationDateList";
	  if(id == 0)
	  {
	   $("#casterrorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#castajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		selectElmt:selectElmt,
		task:"getPublicationDateForCast"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "populatevoterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});

	$("#partyconstituencyList").change(function(){
	 var id = $("#partyconstituencyList").val();
	  var selectElmt = "partypublicationDateList";
	  if(id == 0)
	  {
	   $("#partyerrorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#partyajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		selectElmt:selectElmt,
		task:"getPublicationDateForParty"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "populatevoterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});

	$("#votermodificationconstiId").change(function(){
	 var id = $("#votermodificationconstiId").val();
	 var selectElmt = "votermodificationpublicationList";
	  if(id == 0)
	  {
	   $("#votermodificationerrorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#votermodificationajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		selectElmt:selectElmt,
		task:"getPublicationDateForModification"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "populatevoterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});
	$("#voterDataInsertBtn").click(function(){
		var hamletChecked= $("#voterInfoHamletChecked").is(':checked');
		var hamletBoothChecked = $("#voterInfoHamletBoothChecked").is(':checked');
		var localityChecked = $("#voterInfoLocalityChecked").is(':checked');
		var constituencyId = $("#voterDataConstituencyList").val(); 
		var publicationDateId = $("#publicationDateList").val();
		if(constituencyId == 0)
		{
			$("#errorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#errorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#voterDataInsertBtn").attr("disabled", "disabled");
		$("#ajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  hamletChecked:hamletChecked,
		  hamletBoothChecked:hamletBoothChecked,
		  localityChecked:localityChecked,
		  task:"insertVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});

	$("#voterBasicInfoInsertBtn").click(function(){

		var constituencyId = $("#basicInfoConstituencyList").val(); 
		var publicationDateId = $("#basicInfoPublicationDateList").val();
		if(constituencyId == 0)
		{
			$("#basicInfoErrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#basicInfoErrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#voterBasicInfoInsertBtn").attr("disabled", "disabled");
		$("#basicInfoAjaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"insertVotersBasicInfo"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersBasicInfoToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});
	$("#votingTrendzInsertBtn").click(function(){

		var constituencyId = $("#votingTrendzConstituencyList").val(); 
		var publicationDateId = $("#votingTrendzPublicationDateList").val();
		if(constituencyId == 0)
		{
			$("#votingTrendzErrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#votingTrendzErrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#votingTrendzInsertBtn").attr("disabled", "disabled");
		$("#votingTrendzAjaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"insertvotingTrendzInfo"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotingTrendzToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});

$("#castvoterDataInsertBtn").click(function(){
	
var castconstituencyId = $("#castconstituencyList").val(); 

var checked = $('input[type=checkbox]').is(':checked');
var hamletChecked= $("#HamletChecked").is(':checked');
var boothChecked= $("#BoothChecked").is(':checked');
var hamletBoothChecked = $("#hamletBoothChecked").is(':checked');
var localityChecked = $("#localityChecked").is(':checked');
var wardChecked = $("#customWardCheckedBox").is(':checked');

		var castpublicationDateId = $("#castpublicationDateList").val();
		if(castconstituencyId == 0)
		{
			$("#casterrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(castpublicationDateId == 0 || castpublicationDateId == null)
		{
		  $("#casterrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#castvoterDataInsertBtn").attr("disabled", "disabled");
		$("#castajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :castconstituencyId,
		  publicationDateId : castpublicationDateId,
		  hamletChecked:hamletChecked,
		  boothChecked:boothChecked,
		  hamletBoothChecked:hamletBoothChecked,
		  localityChecked:localityChecked,
		  wardChecked:wardChecked,
		  task:"insertCastVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersCastDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});
	$("#castvoterDataDeleteBtn").click(function(){
		var castconstituencyId = $("#castconstituencyList").val(); 
		var castpublicationDateId = $("#castpublicationDateList").val();
		if(castconstituencyId == 0)
		{
			$("#errorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(castpublicationDateId == 0 || castpublicationDateId == null)
		{
		  $("#casterrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#castvoterDataDeleteBtn").attr("disabled", "disabled");
		$("#castajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :castconstituencyId,
		  publicationDateId : castpublicationDateId,
		  task:"deletecastVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVoterscastDataFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
	});



$("#partyvoterDataInsertBtn").click(function(){
	
var partyconstituencyId = $("#partyconstituencyList").val(); 
		var partypublicationDateId = $("#partypublicationDateList").val();
		if(partyconstituencyId == 0)
		{
			$("#partyerrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(partypublicationDateId == 0 || partypublicationDateId == null)
		{
		  $("#partyerrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#partyvoterDataInsertBtn").attr("disabled", "disabled");
		$("#partyajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :partyconstituencyId,
		  publicationDateId : partypublicationDateId,
		  task:"insertPartyVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersPartyDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});
	$("#partyvoterDataDeleteBtn").click(function(){
		var partyconstituencyId = $("#partyconstituencyList").val(); 
		var partypublicationDateId = $("#partypublicationDateList").val();
		if(partyconstituencyId == 0)
		{
			$("#partyerrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(partypublicationDateId == 0 || partypublicationDateId == null)
		{
		  $("#partyerrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#partyvoterDataDeleteBtn").attr("disabled", "disabled");
		$("#partyajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :partyconstituencyId,
		  publicationDateId : partypublicationDateId,
		  task:"deletepartyVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVotersPartyDataFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
	});

$("#votermodificationDataInsertBtn").click(function(){
	
	$("#votermodificationerrorMsgDiv").html('');
		var votermodificationconstiId = $("#votermodificationconstiId").val(); 
		var publicationDateId = $("#votermodificationpublicationList").val();
		if(votermodificationconstiId == 0)
		{
			$("#votermodificationerrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#votermodificationerrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#votermodificationDataInsertBtn").attr("disabled", "disabled");
		$("#votermodificationajaxImage").css("display","inline-block");
		
		var jsObj=
		{
		  id				  :votermodificationconstiId,
		  publicationDateId : publicationDateId,
		  task:"insertVoterModificationData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVoterModificationDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});

	$("#votermodificationvoterDataDeleteBtn").click(function(){
		var votermodificationconstiId = $("#votermodificationconstiId").val(); 
		var publicationDateId = $("#votermodificationpublicationList").val();
		if(votermodificationconstiId == 0)
		{
			$("#votermodificationerrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#votermodificationerrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#votermodificationvoterDataDeleteBtn").attr("disabled", "disabled");
		$("#votermodificationajaxImage").css("display","inline-block");
		
		var jsObj=
		{
		  id				  :votermodificationconstiId,
		  publicationDateId : publicationDateId,
		  task:"deletevotermodification"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deletevotermodificationFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
	});

	
});

$("#voterDataDeleteBtn").click(function(){
	
        var constituencyId = $("#voterDataConstituencyList").val(); 
		var publicationDateId = $("#publicationDateList").val();
		if(constituencyId == 0)
		{
			$("#errorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#errorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#voterDataDeleteBtn").attr("disabled", "disabled");
		$("#ajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"deleteVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVotersDataFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);


});

$("#districtvoterDataDeleteBtn").click(function(){
	
        var constituencyId = $("#voterDataDistrictList").val(); 
		var publicationDateId = $("#districtpublicationDateList").val();
		if(constituencyId == 0)
		{
			$("#districterrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#districterrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#districvoterDataDeleteBtn").attr("disabled", "disabled");
		$("#districtajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"districtdeleteVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "districtwisedeleteVotersDataFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);


});

$("#constituencyHierarchyId").change(function(){
    
	 $("#conHierErrorMsgDiv").html('');
	var id = $("#constituencyHierarchyId").val();
	 var selectElmt = "consHierarchyPublicationList";
	  if(id == 0)
	  {
	   $("#conHierErrorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#votermodificationajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		selectElmt:selectElmt,
		task:"getPublicationDateForModification"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "voterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

   });


   $("#conHierDataInsertBtn").click(function(){
	   
	  var constituencyId = $("#constituencyHierarchyId").val();
	  var publicationId = $("#consHierarchyPublicationList").val();
      var jsObj=
	 {
		constituencyId:constituencyId,
		publicationId:publicationId,
		task:"insertConstituencyBasicData"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "insertConstituencyBasicDataAction.action?"+rparam;	
	 callAjax(jsObj,url);

	   
   });

   $("#conHierDataDeleteBtn").click(function(){
	   
	  var constituencyId = $("#constituencyHierarchyId").val();
	  var publicationId = $("#consHierarchyPublicationList").val();
      var jsObj=
	 {
		constituencyId:constituencyId,
		publicationId:publicationId,
		task:"deleteConstituencyBasicData"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "deleteConstituencyBasicDataAction.action?"+rparam;	
	 callAjax(jsObj,url);
	 
   });
  

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getPublicationDate")
								{
									buildPublicationDateList(myResults);
									
								}
								else if(jsObj.task == "getPublicationDateForCast")
								{
									
									buildPublicationDateList1(myResults,jsObj.selectElmt);
								}
								
								
								else if(jsObj.task == "getPublicationDateForParty")
								{
									buildPublicationDateList1(myResults,jsObj.selectElmt);
								}
								else if(jsObj.task == "getPublicationDateForModification")
								{
									buildPublicationDateList1(myResults,jsObj.selectElmt);
								}
								else if(jsObj.task == "insertVotersData")
								{
									showInsertVoterDataStatus(myResults);
								}

								else if(jsObj.task == "insertVotersBasicInfo")
								{
									showInsertVotersBasicInfoStatus(myResults);
								}
								else if(jsObj.task == "insertvotingTrendzInfo")
								{
								showInsertVotingTrendzInfoStatus(myResults);
								}
								else if(jsObj.task == "insertCastVotersData")
								{
									showinsertCastVotersDataStatus(myResults);
								}
								else if(jsObj.task == "deletecastVotersData")
								{
									showDeleteVoterCastDataStatus(myResults);
								}
								else if(jsObj.task == "deletepartyVotersData")
								{
									showDeleteVoterPartyDataStatus(myResults);
								}
								else if(jsObj.task == "deleteVotersData")
								{
									showDeleteVoterDataStatus(myResults);

								}

								else if(jsObj.task == "deletevotermodification")
								{
									showdeletevotermodificationStatus(myResults);
								}

								else if(jsObj.task == "insertVoterModificationData")
								{
									showVoterModificationDataStatus(myResults);
								}

								else if(jsObj.task == "getPublicationList" || jsObj.task == "getPublicationListforVoterData")
								{
									if(jsObj.subtask != null && 
										jsObj.subtask == 'voterBasicInfo')
										buildPublicationListForBasicInfo(myResults);
									else
										buildPublicationList(myResults);
								}
								
								else if(jsObj.task == "deleteVoterBasicInfo")
								{
									showStatusForVoterBasicInfo(myResults);
								}
								else if(jsObj.task == "deletevotingTrendzInfo")
								{
								showStatusForVotingTendzInfo(myResults);
								}
								else if(jsObj.task == "getPublicationDateForVotingTrendz")
								{
									
									buildPublicationListForvotingTrendzInfo(myResults,jsObj.selectElmt);
								}

								else if(jsObj.task == "insertConstituencyBasicData")
								 showConstituencyHierarchyInsertDataStatus(myResults);

								else if(jsObj.task == "deleteConstituencyBasicData")
								 showConstituencyHierarchyDeleteDataStatus(myResults);
								
								else if(jsObj.task == "districtdeleteVotersData")
									showdistrictDeleteVoterDataStatus(myResults);
								else if(jsObj.task == "districtwiseinsertVotersData")
									showdistrictInsertVoterDataStatus(myResults);
								else if(jsObj.task == "districtinsertVoterModificationData")
									showdistrictVoterModificationDataStatus(myResults);
								else if(jsObj.task == "districtdeletevotermodification")
								showdistrictdeletevotermodificationStatus(myResults);
							}
								catch (e) {
							     //$("#votersEditSaveAjaxImg").hide();
							     $("#votersEditSaveButtnImg").removeAttr("disabled");
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

	function showInsertVoterDataStatus(result)
	{
		$("#ajaxImage").css("display","none");
		$("#voterDataInsertBtn").removeAttr("disabled");
		//document.getElementById('constituencyList').selectedIndex = 0;
		//document.getElementById('publicationDateList').selectedIndex = 0;
		if(result.resultCode == 0)
		{
			$("#errorMsgDiv").html("Voters Data Inserted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#errorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}
	function showdistrictInsertVoterDataStatus(result)
	{
		$("#districtajaxImage").css("display","none");
		$("#districtwisevoterDataInsertBtn").removeAttr("disabled");
		$("#basicdistrictDiv").css("display","block");
		
		if(result.length > 0)
		{
			$("#districterrorMsgDiv").html("Voters Data Inserted Successfully.").css("color","green");
			if(result[0].name == null)
			$("#basicdistrictDiv").html('<b> Total Constituencies</b> : '+result[0].totalCount+'');
		     else
			if(result[0].name != null)
			$("#basicdistrictDiv").html('<b> Total Constituencies</b> : '+result[0].totalCount+' <b>Data not Available constituencies:</b>');
			for(var i in result)
			{
			if(result[i].name != null)
		    $("#basicdistrictDiv").append(''+result[i].name+'');
			}
			return;
		}
		else
		{
			$("#districterrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}

	}

	function showInsertVotersBasicInfoStatus(result)
	{
		$("#basicInfoAjaxImage").css("display","none");
		$("#voterBasicInfoInsertBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#basicInfoErrorMsgDiv").html("Voters Basic Info Inserted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#basicInfoErrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}
	function showInsertVotingTrendzInfoStatus(result)
	{
		$("#votingTrendzAjaxImage").css("display","none");
		$("#votingTrendzInsertBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#votingTrendzErrorMsgDiv").html("VotingTrendz Info Inserted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#votingTrendzErrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}

	
function showinsertCastVotersDataStatus(result)
{
$("#castajaxImage").css("display","none");
		$("#castvoterDataInsertBtn").removeAttr("disabled");
		//document.getElementById('constituencyList').selectedIndex = 0;
		//document.getElementById('publicationDateList').selectedIndex = 0;
		if(result.resultCode == 0)
		{
			$("#casterrorMsgDiv").html("Caste Voters Data Inserted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#casterrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
}
function showDeleteVoterDataStatus(result)
	{
		$("#ajaxImage").css("display","none");
		$("#voterDataDeleteBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#errorMsgDiv").html("Voters Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#errorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}

function showdistrictDeleteVoterDataStatus(result)
{
	$("#districtajaxImage").css("display","none");
		$("#districtvoterDataDeleteBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#districterrorMsgDiv").html("Voters Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#districterrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
}
	function showDeleteVoterCastDataStatus(result)
	{

		$("#castajaxImage").css("display","none");
		$("#castvoterDataDeleteBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#casterrorMsgDiv").html("Caste Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#casterrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}

	function showDeleteVoterPartyDataStatus(result)
	{
		$("#partyajaxImage").css("display","none");
		$("#partyvoterDataDeleteBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#partyerrorMsgDiv").html("Party Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#partyerrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}
	function showdeletevotermodificationStatus(result)
	{
		$("#votermodificationajaxImage").css("display","none");
		$("#votermodificationvoterDataDeleteBtn").removeAttr("disabled");

		if(result.resultCode == 0)
		{
			$("#votermodificationerrorMsgDiv").html("Voter modification Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#votermodificationerrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}

	}
	function showdistrictdeletevotermodificationStatus(result)
	{
		$("#districtvotermodificationajaxImage").css("display","none");
		$("#districtvotermodificationvoterDataDeleteBtn").removeAttr("disabled");

		if(result.resultCode == 0)
		{
			$("#districtvotermodificationerrorMsgDiv").html("Voter modification Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#districtvotermodificationerrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}

	}
	function buildPublicationDateList1(results,selectElmt)
	{
	
	if(selectElmt == "castpublicationDateList")
	document.getElementById('castajaxLoad').style.display = 'none';
	if(selectElmt == "partypublicationDateList")
	document.getElementById('partyajaxLoad').style.display = 'none';
	if(selectElmt == "votermodificationpublicationList")
    document.getElementById('votermodificationajaxLoad').style.display = 'none';
	var selectedElmt=document.getElementById(selectElmt);
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}





function buildPublicationList(results)
{
	$('#ajaxLoad').css('display','none');
	var selectedElmt = document.getElementById("publicationDateList");
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}

function buildPublicationListForBasicInfo(results)
{
	$('#basicInfoAjaxLoad').css('display','none');
	var selectedElmt = document.getElementById("basicInfoPublicationDateList");
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}
function buildPublicationListForvotingTrendzInfo(results)
{

	$('#votingTrendzAjaxLoad').css('display','none');
	var selectedElmt = document.getElementById("votingTrendzPublicationDateList");
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}
function showVoterModificationDataStatus(result)
{
	$("#votermodificationerrorMsgDiv").html('');
	$("#votermodificationDataInsertBtn").removeAttr("disabled");
		$("#votermodificationajaxImage").css("display","none");
		if(result.resultCode == 0)
		{
			$("#votermodificationerrorMsgDiv").html("Voters Data Inserted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#votermodificationerrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
}
function showdistrictVoterModificationDataStatus(result)
{
	$("#districtvotermodificationerrorMsgDiv").html('');
	$("#districtvotermodificationDataInsertBtn").removeAttr("disabled");
	$("#districtvotermodificationajaxImage").css("display","none");
	$("#basicvotermodificationdistrictDiv").css("display","block");
		if(result.length > 0)
		{
			$("#districtvotermodificationerrorMsgDiv").html("Voters Data Inserted Successfully.").css("color","green");
			if(result[0].name == null)
			$("#basicvotermodificationdistrictDiv").html('<b> Total Constituencies</b> : '+result[0].totalCount+'');
		else
			if(result[0].name != null)
			$("#basicvotermodificationdistrictDiv").html('<b> Total Constituencies</b> : '+result[0].totalCount+' <b>Data not Available constituencies:</b>');
			for(var i in result)
			{
			if(result[i].name != null)
		    $("#basicvotermodificationdistrictDiv").append(''+result[i].name+'');
			}
				return;
		}
		else
		{
			$("#districtvotermodificationerrorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
}

function deleteVoterBasicInfo()
{
	$("#basicInfoErrorMsgDiv").html("");
	var constiId = $("#basicInfoConstituencyList").val();
	if(constiId == 0)
		{
			$("#basicInfoErrorMsgDiv").html('Please Select Constituency.').css("color","red");
			return;
		}
	var jsObj=
		{
		  id:constiId,
		  task : "deleteVoterBasicInfo"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVoterBasicInfoFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
}
function showStatusForVoterBasicInfo(result)
{
if(result.resultCode == 0)
	{
$("#basicInfoErrorMsgDiv").html('Voter Basic Data Deleted successufully').css("color","green");
	}
else
$("#basicInfoErrorMsgDiv").html('Error Occured try Again.').css("color","red");
}

function deleteVotingTendzInfo()
{
	$("#votingTrendzErrorMsgDiv").html("");
	var constiId = $("#votingTrendzConstituencyList").val();
	if(constiId == 0)
		{
			$("#votingTrendzErrorMsgDiv").html('Please Select Constituency.').css("color","red");
			return;
		}
	var jsObj=
		{
		  id:constiId,
		  task : "deletevotingTrendzInfo"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVotingTrendzFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
}
function showStatusForVotingTendzInfo(result)
{
if(result.resultCode == 0)
	{
$("#votingTrendzErrorMsgDiv").html('Data Deleted successufully').css("color","green");
	}
else
$("#votingTrendzErrorMsgDiv").html('Error Occured try Again.').css("color","red");
}


function showConstituencyHierarchyInsertDataStatus(result)
{
	$("#conHierErrorMsgDiv").html('');
  if(result.resultCode == 0)
	$("#conHierErrorMsgDiv").html('Data inserted successufully').css("color","green");
  else
	$("#conHierErrorMsgDiv").html('Error Occured try Again.').css("color","red");
}

function showConstituencyHierarchyDeleteDataStatus(result)
{
	$("#conHierErrorMsgDiv").html('');
  if(result.resultCode == 0)
	$("#conHierErrorMsgDiv").html('Data deleted successufully').css("color","green");
  else
	$("#conHierErrorMsgDiv").html('Error Occured try Again.').css("color","red");
}

$("#districtwisevoterDataInsertBtn").click(function(){
	
		var constituencyId = $("#voterDataDistrictList").val(); 
		var publicationDateId = $("#districtpublicationDateList").val();
		if(constituencyId == 0)
		{
			$("#districterrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#districterrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		//$("#voterDataInsertBtn").attr("disabled", "disabled");
		$("#districtajaxImage").css("display","block");
		$("#basicdistrictDiv").css("display","none");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"districtwiseinsertVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "districtwiseinsertVotersDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});
	
	$("#districtvotermodificationvoterDataDeleteBtn").click(function(){
		var votermodificationconstiId = $("#districtvotermodificationconstiId").val(); 
		var publicationDateId = $("#districtvotermodificationpublicationList").val();
		if(votermodificationconstiId == 0)
		{
			$("#districtvotermodificationerrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#districtvotermodificationerrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#districtvotermodificationvoterDataDeleteBtn").attr("disabled", "disabled");
		$("#districtvotermodificationajaxImage").css("display","inline-block");
		
		var jsObj=
		{
		  id				  :votermodificationconstiId,
		  publicationDateId : publicationDateId,
		  task:"districtdeletevotermodification"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "districtdeletevotermodificationFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
	});
$("#districtvotermodificationDataInsertBtn").click(function(){
	
	$("#districtvotermodificationerrorMsgDiv").html('');
		var votermodificationconstiId = $("#districtvotermodificationconstiId").val(); 
		var publicationDateId = $("#districtvotermodificationpublicationList").val();
		if(votermodificationconstiId == 0)
		{
			$("#districtvotermodificationerrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#districtvotermodificationerrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#districtvotermodificationDataInsertBtn").attr("disabled", "disabled");
		$("#districtvotermodificationajaxImage").css("display","inline-block");
		$("#basicvotermodificationdistrictDiv").css("display","none");
		var jsObj=
		{
		  id				  :votermodificationconstiId,
		  publicationDateId : publicationDateId,
		  task:"districtinsertVoterModificationData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "districtinsertVoterModificationDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});
Data();
</script>
</body>
</html>