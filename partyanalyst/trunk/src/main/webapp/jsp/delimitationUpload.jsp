<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delimitation Mappings Upload</title>
<style>

.scopeWise_head {
	background-color:#F5EFEA;
	color:#77471D;
	font-weight:bold;
	padding:3px;
	text-align:left;
}
.resultsTable
{
	width:100%;	
}
.resultsTable th
{
	text-align:left;	
}
</style>
</head>
	<body>
	<s:form name="DelimitationMappingsUploadAction" action="delimitationMappingsUploadAction" method="post" enctype="multipart/form-data" >
	<h3>Delimitation Mappings Upload</h3>
		<table style="border-collapse:collapse" border="2">
			<tr>
				<td align="center"><b>Country</b></td>
				<td align="left">
					<select name="country">
						<option value="India">India</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center"><b> Delimitation Year </b></td>
				<td align="left">
					<select name="delimitationYear">
						<option value="0">Select option..</option>
						<option value="2009">2009</option>
						<option value="2004">2004</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td align="center"><b> Census Year </b></td>
				<td align="left">
					<select name="censusYear">
						<option value="0">Select option..</option>
						<option value="2001">2001</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td> <s:file name="filePath" label="Upload  File Path" /></td>
			</tr>			
			<tr>
				<td colspan="2" align="center"><s:submit name="upload" value="Upload" align="center"/></td>
			</tr>
			
		</table>
	</s:form>

	<c:if test="${delimitationMappingUploadVO != null}">
	  <c:if test="${delimitationMappingUploadVO.delimitationYear != null}">
	   <table>
	      <tr>
		     <td><b>Delimitation Year :<b></td>
		     <td><c:out value="${delimitationMappingUploadVO.delimitationYear}" /></td>
		  </tr>

		  <tr>
		     <td>Executed Last Row No :</td>
		     <td><c:out value="${delimitationMappingUploadVO.currentRow}" /></td>
		   </tr>

		  <tr>
		     <td>Executed Last Sheet No:</td>
		     <td><c:out value="${delimitationMappingUploadVO.currentSheet}" /></td>
		  </tr>

          <c:if test="${delimitationMappingUploadVO.mappedConstituencies != null}">
		  <tr>
		   <td>Mapped Constituencies :</td>
			  <c:forEach var="consti" items="${delimitationMappingUploadVO.mappedConstituencies}">
						
				 <td><c:out value="${consti}" />,</td>
			  
			  </c:forEach>
		  </tr>
		  </c:if>

		  <c:if test="${delimitationMappingUploadVO.exceptionEncountered != null}">
		    <tr>
			</td><b>Exception Encountered :</b></td>
		    <c:out value="${delimitationMappingUploadVO.exceptionEncountered}" />
			</tr>
		  </c:if>

	   </table>

	  </c:if>
	</c:if>
	</body>
</html>