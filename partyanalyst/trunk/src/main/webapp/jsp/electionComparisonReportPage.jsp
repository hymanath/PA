<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionsComparisonVO " %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionComparisonResultVO" %>
<%@page import="com.itgrids.partyanalyst.dto.PartyElectionResultsVO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Comparison Report</title>

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

	<!-- Combo-handled YUI CSS files: -->
	<link rel="stylesheet" type="text/css"
		href="http://yui.yahooapis.com/combo?2.8.0r4/build/datatable/assets/skins/sam/datatable.css">
	<!-- Combo-handled YUI JS files: -->
	<script type="text/javascript"
		src="http://yui.yahooapis.com/combo?2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js&2.8.0r4/build/element/element-min.js&2.8.0r4/build/datasource/datasource-min.js&2.8.0r4/build/datatable/datatable-min.js"></script>


	<!-- Combo-handled YUI CSS files: -->

	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2009.11.09-19/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">	

	<script type="text/javascript" src="http://yui.yahooapis.com/3.0.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/gallery-2009.11.09-19/build/gallery-accordion/gallery-accordion-min.js"></script>


	<!-- Sam Skin CSS --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css"> 
	 
	<!-- Dependencies --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	 
	<!-- OPTIONAL: Animation (only required if using ContainerEffect) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/animation/animation-min.js"></script> 
	 
	<!-- OPTIONAL: Drag & Drop (only required if enabling Drag & Drop) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script> 
	 
	<!-- Source file --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/container/container-min.js"></script> 


<style type="text/css">

.yui-widget-bd
{
	border:1px solid #93B2CC;
}
table.CandidateElectionResultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.CandidateElectionResultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #C0D9E5;
}
table.CandidateElectionResultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

.headingStyle
{
	color:#1C4472;
	font-weight:bold;
	padding:10px 10px 10px 0;
	text-decoration:underline;
}
.partyElectionTable th
{
	color:#1C4472;
	padding:5px;
}
.partyElectionTable td
{
	padding:5px;
}
districtVotesTable td
{
	padding-bottom:10px;
}


.districtAnc
{
	text-decoration:none;
	color:#1C4472;
	padding-right:60px;
}
.districtAnc hover
{
	text-decoration:underline;
}
#electionResultsMain,#districtWiseResultsMain
{
	margin-left:55px;
	text-align:left;
}
#electionResultsMainBody
{
	padding-left:10px;
}
.yui-skin-sam .yui-accordion
{
	border:1px solid #93B2CC;
}
.yui-accordion-item
{
	width:90%;
}
#closeSpan
{
	float:right;
	cursor:pointer;
	font-weight:bold;
	margin-right:10px;
	border:1px solid;
}
#closeLabelSpan
{
	float:right;padding-right:5px;
	cursor:pointer;
}
#labelHead
{
	font-weight:bold;
	font-size:14px;		
	color:#394351;
}
#electionResultsPopupDiv
{
	border: 2px solid #A0B7C3;
	margin:30px 10px 10px 20px;
	text-align:left;
}
#electionResultsPopupDivHead
{
	padding:10px;
	background-color:#C0D9E5;
	text-decoration:underline;
}
#electionResultsPopupDivBody
{
	background-color:#ECEFF0;
}

.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	background-color:#C0D9E5;
	color:#3F546F;
}
.yui-skin-sam .yui-dt-liner
{
	padding:4px 8px;
	font-size:12px;
}	
.yui-skin-sam tr.yui-dt-odd
{
	background-color:#F6FDFF;
}
.yui-skin-sam tr.yui-dt-odd td.yui-dt-asc, .yui-skin-sam tr.yui-dt-odd td.yui-dt-desc 
{
	background-color:#EBF9FF;
}
.yui-skin-sam tr.yui-dt-even td.yui-dt-asc, .yui-skin-sam tr.yui-dt-even td.yui-dt-desc 
{
	background-color:#F5FAFD;
}


</style>
<script type="text/javascript">



var electionObject=	{
						constsGained:[],
						constsLost:[],
                        constsNotConsideredYearOne:[],
						constsNotConsideredYearTwo:[]
					};
	
   function setContentToPanel(type,val)
   {
		var elmt = document.getElementById("electionResultsPopupDiv");
		var elmtHead = document.getElementById("electionResultsPopupDivHead");
		var elmtBody = document.getElementById("electionResultsPopupDivBody");
		
		if(elmt)
			elmt.style.display="block";

		if(type == "constsGained")
			var localArr=electionObject.constsGained[val].electionResults;
		else if(type == "constsLost")
			var localArr=electionObject.constsLost[val].electionResults;
		else if(type == "constsNotConsideredYearOne")
			var localArr=electionObject.constsNotConsideredYearOne[val].electionResults;
        else if(type == "constsNotConsideredYearTwo")
			var localArr=electionObject.constsNotConsideredYearTwo[val].electionResults;
		
		var str='';
		str+='<table class="CandidateElectionResultsTable" id="electionComparisonTable">';
		for(var i in localArr)
		{
			str+='<tr>';
			str+='<td>'+localArr[i].candidateName+'</td>';
			str+='<td>'+localArr[i].constituencyName+'</td>';
			str+='<td>'+localArr[i].votesEarned+'</td>';
			str+='<td>'+localArr[i].votesPercentage+'</td>';

			if(localArr[i].rank == "1")
				str+='<td>Won</td>';
			else
				str+='<td>Lost</td>';

			if(localArr[i].votesDiff != "")
				str+='<td>'+localArr[i].votesDiff+'</td>';			
			else
				str+='<td> 0 </td>';
			
			str+='<td>'+localArr[i].electorsDiff+'</td>';

			if(localArr[i].secondCandidateName != "")
				str+='<td>'+localArr[i].secondCandidateName+'</td>';
			else
				str+='<td> -- </td>';

			if(localArr[i].votesEarnedBySecond != "")
				str+='<td>'+localArr[i].votesEarnedBySecond+'</td>';
			else
				str+='<td> 0 </td>';

			if(localArr[i].votesPercentageBySecond != "")
				str+='<td>'+localArr[i].votesPercentageBySecond+'</td>';
			else
				str+='<td> 0 </td>';

			if(localArr[i].rankBySecond != "")
			{
				if(localArr[i].rankBySecond == "1")
					str+='<td>Won</td>';
				else
					str+='<td>Lost</td>';			
			}
			else
				str+='<td> -- </td>';			
			str+='</tr>';
		}
		str+='</table>';

		elmtBody.innerHTML=str;
		
		buildelectionDataTable();

   }	

   function buildelectionDataTable()
   {

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("electionComparisonTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "candidateName"
		}, {
			key : "constituencyName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "votesPercentage"
		}, {
			key : "rank"
		}, {
			key : "votesDiff",parser:"number"
		} ,	{
			key : "electorsDiff",parser:"number"
		} ,{
			key : "secondCandidateName"
		} , {
			key : "votesEarnedBySecond",parser:"number"
		} , {
			key : "votesPercentageBySecond",parser:"number"
		} , {
			key : "rankBySecond"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "candidateName",		
		label : "Candidate",
		sortable : true
	}, {
		key : "constituencyName",
		label : "Constituency",
		sortable : true
	}, {
		key : "votesEarned",
		label : "Votes Earned",
		sortable : true
	}, {
		key : "votesPercentage",
		label : "Votes&nbsp;%",
		sortable : true
	}, {
		key : "rank",
		label : "Status",
		sortable : true
	}, {
		key : "votesDiff",
		label : "Votes Diff",
		sortable : true
	}, {
		key : "electorsDiff",
		label : "Electors&nbsp;%",
		sortable : true
	}, {
		key : "secondCandidateName",
		label : "Candidate",
		sortable : true
	}, {
		key : "votesEarnedBySecond",
		label : "Votes Earned",
		sortable : true
	}, {
		key : "votesPercentageBySecond",
		label : "Votes&nbsp;%",
		sortable : true
	}, {
		key : "rankBySecond",
		label : "Status",
		sortable : true
	} ];

	var myDataTable = new YAHOO.widget.DataTable("electionResultsPopupDivBody",resultsColumnDefs, resultsDataSource,{});  

}
	
	function buildJSObj()
	{
		<c:forEach var="comparison" items="${electionsComparisonVO.votesGained}">
			var distObj={
							districtId:'${comparison.districtId}',
							stateId:'${comparison.stateId}',
							districtName:'${comparison.districtName}',
							constiCount:'${comparison.constituenciesCount}',
							electionResults:[]
						}
				
			<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
				var electionObj={
									partyId:'${partyResults.partyId}',
									partyName:'${partyResults.partyName}',
									candidateId:'${partyResults.candidateId}',
									candidateName:'${partyResults.candidateName}',
									constituencyId:'${partyResults.constituencyId}',
									constituencyName:'${partyResults.constituencyName}',
									votesEarned:'${partyResults.votesEarned}',
									rank:'${partyResults.rank}',
									votesPercentage:'${partyResults.votesPercentage}',
									secondCandidateName:'${partyResults.secondCandidateName}',
									votesEarnedBySecond:'${partyResults.votesEarnedBySecond}',
									votesPercentageBySecond:'${partyResults.votesPercentageBySecond}',
									rankBySecond:'${partyResults.rankBySecond}',
									votesDiff:'${partyResults.votesDiff}',
									electorsDiff:'${partyResults.electorsPercentageDiff}'
								}
					distObj.electionResults.push(electionObj);
			</c:forEach>
			this.electionObject.constsGained.push(distObj);
		</c:forEach>

		<c:forEach var="comparison" items="${electionsComparisonVO.votesLost}">
			var distObj={
							districtId:'${comparison.districtId}',
							stateId:'${comparison.stateId}',
							districtName:'${comparison.districtName}',
							constiCount:'${comparison.constituenciesCount}',
							electionResults:[]
						}
				
			<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
				var electionObj={
									partyId:'${partyResults.partyId}',
									partyName:'${partyResults.partyName}',
									candidateId:'${partyResults.candidateId}',
									candidateName:'${partyResults.candidateName}',
									constituencyId:'${partyResults.constituencyId}',
									constituencyName:'${partyResults.constituencyName}',
									votesEarned:'${partyResults.votesEarned}',
									rank:'${partyResults.rank}',
									votesPercentage:'${partyResults.votesPercentage}',
									secondCandidateName:'${partyResults.secondCandidateName}',
									votesEarnedBySecond:'${partyResults.votesEarnedBySecond}',
									votesPercentageBySecond:'${partyResults.votesPercentageBySecond}',
									rankBySecond:'${partyResults.rankBySecond}',
									votesDiff:'${partyResults.votesDiff}',
								    electorsDiff:'${partyResults.electorsPercentageDiff}'
								}
					distObj.electionResults.push(electionObj);
			</c:forEach>
			this.electionObject.constsLost.push(distObj);
		</c:forEach>

		<c:forEach var="comparison" items="${electionsComparisonVO.constituenciesNotConsideredForYearOne}">
			var distObj={
							districtId:'${comparison.districtId}',
							stateId:'${comparison.stateId}',
							districtName:'${comparison.districtName}',
							constiCount:'${comparison.constituenciesCount}',
							electionResults:[]
						}
				
			<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
				var electionObj={
									partyId:'${partyResults.partyId}',
									partyName:'${partyResults.partyName}',
									candidateId:'${partyResults.candidateId}',
									candidateName:'${partyResults.candidateName}',
									constituencyId:'${partyResults.constituencyId}',
									constituencyName:'${partyResults.constituencyName}',
									votesEarned:'${partyResults.votesEarned}',
									rank:'${partyResults.rank}',
									votesPercentage:'${partyResults.votesPercentage}',
									secondCandidateName:'${partyResults.secondCandidateName}',
									votesEarnedBySecond:'${partyResults.votesEarnedBySecond}',
									votesPercentageBySecond:'${partyResults.votesPercentageBySecond}',
									rankBySecond:'${partyResults.rankBySecond}',
									votesDiff:'${partyResults.votesDiff}',
									electorsDiff:'${partyResults.electorsPercentageDiff}'
								}
					distObj.electionResults.push(electionObj);
			</c:forEach>
			this.electionObject.constsNotConsideredYearOne.push(distObj);
		</c:forEach>

		<c:forEach var="comparison" items="${electionsComparisonVO.constituenciesNotConsideredForYearTwo}">
			var distObj={
							districtId:'${comparison.districtId}',
							stateId:'${comparison.stateId}',
							districtName:'${comparison.districtName}',
							constiCount:'${comparison.constituenciesCount}',
							electionResults:[]
						}
				
			<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
				var electionObj={
									partyId:'${partyResults.partyId}',
									partyName:'${partyResults.partyName}',
									candidateId:'${partyResults.candidateId}',
									candidateName:'${partyResults.candidateName}',
									constituencyId:'${partyResults.constituencyId}',
									constituencyName:'${partyResults.constituencyName}',
									votesEarned:'${partyResults.votesEarned}',
									rank:'${partyResults.rank}',
									votesPercentage:'${partyResults.votesPercentage}',
									secondCandidateName:'${partyResults.secondCandidateName}',
									votesEarnedBySecond:'${partyResults.votesEarnedBySecond}',
									votesPercentageBySecond:'${partyResults.votesPercentageBySecond}',
									rankBySecond:'${partyResults.rankBySecond}',
									votesDiff:'${partyResults.votesDiff}',
									electorsDiff:'${partyResults.electorsPercentageDiff}'
								}
					distObj.electionResults.push(electionObj);
			</c:forEach>
			this.electionObject.constsNotConsideredYearTwo.push(distObj);
		</c:forEach>

		buildAccordian();
	}


   function buildAccordian()
	{		
       YUI().use( 'gallery-accordion', function(Y) {
		
		var accordion = new Y.Accordion( {
		contentBox: "#districtWiseResultsMainBody",
		useAnimation: true,
		collapseOthersOnExpand: true
		});
	 
		accordion.render();

		var item1, item2, item3, item4;
		 
		item1 = new Y.AccordionItem( {
		label: "Constituencies Gained Votes% : ${electionsComparisonVO.votesGainedCount} --  in ${electionsComparisonVO.firstYear} [Seats -Won : ${electionsComparisonVO.seatsWonInFirstYearForVotesGained} Lost : ${electionsComparisonVO.seatsLostInFirstYearForVotesGained}] --- in ${electionsComparisonVO.secondYear} [Seats - Won : ${electionsComparisonVO.seatsWonInSecondYearForVotesGained} Lost : ${electionsComparisonVO.seatsLostInSecondYearForVotesGained}] ",
		expanded: true,
		contentBox: "dynamicContentBox1",
		contentHeight: {
			method: "fixed",
			height: 140
		},
		closable: false
		} );
	 
		var str='';
		str+='<table class="districtVotesTable"><tr>';		
		for(var i in electionObject.constsGained)
		{			
		str+='<td>';
		str+='<span id="districtAncSpan">';
		str+='	<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>';
		str+='	<a href="javascript:{}" onclick="setContentToPanel(\'constsGained\','+i+')" class="districtAnc">'+electionObject.constsGained[i].districtName+':'+electionObject.constsGained[i].constiCount+'</a>';
		str+='	</span>';
		str+='</td>';			
		if(i!=0 && i%3==0)
		{			
			str+='	</tr><tr><td colspan="3"> </td></tr><tr>';
		}		
		}
		str+='</tr></table>	';
		item1.set( "bodyContent",str);

		accordion.addItem( item1 );

		item2 = new Y.AccordionItem( {
		label: "Constituencies Lost Votes% : ${electionsComparisonVO.votesLostCount} --  in ${electionsComparisonVO.firstYear} [Seats -Won : ${electionsComparisonVO.seatsWonInFirstYearForVotesLost} Lost : ${electionsComparisonVO.seatsLostInFirstYearForVotesLost}] --- in ${electionsComparisonVO.secondYear} [Seats - Won : ${electionsComparisonVO.seatsWonInSecondYearForVotesLost} Lost : ${electionsComparisonVO.seatsLostInSecondYearForVotesLost}]",
		expanded: false,
		contentBox: "dynamicContentBox2",
		contentHeight: {
			method: "fixed",
			height:140
		}
		} );
	
		var str1='';
		str1+='<table class="districtVotesTable"><tr>';		
		for(var i in electionObject.constsLost)
		{			
		str1+='<td>';
		str1+='<span id="districtAncSpan">';
		str1+='	<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>';
		str1+='	<a href="javascript:{}" onclick="setContentToPanel(\'constsLost\','+i+')" class="districtAnc">'+electionObject.constsLost[i].districtName+':'+electionObject.constsLost[i].constiCount+'</a>';
		str1+='	</span>';
		str1+='</td>';			
		if(i!=0 && i%3==0)
		{			
			str1+='	</tr><tr><td colspan="3"> </td></tr><tr>';
		}		
		}
		str1+='</tr></table>	';

		item2.set( "bodyContent", str1);
		 
		accordion.addItem( item2);
		 
		item3 = new Y.AccordionItem( {
		label: "Constituencies which has not considered in ${electionsComparisonVO.firstYear} -- ( ${electionsComparisonVO.constiNotConstiCountForYearOne} ) -- [Seats -Won : ${electionsComparisonVO.constiNotConsideredForYearOneSeatsWon} Lost : ${electionsComparisonVO.constiNotConsideredForYearOneSeatsLost}] ",
		expanded: false,
		contentBox: "dynamicContentBox3",
		contentHeight: {
			method: "fixed",
			height:140
		}
		} );
 
		var str2='';
		str2+='<table class="districtVotesTable"><tr>';		
		for(var i in electionObject.constsNotConsideredYearOne)
		{			
		str2+='<td>';
		str2+='<span id="districtAncSpan">';
		str2+='	<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>';
		str2+='	<a href="javascript:{}" onclick="setContentToPanel(\'constsNotConsideredYearOne\','+i+')" class="districtAnc">'+electionObject.constsNotConsideredYearOne[i].districtName+':'+electionObject.constsNotConsideredYearOne[i].constiCount+'</a>';
		str2+='	</span>';
		str2+='</td>';			
		if(i!=0 && i%3==0)
		{			
			str2+='	</tr><tr><td colspan="3"> </td></tr><tr>';
		}		
		}
		str2+='</tr></table>	';

		item3.set( "bodyContent",str2);
		 
		accordion.addItem( item3);


		item4 = new Y.AccordionItem( {
		label: "Constituencies which has not considered in ${electionsComparisonVO.secondYear} -- ( ${electionsComparisonVO.constiNotConstiCountForYearTwo} ) -- [Seats -Won : ${electionsComparisonVO.constiNotConsideredForYearTwoSeatsWon} Lost : ${electionsComparisonVO.constiNotConsideredForYearTwoSeatsLost}]",
		expanded: false,
		contentBox: "dynamicContentBox3",
		contentHeight: {
			method: "fixed",
			height:140
		}
		} );
 
		var str3='';
		str3+='<table class="districtVotesTable"><tr>';		
		for(var i in electionObject.constsNotConsideredYearTwo)
		{			
		str3+='<td>';
		str3+='<span id="districtAncSpan">';
		str3+='	<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>';
		str3+='	<a href="javascript:{}" onclick="setContentToPanel(\'constsNotConsideredYearTwo\','+i+')" class="districtAnc">'+electionObject.constsNotConsideredYearTwo[i].districtName+':'+electionObject.constsNotConsideredYearTwo[i].constiCount+'</a>';
		str3+='	</span>';
		str3+='</td>';			
		if(i!=0 && i%3==0)
		{			
			str3+='	</tr><tr><td colspan="3"> </td></tr><tr>';
		}		
		}
		str3+='</tr></table>	';

		item4.set( "bodyContent",str3);
		 
		accordion.addItem( item4);
		 

	   });
    }

	function closeSpan()
	{
		var elmt = document.getElementById("electionResultsPopupDiv");
		if(elmt)
			elmt.style.display="none";
	}

	function showComparisonDetails(id)
	{	
		tr=document.getElementsByTagName('tr')

		for (i=0;i<tr.length;i++)
		{			
			if (tr[i].id && tr[i].id==id)
			{				
				if ( tr[i].style.display=='none' )
				{
					tr[i].style.display = '';
				}
				else
				{
					tr[i].style.display = 'none';
				}
			}
		}		
	}
	
	function showTableDiv(val)
	{
		var index = val.indexOf("_");
		var subStr = val.substring(0,index);
		var elmt=document.getElementById(subStr+"Div");
		var aelmt=document.getElementById(val);

		if(elmt.style.display=='none')
		{
			aelmt.innerHTML="close";
			elmt.style.display='block';
		}
		else
		{
			aelmt.innerHTML="View";
			elmt.style.display='none';
		}
	}
	

</script>
</head>
<body>
       <h2>Elections Comparison Report</h2>
	   <br/>
	   	<c:if test="${electionComparisonResultVO != null }">
		<div style="text-align:left;margin-left:50px;margin-right:10px;">
		<b>Party Participated In Only One Election</b><br/><br/>
		
		<table class="CandidateElectionResultsTable"  width="600px">
	
		    <tr>
			   <th align="center" colspan = "5">District Wise Complete Results</th>
			</tr>  
			<c:forEach var="comparison" items="${electionComparisonResultVO}">
				<tr>
					<td  align="left"><b>${comparison.districtName}</b></td>
					<td colspan="4"  align="center"> <a href="javascript:{}" id="${comparison.districtId}" onclick="showComparisonDetails(this.id)"> View Results</a></td>
				</tr>	
				<tr id="${comparison.districtId}" style="display:none;">
					<td colspan="5"  align="center">
					    <b>Complete Results </b>
					</td>
				</tr>
				<tr id="${comparison.districtId}" style="display:none;">					
					<th>Candidate Name</th>
					<th>Constituency Name</th>
					<th>Votes Earned</th>
					<th>Status</th>
					<th>Votes Percentage</th>
					
				</tr>				
					<c:forEach var="partyResults" items="${comparison.partyElectionResultsVO}">
					<tr id="${comparison.districtId}" style="display:none;">						
						<td><c:url value="candidateElectionResultsAction.action" var="candidateName">
			            <c:param name="candidateId"   value="${partyResults.candidateId}" />
						</c:url>
						<a href='<c:out value="${candidateName}" />'>${partyResults.candidateName}</a></td>
						<td><c:url value="constituencyPageAction.action" var="constituencyName">
			            <c:param name="constituencyId"   value="${partyResults.constituencyId}" />
						</c:url>
						<a href='<c:out value="${constituencyName}" />'>${partyResults.constituencyName}</a></td>
						<td>${partyResults.votesEarned}</td>
						<c:if test="${partyResults.rank == 1 }">
		                <td><c:out value="Won"/></td>
		                </c:if>
		                <c:if test="${partyResults.rank != 1 }">
		                <td><c:out value="Lost"/></td>
		                </c:if>
						<td>${partyResults.votesPercentage}<c:out value="%" /></td>
					</tr>	
					
					</c:forEach>
					<tr id="${comparison.districtId}" style="display:none;">
				      <th colspan="5"  align="center">		    
					 </th>
				  </tr>
				</c:forEach>
			
			</table>
			</div>
		</c:if>

        <c:if test="${electionsComparisonVO != null }">
		<div style="float:right;left:-150px;position:relative;top:10px;">
			<div id="partyHeadingDiv">
				<table border = "0" class="partyElectionTable">
					<tr>
						<th colspan="2" align="left"><u>Party Details...</u></th>
					</tr>
					<tr>
					  <th align="left">Party Name </th>
					  <td align="left"><c:out value=" : ${electionsComparisonVO.partyName}"/></td>
					 </tr>
					 <tr>
					  <th align="left">Elections Years Compared </th>
					  <td align="left"><c:out value=" : ${electionsComparisonVO.firstYear}"/><c:out value=" - " /><c:out value="${electionsComparisonVO.secondYear}"/></td>
					</tr>				
				</table>
			</div>
		</div>
        <div id="electionResultsMain">
			<div id="electionResultsMainHead" class="headingStyle"> Election Results </div>
			<div id="electionResultsMainBody">
			 <table class="CandidateElectionResultsTable" width="350px">
				<tr>
				   <th align="center"> Election &nbsp Year </th>
				   <th align="center"> Votes &nbsp % </th>
				   <th align="center"> Seats &nbsp Won </th>
				</tr>
				<tr>
				  <td align="center">${partyResultsPercentageForYear1.year}</td>
				  <td align="center">${partyResultsPercentageForYear1.percentage} %</td>
				  <td align="center">${partyResultsPercentageForYear1.seatsWOn}</td>
				</tr>
				<tr>
				  <td align="center">${partyResultsPercentageForYear2.year}</td>
				  <td align="center">${partyResultsPercentageForYear2.percentage} %</td>
				  <td align="center">${partyResultsPercentageForYear2.seatsWOn}</td>
				</tr>
			</table>
		   </div>	
       </div>

		<div id="electionResultsPopupDiv" style="display:none;">
			<div id="electionResultsPopupDivHead">
				<div>
					<span id="closeSpan" onclick="closeSpan()">X</span>
					<span id="closeLabelSpan"style="" onclick="closeSpan()"><u>Close</u></span>			
					<span id="labelHead">Candidate Details..</span>						
				</div>
				<div style="font-weight:bold;">
					<table width="100%">
						<tr>
							<td align="center"> Year -- ${electionsComparisonVO.firstYear}</td>
							<td align="center"> Year -- ${electionsComparisonVO.secondYear}</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="electionResultsPopupDivBody" class="yui-skin-sam">
				Content
			</div>		
		</div>

		<div id="districtWiseResultsMain">
			<div id="districtWiseResultsMainHead" class="headingStyle">
				District Wise Results
			</div>
			<div id="districtWiseResultsMainBody" class="yui-skin-sam">				
			</div>
			<script type="text/javascript">				
				buildJSObj();
			</script>
		</div>
		
		
		</c:if>
</body>
</html>