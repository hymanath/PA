<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

 <%@page import="com.itgrids.partyanalyst.dto.StatePageVO" %>
 <%@page import="com.itgrids.partyanalyst.dto.PartyResultsVO" %>
 <%@page import="com.itgrids.partyanalyst.dto.StateElectionsVO" %>
 <%@page import="java.util.ArrayList" %>
 <%@page import="java.util.List" %>

<HTML>
 <HEAD>
  <TITLE> <c:out value="${statePage.stateName}" /> News,Elections, districts,Constituencies,Census, Election Results</TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  
  <META NAME="Keywords" CONTENT="<c:out value='${statePage.stateName}'/> State page, About <c:out value='${statePage.stateName}'/>, <c:out value='${statePage.stateName}'/> Elections, <c:out value='${statePage.stateName}'/> Elections Results, <c:out value='${statePage.stateName}'/> Leaders,  <c:out value='${statePage.stateName}'/> Parties, <c:out value='${statePage.stateName}'/> Problems, <c:out value='${statePage.stateName}'/> Politics, <c:out value='${statePage.stateName}'/> MLA's, <c:out value='${statePage.stateName}'/> MP's,<c:out value='${statePage.stateName}'/> Voting Trends,<c:out value='${statePage.stateName}'/> MPTC, <c:out value='${statePage.stateName}'/> ZPTC, <c:out value='${statePage.stateName}'/> Municipality,<c:out value='${statePage.stateName}'/> Cross Voting,<c:out value='${statePage.stateName}'/> Constituencies">

  <META NAME="Description" CONTENT=" <c:out value='${statePage.stateName}'/> State page ,State page provides the outline and basic information ,districts information,districts election results, census information and latest news of the state.<c:out value='${statePage.stateName}'/> State page provides Assembly election results, Parliament election results, MPTC election results, ZPTC election results, Municipal election results, Corporation election results of all election years.">
	
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
	

	<!-- Local Files-->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<script type="text/javascript" src="js/statePage/statePage.js"></script>
	<link  rel="stylesheet" type="text/css" href="styles/homePage/homePage.css"/>
	<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">

  <style type="text/css">
	.distAnchor
	{
		text-decoration:none;
		color:black;
		font-family:verdana;
		
	}
		
  </style>

	<script type="text/javascript">

		google.load("elements", "1", {packages : ["newsshow"]});

		function callAjax(param)
		{
			var myResults;
			var url = "<%=request.getContextPath()%>/stateElectionResultsAjax.action?"+param;
			var callback = {			
						   success : function( o ) {
								try {
									myResults = YAHOO.lang.JSON.parse(o.responseText);									
									showElectionResults(param,myResults.stateElectionResults.partyResultsVO);								
								}catch (e) {   
								//	alert("Invalid JSON result" + e);   
								}  
						   },
						   scope : this,
						   failure : function( o ) {
									//	alert( "Failed to load result" + o.status + " " + o.statusText);
									 }
						   };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
		}

 </script>
 </HEAD>

<BODY>
<div id="statePage_main">
	<!--<div id="maskDiv"></div>-->
	<div id="electionResultsPopupDiv" class="yui-skin-sam"><div id="electionResultsPopupDiv_inner"></div></div>
	<div id="statePage_header">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img border="none" src="images/icons/electionResultsAnalysisReport/first.png"></td>
				<td><div id="statePageHeading"><span id="stateNameSpan"><c:out value="${statePage.stateName}" /> State Details</div></span></td>
				<td><img border="none" src="images/icons/electionResultsAnalysisReport/second.png"></td>
			</tr>
		</table>
	</div>
	<div id="statePage_body">
			<div id="statePage_layout_main" class="yui-skin-sam"> </div>
			<div id="statePage_layout_right">
				<div id="stateInformation_main">					
					<div id="stateInformation_body" >
						<!--<div class="stateInformation_head">${statePage.stateName} At A Glance :${statePage.stateName} </div>-->
                        <div class="stateInformation_head">
							<table width="99%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
								<tr>
									<td width="8%"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="districtPageRoundedHeaders_center" style="height:14px;width:259px;"><span>${statePage.stateName} Outline </span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						 </div>
						<div id="stateCensusDiv_body">
						 <table border="0" cellpadding="0" cellspacing="0" class="stateDetailsTable" width="90%">
							 <tr>
							     <td> <img src="images/icons/districtPage/listIcon.png"/></td>
								 <th align="left" style="color:#1C4B7A;"><c:out value="State Capital "/> </th>
								 <td  align="left" style="color:#18325A;">: <c:out value="${statePage.adminCapital}" /></td>
							 </tr>
							 <tr>
							     <td> <img src="images/icons/districtPage/listIcon.png"/></td>
								 <th  align="left" style="color:#1C4B7A;"><c:out value="Total Districts"/></th>
								 <td align="left" style="color:#18325A;">: <c:out value="${districtNumber}" /></td>	
							 </tr>
							 <tr>
							    <td> <img src="images/icons/districtPage/listIcon.png"/></td>
								<th  align="left" style="color:#1C4B7A;"><c:out value="State Language"/> </th>
								<td  align="left" style="color:#18325A;">: <c:out value="${statePage.stateLanguage}" /></td>
							 </tr>
							 <tr>
							    <td> <img src="images/icons/districtPage/listIcon.png"/></td>
								<th  align="left" style="color:#1C4B7A;"><c:out value="State Song"/></th>
								<td align="left" style="color:#18325A;">: <c:out value="${statePage.stateSong}" /></td>	
							</tr>
						</table>
						</div>
						<c:if test="${censusVO != null }">
						<!--<div class="stateInformation_head">${statePage.stateName} Census Info : </div>-->
                        <div class="stateInformation_head">
							<table width="99%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
									<tr>
										<td width="8%"><img src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center" style="height:14px;width:259px;"><span>${statePage.stateName} Census Info </span></div></td>
										<td><img src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
							</table>
						</div>
						<div id="stateCensusDiv_body">
						    
							<table border="0" cellpadding="0" cellspacing="0" width="100%" class="stateDetailsTable">
								<tr>
								    <th></th>
									<th>Type</th>
									<th align="center">Total </th>
									<th align="center">Male </th>
									<th align="center">Female </th>
								</tr>
								
								<c:forEach var="census" items="${censusVO}">
								<tr>
                 					<td> <img src="images/icons/districtPage/listIcon.png"/></td>
									<td align="left" style="font-weight:bold;"><c:out value="${census.tru}"/></td>
									<td align="center"><c:out value="${census.totalPopulation }" /></td>
									<td align="center"><c:out value="${census.malePopulation }" /></td>
									<td align="center"><c:out value="${census.femalePopulation }" /></td>
								</tr>
								</c:forEach>
								
							</table>							
						</div>
						</c:if>

						<div class="productFeatureMain" style="margin-top:20px;">							
							 <div class="productFeatureHeader">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center">
											<span class="headerLabelSpan">
												${statePage.stateName} News 
											</span>
										</div>
									</td>
									<td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
								  </tr>
								</table>
							</div>
							<div id="stateNewsBody" class="productFeatureBody" style="overflow:hidden;width:304px;height:250px;">
								
							</div>						
						</div>

					</div>
				</div>			
			</div>
			<div id="statePage_layout_center">
				<div id="stateMap_main">				
					<object width="550" height="500">
						<param name="movie" value="images/stateMaps/${statePage.stateName}/stateMap.swf">
						 <param name="wmode" value="transparent"> 
						<embed wmode="transparent" src="images/stateMaps/${statePage.stateName}/stateMap.swf" width="550" height="500">
						</embed>
					</object>	
				</div>
               
			</div>

			<div id="statePage_electinoResults_nav_div">
				<table width = "100%" style="width:100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="vertical-align:top;width:250px;">
							<div id="electionTypesList_head"><u>Election Type : </u></div>
							<div id="electionTypesList"></div>
						</td>
						<td style="vertical-align:top;"><div id="electionTypesNYearsList" class="yui-skin-sam"></div></td>
					</tr>
				</table>
			</div>
	</div>	
</div>
	
	<script type="text/javascript">

		statePageObj.stateDetails.stateId = '${statePage.stateId}';
		statePageObj.stateDetails.stateName = '${statePage.stateName}';
		statePageObj.stateDetails.stateLanguage = '${statePage.stateLanguage}';
		statePageObj.stateDetails.stateSong = '${statePage.stateSong}';
		statePageObj.stateDetails.adminCapital = '${statePage.adminCapital}';

		searchHead = statePageObj.stateDetails.stateName;
		searchString = statePageObj.stateDetails.stateName+", India";

		<c:if test="${stateElections != null }">			
			<c:forEach var="state" varStatus="stat" items="${stateElections}">
					var obj = {
								electionId:'${state.electionId}',
								electionTypeId:'${state.electionTypeId}',
								electionType:'${state.electionType}',
								year:'${state.year}',
								subtype:'${state.electionSubtype}',
								partyResultsVO:[]
							  };
					<c:forEach var="party" varStatus="stat" items="${state.partyResultsVO}">
						var partyObj = {
											partyId:'${party.partyId}',
											partyName:'${party.partyName}',
											partyFlag:'${party.partyFlag}',
											seatsParticipated:'${party.seatsParticipated}',
											votesEarned:'${party.votesEarned}',
											percentage:'${party.percentage}',
											totalSeatsWon:'${party.totalSeatsWon}'
									   };
							obj.partyResultsVO.push(partyObj);
					</c:forEach>				
					statePageObj.electionResults.push(obj);
			</c:forEach>			
		</c:if>
		initializeStatePage();		
	</script>

 </BODY>
</HTML>


    

