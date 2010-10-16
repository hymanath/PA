<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Booth To Local Election Body</title>

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
var mapLevelValue;
var mapTypeValue;

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

function mapLocations()
{
	var localBodiesElmts = document.getElementsByName("localBodiesCheck");
	var localBodyIds = new Array();
	for(var i=0; i<localBodiesElmts.length;i++)
	{
		if(localBodiesElmts[i].checked == true)
			localBodyIds.push(localBodiesElmts[i].value);
	}
	
	var yearElmt = document.getElementById("yearsId");
	yearValue = yearElmt.options[yearElmt.selectedIndex].value;

	var constituencyElmt = document.getElementById("constituencyField");
	constituencyValue = constituencyElmt.options[constituencyElmt.selectedIndex].value;
	
	
	var localBodyElmt = document.getElementById("localBodyField");
	if(localBodyElmt)
		localBodyElmtValue = localBodyElmt.options[localBodyElmt.selectedIndex].value;
	
	var wardsFieldElmt = document.getElementById("wardsField");
	if(wardsFieldElmt)
		wardsFieldElmtValue = wardsFieldElmt.options[wardsFieldElmt.selectedIndex].value;
	
	
	var isAssemblyToLocal = false;
	var isBoothToLocal = false;
	var isWard = false;
	var lebId = 0;

	if(mapLevelValue == "assemblyLevel")
		isAssemblyToLocal = true;
	else if(mapLevelValue == "boothLevel")
		isBoothToLocal = true;
	
	if(mapTypeValue == "wardWiseWise")
		isWard = true;
	
	if(isAssemblyToLocal && isWard)
		lebId = localBodyElmtValue;
	
	
	if(isBoothToLocal && mapTypeValue == "localBodyWise")
		constituencyValue = localBodyElmtValue;
	else if(isBoothToLocal && mapTypeValue == "wardWiseWise")
		constituencyValue = wardsFieldElmtValue;
	
	var jsObj=
	{
			isAssemblyToLocal : isAssemblyToLocal,
			isBoothToLocal : isBoothToLocal,
			isWard : isWard,
			mappedlocationId : constituencyValue,			
			listOfIds: localBodyIds,
			lebId: lebId,
			year: yearValue,			
			task: 'mapLocations'
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/saveMunicipalWardsAssemblyBoothsAjaxAction.action?"+rparam;		

	var r=confirm("Do You Want To Attach");
	if (r==true)
	{
	  callAjax(jsObj, url);
	}
	
}

function displayAssemblyBooths(results)
{
	var selectBoothEl = document.getElementById("localBodyElectionData_main_right");	
	var selectButtonEl = document.getElementById("localBodyElectionData_main_button");

	var strBoothInfo='';
	strBoothInfo+='<table>';
	strBoothInfo+='<tr>';
	strBoothInfo+='<td>';
	strBoothInfo+='<table>';
	strBoothInfo+='<tr>';
	strBoothInfo+='<th width="50">Select</th>';
	strBoothInfo+='<th width="50">Booth No</th>';
	strBoothInfo+='<th>Villages Covered</th>';
	strBoothInfo+='</tr>';
	strBoothInfo+='</table>';
	strBoothInfo+='</td>';	
	strBoothInfo+='</tr>';
	strBoothInfo+='<tr>';
	strBoothInfo+='<td>';
		strBoothInfo+='<div id="wardsDiv">';
			strBoothInfo+='<table>';				
			for(var i in results)
			{	
				strBoothInfo+='<tr id = "'+results[i].boothConstiElecId+'_row">';			
				strBoothInfo+= '<td width="50"><span><input name="localBodiesCheck" type="checkbox" value="'+results[i].boothConstiElecId+'"></span></td></td>';
				strBoothInfo+= '<td width="50"><span id="'+results[i].boothConstiElecId+'_partNo">'+results[i].partNo+'</span></td>';
				strBoothInfo+= '<td><span id="'+results[i].boothConstiElecId+'_villagesCovered">'+results[i].villagesCovered+'</span></td>';		
				strBoothInfo+='</tr>';
			}			
			strBoothInfo+='</table>';
		strBoothInfo+='</div>';
	strBoothInfo+='</td>';
	strBoothInfo+='</tr>';
	strBoothInfo+='</table>';	

	var strButton = '';
	strButton += '<div><input type="button" value="Attach" onclick="mapLocations()"></div>';

	selectBoothEl.innerHTML = strBoothInfo;
	selectButtonEl.innerHTML = strButton;	
}
function getBoothIdsIntoArray(id){
	 boothIds.push(id.substring(0,id.indexOf('_')));
 }
function showHamletBoothMappings(){
		
	var hamletSelectEl = document.getElementById("wardsField");
	var errorDivEl = document.getElementById("errorMessageDiv");
	locationName = hamletSelectEl.options[hamletSelectEl.selectedIndex].text;
	locationId = hamletSelectEl.options[hamletSelectEl.selectedIndex].value;

	if(locationId == 0)
	{
		errorDivEl.innerHTML = '<span style="color:RED"><b>Please Select Ward</b></span>' ;
		return;
	}else{
		errorDivEl.innerHTML = '';
	}		
	var str = '';
	str += '<table width="100%">';
	str += '<tr><th width="50" align="left">Village</th>';
	str += '<th width="50" align="left">Booth No</th>';
	str += '<th align="left">Locations Covered</th></tr>';
	str +='</table>';
	str += '<div style="overflow:auto;height:400px;">';
	str +='<table width="100%">';	
	for(var i in boothIds){
		str += '<tr>';
		str += '<td width="50">'+locationName+'</td>';
		str += '<td width="50">'+document.getElementById(boothIds[i]+"_partNo").innerHTML+'</td>';
		str += '<td>'+document.getElementById(boothIds[i]+"_villagesCovered").innerHTML+'</td>';
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	str += '<table>';
	str += '<tr>';
	str += '<td align="center"><input type="button" value="Confirm" onclick="sendDataToAction()" align = "middle"/></td>';
	str += '<td><input type="button" value="Cancel" onclick="hidePanel()" align = "middle"/></td>';
	str += '</tr>';
	str += '</table>';
	
	myPanel = new YAHOO.widget.Panel("boothHamletMapPanel", {
            width: "450px", 
            fixedcenter: true, 
            constraintoviewport: false, 
            underlay: "none", 
            close: true, 
            visible: true, 
            draggable: true
    });
    
    myPanel.setHeader("Mapped Booth Information");
    myPanel.setBody(str);
	myPanel.render();
	    
  }
  function hidePanel(){
	  myPanel.hide();
  }

  function sendDataToAction(){
	  var hamletSelectEl = document.getElementById("wardsField");
	  var locationId = hamletSelectEl.options[hamletSelectEl.selectedIndex].value;
	  for(var i in boothIds){
		 	var deleteElmt = document.getElementById(boothIds[i]+'_row');
		    deleteElmt.style.display = 'none';
		}
	  myPanel.hide();
	  var jsObj=
		{
			  	wardId: locationId,
			 	boothIds: boothIds,
				task:"storeWardBoothInfo"
		};
	  boothIds = new Array();
	  var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);	
	  var url = "<%=request.getContextPath()%>/saveMunicipalWardsAssemblyBoothsAjaxAction.action?"+rparam; 			
	  callAjax(jsObj, url);
  }

  
  function showLocalElectionBody()
  {
	
	var stateElmt = document.getElementById("stateId");
	stateValue = stateElmt.options[stateElmt.selectedIndex].value;

	var districtElmt = document.getElementById("districtField");
	districtValue = districtElmt.options[districtElmt.selectedIndex].value;

	getLocationHierarchies(districtValue,'constituenciesInDistrict','boothLocaElectionBodyMapper','constituencyField','currentAdd');

	var elmt = document.getElementById("localBodyElectionData_main_right");
	elmt.innerHTML = '';


	var mapLevelElmts = document.getElementsByName("mappingLevelRadio");
	for(var i=0; i<mapLevelElmts.length;i++)
	{
		if(mapLevelElmts[i].checked == true)
			mapLevelValue = mapLevelElmts[i].value;
	}

	var mapTypeElmts = document.getElementsByName("mappingTypeRadio");	
	for(var i=0; i<mapTypeElmts.length;i++)
	{
		if(mapTypeElmts[i].checked == true)
			mapTypeValue = mapTypeElmts[i].value;
	}
	
	if(mapLevelValue == 'assemblyLevel' && mapTypeValue == 'localBodyWise')
		buildAssemblyLocalBodyWiseMapping("localBodyElectionData_main_left");
	else if(mapLevelValue == 'assemblyLevel' && mapTypeValue == 'wardWiseWise')
		buildAssemblyWardWiseMapping("localBodyElectionData_main_left");
	else if(mapLevelValue == 'boothLevel' && mapTypeValue == 'localBodyWise')
		buildBoothLocalBodyWiseMapping("localBodyElectionData_main_left");
	else if(mapLevelValue == 'boothLevel' && mapTypeValue == 'wardWiseWise')
		buildBoothWardWiseMapping("localBodyElectionData_main_left");
  }
	
  function buildBoothWardWiseMapping(elmtId)
  {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;

	var str ='';
	str += '<table>';
	str += '<tr>';
	str += '<th>Year</th>';
	str += '<td><select id="yearsId" class="selectWidth">';
	str += '<option>2009</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Constituency</th>';
	str += '<td><select id="constituencyField" class="selectWidth" onchange="getLocationHierarchies('+districtValue+',\'localElectionBodiesOfDistrict\',\'boothLocaElectionBodyMapper\',\'localBodyField\',\'currentAdd\')">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Local Body</th>';
	str += '<td><select id="localBodyField" class="selectWidth" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'wardsInALocalElectionBody\',\'boothLocaElectionBodyMapper\',\'wardsField\',\'currentAdd\')">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Ward</th>';
	str += '<td><select id="wardsField" class="selectWidth" onchange="getBoothsInConstituency(this.options[this.selectedIndex].value)">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '</table>';

	elmt.innerHTML = str;
  }

  function buildBoothLocalBodyWiseMapping(elmtId)
  {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;

	var str ='';
	str += '<table>';
	str += '<tr>';
	str += '<th>Year</th>';
	str += '<td><select id="yearsId" class="selectWidth">';
	str += '<option>2009</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Constituency</th>';
	str += '<td><select id="constituencyField" class="selectWidth" onchange="getLocationHierarchies('+districtValue+',\'localElectionBodiesOfDistrict\',\'boothLocaElectionBodyMapper\',\'localBodyField\',\'currentAdd\')">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Local Body</th>';
	str += '<td><select id="localBodyField" class="selectWidth" onchange="getBoothsInConstituency(this.options[this.selectedIndex].value)">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '</table>';

	elmt.innerHTML = str;
  }

  function getBoothsInConstituency()
  {
	var constElmt = document.getElementById("constituencyField");
	var constElmtValue = constElmt.options[constElmt.selectedIndex].value;

	getBoothOfAssemblyByYear(constElmtValue);		
  }

  function buildAssemblyWardWiseMapping(elmtId)
  {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;

	var str ='';
	str += '<table>';
	str += '<tr>';
	str += '<th>Year</th>';
	str += '<td><select id="yearsId" class="selectWidth">';
	str += '<option>2009</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Constituency</th>';
	str += '<td><select id="constituencyField" class="selectWidth" onchange="getLocationHierarchies('+districtValue+',\'localElectionBodiesOfDistrict\',\'boothLocaElectionBodyMapper\',\'localBodyField\',\'currentAdd\')">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Local Body</th>';
	str += '<td><select id="localBodyField" class="selectWidth" onchange="getWardOfLocalBodies(this.options[this.selectedIndex].value)">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '</table>';

	elmt.innerHTML = str;
  }

  function buildAssemblyLocalBodyWiseMapping(elmtId)
  {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;

	var str ='';
	str += '<table>';
	str += '<tr>';
	str += '<th>Year</th>';
	str += '<td><select id="yearsId" class="selectWidth">';
	str += '<option>2009</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '<tr>';
	str += '<th>Select Constituency</th>';
	str += '<td><select id="constituencyField" class="selectWidth" onchange="getLocalBodiesOfDistrict(\'localBodyWise\')">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '<tr>';
	str += '</table>';

	elmt.innerHTML = str;
  }

function getWardOfLocalBodies(id)
{
	var jsObj=
		{
				id: id,				
				task: 'wardsInALocalElectionBody'
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;		
	callAjax(jsObj, url);
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
							else if(jsObj.task == "wardsInALocalElectionBody")
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

</script>
<style>
	.selectWidth{
		color:highlight;
		font-family:monospace;
		font-size:15px;
		font-weight:bold;
		width:300px;
	}
</style>
</head>
<body>		
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
					<td><s:select id="districtField" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select District" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','boothLocaElectionBodyMapper','constituencyField','currentAdd');getLocationHierarchies(this.options[this.selectedIndex].value,'localElectionBodiesOfDistrict','boothLocaElectionBodyMapper','localElectionBodyField','currentAdd');showLocalElectionBody()" /></td>
				</tr>
				<tr>
					<th>Mapping Level</th>
					<td>
						<input type="radio" name="mappingLevelRadio" checked="checked" value="assemblyLevel" onclick="showLocalElectionBody()">Assembly Level</input>
						<input type="radio" name="mappingLevelRadio" value="boothLevel" onclick="showLocalElectionBody()">Booth Level</input>
					</td>
				</tr>
				<tr>
					<th>Mapping Type</th>
					<td>
						<input type="radio" name="mappingTypeRadio" checked="checked" value="localBodyWise" onclick="showLocalElectionBody()">Local Body Wise Mapping</input>
						<input type="radio" name="mappingTypeRadio" value="wardWiseWise" onclick="showLocalElectionBody()">Ward Wise Mapping</input>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td align="left" valign="top">
			<div id="localBodyElectionData_main_left">
				
			</div>
		</td>
		<td align="right" valign="top">
			<div id="localBodyElectionData_main_right" style="height:200px;overflow:auto;width:300px;">
				
			</div>
			<div id="localBodyElectionData_main_button" >
				
			</div>			
		</td>
	</tr>
	</table>
		<div id="boothsList"></div>
		<div id="hamletButtonDiv" ></div>
		<div class="yui-skin-sam">
			<div id="boothHamletMapPanel" ></div>
		</div>	
				
		<script type="text/javascript">
		getLocationHierarchies(1,'statesInCountry','boothLocaElectionBodyMapper','stateId','currentAdd')
		
		</script>
</body>
</html>