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

						<div id="constituencyVotersInfoDiv_Main" class="innerLayoutDivClass">
							<div id="constituencyVotersInfoDiv_Head" class="layoutHeadersClass">${constituencyDetails.constituencyName} Constituency Voters Details :</div>
							<div id="constituencyVotersInfoDiv_Body" class="layoutBodyClass yui-skin-sam"></div>
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
						constituencyName : '${cInfo.constituencyName}',
						candidateId : '${cInfo.candidateId}',
						candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${cInfo.candidateId}"> ${cInfo.candidateName}</a>',	
						partyId:' ${cInfo.partyId}',
						party : '${cInfo.party}'
					 };		
	constituencyPageMainObj.presentAssemblyCandidate.push(candidateObj);
	</c:forEach>
	
	/*	Parliament Candidate Info
		-------------------------
	*/
	var pmtObj = {
					constituencyId : '${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}',
					constituencyName : '${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}',
					candidateId : '${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}',
					candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"> ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</a>',		
					partyId:' ${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}',
					party : '${candidateDetailsForConstituency.parliamentCandidateInfo.party}'
				 };		
	constituencyPageMainObj.presentParliamentCandidate.push(pmtObj);

	
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

	initializeConstituencyPage();
</script>
</body>
</html>