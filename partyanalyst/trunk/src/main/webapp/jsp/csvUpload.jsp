<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Data Upload</title>
</head>
<body>
<s:if test="#session.USER !=null">
<s:if test="#session.USER.isAdmin == 'true'">
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
				<option value='Andhra Pradesh'>Andhra Pradesh</option>
				<option value='Arunachal Pradesh'>Arunachal Pradesh</option>
				<option value='Assam'>Assam</option>
				<option value='Bihar'>Bihar</option>
				<option value='Chattisgarh'>Chattisgarh</option>
				<option value='Goa'>Goa</option>
				<option value='Gujarat'>Gujarat</option>
				<option value='Haryana'>Haryana</option>
				<option value='Himachal Pradesh'>Himachal Pradesh</option>
				<option value='Jammu & Kashmir'>Jammu & Kashmir</option>
				<option value='Jharkhand'>Jharkhand</option>
				<option value='Karnataka'>Karnataka</option>
				<option value='Kerala'>Kerala</option>
				<option value='Madhya Pradesh'>Madhya Pradesh</option>
				<option value='Maharashtra'>Maharashtra</option>
				<option value='Manipur'>Manipur</option>
				<option value='Meghalaya'>Meghalaya</option>
				<option value='Mizoram'>Mizoram</option>
				<option value='Nagaland'>Nagaland</option>
				<option value='Orissa'>Orissa</option>
				<option value='Punjab'>Punjab</option>
				<option value='Rajasthan'>Rajasthan</option>
				<option value='Sikkim'>Sikkim</option>
				<option value='Tamil Nadu'>Tamil Nadu</option>
				<option value='Tripura'>Tripura</option>
				<option value='Uttaranchal'>Uttaranchal</option>
				<option value='Uttar Pradesh'>Uttar Pradesh</option>
				<option value='West Bengal'>West Bengal</option>
				<option value='Andaman & Nicobar Islands'>Andaman & Nicobar Islands</option>
				<option value='Chandigarh'>Chandigarh</option>
				<option value='Dadar & Nagar Haveli'>Dadar & Nagar Haveli</option>
				<option value='Daman & diu'>Daman & diu</option>
				<option value='Delhi'>Delhi</option>
				<option value='Lakshadweep'>Lakshadweep</option>
				<option value='Puducherry'>Puducherry</option>
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