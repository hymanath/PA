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
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

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

	<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>
	<!-- JQuery files Start-->
	<script type="text/javascript" src="js/jQuery/jquery-1.5.2.js"></script>
	<!-- JQuery files End -->
	<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>
	<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js">
	</script>
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>

	
<style type="text/css">

  .formTableStyle {
	font-family:arial;
	font-size:11px;
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
	padding:8px;
}

.regionSelect
{
	width:146px;
}
.selectWidth
{
	width:150px;
}

.fieldsetEle
{
	border:4px solid #F6DFC9;
	margin-bottom:10px;
	padding:5px;
}

.bodystyles 
{
	font-family:verdana;
	font-size:10px;
}

.addLocalGroupHeader {
	background-image:url("images/icons/cadreReport/bg_center.png");
	background-repeat:repeat-x;
	color:#FFFFFF;
	font-size:18px;
	font-weight:bold;
	height:24px;
	padding-top:1px;
	text-align:center;
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

//	var hiddenEl = document.getElementById("groupScopeValueId");
	var stateFieldEl = document.getElementById("stateField_s");
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
function userGroupCatagory(value){
	
var categorysIdElmt = document.getElementById("categoryId");
var str='';
categorysIdElmt.innerHTML = str;
	if(value =='Others'){
		str+='<input type=text id="groupCategoryId" name="groupCategory" value="Group Catagory" onclick="hideText()" onblur="showText()" onChange="addUserGroupCatagory()">';
		categorysIdElmt.innerHTML = str;
	}
}
function showText(){
	
   if($("#posId").val() == ""){
		$("#posId").val("Enter Position");
	}

}
function hideText(){

	if($("#posId").val() == "Enter Position"){
		$("#posId").val('');
	}

}
function addUserGroupCatagory(){
	
 var groupName = document.getElementById("groupCategoryId").value;
 
	var jsObj ={
		group :groupName,
		task:"addGroupCatagory"
      }
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "addUserGroupCatagoryAjaxAction.action?"+rparam;						
	ajaxCall(rparam,jsObj,url);
}

function ajaxCall(param,jsObj,url){
	
	var myResults;
					
	var callback = {			
		             success : function( o ) 
					  {
					try {												
						if(o.responseText)
						myResults = YAHOO.lang.JSON.parse(o.responseText);
								
						if(jsObj.task == "addGroupCatagory")
						 {	
							clearOptionsListForSelectElmtId("categorysId");
							createOptionsForSelectElmtId("categorysId",myResults);
							document.getElementById("categoryId").innerHTML = '';
						 }
                		}  
						catch (e)
							{   
							   	alert("Invalid JSON result" + e);   
							}	  
			              },
			  scope : this,
			  failure : function(o) 
				{
			      	//alert( "Failed to load result" + o.status + " " + o.statusText);
			     }
	};

  YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
</script>
</head>
<body class="bodyStyles">
<center>
<TABLE border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
			<TR>
				<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
					<c:if test="${windowTask == 'edit'}">
						<TD><div class="addLocalGroupHeader"><span style="margin-top:2px;">Edit a Group </span></div></TD>
					</c:if>
					<c:if test="${windowTask != 'edit'}"> 
						<TD><div class="addLocalGroupHeader"><span style="margin-top:2px;">Create a Group </span></div></TD>
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

	<c:if  test="${resultStatus == 'success'}">
	<div id="successMsg" style="color:green;" >Local User Group Added Successfully!</div>
	</c:if>	
	<c:if  test="${resultStatus == 'failure'}">
	<div id="successMsg" style="color:red;" style="color:green;">Error Raised while saving data please check log for details</div>
	</c:if>
	<s:form action="saveLocalGroupAction" method="POST" theme="simple" name="form">
   <FIELDSET class="fieldsetEle">
	<LEGEND style="font-size:12px;"><strong>Local Group Details</strong></LEGEND>
	<table class="formTableStyle" cellpadding="0" cellspacing="0" border="0" align="left">
  	 <tr>
	    <td class="tdstyle">Group Category<font class="required">*</font></td>
		<td><s:select id="categorysId" name="groupCategoryId" cssStyle="width:180px;" list="#session.USER_GROUP_CATEGORIES" listKey="id" listValue="name" onchange="userGroupCatagory(this.options[this.selectedIndex].text)"></s:select>
		</td><td><span id="categoryId"></span></td>
		<td class="tdstyle">Group Name<font class="required">*</font></td>
		<td><s:textfield id="localGroupName" style="width:180px;" theme="simple" maxlength="61" name="localUserGroupName"/></td>
	 </tr>
	 
	 <!--<tr><td></td>
		<td width="14"></td>
		<td width="185">
		 <input class="btn_crtCategory" type="button" onclick="showHideCategoryData(1)" value="Create Category">
		</td>
	  </tr>  -->
	  <tr id="catgrNameRow" style="display:none;">
	    <td></td>
		<td class="tdstyle">Category &nbsp;&nbsp;&nbsp;:  <input id="categoryName" type="text" maxlength="61" style="width:150px;" value="" name="catgryName">
		</td>
	  </tr>
	  <tr id="catgrDescRow" style="display:none;">
	    <td class="tdstyle">Desciption :  <input id="categoryDesc" type="text" maxlength="61" style="width:150px;" value="" name="catgryDesc">
		  <input class="btn_sbtCategory" type="button" onclick="" value="Create">
		  <a href="javascript:{}" onclick="showHideCategoryData(0)">Hide</a>
		</td>
	  </tr>	  	   
	  <tr>
	    <td class="tdstyle">Group Description</td>
		<td colspan="3"><s:textarea id="" name="groupDesc" cols="10" rows="3" style="width:463px;"/></td>
	  </tr>	    
</table>
</FIELDSET>
<FIELDSET class="fieldsetEle">
	<LEGEND style="font-size:12px;"><strong>Contact Address</strong></LEGEND>
 <table class="formTableStyle" height="64" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="tdstyle"><s:label for="houseNoField" id="houseNoLabel"  value="%{getText('houseNo')}" /></td>
		<td><s:textfield id="houseNoField" name="houseNo" maxlength="25" /></td>
		<td class="tdstyle"><s:label for="streetField" id="streetLabel" value="%{getText('street')}" /></td>
		<td><s:textfield id="streetField" name="streetName" maxlength="100"  /></td>
	</tr>	
	<c:if test="${sessionScope.USER.accessType != 'MP'}">
		<tr>
			<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
			<td><s:select id="stateField_add" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','userGroupReg','districtField_add','currentAdd');cleanOptionsList('state')"></s:select></td>
			<td class="tdstyle"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}"/><font class="required"> * </font></td>
			<td><s:select id="districtField_add" cssClass="regionSelect" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','userGroupReg','constituencyField_add','currentAdd');cleanOptionsList('district')"></s:select></td>
		</tr>				
	</c:if>
	<c:if test="${sessionScope.USER.accessType == 'MP'}">
		<tr>
			<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
			<td><s:select id="stateField_add" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','userGroupReg','pConstituencyField_add','currentAdd');cleanOptionsList('state')"></s:select></td>
			<td class="tdstyle"><s:label for="pConstituencyField_add" id="pConstituencyField_addLabel"  value="%{getText('PCONSTITUENCY')}"/><font class="required"> * </font></td>
			<td><s:select id="pConstituencyField_add" cssClass="regionSelect" name="pConstituency" list="#session.p_constituencies" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','userGroupReg','constituencyField_add','currentAdd');cleanOptionsList('district')" ></s:select></td>
		</tr>
	</c:if>
	<tr>
		<td class="tdstyle"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('ACONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
		<td><s:select id="constituencyField_add" cssClass="regionSelect" name="constituency" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','userGroupReg','mandalField_add','currentAdd', 'null');cleanOptionsList('constituency')" headerKey="0" ></s:select></td>
		<td class="tdstyle"><s:label for="mandalField" id="mandalLabel"  value="%{getText('subRegions')}" /><font class="requiredFont"> * </font></td>
		<td><s:select id="mandalField_add" cssClass="regionSelect" name="mandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','userGroupReg','villageField_add','currentAdd');getBooths('currentAdd','constituencyField_add','boothField',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'userGroupReg','boothsInTehsilOrMunicipality')" ></s:select></td>
	</tr>
	<tr>
 		<td class="tdstyle"><s:label for="villageField" id="villageLabel"  value="%{getText('wardOrHamlet')}" /><font class="requiredFont"> * </font></td>
		<td><s:select id="villageField_add" cssClass="regionSelect" name="villageOrWard" list="#session.villagesList" listKey="id" listValue="name" onchange="getBoothsInWard('currentAdd','constituencyField_add','boothField',this.options[this.selectedIndex].value,'userGroupReg','mandalField_add');">
		</s:select></td>
		<td class="tdstyle"><s:label for="pinCodeField" id="pinCodeLabel" value="Pincode" /></td>
		<td><s:textfield id="pinCodeField_add" name="pincode" maxlength="10"/></td>		
	</tr>
	<tr>
		<th colspan="4" width="15px" class="tdstyle" style="color:#0000AA;"><u>Booth details are not compulsory</u></th>
	</tr>	
	<tr>
		<td class="tdstyle"><s:label for="boothField" id="boothLabel"  value="Booth" /></td>
		<td align="left"><s:select id="boothField" cssClass="regionSelect" name="booth" list="#session.boothsList" listKey="id" listValue="name" headerKey="0" headerValue="Select Booth"></s:select></td>		
	</tr>
 </table>

</FIELDSET>

<FIELDSET class="fieldsetEle">
<LEGEND style="font-size:12px;"><strong>Group Scope Details</strong></LEGEND>
<table class="formTableStyle" height="64" cellpadding="0" cellspacing="0" border="0" align="left">
 	 <tr>
	    <td class="tdstyle">Group Scope<font class="required">*</font></td>
		<td><s:select id="scopeLevel" name="groupScopeId" cssStyle="width:150px;" list="#session.USER_GROUP_SCOPES" listKey="id" listValue="name" value="defaultGroupScope" headerKey = "0" headerValue = "Select Scope" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
	  </tr>
	   <c:if test="${sessionScope.USER.accessType != 'MP'}">	 								
			<tr id="row1" style="display:none;">
				<td class="tdstyle"><s:label for="stateField_s" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
				<td><s:select id="stateField_s" cssClass="selectWidth" name="scopeState" list="#session.statesList" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','userGroupReg','districtField','currentAdd', 'null')"></s:select></td>
			</tr>
			<tr id="row2" style="display:none;">
				<td class="tdstyle"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}"/><font class="required"> * </font></td>
				<td><s:select id="districtField" cssClass="selectWidth" name="scopeDistrict" list="#session.districtsList" listKey="id" listValue="name" value="defaultDistrict" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'userGroupReg','constituencyField','currentAdd')"></s:select></td>
			</tr>
		</c:if>
		<c:if test="${sessionScope.USER.accessType == 'MP'}">
			<tr id="row1" style="display:none;">
				<td class="tdstyle"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="required"> * </font></td>
				<td><s:select id="stateField_s" cssClass="selectWidth" name="scopeState" list="#session.statesList" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','userGroupReg','pConstituencyField_s','currentAdd','null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
			</tr>
			<TR id="row2" style="display:none;">	
				<td class="tdstyle"><s:label for="parlConstituencyField" id="parlConstituencyLabel"  value="%{getText('PCONSTITUENCY')}" /><font class="requiredFont"> * </font></td>
				<TD><s:select id="pConstituencyField_s" cssClass="selectWidth" name="pConstituencyId" list="#session.p_constituencies" listKey="id" listValue="name" value="defaultPConstituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','userGroupReg','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></TD>
			</TR>
		</c:if>
	  
	  
	  <!--<tr id="row1" style="display:none;">
			<td width="135px" class="tdstyle">State<font class="required">*</font></td>
			<td><s:select id="stateField" cssClass="selectWidth" name="scopeState" list="#session.statesList" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','newProblemPost','districtField','currentAdd', 'null')"></s:select>
			</td>
		</tr>
		 <tr id="row2" style="display:none;">
			<td width="135px" class="tdstyle">District<font class="required">*</font></td>
				<s:select id="districtField" cssClass="selectWidth" name="scopeDistrict" list="#session.districtsList" listKey="id" listValue="name" value="defaultDistrict" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'newProblemPost','constituencyField','currentAdd')"></s:select>
			</td>
		</tr>
		 -->
		 <tr id="row3" style="display:none;" >
			<td class="tdstyle"><s:label for="parlConstituencyField" id="parlConstituencyLabel"  value="%{getText('ACONSTITUENCY')}" /><font class="requiredFont"> * </font></td>
			<td><s:select id="constituencyField" cssClass="selectWidth" name="scopeConstituency" list="#session.constituenciesList" listKey="id" listValue="name" value="defaultConstituency" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'userGroupReg','mandalField','currentAdd')"></s:select></td>
	    </tr>
		 <tr id="row4" style="display:none;" >
			<td class="tdstyle" width="105px"><s:label for="mandalField" id="mandalLabel"  value="Tehsil/Muncipality"/><font class="required"> * </font></td>
			<td><s:select id="mandalField" cssClass="selectWidth" name="scopeMandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'userGroupReg','null','cadreLevel','constituencyField', 'row6', 'row5')"></s:select></td>
	    </tr>
		 <tr id="row5" style="display:none;" >
			<td class="tdstyle" width="105px"><s:label for="hamletField_s" id="villageLabel"  value="Village/Ward/Division"/><font class="required"> * </font></td>
			<td><s:select id="hamletField_s" cssClass="selectWidth" name="scopeVillage" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="getBoothsInWard('cadreLevel','constituencyField','boothField_s',this.options[this.selectedIndex].value,'userGroupReg','mandalField')"></s:select></td>
	     </tr>
		 <tr id="row6" style="display:none;">
			<td class="tdstyle">Booth<font class="required">*</font></td>
			<td><s:select id="boothField_s" cssClass="selectWidth" name="scopeBooth" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange=""></s:select></td>
		</tr>
	</table>
	</FIELDSET>
	<table class="formTableStyle" width="100%">	
		<tr>
		<td width="300"></td>
		 <td><div align="right">
			<s:submit cssClass="button" cssStyle="width:130px;" value="Save Group" name="Save"></s:submit>
			</div>
		</td>
		</tr>
	</table>
<s:hidden id="windowTaskId" name="windowTask" value="%{windowTask}"/>
 <s:hidden id="localUserGroupId" name="localUserGroupId" value="%{localUserGroupId}" />
 <s:hidden id="defaultGroupScope" name="defaultGroupScope" value="%{defaultGroupScope}" />
	
	</s:form>
 </div>
<%-- <s:hidden id="groupScopeValueId" name="groupScopeValueId"/> --%>
 
</body>
<script type="text/javascript">
populateLocations(${defaultGroupScope},'onLoad');
</script>
</html>