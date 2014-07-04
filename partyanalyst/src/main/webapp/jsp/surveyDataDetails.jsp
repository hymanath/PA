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
	<script src="js/surveyDataDetails.js"></script>
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
			
			.highlight{
			cursor: pointer;
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
	showHideTabs('userTypeTab');


});
  </script>
	<div class="container">
		<div class="row">
			<div class="span10 offset1 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a class="highlight selected" id="userTypeTab" onclick="showHideTabs(this.id);"> User Types </a></li>
					<li><a class="highlight" id="userCreationTab" onclick="showHideTabs(this.id);"> User Creation </a></li>
					<li><a class="highlight" id="tabAssignTab" onclick="showHideTabs(this.id);"> Tab Assign </a></li>
					<li><a class="highlight" id="boothAssignTab" onclick="showHideTabs(this.id);"> Booth Assign </a></li>
					<li><a class="highlight" id="deactivationTab" onclick="showHideTabs(this.id);"> Deactivation </a></li>
					<li><a class="highlight" id="assignLeaderTab" onclick="showHideTabs(this.id);"> Assign Leader</a></li>
					<li><a class="highlight" id="verificationDetailsTab" onclick="showHideTabs(this.id);"> Verification Details	 </a></li>
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
							<div class="row text-center m_top20"><button onClick="saveSurveyUserType();" type="button" class="btn btn-large btn-success">CREATE</button></div>
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
											<select class="input-block-level" id="typeId" onChange="getSurveyUsersByUserType('leaderId',this.value);"> <option value="0">Select Type</option><option value="3">Lead</option><option value="5">Chief Verifier</option></select>
										</div>
										<div class="span6">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="leaderId" onChange="getSurveyUsersByUserTypeForLeaderAssign('userLeaderId');"> <option value="0">Select Leader</option></select>
										</div>
									</div>	
									<div class="row-fluid">
										
										<div class="span6">
											Select User<font class="requiredFont">*</font>
											<select class="input-block-level" id="userLeaderId"> <option value="0">Select User</option></select>
										</div>
										
										<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<s:select theme="simple"  name="constituency" id="constituencyLeaderId" list="constituenciesList" listKey="id" listValue="name" />
											<!--<select class="input-block-level" id="constituencyLeaderId"> 
											<option value="0">Select Constituency</option>
											<option value="232">Kavali</option>
											</select>-->
										</div>
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="assignLeaderToUser();">ASSIGN</button></div>

                            <div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							<!--<a class="btn btn-primary btn-large" href="javascript:{getDayWiseReport()}">TEST</a>-->
					</div>
				</div>
			</div>
			
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Release Leaders To User</h4>
						<div class="row">
								<div id="assignLeaderErrorDivForRelease" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										
										<div class="span6">
											Select user Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="typeIdForRelease" onChange="getSurveyUsersByUserType('leaderIdForRelease',this.value);"> <option value="0">Select Type</option><option value="3">Lead</option><option value="5">Chief Verifier</option></select>
										</div>
										<div class="span6">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="leaderIdForRelease" onChange="getSurveyUsersByUserTypeForLeaderRelease();"> <option value="0">Select Leader</option></select>
										</div>
									</div>	
									<div class="row-fluid">
										
										<div class="span6">
											Select User<font class="requiredFont">*</font>
											<select class="input-block-level" id="userLeaderIdForRelease"> <option value="0">Select User</option></select>
										</div>
										
										<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<!--<s:select theme="simple"  name="constituency" id="constituencyLeaderId" list="constituenciesList" listKey="id" listValue="name" />-->
											<select class="input-block-level" id="constituencyLeaderIdForRelease"> 
											<option value="0">Select Constituency</option>
											</select>
										</div>
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="updateLeaderUserReleation();">RELEASE</button></div>
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
function getDayWiseReport()
{
	var jObj =
	{
	 constituencyId:232,
     userTypeId:1,
	 startDate:"07/01/2014",
	 endDate:"07/04/2014"	

	}
	$.ajax({
			type:'GET',
			url: 'getDayWisereportDetailsByConstituencyId.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				buildDayWiseReport(result);
		});
}
function buildDayWiseReport(result)
{
  var str = '';

  str+='<table border="1">';
  str+='<thead>';
   str+='<tr>';
    str+='<th>UserName</th>';
	$.each(result[0].subList,function(index,value){
      str+='<th>'+value.surveyDate+'</th>';
	});
   str+='</tr>';
  str+='</thead>';
  str+='<tbody>';
  
    $.each(result,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.userName+'</td>';
		   $.each(value.subList,function(index1,value1){
			    str+='<td><a href="javascript:{getDayWiseReportDetailsOfUser('+value.userid+')}">'+value1.count+'</a></td>';
		   });
		str+='</tr>';
	});
  
  str+='</tbody>';
  str+='</table>';

  $('#dayWiseReportDiv').html(str);
}
function getDayWiseReportDetailsOfUser(userId)
{
	var jObj =
	{
	 userId:userId,
	 startDate:"07/01/2014",
	 endDate:"07/04/2014"
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseUserSamplesDetailsByDates.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				buildUserBoothWiseCountDetails(result);
		});
}
function buildUserBoothWiseCountDetails(result)
{
  var str ='';

  str+='<table border="1">';
   str+='<thead>';
    str+='<tr>';
	  str+='<th>Booth No</th>';
	  str+='<th>Total</th>';
	  str+='<th>Completed</th>';
	str+='</tr>';
   str+='</thead>';	  
   str+='<tbody>';
    $.each(result,function(index,value){
		str+='<tr>';
		str+='<td>'+value.partNo+'</td>';
		str+='<td>'+value.totalVoters+'</td>';
		str+='<td>'+value.count+'</td>';
		str+='</tr>';
	});
   str+='</tbody>';
  str+='</table>';

  $('#boothWiseCountDivId').html(str);
  $('#boothWiseCountDivId').dialog();
}

$(".highlight").click(function()
{
	$(".highlight").removeClass("selected");
	$(this).addClass("selected");
})
</script>
</body>
</html>