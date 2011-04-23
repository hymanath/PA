<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting Trendz</title>

<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>

<script type="text/javascript" src="../js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="../js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="../styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="../js/constituencyPage/constituencyPage.js"></script>
<link rel="stylesheet" type="text/css" href="../styles/constituencyPage/constituencyPage.css">	
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />

  <script>

	function callAjax(jsObj,url)
	{					
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);									
								
								if(jsObj.task == "getNextPrevCandidateTrendz")
								{
									buildCandidateVotingTrendzGraphData(jsObj,myResults);
								}										
								else if(jsObj.task == "getVotingTrendzForElectionYears")
								{									
									buildVotingTrendzData(myResults);
								}	
										
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

	
function showNextPreviousCandidateVotingTrendz(index,type)
{
	if(type == 'previous')
		candidateIndex-=1;
	else if(type == 'next')
		candidateIndex+=1;

	if(candidateIndex!=1)
		prevButtonElmt.disabled = false;
	else
		prevButtonElmt.disabled = true;

	if(candidateListSize>1 && candidateIndex!=candidateListSize)
		nextButtonElmt.disabled = false;
	else
		nextButtonElmt.disabled = true;

	for(var i =0;electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO.length;i++)
	{		
		var data = electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO[i];
		if(data.rank == candidateIndex)
		{
			var jsObj=	{
							partyId:data.partyId,
							partyName:data.partyName,
							partyLogo:data.partyLogo,
							partyFlag:data.partyFlag,
							candidateId:data.candidateId,
							candidateName:data.candidateName,							
							validVotes:data.validVotes,
							totalVotes:data.totalVotes,
							maleVotes:data.maleVotes,
							femaleVotes:data.femaleVotes,
							maleAndFemaleVotes:data.maleAndFemaleVotes,
							totalVotesPercent:data.totalVotesPercent,
							maleVotesPercent:data.maleVotesPercent,
							femaleVotesPercent:data.femaleVotesPercent,
							maleAndFemaleVotesPercent:data.maleAndFemaleVotesPercent,
							rank:data.rank,
							overallMaleVotesPercent : data.overallMaleVotesPercent,
							overallFemaleVotesPercent : data.overallFemaleVotesPercent,
							overallMaleOrFemaleVotesPercent : data.overallMaleOrFemaleVotesPercent,						
							status:data.status,
							maleVotesPercentInConstiVotes : data.maleVotesPercentInConstiVotes,
							femaleVotesPercentInConstiVotes :data.femaleVotesPercentInConstiVotes,
							maleOrFemaleVotesPercentInConstiVotes : data.maleOrFemaleVotesPercentInConstiVotes, 
							task:'getNextPrevCandidateTrendz'
						}

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getNextPrevCandidateVotingTrendz.action?"+rparam;
			
			callAjax(jsObj, url);			
			return;
		}
	}
}

function getVotingTrendzForElectionYears()
{
	var jsObj=	{					
					task:'getVotingTrendzForElectionYears'
				}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
}

function getVotingTrendzForyear()
{

	var elements = document.getElementsByTagName('input'); 
	var electionType;
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="electionType" && elements[i].checked==true)
			electionType = elements[i].value;
	}
	if(!electionType)
		return;
	
	var selectElmt = document.getElementById(electionType+"_YearSelect");
	if(!selectElmt)
		return;
	selectElmt.disabled = false;

	var year = selectElmt.options[selectElmt.selectedIndex].text;
	if(year == "Assembly" || year == "Parliament")
		return;

	var value = selectElmt.options[selectElmt.selectedIndex].value;	
	var electionId = value.substring(0,(value.indexOf('_')));
	var electionTypeId = value.substring((value.indexOf('_')+1),value.length);

	

	var jsObj = {
					constituencyId:electionTrendzReportVO.constituencyId,
					electionId:electionId,
					electionTypeId:electionTypeId,
					electionYear:year,
					task:'getVotingTrendzForElectionYears'
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
	
}
	
	var electionTrendzReportVO;
	var candidateIndex = 1,candidateListSize,prevButtonElmt,nextButtonElmt;

	function buildVotingTrendzLayout(divId,obj)
	{	
		var elmt = document.getElementById(divId);

		var str = '';	
		str+='	<div id="constituencyVotersInfoDiv_Body">';
		str+='	<table>';
		str+='		<tr>';
		str+='			<td><div id="constituencyVotersInfoDiv_Head" class="layoutHeadersClass"></div></td>';
		str+='			<td><div id="constituencyVotersInfoDiv_Navigation"></div></td>';
		str+='		</tr>';
		str+='		<tr>';
		str+='			<td colspan="2">';
		str+='				<div id="constituencyVotersInfoDiv_Body_votingTrendzGraph" class="layoutBodyClass yui-skin-sam"></div>';
		str+='			</td>';
		str+='		</tr>';
		str+='		<tr>';
		str+='			<td width="50%"><div id="constituencyVotersInfoDiv_Body_voters" class="layoutBodyClass yui-skin-sam"></div></td>';
		str+='          <td width="50%"><div class="commentsDiv"> ';
		str+='				* Total Male Voters In Constituency   : <font style="color:#FF0000;">'+obj.electionTrendzOverviewVO.maleVotersInConstituency+' 							</font> </div>';
		str+='              <div class="commentsDiv"> * Total Female Voters In Constituency : <font 																				style="color:#FF0000;">'+obj.electionTrendzOverviewVO.femaleVotersInConstituency+' </font> </div>';
		str+='              <div class="commentsDiv"> ';
		str+='				 * Known Classified Male Voters % In Total Male Voters :<font style="color:#FF0000;"> '+obj.electionTrendzOverviewVO.maleVoters+' </font><font style="color:#0000FF;"> ( '+obj.electionTrendzOverviewVO.maleVotersPercentInConsti+' % )</font> </div>';
		str+='              <div class="commentsDiv"> ';
		str+='				 * Known Classified Female Voters % In Total Female Voters   :<font     			   style="color:#FF0000;">'+obj.electionTrendzOverviewVO.femaleVoters+'</font><font style="color:#0000FF;"> ( '+obj.electionTrendzOverviewVO.femaleVotersPercentInConsti+' % )</font> </div>';
		str+='           </td>';
		str+='		</tr>';
		str+='		<tr>';
		str+='			<td colspan="2">';
		str+='				<div id="constituencyVotersInfoDiv_Body_candidateTrendzGraph" class="layoutBodyClass yui-skin-sam">';
		str+='					<div id="candidateTrendzGraph_head"></div>';
		str+='					<div id="candidateTrendzGraph_body">';
		str+='					<table>';
		str+='						<tr>';
		str+='							<td style="vertical-align:top;width:30%;"> <div id="candidateVotingGraph1" class="graphHeadingDivClass"></div></td>';
		str+='							<td style="vertical-align:center"> <div id="candidateTrendzGraphDataDiv" class="graphHeadingDivClass"></div> </td>';
		str+='							<td style="vertical-align:top;width:30%;"> <div id="candidateVotingGraph2" class="graphHeadingNewDivClass"></div></td>';
		str+='						</tr>';
		str+='					</table>';
		str+='				</div>';
		str+='				<div id="candidateTrendzGraph_footer"></div>';
		str+='				</div>';
		str+='			</td>';
		str+='		</tr>';
		str+='		<tr>';
		str+='			<td colspan="2">';
		str+='				<div id="constituencyVotersInfoDiv_Body_candidate" class="layoutBodyClass yui-skin-sam"></div>';
		str+='			</td>';
		str+='		</tr>';
		str+='	</table>';
		str+='	</div>';
		str+='<div id="constituencyVotersInfoDiv_Footer"></div>';

		if(elmt)
			elmt.innerHTML = str;
		
		buildVotingTrendzData(obj);
	}

function buildVotingTrendzData(obj)
{		
	electionTrendzReportVO.electionTrendzOverviewVO = obj.electionTrendzOverviewVO;

	buildConstituencyVotingTrendzHeader(obj);
	buildCenterConstituencyVotersInfoContent(obj.electionTrendzOverviewVO);	
	buildConstituencyVotingTrendzGraph(obj.electionTrendzOverviewVO);	
	buildCandidateVotingTrendzGraphData(obj.electionTrendzOverviewVO,"");
	candidateVotingTrendzDatatable(obj.electionTrendzOverviewVO);
}

function buildCandidateVotingTrendzGraphData(obj,results)
{	
	var candidateTrendzGraphelmt_head = document.getElementById('candidateTrendzGraph_head');
	var candidateTrendzGraphelmt_footer = document.getElementById('candidateTrendzGraph_footer');
	var candidateTrendzGraphelmt = document.getElementById('candidateTrendzGraphDataDiv');

	var data;
	var localObj = electionTrendzReportVO.electionTrendzOverviewVO;
	
	
	if(candidateTrendzGraphelmt_head && candidateTrendzGraphelmt_footer && candidateTrendzGraphelmt)
	{
		candidateTrendzGraphelmt_head.innerHTML = '';
		candidateTrendzGraphelmt_footer.innerHTML = '';
		candidateTrendzGraphelmt.innerHTML = '';
	}
	
	if(results)
		data = obj;
	else
		data = obj.wonCandidateResultTrendz;

	candidateListSize = localObj.partyElectionTrendzVO.length;

	var hStr = '';
	hStr += '<font style="color:Tomato">'+data.candidateName+'</font> Voting Trendz';
	hStr += ' ... ';
	hStr += ' Total Votes Earned : <font style="color:Tomato">' +data.totalVotes + '</font>';
	hStr += ' ... ';
	hStr += ' Total Votes % : <font style="color:Tomato">' +data.totalVotesPercent+' %</font>';
	hStr += ' ... ';
	hStr += ' Result Status : <font style="color:Tomato">' +data.status + '</font>';
	
	if(candidateTrendzGraphelmt_head)
		candidateTrendzGraphelmt_head.innerHTML=hStr;

	var fStr = '';
	fStr += '<table width="100%">';
	fStr += '<tr>';
	fStr += '<td align="left"><input type="button"  id="prevButton" value="Previous" onclick="showNextPreviousCandidateVotingTrendz(candidateIndex,\'previous\')"></td>';
	fStr += '<td></td>';
	fStr += '<td align="right"><input type="button" id="nextButton" value="Next" onclick="showNextPreviousCandidateVotingTrendz(candidateIndex,\'next\')"></td>';
	fStr += '</tr>';
	fStr += '</table>';


	if(candidateTrendzGraphelmt_footer)
		candidateTrendzGraphelmt_footer.innerHTML=fStr;

	prevButtonElmt = document.getElementById("prevButton");
	nextButtonElmt = document.getElementById("nextButton");
	
	if(candidateIndex!=1)
		prevButtonElmt.disabled = false;
	else
		prevButtonElmt.disabled = true;

	if(candidateListSize>1 && candidateIndex!=candidateListSize)
		nextButtonElmt.disabled = false;
	else
		nextButtonElmt.disabled = true;
	
	

	var str = '';
	str+='<div class="CandidateResultsInfoHeading_head">MALE</div>';
	str+='<div class="CandidateResultsInfoHeading_body">';
	str+='<div>M Votes Earned                      : <font style="color:#CA6666">'+data.maleVotes+' ('+data.maleVotesPercent+ ' %)</font> </div>';
	str+='<div>M Votes % In Candidate Gained Votes : <font style="color:DarkGoldenRod">'+data.overallMaleVotesPercent+'%</font> </div>';
    str+='<div>Total M Votes %  In Constituency    : <font style="color:MediumPurple">'+data.maleVotesPercentInConstiVotes+ ' % </font></div>';

	str+='<div class="CandidateResultsInfoHeading_head">FEMALE</div>';
	str+=' <div class="CandidateResultsInfoHeading_body">';
	str+=' <div>F Votes Earned                     : <font style="color:#CA6666">'+data.femaleVotes+' ('+data.femaleVotesPercent+ ' %)</font> </div>';
	str+='<div>F Votes % In Candidate Gained Votes : <font style="color:DarkGoldenRod">'+data.overallFemaleVotesPercent+'%</font> </div>';
	str+='<div>Total F Votes %  In Constituency    : <font style="color:MediumPurple">'+data.femaleVotesPercentInConstiVotes+ ' % </font></div>';

	str+='<div class="CandidateResultsInfoHeading_head">MALE/FEMALE</div>';
	str+=' <div class="CandidateResultsInfoHeading_body">';
	str+=' <div>M/F Votes Earned                     : <font style="color:#CA6666">'+data.maleAndFemaleVotes+' ('+data.maleAndFemaleVotesPercent+ ' %) </font></div>';
	str+='<div>M/F Votes % In Candidate Gained Votes : <font style="color:DarkGoldenRod">'+data.overallMaleOrFemaleVotesPercent+'%</font> </div>';
	str+='<div>Total M/F Votes %  In Constituency    : <font style="color:MediumPurple">'+data.maleOrFemaleVotesPercentInConstiVotes+ ' % </font></div>';


	if(candidateTrendzGraphelmt)
		candidateTrendzGraphelmt.innerHTML = str;
	
	if(results)
	{		
		var imgChart1 = document.getElementById("candVotingTrendz");
		var imgChart2 = document.getElementById("candOverallVotesPercent");
		
		imgChart1.src = '../charts/'+results.candOverallVotesPercent;
		imgChart2.src = '../charts/'+results.candVotingTrendz;

		
	}
	else
	{		
		var graph1Elmt = document.getElementById("candidateVotingGraph1");
		var graph2Elmt = document.getElementById("candidateVotingGraph2");

		
		graph2Elmt.innerHTML='<div >% Votes Gained By Candidate In Total Constituency ...</div><IMG id="candVotingTrendz" SRC="../charts/'+obj.electionTrendzCharts.candOverallVotesPercent+'"/>';
		graph1Elmt.innerHTML='<div > Male,Female,M/F Votes % In Candidate Gained Votes ... </div><IMG id="candOverallVotesPercent" SRC="../charts/'+obj.electionTrendzCharts.candVotingTrendz+'"/>';
	}
}

function buildConstituencyVotingTrendzHeader(obj)
{
	var headElmt = document.getElementById("constituencyVotersInfoDiv_Head");
	
	var str = '';
	str += '<div id="constituencyTrendzHead">'+obj.constituencyName +' Voting Trendz For The Year '+obj.electionYear+'</div>';

	if(headElmt)
		headElmt.innerHTML = str;
}	

function buildCenterConstituencyVotersInfoContent(obj)
{		
	var  votersElmt = document.getElementById('constituencyVotersInfoDiv_Body_voters');		
	var  votersGraphElmt = document.getElementById('constituencyVotersInfoDiv_Body_votersGraph');		

	var str = '';
	str+='<table class="constituencyInfoTable" width="100%">';
	
	str+='<tr>';
	str+='<th colspan="5"> Constituency Voting Info</th>';
	str+='</tr>';
	
	str+='<tr>';
	str+='<th></th>';
	str+='<th>Voters</th>';
	str+='<th>Polled Votes</th>';
	str+='<th>Polling % </th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<td>'+obj.maleVoters+'</td>';
	str+='<td>'+obj.malePolledVotes+'<font style="color:Tomato"> ( '+obj.malePolledPercentInTotalPolled+' % )</font></td>';
	str+='<td>'+obj.malePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Female</th>';
	str+='<td>'+obj.femaleVoters+'</td>';
	str+='<td>'+obj.femalePolledVotes+'<font style="color:Tomato"> ( '+obj.femalePolledPercentInTotalPolled+' % )</font></td>';
	str+='<td>'+obj.femalePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male/Female</th>';
	str+='<td>'+obj.maleAndFemaleVoters+'</td>';
	str+='<td>'+obj.maleAndFemalePolledVotes+'<font style="color:Tomato"> ( '+obj.maleOrFemalePolledPercentInTotalPolled+' % )</font></td>';
	str+='<td>'+obj.maleAndFemalePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Total</th>';
	str+='<td>'+obj.totalVoters+'</td>';
	str+='<td>'+obj.totalPolledVotes+'<font style="color:Tomato"> ( 100 % )</font></td>';
	str+='<td>'+obj.pollingPercent+'</td>';
	str+='</tr>';

	str+='</table>';

	if(votersElmt)
		votersElmt.innerHTML=str;


	/*var gStr = '';
	gStr+='<IMG id="pollingDetailsChartImg" SRC="charts/'+obj.electionTrendzCharts.pollingDetailsChart+'"/>';
	
	if(votersGraphElmt)
		votersGraphElmt.innerHTML = gStr;*/
}	

function buildConstituencyVotingTrendzGraph(obj)
{		
	var elmt = document.getElementById('constituencyVotersInfoDiv_Body_votingTrendzGraph');
	
	if(elmt)
		elmt.innerHTML = '<IMG id="votingTrendzChartImg" SRC="../charts/'+obj.electionTrendzCharts.votingTrendzMainChart+'"/>';

	/*var imgElmt = document.getElementById("votingTrendzChartImg");
	imgElmt.src = 'charts/'+obj.electionTrendzCharts.votingTrendzMainChart;	*/
}


function candidateVotingTrendzDatatable(obj)
{
	var elmt = document.getElementById("constituencyVotersInfoDiv_Body_candidate");
	
	var str = '';
	str+='<table id="maleFemaleVotingTrendzTable">';
	for(var i in obj.partyElectionTrendzVO)
	{
		str+='<tr>';
		str+='<td>'+obj.partyElectionTrendzVO[i].candidateName+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].partyName+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].totalVotes+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].maleVotes+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].femaleVotes+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].maleAndFemaleVotes+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].totalVotesPercent+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].maleVotesPercent+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].femaleVotesPercent+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].maleAndFemaleVotesPercent+'</td>';
		str+='<td>'+obj.partyElectionTrendzVO[i].status+'</td>';	
		str+='</tr>';
	}
	str+='</table>';

	if(elmt)
		elmt.innerHTML=str;

var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("maleFemaleVotingTrendzTable"));
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},							
							{	key : "partyName"},																		
							{	key : "totalVotes",parser:"number"},							
							{	key : "maleVotes",parser:"number"},
							{	key : "femaleVotes",parser:"number"},
							{	key : "maleAndFemaleVotes",parser:"number"},
							{	key : "totalVotesPercent",parser:"number"},
							{	key : "maleVotesPercent",parser:"number"},
							{	key : "femaleVotesPercent",parser:"number"},
							{	key : "maleAndFemaleVotesPercent",parser:"number"},
							{	key : "status"}
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Name', sortable:true}, 
				{key:"partyName", label:'Party', sortable:true}, 							
				{key:"totalVotes", label:'Total Votes', sortable:true},
				{key:"maleVotes", label:'Votes(M)', sortable:true},
				{key:"femaleVotes", label:'Votes(F)', sortable:true},
				{key:"maleAndFemaleVotes", label:'Votes(M/F )', sortable:true},
				{key:"totalVotesPercent", label:'Votes %', sortable:true},
				{key:"maleVotesPercent", label:'M %', sortable:true},
				{key:"femaleVotesPercent", label:'F %', sortable:true},
				{key:"maleAndFemaleVotesPercent", label:'M/F %', sortable:true},
				{key:"status", label:'status', sortable:true}
			]; 
		 
	var captionStr = '';
	captionStr += '<div width="100%">';
	captionStr += '<span id="dataTableTitle">Candidate Voting Trendz</span>';
	captionStr += '<span id="dataTableHead"> <Font color="Red">* </Font>M - Male , <Font color="Red">* </Font> F - Female </span>';
	captionStr += '</div>';
	
	var myDataTable = new YAHOO.widget.DataTable("constituencyVotersInfoDiv_Body_candidate",myColumnDefs, myDataSource); 

}

function buildelectionYearsForVotingTrendz(obj)
{
	

	var elmt = document.getElementById('constituencyVotersInfoDiv_Navigation');

	var str = '';
	str+='<span>View Voting Trendz : </span>';
	str+='<span>';
	str+='<input type="radio" name="electionType" value="assembly" onclick="enableElectionYearSelect(this.value)">';
	str+='<select id="assembly_YearSelect" disabled="disabled">';
	str+='<option value="0">Assembly</option>';
	for(var i in obj.assemblyElections)
		str+='<option value="'+obj.assemblyElections[i].electionId+'_'+obj.assemblyElections[i].electionTypeId+'">'+obj.assemblyElections[i].electionYear+'</option>';
	str+='</select>';
	str+='</input>';
	str+='</span>';

	str+='<span>';
	str+='<input type="radio" name="electionType" value="parliament" onclick="enableElectionYearSelect(this.value)"/>';
	str+='<select id="parliament_YearSelect" disabled="disabled">';
	str+='<option value="0">Parliament</option>';
	for(var i in obj.parliamentElections)
		str+='<option value="'+obj.parliamentElections[i].electionId+'_'+obj.parliamentElections[i].electionTypeId+'">'+obj.parliamentElections[i].electionYear+'</option>';
	str+='</select>';
	str+='</span>';

	str+='<span>';
	str+='<input type="button" value="View" onclick="getVotingTrendzForyear()">';
	str+='</span>';
	if(elmt)
		elmt.innerHTML = str;
				  
}

function enableElectionYearSelect(value)
{
	var asmbSelectElmt = document.getElementById("assembly_YearSelect");
	var parSelectElmt = document.getElementById("parliament_YearSelect");

	if(value=="assembly")
	{
		asmbSelectElmt.disabled = false;
		parSelectElmt.disabled = true;
	}
	else if(value=="parliament")
	{
		asmbSelectElmt.disabled = true;
		parSelectElmt.disabled = false;
	}
}
  
  </script>
 </HEAD>

 <BODY>
	<div id="constituencyVotersInfoDiv_Main" class="innerLayoutDivClass yui-skin-sam">	</div>
	<script type="text/javascript">
		var parent = window.opener;
		electionTrendzReportVO = parent.constituencyPageMainObj.electionTrendzReportVO;		
		buildVotingTrendzLayout("constituencyVotersInfoDiv_Main",electionTrendzReportVO);
		//buildelectionYearsForVotingTrendz(electionTrendzReportVO.previousElectionYears);
	</script>

 </BODY>
</HTML>
