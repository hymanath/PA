<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alliance Parties In ${alliancePartiesInDistrict.districtName} District In Different Elections</title>
<style>
	#alliancePartiesMain fieldset
	{
		border:5px solid #9BAAB6;
		width:50%;
		margin-top:10px;
	}
	
	#alliancePartiesMain fieldset legend
	{
		background-color:#3073A6;
		color:#FFFFFF;
		font-weight:bold;
		padding:3px;
	}
	
	.groupName
	{
		border:1px solid #DFDFDF;
	}
	.allianceName
	{
		background-color:#5E6F92;
		color:#FFFFFF;
		font-weight:bold;
		padding:5px;
	}
	
	.alliancePartyName
	{
		background-color:#D4D9E4;
		font-size:11px;
		margin-top:2px;
		padding:5px;
	}
</style>
</head>
<body>
		<div id="alliancePartiesMain">
			<c:forEach var="election"  items="${alliancePartiesInDistrict.alliancePartiesInElection}">			
				<fieldset>
					<legend>${election.electionType} - ${election.electionYear}</legend>
						<table width="100%" style="width:100%">
						<tr>
						<c:forEach var="group"  items="${election.partiesAlliances}">
							<td>
								<div class="groupName">
									<div class="allianceName">${group.allianceGroupName }</div>
									<div>
										<c:forEach var="party"  items="${group.partiesInAlliance}">
											<div class="alliancePartyName">${party.partyName}</div>
										</c:forEach>
									</div>
								</div>
							</td>
						</c:forEach>
						</tr>
						</table>		
				</fieldset>
			</c:forEach>
		</div>
</body>
</html>