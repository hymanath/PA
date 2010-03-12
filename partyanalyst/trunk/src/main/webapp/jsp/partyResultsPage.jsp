<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Results</title>
<script type="text/javascript">
	function showOppostionDeatils(year)
	{	
		tr=document.getElementsByTagName('tr')

		for (i=0;i<tr.length;i++)
		{			
			if (tr[i].id && tr[i].id==year)
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
<center>
	<H3>
		<u align="centre"> <c:out value="${selectedPartyShortName}"/>  Party Result for <br/></u>
		<u align="centre"> <c:out value="${selectedLocationName}"/>   <c:out value="${reportLevel}"/> <c:out value="${selectedElectionTypeName}"/> Elections</u>
    </H3>
</center>  

<IMG id="chartImg" SRC="charts/<%= request.getAttribute("chartName")%>" WIDTH="450" HEIGHT="400">   

<div id="partyResultsDiv" style="padding:5px;">
	
		<table class="searchresultsTable" border="1">
	<tr>
		<th>Year</th>
        <th>Party</th>
		<th>Participated In</th>
		<th>Seats Win</th>
		<th>% of Votes</th>
		<th></th>

	</tr>
	<c:forEach var="partyResultInfoVO" items="${partyResultInfoVOs}" >		
	<tr>
		<td><c:out value="${partyResultInfoVO.partyInfoVO.electionYear}"/></td>
		<td><c:out value="${partyResultInfoVO.partyInfoVO.partyShortName}"/></td>
		<td><c:out value="${partyResultInfoVO.partyInfoVO.seatsParticipated}"/></td>
		<td><c:out value="${partyResultInfoVO.partyInfoVO.seatsWin}"/></td>
		<td><c:out value="${partyResultInfoVO.partyInfoVO.percentageOfVotes}"/></td>
		<td><a href="javascript:showOppostionDeatils(${partyResultInfoVO.partyInfoVO.electionYear})">Opposition Details</a></td>
	</tr>
	
	<c:forEach var="oppParty" items="${partyResultInfoVO.oppositionPartyInfo}" >
	<tr id="${partyResultInfoVO.partyInfoVO.electionYear}" style="display:none;">
		<td>&nbsp;</td>
		<td><c:out value="${oppParty.partyShortName}"/></td>
		<td><c:out value="${oppParty.seatsParticipated}"/></td>
		<td><c:out value="${oppParty.seatsWin}"/></td>
		<td><c:out value="${oppParty.percentageOfVotes}"/></td>
		<td>&nbsp;</td>
	</tr>
	</c:forEach>
	
	</c:forEach>
</table><br>
</div>



</body>
</html>