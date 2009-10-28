<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.itgrids.partyanalyst.dto.ConstituencyVO" %>
<%@page import="com.itgrids.partyanalyst.dto.CandidateVO" %>
<%@page import="java.util.List" %>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<style type="text/css">
table.searchresultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width:500px;
	margin-top:10px;
}
table.searchresultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.searchresultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
</head>
<body>
	<div id="mainDiv">	
	<div>
	<div id="headerDiv" style="padding: 10px;font-weight:bold">
		Search results for "<s:property value="searchText" />".
	</div>
		<c:if test="${constituencySearchList != null }">
		
		<%System.out.println("IN constituency search list"); %>
	 	<table class="searchresultsTable" width="600px" border="1px">  
	  	<tr>  
        	<th style="padding:5px">Constituency</th>  
         	<th style="padding:5px">Election Type</th>  
         	<th style="padding:5px">District</th>
         	<th style="padding:5px">State</th>
         	<th style="padding:5px">Delemination</th>
     	</tr>  
   		<s:iterator value="constituencySearchList" id="ConstituencyVO" status="cust_stat">  
   		<tr>		
      	 	<td width="100px" style="padding:5px">
      	 	<c:url value="constituencyPageAction.action" var="displayconstituencyURL">
			<c:param name="constituencyId"   value="${id}" />
					  
			</c:url>
			
      	 	<a href='<c:out value="${displayconstituencyURL}" />'><s:property value="name" /></a></td>
       		<td width="100px" style="padding:5px"><s:property value="electionType" /> </td>
       		<td width="120px" style="padding:5px"><s:property value="districtName" /></td>
       		<td width="180px" style="padding:5px"><s:property value="stateName" /></td>       
       		<td width="100px" style="padding:5px"><s:property value="delemitationInfo" /></td>
   		</tr> 
   		</s:iterator>
   		</table>
   		</c:if>
   		<c:if test="${candidatateSearchList != null }">
   		<%System.out.println("IN candidate search list"); %>
		
		<s:iterator value="candidatateSearchList" id="CandidateVO" status="cust_stat">  
   			<div id="searchMainDiv" style="background-color:#FAFAFA; width: 420px; font-family: sans-serif;">
			<div id="searchDetailsTableDiv">
				<table class="searchresultsTable" border="1">
					<tr>
						<th>Name</th>
						<td><c:url value="candidateElectionResultsAction.action" var="candidateName">
			                <c:param name="candidateId"   value="${id}" />
					  
			                </c:url>
							<a href='<c:out value="${candidateName}" />'><s:property value="candidateName" /></a></td>
						<td rowspan="3" style="padding:0px;width:90px;">
							<div id="photoMainDiv" style="background-color: Gainsboro; height: 90px; width: 90px;">
								<c:if test="${empty image}">
									<div id="photoDiv">
										<img  height="90" width="90" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" >
									</div>
								</c:if>
								<c:if test="${!empty image}">
									<div id="photoDiv">
										<img  height="90" width="90" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" /><s:property value="image"/>" >
									</div>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>Party</th>
						<td><s:property value="party" /></td>
					</tr>						
					<tr>
						<th>Year</th>
						<td><s:property value="year" /></td>								
					</tr>				
					<tr>
						<td colspan="3">
							<div style="color:#4C5157; font-weight: bold; font-size: 12px;">Constituencies Participated...</div>
						</td>
					</tr>
					<tr>						
						<th style="text-align: center;">Constituency</th>
						<th style="text-align: center;">Election</th>
						<th style="text-align: center;">Position</th>
					</tr>							
					<c:forEach var="candidateElectionVOs" items="${CandidateVO.candidateElectionVOs}">
					<tr>
						<td style="text-align: center;">${candidateElectionVOs.constituencyName}</td>
						<td style="text-align: center;">${candidateElectionVOs.scope}</td>
						<td style="text-align: center;">${candidateElectionVOs.position}</td>
					</tr>
					</c:forEach>					
				</table>					
			</div>
		</div>
		</s:iterator>
	 <!-- Siva -->
   		</c:if>
    </div>
   </div> 
</body>
</html>
