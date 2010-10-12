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
function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							if(jsObj.task == "boothsOfAssembly")
							{
								displayAssemblyBooths(myResults);
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

function displayAssemblyBooths(results)
{
	var selectBoothEl = document.getElementById("boothsList");
	var selectButtonEl = document.getElementById("hamletButtonDiv");
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
		strBoothInfo+='<div id="wardsDiv" style="overflow:auto;height:200px;width:500px">';
			strBoothInfo+='<table>';				
			for(var i in results)
			{	
				strBoothInfo+='<tr id = "'+results[i].boothConstiElecId+'_row">';			
				strBoothInfo+= '<td width="50"><span><input type="checkbox" id="'+results[i].boothConstiElecId+'_input" onclick ="getBoothIdsIntoArray(this.id)"></span></td></td>';
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
	strButton += '<input type="button" value="Attach" onclick="showHamletBoothMappings()" align = "middle"/>';

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
				<td colspan="2" align="left"><div id="errorMessageDiv"></div></td>				
			</tr>
			<tr>
				<th align = "left">State:</th>
				<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select State" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','boothLocaElectionBodyMapper','districtField','currentAdd')" /></td>
			</tr>
			<tr>
				<th align = "left">District:</th>
				<td><s:select id="districtField" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select District" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','boothLocaElectionBodyMapper','constituencyField','currentAdd');getLocationHierarchies(this.options[this.selectedIndex].value,'localElectionBodiesOfDistrict','boothLocaElectionBodyMapper','localElectionBodyField','currentAdd')" /></td>
			</tr>
		</table>
		<table>
		<tr>
		<td>
			<table>	
				<tr>
					<th align = "left">Local Election Body:</th>
					<td><s:select id="localElectionBodyField" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select Local Election Body" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'wardsInALocalElectionBody','boothLocaElectionBodyMapper','wardsField','currentAdd')" /></td>
				</tr>			
				<tr>
					<th align = "left">Wards:</th>
					<td><s:select id="wardsField" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select Local Election Body" onchange="" /></td>
				</tr>
			</table>
		</td>
		<td>	
			<table>			
				<tr>
					<th align = "left">Assembly:</th>
					<td><s:select id="constituencyField" theme="simple" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey ="0" headerValue="Select Assembly Constituencies" onchange="getBoothOfAssemblyByYear(this.options[this.selectedIndex].value)" /></td>
				</tr>
				
				<tr>
					<th align = "left">Year:</th>
					<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="{'2009'}" /></td>
				</tr>
			</table>
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