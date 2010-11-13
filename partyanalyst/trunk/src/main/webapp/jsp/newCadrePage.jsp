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
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script>
	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	
 
<script type="text/javascript">
	var currentTask = '${windowTask}';
	var successFlag = '${rs.resultCode}';
	var accessValue = '${sessionScope.USER.accessValue}';
	
	function setCadreValue(value, source){
		var scopeLevelEl = document.getElementById("scopeLevel");
		var scopeLevelElVal = scopeLevelEl.options[scopeLevelEl.selectedIndex].text;
		if(value == '0')
		{
			alert("Please Select Valid Location");
			return;
		}
		var boothIdTextEl = document.getElementById("boothNoText");
		var hiddenEl = document.getElementById("cadreLevelValue");
		hiddenEl.value = '';
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

	
	function showPartyCommittee(value)
	{
		var cadreLevelTableEl = document.getElementById("cadreLevelTable");
		var cadreLevelFieldEl = document.getElementById("scopeLevel");
		var cadreLevelDistrictEl = document.getElementById("cadreLevelDistrict");
		var cadreLevelStateEl = document.getElementById("cadreLevelState");
		var cadreLevelConstituencyEl = document.getElementById("cadreLevelConstituency");
		var cadreLevelMandalEl = document.getElementById("cadreLevelMandal");
		var cadreLevelVillageEl = document.getElementById("cadreLevelVillage");
		var row1El = document.getElementById("row1");
		var row2El = document.getElementById("row2");
		var row3El = document.getElementById("row3");
		var row4El = document.getElementById("row4");
		var row5El = document.getElementById("row5");
		var row6El = document.getElementById("row6");
		
		if(value == "Active")			
		{
			cadreLevelTableEl.style.display ='block';
			cadreLevelFieldEl.selectedIndex = '0';
			document.getElementById("cadreLevelValue").value='';
			row1El.style.display = 'none';
			row2El.style.display = 'none';
			row3El.style.display = 'none';
			row4El.style.display = 'none';
			row5El.style.display = 'none';
			row6El.style.display = 'none';			
			
		} else if (value == "Normal")
		{
			cadreLevelTableEl.style.display ='none';
			cadreLevelFieldEl.selectedIndex = '1';
			setCadreValue('1','onChange');
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
		//get all official address elements
		var pstateFieldEl = document.getElementById("pstateField");
		var pdistrictFieldEl = document.getElementById("pdistrictField");
		var pconstituencyFieldEl = document.getElementById("pconstituencyField");
		var pmandalFieldEl = document.getElementById("pmandalField");
		var pvillageFieldEl = document.getElementById("pvillageField");
		var sameAsCAFlagEl = document.getElementById("sameAsCAFlag");
		var optionsArray = new Array();
		if(sameAsCAEl.checked == true && source == 'checkbox')
		{	
				sameAsCAFlagEl.value = true;
				optElements.style.display = 'none';
				clearOptionsListForSelectElmtId("pdistrictField");
				clearOptionsListForSelectElmtId("pconstituencyField");
				clearOptionsListForSelectElmtId("pmandalField");
				clearOptionsListForSelectElmtId("pvillageField");
				
				var optionsArray = new Array();
				var obj = {
						id : 1,
						name:'Please Select '
						};
				optionsArray.push(obj);
				createOptionsForSelectElmtId("pdistrictField",optionsArray);
				createOptionsForSelectElmtId("pconstituencyField",optionsArray);
				createOptionsForSelectElmtId("pmandalField",optionsArray);
				createOptionsForSelectElmtId("pvillageField",optionsArray);
				pstateFieldEl.selectedIndex = '1';										
		
		}  else
		{
			sameAsCAFlagEl.value = false;
			var optionsArr = new Array();
			if(optElements.style.display == "none")
			{
				optElements.style.display = '';
			}
			clearOptionsListForSelectElmtId("pdistrictField");
			clearOptionsListForSelectElmtId("pconstituencyField");
			clearOptionsListForSelectElmtId("pmandalField");
			clearOptionsListForSelectElmtId("pvillageField");
			var obj = {
					id : '0',
					name:'Please Select '
					};
			optionsArr.push(obj);
			createOptionsForSelectElmtId("pdistrictField",optionsArr);
			createOptionsForSelectElmtId("pconstituencyField",optionsArr);
			createOptionsForSelectElmtId("pmandalField",optionsArr);
			createOptionsForSelectElmtId("pvillageField",optionsArr);
			pstateFieldEl.selectedIndex = '0';			
		}	
	}	
	function manageDOBOptions(source)
	{
		var dobSpanEl = document.getElementById("dobSpan");
		var ageSpanEl = document.getElementById("ageSpan");
		var radioEls = document.getElementsByName("dobOption");
		var ageTextEl = document.getElementById("ageTextEl");
		var dobTextEl =  document.getElementById("dobText");
		for(i = 0; i< radioEls.length; i++)
		{
			if(radioEls[i].checked == true && radioEls[i].value == 'Date Of Birth')
			{
				if(dobSpanEl.style.display == 'none')
					dobSpanEl.style.display = 'block';
				if(ageSpanEl.style.display == 'block')
					ageSpanEl.style.display = 'none';
				if(source == 'radio')
				{
					ageTextEl.value='99';
					dobTextEl.value = '';	
				}	
								
						
			} else if(radioEls[i].checked == true && radioEls[i].value == 'Age')
			{
				if(dobSpanEl.style.display == 'block')
					dobSpanEl.style.display = 'none';
				if(ageSpanEl.style.display == 'none')
					ageSpanEl.style.display = 'block';
				if(source == 'radio')
				{
					ageTextEl.focus();
					ageTextEl.value = '';
					dobTextEl.value='31/02/1900';	
				}	
								
			}
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

	function cleanOptionsList(string)
	{
		if(string == "state")
		{
			clearOptionsListForSelectElmtId("constituencyField");
			clearOptionsListForSelectElmtId("mandalField");
			clearOptionsListForSelectElmtId("villageField");
			
		}

		else if(string == "district")
		{
			clearOptionsListForSelectElmtId("mandalField");
			clearOptionsListForSelectElmtId("villageField");
		}

		else if(string == "constituency")
		{
			clearOptionsListForSelectElmtId("villageField");
		}

		if(string == "pstate")
		{
			clearOptionsListForSelectElmtId("pconstituencyField");
			clearOptionsListForSelectElmtId("pmandalField");
			clearOptionsListForSelectElmtId("pvillageField");
			
		}

		else if(string == "pdistrict")
		{
			clearOptionsListForSelectElmtId("pmandalField");
			clearOptionsListForSelectElmtId("pvillageField");
		}

		else if(string == "pconstituency")
		{
			clearOptionsListForSelectElmtId("pvillageField");
		}		
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
		
		var stateElmt = document.getElementById("cadreLevelState");
		var districtElmt = document.getElementById("cadreLevelDistrict");
		var constituencyElmt = document.getElementById("cadreLevelConstituency");
		var mandalElmt = document.getElementById("cadreLevelMandal");
		var villageElmt = document.getElementById("cadreLevelVillage");
		
		if(samsAsCaEl.checked == true)				
		{
			permanantAddrEl.style.display = 'none';
			hideUnhidePrmntAddOptions('checkbox')				
		}
		if(samsAsCaEl.checked == true)				
		{
			permanantAddrEl.style.display = 'none';
				
		}
		if(memberTypeRadioEl.checked == true)
		{
			if(cadreLevelTableEl.style.display == 'none')
			{	
				cadreLevelTableEl.style.display = 'block';
			}
			var effectedRangeEl = document.getElementById("scopeLevel");
			var selectedeffectedRange =effectedRangeEl.options[effectedRangeEl.selectedIndex].value;  
			
			if(selectedeffectedRange != '0')
				populateLocations(selectedeffectedRange, 'onLoad');	
		}
		manageDOBOptions('onLoad');		 
	}
	function populateLocations(val,source)
	{	
		var row1El = document.getElementById("row1");
		var row2El = document.getElementById("row2");
		var row3El = document.getElementById("row3");
		var row4El = document.getElementById("row4");
		var row5El = document.getElementById("row5");
		var row6El = document.getElementById("row6");
		//var boothNoTextEl = document.getElementById("boothNoText");
		var hiddenEl = document.getElementById("cadreLevelValue");
		var stateFieldEl = document.getElementById("stateField_s");
		var districtFieldEl = document.getElementById("districtField_s"); 
		var constituencyFieldEl = document.getElementById("constituencyField_s");
		var mandalFieldEl = document.getElementById("mandalField_s");
		var hamletFieldEl = document.getElementById("hamletField_s");		
		if(source == 'onChange')
		{	
			hiddenEl.value='';
			stateFieldEl.selectedIndex = '0';
			districtFieldEl.selectedIndex = '0';
			constituencyFieldEl.selectedIndex = '0';
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';			
		} else 
			if(source == "onLoad")
			{
				setCadreValue(accessValue,'onChange');
			}	
		row1El.style.display = 'none';
		row2El.style.display = 'none';
		row3El.style.display = 'none';
		row4El.style.display = 'none';
		row5El.style.display = 'none';
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
			if(row6El.style.display == 'none')
				row6El.style.display = '';
		}	 
	}
	
	function showFamilyDetailsTable()
	{
		var familyDetailsTableEle  =  document.getElementById("familyDetailsTableId");
		familyDetailsTableEle.style.display = '';
	}

</script>
<style type="text/css">
	
	#registrationMainDiv
	{
		text-align: left;
		margin-left: 70px;
	}
	.bodyStyle {
		font-family: verdana;
		font-size: 12px;
	}
	.cadreDetailsTable td {
		color: #926682;
		font-family: verdana;
		font-weight: bold;
		text-align: left;
	}
	.cadreDetailsTable th {
		color: #0000AA;
		font-family: verdana;
		font-weight: bold;
		text-align: left;
	}
	.adresDetailsTable th {
		color: #926682;
		font-family: verdana;
		font-weight: bold;
		text-align: left;
	}
	.adresDetailsTable td {
		
		font-family: verdana;
		text-align: left;
	}	 
	fieldset {
		border: 4px solid #CFD6DF;
		margin-bottom: 10px;
		padding: 10px;
		width: 820px;
	}
	legend {
		background-color: #567AAF;
		color: #FFFFFF;
		font-size: 12px;
		padding: 5px;
	}	
	.button {
		background-color: #0000AA;
		color: #FFFFFF;
		font-size: 12px;
		font-weight: bold;
		margin: 10px;
		padding: 2px;
		text-align: center;
		text-decoration: none;
	}
	.anchor
	{
		background-color: #0000AA;
		color: #FFFFFF;
		font-size: 12px;
		font-weight: bold;
		margin: 10px;
		padding: 2px;
		text-align: center;
		text-decoration: none;
		height: 25px;
	}
	
	.cadreReportHeader {
		background-image: url("images/icons/cadreReport/bg_center.png");
		background-repeat: repeat-x;
		color: #FFFFFF;
		font-size: 14px;
		font-weight: bold;
		height: 24px;
		padding-top: 1px;
		text-align: center;
		width: 150px;
	}
	.calBtn
	{
		background-image: url("images/icons/constituencyManagement/calendar.jpeg");
		height: 24px;
		width: 24px;	
	}
	.addressDetailsDiv
	{
		background-color: Silver;
		border: 3px solid #CFD6DF;
		margin-top: 5px;
		margin-bottom: 5px;
		padding: 3px;
	}

	#editDiv
	{
    cursor:pointer;
    cursor:hand;
	}

</style>
</head>
<body class="bodyStyle" onunload="loadOnUnload()">
<s:form action="cadreRegisterAction" method="GET" theme="simple">
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
	
		<c:if test="${windowTask == 'new'}">
			<c:if test="${rs.resultCode !=  '' && rs.resultCode == '0'}">
				<DIV id="alertMessage" style="color:green;font-weight:bold">Cadre Registered Successfully!</DIV>			
			</c:if>
		</c:if>
		<c:if test="${windowTask == 'update_existing'}">
			<c:if test="${rs.resultCode !=  '' && rs.resultCode == '0'}">	
				<DIV id="alertMessage" style="color:green;font-weight:bold">Cadre Details Updated Successfully!</DIV>
			</c:if>	
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
		<table class="cadreDetailsTable" width="100%">		
			<tr>
				<td><s:label for="firstNameField" id="fnameLabel" value="%{getText('firstName')}" /><font class="requiredFont"> * </font></td>
				<td align="left"><s:textfield id="firstNameField" key="cadreInfo.firstName" name="firstName" size="25" tooltip="true" maxlength="40"/></td>
				<td><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></td>
				<td align="left"><s:textfield id="middleNameField" key="cadreInfo.middleName" name="middleName" size="25" tooltip="true" maxlength="40"/></td>
			</tr>
			<tr>	
				<td><s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /><font class="requiredFont"> * </font></td>
				<td align="left"><s:textfield id="lastNameField" key="cadreInfo.lastName" name="lastName" size="25" maxlength="40"/>  </td>
				<td><s:label for="father_spouseName" id="father_spouseNameLabel"  value="%{getText('father_spouseName')}" /><font class="requiredFont"> * </font></td>
				<td align="left"><s:textfield id="father_spouseName" name="fatherOrSpouseName" size="25" maxlength="100"/>  </td>
			</tr>
			<tr>				
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
							<td width="162"><s:radio id="dopOptionRadio" name="dobOption" list="#session.dob_Options" onclick="manageDOBOptions('radio')"/></td>
							<td align="left">
								<span id="dobSpan" style="display:none;">
									<table>
									<tr>
										<td>
											<s:textfield id="dobText" readonly="true" name="dateOfBirth" size="25"/>
											<DIV class="yui-skin-sam"><DIV id="dobText_div" style="position:absolute;"></DIV></DIV>
										</td>
										<td><input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" onclick="showDateCal('dobText_div','dobText','1/1970')"/></td>
									</tr>
									</table>	
								</span>
							</td>							
							<td align="left">
								<span id="ageSpan" style="display:none;">
									<s:textfield id="ageTextEl" name="age" size="25" maxlength="2"/>
								</span>
							</td>						
						</tr>
					</table>
				</td>			
			</tr>	
		<tr>
			<td><s:label for="noOfFamilyMembersId" id="noOfFamilyMembersLabelId" value="No of Family Members"/></td>
			<td align="left"><s:textfield id="noOfFamilyMembersId" name="noOfFamilyMembers" size="25" tooltip="true" maxlength="5"/></td>

			<td><s:label for="noOfVotersId" id="noOfVotersLableId"  value="No of Voters In Family" /></td>
			<td align="left"><s:textfield id="noOfVotersId" name="noOfVoters" size="25" tooltip="true" maxlength="5"/></td>
		</tr>

		<tr>
			<th width="165px"><u><s:label for="currAddField" id="currAddLabel"  value="Family Members Details" /></u></th>
			<td align="left"><div id="editDiv" onclick="showFamilyDetailsTable()"><b><u>Edit</u></b></div></td>
		</tr>
	<table id="familyDetailsTableId" class="cadreDetailsTable" width="100%" style="display:none;">	
		
	  <tr id="firstFamilyMemberRow">
		  <td width="50"><s:label for="firstFamilyMemberNameId" id="firstFamilyMemberNameLabel" value="Name"/></td>
		  <td align="left" width="165px"><s:textfield id="firstFamilyMemberNameId" name="firstFamilyMemberName" maxlength="25" size="25" /> </td>
		  
		  <td width="100"> <s:label for="firstFamilyMemberRelationTextId" id="firstFamilyMemberRelationLabelId" value="Relationship" /></td>
		  <td align="left"> <s:select id="FamilyMemberRelationTextId" cssClass="regionSelect" name="firstFamilyMemberRelationId" list="#session.relationshipList" listKey="id" listValue="name" onchange="" ></s:select></td>
					
		  <td width="100" align="right"><s:label for="firstFamilyMemberDOBId" id="firstFamilyMemberDOBLabel" value="Date Of Birth"/></td>
		  <td align="left">
				<span id="dobSpan">
					<table>
					<tr>
						<td>
							<s:textfield id="firstFamilyMemberDOBId" readonly="true" name="firstFamilyMemberDOB" size="25"/>
							<DIV class="yui-skin-sam"><DIV id="firstFamilyMemberDOB_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td><input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" onclick="showDateCal('firstFamilyMemberDOB_div','firstFamilyMemberDOBId','1/1970')"/></td>
					</tr>
					</table>	
				</span>
			</td>		
		</tr>

		<tr id="secondFamilyMemberRow">
		  <td width="50"><s:label for="secondFamilyMemberNameId" id="secondFamilyMemberNameLabel" value="Name"/></td>
		  <td align="left" width="165px"><s:textfield id="secondFamilyMemberNameId" name="secondFamilyMemberName" maxlength="25" size="25" /> </td>
		  
		  <td width="100"> <s:label for="secondFamilyMemberRelationTextId" id="secondFamilyMemberRelationLabelId" value="Relationship" /></td>
		  <td align="left"> <s:select id="secondFamilyMemberRelationTextId" cssClass="regionSelect" name="secondFamilyMemberRelationId" list="#session.relationshipList" listKey="id" listValue="name" onchange="" ></s:select></td>
					
		  <td width="100" align="right"><s:label for="secondFamilyMemberDOBId" id="secondFamilyMemberDOBLabel" value="Date Of Birth"/></td>
		  <td align="left">
				<span id="dobSpan">
					<table>
					<tr>
						<td>
							<s:textfield id="secondFamilyMemberDOBId" readonly="true" name="secondFamilyMemberDOB" size="25"/>
							<DIV class="yui-skin-sam"><DIV id="secondFamilyMemberDOB_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td><input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" onclick="showDateCal('secondFamilyMemberDOB_div','secondFamilyMemberDOBId','1/1970')"/></td>
					</tr>
					</table>	
				</span>
			</td>		
		</tr>

		<tr id="thirdFamilyMemberRow">
		  <td width="50"><s:label for="thirdFamilyMemberNameId" id="thirdFamilyMemberNameLabel" value="Name"/></td>
		  <td align="left" width="165px"><s:textfield id="thirdFamilyMemberNameId" name="thirdFamilyMemberName" maxlength="25" size="25" /> </td>
		  
		  <td width="100"> <s:label for="thirdFamilyMemberRelationTextId" id="thirdFamilyMemberRelationLabelId" value="Relationship" /></td>
		  <td align="left"> <s:select id="thirdFamilyMemberRelationTextId" cssClass="regionSelect" name="thirdFamilyMemberRelationId" list="#session.relationshipList" listKey="id" listValue="name" onchange="" ></s:select></td>
					
		  <td width="100" align="right"><s:label for="thirdFamilyMemberDOBId" id="thirdFamilyMemberDOBLabel" value="Date Of Birth"/></td>
		  <td align="left">
				<span id="dobSpan">
					<table>
					<tr>
						<td>
							<s:textfield id="thirdFamilyMemberDOBId" readonly="true" name="thirdFamilyMemberDOB" size="25"/>
							<DIV class="yui-skin-sam"><DIV id="thirdFamilyMemberDOB_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td><input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" onclick="showDateCal('thirdFamilyMemberDOB_div','thirdFamilyMemberDOBId','1/1970')"/></td>
					</tr>
					</table>	
				</span>
			</td>		
		  </tr>
	   </table>
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
		</table>
					
		<table id="cuurrentAddTable"class="cadreDetailsTable" width="100%">
			<tr>
				<td width="165px"><s:label for="houseNoField" id="houseNoLabel"  value="%{getText('houseNo')}" /></td>
				<td align="left" width="165px"><s:textfield id="houseNoField" name="houseNo" maxlength="25" size="25" />  </td>
				<td width="165px"><s:label for="streetField" id="streetLabel"  value="%{getText('street')}" /></td>
				<td align="left" width="165px"><s:textfield id="streetField" name="street" maxlength="100" size="25" />  </td>
			</tr>
			<tr>
				<td width="165px"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px"><s:select id="stateField" cssClass="regionSelect" name="state" list="#session.statesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','cadreReg','districtField','currentAdd');cleanOptionsList('state')"></s:select></td>
				<c:if test="${sessionScope.USER.accessType != 'MP'}"> 
					<td><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="districtField" cssClass="regionSelect" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','cadreReg','constituencyField','currentAdd');cleanOptionsList('district')" ></s:select>
					</td>
				</c:if>
				<c:if test="${sessionScope.USER.accessType == 'MP'}"> 
					<td><s:label for="parlConstituencyField" id="parlConstituencyLabel"  value="%{getText('PCONSTITUENCY')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="parlConstituencyField" cssClass="regionSelect" name="parliament" list="#session.p_constituencies" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','cadreReg','constituencyField','currentAdd','null');cleanOptionsList('district')" ></s:select>
					</td>
				</c:if>				
			</tr>
			<tr>
				<td width="165px"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="constituencyField" cssClass="regionSelect" name="constituencyID" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','cadreReg','mandalField','currentAdd', 'null');cleanOptionsList('constituency')"></s:select> 
				</td>
				<td width="165px"><s:label for="mandalField" id="mandalLabel"  value="%{getText('subRegions')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="mandalField" cssClass="regionSelect" name="mandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','cadreReg','villageField','currentAdd','null');getBooths('currentAdd','constituencyField','boothField',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','boothsInTehsilOrMunicipality')"></s:select>				 
				</td>
			</tr>
			<tr>
				<td width="165px"><s:label for="villageField" id="villageLabel"  value="%{getText('wardOrHamlet')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="villageField" cssClass="regionSelect" name="village" list="#session.villagesList" listKey="id" listValue="name" headerKey="0" headerValue="Select Village" onchange="getBoothsInWard('currentAdd','constituencyField','boothField',this.options[this.selectedIndex].value,'cadreReg','mandalField')"></s:select>				
				</td>
				<td width="165px"><s:label for="pinCodeField" id="pinCodeLabel"  value="%{getText('pincode')}" /></td>
				<td align="left" width="165px"><s:textfield id="pinCodeField" name="pinCode" maxlength="10" size="25" />  </td>
			</tr>
			<tr>
				<th colspan="4"><u>Booth details are not compulsory</u></th>
			</tr>			
			<tr>
				<td width="165px" ><s:label for="boothField" id="boothLabel"  value="%{getText('Booth')}" /></td>
				<td align="left" width="165px">
					<s:select id="boothField" cssClass="regionSelect" name="booth" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Booth"></s:select>				
				</td>
				<td width="165px"><input type="button" id="pBoothDetailsPanel" value="View Booths Details"/></td>
				</td>
			</tr>
		</table>			
		<table class="cadreDetailsTable" width="100%">				
			<tr width="165px">
				<th><u><s:label for="prmntAddField" id="prmntAddLabel"  value="%{getText('officialAdd')}" /></u></th>
			</tr>
			<c:if test="${ sameAsCAFlag == true}">
			<tr>
				<td align="left" colspan="2">
						<s:checkbox id="sameAsCA" checked="checked" name = "sameAsCA" onclick="hideUnhidePrmntAddOptions('checkbox')"/>Same As Current Address				
				</td>
			</tr>
			</c:if>
			<c:if test="${ sameAsCAFlag == false}">
			<tr>
				<td align="left" colspan="2">
						<s:checkbox id="sameAsCA" name = "sameAsCA" onclick="hideUnhidePrmntAddOptions('checkbox')"/>Same As Current Address				
				</td>
			</tr>
			</c:if>
		</table>		
		<table id="permanantAddr" class="cadreDetailsTable" width="100%">
			<tr>
				<td width="165px"><s:label for="phouseNoField" id="phouseNoLabel"  value="%{getText('houseNo')}" /></td>
				<td align="left" width="165px"><s:textfield id="phouseNoField" name="phouseNo" maxlength="10" size="25" /></td>
				<td width="165px"><s:label for="pstreetField" id="pstreetLabel"  value="%{getText('street')}" /></td>
				<td align="left" width="165px"><s:textfield id="pstreetField" name="pstreet" maxlength="100" size="25" /></td>
			</tr>
			<tr>				
			<c:if test="${sessionScope.USER.accessType != 'MP'}"> 
				<td width="165px"><s:label for="pstateField" id="pstateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pstateField" cssClass="regionSelect" name="pstate" list="#session.statesList_c" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','cadreReg','pdistrictField','OfficialAdd');cleanOptionsList('pstate')"></s:select>
				</td>
				<td><s:label for="pdistrictField" id="pdistrictLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="pdistrictField" cssClass="regionSelect" name="pdistrict" list="#session.districtsList_o" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','cadreReg','pconstituencyField','OfficialAdd');cleanOptionsList('pdistrict')" headerKey="0" headerValue="Select District"></s:select>
					</td>
			</c:if>
			<c:if test="${sessionScope.USER.accessType == 'MP'}"> 
					<td width="165px"><s:label for="pstateField" id="pstateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
					<td align="left" width="165px">
					<s:select id="pstateField" cssClass="regionSelect" name="pstate" list="#session.statesList_o" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','cadreReg','parlConstituencyField_o','OfficialAdd');cleanOptionsList('pstate')"></s:select>
					</td>
					<td><s:label for="parlConstituencyField_o" id="parlConstituencyLabel_o"  value="%{getText('PCONSTITUENCY')}" /><font class="requiredFont"> * </font></td>
					<td align="left">
						<s:select id="parlConstituencyField_o" cssClass="regionSelect" name="pParliament" list="#session.p_constituencies_o" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','cadreReg','pconstituencyField','currentAdd');cleanOptionsList('district')" ></s:select>
					</td>
				</c:if>
			</tr>
			<tr>
				<td width="165px"><s:label for="pconstituencyField" id="pconstituencyLabel"  value="%{getText('CONSTITUENCY')}"/><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pconstituencyField" cssClass="regionSelect" name="pconstituencyID" list="#session.constituenciesList_o" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','cadreReg','pmandalField','OfficialAdd', 'null');cleanOptionsList('pconstituency')" headerKey="0" headerValue="Select Constituency"></s:select> 
				</td>
				<td width="165px"><s:label for="pmandalField" id="pmandalLabel"  value="%{getText('subRegions')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pmandalField" cssClass="regionSelect" name="pmandal" list="#session.mandalsList_o" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','cadreReg','pvillageField','OfficialAdd');getBooths('currentAdd','pconstituencyField','pboothField',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','boothsInTehsilOrMunicipality')" headerKey="0" headerValue="Select Mandal"></s:select>				 
				</td>
			</tr>
			<tr>
				<td width="165px" ><s:label for="pvillageField" id="pvillageLabel"  value="%{getText('wardOrHamlet')}" /><font class="requiredFont"> * </font></td>
				<td align="left" width="165px">
					<s:select id="pvillageField" cssClass="regionSelect" name="pvillage" list="#session.villagesList_o" listKey="id" listValue="name" headerKey="0" headerValue="Select Village" onchange="getBoothsInWard('currentAdd','pconstituencyField','pboothField',this.options[this.selectedIndex].value,'cadreReg','pmandalField')"></s:select>				
				</td>
				<td width="165px"><s:label for="ppinCodeField" id="ppinCodeLabel"  value="%{getText('pincode')}" /></td>
				<td align="left" width="165px"><s:textfield id="ppinCodeField" name="pPinCode" maxlength="10" size="25" />  </td>
			</tr>
			<tr>
				<th colspan="4"><u>Booth details are not compulsory</u></th>
			</tr>			
			<tr>
				<td width="165px" ><s:label for="pboothField" id="pboothLabel"  value="%{getText('booth')}" /></td>
				<td align="left" width="165px">
					<s:select id="pboothField" cssClass="regionSelect" name="pBooth" list="#session.villagesList_o" listKey="id" listValue="name" headerKey="0" headerValue="Select Booth"></s:select>				
				</td>
				<td width="165px"><input type="button" id="pBoothDetailsPanel" value="View Booths Details"/></td>
				</td>
			</tr>				
		</table>
	</FIELDSET>			
	<fieldset>
		<legend><strong>Social Status</strong></legend>
		<table class="cadreDetailsTable" width="100%">
		<tr>
			<th colspan="4"><s:label for="languageField" id="languageLabel"  value="%{getText('languageEff')}" /></th>
		</tr>	
		<tr>
			<td>English</td>
			<td colspan="3"><s:checkboxlist list="#session.language_options" name="languageOptions_English"/></td>			
		</tr>
		<tr>
			<td>Hindi</td>
			<td colspan="3"><s:checkboxlist list="#session.language_options" name="languageOptions_Hindi"/></td>			
		</tr>
		<tr>
			<td width="130"><s:label for="educationField" id="educationLabel"  value="%{getText('education')}" /><font class="requiredFont"> * </font></td>
			<td align="left"><s:select id="educationField" cssClass="regionSelect" name="education" list="#session.eduQualsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Education"></s:select></td>
			<td align="left" width="165"><s:label for="professionField" id="professionLabel"  value="%{getText('profession')}" /><font class="requiredFont"> * </font></td>
			<td align="left"><s:select id="professionField" name="profession"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Occupation"></s:select></td>
		</tr>				
		<tr>
			<td><s:label for="incomeField" id="incomeLabel"  value="%{getText('income')}" /></td>
			<td align="left"><s:textfield id="incomeField" name="income" size="25" maxlength="8"/></td>
			<td width="100px;"><s:label for="socialStatusField" id="socialStatusLabel"  value="%{getText('socialStatus')}" /><font class="requiredFont"> * </font></td>
			<td style="padding-left: 10px;"><s:radio id="socialStatusField" name="socialStatus" list="#session.socialStatus" listKey="id" listValue="name" required="true"></s:radio> </td>
		</tr>
		</table>
	</fieldset>		
	<fieldset>
		<legend><strong>Cadre Lavel Details</strong></legend>
		<table class="cadreDetailsTable">
			<tr>
				<td width="200"><s:label for="cadreTypeField" id="cadreTypeLabel"  value="%{getText('memberType')}" /><font class="requiredFont"> * </font></td>
				<td align="left">
					<s:radio id="memberType" name="memberType" list="#session.cadreType" onclick="showPartyCommittee(this.value)" value="defaultCadreType"/>
				</td>
			</tr>
		</table>
		<table class="cadreDetailsTable" width="100%" id="cadreLevelTable" style="display:none;"  border="0">
		<tr>
			<td width="200"><s:label for="scopeLevelField" id="cadreLevelLabel"  value="%{getText('CADRE_LEVEL')}" /><font class="requiredFont"> * </font></td>
			<td align="left">				
				<s:select id="scopeLevel" cssClass="regionSelect" name="cadreLevel"list="#session.cadreLevelsList" listKey="id" listValue = "name" value="defaultCadreLevel"  headerKey="0" headerValue="Select Cadre Level" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange')"></s:select>	
			</td>
		</tr>
		<tr id="row1" style="display:none;">
			<td width="200"><s:label for="stateField_s" id="stateLabel"  value="%{getText('STATE')}" /><font class="requiredFont"> * </font></td>
			<td>
				<s:select id="stateField_s" cssClass="regionSelect" value="defaultStateId" list="#session.statesList_c" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','cadreReg','districtField_s','cadreLevel', 'null');setCadreValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>
		<tr id="row2" style="display:none;">
			<td width="200"><s:label for="districtField_s" id="districtLabel"  value="%{getText('DISTRICT')}" /><font class="requiredFont"> * </font></td>
			<td>
				<s:select id="districtField_s" cssClass="regionSelect" value="defaultDistId" list="#session.districtsList_c" listKey="id" listValue="name" headerKey = "0" headerValue = "Select District" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'cadreReg','constituencyField_s','cadreLevel');setCadreValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>
		<tr id="row3" style="display:none;">
			<td width="200"><s:label for="constituencyField_s" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/></td>
			<td>
				<s:select id="constituencyField_s" value="defaultConstId" cssClass="regionSelect" list="#session.constituenciesList_c" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Constituency" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'cadreReg','mandalField_s','cadreLevel');setCadreValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>								
		<tr id="row4" style="display:none;">
			<td width="200"><s:label for="mandalField" id="mandalLabel"  value="%{getText('subRegions')}" /></td>
			<td>
				<s:select id="mandalField_s" cssClass="regionSelect" list="#session.mandalsList_c" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,'cadreReg','null','cadreLevel','constituencyField_s');setCadreValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>					
		<tr id="row5" style="display:none;">
			<td width="200"><s:label for="hamletField_s" id="mandalLabel"  value="%{getText('wardOrHamlet')}" /></td>
			<td>
				<s:select id="hamletField_s" cssClass="regionSelect" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="setCadreValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
			</td>
		</tr>
		<tr id="row6" style="display:none;">
			<td width="200">Booth No</td>
			<td>
				<s:select id="boothField_s" cssClass="regionSelect" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="setCadreValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
				<!--<s:textfield id="boothNoText" size="25" onKeyUp = "setCadreValue('null','onKeyUp')"/>
			--></td>
		</tr>
		<c:if test="${sessionScope.USER.userType == 'Party' && sessionScope.partyCommittees_flag == true}">
		<tr>
			<td><s:label for="partyCommField" id="partyCommLabel"  value="%{getText('partyCommittee')}" /><font class="requiredFont"> * </font></td>
			<td align="left"><s:select id="partyComiteSelect" name="partyCommittee" list="#session.partyCommittees" listKey="id" listValue="name"  headerKey="0" headerValue="Select Partie Committee" onchange="getPartyDesignation(this.options[this.selectedIndex].value)"></s:select></td>
			<td><s:label for="designationCommField" id="designationCommLabel"  value="%{getText('designation')}" /><font class="requiredFont"> * </font></td>
			<td><select id="comiteeDesignationSelect" name="designation" ><option value="0">Please Select</option></select></td>				
		</tr>
		<tr>
			<td><s:label for="durationField" id="durationLabel"  value="%{getText('effectiveDate')}" /><font class="requiredFont"> * </font></td>
			<td colspan="4" align="left">					
				<table class="cadreDetailsTable">
					<tr>				
						<td align="left">
							<s:textfield id="effDateText" readonly="true" name="effectiveDate" size="15"/>
							<DIV class="yui-skin-sam"><DIV id="effDateText_div" style="position:absolute;"></DIV></DIV>
						</td>
						<td>		
							<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('effDateText_div','effDateText','1/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>
						</td>
						<td>To</td>
						<td>
							<s:textfield id="tillDateText" readonly="true" name="endingDate" size="15"/>
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
					<td><s:checkboxlist list="#session.cadreSkills"  name="skills" listKey="id" listValue="name"></s:checkboxlist></td>
				</tr>	
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
					<td><s:checkboxlist list="#session.partyTrainingCamps"  name="trainingCamps" listKey="id" listValue="name"></s:checkboxlist></td>
				</tr>	
				</table>
			</td>
		</tr>
		</c:if>
		</table>
		<input type='hidden' name='cadreLevelValue' id='cadreLevelValue'>
		</fieldset>
		<c:if test="${windowTask == 'new'}">
			<div style="text-align: center;">			
				<s:submit  value="Register" cssClass="button"></s:submit>
				<a href="cadreManagementAction.action" class="anchor">Go To Cadre Management Home Page</a>
				<a href="cadreReportAction.action" class="anchor">Go To Cadre Management Report</a>			
			</div>
		</c:if>
		<c:if test="${windowTask == 'update_existing'}">
			<div style="text-align: center;">
				<s:submit  value="Update" cssClass="button"></s:submit>
				<a href="cadreManagementAction.action" class="anchor">Go To Cadre Management Home Page</a>
				<a href="cadreReportAction.action" class="anchor">Go To Cadre Management Report</a>
			</div>
		</c:if>	
		<input type="hidden" name="defaultStateId" value="${defaultStateId}">
		<input type="hidden" name="defaultDistId" value="${defaultDistId}">
		<input type="hidden" name="defaultConstId" value="${defaultConstId}">
		<input type="hidden" name="sameAsCAFlag" id="sameAsCAFlag" value="${sameAsCAFlag}">		 		
	</div>
	</s:form>
<script type="text/javascript">
	executeOnload();
</script>
<c:if test="${windowTask == 'update_existing'}">
	<script type="text/javascript">
		showFamilyDetailsTable();
	</script>
</c:if>
</body>
</html>