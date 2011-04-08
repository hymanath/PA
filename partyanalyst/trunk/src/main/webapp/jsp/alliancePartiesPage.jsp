<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alliance Parties In ${alliancePartiesInDistrict.districtName} District In Different Elections</title>
<script>window.history.forward(1);</script>
<style>
	#alliancePartiesMain fieldset
	{
		background-color:Ivory;
        margin-top:25px;
        width:75%;
	}
	
	#alliancePartiesMain fieldset legend
	{
		background-color:BurlyWood;
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
		background-color:DarkGray;
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
						<table>
						<c:forEach var="group"  items="${election.partiesAlliances}">
						<tr>
							<td>
									<div class="allianceName">${group.allianceGroupName }</div>
									<div>
										<c:forEach var="party"  items="${group.partiesInAlliance}">
										<td></td>
										<td>${party.partyName}</td>
										</c:forEach>
									</div>
							</td>
						</tr>
						</c:forEach>
						</table>		
				</fieldset>
			</c:forEach>
		</div>
</body>
</html>