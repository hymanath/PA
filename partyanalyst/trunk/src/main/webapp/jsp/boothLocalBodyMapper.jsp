<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Booth To Local Election Body</title>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css">
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

function displayConstLocalBodies(results)
{
	var divElmt =  document.getElementById("mappingLocationsDiv");
	var elmt = document.getElementById("localBodyElectionData_main_right");
	var buttonElmt = document.getElementById("localBodyElectionData_main_button");
	divElmt.style.display = 'block';
	if(!elmt || !buttonElmt)
		return;

	var str = '';
	for(var i=1;i<results.length;i++)
	{
		str += '<div>';
		str += '<table width="100%">';
		str += '<tr>';
		str += '<td><input type="checkbox" id="'+results[i].id+'" checked="checked" name="localBodiesCheck" value="true"></td>';		
		str += '<td>'+results[i].name+'</td>';
		str += '</tr>';
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
	var errorMessageDivEl = document.getElementById("errorMessageDiv");
	var localBodyIds = new Array();
	var modifyLocalBodyIds = new Array();
	for(var i=0; i<localBodiesElmts.length;i++)
	{
		if(localBodiesElmts[i].checked == true && localBodiesElmts[i].value == 'false')
			localBodyIds.push(localBodiesElmts[i].id);
		if(localBodiesElmts[i].checked == false && localBodiesElmts[i].value == 'true')
			modifyLocalBodyIds.push(localBodiesElmts[i].id);
	}
	errorMessageDivEl.innerHTML = '';
		
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

function getBoothsInWard()
{
	var constElmt = document.getElementById("constituencyField");
	var constElmtValue = constElmt.options[constElmt.selectedIndex].value;
	var errorMessageDivEl = document.getElementById("errorMessageDiv");
	errorMessageDivEl.innerHTML = '';
	if(constElmtValue == 0)
	{
		errorMessageDivEl.innerHTML = 'Please Select Valid Constituency';
		return;
	}
		
	getBoothOfWardByYear(constElmtValue);		
}

function getBoothOfWardByYear(id)
{
	var yearsIdEl = document.getElementById("yearsId");	
	var year = yearsIdEl.options[yearsIdEl.selectedIndex].text;
	var wardsFieldEl = document.getElementById("wardsField");
	var wardId = wardsFieldEl.options[wardsFieldEl.selectedIndex].value;
	var jsObj=
		{
				wardId: wardId,
				constituencyId: id,
				task: 'boothsOfWard',
				year: year
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/boothsInWardAndConstAction.action?"+rparam;		
	callAjax(jsObj, url);
}

  function showSelectOptions(event)
  {	  
	  var mapLevelElmts = document.getElementsByName("mappingOptionsRadio");
	  var divElmt =  document.getElementById("mappingLocationsDiv");
	  var constituencyFieldEl = document.getElementById("constituencyField");
	  var constituencyFieldElVal = constituencyFieldEl.options[constituencyFieldEl.selectedIndex].value;
	  var row1El = document.getElementById("row1");
	  var row2El = document.getElementById("row2");
	  var row3El = document.getElementById("row3");
	  var stateSelectElmt = document.getElementsByName("");
	  var distSelectElmt = document.getElementsByName("");
	  var constSelectElmt = document.getElementsByName("");
	  var errorMessageDivEl = document.getElementById("errorMessageDiv");
	  var confirmMessageDivEl = document.getElementById("confirmMessageDiv");
	  confirmMessageDivEl.innerHTML = '';	  
	  errorMessageDivEl.innerHTML = '';
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
	if(mapLevelValue == 'localBodyToAssembly')
	{
		if(event == 'onClick')
			constituencyFieldEl.selectedIndex = 0;
	}	 
	else if(mapLevelValue == 'wardsInLocalBodyToAssembly')
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(constituencyFieldElVal != 0)
			getLocalBodiesOfConstituency(constituencyFieldElVal);								
		
	} else if(mapLevelValue == 'boothsInAssemblyToLocalBody')
	{
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(constituencyFieldElVal != 0)
			getLocalBodiesOfConstituency(constituencyFieldElVal);				
		
	}else if(mapLevelValue == 'boothsInAssemblyToWardsInLocalBody')	
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(constituencyFieldElVal != 0)
			getLocalBodiesOfConstituency(constituencyFieldElVal);							
	}	
		
  }
  
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
	var mapLevelElmts = document.getElementsByName("mappingOptionsRadio");
	var errorMessageDivEl = document.getElementById("errorMessageDiv");
	errorMessageDivEl.innerHTML = '';
	var optionsFlag = false;
	for(var i=0; i<mapLevelElmts.length;i++)
	{
		if(mapLevelElmts[i].checked == true)
			optionsFlag = true;
	}
	if(optionsFlag == false)
		errorMessageDivEl.innerHTML = 'Please Select Mapping Criteria';		
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
							if(jsObj.task == "boothsOfAssembly" || jsObj.task == "boothsOfWard")
							{
								displayAssemblyBooths(myResults);
							}
							else if(jsObj.task == "localElectionBodiesOfDistrict" && jsObj.taskType == "localBodyWise")
							{
								displayDistrictLocalBodies(myResults);
							}
							else if(jsObj.taskType == "localBodyWise")
							{
								displayConstLocalBodies(myResults);
							}
							else if(jsObj.task == "wardsInALocalElectionBody")
							{
								displayDistrictLocalBodies(myResults);
							}
							else if(jsObj.task == "localBodiesInConstituency")
							{
								var confirmMessageDivEl = document.getElementById("confirmMessageDiv");
								confirmMessageDivEl.innerHTML = '';
								
								if(myResults.length > 1)
								{
									clearOptionsListForSelectElmtId(jsObj.selectElementId);
									fillOptionsForSelectedElmt(jsObj.selectElementId, myResults);	
								} else 
									showNonAvailabilityMessage();
								
							} else if(jsObj.task == "mapLocations")
							{
								showConfirmOrError(myResults);
							}
							
							
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showNonAvailabilityMessage()
{
	var confirmMessageDivEl = document.getElementById("confirmMessageDiv");
	confirmMessageDivEl.innerHTML = '';
	confirmMessageDivEl.innerHTML = '<P>Zero Municipal/CORP/GMC are mapped to this constituency.Select <B>Map Municipality/Corp/GMC To Assembly Constituency</B> to map Municipality/Corp/GMC to a constituency</P>';
	
}

function showConfirmOrError(results)
{
	var errorMessageDivEl = document.getElementById("errorMessageDiv");
	errorMessageDivEl.innerHTML = '';
	var confirmMessageDivEl = document.getElementById("confirmMessageDiv");
	confirmMessageDivEl.innerHTML = '';
	var constituencyFieldEl = document.getElementById("constituencyField");
	var localBodyFieldEl = document.getElementById("localBodyField");
	var localElectionBodyFieldEl = document.getElementById("localElectionBodyField");
	var wardsFieldEl = document.getElementById("wardsField");
	
	constituencyFieldEl.selectedIndex = 0;
	if(localBodyFieldEl)
		localBodyFieldEl.selectedIndex = 0;
	if(localElectionBodyFieldEl)
		localElectionBodyFieldEl.selectedIndex = 0;
	if(wardsFieldEl)
		wardsFieldEl.selectedIndex = 0;
	var divElmt =  document.getElementById("mappingLocationsDiv");
	if(results.exceptionEncountered == null)
	{
		confirmMessageDivEl.innerHTML='Database Changes were made Successfully!';
		if(divElmt.style.display == 'block')
			divElmt.style.display = 'none';
		
		
	} else if(results.exceptionEncountered != null) 
		{
			errorMessageDivEl.innerHTML = 'Error occured while processing your request.';
		}
}
function executeOnload()
{
	//first pre select the first option when page loads
	 var  localBodyToAssemblyRadioEl = document.getElementById("localBodyToAssemblyRadio");
	localBodyToAssemblyRadioEl.checked = true;
	showSelectOptions('onLoad');
	//getLocalBodiesOfDistrict('localBodyWise');	
}

function getLocalBodiesOfConstituency(id)
{
	var mapLevelElmts = document.getElementsByName("mappingOptionsRadio");
	var errorMessageDivEl = document.getElementById("errorMessageDiv");
	errorMessageDivEl.innerHTML = '';
	var optionsFlag = false;
	var divElmt =  document.getElementById("mappingLocationsDiv");
	divElmt.style.display = 'none';
	var wardsFieldEl = document.getElementById("wardsField");
	wardsFieldEl.selectedIndex = 0;
	for(var i=0; i<mapLevelElmts.length;i++)
	{
		if(mapLevelElmts[i].checked == true)
			optionsFlag = true;
	}
	if(optionsFlag == false)
	{
		errorMessageDivEl.innerHTML = 'Please Select Mapping Criteria';
		return;		
	} else if(id == 0)
	{
		errorMessageDivEl.innerHTML = 'Please Select Valid Constituency';
		return;
	}
	var yearsIdEl = document.getElementById("yearsId");	
	var year = yearsIdEl.options[yearsIdEl.selectedIndex].text;
	
	if(mapLevelValue == 'localBodyToAssembly')
	{
		getLocalBodiesOfDistrict('localBodyWise');		
		
	} else if(mapLevelValue == 'boothsInAssemblyToWardsInLocalBody' || mapLevelValue == 'wardsInLocalBodyToAssembly')
	{
		getLocalBodiesInConstituency(id, 'localBodyField', year)
		//getLocationHierarchies(id, 'localBodiesInConstituency', 'boothLocaElectionBodyMapper', 'localBodyField', 'currentAdd', 'URBAN',null);
		
	} else {
		getLocalBodiesInConstituency(id, 'localElectionBodyField', year)
		//getLocationHierarchies(id, 'localBodiesInConstituency', 'boothLocaElectionBodyMapper', 'localElectionBodyField', 'currentAdd', 'URBAN',null);
	}
}

function getLocalBodiesInConstituency(id, selectElement, year)
{
	 var jsObj=
		{
			id: id,
			task: 'localBodiesInConstituency',
			taskType:'',
			selectElementId:selectElement,
			year: year
		}	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "locationsHierarchiesAjaxAction.action?"+rparam;		
		callAjax(jsObj, url);
		
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
	#errorMessageDiv
	{
		text-align:left;
		color:red;
		width:800px;
		font-weight:bold;
		margin:5px;
	}
	#confirmMessageDiv
	{
		text-align:left;
		color:green;
		width:800px;
		font-weight:bold;
		margin:5px;
	}
</style>
</head>
<body>
	<div id="headingDiv">Map Municipal/Corp/GMC To Assembly Constituencies</div>
	<div >	
		<div id="confirmMessageDiv"></div>
		<div id="errorMessageDiv"></div>
		<fieldset>
		<legend>Mapping Criteria</legend>   
			<table width="100%">
			<tr>
				<td><input type="radio" id="localBodyToAssemblyRadio" name="mappingOptionsRadio" value="localBodyToAssembly" onclick="showSelectOptions('onClick')"/>Map <B>Municipality/Corp/GMC To Assembly Constituency</B></td>
				<td><input type="radio" id="wardsInLocalBodyToAssemblyRadio" name="mappingOptionsRadio" value="wardsInLocalBodyToAssembly" onclick="showSelectOptions('onClick')"/>Map <B>Wards</B> in Municipality/Corp/GMC <B>To Assembly Constituency</B></td>				
			</tr>
			<tr>
				<td><input type="radio" id="boothsInAssemblyToLocalBodyRadio" name="mappingOptionsRadio" value="boothsInAssemblyToLocalBody" onclick="showSelectOptions('onClick')"/>Map <B>Booths</B> in Assembly Constituency <B>To Municipality/Corp/GMC</B></td>
				<td><input type="radio" id="boothsInAssemblyToWardsInLocalBodyRadio" name="mappingOptionsRadio" value="boothsInAssemblyToWardsInLocalBody" onclick="showSelectOptions('onClick')"/>Map <B>Booths</B> in Assembly Constituency <B>To Wards</B> in Municipality/Corp/GMC</td>				
			</tr>
			</table>
		</fieldset>
		<fieldset>
		<legend>Select The Location To which Mapping to be done</legend>
		<table width="100%">
		<tr>
			<th align = "left">Year</th>
			<td><s:select id="yearsId" theme="simple" list="{2004,2009,2010,2011}" cssClass="selectWidth"/></td>
			<th align = "left">State</th>
			<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="stateList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','boothLocaElectionBodyMapper','districtField','currentAdd')" /></td>
		</tr>
		<tr>
			<th align = "left">District</th>
			<td><s:select id="districtField" theme="simple" cssClass="selectWidth" list="districtList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value, 'getConstNotInGivenAreaType', 'boothLocaElectionBodyMapper', 'constituencyField', 'currentAdd', 'RURAL',null)" /></td>
			<th>Constituency</th>
			<td><s:select id="constituencyField" theme="simple" cssClass="selectWidth"list="constituencyList" listKey="id"  listValue="name" value="defaultConstituency"  onchange="getLocalBodiesOfConstituency(this.options[this.selectedIndex].value)"/></td>
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
			<td><s:select id="wardsField" theme="simple" cssClass="selectWidth" onchange="getBoothsInWard()" list="{}" listKey="id"  listValue="name" headerKey="0" headerValue="Select Location"/></td>
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
		
	</script>
</body>
</html>