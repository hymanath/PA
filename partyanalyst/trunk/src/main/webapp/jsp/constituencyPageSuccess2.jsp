     <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
	
	<html>
	<head> 

	<title><c:out value='${constituencyDetails.constituencyName}'/>&nbsp;
	<c:out value='${constituencyDetails.constituencyType}'/> Constituency Page - News, Details, Mandals, Parties Performance, Voting Trendz, Election Results,MLA, MP,MPTC, ZPTC Election Results</title>

    <META NAME="Keywords" CONTENT="<c:out value='${constituencyDetails.constituencyName}'/> Constituency, About <c:out value='${constituencyDetails.constituencyName}'/> Constituency, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Elections, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Elections Analysis,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Elections Results, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Leaders,  <c:out value='${constituencyDetails.constituencyName}'/> Constituency Parties, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Problems, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Politics, <c:out value='${constituencyDetails.constituencyName}'/> Constituency MLA's, <c:out value='${constituencyDetails.constituencyName}'/> Constituency MP's,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Voting Trends,<c:out value='${constituencyDetails.constituencyName}'/> Constituency MPTC, <c:out value='${constituencyDetails.constituencyName}'/> Constituency ZPTC, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Municipality, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Corporation,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Cross Voting,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Mandals">

    <META NAME="Description" CONTENT=" <c:out value='${constituencyDetails.constituencyName}'/> constituency page provides the outline and basic information ,mandals information,constituency election results and latest news of the state.<c:out value='${statePage.stateName}'/> constituency page provides Assembly election results, Parliament election results, MPTC election results, ZPTC election results, Municipal election results, Corporation election results of all election years.">

	<script type="text/javascript" src="js/constituencyPage/constituencyPage2.js"></script> 	
	<script type="text/javascript" src="js/connectPeople/connectPeopleContent2.js"></script>
	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>
	<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
	<script type="text/javascript" src="js/homePage/homePage.js"></script>
	<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script>
	<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>
	
	<!--<script type="text/javascript" src="js/jQuery/jquery-1.5.2.js"></script> --> 
	<script
	src="js/jQuery/jquery-ui.min.js">
		</script>
	<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
	<script src="${mapKey}" type="text/javascript"></script>
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	
	<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){   %>	
   <script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
   
   <% }
   %>
	
	<!-- For image display on mouseover -->
	
	<script type="text/javascript" src="js/overlib_mini.js"></script> 
	<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script> 
	
	
	<!-- Combo-handled YUI CSS files: --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/button/assets/skins/sam/button.css"> 

	<!-- Combo-handled YUI JS files: --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js"></script> 
	<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage2.css">	</link>
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

	<script type="text/javascript">
	var censusResultForParl = null;
	$(document).ready(function(){
	$("#MandalsDelimit a").click(function(){
		$("#MandalsDelimit a").removeClass("active");
		$(this).addClass("active activepinkdownarrow");
	});
	
	showMunicipalityResults1();
	//getMunicipalityResults();
	getMunicipalityResultsForAssemblies();
	

	$("#LocalElectionsNav a").click(function(){
		$("#LocalElectionsNav a").removeClass("active");
		$(this).addClass("active");
	});
	$("#LocalElectionsNavNew a").click(function(){
		$("#LocalElectionsNavNew a").removeClass("active1");
		$(this).addClass("active1");
	});
	
});
	google.load("visualization", "1", {packages:["corechart"]});
	google.load("elements", "1", {packages : ["newsshow"]});
	var userStatusType= "${userDetails.userStatusType}";
	var userType = "${sessionScope.USER.userStatus}";
	var stateName = '${constituencyDetails.stateName}';
	var districtName = '${constituencyDetails.districtName}';
	var constituencyName ='${constituencyDetails.constituencyName}'
	var constiId = '${constituencyId}'; 
	var consticType = '${constituencyDetails.constituencyType}';
	var parliamentconstituency='${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}'
	
	var changeReq = 0;
	
	
	var conn='${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName}';
	
	
	
	var parliamentcandidateName='${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}';
	
	var constituencyId = '${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyId}';

	var elecType = '${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType}';
	var elecYear = '${candidateDetailsForConstituency.assemblyCandidateInfo[0].latestElecYear}';
	var party='${candidateDetailsForConstituency.assemblyCandidateInfo[0].party}';
	var totalNoOflocalElectionsBodies = 0;
	
	var candidateId='${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateId}';
	var assembly='${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType == 'Assembly'}';
	var muncipalityPresent = false;
	var corporationPresent = false;
	var greaterPresent = false;
	var mptcPresent = false;
	var zptcPresent = false;
	var userName = '${sessionScope.UserName}';
		
	function setImage(img)
	{
		img.src = "images/constituencyPage/cp-thumb.jpg";
	}
	</script>
	<style>
     
	#mandalsVotersInfoDiv_Head{display:none;}
	#mandalsVotersInfoDiv_Body{width:auto;margin-left:auto;margin-right:auto;float:none;display:table;}

	.detailed-ele-inf * table,#mandalsVotersInfoDiv_Body div div table{border-collapse:collapse;padding:10px;}
 
	.detailed-ele-inf * th, #mandalsVotersInfoDiv_Body div div table th{text-align:left;
    padding:10px;font-weight:bold;background-color:#CDE6FC;font-size:13px;}

	#CorporationTableDiv_0 > table,#greaterTableDiv_0 > table,#elecResDiv > table,#elecResDivForPal > table,#parliamentElecResDiv > table{border-collapse:collapse;border:1px solid #d3d3d3;width:100%;}
	#CorporationTableDiv_0 > table * th,#greaterTableDiv_0 > table * th,#elecResDivForPal > table * th,#elecResDiv > table * th,#parliamentElecResDiv > table * th{height:20px;text-align:left;background-color:#ceedf0; padding:5px;font-weight:bold;border:1px solid #d3d3d3;}
	#CorporationTableDiv_0 > table * td,#greaterTableDiv_0 > table * td,#elecResDivForPal > table * td, #elecResDiv > table * td,#parliamentElecResDiv > table * td{padding:3px;padding-left:5px;font-weight:normal;border:1px solid #d3d3d3;}
	#elecResDivForPal > table * tr:nth-child(even),#elecResDiv > table * tr:nth-child(even),#parliamentElecResDiv > table * tr:nth-child(even){background:#f9f9f9;}
	#CorporationTableDiv_0 > table * a,#greaterTableDiv_0 > table * a,#elecResDivForPal > table * a,#elecResDiv > table * a{text-decoration:none;color:#3d3d3d;font-weight:bold;}
	
	.detailed-ele-inf * td, #mandalsVotersInfoDiv_Body div div table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
	.detailed-ele-inf * tr:nth-child(even),#mandalsVotersInfoDiv_Body div div table tr:nth-child(even){background:#f9f9f9;}
	
	.detailed-ele-inf * table * a,#mandalsVotersInfoDiv_Body div div table th a,#mandalsVotersInfoDiv_Body div div table td a{text-decoration:none;color:#3d3d3d;}
	
	.detailed-ele-inf * table *a:hover, #mandalsVotersInfoDiv_Body div div table th a:hover,#mandalsVotersInfoDiv_Body div div table td a:hover{text-decoration:underline;color:#000;}
	
	.cd-right-sec{float:left;margin-left:8px;}
	
	#inner-content-mainsec{width:1000px;;margin-left:auto;margin-right:auto;float:none;background:#fff;border-radius:5px;
	display:table;}
	
	.mvd-fields li a:hover, .mvd-fields li a.active{
	background: url("images/constituencyPage/pink-down-arrow.png") no-repeat scroll center bottom transparent;
	color: #9D5CB2;
    float: left;
    padding-bottom: 8px;}
	.mvd-fields li a:hover strong, .mvd-fields li a.active strong{background-color:#F5E4FA;}
	
	.detailed-ele-inf {
		display:table;
		padding-top: 0;
		position: relative;
		width: auto;
		margin-left: auto;
		margin-right: auto;
		float: none;
		Clear: both; 
	}
	#zptcPartyTrendsDetailsDiv > table{width:100%;}
	
	#censusSelect{
    margin-right: auto;
    padding-right: 0;
    width: 149px;}
	#announcementsInConstituencyDiv > table{border-collapse:collapse;border-bottom:1px solid #d3d3d3;width:100%;}
	.cp-cont-sub-fields li{border:none;}

	.yui-skin-sam .yui-dt table{border-collapse:collapse;border:1px solid #d3d3d3;}

	#electionYearsWithAssets_Panel_Div > table{width:100%;}

	#zptcDivBody{width:auto;margin-left:auto;margin-right:auto;}
	#mptcPartyTrendsDetailsDiv{width:230px;border:1px solid #d3d3d3;clear:both; margin-left: 17px;}
	#mptcPartyTrendsDetailsDiv > table{width:100%;}
	#zptcCandidateLink,#zptcElectionIdsSelectDiv{width:auto;display:inline-block;}
	#zptcElectionIdsSelectDiv{position:relative;padding-left:10px;top:-13px;}
	#mptcElectionIdsSelectDiv,#mptcCandidateLink{width:auto;display:inline-block;}
	#mptcElectionIdsSelectDiv{position:relative;top:-13px;padding-left:10px;}
	#zptcPartyTrendsDetailsDiv{border:1px solid #d3d3d3;}
	#CorporationTableDiv_0 > table * td, #greaterTableDiv_0 > table * td, #elecResDivForPal > table * td, #elecResDiv > table * td,#parliamentElecResDiv > table * td {
    font-weight: normal;
    font: small-caption;
   }
#CorporationTableDiv_0 > table * th, #greaterTableDiv_0 > table * th, #elecResDivForPal > table * th, #elecResDiv > table * th,#parliamentElecResDiv > table * th{background-color: #CDE6FC;}

.electionTrendzHeaderBackground_center
{	
	background-image:url(images/icons/homePage_new/newsheader_center.jpg);
	height:40px;
}
#elecResDivForPal caption ,#elecResDiv caption {background: none repeat scroll 0 0 #3897A5;
    color: #FFFFFF;
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 5px;
    margin-left: 344px;
    padding: 4px;
    width: 60%;}
#parliamentElecResDiv caption {background: none repeat scroll 0 0 #3897A5;
    color: #FFFFFF;
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 5px;
    margin-left: 344px;
    padding: 4px;
    width: 60%;}

#headingSpan{font-size:14px;font-weight:bold;background:#3897A5;color:#fff;
width:100px;margin:10px;}
.headingClass
{
color: #C66E17;
font-size: 15px;
font-weight: bold;
margin-bottom: 4px;
margin-left: 18px;
margin-top: 4px;
}


.our-services {
    border: 1px solid #CCCCCC;
    border-radius: 5px 5px 5px 5px;
    clear: both;
    display: block;
    float: right;
    margin-bottom: 20px;
	margin-top: 12px;
    width: 320px;
	margin-right: 0px;
	padding-bottom: 20px;
}
.our-services a {

	background: none repeat scroll 0 0 #97DFEB;
    border: 2px solid #FFFFFF;
    border-radius: 5px 5px 5px 5px;
    color: #000000;
    float: left;
    font-size: 14px;
    height: 42px;
    text-align: center;
    text-decoration: none;
    text-shadow: 0 0 5px #FFFFFF;
    width: 135px;
	margin-left:5px;
	margin-right:2px;
	margin-bottom:2px;
	margin-top:2px;

}
#parliamentElectionResultsDivNew{
  border: 1px solid #E7E7E7;
}


.opinionpoll .question{padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;}
.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;margin-left:19px;}
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}
.resultdisplay a{display:inline-block;text-decoration:none;}
.resultdisplay .previouslink{ float:left;}
.resultdisplay .nextlink{ float:right;}

.opinionpoll .answer .span2{margin-left:0px;}
.opinionpoll .answer span{margin-left:10px;}
.main-mbg{line-height:none !important;}
/*.main-title-sec {height:20px;}*/
#constituencyType{margin-bottom:5px;display:inline-block;}

/** Favorite Link Start**/

.muncipality table {border-collapse:collapse;border:1px solid #d3d3d3;width:100%;margin-top:10px;}
.muncipality table * th { background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;}
.muncipality table * td{
    font-weight: normal;
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;

   }
  .muncipality table * tr:nth-child(even){background:#f9f9f9;}
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
#mandalwisevotingTrendz{background:#fff !important;}
/** Favorite Link End**/

	  </style>
	  </head>
	<div class="clear"></div>

<!--	<div style="text-align:center;margin-bottom:10px;">
<script type="text/javascript"><!--
google_ad_client = "ca-pub-0938408694174139";
/* PartyPageHeader */
google_ad_slot = "2678494123";
google_ad_width = 728;
google_ad_height = 90;
//-->
<!--</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</div> -->

     <div class="main-title-sec">
        <div class="main-mbg"><div id="constituencyType"></div></div>
        <div class="main-bbg"></div>

		<div style="margin-top: 9px;">
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
			<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/constituencyPageAction.action?constituencyId=${constituencyId}">
				Tweet</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
			</script>
		</span>
		
		<span style="float:right;margin-right: 20px;">
			<a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/constituencyPageAction.action?constituencyId=${constituencyId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>
		</span>
		</div>
      </div>
      
	  

	  
	 <!--CIBSTUTYEBCT DETAILS LEFT SECTION START-->
      <body>

	
		<div id="inner-content-mainsec">
		<div id="logInDiv"></div>
		<div class="cd-left-sec">
        <div class="cd-mid-cont-sec">

  <p class="pa-fi"><b>State: </b><strong>
		  <a style="color: #247CD4;text-decoration: underline;" title="Click here to get ${constituencyDetails.stateName} State details" href="statePageAction.action?stateId=${navigationVO.stateInfo.id}">${constituencyDetails.stateName}</a> </strong>&gt;  
		  <b>District: </b><strong>
		  <c:forEach var="district" items="${navigationVO.districtInfo}">
					<a style="color: #247CD4;text-decoration: underline";  title="Click here to get ${district.name} District details" href="districtPageAction.action?districtId=${district.id}&districtName=${district.name}">${district.name}</a>
			</c:forEach></strong>
			<c:if test="${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType == 'Parliament'}"><s:if test="%{navigationVO.pcsInfo.size > 0}">&gt; 
		<b>Parliament:</b>

			<strong><c:forEach var="parliament" items="${navigationVO.pcsInfo}">
			 <a style="color:#247CD4;text-decoration:underline;"   title="Click here to get ${parliament.name} Parliament details"  href="constituencyPageAction.action?constituencyId=${parliament.id}">${parliament.name}</a>
			
			
		</c:forEach>	
		</strong>
		</s:if>
		</c:if> 
		

		 <c:if test="${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType == 'Assembly'}">
		 <s:if test="%{navigationVO.acsInfo.size > 0}">&gt;Assembly :
			<c:forEach var="assembly" items="${navigationVO.acsInfo}">
		 	 <strong>  <a   title="Click here to get ${assembly.name} Assembly details" href="constituencyPageAction.action?constituencyId=${assembly.id}">${assembly.name}</a>&nbsp;
			
			</c:forEach>
		</s:if>
		</c:if>
		<!--Constituency Type:&nbsp
		<strong>${constituencyDetails.constituencyType}</strong>-->
			 
		</p>

 <c:if test="${sessionScope.UserType == 'PartyAnalyst' || sessionScope.UserType == 'FreeUser'}"> 
	<!--<div style="float:right;margin-right:140px;">
	<input type="button" style="position:fixed;z-index:2;" class="btn btn-success" value="Add to favourite links" onClick="savefavouriteLink();" title="Click here to add this link to favourite links"/>
	</div>-->

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
         

<!--add-->
<!-- Constituency Details -->

          <!--ELECTED CANDIDATE INFO SECTION START-->

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
          
          <div class="cl-sub-fields-sec" style="margin-top:12px;">
            <h1 class="topone"></h1><h1 class="org-title"><span>Elected Candidate Info</span></h1>
			<c:if test="${constituencyDetails.deformDate !=null}">
			 
			
			<span style="clear:right;margin-left:171px;text-decoration: none;color:#5CB275;font-size:13px;"><b>This constituency has been delimited</b></span>
			
		         
			</c:if>
			<c:if test="${constituencyDetails.deformDate ==null}">
            <ul class="eci-fieds-sec">
              <li>
				 <c:if test="${constituencyDetails.constituencyType != 'Assembly'}">
				<div style="background-color: #FFF9E1;border: 1px solid #F4EED5;width:100%;">
				    <table style="width:100%">
				     <tr>
				        <td rowspan="2" style="width:20%;text-align:center;background-color: #FFF5CE;"><a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName} Profile - News, Photos and Videos" href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"><img  onerror="setImage(this)"  src="images/candidates/${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}.jpg" height="65" width="60"/></a> </td>                    
						<td style="width:30%;text-align:center;background-color: #FFF5CE;color: #C20000;"><b>Member Of Parliament :</b></td>
						<td style="width:30%;text-align:center;background-color: #FFF5CE; color: #C20000;"><b>Party :</b></td>
						<td style="width:20%;text-align:left;background-color: #FFF5CE;"> </td>
					 </tr>
					 <tr>
						<td  style="width:30%;text-align:center;"><a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName} Profile - News,Photos and Videos" href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"><b>${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</b></a></td>
						<td  style="width:30%;text-align:center;"> 
						  <table style="width:100%;text-align:center;">
						    <tr>
	                          <c:if  test="${candidateDetailsForConstituency.parliamentCandidateInfo.party != 'IND'}">
                                <td><a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.party} party Election Results, News, Photos and Videos" href="partyPageAction.action?partyId=${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}"><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/> </a></td><td style="text-align:left;"> <a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.party} party Election Results, News, Photos and Videos" href="partyPageAction.action?partyId=${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}"><b>${candidateDetailsForConstituency.parliamentCandidateInfo.party}</b></a></td>
			                  </c:if>
                              <c:if  test="${candidateDetailsForConstituency.parliamentCandidateInfo.party == 'IND'}">
                                <td><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/> </td><td  style="text-align:left;"><b> ${candidateDetailsForConstituency.parliamentCandidateInfo.party}</b></td>
			                  </c:if>			       				      
						    </tr>
						  </table>
						</td>
						<td style="width:20%;text-align:left;"><div class="pl-sub-but"><a href="javascript:{}" title="Click here to view   ${constituencyDetails.constituencyName}  &nbsp; parliamentary constituency election results" onclick="getConstituencyElecResultsWindow(${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId},'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}','${candidateDetailsForConstituency.parliamentCandidateInfo.latestElecYear}')"><strong>View Results</strong></a></div></td>
				     </tr>
				    </table>
                  </div>
            </c:if>
			<c:if test="${constituencyDetails.constituencyType == 'Parliament'}"><a href="javascript:{}" title="Click here to View  ${constituencyDetails.constituencyName}  &nbsp; Parliamentary constituency participated Candidates Affidavit Summary" style="float:right;" onclick="getcandidateAssetsAndLiabilities(constiId),getAssetsElectionYearsInfo(constiId)"><b>Candidates Affidavit Summary</b></a> 
					<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>

								<div id="electionYears_Main">
									<div id="electionYearsWithAssets_Div"></div>
								</div>
								<div id="constituencyPageCandidateAssetsInfo_Body" class="layoutBodyClass      yui-skin-sam" style="height:auto;max-height:400px;overflow-y:auto">
										<div id="electionYearsWithAssets_Panel_Div"></div>
										<div id="constituencyPageCandidateNominationsInfo_Bottom"></div>
								</div>
								</div>
								
								<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>
				               </div>
				</c:if>
				 <c:if test="${constituencyDetails.constituencyType == 'Parliament'}">
			<div id="constituencyPageCandidateInfo_Main" class="innerLayoutDivClass">
							<div id="constituencyPageCandidateInfo_Body" class="layoutBodyClass yui-skin-sam">
								<div id="constituencyPageCandidateInfo_Top"></div>
                               	<div id="constituencyPageCandidateInfo_Bottom"></div>
				
								</div>
						  
						  </c:if>
							
						
			<c:if test="${constituencyDetails.constituencyType != 'Parliament'}">

			

                <div  id="assemblyDiv">
				  <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
				   <div style="background-color: #FFF9E1;border: 1px solid #F4EED5;width:100%;">
				    <table style="width:100%">
				     <tr>
				        <td rowspan="2" style="width:20%;text-align:center;background-color: #FFF5CE;"><a title="Click here to view ${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName} Profile - News, Photos and Videos" href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateId}"><img  onerror="setImage(this)"  src="images/candidates/${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName}.jpg" height="65" width="60"/></a> </td>                    
						<td style="width:35%;text-align:center;background-color: #FFF5CE;color: #C20000;"><b>Member Of Legislative Assembly :</b></td>
						<td style="width:20%;text-align:center;background-color: #FFF5CE; color: #C20000;;"><b>Party :</b></td>
						<td style="width:20%;text-align:left;background-color: #FFF5CE;"> </td>
					 </tr>
					 <tr>
						<td  style="width:35%;text-align:center;"><a title="Click here to view ${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName} Profile - News,Photos and Videos" href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateId}"><b>${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName}</b></a></td>
						<td  style="width:20%;text-align:center;"> 
						  <table style="width:100%;text-align:center;">
						    <tr>
	                          <c:if  test="${candidateDetailsForConstituency.assemblyCandidateInfo[0].party != 'IND'}">
                                <td><a title="Click here to view ${candidateDetailsForConstituency.assemblyCandidateInfo[0].party} party Election Results, News, Photos and Videos" href="partyPageAction.action?partyId=${candidateDetailsForConstituency.assemblyCandidateInfo[0].partyId}"><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.assemblyCandidateInfo[0].partyFlag}" height="30" width="40"/> </a></td><td style="text-align:left;"> <a title="Click here to view ${candidateDetailsForConstituency.assemblyCandidateInfo[0].party} party Election Results, News, Photos and Videos" href="partyPageAction.action?partyId=${candidateDetailsForConstituency.assemblyCandidateInfo[0].partyId}"><b>${candidateDetailsForConstituency.assemblyCandidateInfo[0].party}</b></a></td>
			                  </c:if>
                              <c:if  test="${candidateDetailsForConstituency.assemblyCandidateInfo[0].party == 'IND'}">
                                <td><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.assemblyCandidateInfo[0].partyFlag}" height="30" width="40"/> </td><td  style="text-align:left;"><b> ${candidateDetailsForConstituency.assemblyCandidateInfo[0].party}</b></td>
			                  </c:if>			       				      
						    </tr>
						  </table>
						</td>
						<td style="width:20%;text-align:left;"><div class="pl-sub-but"><a href="javascript:{}" title="Click here to view ${constituencyDetails.constituencyName} &nbsp; Assembly constituency election results" onclick="getConstituencyElecResultsWindow(${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyId},'${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType}','${candidateDetailsForConstituency.assemblyCandidateInfo[0].latestElecYear}')"><strong>View Results</strong></a></div></td>
				     </tr>
				    </table>
                  </div>
				</c:if>

			




			<div id="candidatesAffidavitSummary" class="cas-view">
			<c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
				<a href="javascript:{}" title="Click here to View  ${constituencyDetails.constituencyName}  &nbsp; Assembly constituency participated Candidates Affidavit Summary" onclick="getcandidateAssetsAndLiabilities(constiId),getAssetsElectionYearsInfo(constiId)"><b>Candidates Affidavit Summary</b></a></li>
			<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>

								<div id="electionYears_Main">
									<div id="electionYearsWithAssets_Div"></div>
								</div>
								<div id="constituencyPageCandidateAssetsInfo_Body" class="layoutBodyClass      yui-skin-sam" style="height:auto;">
										<div id="electionYearsWithAssets_Panel_Div"></div>
										<div id="constituencyPageCandidateNominationsInfo_Bottom"></div>
								</div>
								</div>
								
								<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>

				</c:if>
				</c:if>
				     </div>          
							
              
              <li>
                
			   <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
				<div style="background-color: #FFF9E1;border: 1px solid #F4EED5;width:100%;">
				    <table style="width:100%">
				     <tr>
				        <td rowspan="2" style="width:20%;text-align:center;background-color: #FFF5CE;"><a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName} Profile - News, Photos and Videos" href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"><img  onerror="setImage(this)"  src="images/candidates/${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}.jpg" height="65" width="60"/></a> </td>                    
						<td style="width:30%;text-align:center;background-color: #FFF5CE;color: #C20000;"><b>Member Of Parliament :</b></td>
						<td style="width:30%;text-align:center;background-color: #FFF5CE; color: #C20000;"><b>Party :</b></td>
						<td style="width:20%;text-align:left;background-color: #FFF5CE;"> </td>
					 </tr>
					 <tr>
						<td  style="width:30%;text-align:center;"><a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName} Profile - News,Photos and Videos" href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"><b>${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</b></a></td>
						<td  style="width:30%;text-align:center;"> 
						  <table style="width:100%;text-align:center;">
						    <tr>
	                          <c:if  test="${candidateDetailsForConstituency.parliamentCandidateInfo.party != 'IND'}">
                                <td><a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.party} party Election Results, News, Photos and Videos" href="partyPageAction.action?partyId=${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}"><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/> </a></td><td style="text-align:left;"> <a title="Click here to view ${candidateDetailsForConstituency.parliamentCandidateInfo.party} party Election Results, News, Photos and Videos" href="partyPageAction.action?partyId=${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}"><b>${candidateDetailsForConstituency.parliamentCandidateInfo.party}</b></a></td>
			                  </c:if>
                              <c:if  test="${candidateDetailsForConstituency.parliamentCandidateInfo.party == 'IND'}">
                                <td><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/> </td><td  style="text-align:left;"><b> ${candidateDetailsForConstituency.parliamentCandidateInfo.party}</b></td>
			                  </c:if>			       				      
						    </tr>
						  </table>
						</td>
						<td style="width:20%;text-align:left;"><div class="pl-sub-but"><a href="javascript:{}" title="Click here to view  ${parliament.name}  parliamentry constituency election results" onclick="getConstituencyElecResultsWindow(${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId},'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}','${candidateDetailsForConstituency.parliamentCandidateInfo.latestElecYear}')"><strong>View Results</strong></a></div></td>
				     </tr>
				    </table>
                  </div>
            </c:if>
		    </li>
                <div class="cas-view"/>
			 </ul>
			</c:if>
			</div>
          
          <!--ELECTED CANDIDATE INFO SECTION END--> 
          
          <!--ELECTED INFORMATION SECTION START-->
          
          <div class="cl-sub-fields-sec">
            <h1 class="toptwo"></h1><h1 class="blu-title"><span>Election Information in ${constituencyDetails.constituencyName} &nbsp;${constituencyDetails.constituencyType} Constituency</span></h1>
            <div class="detailed-ele-inf"> 
            </div>
             
            
			
            <div class="clear"></div>
			<div id="detailedChartDIV" class="yui-skin-sam"></div>
				<div id="constituencyPageElectionEnlargedImgDiv" class="layoutHeadersClass"></div>
					<div id="constituencyPageElectionImgDiv" ></div>
						<div id="constituencyPageElectionInfoDiv_Body" class="layoutBodyClass"></div>
							
		
            
            <p class="detailed-ele-inf" style="display:block;margin-top:15px"> <span class="fleft" style="font-weight:bold;color:#C20000;font-size: 14px;">${constituencyDetails.constituencyName}  &nbsp;${constituencyDetails.constituencyType}  Constituency Detailed Election Information</span>
			<span id="minusPlusDiv"><a title="Click here to View ${constituencyDetails.constituencyName}   &nbsp; ${constituencyDetails.constituencyType} Constituency Detailed Election Information" onclick="detailedElectionResult()"> <img src="images/plus.png" alt="" class="fleft" style="padding:3px 0px 0px 5px;cursor: pointer;"/><b> Click Here To View</b></a></span></p>
			<div id="detailedElectionInfoDiv_Body" style="display: block;"></div>
          </div>
          
		 
          <!--ELECTED INFORMATION SECTION END--> 
          
          <!--MANDALS VOTERS SECTION START-->
          
          <div class="cl-sub-fields-sec" id="hidemandDIV">
		    <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
            <h1 class="topthree"></h1><h1 class="pur-title"><span>Mandals Wise Voters Details Of ${constituencyDetails.constituencyName}  &nbsp; Assembly Constituency</span></h1>
			</c:if>
			<c:if test="${constituencyDetails.constituencyType != 'Assembly'}">
			 <h1 class="topthree"></h1><h1 class="pur-title"><span>Assembly Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Parliament Constituency</span></h1>
			</c:if>
            <ul class="mvd-fields" id="MandalsDelimit">
			 <div id="allDIV">
			  <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
              <li><a href="javascript:{}" title="Click here to view Mandals Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Assembly After Delimitation" onclick="showMandalsAfterDelimitationDiv(constiId)" class="active" style="cursor:pointer"><strong>Mandals After Delimitation</strong></a></li>
              <li><a href="javascript:{}" title="Click here to view Mandals Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Assembly Before Delimitation" onclick="showMandalsBeforeDelimitationDiv(constiId)" style="cursor:pointer"><strong>Mandals Before Delimitation</strong></a></li>
			  </c:if>
			  <c:if test="${constituencyDetails.constituencyType != 'Assembly'}">
			   <li><a href="javascript:{}" title="Click here to view Assemblies Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Parliament After Delimitation" onclick="showMandalsAfterDelimitationDiv(constiId)" class="active" style="cursor:pointer"><strong>Assemblies After Delimitation</strong></a></li>
              <li><a href="javascript:{}" title="Click here to view Assemblies Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Parliament Before Delimitation" onclick="showMandalsBeforeDelimitationDiv(constiId)" style="cursor:pointer"><strong>Assemblies Before Delimitation</strong></a></li>			  
			  </c:if>
			 </div>
			 <div id="oneDIV" style="display:none;">
			 <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
			   <li><a href="javascript:{}"  title="Click here to view Mandals Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Assembly After Delimitation"  class="active" style="cursor:pointer"><strong>Mandals After Delimitation</strong></a></li>
			 </c:if>
			 <c:if test="${constituencyDetails.constituencyType != 'Assembly'}">
			   <li><a href="javascript:{}"  title="Click here to view Assemblies Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Parliament After Delimitation"  class="active" style="cursor:pointer"><strong>Assemblies After Delimitation</strong></a></li>
			 </c:if>
			</div>
			 <div id="twoDIV" style="display:none;">
			 <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
			   <li><a href="javascript:{}"  title="Click here to view Mandals Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Assembly Before Delimitation"  class="active" style="cursor:pointer"><strong>Mandals Before Delimitation</strong></a></li>
			 </c:if>
			 <c:if test="${constituencyDetails.constituencyType != 'Assembly'}">
			   <li><a href="javascript:{}"  title="Click here to view Assemblies Wise Voters Details Of ${constituencyDetails.constituencyName} &nbsp; Parliament Before Delimitation"  class="active" style="cursor:pointer"><strong>Assemblies Before Delimitation</strong></a></li>
			 </c:if>
			 </div>
			  
            </ul>
			
            <div class="clear"></div>
            <div class="mandals-voters-sec" style="float:none;margin-left:auto;margin-right:auto;padding:0 0 15px 0px;">
			
						<div id="constituencyCenterContentOuter1" class="rounded"> 						
						

						<div id="divInteractive_Chart_0"></div>
						<div id="divInteractive_Chart_1"></div>

						<div id="mandalsVotersInfoDiv_Main" class="innerLayoutDivClass">
						<div id="mandalsVotersInfoDiv_Head" class="layoutHeadersClass"></div>
							
						<div id="mandalsVotersInfoDiv_Body" class="mv-full-details yui-dt"></div>
							
						</div>
				
						</div>
						
		
				</div>
			</div>
          
       
          
          <!--MANDALS VOTERS SECTION END--> 
          
          <!--ALL LOCAL ELECTIONS CONSITITUENCY SECTION START-->


		  <div class="cl-sub-fields-sec ale-sec" id="hideAllRuralLocalElections">
            <h1 class="toptwo"></h1><h1 class="blu-title" ><span>All Rural Local Body Elections Happened In ${constituencyDetails.constituencyName} &nbsp; Constituency</span></h1>
		   <div id="showmptcZptc">
			  <ul class="mvd-fields" id="LocalElectionsNavNew">             

                <li id="ZptcDiv"> <a href="javascript:{}" title="Click here to view ZPTC Elections Happened In ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency" onclick="showZptcPartyDetails()" style="cursor:pointer"><strong>ZPTC's</strong></a></li>			  
			  
                <li id="mptcDiv"><a href="javascript:{}" title="Click here to view MPTC Elections Happened In ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency" onclick="showMptcPartyDetails()" style="cursor:pointer"><strong>MPTC's</strong></a></li>           
             </ul>

            <div id="zptcDivBody" class="detailed-ele-inf">
			
			<div id="headDiv" style="color: #5CB275;text-decoration: none;display:block;clear:left;padding:10px;font-weight:bold;">Detailed View of ${constituencyDetails.constituencyName} Zptc Election Results </div>
			
				
				<div id="zptcElectionIdsSelectDiv"></div>
					<div id="zptcCandidateLink" ></div>
					<div id="countZptcDiv">
			<span>Total Number of ZPTC's :&nbsp;<span id="totalZptcCountResultDiv" style="display:inblock";></span> </span></div>
						<table><tr>
							<td valign='top'><div id="zptcPartyTrendsDetailsDiv"></div></td>
							<td valign='top'><div id="zptcChartDiv"></div></td>
						</tr></table>
						
			 </div>
			<div id="mptcDivBody" class="detailed-ele-inf">
				<div id="mptcheadDiv" style="color: #5CB275;text-decoration: none;display:block;clear:both;padding:10px;font-weight:bold;">Detailed View of ${constituencyDetails.constituencyName}  &nbsp; Mptc Election Results</div>
			<div id="mptcElectionIdsSelectDiv"></div>
				<div id="mptcCandidateLink"></div>
				<div id="countMptcDiv">
				<span style="float:left";>Total Number of MPTC's :&nbsp;</span>
					<span id="totalMptcCountResultDiv" style="float:left";></span>
			  
						</div>	<table><tr><td>						
							<div id="mptcPartyTrendsDetailsDiv"></div></td><td>
							<div id="MptcChartDiv"></div></td></table>
				</div>
		
          
			 </div>
		    </div>
        
        
         
          <!--ALL LOCAL ELECTIONS CONSITITUENCY SECTION END-->
          
          <div class="clear"></div>
		  	

        </div>
	</div>
	   

     

      <!--CIBSTUTYEBCT DETAILS LEFT SECTION END--> 

		
	        <!--CIBSTUTYEBCT DETAILS RIGHT SECTION START-->
      
      <div class="cd-right-sec"> 
        
		  <div class="cp-sub-field-sec">

		
		<c:if test="${constituencyDetails.hasAnalize}">
			<input title="Click Here To Get ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency Voting Trendz" type="button" class="button" style="background-color:#3897A5;cursor:pointer;font-weight:bold;" value="${constituencyDetails.constituencyName} &nbsp; Detailed Analysis" onclick="openConstVotingTrendzWindow('${constituencyDetails.districtId}','${constituencyDetails.constituencyId}','${constituencyDetails.constituencyName}')" />
		</c:if>
	
	</div>
						
        <!--VIEW YOUR PROBLEMS SECTION START-->
        
        <div class="vy-problems-sec">
          <h1 class="vyp-title"><img src="images/view-your-problems.png" alt=""/></h1>
        <div class="vy-fields-sec">
        <div class="post-problem">
              <div>Post your constituency problem and<br />
                bring it to the all people notice.</div>
				<div id="problemPostingDiv_Head"></div>
				<div id="problemPostingDiv_Body"></div>
				<div id="problemViewingDiv">
								
							</div>
			
              
            </div>
			<div id="problemViewingDiv_Head"></div>
			<div id="problemViewingDiv_Body"></div>
			
            
        </div>
        
        <!--VIEW YOUR PROBLEMS SECTION END--> 
        
        <!--CD SUB RIGHT SECTION START-->
        
        <div class="cd-sub-right-sec">
          <div class="cd-sub-right-cont"> 
            
            <!--CONNECT PEOPLE SECTION START-->
            
            <div class="connect-people-sec">
              <div class="cp-title-sec">
               <h1 class="cp-title">Connect To Your Constituency People</h1>
           </div>
              <div class="cp-cont-sec">
			  <div id="constituencyPeopleConnectDiv">
								<!--<div id="constituencyPeopleConnectDiv_Head"></div>-->
								<div id="constituencyPeopleConnectDiv_Body"></div>
							</div>

			  </div>
                
                  
				  
				  
               
              <!-- <div class="view-all">-->

			   <div id="connectPeoplePopup_outer" class="yui-skin-sam">
			<div id="connectPeoplePopup"><div id="allConnectedUsersDisplay_main"></div></div>
			</div>
				 </div>
              </div>
              <div class="cp-bbg"></div>
            </div>
            <div id="connectPeoplePopup"></div>
			
            <!--CONNECT PEOPLE SECTION END--> 
            
            <!--ANNOUNCEMENTS SECTION START-->
            
            <div class="cp-sub-field-sec">
			
					
			  <h1 class="cp-sub-title"><span style="color:#787272"><b>Announcements From Your Leaders</b></span></h1>

			 
              <div class="cp-cont-sub-fields">
               <ul>
                  <li>
                    <div id="constituencyAnnouncementsDiv" class="rounded"> 						
						
							<div id="announcements_div_main" class="innerLayoutDivClass">
								<div id="announcementsInConstituencyDiv" style="height:auto;max-height:280px;overflow-y:auto;width:310px;">
								</div>
								<div id="announcementsOfAUserDiv" >
								</div>
							</div> </li></ul>
                 
              </div>
            </div>
            
            <!--ANNOUNCEMENTS SECTION END--> 
            
            <!--ASSESS YOUR PARTY RESULTS SECTION START-->
            
            <div class="cp-sub-field-sec">
              <h1 class="cp-sub-title"><span style="color:#787272"><b>Assess Your Party Results</b></span></h1>
              <div class="cp-cont-sub-fields">
                <ul>
                  <li>
                    <p>&nbsp;&nbsp;&nbsp;Assess your constituency election results and post your reasons for winning/loosing .</p>
                    <p class="ass-pre">
					<div id="analyzeConstituencyPageDiv_main">
								<div id="analyzeConstituencyPageDiv_Head"></div>
								<div id="analyzeConstituencyPageDiv_Body"></div>
							</div>
					</p>
                  </li>
                </ul>
              </div>
            </div>
            
            <!--ASSESS YOUR PARTY RESULTS SECTION END--> 
            
            <!--LOCATION MAP SECTION START-->
            
            <div class="cp-sub-field-sec">
              <h1 class="cp-sub-title"><span style="color:#787272"><b>Location Map</b></span></h1>
              <div id="map_canvas" style="position: relative; background-color: rgb(229, 227, 223); height: 283px; margin-top: 41px; width: 311px;"></div>
            </div>
            
            <!--LOCATION MAP SECTION END--> 
            
            <!--NEWS AND UPDATES SECTION START-->
			<c:if test="${userDetails.loginStatus}">
            <div class="cp-sub-field-sec">
			  <c:if test="${constituencyDetails.votingTrendz}">
			 	<div  class="cp-sub-field-sec" id="votingTrendzDiv_Body">
					 </div>
				</c:if>						
			 
                
             
            
            <!--NEWS AND UPDATES SECTION END--> 
            
          </div>
</c:if>
        
        </div>
<!-- Opinion Poll Start-->
			
						<div id="pollsWidgetMain" style="margin:10px 0px 10px 0px;clear:both;">
									<div id="pollsWidgetHeader">
										<!--<table width="100%" border="0" cellspacing="0" cellpadding="0">
										  <tr>
											<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
											<td width="98%">
												<div class="electionTrendzHeaderBackground_center">
													<span class="headerLabelSpan headerLabelSpan1" style="color:#C66E17;top:13px;">
														Opinion Poll
													</span>
												</div>
											</td>
											<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
										  </tr>
										</table>	-->
									 </div>
									<!--<div id="pollsWidgetBody" style="height:auto;width:302px;background:#ffffff;overflow:hidden;">-->

									<!--<div id="pollsWidgetBody" class="span4 well" style="background:#FFFFFF;width:290px;margin-left:1px;">-->
                                     <div style="height:15px;padding-bottom:5px;">
                                     <div id="alreadyVotedDivId"></div>
									 </div>
									<div id="pollsWidgetBody">
								
									</div>
									<div id="pollsWidgetFooter">
										
									</div>
								</div>
							<!-- Opinion Poll End-->


		<!-- <h1 class="cp-sub-title"><span style="color:#787272"><b>Party Analyst</b></span></h1> -->
        <!-- <div id="partyAnysAdd" style="margin-left:11px;"></div> -->
						
						<!-- <div class="servicesClass">
		                    <h3 class="headingClass">Our Services</h3>
		                    <a href="newsMonitoringService.action" title="News tracking service">News<br/>Monitoring Service</a>
		                    <a href="VotersPulse.action" title="Election-Political Surveys">Voters<br/> Pulse</a>
		                    <a href="constituencyProfileReport.action" title="Reports">Constituency Management Reports</a>
		                    <a href="electionAnalysisAndManagementTool.action" title="Our unique tool designed for Politicians">Political<br/>Management Tool</a>
		                </div> -->

						<div class="our-services">
		                    <h3 class="headingClass">Our Services</h3>
		                    <a href="newsMonitoringService.action" title="News tracking service">News<br/>Monitoring Service</a>
		                    <a href="VotersPulse.action" title="Election-Political Surveys">Voters<br/> Pulse</a>
		                    <a href="constituencyProfileReport.action" title="Reports">Constituency Management Reports</a>
		                    <a href="electionAnalysisAndManagementTool.action" title="Our unique tool designed for Politicians">Election Analysis And<br/>Management Tool</a>
		                </div>
<!--EMail Alert Section Start-->						
		 <div class="our-services">
            <h2 class="ea-fc-title">email alert <span class="blue-down-arrow"><img src="images/icons/candidatePage/blue-down-arrow.png" alt=""/></span> </h2>
            <div class="" style="font-size:15px;text-align:center;">             			  
				<span id="subscribeSpan">
					<s:if test="isSubscribed == true ">
					Unsubscribe to stop<br/>
					updates of<br />
					<span class="li-red">
					${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency</span><br/>
					<input class="unsubscribebtn" type="button" onclick=
					"unsubscriptionDetails()" value="UNSUBSCRIBE"/>
					</s:if>
					
					<s:else>
					Subscribe and get <br/>
					updates of<br />
					<span class="li-red">${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Constituency</span><br/>
					<input  class="subscribebtn" type="button" onclick=
					"subscriptionDetails()" value="SUBSCRIBE"/>
					</s:else>
				</span>
            </div>
          </div>
<!--EMail Alert Section End-->		
						  
<!--
<div style="margin-left:11px;">
	<script type="text/javascript"><!--
		google_ad_client = "ca-pub-0938408694174139";
		/* CandidatePageRightBox */
		google_ad_slot = "5426332176";
		google_ad_width = 300;
		google_ad_height = 250;
		//-->
		<!--</script>
		<script type="text/javascript"
		src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>
	</div>  -->
        <!--CD SUB RIGHT SECTION END--> 
        
      </div>
      
	   <div class="cl-sub-fields-sec ale-sec" id="hideAllUrbanLocalElections" style="margin-top:20px;margin-left:5px;width:968px;">
            <h1 class="topfour"></h1><h1 class="gre-title"><span>All Urban Local Body Elections Happened In ${constituencyDetails.constituencyName} &nbsp; Constituency</span></h1>
			 <div id="showmungracorp">
			<ul class="mvd-fields" id="LocalElectionsNav">             
               <li id="muncipalityDiv"><a href="javascript:{}" title="Click here to view MUNCIPALITY Elections Happened In ${constituencyDetails.constituencyName}    &nbsp; ${constituencyDetails.constituencyType} Constituency" onclick="showMunicipalityResults1(),getMunicipalityResultsForAssemblies()" class="active"><strong>MUNCIPALITY</strong></a></li>			  
			   <li id="greterMunicipalDiv"><a href="javascript:{}" title="Click here to view GREATER MUNICIPALCORP Elections Happened In ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency" onclick="showGreterElectionDetails()" style="cursor:pointer"><strong>GREATER MUNICIPALCORP</strong></a></li>
			    <li id="corporationTabDiv"><a href="javascript:{}" title="Click here to view CORPORATION Elections Happened In ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency" onclick="showCorporationDetails()"  style="cursor:pointer"><strong>CORPORATION's</strong></a></li>
            </ul>

			<!-- <div id="muncipalDivBody" class="detailed-ele-inf">
			<div id="MuncipalElectionSelectDiv" style="float:left;width:auto;margin-top:-4px;">Select Election Year:
				<s:select theme="simple" id="municipalitySelect" name="municipalities" list="municipalElections" listKey="id" listValue="name"></s:select>&nbsp&nbsp
						
			</div>
			
			 <div id = "muncipalDiv"></div>
			 <div id="showMoreMuncipalResultsDiv" style="clear:right;margin-left:3px;text-decoration: none;"></div>
					<div id="municipalityData_main"></div>
					
			</div>-->
			<div id="muncipalDivBody">
			<div id="MuncipalElectionSelectDiv" style="float:left;width:100%;margin-top:-5px;padding-left:48px;">Select Election Year:
				<s:select theme="simple" id="municipalitySelect" name="municipalities" list="municipalElections" listKey="id" listValue="name"></s:select>&nbsp&nbsp
						
			</div>
			<div id="muncipalitiesDiv1" style="background:#ffffff;float:right;width:100%;"> </div>		
			</div>
			
			</div>
			<!--corporationDiv-->
			<div id = "corporationDiv">
			<div id="corporationSelectDiv" style="float:left;width:100%;margin-top:-5px;padding-left:48px;">
			<b>Select Corporation Election&nbsp;:&nbsp;</b><s:select theme="simple" id="corporationSelect" label="Select Cororation Election" name="corporations" list="corporateElections" listKey="id" listValue="name" onchange="getCoroporationResults()"></s:select>
			</div>
			
			<div style="width:100%;">	
			<div id="corporationsDiv" class="span6" id="corporationsDiv" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);"> </div>
			<div id="CorporationPieChartDiv"  style="position: relative; width: 350px; height: 300px; margin-top:140px;right: 0px; bottom: 0px; margin-bottom: 0px;  display: block;" class="span4"></div>
		</div>
			</div>
			<!--corporationDiv-->
					<!--<div id = "corporationDiv">
					
									<div id="corporationHeadingDIV" style="display:block;clear:both;">
									
									<table>
											<tr>
												<td>
													<b>Select Corporation Election&nbsp;:&nbsp;</b><s:select theme="simple" id="corporationSelect" label="Select Cororation Election" name="corporations" list="corporateElections" listKey="id" listValue="name" onchange="getCoroporationResults()"></s:select>
												</td>
												<td>
													<div id="showMoreCorporationResultsDiv"></div>										
												</td>
											</tr>
										</table>	
									</div>
									<div id="coroporationData_main">
									
									</div>
								</div>-->
									
								<div id = "greaterDiv" style="display:block;clear:both;">
									<div>
									<table>
											<tr>
												<td>
													<b>Select Greater Election&nbsp;:&nbsp;</b><s:select theme="simple" id="greaterSelect" label="Select Greater Election" name="greaters" list="greaterElections" listKey="id" listValue="name" onchange="getGreaterResults()"></s:select>
													</td>
												<td>
													<div id="showMoreGreaterResultsDiv"></div>										
												</td>
											</tr>
										</table>
									</div>
									<div id="wardsElectionResults_body" class="productFeatureBody yui-skin-sam">
											<div id="wardsElectionResults_body_radioSelectDiv" style="padding:5px;font-weight:bold;">
												Select Results Criteria :
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)"  value="all" checked="checked"/>All
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)" value="partyWise"/>Party Wise Results	
												<s:select theme="simple" cssClass="selectBoxWidth" cssStyle="visibility:hidden;width:100px;" name="wardWise_parties" id="wardWise_parties" list="greaterInfo.listOfParties" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getWardWiseElectionResults('partyWise',this.options[this.selectedIndex].value)"/>
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)" value="wardWise"/>Ward Wise Results	
												<s:select theme="simple" cssClass="selectBoxWidth" cssStyle="visibility:hidden;width:100px;" name="wardWise_wards" id="wardWise_ward" list="greaterInfo.listOfWards" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getWardWiseElectionResults('wardWise',this.options[this.selectedIndex].value)"/>
											</div>											
										</div>
									<div id="GHMCData_main"></div>
								</div><!--
							</td>
						</tr>
					</table>
				</div>-->
				
	
            </div>
		   </div>
		   
	  
	  
		<c:if test="${userDetails.loginStatus}">
		
		
		<c:if test="${constituencyDetails.viewCompletePage}">
			<div id="MandalwiseVotingTrendz" class="rounded" >
			  <div id="mandalwisevotingTrendz"class="trenzCss">
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
				<div id="MandalVotingTrendz_head"></div>
				<div id="electionIdsSelectDiv" style="padding-left:13px;"></div>
				<div id="parliamentElectionResultsDivNew" style="display:none;overflow:auto;margin-top:10px;"></div>
				<div id="censusSelectDiv"></div>
				<div id="censusErrorMsgDiv" style="padding-left:10px;"></div>
				<div id="mandalOrConstiElecResultDiv">
				<div id="parliamentElectionResultsDiv" style="overflow:auto;margin-top:10px;"></div>
				<div id="electionResultsInConstituencyDiv" style="margin-top: -1px;"></div>
				<div id="labelRadioDiv"></div>			
				<div id="resultsDataTableDiv"></div>
				<div id="missingDataInfoDiv"></div>
				
				</div>
						
			</div>
			</div>
		</c:if>
		
		
			</c:if>	
      <!--CIBSTUTYEBCT DETAILS RIGHT SECTION END--> 
	<script type="text/javascript">
	if('${candidateDetailsForConstituency.ispartial}' == 'true'){
	        getcandidateAssetsAndLiabilities(constiId);
			getAssetsElectionYearsInfo(constiId);
	}
	buildPolls();
function getConstiElecYearsForAss(){
   var jsObj = {
			constituencyId:'${parliamentCinstiId}',
			task:"getConstituencyElectionsYersForAss"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
}
function buildConstituencyElectionsYersForAss(optionsList)
{	
    document.getElementById("constituencyParlYearsDiv").style.display="block";
	
	var elmt = document.getElementById("constituencyParlYears");
	
	if( !elmt || optionsList == null)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=1;i--)
	{
		elmt.remove(i);
	}	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].name;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
}
function showUrbanDivIfPresent()
{

   if(changeReq == 0)
 {
 
 if(muncipalityPresent && corporationPresent && greaterPresent)
 {
  document.getElementById("hideAllUrbanLocalElections").style.display ="none";  
 }
 if(muncipalityPresent == false)

 {
   $("#LocalElectionsNav a").removeClass("active");
   $("#muncipalityDiv a").addClass("active");
    showMunicipalityResults1();
 }
 
 else if(corporationPresent == false)
 {
   $("#LocalElectionsNav a").removeClass("active");
   $("#corporationTabDiv a").addClass("active");
    showCorporationDetails();
 }
 else if(greaterPresent == false)
 {
	 
   $("#LocalElectionsNav a").removeClass("active");
   $("#greterMunicipalDiv a").addClass("active");
    showGreterElectionDetails();	
 }
 
}
}

<!--constituency subscription functions*-->
function subscriptionDetails()
 {
	if(userName==''){
       showNotLogIn();
    return false;}
	
	else{	
	var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			id: constituencyPageMainObj.constituencyInfo.constituencyId,
			task: "constituencySubscriptionDetails",
			
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "constituencyEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
   }
 }
 
 function unsubscriptionDetails()
 {
	var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			id: constituencyPageMainObj.constituencyInfo.constituencyId,
			task: "constituencyUnsubscriptionDetails",
			
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "constituencyEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
 }
function unSubscribeBtnBuild()
{
$('#subscribeSpan').html('');
var str='';
str+='Unsubscribe to stop<br/>updates of<br />';
str+='<span class="li-red">${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Constituency</span><br/>';
str+='<input  class="unsubscribebtn" type="button" onclick="unsubscriptionDetails()" value="UNSUBSCRIBE"/>';
$('#subscribeSpan').html(str);
subscribeAlert();
}

function subscribeBtnBuild()
{
$('#subscribeSpan').html('');
var str='';
str+='subscribe to get<br/>updates of<br />';
str+='<span class="li-red">${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Constituency</span><br/>';
str+='<input  class="subscribebtn" type="button" onclick="subscriptionDetails()" value="SUBSCRIBE"/>';
$('#subscribeSpan').html(str);
unSubscribeAlert();
}

function showNotLogIn()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">Please login to subscribe </div></div>';
		document.getElementById("logInDiv").innerHTML = str;
}

function subscribeAlert()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">You had subscribed successfully </div></div>';		document.getElementById("logInDiv").innerHTML = str;
}
function unSubscribeAlert()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">You had Unsubscribed successfully </div></div>';		document.getElementById("logInDiv").innerHTML = str;
}
/*constituency subscriptions functions end*/


function showRuralDivIfPresent()
{ 
 if(changeReq == 0)
 {
 if(mptcPresent && zptcPresent)
 {
  document.getElementById("hideAllRuralLocalElections").style.display ="none";  
 }
 if(zptcPresent == false)
 {
   $("#LocalElectionsNavNew a").removeClass("active1");
   $("#ZptcDiv a").addClass("active1");
    showZptcPartyDetails();	
 }
 else if(mptcPresent == false)
 {
   $("#LocalElectionsNavNew a").removeClass("active1");
   $("#mptcDiv a").addClass("active1");
    showMptcPartyDetails();
 } 
}
}	
var defDate = constituencyPageMainObj.constituencyInfo.deformDate;
	userLoginStatus = '${userDetails.loginStatus}';
	userId = '${userDetails.userId}';
	var userId = "${sessionScope.USER.registrationID}";
	var userType = "${sessionScope.USER.userStatus}";
	var loginStat = "${sessionScope.loginStatus}";
	var constituencyResults,createGroupDialog;
	
	/*	Constituency Page basic Info
		-----------------------------
	*/
	constituencyPageMainObj.constituencyAddress="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";
	constituencyPageMainObj.contextPath = "<%=request.getContextPath()%>";	
	
	constituencyPageMainObj.forwardTask = "${redirectLoc}";
	constituencyPageMainObj.constituencyInfo.constituencyId = "${constituencyId}";
	constituencyPageMainObj.constituencyInfo.constituencyName = "${constituencyDetails.constituencyName}";
	constituencyPageMainObj.constituencyInfo.districtName = "${constituencyDetails.districtName}";
	constituencyPageMainObj.constituencyInfo.stateName = "${constituencyDetails.stateName}";
	constituencyPageMainObj.constituencyInfo.startDate = "${constituencyDetails.startDate}";
	constituencyPageMainObj.constituencyInfo.deformDate = "${constituencyDetails.deformDate}";
	constituencyPageMainObj.constituencyInfo.constituencyType = "${constituencyDetails.constituencyType}";
	constituencyPageMainObj.constituencyInfo.reservation_zone = "${constituencyDetails.reservation_zone}";	
	
	<c:forEach var="parliament" items="${navigationVO.pcsInfo}">
		parliamentConstiId = "${parliament.id}";
		parliamentConstiName = "${parliament.name}";		
	</c:forEach>


/*	Assembly Candidate Info
		-------------------------
	*/
	<c:if test="${candidateDetailsForConstituency.assemblyCandidateInfo != null}">
	<c:forEach var="cInfo" items="${candidateDetailsForConstituency.assemblyCandidateInfo}">	
	var candidateObj={
						constituencyId : '${cInfo.constituencyId}',
						constituencyName : '<a href="constituencyPageAction.action?constituencyId=${cInfo.constituencyId}"> ${cInfo.constituencyName}</a>',
						constituencyType:'${cInfo.constituencyType}',
						deformDate:'${cInfo.deformDate}',
						candidateId : '${cInfo.candidateId}',
						//candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${cInfo.candidateId}"> ${cInfo.candidateName}</a>',	
						candidateName:'<a id="${cInfo.candidateName}" onmouseover="displayImage(this.id)" onmouseout="return nd();" href="candidateElectionResultsAction.action?candidateId=${cInfo.candidateId}"> ${cInfo.candidateName}</a>',	
						partyId:' ${cInfo.partyId}',
						party : '${cInfo.party}',
						partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${cInfo.partyFlag}" height="30" width="40"/>',
						knowMore:'<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\'${cInfo.constituencyId}\',\'${cInfo.constituencyType}\',\'${cInfo.latestElecYear}\')">view results</a>'
					 };		
	
	constituencyPageMainObj.presentAssemblyCandidate.push(candidateObj);
	</c:forEach>
	</c:if>
	


	<c:if test="${candidateDetailsForConstituency.assemblyCandidateInfo != null}">
	<c:forEach var="cInfo" items="${candidateDetailsForConstituency.assemblyCandidateInfo}">	
	var candidateObj1={
						constituencyId : '${cInfo.constituencyId}',
						constituencyName :'${cInfo.constituencyName}</a>',
						
					 };		
	
	constituencyPageMainObj.munAssemblyConstituencyIds.push(candidateObj1);
	</c:forEach>
	</c:if>
	
	/*	Parliament Candidate Info
		-------------------------
	*/
	<c:if test="${candidateDetailsForConstituency.parliamentCandidateInfo != null}">
	var pmtObj = {
					constituencyId : '${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}',
					constituencyName :  '<a href="constituencyPageAction.action?constituencyId=${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}">${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}</a>',									
					constituencyType:'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}',
					deformDate:'${candidateDetailsForConstituency.parliamentCandidateInfo.deformDate}',
					candidateId : '${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}',
					candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"> ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</a>',		
					partyId:' ${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}',
					party : '${candidateDetailsForConstituency.parliamentCandidateInfo.party}',
					partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/>',
					knowMore:'<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}\',\'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}\',\'${candidateDetailsForConstituency.parliamentCandidateInfo.latestElecYear}\')">view results</a>'
				 };		
	constituencyPageMainObj.presentParliamentCandidate.push(pmtObj);
	</c:if>






	/*	Constituency Election Results Info
		----------------------------------
	*/

	<c:forEach var="constituencyElectionResults" items="${constituencyElectionResultsVO}">	
	var constiObj=
				{
					candidateName:'${constituencyElectionResults.candidateResultsVO.candidateName}',
					partyName:'${constituencyElectionResults.candidateResultsVO.partyName}',
					year:'${constituencyElectionResults.electionYear}',
					votesEarned:'${constituencyElectionResults.candidateResultsVO.votesEarned}',
					votesPercentage:'${constituencyElectionResults.candidateResultsVO.votesPercentage}',
					votesMargin:'${constituencyElectionResults.candidateResultsVO.votesMargin}',
partyShortName:'${constituencyElectionResults.candidateResultsVO.partyShortName}',
					oppositionCandInfo:[]
				 };
			<c:forEach var="detailedResult" items="${constituencyElectionResults.candidateOppositionList}" >
				var oppositionList={
										candidateName:'${detailedResult.candidateName}',
										partyName:'${detailedResult.partyName}',
										year:'${constituencyElectionResults.electionYear}',
										votesEarned:'${detailedResult.votesEarned}',
										votesPercentage:'${detailedResult.votesPercentage}'	,
								partyShortName:'${detailedResult.partyShortName}'	
									};
						
					constiObj.oppositionCandInfo.push(oppositionList);
			</c:forEach>			
			constituencyPageMainObj.constituencyElectionInfo.push(constiObj);			
	</c:forEach>

	/*	Constituency problems Info
		-------------------------
	*/
	
	<c:forEach var="problem" items="${problemBean}">	

	var probDesc = "${problem.description}";
    probDesc = probDesc.replace("&#39;","&#92;&#39;");

	var problemObj={
						problemId:'${problem.problemId}',
						problem:'${problem.problem}',
						//description:'${problem.description}',
						description:probDesc,
						state:'${problem.state}',
						district:'${problem.district}',
						constituency:'${problem.constituency}',
						tehsil:'${problem.tehsil}',
						village:'${problem.village}',
						hamlet:'${problem.hamlet}',
						reportedDate:'${problem.postedDate}',
						existingFrom:'${problem.existingFrom}',
						name:'${problem.name}',
						postedPersonName:'${problem.postedPersonName}',
						email:'${problem.email}',						
						phone:'${problem.phone}',
						mobile:'${problem.mobile}',
						address:'${problem.address}',
						problemLocationId:'${problem.problemLocationId}',
						problemHistoryId:'${problem.problemHistoryId}',
						acceptedCount: '${problem.acceptedCount}',
						rejectedCount: '${problem.rejectedCount}'
					};
		
	constituencyPageMainObj.problemsInfo.push(problemObj);
	</c:forEach>

	/*
		Voting Trendz Info 
		------------------
	*/	

		var vTObj = constituencyPageMainObj.electionTrendzReportVO;

		vTObj.state = '${electionTrendzReportVO.state}';
		vTObj.electionType = '${electionTrendzReportVO.electionType}';
		vTObj.electionYear = '${electionTrendzReportVO.electionYear}';
		vTObj.constituencyId = '${electionTrendzReportVO.constituencyId}';
		vTObj.constituencyName = '${electionTrendzReportVO.constituencyName}';
				

		vTObj.electionTrendzOverviewVO.totalVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.totalVoters}';
		vTObj.electionTrendzOverviewVO.maleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVoters}';
		vTObj.electionTrendzOverviewVO.femaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVoters}';
		vTObj.electionTrendzOverviewVO.maleAndFemaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemaleVoters}';	
		
		vTObj.electionTrendzOverviewVO.totalPolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.totalPolledVotes}';
		vTObj.electionTrendzOverviewVO.malePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.malePolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePolledVotes}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePolledVotes}';

		vTObj.electionTrendzOverviewVO.pollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.pollingPercent}';
		vTObj.electionTrendzOverviewVO.malePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercent}';
		vTObj.electionTrendzOverviewVO.femalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercent}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePollingPercent}';
		
		vTObj.electionTrendzOverviewVO.maleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercent}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercent}';
		vTObj.electionTrendzOverviewVO.maleOrFemaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemaleVotersPercent}';

		vTObj.electionTrendzOverviewVO.overallMalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallFemalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallMaleOrFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMaleOrFemalePollPercent}';
				
		vTObj.electionTrendzOverviewVO.maleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersInConstituency}';
		vTObj.electionTrendzOverviewVO.femaleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersInConstituency}';

		vTObj.electionTrendzOverviewVO.maleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercentInConsti}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercentInConsti}';

		vTObj.electionTrendzOverviewVO.malePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercentInTotalPolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercentInTotalPolledVotes}';
		vTObj.electionTrendzOverviewVO.maleOrFemalePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemalePollingPercentInTotalPolledVotes}';
	
		
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz}';


		<c:forEach var="candidate" items="${electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO}">	
			var cObj = {
							partyId:'${candidate.partyId}',
							partyName:'${candidate.partyName}',
							partyLogo:'${candidate.partyLogo}',
							partyFlag:'${candidate.partyFlag}',
							candidateId:'${candidate.candidateId}',
							candidateName:'${candidate.candidateName}',							
							validVotes:'${candidate.validVotes}',
							totalVotes:'${candidate.totalVotes}',
							maleVotes:'${candidate.maleVotes}',
							femaleVotes:'${candidate.femaleVotes}',
							maleAndFemaleVotes:'${candidate.maleAndFemaleVotes}',
							totalVotesPercent:'${candidate.totalVotesPercent}',
							maleVotesPercent:'${candidate.maleVotesPercent}',
							femaleVotesPercent:'${candidate.femaleVotesPercent}',
							maleAndFemaleVotesPercent:'${candidate.maleAndFemaleVotesPercent}',
							rank:'${candidate.rank}',
							overallMaleVotesPercent : '${candidate.overallMaleVotesPercent}',
							overallFemaleVotesPercent : '${candidate.overallFemaleVotesPercent}',
							overallMaleOrFemaleVotesPercent : '${candidate.overallMaleOrFemaleVotesPercent}',						
							maleVotesPercentInConstiVotes:'${candidate.maleVotesPercentInConstiVotes}',
							femaleVotesPercentInConstiVotes:'${candidate.femaleVotesPercentInConstiVotes}',
							maleOrFemaleVotesPercentInConstiVotes:'${candidate.maleOrFemaleVotesPercentInConstiVotes}',
							status:'${candidate.status}'
					   };
					  vTObj.electionTrendzOverviewVO.partyElectionTrendzVO.push(cObj);
		</c:forEach>
		
		
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.status = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.status}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes}';

		
		<c:forEach var="asmElection" items="${electionTrendzReportVO.prevElectionYearsInfo.assemblyElections}">	
			var assemblyElecObj={
									electionId:'${asmElection.electionId}',
									electionTypeId:'${asmElection.electionTypeId}',
									electionType:'${asmElection.electionType}',
									stateId:'${asmElection.stateId}',
									state:'${asmElection.state}',
									electionYear:'${asmElection.electionYear}' 
								};

			vTObj.previousElectionYears.assemblyElections.push(assemblyElecObj);
		</c:forEach>

		<c:forEach var="parElection" items="${electionTrendzReportVO.prevElectionYearsInfo.parliamentElections}">	
			var parElecObj={
									electionId:'${parElection.electionId}',
									electionTypeId:'${parElection.electionTypeId}',
									electionType:'${parElection.electionType}',
									stateId:'${parElection.stateId}',
									state:'${parElection.state}',
									electionYear:'${parElection.electionYear}'
								};

			vTObj.previousElectionYears.parliamentElections.push(parElecObj);
		</c:forEach>



	<c:forEach var="vInfo" items="${constituencyVO.assembliesOfParliamentInfo}" >	
		var obj ={
					year:'${vInfo.year}',
					info:[]
				};
		<c:forEach var="info" items="${vInfo.votersInfoForMandalVO}" >	
		var urlStr = '';
		if('${constituencyVO.electionType}' == 'Parliament')
			urlStr += 'constituencyPageAction.action?constituencyId=${info.mandalId}';
		else{
			if(${info.isMandal})
				urlStr += 'mandalPageElectionInfoAction.action?MANDAL_ID=${info.mandalId}&MANDAL_NAME=${info.mandalName}';
			else
				urlStr += 'localBodyElectionAction.action?localBodyId=${info.mandalId}';
		}
		
		var vObj=
				{
					mandalId:'${info.mandalId}',
					mandalName:'<a href="'+urlStr+'"> ${info.mandalName}</a>',
					mandalMaleVoters:'${info.totalMaleVoters}',
					mandalFemaleVoters:'${info.totalFemaleVoters}',
					mandalTotalVoters:'${info.totalVoters}',
					isPartial:'${info.isPartial}'
				 };
			obj.info.push(vObj);
		</c:forEach>
			constituencyPageMainObj.constituencyVotersInfo.push(obj);
	</c:forEach>
	<c:forEach var="candidate" items="${userDetails.candidateVO}">	
			var userObj={
								id:'${candidate.id}',
								candidateName:'${candidate.candidateName}',
								status:'${candidate.status}',
								constituencyName:'${candidate.constituencyName}'
							};
				
			constituencyConnectedPeople.push(userObj);
		</c:forEach>

	var tehsilElections={
			zptcElectionYears:[],
			mptcElectionYears:[]
	};
	var totalZptcSeats,totalMptcSeats;
	var hideMptcZptcDiv;
	var counter = 0;
	var myDataTableForParty,myDataTableForMptcParty,zptcElectionYear,mptcElectionYear;
	var mptcElectionTypeId=${mptcElectionId},zptcElectionTypeId=${zptcElectionId};
	var mptcElectionType="${mptcElectionType}",zptcElectionType="${zptcElectionType}";
	
	var tehsilDetails={
			zptcArray:[],
			mptcArray:[],
			partyArray:[],
			partyMptcArray:[]
	};
	
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
	



	function callAjax(jsObj,url)
	{	
	
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task=="partiesPerformanceInDiffElectionsAjax")
								{
									showAllPartiesAllElectionResultsChart(myResults);
								
								}

								else if(jsObj.task == "getNextPrevCandidateTrendz")
								{
									buildCandidateVotingTrendzGraphData(jsObj,myResults);
								}										
								else if(jsObj.task == "getVotingTrendzForElectionYears")
								{									
									buildVotingTrendzData(myResults);
								}


								else if(jsObj.task == "getConstituencyElections")
								{		
									buildElectionsSelectBox(myResults);									
								}else if(jsObj.task == "getParliamentConstituencyElectionResults")
								{		
								    document.getElementById("censusAjaxImgDivForParlinit").style.display ="none";	
									parliamentResult = myResults;
									buildParliamentResults(jsObj.electionYear,myResults);			
								}

								else if(jsObj.task == "getConstituencyResultsBySubLocations")
								{	
                                    document.getElementById("parliamentElectionResultsDivNew").innerHTML ="";								
									constituencyResults = myResults;
									buildCensusSelect(constituencyResults);
									buildConstituencyElecResultsDataTable("number");
									buiidElecResultRadioSelect();
									buildConstituencyElectionResultsDataTable("number");
								}

								else if(jsObj.task == "getCensusDetailsForAConstituency")
								{
								    document.getElementById("censusAjaxImgDiv").style.display="none";
									censusResult = myResults;
									buildCensusChartForAConstituency(myResults);
									buiidCensusRadioSelect();	
									buildConstituencyElectionResultsDataTableWithCensus(myResults,"number");
								}
								else if(jsObj.task == "getParliamentConstituencyElectionResultsCensus")
								{
								  document.getElementById("censusAjaxImgDivForParl").style.display ="none";
								  censusResultForParl = myResults;
								  
								  buiidCensusRadioSelectForParl();
								  buildConstituencyElectionResultsDataTableWithCensusParl(myResults,"number");
								  buildCensusChartForAConstituencyParl(myResults);
								}
								else if(jsObj.task == "getAnnouncementDetailsOfAConstituency")
								{
                                  
									   buildAnnouncementDetails(myResults);
								  
								 /* else
								   {
									   hideAnnouncementDetails();
								   }*/
								}

								else if(jsObj.task == "getUserAnnouncementDetails")
								{
								   /*hideAjaxImage(jsObj.divId)*/
								buildUserAnnouncementDetails(jsObj,myResults);
								}





								else if(jsObj.task == "mandalVotesShareDetailsChart")
								{
									showMandalVotesShareDetailsChart(myResults);
									showMandalsAfterDelimitationDiv();
								}

								

								else if(jsObj.task == "getZptcElectionResults")
								{		
								
								
									if(myResults!= null &&  myResults.length>0){
									hideMptcZptcDiv=false;
									buildZptcResults(myResults);
									
									}else{
										zptcPresent = true;
										hideMptcZptcDiv =true;
										hideZptcOrMptcDiv('ZPTC');
										showRuralDivIfPresent();
									}	
								
								}

								else if(jsObj.task == "getMptcElectionResults")
								{	
									if(myResults!= null &&  myResults.length>0){
									hideMptcZptcDiv =false;	
									buildMptcResults(myResults);
									}
								
									
										 
								

								else{
								          mptcPresent = true;
											hideMptcZptcDiv =true;
										hideZptcOrMptcDiv('MPTC');
										showRuralDivIfPresent();
										}
								}	
							

								else if(jsObj.task == "getConstituencyElectionYears")
								{
                                   if(myResults != null && myResults.length > 0)
								  {
                                      buildElectionYearsWithAssets(myResults);
								   }
								}
								else if(jsObj.task == "getCandidateAssetsAndLiabilitiesInfo")
								{
                                   if(myResults != null)
								   {  
									   buildAssetsAndLiabilities(myResults);

								   }
                                   hideBusyImgWithId("electionYearSelectForAssets");
								}
                                else if(jsObj.task == "getConstituencyElectionsYersForAss"){
								    buildConstituencyElectionsYersForAss(myResults);
								}
								else if(jsObj.task == "getAllConnectedUsers")
								{	
									showAllConnectedUsersInPanel(jsObj,myResults);
								}	
								if(jsObj.task == "connectUserSet")
								{									
									showAllConnectedUsersStatus(jsObj,myResults);
								}
								if(jsObj.task == "getAllConnectedUsersByFilterView")
								{
									showAllConnectedUsersInPanelByFilterView(jsObj,myResults);
								}
								if(jsObj.task == "getCandidateNominationDetails")
								{
									showCandidateNominationsInRecentElections(myResults);
								}
								if(jsObj.task == "sendMessageToConnectUser")
								{						
									showMessageConfirmation(myResults);
								}
								
								
								else if(jsObj.task == "municipalElectionsInfo")
								{		
									
										//totalNoOflocalElectionsBodies++;						
										showMunicipalInfo(myResults);

									}



										else if(jsObj.task == "corporationElectionsInfo")
								{	
																
										showCorporationInfo(myResults);
									
									
										
								}
								else if(jsObj.task == "greaterElectionsInfo" || jsObj.task == "getGhmcResultsBasedOnSelection")
								{		
										
										showGreaterInfo(myResults);
									
										
								}
								else if(jsObj.task == "constituencySubscriptionDetails")
								{
									unSubscribeBtnBuild();
								}
								else if(jsObj.task == "constituencyUnsubscriptionDetails")
								{
									subscribeBtnBuild();
								}

								else if(jsObj.task  == "municipalElectionsInfoByAssmeblyConsIds") 
							{
								
								if(myResults.muncipalityVO == null)
								{
								hideAndShowMuncipality();
								}
							showMuncipalDetailsForAssemblyConst(myResults.muncipalityVO,"MUNCIPALITY");
						
						}
						else if(jsObj.task  == "corporationElectionsInfoByAssmeblyConsIds")
							{
						 $('#CorporationPieChartDiv').hide();
						 if(myResults.muncipalityVO == null)
								{
							hideAndShowCorporation();
								}
						if(myResults.muncipalityVO != null)
							 $('#CorporationPieChartDiv').show();
							showMuncipalDetailsForAssemblyConst(myResults.muncipalityVO,"CORPORATION");
							
						}
					
							
								
							}catch (e) {   
							   	//alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

	
		function openConstVotingTrendzWindow(distId,constId,constName)
			{			
	
		var browser1 = window.open("constituencyVotingTrendzAction.action?districtId="+distId+"&constiId="+constId+"&constiName="+constName,"biElectionConstituencyResults1","scrollbars=yes,resizable=1,height=650,width=900,left=200,top=200");

		browser1.focus();
		}
		
		function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
		{
		
	   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   browser1.focus();
		}


	function showDetailedChart(chartName)
		{		
		var elmt = document.getElementById('detailedChartDIV');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');

	    var str='';
		str+='<img src="charts/'+chartName+'" />';
		divChild.innerHTML=str;
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "800px", 		
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:600,
				  y:800
	             } );
		createGroupDialog.render();
	}
	function handleCreateGroupSubmit()
	{
		createGroupDialog.hide();			
	}

	function handleCreateGroupCancel()
	{
		this.cancel();
	}
	function hideComparedResultsDiv()
    {
	var elmt = document.getElementById("detailedChartDiv");
	if(!elmt)
		return;
		
	elmt.style.display = 'none';
	
    }
	

	
	function buildConstituencyElecResultsDataTable(value){
	
	if(constituencyResults == null){
		var mandalwiseVotingTrendzEL = document.getElementById("MandalwiseVotingTrendz");
		if(mandalwiseVotingTrendzEL)
			mandalwiseVotingTrendzEL.style.display = 'none';
		return;
	}
	
	if(constituencyResults.electionType == 'Assembly'){		
		var parliamentButtonDiv = document.getElementById("parliamentResultsButtonDiv");
		var str = '';
		var electionSelect = document.getElementById("electionYearSelect");
		var elecYear = electionSelect.options[electionSelect.selectedIndex].text;
		str += "<table><tr>";
		str += '<td><div class="view-all"><a style="cursor:pointer;" onclick="getConstiElecYearsForAss();"/> Parliament(s)</a></td>';
        str += '<td><div style="display:none" id="constituencyParlYearsDiv"><b>Select Parliament Election Year :&nbsp;<select id="constituencyParlYears" onchange="getParliamentResults(this.options[this.selectedIndex].value)"/> <option value="0">Select Year</option></select></div></td>';
		str += '<td><div id="censusAjaxImgDivForParlinit" align="center" style="display:none;"><img width="16px" height="16px" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
		// Detailed Chart is disabled for Assembly.
		// modified by sai
		/*str += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart For Assembly"></div></td>';*/

		str += '<td><div class="view-all"><a  href="municipalWardsAssemblyBoothsMapperAction.action?windowTask=update&constituencyId='+constituencyId+'" >Map Municipal/Corp/GMC to Assembly</a></div></td>';
		str += "</tr></table>";
		parliamentButtonDiv.innerHTML = str;		

		
	}
	if(constituencyResults.electionType != 'Assembly'){		
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><a href="javascript:{}" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart">Detailed Chart</a></div></td>';
		details.innerHTML = detailsDIV;
	}
	
		/*if(counter>=1){
		
		// Parliament Detailed Chart is disabled.
		// Modified by sai

		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart For Parliament"></div></td>';
		details.innerHTML = detailsDIV;
	}*/
	
	var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");
	var chart = '';
	chart += '<div style="margin-left:100px;"><img src="charts/'+constituencyResults.chartPath+'"/></div>';
	chartResultDiv.innerHTML = chart;
	var imgElmt = document.getElementById('AjaxImgDiv');
	if(imgElmt.style.display == "block")
	{
          imgElmt.style.display = "none";
	}
    var conName = constituencyResults.constituencyName;
	var elecYear = constituencyResults.electionYear;
	var elecTyp = constituencyResults.electionType;
    getInteractiveChart(chartResultDiv,constituencyResults.constituencyOrMandalWiseElectionVO,constituencyResults.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
		
	
	
	}

	function getParliamentResults(elecYear){
		if(elecYear == 0 || elecYear == '0')
		 return;
		counter++;
	    document.getElementById("censusAjaxImgDivForParlinit").style.display ="block";	
		var jsObj = {
				constituencyId:${constituencyId},
				electionYear:elecYear,
				censusYear:'',
				delimitationYear:'',
				seletedIndex:'',
				seletedText:'',
				task:"getParliamentConstituencyElectionResults"
			};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getParliamentMandalResultsAction.action?"+rparam;
		callAjax(jsObj, url);
		}


	function buildParliamentResults(parlyear,results){
		if(results != null){
			var parliamentDivElmt = document.getElementById("parliamentElectionResultsDivNew");
			if(parliamentDivElmt.style.display == "none")
			{
				parliamentDivElmt.style.display = "block";
			}
			var str = '';
			str += '<table  style="padding-left:11px;padding-top:20px;">';
		    str += '<tr>';
		    str += '<th>To Compare Mandal Wise Election Results With Census, Select Any Census Parameter:';
		    
		    str += '&nbsp;&nbsp;';
		    str += '<select id="censusSelectForParl" onchange = "getParliamentResultsForCensus(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'+parlyear+')">';
		    str += '<option value=\'0\'>Select Census</option>';
		    str += '<option value=\'1\'>SC Population</option>';
		    str += '<option value=\'2\'>ST Population</option>';
		    str += '<option value=\'3\'>Literates</option>';
		    str += '<option value=\'4\'>illiterates</option>';
		    str += '<option value=\'5\'>Working People</option>';
		    str += '<option value=\'6\'>Non Working People</option>';
		    str += '</select>';
		    str += '</th>'
		    str += '<td><div id="censusAjaxImgDivForParl" align="center" style="display:none;"><img width="16px" height="16px" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
		    str += '</tr>';
		    str += '<tr>';
		    str += '<td><b>(In the graph Census Details are in Percentages, these Percentages Calculated Over Total Population)</b></td>';
		    str += '</tr>';
		    str += '</table>';
			str+='<span id="heading"></span>';
			str += '<div id="newCensusSelForConParl">';
			str += '<table  style="margin-left: 13px; margin-bottom: -43px;">';
			str += '<th>Select Format You Want:</th>';
			str += '<td><input type="radio" name="parliament" value="number" checked="checked" onclick="buildParliamentResultDT(this.value)" style="margin: 3px;">By Votes</td>';
			str += '<td><input type="radio" name="parliament" value="percentage" onclick="buildParliamentResultDT(this.value)" style="margin: 3px;">By Percentage</td>';
			str += '</table>';
			str += '</div>';
			str += '<div id="resultDataTableDivForParl"></div>';
			str += '<div id="resultGraphDivForParl"></div>';
			parliamentDivElmt.innerHTML = str;
			buildParliamentResultDT("number");
		 }else{
		    var parliamentDivElmt = document.getElementById("parliamentElectionResultsDivNew");
			if(parliamentDivElmt.style.display == "none")
			{
				parliamentDivElmt.style.display = "block";
			}
			parliamentDivElmt.innerHTML = "<span style='margin-left:400px;'><b>No Records Found</span></b>";
		 }
			
		}

function buildParliamentResultDT(checked){
	var parliamentDiv = document.getElementById("resultDataTableDivForParl");
	var str = '';
	var details = document.getElementById("detailsDiv");
	var detailsDIV = '';

	if(parliamentResult == null){
		detailsDIV += 'No Data Available';
		return;	
	}
	
	// Parliament Detailed Chart is disabled.
	// Modified by sai

	/*detailsDIV += '<div><input type="button" class="button" onclick="showDetailedChart(\''+parliamentResult.detailedChartPath+'\')" value="Detailed Chart For Paliament"></div>';	*/
	//$("#heading").html("Mandal Wise Election Results For "+parliamentResult.constituencyName+" Parliament In "+parliamentResult.electionYear+"");

	str += '<div id="parliamentElecResDiv" style="margin-top:20px;">';
	str += '<table id = "parliamentElecResTable" width="80%">';
	for(var j in parliamentResult.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(parliamentResult.constituencyOrMandalWiseElectionVO[j].showLink)
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationId+'&MANDAL_NAME='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'">'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</a></td>';
		else
			str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</td>';
		for(var k in parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs){
			if(checked == 'number')
				str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesEarned+'</td>';
			else
				str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesPercentage+'</td>';
		}
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	str += '<div id="parliamentChartDiv"><img src="charts/'+parliamentResult.chartPath+'"></div>';

	parliamentDiv.innerHTML = str;
	if(counter!=0){
		details.innerHTML = detailsDIV;
	}
	
	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 var villageHead = {
				key:"Mandal",
				lable: "Mandal",
				sortable:true
		   }

	 var villageValue = {key:"Mandal"}

	 myColumnDefs.push(villageHead);
	 myFields.push(villageValue);
	 

	 for(var j in parliamentResult.candidateNamePartyAndStatus){
		var obj1 = {
					key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
					label:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
					sortable:true
				}
		var obj2 = {
					key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("parliamentElecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
										fields:myFields    
									};
var villageDataTable = new YAHOO.widget.DataTable("parliamentElecResDiv",myColumnDefs, myDataSource,{caption:"Mandal Wise Election Results For "+parliamentResult.constituencyName+" Parliament Constituency In "+parliamentResult.electionYear+""});
	
	var conName = parliamentResult.constituencyName;
	var elecYear = parliamentResult.electionYear;
	var elecTyp = parliamentResult.electionType;
	var parlDivElmnt = document.getElementById("parliamentChartDiv");
	getInteractiveChart(parlDivElmnt, parliamentResult.constituencyOrMandalWiseElectionVO,parliamentResult.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
}


//All Parties Performance In Different Elections Linechart
	function showAllPartiesAllElectionResultsChart(myResults)
	{
		
   var chartColumns = myResults[0].partiesList;
  
     var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'Party');
 
     var partiesArray = new Array();
     //for chart columns
	 for(var i in chartColumns)
	 {
		
	   var colData = chartColumns[i].name;
	  
	   data.addColumn('number', colData);

	   partiesArray.push(chartColumns[i].name);
	 }

      //for chart rows
	  for(var j in myResults)
	  {
		  
		  var array = new Array();
		  var year = myResults[j].electionYear+" "+myResults[j].electionType;
		  array.push(year);

		  for(var k in myResults[j].partyResultsVO)
		  {
			  var percentage = myResults[j].partyResultsVO[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

	  var ctitle = 'All Parties Performance In Different Elections'; 
	 var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");

      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

	  if(staticColors != null && staticColors.length > 0)
	  {
		  new google.visualization.LineChart(chartResultDiv).
			  draw(data, {curveType: "function",width: 623, height: 400,title:ctitle,colors:staticColors,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});
	  }
	  else
	  {
          new google.visualization.LineChart(chartResultDiv).
			  draw(data, {curveType: "function",width: 623, height: 400,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});
	  }
}

//Mandals Voters Details  piechart
	function showMandalVotesShareDetailsChart(myResults)
	{
	 var countal = 0;
	         if(myResults.assembliesOfParliamentInfo != null)
	             {
				  var countLoc = 0;
				   for(var x in myResults.assembliesOfParliamentInfo )
	                {
					  if(myResults.assembliesOfParliamentInfo[x].votersInfoForMandalVO != null && myResults.assembliesOfParliamentInfo[x].votersInfoForMandalVO.length > 0)
					     countLoc = countLoc+1;
					}
				   countal = countLoc; 
	            }
	var mandalwiseVotersShare = myResults.assembliesOfParliamentInfo;
			
		for(var c in mandalwiseVotersShare){

			var data = new google.visualization.DataTable();
			
			data.addColumn('string', 'Mandals');
			data.addColumn('number', 'Voters % Share');


			data.addRows(mandalwiseVotersShare[c].votersInfoForMandalVO.length);
			var k=0;
			for (var i in mandalwiseVotersShare[c].votersInfoForMandalVO)
			{
			data.setValue(k, 0, mandalwiseVotersShare[c].votersInfoForMandalVO[i].mandalName);
			data.setValue(k, 1,  mandalwiseVotersShare[c].votersInfoForMandalVO[i].totVoters);
			k++;
			}
			
			var ctitle='';
			var chartDiv='';
          
			if(c == 0)
			{
				if(document.getElementById('divInteractive_Chart_0')){
					
			
					if(c == 0){
					 if(countal == 1)
					 {
					   chartDiv = document.getElementById('divInteractive_Chart_0');
						if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
						ctitle = 'Assembly Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In '+myResults.assembliesOfParliamentInfo[0].constYear;
						else 
						ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In '+myResults.assembliesOfParliamentInfo[0].constYear;
					 }
					 else
					 {
						chartDiv = document.getElementById('divInteractive_Chart_0');
						if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
						ctitle = 'Assembly Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2009';
						else 
						ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2009';
					 }
					}
					var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width: 580, height: 320, title: ctitle, legendFontSize:14,fontSize:13,titleFontSize:16,tooltipFontSize:15, stroke:3});
				}
			}
			else
			{
				if(document.getElementById('divInteractive_Chart_1')){
					
					
					if(c == 1){
					chartDiv = document.getElementById('divInteractive_Chart_1');
					if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
					ctitle = 'Assembly Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2004';
					else
					ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2004';
					}
					var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width: 580, height: 320, title: ctitle, legendFontSize:14,fontSize:13,titleFontSize:16,tooltipFontSize:15, stroke:3});
					
					
				}
			}
			
		}
		
		showDivDisplay(countal,myResults);
		buildCenterVotersCandidateInfoContent();
}
    function showDivDisplay(countal,myResults)
     {
       if(countal == 0)
	   {
	     document.getElementById('hidemandDIV').style.display='none';
	   }
	   else if(countal == 1)
       {
	     document.getElementById('allDIV').style.display='none';
		 if(myResults.assembliesOfParliamentInfo[0].constYear == "2009")
		   document.getElementById('oneDIV').style.display='block';
		 else
		   document.getElementById('twoDIV').style.display='block';
	   }
     }
	function getZptcPartyDetails(elecYear){
	
	if(document.getElementById('mptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('mptcPartyTrendsDetailsDiv').style.display='none';
	/*if(document.getElementById('municipalityData_main').style.display=='block')
		document.getElementById('municipalityData_main').style.display='none';*/

	if(document.getElementById('zptcPartyTrendsDetailsDiv').style.display=='none')
		document.getElementById('zptcPartyTrendsDetailsDiv').style.display='block';
	
	zptcElectionYear = elecYear;
	constituencyTYPE = constituencyPageMainObj.constituencyInfo.constituencyType;
	var jsObj = {
			constituencyType:constituencyPageMainObj.constituencyInfo.constituencyType,
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getZptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}

	function getMptcPartyDetails(elecYear){
	
	if(document.getElementById('mptcPartyTrendsDetailsDiv').style.display=='none')
		document.getElementById('mptcPartyTrendsDetailsDiv').style.display='block';
	if(document.getElementById('zptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('zptcPartyTrendsDetailsDiv').style.display='none';
	/*if(document.getElementById('municipalityData_main').style.display=='block')
		document.getElementById('municipalityData_main').style.display='none';*/
	
	
	constituencyTYPE = constituencyPageMainObj.constituencyInfo.constituencyType;

	mptcElectionYear = elecYear;
	var jsObj = {
			constituencyType:constituencyPageMainObj.constituencyInfo.constituencyType,
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getMptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}
	function hideZptcOrMptcDiv(election)
	{
		
		
	var divEle = null;
	if(election == 'MPTC')
	{
		divEle = document.getElementById('mptcPartyTrendsDetailsDiv');
		document.getElementById('mptcPartyTrendsDetailsDiv').innerHTML ='';
		document.getElementById('MptcChartDiv').innerHTML ='';
		document.getElementById('totalMptcCountResultDiv').innerHTML ='';
			//document.getElementById('mptcheadDiv').innerHTML ='';
			//document.getElementById('countMptcDiv').innerHTML ='';
		//document.getElementById('mptcCandidateLink').innerHTML ='';
			//document.getElementById('mptcElectionIdsSelectDiv').innerHTML ='';
		//document.getElementById('mptcDivBody').innerHTML ='';
	}
	else if(election == 'ZPTC')
	{

		divEle = document.getElementById('zptcPartyTrendsDetailsDiv');
			document.getElementById('headDiv').innerHTML ='';
			document.getElementById('countZptcDiv').innerHTML ='';
		document.getElementById('zptcCandidateLink').innerHTML ='';
			document.getElementById('zptcElectionIdsSelectDiv').innerHTML ='';
						
	}
		
	var str = '<font color="red"><B>No Data Available For This Selection</B></font>';
	divEle.innerHTML = str;
	
	}

	function showAndHide(id)
	{
	
	//var divInteractive_Chart_0=document.getElementById('divInteractive_Chart_0');
	//var divInteractive_Chart_1=document.getElementById('divInteractive_Chart_1');
	if(id == 'divInteractive_Chart_0'){
	if(document.getElementById("divInteractive_Chart_1").style.display == 'block')
	document.getElementById("divInteractive_Chart_1").style.display = 'none';
 
	}
	if(id == 'divInteractive_Chart_1'){
	if(document.getElementById("divInteractive_Chart_0").style.display == 'block')
	document.getElementById("divInteractive_Chart_0").style.display = 'none';
 
	}

	}

	function showMunicipalInfo(myResults){


	var HeadElmt = document.getElementById('municipalityData_main');
	var str ='';	
	if(myResults.muncipalityVO == null){
		var showDiv = document.getElementById('muncipalDiv');
		str +='No Data available For MUNCIPALITY';
		//showDiv.style.display = "str";
	}
	
	buildCorpOrMunicipTable(HeadElmt, myResults, "MUNCIPALITY","showMoreMuncipalResultsDiv");
	}

	function buildCorpOrMunicipTable(divId, myResults, elecType,showMoreDiv){
	
	var moreElmt = document.getElementById(showMoreDiv);
	
	var appendingStr = '';
	for(var i in myResults.muncipalityVO){
		appendingStr += '<a href="javascript:{}" title="Click here to View ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency '+elecType+'\'s Election Results" onclick="redirectMuncipalityCandidateLink(\''+elecType+'\','+ myResults.muncipalityVO[i].electionTypeId+','+ myResults.muncipalityVO[i].muncipalityId+','+myResults.muncipalityVO[i].latestMuncipalElectionYear+',\''+myResults.muncipalityVO[i].muncipalityName+'\')" style="text-decoration:none;" class="candidateDetailsStyle">Show Results</a>';
	}	
	moreElmt.innerHTML = appendingStr;
	
	var str = '';
	var selectId=document.getElementById('MuncipalElectionSelectDiv');
	if(myResults.muncipalityVO==null)
	{
		document.getElementById('muncipalityDiv').innerHTML='';
		//str +='<font color="#5CB275" size:10px>No Data available .......</font>';
		document.getElementById('MuncipalElectionSelectDiv').innerHTML='';
		muncipalityPresent = true;
		showUrbanDivIfPresent();
		
	}
	for(var i in myResults.muncipalityVO){
		str += '<div style="display:block;width:auto;position:relative;clear:both;margin-top:15px;color:#5CB275;">';
		str += '<a  style="text-decoration:none;color:#5CB275;font-weight:bold;" href=\"localBodyElectionAction.action?stateId='+myResults.muncipalityVO[i].stateId+'&localBodyElectionTypeId='+myResults.muncipalityVO[i].electionTypeId+'&localBodyId='+myResults.muncipalityVO[i].muncipalityId+'\">Detailed View of '+myResults.muncipalityVO[i].muncipalityName+' '+elecType+' Election Results In '+myResults.muncipalityVO[i].latestMuncipalElectionYear+'</a></div>';
		str += '<table style="width:90%"><tr><td>';
		str += '<div style="display:inline-block;width: 309px;position:relative;float:left;margin:10px; border: solid 1px #d3d3d3;" id=\"'+elecType+'TableDiv_'+i+'\"></div>';
		str+='</td><td>'
		str += '<div id="municipalityChartDiv"  style="position: relative; right: 0px; left: 28px;"></div>';
		str += '</td></tr></table>';
	}
	divId.innerHTML = str;
	buildDataTable(elecType);
	}



	function buildElectionResults()
	{
	
	var HeadElmt = document.getElementById('constituencyPageElectionInfoDiv_Head');
	var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");
	var chartName = "${chartName}";
	var chart = '';
	var enlargedChartName = "${enlargedChartName}";
	var details = document.getElementById("constituencyPageElectionEnlargedImgDiv");
	var detailsDIV = '';

	chart+='<img src="charts/'+chartName+'" style="width:550px"/>';
	chartResultDiv.innerHTML = chart;
	
	detailsDIV += '<table><tr>';
	detailsDIV += '<td><div><a href="javascript:{}" title="Click here to view All Parties performance in different elections of ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency" style="background:none repeat scroll 0 0 #335291;color:#FFFFFF;font-size:13px;margin-left:432px;padding:5px;width:113px;" onclick="showDetailedChart(\''+enlargedChartName+'\')" value="Detailed Chart">Detailed Chart</a></div></td>';
	
	details.innerHTML = detailsDIV;
	document.getElementById('constituencyPageElectionInfoDiv_Body').style.display='none';
	
}

	function detailedElectionResult(){
		
		var BodyElmt = document.getElementById('detailedElectionInfoDiv_Body');
		
		var minusPlusDivElmt = document.getElementById("minusPlusDiv");
		var elecStr = '';
		var str = '';
			str+='<a style="cursor:pointer;" title="Click here to Hide ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency Detailed Election Information" onclick="hideDetailedElectionResultDiv(\'detailedElectionInfoDiv_Body\')"> <img src="images/minus.png" alt="" class="fleft" style="padding:3px 0px 0px 5px;"/> <b>&nbsp;Click here to Hide</b></a></span>';
		minusPlusDivElmt.innerHTML = str;

		for(var i in constituencyPageMainObj.constituencyElectionInfo)
		{
			var data = constituencyPageMainObj.constituencyElectionInfo[i];
			var info = constituencyPageMainObj.constituencyInfo;
			elecStr+='<div id="constituencyElectionInfo_'+i+'" class="electionInformationClass" title="Click here to view ${constituencyDetails.constituencyName} &nbsp; '+info.constituencyType+' constituency Election Results in '+data.year+'"  onmouseover="addCss(this.id);" onmouseout="removeCss(this.id);" onclick="showDetailedElectionResult(this.id)">';
			elecStr+='<span id="pointerImg"> <img height="10" width="10" src="'+constituencyPageMainObj.contextPath+'/images/icons/arrow.png"/></span>';
			elecStr+='<span id=""> <b> In '+data.year+' - '+data.candidateName+' - ' +data.partyShortName+ ' Won with  '+data.votesMargin+' Majority Of Votes On '+ data.oppositionCandInfo[0].candidateName+ ' - ' +data.oppositionCandInfo[0].partyShortName+ ''
			 '</b></span>';		
			elecStr+='</div>';
		}
		
		if(BodyElmt)
			
			BodyElmt.innerHTML=elecStr;
		document.getElementById('detailedElectionInfoDiv_Body').style.display ='block';
		
		}

		function hideDetailedElectionResultDiv(divElmt)
		{
		document.getElementById(divElmt).style.display ='none';
		var minusPlusDivElmt = document.getElementById("minusPlusDiv");
		var str = '';

		str+='<a title="Click here to View ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency Detailed Election Information" style="cursor:pointer;" onclick="detailedElectionResult()"> <img  src="images/plus.png" alt="" class="fleft" style="padding:3px 0px 0px 5px;cursor: pointer;"/><b>&nbsp;Click here to View</b></a></span>';

		minusPlusDivElmt.innerHTML = str;
		}
	 function buildElectionYearsWithAssets(myResults)
		{
		
		var divElmt = document.getElementById("electionYearsWithAssets_Div");
		var selectedVal = '';

        var electionYearSelect = '';

        electionYearSelect += '<table>';
		electionYearSelect += '<th>Select Election Year to view Assets & Liabilities :</th>';
		electionYearSelect += '<th style="text-align:left;">';
		electionYearSelect += '<select id="electionYearSelectForAssets" class = "selectWidth" onchange = "getcandidateAssetsAndLiabilities(this.options[this.selectedIndex].value)">';
		for(var i in myResults)
		{			
			electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
			if(i == 0)
				selectedVal = myResults[i].id;
		}
		electionYearSelect += '</select>';
		electionYearSelect += '<td><span id="electionYearSelectForAssets_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span></td>';
		electionYearSelect += '</th>';

		electionYearSelect += '</table>';
		electionYearSelect += '<h1 class="topfive"></h1><span class="hideDetailsBtnClass"><a style="cursor:pointer;" onclick="hideCandidateAffidavit(\'electionYearsPanel_Main_Div\')" >Hide Details</a></span>';

		
	    divElmt.innerHTML = electionYearSelect; 
		
		getcandidateAssetsAndLiabilities(selectedVal);
		document.getElementById("electionYearsPanel_Main_Div").style.display = 'block';
		document.getElementById("electionYearsPanel_Main_Div").style.clear = 'both';

	}

	function hideCandidateAffidavit(divId)
	{
		
		document.getElementById("electionYearsPanel_Main_Div").style.display = 'none';
	}

	function getAssetsElectionYearsInfo(constiId)
	{
        var jsObj=
		{
				constituencyId:constiId,
				task:"getConstituencyElectionYears"						
		};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/constituencyElectionYearsWithAssets.action?"+rparam;
		callAjax(jsObj,url);
	}


	function getcandidateAssetsAndLiabilities(constiELecId)
	{	
		showBusyImgWithId("electionYearSelectForAssets");

		var jsObj=
		{
				constiElecId:constiELecId,
				task:"getCandidateAssetsAndLiabilitiesInfo"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/candidateAssetsAndLiabilitiesAction.action?"+rparam;
		callAjax(jsObj,url);
	}

	function hideBusyImgWithId(elmtId)
	{
	
	var spanElmt = document.getElementById(elmtId+"_ImgSpan");
	if(spanElmt)
		spanElmt.style.display = "none";
	}

	function showMandalsAfterDelimitationDiv()
	{
		document.getElementById('divInteractive_Chart_0').style.display = 'block';
		document.getElementById('divChild_Body_0').style.display = 'block';
		document.getElementById('divInteractive_Chart_1').style.display = 'none';
		if(document.getElementById('divChild_Body_1') != null)
		document.getElementById('divChild_Body_1').style.display = 'none';
		
		
	}

	function showMandalsBeforeDelimitationDiv()
	{
		document.getElementById('divInteractive_Chart_0').style.display = 'none';
		document.getElementById('divChild_Body_0').style.display = 'none';
		document.getElementById('divInteractive_Chart_1').style.display = 'block';
		if(document.getElementById('divChild_Body_1') != null)
		document.getElementById('divChild_Body_1').style.display = 'block';
		
	}


		function showGreterElectionDetails(){
		document.getElementById('greaterDiv').style.display='block';
		document.getElementById('corporationDiv').style.display='none';
		document.getElementById('muncipalDivBody').style.display='none';
		
		
		}
		function showCorporationDetails(){
		document.getElementById('corporationDiv').style.display='block';
		document.getElementById('muncipalDivBody').style.display='none';
		document.getElementById('greaterDiv').style.display='none';
		
		
		}
		function showZptcPartyDetails()
		{
		
		document.getElementById('zptcDivBody').style.display='block';
		document.getElementById('mptcDivBody').style.display='none';
		
		}
		function showMptcPartyDetails()
		{
		document.getElementById('zptcDivBody').style.display='none';
		document.getElementById('mptcDivBody').style.display='block';
		
		}
		/*function showMunicipalityResults(){
		
		document.getElementById('muncipalDivBody').style.display='block';
		document.getElementById('corporationDiv').style.display='none';
		document.getElementById('greaterDiv').style.display='none';
			
		}*/

		function showMunicipalityResults1()
		{
			document.getElementById('muncipalDivBody').style.display='block';
			document.getElementById('corporationDiv').style.display='none';
			document.getElementById('greaterDiv').style.display='none';
		}
		function redirectMptcCandidateLink(){
			 
			 var browser2 = window.open("constituencyPageCandidateDetailsAjaxAction.action?constId="+${constituencyId}+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser2.focus();
		}



		function redirectZptcCandidateLink(){	

			
			 
			 var browser1 = window.open("constituencyPageCandidateDetailsAjaxAction.action?constId="+${constituencyId}+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser1.focus();
		}
		


		function redirectMuncipalityCandidateLink(muncipalityElectionType,muncipalityElectionId,muncipalityId,latestMuncipalElectionYear,name){	
		
		var browser3 = window.open("muncipalElectionReportAction.action?muncipalityId="+muncipalityId+"&muncipalityElectionType="+muncipalityElectionType+"&name="+name+"&muncipalityElectionId="+muncipalityElectionId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
		browser3.focus();
	}



	function buildDataTable(elecType){
		var municipalitySelectBoxValue = document.getElementById('municipalitySelect');
		var municipalitySelect = municipalitySelectBoxValue.options[municipalitySelectBoxValue.selectedIndex].text;

	for(var i in myResults.muncipalityVO){
		var resultsDataSource = new YAHOO.util.DataSource(myResults.muncipalityVO[i].muncipalityVO);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
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

		var resultsColumnDefs = [ {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Seats Contested",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];		
		myDataTableForMptcParty = new YAHOO.widget.DataTable(elecType+"TableDiv_"+i,resultsColumnDefs, resultsDataSource,null);

	}

	var data =  new google.visualization.DataTable();

	data.addColumn('string','partyName');
	data.addColumn('number','seatsWonByParty');
	for(var j=0; j<myResults.muncipalityVO.length;j++){
		data.addRows(myResults.muncipalityVO[j].muncipalityVO.length);
		for(var k=0; k<myResults.muncipalityVO[j].muncipalityVO.length; k++){
		data.setValue(k,0,myResults.muncipalityVO[j].muncipalityVO[k].partyName);
		data.setValue(k,1,myResults.muncipalityVO[j].muncipalityVO[k].seatsWonByParty);
		}
	}
	var chart = new google.visualization.PieChart(document.getElementById('municipalityChartDiv')); 
	chart.draw(data,{width: 420, height: 300, title:'All Parties Performance In  '+constituencyName+' '+elecType+' In '+municipalitySelect+'',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:9}});


}


	function showNextPreviousCandidateVotingTrendz(index,type)
	{
	if(type == 'Previous')
		candidateIndex-=1;
	else if(type == 'Next')
		candidateIndex+=1;

	if(candidateIndex!=1)
		prevButtonElmt.disabled = false;
	else
		prevButtonElmt.disabled = true;

	if(candidateListSize>1 && candidateIndex!=candidateListSize)
		nextButtonElmt.disabled = false;
	else
		nextButtonElmt.disabled = true;

	for(var i =0;i<constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO.length;i++)
	{		
		var data = constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO[i];
		if(data.rank == candidateIndex)
		{
			var jsObj=	{
							partyId:data.partyId,
							partyName:data.partyName,
							partyLogo:data.partyLogo,
							partyFlag:data.partyFlag,
							candidateId:data.candidateId,
							candidateName:data.candidateName,							
							validVotes:data.validVotes,
							totalVotes:data.totalVotes,
							maleVotes:data.maleVotes,
							femaleVotes:data.femaleVotes,
							maleAndFemaleVotes:data.maleAndFemaleVotes,
							totalVotesPercent:data.totalVotesPercent,
							maleVotesPercent:data.maleVotesPercent,
							femaleVotesPercent:data.femaleVotesPercent,
							maleAndFemaleVotesPercent:data.maleAndFemaleVotesPercent,
							rank:data.rank,
							overallMaleVotesPercent : data.overallMaleVotesPercent,
							overallFemaleVotesPercent : data.overallFemaleVotesPercent,
							overallMaleOrFemaleVotesPercent : data.overallMaleOrFemaleVotesPercent,						
							status:data.status,
							maleVotesPercentInConstiVotes : data.maleVotesPercentInConstiVotes,
							femaleVotesPercentInConstiVotes :data.femaleVotesPercentInConstiVotes,
							maleOrFemaleVotesPercentInConstiVotes : data.maleOrFemaleVotesPercentInConstiVotes, 
							task:'getNextPrevCandidateTrendz'
						}

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getNextPrevCandidateVotingTrendz.action?"+rparam;
			
			callAjax(jsObj, url);			
			return;
		}
	}
}

 function hideCensusAjaxImage()
{
 if(document.getElementById('censusAjaxImgDiv') != null)
 {
    var imgElmt = document.getElementById('censusAjaxImgDiv');
	if(imgElmt.style.display == "block")
	{
          imgElmt.style.display = "none";
	}
 }
}

function showCensusError(myResult)
{
  if(document.getElementById('censusErrorMsgDiv') != null)
 {
	var cenErrorEle = document.getElementById("censusErrorMsgDiv");
	
	var cenvar = '';
	cenvar += '<table>';
	cenvar += '<th>Census Data not avaliable for this Constituency.</th>';
	cenvar += '</table>';
	cenErrorEle.innerHTML = cenvar;
	cenErrorEle.style.display = '';
  }
}

function removeCensusNotAvailableErrorMessage()
{ 
  if(document.getElementById('censusErrorMsgDiv') != null)
 {
	var cenErrorEle = document.getElementById("censusErrorMsgDiv");

	if(cenErrorEle)
	{
		cenErrorEle.style.display = "none";
	}
 }
}

	function showDetailedElectionResult(id)
	{

	var index = id.substring((id.indexOf('_')+1),id.length);
	
	var data = constituencyPageMainObj.constituencyElectionInfo[index];
	var info = constituencyPageMainObj.constituencyInfo;
	var constiId = ${constituencyId}; 
	var elecType = constituencyPageMainObj.constituencyInfo.constituencyType; 
	var elecYear = data.year; 

		var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
    browser1.focus();	

	/*var str='';
	str+='<fieldset id="constituencyInfoFieldSet">';
	str+='<legend> Constituency Info </legend>';
	str+='<div id="coinstituencyInfoDiv">';
	str+='<table id="constituencyInfoTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Constituency Name</th>';
	str+='<td>'+info.constituencyName+'</td>';
	str+='<th>Constituency Type</th>';
	str+='<td>'+info.constituencyType+'</td>';	
	str+='</tr>';

	str+='<tr>';	
	str+='<th>District</th>';
	str+='<td>'+info.districtName+'</td>';
	str+='<th>State</th>';
	str+='<td>'+info.stateName+'</td>';	
	str+='</tr>';	
	str+='</table>';
	str+='</div>';
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="WinningCandidateFieldSet">';
	str+='<legend> Winning Candidate Info </legend>';
	str+='<div id="WinningCandidateDiv">';
	str+='<table id="WinningCandidateTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Name</th>';
	str+='<td colspan="3">'+data.candidateName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Party</th>';
	str+='<td>'+data.partyName+'</td>';	
	str+='<th>Year</th>';
	str+='<td>'+data.year+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Votes Earned</th>';
	str+='<td>'+data.votesEarned+'</td>';	
	str+='<th>Votes Percentage</th>';
	str+='<td>'+data.votesPercentage+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';	
	str+='</fieldset>';
	//--------
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> Opposition\'s Results Info </legend>';
	str+='<div id="oppCandResultsDiv">';	
	str+='</div>';
	str+='</fieldset>';

	ElectionResultPanel = new YAHOO.widget.Panel("electionResults_Panel", 
				{
					width:"800px", 
					fixedcenter : false, 
					visible : true,  
					constraintoviewport : true,
					x:200,
					y:400,
					iframe :true,
					modal :true,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	

	ElectionResultPanel.render();
	ElectionResultPanel.setHeader(' Election Results');
	ElectionResultPanel.setBody(str);
	
	 var myDataSource = new YAHOO.util.DataSource(data.oppositionCandInfo); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
	            fields: [
							{key:"candidateName"},
							{key:"partyName"},
							{key:"year",parser:"number"},
							{key:"votesEarned",parser:"number"},
							{key:"votesPercentage",parser:"number"}
						] 
	        }; 
	
	 var myColumnDefs = [ 
	            {key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
	            {key:"partyName", label:'Party Name', sortable:true, resizeable:true}, 
	            {key:"year", label:'Year',sortable:true, resizeable:true}, 
	            {key:"votesEarned",label:'Votes Earned',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
	            {key:"votesPercentage",label:'Votes %', sortable:true, resizeable:true} 
	        ]; 
	 
	var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource); 
	*/
	}




	function buildConstituencyElectionResultsDataTable(value)
	{
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var str = '';
	str += '<div id="elecResDiv" style="width:900px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';
	for(var i in constituencyResults.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName == 'Others *' || 
				!constituencyResults.constituencyOrMandalWiseElectionVO[i].showLink)
			str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</td>';
		else
		if(constituencyResults.electionType == 'Assembly'){
			if(constituencyResults.constituencyOrMandalWiseElectionVO[i].isUrban)
				str += '<td><a  href="localBodyElectionAction.action?stateId=1&localBodyElectionTypeId=5&localBodyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
			else
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else
			str += '<td><a href="constituencyPageAction.action?constituencyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		for(var j in constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs){
			if(value == 'number')
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
		str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	var resultDiv = document.getElementById("missingDataInfoDiv");	
	var missingVotesDiv = '';
	missingVotesDiv+='<b>';
	missingVotesDiv += '<font color="Red"><b>*</b></font>';		
	missingVotesDiv += ' Others Include Postal Ballet Votes';
	missingVotesDiv+='</b>';
	resultDiv.innerHTML = missingVotesDiv;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constituencyResults.electionType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 

	 for(var i in constituencyResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					label:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							        };

	 if(constituencyResults.electionType == 'Parliament'){
		var extraInfoDiv = document.getElementById("missingDataInfoDiv");
		var str = '';
		str += '<br>';
		str += '<table>';
		str += '<tr>';
		str += '<td>';
		str += '<font color="Red"><b>*</b></font>';				
		str += '</td>';
		str += '<td><b>';
			if(constituencyResults.isDataInsufficient){
				str += ' Others Include Postal Ballet Votes, ';
				for(var i in constituencyResults.missingConstituencies)
					str += '<a href="constituencyPageAction.action?constituencyId='+constituencyResults.missingConstituencies[i].id+'">'+constituencyResults.missingConstituencies[i].name + '</a> Assembly ,';
				str = str.substring(0,str.length-1);
				str += ' Wise ${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Election Results';
			}else{
				str += ' Others Include Postal Ballet Votes';
			}
		str += '</b></td>';
		str += '</tr>';
		str += '</table>';		
		extraInfoDiv.innerHTML = str;		
	 }
	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource,{caption:"Mandal Wise Election Results For ${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Constituency In "+constituencyResults.electionYear+" "});
	}

	function getConstituencyElections(){

	var jsObj = {
			constituencyId:${constituencyId},
			task:"getConstituencyElections"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
	}


	function buildCensusSelect(myResult)
	{
	var censelectEle = document.getElementById("censusSelectDiv");
	
	var cenvar = '';
	cenvar += '<table  style="padding-left:11px;padding-top:20px;">';
	cenvar += '<tr>';
	if('${constituencyDetails.constituencyType}' == 'Assembly')
	{
		cenvar += '<th>To Compare Mandal Wise Election Results With Census, Select Any Census Parameter:';
	}

	if('${constituencyDetails.constituencyType}' == 'Parliament')
	{
		cenvar += '<th>To Compare Assembly Wise Election Results With Census, Select Any Census Parameter:';
	}
	cenvar += '&nbsp;&nbsp;';
	cenvar += '<select id="censusSelect" onchange = "getCensusDetailsForAConstituency(\'${constituencyId}\',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
	cenvar += '<option value=\'0\'>Select Census</option>';
	cenvar += '<option value=\'1\'>SC Population</option>';
	cenvar += '<option value=\'2\'>ST Population</option>';
	cenvar += '<option value=\'3\'>Literates</option>';
	cenvar += '<option value=\'4\'>illiterates</option>';
	cenvar += '<option value=\'5\'>Working People</option>';
	cenvar += '<option value=\'6\'>Non Working People</option>';
	cenvar += '</select>';
	cenvar += '</th>'
	cenvar += '<td><div id="censusAjaxImgDiv" align="center" style="display:none;"><img width="16px" height="16px" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	cenvar += '</tr>';
	cenvar += '<tr>';
	cenvar += '<td><b>(In the graph Census Details are in Percentages, these Percentages Calculated Over Total Population)</b></td>';
	cenvar += '</tr>';
	cenvar += '</table>';


	censelectEle.innerHTML = cenvar;
	}


	function buiidElecResultRadioSelect()
	{
	var ElecResultselectEle = document.getElementById("labelRadioDiv");
	
	var selectEle = '';

	selectEle += '<table style="margin-bottom: -45px;"><tr>';
	selectEle += '<td id="labelRadio"><b>Select Format You Want :</b></td>';
	selectEle += '<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTable(this.value)" style="margin: 3px;">By Votes </td>';
	selectEle += '<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElectionResultsDataTable(this.value)" style="margin: 3px;"/>By Percentage </td>';
	selectEle += '</tr></table>';

	ElecResultselectEle.innerHTML = selectEle;
	}
	function buildElectionsSelectBox(myResults){
	var selectDiv = document.getElementById("electionIdsSelectDiv");
	var electionYearSelect = '';	
	var selectDivEl = document.getElementById("MandalwiseVotingTrendz");
	if(myResults.length == 0){
		if(selectDivEl){
			selectDivEl.style.display = 'none';
		}
			
		//electionYearSelect +='<b>Sorry, Constituency/Mandal Data Not Available For This Constituency</b>';
		//var resultDiv = document.getElementById("mandalOrConstiElecResultDiv");
		//resultDiv.style.display = "none";
		//selectDiv.innerHTML = electionYearSelect; 
		return;
	}

	var headingDiv = document.getElementById("MandalVotingTrendz_head");
	var str='';
	if(headingDiv == null)
		return;
	if('${constituencyDetails.constituencyType}' == 'Assembly'){
		//headingDiv.innerHTML = ' Mandal Wise Voting Trendz ';
	str +='<h1 class="gre-title">';
	str +='<span>Mandal Wise Voting Trendz</span>';
		str +='</h1>';
	headingDiv.innerHTML=str;
	}
	if('${constituencyDetails.constituencyType}' == 'Parliament')
		{
		//headingDiv.innerHTML = ' Assembly Wise Voting Trendz '; 
	str +='<h1 class="gre-title">';
	str +='<span> Assembly Wise Voting Trendz</span>';
	str +='</h1>';
		headingDiv.innerHTML = str;
	str+='<br>';
	str+='<br>';
	}
	electionYearSelect += '<table>';
	if('${constituencyDetails.constituencyType}' == 'Assembly')
		  electionYearSelect += '<th>Select Assembly Election Year :</th>';
		else
		  electionYearSelect += '<th>Select Parliament Election Year :</th>';
	electionYearSelect += '<th>';
	electionYearSelect += '<select id="electionYearSelect" class = "selectWidth" onchange = "getConstituencyResults(this.options[this.selectedIndex].text)">';
	for(var i in myResults)
	{			
		electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	electionYearSelect += '</select>';
	electionYearSelect += '</th>';
	electionYearSelect += '<td><div id="AjaxImgDiv" align="center" style="display:none;"><img width="5" height="5" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	electionYearSelect += '<td><div id="parliamentResultsButtonDiv"></td>';
	electionYearSelect += '<td><div id="detailsDiv"></td>';
	electionYearSelect += '</table>';
	selectDiv.innerHTML = electionYearSelect; 
	getConstituencyResults(myResults[0].name);
	}


	function getConstituencyResults(elecYear){
	document.getElementById("parliamentElectionResultsDivNew").style.display ="none";
	var imgElmt = document.getElementById('AjaxImgDiv');
	var parliamentDiv = document.getElementById('parliamentElectionResultsDiv');
	parliamentDiv.style.display = "none";

	if(imgElmt.style.display == "none")
	{
          imgElmt.style.display = "block";
	}
	var jsObj = {
			constituencyId:${constituencyId},
			electionYear:elecYear,
			chartHeight: 350,
			chartWidth: 500,
			others:true,
			task:"getConstituencyResultsBySubLocations"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/assemblyWiseParliamentResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}



	function buildConstituencyElectionResultsDataTableWithCensus(myResults,value)
	{ 
	var constType = '${constituencyDetails.constituencyType}';
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var selectedIndex = myResults.censusVO[0].censusSelectedIndex;
	
	var str = '';
	str += '<div id="elecResDiv" style="width=250px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';

	for(var i in myResults.constituencyOrMandalWiseElectionVO)
	{
		str += '<tr>';
			
		if(constType == 'Assembly')
		{	
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else if(constType == 'Parliament')
			str += '<td><a href="constituencyPageAction.action?constituencyId='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		
		if(value == 'number')
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSC+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationST+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literates+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiterates+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeople+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeople+'</td>';

		}
		else
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSCPercentage+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationSTPercentage+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literatesPercentage+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiteratesPercentage+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeoplePercentage+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeoplePercentage+'</td>';
		}

		for(var j in myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs)
		{
			if(value == 'number')
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
			str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 
	 var cenObj1 = {
			key:myResults.censusVO[0].censusFields[0],
			label:myResults.censusVO[0].censusFields[0],
			sortable:true
	    }
	
	 var cenObj2 = {
			 key:myResults.censusVO[0].censusFields[0],
			 parser:"number"
		}
		myColumnDefs.push(cenObj1);
		myFields.push(cenObj2);
	 for(var i in myResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					label:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							       };

	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource);
	} 

	function buiidCensusRadioSelect()
	{
	var cenRadioselectEle = document.getElementById("labelRadioDiv");
	
	var cenRadiovar = '';

	cenRadiovar += '<table style="margin-bottom: -14px;"><tr>';
	cenRadiovar += '<td id="labelRadio"><b>Select The Format You Want :</b></td>';
	cenRadiovar += '<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTableWithCensus(censusResult,this.value)">By Votes </td>';
	cenRadiovar += '<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElectionResultsDataTableWithCensus(censusResult,this.value)"/>By Percentage </td>';
	cenRadiovar += '</tr></table>';

	cenRadioselectEle.innerHTML = cenRadiovar;
	}


	function getCensusDetailsForAConstituency(constituencyId,index,text)
	{
	if(index == 0)
			return;

	var imgElmt = document.getElementById('censusAjaxImgDiv');
	
	if(imgElmt.style.display == "none")
	{
          imgElmt.style.display = "block";
	}
	
	var electionSelectEle = document.getElementById("electionYearSelect");
	var electionYear      = electionSelectEle.options[electionSelectEle.selectedIndex].text;
	var constituencyType  = '${constituencyDetails.constituencyType}';

		var jsObj = {
			constituencyId  : constituencyId,
			censusYear      : 2001,
			delimitationYear: 2009,
			seletedIndex    : index,
			seletedText     : text,
			electionYear    : electionYear,
			constituencyType: constituencyType,
			others          : false,
			task:"getCensusDetailsForAConstituency"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getCensusDetailsForAConstituency.action?"+rparam;
	callAjax(jsObj, url);
	}


	function buildCensusChartForAConstituency(myResults)
	{
	if(myResults.censusVO == null || myResults.censusVO.length == 0)
	{
		showCensusError();
		hideCensusAjaxImage();
		return;
	}

	else if(myResults.censusVO.length != myResults.constituencyOrMandalWiseElectionVO.length)
	{
		showCensusError();
		hideCensusAjaxImage();
		return;
	}
	
	else if(myResults.censusVO.length > 0)
	{
    var chartColumns = myResults.candidateNamePartyAndStatus;
	var chartRows    = myResults.constituencyOrMandalWiseElectionVO;
	var census       = myResults.censusVO;
    var partiesArray = new Array();
	var selectedIndex= census[0].censusSelectedIndex;
	
	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

	 var colData = census[0].censusFields[0];
	 data.addColumn('number',colData);
	 partiesArray.push(colData);

	 //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }

      //for chart rows
	  for(var j in chartRows)
	  {
		  var array = new Array();
		  array.push(chartRows[j].locationName);
		
		if(selectedIndex == 1)
		var censusPercentage = census[j].populationSCPercent;
		else if(selectedIndex == 2)
		var censusPercentage = census[j].populationSTPercent;
		else if(selectedIndex == 3)
		var censusPercentage = census[j].literatesPercent;
		else if(selectedIndex == 4)
		var censusPercentage = census[j].illiteratesPercent;
		else if(selectedIndex == 5)
		var censusPercentage = census[j].workingPeoplePercent;
		else if(selectedIndex == 6)
		var censusPercentage = census[j].nonWorkingPeoplePercent;
	  
	     array.push(censusPercentage);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }

		  data.addRow(array);
	  }
    var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");

	if('${constituencyDetails.constituencyType}' == 'Assembly')
	{
		ctitle = "Mandal Wise Election Results V/S Census Chart For '${constituencyDetails.constituencyName}'";
	}
	if('${constituencyDetails.constituencyType}' == 'Parliament')
	{
		ctitle = "Assembly Constituency Wise Election Results V/S Census Chart For '${constituencyDetails.constituencyName}'";
	}
	
	var staticColors = setStaticColorsForInteractiveChartsForCensusAndPartiesArray(partiesArray);
    
	 if(chartRows.length == 1)
	{
		if(staticColors != null && staticColors.length > 0)
		{
		  new google.visualization.ColumnChart(chartResultDiv).
		      draw(data, {width:800, height: 500,title:ctitle,colors:staticColors,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		 new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 800, height: 500,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	}
	 if(chartRows.length > 1)
	{
		 if(staticColors != null && staticColors.length > 0)
		{
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width: 800, height: 500,title:ctitle,colors:staticColors,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		   new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 800, height: 500,title:ctitle,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	 }
   hideCensusAjaxImage();
   removeCensusNotAvailableErrorMessage();
  }

	}



	function getInteractiveChart(chartResultDiv,constituencyResults,partiesList,constiType,constiName,electionYear)
	{
	var chartColumns = partiesList;
	var chartRows = constituencyResults;
    var partiesArray = new Array();

	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

     //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }

      //for chart rows
	  for(var j in constituencyResults)
	  {
		  var array = new Array();
		  array.push(constituencyResults[j].locationName);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

      var ctitle='';
	  if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
	  {
	    ctitle = 'Assembly Constituency Wise Election Results Chart For '+constiName+' '+constiType+' Constituency In '+electionYear;
	  }else
	  {
         ctitle = 'Mandal Wise Election Results Chart For '+constiName+' '+constiType+' Constituency In '+electionYear;
	  }
       
      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	  
	  if(staticColors != null && staticColors.length > 0)
	  {
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width:800, height: 500,title:ctitle,colors:staticColors,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
	  }else
	  {
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width:800, height: 500,pointSize: 4,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
	  }
	}



function getVotingTrendzForElectionYears()
{
	var jsObj=	{					
					task:'getVotingTrendzForElectionYears'
				}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
}


	function getVotingTrendzForyear()
	{

	var elements = document.getElementsByTagName('input'); 
	var electionType;
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="electionType" && elements[i].checked==true)
			electionType = elements[i].value;
	}
	if(!electionType)
		return;
	
	var selectElmt = document.getElementById(electionType+"_YearSelect");
	if(!selectElmt)
		return;
	selectElmt.disabled = false;

	var year = selectElmt.options[selectElmt.selectedIndex].text;
	if(year == "Assembly" || year == "Parliament")
		return;

	var value = selectElmt.options[selectElmt.selectedIndex].value;	
	var electionId = value.substring(0,(value.indexOf('_')));
	var electionTypeId = value.substring((value.indexOf('_')+1),value.length);

	

	var jsObj = {
					constituencyId:constituencyPageMainObj.electionTrendzReportVO.constituencyId,
					electionId:electionId,
					electionTypeId:electionTypeId,
					electionYear:year,
					task:'getVotingTrendzForElectionYears'
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
	
	}

function hideAndShowMuncipality()
{
muncipalityPresent = true;
document.getElementById('muncipalityDiv').innerHTML='';
					
document.getElementById('MuncipalElectionSelectDiv').innerHTML='';

showUrbanDivIfPresent();
}

function hideAndShowCorporation()
{
corporationPresent = true;

	
var showDiv = document.getElementById('corporationDiv');
		
document.getElementById('corporationTabDiv').innerHTML='';
showDiv.innerHTML=str;
showDiv.style.display = "none";
showUrbanDivIfPresent();

}
	function showCorporationInfo(myResults){
	var str='';
	var HeadElmt = document.getElementById('coroporationData_main');
	
	if(myResults.muncipalityVO == null){
		var showDiv = document.getElementById('corporationDiv');
		var corporationHeadingDIVEle = document.getElementById('corporationHeadingDIV');
		//str +='<font style="clear:right;margin-left:71px;text-decoration: none;color:#5CB275;cursor:pointer;">No Data available .......</font>';
		document.getElementById('corporationTabDiv').innerHTML='';
		showDiv.innerHTML=str;
		showDiv.style.display = "none";
		corporationPresent = true;
		showUrbanDivIfPresent();
	}

	buildCorpOrMunicipTable(HeadElmt, myResults, "Corporation","showMoreCorporationResultsDiv");
	}


	function showGreaterInfo(myResults){
	var moreElmt = document.getElementById('showMoreGreaterResultsDiv');	
	var elecType= "Greater Municipal Corp";
	var appendingStr = '';
	for(var i in myResults.localElectionsInfo){
		appendingStr += '<a href="javascript:{}" title="Click here to View ${constituencyDetails.constituencyName} &nbsp; ${constituencyDetails.constituencyType} Constituency Greater Municipality Corporation Candidates Election Results" onclick="redirectMuncipalityCandidateLink(\''+elecType+'\','+ myResults.localElectionsInfo[i].electionTypeId+','+ myResults.localElectionsInfo[i].id+','+myResults.localElectionsInfo[i].electionYear+',\''+myResults.localElectionsInfo[i].name+'\')" style="text-decoration:none;" class="candidateDetailsStyle" >Show Results</a>';
	}	
	moreElmt.innerHTML = appendingStr;
	
	var HeadElmt = document.getElementById('GHMCData_main');
	var str='';
	if(myResults.localElectionsInfo == null){
		var showDiv = document.getElementById('greaterDiv');
		//str +='<font style="clear:right;margin-left:71px;text-decoration: none;color:#5CB275;cursor:pointer;">No Data available .......</font>';
		document.getElementById('greterMunicipalDiv').style.display = "none";
		greaterPresent = true;
		showUrbanDivIfPresent();
		showDiv.innerHTML=str;
		showDiv.style.display = "none";
	}
	var str = '';
	for(var i in myResults.localElectionsInfo){
		str += '<div style="color:#5CB275 !important;text-decoration: none;display:block;clear:left;padding:10px;">';
		str += '<a style="color:#5CB275;font-weight:bold;" href=\"localBodyElectionAction.action?stateId='+myResults.localElectionsInfo[i].stateId+'&localBodyElectionTypeId='+myResults.localElectionsInfo[i].electionTypeId+'&localBodyId='+myResults.localElectionsInfo[i].id+'\">Detailed view of '+myResults.localElectionsInfo[i].name+' Election Results In '+myResults.localElectionsInfo[i].electionYear+'</a></div>';
		str += '<div><img src=\"charts\\'+myResults.localElectionsInfo[i].chartName+'\"></div>';
		str += '<div id=\"greaterTableDiv_'+i+'\"></div>';
	}
	HeadElmt.innerHTML = str;
	for(var i in myResults.localElectionsInfo){
		var resultsDataSource = new YAHOO.util.DataSource(myResults.localElectionsInfo[i].wardwiseResultsForParty);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "partyName"
			}, {
				key : "candidateName"
			}, {
				key : "votesEarned"
			}, {
				key : "votesPercentage"
			}, {
				key : "rank"
			}, {
				key : "totalVotes"
			}]
		};

		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Ward",
			sortable : true
		}, {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "candidateName",
			label : "Candidate",
			sortable : true
		}, {
			key : "votesEarned",
			label : "Votes Gained",
			sortable : true
		}, {
			key : "votesPercentage",
			label : "Votes %",
			sortable : true
		}, {
			key : "rank",
			label : "Rank",
			sortable : true
		}, {
			key : "totalVotes",
			label : "Total Voters",
			sortable : true
		}  ];		
		if(myResults.localElectionsInfo[i].wardwiseResultsForParty.length >10)
		{
			var recordsPerPage = {
		    paginator : new YAHOO.widget.Paginator({
		        rowsPerPage: 10 
		    })
			};
		}	
		myDataTableForMptcParty = new YAHOO.widget.DataTable("greaterTableDiv_"+i,resultsColumnDefs, resultsDataSource,recordsPerPage);
	}
	
	}
function buildPartyAdvertisiment()
{
  var str='';
		    str+='<div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>';
            str+='<div class="clear"></div>';
            str+='<p></p>';
            str+='<span class="gray">Are you a</span>'; 
			str+='<strong>Politician';
			str+='<span class="orange">/</span>Political Party';
			str+='<span class="orange">/</span>Media...</strong> Want to know how you can be benefited with ';
			str+='<span class="orange">PartyAnalyst</span> ?';
            str+='<div class="clear"></div>';
            str+='<div class="img-clickhere-button">';
			str+='<a href="viewFeaturesAction.action"></a></div>';
          str+='</div>';
      document.getElementById("partyAnysAdd").innerHTML=str;
  
}
function addCss(id)
{
   $("#"+id).removeClass("electionInformationClass");
    $("#"+id).addClass("electionInformationClassNew");

}
function removeCss(id)
{

 $("#"+id).removeClass("electionInformationClassNew");
 $("#"+id).addClass("electionInformationClass");
}

function buildCensusChartForAConstituencyParl(myResults){
	if(myResults.censusVO == null || myResults.censusVO.length == 0)
	{
		
		return;
	}

	else if(myResults.censusVO.length != myResults.constituencyOrMandalWiseElectionVO.length)
	{
		
		return;
	}
	
	else if(myResults.censusVO.length > 0)
	{
    var chartColumns = myResults.candidateNamePartyAndStatus;
	var chartRows    = myResults.constituencyOrMandalWiseElectionVO;
	var census       = myResults.censusVO;
    var partiesArray = new Array();
	var selectedIndex= census[0].censusSelectedIndex;
	
	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

	 var colData = census[0].censusFields[0];
	 data.addColumn('number',colData);
	 partiesArray.push(colData);

	 //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }
      //for chart rows
	  for(var j in chartRows)
	  {
		  var array = new Array();
		  array.push(chartRows[j].locationName);
		
		if(selectedIndex == 1)
		var censusPercentage = census[j].populationSCPercent;
		else if(selectedIndex == 2)
		var censusPercentage = census[j].populationSTPercent;
		else if(selectedIndex == 3)
		var censusPercentage = census[j].literatesPercent;
		else if(selectedIndex == 4)
		var censusPercentage = census[j].illiteratesPercent;
		else if(selectedIndex == 5)
		var censusPercentage = census[j].workingPeoplePercent;
		else if(selectedIndex == 6)
		var censusPercentage = census[j].nonWorkingPeoplePercent;
	  
	     array.push(censusPercentage);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = parseFloat(chartRows[j].partyElectionResultVOs[k].votesPercentage);
              array.push(percentage);
		  }

		  data.addRow(array);
	  }
    var chartResultDiv = document.getElementById("resultGraphDivForParl");

	
		ctitle = "Mandal Wise Election Results V/S Census Chart ";
	
	
	
	var staticColors = setStaticColorsForInteractiveChartsForCensusAndPartiesArray(partiesArray);
    
	 if(chartRows.length == 1)
	{
		if(staticColors != null && staticColors.length > 0)
		{
		  new google.visualization.ColumnChart(chartResultDiv).
		      draw(data, {width:800, height: 500,title:ctitle,colors:staticColors,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		 new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 800, height: 500,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	}
	 if(chartRows.length > 1)
	{
		 if(staticColors != null && staticColors.length > 0)
		{
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width: 800, height: 500,title:ctitle,colors:staticColors,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		   new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 800, height: 500,title:ctitle,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	 }
  }

  }
  function buiidCensusRadioSelectForParl(){
    var cenRadioselectEle = document.getElementById("newCensusSelForConParl");
	
	var cenRadiovar = '';

	cenRadiovar += '<table style="margin-bottom: -14px;"><tr>';
	cenRadiovar += '<td id="labelRadio"><b>Select The Format You Want :</b></td>';
	cenRadiovar += '<td><input type="radio" name="dispalyforparl" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTableWithCensusParl(censusResultForParl,this.value)">By Votes </td>';
	cenRadiovar += '<td><input type="radio" name="dispalyforparl" value="percentage" onclick="buildConstituencyElectionResultsDataTableWithCensusParl(censusResultForParl,this.value)"/>By Percentage </td>';
	cenRadiovar += '</tr></table>';

	cenRadioselectEle.innerHTML = cenRadiovar;
  }
  function buildConstituencyElectionResultsDataTableWithCensusParl(myResults,value){
	var resultDiv = document.getElementById("resultDataTableDivForParl");	
	var selectedIndex = myResults.censusVO[0].censusSelectedIndex;
	
	var str = '';
	str += '<div id="elecResDivForPal" style="width=250px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTableForParl">';

	for(var i in myResults.constituencyOrMandalWiseElectionVO)
	{
		str += '<tr>';
			
		
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		
		
		if(value == 'number')
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSC+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationST+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literates+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiterates+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeople+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeople+'</td>';

		}
		else
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSCPercentage+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationSTPercentage+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literatesPercentage+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiteratesPercentage+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeoplePercentage+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeoplePercentage+'</td>';
		}

		for(var j in myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs)
		{
			if(value == 'number')
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
			str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 
	 
	 var cenObj1 = {
			key:myResults.censusVO[0].censusFields[0],
			label:myResults.censusVO[0].censusFields[0],
			sortable:true
	    }
	
	 var cenObj2 = {
			 key:myResults.censusVO[0].censusFields[0],
			 parser:"number"
		}
		myColumnDefs.push(cenObj1);
		myFields.push(cenObj2);
	 for(var i in myResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					label:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("elecResTableForParl")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							       };

	 var villageDataTable = new YAHOO.widget.DataTable("elecResDivForPal",myColumnDefs, myDataSource);
  }
  
//getCoroporationResults();
getCoroporationResultsForAssemblies();
showRuralDivIfPresent();
showUrbanDivIfPresent();
//buildPartyAdvertisiment();	
getAllZptcYears();	
getAllMptcYears();
getGreaterResults();
buildConstituencyInfo();
initializeConstituencyPage();
detailedElectionResult();

 </script>

 <script>
function savefavouriteLink(){

	//var pageTitle = '${constituencyDetails.constituencyName}'+'${constituencyDetails.constituencyType}'+' District News,Constituencies,MLA, MP,Details,  Elections Results,Parties Performance,MPTC, ZPTC, Municipality, Corporation Election Results';
	var pageTitle = '${constituencyDetails.constituencyName}'+ ' ${constituencyDetails.constituencyType}'+' Constituency Page - News, Details, Mandals, Parties Performance, Voting Trendz, Election Results,MLA, MP,MPTC, ZPTC Election Results';

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
									
									if(jObj.task == "saveFavouriteLink"){
										$('.favouritelink').hide();
										alert("Link added successfully");
									}
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
function hideFavouriteLink(){
$('.favouritelink').hide();
}
</script>
 
  </body>
</html>