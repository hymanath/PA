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

	<!-- Local Files-->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<script type="text/javascript" src="js/statePage/statePage.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">

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
	<link href="styles/assets/css/bootstrap.css" rel="stylesheet">

	<!-- YUI Dependency files (End) -->


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


	<style type="text/css">
	#statePageMainDiv
	{
	margin-left:auto;
	margin-right:auto;
	float:none;
	background:#FFFFFF;
	width:990px;
	margin-top:30px;
	}
	 #statePage_header
	{
	background: #06ABEA;
	border-radius: 3px;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    padding: 7px;
    text-align: left;
	}
	.districtPageRoundedHeaders_center {
    background-image: url("images/icons/districtPage/header_body.png");
    font-weight: bold;
    padding: 11px;
}
#stateCensusDiv_body
{
border-left:1px solid #E0E0D6;
border-right:1px solid #E0E0D6;
border-bottom:1px solid #E0E0D6;
margin-right: 2px;
}

#stateCensusDiv_body
{

font-size:12px;
}
.stateInformation_head
{
margin-top:10px;
margin-right:1px;
}
.productFeatureHeaderBackground_center {
  background-image: url("images/icons/homePage_new/blue_header_center.jpg");
    height: 40px;
	
}
.productFeatureHeaderBackground_center {
    border-bottom: 2px solid #E0E0D6;
}
.headerLabelSpan {
    font-size: 15px;
    font-weight: bold;
    top: 10px;
    position: relative;
    color: #FFFFFF;
    left: 20px;
}
.electionResultsDiv_body {
    height: 155px;
}

#statePage_layout_main .yui-layout-doc
{
width:auto;
 border-top: 2px solid #E0E0D6;
 border-bottom:2px solid #E0E0D6; 
 margin-top:20px;
 border-radius: 5px;
}
#statePage_layout_main .yui-layout-doc #yui-gen0{
left:none;
 border-left: 3px solid #E0E0D6;
 font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
}
#yui-gen2{
	width:auto;
	border-right: 0px solid #E0E0D6;
	border-left: 2px solid #E0E0D6;
	margin-left:1px;
	border-radius:3px;
}
#yui-gen3 .yui-layout-bd yui-layout-bd-nohd yui-layout-bd-noft{
	width:660px;
}

#statePage_electinoResults_nav_div{
 margin-left: 21px;
 margin-top: 10px;
 font-size:11px;
font-family: "lucida grande",tahoma,verdana,arial,sans-serif;

}
.partyResultsTable td a {
    color: #669900;
    font-weight: bold;
    text-decoration: underline;
}
#stateNewsBody,#stateNewsBody1,#stateNewsBody2{
border-left:1px solid #B3C1CE;
border-right:1px solid #B3C1CE;
border-bottom:1px solid #B3C1CE;
}
.electionResultsDiv{
line-height:1.7em;
}
#electionResultsPopupDiv_inner{
font-weight:bold;

font-size:12px;
}
/** #electionResultsPopupDiv_inner .yui-dt-liner a{ 
color:#669900;
}*/
.yui-dt-liner a
{
color:#669900;
text-decoration:underline;
font-weight:bold;
font-size:12px;
}
#electionResultsPopupDiv_inner_h
{
	text-align:center;
}
#yui-dt0-th-partyName-liner span a{
color:none;
}
#ministerDiv_body
{
	 border-left: 1px solid #E0E0D6;
    border-right: 1px solid #E0E0D6 ;
    border-bottom: 1px solid #E0E0D6;
	 padding-top: 8px;
	  padding-bottom: 8px;
}
#ministerDiv a:hover{
text-decoration:none;

}
#ministerDiv_body a:hover{
text-decoration:none;

}
#ministersOfState a:hover{
text-decoration:none;

}


/** Favorite Link Start**/


.favouritelink{position:fixed;bottom:72px;right:7px;height:37px;cursor:pointer;text-decoration:none; opacity:0.84; filter: alpha(opacity = 30);
 transition: opacity .25s ease-in-out;
 z-index:999999;
}
.favouritelink:hover {text-decoration:none;opacity:1; filter: alpha(opacity = 100);}
.favouritelink .favouritelink-title{display:none;}
.favouritelink:hover .favouritelink-title{display:inline-block; }
.favouritelink:hover .favouritelink-title h6{color:#fff;padding:9px 20px;margin:2px;margin-right:-4px;border-radius:7px; 
 }
.favouritelink .favouritelink-image{display:inline-block;}
.favouritelink .favouritelink-image img{vertical-align:middle;}
.bluegrad{background: #1e5799; /* Old browsers */
background: -moz-linear-gradient(top,  #1e5799 0%, #2989d8 50%, #207cca 51%, #7db9e8 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#1e5799), color-stop(50%,#2989d8), color-stop(51%,#207cca), color-stop(100%,#7db9e8)); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  #1e5799 0%,#2989d8 50%,#207cca 51%,#7db9e8 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  #1e5799 0%,#2989d8 50%,#207cca 51%,#7db9e8 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  #1e5799 0%,#2989d8 50%,#207cca 51%,#7db9e8 100%); /* IE10+ */
background: linear-gradient(to bottom,  #1e5799 0%,#2989d8 50%,#207cca 51%,#7db9e8 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1e5799', endColorstr='#7db9e8',GradientType=0 ); /* IE6-9 */
}
.favouritelink .favouritelink-close {position:absolute;top:-12px;right:5px;}
.favouritelink:hover .favouritelink-close {display:block}
/** Favorite Link End**/

.ministerTabCls{line-height:1.7em; margin-left: 2px;}
</style>
</head>
<body>

<s:set name="actionName" value="%{#context[@com.opensymphony.xwork2.ActionContext@ACTION_NAME]}"/>

<s:set name="parameters" value="%{#context[@com.opensymphony.xwork2.ActionContext@PARAMETERS]}"/>
<script>

  <%
      String environment = "local";
	  if(request.getRequestURL().indexOf("partyanalyst.com") != -1)
	  environment = "live";

    %>
	
var queryString='';

<s:iterator value="#parameters" var="param">	
	
	queryString+='<s:property value="%{#param.key}"/>'+'=';
	queryString+='<s:property value="%{#param.value}"/>'+',';
	
</s:iterator>


</script>


<script>
function savefavouriteLink(){

	var pageTitle = '${statePage.stateName}'+' News,Elections, districts,Constituencies,Census, Election Results';

	environment = '<%=environment%>';


	var jObj = {
				link: '${actionName}',
				queryString:queryString,
				pageTitle:pageTitle,
				environment:environment,
				task: 'saveFavouriteLink'
				
			};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "saveUserFavouriteLink.action?"+rparam;
	callAjaxTosaveUserFavouriteLink(jObj,url);

}

function callAjaxTosaveUserFavouriteLink(jObj,url){

	var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jObj.task == "saveFavouriteLink")
										$('.favouritelink').hide();
										alert("Link added successfully");																	
								}
							catch (e) {   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                }
		               };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);

}
</script>

<div id="statePageMainDiv">
	<div id="electionResultsPopupDiv" class="yui-skin-sam">
		<div id="electionResultsPopupDiv_inner">
		</div>
	</div>
	<c:if test="${sessionScope.UserType == 'PartyAnalyst' || sessionScope.UserType == 'FreeUser'}"> 
	<!-- <div style="float:right;margin-right:140px;">
	<input type="button" style="position:fixed;z-index:2;" class="btn btn-success" value="Add to favourite links" onClick="savefavouriteLink();" title="Click here to add this link to favourite links"/>
	</div> -->
	<div class="favouritelink">
	<a href="javaScript:{savefavouriteLink()}"   title="Click here to add this link to favourite links">
	<span class="favouritelink-title">
	<h6 class="bluegrad"> Add To Favourite </h6>
	</span>
	<span class="favouritelink-image">
	<img src="images/add2fav.png">
	</span>
	</a>
	<span class="favouritelink-close" onClick="hideFavouriteLink();" title="hide">
    <i class="icon-remove-sign"></i>
	</span>
	</div>
	</c:if>

	<div id="statePage_header">
		<c:out value="${statePage.stateName}" /> State Details

		<span style="float:right;">
			<g:plusone size="medium"></g:plusone>

			<script type="text/javascript">
			 (function() {
			  var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
				 po.src = 'https://apis.google.com/js/plusone.js';
				 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
			 })();
			</script>
		</span>
		<span style="float:right;">
			<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/statePageAction.action?stateId=${statePage.stateId}">
				Tweet</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
			</script>
		</span>
		<span style="float:right;margin-right: 20px;">
			<a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/statePageAction.action?stateId=${statePage.stateId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>
		</span>

	</div>

	<div id="statePage_body">
		<div id="statePage_layout_main" class="yui-skin-sam">
		</div>

		

		
		<div id="statePage_layout_right">
			<div id="stateInformation_main">					
					<div id="stateInformation_body" style="margin-top: 10px;">
						<!--<div class="stateInformation_head">${statePage.stateName} At A Glance :${statePage.stateName} </div>-->

						<c:if test="${electionGoverningBodyVO != null}">
						 <div id="ministerDiv" class="stateInformation_head">
							<table width="99%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
								<tr>
								<td width="8%"><img src="images/icons/districtPage/header_left.gif"/></td>
								<td>
								<div class="districtPageRoundedHeaders_center" style="height:14px;width:240px;">
								<span>Chief Minister of ${statePage.stateName}</span>
								</div>
								</td>
								<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
								</table>
						 </div>
						 
						 <div id="ministerDiv_body" style="margin-right: 2px;">
							
									<table>
									<tr>
									<td>
									<div>
									<a href="candidateElectionResultsAction.action?candidateId=${electionGoverningBodyVO.candidateId}">
									<img src="images/candidates/${electionGoverningBodyVO.candidateName}.jpg" width="100px" height="88px;"   style="margin:2px 0px 2px 2px;" alt="${electionGoverningBodyVO.candidateName}" title="Click here to view <c:out value='${electionGoverningBodyVO.candidateName}'/> Profile - Election Results, News, Photos, Videos"/></a>
									</div></td><td>
									<table class="ministerTabCls">
									<tr>
									
									<td style="font-size: 12px; font-weight: normal;" colspan="2"><a href="candidateElectionResultsAction.action?candidateId=${electionGoverningBodyVO.candidateId}" title="Click here to view <c:out value='${electionGoverningBodyVO.candidateName}'/> Profile - Election Results, News, Photos, Videos"><span style="color:#8B4724;font-weight:bold;font-size:11px;"><c:out value="${electionGoverningBodyVO.candidateName}"></c:out></span></a></td>
									</tr>
									<tr>
									<th style="font-size: 13px; font-weight: bold;">Party</th>
									<td style="font-size: 12px; font-weight: normal;">:&nbsp&nbsp<a href="partyPageAction.action?partyId=${electionGoverningBodyVO.partyId}"><span style="color:#8B4724;" title="Click here to view <c:out value='${electionGoverningBodyVO.partyName}'/> Details"><c:out value="${electionGoverningBodyVO.partyName}"></c:out></span></a></td>
									
									</tr>
									<tr>
									<th style="font-size: 13px; font-weight: bold;">FromDate</th>
									<td style="font-size: 12px; font-weight: normal;">:&nbsp&nbsp<c:out value="${electionGoverningBodyVO.startDate}"></c:out></td>
									</tr>
									
							</table></td></tr></table>
							
						 </div>
						 
						 <div id="ministersOfState" style="margin-left: 25px; margin-top: 13px;">
						 <a href="ministersPageAction.action?electionId=${electionGoverningBodyVO.electionId}" style="background:#6380BA;color:#FFFFFF;border: medium none;border-radius: 4px;font-weight: bold;padding: 3px 7px 4px;font-size: 13px;">
							View Ministers of ${statePage.stateName}
						</a>
						 </div>
						 </c:if>

                         <div class="stateInformation_head">
							<table width="99%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
								<tr>
									<td width="8%"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="districtPageRoundedHeaders_center" style="height:14px;width:240px;"><span>${statePage.stateName} Outline </span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						 </div>
						<div id="stateCensusDiv_body">
						 <table border="0" cellpadding="0" cellspacing="0px" class="stateDetailsTable" width="90%">
							 <tr>
							      <td> <img src="images/icons/districtPage/listIcon.png"/>
								 </td>
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
										<td><div class="districtPageRoundedHeaders_center" style="height:14px;width:240px;"><span>${statePage.stateName} Census Info </span></div></td>
										<td><img src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
							</table>
						</div>
						<div id="stateCensusDiv_body">
						    
							<table border="0" cellpadding="0" cellspacing="0px" width="100%" class="stateDetailsTable">
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
						<c:if test="${electionGoverningBodyVO == null}">
						 <div class="productFeatureMain" style="margin-top:20px;margin-left:-12px;">							
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
							<div id="stateNewsBody" class="productFeatureBody" style="overflow:hidden;height:250px;">
								
							</div>						
						</div>
						</c:if>
					</div>
				
		</div>
		<div id="statePage_layout_center">
				<div id="stateMap_main">				
					<object width="550" height="500">
						<param name="movie" value="images/stateMaps/${statePage.stateName}/stateMap.swf">
						 <param name="wmode" value="transparent"> 
						<embed wmode="transparent" src="images/stateMaps/${statePage.stateName}/stateMap.swf" width="550" height="500" style="margin-left: 40px; margin-top: 15px;">
						</embed>
					</object>	
				</div>
               
			</div>
	
		</div>

			<c:if test="${electionGoverningBodyVO != null}">
				<div class="productFeatureMain" style="margin-top:15px;">							
							  <div class="productFeatureHeader" id="productFeatureHeaderDiv" style="">
								
										<div class="productFeatureHeaderBackground_center" style="background:#06ABEA;border-radius: 3px 3px 3px 3px; text-align: left; height: 30px;">
											<span class="headerLabelSpan" style="left: 16px; top: 6px;">
												${statePage.stateName} News 
											</span>
										</div>
									
							</div>
							<table width="100%" cellspacing="10"><tr><td>
							<div id="stateNewsBody" class="productFeatureBody" style="overflow:hidden;height:auto;">
								
							</div></td><td>
							<div id="stateNewsBody1" class="productFeatureBody" style="overflow:hidden;height:auto;">
							</div></td>
							<td>
							<div id="stateNewsBody2" class="productFeatureBody" style="overflow:hidden;height:auto;"></div>
							</td>
							</tr></table>
						</div>
			
	</c:if>	
			
		<div id="statePage_electinoResults_nav_div">
				<table width = "100%" style="width:100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="vertical-align:top;width:250px;">
							<div id="electionTypesList_head"><u>Election Type : </u></div>
							<div id="electionTypesList"></div>
						</td>
						<td style="vertical-align:top;"><div id="electionTypesNYearsList" class="yui-skin-sam" style="width:840px;"></div></td>
					</tr>
				</table>
			</div>
	
	</div>
</div>

<script type="text/javascript">
		var stateName = "${statePage.stateName}"; 
		var eleGoverningBodyVOValue ="${electionGoverningBodyVO}" ;

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
		function hideFavouriteLink(){
		$('.favouritelink').hide();
		}
		
	</script>

</body>
</html>