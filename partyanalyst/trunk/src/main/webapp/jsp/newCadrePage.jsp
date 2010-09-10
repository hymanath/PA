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
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>	
	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	
 
<script type="text/javascript">
	var currentTask = '${windowTask}';
	var successFlag = '${rs.resultCode}';
	
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
		if(value == '-1')
		{
			alert("Please Select Valid Location");
			return;
		}	
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
		
		if(value == '0')
		{
			alert("Please Select Valid Location");
			return;
		}
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
		if(value == '0')
		{
			alert("Please Select Valid Location");
			return;
		}
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
	function hideUnhidePrmntAddOptions(source)
	{
		
		var sameAsCAEl = document.getElementById("sameAsCA");
		var optElements = document.getElementById("permanantAddr");
		
		if(sameAsCAEl.checked == true && source == 'checkbox')
		{	
				optElements.style.display = 'none';
		
		} else if(source == 'button') 
		{			
				if(optElements.style.display == "none")
					optElements.style.display = '';	
				if(sameAsCAEl.checked == true)
					sameAsCAEl.checked = false;							
		} else
		{
			if(optElements.style.display == "none")
				optElements.style.display = '';
		}	
	}
	function hideUnhideCrntAddOptions()
	{
		var btnEl = document.getElementById("editCrntBtn");
		var optElement = document.getElementById("cuurrentAddTable");
		
		if(btnEl.checked == true)
		{	
			optElement.style.display = 'none';
		
		} else 
		{			
				if(optElement.style.display == "none")
					optElement.style.display = '';			
		}
	}
		
	function enableDisableCalBtn(selectedOption)
	{		
		var btnEl = document.getElementById("calBtnEl");
		var ageTextEl = document.getElementById("ageTextEl");
		var dobTextEl = document.getElementById("dobText");
		if(selectedOption == 'dobOption')
		{	
			btnEl.disabled = false;
			ageTextEl.disabled = true;
			ageTextEl.value = '';
		}	
		else if(selectedOption == 'age')
		{
			ageTextEl.disabled = false;
			ageTextEl.focus();
			btnEl.disabled = true;
			dobTextEl.value = '';
		}	
	}
	function validateClientSide()
	{
		var flag;
		
		var sameAsCAEl = document.getElementById("sameAsCA");
		var locationError_cEl = document.getElementById("locationError_c");
		var locationError_pEl = document.getElementById("locationError_p");
		//current address fields
		var stateFieldEl = document.getElementById("stateField");
		var districtFieldEl = document.getElementById("districtField");
		var constituencyFieldEl = document.getElementById("constituencyField");
		var mandalFieldEl = document.getElementById("mandalField");
		var villageFieldEl = document.getElementById("villageField");
		//selected values
		var stateFieldSelected = stateFieldEl.options[stateFieldEl.selectedIndex].value;
		var districtFieldElSelected = districtFieldEl.options[districtFieldEl.selectedIndex].value;
		var constituencyFieldElSelected =  constituencyFieldEl.options[constituencyFieldEl.selectedIndex].value;
		var mandalFieldElSelected = mandalFieldEl.options[mandalFieldEl.selectedIndex].value; 
		var villageFieldElSelected = villageFieldEl.options[villageFieldEl.selectedIndex].value;

		//official address
		var pstateFieldEl = document.getElementById("pstateField");
		var pdistrictFieldEl = document.getElementById("pdistrictField");
		var pconstituencyFieldEl = document.getElementById("pconstituencyField");
		var pmandalFieldEl = document.getElementById("pmandalField");
		var pvillageFieldEl = document.getElementById("pvillageField");
		//selected values
		var pstateFieldSelected = pstateFieldEl.options[pstateFieldEl.selectedIndex].value;
		var pdistrictFieldSelected = pdistrictFieldEl.options[pdistrictFieldEl.selectedIndex].value;
		var pconstituencyFieldSelected = pconstituencyFieldEl.options[pconstituencyFieldEl.selectedIndex].value;
		var pmandalFieldSelected = pmandalFieldEl.options[pmandalFieldEl.selectedIndex].value;
		var pvillageFieldSelected = pvillageFieldEl.options[pvillageFieldEl.selectedIndex].value;	 

		locationError_cEl.innerHTML = '';
		locationError_pEl.innerHTML = '';
		//current address
		if(stateFieldSelected == '0' || districtFieldElSelected == '0' || constituencyFieldElSelected == '0' || mandalFieldElSelected == '0' || villageFieldElSelected == '0')
		{
			locationError_cEl.innerHTML = 'Invalid selection in current address ';
			flag = false;
		} else if (sameAsCAEl.checked == false )
		{
			if(pstateFieldSelected == '0' || pdistrictFieldSelected == '0' || pconstituencyFieldSelected == '0' || pmandalFieldSelected == '0' || pvillageFieldSelected == '0')
			{
				locationError_pEl.innerHTML = 'Invalid selection in permanat address ';
				flag = false;
			}
				
		} else {
				flag = true;
			}
		
		return flag;
	}

	function executeOnload()
	{
		var textBoxEl = document.getElementById("firstNameField");
		textBoxEl.focus();
		
		var samsAsCaEl = document.getElementById("sameAsCA");
		var permanantAddrEl = document.getElementById("permanantAddr");
		var cuurrentAddTableEl = document.getElementById("cuurrentAddTable");
		var memberTypeRadioEl = document.getElementById("memberTypeActive");
		var cadreLevelTableEl = document.getElementById("cadreLevelTable"); 

		if(samsAsCaEl.checked == true)				
		{
			permanantAddrEl.style.display = 'none';
				
		}
		if(memberTypeRadioEl.checked == true)
		{
			if(cadreLevelTableEl.style.display == 'none')
				cadreLevelTableEl.style.display = 'block';
		}
		if(currentTask == 'update_existing')
		{
			cuurrentAddTableEl.style.display = 'none';
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
	.adresDetailsTable th {
		color:#926682;
		font-family:verdana;
		font-weight:bold;
		text-align:left;
	}
	.adresDetailsTable td {
		
		font-family:verdana;
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
		background-color:#0000AA;
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;
		margin:10px;
		padding:2px;
		text-align:center;
		text-decoration:none;
	}
	.anchor
	{
		background-color:#0000AA;
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;
		margin:10px;
		padding:2px;
		text-align:center;
		text-decoration:none;
		height:25px;
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
	.addressDetailsDiv
	{
		background-color:Silver;
		border: 3px solid #CFD6DF;
		margin-top:5px;
		margin-bottom:5px;
		padding:3px;
	}
</style>
</head>
<body class="bodyStyle" onunload="loadOnUnload()">
<s:form action="cadreRegisterAction" method="POST" theme="simple" onsubmit="return validateClientSide()">
	<CENTER>
		<TABLE border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
			<TR>
				<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
				<c:if test="${windowTask == 'new'}">
					<TD><div class="cadreReportHeader"><span style="margin-top:2px;">Add New Cadre</span></div></TD>
				</c:if>
				<c:if test="${windowTask == 'update_existing'}">
					<TD><div class="cadreReportHeader"><span style="margin-top:2px;">Update Cadre Details</span></div></TD>
				</c:if>
				<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>	
			</TR>
		</TABLE>
	</CENTER>
	
	<div id="registrationMainDiv">
		<c:if test="${rs.resultCode !=  '' && rs.resultCode == '0'}">
			<DIV id="alertMessage" style="color:green;font-weight:bold">Cadre Registered Successfully!</DIV>			
		</c:if>
		<table id="cadreRegistrationTable" class="registrationTable">
		<tr>
			<td colspan="2">
				<div id="locationError_c" style="color: red;"></div>
				<div id="locationError_p" style="color: red;"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div style="color: red;">
					<s:actionerror />
					<s:fielderror />
				</div>
			</td>
		</tr>
	</table>
	<input type="hidden" id="hiddenVal" name="cadreId" value="${cadreId}"/>
	<input type="hidden" id="hiddenValue" name="windowTask" value="${windowTask}"/>
	<FIELDSET>
		<LEGEND><strong>Personal Details</strong></LEGEND>
		<table class="cadreDetailsTable">		
			<tr>
				<td><s:label for="firstNameField" id="fnameLabel" value="%{getText('firstName')}" /><font class="requiredFont"> * </font></td>
				<td align="left"><s:textfield id="firstNameField" key="cadreInfo.firstName" name="firstName" size="25" tooltip="true" maxlength="40"/></td>
				<td><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></td>
				<td align="left"><s:textfield id="middleNameField" key="cadreInfo.middleName" name="middleName" size="25" tooltip="true" maxlength="40"/></td>
				<td><s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /><font class="requiredFont"> * </font></td>
				<td align="left"><s:textfield id="lastNameField" key="cadreInfo.lastName" name="lastName" size="25" maxlength="40"/>  </td>
			</tr>
			<tr>
				<td><s:label for="father_spouseName" id="father_spouseNameLabel"  value="%{getText('father_spouseName')}" /><font class="requiredFont"> * </font></td>
				<td align="left"><s:textfield id="father_spouseName" name="fatherOrSpouseName" size="25" maxlength="100"/>  </td>
				<td><s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
					<s:radio id="gender" name="gender" list="#session.genders"/>
				</td>
			</tr>
			<tr>
				<td colspan ="6">
					<table>
						<tr>
							<td colspan="6" style="font-weight:normal;color:black;">If you dont know exact "Date Of Birth", select "Age" option and enter approximate age in Age text box</td>							
						</tr>
						<tr>
							<td width="162"><input type="radio" id="dopOptionRadio" name="dobOption" value="dobOption" onclick="enableDisableCalBtn(this.value)"/>Date Of Birth<font class="requiredFont"> * </font></td>
							<td align="left">
								<s:textfield id="dobText" readonly="readonly" name="dateOfBirth" size="25"/>
								<DIV class="yui-skin-sam"><DIV id="dobText_div" style="position:absolute;"></DIV></DIV>
							</td>
							<td><input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" disabled="true" onclick="showDateCal('dobText_div','dobText','1/1970')"/></td>
							<td align="left">Or</td>	
							<td><input id="ageOptionRadio" type="radio" name="dobOption" value="age" onclick="enableDisableCalBtn(this.value)"/>Age<font class="requiredFont"> * </font></td>
							<td align="left"><s:textfield id="ageTextEl" name="age" size="25" maxlength="2"/> </td>
						</tr>
					</table>
				</td>			
			</tr>					
		</table>	
	</FIELDSET>
	<FIELDSET>
		<LEGEND><strong>Contact Details</strong></LEGEND>
		<table class="cadreDetailsTable" width="100%" border="0">
			<tr>
				<td width="165px"><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px"><s:textfield id="mobileField" name="mobile" maxlength="12" size="25" />  </td>
				<td width="165px"><s:label for="telePhoneField" id="telePhoneLabel"  value="%{getText('telephoneNo')}" /></td>
				<td align="left" width="165px"><s:textfield id="telePhoneField" name="telephone" maxlength="10" size="25" />  </td>
			</tr>
			<tr>
				<td width="165px"><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></td>
				<td align="left" colspan="3"><s:textfield id="emailField" name="email" size="75"/>  </td>
			</tr>
			<tr>
				<th width="165px"><u><s:label for="currAddField" id="currAddLabel"  value="%{getText('currAdd')}" /></u></th>
			</tr>
			<tr>
				<td width="165px"><s:label for="houseNoField" id="houseNoLabel"  value="%{getText('houseNo')}" /></td>
				<td align="left" width="165px"><s:textfield id="houseNoField" name="houseNo" maxlength="25" size="25" />  </td>
				<td width="165px"><s:label for="streetField" id="streetLabel"  value="%{getText('street')}" /></td>
				<td align="left" width="165px"><s:textfield id="streetField" name="street" maxlength="100" size="25" />  </td>
			</tr>
		</table>
		
		<c:if test="${windowTask == 'update_existing'}">
			<div class = "addressDetailsDiv">
			<table class ="adresDetailsTable" border="0" width="100%">		
				<tr>
					<th width="165px"><s:label id="villageLabel"  value="%{getText('VILLAGE')}" /></th>
					<td align="left" width="165px"><s:property value="villageName" /></td>
					<th width="165px"><s:label id="mandalLabel"  value="%{getText('MANDAL')}" /></th>
					<td align="left" width="165px"> <s:property value="mandalName" /></td>
				</tr>
				<tr>
					<th width="165px"><s:label id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/></th>
					<td align="left" width="165px"><s:property value="constituencyName" /></td>
					<th width="165px"><s:label  id="districtLabel"  value="%{getText('DISTRICT')}" /></th>
					<td align="left" width="165px"><s:property value="districtName" /></td>
				</tr>
				<tr>
					<th width="165px"><s:label id="stateLabel"  value="%{getText('STATE')}" /></th>
					<td align="left" width="165px"><s:property value="stateName" /></td>
					<th width="165px"><s:label id="pinCodeLabel"  value="%{getText('pincode')}" /></th>
					<td align="left" width="165px"><s:property value="pinCode"/></td>				
				</tr>
				<tr>
					<td colspan="4" style="text-align:right;"><input id="editCrntBtn" align="right" type="button" onclick="hideUnhideCrntAddOptions()" style="width:150px;" value="Edit Current Address" /></td>
				</tr>
			</table>
			</div>	
		</c:if>
					
		<table id="cuurrentAddTable"class="cadreDetailsTable" width="100%">
			<tr>
				<td width="165px"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px"><s:select id="stateField" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select State" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false','current','districtField')"></s:select></td>
				<c:if test="${sessionScope.USER.accessType != 'MP'}"> 
					<td><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
					<s:select id="districtField" cssClass="regionSelect" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,'false','current','constituencyField')" headerKey="0" headerValue="Select Constituency"></s:select>
						<!--<select id="districtField" class="regionSelect" name="district" onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,'false','current','constituencyField')" <c:if test="${sessionScope.USER.accessType == 'MP'}"> <c:out value="disabled='disabled'" /></c:if> >
							<c:forEach var="dist" items="${districtList}" >
								<option value="${dist.id}">${dist.name}</option>
							</c:forEach>
						</select>							
					--></td>
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
				<td width="165px"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="constituencyField" cssClass="regionSelect" name="constituency" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getMandalList(this.name,this.options[this.selectedIndex].value,'false','current','mandalField')" headerKey="0" headerValue="Select Constituency"></s:select> 
				</td>
				<td width="165px"><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="mandalField" cssClass="regionSelect" name="mandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false','current','villageField')" headerKey="0" headerValue="Select Mandal"></s:select>				 
				</td>
			</tr>
			<tr>
				<td width="165px"><s:label for="villageField" id="villageLabel"  value="%{getText('VILLAGE')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="villageField" cssClass="regionSelect" name="village" list="#session.villagesList" listKey="id" listValue="name" headerKey="0" headerValue="Select Village"></s:select>				
				</td>
				<td width="165px"><s:label for="pinCodeField" id="pinCodeLabel"  value="%{getText('pincode')}" /></td>
				<td align="left" width="165px"><s:textfield id="pinCodeField" name="pinCode" maxlength="10" size="25" />  </td>
			</tr>
		</table>
		<table class="cadreDetailsTable" width="100%">				
			<tr width="165px">
				<th><u><s:label for="prmntAddField" id="prmntAddLabel"  value="%{getText('officialAdd')}" /></u></th>
			</tr>
			<tr>
				<td align="left" colspan="2">
						<s:checkbox id="sameAsCA" name = "sameAsCA" onclick="hideUnhidePrmntAddOptions('checkbox')"/>Same As Current Address				
				</td>
				<c:if test="${windowTask == 'update_existing'}">
					<td style="text-align:right;"><input id="editCrntBtn" align="right" type="button" onclick="hideUnhidePrmntAddOptions('button')" style="width:150px;" value="Edit Permanant Address" /></td>
				</c:if>
			</tr>
		</table>
		<table id="permanantAddr" class="cadreDetailsTable" width="100%">
			<tr>
				<td width="165px"><s:label for="phouseNoField" id="phouseNoLabel"  value="%{getText('houseNo')}" /></td>
				<td align="left" width="165px"><s:textfield id="phouseNoField" name="phouseNo" maxlength="10" size="25" /></td>
				<td width="165px"><s:label for="pstreetField" id="pstreetLabel"  value="%{getText('street')}" /></td>
				<td align="left" width="165px"><s:textfield id="pstreetField" name="pstreet" maxlength="100" size="25" /></td>
			</tr>
			<tr>
				<td width="165px"><s:label for="pstateField" id="pstateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pstateField" cssClass="regionSelect" name="pstate" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select State" onchange="getnextList('state',this.options[this.selectedIndex].value,'false','permanent','pdistrictField')"></s:select>
				</td>
			<c:if test="${sessionScope.USER.accessType != 'MP'}"> 
				<td><s:label for="pdistrictField" id="pdistrictLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="pdistrictField" cssClass="regionSelect" name="pdistrict" list="#session.districtsList_1" listKey="id" listValue="name" onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,'false','permananent','pconstituencyField')" headerKey="0" headerValue="Select Constituency"></s:select>
						<!--<select id="pdistrictField" class="regionSelect" name="pdistrict" onchange="getConstituencyList('district',this.options[this.selectedIndex].value,'false','permananent','pconstituencyField')" <c:if test="${sessionScope.USER.accessType == 'MP'}"> <c:out value="disabled='disabled'" /></c:if> >
							<c:forEach var="dist" items="${districtList}" >
							<option value="${dist.id}">${dist.name}</option>
							</c:forEach>
						</select>							
				--></td>
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
				<td width="165px"><s:label for="pconstituencyField" id="pconstituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pconstituencyField" cssClass="regionSelect" name="pconstituency" list="#session.constituenciesList_1" listKey="id" listValue="name" onchange="getMandalList('constituency',this.options[this.selectedIndex].value,'false','permanent','pmandalField')" headerKey="0" headerValue="Select Constituency"></s:select> 
				</td>
				<td width="165px"><s:label for="pmandalField" id="pmandalLabel"  value="%{getText('MANDAL')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pmandalField" cssClass="regionSelect" name="pmandal" list="#session.mandalsList_1" listKey="id" listValue="name" onchange="getnextList('mandal',this.options[this.selectedIndex].value,'false','permananent','pvillageField')" headerKey="0" headerValue="Select Mandal"></s:select>				 
				</td>
			</tr>
			<tr>
				<td width="165px" ><s:label for="pvillageField" id="pvillageLabel"  value="%{getText('VILLAGE')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pvillageField" cssClass="regionSelect" name="pvillage" list="#session.villagesList_1" listKey="id" listValue="name" headerKey="0" headerValue="Select Village"></s:select>				
				</td>
				<td width="165px"><s:label for="ppinCodeField" id="ppinCodeLabel"  value="%{getText('pincode')}" /></td>
				<td align="left" width="165px"><s:textfield id="ppinCodeField" name="ppinCode" maxlength="10" size="25" />  </td>
			</tr>				
		</table>
	</FIELDSET>
	<fieldset>
		<legend><strong>Social Status</strong></legend>
		<table class="cadreDetailsTable" width="100%">
		<tr>
			<th colspan="4"><s:label for="languageField" id="languageLabel"  value="%{getText('languageEff')}" /></th>
		</tr>	
		<!--<c:forEach var="lang" items="${sessionScope.languagesList}" >
		<tr>
			<td>${lang.name}</td>
			<td><s:checkboxlist list="#session.language_options" name="languageOptions_%{lang.name}"/></td>
			<td><input type="checkbox" name="languageOptions_${lang.name}"  value="speak"/>Can Speak</td>
			<td><input type="checkbox" name="languageOptions_${lang.name}"  value="read"/>Can Read</td>
			<td><input type="checkbox" name="languageOptions_${lang.name}"  value="write"/>Can Write</td>
		</tr>
		</c:forEach>		
		-->
		<tr>
			<td>English</td>
			<td><s:checkboxlist list="#session.language_options" name="languageOptions_English"/></td>			
		</tr>
		<tr>
			<td>Hindi</td>
			<td><s:checkboxlist list="#session.language_options" name="languageOptions_Hindi"/></td>			
		</tr>
		<tr>
			<td width="130"><s:label for="educationField" id="educationLabel"  value="%{getText('education')}" /><font class="requiredFont"> * </font></td>
			<td align="left"><s:select id="educationField" cssClass="regionSelect" name="education" list="#session.eduQualsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Education"></s:select></td>
			<td align="left" width="165"><s:label for="professionField" id="professionLabel"  value="%{getText('profession')}" /><font class="requiredFont"> * </font></td>
			<td align="left"><s:select id="professionField" name="profession"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Occupation"></s:select></td>
		</tr>				
		<tr>
			<td><s:label for="incomeField" id="incomeLabel"  value="%{getText('income')}" /></td>
			<td align="left"><s:textfield id="incomeField" name="income" size="25"/></td>
			<td width="100px;"><s:label for="socialStatusField" id="socialStatusLabel"  value="%{getText('socialStatus')}" /><font class="requiredFont"> * </font></td>
			<td style="padding-left: 10px;"><s:radio id="socialStatusField" name="socialStatus" list="#session.socialStatus" listKey="id" listValue="name" required="true"></s:radio> </td>
		</tr>
		</table>
	</fieldset>		
	<fieldset>
		<legend><strong>Cadre Lavel Details</strong></legend>
		<table class="cadreDetailsTable">
			<tr>
				<td><s:label for="cadreTypeField" id="cadreTypeLabel"  value="%{getText('memberType')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
					<s:radio id="memberType" name="memberType" list="#session.cadreType" onclick="showPartyCommittee(this.value)"/>
					<!--<input type="radio" name="memberType" value="Active" onclick="showPartyCommittee(this.value)"/>Active
					<input type="radio" name="memberType" value="Normal" onclick="showPartyCommittee(this.value)"/>Normal
				--></td>
			</tr>
		</table>
		<table class="cadreDetailsTable" width="100%" id="cadreLevelTable" style="display:none;"  border="0">
		<tr>
			<td width="140px"><s:label for="cadreLevelField" id="cadreLevelLabel"  value="%{getText('CADRE_LEVEL')}" /><font class="requiredFont"> * </font></td>
			<td align="left">
				<!--<select id="cadreLevelField" name="cadreLevel" onchange="getStateList()">
					<option	 value='0'>Select Level</option>		
					<option  value='2'>State</option>	
					<option  value='3'>District</option>
					<option  value='4'>Constituency</option>	
					<option  value='5'>Mandal</option>		
					<option  value='6'>Village</option>					
				</select> 
				--><s:select id="cadreLevelField" name="cadreLevel"list="#{'2':'State','3':'District','4':'Constituency','5':'Mandal','6':'Village'}"  headerKey="0" headerValue="Select Cadre Level" onchange="getStateList()"></s:select>
				<input type='hidden' name='cadreLevelValue' id='cadreLevelValue'>
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
		<c:if test="${sessionScope.USER.userType == 'Party' && sessionScope.partyCommittees_flag == true}">
		<tr>
			<td><s:label for="partyCommField" id="partyCommLabel"  value="%{getText('partyCommittee')}" /><font class="requiredFont"> * </font></td>
			<td align="left">
			<!--<select id="partyComiteSelect" name="partyComite" onchange="getPartyDesignation(this.options[this.selectedIndex].value)">
					<option>Please Select</option>
					<c:forEach var="partyCommittee"  items="${sessionScope.partyCommittees}" >
					<option value='${partyCommittee.id}'>${partyCommittee.name}</option>	
					</c:forEach>					
				</select>				
			-->
			<s:select id="partyComiteSelect" name="partyCommittee" list="#session.partyCommittees" listKey="id" listValue="name"  headerKey="0" headerValue="Select Partie Committee" onchange="getPartyDesignation(this.options[this.selectedIndex].value)"></s:select>
			</td>
			<td><s:label for="designationCommField" id="designationCommLabel"  value="%{getText('designation')}" /><font class="requiredFont"> * </font></td>
			<td>
			
			<select id="comiteeDesignationSelect" name="designation" >
				<option value="0">Please Select</option>											
			</select>
			</td>				
		</tr>
		<tr>
				<td><s:label for="durationField" id="durationLabel"  value="%{getText('effectiveDate')}" /><font class="requiredFont"> * </font></td>
				<td colspan="4" align="left">					
				<table class="cadreDetailsTable">
					<tr>				
						<td align="left">
								<s:textfield id="effDateText" readonly="readonly" name="effectiveDate" size="15"/>
								<DIV class="yui-skin-sam"><DIV id="effDateText_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td>		
								<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('effDateText_div','effDateText','1/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>
						</td>
						<td>To</td>
						<td>
								<s:textfield id="tillDateText" readonly="readonly" name="endingDate" size="15"/>
								<DIV class="yui-skin-sam"><DIV id="tillDateText_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td>		
								<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('tillDateText_div','tillDateText','1/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>
						</td>
					</tr>	
				</table>	
				</td>								
		</tr>
		</c:if>
		<c:if test="${sessionScope.USER.userType == 'Party' && sessionScope.cadreSkills_flag == true}">
		<tr>
			<th><u>Cadre Skills</u></th>
		</tr>
		<tr>	
			<td colspan="5">
				<table>
				<tr>
					<s:checkboxlist list="#session.cadreSkills"  name="skills" listKey="id" listValue="name">
					</s:checkboxlist>
				<!--<c:forEach var="skills" items="${sessionScope.cadreSkills}" varStatus="status" >
							
						<td><input type="checkbox" name="skills" id="${skills.id}" value="${skills.id}"/>${skills.name}</td>
						<c:if test="${status.count % 3 == 0}">
							</tr><tr>
						</c:if>						
				</c:forEach>
				--></tr>	
				</table>
			</td>
		</tr>
		</c:if>
		<c:if test="${sessionScope.USER.userType == 'Party' && sessionScope.partyTrainingCamps_flag == true}">
		<tr>
			<th colspan="6"><u>Participated Training Camps</u></th>
		</tr>
		<tr>	
			<td colspan="6">
				<table>
				<tr>
				<s:checkboxlist list="#session.partyTrainingCamps"  name="trainingCamps" listKey="id" listValue="name">
				</s:checkboxlist>
				<!--<c:forEach var="trainingCamps" items="${sessionScope.partyTrainingCamps}" varStatus="status" >
							
						<td><input type="checkbox" name="trainingCamps" id="${trainingCamps.id}" value="${trainingCamps.id}"/>${trainingCamps.name}</td>
						<c:if test="${status.count % 5 == 0}">
							</tr><tr>
						</c:if>						
				</c:forEach>
				--></tr>	
				</table>
			</td>
		</tr>
		</c:if>
		</table>
		</fieldset>
		<c:if test="${windowTask == 'new'}">
			<div style="text-align: center;">			
				<s:submit  value="Register" cssClass="button"></s:submit>
				<a href="cadreManagementAction.action" class="anchor">Go To Cadre Management Home Page</a>
				<a href="cadreReportAction.action" class="anchor">Go To Cadre Management Report</a>			
			</div>
		</c:if>
		<c:if test="${windowTask == 'update_existing'}">
			<div style="text-align: center;"><s:submit  value="Update" cssClass="button"></s:submit></div>
		</c:if>	
	</div>
	</s:form>
<script type="text/javascript">
	executeOnload();
</script>
</body>
</html>