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
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/resize/resize-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/layout/layout-min.js"></script> 
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
		font-size:20px;
		font-weight:bold;
		text-decoration:underline;
		margin-bottom:20px;
		font-family:Georgia;
	}
	#cadreManagementMainDiv
	{
		/*background-color:#EFEFEF;*/
		height:920px;
		margin-left:25px;
		margin-right:25px;
		text-align:left;
	}

	#cadreSMSGroupsMainDiv
	{
		height:97%;
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
		/*background-color:#CBDCE8;*/
	}

	#cadreDatesYUICalDiv
	{
		width:97%;
	}

	#cadreImpDatesDiv
	{
		float:left;
		height:200px;
		width:99%;
		background-color:#F9FCFF;
		overflow:auto;
		border:2px solid #839DB7;
	}
	#cadreImpEventsDiv
	{
		float:right;
		height:200px;
		width:99%;
		background-color:#F9FCFF;
		border:2px solid #839DB7;
	}

	#cadreImpDatesHeadDiv, #cadreImpEventsHeadDiv, #cadreSMSHeadDiv, #cadreGroupsHeadDiv
	{
		padding:5px;
		color:#1E4864;
		font-weight:bold;
		background-color:#839DB7;
		height:20px;
		background-image:url(images/icons/contHeading.png);
		background-repeat:repeat-x;
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
		color:#1E4864;
		padding:7px;
		font-weight:bold;
		text-align:left;
		background-image:url(images/icons/contHeading.png);
		background-repeat:repeat-x;
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
	.yui-skin-sam .yui-calendar td.calcell.highlight2 
	{
		background-color:#4b83e3;
	}

	.tinyDateCal
	{
		position:absolute;
	}
	.timeSelect
	{
		margin-right:5px;
		padding:2px;
	}
	#actionDetailsDiv
	{
		background-color:#EEF4FF;
	}

	.yui-layout-hd
	{
		color:#1E4864;
		font-size:12px;
		text-decoration:underline;
	}
	.yui-skin-sam .yui-accordion-item-label
	{
		color:#1E4864;
	}

	.yui-accordion-item .yui-widget-bd 
	{
		background-color:#FFFFFF;
	}

	.yui-layout .yui-layout-unit div.yui-layout-hd h2 
	{
		font-family:georgia;
	}
	
	.yui-skin-sam .yui-calendar td.calcell.today 
	{
		background-color:none;
	}
	.yui-skin-sam .yui-calendar td.calcell.today a 
	{
		background-color:none;
	}
	.yui-skin-sam .yui-calendar td.calcellhover a
	{
		background-color:none;
	}
	
</style>

<script type="text/javascript">
	var smsDialog, newEventDialog, newDateDialog, mainEventCalendar,dateCalendar ;
	var cadreObj={
					regionCadres:[]
				 };
	var actionPlanArray = new Array();
	var renderDatesArr = new Array();

	var ImpDatesArray = new Array();
	var ImpEventsArray = new Array();

	function buildLayout()
	{
		var layoutEl = new YAHOO.widget.Layout('cadreManagementMainDiv', { 
			units: [ 	        
					{ 
						position: 'right',
						header:false,
						width: 300,	
						height:520,						
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: true,
						body: 'cadreTeamsMainDiv',
						animate: true
					},
					{ 
						position: 'bottom', 
						height: 520,
						header: 'Important Dates And Event Management', 
						body: 'cadreEventsCalMainDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true }, 
					{
						position: 'left',
						header:false,
						width: 270,
						height:520,						
						body: 'cadreSMSGroupsMainDiv',
						resize: false, 
						gutter: '5px',
						collapse: false,
						scroll: true,						
						animate: true  
					}, 
					{ 
						position: 'center',						
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
				height: 305
			},
			closable: false
		} );
	 		
		item1.set( "bodyContent","Campaign Teams");

		accordion.addItem( item1 );


		item2 = new Y.AccordionItem( 
		{
			label: " Booth Level Teams",
			expanded: false,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 305
			},
			closable: false
		} );
	 		
		item2.set( "bodyContent","Booth Level Teams");

		accordion.addItem( item2 );


		item3 = new Y.AccordionItem( 
		{
			label: " Feed Back Teams",
			expanded: false,
			contentBox: "dynamicContentBox1",
			contentHeight: 
			{
				method: "fixed",
				height: 305
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
								else if(jsObj.task=="createImpDateEvent")
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
		console.log(arr);
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
			str+='<td>'+cadreObj.regionCadres[i].val+' Level Cadres - '+cadreObj.regionCadres[i].id+'</td>';
			str+='</tr>';
			str+='</table>';


			child.innerHTML=str;

			elmt.appendChild(child);
		}
	}


	function dateToLocaleString(dt, cal)
	{ 
		 
		var dStr = dt.getDate(); 
		var mStr = dt.getMonth()+1; 
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
		var divId = obj.containerId;		
		var subStr = divId.substring(0,divId.indexOf('_'));		
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 

		var calDateResult = dateToLocaleString(selDate, this);
		
		var divElmt = document.getElementById(divId);
		var elmt = document.getElementById(subStr);
		if(elmt)
		{
			elmt.value = calDateResult;
		}

		divElmt.style.display='none';

	}
	

	function demo()
	{
		
	}
	function addCreatedEvent(results,jsObj)
	{
		
		var divElmt = document.createElement('div');
		divElmt.setAttribute('id',results.startDate);
		divElmt.setAttribute('class','eventSummaryDiv');
		
				
		var str='';
		str+='<table>';
		str+='<tr>';
		str+='<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png" onclick="demo()"/></td>';
		str+='<td>'+results.title+'</td>';
		if(results.startDate)
		{
			str+='<td> - </td>';
			str+='<td>'+results.startDate+'</td>';
		}
		if(results.endDate)
		{
			str+='<td> - </td>';
			str+='<td>'+results.endDate+'</td>';
		}
		str+='<tr>';
		str+='</table>';
		divElmt.innerHTML=str;
				
		if(jsObj.task == "createEvent")
			var elmt = document.getElementById("cadreImpEventsBodyDiv");
		else if(jsObj.task == "createImpDateEvent")
			var elmt = document.getElementById("cadreImpDatesBodyDiv");

		if(elmt)
		{
			elmt.appendChild(divElmt);
		}
		
		if(jsObj.task == "createEvent")
			newEventDialog.cancel();
		else if(jsObj.task == "createImpDateEvent")
			newDateDialog.cancel();	

		if(results.startDate)
		{			
			var index = results.startDate.indexOf(' ');
			var colIndex1 = results.startDate.indexOf(':');
			var colIndex2 = results.startDate.lastIndexOf(':');
			
			var StartDayStr = results.startDate.substring(0,2);		
			var StartMonStr = results.startDate.substring(3,5);
			var StartYearStr = results.startDate.substring(6,10);	
			var StartTimeHrs = results.startDate.substring(index,colIndex1);	
			var StartTimeMin = results.startDate.substring(colIndex1+1,colIndex2);	
		}
		if(results.endDate)
		{			
			var index = results.endDate.indexOf(' ');
			var colIndex1 = results.endDate.indexOf(':');
			var colIndex2 = results.endDate.lastIndexOf(':');
			
			var EndDayStr = results.endDate.substring(0,2);		
			var EndMonStr = results.endDate.substring(3,5);
			var EndYearStr = results.endDate.substring(6,10);	
			var EndTimeHrs = results.endDate.substring(index,colIndex1);	
			var EndTimeMin = results.endDate.substring(colIndex1+1,colIndex2);	
		}
		
		if(results.endDate)
		{			
			var renderValue=StartMonStr+"/"+StartDayStr+"/"+StartYearStr+"-"+EndMonStr+"/"+EndDayStr+"/"+EndYearStr;
		}
		else
		{			
			var renderValue=StartMonStr+"/"+StartDayStr+"/"+StartYearStr;
		}
		
		for(var i=EndYearStr;i>=StartYearStr;i--)
		{
			for(var j=EndMonStr;j>=StartMonStr;j--)
			{
				for(var k=EndDayStr;k>=StartDayStr;k--)
				{
					var renderValue
				}
			}
		}	
		var renderObj = {
							renderDate:renderValue,
							renderType:jsObj.task 
						};
				
		
		renderDatesArr.push(renderObj);
		console.log(renderDatesArr);
		renderStack();
		/*if(jsObj.task == "createEvent")
		{
			mainEventCalendar.addRenderer(renderDate, mainEventCalendar.renderCellStyleHighlight1);
		}
		else if(jsObj.task == "createImpDateEvent")
			mainEventCalendar.addRenderer(renderDate, mainEventCalendar.renderCellStyleHighlight2);

		
		mainEventCalendar.render(); */
		
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
		var date = (new Date().getMonth()+1)+"/"+new Date().getDate()+"/"+ new Date().getFullYear();
		
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

		dateCalendar = new YAHOO.widget.Calendar(id, {navigator:navConfig, mindate: date, title:"Choose a date:", close:true }); 
		dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true); 		
		dateCalendar.render(); 
		dateCalendar.show();		
	}
	function buildNewEventPopup()
	{

		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
				
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newEventDiv');
		divChild.setAttribute('class','yui-skin-sam');
		
		var eventStr='';
		eventStr+='<div class="hd">Enter New Event Details...</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<div id="eventDetailsDiv"><table>';
		eventStr+='<tr>';
		eventStr+='<th>Event Name</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="eventNameText" name="eventNameText"/></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Date</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="startDateText" name="startDateText" value="'+date+'" onfocus="showDateCal(\'startDateText_Div\')"/></div>';
		eventStr+='<div id="startDateText_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';
		eventStr+='<th>End Date</th>';
		eventStr+='<td><div><input type="text" id="endDateText" name="endDateText" value="'+date+'" onfocus="showDateCal(\'endDateText_Div\')"/></div>';
		eventStr+='<div id="endDateText_Div" class="tinyDateCal"></div></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Time</th>';
		eventStr+='<td>';
		eventStr+='<select id="startTimeHrs" name="startTimeText" class="timeSelect">';
		eventStr+='<option> Hrs </option>';
		for(var i=0;i<=23;i++)
		{
			eventStr+='<option>'+i+'</option>';
		}
		eventStr+='</select>';

		eventStr+='<select id="startTimeMin" name="startTimeText" class="timeSelect">';
		eventStr+='<option> Min </option>';
		eventStr+='<option>00</option>';		
		eventStr+='<option>15</option>';
		eventStr+='<option>30</option>';
		eventStr+='<option>45</option>';		
		eventStr+='</select>';
		eventStr+='</td>';
		eventStr+='<th>End Time</th>';
		eventStr+='<td>';
		eventStr+='<select id="endTimeHrs" name="endTimeText" class="timeSelect">';
		eventStr+='<option> Hrs </option>';
		for(var i=0;i<=23;i++)
		{
			eventStr+='<option>'+i+'</option>';
		}
		eventStr+='</select>';
		
		eventStr+='<select id="endTimeMin" name="startTimeText" class="timeSelect">';
		eventStr+='<option> Min </option>';
		eventStr+='<option>00</option>';		
		eventStr+='<option>15</option>';
		eventStr+='<option>30</option>';
		eventStr+='<option>45</option>';		
		eventStr+='</select>';

		eventStr+='</td>';
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
		eventStr+='<th><div id="actionPlanLabelDiv"></div></th>';
		eventStr+='<td colspan="3"><div id="actionPlanDataDiv"></div></td>';		
		eventStr+='</tr>';
		
		eventStr+='<tr>';
		eventStr+='<td colspan="4" align="right"><a href="javascript:{}" onclick="createActionPlan()"> Create Action Plan</a></td>';
		//eventStr+='<th>Action plans</th>';
		//eventStr+='<td colspan="3"><input type="text" size="50" id="actionPlansText" name="actionPlansText"/></td>';
		eventStr+='</tr>';

		eventStr+='</table>';
		eventStr+='<div id="actionDetailsDiv">';
		eventStr+='</div>';
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

	function createActionPlan()
	{
		var divElmt = document.getElementById('actionDetailsDiv');

		var str='';
		str+='<table>';
		str+='<tr>';
		str+='<th> Action Plan</th>';
		str+='<td><input type="text" id="actionPlanText" name="actionPlanText"/></td>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Target Date</th>';
		str+='<td>';
		str+='<div><input type="text" id="actionTargetDateText" name="actionTargetDateText" onfocus="showDateCal(\'actionTargetDateText_Div\')"/></div>';
		str+='<div id="actionTargetDateText_Div" class="tinyDateCal"></div>';
		str+='</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Organisers</th>';
		str+='<td><select id="actionOrganisersSelect"><option> Select Organisers</option></select</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td align="right"> <input type="button" value="Add" onclick="addActionPlanToEvent()"/></td>';
		str+='<td align="left"> <input type="button" value="cancel"/></td>';
		str+='</tr>';
		str+='</table>';

		if(divElmt)
			divElmt.innerHTML=str;
	}
	
	function addActionPlanToEvent()
	{
		var labelDivElmt = document.getElementById("actionPlanLabelDiv");
		var dataDivElmt = document.getElementById("actionPlanDataDiv");
		var actionDivElmt = document.getElementById("actionDetailsDiv");

		var actionPlanValue = document.getElementById('actionPlanText').value;
		var targetDateValue = document.getElementById('actionTargetDateText').value;
		var organisersSelectElmt = document.getElementById('actionOrganisersSelect');
		var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;

		var actionObj = {
							actionPlan:actionPlanValue,
							targetDate:targetDateValue,
							organisers:organisersValue			
						};
		actionPlanArray.push(actionObj);

		var divChild = document.createElement('div');
		divChild.setAttribute('id','newImpDateDiv');
		divChild.setAttribute('class','ImpDateDetailDiv');

		var divStr='';
		divStr+=actionPlanValue;
		divChild.innerHTML=divStr;

		if(labelDivElmt)
			labelDivElmt.innerHTML="Action Plan";
		if(dataDivElmt)
			dataDivElmt.appendChild(divChild);
		if(actionDivElmt)
			actionDivElmt.innerHTML="";

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
		
		var startTimeHrs = document.getElementById("startTimeHrs");
		var startTimeHrsVal = startTimeHrs.options[startTimeHrs.selectedIndex].text;
		var startTimeMin = document.getElementById("startTimeMin");
		var startTimeMinVal = startTimeMin.options[startTimeMin.selectedIndex].text;
		
		var endTimeHrs = document.getElementById("endTimeHrs");		
		var endTimeHrsVal = endTimeHrs.options[endTimeHrs.selectedIndex].text;
		var endTimeMin = document.getElementById("endTimeMin");
		var endTimeMinVal = endTimeMin.options[endTimeMin.selectedIndex].text;

		var descVal = document.getElementById("descTextArea").value;
		var organisersVal = document.getElementById("organisersText").value;

		var actionPlansVal='';
		
		var jsObj={
					eventName:eventNameVal,
					startDate:startDateVal,
					endDate:endDateVal,
					startTimeHrs:startTimeHrsVal,
					startTimeMin:startTimeMinVal,					
					endTimeHrs:endTimeHrsVal,
					endTimeMin:endTimeMinVal,
					desc:descVal,
					organisers:organisersVal,
					actionPlans:actionPlanArray,
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
		eventStr+='<td colspan="3"><input type="text" size="50" id="ImpeventNameText" name="ImpeventNameText"/></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Date</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="ImpStartDateText" name="ImpStartDateText" onfocus="showDateCal(\'ImpStartDateText_Div\')"/></div>';
		eventStr+='<div id="ImpStartDateText_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';
		eventStr+='<th>End Date</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="ImpEndDateText" name="ImpEndDateText" disabled="true" onfocus="showDateCal(\'ImpEndDateText_Div\')"/></div>';
		eventStr+='<div id="ImpEndDateText_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';
		eventStr+='</tr>';
	
		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="ImpdescTextArea" name="ImpdescTextArea"></textarea></td>';
		eventStr+='</tr>';		

		eventStr+='<tr>';
		eventStr+='<th>Repeat Frequency</th>';
		eventStr+='<td colspan="3">';
		eventStr+='<select id="repeatFreqSelect" class="timeSelect" onchange="showEndDateText(this.options[this.selectedIndex].text)">';
		eventStr+='<option>Does Not Repeat</option>';
		eventStr+='<option>Yearly</option><option>Monthly</option><option>Weekly</option></select></td>';
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
	
	function showEndDateText(val)
	{
		var txtElmt = document.getElementById('ImpEndDateText');
		if(val == "Does Not Repeat")
		{
			txtElmt.disabled=true;
		}
		else
			txtElmt.disabled=false;
	}

	function showNewImpDatePopup()
	{
		newDateDialog.show();
	}

	function handleImpDateSubmit()
	{
		var ImpeventNameVal = document.getElementById("ImpeventNameText").value;
		var ImpstartDateVal = document.getElementById("ImpStartDateText").value;		
		var ImpendDateVal = document.getElementById("ImpEndDateText").value;		
		var ImpDescVal = document.getElementById("ImpdescTextArea").value;

		var repeatFreqElmt = document.getElementById("repeatFreqSelect");
		repeatFreqVal =  repeatFreqElmt.options[repeatFreqElmt.selectedIndex].value;

		if(repeatFreqVal = "Does Not Repeat")
			ImpendDateVal = ImpstartDateVal;

		var jsObj={
					eventName:ImpeventNameVal,
					startDate:ImpstartDateVal,	
					endDate:ImpendDateVal,
					desc:ImpDescVal,
					frequency:repeatFreqVal,
					task:"createImpDateEvent"
				  }
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		
		callAjax(jsObj,url);
	}
	
	function handleImpDateCancel()
	{
		this.cancel();
	}

	function showInitialImpEventsAndDates(eventsarr,type)
	{
		if(type == "impEvents")
			var elmt = document.getElementById("cadreImpEventsBodyDiv");
		else if(type == "impDates")
			var elmt = document.getElementById("cadreImpDatesBodyDiv");

		for(var i in eventsarr)
		{
			if(eventsarr[i].startDate)
			{			
				var index = eventsarr[i].startDate.indexOf(' ');
				var colIndex1 = eventsarr[i].startDate.indexOf(':');
				var colIndex2 = eventsarr[i].startDate.lastIndexOf(':');
				
				var StartDayStr = eventsarr[i].startDate.substring(0,2);		
				var StartMonStr = eventsarr[i].startDate.substring(3,5);
				var StartYearStr = eventsarr[i].startDate.substring(6,10);	
				var StartTimeHrs = eventsarr[i].startDate.substring(index,colIndex1);	
				var StartTimeMin = eventsarr[i].startDate.substring(colIndex1+1,colIndex2);	

			}
			if(eventsarr[i].endDate)
			{			
				var index = eventsarr[i].endDate.indexOf(' ');
				var colIndex1 = eventsarr[i].endDate.indexOf(':');
				var colIndex2 = eventsarr[i].endDate.lastIndexOf(':');
				
				var EndDayStr = eventsarr[i].endDate.substring(0,2);		
				var EndMonStr = eventsarr[i].endDate.substring(3,5);
				var EndYearStr = eventsarr[i].endDate.substring(6,10);	
				var EndTimeHrs = eventsarr[i].endDate.substring(index,colIndex1);	
				var EndTimeMin = eventsarr[i].endDate.substring(colIndex1+1,colIndex2);
			}

			/*if(type == "impEvents")
			{
				for(var j=EndDayStr;j>=StartDayStr;j--)
				{
						ImpEventsArray.push(j);
				}
			}
			else if(type == "impDates")
			{
				
				ImpEventsArray.push(StartDayStr);				
			}*/

			var divElmt = document.createElement('div');
			divElmt.setAttribute('id',eventsarr[i].title);
			divElmt.setAttribute('class','eventSummaryDiv');
			if(i%2!=0)
				divElmt.setAttribute('style','background-color:#EBF5FF;');

			var str='';
			str+='<table>';
			str+='<tr>';
			str+='<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/></td>';
			str+='<td>'+eventsarr[i].title+'</td>';	
			str+='<td> - </td>';	
			str+='<td>'+eventsarr[i].startDate+'</td>';

			if(eventsarr[i].endDate)
			{
				str+='<td> - </td>';	
				str+='<td>'+eventsarr[i].endDate+'</td>';
			}
			str+='<tr>';
			str+='</table>';
			divElmt.innerHTML=str;		
				
			if(elmt)
			{
				elmt.appendChild(divElmt);
			}
			
			if(eventsarr[i].endDate)
			{			
				var renderValue=StartMonStr+"/"+StartDayStr+"/"+StartYearStr+"-"+EndMonStr+"/"+EndDayStr+"/"+EndYearStr;
			}
			else
			{			
				var renderValue=StartMonStr+"/"+StartDayStr+"/"+StartYearStr;
			}
			
			var renderObj = {
								renderDate:renderValue,
								renderType:type
							}
			renderDatesArr.push(renderObj);


		/*	if(type == "impEvents")			
				mainEventCalendar.addRenderer(renderDate, mainEventCalendar.renderCellStyleHighlight1);			
			else if(type == "impDates")
				mainEventCalendar.addRenderer(renderDate, mainEventCalendar.renderCellStyleHighlight2); */
								
		}
	}

	function renderStack()
	{
		for(var i in renderDatesArr)
		{
			if(renderDatesArr[i].renderType == "impEvents" || renderDatesArr[i].renderType == "createEvent")			
				mainEventCalendar.addRenderer(renderDatesArr[i].renderDate, mainEventCalendar.renderCellStyleHighlight1);			
			else if(renderDatesArr[i].renderType == "impDates" || renderDatesArr[i].renderType == "createImpDateEvent")
				mainEventCalendar.addRenderer(renderDatesArr[i].renderDate, mainEventCalendar.renderCellStyleHighlight2); 
		}
		
		mainEventCalendar.render(); 
	/*		
			var day = new Array();
			day.push(2009);
			day.push(28);
			day.push(12);

			var renderArr = new Array('D',day,function(){});

			mainEventCalendar._renderStack.push(renderArr);

			
	*/	
		
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
		
		<div id="cadreGroupsMainDiv"  class="yui-skin-sam">
			<div id="cadreGroupsHeadDiv" class=".yui-widget-hd">Associate Groups</div>
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
			<div id="cadreSMSBodyDiv">Cadre SMS feature enables the user to send SMS to the cadres, based on the location and cadre level</div>
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
								<span id="newEventSpan"><a href="javascript:{}" onclick="showNewImpDatePopup()">Create New Date</a></span>							
							</div>
							<div id="cadreImpDatesBodyDiv"> </div>
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
		<c:forEach var="pd1" items="${cadreManagementVO.cadresByCadreLevel}" >				
				var ob =
					{
						id:'${pd1.value}',
						val:'${pd1.key}'
					};
					regionLevelCadres.push(ob);				
		</c:forEach>
		showRegionLevelCadres(regionLevelCadres);	
		
		var impEvents = new Array();
		<c:forEach var="impEvent" items="${cadreManagementVO.userEvents}" >			
				var ob =
					{
						title:'${impEvent.title}',
						startDate:'${impEvent.startDate}',
						endDate:'${impEvent.endDate}',
						description:'${impEvent.description}'
					};
					impEvents.push(ob);
		</c:forEach>
		
		showInitialImpEventsAndDates(impEvents,'impEvents');
		
		var impDates = new Array();
		<c:forEach var="impDate" items="${cadreManagementVO.userImpDates}" >			
				var ob =
					{
						title:'${impDate.title}',
						startDate:'${impDate.startDate}',
						importance:'${impDate.importance}',
						eventType:'${impDate.eventType}'
					};
					impDates.push(ob);
		</c:forEach>
		
		showInitialImpEventsAndDates(impDates,'impDates');
		renderStack();

	</script>

	
</body>
</html>