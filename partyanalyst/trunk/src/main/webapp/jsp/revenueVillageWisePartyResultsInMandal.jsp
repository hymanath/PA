<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${partyName } results In ${mandalName} Mandal By Revenue Villages</title>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
    <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
    <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
    <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
       
    <!-- YUI Skin Sam -->

    <link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
   
    <!-- YUI Dependency files (End) -->
    <style>
    .yui-skin-sam .yui-dt-liner {
		margin:0;
		padding:4px;
		}
	fieldset {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;		
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:13px;
		padding:5px;
	}
	.h3 {
		background-image: url("images/icons/electionResultsReport/heading.png");
		border-left: 1px solid #CCCCCC;
		border-right :1px solid #CCCCCC;
		color: #006221;
		font-family: MS Sans-serif;
		font-size: 17px;
		font-weight: bold;
		height: 25px;
		margin-bottom: 15px;
		margin-top: 0;
		padding:10px;
			
	}
	.yui-skin-sam .yui-dt table {
		border:1px solid #7F7F7F;
		border-collapse:separate;
		border-spacing:0;
		font-family:verdana;
		font-size:12px;
		margin:0;
		padding:0;
    }
    </style>
<script type="text/javascript">
	function createDataTable(divId, tableId){
			
		 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get(tableId)); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
		 myDataSource.responseSchema = { 
				 fields: [
							{
								key : "village"
							},{
								key : "votesEarned",parser:"number"
							},{
								key : "validVotes",parser:"number"
							},{
								key : "percentage",parser:"float"
							},{
								key : "constituency"
							}
						]  
		};

		 var myColumnDefs = [ 
									{
										key : "village",
										label : "Revenue Village",
										sortable : true
									}, 
									{
										key : "votesEarned",
										label : "Votes Earned",
										sortable : true
									},
									{
										key : "validVotes",
										label : "Valid Votes",
										sortable : true
									},
									{
										key : "percentage",
										label : "Percentage",
										sortable : true
									},
									{
										key : "constituency",
										label : "Constituency",
										sortable : true
									}
					        ]; 
		        
		var villageDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource);
	}
	window.history.forward(1);
</script>
</head>
<body>
	<center><div style="margin-top:10px;" class="h3">${partyName } Performance In Different Elections Of ${mandalName} Mandal By Revenue Villages</div></center>
	<c:choose>
      <c:when test="${empty constituencyRevenueVillagesVO.electionInfoByLocations}">
			<div style="margin-top:20px;"><b>Sorry, No Records Found</b></div>
      </c:when>		
      <c:otherwise>
      		<div style="margin-bottom:10px;"><img src="charts/${constituencyRevenueVillagesVO.chartPath }" /></div>
      		<div>
      		<table width="100%">
      		<tr>
      			<c:forEach var="election" varStatus="status" items="${constituencyRevenueVillagesVO.electionInfoByLocations}">
   				<td width="50%" valign="top">
   				<div class="yui-skin-sam">
	   				<fieldset>
	   					<legend>${election.electionYear} ${election.electionType}</legend>
	   					<div id="divId_${status.index}" >
		   					<table id="tableId_${status.index}">
								<c:forEach var="result" items="${election.partyResultsInElection}">
									<tr>
							      		<td><c:out value="${result.townshipName}"/></td>
								      	<td><c:out value="${result.votesEarned}"/></td>
										<td><c:out value="${result.validVotes}"/></td>	
										<td><c:out value="${result.votesPercentage}"/></td>	
										<td><c:out value="${result.constiName}"/></td>
									</tr>
								</c:forEach>
							</table>
						</div>
	   				</fieldset>
				</div>
				</td>
				<c:if test="${(status.index%2)==1}"></tr></c:if>				
			</c:forEach>
			</tr>
			</table>
			</div>
      </c:otherwise>
  	</c:choose>
  	<script type="text/javascript">
	  	<c:forEach var="election" varStatus="status" items="${constituencyRevenueVillagesVO.electionInfoByLocations}">
			createDataTable("divId_${status.index}","tableId_${status.index}");
		</c:forEach>
	</script>
</body>
</html>