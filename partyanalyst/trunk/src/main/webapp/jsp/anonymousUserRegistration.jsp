<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
//Problem Management
String redirectLoc = "";
String task = "";
String name = "";
String stateId = "";
String districtId = "";
String localBodyId = "";
String constituencyId = "";
String localBodyElectionTypeId = "";

if(request.getParameter("redirectLoc")!=null){
	redirectLoc = request.getParameter("redirectLoc");
}

if(request.getParameter("task")!=null){
	task = request.getParameter("task");
}

if(request.getParameter("name")!=null){
	name = request.getParameter("name");
}

if(request.getParameter("stateId")!=null){
	stateId = request.getParameter("stateId");
}

if(request.getParameter("districtId")!=null){
	districtId = request.getParameter("districtId");
}

if(request.getParameter("localBodyId")!=null){
	localBodyId = request.getParameter("localBodyId");
}

if(request.getParameter("constituencyId")!=null){
	constituencyId = request.getParameter("constituencyId");
}

if(request.getParameter("localBodyElectionTypeId")!=null){
	localBodyElectionTypeId = request.getParameter("localBodyElectionTypeId");
}
%>

<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>
	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

	<!-- YUI Dependency files (End) -->

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>	
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	
<script type="text/javascript">
function callAJAX(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "checkAnanymousUserNameAvailability")
					{
						showDetails(results);
					}
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
	
function getStates()
{
	getStatesComboBoxForACountry(1,'stateSelectBox');
}

function checkAvailability()
{
 	var name = document.getElementById("userNameField").value;
 	
 	if(name==""){
		document.getElementById("errorMessageDiv").innerHTML = "UserName field cannot be empty";
 	 }else{ 	 	
 		document.getElementById("errorMessageDiv").innerHTML = " ";
 		var jsObj=
		{		
 				userName:name,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/checkAnanymousUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);
 	 }
}

function showDetails(results)
{
	var result = document.getElementById("resultDIV");
	var str='';
	if(results==121){		
		str+='<div style="color:green"> User Name is available</div>';	
	}else{
		str+='<div style="color:red"> User Name is not available</div>';	
	}
	result.innerHTML = str;
}
</script>
<style type="text/css">
    fieldset {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width:379px;
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:10px;
		padding:5px;
	}
</style>
</head>  
<body>  
<s:form action="anonymousUserRegistrationAction.action" method="POST" theme="simple">  
   <br><br>
  <div id="headerDiv" style="margin-right: 500px;font-size:20px;text-decoration:underline;" > User Registration Form </div>
 <div id="registrationMainDiv">
		<table class="registrationTable">
			<tr>
				<td colspan="2">
					<div style="color: red;font-weight:bold;" id="errorMessageDiv">
						<s:actionerror />
						<s:fielderror />
						<s:actionmessage/>						
					</div>
				</td>
			</tr>
		</table>
		 <br>
        <FIELDSET>
		<LEGEND><strong>Account Details</strong></LEGEND>
		<div id="resultDIV"></div>
		 <div id="loginDetailsDiv" class="accessDivMain">
			<div id="loginDetailsDivBody" class="accessDivBody">
				<table class="registrationTable">					
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="userNameField" id="userNameLabel"  value="%{getText('userName')}" /></td>
						<td style="padding-left: 15px;"><s:textfield id="userNameField" name="userName"/>  </td>
						<td style="padding-left: 15px;"><input type="button" name="checkUserNameAvailability" value="Check Availability" onclick="checkAvailability()"/></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="passwordField" id="passwordLabel"  value="%{getText('password')}" /></td>
						<td style="padding-left: 15px;"><s:password id="passwordField" name="password"/>  </td>
					</tr>
					<tr>
						<td width="101px;"> <font class="requiredFont"> * </font> <s:label for="passwordField" id="passwordLabel"  value="%{getText('reEnterPassword')}" /></td>
						<td style="padding-left: 15px;"><s:password id="passwordField" name="reEnteredPassword"/>  </td>
					</tr>
				</table>
			</div>
		 </div>
         </FIELDSET>
		 <FIELDSET>
		 <LEGEND><strong>Personal Details</strong></LEGEND>
		 <div id="personalDetailsDiv" class="accessDivMain">
			 <div id="personalDetailsDivBody" class="accessDivBody">
				<table class="registrationTable" cellspacing="2" cellpadding="2">
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font><s:label for="firstNameField" id="fnameLabel"  value="%{getText('firstName')}" /></td>
						<td><s:textfield id="nameField" name="firstName"/>  </td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="userNameField" id="userNameLabel"  value="%{getText('lastName')}" /></td>
						<td><s:textfield id="userNameField" name="lastName"/>  </td>
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
				  </table>
				</div>
				</FIELDSET>
				<FIELDSET>
				<LEGEND><strong>Contact Details</strong></LEGEND>
				<div id="contactDetailsDiv" class="accessDivMain">
					<table class="registrationTable" cellspacing="2" cellpadding="2">
									
					<tr>
						<td width="100px;"><s:label for="mobileField" id="mobileLabel"  value="%{getText('mobile')}" /></td>
						<td><s:textfield id="mobileField" name="mobile"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="addressField" id="addressLabel"  value="%{getText('address')}" /></td>
						<td><s:textfield id="addressField" name="address"/>  </td>
					</tr>
					
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="stateSelectBox" id="stateLabel"  value="State" /></td>
								
					 	<td>
								<select class="regionsSelectBox"  name="state" id="stateSelectBox" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtSelectBox')" style="width:130px;">
								<option value="0"> Select State</option>
								</select>
						</td>
					</tr>	
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="districtSelectBox" id="districtLabel"  value="District" /></td>
						<td>
								<select class="regionsSelectBox"  name="district" id="districtSelectBox" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,'constituencySelectBox')" style="width:130px;">
								<option value="0"> Select District</option>
								</select>
						</td>
					</tr>	
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="constituencySelectBox" id="constituencytLabel"  value="Constituency" /></td>
						<td>							
								<select class="regionsSelectBox"  name="constituency" id="constituencySelectBox" style="width:130px;">
								<option value="0"> Select Constituency</option>
								</select>
						</td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="pinCodeField" id="pinCodeLabel"  value="%{getText('pincode')}" /></td>
						<td><s:textfield id="pincodeField" name="pincode"/>  </td>
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
						<td width="100px;"></td>
						<td> <div style="text-align: left;"><s:submit name="Save"></s:submit></div></td>
					</tr> 				
				</table>
			</div>	
		 </div>
		 </FIELDSET>

</div>
         <!--  Problem Management Params -->
         <input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
		 <input type="hidden" name="task" value="<%=task %>" />
		 <input type="hidden" name="name" value="<%=name %>" />
		 <input type="hidden" name="stateId" value="<%=stateId %>" />
		 <input type="hidden" name="districtId" value="<%=districtId %>" />
		 <input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
		 <input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
		 <input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />
</s:form>  
<script language="javascript">
getStates();
</script>
</body>  
</html>