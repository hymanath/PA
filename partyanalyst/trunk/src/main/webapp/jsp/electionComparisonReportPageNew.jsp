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

<c:if test="${allianceCheck == 'true'}"><title> ${electionComparisonReportVO.electionType} Election Comparison Report For ${selectedPartyName} Including Alliances</title></c:if>
<c:if test="${allianceCheck == 'false'}"><title>${electionComparisonReportVO.electionType} Election Comparison Report For ${selectedPartyName}</title></c:if>


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
		background-image:url(images/icons/statePage/header_body.png);
		color:highlight;
		font-family:verdana;
		font-size:16px;
		font-weight:bold;
		height:27px;
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
		color:#73787E;
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
		width:95%;
	}
	
	
</style>
<script type="text/javascript">	

var electionObject=	{
	    selectedParty:${electionComparisonReportVO.partyId},
		resultsForYearOne:[],
		resultsForYearTwo:[],
		yearOne:${electionComparisonReportVO.yearOne},
		yearTwo:${electionComparisonReportVO.yearTwo},
		elecIdYearOne:${electionComparisonReportVO.elecIdYearOne},
		elecIdYearTwo:${electionComparisonReportVO.elecIdYearTwo}
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
			arr.push(obj);
			
		}
		else
		{
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
			arr.push(obj);
		}
	}
	
	if(electionObject.resultsForYearTwo.length>0)
	{
		for(var i in electionObject.resultsForYearTwo)
		{
			var local = electionObject.resultsForYearTwo[i];
			
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
		label : "District",
		sortable : true
	}, 
	{
		label:""+electionObject.yearOne,
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "totalConstiOne",
						label : "*TC",
						sortable : true
					},
					{
						key : "constiParticipatedOne",
						label : "*PC",
						sortable : true
					},
					{
						key : "seatsWonOne",
						label : "Won",
						sortable : true
					},
					{
						key : "votesPercentOne",
						label : "*PC%",
						sortable : true
					},
					{
						key : "totalPercentageOne",
						label : "*TC %",
						sortable : true
					}				
				]
	},
	{
		label:"Diff %",
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "seatsDiffOne",
						label : "SeatsDiff",
						sortable : true
					},
					{
						key : "votesDiffOne",
						label : "Votes % Diff",
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
						label : "*TC",
						sortable : true
					},
					{
						key : "constiParticipatedTwo",
						label : "*PC",
						sortable : true
					},
					{
						key : "seatsWonTwo",
						label : "Won",
						sortable : true
					},
					{
						key : "votesPercentTwo",
						label : "*PC %",
						sortable : true
					},
					{
						key : "totalPercentageTwo",
						label : "*TC %",
						sortable : true
					}				
				]
	}
	];
	
	/*var myRowFormatter = function(elTr, oRecord) { 
				
				var seatsDiff = oRecord.getData('seatsDiffOne');
				var votesDiff = oRecord.getData('votesDiffOne');
				
				if(seatsDiff > 0)
					seatsDiff.style.color = 'red';
				else 
					seatsDiff.style.color = 'green';

				if(votesDiff > 0)
					votesDiff.style.color = 'red';
				else 
					votesDiff.style.color = 'green';

				return true; 
			};*/
    myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 

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
    str+='<td style="color:red;font-size:15px;width:25%;text-align:left;"> '+seatesDiff+' Seats </td>';

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



function getPartyPositions(partyId,id,rankPos)
{

	var imgElmt = document.getElementById("imageYearOneId");
    imgElmt.style.display = 'block';
	
  var elecId;
  if(id == "overallResultsYearOne")
  elecId = electionObject.elecIdYearOne;
  else if(id == "overallResultsYearTwo")
  elecId = electionObject.elecIdYearTwo;
	
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
	  var party='${electionComparisonReportVO.partyId}';
	  var hasAllianc='${electionComparisonReportVO.hasAlliances}';
	 
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

function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
{	
   var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();
}

//wkg table
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
						key : "partyName",
						label : "Party",
						sortable : true
					},
					{
						key : "rank",
						label : "Rank",
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
						key : "viewResultsOne",
						label : "Results",
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
						key : "secndCandPartyName",
						label : "Party",
						sortable : true
					},
					{
						key : "secndCandRank",
						label : "Rank",
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
					},
					{
						key : "viewResultsTwo",
						label : "Results",
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
	
	/*var str='';
	str+='<div id="comparedResults_main" class="comparedResultsOuter">';
	//str+='<div id="comparedResults_head" class="resultsHeadClass">';
	//str+='Compared Results';
	//str+='<span style="float:right;"><a href="javascript:{}" class="yuiCloseImg" onclick="hideComparedResultsDiv()"> </a></span>';
	//str+='</div>';
	*/

	//elmt.innerHTML = str;

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
<center>
<table cellspacing="0" cellpadding="0" border="0" style="margin-top:30px;">
	<tbody><tr>
		<td valign="top"><img border="none" src="images/icons/statePage/header_left.png"></td>
		<c:if test="${allianceCheck == 'true'}"><td valign="top"><div id="mainHeadingDiv">${electionComparisonReportVO.electionType} Elections Comparison Report For ${selectedPartyName} Including Alliances</div></td></c:if>
		<c:if test="${allianceCheck == 'false'}"><td valign="top"><div id="mainHeadingDiv">${electionComparisonReportVO.electionType} Elections Comparison Report For ${selectedPartyName}</div></td></c:if>
		<td valign="top"><img border="none" src="images/icons/statePage/header_right.png"></td>
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
		<div id="cand_elec_div_panel">
	</div>
</div>

<div id="imageYearOneId" align="center" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/barloader.gif" /></img>
</div>

<table style="width: 95%;">
<tr>
	<td id="tdThr" align="left" width="70%" colspan="2">
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
	 <td align="left"><div id="commentsDiv">  *P -- Party Participated  |   *S --Total State  |   *NP --Not Participated</div></td>
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
	  <td id="twoYearPanel" colspan="2">
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
		
		//buildDataForYearOne();
		//buildDataForYearTwo();
		
		//buildPartyElecResultsTable("firstpanel");
		overallResultsForYearOne();
		overallResultsForYearTwo();
		getDiffPercent();
		
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