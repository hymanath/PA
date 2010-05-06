<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Election Results Analysis Report</TITLE>
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


<SCRIPT>
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getElectionsTypesInState")
								{	
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									populateElectionTypeDropdown(myResults);
								}
								else if(jsObj.task == "getElectionsYears")
								{	
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									populateElectionYearDropdown(myResults);
								}
								else if(jsObj.task == "getStaticParties")
								{										
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									populatePartiesDropdown(myResults);
								}
								else if(jsObj.task == "getBasicAnalysisDetails")
								{										
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									showBasicAnalysisDetails(myResults,"toolsTrue");
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
								else if(jsObj.task == "getConstituencyStatusAnalysisForVotesMarginWindow")
								{
									
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
function getEletionTypesInState(id)
{	
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';

	var electionTypesEl = document.getElementById("electionTypeSelectEl"); 
	var noOfElectionTypesElOptions = electionTypesEl.options;
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	var noOfElectionYearsElOptions = electionYearsEl.options;
	var partySelectEl = document.getElementById("partySelectEl");
	if(noOfElectionTypesElOptions.length != 0)
	{
		electionTypesEl.selectedIndex= '0';
	}
	if(noOfElectionYearsElOptions.length != 0)
	{
		electionYearsEl.selectedIndex= '0';
	}
	partySelectEl.selectedIndex= '0';	
	var jsObj= 
	{
	 	stateId: id,				
		task:"getElectionsTypesInState"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionTypesAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getEletionYears(electionType,electionTypeId)
{
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	var noOfElectionYearsElOptions = electionYearsEl.options;
	var partySelectEl = document.getElementById("partySelectEl");
	if(noOfElectionYearsElOptions.length != 0)
	{
		electionYearsEl.selectedIndex= '0';
	}
	partySelectEl.selectedIndex= '0';
	var jsObj= 
	{
		electionType: electionType,
		electionTypeId: electionTypeId,
		stateID:"1",			
		task:"getElectionsYears"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionYearsAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getStaticParties()
{
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';
	var jsObj= 
	{					
		task:"getStaticParties"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/partiesAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getBasicAnalysisDetails(id)
{
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';
	var stateSelectEl = document.getElementById("stateSelectEl");
	var electionTypesEl = document.getElementById("electionTypeSelectEl");
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	var stateId =stateSelectEl.value;	
	var electionType = electionTypesEl.options[electionTypesEl.selectedIndex].text;
	var electionYear = electionYearsEl.options[electionYearsEl.selectedIndex].text;	
	var electionTypeId = electionTypesEl.options[electionTypesEl.selectedIndex].value;	
	
	var jsObj= 
	{
	 	electionYear: electionYear,
		stateId: stateId,
		electionType: electionType,
		electionTypeId: electionTypeId,
		partyId: id,		
		task:"getBasicAnalysisDetails"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionResultsAnalysisAjaxAction.action?"+param;
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
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;	

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear;
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
}

function openMainPartyElectionResultsAnalysisWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear,position)
{
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;	

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&windowTask=mainPartyResultsAnalysisPopup";
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
}

function openMainPartyElectionResultsAnalysisCategoryWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear,position,categoryId)
{
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;	

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&categoryId="+categoryId+"&windowTask=mainPartyCategoryAnalysisPopup";
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
}


function openMainPartyMultipleReasonsAnalysisWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear,position,reasonCount,constituencyCount)
{
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;	

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&reasonCount="+reasonCount+"&constituencyCount="+constituencyCount+"&windowTask=multipleReasonAnalysisPopup";
	/*var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();*/
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


function showMarginCountAnalysisForConstituenciesPopup(index,partyId,status)
{
	index = index+1;
	if(status == "WON")
		rank = 1;
	else if(status == "LOST")
		rank = 0;

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAction.action?electionId="+electionId+"&electionYear="+electionYear+"&electionTypeId="+electionTypeId+"&electionType="+electionType+"&partyId="+partyId+"&rank="+rank+"&clickIndex="+index+"&resultStatus="+status+"&windowTask=mainPartyMarginCountAnalysisPopup";
	var browser1 = window.open(urlStr,"partyElectionResultsPopup","scrollbars=yes,height=600,width=1300,left=200,top=200");
	
	browser1.focus();
}

function showMarginCountAnalysisForAnalyzedConstituenciesPopup(index,partyId,status)
{
	index = index+1;
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;	
	var position = '';
	if(status == "WON")
		position = "Won";
	else if(status == "LOST")
		position = "Lost";

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&clickIndex="+index+"&resultStatus="+status+"&windowTask=mainPartyMarginCountAnalyzedConstituenciesPopup";
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
}

function showMarginCountAnalysisForCategory(index,partyId,categoryId,status)
{	
	index = index+1;
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;	
	var position = '';
	if(status == "WON")
		position = "Won";
	else if(status == "LOST")
		position = "Lost";
			
	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&clickIndex="+index+"&categoryId="+categoryId+"&resultStatus="+status+"&windowTask=mainPartyMarginCountAnalyzedCategoryPopup";
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
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

function openPreYearStatewiseAnalysisWindow(electionType,electionTypeId,electionYear,partyId)
{
	var browser1;
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.options[stateSelectEl.selectedIndex].value;
	var stateName = stateSelectEl.options[stateSelectEl.selectedIndex].text;
	var selectYearEl = document.getElementById("selectYearAnalysisTool");
	var electionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var yearAlertEl = document.getElementById("yearAlertAnalysisReport");//stateName
	var urlStr = "<%=request.getContextPath()%>/electionResultsAnalysisReportPopupAction.action?stateId="+stateId+"&stateName="+stateName+"&electionType="+electionType+"&electionYear="+electionYear+"&electionTypeId="+electionTypeId+"&partyId="+partyId+"";
	if(electionYear == 'Select Year') 
	{
		yearAlertEl.style.display ='block';
		yearAlertEl.innerHTML = "Please Select A Year!";
		return;
	}
	else {yearAlertEl.style.display ='none';} 
	
	browser1 = window.open(urlStr,"electionResultsAnalysisPopup","scrollbars=yes,height=600,width=700,left=200,top=200");
	browser1.focus();
		
}

function openStatewiseElectionResultsWindow(currentYear,stateName,electionType)
{	
	var selectYearEl = document.getElementById("selectYearERR");
	var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var yearAlertSEl = document.getElementById("yearAlertERR");
	var browser1;
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;
	var urlStr = "<%=request.getContextPath()%>/statewiseElectionResultsComparisionToolAction.action?stateID="+stateId+"&stateName="+stateName+"&electionType="+electionType+"&currentElectionyear="+currentYear+"&selectedElectionYear="+selectedElectionYear+"";
	if(selectedElectionYear == 'Select Year') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Year!";
		return;
	}else {yearAlertSEl.style.display ='none';} 
	
	browser1 = window.open(urlStr,"electionResultsReport","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
}
function openDistwiseElectionResultsWindow(currentYear,stateName,electionType)
{
	var selectYearEl = document.getElementById("selectYearERR1");
	var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var yearAlertEl = document.getElementById("yearAlertERR1");
	var browser1;
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;
	var urlStr = "<%=request.getContextPath()%>/districtwiseElectionResultsAnalysysForElectionReportAction.action?stateID="+stateId+"&stateName="+stateName+"&electionType="+electionType+"&currentElectionyear="+currentYear+"&selectedElectionYear="+selectedElectionYear+"";
	if(selectedElectionYear == 'Select Year') 
	{
		yearAlertEl.style.display ='block';
		yearAlertEl.innerHTML = "Please Select A Year!";
		return;
	}
	else {yearAlertEl.style.display ='none';} 
	
	browser1 = window.open(urlStr,"electionResultsReport1","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
}
function openPartyPerformanceWindow(electionTypeId)
{
	var selectYearEl = document.getElementById("selectYearPPR");
	var year =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var selectPartyEl = document.getElementById("selectPartyPPR");
	var party =  selectPartyEl.options[selectPartyEl.selectedIndex].value;
	var allianceCheckboxEl = document.getElementById("pprCheckBox");
	var alliances = allianceCheckboxEl.checked;
	var yearAlertSEl = document.getElementById("yearAlertPPR");
	var reportLevel = "1";
	var browser1;
	var stateSelectEl = document.getElementById("stateSelectEl");
	var stateId =stateSelectEl.value;
	var urlStr = "<%=request.getContextPath()%>/partyPerformanceReportPopup.action?state="+stateId+"&country=1&district=0&1="+reportLevel+"&electionType="+electionTypeId+"&year="+year+"&party="+party+"&alliances="+alliances;
	if(year == 'Select Year' && party == '0') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select Year and Party!";
		return;
	}else {yearAlertSEl.style.display ='none';}
	if(year == 'Select Year') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Year!";
		return;
	}else {yearAlertSEl.style.display ='none';}
	if(party == '0') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Party!";
		return;
	}else {yearAlertSEl.style.display ='none';} 
		
	browser1 = window.open(urlStr,"partyPerformanceReport","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
}
</SCRIPT>

</HEAD>
<BODY>
	<DIV id="page_layout_main" class="yui-skin-sam"></DIV>
	<DIV id="page_layout_right">
		<DIV id="sideHeader"></DIV>
		<DIV id="toolsDiv"></DIV>	
	</DIV>
	<DIV id="page_layout_center">
		<DIV id="pageHeading" >
			<TABLE cellspacing="0" cellpadding="0" border="0" width="90%">
				<TR>
					<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png" border="none"/></TD>
					<TD valign="top"><DIV class="mainHeading">Election Results Analysis Report</DIV></TD>
					<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png" border="none"/></TD>
				</TR>
			</TABLE>
		</DIV>	
		<DIV id="inputsTags" style="border:2px solid #DBDCDB;margin-left:15px;margin-right:15px;">
				<TABLE width="100%" class="inputsTable">
				<CAPTION>Please select the following options to view Report</CAPTION>
					<TR>	
						<TH >State</TH>
						<TD >
						<s:select id="stateSelectEl" theme="simple" name="stateSelectEl" cssClass="selectWidth" list="statesList" listKey="id" listValue="name" onchange="getEletionTypesInState(this.options[this.selectedIndex].value)"/>												
						</TD>
						<TH>Election Type</TH>
						<TD>
						<SELECT id="electionTypeSelectEl" name="electionTypeSelectEl" class="selectWidth" onchange="getEletionYears(this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">							
						</SELECT>
						</TD>
					</TR>						
					<TR>
						<TH>Year</TH>
						<TD>
						<SELECT id="electionYearSelectEl" name="electionYearSelectEl" class="selectWidth" onchange="getStaticParties()">							
						</SELECT>
						</TD>
						<TH >Party</TH>
						<TD >
						<select id="partySelectEl" name="partySelectEl" class="selectWidth" onchange="getBasicAnalysisDetails(this.options[this.selectedIndex].value)"/>						
						</TD>
					</TR>					
				</TABLE>			
		</DIV>
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
	</DIV>	
<SCRIPT>
initializePage();
</SCRIPT>
</BODY>
</HTML>