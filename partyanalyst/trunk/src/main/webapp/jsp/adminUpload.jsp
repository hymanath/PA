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
		width: 940px;
		padding: 10px;
		float: none;
	    margin-left: auto;
	    margin-right: auto;
		background:#FFF;
		margin-bottom: 15px;
		border-radius: 3px;
	}
	.f1 {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 850px;
		margin-left: 30px;
	}
	.l1 {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		padding:5px;
		font-weight: bold;
		margin-bottom: 0px;
		padding: 0px 0px 0px 9px;
		border-bottom-width: 0px;
		line-height: 30px;
	}
	.f2 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 200px;
		margin-left:30px;
			
	}
	.f3 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 340px;
		height: 217px;
	}
	.l2 {
		color:navy;
		font-size:12px;
		padding:5px;
		font-weight: bold;
		padding-left: 11px;
		padding-right: 6px;
		border-bottom-width: 0px;
		height: 10px;
		font-size: 14px;
		
	}
	legend {
	width:auto;
	line-height: normal;
	margin-bottom:10px;
	}
	.bodyClass
	{
		text-color:#707070;
	}
	h4.tdstyle{
	color: #5E5A80;
    font-weight: bold;
	line-height: 2em;
	font-size: 13px;
	}
	h4.tdsubstyle{
	color: #AD56A9;
	font-weight: bold;
	font-size: 14px;
	}
	a.alinkstyle 
	{
	color:#168787;
	}
	 #headingStyle{
     background-color: #567AAF;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    margin-top: 11px;
    text-align: center;
    width: 172px;
	margin-bottom: 25px;
	margin-left:auto;
	margin-right:auto;
	float:none;
	border-radius: 5px;
	}
	.f4 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 340px;
		height: 217px;
		margin-left: 44px;
		
		
	}
	.f5 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 340px;
		height: 400px;
	    margin-right: 4px;
		margin-left: 44px;
		
	}
	.f6 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 340px;
		height: 400px;
	}
	.f0 {
		border:2px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;
		width: 340px;
		
		margin-left: 42px;
		
		
	}
	.fontStyle
	{
		font-weight:bold;
		line-height:2em;
		font-size: 13px;
		
	}
	</style>
	<script type="text/javascript">
	 
	</script>
  </HEAD>
  <BODY class="bodyClass">
	<div id="geadingDiv"> 
		<h2 id ="headingStyle">PartyAnalyst Admin</h2>
	</div>
<div id="contentDiv">
	<table>
		<tr>
			<td>
				<fieldset class="f1">
					<legend class="l1" style="width:170px;">PartyAnalyst Utilities Admin</legend>
					  	<table border="0" width="100%">
							<tr>
							    <td rowspan="1" >
									<fieldset class="f5">
									<legend class="l2" style=" width: 121px;">Manage Customers</legend>
									<span class="fontStyle">Register a new user&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="userRegPageAction?registrationType=mainUser"/>">Register</a>
									</span><br>
									<span class="fontStyle">Organise Entitlements&nbsp;:&nbsp;&nbsp;
										<a  class="alinkstyle" href="<s:url action="userEntitlementAction"/>">organise</a>
									</span><br>
									<span class="fontStyle">Manage User Region Access&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="userAccessRegionAction"/>">Manage</a>
									</span><br>
									<span class="fontStyle">Manage User Sms Access&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="manageUserMessageCreditsAction"/>">Manage</a>
									</span><br>
									<span class="fontStyle">Assign Candidates To User&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="assigningCandidatesToUserAction"/>">Assign</a>
									</span><br>
									<span class="fontStyle">Assign Party To User&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="assigningPartyToUserAction"/>">Assign</a>
									</span><br>
									<span class="fontStyle">
										Restrict User :&nbsp;
										<a class="alinkstyle" href="<s:url action="setUserRestrictionAction"/>"> Restrict User
										</a>
									</span><br>
									<span class="fontStyle">Assign Sub User&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="assignSubUserAction"/>">Assign</a>
									</span>
									</fieldset>
								</td>
								
								
								<td >
									<fieldset class="f6">
									<legend class="l2" style="width:110px;">Admin Approvals</legend>
									<h4 class="tdsubstyle" style="margin-bottom: 5px;">Problem Management</h4>

									<span class="fontStyle" style="margin-left: 24px;">Accept/Reject recent problems posted<br>
										<span style="margin-left: 108px;">:&nbsp;&nbsp;
											<a class="alinkstyle" href="<s:url action="problemManagementAdminAction"/>"><b>&nbsp;Recent Problems :&nbsp;(${problemBeanVO.problemsCount})</b>
											</a>
										</span> 
									</span><br>
									<span class="fontStyle" style="margin-left: 53px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
										<a style="color:#168787;" href="<s:url action="imageManagementAdminAction"/>"><b>&nbsp;
										Related Images :&nbsp;(${problemBeanVO.imageCount})</b>
										</a>
									</span>
									
									<span class="fontStyle" style="margin-bottom: 15px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Approval/Reject Recently Posted Comments on &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;problems
										&nbsp;:
										<a class="alinkstyle" href="<s:url action="problemApprovalAdminAction"/>"><b>&nbsp;Approval :&nbsp;(${problemBeanVO.discommentCount})</b>
										</a>

									</span><br>
									
									<span class="fontStyle" style="margin-left: 28px;">Abuse Comments&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href='<s:url action="abuseCommentaction"/>'>Click Here</a>
									</span>

									<h4 class="tdsubstyle" style="margin-bottom: 10px;">Other Approvals
									</h4>
									<span class="fontStyle" style="margin-left: 26px;">Accept/Reject Recent posted comments on 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Political Reasons&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="commentsControlAdminAction"/>"><b>&nbsp;Posted Comments :&nbsp;(${problemBeanVO.politicalCount})</b>
										</a>
									</span><br>
									<!-- <h4 class="tdstyle">Accept/Reject Recently Posted Feedbacks&nbsp;:&nbsp;&nbsp;<a class="alinkstyle" href="<s:url action="feedbackAdminApprovalAction"/>"><b>&nbsp;&nbsp; Posted Feedbacks :&nbsp;(${problemBeanVO.feedBackCount})</b></a></h4>-->
									<span class="fontStyle" style="margin-left: 26px;">
										Accept/Reject Recently Posted Feedbacks<br>
										<span style="margin-left:90px;">:&nbsp;&nbsp;<a href="feedbackAdminApprovalAction.action" class="alinkstyle"><b>&nbsp;Posted Feedbacks :&nbsp;(${problemBeanVO.feedBackCount})</b></a>
										</span>
									</span><br>

									<span class="fontStyle">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Accept/Reject Recently Posted Messages For &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Candidate/Party
										<span>:&nbsp;&nbsp;<a href='<s:url action="approveMessageAction"/>'  class="alinkstyle"><b>&nbsp;Posted Feedbacks :&nbsp;(${problemBeanVO.candidateMsgCount})/(${problemBeanVO.partyMsgCount})</b></a>
										</span>
									</span>

									</fieldset>
								</td>
							    
                            </tr>
							<tr>
							    <td> <fieldset class="f4">
                                    <legend class="l2" style="width:135px;">Manage Profile Pages</legend>
                                    <span class="fontStyle">
										To Manage Special Pages : <a class="alinkstyle" href='<s:url action="specialPageManageAction"/>'><b>Click Here</b></a>
									</span><br>
									<span class="fontStyle">
										To Manage Party Pages :
										<a class="alinkstyle" href='<s:url action="partyManagementAction"/>'><b>Click Here</b></a>
									</span><br>
									<span class="fontStyle">
										To Manage Custom Pages For Special Pages :
										<a class="alinkstyle" href='<s:url action="customPagesAction"/>'><b>Click Here</b></a>
									</span><br>
									<span class="fontStyle">
										To Manage Candidate Profiles :
										<a class="alinkstyle" href='<s:url action="profileManagePageAction"/>'><b>Click Here</b></a>
									</span><br>
									
                                    </fieldset>
                                </td>
								
						        <td>
									<fieldset class="f3">
									<legend class="l2" style="width:86px;">Opinion Polls</legend>
									 <span class="fontStyle">Add Opinion Poll&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="opinionPollPost"/>"><b>&nbsp;Add Opinion Poll</b>
									</a>
									</span><br>
									<!-- <h4 class="tdstyle">AddNewQuestion&nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href='<s:url action="homePageQuestionsAction"/>'><b>&nbsp;&nbsp;Add Question</b>
										</a>
									</h4>-->
									<span class="fontStyle">AddHomePageQuestion&nbsp;:
										<a class="alinkstyle" href='<s:url action="homePageQuestionsAction"/>'><b>&nbsp;Home Page(Presently not using)</b>
										</a>
									</span><br>
									<span class="fontStyle">Send Updates To Subscribed Users&nbsp;:
										<a class="alinkstyle" href='<s:url action="dailyUpdatesAction"/>'><b>&nbsp;Send Updates</b>
										</a>
									</span>
								    </fieldset>
								</td>
								
						  </tr>
						  
						  <tr>
						     <td> <fieldset class="f0">
                                    <legend class="l2" style="width:145px;">User Tracking Analysis</legend>
                                    <center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="userTrackingReportAction"/>'><b>View User Tracking Details</b></a></h4></center>
									<center><h4 class="tdstyle"><a class="alinkstyle" href='<s:url action="memoryManagementAction"/>'><b>Memory Moniter</b></a></h4></center>
                                    </fieldset>
                                </td>
								<td> <fieldset class="f3" style="height:88px;">
                                    <legend class="l2" style="width:65px;">Send Sms</legend>
                                   <a class="alinkstyle" href='<s:url action="sendUpdatesAction"/>'><b>Send SMS To Free User</b></a><br><br>
								   <a class="alinkstyle" href='<s:url action="sendUpdatesByemailAction"/>'><b>Send Emails To  User</b></a>
									
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
					<legend class="l1" style="width:272px;">Upload Data To Party Analyst Database Schema</legend>
					<table border="0" width="99%">
							<tr>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Election Data Upload</legend>
									<span class="fontStyle">
										Upload Assembly/Parliament Elections Data :&nbsp;
										<a class="alinkstyle" href="<s:url action="electionUpload"/>">Upload
										</a>
									</span><br>
									<span class="fontStyle">
										Upload MPTC/ZPTC/Municipal Elections Data :&nbsp;
										<a class="alinkstyle" href="<s:url action="mptcElectionAction"/>">Upload 
									</span><br>
									</h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Booth Data Upload</legend>
									<span class="fontStyle">
										Upload Booth Data :&nbsp;
										<a class="alinkstyle"  href="<s:url action="boothDataUpload"/>"> Upload
										</a>
									</span><br>
									<span class="fontStyle">
										Upload Booth Results :&nbsp;
										<a class="alinkstyle" href="<s:url action="boothResultUpload"/>"> Upload
										</a>
									</span><br>
									
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Mapping(Upload Files)</legend>
									<span class="fontStyle">
										Upload Booth Village Mapping Data :&nbsp;
										<a class="alinkstyle" href="<s:url action="villageBoothMapperUploadAction"/>">Upload
										</a>
									</span><br>
									<span class="fontStyle">
										Upload Booth Hamlet Publication Mapping Data :&nbsp;
										<a class="alinkstyle" href="<s:url action="boothPublicationUploadAction"/>">Upload
										</a>
									</span><br>
									</fieldset>
								</td>								
							</tr>
							<tr>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2" >Mapping(User Interface)</legend>
									<span class="fontStyle">
										Map Booths To Revenue Village <span style="margin-left:124px;">:&nbsp;
										<a class="alinkstyle" href="<s:url action="hamletBoothMapperAction"/>"> Map 
										</a></span>
									</span><br>
									<span class="fontStyle">
										Map Booths To Local Election Body Wards :&nbsp;
										<a class="alinkstyle" href="<s:url action="municipalWardsAssemblyBoothsMapperAction?windowTask=new&constituencyId=0"/>"> Map 
										</a>
									</span><br>
									<span class="fontStyle">Map Villages To Booths :&nbsp;
										<a class="alinkstyle" href="<s:url action="villageBoothMapperAction"/>"> Map 
										</a>
									</span><br>
									</fieldset>
								</td>								
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Voters Data Upload</legend>
									<span class="fontStyle">To Upload Voter    Data :&nbsp;
										<a class="alinkstyle" href="<s:url action="voterDataUpload"/>"> Upload
										</a>
									</span><br>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Problems Upload</legend>
									<span class="fontStyle">To Upload Problems Data 
									<br><span>:&nbsp;
										<a class="alinkstyle" href="<s:url action="problemDataUpload"/>"> Upload 
										</a></span>
									</span><br>
									</fieldset>
								</td>
							</tr>
							<tr>
							    <td>
									<fieldset class="f2">
									<legend class="l2">Delimitation Census Upload</legend>
									<span class="fontStyle">Upload Delimitation Mapping Data For Constituencies &nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="delimitationMappingsUpload"/>"><b>Start Mapping</b>
										</a>
									</span><br>
									</fieldset>
								</td>
								<td>
                                    <fieldset class="f2">
                                    <legend class="l2" style="padding-bottom: 18px;">Constituency Wise Census Mapping</legend>
                                    <span class="fontStyle">
										To Map the Census Details For Constituencies &nbsp;:&nbsp;
										<a class="alinkstyle" href="<s:url action="constituencyWiseCensusMappingAction"/>"><b>Start Mapping</b>
										</a>
									</span><br>
                                    </fieldset>
                                </td>
								<td> <fieldset class="f2">
                                    <legend class="l2" style="padding-bottom: 18px;">Assign Governing Body Positions/Ministers</legend>
                                    
										<span class="fontStyle" style="margin-left: 30px;">
										  <a class="alinkstyle" href='<s:url action="assignCandidateToElectionAction"/>'><b>Assign Candidate</b>
										</a>
									</span>
								  
                                    </fieldset>
                                </td>
							</tr>
								<tr> 
							 <td>
									<fieldset class="f2">
									<legend class="l2">Towards Maintanace</legend>
									<span class="fontStyle">Remove youtube videos &nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="removeVideosAction"/>"><b>RemoveVideos</b>
										</a>
									</span><br>
									</fieldset>
								</td>
							
						
							 <td>
									<fieldset class="f2">
									<legend class="l2">Towards ImagesAppearance</legend>
									<span class="fontStyle">Resize Images &nbsp;:&nbsp;&nbsp;
										<a class="alinkstyle" href="<s:url action="createThumbNailsAction"/>"><b>createThumbNails</b>
										</a>
									</span><br>
									</fieldset>
								</td>

								<td>
									<fieldset class="f2">
									<legend class="l2">Upload SpecialPage Data</legend>
									
										<a class="alinkstyle" href="<s:url action="specialPageDataUploadAction"/>"><b>Upload</b>
										</a>
									</span><br>
									</fieldset>
								</td>
								</tr>
								<tr>
								<td><fieldset class="f2">
									<legend class="l2">Voter Data Menu</legend>
									<span class="fontStyle">
										Voter Data Insert:&nbsp;
										<a class="alinkstyle" href="<s:url action="voterDataManageAction"/>"> Voter Data Insert
										</a>
									</span><br>
									<span class="fontStyle">
										Populate Voters Data:&nbsp;
										<a class="alinkstyle" href="<s:url action="populateVoterDataAction"/>"><b>
										Populate Voters Data To Intermediate Tables</b>
										</a>
									</span><br>
									<!-- Updated By Prasad For Adding Missing Voters-->
									<span class="fontStyle">
										Add Missing Voters:&nbsp;
										<a class="alinkstyle" href="<s:url action="missingVotersSearchAction"/>"><b>Add Missing Voters </b>
										</a>
									</span><br>
									</fieldset></td>
								<td>
								<fieldset class="f2">
									<legend class="l2">Voter Edit Menu</legend>
									<span class="fontStyle">
										Voter's Edit Form:&nbsp;
										<a class="alinkstyle" href="<s:url action="voterEditDataAction"/>"> Voter's Edit Form
										</a>
									</span><br>
									</fieldset></td>
								<td>
								<fieldset class="f2">
									<legend class="l2">Voter's	Validation tool</legend>
									<span class="fontStyle">
										Voter's	Validation tool:&nbsp;
										<a class="alinkstyle" href="<s:url action="validationToolsAction"/>"> Voter's	Validation tool	</a>
									</span><br>
									</fieldset></td>
								
								<td></td>
								</tr>
						</table>
				</fieldset>
			</td>
		</tr>
		
	</table>
</div>

 </BODY>
</HTML>