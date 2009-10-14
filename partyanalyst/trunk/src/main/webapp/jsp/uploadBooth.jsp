<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Login</title>
</head>
	<body>
		<s:form name="BoothUploadAction" action="boothUploadAction" method="POST" >
			<s:textfield name="electionType" label="Election Type:"/>
			<s:textfield name="electionYear" label="Election Year:"/>
			<s:textfield name="filePath" label="File Path:"/>
			<s:submit value="Submit" align="center"/>
		</s:form>
	</body>
</html>