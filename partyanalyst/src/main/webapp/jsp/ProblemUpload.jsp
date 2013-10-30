<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problem Upload</title>
</head>
	<body>
<s:if test="#session.USER !=null">
<s:if test="#session.USER.isAdmin == 'true'">	<s:form name="ProblemUploadAction" action="problemUploadAction" method="post" enctype="multipart/form-data" >
	<h3>Problem Upload</h3>
	  <table border="1" style="border-collapse:collapse">
	     <tr>
			<td> <s:file name="filePath" label="File Path" /></td>
		 </tr>			
		 <tr>
			<td> Year</td>
			<td align="left">
				<select name="year">
					<option value="0">Select option..</option>
					<option value="2009">2009</option>
				</select>	
			</td>			
		</tr>
		 <tr>
			<td> State</td>
			<td align="left">
				<select name="stateName">
					<option value="0">Select option..</option>
					<option value="AndhraPradesh">AndhraPradesh</option>
				</select>	
			</td>			
		</tr>
		<tr>
			<td colspan="2"><s:checkbox name="isValidate" id="isValidate" label="Validate Data"/></td>
			<td colspan="2"><s:submit name="upload" value="Upload" align="center"/></td>
		 </tr>
		 </table>
	</s:form>
	<c:if test="${!empty corrections}">
		<div style="text-align:left;margin-left:50px;">
			<c:forEach var="result" items="${corrections}" varStatus="">
				<c:out value="${result}"></c:out><br/>
			</c:forEach>
		</div>
	</c:if>				
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