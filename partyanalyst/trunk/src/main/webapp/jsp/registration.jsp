<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>
<style type="text/css">

	.calBtn
	{
		background-image: url("images/icons/constituencyManagement/calendar.jpeg");
		height: 24px;
		width: 24px;	
	}

</style>
<script type="text/javascript">

	var ACCESSVALUE;

	var mainUserAccessType = '${userAccessType}';
	var mainUserAccessValue = '${userAccessValue}';
	var mainUserAccessLocation = '${userAccessLocation}';	

	var SUB_USER_ACCESSVALUE;

	function getAccessValue(value)
	{			
		var thElmt=document.getElementById("thId");
		var tdElmt=document.getElementById("tdId");

		thElmt.innerHTML='<font class="requiredFont"> * </font> <s:label theme="simple" for="accessValueField" id="accessValueLabel"  value="%{getText(\'accessValue\')}" />';

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
					selected:svalue,
					taskType:"mainUser"
			}
					
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/partyResultScopeAction.action?"+rparam;
			callAjax(jsObj,url,rparam);
	}
	function buildSelectBox(results,param)
	{	
		var tdElmt=document.getElementById("tdId");		
		
		var jsObj=YAHOO.lang.JSON.parse(param.substring(5));
		
		if(jsObj.selected!="null")
		{
			var levelValue;			
			/*
			if(ACCESSVALUE=="MLA" || ACCESSVALUE=="MP")
			levelValue="Constituency";
			else */
			levelValue=ACCESSVALUE;

			var elmt=document.getElementById(levelValue+"NameSelect");	
			
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
			str+='<select name="stateSelect" id="stateNameSelect" onchange="getValues(this.options[this.selectedIndex].value)">';
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
		if(ACCESSVALUE=="MLA")
		{
			str+='<select name="accessValue" id="MLANameSelect" style="margin-left:5px;">';
			str+='<option>Select Constituency</option>';
			str+='</select>';
		}
		if(ACCESSVALUE=="MP")
		{
			str+='<select name="accessValue" id="MPNameSelect" style="margin-left:5px;">';
			str+='<option>Select Constituency</option>';
			str+='</select>';
		}
		tdElmt.innerHTML=str;
	}
	
	function getValues(value)
	{
		var levelValue;
		/*
		if(ACCESSVALUE=="MLA" || ACCESSVALUE=="MP")
			levelValue="Constituency";
		else */
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

	function buildSubUsersRegionSelect(jsObj,results)
	{
		var tdElmt=document.getElementById("tdId");

		var str = '';
		str += '<select name="accessValue">';
		for(var i=0; i<results.length; i++)
		{
			str += '<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		str += '</select>';

		tdElmt.innerHTML = str;
	}

	function callAjax(jsObj,url,param)
	{		
 		var myResults; 				
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 	
								if(jsObj.taskType == "subUser")
									buildSubUsersRegionSelect(jsObj,myResults);									
								else if(jsObj.taskType == "mainUser")
									buildSelectBox(myResults.namesList,param);								
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}

	function showAndHide()
	{
		var Ele = document.getElementById('freeuserDiv');
		Ele.style.display='block';
	}

	function getAccessValuesForSubUser(value)
	{
		SUB_USER_ACCESSVALUE = value;

		var thElmt=document.getElementById("thId");
		var tdElmt=document.getElementById("tdId");

		thElmt.innerHTML='<font class="requiredFont"> * </font> <s:label theme="simple" for="accessValueField" id="accessValueLabel"  value="%{getText(\'accessValue\')}" />';
		
		var str = '';
		if(mainUserAccessType == value)
		{
			str += '<select name="accessValue">';
			str += '<option>'+mainUserAccessLocation+'</option>';
			str += '</select>';
			tdElmt.innerHTML=str;
		}
		else
		{
			var jsObj=
			{
					mainUserLocationId:mainUserAccessValue,
					reportLevel:value,
					taskType:"subUser"
			};				
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getSubUserAccessValueAction.action?"+rparam;
			callAjax(jsObj,url,rparam);
		}		
	}
function getStatesValidationForFreeuser()
{
	
var Ele=document.getElementById('freeuser');
var errorDiv=document.getElementById('errorDiv');
var stateSelectBox =document.getElementById("stateSelectBox").value;
var constituency = document.getElementById("constituency").value;
var str = '<font style="color:red;font-size:12px;">';

	if(Ele.checked == true)
	
	if(stateSelectBox == 0)
	{
		str += 'Select State';
		
		errorDiv.innerHTML = str;
		return false;
	}
	else if(constituency == 0)
	{
		str += 'Select Constituency';
		
		errorDiv.innerHTML = str;
		return false;
	}
	else
	
	return true;
	
}
</script>

</head>  
<body>  
<s:form action="RegistrationAction" method="POST" theme="simple">  
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
						<td width="100px;"><font class="requiredFont"> * </font><s:label for="firstNameField" id="fnameLabel"  value="%{getText('firstName')}" /></td>
						<td><s:textfield id="firstNameField" name="firstName"/>  </td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;" ><s:label for="middleNameField" id="middleNameLabel"  value="%{getText('middleName')}" /></td>
						<td><s:textfield id="middleNameField" name="middleName"/>  </td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="lastNameField" id="lastNameLabel"  value="%{getText('lastName')}" /></td>
						<td align="left"><s:textfield id="lastNameField" name="lastName"/>  </td>
					</tr>
					
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="partyField" id="partyLabel"  value="%{getText('party')}" /></td>
						<td align="left"> <s:select name="party" id="party"  list="#session.parties" listKey="id" listValue="name"></s:select> </td>
					</tr>
					
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="genderField" id="genderLabel"  value="%{getText('gender')}" /></td>
						<td><s:radio id="genderField" name="gender" list="#session.gender" />  </td>			
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><s:label for="dateOfBirthField" id="dateOfBirthLabel"  value="%{getText('dateOfBirth')}" /></td>
						<td> 
							<s:textfield id="dateOfBirthField" readonly="true" name="dateOfBirth" size="25"/>
							<DIV class="yui-skin-sam"><DIV id="DOB_div" style="position:absolute;"></DIV></DIV>
						 </td>
						<td>
							<input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" onclick="showDateCal('DOB_div','dateOfBirthField','1/1970')"/>
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
					
				</table>
			</div>	
		 </div>
			<div id="errorDiv"></div>
		 <div id="accessDetailsDiv" class="accessDivMain">
			 <div id="accessDetailsDivHead" class="accessDivHead"><u>Access Level Details...</u></div>
			 <div id="accessDetailsDivBody" class="accessDivBody">
				<table class="registrationTable">
					
					<tr>
					<td style="padding-left: 10px;">
					<s:label for="userrole" id="roleTypeid"/>Role Type
					</td>
				
				<td style="padding-left: 11px;"><s:checkbox id="freeuser"  value="freeuser" name ="freeuser" onclick="showAndHide()"/>FREE_USER</td>

				
					</tr>

			<tr><td colspan="2">		
			
		<div id="freeuserDiv" style="display:none;color:#926682"><b style="color:red">*</b> Select State:<s:select name="stateId" id="stateSelectBox" cssClass="textFieldStyle" headerKey="0" headerValue="Select State" list="#session.states" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')" cssStyle="width:189px;"  theme="simple" />
						


					<b style="color:red">*</b> 
 Select Constituency:<s:select name="constituencyId" id="constituency"  cssClass="textFieldStyle" headerKey="0" headerValue="Select Constituency" list="constituencies" listKey="id" listValue="name" cssStyle="width:189px;" theme="simple" /><span id="selectConstituency" style="border-radius: 2px 2px 2px 2px;padding-left:10px;font-weight:lighter"></span>
		 </div></td></tr>

				
					
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="userTypeField" id="userTypeLabel"  value="%{getText('userType')}" /></td>
						<td style="padding-left: 10px;"><s:radio id="userTypeField" name="userType" list="#session.userType" required="true"></s:radio> </td>
					</tr>
										
					<c:if test="${registrationType == 'subUser'}">				
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="accessTypeField" id="accessTypeLabel"  value="%{getText('accessType')}" /></td>
						<td style="padding-left: 10px;"><s:radio id="accessTypeField" name="accessType" list="#session.type" required="true" onclick="getAccessValuesForSubUser(this.value);"></s:radio> </td>
					</tr>
					</c:if>

					<c:if test="${registrationType == 'mainUser'}">
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="accessTypeField" id="accessTypeLabel"  value="%{getText('accessType')}" /></td>
						<td style="padding-left: 10px;"><s:radio id="accessTypeField" name="accessType" list="#session.type" onclick="getAccessValue(this.value);" required="true"></s:radio> </td>
					</tr>
					</c:if>					
				<tr>
						<td width="100px;" id="thId"> </td>
						<td style="padding-left: 15px;" id="tdId"> </td>
					</tr>

					<tr>
						<td width="100px;" id="thId"> </td>
						<td style="padding-left: 15px;" id="tdId">
							<input type="hidden" name="registrationType" value="${registrationType}"/>							
						</td>
					</tr>
					

				 </table>
			 </div>

			
		</div> 
		<div style="text-align: center;">
<s:submit name="Save" onclick="return getStatesValidationForFreeuser()"></s:submit> 
		</div>
</div>
</s:form>  
</body>  
</html>