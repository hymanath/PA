<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Local Body Elections Upload</title>
</head>
<body>
<c:if test="${resultVO.electionType != null}">
Exception Occured While Reading Row:${resultVO.currentRow} <br>
</c:if>

Failure:<%out.println(((com.itgrids.partyanalyst.dto.MPTCElectionResultVO)request.getAttribute("resultVO")).getExceptionEncountered().getMessage());%>

</body>
</html>