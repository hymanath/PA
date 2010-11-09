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
	.l2 {
		
		font-size:12px;
		padding:5px;
		font-weight: bold;		
	}
	.bodyClass
	{
		text-color:#707070;
	}
	</style>
  </HEAD>
  <BODY class="bodyClass">
  <h2><u>PartyAnalyst Admin</u></h2>
<div id="contentDiv">
	<table>
		<tr>
			<td>
				<fieldset class="f1">
					<legend class="l1">Party Analyst Utilities Admin</legend>
						<table border="0" width="100%">
							<tr>
								<td>
									<fieldset class="f2">
									<legend class="l2">User Registration</legend>
									<h4>Register a new user:<a href="<s:url action="userRegPageAction"/>">Register</a></h4>
									</fieldset>
								</td>
								<td>
									<fieldset class="f2">
									<legend class="l2">Configure Opinion Polls</legend>
									<h4>Add Opinion Poll:<a href="<s:url action="opinionPollPost"/>"><b>&nbsp;Add Opinion Poll</b></a></h4>
									</fieldset>
								</td>
								<td>
									<fieldset class="f2">
									<legend class="l2">Scrutinize Problems</legend>
									<h4>Accept/Reject recent problems posted:<a href="<s:url action="problemManagementAdminAction"/>"><b>&nbsp;Recent Problems</b></a></h4>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
									<fieldset class="f2">
									<legend class="l2">Entitlements</legend>
									<h4>Organise Entitlements:<a href="<s:url action="userEntitlementAction"/>">organise</a></h4>
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
									<h4>Upload Assembly/Parliament Elections Data :<a href="<s:url action="electionUpload"/>">Upload</a></h4>
									<h4>Upload MPTC/ZPTC/Municipal Elections Data :<a href="<s:url action="mptcElectionAction"/>">Upload </a></h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Booth Data Upload</legend>
									<h4>Upload Booth Data :<a href="<s:url action="boothDataUpload"/>"> Upload </a></h4>
									<h4>Upload Booth Results :<a href="<s:url action="boothResultUpload"/>"> Upload </a></h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Mapping(Upload Files)</legend>
									<h4>Upload Booth Village Mapping Data<a href="<s:url action="villageBoothMapperAction"/>">Upload</a></h4>
									</fieldset>
								</td>								
							</tr>
							<tr>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Mapping(User Interface)</legend>
									<h4>Map Booths To Revenue Village<a href="<s:url action="hamletBoothMapperAction"/>"> Map </a></h4>
									<h4>Map Booths To Local Election Body Wards<a href="<s:url action="municipalWardsAssemblyBoothsMapperAction"/>"> Map </a></h4>
									<h4>Map Villages To Booths <a href="<s:url action="villageBoothMapperAction"/>"> Map </a></h4>
									</fieldset>
								</td>								
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Voters Data Upload</legend>
									<h4>To Upload Voter    Data :<a href="<s:url action="voterDataUpload"/>"> Upload </a></h4>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset class="f2" style="height:150px;">
									<legend class="l2">Problems Upload</legend>
									<h4>To Upload Problems Data :<a href="<s:url action="problemDataUpload"/>"> Upload </a></h4>
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