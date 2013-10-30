<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:if test="${electionType != 'Parliament'}"><TITLE>${stateName} ${electionYear} ${electionType} Elections Results Analysis</TITLE></c:if>
<c:if test="${electionType == 'Parliament'}"><TITLE>${electionYear} ${electionType} Election Results Analysis  Results </TITLE></c:if>
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

<!-- Local Files-->
	<SCRIPT type="text/javascript" src="js/ElectionResultsAnalysisReport/electionResultsAnalysisReport.js"></SCRIPT>
	<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/electionResultsAnalysisReport.css">
	
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
	<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<SCRIPT type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
	var stateId = '${stateId}';
	var electionType = '${electionType}';
	var electionYear = '${electionYear}';
	var stateName = '${stateName}';
	var partyId = '${partyId}';
	var electionTypeId = '${electionTypeId}';
	var electionId = '${electionId}';
	
	function callAjax(param,jsObj,url){
		var myResults;
						
			var callback = {			
			               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task == "getBasicAnalysisDetails")
									{										
										var elmt = document.getElementById("electionPageAjaxImgDiv");
										if(elmt)
											elmt.style.display = 'none';
										showBasicAnalysisDetails(jsObj,myResults,"toolsFalse");
									}
									else if(jsObj.task == "getAnalysisDetailsInPartyLostPositions")
									{
										showAnalysisDetailsInPartyLostPositions(myResults);
									}
									else if(jsObj.task == "getAnalysisDetailsInPartyWonPositions")
									{
										showAnalysisDetailsInPartyWonPositions(myResults);
									}	
									else if(jsObj.task == "getVotesMarginInfo")
									{
										buildVotesMarginInfoContent(jsObj,myResults);
									}
									
							}catch (e) {   
									   	//alert("Invalid JSON result" + e);   
								}  
				               },
				               scope : this,
				               failure : function( o ) {
				                			//alert( "Failed to load result" + o.status + " " + o.statusText);
				                         }
				               };

				YAHOO.util.Connect.asyncRequest('GET', url, callback);
		}
	function getBasicAnalysisDetails()
	{
		var elmt = document.getElementById("electionPageAjaxImgDiv");
		if(elmt.style.display == 'none')
		elmt.style.display = 'block';
				
		var jsObj= 
		{
		 	electionYear: electionYear,
			stateId: stateId,
			electionType: electionType,
			electionTypeId: electionTypeId,
			partyId: partyId,
			electionId:electionId,
			task:"getBasicAnalysisDetails"		
		}
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/electionResultsAnalysisReportPopupAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	function getAnalysisDetailsInPartyWonPositions(electionType,electionYear,electionId,partyId){

		var jsObj= 
		{
		 	electionYear: electionYear,
			stateId: '1',
			electionType: electionType,
			partyId: partyId,
			electionId: electionId,		
			task:"getAnalysisDetailsInPartyWonPositions"		
		}
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/analysisCategoryResultForAPartyInAnElection.action?"+param;
		callAjax(param,jsObj,url);	
	}
	function getAnalysisDetailsInPartyLostPositions(electionType,electionYear,electionId,partyId){
		var jsObj= 
		{
		 	electionYear: electionYear,
			stateId: '1',
			electionType: electionType,
			partyId: partyId,
			electionId: electionId,		
			task:"getAnalysisDetailsInPartyLostPositions"		
		}
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/analysisCategoryResultForAPartyInAnElection.action?"+param;
		callAjax(param,jsObj,url);
	}
	function buildVotesMarginInfo(electionId,partyId,status)
	{	
		var jsObj= 
		{
		 	electionId: electionId,
			partyId: partyId,
			status: status,		
			task:"getVotesMarginInfo"		
		}
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/votesMaringInfoForMainPartyInAnElection.action?"+param;
		callAjax(param,jsObj,url);
	}
	

	function openPartyElectionResultsWindow(electionId,partyId,rank,partyName,electionType,stateName,electionYear,electionTypeId)
	{ 
	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAction.action?electionId="+electionId+"&partyId="+partyId+"&rank="+rank+"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&electionTypeId="+electionTypeId+"&windowTask=partyElectionResultsAnalysisPopup";
		var browser1 = window.open(urlStr,"partyElectionResultsPopup","scrollbars=yes,height=600,width=1300,left=200,top=200");
		
		browser1.focus();
	}
	
	function openPartyElectionResultsAnalysisWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear)
	{
		var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
		"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear;
		var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
		
		browser2.focus();	
	}
	function openMainPartyElectionResultsAnalysisWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear,position)
	{
		var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
		"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&windowTask=mainPartyResultsAnalysisPopup";
		var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
		
		browser2.focus();	
	}

	function openMainPartyElectionResultsAnalysisCategoryWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear,position,categoryId)
	{
		var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
		"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&categoryId="+categoryId+"&windowTask=mainPartyCategoryAnalysisPopup";
		var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
		
		browser2.focus();	
	}


	function openMainPartyMultipleReasonsAnalysisWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear,position,reasonCount,constituencyCount)
	{
		var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
		"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&reasonCount="+reasonCount+"&constituencyCount="+constituencyCount+"&windowTask=multipleReasonAnalysisPopup";
		var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
		
		browser2.focus();
	}
		
</SCRIPT>
</HEAD>
<BODY>
		<DIV id="electionPageAjaxImgDiv" style="display:none">
			<DIV>Please Wait..</DIV>
			<IMG src="images/icons/barloader.gif"/>
		</DIV>
		<DIV id="resultInfoDiv">
		<DIV id="basicDetailsHead" style="margin-top:10px;"></DIV>
		<DIV id="basicDetails" class="yui-skin-sam"></DIV>
		<DIV id="analysisDetails" class="analysisDetails">		
		<DIV id="tablerDetails"></DIV>
		</DIV>
		<DIV id="lostPosAnalisisDetails" class="analysisDetails"></DIV>
		<DIV id="wonPosAnalisisDetails" class="analysisDetails">		
		</DIV>						
		</DIV>
		<SCRIPT>
		getBasicAnalysisDetails();
		</SCRIPT>
</BODY>
</HTML>