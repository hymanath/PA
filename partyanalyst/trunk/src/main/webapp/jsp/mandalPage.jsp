<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tehsil Details</title>
<style type="text/css">
.ConstituencyElectionsTable th
{
	text-align:left;
}
.ConstituencyElectionsTable td
{
	text-align:right;
}
</style>
</head>
<body> 

<h3><u><c:out value="${mandalInfoVO.mandalName}"/> Tehsil / Mandal Details</u></h3>
<tr><td>
	<table class="ConstituencyElectionsTable" align="left" style="margin-left:20px">		
		<tr>
			<th></th>
			<th>Male</th>
			<th>Female</th>
			<th>Total</th>
		</tr>
		<tr>
			<th>Population</th>
			<td><c:out value="${mandalInfoVO.totalMalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalFemalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalPersons}"/></td>
		</tr>
		<tr>
			<th>SC Population</th>
			<td><c:out value="${mandalInfoVO.totalSCMalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalSCFemalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalSCPersons}"/></td>
		</tr>
		<tr>
			<th>ST Population</th>
			<td><c:out value="${mandalInfoVO.totalSTMalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalSTFemalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalSTPersons}"/></td>
		</tr>
			
		<tr>
			<th>Literate Populations</th>
			<td><c:out value="${mandalInfoVO.totalLiterateMalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalLiterateFemalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalLiteratePersons}"/></td>
		</tr>
		<tr>
			<th>Illiterate Population</th>
			<td><c:out value="${mandalInfoVO.totalIlliterateMalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalIlliterateFemalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalIlliteratePersons}"/></td>
		</tr>
		<tr>
			<th>Working Population</th>
			<td><c:out value="${mandalInfoVO.totalWorkingMalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalWorkingFemalePersons}"/></td>
			<td><c:out value="${mandalInfoVO.totalWorkingPersons}"/></td>
		</tr>	
	</table>
</td>
<td>
<table class="ConstituencyElectionsTable" align="left" style="margin-left:50px">
	<tr>		
		<th><b>Village ID</b></th>
		<th><b>Village Name</b></th>
		<th><b>Total Populations</b></th>
		<th><b>SC Population</b></th>
		<th><b>ST Population</b></th>
		<th><b>Literate Population</b></th>
		<th><b>Illiterate Population</b></th>
		<th><b>Working Population</b></th>
	</tr>
	<c:forEach var="villageCensus" items="${villageDetailsVO.villageCensusList}" >	
		<tr>
			<td ><c:out value="${villageCensus.townshipID}"/></td>
			<td ><c:out value="${villageCensus.townshipName}"/></td>
			<td ><c:out value="${villageCensus.totalPersons}"/></td>
			<td ><c:out value="${villageCensus.totalSCPersons}"/></td>
			<td ><c:out value="${villageCensus.totalSTPersons}"/></td>
			<td ><c:out value="${villageCensus.totalLiteratePersons}"/></td>
			<td ><c:out value="${villageCensus.totalIlliteratePersons}"/></td>
			<td ><c:out value="${villageCensus.totalWorkingPersons}"/></td>
		</tr>
	</c:forEach>
</table>
</td>
</tr>
</body>
</html>