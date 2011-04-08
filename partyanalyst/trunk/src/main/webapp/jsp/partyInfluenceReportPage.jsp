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

	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<style type="text/css">
table.CandidateElectionResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 2px;
	border-color: #666666;
	border-collapse: collapse;
	margin:center;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #fhfhgh;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}


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
		background-color:#f1f2f3;
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
		text-align:left;
	}

	#treeViewDiv
	{
		margin-left:30px;
	}
	.partyDetSpan
	{
		text-decoration:underline;
		color:#344C7C;
	}

	.partyDetailsBody
	{
		border-bottom:1px solid #E0E0D6;
		border-left:1px solid #E0E0D6;
		border-right:1px solid #E0E0D6;
		height:150px;
		overflow:auto;
		padding:10px;
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

		distStr+='<th>CC * %diff :</th>';
		if(distObj.distVotesPercentage < 0)
		distStr+='<td align="center" style="color:#FF0000">'+distObj.distVotesPercentage+' %</td>';
		else if(distObj.distVotesPercentage > 0)
		distStr+='<td align="center" style="color:#04B45F">'+'+ '+distObj.distVotesPercentage+' %</td>';

        distStr+='<th>CD * %diff :</th>';
		if(distObj.distTotalPercentDiff < 0)
        distStr+='<td align="center" style="color:#FF0000">'+distObj.distTotalPercentDiff+' %</td>';
		else if(distObj.distTotalPercentDiff > 0)
		distStr+='<td align="center" style="color:#04B45F">'+'+ '+distObj.distTotalPercentDiff+' %</td>';
		distStr+='<th> '+distObj.partyName+'-'+distObj.year+'%:</th>';
		distStr+='<td align="center">'+distObj.partyVotesPercentage+' %</td>';	
		

		distStr+='<th> '+distObj.newPartyName+'-'+distObj.year+'%:</th>';
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
			str+='<th colspan="6" align="center">Year '+distObj.previousYear+'</th>';
			str+='<th colspan="2" align="center">('+distObj.year+'-'+distObj.previousYear+')</th>';			
			str+='</tr>';

			str+='<tr>';
			str+='<th align="center">Party</th>';
			str+='<th align="center">Candidate Name</th>';
			str+='<th align="center">Votes Earned</th>';
			str+='<th colspan="2" align="center">Votes&nbsp;%</th>';
			str+='<th align="center">Status</th>';
			str+='<th align="center" align="center">Party</th>';
			str+='<th align="center">Candidate Name</th>';
			str+='<th align="center">Votes Earned</th>';
			str+='<th colspan="2" align="center">Votes&nbsp;%</th>';
			str+='<th align="center">Status</th>';
			str+='<th colspan="2" align="center">Votes Diff&nbsp;%</th>';			
			str+='</tr>';			
			
			str+='<tr>';					
			str+='<td align="center">'+constObj[cons].electionResultOne.partyOne.partyName+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyOne.candidateName+'</td>';
			str+='<td align="center">'+constObj[cons].electionResultOne.partyOne.votesEarned+'</td>';
			str+='<td colspan="2">'+constObj[cons].electionResultOne.partyOne.VotesPercentage+'&nbsp;%</td>';
			if(constObj[cons].electionResultOne.partyOne.rank == 1)
			str+='<td align="center">Won</td>';
			else if(constObj[cons].electionResultOne.partyOne.rank == 2)
            str+='<td align="center">Runner-up</td>';
			else
			str+='<td align="center">Lost</td>';
			str+='<td rowspan="2">'+constObj[cons].electionResultTwo.partyOne.partyName+'</td>';
            str+='<td rowspan="2">'+constObj[cons].electionResultTwo.partyOne.candidateName+'</td>';
			str+='<td rowspan="2" align="center">'+constObj[cons].electionResultTwo.partyOne.votesEarned+'</td>';
			str+='<td rowspan="2" colspan="2">'+constObj[cons].electionResultTwo.partyOne.VotesPercentage+'&nbsp;%</td>';
			if(constObj[cons].electionResultTwo.partyOne.rank == 1)
			str+='<td rowspan="2" align="center">Won</td>';
			else if(constObj[cons].electionResultTwo.partyOne.rank == 2)
			str+='<td rowspan="2" align="center">Runner-up</td>';
			else
			str+='<td rowspan="2" align="center">Lost</td>';
			if(constObj[cons].votesDiff < 0)
			str+='<td rowspan="2" colspan="2" style="color:#FF0000" align="center">'+constObj[cons].votesDiff+'&nbsp;%</td>';	
			else if(constObj[cons].votesDiff > 0)
            str+='<td rowspan="2" colspan="2" style="color:#04B45F" align="center">'+'+ '+constObj[cons].votesDiff+'&nbsp;%</td>';	
			str+='</tr>';	

			str+='<tr>';
			str+='<td align="center">'+constObj[cons].electionResultOne.partyTwo.partyName+'</td>';
			str+='<td>'+constObj[cons].electionResultOne.partyTwo.candidateName+'</td>';
            str+='<td align="center">'+constObj[cons].electionResultOne.partyTwo.votesEarned+'</td>';
			str+='<td colspan="2">'+constObj[cons].electionResultOne.partyTwo.VotesPercentage+'&nbsp;%</td>';
			if(constObj[cons].electionResultOne.partyTwo.rank == 1)
			str+='<td align="center">Won</td>';
			else if(constObj[cons].electionResultOne.partyTwo.rank == 2)
			str+='<td align="center">Runner-up</td>';
			else
			str+='<td align="center">Lost</td>';
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
	window.history.forward(1);
</script>
</head>
<body>
		<table><tr><td><h3> PARTY INFLUENCE REPORT FOR </h3></td><td align="center" style="color:#FF0000"><h3><c:out value="${partyInfluenceReportVO.impactedPartyName}" /></h3></td><td><h3> AGAINST </h3></td><td align="center" style="color:#04B45F"><h3><c:out value="${partyInfluenceReportVO.newPartyName}"/></h3></td></tr></table>
            <br>
			<div id="influenceReportMain">				
				<div id="reportMain">
				<div id="tableDiv" style="text-align:left;margin-left:80px;margin-right:20px;">
					<table width="100%">						
						<tr>
							<td valign="top">
								<div id="newPartyDiv_main">
									<div id="newPartyDiv_head">										
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td><div class="districtPageRoundedHeaders_center"><span>${partyInfluenceReportVO.newPartyName} State Wise Results for ${partyInfluenceReportVO.year}</span></div></td>
												<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
											</tr>
										</table>											
									</div>
									<div id="newPartyDiv_body" class="partyDetailsBody">
										<table class="CandidateElectionResultsTable" width="100%">
											<tr>
												<th><c:out value="New Party " /></th>
												<td align="center"><c:out value="${partyInfluenceReportVO.newPartyName}" /></td>
												
											</tr>
											<tr>
												<th><c:out value="Votes % in " /><c:out value="${partyInfluenceReportVO.year}" /></th>
												<td align="center"><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentForNewPartyForYearOne}" /> %</td>
												
											</tr>
											<tr>
												<th><c:out value="Constituencies Considered " /></th>
												<td align="center"><c:out value="${partyInfluenceReportVO.totalConstituenciesConsidered}" /></td>
												
											</tr>											
										</table>	
									</div>
								</div>
								
							</td>
							<td style="padding-left:20px;" valign="top">								
								<div id="impactedPartyDiv_main">
									<div id="impactedPartyDiv_head">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td><div class="districtPageRoundedHeaders_center"><span>${partyInfluenceReportVO.impactedPartyName} State Wise Results for ${partyInfluenceReportVO.year} and ${partyInfluenceReportVO.previousYear}</span></div></td>
												<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
											</tr>
										</table>											
									</div>
									<div id="impactedPartyDiv_body" class="partyDetailsBody">
										<table class="CandidateElectionResultsTable" style="margin:left:10px;" width="100%">
											<tr>
												
												<th><c:out value="Impacted Party " /></th>
												<td align="center"><c:out value="${partyInfluenceReportVO.impactedPartyName}" /></td>
											</tr>
											<tr>
												
												<th><c:out value=" Votes % in "/> <c:out value="${partyInfluenceReportVO.year}" /></th>
												<td ><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentForPartyForYearOne}" /> %</td>
											</tr>
											<tr>
												
												<th><c:out value="Votes % in " /><c:out value="${partyInfluenceReportVO.previousYear}" /></th>
												<td><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentForPartyForYearTwo}" /> %</td>
											</tr>
											<tr>
												<th align="center"><c:out value="Votes % Diff ( " />
										   <c:out value="${partyInfluenceReportVO.year}" /> <c:out value=" - " /><c:out value="${partyInfluenceReportVO.previousYear}" />  <c:out value=") " /></th>
											<td style="color:red;"><c:out value="${partyInfluenceReportVO.totalDistrictsVotesPercentDiffForParty}" /> %</td>
												
											</tr>
										</table>
									</div>
								</div>								
							</td>
						</tr>
					</table>

					
				 
					
				
				</div>	
				<br/>
				<div id="treeViewDiv">
					<h3 style="text-align: left; margin-left: 50px;"><u> District wise results..</u><h4 style="text-align: right;margin-right: 20px; color: #BDBDBD;">  CC * -- Only Considered Constituencies | CD * -- Complete District Constituencies</h4></h3>
					
				</div>
		</div>
				<script type="text/javascript">
				<c:forEach var="result" items="${partyInfluenceReportVO.districtWiseConstituencyElectionResultsVO}">
					var distObj={
									districtId:'${result.districtId}',
									districtName:'${result.districtName}',
									stateName:'${result.stateName}',
									distVotesPercentage:'${result.districtWiseVotesPercntDiff}',
									partyVotesPercentage:'${result.partyVotesPercentage}',
									newPartyVotesPercentage:'${result.newPartyVotesPercentage}',
									partyVotesPercentageYear2:'${result.partyVotesPercentForYear2}',
									year:'${result.year}',
									previousYear:'${result.previousYear}',
									partyName:'${result.partyName}',
									newPartyName:'${result.newPartyName}',
									distTotalPercentDiff:'${result.totalVotesPercentageDiffForDistrict}'
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
															hasRebel:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.hasRebel}'	,
															rank:'${constResult.constituencyElectionResultsForYearOne.electionResultForParty.rank}'
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
															hasRebel:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.hasRebel}',
															rank:'${constResult.constituencyElectionResultsForYearOne.electionResultForNewParty.rank}'
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
															hasRebel:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.hasRebel}',
															rank:'${constResult.constituencyElectionResultsForYearTwo.electionResultForParty.rank}'
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