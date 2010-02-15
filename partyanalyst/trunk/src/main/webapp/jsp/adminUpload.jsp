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
 <tr><td align = "right"><h4>To Upload Election Data :<a href="<s:url action="electionUpload"/>"> Upload </h4></a><br/></tr>
 <tr><td align = "right"><h4>To Upload Booth    Data :<a href="<s:url action="boothDataUpload"/>"> Upload </h4></a><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Booth Results :<a href="<s:url action="boothResultUpload"/>"> Upload </h4></a><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Voter    Data :<a href="<s:url action="voterDataUpload"/>"> Upload </h4></a><br/></td></tr>
 <tr><td align = "right"><h4>To Upload Problems Data :<a href="<s:url action="problemDataUpload"/>"> Upload </h4></a><br/></td></tr>
 <tr><td align = "right"><h4>To Upload MPTC/ZPTC Election Data :<a href="<s:url action="mptcElectionAction"/>"> Upload </h4></a><br/></td></tr>
 <tr><td align="right"><h4>Map Booths To Hamlets</h4><a href="<s:url action="hamletBoothMapperAction"/>"> Map </h4></a><br/></td></tr>
 </table>
 </BODY>
</HTML>