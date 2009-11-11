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
table.CandidateElectionResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 2px;
	border-color: #666666;
	border-collapse: collapse;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #909090;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
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
	.districtDiv
	{
		text-align:left;
		margin-top:5px;
		margin-left:50px;
	}
	.treeTable th
	{
		color:#859098;
		background-color:#EFEFEF;
	}
	.treeTable td
	{
		color:#224662;
		width:100px;
		background-color:#EFEFEF;
	}
	.ygtvchildren
	{
		color:#224662;
	}

</style>
<script type="text/javascript">

	function buildJSObj(distObj,constObj)
	{
		

		var divElmt = document.getElementById("treeViewDiv");
		
		var childDivElmt = document.createElement('div');
		childDivElmt.setAttribute('id','districtInfluenceDiv_'+distObj.districtId);
		childDivElmt.setAttribute('class','districtDiv');
		divElmt.appendChild(childDivElmt);

		var tree;	
			
		tree = new YAHOO.widget.TreeView(childDivElmt.id);	
		
		var rootNode = tree.getRoot(); 	
		
		var distStr='';
		distStr+='<table class="treeTable">';
		distStr+='<tr>';		
		distStr+='<td>'+distObj.districtName+'</td>';		

		distStr+='<th>Impacted % :</th>';
		distStr+='<td align="center" style="color:#FF0000">'+distObj.distVotesPercentage+' %</td>';

		distStr+='<th>2009 % :</th>';
		distStr+='<td align="center">'+distObj.partyVotesPercentage+' %</td>';
		
		
		distStr+='<th> '+distObj.newPartyName+' - 2009 % :</th>';
		distStr+='<td align="center">'+distObj.newPartyVotesPercentage+' %</td>';

		
		distStr+='</tr>';
		distStr+='</table>';


		//var label=distObj.districtName;

		var stateNode = new YAHOO.widget.TextNode(distStr, rootNode);		

		for ( var cons in constObj ) 
		{ 			
	        var constNode = new YAHOO.widget.TextNode(constObj[cons].constName, stateNode, false);
			
			var str='';
			str+='<table class="partyPerformanceCriteriaTable" style="margin-right:10px;">';
			str+='<tr>';
			str+='<th colspan="6" align="center">Year '+distObj.year+'</th>';
			str+='<th colspan="3" align="center">Year '+distObj.previousYear+'</th>';
			str+='<th colspan="2" align="center">('+distObj.year+'-'+distObj.previousYear+')</th>';			
			str+='</tr>';

			str+='<tr>';
			str+='<th align="center">Party</th>';
			str+='<th colspan="2" align="center">Votes %</th>';
			str+='<th align="center">Party</th>';
			str+='<th colspan="2" align="center">Votes %</th>';
			str+='<th align="center">Party</th>';
			str+='<th colspan="2" align="center">Votes %</th>';
			str+='<th align="center">Party %</th>';
			str+='<th align="center">Votes Diff %</th>';			
			str+='</tr>';			
			
			str+='<tr>';					
			str+='<td>'+constObj[cons].electionResultOne.partyOne.partyName+'</td>';
			str+='<td colspan="2" align="center">'+constObj[cons].electionResultOne.partyOne.VotesPercentage+' %</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyTwo.partyName+'</td>';
			str+='<td colspan="2" align="center">'+constObj[cons].electionResultOne.partyTwo.VotesPercentage+' %</td>';
			str+='<td>'+constObj[cons].electionResultTwo.partyOne.partyName+'</td>';
			str+='<td colspan="2" align="center">'+constObj[cons].electionResultTwo.partyOne.VotesPercentage+' %</td>';
			str+='<td rowspan="3">'+constObj[cons].electionResultTwo.partyOne.partyName+'</td>';
			str+='<td rowspan="3">'+constObj[cons].votesDiff+' %</td>';			
			str+='</tr>';	

			str+='<tr>';
			str+='<th>Candidate Name</th>';
			str+='<th>Votes Earned</th>';
			str+='<th>Valid Votes</th>';
			str+='<th>Candidate Name</th>';
			str+='<th>Votes Earned</th>';
			str+='<th>Valid Votes</th>';
			str+='<th>Candidate Name</th>';
			str+='<th>Votes Earned</th>';
			str+='<th>Valid Votes</th>';
			str+='</tr>';
			str+='<tr>';
			str+='<td>'+constObj[cons].electionResultOne.partyOne.candidateName+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyOne.votesEarned+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyOne.validVotes+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyTwo.candidateName+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyTwo.votesEarned+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyTwo.validVotes+'</td>';
			str+='<td>'+constObj[cons].electionResultTwo.partyOne.candidateName+'</td>';
			str+='<td>'+constObj[cons].electionResultTwo.partyOne.votesEarned+'</td>';
			str+='<td>'+constObj[cons].electionResultTwo.partyOne.validVotes+'</td>';
			str+='</tr>';

			str+='</table>';
			
			var constDetailNode = new YAHOO.widget.HTMLNode(str, constNode, false); 
			constDetailNode.isLeaf = true;
		}
		 tree.draw(); 

		/*for (var i in obj)
		{
			new YAHOO.widget.TextNode("HI", node, false); 
		}
		*/
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
		<h3>PARTY INFLUENCE REPORT</h3>

			<div id="influenceReportMain">				
				<div id="reportMain">
				<div id="tableDiv" style="text-align:left;margin-left:85px;">
				  <table class="CandidateElectionResultsTable" width="600px" border="2">
				    <tr>
					   <th align="center" ><b><c:out value="New Party " /></b></th>
					   <th align="center" ><b><c:out value="${partyInfluenceReportVO.newPartyName}" /></b></th>
					   <th align="center" ><b><c:out value="Impacted Party " /></b></th>
					   <th align="center"><b><c:out value="${partyInfluenceReportVO.impactedPartyName}" /></b></th>
					   <th align="center" ><b><c:out value="Constituencies Considered " /></b></th>
					   <th align="center" > <b><c:out value="${partyInfluenceReportVO.totalConstituenciesConsidered}" /></b></th>
					
					   <th align="center" ><b><c:out value="Districts Considered " /></b></th>
					   <th align="center" > <b><c:out value="${partyInfluenceReportVO.totalDistrictsConsidered}" /></b></th>
					</tr>
					<tr>
					   <td align="center"  colspan="2"><b><c:out value="Total Percentage Of Votes For " /><c:out value="${partyInfluenceReportVO.impactedPartyName}" />
					   <c:out value=" In " /> <c:out value="${partyInfluenceReportVO.year}" /></b></td>
					   <td align="center" > <b><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentForPartyForYearOne}" /> %</b></td>
					
					   <td align="center" colspan="2"><b><c:out value="Total Percentage Of Votes For " /><c:out value="${partyInfluenceReportVO.impactedPartyName}" />
					   <c:out value=" In " /> <c:out value="${partyInfluenceReportVO.previousYear}" /></b></td>
					   <td align="center" > <b><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentForPartyForYearTwo}" /> %</b></td>
					   <td align="center" ><b><c:out value="Total Percentage Of Votes For " /><c:out value="${partyInfluenceReportVO.newPartyName}" />
					   <c:out value=" In " /> <c:out value="${partyInfluenceReportVO.year}" /></b></td>
					   <td align="center" > <b><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentForNewPartyForYearOne}" /> %</b></td>
					 </tr>
					<tr>
					   <th align="center" colspan="6"><b><c:out value="Total Percentage Difference Of Votes For " /><c:out value="${partyInfluenceReportVO.impactedPartyName}" />
					   <c:out value=" In " /><c:out value="${partyInfluenceReportVO.year}" /> <c:out value=" - " /><c:out value="${partyInfluenceReportVO.previousYear}" /></b></th>
					   <th align="center" colspan="2"> <b><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentDiffForParty}" /> %</b></th>
					</tr>
				</table>
				</div>	
				<br/><br/>	
				<div id="treeViewDiv">
					<h3 style="text-align: left; margin-left: 50px;"><u> District wise results..</u></h3>
				</div>
				</div>
				<script type="text/javascript">
				<c:forEach var="result" items="${partyInfluenceReportVO.districtWiseConstituencyElectionResultsVO}">
					var distObj={
									districtId:'${result.districtId}',
									districtName:'${result.districtName}',
									stateName:'${result.stateName}',
									distVotesPercentage:'${result.districtVotesPercentageDiff}',
									partyVotesPercentage:'${result.partyVotesPercentage}',
									newPartyVotesPercentage:'${result.newPartyVotesPercentage}',
									partyVotesPercentageYear2:'${result.partyVotesPercentForYear2}',
									year:'${result.year}',
									previousYear:'${result.previousYear}',
									partyName:'${result.partyName}',
									newPartyName:'${result.newPartyName}',									
								}
					var consObj=new Array();
					<c:forEach var="constResult" items="${result.constituencyElectionsDetailedResults}">	
						var consData={
							constId:'${constResult.constituencyId}',
							constDistId:'${constResult.districtId}',
							constName:'${constResult.constituencyName}',
							votesDiff:'${constResult.votesPercentageDiff}',
							electionResultOne:{
												partyOne:{
															year:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.year}',
															candidateId:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.candidateId}',
															constituencyId:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.constituencyId}',
															districtId:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.districtId}',
															districtName:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.districtName}',
															constituencyName:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.constituencyName}',
															candidateName:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.candidateName}',
															partyName:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.partyName}',
															votesEarned:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.votesEarned}',	
															validVotes:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.validVotes}',
															VotesPercentage:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.percentageOfVotes}',
															hasRebel:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.hasRebel}'				
														 },
												partyTwo:{
															year:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.year}',
															candidateId:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.candidateId}',
															constituencyId:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.constituencyId}',
															districtId:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.districtId}',
															districtName:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.districtName}',
															constituencyName:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.constituencyName}',
															candidateName:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.candidateName}',
															partyName:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.partyName}',
															votesEarned:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.votesEarned}',	
															validVotes:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.validVotes}',
															VotesPercentage:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.percentageOfVotes}',
															hasRebel:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.hasRebel}'	
														 }
											   },
							electionResultTwo:{
												partyOne:{
															year:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.year}',
															candidateId:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.candidateId}',
															constituencyId:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.constituencyId}',
															districtId:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.districtId}',
															districtName:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.districtName}',
															constituencyName:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.constituencyName}',
															candidateName:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.candidateName}',
															partyName:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.partyName}',
															votesEarned:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.votesEarned}',	
															validVotes:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.validVotes}',
															VotesPercentage:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.percentageOfVotes}',
															hasRebel:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.hasRebel}'		
														}
											  }
						}
						consObj.push(consData);
					</c:forEach>
					buildJSObj(distObj,consObj);
				</c:forEach>
				</script>		
</body>
</html>