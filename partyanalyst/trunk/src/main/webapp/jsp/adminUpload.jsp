<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<HTML>
 <HEAD>
  <TITLE>Constituency Search</TITLE>
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
  </HEAD>
  <BODY>
  <h2><u>Welcome To Administrator Upload Page </u></h2>

 <table  style="width:310px;" border="0">
 <tr><td align = "right"><h4>Register :<a href="<s:url action="userRegPageAction"/>"> Register </a></h4><br/></td></tr>
 <tr><td align="right"><h4>To Manage Problems :<a href="<s:url action="problemManagementAdminAction"/>"><b>&nbsp; Post </b></a></h4><br/></td></tr> 
 <tr><td align="right"><h4>To Post a Opinion Poll :<a href="<s:url action="opinionPollPost"/>"><b>&nbsp; Post </b></a></h4><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Election Data :<a href="<s:url action="electionUpload"/>"> Upload </a></h4><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Booth    Data :<a href="<s:url action="boothDataUpload"/>"> Upload </a></h4><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Booth Results :<a href="<s:url action="boothResultUpload"/>"> Upload </a></h4><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Voter    Data :<a href="<s:url action="voterDataUpload"/>"> Upload </a></h4><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Problems Data :<a href="<s:url action="problemDataUpload"/>"> Upload </a></h4><br/></td></tr>
 <tr><td align = "right"><h4>To Upload MPTC/ZPTC/Muncipal Election Data :<a href="<s:url action="mptcElectionAction"/>"> Upload </a></h4><br/></td></tr>
 <tr><td align="right"><h4>Map Booths To Hamlets</h4><a href="<s:url action="hamletBoothMapperAction"/>"> Map </a><br/></td></tr>
 <tr><td align="right"><h4>Map Booths To Wards</h4><a href="<s:url action="municipalWardsAssemblyBoothsMapperAction"/>"> Map </a><br/></td></tr>
 <tr><td align="right"><h4>Upload Booth Village Mapping Data</h4><a href="<s:url action="villageBoothMapperAction"/>"> Upload </a><br/></td></tr>
 </table>
 </BODY>
</HTML>