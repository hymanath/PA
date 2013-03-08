<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre ${windowTask}</title>

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

<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>

<script type = "text/javascript">
var accessValue = '${sessionScope.USER.accessValue}';
var accessType = '${sessionScope.USER.accessType}';
var winTask = '${windowTask}';
var isProblemAdding = <%=request.getParameter("addProblem")%>;
var voterId = '${voterId}'; 
function populateLocations(val,source)
{	
	REPORTLEVEL = val;
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");
	var row7El = document.getElementById("row7");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s"); 
	var selectedDistrict = districtFieldEl.options[districtFieldEl.selectedIndex].value; 
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var hamletFieldEl = document.getElementById("hamletField_s");		
	var boothFieldEl = document.getElementById("boothField_s");
	var parliamentConstituencyEl = document.getElementById("parliamentConstituencyField_s");
		
 if(source == 'onChange')
{
	if(accessType == 'COUNTRY')
	{
		stateFieldEl.selectedIndex = '0';
		districtFieldEl.selectedIndex = '0';
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
	} else if(accessType == 'STATE')
	{
		districtFieldEl.selectedIndex = '0';
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
	} else if(accessType == 'DISTRICT' || accessType == 'MP')
	{
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		getSubRegionsInDistrict(selectedDistrict,'cadreSearch','constituencyField_s','cadreSearch')
	} else if(accessType == 'MLA')
	{
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		getSubRegionsInConstituency(accessValue,'cadreSearch','mandalField_s','cadreSearch')
	}						
} else if(source == "onLoad")
	{
		//setCadreValue(accessValue,'onChange');
		if(val == 9)
		{
			mandalField_sVal = mandalFieldEl.options[mandalFieldEl.selectedIndex].text;
			var flag = mandalField_sVal.search("Greater Municipal Corp");
			if(flag == '-1')
			{
				if(row6El.style.display == 'none')
					row6El.style.display = '';						
			} else {
				if(row5El.style.display == 'none')
					row5El.style.display = '';
				if(row6El.style.display == 'none')
					row6El.style.display = '';
			}
		}
	}	
	
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';	
	row7El.style.display == 'none';
	var value = val;
	if(value == 1)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';	
		    row7El.style.display = 'none';
		
	} else if(value == 2)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		   row7El.style.display = 'none';
	} else if(value == 3)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 4)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		    row7El.style.display = 'none';
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
		    row7El.style.display = 'none';
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
		    row7El.style.display = 'none';
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
		    row7El.style.display = 'none';
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
		    row7El.style.display = 'none';
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
		    row7El.style.display = 'none';
	}
	else if(value == 10)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row7El.style.display == 'none')
			row7El.style.display = '';
	}
			 
}

function getCadreId(selectEle)
{
	if(selectEle.checked == true && isProblemAdding != null && isProblemAdding == true)
	{
		deSelectCheckBox();
		selectEle.checked = true;
	}
	else
	  return;
}

function setCadreIdToProblem()
{
	var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("addSelectedCadreErrorMsg");
    var cid = null;
	var cMobile = null;
	var cName = null;

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		
		cid  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		cMobile = elements[i].value.substring(elements[i].value.indexOf('_')+1,elements[i].value.lastIndexOf('_'));
		cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
	}
	if(cid == null)
	{
		errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Cadre to Add the Problem</font>';
		return;
	}
	else if(cid != null)
	{
		errorSpanElmt.innerHTML = '';
		window.opener.setSelectedCadre(cid,cName);
		window.close();
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

.yui-skin-sam 
{
	font-weight:bold;
}
.yui-skin-sam .yui-dt th
{
	background-image:url(images/YUI-images/sprite.png)
}

#yui-dt0-th-Categorize
{
	background-color:blue;
}

</style>
</head>
<body>
	
	<div id="cadreSearchHeading"><center>
        <table border="0" cellpadding="0" cellspacing="0">          
          <tr>
            <td><img src="images/icons/constituencyManagement/left_blue_main.png"/>
			</td>
			<c:if test="${windowTask == 'Sms'}">
            <td><div id="headerImageCenterDiv">
			<span id="headerImageCenterSpan">Cadre 
			SMS</span></div></td>
			</c:if>
			<c:if test="${windowTask == 'Search'}">
            <td><div id="headerImageCenterDiv">
			<span id="headerImageCenterSpan">Cadre 
			Search</span></div></td>
			</c:if>
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
			</table>	

			<table width="100%" class="cadreSearchInputTable">	
				<tr>
					<th style="width:210px;padding-left:17px;">Search By Cadre Name&nbsp;&nbsp;(optional)</th>
					<td>
						<input type="text" id="cadreNameText"/>
						<input type="radio" value="StartingWith" checked="checked" name="nameBasedRadio">Starting With 
						<input type="radio" value="ExactMatch" name="nameBasedRadio">Exact Match
						<input type="radio" value="AnyWhereInName"  name="nameBasedRadio">Any Where In Name
					</td>
			    </tr>
			</table>
    
			<table width="100%" class="cadreSearchInputTable">
				<tr>
					<th align="left"><font color="#FF0000"> * </font> ${windowTask} Based On</th>                
					<td align="left">
						<input type="radio" name="searchBasedRadio" onClick="searchBased(this.value)"  checked="checked" value="location"/> Cadre Location  
						<input type="radio" name="searchBasedRadio" onClick="searchBased(this.value)" value="level"/> Cadre Region Level
					</td>
				</tr>

				<tr>
					<th align="left"><font color="#FF0000"> * </font>Select Range</th>                
					<td align="left">
						<div id="rangeRegionsRadioElmtDiv"></div>
						<div id="rangeRegionsSelectElmtDiv" style="width:600px;">
						
							<table  width="100%" >
								<tr id="row1" style="display:none;">
									<td width="200"><s:label for="stateField_s" id="stateLabel" theme="simple" value="%{getText('STATE')}" /><font color="#FF0000"> * </font></td>
									<td>
										<s:select id="stateField_s" cssClass="regionSelect" theme="simple" list="stateList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','cadreSearch','districtField_s','cadreSearch', 'null')"></s:select>
									</td>
								</tr>
								<tr id="row2" style="display:none;">
									<td width="200"><s:label for="districtField_s" id="districtLabel" theme="simple" value="%{getText('DISTRICT')}" /><font color="#FF0000"> * </font></td>
									<td>
										<s:select id="districtField_s" cssClass="regionSelect" theme="simple" list="districtList" listKey="id" listValue="name" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'cadreSearch','constituencyField_s','cadreSearch')"></s:select>
									</td>
								</tr>
								<tr id="row3" style="display:none;">
									<td width="200"><s:label for="constituencyField_s" id="constituencyLabel" theme="simple"  value="%{getText('CONSTITUENCY')}"/><font color="#FF0000"> * </font></td>
									<td>
										<s:select id="constituencyField_s" theme="simple" cssClass="regionSelect" list="constituencyList" listKey="id" listValue="name" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'cadreSearch','mandalField_s','cadreSearch')"></s:select>
									</td>
								</tr>
								

								<tr id="row7" style="display:none;">
									<td><span>Parliament Constituency <font color="#FF0000">*</font></span></td>
									<td><s:select id="parliamentConstituencyField_s" theme="simple" cssClass="regionSelect" list="parliamentConstituencies" listKey="id" listValue="name"></s:select></td>

								</tr>


								<tr id="row4" style="display:none;">
									<td width="200"><s:label for="mandalField" id="mandalLabel" theme="simple"  value="%{getText('subRegions')}" /><font color="#FF0000"> * </font></td>
									<td>
										<s:select id="mandalField_s" cssClass="regionSelect" theme="simple" list="{}" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreSearch','null','cadreSearch','constituencyField_s','row6', 'row5')"></s:select>
									</td>
								</tr>					
								<tr id="row5" style="display:none;">
									<td width="200"><s:label for="hamletField_s" id="mandalLabel" theme="simple"  value="%{getText('wardOrHamlet')}" /><font color="#FF0000"> * </font></td>
									<td>
										<s:select id="hamletField_s" cssClass="regionSelect" onchange="getBoothsInWard('cadreSearch','constituencyField_s','boothField_s',this.options[this.selectedIndex].value,'cadreSearch','mandalField_s')" list="{}" theme="simple" listKey="id" listValue="name"></s:select>
									</td>
								</tr>
								<tr id="row6" style="display:none;">
									<td width="200">Booth No<font color="#FF0000"> * </font></td>
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
						<input type="radio" name="cadreTypeRadio" value="All" checked="checked" onClick="getCriteriaValue(this.value,'searchCriteria')"/> All
						<input type="radio" name="cadreTypeRadio" value="Active" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Active Cadre
						<input type="radio" name="cadreTypeRadio" value="Normal" onClick="getCriteriaValue(this.value,'searchCriteria')"/> Normal Cadre
					</td>
				</tr>
				<tr>
					<th align="left"><font color="#FF0000"> * </font> Gender</th>
					<td align="left">
						<input type="radio" name="genderTypeRadio" value="allGenders" checked="checked"/> All
						<input type="radio" name="genderTypeRadio" value="Male"/> Male
						<input type="radio" name="genderTypeRadio" value="Female"/> Female
					</td>
				</tr>
				
				<tr>
					<th align="left">&nbsp;&nbsp;&nbsp;<s:label for="bloodGroupId" id="bloodGroupLabel" theme="simple" value="Blood Group"/></th>
					<td>&nbsp;&nbsp;<s:select id="bloodGroupId" cssClass="regionSelect" theme="simple" style="width:75px;" list="bloodGroupList" listKey="id" listValue="name"></s:select></td>
				</tr>
				<tr>
				<th>&nbsp;&nbsp;&nbsp;<span>Select Cadre : </span></th>
				<td><input type="radio" name="cadreRegisterTypeRadio" value="allCadres" checked="true"/>all
				<input type="radio" name="cadreRegisterTypeRadio" value="registeredByUser"/>Registered by User
				<input type="radio" name="cadreRegisterTypeRadio" value="registeredFromOnline" />Registered From OnLine</td>
				</tr>
				</table>
			</div>

			<div id="filterOptionsCadresSearch" class="fullSaintDesc" style="display:none">
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
			<tr>
				<th>Add Sender Name</th>
				<td>
					
					<input id="smsIncludeSenderName" type="checkbox" onclick="enableSenderName()" name="smsIncludeUserName">
					<input type="text" id="senderNameText" disabled="disabled" value="${sessionScope.UserName}"/>
				</td>
			</tr>
			</table>
		</div>		
		</c:if>
		<div id="searchButtonDiv" style="margin-top: 10px;">
			<table >
				
				<tr>					
					<td align="center">
						
						<input type="button" class="btnClass" onclick="getCadresResults('search')" value="Search"/>
						
						<c:if test="${windowTask == 'Sms'}">
						<input type="button" class="btnClass" onclick="sendCadreSMS()" value="Send SMS"/>
						</c:if>
						
					</td>
					<td align="center">
						<a class="toggleDiv" href="javascript:{}" />Add filter to ${windowTask}
						<a class="toggleDiv1" style="display:none" href="javascript:{}" />Remove filter to ${windowTask}
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
			<div id="searchResult"></div>
		</div>
		<div id="searchResultsDiv_footer" style="text-align:center;"></div>
		<div id="cadreProblemSelectErrorDiv" style="text-align:center;"><span id="addSelectedCadreErrorMsg"></span></div>
		<div id="cadreProblemSelectDiv" style="text-align:center;"></div>
	</div>

	<script type="text/javascript">	
		expandFilterOptions();

		$('document').ready(function(){
				$(".toggleDiv").click(function(){
					$(".toggleDiv").hide();
					$(".toggleDiv1").show();
				$(".fullSaintDesc").toggle();
				return false;
			 });  

			 $(".toggleDiv1").click(function(){
					$(".toggleDiv1").hide();
					$(".toggleDiv").show();
				$(".fullSaintDesc").toggle();
				return false;
			 });  
		});

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

<!--  cadre search  ---> 

function buildCadreSearchResultDataTable(rparam)
{
 YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("cadreId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='openRegistrationForm("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";
		
  };

  YAHOO.widget.DataTable.deleteCadre = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("cadreId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='deleteCadre("+id+",\"search\")'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";
		
  };

   YAHOO.widget.DataTable.viewDetails = function(elLiner, oRecord, oColumn, oData) 
  {
	var fname = oData;
	var id= oRecord.getData("cadreId");
	var lname= oRecord.getData("lastName");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='getCadreInfo("+id+")'>"+fname+" "+lname+" </a>";
  };

  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
  {
	var name = oData;
	var id= oRecord.getData("cadreId");
	var mobile= oRecord.getData("mobile");
	var firstName= oRecord.getData("firstName");
	elLiner.innerHTML="<input type='checkbox' onClick='getCadreId(this)' name='cadreResult_check' value='"+id+"_"+mobile+"_"+firstName+"'>";
				
  };

  YAHOO.widget.DataTable.image = function(elLiner, oRecord, oColumn, oData) 
  {
	elLiner.innerHTML ="<img height='85px' width='85px' src='images/cadre_images/"+oData+"'/>";
  };
  
  var CadreSearchResultColumnDefs = [ 
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							{key:"firstName", label: "Name",sortable: true, formatter:YAHOO.widget.DataTable.viewDetails} ,
							{key:"image", label: "Image",formatter:YAHOO.widget.DataTable.image}, 
		    	            {key:"mobile", label: "Mobile", sortable: true}, 
		    	           	{key:"strCadreLevel", label: "Cadre Level", sortable: true},
							{key:"email", label: "Address"},
		    				{key:"memberType", label: "Cadre Type",sortable:true},
							{key:"educationStr", label: "Education",sortable:true},
		    				{key:"professionStr", label: "Occupation",sortable:true},
							{key:"casteCategoryStr", label: "Caste Category",sortable:true},
							{key:"edit", label: "Edit",formatter:YAHOO.widget.DataTable.edit},
							{key:"Delete", label: "Delete",formatter:YAHOO.widget.DataTable.deleteCadre}
		    	        ]; 
	var CadreSearchResultDataSource = new YAHOO.util.DataSource("getCadreDetailsForSMSAjaxAction.action?"+rparam+"&windowTask="+winTask+"&"); 
	CadreSearchResultDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	
	CadreSearchResultDataSource.responseSchema = { 
            resultsList:"cadreInfo", 
		fields: [
				{key:"firstName"},
				"lastName","image","mobile", "strCadreLevel","memberType",
				"educationStr","professionStr","casteCategoryStr","cadreId","email","pincode"
				],
		metaFields: {
			totalRecords: "totalSearchCount" // Access to value in the server response
		}         
        };


    var myConfigs = {
			        initialRequest: "sort=firstName&dir=asc&startIndex=0&results=20", // Initial request for first page of data
			        dynamicData: true, // Enables dynamic server-driven data
			        sortedBy : {key:"firstName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
		};

		var CadreSearchResultDataTable = new YAHOO.widget.DataTable("searchResult", CadreSearchResultColumnDefs,CadreSearchResultDataSource, myConfigs);

		CadreSearchResultDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) 
		{		
		        oPayload.totalRecords = oResponse.meta.totalRecords;
		        return oPayload;
		}
		//function calling to build Result
		return {
			oDS: CadreSearchResultDataSource,
			oDT: CadreSearchResultDataTable,
    	};
	}

function getCadreInfo(cadreId)
{
	var urlStr = "getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId="+cadreId;
	var cadreViewBrowser = window.open(urlStr,"cadreInfoPopup","scrollbars=yes,height=600,width=600,left=200,top=50");	
	cadreViewBrowser.focus();
}

function openRegistrationForm(cadreId)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?cadreId="+cadreId+"&windowTask="+task;
	var updateBrowser = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();				
}

function showCadreSearchResults(searchCount)
 {
	var totalSearchCount = searchCount;
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = document.getElementById("searchResult");
	var footerElmt = document.getElementById("searchResultsDiv_footer");
	var resultsCountEl = document.getElementById("resultsCount");
	
	if(!headElmt || !bodySearchElmt || !footerElmt)
		return;
	
	bodySearchElmt.style.display = 'block';
	resultsCountEl.innerHTML ='';
	headElmt.innerHTML = 'Search Results';
		
	if(totalSearchCount == 0)
	{
		bodySearchElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">No Search results found.</div>';
		footerElmt.style.display = 'none';
		return;
		
	} 
	else 
	{
	resultsCountEl.innerHTML = '<span>'+totalSearchCount+'</span> cadres found with this selection criteria';

	var fStr = '';
	fStr += '<span><input type="button" class="btnClass" onclick="selectCheckBox()" value="Select All"/></span>';
	fStr += '<span><input type="button" class="btnClass" onclick="deSelectCheckBox()" value="DeSelect All"/></span>';
	fStr += '<span><input type="button" class="btnClass" onclick="sendCadreSMS()" value="Send SMS"/></span><BR><BR><BR>';
	fStr += '<span id="smsStatusTextSpan"></span>';
	footerElmt.innerHTML = fStr;
	footerElmt.style.display = '';
	}
	
	if(isProblemAdding != null && isProblemAdding == true)
	{
		footerElmt.style.display = 'none';
		
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setCadreIdToProblem()" value="Add Selected cadre"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
	if(isProblemAdding != null && isProblemAdding == false)
	{
		footerElmt.style.display = 'none';
		
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setOrganizers()" value="Add Organizers"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
	if(voterId !== ''){
	   footerElmt.style.display = 'none';
		
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setCadreToVoter()" value="Add Selected cadre"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
}
function setOrganizers(){
   var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("addSelectedCadreErrorMsg");
	var candidates = new Array();
    var cid = null;
	var cName = null;

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		
		cid  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
	   var obj1 = {};
	   obj1["id"] = cid;
	   obj1["name"] = cName;
	   candidates.push(obj1);
	}
	if(cid == null)
	{
		errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Cadre to Add As Organiser</font>';
		return;
	}
	else if(cid != null)
	{
		errorSpanElmt.innerHTML = '';
		window.opener.setSelectedOrganizers(candidates);
		window.close();
	}

}
function setCadreToVoter(){
   var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("addSelectedCadreErrorMsg");
    var cid = null;
	var cName = null;
	var cCount = 0;

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		cCount = cCount+1;
		cid  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
	}
	if(cCount == 0)
	{
		errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Cadre</font>';
		return;
	}else if(cCount > 1){
	    errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Only One Cadre</font>';
		return;
	}
	else if(cid != null && cCount == 1)
	{
		errorSpanElmt.innerHTML = '';
		window.opener.mapSelectedCadreToVoter(cid,cName,voterId);
		window.close();
	}
}
<!--  cadre search  --- end> 
</script>
</body>
</html>