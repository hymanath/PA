<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>District Page</title>


<!-- YUI files dependencies (start) -->

<!--CSS files (default YUI Sam Skin) -->
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
 
<!--JS files Dependencies -->
<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/json/json-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/get/get-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

<!-- YUI files dependencies (end) -->


<script type="text/javascript">
	function initializeResultsTable() {

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("mlaDataSortingTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constituencyName",formatter:YAHOO.widget.DataTable.formatLink
		}, {
			key : "candidateName",formatter:YAHOO.widget.DataTable.formatLink
		}, {
			key : "partyName"
		}, {
			key : "partyFlag"
		}, {
			key : ""
		}]
	};

	var resultsColumnDefs = [ {
		key : "constituencyName",
		label : "Constituency Name",
		sortable : true
	}, {
		key : "candidateName",
		label : "Candidate Name",
		sortable : true
	}, {
		key : "partyName",
		label : "Party Name",
		sortable : true
	}, {
		key : "partyFlag",
		label : "Party Flag"	
	}, {
		key : "",
		label : "Complete Results"	
	}];

	var myConfigs = {
    paginator : new YAHOO.widget.Paginator({
        rowsPerPage: 10
    })
};


	var myDataTable = new YAHOO.widget.DataTable("mlaInfoDivBody",resultsColumnDefs, resultsDataSource,myConfigs);  

}

function getConstituencyElecResultsWindow(constiId)
{
   var browser1 = window.open("","http://localhost:8080/PartyAnalyst/constituencyElectionResultsAction.action?constituencyId="+constiId,"browser1");
   browser1.focus();
}
</script>

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
			font-size:12px;
		}
		.districtAnc
		{
			color:#1C4B7A;
		}	
		.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
		{
			background:none;
		}

		.yui-skin-sam thead {

			background-color:#C4DEFF;
			color:#3F546F;
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
			<c:if test="${stat.count % 4==0}">
				</tr><tr><td colspan="4"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		
		</tr><tr><td colspan="4"></td></tr><tr>
		<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.newConstituencies}">
			<td>
				<span id="districtAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">* ${result.name}
					</a>
				</span>
			</td>	
			<c:if test="${stat.count % 4==0}">
				</tr><tr><td colspan="4"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr>
		</table>		
	</div>
	&nbsp &nbsp	* indicates New Constituencies after Delimitation
</div>



<div id="districtInfoDiv" class="detailsDiv">
<div id="districtInfoDivHead" class="detailsHead">
		 Dissolved Constituencies in Delimitation ${constituenciesStatusVO.delimitationYear}
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


<div id="districtInfoDiv" class="detailsDiv">
<div id="districtInfoDivHead" class="detailsHead">
		MLA's in the District
	</div>

	<div id="mlaInfoDivBody" class="yui-skin-sam">
		<table  id="mlaDataSortingTable">
			
			<c:forEach var="candidate" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">			
				<tr>
					<td>
						<span id="districtAncSpan">
							<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${candidate.constituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.constituencyName}
							</a>
						</span>
					</td>
					<td>
					<span id="districtAncSpan">
							<a href="candidateElectionResultsAction.action?candidateId=${candidate.candidateId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.candidateName}
							</a>
						</span>
					 &nbsp </td>
					<td><c:out value="${candidate.partyName}"/></td>
					<td><img src="<%=request.getContextPath()%>/images/party_flags/${candidate.partyFlag}" height="30" width="40"/></td>
					<td><a href="" onclick="getConstituencyElecResultsWindow(${candidate.constituencyId})">view results</a></td>
				</tr>  
			</c:forEach>
		</table>		
	</div>
</div>

<script language="javascript">
initializeResultsTable();
</script>


<div id="mandalInfoDiv" class="detailsDiv">
	<div id="mandalInfoDivHead" class="detailsHead">
		Mandal in <c:out value="${districtName}"/> District 
	</div>

	<div id="mandalInfoDivBody" class="detailsBody">
		<table><tr>
		<c:forEach var="mandalsBeforeDelimitationConstituency" varStatus="stat" items="${mandals}">				
			<td>
				<span id="mandalAncSpan">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					<a href="mandalPageElectionInfoAction.action?MANDAL_ID=${mandalsBeforeDelimitationConstituency.id}&MANDAL_NAME=${mandalsBeforeDelimitationConstituency.name}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mandalsBeforeDelimitationConstituency.name}
					</a>&nbsp 
				</span>
			</td>	
			<c:if test="${stat.count % 4==0}">
				</tr><tr><td colspan="4"> </td></tr><tr>
			</c:if>			
		</c:forEach>			
		</tr>  </table>		
	</div>
	
</div>


</div>
</body>
</html>