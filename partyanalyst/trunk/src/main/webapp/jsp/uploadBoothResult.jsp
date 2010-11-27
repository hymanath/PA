<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Booth Result Upload</title>

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
	<s:form name="BoothResultUploadForm" action="boothResultUploadAction" method="POST" enctype="multipart/form-data">
		<h3>Booth Results Upload</h3>
		<table border="1" style="border-collapse:collapse">
			<tr>
				<td>Election Type</td>
				<td align="left">
					<select name="electionScopeId">
						<option value="0">Select option..</option>
						<option value="1">Parliament</option>
						<option value="2">AP Assembly</option>
					</select>
				</td>
			</tr>
			<tr>
				<td> Election Year</td>
				<td align="left">
					<select name="electionYear">
						<option value="0">Select option..</option>
						<option value="2010">2010</option>
						<option value="2009">2009</option>
						<option value="2008">2008</option>
						<option value="2006">2006</option>	
						<option value="2004">2004</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td><s:file name="filePath" label="File Path" /></td>
			</tr>			
			<tr>
				<td colspan="2"><s:checkbox name="isValidate" id="isValidate" label="Validate Data"/></td>
				<td colspan="2"><s:submit name="upload" value="Upload" align="center"/></td>
			</tr>
		</table>
	</s:form>
	<c:if test="${!empty uploadInfo}">
		<div style="margin-top: 10px;">
			<c:forEach var="result" items="${uploadInfo}" varStatus="">
				<div style="border:2px solid #F5EFEA;width:800px;margin-bottom:15px;text-align:left;">			
					<DIV class="scopeWise_head">${result.constituencyName } Booth Results Upload Info</DIV>
					<table border="0" width="100%" class="resultsTable">
						<tr>
							<th>Booths In DB:</th>
							<td>${result.boothsInDB }</td>
							<th>Booths Identified:</th>
							<td>${result.boothsIdentified}</td>
							<th>Booths Results Inserted:</th>
							<td>${result.boothsInserted}</td>
							<th>Total Candidates Identified:</th>
							<td>${result.totalCandidates }</td>
						</tr>
					</table>
					<div>			
						<hr style="margin:10px;">
						<c:if test="${! empty result.uploadInfo}">
							<b>Validations and Corrections:</b>
							<table>
								<c:forEach var="info" items="${result.uploadInfo}" varStatus="">
									<tr><td>${info}</td></tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty result.uploadInfo}">
							<b>No Corrections Or Validations Required For This Constituency</b>
						</c:if>
					</div>	
				</div>
			</c:forEach>
		</div>
	</c:if>		
	</body>
</html>