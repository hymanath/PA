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

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />

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
			|| (name == "cadreLevelState" && cadreLevelElmtText == "Mandal")|| (name == "cadreLevelState" && cadreLevelElmtText == "Village"))
		{
			getnextList("state",id,"true");				
		}
		else if(name == "cadreLevelDistrict" && cadreLevelElmtText == "Constituency")
		{
			getnextList("constituency",id,"true");				
		}
		else if(name == "cadreLevelDistrict" && (cadreLevelElmtText == "Mandal" || cadreLevelElmtText == "Village"))
		{
			getnextList("district",id,"true");				
		}
		else if(name == "cadreLevelMandal" && (cadreLevelElmtText == "Village"))
		{
			getnextList("mandal",id,"true");				
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
//alert(123);
		getStatesNDistricts("cadreLevel",cadreLevelElmtText,cadreLevelElmtValue)
		
	}

	function getStatesNDistricts(level,text,value)
	{
		//alert(level);//type ==== cadreLevel
		//alert(text);//reportLevel ====Village
		//alert(value);//selected ====6
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
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;
		callAjax(rparam, jsObj, url);
	}

	function callAjax(param, jsObj, url){
		var myResults;			
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								//console.log(myResults);
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
			var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;			
		callAjax(rparam, jsObj, url);
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
			{
				if(changedVal == "true")
					selectedElmt=document.getElementById("cadreLevelVillage");
				else if(changedVal == "false")
					selectedElmt=document.getElementById("villageField");
			}
			else if(selectedValue=="Constituencies")
				selectedElmt=document.getElementById("mandalField");
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
</script>
<style type="text/css">
	
	#registrationMainDiv
	{
		text-align:left;
		margin-left:70px;
	}
	
</style>
</head>
<body>
<s:form action="cadreRegisterAction" method="POST" theme="simple">
	<h2>Cadre Registration Page</h2>
	<div id="registrationMainDiv">
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
		<div id="loginDetailsDivHead" class="accessDivHead"><u>Cadre Details...</u></div>
		<div id="loginDetailsDivBody" class="accessDivBody">
		<table class="registrationTable">		
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="firstNameField" id="fnameLabel"  value="%{getText('firstName')}" /></td>
			<td align="left"><s:textfield id="firstNameField" name="firstName"/>  </td>
		</tr>
		<tr>
			<td><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></td>
			<td align="left"><s:textfield id="middleNameField" name="middleName"/>  </td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /></td>
			<td align="left"><s:textfield id="lastNameField" name="lastName"/>  </td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /></td>
			<td align="left">
				<input type="radio" name="gender" value="M" checked="checked"/>Male
				<input type="radio" name="gender" value="F"/>Female
			</td>		
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /></td>
			<td align="left"><s:textfield id="mobileField" name="mobile" maxlength="10" />  </td>
		</tr>
		<tr>
			<td><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></th>
			<td align="left"><s:textfield id="emailField" name="email"/>  </td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /></td>
			<td align="left">
				<s:select id="stateField" cssClass="regionSelect" name="state" list="stateList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false')"></s:select>

				
			</td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /></td>
			<td align="left">
				<select id="districtField" class="regionSelect" name="district" onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,'false')" <c:if test="${sessionScope.USER.accessType == 'MP'}"> <c:out value="disabled='disabled'" /></c:if> >
					<c:forEach var="dist" items="${districtList}" >
					<option value="${dist.id}">${dist.name}</option>
					</c:forEach>
				</select>
				
			</td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}"/></td>
			<td align="left">
				<s:select id="constituencyField" cssClass="regionSelect" name="constituency" list="constituencyList" listKey="id" listValue="name" onchange="getMandalList(this.name,this.options[this.selectedIndex].value,'false')" headerKey="-1" headerValue="Select Constituency"></s:select> 
			</td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}" /></td>
			<td align="left">
				<s:select id="mandalField" cssClass="regionSelect" name="mandal" list="mandalList" listKey="id" listValue="name" onchange="getnextList(this.name,this.options[this.selectedIndex].value,'false')" headerKey="-1" headerValue="Select Mandal"></s:select>				 
			</td>
		</tr>
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="villageField" id="villageLabel"  value="%{getText('VILLAGE')}" /></td>
			<td align="left">
				<s:select id="villageField" cssClass="regionSelect" name="village" list="villageList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Village"></s:select>				
			</td>
		</tr>
		</table>
		</div>
		</div>
		
		<div id="personalDetailsDiv" class="accessDivMain">
		<div id="personalDetailsDivHead" class="accessDivHead"><u>Cadre Level Details...</u></div>
		<div id="personalDetailsDivBody" class="accessDivBody">
		<table class="registrationTable">				
		<tr>
			<td><font class="requiredFont"> * </font><s:label for="cadreLevelField" id="cadreLevelLabel"  value="%{getText('CADRE_LEVEL')}" /></td>
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
			<td><font class="requiredFont"> * </font><s:label for="cadreLevelValueField" id="cadreLevelValueLabel"  value="%{getText('CADRE_LEVEL_VALUE')}" /></td>
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
		
				<select id="cadreLevelMandal" name="cadreLevelMandal" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value);
										getCadreLevelValues(this.name,this.options[this.selectedIndex].text,
										this.options[this.selectedIndex].value)">
					<option>Select Mandal</option>					
				</select> 

				<select id="cadreLevelVillage" name="cadreLevelVillage" disabled ="true" onchange="setCadreValue(this.options[this.selectedIndex].value)">
					<option>Select Village</option>					
				</select> 
			</td>
		</tr>
		</table>
		</div></div>
		<div style="text-align: center;">
			<input type="submit" value="Register">
		</div>
	</div>
	</s:form>
</body>
</html>