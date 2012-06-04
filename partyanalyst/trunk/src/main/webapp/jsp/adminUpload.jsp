<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<HTML>
 <HEAD>
  <TITLE>PartyAnalyst Admin</TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">
  <!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js" ></script>	
	<script type="text/javascript" src="js/yahoo/datasource-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/get-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/connection-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/animation-min.js" ></script>	
	<script type="text/javascript" src="js/yahoo/json-min.js" ></script>	  
	<script type="text/javascript" src="js/yahoo/autocomplete-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	
	<link href="styles/yuiStyles/autocomplete.css" rel="stylesheet" type="text/css" />

	<!-- YUI Dependency files (End) -->
	<style>
	#contentDiv
	{
		border: 3px solid #707070;
		width: 800px;
		padding: 10px;
	}
	.f1 {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 750px;
	}
	.l1 {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		padding:5px;
		font-weight: bold;
	}
	.f2 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 200px;
		height: 75px;
		
		
	}
	.f3 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 229px;
		height: 217px;
	}
	.l2 {
		color:navy;
		font-size:12px;
		padding:5px;
		font-weight: bold;		
	}
	.bodyClass
	{
		text-color:#707070;
	}
	h4.tdstyle{

	color: #5E5A80;
    font-weight: bold;
	}
	h4.tdsubstyle{
	color: #AD56A9;
	font-weight: bold;
	font-size: 12px;
	
	}
	a.alinkstyle {
		color:#168787;
	}
	 #headingStyle{
     background-color: #567AAF;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    height: 25px;
    margin-top: 11px;
    text-align: center;
    width: 172px;
	}
	.f4 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 229px;
		height: 217px;
		margin-left: 68px;
		
		
	}
	.f5 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 229px;
		height: 352px;
	    margin-right: 4px;
		margin-left: 68px;
		
	}
	.f6 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 229px;
		height: 352px;
	}
	.f0 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 229px;
		height: 75px;
		margin-left: 68px;
		
		
	}
	</style>
	<script type="text/javascript">
	 
	</script>
  </HEAD>
  <BODY class="bodyClass">
  <h2 id ="headingStyle">PartyAnalyst Admin</h2>
<div id="contentDiv">
	<table>
		<tr>
			<td>
				<fieldset class="f1">
					<legend class="l1">PartyAnalyst Utilities Admin</legend>
					  	<table border="0" width="100%">
							<tr>
							    <td rowspan="1" >
									<fieldset class="f5">
									<legend class="l2">Manage Customers</legend>
									<h4 class="tdstyle">Register a new user&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="userRegPageAction?registrationType=mainUser"/>">Register</a></h4>
									<h4 class="tdstyle">Organise Entitlements&nbsp;:&nbsp;&nbsp;<a  class="alinkstyle" href="<s:url action="userEntitlementAction"/>">organise</a></h4>
									<h4 class="tdstyle">Manage User Region Access&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="userAccessRegionAction"/>">Manage</a></h4>
									<h4 class="tdstyle">Manage User Sms Access&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="manageUserMessageCreditsAction"/>">Manage</a></h4>
									<h4 class="tdstyle">Assign Candidates To User&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="assigningCandidatesToUserAction"/>">Assign</a></h4>
									</fieldset>
								</td>
								
								
								<td >
									<fieldset class="f6">
									<legend class="l2">Admin Approvals</legend>
									<h4 class="tdsubstyle">Problem Management</h4>
									<h4 class="tdstyle">Accept/Reject recent problems posted&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="problemManagementAdminAction"/>"><b>&nbsp;Recent Problems :&nbsp;(${problemBeanVO.problemsCount})</b></a> </h4>
									<h4 class="tdstyle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<a href="<s:url action="imageManagementAdminAction"/>"><b>&nbsp;Related Images :&nbsp;(${problemBeanVO.imageCount})</b></a></h4>
									<h4 class="tdstyle">Approval/Reject Recently Posted Comments on problems &nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="problemApprovalAdminAction"/>"><b>&nbsp;Approval :&nbsp;(${problemBeanVO.discommentCount})</b></a></h4>
									<h4 class="tdsubstyle">Other Approvals</h4>
									<h4 class="tdstyle">Accept/Reject recent posted comments on Political Reasons&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="commentsControlAdminAction"/>"><b>&nbsp;Posted Comments :&nbsp;(${problemBeanVO.politicalCount})</b></a></h4>
									<h4 class="tdstyle">Accept/Reject Recently Posted Feedbacks&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="feedbackAdminApprovalAction"/>"><b>&nbsp;&nbsp; Posted Feedbacks :&nbsp;(${problemBeanVO.feedBackCount})</b></a></h4>
									</fieldset>
								</td>
							    
                            </tr>
							<tr>
							    <td> <fieldset class="f4">
                                    <legend class="l2">Manage Profile Pages</legend>
                                    <center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="specialPageManageAction"/>'><b>Special Page Management</b></a></h4></center>
									<center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="partyManagementAction"/>'><b>Party Management</b></a></h4></center>
									<center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="customPagesAction"/>'><b>Maintain Custom Pages</b></a></h4></center>
									<center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="approveMessageAction"/>'><b>Manage Candidate profile</b></a></h4></center>
                                    </fieldset>
                                </td>
								
						        <td>
									<fieldset class="f3">
									<legend class="l2">Opinion Polls</legend>
									<h4 class="tdstyle">Add Opinion Poll&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="opinionPollPost"/>"><b>&nbsp;Add Opinion Poll</b></a></h4>
									<h4 class="tdstyle">AddNewQuestion&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href='<s:url action="homePageQuestionsAction"/>'><b>&nbsp;&nbsp;Add Question</b></a></h4>
									<h4 class="tdstyle">AddHomePageQuestion&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href='<s:url action="homePage.action"/>'><b>&nbsp;&nbsp;Home Page(Presently not using)</b></a></h4>
								    </fieldset>
								</td>
								
						  </tr>
						  
						  <tr>
						     <td> <fieldset class="f0">
                                    <legend class="l2">User Tracking Analysis</legend>
                                    <center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="userTrackingReportAction"/>'><b>View User Tracking Details</b></a></h4></center>
									<center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="memoryManagementAction"/>'><b>Memory Moniter</b></a></h4></center>
                                    </fieldset>
                                </td>
								<td> <fieldset class="f3" style="height:88px;">
                                    <legend class="l2">Send Sms</legend>
                                   <a class="alinkstyle" href='<s:url action="sendUpdatesAction"/>'><b>send Sms</b></a>
									
                                    </fieldset>
                                </td>
						  </tr>
							
							
							
                        </table>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset class="f1">
					<legend class="l1">Upload Data To Party Analyst Database Schema</legend>
					<table border="0" width="99%">
							<tr>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Election Data Upload</legend>
									<h4 class= "tdstyle">Upload Assembly/Parliament Elections Data :&nbsp;<a class="alinkstyle" href="<s:url action="electionUpload"/>">Upload</a></h4>
									<h4 class= "tdstyle" >Upload MPTC/ZPTC/Municipal Elections Data :&nbsp;<a class="alinkstyle" href="<s:url action="mptcElectionAction"/>">Upload </a></h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Booth Data Upload</legend>
									<h4 class= "tdstyle">Upload Booth Data :&nbsp;<a class="alinkstyle"  href="<s:url action="boothDataUpload"/>"> Upload </a></h4>
									<h4 class= "tdstyle" >Upload Booth Results :&nbsp;<a class="alinkstyle" href="<s:url action="boothResultUpload"/>"> Upload </a></h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Mapping(Upload Files)</legend>
									<h4 class= "tdstyle">Upload Booth Village Mapping Data :&nbsp;<a class="alinkstyle" href="<s:url action="villageBoothMapperUploadAction"/>">Upload</a></h4>
									</fieldset>
								</td>								
							</tr>
							<tr>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Mapping(User Interface)</legend>
									<h4 class= "tdstyle" >Map Booths To Revenue Village :&nbsp;<a class="alinkstyle" href="<s:url action="hamletBoothMapperAction"/>"> Map </a></h4>
									<h4 class= "tdstyle" >Map Booths To Local Election Body Wards :&nbsp;<a class="alinkstyle" href="<s:url action="municipalWardsAssemblyBoothsMapperAction?windowTask=new&constituencyId=0"/>"> Map </a></h4>
									<h4 class= "tdstyle" >Map Villages To Booths :&nbsp;<a class="alinkstyle" href="<s:url action="villageBoothMapperAction"/>"> Map </a></h4>
									</fieldset>
								</td>								
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Voters Data Upload</legend>
									<h4 class= "tdstyle" >To Upload Voter    Data :&nbsp;<a class="alinkstyle" href="<s:url action="voterDataUpload"/>"> Upload </a></h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Problems Upload</legend>
									<h4 class= "tdstyle" >To Upload Problems Data :&nbsp;<a class="alinkstyle" href="<s:url action="problemDataUpload"/>"> Upload </a></h4>
									</fieldset>
								</td>
							</tr>
							<tr>
							    <td>
									<fieldset class="f2">
									<legend class="l2">Delimitation Census Upload</legend>
									<h4 class="tdstyle">Upload Delimitation Mapping Data For Constituencies &nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="delimitationMappingsUpload"/>"><b>Start Mapping</b></a></h4>
									</fieldset>
								</td>
								<td>
                                    <fieldset class="f2">
                                    <legend class="l2">Constituency Wise Census Mapping</legend>
                                    <h4 class="tdstyle">To Map the Census Details For Constituencies &nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="constituencyWiseCensusMappingAction"/>"><b>&nbsp;Start Mapping</b></a></h4>
                                    </fieldset>
                                </td>
								<td> <fieldset class="f2">
                                    <legend class="l2">Assign Governing Body Positions/Ministers</legend>
                                    <center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="assignCandidateToElectionAction"/>'><b>Assign Candidate</b></a></h4></center>
                                    </fieldset>
                                </td>
							</tr>
							
							
						</table>
				</fieldset>
			</td>
		</tr>
		
	</table>
</div>

 </BODY>
</HTML>