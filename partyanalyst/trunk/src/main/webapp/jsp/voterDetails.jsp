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
<body>
<div id="imgDescriptionDiv" style="margin:10px;text-align:center;">
<b style="margin-left: 5px">Influencing People</b>:<img title="Politician" alt="Politicion" src="./images/icons/influencing.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Cadre</b>:<img title="Cadre" alt="Politicion" src="./images/icons/cadre.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Politician</b>:<img title="Politician" alt="Politicion" src="./images/icons/politican.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<div id="errorMessageDiv"></div>
</div>
 <div id="votersInnerDiv1" style="height:auto">
	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
 </div>


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


if(maintype=="panchayat"){
	buildVotersByLocPanchayatDataTable1();
}
if(maintype=="booth"){
	buildVotersByLocBoothDataTable1();
}
if(maintype=="hamlet"){
	buildVotersByLocHamletTestDataTable1();
}

function buildVotersByLocPanchayatDataTable1()
{
//publicationId = mainpublicationId;
//panchaytId =  mainreqid;

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
{key:"voterId", label: "SNo",width:15,sortable: true},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "voter ID",width:110,sortable: true},
{key:"gender", label: "Gender", width:45, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "House No",width:30, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&panchaytId="+panchaytId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
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
{key:"voterId", label: "SNo",width:15,sortable: true},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "voter ID",width:110,sortable: true},
{key:"gender", label: "Gender", width:45, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "House No",width:30, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
{key:"serialNo", label:"Serial No"},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true},
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
{key:"voterId", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","serialNo"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
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
var str ='';
var id=oRecord.getData("voterIds");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" >Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" >Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};">Add To Politician</a>';
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
{key:"voterId", label: "SNo",width:20,sortable: true},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "voter ID",sortable: true},
{key:"gender", label: "Gender", width:50, sortable: true},
{key:"age", label: "Age",  width:30,sortable:true},
{key:"houseNo", label: "House No",width:50, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&hamletId="+hamletId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
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
</script>
</body>
