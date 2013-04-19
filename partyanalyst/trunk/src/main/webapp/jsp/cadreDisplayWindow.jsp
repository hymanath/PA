<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script>  
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css -->  
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
<style type="text/css">
#InfluencingPeopleDetailsDiv table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#InfluencingPeopleDetailsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#InfluencingPeopleDetailsDiv table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	text-decoration:none;
	}
#InfluencingPeopleDetailsDiv table a{
color:#333333;text-decoration:none;
}
#InfluencingPeopleDetailsDiv
{
	font-family : arial;
	font-size: 13px;
    margin-top: 20px;
	padding: 10px 10px 10px 15px;
}
#InfluencingPeopleDetailsDiv table th {padding:5px !important;}
#InfluencingPeopleDetailsDiv{padding-left: 2px !important; }
#InfluencingPeopleDetailsDiv table td{padding-right:3px !important;}
#impFamilesnfoForHamletByBooth  table tr:nth-child(even){background:#EdF5FF;}
</style>
<title>cadre</title>
<script type="text/javascript">
var  locationValue    = '${locationValue}';
var  typeValue    = '${typeValue}';
var  publicationDateId    = '${publicationDateId}';
var  btnName    = '${btnName}';
var  mainreqid    = '${mainreqid}';
var  maintype    = '${maintype}';
function getInfluencingPeopleVotersDetails()
{
	$("#titleDiv").append('<h3 style="background: none repeat scroll 0% 0% #4285F4; color: rgb(255, 255, 255); padding: 5px; border-radius: 5px 5px 5px 5px; text-align: center; margin: 10px; border-top-width: 40px;">Voters Details</h3>');
	if(btnName == "Politician")
	{
YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var id=oRecord.getData("candidateId");
		var name = oRecord.getData("firstName");
		elLiner.innerHTML ='<a target="_blank" id="candidateId" href="candidateElectionResultsAction.action?candidateId='+id+' ">'+name+'</a>';
	}	
	var votersByLocBoothColumnDefs = [
	{key:"voterId", label: "SNo",width:20,sortable: true},
	{key:"firstName", label: "Name",width:80, sortable: true},
	{key:"voterIDCardNo", label: "voter ID",sortable: true},
	{key:"gender", label: "Gender", width:50, sortable: true},
	{key:"age", label: "Age",  width:30,sortable:true},
	{key:"houseNo", label: "House No",width:60, sortable:true},
	{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
	//{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
	//{key:"relationshipType", label: "Relationship", sortable:true},
	{key:"mobileNo",label:"MobileNo",sortable:true},
	{key:"localArea", label: "Location", sortable: true},
	//{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
	//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}
];
var votersByLocBoothDataSource = new YAHOO.util.DataSource("getInfluencingPeopleVotersDetailsAction.action?locationValue="+mainreqid+"&type="+maintype+"&publicationDateId="+publicationDateId+"&buttonName="+btnName+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","localArea","candidateId"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};
	}
	if(btnName != "Politician")
	{
var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo",width:20,sortable: true},
	{key:"firstName", label: "Name",width:80, sortable: true},
	{key:"voterIDCardNo", label: "voter ID",sortable: true},
	{key:"gender", label: "Gender", width:50, sortable: true},
	{key:"age", label: "Age",  width:30,sortable:true},
	{key:"houseNo", label: "House No",width:60, sortable:true},
	{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
	//{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
	//{key:"relationshipType", label: "Relationship", sortable:true},
	{key:"mobileNo",label:"MobileNo",sortable:true},
	{key:"localArea", label: "Location", sortable: true},
	//{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
	//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];
var votersByLocBoothDataSource = new YAHOO.util.DataSource("getInfluencingPeopleVotersDetailsAction.action?locationValue="+mainreqid+"&type="+maintype+"&publicationDateId="+publicationDateId+"&buttonName="+btnName+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","localArea"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};
	}


var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=3", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 3 
			    })  // Enables pagination
};


var votersByLocBoothDataTable = new YAHOO.widget.DataTable("InfluencingPeopleDetailsDiv",
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

</script>
</head>
<body  style="background: none repeat scroll 0% 0% rgb(238, 238, 238);">

<div id="titleDiv"></div>
<div id="influencyPopupDiv">
	<div id="InfluencingPeopleDetailsDiv" class="yui-skin-sam yui-dt-sortable"></div>
	 </div>
<script type="text/javascript">
getInfluencingPeopleVotersDetails();
</script>
</body>
</html>
