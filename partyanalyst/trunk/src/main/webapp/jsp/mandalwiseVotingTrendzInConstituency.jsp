<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title >Voting Trends in Constituency</title>

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
	

	

	
    <style type="text/css">
		.mainHeading 
		{
			background-image:url("images/icons/electionResultsReport/heading.png");
			border:0 solid #AEE2FF;
			color:#000000;
			font-family:MS Sans-serif;
			font-size:22px;
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
			color:black;
			margin-left: 5px; 
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
			background-image: url("images/icons/electionResultsAnalysisReport/mid.png"); ;
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
		#zptcHeadDiv, #mptcHeadDiv
		{
			font-weight:bold;
			padding:5px;
			font-size:17px;
			color:#247CD4;
		}
		.counterSize 
		{
			color:red;
			font-size:18px;
		}
    </style>

	<link rel="stylesheet" type="text/css" href="styles/tv9Styles/tv9Styles.css">

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
		var constituencyNameGlobal;
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
		var mandalNamesArr = null;
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
			
			function constituencyOverViewResult(jsObj,myResults)
			{
				
				var heading =document.getElementById("constituencyMainDetails_Div");
				var headingDIV='';
				headingDIV+='<fieldset padding:10px;">';  		
				headingDIV+='<legend style="background-color:#567AAF;font-family:verdana,helvetica,clean,sans-serif;color:#FFFFFF;font-weight:bold;padding:10px;font-size:20px;">Constituency Overview</legend>';

				headingDIV+='<div id="votesInfoDiv" style="color:#247CD4;font-size:12px;font-weight:bold;"></div>';
				headingDIV+='<table width="75%" cellspacing="10" style="margin-top:10px;">';   
				headingDIV+=' <tr>';
				headingDIV+=' <td><img src="images/bi-constituency_maps/'+jsObj.constiName+'.jpg"/></td>';
				headingDIV+=' </tr>';
				
				headingDIV+='	<tr>';
				headingDIV+='		<td width="50%" style="color:#18325A;font-size:17px;font-family:verdana;" align="left"><b>Total Voters for Year 2009</b></td>';
				headingDIV+='		<td width="2%"><b> :</b></td>';
				headingDIV+='		<td width="30%" align="left" style="color:red;font-size:17px;font-weight:bold;">'+myResults.latestElectionYearsTotalVoters+'</td>';
				
				headingDIV+='		</tr>';			

				headingDIV+='	<tr>';
				headingDIV+='		<td style="color:#18325A;font-size:17px;font-family:verdana;" align="left"><b>Total Voters for Year 2010</b></td>';
				headingDIV+='		<td><b> :</b></td>';
				if(myResults.presentYearTotalVoters!=0)
				{	
					headingDIV+='		<td align="left" style="color:red;font-size:17px;font-weight:bold;">'+myResults.presentYearTotalVoters+'</td>';				
				}
				else
				{				
					headingDIV+='		<td align="left" style="color:#1C4B7A;"> Data Not Available</td>';
				}					
				
				headingDIV+='	</tr>';

				headingDIV+='		<tr>';               
				headingDIV+='		<td style="color:#18325A;font-size:17px;font-family:verdana;" align="left"><b>Total Polled Votes for Year 2009</b></td>';
				headingDIV+='		<td><b> :</b></td>';
				headingDIV+='		<td align="left" style="color:red;font-size:17px;font-weight:bold;">'+myResults.latestElectionYearsTotalPolledVotes+' <font color="black"> [</font><font color="green"> '+myResults.latestElectionYearsTotalVotesPercentage+' % </font><font color="black">]</font></td>';		
								
				headingDIV+='	</tr>';

				headingDIV+='</table>';
				
                headingDIV+='<div id="contestingCandidates_InnerDiv" style="margin:0px;">';
				headingDIV+='<table width="100%" border="0">';
				headingDIV+='<tr>';
				headingDIV+='<td width="65%" valign="top">';
				headingDIV+= '<div id="constiContestingCandsDiv" style="color:#247CD4;font-size:12px;font-weight:bold;text-align:left;padding:5px;text-decoration:underline;">';
				headingDIV+= '</div>';
				headingDIV+='<div id="contestingCandidatesIn" style="margin:10px;border:solid 0px;" >';

				headingDIV+='<table width="890px" class="participatingPartiestable" border="0">';	
				headingDIV+='<tr>';
				headingDIV+='<td colspan="2" style="border:0px;padding:0px;">';
				headingDIV+='<table class="participatingPartiestable_inner" border="0"  cellpadding="0" cellspacing="0">';
				headingDIV+='<tr>';
				headingDIV+='	<td valign="top" style="padding:0px;border:none;"> ';
				headingDIV+='		<img src="images/icons/tv9Icons/first.png">';
				headingDIV+='	</td>';

				headingDIV+='	<td valign="top" style="padding:0px;border:none;">';
				headingDIV+='		<div class="detailsTableHeader" style="width:845px;">';
				headingDIV+='			<span class="detailsTableHeaderSpan">Bye-Election 2010 Contesting Candidates </span>';
				headingDIV+='		</div>';
				headingDIV+='	</td>';

				headingDIV+='	<td valign="top" style="padding:0px;border:none;"> ';
				headingDIV+='		<img src="images/icons/tv9Icons/second.png">';
				headingDIV+='	</td>';

				headingDIV+='</tr>';
				headingDIV+='</table>';
				headingDIV+='</td>';
				headingDIV+='</tr>';			
				
				headingDIV+='<tr>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Party</th>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Candidate</th>';
				headingDIV+='</tr>';
		
				for(var c in myResults.contestingCands)
				{
					headingDIV+='<tr>';
					headingDIV+='<td align="center"><b style="color:#121922">'+myResults.contestingCands[c].partyName+'</b></td>';
					headingDIV+='<td align="left"><b style="color:#121922">'+myResults.contestingCands[c].candidateName+'</b></td>';
					headingDIV+='</tr>';
				}
				
				headingDIV+='</table>';

				headingDIV+='</div>';
                headingDIV+='</div>';
				headingDIV+='</td>';				
				
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td width="65%" valign="top">';
				headingDIV+='	<div style="font-size: 12px;font-weight:bold;text-align:left;color:#247CD4;padding:5px;text-decoration:underline">';
				headingDIV+='		';
				headingDIV+='	</div>';
				//-----------
				headingDIV+='	<div style="margin:10px">';
				headingDIV+='	<table class="participatingPartiestable" border="0" width="890px">';	
				headingDIV+='	<tr>';
				headingDIV+='		<td colspan="5" style="border:0px;padding:0px;">';
				headingDIV+='			<table class="participatingPartiestable_inner" border="0"  cellpadding="0" cellspacing="0" width="100%">';
				headingDIV+='			<tr>';
				headingDIV+='				<td valign="top" style="padding:0px;border:none;"> ';
				headingDIV+='					<img src="images/icons/tv9Icons/first.png">';
				headingDIV+='				</td>';

				headingDIV+='				<td valign="top" style="padding:0px;border:none;">';
				headingDIV+='					<div class="detailsTableHeader" style="width:845px;">';
				headingDIV+='						<span class="detailsTableHeaderSpan">2009 Won & Opposition Candidates Result In Constituency </span>';
				headingDIV+='					</div>';
				headingDIV+='				</td>';

				headingDIV+='				<td valign="top" style="padding:0px;border:none;">';
				headingDIV+='					<img src="images/icons/tv9Icons/second.png">';
				headingDIV+='				</td>';
				headingDIV+='			</tr>';
				headingDIV+='			</table>';
				headingDIV+='		</td>';
				headingDIV+='	</tr>';

				headingDIV+='<tr>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Party</th>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Candidate</th>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Votes Polled</th>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Votes %</th>';
				headingDIV+='<th align="center" style="color:#FFFFFF">Status</th>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td align="center"><b style="color:#121922">'+myResults.elecResultsInConsti.partyName+'</b></td>';
				headingDIV+='<td align="left"><b style="color:#121922">'+myResults.elecResultsInConsti.candidateName+'</b></td>';
				headingDIV+='<td align="center"><b style="color:#121922">'+myResults.elecResultsInConsti.votesEarned+'</b></td>';
				headingDIV+='<td align="left"><b style="color:#121922">'+myResults.elecResultsInConsti.votesPercentage+'</b></td>';
				headingDIV+='<td align="left"><b style="color:#121922"> Won </b></td>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td align="center"><b style="color:#121922">'+myResults.elecResultsInConsti.secondPartyName+'</b></td>';
				headingDIV+='<td align="left"><b style="color:#121922">'+myResults.elecResultsInConsti.secondCandidateName+'</b></td>';
				headingDIV+='<td align="center"><b style="color:#121922">'+myResults.elecResultsInConsti.votesEarnedBySecond+'</b></td>';
				headingDIV+='<td align="left"><b style="color:#121922">'+myResults.elecResultsInConsti.votesPercentageBySecond+'</b></td>';
				headingDIV+='<td align="left"><b style="color:#121922"> Runner-up </b></td>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td colspan="5" style="color:#764B00;font-size:18px;" align="center">';
				headingDIV+='	<b>Margin Votes</b> : '+myResults.elecResultsInConsti.marginVotes;
				headingDIV+='</td>';
				headingDIV+='</tr>';

				headingDIV+='<tr>';
				headingDIV+='<td colspan="5" style="color:#764B00;font-size:18px;" align="center">';
				headingDIV+='	<b>Margin Votes %</b> : '+myResults.elecResultsInConsti.marginPercent;
				headingDIV+=' %</td>';
				headingDIV+='</tr>';
				headingDIV+='</table>';
				headingDIV+='</div>';
				//------
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
											showChartData(jsObj, myResults);
										} else if(jsObj.task == "getConstituencyResultsBySubLocations")
										{
											constiMandalWiseResultsPresChart = myResults.chartPath;
											if(mandalIds != null)
											{
												getMandalsAndPartiesResults();
											}
											
										}else if(jsObj.task == "getMandalsAndPartiesChartInElection")
										{
											constiMandalWiseResultsPrevChart = myResults;
											var imageDiv = document.getElementById("constitutencyMandalWiseResultsChart");
											var str = '';
											str +='<div style="margin: 10px;">';
											str += '<table border="1" width="100%" style="border-collapse:collapse;">';
											str += '<tr><td valign="top" align="left">';
											str += '<img width="750" src="charts/'+constiMandalWiseResultsPresChart+'"></td></tr>';
											str += '<tr><td valign="top" align="left">';
											str += '<img width="750" src="charts/'+constiMandalWiseResultsPrevChart+'"></td></tr>';
											str += '</table>';
											str += '</div>';
											imageDiv.innerHTML = str;
										}	
										else if(jsObj.task == "getConstituencyVotesOverview"){
											constituencyOverViewResult(jsObj,myResults);
										}
										else if(jsObj.task == "votesSharingInConstituency"){
											buildVotesSharingData(jsObj,myResults);
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
			muncipalityDiv+='<td><img src="images/icons/tv9Icons/first.png"/></td>';
			muncipalityDiv+='<td><div id="muncipalityInfoDivHead" class="districtPageRoundedHeaders_center" style=" width: 700px; height: 18px;"><a href="javascript:{}" style="color:#FFFFFF;text-decoration:none;font-size:19px;font-weight:bold;">'+totalMessage+' : <b class="counterSize"> '+result[0].totalMuncipalities+'</b></a></div>';
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
			corporationDiv+='<td><img src="images/icons/tv9Icons/first.png"/></td>';	
			corporationDiv+='<td><div id="corporationInfoDivHead" class="districtPageRoundedHeaders_center" style="width: 700px; height: 18px;"><a href="javascript:{}" style="color:#FFFFFF;text-decoration:none;font-size:14px;">'+totalMessage+' : <b class="counterSize"> '+result[0].totalMuncipalities+'</b></a></div>';
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
				rvStr += '<table id="municipalityVotersDiv" class="datatableClass" width="90%" style="background-color:#F3F6F7;margin-top:10px;margin-right:10px;">';
				rvStr += '<tr>';
				rvStr += '<th align="left">Muncipality Name :</th><th align="left">'+result[i].muncipalityName+'</th>'; 
				rvStr += '<th align="left">Total Wards :</th><th align="left">'+result[i].totalWards+'</th>';
				rvStr += '</tr>';
				rvStr += '<tr>';
				rvStr += '<th align="left">Total Voters :</th><th align="left">'+result[i].totalVoters+'</th>';
				rvStr += '<th align="left">Total Polled Votes :</th><th align="left">'+result[i].totalPolledVotes+'</th>';
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
			str+='			<td width="2%" style="padding:0px;border:none;"> <img src="images/icons/tv9Icons/first.png"></td>';
			str+='			<td width="98%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Share in '+tehsilName+' Mandal</span></div></td>';
			str+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/tv9Icons/second.png"></td>';
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
						str += '<td name="'+info.electionType+'">&nbsp </td>';
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

		function buildVotesSharingData(jsObj,results)
		{
			votesShareData = results;
            var chartName = '';

			if(results[0] != null)
			{
			chartName = results[0].chartName;
			
			}

			var shareElmt = document.getElementById("votersShareData_main");
			var checkBoxElmt = document.getElementById("votersShareCheckBox");

			if(!shareElmt && !checkBoxElmt)
				return;
			
			var electionListLength = results[0].electionList.length+2;

			var str = '';
			str += '<table><tr>';
			str += '<td colspan=6> ';
			str += '<div><table border="0" cellspacing="0" cellpadding="0">';
			str += '<tr>';
			str += '<td><img src="images/icons/tv9Icons/left_blue_main.png"/></td>';
			str += '<td><div style="height:40px;background-image:url(\'images/icons/tv9Icons/header_body_blue.png\')">';
			str += '<span id="headDiv" style="color:#0C2640;font-size:16px;font-weight:bold;position:relative;top:10px;">';
			str += '</span>';
			str += '</div></td>';
			str += '<td><img src="images/icons/tv9Icons/right_blue_main.png"/></td>';
			str += '</tr>';
			str += '</table></div>';
			str += '<p><font style="color: rgb(36, 124, 212); font-size: 15px; font-weight: bold;">(Based on 2009 Delimitation Boundaries)</p></font>';
			str += '</td>';
			str += '</tr><tr>';
			str += '<td><div style="height:15px;width:15px;border:1px solid #DDEB9B;background-color:#F6CECE;margin:10px;"></div></td>';
			str += '<td><P class="votesSharingHeaderPara">Bye Elections</P></td>';
			str += '<td><div style="height:15px;width:15px;border:1px solid #F6CECE;background-color:#DDEB9B;margin:10px;"></div></td>';
			str += '<td><P class="votesSharingHeaderPara">Alliance Results</P></td>';
			str += '<td><div style="margin:5px;"><font style="color:red;font-size:18px;"> *</div></td>';
			str += '<td><P class="votesSharingHeaderPara">Grouped Alliance Party Results </P></td>';
            str += '</tr></table>';
		
			
			str += '<table id="votesShareDetailsTable" cellspacing="4" cellmargin="0">';
			str += '<tr>';
			str += '<td colspan="'+electionListLength+'" style="padding:0px;border:0px">';
			str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
			str+='			<tr>';
			str+='			<td width="2%" style="padding:0px;border:0px;"> <img src="images/icons/tv9Icons/first.png"></td>';
			str+='			<td width="98%" style="padding:0px;border:0px;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Shares</span></div></td>';
			str+='			<td width="1%" style="padding:0px;border:0px;"><img src="images/icons/tv9Icons/second.png"></td>';
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
				if(results[j].range == " ")
					continue
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
				str += '<td align="left" style="color:#FF8000;font-weight:bold;width:130px;">'+results[j].range+'</td>';
				str += '</tr>';
			}

			str += '</table>';
			shareElmt.innerHTML = str;

			var chartDivElmt = document.getElementById("constitutencyResultsChart");
			var imgStr = '';
			imgStr+='<img width="750" src="charts/'+chartName+'" border="none" />';		
			chartDivElmt.innerHTML = imgStr;

			if(jsObj.getAll == "-")
			{				
				return;
			}
			
			
			var cStr = '';
			cStr += '<div> ';
			cStr += '<center><table>';
			cStr += '<tr>';
			cStr += '<td style="color:#121922;font-weight:bold;font-size:16px;">View :</td>';
			cStr += '<td style="color:#121922;font-weight:bold;font-size:16px;">';

			cStr += '<input type="checkbox" name="elecType" checked="checked" value="ALL" onclick="showSelectedColoumn(this.value)"/>ALL';
			cStr += '<input type="checkbox" name="elecType" value="AC" onclick="showSelectedColoumn(this.value)"/>AC';
			cStr += '<input type="checkbox" name="elecType" value="PC" onclick="showSelectedColoumn(this.value)"/>PC';
			cStr += '<input type="checkbox" name="elecType" value="MPTC" onclick="showSelectedColoumn(this.value)"/>MPTC';
			cStr += '<input type="checkbox" name="elecType" value="ZPTC" onclick="showSelectedColoumn(this.value)"/>ZPTC';		
			cStr += '</td>';
			cStr += '</tr>';
			cStr += '</table></center>';
			cStr += '</div>';
			
			checkBoxElmt.innerHTML = cStr;
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
				partyVotesSharing('all','-',1);
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
			
			if(electypeSelectedElmts && electypeSelectedElmts.length > 0){
				partyVotesSharing("-",electypeSelectedElmts,0);
			}
			else if(electypeSelectedElmts && electypeSelectedElmts.length == 0)
				partyVotesSharing('all','-',1);
			
			/*var elmt = document.getElementById("votersShareData_main");
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

			elmt.innerHTML = str;*/
		}

		function showChartData(jsObj, results)
		{
			var selectboxElmtDiv = document.getElementById("selectLocationOptions");
			var checkboxElmtDiv = document.getElementById("inputSelectionCriteria");
			var selectOptionsSelectButtonElmt = document.getElementById("selectOptionsSelectButton");

			if(selectboxElmtDiv && checkboxElmtDiv)
			{
				selectboxElmtDiv.style.display = 'none';
				checkboxElmtDiv.style.display = 'none';
				selectOptionsSelectButtonElmt.style.display = 'block';
				
				selectOptionsSelectButtonElmt.innerHTML = '<input type="button" value="Show Options" onclick="displaySelectionCriteria()">';
			}

			chartName = results.chartName;
			var headDivEl = document.getElementById("headDiv");
			var str='';
			str += 'Parties Performance in '+jsObj.constituencyName+' Assembly Constituencey Limits In Different Elections';
			headDivEl.innerHTML = '';
            headDivEl.innerHTML = str;
            
			var mainDivElmt = document.getElementById("crossVotingData_Graph_Div");
			var divEl = document.getElementById("constitutencyResultsChart");
			mainDivElmt.style.display = 'block';

			divEl.innerHTML = '';
			divEl.innerHTML = '<img width="750" src="charts/'+chartName+'" border="none" />';
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
					resultLevel: "constituency",
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
					resultLevel: "constituency",
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
			partyVotesSharing('all','-',1);
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
			var brow1 = window.open("<s:url action="townshipElectionResultsAction"/>?mandalId="+mandalId+"&electionId="+electionId+"&mandalName="+name+"&electionType="+electionType+"&electionYear="+electionYear+"&windowTask=includeVotingTrendz","brow1","width=1050,height=600,menubar=no,status=no,location=no,resizable=1,toolbar=no,scrollbars=yes");
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
			str += '<table width="90%">';
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
				str += '<table  width="90%">';
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
			str += '<td width="50%" style="vertical-align:top;"><div id="partyVotesSharingDetailsDiv"><div id="votersShareData_main"></div><div id="votersShareCheckBox"></div></div></td>';
			str += '</tr>';
			str += '<tr>';
			str += '<td width="50%" style="vertical-align:top;"><div id="constitutencyResultsChart"></div></td>';
			str += '</tr>';			
			str += '</table>';
			str += '</div>';

			str += '<div id="constituencyOverViewDiv"></div>';	
			str += '<div id="crossVotingDetailsDiv" style="margin:10px;">';
			str += '<div id="crossVotingHeadingDiv"> ';
			str += '<table border="0" cellspacing="0" cellpadding="0">';
			str += '<tr>';
			str += '<td><img src="images/icons/tv9Icons/left_blue_main.png"/></td>';
			str += '<td><div style="height:40px;background-image:url(\'images/icons/tv9Icons/header_body_blue.png\')">';
			str += '<span style="color:#0C2640;font-size:20px;font-weight:bold;position:relative;top:5px;">';
			str += 'Non Participating Parties Strength & Cross Voting Details</span>';
			str += '</div></td>';
			str += '<td><img src="images/icons/tv9Icons/right_blue_main.png"/></td>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';	
			str+='<TABLE border="0" width="100%">';
			str+='<TR>';
			str+='<TD valign="top"><DIV id="nonParticipatingDiv" style="margin:20px 0px;"></TD>';
			str+='</TR>';
			str+='<TR>';
			str+='<TD valign="top"><DIV id="crossVotingDiv" style="margin:20px 0px;"></TD>';
			str+='</TR>';
			str+='</TABLE>';
			str+='</div>';
			str += '<div id="overViewHeadingDiv" style="padding-left:10px;"></div>';
			str += '<div id="constitutencyMandalWiseResultsChart"></div>';
			str += '<div id="mandalVotingTrendzDataDiv_head">';
			str += '<table border="0" cellspacing="0" cellpadding="0">';
			str += '<tr>';
			str += '<td><img src="images/icons/tv9Icons/left_blue_main.png"/></td>';
			str += '<td><div style="height:40px;background-image:url(\'images/icons/tv9Icons/header_body_blue.png\')">';
			str += '<span style="color:#0C2640;font-size:20px;font-weight:bold;position:relative;top:6px;">';
			str += 'Voting Trendz in Constituency</span>';
			str += '</div></td>';
			str += '<td><img src="images/icons/tv9Icons/right_blue_main.png"/></td>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';
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
		}

		function getResultsForSelectedElection(constiId, constiName)
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
					
					constituencyName: constiName,
					constituencyId: constiId,
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
			crossVotingDetailsDivEl.innerHTML = '';
			var nonParticipatingDivEl = document.getElementById("nonParticipatingDiv");
			nonParticipatingDivEl.innerHTML = '';
			var mandalwiseVotersShare = resultsData.constituencyVO.assembliesOfParliamentInfo;
			var constituencyId = jsObj.constituencyId;
			constituencyNameGlobal = jsObj.constiName; 
			constituencyName = jsObj.constiName;
			mandalNamesArr = new Array();
			var crossVotingData_Graph_DivEl = document.getElementById("crossVotingData_Graph_Div");
			if(crossVotingData_Graph_DivEl.style.display == 'block')
			{	
				crossVotingData_Graph_DivEl.style.display = 'none';
			}	
			if(resultsData.biElectionResultsMainVO != null)
			{	
				var results = resultsData.biElectionResultsMainVO;
				var crossVotingResults = resultsData.biElectionResultsMainVO[0].crossVotingResults;
				var nonParticipatingParties = resultsData.biElectionResultsMainVO[0].nonPartiParties;
			}
			
			if(crossVotingResults != null)
			{	
				var crossVotingResultsContent = '';
				crossVotingResultsContent+='<TABLE width="100%" class="participatingPartiestable" border="0">';
				crossVotingResultsContent+='	<TR>';
				crossVotingResultsContent+='	<TD colspan="6" style="padding:0px;border:none;">';
				crossVotingResultsContent+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
				crossVotingResultsContent+='			<tr>';
				crossVotingResultsContent+='			<td width="2%" style="padding:0px;border:none;"> <img src="images/icons/tv9Icons/first.png"></td>';
				crossVotingResultsContent+='			<td width="97%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan">Cross Voting Details</span></div></td>';
				crossVotingResultsContent+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/tv9Icons/second.png"></td>';
				crossVotingResultsContent+='			</tr>';
				crossVotingResultsContent+='		</table>';
				crossVotingResultsContent+='	</TD>';
				crossVotingResultsContent+='	</tr>';
				crossVotingResultsContent+='	<TR>';
				crossVotingResultsContent+='		<TH style="color:#FFFFFF;">Party</TH>';
				crossVotingResultsContent+='		<TH style="color:#FFFFFF;">Votes Polled(AC)</TH>';
				crossVotingResultsContent+='		<TH style="color:#FFFFFF;">Votes %(AC)</TH>';
				crossVotingResultsContent+='		<TH style="color:#FFFFFF;">Votes Polled(PC)</TH>';
				crossVotingResultsContent+='		<TH style="color:#FFFFFF;">Votes %(PC)</TH>';
				crossVotingResultsContent+='		<TH style="color:#FFFFFF;">Difference</TH>';
				crossVotingResultsContent+='	</TR>';			
				for(var d in crossVotingResults)
				{
					    if(d == crossVotingResults.length-1)
					    {
	                    crossVotingResultsContent+='<TR>';
						crossVotingResultsContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+crossVotingResults[d].partyName+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#764B00;">'+crossVotingResults[d].votesEarned+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+crossVotingResults[d].percentage+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+crossVotingResults[d].ballotVotes+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+crossVotingResults[d].ballotVotesPercentage+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+crossVotingResults[d].diffPercent+'</TD>';							
						crossVotingResultsContent+='</TR>';
						}
	
						else
					    {
						crossVotingResultsContent+='<TR>';
						crossVotingResultsContent+='<TD align="center" style="color:#121922;">'+crossVotingResults[d].partyName+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#121922;">'+crossVotingResults[d].votesEarned+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#121922;">'+crossVotingResults[d].percentage+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#121922;">'+crossVotingResults[d].ballotVotes+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#121922;">'+crossVotingResults[d].ballotVotesPercentage+'</TD>';
						crossVotingResultsContent+='<TD align="center" style="color:#121922;">'+crossVotingResults[d].diffPercent+'</TD>';							
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
			nonParticipatingContent+='			<td width="2%" style="padding:0px;border:none;"> <img src="images/icons/tv9Icons/first.png"></td>';
			nonParticipatingContent+='			<td width="97%" style="padding:0px;border:none;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan">Non Participating Parties Details</span></div></td>';
			nonParticipatingContent+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/tv9Icons/second.png"></td>';
			nonParticipatingContent+='			</tr>';
			nonParticipatingContent+='		</table>';
			nonParticipatingContent+='	</TD>';
			nonParticipatingContent+='	</tr>';
			nonParticipatingContent+='<TR>';
			nonParticipatingContent+='<TH style="color:#FFFFFF;">Party</TH>';
			nonParticipatingContent+='<TH style="color:#FFFFFF;">Votes Polled(AC)</TH>';
			nonParticipatingContent+='<TH style="color:#FFFFFF;">Votes %(AC)</TH>';
			nonParticipatingContent+='<TH style="color:#FFFFFF;">Votes Polled(PC)</TH>';
			nonParticipatingContent+='<TH style="color:#FFFFFF;">Votes %(PC)</TH>';			
			nonParticipatingContent+='</TR>';			
			for(var y in nonParticipatingParties)
			{       if(y == nonParticipatingParties.length-1)
				    {
				    nonParticipatingContent+='<TR>';
					nonParticipatingContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+nonParticipatingParties[y].partyName+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+nonParticipatingParties[y].votesEarned+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+nonParticipatingParties[y].percentage+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+nonParticipatingParties[y].ballotVotes+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#764B00;font-weight:bold;">'+nonParticipatingParties[y].ballotVotesPercentage+'</TD>';										
					nonParticipatingContent+='</TR>';
				       
			        }
					else
				    {
					nonParticipatingContent+='<TR>';
					nonParticipatingContent+='<TD align="center" style="color:#121922;">'+nonParticipatingParties[y].partyName+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#121922;">'+nonParticipatingParties[y].votesEarned+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#121922;">'+nonParticipatingParties[y].percentage+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#121922;">'+nonParticipatingParties[y].ballotVotes+'</TD>';
					nonParticipatingContent+='<TD align="center" style="color:#121922;">'+nonParticipatingParties[y].ballotVotesPercentage+'</TD>';										
					nonParticipatingContent+='</TR>';		
					}
			}
			nonParticipatingContent+='</TABLE>';			
			nonParticipatingDivEl.innerHTML = nonParticipatingContent;
			
			}	
			var mdlwiseVotersDetailsStr = '';
			mdlwiseVotersDetailsStr += '<table border="0" cellspacing="0" cellpadding="0">';
			mdlwiseVotersDetailsStr += '<tr>';
			mdlwiseVotersDetailsStr += '<td><img src="images/icons/tv9Icons/left_blue_main.png"/></td>';
			mdlwiseVotersDetailsStr += '<td><div style="height:40px;background-image:url(\'images/icons/tv9Icons/header_body_blue.png\')">';
			mdlwiseVotersDetailsStr += '<span style="color:#0C2640;font-size:20px;font-weight:bold;position:relative;top:5px;">';
			mdlwiseVotersDetailsStr += 'Mandalwise Voters Share in Constituency</span>';
			mdlwiseVotersDetailsStr += '</div></td>';
			mdlwiseVotersDetailsStr += '<td><img src="images/icons/tv9Icons/right_blue_main.png"/></td>';
			mdlwiseVotersDetailsStr += '</tr>';
			mdlwiseVotersDetailsStr += '</table>';

			mdlwiseVotersDetailsStr+='<Table width="70%" border="0" class="mandalVotesShareTable" cellpadding="5" cellspacing="5">';
			mdlwiseVotersDetailsStr+='<TR>';			
			mdlwiseVotersDetailsStr+='	<TH>Mandal</TH>';
			mdlwiseVotersDetailsStr+='	<TH>%</TH>';
			mdlwiseVotersDetailsStr+='	<TH>Analyze</TH>';
			mdlwiseVotersDetailsStr+='</TR>';
			for (var x in mandalwiseVotersShare[0].votersInfoForMandalVO)
			{				
				mdlwiseVotersDetailsStr+='<TR>';				
				mdlwiseVotersDetailsStr+='<TD align="left" style="text-align:left;"><A class="mandalvotersAnc" href="javascript:{}" title="Displays Parties Performane in All Elections Mandalwise" onclick="getMandalwiseVotesShare('+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalId+','+constituencyId+',\''+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName+'\')">'+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName+'</A></TD>';
				mdlwiseVotersDetailsStr+='<TD align="center">'+mandalwiseVotersShare[0].votersInfoForMandalVO[x].percent+' %</TD>';
				mdlwiseVotersDetailsStr+='<TD align="center"><A href="javascript:{}" class="mandalvotersAnc" title="Displays Parties Performane in All Elections Revenue Mandalwise" onclick="openwin('+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalId+',\''+mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName+'\',\'Assembly\',\'2009\',\'3\')">Analyze</A></TD>';
				mdlwiseVotersDetailsStr+='</TR>';
	
				var mandalObj={
					id: mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalId,
					name: mandalwiseVotersShare[0].votersInfoForMandalVO[x].mandalName  
					};
				mandalNamesArr.push(mandalObj);
										
			}
			
			mdlwiseVotersDetailsStr+='</Table>';
			mandalvotersDetailsEl.innerHTML = mdlwiseVotersDetailsStr;
				
			if(resultsData.urbanRuralConstiResults != null)
			{	
				var urbanConstResults = resultsData.urbanRuralConstiResults;
			}	 
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
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2009_Assembly"  />2009 AC</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2004_Assembly"  />2004 AC</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2009_Parliament"  />2009 PC</td>';			
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2008_Parliament"  />2008 PC<font style="color:red;"> *</font></td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2006_Parliament"  />2006 PC<font style="color:red;"> *</font></td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2004_Parliament"  />2004 PC</td>';
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
			str1 += '<table width="75%">';
			str1 += '<tr>';
			str1 += '<th align="left"><font style="color:red;"> *</font> Indicates Bye Elections</th>';	
			str1 += '<th align="left"> AC - Assembly</th>';	
			str1 += '<th align="left"> PC - Parliament</th>';
			str1 += '</tr>';
			str1 += '<tr>';
			str1 += '<td align="left">';
			str1 += '<INPUT type="checkbox" name="allianceCheckBox" id="allianceChkBox" />Include Alliances';
			str1 += '<input type="button" value="Select All" onclick="selectAllPartiesNYears()">';
			str1 += '<input type="button" value="De-select All" onclick="DeselectAllPartiesNYears()">';
			str1 += '<INPUT type="button" id="getResults" onclick="getResultsForSelectedElection('+constituencyId+', \''+constituencyNameGlobal+'\')" value="Show Results" />';
			str1 += '</td>';
			str1 += '</tr>';
			str1 += '</table>';			
			str1 += '</div>';
			inputEl.innerHTML = str1;
			
			
			//Rendering Mandal voting trendz data
			var chartDetailsObjArr = resultsData.chartsListForElectionTypes;
			if(results != null )
			{
				bodyElmt.innerHTML = '';
				var str = '';
				str += '<table width="100%" height="150" cellspacing = "5" cellpadding="5" border="0">';
				var mandalIdsFlag = 0;
				mandalIds = new Array();
				
					for(var i in results)
						{	
							for(var j in results[i].biElectionResultsVO)
							{
								
								var electionHeaderLength = (results[i].biElectionResultsVO[j].partysList.length*2)+2;
								var partyHeaderLength = results[i].biElectionResultsVO[j].partysList.length*2;
								str += '<tr>';
								/*str += '<td width="100">';
								for(var z in chartDetailsObjArr)
								{
									var chartDetailsObj = chartDetailsObjArr[z]; 
									var electionType = chartDetailsObj.electionType
									var electionYear = chartDetailsObj.electionYear
									var chartName = chartDetailsObj.chartName;	
									if(electionType == results[i].biElectionResultsVO[j].electionType && electionYear ==  results[i].biElectionResultsVO[j].electionYear)
									//str += '<img src="charts/'+chartName+'" />';
									str += '';
										
								}
								str += '</td>';*/
								str += '<td style="vertical-align:top;padding-bottom:20px;">';
								
								str += '<table width="100%" class="mandalResultsTable" border="1">';
								str += '<tr>';
								str += '<th style="color:#FFFFFF;font-size:19px;padding:9px;background-image:url(\'images/icons/tv9Icons/tableHeader.png\')" colspan="'+electionHeaderLength+'" align="left">';
								str += '<span style="float:left">'+results[i].biElectionResultsVO[j].electionType+' - '+results[i].biElectionResultsVO[j].electionYear+'</span>';
								str += '<div style="float:right;cursor:pointer;">';
								str += '<div id="imgDiv_'+i+'_'+j+'" onclick="showHideMandalTrendzGraph(this.id)"> View Graph</div>'; 
								for(var z in chartDetailsObjArr)
								{
									var chartDetailsObj = chartDetailsObjArr[z]; 
									var electionType = chartDetailsObj.electionType
									var electionYear = chartDetailsObj.electionYear
									var chartName = chartDetailsObj.chartName;	
									if(electionType == results[i].biElectionResultsVO[j].electionType && electionYear ==  results[i].biElectionResultsVO[j].electionYear)
									str += '<div style="display:none;" id="imgDiv_'+i+'_'+j+'_graph" class="mandalVotingTrendzGraphImg" ><div id="imgDiv_'+i+'_'+j+'" class="trendzGraphDiv"  onclick="showHideMandalTrendzGraph(this.id)">Close</div><img src="charts/'+chartName+'" /></div>';
									
										
								}
								str += '</div>';
								str += '</th>';
								str += '</tr>';
								str += '<tr>';
								str += '<th rowspan="3" style="background-image:url(\'images/icons/tv9Icons/thBackGround.png\')">Mandal</th>';
								str += '<th rowspan="3" style="background-image:url(\'images/icons/tv9Icons/thBackGround.png\')">Constituency</th>';
								str += '<th colspan="'+partyHeaderLength+'" align="center" style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">Party</th>';
								str += '</tr>';
								str += '<tr>';
								for(var p in results[i].biElectionResultsVO[j].partysList)
								{
									str += '<th colspan="2" style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">'+results[i].biElectionResultsVO[j].partysList[p].name+'</th>';
								}
								str += '</tr>';
								str += '<tr>';
								for(var q in results[i].biElectionResultsVO[j].partysList)
								{
									str += '<th style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">V*</th>';
									str += '<th style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">%</th>';
								}
								str += '</tr>';
								for(var k in results[i].biElectionResultsVO[j].electionResultsForMandal)
								{
									
									var info = results[i].biElectionResultsVO[j].electionResultsForMandal[k];
									str += '<tr>';
									if(info.partyElecResultsInConstituency.length == 0)
									{
										var cols = partyHeaderLength+1;
										str += '<th style="background:none;"><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs"  onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';
										for(var colsno = 0;colsno < cols; colsno++)
											str += '<td> -- </td>';	
									}
									else
									{
										if(mandalIdsFlag == 0)
											mandalIds.push(info.mandalId);
										
										str += '<th style="background:none;" rowspan="'+info.partyElecResultsInConstituency.length+'"><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs" onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';				
										for(var l in info.partyElecResultsInConstituency)
										{
											str += '<th style="color:#62662B;width:150px;font-size:17px;background:none">'+info.partyElecResultsInConstituency[l].constituencyName.toUpperCase()+'</th>';
															
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
									str += '<th colspan="2" style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">Postal Ballot Votes</th>';
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
								str += '<th colspan="2" style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">Total</th>';
								for(var sum in results[i].biElectionResultsVO[j].partyResultsSum)
								{
									str += '<td><font style="color:#764B00;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].votesEarned+'</font></td><td><font style="color:#764B00;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].percentage+'</font></td>';
								}					
								str += '</tr>';
								str += '</table>';
								str += '</td>';
								str += '</tr>';
								str += '<tr>';
								str += '<th style="color:#62662B;width:150px;font-size:14px;background:none;" align="right"> V* - Votes Polled For Party</th>';
								
								str += '</tr>';
								str += '<tr><td colspan="2"><hr/></td></tr>';
                               
							}
							
						}
						str += '</table>';
						               

						bodyElmt.innerHTML = str;
						
		} else if(urbanConstResults != null)
		{
			bodyElmt.innerHTML = '';
			mandalIds = null;
			var urbanStr = '';
				
			for(var a in urbanConstResults)
			{	
					urbanStr += '<table width="100%" height="150" cellspacing = "5" cellpadding="5" border="0">';
					urbanStr += '	<tr>';
					var electionHeaderLen = (urbanConstResults[a].partiesHeading.length*2)+2;
					var partyHeaderLen = urbanConstResults[a].partiesHeading.length*2;
					/*urbanStr += '		<td>';
					urbanStr += '			<img src="charts/'+urbanConstResults[a].electionPieChart+'" />';
					urbanStr += '		</td>';*/
					urbanStr += '		<td style="vertical-align:top;padding-bottom:20px;" width="100%">';
					urbanStr += '			<table width="100%" class="mandalResultsTable" border="1" style="margin:10px;">';
					urbanStr += '				<tr>';
					urbanStr += '					<th style="color:#FFFFFF;font-size:19px;padding:9px;background-image:url(\'images/icons/tv9Icons/tableHeader.png\')" colspan="'+electionHeaderLen+'" align="left">';
					urbanStr += ' <span>'+urbanConstResults[a].electionType+' - '+urbanConstResults[a].electionYear+'</span>';
					urbanStr += '<div style="float:right;cursor:pointer;">';
					urbanStr += '<div id="imgDiv_'+a+'" onclick="showHideMandalTrendzGraph(this.id)"> View Graph</div>'; 
					
					urbanStr += '<div style="display:none;" id="imgDiv_'+a+'_graph" class="mandalVotingTrendzGraphImg" ><div id="imgDiv_'+a+'" class="trendzGraphDiv"  onclick="showHideMandalTrendzGraph(this.id)">Close</div><img src="charts/'+urbanConstResults[a].electionPieChart+'" /></div>';						
							
				
					urbanStr += '</div>';
					urbanStr += '</th>';
					urbanStr += '				</tr>';
					urbanStr += '				<tr>';
					urbanStr += '					<th rowspan="3" style="background-image:url(\'images/icons/tv9Icons/thBackGround.png\')">Mandal</th>';
					urbanStr += '					<th rowspan="3" style="background-image:url(\'images/icons/tv9Icons/thBackGround.png\')">Constituency</th>';
					urbanStr += '					<th colspan="'+partyHeaderLen+'" style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')" align="center">Party</th>';
					urbanStr += '				</tr>';
					urbanStr += '				<tr>';
					for(var c in urbanConstResults[a].partiesHeading)
					{
						urbanStr += '				<th style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')" colspan="2">'+urbanConstResults[a].partiesHeading[c].name+'</th>';
					}
					urbanStr += '				</tr>';
					urbanStr += '				<tr>';
					for(var e in urbanConstResults[a].partiesHeading)
					{
						urbanStr += '				<th style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">V*</th><th style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">%</th>';
					}
					urbanStr += '				</tr>';
					for(var f in urbanConstResults[a].constituencyMandalInfo)
					{						
							
							var info1 = urbanConstResults[a].constituencyMandalInfo[f].partiesReslts;
							urbanStr += '		<tr>';	
						// if postal ballot votes

						if(urbanConstResults[a].constituencyMandalInfo[f].isPostalVotes == true)	
						{		
								urbanStr += '		<th colspan="2" style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')">Postal Ballot Votes</th>';												
								//urbanStr+='</tr><tr>';									
								
						} else if(urbanConstResults[a].constituencyMandalInfo[f].isTotal == true)	
						{		
							urbanStr += '			<th style="background-image:url(\'images/icons/indexPage/reportGroupHeader.png\')" colspan="2">Total Votes</th>';												
							//urbanStr+='</tr><tr>';									
							
						} else	
						{		
								urbanStr += '		<th style="background:none;"><A href="javascript:{}" title="Click to view results and voting trendz in '+urbanConstResults[a].constituencyMandalInfo[f].tehsilName+' mandal" class="viewAncs" onclick="openwin('+urbanConstResults[a].constituencyMandalInfo[f].tehsilId+',\''+urbanConstResults[a].constituencyMandalInfo[f].tehsilName+'\',\''+urbanConstResults[a].electionType+'\','+urbanConstResults[a].electionYear+','+urbanConstResults[a].electionId+')">'+urbanConstResults[a].constituencyMandalInfo[f].tehsilName+'</A></th>';
								urbanStr += '		<th style="background:none;color:#62662B;width:150px;font-size:14px;">'+urbanConstResults[a].constituencyMandalInfo[f].constituencyName.toUpperCase()+'</th>';
						}								
							for(var g in info1)
								{
											
										if(info1[g].partyTotalVotes == "0" || info1[g].percentageOfVotes == "-1" )
										{
											urbanStr += '<td> -- </td>';
											urbanStr += '<td> -- </td>';
										}else
										{
											urbanStr += '<td>'+info1[g].partyTotalVotes+'</td>';
											urbanStr += '<td>'+info1[g].percentageOfVotes+'</td>';
										}						
									}								
								
						
						urbanStr+='</tr><tr>';	
					}
					urbanStr += '</tr>';
					urbanStr += '		</table>';
					urbanStr += '</td>';
					urbanStr += '</tr>';
					urbanStr += '</table>';					
			}
			
			bodyElmt.innerHTML = urbanStr;
			//building 2004 2009 charts
					var imageDiv = document.getElementById("constitutencyMandalWiseResultsChart");
					var str = '';
					str +='<div style="margin: 10px;">';
					str += '<table border="1" width="100%" style="border-collapse:collapse;">';
					str += '<tr><td valign="top" align="left">';
					str += '<img width="750" src="charts/'+resultsData.assemblyResultsChartForPresentYear+'"></td></tr>';
					str += '<tr><td valign="top" align="left">';
					str += '<img width="750" src="charts/'+resultsData.assemblyResultsChartForPreviousYear+'"></td></tr>';
					str += '</table>';
					str += '</div>';
					imageDiv.innerHTML = str;
					
		}	

			
			//getZptcPartyDetails(tehsilElections.zptcElectionYears[0].value);
			//getMptcPartyDetails(tehsilElections.mptcElectionYears[0].value);
			
			getConstituencyResults("2009");
			getAllZptcYears();	  
			getAllMptcYears();	
			//To build Graphs for 2009 and 2004 Mandals Wise Parties Results 
		}
		
		function hideMandalTrendzGraph(id)
		{
			var elmt = document.getElementById(id+"_graph");
			if(!elmt)
				return;
			
			if(elmt.style.display == 'block')
				elmt.style.display = 'none';
		}

		function showHideMandalTrendzGraph(id)
		{
			var elmt = document.getElementById(id+"_graph");
			if(!elmt)
				return;			
			
			if(elmt.style.display == 'none')
				elmt.style.display = 'block';
			else if(elmt.style.display == 'block')
				elmt.style.display = 'none';
		
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
			var totalVotersDivElmt = document.getElementById("totalZptcVotersDiv");
			var chartDivEl = document.getElementById("zptcChartDiv");
			var zptcOptionsDivEl = document.getElementById("zptcOptionsDiv");
			var zptcChartName = results[0].chartName;
			var selectedYearVal = jsObj.electionYear;
			var zptcHeadDivEl = document.getElementById("zptcHeadDiv");
			zptcHeadDivEl.innerHTML = '';
			totalVotersDivElmt.innerHTML = '';
			var linkRef = '<a href="javascript:{}" onclick="redirectZptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Candidate Results in Constituency</a>';
			candLink.innerHTML = linkRef;
			
			totalZptcSeats = results[0].totalSeats;	
			
			var chartStr = '';				
			chartStr+='<img src="charts/'+zptcChartName+'"/>';
			chartDivEl.innerHTML = chartStr;
			if(results[0].totalVotersInConstituency != null && results[0].totalPolledVotes != null)
			{
				var voterStr = '';
				voterStr += '<table class="zptcMptcVotersTable" width="100%">';
				voterStr += '<tr>';
				voterStr += '<th align="left"> Total Voters  : </th><td>'+results[0].totalVotersInConstituency+'</td>';
				voterStr += '</tr>';
				voterStr += '<tr>';
				voterStr += '<th align="left"> Total Polled Votes : </th><td>'+results[0].totalPolledVotes+'</td>';
				voterStr += '</tr>';
				voterStr += '</table>';
				totalVotersDivElmt.innerHTML = voterStr;
			}	
			var zptcOptionsDivElStr = '';
			zptcOptionsDivElStr+='<TABLE>';
			zptcOptionsDivElStr+='<TR>';
			zptcOptionsDivElStr+='<TH style="font-size:14px;">View Results By</TH>';
			zptcOptionsDivElStr+='<TH style="font-size:14px;" align="left"><INPUT type="radio" name="locationOption" id="clocationOption" onclick="showHideMandalDropdown1(\'mandalOpt\','+selectedYearVal+')"/>Constituency</TH>';
			zptcOptionsDivElStr+='<TH style="font-size:14px;" align="left"><INPUT type="radio" name="locationOption" id="mlocationOption" onclick="showHideMandalDropdown(\'mandalOpt\')"/>Mandal</TH>';
			zptcOptionsDivElStr+='<TH style="font-size:14px;" align="left"><SELECT name ="mandalOpt" id = "mandalOpt" class="selectWidth" style="display:none;" onchange="getMandalLocalElectionResults(this.options[this.selectedIndex].value,\'ZPTC\',\'getZptcElectionResults\',this.options[this.selectedIndex].text,this.options[this.selectedIndex].index)">';
			zptcOptionsDivElStr+='<OPTION value="0">Select Mandal</OPTION>';
			for(var i in mandalNamesArr)
			{
				zptcOptionsDivElStr+='<OPTION value='+mandalNamesArr[i].id+'>'+mandalNamesArr[i].name+'</OPTION>';
			}
			zptcOptionsDivElStr+='</SELECT>';
			zptcOptionsDivElStr+='</TH>';
			zptcOptionsDivElStr+='<TR>';
			zptcOptionsDivElStr+='</TABLE>';
			zptcOptionsDivEl.innerHTML = zptcOptionsDivElStr;
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
			if(jsObj.resultLevel == 'constituency')
			{
				var clocationOptionEl = document.getElementById("clocationOption");
				clocationOptionEl.checked = true;
				zptcHeadDivEl.innerHTML = "ZPTC Results in "+constituencyNameGlobal+" Constituency";
				zptcCount.innerHTML +=totalZptcSeats;
			} else if (jsObj.resultLevel == 'mandal')
				{
					var mlocationOptionEl = document.getElementById("mlocationOption");
					mlocationOptionEl.checked = true;
					var mzSelectOptionEl = document.getElementById("mandalOpt");
					mzSelectOptionEl.style.display="block";
					mzSelectOptionEl.selectedIndex=jsObj.index;
					zptcHeadDivEl.innerHTML = "ZPTC Results in "+jsObj.mandalName+" Mandal";
					zptcCount.innerHTML = '1';
				}

			var emptyArr = new Array();
		    if(results.length == 0)
			{	tehsilDetails.partyArray = emptyArr;				
			}
		    initializeResultsTableForParty();
		}

		function showHideMandalDropdown1(id, year)
		{
			var mandalOptEl = document.getElementById(id);
			mandalOptEl.style.display = 'none';
			
			getZptcPartyDetails(year);
		}
		function showHideMandalDropdown2(id, year)
		{
			var mandalOptEl = document.getElementById(id);
			mandalOptEl.style.display = 'none';
			
			getMptcPartyDetails(year);
		}

		function getMandalLocalElectionResults(mandalId, electionType, task, mandalName, index)
		{
			if(mandalId == 0)
				return;	
			
			var electionIdEl = document.getElementById("staticGrpSelectBox");
			var electionYear = electionIdEl.options[electionIdEl.selectedIndex].text;
			 
			var jsObj = {
					tehsilId: mandalId,
					electionType: electionType,
					electionYear: electionYear,
					resultLevel: "mandal",
					task: task,
					mandalName: mandalName,
					index: index 			 					
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/getMandalLocalElectionsAjaxAction.action?"+rparam;
				callAjax(jsObj, url);				
					
		}
		function showHideMandalDropdown(id)
		{
			
			var mandalOptEl = document.getElementById(id);
			if(mandalOptEl.style.display == 'none')
			{
				mandalOptEl.style.display = 'block';
			} else if(mandalOptEl.style.display == 'block')
			{
				mandalOptEl.style.display = 'none';
			}
				
			
		}

		function buildMptcResults(results, jsObj){
			assignToPartyDataArray = new Array();
			var electionIdEl = document.getElementById("staticGrpSelectBox");
			var selectedYearVal = jsObj.electionYear;
			var mptcChartName = results[0].chartName;
			var candLink = document.getElementById("mptcCandidateLink");
			var mptcVotersDivElmt = document.getElementById("totalMptcVotersDiv");
			var mptcOptionsDivEl = document.getElementById("mptcOptionsDiv");
			var chartDivEl = document.getElementById("mptcChartDiv");
			var mptcHeadDivEl = document.getElementById("mptcHeadDiv");
			mptcHeadDivEl.innerHTML = '';
			mptcVotersDivElmt.innerHTML = '';
			var linkRef = '<a href="javascript:{}" onclick="redirectMptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Candidate Results in Constituency</a>';
			candLink.innerHTML = linkRef;
			  totalMptcSeats = results[0].totalSeats;
			  
					var chartStr = '';
					
					chartStr+='<img src="charts/'+mptcChartName+'"/>';
					chartDivEl.innerHTML = chartStr;
			
			if(results[0].totalVotersInConstituency != null && results[0].totalPolledVotes != null)
			{	
				var voterStr = '';
				voterStr += '<table class="zptcMptcVotersTable" width="100%">';
				voterStr += '<tr>';
				voterStr += '<th align="left"> Total Voters  : </th><td>'+results[0].totalVotersInConstituency+'</td>';
				voterStr += '</tr>';
				voterStr += '<tr>';
				voterStr += '<th align="left"> Total Polled Votes : </th><td>'+results[0].totalPolledVotes+'</td>';
				voterStr += '</tr>';
				voterStr += '</table>';
				mptcVotersDivElmt.innerHTML = voterStr;
			}	
			var mptcOptionsDivElStr = '';
			mptcOptionsDivElStr+='<TABLE>';
			mptcOptionsDivElStr+='<TR>';
			mptcOptionsDivElStr+='<TH style="font-size:14px;">View Results By</TH>';
			mptcOptionsDivElStr+='<TH style="font-size:14px;" align="left"><INPUT type="radio" name="mlocationOption" id="cmlocationOption" checked = "true" onclick="showHideMandalDropdown2(\'mmandalOpt\','+selectedYearVal+')"/>Constituency</TH>';
			mptcOptionsDivElStr+='<TH style="font-size:14px;" align="left"><INPUT type="radio" name="mlocationOption" id="mmlocationOption" onclick="showHideMandalDropdown(\'mmandalOpt\')"/>Mandal</TH>';
			mptcOptionsDivElStr+='<TH style="font-size:14px;" align="left"><SELECT name ="mandalOpt" id = "mmandalOpt" class="selectWidth" style="display:none;" onchange="getMandalLocalElectionResults(this.options[this.selectedIndex].value,\'MPTC\',\'getMptcElectionResults\',this.options[this.selectedIndex].text,this.options[this.selectedIndex].index)">';
			mptcOptionsDivElStr+='<OPTION value="0">Select Mandal</OPTION>';
			for(var i in mandalNamesArr)
			{
				mptcOptionsDivElStr+='<OPTION value='+mandalNamesArr[i].id+'>'+mandalNamesArr[i].name+'</OPTION>';
			}
			mptcOptionsDivElStr+='</SELECT>';
			mptcOptionsDivElStr+='</TH>';
			mptcOptionsDivElStr+='<TR>';
			mptcOptionsDivElStr+='</TABLE>';
			mptcOptionsDivEl.innerHTML = mptcOptionsDivElStr;
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

			var mptcCount = document.getElementById("totalMptcCountResultsDiv");
			mptcCount.innerHTML='';
			
			var totalMptcSeats='';
			totalMptcSeats+="<b>";
			totalMptcSeats+=results[0].totalSeats;
			totalMptcSeats+="</b>";
			//mptcCount.innerHTML +=totalMptcSeats;
			
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	tehsilDetails.partyMptcArray = emptyArr;				
			}
		    if(jsObj.resultLevel == 'constituency')
			{
		    	var clocationOptionEl = document.getElementById("cmlocationOption");
				clocationOptionEl.checked = true;
				
				mptcHeadDivEl.innerHTML = "MPTC Results in "+constituencyNameGlobal+" Constituency";
				mptcCount.innerHTML +=totalMptcSeats;
			} else if (jsObj.resultLevel == 'mandal')
				{
				var clocationOptionEl = document.getElementById("mmlocationOption");
				clocationOptionEl.checked = true;
				var mzSelectOptionEl = document.getElementById("mmandalOpt");
				mzSelectOptionEl.style.display="block";
				mzSelectOptionEl.selectedIndex=jsObj.index;
					mptcHeadDivEl.innerHTML = "MPTC Results in "+jsObj.mandalName+" Mandal";
					//mptcCount.innerHTML = 'N/A';
					mptcCount.innerHTML = totalMptcSeats;
					
					
				}
			
		    initializeMptcResultsTableForParty(); 
		}

		function getConstituencyResults(elecYear){
			var jsObj = {
				constituencyId:constituencyIdGlobal,
				electionYear:elecYear,
				chartHeight: 520,
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
					mandalIds: mandalIds,
					electionYear:"2004",
					electionType:"Assembly",
					chartHeight: 520,
					chartWidth: 800,
					task:"getMandalsAndPartiesChartInElection"
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "<%=request.getContextPath()%>/getLineChartForMandalsAndPartiesAction.action?"+rparam;
				callAjax(jsObj, url);
		}
		function partyVotesSharing(all,allChoices,flag){		
			var getAllData = all;
			var getSelectedChoices = allChoices;
			var jsObj = {
					getAll : all,
					choices : allChoices,
					flag : flag,
					constituencyId: constituencyIdGlobal,
					constiName: constituencyName,
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
	
	<div style="background-color:#FFFFFF;padding-top:0px;">
	<div id="windowHeader" style="background-color:black">
	<table width="100%">
		<tr>		
		    <td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"/></td>
			<td width="72%" align="center">
				<TABLE cellspacing="0" cellpadding="0" border="0" >
				<TR>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" style="margin-top:15px;" /></TD>
					<TD valign="top"><DIV id="mainHead" class="mainHeading">Voting Trends in ${constiName}</DIV></TD>
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
		<table width="98%">
			<tr>
				<td style="vertical-align:top;">
					<div id="zptc_main">
						<div id="zptc_head">
							<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="images/icons/tv9Icons/first.png"/></td>
								<td>	
									<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:685px;height:18px;">
										<span>Total Number of ZPTC's : </span>
										<span id="totalZptcCountResultDiv"></span>														
									</div>
								</td>
								<td><img src="images/icons/tv9Icons/second.png"/></td>
							</tr>
							</table>
						</div>
						<div id="zptc_body" style="width:745px;">
							<table width="100%">									
								<tr>
									<td>
										<table width="100%">
											<tr>
												<td colspan="2" align="left">
													<table width="100%">
														<tr>
															<td><div id="zptcElectionIdsSelectDiv" style="font-size:14px;"></div></td>
															<td><div id="zptcCandidateLink"></div></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
											    <td colspan="2"><div id="zptcOptionsDiv"></div></td>
											</tr>
											<tr>
												<td width="50%"><div></div></td>
												<td width="50%"><div id="zptcHeadDiv"></div></td>
											</tr>
											<tr>
												<td width="50%"><div></div></td>
												<td width="50%" align="center"><div id="totalZptcVotersDiv"></div></td>
											</tr>
											<tr>
												<td align="center"><div id="zptcChartDiv"></div></td>
												<td class="yui-skin-sam" valign="top"><div id="zptcPartyTrendsDetailsDiv"></div></td>
											</tr>
										</table>
									</td>
								</tr>
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
										<td><img src="images/icons/tv9Icons/first.png"/></td>
										<td>	
											<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:685px;height:18px;">
												<span>Total Number of MPTC's : </span>
												<span id="totalMptcCountResultsDiv"></span>
											</div>
										</td>
										<td><img src="images/icons/tv9Icons/second.png"/></td>
									</tr>
								</table>
							</div>
						<div id="mptc_body" style="width:745px;">
								<table width="100%">									
									<tr><td>
											<table width="100%"><tr><td colspan="2">
															<table width="100%"><tr>
														   		<td><div id="mptcElectionIdsSelectDiv" style="padding-left:10px;font-size:14px" class="yui-skin-sam"></div></td>
														   		<!--<td><div id="mptcCandidateLink"></div></td>
																--><td><div id="mptcCandidateLink"></div></td>
													   		</tr></table>
													   </td></tr>
												<tr>
													<td colspan="2"><div id="mptcOptionsDiv"></div></td>	
												</tr>
												<tr>
													<td width="50%"><div></div></td>
													<td width="50%"><div id="mptcHeadDiv"></div></td>
												</tr>
												<tr>
													<td width="50%"><div></div></td>
													<td width="50%" align="center"><div id="totalMptcVotersDiv"></div></td>
											   </tr>
												   <tr>
												   	   <td align="center"><div id="mptcChartDiv"></div></td>		
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
				
				<div id="muncipality_corporation_div_main" style="width:auto;padding-left:10px;">
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
			//getAllZptcYears();	  
			//getAllMptcYears();
			partyVotesSharing('all','-',1);
			getMuncipalElections();
			getCorporationElections();
			mandalVotingShareDetailsMethod();
			//getMandalsAndPartiesResults();
	</script>
</body>
</html>