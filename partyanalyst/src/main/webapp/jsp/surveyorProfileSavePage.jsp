 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Profilr Saving</title>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery_validation_1.7.js"></script>
<script type="text/javascript" src="js/surveyAnalysis/surveyAnalysis.js"></script>

<style>
	#myModal 
	{
		width: 700px; 
		margin-right: auto;
		margin-left:  auto;
	} 

	#myModal .modal-body {
		//max-height: 500px;
	}
</style>
<script>
function validateMethod(){
var nameField = document.getElementById("name");
var stateIdField = document.getElementById("stateId");
var districtIdField = document.getElementById("districtId");
var constituencyListField = document.getElementById("constituencyList");
var tehsilIdField = document.getElementById("tehsilId");
var villageIdField = document.getElementById("villageId");
  if(nameField.value == ''){
		$("#errorDiv").html("<span class='errorClass'>&nbsp;Please Enter  Name.</span>");
	    return false;
	}
	if(stateIdField.value== '0'){
		$("#errorDiv").html("<span class='errorClass'>&nbsp;Please Select State.</span>");
		return false;
	}
	if(districtIdField.value== '0'){
		$("#errorDiv").html("<span class='errorClass'>&nbsp;Please Select District.</span>");
		return false;
	}
	if(constituencyListField.value== '0'){
		$("#errorDiv").html("<span class='errorClass'>&nbsp;Please Select Constituency.</span>");
		return false;
	}
	if(tehsilIdField.value== '0'){
		$("#errorDiv").html("<span class='errorClass'>&nbsp;Please Select Mandal.</span>");
		return false;
	}
	if(villageIdField.value== '0'){
		$("#errorDiv").html("<span class='errorClass'>&nbsp;Please Select Villiage.</span>");
		return false;
	}
		
}
</script>
</head>
<body>
<div>

 <div id="myModal">
		<div id="errorDiv" style="color: red; margin-top: 10px; font-size: 13px;font:bold;">
		<c:if test="${not empty resultStatus}">
		<div id="successMsg" style="color: Green; font-size: 12px; font-weight: bold; margin-top: 20px;">Surveyor Profile Saved Successfully!</div>
		</c:if>
		</div>

		<div>
		<p style="font-size:16px;font-weight:bold;"></p>
		<form class="form-horizontal"
		id="personalInfoForm" action="saveSurveyorInfoAction.action"
		method="post" onsubmit="return validateMethod()">
				<legend>Personal Information</legend>

				<input type="hidden" id="surveyorId" name="surveyorId" value="${surveyorId}"/>
				<input type="hidden" id="userAddressId" name="userAddressId" value="${userAddressId}"/>
				<input type="hidden" id="surveyorProfileId" name="surveyorProfileId" value="${surveyorProfileId}"/>
				<input type="hidden" id="updationDetailsId" name="updationDetailsId" value="${updationDetailsId}"/>

				<div class="control-group">
					<label class="control-label" for="name">Name<b style="color:red"> *</b></label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.name != null}">
					<input type="text" id="name" placeholder="Your Name" name='surveyorPersonalInfoVO.name' value="${surveyorPersonalInfoVO.name}">
					</c:if>
					<c:if test="${surveyorPersonalInfoVO.name == null}">
					<input type="text" id="name" placeholder="Your Name" name='surveyorPersonalInfoVO.name'>
					</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="contactnumber">Contact Number</label>
					<div class="controls">
						<c:if test="${surveyorPersonalInfoVO.mobileNumber != null}">
						<input type="text" id="mobileNumber" placeholder="Mobile Number" name='surveyorPersonalInfoVO.mobileNumber' value="${surveyorPersonalInfoVO.mobileNumber}">
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.mobileNumber == null}">
						<input type="text" id="mobileNumber" placeholder="Mobile Number" name='surveyorPersonalInfoVO.mobileNumber'>
						</c:if>
						<!--<label class="control-label" for="phoneno">Phone Number</label>-->
						<c:if test="${surveyorPersonalInfoVO.phoneNumber != null}">
						<input type="text" id="phoneNumber" placeholder="Phone Number" name='surveyorPersonalInfoVO.phoneNumber' value="${surveyorPersonalInfoVO.phoneNumber}">
						</c:if>

						<c:if test="${surveyorPersonalInfoVO.phoneNumber == null}">
						<input type="text" id="phoneNumber" placeholder="Phone Number" name='surveyorPersonalInfoVO.phoneNumber'>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="age" >Age</label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.age != null}">
						<input type="text" id="age" placeholder="Age" name='surveyorPersonalInfoVO.age' value="${surveyorPersonalInfoVO.age}">
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.age == null}">
						<input type="text" id="age" placeholder="Age" name='surveyorPersonalInfoVO.age'>
						</c:if>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="emailid">Email-Id</label>
					<div class="controls">
						<c:if test="${surveyorPersonalInfoVO.email != null}">
						<input type="text" id="emailId" placeholder="Email-Id" name='surveyorPersonalInfoVO.email' value="${surveyorPersonalInfoVO.email}">
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.email == null}">
						<input type="text" id="emailId" placeholder="Email-Id" name='surveyorPersonalInfoVO.email'>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="qualification">Qualification</label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.qualification != null}">
					<s:select theme="simple" list="eduStatus" name="surveyorPersonalInfoVO.qualification" listKey="id" listValue="name" value="qualification"/> 
					</c:if>
					<c:if test="${surveyorPersonalInfoVO.qualification == null}">
					<s:select theme="simple" list="eduStatus" name="surveyorPersonalInfoVO.qualification" listKey="id" listValue="name" /> 
					</c:if>
				</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="occupation">Occupation</label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.occupation != null}">
					<s:select theme="simple" list="occupationsList" name="surveyorPersonalInfoVO.occupation" listKey="id" listValue="name" value="occupation"/> 
					</c:if>
					<c:if test="${surveyorPersonalInfoVO.occupation == null}">
					<s:select theme="simple" list="occupationsList" name="surveyorPersonalInfoVO.occupation" listKey="id" listValue="name" /> 
					</c:if>
				</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="caste">Caste</label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.caste != null}">
					<s:select theme="simple" list="casteList" name="surveyorPersonalInfoVO.caste" listKey="id" listValue="name" value="caste"/>
					</c:if>
					<c:if test="${surveyorPersonalInfoVO.caste == null}">
					<s:select theme="simple" list="casteList" name="surveyorPersonalInfoVO.caste" listKey="id" listValue="name" />
					</c:if>
				</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gender">Gender</label>
					<div class="controls">
						<c:if test="${surveyorPersonalInfoVO.gender != null}">
						<select class='select' id='genderId' name='surveyorPersonalInfoVO.gender' value="${surveyorPersonalInfoVO.gender}">
							<option value="0">Select</option>
							<option value="1">Male</option>
							<option value="2">Female</option>
						</select>
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.gender == null}">
						<select class='select' id='genderId' name='surveyorPersonalInfoVO.gender'>
							<option value="0">Select</option>
							<option value="1">Male</option>
							<option value="2">Female</option>
						</select>	
						</c:if>
					</div>
				</div>
				
				<legend>Address</legend>
				<div class="control-group">
				<label class="control-label" for="gender">address</label>
				<div class="controls">
				<c:if test="${surveyorPersonalInfoVO.address != null}">
					<textarea id="newsCateDesc"  name="surveyorPersonalInfoVO.address" style="background-color: rgb(255, 255, 255); width: 215px;" rows="3" cols="27">${surveyorPersonalInfoVO.address}</textarea> 
					</c:if>
					<c:if test="${surveyorPersonalInfoVO.address == null}">
					<textarea id="newsCateDesc" name="surveyorPersonalInfoVO.address" style="background-color: rgb(255, 255, 255); width: 215px;" rows="3" cols="27"></textarea> 
					</c:if>
				</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="state">State<b style="color:red"> *</b></label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.state != null}">
						<s:select id="stateId" theme="simple" list="stateList" value="state" name="surveyorPersonalInfoVO.state" listKey="id" listValue="name" onChange="getDistricts();" /> 
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.state == null}">
						<s:select id="stateId" theme="simple" list="stateList" name="surveyorPersonalInfoVO.state" listKey="id" listValue="name" onChange="getDistricts();" /> 
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="district">District<b style="color:red"> *</b></label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.district != null}">
						<s:select theme="simple" value="district"
						list="districtList" id="districtId" name="surveyorPersonalInfoVO.district" listKey="id" listValue="name" onChange="getConstituencys();"/> 
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.district == null}">
						<s:select theme="simple"
						list="districtList" id="districtId" name="surveyorPersonalInfoVO.district" listKey="id" listValue="name" onChange="getConstituencys();"/> 
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="constituency">Constituency<b style="color:red"> *</b></label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.constituency != null}">
						<s:select theme="simple" value="constituency"
						list="constituencyList" id="constituencyList" name="surveyorPersonalInfoVO.constituency" listKey="id" listValue="name" onChange="getMandals();"/>
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.constituency == null}">
						<s:select theme="simple"
						list="constituencyList" id="constituencyList" name="surveyorPersonalInfoVO.constituency" listKey="id" listValue="name" onChange="getMandals();"/>
						</c:if>
					</div>
				</div>
					<div class="control-group">
					<label class="control-label" for="tehsil">Tehsil<b style="color:red"> *</b></label>
					<div class="controls">
					<c:if test="${surveyorPersonalInfoVO.tehsil != null}">
					<s:select theme="simple" id='tehsilId' list="tehsilList" listKey="id" listValue="name" value="tehsil" name='surveyorPersonalInfoVO.tehsil' onChange="getVillages(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);"/>
					
					</c:if>
					<c:if test="${surveyorPersonalInfoVO.tehsil == null}">
						<select class='select' id='tehsilId' name='surveyorPersonalInfoVO.tehsil' onChange="getVillages(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
							<option value="0">Select Tehsil</option>
						</select>
					</c:if>
					</div>
					</div>
					<div class="control-group">
					<label class="control-label" for="village">Village<b style="color:red"> *</b></label>
					<div class="controls">
						<c:if test="${surveyorPersonalInfoVO.village != null}">
						<s:select theme="simple" id='villageId' list="hamletList" listKey="id" listValue="name" value="village"  name='surveyorPersonalInfoVO.village'/>
						</c:if>
						<c:if test="${surveyorPersonalInfoVO.village == null}">
						<select class='select' id='villageId' name='surveyorPersonalInfoVO.village'>
							<option value="0">Select Villiage</option>
						</select>
						</c:if>
					</div>
					</div>
					<div style="float: right; width: 330px;">
						<input class="btn btn-primary" type="submit" value="Save changes" onSubmit="personalInfoForm.submit()" >
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>