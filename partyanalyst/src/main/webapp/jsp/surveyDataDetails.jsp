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
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
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
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:11px; padding:12px 4px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			.requiredFont,#noBoothsDivId{
				color:red;
				font-size:13px;
			}
			
			.highlight{
			cursor: pointer;
			}
			
			.datePickerCls{
			 cursor: text !important;
			}
			
			.survey_nav ul li .dropdown-menu{background:#ffcc00; border-top-left-radius:0px; border-top-right-radius:0px;margin-top:-1px; margin-right:5px;}
			.survey_nav ul li .dropdown li a{color:#333; }
			
			.ui-multiselect{
				width:200px !important;
			}
			
		</style>
  </head>
  
  <body>
<script>
  $(document).ready(function(){
  
   $('.datePickerCls').datepicker({
   dateFormat: 'dd-mm-yy'
   });
   
   $('#userLeaderId').multiselect({
	  noneSelectedText:"Select User(s)"});
	  
	  
	   $('#userLeaderIdForRelease').multiselect({
	  noneSelectedText:"Select User(s)"});
	  
	   $('#inHouseVerifierId').multiselect({
	  noneSelectedText:"Select User(s)"});
	  
	   $('#inHouseReleaseId').multiselect({
	  noneSelectedText:"Select User(s)"});
	  
    $('#constituencyId').change(function(){
		$('#boothsDtlsId').html('');
		getUserAssignedBoothsDetailsForAConstituency();
	});
	
	$('#webMonitorUserIds').multiselect({
	  noneSelectedText:"Select User(s)"});
	showHideTabs('userCreationTab');
	
	
	$('#boothIdForSavePercentages').multiselect({
	  noneSelectedText:"Select Booth(s)"});


});
  </script>
	<div class="container">
		<div class="row">
			<div class="span10 offset1 m_top20 survey_nav">
				<ul class="inline unstyled">
					<!-- <li><a class="highlight selected " id="userTypeTab" onclick="showHideTabs(this.id);"> User Type Creation </a></li>-->
					<li><a class="highlight  selected" id="userCreationTab" onclick="showHideTabs(this.id);"> User Creation </a></li>
					<li><a class="highlight" id="assignLeaderTab" onclick="showHideTabs(this.id);"> Assign/Release</a></li>
					<li><a class="highlight" id="tabAssignTab" onclick="showHideTabs(this.id);"> Tab Assign </a></li>
					<li><a class="highlight" id="boothAssignTab" onclick="showHideTabs(this.id);"> Booth Assign </a></li>
					<li><a class="highlight" id="deactivationTab" onclick="showHideTabs(this.id);"> Deactivation </a></li>
					<li><a class="highlight" id="verificationDetailsTab" onclick="showHideTabs(this.id);"> Verification </a></li>
					<li><a class="highlight" id="webMonitorAssignUsersTab" onclick="showHideTabs(this.id);">Web Monitor </a></li>
					<li><a class="highlight" id="boothPercentageTab" onclick="showHideTabs(this.id);">Booth Percentage</a></li>
					<!--<li class="highlight" id="reportsTab" onclick="showHideTabs(this.id);">
					
					
					</li>-->
					<!--<li><a class="highlight" id="reportTab" onclick="redicttoVerificationPage();"> Reports	 </a></li>-->
				</ul>
			</div>
		</div>
	<!--	<div class="row" id="createUserTypeDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red">
						<h4>Create User Types</h4>
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
									Description
									<textarea id="userTypeDescription" class="input-block-level" rows="2"></textarea>
								</div>
							</div>
							<div class="row text-center m_top20"><button onClick="saveSurveyUserType();" type="button" class="btn btn-large btn-success">CREATE</button><img id="processingImgForUserType" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>
					</div>
				</div>
			</div>
		</div>
	-->
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
											First Name<font class="requiredFont">*</font>
											<input type="text" id="firstName" placeholder="First Name..." class="input-block-level">
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
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="saveSurveyUser();">CREATE</button><img id="processingImgForUserCreation" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="saveBoothsPercentage" style="dispaly:none;">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4 id="wmtitleId">Verifier Booth Percentage</h4>
							<div class="row">
						<div id="errorDivSB" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
									<div class="offset1">
										<div class="row-fluid">
											
											<div class="span4">
												Select Constituency <font class="requiredFont">*</font>
													<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyForSP" list="dataAvilableConstituencies" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onChange="getBoothsDetailsInCallStatusInfoForSavePercnt(this.value,'boothIdForSavePercentages')"/>
													
											</div>
											
											<div class="span4">
												Select Booth(s) <font class="requiredFont">*</font> : 
												<select class="input-block-level" id = "boothIdForSavePercentages" multiple="multiple"> <option value="0">Select Booth(s)</option></select>
											</div>
												
												
											<div class="span4">
												Percentage <font class="requiredFont">*</font> : 
												<input type="text" placeholder="Enter Percentage" id="percenageForBooth" style="width:180px"/></div>
												
											</div>
											
										</div>	
									</div>
									 
									 
									<div class="row text-center m_top20">
									<button type="button" class="btn btn-success" style="cursor:pointer;" onclick="saveBoothPercentage()">SUBMIT</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img id="boothImageForSavingPercent" style="display: none;" src="./images/icons/search.gif" alt="Processing Image" style="margin-right: 30px"></img>
									 
									</div>
						<div id="retunMsg" class="clearCls"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximgForWm" style="width:70px;height:60px;display:none;"/>
								</div>
						
                         <div id="dayWiseReportDivForWm" class="clearCls"></div>
							
					</div>
				</div>
				
				
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>PROVIDING DATA FOR THIRD PARTY</h4>
							<div class="row">
								<div id="errorDivTP" class="span8 offset3 clearCls"></div>
							</div>
							<div class="row">
								<div class="offset3">
									<div class="row-fluid">											
										<div class="span4">
											Select Constituency <font class="requiredFont">*</font>
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyForTP" list="dataAvilableConstituencies" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onChange="getThirdPartyDataAvaliableBooths(this.value,'boothIdForTP')"/>
										</div>
										<div class="span3">
											Select Booth <font class="requiredFont">*</font> : 
											<select class="input-block-level" id = "boothIdForTP" > <option value="0">Select Booth</option></select>
										</div>											
									</div>	
								</div>
									<div class="row text-center m_top10"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="saveThirdPartyDetails()">SUBMIT</button></div>
									<img src='images/Loading-data.gif' class="offset5"  id="ajaxImgForTP" style="width:70px;height:60px;display:none;"/>
							</div>
					</div>
				</div>
			</div>
			
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>REMOVING THIRD PARTY DATA </h4>
							<div class="row">
								<div id="errorDivTPRemove" class="span8 offset3 clearCls"></div>
							</div>
							<div class="row">
								<div class="offset3">
									<div class="row-fluid">											
										<div class="span4">
											Select Constituency <font class="requiredFont">*</font>
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyForTPRemove" list="dataAvilableConstituencies" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onChange="getThirdPartyDataAvaliable(this.value,'boothIdForTPRemove')"/>
										</div>
										<div class="span3">
											Select Booth <font class="requiredFont">*</font> : 
											<select class="input-block-level" id = "boothIdForTPRemove" > <option value="0">Select Booth</option></select>
										</div>											
									</div>	
								</div>
									<div class="row text-center m_top10"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="removeThirdPartyDetails()">REMOVE</button></div>
									<img src='images/Loading-data.gif' class="offset5"  id="ajaxImgForTPForRemove" style="width:70px;height:60px;display:none;"/>
							</div>
					</div>
				</div>
			</div>
	
	</div>
		<!----TAB Assign ---->		
		<div class="row" id="tabAssignDiv">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
					
					<!----	<h4>User Tab Assign</h4> ---->
					<h4>Assign Tabs to Leader </h4> 
						<div class="row">
						<div id="assignTabErrorDiv" class="span8 offset2 errorCls"></div>
						</div>
							<div class="row">
								<div class="span10 offset2">
									<div class="row-fluid">
										<div class="span6">
											<!--<label>User Name</label>
											<input type="text" placeholder="User Name..." class="input-block-level" id="uname">-->
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="surveyUserTypeForSelect" onchange="getSurveyUsersByUserType('surveyUserIdForSelect',this.value);">
											<option value="0"> Select User type </option>
											<option value="3">Data Lead </option>
											<option value="5"> Verifier Lead </option>
											<option value="11"> Third Party Lead </option>
											</select>
											</div>
											<div class="span6">
											Select Leader Name<font class="requiredFont">*</font>
											<select class="input-block-level" id="surveyUserIdForSelect" onchange="getAlreadyAssignTabsList(this.value);"> <option value="0">Select Leader Name</option></select>
											</div>	
										
										</div>
										<!--<div class="span3">
											<label>Status</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>-->
										<div class="row-fluid">
											<div class="span6">
											Tab No<font class="requiredFont">*</font>
											<input type="text" placeholder="Tab No..." class="input-block-level newTabCls" id="tabNo1">
										</div>
										<div class="span6">
											Date<font class="requiredFont">*</font>
											<input type="text" placeholder="Select Date ..." class="input-block-level datePickerCls" id="date1"  readOnly="true">
										</div>	
																		
									</div>
									
									<div id="assignNewTabsDiv"></div>
									<a href="javascript:{assignNewTabDiv();}"><span class="btn btn-info" style="float:right;" title="Click here to Assign a new tab"> <b> +</b></span> </a> 
								<!--
									<div class="row-fluid">
										<div class="span12">
											Remarks
											<textarea class="input-block-level" rows="2" id="remarks"></textarea>
										</div>
									</div>
								-->
								
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onclick="AssignTab();">ASSIGN</button><img id="processingImgForTabAssign" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>



					</div>
				</div>
			</div>
		</div>
		
		<!----TAB Assign ---->		
		<div class="row" id="tabAssignUserDiv" style="display:none;">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
					
					<!----	<h4>User Tab Assign</h4> ---->
					<h4>Assign Tabs to User </h4> 
						<div class="row">
						<div id="assignTabUserErrorDiv" class="span8 offset2 errorCls" style="color:#FF0020;"></div>
						</div>
							<div class="row">
								<div class="span10 offset2">
									<div class="row-fluid">
										<div class="span6">
											Select Constituency <font class="requiredFont">*</font>
											<select class="input-block-level" id="constitList" onchange="getConstituencyLeadersList('constituencyLeadrList',this.value);">
											<option value="0">Select Constituency</option>
											</select>
											</div>
											<div class="span6">
										Select Leader <font class="requiredFont">*</font>
										<select class="input-block-level" id="constituencyLeadrList" onchange="usersListByTabsInfo('tabAssignTabsId',this.value);"> 
											<option value="0">Select Leader</option>
										</select>
											</div>	
										
										</div>
										
										<div id="tabAssignTabsId"></div>
								
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onclick="assignTabsToLeaderUsers();">ASSIGN</button><img id="processingImgForTabAssign" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>



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
								<div id="assignBoothErrorDiv" class="span8 offset3 errorCls"></div>
							</div>
								<div class="row">
								<div class="span12 offset3">
									<div class="row-fluid">
										<div class="span3">
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="boothAssignUserType" onchange="getSurveyUsersByUserTypeForBooth('leaderIdForBooth',this.value);">
											
											<option value="0">Select User type</option>
											<option value="1">Data Collectors</option>
											<option value="4">Data Verifier</option>
											<option value="10">Third Party</option>
											<!--<option value="5">Verifier Lead</option>-->
											</select>
										</div>
										<div class="span3">
											Select Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="leaderIdForBooth" onChange="getUsersForLeaders('userId',this.value)"> 
											<option value="0">Select Leader</option>
											</select>
										</div>
										</div>
										<div  class="row-fluid">
										<div class="span3">
											Select User Name<font class="requiredFont">*</font>
											<select class="input-block-level" id="userId" onChange="getExistedConstituenciesDetails(this.value)"> 
											<option value="0">Select User Name</option>
											</select>
										</div>
										<div class="span3">
											Select Constituency<font class="requiredFont">*</font>
											<select class="input-block-level" id="constituencyId"> 
											<option value="0">Select Constituency</option>
											</select>
											
										</div>
										<div class="span3" style="margin-top: 30px;">
										<label for="remainingData"><input type="checkbox" id="remainingData"/> Special Booths</label> 
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
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="saveUserAssignedBoothsDetails();" id="assignBtnId">ASSIGN</button>
							<img src='./images/icons/search.gif' id="assignboothimg" style="display:none;"/></div>

							<div id="noBoothsDivId"></div>



							

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
										Select Constituency  <font class="requiredFont">*</font> :
										<s:select theme="simple"  name="constituency" headerValue="Select Constituency" headerKey="0" id="deActivatConstituencyId" style="width:180px;" list="constituenciesList" listKey="id" listValue="name" />
										</div>
										
										<div class="span6">
												Select User Type <font class="requiredFont">*</font>
												<select class="input-block-level"id="deactiveUserTypeId" onChange="getSurveyUsersByUserTypeForDeActive('deactivateLeaderId',this.value);"> 
													<option value="0">Select User Type</option>
													<option value="1"> Data Collector </option>
													<option value="3"> Lead </option>
													<option value="4"> Data Verifier </option>
													<option value="5"> Verifier Lead </option>
													<option value="10"> Third Party </option>
													<option value="11"> Third Party Lead </option>
												</select>
										</div>
											<div class="row-fluid">
												<div class="span6">
														Select Lead <font class="requiredFont">*</font>
														<select class="input-block-level"id="deactivateLeaderId" onChange="getAssignedUsers('deactivateUserId',this.value,'');"> 
														<option value="0"> Select Lead </option></select>
												</div>
													
												<div class="span6">
													Select Data Collector <font class="requiredFont">*</font>
													<select class="input-block-level"id="deactivateUserId"> 
													<option value="0">Select Data Collector</option></select>
												</div>	
											
											</div>
										</div>
									<div class="row-fluid">
										<div class="span12">
									Remarks	<font class="requiredFont">*</font>
									<textarea class="input-block-level" rows="2" id="remarksId"></textarea>
									</div>
									</div>

										<div class="row-fluid" id="deactivedummyLead" style="display:none">

										<div class="span6" id="dummyLeadinnerDiv">
										<div id="errorPop"></div>
									Lead Name	<font class="requiredFont">*</font>
									<select class="input-block-level"id="dummyLeadID"> 
												</select>
									<!--<input class="input-block-level" type="text" id="leadId" placeholder ="Enter lead name"/>-->
									

									&nbsp;<input type="button" value="OK" onclick="deactivateLead();"/>
									<input type="button" value="Cancel" onclick="closePopup();"/>
									<img id="dummyuserImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
									</div>
					              </div>


								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="deactivateUser();">DEACTIVATE</button><img id="processingImgForDeactivation" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- Assign Booth To Leader ---->		
		<div class="row" id="assignBoothToLeaderDiv">
		
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Tag Constituency To Leader</h4>
						<div class="row">
								<div id="assignconstiErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span12 offset2">
									<div class="row-fluid">									
										<div class="span3">
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="constiUserTypeId" onChange="getAssignedConstituencyUsers('assignConstituencyLeaderId',this.value);"> <option value="0">Select Type</option><option value="3">Data Lead</option><option value="5">Verifier Lead</option><option value="11">Third Party Lead</option></select><img id="constiUserTypeIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span3">
											Select Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="assignConstituencyLeaderId" onChange="getAssignedConstituencies('assignConstituencyId');"> <option value="0">Select Leader</option></select><img id="assignConstituencyLeaderIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
									
																
										<div class="span3">
											Select Constituency<font class="requiredFont">*</font>
											<select class="input-block-level" id="assignConstituencyId"> <option value="0">Select Constituency</option></select>
											
										</div>
										
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="assignConstituencyToUser();">ASSIGN</button><img id="processingImgForAssignConsti" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>

                            <div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							<!--<a class="btn btn-primary btn-large" href="javascript:{getComparisionReport()}">TEST</a>-->
							
							<!--<a class="btn btn-primary btn-large" href="javascript:{getDayWiseReport()}">TEST</a>-->
					</div>
				</div>
			</div>
					
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Untag Constituency To Leader</h4>
						<div class="row">
								<div id="untagConstiErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span12 offset2">
									<div class="row-fluid">									
										<div class="span3">
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="untagUserTypeId" onChange="getAlreadyAssignedUsers('untagUserId',this.value);"> <option value="0">Select Type</option><option value="3">Data Lead</option><option value="5">Verifier Lead</option><option value="11">Third Party Lead</option></select><img id="untagUserIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span3">
											Select Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="untagUserId" onchange="getConstituenciesToUnTag('untagConstituencyId',this.value);"> <option value="0">Select Leader</option></select><img id="untagUserIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
									
																
										<div class="span3">
											Select Constituency<font class="requiredFont">*</font>
											<select class="input-block-level" id="untagConstituencyId"> <option value="0">Select Constituency</option></select>
											
										</div>
										
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="unTagConstituencyToUser();">ASSIGN</button><img id="processingImgToUnTagConsti" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>
					</div>
				</div>
			</div>
			
					
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Assign Users To Leader</h4>
						<div class="row">
								<div id="assignLeaderErrorDiv" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span12 offset2">
									<div class="row-fluid">
										
										<div class="span3">
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="typeId" onChange="getSurveyUsersByUserType('leaderId',this.value,'typeIdImg');"> <option value="0">Select Type</option><option value="3">Data Lead</option><option value="5">Verifier Lead</option><option value="11">Third Party Lead</option></select><img id="typeIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span3">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="leaderId" onChange="getSurveyUsersByUserTypeForLeaderAssign('userLeaderId','leaderIdImg');"> <option value="0">Select Leader</option></select><img id="leaderIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
									
										
										<div class="span3">
											Select User<font class="requiredFont">*</font>
											<select class="input-block-level" id="userLeaderId" multiple="true"></select>
										</div>
										
										<!--<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<s:select theme="simple"  name="constituency" id="constituencyLeaderId" list="constituenciesList" listKey="id" listValue="name" />
											<!--<select class="input-block-level" id="constituencyLeaderId"> 
											<option value="0">Select Constituency</option>
											<option value="232">Kavali</option>
											</select>
										</div>-->
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="assignLeaderToUser();">ASSIGN</button><img id="processingImgForAssignLeader" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>

                            <div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							<!--<a class="btn btn-primary btn-large" href="javascript:{getComparisionReport()}">TEST</a>-->
							
							<!--<a class="btn btn-primary btn-large" href="javascript:{getDayWiseReport()}">TEST</a>-->
					</div>
				</div>
			</div>
			
			
			
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Release Users From Leader</h4>
						<div class="row">
								<div id="assignLeaderErrorDivForRelease" class="span8 offset2 errorCls"></div>
							</div>
							<div class="row">
								<div class="span12 offset2">
									<div class="row-fluid">
										
										<div class="span3">
											Select User Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="typeIdForRelease" onChange="getSurveyUsersByUserType('leaderIdForRelease',this.value,'typeIdForReleaseImg');"> <option value="0">Select Type</option><option value="3">Data Lead</option><option value="5">Verifier Lead</option><option value="11">Third Party Lead</option></select><img id="typeIdForReleaseImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span3">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="leaderIdForRelease" onChange="getSurveyUsersByUserTypeForLeaderRelease('leaderIdForReleaseImg');"> <option value="0">Select Leader</option></select><img id="leaderIdForReleaseImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
									
										
										<div class="span3">
											Select User<font class="requiredFont">*</font>
											<select class="input-block-level" id="userLeaderIdForRelease"  multiple="true"></select>
										</div>
										
										<!--<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<!--<s:select theme="simple"  name="constituency" id="constituencyLeaderId" list="constituenciesList" listKey="id" listValue="name" />
											<select class="input-block-level" id="constituencyLeaderIdForRelease"> 
											<option value="0">Select Constituency</option>
											</select>
										</div>-->
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="updateLeaderUserReleation();">RELEASE</button><img id="processingImgForReleaseLeader" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>
					</div>
				</div>
			</div>
			
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Assign Inhouse Verifiers To Leader</h4>
						<div class="row">
								<div id="inhouseAssignErrorDiv" class="span8 offset2 errorCls"></div>
						</div>
							<div class="row">
								<div class="span12 offset2">
									<div class="row-fluid">
										
										<div class="span3">
											Select user Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="inHousetypeId" onChange="getSurveyUsersByUserType('inHouseleaderId',this.value,'inHousetypeIdImg');"> <option value="0">Select Type</option><option value="3">Data Lead</option></select><img id="inHousetypeIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span3">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="inHouseleaderId" onChange="getRemeaningSurveyUsersByUserType('inHouseVerifierId',2,'inHouseleaderIdImg');"> <option value="0">Select Leader</option></select><img id="inHouseleaderIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
								
										
										<div class="span3">
											Select In House Verifier<font class="requiredFont">*</font>
											<select class="input-block-level" id="inHouseVerifierId"  multiple="true"> </select>
										</div>
										
										<!--<div class="span6">
											Select Constituency<font class="requiredFont">*</font>
											<s:select theme="simple"  name="constituency" id="constituencyLeaderId" list="constituenciesList" listKey="id" listValue="name" />
											<!--<select class="input-block-level" id="constituencyLeaderId"> 
											<option value="0">Select Constituency</option>
											<option value="232">Kavali</option>
											</select>
										</div>-->
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="assignInhouseVerfiersToLeader();">ASSIGN</button><img id="processingImgForInhouseLeader" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>

                            <div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							<!--<a class="btn btn-primary btn-large" href="javascript:{getComparisionReport()}">TEST</a>-->
							
							<!--<a class="btn btn-primary btn-large" href="javascript:{getDayWiseReport()}">TEST</a>-->
					</div>
				</div>
			</div>
			
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Release Inhouse Verfiers From Leader</h4>
						<div class="row">
								<div id="inhouseReleaseErrorDiv" class="span8 offset2 errorCls"></div>
						</div>
							<div class="row">
								<div class="span12 offset2">
									<div class="row-fluid">
										
										<div class="span3">
											Select user Type<font class="requiredFont">*</font>
											<select class="input-block-level" id="inHouseReleasetypeId" onChange="getSurveyUsersByUserType('inHouseReleaseleaderId',this.value,'inHouseReleasetypeIdImg');"> <option value="0">Select Type</option><option value="3">Data Lead</option></select><img id="inHouseReleasetypeIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span3">
											Select  Leader<font class="requiredFont">*</font>
											<select class="input-block-level" id="inHouseReleaseleaderId" onChange="getInHouseVerfiersByUserTypeForLeaderRelease('inHouseReleaseleaderIdImg');"> <option value="0">Select Leader</option></select><img id="inHouseReleaseleaderIdImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
									
										
										<div class="span3">
											Select In House Verifier<font class="requiredFont">*</font>
											<select class="input-block-level" id="inHouseReleaseId"  multiple="true"> </select>
										</div>
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="updateLeaderInHouseReleation();">RELEASE</button><img id="processingImgForreleaseInhouseLeader" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img></div>

                            <div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							<!--<a class="btn btn-primary btn-large" href="javascript:{getComparisionReport()}">TEST</a>-->
							
							<!--<a class="btn btn-primary btn-large" href="javascript:{getDayWiseReport()}">TEST</a>-->
					</div>
				</div>
			</div>
		</div>
		
		<!---- User Verification Details ---->	
		
		<div class="row" id="verificationDiv"></div>
		<!-----LEADER NAME------->
		<div class="row" id="leaderNameDiv"></div>
		
		<!-----WEB MONITOR ------->
		<div class="row" id="webMonitorAssignUsersDivId">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>ASSIGN USERS TO WEB MONITORS</h4>
							<div class="row">
								<div id="webMonitorErrorId" class="span8 offset1 errorCls"></div>
							</div>
								<div class="row">
								<div class="span12 offset1">
									<div class="row-fluid">
										<div class="span3">
											Select User<font class="requiredFont">*</font>
											<s:select theme="simple"  style="width:180px;" name="user" headerValue="Select User" headerKey="0" id="webMonitorUserId" list="usersList" listKey="id" listValue="name"/>
										</div>
										<div class="span3">
											Select Constituency<font class="requiredFont">*</font>
											<s:select theme="simple"  name="constituency" headerValue="Select Constituency" headerKey="0" id="webMonitorConstituencyId" style="width:180px;" list="dataAvilableConstituencies" listKey="id" listValue="name" onchange="getAssignedUsersOfConstituency()" />
										</div>
										<div class="span4">
											Select User(s)<font class="requiredFont">*</font>
											 <select id="webMonitorUserIds" multiple="true"></select>
											
										</div>
										<div class="span1" style="margin:25px -33px 0 0px;width: 15px;">
										<img id="userImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>	
								</div>
								</div>
							
							
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="saveWebMonioringAssignDetails();">ASSIGN</button>
							<img src='./images/icons/search.gif' id="webMonioringAssignimg" style="display:none;"/></div>
							
							<!-- <div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="getSurveyBoothDetails();">Details</button>
							<div id="tableDtailsDiv"></div>-->
					</div>
				</div>
			</div>
		</div>
		
		
	</div>
<script>


$(".highlight").click(function()
{
	$(".highlight").removeClass("selected");
	$(this).addClass("selected");
})

var count = 1;
function assignNewTabDiv(){
count = count+1;
var str ='';

	str +='<div class="row-fluid"  id="newTabAssignDiv'+count+'">';
	str +='	<div class="span6">';
	str +='		Tab No<font class="requiredFont">*</font>';
	str +='		<input type="text" placeholder="Tab No..." class="input-block-level newTabCls" id="tabNo'+count+'">';
	str +='	</div>';
	str +='	<div class="span6">';
	str +='		Date<font class="requiredFont">*</font>';   	
	str +='		<input type="text" placeholder="Select Date..." class="input-block-level datePickerCls" id="date'+count+'" readOnly="true" style="width:270px;"> <a href="javascript:{clearDiv('+count+');}"> <span id="removeDivId" class="btn btn-danger" style="margin-top: -10px;" title="Click here to remove this tab details."> <b> - </b></span> </a>';
	str +='	</div>	';								
	str +='</div>';
		str +=' ';
$('#assignNewTabsDiv').append(str);	
   $('.datePickerCls').datepicker({
   dateFormat: 'dd-mm-yy'
   });
   	

}

</script>
</body>
</html>