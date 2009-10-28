<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<s:url value='/styles/table.css'/>" rel="stylesheet" type="text/css" media="all"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javaScript">
function showBand(divtag)
{ 
	var divElmt=document.getElementById(divtag);
	var spanElmt=document.getElementById(divtag+"span");	
	if(!divElmt || !spanElmt)
		return;
	if(divElmt.style.display=="none")
	{
		divElmt.style.display = 'block';
		spanElmt.innerHTML="Hide Details";
	}
	else
	{
		divElmt.style.display = 'none';
		spanElmt.innerHTML="Display Details";
	}
}
function fadeIn(objId, opacity)
{
	var obj = document.getElementById(objId);
	if(obj && opacity <= 100){
		setOpacity(obj, opacity);
		opacity += 4;
		window.setTimeout("fadeIn('"+objId+"',"+opacity+")", 100);		
	}
}
function setOpacity(obj, opacity)
{
	opacity = (opacity == 100)?99.999:opacity;
	obj.style.filter ="alpha(opacity:"+opacity+")";// IE/Win
	obj.style.KHTMLOpacity = opacity/100;// Safari<1.2, Konqueror
	obj.style.MozOpacity = opacity/100;// Older Mozilla and Firefoxv
	obj.style.opacity = opacity/100;// Safari 1.2, newer Firefox and Mozilla, CSS3
}
/*function closeSection(divtag) {
	document.getElementById(divtag).style.display = 'none';
}*/
</script>
<style type="text/css">
	#partyPerformanceReportMainDiv
	{
		text-align:left;
		margin-left:50px;
		font-size:12px;
	}
</style>
</head> 
<body>
<div id="partyPerformanceReportMainDiv">
<div style="padding: 5px; font-weight: bold; color: #46505B; font-family: Trebuchet MS; font-size: 15px;text-align:center;">
	<u>
		Party Performance Report for the year <s:property value="stateData.year" /> 
	</u>
	<!--<u><s:property value="stateData.state" /> State - Party <s:property value="stateData.party" /> </u> -->
</div>
<br/><br/>
 <s:label labelposition="left"><b><U>Party Details:</U></b></s:label>
<div style="margin-left: 15px;">
<table class="partyPerformanceReportTable" border="1">
	<tr>
		<th>State</th>
		<td style="background-color: #ECF1F5"><s:property value="stateData.state" /></td>
	</tr>
	<tr id="district"
	  <% java.lang.String district = (java.lang.String) request.getAttribute("stateData.district");
		if(district == null) { %> 
			style="display:none"
		<% } %>
		>
		<th>District</th>
		<td style="background-color: #ccb"><s:property value="stateData.district" /></td>
	</tr>
	<tr>
		<th>Party</th>
		<td style="background-color: #FFFFFF"><s:property value="stateData.party" /></td>
	</tr>
	<tr>
		<th>Seats Won</th>
		<td style="background-color: #ECF1F5"> <s:property value="stateData.totalSeatsWon" />( <s:property value="stateData.diffSeatsWon" /> )</td>
	</tr>
	<tr>
		<th>Votes %</th>
		<td style="background-color: #FFFFFF"><s:property value="stateData.totalPercentageOfVotesWon" />%( <s:property value="stateData.diffOfTotalPercentageWinWithPrevElection"/>%)</td>
	</tr>	
</table>
</div>
<div style="left:650px;margin-right:20px;position:absolute;top:320px;">
	<IMG id="chartImg" SRC="charts/<%=request.getAttribute("chartName")%>" WIDTH="400" HEIGHT="350" style="opacity:0.0;">
</div>
<!--Total Seats Won: <s:property value="stateData.totalSeatsWon" />( <s:property value="stateData.diffSeatsWon" /> ) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<s:label labelposition="right">Total Percentage of Votes: <s:property value="stateData.totalPercentageOfVotesWon" />%( <s:property value="stateData.diffOfTotalPercentageWinWithPrevElection"/>%) </s:label> -->
<BR/><BR/>
 <s:label labelposition="left"><b><U>Party Positions:</U></b></s:label>
<!--<br><p align="center"> -->
<div style="margin-left: 15px;">
<c:set var="data" value="stateData" scope="session" />
<c:set var="myId" value="row" />

<display:table class="partyPerformanceReportTable" name="stateData.positionDistribution" id="${myId}" length="1" cellpadding="10px" >
    <c:forEach var="pd" items="${stateData.positionDistribution}" varStatus="status">
    	<jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
		<c:choose>
			<c:when test="<%=status.getIndex() == 1%>">
					<display:column title="2nd Position" > <c:out value="${pd.value}" />  </display:column>
			</c:when>
			<c:when test="<%=status.getIndex() == 2%>">
					<display:column title="3rd Position" > <c:out value="${pd.value}" />  </display:column> 
			</c:when>
			<c:when test="<%=status.getIndex() == 3%>">
					<display:column title="4th Position" > <c:out value="${pd.value}" />  </display:column>
			</c:when>
			<c:when test="<%=status.getIndex() == 4%>">
					<display:column title="Nth Position" > <c:out value="${pd.value}" />  </display:column>
			</c:when>
		</c:choose>	          
	</c:forEach>
</display:table>  
</div>
<!--<center><IMG SRC="charts/partyPositionsChart_<%=request.getSession().getId()%>.png" WIDTH="300" HEIGHT="200"  BORDER="0" ></center> -->

<br>
<div>
	<B><U>Detailed Report...</U></B>
</div>
<br/>
<div>
<c:set var="constituencyPositionsList" value="stateData.constituencyPositions" scope="session" />
<c:forEach var="constPositions" items="${stateData.constituencyPositions}" >
	<c:choose>
		<c:when test="${constPositions.type=='POSITIONS_WON_MINOR_BAND'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with lower % margin: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>	
				</c:if>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_WON_MAJOR_BAND'}">
			<div style="padding: 5px 5px 10px 0px;font-family: Trebuchet MS;font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with highest % margin: <c:out value="${constPositions.positionsWon}" /> 
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_LOST_MINOR_BAND'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Losing Positions with lower % margin: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_MAJOR_BAND'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Losing Positions with highest % margin: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>	
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_POSITIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with Positive Swing: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_POSITIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Loosing Positions with Positive Swing: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_NEGATIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with Negative Swing: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_NEGATIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Loosing Positions with Negative Swing: <c:out value="${constPositions.positionsWon}" /> 
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>	
		<c:when test="${constPositions.type=='POSITIONS_LOST_BY_DROPPING_VOTES'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Losing Positions with droping voting percentage: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>
</c:choose>
<div id="${constPositions.type}" style="display:none">
<center>
				<display:table class="partyPerformanceReportTable" name="${constPositions.constituencyPositionDetails}" id="row" style="margin-top:0px;"> 
							<display:column title="Constiuency Name" property="constiuencyName" />
							<display:column title="Candidate Name" property="candidateName" />
							<display:column title="% of Votes" property="percentageOfVotes" />
							<display:column title="% of Votes Polled" property="prevElectionPercentage" />
							<display:column title="Previous Election %" property="prevElectionPercentage" />
							<display:column title="Oppositin Party % of Votes" property = "oppositePartyPercentageOfVotes" />
							<display:column title="Opposition Party" property="oppositeParty" />
							<display:column title="Opposition Party Candidate" property="oppositePartyCandidate" />
							<display:column title="Previous Election Candidate Name" property = "prevElectionCandidateName" />
							<display:column title="Previous Election % of Votes" property="prevElectionVotes" />
							<display:column title="Previous Election % of Votes Polled" property="prevElectionPercentageOfVotesPolled" />
				</display:table>	
</center>
<!--<a href="#" onclick="closeSection('${constPositions.type}');">close</a><BR>-->
</div> 
</c:forEach> 
</div>
<br/><br/>

 <s:label labelposition="left"><b><U>Your votes are flown to any one of the below parties:</U></b></s:label>
<div style="margin-left: 15px;"> 
<table class="partyPerformanceReportTable" border="1">
	<c:forEach var="p" items="${stateData.toPartySwing}" >
	<tr>
		<th>${p.key}</th>
		<td style="background-color: #eec">${p.value}% </td>
	</tr>
	</c:forEach>
</table>
</div>
<br>
<s:label labelposition="left"><b><U>Rebel Candidates::</U></b></s:label>
<display:table class="partyPerformanceReportTable" name="${stateData.rebelPartyCandidates}" id="row" style="margin-top:0px;"> 
							<display:column title="Constiuency Name" property="constiuencyName" />
							<display:column title="Candidate Name" property="candidateName" />
							<display:column title="% of Votes" property="percentageOfVotes" />
							<display:column title="Position" property="rank" />
							<display:column title="Party" property="oppositeParty" />
				</display:table>	
<br>
<div>
<s:form action="partyPerformanceJasper.action?jasperFile=jasper\partyPerformance\partyPerformanceReport.jrxml&type=normal" style="float: left;margin-right:20px;">
<input type="submit" value="Generate PDF">
</s:form>
<s:form action="partyPerformanceJasper.action?jasperFile=jasper\partyPerformance\partyPerformanceReport.jrxml&type=detailed">
<input type="submit" value="Generate Detailed PDF">
</s:form>
</div>
<script type="text/javascript">
	fadeIn("chartImg",10);
</script>
</div>
</body>
</html>

