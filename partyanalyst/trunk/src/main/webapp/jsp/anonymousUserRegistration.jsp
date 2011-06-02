<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
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

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>

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

<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>


<!-- JQuery files (End) -->
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>	
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>	
    <link rel="stylesheet" type="text/css" href="styles/jQuery/jquery.datepick.css">

<script type="text/javascript" src="js/jQuery/jquery.datepick.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.datepick-en-GB.js"></script>

<script type="text/javascript">
var spvar = 'special';
var r={
  'special':/[\W]/g
  //'quotes':/['\''&'\"']/g,
  //'notnumbers':/[^\d]/g
}

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
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
function checkAvailability()
{ 
	var name = document.getElementById("userNameField").value;
 	
	if(name==""){
		document.getElementById("resultDIV").innerHTML = "<font color='red'>UserName field cannot be empty</font>";
 	 }
	 else if (name.charAt(0).indexOf(" ")==0){
		 document.getElementById("resultDIV").innerHTML = "<font color='red'>UserName Should not contain spaces</font>";
 	 }
	 else if(name.length < 6)
	{
		document.getElementById("resultDIV").innerHTML = "<font color='red'>UserName must be between 6 and 20 characters long.</font>";
	}
	 
     else{ 	 	
 		document.getElementById("resultDIV").innerHTML = " ";
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

function validUsername()
{
	var textEle = document.getElementById("userNameField");
	var resultDIVEle = document.getElementById("resultDIV");

	resultDIVEle.innerHTML = "";
	if(textEle.value.length > 0 && textEle.value.length < 6)
	{
		resultDIVEle.innerHTML = "<font color='red'>UserName must be between 6 and 20 characters long.</font>";
		return;
	}
		
	if(textEle.value.match(r[spvar]))
		resultDIVEle.innerHTML = "<font color='red'>UserName Should not contain Special Characters</font>";
	else
		resultDIVEle.innerHTML = "";
		
}
function validPassword()
{  
	var textEle = document.getElementById("passwordField");
	var resultDIVEle = document.getElementById("pwdDiv");
	resultDIVEle.innerHTML = "";
	if(textEle.value.length > 0 &&textEle.value.length < 6)
		resultDIVEle.innerHTML = "<font color='red'>Password minimum of 6 characters in length.</font>";
}
function validRePassword()
{
	var pwd1 = document.getElementById("passwordField");
	var pwd2 = document.getElementById("rePasswordField");
	var resultDIVEle = document.getElementById("pwdDiv");
	resultDIVEle.innerHTML = "";
	
	if(pwd1.value.length > 0 && pwd1.value.length < 6)
	{
		resultDIVEle.innerHTML = "<font color='red'>Password minimum of 6 characters in length.</font>";
		return;
	}
	if(pwd1.value.length > 0 && pwd2.value.length > 0 && pwd1.value != pwd2.value)
		resultDIVEle.innerHTML = "<font color='red'>Passwords do not match.</font>";
}

function validNames(id)
{
	var textEle = document.getElementById(id);
	var resultDIVEle = document.getElementById("namesDiv");
	var name = '';
	if(id == 'nameField')
		name = 'First name';
	else 
		name = 'Last name';

	resultDIVEle.innerHTML = "";
	if(textEle.value.match(r[spvar]))
		resultDIVEle.innerHTML = "<font color='red'>"+name+" Should not contain Special Characters</font>";
	else
		resultDIVEle.innerHTML = "";
		
}
function showDetails(results)
{
	var result = document.getElementById("resultDIV");
	var str='';
	if(results==121){		
		str+='<div style="color:green"> Username is available</div>';	
	}else{
		str+='<div style="color:red"> Username is not available</div>';	
	}
	result.innerHTML = str;
}

function validateMobile()
{
	var mobilEle = document.getElementById("mobileField");
	var resultDIVEle = document.getElementById("mobileDiv");
	var mobile = mobilEle.value;

	resultDIVEle.innerHTML = "";
	
	if(isNaN(mobile)|| mobile.indexOf(" ")!=-1)
		resultDIVEle.innerHTML = "<font color='red'><b>Mobile Number Should contains only Numbers.</b></font>";
		
	else if((mobile.length > 0 && mobile.length < 10) || mobile.length > 12)
		resultDIVEle.innerHTML = "<font color='red'><b>Plase provide correct Mobile number.</b></font>";
		
}

function validateEmail()
{
	var email = document.getElementById("emailField").value;
	var resultDIVEle = document.getElementById("mobileDiv");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(email.length == 0)
		resultDIVEle.innerHTML = "<font color='red'><b>Please Enter Email Address.</b></font>";
	
	else if(reg.test(email) == false)
		resultDIVEle.innerHTML = "<font color='red'><b>Plase provide correct Email Address.</b></font>";

}

</script>
<style type="text/css">
   
   .calBtn
	{
		background-image: url("images/icons/constituencyManagement/calendar.jpeg");
		height: 24px;
		width: 24px;	
	}

   .dotline {
		border-top: 1px solid #D6D6D6;
		height: 1px;
		margin-bottom: 10px;
		margin-top: 0.5em;
		width: 70%;
   }
	.headerDiv
	{
		border-bottom: 2px solid #B8C4D0;
		display: inline-block;
		margin-left: 20px;
		overflow: hidden;
		padding-top: 12px;
		width: 66.6%;
		
	}

	.headerDiv p 
	{
       color: #662986;
	   font-size: 160%;
	   padding-bottom: 10px;
	   text-align:center;
	}

	.row
	{
	   margin-bottom: 0.9em;
       position: relative;
	}

	.row label {
		display: inline-block;
		color:#926682;
		text-align: right;
		/*width: 13.8em;*/
		font-weight : bold;
    }
	
	.regTable td {
		color:#926682;
		font-family:verdana;
		font-weight:bold;
		text-align:right;
	}

	.label {
		color: #505050;
		font-size: 100%;
		padding-right: 4px;
    }

	#personalFieldsCollection {
		margin-top: 0.5em;
    }

   .fieldsCollection {
		margin: -1.3em 1.5em;
		position: relative;
    }
   fieldset, img {
		border: 0 none;
   }

   .fieldsCollection legend {
		color: #662986;
		font-size: 108%;
		font-weight: bold;
   }
	
   .freeUserRegDetailsTable
   {
	margin-left:70px;
   }

   .freeUserRegDetailsTable td
   {
	padding:5px;
   }
</style>
</head>  
<body>  
<s:form action="anonymousUserRegistrationAction.action" method="POST" theme="simple" enctype="multipart/form-data">  
   <br><br>
  <div id="headerDiv" class="headerDiv"> 
   <c:if test="${registrationId == '0'}">
   	<p>Get a free registration to connect to people and info that you care about</p>
   </c:if>
   <c:if test="${registrationId != '0'}">
   	User Profile
   </c:if> 
  </div>
 <div id="registrationMainDiv" style="padding-bottom:5px;">
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

		<c:if test="${registrationId == '0'}">
	        <FIELDSET id="personalFieldsCollection" class="fieldsCollection">

			    <div class="dotline"></div>
				<LEGEND> Select an Username and password</LEGEND>
				 <div id="loginDetailsDiv" class="accessDivMain">
					<div id="loginDetailsDivBody" class="accessDivBody">
						
						<Table class="regTable">
							<tr>
								<td width="160px"><s:label for="userNameField" id="userNameLabel" value="%{getText('userName')}" /><font class="requiredFont"> * </font></td>
								
								<td><s:textfield id="userNameField" name="userName" maxlength="20" onBlur="validUsername()"/></td>  
								<td><input type="button" name="checkUserNameAvailability" value="Check Availability" onclick="checkAvailability()"/></td>
								
							</tr>
						</Table>

						<Table class="regTable">
							<tr><td width="160px"></td><td><div id="resultDIV"></div></td></tr>
						</Table>

						<Table class="regTable">	
							<tr>
								<td width="160px"><s:label for="passwordField" id="passwordLabel" value="%{getText('password')}" /><font class="requiredFont"> * </font></td>
								<td><s:password id="passwordField" name="password" maxlength="20" onBlur="validPassword()"/></td>
								
								<td width="160px"><s:label for="passwordField" id="passwordLabel" value="%{getText('reEnterPassword')}" /><font class="requiredFont"> * </font></td> 
								<td><s:password id="rePasswordField" name="reEnteredPassword" maxlength="20" onBlur="validRePassword()"/></td>  								

							</tr>
						</Table>
						
						<Table class="regTable">
							<tr><td width="160px"></td><td><div id="pwdDiv"></div></td></tr>
						</Table>

					</div>
				 </div>
	         </FIELDSET>
         </c:if>
		  <FIELDSET id="personalFieldsCollection" class="fieldsCollection">

          <div class="dotline"></div>
		  <LEGEND>Provide Personal Information</LEGEND>
		     <div id="personalDetailsDiv" class="accessDivMain">
			 <div id="personalDetailsDivBody" class="accessDivBody">
					
					<table class="freeUserRegDetailsTable">
						<tr class="row">
							<td>
								<font class="requiredFont"> * </font>
								<s:label for="nameField" id="fnameLabel"  cssClass="label" value="%{getText('firstName')}" cssStyle="width:auto"/>						
							</td>
							<td><s:textfield id="nameField" name="firstName" onBlur="validNames('nameField')"/> </td>

							<td style="width:110px;padding-left:40px;">
								<font class="requiredFont"> * </font>
								 <s:label for="lastNameField" id="userNameLabel" cssClass="label" value="%{getText('lastName')}" cssStyle="width:auto"/>					
							</td>
							<td><s:textfield id="lastNameField" name="lastName" onBlur="validNames('lastNameField')"/> </td>
						</tr>
					</table>
					
					<table class="regTable">
						<tr class="row">
							<td width="160px"></td><td><div id="namesDiv"></div></td>
						</tr>
					</table>

					<table class="freeUserRegDetailsTable">
						<tr class="row">
							<td>
								<font class="requiredFont"> * </font>
								<s:label for="genderField" id="genderLabel" value="%{getText('gender')}" cssClass="label" cssStyle="width:auto" />		
							</td>
							<td><s:radio id="genderField" name="gender" list="#session.gender"/> </td>						
						</tr>
					</table>

					<table class="freeUserRegDetailsTable">
						<tr class="row">
							<td valign="top">
								<font class="requiredFont"> * </font>
								<s:label for="dateOfBirthField" id="dateOfBirthLabel" class="label"  value="%{getText('dateOfBirth')}" />	
							</td>
							<td><s:textfield id="dateOfBirthField"  name="dateOfBirth" size="25" onfocus="showCalendar(this.id)"/>
						</td>
						
						<td valign="top"><input id="dateOfBirthField" type="button" class="calBtn" title="Click To Select A Date" onclick="focusCalTextElmt(this.id)"/></td>						
						</tr>

					</table>
				   
				<!--	<DIV class="yui-skin-sam"><DIV id="DOB_div" style="position:absolute;margin-left:400px;"></DIV>
					</DIV> -->

					</div>
				</div>
				</FIELDSET>


				<FIELDSET id="personalFieldsCollection" class="fieldsCollection">
				<div class="dotline"></div>
				<LEGEND>Provide Contact Details</LEGEND>
				<div id="contactDetailsDiv" class="accessDivMain">
					
					<table class="freeUserRegDetailsTable">
						<tr class="row">
							<td width="82px;">
							<s:label for="mobileField" id="mobileLabel"  class="label" value="%{getText('mobile')}" />					
							</td>
							<td><s:textfield id="mobileField" name="mobile" maxlength="12" onBlur="validateMobile()"/>  </td>

							<td style="width:45px;padding-left:35px;">
								<font class="requiredFont"> * </font>
								<s:label for="emailField" id="emailLabel"  class="label" value="%{getText('email')}" />					
							</td>
							<td><s:textfield id="emailField" name="email" size="30" onBlur="validateEmail()"/>  </td>
						</tr>
					</table>
					
					<table class="freeUserRegDetailsTable">
						<tr><td></td>
							<td>
								<div id="mobileDiv">
								</div>
							</td>
						</tr>
					</table>
						
					<table class="freeUserRegDetailsTable">
						<tr class="row">
							<td>
								<s:label for="addressField" id="addressLabel"  class="label" value="%{getText('address')}" />				
							</td>
							<td><s:textfield id="addressField" name="address"/>  </td>				
						</tr>

						<tr class="row">
							<td>
								<font class="requiredFont"> * </font>
								<s:label for="stateSelectBox" id="stateLabel"  class="label" value="State" />			
							</td>
							<td>	<s:select name="state" id="stateSelectBox" cssClass="regionsSelectBox" headerKey="0" headerValue="Select State" list="#session.states" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtSelectBox')" cssStyle="width:145px;" /></td>				
						</tr>

						<tr class="row">
							<td>
								<font class="requiredFont"> * </font>
								<s:label for="districtSelectBox" id="districtLabel" class="label" value="District" />			
							</td>
							<td><s:select name="district" id="districtSelectBox" cssClass="regionsSelectBox" headerKey="0" headerValue="Select District" list="#session.districts" listKey="id" listValue="name" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,'constituencySelectBox')" cssStyle="width:145px;" /></td>				
						</tr>

						<tr class="row">
							<td>
								<font class="requiredFont"> * </font>
								<s:label for="constituencySelectBox" id="constituencytLabel" class="label" value="Constituency" />		
							</td>
							<td><s:select name="constituency" id="constituencySelectBox" cssClass="regionsSelectBox" headerKey="0" headerValue="Select Constituency" list="#session.constituencies" listKey="id" listValue="name" cssStyle="width:145px;" />	</td>				
						</tr>
					</table>
			</div>
		 </FIELDSET>
		<div style="margin:5px;margin-left:20px">
			 <s:checkbox name="accept"/>
			<b>I accept <a target="_blank" href="footerLinksAction.action?linkFrom=termsOfUse#termsOfUse"> Terms of use </a> &nbsp; and <a target="_blank" href="footerLinksAction.action?linkFrom=privacy#privacyPolicy">Privacy policy</a> of the Website</b>
		</div>
		<FIELDSET id="personalFieldsCollection" class="fieldsCollection">
		<div class="dotline"></div>
		<LEGEND>Do You Want To</LEGEND>
		 <div id="contactDetailsDiv" class="accessDivMain">
					
			<s:checkboxlist list="#session.profileOpts" labelposition="top" theme="vertical-checkbox" listKey="id" listValue="name" name="profileOpts"/>
			<div style="margin-left:170px;">
				<s:submit name="Save" value="Create My Account"></s:submit>
			</div>
					
		 </div>
		  <div><font class="requiredFont"> * </font><b> Fields are mandatory</b></div>
		 </FIELDSET>

</div>
         <!--  Problem Management Params -->
         <input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
		 <input type="hidden" name="task" value="<%=task %>" />
		 <input type="hidden" name="name" value="<%=name %>" />
		 <input type="hidden" name="registrationId" value="${registrationId }" />
		 <input type="hidden" name="stateId" value="<%=stateId %>" />
		 <input type="hidden" name="districtId" value="<%=districtId %>" />
		 <input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
		 <input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
		 <input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />
</s:form>  
<script language="javascript">
document.getElementsByName("gender")[0].checked = true;
document.getElementsByName("accept")[0].checked = true;
</script>
</body>  
</html>