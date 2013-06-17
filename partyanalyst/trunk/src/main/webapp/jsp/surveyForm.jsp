<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Form Details</title>
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
  <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
  <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
 <script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<style type="text/css">

table {
    background-color: transparent;
    border-spacing: 14px;
     border-collapse: separate;
    max-width: 564%;
}


textarea {
    background-color: #FFFFFF;
    color: #000000;
    font: 12px/17px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 39px;
    margin-left: 7px;
    padding: 5px 0 5px 10px;
    width: 760px;
}
</style>

<script type="text/javascript">
var constituencyId   = "";
var mandalId         = "";
var reportLevel      = "";
var checkedele       = "";
var selectedEle      = "";
var levelId          = "";
var locationId       = "";
var panchayatId      = "";
var constituencyType = "";
var surveyId         = '${surveyId}';
function getreportLevel()
{
	var value = "";
	if(constituencyType == "URBAN")
	{
		value = $("#urbanLocation option:selected").val();
	}
	else
	{
		value = $("#ruralLocation option:selected").val();
	}
	/* if(value == 1)
	{
		$('#secondRow').show();
		$('#ConstituencyLable').show();
		$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
	} */
	if(value == 1)
	{
		$('#secondRow').show();
		//$('#ConstituencyLable').show();
		//$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
	}
	else if(value == 2)
	{
		$('#secondRow').show();
		//$('#ConstituencyLable').show();
		//$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').hide();
		$('#hamletcolumn').hide();
		$('#boothLable').hide();
		$('#boothColumn').hide();
	}
	else if(value == 3)
	{
		$('#secondRow').show();
		//$('#ConstituencyLable').show();
		//$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').hide();
		$('#hamletcolumn').hide();
		$('#boothLable').hide();
		$('#boothColumn').hide();
	}
	else if(value == 4)
	{
		$('#secondRow').show();
		//$('#ConstituencyLable').show();
		//$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#thirdRow').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
	}
	else if(value == 5)
	{
		$('#secondRow').show();
		//$('#ConstituencyLable').show();
		//$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#thirdRow').show();
		$('#boothLable').show();
		$('#boothColumn').show();
		$('#hamletLable').hide();
		$('#hamletcolumn').hide();
		$('#panchayatLable').hide();
		$('#panchayatColumn').hide();
	}
	
}
function getMandals(){
	constituencyId = $("#constituencyList option:selected").val();
	reportLevel = "Constituency";
	  var jsObj=
			{
					
					selected:constituencyId,
					selectElmt:"mandalField",
					str   : "all",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
function buildMandals(myResults)
{
	var str = "";
	$("#mandalId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#mandalId').html(str);
	
}

function getPanchayatList()
{
	mandalId = $("#mandalId option:selected").val();
	var value = mandalId.substring(1);
	if(mandalId.charAt(0) =="1")
	{
		reportLevel = "muncipality";
		var timeST = new Date().getTime();
		var jsObj =
		{ 
			time : timeST,
			id:mandalId,
			task:"hamletsOrWardsInRegion",
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:"",
			constId:""
			
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;
	callAjax(jsObj,url);
	}
	else
	{
		reportLevel = "mandal";
		checkedele  = "panchayat";
		selectedEle = "panchayatField";
	
		var jsObj=
		{	
			selected:value,
			checkedele:checkedele,
			selectedEle:selectedEle,
			constituencyId:constituencyId,
			flag:-1,
			type:reportLevel,
			publicationValue : 8,
			task:"getPanchayat"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPanchayatByConstituencyAction.action?"+rparam;
	callAjax(jsObj,url);
	}	
							
	
} 

function buildPanchayats(myResults)
{
	var str = "";
	$("#PanchayatId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#PanchayatId').html(str);
	getBooths();
}

function getHamlets()
{
	levelId = $("#levelId option:selected").val();
	panchayatId = $("#PanchayatId option:selected").val();
	
		reportLevel = "hamlet";
		var jsObj=
		{
			selected:panchayatId,
			selectedEle:"hamletField",
			publicationValue : 8,
			task:"getHamletsList"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
	
	/* else if(levelId == 2)
	{
		reportLevel = "booth";
		checkedele  = "pollingstationByPublication";
		selectedEle = "pollingStationField";
		var jsObj=
		{	
			selected:mandalId.substring(1),
			checkedele:checkedele,
			selectedEle:selectedEle,
			constituencyId:constituencyId,
			flag:-1,
			type:"mandal",
			publicationValue : 8,
			task:"getPanchayat"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPanchayatByConstituencyAction.action?"+rparam;
	callAjax(jsObj,url); 
	}*/
}

function getBooths()
{
	var type = "mandal";
	if(mandalId.charAt(0) =="1")
	{
		type = "muncipality";
	}
	reportLevel = "booth";
	checkedele  = "pollingstationByPublication";
	selectedEle = "pollingStationField";
	var jsObj=
	{	
		selected:mandalId.substring(1),
		checkedele:checkedele,
		selectedEle:selectedEle,
		constituencyId:constituencyId,
		flag:-1,
		type:type,
		publicationValue : 8,
		task:"getPanchayat"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPanchayatByConstituencyAction.action?"+rparam;
	callAjax(jsObj,url); 
}

function buildHamlets(myResults)
{
	var str = "";
	$("#hamletId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#hamletId').html(str);
}
function buildBooths(myResults)
{
	var str = "";
	$("#boothId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#boothId').html(str);
}

function buildMuncipalityBooths(myResults)
{
	var str = "";
	$("#boothId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>Booth NO -'+myResults[i].name+'</option>';
	}
	$('#boothId').html(str);
}

function getConstituencyType()
{
		/* var constituencyId = $("#constituencyList option:selected").val();
		publicationId = $('#publicationId').val(); */
		var jsObj=
			{
				constituencyId : constituencyId,
				publicationId  : 8,
				task           : "getConstituencyType"
			}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getReportLevelDetails.action?"+rparam;	

		callAjax(jsObj,url);
}

function buildConstituencyType(myResults)
{
	if(myResults[0].name == "URBAN")
	{
		constituencyType = "URBAN";
		$('#urbanLocation').show();
		$('#ruralLocation').hide();
	}
	else
	{
		constituencyType = "RURAL";
		$('#urbanLocation').hide();
		$('#ruralLocation').show();
	}
}
function callAjax(jsObj,url)
{
			 var myResults;
			 var callback = {			
 		               success : function( o ) {
							try {												
								    myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
									if(jsObj.task == "getMandalList")
									{
										buildMandals(myResults);
									}
									
									else if(jsObj.task == "getPanchayat" && jsObj.checkedele == "panchayat" || jsObj.task == "hamletsOrWardsInRegion")
									{
										buildPanchayats(myResults);
									}
									
									else if(jsObj.task == "getHamletsList")
									{
										buildHamlets(myResults);
									}
									
									else if(jsObj.task == "getPanchayat" && jsObj.checkedele == "pollingstationByPublication")
									{
										if(jsObj.type == "mandal")
										{
											buildBooths(myResults);
										}
										else
										{
											buildMuncipalityBooths(myResults);
										}
										
									}
									
									else if(jsObj.task == "getConstituencyType")
									{
										buildConstituencyType(myResults);
									}
									else if(jsObj.task == "getSurveyDetails")
									{
										getLevlValuesForSurvey(myResults);
									}
									else if(jsObj.task == "getLocationDetails")
									{
										buildLevlValuesForSurvey(myResults,jsObj);
									}
									else if(jsObj.task == "getVoterDetails")
									{
										buildVoterDetails(myResults);
									}
								}catch (e) {
								
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function openSurveyForm()
{
	var surveyId = 1;
	var urlString = "surveyFormAction.action?surveyId="+surveyId+"";
	window.location.href = urlString;	
}

if(surveyId > 0)
{
	var jsObj=
	{	
		surveyId : surveyId,
		task     : "getSurveyDetails" 
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
	
}

function getLevlValuesForSurvey(myResults)
{
	for(var i in myResults)
	{
		var locationId     = myResults[0].locationScopeId;
		var locationValue  = myResults[0].locationValue;
	}
	var jsObj=
	{	
		locationId     : locationId,
		locationValue  : locationValue,
		task           : "getLocationDetails" 
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
}

function buildLevlValuesForSurvey(myResults,jsObj)
{
	if(jsObj.locationId == 1)
	{
		$('#mainRow').show();
		$('#firstRow').show();
		$('#secondRow').hide();
		$('#countryLable').show();
		$('#countryColumn').show();
		$('#stateLable').show();
		$('#stateColumn').show();
		$('#districtLable').show();
		$('#districtColumn').show();
		$('#ConstituencyLable').show();
		$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
		$("#countryId option ").remove();
		var str = "";
		str += '<option value='+jsObj.locationValue+'>'+myResults+'</option>';
		$('#countryId').html(str);
		var value = $('#countryId option : selected').val();
		if(value > 0)
		{
			getStates();
		}
	}
	else if(jsObj.locationId == 2)
	{
		$('#mainRow').show();
		$('#firstRow').show();
		$('#secondRow').hide();
		$('#countryLable').hide();
		$('#countryColumn').hide();
		$('#stateLable').show();
		$('#stateColumn').show();
		$('#districtLable').show();
		$('#districtColumn').show();
		$('#ConstituencyLable').show();
		$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
		$("#stateId option ").remove();
		var str = "";
		str += '<option value='+jsObj.locationValue+'>'+myResults+'</option>';
		$('#stateId').html(str);
		
		var value =  $("#stateId option:selected").val();
		if(value > 0)
		{
			getDistricts();
		}
		
	}
	else if(jsObj.locationId == 3)
	{
		$('#mainRow').show();
		$('#firstRow').show();
		$('#secondRow').hide();
		$('#countryLable').hide();
		$('#countryColumn').hide();
		$('#stateLable').hide();
		$('#stateColumn').hide();
		$('#districtLable').show();
		$('#districtColumn').show();
		$('#ConstituencyLable').show();
		$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
		$("#districtId option ").remove();
		var str = "";
		str += '<option value='+jsObj.locationValue+'>'+myResults+'</option>';
		$('#districtId').html(str);
		var value =  $("#districtId option:selected").val();
		alert(value);
		if(value > 0)
		{
			getConstituencys();
		}
		
	}
	else if(jsObj.locationId == 4)
	{
		//alert("constituency");
		$('#mainRow').hide();
		$('#firstRow').show();
		$('#secondRow').hide();
		$('#countryLable').hide();
		$('#countryColumn').hide();
		$('#stateLable').hide();
		$('#stateColumn').hide();
		$('#districtLable').hide();
		$('#districtColumn').hide();
		$('#ConstituencyLable').show();
		$('#ConstituencyColumn').show();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').show();
		$('#panchayatColumn').show();
		$('#hamletLable').show();
		$('#hamletcolumn').show();
		$('#boothLable').hide();
		$('#boothColumn').hide();
		$("#constituencyList option ").remove();
		var str = "";
		str += '<option value='+jsObj.locationValue+'>'+myResults+'</option>';
		$('#constituencyList').html(str);
		var value =  $("#constituencyList option:selected").val();
		if(value > 0)
		{
			getMandals();
			getConstituencyType();
		}
	}
	else if(jsObj.locationId == 5)
	{
		//alert("mandal");
		$('#mainRow').hide();
		$('#firstRow').show();
		$('#secondRow').show();
		$('#countryLable').hide();
		$('#countryColumn').hide();
		$('#stateLable').hide();
		$('#stateColumn').hide();
		$('#districtLable').hide();
		$('#districtColumn').hide();
		$('#ConstituencyLable').hide();
		$('#ConstituencyColumn').hide();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').hide();
		$('#panchayatColumn').hide();
		$('#hamletLable').hide();
		$('#hamletcolumn').hide();
		$('#boothLable').hide();
		$('#boothColumn').hide();
		$("#mandalId option ").remove();
		var str = "";
		str += '<option value='+jsObj.locationValue+'>'+myResults+'</option>';
		$('#mandalId').html(str);
		var str = "";
		$("#ruralLocationId option").remove();
		str += '<option value="0">select location</option>';
		str += '<option value="3">panchayat</option>';
		str += '<option value="4">hamlets</option>';
		str += '<option value="5">pollingstation</option>';
		$('#ruralLocationId').html(str);
		
		var value =  $("#mandalId option:selected").val();
		if(value > 0)
		{
			getPanchayatList();
		}
		
	}
	else if(jsObj.locationId == 6)
	{
		//alert("muncipality");
		$('#mainRow').hide();
		$('#firstRow').show();
		$('#secondRow').show();
		$('#countryLable').hide();
		$('#countryColumn').hide();
		$('#stateLable').hide();
		$('#stateColumn').hide();
		$('#districtLable').hide();
		$('#districtColumn').hide();
		$('#ConstituencyLable').hide();
		$('#ConstituencyColumn').hide();
		$('#mandalLable').show();
		$('#mandalColumn').show();
		$('#panchayatLable').hide();
		$('#panchayatColumn').hide();
		$('#hamletLable').hide();
		$('#hamletcolumn').hide();
		$('#boothLable').hide();
		$('#boothColumn').hide();
		$("#mandalId option ").remove();
		var str = "";
		str += '<option value='+jsObj.locationValue+'>'+myResults+'</option>';
		$('#mandalId').html(str);
		var str = "";
		$("#ruralLocationId option").remove();
		str += '<option value="0">Select Location</option>';
		str += '<option value="2">ward</option>';
		str += '<option value="5">PollingStation</option>';
		$('#ruralLocationId').html(str);
		var value =  $("#mandalId option:selected").val();
		if(value > 0)
		{
			getPanchayatList();
		}		
	}
}

function getVoterDetails()
{
	var voterId = $('#voterId').val();
	var jsObj=
		{
			voterId : voterId,
			task    : "getVoterDetails"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;	

	callAjax(jsObj,url);
}
function buildVoterDetails(myresults)
{
	if(myresults != null)
	{
		$('#nameId').val(myresults[0].firstName);
		$('#mobileNoId').val(myresults[0].mobileNo);
		$('#ageId').val(myresults[0].age);
		var agestr = '<option>'+myresults[0].gender+'</option>';
		$('#genderId').html(agestr);
		var castestr = '<option value='+myresults[0].categoryValuesId+'>'+myresults[0].cast+'</option>';
		$('#casteList').html(castestr);
		$('#educationList').html(educationstr);
		$('#nameId').val(myresults[0].firstName);
	}
}
</script>
</head>
<body>
<div><input type="button" class="btn-info" value="Create Survey" style="float:right;margin-right:15px;" onClick="openSurveyForm();"></input></div>
<s:form action="surveyFormSaveAction" method="GET" theme="simple" name="form">
<s:token/>
<div>
<div id="selectionDiv" class="widget green whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 942px;padding-bottom: 12px;">
	<h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">Select Your Location Details</h4>
	<div id="selectiontableDiv"  style="margin-left: 15px;">
	<table>
	<tr id="mainRow" style="display:none;">
	<!--<td><span>Level : </span></td><td><select id="levelId"><option value="0" >Select Level</option></select></td>-->
	<td id="countryLable" style="display:none;"><span>Country : </span></td><td id="countryColumn" style="display:none;"><select id="countryId" onChange="getStates();"><option value="0" name="countryId">Select Country</option></select></td>
	<td id="stateLable" style="display:none;"><span>State : </span></td><td id="stateColumn" style="display:none;"><s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="stateId" id="stateId" list="stateList" listKey="id" listValue="name"  class="selectWidth" onChange="getDistricts();"/></td>
	<td id="districtLable" style="display:none;"><span>District : </span></td><td id="districtColumn" style="display:none;"><s:select theme="simple" cssClass="selectWidth" label="Select Your District" name="districtId" id="districtId" list="districtList" listKey="id" listValue="name"  class="selectWidth" onChange="getConstituencys();"/></td>
	</tr>
	<tr id="firstRow" style="display:none;">
	<td id="ConstituencyLable" ><span>Constituency : </span></td><td id="ConstituencyColumn" ><s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyId" id="constituencyList" list="constituencyList" listKey="id" listValue="name"  class="selectWidth" onChange="getMandals();getConstituencyType();"/></td>
	<td><span>Location : </span></td>
	<td id="ruralLocation" ><select id="ruralLocationId" onChange="getreportLevel()">
		<option value=0>Select Location</option>
		<option value=1>Mandal</option>
		<option value=3>Panchayat</option>
	    <option value=4>Hamlets</option>
        <option value=5>PollingStation</option>
		</select>
      </td>
	 <td id="urbanLocation" style="display:none;"><select id="urbanLocationId" onChange="getreportLevel()">
		<option value=0>Select Location</option>
		<!--<option value=1>Constituency</option>-->
		<option value=2>Ward</option>
        <option value=5>PollingStation</option>
		</select>
      </td>
	</tr>
	<tr id="secondRow" style="display:none;">
	
	<td id="mandalLable" style="display:none;"><span>Mandal/Muncipality : </span></td><td id="mandalColumn" style="display:none;"><select id="mandalId" onChange="getPanchayatList()"><option id="0" name="mandalId">Select Mandal</option></select></td>
	<!--<td id="locationLable" style="diaplay:none;"><span>Location : </span></td>
	<td id="ruralLocation" style="diaplay:none;"><select id="ruralLocationId" onChange="getreportLevel()"></select>
      </td>
	 <td id="urbanLocation" style="display:none;"><select id="urbanLocationId" onChange="getreportLevel()">
		<option value=0>Select Location</option></select>
      </td>-->
	<td id="panchayatLable" style="display:none;"><span>Panchayat/Ward : </span></td><td id="panchayatColumn" style="display:none;"><select id="PanchayatId" onChange="getHamlets();" name="panchayatId"><option id="0" >Select Panchayat</option></select></td>
	<td id="hamletLable" style="display:none;"><span>Hamlet : </span></td><td id="hamletcolumn" style="display:none;"><select id="hamletId" name="hamletId"><option value="0">Select Hamlet</option></select></td>
	<td id="boothLable" style="display:none;"><span>Booth : </span></td><td id="boothColumn" style="display:none;"><select id="boothId" name="boothId"><option value="0">Select Booth</option></select></td>
	</tr>
	
	<!--<tr id="thirdRow" style="display:none;">
	<td><span>Level : </span></td><td><select id="levelId" onChange="getHamletsOrBooths();">
	<option value="0">Select Level</option>
	<option value="1">Hamlet </option>
	<option value="2">Booth</option>
	</select></td>
	
	</tr>-->
	</table>
	</div>
</div>
</div>
	
<div>
<div id="personalDetailsDiv" class="widget blue whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 942px;padding-bottom: 12px;">
	<h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="personalDetailsHeading">Personal Details</h4>
	<div id="personaltableDiv"  style="margin-left: 15px;">
	<table>
	<tr>
	<td><span>Voter ID : </span></td><td><input type="text" id="voterId" style="width: 134px;" onChange="getVoterDetails();" name="voterId"></input></td>
	<td><span>Name : </span></td><td><input type="text" id="nameId" style="width: 134px;" name="name"></input></td>
	<td><span>Mobile NO : </span></td><td><input type="text" id="mobileNoId" style="width: 134px;" name="mobileNo"></input></td>
	<td><span>Age : </span></td><td><input type="text" id="ageId" style="width: 20px;" name="age"></input></td>
	
	</tr>
	<tr>
	<td><span>Gender : </span></td><td><select id="genderId" name="gender">
	<option id="0">Select Gender</option>
	<option id="1">Male </option>
	<option id="2">Female</option>
	</select></td>
	<td><span>Caste : </span></td><td><s:select theme="simple" cssClass="selectWidth" label="Select Caste" name="caste" id="caste" list="casteList" listKey="id" listValue="name"  class="selectWidth"/></td>
	<!--<td><span>Caste Categoery : </span></td><td><select id="casteCategoeryId" >
	<option id="0">Select Categoery</option>
	</select></td>-->
	<td><span>Education : </span></td><td><s:select theme="simple" cssClass="selectWidth" label="Select Education" name="education" id="educationList" list="eduStatus" listKey="id" listValue="name"  class="selectWidth"/></td>
	</tr>
	<tr>
	
	<td><span>Occupation : </span></td><td><s:select theme="simple" cssClass="selectWidth" label="Select Occupation" name="occupation" id="occupationList" list="occupationsList" listKey="id" listValue="name"  class="selectWidth"/></td>
	
	</tr>
	</table>
	<div style="margin-left:10px;"><span>Land Marks : </span><textarea id="descTextArea" onkeyup="limitText('descTextArea','maxcount',200)" rows="" cols="40" name="landmark"></textarea></div>
	</div>
</div>
</div>
<s:submit cssClass="button" value="Save" name="Save" ></s:submit>
</s:form>
</body>
</html>