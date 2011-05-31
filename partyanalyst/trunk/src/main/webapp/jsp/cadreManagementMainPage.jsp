<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Management </title>

	
	
<!-- YUI Dependency files (Start) -->

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



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
<!-- YUI Dependency files (End) -->

<!-- Local script and css files (Start)-->
<script type="text/javascript" src="js/cadreManagement/cadreManagement.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<!--<script type="text/javascript" src="js/cadreManagement/cadreSMSPageJs.js"></script>
<script type="text/javascript" src="js/cadreManagement/cadreLocation.js"></script>-->

<link rel="stylesheet" type="text/css" href="styles/cadreManagement/cadreManagement.css">
<!-- Local script and css files (End)-->
<style>

#errorMsgDiv {
    
	color: #E92B2B;
    font-weight: bold;
			}
</style>
	

<script type="text/javascript">

	var smsDialog, newEventDialog, newDateDialog,eventDateDialog,mainEventCalendar,dateCalendar,cadreDataTable,cadreAnim,jsonStr;
	var selectedEventObj={
							userEventsId:"",
							eventName:"",
							startDate:"",
							endDate:"",
							startTimeHrs:"",
							startTimeMin:"",					
							endTimeHrs:"",
							endTimeMin:"",
							locationType:"",
							locaitonId:"",
							desc:"",
							organizers:"",
							actionPlans:"",
							isDeleted:"",
							task:""
						};
	var	selectedDateObj={
							importantDateId:"",
							eventId:"",
							eventType:"",
							eventName:"",
							startDate:"",	
							endDate:"",
							desc:"",
							frequency:"",
							isDeleted:"",
							task:""
						};
	
	var cadreObj={
					regionCadres:[]
				 };

	var dateObj={
					dateVal:new Date().getDate(),
					monthVal:new Date().getMonth(),
					yearVal:new Date().getFullYear()
				};
	var actionPlanArray = new Array();
	var renderDatesArr = new Array();

	var ImpDatesArray = new Array();
	var ImpEventsArray = new Array();
	var eventCadresArray = new Array();
	var actionCadresArray = new Array();
	var eventsRenderArr = new Array();
	var datesRenderArr = new Array();
	var eventDateRenderArr = new Array();	
	var emptyArray = new Array();
	var popupPanel;
	var smsHidden = 1;
	
	function smsRenewalMessage()
	{
		var elmt = document.getElementById('smsErrorPopupDiv');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','smsErrorDiv');
		
		var str = '';
		str	+= '<div id="smsErrorMain" style="padding:10px;">';
		str	+= '	<table id="loginDetailsTable" width="100%">';
		str	+= '		<tr>';
		str	+= '			<th colspan="3" align="left">';
		str	+= '				Your SMS Credentials are expired ';
		str	+= '			</th>';		
		str	+= '		</tr>';
		str	+= '		<tr>';
		str	+= '			<td colspan="3">Please contact contact us @  </td>';
		str	+= '		</tr>';
		str	+= '		<tr>';
		str	+= '			<th align="left">Phone </th><td>: </td><td> +91-40-40124153</td>';
		str	+= '		</tr>';
		str	+= '		<tr>';
		str	+= '			<th align="left">Mail </th><td>: </td><td> license@itgrids.com</td>';
		str	+= '		</tr>';
		str	+= '	</table>';	
		str	+= '</div>';
		divChild.innerHTML=str;		
		
		elmt.appendChild(divChild);	
		if(popupPanel)
			popupPanel.destroy();
		popupPanel = new YAHOO.widget.Dialog("smsErrorDiv",
				{ 
					 height:'150px',
					 width:'250px',
		             fixedcenter : true, 
		             visible : true,
		             constraintoviewport : true, 
		    		 iframe :true,
		    		 modal :true,
		    		 hideaftersubmit:true,
		    		 close:true,
					 draggable:true
	             } ); 
		popupPanel.render();
	}
	
	
	
	function buildSMSPopup()
	{
		var remainingSms = "${remainingSms}";
		if(remainingSms==0){
			smsRenewalMessage();
			return;
		}
		
		var str = '';	
		str +='	<form action="cadreRegisterAction" method="POST" name="smsForm">';
		str +='	<table>';
		str +='	<tr>';
		str +='		<th align="left">SMS Type</th>';
		str +='		<td align="left">';
		str +='			<input type="radio" name="sms_type" value="locationWise" onclick="getUserLocationData(this.value,\'sms\')"/> Location Wise	';			
		str +='			<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value,\'sms\')"/> Cadre Level Wise';
		str +=' 	</td>';		
		str +='	</tr>';
		str +='	<tr>';
		str +='		<th align="left" colspan="2"><div id="region_type_Alert" class="errorMessage"></div></th>';			
		str +='	</tr>';
		str +='	<tr>';
		str +='		<th align="left"><div id="region_type_Label"></div></th>';
		str +='		<td align="left"><div id="region_type_Data"></div></td>	';			
		str +='	</tr>';
		str +='	<tr>';
		str +='		<th align="left">';
		str +='			<div id="region_select_Label">	</div>';
		str +='		</th>';
		str +='		<td align="left">';
		str +='			<div id="region_select_Data">  </div>';					
		str +='		</td>';
		str +='	</tr>';
		str +='	<tr>';
		str +='		<th align="left"><div id="sms_cadre_name_include_label"></div></th>';
		str +='		<td align="left"><div id="sms_cadre_name_include_value"></div></td>';				
		str +='	</tr>';
		str +=' <tr>';
		str +='		<th align="left" colspan="2"><div id="sms_text_Alert" class="errorMessage"></div></th>';
		str +='	</tr>';
		str +=' <tr>';
		str +='		<th align="left"><div id="sms_text_Label"></div></th>';
		str +='		<td align="left"><div id="sms_text_Data"></div></td>';				
		str +='	</tr>';
		str +='	<tr>';
		str +='		<th align="left"><div id="sms_user_name_include_label"></div></th>';
		str +='		<td align="left"><div id="sms_user_name_include_value"></div></td>';				
		str +='	</tr>';
		str +='	<tr>';
		str +='		<td align="left" colspan="2">';
		str +='		<div id="successDiv" style="color:green"></div>';
		str +='		</td>';
		str +='	</tr>';
		str +='	<tr>';
		str +='		<td align="center" colspan="2"><div id="button_div"></div></td>	';			
		str +='	</tr>';		
		str +='	</table>';
		str +='	<form>';
		

		smsDialog = new YAHOO.widget.Dialog("myDialog",
				{ width : "650px", 
	              fixedcenter : true, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true	              
	             } ); 

		smsDialog.setHeader("Cadre SMS...");
		smsDialog.setBody(str);

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
									fillDataOptions(myResults,jsObj);	
								else if(jsObj.task == "fillSelectElements")
									fillSelectElement(myResults,jsObj);
								else if(jsObj.task == "sendSMS")
									displaySuccessMessage(myResults,jsObj);
								else if(jsObj.task=="CADRE_LEVEL")
									fillDataForCadreLevel(myResults,jsObj);
								else if(jsObj.task=="createEvent")
								{
									if(myResults.userEventsId == null)
									{
										alert("Event cannot be created due to some exception");
									}
									addCreatedEvent(myResults,jsObj);
									alert("Important Event created successfully");
								}
								else if(jsObj.task=="createImpDateEvent")
								{
									/*if(myResults.importantDateId == null)
									{
										alert("Important date cannot be created due to some exception");
									}*/
									addCreatedEvent(myResults,jsObj);			
									alert("Important Date created successfully");
								}
								else if(jsObj.task=="subscribe")
								{									
									var elmt = document.getElementById('subscribePartyDates');
									if(!elmt)
										alert("No subscribe Element");
									if(elmt.innerHTML == "Subscribe Party Imp Dates")
										elmt.innerHTML == "Unsubscribe Party Imp Dates";
									else if(elmt.innerHTML == "Subscribe Party Imp Dates")
										elmt.innerHTML == "Unsubscribe Party Imp Dates";

									elmt.innerHTML=myResults.subscribeTitle;
									showInitialImpEventsAndDates(myResults.userImpDates,"impDates","subscribe");
								}
								else if(jsObj.task=="showSelectedDateEvent")
								{
									buildSelectedDateEventPopup(myResults,jsObj);
								}
								else if(jsObj.task=="showSelectedDateEvent_nonEditable")
								{									
									buildUnEditableSelectedDateEventPopup(myResults,jsObj);
								}								
								else if(jsObj.task=="nextMonthEvents")
								{
									showInitialImpEventsAndDates(myResults.userEvents,"impEvents","nextPreviousMonthEvents");
									showInitialImpEventsAndDates(myResults.userImpDates,"impDates","nextPreviousMonthEvents");
								}
								else if(jsObj.type=="cadreLevel")
								{
									fillSelectElement(myResults,jsObj);
								}
								else if(jsObj.type=="cadreDetails")
								{
									fillSelectElement(myResults,jsObj);
								}
								else if(jsObj.task=="getEventCadres")
								{									
									showEventForCadres(myResults,jsObj);
								}
								else if(jsObj.task=="deleteEvent" || jsObj.task=="deleteImpDate")
								{									
									removeDeletedElement(myResults,jsObj);									
								}
								else if(jsObj.task=="updateCreateEvent" || jsObj.task=="updateImpDateEvent")
								{		
									addCreatedEvent(myResults,jsObj);	
								}
										
							}catch (e) {   
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
	
	function removeDeletedElement(id,jsObj)
	{
		
		if(eventDateDialog)
			eventDateDialog.hide();
		if(jsObj.task=="deleteEvent"){		
		var elmt = document.getElementById("ImpEvent_"+myResults);
		alert("Event successfully deleted");
	
		var parent = elmt.parentNode;
		parent.removeChild(elmt);
		}
		else if(jsObj.task=="deleteImpDate"){		
		var elmt = document.getElementById("ImpDate_"+myResults);
		alert("Date successfully deleted");
			
		var parent = elmt.parentNode;
		parent.removeChild(elmt);
		}
		
	}

	function setCadreValue(value)
	{
		document.getElementById("cadreLevelValue").value=value;
		return true;
	}
	
	function getCadreLevelValues(name,value,id)
	{
		var cadreLevelElmt = document.getElementById("cadreLevelField");
		var cadreLevelElmtText = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].text;
		var cadreLevelElmtValue = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].value;
		
		if(name == "cadreLevelState" && cadreLevelElmtText == "State")
		{
			document.getElementById("cadreLevelValue").value=1;						
		}
		else if((name == "cadreLevelState" && cadreLevelElmtText == "District") || (name == "cadreLevelState" && cadreLevelElmtText == "Constituency") 
			|| (name == "cadreLevelState" && cadreLevelElmtText == "Mandal")|| (name == "cadreLevelState" && cadreLevelElmtText == "Village")) 
		{
			
			getnextList("state",id,"true");				
		}
		else if(name == "cadreLevelDistrict" && (cadreLevelElmtText == "Mandal" || cadreLevelElmtText == "Village"))
		{
			
			getnextList("district",id,"true");				
		}
		else if(name == "cadreLevelDistrict" && cadreLevelElmtText == "Constituency")
		{
			
			getnextList("constituency",id,"true");				
		}

		else if(name == "cadreLevelMandal" && (cadreLevelElmtText == "Village"))
		{
			
			getnextList("mandal",id,"true");				
		}
			
		//if(document.getElementById("cadreLevelField").value == 5)
		//	getDistrictLevelValues(name,value,id)
	}

	function getStateList()
	{	
		var cadreLevelElmt = document.getElementById("cadreLevelField");
		
		var stateElmt = document.getElementById("cadreLevelState");
		var districtElmt = document.getElementById("cadreLevelDistrict");
		var constituencyElmt = document.getElementById("cadreLevelConstituency");
		var mandalElmt = document.getElementById("cadreLevelMandal");
		var villageElmt = document.getElementById("cadreLevelVillage");

		if(!cadreLevelElmt || !stateElmt || !districtElmt || !constituencyElmt || !mandalElmt || !villageElmt)
			alert("Selected Element is null !!");
		
		var cadreLevelElmtText = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].text;
		var cadreLevelElmtValue = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].value;

		var stateElmtText = stateElmt.options[stateElmt.selectedIndex].text;
		var stateElmtValue = stateElmt.options[stateElmt.selectedIndex].value;

		var districtElmtText = districtElmt.options[districtElmt.selectedIndex].text;
		var districtElmtValue = districtElmt.options[districtElmt.selectedIndex].value;

		var constituencyElmtText = constituencyElmt.options[constituencyElmt.selectedIndex].text;
		var constituencyElmtValue = constituencyElmt.options[constituencyElmt.selectedIndex].value;

		var mandalElmtText = mandalElmt.options[mandalElmt.selectedIndex].text;
		var mandalElmtValue = mandalElmt.options[mandalElmt.selectedIndex].value;

		var villageElmtText = villageElmt.options[villageElmt.selectedIndex].text;
		var villageElmtValue = villageElmt.options[villageElmt.selectedIndex].value;

		stateElmt.disabled = true;
		districtElmt.disabled = true;
		constituencyElmt.disabled = true;
		mandalElmt.disabled = true;
		villageElmt.disabled = true;		
					
		if(cadreLevelElmtText == "State")
		{
			stateElmt.disabled = false;	
			document.getElementById("cadreLevelValue").value=1;
		}
		else if(cadreLevelElmtText == "District")			
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
		}		
		else if(cadreLevelElmtText == "Constituency")
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
			constituencyElmt.disabled = false;
		}
		else if(cadreLevelElmtText == "Mandal")
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
			mandalElmt.disabled = false;
		}
		else if(cadreLevelElmtText == "Village")
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
			mandalElmt.disabled = false;
			villageElmt.disabled = false;
		}
		
		getStatesNDistricts("cadreLevel",cadreLevelElmtText,cadreLevelElmtValue)
		
	}
	function getStatesNDistricts(level,text,value)
	{	    
		var jsObj=
			{
					type:level,
					reportLevel:text,
					selected:value
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;

		callAjax(jsObj, url);
	}

	function getnextList(name,value,choice)
	{

		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:name,
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;
		callAjax(jsObj, url);
	}

	function displaySuccessMessage(results,jsObj)
	{
		var divElmt = document.getElementById("successDiv");
		var region_type_AlertEl = document.getElementById("region_type_Alert");
		var sms_text_AlertEl = document.getElementById("sms_text_Alert");
		var smsTextAreaEl = document.getElementById("smsTextArea"); 
		var maxcountEl = document.getElementById("maxcount");
		maxcountEl.innerHTML = '200';
		if(region_type_AlertEl)
			region_type_AlertEl.innerHTML = '';
		if(sms_text_AlertEl)
			sms_text_AlertEl.innerHTML = '';
		smsTextAreaEl.value='';
		var str='';
		if(results.status==0){
			str+=" SMS sent successfully to "+results.totalSmsSent+" cadres";
			if(results.remainingSmsCount!=0){
				str+="<br/>";
				str+=" You can send "+results.remainingSmsCount+"more SMS";
			}else{
				str+="<br/>";
				str+=" You cannot any more SMS ";
				smsRenewalMessage();
			}
			
		}else{
			smsRenewalMessage();
		}
		
		divElmt.innerHTML=str;
	}
	function fillSelectElement(results,jsObj)
	{
		if(jsObj.type == "cadreDetails")
		{
			if(jsObj.reportLevel=="state")
			{
				var elmt = document.getElementById("cadreLevelDistrict");
			}
			else if(jsObj.reportLevel=="district")
			{
				var elmt=document.getElementById("cadreLevelConstituency");
				
			}
			else if(jsObj.reportLevel=="constituency")
			{
				var elmt=document.getElementById("cadreLevelMandal");
			}
			else if(jsObj.reportLevel=="mandal")
			{
				var elmt=document.getElementById("cadreLevelVillage");
			}
			else if(jsObj.reportLevel=="Constituencies")
			{
				var elmt=document.getElementById("mandalField");
			}
		}
		if(jsObj.type == "cadreLevel")
			var elmt = document.getElementById("cadreLevelState");
		else if(jsObj.type == "STATE")
			var elmt = document.getElementById(jsObj.taskType+"_DistrictSelect");
		else if(jsObj.type == "DISTRICT")
			var elmt = document.getElementById(jsObj.taskType+"_ConstituencySelect");
		else if(jsObj.type == "CONSTITUENCY")
			var elmt = document.getElementById(jsObj.taskType+"_MandalSelect");
		else if(jsObj.type == "MANDAL")
			var elmt = document.getElementById(jsObj.taskType+"_VillageSelect");
			
		var len=elmt.length;			
		for(i=len-1;i>=0;i--)		
			elmt.remove(i);
		
		/*var x=document.createElement('option');		
		x.value="0";		
		x.text="Select";		

		try
		{
			elmt.add(x,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(x); // IE only
		}*/
		
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

function getUserLocationData(val,type)
	{			
		if(type == 'event' || type == 'Editevent' )
		{
			var str="cadreLevelDivId_"+type;
			
			cadreAnim = new YAHOO.util.Anim(str, {
				height: {
					 to: 190
					
				} 
			}, 1, YAHOO.util.Easing.easeOut);

			cadreAnim.animate();
			var ancElmt = document.getElementById('cadreLevelDivId_'+type+'_anc');
			if(ancElmt)
			ancElmt.style.display = 'block';
		}
		

		var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
		var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");

		if(eventCadreDivHeadElmt && eventCadreDivBodyElmt)
		{
			eventCadreDivHeadElmt.innerHTML="";
			eventCadreDivBodyElmt.innerHTML="";
		}
		
		var jsObj={
					value:val,
					taskType:type,
					task:"getUserLocation"
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/cadreSMSLocationWiseData.action?";
		callAjax(jsObj,url);
	}


function getUsersCadreLevelData(value,type)
	{		
		var str="cadreLevelDivId_"+type;
		
		cadreAnim = new YAHOO.util.Anim(str, {
			height: {
				to: 200
			} 
		}, 1, YAHOO.util.Easing.easeOut);

		cadreAnim.animate();

		var ancElmt = document.getElementById('cadreLevelDivId_'+type+'_anc');
		if(ancElmt)
			ancElmt.style.display = 'block';

		var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
		var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");

		if(eventCadreDivHeadElmt && eventCadreDivBodyElmt)
		{
			eventCadreDivHeadElmt.innerHTML="";
			eventCadreDivBodyElmt.innerHTML="";
		}

		var jsObj={
				taskType:type,
				task:"CADRE_LEVEL"
			  };
		var url = "<%=request.getContextPath()%>/usersCadreLevelData.action";	
		callAjax(jsObj,url);
	}
	
	function getNextRegions(id,val,regTask)
	{
		var selectElmt = document.getElementById(id);
		var selectValue = selectElmt.options[selectElmt.selectedIndex].value;
		
		if(selectValue=="0")
			return;

		var jsObj={
					value:selectValue,
					type:val,
					taskType:regTask,
					task:"fillSelectElements"
				  };
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?REGION="+val+"&REGION_ID="+selectValue;
		callAjax(jsObj,url);

	}

	function enableTextBox(){
		var textBoxElmt = document.getElementById("user_name");
		textBoxElmt.disabled =false;
	}
	function disableTextBox(){
		var textBoxElmt = document.getElementById("user_name");
		textBoxElmt.disabled =true;
	}


function getCadresLevelForEvent(regTask)
	{
		var region;
		var elmtId = "cadreLevelDivId_"+regTask;

		animateExpandDiv(elmtId,600);
		
		var elements = document.getElementsByTagName('input'); 
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="radio" && elements[i].name=="region_type_radio" && elements[i].checked==true)
				region = elements[i].value.toUpperCase();
		}

		if(region == '1')
			region = 'COUNTRY';
		else if(region == '2')
			region = 'STATE';
		else if(region == '3')
			region = 'DISTRICT';
		else if(region == '4')
			region = 'CONSTITUENCY';
		else if(region == '5')
			region = 'MANDAL';
		else if(region == '6')
			region = 'VILLAGE';

		var jsObj={	
					regionVal:region,
					regionSelectVal:"",
					displayType:regTask,
					cadreLevel:"cadreLevel",
					task:"getEventCadres"
					};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "<%=request.getContextPath()%>/getCadresForEvent.action?"+rparam;
		
		callAjax(jsObj, url);
	}


function fillDataForCadreLevel(results,jsObj)
	{
		var successDivElmt=	 document.getElementById("successDiv");
		//successDivElmt.innerHTML="";
		
		var actionVal = jsObj.taskType;

		if(jsObj.taskType == "sms")
		{
			var regionTypeElmtLabel = document.getElementById("region_type_Label");
			var regionTypeElmtData = document.getElementById("region_type_Data");
			var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
			var regionTypeSelectElmtData = document.getElementById("region_select_Data");
		}
		else if(jsObj.taskType == "event" || jsObj.taskType == "action")
		{			
			var regionTypeElmtLabel = document.getElementById(actionVal+"_region_type_Label");
			var regionTypeElmtData = document.getElementById(actionVal+"_region_type_Data");
			var regionTypeSelectElmtLabel = document.getElementById(actionVal+"_region_select_Label");
			var regionTypeSelectElmtData = document.getElementById(actionVal+"_region_select_Data");
		}
		regionTypeElmtLabel.innerHTML="Select Cadre Level";

		var str='';
		for(var i in results)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results[i].id+'"> '+results[i].name+' ';
		}
		regionTypeElmtData.innerHTML=str;
		//--------------


		if(regionTypeSelectElmtLabel && regionTypeSelectElmtData)
		{
			regionTypeSelectElmtLabel.innerHTML="";
			regionTypeSelectElmtData.innerHTML="";
		}

		if(jsObj.taskType == "event" || jsObj.taskType == "action")
		{
			var submitStr='<input type="button" onclick="getCadresLevelForEvent(\''+jsObj.taskType+'\')" value="Get Cadres"/>';
			var submitDiv = document.getElementById(actionVal+"_region_submit");
			submitDiv.innerHTML = submitStr;
			return;
		}
		//-------------
		
		var smsCadreNameIncludeLabel = document.getElementById("sms_cadre_name_include_label");
		var smsCadreNameIncludeValue = document.getElementById("sms_cadre_name_include_value");

		if(smsCadreNameIncludeLabel)
			smsCadreNameIncludeLabel.innerHTML="Include Cadre Name";
		
		var smsCadreIncludeStr='<input type="radio" id="include_cadre_name" name="include_cadre_name" value="YES" /> Yes';
		   smsCadreIncludeStr+='<input type="radio" id="no_cadre_name" name="include_cadre_name" value="NO" checked="checked" /> No    ';
		   smsCadreIncludeStr+='.       with cadre names performance degrades';
		if(smsCadreNameIncludeValue)
			smsCadreNameIncludeValue.innerHTML=smsCadreIncludeStr;

		//------
		
		
		var smsUserNameIncludeLabel = document.getElementById("sms_user_name_include_label");
		var smsUserNameIncludeValue = document.getElementById("sms_user_name_include_value");

		if(smsUserNameIncludeLabel)
			smsUserNameIncludeLabel.innerHTML="Include User Name";
		
		var smsUserIncludeStr='<input type="radio" id="include_user_name" name="include_user_name" value="YES" onclick="enableTextBox();" /> Yes';
		smsUserIncludeStr+='<input type="radio" id="no_user_name" name="include_user_name" value="NO" checked="checked" onclick="disableTextBox();" /> No    ';
		smsUserIncludeStr+='. <input type="text" id ="user_name" value="${sessionScope.UserName}" name="user_name" disabled/>';
		if(smsUserNameIncludeValue)
			smsUserNameIncludeValue.innerHTML=smsUserIncludeStr;

		//-------------------------------------
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

	function fillDataOptions(results,jsObj)
	{			
		//Setting values for region type..
		var regTask = jsObj.taskType;
		if(jsObj.taskType == 'sms')
		{
			var regionTypeElmtLabel = document.getElementById("region_type_Label");
			var regionTypeElmtData = document.getElementById("region_type_Data");

			var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
			var regionTypeSelectElmtData = document.getElementById("region_select_Data");

			var smsCadreNameIncludeLabel = document.getElementById("sms_cadre_name_include_label");
			var smsCadreNameIncludeValue = document.getElementById("sms_cadre_name_include_value");

			if(smsCadreNameIncludeLabel)
				smsCadreNameIncludeLabel.innerHTML="Include Cadre Name";
			
			var smsCadreIncludeStr='<input type="radio" id="include_cadre_name" name="include_cadre_name" value="YES" /> Yes';
			   smsCadreIncludeStr+='<input type="radio" id="no_cadre_name" name="include_cadre_name" value="NO" checked="checked" /> No    ';
			   smsCadreIncludeStr+='.       with cadre names performance degrades';
			if(smsCadreNameIncludeValue)
				smsCadreNameIncludeValue.innerHTML=smsCadreIncludeStr;

			//------
			
			
			var smsUserNameIncludeLabel = document.getElementById("sms_user_name_include_label");
			var smsUserNameIncludeValue = document.getElementById("sms_user_name_include_value");

			if(smsUserNameIncludeLabel)
				smsUserNameIncludeLabel.innerHTML="Include User Name";
			
			var smsUserIncludeStr='<input type="radio" id="include_user_name" name="include_user_name" value="YES" onclick="enableTextBox();" /> Yes';
			smsUserIncludeStr+='<input type="radio" id="no_user_name" name="include_user_name" value="NO" checked="checked" onclick="disableTextBox();" /> No    ';
			smsUserIncludeStr+='.                    <input type="text" id ="user_name" name="user_name" value="${sessionScope.UserName}" disabled/>';
			if(smsUserNameIncludeValue)
				smsUserNameIncludeValue.innerHTML=smsUserIncludeStr;

		}
		else if(jsObj.taskType == 'event' || jsObj.taskType == 'action' || jsObj.taskType == 'Editevent')
		{			
			var regionTypeElmtLabel = document.getElementById(regTask+"_region_type_Label");
			var regionTypeElmtData = document.getElementById(regTask+"_region_type_Data");

			var regionTypeSelectElmtLabel = document.getElementById(regTask+"_region_select_Label");
			var regionTypeSelectElmtData = document.getElementById(regTask+"_region_select_Data");

		}

		var str='';
		for(var i in results.regions)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'" onclick="displayRegionsSelect(this.value,\''+regTask+'\')" /> '+results.regions[i].name+'';
		}		

		if(regionTypeElmtLabel)
			regionTypeElmtLabel.innerHTML="Select Level";
		if(regionTypeElmtData)
			regionTypeElmtData.innerHTML=str;
		
		//---------
		//Setting values for select box..
		
	  if(regionTypeSelectElmtLabel)
			regionTypeSelectElmtLabel.innerHTML="Select Location";

		var regionStr='';
		
		regionStr+='<select id="'+regTask+'_StateSelect" class="selectBox" onchange="getNextRegions(this.id,\'STATE\',\''+regTask+'\')" disabled="true">';
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

		
		regionStr+='<select id="'+regTask+'_DistrictSelect" class="selectBox" onchange="getNextRegions(this.id,\'DISTRICT\',\''+regTask+'\')" disabled="false">';
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
		
		
		regionStr+='<select id="'+regTask+'_ConstituencySelect" class="selectBox" onchange="getNextRegions(this.id,\'CONSTITUENCY\',\''+regTask+'\')" disabled="true">';
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
	
	
	
		regionStr+='<select id="'+regTask+'_MandalSelect" class="selectBox" onchange="getNextRegions(this.id,\'MANDAL\',\''+regTask+'\')" disabled="true">';
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

		
		regionStr+='<select id="'+regTask+'_VillageSelect" class="selectBox" disabled="true">';
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

		if(jsObj.taskType == 'event' || jsObj.taskType == 'action' || jsObj.taskType == 'Editevent')
		{
			var submitStr='<input type="button" onclick="getCadresForEvent(\''+regTask+'\')" value="Get Cadres"/>';
			var submitDiv = document.getElementById(regTask+"_region_submit");
			submitDiv.innerHTML = submitStr;
			return;
		}
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

	function animateExpandDiv(val,num)
	{			
		if(!document.getElementById(""+val))
		{
			return;
		}
				
		var myAnim = new YAHOO.util.Anim(val, {
			height: {
				to: num 
			} 
		}, 1, YAHOO.util.Easing.easeIn);

		myAnim.animate();	
	}

	function getCadresForEvent(regTask)
	{		
		var region,regionSelect;
		
		if(regTask == 'action')
		{
			var elmtId = "cadreLevelDivId_eventAction";
			animateExpandDiv(elmtId,630);
		}
		else
		{
			var elmtId = "cadreLevelDivId_"+regTask;
			animateExpandDiv(elmtId,500);
		}
				
		
		

		var elements = document.getElementsByTagName('input'); 

		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="radio" && elements[i].name=="region_type_radio" && elements[i].checked==true)
			{
				region = elements[i].value.toUpperCase();				
			}
		}	
		
		if(region == "STATE")
			var selectElmt = document.getElementById(regTask+"_StateSelect");
		else if(region == "DISTRICT")
	 		var selectElmt = document.getElementById(regTask+"_DistrictSelect");
		else if(region == "CONSTITUENCY")
	 		var selectElmt = document.getElementById(regTask+"_ConstituencySelect");
		else if(region == "MANDAL")
	 		var selectElmt = document.getElementById(regTask+"_MandalSelect");
		else if(region == "VILLAGE")
	 		var selectElmt = document.getElementById(regTask+"_VillageSelect");

		var regionSelect = selectElmt.options[selectElmt.selectedIndex].value;	
			
		var jsObj={	
					regionVal:region,
					regionSelectVal:regionSelect,
					displayType:regTask,
					cadreLevel:"locationLevel",
					task:"getEventCadres"
					};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "<%=request.getContextPath()%>/getCadresForEvent.action?"+rparam;
		
		callAjax(jsObj, url);
		
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
		var val = null;
		var region_type_AlertEl = document.getElementById("region_type_Alert");
		var sms_text_AlertEl = document.getElementById("sms_text_Alert");
		if(region_type_AlertEl)
			region_type_AlertEl.innerHTML = '';
		if(sms_text_AlertEl)
			sms_text_AlertEl.innerHTML = '';
		for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
		{
			if( document.smsForm.region_type_radio[i].checked == true ){
				val = document.smsForm.region_type_radio[i].value;
				
			}
		}
		if(val == null)
		{
			region_type_AlertEl.innerHTML = 'Please Select Region Level!';
			return;
		}
		var textAreaElmt = document.getElementById("smsTextArea");

		textAreaElmtValue = textAreaElmt.value
		if(textAreaElmtValue == '')
		{
			sms_text_AlertEl.innerHTML = 'Please Type A Message!';
			return;
		}
		val=val.toUpperCase();

		//---
		var include_cadre ="NO";
		var include_user ="NO";

		var elements = document.getElementsByTagName('input'); 
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="radio" && elements[i].name=="include_user_name" && elements[i].checked==true)
				include_user = elements[i].value;
			else if(elements[i].type=="radio" && elements[i].name=="include_cadre_name" && elements[i].checked==true)
				include_cadre = elements[i].value;
		}
		
		if(include_user=='YES'){
			if(document.getElementById('user_name')!=null && document.getElementById('user_name').value!='' )
				textAreaElmtValue = textAreaElmtValue + ' Thx ' + document.smsForm.user_name.value;
		}

		var valSelectValue = valSelect.options[valSelect.selectedIndex].value;
		val=val.toUpperCase();
		//---
		
		var jsObj={
					SMS_LEVEL_TYPE:'CADRE_LEVEL',
					SMS_LEVEL_VALUE: val,
					SMS_MESSAGE:textAreaElmtValue,
					SMS_INCLUDE_CADRE_NAME:include_cadre,
					task:"sendSMS"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
		callAjax(jsObj,url);
	}
	function sendSMS()
	{
		var val= null;
		var region_type_AlertEl = document.getElementById("region_type_Alert");
		var sms_text_AlertEl = document.getElementById("sms_text_Alert");
		if(region_type_AlertEl)
			region_type_AlertEl.innerHTML = '';
		if(sms_text_AlertEl)
			sms_text_AlertEl.innerHTML = '';
		for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
		{
			if( document.smsForm.region_type_radio[i].checked == true )
				val = document.smsForm.region_type_radio[i].value;
		}
		if(val == null)
		{	
			if(region_type_AlertEl)
			region_type_AlertEl.innerHTML = 'Please Select Region Level!'
			return;
		}	
		var valSelect = document.getElementById("sms_"+val+"Select");
		var textAreaElmt = document.getElementById("smsTextArea");
		
		var include_cadre ="NO";
		var include_user ="NO";
		//var user_name ='Thx ${sessionScope.UserName}';
		var elements = document.getElementsByTagName('input'); 
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="radio" && elements[i].name=="include_user_name" && elements[i].checked==true)
				include_user = elements[i].value;
			else if(elements[i].type=="radio" && elements[i].name=="include_cadre_name" && elements[i].checked==true)
				include_cadre = elements[i].value;
		}
		

		textAreaElmtValue = textAreaElmt.value
		
		if(textAreaElmtValue == '')
		{
			sms_text_AlertEl.innerHTML = 'Please Type A Message!';
			return;
		}	
		if(include_user=='YES'){
			if(document.getElementById('user_name')!=null && document.getElementById('user_name').value!='' )
				textAreaElmtValue = textAreaElmtValue + ' Thx ' + document.smsForm.user_name.value;
		}
		var valSelectValue = valSelect.options[valSelect.selectedIndex].value;
		val=val.toUpperCase();
		
		var jsObj={
					SMS_LEVEL_TYPE: val,
					SMS_LEVEL_VALUE: valSelectValue,
					SMS_MESSAGE: textAreaElmtValue,
					SMS_INCLUDE_CADRE_NAME: include_cadre,
					task:"sendSMS"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam+"&smsHidden="+smsHidden;
		callAjax(jsObj,url);
		
	}

	function smsHiddenIncrementHidden()
	{
		smsHidden++;
	}
	
	function displayRegionsSelect(val,regTask)
	{
		var stateSelectElmt = document.getElementById(regTask+"_StateSelect");
		var districtSelectElmt = document.getElementById(regTask+"_DistrictSelect");
		var constituencySelectElmt = document.getElementById(regTask+"_ConstituencySelect");
		var mandalSelectElmt = document.getElementById(regTask+"_MandalSelect");
		var villageSelectElmt = document.getElementById(regTask+"_VillageSelect");
		
		stateSelectElmt.disabled=true;
		districtSelectElmt.disabled=true;	
		constituencySelectElmt.disabled=true;	
		mandalSelectElmt.disabled=true;	
		villageSelectElmt.disabled=true;	
		if(val == "STATE"){
			stateSelectElmt.disabled=false;
		}
		if(val == "DISTRICT")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;			
		}
		if(val == "CONSTITUENCY")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;	
			constituencySelectElmt.disabled=false;	
		}
		if(val == "MANDAL")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;
			constituencySelectElmt.disabled=false;	
			mandalSelectElmt.disabled=false;	
		}
		if(val == "VILLAGE")
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
		var subStr = divId.substring(0,divId.lastIndexOf('_'));	
				
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 

		var calDateResult = dateToLocaleString(selDate, this);
		
		var divElmt = document.getElementById(divId);
		var elmt = document.getElementById(subStr);
		
		if(elmt)
		{
			elmt.value = calDateResult;
		}
		
		if(subStr == "startDateText")
		{
			var EendElmt = document.getElementById("endDateText");
			if(EendElmt)
				EendElmt.value = calDateResult;
		}
		else if(subStr == "ImpStartDateText")
		{
			var IendElmt = document.getElementById("ImpEndDateText");
			if(IendElmt)
				IendElmt.value = calDateResult;
		}

		divElmt.style.display='none';
	}

	function getDateTime(date)
	{		
		var startDayStr = date.substring(8,10);		
		var startMonStr = date.substring(5,7);
		var startYearStr = date.substring(0,4);	

		var startTimeHrs = date.substring(11,13);	
		var startTimeMin = date.substring(14,16);	

	/*	var index = date.indexOf('T');
		var colIndex1 = date.indexOf(':');
		var colIndex2 = date.lastIndexOf(':');
		
		var startDayStr = date.substring(8,10);		
		var startMonStr = date.substring(5,7);
		var startYearStr = date.substring(0,4);	
		var startTimeHrs = date.substring(index+1,colIndex1);	
		var startTimeMin = date.substring(colIndex1+1,colIndex2);	
    */
		var dateTimeObj={
							day:startDayStr,
							month:startMonStr,
							year:startYearStr,
							hours:startTimeHrs,
							minutes:startTimeMin
						};

		return dateTimeObj;
	}
	
	function buildUnEditableSelectedDateEventPopup(results,jsObj)
	{
		
		var elmt = document.getElementById('cadreManagementMainDiv');		

		var divChild = document.createElement('div');
		divChild.setAttribute('id','eventDateDetails');
		divChild.setAttribute('class','yui-skin-sam');
		
		
		if(jsObj.taskType == "impEvent")
		{
			if(results.startDate)
				var sDayobj = getDateTime(results.startDate);			
			if(results.endDate)
				var eDayobj = getDateTime(results.endDate);
		}
		else if (jsObj.taskType == "impDate")
		{
			if(results[0].startDate)
				var startDateObj = getDateTime(results[0].startDate);
			if(results[0].endDate)
				var endDateObj = getDateTime(results[0].endDate);
			if(results[0].impDate)
				var impDateObj = getDateTime(results[0].impDate);
		}		
		
		var eventStr='';
		eventStr+='<div class="hd" style="color:#2B4E70;">Event Details...</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<table class="selectedDateEvent">';
		eventStr+='<tr>';
		eventStr+='<th>Title</th>';		
		if(jsObj.taskType == "impEvent")
		{
			eventStr+='<td colspan="3"><span id="" class="fieldSpan">'+results.title+'</span></td>';
		}
		else if(jsObj.taskType == "impDate")
		{
			eventStr+='<td colspan="3"><span id="" class="fieldSpan">'+results[0].title+'</span></td>';					
		}
		eventStr+='</tr>';
		
		if(jsObj.taskType == "impEvent")
		{
			eventStr+='<tr>';
			if(results.startDate)
			{
				eventStr+='<th>Start date</th>';
				eventStr+='<td><span class="fieldSpan">'+sDayobj.day+'/'+sDayobj.month+'/'+sDayobj.year+'</span></td>';
			}
			if(results.endDate)
			{
				eventStr+='<th>End date</th>';
				eventStr+='<td><span class="fieldSpan">'+eDayobj.day+'/'+eDayobj.month+'/'+eDayobj.year+'</span></td>';		
			}
			eventStr+='</tr>';
			eventStr+='<tr>';
			if(results.startDate)
			{		
				eventStr+='<th>Start Time</th>';
				eventStr+='<td><span class="fieldSpan">'+sDayobj.hours+':'+sDayobj.minutes+'</span></td>';
			}
			if(results.endDate)
			{
				eventStr+='<th>End Time</th>';
				eventStr+='<td><span class="fieldSpan">'+eDayobj.hours+':'+eDayobj.minutes+'</span></td>';		
			}
			eventStr+='</tr>';
		}
		else if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';

			eventStr+='<th>Imp date</th>';
			eventStr+='<td><span class="fieldSpan">'+impDateObj.day+'/'+impDateObj.month+'/'+impDateObj.year+'</span></td>';
			eventStr+='</tr>';
		}

		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		if(jsObj.taskType == "impEvent")
		{
			if(results.description != '')
				eventStr+='<td colspan="3"><span class="fieldSpan">'+results.description+'</span></td>';
			else
				eventStr+='<td colspan="3"><span class="fieldSpan"> - </span></td>';
		}
		else if(jsObj.taskType == "impDate")
		{
			if(results[0].importance != '')
				eventStr+='<td colspan="3"><span class="fieldSpan">'+results[0].importance+'</span></td>';
			else
				eventStr+='<td colspan="3"><span class="fieldSpan"> - </span></td>';
		}
		eventStr+='</tr>';

		if(results.organizers)
		{
			eventStr+='<tr>';
			eventStr+='<th>Organizers</th>';
			if(results.organizers != null)
			{
				eventStr+='<td colspan="3">';
				for(var cadre in results.organizers)
					eventStr+='<div class="fieldSpan" id="'+results.organizers[cadre].id+'_div">'+results.organizers[cadre].name+'</div>';
				eventStr+='</td>';
			}
			else
				eventStr+='<td colspan="3"><span class="fieldSpan"> - </span></td>';
			eventStr+='</tr>';
		}
		if(results.actionPlans)
		{
			eventStr+='<tr>';
			eventStr+='<th>Action Plan(s)</th>';
			eventStr+='<td colspan="3">';
			for(var i in results.actionPlans)
			{	
				var actionDayobj = getDateTime(results.actionPlans[i].targetDate);	
				eventStr+='<div class="eventActionPlanDiv">'+results.actionPlans[i].action+'-'+actionDayobj.day+'/'+actionDayobj.month+'/'+actionDayobj.year+'</div>';	
				
			}
			eventStr+='</td>';
			eventStr+='</tr>';

		}
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';
			eventStr+='<th>Repeat Frequency</th>';
			if(results[0].frequency != null)
				eventStr+='<td colspan="3"><span class="fieldSpan">'+results[0].frequency+'</span></td>';		
			else
				eventStr+='<td colspan="3"><span class="fieldSpan"> - </span></td>';		
			
			if(results[0].endDate)
			{
				eventStr+='<th>Repeat Until</th>';
				eventStr+='<td><span class="fieldSpan">'+endDateObj.day+'/'+endDateObj.month+'/'+endDateObj.year+'</span></td>';
			}
			eventStr+='</tr>';
		}
		/*
		eventStr+='<tr>';
		eventStr+='<td><input type="button" value="Update" onclick="updateSelectedEvent(\''+jsObj.taskType+'\')"></input></td>';
		eventStr+='<td><input type="button" value="Delete" onclick="deleteSelectedEvent(\''+jsObj.taskType+'\')"></input></td>';
		eventStr+='<td><input type="button" value="Delete" onclick="cancelSelectedEvent(\''+jsObj.taskType+'\')"></input></td>';
		eventStr+='</tr>';
		*/
		eventStr+='</table>';
		eventStr+='</div>';		
		divChild.innerHTML=eventStr;

		elmt.appendChild(divChild);

		if(eventDateDialog)
			eventDateDialog.destroy();

		eventDateDialog = new YAHOO.widget.Dialog("eventDateDetails",
				{ width : "600px", 
	              fixedcenter : false, 
	              visible : true,  
				  x:350,
				  y:800,
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true
	             } ); 
		eventDateDialog.render(); 
	}

	function buildSelectedDateEventPopup(results,jsObj)
	{
		var elmt = document.getElementById('cadreManagementMainDiv');		
		var eventId;
		
		jsonStr = YAHOO.lang.JSON.stringify(results);
		
		var divChild = document.createElement('div');
		divChild.setAttribute('id','eventDateDetails');
		divChild.setAttribute('class','yui-skin-sam');
		
		
		if(jsObj.taskType == "impEvent")
		{
			eventId = results.userEventsId;
			if(results.startDate)
				var sDayobj = getDateTime(results.startDate);			
			if(results.endDate)
				var eDayobj = getDateTime(results.endDate);
		}
		else if (jsObj.taskType == "impDate")
		{
			eventId = results[0].importantDateId;
			if(results[0].startDate)
				var startDateObj = getDateTime(results[0].startDate);
			if(results[0].endDate)
				var endDateObj = getDateTime(results[0].endDate);
			if(results[0].impDate)
				var impDateObj = getDateTime(results[0].impDate);
		}
			

		var eventStr='';
		eventStr+='<div class="hd" style="color:#2B4E70;">Event Details...</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<table class="selectedDateEvent">';
		eventStr+='<tr>';
		eventStr+='<th>Title</th>';		
		if(jsObj.taskType == "impEvent")
		{
			eventStr+='<td colspan="3">';
			eventStr+='<input type="text" id="eventNameText" class="fieldSpan" onclick="changeToEditableField(this,\'text\',\'impEvent\',\'title\')" value="'+results.title+'">';
			eventStr+='</td>';
		}
		else if(jsObj.taskType == "impDate")
		{
			eventStr+='<td colspan="3">';
			eventStr+='<input id="ImpeventNameText" class="fieldSpan" onclick="changeToEditableField(this,\'text\',\'impDate\',\'title\')" value="'+results[0].title+'">';
			eventStr+='</td>';					
		}
		eventStr+='</tr>';
		
		if(jsObj.taskType == "impEvent")
		{
			eventStr+='<tr>';
			if(results.startDate)
			{
				eventStr+='<th>Start date</th>';
				eventStr+='<td>';
				eventStr+='<div><input type="text" id="startDateText" readonly="readonly" name="startDateText" value="'+sDayobj.day+'/'+sDayobj.month+'/'+sDayobj.year+'" onfocus="showDateCal(\'startDateText_Div\',this.value)"/></div>';
				eventStr+='<div id="startDateText_Div" class="tinyDateCal"></div>';
				eventStr+='</td>';				
			}
			if(results.endDate)
			{
				eventStr+='<th>End date</th>';
				eventStr+='<td>';
				eventStr+='<div><input type="text" id="endDateText" readonly="readonly" name="endDateText" value="'+eDayobj.day+'/'+eDayobj.month+'/'+eDayobj.year+'" onfocus="showDateCal(\'endDateText_Div\',this.value)"/></div>';
				eventStr+='<div id="endDateText_Div" class="tinyDateCal"></div>';
				eventStr+='</td>';				
			}
			eventStr+='</tr>';
			eventStr+='<tr>';
			if(results.startDate)
			{		
				eventStr+='<th>Start Time</th>';
				eventStr+='<td>';
				eventStr+='<select id="startTimeHrs" name="startTimeText" class="timeSelect">';		
				for(var i=0;i<=23;i++)
				{
					if(i==sDayobj.hours)
						eventStr+='<option selected="selected">'+i+'</option>';
					eventStr+='<option>'+i+'</option>';
				}
				eventStr+='</select>';
				eventStr+='<select id="startTimeMin" name="startTimeText" class="timeSelect">';
				eventStr+='<option>00</option>';		
				eventStr+='<option>15</option>';
				eventStr+='<option selected="selected">30</option>';
				eventStr+='<option>45</option>';		
				eventStr+='</select>';
				eventStr+='</td>';				
			}
			if(results.endDate)
			{
				eventStr+='<th>End Time</th>';
				eventStr+='<td>';
				eventStr+='<select id="endTimeHrs" name="endTimeText" class="timeSelect">';
				for(var i=0;i<=23;i++)
				{
					if(i==eDayobj.hours)
						eventStr+='<option selected="selected">'+i+'</option>';
					eventStr+='<option>'+i+'</option>';
				}
				eventStr+='</select>';
				
				eventStr+='<select id="endTimeMin" name="startTimeText" class="timeSelect">';
				eventStr+='<option>00</option>';		
				eventStr+='<option>15</option>';
				eventStr+='<option selected="selected">30</option>';
				eventStr+='<option>45</option>';		
				eventStr+='</select>';
				eventStr+='</td>';				
			}
			eventStr+='</tr>';
		}
		else if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';

			eventStr+='<th>Imp date</th>';
			eventStr+='<td>';
			eventStr+='<div><input type="text" id="ImpStartDateText" value="'+impDateObj.day+'/'+impDateObj.month+'/'+impDateObj.year+'" name="ImpStartDateText" readonly="readonly" onfocus="showDateCal(\'ImpStartDateText_Div\')"/></div>';
			eventStr+='<div id="ImpStartDateText_Div" class="tinyDateCal"></div>';
			eventStr+='</td>';			
			eventStr+='</tr>';
		}

		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		if(jsObj.taskType == "impEvent")
		{
				eventStr+='<td colspan="3">';
				eventStr+='<textarea rows="5" cols="50" id="descTextArea" name="descTextArea">'+results.description+'</textarea>';
				eventStr+='<td>';
			
			
		}
		else if(jsObj.taskType == "impDate")
		{
				eventStr+='<td colspan="3">';
				eventStr+='<textarea rows="5" cols="50" id="ImpdescTextArea" name="ImpdescTextArea">'+results[0].importance+'</textarea>';
				eventStr+='</td>';				
		}
		eventStr+='</tr>';

		if(results.organizers)
		{	
			eventCadresArray = results.organizers;
			eventStr+='<tr>';
			eventStr+='<th>Organizers</th>';
			if(results.organizers != null)
			{
				eventStr+='<td colspan="3">';
				eventStr+='<div id="eventOrganizersDiv_Main">';
				eventStr+='<div id="eventOrganizersDiv_Head" style="text-align:right;">';
				eventStr+='<a href="javascript:{}" onclick="editOrganizersForEvent(\'eventOrganizersDiv_Body\')">Add Organizers</a>';
				eventStr+='</div>';
				eventStr+='<div id="eventOrganizersDiv_Body">';
				for(var cadre in results.organizers)
				{
					eventStr+='<div id="event_'+results.organizers[cadre].id+'_div" class="cadresDivForPanel" ';					
					eventStr+='<span id="cadreSpan_'+results.organizers[cadre].id+'" class="cadresCloseSpan" onclick="deleteEventCadreFromPanelDiv(this.id,\'event\')"> X </span>';	
					eventStr+=results.organizers[cadre].name;
					eventStr+='</div>';								
				}
				eventStr+='</div>';				
				eventStr+='</div>';				
				eventStr+='</td>';
			}
			else
				eventStr+='<td colspan="3"><span class="fieldSpan" onclick="changeToEditableField(this,\'text\',\'impEvent\',\'organizers\')"> - </span></td>';
			eventStr+='</tr>';
		}
		if(results.actionPlans)
		{				
			for(var a=0;a<results.actionPlans.length;a++)
			{
				var targetDateObj = getDateTime(results.actionPlans[a].targetDate);
				var actObj={
								action:results.actionPlans[a].action,
								targetDate:targetDateObj.day+'/'+targetDateObj.month+'/'+targetDateObj.year,
								actionPlanOrganizers:results.actionPlans[a].actionPlanOrganizers	
						   }
				actionPlanArray.push(actObj);
			}
		
			eventStr+='<tr>';
			eventStr+='<th>Action Plan(s)</th>';
			
			eventStr+='<td colspan="3">';
			eventStr+='<div id="actionPlanDiv_Main">';
			eventStr+='<div id="actionPlanDiv_Head" style="text-align:right;">';
			eventStr+='<a href="javascript:{}" onclick="editactionPlanForEvent(\'actionPlanDiv_Body\')">Add Action Plan</a>';
			eventStr+='</div>';
			eventStr+='<div id="actionPlanDiv_Body">';
			for(var i in results.actionPlans)
			{
				var actionDayobj = getDateTime(results.actionPlans[i].targetDate);	
				eventStr+='<div id="action_'+results.actionPlans[i].eventActionPlanId+'_div" class="cadresDivForPanel" ';				
				eventStr+='<span id="cadreSpan_'+results.actionPlans[i].eventActionPlanId+'" class="cadresCloseSpan" onclick="deleteActionPlanFromPanelDiv(this.id,\'event\')"> X </span>';	
				eventStr+=results.actionPlans[i].action+'-'+actionDayobj.day+'/'+actionDayobj.month+'/'+actionDayobj.year;
				eventStr+='</div>';							
			}
			eventStr+='</div>';				
			eventStr+='</div>';				
			eventStr+='</td>';

			/*eventStr+='<td colspan="3">';
			for(var i in results.actionPlans)
			{	
				var actionDayobj = getDateTime(results.actionPlans[i].targetDate);	
				eventStr+='<div class="eventActionPlanDiv">'+results.actionPlans[i].action+'-'+actionDayobj.day+'/'+actionDayobj.month+'/'+actionDayobj.year+'</div>';	
				
			}
			eventStr+='</td>';*/
			eventStr+='</tr>';

		}
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';
			eventStr+='<th>Repeat Frequency</th>';
			if(results[0].frequency != null)
				eventStr+='<td colspan="1"><span class="fieldSpan" onclick="changeToEditableField(this,\'select\',\'impDate\',\'frequency\')">'+results[0].frequency+'</span></td>';		
			else
				eventStr+='<td colspan="1"><span class="fieldSpan" onclick="changeToEditableField(this,\'select\',\'impDate\',\'frequency\')"> - </span></td>';		
			
			if(results[0].endDate)
			{
				eventStr+='<th>Repeat Until</th>';
				eventStr+='<td>';
				eventStr+='<div><input type="text" id="ImpEndDateText" readonly="readonly" value="'+endDateObj.day+'/'+endDateObj.month+'/'+endDateObj.year+'" name="ImpEndDateText" disabled="true" onfocus="showDateCal(\'ImpEndDateText_Div\')"/></div>';
				eventStr+='<div id="ImpEndDateText_Div" class="tinyDateCal"></div>';
				eventStr+='</td>';
				//eventStr+='<span class="fieldSpan" onclick="changeToEditableField(this,\'date\',\'impDate\',\'endDate\')">'+endDateObj.day+'/'+endDateObj.month+'/'+endDateObj.year+'</span></td>';
			}
			eventStr+='</tr>';
		}
		
		eventStr+='<tr>';
		eventStr+='<td><input type="button" value="Update" onclick="updateSelectedEvent(\''+jsObj.taskType+'\')"></input></td>';
		eventStr+='<td><input type="button" value="Delete" onclick="deleteSelectedEvent(\''+jsObj.taskType+'\',\''+eventId+'\')"></input></td>';		
		eventStr+='</tr>';
		
		eventStr+='</table>';
		eventStr+='</div>';		
		divChild.innerHTML=eventStr;

		elmt.appendChild(divChild);

		if(eventDateDialog)
			eventDateDialog.destroy();

		eventDateDialog = new YAHOO.widget.Dialog("eventDateDetails",
				{ width : "800px", 
	              fixedcenter : false, 
	              visible : true,  
				  x:300,
				  y:500,
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true
	             } ); 
		eventDateDialog.render(); 
	}
	
	function editactionPlanForEvent(id)
	{
		var divElmt = document.getElementById(id);
		if(divElmt)
			divElmt.innerHTML='';

		var editEventStr=createActionPlan("EditactionPlan");
		divElmt.innerHTML=editEventStr;		
		var divElmt = document.getElementById("cadreLevelDivId_EditactionPlan");
		divElmt.style.display = 'block';
	}
	function editOrganizersForEvent(id)
	{		
		var divElmt = document.getElementById(id);
		if(divElmt)
			divElmt.innerHTML='';

		var editEventStr=getOrganisersString("Editevent");
		divElmt.innerHTML=editEventStr;		

		var divElmt = document.getElementById("cadreLevelDivId_Editevent");
		divElmt.style.display = 'block';
	}

	function addCadresToEditEventPanel(type)
	{
		var cadreArray = new Array();

		
		var divElmt;
		if(type == 'Editevent')
		{
			cadreArray = eventCadresArray;
			divElmt = document.getElementById("eventOrganizersDiv_Body");
		}
		else if(type == 'Editaction')
		{
			cadreArray = actionCadresArray;
			divElmt = document.getElementById("actionPlansDiv");
		}
		
		
		if(cadreArray.length == 0 || !divElmt)
			return;

		var str = '';
		str+='<table class="selectedCadresDateEvent" width="100%">';		
		for(var i in cadreArray)
		{
			str+='<tr>';			
			str+='<td>';
			str+='<div id="cadreNameDiv_'+cadreArray[i].id+'" class="cadresDivForPanel" ';
			//str+='onmouseover="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'block\';}" ';
			//str+='onmouseout="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'none\';}">';
			str+='<span id="cadreSpan_'+cadreArray[i].id+'" class="cadresCloseSpan" onclick="closeEventCadrePanelDiv(this.id,\''+type+'\')"> X </span>';			
			str+=cadreArray[i].name;
			str+='</div>';			
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';

		if(divElmt)
			divElmt.innerHTML=str;

		var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
		var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");

		if(eventCadreDivHeadElmt && eventCadreDivBodyElmt)
		{
			eventCadreDivHeadElmt.innerHTML="";
			eventCadreDivBodyElmt.innerHTML="";
		}
		
		closeCadresInfoDiv("cadreLevelDivId_"+type+"_anc");
	}

	function deleteEventCadreFromPanelDiv(id,type)
	{
		var status=confirm("Are you sure want to delete this organizer from this event");
		if(status==false)
			return;
		
		var cadreId = id.substring(id.indexOf('_')+1,id.length);

		var cadreArray = new Array();		
		if(type == 'event')
			cadreArray = eventCadresArray;
		if(cadreArray.length == 0)
			return;

		for(var i=0; i<cadreArray.length;i++ )
		{ 
				if(cadreArray[i].id==cadreId)
					cadreArray.splice(i,1); 
		}
		
		if(type == 'event')
			eventCadresArray = cadreArray;

		var cDivId = document.getElementById(cadreId+"_div");
		var parent = cDivId.parentNode;
		parent.removeChild(cDivId);	
	}
	function updateSelectedEvent(type)
	{			
		var results = YAHOO.lang.JSON.parse(jsonStr);
		
		var jsObj;
		/*if(type == "impEvent")
		{
			selectedEventObj.userEventsId = results.userEventsId;
			selectedEventObj.eventName = results.title;
			selectedEventObj.startDate = sDayobj.day+'/'+sDayobj.month+'/'+sDayobj.year;
			selectedEventObj.endDate = eDayobj.day+'/'+eDayobj.month+'/'+eDayobj.year;
			selectedEventObj.startTimeHrs = sDayobj.hours;
			selectedEventObj.startTimeMin = sDayobj.minutes;
			selectedEventObj.endTimeHrs = eDayobj.hours;
			selectedEventObj.endTimeMin = eDayobj.minutes;
			selectedEventObj.locationType=results.locationType;
			selectedEventObj.locationId=results.locationId;
			selectedEventObj.desc = results.description;
			selectedEventObj.organizers = results.organizers;
			selectedEventObj.actionPlans = results.actionPlans;
			selectedEventObj.isDeleted = "NO";
			selectedEventObj.task="createEvent";
		}
		else if(type == "impDate")
		{
			selectedDateObj.importantDateId = results[0].importantDateId;
			selectedDateObj.eventId = results[0].eventId;
			selectedDateObj.eventType = results[0].eventType;
			selectedDateObj.eventName = results[0].title;
			if(results[0].startDate)
				selectedDateObj.startDate = startDateObj.day+'/'+startDateObj.month+'/'+startDateObj.year;
			if(results[0].endDate)
				selectedDateObj.endDate = endDateObj.day+'/'+endDateObj.month+'/'+endDateObj.year;
			selectedDateObj.desc = results[0].importance;
			selectedDateObj.frequency = results[0].frequency;
			selectedDateObj.isDeleted = "NO";
			selectedDateObj.task="createImpDateEvent";
		}*/
		if(type == 'impEvent')
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
			
			selectedEventObj.userEventsId = results.userEventsId;
			selectedEventObj.eventName=eventNameVal;
			selectedEventObj.startDate=startDateVal;
			selectedEventObj.endDate=endDateVal;
			selectedEventObj.startTimeHrs=startTimeHrsVal;
			selectedEventObj.startTimeMin=startTimeMinVal;					
			selectedEventObj.endTimeHrs=endTimeHrsVal;
			selectedEventObj.endTimeMin=endTimeMinVal;	
			selectedEventObj.locationType=results.locationType;
			selectedEventObj.locationId=results.locationId;
			selectedEventObj.desc=descVal;
			selectedEventObj.organizers = eventCadresArray;
			selectedEventObj.actionPlans = actionPlanArray;
			selectedEventObj.isDeleted = "NO";
			selectedEventObj.task="updateCreateEvent";

			jsObj = selectedEventObj;
		}
		else if(type == 'impDate')
		{
			var ImpeventNameVal = document.getElementById("ImpeventNameText").value;
			var ImpstartDateVal = document.getElementById("ImpStartDateText").value;		
			var ImpendDateVal = document.getElementById("ImpEndDateText").value;		
			var ImpDescVal = document.getElementById("ImpdescTextArea").value;

			selectedDateObj.importantDateId = results[0].importantDateId;
			selectedDateObj.eventId = results[0].eventId;
			selectedDateObj.eventType = results[0].eventType;
			selectedDateObj.eventName = ImpeventNameVal;
			selectedDateObj.startDate = ImpstartDateVal;
			selectedDateObj.endDate = ImpendDateVal;
			selectedDateObj.desc = ImpDescVal;
			selectedDateObj.frequency = results[0].frequency;
			selectedDateObj.isDeleted = "NO";
			selectedDateObj.task="updateImpDateEvent";

			jsObj = selectedDateObj;			
		}		
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		
		callAjax(jsObj,url);
	}

	function deleteSelectedEvent(type,eId)
	{		
		var jsObj;
			if(type == 'impEvent')
			{	
				jsObj = selectedEventObj;
				jsObj.userEventsId = eId;
				jsObj.task="deleteEvent";			
				var status=confirm("Are you sure want to delete this Important Event");
			}
			else if(type == 'impDate')
			{
				jsObj = selectedDateObj;
				jsObj.importantDateId = eId;
				jsObj.task="deleteImpDate";
				var status=confirm("Are you sure want to delete this Important Date");
			}		
			if(status==false){
 				return;
			}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/deleteEventAction.action?"+rparam;		
		callAjax(jsObj,url);		
	}

	function showSelectedDateEvent(elmtId,eType,taskType)
	{	
		//var mainEventCalendarNavigator = new YAHOO.widget.CalendarNavigator(mainEventCalendar);
		
		var eid = elmtId.substring((elmtId.indexOf('_')+1),elmtId.length);
           
		var jsObj={
					eventId:eid,
					currentDay:dateObj.dateVal,
					currentMonth:dateObj.monthVal,
					currentYear:dateObj.yearVal,
					eventType:eType,	
					taskType:taskType,					
					task:"showSelectedDateEvent"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/showImpDateEvent.action?"+rparam;		
		callAjax(jsObj,url);
	}

	function showUnEditableSelectedDateEvent(elmtId,eType,taskType)
	{
		var eid = elmtId.substring((elmtId.indexOf('_')+1),elmtId.length);

		var jsObj={
					eventId:eid,
					currentDay:dateObj.dateVal,
					currentMonth:dateObj.monthVal,
					currentYear:dateObj.yearVal,
					eventType:eType,	
					taskType:taskType,					
					task:"showSelectedDateEvent_nonEditable"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/showImpDateEvent.action?"+rparam;		
		callAjax(jsObj,url);
	}
	function addCreatedEvent(results,jsObj)
	{	
	  if(jsObj.task == "updateCreateEvent") 
		{
			
			eventDateDialog.hide();
			alert("Event Updated Successfully");
			if(jsObj.task == "updateCreateEvent")
			var elmt = document.getElementById("ImpEvent_"+results.userEventsId);

			var parent = elmt.parentNode;
			parent.removeChild(elmt);
		}
		if(jsObj.task == "updateImpDateEvent")
		{
			eventDateDialog.hide();
			alert("Date Updated Successfully");
			if(jsObj.task == "updateImpDateEvent")
			var elmt = document.getElementById("ImpDate_"+results[0].importantDateId);
			
			var parent = elmt.parentNode;
			parent.removeChild(elmt);
		}
		
		var divElmt = document.createElement('div');
		/*if(results == "")
		{
			alert("No dates to display");
			return;
		}*/

		var str='';
		
		if(jsObj.task == "createEvent" || jsObj.task == "updateCreateEvent")
		{			
			str+='<div id="ImpEvent_'+results.userEventsId+'" class="eventSummaryDiv">';
			//str+='onmouseover="displayEditCloseIcons(this.id)" ';
			//str+='onmouseout="hideEditCloseIcons(this.id)">';
			str+='<span id="cadreSpan_'+results.userEventsId+'_cross" class="cadresCloseSpan" onclick="deleteSelectedEvent(\'impEvent\','+results.userEventsId+')"> X </span>';
			str+='<span id="cadreSpan_'+results.userEventsId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(\'ImpEvent_'+results.userEventsId+'\',\'\',\'impEvent\')">';
			str+='<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/pencil.png"/> </span>';

			//str+='<div id="ImpEvent_'+results.userEventsId+'" class="eventSummaryDiv" onclick="showSelectedDateEvent(this.id,\'\',\'impEvent\')">';
		}
		else if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
		{			
			str+='<div id="ImpDate_'+results[0].importantDateId+'" class="eventSummaryDiv">';	
			//str+='onmouseover="displayEditCloseIcons(this.id)" ';
			//str+='onmouseout="hideEditCloseIcons(this.id)">';
			str+='<span id="cadreSpan_'+results[0].importantDateId+'_cross" class="cadresCloseSpan" onclick="deleteSelectedEvent(\'impDate\','+results[0].importantDateId+')"> X </span>';
			str+='<span id="cadreSpan_'+results[0].importantDateId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(this.id,\'ImpDate_'+results[0].importantDateId+'\',\'impDate\')">';
			str+='<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/pencil.png"/> </span>';

			//str+='<div id="ImpDate_'+results[0].importantDateId+'" class="eventSummaryDiv" onclick="showSelectedDateEvent(this.id,\''+results[0].eventType+'\',\'impDate\')">';
		}

		if(jsObj.task == "createEvent" || jsObj.task == "updateCreateEvent")
		str+='<table onclick="showUnEditableSelectedDateEvent(\'ImpEvent_'+results.userEventsId+'\',\'\',\'impEvent\')">';
		else if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
		str+='<table onclick="showUnEditableSelectedDateEvent(\'ImpDate_'+results[0].importantDateId+'\',\''+results[0].importantDateId+'\',\'impDate\')">';		
		str+='<tr>';
		str+='<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/></td>';
		if(jsObj.task == "createEvent" || jsObj.task == "updateCreateEvent")
		{
			str+='<td>'+results.title+'</td>';
			if(results.startDate)
			{	
				var sDayobj = getDateTime(results.startDate);
				str+='<td> - </td>';
				str+='<td>'+sDayobj.day+'/'+sDayobj.month+'/'+sDayobj.year+'</td>';	
			}
			if(results.endDate)
			{
				var eDayobj = getDateTime(results.endDate);
				str+='<td> - </td>';
				str+='<td>'+eDayobj.day+'/'+eDayobj.month+'/'+eDayobj.year+'</td>';	
			}
		}
		else if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
		{
			var impDayobj = getDateTime(results[0].impDate);	
			str+='<td>'+results[0].title+'</td>';
			str+='<td> - </td>';
			str+='<td>'+impDayobj.day+'/'+impDayobj.month+'/'+impDayobj.year+'</td>';			
		}
		str+='<tr>';
		str+='</table>';
		str+='</div>';
		divElmt.innerHTML=str;
				
		var elmt;
		if(jsObj.task == "createEvent"  || jsObj.task == "updateCreateEvent")
			elmt = document.getElementById("cadreImpEventsBodyDiv");
		else if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
			elmt = document.getElementById("cadreImpDatesBodyDiv");

		if(elmt)
		{
			elmt.appendChild(divElmt);
		}
		
		if(jsObj.task == "createEvent")
		{	
			if(newEventDialog)
				newEventDialog.cancel();
		}
		else if(jsObj.task == "createImpDateEvent")
		{
			if(newDateDialog)
				newDateDialog.cancel();	
		}
		
		if(results.endDate)
		{		
			var startDate = new Date(sDayobj.month+"/"+sDayobj.day+"/"+sDayobj.year);
			var endDate = new Date(eDayobj.month+"/"+eDayobj.day+"/"+eDayobj.year);

			while(startDate<=endDate)
			{
				var eventColorRender = (startDate.getMonth()+1)+"/"+startDate.getDate()+"/"+startDate.getFullYear();
						
				if(existingDataCheck(eventColorRender,jsObj.task))
				{
					eventsRenderArr.push(eventColorRender);
				}
				else
				{
					removeByElement(datesRenderArr,eventColorRender);
					eventDateRenderArr.push(eventColorRender);
				}				
				startDate=new Date(startDate.getTime()+86400000);
			}			
			var renderValue=sDayobj.month+"/"+sDayobj.day+"/"+sDayobj.year+"-"+eDayobj.month+"/"+eDayobj.day+"/"+eDayobj.year;
		}
		else
		{			
			var impDate = new Date(impDayobj.month+"/"+impDayobj.day+"/"+impDayobj.year);
			var renderValue=(impDate .getMonth()+1)+"/"+impDate.getDate()+"/"+impDate.getFullYear();
				
			if(existingDataCheck(renderValue,jsObj.task))
				datesRenderArr.push(renderValue);
			else
			{
				removeByElement(eventsRenderArr,renderValue);
				eventDateRenderArr.push(renderValue);
			}
		}
		
		/*var renderObj = {
							renderDate:renderValue,
							renderType:jsObj.task 
						};
				
		
		renderDatesArr.push(renderObj);		*/

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
		mainEventCalendar.changePageEvent.subscribe(changePageHandler, mainEventCalendar, true); 
		mainEventCalendar.render(); 
	}
	
	function changePageHandler(type,args,obj)
	{		
		var current = args[1];
		var date = new Date(current);
		var month = date.getMonth();
		var year = date.getFullYear();	
		
		dateObj.dateVal = '1';
		dateObj.monthVal = date.getMonth();
		dateObj.yearVal = date.getFullYear();

		var jsObj={
					monthVal:month,
					yearval:year,
					task:'nextMonthEvents'
				  };

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getNextMonthDatesEvents.action?"+rparam;		
		callAjax(jsObj,url);
		
	}
	function showDateCal(id,val)
	{			
		//var dataL = val.substring(2,4)+'/'+val.substring(0,2)+'/'+val.substring(4,val.length);
		
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

	function setEndDateText(text)
	{
		
	}
	function removeElementsArray(arr)
	{	
		if(!arr && arr.length == 0)
			return;
		
		for(var i=0;arr.length!=0;i++)
			arr.pop();
		
	}
	function buildNewEventPopup()
	{		
		
		removeElementsArray(eventCadresArray);
		removeElementsArray(actionCadresArray);
		removeElementsArray(actionPlanArray);
		
		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
				
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newEventDiv');
		divChild.setAttribute('class','yui-skin-sam');
		
		var eventStr='';
		eventStr+='<div class="hd">Enter New Event Details...</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<div id="errorMsgDiv"></div>';
		eventStr+='<div id="eventDetailsDiv"><table class="selectedDateEvent">';
		eventStr+='<tr>';
		eventStr+='<th>Event Name</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="eventNameText" name="eventNameText"/></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Date</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="startDateText_new" readonly="readonly" name="startDateText" value="'+date+'" onfocus="showDateCal(\'startDateText_new_Div\',this.value)"/></div>';
		eventStr+='<div id="startDateText_new_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';
		eventStr+='<th>End Date</th>';
		eventStr+='<td><div><input type="text" id="endDateText_new" readonly="readonly" name="endDateText" value="'+date+'" onfocus="showDateCal(\'endDateText_new_Div\',this.value)"/></div>';
		eventStr+='<div id="endDateText_new_Div" class="tinyDateCal"></div></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Start Time</th>';
		eventStr+='<td>';
		eventStr+='<select id="startTimeHrs" name="startTimeText" class="timeSelect">';		
		for(var i=0;i<=23;i++)
		{
			if(i==9)
				eventStr+='<option selected="selected">'+i+'</option>';
			eventStr+='<option>'+i+'</option>';
		}
		eventStr+='</select>';

		eventStr+='<select id="startTimeMin" name="startTimeText" class="timeSelect">';
		eventStr+='<option>00</option>';		
		eventStr+='<option>15</option>';
		eventStr+='<option selected="selected">30</option>';
		eventStr+='<option>45</option>';		
		eventStr+='</select>';
		eventStr+='</td>';
		eventStr+='<th>End Time</th>';
		eventStr+='<td>';
		eventStr+='<select id="endTimeHrs" name="endTimeText" class="timeSelect">';
		for(var i=0;i<=23;i++)
		{
			if(i==17)
				eventStr+='<option selected="selected">'+i+'</option>';
			eventStr+='<option>'+i+'</option>';
		}
		eventStr+='</select>';
		
		eventStr+='<select id="endTimeMin" name="startTimeText" class="timeSelect">';
		eventStr+='<option>00</option>';		
		eventStr+='<option>15</option>';
		eventStr+='<option selected="selected">30</option>';
		eventStr+='<option>45</option>';		
		eventStr+='</select>';

		eventStr+='</td>';
		eventStr+='</tr>';
				
		
		eventStr+='<tr>';
		eventStr+='<th>Location Level</th>';
		eventStr+='<td colspan="3"><select id="cadreLevelField" name="cadreLevel" onchange="getStateList()">';
		eventStr+='	<option	 value="0">Select Level</option>';		
		eventStr+='	<option  value="2">State</option>';	
		eventStr+='	<option  value="3">District</option>';
		eventStr+='	<option  value="4">Constituency</option>';	
		eventStr+='	<option  value="5">Mandal</option>';		
		eventStr+='	<option  value="6">Village</option>	';				
		eventStr+=' </select> <input type="hidden" name="cadreLevelValue" id="cadreLevelValue"></td>';
		eventStr+='</tr>';
		
		eventStr+='<tr>';
		eventStr+='<th>Location</th>';
		eventStr+='<td colspan="3">';
		eventStr+='	<select id="cadreLevelState" name="cadreLevelState" disabled = "true" class="cadreLevelSelect" onchange="setCadreValue(this.options[this.selectedIndex].value);	getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option> </option>';					
		eventStr+='	</select>'; 
 		eventStr+='	<select id="cadreLevelDistrict" class="cadreLevelSelect" name="cadreLevelDistrict" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);							getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option></option>';					
		eventStr+='	</select>'; 
				
		eventStr+='	<select id="cadreLevelConstituency" class="cadreLevelSelect" name="cadreLevelConstituency" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);					getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option></option>';					
		eventStr+='	</select> ';
		
		eventStr+='	<select id="cadreLevelMandal" class="cadreLevelSelect" name="cadreLevelMandal" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);			getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option></option>';					
		eventStr+='	</select> ';
		
		eventStr+='	<select id="cadreLevelVillage" class="cadreLevelSelect" name="cadreLevelVillage" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value); getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+=' 	<option></option>';					
		eventStr+='	</select>';
		eventStr+='</td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="descTextArea" name="descTextArea"></textarea></td>';
		eventStr+='</tr>';
		eventStr+='</table>';
		
		eventStr+='<table class="selectedDateEvent" width="100%">';
		eventStr+='<tr>';
		eventStr+='<th>Organizers</th>';
		eventStr+='<td><div id="eventCadresDiv"><span style="color:#CFCFCF">No Organizers For Event</span></div></td>';		
		eventStr+='</tr>';
		eventStr+='<tr>';
		eventStr+='<td colspan="2" align="right"><span class="buttonSpan" onclick="javascript:{document.getElementById(\'cadreLevelDivId_event\').style.display=\'block\';}">Add Organizers</span></td>';		
		eventStr+='</tr>';
		eventStr+='</table>';
		eventStr+=getOrganisersString("event");
		
		eventStr+='<table class="selectedDateEvent" width="100%">';
		eventStr+='<tr>';
		eventStr+='<th><div id="actionPlanDiv_Label">Action Plans</div></th>';
		eventStr+='<td><div id="actionPlanDiv_Body"><span style="color:#CFCFCF">No Action Plans For Event</span></div></td>';		
		eventStr+='</tr>';
		eventStr+='<tr>';
		eventStr+='<td colspan="2" align="right"><span class="buttonSpan" onclick="javascript:{document.getElementById(\'cadreLevelDivId_eventAction\').style.display= \'block\'; removeElementsArray(actionCadresArray);}">Add Action Plan</span></td>';		
		eventStr+='</tr>';
		eventStr+='</table>';
		eventStr+='<div id="actionPlansDiv"></div>';
		eventStr+=createActionPlan("eventAction");

		/*eventStr+='<table class="cadreLevelDivClass">';
		eventStr+='<tr>';
		eventStr+='<th><div id="actionPlanLabelDiv"></div></th>';
		eventStr+='<td colspan="3"><div id="actionPlanDataDiv"></div></td>';		
		eventStr+='</tr>';
		eventStr+='</table>';*/


		/*eventStr+='<tr>';
		eventStr+='<td colspan="4" align="right"><a href="javascript:{}" onclick="createActionPlan()"> Create Action Plan</a></td>';		
		eventStr+='</tr>';
		eventStr+='<div id="actionDetailsDiv"></div>';*/
		
		eventStr+='</div></div>';

		divChild.innerHTML=eventStr;
		elmt.appendChild(divChild);
		
		if(newEventDialog)
			newEventDialog.destroy();

		newEventDialog = new YAHOO.widget.Dialog("newEventDiv",
				{
				  width : "700px",
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  y:100,
				  x:300,
				  y:700,
				  hideaftersubmit:true,
		          buttons : [ { text:"Create Event", handler:handleSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleCancel } ]
	             } ); 
		newEventDialog.render(); 
	}
	
	function createActionPlan(regTask)
	{		
		var actObj = {};
		var str='';
		str+='<div id="cadreLevelDivId_'+regTask+'" class="actioncadreLevelDivClass">';
		str+='<div id="cadreLevelDivId_'+regTask+'_inner" class="actioncadreLevelDivClassInner">';
		str+='<table class="selectedDateEvent" width="100%">';

		str+=getActionPlanString(actObj,"false","",regTask);
		str+='<tr>';
		str+='<th><div id="cadresForActionPlanDiv_Label"></div></th>';
		str+='<td>';
		str+='<div id="cadresForActionPlanDiv_Body"></div>';
		str+='</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td align="right"> <input type="button" value="Add" onclick="addActionPlanToEvent(\''+regTask+'\')"/></td>';
		str+='<td align="left"> <input type="button" id="cadreLevelDivId_'+regTask+'_anc" value="cancel" onclick="clearActionPlanDetails(this.id,\''+regTask+'\')"/></td>';
		str+='</tr>';
		str+='</table>';		
		str+='</div></div>';

		return str;
	}

	function getActionPlanString(actObj,status,index,regTask)
	{
		var str=''; 
		str+='<tr>';
		str+='<th> Action Plan</th>';
		if(status == "false")
			str+='<td><input type="text" id="actionPlanText" size ="60" onfocus="expandActionPlansDiv(\''+regTask+'\')"  name="actionPlanText"/></td>';
		else if(status == "true")
			str+='<td><input type="text" id="actionPlanText_'+index+'" size ="60" name="actionPlanText" value="'+actObj.actionPlan+'"/></td>';
		str+='<td  align="right">';			
		str+='<a id="cadreLevelDivId_'+regTask+'_anc" href="javascript:{}" onclick="closeCadresInfoDiv(this.id,\''+regTask+'\')">Close</a>';
		str+='</td>';	
		str+='</tr>';
		str+='<tr>';
		str+='<th>Target Date</th>';
		str+='<td>';
		if(status == "false")
		{
			str+='<div><input type="text" id="actionTargetDateText" name="actionTargetDateText" onfocus="showDateCal(\'actionTargetDateText_Div\')"/></div>';
			str+='<div id="actionTargetDateText_Div" class="tinyDateCal"></div>';
		}
		else if(status == "true")
		{
			str+='<div><input type="text" id="actionTargetDateText_'+index+'" name="actionTargetDateText" onfocus="showDateCal(\'actionTargetDateText_'+index+'_Div\')" value="'+actObj.targetDate+'"/></div>';
			str+='<div id="actionTargetDateText_'+index+'_Div" class="tinyDateCal"></div>';
		}
		str+='</td>';
		str+='</tr>';				
		if(status == "false")
		{
			str+='<tr>';
			str+='<th>Organisers Level</th>';		
			str+='<td colspan="2">';
			str+='<input type="radio" name="sms_type" value="locationWise" onclick="getUserLocationData(this.value,\'action\')"/> Location Wise';	
			str+='<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value,\'action\')"/> Cadre Level Wise';
			str+='</td>';			
			str+='</tr>';
			
			str+='<tr>';		
			str+='<th align="left"><div id="action_region_type_Label"></div></th>';
			str+='<td align="left"><div id="action_region_type_Data"></div></td>';				
			str+='</tr><tr>';		
			str+='	<th align="left"><div id="action_region_select_Label">	</div></th>';
			str+='	<td align="left"><div id="action_region_select_Data">	</div>';
			str+=' <div id="action_region_submit"></div></td>';
			str+='</tr>';
			str+='<tr>';		
			str+='<th><div id="actionCadreDivHead"></div></th>';
			str+='<td colspan="3"><div id="actionCadreDivBody"></div></td>';		
			str+='</tr>';					
		}
		else if(status == "true")
		{
			str+='<tr>';
			str+='<th>Organisers</th>';
			str+='<td><select id="actionOrganisersSelect_'+index+'"><option> Select Organisers</option></select</td>';
			str+='</tr>';
		}
			

		return str;
	}
	
	function expandActionPlansDiv(type)
	{
		var str="cadreLevelDivId_"+type;
		
		cadreAnim = new YAHOO.util.Anim(str, {
			height: {
				 to:640
			} 
		}, 1, YAHOO.util.Easing.easeOut);

		cadreAnim.animate();

		var elmt = document.getElementById('cadreLevelDivId_'+type+'_anc');
		elmt.style.display = 'block';
	}

	function getOrganisersString(regTask)
	{
		var eventStr='';
		eventStr+='<div id="cadreLevelDivId_'+regTask+'" class="cadreLevelDivClass">';
		eventStr+='<div id="cadreLevelDivId_'+regTask+'_inner" class="cadreLevelDivClassInner">';
		eventStr+='<table class="selectedDateEvent" width="100%">';
		eventStr+='<tr>';
		eventStr+='<th>Select Organizers</th>';		
		eventStr+='<td colspan="2">';
		eventStr+='<input type="radio" name="sms_type" value="locationWise" onclick="javascript:{getUserLocationData(this.value,\''+regTask+'\')}"/> Location Wise';	
		eventStr+='<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value,\''+regTask+'\')"/> Cadre Level Wise';
		eventStr+='</td>';
		eventStr+='<td  align="right">';			
		eventStr+='<a id="cadreLevelDivId_'+regTask+'_anc" href="javascript:{}" onclick="clearActionPlanDetails(this.id,\''+regTask+'\')">Close</a>';
		eventStr+='</td>';			
		eventStr+='</tr>';		
		eventStr+='<tr>';		
		eventStr+='<th align="left"><div id="'+regTask+'_region_type_Label"></div></th>';
		eventStr+='<td align="left"><div id="'+regTask+'_region_type_Data"></div></td>';				
		eventStr+='</tr><tr>';		
		eventStr+='	<th align="left"><div id="'+regTask+'_region_select_Label">	</div></th>';
		eventStr+='	<td align="left"><div id="'+regTask+'_region_select_Data">	</div>';
		eventStr+=' <div id="'+regTask+'_region_submit"></div></td>';
		eventStr+='</tr>';
		eventStr+='<tr>';		
		eventStr+='<th><div id="'+regTask+'CadreDivHead"></div></th>';
		eventStr+='<td colspan="3"><div id="'+regTask+'CadreDivBody"></div></td>';		
		eventStr+='</tr>';		
		eventStr+='</table>';		
		eventStr+='</div></div>';

		return eventStr;

	}
	
	function clearActionPlanDetails(id,type)
	{
		if(document.getElementById('actionPlanText'))
			document.getElementById('actionPlanText').value = '';
		if(document.getElementById('actionTargetDateText'))
			document.getElementById('actionTargetDateText').value = '';
		
		if(document.getElementById("cadresForActionPlanDiv_Label"))
			document.getElementById("cadresForActionPlanDiv_Label").innerHTML='';
		if(document.getElementById("cadresForActionPlanDiv_Body"))
			document.getElementById("cadresForActionPlanDiv_Body").innerHTML='';
				
		removeElementsArray(actionCadresArray);
		removeElementsArray(actionPlanArray);

		closeCadresInfoDiv(id,type);
	}
	function closeCadresInfoDiv(id,type)
	{
		/*var ancElmt = document.getElementById(id);
		if(ancElmt)
			ancElmt.style.display = 'none';*/
		var elmtId = id.substring(0,(id.lastIndexOf('_')));
	
		if(!elmtId)
			alert("No ID present to close container");
				
		var myAnim = new YAHOO.util.Anim(elmtId, {
			height: {
				  to: 100
			} 
		}, 1, YAHOO.util.Easing.easeIn);

		myAnim.animate();

		cleanCadresInfoDiv(type);

		var animElmt = document.getElementById(elmtId);
		if(animElmt)
			animElmt.style.display = 'none';
	}
	function showEventForCadres(results,jsObj)
	{	
		var taskType = jsObj.displayType;
		var eventsHeadElmt = document.getElementById(taskType+"CadreDivHead");
		var eventsBodyElmt = document.getElementById(taskType+"CadreDivBody");
		
		if(eventsHeadElmt)
			eventsHeadElmt.innerHTML="Select Cadres";
		if(results.length == 0)
		{
			eventsBodyElmt.innerHTML="No Cadres Present";
			return;
		}
		

		var dtSource = new Array();

	/*	var str='';
		str+='<table>';
		for(var i in results)
		{		
			str+='<tr>';			
			if(jsObj.cadreLevel == 'locationLevel')
			{
				str+='<td><input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].id+'" value="'+results[i].name+'"/></td>';
				str+='<td><span id="'+results[i].id+'_span">'+results[i].name+'</span></td>';
			}
			else if(jsObj.cadreLevel == 'cadreLevel')
			{
				str+='<td><input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].cadreID+'" value="'+results[i].firstName+' '+results[i].middleName+' '+results[i].lastName+'"/></td>';
				str+='<td><span id="'+results[i].cadreId+'_span">'+results[i].firstName+' '+results[i].middleName+' '+results[i].lastName+'</span></td>';			
			}
			str+='</tr>';
		}
		str+='<tr>';
		str+='<td colspan="2" align="center"><input type="button" onclick="addCadresToEvent(\''+taskType+'\')" value="Select Cadres"></td>';
		str+='<tr>';
		str+='</table>';

		if(eventsBodyElmt)
			eventsBodyElmt.innerHTML=str;*/
		
		
		

		for(var i in results)
		{
			if(jsObj.cadreLevel == 'locationLevel')
			{
				var obj={
							check:'<input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].id+'" value="'+results[i].name+'"/>',
							name:results[i].name
						};
			}
			else if(jsObj.cadreLevel == 'cadreLevel')
			{
				var obj={
							check:'<input type="checkbox" name="eventCadreCheck" id="'+taskType+'_'+results[i].cadreID+'" value="'+results[i].firstName+' '+results[i].middleName+' '+results[i].lastName+'"/>',
							name:results[i].firstName+' '+results[i].middleName+' '+results[i].lastName
						};
			}
			dtSource.push(obj);
		}

		var myColumnDefs = [ 
			{key:"check",label:"Select",resizeable:true}, 
			{key:"name", sortable:true,label:"Name",resizeable:true}	            
		]; 
		
		var myDataSource = new YAHOO.util.DataSource(dtSource); 
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		myDataSource.responseSchema = { 
			fields: ["check","name"] 
		}; 
		var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 10
			})
		};
				
		cadreDataTable = new YAHOO.widget.DataTable(taskType+"CadreDivBody",myColumnDefs, myDataSource,myConfigs); 
		
		var cElmt = document.createElement('div');
		var str='';
		str+='<input type="button" onclick="selectCadresToEvent(\''+taskType+'\')" value="Select All">';	
		str+='<input type="button" onclick="deselectCadresToEvent(\''+taskType+'\')" value="Deselect All">';
		str+='<input type="button" onclick="addCadresToEvent(\''+taskType+'\')" value="Add Organizers">';
		cElmt.innerHTML=str;

		var parentElmt = document.getElementById(taskType+"CadreDivBody");
		parentElmt.appendChild(cElmt);
	}
	
	function selectCadresToEvent(type)
	{
		var elements = document.getElementsByTagName('input'); 
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="checkbox" && elements[i].name=="eventCadreCheck")  
			{
				elements[i].checked=true;				
			}
		}
	}
	function deselectCadresToEvent(type)
	{
		var elements = document.getElementsByTagName('input'); 
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="checkbox" && elements[i].name=="eventCadreCheck")  
			{
				elements[i].checked=false;				
			}
		}
	}

	function addCadresToEvent(type)
	{			
		var elements = document.getElementsByTagName('input'); 		
		
		var length;
		var array = new Array();
		if(type == 'event' || type == 'Editevent')
		{
			length = eventCadresArray.length;
			array  = eventCadresArray;
		}
		else if(type == 'action')
		{
			length = actionCadresArray.length;
			array = actionCadresArray;
		}
		
		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="checkbox" && elements[i].name=="eventCadreCheck" && elements[i].checked==true)
			{				
				var eid = elements[i].id.substring((elements[i].id.indexOf('_'))+1,elements[i].id.length);					
				var eventCadresObj ={
										id:eid,
										name:elements[i].value								
									};
				if((type == 'event' || type == 'Editevent')&& hasObjectInArray(array,eventCadresObj.id))
					eventCadresArray.push(eventCadresObj);
				else if(type == 'action' && hasObjectInArray(array,eventCadresObj.id))
					actionCadresArray.push(eventCadresObj);
			}			
		}
		

		if((type == 'event' && eventCadresArray.length == 0) ||(type == 'action' && actionCadresArray.length == 0))
		{
			alert("No cadres has been selected");
			return;
		}	
		else
		{			
			addCadresToEventPanel(type);
		}
	}
	
	function hasObjectInArray(array,cId)
	{
		var status=true;
		for(var i=0; i<array.length;i++ )
		{ 
			if(array[i].id==cId)
				status=false; 
		}	
		return status;
	}
	
	function addCadresToEventPanel(type)
	{		
		var cadreArray = new Array();
		var divElmt;
		if(type == 'Editevent' || type == 'Editaction')
		{
			addCadresToEditEventPanel(type);
		}

		
		if(type == 'event')
		{
			cadreArray = eventCadresArray;
			divElmt = document.getElementById("eventCadresDiv");
			if(cadreArray.length == 0 || !divElmt)
			return;

			var str = '';
			str+='<table class="selectedCadresDateEvent" width="100%">';			
			for(var i in cadreArray)
			{
				str+='<tr>';					
				str+='<td>';
				str+='<div id="cadreNameDiv_'+cadreArray[i].id+'" class="cadresDivForPanel"';
				//str+='onmouseover="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'block\';}"';
				//str+='onmouseout="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'none\';}">';
				str+='<span id="cadreSpan_'+cadreArray[i].id+'" class="cadresCloseSpan" onclick="closeEventCadrePanelDiv(this.id,\''+type+'\')"> X </span>';			
				str+=cadreArray[i].name;
				str+='</div>';			
				str+='</td>';
				str+='</tr>';
			}
			str+='</table>';
			if(divElmt)
				divElmt.innerHTML=str;
		}
		else if(type == 'action')
		{
			cadreArray = actionCadresArray;
			divElmt_Label = document.getElementById("cadresForActionPlanDiv_Label");
			divElmt_Body = document.getElementById("cadresForActionPlanDiv_Body");

			if(cadreArray.length == 0 || !divElmt_Label || !divElmt_Body)
			return;

			var labelstr='';
			labelstr+='Action Organizers';
			
			var str = '';	
			str+='<div>';	
			for(var i in cadreArray)
			{				
				str+='<div id="cadreNameDiv_'+cadreArray[i].id+'" class="cadresDivForPanel"';
				//str+='onmouseover="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'block\';}" ';
				//str+='onmouseout="javascript:{document.getElementById(\'cadreSpan_\'+id.substring(id.indexOf(\'_\')+1,id.length)).style.display=\'none\';}">';
				str+='<span id="cadreSpan_'+cadreArray[i].id+'" class="cadresCloseSpan" onclick="closeEventCadrePanelDiv(this.id,\''+type+'\')"> X </span>';			
				str+=cadreArray[i].name;
				str+='</div>';							
			}
			str+= '</div>';	

			if(divElmt_Label)
				divElmt_Label.innerHTML=labelstr;
			if(divElmt_Body)
				divElmt_Body.innerHTML=str;		
		}	
		
		cleanCadresInfoDiv(type);
		closeCadresInfoDiv("cadreLevelDivId_"+type+"_anc");
	}

	function cleanCadresInfoDiv(type)
	{
		var actionRegionSelectLabel = document.getElementById(type+'_region_select_Label');
		var actionRegionSelectData = document.getElementById(type+'_region_select_Data');
		var actionRegionSubmit = document.getElementById(type+'_region_submit');
		var actionRegionTypeLabel = document.getElementById(type+'_region_type_Label');
		var actionRegionTypeData = document.getElementById(type+'_region_type_Data');
		var eventCadreDivHeadElmt = document.getElementById(type+"CadreDivHead");
		var eventCadreDivBodyElmt = document.getElementById(type+"CadreDivBody");


		if(actionRegionSelectLabel && actionRegionSelectData && actionRegionSubmit && actionRegionTypeLabel && actionRegionTypeData && eventCadreDivHeadElmt && eventCadreDivBodyElmt)
		{
			actionRegionSelectLabel.innerHTML="";
			actionRegionSelectData.innerHTML="";
			actionRegionSubmit.innerHTML="";
			actionRegionTypeLabel.innerHTML="";
			actionRegionTypeData.innerHTML="";
			eventCadreDivHeadElmt.innerHTML="";
			eventCadreDivBodyElmt.innerHTML="";
		}
		/*if(type == 'action')
		{
			var actionPlanDivLabel = document.getElementById("cadresForActionPlanDiv_Label");
			var actionPlanDivBody = document.getElementById("cadresForActionPlanDiv_Body");
			if(actionPlanDivLabel && actionPlanDivBody)
			{
				actionPlanDivLabel.innerHTML='';
				actionPlanDivBody.innerHTML='';
			}
		}*/
	}
	
	function closeEventCadrePanelDiv(id,type)
	{
		var cadreArr,divElmt;

		if(type == 'event')
		{
			divElmt = document.getElementById("eventCadresDiv");			
			for(var i=0; i<eventCadresArray.length;i++ )
			{ 
				if(eventCadresArray[i].id==id.substring(id.indexOf('_')+1,id.length))
					eventCadresArray.splice(i,1); 
			} 	
			if(eventCadresArray.length == 0)
			divElmt.innerHTML='<span style="color:#CFCFCF">No Organizers For Event</span>';
		}
		else if(type == 'action')
		{
			divElmt = document.getElementById("actionPlansDiv");			
			for(var i=0; i<actionCadresArray.length;i++ )
			{ 
				if(actionCadresArray[i].id==id.substring(id.indexOf('_')+1,id.length))
					actionCadresArray.splice(i,1); 
			} 	
			if(actionCadresArray.length == 0)
			divElmt.innerHTML='<span style="color:#CFCFCF">No Organizers For Action Plan</span>';
		}
		
	var elmt = document.getElementById("cadreNameDiv_"+id.substring(id.indexOf('_')+1,id.length));
	
		if(elmt)
		elmt.style.display='none';
	}
	
	function addActionPlanForEditablePanel(type)
	{		
		actionPlansElmt = document.getElementById("actionPlanDiv_Body");
		var actionDivElmt = document.getElementById("actionDetailsDiv");

		var actionPlanValue = document.getElementById('actionPlanText').value;
		var targetDateValue = document.getElementById('actionTargetDateText').value;
		//var organisersSelectElmt = document.getElementById('actionOrganisersSelect');
		//var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;
		
		
		/*var actDemoArray = new Array();
		for(var a=0;a<actionPlanArray;a++)
		{
			var targetDateObj = getDateTime(actionPlanArray[a].targetDate);
			var actObj={
							action:actionPlanArray[a].action,
							targetDate:targetDateObj.day+'/'+targetDateObj.month+'/'+targetDateObj.year,
							actionPlanOrganizers:actionPlanArray[a].actionPlanOrganizers	
					   }
			actDemoArray.push(actObj);
		}*/
		
		var actionObj = {
							action:actionPlanValue,
							targetDate:targetDateValue,
							actionPlanOrganizers:actionCadresArray			
						};
		actionPlanArray.push(actionObj);

		
		
		var divStr='';
		divStr+='<div id="newImpDateDiv">';
		divStr+='<table class="selectedDateEvent" width="100%">';		
		divStr+='<tr>';		
		divStr+='<td>';		
		for(var i=0;i<actionPlanArray.length;i++)
		{
			divStr+='<div class="ImpDateDetailDiv" id="actionPlanDiv_'+i+'">';
			divStr+='<div id="actionPlanDiv_'+i+'_head" class="ImpDateDetailDivHead">';			
			divStr+='<span id="actionPlanClose_'+actionPlanArray[i].action+'" class="cadresCloseSpan" onclick="deleteActionPlanFormEventPanel(this.id,\''+type+'\')"> X </span>';	
			divStr+='<span id="actionPlanDiv_'+i+'_head" class="cadresCloseSpan" onclick="showCreatedActionPlan(this.id,\''+type+'\')"> Edit </span>';	
			divStr+=actionPlanArray[i].action+' - '+actionPlanArray[i].targetDate+'</div>';
			divStr+='<div id="actionPlanDiv_'+i+'_body" class="ImpDateDetailDivBody"></div>';
			divStr+='</div>';
		}

		divStr+='</td>';
		divStr+='</tr>';
		divStr+='</table>';
		divStr+='</div>';

		if(actionPlansElmt)
			actionPlansElmt.innerHTML = divStr;
	}
	function addActionPlanToEvent(type)
	{	
		var actionPlansElmt; 
		if(type == 'eventAction')
		{			
			actionPlansElmt = document.getElementById("actionPlanDiv_Body");
		}
		else if(type == 'EditactionPlan')
		{
			//actionPlansElmt = document.getElementById("actionPlanDiv_Body");
			addActionPlanForEditablePanel(type);
			return;
		}

		
		var actionDivElmt = document.getElementById("actionDetailsDiv");

		var actionPlanValue = document.getElementById('actionPlanText').value;
		var targetDateValue = document.getElementById('actionTargetDateText').value;
		//var organisersSelectElmt = document.getElementById('actionOrganisersSelect');
		//var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;
		
		var actionPlanArrLength = actionPlanArray.length;		
		var actionObj = {
							action:actionPlanValue,
							targetDate:targetDateValue,
							actionPlanOrganizers:actionCadresArray			
						};
		actionPlanArray.push(actionObj);

		var divStr='';
		divStr+='<div id="newImpDateDiv">';
		divStr+='<table class="selectedDateEvent" width="100%">';
		divStr+='<tr>';		
		divStr+='<td>';		
		for(var i=0;i<actionPlanArray.length;i++)
		{
			divStr+='<div class="ImpDateDetailDiv" id="actionPlanDiv_'+i+'">';
			divStr+='<div id="actionPlanDiv_'+i+'_head" class="ImpDateDetailDivHead">';			
			divStr+='<span id="actionPlanClose_'+actionPlanArray[i].action+'" class="cadresCloseSpan" onclick="deleteActionPlanFormEventPanel(this.id,\''+type+'\')"> X </span>';	
			divStr+='<span id="actionPlanDiv_'+i+'_head" class="cadresCloseSpan" onclick="showCreatedActionPlan(this.id,\''+type+'\')"> Edit </span>';	
			divStr+=actionPlanArray[i].action+' - '+actionPlanArray[i].targetDate+'</div>';
			divStr+='<div id="actionPlanDiv_'+i+'_body" class="ImpDateDetailDivBody"></div>';
			divStr+='</div>';
		}

		divStr+='</td>';
		divStr+='</tr>';
		divStr+='</table>';
		divStr+='</div>';

		if(actionPlansElmt)
			actionPlansElmt.innerHTML = divStr;
       
		if(actionDivElmt)
			actionDivElmt.innerHTML="";
		
		document.getElementById('actionPlanText').value = '';
		document.getElementById('actionTargetDateText').value = '';
		if(document.getElementById("cadresForActionPlanDiv_Label"))
			document.getElementById("cadresForActionPlanDiv_Label").innerHTML='';
		if(document.getElementById("cadresForActionPlanDiv_Body"))
			document.getElementById("cadresForActionPlanDiv_Body").innerHTML='';
		

		cleanCadresInfoDiv('action');
		closeCadresInfoDiv('cadreLevelDivId_'+type+'_anc');

	}

	function deleteActionPlanFormEventPanel(id,type)
	{			
		var actPlan = id.substring(id.indexOf('_')+1,id.length);
		var actionPlansDiv = document.getElementById("actionPlanDiv_Body");

		for(var i=0;i<actionPlanArray.length;i++)
		{
			if(actionPlanArray[i].actionPlan == actPlan)
				actionPlanArray.splice(i,1);
		}
		
		var elmt = document.getElementById(id);
		var parent = elmt.parentNode.parentNode;
		if(parent)
		{
			parent.innerHTML='';
			parent.style.display='none';
		}

		if(actionPlanArray.length == 0 && actionPlansDiv)
			actionPlansDiv.innerHTML='<span style="color:#CFCFCF">No Action Plans For Event</span>';	
	}
	function showCreatedActionPlan(divId,type)
	{
		var score = divId.indexOf('_');	
		var lastScore = divId.lastIndexOf('_');	
		var index = divId.slice(score+1,lastScore);
		var lastDiv = divId.substring(lastScore+1);	
		var firstDiv = divId.substring(0,lastScore);	

		for(var i in actionPlanArray)
		{
			var arrayElmt = document.getElementById("actionPlanDiv_"+i+"_body");
			arrayElmt.style.display = 'none';
		}

		var bodyElmt = document.getElementById(firstDiv+'_body');
		bodyElmt.style.display = 'block';
		
		var str='';
		str+='<table>';
		str = getActionPlanString(actionPlanArray[index],"true",index,"");
		str+='<tr>';
		str+='<td align="right"> <input type="button" value="Update" onclick="upDateActionPlanToEvent(\''+index+'\',\''+type+'\')"/></td>';
		str+='<td align="left"> <input type="button" value="cancel" onclick="hideActionBodyDiv(\''+firstDiv+'\')"/></td>';
		str+='</tr>';
		str+='</table>';

		if(bodyElmt)
			bodyElmt.innerHTML=str;
		
	}
	
	function upDateActionPlanToEvent(index,type)
	{
		var actionPlanValue = document.getElementById('actionPlanText_'+index).value;
		var targetDateValue = document.getElementById('actionTargetDateText_'+index).value;
		var organisersSelectElmt = document.getElementById('actionOrganisersSelect_'+index);
		var organisersValue = organisersSelectElmt.options[organisersSelectElmt.selectedIndex].value;
		
		var actionPlanObj= actionPlanArray[index];		
		
		actionPlanArray[index].actionPlan=actionPlanValue;
		actionPlanArray[index].targetDate=targetDateValue;
		actionPlanArray[index].organisers=[];
		
		var headElmt = document.getElementById('actionPlanDiv_'+index+'_head');
		var bodyElmt = document.getElementById('actionPlanDiv_'+index+'_body');
		
		var divStr='';
		divStr+='<span id="actionPlanClose_'+actionPlanArray[index].actionPlan+'" class="cadresCloseSpan" onclick="deleteActionPlanFormEventPanel(this.id,\''+type+'\')"> X </span>';	
		divStr+='<span id="actionPlanDiv_'+index+'_head" class="cadresCloseSpan" onclick="showCreatedActionPlan(this.id)"> Edit </span>';	
		divStr+=actionPlanValue+' - '+targetDateValue+'</div>';

		if(headElmt)
			headElmt.innerHTML=divStr;
		if(bodyElmt)
		{
			bodyElmt.innerHTML="";
			bodyElmt.style.display = 'none';
		}
	}
	function hideActionBodyDiv(div)
	{		
		var elmt = document.getElementById(div+'_body');
		elmt.innerHTML='';
		elmt.style.display = 'none';
	}
	function showNewEventPopup()
	{
		newEventDialog.show();		
	}

	function handleSubmit()
	{
		var eventNameVal = document.getElementById("eventNameText").value;
		var startDateVal = document.getElementById("startDateText_new").value;
		var endDateVal = document.getElementById("endDateText_new").value;
		
		var startTimeHrs = document.getElementById("startTimeHrs");
		var startTimeHrsVal = startTimeHrs.options[startTimeHrs.selectedIndex].text;
		var startTimeMin = document.getElementById("startTimeMin");
		var startTimeMinVal = startTimeMin.options[startTimeMin.selectedIndex].text;
		
		var endTimeHrs = document.getElementById("endTimeHrs");		
		var endTimeHrsVal = endTimeHrs.options[endTimeHrs.selectedIndex].text;
		var endTimeMin = document.getElementById("endTimeMin");
		var endTimeMinVal = endTimeMin.options[endTimeMin.selectedIndex].text;

		var descVal = document.getElementById("descTextArea").value;
		descVal = removeEnterStrokeForString(descVal);
		 
		//validation code
		if(eventNameVal == '' ){
			document.getElementById("errorMsgDiv").innerHTML = "Please Enter Event";
			
		}
		else if ( /[^A-Za-z\d]/.test(eventNameVal)) 
		{
		  document.getElementById("errorMsgDiv").innerHTML = " Event Name cannot allow special characters & numbers";
		}
		else if(descVal == '')
		{
		  document.getElementById("errorMsgDiv").innerHTML = "Please Enter Description";
		}
		else if(startDateVal > endDateVal){
 
		document.getElementById("errorMsgDiv").innerHTML = "Please select A valid date for End Date";
		}
        else {
		var loctionLevelFieldElmt = document.getElementById("cadreLevelField");
		locationLevelFieldval = loctionLevelFieldElmt.options[loctionLevelFieldElmt.selectedIndex].text.toUpperCase();		
		
		var locationValueElmt;
		if(locationLevelFieldval == "STATE")
			locationValueElmt = document.getElementById("cadreLevelState");
		else if(locationLevelFieldval == "DISTRICT")
			locationValueElmt = document.getElementById("cadreLevelDistrict");
		else if(locationLevelFieldval == "CONSTITUENCY")
			locationValueElmt = document.getElementById("cadreLevelConstituency");
		else if(locationLevelFieldval == "MANDAL")
			locationValueElmt = document.getElementById("cadreLevelMandal");
		else if(locationLevelFieldval == "VILLAGE")
			locationValueElmt = document.getElementById("cadreLevelVillage");

		var locationValue = locationValueElmt.options[locationValueElmt.selectedIndex].value;
		

		//var organisersVal = document.getElementById("organisersText").value;

		var actionPlansVal='';
		
		selectedEventObj.userEventsId="";
		selectedEventObj.eventName=eventNameVal;
		selectedEventObj.startDate=startDateVal;
		selectedEventObj.endDate=endDateVal;
		selectedEventObj.startTimeHrs=startTimeHrsVal;
		selectedEventObj.startTimeMin=startTimeMinVal;					
		selectedEventObj.endTimeHrs=endTimeHrsVal;
		selectedEventObj.endTimeMin=endTimeMinVal;
		selectedEventObj.locationType=locationLevelFieldval;
		selectedEventObj.locationId=locationValue;
		selectedEventObj.desc=descVal;
		selectedEventObj.organizers=eventCadresArray;
		selectedEventObj.actionPlans=actionPlanArray;
		selectedEventObj.isDeleted="NO";
		selectedEventObj.task="createEvent";

		var rparam ="task="+YAHOO.lang.JSON.stringify(selectedEventObj);
		var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		

		callAjax(selectedEventObj,url);
		}
	}

	function handleCancel()
	{
		this.cancel();
	}	

	

	function buildNewImpDatePopup()
	{
		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();

				
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newImpDateDiv');
		divChild.setAttribute('class','yui-skin-sam');
		
		var eventStr='';
		
		eventStr+='<div class="hd">New Date</div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<div id="errorMesgDIV"><font color="red"</font></div>';
		eventStr+='<table>';
		eventStr+='<tr>';
		eventStr+='<th>Important Date Title</th>';
		eventStr+='<td colspan="3"><input type="text" size="50" id="ImpeventNameText" name="ImpeventNameText"/></td>';
		eventStr+='</tr>';

		eventStr+='<tr>';
		eventStr+='<th>Important Date</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="ImpStartDateText_new" value="'+date+'" name="ImpStartDateText" readonly="readonly" onfocus="showDateCal(\'ImpStartDateText_new_Div\')"/></div>';
		eventStr+='<div id="ImpStartDateText_new_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';		
		eventStr+='</tr>';
	
		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="ImpdescTextArea" name="ImpdescTextArea"></textarea></td>';
		eventStr+='</tr>';		

		eventStr+='<tr>';
		eventStr+='<th>Repeat Frequency</th>';
		eventStr+='<td>';
		eventStr+='<select id="repeatFreqSelect" class="timeSelect" onchange="showEndDateText(this.options[this.selectedIndex].text)">';
		eventStr+='<option value="No Repeat">No Repeat</option>';
		eventStr+='<option value="Yearly">Yearly</option><option value="Monthly">Monthly</option><option value="Weekly">Weekly</option></select></td>';
		eventStr+='<th>Repeat Until</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="ImpEndDateText_new" readonly="readonly" value="'+date+'" name="ImpEndDateText" disabled="true" onfocus="showDateCal(\'ImpEndDateText_new_Div\')"/></div>';
		eventStr+='<div id="ImpEndDateText_new_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';
		eventStr+='</tr>';		

		eventStr+='</table>';
		eventStr+='</div>';

		divChild.innerHTML=eventStr;
		elmt.appendChild(divChild);

		if(newDateDialog)
			newDateDialog.destroy();
		
		newDateDialog = new YAHOO.widget.Dialog("newImpDateDiv",
				{ width : "600px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  x:200,
				  y:700,
				  hideaftersubmit:true,
		          buttons : [ { text:"Create New Date", handler:handleImpDateSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleImpDateCancel } ]
	             } ); 
		newDateDialog .render(); 
	}
	
	function showEndDateText(val)
	{
		var txtElmt = document.getElementById('ImpEndDateText_new');
		if(val == "No Repeat")
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
		var ImpstartDateVal = document.getElementById("ImpStartDateText_new").value;		
		var ImpendDateVal = document.getElementById("ImpEndDateText_new").value;		
		var ImpDescVal = document.getElementById("ImpdescTextArea").value;
		ImpDescVal = removeEnterStrokeForString(ImpDescVal);

		var repeatFreqElmt = document.getElementById("repeatFreqSelect");
		repeatFreqVal =  repeatFreqElmt.options[repeatFreqElmt.selectedIndex].value;
			
			//validation code for Imp dates
		    if(ImpeventNameVal == '')
		    {
			document.getElementById("errorMesgDIV").innerHTML = '<font color="red">Please Enter Important Date Title </font>';
			return;
			}
			else if ( /[^A-Za-z\d\s]/.test(ImpeventNameVal))
			{ 
				document.getElementById("errorMesgDIV").innerHTML = '<font color="red"> Important Date Title cannot allow special characters & Numbers</font>';
				return;
			}
			else if(ImpDescVal == '')
			{
			   document.getElementById("errorMesgDIV").innerHTML = '<font color="red">Please Enter Description</font>';
			  return;
			}

			 if(repeatFreqVal == "No Repeat")
			{
				ImpendDateVal = ImpstartDateVal;
			}
		
		selectedDateObj.importantDateId="";
		selectedDateObj.eventId="";
		selectedDateObj.eventType="";
		selectedDateObj.eventName=ImpeventNameVal;
		selectedDateObj.startDate=ImpstartDateVal;	
		selectedDateObj.endDate=ImpendDateVal;
		selectedDateObj.desc=ImpDescVal;
		selectedDateObj.frequency=repeatFreqVal;
		selectedDateObj.isDeleted=repeatFreqVal;
		selectedDateObj.task="createImpDateEvent";
	
				
		var rparam ="task="+YAHOO.lang.JSON.stringify(selectedDateObj);
		var url = "<%=request.getContextPath()%>/createEventAction.action?"+rparam;		
		callAjax(selectedDateObj,url);
	}
	
	function handleImpDateCancel()
	{
		this.cancel();
	}

	function displayEditCloseIcons(id)
	{		
		document.getElementById('cadreSpan_'+id.substring(id.indexOf('_')+1,id.length)+'_cross').style.display='block';
		document.getElementById('cadreSpan_'+id.substring(id.indexOf('_')+1,id.length)+'_edit').style.display='block';
	}

	function hideEditCloseIcons(id)
	{
		document.getElementById('cadreSpan_'+id.substring(id.indexOf('_')+1,id.length)+'_cross').style.display='none';
		document.getElementById('cadreSpan_'+id.substring(id.indexOf('_')+1,id.length)+'_edit').style.display='none';
	}

	function showInitialImpEventsAndDates(eventsarr,type,task)
	{			
		var divElmt;
		if(type == "impEvents")
			var elmt = document.getElementById("cadreImpEventsBodyDiv");
		else if(type == "impDates")
			var elmt = document.getElementById("cadreImpDatesBodyDiv");
		
		if(elmt)
			elmt.innerHTML='';
		
		
		for(var i in eventsarr)
		{			
			
			if(type == "impEvents" && eventsarr[i].startDate)
			{	
				var sDayobj = getDateTime(eventsarr[i].startDate);				
				var startDayStr = sDayobj.day;
				var startMonStr = sDayobj.month;
				var startYearStr = sDayobj.year;
				var startTimeHrs = sDayobj.hours;
				var startTimeMin = sDayobj.minutes;
			}
			else if(type == "impDates" && eventsarr[i].impDate)
			{	
				var sDayobj = getDateTime(eventsarr[i].impDate);				
				var startDayStr = sDayobj.day;
				var startMonStr = sDayobj.month;
				var startYearStr = sDayobj.year;
				var startTimeHrs = sDayobj.hours;
				var startTimeMin = sDayobj.minutes;
			}

			if(eventsarr[i].endDate)
			{
				var eDayobj = getDateTime(eventsarr[i].endDate);				
				var endDayStr = eDayobj.day;
				var endMonStr = eDayobj.month;
				var endYearStr = eDayobj.year;
				var endTimeHrs = eDayobj.hours;
				var endTimeMin = eDayobj.minutes;
			}
			
			divElmt = document.createElement('div');						

			if(i%2!=0)
				divElmt.setAttribute('style','background-color:#EBF5FF;');
			else
				divElmt.setAttribute('style','background-color:#F9FCFF;');
		
			var str='';
			if(type == "impEvents")
			{
				str+='<div id="ImpEvent_'+eventsarr[i].userEventsId+'" class="eventSummaryDiv">';				
				str+='<span id="cadreSpan_'+eventsarr[i].userEventsId+'_cross" class="cadresCloseSpan" onclick="deleteSelectedEvent(\'impEvent\','+eventsarr[i].userEventsId+')"> X </span>';
				str+='<span id="cadreSpan_'+eventsarr[i].userEventsId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(\'ImpEvent_'+eventsarr[i].userEventsId+'\',\'\',\'impEvent\')">';
				/*str+='<span id="cadreSpan_'+eventsarr[i].userEventsId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(\'ImpEvent_'+eventsarr[i].userEventsId+'\',\'\',\''+eventsarr[i].eventType+'\',\'\',\'impEvent\')">';*/
				str+='<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/pencil.png"/> </span>';
			}			
			else if(type == "impDates")
			{
				str+='<div id="ImpDate_'+eventsarr[i].importantDateId+'" class="eventSummaryDiv">';	
				str+='<span id="cadreSpan_'+eventsarr[i].importantDateId+'_cross" class="cadresCloseSpan" onclick="deleteSelectedEvent(\'impDate\','+eventsarr[i].importantDateId+')"> X </span>';
				str+='<span id="cadreSpan_'+eventsarr[i].importantDateId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(\'ImpDate_'+eventsarr[i].importantDateId+'\',\''+eventsarr[i].eventType+'\',\'impDate\')">';
				str+='<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/pencil.png"/> </span>';
				
			}

			if(type == "impEvents")
			str+='<table onclick="showUnEditableSelectedDateEvent(\'ImpEvent_'+eventsarr[i].userEventsId+'\',\'\',\'impEvent\')">';
			else if(type == "impDates")
			str+='<table onclick="showUnEditableSelectedDateEvent(\'ImpDate_'+eventsarr[i].importantDateId+'\',\''+eventsarr[i].eventType+'\',\'impDate\')">';
			str+='<tr>';
			str+='<td><img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/></td>';
			str+='<td>'+eventsarr[i].title+'</td>';	
			str+='<td> - </td>';	
			str+='<td>'+startDayStr+'-'+startMonStr+'-'+startYearStr+'</td>';

			if(type == "impEvents" && eventsarr[i].endDate)
			{
				str+='<td> to </td>';	
				str+='<td>'+endDayStr+'-'+endMonStr+'-'+endYearStr+'</td>';
			}
			str+='<tr>';
			str+='</table>';
			str+='</div>';
			divElmt.innerHTML=str;		
				
			if(elmt)
			{
				elmt.appendChild(divElmt);
			}

			
			
			if(type == "impEvents" && eventsarr[i].endDate)
			{	
				var startDate = new Date(startMonStr+"/"+startDayStr+"/"+startYearStr);
				var endDate = new Date(endMonStr+"/"+endDayStr+"/"+endYearStr);
				
				while(startDate<=endDate)
				{
					var eventColorRender = (startDate.getMonth()+1)+"/"+startDate.getDate()+"/"+startDate.getFullYear();
							
					if(existingDataCheck(eventColorRender,type))
					{
						eventsRenderArr.push(eventColorRender);
					}
					else
					{
						removeByElement(datesRenderArr,eventColorRender);
						eventDateRenderArr.push(eventColorRender);
					}				
					startDate=new Date(startDate.getTime()+86400000);
				}	
								
			}
			else
			{	
				var startDate = new Date(startMonStr+"/"+startDayStr+"/"+startYearStr);
				var renderValue=(startDate.getMonth()+1)+"/"+startDate.getDate()+"/"+startDate.getFullYear();
				
				if(existingDataCheck(renderValue,type))
					datesRenderArr.push(renderValue);
				else
				{
					removeByElement(eventsRenderArr,renderValue);
					eventDateRenderArr.push(renderValue);
				}
			}
			
		
			renderStack();	
						
		}
	}

	function existingDataCheck(date,type)
	{		
		var testArray = new Array();
		var status = true;
		if(type == "impEvents" || type == "createEvent")		
			testArray = datesRenderArr;
		else if(type == "impDates" || type == "createImpDateEvent")
			testArray = eventsRenderArr;
				
		var arrLength = testArray.length;
		
		if(arrLength == 0)
			return true;
		for(var i=0;i<arrLength;i++)
		{
			if(testArray[i] == date)
				status = false;
		}
		
		return status;
	}

	function removeByElement(arrayName,arrayElement)
	{		
		for(var i=0; i<arrayName.length;i++ )
		{ 
			if(arrayName[i]==arrayElement)
				arrayName.splice(i,1); 
		 } 		
	}


	function renderStack()
	{	
		for(var i in eventsRenderArr)
		{
			mainEventCalendar.addRenderer(eventsRenderArr[i], mainEventCalendar.renderCellStyleHighlight1);			
		}
		for(var j in datesRenderArr)
		{
			mainEventCalendar.addRenderer(datesRenderArr[j], mainEventCalendar.renderCellStyleHighlight2);			
		}
		for(var k in eventDateRenderArr)
		{
			mainEventCalendar.addRenderer(eventDateRenderArr[k], mainEventCalendar.renderCellStyleHighlight3);			
		}

		
		mainEventCalendar.render(); 
	}
	
	function subscribe()
	{		
		var url = "<%=request.getContextPath()%>/userSubscribePartyImpDates.action?";
		var jsObj={
				task:"subscribe"
			  }		
			callAjax(jsObj,url);
	}
	
</script>
</head>
<body>
	<div id="errorMessageDIV" class="yui-skin-sam">
		<div id="smsErrorPopupDiv">
		</div>
	</div>

	<div id="CadreManagementHeaderMain">
		<DIV id="cadreMgmtDesc">
			<UL>
				<LI>Add cadre and manage cadre efficiently</LI>
				<LI>Identify the locations having less cadre and strong cadre</LI>
				<LI>Communicate with your cadre efficiently</LI>
				<LI>Maintain Associate Groups(Mahila, Youth, Trade Unions)</LI>
				<LI>Plan and organize events with Event Planning Tool</LI>
				<LI>Plan booth level teams, feedback teams and campaign teams</LI>
			</UL>
		</DIV>		
	</div>
	<div id="cadreManagementMainDiv" class="yui-skin-sam">		
		<div id="myDialog" class="yui-skin-sam"> 			
		</div> 
	</div>
	
	

	<div id="cadreSMSGroupsMainDiv">
		
		<div id="cadreGroupsMainDiv"  class="yui-skin-sam">
			<div id="cadreGroupsHeadDiv" class=".yui-widget-hd">Upload Your Cadre</div>
			<div id="cadreGroupsBodyDiv">Cadre Upload feature enables you to upload cadre details by submitting the Excel file containing cadres.</div>
            <div id="cadreGroupsFooterDiv">
			   <span><a href="genericModuleDataUpload.action" id="sendSMSAnc">Start Upload</a></span>
			</div>
		</div>
		<div id="cadreSMSMainDiv">
			<div id="cadreSMSHeadDiv">Cadre SMS</div>
			<div id="cadreSMSBodyDiv">Cadre SMS feature enables the user to send SMS to the cadres, based on the location and cadre level.</div>
			<div id="cadreSMSFooterDiv">
				<span><a href="cadreRegisterPageAction.action?cadreId=0&windowTask=new" id="sendSMSAnc">Add Cadre</a></span>
				<span><a href="javascript:{}" id="sendSMSAnc" onclick="buildSearchPagePopup('Search')">Cadre Search</a></span>
				<span><a href="javascript:{}" id="sendSMSAnc" onclick="buildSearchPagePopup('Sms')">Cadre SMS</a></span>
			</div>
		</div>
	</div>
	<div id="cadreDetailsMainDiv">
		<div id="regionLevelCadreDiv">
			<div id="regionLevelCadreDivHead">Region Level Cadres</div>
			<div id="regionLevelCadreDivBody"></div>
			<div id="cadreStrengthAreasDiv" class="cadreInfoDiv"><a href="cadreReportAction.action" class="ancLink">Know your Cadre Strength areas</a></div>
		</div>		
		
	</div>		
	<div id="cadreTeamsMainDiv">
		<div id="cadreTeamsAccordianDiv"></div>
	</div>
	<div id="cadreEventsCalMainDiv">
		<div id="cadreDatesYUICalDiv">
		
		</div>
		<div id="cadreEventsDetailsDivMain">
			<span class="impInfoSpan">
				<a href="javascript:{}" onclick="subscribe()">
					<span id="subscribePartyDates">
						<c:choose>
							<c:when test="${sessionScope.USER.subscribePartyImpDate == 'ALL'}">
								<c:out value="Unsubscribe Party Imp Dates" />
							</c:when>
							<c:otherwise>
								<c:out value="Subscribe Party Imp Dates" />
							</c:otherwise>
						</c:choose>
					</span>
				</a>
			</span>
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
								<span id="newEventSpan"><a href="javascript:{}" onclick="buildNewImpDatePopup()">Create New Date</a></span>							
							</div>
							<div id="cadreImpDatesBodyDiv"> </div>
						</div>
					</td>
					<td width="50%">
						<div id="cadreImpEventsDiv">
							<div id="cadreImpEventsHeadDiv">
								<span style="float: left;">Important Events</span>
								<span id="newEventSpan"><a href="javascript:{}" onclick="buildNewEventPopup()">Create New Event</a></span>
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
		
		buildCalendarControl();	
		//buildSMSPopup();
		//buildNewEventPopup();
		//buildNewImpDatePopup();
		//var sendSMSButton = new YAHOO.widget.Button("sendSMSAnc"); 
		//sendSMSButton.on("click", showSendSMSPopup);

		
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
						userEventsId:"${impEvent.userEventsId}",
						title:"${impEvent.title}",
						startDate:"${impEvent.startDate}",
						endDate:"${impEvent.endDate}",
						description:"${impEvent.description}"
					};
					impEvents.push(ob);
		</c:forEach>			
		
		showInitialImpEventsAndDates(impEvents,'impEvents',"");
		

		var impDates = new Array();
		<c:forEach var="impDate" items="${cadreManagementVO.userImpDates}" >			
				var ob =
					{
						importantDateId:"${impDate.importantDateId}",
						title:"${impDate.title}",
						impDate:"${impDate.impDate}",
						importance:"${impDate.importance}",
						eventType:"${impDate.eventType}"
					};
					impDates.push(ob);
		</c:forEach>
		
		showInitialImpEventsAndDates(impDates,'impDates',"");
		renderStack();

		initializeCadreManagement();
		
	</script>

	
</body>
</html>