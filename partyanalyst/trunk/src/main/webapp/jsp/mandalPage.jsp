<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
				<c:forEach var="mandal" items="${mandalInfoVOList}" >	
					<tr>
						<td ><c:out value="${mandal.mandalID}"/></td>
						<td ><c:out value="${mandal.mandalName}"/> 						
						</td>
						<td ><c:out value="${mandal.totalPersons}"/></td>
						<td ><c:out value="${mandal.totalSCPersons}"/></td>
						<td ><c:out value="${mandal.totalSTPersons}"/></td>
						<td ><c:out value="${mandal.totalLiteratePersons}"/></td>
						<td ><c:out value="${mandal.totalIlliteratePersons}"/></td>
						<td ><c:out value="${mandal.totalWorkingPersons}"/></td>
					</tr>
				</c:forEach>
			
			</table>
</body>
</html>