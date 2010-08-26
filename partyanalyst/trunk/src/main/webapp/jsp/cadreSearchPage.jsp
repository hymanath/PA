<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                <td><div id="errorMsgDiv"></div></td>
            </tr>
        	<tr>
            	<th><font color="#FF0000"> * </font>Access level</th>                
                <td>
                	<input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="Country"/> Country		
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="State"/> State
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="District"/> District
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="Constituency"/> Constituency
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="Mandal"/> Mandal
                    <input type="radio" name="accessLevelRadio" onClick="getRegionsForAccessLevel(this.value,'accessRegion')" value="Village"/> Village                    
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
				<th><font color="#FF0000"> * </font> Cadre Type</span></th>
				<td>
					<input type="radio" name="cadreTypeRadio" checked="checked" value="active" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Active Cadre
					<input type="radio" name="cadreTypeRadio" value="normal" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Normal Cadre
				</td>
			</tr>
			
			<tr>
				<th valign="top"><font color="#FF0000"> * </font> Social Status</span></th>
				<td valign="top">
					<input type="checkbox" id="socialStatusCheck" value="socialStatus" onclick="showSocialStatus(this)"/> Include Cadre Social Status
					<div>	
						<table>
							<tr>
								<td><input type="checkbox" name="socialStatus" disabled="disabled" value="resevation"/> Reservation Category</td>
								<td>
									<select id="socialStatus_resevation" disabled="disabled">
										<option>SC</option>
										<option>ST</option>
										<option>BC</option>
										<option>Minority</option>
										<option>General</option>
										<option>Female</option>
									</select>
								</td>
							</tr>						
							<tr>
								<td><input type="checkbox" name="socialStatus" disabled="disabled" value="education"/> Education</td>
								<td>
									<select id="socialStatus_education" disabled="disabled">
										<option>Tenth</option>
										<option>twelve/Diploma</option>
										<option>Graduation</option>
										<option>Post Graduation</option>
										<option>Doctrate</option>
									</select>
								</td>
							</tr>						
							<tr>
								<td><input type="checkbox" name="socialStatus" disabled="disabled" value="occupation"/> Occupation</td>
								<td>
									<select id="socialStatus_occupation" disabled="disabled">
										<option>Agriculture</option>
										<option>Engineer</option>
										<option>Doctor</option>
									</select>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>

			<tr>
				<th valign="top"><div id="searchCriteria_label"><font color="#FF0000"> * </font> Search Criteria</span></div></th>
				<td valign="top">
					<div id="searchCriteria_data">
						<div>
							<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="all"/>All</span>	
						</div>
						<div>
							<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="committe"/>Committe Wise</span>
							<span id="committe_Select"></span>
						</div>
						<div>
							<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="category"/>Category Wise</span>
							<span id="category_Select"></span>
						</div>
						<div>
							<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="age"/>Age Wise</span>
							<span id="age_Select"></span>
						</div>
						<div>
							<span><input type="radio" name="criteriaValue" onclick="getSearchOptions(this.value)" value="occupation"/>Occupation Wise</span>
							<span id="occupation_Select"></span>
						</div>
					</div>
				</td>
			</tr>           
				 
            <tr>
            	<td colspan="2" align="center"><span id="accessRegion_button"></span></td>
            </tr>
        </table>
	</div>

	<div id="searchResultsDiv_main" class="yui-skin-sam">
		<div id="searchResultsDiv_head"></div>
		<div id="searchResultsDiv_body"></div>
		<div id="searchResultsDiv_footer"></div>
	</div>
</body>
</html>