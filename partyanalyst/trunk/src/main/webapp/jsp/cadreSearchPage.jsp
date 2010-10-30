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
						<div id="rangeRegionsSelectElmtDiv" style="width:600px;"></div>
				         
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
						
						<input type="button" onclick="getCadresResults('search')" value="Search"/>
						
						<c:if test="${windowTask == 'Sms'}">
						<input type="button" onclick="getCadresResults('sms')" value="Send SMS"/>
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
		<div id="searchResultsDiv_body">
			<div id="smsResult"></div>
			<div id="searchResult" style="display:none"></div>
		</div>
		<div id="searchResultsDiv_footer"></div>
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