<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionsComparisonVO " %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionComparisonResultVO" %>
<%@page import="com.itgrids.partyanalyst.dto.PartyElectionResultsVO" %>
<%@page import="com.itgrids.partyanalyst.dto.ElectionComparisonReportVO" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Comparision Report</title>
<c:if test="${allianceCheck == 'true'}"><title> ${electionComparisonReportVO.electionType} Elections Comparison Report For ${selectedPartyName} Including Alliances</title></c:if>
<c:if test="${allianceCheck == 'false'}"><title>${electionComparisonReportVO.electionType} Elections Comparison Report For ${selectedPartyName}</title></c:if>


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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	
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
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

	
	<link type="text/css" rel="stylesheet" href="styles/indexPage/indexPage.css">
	<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link  rel="stylesheet" type="text/css" href="styles/homePage/homePage.css"/>
		
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

<style type="text/css">

	
	
	.yui-skin-sam .yui-dt-liner 
	{
		margin:0 0 0 0px;
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
	border-collapse:collapse;
	}

	.partyElecResultTable th
	{
		padding:4px;
	}

	.partyElecResultTable td
	{
		padding:4px;
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
		background-image:url(images/icons/electionResultsAnalysisReport/mid.png);
		color:highlight;
		font-family:verdana;
		font-size:16px;
		font-weight:bold;
		height:20px;
		padding:5px;
		color: #000000;
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
		width:900px;		
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
	#description 
	{
		line-height:1.5em;
		padding:10px;
		text-align:justify;
		word-spacing:2px;
		width:90%;
		color:#192535;
		font-size:12px;
	}

	#twoYearpanel_body table
	{
		width:100%;
	}
	
	#electionComparisonGraphCarousel .yui-carousel-element li
	{
		height:360px;
		width:880px;
	}

	#electionComparisonGraphCarousel .yui-carousel-item-selected 
	{
		border:1px solid #CCCCCC;
	}

	.graphHeader
	{
		color:#194175;
		font-size:14px;
		font-weight:bold;
		padding:15px;
		text-align:center;
	}
	#panelFortwoYear
	{
		width:100%;
	}
	
	#ecrLayout_main
	{
		width:94%;
	}
	
	#ecrLayout_main fieldset
	{
		border:0 none;
		margin:0;
		padding:0;
	}

	#ecrLayout_main fieldset legend
	{
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:13px;
		padding:6px;
	}

	#diffPercentage_main_outer
	{
		background-color:#E4EDF0;
		padding:5px;
	}

	#diffPercentage_main
	{
		position:relative;
	}

	.yearsResults_body
	{
		border-bottom:1px solid #E0E0D6;
		border-left:1px solid #E0E0D6;
		border-right:1px solid #E0E0D6;
	}

</style>
<script type="text/javascript">	
var labelResources = { <%		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String firstPos = rb.getString("firstPos");			
		String secondPos = rb.getString("secondPos");
		String thirdPos = rb.getString("thirdPos");
		String fourthPos  = rb.getString("fourthPos");
		String nthPos  = rb.getString("nthPos");
		String pc = rb.getString("pc");
		String participatedConsts = rb.getString("participatedConsts");
		String party  = rb.getString("party");		
		String tc = rb.getString("tc");
		String tcDef  = rb.getString("tcDef");
		String pp = rb.getString("pp");
		String ppDef = rb.getString("ppDef");
		String pnp = rb.getString("pnp");
		String pnpDef = rb.getString("pnpDef");
		String stateWise = rb.getString("stateWise");
		String stateWiseDef = rb.getString("stateWiseDef");
		String dist = rb.getString("dist");
		String constituency = rb.getString("constituency");
		String candidate  = rb.getString("candidate");
		String votesPercentage = rb.getString("votesPercentage");
		String seatsWon = rb.getString("seatsWon");
		String votes = rb.getString("votes");
		String rank = rb.getString("rank");
		String electionType = rb.getString("electionType");
		String electionYear = rb.getString("electionYear");
		String votesEarned = rb.getString("votesEarned");
		String state = rb.getString("state");
		String results = rb.getString("results");
		String electionRes = rb.getString("electionRes");
		String oppPartyRes = rb.getString("oppPartyRes");
		
		ResourceBundle ecrRb = ResourceBundle.getBundle("ecr_Labels");
		String diff = ecrRb.getString("diff");
		String diffWonSeats = ecrRb.getString("diffWonSeats");
		String diffVotesPercent = ecrRb.getString("diffVotesPercent");
		String resultsHead = ecrRb.getString("resultsHead");
		String overAll = ecrRb.getString("overAll");
		String votesPcntInc = ecrRb.getString("votesPcntInc");
		String votesPcntDec = ecrRb.getString("votesPcntDec");
		String notConsidered = ecrRb.getString("notConsidered");
		String won = ecrRb.getString("won");
		String seatsDiff = ecrRb.getString("seatsDiff");
		String electionProf = ecrRb.getString("electionProf");
		%> }
var electionType = '${electionComparisonReportVO.electionType}';
var selectedElectionYear;
var electionObject=	{
	    selectedParty:'${electionComparisonReportVO.partyId}',
		resultsForYearOne:[],
		resultsForYearTwo:[],
		yearOne:'${electionComparisonReportVO.yearOne}',
		yearTwo:'${electionComparisonReportVO.yearTwo}',
		elecIdYearOne:'${electionComparisonReportVO.elecIdYearOne}',
		elecIdYearTwo:'${electionComparisonReportVO.elecIdYearTwo}'
};

function buildDataForTwoElectionYears()
{		
	//Data For Election Year One...	
	
	
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
						   seatsDiff:'${electionResults.seatsWonDiff}',
						   votesDiff:'${electionResults.votesPercentDiff}',
						   totalPercentage:'${electionResults.totalPercentage}',
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

		//Data For Election Year Two...

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
							   seatsDiff:'${electionResults.seatsWonDiff}',
							   votesDiff:'${electionResults.votesPercentDiff}',
							   totalPercentage:'${electionResults.totalPercentage}',
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
			electionObject.resultsForYearTwo.push(distObjYearTwo);
		 </c:forEach>
	
 
 
	buildElectionResultsComparePanel();
}


function districtPresentStatus(district,arr)
{
	for(var i in arr)
	{
		var localObj = arr[i];
		if(district == localObj.districtName)
		{
			electionObject.resultsForYearTwo.splice(i,1); 
			return localObj;
		}
	}

}

function buildElectionResultsComparePanel()
{	
	var elmtHead = document.getElementById("twoYearpanel_head");
	var elmtHeadCompare = document.getElementById("twoYearpanel_head_compare");	
	var elmtBody = document.getElementById("twoYearpanel_body");
	var elmtFooter = document.getElementById("twoYearpanel_footer");

	var id = "comparedResults";

	if(!elmtHead || !elmtBody || !elmtFooter || !elmtHeadCompare)
		return;

	var arr = new Array();
	

	for(var i in electionObject.resultsForYearOne)
	{
		var local = electionObject.resultsForYearOne[i];
		var secondLocal = districtPresentStatus(local.districtName,electionObject.resultsForYearTwo);
		if(secondLocal)
		{
			if(i==0){
				var obj = {
						inputRadio:'<input type="radio" checked=checked name="'+id+'_radio" value="'+local.districtId+'"/>',
						districtId:local.districtId,
						districtName:local.districtName,

						totalConstiOne:local.totalConsti,
						constiParticipatedOne:local.constiParticipated,
						seatsWonOne:local.seatsWon,
						votesPercentOne:local.votesPercent,
						seatsDiffOne:local.seatsDiff,
						votesDiffOne:local.votesDiff,
						totalPercentageOne:local.totalPercentage,

						totalConstiTwo:secondLocal.totalConsti,
						constiParticipatedTwo:secondLocal.constiParticipated,
						seatsWonTwo:secondLocal.seatsWon,
						votesPercentTwo:secondLocal.votesPercent,
						seatsDiffTwo:secondLocal.votesPercent,
						votesDiffTwo:secondLocal.votesDiff,
						totalPercentageTwo:secondLocal.totalPercentage
					  };
			}else{
				var obj = {
						inputRadio:'<input type="radio" name="'+id+'_radio" value="'+local.districtId+'"/>',
						districtId:local.districtId,
						districtName:local.districtName,

						totalConstiOne:local.totalConsti,
						constiParticipatedOne:local.constiParticipated,
						seatsWonOne:local.seatsWon,
						votesPercentOne:local.votesPercent,
						seatsDiffOne:local.seatsDiff,
						votesDiffOne:local.votesDiff,
						totalPercentageOne:local.totalPercentage,

						totalConstiTwo:secondLocal.totalConsti,
						constiParticipatedTwo:secondLocal.constiParticipated,
						seatsWonTwo:secondLocal.seatsWon,
						votesPercentTwo:secondLocal.votesPercent,
						seatsDiffTwo:secondLocal.votesPercent,
						votesDiffTwo:secondLocal.votesDiff,
						totalPercentageTwo:secondLocal.totalPercentage
					  };
			}		
			arr.push(obj);
			
		}
		else
		{
			if(i==0){
				var obj = {
						inputRadio:'<input type="radio" checked = checked name="'+id+'_radio" value="'+local.districtId+'"/>',
						districtId:local.districtId,
						districtName:local.districtName,

						totalConstiOne:local.totalConsti,
						constiParticipatedOne:local.constiParticipated,
						seatsWonOne:local.seatsWon,
						votesPercentOne:local.votesPercent,
						seatsDiffOne:'0',
						votesDiffOne:'0',
						totalPercentageOne:local.totalPercentage,

						totalConstiTwo:"*NP",
						constiParticipatedTwo:"*NP",
						seatsWonTwo:"*NP",
						votesPercentTwo:"*NP",
						seatsDiffTwo:"0",
						votesDiffTwo:"0",
						totalPercentageTwo:"0"
					  };
			}else{
				var obj = {
						inputRadio:'<input type="radio" name="'+id+'_radio" value="'+local.districtId+'"/>',
						districtId:local.districtId,
						districtName:local.districtName,

						totalConstiOne:local.totalConsti,
						constiParticipatedOne:local.constiParticipated,
						seatsWonOne:local.seatsWon,
						votesPercentOne:local.votesPercent,
						seatsDiffOne:'0',
						votesDiffOne:'0',
						totalPercentageOne:local.totalPercentage,

						totalConstiTwo:"*NP",
						constiParticipatedTwo:"*NP",
						seatsWonTwo:"*NP",
						votesPercentTwo:"*NP",
						seatsDiffTwo:"0",
						votesDiffTwo:"0",
						totalPercentageTwo:"0"
					  };				
			}
			arr.push(obj);
		}
	}
	
	if(electionObject.resultsForYearTwo.length>0)
	{
		for(var i in electionObject.resultsForYearTwo)
		{
			var local = electionObject.resultsForYearTwo[i];
			if(i==0){
				var obj = {
						inputRadio:'<input type="radio" checked=checked name="'+id+'_radio" value="'+local.districtId+'"/>',
						districtId:local.districtId,
						districtName:local.districtName,

						totalConstiOne:"*NP",
						constiParticipatedOne:"*NP",
						seatsWonOne:"*NP",
						votesPercentOne:"*NP",
						seatsDiffOne:"0",
						votesDiffOne:"0",

						totalConstiTwo:local.totalConsti,
						constiParticipatedTwo:local.constiParticipated,
						seatsWonTwo:local.seatsWon,
						votesPercentTwo:local.votesPercent,
						seatsDiffTwo:"0",
						votesDiffTwo:"0",
						totalPercentageTwo:local.totalPercentage
					  };
		    }else{
		    	var obj = {
						inputRadio:'<input type="radio" name="'+id+'_radio" value="'+local.districtId+'"/>',
						districtId:local.districtId,
						districtName:local.districtName,

						totalConstiOne:"*NP",
						constiParticipatedOne:"*NP",
						seatsWonOne:"*NP",
						votesPercentOne:"*NP",
						seatsDiffOne:"0",
						votesDiffOne:"0",

						totalConstiTwo:local.totalConsti,
						constiParticipatedTwo:local.constiParticipated,
						seatsWonTwo:local.seatsWon,
						votesPercentTwo:local.votesPercent,
						seatsDiffTwo:"0",
						votesDiffTwo:"0",
						totalPercentageTwo:local.totalPercentage
					  };					
			}
			arr.push(obj);
		}
	}


	
	  var lyear='';

	  if(electionObject.resultsForYearOne > electionObject.resultsForYearTwo)
		  lyear="Party Results For "+electionObject.yearOne+" - "+electionObject.yearTwo;	 
	  elmtHead.innerHTML = lyear
		

	   var fStr = '';
	   fStr+='<div id="'+id+'_electionResults_footer" class="resultFooterClass">';
	   fStr+='<table width="100%">';
       fStr+='<tr>';
	   fStr+='<td align="center" colspan="2"><input type="button" name="'+id+'_compbutton" value="Compare Results" onclick="getComparedResults(\''+id+'\')"/></td>';
	   fStr+='</tr>';
	   fStr+='</table>';
	   fStr+='</div>';
	  
	   elmtFooter.innerHTML = fStr;
	   elmtHeadCompare.innerHTML = fStr;


	    buildTwoYearPanelDataTable(arr,"twoYearpanel_body");

}

function buildTwoYearPanelDataTable(arr,divId)
{
	var resultsDataSource = new YAHOO.util.DataSource(arr);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [
		{
			key : "inputRadio"
		}, 
		{
			key : "districtName"
		},

		{
			key : "totalConstiOne",parser:"number"
		}, 
		{
			key : "constiParticipatedOne",parser:"number"
		}, 
		{
			key : "seatsWonOne",parser:"number"
		},
		{
			key : "votesPercentOne",parser:"number"
		},		
		{
			key : "totalPercentageOne",parser:"number"
		},		 
		
		{
			key : "seatsDiffOne",parser:"number"
		},
		{
			key : "votesDiffOne",parser:"number"
		},

		{
			key : "totalConstiTwo",parser:"number"
		},
		{
			key : "constiParticipatedTwo",parser:"number"
		},
		{
			key : "seatsWonTwo",parser:"number"
		},
		{
			key : "votesPercentTwo" ,parser:"number"
		},
		{
			key : "totalPercentageTwo" ,parser:"number"
		}]
	};

	var resultsColumnDefs = [ 
	{
		key : "inputRadio",
		label : "",
		sortable : false
	}, 
	{
		key : "districtName",
		label : "<%=dist%>",
		sortable : true
	}, 
	{
		label:""+electionObject.yearOne,
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "totalConstiOne",
						label : "<%=tc%>",
						sortable : true
					},
					{
						key : "constiParticipatedOne",
						label : "<%=pc%>",
						sortable : true
					},
					{
						key : "seatsWonOne",
						label : "<%=won%>",
						sortable : true
					},
					{
						key : "votesPercentOne",
						label : "<%=pc%>%",
						sortable : true
					},
					{
						key : "totalPercentageOne",
						label : "<%=tc%>%",
						sortable : true
					}				
				]
	},
	{
		label:"<%=diff%>",
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "seatsDiffOne",
						label : "<%=seatsDiff%>",
						sortable : true
					},
					{
						key : "votesDiffOne",
						label : "<%=votesPercentage%>Diff",
						sortable : true
					}
				]
	},
	{
		label:""+electionObject.yearTwo,
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "totalConstiTwo",
						label : "<%=tc%>",
						sortable : true
					},
					{
						key : "constiParticipatedTwo",
						label : "<%=pc%>",
						sortable : true
					},
					{
						key : "seatsWonTwo",
						label : "<%=won%>",
						sortable : true
					},
					{
						key : "votesPercentTwo",
						label : "<%=pc%>%",
						sortable : true
					},
					{
						key : "totalPercentageTwo",
						label : "<%=tc%>%",
						sortable : true
					}				
				]
	}
	];

    myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 

}



function overallResultsForYearOne()
{
	var elmt = document.getElementById("overallResultsYearOnePanel");	
	if(!elmt)
		return;

	var totalSeatsWonYearOne =0;
 <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearOne}">
		totalSeatsWonYearOne += ${partyPositions.totalSeatsWon} 
	 </c:forEach>

   var str='';
   str+='<div id="yearOneResults_head">';
	str+='<table  border="0" cellpadding="0" cellspacing="0" style="width:100%;">';
	str+='	<tr>';
	str+='		<td width="10px"><img src="images/icons/districtPage/header_left.gif"/></td>';
	str+='		<td><div class="districtPageRoundedHeaders_center" style="padding:11px;width:462px;"><span>Complete Results for '+electionType+' '+electionObject.yearOne+' Elections &nbsp;&nbsp;<span align="right">&nbsp;<font color="green">Total Seats Won : '+totalSeatsWonYearOne+'</font><span></span></div></td>';
	str+='	<td><img src="images/icons/districtPage/header_right.gif"/></td>';
	str+='	</tr>';
	
	str+='</table>';
    str += '</div>';
   str+='<div id="yearOneResults_body" class="yearsResults_body">';
   str+='<table class="partyElecResultTable" style="width:100%;">';
   str+='<tr>';
   str+='<th><%=party%></th>';
   str+='<th><%=pc%></th>';
   str+='<th><%=firstPos%></th>';
   str+='<th><%=secondPos%></th>';
   str+='<th><%=thirdPos%></th>';
   str+='<th><%=fourthPos%></th>';
   str+='<th>(<%=pp%>)%</th>';
   str+='<th>(<%=stateWise%>)%</th>';
   str+='</tr>';
   <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearOne}">
   str+='<tr>';
   if('${partyPositions.partyId}' != electionObject.selectedParty)
   str+='<td align="center">'+"${partyPositions.partyName}"+'</td>';
   if('${partyPositions.partyId}' == electionObject.selectedParty)
   str+='<td align="center" style="color:red;">'+"${partyPositions.partyName}"+'</td>';
   str+='<td align="center">'+"${partyPositions.totalConstiParticipated}"+'</td>';


   if("${partyPositions.totalSeatsWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'1\')">'+"${partyPositions.totalSeatsWon}"+'</a></td>';
   }else{
	   str+='<td align="center">'+"${partyPositions.totalSeatsWon}"+'</td>'; 	   	
   }
   if("${partyPositions.secondPosWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'2\')">'+"${partyPositions.secondPosWon}"+'</a></td>';
   }else{
	   str+='<td align="center">'+"${partyPositions.secondPosWon}"+'</td>';	   
   }

   
   if("${partyPositions.thirdPosWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'3\')">'+"${partyPositions.thirdPosWon}"+'</a></td>';
   }else{
	   str+='<td align="center">'+"${partyPositions.thirdPosWon}"+'</td>';	   
   }
   
   if("${partyPositions.fourthPosWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'4\')">'+"${partyPositions.fourthPosWon}"+'</a></td>'; 
   }else{
	   str+='<td align="center">'+"${partyPositions.fourthPosWon}"+'</td>';	   
   }  
    str+='<td align="center">'+"${partyPositions.votesPercentage}"+'</td>';
   str+='<td align="center">'+"${partyPositions.completeVotesPercent}"+'</td>';
   str+='</tr>';
   </c:forEach>
   str+='</table>';
   str+='</div>';
   str+='</div>';	
	
   elmt.innerHTML = str; 
}


function overallResultsForYearTwo()
{
	var elmt = document.getElementById("overallResultsYearTwoPanel");	
	if(!elmt)
		return;
 var totalSeatsWonYearTwo =0;
<c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearTwo}">
		totalSeatsWonYearTwo += ${partyPositions.totalSeatsWon}</c:forEach>
	var str='';
	str+='<div id="yearTwoResults_head">';
	str+='<table  border="0" cellpadding="0" cellspacing="0" style="width:100%;">';
	str+='	<tr>';
	str+='		<td width="10px"><img src="images/icons/districtPage/header_left.gif"/></td>';
	str+='		<td><div class="districtPageRoundedHeaders_center" style="padding:11px;width:462px;"><span>Complete Results for '+electionType+' '+electionObject.yearTwo+' Elections <span align="right">&nbsp;&nbsp;<font color="green">Total Seats Won : '+totalSeatsWonYearTwo+'</span></span></div></td>';
	str+='	<td><img src="images/icons/districtPage/header_right.gif"/></td>';
	str+='	</tr>';
	str+='</table>';
   str += '</div>';
   str+='<div id="yearTwoResults_body" class="yearsResults_body">';
   str+='<table class="partyElecResultTable" style="width:100%;">';
   str+='<tr>';
   str+='<th><%=party%></th>';
   str+='<th><%=pc%></th>';
   str+='<th><%=firstPos%></th>';
   str+='<th><%=secondPos%></th>';
   str+='<th><%=thirdPos%></th>';
   str+='<th><%=fourthPos%></th>';
   str+='<th>(<%=pp%>) %</th>';
   str+='<th>(<%=stateWise%>) %</th>';
   str+='</tr>';
   <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearTwo}">
   str+='<tr>';
   if('${partyPositions.partyId}' != electionObject.selectedParty)
   str+='<td align="center">'+"${partyPositions.partyName}"+'</td>';
   if('${partyPositions.partyId}' == electionObject.selectedParty)
   str+='<td align="center" style="color:red;">'+"${partyPositions.partyName}"+'</td>';
   str+='<td align="center">'+"${partyPositions.totalConstiParticipated}"+'</td>';  
   if("${partyPositions.totalSeatsWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'1\')">'+"${partyPositions.totalSeatsWon}"+'</a></td>';
   }else{
	   str+='<td align="center">'+"${partyPositions.totalSeatsWon}"+'</td>'; 	   	
   }
   if("${partyPositions.secondPosWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'2\')">'+"${partyPositions.secondPosWon}"+'</a></td>';
   }else{
	   str+='<td align="center">'+"${partyPositions.secondPosWon}"+'</td>';	   
   }
   if("${partyPositions.thirdPosWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'3\')">'+"${partyPositions.thirdPosWon}"+'</a></td>';
   }else{
	   str+='<td align="center">'+"${partyPositions.thirdPosWon}"+'</td>';	   
   }
   if("${partyPositions.fourthPosWon}"!="0"){
	   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'4\')">'+"${partyPositions.fourthPosWon}"+'</a></td>'; 
   }else{
	   str+='<td align="center">'+"${partyPositions.fourthPosWon}"+'</td>';	   
   }  
   str+='<td align="center">'+"${partyPositions.votesPercentage}"+'</td>';
   str+='<td align="center">'+"${partyPositions.completeVotesPercent}"+'</td>';
   str+='</tr>';
   </c:forEach>
   str+='</table>';
   str+='</div>';
   str+='</div>';	

   elmt.innerHTML = str;

  
}

function getDiffPercent()
{
	var elmt = document.getElementById("diffPercentage_main");	
	if(!elmt)
		return;

    var diffPercent = '${electionComparisonReportVO.votesPercentDiff}';
	var seatesDiff =  '${electionComparisonReportVO.seatsDiff}';
	var str = '';
	str+='<div class="resultBodyClass" style="background-color:#FFFFFF;">';
	str+='<table style="width:100%">';
	
		
	str+='<tr>';
	str+='<th align="left" style="width: 27%;text-align:left;"> <%=diffWonSeats%>( '+electionObject.yearOne+'  --  '+electionObject.yearTwo+'  ) :   </th>';
	
	if(seatesDiff > 0)
	str+='<td style="color:green;font-size:15px;width:25%;text-align:left;"> + '+seatesDiff+' Seats </td>';
	if(seatesDiff < 0)
    str+='<td style="color:red;font-size:15px;width:25%;text-align:left;"> '+seatesDiff+' Seats </td>';
    if(seatesDiff == 0)
    str+='<td style="color:red;font-size:15px;width:25%;text-align:left;"> 0 Seats </td>';
    str += '</tr>';
	str+='<tr>';
	str+='<th align="left" style="width: 33%;text-align:left;" > <%=diffVotesPercent%> ( '+electionObject.yearOne+'  --  '+electionObject.yearTwo+'  ) :   </th>';
	if(diffPercent > 0)
	str+='<td style="color:green;font-size:15px;text-align:left;">'+diffPercent+' % increase</td>';
	if(diffPercent < 0)
    str+='<td style="color:red;font-size:15px;text-align:left;">'+diffPercent+' % decrease</td>';
    if(diffPercent == 0)
        str+='<td style="color:red;font-size:15px;text-align:left;">'+diffPercent+' % </td>';
    str+='</tr>';


    str+='</table>';
	str+='</div>';

	elmt.innerHTML = str;
	
}



function getPartyPositions(partyId,id,rankPos)
{
 	var imgElmt = document.getElementById("imageYearOneId");
    imgElmt.style.display = 'block';
	
  var elecId;
  if(id == "overallResultsYearOne"){
  	elecId = electionObject.elecIdYearOne;
  	selectedElectionYear = electionObject.yearOne;
  }
  else if(id == "overallResultsYearTwo"){
  	elecId = electionObject.elecIdYearTwo;
  	selectedElectionYear = electionObject.yearTwo;
  }
	
      var elecType='${electionComparisonReportVO.electionType}';
	  var state='${electionComparisonReportVO.stateId}';
	  var hasAllianc='${electionComparisonReportVO.hasAlliances}';
	  
	  var jsObj= 
	  {
		  electionId:elecId,
		  party:partyId,
		  rank:rankPos		  
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
								//alert( "Failed to load result" + o.status + " " + o.statusText);
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
		str+='<td><a href="javascript:{}" onclick="getConstituencyElecResults('+data[i].constituencyId+','+selectedElectionYear+')">'+data[i].constituencyName+'</a></td>';	
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

function getConstituencyElecResults(constiId,elecYear)
{
   var elecType = "${electionComparisonReportVO.electionType}";
   //var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();
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
	hStr += '<table width="100%"><tr><td align="left"><span style="color:#4B74C6"><b>Comparison Report For the Rank - '+rank+'</b></span></td>';
	hStr +='<td align="right"><span id="partyPositionsCloseSpan" onclick="closePartyPosition()"> X </span></td></tr></table>'
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
	
	var key1={key : "constituencyName"};
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
							label : "<%=constituency%>",
							sortable : true
						},
						{
							key : "candidateName",		
							label : "<%=candidate%>",
							sortable : true
						},
						{
							key : "votePercentage",
							parser:"number",
							label : "<%=votesPercentage%>",
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
										label : "<%=candidate%>",
										sortable : true
									},
									{
										key : "pName"+d,		
										label : "<%=party%>",
										sortable : true
									},
									{
										key : "vPercentage"+d,
										parser:"number",
										label : "<%=votesPercentage%>",
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

   var stateOrdistrictId;
   var elements = document.getElementsByTagName('input'); 
	  for(var i=0;i<elements.length;i++)
	  {
		if(elements[i].type=="radio" && elements[i].name=="comparedResults_radio" && elements[i].checked==true)
		{
			stateOrdistrictId = elements[i].value;
		}       

	  }
	  var elecId1=electionObject.elecIdYearOne;
      var elecId2=electionObject.elecIdYearTwo;
      var elecYear1 = electionObject.yearOne;
      var elecYear2 = electionObject.yearTwo;
	  var party='${electionComparisonReportVO.partyId}';
	  var hasAllianc='${electionComparisonReportVO.hasAlliances}';
	  var urlStr = "<%=request.getContextPath()%>/ecrComparedResultsAction.action?elecIdOne="+elecId1+"&elecIdTwo="+elecId2+"&partyId="+party+"&hasAlliances="+hasAllianc+"&stateOrDistrictId="+stateOrdistrictId+"&electionType="+electionType+"&elecYear1="+elecYear1+"&elecYear2="+elecYear2;
		var browser1 = window.open(urlStr,"browser1","scrollbars=yes,height=600,width=1200,left=200,top=200");
		
		browser1.focus();
		 /*
	  var jsObj= 
	  {
		  electionIdOne:elecId1,
		  electionIdTwo:elecId2,
		  stateOrDistrictId:stateOrdistrictId,
		  partyId:party,
          hasAlliance:hasAllianc
	  }

	  var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	  callAjax(param,jsObj);
	  */
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
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
{	
  // var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();
}

function buildDataTable(divId,arr,yearOne,yearTwo)
{	

	for(var i in arr)
	{
		arr[i].viewResultsOne = '<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\''+arr[i].constituencyId+'\',\''+arr[i].electionType+'\',\''+electionObject.yearOne+'\')">View</a>';
		arr[i].viewResultsTwo = '<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\''+arr[i].constituencyId+'\',\''+arr[i].electionType+'\',\''+electionObject.yearTwo+'\')">View</a>';
	}
	

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
			key : "partyName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "votesPercent",parser:"number"
		}, {
			key : "rank",parser:"number"
		},{
			key : "viewResultsOne"
		},

		{
			key : "votesPercentDiff",parser:"number"
		} ,	{
			key : "secndCandName"
		} , {
			key : "secndCandPartyName"
		}, {
			key : "secndCandRank" ,parser:"number"
		},
		{
			key : "votesEarnedBySecnd",parser:"number"
		} , {
			key : "secndVotesPercent",parser:"number"
		},{
			key : "viewResultsTwo"
		} ]
	};

	var resultsColumnDefs = [ 
	{
		key : "constiName",
		label : "<%=constituency%>",
		sortable : true
	}, 
	{
		label:"Year - "+yearOne,
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "candName",
						label : "<%=candidate%>",
						sortable : true
					},
					{
						key : "partyName",
						label : "<%=party%>",
						sortable : true
					},
					{
						key : "rank",
						label : "<%=rank%>",
						sortable : true
					},
					{
						key : "votesEarned",
						label : "<%=votes%>",
						sortable : true
					},
					{
						key : "votesPercent",
						label : "%",
						sortable : true
					},
					{
						key : "viewResultsOne",
						label : "<%=results%>",
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
						label : "<%=diff%>",
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
						label : "<%=candidate%>",
						sortable : true
					},
					{
						key : "secndCandPartyName",
						label : "<%=party%>",
						sortable : true
					},
					{
						key : "secndCandRank",
						label : "<%=rank%>",
						sortable : true
					},
					{
						key : "votesEarnedBySecnd",
						label : "<%=votes%>",
						sortable : true
					},
					{
						key : "secndVotesPercent",
						label : "%",
						sortable : true
					},
					{
						key : "viewResultsTwo",
						label : "<%=results%>",
						sortable : true
					}									
				 ]
	}
	];

    myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 
}



function displayComparedResults(jsObj,data)
{   
	var elmt = document.getElementById("comparedResultsPanel");
	if(!elmt)
		return;
	
	var str='';
	str+='<div id="completeOneField">';	
	str+='<legend>Complete Results For '+electionType+' '+data.yearOne+' Elections'+data.positionsYearOne.totalSeatsWon+'</legend>';
	str+='<table width="100%">';
	str+='<tr>';
    str+='<td align="center"><%=party%></td>';
	str+='<td align="center" style="color:DodgerBlue;"><%=pc%></td>';
	str+='<td align="center"><%=seatsWon%></td>';
	str+='<td align="center"><%=secondPos%></td>';
	str+='<td align="center"><%=thirdPos%></td>';
	str+='<td align="center"><%=votesPercentage%></td>';
    str+='<td align="center"><%=overAll%></td>';
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
    str+='<td style="color:DodgerBlue;"><%=pc%> -- <%=participatedConsts%></td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';

	str+='<div id="completeTwoField">';
	str+='<legend>Complete Results For '+electionType+' '+data.yearTwo+' Elections'+data.positionsYearTwo.totalSeatsWon+'</legend>';
	str+='<table width="100%">';
	str+='<tr>';
    str+='<td align="center"><%=party%></td>';
	str+='<td align="center" style="color:DodgerBlue;"><%=pc%></td>';
	str+='<td align="center"><%=seatsWon%></td>';
	str+='<td align="center"><%=secondPos%></td>';
	str+='<td align="center"><%=thirdPos%></td>';
	str+='<td align="center"><%=votesPercentage%></td>';
    str+='<td align="center"><%=overAll%></td>';
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
    str+='<td style="color:DodgerBlue;"><%=pc%> -- <%=participatedConsts%></td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';

	str+='<fieldset id="electionProfileField">';
	str+='<legend><%=votesPcntInc%></legend>';	
	str+='<div id="votesPercentageIncDiv">';
	
	str+='</div>';	
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="electionResultsField">';
	str+='<legend><%=votesPcntDec%></legend>';
	str+='<div id="votesPercentageDecDiv">';	
	str+='</div>';	
	str+='</fieldset>';
	//--------

	str+='<table>'
	str+='<tr>';
	str+='<td style="vertical-align:top;">';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend><%=notConsidered%> - '+data.yearOne+' </legend>';
	str+='<div id="notConsYearOneDiv">';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='<td style="vertical-align:top;">';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> <%=notConsidered%> - '+data.yearTwo+' </legend>';
	str+='<div id="notConsYearTwoDiv">';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='</tr></table>';

	str+='</div>';
	var comparedElectionResultPanel = new YAHOO.widget.Panel("comparedResultsPanel", 
				{ 					
					fixedcenter : false, 
					visible : true,
					width:"950px",
					constraintoviewport : false,
					x:150,
					y:650,
					iframe :true,
					modal :false,
					visible:true,						
					draggable:false, 
					close:true
				} ); 
	
	
	comparedElectionResultPanel.setHeader('Compared Results');
	comparedElectionResultPanel.setBody(str);
	comparedElectionResultPanel.render();
	

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
		label : "<%=constituency%>",
		sortable : true
	},
	{
		key : "candidateName",
		label : "<%=candidate%>",
		sortable : true
	},
	{
		key : "partyName",
		label : "<%=party%>",
		sortable : true
	},
	{
		key : "votesEarned",
		label : "<%=votes%>",
		sortable : true
	},
	{
		key : "votesPercent",
		label : "<%=votesPercentage%>",
		sortable : true
	},
	{
		key : "rank",
		label : "<%=rank%>",
		sortable : true
	}]
	
	myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 
}

function createCoulmnChart()
{
	var years = new Array();
	years.push('${electionComparisonReportVO.yearOne}');
	years.push('${electionComparisonReportVO.yearTwo}');

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Year');
	data.addColumn('number', 'Seats Won');
	data.addColumn('number', '2nd Pos');
	data.addColumn('number', '3rd Pos');
	data.addColumn('number', '4th Pos');
	
	data.addRows(years.length);
	
	<c:if test="${allianceCheck == 'true'}">
		
		var totalSeatsWonYearOne = 0;
		var secondPosWonYearOne = 0;
		var thirdPosWonYearOne = 0;
		var fourthPosWonYearOne = 0;			

		<c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearOne}">
			totalSeatsWonYearOne += ${partyPositions.totalSeatsWon};
			secondPosWonYearOne += ${partyPositions.secondPosWon};
			thirdPosWonYearOne += ${partyPositions.thirdPosWon};
			fourthPosWonYearOne += ${partyPositions.fourthPosWon};
		</c:forEach>

		data.setValue(0, 0, years[0]);				
		data.setValue(0, 1, totalSeatsWonYearOne);
		data.setValue(0, 2, secondPosWonYearOne);
		data.setValue(0, 3, thirdPosWonYearOne);
		data.setValue(0, 4, fourthPosWonYearOne);

		var totalSeatsWonYearTwo = 0;
		var secondPosWonYearTwo = 0;
		var thirdPosWonYearTwo = 0;
		var fourthPosWonYearTwo = 0;
		
		<c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearTwo}">
			totalSeatsWonYearTwo += ${partyPositions.totalSeatsWon};
			secondPosWonYearTwo += ${partyPositions.secondPosWon};
			thirdPosWonYearTwo += ${partyPositions.thirdPosWon};
			fourthPosWonYearTwo += ${partyPositions.fourthPosWon};
		</c:forEach>

		data.setValue(1, 0,  years[1]);				
		data.setValue(1, 1, totalSeatsWonYearTwo);
		data.setValue(1, 2, secondPosWonYearTwo);
		data.setValue(1, 3, thirdPosWonYearTwo);
		data.setValue(1, 4, fourthPosWonYearTwo);
		 
	</c:if>

	<c:if test="${allianceCheck == 'false' || allianceCheck == ''}">
		 data.setValue(0, 0, years[0]);
		 <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearOne}">
			data.setValue(0, 1, ${partyPositions.totalSeatsWon});
			data.setValue(0, 2, ${partyPositions.secondPosWon});
			data.setValue(0, 3, ${partyPositions.thirdPosWon});
			data.setValue(0, 4, ${partyPositions.fourthPosWon});
		 </c:forEach>
			
		  data.setValue(1, 0, years[1]);
		 <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearTwo}">
			data.setValue(1, 1, ${partyPositions.totalSeatsWon});
			data.setValue(1, 2, ${partyPositions.secondPosWon});
			data.setValue(1, 3, ${partyPositions.thirdPosWon});
			data.setValue(1, 4, ${partyPositions.fourthPosWon});
		 </c:forEach>
	</c:if>

	var chart = new google.visualization.ColumnChart(document.getElementById('graphDiv'));
		chart.draw(data, {width: 320, height: 410,legend:'right',legendTextStyle:{fontSize:10}, title: 'Party Positions in ${electionComparisonReportVO.yearOne} and ${electionComparisonReportVO.yearTwo}',
				  hAxis: {title: 'Year', titleTextStyle: {color: 'red'}}
				 });

}

</script>
</head>
<body>
<center>
<table cellspacing="0" cellpadding="0" border="0" style="margin-top:30px;">
	<tbody><tr>
		<td valign="top"><img border="none" src="images/icons/electionResultsAnalysisReport/first.png"></td>
		<c:if test="${allianceCheck == 'true'}"><td valign="top"><div id="mainHeadingDiv">${electionComparisonReportVO.electionType} Elections Comparison Report For ${selectedPartyName} Including Alliances</div></td></c:if>
		<c:if test="${allianceCheck == 'false' || allianceCheck == ''}"><td valign="top"><div id="mainHeadingDiv">${electionComparisonReportVO.electionType} Elections Comparison Report For ${selectedPartyName}</div></td></c:if>
		<td valign="top"><img border="none" src="images/icons/electionResultsAnalysisReport/second.png"></td>
	</tr>
</tbody></table>

<br/><br/>
<div id="description"><font style="color: rgb(75, 116, 198);font-weight:bold;">Elections Comparison Report</font> gives a glance of compared election results for a party participated in any two elections in detailed view.This report gives an overview for a user to know whether the party has improved or lost its performance in selected present year when compared to selected previous year.
</div>
<div id = "partyPositions" class="yui-skin-sam">
	<div id = "partyPositionsHead"></div>
    <div id = "partyPositionsBody"></div>	
</div>
<div id="CandidatePageLayoutDiv">
	<div id="cand_elect_div" class="yui-skin-sam" style="position:absolute;">
		<div id="cand_elec_div_panel"></div>
	</div>
</div>

<div id="imageYearOneId" align="center" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/barloader.gif" /></img>
</div>

<!-- New UI Changes start -->
<div id="ecrLayout_main">
	<div id="ecr_ElectionAnalysis_data_div">
		<fieldset>			
				<div class="productFeatureHeader">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>                                    
						<td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
						<td width="98%">
							<div class="productFeatureHeaderBackground_center">
								<span class="headerLabelSpan">
									Election Results Analysis  
								</span>
							</div>
						</td>
						<td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
					  </tr>
					</table>
				</div>
				<div id="diffPercentage_main_outer">				
				<table width="100%">
					<tr>
						<td width="50%" valign="top">							
							<div id="constituencyCenterContentOuter" class="rounded" style="margin:0px;"> 						
									<div class="corner topLeft"></div>
									<div class="corner topRight"></div>
									<div class="corner bottomLeft"></div>
									<div class="corner bottomRight"></div> 

									<div id="diffPercentage_main"></div>
							</div>
							<div id="constituencyCenterContentOuter" class="rounded"> 						
									<div class="corner topLeft"></div>
									<div class="corner topRight"></div>
									<div class="corner bottomLeft"></div>
									<div class="corner bottomRight"></div> 
                                    
									<div id="overallResultsYearOneDiv" class="yui-skin-sam" style="position:relative">
										 <div id="overallResultsYearOnePanel" class="panelOuterDiv" style="border:0px none;"></div>
									</div>
									<div id="commentsDiv">  <%=pc%> -- <%=participatedConsts%> |  <%=tc%> -- <%=tcDef%></div>
									<div id="commentsDiv">  <%=pp%> -- <%=ppDef%>  |   <%=stateWise%> -- <%=stateWiseDef%>  |   <%=pnp%> -- <%=pnpDef%></div>
							</div>

							<div id="constituencyCenterContentOuter" class="rounded"> 						
									<div class="corner topLeft"></div>
									<div class="corner topRight"></div>
									<div class="corner bottomLeft"></div>
									<div class="corner bottomRight"></div> 
									 <div id="overallResultsYearTwoDiv" class="yui-skin-sam" style="position:relative;">
										 <div id="overallResultsYearTwoPanel" class="panelOuterDiv" style="border:0px none;"></div>
									 </div>
									 <div id="commentsDiv">  <%=pc%> -- <%=participatedConsts%> |  <%=tc%> -- <%=tcDef%></div>
									<div id="commentsDiv">  <%=pp%> -- <%=ppDef%>  |   <%=stateWise%> -- <%=stateWiseDef%>  |   <%=pnp%> -- <%=pnpDef%></div>
							</div>
							
					</td>
						<td width="50%" valign="top">
							<div id="constituencyCenterContentOuter" class="rounded" style="margin:0px;"> 						
									<div class="corner topLeft"></div>
									<div class="corner topRight"></div>
									<div class="corner bottomLeft"></div>
									<div class="corner bottomRight"></div> 

									<div id="mainGraphDiv" class="yui-skin-sam">
										 <div id="graphDiv">
										  <IMG id="chartImg" SRC="charts/<%=request.getAttribute("barChartName")%>" WIDTH="300" HEIGHT="480">
										 </div>
									</div>
							</div>
							
						</td>
					</tr>
				</table>
				</div>
		</fieldset>
	</div>
	<div></div>
</div>
<!-- New UI Changes end -->

<table style="width:70%;" border="0">
<tr>
	<td id="tdThr" align="left" width="70%" colspan="2">
	     <div id="diffPercentDiv" class="yui-skin-sam" style="margin-left:10px;">
		     <div id="diffPercentPanel" class="panelOuterDiv"></div>
		 </div>
	 </td>
</tr>

<tr>
	<td colspan="2">
		<div id="electionComparisonGraphCarousel" class="yui-skin-sam"> 
			<ul>
				<li>
					<div id="percentageChartDiv">	
						<c:if test="${electionComparisonReportVO.electionType == 'Parliament'}">
							<div id="percentageChartDiv_head" class="graphHeader">Percentage Gained By ${selectedPartyName} Party In All States For ${electionComparisonReportVO.yearOne} - ${electionComparisonReportVO.yearTwo} Parliament Elections</div>
						</c:if>
						<c:if test="${electionComparisonReportVO.electionType != 'Parliament'}">										
							<div id="percentageChartDiv_head" class="graphHeader">Percentage Gained By ${selectedPartyName} Party In All Districts For ${electionComparisonReportVO.yearOne} - ${electionComparisonReportVO.yearTwo} Assembly Elections</div>
						</c:if>	
						<div id="percentageChartDiv_img"><img src="charts/${electionComparisonReportVO.percentageChart}"/></div>
						
					</div>
				</li>
				<li>
					<div id="seatsWonChart" >
						<c:if test="${electionComparisonReportVO.electionType == 'Parliament'}">
							<div id="seatsWonChart_head" class="graphHeader">Seats Won By ${selectedPartyName} Party In All States For ${electionComparisonReportVO.yearOne} - ${electionComparisonReportVO.yearTwo} Parliament Elections</div>
						</c:if>
						<c:if test="${electionComparisonReportVO.electionType != 'Parliament'}">
							<div id="seatsWonChart_head" class="graphHeader">Seats Won By ${selectedPartyName} Party In All Districts For ${electionComparisonReportVO.yearOne} - ${electionComparisonReportVO.yearTwo} Assembly Elections</div>
						</c:if>
							
							<div id="seatsWonChart_img"><img src="charts/${electionComparisonReportVO.seatsWonChart}"/></div>
							
					</div>
				</li>
			</ul>		
		</div>
	</td>
</tr>

 <tr>
	  <td id="twoYearPanel" colspan="2" align="center">
		  <div id="panelFortwoYear" class="yui-skin-sam">
				<div id="twoYearpanel" class="panelOuterDiv">
					<div id="twoYearpanel_head" class="resultsHeadClass"></div>
					<div id="twoYearpanel_head_compare"></div>						
					<div id="twoYearpanel_body"></div>
					<div id="twoYearpanel_footer"></div>
				</div>
			</div>
	  </td>	   
</tr>
</table> 

<div class="yui-skin-sam">
	<div id="comparedResultsPanel"></div>
</div>

</div>

<script type="text/javascript">	
		overallResultsForYearOne();
		overallResultsForYearTwo();
		getDiffPercent();
		google.load("visualization", "1", {packages:["corechart"]});
		google.setOnLoadCallback(createCoulmnChart);

		
		var allianceCarousel = new YAHOO.widget.Carousel("electionComparisonGraphCarousel",
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 1,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

		allianceCarousel.render(); 
		allianceCarousel.show();


		buildDataForTwoElectionYears();
</script>
</center>
</body>
</html>