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

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
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
            <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">Cadre Search</span></div></td>
            <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
          </tr>
        </table>    	
     </center></div>
	<div id="CadreSearchMain">
		<table width="100%" id="cadreSearchInputTable">
            <tr>
                <td colspan="2"><div id="errorMsgDiv"></div></td>
            </tr>
        	<tr>
            	<th><font color="#FF0000"> * </font>Access level</th>                
                <td>
                	<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="1"/> Country		
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="2"/> State
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="3"/> District
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="4"/> Constituency
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="5"/> Mandal
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="6"/> Village                    
                </td>
            </tr>
            <tr>
                <th>
					<span id="accessRegion_label"></span>
				</th>                
                <td>
					<span id="accessRegion_data">
					
					</span>
			</tr>
			
			<tr>
				<th valign="top"><font color="#FF0000"> * </font> Social Status</span></th>
				<td valign="top">
					<input type="checkbox" id="socialStatusCheck" value="socialStatus" onclick="showSocialStatus(this)"/> Include Cadre Social Status
					<div>	
						<table>
							<tr>
								<td><input type="checkbox" name="socialStatus" onclick="addSocialStatusValue(this)" disabled="disabled" value="resevation"/> Reservation Category</td>
								<td>
									<select class="searchcriteriaSelect" onchange="changeSocialStatus(this)" id="socialStatus_resevation" disabled="disabled">
										
									</select>
								</td>
							</tr>						
							<tr>
								<td><input type="checkbox" name="socialStatus" onclick="addSocialStatusValue(this)" disabled="disabled" value="education"/> Education</td>
								<td>
									<select class="searchcriteriaSelect" id="socialStatus_education" onchange="changeSocialStatus(this)" disabled="disabled">
										
									</select>
								</td>
							</tr>						
							<tr>
								<td><input type="checkbox" name="socialStatus" onclick="addSocialStatusValue(this)" disabled="disabled" value="occupation"/> Occupation</td>
								<td>
									<select class="searchcriteriaSelect" id="socialStatus_occupation" onchange="changeSocialStatus(this)" disabled="disabled">
										
									</select>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			
			<tr>
				<th><font color="#FF0000"> * </font> Cadre Type</span></th>
				<td>
					<input type="radio" name="cadreTypeRadio" value="all" checked="checked" onClick="getCriteriaValue(this.value,'searchCriteria')"/> All
					<input type="radio" name="cadreTypeRadio" value="active" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Active Cadre
					<input type="radio" name="cadreTypeRadio" value="normal" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Normal Cadre
				</td>
			</tr>			
			
			<tr>
				<th><div id="searchType_label"></div></th>
				<td>
					<div id="searchType_data">
						
					</div>
				</td>
			</tr>

			<tr>
				<th valign="top"><div id="searchCriteria_label"></div></th>
				<td valign="top">
					<div id="searchCriteria_data">
						
					</div>
				</td>
			</tr>
			
			<tr>
				<th valign="top"><div id="searchPerform_label">Perform Search By</div></th>
				<td valign="top">
					<div id="searchPerform_label">
						<input type="radio" name="performSearch" value="and" checked="checked" onclick="javascript:{PERFORMSEARCH = this.value}">And
						<input type="radio" name="performSearch" value="or" onclick="javascript:{PERFORMSEARCH = this.value}">Or
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

	<div id="searchResultsDiv_main" class="yui-skin-sam">
		<div id="searchResultsDiv_head"></div>
		<div id="searchResultsDiv_body"></div>
		<div id="searchResultsDiv_footer"></div>
	</div>

	<script type="text/javascript">		
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
	</script>
</body>
</html>