<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Management </title>

	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo/yahoo-min.js"></script>

	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/button/button-min.js"></script> 	
	<script src="http://yui.yahooapis.com/2.8.0r4/build/resize/resize-min.js"></script> 
	<script src="http://yui.yahooapis.com/2.8.0r4/build/layout/layout-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/container/container-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dom/dom-min.js"></script> 
	
	<script type="text/javascript" src="http://yui.yahooapis.com/3.0.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/gallery-2009.11.09-19/build/gallery-accordion/gallery-accordion-min.js"></script>

	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/calendar/calendar-min.js"></script> 

	<script type="text/javascript" src="js/json/json-min.js"></script> 
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/assets/skins/sam/resize.css"> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/assets/skins/sam/layout.css">
	
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/button/assets/skins/sam/button.css"> 

	<!-- Combo-handled YUI CSS files: -->
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2009.11.09-19/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">	
	<!--CSS file (default YUI Sam Skin) --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/calendar/assets/skins/sam/calendar.css"> 

	<!-- YUI Dependency files (End) -->

	<!--<script type="text/javascript" src="js/cadreManagement/cadreSMSPageJs.js"></script>-->

<style type="text/css">

	#CadreManagementHeaderMain
	{
		color:#1C487A;
		font-size:17px;
		font-weight:bold;
		text-decoration:underline;
	}
	#cadreManagementMainDiv
	{
		background-color:#EFEFEF;
		height:920px;
		margin-left:25px;
		margin-right:25px;
		text-align:left;
	}

	#cadreSMSGroupsMainDiv
	{
		height:330px;
		width:auto;
		background-color:#CBDCE8;		
	}
	
	#cadreDetailsMainDiv
	{
		height:100%;
		width:100%;
		background-color:#F9FCFF;
	}	

	#cadreTeamsMainDiv
	{
		height:99%;		
		width:99%;
	}

	#cadreEventsCalMainDiv
	{
		height:100%;
		width:100%;
		background-color:#CBDCE8;
	}

	#cadreDatesYUICalDiv
	{
		width:97%;
	}

	#cadreImpDatesDiv
	{
		float:left;
		height:200px;
		width:100%;
		background-color:#F9FCFF;
		overflow:auto;
	}
	#cadreImpEventsDiv
	{
		float:right;
		height:200px;
		width:100%;
		background-color:#F9FCFF;	
	}

	#cadreImpDatesHeadDiv, #cadreImpEventsHeadDiv, #cadreSMSHeadDiv, #cadreGroupsHeadDiv
	{
		padding:5px;
		font-weight:bold;
		background-color:#839DB7;
		height:20px;
	}

	#cadreImpDatesBodyDiv, #cadreImpEventsBodyDiv
	{
		height:170px;
		overflow:auto;		
	}
	
	#cadreSMSMainDiv
	{
		height:38%;		
		margin-top:5px;
		background-color:#F9FCFF;		
		border:2px solid #95A6B7;
	}
	#cadreGroupsMainDiv
	{
		height:100%;
		background-color:#F9FCFF;		
		margin-bottom:5px;
		border:2px solid #95A6B7;
	}

	
	#cadreSMSBodyDiv
	{
		height:65px;
	}
	#cadreSMSFooterDiv
	{
		padding:5px;
		text-align:right;		
	}
	
	.yui-skin-sam .yui-calendar
	{
		width:100%;
	}
	#cadreEventsCalMainDiv .yui-calcontainer 
	{
		float:none;
	}
	
	.yui-skin-sam .yui-panel .bd, .yui-skin-sam .yui-panel .ft
	{
		background-color:#FFFFFF;
	}
	.cadreInfoDiv
	{
		padding:5px;
		font-size:12px;
	}

	#regionLevelCadreDiv
	{		
		border:2px solid #95A6B7;
		height:58%;
	}
	#cadreStrengthAreasDiv
	{
		text-align:right;
		margin-right:10px;
	}
	#regionLevelCadreDivHead
	{
		background-color:#839DB7;
		padding:8px;
		font-weight:bold;
		text-align:center;
	}
	#regionLevelCadreDivBody
	{
		height:130px;
	}
	#sendSMSAnc
	{
		background-color:#80B4E8;
		color:#244565;
		padding:5px;
		text-decoration:none;
	}
	#newEventSpan
	{
		float:right;
		margin-right:5px;

	}
	#cadreEventsDetailsDivMain
	{
		margin-right:10px;
		text-align:right;
		padding:7px;
	}
	.impInfoSpan
	{
		font-size:12px;
		padding:5px;
	}

	.yui-skin-sam .yui-calendar td.calcell.highlight1 
	{
		background-color:#39e7de;
	}

	.tinyDateCal
	{
		position:absolute;
	}
</style>

<script type="text/javascript">
	var smsDialog, newEventDialog, newDateDialog, mainEventCalendar,dateCalendar ;
	var cadreObj={
					regionCadres:[]
				 };

	function buildLayout()
	{
		var layoutEl = new YAHOO.widget.Layout('cadreManagementMainDiv', { 
			units: [ 	        
					{ 
						position: 'right',
						width: 300,	
						height:520,
						header: 'Cadre Teams',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,
						body: 'cadreTeamsMainDiv',
						animate: true
					},
					{ 
						position: 'bottom', 
						height: 520,
						header: 'Cadre Events', 
						body: 'cadreEventsCalMainDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true }, 
					{
						position: 'left',
						width: 270,
						height:520,
						header: 'Associate Groups',
						body: 'cadreSMSGroupsMainDiv',
						resize: false, 
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true  
					}, 
					{ 
						position: 'center',
						header: 'Cadre Details',
						body: 'cadreDetailsMainDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true
					} 
	    ] 
		}); 
		layoutEl.render(); 
	}

	function buildAssociateGroupsAccrodian()
	{

	}
	function buildCadreTeamsAccrodian()
	{
		//----------------------
		 YUI().use( 'gallery-accordion', function(Y) {
		
		var accordion = new Y.Accordion( {
		contentBox: "#cadreTeamsAccordianDiv",
		useAnimation: true,
		collapseOthersOnExpand: true
		});
	 
		accordion.render();

		var item1, item2, item3;
		 
		item1 = new Y.AccordionItem( 
		{
			label: " Campaign Teams",
			expanded: true,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 250
			},
			closable: false
		} );
	 		
		item1.set( "bodyContent","campaign Teams");

		accordion.addItem( item1 );


		item2 = new Y.AccordionItem( 
		{
			label: " Booth Level Teams",
			expanded: false,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 250
			},
			closable: false
		} );
	 		
		item2.set( "bodyContent","boothLevel Teams");

		accordion.addItem( item2 );


		item3 = new Y.AccordionItem( 
		{
			label: " Feed Back Teams",
			expanded: false,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 250
			},
			closable: false
		} );
	 		
		item3.set( "bodyContent","Feed Back Teams");

		accordion.addItem( item3 );
		 });
	}
	
	function buildSMSPopup()
	{
		smsDialog = new YAHOO.widget.Dialog("myDialog",
				{ width : "600px", 
	              fixedcenter : true, 
	              visible : false,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true	              
	             } ); 
		smsDialog.render(); 
		
	}

	function showSendSMSPopup()
	{
		smsDialog.show(); 
	}
	
	
	//----------

	function callAjax(jsObj,url)
	{			
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);
							
								if(jsObj.task == "getUserLocation")
									fillDataOptions(myResults);	
								else if(jsObj.task == "fillSelectElements")
									fillSelectElement(myResults,jsObj);
								else if(jsObj.task == "sendSMS")
									displaySuccessMessage(myResults,jsObj);
								else if(jsObj.task=="CADRE_LEVEL")
									fillDataForCadreLevel(myResults);
								else if(jsObj.task=="createEvent")
									addCreatedEvent(myResults,jsObj);


									
							}catch (e) {   
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

	function displaySuccessMessage(results,jsObj)
	{
		var divElmt = document.getElementById("successDiv");

		var str="SMS sent successfully to "+results+" cadres";
		divElmt.innerHTML=str;
	}
	function fillSelectElement(results,jsObj)
	{
		if(jsObj.type == "STATE")
		{
			var elmt = document.getElementById("DistrictSelect");
		}
		else if(jsObj.type == "DISTRICT	")
		{
			var elmt = document.getElementById("ConstituencySelect");
		}
		else if(jsObj.type == "CONSTITUENCY")
		{	
			var elmt = document.getElementById("MandalSelect");
		}	
		else if(jsObj.type == "MANDAL")
		{
			var elmt = document.getElementById("VillageSelect");
		}

		var len=elmt.length;			
		for(i=len-1;i>=0;i--)		
			elmt.remove(i);
		
		var x=document.createElement('option');		
		x.value="0";		
		x.text="Select";		

		try
		{
			elmt.add(x,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(x); // IE only
		}
		
		for (var l in results)
		{
			var y=document.createElement('option');
			y.value=results[l].id;
			y.text=results[l].name;
			
			try
			{
				elmt.add(y,null); // standards compliant
			}
			catch(ex)
			{
				elmt.add(y); // IE only
			}
		}
		
	}
	function getUserLocationData(val)
	{
		var jsObj={
					value:val,
					task:"getUserLocation"
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/cadreSMSLocationWiseData.action?";
		callAjax(jsObj,url);
	}


	function getUsersCadreLevelData(){		
		var jsObj={
				task:"CADRE_LEVEL"
			  };
		var url = "<%=request.getContextPath()%>/usersCadreLevelData.action";
		callAjax(jsObj,url);
	}
	
	function getNextRegions(id,val)
	{
		var selectElmt = document.getElementById(id);
		var selectValue = selectElmt.options[selectElmt.selectedIndex].value;
		
		if(selectValue=="0")
			return;

		var jsObj={
					value:selectValue,
					type:val,
					task:"fillSelectElements"
				  };
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?REGION="+val+"&REGION_ID="+selectValue;
		callAjax(jsObj,url);

	}

	function fillDataForCadreLevel(results)
	{
		var successDivElmt=	 document.getElementById("successDiv");
		successDivElmt.innerHTML="";

		var regionTypeElmtLabel = document.getElementById("region_type_Label");
		var regionTypeElmtData = document.getElementById("region_type_Data");

		regionTypeElmtLabel.innerHTML="Select Cadre Level";

		var str='';
		for(var i in results)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results[i].id+'"> '+results[i].name+' ';
		}
		regionTypeElmtData.innerHTML=str;
		//--------------
		var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
		var regionTypeSelectElmtData = document.getElementById("region_select_Data");

		if(regionTypeSelectElmtLabel && regionTypeSelectElmtData)
		{
			regionTypeSelectElmtLabel.innerHTML="";
			regionTypeSelectElmtData.innerHTML="";
		}
		//-------------
		var smsTextElmtLabel = document.getElementById("sms_text_Label");
		var smsTextElmtData = document.getElementById("sms_text_Data");
		
		if(smsTextElmtLabel)
			smsTextElmtLabel.innerHTML="SMS Text";
		
		var smsStr='';
		smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
		smsStr+='<div id="limitDiv">';
		smsStr+='<table style="width:100%;"><tr>';
		smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
		smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
		smsStr+='</tr></table>';
		smsStr+='</div>';	
			
		
		if(smsTextElmtData)
			smsTextElmtData.innerHTML=smsStr;


		var buttonDiv =  document.getElementById("button_div");
		var bstr='';
		bstr+='<input type="button" value="Send SMS" onclick="sendSMSCadreLevel()"/>';
		
		if(buttonDiv)
			buttonDiv.innerHTML=bstr;
		
	}
	function fillDataOptions(results)
	{	
		
		
		//Setting values for region type..
		var regionTypeElmtLabel = document.getElementById("region_type_Label");
		var regionTypeElmtData = document.getElementById("region_type_Data");

		var str='';
		for(var i in results.regions)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'" onclick="displayRegionsSelect(this.value)" /> '+results.regions[i].name+'';
		}		

		if(regionTypeElmtLabel)
			regionTypeElmtLabel.innerHTML="Select Level";
		if(regionTypeElmtData)
			regionTypeElmtData.innerHTML=str;

		//---------
		//Setting values for select box..
		var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
		var regionTypeSelectElmtData = document.getElementById("region_select_Data");

		if(regionTypeSelectElmtLabel)
			regionTypeSelectElmtLabel.innerHTML="Select Location";

		var regionStr='';
		
		regionStr+='<select id="StateSelect" class="selectBox" onchange="getNextRegions(this.id,\'STATE\')" disabled="true">';
		if(results.states != "")
		{
			for(var state in results.states)
			{
				regionStr+='<option value="'+results.states[state].id+'">'+results.states[state].name+'</option>';
			}
		}
		else
		{
			regionStr+='<option value="0"> Select State</option>';
		}
		regionStr+='</select>';	

		
		regionStr+='<select id="DistrictSelect" class="selectBox" onchange="getNextRegions(this.id,\'DISTRICT\')" disabled="true">';
		if(results.districts != "")
		{
			for(var district in results.districts)
			{
				regionStr+='<option value="'+results.districts[district].id+'">'+results.districts[district].name+'</option>';
			}
		}
		else
		{
			regionStr+='<option value="0"> Select District</option>';
		}
		regionStr+='</select>';
		
		
		regionStr+='<select id="ConstituencySelect" class="selectBox" onchange="getNextRegions(this.id,\'CONSTITUENCY\')" disabled="true">';
		if(results.constituencies != "")
		{
			for(var consti in results.constituencies)
			{
				regionStr+='<option value="'+results.constituencies[consti].id+'">'+results.constituencies[consti].name+'</option>';
			}
		}
		else
		{
			regionStr+='<option value="0"> Select Constituency</option>';
		}
		regionStr+='</select>';
	
	
	
		regionStr+='<select id="MandalSelect" class="selectBox" onchange="getNextRegions(this.id,\'MANDAL\')" disabled="true">';
		if(results.mandals != "")
		{
			for(var mandal in results.mandals)
			{
				regionStr+='<option value="'+results.mandals[mandal].id+'">'+results.mandals[mandal].name+'</option>';
			}
		}
		else
		{	
			regionStr+='<option value="0"> Select Mandal</option>';
		}
		regionStr+='</select>';

		
		regionStr+='<select id="VillageSelect" class="selectBox" disabled="true">';
		if(results.villages != "")
		{
			for(var village in results.villages)
			{
				regionStr+='<option value="'+results.villages[village].id+'">'+results.villages[village].name+'</option>';
			}
		}
		else
		{	
			regionStr+='<option value="0"> Select Village</option>';
		}
		regionStr+='</select>';
	
		if(regionTypeSelectElmtData)
			regionTypeSelectElmtData.innerHTML=regionStr;

		//---------------
		// Displaying Text Area
		var smsTextElmtLabel = document.getElementById("sms_text_Label");
		var smsTextElmtData = document.getElementById("sms_text_Data");
		
		if(smsTextElmtLabel)
			smsTextElmtLabel.innerHTML="SMS Text";

		var smsStr='';
		smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
		smsStr+='<div id="limitDiv">';
		smsStr+='<table style="width:100%;"><tr>';
		smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
		smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
		smsStr+='</tr></table>';
		smsStr+='</div>';	
		
		if(smsTextElmtData)
			smsTextElmtData.innerHTML=smsStr;

		//--------------------------
		//Displaying button

		var buttonDiv = document.getElementById("button_div");
		var bstr='';
		bstr+='<input type="button" value="Send SMS" onclick="sendSMS()"/>';
		if(buttonDiv)
		{
			buttonDiv.innerHTML=bstr;
		}

	
	}
	
	function limitText(limitField, limitCount, limitNum)
	{		
		var limitFieldElmt = document.getElementById(limitField);
		var limitCountElmt = document.getElementById(limitCount);

		if (limitFieldElmt.value.length > limitNum) 
		{
			limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
		}
		else
		{			
			limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
		}
	}

	function sendSMSCadreLevel(){
		for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
		{
			if( document.smsForm.region_type_radio[i].checked == true ){
				val = document.smsForm.region_type_radio[i].value;
				
			}
		}
		
		var textAreaElmt = document.getElementById("smsTextArea");

		textAreaElmtValue = textAreaElmt.value
		val=val.toUpperCase();
		var jsObj={
					SMS_LEVEL_TYPE:'CADRE_LEVEL',
					SMS_LEVEL_VALUE:val,
					SMS_MESSAGE:textAreaElmtValue,
					task:"sendSMS"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
		callAjax(jsObj,url);
	}
	function sendSMS()
	{
		for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
		{
			if( document.smsForm.region_type_radio[i].checked == true )
				val = document.smsForm.region_type_radio[i].value;
		}
		
		var valSelect = document.getElementById(val+"Select");
		var textAreaElmt = document.getElementById("smsTextArea");

		textAreaElmtValue = textAreaElmt.value
		var valSelectValue = valSelect.options[valSelect.selectedIndex].value;
		val=val.toUpperCase();
		var jsObj={
					SMS_LEVEL_TYPE:val,
					SMS_LEVEL_VALUE:valSelectValue,
					SMS_MESSAGE:textAreaElmtValue,
					task:"sendSMS"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
		callAjax(jsObj,url);
		
	}
	
	function displayRegionsSelect(val)
	{
		var stateSelectElmt = document.getElementById("StateSelect");
		var districtSelectElmt = document.getElementById("DistrictSelect");
		var constituencySelectElmt = document.getElementById("ConstituencySelect");
		var mandalSelectElmt = document.getElementById("MandalSelect");
		var villageSelectElmt = document.getElementById("VillageSelect");
		
		stateSelectElmt.disabled=true;
		districtSelectElmt.disabled=true;	
		constituencySelectElmt.disabled=true;	
		mandalSelectElmt.disabled=true;	
		villageSelectElmt.disabled=true;	

		if(val == "District")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;			
		}
		if(val == "Constituency")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;	
			constituencySelectElmt.disabled=false;	
		}
		if(val == "Mandal")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;
			constituencySelectElmt.disabled=false;	
			mandalSelectElmt.disabled=false;	
		}
		if(val == "Village")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;	
			constituencySelectElmt.disabled=false;	
			mandalSelectElmt.disabled=false;	
			villageSelectElmt.disabled=false;	
		}		
	}

	function showRegionLevelCadres(arr)
	{
		cadreObj.regionCadres=arr;
		var elmt = document.getElementById("regionLevelCadreDivBody");
		if(!elmt)
			alert("No Div Element present for displaying cadre details");		
		elmt.innerHTML='';

		for(var i in cadreObj.regionCadres)
		{
			var child = document.createElement('div');
			child.setAttribute('id',cadreObj.regionCadres[i].val+"_"+cadreObj.regionCadres[i].id+"_div");
			child.setAttribute('class','cadreInfoDiv');
			if(i%2!=0)
				child.setAttribute('style','background-color:#EBF5FF;');

			var str='';
			str+='<table>';
			str+='<tr>';
			str+='<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/></td>'
			str+='<td>'+cadreObj.regionCadres[i].val+' level cadres - '+cadreObj.regionCadres[i].id+'</td>';
			str+='</tr>';
			str+='</table>';


			child.innerHTML=str;

			elmt.appendChild(child);
		}
	}


	function dateToLocaleString(dt, cal)
	{ 
		 
		var dStr = dt.getDate(); 
		var mStr = dt.getMonth(); 
		var yStr = dt.getFullYear(); 
		return (dStr + "/" + mStr + "/" + yStr); 
	} 
	 
	function mySelectHandler(type,args,obj)
	{ 
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 

		var calDateResult = dateToLocaleString(selDate, this);

		var divElmt = document.createElement('div');
		divElmt.setAttribute('id',calDateResult);
		divElmt.innerHTML="SELECTED: " + calDateResult;
	
		var elmt = document.getElementById("cadreImpEventsBodyDiv");
		if(elmt)
		{
			//elmt.appendChild(divElmt);
		}
	     
	};
	
	function displayDateText(type,args,obj)
	{
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 

		var calDateResult = dateToLocaleString(selDate, this);

		var elmt = document.getElementById("startDateText");
		if(elmt)
		{
			elmt.value = calDateResult;
		}
	}
	
	function addCreatedEvent(results,jsObj)
	{
		var divElmt = document.createElement('div');
		divElmt.setAttribute('id',results.startDate);
		divElmt.setAttribute('class','eventSummaryDiv');

		var str='';
		str+='<table>';
		str+='<tr>';
		str+='<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/></td>';
		str+='<td>'+results.title+'</td>';
		str+='<tr>';
		str+='</table>';
		divElmt.innerHTML=str;

		var elmt = document.getElementById("cadreImpEventsBodyDiv");
		if(elmt)
		{
			elmt.appendChild(divElmt);
		}

		newEventDialog.cancel();

		mainEventCalendar.addRenderer("12/19", mainEventCalendar.renderCellStyleHighlight1); 
		mainEventCalendar.render(); 
	}
	function buildCalendarControl()
	{
		var navConfig = { 
	      strings : { 
	          month: "Choose Month", 
	          year: "Enter Year", 
	          submit: "OK", 
	          cancel: "Cancel", 
	          invalidYear: "Please enter a valid year" 
	      }, 
	      monthFormat: YAHOO.widget.Calendar.SHORT, 
	      initialFocus: "year" 
	}; 

		mainEventCalendar = new YAHOO.widget.Calendar("cadreDatesYUICalDiv", {navigator:navConfig}); 
		mainEventCalendar.selectEvent.subscribe(mySelectHandler, mainEventCalendar, true); 
		mainEventCalendar.render(); 
	}

	function showDateCal(id)
	{
		if(dateCalendar)
			dateCalendar.destroy();

		var navConfig = { 
	      strings : { 
	          month: "Choose Month", 
	          year: "Enter Year", 
	          submit: "OK", 
	          cancel: "Cancel", 
	          invalidYear: "Please enter a valid year" 
	      }, 
	      monthFormat: YAHOO.widget.Calendar.SHORT, 
	      initialFocus: "year" 
	}; 

		dateCalendar = new YAHOO.widget.Calendar(id, {navigator:navConfig}); 
		dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true); 		
		dateCalendar.render(); 
		
	}
	function buildNewEventPopup()
	{
		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getFullYear()+"/"+(new Date().getMonth()+1)+"/"+new Date().getDate();

				
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newEventDiv');
		divChild.setAttribute('class','yui-skin-sam');
		
		var eventStr='';
		eventStr+='<div class="hd">New Event</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<table>';
		eventStr+='<tr>';
		eventStr+='<th>Event Name</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="eventNameText" name="eventNameText"/></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Date</th>';
		eventStr+='<td>';
		eventStr+='<input type="text" id="startDateText" name="startDateText" onfocus="showDateCal(\'startCalDiv\')"/>';
		eventStr+='<div id="startCalDiv" class="tinyDateCal"></div>';
		eventStr+='</td>';
		eventStr+='<th>End Date</th>';
		eventStr+='<td><input type="text" id="endDateText" name="endDateText" onfocus="showDateCal(\'endCalDiv\')"/><div id="endCalDiv" class="tinyDateCal"></div></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Time</th>';
		eventStr+='<td><input type="text" id="startTimeText" name="startTimeText"/></td>';
		eventStr+='<th>End Time</th>';
		eventStr+='<td><input type="text" id="endTimeText" name="endTimeText"/></td>';
		eventStr+='</tr>';
	
		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="descTextArea" name="descTextArea"></textarea></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Organisers</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="organisersText" name="organisersText"/></td>';
		eventStr+='</tr>';
		
		eventStr+='<tr>';
		eventStr+='<th>Action plans</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="actionPlansText" name="actionPlansText"/></td>';
		eventStr+='</tr>';

		eventStr+='</table>';
		eventStr+='</div>';

		divChild.innerHTML=eventStr;
		elmt.appendChild(divChild);

		newEventDialog = new YAHOO.widget.Dialog("newEventDiv",
				{ width : "600px", 
	              fixedcenter : true, 
	              visible : false,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
		          buttons : [ { text:"Create Event", handler:handleSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleCancel } ]
	             } ); 
		newEventDialog.render(); 
	}

	function showNewEventPopup()
	{
		newEventDialog.show();
	}

	function handleSubmit()
	{
		var eventNameVal = document.getElementById("eventNameText").value;
		var startDateVal = document.getElementById("startDateText").value;
		var endDateVal = document.getElementById("endDateText").value;
		var startTimeVal = document.getElementById("startTimeText").value;
		var endTimeVal = document.getElementById("endTimeText").value;
		var descVal = document.getElementById("descTextArea").value;
		var organisersVal = document.getElementById("organisersText").value;
		var actionPlansVal = document.getElementById("actionPlansText").value;
		
		var jsObj={
					eventName:eventNameVal,
					startDate:startDateVal,
					endDate:endDateVal,
					startTime:startTimeVal,
					endTime:endTimeVal,
					desc:descVal,
					organisers:organisersVal,
					actionPlans:actionPlansVal,
					task:"createEvent"
				  }
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		
		callAjax(jsObj,url);
	}

	function handleCancel()
	{
		this.cancel();
	}	

	function buildNewImpDatePopup()
	{
		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getFullYear()+"/"+(new Date().getMonth()+1)+"/"+new Date().getDate();

				
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newImpDateDiv');
		divChild.setAttribute('class','yui-skin-sam');
		
		var eventStr='';
		eventStr+='<div class="hd">New Date</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<table>';
		eventStr+='<tr>';
		eventStr+='<th>Event Name</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="eventNameText" name="eventNameText"/></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Date</th>';
		eventStr+='<td><input type="text" id="startDateText" name="startDateText"/></td>';
		eventStr+='<th>End Date</th>';
		eventStr+='<td><input type="text" id="endDateText" name="endDateText"/></td>';
		eventStr+='</tr>';	
	
		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="descTextArea" name="descTextArea"></textarea></td>';
		eventStr+='</tr>';		

		eventStr+='</table>';
		eventStr+='</div>';

		divChild.innerHTML=eventStr;
		elmt.appendChild(divChild);

		newDateDialog = new YAHOO.widget.Dialog("newImpDateDiv",
				{ width : "600px", 
	              fixedcenter : true, 
	              visible : false,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
		          buttons : [ { text:"Create Event", handler:handleImpDateSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleImpDateCancel } ]
	             } ); 
		newDateDialog .render(); 
	}

	function showNewImpDatePopup()
	{
		newDateDialog.show();
	}

	function handleImpDateSubmit()
	{

	}
	
	function handleImpDateCancel()
	{

	}
</script>
</head>
<body>
	
	<div id="CadreManagementHeaderMain">
		Cadre Management
	</div>
	<div id="cadreManagementMainDiv" class="yui-skin-sam">		
		<div id="myDialog" class="yui-skin-sam"> 
			<div class="hd">Cadre SMS Page</div> 
			<div class="bd"> 
				 <s:form action="cadreRegisterAction" method="POST" theme="simple" name="smsForm">
					<table>
						<tr>
							<th align="left">SMS Type</th>
							<td align="left">
								<input type="radio" name="sms_type" value="locationWise" onclick="getUserLocationData(this.value)"/> Location Wise
								<!-- <td><input type="radio" name="sms_type" onselect="getUsersGroupData()"/> Group Wise</td>-->
								<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value)"/> Cadre Level Wise
							</td>		
						</tr>
						<tr>
							<th align="left"><div id="region_type_Label"></div></th>
							<td align="left"><div id="region_type_Data"></div></td>				
						</tr>
						<tr>
							<th align="left">
								<div id="region_select_Label">
								</div>
							</th>
							<td align="left">
								<div id="region_select_Data">
								</div>					
							</td>
						</tr>
						<tr>
							<th align="left"><div id="sms_text_Label"></div></th>
							<td align="left"><div id="sms_text_Data"></div></td>				
						</tr>
						<tr>
							<td align="center" colspan="2"><div id="button_div"></div></td>				
						</tr>
						<tr>
							<td align="left" colspan="2">
								<div id="successDiv" ></div>
							</td>
						</tr>
					</table>
				  </s:form>
			</div> 
		</div> 
	</div>
	
	

	<div id="cadreSMSGroupsMainDiv">
		
		<div id="cadreGroupsMainDiv">
			<div id="cadreGroupsHeadDiv">Associate Groups</div>
			<div id="cadreGroupsBodyDiv" style="height: 130px;">Associate groups</div>
		</div>
	</div>
	<div id="cadreDetailsMainDiv">
		<div id="regionLevelCadreDiv">
			<div id="regionLevelCadreDivHead">Region Level Cadres</div>
			<div id="regionLevelCadreDivBody"></div>
			<div id="cadreStrengthAreasDiv" class="cadreInfoDiv"><a href="cadreReportAction.action">Know your Cadre Strength areas</a></div>
		</div>		
		<div id="cadreSMSMainDiv">
			<div id="cadreSMSHeadDiv">Cadre SMS</div>
			<div id="cadreSMSBodyDiv">Cadre SMS feature enables the user to send SMS to the cadres based on the location and cadre level</div>
			<div id="cadreSMSFooterDiv">				
				<a href="javascript:{}" id="sendSMSAnc" onclick="showSendSMSPopup()">Send SMS</a>				
				<!--<a href="cadreSMSAction.action" id="sendSMSAnc">Send SMS</a>-->
			</div>
		</div>
	</div>		
	<div id="cadreTeamsMainDiv">
		<div id="cadreTeamsAccordianDiv"></div>
	</div>
	<div id="cadreEventsCalMainDiv">
		<div id="cadreDatesYUICalDiv">
		
		</div>
		<div id="cadreEventsDetailsDivMain">
			<span class="impInfoSpan"> <img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/bluebox.png"/> - Only Important Dates </span>
			<span class="impInfoSpan"> <img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/lightbluebox.png"/> - Only Important Events</span>
			<span class="impInfoSpan"> <img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/brownbox.png"/> - Dates & Events</span>
		</div>
		<div id="cadreEventsDatesMain">
			<table width="100%">
				<tr>
					<td width="50%">
						<div id="cadreImpDatesDiv">
							<div id="cadreImpDatesHeadDiv">
								<span style="float: left;">Important Dates</span>
								<span id="newEventSpan"><a href="javascript:{}" onclick="showNewImpDatePopup()">Create New Event</a></span>							
							</div>
							<div id="cadreImpDatesBodyDiv"> Imp Dates Content </div>
						</div>
					</td>
					<td width="50%">
						<div id="cadreImpEventsDiv">
							<div id="cadreImpEventsHeadDiv">
								<span style="float: left;">Important Events</span>
								<span id="newEventSpan"><a href="javascript:{}" onclick="showNewEventPopup()">Create New Event</a></span>
							</div>
							<div id="cadreImpEventsBodyDiv"> 
								
								
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>		
	</div>
	
	

	<script type="text/javascript">
		buildLayout();
		buildAssociateGroupsAccrodian();
		buildCadreTeamsAccrodian();
		buildCalendarControl();	
		buildSMSPopup();
		buildNewEventPopup();
		buildNewImpDatePopup();
		
		var regionLevelCadres = new Array();
		<c:forEach var="pd1" items="${userCadresInfoVO.regionLevelCadres}" >				
				var ob =
					{
						id:'${pd1.value}',
						val:'${pd1.key}'
					};
					regionLevelCadres.push(ob);				
		</c:forEach>
		showRegionLevelCadres(regionLevelCadres);				
	</script>

	
</body>
</html>