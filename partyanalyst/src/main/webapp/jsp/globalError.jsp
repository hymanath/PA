<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.itgrids.partyanalyst.utils.IConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Us</title>
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">
 <link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" /> 
</head>
<body>

<div id="constituencyCenterContentOuter" style="height: 600px;background-color:#E4EDF0;padding:100px;">
	<div id="constituencyCenterContentOuter" class="rounded" style="margin-top:0px;height:185px;width:750px;">					
		<div class="corner topLeft"></div>
		<div class="corner topRight"></div>
		<div class="corner bottomLeft"></div>
		<div class="corner bottomRight"></div> 

		<div id="constituencyPageRightMapDiv1" class="contentDivClass" style="height:90%;">
			<div style="margin-top:5px;margin-bottom:20px;">
				<font color="red" size="3px"><b>Sorry! we are unable to process your request, click here to continue
				<c:choose>       
			      <c:when test="${fn:contains(theString, 'tdpserver')}">
			      <c:set var="theString" value='<%=IConstants.DEPLOYED_HOST%>'/>
				     <a href="home.action">Click here</a>
			     </c:when>
				 <c:otherwise>
			     	<a href="home.action">Click here</a>
				 </c:otherwise>
			</c:choose>
			</b>
				</font><br><br>
			</div>
			<!--
			<div style="background:none repeat scroll 0 0 #CEDCF3;border:1px solid #9ABAED;">
			<table width="100%">
			<tr>
				<td><img src="images/icons/homePage_new/logo.png" height="80px" width="170px"></td>
				<td>
					<table >
					
					<tr><td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>
					<tr><td>Hyderabad.<br></td></tr>
					<tr><td>Mobile: +91 96766 96760<br></td></tr>
					<tr><td>Email: info@partyanalyst.com or a.dakavaram@itgrids.com<br></td></tr>
				</table>
				</td>
				<td><img src="images/icons/homePage_new/itgrids_logo.gif" height="80px" width="170px"></td>				
			</tr>
			</table>	
				
			</div>
			-->
		</div>	
	</div>
</div>	
</body>
</html>