<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters Pulse - Party Analyst's Election Surveys, Political Surveys</title>
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
<div class="mainnewsdiv">
<div class="Promo-box-left gradfill">
<h1 class="gradlightblack">Voters Pulse  - An Election Survey<div class="Sample-Report-top">
<a class="inline" href="#fancydivbox">View Sample</a>
</div></h1>

<span class="titlespan">Making Research Matters</span>
<p class="">Voters Pulse is an election or political survey to understand voters pulse towards parties, candidatures and aspirations based on geographical locations and demographics.
</p>

<div class="votersbox">
<div class="votersbox-left">

<h2 style="font:20px 'Oswald',Arial;margin:10px 0px 0px 0px;color:#fff;">Why Survey is required for a Politician / Party?</h2><span class="survey-span">To strategize your activities, make progressive decisions in reaching peoples heart and gain a winning-edge.</span>


<h2 style="font:20px 'Oswald',Arial;margin:10px 0px 0px 0px;color:#fff;" >Why Voters Pulse?</h2>
<span class="survey-span">We are exclusively in to Political Arena and our team provides you with the accurate data, detailed analytical reports and methodologies to analyze your goals. </span>

<h3 style="width:125px;height:125px;border-radius:50%;background:#FFFFD6;display:inline-block;text-align:center;font:20px 'Oswald',Arial;line-height:125px;border:11px solid #ff0000;box-shadow:0px 0px 5px #333;margin:5px 35px;">Voters Pulse</h3>
</div>
<div class="votersbox-right">
<h1  style="color:#5e5e5e;">It's All About:</h1>
<ul class="survey-feature-box">
<li>Voters mind is very dynamic, understand the voter preferences in present situation and do comparative study with previous results. This study enables to understand where we moved to positive or moved to negative.</li>
<li>Instead of planning for entire constituency, strategize each village wise how far we need to improve and what are the chances we have for that.</li>
<li>If we know voter preferences based on age groups, caste, gender, location wise, we can plan in a better way to reach the groups where we need to improve.</li>
<li>Understand voter opinions on present and past governments, present representatives (M.P / M.L.A) which help to strategize for an election.</li>
<li>Understand the people problems, with this understanding plane how to solve or give promises to solve, which enables voters trust.</li>
<li>It enables to identify influence local leaders, groups in each area wise.</li>
	
</ul>

</div> </div>
<div class="helpbox-survey" style="top:0px;">
<div class="Sample-Report">
<a class="inline" href="#fancydivbox">View Sample</a>
</div>
 
<div class="Query-box">
<h2>Get Started Now  (or) Any questions?</h2>
<span>
Our qualified team will be happy to answer all your questions about Voters Pulse Services and Other Products.
<br/><br/>
<span style="width:680px;display:table;text-align:center;color:blue;">
Please call us : +919676696760
<br/>(or)<br/>
Email us : <a href="mailto:info@partyanalyst.com">info@partyanalyst.com</a> </span>
 </span>
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
<div class="service-box">
<a href="#"><span>Party / Candidate Analyzer</span></a>
<img src="images/new_homepage/sp-pa.jpg"/>
Analyze your constituency,  track your progress and pending facilities.
</div>
<!--<div class="service-box">
<a href="#"><span>Survey Meter</span></a>
<img src="images/new_homepage/checklist.jpg"/>
Now, you can drive your survey's through Party Analyst...
Ask, Analyze, Improve
</div>-->
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
	<img src="images/Voterspulse1.png" alt="survey-report" /><br/><img src="images/Voterspulse2.png" alt="survey-report"/>
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