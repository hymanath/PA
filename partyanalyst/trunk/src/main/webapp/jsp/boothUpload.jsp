<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booth Data Upload</title>
</head>
	<body>
	<s:form name="BoothUploadAction" action="boothUploadAction" method="post" enctype="multipart/form-data" >
		<table border="1" style="border-collapse:collapse">
			<tr>
				<td>Election Type</td>
				<td align="left">
					<select name="electionScopeId">
						<option value="0">Select option..</option>
						<option value="1">Parliament</option>
						<option value="2">AP Assembly</option>
						<option value="4">MH Assembly</option>
					</select>
				</td>
			</tr>
			<tr>
				<td> Election Year</td>
				<td align="left">
					<select name="electionYear">
						<option value="0">Select option..</option>
						<option value="2009">2009</option>
						<option value="2004">2004</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td> <s:file name="filePath" label="File Path" /></td>
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
	</body>
</html>