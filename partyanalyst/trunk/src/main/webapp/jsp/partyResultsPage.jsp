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
<title>
<div><c:out value="${selectedPartyShortName}"/>  Party Results for  <c:out value="${selectedLocationName}"/>   <c:out value="${reportLevel}"/> <c:out value="${selectedElectionTypeName}"/> Elections
		</div></title>
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script>
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
<style type="text/css">
table.searchresultsTable {
    border-collapse: collapse;
    color: #333333;
    font-family: verdana,arial,sans-serif;
    font-size: 11px;
	background:#FFFFFF;
}
table.searchresultsTable th {
    background-color: #DEDEDE;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}
table.searchresultsTable td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}
#headingDiv
{
     border-radius: 7px 7px 7px 7px;
    color: #FFFFFF;
    font-weight: bold;
    padding: 9px 8px 9px 8px;
    width: 960px;
    font-size: 14px;
}
#imageDiv
{
	background: none repeat scroll 0 0 #FFFFFF;
    border-radius: 5px 5px 5px 5px;
    height: 450px;
    margin-left: 220px;
    width: 550px;
}

</style>
</head>
<body>
	<div id="partyResultsMainDiv" style="margin-left: auto; margin-right: auto; float: none; width: auto;">
	
		<div id="headingDiv" style="background:#06ABEA;margin-left: 12px;">
		
			<c:out value="${selectedPartyShortName}"/>  Party Results for  <c:out value="${selectedLocationName}"/>   <c:out value="${reportLevel}"/> <c:out value="${selectedElectionTypeName}"/> Elections

			<span style="float:right;">
				<g:plusone size="medium"></g:plusone>

			<script type="text/javascript">
			(function() {
			var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
			po.src = 'https://apis.google.com/js/plusone.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
		  })();
		  </script>
		  </span>

		<span style="float:right">
		<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/partyResultsAction.action?selectedPartyShortName=${selectedPartyShortName}&selectedPartyId=${selectedPartyId}&selectedElectionTypeName=${selectedElectionTypeName}&selectedLocationName=${selectedLocationName}&electionType=${electionType}&reportLevel=${reportLevel}&stateSelectName=${stateSelectName}&partySelectName=${partySelectName}&alliances=true&__checkbox_alliances=true">
		Tweet</a>
		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
		</script>
		</span>

		<span style="float:right;margin-right: 25px;">
		<a name="fb_share" type="button_count" share_url="www.partyanalyst.com/partyResultsAction.action?selectedPartyShortName=${selectedPartyShortName}&selectedPartyId=${selectedPartyId}&selectedElectionTypeName=${selectedElectionTypeName}&selectedLocationName=${selectedLocationName}&electionType=${electionType}&reportLevel=${reportLevel}&stateSelectName=${stateSelectName}&partySelectName=${partySelectName}&alliances=true&__checkbox_alliances=true">Share in Facebook</a> 
		</span>
	
	</div>
	
	<div id="imageDiv" style="margin-top:20px;" align="center">
		<img width="450" height="400" id="chartImg" src="charts/partyResultsChart_13622S161FCC30D2AC72F4DFEF2715FA2F51EDC.png" style="margin-top:20px;"/> 
    </div>

	<div id="partyResultsDiv" style="padding:5px;">
	
		<table class="searchresultsTable" border="1" style="width:90%;margin-top: 30px;" align="center">
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
		<a href="partyPageAction.action?partyId=${partyResultInfoVO.partyInfoVO.partyId}" style="color:red;text-decoration:underLine;font-weight:bold;"><c:out value="${partyResultInfoVO.partyInfoVO.partyShortName}"/></a>
		</c:if>

		<c:if test="${partyResultInfoVO.partyInfoVO.partyShortName == 'IND'}">
		<c:out value="${partyResultInfoVO.partyInfoVO.partyShortName}"/>
		</c:if>

		</font></td>

		<td align="center"><font color="red"><c:out value="${partyResultInfoVO.partyInfoVO.seatsParticipated}"/></font></td>
		<td align="center" ><font color="red"><c:out value="${partyResultInfoVO.partyInfoVO.seatsWin}"/></font></td>
		<td align="center"><font color="red"><c:out value="${partyResultInfoVO.partyInfoVO.percentageOfVotes}"/></font></td>
		<td align="center"><a href="javascript:showOppostionDeatils(${partyResultInfoVO.partyInfoVO.electionYear})" style="text-decoration:underLine;color:#669900;font-weight:bold;">  View Opposition Details   </a></td>
	</tr>
	</c:if>	
	
	<c:if test="${partyResultInfoVO.alliancePartysInfo != null}">
	<c:forEach var="alliancParty" items="${partyResultInfoVO.alliancePartysInfo}" >
	<tr id="${partyResultInfoVO.partyInfoVO.electionYear}_alliance">
		<td>&nbsp;</td>
		<td align="center"><font color="#896ADE">
		
		<c:if test="${alliancParty.partyShortName == 'IND'}">
			<a href="javascript:{}" style="text-decoration:underLine;">
			<c:out value="${alliancParty.partyShortName}"/></a>
		</c:if>

		<c:if test="${alliancParty.partyShortName != 'IND'}">
		<a href="partyPageAction.action?partyId=${alliancParty.partyId}" style="text-decoration:underLine;font-weight:bold;">
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
		<a href="javascript:{}" style="text-decoration:underLine;color:#669900;font-weight:bold;">
		<c:out value="${oppParty.partyShortName}"/></a>
		</c:if>

		<c:if test="${oppParty.partyShortName != 'IND'}">
		<a href="partyPageAction.action?partyId=${oppParty.partyId}" style="text-decoration:underLine;color:#669900;font-weight:bold;">
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
		<table width="500" style="margin-left: 45px; margin-top: 10px; font-weight: bold;">
			<tr>	
				<td ><div style="height:10px;width:10px;border:1px solid #896ADE;background-color:red;"></div></td><td><font color="red"><%=mainParty%></font></td>
				<td><div style="height:10px;width:10px;border:1px solid red;background-color:#896ADE;"></div></td><td><font color="#896ADE"><%=allianceParty%></font></td>
				<td><div style="height:10px;width:10px;border:1px solid red;background-color:black;"></div></td><td><font color="#000000"><%=oppParty%></font></td>
			</tr>
		</table>
</div>

</div>
</body>
</html>