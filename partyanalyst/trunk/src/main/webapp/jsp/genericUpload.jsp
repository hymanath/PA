<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Data Upload</title>
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
	<s:form name="cadreDataUpload" action="genericUploadAction" method="post" enctype="multipart/form-data" >
    <h3>Cadre Data Upload</h3>
	<table style="border-collapse:collapse" border="2">
	     <tr>
				<td align="center"><b>Country</b></td>
				<td align="left">
					<select name="country">
						<option value="1">India</option>
					</select>
				</td>
		 </tr>
		 <tr>
				<td align="center"><b>Module</b></td>
				<td align="left">
					<select name="module">
						<option value="CADRE">Cadre</option>
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

    <c:if test="${loginDetails != ''}">
	  <BR>
	     <H3><c:out value="${loginDetails}" /></H3>
	  <BR>
	</c:if>
	<c:if test="${uploadResultsVO != null}">
      
	   <BR>
	     <H3><c:out value="${uploadResultsVO.uploadStatus}" /></H3>
	   <BR>
	   <table>
	   
	   <tr>
	     <td style="font-size:14px;"> Total Sheets  </td>      
		 <td style="font-size:14px;">:</td>
		 <td style="font-size:14px;"><c:out value="${uploadResultsVO.totalSheets}" /> </td>
	   </tr>
	   <tr>
	     <td style="font-size:14px;"> Last Sheet Executed </td>
		 <td style="font-size:14px;">:</td>
		 <td style="font-size:14px;"><c:out value="${uploadResultsVO.currentSheet}" /> </td>
	   </tr>
	   <tr>
	     <td style="font-size:14px;"> Total rows Executed </td>
		 <td style="font-size:14px;">:</td>
		 <td style="font-size:14px;"><c:out value="${uploadResultsVO.totalRowsExecuted}" /> </td>
	   </tr>	
	  </table>
	</c:if>
    <c:if test="${uploadStatus != null && uploadStatus == false}">
	  
	  <BR>
	   <H3>Failed Due TO <c:out value="${uploadResultsVO.exceptionEncountered}" /> ..</H3>
      <BR>
	</c:if>

	</body>
</html>