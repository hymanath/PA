<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Booth Data Upload</title>
</head>
	<body>
	<s:form name="BoothUploadAction" action="boothUploadAction" method="POST" >
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
				<td>Select File</td>
				<td> <input name="filePath" type="file"/></td>
			</tr>			
			<tr>
				<td colspan="2"><s:submit name="upload" value="Upload" align="center"/></td>
			</tr>
		</table>
	</s:form>
	</body>
</html>