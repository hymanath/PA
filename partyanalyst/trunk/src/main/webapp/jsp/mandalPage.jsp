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

</body>
</html>