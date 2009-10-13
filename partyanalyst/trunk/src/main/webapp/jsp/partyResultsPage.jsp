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
</head>
<body>
<center>
	<H3>
		<u> <c:out value="${selectedPartyShortName}"/>-Party &nbsp Report Result for the   <c:out value="${selectedElectionTypeName}"/></u>
    </H3>
</center>

<display:table name="partyResultInfoVOs" id="one">
  <display:column property="partyInfoVO.electionYear" title="Year" sortable="true" />
  <display:column property="partyInfoVO.seatsParticipated"  title="Participated seats"  sortable="true"/>
  <display:column property="partyInfoVO.seatsWin"  title="Seats Win"  sortable="true"/>
  <display:column property="partyInfoVO.percentageOfVotes"  title="Votes%"  sortable="true"/>
 
  <c:forEach var="oppParty" items="${one.oppositionPartyInfo}" >
	  <display:column title="Opposite Party">
		<c:out value="${oppParty.partyShortName}"/>
	  </display:column>
	  <display:column    title="Participated seats"  sortable="true">
		<c:out value="${oppParty.seatsParticipated}"/>
	  </display:column>
	  <display:column   title="Seats Win"  sortable="true">
		<c:out value="${oppParty.seatsWin}"/>
	  </display:column>
	  <display:column   title="Votes%"  sortable="true">
		<c:out value="${oppParty.percentageOfVotes}"/>
	  </display:column>
  </c:forEach>
  
</display:table>

<IMG id="chartImg" SRC="charts/<%= request.getAttribute("chartName")%>" WIDTH="450" HEIGHT="400" style="opacity:0.0;">

</body>
</html>