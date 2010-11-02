<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Villages To Booth</title>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css">
 
<!-- Dependencies -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script>
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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script> 
<!-- OPTIONAL: Animation (only required if using ContainerEffect) -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/animation/animation-min.js"></script>
 
<!-- OPTIONAL: Drag & Drop (only required if enabling Drag & Drop) -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script>
 
<!-- Source file -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/container/container-min.js"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<script type="text/javascript">
var boothIds = new Array();
var stateValue;
var districtValue;
var selectedYear;
function getBoothOfAssemblyByYear(id)
{
	var yearEl = document.getElementById
	var jsObj=
		{
				id: id,
				task: 'boothsOfAssembly'
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/municipalWardsAssemblyBoothsMapperAjaxAction.action?"+rparam;		
	callAjax(jsObj, url);
}


function displayDistrictLocalBodies(results)
{
	var elmt = document.getElementById("localBodyElectionData_main_right");
	var buttonElmt = document.getElementById("localBodyElectionData_main_button");
	if(!elmt || !buttonElmt)
		return;

	var str = '';
	for(var i=1;i<results.length;i++)
	{
		str += '<div>';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td><input type="checkbox" name="localBodiesCheck" value="'+results[i].id+'"></td>';		
		str += '<td>'+results[i].name+'</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
	}

	elmt.innerHTML = str;

	buttonElmt.innerHTML = '<div><input type="button" value="Attach" onclick="mapLocations()"></div>';

}

function getLocalBodiesOfDistrict(taskType)
{
	var jsObj=
		{
				id: districtValue,
				taskType:taskType,
				task: 'localElectionBodiesOfDistrict'
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;		
	callAjax(jsObj, url);
}

function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
							if(jsObj.task == "boothsOfAssembly")
							{
								displayAssemblyBooths(myResults);
							}
							else if(jsObj.task == "localElectionBodiesOfDistrict" && jsObj.taskType == "localBodyWise")
							{
								displayDistrictLocalBodies(myResults);
							}							
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function getElectionYear(eleYear){
	selectedYear = eleYear;
}
function yearsPopulationForMapping(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;

	var str ='';
	str += '<table>';
	str += '<tr>';
	str += '<th style="padding-right:13px;">Year:  </th>';
	str += '<td><select id="yearsId" class="selectWidth" onchange="getElectionYear(this.options[this.selectedIndex].value)">';
	str += '<option value="0">Select an year</option>';
	str += '<option value="2009">2009</option>';
	str += '<option value="2004">2004</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '</table>';

	elmt.innerHTML = str;
}

function validateAndForwardToAction(elmt)
{
	var errorFlag=0;
	var message="";	
	var electionFlag=0;				

	var stateId = document.getElementById("stateId").value;
	var districtId = document.getElementById("districtField").value;
	var yearsId = selectedYear;
	if(stateId==0){
		message+='Please Select State';
		message+='<br/>';
		errorFlag=1;
	}
	if(districtId==0){
		message+='Please Select District';
		message+='<br/>';
		errorFlag=1;
	}
	if(yearsId==0){
		message+='Please select year';
		message+='<br/>';
		errorFlag=1;
	} 	
	if(errorFlag==1){
		document.getElementById("errorMessage").innerHTML = message;
		return false;
	}else{
		var jsObj=
		{
				districtId:districtId,
				electionYear:yearsId,				
				task:"setDataForVillageBoothRelation"						
		};
			
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/setDataForVillageBooth.action?"+rparam;		
		callAjax(jsObj, url);
		return true;							
	}	
}
</script>
<style>
	.selectWidth{
		color:highlight;
		font-family:monospace;
		font-size:15px;
		font-weight:bold;
		width:300px;
	}
	#errorMessage
	{
		color:red;
		font-weight:bold;
		padding-top:12px;
	}
</style>
</head>
<body>		
		<table>
			<tr>
	 			<td align="left">
	 				<div id="errorMessage"></div>
	 			</td>
	 		</tr>
		</table>
		<table>
			<tr>
				<td colspan="2" align="left">
					<table>
						<tr>
							<td colspan="2" align="left"><div id="errorMessageDiv"></div></td>				
						</tr>
						<tr>
							<th align = "left">State:</th>
							<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select State" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','boothLocaElectionBodyMapper','districtField','currentAdd')" /></td>
						</tr>
						<tr>
							<th align = "left">District:</th>
							<td><s:select id="districtField" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select District" /></td>
						</tr>
					</table>
					<table>					
						<tr>	
							<td><div id="yearsPopulation"></div></td>
						</tr>			
					</table>
				</td>
			</tr>	
			<tr>
				<th colspan="2" align="center">
					<input id="viewReportButton" type="button" onClick="return validateAndForwardToAction(this)"  value="Submit"/>					
				</th>
	 		</tr>	
		</table>
			
<script type="text/javascript">
	getLocationHierarchies(1,'statesInCountry','boothLocaElectionBodyMapper','stateId','currentAdd');	
	yearsPopulationForMapping("yearsPopulation");
</script>
</body>
</html>