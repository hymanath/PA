<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Booth To Local Election Body</title>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css">
 
	<!--  YUI Dependencies 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script>
	 Local files Dependencies 
	
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
	
	
	--><script src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script>
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
var windowTask = '${windowTask}';

function getBoothOfAssemblyByYear(id)
{
	var yearsIdEl = document.getElementById("yearsId");	
	var year = yearsIdEl.options[yearsIdEl.selectedIndex].text;
	var localElectionBodyFieldEl = document.getElementById("localElectionBodyField");
	var localBodyId = localElectionBodyFieldEl.options[localElectionBodyFieldEl.selectedIndex].value;
	var jsObj=
		{
				localBodyId: localBodyId,
				constituencyId: id,
				task: 'boothsOfAssembly',
				year: year
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/boothsInLocalBodiesAndConstAction.action?"+rparam;		
	callAjax(jsObj, url);
}


function displayDistrictLocalBodies(results)
{
	var divElmt =  document.getElementById("mappingLocationsDiv");
	var elmt = document.getElementById("localBodyElectionData_main_right");
	var buttonElmt = document.getElementById("localBodyElectionData_main_button");
	divElmt.style.display = 'block';
	if(!elmt || !buttonElmt)
		return;

	var str = '';
	for(var i=0;i<results.length;i++)
	{
		str += '<div>';
		str += '<table width="100%">';
		if(results[i].flag == true)
		{
			str += '<tr>';
			str += '<td><input type="checkbox" id="'+results[i].regionId+'" checked="checked" name="localBodiesCheck" value="'+results[i].flag+'"></td>';		
			str += '<td>'+results[i].regionName+'</td>';
			str += '</tr>';
		} else 
		{
			str += '<tr>';
			str += '<td><input type="checkbox" id="'+results[i].regionId+'" name="localBodiesCheck" value="'+results[i].flag+'"></td>';		
			str += '<td>'+results[i].regionName+'</td>';
			str += '</tr>';
		}	
		str += '</table>';
		str += '</div>';
	}

	elmt.innerHTML = str;

	buttonElmt.innerHTML = '<div><input type="button" value="Attach" onclick="mapLocations()"></div>';

}

function displayAssemblyBooths(results)
{
	var divElmt =  document.getElementById("mappingLocationsDiv");
	var selectBoothEl = document.getElementById("localBodyElectionData_main_right");	
	var selectButtonEl = document.getElementById("localBodyElectionData_main_button");
	divElmt.style.display = 'block';
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
				if(results[i].flag == true)
				{	
					strBoothInfo+='<tr id = "'+results[i].regionId+'_row">';			
					strBoothInfo+= '<td width="50"><span><input name="localBodiesCheck" type="checkbox" checked="checked" id="'+results[i].regionId+'" value="'+results[i].flag+'"></span></td></td>';
					strBoothInfo+= '<td width="50"><span id="'+results[i].regionId+'_partNo">'+results[i].regionName+'</span></td>';
					strBoothInfo+= '<td><span id="'+results[i].regionId+'_villagesCovered">'+results[i].villagesCovered+'</span></td>';		
					strBoothInfo+='</tr>';
				} else 
				{
					strBoothInfo+='<tr id = "'+results[i].regionId+'_row">';			
					strBoothInfo+= '<td width="50"><span><input name="localBodiesCheck" type="checkbox" id="'+results[i].regionId+'" value="'+results[i].flag+'"></span></td></td>';
					strBoothInfo+= '<td width="50"><span id="'+results[i].regionId+'_partNo">'+results[i].regionName+'</span></td>';
					strBoothInfo+= '<td><span id="'+results[i].regionId+'_villagesCovered">'+results[i].villagesCovered+'</span></td>';		
					strBoothInfo+='</tr>';
				}	
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

function mapLocations()
{
	var localBodiesElmts = document.getElementsByName("localBodiesCheck");
	var localBodyIds = new Array();
	var modifyLocalBodyIds = new Array();
	for(var i=0; i<localBodiesElmts.length;i++)
	{
		if(localBodiesElmts[i].checked == true && localBodiesElmts[i].value == 'false')
			localBodyIds.push(localBodiesElmts[i].id);
		if(localBodiesElmts[i].checked == false && localBodiesElmts[i].value == 'true')
			modifyLocalBodyIds.push(localBodiesElmts[i].id);
	}
	
	var yearElmt = document.getElementById("yearsId");
	yearValue = yearElmt.options[yearElmt.selectedIndex].value;

	var constituencyElmt = document.getElementById("constituencyField");
	constituencyValue = constituencyElmt.options[constituencyElmt.selectedIndex].value;
	
	var localElectionBodyElmt = document.getElementById("localElectionBodyField");
	if(localElectionBodyElmt)
		localBodyElmtValue1 = localElectionBodyElmt.options[localElectionBodyElmt.selectedIndex].value;
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
	
	if(mapLevelValue == 'localBodyToAssembly')
	{
		isAssemblyToLocal = true;
	} else if(mapLevelValue == 'wardsInLocalBodyToAssembly')
	{
		isAssemblyToLocal = true;
		isWard = true;
		//localBodyField
		lebId = localBodyElmtValue;
		
	} else if(mapLevelValue == 'boothsInAssemblyToLocalBody')
	{
		isBoothToLocal = true;
		//localElectionBodyField
		constituencyValue = localBodyElmtValue1;
			
		 
	} else if(mapLevelValue == 'boothsInAssemblyToWardsInLocalBody')	
	{
		isBoothToLocal = true;
		isWard = true;
		constituencyValue = wardsFieldElmtValue;
	}
	/*	
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
	*/
	var jsObj=
	{
			isAssemblyToLocal : isAssemblyToLocal,
			isBoothToLocal : isBoothToLocal,
			isWard : isWard,
			mappedlocationId : constituencyValue,			
			listOfIds: localBodyIds,
			listOfModificationIds: modifyLocalBodyIds,
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

function getBoothsInConstituency()
{
	var constElmt = document.getElementById("constituencyField");
	var constElmtValue = constElmt.options[constElmt.selectedIndex].value;

	getBoothOfAssemblyByYear(constElmtValue);		
}

/*

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
	  var url = "saveMunicipalWardsAssemblyBoothsAjaxAction.action?"+rparam; 			
	  callAjax(jsObj, url);
  }
  
  */
  //
  function showSelectOptions()
  {	  
	  var mapLevelElmts = document.getElementsByName("mappingOptionsRadio");
	  var divElmt =  document.getElementById("mappingLocationsDiv");
	  var row1El = document.getElementById("row1");
	  var row2El = document.getElementById("row2");
	  var row3El = document.getElementById("row3");
	  var stateSelectElmt = document.getElementsByName("");
	  var distSelectElmt = document.getElementsByName("");
	  var constSelectElmt = document.getElementsByName("");
	if(divElmt.style.display == 'block')
		divElmt.style.display = 'none';
	  row1El.style.display = 'none';
	  row2El.style.display = 'none';
	  row3El.style.display = 'none';
	  
	for(var i=0; i<mapLevelElmts.length;i++)
	{
		if(mapLevelElmts[i].checked == true)
			mapLevelValue = mapLevelElmts[i].value;
	}
		 
	if(mapLevelValue == 'wardsInLocalBodyToAssembly')
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';								
		
	} else if(mapLevelValue == 'boothsInAssemblyToLocalBody')
	{
		if(row3El.style.display == 'none')
			row3El.style.display = '';				
		
	}else if(mapLevelValue == 'boothsInAssemblyToWardsInLocalBody')	
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';					
	}
	if(windowTask == 'update')
	{
		getLocalBodiesOfDistrict('localBodyWise');
	}
		
  }
  /*
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
	
	//if(mapLevelValue == 'assemblyLevel' && mapTypeValue == 'localBodyWise')
		//buildAssemblyLocalBodyWiseMapping();
	if(mapLevelValue == 'assemblyLevel' && mapTypeValue == 'wardWiseWise')
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
  }*/

  /*
  function buildAssemblyWardWiseMapping(elmtId)
  {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;

	var str ='';
	str += '<table>';	

	str += '<tr>';
	str += '<th>Select Constituency</th>';
	str += '<td><select id="constituencyField" class="selectWidth" onchange="getLocationHierarchies('+districtValue+',\'localElectionBodiesOfDistrict\',\'boothLocaElectionBodyMapper\',\'localBodyField\',\'currentAdd\')">';
	str += '<option>Select Assembly Constituencies</option>';
	str == '</select>';
	str += '</td>';
	str += '</tr>';
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

  function buildAssemblyLocalBodyWiseMapping()
  {
	  var row1El = document.getElementById("row1");
	  var row2El = document.getElementById("row2");
	  row1El.style.display = '';
	  row2El.style.display = '';
	  
  }*/

function getWardOfLocalBodies(id)
{
	if(mapLevelValue == 'boothsInAssemblyToWardsInLocalBody')
	{
		getLocationHierarchies(id,'wardsInALocalElectionBody','boothLocaElectionBodyMapper','wardsField','currentAdd')
	} else {
		var constituencySelectEl = document.getElementById("constituencyField");
		var constituencyId = constituencySelectEl.options[constituencySelectEl.selectedIndex].value;
		var yearsIdEl = document.getElementById("yearsId");	
		var year = yearsIdEl.options[yearsIdEl.selectedIndex].text;
		
		var jsObj=
			{
					localBodyId: id,
					constituencyId: constituencyId, 				
					task: 'wardsInALocalElectionBody',
					year: year
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/wardsInLocalBodiesAndConstAction.action?"+rparam;		
		callAjax(jsObj, url);
	}
}

function getLocalBodiesOfDistrict(taskType)
{
	//var mapLevelElmts = document.getElementsByName("mappingOptionsRadio");
	var districtSelectEl = document.getElementById("districtField");
	var id = districtSelectEl.options[districtSelectEl.selectedIndex].value;
	var constituencySelectEl = document.getElementById("constituencyField");
	var constituencyId = constituencySelectEl.options[constituencySelectEl.selectedIndex].value;
	var yearsIdEl = document.getElementById("yearsId");	
	var year = yearsIdEl.options[yearsIdEl.selectedIndex].text;
	if(mapLevelValue == 'localBodyToAssembly')
	{	
		var jsObj=
			{
				districtId: id,
				constituencyId: constituencyId,
				task: 'localElectionBodiesOfDistrict',
				year: year,
				taskType:'localBodyWise' 
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/localBodiesInDistAndConstAction.action?"+rparam;		
		callAjax(jsObj, url);
	} else if(mapLevelValue == 'wardsInLocalBodyToAssembly' || mapLevelValue == 'boothsInAssemblyToWardsInLocalBody') {
			getLocationHierarchies(id,'localElectionBodiesOfDistrict','boothLocaElectionBodyMapper','localBodyField','currentAdd');
	} else
		{
			getLocationHierarchies(id,'localElectionBodiesOfDistrict','boothLocaElectionBodyMapper','localElectionBodyField','currentAdd');
		}	
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
function executeOnload()
{
	//first pre select the first option when page loads
	 var  localBodyToAssemblyRadioEl = document.getElementById("localBodyToAssemblyRadio");
	localBodyToAssemblyRadioEl.checked = true;
	showSelectOptions();
	getLocalBodiesOfDistrict('localBodyWise');
	 
	
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
	#headingDiv
	{
		background-image:url(images/icons/constituencyManagement/header_body_blue.png);
		border:1px solid #75A0C2;
		height:25px;
		margin-bottom:15px;
		margin-top:15px;
		padding-top:5px;
		width:400px;
		font-weight:bold;
		color: #FFFFFF;
		font-size:14px;		
	}
	.tables
	{
		margin-left:150px;	
	}
	fieldset {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width:800px;
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;
		padding:5px;
	}
</style>
</head>
<body>
	<div id="headingDiv">Map Municipal/Corp/GMC To Assembly Constituencies</div>
	<div >	
		<div id="errorMessageDiv"></div>
		<fieldset>
		<legend>Mapping Criteria</legend>   
			<table width="100%">
			<tr>
				<td><input type="radio" id="localBodyToAssemblyRadio" name="mappingOptionsRadio" value="localBodyToAssembly" onclick="showSelectOptions()"/>Map Municipality/Corp/GMC to Assembly Constituency</td>
				<td><input type="radio" id="wardsInLocalBodyToAssemblyRadio" name="mappingOptionsRadio" value="wardsInLocalBodyToAssembly" onclick="showSelectOptions()"/>Map Wards in Municipality/Corp/GMC to Assembly Constituency</td>				
			</tr>
			<tr>
				<td><input type="radio" id="boothsInAssemblyToLocalBodyRadio" name="mappingOptionsRadio" value="boothsInAssemblyToLocalBody" onclick="showSelectOptions()"/>Map Booths in Assembly Constituency to Municipality/Corp/GMC</td>
				<td><input type="radio" id="boothsInAssemblyToWardsInLocalBodyRadio" name="mappingOptionsRadio" value="boothsInAssemblyToWardsInLocalBody" onclick="showSelectOptions()"/>Map Booths in Assembly Constituency to Wards in Municipality/Corp/GMC</td>				
			</tr>
			</table>
		</fieldset>
		<fieldset>
		<legend>Select The Location To which Mapping to be done</legend>
		<table width="100%">
		<!--<tr>
			<th>Mapping Level</th>
				<td>
					<input type="radio" name="mappingLevelRadio" checked="checked" value="assemblyLevel" onclick="showLocalElectionBody()"/>Assembly Level
					<input type="radio" name="mappingLevelRadio" value="boothLevel" onclick="showLocalElectionBody()"/>Booth Level
				</td>
		</tr>
		<tr>
			<th>Mapping Type</th>
			<td>
				<input type="radio" name="mappingTypeRadio" checked="checked" value="localBodyWise" onclick="showLocalElectionBody()"/>Local Body Wise Mapping
				<input type="radio" name="mappingTypeRadio" value="wardWiseWise" onclick="showLocalElectionBody()"/>Ward Wise Mapping
			</td>
		</tr>
		-->
		<tr>
			<th align = "left">Year</th>
			<td><s:select id="yearsId" theme="simple" list="{2009}" cssClass="selectWidth"/></td>
			<th align = "left">State:</th>
			<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="stateList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','boothLocaElectionBodyMapper','districtField','currentAdd')" /></td>
		</tr>
		<tr>
			<th align = "left">District:</th>
			<td><s:select id="districtField" theme="simple" cssClass="selectWidth" list="districtList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','boothLocaElectionBodyMapper','constituencyField','currentAdd')" /></td>
			<th>Constituency</th>
			<td><s:select id="constituencyField" theme="simple" cssClass="selectWidth"list="constituencyList" listKey="id"  listValue="name"  onchange="getLocalBodiesOfDistrict('localBodyWise')"/></td>
		</tr>				
		<tr id="row1" style="display:none;">
			<th align="left">Municipal/Corp/GMC</th>
			<td><s:select id="localBodyField" theme="simple" cssClass="selectWidth" onchange="getWardOfLocalBodies(this.options[this.selectedIndex].value)" list="{}" listKey="id"  listValue="name" headerKey="0" headerValue="Select Location"/></td>
		</tr>
		<tr id="row3" style="display:none;">
			<th align="left">Municipal/Corp/GMC</th>
			<td><s:select id="localElectionBodyField" theme="simple" cssClass="selectWidth" onchange="getBoothsInConstituency()" list="{}" listKey="id"  listValue="name" headerKey="0" headerValue="Select Location"/></td>
		</tr>
		<tr id="row2" style="display:none;">
			<th align="left">Ward</th>
			<td><s:select id="wardsField" theme="simple" cssClass="selectWidth" onchange="getBoothsInConstituency()" list="{}" listKey="id"  listValue="name" headerKey="0" headerValue="Select Location"/></td>
		</tr>						
	</table>
	</fieldset>
	<div id="mappingLocationsDiv" style="display:none;">
		<fieldset style="width:400px;">
		<legend>Select The Sub Regions to be mapped</legend>
			<div id="localBodyElectionData_main_right" style="height:200px;overflow:auto;"></div>
			<div id="localBodyElectionData_main_button" ></div>			
		</fieldset>
	</div>
	
		
	</div>				
	<script type="text/javascript">

	if(windowTask == 'update')
			executeOnload();
	else 
		getLocationHierarchies(1,'statesInCountry','boothLocaElectionBodyMapper','stateId','currentAdd');
		
	</script>
</body>
</html>