<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="s" uri="/struts-tags" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@taglib uri="http://displaytag.sf.net" prefix="display" %>
   <%@page import="com.itgrids.partyanalyst.excel.PartyBoothPerformanceVO" %>
<%@page import="com.itgrids.partyanalyst.excel.BoothResultVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booth Results</title>
<style type="text/css">
	#boothResultsDiv
	{
		text-align:left;
		margin-left:50px;
		font-size:12px;
	}
</style>
</head>
<body>
		<h3><u>Booth Results</u></h3> 
		<div id="boothResultsDiv">
		<h4><u>Candidate Details : </u></h4>
		<table class="searchresultsTable" style="width:auto;float:left;">
			<tr>
				<th> Party Name</th>
				<td><s:property value="boothResult.partyName"/></td>
			</tr>
			<tr>
				<th>Candidate Name</th>
				<td><s:property value="boothResult.candidateName"/></td>
			</tr>
			<tr>
				<th> Constituency Name</th>
				<td><s:property value="boothResult.constituencyName"/></td>
			</tr>
			<tr>
				<th> Election Type</th>
				<td><s:property value="boothResult.electionType"/></td>
			</tr>
			<tr>
				<th> Election Year</th>
				<td><s:property value="boothResult.electionYear"/></td>
			</tr>
		</table>
		<table style="padding-left:100px;padding-bottom:100px;">
			<tr>
				<th> Total Valid votes</th>				
				<td><s:property value="boothResult.totalValidVotes"/></td>
			</tr>
			<tr>
				<th> Total Votes Gained</th>				
				<td><s:property value="boothResult.votesGained"/></td>
			</tr>
			<tr>
				<th> Total Percentage</th>				
				<td><s:property value="boothResult.percentage"/></td>
			</tr>
		</table>
		<br/>
		<div>
			<h4><u>Booth Wise Performance : </u></h4>

				<display:table class="searchresultsTable" name="${boothResult.boothResults}" defaultorder="ascending" defaultsort="4" style="width:auto;margin-right:20px;"> 
					<display:column style="text-align: center;" title="Booth No" property="partNo" />
					<display:column style="text-align: left;" title="Location" property="location" />
					<display:column style="text-align: left;" title="Villages Covered" property="villagesCovered" />
					<display:column style="text-align: center;" title="Mandal" property="mandal" />
					<display:column style="text-align: center;" title="Votes Earned" property="votesEarned" />
					<display:column style="text-align: center;" title="Total Voters" property="totalVoters" />
					<display:column style="text-align: center;" title="Percentage" property="percentage" />
				</display:table>

			</div>
	</div>
</body>
</html>