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
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<style type="text/css">

	.calBtn
	{
		background-image: url("images/icons/constituencyManagement/calendar.jpeg");
		height: 24px;
		width: 24px;	
	}
	
	#genderFieldFemale,#genderFieldMale {
	    float: left;
		margin-bottom: 14px;
		margin-left: -13px;
		margin-top: 7px;
	}
	#freeuser
	{
		float: left;
		margin-bottom: 11px;
		margin-left: -9px;
		margin-top: 4px;
	}
	#userTypeFieldadmin
	{
		float: left;
    margin-bottom: -19px;
    margin-left: -2px;
    margin-top: 5px;
	}
	
	label 
	{
		margin-left: 12px;
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
			str+='<select id="accessValueField" name="accessValue"  style="float: left; margin-left: -372px;">';			
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
			str+='<select name="accessValue" id="stateNameSelect"  style="float: left; margin-left: -372px;">';
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
			str+='<select name="accessValue" id="DistrictNameSelect"  style="float: left; margin-left: -372px;">';
			str+='<option>Select District</option>';
			str+='</select>';
		}
		if(ACCESSVALUE=="MLA")
		{
			str+='<select name="accessValue" id="MLANameSelect"  style="float: left; margin-left: -372px;">';
			str+='<option>Select Constituency</option>';
			str+='</select>';
		}
		if(ACCESSVALUE=="MP")
		{
			str+='<select name="accessValue" id="MPNameSelect"  style="float: left; margin-left: -372px;">';
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
		str += '<select name="accessValue"  style="float: left; margin-left: -372px;">';
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

								else if(jsObj.taskType =="checkAnanymousUserNameAvailability")
								{
									getUserNameAvailabilityResult(myResults);
								}

							}catch (e) {   
							   	//alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}

function getUserNameAvailabilityResult(results)
{  
	var result = document.getElementById("userNameAvlDiv");
	var submitButtonEle = document.getElementById("RegistrationAction_Save");

	var str = '';

	if(results == 121)
	{		
		str+="<font style='color:green;'> Username is available</font>";
		submitButtonEle.disabled = false;
	}
	else
	{
		str+="<font style='color:red;'> Username is not available</font>";
	    submitButtonEle.disabled = true;
	}
	result.innerHTML = str;

}


	function showAndHide()
	{
		var Ele = document.getElementById('freeuserDiv');
		var checkEle=document.getElementById('freeuser');
		if(checkEle.checked == true)
		Ele.style.display='block';
		if(checkEle.checked == false)
		Ele.style.display='none';
	}

	function getAccessValuesForSubUser(value)
	{
		SUB_USER_ACCESSVALUE = value;
		var thElmt=document.getElementById("thId");
		var tdElmt=document.getElementById("tdId");

		thElmt.innerHTML=' <s:label theme="simple" for="accessValueField" id="accessValueLabel"  value="%{getText(\'accessValue\')}"/><b  style="color: red; float: left; margin-right: 88px; margin-top: -16px; margin-bottom: 17px;">*</b>';
		
		var str = '';
		if(mainUserAccessType == value)
		{
			str += '<select name="accessValue"  style="float: left; margin-left: -372px;">';
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

/* function checkUserNameAvailability()
{
	var userName = document.getElementById("userNameField").value;
	var errorDiv = document.getElementById('userNameAvlDiv');
	var submitButtonEle = document.getElementById("RegistrationAction_Save");
	//var userName = trim(userNameEle);
	
	
	if(userName.length == 0)
	{
		errorDiv.innerHTML = '<font style="color:red">UserName Sholud Not Be Empty</font>';
		submitButtonEle.disabled = true;
	}
	else if(userName.indexOf(" ")!= -1)
	{
		errorDiv.innerHTML = '<font style="color:red">UserName Sholud Not Contain Spaces.</font>';
		submitButtonEle.disabled = true;

	}
	else if(userName.length > 0 && userName.length < 6)
	{
		errorDiv.innerHTML = '<font style="color:red">UserName Name Minimum Of 6 Characters.</font>';
	}
	else 
	{
		var jsObj=
		{		
 				userName:userName,
				taskType:"checkAnanymousUserNameAvailability"				
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
		callAjax(jsObj,url,rparam);
	}
	
}
 */


</script>

</head>  
<body>  
<s:form action="RegistrationAction" method="POST" theme="simple">  
    <div align="center" style="margin-bottom: 17px; margin-top: 13px; font-family: arial; font-size: 15px; width: 900px; padding-bottom: 3px; height: 28px; margin-left: 53px; padding-top: 12px; font-weight: bold; border-radius: 4px 4px 4px 4px; color: white; background-color: rgb(91, 192, 222);">User Registration Form</div> 
 <div id="registrationMainDiv" align = "center"  style="font-family: verdana; font-size: 12px;">
		<table class="registrationTable">
			<tr>
				<td colspan="2" >
					<div style="color: red;">
						<s:actionerror />
						<s:fielderror />
					</div>
				</td>
			</tr>
		</table>

		 <div id="loginDetailsDiv" class="accessDivMain" align="center">
		 <div id="errorMsgDiv"></div>
			<div id="loginDetailsDivHead" class="accessDivHead"><div style="width: 102px; margin-bottom: 11px; color: blue; font-family: verdana; font-size: 13px; margin-left: -265px;">Login Details</div></div>
			<div id="loginDetailsDivBody" class="accessDivBody" style="margin-left: -11px;">
				<table class="registrationTable" >
					<tr>
						<td width="100px;"><span>User Name </span><b class="requiredFont" style="color:red"> * </b></td>
						<td ><s:textfield id="userNameField" name="userName" onBlur="checkUserNameAvailability()" /></td>
						<td><div id="userNameAvlDiv"></div></td>
					</tr>
				</table>
				<table class="registrationTable">
					<tr>
						<td width="100px;"><span>Password</span><b class="requiredFont" style="color:red"> * </b></td>
						<td ><s:password id="passwordField" name="password"/>  </td>
						<td></td>
					</tr>
				</table>
			</div>
		 </div>

		 <div id="personalDetailsDiv" class="accessDivMain">
			 <div id="personalDetailsDivHead" class="accessDivHead"><div style="color: blue; font-family: verdana; font-size: 13px; margin-left: -243px; margin-bottom: 14px; width: 130px; margin-top: 10px;">Personal Details</div></div>
			 <div id="personalDetailsDivBody" class="accessDivBody" align="center">
				<table class="registrationTable">
					<tr>
						<td width="100px;"><span>First Name</span><b class="requiredFont" style="color:red"> * </b></td>
						<td><s:textfield id="firstNameField" name="firstName"/>  </td>
					</tr>
					<tr>
						<td width="100px;" ><span>Middle Name</span></td>
						<td><s:textfield id="middleNameField" name="middleName"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><span>Last Name</span><b class="requiredFont" style="color:red"> * </b></td>
						<td align="left"><s:textfield id="lastNameField" name="lastName"/>  </td>
					</tr>
					
					<tr>
						<td width="100px;"><span>Party</span><b class="requiredFont" style="color:red"> * </b></td>
						<td align="left"> <s:select name="party" id="party"  list="#session.parties" listKey="id" listValue="name"></s:select> </td>
					</tr>
					
					<tr>
						<td width="100px;"><span>Gender</span><b class="requiredFont" style="color:red"> * </b></td>
						<td><s:radio theme="simple" id="genderField" name="gender" list="#session.gender" />  </td>
						<!--<s:iterator value="#session.gender" var="gender" >
							<td width="150px;"><input type="radio" id="accessTypeField" name="gender"></input><span style="float: left; margin: -14px 10px 0px 18px;">${gender}</span></td>
						</s:iterator>		-->				
					</tr>
					<tr>
						<td width="100px;"><span>Date Of Birth</span><b class="requiredFont" style="color:red"> * </b></td>
						<td> 
							<s:textfield id="dateOfBirthField"  readonly="true" name="dateOfBirth" size="25"/>
							<DIV class="yui-skin-sam"><DIV id="DOB_div" style="position:absolute;"></DIV></DIV>
						 </td>
						<td>
							<input id="calBtnEl" type="button" style="width: 28px;" class="calBtn" title="Click To Select A Date" onclick="showDateCal('DOB_div','dateOfBirthField','1/1970')"/>
						</td>
					</tr>
					<tr>
						<td width="100px;"><span>Email</span></td>
						<td><s:textfield id="emailField" name="email"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><span>Telephone</span></td>
						<td><s:textfield id="telephoneNoField" name="phone"/>  </td>
					</tr>
					<tr>
						<td width="100px;"> <span>Mobile</span><b class="requiredFont" style="color:red"> * </b></td>
						<td><s:textfield id="mobileField" name="mobile"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><span>Address</span><b class="requiredFont" style="color:red"> * </b></td>
						<td><s:textfield id="addressField" name="address"/>  </td>
					</tr>
					
				</table>
			</div>	
		 </div>
		<div id="errorDiv"></div>
		<div id="accessDetailsDiv" class="accessDivMain" align="center">
		<div id="accessDetailsDivHead" class="accessDivHead"><div style="color: blue; font-family: verdana; font-size: 13px; margin-bottom: 14px; margin-top: 10px; width: 150px; margin-left: -229px;">Access Level Details</div></div>
		<div id="accessDetailsDivBody" class="accessDivBody" style="width: 102px; margin-bottom: 11px; font-family: verdana; font-size: 13px; margin-left: -265px;">
		<table class="registrationTable">
					
		<tr style="float: left; margin-left: 1px;">
			<td ><span>Role Type</span></td>

			<td  style="float: left; margin-left: 130px; margin-top: -22px;"><s:checkbox id="freeuser"  value="freeuser" name ="freeuser" onclick="showAndHide()" /><span style="float: left; margin-left: 9px; margin-top: 2px;">FREE_USER</span></td>

		</tr>

		<tr>
			<td colspan="2">		
			
			<div id="freeuserDiv" style="display:none;color:#926682"><b style="color:red">*</b> Select State<s:select name="stateId" id="stateSelectBox" cssClass="textFieldStyle" headerKey="0" headerValue="Select State" list="#session.states" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"  theme="simple"  style="width: 148px; margin-left: 61px;"/>
							


				<br><br><b style="color:red">*</b> 
			 Select Constituency<select name="constituency" id="constituency"  cssClass="textFieldStyle" style="margin-top: -18px; margin-left: 12px;"/>
			 <option>Select Constituency</option>
			 </select><span id="selectConstituency" style="border-radius: 2px 2px 2px 2px;padding-left:10px;font-weight:lighter"></span>
			</div>
			</td>
		 </tr>

				
					
					<tr><td>
					<table>
					<tr>
						<td><span>User Type</span><b class="requiredFont" style="color: red; margin-right: 41px;"> * </b></td>
						<td><s:radio id="userTypeField" name="userType" list="#session.userType" required="true" ></s:radio> </td>
						<!--<s:iterator value="#session.userType" var="userType" >
							<td width="150px;"><input type="radio" id="userTypeField"></input><span style="float: left; margin: -14px 10px 0px 18px;">${userType}</span></td>
						</s:iterator>-->
					</tr>
					</table></td>
					</tr>					
					<c:if test="${registrationType == 'subUser'}">				
					<tr>
					<td>
					<table>
					<tr>
						<td>Access Type<b class="requiredFont" style="color: red; margin-right: 119px; margin-bottom: 15px;"> * </b></td>
						<!--<s:radio id="accessTypeField" name="accessType" list="#session.type" required="true" onclick="getAccessValuesForSubUser(this.value);" ></s:radio> -->
						<s:iterator value="#session.type" var="accessType" >
							<td><input type="radio" id="accessTypeField" name="accessType" onclick="getAccessValuesForSubUser('${accessType}');"></input><span style="float: left; margin: -14px 10px 0px 18px;">${accessType}</span></td>
						</s:iterator>
					</tr>
					</table>
					</td>
					</tr>
					</c:if>

					<c:if test="${registrationType == 'mainUser'}">
					<tr>
						<td ><font class="requiredFont"> * </font> <s:label for="accessTypeField" id="accessTypeLabel"  value="%{getText('accessType')}" /></td>
						<td style="padding-left: 10px;"><s:radio id="accessTypeField" name="accessType" list="#session.type" onclick="getAccessValue(this.value);" required="true"></s:radio> </td>
					</tr>
					</c:if>					
					<tr>
						<td id="thId"> </td>
						<td style="padding-left: 15px;" id="tdId"> </td>
					</tr>

					<tr>
						<td  id="thId"> </td>
						<td style="padding-left: 15px;" id="tdId">
							<input type="hidden" name="registrationType" value="${registrationType}"/>							
						</td>
					</tr>
					

		</table>
		</div>

			
		</div> 
		<div style="text-align: center;">
<s:submit name="Save" style="background-color: green; color: white; height: 38px; border-radius: 7px 7px 7px 7px; width: 78px;"  onclick="return getStatesValidationForFreeuser()" ></s:submit> 
		</div>
</div>
</s:form>  

</body>  
</html>