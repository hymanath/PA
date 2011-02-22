<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/animation/animation-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/carousel/carousel-min.js&2.8.2r1/build/layout/layout-min.js"></script>  

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<title>Party Strengths</title>
<style type="text/css">
	table.CandidateElectionResultsTable{
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	
	#partyStrenghtsTable td
	{
	 	 padding-bottom: 13px;
	}
	
</style>

<script type="text/javascript">

	function initializeResultsTable() {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("dataSortingTable"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "INC"
			}, {   
				key : "TDP"
			}, {
				key : "TRS"
			}, {
				key : "CPI"
			}, {
				key : "CPM"
			}, {
				key : "AIMIM"
			}, {
				key : "BJP"
			}, {
				key : "PRP"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true
		}, {
			key : "INC",
			label : "INC",
			sortable : true
		}, {
			key : "TDP",
			label : "TDP",
			sortable : true	
		}, {
			key : "TRS",
			label : "TRS",
			sortable : true	
		}, {
			key : "CPI",
			label : "CPI"	
		}, {
			key : "CPM",
			label : "CPM"	
		}, {
			key : "AIMIM",
			label : "AIMIM"	
		}, {
			key : "BJP",
			label : "BJP"	
		}, {
			key : "PRP",
			label : "PRP"	
		}];
	
	
		var myDataTable = new YAHOO.widget.DataTable("dataTableDiv",resultsColumnDefs, resultsDataSource);  
	}
</script>


</head>
<body>
		<div id="main_div">
			<div id="header_div" style="margin-top: 24px;">
				<table>
					<tr>
						<th colspan="2">
							<span style="margin: 0px; text-align: center;">Party Strengths</span>
						</th>
					</tr>
				</table>
				<table class="partyStrengthsTable">
					<tr>
						<td colspan="2">
							<div style="color: red;font-weight:bold;" id="errorMessageDiv">
								<s:actionerror />
								<s:fielderror />
								<s:actionmessage/>						
							</div>
						</td>
					</tr>
				</table>
				<s:form name="partyStrengthResultsAction" action="partyStrengthResultsAction" method="POST">
					<table id="partyStrenghtsTable" width="400px;">						
						<tr>
							<th align="left">Election Type</td>
							<td align="left">
								<select id="electionTypeSelect" name="electionType" class = "selectWidth">
									<option value="0">Select Election Type</option>									
									<option value="Assembly">Assembly</option>
									<option value="Parliament">Parliament</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="left">Select State</th>
							<td align="left">
								<select id="electionTypeSelect" name="state"  class = "selectWidth">
									<option value="0">Select</option>
									<option value="1">Andhra Pradesh</option>									
								</select>
							</td>
						</tr>
						<tr>
							<th align="left">Select Years</th>
							<td align="left">
								<select id="electionYears" name="electionYears" class = "selectWidth">
									<option value="0">Select</option>
									<option value="7">7</option>	
									<option value="6">6</option>	
									<option value="5">5</option>
									<option value="4">4</option>
									<option value="3">3</option>
									<option value="2">2</option>
									<option value="1">1</option>									
								</select>								
							</td>
						</tr>
						<tr>
							<th align="left">Select Party</th>
							<td align="left">														
								<s:select theme="simple" name="party" id="partyList" cssClass = "selectWidth" list="partyList" headerKey="-1" headerValue="Select Party" listKey="id" listValue="name" />
							</td>
						</tr>
						<tr>
							<td> <input type="submit" value="Submit"></td>
						</tr>
					</table>
				</s:form>				
			</div>		
			<div id="data_body" class="yui-skin-sam">	
					<div id="dataTableDiv">
						<table  id="dataSortingTable" border="1">	
						<tr>
							<th> Constituency Name</th>					
							<th> INC </th>
							<th> TDP </th>
							<th> TRS </th>
							<th> CPI </th>
							<th> CPM </th>
							<th> AIMIM </th>
							<th> BJP </th>
							<th> PRP </th>
						</tr>					
								<c:forEach var="details" varStatus="stat" items="${electionInfo.requiredConstituenciesInfo.partiesStrengthsInfoVO}">	
									<tr>
										<td>
											${details.constituencyName}
										</td>
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">
											<td>
												<c:if test="${partyResults.partyName == 'INC'}">
													 ${partyResults.partyName} ${partyResults.count}
												</c:if>
											</td>
										</c:forEach>
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">											
											<td>
												<c:if test="${partyResults.partyName == 'TDP'}">
													${partyResults.partyName} ${partyResults.count}
												</c:if>	
											</td>
										</c:forEach>	
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">
											<td>	
												<c:if test="${partyResults.partyName == 'TRS'}">
													 ${partyResults.partyName} ${partyResults.count}
												</c:if>
											</td>	
										</c:forEach>
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">	
											<td>	
												<c:if test="${partyResults.partyName == 'CPI'}">
													 ${partyResults.partyName} ${partyResults.count}
												</c:if>
											</td>		
										</c:forEach>	
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">
											<td>
												<c:if test="${partyResults.partyName == 'CPM'}">
													 ${partyResults.partyName} ${partyResults.count}
												</c:if>	
											</td>
										</c:forEach>
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">	
											<td> 
												<c:if test="${partyResults.partyName == 'AIMIM'}">
													${partyResults.partyName} ${partyResults.count}
												</c:if>	
											</td>
										</c:forEach>	
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">
											<td>
												<c:if test="${partyResults.partyName == 'BJP'}">
													 ${partyResults.partyName} ${partyResults.count}
												</c:if>	
											</td>
										</c:forEach>	
										<c:forEach var="partyResults" varStatus="stat" items="${details.partyResults}">
											<td>
												<c:if test="${partyResults.partyName == 'PRP'}">
													 ${partyResults.partyName} ${partyResults.count}
												</c:if>	
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
						</table>	
					</div>	
			</div>	
		</div>
</body>
</html>