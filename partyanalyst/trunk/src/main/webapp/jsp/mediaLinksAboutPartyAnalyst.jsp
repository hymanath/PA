<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Polls</title>
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
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<style type="text/css">
	
	
	#headingDiv
	{
      font-family:verdana;
	  font-weight:bold;
	  font-size:14px;
	}

	#detailsDiv
	{
	  font-family:verdana;
	  font-weight:bold;
	  font-size:12px;
	}

	#channelDiv
	{
      font-family:verdana;
	  font-weight:bold;
	  font-size:13px;
	}

	body
	{
		background-color:#EAEDEF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}
	
</style>
<script type="text/javascript">
	

</script>
</head>
<body>
		<div id="mainHeadingDiv">
			
			<center>
				<table>
				   <tr>
				       <td width="30%">
					     <img src="images/icons/homePage_new/logo.gif"></img>
					   </td>
					   <td width="70%">
					     <div id="headingDiv">AMAZING reviews from National & International Media</div>
					   </td>
				   </tr>
				</table>
			</center>
		</div>
		
		<jsp:include page="../jsp/partyAnalystLaunchVideos.jsp"/>
	<br><br><br><br><br><br>
		
		<center style="margin-top:30px;">
		
		   <table>
		      <tr>
			     <td width="30%">
				    <div id="channelDiv">Asian Correspondent</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://asiancorrespondent.com/52282/building-a-business-around-the-great-indian-democracy/" target="_blank">Building a business around the Great Indian Democracy</a>
					</div>
				 </td>
			  </tr>
			   <tr>
			     <td width="30%">
				    <div id="channelDiv">The Hindu</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.hindu.com/2011/04/11/stories/2011041153190400.htm" target="_blank">www.partyanalyst.com launched </a>
					</div>
				 </td>
			  </tr>
			  
			 
			   <tr>
			     <td width="30%">
				    <div id="channelDiv">www.sify.com</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.sify.com/finance/it-grids-to-raise-rs-5-crore-vc-fund-soon-news-equity-leljRghajed.html
                       " target="_blank">IT Grids to raise Rs 5 crore VC fund soon</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">The Hindu Businessline</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.thehindubusinessline.com/todays-paper/tp-others/tp-states/article1685637.ece"
                        target="_blank">Portal for election analysis, information launched</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">Business Standard</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.business-standard.com/india/news/it-grids-to-raise-rs5-crore-vc-fund-soon/431702/"
                        target="_blank">IT Grids to raise Rs 5 crore VC fund soon</a>
					</div>
				 </td>
			  </tr>
			   <tr>
			     <td width="30%">
				    <div id="channelDiv">Life Style Blogindia</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.lifestyleblogindia.com/2011/04/election-analysis-political-management-software/"
                        target="_blank">Election Analysis Software for Politicians, Media & Voters Launched</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">DNAIndia</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.dnaindia.com/india/report_website-offers-election-analysis_1530393"
                        target="_blank">Website offers election analysis</a>
					</div>
				 </td>
			  </tr>
				  <tr>
			     <td width="30%">
				    <div id="channelDiv">Web News Wire</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.webnewswire.com/node/697536"
                        target="_blank">Election Analysis Software for Politicians, Media & Voters Launched </a>
					</div>
				 </td>
			  </tr>
			 
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">siliconindia News</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.siliconindia.com/shownews/Software_IT_girds_to_raise_VC_funding_-nid-81840.html"  target="_blank">Software IT Grids to raise VC funding </a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">AllVoices</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.allvoices.com/contributed-news/8740706-party-analyst-a-unique-site-on-parties-politics-and-elections-launched"
                        target="_blank">Party Analyst, a unique site on parties, politics and elections launched</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">AFaqs</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.afaqs.com/news/company_briefs/?id=48696_Election+analysis++political+management+software+for+politicians+and+political+parties"
                        target="_blank">Election analysis software for politicians and political parties</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">DealCurry</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.dealcurry.com/20110411-Software-Firm-IT-Grids-To-Raise-VC-Funding.htm"
                        target="_blank">Party Analyst, a unique site on parties, politics and elections launched</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">VCCircle</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://www.vccircle.com/500/news/news-roundup-indias-sun-pharma-merck-in-generics-joint-venture"
                        target="_blank">IT Grids To Raise Rs 5Cr PE Funding</a>
					</div>
				 </td>
			  </tr>
			  <tr>
			     <td width="30%">
				    <div id="channelDiv">The Wall Street Journal</div>
				 </td>
				 <td width="70%">
	                <div id="detailsDiv">
					   <a href="http://online.wsj.com/article/SB10001424052748704641604576255692445828326.html  ( WSJ Wow!!!  )"
                        target="_blank">Website offers election analysis</a>
					</div>
				 </td>
			  </tr>
			 </table>
		</center>

</body>
</html>