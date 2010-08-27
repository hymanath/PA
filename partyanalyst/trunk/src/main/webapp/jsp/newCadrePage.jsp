<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration</title>
	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
	
	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	
<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script> 
<script type="text/javascript">


	var cadreObj = {partyCommittees:[]};	
	function setCadreValue(value){
		document.getElementById("cadreLevelValue").value=value;
		return true;
	}

	function getCadreLevelValues(name,value,id)
	{
		var cadreLevelElmt = document.getElementById("cadreLevelField");
		var cadreLevelElmtText = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].text;
		var cadreLevelElmtValue = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].value;
		
		if(name == "cadreLevelState" && cadreLevelElmtText == "State")
		{
			document.getElementById("cadreLevelValue").value=1;						
		}
		else if((name == "cadreLevelState" && cadreLevelElmtText == "District") || (name == "cadreLevelState" && cadreLevelElmtText == "Constituency") 
			|| (name == "cadreLevelState" && cadreLevelElmtText == "Mandal")|| (name == "cadreLevelState" && cadreLevelElmtText == "Village"))
		{
			getnextList("state",id,"true","null","null");				
		}
		else if(name == "cadreLevelDistrict" && cadreLevelElmtText == "Constituency")
		{
			getnextList("constituency",id,"true","null","null");				
		}
		else if(name == "cadreLevelDistrict" && (cadreLevelElmtText == "Mandal" || cadreLevelElmtText == "Village"))
		{
			getnextList("district",id,"true","null","null");				
		}
		else if(name == "cadreLevelMandal" && (cadreLevelElmtText == "Village"))
		{
			getnextList("mandal",id,"true","null","null");				
		}
		
		//if(document.getElementById("cadreLevelField").value == 5)
		//	getDistrictLevelValues(name,value,id)
	}
	function getStateList()
	{
		var cadreLevelElmt = document.getElementById("cadreLevelField");
		
		var stateElmt = document.getElementById("cadreLevelState");
		var districtElmt = document.getElementById("cadreLevelDistrict");
		var constituencyElmt = document.getElementById("cadreLevelConstituency");
		var mandalElmt = document.getElementById("cadreLevelMandal");
		var villageElmt = document.getElementById("cadreLevelVillage");

		if(!cadreLevelElmt || !stateElmt || !districtElmt || !constituencyElmt || !mandalElmt || !villageElmt)
			alert("Selected Element is null !!");
		
		var cadreLevelElmtText = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].text;
		var cadreLevelElmtValue = cadreLevelElmt.options[cadreLevelElmt.selectedIndex].value;

		var stateElmtText = stateElmt.options[stateElmt.selectedIndex].text;
		var stateElmtValue = stateElmt.options[stateElmt.selectedIndex].value;

		var districtElmtText = districtElmt.options[districtElmt.selectedIndex].text;
		var districtElmtValue = districtElmt.options[districtElmt.selectedIndex].value;

		var constituencyElmtText = constituencyElmt.options[constituencyElmt.selectedIndex].text;
		var constituencyElmtValue = constituencyElmt.options[constituencyElmt.selectedIndex].value;

		var mandalElmtText = mandalElmt.options[mandalElmt.selectedIndex].text;
		var mandalElmtValue = mandalElmt.options[mandalElmt.selectedIndex].value;

		var villageElmtText = villageElmt.options[villageElmt.selectedIndex].text;
		var villageElmtValue = villageElmt.options[villageElmt.selectedIndex].value;

		stateElmt.disabled = true;
		districtElmt.disabled = true;
		constituencyElmt.disabled = true;
		mandalElmt.disabled = true;
		villageElmt.disabled = true;
		//alert(cadreLevelElmtText);
					
		if(cadreLevelElmtText == "State")
		{
			stateElmt.disabled = false;	
			document.getElementById("cadreLevelValue").value=1;
		}
		else if(cadreLevelElmtText == "District")			
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
		}		
		else if(cadreLevelElmtText == "Constituency")
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
			constituencyElmt.disabled = false;
		}
		else if(cadreLevelElmtText == "Mandal")
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
			mandalElmt.disabled = false;
		}
		else if(cadreLevelElmtText == "Village")
		{
			stateElmt.disabled = false;
			districtElmt.disabled = false;
			mandalElmt.disabled = false;
			villageElmt.disabled = false;
		}

		getStatesNDistricts("cadreLevel",cadreLevelElmtText,cadreLevelElmtValue)
		
	}

	function getStatesNDistricts(level,text,value)
	{
		var jsObj=
			{
					type:level,
					reportLevel:text,
					selected:value
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;		
		callAjax(rparam, jsObj, url);
	}
	function getnextList(name,value,choice,type,selectBoxId)
	{
		
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:name,
					selected:value,
					changed:choice,
					addresstype: type,
					selectBoxId: selectBoxId
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;
		callAjax(rparam, jsObj, url);
	}

	function callAjax(param, jsObj, url){
		var myResults;			
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								if(jsObj.task == "designations")
								{
									fillDesignationOptions(myResults);
								} else 
								buildSelectOption(myResults, jsObj);								
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

	function getMandalList(name,value,choice,type,selectBoxId)
	{

		var districtLabel = document.getElementById("districtLabel");
		if(districtLabel==null){
			var districtField = document.getElementById("districtField");		
				document.getElementById("districtField").value = 0;				
		}	
		
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"Constituencies",
					selected:value,
					changed:choice,
					addresstype: type,
					selectBoxId: selectBoxId
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);			
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;			
		callAjax(rparam, jsObj, url);
	}
	function getConstituencyList(name,value,choice,type,selectBoxId)
	{
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"constituency",
					selected:value,
					changed:choice,
					addresstype: type,
					selectBoxId: selectBoxId
					
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);					
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;	
			callAjax(rparam, jsObj, url);
	}
	function buildSelectOption(results, jsObj)
	{		
		
		var selectedValue= jsObj.reportLevel;
		var taskType=jsObj.type;
		var changedVal = jsObj.changed;
		
		var selectedElmt;
		
		if(taskType == "cadreDetails")
		{
			if(selectedValue=="state")
			{
				if(changedVal == "false")
				{
					selectedElmt=document.getElementById(jsObj.selectBoxId);					
				}	
				else if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelDistrict");
			} else if(selectedValue=="pstate")
			{
			
				if(changedVal == "false")
				{
					selectedElmt=document.getElementById(jsObj.selectBoxId);					
				}	
				else if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelDistrict");
			} else if(selectedValue=="pdistrict")
			{
				if(changedVal == "false")
				{
					selectedElmt=document.getElementById(jsObj.selectBoxId);					
				}	
				else if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelDistrict");
			} else if(selectedValue=="district")
			{
				if(changedVal == "false")
					selectedElmt=document.getElementById(jsObj.selectBoxId);
				else if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelMandal");
			}
			else if(selectedValue=="constituency")
			{
				if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelConstituency");
				else if(changedVal == "false")
					selectedElmt=document.getElementById(jsObj.selectBoxId);
			}else if(selectedValue=="mandal")
			{
				if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelVillage");
				else if(changedVal == "false")
					selectedElmt=document.getElementById(jsObj.selectBoxId);
			}
			else if(selectedValue=="Constituencies")
				selectedElmt=document.getElementById(jsObj.selectBoxId);
		}
		else if(taskType=="cadreLevel")
		{
			if(selectedValue == "State" || selectedValue == "District" || selectedValue == "Constituency" || selectedValue == "Mandal" || selectedValue == "Village")
				selectedElmt=document.getElementById("cadreLevelState");
			else
				selectedElmt=document.getElementById("cadreLevelDistrict");
		}
		else if(selectedValue=="cadreLevelDistrict")
			selectedElmt=document.getElementById("cadreLevelMandal");
		else if(selectedValue=="cadreLevelMandal")
			selectedElmt=document.getElementById("cadreLevelVillage");
		
		
		var len=selectedElmt.length;			
		for(i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		} 	
		for(var val in results)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;
			
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}			
		}
	}
	function showPartyCommittee(value)
	{
		var cadreLevelTableEl = document.getElementById("cadreLevelTable");

		//var comiteeDesignationSelectEl = document.getElementById("comiteeDesignationSelect");
		if(value == "Active")			
		{
			cadreLevelTableEl.style.display ='block'
		} else if (value == "Normal")
		{
			cadreLevelTableEl.style.display ='none'
		}
				
	}
	function getPartyDesignation(value)
	{
		var jsObj=
		{
				task:"designations",				
				id:value				
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);					
		var url = "<%=request.getContextPath()%>/designationForCommitteAjaxAction.action?"+rparam;	
		callAjax(rparam, jsObj, url);
	}
	function fillDesignationOptions(results)
	{
		var designationFieldEl = document.getElementById("comiteeDesignationSelect");
		designationFieldEl.disabled = false;
		removeSelectElements(designationFieldEl);
		
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				designationFieldEl.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				designationFieldEl.add(opElmt); // IE only
				}
		}
	}
	function removeSelectElements(elmt)
	{
		if(!elmt)
			return;

		var len=elmt.length;			
		for(i=len-1;i>=0;i--)
		{
			elmt.remove(i);
		}	
	}
	function hideUnhidePrmntAddOptions()
	{
		
		var sameAsCAEl = document.getElementById("sameAsCA");
		var optElements = document.getElementById("permanantAddr");
		
		if(sameAsCAEl.checked == true)
		{	
				optElements.style.display = 'none';
		
		} else 
		{			
				if(optElements.style.display == "none")
					optElements.style.display = '';			
		}
	}
	function showHideEffOptions(id)
	{
		var effOptions = document.getElementsByName("languageEffOptions_"+id);
		
		for(i=0;i<effOptions.length;i++)
		{
			if(effOptions[i].disabled == true)
				effOptions[i].disabled = false;
			else if (effOptions[i].disabled == false)
			{
				effOptions[i].disabled = true;
				if(effOptions[i].checked == true)
					effOptions[i].checked = false;
			}	
		}
	}
	function enableDisableCalBtn()
	{
		var btnEl = document.getElementById("calBtnEl");
		var ageTextEl = document.getElementById("ageTextEl");
		if(btnEl.disabled == true)
		{	
			btnEl.disabled = false;
			ageTextEl.disabled = true;
		}	
		else if(btnEl.disabled == false)
		{
			ageTextEl.disabled = false;
			ageTextEl.focus();
			btnEl.disabled = true;
		}	
	}
	
</script>
<style type="text/css">
	
	#registrationMainDiv
	{
		text-align:left;
		margin-left:70px;
	}
	.bodyStyle {
		font-family:verdana;
		font-size:12px;
	}
	.cadreDetailsTable td {
		color:#926682;
		font-family:verdana;
		font-weight:bold;
		text-align:left;
	}
	.cadreDetailsTable th {
		color: #0000AA;
		font-family:verdana;
		font-weight:bold;
		text-align:left;
	}	
	fieldset {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width:820px;
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		padding:5px;
	}	
	.button {
		background:url("images/icons/brown_but.gif") no-repeat scroll 0 0 transparent;
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;
		height:28px;
		margin:10px;
		padding-top:2px;
		text-align:center;
		text-decoration:none;
		width:70px;
	}
	.cadreReportHeader {
		background-image:url("images/icons/cadreReport/bg_center.png");
		background-repeat:repeat-x;
		color:#FFFFFF;
		font-size:14px;
		font-weight:bold;
		height:24px;
		padding-top:1px;
		text-align:center;
		width:150px;
	}
	.calBtn
	{
		background-image:url("images/icons/constituencyManagement/calendar.jpeg");
		height:24px;
		width:24px;	
	}
</style>
</head>
<body class="bodyStyle">
<s:form action="cadreRegisterAction" method="POST" theme="simple">
	<CENTER>
<TABLE border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
	<TR>
		<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
		<TD>
			<div class="cadreReportHeader"><span style="margin-top:2px;">Add New Cadre</span></div>
		</TD>
		<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>
	</TR>
</TABLE>
</CENTER>
	<div id="registrationMainDiv">
		<c:if test="${cadreId == null}">
			<DIV id="alertMessage" style="display:none;"></DIV>
		</c:if>
		<c:if test="${cadreId != null}">
			<DIV id="alertMessage" style="color:green;font-weight:bold">Cadre Registered Successfully!</DIV>
		</c:if>
	<table id="cadreRegistrationTable" class="registrationTable">
	<tr>
		<td colspan="2">
			<div style="color: red;">
				<s:actionerror />
				<s:fielderror />
			</div>
		</td>
	</tr>
	</table>

	<div id="loginDetailsDiv" class="accessDivMain">
		<div id="loginDetailsDivBody">
			<FIELDSET>
				<LEGEND><strong>Personal Details</strong></LEGEND>
				<table class="cadreDetailsTable">		
					<tr>
						<td><s:label for="firstNameField" id="fnameLabel"  value="%{getText('firstName')}" /><font class="requiredFont"> * </font></td>
						<td align="left"><s:textfield id="firstNameField" name="firstName" size="25"/></td>
						<td><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></td>
						<td align="left"><s:textfield id="middleNameField" name="middleName" size="25"/></td>
						<td><s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /><font class="requiredFont"> * </font></td>
						<td align="left"><s:textfield id="lastNameField" name="lastName" size="25"/>  </td>
					</tr>
					<tr>
						<td><s:label for="father_spouseName" id="father_spouseNameLabel"  value="%{getText('father_spouseName')}" /><font class="requiredFont"> * </font></td>
						<td align="left"><s:textfield id="father_spouseName" name="fatherOrSpouseName" size="25"/>  </td>
						<td><s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /><font class="requiredFont"> * </font></td>
						<td align="left">
							<input type="radio" name="gender" value="M"/>Male
							<input type="radio" name="gender" value="F"/>Female
						</td>
					</tr>
					<tr>
					<td colspan ="6">
						<table>
						<tr>
							<td colspan="6" style="font-weight:normal;color:black;">If you dont know exact "Date Of Birth", select "Age" option and enter approximate age in Age text box</td>							
						</tr>
						<tr>
						<td width="162"><input type="radio" name="dobOption" value="dobOption" onclick="enableDisableCalBtn()"/>Date Of Birth<font class="requiredFont"> * </font></td>
						<td align="left">
							<input type="text" id="dobText" readonly="readonly" name="dateOfBirth" size="25"/>
							<DIV class="yui-skin-sam"><DIV id="dobText_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td><input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" disabled="true" onclick="showDateCal('dobText_div','dobText','1/1970')"/></td>
						<td align="left">Or</td>	
						<td><input type="radio" name="dobOption" value="age" onclick="enableDisableCalBtn()"/>Age<font class="requiredFont"> * </font></td>
						<td align="left"><s:textfield id="ageTextEl" name="age" size="25"/> </td>
						</tr>
						</table>
					</td>			
					</tr>					
				</table>	
			</FIELDSET>
			<FIELDSET>
				<LEGEND><strong>Contact Details</strong></LEGEND>
				<table class="cadreDetailsTable" width="100%">
				<tr>
					<td><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /><font class="requiredFont"> * </font></td>
					<td align="left"><s:textfield id="mobileField" name="mobile" maxlength="10" size="25" />  </td>
					<td><s:label for="telePhoneField" id="telePhoneLabel"  value="%{getText('telephoneNo')}" /></td>
					<td align="left"><s:textfield id="telePhoneField" name="telephone" maxlength="10" size="25" />  </td>
				</tr>
				<tr>
					<td><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></td>
					<td align="left" colspan="3"><s:textfield id="emailField" name="email" size="75"/>  </td>
				</tr>
				<tr>
					<th><u><s:label for="currAddField" id="currAddLabel"  value="%{getText('currAdd')}" /></u></th>
				</tr>
				<tr>
					<td><s:label for="houseNoField" id="houseNoLabel"  value="%{getText('houseNo')}" /><font class="requiredFont"> * </font></td>
					<td align="left"><s:textfield id="houseNoField" name="houseNo" maxlength="100" size="25" />  </td>
					<td><s:label for="streetField" id="streetLabel"  value="%{getText('street')}" /></td>
					<td align="left"><s:textfield id="streetField" name="street" maxlength="100" size="25" />  </td>
				</tr>
				<tr>
					<td><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="stateField" cssClass="regionSelect" name="state" list="#session.stateList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false','current','districtField')"></s:select>
					</td>
				<c:if test="${sessionScope.USER.accessType != 'MP'}"> 
					<td><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
						<td align="left">
							<select id="districtField" class="regionSelect" name="district" onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,'false','current','constituencyField')" <c:if test="${sessionScope.USER.accessType == 'MP'}"> <c:out value="disabled='disabled'" /></c:if> >
								<c:forEach var="dist" items="${districtList}" >
								<option value="${dist.id}">${dist.name}</option>
								</c:forEach>
							</select>							
					</td>
				</c:if>
				</tr>
				<c:if test="${sessionScope.USER.accessType == 'MP'}"> 	
					<tr>
						<td>
							<input type="hidden" id="districtField" name="district">
						</td>
					</tr>
				</c:if>
				<tr>
					<td><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="constituencyField" cssClass="regionSelect" name="constituency" list="#session.constituencyList" listKey="id" listValue="name" onchange="getMandalList(this.name,this.options[this.selectedIndex].value,'false','current','mandalField')" headerKey="-1" headerValue="Select Constituency"></s:select> 
					</td>
					<td><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="mandalField" cssClass="regionSelect" name="mandal" list="#session.mandalList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false','current','villageField')" headerKey="-1" headerValue="Select Mandal"></s:select>				 
					</td>
				</tr>
				<tr>
					<td><s:label for="villageField" id="villageLabel"  value="%{getText('VILLAGE')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="villageField" cssClass="regionSelect" name="village" list="#session.villageList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Village"></s:select>				
					</td>
					<td><s:label for="pinCodeField" id="pinCodeLabel"  value="%{getText('pincode')}" /><font class="requiredFont"> * </font></td>
					<td align="left"><s:textfield id="pinCodeField" name="pinCode" maxlength="10" size="25" />  </td>
				</tr>				
				<tr>
					<th><u><s:label for="prmntAddField" id="prmntAddLabel"  value="%{getText('officialAdd')}" /></u></th>
				</tr>
				<tr>
					<td align="left" colspan="2">
						<input type="checkbox" id="sameAsCA" name = "sameAsCA" onclick="hideUnhidePrmntAddOptions()" />Same As Current Address				
					</td>
				</tr>
				</table>
				<table id="permanantAddr" class="cadreDetailsTable" width="100%">
					<tr>
						<td><s:label for="phouseNoField" id="phouseNoLabel"  value="%{getText('houseNo')}" /><font class="requiredFont"> * </font></td>
						<td align="left"><s:textfield id="phouseNoField" name="phouseNo" maxlength="10" size="25" /></td>
						<td><s:label for="pstreetField" id="pstreetLabel"  value="%{getText('street')}" /></td>
						<td align="left"><s:textfield id="pstreetField" name="pstreet" maxlength="10" size="25" /></td>
					</tr>
					<tr>
						<td><s:label for="pstateField" id="pstateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
						<td align="left">
							<s:select id="pstateField" cssClass="regionSelect" name="pstate" list="#session.stateList" listKey="id" listValue="name" onchange="getnextList('state',this.options[this.selectedIndex].value,'false','permanent','pdistrictField')"></s:select>
						</td>
					<c:if test="${sessionScope.USER.accessType != 'MP'}"> 
						<td><s:label for="pdistrictField" id="pdistrictLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
							<td align="left">
								<select id="pdistrictField" class="regionSelect" name="pdistrict" onchange="getConstituencyList('district',this.options[this.selectedIndex].value,'false','permananent','pconstituencyField')" <c:if test="${sessionScope.USER.accessType == 'MP'}"> <c:out value="disabled='disabled'" /></c:if> >
									<c:forEach var="dist" items="${districtList}" >
									<option value="${dist.id}">${dist.name}</option>
									</c:forEach>
								</select>							
						</td>
					</c:if>
					</tr>
					<c:if test="${sessionScope.USER.accessType == 'MP'}"> 	
						<tr >
							<td>
								<input type="hidden" id="districtField" name="pdistrict">
							</td>
						</tr>
					</c:if>
					<tr>
						<td><s:label for="pconstituencyField" id="pconstituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
						<td align="left">
							<s:select id="pconstituencyField" cssClass="regionSelect" name="pconstituency" list="#session.constituencyList" listKey="id" listValue="name" onchange="getMandalList('constituency',this.options[this.selectedIndex].value,'false','permanent','pmandalField')" headerKey="-1" headerValue="Select Constituency"></s:select> 
						</td>
						<td><s:label for="pmandalField" id="pmandalLabel"  value="%{getText('MANDAL')}" /><font class="requiredFont"> * </font></td>
						<td align="left">
							<s:select id="pmandalField" cssClass="regionSelect" name="pmandal" list="#session.mandalList" listKey="id" listValue="name" onchange="getnextList('mandal',this.options[this.selectedIndex].value,'false','permananent','pvillageField')" headerKey="-1" headerValue="Select Mandal"></s:select>				 
						</td>
					</tr>
					<tr>
						<td><s:label for="pvillageField" id="pvillageLabel"  value="%{getText('VILLAGE')}" /><font class="requiredFont"> * </font></td>
						<td align="left">
							<s:select id="pvillageField" cssClass="regionSelect" name="pvillage" list="#session.villageList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Village"></s:select>				
						</td>
						<td><s:label for="ppinCodeField" id="ppinCodeLabel"  value="%{getText('pincode')}" /><font class="requiredFont"> * </font></td>
						<td align="left"><s:textfield id="ppinCodeField" name="ppinCode" maxlength="10" size="25" />  </td>
					</tr>				
				</table>
			</FIELDSET>
		</div>
		</div>
		<div id="otherDetailsDiv">
			<fieldset>
			<legend><strong>Social Status</strong></legend>
			<table class="cadreDetailsTable">
				<tr>
					<th colspan="4"><s:label for="languageField" id="languageLabel"  value="%{getText('languageEff')}" /><font class="requiredFont"> * </font></th>
				</tr>	
				<c:forEach var="lang" items="${sessionScope.languagesList}" >
					<tr>
						<td><input type="checkbox" id="${lang.id}" value="${lang.id}" onclick="showHideEffOptions(this.id)"/>${lang.name}</td>
						<td><input type="checkbox" name="languageEffOptions_${lang.id}" disabled="true"/>Can Speak</td>
						<td><input type="checkbox" name="languageEffOptions_${lang.id}" disabled="true"/>Can Read</td>
						<td><input type="checkbox" name="languageEffOptions_${lang.id}" disabled="true"/>Can Write</td>
					</tr>
				</c:forEach>		
				<tr>
					<td width="130"><s:label for="educationField" id="educationLabel"  value="%{getText('education')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
							<s:select id="educationField" cssClass="regionSelect" name="education" list="#session.eduStatus" listKey="id" listValue="name"  headerKey="-1" headerValue="Select Education"></s:select>				
					</td>
					<td><s:label for="professionField" id="professionLabel"  value="%{getText('profession')}" /></td>
					<td align="left"><s:select id="professionField" name="profession"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="-1" headerValue="Select Occupation"></s:select></td>
				</tr>				
				<tr>
						<td><s:label for="incomeField" id="incomeLabel"  value="%{getText('income')}" /></td>
						<td align="left"><s:textfield id="incomeField" name="income" size="25"/></td>
						<td width="100px;"><s:label for="socialStatusField" id="socialStatusLabel"  value="%{getText('socialStatus')}" /><font class="requiredFont"> * </font></td>
						<td style="padding-left: 10px;"><s:radio id="socialStatusField" name="socialStatus" list="#session.socialStatus" listKey="id" listValue="name" required="true"></s:radio> </td>
					</tr>
			</table>
			</fieldset>
		</div>
		<div id="personalDetailsDiv" class="accessDivMain">
		
		<div id="personalDetailsDivBody" class="accessDivBody">
		<fieldset>
			<legend><strong>Cadre Lavel Details</strong></legend>
			<table class="cadreDetailsTable" border="0">
			<tr>
				<td><s:label for="cadreTypeField" id="cadreTypeLabel"  value="%{getText('memberType')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
					<input type="radio" name="memberType" value="Active" onclick="showPartyCommittee(this.value)"/>Active
					<input type="radio" name="memberType" value="Normal" onclick="showPartyCommittee(this.value)"/>Normal
				</td>
			</tr>
			</table>
			<table class="cadreDetailsTable" width="100%" id="cadreLevelTable" style="display:none;">
			<tr>
				<td><s:label for="cadreLevelField" id="cadreLevelLabel"  value="%{getText('CADRE_LEVEL')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
					<select id="cadreLevelField" name="cadreLevel" onchange="getStateList()">
						<option	 value='0'>Select Level</option>		
						<option  value='2'>State</option>	
						<option  value='3'>District</option>
						<option  value='4'>Constituency</option>	
						<option  value='5'>Mandal</option>		
						<option  value='6'>Village</option>					
					</select> <input type='hidden' name='cadreLevelValue' id='cadreLevelValue'>
				</td>
			</tr>
			<tr>		
				<td><s:label for="cadreLevelValueField" id="cadreLevelValueLabel"  value="%{getText('CADRE_LEVEL_VALUE')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
					<select id="cadreLevelState" name="cadreLevelState" disabled = "true" onchange="setCadreValue(this.options[this.selectedIndex].value);
											getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
											this.options[this.selectedIndex].value)">
						<option>Select State</option>					
					</select> 
				</td>
				<td>	
					<select id="cadreLevelDistrict" name="cadreLevelDistrict" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);
											getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
											this.options[this.selectedIndex].value)">
						<option>Select District</option>					
					</select> 
				</td>
				<td>	
					<select id="cadreLevelConstituency" name="cadreLevelConstituency" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);
											getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
											this.options[this.selectedIndex].value)">
						<option>Select Constituency</option>					
					</select> 
				</td>
				<td>
					<select id="cadreLevelMandal" name="cadreLevelMandal" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);
											getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
											this.options[this.selectedIndex].value)">
						<option>Select Mandal</option>					
					</select> 
				</td>
				<td>	
					<select id="cadreLevelVillage" name="cadreLevelVillage" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value)">
						<option>Select Village</option>					
					</select> 
				</td>
			</tr>
			<c:if test="${sessionScope.USER.userType == 'Party'}">
			<tr>
				<td><s:label for="partyCommField" id="partyCommLabel"  value="%{getText('partyCommittee')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
				<select id="partyComiteSelect" name="partyComite" onchange="getPartyDesignation(this.options[this.selectedIndex].value)">
						<option>Please Select</option>
						<c:forEach var="partyCommittee"  items="${partyCommitteesList}" >
						<option value='${partyCommittee.id}'>${partyCommittee.name}</option>	
						</c:forEach>					
					</select>
				</td>
				<td><s:label for="designationCommField" id="designationCommLabel"  value="%{getText('designation')}" /><font class="requiredFont"> * </font></td>
				<td>
				<select id="comiteeDesignationSelect" name="designation" disabled="true">
					<option>Please Select</option>											
				</select>
				</td>				
			</tr>
			<tr>
					<td><s:label for="durationField" id="durationLabel"  value="%{getText('effectiveDate')}" /><font class="requiredFont"> * </font></td>
					<td colspan="4" align="left">					
					<table class="cadreDetailsTable">
						<tr>				
							<td align="left">
									<input type="text" id="effDateText" readonly="readonly" name="effectiveDate" size="15"/>
									<DIV class="yui-skin-sam"><DIV id="effDateText_div" style="position:absolute;"></DIV></DIV>
							</td>
							<td>		
									<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('effDateText_div','effDateText','1/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>
							</td>
							<td>To</td>
							<td>
									<input type="text" id="tillDateText" readonly="readonly" name="endingDate" size="15"/>
									<DIV class="yui-skin-sam"><DIV id="tillDateText_div" style="position:absolute;"></DIV></DIV>
							</td>
							<td>		
									<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('tillDateText_div','tillDateText','1/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>
							</td>
						</tr>	
					</table>	
					</td>								
			</tr>
			<tr>
				<th><u>Cadre Skills</u></th>
			</tr>
			<tr>	
				<td colspan="5">
					<table>
					<tr>
					<c:forEach var="skills" items="${sessionScope.cadreSkillsList}" varStatus="status" >
								
							<td><input type="checkbox" name="skills" id="${skills.id}" value="${skills.id}"/>${skills.name}</td>
							<c:if test="${status.count % 3 == 0}">
								</tr><tr>
							</c:if>						
					</c:forEach>
					</tr>	
					</table>
				</td>
			</tr>
			<tr>
				<th><u>Participated Training Camps</u></th>
			</tr>
			<tr>	
				<td colspan="5">
					<table>
					<tr>
					<c:forEach var="trainingCamps" items="${sessionScope.partyTrainingCampsList}" varStatus="status" >
								
							<td><input type="checkbox" name="trainingCamps" id="${trainingCamps.id}" value="${trainingCamps.id}"/>${trainingCamps.name}</td>
							<c:if test="${status.count % 5 == 0}">
								</tr><tr>
							</c:if>						
					</c:forEach>
					</tr>	
					</table>
				</td>
			</tr>
			</c:if>
			</table>
			</fieldset>
		</div></div>
		<div style="text-align: center;">
			<input type="submit" value="Register" class="button">
		</div>
	</div>
	</s:form>
	<script type="text/javascript">


	<c:forEach var="partyCommittee"  items="${partyCommitteesList}" >
var ob={
			id:'${partyCommittee.id}',
			value:'${partyCommittee.name}'
		};
cadreObj.partyCommittees.push(ob);	
</c:forEach>
</script>
</body>

varStatus

</html>