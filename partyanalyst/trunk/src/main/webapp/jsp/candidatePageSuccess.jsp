<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Details</title>

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
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/2.8.0r4/build/carousel/assets/skins/sam/carousel.css">


<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="js/candidatePage/candidatePage.js"></script>
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">	


</head>
<body class="yui-skin-sam">
<div id="CandidatePageMainDiv">
	
	<div id="CandidatePageLayoutDiv">
		<div id="cand_elect_div" class="yui-skin-sam">
			<div id="cand_elec_div_panel">
		</div>
	</div>
	<div id="CandidatePageTopNavLinksDiv" class="topNavLinksClass">
		<div id="candidateTopLinksMain" class="yui-skin-sam">
			<div id="navigationHead" class="yuimenubar yuimenubarnav"> 
				<div class="bd"> 
					<ul class="first-of-type"> 
						<li class="yuimenubaritem"> 
							<a id="profileAnc" class="yuimenubaritemlabel" href="javascript:{}" onclick="showTopMenuContent(this.id)" style="color:#FFFFFF">Profile</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a id="newsAnc" class="yuimenubaritemlabel" href="javascript:{}" onclick="showTopMenuContent(this.id)">News</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a id="photoAnc" class="yuimenubaritemlabel" href="javascript:{}" onclick="showTopMenuContent(this.id)">Photo</a> 
						</li> 
						<li class="yuimenubaritem"> 
							<a id="videoAnc" class="yuimenubaritemlabel" href="javascript:{}" onclick="showTopMenuContent(this.id)">Video</a> 
						</li> 
						<li class="yuimenubaritem" style="border:none;"> 
							<a id="developmentsAnc" class="yuimenubaritemlabel" href="javascript:{}" onclick="showTopMenuContent(this.id)">Developments</a> 
						</li> 
					</ul> 
				</div> 
			</div> 
		</div>
	</div>
	<div id="CandidatePageRightImageDiv">
		<div id="candidateImageDiv_Main" style="padding:5px;">
			<img id="candidateImage" height="300" width="225" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />ncb.jpg" >
		</div>		
	</div>
	<div id="CandidatePageBottomLinksDiv">
		<div id="candidatePageContent_electionPrf">
			<div id="candidatePageContent_electionPrf_head"></div>
			<div id="candidatePageContent_electionPrf_body"></div>
		</div>
	</div>
	<div id="CandidatePageCenterContentDiv">
		<div id="candidatePageContent_header" class="leftAlignText"></div>
		<div id="candidatePageContent_body" class="leftAlignText">
			<!-- Profile Info Div-->
			<div id="candidatePageContent_body_ProfileMain" class="candidateStaticContentDiv" style="display:block;">
				<div id="candidatePageContent_body_basicPrf">
					<div id="candidatePageContent_body_basicPrf_head">${candidateVO.candidateName} </div>
					<div id="candidatePageContent_body_basicPrf_body">
						<%
						    java.lang.String staticURL = (java.lang.String) request.getAttribute("candidateURLString");
							java.lang.String profileURL = staticURL + "/profile.jsp";
							java.lang.String newsURL = staticURL + "/news.jsp";
							java.lang.String photosURL = staticURL + "/photo.jsp";
							java.lang.String videosURL = staticURL + "/video.jsp";
							java.lang.String developmentsURL = staticURL + "/developments.jsp";
						%>

						<jsp:include page="<%= profileURL%>" flush="true" />
					</div>
				</div>
				<div id="candidatePageContent_body_electionPrf">
					<div id="candidatePageContent_body_electionPrf_head">${candidateVO.candidateName} 's Political Career:</div>
					<div id="candidatePageContent_body_electionPrf_body"></div>
				</div>
			</div>
			<!-- News Info Div-->
			<div id="candidatePageContent_body_NewsMain" class="candidateStaticContentDiv">
				<jsp:include page="<%= newsURL%>" flush="true"/>
			</div>
			<!-- Photo Info Div-->
			<div id="candidatePageContent_body_PhotoMain" class="candidateStaticContentDiv">
				<jsp:include page="<%= photosURL%>" flush="true"/>
			</div>
			
			<!-- Video Info Div-->
			<div id="candidatePageContent_body_VideoMain" class="candidateStaticContentDiv">
				<jsp:include page="<%= videosURL%>" flush="true"/>
			</div>
			
			<!-- Developments Info Div-->
			<div id="candidatePageContent_body_DevelopmentsMain" class="candidateStaticContentDiv">
				<jsp:include page="<%= developmentsURL%>" flush="true"/>				
			</div>
		</div>
	</div>
	
</div>

<script type="text/javascript">	
	candidateInfoObject.name = "${candidateVO.candidateName}";
	candidateInfoObject.candidateImgURL = "<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" ;
	candidateInfoObject.contextPath = "<%=request.getContextPath()%>";
	<c:forEach var="candidateElectionResults" items="${candidateElectionDetails}" >		
			var candidateObj={
								electionId:'${candidateElectionResults.electionId}',
								candidateName:'${candidateElectionResults.candidateName}',
								partyName:'${candidateElectionResults.partyName}',
								constituencyName:'${candidateElectionResults.constituencyName}',
								electionType:'${candidateElectionResults.electionType}',
								electionYear:'${candidateElectionResults.electionYear}',
								districtName:'${candidateElectionResults.districtName}',
								stateName:'${candidateElectionResults.stateName}',
								votesEarned:'${candidateElectionResults.votesEarned}',
								votePercentage:'${candidateElectionResults.votesPercentage}',
								status:'',
								oppositionCandidates:[]
							};
			<c:if test="${candidateElectionResults.status == true }">
				candidateObj.status='Won';
			</c:if>						
			<c:if test="${candidateElectionResults.status == false }">
				candidateObj.status='Lost';
			</c:if>

			<c:forEach var="detailedResult" items="${candidateElectionResults.oppositionCandidates}" >
				var oppositionList={
									candidateName:'${detailedResult.candidateName}',
									partyName:'${detailedResult.partyName}',
									votesEarned:'${detailedResult.votesEarned}',
									votesPercentage:'${detailedResult.votesPercentage}',
									status:''
								};
						<c:if test="${detailedResult.status == true }">
							oppositionList.status='Won';
						</c:if>
						<c:if test="${detailedResult.status == false }">
							 oppositionList.status='Lost';
						</c:if>
					candidateObj.oppositionCandidates.push(oppositionList);
			</c:forEach>			
			candidateInfoObject.candidateInfoArray.push(candidateObj);			
	</c:forEach>
	
	initializeCandidatePage();
</script>

</body>
</html>
  