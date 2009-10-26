<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<s:form action="partyBoothResult2Action">
		<s:select label="Select Constituency" name="constituencyName" id="constituencyNameSelect" list="constituencyVOs"  listKey="id" listValue="name" headerKey="0" headerValue="Select" />
		<s:hidden name="electionYear"></s:hidden>
		<s:hidden name="partyName"></s:hidden>
		<s:submit value="Get Booth Results"/>
		</s:form>
</body>
</html>