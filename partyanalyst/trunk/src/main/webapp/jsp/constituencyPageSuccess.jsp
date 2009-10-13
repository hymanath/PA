<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Details</title>

<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAtj-arj0xLH-lCl9FnZx0sRRH1psPGaU8keNJ-w6vMT2kOwbIpRR376mZ9g1k6g42yZHtunFsovIjug&sensor=true"
            type="text/javascript"></script>
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
	function getString() 
	{
	  alert("In function");
	  if (GBrowserIsCompatible())
	  {        
		//var str=document.getElementById("add").value;
		//console.log("In second if");
	//	showAddress(str);      
      }
    }
	
	function showAddress(address)
	{
		var map = new GMap2(document.getElementById("map_canvas"));
		var geocoder = new GClientGeocoder();
		geocoder.getLatLng(address,
		function(point)
		{			
	      if (!point) 
		  {
			alert(address + " not found");
		  }
		  else 
		  {
			map.setCenter(point, 14);
			var marker = new GMarker(point);
			map.addOverlay(marker);
			//marker.openInfoWindowHtml(address);
			map.setUIToDefault();
		  }
		})
    }
</script>
</head>
<body onLoad="getString()">
<div id="ResultsDiv" style="padding:5px;">
   <h2><b><c:out value="${constituencyDetails.constituencyName}" /> Constituency Details </b></h2>
  <table class="ConstituencyElectionsTable" style="float:left;margin-left:40px;" >
   <tr>
      <th><c:out value="Constituency Name" /></th>
      <td><b><c:out value="${constituencyDetails.constituencyName}" /></b></td> 
   </tr>
   <tr>
      <th><c:out value="District" /></th>
      <td><b><c:out value="${constituencyDetails.districtName}" /></b></td> 
   </tr>
   <tr>
      <th><c:out value="State" /></th>
      <td><b><c:out value="${constituencyDetails.stateName}" /></b></td> 
   </tr>
   <tr>
      <th><c:out value="Formation Date" /></th>
      <td><b><c:out value="${constituencyDetails.startDate}" /></b></td> 
   </tr>
  </table>

<div id="map_canvas" style="border: 1px solid ; width: 200px; height: 150px;">
</div>

    <h2>Constituency Election Info</h2>
	
	<table class="ConstituencyElectionsTable" border="1">
	<tr>
		<th>Election Type</th>
        <th>Year</th>
		<th>Candidate Won</th>
		<th>Party Name</th>
		<th>Votes Earned</th>
		<th>Votes Percentage</th>
		<th></th>

	</tr>
	<c:forEach var="constituencyElectionResults" items="${constituencyElectionResultsVO}" >		
	<tr>
		<td><c:out value="${constituencyElectionResults.electionType}"/></td>
		<td><c:out value="${constituencyElectionResults.electionYear}"/></td>
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.candidateName}"/></td>
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.partyName}"/></td>
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.votesEarned}"/></td>
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.votesPercentage}"/></td>
		<td><a href="javascript:showDetails(${constituencyElectionResults.electionId})">More Details</a></td>
	</tr>
	<tr id="${constituencyElectionResults.electionId}" style="display:none;">
	  <th colspan="7" align="center">Complete Results Of <c:out value="${constituencyDetails.constituencyName}" /> Constituency <c:out value="${constituencyElectionResults.electionType}"/> <c:out value="${constituencyElectionResults.electionYear}"/> Election</th>
	</tr>
	 	<tr id="${constituencyElectionResults.electionId}" style="display:none;">
 
		<td ><b>Election Type</b></td>
		<td ><b>Year</b></td>
		<td ><b>Candidate Name</b></td>
		<td colspan="2"><b>Party Name</b></td>
		<td ><b>Votes Earned</b></td>
		<td colspan="2"><b>Votes Percentage</b></td>
	</tr>
	<c:forEach var="detailedResult" items="${constituencyElectionResults.candidateOppositionList}" >
	<tr id="${constituencyElectionResults.electionId}" style="display:none;">
		<td ><c:out value="${constituencyElectionResults.electionType}"/></td>
		<td ><c:out value="${constituencyElectionResults.electionYear}"/></td>
		<td ><c:out value="${detailedResult.candidateName}"/></td>
		<td colspan="2"><c:out value="${detailedResult.partyName}"/></td>
		<td ><c:out value="${detailedResult.votesEarned}"/></td>
		<td colspan="2"><c:out value="${detailedResult.votesPercentage}"/></td>
	</tr>
	</c:forEach>
	<tr id="${constituencyElectionResults.electionId}" style="display:none;">
       <th colspan="7" align="center"> . </th>
	</tr>
	</c:forEach>
</table><br>
</div>
<script type="text/javascript">
	
		var address="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";		
		var map = new GMap2(document.getElementById("map_canvas"));
		var geocoder = new GClientGeocoder();
		geocoder.getLatLng(address,
		function(point)
		{			
	      if (!point) 
		  {
			alert(address + " not found");
		  }
		  else 
		  {
			map.setCenter(point, 8);
			var marker = new GMarker(point);
			map.addOverlay(marker);
			map.setUIToDefault();
		  }
		})
   
</script>
</body>
</html>