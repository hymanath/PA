<%@page import="com.itgrids.partyanalyst.utils.IConstants"%><% 	String host = IConstants.DEPLOYED_HOST;	String temp_host = IConstants.DEPLOYED_TEMP_HOST;	if(host.equalsIgnoreCase("tdpserver"))	  response.sendRedirect("home.action");	else if(temp_host.equalsIgnoreCase("tdpserver"))		  response.sendRedirect("home.action");	else      response.sendRedirect("homePage.action");%>