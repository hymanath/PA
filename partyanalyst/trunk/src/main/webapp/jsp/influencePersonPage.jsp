<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Influence Person Page</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
<style type="text/css">

	
	body
	{
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
	}

	#influencingPeopleDetails_main
	{
		padding:20px;
	}

	#influencingPeopleDetails_head
	{
		color:#8E320A;
		font-size:17px;
		font-weight:bold;
		text-align:center;
		text-decoration:underline;
		padding:5px;
		margin-top:10px;
	}

	.influencePersonInfoTable th
	{
		color:#652D2D;
		padding:4px;
		text-align:left;
	}

	.influencePersonInfoTable td
	{
		color:#31383C;
		padding:4px;
		text-align:left;
	}
	
	fieldset {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;		
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		padding:5px;
		font-weight:bold;
	}

</style>

</head>
<body>
	
	<div id="influencingPeopleDetails_main">

		<div id="influencingPeopleDetails_head">
			 ${influencingPersonVO.firstName} Complete Profile
		</div>

		<div id="influencingPeopleDetails_body">
				<table width="100%">
					<tr>
						<td>
							<div id="personalDetailsDiv">
								<fieldset>
									<legend> Personal Details</legend>
									<table class="influencePersonInfoTable" width="100%">
										<tr>
											<th> First Name</th>
											<td> ${influencingPersonVO.firstName} </td>
											<td rowspan="4"><img height="100px" width="110px" src="images/icons/indexPage/human.jpg"></td>
										</tr>
										<tr>
											<th> Last Name</th>
											<td> ${influencingPersonVO.lastName} </td>
										</tr>								
										<tr>
											<th> Gender</th>
											<td> ${influencingPersonVO.gender} </td>
										</tr>
										<tr>
											<th> Father/Spouse Name</th>
											<td> ${influencingPersonVO.fatherOrSpouseName} </td>
										</tr>									
									</table>
								</fieldset>
							<div>
						</td>
						
					</tr>	
					<tr>
						<td colspan="2">
							<div id="contactDetails">
								<fieldset>
									<legend> Contact Details</legend>
									<table class="influencePersonInfoTable" width="100%">
										<tr>
											<th> Email</th>
											<td> ${influencingPersonVO.email} </td>

											<th> Mobile</th>
											<td> ${influencingPersonVO.mobile} </td>
										</tr>
										
										<tr>
											<c:if test="${influencingPersonVO.houseNo != null && influencingPersonVO.houseNo != ''}">
												<th> House No</th>
												<td> ${influencingPersonVO.houseNo} </td>
											</c:if>	
											<c:if test="${influencingPersonVO.streetName != null && influencingPersonVO.streetName != ''}">
												<th> Street</th>
												<td> ${influencingPersonVO.streetName} </td>
											</c:if>	
										</tr>

										<tr>
											<th> State</th>
											<td> ${influencingPersonVO.state} </td>

											<th> District</th>
											<td> ${influencingPersonVO.district} </td>
										</tr>
										<tr>
											<th> Constituency</th>
											<td> ${influencingPersonVO.constituency} </td>
											
											<c:if test="${influencingPersonVO.mandal != null && influencingPersonVO.mandal != ''}">
												<th> Mandal/Municipality/Corporation</th>
												<td> ${influencingPersonVO.mandal} </td>
											</c:if>	
										</tr>

										<c:if test="${influencingPersonVO.wardOrHamlet != null && influencingPersonVO.wardOrHamlet != ''}">
										<tr>
											<th> Ward/Village</th>
											<td> ${influencingPersonVO.wardOrHamlet} </td>											
										</tr>
										</c:if>

										<tr>
											<c:if test="${influencingPersonVO.booth != null && influencingPersonVO.booth != ''}">
												<th> Booth</th>
												<td> ${influencingPersonVO.booth} </td>
											</c:if>

											<c:if test="${influencingPersonVO.pincode != null && influencingPersonVO.pincode != ''}">
												<th> Pincode</th>
												<td> ${influencingPersonVO.pincode} </td>
											</c:if>
										</tr>
									</table>	
								</fieldset>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div>
								<fieldset>
									<legend> Social Status</legend>
									<table class="influencePersonInfoTable" width="100%">
										<tr>
											<th> Cast</th>
											<td> ${influencingPersonVO.cast} </td>
										</tr>
										<tr>
											<th> Occupation</th>
											<td> ${influencingPersonVO.occupation} </td>
										</tr>
										<tr>
											<th> Party</th>
											<td> ${influencingPersonVO.party} </td>
										</tr>
										<tr>
											<th> Position</th>
											<td> ${influencingPersonVO.position} </td>
										</tr>
										
										<tr>
											<th> Influence Range</th>
											<td> ${influencingPersonVO.influencingRangeScope} </td>
										</tr>
										
										<tr>
											<th> Influence Region</th>
											<td> ${influencingPersonVO.influencingScopeValue} </td>
										</tr>
										
									</table>
								</fieldset>
							</div>
						</td>
					</tr>
				</table>			
		</div>
	
	</div>

</body>
</html>