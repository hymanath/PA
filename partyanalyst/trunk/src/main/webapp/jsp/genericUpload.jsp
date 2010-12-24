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
			<td> <s:file name="filePath" label="Upload  File Path" /></td>
		</tr>			
		<tr>
			<td colspan="2" align="center"><s:submit name="upload" value="Upload" align="center"/></td>
		</tr>
	</table>
	</body>
</html>