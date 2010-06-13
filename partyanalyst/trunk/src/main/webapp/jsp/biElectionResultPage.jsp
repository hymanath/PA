
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Telangana Bi-Elections War - 2010</title>


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
		var electionYear = '${electionYear}';
		var electionType = '${electionType}';
		var presentYearResultsChartName = '${presentYearResultsChartName}';
		var previousYearResultsChartName = '${previousYearResultsChartName}';

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

		function getMandalVotingTrendz(distId,constId)
		{
			var jsObj=
			{
					districtId:distId,
					constituencyId:constId, 
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
										alert( "Failed to load result" + o.status + " " + o.statusText);
									 }
						   };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
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
				<td><div id="pageHeading"><span id="stateNameSpan"> Telangana Bi-Elections War - 2010</span></div></td>
				<td><img border="none" src="images/icons/statePage/header_right.png"></td>
			</tr>
		</table>
	    </div>
		<div>
		<br><br>
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
							<td width="100%"><div class="districtPageRoundedHeaders_center"><span> Bi Election Districts </span></div></td>
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
					<img src="images/icons/districtPage/telangana.png" border="0"/>			
				</div>
			</div>			
		</div>
		
		<div id="partiesPerformanceGraphDistrict">
				<div id="partiesPerformanceCarousel" class="yui-skin-sam">
					<ul>
					<li>
						<div id="allElectionResultsInDT"  class="allianceListDiv">
							<div id="allElectionResultsInDT_head"></div>
							<div id="allElectionResultsInDT_body"></div>
						
						</div>
					</li>
					<li>
						<div id="positionsGraphDiv" class="allianceListDiv">
							<div id="electionHirarchiDiv" ></div>
							<div id="partyPositionsDiv"></div>
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
								<div id="mpInfoDivHead" class="districtPageRoundedHeaders_center" style="width:793px;">
									<span>Bi-Election Constituencie Details</span>
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
			<div id="mla_main_div">
				<div id="mla_head">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
						<tr>
							<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
							<td>	
								<div id="mlaInfoDivHead" class="districtPageRoundedHeaders_center" style="width:793px;">
									<span>Mandal Details</span>
								</div>
							</td>
							<td><img src="images/icons/districtPage/header_right.gif"/></td>
						</tr>
					</table>
				</div>
				<div id="mla_body" class="yui-skin-sam">
					<div id="mlaInfoDivBody">
						<table>
						<tr><td align="center"><b><u>Karimnagar</u></b></td></tr>
						 <tr>
						      <td>
							     <ul class="dashBoardContentList"><li><b>Dharmapuri</b></li></ul>
								 <ul class="regionsList"><li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Dharmapuri" title="click to view Dharmapuri mandal details">Dharmapuri</a></li>
								     <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Velgatoor" title="click to view Velgatoor mandal details">Velgatoor</a></li>
								     <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Dharmaram"  title="click to view Dharmaram mandal details">Dharmaram</a></li>
								     <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Gollapalle"  title="click to view Gollapalle mandal details">Gollapalle</a></li>
								     <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Pegadapalle"  title="click to view Pegadapalle mandal details">Pegadapalle</a></li></ul>
							  </td>
							  <td>
							     <ul class="dashBoardContentList"><li><b>Huzurabad</b></li></ul>
                                  <ul class="regionsList"><li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Veenavanka" title="click to view Veenavanka mandal details">Veenavanka</a></li>
								      <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Jammikunta" title="click to view Jammikunta mandal details">Jammikunta</a></li>
								      <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Huzurabad" title="click to view Huzurabad mandal details">Huzurabad</a></li>
								      <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Kamalapur" title="click to view Kamalapur mandal details">Kamalapur</a></li></ul>
								      
							  </td>
							  <td>
							     <ul class="dashBoardContentList"><li><b>Koratla</b></li></ul>
								  <ul class="regionsList"><li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Ibrahimpatnam" title="click to view Ibrahimpatnam mandal details">Ibrahimpatnam</a></li>
								      <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Mallapur" title="click to view Mallapur mandal details">Mallapur</a></li>
								      <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Koratla" title="click to view Koratla mandal details">Koratla</a></li>
								      <li><a href="mandalPageElectionInfoAction.action?MANDAL_ID=110&MANDAL_NAME=Metpalle" title="click to view Metpalle mandal details">Metpalle</a></li></ul>
							  </td>
							  <td>
							     <ul class="dashBoardContentList"><li><b>Sircilla</b></li></ul>
								  <ul class="regionsList"><li>Yellareddipet</li>
								      <li>Gambhiraopet</li>
								      <li>Mustabad</li>
								      <li>Sircilla</li></ul>
							  </td>
							  <td><ul class="dashBoardContentList"><li><b>Vemulawada</b></li></ul>
							      <ul class="regionsList"><li>Medipalle</li>
								      <li>Kathlapur</li>
								      <li>Chandurthi</li>
								      <li>Vemulawada</li>
									  <li>Konaraopeta</li></ul>
							  </td>
                         </tr>
  
						</table>
					</div>
				</div>
			</div>
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
	<c:forEach var="winningCandidates" items="${winningCandidatesList}">
	var winCandidatesObj =	{
			distName:"${winningCandidates.districtName}",
			constName:"${winningCandidates.constituencyName}",
			name: "${winningCandidates.candidateName}",
			partyName: "${winningCandidates.partyName}",
			votesPercent: "${winningCandidates.votesPercentage}",
			marginPercent: "${winningCandidates.votesMargin}",
			completeResults: '<A href="javascript:{}" onclick="getMoreDetails(${winningCandidates.constituencyId},electionType,electionYear)">More Results</A>'
					
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

	allianceCarousel.render(); 
	allianceCarousel.show();
	</script>
	</body>
	</html>