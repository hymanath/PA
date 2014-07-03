<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
<html>
 <head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>


    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
	
		<style>
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.survey_nav{height:40px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:40px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 5px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			.requiredFont{
				color:red;
				font-size:13px;
			}
			
			
		</style>
  </head>
  
  <body>
<script>
  $(document).ready(function(){
   
    $('#constituencyId,#userId').change(function(){
		$('#boothsDtlsId').html('');
		getUserAssignedBoothsDetailsForAConstituency();
	});



});
  </script>
	<div class="container">
		<div class="row">
			<div class="span10 offset1 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a href="#" class="highlight selected" id="userTypeTab" onclick="showHideTabs(this.id);"> User Types </a></li>
					<li><a href="#" class="highlight" id="userCreationTab" onclick="showHideTabs(this.id);"> User Creation </a></li>
					<li><a href="#" class="highlight" id="tabAssignTab" onclick="showHideTabs(this.id);"> Tab Assign </a></li>
					<li><a href="#" class="highlight" id="boothAssignTab" onclick="showHideTabs(this.id);"> Booth Assign </a></li>
					<li><a href="#" class="highlight" id="deactivationTab" onclick="showHideTabs(this.id);"> Deactivation </a></li>
					<li><a href="#" class="highlight" id="assignLeaderTab" onclick="showHideTabs(this.id);"> Assign Leader</a></li>
					<li><a href="#" class="highlight" id="verificationDetailsTab" onclick="showHideTabs(this.id);"> Verification Details	 </a></li>
				</ul>
			</div>
		</div>
		<div class="row" id="createUserTypeDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red">
						<h4>Create user types</h4>
							<div class="row">
								<div id="createUserTypeErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span8 offset2">
									User Type <font class="requiredFont">*</font>
									<input type="text" id="userTypeName" class="input-block-level"></input>
								</div>
							</div>
							<div class="row">
								<div class="span8 offset2">
									Description<font class="requiredFont">*</font>
									<textarea id="userTypeDescription" class="input-block-level" rows="2"></textarea>
								</div>
							</div>
							<div class="row text-center m_top20"><button onClick="saveSurveyUserType();" type="button" class="btn btn-large btn-success">UPDATE</button></div>
					</div>
				</div>
			</div>
		</div>
	
		<!----User Creation----->		
		<div class="row" id="userCreationDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>User Creation</h4>
							<div class="row">
						<div id="createUserErrorDiv" class="span8 offset2 errorCls"></div>
						</div>
							<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<div class="span4">
											Firsr Name<font class="requiredFont">*</font>
											<input type="text" id="firstName" placeholder="Firsr Name..." class="input-block-level">
										</div>
										<div class="span4">
											Last Name<font class="requiredFont">*</font>
											<input type="text" id="lastName" placeholder="Last Name..." class="input-block-level">
										</div>
										<div class="span4">
											User Name<font class="requiredFont">*</font>
											<input type="text" id= "userName" placeholder="User Name..." class="input-block-level">
										</div>
										
									</div>
									<div class="row-fluid">
										<div class="span12">
											Address<font class="requiredFont">*</font>
											<textarea id="address" class="input-block-level" rows="2"></textarea>
										</div>										
									</div>
									<div class="row-fluid">
										<div class="span6">
											Password<font class="requiredFont">*</font>
											<input id="password" type="password" placeholder="Password..." class="input-block-level">
										</div>
										<div class="span6">
											Retype Password<font class="requiredFont">*</font>
											<input id="retypePassword" type="password" placeholder="Retype Password..." class="input-block-level">
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6">
											Mobile Number<font class="requiredFont">*</font>
											<input id="mobileNumber" type="text" placeholder="Enter Number..." class="input-block-level">
										</div>
										<div class="span6">
											User Type<font class="requiredFont">*</font>
											<select id = "userType" class="input-block-level"> 
											<option value="0">Select User type</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="saveSurveyUser();">SUBMIT</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!----TAB Assign ---->		
		<div class="row" id="tabAssignDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
					
						<h4>User Tab Assign</h4>
						<div class="row">
						<div id="assignTabErrorDiv" class="span8 offset2 errorCls"></div>
						</div>
							<div class="row">
								<div class="span10 offset2">
									<div class="row-fluid">
										<div class="span4">
											<!--<label>User Name</label>
											<input type="text" placeholder="User Name..." class="input-block-level" id="uname">-->
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="surveyUserTypeForSelect" onchange="getSurveyUsersByUserType('surveyUserIdForSelect',this.value);">
											<option value="0">Select User type</option>
											</select>
											</div>
											<div class="span4">
										Select User Name<font class="requiredFont">*</font>
										<select class="input-block-level" id="surveyUserIdForSelect"> </select>
											</div>	
										<div class="span2">
											Tab No<font class="requiredFont">*</font>
											<input type="text" placeholder="Tab No..." class="input-block-level" id="tabNo">
										</div>
										</div>
										<!--<div class="span3">
											<label>Status</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>-->
											<div class="row-fluid">
										<div class="span3">
											Date<font class="requiredFont">*</font>
											<input type="text" placeholder="User Name..." class="input-block-level" id="date">
										</div>	
																		
									</div>
									<div class="row-fluid">
										<div class="span12">
											Remarks<font class="requiredFont">*</font>
											<textarea class="input-block-level" rows="2" id="remarks"></textarea>
										</div>
									</div>
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onclick="AssignTab();">ASSIGN</button></div>



					</div>
				</div>
			</div>
		</div>
		
		<!---- Booth Assign---->		
		<div class="row" id="boothAssigniv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Assign Booth To User</h4>
							<div class="row">
								<div id="assignBoothErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
								<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<div class="span6">
											Select User Name<font class="requiredFont">*</font>
											<select class="input-block-level" id="userId"> 
												<option value="4">samba_shiva</option>
												<option value="1">rajiv_raj</option>
												<option value="3">sunil_mathur</option>
											</select>
										</div>
										<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<!--<select class="input-block-level" id="constituencyId"> 
											<option value="0">Select Constituency</option>
											</select>-->

						<s:select theme="simple"  name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" />

										</div>
									</div>	
									<!--<div class="row-fluid">
										<div class="span6">
											Panchayat<font class="requiredFont">*</font>
											<select class="input-block-level" id="panchayatId"> 
											<option value="0">Select Panchayat</option></select>
										</div>
										<div class="span6">
											Booths<font class="requiredFont">*</font>
											<select class="input-block-level" id="boothId"> 
											<option value="0">Select Booth</option></select>
										</div>
									</div>-->
									</div>
									</div>
									<div id="boothsDtlsId">
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="saveUserAssignedBoothsDetails();">ASSIGN</button></div>

							

							<!--<a class="btn btn-large btn-success" href="javascript:{saveUserAssignedBoothsDetails();}">ASSIGN</a>-->
					</div>
				</div>
			</div>
		</div>
		
		<!---- User Deactivation---->		
		<div class="row" id="userDeactivationDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Deactive User</h4>
						<div class="row">
								<div id="deactivateUserErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span8 offset2">
										<div class="row-fluid">
										<div class="span6">
												Select User Name <font class="requiredFont">*</font>
												<select class="input-block-level"id="deactiveUserTypeId" onChange="getSurveyUsersByUserType('deactivateUserId',this.value);"> 
												<option value="0">Select User Type</option></select>
											</div>
											<div class="span6">
												Select User Name <font class="requiredFont">*</font>
												<select class="input-block-level"id="deactivateUserId"> 
												<option value="0">Select User Name</option></select>
											</div>										
										</div>
									<div class="row-fluid">
										<div class="span12">
									Remarks	<font class="requiredFont">*</font>
									<textarea class="input-block-level" rows="2" id=="remarksId"></textarea>
									</div>
									</div>
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="deactivateUser();">DEACTIVATE</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- Assign Booth To Leader ---->		
		<div class="row" id="assignBoothToLeaderDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Assign Leaders To User</h4>
						<div class="row">
								<div id="assignLeaderErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										
										<div class="span6">
											Select user Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="typeId" onChange="getSurveyUsersByUserType('leaderId',this.value);"> <option value="0">Select Type</option></select>
										</div>
										<div class="span6">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="leaderId" onChange="getSurveyUsersByUserType('userLeaderId',1);"> <option value="0">Select Leader</option></select>
										</div>
									</div>	
									<div class="row-fluid">
										
										<div class="span6">
											Select User<font class="requiredFont">*</font>
											<select class="input-block-level" id="userLeaderId"> <option value="0">Select User</option></select>
										</div>
										
										<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<select class="input-block-level" id="constituencyLeaderId"> 
											<option value="0">Select Constituency</option>
											<option value="232">Kavali</option>
											</select>
										</div>
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="assignLeaderToUser();">ASSIGN</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- User Verification Details ---->	
		
		<div class="row" id="verificationDiv"></div>
		<!-----LEADER NAME------->
		<div class="row" id="leaderNameDiv"></div>
		
	</div>
 <script>
function getUserTypes(divId)
{
	var jsObj =
	{

	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUserTypeAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}

	});
}

function getSurveyUsersByUserType(divId,value)
{
	var jsObj =
	{
	userTypeId :value,
	task : "getSurveyUsersByUserType"
	}
	$.ajax({
	type:'GET',
	url: 'getSurveyUsersByUserTypeAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	$('#'+divId+'').find('option:not(:first)').remove();
	if(result != null && result.length > 0)
	{
	for(var i in result)
	{
	$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	}

	}
	});
}


function saveSurveyUser()
{
	var	firstName =  $.trim($('#firstName').val());
	var	lastName =   $.trim($('#lastName').val());
	var	userName =   $.trim($('#userName').val());
	var	password =  $.trim($('#password').val());
	var address =   $.trim($('#address').val());
	var	mobileNo =   $.trim($('#mobileNumber').val());
	var	userType =  $('#userType').val();
	var retypePassword =  $.trim($('#retypePassword').val());
	
	if(firstName.length == 0)
	{
		$("#createUserErrorDiv").html("FirstName is required").css("color","red");
		return;
	}
	if(lastName.length == 0)
	{
		$("#createUserErrorDiv").html("LastName is required").css("color","red");
		return;
	}
	if(userName.length == 0)
	{
		$("#createUserErrorDiv").html("UserName is required").css("color","red");
		return;
	}
	
	if(address.length == 0)
	{
		$("#createUserErrorDiv").html("Address is required").css("color","red");
		return;
	
	}
	if(password.length == 0)
	{
		$("#createUserErrorDiv").html("Password is required").css("color","red");
		return;
	}
	if(retypePassword.length == 0)
	{
		$("#createUserErrorDiv").html("Retype Password is required").css("color","red");
		return;
	}
	 if(password.length > 0 && retypePassword.length > 0 && password != retypePassword)
	{
 		$("#createUserErrorDiv").html("Passwords donot match").css("color","red");
       return
	}
	if(mobileNo.length == 0)
	{
		$("#createUserErrorDiv").html("Mobile Number is required").css("color","red");
		return;
	}
	else if(isNaN(mobileNo))
	{
		$("#createUserErrorDiv").html("Mobile Number Should be numeric").css("color","red");
		return;
	}
	if(userType == 0)
	{
		$("#createUserErrorDiv").html("UserType is required").css("color","red");
		return;
	
	}
	var jsObj = 
	{
		firstName : firstName,
		lastName :  lastName,
		userName : userName,
		password :password,
		address :  address,
		mobileNo : mobileNo,
		userType : userType,
		task : "saveSurveyUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyUserAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#createUserErrorDiv").html('User Created successfully.').css("color","green");
				setTimeout(function(){$('#createUserErrorDiv').html('');}, 3000);
				$('#mobileNo,#address,#password,#userName,#lastName,#firstName,#retypePassword').val('');
				$('#userType').val(0);
			}
			else
			{
				$("#createUserErrorDiv").html('Error Occured,Try again....').css("color","red");
			}
		
		});

}


function saveSurveyUserType()
{
	var description = $.trim($('#userTypeDescription').val());
	var userTypeName = $.trim($('#userTypeName').val());
	if(userTypeName.length == 0)
	{
		$("#createUserTypeErrorDiv").html("Please Enter User Type ").css("color","red");
		return;
	}
	if(description.length == 0)
	{
		$("#createUserTypeErrorDiv").html("Please Enter User Type Description").css("color","red");
		return;
	}
	
	var jsObj = 
	{
		description : description,
		userType : userTypeName,
		task : "saveSurveyUserType"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyUserTypeAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#createUserTypeErrorDiv").html('User Type created successfully.').css("color","green");
				setTimeout(function(){$('#createUserTypeErrorDiv').html('');}, 3000);
				$('#userTypeDescription').val('');
			}
			else
			{
				$("#createUserTypeErrorDiv").html('Error Occured,Try again....').css("color","red");
			}
		});
}

$(".highlight").click(function()
{
	$(".highlight").removeClass("selected");
	$(this).addClass("selected");
})
function AssignTab()
{
	$("#assignTabErrorDiv").html('');
	//var uname = $.trim($("#uname").val());
	var surveyUser = $("#surveyUserIdForSelect").val();
	var tabNo = $.trim($("#tabNo").val());
	var remarks = $.trim($("#remarks").val());
	var date = $("#date").val();
	var str = ''; 
	if(surveyUser == 0)
	{
		str +='Select User<br/>';
	}
	
	if(tabNo.length == 0)
	{
		str +='Tab No is required <br/>';
	}
	if(remarks.length == 0)
	{
		str +='Remarks is required <br/>';
	}
	if(date.length == 0)
	{
		str +='Date is required <br/>';
	}
	if(str != '')
	{
	$("#assignTabErrorDiv").html(str).css("color","red");
	return;
	}
	else
$("#assignTabErrorDiv").html('');
	var jObj = {
		surveyUserId : surveyUser,
		tabNo :tabNo,
		remarks : remarks,
		date : date
			
	}
	$.ajax({
          type:'POST',
          url: 'assignTabAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jObj)},
     	  }).done(function(result){ 
			if(result.resultCode == 0) 
				$("#assignTabErrorDiv").html('Tab assigned successfully.').css("color","green");
			else
			  {
				$("#assignTabErrorDiv").html('Error Occured,Try again....').css("color","red");
			  }
	   });
	
}

function showHideTabs(id)
{


if(id == "userTypeTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").show();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	
	}

else if(id == "userCreationTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").show();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	getUserTypes('userType');
	}

	else if(id == "tabAssignTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").show();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	buildDatePicker();
	getUserTypes('surveyUserTypeForSelect');
	}

		else if(id == "boothAssignTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").show();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	}
	
	else if(id == "deactivationTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").show();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	getUserTypes('deactiveUserTypeId');
	}

	
		else if(id == "assignLeaderTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").show();
	$("#verificationDiv").hide();
	$("#leaderNameDiv").hide();
	getUserTypes('typeId');
	
	}
		else if(id == "verificationDetailsTab")
	{
	$(".errorCls").html('');
	$("#createUserTypeDiv").hide();
	$("#userCreationDiv").hide();
	$("#tabAssignDiv").hide();
	$("#boothAssigniv").hide();
	$("#userDeactivationDiv").hide();
	$("#assignBoothToLeaderDiv").hide();
	$("#verificationDiv").show();
	$("#leaderNameDiv").show();
	getLeaderDetetilsByContituencyWise();
	}



}
function buildDatePicker()
{
	
	$("#date").datepicker({ 
	dateFormat: 'dd-mm-yy',
	
	onSelect: function(dateText, inst) {
	}
   }).datepicker('setDate', new Date());

}

function assignBooth()
{
	var	user =  $("#userId").val();
	var	constituencyId = $("#constituencyId").val();
	var	panchayatId =  $("#panchayatId").val();
	var	boothId =  $('#boothId').val();

	if(user == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select The User").css("color","red");
		return;
	}
	if(constituencyId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Constituency").css("color","red");
		return;
	}
	if(panchayatId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Panchayat").css("color","red");
		return;
	}
	if(boothId == 0)
	{
		$("#assignBoothErrorDiv").html("Please Select Booth").css("color","red");
		return;
	}
	var jsObj = 
	{
		user : user,
		constituencyId :  constituencyId,
		panchayatId : panchayatId,
		boothId :  boothId,
		task : "assignBooth"
	}
	
	$.ajax({
		type:'GET',
		url: 'assignBoothAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#assignBoothErrorDiv").html('Booth Assigned successfully.').css("color","green");
				setTimeout(function(){$('#assignBoothErrorDiv').html('');}, 3000);				
				$('#userId,#boothId,#panchayatId,#constituencyId').val(0);
			}
			else
			{
				$("#assignBoothErrorDiv").html('Error Occured,Try again....').css("color","red");
			}
		
		});

}
function assignLeaderToUser()
{
	var	typeId =  $("#typeId").val();
	var	constituencyLeaderId = $("#constituencyLeaderId").val();
	var	leaderId =  $("#leaderId").val();
	var	userLeaderId =  $('#userLeaderId').val();

	if(constituencyLeaderId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select Constituency").css("color","red");
		return;
	}
	if(typeId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select the Type").css("color","red");
		return;
	}
	if(leaderId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select Leader").css("color","red");
		return;
	}
	if(userLeaderId == 0)
	{
		$("#assignLeaderErrorDiv").html("Please Select User").css("color","red");
		return;
	}
	var jsObj = 
	{
		constituencyId : constituencyLeaderId,
		userTypeId :  typeId,
		leaderId : leaderId,
		userId :  userLeaderId,
		task : "assignLeader"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveServeyUserRelationDetailsAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#assignLeaderErrorDiv").html('Leader Assigned successfully.').css("color","green");
				setTimeout(function(){$('#assignLeaderErrorDiv').html('');}, 3000);				
				$('#userLeaderId,#leaderId,#typeId,#constituencyLeaderId').val(0);
			}
			else
			{
				$("#assignLeaderErrorDiv").html('Error Occured,Try again....').css("color","red");
			}		
		});

}

function deactivateUser()
{
	var	deactivateUserId =  $("#deactivateUserId").val();
	var	remarksId = $.trim($("#remarksId").val());
	

	if(deactivateUserId == 0)
	{
		$("#deactivateUserErrorDiv").html("Please Select The User").css("color","red");
		return;
	}
	if(remarksId.length == 0)
	{
		$("#deactivateUserErrorDiv").html("Remarks is Required").css("color","red");
		return;
	}
	
	var jsObj = 
	{
		deactivateUserId : deactivateUserId,
		remarksId :  remarksId,
		
		task : "deactivateUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'deactivateUserAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result.resultCode == 0) 
			{
				$("#deactivateUserErrorDiv").html('Deactivated Usersuccessfully.').css("color","green");
				setTimeout(function(){$('#deactivateUserErrorDiv').html('');}, 3000);				
				$('#deactivateUserId').val(0);
				$('#remarksId').val('');
			}
			else
			{
				$("#deactivateUserErrorDiv").html('Error Occured,Try again....').css("color","red");
			}		
		});

}

function getLeaderDetetilsByContituencyWise()
{
	var jsObj = 
	{
	}
	
	$.ajax({
		type:'GET',
		url: 'getSurveyLeadersAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.length > 0)
			{	
				var str = '';
			
				str += '<div class="span10 offset1">';
				str += '<div class="row-fluid">';
				str += '<div class="span12 widgetservey_Red m_top20">';
				str += '<h4>User Verification Details</h4>';
				str += '<div class="row">';
				str += '<div class="span8 offset2">';
				str += '<table class="table table-bordered">';
				str += '<thead>';
				str += '<tr>	';									  
				str += ' <th>CONSTITUENCY NAME</th>';
				str += '<th>LEADER NAME</th>	';									  
				str += '</tr>';
				str += ' </thead>';
				str += '<tbody>	';			
				for(var i in result)
				{
					str += '<tr>		';								  
					str += '<td>'+result[i].desc+'</td>';
					str += '<td><a onClick="getBoothDetailsForSelectedUser('+result[i].id+','+result[i].rank+')">'+result[i].name+'</a></td>';
					str += '</tr>	';									
				}
				
				str += '</tbody>';
				str += '</table>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
			    str += '</div>';
				
				$('#verificationDiv').html(str);
			}			
		});
}

function getBoothDetailsForSelectedUser(leaderId,constituencyId)
{
	var jsObj = 
	{
		leaderId : leaderId,
		constituencyId : constituencyId,
		task : "getBoothDetailsForSelectedUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'getSurveyUsersByLeadersAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result != null && result.length > 0)
			{	
				var str = '';
				str += '<div class="span10 offset1">';
				str += '<div class="row-fluid">';
				str += '<div class="span12 widgetservey m_top20">';
				str += '<h4>LEADER NAME</h4>';
				str += '<div class="row">';
				str += '<div class="span8 offset2">';
				str += '<table class="table table-bordered">';
				str += '<thead>';
				str += '<tr>	';									  
				str += '<th>USER NAMES</th>';
				str += ' <th>ASSIGNED BOOTH NUMBERS</th>	';									  
				str += '</tr>';
				str += '</thead>';
				str += '<tbody>	';	
				for(var i in result)	
				{
					str += '<tr>';								  
					str += '<td>'+result[i].name+'</td>';
					str += '<td>';
					for(var j in result[i].genericVOList)
					{
						str += '<a>'+result[i].genericVOList[j].rank+' , </a>';
					}
					str += ' </td>';
					str += '</tr>';	
				}			
											
				str += ' </tbody>';
				str += '</table>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
			    str += '</div>';
				$('#leaderNameDiv').html(str);
			}			
		});
}

function getUserAssignedBoothsDetailsForAConstituency()
{
	var jObj =
	{
	 constituencyId:$('#constituencyId').val(),
	 userId:$('#userId').val()
	}
	$.ajax({
			type:'GET',
			url: 'getAssignedBoothsDetailsByConstituencyIdAndUserId.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				buildBoothDetails(result);
		});
}

function buildBoothDetails(result)
{
	var str = '';

	str+='<div class="span12">';
    str+='<br/><br/>';
	 $.each(result,function(index,value){
		 if(value.userHas == false)
		   str+='<div class="span2"><label><input type="checkbox" value="'+value.boothId+'" class="boothChckbox"/>  Booth - '+value.partNo+'</label></div>';
		 else
		  str+='<div class="span2"><label><input type="checkbox" checked value="'+value.boothId+'" class="boothChckbox"/>  Booth - '+value.partNo+'</label></div>';

	 });
	str+='</div>';
	
	$('#boothsDtlsId').html(str);
}
function saveUserAssignedBoothsDetails()
{
	var jObj =
	{
	  boothIds:[],
	  constituencyId:$('#constituencyId').val(),
	  surveyUserId:$('#userId').val()
	}

	$('.boothChckbox').each(function(index,value){
		if(this.checked)
			jObj.boothIds.push(this.value);
	}); 
	
	$.ajax({
			type:'GET',
			url: 'saveUserAssignedBoothsDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){

		});

}
</script>
<script>
showHideTabs('userTypeTab');
</script>
</body>
</html>