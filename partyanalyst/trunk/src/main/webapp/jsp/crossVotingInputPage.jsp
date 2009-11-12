<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cross Voting Report</title>

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

#crossVotingInputDiv
{
	text-align:left;
	margin-left:100px;
}
#labelspan
{
	color:#724400;
	text-decoration:underline;
}
.crossVotingInputTable td
{
	color:#724400;
	padding:3px;
}
#crossVotingResultDiv
{
	text-align: left; margin-top: 30px; margin-left: 100px;
}
#candidateDetailsDiv
{
	padding-left: 10px;
}
#assemblyCandidateDiv
{
	float:left;
	padding-right:20px;
	background-color:#EFEFEF;
	border:2px outset #CCCCCC;
}
#parliamentCandidateDiv
{
	background-color:#EFEFEF;
	margin-left:401px;
	margin-right:44px;
	border:2px outset #CCCCCC;
}
#treeDiv
{
	margin-top: 30px;
}
.detailsTable td
{
	padding:5px;
	color:#156FAB;
}
.detailsTable th
{
	padding:5px;
	color:#234E7A
}
.detailsTable a
{
	color:#156FAB;
	font-size:12px;
}

.mandalDatatable th
{
	color:#94A3AC;	
	padding-right:5px;
}
.mandalDatatable td
{
	color:#275D81;
	padding-right:5px;	
}
.mandalDiv
{
	padding:5px;
}

</style>
<script type="text/javascript">
	function getParliament()
	{
		var elecYearElmt = document.getElementById("electionYearField");
		var partyElmt = document.getElementById("partyListField");

		if(!elecYearElmt || !partyElmt)
			return;

		var elecValue =  elecYearElmt.options[elecYearElmt.selectedIndex].value;
		var partyValue =  partyElmt.options[partyElmt.selectedIndex].value;
					
		if(elecValue == -1 || partyValue == -1)
			return;
		else
		{
			var jsObj={
						election:elecValue,
						party:partyValue,
						task:"Parliament"
					  }

			var bparam="election="+jsObj.election+"&party="+jsObj.party;
			callAjax(jsObj,bparam);
		}
	}

	function getAssembly()
	{
		var parliamentSelectElmt =  document.getElementById("ParliamentSelect");
		var parliamentValue =  parliamentSelectElmt.options[parliamentSelectElmt.selectedIndex].value;
		var jsObj={
						parliamentValue : parliamentValue,
						task:"Assembly"
				  }
			
			var bparam="parliamentValue="+jsObj.parliamentValue;
			callAjax(jsObj,bparam);
	}
	
	function getCrossVoting()
	{
		var elecYearElmt = document.getElementById("electionYearField");
		var partyElmt = document.getElementById("partyListField");
		var parliamentSelectElmt =  document.getElementById("ParliamentSelect");
		var assemblySelectElmt =  document.getElementById("AssemblySelect");

		var elecValue =  elecYearElmt.options[elecYearElmt.selectedIndex].value;
		var partyValue =  partyElmt.options[partyElmt.selectedIndex].value;
		var parliamentValue =  parliamentSelectElmt.options[parliamentSelectElmt.selectedIndex].value;
		var assemblyValue =  assemblySelectElmt.options[assemblySelectElmt.selectedIndex].value;

		var jsObj={
						electionValue : elecValue,
						partyValue : partyValue,
						parliamentValue : parliamentValue,
						assemblyValue : assemblyValue,
						task:"crossVotingReport"
				  }
			
			var bparam="election="+jsObj.electionValue+"&party="+jsObj.partyValue+"&parliamentValue="+jsObj.parliamentValue+"&assemblyValue="+jsObj.assemblyValue;
			callAjax(jsObj,bparam);
	}
	function callAjax(jObj,param)
	{
		var myResults;		
		var boothUrl = "<%=request.getContextPath()%>/crossVotingReportAjaxAction.action?"+param; 
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText); 
								
								if(jObj.task == "Parliament" || jObj.task=="Assembly")
									buildParliamemtSelect(jObj,myResults.dataList);
								else if(jObj.task == "crossVotingReport")
									buildCrossVotingReport(jObj,myResults.crossVotingConsolidateVO);
												
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', boothUrl , callback);
	}

	function buildCrossVotingReport(obj,result)
	{
		

		var resultDiv = document.getElementById("crossVotingResultDiv");
		resultDiv.style.display="block";

		var str='';
		str+='<div id="headingDiv"><h3><u>Cross Voting Details..</u></h3></div>';
		str+='<div id="candidateDetailsDiv">';
		str+='<div id="assemblyCandidateDiv">';
		str+='<table id="assemblyTable" class="detailsTable" style="width:auto;">';
		str+='<tr><th colspan="2"><u>Assembly Candidate Details..</u></th>';		
		str+='</tr>';
		str+='<tr>';
		str+='<th>Name</th>';
		str+='<td><a href="candidateElectionResultsAction.action?candidateId='+result.acCandidateData.candidateId+'">'+result.acCandidateData.candidateName+'</a></td>';
		str+='<td rowspan="3"><img  height="90" width="90" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" ></td>';
		str+='</tr>';
		str+='<tr><th>Rank</th><td>'+result.acCandidateData.rank+'</td></tr>';
		str+='<tr><th>Votes %</th><td>'+result.acCandidateData.votesPercentage+'</td></tr>';
		str+='</table>';		
		str+='</div>';
		str+='<div id="parliamentCandidateDiv">';
		str+='<table id="parliamentTable" class="detailsTable" style="width:auto;">';
		str+='<tr><th colspan="2"><u>Parliament Candidate Details..</u></th></tr>';
		str+='<tr><th>Name</th>';
		str+='<td> <a href="candidateElectionResultsAction.action?candidateId='+result.pcCandidateData.candidateId+'">'+result.pcCandidateData.candidateName+'</a></td>';
		str+='<td rowspan="4"><img  height="90" width="90" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" ></td>';
		str+='</tr>';
		str+='<tr><th>Rank</th><td>'+result.pcCandidateData.rank+'</td></tr>';
		str+='<tr><th>Votes %</th><td>'+result.pcCandidateData.votesPercentage+'</td></tr>';
		str+='</table>';	
		str+='</div>';
		str+='<div id="treeDiv"><span><h4><u>Mandals Details..</u></h4></span><div id="treeDataDiv"></div></div>';
		str+='</div>';


		resultDiv.innerHTML=str;
		
		for(var i in result.mandals)
		{
			buildTreeView(result.mandals[i]);
		}
	}

	function buildTreeView(mandal)
	{
		
		var divElmt = document.getElementById("treeDataDiv");
				
		var childDivElmt = document.createElement('div');
		childDivElmt.setAttribute('id','mandalDiv_'+mandal.mandalName);
		childDivElmt.setAttribute('class','mandalDiv');
		divElmt.appendChild(childDivElmt);

		var tree;	
		tree = new YAHOO.widget.TreeView(childDivElmt.id);			
		var rootNode = tree.getRoot(); 	

		var mstr='';
		mstr+='<table class="mandalDatatable">';
		mstr+='<tr>';
		mstr+='<th width="100px;">'+mandal.mandalName+'</th>';		
		mstr+='<th>Assembly :</th>';
		mstr+='<td>'+mandal.acPercentageInMandal+'  %</td>';
		mstr+='<th>Parliament :</th>';
		mstr+='<td>'+mandal.pcPercentageInMandal+'  %</td>';
		mstr+='<th>%age of votes flown from assembly to parliament:</th>';
		mstr+='<td>'+mandal.percentageDifferenceInMandal+' %</td>';					
		mstr+='</tr>';
		mstr+='</table>'

		var label = mandal.mandalName;		

		var mandalNode = new YAHOO.widget.TextNode(mstr, rootNode);		
		
		var str='';
			str+='<table class="searchresultsTable" style="width:auto;">';
			str+='<tr>';
			str+='<th>Part No</th>';
			str+='<th>Villages Covered</th>';
			str+='<th>Percentage (P)</th>';
			str+='<th>Valid Votes (P)</th>';
			str+='<th>Percentage (A)</th>';
			str+='<th>Valid Votes (A)</th>';			
			str+='<th>% Diff</th>';			
			str+='</tr>';
			for (var j in mandal.crossVotedBooths) 
			{			
				str+='<tr>';			
				str+='<td>'+mandal.crossVotedBooths[j].partNO+'</td>';
				str+='<td>'+mandal.crossVotedBooths[j].villagesCovered+'</td>';
				str+='<td>'+mandal.crossVotedBooths[j].pcPercentage+'</td>';
				str+='<td>'+mandal.crossVotedBooths[j].pcValidVotes+'</td>';
				str+='<td>'+mandal.crossVotedBooths[j].acPercentage+'</td>';
				str+='<td>'+mandal.crossVotedBooths[j].acValidVotes+'</td>';
				str+='<td>'+mandal.crossVotedBooths[j].percentageDifference+'</td>';
				str+='</tr>';			
			}
			str+='</table>';
	        var constNode = new YAHOO.widget.TextNode(str, mandalNode, false);
		
		tree.draw(); 
		
	}
	function buildParliamemtSelect(jObj,results)
	{

		
		if(jObj.task=="Parliament")
		{			
			
			var pSelectElmt = document.getElementById("ParliamentSelect");	
		}
		else if(jObj.task=="Assembly")
		{			
			var pSelectElmt = document.getElementById("AssemblySelect");	
		}
		else
			return;
		
		for(var i in results)
		{
			var childElmt = document.createElement('option');
			childElmt.value = results[i].id;
			childElmt.text = results[i].name;
		
			try
			{
				pSelectElmt.add(childElmt,null);	
			}
			catch (e)
			{
				pSelectElmt.add(childElmt);
			}				
		}		
	}

</script>
</head>
<body>
		<h3><u>Cross Voting Report</u></h3>
		<div id="crossVotingInputDiv">
			<table class="crossVotingInputTable">
				<tr>
					<td colspan="2"><h4><span id="labelspan">Select Election year and party :</span></h4></td>
				</tr>
				<tr>
					<td align="left"><s:label theme="simple" for="electionYearField" id="electionYearLabel" value="Election Year"></s:label></td>
					<td align="left">
						<s:select theme="simple" id="electionYearField" name="electionYearField" list="electionYearList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Year"></s:select>
					</td>
				</tr>
				<tr>
					<td align="left"><s:label theme="simple" for="partyListField" id="partyListLabel" value="Party"></s:label></td>
					<td align="left">
						<s:select theme="simple" id="partyListField" name="partyListField" list="partyList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Year" onchange="getParliament()"></s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Parliament Constituency</td>
					<td align="left">
							<select id="ParliamentSelect" onchange="getAssembly()">
								<option value="-1">Select </option>
								
							</select>						
					</td>
				</tr>
				<tr>
					<td align="left">Assembly Constituency</td>
					<td align="left">
						<select id="AssemblySelect" onchange="getCrossVoting()">
							<option value="-1">Select</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div id="crossVotingResultDiv" style="display:none;"></div>
</body>
</html>