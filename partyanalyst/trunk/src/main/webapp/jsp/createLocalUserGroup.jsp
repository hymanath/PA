<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User Group</title>
<SCRIPT type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">
 <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
	

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	
<style type="text/css">

  .formTableStyle {
	font-family:arial;
	font-size:14px;
	font-weight:bold;
  }

  .labelStyle {
	font-family:arial;
	font-size:22px;
  }
  .btn_crtCategory {
	  margin:5px 0 8px;
	  width:120px;
  }
   .btn_sbtCategory {
	  margin:5px 0 8px;
	  width:50px;
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
.selectWidth {
width:150px;
}

</style>
<script type="text/javascript">

var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String problemLabel = rb.getString("problem");		
		
	  %> }

function showHideCategoryData(status)
{
	var catgryNameId = document.getElementById("catgrNameRow");
	var catgryDescId = document.getElementById("catgrDescRow");

    if(catgryNameId)
	{
		if(status == 1)
		 catgryNameId.style.display = '';
		if(status == 0)
         catgryNameId.style.display = 'none';
		var categoryElmt = document.getElementById("categoryName");
		categoryElmt.focus();
	}
    if(catgryDescId)
	{
		if(status == 1)
		  catgryDescId.style.display = '';
		if(status == 0)
		  catgryDescId.style.display = 'none';
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
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+" ";
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

	var hiddenEl = document.getElementById("groupScopeValueId");
	var stateFieldEl = document.getElementById("stateField");
	var districtFieldEl = document.getElementById("districtField"); 
	var constituencyFieldEl = document.getElementById("constituencyField");
	var mandalFieldEl = document.getElementById("mandalField");
	var hamletFieldEl = document.getElementById("hamletField_s");
	var boothFieldEl = document.getElementById("boothField_s");
	
	if(source == 'onChange')
	{	
		hiddenEl.value='';
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
			 row5El.style.display == 'none'

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
			/*if(row6El.style.display == 'none')
				row6El.style.display = '';*/
		}	 
}

function setLocationValue(value,source)
{ 
	var scopeLevelEl = document.getElementById("scopeLevel");
	var scopeLevelElVal = scopeLevelEl.options[scopeLevelEl.selectedIndex].text;
	
	if(value == '0')
	{
		alert("Please Select Valid Location"); 
		return; 
	}	
	var boothIdTextEl = document.getElementById("boothNoText");
	var hiddenEl = document.getElementById("groupScopeValueId"); 
	hiddenEl.value = '';
	if(source == 'onKeyUp')
	{
		hiddenEl.value = boothIdTextEl.value;	
	}
	else
	{
		hiddenEl.value = value;
	}
	if(scopeLevelElVal == 'BOOTH')
	{
		
	}
}

</script>
</head>
<body onload="populateLocations(${defaultGroupScope},'onChange')" class="bodyStyle">
<s:form action="saveLocalGroupAction" method="GET" theme="simple" name="form">
 <div id="wrapper" style="width:550px;">
   <table cellspacing="0" cellpadding="0" border="0" align="center" width="100%">
    <tbody>
	 
	 <tr height="45" valign="bottom">
	   <td>
	     <br>
		  <img hspace="0" title="partyanalyst.com" src="images/icons/homePage_new/logo.png">
	   </td>
	   <td></td>
	 </tr>
	 
	 <tr>
	   <td height="10"></td>
	 </tr>

	 <tr>
	   <td height="3" bgcolor="#e5e5e5" colspan="2"></td>
	 </tr>

	 <tr>
	   <td height="10"></td>
	 </tr>

    </tbody>

   </table>
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
   <table class="formTableStyle" height="64" cellpadding="0" cellspacing="0" border="0" align="center" width="660">
    <tbody>
	  <tr>
	    <td class="labelStyle" colspan="5">Create a Group</td>
	  </tr>
	  <tr>
	    <td height="17"></td>
	  </tr>
	  <tr>
	    <td width="100px">Select Group Category<font class="required">*</font></td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <s:select id="categorysId" name="groupCategoryId" cssStyle="width:150px;" list="#session.USER_GROUP_CATEGORIES" listKey="id" listValue="name" onchange=""></s:select>
		</td>
	  </tr>
	  <tr>
	    <td width="100px"></td>
		<td width="14">
		  <b> </b>
		</td>
		<td width="185">
		 <input class="btn_crtCategory" type="button" onclick="showHideCategoryData(1)" value="Create Category">
		</td>
	  </tr>
	  <tr id="catgrNameRow" style="display:none;">
	    <td width="100px"></td>
		<td width="14">
		  <b> </b>
		</td>
		<td width="185">
		 Category &nbsp;&nbsp;&nbsp;:  <input id="categoryName" type="text" maxlength="61" style="width:150px;" value="" name="catgryName">
		</td>
	  </tr>
	  <tr id="catgrDescRow" style="display:none;">
	    <td width="100px"></td>
		<td width="14">
		  <b> </b>
		</td>
		<td width="185">
		Desciption :  <input id="categoryDesc" type="text" maxlength="61" style="width:150px;" value="" name="catgryDesc">
		  <input class="btn_sbtCategory" type="button" onclick="" value="Create">
		  <a href="javascript:{}" onclick="showHideCategoryData(0)">Hide</a>
		</td>
	  </tr>

	  <tr>
	    <td width="100px">Enter Group Name<font class="required">*</font></td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <input id="localGroupName" type="text" maxlength="61" style="width:150px;" value="" name="localGroupName">
		</td>
	  </tr>

      <tr><td>&nbsp;</td></tr>
	   <tr>
	    <td width="100px">Enter Group Description</td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <textarea id="." cols="10" rows="5" style="width:150px;" name="localGroupDesc" onkeyup='limitText("localGroupDesc","maxcount",200)'>
		  </textarea>
		</td>
	  </tr>

      <tr><td>&nbsp;</td></tr>
	  <tr>
	    <td width="100">Select Group Scope<font class="required">*</font></td>
		<td width="14">
		  <b> : </b>
		</td>
		<td width="185">
		  <s:select id="scopeLevel" name="groupScopeId" cssStyle="width:150px;" list="#session.USER_GROUP_SCOPES" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Scope" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange')"></s:select>
		</td>
	  </tr>
	  </tbody>
   </table>
   <table class="formTableStyle" width="100%">	
	  <tr id="row1" width="100px" style="display:none;">
			<td width="214px" >State<font class="required">*</font></td>
			<td width="27px">
			<b> : </b></td>
			<td>
			<s:select id="stateField" cssClass="selectWidth" name="pstate" list="#session.statesList" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','newProblemPost','districtField','currentAdd', 'null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>
		 <tr id="row2" width="100px" style="display:none;">
			<td width="214px" >District<font class="required">*</font></td>
			<td width="27px">
			<b> : </b></td>
			<td>
			<s:select id="districtField" cssClass="selectWidth" name="pstate" list="#session.districtsList" listKey="id" listValue="name" value="defaultDistrict" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'newProblemPost','constituencyField','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>
		 <tr id="row3" width="100px" style="display:none;" >
			<td width="214px" >Constituency<font class="required">*</font></td>
			<td width="27px">
			<b> : </b></td>
			<td>
			<s:select id="constituencyField" cssClass="selectWidth" name="pstate" list="#session.constituenciesList" listKey="id" listValue="name" value="defaultConstituency" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'newProblemPost','mandalField','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
		  </td>
	    </tr>
		 <tr id="row4" width="100px" style="display:none;" >
			<td width="214px" >Tehsil/Municipality/Corporation<font class="required">*</font></td>
			<td width="27px">
			<b> : </b></td>
			<td>
			<s:select id="mandalField" cssClass="selectWidth" name="pstate" list="#session.mandalsList" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','null','cadreLevel','constituencyField', 'row6', 'row5');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
		  </td>
	    </tr>
		 <tr id="row5" width="100px" style="display:none;" >
			<td width="214px" >Village/Ward<font class="required">*</font></td>
			<td width="27px">
			<b> : </b></td>
			<td>
			<s:select id="hamletField_s" cssClass="selectWidth" name="pstate" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="getBoothsInWard('cadreLevel','constituencyField','boothField_s',this.options[this.selectedIndex].value,'cadreReg','mandalField');setLocationValue(this.options[this.selectedIndex].value)"></s:select>
		  </td>
	     </tr>
		 <tr id="row6" width="100px" style="display:none;">
			<td width="214px" >Booth<font class="required">*</font></td>
			<td width="27px">
			<b> : </b></td>
			<td>
			<s:select id="boothField_s" cssClass="selectWidth" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
	</tr>
	</table>
	
 </div>
 <s:hidden id="groupScopeValueId" name="groupScopeValueId"/>
 
  <table class="formTableStyle" width="100%">	
  <tr>
	    <td height="20px"></td>
  </tr>
	<tr>
		<td width="150px"></td><td width="90px"></td>
		 <td><div align="right">
			<s:submit cssClass="button" cssStyle="width:130px;" value="Save Group" name="Save"></s:submit>
			</div>
		</td>
	</tr>
	</table>
</s:form>
<script type="text/javascript">
</script>
</body>
</html>