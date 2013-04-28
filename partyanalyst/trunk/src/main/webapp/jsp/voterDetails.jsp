<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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

<title>Voters Details</title>

</head>
<style>
 
#hamletAndBoothCount ,#voterTypeId
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
#headingDiv
{
	background: none repeat scroll 0 0 #49AFCD;
    border-radius: 4px 4px 4px 4px;
    color: white;
    font-family: arial;
    font-size: 17px;
    height: 25px;
    margin-bottom: 10px;
    margin-top: 10px;
	font-weight: bolder;
	padding-top: 12px;
	margin-left:20px;
	margin-right: 20px;
}
.table th, .table td {
    border-top: 1px solid #DDDDDD;
    line-height: 20px;
    padding: 8px;
    text-align: left;
    vertical-align: top;
    width: 100px;
}
#mainDiv
{
	border: 1px solid #C3C3C3;
    border-radius: 4px 4px 4px 4px;
    margin-left: 20px;
    margin-right: 20px;
}
#votersByLocationTabContentDiv_body
{
	border: 1px solid #C3C3C3;
    border-radius: 4px 4px 4px 4px;
	width:979px;
	margin-left:10px;
}
#InfluencingPeopleDetailsDiv table th, #impFamilesBasicSubDetailsForHamlet table th {
    background-color: #CDE6FC;
    color: #333333;
    font-family: arial;
    font-size: 12px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}

</style>
<body>
<div id="headingDiv" align="center" >${name} Voter Details</div>
<div id="ajaxImageDiv" align="center" style="margin-top: 50px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>
<div id="mainDiv"  style="display:none;">
<div id="votersCountId"></div>
<div id="hamletAndBoothCount"></div>
<div id="voterTypeId"></div>
</div>

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
<b style="margin-left: 5px">Influencing People</b>:<img title="Politician" alt="Politicion" src="./images/icons/influencing.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Cadre</b>:<img title="Cadre" alt="Politicion" src="./images/icons/cadre.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Politician</b>:<img title="Politician" alt="Politicion" src="./images/icons/politican.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<div id="errorMessageDiv"></div>
</div>
<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable yui-dt table table-bordered table-striped table-hover" style="display:none;">
</div></div>

<div id="influencyPopupDiv"></div>

<script>

var publicationId = "${publicationId}";
var startIndex= "${startIndex}";
var dir="${dir}";
var sort="${sort}";
var results="${results}";
var boothId="${boothId}";
var panchaytId="${panchaytId}";
var maintype="${maintype}";
var hamletId="${hamletId}";
var constituencyId = "${constituencyId}";
var customwardId = "${customwardId}";
var name = "${name}";
var pResults;
var id;
var limit = 100;
var confTrue = false;
function getVoterDetails()
{
	 $("#ajaxImageDiv").css('display','none');
	$('.requiredAttrClass').each(function(){
	  $(this).attr('checked','checked');
    });

	$('.notRequiredAttrClass').each(function(){
		$(this).attr('checked',false);
	});
	$('#atterHeadingDiv').show();
	$('#impFamilySelectedDetails').show();
	$('#imgDescriptionDiv').show();
	if(maintype=="panchayat")
	{
		//getCountOfHamletsAndBoothsInPanchayat();
		id = panchaytId;
		buildVotersByLocPanchayatDataTable1();
	}
	if(maintype=="booth")
	{
		id = boothId;
		buildVotersByLocBoothDataTable1();
	}
	if(maintype=="hamlet")
	{
		id = hamletId;
		buildVotersByLocHamletTestDataTable1();
	}
	if(maintype=="customWard")
	{
		id = customwardId;
		buildVotersByLocCustomWardDataTable1();
	}

}


function buildVotersByLocPanchayatDataTable1()
{
//publicationId = mainpublicationId;
//panchaytId =  mainreqid;

	$('#votersByLocationTabContentDiv_body').show();
if(panchaytId == "0" || publicationId == "0")
	return false;
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("firstName");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var isInfluencePerson=oRecord.getData("isInfluencePerson");
		var isCadere = oRecord.getData("isCadrePerson");
		var isPolitician = oRecord.getData("isPoliticion");
		if(isInfluencePerson == true)
		{
			str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
		}
		if(isCadere == true)
		{
			str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
		}
		if(isPolitician == true)
		{
			str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
		}
		elLiner.innerHTML =str;
	}
YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var voterId= oRecord.getData("voterIds");


		str+='<a href="javaScript:{getInfluencePeopleOfAnUser('+voterId+');}">Click here</a>';
		//var boothId=oRecord.getData("boothId"); 
		//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
		//elLiner.innerHTML=id;
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},
{key:"firstName", label: "Name",width:100, sortable: true},
{key:"voterIDCardNo", label: "Voter ID",width:120,sortable: true},
{key:"partNo", label: "Booth No",width:30, sortable:true},
{key:"gender", label: "Gen", width:15, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "HNO",width:20, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:70,sortable:true},
{key:"Type", label: "Type", width:60,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true,width:50},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&panchaytId="+panchaytId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [{key:"serialNo", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};
var myConfigs = {
initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.results;
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};


}

function buildVotersByLocCustomWardDataTable1()
{


	$('#votersByLocationTabContentDiv_body').show();
if(customwardId == "0" || customwardId == "0")
	return false;
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("firstName");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var isInfluencePerson=oRecord.getData("isInfluencePerson");
		var isCadere = oRecord.getData("isCadrePerson");
		var isPolitician = oRecord.getData("isPoliticion");
		if(isInfluencePerson == true)
		{
			str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
		}
		if(isCadere == true)
		{
			str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
		}
		if(isPolitician == true)
		{
			str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
		}
		elLiner.innerHTML =str;
	}
YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var voterId= oRecord.getData("voterIds");


		str+='<a href="javaScript:{getInfluencePeopleOfAnUser('+voterId+');}">Click here</a>';
		//var boothId=oRecord.getData("boothId"); 
		//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
		//elLiner.innerHTML=id;
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},
{key:"firstName", label: "Name",width:100, sortable: true},
{key:"voterIDCardNo", label: "Voter ID",width:120,sortable: true},
{key:"partNo", label: "Booth No",width:30, sortable:true},
{key:"gender", label: "Gen", width:15, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "HNO",width:20, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:70,sortable:true},
{key:"Type", label: "Type", width:60,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true,width:50},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&customwardId="+customwardId+"&constiId="+constituencyId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [{key:"serialNo", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};
var myConfigs = {
initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.results;
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};


}


function buildVotersByLocBoothDataTable1()
{
//var boothId = mainreqid;
var publicationDateId = publicationId;
$('#votersByLocationTabContentDiv_body').show();
if(boothId == "0" || boothId == null || publicationDateId == null || publicationDateId == "0")
	return false;

	YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var name = oRecord.getData("firstName");
		var influencePerson=oRecord.getData("influencePerson");
		str += '<ul class="dd_menu">';
		str += ' <li><i class="icon-th-list"></i>';
		str += ' <ul>';
		str += ' <li>';
		str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
		str += ' </li>';
		str += ' <li>';
		str += ' <a href= "javascript:{};" onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
		str += ' </li>';
		str += ' <li>';
		str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
		str += ' </li>';
		str += ' <li>';
		str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
		str += ' </li>';
		str += ' <li>';
		str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
		str += ' </li>';
		str += ' </ul>';
		str += ' </li>';
		str += ' </ul>';
		elLiner.innerHTML =str;
	}
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var isInfluencePerson=oRecord.getData("isInfluencePerson");
		var isCadere = oRecord.getData("isCadrePerson");
		var isPolitician = oRecord.getData("isPoliticion");
		if(isInfluencePerson == true)
		{
			str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
		}
		if(isCadere == true)
		{
			str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
		}
		if(isPolitician == true)
		{
			str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
		}
		elLiner.innerHTML =str;
	}
var votersByLocBoothColumnDefs = [
{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},
{key:"firstName", label: "Name",width:100, sortable: true},
{key:"voterIDCardNo", label: "Voter ID",width:120,sortable: true},
{key:"partNo", label: "Booth No",width:30, sortable:true},
{key:"gender", label: "Gen", width:15, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "HNO",width:20, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:70,sortable:true},
{key:"Type", label: "Type", width:60,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true,width:50},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];
YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
	var id = oRecord.getData("voterIds");
	var name = oRecord.getData("firstName");
	elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId="+boothId+"&publicationId="+publicationDateId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"serialNo", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.results;
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};

}

function buildVotersByLocHamletTestDataTable1()
{

YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
$('#votersByLocationTabContentDiv_body').show();
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("firstName");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var isInfluencePerson=oRecord.getData("isInfluencePerson");
		var isCadere = oRecord.getData("isCadrePerson");
		var isPolitician = oRecord.getData("isPoliticion");
		if(isInfluencePerson == true)
		{
			str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
		}
		if(isCadere == true)
		{
			str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
		}
		if(isPolitician == true)
		{
			str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
		}
		elLiner.innerHTML =str;
	}
YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var voterId= oRecord.getData("voterIds");


		str+='<a href="javaScript:{getInfluencePeopleOfAnUser('+voterId+');}">Click here</a>';
		//var boothId=oRecord.getData("boothId"); 
		//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
		//elLiner.innerHTML=id;
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "Voter ID",width:120,sortable: true},
{key:"partNo", label: "Booth No",width:30, sortable:true},
{key:"gender", label: "Gen", width:20, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "HNO",width:20, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:70,sortable:true},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true,width:50},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&hamletId="+hamletId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"serialNo", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};

var myConfigs = {
initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.results;
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}


	  /* $("#votersOuterDiv1").dialog({ 
					title:'Voters Details',
					height: 'auto',
					width: 950,
					show: "blind",
					modal: true,
					overlay: { opacity: 0.5, background: 'black'},
					 buttons: {
				   "Close":function() {$(this).dialog("close")}
					   }	

	   });*/

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}

function getImpFamilyDetails()
{
	if(maintype == "booth")
	{
		var jsObj=
			{
					
				type:maintype,
				id:boothId,
				publicationDateId:publicationId,
				typename:name,
				constituencyId:constituencyId,
				buildType:'hamlet',
				requestFor:"",
				task:"importantFamiliesinfo"
	
			}
		var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
			
		var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
		callAjax(jsObj,url1);
	}
	else if(maintype == "hamlet")
	{
		var jsObj=
			{
					
				type:maintype,
				id:hamletId,
				publicationDateId:publicationId,
				typename:name,
				constituencyId:constituencyId,
				buildType:'hamlet',
				requestFor:"",
				task:"importantFamiliesinfo"
	
			}
		var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
			
		var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
		callAjax(jsObj,url1);
	}

else if(maintype == "customWard")
	{
		var jsObj=
			{
					
				type:maintype,
				id:customwardId,
				publicationDateId:publicationId,
				typename:name,
				constituencyId:constituencyId,
				buildType:'customWard',
				requestFor:"",
				task:"importantFamiliesinfo"
	
			}
		var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
			
		var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
		callAjax(jsObj,url1);
	}

	else
	{
		var jsObj=
			{
					
				type:maintype,
				id:panchaytId,
				publicationDateId:publicationId,
				typename:name,
				constituencyId:constituencyId,
				buildType:'booth',
				requestFor:"",
				task:"importantFamiliesinfo"
	
			}
		var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
			
		var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
		callAjax(jsObj,url1);
	}
	

}

function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);					
			if(jsObj.task == "importantFamiliesinfo")
			{
				$('#mainDiv').show();
				buildVotersCount(myResults,jsObj);
			}			
			else if (jsObj.task == "getInfluencingPeopleCount")
			{
				buildVoterTypeDetails(myResults,jsObj);
			}	
			else if (jsObj.task == "ckeckForVoterId")
			{
				buildVoterToSelectedType(myResults,jsObj);
			}
			else if (jsObj.task == "districtsInState")
			{
				buildDistricts(myResults);
			}
			else if (jsObj.task == "constituenciesInDistrict")
			{
				buildConstituencies(myResults);
			}
			else if (jsObj.task == "subRegionsInConstituency")
			{
				buildMandalsOrMuncipalities(myResults);
			}
			else if (jsObj.task == "boothsInTehsilOrMunicipality")
			{
				buildBooths(myResults);
			}
			else if (jsObj.task == "hamletsOrWardsInRegion")
			{
				buildHamletsOrWardsInRegion(myResults);
			}
			else if (jsObj.task == "getInfluencingPeopleBySearch")
			{
				$('#ajaxImageDiv1').css('display','none');
                buildInfluencePeopleSearchResults(myResults,jsObj.voterId);
			}
			else if (jsObj.task == "getHamletAndBoothCount")
			{
				buildHamletAndBoothCount(myResults);
			}
			else if (jsObj.task == "getUserCategories")
			{
				buildCategories(myResults);
			}
			else if (jsObj.task == "getVoterData")
			{
				
				buildVotersInFamilyWithRetrievedResults(myResults);
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

function openCadreSearchWindow(clickedId){
  
	var cadreWindow = window.open("cadreSearchAction.action?windowTask=Search&voterId="+clickedId,"cadreSearch","scrollbars=yes,height=600,width=750,left=200,top=200");
	cadreWindow.focus();
}
function buildVotersCount(myResults,jsObj)
{
	if(myResults != null)
	{
		 $('#votersCountId').html('<span id="totCount">Total Voters : </span><span><b style="color:navy">'+myResults.totalVoters+'</b></span><span id="maleCount" style="margin-left: 10px;">Male Voters : </span><span><b style="color:navy">'+myResults.totalMaleVoters+' </b></span><span id="femaleCount" style="margin-left: 10px;">Female Voters : </span><span><b style="color:navy">'+myResults.totalFemaleVoters+'</b></span>'); 
		/* $('#votersCountId').html('<table class="table table-bordered table-striped table-hover"> <tr><th>Total Voters</th><td>'+myResults.totalVoters+'</td></tr><tr><th>Male Voters</th><td>'+myResults.totalMaleVoters+'</td></tr><tr><th>Female Voters</th><td>'+myResults.totalFemaleVoters+'</td></tr></table>'); */
	}
	getInfluencingPeopleCount(jsObj.id);
}

function getInfluencingPeopleCount(id)
{
	var jsObj=
			{
		locationValue:id,
		type : maintype,
		publicationDateId:publicationId,
		name             :name,
		task:"getInfluencingPeopleCount"
	};
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
		
    var url1 = "getInfluencingPeopleCountAction.action?"+rparam1;
	callAjax(jsObj,url1);
}

function buildVoterTypeDetails(myResults,jsObj)
{
	var locationValue =jsObj.locationValue;
	var name = jsObj.name;
	var	typeValue = jsObj.type;
	var	publicationDateId = jsObj.publicationDateId;
	if(myResults != null)
	{
		 $('#voterTypeId').html('<span>Influencing People : </span><span class="btnName" onclick="getInfluencingPeopleVotersDetails('+locationValue+',\''+maintype+'\','+publicationDateId+',\'InfluencePeople\' ,'+myResults[0].influencePeopleCount+');"><b style="color:navy;">'+myResults[0].influencePeopleCount+'</b></span><span style="margin-left: 10px;">Cadre : </span><span class="btnName"  onclick="getInfluencingPeopleVotersDetails('+locationValue+',\''+maintype+'\','+publicationDateId+',\'Cadre\','+myResults[0].cadreCount+');"><b style="color:navy;">'+myResults[0].cadreCount+'</b></span><span style="margin-left: 10px;">Politican : </span><span class="btnName" onclick="getInfluencingPeopleVotersDetails('+locationValue+',\''+maintype+'\','+publicationDateId+',\'Politician\','+myResults[0].politicianCount+');"><b style="color:navy;">'+myResults[0].politicianCount+'</b></span>'); 
		
	}
	getVoterDetails();
}

function getInfluencingPeopleVotersDetails(locationValue,typeValue,publicationDateId,btnName,count)
{
	if(count != 0)
	{
		var reqBrowser = window.open("cadreDisplayWindowAction.action?locationValue="+locationValue+"&typeValue="+typeValue+"&publicationDateId="+publicationDateId+"&btnName="+btnName+"&mainreqid="+id+"&maintype="+maintype+"","newBrowser","width=900,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		reqBrowser.focus();
	}
	
}

function checkForVoter(voterId,type,name)
{
	var jsObj = {
			voterId:voterId,
			type : type,
			name : name,
			task:"ckeckForVoterId"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "ckeckForVoterIdAction.action?"+rparam;
	callAjax(jsObj, url);
}

function buildVoterToSelectedType(result,jsObj)
{
	if(jsObj.type == "influencingPeople")
	{
		if(result.resultCode == 1)
		{
			addInfluencingPeople(jsObj.voterId,jsObj.name);
		}
		else 
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a Influencing People</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
	if(jsObj.type == "cadre")
	{
		if(result.resultCode == 1)
		{
			openRegistrationForm(jsObj.voterId,jsObj.name);
		}
		else
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a Cadre</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
	if(jsObj.type == "candidate")
	{
		if(result.resultCode == 1)
		{
		addToPolitician(jsObj.voterId,jsObj.name);
		}
		else
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a politician</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
}

function addInfluencingPeople(voterId,name)
{
	var type='edit';
	var urlStr = "influencingPeopleAction.action?windowTask="+type+"&voterId="+voterId+"&name="+name;
	var browser2 = window.open(urlStr,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
}
function openRegistrationForm(voterId,name)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?voterId="+voterId+"&windowTask="+task+"&name="+name;
	var updateBrowser = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();				
}
function addToPolitician(voterId,name)
{
	var urlStr = "assigningCandidatesToVoterAction.action?voterId="+voterId+"&name="+name;
	var browser2 = window.open(urlStr,"assigningCandidatesToVoterAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
}
function refreshingchildWindowWindow()
{
	if(confTrue == true)
	{
		checkForAttributesToDisplay();
	}
	else
	{
		getVoterDetails();
	}
	
}


function getInfluencePeopleOfAnUser(voterId){

  showInfluencePeopleDialog(voterId)
}



function showInfluencePeopleDialog(voterId){

	$('#searchResultsDiv').html('');
	$('#influencePeopleInnerDiv').show();
	$('#totalCountId').hide();
	$('#searchResultsDiv').hide();
	var str='';
    str+='<form class="form-horizontal">';
   str+='<div class="control-group">';
	 str+='<span><label class="control-label" style="font-size: 15px;margin-left: 5px;">Enter Name :</label></span><div class="controls"><input id="nameId" type="text" name="name" style="margin-left: 49px;width: 169px;"/></div><span id="nameErrMsg" class="locationErrorMsg" style="float: right;margin-right: 90px;margin-top: -24px;"></span></div>';
	 str+='<div class="control-group"><span><label class="control-label" style="font-size: 15px;margin-left: 10px;">Father Name :</label></span><div class="controls"><input id="fatherNameId" type="text" name="name" style="margin-left: 49px;width: 169px;"/></div></div>';
    
	// str+='<h5>Select Scope</h5>';
    str+='<div class="control-group">';
	  str+='<span><label class="control-label" style="font-size: 15px;margin-left: 2px;">Select Scope</label></span>';
	  str+='<div class="controls">';
	  str+='<select id="scopeId" onChange="showLocationsDiv();" style="font-size:14px;font-family:helvetica;width:185px;margin-left: 50px;">';
	        str+='<option value="0" >Select</option>';
			str+='<option value="2">STATE</option>';
			str+='<option value="3">DISTRICT</option>';
			str+='<option value="4">CONSTITUENCY</option>';
			str+='<option value="5">MANDAL</option>';
			str+='<option value="6">VILLAGE</option>';
			str+='<option value="7">MUNCIPAL-CORP-GMC</option>';
			str+='<option value="8">WARD</option>';
			str+='<option value="9">BOOTH</option>';
	  str+='</select>';
	  str+='</div>';
    str+='</div>';

    str+='<div id="locationsDiv" style="display:none;padding:10px;margin:5px;width:525px;">';
	  str+='<div  id="regionstitleDiv" style="display:none;margin-left: 60px;"><h5>Select region</h5></div>';

	  str+='<div id="stateSelect" style="display:none;margin-left: 39px;margin-top: 14px" class="locationDivClass "><div class="control-group"><label class="control-label" style="font-size: 12px;width: 59px;">STATE</label><div class="controls"><select id="stateSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="1">ANDHRA PRADESH</option></select></div></div></div>';

	  str+='<div id="districtSelect" style="display:none;margin-left: 39px;" class="locationDivClass control-group"><label class="control-label" style="font-size: 12px;width: 78px;">DISTRICT</label><div class="controls"><select id="districtSelectId" onChange="getConstituenciesInDistrict();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="districtErrMsg" style="float: right; margin-right: -32px; margin-top: -23px;" class="locationErrorMsg" ></span></div>';

	  str+='<div id="constituencySelect" style="display:none;margin-left: 39px" class="locationDivClass control-group" ><label class="control-label" style="font-size: 12px; width: 114px;">CONSTITUENCY</label><div class="controls"><select id="constituencySelectId" onChange="getMandalsOrMuncipalities();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="constituencyErrMsg" class="locationErrorMsg" style="float: right; margin-right: -73px; margin-top: -21px;"></span></div>';

	  str+='<div id="mandalSelect"  class="locationDivClass control-group" style="display:none;margin-left: 39px"><label class="control-label" style="font-size: 12px; width: 141px;">TEHSIL/MUNCIPALITY</label><div class="controls"><select id="mandalSelectId" onChange="getHamletsOrWards();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="tehsilOrMuncipalityErrMsg" class="locationErrorMsg" style="float: right; margin-right: -62px; margin-top: -23px;"></span></div>';

	  str+='<div id="wardSelect"style="display:none;margin-left: 39px"  class="locationDivClass control-group" ><label class="control-label" style="font-size: 12px;">VILLAGE/WARD/DIVISION</label><div class="controls"><select id="wardSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="villageOrWardErrMsg" class="locationErrorMsg" style="float: right; margin-right: -20px; margin-top: -21px;"></span></div>';

	  str+='<div id="boothSelect" class="locationDivClass control-group" style="display:none;margin-left: 39px"><label class="control-label" style="font-size: 12px; width: 59px;">BOOTH</label><div class="controls"><select id="boothSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="boothErrMsg" class="locationErrorMsg" style="float: right; margin-right: -28px; margin-top: -24px;"></span></div>';

	str+='</div>';
	  str+='</form >';
	str+='<div><a class="btn btn-primary" id="searchButtonId" style="float:left; margin-left: 264px;margin-top: -25px;display:none; color: white;" href="javaScript:{callAjaxToSearchInfluencingPeople('+voterId+');}">Search</a></div>';
	str+='<div id="ajaxImageDiv1" style="display:none;"><img style="margin-left:244px;" src="images/icons/ajaxImg.gif"></div>';


	$('#influencePeopleInnerDiv').html(str);
	
	getDistrictsInAState();

	$('#influencePeopleOuterDiv').dialog({ 
	                            title:'Search Influence People',
	                            height: 'auto',
								width: 750,
								height:750,
								closeOnEscape: false,
								show: "blind",
								hide: "explode",
								modal: true,
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });


	 

}
function showLocationsDiv(){
		$('#searchButtonId').show();
		if($('#scopeId').val() == "0")
		{
			$('#regionstitleDiv').css('display','none');
			$('#locationsDiv').css('display','none');
		}
		else
	    {
			$('#regionstitleDiv').css('display','block');
			$('#locationsDiv').css('display','block');
		}

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').find('option').remove();

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').append('<option value="0">Select</option>');

		$('#districtSelectId').val("0");

	if($('#scopeId').val() == "2")
	{
		$('#stateSelect').css('display','block');
		$('#districtSelect , #constituencySelect , #mandalSelect , #wardSelect , #boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "3")
	{
		$('#stateSelect , #districtSelect').css('display','block');

		$('#constituencySelect , #mandalSelect ,#wardSelect ,#boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "4")
	{
		$('#stateSelect , #districtSelect , #constituencySelect').css('display','block');

		$('#mandalSelect , #wardSelect , #boothSelect').css('display','none');
	
	}
	else if($('#scopeId').val() == "5" || $('#scopeId').val() == "7")
	{
		$('#stateSelect , #districtSelect , #constituencySelect ,#mandalSelect ').css('display','block');
		
		$('#wardSelect , #boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "6" || $('#scopeId').val() == "8")
	{
		$('#stateSelect , #districtSelect , #constituencySelect , #mandalSelect , #wardSelect ').css('display','block');
		
		$('#boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "9")
	{
		$('.locationDivClass').css('display','block');
	}

}
function getConstituenciesInDistrict(){

	var jsObj=
			{
				id:$('#districtSelectId').val(),
				task:"constituenciesInDistrict"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjax(jsObj,url);
}
function getMandalsOrMuncipalities()
{
	if($('#scopeId').val() == "5" || $('#scopeId').val() == "6")
      getMandalsOrMuncipalities1("RURAL");
	else if($('#scopeId').val() == "7" || $('#scopeId').val() == "8")
	 getMandalsOrMuncipalities1("URBAN");
	else if($('#scopeId').val() == "9")
	 getMandalsOrMuncipalities1("");

}
function getMandalsOrMuncipalities1(areaType){

	var jsObj=
			{
				id:$('#constituencySelectId').val(),
				task:"subRegionsInConstituency",
				areaType:areaType
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjax(jsObj,url);

}
function getDistrictsInAState(){
	var jsObj=
			{
				id:1,
				task:"districtsInState"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjax(jsObj,url);
}
function buildDistricts(results)
{

	$('#districtSelectId').find('option').remove();
	$('#districtSelectId').append('<option value="0">Select</option>');

	for(var i=0;i<results.length;i++)
		$('#districtSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildConstituencies(results)
{
  $('#constituencySelectId').find('option').remove();
  $('#constituencySelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#constituencySelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}
function buildMandalsOrMuncipalities(results)
{
 $('#mandalSelectId').find('option').remove();
 $('#mandalSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#mandalSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}
function getHamletsOrWards(){

	if($('#scopeId').val() == "9"){
 	 getHamletsOrRegions();
	 getBoothsInTehsilOrMunicipality();
	}else{
		  getHamletsOrRegions();
  
	}
   //if($('#scopeId').val() == "6")
	  // getHamletsOrRegions('hamlets');
  // else if($('#scopeId').val() == "8")
   	   //getHamletsOrRegions('wards');

}
function getHamletsOrRegions(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				task:"hamletsOrWardsInRegion"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjax(jsObj,url);

}
function getBoothsInTehsilOrMunicipality(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				constId:$('#constituencySelectId').val(),
				task:"boothsInTehsilOrMunicipality"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjax(jsObj,url);

}

function buildBooths(results){

	$('#boothSelectId').find('option').remove();
	$('#boothSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#boothSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}
function buildHamletsOrWardsInRegion(results){

	$('#wardSelectId').find('option').remove();
	$('#wardSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#wardSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');


}

function callAjaxToSearchInfluencingPeople(voterId)
{

	$('#searchResultsDiv').show();
	$('#totalCountId').show();
	$('#districtErrMsg ,#constituencyErrMsg , #tehsilOrMuncipalityErrMsg ,#villageOrWardErrMsg , #boothErrMsg , #searchResultsDiv , #nameErrMsg').html('');
	var isValid = true;

	var scopeSelected = $('#scopeId').val();

	if(scopeSelected == "3"){
		if($('#districtSelectId').val() == "0")
		{
			 isValid = false; 
			$('#districtErrMsg').html('District is required');
		}

	}
	else if(scopeSelected == "4"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}

	}
	else if(scopeSelected == "5"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Mandal is required');
			 isValid = false;
		}

	}
	else if(scopeSelected == "6"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			isValid = false;
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Mandal is required');
			isValid = false;
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Village is required');
			isValid = false;
		}

	}
	else if(scopeSelected == "7"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			isValid = false;
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}

	}
	else if(scopeSelected == "8"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;

		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Ward is required');
			 isValid = false; 
		}
	}
	else if(scopeSelected == "9"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Ward is required');
			 isValid = false; 
		}
		if($('#boothSelectId').val() == "0")
		{
			$('#boothErrMsg').html('Booth is required');
			 isValid = false; 
		}
	}

	if($('#nameId').val() == "")
	{
		$('#nameErrMsg').html('Name is required');
		isValid = false; 
	}


	if(isValid == false)	
		isValid = true;
	else	
     callAjaxToSearchInfluencingPeople1(voterId);

}

function callAjaxToSearchInfluencingPeople1(voterId)
{

document.getElementById('ajaxImageDiv1').style.display = 'block';



	var stateVal = 0;
	var districtVal = 0;
	var constituencyVal = 0;
	var mandalVal = 0;
	var muncipalityVal = 0;
	var villageVal = 0;
	var wardVal = 0;
	var boothVal = 0;
	var name="";
	var fatherOrSpouceName = "";

	name=$('#nameId').val();
	fatherOrSpouceName = $('#fatherNameId').val();

	var scopeVal = $('#scopeId').val();

	if(scopeVal == "2")
		stateVal = $('#stateSelectId').val();
	else if (scopeVal == "3")
	{
	     districtVal = $('#districtSelectId').val();
	}
	else if (scopeVal == "4")
	{
	     constituencyVal = $('#constituencySelectId').val();
	}
	else if (scopeVal == "5" || scopeVal == "7")
	{	
		 var mandalTemp = $('#mandalSelectId').val();

		 if(mandalTemp.substring(0,1) == "2")
			mandalVal =  mandalTemp.substring(1);
		 else if(mandalTemp.substring(0,1) == "1")
			muncipalityVal =  mandalTemp.substring(1);
    
	}
	else if (scopeVal == "6" || scopeVal == "8")
	{

		 var villageTemp = $('#wardSelectId').val();

		 if(villageTemp.substring(0,1) == "2")
			villageVal =  villageTemp.substring(1);
		 else if(villageTemp.substring(0,1) == "1")
			wardVal =  villageTemp.substring(1);
	}
	else if (scopeVal == "9")
	{
         boothVal = $('#boothSelectId').val();
	}


	var jsObj=
			{   scope:scopeVal,
				name:name,
				state:stateVal,
 			    district: districtVal,
                constituency:constituencyVal,
                mandal:mandalVal,
				muncipality:muncipalityVal,
				hamlet:villageVal,
				ward:wardVal,
				booth:boothVal,	
				fatherOrSpouceName:fatherOrSpouceName,
				voterId:voterId,
				task:"getInfluencingPeopleBySearch"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getInfluencingPeopleBySearch.action?"+rparam;						
		callAjax(jsObj,url);
		

}

function buildInfluencePeopleSearchResults(results,voterId){
	 
    $("#searchResultsDiv").html("");
	$('#totalCountId').show();
	var totalRecords = results.length;
	$('#totalCountId').html('<span style="float: none;position: relative;top: 5px;"><b>Total Records: </b><b style="color: red;">'+totalRecords+'</b></span>');
	 YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var id= oRecord.getData("influencePersonId");

		str+='<span><a href="javaScript:{mapAsInfluencePeople('+id+','+voterId+');}" class="btn btn-mini" ">Map to voter</a></span>';
	
		elLiner.innerHTML=str;
					
	};

	
     var influencePeopleColumnDefs =
		                [ 
						 {key:"firstName",label:"First Name",sortable:true ,width:150},
						 {key:"lastName",label:"Last Name",sortable:true,width:120},
   		                 {key:"contactNumber",label:"Mobile No",sortable:true,width:120},
		                 {key:"Select",label:"Select", formatter:YAHOO.widget.DataTable.select,width:200}
						]; 

     var myConfigs =
		        { 
			        paginator : new YAHOO.widget.Paginator({ 
		              rowsPerPage    : 10
			         }) 
				};
			    
				
	 var myDataSource = new YAHOO.util.DataSource(results);
		 myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
		 myDataSource.responseschema = {
						 fields : ["firstName","lastName"]
					};

	 var familesDataSource = new YAHOO.widget.DataTable("searchResultsDiv", influencePeopleColumnDefs,myDataSource, myConfigs);
 
}

function getCountOfHamletsAndBoothsInPanchayat()
{
	var jsObj=
			{   
				panchaytId : panchaytId,
				task        : "getHamletAndBoothCount"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getHamletAndBoothCountAction.action?"+rparam;						
		callAjax(jsObj,url);
}

function buildHamletAndBoothCount(result)
{
	 $('#hamletAndBoothCount').html('<span>Total Hamlets : </span><span ><b style="color:navy;">'+result[0]+'</b></span><span id="totalBooths" style=" margin-left: 10px;">Total Booths : </span><span><b style="color:navy;">'+result[1]+'</b></span>'); 
	/* $('#hamletAndBoothCount').html('<table class="table table-bordered table-striped table-hover"><tr><th>Total Hamlets</th><th>Total Booths</th></tr><tr><td>'+result[0]+'</td><td>'+result[1]+'</td></tr></table>'); */
}

function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}

function checkForAttributesToDisplay(){
	confTrue = true;
	reqfields = "";
	reqfieldsArr = new Array();
	$('.attributeTypeClassIni1').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
		    }
          });
	$('.notRequiredAttrClass').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfields = reqfields+","+ids[0];
		    }
          });
		   if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);
		  
		if(maintype=="panchayat")
		    {
			id = panchaytId;
			var jsObj=
			{
				constituencyId : constituencyId,
				id : id,
				publicationId: publicationId,
				reqfields:reqfields,
				startIndex : 0,
				maxIndex : 100,
				buildType : "panchayat",
				sort : "voterId",
				dir : "asc",
				task:"getVoterData"
			}
			//var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			//var url = "getVoterData.action?"+rparam;						
			//callAjax(jsObj,url);
		}
		else if(maintype=="booth")
		    {
			id = boothId;
			var jsObj=
			{
				constituencyId : constituencyId,
				id : id,
				publicationId: publicationId,
				reqfields:reqfields,
				startIndex : 0,
				maxIndex : 100,
				buildType : "booth",
				sort : "voterId",
				dir : "asc",
				task:"getVoterData"
			}
			//var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			//var url = "getVoterData.action?"+rparam;						
			//callAjax(jsObj,url);
		}
		else if(maintype=="hamlet")
		    {
			id = hamletId;
			var jsObj=
			{
				constituencyId : constituencyId,
				id : id,
				publicationId: publicationId,
				reqfields:reqfields,
				startIndex : 0,
				maxIndex : 100,
				buildType : "hamlet",
				sort : "voterId",
				dir : "asc",
				task:"getVoterData"
			}
			//var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			//var url = "getVoterData.action?"+rparam;						
			//callAjax(jsObj,url);
		}
		   buildVotersInFamilyWithRetrievedResults();

}

function buildVotersInFamilyWithRetrievedResults(){
	
	$('#votersByLocationTabContentDiv_body').show();
	var x = 1;
	
	    YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
			{
			var vId = oRecord.getData("voterId");
			var vName = oRecord.getData("name");
			var vBoothId = oRecord.getData("boothId");
			 
			elLiner.innerHTML ='<a href="javascript:{};" style="cursor:pointer;" onclick="openProblemEditForm('+vId+','+vBoothId+');">'+vName+'</a>';
				
			} 
          /* YAHOO.widget.DataTable.voterLink = function(elLiner, oRecord, oColumn, oData) 
			{
			var vBoothId = oRecord.getData("boothId");
			var houseNo = oRecord.getData("houseNo");
			elLiner.innerHTML ='<a href="javascript:{};" style="cursor:pointer;" onclick="getVotersInAFamilySearch('+vBoothId+','+publicationDateId+',\''+houseNo+'\');">'+houseNo+'</a>';
				
			} */
		
		  
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("name");
var influencePerson=oRecord.getData("influencePerson");
str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
		 
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	    {
			var str ='';
			var id=oRecord.getData("voterId");
			var isInfluencePerson=oRecord.getData("isInfluencePerson");
			var isCadere = oRecord.getData("isCadrePerson");
			var isPolitician = oRecord.getData("isPoliticion");
			if(isInfluencePerson == true)
			{
				str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
			}
			if(isCadere == true)
			{
				str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
			}
			if(isPolitician == true)
			{
				str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
			}
			elLiner.innerHTML =str;
	    }
		
	YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{ 
			confTrue = false;
		    var check = false;
			var vId = oRecord.getData("voterId");
			var vBoothId = oRecord.getData("boothId");
            for(var i in selectedVotersArr){
			if(selectedVotersArr[i].boothId == vBoothId && selectedVotersArr[i].voterId == vId ){
				 check = true;
			 }
		    }		
			//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+","+vBoothId+"'/>";
			if(check)
			  elLiner.innerHTML="<input type='checkbox' checked='checked' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";
            else
              elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";			
		  };
	
	for(var i in reqfieldsArr)
	{
		var id3 = reqfieldsArr[i].split(",");
		YAHOO.widget.DataTable[""+id3[0]] = function(elLiner, oRecord, oColumn, oData) 
		{ 			
			var ids={};
			ids[0]=oColumn.field;
			var val = "";
				var categ = oRecord.getData("categoriesList");
				  for(var i in categ){
					if(categ[i].categoryValuesId == ids[0])
					   if(categ[i].name != null)
						val = categ[i].name;
				  }
				
				elLiner.innerHTML=val;			
		  };
		 
		 }


		var votersResultColumnDefs = 
	 [ 		    	             
		{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},  	            
		{key:"name", label: "Name", sortable: true,width:100},
		{key:"houseNo", label: "HNO",sortable:true,width:20},
		{key:"voterId", label: "Voter Card Id",sortable:true,width:100},
		{key:"partNo", label: "Booth No",sortable:true,width:70}
	]; 
	if($("#ageId").is(':checked'))
	{
		obj = {key:"age",label: "Age",sortable: true,width:15};
		votersResultColumnDefs.push(obj);	
	}
	if($("#genderId").is(':checked'))
	{
		obj = {key:"gender",label: "Gender",sortable: true,width:35};
		votersResultColumnDefs.push(obj);
	}
	if($("#casteId").is(':checked'))
	{
		obj = {key:"casteName",label: "Caste",width:50};
		votersResultColumnDefs.push(obj);
	}
	if($("#partyId").is(':checked'))
	{

		obj = {key:"partyName",label: "Party",width:40};
		votersResultColumnDefs.push(obj);
	}
	if($("#typeId").is(':checked'))
	{
		obj = {key:"Type",label: "Type",formatter:YAHOO.widget.DataTable.Type,width:70};
		votersResultColumnDefs.push(obj);
	}
	if($("#actionsId").is(':checked'))
	{
		obj = {key:"Actions",label: "Actions",formatter:YAHOO.widget.DataTable.ActionLink};
		votersResultColumnDefs.push(obj);
	}

		 for(var i in reqfieldsArr){
		    var ids1 = reqfieldsArr[i].split(",");
		     
		   obj = {
				key:""+ids1[0], label: ""+ids1[1],formatter:YAHOO.widget.DataTable[""+ids1[0]]
					};
					votersResultColumnDefs.push(obj);
					
		 }
		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterData.action?constituencyId="+constituencyId+"&reqfields="+reqfields+"&buildType="+maintype+"&publicationId="+publicationId+"&id="+id+"&");
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "votersList",
		fields : ["voterId","name","houseNo","age","gender","casteName","partyName","isCadrePerson","isInfluencePerson","isPoliticion","categoriesList","partNo","serialNo","voterIds"],
		metaFields: {
		totalRecords: "totalVoters" // Access to value in the server response
		}
		};

		
//end
		var myConfigs = {
		initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
		   paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit 
						})  // Enables pagination
		};
		if(confTrue){
		 var stindx = 0;
		 try{
		  stindx =  (parseInt($.trim($('.yui-pg-current-page').html()))-1)*100;
		  myConfigs["paginator"] = new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit ,
						initialPage:$('.yui-pg-current-page').html(),
						totalRecords:totalReq
						}) 
		  }catch(e){}
		 myConfigs["initialRequest"] ="sort=name&dir=asc&startIndex="+stindx+"&results="+limit+"&initialPage ="+$('.yui-pg-current-page').html();
		}
		var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
		votersResultColumnDefs, votersByLocBoothDataSource, myConfigs);
        // $("#votersBySearchTabContentDiv_body").show();
		votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		totalReq = oResponse.meta.totalRecords;
		return oPayload;
		}


		return {
		oDS: votersByLocBoothDataSource,
		oDT: votersByLocBoothDataTable
		};
    }

getImpFamilyDetails();
getUserCategories();
getCountOfHamletsAndBoothsInPanchayat();
 if(maintype=="customWard")
	{
		
		getVoterDetails();
	}  
   
</script>
</body>
