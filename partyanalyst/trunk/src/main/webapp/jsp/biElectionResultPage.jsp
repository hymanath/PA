<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Telangana Bye-Elections - 2010 Analysis</title>


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

	<script type="text/javascript" src="js/biElectionPage/biElectionPage.js"></script>	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">
	<link rel="stylesheet" type="text/css" href="styles/indexPage/indexPage.css">
	<link rel="stylesheet" type="text/css" href="styles/biElectionPage/biElectionPage.css">

	

	<script type="text/javascript">
	 <%			
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String desc1 = rb.getString("resultsDescritpion1");
		String desc2 = rb.getString("resultsDescritpion2");
		String indPartyDesc = rb.getString("indPartyDesc");
		String partyBoothResultsText = rb.getString("partyBoothResultsText");
		String locationAlert2 = rb.getString("locationAlert2");
		String locationAlert = rb.getString("locationAlert");
		String locationAlert1 = rb.getString("locationAlert1");
	%>
	var localizationObj = {
			desc1 : '<%=desc1%>',
			desc2 : '<%=desc2%>',
			partyBoothResultsText: '<%=partyBoothResultsText%>'								
	};
		var enlargedPresentYearResultsChartName = "${enlargedPresentYearResultsChartName}"; 
		var enlargedPreviousYearResultsChartName = "${enlargedPreviousYearResultsChartName}";

		var electionYear = '${electionYear}';
		var electionType = '${electionType}';
		var presentYearResultsChartName = '${presentYearResultsChartName}';
		var previousYearResultsChartName = '${previousYearResultsChartName}';
		var biElectionObj = {
				staticParties:[]
				};
		function getMoreDetails(constiId,elecType,elecYear)
		{	
			 var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
			 browser1.focus();
		}
		function getConstituencyElectionResults(id)
		{
		  var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+id+"&electionType="+assemblyElectionType+"&electionYear="+presentElectionYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
		  browser1.focus();		
		}

		function openVotingTrendzWindow(distId,constId,constName)
		{			
			var browser1 = window.open("<s:url action="mandalVotingTrendzForBiElectionAction.action"/>?districtId="+distId+"&constiId="+constId+"&constiName="+constName,"biElectionConstituencyResults","scrollbars=yes,height=800,width=950, resizable = 1,left=200,top=200");

			browser1.focus();
		}
		function openConstVotingTrendzWindow(distId,constId,constName)
		{			
			var browser1 = window.open("<s:url action="constituencyVotingTrendzAction.action"/>?districtId="+distId+"&constiId="+constId+"&constiName="+constName,"biElectionConstituencyResults1","scrollbars=yes,resizable=1,left=200,top=200");

			browser1.focus();
		}

		function open2010ElectionResultsWindow()
		{
			var browser1 = window.open("<s:url action="byeElectionResultsAnalysisAction.action"/>","biElectionResultsAnalysis","scrollbars=yes,resizable=1,left=200,top=200");

			browser1.focus();
						
		} 

		function openMandalBoothResultsForPartyWindow()
		{
			var constEl = document.getElementById("selectConst");
			var partyEl = document.getElementById("selectParty");
			var constId = constEl.options[constEl.selectedIndex].value;
			var partyId = partyEl.options[partyEl.selectedIndex].value; 
			var alertEl = document.getElementById("locationAlert");
			var partyName = partyEl.options[partyEl.selectedIndex].text;
			var constName = constEl.options[constEl.selectedIndex].text;

			
			if(constId == 0 && partyId == 0)
			{
				alertEl.innerHTML = '';
				alertEl.innerHTML = '<%=locationAlert2%>';
				return;
			}
			if(constId == 0)
			{
				alertEl.innerHTML = '';
				alertEl.innerHTML = '<%=locationAlert%>';
				return;
			}
			if(partyId == 0)
			{
				alertEl.innerHTML = '';
				alertEl.innerHTML = '<%=locationAlert1%>';
				return;
			}
			
			var browser1 = window.open("<s:url action="mandalwiseBoothResultsForPartyAction.action"/>?constituencyId="+constId+"&partyId="+partyId+"&constituencyName="+constName+"&partyName="+partyName,"mandalwiseBoothResultsForParty","scrollbars=yes,height=800,width=950,left=200,top=200,resizable=yes");
			browser1.focus();
						
		}

		function getMandalVotingTrendz(distId,constId,constName)
		{
			return;
			var jsObj=
			{
					districtId:distId,
					constituencyId:constId, 
					constiName:constName,
					task:"getMandalVotingTrendz"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getMandalsVotingTrendzForBiElection.action?"+rparam;

			callAjax(jsObj, url);
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
										
									}
								catch (e) {   
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

		function openwin(mandalId,name, electionType,electionYear,electionId){
			
			var brow1 = window.open("<s:url action="townshipElectionResultsAction"/>?mandalId="+mandalId+"&electionId="+electionId+"&mandalName="+name+"&electionType="+electionType+"&electionYear="+electionYear+"&windowTask=includeVotingTrendz","brow1","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			brow1.focus();
		}

    </script>
  
	</head>
	<body>
	<div id="detailedChartDIV" class="yui-skin-sam"></div>
	<div id="districtPageMainDiv">	
	    <div id="biElectionPage_header">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img border="none" src="images/icons/statePage/header_left.png"></td>
				<td><div id="pageHeading"><span id="stateNameSpan"> Telangana Bye-Elections - 2010 Analysis</span></div></td>
				<td><img border="none" src="images/icons/statePage/header_right.png"></td>
			</tr>
		</table>
	    </div>
		<div style="margin-top:20px;margin-bottom:10px;width:914px;padding:5px;font-size:14px;font-weight:bold;color:#764B00;border:1px solid #DBDCDB;background-color:#EAEAE9">
            <MARQUEE width=100% behaviour=SCROLL scrollAmount=3 >Issue of Notification  : 02-07-2010 (Friday)  <B>||</B>  Last date of making Nominations  : 09-07-2010 (Friday)  <B> ||  </B> Scrutiny of Nominations : 10-07-2010 (Saturday) <B>  || </B> Last date for withdrawal of Candidatures : 12-07-2010 (Monday)  <B> || </B> Date of Poll : 27-07-2010 (Tuesday) <B> || </B> Hours of Poll : 07.00 AM to 05.00 PM  <B> || </B> Counting of Votes  : 30-07-2010 (Friday) 08.00 AM </MARQUEE>
        </div>
		<div>
		
		</div>

		<!--BiElection Page Layout-->
		<div id="biElectionPageLayout_main"></div>	

		<!--BiElection Page Right Layout-->
		<div id="biElectionPageLayout_right">		

			<div id="biElectionDistricts_main">
				<div id="biElectionDistricts_head">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
						<tr>
							<td><img src="images/icons/districtPage/header_left.gif"/></td>
							<td width="100%"><div class="districtPageRoundedHeaders_center"><span> Bye Election Districts </span></div></td>
							<td><img src="images/icons/districtPage/header_right.gif"/></td>
						</tr>
					</table>
				</div>
				<div id="biElectionDistricts_body" class="frameBody">					
				</div>			
			</div>
		
        </div>


		<!--BiElection Page Center Layout-->
		<div id="biElectionPageLayout_center">
			<div id="districtMap" style="margin-left:12px;">
				<div id="districtMap_head">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
						<tr>
							<td><img src="images/icons/districtPage/header_left.gif"/></td>
							<td width="100%"><div class="districtPageRoundedHeaders_center"><span>Telangana</span></div></td>
							<td><img src="images/icons/districtPage/header_right.gif"/></td>
						</tr>
					</table>
				</div>
				<div id="telanganaMap_body">				 
					<object width="550" height="310">
						<param name="movie" value="images/icons/districtPage/telangana.swf">
						 <param name="wmode" value="transparent"> 
						<embed wmode="transparent" src="images/icons/districtPage/telangana.swf" width="550" height="310">
						</embed>
					</object>		
				</div>
			</div>			
		</div>
		<div id="boothResultsSelection" style="border:1px solid #E0E0D6;text-align:left;margin-left:18px;margin-right:13px;padding:10px;margin-top:23px;"></div>
		<div id="enlargedDIVForMandals" class="yui-skin-sam">
			<div id="enlargedDIV"></div>
		</div>
		<div id="partiesPerformanceGraphDistrict">
				<div id="partiesPerformanceCarousel" class="yui-skin-sam">
					<ul>
					<li>
						<div id="presentYearResultsEnlargedChartName"> 
							<input type="button" class="button" style="margin-bottom:-6px;margin-left:673px;margin-top:11px;" onclick="showDetailedEnlargedChart(enlargedPresentYearResultsChartName)" value="Detailed Chart">
						</div>
						<div id="allElectionResultsInDT"  class="allianceListDiv">
							<div id="allElectionResultsInDT_head"></div>
							<div id="allElectionResultsInDT_body"><img src="charts/${presentYearResultsChartName}"/></div>
							<div id="indPartyDescription" ><%=indPartyDesc%></div>
						
						</div>
					</li>
					<li>
						<div id="previousYearResultsEnlargedChartName">
							<input type="button" style="margin-bottom:-6px;margin-left:673px;margin-top:11px;" class="button" onclick="showDetailedEnlargedChart(enlargedPreviousYearResultsChartName)" value="Detailed Chart">
						</div> 
						<div id="positionsGraphDiv" class="allianceListDiv">
							<div id="electionHirarchiDiv" ></div>
							<div id="partyPositionsDiv"><img src="charts/${previousYearResultsChartName}"/></div>
							<div id="indPartyDescription"><%=indPartyDesc%></div>
						</div>
					</li>
					</ul>
				</div>
		</div>

		<div id="detailedChartDiv" style="position:absolute;top:750px;left:150px;z-index:1;"></div>
		<!--District Page MP, MLA Div-->
		<div id="mpMla_main">
			<div id="mp_main_div">
				<div id="mp_head">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
						<tr>
							<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
							<td>	
								<div id="mpInfoDivHead" class="districtPageRoundedHeaders_center" style="width:863px;">
									<span>Winners (2009) in  Bye-Election Constituencies</span>
								</div>
							</td>
							<td><img src="images/icons/districtPage/header_right.gif"/></td>
						</tr>
					</table>
				</div>
				<div id="mp_body" class="yui-skin-sam">
					<div id="mpsInfoDivBody">
					</div>
				</div>
			</div>

		

			<!--<div id="mla_main_div">
				<div id="mla_head">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
						<tr>
							<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
							<td>	
								<div id="mlaInfoDivHead" class="districtPageRoundedHeaders_center" style="width:863px;">
									<span>Mandal Details</span>
								</div>
							</td>
							<td><img src="images/icons/districtPage/header_right.gif"/></td>
						</tr>
					</table>
				</div>
				<div id="mla_body" class="yui-skin-sam">
					
				</div>
			</div>-->
		</div>
		
	</div>
	
	
	<script language="javascript">
	
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

<c:forEach var="staticParties" items="${partiesList}">
var partyObj =	{
				partyId:"${staticParties.id}",
				partyName:"${staticParties.name}"
			};
biElectionObj.staticParties.push(partyObj);			
</c:forEach>
	<c:forEach var="winningCandidates" items="${winningCandidatesList}">
	var winCandidatesObj =	{
			distName:'<a class="viewAncs" title="Click to view ${winningCandidates.districtName} District Page" href="districtPageAction.action?districtId=${winningCandidates.districtId}&districtName=${winningCandidates.districtName}">${winningCandidates.districtName}</a>',
			constName:'<a class="viewAncs" title="Click to view ${winningCandidates.constituencyName} Constituency Page" href="constituencyPageAction.action?districtId=${winningCandidates.districtId}&constituencyId=${winningCandidates.constituencyId}">${winningCandidates.constituencyName}',
			name: "${winningCandidates.candidateName}",
			partyName: "${winningCandidates.partyName}",
			votesPercent: "${winningCandidates.votesPercentage}",
			marginVotes: "${winningCandidates.marginVotes}",
			marginPercent: "${winningCandidates.votesMargin}",
			completeResults: '<A href="javascript:{}" class="viewAncs" title="Click to view detailed election results" onclick="getMoreDetails(${winningCandidates.constituencyId},electionType,electionYear)">More Results</A>'
					
				};
	dtArray.push(winCandidatesObj);
				
</c:forEach>
	initializeBiElectionPage();
	
    var allianceCarousel = new YAHOO.widget.Carousel("partiesPerformanceCarousel",
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 1,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});
	allianceCarousel.registerPagination('<span class="carouselTitleSpan">Parties Performance In Bye-Election Constituencies</span>'); 
	allianceCarousel.render(); 
	allianceCarousel.show();
	</script>
	</body>
	</html>
