<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Influencing People </title>

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
    <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">
<!-- YUI Dependency files (End) -->

<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>


<style type="text/css">

.selectWidth
	{
		width:145px;
	}
#errorMessageDisplay{
	color:red;
	font-size:14px;
	font-weight:bold;
}

.addInfluencingHeader
{
	background-image:url("images/icons/cadreReport/bg_center.png");
	background-repeat:repeat-x;
	color:#FFFFFF;
	font-size:14px;
	font-weight:bold;
	height:24px;
	padding-top:1px;
	text-align:center;
	width:250px;
}

.bodyStyle {
		font-family:verdana;
		font-size:11px;
	}

.required
{
	color :red;
}

.tdstyle{
	color:#926682;
	font-family:verdana;
	font-weight:bold;
	text-align:left;
}

.regionSelect
{
width:146px;
}
</style>
</head>
<script type="text/javascript"> 
var userAccess = '${sessionScope.USER.accessType}';

var Localization = { <%
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String STATE = rb.getString("STATE");
			String DISTRICT = rb.getString("DISTRICT");
			String CONSTITUENCY = rb.getString("CONSTITUENCY");
			String MANDAL  = rb.getString("MANDAL");
			String TehsilOrMuncipality = rb.getString("TehsilOrMuncipality");
			String VILLAGE = rb.getString("VILLAGE");
			String HAMLET   = rb.getString("HAMLET");
			String InfluenceRange  = rb.getString("influenceRange");
			String Position = rb.getString("position");
			String Party = rb.getString("party");
			String Cast = rb.getString("cast");
			String Occupation = rb.getString("occupation");
			String Male  = rb.getString("Male");
			String Female = rb.getString("Female");
			String wardOrHamlet = rb.getString("wardOrHamlet");
			String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
			String PCONSTITUENCY = rb.getString("PCONSTITUENCY");
  %> }


function callAjax(param,jsObj,url){
	var myResults;	
	var callback = {			
	    success : function( o ) {
			try {	
					if(o.responseText.length!=0){
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
					}						
					if(jsObj.task == "getStates")
					{
						clearOptionsListForSelectElmtId("stateId");
						createOptionsForSelectElmtId("stateId",myResults);
					}
					if(jsObj.task == "getDistricts")
					{
						clearOptionsListForSelectElmtId("districtField");
						createOptionsForSelectElmtId("districtField",myResults);	
					}	
					if(jsObj.task == "getConstituencies")
					{
						clearOptionsListForSelectElmtId("constituencyField");
						createOptionsForSelectElmtId("constituencyField",myResults);
					}		
					if(jsObj.task == "getMandals")
					{
						clearOptionsListForSelectElmtId("mandalField");
						createOptionsForSelectElmtId("mandalField",myResults);
					}		
					if(jsObj.task == "getTowhships")
					{
						clearOptionsListForSelectElmtId("villageField");
						createOptionsForSelectElmtId("villageField",myResults);
					}
					if(jsObj.task == "getVillages")
					{
						clearOptionsListForSelectElmtId("hamletField_s");
						createOptionsForSelectElmtId("hamletField_s",myResults);
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

function getSelectOptionVOList(id, task, influenceRange)
{	
	if(id == 0)
		return;
	
	var jsObj=
		{
				locationId:id,
				task:task						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

/*function influenceRangeFunction(id,text){
		var specifyBox = document.getElementById("influenceRangeInputId");
		specifyBox.value = text;
} */

/*function setLocationValue(value,source)
{ 
	var scopeLevelEl = document.getElementById("scopeLevel");
	var scopeLevelElVal = scopeLevelEl.options[scopeLevelEl.selectedIndex].text;

	if(value == '0')
	{
		alert("Please Select Valid Location"); 
		return; 
	}	
		var boothIdTextEl = document.getElementById("boothNoText");
		//var hiddenEl = document.getElementById("influenceRangeInputId"); 
		//hiddenEl.value = '';

		if(source == 'onKeyUp')
		{
			hiddenEl.value = boothIdTextEl.value;	
		} else
		{
			hiddenEl.value = value;
		}
		if(scopeLevelElVal == 'BOOTH')
		{
			
		}
	}*/

function executeOnload() 
{
	
	var effectedRangeEl = document.getElementById("scopeLevel");
	
	var selectedeffectedRange =effectedRangeEl.options[effectedRangeEl.selectedIndex].value; 
		if(selectedeffectedRange != '0')
		populateLocations(selectedeffectedRange, 'onLoad');	
	
	document.getElementById("genderMale").checked = true;
}

function cleanOptionsList(string)
{
	
	if(string == "state")
	{
		clearOptionsListForSelectElmtId("constituencyField_add");
		clearOptionsListForSelectElmtId("mandalField_add");
		clearOptionsListForSelectElmtId("villageField_add");
		
	}

	else if(string == "district")
	{
		clearOptionsListForSelectElmtId("mandalField_add");
		clearOptionsListForSelectElmtId("villageField_add");
	}

	else if(string == "constituency")
	{
		clearOptionsListForSelectElmtId("villageField_add");
	}
	
	
}
function populateLocations(val,source)
{	
	
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");

	//var hiddenEl = document.getElementById("influenceRangeInputId");
	var stateFieldEl = document.getElementById("stateField");
	var districtFieldEl = document.getElementById("districtField"); 
	var constituencyFieldEl = document.getElementById("constituencyField");
	var mandalFieldEl = document.getElementById("mandalField");
	var hamletFieldEl = document.getElementById("hamletField_s");
	var boothFieldEl = document.getElementById("boothField_s");
	
	if(source == 'onChange')
	{	
		//hiddenEl.value='';
		stateFieldEl.selectedIndex = '0';
		
		if(districtFieldEl)
			districtFieldEl.selectedIndex = '0';
		
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';

		if(boothFieldEl)
			boothFieldEl.selectedIndex = '0';
		
	}	
	
	row1El.style.display = 'none';
	
	if(row2El)
		row2El.style.display = 'none';
	
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';

	if(row6El)
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
		  /*	if(row6El.style.display == 'none')
				row6El.style.display = ''; */
		}	 
}
function doUnload()
{
	var jsObj=
	{
		task:"removeSessionVariablesForInfluencingPeople"					
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/removeSessionVariablesForInfluencingPeopleAjaxAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);
	window.opener.document.location.reload(true);
}

function sessionClean(module,accessType,windowTask)
{

	var jsObj=
		{				
			module: module,
			accessType: accessType,
			windowTask: windowTask,
			task:"sessionCleaning"
		};
			

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/sessionCleaningForAModuleAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

getSelectOptionVOList(this.value,"getStates","COUNTRY");

function refreshParentWindow(){
window.close();

}
function refreshParent() {
  window.opener.location.href = window.opener.location.href;

  if (window.opener.progressWindow)
		
 {
    window.opener.progressWindow.close()
  }
  window.close();
}

</script>

<body onLoad="executeOnload()" class="bodyStyle">
 	<center>
 	<TABLE border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
			<TR>
				<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
					<c:if test="${windowTask == 'edit'}">
						<TD>
						<div class="addInfluencingHeader"><span style="margin-top:2px;">Edit Influencing People </span></div>
						</TD>
					</c:if>
					<c:if test="${windowTask != 'edit'}">
						<TD>
						<div class="addInfluencingHeader"><span style="margin-top:2px;">Add Influencing People </span></div>
						</TD>
					</c:if>
				<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>	
			</TR>
	</TABLE>
	</center>
 	<div id="loginDetailsDivBody" align="center" class="accessDivBody">
		
	<div id="errorMsgDiv">
		<table class="registrationTable" >
			<tr>
				<td colspan="2">
					<div style="color: red;">
						<s:actionerror />
						<s:fielderror />
					</div>
				</td>
			</tr>
		</table>
	</div>	
	
	<c:if  test="${resultStatus == '0'}">
	<div id="successMsg" style="color:green;" >Influencing People Registered Successfully!</div>
	</c:if>	
	<c:if  test="${resultStatus == '1'}">
	<div id="successMsg" style="color:red;" style="color:green;">Error Raised while saving data please check log for details</div>
	</c:if>
	
	<s:form action="influencingPeopleSaveAction" method="post" theme="simple" name="form">
	<FIELDSET>
	<LEGEND style="font-size:12px;"><strong>Personal Details</strong></LEGEND>

	<table class="registrationTable" width="100%">			
		<tr>
			<td class="tdstyle" width="90px"><s:label class="selectWidth" for="firstNameField" theme="simple" id="fnameLabel"  value="%{getText('firstName')}"/><font class="required">*</font></td>
			<td align="left"><s:textfield id="firstNameField" theme="simple" name="firstName"/></td>

			<td class="tdstyle"><s:label class="selectWidth" for="middleNameField" theme="simple" id="middleNameLabel"  value="%{getText('middleName')}"/></td>
			<td><s:textfield id="middleNameField" theme="simple" name="middleName"/></td>
		</tr>					
		<tr>
			<td class="tdstyle"><s:label class="selectWidth" for="lastNameField" theme="simple" id="lastNameLabel" value="%{getText('lastName')}"/><font class="required">*</font></td>
			<td><s:textfield id="lastNameField" theme="simple" name="lastName"/></td>
			
			<td class="tdstyle" width="120"><s:label class="selectWidth" for="father_spouseName" theme="simple" id="father_spouseNameLabel" value="%{getText('father_spouseName')}"/><font class="required">*</font></td>
			<td><s:textfield id="father_spouseName" theme="simple" name="fatherOrSpouseName"/></td>
		</tr>
		<tr>
			<td class="tdstyle"><s:label class="selectWidth" for="genderField" id="genderLabel" theme="simple" value="%{getText('gender')}"/><font class="required">*</font></td>
			<td align="left" class="tdstyle">
				<s:radio id="gender" name="gender" list="#session.genders"/>
			</td>		
		</tr>	
	</table>
	</FIELDSET>

	<FIELDSET>
	<LEGEND style="font-size:12px;"><strong>Contact Details</strong></LEGEND>
	<table class="registrationTable" width="100%">	
	<tr>
		<td class="tdstyle"><s:label class="selectWidth" for="mobileField" id="mobileLabel"  theme="simple" value="%{getText('mobile')}" /><font class="required"> * </font> </td>
		<td><s:textfield id="mobileField" theme="simple" name="mobile"/>  </td>
		<td class="tdstyle"><s:label class="selectWidth" for="emailField" theme="simple" id="emailLabel" value="%{getText('email')}"/></td>
		<td><s:textfield id="emailField" theme="simple" name="email"/>  </td>
	</tr>	
	<tr>
		<th width="15px" class="tdstyle"><u><s:label for="addressField" id="addressLabel"  value="%{getText('address')}" style="color:#0000AA;"/></u></th>
	</tr>
	<tr>
		<td class="tdstyle"><s:label for="houseNoField" id="houseNoLabel"  value="%{getText('houseNo')}" /></td>
		<td><s:textfield id="houseNoField" name="houseNo" maxlength="25" /></td>
		<td class="tdstyle"><s:label for="streetField" id="streetLabel" value="%{getText('street')}" /></td>
		<td><s:textfield id="streetField" name="streetName" maxlength="100"  /></td>
	</tr>
	
	<c:if test="${sessionScope.USER.accessType != 'MP'}">
		<tr>
			<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
			<td ><s:select id="stateField_add" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtField_add','currentAdd');cleanOptionsList('state')"></s:select></td>
			<td class="tdstyle"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}"/><font class="required"> * </font></td>
			<td><s:select id="districtField_add" cssClass="regionSelect" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','influencingPeopleReg','constituencyField_add','currentAdd');cleanOptionsList('district')"></s:select></td>
		</tr>				
	</c:if>
	<c:if test="${sessionScope.USER.accessType == 'MP'}">
		<tr>
			<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
			<td ><s:select id="stateField_add" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','influencingPeopleReg','pConstituencyField_add','currentAdd');cleanOptionsList('state')"></s:select></td>
			<td class="tdstyle"><s:label for="pConstituencyField_add" id="pConstituencyField_addLabel"  value="%{getText('PCONSTITUENCY')}"/><font class="required"> * </font></td>
			<td><s:select id="pConstituencyField_add" cssClass="regionSelect" name="pConstituency" list="#session.p_constituencies" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','influencingPeopleReg','constituencyField_add','currentAdd');cleanOptionsList('district')" ></s:select></td>
		</tr>
	</c:if>	
	<!--<tr>
		<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
		<td ><s:select id="stateField_add" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtField_add','currentAdd');cleanOptionsList('state')"></s:select></td>
		<td class="tdstyle"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}"/><font class="required"> * </font></td>
		<td><s:select id="districtField_add" cssClass="regionSelect" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','influencingPeopleReg','constituencyField_add','currentAdd');cleanOptionsList('district')" headerKey="0" ></s:select></td>
	</tr>	
	--><tr>
		<td class="tdstyle" width="100px"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="required"> * </font></td>
		<td><s:select id="constituencyField_add" cssClass="regionSelect" name="constituency" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','influencingPeopleReg','mandalField_add','currentAdd', 'null');cleanOptionsList('constituency')" headerKey="0" ></s:select></td>

		<td class="tdstyle" width="105px"><s:label for="mandalField" id="mandalLabel"  value="%{getText('TehsilOrMuncipality')}"/><font class="required"> * </font></td>
		<td><s:select id="mandalField_add" cssClass="regionSelect" name="mandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','influencingPeopleReg','villageField_add','currentAdd');getBooths('currentAdd','constituencyField_add','boothField',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','boothsInTehsilOrMunicipality')"></s:select></td>
	</tr>	
	<tr>
 		<td class="tdstyle" width="100px"><s:label for="wardOrHamlet" id="wardOrHamletLabel"  value="%{getText('wardOrHamlet')}" /><font class="required"> * </font></td>
		<td><s:select id="villageField_add" cssClass="regionSelect" name="wardOrHamlet" list="#session.villagesList" listKey="id" listValue="name" onchange="getBoothsInWard('currentAdd','constituencyField_add','boothField',this.options[this.selectedIndex].value,'cadreReg','mandalField_add');"></s:select></td>
		<td class="tdstyle"><s:label for="pinCodeField" id="pinCodeLabel" value="%{getText('pincode')}" /></td>
		<td><s:textfield id="pinCodeField_add" name="pincode" maxlength="6"/></td>
	</tr>
	<tr>
		<th colspan="4" width="15px" class="tdstyle" style="color:#0000AA;"><u>Booth details are not compulsory</u></th>
	</tr>	
	<tr>
		<td class="tdstyle" width="100px"><s:label for="boothField" id="boothLabel"  value="%{getText('Booth')}" /></td>
		<td align="left" width="165px">
		<s:select id="boothField" cssClass="regionSelect" name="booth" list="#session.boothsList" listKey="id" listValue="name" headerKey="0" headerValue="Select Booth"></s:select>				
		</td>
		<td width="165px"><input type="button" id="pBoothDetailsPanel" value="View Booths Details" onclick="showBoothsCompleteDetails('boothField','mandalField_add')"/></td>
	</tr>
	</table>
</FIELDSET>
<FIELDSET>
	<LEGEND style="font-size:12px;"><strong>Social Status</strong></LEGEND>
	<table class="registrationTable" width="100%">	
	<tr>
		<td class="tdstyle" width="88px"><s:label for="occupationField" id="occupationLabel"  value="%{getText('occupation')}" /><font class="required"> * </font></td>
		<td><s:select id="occupationField" cssClass="regionSelect" name="occupation"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Occupation"></s:select></td>
		<td class="tdstyle" width="110px"><s:label for="partyField" id="partyLabel"  value="%{getText('party')}"/></td>
		<td align="left">
		<s:select id="party" list="#session.staticParties" listKey="id" listValue="name" name="party" cssClass="regionSelect" /></td>
	</tr>			
	<tr>
		<td class="tdstyle" width="120px;"><s:label for="socialStatusField" id="socialStatusLabel"  value="%{getText('socialStatus')}" /><font class="required"> * </font></td>
		<td class="tdstyle" style="padding-left: 10px;" colspan="3"><s:radio id="socialStatusField" name="cast" list="#session.socialStatus" listKey="id" listValue="name" required="true"/></td>
	</tr>
	<tr>
		
		<td class="tdstyle" width="88px;"><s:label for="positionField" id="positionLabel"  value="%{getText('position')}" /><font class="required"> * </font></td>	
		<td> <s:select id="position" list="#session.positionsList" listKey="id" listValue="name" name="position" cssClass="regionSelect" /></td>
	</tr>
 </table>
 </FIELDSET>
 <FIELDSET>
	<LEGEND style="font-size:12px;"><strong>Influence Details</strong></LEGEND>
 	 <table class="registrationTable" width="100%">
		<tr>		
			<td width="120px" class="tdstyle"><s:label for="scopeLevel" id="influenceRangeId"  value="%{getText('InfluenceRange')}" /><font class="required"> * </font></td>
			<td align="left">
				<s:select id="scopeLevel" list="#session.influenceRange" listKey="id" listValue="name" value="defaultInfluenceRange" name="influencingRange" cssClass="regionSelect" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange')"/> 
			</td>			
		</tr>
		<c:if test="${sessionScope.USER.accessType != 'MP'}">	 								
			<tr id="row1" style="display:none;">
				<td width="120px" class="tdstyle"><%=STATE%><font class="required">*</font></td>
				<td>
				<s:select id="stateField" cssClass="selectWidth" name="scopeState" list="#session.statesList_c" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','newProblemPost','districtField','currentAdd', 'null')"></s:select>
				</td>
			</tr>
			<tr id="row2" style="display:none;">
				<td width="120px" class="tdstyle"><%=DISTRICT%><font class="required"> * </font></td>
				<td>
				<s:select id="districtField" cssClass="selectWidth" name="scopeDistrict" list="#session.districtsList_c" listKey="id" listValue="name" value="defaultDistrict" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'newProblemPost','constituencyField','currentAdd')"></s:select>
				</td>
			</tr>
		</c:if>
		<c:if test="${sessionScope.USER.accessType == 'MP'}">
			<tr id="row1" style="display:none;">
				<td width="120px" class="tdstyle"><%=STATE%><font class="requiredFont">*</font></td>
				<td>
					<s:select id="stateField_s" cssClass="selectWidth" name="scopeState" list="#session.statesList_c" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','newProblemPost','pConstituencyField_s','currentAdd','null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
				</td>
			</tr>
			<TR id="row2" style="display:none;">	
				<TD width="120px" class="tdstyle"><%=PCONSTITUENCY%></TD>
				<TD>
				<s:select id="pConstituencyField_s" cssClass="selectWidth" name="pConstituencyId" list="#session.p_constituencies" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Constituency" value="defaultPConstituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','newProblemPost','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
				</TD>
			</TR>
		</c:if>
		<tr id="row3" style="display:none;">
		<td width="120px" class="tdstyle"><%=ACONSTITUENCY%><font class="required"> * </font></td>
		<td>
			<s:select id="constituencyField" cssClass="selectWidth" name="scopeConstituency" list="#session.constituenciesList_c" listKey="id" listValue="name" value="defaultConstituency" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'newProblemPost','mandalField','currentAdd')"></s:select>
		</td>
	</tr>								
	<tr id="row4" style="display:none;">
		<td width="120px" class="tdstyle"><%=MANDAL%><font class="required"> * </font></td>
		<td>
			<s:select id="mandalField" cssClass="selectWidth" name="scopeMandal" list="#session.mandalsList_c" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','null','cadreLevel','constituencyField', 'row6', 'row5')"></s:select>
		</td>
	</tr>					
	<tr id="row5" style="display:none;">
		<td width="120px" class="tdstyle"><%=wardOrHamlet%><font class="required"> * </font></td>
		<td>
			<s:select id="hamletField_s" cssClass="selectWidth" name="scopeVillage" list="{#session.villagesList_c}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="getBoothsInWard('cadreLevel','constituencyField','boothField_s',this.options[this.selectedIndex].value,'cadreReg','mandalField')"></s:select>
		</td>
	</tr>	
	<tr id="row6" style="display:none;">
			<td width="120px" class="tdstyle">Booth No</td>
			<td>
				<s:select id="boothField_s" cssClass="selectWidth" name="scopeBooth"list="{#session.boothsList_c}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange=""></s:select>
			</td>
			<td>
				<input type="button" id="pBoothDetailsPanel" value="View Booths Details" onclick="showBoothsCompleteDetails('boothField_s', 'mandalField')"/>
			</td>
	</tr>
	</TABLE>
</FIELDSET>
<div id="specifyInfluenceRange"></div>
<%-- <s:hidden id="influenceRangeInputId" name="influencingScopeValue"/> --%>
<s:hidden id="windowTaskId" name="windowTask" value="%{windowTask}"/>
<s:hidden id="influencingPersonIdId" name="influencingPersonId" value="%{influencingPersonId}" /> 

<div id="saveDiv" align="center">
	<s:submit cssClass="button" value="Save" name="Save"></s:submit>
	<input type="button" value="Exit" class="button" onClick="refreshParent()"/>
</div>
<!--<div id="exitDiv" align="center">
	<input type="button" name="exit" onclick="sessionClean('influencingPeople','District','new')" value="Exit" ></input> 
</div>  -->
<div class="yui-skin-sam"><div id="boothDetailsPopup"></div></div>
</s:form>

</div>		
</body>
</html> 

