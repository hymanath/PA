<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>District Page</title>
  <style type="text/css">
		.detailsDiv
		{
			margin-top:10px;
			margin-bottom:10px;
			margin-right:30px;
			margin-left:30px;
			text-align:left;
		}
		.detailsHead
		{
			font-weight:bold;
			color:#1C4B7A;
			text-decoration:underline;
			font-size:15px;
			padding:5px 5px 5px 0px;
			text-
		}
		.detailsBody
		{
			padding:5px;
			background-color:#F1F5F7;
		}				
		#districtAncSpan
		{
			padding:10px;
		}
		.districtAnc
		{
			color:#1C4B7A;
		}	
	</style>
</head>
<body>
<div class="detailsHead">
		Welcome to <c:out value="${districtName}"></c:out> District Page <br/><br/>
</div>
<div id="districtInfoDiv" class="detailsDiv">
	<div id="districtInfoDivHead" class="detailsHead">
		Constituencies after Delimitation ${constituenciesStatusVO.delimitationYear}
	</div>
		<div id="districtInfoDivBody" class="detailsBody">

		<table><tr>
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.existConstituencies}">			
				<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${result.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 5==0}">
				</tr><tr><td colspan="5"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.newConstituencies}">			
				<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}"> *${result.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 5==0}">
				</tr><tr><td colspan="5"> </td></tr><tr>
			</c:if>			
		</c:forEach>		
		</tr></table>		
	</div>
	&nbsp &nbsp	* indicates New Constituencies after Delimitation
</div>

<div id="districtInfoDiv" class="detailsDiv">
<div id="districtInfoDivHead" class="detailsHead">
		Constituencies before Delimitation ${constituenciesStatusVO.delimitationYear}
	</div>

	<div id="districtInfoDivBody" class="detailsBody">
		<table><tr>
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.deletedConstituencies}">			
				<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${result.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 7==0}">
				</tr><tr><td colspan="7"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr></table>		
	</div>
</div>
</div>

<div id="districtInfoDiv" class="detailsDiv">
<div id="districtInfoDivHead" class="detailsHead">
		MLA's in the District
	</div>

	<div id="districtInfoDivBody" class="detailsBody">
		<table>
			<tr>
				<th>Constituency Name &nbsp</th>
				<th>Candidate Name &nbsp</th>
				<th>Party Name &nbsp</th>
			</tr>
			<c:forEach var="candidate" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">			
				<tr>
					<td><c:out value="${candidate.constituencyName}"/> &nbsp </td>
					<td><c:out value="${candidate.candidateName}"/> &nbsp </td>
					<td><c:out value="${candidate.partyName}"/></td>
				</tr>
			</c:forEach>
		</table>		
	</div>
</div>
<div id="mandalInfoDiv" class="detailsDiv">
	<div id="mandalInfoDivHead" class="detailsHead">
		Mandal Info 
	</div>

	<div id="mandalInfoDivBody" class="detailsBody">
		<table><tr>
		<c:forEach var="mandalsBeforeDelimitationConstituency" varStatus="stat" items="${mandals}">				
			<td>
				<span id="mandalAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="mandalPageAction.action?MANDAL_ID=${mandalsBeforeDelimitationConstituency.id}&MANDAL_NAME=${mandalsBeforeDelimitationConstituency.name}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mandalsBeforeDelimitationConstituency.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 7==0}">
				</tr><tr><td colspan="7"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr></table>		
	</div>
	</div>
</div>
</body>
</html>