<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionsComparisonVO " %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionComparisonResultVO" %>
<%@page import="com.itgrids.partyanalyst.dto.PartyElectionResultsVO" %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionComparisonReportVO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Comparison Report</title>

	<!-- YUI Dependency Files-->
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">


<style type="text/css">

	
	
	.yui-overlay, .yui-panel-container
	{
		position:relative;
	}
	.yui-module yui-overlay yui-panel
	{
      width:400px;
	}
	.yui-skin-sam .yui-dt-liner 
	{
		margin:0 0 0 5px;
		padding:5px;
	}
	#partyElectionResultsDivMain
	{
		overflow-x:scroll;
		overflow-y:scroll;
		height:200px;
		margin-left:10px;
		margin-top:20px;
		width:95%;
	}
	.yui-skin-sam .yui-dt table
	{
		border:2px solid #DFDFDF;
		border-collapse:separate;
		border-spacing:0;
		font-family:Verdana;
		color:background;
	}
	.partyElecResultTable
	{
	font-family:Verdana;
	width:350px;
	}
	.labelDisplay
	{
		color:appworkspace;
		cursor:auto;
		font-family:verdana;
		font-weight:bold;
		position:absolute;
		font-size:17px;
		
	}
		
	#panelForYearOne
	{
		margin-left:60px;
	}
	#panelForYearTwo
	{
		margin-right:60px;
	}
	#votesPercentageIncDiv table
	{
		width:100%;
	}
	#votesPercentageDecDiv table
	{
		width:100%;
	}
	#partyResultsHeading
	{
		color:highlight;
		font-size:16px;
		font-weight:bold;
		text-align:left;
		margin:10px;
	}
	#mainHeadingDiv
	{
		color:highlight;
		font-size:18px;
		font-family:verdana;
		font-weight:bold;		
		background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
		padding:4px;
	}
	
	#tdThr .yui-skin-sam .yui-panel .hd 
	{
		color:highlight;
		font-weight:bold;
        text-align:left;
		font-size:12px;
	}

	#tdOne .yui-skin-sam .yui-panel .hd 
	{
		color:highlight;
		font-weight:bold;
        text-align:left;
		font-size:12px;
	}
	#tdTwo .yui-skin-sam .yui-panel .hd 
	{
		color:highlight;
		font-weight:bold;
        text-align:left;
		font-size:12px;
	}
	#tdOne .yui-skin-sam .yui-panel .bd
	{
		background-color:FloralWhite;
        color:DarkGreen;
		font-size:13px;
	}
	#tdTwo .yui-skin-sam .yui-panel .bd
	{
		background-color:FloralWhite;
        color:DarkGreen;
		font-size:13px;
	}
	#tdThr .yui-skin-sam .yui-panel .bd
	{
		background-color:FloralWhite;
		color:color:navy;
		font-size:13px;
		font-weight:bold;
	}
	#yearOnePanel .yui-skin-sam .yui-panel .bd
	{
		background-color:FloralWhite;
        color:Black;
		font-size:13px;
	}
    #yearTwoPanel .yui-skin-sam .yui-panel .bd
	{
		background-color:FloralWhite;
        color:Black;
		font-size:13px;
	}
	#yearOnePanel .yui-skin-sam .yui-panel .hd 
	{
	  color:highlight;
	  font-weight:bold;
	  text-align:left;
	  font-size:12px;
	}
    #yearTwoPanel .yui-skin-sam .yui-panel .hd 
	{
	  color:highlight;
	  font-weight:bold;
	  text-align:left;
	  font-size:12px;
	}
	#commentsDiv
	{
		color:appworkspace;
        font-family:verdana;
		font-weight:bold;
		margin:10px;
		text-align:left;
  	}
	legend {
		background-color:#9696C0;
		color:#FFFFFF;
		font-family:status-bar;
		font-size:11px;
		font-weight:bold;
		padding:5px;
    }
    }
	fieldset {
		border:4px solid #F6DFC9;
		margin-bottom:10px;
	}
	#partyPositions
	{
		margin:30px;
		border:1px solid #ADADAD;
		display:none;
	}
	#partyPositionsHead
	{
		background:transparent url(js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png) repeat-x scroll 0 -200px;
		text-align:right;
		padding:5px;
	}
	#partyPositionsCloseSpan
	{
		cursor:pointer;
		font-weight:bold;
		border:1px solid black;
	}
	#partyPositionsBody
	{
		padding:5px;
		overflow:auto;
	}

	#partyPositionsBody table
	{
		width:100%;
	}
	
	.yui-skin-sam .yui-panel .bd
	{
		background-color:#FFFFFF;
	}
	
	.yui-skin-sam .yui-panel-container 
	{
		z-index : 0;
	}

	#oppCandResultsDiv table
	{
		width:100%;
	}

	#completeOneField
	{
		background-color:aliceBlue;
		font-family:verdana;
		font-weight:bold;
		margin:20px;
		width:700px;
		color:Olive;
		border:medium double;
	}
	#completeTwoField
	{
		background-color:aliceBlue;
		font-family:verdana;
		font-weight:bold;
		margin:20px;
		width:700px;
		color:Olive;
		border:medium double;
	}
	.greenColorClass{
		color:green;
	}
	.redColorClass{
		color:red;
	}
	
	.panelOuterDiv
	{
		border:1px solid #808080;
		margin-top:10px;
	}

	.resultsHeadClass
	{
		background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat-x scroll 0 -200px transparent;
		color:highlight;
		font-size:12px;
		font-weight:bold;
		text-align:left;
		padding:5px;
	}
	
	.resultBodyClass
	{
		background-color:floralWhite;
		color:DarkGreen;
		font-size:13px;
		padding:5px;
	}
	
	.resultFooterClass
	{
		background-color:#F2F2F2;
		padding:5px 10px;
	}

	.comparedResultsOuter
	{
		border:1px solid #808080;
		margin-top:10px;
		background-color:#FFFFFF;
	}


	.yuiCloseImg
	{
		background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") no-repeat scroll 0 -300px transparent;		
		cursor:pointer;
		height:15px;
		position:absolute;
		right:6px;
		top:16px;
		width:25px;
	}

	.headerRow
	{
		background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat-x scroll 0 -200px transparent;
		color:highlight;
		font-size:12px;
		font-weight:bold;
		padding:5px;
		text-align:left;
	}

</style>
<script type="text/javascript">	

var electionObject=	{
	    selectedParty:${electionComparisonReportVO.partyId},
		resultsForYearOne:[],
		resultsForYearTwo:[],
		yearOne:${electionComparisonReportVO.yearOne},
		yearTwo:${electionComparisonReportVO.yearTwo}
		
};

function buildDataForYearOne()
{	
   <c:forEach var="electionResults" items="${electionComparisonReportVO.districtWisePartyResultsForYearOne}">
	   var distObjYearOne={
					   districtId:'${electionResults.districtId}',
					   stateId:'${electionResults.stateId}',
					   districtName:'${electionResults.districtName}',
					   stateName:'${electionResults.stateName}',
					   constiCount:'${electionResults.totalConstituencies}',
					   constiParticipated:'${electionResults.constiParticipated}',
					   totalConsti:'${electionResults.constiCount}',
					   seatsWon:'${electionResults.seatsWon}',
					   votesPercent:'${electionResults.votesPercent}',
					   electionResults:[]
			          }
		
	<c:forEach var="partyResults" items="${electionResults.partyElectionResultsList}">
		var electionObj={
							partyId:'${partyResults.partyId}',
							partyName:'${partyResults.partyName}',
							candidateId:'${partyResults.candidateId}',
							candidateName:'${partyResults.candidateName}',
							constituencyId:'${partyResults.constituencyId}',
							constituencyName:'${partyResults.constituencyName}',
							votesEarned:'${partyResults.votesEarned}',
							rank:'${partyResults.rank}',
							votesPercentage:'${partyResults.votesPercent}',
							oppCandidateName:'${partyResults.oppositionCandidates.candidateName}',
							oppCandVotesEarned:'${partyResults.oppositionCandidates.votesEarned}',
							oppCandvotesPercentage:'${partyResults.oppositionCandidates.votesPercentage}',
							oppCandRank:'${partyResults.oppositionCandidates.rank}',
							oppCandParty:'${partyResults.oppositionCandidates.partyName}',
							electors:'${partyResults.electors}'
						}
		distObjYearOne.electionResults.push(electionObj);
	</c:forEach>
	electionObject.resultsForYearOne.push(distObjYearOne);
 </c:forEach>

	 buildPanel('firstpanel',electionObject.resultsForYearOne,electionObject.yearOne);
}

function buildDataForYearTwo()
{
	
   <c:forEach var="electionResults" items="${electionComparisonReportVO.districtWisePartyResultsForYearTwo}">
	var distObjYearTwo={
					   districtId:'${electionResults.districtId}',
					   stateId:'${electionResults.stateId}',
					   districtName:'${electionResults.districtName}',
					   stateName:'${electionResults.stateName}',
					   constiCount:'${electionResults.totalConstituencies}',
					   constiParticipated:'${electionResults.constiParticipated}',
					   totalConsti:'${electionResults.constiCount}',
					   seatsWon:'${electionResults.seatsWon}',
					   votesPercent:'${electionResults.votesPercent}',
					   electionResults:[]
			          }
		
	<c:forEach var="partyResults" items="${electionResults.partyElectionResultsList}">
		var electionObj={
							partyId:'${partyResults.partyId}',
							partyName:'${partyResults.partyName}',
							candidateId:'${partyResults.candidateId}',
							candidateName:'${partyResults.candidateName}',
							constituencyId:'${partyResults.constituencyId}',
							constituencyName:'${partyResults.constituencyName}',
							votesEarned:'${partyResults.votesEarned}',
							rank:'${partyResults.rank}',
							votesPercentage:'${partyResults.votesPercent}',
							oppCandidateName:'${partyResults.oppositionCandidates.candidateName}',
							oppCandVotesEarned:'${partyResults.oppositionCandidates.votesEarned}',
							oppCandvotesPercentage:'${partyResults.oppositionCandidates.votesPercentage}',
							oppCandRank:'${partyResults.oppositionCandidates.rank}',
							oppCandParty:'${partyResults.oppositionCandidates.partyName}',
							electors:'${partyResults.electors}'
						}
		distObjYearTwo.electionResults.push(electionObj);
	</c:forEach>
	this.electionObject.resultsForYearTwo.push(distObjYearTwo);
 </c:forEach>

	  buildPanel('secondpanel',electionObject.resultsForYearTwo,electionObject.yearTwo);
}

function buildPanel(id,arr,year)
{
		var elmt = document.getElementById(id);

		if(!elmt)
			return;

	   var lyear='';
	   lyear="Party Results In ";
	   lyear+='';
	   lyear+=year;
	
	   var str='';
	   str+='<div id="'+id+'_electionResults_head" class="resultsHeadClass"> '+lyear+' </div>';
	   str+='<div id="'+id+'_electionResults_body" class="resultBodyClass">';
	   str+='<table class="partyElecResultTable">';
	   str+='<tr>';
	   str+='<th>';
       str+='</th>';
	   str+='<th align="left">District</th>';
       str+='<th>*TC</th>';
       str+='<th>*PC</th>';
	   str+='<th>Won</th>';
	   str+='<th>%</th>';
	   for(var i in arr)
	   {
		   var totalconsti=arr[i].electionResults.length;
		   str+='<tr>';
		   if(i==0)
		   str+='<td align="center"><input type="radio" name="'+id+'_radio" value="'+arr[i].districtId+'" checked="true" onclick="buildPartyElecResultsTable(\''+id+'\')"/></td>';
		   else
		   str+='<td align="center"><input type="radio" name="'+id+'_radio" value="'+arr[i].districtId+'" onclick="buildPartyElecResultsTable(\''+id+'\')"/></td>';
		   str+='<td align="left">'+arr[i].districtName+'</td>';
		   str+='';
		   str+='<td align="center">'+arr[i].totalConsti+'</td>';
		   str+='<td align="center">'+arr[i].constiParticipated+'</td>';
           str+='<td align="center">'+arr[i].seatsWon+'</td>';
		   str+='<td align="center">'+arr[i].votesPercent+'</td>';
		   str+='</tr>';
		   //if(++i%2 == 0)
				//str+='</tr><tr>';
       }
	   //str+='</tr>';
	   str+='</table>';
	   str+='</div>';
	   str+='<div id="'+id+'_electionResults_footer" class="resultFooterClass">';
	   str+='<table>';
       str+='<tr>';
	   str+='<td><input type="button" name="'+id+'_compbutton" value="Compared Results" onclick="getComparedResults(\''+id+'\')"/></td>';
	   str+='</tr>';
	   str+='</table>';
	   str+='</div>';
	   str+='</div>';
		
		elmt.innerHTML = str;
	   /*myPanel = new YAHOO.widget.Panel(id, { 
				 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
       });
	   myPanel.setHeader(lyear);
       myPanel.setBody(str);
	   myPanel.setFooter(footer);
       myPanel.render();*/
}

function buildPartyElecResultsTable(radioBtnId)
{
    var selectdPanelfirst;
	var selectdPanelsecond;
	var flag1=false;
	var flag2=false;
	var elements = document.getElementsByTagName('input'); 
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="firstpanel_radio" && elements[i].checked==true && radioBtnId=="firstpanel")
		{
		flag1=true
		selectdPanelfirst = elements[i].value;
		}
        else if(elements[i].type=="radio" && elements[i].name=="secondpanel_radio" && elements[i].checked==true && radioBtnId=="secondpanel")
		{
		flag2=true;
		selectdPanelsecond = elements[i].value;
		}

	}

   var districtId;
   var districtName;
   var elecId;
   var arr = new Array();
   if(flag1 == true)
   {
	districtId=selectdPanelfirst;
    arr=electionObject.resultsForYearOne;
    elecId = '${electionComparisonReportVO.elecIdYearOne}';
   }
   else if(flag2 == true){
   districtId=selectdPanelsecond;
   arr=electionObject.resultsForYearTwo;
   elecId = '${electionComparisonReportVO.elecIdYearTwo}';
   }

   for(var i in arr)
   {
      if(districtId == arr[i].districtId)
	  {
		 districtName=arr[i].districtName;
         var str='';
		 str+='<table id="partyElecResultsTable">';
		 var elecResults=arr[i].electionResults;
		 
		 for(var k in elecResults)
		 {
           str+='<tr>';
		   str+='<td><a href="#"  onclick="getConstituencyCompleteDetails('+elecResults[k].constituencyId+','+elecResults[k].partyId+','+elecId+')">'+elecResults[k].constituencyName+'</td>';
		   str+='<td>'+elecResults[k].candidateName+'</td>';
		   str+='<td>'+elecResults[k].partyName+'</td>';
           str+='<td>'+elecResults[k].votesEarned+'</td>';
		   str+='<td>'+elecResults[k].electors+'</td>';
		   str+='<td>'+elecResults[k].votesPercentage+'</td>';
           str+='<td>'+elecResults[k].rank+'</td>';
           str+='<td>'+elecResults[k].oppCandidateName+'</td>';
		   str+='<td>'+elecResults[k].oppCandParty+'</td>';
		   str+='<td>'+elecResults[k].oppCandVotesEarned+'</td>';
		   str+='<td>'+elecResults[k].oppCandvotesPercentage+'</td>';
		   str+='<td>'+elecResults[k].oppCandRank+'</td>';
		   str+='</tr>';
		 }
		 str+='</table>';
	  }
   }
	
	var elmt = document.getElementById('partyElectionResultsDiv');
	elmt.innerHTML=str;

    var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("partyElecResultsTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constituencyName"
		}, {
			key : "candidateName"
		}, {
			key : "partyName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "electors",parser:"number"
		}, {
			key : "votesPercentage"
		} , {
			key : "rank",parser:"number"
		},{
			key : "oppCandidateName"
		},{
			key : "oppCandParty"
		},{
			key : "oppCandVotesEarned"
		},{
			key : "oppCandvotesPercentage"
		},{
			key : "oppCandRank",parser:"number"
		}]
	};

	var resultsColumnDefs = [ {
		key : "constituencyName",
		label : "Constituency",
		sortable : true
	}, {
		key : "candidateName",
		label : "Candidate",
		sortable : true
	}, {
		key : "partyName",
		label : "Party",
		sortable : true
	}, {
		key : "votesEarned",
		label : "Votes",
		sortable : true
	}, {
		key : "electors",
		label : "Electors",
		sortable : true
	}, {
		key : "votesPercentage",
		label : "Votes %",
		sortable : true
	}, {
		key : "rank",
		label : "Rank",
		sortable : true
	}, {
		key : "oppCandidateName",
		label : "Opposition Candidate",
		sortable : true
	}, {
		key : "oppCandParty",
		label : "Opposition Party",
		sortable : true
	}, {
		key : "oppCandVotesEarned",
		label : "Votes",
		sortable : true
	}, {
		key : "oppCandvotesPercentage",
		label : "Votes %",
		sortable : true
	}, {
		key : "oppCandRank",
		label : "Rank",
		sortable : true
	}];

    var myDataTable = new YAHOO.widget.DataTable("partyElectionResultsDiv",resultsColumnDefs, resultsDataSource); 
	
	if(flag1==true)
	displayLabel(electionObject.yearOne,districtName);
	else
	displayLabel(electionObject.yearTwo,districtName);
}

function displayLabel(year,district)
{
     var str='';
	 str+="In ";
	 str+=year;
     str+=", ";
	 str+=district;
	 str+='  District Results';

	 var elmt = document.getElementById("displayLabelDiv");
	 if(elmt)
		 elmt.innerHTML=str;
}

function overallResultsForYearOne()
{
	var elmt = document.getElementById("overallResultsYearOnePanel");	
	if(!elmt)
		return;


   var str='';
   str+='<div id="yearOneResults_head" class="resultsHeadClass">Complete Results for Year '+electionObject.yearOne+' ...</div>';
   str+='<div class="resultBodyClass">';
   str+='<table class="partyElecResultTable" style="width:100%;">';
   str+='<tr>';
   str+='<th>Party</th>';
   str+='<th>*PC</th>';
   str+='<th>1Pos</th>';
   str+='<th>2Pos</th>';
   str+='<th>3Pos</th>';
   str+='<th>4Pos</th>';
   str+='<th>(*P) %</th>';
   str+='<th>(*S) %</th>';
   str+='</tr>';
   <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearOne}">
   str+='<tr>';
   if('${partyPositions.partyId}' != electionObject.selectedParty)
   str+='<td align="center">'+"${partyPositions.partyName}"+'</td>';
   if('${partyPositions.partyId}' == electionObject.selectedParty)
   str+='<td align="center" style="color:red;">'+"${partyPositions.partyName}"+'</td>';
   str+='<td align="center">'+"${partyPositions.totalConstiParticipated}"+'</td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'1\')">'+"${partyPositions.totalSeatsWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'2\')">'+"${partyPositions.secondPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'3\')">'+"${partyPositions.thirdPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'4\')">'+"${partyPositions.fourthPosWon}"+'</a></td>';
   str+='<td align="center">'+"${partyPositions.votesPercentage}"+'</td>';
   str+='<td align="center">'+"${partyPositions.completeVotesPercent}"+'</td>';
   str+='</tr>';
   </c:forEach>
   str+='</table>';
   str+='</div>';
   str+='</div>';	
	
   elmt.innerHTML = str;
 /*  myPanel = new YAHOO.widget.Panel("overallResultsYearOnePanel", {
                 width: "400", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
   });
   myPanel.setHeader("Complete Results for Year "+electionObject.yearOne+" ...");
   myPanel.setBody(str);
   myPanel.render();*/
}


function overallResultsForYearTwo()
{
	var elmt = document.getElementById("overallResultsYearTwoPanel");	
	if(!elmt)
		return;

   var str='';
   str+='<div id="yearTwoResults_head" class="resultsHeadClass">Complete Results for Year '+electionObject.yearTwo+' ...</div>';
   str+='<div class="resultBodyClass">';
   str+='<table class="partyElecResultTable" style="width:100%;">';
   str+='<tr>';
   str+='<th>Party</th>';
   str+='<th>*PC</th>';
   str+='<th>1Pos</th>';
   str+='<th>2Pos</th>';
   str+='<th>3Pos</th>';
   str+='<th>4Pos</th>';
   str+='<th>(*P) %</th>';
   str+='<th>(*S) %</th>';
   str+='</tr>';
   <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearTwo}">
   str+='<tr>';
   if('${partyPositions.partyId}' != electionObject.selectedParty)
   str+='<td align="center">'+"${partyPositions.partyName}"+'</td>';
   if('${partyPositions.partyId}' == electionObject.selectedParty)
   str+='<td align="center" style="color:red;">'+"${partyPositions.partyName}"+'</td>';
   str+='<td align="center">'+"${partyPositions.totalConstiParticipated}"+'</td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'1\')">'+"${partyPositions.totalSeatsWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'2\')">'+"${partyPositions.secondPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'3\')">'+"${partyPositions.thirdPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'4\')">'+"${partyPositions.fourthPosWon}"+'</a></td>';
   str+='<td align="center">'+"${partyPositions.votesPercentage}"+'</td>';
   str+='<td align="center">'+"${partyPositions.completeVotesPercent}"+'</td>';
   str+='</tr>';
   </c:forEach>
   str+='</table>';
   str+='</div>';
   str+='</div>';	

   elmt.innerHTML = str;

  /*
   myPanel = new YAHOO.widget.Panel("overallResultsYearTwoPanel", {
                 width: "400", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
   });
   myPanel.setHeader("Complete Results for Year "+electionObject.yearTwo+" ...");
   myPanel.setBody(str);
   myPanel.render();*/
}

function getDiffPercent()
{
	var elmt = document.getElementById("diffPercentPanel");	
	if(!elmt)
		return;

    var diffPercent = '${electionComparisonReportVO.votesPercentDiff}';
	var seatesDiff =  '${electionComparisonReportVO.seatsDiff}';
	var str = '';
	str+='<div class="resultBodyClass">';
	str+='<table width="100%" style="width:100%">';
	
		
	str+='<tr>';
	str+='<th align="left" style="width: 27%;text-align:left;"> Seats Difference ( '+electionObject.yearOne+'  --  '+electionObject.yearTwo+'  ) :   </th>';
	if(seatesDiff > 0)
	str+='<td style="color:green;font-size:15px;width:25%;text-align:left;"> + '+seatesDiff+' Seats </td>';
	if(seatesDiff < 0)
    str+='<td style="color:red;font-size:15px;width:25%;text-align:left;"> - '+seatesDiff+' Seats </td>';

	str+='<th align="left" style="width: 33%;text-align:left;" > Votes Percent Difference ( '+electionObject.yearOne+'  --  '+electionObject.yearTwo+'  ) :   </th>';
	if(diffPercent > 0)
	str+='<td style="color:green;font-size:15px;text-align:left;">'+diffPercent+' % increase</td>';
	if(diffPercent < 0)
    str+='<td style="color:red;font-size:15px;text-align:left;">'+diffPercent+' % decrease</td>';
    str+='</tr>';


    str+='</table>';
	str+='</div>';

	elmt.innerHTML = str;
	/*myPanel = new YAHOO.widget.Panel("diffPercentPanel", {
                 width: "400", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
   });
   //myPanel.setBody("Complete Voting Percent Difference (  "+electionObject.yearOne+"  --  "+electionObject.yearTwo+"  )  :   " +diffPercent );
   myPanel.setBody(str);
   myPanel.render();*/
}

function getCompleteConstituencyElecResult(constiId,partyId)
{
	 var elecType='${electionComparisonReportVO.electionType}';
	 var state='${electionComparisonReportVO.stateId}';
   
}

function getPartyPositions(partyId,id,rankPos)
{

	var imgElmt = document.getElementById("imageYearOneId");
    imgElmt.style.display = 'block';
	
  var elecYear;
  if(id == "overallResultsYearOne")
  elecYear = electionObject.yearOne;
  else if(id == "overallResultsYearTwo")
  elecYear = electionObject.yearTwo;

      var elecType='${electionComparisonReportVO.electionType}';
	  var state='${electionComparisonReportVO.stateId}';
	  var hasAllianc='${electionComparisonReportVO.hasAlliances}';

	  var jsObj= 
	  {
          electionType:elecType,
		  electionYear:elecYear,
		  stateId:state,
		  party:partyId,
		  rank:rankPos,
          hasAlliance:hasAllianc
		  
	  }
	  var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	 callPartyPositionAjax(param,jsObj);
}

function callPartyPositionAjax(param,jsObj){
	var myResults;
	var url = "<%=request.getContextPath()%>/electionComparisonPositionAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayPartyPositionResults(jsObj,myResults);							
						}catch (e) {   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function displayPartyPositionResults(jsObj,data)
{
    if(data[0]==null)
	{
		alert("No results found");
		return;
	}

	var divElmt = document.getElementById("partyPositions");
	var divElmtBody = document.getElementById("partyPositionsBody");
	var imgElmt = document.getElementById("imageYearOneId");

	//var divElmtHead = document.getElementById("labelHead");	
	rank = jsObj.rank;
	var party = jsObj.party;
	if(rank==-1)
		rank = 'N';
	/*divElmtHead.innerHTML=" "+'${stateData.party}'+" In position : "+rank+" And Its Opposition Party Details";*/

	divElmt.style.display = 'block';
	imgElmt.style.display = 'none';

	var str='';
	str+='<table id="partyPositionTable"  class="" border="0">';	
	for(var i in data)
	{		
		str+='<tr>';
		str+='<td><a href="#"  onclick="getConstituencyCompleteDetails('+data[i].constituencyId+','+data[i].partyId+','+data[i].electionId+')">'+data[i].constituencyName+'</a></td>';
		str+='<td>'+data[i].candidateName+'</td>';
		str+='<td align="right">'+data[i].votePercentage+'</td>';
		for (var d in data[i].oppPartyPositionInfoList)
		{
			str+='<td>'+data[i].oppPartyPositionInfoList[d].candidateName+'</td>';
			str+='<td>'+data[i].oppPartyPositionInfoList[d].partyName+'</td>';
			str+='<td align="right">'+data[i].oppPartyPositionInfoList[d].votePercentage+'</td>';
		}
		str+='</tr>';
	}	
	str+='</table>'
	divElmtBody.innerHTML=str;
	
	buildPartyPositionDataTable(data,rank);	
}

function getConstituencyCompleteDetails(constituencyId,partyId,electionId)
{
   var jsObj= 
	  {
	     constiId:constituencyId,
		 partyId:partyId,
		 electionId:electionId
      }
	  var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	 callCompleteConstituencyResultsAjax(param,jsObj);
   
}

function callCompleteConstituencyResultsAjax(param,jsObj)
{
   var myResults;
	var url = "<%=request.getContextPath()%>/electionComparisonConstiResultsAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayCompleteConstiResults(jsObj,myResults);							
						}catch (e) {   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function displayCompleteConstiResults(jsObj,data)
{
    
    var candidateObj={
								candidateName:data.candidateName,
								partyName:data.partyName,
								constituencyName:data.constituencyName,
								electionType:data.electionType,
								electionYear:data.electionYear,
								districtName:data.districtName,
								stateName:data.stateName,
								votesEarned:data.votesEarned,
								votePercentage:data.votesPercentage,
								rank:data.rank,
								status:'',
								oppositionCandidates:[]
							};

						    <c:if test="${data.rank == 1}">
				            candidateObj.status='Won';
			                </c:if>						
			                <c:if test="${data.rank != 1}">
				            candidateObj.status='Lost';
			                </c:if>
                           for(var i in data.oppositionCandidates)
	                       {
                              var oppositionList={
									candidateName:data.oppositionCandidates[i].candidateName,
									partyName:data.oppositionCandidates[i].partyName,
									votesEarned:data.oppositionCandidates[i].votesEarned,
									votesPercentage:data.oppositionCandidates[i].votesPercentage,
									rank:data.oppositionCandidates[i].rank,
									status:''
								};
						        <c:if test="${data.oppositionCandidates[i].rank == 1}">
							    oppositionList.status='Won';
						        </c:if>
						        <c:if test="${data.oppositionCandidates[i].rank != 1}">
							    oppositionList.status='Lost';
						        </c:if>
								candidateObj.oppositionCandidates.push(oppositionList);
					       }
						  
                           showElectionResultsInPopup(candidateObj);
}

function showElectionResultsInPopup(data)
{
		
	var str='';
	str+='<fieldset id="electionProfileField">';
	str+='<legend> Election Profile Info </legend>';
	str+='<div id="electionProfileDiv">';
	str+='<table class="elecInfoTableClass" width="100%">';
	str+='<tr>';
	str+='<th align="center">Name</th>';
	str+='<td colspan="3" align="left">'+data.candidateName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th align="center">Party</th>';
	str+='<td align="left">'+data.partyName+'</td>';	
	str+='<th align="center">Constituency</th>';
	str+='<td align="left">'+data.constituencyName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th align="center">District</th>';
	str+='<td align="left">'+data.districtName+'</td>';	
	str+='<th align="center">State</th>';
	str+='<td align="left">'+data.stateName+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="electionResultsField">';
	str+='<legend> Election Results Info </legend>';
	str+='<div id="electionResultsDiv">';
	str+='<table class="elecInfoTableClass" width="100%">';
	str+='<tr>';
	str+='<th align="center">Election Type</th>';
	str+='<td colspan="3" align="left">'+data.electionType+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th align="center">Year</th>';
	str+='<td align="left">'+data.electionYear+'</td>';	
	str+='<th align="center">Rank</th>';
	str+='<td align="left">'+data.rank+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th align="center">Votes Earned</th>';
	str+='<td align="left">'+data.votesEarned+'</td>';	
	str+='<th align="center">Votes Percentage</th>';
	str+='<td align="left">'+data.votePercentage+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';	
	str+='</fieldset>';
	//--------
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> Opposition\'s Results Info </legend>';
	str+='<div id="oppCandResultsDiv">';	
	str+='</div>';
	str+='</fieldset>';
	
	
	var candidateElectionResultPanel = new YAHOO.widget.Panel("cand_elec_div_panel", 
				{
					width:"800px", 
					fixedcenter : true, 
					visible : true,  
					constraintoviewport : false,					
					iframe :true,
					modal :false,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	
	
	candidateElectionResultPanel.setHeader(data.electionYear+' '+data.electionType+' Election Details');
	candidateElectionResultPanel.setBody(str);
	candidateElectionResultPanel.render();	

	 var myDataSource = new YAHOO.util.DataSource(data.oppositionCandidates); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
	            fields: [
							{	key : "candidateName"	},
							{	key : "partyName"	},
							{	key : "rank",parser:"number"	},
							{	key : "votesEarned",parser:"number" },
							{	key : "votesPercentage",parser:"number" }
					
						]
	        }; 
	
	 var myColumnDefs = [ 
	            {key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
	            {key:"partyName", label:'Party Name', sortable:true, resizeable:true}, 
	            {key:"rank", label:'Rank',sortable:true, resizeable:true}, 
	            {key:"votesEarned",label:'Votes Earned', sortable:true, resizeable:true}, 
	            {key:"votesPercentage",label:'Votes %', sortable:true, resizeable:true} 
	        ]; 
	 
	var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource); 
}


function closePartyPosition()
{
	var divElmt = document.getElementById("partyPositions");
	divElmt.style.display = 'none';
}
function buildPartyPositionDataTable(info,rank)
{
	var divHead = document.getElementById('partyPositionsHead');
	var hStr = '';
	hStr+='<span id="partyPositionsCloseSpan" onclick="closePartyPosition()"> X </span>'
	if(divHead)
		divHead.innerHTML = hStr;

	if(info[0]==null)	
		return;
	var count=0;

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("partyPositionTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : []
	};	
	
	var key1={key : "constituencyName",formatter:YAHOO.widget.DataTable.formatLink};
	var key2={key:"candidateName"};
	var key3={key : "votePercentage",parser:"number"};
	resultsDataSource.responseSchema.fields.push(key1);
	resultsDataSource.responseSchema.fields.push(key2);
	resultsDataSource.responseSchema.fields.push(key3);

	for (var k in  info[0].oppPartyPositionInfoList)
	{		
		var key4={key : "cName"+k};
		var key5={key : "pName"+k};		
		var key7={key : "vPercentage"+k,parser:"number"};
		resultsDataSource.responseSchema.fields.push(key4);
		resultsDataSource.responseSchema.fields.push(key5);
		resultsDataSource.responseSchema.fields.push(key7);
	}	
	//--------
   
	var resultsColumnDefs = [
		{
			label:"Candidate Details in "+rank+" position ",
			className:"yui-dt-sortable ",
			children:[ 	
						{
							key : "constituencyName",		
							label : "Constituency",
							sortable : true
						},
						{
							key : "candidateName",		
							label : "Candidate Name",
							sortable : true
						},
						{
							key : "votePercentage",
							parser:"number",
							label : "Votes&nbsp%",
							sortable : true
						}
					 ]
		}
	];
	
	for (var d in info[0].oppPartyPositionInfoList)
	{
		count++;
		var obj = {
						label:"Candidate Details in "+(count)+" position ",
						className:"yui-dt-sortable ",
						children:[ 	
									{
										key : "cName"+d,		
										label : "Name",
										sortable : true
									},
									{
										key : "pName"+d,		
										label : "Party",
										sortable : true
									},
									{
										key : "vPercentage"+d,
										parser:"number",
										label : "Votes&nbsp%",
										sortable : true
									}
								 ]
					}
		
		resultsColumnDefs.push(obj);		
	}

	var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 5
			    }) 
				};	

	var myDataTable = new YAHOO.widget.DataTable("partyPositionsBody",resultsColumnDefs, resultsDataSource,myConfigs);  

}


function getComparedResults(panelId)
{

   var districtId;
   var elements = document.getElementsByTagName('input'); 
	  for(var i=0;i<elements.length;i++)
	  {
		if(elements[i].type=="radio" && elements[i].name=="firstpanel_radio" && elements[i].checked==true && panelId=="firstpanel")
		{
		districtId = elements[i].value;
		}
        else if(elements[i].type=="radio" && elements[i].name=="secondpanel_radio" && elements[i].checked==true && panelId=="secondpanel")
		{
		districtId = elements[i].value;
		}

	  }
	  var yearOne=electionObject.yearOne;
      var yearTwo=electionObject.yearTwo;
	  var elecType='${electionComparisonReportVO.electionType}';
	  var state='${electionComparisonReportVO.stateId}';
	  var party='${electionComparisonReportVO.partyId}';
	  var hasAllianc='${electionComparisonReportVO.hasAlliances}';

	  var jsObj= 
	  {
          firstYear:yearOne,
		  secondYear:yearTwo,
		  electionType:elecType,
		  stateId:state,
		  partyId:party,
          hasAlliance:hasAllianc,
		  district:districtId
	  }
	  var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	  callAjax(param,jsObj);
}



function callAjax(param,jsObj){
	var myResults;
	var url = "<%=request.getContextPath()%>/electionComparisonAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayComparedResults(jsObj,myResults);							
						}catch (e) {   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildDataTable(divId,arr,yearOne,yearTwo)
{
    var colorClass = '';
	if(divId == "votesPercentageIncDiv")
	colorClass = "greenColorClass";
    if(divId == "votesPercentageDecDiv")
	colorClass = "redColorClass";

	var resultsDataSource = new YAHOO.util.DataSource(arr);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constiName"
		}, {
			key : "candName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "votesPercent",parser:"number"
		}, {
			key : "votesPercentDiff",parser:"number"
		} ,	{
			key : "secndCandName"
		} ,{
			key : "votesEarnedBySecnd",parser:"number"
		} , {
			key : "secndVotesPercent",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ 
	{
		key : "constiName",
		label : "Constituency",
		sortable : true
	}, 
	{
		label:"Year - "+yearOne,
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "candName",
						label : "Candidate",
						sortable : true
					},
					{
						key : "votesEarned",
						label : "Votes",
						sortable : true
					},
					{
						key : "votesPercent",
						label : "%",
						sortable : true
					}					
				]
	},
	{
		label:"Diff %",
		className:"yui-dt-sortable ",
		children:[ 					
					{
						key : "votesPercentDiff",
						label : "% Diff",
						className:colorClass,
						sortable : true
					}
				]
	},
	{
		label:"Year - "+yearTwo,
		className:"yui-dt-sortable ",
		children:[ 
					
					{
						key : "secndCandName",
						label : "Candidate",
						sortable : true
					},
					{
						key : "votesEarnedBySecnd",
						label : "Votes",
						sortable : true
					},
					{
						key : "secndVotesPercent",
						label : "%",
						sortable : true
					}									
				 ]
	}
	];

    myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 
}

function hideComparedResultsDiv()
{
	var elmt = document.getElementById("comparedResultsPanel");
	if(!elmt)
		return;
		
	elmt.style.display = 'none';
	
}

function displayComparedResults(jsObj,data)
{   	
	var elmt = document.getElementById("comparedResultsPanel");
	if(!elmt)
		return;

	elmt.style.display = 'block';

	var str='';
	str+='<div id="comparedResults_main" class="comparedResultsOuter">';
	str+='<div id="comparedResults_head" class="resultsHeadClass">';
	str+='Compared Results';
	str+='<span style="float:right;"><a href="javascript:{}" class="yuiCloseImg" onclick="hideComparedResultsDiv()"> </a></span>';
	str+='</div>';
	str+='<div id="comparedResults_body" class="comparedResultsBody">';
	str+='<div id="completeOneField">';	
	str+='<legend>'+data.yearOne+' Complete Results</legend>';
	str+='<table width="100%">';
	str+='<tr>';
    str+='<td align="center">Party</td>';
	str+='<td align="center" style="color:DodgerBlue;">*CP</td>';
	str+='<td align="center">SeatsWon</td>';
	str+='<td align="center">2 Pos</td>';
	str+='<td align="center">3 Pos</td>';
	str+='<td align="center">Votes %</td>';
    str+='<td align="center">Overall %</td>';
	for(i in data.positionsYearOne){
	str+='<tr>';
	if(data.positionsYearOne[i].partyId == electionObject.selectedParty)
    str+='<td align="center" style="color:red;">'+data.positionsYearOne[i].partyName+'</td>';
	else
	str+='<td align="center">'+data.positionsYearOne[i].partyName+'</td>';
	str+='<td align="center">'+data.positionsYearOne[i].totalConstiParticipated+'</td>';
	str+='<td align="center">'+data.positionsYearOne[i].totalSeatsWon+'</td>';
	str+='<td align="center">'+data.positionsYearOne[i].secondPosWon+'</td>';
	str+='<td align="center">'+data.positionsYearOne[i].thirdPosWon+'</td>';
	str+='<td align="center">'+data.positionsYearOne[i].votesPercentage+'</td>';
	str+='<td align="center">'+data.positionsYearOne[i].completeVotesPercent+'</td>';
	str+='</tr align="center">';
	}
	str+='<tr>';
    str+='<td style="color:DodgerBlue;">* CP -- Constituencies Participated </td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='<div id="completeTwoField">';
	str+='<legend>'+data.yearTwo+' Complete Results</legend>';
	str+='<table width="100%">';
	str+='<tr>';
    str+='<td align="center">Party</td>';
	str+='<td align="center" style="color:DodgerBlue;">*CP</td>';
	str+='<td align="center">SeatsWon</td>';
	str+='<td align="center">2 Pos</td>';
	str+='<td align="center">3 Pos</td>';
	str+='<td align="center">Votes %</td>';
    str+='<td align="center">Overall %</td>';
	for(i in data.positionsYearTwo){
	str+='<tr>';
	if(data.positionsYearTwo[i].partyId == electionObject.selectedParty)
    str+='<td align="center" style="color:red;">'+data.positionsYearTwo[i].partyName+'</td>';
	else
	str+='<td align="center">'+data.positionsYearTwo[i].partyName+'</td>';
	str+='<td align="center">'+data.positionsYearTwo[i].totalConstiParticipated+'</td>';
	str+='<td align="center">'+data.positionsYearTwo[i].totalSeatsWon+'</td>';
	str+='<td align="center">'+data.positionsYearTwo[i].secondPosWon+'</td>';
	str+='<td align="center">'+data.positionsYearTwo[i].thirdPosWon+'</td>';
	str+='<td align="center">'+data.positionsYearTwo[i].votesPercentage+'</td>';
	str+='<td align="center">'+data.positionsYearTwo[i].completeVotesPercent+'</td>';
	str+='</tr align="center">';
	}
	str+='<tr>';
    str+='<td style="color:DodgerBlue;">* CP -- Constituencies Participated </td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='<fieldset id="electionProfileField">';
	str+='<legend> VOTES PERCENT INCREASE</legend>';	
	str+='<div id="votesPercentageIncDiv">';
	
	str+='</div>';	
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="electionResultsField">';
	str+='<legend> VOTES PERCENT DECREASE</legend>';
	str+='<div id="votesPercentageDecDiv">';	
	str+='</div>';	
	str+='</fieldset>';
	//--------
	str+='<table>'
	str+='<tr>';
	str+='<td style="vertical-align:top;">';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> NOT CONSIDERED - '+data.yearOne+' </legend>';
	str+='<div id="notConsYearOneDiv">';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='<td style="vertical-align:top;">';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> NOT CONSIDERED - '+data.yearTwo+' </legend>';
	str+='<div id="notConsYearTwoDiv">';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='</tr></table>';
	str+='</div>';
	str+='</div>';
	str+='</div>';

	elmt.innerHTML = str;

	/*var comparedElectionResultPanel = new YAHOO.widget.Panel("comparedResultsPanel", 
				{ 					
					fixedcenter : true, 
					visible : true,  
					constraintoviewport : true,
					iframe :true,
					modal :false,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	
	
	comparedElectionResultPanel.setHeader('Compared Results');
	comparedElectionResultPanel.setBody(str);
	comparedElectionResultPanel.render();*/
	

	buildDataTable("votesPercentageIncDiv",data.votesPercentGainedResults,data.yearOne,data.yearTwo);
	buildDataTable("votesPercentageDecDiv",data.votesPercentLostResults,data.yearOne,data.yearTwo);
	buildYearDataTable("notConsYearOneDiv",data.notConsideredYearOneResults);
	buildYearDataTable("notConsYearTwoDiv",data.notConsideredYearTwoResults);
}

function buildYearDataTable(divId,data)
{
	var resultsDataSource = new YAHOO.util.DataSource(data);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constituencyName"
		}, {
			key : "candidateName"
		}, {
			key : "partyName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "votesPercent",parser:"number"
		} ,	{
			key : "rank",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ 
	{
		key : "constituencyName",
		label : "Constituency",
		sortable : true
	},
	{
		key : "candidateName",
		label : "Candidate",
		sortable : true
	},
	{
		key : "partyName",
		label : "Party",
		sortable : true
	},
	{
		key : "votesEarned",
		label : "Votes",
		sortable : true
	},
	{
		key : "votesPercent",
		label : "%",
		sortable : true
	},
	{
		key : "rank",
		label : "Rank",
		sortable : true
	}]
	
	myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 
}

</script>
</head>
<body>

<table cellspacing="0" cellpadding="0" border="0" style="margin-top:30px;">
	<tbody><tr>
		<td valign="top"><img border="none" src="images/icons/electionResultsAnalysisReport/first.png"></td>
		<td valign="top"><div id="mainHeadingDiv">Election Comparison Report</div></td>
		<td valign="top"><img border="none" src="images/icons/electionResultsAnalysisReport/second.png"></td>
	</tr>
</tbody></table>

<br/><br/>
<div>
<div id = "partyPositions" class="yui-skin-sam">
	<div id = "partyPositionsHead"></div>
    <div id = "partyPositionsBody"></div>	
</div>
<div id="CandidatePageLayoutDiv">
	<div id="cand_elect_div" class="yui-skin-sam" style="position:absolute;">
		<div id="cand_elec_div_panel">
	</div>
</div>

 <div id="comparedResultsDiv" class="yui-skin-sam" style="position:absolute;top:480px;">
	<div id="comparedResultsPanel"></div>
 </div>

<div id="imageYearOneId" align="center" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/barloader.gif" /></img>
</div>

<table style="width: 98%;">
<tr>
	<td id="tdThr" width="70%" colspan="2">
	     <div id="diffPercentDiv" class="yui-skin-sam" style="margin-left:20px;">
		     <div id="diffPercentPanel" class="panelOuterDiv"></div>
		 </div>
	 </td>
</tr>
<tr>
	<td id="tdTwo" width="70%" style="padding:-left	">
		 <div id="overallResultsYearTwoDiv" class="yui-skin-sam" style="margin-top:10px;margin-left:20px">
			 <div id="overallResultsYearTwoPanel" class="panelOuterDiv"></div>
		 </div>
	 </td>
	 <td rowspan="2" style="vertical-align:top;">
	     <div id="mainGraphDiv" class="yui-skin-sam" style="margin-left:35px">
		     <div id="graphDiv">
			  <IMG id="chartImg" SRC="charts/<%=request.getAttribute("barChartName")%>" WIDTH="350" HEIGHT="300">
			 </div>
		 </div>
	 </td>
</tr>
<tr>	
	<td id="tdOne" width="70%">
	     <div id="overallResultsYearOneDiv" class="yui-skin-sam" style="margin-left:20px;">
		     <div id="overallResultsYearOnePanel" class="panelOuterDiv"></div>
		 </div>
	 </td>
    
</tr>

<tr>
     <td align="left"><div id="commentsDiv">  *PC -- Participated Constituencies |   *TC --Total Constituencies </div></td>
</tr>
<tr>
	 <td align="left"><div id="commentsDiv">  *P -- Party Participated  |   *S --Total State </div></td>

</tr>
<tr>
    <td colspan="2">
		<div id="partyResultsHeading"><h4>Detailed Results .... </h4></div>
	</td>
</tr>
 <tr>
	  <td id="yearOnePanel">
		  <div id="panelForYearOne" class="yui-skin-sam">
				<div id="firstpanel" class="panelOuterDiv"></div>
			</div>
	  </td>	   
	  <td id="yearTwoPanel">
			<div id="panelForYearTwo" class="yui-skin-sam">	
				<div id="secondpanel" class="panelOuterDiv"></div>
			</div>
	  </td>
 </tr>
 <tr>
    <td align="left">
    <div id="displayLabelDiv" class="labelDisplay"></div>
	</td>
 </tr>
</table> 
</div>

<div id="partyElectionResultsDivMain" class="yui-skin-sam">	
			<div id="partyElectionResultsDiv"></div>
</div>
  

<script type="text/javascript">	
		buildDataForYearOne();
		buildDataForYearTwo();
		buildPartyElecResultsTable("firstpanel");
		overallResultsForYearOne();
		overallResultsForYearTwo();
		getDiffPercent();
</script>
</body>
</html>