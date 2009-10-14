<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Login</title>
</head>
	<body>
		<s:form name="BoothResultUploadForm" action="boothResultUploadAction" method="POST" >
			<s:textfield name="constituencyName" label="Constytuency Name:"/>
			<s:textfield name="districtName" label="District Name:"/>
			<s:textfield name="electionType" label="Election Type:"/>
			<s:textfield name="electionYear" label="Election Year:"/>
			<s:textfield name="filePath" label="File Path:"/>
			<s:submit value="Submit" align="center"/>
		</s:form>
	</body>
</html>