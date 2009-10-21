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
		document.getElementById("cadreLevelValueField").value=value;
		return true;
	}
	function getDistrictLevelValues(name,value,id)
	{
	if(value=="MANDAL")
		document.getElementById("cadreLevelMandal").disabled = false;
	else
		document.getElementById("cadreLevelMandal").disabled = true;

		var jsObj=
			{
					reportLevel:name,
					value:value,
					id:id
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}
	function getnextList(name,value)
	{

		var jsObj=
			{
					reportLevel:name,
					selected:value
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}

	function callAjax(param){
		console.log(param);
 		var myResults;
 		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+param;			
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								//console.log(myResults);
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

	function buildSelectOption(results)
	{
		//console.log("In function");
		var taskValue= YAHOO.lang.JSON.parse(results.task);
		var selectedValue=taskValue.reportLevel;
		var selectedElmt;

		if(selectedValue=="state")
			selectedElmt=document.getElementById("districtField");
		else if(selectedValue=="district")
			selectedElmt=document.getElementById("mandalField");
		else if(selectedValue=="mandal")
			selectedElmt=document.getElementById("villageField");
		else if(selectedValue=="cadreLevel")
			selectedElmt=document.getElementById("cadreLevelDistrict");
		
		for(var val in results.namesList)
		{
			console.log(results.namesList[val].name);
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
</head>
<body>
<s:form action="cadreRegisterAction" method="POST" theme="simple">
	<h3>Cadre Registration Page</h3>
		<table>
		<tr>
			<td colspan="2">
				<div style="color: red;">
					<s:actionerror />
					<s:fielderror />
				</div>
			</td>
		</tr>
		<tr>
			<th><s:label for="firstNameField" id="fnameLabel"  value="%{getText('firstName')}" /></th>
			<td><s:textfield id="firstNameField" name="firstName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></th>
			<td><s:textfield id="middleNameField" name="middleName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /></th>
			<td><s:textfield id="lastNameField" name="lastName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /></th>
			<td><s:textfield id="genderField" name="gender"/>  </td>
		</tr>
		<tr>
			<th><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /></th>
			<td><s:textfield id="mobileField" name="mobile"/>  </td>
		</tr>
		<tr>
			<th><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></th>
			<td><s:textfield id="emailField" name="email"/>  </td>
		</tr>
		<tr>
			<th><s:label for="stateField" id="stateLabel"  value="State" /></th>
			<td>
				<select id="stateField" name="state" onchange="getnextList(this.name,this.options[this.selectedIndex].value)">
					<option>Select State</option>
					<option value="1">Andhra Pradesh</option>
				</select> 
			</td>
		</tr>
		<tr>
			<th><s:label for="districtField" id="districtLabel"  value="District" /></th>
			<td>
				<select id="districtField" name="district" onchange="getnextList(this.name,this.options[this.selectedIndex].value)">
					<option>Select District</option>					
				</select> 
			</td>
		</tr>
		<tr>
			<th><s:label for="mandalField" id="mandalLabel"  value="Mandal" /></th>
			<td>
				<select id="mandalField" name="mandal" onchange="getnextList(this.name,this.options[this.selectedIndex].value)">
					<option>Select Mandal</option>					
				</select> 
			</td>
		</tr>
		<tr>
			<th><s:label for="villageField" id="villageLabel"  value="Village" /></th>
			<td>
				<select id="villageField" name="village">
					<option>Select Village</option>					
				</select> 
			</td>
		</tr>
		
				
		<tr>
			<th><s:label for="cadreLevelField" id="cadreLevelLabel"  value="Cadre Level" /></th>
			<td>
				<select id="cadreLevelField" name="cadreLevel" onchange="getDistrictLevelValues(this.name,		
														this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">
					<option>Select Level</option>		
					<option  value='2'>STATE</option>	
					<option  value='3'>DISTRICT</option>	
					<option  value='5'>MANDAL</option>					
				</select> <input type='hidden' name='cadreLevelValue' id='cadreLevelValue'>
			</td>
		</tr>
		<tr>		
			<th><s:label for="cadreLevelValueField" id="cadreLevelValueLabel"  value="Cadre Level Value" /></th>
			<td>
				<select id="cadreLevelDistrict" name="cadreLevelDistrict" onchange="setCadreValue(this.options[this.selectedIndex].value);
										getDistrictLevelValues(this.name,this.options[this.selectedIndex].text,
										this.options[this.selectedIndex].value)">
					<option>Select District Level Value</option>					
				</select> 
			</td>
		</tr>
		
		
		<tr>
			<td>
				<select id="cadreLevelMandal" name="cadreLevelMandal" onchange="setCadreValue(this.options[this.selectedIndex].value)">
					<option>Select Mandal Level Value</option>					
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