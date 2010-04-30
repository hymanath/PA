<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT> 
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></SCRIPT>

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">

<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/partyElectionResultsReport.css">

<script type="text/javascript" src="js/ElectionResultsAnalysisReport/partyElectionResultsAnalysisReport.js"></script>

<c:if test="${electionType != 'Parliament' && status =='analyzed'}">
	<TITLE>${stateName} ${electionType} ${electionYear} Election Results Analyzed Constituencies </TITLE>
</c:if>
<c:if test="${electionType != 'Parliament' && status =='notAnalyzed'}">
	<TITLE>${stateName} ${electionType} ${electionYear} Election Results Yet To Be Analyzed Constituencies</TITLE>
</c:if>
<c:if test="${electionType == 'Parliament' && status =='analyzed'}">
	<TITLE>${electionType} ${electionYear} Election Results Analyzed Constituencies</TITLE>
</c:if>
<c:if test="${electionType == 'Parliament' && status =='notAnalyzed'}">
	<TITLE>${electionType} ${electionYear} Election Results Yet To Be Analyzed Constituencies</TITLE>
</c:if>


<style>
	
	.constituencyAnalysisMainDiv
	{
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:12px;
		width:830px;
		margin-top:10px;
	}

	.constituencyAnalysisHeadDiv
	{
		color:#344D6E;
		font-size:12px;
		font-weight:bold;
		padding:5px;
		word-spacing:6px;
		cursor:pointer;
		background-image:url("images/icons/indexPage/swasthic_body.png");
	}

	.constituencyAnalysisBodyDiv
	{
		padding:15px;		
		background-color:#F5F7F9;
	}

	.constituencyAnalysisBodyDiv table
	{
		width:100%;
	}
</style>


<SCRIPT type="text/javascript">
var electionId = '${electionId}';
var partyId = '${partyId}';
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getCandidateComments")
								{
									showAnalysisDetails(myResults);
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
function getCandidateComments(){

	var jsObj= 
	{
		
	 	electionId: electionId,
	 	partyId: partyId,				
		task:"getCandidateComments"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}

function getMoreDetails(constiId)
{	
var electionType = '${electionType}';
var electionYear = '${electionYear}';
var urlStr = "<%=request.getContextPath()%>/constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+electionType+"&electionYear="+electionYear;	
var browser2 = window.open(urlStr,"candidateResults","scrollbars=yes,height=600,width=750,left=200,top=200");
browser2.focus();
}
													

</SCRIPT>
</HEAD>
<BODY>
	<CENTER>
	<c:if test="${electionType != 'Parliament' && status =='analyzed'}">
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${stateName} ${electionType} ${electionYear} Election Results Analyzed Constituencies</H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>
	</c:if>	
	<c:if test="${electionType != 'Parliament' && status =='notAnalyzed'}">
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${stateName} ${electionType} ${electionYear} Election Results Yet To Be Analyzed Constituencies</H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>	
	</c:if>
	<c:if test="${electionType == 'Parliament' && status =='analyzed'}">
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${electionType} ${electionYear} Election Results Analyzed Constituencies</H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>	
	</c:if>
	<c:if test="${electionType == 'Parliament' && status =='notAnalyzed'}">	
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${electionType} ${electionYear} Election Results Yet To Be Analyzed Constituencies</H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>	
	</c:if>
	</CENTER>
	
	<DIV id="analysisDetails"></DIV>
	
<SCRIPT type="text/javascript"> 
	getCandidateComments();
</SCRIPT>
</BODY>
</HTML>