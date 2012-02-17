<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:form name="UploadExcelAction" action="uploadExcel" method="post" enctype="multipart/form-data">
 	<h3>Election Data Upload</h3>
	<table border="1">
		<tr>
			<td>Country</td>
			<td><select name="country">
				<option value="0">Select option..</option>
				<option value="India" selected="selected">India</option>
			</select></td>
		</tr>
		<tr>
			<td>Election Scope</td>
			<td><select name="electionScope">
				<option value="0">Select option..</option>
				<option value="Andhra Pradesh">Andhra Pradesh</option>
				<option value="Tamil Nadu">Tamil Nadu</option>
				<option value="Karnataka">Karnataka</option>
				<option value="West Bengal">West Bengal</option>
				<option value="Assam">Assam</option>
				<option value="Kerala">Kerala</option>
				<option value="Puducherry">Puducherry</option>
				<option value="Punjab">Punjab</option>
				<option value="Uttar Pradesh">Uttar Pradesh</option>
				<option value="Uttaranchal">Uttaranchal</option>
				<option value="Manipur">Manipur</option>
				<option value="Goa">Goa</option>
			</select></td>
		</tr>
		<tr>
			<td>District</td>
			<td><select name="district">
				<option value="0">Select option..</option>
				<option value="Adilabad" selected="selected">Ailabad</option>
			</select></td>
		</tr>
		<tr>
			<td>Election Type</td>
			<td><select name="electionType">
				<option value="0">Select option..</option>
				<option value="Census">Census</option>
				<option value="Assembly">Assembly</option>
				<option value="Parliament">Parliament</option>
			</select></td>
		</tr>
		<tr>
			<td>Election Subtype</td>
			<td><select name="elecSubtype">
				<option value="0">Select option..</option>
				<option value="MAIN">Main Election</option>
				<option value="BYE">Bye Election</option>
			</select></td>
		</tr>
		<tr>
			<td>Election Status</td>
			<td><select name="isElectionResults">
				<option value="0">Select option..</option>
				<option value="RESULTS">Election Results</option>
				<option value="NOMINATIONS">Candidate Nominations</option>
			</select></td>
		</tr>
		<tr>
			<td><s:file name="inputFile" label="InputFile" /></td>
		</tr>
		<tr>
			<td colspan="2"><s:textfield label="Election Year" name="electionYear" /><s:submit name="upload" value="Upload" /></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>