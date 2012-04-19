<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:if test="#session.USER !=null">
<s:if test="#session.USER.isAdmin == 'true'">
<s:form action="voterDataUploadAction" method="POST" enctype="multipart/form-data">
	<h3>Voter Data Upload</h3>
	<table border="1">
		<tr>
			<td>Election Type</td>
			<td><select name="electionTypeId">
				<option value="0">Select option..</option>
				<option value="2">Assembly</option>
				<option value="1">Parliament</option>
			</select></td>
		</tr>
		<tr>
			<td>State</td>
			<td><select name="stateId">
				<option value="0">Select option..</option>
				<option value="1">Andhra Pradesh</option>
				<option value="24">Tamilnadu</option>
			</select></td>
		</tr>
		<tr>
			<td>Election Year</td>
			<td><select name="electionYear">
				<option value="0">Select option..</option>
				<option value="2009">2009</option>
				<option value="2004">2004</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><s:file name="filePath" label="File Path" /></td>
		</tr>
		<tr><td><s:checkbox label="%{getText('validateData')}" name="validateData"/></td></tr>
		<tr><TD><s:submit name="upload" value="Upload" /></TD></tr>	
	</table>
	<c:if test="${!empty corrections}">
		<div style="text-align:left;margin-left:50px;">
			<c:forEach var="result" items="${corrections}" varStatus="">
				<c:out value="${result}"></c:out><br/>
			</c:forEach>
		</div>
	</c:if>	
</s:form>
</s:if>
<s:else>
<%
	response.sendRedirect("userEntitlementAction.action");
%>
</s:else>
</s:if>
<s:else>
<%
	response.sendRedirect("loginInputAction.action");
%>
</s:else>
</body>
</html>