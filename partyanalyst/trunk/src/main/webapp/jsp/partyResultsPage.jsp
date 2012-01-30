<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Party Results</title>
<script type="text/javascript">
var labelResources = { <%		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String electionYear = rb.getString("electionYear");
		String party = rb.getString("party");
		String participatedConsts = rb.getString("participatedConsts");
		String seatsWon = rb.getString("seatsWon");
		String votesPercentage = rb.getString("votesPercentage");
		String oppPartyRes = rb.getString("oppPartyRes");
		String oppParty = rb.getString("oppParty");
		ResourceBundle pprRb = ResourceBundle.getBundle("ppr_Labels");
		
		String mainParty = pprRb.getString("mainParty");
		String allianceParty = pprRb.getString("allianceParty");
%>}
    function showOppostionDeatils(year)
	{	
		var id = year+"_opposition";

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
<center>
	<H3 align="center" style="font-family: verdana,arial,sans-serif;color:#003399;">
		<u align="centre"> <c:out value="${selectedPartyShortName}"/>  Party Results for  <c:out value="${selectedLocationName}"/>   <c:out value="${reportLevel}"/> <c:out value="${selectedElectionTypeName}"/> Elections</u>
    </H3>
</center>  

<IMG id="chartImg" SRC="charts/<%= request.getAttribute("chartName")%>" WIDTH="450" HEIGHT="400">   

<div id="partyResultsDiv" style="padding:5px;">
	
		<table class="searchresultsTable" border="1" style="width:90%;">
	<tr>
		<th><%=electionYear%></th>
        <th><%=party%></th>
		<th><%=participatedConsts%></th>
		<th><%=seatsWon%></th>
		<th><%=votesPercentage%></th>
		<th><%=oppPartyRes%></th>
		
	</tr>
	<c:forEach var="partyResultInfoVO" items="${partyResultInfoVOs}" >	
	<c:if test="${partyResultInfoVO.partyInfoVO != null}">	
	<tr>

		<td align="center"><c:out value="${partyResultInfoVO.partyInfoVO.electionYear}"/></td>
		<td align="center"><font color="red">

		<c:if test="${partyResultInfoVO.partyInfoVO.partyShortName != 'IND'}">
		<a href="partyPageAction.action?partyId=${partyResultInfoVO.partyInfoVO.partyId}" style="color:red"><c:out value="${partyResultInfoVO.partyInfoVO.partyShortName}"/></a>
		</c:if>

		<c:if test="${partyResultInfoVO.partyInfoVO.partyShortName == 'IND'}">
		<c:out value="${partyResultInfoVO.partyInfoVO.partyShortName}"/>
		</c:if>

		</font></td>

		<td align="center"><font color="red"><c:out value="${partyResultInfoVO.partyInfoVO.seatsParticipated}"/></font></td>
		<td align="center" ><font color="red"><c:out value="${partyResultInfoVO.partyInfoVO.seatsWin}"/></font></td>
		<td align="center"><font color="red"><c:out value="${partyResultInfoVO.partyInfoVO.percentageOfVotes}"/></font></td>
		<td align="center"><a href="javascript:showOppostionDeatils(${partyResultInfoVO.partyInfoVO.electionYear})">  View Opposition Details   </a></td>
	</tr>
	</c:if>	
	
	<c:if test="${partyResultInfoVO.alliancePartysInfo != null}">
	<c:forEach var="alliancParty" items="${partyResultInfoVO.alliancePartysInfo}" >
	<tr id="${partyResultInfoVO.partyInfoVO.electionYear}_alliance">
		<td>&nbsp;</td>
		<td align="center"><font color="#896ADE">
		
		<c:if test="${alliancParty.partyShortName == 'IND'}">
		<c:out value="${alliancParty.partyShortName}"/>
		</c:if>

		<c:if test="${alliancParty.partyShortName != 'IND'}">
		<a href="partyPageAction.action?partyId=${alliancParty.partyId}">
		<c:out value="${alliancParty.partyShortName}"/></a>
		</c:if>
		
		</font></td>
		<td align="center"><font color="#896ADE"><c:out value="${alliancParty.seatsParticipated}"/></font></td>
		<td align="center"><font color="#896ADE"><c:out value="${alliancParty.seatsWin}"/></font></td>
		<td align="center"><font color="#896ADE"><c:out value="${alliancParty.percentageOfVotes}"/></font></td>
	</tr>
	</c:forEach>
	</c:if>	
	<c:forEach var="oppParty" items="${partyResultInfoVO.oppositionPartyInfo}" >
	<tr id="${partyResultInfoVO.partyInfoVO.electionYear}_opposition" style="display:none;">
		<td>&nbsp;</td>
		<td align="center">

		<c:if test="${oppParty.partyShortName == 'IND'}">
		<c:out value="${oppParty.partyShortName}"/>
		</c:if>

		<c:if test="${oppParty.partyShortName != 'IND'}">
		<a href="partyPageAction.action?partyId=${oppParty.partyId}">
		<c:out value="${oppParty.partyShortName}"/></a>
		</c:if>
		</td>
		<td align="center"><c:out value="${oppParty.seatsParticipated}"/></td>
		<td align="center"><c:out value="${oppParty.seatsWin}"/></td>
		<td align="center"><c:out value="${oppParty.percentageOfVotes}"/></td>
		
	</tr>
	</c:forEach>
	<tr>
		<th colspan="6"> </th>
	</tr>
	</c:forEach>	
</table>
		<table align="left" width="500" style="margin-left:45px;">
			<tr>	
				<td ><div style="height:10px;width:10px;border:1px solid #896ADE;background-color:red;"></div></td><td><font color="red"><%=mainParty%></font></td>
				<td><div style="height:10px;width:10px;border:1px solid red;background-color:#896ADE;"></div></td><td><font color="#896ADE"><%=allianceParty%></font></td>
				<td><div style="height:10px;width:10px;border:1px solid red;background-color:black;"></div></td><td><font color="#000000"><%=oppParty%></font></td>
			</tr>
		</table>
</div>
</body>
</html>