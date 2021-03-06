<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> TDP News Portal </title>
	<meta name="" content="">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<!-- <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'> -->
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/googleAPIStyles.css"/>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<style>
.errorClass{
 color:red;font-weight:normal;
 font-family: verdana,sans-serif;
 margin-left:150px;
 
}
.accesslvl{
 margin-left: 181px;
 margin-bottom:15px;
}
#stateDIV,#districtDIV,#parliamentDIV,#assemblyDIV{
  display:none;
}
.ui-multiselect{
    height: 30px;
    width: 220px;
  }
</style>
<script>

</script>
</head>
<body>
		
		<!----Container---->
		<div class="container">
			<div class="row m_top10">
			

<c:if test="${savedSuccessfully == true}">
<div id="" style="color:green;text-align:center;"> Registered Successfully</div>
</c:if>
 <s:form action="userRegistration" id="registrationForm" method="POST" theme="simple" enctype="multipart/form-data" 
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

<span id="usrTypeId">
  </span>
<div class="controls">
 <b style="color:red;font-size:20px">*</b>  &nbsp;&nbsp;<s:select name="userType" id="userTypeId" list="userTypeList"  onChange="validateUserType();getAllEntitleMents();" theme="simple" listKey="id" listValue="name"/>
</div>

</div>
<div class="control-group">

<span id="usrRolesId">
  </span>
<div class="controls">
   &nbsp;&nbsp;&nbsp;&nbsp;<select id="usrRolesSelectId"></select>
 <input type="hidden" id="userRoleTypeId" name="userRoleType" />
</div>

</div>
<div class="control-group" id="usrDeptRolesMainId" style="display:none;">

<span id="usrDeptRolesId">
  </span>
<div class="controls">
   <b style="color:red;font-size:20px">*</b>  &nbsp;&nbsp;<s:select name="usrDeptId" id="usrDeptSelectId" list="departmentList" onChange="removeErrorMsg('usrDeptRolesId');" theme="simple" listKey="id" listValue="name"/>
</div>

</div>
<div id="subuserAddressDiv" style="display:none;">
 <div class="accesslvl">
    <div id="accesslvlerr" style=" margin-left:-24px;"></div>
	<b style="color:red;font-size:20px">*&nbsp;</b>
	<select id="selectLocLvl" name="accesslevel" onchange="showLoc();">
	  <option value="0">Select Access Level</option>
	  <option value="1">State</option>
	  <option value="2">District</option>
	  <option value="3">Parliament Constituency</option>
	  <option value="4">Assembly Constituency</option>
	  
	</select>
 </div>


<span id="locationErr">
  </span>
<div id="stateDIV" class="controls" style="margin-bottom:15px;">
 <b style="color:red;font-size:20px;">*</b> &nbsp; <s:select name="stateId"  list="statesList"   theme="simple" listKey="id" listValue="name"/>
</div>
<div id="districtDIV" class="controls" style="margin-bottom:15px;">
 <b style="color:red;font-size:20px;">*</b> &nbsp; <s:select name="districtId"  list="districtsList"   theme="simple" listKey="id" listValue="name"/>
</div> 
<div id="parliamentDIV" class="controls" style="margin-bottom:15px;">
 <b style="color:red;font-size:20px;">*</b> &nbsp;<s:select name="parliamentId"  list="parliamantConstis.constituencies"   theme="simple" listKey="id" listValue="name"/>
</div> 
<div id="assemblyDIV" class="controls" style="margin-bottom:15px;">
 <b style="color:red;font-size:20px;">*</b> &nbsp; <s:select name="assemblyId"  list="constituencyInfoVO.constituencies"   theme="simple" listKey="id" listValue="name"/>
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
<script>

var spvar = 'special';
var r={
  'special':/[\W]/g
}
 var isSaved = '${savedSuccessfully}';
var emailflag = true;
</script>	
<script>
function showLoc(){
  var id = $("#selectLocLvl").val();
  if(id == 0)
  {
    showHide(false,false,false,false);
  }else if(id == 1){
	showHide(true,false,false,false);
  } else if(id == 2){
    showHide(false,true,false,false);
  }else if(id == 3){
    showHide(false,false,true,false);
  }else if(id == 4){
    showHide(false,false,false,true);
  }
}
function showHide(state,dist,parl,assem){
	if(state){ 
		  $("#stateDIV").show();
		}else{
		  $("#stateDIV").hide();
		}
    if(dist){ 
	  $("#districtDIV").show();
	}else{
	  $("#districtDIV").hide();
	}
	if(parl){ 
	  $("#parliamentDIV").show();
	}else{
	  $("#parliamentDIV").hide();
	}
	if(assem){ 
	  $("#assemblyDIV").show();
	}else{
	  $("#assemblyDIV").hide();
	}  
}
function getAllEntitleMents(){
	var userType = $("#userTypeId").val();
	 $("#usrRolesSelectId option").remove();
	if(userType == 0){
	    $('#usrRolesSelectId').multiselect({	
			multiple: true,
			selectedList: 0,
			noneSelectedText:"Select Entitlements",
			hide: "explode"	
		}).multiselectfilter({ 
			 header:"Select Entitlements"	
		});
	   $(".ui-multiselect").each(function(){
		 $(this).attr("style","width: 220px");	
	   });
	   
	   return;
	}
	 
	 var jsObj=
	 {
		roleId:userType
	 };
	 
	 $.ajax({
		type: "POST",
		url: "getAllEntitlements.action",
		data: {task : JSON.stringify(jsObj)}
		})
		.done(function( result ) {
		$.each(result,function(index,value){
			$('#usrRolesSelectId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		 $('#usrRolesSelectId').multiselect({	
			multiple: true,
			selectedList: 0,
			noneSelectedText:"Select Entitlements",
			hide: "explode"	
		 }).multiselectfilter({ 
			 header:"Select Entitlements"	
		 });
	     $(".ui-multiselect").each(function(){
		   $(this).attr("style","width: 220px");	
	     });
     });
}
function getConstituenciesForDistrict(){
	var assemblyRadioValue = document.getElementById("assemblyRadio");
	var parliamentRadioValue = document.getElementById("parliamentRadio");

	var electiontypeId = 0;
	if(assemblyRadioValue.checked == true ){
		electiontypeId= assemblyRadioValue.value;
	}
	else if(parliamentRadioValue.checked == true ){
		electiontypeId= parliamentRadioValue.value;
	}
	var jsObj = {
		electionTypeId : electiontypeId,
		stateId : 1,
		task:"getCosntituencesByElectionType"	
	}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesByElectionType.action?"+rparam;		
	callAJAX(jsObj,url);
}


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
					else if(jsObj.task == "getCosntituencesByElectionType"){
						buildConstituenciesList(results);
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

function buildConstituenciesList(result){

		var elmt = document.getElementById("constituencyList");
		clearOptionsListForSelectElmtId("constituencyList")
		
		
		for(var i in result.constituencies)
		{
			
			var option=document.createElement('option');
				option.value=result.constituencies[i].id;
			
			option.text=result.constituencies[i].name;
			try{
				elmt.add(option,null);	
			}catch (ex){
				elmt.add(option);
			}
		}
	
}
	
function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
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
	if(fstEle.value.trim().length <4){
		fstEleErr.innerHTML = "<span class='errorClass'>&nbsp;First Name must contain minimum 4 Charactors.</span>";
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

function validateLocation(){

var textEle = document.getElementById("constituencyList");
var resultDIVEle = document.getElementById("locationErr");
	resultDIVEle.innerHTML = "";
	if(textEle.value == 0 )
	{		
		resultDIVEle.innerHTML = "<span class='errorClass'> Select Constituency </span>";
		return;
	}
	else
		resultDIVEle.innerHTML = "";

}
function isPwdSpclChar(){

   var pwd = document.getElementById("passwordField").value;
	if(pwd == "")
	 {
	 $("#pwdId").html("Please Enter Password").addClass("errorClass");
	 return false;
	 }
	 if(pwd.trim().length <6 ){
		$("#pwdId").html("Password must contain minimum 6 Charactors.").addClass("errorClass");
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
	if(lstEle.value.trim().length <4){
		lstEleErr.innerHTML = "<span class='errorClass'>&nbsp;Last Name must contain minimum 4 Charactors.</span>";
		return false;
	 }
	if(lstEle.value.match(r[spvar])){
		lstEleErr.innerHTML = "<span class='errorClass'>Should Not Contain Special Characters</span>";
		 return false;
	}
	
	else 
             return true;
}

function validateUserType()
{	
	$("#usrDeptRolesMainId").hide();
	$("#usrDeptRolesId").html();
	var usrEle = document.getElementById("userTypeId");
	var usrEleErr=document.getElementById("usrTypeId");
	usrEleErr.innerHTML = "";
	$('#subuserAddressDiv').css("display","none");
	$("#districtDIV").hide();
	$("#parliamentDIV").hide();
	$("#assemblyDIV").hide();
  
	$('#selectLocLvl').find('option').remove();
	$('#selectLocLvl').append('<option value="0"> Select Access Level </option>');
	$('#selectLocLvl').append('<option value="1"> State </option>');
	$('#selectLocLvl').append('<option value="2"> District </option>');
	$('#selectLocLvl').append('<option value="3"> Parliament Constituency </option>');
	$('#selectLocLvl').append('<option value="4"> Assembly Constituency </option>');
    if(usrEle.value== 0){
		usrEleErr.innerHTML = "<span class='errorClass'>&nbsp;Please Select User Type.</span>";
	    return false;
	}
	else if(usrEle.value== 2 || usrEle.value == 4){
		$('#subuserAddressDiv').css("display","block");
		if(usrEle.value == 4){
			$('#selectLocLvl').find('option').remove();
			$('#selectLocLvl').append('<option value="0"> Select Access Level </option>');
			$('#selectLocLvl').append('<option value="3"> Parliament Constituency </option>');
		}
	}else if(usrEle.value== 6 ){
		$("#usrDeptRolesMainId").show();
	}	
	else
	 return true;
}

function validatefields()
{
	$("#usrDeptRolesId").html();
	var selEnts = "";
	selectedEntsvalues = $("#usrRolesSelectId").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
		
	for(var i in selectedEntsvalues)
	{
	  selEnts = selEnts+""+selectedEntsvalues[i]+",";
	}
	if(selEnts.length > 0){
	   selEnts = selEnts.substring(0, selEnts.length - 1);
	}
	$("#userRoleTypeId").val(selEnts);
	var flag = true;
	
	
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
	
	if(!validateFirstName())
	{
		flag = false;
	}
	if(!validateLastName())
	{
		flag = false;
	}
	if($("#userTypeId").val() == 2){
	  if($("#selectLocLvl").val() == 0){
	    $("#accesslvlerr").html("Please Select Access Level ").css("color","red");
		flag = false;
      }else{
         $("#accesslvlerr").html("");
      }	  
	}
	if($("#userTypeId").val() == 6){
		  if($("#usrDeptSelectId").val() == 0){
		    $("#usrDeptRolesId").html("Please Select Department ").css("color","red").css("margin-left","147px");
			flag = false;
	      }else{
	         $("#usrDeptRolesId").html("");
	      }	  
		}
	return flag;
}
function removeErrorMsg(id){
	$("#"+id).html("");
	
}
$(document).ready(function(){
  $('#usrRolesSelectId').multiselect({	
			multiple: true,
			selectedList: 0,
			noneSelectedText:"Select Entitlements",
			hide: "explode"	
	}).multiselectfilter({ 
         header:"Select Entitlements"	
	});
   $(".ui-multiselect").each(function(){
     $(this).attr("style","width: 220px");	
   });
});

</script>
</body>

</html>