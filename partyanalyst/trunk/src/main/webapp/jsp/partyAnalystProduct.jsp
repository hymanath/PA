<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Analysis And Management Tool</title>
 <!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/calendar/assets/skins/sam/calendar.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link media="screen" href="js/fancybox/jquery.fancybox-1.3.4.css" type="text/css" rel="stylesheet">
<script src="js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
<script src="js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript">	</script>
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>

<link type="text/css" rel="stylesheet" href="styles/newsstyle.css">
<link type="text/css" rel="stylesheet" href="styles/bubble.css">
</head>
<body>
<style>
  .features-box{
    width: 274px;
  }  
  .helpbox-red{
   width: 680px;
  }
</style>
<div class="mainnewsdiv">
<div class="Promo-box-left">
<h1 class="gradlightblack">Election Analysis And Management Tool <div class="Sample-Report-top">

</div></h1>
<span class="titlespan">KNOW - ANALYZE - ACT</span>
<p class="">First of its kind of election analysis platform for Politicians, Political Parties and Media. It also provides Management Tools<br /> for Politicians and Political Parties.
</p>
<div class="why-box">
<h2 class="oval-thought1">Why Party Analyst?</h2>
<span class="RectSpan-thought">Party Analyst provides exclusive elections analysis tools with different data sets to understand the voting patterns based on demographically, geographically from state level to village level.</span>
<p class="gradcream">
It provides different tools to Politicians and Political Parties to manage Cadre, Constituencies and People Problems. It provides an interface to interact with voters and publish their activities to public. <br/><br/>

When you partner with Party Analyst, anything is possible, almost everything can be known.
</p>
</div>
<div class="featureofferbox">
<div class="Offer-box triangle-border">
<h3>Special Offers from Party Analyst</h3>
<ul>
<li>For Parliament Constituency subscription get Free Ad in your Parliament Constituency and all Assembly Constituencies of Parliament Page + 3 Lakhs SMS Free.</li>
<li>For Assembly Constituency subscription get Free Ad in the Constituency Page + 1 Lakh SMS Free.</li>
<li>You can Gift the PartyAnalyst subscription to your Leader get 15% Off.</li>
</ul>
</div>
<div class="features-box">
<h3>Features</h3>
<ul class="listdisc">
<li>Profile Management <span>Which enables to publish your profile to every one.You can attach News articles published in different news papers to your profile,which can visible to public. </span></li>
<li>Constituency Management <span>Consituency management feature allows user to keep track their constituency problems, know political changes and impact in their constituency, view influencing people, mandal level leaders and voters details by hamlet level. </span></li>
<li>Election Analysis<span>Different Tools like Elections Comparision Report,Election Results Analysis Report for Elections used to analyse elections results and compare them. </span></li>
<li>Problem Management<span>Problem management feature is mainly helps to keep track of peoples problems or constituency problems in different levels. The user can track problems by location, status,deparment.You can send updates to users through SMS.<br/> It also helps to manage with government officials and also can publish to public what we achieved</span></li>
<li>Cadre Management <span>Cadre management feature is mainly used to manage their cadre efficiently.The user can add & manage cadre, communicate with cadre through SMS or Email, plan and organize events, maintain associate groups. </span></li>
</ul>
</div>

<div class="helpbox-red">
<div class="Sample-Report">

</div>
 
<div class="Query-box">
<h3>Any questions?</h3>
<span>
Our qualified team will be happy to answer all your questions about News Monitoring Services and Other Products.
<br/><br/>
<span style="width:680px;display:table;text-align:center;color:blue;">
Call for Demo : +919676696760
<br/>(or)<br/>
Email us : <a href="mailto:info@partyanalyst.com">info@partyanalyst.com</a> </span>
 </span>
</div>
</div>
</div>
</div>

<div class="sevices-right">
<h3>Our Services</h3>
<div class="service-box">
<a href="constituencyProfileReport.action"><span>Constituency Profile Report</span></a>
<img src="images/site/imgpartyperf.jpg"/>
Get Constituency Reports
</div>
<!--<div class="service-box">
<a href="#"><span>Party / Candidate Analyzer</span></a>
<img src="images/new_homepage/sp-pa.jpg"/>
Analyze your constituency,  track your progress and pending facilities.
</div>-->
<div class="service-box">
<a href="VotersPulse.action"><span>Voters Pulse</span></a>
<img src="images/new_homepage/checklist.jpg"/>
Now, you can drive your survey's through Party Analyst...
Ask, Analyze, Improve
</div>
<div class="service-box">
<a href="newsMonitoringService.action"><span>News Monitoring Service</span></a>
<img src="images/new_homepage/NewsTracker.png"/>
As a Politician or Political Party, News Monitoring and analyzing is very important to keep upper hand in the political arena.
</div>

</div>
</div>
<div class="adboard"><span>To place your advertisement in this page please contact us at <a href="mailto:info@partyanalyst.com">info@partyanalyst.com</a></span> </div>


<div id="promodiv" style="display:none;">
	<div id="fancydivbox" style="height:450px;overflow-x:hidden;overflow-y:scroll;">
	<img src="images/NewsSample/News_Service1.PNG" /><br/>
	<img src="images/NewsSample/News_service2.PNG" />
	</div>
</div>
<script>
$(document).ready(function(){
		$(".inline").fancybox();
		//$(".inline").trigger("click");
		
   	$(".service-box").click(function(){
      $(location).attr('href',$(this).find("a").attr("href"));
    });
});


</script>
</body>
</html>