<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
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

<script type="text/javascript">

		
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
			|| (name == "cadreLevelState" && cadreLevelElmtText == "Mandal"))
		{
			getnextList("state",id,"true");				
		}
		else if(name == "cadreLevelDistrict" && cadreLevelElmtText == "Constituency")
		{
			getnextList("constituency",id,"true");				
		}
		else if(name == "cadreLevelDistrict" && cadreLevelElmtText == "Mandal")
		{
			getnextList("district",id,"true");				
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

		if(!cadreLevelElmt || !stateElmt || !districtElmt || !constituencyElmt || !mandalElmt)
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

		stateElmt.disabled = true;
		districtElmt.disabled = true;
		constituencyElmt.disabled = true;
		mandalElmt.disabled = true;

		
					
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
			callAjax(rparam);
	}
	function getnextList(name,value,choice)
	{

		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:name,
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}

	function callAjax(param){
		var myResults;
 		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+param;			
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);								
								buildSelectOption(myResults);								
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

	function getMandalList(name,value,choice)
	{
		
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"Constituencies",
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}
	function getConstituencyList(name,value,choice)
	{
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"constituency",
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}
	function buildSelectOption(results)
	{		
		var taskValue= YAHOO.lang.JSON.parse(results.task);

		var selectedValue=taskValue.reportLevel;
		
		var taskType=taskValue.type;
		var changedVal = taskValue.changed;

		var selectedElmt;
		
		if(taskType == "cadreDetails")
		{
			if(selectedValue=="state")
			{
				if(changedVal == "false")
					selectedElmt=document.getElementById("districtField");
				else if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelDistrict");
			}
			else if(selectedValue=="district")
			{
				if(changedVal == "false")
					selectedElmt=document.getElementById("constituencyField");
				else if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelMandal");
			}
			else if(selectedValue=="constituency")
			{
				if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelConstituency");
				else if(changedVal == "false")
					selectedElmt=document.getElementById("constituencyField");
			}
			else if(selectedValue=="mandal")
				selectedElmt=document.getElementById("villageField");
			else if(selectedValue=="Constituencies")
				selectedElmt=document.getElementById("mandalField");
		}
		else if(taskType=="cadreLevel")
		{
			if(selectedValue == "State" || selectedValue == "District" || selectedValue == "Constituency" || selectedValue == "Mandal")
				selectedElmt=document.getElementById("cadreLevelState");
			else
				selectedElmt=document.getElementById("cadreLevelDistrict");
		}
		else if(selectedValue=="cadreLevelDistrict")
			selectedElmt=document.getElementById("cadreLevelMandal");
		
		
		var len=selectedElmt.length;			
		for(i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}	
		for(var val in results.namesList)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=results.namesList[val].id;
			opElmt.text=results.namesList[val].name;
			
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
</script>
<style type="text/css">
	#cadreRegistrationTable th
	{
		text-align:left;
		color:#DFA1A1;
	}

</style>
</head>
<body>
<s:form action="cadreRegisterAction" method="POST" theme="simple">
	<h2>Cadre Registration Page</h2>
		<table id="cadreRegistrationTable">
		<tr>
			<td colspan="2">
				<div style="color: red;">
					<s:actionerror />
					<s:fielderror />
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left"><h3><u style="color:#4D0A0A;">Cadre Details</u></h3></td>		
		</tr>
		<tr>
			<th><s:label for="firstNameField" id="fnameLabel"  value="%{getText('firstName')}" /></th>
			<td align="left"><s:textfield id="firstNameField" name="firstName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></th>
			<td align="left"><s:textfield id="middleNameField" name="middleName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /></th>
			<td align="left"><s:textfield id="lastNameField" name="lastName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /></th>
			<td align="left">
				<input type="radio" name="gender" value="M" checked="checked"/>Male
				<input type="radio" name="gender" value="F"/>Female
			</td>		
		</tr>
		<tr>
			<th><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /></th>
			<td align="left"><s:textfield id="mobileField" name="mobile"/>  </td>
		</tr>
		<tr>
			<th><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></th>
			<td align="left"><s:textfield id="emailField" name="email"/>  </td>
		</tr>
		<tr>
			<th><s:label for="stateField" id="stateLabel"  value="State" /></th>
			<td align="left">
				<s:select id="stateField" name="state" list="stateList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false')"></s:select>

				
			</td>
		</tr>
		<tr>
			<th><s:label for="districtField" id="districtLabel"  value="District" /></th>
			<td align="left">
				<s:select id="districtField" name="district" list="districtList" listKey="id" listValue="name" onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,'false')" headerKey="-1" headerValue="Select District"></s:select>
				
			</td>
		</tr>
		<tr>
			<th><s:label for="constituencyField" id="constituencyLabel"  value="Constituency" /></th>
			<td align="left">
				<s:select id="constituencyField" name="constituency" list="constituencyList" listKey="id" listValue="name" onchange="getMandalList(this.name,this.options[this.selectedIndex].value,'false')" headerKey="-1" headerValue="Select Constituency"></s:select> 
			</td>
		</tr>
		<tr>
			<th><s:label for="mandalField" id="mandalLabel"  value="Mandal" /></th>
			<td align="left">
				<s:select id="mandalField" name="mandal" list="mandalList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false')" headerKey="-1" headerValue="Select Mandal"></s:select>				 
			</td>
		</tr>
		<tr>
			<th><s:label for="villageField" id="villageLabel"  value="Village" /></th>
			<td align="left">
				<s:select id="villageField" name="village" list="villageList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Village"></s:select>				
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left"><h3><u style="color:#4D0A0A;">Cadre Level Details</u></h3></td>
		</tr>				
		<tr>
			<th><s:label for="cadreLevelField" id="cadreLevelLabel"  value="Cadre Level" /></th>
			<td align="left">
				<select id="cadreLevelField" name="cadreLevel" onchange="getStateList()">
					<option	 value='0'>Select Level</option>		
					<option  value='2'>State</option>	
					<option  value='3'>District</option>
					<option  value='4'>Constituency</option>	
					<option  value='5'>Mandal</option>					
				</select> <input type='hidden' name='cadreLevelValue' id='cadreLevelValue'>
			</td>
		</tr>
		<tr>		
			<th><s:label for="cadreLevelValueField" id="cadreLevelValueLabel"  value="Cadre Level Value" /></th>
			<td align="left">
				<select id="cadreLevelState" name="cadreLevelState" disabled = "true" onchange="setCadreValue(this.options[this.selectedIndex].value);
										getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
										this.options[this.selectedIndex].value)">
					<option>Select State</option>					
				</select> 
				<select id="cadreLevelDistrict" name="cadreLevelDistrict" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);
										getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
										this.options[this.selectedIndex].value)">
					<option>Select District</option>					
				</select> 
				
				<select id="cadreLevelConstituency" name="cadreLevelConstituency" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);
										getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
										this.options[this.selectedIndex].value)">
					<option>Select Constituency</option>					
				</select> 
		
				<select id="cadreLevelMandal" name="cadreLevelMandal" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value)">
					<option>Select Mandal</option>					
				</select> 
			</td>
		</tr>
		
		<tr>
		<td colspan="2" align="center">
			<input type="submit" value="Register">
		</td>
			
		</tr>
		</table>
	</s:form>
</body>
</html>