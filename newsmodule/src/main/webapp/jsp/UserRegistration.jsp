<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Telugudesham Party</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
<style>
.errorClass{
 color:red;font-weight:normal;
 font-family: verdana,sans-serif;
 margin-left:150px;
 
}

</style>
</head>
<body>
		
		<!----Container---->
		<div class="container">
			<div class="row m_top10">
			

<c:if test="${savedSuccessfully == true}">
<div id="" style="color:green;text-align:center;"> Registered Successfully</div>
</c:if>
 <s:form action="userRegistration.action?" id="registrationForm" method="POST" theme="simple" enctype="multipart/form-data" 
onsubmit="return validatefields()" cssClass="form-horizontal text-center">
<div class="control-group">
<div class="controls">
<strong style="color:red;margin">REGISTER</strong>
<span style="padding-left:10px"> Fields marked with (
<b style="color:red;font-size:20px">*</b>
) are mandatory</span>
</div></div>
     <div class="control-group">
	
<span id="mobileDiv" ></span>
<div class="controls">
 <b style="color:red;font-size:20px">*</b>  &nbsp;&nbsp;<s:textfield theme="simple" name="email"  maxlength = "40" id="emailField" value="Username (Email)" size="30" onClick="removeTextInTextBoxes(this.id)" onBlur="validateEmail(),checkAvailability(),showTextInTextBoxes(this.id)"/>
</div>

</div>

   <div class="control-group">
	

<span id="pwdId">
  </span>
<div class="controls">
 <b style="color:red;font-size:20px">*</b>  &nbsp;&nbsp;<s:password theme="simple" name="password"  maxlength = "40" id="passwordField" placeholder="Password" size="30" onClick="removeTextInTextBoxes(this.id)" onBlur="isPwdSpclChar(),showTextInTextBoxes(this.id)"/>
</div>

</div>

 <div class="control-group">
<span id="fstNameId">
  </span>

<div class="controls">
 <b style="color:red;font-size:20px">*</b>  &nbsp;&nbsp;<s:textfield name="firstName" value="First Name" id="firstNameId" maxlength = "20" size="30" onClick="removeTextInTextBoxes(this.id)" theme="simple" onBlur="showTextInTextBoxes(this.id),validateFirstName()"/>
</div>

</div>
<div class="control-group">

<span id="lstNameId">
  </span>
<div class="controls">
 <b style="color:red;font-size:20px">*</b>  &nbsp;&nbsp;<s:textfield name="lastName" value="Last Name" id="lastNameId"  maxlength = "20"  size="30" onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id),validateLastName()" theme="simple"/>
</div>

</div>
 <div class="control-group">
<div class="controls">

<button type="submit" class="btn btn-success">Register</button>
</div>
</div>
</s:form>

</div>
</div>

<!------JS------>
	
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
<script>

var spvar = 'special';
var r={
  'special':/[\W]/g
}
 var isSaved = '${savedSuccessfully}';
var emailflag = true;
</script>	
<script>

function validateEmail()
{   
	var email = document.getElementById("emailField").value;
	var resultDIVEle = document.getElementById("mobileDiv");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if(email == 'Username (Email)')
	{
		resultDIVEle.innerHTML = "<span class='errorClass'>Please Enter Email Address.</span>";
		 return false;
	}
	if(reg.test(email) == false)
		resultDIVEle.innerHTML = "<span class='errorClass'>Please Provide Correct Email Address.</span>";
    else 
             return true;

	if(email.length == 0){
		resultDIVEle.innerHTML = "<span class='errorClass'>UserName Should Not Be Empty.</span>";
        return false;
	}
	
	
}
function checkAvailability()
{ 
	var name = document.getElementById("emailField").value;
 	var resultDIVEle = document.getElementById("mobileDiv");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(name.length == 0)
		resultDIVEle.innerHTML="<span class='errorClass'>UserName Sholud Not Be Empty.</span>";
	
	else if(reg.test(name) == false)
		resultDIVEle.innerHTML = "<span class='errorClass'>Please Provide Valid Email Address.</span>";
		
	else {		

 		document.getElementById("mobileDiv").innerHTML = " ";
 		var jsObj=
		{		
 				userName:name,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);
	}

}

function callAJAX(jsObj,url){
	
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
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

function showDetails(results)
{  
	var textEle = document.getElementById("emailField");
	var result = document.getElementById("mobileDiv");
	var str='';
	if(results==121){		
		str+="<span style='color:green;font-weight:normal;'> Username is available.</span>";
		emailflag = true;
		result.innerHTML = str;
		return true;
	}else if(textEle.value == 'Username (Email)'){
		str+="<span class='errorClass'>Please Enter Username.</span>";	
        result.innerHTML = str;
		return false;
		}
	else
	{
		str+="<span class='errorClass'> Username is not available.</span>";	
	    result.innerHTML = str;
		emailflag = false;
		return false;
	}
	result.innerHTML = str;
}
function removeTextInTextBoxes(id)
{

  var ids=document.getElementById(id);
   if(ids.value  =='Username (Email)' || 
		ids.value =='First Name' ||
		ids.value =='Last Name' || ids.value=='Password') 
		{
       ids.value='';
	return;
  }
}
function showTextInTextBoxes(id){

   if($("#emailField").val() ==''){
		$("#emailField").val('Username (Email)');
	  } 
	  if($("#firstNameId").val() =='' ){
		 $("#firstNameId").val('First Name');
	  }
	 
	 if($("#lastNameId").val() ==''){
         $("#lastNameId").val('Last Name');
	 }
	  
}
function validateFirstName()
{ 

	var fstEle = document.getElementById("firstNameId");
	var fstEleErr=document.getElementById("fstNameId");
	fstEleErr.innerHTML = "";
	
    if(fstEle.value== 'First Name'){
		fstEleErr.innerHTML = "<span class='errorClass'>&nbsp;Please Enter First Name.</span>";
	    return false;
	}
	if(fstEle.value.match(r[spvar])){
		fstEleErr.innerHTML = "<span class='errorClass'>Should Not Contain Special Characters</span>";
	    return false;
	}
	else 
             return true;
 }
 function validUsername()
{
	var textEle = document.getElementById("userNameField");
	var resultDIVEle = document.getElementById("resultDIV");

	resultDIVEle.innerHTML = "";
	if(textEle.value.length > 0 && textEle.value.length < 6)
	{
		resultDIVEle.innerHTML = "<span class='errorClass'>UserName must be between 6 and 20 characters long.</span>";
		return;
	}
		
	if(textEle.value.match(r[spvar]))
		resultDIVEle.innerHTML = "<span class='errorClass'>UserName Should not contain Special Characters</span>";
	else
		resultDIVEle.innerHTML = "";
		
}
function isPwdSpclChar(){

   var pwd = document.getElementById("passwordField").value;
	if(pwd == "")
	 {
	 $("#pwdId").html("Please Enter PassWord").addClass("errorClass");
	 return false;
	 }
	if (pwd.match(/[^a-zA-Z0-9 ]/g)) {
	$("#pwdId").html("Password should not contain special characters").addClass("errorClass");
	return false;
	}else{
	$("#pwdId").html("");
	return true;
	}
}
function validateLastName()
{
	var lstEle = document.getElementById("lastNameId");
	var lstEleErr=document.getElementById("lstNameId");
	lstEleErr.innerHTML = "";
    if(lstEle.value== 'Last Name'){
		lstEleErr.innerHTML = "<span class='errorClass'>&nbsp;Please Enter Last Name.</span>";
	    return false;
	}
	if(lstEle.value.match(r[spvar])){
		lstEleErr.innerHTML = "<span class='errorClass'>Should Not Contain Special Characters</span>";
		 return false;
	}
	
	else 
             return true;
}

function validatefields()
{
	
	var flag = true;
	
	if(!validateLastName())
	{
		flag = false;
	}
	if(!validateFirstName())
	{
		flag = false;
	}
	if(!validateEmail())
	{
		flag = false;
	}
	if(!isPwdSpclChar())
	{
	flag = false;
	}
	if(!emailflag)
	{
		$("#mobileDiv").html("Username is not available").css("color","red");	
	    
		flag = false;
	}
	
	return flag;
}


</script>
</body>

</html>