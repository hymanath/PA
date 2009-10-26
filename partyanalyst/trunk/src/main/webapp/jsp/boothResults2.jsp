<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="s" uri="/struts-tags" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<table class="searchresultsTable" style="width:auto;">
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

		<br/>
		<h4><u>Booth Wise Performance : </u></h4>
		<table class="searchresultsTable" style="width:auto;text-align:center;">		
				<tr>
					<th>Booth No</th>
					<th>Location</th>
					<th>Villages Covered</th>
					<th>Votes Earned</th>
					<th>Total Voters</th>
					<th>Votes %</th>
				</tr>
				<c:forEach var="booth" items="${boothResult.boothResults}">
					<tr>
						<td style="text-align: center;">${booth.partNo}</td>
						<td style="text-align: left;">${booth.location}</td>
						<td style="text-align: left;">${booth.villagesCovered}</td>
						<td style="text-align: center;">${booth.votesEarned}</td>
						<td style="text-align: center;">${booth.totalVoters}</td>
						<td style="text-align: center;">${booth.percentage}</td>
					</tr>
				</c:forEach>	
		</table>
		</div>
</body>
</html>