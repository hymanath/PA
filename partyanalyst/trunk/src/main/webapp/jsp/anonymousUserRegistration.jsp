<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<style type="text/css">

	.calBtn
	{
		background-image: url("images/icons/constituencyManagement/calendar.jpeg");
		height: 24px;
		width: 24px;	
	}

</style>
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
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
function checkAvailability()
{ 
	var name = document.getElementById("userNameField").value;
 	
		if(name==""){
		document.getElementById("errorMessageDiv").innerHTML = "UserName field cannot be empty";
 	 }
	 else if (name.charAt(0).indexOf(" ")==0){
		 document.getElementById("errorMessageDiv").innerHTML = "UserName Should not contain spaces";
 	 }
     else{ 	 	
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
 
var uploadPicStatus = false;
function uploadImageFile()
{
    var photoElmt = document.getElementById("uploadFileId");
	var photoStatusElmt = document.getElementById("uploadPic_window_status");
	var fileLimit = 1048576; //1024*1024 = 1048576 bytes (2MB photo limit)
	
	var file = photoElmt.files[0];
	
	var fileType = file.type;
	var fileImgType = fileType.substring(fileType.indexOf('/')+1,fileType.length);
	

	if(fileImgType == "jpeg" || fileImgType == "png" || fileImgType == "gif")
	{
		var fileSize = file.fileSize/fileLimit;
		if(fileSize > 2)
		{
			photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image size should be < 2MB.</span>';
		}
		else
		{
			photoStatusElmt.innerHTML = '';
			var previewElmt = document.getElementById("Imgpreview");
			previewElmt.src = file.getAsDataURL();
			uploadPicStatus = true;
		}
	}
	else
	{
		photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image is not of the type specified.</span>';
	}
 }

 
	
</script>
<style type="text/css">
   
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
		text-align: right;
		width: 13.8em;
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
	
</style>
</head>  
<body>  
<s:form action="anonymousUserRegistrationAction.action" method="POST" theme="simple" enctype="multipart/form-data">  
   <br><br>
  <div id="headerDiv" class="headerDiv"> 
   <c:if test="${empty registrationId}">
   	<p>Get a free registration to connect to people and info that you care about</p>
   </c:if>
   <c:if test="${! empty registrationId}">
   	User Profile
   </c:if> 
  </div>
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
		 <c:if test="${empty registrationId}">
	        <FIELDSET id="personalFieldsCollection" class="fieldsCollection">

			    <div class="dotline"></div>
				<LEGEND> Select an ID and password</LEGEND>
				<div id="resultDIV"></div>
				 <div id="loginDetailsDiv" class="accessDivMain">
					<div id="loginDetailsDivBody" class="accessDivBody">
						
							    <div id="fulNameFieldDiv" class="row">
								<s:label for="userNameField" id="userNameLabel" class="label" value="%{getText('userName')}" /><font class="requiredFont"> * </font> 								
								<s:textfield id="userNameField" name="userName"/>  
								<input type="button" name="checkUserNameAvailability" value="Check Availability" onclick="checkAvailability()"/>
								</div>
							    <div id="passwordFieldDiv" class="row">
								<s:label for="passwordField" id="passwordLabel"  class="label" value="%{getText('password')}" /><font class="requiredFont"> * </font>								   
								<s:password id="passwordField" name="password"/>
							    </div>
								<div id="cpasswordFieldDiv" class="row">
								<s:label for="passwordField" id="passwordLabel"  class="label" value="%{getText('reEnterPassword')}" /><font class="requiredFont"> * </font> 
								<s:password id="passwordField" name="reEnteredPassword"/>  
							    </div>
					</div>
				 </div>
	         </FIELDSET>
         </c:if>
		  <FIELDSET id="personalFieldsCollection" class="fieldsCollection">

          <div class="dotline"></div>
		  <LEGEND>Provide Personal Information</LEGEND>
		     <div id="personalDetailsDiv" class="accessDivMain">
			 <div id="personalDetailsDivBody" class="accessDivBody">
				
				        
						<div id="firstNameFieldDiv" class="row">
				        <s:label for="firstNameField" id="fnameLabel"  class="label" value="%{getText('firstName')}" />
						<font class="requiredFont"> * </font>
						<s:textfield id="nameField" name="firstName"/> 
						</div>

						<DIV class="yui-skin-sam"><DIV id="DOB_div" style="position:absolute;margin-left:400px;"></DIV>
						</DIV>

					
					    <div id="userNameFieldDiv" class="row">
					    <s:label for="userNameField" id="userNameLabel" class="label" value="%{getText('lastName')}" />
						<font class="requiredFont"> * </font> 
						<s:textfield id="userNameField" name="lastName"/> 
						</div>

								
					    <div id="genderFieldDiv" style="margin-left:117px;">
						<s:label for="genderField" id="genderLabel" value="%{getText('gender')}" />
						<font class="requiredFont"> * </font> 
						<s:radio id="genderField" name="gender" list="#session.gender"/>
						</div>
						
					    <div id="doBirthFieldDiv" class="row">
						<!--<DIV class="yui-skin-sam"><DIV id="DOB_div" style="position:absolute;"></DIV>-->
						<s:label for="dateOfBirthField" id="dateOfBirthLabel" class="label"  value="%{getText('dateOfBirth')}" />
						<font class="requiredFont"> * </font>
						<s:textfield id="dateOfBirthField" readonly="true" name="dateOfBirth" size="25"/>
						<input id="calBtnEl" type="button" class="calBtn" title="Click To Select A Date" onclick="showDateCal('DOB_div','dateOfBirthField','1/1970')"/>
						</div>
												
														
						<div id="uploadImageFieldDiv" class="row">
					    <s:label for="uploadImageLabel" id="uploadImageLabel" class="label"  value="Upload Photo" />
						<font class="requiredFont"> &nbsp;&nbsp; </font>
						<s:file  id="uploadFileId" name="uploadImage" label="Upload" onchange="uploadImageFile()"/>
						</div>
						<div id="uploadPic_window_status"></div>
						
						<!--<div style="margin-left:400px;"><img id="Imgpreview" height="100" width="90" src="/PartyAnalyst/images/icons/indexPage/human.jpg"/></div>-->
				
					</div>
				</div>
				</FIELDSET>


				<FIELDSET id="personalFieldsCollection" class="fieldsCollection">
				<div class="dotline"></div>
				<LEGEND>Provide Contact Details</LEGEND>
				<div id="contactDetailsDiv" class="accessDivMain">

				        <div id="mobileFieldDiv" class="row">
					    <s:label for="mobileField" id="mobileLabel"  class="label" value="%{getText('mobile')}" />
						<font class="requiredFont"> &nbsp;&nbsp; </font>
						<s:textfield id="mobileField" name="mobile" maxlength="12"/>  
                        </div>
                        
						<div id="addressFieldDiv" class="row">
					    <s:label for="addressField" id="addressLabel"  class="label" value="%{getText('address')}" />
						<font class="requiredFont"> * </font> 
						<s:textfield id="addressField" name="address"/> 
						</div>

                        
						<div id="stateFieldDiv" class="row">
						<s:label for="stateSelectBox" id="stateLabel"  class="label" value="State" />
						<font class="requiredFont"> * </font> 
						<s:select name="state" id="stateSelectBox" cssClass="regionsSelectBox" headerKey="0" headerValue="Select State" list="#session.states" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtSelectBox')" cssStyle="width:145px;" />
						</div>
						
						<div id="districtFieldDiv" class="row">
						<s:label for="districtSelectBox" id="districtLabel" class="label" value="District" />
						<font class="requiredFont"> * </font> 
						<s:select name="district" id="districtSelectBox" cssClass="regionsSelectBox" headerKey="0" headerValue="Select District" list="#session.districts" listKey="id" listValue="name" onchange="getConstituenciesComboBoxForADistrict(this.options[this.selectedIndex].value,'constituencySelectBox')" cssStyle="width:145px;" />
						</div>
						
						<div id="constituencyFieldDiv" class="row">
						<s:label for="constituencySelectBox" id="constituencytLabel" class="label" value="Constituency" />
						<font class="requiredFont"> * </font> 
						<s:select name="constituency" id="constituencySelectBox" cssClass="regionsSelectBox" headerKey="0" headerValue="Select Constituency" list="#session.constituencies" listKey="id" listValue="name" cssStyle="width:145px;" />	
						</div>
						
						<div id="pinCodeFieldDiv" class="row">
						<s:label for="pinCodeField" id="pinCodeLabel"  class="label" value="%{getText('pincode')}" />
						<font class="requiredFont"> &nbsp;&nbsp; </font>
						<s:textfield id="pincodeField" name="pincode"/>  
						</div>

                        <div id="emailFieldDiv" class="row">
						<s:label for="emailField" id="emailLabel"  class="label" value="%{getText('email')}" />
						<font class="requiredFont"> * </font>
						<s:textfield id="emailField" name="email"/>  
						</div>
					    
						<div id="telephoneNoFieldDiv" class="row">
					    <s:label for="telephoneNoField" id="telephoneNoLabel"  class="label" value="%{getText('telephoneNo')}" />
						<font class="requiredFont"> &nbsp;&nbsp; </font>
						<s:textfield id="telephoneNoField" name="phone"/> 
						</div>
					
			 </div>
		 </FIELDSET>

		<FIELDSET id="personalFieldsCollection" class="fieldsCollection">
		<div class="dotline"></div>
		<LEGEND>Do You Want To</LEGEND>
		 <div id="contactDetailsDiv" class="accessDivMain">
					
					<s:checkboxlist list="#session.profileOpts" labelposition="top" theme="vertical-checkbox" listKey="id" listValue="name" name="profileOpts"/>
					<div style="margin-left:170px;">
					<s:submit name="Save" value="Create My Account"></s:submit>
					</div>
					
		 </div>
		  <div><font class="requiredFont"> * </font> Fields are mandatory</div>
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
document.getElementsByName("gender")[1].checked = true;
</script>
</body>  
</html>