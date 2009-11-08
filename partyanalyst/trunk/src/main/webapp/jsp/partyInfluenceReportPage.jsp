<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Influence Report</title>

<!-- YUI Dependency Files-->
	
	<link href="styles/yuiStyles/treeview.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/calendar.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/datatable.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js" ></script>
	<script type="text/javascript" src="js/yahoo/animation-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/calendar-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/element-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/connection.js"></script> 
	<script type="text/javascript" src="js/yahoo/history.js"></script> 
	
	<!-- YUI Dependency Files-->

<style type="text/css">
		
	.searchresultsTable
	{
		width:auto;
	}
	.partyFullResultsTable
	{
		border-collapse:collapse;
	}
	#constFullDetails
	{
		position: absolute; background-color: #EFEFEF; top: 400px; left: 250px; width: auto; height: 100px;display:none;
		border:5px solid #AFAFAF;
		opacity:0.0;
	}
	#closeSpan
	{
		font-weight:bold;
		border:1px solid;
		cursor:pointer;
	}
	#constFullDetailsHead
	{
		font-weight:bold;
		background-color:#CACACA;
		text-align:right;
		padding:5px;
	}
	#constFullDetailsHeadDetails
	{
		float:left;
		text-decoration:underline;
	}
	#constFullDetailsBody
	{
		padding:5px;
	}

</style>
<script type="text/javascript">

	function buildJSObj(districtName,stateName,diff,obj)
	{
		

		var divElmt = document.getElementById("districtInfluenceDiv");

		var childDivElmt = document.createElement('div');
		childDivElmt.setAttribute('id',districtName+'districtInfluenceDiv');
		divElmt.appendChild(childDivElmt);

		var tree;	
		
		tree = new YAHOO.widget.TreeView(childDivElmt.id);				 
		var rootNode = tree.getRoot(); 				
		var label=districtName+"-"+diff;
		var stateNode = new YAHOO.widget.TextNode(label, rootNode);		
		tree.render();

		for (var i in obj)
		{
			new YAHOO.widget.TextNode("HI", node, false); 
		}
	}

	function showTableRow(val)
	{
		
		
		var ancElmt= document.getElementById(val+"_table");
		
		if ( ancElmt.style.display=='none' )
			ancElmt.style.display = "block";
		else
			ancElmt.style.display = "none";
		/*tr=document.getElementsByTagName('tr');

		for (i=0;i<tr.length;i++)
		{			
			if (tr[i].id && tr[i].id==val)
			{				
				if ( tr[i].style.display=='none' )
				{
					tr[i].style.display = '';
					ancElmt.innerHTML="close";
				}
				else
				{
					tr[i].style.display = 'none';
					ancElmt.innerHTML="view results";
				}
			}
		}	*/	
	}
	function showDetails(val)
	{
		
		var ancElmt= document.getElementById("constFullDetails");	
		ancElmt.style.display = "block";

		var ancElmtHead= document.getElementById("constFullDetailsBody");	
		ancElmtHead.innerHTML="";
		var str='';
		str+='<table border="1" class="partyFullResultsTable">';
		str+='<tr>';
		str+='<th>Name</th>';
		str+='<th>Votes Earned</th>';
		str+='<th>Name</th>';
		str+='<th>Votes Earned</th>';
		str+='<th>Name</th>';
		str+='<th>Votes Earned</th>';
		str+='</tr>';
		str+='<tr>';
		for (var i in val)
		{			
			str+='<td>'+val[i]+'</td>';			
		}
		str+='</tr>';
		str+='</table>';
		ancElmtHead.innerHTML=str;

		fadeIn("constFullDetails",10);
	}
	
	function fadeIn(objId, opacity)
	{
		var obj = document.getElementById(objId);
		if(obj && opacity <= 100){
			setOpacity(obj, opacity);
			opacity += 10;
			window.setTimeout("fadeIn('"+objId+"',"+opacity+")", 100);		
		}
	}
	function setOpacity(obj, opacity)
	{
		opacity = (opacity == 100)?99.999:opacity;
		obj.style.filter ="alpha(opacity:"+opacity+")";// IE/Win
		obj.style.KHTMLOpacity = opacity/100;// Safari<1.2, Konqueror
		obj.style.MozOpacity = opacity/100;// Older Mozilla and Firefoxv
		obj.style.opacity = opacity/100;// Safari 1.2, newer Firefox and Mozilla, CSS3
	}

	function hideDetails()
	{
		var ancElmt= document.getElementById("constFullDetails");
		ancElmt.style.display = "none";
	}
</script>
</head>
<body>
		<h3>Party Influence Report</h3>
		<div id="influenceReportMain" style="text-align:left;margin-left:50px;">
			<div id="constFullDetails">
				<div id="constFullDetailsHead">
					<span id="constFullDetailsHeadDetails">Complete Party  Results..</span>	
					<span id="closeSpan" onclick="hideDetails()">X</span>
				</div>
				<div id="constFullDetailsBody"></div>
			</div>
			<div id="influenceReportMain">
				<div><u>District wise result..</u></div>
				
				<c:forEach var="result" items="${districtWiseConstituencyElectionResultsVO}">
				<table style="margin-top:10px;">
					<tr>
						<td><c:out value="${result.districtName}"></c:out></td>
						<td><c:out value="${result.districtVotesPercentageDiff}"></c:out> %</td>
						<td><a href="javascript:{}" id="${result.districtName}_${result.districtId}_consTr" onclick="showTableRow(this.id)">view results</a></td>
					</tr>
				</table >
				<c:forEach var="constResult" items="${result.constituencyElectionsDetailedResults}">	
						<table id="${result.districtName}_${result.districtId}_consTr_table" style="display:none;margin-left:30px;margin-top:10px;" class="searchresultsTable">
							<tr>
								<th>Constituency Name</th>
								<td><c:out value="${constResult.constituencyName}"></c:out> </td>
							
							
								<th>Votes % Difference</th>
								<td><c:out value="${constResult.votesPercentageDiff}"></c:out> %</td>
							
								<td colspan="4" align="right"><a href="javascript:{}" id="${constResult.constituencyName}_${constResult.constituencyId}_candTr" onclick="showTableRow(this.id)">view results</a></td>
								
							</tr>
							
						</table >
						<table id="${constResult.constituencyName}_${constResult.constituencyId}_candTr_table" style="display:none;margin-left:30px;margin-top:10px;width:auto;margin-right:10px;" class="searchresultsTable">
							<tr>
								<th colspan="6" align="center">Year - <c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForParty.year}"></c:out></th>
								<th> </th>
								<th colspan="4" align="center">Year - <c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.year}"></c:out></th>
							</tr>

							<tr>
								<th>Candidate Name</th>
								<th>Party Name</th>								
								<th>% Votes</th>
								<th>Candidate Name</th>
								<th>Party Name</th>								
								<th>% Votes</th>
								<th> </th>
								<th>Candidate Name</th>
								<th>Party Name</th>								
								<th>% Votes</th>
								<c:if test="${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty !=null}" >
									<th>Candidate Name</th>
									<th>Party Name</th>								
									<th>% Votes</th>
								</c:if>
								<th> </th>
							</tr>
							
							<tr>												
								<td><c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.candidateName}"></c:out> </td>
								<td><c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.partyName}"></c:out> </td>					
								<td><c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.percentageOfVotes}"></c:out> %</td>

								<td><c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForParty.candidateName}"></c:out> </td>
								<td><c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForParty.partyName}"></c:out> </td>						
								<td><c:out value="${constResult.constituencyElectionResultsForYearOne.electionResultForParty.percentageOfVotes}"></c:out> %</td>
								
								<td> </td>
								<td><c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.candidateName}"></c:out> </td>
								<td><c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.partyName}"></c:out> </td>						
								<td><c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.percentageOfVotes}"> </c:out> %</td>	

								<c:if test="${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty !=null}" >
									<td><c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty.candidateName}"></c:out> </td>
									<td><c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty.partyName}"></c:out> </td>				
									<td><c:out value="${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty.percentageOfVotes}"></c:out></td>	
								</c:if>

								<script type="text/javascript">
									var dataArray=new Array();
									dataArray.push("${constResult.constituencyElectionResultsForYearOne.electionResultForParty.candidateName}");
									dataArray.push("${constResult.constituencyElectionResultsForYearOne.electionResultForParty.votesEarned}");
								
									
									dataArray.push("${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.candidateName}");
									dataArray.push("${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.votesEarned}");
								

									dataArray.push("${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.candidateName}");
									dataArray.push("${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.votesEarned}");
									
									<c:if test="${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty !=null}" >
										dataArray.push("${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty.candidateName}");
										dataArray.push("${constResult.constituencyElectionResultsForYearTwo.electionResultForNewParty.votesEarned}");
										
									</c:if>
									
								</script>
								<td><a href="javascript:{}" onclick="showDetails(dataArray)">Details..</a></td>
							</tr>
							</table>
						</c:forEach>
					
				</c:forEach>
				</table>
			
			</div>
			
			
			<!--
			<div id="districtInfluenceDiv">
			
			</div>
		
			<script type="text/javascript">
				<c:forEach var="result" items="${districtList}">
				var consObj;
					<c:forEach var="constituency" items="${result.constituencyElectionsDetailedResults}">
						consObj={
							constName:'${constituency.constituencyName}',
							votesDiff:'${constituency.votesPercentageDiff}',
							electionResultOne:{
												partyOne:{
															year:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.year}',
															candName:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.candidateName}',
															partyName:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.partyName}',
															votesEarned:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.votesEarned}',
															VotesPercentage:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.percentageOfVotes}',
															hasRebel:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.hasRebel}'
														 },
												partyTwo:{
															year:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.year}',
															candName:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.candidateName}',
															partyName:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.partyName}',
															votesEarned:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.votesEarned}',
															VotesPercentage:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.percentageOfVotes}',
															hasRebel:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.hasRebel}'
														 }
											   },
							electionResultTwo:{
												partyOne:{
															year:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.year}',
															candName:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.candidateName}',
															partyName:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.partyName}',
															votesEarned:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.votesEarned}',
															VotesPercentage:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.percentageOfVotes}',
															hasRebel:'${constituency.constituencyElectionResultsOne.electionResultForPartyOne.hasRebel}'
														}
											  }
						}

						//buildJSObj(consObj);
					</c:forEach>
					
					buildJSObj('${result.districtName}','${result.stateName}','${result.districtVotesPercentageDiff}',consObj);
				</c:forEach>
			</script>
			
			-->
		</div>
</body>
</html>