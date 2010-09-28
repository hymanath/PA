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

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>	
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	
<script type="text/javascript">

</script>

</head>  
<body>  
<s:form action="anonymousUserRegistrationAction.action" method="POST" theme="simple">  
    <h2>User Registration Form</h2> 
 <div id="registrationMainDiv">
		<table class="registrationTable">
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
			<div id="loginDetailsDivHead" class="accessDivHead"><u>Login Details...</u></div>
			<div id="loginDetailsDivBody" class="accessDivBody">
				<table class="registrationTable">
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="userNameField" id="userNameLabel"  value="%{getText('userName')}" /></td>
						<td style="padding-left: 15px;"><s:textfield id="userNameField" name="userName"/>  </td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="passwordField" id="passwordLabel"  value="%{getText('password')}" /></td>
						<td style="padding-left: 15px;"><s:password id="passwordField" name="password"/>  </td>
					</tr>
				</table>
			</div>
		 </div>

		 <div id="personalDetailsDiv" class="accessDivMain">
			 <div id="personalDetailsDivHead" class="accessDivHead"><u>Personal Details...</u></div>
			 <div id="personalDetailsDivBody" class="accessDivBody">
				<table class="registrationTable">
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font><s:label for="firstNameField" id="fnameLabel"  value="%{getText('name')}" /></td>
						<td><s:textfield id="nameField" name="name"/>  </td>
					</tr>
										
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /></td>
						<td><s:radio id="genderField" name="gender" list="#session.gender" value="male"/>  </td>			
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><s:label for="dateOfBirthField" id="dateOfBirthLabel"  value="%{getText('dateOfBirth')}" /></td>
						<td> 
							<s:select name="day" id="dobDay"  list="#session.dobDay" listKey="id" listValue="name"></s:select>
							<s:select name="month" id="dobMonth" list="#session.dobMonth" listKey="id" listValue="name"></s:select>
							<s:select name="year" id="dobYear" list="#session.dobYear" listKey="id" listValue="name"></s:select>							
						 </td>
					</tr>
					
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></td>
						<td><s:textfield id="emailField" name="email"/>  </td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="telephoneNoField" id="telephoneNoLabel"  value="%{getText('telephoneNo')}" /></td>
						<td><s:textfield id="telephoneNoField" name="phone"/>  </td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /></td>
						<td><s:textfield id="mobileField" name="mobile"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="addressField" id="addressLabel"  value="%{getText('address')}" /></td>
						<td><s:textfield id="addressField" name="address"/>  </td>
					</tr>
					<tr>
					
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="countrySelectBox" id="countryLabel"  value="%{getText('country')}" /></td>
					 	<td>
								<select class="regionsSelectBox"  name="country" id="countrySelectBox" onchange="getStatesComboBoxForACountry(this.options[this.selectedIndex].value,'stateSelectBox')">
								<option value="0"> Select Country </option>
								<option value="1"> India </option>
								</select>
					 	</td>
					</tr>
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="stateSelectBox" id="stateLabel"  value="%{getText('state')}" /></td>
								
					 	<td>
								<select class="regionsSelectBox"  name="state" id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtSelectBox')">
								<option value="0"> Select State</option>
								</select>
						</td>
					</tr>	
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="districtSelectBox" id="districtLabel"  value="%{getText('district')}" /></td>
						<td>
								<select class="regionsSelectBox"  name="district" id="districtSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,'constituencySelectBox')">
								<option value="0"> Select District</option>
								</select>
						</td>
					</tr>	
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="constituencySelectBox" id="constituencytLabel"  value="%{getText('constituency')}" /></td>
						<td>							
								<select class="regionsSelectBox"  name="constituency" id="constituencySelectBox" onchange="getMandalsComboBoxForAConstituency(this.options[this.selectedIndex].value,'mandalSelectBox')">
								<option value="0"> Select Constituency</option>
								</select>
						</td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="pinCodeField" id="pinCodeLabel"  value="%{getText('pincode')}" /></td>
						<td><s:textfield id="pincodeField" name="pincode"/>  </td>
					</tr>	
					<tr>
						<td width="100px;"></td>
						<td> <div style="text-align: left;"><s:submit name="Save"></s:submit></div></td>
					</tr> 				
				</table>
			</div>	
		 </div>

</div>
</s:form>  
</body>  
</html>