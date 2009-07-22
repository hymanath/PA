<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javaScript">
function showBand(divtag) {                      
    document.getElementById(divtag).style.display = 'inline';  
}
function closeSection(divtag) {
	document.getElementById(divtag).style.display = 'none';
}
</script>
</head> 
<body>
<center><H3><u>
State level report for the year <s:property value="stateData.year" /> 
<s:property value="stateData.state" /> State - Party <s:property value="stateData.party" />
</u></H3></center>
Total Seats Won: <s:property value="stateData.totalSeatsWon" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<s:label labelposition="right">Total Percentage of Votes: <s:property value="stateData.totalPercentageOfVotesWon" /><s:property value="stateData.totalPercentageOfVotesWonPreviousElection-stateData.totalPercentageOfVotesWon"/></s:label>
<br><br>
<s:label labelposition="left">Party Positions:</s:label>
<br><p align="center">
<c:set var="data" value="stateData" scope="session" />
<c:set var="myId" value="row" />

<display:table name="stateData.positionDistribution" id="${myId}" length="1" cellpadding="10px" style="background-color:yellow">
    <c:forEach var="pd" items="${stateData.positionDistribution}" varStatus="status">
    	<jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
		<c:choose>
			<c:when test="<%=status.getIndex() == 1%>">
					<display:column title="${pd.key}ndPosition" > <c:out value="${pd.value}" />  </display:column>
			</c:when>
			<c:when test="<%=status.getIndex() == 2%>">
					<display:column title="${pd.key}rdPosition" > <c:out value="${pd.value}" />  </display:column> 
			</c:when>
			<c:when test="<%=status.getIndex() == 3%>">
					<display:column title="${pd.key}thPosition" > <c:out value="${pd.value}" />  </display:column>
			</c:when>
		</c:choose>	          
	</c:forEach>
	<display:column title=""></display:column>

</display:table>  
</p> 

<center><IMG SRC="charts/partyPositionsChart_<%=request.getSession().getId()%>.png" WIDTH="300" HEIGHT="200"  BORDER="0" ></center>

<br>
<c:set var="constituencyPositionsList" value="stateData.constituencyPositions" scope="session" />
<c:forEach var="constPositions" items="${stateData.constituencyPositions}" >
	<c:choose>
		<c:when test="${constPositions.type=='POSITIONS_WON_MINOR_BAND'}">
			<b>Winning Positions with lower percentage margin: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click here for details</a>)</b><br>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_MINOR_BAND'}"><BR>
		<B>Losing Positions with lower percentage margin: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click here for details</a>)</B><br>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_POSITIVE_SWING'}">
			<br>
			<B>Positive swing:</B> <br><p>
					&nbsp;&nbsp;&nbsp;	Winning Positions: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click here for details</a>)
					</p>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_POSITIVE_SWING'}">
		<br>
			<B>Negative swing: </B><BR><P>
					&nbsp;&nbsp;&nbsp;	Winning Positions: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click here for details</a>)<br>
					</p>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_NEGATIVE_SWING'}">
		<br>
			<B>Negative swing: </B><BR><P>
			&nbsp;&nbsp;&nbsp;	Winning Positions: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click 	here for details</a>)<br>
			</p>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_NEGATIVE_SWING'}">
		<p>
			&nbsp;&nbsp;&nbsp;	Loosing Positions: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click here for details</a>)
		</p>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_BY_DROPPING_VOTES'}">
		<br>
			<b>Losing Positions with droping voting percentage: <c:out value="${constPositions.positionsWon}" /> (<a href="#" onclick="showBand('${constPositions.type}');">Click here for details</a>)</b><br>
		</c:when>
	</c:choose>
<div id="${constPositions.type}" style="display:none">
				<display:table name="${constPositions.constituencyPositionDetails}" id="row"> 
							<display:column title="Constiuency Name" property="constiuencyName" />
							<display:column title="Candidate Name" property="candidateName" />
							<display:column title="Percentage of Votes" property="percentageOfVotes" />
							<display:column title="Previous Election Percentage" property="prevElectionPercentage" />
							<display:column title="Opposition Party" property="oppositeParty" />
							<display:column title="Opposition Party Candidate" property="oppositePartyCandidate" />
				</display:table>	
<a href="#" onclick="closeSection('${constPositions.type}');">close</a><BR>
</div> 
</c:forEach>  
<br><b>Your votes are flown to any one of the below parties:</b><br>
<center>
<c:forEach var="p" items="${stateData.toPartySwing}" >
${p.key} : ${p.value} <s:label>%</s:label>                   
</c:forEach>
</center>
<br>
<s:form action="partyPerformanceJasper.action?jasperFile=jasper\partyPerformance\partyPerformanceReport.jrxml&type=detailed">
<input type="submit" value="Generate PDF">
</s:form>
<s:form action="partyPerformanceJasper.action?jasperFile=jasper\partyPerformance\partyPerformanceReport.jrxml&type=normal">
<input type="submit" value="Generate Detailed PDF">
</s:form>

</body>
</html>

