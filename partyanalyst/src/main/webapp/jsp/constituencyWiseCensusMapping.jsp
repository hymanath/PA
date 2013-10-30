<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst - Constituency Wise Census Details Mapping </title>
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/skin.css"> 
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 
<style type="text/css">

#censusMapping_main
{
	padding:20px;
}

#censusReportHeading
{
	color:#6C3C05;
	font-size:20px;
	font-weight:normal;
	padding:20px;
	text-decoration:underline;
}
#censusMapping_body
{
	text-align:left;
}

#censusReport_body_input_table th
{
	padding:10px;
	width:80px;
}

#censusReport_body_input_table td
{
	padding:10px;
}
.button 
{
	background-attachment:scroll;
	background-color:#335291;
	background-image:none;
	background-position:0 0;
	background-repeat:repeat;
	font-weight:bold;
	color:#FFFFFF;
	width:120px;
	height:35px;
	align:center;
}

</style>

<script type="text/javascript">

function doCensusMapping()
{
	var stateElmt = document.getElementById("stateList");
	var districtElmt = document.getElementById("districtList");
	var yearElmt = document.getElementById("yearSelect");
	var stRadioEle = document.getElementById("stRadioId");
	var diRadioEle = document.getElementById("diRadioId");
	var modifyCheckEle = document.getElementById("modifyCheckboxId");

	var reportLevel = '';
	var modifycheck = '';

	var yearValue = yearElmt.options[yearElmt.selectedIndex].text;
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
	var districtId = districtElmt.options[districtElmt.selectedIndex].value;

	if(stRadioEle.checked == true)
		reportLevel = stRadioEle.value;
	else if(diRadioEle.checked == true)
		reportLevel = diRadioEle.value;
	
	if(reportLevel == 'state' && stateId == 0)
		return;

	else if(reportLevel == 'district' && (stateId == 0 || districtId == 0))
		return;

	if(modifyCheckEle.checked == true)
		modifycheck = 'true';
	else
		modifycheck = 'false';

	ShowAjaxImage();
	
	var jsObj=
	{
			stateId		: stateId,
			districtId	: districtId,
			yearValue	: yearValue,
			reportLevel : reportLevel,
			modify		: modifycheck,
			task		: "doConstituencyWiseCensusMapping"					
	}; 

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "doConstituencyWiseCensusMappingAction.action?"+rparam;						
	censusAjaxCall(jsObj,url);
}

function censusAjaxCall(jsObj,url){
	
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "doConstituencyWiseCensusMapping")
								{	
									hideAjaxImage();
									buildResultTable(myResults);
								} 
																
						}
						catch (e)
							{   
							  // 	alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
function hideDistrictSelect()
{
	document.getElementById("districtList").disabled= true; 
}

function showDistrictSelect()
{
	document.getElementById("districtList").disabled= false; 
}

function ShowAjaxImage()
{
	var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	ajaxImgElmt.style.display = "block";
	return;
}
function hideAjaxImage()
{
	var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	ajaxImgElmt.style.display = "none";
	return;
}

function buildResultTable(myResults)
{
	if(myResults == null)
	{
		return;
	}
	else
	{
		var resultDivElmt = document.getElementById("mappingResultDiv");

		var resultVar = '';

		resultVar += '<h2 style="color:blue;"><span><b>-----------Mapping Results----------</b> </span></h2><h3><BR><BR>';

		if(myResults.mapeedConstituencies != null && myResults.mapeedConstituencies.length > 0)
		{
			resultVar += '<span><b style="color:green;">----------Mapped Constituencies---------</b> </span><BR><BR>';

			resultVar += '<table border="1" align="center" width="50%">';
			resultVar += '<tr>';
			resultVar += '	<th>SNO</th>';
			resultVar += '	<th>Constituency Id</th>';
			resultVar += '	<th>Constituency Name</th>';
			resultVar += '</tr>';

			var sn = 0;
			for(var i in myResults.mapeedConstituencies)
			{
				resultVar += '<tr>';
				resultVar += '<td>'+ ++sn +'</td>';
				resultVar += '<td>'+myResults.mapeedConstituencies[i].id+'</td>';
				resultVar += '<td>'+myResults.mapeedConstituencies[i].name+'</td>';
				resultVar += '</tr>';
			}
			resultVar += '</table><BR><BR>';
		}

		if(myResults.unMapeedConstituencies != null && myResults.unMapeedConstituencies.length > 0)
		{
			resultVar += '<span><b style="color:red;">----------Un Mapped Constituencies---------</b> </span><BR><BR>';

			resultVar += '<table border="1" align="center" width="50%">';
			resultVar += '<tr>';
			resultVar += '	<th>SNO</th>';
			resultVar += '	<th>Constituency Id</th>';
			resultVar += '	<th>Constituency Name</th>';
			resultVar += '</tr>';
			
			var sn = 0;
			for(var j in myResults.unMapeedConstituencies)
			{
				resultVar += '<tr>';
				resultVar += '<td>'+ ++sn +'</td>';
				resultVar += '<td>'+myResults.unMapeedConstituencies[j].id+'</td>';
				resultVar += '<td>'+myResults.unMapeedConstituencies[j].name+'</td>';
				resultVar += '</tr>';
			}
			resultVar += '</table><BR><BR>';
		}

		if(myResults.existedConstituencies != null && myResults.existedConstituencies.length > 0)
		{
			resultVar += '<span><b style="color:blue;">----------Already Existed Constituencies---------</b> </span><BR><BR>';

			resultVar += '<table border="2" align="center" width="50%">';
			resultVar += '<tr>';
			resultVar += '	<th>SNO</th>';
			resultVar += '	<th>Constituency Id</th>';
			resultVar += '	<th>Constituency Name</th>';
			resultVar += '</tr>';

			var sn = 0;
			for(var k in myResults.existedConstituencies)
			{
				resultVar += '<tr>';
				resultVar += '<td>'+ ++sn +'</td>';
				resultVar += '<td>'+myResults.existedConstituencies[k].id+'</td>';
				resultVar += '<td>'+myResults.existedConstituencies[k].name+'</td>';
				resultVar += '</tr>';
			}
			resultVar += '</table><BR><BR>';
		}

		resultDivElmt.innerHTML = resultVar;

		}
}
	
</script>
</head>

<body>

<div id="censusMapping_main">
<div id="censusReportHeading"> Constituency Wise Census Mapping </div>
<div id="censusMapping_body">		
	<div style="margin:15px;">
	<table>	
	<tr>
		<th>Please Select Level To Map :&nbsp;&nbsp;</th>
		<td><input  id="stRadioId" type="radio" name="location" value="state" onclick="hideDistrictSelect()" checked="checked"></input></td>
		<th>State Wise Report</th>
		<td><input  id="diRadioId" type="radio" name="location" value="district" onclick="showDistrictSelect()"></input></td>
		<th>District Wise Report</th>
	</tr>
	</table>

	<table id="censusReport_body_input_table">
			
		<tr>
			<th>State</th>
			<td>
				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList" list="statesListForCensus" listKey="id" listValue="name" headerKey="0" headerValue="Select State"  onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtList','currentAdd');"/>	
			</td>

			<th>District</th>
			<td>
				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district_s" id="districtList" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select District" />	
			</td>
		
			<th>Year</th>
			<td>
				<select id="yearSelect">
					<option>2001</option>
					<option>2011</option>
				</select>
			</td>
		</tr>
	</table>
	<table>
	<tr style="margin-left:15px;">
		<td><input type="checkbox" id="modifyCheckboxId"/></td>
		<th>Modify Existing Data</th>
		</tr>
	</table>

	<table style="margin-top:15px;" align="center">
		<th></th>
		<td><div id="saveDiv" align="center">
			<input type="button" Class="button" value="Start Mapping" name="post" onclick="doCensusMapping()"></input>
			</div>
		</td>
	</table>
</div>
</div>

<div id="ajaxLoadDiv" style="display:none;padding-top:20px;">
	<span><b>Your Request is Processing, Please Wait....</b> </span>
	<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>
</div>
<div id="mappingResultDiv" style="padding-top:20px;"></div>
</div>
<script>
	hideDistrictSelect();
</script>
</body>
</html>