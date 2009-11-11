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
	<h3><u style="color: #4D2828;"><c:out value="${constituencyDetails.constituencyName}" /> Constituency Details </u></h3>
<div id="ResultsDiv" style="padding:5px;text-align:left;margin-left:50px;">
  <h4><u style="color: #4D2828;">Constituency Details</u></h4> 
  <table style="float:left;" class="constituencyDetailsTable">
   <tr>
      <th><c:out value="Constituency Name" /></th>
      <td><c:out value="${constituencyDetails.constituencyName}" /></td> 
   </tr>
   <tr>
      <th><c:out value="District" /></th>
      <td><c:out value="${constituencyDetails.districtName}" /></td> 
   </tr>
   <tr>
      <th><c:out value="State" /></th>
      <td><c:out value="${constituencyDetails.stateName}" /></td> 
   </tr>
   <tr>
      <th><c:out value="Formation Date" /></th>
      <td><c:out value="${constituencyDetails.startDate}" /></td> 
   </tr>
  </table>

<div id="map_canvas" style="border: 1px solid ; width: 260px; height: 200px;margin-left:400px;">
</div>
<c:out value="${delimitationConstituencyMandalResultVO.constituencyType}"/>
<table border="2">
	<tr>
		<td>
			<table>
				 <tr>		
					<td><b>MandalID</b></td>
					<td><b>Mandal Name</b></td>
					<td><b>Total Populations</b></td>
					<td><b>SC Population</b></td>
					<td><b>ST Population</b></td>
					<td><b>Literate Population</b></td>
					<td><b>Illiterate Population</b></td>
					<td><b>Working Population</b></td>
				</tr>
				<c:forEach var="presentMandals" items="${delimitationConstituencyMandalResultVO.presentMandals}" >	
					<tr>
						<td ><c:out value="${presentMandals.mandalID}"/></td>
						<td >						
							<c:url value="mandalPageAction.action" var="displayMandalURL">
								<c:param name="MANDAL_ID"   value="${presentMandals.mandalID}" />
								<c:param name="MANDAL_NAME"   value="${presentMandals.mandalName}" />
							</c:url>				
	      	 				<a href='<c:out value="${displayMandalURL}" />'> 
								<c:out value="${presentMandals.mandalName}"/> 
							</a>						
						</td>
						<td ><c:out value="${presentMandals.totalPersons}"/></td>
						<td ><c:out value="${presentMandals.totalSCPersons}"/></td>
						<td ><c:out value="${presentMandals.totalSTPersons}"/></td>
						<td ><c:out value="${presentMandals.totalLiteratePersons}"/></td>
						<td ><c:out value="${presentMandals.totalIlliteratePersons}"/></td>
						<td ><c:out value="${presentMandals.totalWorkingPersons}"/></td>
					</tr>
				</c:forEach>
			
			</table>
		</td>
		
		<td>
			<c:if test="${delimitationConstituencyMandalResultVO.constituencyType==CHANGE_CONSTITUENCY}">
				<table>
				<tr>		
						<td><b>MandalID</b></td>
						<td><b>Mandal Name</b></td>
						<td><b>Total Populations</b></td>
						<td><b>SC Population</b></td>
						<td><b>ST Population</b></td>
						<td><b>Literate Population</b></td>
						<td><b>Illiterate Population</b></td>
						<td><b>Working Population</b></td>
					</tr>
					<c:forEach var="mandalsBeforeDelimitationConstituency" items="${delimitationConstituencyMandalResultVO.mandalsBeforeDelimitationConstituency}" >	
						<tr>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.mandalID}"/></td>
							<td>
								<c:url value="mandalPageAction.action" var="displayMandalURL">
									<c:param name="mandalId"   value="${mandalsBeforeDelimitationConstituency.mandalID}" />
								</c:url>
				
	      	 					<a href='<c:out value="${displayMandalURL}" />'> 
							
							
								 <c:out value="${mandalsBeforeDelimitationConstituency.mandalName}"/> </a>
							</td>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.totalPersons}"/></td>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.totalSCPersons}"/></td>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.totalSTPersons}"/></td>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.totalLiteratePersons}"/></td>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.totalIlliteratePersons}"/></td>
							<td ><c:out value="${mandalsBeforeDelimitationConstituency.totalWorkingPersons}"/></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</td>
		
	</tr>
</table>
    <h4><u style="color: #4D2828;">Previous <s:property value="electionType"/> Results</u></h4>
	
	<table class="ConstituencyElectionsTable" border="1">
	<tr>        
		<th>Candidate Won</th>
		<th>Party Name</th>
		<th>Year</th>
		<th>Votes Earned</th>
		<th>Votes Percentage</th>
		<th></th>

	</tr>
	<c:forEach var="constituencyElectionResults" items="${constituencyElectionResultsVO}" >		
	<tr>		
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.candidateName}"/></td>		
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.partyName}"/></td>
		<td><c:out value="${constituencyElectionResults.electionYear}"/></td>		
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.votesEarned}"/></td>
		<td><c:out value="${constituencyElectionResults.candidateResultsVO.votesPercentage}"/></td>
		<td><a href="javascript:showDetails(${constituencyElectionResults.electionId})">More Details</a></td>
	</tr>
	<tr id="${constituencyElectionResults.electionId}" style="display:none;">
	  <th colspan="7" align="center">Complete Results Of <c:out value="${constituencyDetails.constituencyName}" /> Constituency <c:out value="${constituencyElectionResults.electionType}"/> <c:out value="${constituencyElectionResults.electionYear}"/> Election</th>
	</tr>
	 <tr id="${constituencyElectionResults.electionId}" style="display:none;">		
		<td><b>Candidate Name</b></td>
		<td><b>Party Name</b></td>
		<td><b>Year</b></td>
		<td><b>Votes Earned</b></td>
		<td colspan="2"><b>Votes Percentage</b></td>
	</tr>
	<c:forEach var="detailedResult" items="${constituencyElectionResults.candidateOppositionList}" >
	<tr id="${constituencyElectionResults.electionId}" style="display:none;">		
		<td ><c:out value="${detailedResult.candidateName}"/></td>
		<td ><c:out value="${detailedResult.partyName}"/></td>
		<td ><c:out value="${constituencyElectionResults.electionYear}"/></td>
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