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

var Localization = { <%
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String STATE = rb.getString("STATE");
			String DISTRICT = rb.getString("DISTRICT");
			String CONSTITUENCY = rb.getString("CONSTITUENCY");
			String MANDAL  = rb.getString("MANDAL");
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
						clearOptionsListForSelectElmtId("hamletField");
						createOptionsForSelectElmtId("hamletField",myResults);
					}				
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

function influenceRangeFunction(id,text){
		var specifyBox = document.getElementById("influenceRangeInputId");
		specifyBox.value = text;
}
function checkRequestType()
{
	var resultStatus = "${resultStatus}";
	var successMsgDIV = document.getElementById("successMsg");
	var message = '';
	if(resultStatus==""){
		message+='';
	}else if(resultStatus==0){
		message+='<div id="successDIV" style="color:green;">Successfuly Saved</div>';
	}else if(resultStatus==1){
		message+='<div id="successDIV" style="color:green;"><p>Error Raised while saving data please check log for details.</p></div>';
	}
	successMsgDIV.innerHTML = message;
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
}

getSelectOptionVOList(this.value,"getStates","COUNTRY");
</script>

<body class="bodyStyle" onload="checkRequestType()" onunload="doUnload()">
 <CENTER>
		<TABLE border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
			<TR>
				<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
				<c:if test="${windowTask == 'new'}">
				<TD>				
				<div class="addInfluencingHeader"><span style="margin-top:2px;">Add Influencing People </span></div>	
				</TD>
				</c:if>
				<c:if test="${windowTask == 'edit'}">
				<TD>
				<div class="addInfluencingHeader"><span style="margin-top:2px;">Edit Influencing People </span></div>
				</TD>
				</c:if>
				<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>	
			</TR>
		</TABLE>
  </CENTER>

<div id="registrationMainDiv">

<div id="loginDetailsDiv" class="accessDivMain">
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
	
	<s:form action="influencingPeopleSaveAction.action" method="post" theme="simple" name="form">
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
	</tr><tr><td></td></tr>										
	
	<tr>
		<th width="15px" class="tdstyle"><u><s:label for="addressField" id="addressLabel"  value="%{getText('address')}" style="color:#0000AA;"/></u></th>
	</tr>

	<tr>
		<td class="tdstyle"><s:label for="houseNoField" id="houseNoLabel"  value="%{getText('houseNo')}" /></td>
		<td><s:textfield id="houseNoField" name="houseNo" maxlength="25" /></td>
		<td class="tdstyle"><s:label for="streetField" id="streetLabel" value="%{getText('street')}" /></td>
		<td><s:textfield id="streetField" name="streetName" maxlength="100"  /></td>
			</tr>
	<tr>
		<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
		<td ><s:select id="stateField" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select State" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtField','currentAdd')"></s:select></td>

		<td class="tdstyle"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}"/><font class="required"> * </font></td>
		<td><s:select id="districtField" cssClass="regionSelect" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','influencingPeopleReg','constituencyField','currentAdd')" headerKey="0" headerValue="Select District"></s:select></td>
	</tr>
	
	<tr>
		<td class="tdstyle" width="100px"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="required"> * </font></td>
		<td><s:select id="constituencyField" cssClass="regionSelect" name="constituency" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','influencingPeopleReg','mandalField','currentAdd', 'null')" headerKey="0" headerValue="Select Constituency"></s:select></td>
	
		<td class="tdstyle" width="105px"><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}"/><font class="required"> * </font></td>
		<td><s:select id="mandalField" cssClass="regionSelect" name="mandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','influencingPeopleReg','villageField','currentAdd')" headerKey="0" headerValue="Select Mandal"></s:select></td>
	</tr>
	
	<tr>
 		<td class="tdstyle" width="100px"><s:label for="wardOrHamlet" id="wardOrHamletLabel"  value="%{getText('wardOrHamlet')}" /><font class="required"> * </font></td>
		<td><s:select id="villageField" cssClass="regionSelect" name="wardOrHamlet" list="#session.villagesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'getVillages','REVENUE VILLAGE / TOWN')" headerKey="0" headerValue="Select Village"></s:select></td>
		
		<td class="tdstyle"><s:label for="pinCodeField" id="pinCodeLabel" value="%{getText('pincode')}" /></td>
		<td><s:textfield id="pinCodeField" name="pincode" maxlength="10"/></td>
	</tr>
	</table>
</FIELDSET>
<FIELDSET>
	<LEGEND style="font-size:12px;"><strong>Influence Details</strong></LEGEND>
	<table class="registrationTable" width="100%">	
	<tr>
		<td class="tdstyle" width="88px"><s:label for="occupationField" id="occupationLabel"  value="%{getText('occupation')}" /><font class="required"> * </font></td>
		<td><s:select id="occupationField" cssClass="regionSelect" name="occupation"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Occupation"></s:select></td>
		
	
		<td class="tdstyle" width="110px"><s:label for="partyField" id="partyLabel"  value="%{getText('party')}"/></td>
		<td align="left">
		<s:select id="party" list="#session.staticParties" listKey="id" listValue="name" name="party" cssClass="regionSelect" /></td>
	</tr>	
 </table>

<table class="registrationTable" width="100%">
	<tr>
		<td class="tdstyle" width="120px;"><s:label for="socialStatusField" id="socialStatusLabel"  value="%{getText('socialStatus')}" /><font class="required"> * </font></td>
		<td class="tdstyle" style="padding-left: 10px;">

		<s:radio id="socialStatusField" name="cast" list="#session.socialStatus" listKey="id" listValue="name" required="true"></s:radio></td></tr>
</table>

<table class="registrationTable" width="100%">
	<tr>
		
		<td class="tdstyle" width="88px;"><s:label for="positionField" id="positionLabel"  value="%{getText('position')}" /></td>	
		<td> <s:select id="position" list="#session.positionsList" listKey="id" listValue="name" name="position" cssClass="regionSelect" /></td>
		
		<td width="120px" class="tdstyle"><%=InfluenceRange%><font class="required"> * </font></td>
			<td align="left">
			<s:select id="effectedRange" list="#session.influenceRange" listKey="id" listValue="name" name="influenceRange" cssClass="regionSelect" onchange="influenceRangeFunction(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)"/> 
			</td>
		<td><input type="hidden" id="influenceRangeInputId" name="influencingRange"></td>
		<td><div id="specifyInfluenceRange"></div></td>
	</tr>									
</table>
</FIELDSET> 

<div id="saveDiv" align="center">
	<s:submit cssClass="button" value="ADD" name="Save"></s:submit>
</div>
</s:form>

<div id="successMsg"></div> 
</div>	
</div>		 
</div>		
</body>
</html>