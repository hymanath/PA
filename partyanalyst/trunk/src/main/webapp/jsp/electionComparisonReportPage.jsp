<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionsComparisonVO " %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionComparisonResultVO" %>
<%@page import="com.itgrids.partyanalyst.dto.PartyElectionResultsVO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Comparison Report</title>
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

</style>
<script type="text/javascript">
	
	function showComparisonDetails(id)
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
	
	function showTableDiv(val)
	{
		var index = val.indexOf("_");
		var subStr = val.substring(0,index);
		var elmt=document.getElementById(subStr+"Div");
		var aelmt=document.getElementById(val);

		if(elmt.style.display=='none')
		{
			aelmt.innerHTML="close";
			elmt.style.display='block';
		}
		else
		{
			aelmt.innerHTML="View";
			elmt.style.display='none';
		}
	}
</script>
</head>
<body>
       <h2>Elections Comparison Report</h2>
	   <br/>
	   	<c:if test="${electionComparisonResultVO != null }">
		<div style="text-align:left;margin-left:100px;margin-right:10px;">
		<b>Party Participated In Only One Election</b>
		
		<table class="CandidateElectionResultsTable"  width="600px">
		    <tr>
			   <th align="center" colspan = "5">District Wise Complete Results</th>
			</tr>  
			<c:forEach var="comparison" items="${electionComparisonResultVO}">
				<tr>
					<td  align="left"><b>${comparison.districtName}</b></td>
					<td colspan="4"  align="center"> <a href="javascript:{}" id="${comparison.districtId}" onclick="showComparisonDetails(this.id)"> View Results</a></td>
				</tr>	
				<tr id="${comparison.districtId}" style="display:none;">
					<td colspan="5"  align="center">
					    <b>Complete Results </b>
					</td>
				</tr>
				<tr id="${comparison.districtId}" style="display:none;">					
					<th>Candidate Name</th>
					<th>Constituency Name</th>
					<th>Votes Earned</th>
					<th>Status</th>
					<th>Votes Percentage</th>
					
				</tr>				
					<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
					<tr id="${comparison.districtId}" style="display:none;">						
						<td><c:url value="candidateElectionResultsAction.action" var="candidateName">
			            <c:param name="candidateId"   value="${partyResults.candidateId}" />
						</c:url>
						<a href='<c:out value="${candidateName}" />'>${partyResults.candidateName}</a></td>
						<td><c:url value="constituencyPageAction.action" var="constituencyName">
			            <c:param name="constituencyId"   value="${partyResults.constituencyId}" />
						</c:url>
						<a href='<c:out value="${constituencyName}" />'>${partyResults.constituencyName}</a></td>
						<td>${partyResults.votesEarned}</td>
						<c:if test="${partyResults.rank == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rank != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
						<td>${partyResults.votesPercentage}<c:out value="%" /></td>
					</tr>	
					
					</c:forEach>
					<tr id="${comparison.districtId}" style="display:none;">
				      <th colspan="5"  align="center">		    
					 </th>
				  </tr>
				</c:forEach>
			
			</table>
			</div>
		</c:if>

        <c:if test="${electionsComparisonVO != null }">
		 <div style="text-align:left;margin-left:200px;margin-right:10px;">

		   <div>
            <table border = "0" >
            <tr>
			  <th align="left">Party Name </th>
			  <td align="left"><c:out value=" : ${electionsComparisonVO.partyName}"/></td>
			
			  <th style="padding-left:20px;">Elections Years Compared </th>
			  <td ><c:out value=" : ${electionsComparisonVO.firstYear}"/><c:out value=" - " /><c:out value="${electionsComparisonVO.secondYear}"/></td>
			</tr>
			<tr>
			</tr>
		   </table>
		</div>
        <div>
		<br/>
		 <table class="CandidateElectionResultsTable" width="350px">
		    <tr>
			   <th align="center"> Election Year </th>
		       <th align="center"> Total Votes Percentage </th>
			   <th align="center"> Total Seats Won </th>
		    </tr>
			<tr>
			  <td align="center">${partyResultsPercentageForYear1.year}</td>
			  <td align="center">${partyResultsPercentageForYear1.percentage} %</td>
			  <td align="center">${partyResultsPercentageForYear1.seatsWOn}</td>
			</tr>
			<tr>
			  <td align="center">${partyResultsPercentageForYear2.year}</td>
			  <td align="center">${partyResultsPercentageForYear2.percentage} %</td>
			  <td align="center">${partyResultsPercentageForYear2.seatsWOn}</td>
			</tr>
	   </table>
       </div>
		<div>
			<h4><u>District Wise Results</u></h4>
			<span>  Votes % Gained  - <c:out value="${electionsComparisonVO.votesGainedCount}"/></span> <a id="votesGained_Anc" href="javascript:{}" onclick="showTableDiv(this.id)"> View</a>
			<span style="margin-left:20px;">  Votes % Lost  - <c:out value="${electionsComparisonVO.votesLostCount}"/><a  id="votesLost_Anc" href="javascript:{}" onclick="showTableDiv(this.id)"> View</a>
			</span>
			<span style="margin-left:20px;"> Constituencies Not Considered  - <c:out value="${electionsComparisonVO.count}"/><a  id="constiNotConsi_Anc" href="javascript:{}" onclick="showTableDiv(this.id)"> View</a>
			</span>
		</div>
		<br/><br/>
		
		<div style="display:none;" id="votesGainedDiv">
			<table class="CandidateElectionResultsTable" width="600px">
			<tr>
				<th colspan="10" align="center"> Votes % Gained</th>
			</tr>
			<c:forEach var="comparison" items="${electionsComparisonVO.votesGained}">
				<tr>
					<td  align="left"><b>${comparison.districtName}</b></td>
					<td colspan="9"  align="center"> <a href="javascript:{}" id="votesGained${comparison.districtId}" onclick="showComparisonDetails(this.id)"> View Results</a></td>
				</tr>	
				<tr id="votesGained${comparison.districtId}" style="display:none;">
					<td colspan="10"  align="center">
					    <b> In <c:out value="${electionsComparisonVO.firstYear}"/> </b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						
						<b>Complete Votes Gained Results</b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						
						<b> In <c:out value="${electionsComparisonVO.secondYear}"/> </b>
					</td>
				</tr>
				<tr id="votesGained${comparison.districtId}" style="display:none;">					
					<th>Candidate Name</th>
					<th>Constituency Name</th>
					<th>Votes Earned</th>
					<th>Status</th>
					<th>Votes %</th>
					<th> Votes % Increase</th>
					<th>Candidate Name</th>
					<th>Votes Earned</th>
					<th>Votes %</th>
					<th>Status</th>
				</tr>				
					<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
					<tr id="votesGained${comparison.districtId}" style="display:none;">
					    <td><c:url value="candidateElectionResultsAction.action" var="candidateName">
			            <c:param name="candidateId"   value="${partyResults.candidateId}" />
						</c:url>
						<a href='<c:out value="${candidateName}" />'>${partyResults.candidateName}</a></td>
						<td><c:url value="constituencyPageAction.action" var="constituencyName">
			            <c:param name="constituencyId"   value="${partyResults.constituencyId}" />
						</c:url>
						<a href='<c:out value="${constituencyName}" />'>${partyResults.constituencyName}</a></td>
						<td>${partyResults.votesEarned}</td>
						<c:if test="${partyResults.rank == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rank != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
						<td>${partyResults.votesPercentage}<c:out value="%" /></td>
						<th>${partyResults.votesDiff}<c:out value="%" /></th>
						<td><b>${partyResults.secondCandidateName}</b></td>
						<td>${partyResults.votesEarnedBySecond}</td>
						<td>${partyResults.votesPercentageBySecond}<c:out value="%" /></td>
						<c:if test="${partyResults.rankBySecond == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rankBySecond != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
					</tr>	
					</c:forEach>
					<tr id="votesGained${comparison.districtId}" style="display:none;">
						<th colspan="10"></th>
					</tr>
			</c:forEach>
			
			</table>
		</div>
		<br/><br/>
		<div style="display:none;" id="votesLostDiv">
			<table class="CandidateElectionResultsTable"  width="600px">
			<tr>
				<th colspan="10" align="center"> Votes % Lost</th>
			</tr>
			<c:forEach var="comparison" items="${electionsComparisonVO.votesLost}">
				<tr>
					<td  align="left"><b>${comparison.districtName}</b></td>
					<td colspan="9"  align="center"> <a href="javascript:{}" id="${comparison.districtId}" onclick="showComparisonDetails(this.id)"> View Results</a></td>
				</tr>	
				<tr id="${comparison.districtId}" style="display:none;">
					<td colspan="10"  align="center">
					    <b> In <c:out value="${electionsComparisonVO.firstYear}"/></b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<b>Complete Votes Lost Results </b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<b> In <c:out value="${electionsComparisonVO.secondYear}"/> </b>
					</td>
				</tr>
				<tr id="${comparison.districtId}" style="display:none;">					
					<th>Candidate Name</th>
					<th>Constituency Name</th>
					<th>Votes Earned</th>
					<th>Status</th>
					<th>Votes %</th>
					<th>Votes % Decrease</th>
					<th>Candidate Name</th>
					<th>Votes Earned</th>
					<th>Votes %</th>
					<th>Status</th>
				</tr>				
					<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
					<tr id="${comparison.districtId}" style="display:none;">						
						<td><c:url value="candidateElectionResultsAction.action" var="candidateName">
			            <c:param name="candidateId"   value="${partyResults.candidateId}" />
						</c:url>
						<a href='<c:out value="${candidateName}" />'>${partyResults.candidateName}</a></td>
						<td><c:url value="constituencyPageAction.action" var="constituencyName">
			            <c:param name="constituencyId"   value="${partyResults.constituencyId}" />
						</c:url>
						<a href='<c:out value="${constituencyName}" />'>${partyResults.constituencyName}</a></td>
						<td>${partyResults.votesEarned}</td>
						<c:if test="${partyResults.rank == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rank != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
						<td>${partyResults.votesPercentage}<c:out value="%" /></td>
						<th>${partyResults.votesDiff}<c:out value="%" /></th>
						<td><b>${partyResults.secondCandidateName}</b></td>
						<td>${partyResults.votesEarnedBySecond}</td>
						<td>${partyResults.votesPercentageBySecond}<c:out value="%" /></td>
						<c:if test="${partyResults.rankBySecond == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rankBySecond != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
					</tr>	
					</c:forEach>
					<tr id="${comparison.districtId}" style="display:none;">
						<th colspan="10"></th>
					</tr>
			</c:forEach>
			
			</table>

		</div>
		<br/><br/>
		<div style="display:none;" id="constiNotConsiDiv">
			<table class="CandidateElectionResultsTable" width="600px">
			<tr>
				<th colspan="10" align="center"> Constituencies Not Considered</th>
			</tr>
			<c:forEach var="comparison" items="${electionsComparisonVO.constituenciesNotConsidered}">
				<tr>
					<td  align="left"><b>${comparison.districtName}</b></td>
					<td colspan="9"  align="center"> <a href="javascript:{}" id="constituenciesNotConsidered${comparison.districtId}" onclick="showComparisonDetails(this.id)"> View Results</a></td>
				</tr>	
				<tr id="constituenciesNotConsidered${comparison.districtId}" style="display:none;">
					<td colspan="5"  align="center">
					    <b> In <c:out value="${electionsComparisonVO.firstYear}"/> </b>
						
						<b>Complete Votes Gained Results</b>
						
						<b> In <c:out value="${electionsComparisonVO.secondYear}"/> </b>
					</td>
				</tr>
				<tr id="constituenciesNotConsidered${comparison.districtId}" style="display:none;">					
					<th>Candidate Name</th>
					<th>Constituency Name</th>
					<th>Votes Earned</th>
					<th>Status</th>
					<th>Votes %</th>
					
				</tr>				
					<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
					<tr id="constituenciesNotConsidered${comparison.districtId}" style="display:none;">
					    <td><c:url value="candidateElectionResultsAction.action" var="candidateName">
			            <c:param name="candidateId"   value="${partyResults.candidateId}" />
						</c:url>
						<a href='<c:out value="${candidateName}" />'>${partyResults.candidateName}</a></td>
						<td><c:url value="constituencyPageAction.action" var="constituencyName">
			            <c:param name="constituencyId"   value="${partyResults.constituencyId}" />
						</c:url>
						<a href='<c:out value="${constituencyName}" />'>${partyResults.constituencyName}</a></td>
						<td>${partyResults.votesEarned}</td>
						<c:if test="${partyResults.rank == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rank != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
						<td>${partyResults.votesPercentage}<c:out value="%" /></td>
						
					</tr>	
					</c:forEach>
					<tr id="constituenciesNotConsidered${comparison.districtId}" style="display:none;">
						<th colspan="5"></th>
					</tr>
			</c:forEach>
			
			</table>
		</div>

	</div>
	</c:if>
</body>
</html>