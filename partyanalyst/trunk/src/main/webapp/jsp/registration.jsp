<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>
	<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
<script type="text/javascript">

	var ACCESSVALUE;

	function getAccessValue(value)
	{		
		var thElmt=document.getElementById("thId");
		var tdElmt=document.getElementById("tdId");

		thElmt.innerHTML='<s:label theme="simple" for="accessValueField" id="accessValueLabel"  value="%{getText(\'accessValue\')}" />';

		var str='';
		if(value=="COUNTRY")
		{			
			str+='<select id="accessValueField" name="accessValue">';			
			str+='<option value="1">India</option>';
			str+='</select>';			
		}else if(value=="STATE")
		{
			ACCESSVALUE="State";
			getAList(value,"null");

		}else if(value=="DISTRICT")
		{
			ACCESSVALUE="District";
			getAList(value,"null");
		}else if(value=="MLA")
		{
			ACCESSVALUE="MLA";
			getAList(value,"null");
		}else if(value=="MP")
		{
			ACCESSVALUE="MP";
			getAList(value,"null");
		}
		tdElmt.innerHTML=str;
	}
	function getAList(value,svalue)
	{
		var jsObj=
			{
					reportLevel:value,
					selected:svalue
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
			callAjax(rparam);
	}
	function buildSelectBox(results,param)
	{			
		var tdElmt=document.getElementById("tdId");		
		
		var jsObj=YAHOO.lang.JSON.parse(param.substring(5));
		
		if(jsObj.selected!="null")
		{
			var levelValue;

			if(ACCESSVALUE=="MLA" || ACCESSVALUE=="MP")
			levelValue="Constituency";
			else 
			levelValue=ACCESSVALUE;

			var elmt=document.getElementById(levelValue+"NameSelect");	
			console.log(elmt);
			removeSelectElements(elmt);			
			for (var l in results)
			{
				var y=document.createElement('option');
				y.value=results[l].id;
				y.text=results[l].name;
				
				try
			  	{
					elmt.add(y,null); // standards compliant
			  	}
				catch(ex)
			  	{
					elmt.add(y); // IE only
			  	}
			}
			
			return;
		}

		var str='';
		if(ACCESSVALUE=="State")
		{
			str+='<select name="accessValue" id="stateNameSelect">';
		}
		else
		{
			str+='<select name="stateSelect" id="stateNameSelect" onchange="getValues(this.options[this.selectedIndex].text)">';
		}		
			str+='<option>Select State</option>';
			for(var item in results)
				str+='<option value="'+results[item].id+'">'+results[item].name+'</option>';
			str+='</select>';
			
		if(ACCESSVALUE=="District")
		{			
			str+='<select name="accessValue" id="DistrictNameSelect" style="margin-left:5px;">';
			str+='<option>Select District</option>';
			str+='</select>';
		}
		if(ACCESSVALUE=="MLA" || ACCESSVALUE=="MP")
		{
			str+='<select name="accessValue" id="ConstituencyNameSelect" style="margin-left:5px;">';
			str+='<option>Select Constituency</option>';
			str+='</select>';
		}
		tdElmt.innerHTML=str;
	}
	
	function getValues(value)
	{
		var levelValue;

		if(ACCESSVALUE=="MLA" || ACCESSVALUE=="MP")
			levelValue="Constituency";
		else 
			levelValue=ACCESSVALUE;

		
		var elmt=document.getElementById(levelValue+"NameSelect");
		
		if (!elmt || ACCESSVALUE=="State")
			return;

		removeSelectElements(elmt);

		if(value=="Select State")
		{
			var y=document.createElement('option');
			if(accessValue=="District")
				y.text="Select District";
			if(accessValue=="Constituency")
				y.text="Select Constituency";
			try
			{
				elmt.add(y,null); // standards compliant
			}
			catch(ex)
			{
				elmt.add(y); // IE only
			}
			return;
		}

		getAList(levelValue,value);	

	}

	function removeSelectElements(elmt)
	{
		var len=elmt.length;			
		for(i=len-1;i>=0;i--)		
			elmt.remove(i);
	}
	function callAjax(param){
		
 		var myResults;
 		var url = "<%=request.getContextPath()%>/partyResultScopeAction.action?"+param;		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 								
								buildSelectBox(myResults.namesList,param);								
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
</script>
</head>  
<body>  
<s:form action="RegistrationAction" method="POST" theme="simple">  
    <h2>User Registration Form</h2>  
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
			<th><s:label for="userNameField" id="userNameLabel"  value="%{getText('userName')}" /></th>
			<td><s:textfield id="userNameField" name="userName"/>  </td>
		</tr>
		<tr>
			<th><s:label for="passwordField" id="passwordLabel"  value="%{getText('password')}" /></th>
			<td><s:textfield id="passwordField" name="password"/>  </td>
		</tr>
		<tr>
			<th><s:label for="dateOfBirthField" id="dateOfBirthLabel"  value="%{getText('dateOfBirth')}" /></th>
			<td><s:textfield id="dateOfBirthField" name="dateOfBirth"/>  </td>
		</tr>
		<tr>
			<th><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></th>
			<td><s:textfield id="emailField" name="email"/>  </td>
		</tr>
		<tr>
			<th><s:label for="telephoneNoField" id="telephoneNoLabel"  value="%{getText('telephoneNo')}" /></th>
			<td><s:textfield id="telephoneNoField" name="phone"/>  </td>
		</tr>
		<tr>
			<th><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /></th>
			<td><s:textfield id="mobileField" name="mobile"/>  </td>
		</tr>
		<tr>
			<th><s:label for="addressField" id="addressLabel"  value="%{getText('address')}" /></th>
			<td><s:textfield id="addressField" name="address"/>  </td>
		</tr>
		<tr>
			<th><s:label for="countryField" id="countryLabel"  value="%{getText('country')}" /></th>
			<td><s:textfield id="countryField" name="country"/>  </td>
		</tr>
		<tr>
			<th><s:label for="pincodeField" id="pincodeLabel"  value="%{getText('pincode')}" /></th>
			<td><s:textfield id="pincodeField" name="pincode"/>  </td>
		</tr>
		<tr>
			<th><s:label for="accessTypeField" id="accessTypeLabel"  value="%{getText('accessType')}" /></th>
			<td><s:radio id="accessTypeField" name="accessType" list="type" onclick="getAccessValue(this.value);"></s:radio> </td>
		</tr>
		<tr>
			<th id="thId"> </th>
			<td id="tdId"> </td>
		</tr>
		<tr>			
			<td colspan="2" align="center"><s:submit name="Save"></s:submit> </td>
		</tr>
	 </table>
   
</s:form>  
</body>  
</html>