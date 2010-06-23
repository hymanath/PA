<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

<!-- YUI Dependency files (End) -->
	

	<link href="styles/styles.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/indexPage/indexPage.js" ></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js" ></script>
	<script type="text/javascript" src="js/c/commonUtilityScript.js" ></script>
	
<!-- 
    <script type="text/javascript">
    var smsDialog, newEventDialog, newDateDialog,eventDateDialog,mainEventCalendar,dateCalendar,cadreDataTable,cadreAnim,jsonStr;

    function removeElementsArray(arr)
	{	
		if(!arr && arr.length == 0)
			return;
		
		for(var i=0;arr.length!=0;i++)
			arr.pop();
		
	}
	function buildNewEventPopup()
	{		
		
	/*	removeElementsArray(eventCadresArray);
		removeElementsArray(actionCadresArray);
		removeElementsArray(actionPlanArray);
		*/
		
		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
		alert(date);
				
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newEventDiv');
		divChild.setAttribute('class','yui-skin-sam');
		
		var eventStr='';
		eventStr+='<div class="hd">Enter New Event Details...</div> ';
		eventStr+='<div class="bd">'; 
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
		eventStr+='	<select id="cadreLevelState" name="cadreLevelState" disabled = "true" class="cadreLevelSelect" onchange="setCadreValue(this.options[this.selectedIndex].value);										getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option> </option>';					
		eventStr+='	</select>'; 

 		eventStr+='	<select id="cadreLevelDistrict" class="cadreLevelSelect" name="cadreLevelDistrict" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);							getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option></option>';					
		eventStr+='	</select>'; 
				
		eventStr+='	<select id="cadreLevelConstituency" class="cadreLevelSelect" name="cadreLevelConstituency" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);					getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option></option>';					
		eventStr+='	</select> ';
		
		eventStr+='	<select id="cadreLevelMandal" class="cadreLevelSelect" name="cadreLevelMandal" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);								getCadreLevelValues(this.name,this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">';
		eventStr+='	<option></option>';					
		eventStr+='	</select> ';
		
		eventStr+='	<select id="cadreLevelVillage" class="cadreLevelSelect" name="cadreLevelVillage" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value)">';
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
		eventStr+='<td colspan="2" align="right"><span class="buttonSpan" onclick="javascript:{document.getElementById(\'cadreLevelDivId_eventAction\').style.display=\'block\';removeElementsArray(actionCadresArray);}">Add Action Plan</span></td>';		
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

		alert(eventStr);
		newEventDialog = new YAHOO.widget.Dialog("newEventDiv",
				{ width : "800px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  x:200,
				  y:100,
				  hideaftersubmit:true,
		          buttons : [ { text:"Create Event", handler:handleSubmit, isDefault:true }, 
	                          { text:"Cancel", handler:handleCancel } ]
	             } ); 
		newEventDialog.render();
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
	</script>
-->
	</head>
	<body>
		<div id="dashboard_main">

			<div id="dashboard_layout_main">						
			</div>
			<div id="cadreManagementMainDiv" class="yui-skin-sam">		
		<div id="myDialog" class="yui-skin-sam"> 			
		</div> 
	</div>
	
	
			
			<div id="dashBoardLeftlayoutDiv">
				<div id="humanImgDiv">
					<table>
						<tr><td colspan="4"><div><img src="images/icons/indexPage/human.jpg"/></div></td></tr>	
						<tr>	
							<td width="25%" id="uploadPhotoImage"></td>
							<td><a href="javascript:{}" class="profileAnc">Upload Photo</a></td>
						</tr>
						<tr>
							<td id=""><img src="images/usergroups/search.gif" style="padding-left:10px;"/></td>
							<td><a href="javascript:{}" class="profileAnc">View Profile</a></td> 
						</tr>
					</table>					
				</div>		
				<div id="noticeBoard">
					<div id="noticeBoard_head">Announcements</div>
					<div id="noticeBoard_body"></div>
					<div id="noticeBoard_footer"></div>
				</div>
				
			</div>

			<div id="dashBoardCenterlayoutDiv">
				<div id="dashBoardCenterlayout_header">		
					<table width="100%" style="width:100%;" cellspacing="0" cellpadding="0" border="0">
					<tr>
					<td style="width:35px;"><img src="images/icons/indexPage/swasthic_left.png"/></td>
					<td class="centerSwasthicImage" style="vertical-align: middle;">	
						<table width="100%" style="width:100%;">
							<tr>
								<td align="left"><font class="welcomeUserFont">Welcome </font> <font class="welcomeUserFont" style="color:#4B74C6">User </font></td>
								<td align="right">
									<!--<img src="images/icons/indexPage/clock.png" height="25px"/>-->
									<div id="todayDate"></div>
								</td>
							</tr>
						</table>						

					</td>
					<td style="width:35px;"><img src="images/icons/indexPage/swasthic_right.png"/></td>					
					</table>
				</div>
				<div id="dashBoardCenterlayout_body">
					<table width="100%" style="width:100%;height:100%">
						<tr>
							<td style="vertical-align:top;width:70%;">
								<table width="100%" style="width:100%;">
									<tr>
										<td>
											<div id="impEventsDiv_main">
												<div id="impEventsDiv_head">
													<table><tr>
														<td><img src="images/icons/indexPage/cal.png"/></span></td>
														<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Today's Event</span></td>
													</tr></table>
												</div>
												<div id="impEventsDiv_body">
												<span class="dashBoardCenterContentBody" style="color:#4B74C6">You have ${eventCount} event(s) scheduled today</span>
													<ul class="dashBoardContentList">
													<c:forEach var="impEvents" items="${cadreManagementVO.userEvents}" >
														<li><c:out value="${impEvents.eventDisplayTitle}" /></li>	
													</c:forEach>										
													</ul>
												</div>

												<div id="impEventsDiv_footer" style="text-align:right">
													<span class="dashBoardLinks"><a href="cadreManagementAction.action">View All</a></span>
													<span class="dashBoardLinks"><a href="javascript:{}" onclick="buildNewEventPopup()">Create</a></span>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><div></div></td>
									</tr>
									<tr>
										<td>
											<div id="impDatesDiv_main">
												<div id="impDatesDiv_head">
													<table><tr>
														<td><img src="images/icons/indexPage/cal.png"/></span></td>
														<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Imp Dates</span></td>
													</tr></table>
												</div>
												<div id="impDatesDiv_body">
													<span class="dashBoardCenterContentBody" style="color:#4B74C6">You have ${impDateCount} Imp date(s) scheduled today</span>
													<ul class="dashBoardContentList">
														<c:forEach var="impDates" items="${cadreManagementVO.userImpDates}" >
														<li><c:out value="${impDates.title}" /></li>	
													</c:forEach>													
													</ul>
												</div>
												<div id="impDatesDiv_footer" style="text-align:right">
													<span class="dashBoardLinks"><a href="cadreManagementAction.action">View All</a></span>
													<span class="dashBoardLinks"><a href="javascript:{}" onclick="buildImpDatePopup()">Create</a></span>
												</div>
											</div>
										</td>
									</tr>	
									<tr>
										<td>
											<div id="cadresDiv_main">
												<div id="cadresDiv_head">
													<table><tr>
														<td><img src="images/icons/indexPage/group_icon.png"/></span></td>
														<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">Cadres Info</span></td>
													</tr></table>
												</div>
												<div id="cadresDiv_body">
													<span class="dashBoardCenterContentBody" style="color:#4B74C6"></span>
													<ul class="dashBoardContentList">
													<c:forEach var="cadreLevels" items="${cadreManagementVO.cadresByCadreLevel}" >
														<li>${cadreLevels.key} Level Cadres - ${cadreLevels.value} </li>
													</c:forEach>													
													</ul>
												</div>
												<div id="cadresDiv_footer" style="text-align:right">
													<span class="dashBoardLinks">View All</span>
													<span class="dashBoardLinks">
														<span><img src="images/icons/indexPage/sms_cell.png"/></span>
														<span>Send SMS</span>
													</span>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
							
							<td style="vertical-align:top;border-left:1px solid #cdcdcd;">

								<div id="staticDataView_main" class="yui-skin-sam">
									<div id="staticDataView_head"></div>
									<div id="staticDataView_body">
										
										<!--<ul id="dashboardRightLayoutList">
											<li>View Your State</li>
											<li>View Your District</li>
											<li>View Your Constituency</li>
											<li>View Your Mandal</li>											
										</ul>-->
									</div>
									<div id="staticDataView_footer"></div>
								</div>

								<div id="usergroups_main">
									<div id="usergroups_head">
										<table><tr>
											<td><img src="images/icons/indexPage/group_icon.png"/></span></td>
											<td style="vertical-align:center;"><span class="dashBoardCenterContentHeader">User Groups</span></td>
										</tr></table>
									</div>
									<div id="usergroups_body">
										<font style="color:#4B74C6;padding-left:22px;">Total Groups Created : <c:out value= "${userGroups + systemGroups}" />  </font>
										<ul class="dashBoardContentList">
											<li> System Groups - ${systemGroups} </li>
											<li> User Groups - ${userGroups}</li>
										</ul>
									</div>
									<div id="usergroups_footer" style="text-align:right">
										<span class="dashBoardLinks">View All</span>
										<span class="dashBoardLinks">Create</span>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div id="dashboard_reportsNav_main">
				<table id="dashboard_main_table" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="vertical-align:top;width:250px;">
							<div id="dashboard_leftNav" style="margin-top: 35px;">								
								<ul id="pa_reportsList_main">
									<li id="partyAnalysisListItem">										
										<div id="partyAnalysis_div" class="reportGroupClass reportGroupClassSelected" onclick="showReportsInCarousel('partyAnalysis')">Party Analysis</div>
									</li>
									<li id="politicianAnalysisListItem">
										<div id="politicianAnalysis_div" class="reportGroupClass" onclick="showReportsInCarousel('politicianAnalysis')">Politician Analysis</div>
									</li>
								</ul>
							</div>
						</td>
						<td style="vertical-align:top;">
							<div id="dashboard_centerContent">
								<div id="pa_reports_carousel" class="yui-skin-sam" style="width:100%">
									<ul>
										<li> 
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Party Performance Report</div>
												<div class="pa_reports_body">													
													<div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report1.png"/></div>
													<div style="height:120px">
														Party Performance Report gives a detailed election results analysis for a party on its performance in an election.
														This report mainly focus on complete party election results of won/lost details in different positions, which include first,second,third upto Nth position dtails and election results in those positions.
													</div>
													<div style="float:right;padding-top:4px;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>											
											</div>
										</li>
										<li>
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Election Comparison Report</div>
												<div class="pa_reports_body">
													<div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report2.png"/></div>
													<div style="height:120px">
													Elections Comparison Report gives a glance of compared election results for a party participated any two elections in a detailed view.This report mainly provides a overview  for a user to know wheather the party improved/lost its performance in selected present year when compared to selected previous year.
													</div>
													<div style="float:right;padding-top:4px;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>											
											</div> 
										</li>
										<li>
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head">Party Results Report</div>
												<div class="pa_reports_body">
													 <div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report3.png"/></div>
													<div style="height:120px">
													 Party Results Report gives overall picture for a party in different types of elections like assembly/parliament/zptc/mptc/municipality in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.
													</div>
													 <div style="float:right;padding-top:4px;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>											
											</div> 
										</li>
										<li> 
											<div class="reports_carousel_div_class">
												<div class="pa_reports_head"> Party Influence Report</div>
												<div class="pa_reports_body"> 
													<div style="margin-top:10px"><img src="images/icons/indexPage/partyanalysis/report4.png"/></div>
													<div style="height:120px">
													Party Influence Report compares the election results among one party and newly establish party.The resuilts are compared among all the districts wise and the mandals wise.
													</div>
													<div style="float:right;padding-top:4px;"><a href="javascript:{}" class="viewReportAnc">View</a></div>
												</div>									
											</div> 
										</li>
									</ul>
								</div> 
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>


		
		<script type="text/javascript">
		
		<c:forEach var="mlaConstituencies"  items="${mlaConstituenciesList}" >
			var ob={
						id:'${mlaConstituencies.id}',
						name:'${mlaConstituencies.name}'
					};
			indexPageMainObj.mlaConstituencies.push(ob);	
		</c:forEach>

		<c:forEach var="mpConstituencies"  items="${mpConstituenciesList}" >
			var ob={
						id:'${mpConstituencies.id}',
						name:'${mpConstituencies.name}'
					};
			indexPageMainObj.mpContituencies.push(ob);	
		</c:forEach>
		
		
		initializeIndexPage();
		</script>
			
</body>
</html>