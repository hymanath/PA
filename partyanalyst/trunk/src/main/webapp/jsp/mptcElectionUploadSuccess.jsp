<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Local Body Elections Upload</title>
</head>
<body>
<table>
	<tr>
		<td>Successfully added the Election Data</td>
	</tr>
	<tr>
		<td>No. of constituency Election Results added:</td>
		<td> <c:out value="${resultVO.constituencyElectionResults}" /> </td>
	</tr>
	<tr>
		<td>No. of constituency Election added:</td>
		<td><c:out value="${resultVO.constituencyElections}" /></td>
	</tr>
	<tr>
		<td>No. of Nomination added:</td>
		<td><c:out value="${resultVO.nominations}" /></td>
	</tr>
	<tr>
		<td>No. of Parties added:</td>
		<td><c:out value="${resultVO.parties}" /></td>
	</tr>
	<tr>
		<td>No. of Candidates added:</td>
		<td><c:out value="${resultVO.candidates}" /></td>
	</tr>
	<tr>
		<td>No. of Candidate Results added: </td>
		<td><c:out value="${resultVO.candidateResults}" /></td>
	</tr>
</table>


</body>
</html>