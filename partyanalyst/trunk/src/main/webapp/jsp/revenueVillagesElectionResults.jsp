<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election results in all revenue villages of ${tehsilName } Mandal </title>

</head>
<body>
	<div style="color:#24455E;font-size:17px;font-weight:bold;margin-bottom:10px;margin-top:23px;text-decoration:underline;">All Parties Trends In All Elections Of ${tehsilName } Mandal</div>
	<div>
	<c:if test="${! empty mandalVO}">
		<s:form action="mandalRevenueVillagesElecViewAction" name="MandalRevenueVillagesElecViewAction" method="GET" enctype="multipart/form-data">
			<table>
				<tr><td><s:checkboxlist list="mandalVO.partiesInMandal" listKey="id" listValue="name" name="parties" label="Parties:"/></td></tr>
				<tr><td><s:checkboxlist list="mandalVO.electionsInMandal" listKey="id" listValue="name" name="elections" label="Elections:"/></td></tr>
				<tr><td><input type="hidden" name="tehsilId" value="${tehsilId}"/></td><td><input type="hidden" name="tehsilName" value="${tehsilName}"/></td><td align="center"><s:checkbox theme="simple" id="allianceCheck" name="includeAlliance" value="hasAllianceParties"></s:checkbox><b> Include Aliance Parties</b></td></tr>
				<tr><td colspan="2" align="center"><s:submit label="View Chart" /></td></tr>
			</table>
		</s:form>
	</c:if>
	</div>
	<div>
		<c:if test="${! empty chartPath}">
			<img src="charts/${chartPath}">
		</c:if>
	</div>
</body>
</html>