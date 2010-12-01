<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Search</title>

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
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
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


<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type = "text/javascript">
function populateLocations(val,source)
{	
	REPORTLEVEL = val;
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");
	//var boothNoTextEl = document.getElementById("boothNoText");
	//var hiddenEl = document.getElementById("cadreLevelValue");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s"); 
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var hamletFieldEl = document.getElementById("hamletField_s");		
	if(source != 'onLoad')
	{
		clearOptionsListForSelectElmtId("stateField_s");
		clearOptionsListForSelectElmtId("districtField_s");
		clearOptionsListForSelectElmtId("constituencyField_s");
		clearOptionsListForSelectElmtId("mandalField_s");
		clearOptionsListForSelectElmtId("hamletField_s");
		getLocationHierarchies(1, "statesInCountry", "cadreSearch", "stateField_s", null, null, null);
	}	
	
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';		
	var value = val;
	if(value == 1)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		
	} else if(value == 2)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
	} else if(value == 3)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';					
	} else if(value == 4)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';			
	} else if(value == 5)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 6)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 7)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 8)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 9)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row6El.style.display == 'none')
			row6El.style.display = '';			
	}
			 
}
</script>
<style>
.btnClass
{
	background-image:url(images/icons/indexPage/swasthic_body.png);
	background-repeat:repeat-x;
	border:1px solid #ADADAD;
	color:#244565;
	font-weight:bold;
	padding:5px;
	text-decoration:none;
}	

</style>
</head>
<body>
	
	<div id="cadreSearchHeading"><center>
        <table border="0" cellpadding="0" cellspacing="0">          
          <tr>
            <td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
            <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">Cadre ${windowTask}</span></div></td>
            <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
          </tr>
        </table>    	
     </center></div>
	<center>
	<div id="CadreSearchMain">
		<div id="basicCadresSearch">
			<table width="100%" class="cadreSearchInputTable">				
				
				<tr>
					<td colspan="2"><div id="errorMsgDiv"></div></td>
				</tr>

				<tr>
					<th align="left"><font color="#FF0000"> * </font> ${windowTask} Based On</th>                
					<td align="left">
						<input type="radio" name="searchBasedRadio" onClick="searchBased(this.value)"  checked="checked" value="location"/> Cadre Location  
						<input type="radio" name="searchbasedRadio" onClick="searchBased(this.value)" value="level"/> Cadre Region Level
					</td>
				</tr>

				<tr>
					<th align="left"><font color="#FF0000"> * </font>Select Range</th>                
					<td align="left">
						<div id="rangeRegionsRadioElmtDiv"></div>
						<div id="rangeRegionsSelectElmtDiv" style="width:600px;">
						
							<table  width="100%" >
								<tr id="row1" style="display:none;">
									<td width="200"><s:label for="stateField_s" id="stateLabel" theme="simple" value="%{getText('STATE')}" /></td>
									<td>
										<s:select id="stateField_s" cssClass="regionSelect" theme="simple" list="stateList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','cadreSearch','districtField_s','cadreSearch', 'null')"></s:select>
									</td>
								</tr>
								<tr id="row2" style="display:none;">
									<td width="200"><s:label for="districtField_s" id="districtLabel" theme="simple" value="%{getText('DISTRICT')}" /></td>
									<td>
										<s:select id="districtField_s" cssClass="regionSelect" theme="simple" list="districtList" listKey="id" listValue="name" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'cadreSearch','constituencyField_s','cadreSearch')"></s:select>
									</td>
								</tr>
								<tr id="row3" style="display:none;">
									<td width="200"><s:label for="constituencyField_s" id="constituencyLabel" theme="simple"  value="%{getText('CONSTITUENCY')}"/></td>
									<td>
										<s:select id="constituencyField_s" theme="simple" cssClass="regionSelect" list="constituencyList" listKey="id" listValue="name" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'cadreSearch','mandalField_s','cadreSearch')"></s:select>
									</td>
								</tr>								
								<tr id="row4" style="display:none;">
									<td width="200"><s:label for="mandalField" id="mandalLabel" theme="simple"  value="%{getText('subRegions')}" /></td>
									<td>
										<s:select id="mandalField_s" cssClass="regionSelect" theme="simple" list="{}" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,'cadreSearch','null','cadreSearch','constituencyField_s')"></s:select>
									</td>
								</tr>					
								<tr id="row5" style="display:none;">
									<td width="200"><s:label for="hamletField_s" id="mandalLabel" theme="simple"  value="%{getText('wardOrHamlet')}" /></td>
									<td>
										<s:select id="hamletField_s" cssClass="regionSelect" list="{}" theme="simple" listKey="id" listValue="name"></s:select>
									</td>
								</tr>
								<tr id="row6" style="display:none;">
									<td width="200">Booth No</td>
									<td>
										<s:select id="boothField_s" cssClass="regionSelect" theme="simple" list="{}" listKey="id" listValue="name"></s:select>
									</td>
								</tr>
							</table>
						</div>
				         
					</td>
				</tr>
				
				<tr>
					<th align="left"><font color="#FF0000"> * </font> Cadre Type</th>
					<td align="left">
						<input type="radio" name="cadreTypeRadio" value="all" checked="checked" onClick="getCriteriaValue(this.value,'searchCriteria')"/> All
						<input type="radio" name="cadreTypeRadio" value="active" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Active Cadre
						<input type="radio" name="cadreTypeRadio" value="normal" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Normal Cadre
					</td>
				</tr>
				<tr>
					<th align="left"><font color="#FF0000"> * </font> Gender</th>
					<td align="left">
						<input type="radio" name="genderTypeRadio" value="allGenders" checked="checked" onClick="getCriteriaValue(this.value,'searchCriteria')"/> All
						<input type="radio" name="genderTypeRadio" value="male" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Male
						<input type="radio" name="genderTypeRadio" value="female" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Female
					</td>
				</tr>			
				</table>
			</div>

			<div id="filterOptionsCadresSearch">
				<table width="100%" class="cadreSearchInputTable">
				<tr>
					<th valign="top" align="left"><font color="#FF0000"> * </font> Social Status</th>
					<td valign="top" align="left">
						<input type="checkbox" id="socialStatusCheck" value="socialStatus" onclick="showSocialStatus(this)"/> Include Cadre Social Status
						<div>	
							<table>
								<tr>
									<td><input type="checkbox" name="socialStatus" onclick="addSocialStatusValue(this)" disabled="disabled" value="resevation"/> Reservation Category</td>
									<td>
										<select class="searchcriteriaSelect" multiple="multiple" size="3" onclick="changeSocialStatus(this)" id="socialStatus_resevation" disabled="disabled">
											
										</select>
									</td>
								</tr>						
								<tr>
									<td><input type="checkbox" name="socialStatus" onclick="addSocialStatusValue(this)" disabled="disabled" value="education"/> Education</td>
									<td>
										<select class="searchcriteriaSelect" multiple="multiple" size="3" id="socialStatus_education" onclick="changeSocialStatus(this)" disabled="disabled">
											
										</select>
									</td>
								</tr>						
								<tr>
									<td><input type="checkbox" name="socialStatus" onclick="addSocialStatusValue(this)" disabled="disabled" value="occupation"/> Occupation</td>
									<td>
										<select class="searchcriteriaSelect" multiple="multiple" size="3" id="socialStatus_occupation" onclick="changeSocialStatus(this)" disabled="disabled">
											
										</select>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>			
				
				<tr>
					<th valign="top" align="left"><div id="searchCriteria_label"></div></th>
					<td valign="top" align="left">
						<div id="searchCriteria_data">
							
						</div>
					</td>
				</tr>
				
				<tr>
					<th valign="top" align="left"><div id="searchPerform_label">Perform ${windowTask} with</div></th>
					<td valign="top" align="left">
						<div id="searchPerform_label">
							<input type="radio" name="performSearch" value="and" checked="checked" onclick="javascript:{PERFORMSEARCH = this.value}">all of these words
							<input type="radio" name="performSearch" value="or" onclick="javascript:{PERFORMSEARCH = this.value}">Any one of these words
						</div>
					</td>
				</tr>
					 
				<tr>
					<td colspan="2" align="center"><span id="accessRegion_button"></span></td>
				</tr>
				<tr>
					<th><span id="smsTxtArea_label"></span></th>
					<td><span id="smsTxtArea_data"></span></td>
				</tr>			
				<tr>
					<th><span id="includeUserName_label"></span></th>
					<td><span id="includeUserName_data"></span></td>
				</tr>
				<tr>				
					<td colspan="2" align="center"><span id="smsSendSpan_button"></span></td>
				</tr>				
			</table>
		</div>
		<c:if test="${windowTask == 'Sms'}">
		<div>
			<table width="100%" class="cadreSearchInputTable">	
			<tr>
				<th><font color="#FF0000"> * </font> SMS Text</th>
				<td>
					<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText('smsTextArea','maxcount',200)" ></textarea></div> 
					<div id="limitDiv">
					<table><tr>
					<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>
					<td style="width:50%;"><div>Should not exceed 200 chars</div></td>
					</tr></table>
					</div>
				</td>
			</tr>
			<tr>
				<th>Include User Name</th>
				<td>
					<input type="radio" id="include_user_name" onclick="javascript:{SMSINCLUDECADRENAME = this.value}" name="include_user_name" value="YES" /> Yes
					<input type="radio" id="no_user_name" onclick="javascript:{SMSINCLUDECADRENAME = this.value}"name="include_user_name" value="NO" checked="checked"/> No    
				</td>
			</tr>
			</table>
		</div>		
		</c:if>
		<div id="searchButtonDiv">
			<table >
				
				<tr>					
					<td align="center">
						
						<input type="button" class="btnClass" onclick="getCadresResults('search')" value="Search"/>
						
						<c:if test="${windowTask == 'Sms'}">
						<input type="button" class="btnClass" onclick="getCadresResults('sms')" value="Send SMS"/>
						</c:if>
						
					</td>
					<td align="center">
						<a href="javascript:{}" onclick="expandFilterOptions()"/>Add filter to ${windowTask}
					</td>
				</tr>
			</table>	
		</div>
	</div>
	</center>
	<div id="smsDialogBox" class="yui-skin-sam"></div>
	<div id="searchResultsDiv_main" class="yui-skin-sam">
		<div id="searchResultsDiv_head"></div>
		<div id="resultsCount" style="margin-left:30px;color:#707070;font-weight:bold;font-size:13px;text-align:center;"></div>
		<div id="searchResultsDiv_body">
			<div id="smsResult"></div>
			
			<div id="searchResult" style="display:none"></div>
		</div>
		<div id="searchResultsDiv_footer" style="text-align:center;"></div>
	</div>

	<script type="text/javascript">		
		
		 <%
		
		ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
		String exceptionMsg = rb.getString("exceptionMsg");

		%>

		errorMsglabel = '<%=exceptionMsg%>';
						

		CLICKTYPE = '${windowTask}';
		partyHasCommitees = '${isPartyCommitee}';
		partyHasCamps = '${isTrainingCamps}';
		partyHasSkills = '${isCadreSkills}';
		
        
		<c:forEach var="social" items="${socialStatus}">
			var obj = {
			           	id:'${social.id}',
						name:'${social.name}'
					  };
					socialStatus.push(obj);
		</c:forEach>
		<c:forEach var="edu" items="${eduStatus}">
			var obj = {
			           	id:'${edu.id}',
						name:'${edu.name}'
					  };
					eduStatus.push(obj);
		</c:forEach>
		<c:forEach var="committe" items="${partyCommitteesList}">
			var obj = {
			           	id:'${committe.id}',
						name:'${committe.name}'
					  };
					partyCommitte.push(obj);
		</c:forEach>
		<c:forEach var="skill" items="${cadreSkillsList}">
			var obj = {
			           	id:'${skill.id}',
						name:'${skill.name}'
					  };
					cadreSkills.push(obj);
		</c:forEach>
		<c:forEach var="camps" items="${partyTrainingCampsList}">
			var obj = {
			           	id:'${camps.id}',
						name:'${camps.name}'
					  };
					partyTrainingCamps.push(obj);
		</c:forEach>
		<c:forEach var="occupation" items="${occupationsList}">
			var obj = {
			           	id:'${occupation.id}',
						name:'${occupation.name}'
					  };
					occupations.push(obj);
		</c:forEach>
				
		buildselectBoxes();
		getLocationWiseRangeDetails();
	</script>
</body>
</html>