<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting Trends in ${constiName} Constituency</title>

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

	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">
	<link rel="stylesheet" type="text/css" href="styles/indexPage/indexPage.css">
	<link rel="stylesheet" type="text/css" href="styles/biElectionPage/biElectionPage.css">

	<script type="text/javascript" src="js/constituencyPage/constituencyPage.js"></script>
	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	
    <style type="text/css">
		.mainHeading 
		{
			background-image:url("images/icons/electionResultsReport/heading.png");
			border:0 solid #AEE2FF;
			color:#000000;
			font-family:MS Sans-serif;
			font-size:17px;
			font-weight:bold;
			height:25px;
			margin-bottom:15px;
			margin-top:15px;
			padding:10px;
			text-align:center;
		}
		#inputSelectionCriteria,#crossVotingData_Graph_Div,#constituencyMainDetails_Div,#crossVotingDetailsDiv,#overViewHeadingDiv
		{
			padding: 10px;
			color: #707070;
			margin: 10px;
			border: 2px solid #E0E0D6;
			font-family: verdana;
			
		}
		
		#selectLocationOptions
		{
			padding: 10px;
			color: #707070;
			margin: 10px;
			border: 2px solid #E0E0D6;
		}
		.selectWidth
		{
			width:150px;
			font-weight: bold;
			color:#909090;
		}
		CAPTION
		{
			font-weight: bold;
			font-size:12px;
			text-align: center;
			padding: 10px;
			border-bottom: 1px solid #707070;	
			color: #247CD4;		
		}
		
	
		.participatingPartiestable th
		{		
			padding:5px;
			background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat scroll 0 0 #D8D8DA;
			border:1px solid #ADADAD;
		}

		.participatingPartiestable td
		{			
			padding:5px;
			border:1px solid #ADADAD;
		}
		.detailsTableHeader
		{
			background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
			height:30px;
			text-align:left;
		}

		.detailsTableHeaderSpan
		{
			position:relative;
			top:8px;
			color:#4B74C6;
			font-weight:bold;
		}

		.detailsBodyDiv
		{
			border-bottom:1px solid #D2D6DB;
			border-left:1px solid #D2D6DB;
			border-right:1px solid #D2D6DB;
		}
		
		#votesShareDetailsTable th
		{
			background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat scroll ;
			border:1px solid #ADADAD;
			padding:5px;
			color:#18325A;
		}

		#votesShareDetailsTable td
		{
			padding:5px;
			border:1px solid #DFDFDF;
			color:#707070;
			font-weight: bold;
		}
		.mandalVotesShareTable th
		{
			/*background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat scroll 0 0 #D8D8DA;
			color:#0055CC;*/
			background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat scroll 0 0 #D8D8DA;
			border:1px solid #ADADAD;
			padding:5px;
		}
		.mandalVotesShareTable td
		{
			background-color:  #F0F8FF;
			text-align:center;
			padding:5px;
			
		}
    </style>
	<script  type="text/javascript"><!--
	var Localization = { <%		
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String totalMuncipalities = rb.getString("totalMuncipalities");
			String totalCorporations = rb.getString("totalCorporations");
			String muncipalDataAvailability = rb.getString("muncipalDataAvailability");
			String corporationsDataAvailability = rb.getString("corporationsDataAvailability");
		%> }
		var totalMuncipalities = "<%=totalMuncipalities%>";
		var totalCorporations = "<%=totalCorporations%>";
		var muncipalDataAvailability = "<%=muncipalDataAvailability%>";
		var corporationsDataAvailability = "<%=corporationsDataAvailability%>";
		var districtsInfo = new Array();
		var localizationObj = '';
		var assemblyElectionType='Assembly';
		var constituencyIdGlobal = '${constiId}';
		var constituencyName = '${constiName}';
		var constiMandalWiseResultsPresChart;
		var constiMandalWiseResultsPrevChart;
		var mptcChart2001,mptcChart2006,zptcChart2001,zptcChart2006;	
		var allPartiesCharts = '';
		var mandalIds = null;
		var votesShareData = '';
		var tehsilDetails={
				zptcArray:[],
				mptcArray:[],
				partyArray:[],
				partyMptcArray:[],
				partyMuncipalArray:[],
				partyCorporationArray:[],
				localBodyArray:[]
		};
		var tehsilElections={
				zptcElectionYears:[],
				mptcElectionYears:[],
				staticParties:[]
		};
		var mptcElectionType="${mptcElectionType}",zptcElectionType="${zptcElectionType}";
		var muncipalityElectionType='${muncipalityElectionType}',corporationElectionType='${corporationElectionType}';
		var muncipalityElectionId='${muncipalityElectionTypeId}',corporationElectionTypeId='${corporationElectionTypeId}';
		
		var docOpener = window.opener;
		localizationObj = docOpener.localizationObj;
		<c:forEach var="staticParties" items="${staticPartiesList}">
			var pObj =	{
							partyId:"${staticParties.id}",
							partyName:"${staticParties.name}"
						};
		
				tehsilElections.staticParties.push(pObj);
						
		</c:forEach>
		
		<c:forEach var="district" items="${districtsAndConsts}">
			var districtObj={
									districtName:"${district.districtName}",
									districtId:"${district.districtId}",
									constituencies:[]								
							};
				
			<c:forEach var="constituencies" items="${district.constituenciesList}">
					var cObj =	{
									constId:"${constituencies.id}",
									constName:"${constituencies.name}"
								};
					districtObj.constituencies.push(cObj);			
			</c:forEach>
			districtsInfo.push(districtObj);	
		</c:forEach>

			<c:forEach var="zptcElectionYears"  items="${zptcElectionYears}" >
				var ob={
							id:'${zptcElectionYears.id}',
							value:'${zptcElectionYears.name}'
						};
				tehsilElections.zptcElectionYears.push(ob);	
				</c:forEach>

				<c:forEach var="mptcElectionYears"  items="${mptcElectionYears}" >
				var ob={
							id:'${mptcElectionYears.id}',
							value:'${mptcElectionYears.name}'
						};
				tehsilElections.mptcElectionYears.push(ob);	
			</c:forEach>

			function getConstituencyOverViewResult(constituencyId,constituencyName)
			{				
				var heading =document.getElementById("overViewHeadingDiv");
				var headingDIV='';
				heading.innerHtml=headingDIV;
				
				var jsObj = {						
						constituencyId:constituencyId,
						constiName:constituencyName,
						task:"getConstituencyVotesOverview"
					};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/getVotesOverViewInAConstituencyAjaxAction.action?"+rparam;
				callAjax(jsObj, url);
			}
			
			function constituencyOverViewResult(myResults)
			{
				
				var heading =document.getElementById("constituencyMainDetails_Div");
				var headingDIV='';
				headingDIV+='<fieldset padding:10px;">';  		
				headingDIV+='<legend style="background-color:#567AAF;font-family:verdana,helvetica,clean,sans-serif;color:#FFFFFF;font-weight:bold;padding:10px;font-size:12px;">Constituency Overview</legend>';

				headingDIV+='<div id="votesInfoDiv" style="color:#247CD4;font-size:12px;font-weight:bold;"></div>';
				headingDIV+='<table width="80%" cellspacing="10" style="margin-top:10px;">';   

				headingDIV+='	<tr>';
				headingDIV+='		<td style="color:#18325A;font-size:12px;font-family:verdana;" align="left"><b>Total Voters for Year 2009</b></td>';
				headingDIV+='		<td><b> :</b></td>';
				headingDIV+='		<td align="left" style="color:red;font-size:14px;font-weight:bold;">'+myResults.latestElectionYearsTotalVoters+'</td>';
               
				headingDIV+='		<td style="color:#18325A;font-size:12px;font-family:verdana;" align="left"><b>Total Polled Votes for Year 2009</b></td>';
				headingDIV+='		<td><b> :</b></td>';
				headingDIV+='		<td align="left" style="color:red;font-size:14px;font-weight:bold;">'+myResults.latestElectionYearsTotalPolledVotes+' <font color="black"> [</font><font color="green"> '+myResults.latestElectionYearsTotalVotesPercentage+' % </font><font color="black">]</font></td>';		
								
				headingDIV+='	</tr>';

				headingDIV+='	<tr>';
				headingDIV+='		<td style="color:#18325A;font-size:12px;font-family:verdana;" align="left"><b>Total Voters for Year 2010</b></td>';
				headingDIV+='		<td><b> :</b></td>';
				if(myResults.presentYearTotalVoters!=0)
				{	
					headingDIV+='		<td align="left" style="color:red;font-size:14px;font-weight:bold;">'+myResults.presentYearTotalVoters+'</td>';				
				}
				else
				{				
					headingDIV+='		<td align="left" style="color:#1C4B7A;"> Data Not Available</td>';
				}					
				
				headingDIV+='	</tr>';
				headingDIV+='</table>';
				
                headingDIV+='<div id="contestingCandidates_InnerDiv" style="margin:10px;">';
				headingDIV+='<table width="100%" border="0">';
				headingDIV+='<tr>';
				headingDIV+='<td width="30%" valign="top">';
				headingDIV+= '<div id="constiContestingCandsDiv" style="color:#247CD4;font-size:12px;font-weight:bold;text-align:left;padding:5px;text-decoration:underline;">';
				headingDIV+= 'Bye-Election 2010 Contesting Candidates</div>';
				headingDIV+='<div id="contestingCandidatesIn" style="margin:10px;border:solid 0px;" >';

				headingDIV+='<table class="participatingPartiestable" border="0">';	
				headingDIV+='<tr>';
				headingDIV+='<td colspan="2" style="border:0px;padding:0px;">';
				headingDIV+='<table class="participatingPartiestable_inner" border="0"  cellpadding="0" cellspacing="0">';
				headingDIV+='<tr>';
				headingDIV+='	<td style="padding:0px;border:none;"> ';
				headingDIV+='		<img src="images/icons/electionResultsAnalysisReport/header_left.gif">';
				headingDIV+='	</td>';

				headingDIV+='	<td style="padding:0px;border:none;">';
				headingDIV+='		<div class="detailsTableHeader" style="width:271px;">';
				headingDIV+='			<span class="detailsTableHeaderSpan"> </span>';
				headingDIV+='		</div>';
				headingDIV+='	</td>';

				headingDIV+='	<td style="padding:0px;border:none;">';
				headingDIV+='		<img src="images/icons/electionResultsAnalysisReport/second.png">';
				headingDIV+='	</td>';
				headingDIV+='</tr>';
				headingDIV+='</table>';
				headingDIV+='</td>';
				headingDIV+='</tr>';			
				
				headingDIV+='<tr>';
				headingDIV+='<th align="center" style="color:#4B74C6">Party</th>';
				headingDIV+='<th align="center" style="color:#4B74C6">Candidate</th>';
				headingDIV+='</tr>';
		
				for(var c in myResults.contestingCands)
				{
					headingDIV+='<tr>';
					headingDIV+='<td align="center"><b>'+myResults.contestingCands[c].partyName+'</b></td>';
					headingDIV+='<td align="left"><b>'+myResults.contestingCands[c].candidateName+'</b></td>';
					headingDIV+='</tr>';
				}
				
				headingDIV+='</table>';
				headingDIV+='</div>';
                headingDIV+='</div>';
				headingDIV+='</td>';
				headingDIV+='<td width="35%" valign="top">';
				headingDIV+='	<div style="font-size: 12px;font-weight:bold;text-align:left;color:#247CD4;padding:5px;text-decoration:underline">';
				headingDIV+='		2009 Won & Opposition Candidates Result In Constituency';
				headingDIV+='	</div>';
				//-----------
				headingDIV+='	<div style="margin:10px">';
				headingDIV+='	<table class="participatingPartiestable" border="0" width="500px">';	
				headingDIV+='	<tr>';
				headingDIV+='		<td colspan="5" style="border:0px;padding:0px;">';
				headingDIV+='			<table class="participatingPartiestable_inner" border="0"  cellpadding="0" cellspacing="0" width="100%">';
				headingDIV+='			<tr>';
				headingDIV+='				<td style="padding:0px;border:none;"> ';
				headingDIV+='					<img src="images/icons/electionResultsAnalysisReport/header_left.gif">';
				headingDIV+='				</td>';

				headingDIV+='				<td style="padding:0px;border:none;">';
				headingDIV+='					<div class="detailsTableHeader" style="width:500px;">';
				headingDIV+='						<span class="detailsTableHeaderSpan"> </span>';
				headingDIV+='					</div>';
				headingDIV+='				</td>';

				headingDIV+='				<td style="padding:0px;border:none;">';
				headingDIV+='					<img src="images/icons/electionResultsAnalysisReport/second.png">';
				headingDIV+='				</td>';
				headingDIV+='			</tr>';
				headingDIV+='			</table>';
				headingDIV+='		</td>';
				headingDIV+='	</tr>';

				headingDIV+='<tr>';
				headingDIV+='<th align="center" style="color:#4B74C6">Party</th>';
				headingDIV+='<th align="center" style="color:#4B74C6">Candidate</th>';
				headingDIV+='<th align="center" style="color:#4B74C6">Votes Earned</th>';
				headingDIV+='<th align="center" style="color:#4B74C6">Votes %</th>';
				headingDIV+='<th align="center" style="color:#4B74C6">Status</th>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td align="center"><b>'+myResults.elecResultsInConsti.partyName+'</b></td>';
				headingDIV+='<td align="left"><b>'+myResults.elecResultsInConsti.candidateName+'</b></td>';
				headingDIV+='<td align="center"><b>'+myResults.elecResultsInConsti.votesEarned+'</b></td>';
				headingDIV+='<td align="left"><b>'+myResults.elecResultsInConsti.votesPercentage+'</b></td>';
				headingDIV+='<td align="left"><b> Won </b></td>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td align="center"><b>'+myResults.elecResultsInConsti.secondPartyName+'</b></td>';
				headingDIV+='<td align="left"><b>'+myResults.elecResultsInConsti.secondCandidateName+'</b></td>';
				headingDIV+='<td align="center"><b>'+myResults.elecResultsInConsti.votesEarnedBySecond+'</b></td>';
				headingDIV+='<td align="left"><b>'+myResults.elecResultsInConsti.votesPercentageBySecond+'</b></td>';
				headingDIV+='<td align="left"><b> Runner-up </b></td>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td colspan="5" style="color:#4B74C6;font-size:13px;" align="center">';
				headingDIV+='	<b>Margin Votes</b> : '+myResults.elecResultsInConsti.marginVotes;
				headingDIV+='</td>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td colspan="5" style="color:#4B74C6;font-size:13px;" align="center">';
				headingDIV+='	<b>Margin Votes %</b> : '+myResults.elecResultsInConsti.marginPercent;
				headingDIV+=' %</td>';
				headingDIV+='</tr>';
				headingDIV+='</table>';
				headingDIV+='</div>';
				//------
				headingDIV+='</td>';
				
				headingDIV+='<td width="35%">';
				headingDIV+='<img src="images/bi-constituency_maps/'+constituencyName+'.jpg"/>';
				headingDIV+='</td>';
			
				headingDIV+='</tr>';
				headingDIV+='</table>';
				heading.innerHTML=headingDIV;
			}
			function mandalVotingShareDetailsMethod()
			{

                var heading =document.getElementById("overViewHeadingDiv");
				var headingDIV='';
				headingDIV+='	<table width="100%">';
				headingDIV+='			<tr>';
				headingDIV+='					<td> <div id="mandalVotesShare"></td>';
				headingDIV+='			</tr>';	
				headingDIV+='	</table>';
				headingDIV+='<div id="madalwiseVotesRangeChart" style="text-align:center;"></div>';				
				headingDIV+='<div id="madalwiseVotesRange"></div>';
				headingDIV+='</fieldset>';
				headingDIV+='<div id="madalAllPartiesLocalElecData"></div>';
				heading.innerHTML=headingDIV; 
				
			}
			
		function callAjax(jsObj,url)
		{					
			var callback = {			
						   success : function( o ) {
								try {
										myResults = YAHOO.lang.JSON.parse(o.responseText);	
										
										if(jsObj.task == "getMandalVotingTrendz")
										{
											buildMandalVotingTrendzData(jsObj,myResults);
										}
										else if(jsObj.task == "getZptcElectionResults")
										{		
											if(myResults!= null &&  myResults.length>0){
												buildZptcResults(myResults,jsObj);	
											}else{
												hideZptcDiv();			
											}	
										}
										else if(jsObj.task == "getMptcElectionResults")
										{		
											if(myResults!= null &&  myResults.length>0){
												buildMptcResults(myResults, jsObj);
											}else{
												hideMptcDiv();			
											}	
										} else if(jsObj.task == "constituencyResults")
										{
											showChartData(myResults);
										} else if(jsObj.task == "getConstituencyResultsBySubLocations")
										{
											constiMandalWiseResultsPresChart = myResults.chartPath;
											getMandalsAndPartiesResults();
										}else if(jsObj.task == "getMandalsAndPartiesChartInElection")
										{
											constiMandalWiseResultsPrevChart = myResults;
											var imageDiv = document.getElementById("constitutencyMandalWiseResultsChart");
											var str = '';
											str +='<div style="margin: 10px;">';
											str += '<table border="1" width="100%" style="border-collapse:collapse;">';
											str += '<tr><td valign="top" align="center"><img src="charts/'+constiMandalWiseResultsPresChart+'"></td></tr>';
											str += '<tr><td valign="top" align="center"><img src="charts/'+constiMandalWiseResultsPrevChart+'"></td></tr>';
											str += '</table>';
											str += '</div>';
											imageDiv.innerHTML = str;
										}	
										else if(jsObj.task == "getConstituencyVotesOverview"){
											constituencyOverViewResult(myResults);
										}
										else if(jsObj.task == "votesSharingInConstituency"){
											buildVotesSharingData(myResults);
										}else if(jsObj.task == "getMandalVotesShare"){
											buildMandalVotesSharingData(myResults, jsObj);
											showAllPartiesAllElectionsResults(myResults);
										} 
										if(jsObj.task == "muncipalElectionDetails") 
										{
											if(myResults != null){										
												showMuncipalDetailsForLatestElectionYear(myResults.muncipalityVO,muncipalityElectionType);
											}else{
												errorMessageMuncipalitiesDiv();	
											}
											
										} 
										if(jsObj.task == "corporationElectionDetails") 
										{
											if(myResults != null){
												showMuncipalDetailsForLatestElectionYear(myResults.muncipalityVO,corporationElectionType);
											}else{
												errorMessageCorporationDiv();	
											}
										} 
									}
								catch (e) {   
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
		
		function initializeMuncipalResultsTableForParty(divId, dataSrc,electionType)
		{
			var resultsDataSourceForTehsil = new YAHOO.util.DataSource(dataSrc);
			resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
			resultsDataSourceForTehsil.responseSchema = {
				fields : [ {
					key : "partyName"
				}, {
					key : "participatedSeats"
				}, {
					key : "seatsWonByParty"
				}, {
					key : "percentageOfVotesWonByParty"
				}]	
			};
			
			var resultsColumnDefsForTehsil = [ 
					{key:"partyName",label : "Party Name",sortable:true, resizeable:true}, 
					{key:"participatedSeats",label : "Participated Seats",sortable:true,resizeable:true}, 
					{key:"seatsWonByParty",label : "Seats Won",sortable:true, resizeable:true}, 
					{key:"percentageOfVotesWonByParty",label : "Votes %", sortable:true, resizeable:true}	           
			];

							
			var myDataTableForMuncipalParty	 = new YAHOO.widget.DataTable(divId,resultsColumnDefsForTehsil, resultsDataSourceForTehsil);
		}

		function hideMuncipalitiesDiv(){		

			var muncipalityDIVEl = document.getElementById("muncipalitiesDivHead");	
			if(muncipalityDIV.style.display=='block'){
				muncipalityDIV.style.display = 'none';
			}else{
				muncipalityDIV.style.display = 'block';
			}
			if(muncipalityDIVEl.style.display=='block'){
				muncipalityDIVEl.style.display = 'none';
			}else{
				muncipalityDIVEl.style.display = 'block';
			}		
		}	
		function hideCorporationDiv(){
			if(muncipalityDIV.style.display=='block'){
				muncipalityDIV.style.display = 'none';
			}else{
				muncipalityDIV.style.display = 'block';
			}
		}
		function muncipalityHeadConstruction(result){			
			var totalMessage = '';
			totalMessage = totalMuncipalities;
			var muncipality = document.getElementById("muncipalitiesDivHead");
			var muncipalityDiv='';		
			muncipalityDiv+='<table border="0" cellpadding="0" cellspacing="0"><tr>';
			muncipalityDiv+='<td><img src="images/icons/districtPage/header_left.gif"/></td>';
			muncipalityDiv+='<td><div id="muncipalityInfoDivHead" class="districtPageRoundedHeaders_center" style="padding: 9px; width: 857px; height: 18px;"><a href="javascript:{}">'+totalMessage+' : <b class="counterSize"> '+result[0].totalMuncipalities+'</b></a></div>';
			muncipalityDiv+='</td>';
			muncipalityDiv+='</tr></table>';	
			muncipality.innerHTML = muncipalityDiv;
		}
		function corporationHeadConstruction(result){
		
			var totalMessage = '';		
			totalMessage = totalCorporations;
			var corporation = document.getElementById("corporationDivHead");
			
			var corporationDiv='';	
			corporationDiv+='<table border="0" cellpadding="0" cellspacing="0"><tr>';
			corporationDiv+='<td width="30"><img src="images/icons/districtPage/header_left.gif"/></td>';	
			corporationDiv+='<td><div id="corporationInfoDivHead" class="districtPageRoundedHeaders_center" style="padding: 9px; width: 857px; height: 18px;"><a href="javascript:{}">'+totalMessage+' : <b class="counterSize"> '+result[0].totalMuncipalities+'</b></a></div>';
			corporationDiv+='</td></tr></table>';	
			corporation.innerHTML = corporationDiv;
		}
		function showMuncipalDetailsForLatestElectionYear(result,electionType){	
			
			var muncipalityDIV = '';
			if(electionType == muncipalityElectionType){
				localBodyArray = tehsilDetails.partyMuncipalArray;
				muncipalityDIV = document.getElementById("muncipalitiesDiv");		
				muncipalityDIV.style.display = 'block';		
				muncipalityHeadConstruction(result);				
			}else{			
				localBodyArray = tehsilDetails.partyCorporationArray;
				muncipalityDIV = document.getElementById("corporationDiv");
				muncipalityDIV.style.display = 'block';		
				corporationHeadConstruction(result);
			}
			var listSize = result[0].totalMuncipalities-1;
			var rvStr = '';
			rvStr+='<table width="95%">';		
			for(var i in result)
			{		
				if(i%2==0){
					rvStr+='</tr>';
					rvStr+='<tr>';
				}
				
				if(i == listSize)
					rvStr+='<td colspan="2" style="vertical-align: top;">';
				else
					rvStr+='<td  style="vertical-align: top;">';		
				assignToPartyDataArray = new Array();
							
				rvStr+='<div id="allMuncipalitiesDetails'+i+'" style="padding:10px;width:100%;vertical-align:top;" class="datatableClass">';
				rvStr += '<table class="datatableClass" width="90%" style="background-color:#F3F6F7;margin-top:10px;margin-right:10px;">';
				rvStr += '<tr>';
				rvStr += '<th align="left">Muncipality Name :</th><td align="left">'+result[i].muncipalityName+'</td>'; 
				rvStr += '<th align="left">Total Wards :</th><td align="left">'+result[i].totalWards+'</td>';
				rvStr += '</tr>';
				rvStr += '<tr>';
				rvStr += '<th align="left">Total Voters :</th><td align="left">'+result[i].totalVoters+'</td>';
				rvStr += '<th align="left">Total Polled Votes :</th><td align="left">'+result[i].totalPolledVotes+'</td>';
				rvStr += '</tr>';
				rvStr += '</table>';	
				rvStr +='<div class="yui-skin-sam" style="margin-top:10px;margin-bottom:10px;">';
				rvStr +='<table>';
				rvStr +='<tr>';																					
				rvStr +='<td style="vertical-align:top;">';			
				if(electionType == muncipalityElectionType){
					rvStr +='<a href="javascript:{}" onclick="redirectMuncipalityCandidateLink('+ result[i].muncipalityId+','+result[i].latestMuncipalElectionYear+',\''+result[i].muncipalityName+'\')"  style="text-decoration:none;" class="candidateDetailsStyle">Show Candidate Details</a></td>';
				}else{
					rvStr +='<a href="javascript:{}" onclick="redirectCorporationCandidateLink('+ result[i].muncipalityId+','+result[i].latestMuncipalElectionYear+',\''+result[i].muncipalityName+'\')"  style="text-decoration:none;" class="candidateDetailsStyle">Show Candidate Details</a></td>';
				}			
				rvStr+='</td>';
				rvStr +='</tr>';
				rvStr +='<td></td>';
				rvStr +='<td></td>';
				rvStr +='<tr>';
				rvStr +='</tr>';
				rvStr +='<tr>';
				if(electionType == muncipalityElectionType){
					rvStr +='<td style="vertical-align: top;"> <div><div id="dataTable'+i+'"></div></div></td>';
				}else{
					rvStr +='<td style="vertical-align: top;"> <div><div id="corporationDataTable'+i+'"></div></div></td>';
				}
				rvStr +='</tr>';			
				rvStr +='</table></div>';
				rvStr+='</td>';
			}
			rvStr+='</table>';	
			muncipalityDIV.innerHTML = rvStr;	
			for(var i in result)
			{
				var localDataArr = new Array();
				for(var j in result[i].muncipalityVO)
				{					
					var muncipalObj =
					 {		
							partyName:result[i].muncipalityVO[j].partyName,
							participatedSeats:result[i].muncipalityVO[j].participatedSeats,
							seatsWonByParty:result[i].muncipalityVO[j].seatsWonByParty,
							percentageOfVotesWonByParty:result[i].muncipalityVO[j].percentageOfVotesWonByParty				
					 };
					localDataArr.push(muncipalObj);
				}	
				if(electionType == muncipalityElectionType){	
					initializeMuncipalResultsTableForParty('dataTable'+i,localDataArr,electionType) ;
				}else{
					initializeMuncipalResultsTableForParty('corporationDataTable'+i,localDataArr,electionType) ;
				}
			}
		}
		function errorMessageMuncipalitiesDiv(){		

			var muncipalityDIVHead = document.getElementById("muncipalitiesDivHead");
			var muncipalityDIVBody = document.getElementById("muncipalitiesDiv");
			
			if(muncipalityDIVHead && muncipalityDIVBody)
			{
				muncipalityDIVHead.innerHTML = '';
				muncipalityDIVBody.innerHTML = '';
				muncipalityDIVBody.style.display = 'none';		
			}
	
		}		
		function errorMessageCorporationDiv(){
			var corporationDIVHead = document.getElementById("corporationDivHead");
			var corporationDIVBody = document.getElementById("corporationDiv");
			
			if(corporationDIVHead && corporationDIVBody)
			{
				corporationDIVHead.innerHTML = '';
				corporationDIVBody.innerHTML = '';
				corporationDIVBody.style.display = 'none';
			}
		}
			
		
		function buildMandalVotesSharingData(results, jsObj)
		{
			var divEl = document.getElementById("madalwiseVotesRange");
			if(!divEl)
				return;
			var electionListLength = results.elections.length+2;
			var tehsilName = jsObj.tehsilName;
			var str = '';
		
			str += '<div id="votersShareData_main">';
			str += '<table><tr>';
			str += '<td><div style="height:10px;width:10px;border:1px solid #DDEB9B;background-color:#F6CECE;margin:10px;"></div></td>';
			str += '<td><P>Bye Elections</P></td>';
			str += '<td><div style="height:10px;width:10px;border:1px solid #F6CECE;background-color:#DDEB9B;margin:10px;"></div></td>';
			str += '<td><P>Alliance Results</P></td>';
			str += '</tr>';
			str += '</table>';
			str += '<table id="votesShareDetailsTable" width="100%" cellspacing="4" cellmargin="0">';
			str += '<tr>';
			str += '<td colspan="'+electionListLength+'" style="padding:0px;">';
			str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
			str+='			<tr>';
			str+='			<td width="2%" style="padding:0px;border:none;"> <img src="images/icons/electionResultsAnalysisReport/header_left.gif"></td>';
			str+='			<td width="98%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Share in '+tehsilName+' Mandal</span></div></td>';
			str+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
			str+='			</tr>';
			str+='		</table>';
			str += '</td>';
			str += '</tr>';
			str += '<tr>';
			str += '<th>Parties</th>';
			for(var i in results.elections)
				str += '<th>'+results.elections[i].name+'</th>';
			str += '<th>Range</th>';
			str += '</tr>';
			
			for(var j in results.allPartiesAllElectionResults)
			{
				str += '<tr>';
				if(results.allPartiesAllElectionResults[j].partyName != 'Others *')
				{	
					str += '<td align="center">'+results.allPartiesAllElectionResults[j].partyName+'</td>';
				
				for(var k in results.allPartiesAllElectionResults[j].electionWiseResults)
				{
					
					var info = results.allPartiesAllElectionResults[j].electionWiseResults[k];
					if(info.percentage == null || info.percentage == '--' || info.percentage == "-1")
					{
						str += '<td name="'+info.electionType+'"> </td>';
					}
					else if(info.electionType == 'Parliament' && info.electionYear == '2008' 
						|| info.electionType == 'Parliament' && info.electionYear == '2006')
					{
                        str += '<td align="center" name="'+info.electionType+'" style="background-color:#F6CECE;">'+info.percentage+'</td>';
					}
					else if(info.hasAlliance == "true")
							str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'</td>';
						else
							str += '<td align="center" name="'+info.electionType+'">'+info.percentage+'</td>';
					
				}
				str += '<td style="color:GoldenRod;font-weight:bold;" align="center">'+results.allPartiesAllElectionResults[j].range+'</td>';				
				}
				str += '</tr>';
			}

			str += '</table>';
			str += '</div>';
			divEl.innerHTML = str;			
		}

		function buildVotesSharingData(results)
		{
			
			votesShareData = results;
			var elmt = document.getElementById("partyVotesSharingDetailsDiv");

			if(!elmt)
				return;
			var electionListLength = results[0].electionList.length+2;

			var str = '';
			str += '<table><tr>';
			str += '<td><div style="height:10px;width:10px;border:1px solid #DDEB9B;background-color:#F6CECE;margin:10px;"></div></td>';
			str += '<td><P>Bye Elections</P></td>';
			str += '<td><div style="height:10px;width:10px;border:1px solid #F6CECE;background-color:#DDEB9B;margin:10px;"></div></td>';
			str += '<td><P>Alliance Results</P></td>';
			str += '<td><div style="margin:5px;"><font style="color:red;"> *</div></td>';
			str += '<td><P>Grouped Alliance Party Results </P></td>';
            str += '</tr></table>';
		
			str += '<div id="votersShareData_main">';
			str += '<center><table id="votesShareDetailsTable" width="100%" cellspacing="4" cellmargin="0">';
			str += '<tr>';
			str += '<td colspan="'+electionListLength+'" style="padding:0px;">';
			str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
			str+='			<tr>';
			str+='			<td width="2%" style="padding:0px;border:none;"> <img src="images/icons/electionResultsAnalysisReport/header_left.gif"></td>';
			str+='			<td width="98%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Shares</span></div></td>';
			str+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
			str+='			</tr>';
			str+='		</table>';
			str += '</td>';
			str += '</tr>';

			str += '<tr>';
			str += '<th>Parties</th>';
			for(var i in results[0].electionList)
				str += '<th>'+results[0].electionList[i].name+'</th>';
			str += '<th>Range</th>';
			str += '</tr>';
			
			for(var j in results)
			{
				str += '<tr>';
				str += '<td align="center">'+results[0].partiesList[j].name+'</td>';
				for(var k in results[j].electionWiseResults)
				{
					var info = results[j].electionWiseResults[k];
					
					if(info.percentage != "-1")
					{
						 //for Bye Election Results
						if(info.electionType == 'Parliament' && info.electionYear == '2008' 
							|| info.electionType == 'Parliament' && info.electionYear == '2006')
                           str += '<td align="center" name="'+info.electionType+'" style="background-color:#F6CECE;">'+info.percentage+'</td>';
						else if(info.alliancRes == true){
							str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'<font style="color:red;"> *</td>'; 
						} 
                        else if(info.hasAlliance == "true")
							str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'</td>';
						else if(info.hasAlliance == "false")
							str += '<td align="center" name="'+info.electionType+'">'+info.percentage+'</td>';
					}
					else
						str += '<td name="'+info.electionType+'"><div style="visibility:hidden;">NA</div></td>';
				}
				str += '<td align="center" style="color:GoldenRod;font-weight:bold;">'+results[j].range+'</td>';
				str += '</tr>';
			}

			str += '</table></center>';
			str += '</div>';
			str += '<div> ';
			str += '<center><table>';
			str += '<tr>';
			str += '<td>View :</td>';
			str += '<td>';

			str += '<input type="checkbox" name="elecType" checked="checked" value="ALL" onclick="showSelectedColoumn(this.value)"/>ALL';
			str += '<input type="checkbox" name="elecType" value="AC" onclick="showSelectedColoumn(this.value)"/>AC';
			str += '<input type="checkbox" name="elecType" value="PC" onclick="showSelectedColoumn(this.value)"/>PC';
			str += '<input type="checkbox" name="elecType" value="MPTC" onclick="showSelectedColoumn(this.value)"/>MPTC';
			str += '<input type="checkbox" name="elecType" value="ZPTC" onclick="showSelectedColoumn(this.value)"/>ZPTC';	

			str += '</td>';
			str += '</tr>';
			str += '</table></center>';
			str += '</div>';
			elmt.innerHTML = str;
		}
		
		function getElectionType(type)
		{
			var ecType;

			if(type == "Assembly")
				ecType = "AC";
			else if(type == "Parliament")
				ecType = "PC";
			else if(type == "MPTC")
				ecType = "MPTC";
			else if(type == "ZPTC")
				ecType = "ZPTC";

			return ecType;
		}

		function checkExistingValueInArray(val,array)
		{
			
			var status=false;
			for(var i=0; i<array.length;i++ )
			{ 
				if(array[i]==val)
					status=true; 
			}	
			return status;
		}

		//siva
		function showSelectedColoumn(checkedValue)
		{
			var docelements = document.getElementsByTagName('input'); 		
			var electTypeCheckBoxElmts = new Array();
			var electypeSelectedElmts = new Array();
			
			for(var i =0; i<docelements.length; i++)
			{
				if(docelements[i].type=="checkbox" && docelements[i].name=="elecType")
				{	
					electTypeCheckBoxElmts.push(docelements[i]);
				}
			}
						
			if(checkedValue == "ALL")
			{
				partyVotesSharing();
				return;
			}
			else if(checkedValue == "AC" || checkedValue == "PC" || checkedValue == "MPTC" || checkedValue == "ZPTC")
			{
				for(var i =0; i<electTypeCheckBoxElmts.length; i++)
				{
					if(electTypeCheckBoxElmts[i].value == "ALL")
					{
						electTypeCheckBoxElmts[i].checked = false;
						continue;
					}
					else if(electTypeCheckBoxElmts[i].checked == true)
					{
						electypeSelectedElmts.push(electTypeCheckBoxElmts[i].value);
					}
				}
			}
			
			
			var elmt = document.getElementById("votersShareData_main");
			if(!elmt)
				return;
			
			var electionListLength = votesShareData[0].electionList.length+2;
			var ecType = getElectionType(checkedValue);

			var str = '';
			str += '<center><table id="votesShareDetailsTable" width="100%" cellspacing="4" cellmargin="0">';
			str += '<tr>';
			str += '<td colspan="'+electionListLength+'" style="padding:0px;">';
			str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
			str+='			<tr>';
			str+='			<td width="2%" style="padding:0px;border:none;"> <img src="images/icons/electionResultsAnalysisReport/header_left.gif"></td>';
			str+='			<td width="98%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Shares</span></div></td>';
			str+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
			str+='			</tr>';
			str+='		</table>';
			str += '</td>';
			str += '</tr>';
			
			str += '<tr>';
			str += '<th>Parties</th>';
			for(var i in votesShareData[0].electionList)
			{
				var electype = votesShareData[0].electionList[i].name.substring(votesShareData[0].electionList[i].name.indexOf('-')+3, votesShareData[0].electionList[i].name.length);
				electype = electype.replace(".", "");
				var flag = checkExistingValueInArray(electype,electypeSelectedElmts);
				if(flag)
					str += '<th>'+votesShareData[0].electionList[i].name+'</th>';
			}
			str += '<th>Range</th>';
			str += '</tr>';
			
			for(var j in votesShareData)
			{
				str += '<tr>';
				str += '<td align="center">'+votesShareData[0].partiesList[j].name+'</td>';
				for(var k in votesShareData[j].electionWiseResults)
				{
					var info = votesShareData[j].electionWiseResults[k];
					var ecType = getElectionType(info.electionType);

					if(!checkExistingValueInArray(ecType,electypeSelectedElmts))
						continue;
					if(info.percentage != "-1")
					{
						//for Bye Election Results
						if(info.electionType == 'Parliament' && info.electionYear == '2008' 
							|| info.electionType == 'Parliament' && info.electionYear == '2006')
                           str += '<td align="center" name="'+info.electionType+'" style="background-color:#F6CECE;">'+info.percentage+'</td>';
                        else if(info.alliancRes == true){
							str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'<font style="color:red;"> *</td>'; 
						}
                        else if(info.hasAlliance == "true")
							str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'</td>';
						else if(info.hasAlliance == "false")
							str += '<td align="center" name="'+info.electionType+'">'+info.percentage+'</td>';
					}
					else
						str += '<td name="'+info.electionType+'"><div style="visibility:hidden;">NA</div> </td>';
				}
				str += '<td style="color:GoldenRod;font-weight:bold;" align="center">'+votesShareData[j].range+'</td>';
				str += '</tr>';
			}

			str += '</table></center>';

			elmt.innerHTML = str;
		}

		function showChartData(results)
		{
			var selectboxElmtDiv = document.getElementById("selectLocationOptions");
			var checkboxElmtDiv = document.getElementById("inputSelectionCriteria");
			var selectOptionsSelectButtonElmt = document.getElementById("selectOptionsSelectButton");

			if(selectboxElmtDiv && checkboxElmtDiv)
			{
				selectboxElmtDiv.style.display = 'none';
				checkboxElmtDiv.style.display = 'none';
				selectOptionsSelectButtonElmt.style.display = 'block';
				
				selectOptionsSelectButtonElmt.innerHTML = '<input type="button" value="Select Option" onclick="displaySelectionCriteria()">';
			}

			chartName = results.chartName;

			var mainDivElmt = document.getElementById("crossVotingData_Graph_Div");
			var divEl = document.getElementById("constitutencyResultsChart");
			mainDivElmt.style.display = 'block';

			divEl.innerHTML = '';
			divEl.innerHTML = '<center><img src="charts/'+chartName+'" border="none" /></center>';
		}
		
		function displaySelectionCriteria()
		{
			var selectboxElmtDiv = document.getElementById("selectLocationOptions");
			var checkboxElmtDiv = document.getElementById("inputSelectionCriteria");
			var selectOptionsSelectButtonElmt = document.getElementById("selectOptionsSelectButton");

			if(selectboxElmtDiv && checkboxElmtDiv)
			{
				selectboxElmtDiv.style.display = 'block';
				checkboxElmtDiv.style.display = 'block';
				selectOptionsSelectButtonElmt.style.display = 'none';				
			}
		}

		function getZptcPartyDetails(elecYear){
			zptcElectionYear = elecYear;
			constituencyTYPE = assemblyElectionType;
			var jsObj = {
					constituencyType: assemblyElectionType,
					constituencyId:constituencyIdGlobal,
					electionYear:elecYear,
					task:"getZptcElectionResults"
				};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
			callAjax(jsObj, url);
		}

		function getMptcPartyDetails(elecYear){
			mptcElectionYear = elecYear;
			var jsObj = {
					constituencyType: assemblyElectionType,
					constituencyId: constituencyIdGlobal,
					electionYear:elecYear,
					task:"getMptcElectionResults"
				};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
			callAjax(jsObj, url);
		}

		function redirectZptcCandidateLink(){												
			 var browser1 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyIdGlobal+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser1.focus();
		}

		function redirectMptcCandidateLink(){
			 var browser2 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyIdGlobal+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser2.focus();
		}
		
		function redirectMuncipalityCandidateLink(muncipalityId,latestMuncipalElectionYear,name){
			var browser3 = window.open("<s:url action="muncipalElectionReportAction.action"/>?muncipalityId="+muncipalityId+"&muncipalityElectionType="+muncipalityElectionType+"&name="+name+"&muncipalityElectionId="+muncipalityElectionId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
			browser3.focus();
		}
		
		function redirectCorporationCandidateLink(corporationId,latestCorporationElectionYear,name){
			var browser4 = window.open("<s:url action="muncipalElectionReportAction.action"/>?muncipalityId="+corporationId+"&muncipalityElectionType="+corporationElectionType+"&name="+name+"&muncipalityElectionId="+corporationElectionTypeId+"&electionYear="+latestCorporationElectionYear,"browser4","scrollbars=yes,height=670,width=1170,left=200,top=200");
			browser4.focus();
		}

		function hideZptcDiv(){
			var imgElmt = document.getElementById("zptcPartyTrendsDetailsDiv");
			var electionDetails="";
			electionDetails +="<br/>";
			electionDetails +="<b>Zptc Data is not available.</b>";			
			imgElmt.innerHTML = electionDetails;		

			 var candLink = document.getElementById("zptcCandidateLink");
			 var candidateLink="";
			 candLink.innerHTML = candidateLink;
		}
		function hideMptcDiv(){
			var imgElmt = document.getElementById("mptcPartyTrendsDetailsDiv");
			var electionDetails="";
			electionDetails +="<br/>";
			electionDetails +="<b>Mptc Data is not available.</b>";			
			imgElmt.innerHTML = electionDetails;

			 var candLink = document.getElementById("mptcCandidateLink");
			 var candidateLink="";
			 candLink.innerHTML = candidateLink;
		}				
		function getMandalVotingTrendz(distId,constId,constName)
		{
			var jsObj=
			{
					districtId:distId,
					constituencyId:constId, 
					constiName:constName,
					task:"getMandalVotingTrendz"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyVotingTrendzAjaxAction.action?"+rparam;

			callAjax(jsObj, url);
		}
		
		function setValuesForMandalVotingTrendz(value,text)
		{
			var radioValue = '';
			var cursorImgElmt = document.getElementById('cursorImg');
			if(cursorImgElmt)
				cursorImgElmt.style.display = 'block';

			var elements = document.getElementsByTagName('input'); 

			for(var i=0;i<elements.length;i++)
			{
				if(elements[i].type=="radio" && elements[i].name=="districtRadio" && elements[i].checked==true)
					radioValue = elements[i].value;
			}
			
			getMandalVotingTrendz(radioValue,value,text);
			constituencyIdGlobal = value;

			getConstituencyOverViewResult(value,text);
			partyVotesSharing();
			getMuncipalElections();  
			getCorporationElections(); 
			
		}

		function getConstituenciesInfo(distId,index)
		{
			var obj = districtsInfo[index];

			var elmt = document.getElementById("constSelectElmt");
			if(!elmt)
				return;

			var str = '';
			str += '<select class="selectWidth" onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
			str += '<option value="0">Select</option>';
			for(var j in obj.constituencies)
			{
				str += '<option value="'+obj.constituencies[j].constId+'"> '+obj.constituencies[j].constName+' </option>';
			}	
			str += '</select>';
			
			elmt.innerHTML = str;
		}
		
		function openwin(mandalId,name, electionType,electionYear,electionId){					
			var brow1 = window.open("<s:url action="townshipElectionResultsAction"/>?mandalId="+mandalId+"&electionId="+electionId+"&mandalName="+name+"&electionType="+electionType+"&electionYear="+electionYear+"&windowTask=includeVotingTrendz","brow1","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			brow1.focus();
		}

		function buildMandalsVotingTrendz()
		{
			var distObj = null;
			var elmt = document.getElementById("votingTrendzInfoMain");

			if(!elmt)
				return;

			var str='';
			str += '<div id="selectLocationOptions">';
			str += '<div id="districtsInfoRadioElmtDiv">';
			str += '<table width="60%">';
			str += '<tr>';
			str += '<th width="30%" align="left">';
			str += 'Select District : ';
			str += '</th>';
			str += '<td>';
			for(var i in districtsInfo)
			{
				if(districtsInfo[i].districtId == ${districtId})
				{
					str += '<input type="radio" checked="checked" name="districtRadio" value="'+districtsInfo[i].districtId+'" onclick="getConstituenciesInfo(this.value,\''+i+'\')"/> '+districtsInfo[i].districtName;
					distObj = districtsInfo[i];
				}
				else
					str += '<input type="radio" name="districtRadio" value="'+districtsInfo[i].districtId+'" onclick="getConstituenciesInfo(this.value,\''+i+'\')"/> '+districtsInfo[i].districtName;

			}
			str += '</td>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';
			
			str += '<div id="constituenciesInfoSelectElmtDiv">';
			if(distObj)
			{
				str += '<table  width="60%">';
				str += '<tr>';
				str += '<th width="30%" align="left">';
				str += 'Select Constituency : ';
				str += '</th>';
				str += '<td>';
				str += '<div id="constSelectElmt"><select class="selectWidth" onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
				for(var j in distObj.constituencies)
				{
					if(distObj.constituencies[j].constId == ${constiId})
						str += '<option value="'+distObj.constituencies[j].constId+'" selected="selected"> '+distObj.constituencies[j].constName+' </option>';
					else
						str += '<option value="'+distObj.constituencies[j].constId+'"> '+distObj.constituencies[j].constName+' </option>';
				}	
				str += '</select></div>';
				str += '</td>';
				str += '<td><img id="cursorImg" style="display:none;" src="images/icons/search.gif"/></td>';	
				str += '</table>';
				str += '</div>';
			}
			str += '</div>';
			str += '<div id ="inputSelectionCriteria">';
				
			str += '</div>';
			str += '<div id="selectOptionsSelectButton" style="margin-bottom:10px;padding:10px;text-align:right"></div>';
            str += '<div id="constituencyMainDetails_Div" style="margin-bottom:10px;padding:10px;text-align:right"></div>';
			str += '<div id="crossVotingData_Graph_Div" style="display:none;">';
			str += '<table width="100%">';
			str += '<tr>';
			str += '<td width="50%" style="vertical-align:top;"><div id="partyVotesSharingDetailsDiv"></div></td>';
			str += '</tr>';
			str += '<tr>';
			str += '<td width="50%" style="vertical-align:top;"><div id="constitutencyResultsChart"></div></td>';
			str += '</tr>';			
			str += '</table>';
			str += '</div>';

			str += '<div id="constituencyOverViewDiv"></div>';	
			str += '<div id="crossVotingDetailsDiv" style="margin:10px;">';
			str += '<div id="crossVotingHeadingDiv"> <P style="color:#247CD4;font-size:12px;font-weight:bold;">Non Participating Parties & Cross Voting Results</P></div>';	
			str+='<TABLE border="0" width="100%">';
			str+='<TR>';
			str+='<TD valign="top"><DIV id="nonParticipatingDiv"></TD>';
			str+='<TD valign="top"><DIV id="crossVotingDiv"></TD>';
			str+='</TR>';
			str+='</TABLE>';
			str+='</div>';
			str += '<div id="overViewHeadingDiv" style="padding-left:10px;"></div>';
			str += '<div id="constitutencyMandalWiseResultsChart"></div>';
			str += '<div id="mandalVotingTrendzDataDiv_head">Voting Trendz in Constituency</div>';
			str += '<div id="mandalVotingTrendzDataDiv">';
			str += '<div id="mandalVotingTrendzDataDiv_body" class="yui-skin-sam">';
			str += '	<div id="allPartiesResultsChartsPanel"></div>';
			str += '	<div id="mandalsListInConstituency"></div>';	
			str += '	<div id="mandalVotingTrendzData"></div>';			
			str += '</div>';
			
			str += '</div>';	
			
			elmt.innerHTML = str;

			getMandalVotingTrendz('${districtId}','${constiId}','${constiName}');
		}

		function displayAllPartiesChart()
		{			
			var graphStr = '';
			graphStr += '<HTML><HEAD><TITLE>All Parties Election Results in ${constiName} Constituency</TITLE></HEAD>';
			graphStr += '<body>';
			graphStr += '<table>';
			graphStr += '<tr>';
			for(var graph in allPartiesCharts)
			{
				graphStr += '<td>';
				graphStr += '<img src="charts/'+allPartiesCharts[graph]+'"/>';
				graphStr += '</td>';

				if(graph == 0)
					continue;
				if(graph % 3 == 0)
					graphStr += '</tr><tr>';
			}
			graphStr += '</tr>';
			graphStr += '</table>';
			graphStr += '</BODY></HTML>';
				
				

			var allPartiesChartsWindow = window.open("","allPartiesChartsWindow","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			allPartiesChartsWindow.focus();
			allPartiesChartsWindow.document.open("text/html", "replace");
			allPartiesChartsWindow.document.write(graphStr);			
			allPartiesChartsWindow.document.close();

			/*var allPartiesPanel = new YAHOO.widget.Panel("allPartiesResultsChartsPanel", {
                 width: "800", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: true, 
                 visible: true, 
				 x:'200',
				 y:'800',
                 draggable: true
			   });
			allPartiesPanel.setHeader("All Parties Results");
			allPartiesPanel.setBody(graphStr);
			allPartiesPanel.render();*/
		}

		function getResultsForSelectedElection()
		{
			var inputSelectionErrorEl = document.getElementById("inputSelectionError");
			var partyCheckboxEls = document.getElementsByName("partywiseCheckBox");
			var electionTypeCheckboxEls = document.getElementsByName("electionCheckBox");
			var allianceCheckboxEls = document.getElementById("allianceChkBox");
			var allainceVal;
			var selectedPartiesIds = new Array();
			var selectedElectionTypesYears = new Array();
			var selectedPartiesCount, electionTypesCount;		
			inputSelectionErrorEl.innerHTML = '';
			if(allianceCheckboxEls.checked == true)
			{
				allainceVal = true;	
			} else 
				{
					allainceVal = false;
				}
			for(var i=0; i < partyCheckboxEls.length; i++)
			{
				if(partyCheckboxEls[i].checked == true)
				{
					selectedPartiesIds.push(partyCheckboxEls[i].id);
				}
			}			
			
			for(var j = 0; j < electionTypeCheckboxEls.length;j++)
			{
				if(electionTypeCheckboxEls[j].checked == true)
				{
					var checkedVal = electionTypeCheckboxEls[j].id;
					var jsObj={
							type: checkedVal.substring(checkedVal.indexOf('_')+1,checkedVal.length),
							year: checkedVal.substring(0,checkedVal.indexOf('_'))
							};
					selectedElectionTypesYears.push(jsObj);
				}	
			}

			selectedPartiesCount =  selectedPartiesIds.length;
			electionTypesCount = 	selectedElectionTypesYears.length;
			if(selectedPartiesCount == 0 && electionTypesCount == 0)
			{
				inputSelectionErrorEl.innerHTML = '';
				inputSelectionErrorEl.innerHTML = 'Please Select Party and Election Type';
				return;
			}
			if(electionTypesCount == 0)
			{
				inputSelectionErrorEl.innerHTML = '';
				inputSelectionErrorEl.innerHTML = 'Please Select Election Type';
				return;
			}
			if(selectedPartiesCount == 0)
			{
				inputSelectionErrorEl.innerHTML = '';
				inputSelectionErrorEl.innerHTML = 'Please Select Party';
				return;
			}
			var jsObj = {
					
					constituencyName: constituencyName,
					constituencyId: constituencyIdGlobal,
					partiesArr: selectedPartiesIds,
					electionTypeArr: selectedElectionTypesYears,
					task: "constituencyResults",
					alliances: allainceVal 
					};
			
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyVotingTrendzChartAjaxAction.action?"+rparam;
			callAjax(jsObj, url);
			
		}
		
		function buildMandalVotingTrendzData(jsObj,resultsData)
		{
			
			var headElmt = document.getElementById("mandalVotingTrendzDataDiv_head");
			var bodyElmt = document.getElementById("mandalVotingTrendzData");
			var graphElmt = document.getElementById("mandalDetailsChart_body");
			var mandalsListElmt = document.getElementById("mandalsListInConstituency");
			var mandalvotersDetailsEl = document.getElementById("mandalVotesShare");
			var crossVotingDetailsDivEl = document.getElementById("crossVotingDiv");
			var nonParticipatingDivEl = document.getElementById("nonParticipatingDiv");
			var crossVotingResults = resultsData.biElectionResultsMainVO[0].crossVotingResults;
			var nonParticipatingParties = resultsData.biElectionResultsMainVO[0].nonPartiParties;
			var mandalwiseVotersShare = resultsData.constituencyVO.assembliesOfParliamentInfo;
			var constituencyId = jsObj.constituencyId;
			var crossVotingResultsContent = '';
			crossVotingResultsContent+='<TABLE width="100%" class="participatingPartiestable" border="0">';
			crossVotingResultsContent+='	<TR>';
			crossVotingResultsContent+='	<TD colspan="6" style="padding:0px;border:none;">';
			crossVotingResultsContent+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
			crossVotingResultsContent+='			<tr>';
			crossVotingResultsContent+='			<td width="3%" style="padding:0px;border:none;"> <img src="images/icons/electionResultsAnalysisReport/header_left.gif"></td>';
			crossVotingResultsContent+='			<td width="97%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan">Cross Voting Details</span></div></td>';
			crossVotingResultsContent+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
			crossVotingResultsContent+='			</tr>';
			crossVotingResultsContent+='		</table>';
			crossVotingResultsContent+='	</TD>';
			crossVotingResultsContent+='	</tr>';
			crossVotingResultsContent+='	<TR>';
			crossVotingResultsContent+='		<TH style="color:#18325A;">Party</TH>';
			crossVotingResultsContent+='		<TH style="color:#18325A;">VotesEarned(A)</TH>';
			crossVotingResultsContent+='		<TH style="color:#18325A;">Votes%(A)</TH>';
			crossVotingResultsContent+='		<TH style="color:#18325A;">VotesEarned(P)</TH>';
			crossVotingResultsContent+='		<TH style="color:#18325A;">Votes%(P)</TH>';
			crossVotingResultsContent+='		<TH style="color:#18325A;">Difference</TH>';
			crossVotingResultsContent+='	</TR>';			
			for(var d in crossVotingResults)
			{
				    if(d == crossVotingResults.length-1)
				    {
                    crossVotingResultsContent+='<TR>';
					crossVotingResultsContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+crossVotingResults[d].partyName+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:GoldenRod;">'+crossVotingResults[d].votesEarned+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+crossVotingResults[d].percentage+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+crossVotingResults[d].ballotVotes+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+crossVotingResults[d].ballotVotesPercentage+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+crossVotingResults[d].diffPercent+'</TD>';							
					crossVotingResultsContent+='</TR>';
					}

					else
				    {
					crossVotingResultsContent+='<TR>';
					crossVotingResultsContent+='<TD align="center" style="color:#73787E;">'+crossVotingResults[d].partyName+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:#73787E;">'+crossVotingResults[d].votesEarned+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:#73787E;">'+crossVotingResults[d].percentage+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:#73787E;">'+crossVotingResults[d].ballotVotes+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:#73787E;">'+crossVotingResults[d].ballotVotesPercentage+'</TD>';
					crossVotingResultsContent+='<TD align="center" style="color:#73787E;">'+crossVotingResults[d].diffPercent+'</TD>';							
					crossVotingResultsContent+='</TR>';
					}
								
			}
			crossVotingResultsContent+='</TABLE>';
			crossVotingDetailsDivEl.innerHTML = crossVotingResultsContent;

			var nonParticipatingContent = '';			
			nonParticipatingContent+='<TABLE width="100%"  class="participatingPartiestable" border="0">';		
			nonParticipatingContent+='	<TR>';
			nonParticipatingContent+='	<TD colspan="6" style="padding:0px;border:none;">';
			nonParticipatingContent+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
			nonParticipatingContent+='			<tr>';
			nonParticipatingContent+='			<td width="3%" style="padding:0px;border:none;"> <img src="images/icons/electionResultsAnalysisReport/header_left.gif"></td>';
			nonParticipatingContent+='			<td width="97%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan">Non Participating Parties Details</span></div></td>';
			nonParticipatingContent+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
			nonParticipatingContent+='			</tr>';
			nonParticipatingContent+='		</table>';
			nonParticipatingContent+='	</TD>';
			nonParticipatingContent+='	</tr>';
			nonParticipatingContent+='<TR>';
			nonParticipatingContent+='<TH style="color:#18325A;">Party</TH>';
			nonParticipatingContent+='<TH style="color:#18325A;">VotesEarned(A)</TH>';
			nonParticipatingContent+='<TH style="color:#18325A;">Votes%(A)</TH>';
			nonParticipatingContent+='<TH style="color:#18325A;">VotesEarned(P)</TH>';
			nonParticipatingContent+='<TH style="color:#18325A;">Votes%(P)</TH>';			
			nonParticipatingContent+='</TR>';			
			for(var y in nonParticipatingParties)
			{       if(y == nonParticipatingParties.length-1)
				    {
				    nonParticipatingContent+='<TR>';
					nonParticipatingContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+nonParticipatingParties[y].partyName+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+nonParticipatingParties[y].votesEarned+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+nonParticipatingParties[y].percentage+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+nonParticipatingParties[y].ballotVotes+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:GoldenRod;font-weight:bold;">'+nonParticipatingParties[y].ballotVotesPercentage+'</TD>';										
					nonParticipatingContent+='</TR>';
				       
			        }
					else
				    {
					nonParticipatingContent+='<TR>';
					nonParticipatingContent+='<TD align="center" style="color:#73787E;">'+nonParticipatingParties[y].partyName+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#73787E;">'+nonParticipatingParties[y].votesEarned+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#73787E;">'+nonParticipatingParties[y].percentage+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#73787E;">'+nonParticipatingParties[y].ballotVotes+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#73787E;">'+nonParticipatingParties[y].ballotVotesPercentage+'</TD>';										
					nonParticipatingContent+='</TR>';		
					}
			}
			nonParticipatingContent+='</TABLE>';			
			nonParticipatingDivEl.innerHTML = nonParticipatingContent;
			
				
			var mdlwiseVotersDetailsStr = '';
			var mdlwiseVotersDetailsStr = '<P style="color:#247CD4;font-size:12px;font-weight:bold;">Mandalwise Voters Share in Constituency</B></P>';
			mdlwiseVotersDetailsStr+='<Table width="100%" border="3" cellpadding="5" cellspacing="5">';
			mdlwiseVotersDetailsStr+='<TR>';			
			for (var x in mandalwiseVotersShare[0].votersInfoForMandalVO)
			{
				
				mdlwiseVotersDetailsStr+='<TD>';
				mdlwiseVotersDetailsStr+='	<TABLE width="100%" class="mandalVotesShareTable">';
				mdlwiseVotersDetailsStr+='		<TR>';
				mdlwiseVotersDetailsStr+='			<TH>Mandal</TH>';
				mdlwiseVotersDetailsStr+='			<TH>%</TH>';
				mdlwiseVotersDetailsStr+='			<TH>Analyze</TH>';
				mdlwiseVotersDetailsStr+='		</TR>';
				mdlwiseVotersDetailsStr+='			<TD><A href="javascript:{}" title="Displays Parties Performane in All Elections Mandalwise" onclick="getMandalwiseVotesShare('+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalId+','+constituencyId+',\''+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName+'\')">'+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName+'</A></TD>';
				mdlwiseVotersDetailsStr+='			<TD>'+mandalwiseVotersShare[0].votersInfoForMandalVO[x].percent+' %</TD>';
				mdlwiseVotersDetailsStr+='			<TD><A href="javascript:{}" title="Displays Parties Performane in All Elections Revenue Mandalwise" onclick="openwin('+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalId+',\''+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName+'\',\'Assembly\',\'2009\',\'3\')">Analyze</A></TD>';
				mdlwiseVotersDetailsStr+='		</TR>';
				mdlwiseVotersDetailsStr+='	</TABLE>';
				mdlwiseVotersDetailsStr+='</TD>';				
				if (x != 0 && x % 2 == 0)
					{
					
					mdlwiseVotersDetailsStr += '</tr><tr>';
					}
						
			}
			mdlwiseVotersDetailsStr+='</TR>';
			mdlwiseVotersDetailsStr+='</Table>';
			mandalvotersDetailsEl.innerHTML = mdlwiseVotersDetailsStr;
			var results = resultsData.biElectionResultsMainVO;
			//Hiding busy cursor Image
			var cursorImgElmt = document.getElementById('cursorImg');
			if(cursorImgElmt)
				cursorImgElmt.style.display = 'none';

			var inputEl = document.getElementById("inputSelectionCriteria");
			inputEl.innerHTML = '';

			var constChartEl = document.getElementById("constitutencyResultsChart");
			constChartEl.innerHTML = '';

			var mainHeadEl = document.getElementById("mainHead");
			mainHeadEl.innerHTML = '';
			mainHeadEl.innerHTML = 'Voting Trendz in '+jsObj.constiName+' Constituency';

			var str1= '';
			str1 += '<P><b> Select a Party and Election Type to View Results </b></P>';
			str1 += '<div id ="inputSelectionError"></div>';
			str1 += '<table>';
			str1 += '<tr>';
			str1 += '<th align="left">Party:</th>';
			for(var k in tehsilElections.staticParties)
			{
				str1 += '<td><INPUT type="checkbox" name="partywiseCheckBox" id='+tehsilElections.staticParties[k].partyName+' />'+tehsilElections.staticParties[k].partyName+'</td>';
			}
			str1 += '</tr>';
			str1 += '<tr>';
			str1 += '<th align="left" rowspan="2" valign="top">Election Type:</th>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2009_Assembly"  />2009 Assembly</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2004_Assembly"  />2004 Assembly</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2009_Parliament"  />2009 Parliament</td>';			
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2008_Parliament"  />2008 Parliament<font style="color:red;"> *</font></td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2006_Parliament"  />2006 Parliament<font style="color:red;"> *</font></td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2004_Parliament"  />2004 Parliament</td>';
		    str1 += '</tr>';
			str1 += '<tr>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2006_MPTC"  />2006 MPTC</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2001_MPTC" />2001 MPTC</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2006_ZPTC" />2006 ZPTC</td>';
			str1 += '<td colspan="4"><INPUT type="checkbox" name="electionCheckBox" id="2001_ZPTC" />2001 ZPTC</td>';
			str1 += '</tr>';
			str1 += '</tr>';		    
			str1 += '</table>';
			str1 += '<div style="text-align:right;width:100%;">';
			str1 += '<table width="100%">';
			str1 += '<tr>';
			str1 += '<th align="left"><font style="color:red;"> *</font> Indicates Bye Elections</th>';			
			str1 += '</td align="right">';
			str1 += '<INPUT type="checkbox" name="allianceCheckBox" id="allianceChkBox" />Include Alliances';
			str1 += '<input type="button" value="Select All" onclick="selectAllPartiesNYears()">';
			str1 += '<input type="button" value="De-select All" onclick="DeselectAllPartiesNYears()">';
			str1 += '<INPUT type="button" id="getResults" onclick="getResultsForSelectedElection()" value="Show Results" />';
			str1 += '</td>';
			str1 += '</tr>';
			str1 += '</table>';			
			str1 += '</div>';
			inputEl.innerHTML = str1;
			
			
			//Rendering Mandal voting trendz data
			var chartDetailsObjArr = resultsData.chartsListForElectionTypes;
			var str = '';
			str += '<table width="100%" height="150" cellspacing = "5" cellpadding="5">';
			var mandalIdsFlag = 0;
			mandalIds = new Array();
			for(var i in results)
			{	
				for(var j in results[i].biElectionResultsVO)
				{
					
					var electionHeaderLength = (results[i].biElectionResultsVO[j].partysList.length*2)+2;
					var partyHeaderLength = results[i].biElectionResultsVO[j].partysList.length*2;
					str += '<tr>';
					str += '<td width="100">';
					for(var z in chartDetailsObjArr)
					{
						var chartDetailsObj = chartDetailsObjArr[z]; 
						var electionType = chartDetailsObj.electionType
						var electionYear = chartDetailsObj.electionYear
						var chartName = chartDetailsObj.chartName;	
						if(electionType == results[i].biElectionResultsVO[j].electionType && electionYear ==  results[i].biElectionResultsVO[j].electionYear)
						str += '<img src="charts/'+chartName+'" />';
							
					}
					str += '</td>';
					str += '<td style="vertical-align:top;padding-bottom:20px;">';
					str += '<table width="100%" class="participatingPartiestable" border="0">';
					str += '<tr>';
					str += '<th colspan="'+electionHeaderLength+'" align="left">'+results[i].biElectionResultsVO[j].electionType+' - '+results[i].biElectionResultsVO[j].electionYear+'</th>';
					str += '</tr>';
					str += '<tr>';
					str += '<th rowspan="3">Mandal</th>';
					str += '<th rowspan="3">Constituency</th>';
					str += '<th colspan="'+partyHeaderLength+'" align="center">Party</th>';
					str += '</tr>';
					str += '<tr>';
					for(var p in results[i].biElectionResultsVO[j].partysList)
					{
						str += '<th colspan="2">'+results[i].biElectionResultsVO[j].partysList[p].name+'</th>';
					}
					str += '</tr>';
					str += '<tr>';
					for(var q in results[i].biElectionResultsVO[j].partysList)
					{
						str += '<th>V*</th><th>%</th>';
					}
					str += '</tr>';
					for(var k in results[i].biElectionResultsVO[j].electionResultsForMandal)
					{
						
						var info = results[i].biElectionResultsVO[j].electionResultsForMandal[k];
						str += '<tr>';
						if(info.partyElecResultsInConstituency.length == 0)
						{
							var cols = partyHeaderLength+1;
							str += '<th><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs"  onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';
							for(var colsno = 0;colsno < cols; colsno++)
								str += '<td> -- </td>';	
						}
						else
						{
							if(mandalIdsFlag == 0)
								mandalIds.push(info.mandalId);
							str += '<th rowspan="'+info.partyElecResultsInConstituency.length+'"><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs" onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';				
							for(var l in info.partyElecResultsInConstituency)
							{
								str += '<th style="color:#73787E;width:150px;font-size:10px;">'+info.partyElecResultsInConstituency[l].constituencyName.toUpperCase()+'</th>';
												
								for(var m in info.partyElecResultsInConstituency[l].partyElecResults)
								{
									var data = info.partyElecResultsInConstituency[l].partyElecResults[m];	
									if(data.votesEarned == 0)
										str += '<td> -- </td>';
									else
										str += '<td>'+data.votesEarned+'</td>';

									str += '<td>'+data.percentage+'</td>';					
								}
								
								str+='</tr><tr>';
							}
						}
						str += '</tr>';
					}

					mandalIdsFlag++;
					
					if(results[i].biElectionResultsVO[j].electionType == "Assembly" && results[i].biElectionResultsVO[j].electionYear == "2009")
					{	
						str += '<tr>';
						str += '<th colspan="2">Postal Ballot Votes</th>';
						for(var sum in results[i].biElectionResultsVO[j].partyResultsSum)
						{
							if(results[i].biElectionResultsVO[j].partyResultsSum[sum].ballotVotes != null)
								{
									str += '<td><font style="color:#62662B;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].ballotVotes+'</font></td><td><font style="color:#62662B;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].ballotVotesPercentage+'</font></td>';
								} else 
									str += '<td><font style="color:#62662B;font-weight:bold;">--</font></td><td><font style="color:#62662B;font-weight:bold;">--</font></td>';
						}
						
						str += '</tr>';
					}
					str += '<tr>';
					str += '<th colspan="2">Total</th>';
					for(var sum in results[i].biElectionResultsVO[j].partyResultsSum)
					{
						str += '<td><font style="color:GoldenRod;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].votesEarned+'</font></td><td><font style="color:GoldenRod;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].percentage+'</font></td>';
					}					
					str += '</tr>';
					str += '</table>';
					str += '</td>';
					str += '</tr>';
					str += '<tr><td colspan="2"><hr/></td></tr>';
				}
				
			}
			str += '</table>';

			bodyElmt.innerHTML = str;
			//getZptcPartyDetails(tehsilElections.zptcElectionYears[0].value);
			//getMptcPartyDetails(tehsilElections.mptcElectionYears[0].value);
			
			getConstituencyResults("2009");
			//To build Graphs for 2009 and 2004 Mandals Wise Parties Results 
		}

		function getMandalwiseVotesShare(tehsilId,constituencyId, tehsilName)
		{
			var jsObj = {
					tehsilId: tehsilId,
					tehsilName: tehsilName,
					task:"getMandalVotesShare",
					chartHeight:400,
					chartWidth:800
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/votesSharingInMandalAjaxAction.action?"+rparam;
				callAjax(jsObj, url);
		}
		
		function DeselectAllPartiesNYears()
		{
			var elements = document.getElementsByTagName('input'); 
			for(var i=0;i<elements.length;i++)
			{
				if(elements[i].type=="checkbox" && (elements[i].name=="electionCheckBox"  || elements[i].name=="partywiseCheckBox"))  
				{
					elements[i].checked=false;				
				}
			}
		}

		function selectAllPartiesNYears()
		{
			var elements = document.getElementsByTagName('input'); 
			for(var i=0;i<elements.length;i++)
			{
				if(elements[i].type=="checkbox" && (elements[i].name=="electionCheckBox"  || elements[i].name=="partywiseCheckBox"))  
				{
					elements[i].checked=true;				
				}
			}
		}
		function buildZptcResults(results, jsObj){
			
			assignToPartyDataArray = new Array();
			var candLink = document.getElementById("zptcCandidateLink");
			var zptcChartName = results[0].chartName;
			var selectedYearVal = jsObj.electionYear;
			var chartDivEl = document.getElementById("zptcChartDiv");
			var linkRef = '<a href="javascript:{}" onclick="redirectZptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Results</a>';
			candLink.innerHTML = linkRef;
			totalZptcSeats = results[0].totalSeats;		//	var totalZptcSeats,totalMptcSeats;
			
				var chartStr = '';				
				chartStr+='<img src="charts/'+zptcChartName+'"/>';
				chartDivEl.innerHTML = chartStr;
			
			for(var i in results)
			{		
				var problemObj=		
				 {		
						partyName:results[i].partyName,
						participatedSeats:results[i].participatedSeats,
						seatsWonByParty:results[i].seatsWonByParty,
						percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
				 };
				
				assignToPartyDataArray.push(problemObj);
				tehsilDetails.partyArray=assignToPartyDataArray;	
			}

			var zptcCount = document.getElementById("totalZptcCountResultDiv");
			zptcCount.innerHTML ='';

			
			var totalZptcSeats='';
			totalZptcSeats+="<b>"+results[0].totalSeats+"</b>";
			zptcCount.innerHTML +=totalZptcSeats;

			var emptyArr = new Array();
		    if(results.length == 0)
			{	tehsilDetails.partyArray = emptyArr;				
			}
		    initializeResultsTableForParty();
		}

		function buildMptcResults(results, jsObj){
			assignToPartyDataArray = new Array();
			var electionIdEl = document.getElementById("staticGrpSelectBox");
			var selectedYearVal = jsObj.electionYear;
			var mptcChartName = results[0].chartName;
			var candLink = document.getElementById("mptcCandidateLink");
			var chartDivEl = document.getElementById("mptcChartDiv");
			var linkRef = '<a href="javascript:{}" onclick="redirectMptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Results</a>';
			candLink.innerHTML = linkRef;
			  totalMptcSeats = results[0].totalSeats;
			  
					var chartStr = '';
					
					chartStr+='<img src="charts/'+mptcChartName+'"/>';
					chartDivEl.innerHTML = chartStr;
				
			for(var i in results)
			{		
				var problemObj=		
				 {		
						partyName:results[i].partyName,
						participatedSeats:results[i].participatedSeats,
						seatsWonByParty:results[i].seatsWonByParty,
						percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
				 };
				
				assignToPartyDataArray.push(problemObj);
				tehsilDetails.partyMptcArray=assignToPartyDataArray;	
			}

			var mptcCount = document.getElementById("totalMptcCountResultDiv");
			mptcCount.innerHTML='';
			
			var totalMptcSeats='';
			totalMptcSeats+="<b>";
			totalMptcSeats+=results[0].totalSeats;
			totalMptcSeats+="</b>";
			mptcCount.innerHTML +=totalMptcSeats;
			
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	tehsilDetails.partyMptcArray = emptyArr;				
			}
		    initializeMptcResultsTableForParty(); 
		}

		function getConstituencyResults(elecYear){
			var jsObj = {
				constituencyId:constituencyIdGlobal,
				electionYear:elecYear,
				chartHeight: 400,
				chartWidth: 800,
				others:false,
				task:"getConstituencyResultsBySubLocations"
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/assemblyWiseParliamentResultAction.action?"+rparam;
			callAjax(jsObj, url);
		}

		function getMandalsAndPartiesResults(){
			var jsObj = {
					mandalIds:mandalIds,
					electionYear:"2004",
					electionType:"Assembly",
					chartHeight: 400,
					chartWidth: 800,
					task:"getMandalsAndPartiesChartInElection"
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/getLineChartForMandalsAndPartiesAction.action?"+rparam;
				callAjax(jsObj, url);
		}
		function partyVotesSharing(){
			var jsObj = {
					constituencyId:constituencyIdGlobal,
					task:"votesSharingInConstituency",
					choice:"All"
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/votesSharingInConstituencyAjaxAction.action?"+rparam;
				callAjax(jsObj, url);
		}

		function getMuncipalElections(){
			var jsObj = {
					constituencyId:constituencyIdGlobal,
					task:"muncipalElectionDetails"								
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/localMuncipalityElectionsAjaxAction.action?"+rparam;
				callAjax(jsObj, url);
		}
		
		function getCorporationElections(){
			var jsObj = {
					constituencyId:constituencyIdGlobal,
					task:"corporationElectionDetails"				
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/localCorporationElectionsAjaxAction.action?"+rparam;
				callAjax(jsObj, url);				
		}

		function showAllPartiesAllElectionsResults(results)
		{
			var imageDivEl = document.getElementById("madalwiseVotesRangeChart");
			var dataDivEl = document.getElementById("");
			var str = '';
			str += '<img src="charts/'+myResults.chartName+'">';		
			imageDivEl.innerHTML = str;
			/*var dataDivElContent = '';
			dataDivElContent+='<div id="mptc_main">';
			dataDivElContent+='<div id="mptc_head">';
			dataDivElContent+='<table border="0" cellpadding="0" cellspacing="0" >';
			dataDivElContent+='	<tr>';
			dataDivElContent+='		<td><img src="images/icons/districtPage/header_left.gif"/></td>';
			dataDivElContent+='		<td>';	
			dataDivElContent+='			<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:850px;padding:9px;height:18px;">';
			dataDivElContent+='			<span>Total Number of MPTCs :</span>';
			dataDivElContent+='					<span id="totalMptcCountResultDiv"></span>';
			dataDivElContent+='				</div>';
			dataDivElContent+='			</td>';
			dataDivElContent+='			<td><img src="images/icons/districtPage/header_right.gif"/></td>';
			dataDivElContent+='		</tr>';
			dataDivElContent+='	</table>';
			dataDivElContent+='</div>';
			dataDivElContent+='<div id="mptc_body" style="width:900px;">';
			dataDivElContent+='<table width="100%">';		
			dataDivElContent+='<tr><td>';
			dataDivElContent+='<table width="100%"><tr><td colspan="2">';
			dataDivElContent+='<table ><tr>';
			dataDivElContent+='<td><div id="mptcElectionIdsSelectDiv" style="padding-left:10px;" class="yui-skin-sam"></div></td>';
			dataDivElContent+='<td><div id="mptcCandidateLink"></div></td>';
			dataDivElContent+='</tr></table>';
			dataDivElContent+='</td></tr>';
			dataDivElContent+='<tr>';
			dataDivElContent+='<td valign="top"><div id="mptcChartDiv"></div></td>';		
			dataDivElContent+='<td class="yui-skin-sam" valign="top"><div id="mptcPartyTrendsDetailsDiv"></div></td>';
			dataDivElContent+='</tr></table>';
			dataDivElContent+='</td></tr>';
			dataDivElContent+='</table>';	
			dataDivElContent+='</div>';
			dataDivElContent+='</div>';*/
			
}
	--></script>

</head>
<body>
	<div style="background-color:#FFFFFF;padding-top:10px;">
	<div id="windowHeader" style="background-color:black">
	<table width="100%">
		<tr>		
			<td width="86%" align="center">
				<TABLE cellspacing="0" cellpadding="0" border="0" >
				<TR>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" style="margin-top:15px;" /></TD>
					<TD valign="top"><DIV id="mainHead" class="mainHeading">Voting Trends in ${constiName} Constituency</DIV></TD>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" style="margin-top:15px;" border="none"/></TD>
				</TR>
				</TABLE>
			</td>
			<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"/></td>
		</tr>
	</table>
	</div>	
	<div id="votingTrendzInfoMain"></div>
	<div class="rounded" >
		<center>
		<table>
			<tr>
					<td style="vertical-align:top;">
						<div id="zptc_main">
							<div id="zptc_head">
								<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><img src="images/icons/districtPage/header_left.gif"/></td>
									<td>	
										<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:850px;height:18px;padding:9px;">
											<span>Total Number of ZPTC's : </span>
											<span id="totalZptcCountResultDiv"></span>														
										</div>
									</td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
								</table>
							</div>
							<div id="zptc_body" style="width:900px;">
								<table width="100%">									
									<tr><td>
											<table width="100%"><tr><td colspan="2" align="left">
															<table ><tr>
														   		<td><div id="zptcElectionIdsSelectDiv" style="padding-left:10px;">
														   		</div></td>
														   		<td><div id="zptcCandidateLink"></div></td>
													   		</tr></table>
													   </td></tr>
												   <tr>
												   	   <td valign="top"><div id="zptcChartDiv"></div></td>
													   <td class="yui-skin-sam" valign="top"><div id="zptcPartyTrendsDetailsDiv" style="border:2px solid #9696C0"></div></td>
											</tr></table>
									</td></tr>
								</table>	
							</div>		
						</div>		
						</td>			
					</tr>
					<tr>
					<td style="vertical-align:top;">
						<div id="mptc_main">
							<div id="mptc_head">
								<table border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td><img src="images/icons/districtPage/header_left.gif"/></td>
										<td>	
											<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:850px;padding:9px;height:18px;">
												<span>Total Number of MPTC's : </span>
												<span id="totalMptcCountResultDiv"></span>
											</div>
										</td>
										<td><img src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
						<div id="mptc_body" style="width:900px;">
								<table width="100%">									
									<tr><td>
											<table width="100%"><tr><td colspan="2">
															<table ><tr>
														   		<td><div id="mptcElectionIdsSelectDiv" style="padding-left:10px;" class="yui-skin-sam"></div></td>
														   		<td><div id="mptcCandidateLink"></div></td>
													   		</tr></table>
													   </td></tr>
												   <tr>
												   	   <td valign="top"><div id="mptcChartDiv"></div></td>		
													   <td class="yui-skin-sam" valign="top"><div id="mptcPartyTrendsDetailsDiv"></div></td>
											</tr></table>
									</td></tr>
								</table>	
							</div>
						</div>
					</td>	
				</tr>
				</table>
				</center>
				</div>		
				
				<div id="muncipality_corporation_div_main" style="width:auto;">
					<center>
					<table>
						<tr>
							<td align="left">
								<div id="corporationDiv_main">
								<div id="corporationDivHead" style="text-align:left;cursor:pointer;" onclick="hideCorporationDiv()"></div>
								<div id="corporationDiv" style="text-align:left;border-bottom:1px solid #E0E0D6;border-left:1px solid #E0E0D6;border-right:1px solid #E0E0D6;height:auto;overflow:auto;padding:15px;" ></div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="left">
							<div id="muncipalitiesDiv_main">
								<div id="muncipalitiesDivHead" style="text-align:left;" onclick="hideMuncipalitiesDiv()"></div>
								<div id="muncipalitiesDiv" style="text-align:left;border-bottom:1px solid #E0E0D6;border-left:1px solid #E0E0D6;border-right:1px solid #E0E0D6;height:auto;overflow:auto;padding:15px;"></div>
							</div>
							</td>
						</tr>
					</table>
					</center>
				</div>
		
		<div id="index_footer" class="indexLayoutContainer" style="width:100%">
			<div id="index_inner_footer">
			<table width="100%" id="copyrightLinksTable">
				<tr>
					<td align="left"> © Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
					<td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
				</tr>
			</table>
			</div>
		</div>
	</div>
	<SCRIPT type="text/javascript"> 			
			buildMandalsVotingTrendz();		
			getConstituencyOverViewResult(constituencyIdGlobal,constituencyName);	
			getAllZptcYears();	  
			getAllMptcYears();
			partyVotesSharing();
			getMuncipalElections();
			getCorporationElections();
			mandalVotingShareDetailsMethod();
	</script>
</body>
</html>