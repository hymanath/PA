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

<script type="text/Javascript" src="js/homePage/jquery.js"></script>

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">

<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/partyElectionResultsReport.css">
<LINK rel="stylesheet" type="text/css" href="styles/CommentsDialog/commentsDialog.css">

<script type="text/javascript" src="js/ElectionResultsAnalysisReport/partyElectionResultsAnalysisReport.js"></script>
<script type="text/javascript" src="js/CommentsDialog/commentsDialog.js"></script>

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!-- JQuery files (End) -->

	<!-- Slider skin (optional) --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.2r1/build/slider/assets/skins/sam/slider.css">

	<!-- Slider source file --> 
	<script src="http://yui.yahooapis.com/2.8.2r1/build/slider/slider-min.js"></script>

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
		width:900px;
		margin-top:10px;
		border:1px solid #E1E6E9;
	}

	.constituencyAnalysisHeadDiv
	{
		color:#5F3F08;
		font-size:12px;
		font-weight:bold;
		/*padding:5px;*/
		word-spacing:6px;
		cursor:pointer;
		/*background-image:url("images/icons/indexPage/swasthic_body.png");*/
		background-image:url("images/icons/districtPage/header_body.png");
	}

	.constituencyAnalysisBodyDiv
	{
		padding:15px;		
		background-color:#FFFFFF;
	}

	.constituencyAnalysisBodyDiv table
	{
		width:100%;
	}
	#slider-bg
	{ 
		background:url(http://yui.yahooapis.com/2.8.2r1/build/slider/assets/bg-fader.gif) 5px 0 no-repeat; 
	}
	.yui-skin-sam .yui-h-slider {		
		width:110px;
	}

	.analysisLink
	{
		color:#4B481B;
		text-decoration:none;
	}

	.analysisLink:hover
	{
		text-decoration:underline;
	}

</style>


<SCRIPT type="text/javascript">
var electionId = '${electionId}';
var partyId = '${partyId}';
var electionType = '${electionType}';
var electionYear = '${electionYear}';
var status = '${status}'
var hidden=1;

function incrementHidden()
{
	hidden++;
}
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);

								var imgElmt = document.getElementById("barloaderGif");

								if(imgElmt.style.display == "block")
										imgElmt.style.display = "none"
								else if(imgElmt.style.display == "none")
									imgElmt.style.display == "block"

								if(jsObj.task == "getCandidateComments" || jsObj.task == "getMainPartyCandidateComments")
								{
									if(jsObj.status == "analyzed")
									{										
										showAnalysisDetails(jsObj,myResults);
									}
									else if(jsObj.status == "notAnalyzed")
									{										
										showNotAnalyzedDetails(jsObj,myResults);
									}
								}
								else if(jsObj.task == "getCommentsClassificationsList")
								{									
									buildCommentsClassificationsOptions(myResults);
								}
								else if(jsObj.task == "getPreviousComments")
								{									
									showPreviousComments(myResults,jsObj);
								}
								else if(jsObj.task == "addNewComment")
								{								
									updatePreviousCommentsDataTable(myResults);
								}
								else if(jsObj.task == "getMainPartyCategoryComments")
								{									
									showAnalysisDetails(jsObj,myResults);
								}
								else if(jsObj.task == "getMainPartyMultipleReasonsComments")
								{									
									//showAnalysisDetails(jsObj,myResults);
								}
								else if(jsObj.task == "getAnalyzedConstituencyStatusAnalysisForVotesMarginWindow")
								{
									showAnalysisDetails(jsObj,myResults);
								}
								else if(jsObj.task == "getConstituencyStatusAnalysisCategoryForVotesMarginWindow")
								{
									showAnalysisDetails(jsObj,myResults);
								}
								else if(jsObj.task == "getConstituencyAnalyzedComments")
								{
									showConstituencyAnalyzedComments(jsObj,myResults);
								}
						}
						catch (e) {   
						   	alert("Invalid JSON result" + e);   
					}  
	               },
	               scope : this,
	               failure : function( o ) {
	                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
	                         }
	               };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function getCandidateComments()
{
	var status = '${status}';
	var stateId = '${stateId}';
	var url = '';
	
	var jsObj= 
	{
		
	 	electionId: electionId,
	 	partyId: partyId,		
		status:status,
		stateId:stateId,
		task:"getCandidateComments"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	incrementHidden();

	if(status == "analyzed")
		url = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAjaxAction.action?"+param+"&hidden="+hidden;
	else if(status == "notAnalyzed")
		url = "<%=request.getContextPath()%>/partyElectionResultsNotAnalysedAjaxAction.action?"+param+"&hidden="+hidden;

	callAjax(param,jsObj,url);
}

function getMainPartyComments()
{
	var status = '${status}';
	var stateId = '${stateId}';
	var position = '${position}';
	var url = '';
	
	var jsObj= 
	{
		
	 	electionId: electionId,
	 	partyId: partyId,		
		status:status,
		stateId:stateId,
		position:position,
		task:"getMainPartyCandidateComments"		
	}
		
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	incrementHidden();

	if(status == "analyzed")
		url = "<%=request.getContextPath()%>/partyMainPartyElectionResultsAnalysisAjaxAction.action?"+param+"&hidden="+hidden;
	else if(status == "notAnalyzed")
		url = "<%=request.getContextPath()%>/partyMainPartyElectionResultsNotAnalysedAjaxAction.action?"+param+"&hidden="+hidden;

	callAjax(param,jsObj,url);
}

function getMainPartyCategoryComments()
{
	var status = '${status}';
	var stateId = '${stateId}';
	var position = '${position}';
	var categoryId = '${categoryId}';
	var url = '';
	
	var jsObj= 
	{
		
	 	electionId: electionId,
	 	partyId: partyId,		
		status:status,
		stateId:stateId,
		position:position,
		categoryId:categoryId,
		task:"getMainPartyCategoryComments"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	incrementHidden();	
	url = "<%=request.getContextPath()%>/partyMainPartyCategoryAnalysisAjaxAction.action?"+param+"&hidden="+hidden;

	callAjax(param,jsObj,url);
}


function getMainPartyMultipleReasonComments()
{
	
	var status = '${status}';
	var stateId = '${stateId}';
	var position = '${position}';
	var reasonCount= '${reasonCount}';
	var constituencyCount = '${constituencyCount}';
	var url = '';
	
	var jsObj= 
	{
		
	 	electionId: electionId,
	 	partyId: partyId,		
		status:status,
		stateId:stateId,
		position:position,
		reasonCount:reasonCount,
		constituencyCount:constituencyCount,
		task:"getMainPartyMultipleReasonsComments"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	incrementHidden();	
	url = "<%=request.getContextPath()%>/partyMainPartyMultipleReasonsAnalysisAjaxAction.action?"+param+"&hidden="+hidden;

	callAjax(param,jsObj,url);
}


function getMoreDetails(constiId)
{	

var urlStr = "<%=request.getContextPath()%>/constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+electionType+"&electionYear="+electionYear;	
var browser2 = window.open(urlStr,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
browser2.focus();
}

function getCommentsClassifications(rank)
	{ 
		var jsObj={
				rank: rank,
				task: "getCommentsClassificationsList"				
			  }	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/commentsClassificationDataAction.action?"+rparam;		
	callAjax(rparam,jsObj,url);
	}
	function showExistingComments(id,candidateName,category, constituencyId,constituencyName,partyName)
	{
		var candidateId;
		var constituencyId;
		if(category == "candidate")
		{
			candidateId = id;
			constituencyId = constituencyId;		
		}
		var jsObj={
				
				candidateId: candidateId,
				candidateName: candidateName,
				constituencyId: constituencyId,
				electionId: electionId,
				electionType: electionType,
				year: electionYear,
				candidateId: candidateId,
				constituencyId: constituencyId,
				constituencyName: constituencyName,
				partyName: partyName,			
				task:"getPreviousComments"				
			  }
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		incrementHidden();
		var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam+"&hidden="+hidden;		
		callAjax(rparam,jsObj,url);	
	}


	function handleAddCommentsSubmit(id,category,constituencyId)
	{
		var commentVal = document.getElementById("commentText").value; 
		var postedByVal = document.getElementById("commentPostedByText").value;
		var partyId;
		var candidateId;
		var constituencyId;
		var commentCategoryId;
		var alertMessageEl = document.getElementById("alertMessage"); 
		var reasonSeverityElmt = document.getElementById("slider-value");
		var reasonSeverityvalue = reasonSeverityElmt.innerHTML;
 
		if(category == "candidate")
		{
			var commentCategoryEl = document.getElementById("commentsClassificaitonSelectBox");
			if(commentCategoryEl)
			{
				commentCategoryId = commentCategoryEl.value;
				
			}	
			partyId = '0';
			candidateId = id;
			constituencyId = constituencyId;		
		}
		if(category == "party")
		{
			partyId = id;
			candidateId = '0';
			constituencyId = '0';
			commentCategoryId = '0';	
			
		}
		
		if(commentCategoryId == '' || commentVal == '' || postedByVal == '' || commentCategoryId == 'Select Classification' )		
		{
			alertMessageEl.innerHTML = 'Please Fill Mandatory Fields!';
			return;		
		}	
		if(reasonSeverityvalue == 0)
		{
			alertMessageEl.innerHTML = 'Please Select Reason severity value!';
			return;
		}
		if(commentCategoryId != '' && commentVal != '' && postedByVal != '')		
		{
			var jsObj={
					electionId: electionId,
					electionType: electionType,
					year: electionYear,
					partyId: partyId,
					candidateId: candidateId,
					constituencyId: constituencyId,
					commentDesc: commentVal,
					postedBy: postedByVal,
					category: category,
					commentCategoryId: commentCategoryId,
					reasonSeverityvalue: reasonSeverityvalue,
					task:"addNewComment"				
				  }	 
				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam;		
			callAjax(rparam,jsObj,url);	
			alertMessageEl.innerHTML = '';
		}
				
		
	}
	

	function handleAddCommentsCancel(task,status)
	{
		var openerDoc = window.opener;		

		if(task == "getMainPartyCandidateComments")
			getMainPartyComments();
		else if(task == "getCandidateComments")
			getCandidateComments();
		else if(task == "getMainPartyCategoryComments")
			getMainPartyCategoryComments();
		else if(task == "getAnalyzedConstituencyStatusAnalysisForVotesMarginWindow")
			getMarginCountAnalysisForAnalyzedConstituencies('${clickIndex}','${resultStatus}');
		else if(task == "getConstituencyStatusAnalysisCategoryForVotesMarginWindow")
			getMainPartyMarginCountAnalyzedCategoryResults('${clickIndex}','${resultStatus}','${categoryId}');
		
		$( "#commentsDialogDiv" ).dialog("destroy");

		//openerDoc.ajaxCallForBasicAnalysisDetails(openerDoc.electionYear,openerDoc.stateId,openerDoc.electionType,openerDoc.electionTypeId,openerDoc.partyId);
	}
													
	function getMarginCountAnalysisForAnalyzedConstituencies(index,resultStatus)
	{	
		var jsObj= 
		{				
			partyId: partyId,
			electionId:electionId,
			resultStatus:resultStatus,
			clickIndex:index,
			task:"getAnalyzedConstituencyStatusAnalysisForVotesMarginWindow"		
		}

		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/analyzedConstituencyStatusAnalysisForVotesMarginAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}

	
	function getMainPartyMarginCountAnalyzedCategoryResults(index,resultStatus,categoryId)
	{		
		var jsObj= 
		{			
			partyId: partyId,		
			electionId:electionId,
			resultStatus:resultStatus,
			clickIndex:index,
			categoryId:categoryId,
			task:"getConstituencyStatusAnalysisCategoryForVotesMarginWindow"		
		}
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/constituencyStatusAnalysisCategoryForVotesMarginAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}

	function doUnload()
	{
		window.opener.getBasicAnalysisDetails();
	}

</SCRIPT>
</HEAD>
<BODY onunload="doUnload()">
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

		<img id="barloaderGif" style="display:block;" src="images/icons/barloader.gif "/>
	</CENTER>


	
	<DIV id="analysisDetails"></DIV>
	<DIV class = "yui-skin-sam" id="commentsDialogDiv"></DIV>
	
<SCRIPT type="text/javascript"> 

	if('${windowTask}' == "partyElectionResultsAnalysisPopup")
	{	
		
		getCandidateComments();
	}
	else if('${windowTask}' == "mainPartyResultsAnalysisPopup")
	{
		getMainPartyComments();
	}
	else if('${windowTask}' == "mainPartyCategoryAnalysisPopup")
	{
		getMainPartyCategoryComments();
	}
	else if('${windowTask}' == "multipleReasonAnalysisPopup")
	{
		getMainPartyMultipleReasonComments();
	}
	else if('${windowTask}' == "mainPartyMarginCountAnalyzedConstituenciesPopup")
	{
		getMarginCountAnalysisForAnalyzedConstituencies('${clickIndex}','${resultStatus}');
	}
	else if('${windowTask}' == "mainPartyMarginCountAnalyzedCategoryPopup")
	{
		getMainPartyMarginCountAnalyzedCategoryResults('${clickIndex}','${resultStatus}','${categoryId}');
	}
	
	
</SCRIPT>
</BODY>
</HTML>