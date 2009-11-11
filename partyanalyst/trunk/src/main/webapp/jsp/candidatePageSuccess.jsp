<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Details</title>
<style type="text/css">
table.CandidateElectionResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #94A9C8;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

#candidateDetailsTable
{
	float: left;
	margin-left: 70px;
}

#candidateDetailsTable th
{
	padding:8px;
	color:#8A9194;
}

#candidateDetailsTable td
{
	padding:8px;
	color:#3A4750;
}

#moreDetailsAnc
{
	text-decoration:none;
	color:#64941D;
}


</style>
<script type="text/javascript">
	
	function showDetails(id)
	{	
		tr=document.getElementsByTagName('tr')

		for (i=0;i<tr.length;i++)
		{			
			if (tr[i].id && tr[i].id==id)
			{				
				if ( tr[i].style.display=='none' )
				{
					tr[i].style.display = '';
				}
				else
				{
					tr[i].style.display = 'none';
				}
			}
		}		
	}
	
</script>
</head>
<body>
<div id="ResultsDiv" style="padding:5px;">
   
	<h2><u style="color:#0C3E57;">Candidate Details</u></h2>
    <table id="candidateDetailsTable" style="">
		<tr>
			<th>Name</th>
			<td><c:out value="${candidateVO.candidateName}"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><a id="moreDetailsAnc" href="javascript:{}">More Details... </a></td>
		</tr>
	</table>

	<div style="background-color: #efefef; height: 180px; width: 200px; margin-left:50px;display:inline;">
				<img  height="180" width="200" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" >
	</div>
	
	
	<h4 style="text-align:left;"><u style="color:#0C3E57;"><c:out value="${candidateVO.candidateName}"/>  Election Info</u></h4>
	
	<table class="CandidateElectionResultsTable" border="1">
	<tr>
		<th>Candidate Name</th>
        <th>Party Name</th>
		<th>Constituency Name</th>
		<th>Election Type</th>
		<th>Election Year</th>
		<th>District Name</th>
		<th>State Name</th>
		<th>Votes Earned</th>
		<th>Votes Percentage</th>
		<th>status</th>
		<th></th>

	</tr>
	<c:forEach var="candidateElectionResults" items="${candidateElectionDetails}" >		
	<tr>
		<td><c:out value="${candidateElectionResults.candidateName}"/></td>
		<td><c:out value="${candidateElectionResults.partyName}"/></td>
		<td><c:out value="${candidateElectionResults.constituencyName}"/></td>
		<td><c:out value="${candidateElectionResults.electionType}"/></td>
		<td><c:out value="${candidateElectionResults.electionYear}"/></td>
		<td><c:out value="${candidateElectionResults.districtName}"/></td>
		<td><c:out value="${candidateElectionResults.stateName}"/></td>
		<td><c:out value="${candidateElectionResults.votesEarned}"/></td>
		<td><c:out value="${candidateElectionResults.votesPercentage}"/></td>
		<c:if test="${candidateElectionResults.status == true }">
		 <td><c:out value="Won"/></td>
		</c:if>
		<c:if test="${candidateElectionResults.status == false }">
		 <td><c:out value="Lost"/></td>
		</c:if>
		<td><a href="javascript:showDetails(${candidateElectionResults.electionId})">More Details</a></td>
	</tr>
	
	<tr id="${candidateElectionResults.electionId}" style="display:none;">
	  <th colspan="11" align="center">Complete Results Of <c:out value="${candidateElectionResults.constituencyName}" /> Constituency <c:out value="${constituencyElectionResults.electionType}"/> <c:out value="${constituencyElectionResults.electionYear}"/> Election</th>
	</tr>
	 	<tr id="${candidateElectionResults.electionId}" style="display:none;">
 
		<td ><b>Election Type</b></td>
		<td ><b>Year</b></td>
		<td><b>Constituency</b></td>
		<td colspan="2"><b>Candidate Name</b></td>
		<td colspan="3"><b>Party Name</b></td>
		<td ><b>Votes Earned</b></td>
		<td ><b>Votes Percentage</b></td>
		<td><b>Status</b></td>
	</tr>
	<c:forEach var="detailedResult" items="${candidateElectionResults.oppositionCandidates}" >
	<tr id="${candidateElectionResults.electionId}" style="display:none;">
		<td ><c:out value="${candidateElectionResults.electionType}"/></td>
		<td ><c:out value="${candidateElectionResults.electionYear}"/></td>
		<td ><c:out value="${candidateElectionResults.constituencyName}"/></td>
		<td colspan="2"><c:out value="${detailedResult.candidateName}"/></td>
		<td colspan="3"><c:out value="${detailedResult.partyName}"/></td>
		<td ><c:out value="${detailedResult.votesEarned}"/></td>
		<td ><c:out value="${detailedResult.votesPercentage}"/></td>
		<c:if test="${detailedResult.status == true }">
		 <td><c:out value="Won"/></td>
		</c:if>
		<c:if test="${detailedResult.status == false }">
		 <td><c:out value="Lost"/></td>
		</c:if>
	</tr>
	</c:forEach>
	<tr id="${candidateElectionResults.electionId}" style="display:none;">
       <th colspan="11" align="center"> . </th>
	</tr>
	</c:forEach>
</table><br>

</div>

</body>
</html>
  