<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> ${constituencyDetails.constituencyName} Constituency Details</title>


<!-- YUI Dependency files (Start) -->

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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="js/constituencyPage/constituencyPage.js"></script>
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	

<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRSqcWSqDo-rwCiW8VjO_0U_k7HAuxQBSweyAZ1v5ozDSPMDKAFtPwSrGw&sensor=true"
            type="text/javascript"></script>

<script type="text/javascript">
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
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
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

	for(var i =0;i<constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO.length;i++)
	{		
		var data = constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO[i];
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
					constituencyId:constituencyPageMainObj.electionTrendzReportVO.constituencyId,
					electionId:electionId,
					electionTypeId:electionTypeId,
					electionYear:year,
					task:'getVotingTrendzForElectionYears'
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
	
}
</script>
</head>
<body onLoad="getString()">
<div id="constituencyPageMain">

	<div id="electionResults_Panel_Main" class="yui-skin-sam">
			<div id="electionResults_Panel">
	</div>

	<table width="100%">
		<tr>
			<td class="alignTD">
				
				<div id="constituencyPageCenterInfoDiv">
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>

							<div id="constituencyPageProfileInfoDiv_Main" class="innerLayoutDivClass">
								<div id="constituencyPageProfileInfoDiv_Head" class="layoutHeadersClass"> ${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType}  Constituency Details : </div>
								<div id="constituencyPageProfileInfoDiv_Body" class="layoutBodyClass"></div>
							</div>
					</div>	
					
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
					
						<div id="constituencyPageCandidateInfo_Main" class="innerLayoutDivClass">
							<div id="constituencyPageCandidateInfo_Head" class="layoutHeadersClass"></div>
							<div id="constituencyPageCandidateInfo_Body" class="layoutBodyClass yui-skin-sam">
								<div id="constituencyPageCandidateInfo_Assembly"></div>
								<div id="constituencyPageCandidateInfo_Parliament"></div>
							</div>
						</div>		
					</div>

					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>	
						<div id="constituencyPageElectionInfoDiv_Main" class="innerLayoutDivClass">
							<div id="constituencyPageElectionInfoDiv_Head" class="layoutHeadersClass"></div>
							<div id="constituencyPageElectionInfoDiv_Body" class="layoutBodyClass"></div>
						</div>		
					</div>
				

					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>

						<div id="mandalsVotersInfoDiv_Main" class="innerLayoutDivClass">
							<div id="mandalsVotersInfoDiv_Head" class="layoutHeadersClass">${constituencyDetails.constituencyName} Mandals Voters Details :</div>
							<div id="mandalsVotersInfoDiv_Body" class="layoutBodyClass yui-skin-sam"></div>
						</div>
					</div>
				
				</div>
			</td>
			<!--<td> <div class="spacerTD"> </div> </td>-->
			<td class="alignTD">
				<div id="constituencyRightContentDiv">					
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div> 

						<div id="constituencyPageRightMapDiv" class="contentDivClass">
							<div id="map_canvas"></div>
							<div id="constituencyInfoDiv">
								<div id="constituencyInfoDiv_Head"></div>
								<div id="constituencyInfoDiv_Body"></div>
							</div>					
							<div id="constituencyPeopleConnectDiv">
								<div id="constituencyPeopleConnectDiv_Head"></div>
								<div id="constituencyPeopleConnectDiv_Body"></div>
							</div>
							<div id="problemViewingDiv">
								<div id="problemViewingDiv_Head"></div>
								<div id="problemViewingDiv_Body"></div>
							</div>
							<div id="problemPostingDiv">
								<div id="problemPostingDiv_Head"></div>
								<div id="problemPostingDiv_Body"></div>
							</div>					
						</div>	
					</div>
				</div>
			</td>
			
		</tr>
		<tr>
			<td colspan="2">
				<div id="constituencyCenterContentOuter" class="rounded"> 						
					<div class="corner topLeft"></div>
					<div class="corner topRight"></div>
					<div class="corner bottomLeft"></div>
					<div class="corner bottomRight"></div>

					<div id="constituencyVotersInfoDiv_Main" class="innerLayoutDivClass">	</div>
					<div id="constituencyVotersInfoDiv_electionYears" class="innerLayoutDivClass">	</div>

				</div>
			</td>
		</tr>
	</table>
	<!--<div id="constituencyPageLayoutDiv">
		<div id="electionResults_Panel_Main" class="yui-skin-sam">
			<div id="electionResults_Panel">
		</div>
	</div>	
	<div id="constituencyRightmapOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
	-->
	
</div>

<script type="text/javascript">

	/*	Constituency Page basic Info
		-----------------------------
	*/
	constituencyPageMainObj.constituencyAddress="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";
	constituencyPageMainObj.contextPath = "<%=request.getContextPath()%>";	
	
	constituencyPageMainObj.constituencyInfo.constituencyName = "${constituencyDetails.constituencyName}";
	constituencyPageMainObj.constituencyInfo.districtName = "${constituencyDetails.districtName}";
	constituencyPageMainObj.constituencyInfo.stateName = "${constituencyDetails.stateName}";
	constituencyPageMainObj.constituencyInfo.startDate = "${constituencyDetails.startDate}";
	constituencyPageMainObj.constituencyInfo.deformDate = "${constituencyDetails.deformDate}";
	constituencyPageMainObj.constituencyInfo.constituencyType = "${constituencyDetails.constituencyType}";
	
	
	/*	Constituency Election Results Info
		----------------------------------
	*/

	<c:forEach var="constituencyElectionResults" items="${constituencyElectionResultsVO}">	
	var constiObj=
				{
					candidateName:'${constituencyElectionResults.candidateResultsVO.candidateName}',
					partyName:'${constituencyElectionResults.candidateResultsVO.partyName}',
					year:'${constituencyElectionResults.electionYear}',
					votesEarned:'${constituencyElectionResults.candidateResultsVO.votesEarned}',
					votesPercentage:'${constituencyElectionResults.candidateResultsVO.votesPercentage}',
					oppositionCandInfo:[]
				 };
			<c:forEach var="detailedResult" items="${constituencyElectionResults.candidateOppositionList}" >
				var oppositionList={
										candidateName:'${detailedResult.candidateName}',
										partyName:'${detailedResult.partyName}',
										year:'${constituencyElectionResults.electionYear}',
										votesEarned:'${detailedResult.votesEarned}',
										votesPercentage:'${detailedResult.votesPercentage}'										
									};
						
					constiObj.oppositionCandInfo.push(oppositionList);
			</c:forEach>			
			constituencyPageMainObj.constituencyElectionInfo.push(constiObj);			
	</c:forEach>
	
	/*	Constituency Voters Info
		-------------------------
	*/

	<c:forEach var="vInfo" items="${votersInfo}" >	
		var obj ={
					year:'${vInfo.year}',
					info:[]
				};
		<c:forEach var="info" items="${vInfo.votersInfoForMandalVO}" >	
		var vObj=
				{
					mandalId:'${info.mandalId}',
					mandalName:'<a href="mandalPageElectionInfoAction.action?MANDAL_ID=${info.mandalId}&MANDAL_NAME=${info.mandalName}"> ${info.mandalName}</a>',
					mandalMaleVoters:'${info.totalMaleVoters}',
					mandalFemaleVoters:'${info.totalFemaleVoters}',
					mandalTotalVoters:'${info.totalVoters}',
					isPartial:'${info.isPartial}'
				 };
			obj.info.push(vObj);
		</c:forEach>
			constituencyPageMainObj.constituencyVotersInfo.push(obj);
	</c:forEach>
	

	/*	Assembly Candidate Info
		-------------------------
	*/
	<c:forEach var="cInfo" items="${candidateDetailsForConstituency.assemblyCandidateInfo}">	
	var candidateObj={
						constituencyId : '${cInfo.constituencyId}',
						constituencyName : '<a href="constituencyPageAction.action?constituencyId=${cInfo.constituencyId}"> ${cInfo.constituencyName}</a>',
						constituencyType:'${cInfo.constituencyType}',
						deformDate:'${cInfo.deformDate}',
						candidateId : '${cInfo.candidateId}',
						candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${cInfo.candidateId}"> ${cInfo.candidateName}</a>',	
						partyId:' ${cInfo.partyId}',
						party : '${cInfo.party}',
						partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${cInfo.partyFlag}" height="30" width="40"/>'
					 };		
	
	constituencyPageMainObj.presentAssemblyCandidate.push(candidateObj);
	</c:forEach>
	
	/*	Parliament Candidate Info
		-------------------------
	*/
	var pmtObj = {
					constituencyId : '${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}',
					constituencyName :  '<a href="constituencyPageAction.action?constituencyId=${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}">${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}</a>',									
					constituencyType:'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}',
					deformDate:'${candidateDetailsForConstituency.parliamentCandidateInfo.deformDate}',
					candidateId : '${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}',
					candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"> ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</a>',		
					partyId:' ${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}',
					party : '${candidateDetailsForConstituency.parliamentCandidateInfo.party}',
					partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/>'
				 };		
	constituencyPageMainObj.presentParliamentCandidate.push(pmtObj);

	//constituencyName : '<a href="constituencyPageAction.action?constituencyId=${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}&electionType=${constituencyDetails.constituencyType}&delimitation=${constituencyDetails.deformDate}">${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}</a>',

	/*	Constituency problems Info
		-------------------------
	*/
	
	<c:forEach var="problem" items="${problemBean}">	
	var problemObj={
						problemId:'${problem.problemId}',
						problem:'${problem.problem}',
						description:'${problem.description}',
						state:'${problem.state}',
						district:'${problem.district}',
						constituency:'${problem.constituency}',
						tehsil:'${problem.tehsil}',
						village:'${problem.village}',
						hamlet:'${problem.hamlet}',
						reportedDate:'${problem.reportedDate}',
						existingFrom:'${problem.existingFrom}',
						name:'${problem.name}',
						postedPersonName:'${problem.postedPersonName}',
						email:'${problem.email}',						
						phone:'${problem.phone}',
						mobile:'${problem.mobile}',
						address:'${problem.address}'
					};
		
	constituencyPageMainObj.problemsInfo.push(problemObj);
	</c:forEach>

	/*
		Voting Trendz Info 
		------------------
	*/	

		var vTObj = constituencyPageMainObj.electionTrendzReportVO;

		vTObj.state = '${electionTrendzReportVO.state}';
		vTObj.electionType = '${electionTrendzReportVO.electionType}';
		vTObj.electionYear = '${electionTrendzReportVO.electionYear}';
		vTObj.constituencyId = '${electionTrendzReportVO.constituencyId}';
		vTObj.constituencyName = '${electionTrendzReportVO.constituencyName}';
				

		vTObj.electionTrendzOverviewVO.totalVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.totalVoters}';
		vTObj.electionTrendzOverviewVO.maleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVoters}';
		vTObj.electionTrendzOverviewVO.femaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVoters}';
		vTObj.electionTrendzOverviewVO.maleAndFemaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemaleVoters}';	
		
		vTObj.electionTrendzOverviewVO.totalPolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.totalPolledVotes}';
		vTObj.electionTrendzOverviewVO.malePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.malePolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePolledVotes}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePolledVotes}';

		vTObj.electionTrendzOverviewVO.pollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.pollingPercent}';
		vTObj.electionTrendzOverviewVO.malePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercent}';
		vTObj.electionTrendzOverviewVO.femalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercent}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePollingPercent}';
		
		vTObj.electionTrendzOverviewVO.maleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercent}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercent}';
		vTObj.electionTrendzOverviewVO.maleOrFemaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemaleVotersPercent}';

		vTObj.electionTrendzOverviewVO.overallMalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallFemalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallMaleOrFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMaleOrFemalePollPercent}';
				
		vTObj.electionTrendzOverviewVO.maleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersInConstituency}';
		vTObj.electionTrendzOverviewVO.femaleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersInConstituency}';

		vTObj.electionTrendzOverviewVO.maleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercentInConsti}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercentInConsti}';
	
		
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz}';


		<c:forEach var="candidate" items="${electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO}">	
			var cObj = {
							partyId:'${candidate.partyId}',
							partyName:'${candidate.partyName}',
							partyLogo:'${candidate.partyLogo}',
							partyFlag:'${candidate.partyFlag}',
							candidateId:'${candidate.candidateId}',
							candidateName:'${candidate.candidateName}',							
							validVotes:'${candidate.validVotes}',
							totalVotes:'${candidate.totalVotes}',
							maleVotes:'${candidate.maleVotes}',
							femaleVotes:'${candidate.femaleVotes}',
							maleAndFemaleVotes:'${candidate.maleAndFemaleVotes}',
							totalVotesPercent:'${candidate.totalVotesPercent}',
							maleVotesPercent:'${candidate.maleVotesPercent}',
							femaleVotesPercent:'${candidate.femaleVotesPercent}',
							maleAndFemaleVotesPercent:'${candidate.maleAndFemaleVotesPercent}',
							rank:'${candidate.rank}',
							overallMaleVotesPercent : '${candidate.overallMaleVotesPercent}',
							overallFemaleVotesPercent : '${candidate.overallFemaleVotesPercent}',
							overallMaleOrFemaleVotesPercent : '${candidate.overallMaleOrFemaleVotesPercent}',						
							maleVotesPercentInConstiVotes:'${candidate.maleVotesPercentInConstiVotes}',
							femaleVotesPercentInConstiVotes:'${candidate.femaleVotesPercentInConstiVotes}',
							maleOrFemaleVotesPercentInConstiVotes:'${candidate.maleOrFemaleVotesPercentInConstiVotes}',
							status:'${candidate.status}'
					   };
					  vTObj.electionTrendzOverviewVO.partyElectionTrendzVO.push(cObj);
		</c:forEach>
		
		
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.status = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.status}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes}';

		
		<c:forEach var="asmElection" items="${electionTrendzReportVO.prevElectionYearsInfo.assemblyElections}">	
			var assemblyElecObj={
									electionId:'${asmElection.electionId}',
									electionTypeId:'${asmElection.electionTypeId}',
									electionType:'${asmElection.electionType}',
									stateId:'${asmElection.stateId}',
									state:'${asmElection.state}',
									electionYear:'${asmElection.electionYear}'
								};

			vTObj.previousElectionYears.assemblyElections.push(assemblyElecObj);
		</c:forEach>

		<c:forEach var="parElection" items="${electionTrendzReportVO.prevElectionYearsInfo.parliamentElections}">	
			var parElecObj={
									electionId:'${parElection.electionId}',
									electionTypeId:'${parElection.electionTypeId}',
									electionType:'${parElection.electionType}',
									stateId:'${parElection.stateId}',
									state:'${parElection.state}',
									electionYear:'${parElection.electionYear}'
								};

			vTObj.previousElectionYears.parliamentElections.push(parElecObj);
		</c:forEach>

		

	initializeConstituencyPage();
</script>
</body>
</html>