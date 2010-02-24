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
	
	
</style>
<script type="text/javascript">	
var electionObject=	{
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
	   var lyear='';
	   lyear="Party Results In ";
	   lyear+='';
	   lyear+=year;

	   var str='';
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

	   var footer = '';
	   footer+='<table>';
       footer+='<tr>';
	   footer+='<td><input type="button" name="'+id+'_compbutton" value="Compared Results" onclick="getComparedResults(\''+id+'\')"/></td>';
	   footer+='</tr>';
	   footer+='</table>';
	   myPanel = new YAHOO.widget.Panel(id, { 
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
       myPanel.render();

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
   var arr = new Array();
   if(flag1 == true)
   {
	districtId=selectdPanelfirst;
    arr=electionObject.resultsForYearOne;
   }
   else if(flag2 == true){
   districtId=selectdPanelsecond;
   arr=electionObject.resultsForYearTwo;
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
		   str+='<td>'+elecResults[k].constituencyName+'</td>';
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
   var str='';
   str+='<table class="partyElecResultTable" style="width:100%;">';
   str+='<tr>';
   str+='<th>Party</th>';
   str+='<th>*PC</th>';
   str+='<th>1Pos</th>';
   str+='<th>2Pos</th>';
   str+='<th>3Pos</th>';
   str+='<th>4Pos</th>';
   str+='<th>Votes%</th>';
   str+='</tr>';
   <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearOne}">
   str+='<tr>';
   str+='<td align="center">'+"${partyPositions.partyName}"+'</td>';
   str+='<td align="center">'+"${partyPositions.totalConstiParticipated}"+'</td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'1\')">'+"${partyPositions.totalSeatsWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'2\')">'+"${partyPositions.secondPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'3\')">'+"${partyPositions.thirdPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearOne\',\'4\')">'+"${partyPositions.fourthPosWon}"+'</a></td>';
   str+='<td align="center">'+"${partyPositions.votesPercentage}"+'</td>';
   str+='</tr>';
   </c:forEach>
   str+='</table>';

   myPanel = new YAHOO.widget.Panel("overallResultsYearOnePanel", {
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
   myPanel.render();
}


function overallResultsForYearTwo()
{
   var str='';
   str+='<table class="partyElecResultTable" style="width:100%;">';
   str+='<tr>';
   str+='<th>Party</th>';
   str+='<th>*PC</th>';
   str+='<th>1Pos</th>';
   str+='<th>2Pos</th>';
   str+='<th>3Pos</th>';
   str+='<th>4Pos</th>';
   str+='<th>Votes%</th>';
   str+='</tr>';
   <c:forEach var="partyPositions" items="${electionComparisonReportVO.positionsForYearTwo}">
   str+='<tr>';
   str+='<td align="center">'+"${partyPositions.partyName}"+'</td>';
   str+='<td align="center">'+"${partyPositions.totalConstiParticipated}"+'</td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'1\')">'+"${partyPositions.totalSeatsWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'2\')">'+"${partyPositions.secondPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'3\')">'+"${partyPositions.thirdPosWon}"+'</a></td>';
   str+='<td align="center"><a href="#" onclick="getPartyPositions('+${partyPositions.partyId}+',\'overallResultsYearTwo\',\'4\')">'+"${partyPositions.fourthPosWon}"+'</a></td>';
   str+='<td align="center">'+"${partyPositions.votesPercentage}"+'</td>';
   str+='</tr>';
   </c:forEach>
   str+='</table>';

  
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
   myPanel.render();
}

function getPartyPositions(partyId,id,rankPos)
{
	
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

	//var divElmtHead = document.getElementById("labelHead");	
	rank = jsObj.rank;
	var party = jsObj.party;
	if(rank==-1)
		rank = 'N';
	/*divElmtHead.innerHTML=" "+'${stateData.party}'+" In position : "+rank+" And Its Opposition Party Details";*/

	divElmt.style.display = 'block';

	var str='';
	str+='<table id="partyPositionTable"  class="" border="0">';	
	for(var i in data)
	{		
		str+='<tr>';
		str+='<td>'+data[i].constituencyName+'</td>';
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

function buildPartyPositionDataTable(info,rank)
{
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
function displayComparedResults(jsObj,data)
{   	
	
	var str='';
	
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
	str+='<td>';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> NOT CONSIDERED - '+data.yearOne+' </legend>';
	str+='<div id="notConsYearOneDiv">';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='<td>';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> NOT CONSIDERED - '+data.yearTwo+' </legend>';
	str+='<div id="notConsYearTwoDiv">';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='</tr></table>';
	str+='</div>';
	str+='</div>';

	candidateElectionResultPanel = new YAHOO.widget.Panel("comparedResultsPanel", 
				{ 
					width:"1000px",
					fixedcenter : false, 
					visible : true,  
					constraintoviewport : true,
					x:50,
					y:300,
					iframe :true,
					modal :true,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	

	candidateElectionResultPanel.render();
	candidateElectionResultPanel.setHeader('Compared Results');
	candidateElectionResultPanel.setBody(str);

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
<body >
<div id="mainHeadingDiv"> Election Comparison Report </div>
<br/><br/>
<div>
<div id = "partyPositions" class="yui-skin-sam">
    <div id = "partyPositionsBody"></div>
	
</div>
<table>
<tr>
     <td id="tdOne">
	     <div id="overallResultsYearOneDiv" class="yui-skin-sam">
		     <div id="overallResultsYearOnePanel"></div>
		 </div>
	 </td>
	  <td rowspan="2">
	     <div id="mainGraphDiv" class="yui-skin-sam" style="margin-left:20px">
		     <div id="graphDiv">
			  <IMG id="chartImg" SRC="charts/<%=request.getAttribute("barChartName")%>" WIDTH="350" HEIGHT="300">
			 </div>
		 </div>
	 </td>
</tr>
<tr>
	<td id="tdTwo">
	     <div id="overallResultsYearTwoDiv" class="yui-skin-sam" style="margin-top:10px">
		     <div id="overallResultsYearTwoPanel"></div>
		 </div>
	 </td>
</tr>
<tr>
     <td align="left"><div id="commentsDiv">  *PC -- Participated Constituencies |   *TC --Total Constituencies </div></td>
	
</tr>
<tr>
    <td colspan="2">
		<div id="partyResultsHeading"><h4>Detailed Results .... </h4></div>
	</td>
</tr>
 <tr>
	  <td id="yearOnePanel">
		  <div id="panelForYearOne" class="yui-skin-sam">
				<div id="firstpanel"></div>
			</div>
	  </td>	   
	  <td id="yearTwoPanel">
			<div id="panelForYearTwo" class="yui-skin-sam">	
				<div id="secondpanel"></div>
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
  
 <div id="comparedResultsDiv" class="yui-skin-sam">
	<div id="comparedResultsPanel"></div>
 </div>
<script type="text/javascript">	
		buildDataForYearOne();
		buildDataForYearTwo();
		buildPartyElecResultsTable("firstpanel");
		overallResultsForYearOne();
		overallResultsForYearTwo();
</script>
</body>
</html>