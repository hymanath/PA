<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters Pulse</title>
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
<h1 class="gradlightblack">Voters Pulse <div class="Sample-Report-top">
<a class="inline" href="#fancydivbox">View Sample</a>
</div></h1>
<span class="titlespan">Making Research Matters</span>
<p class="">To understand the present voters trend vs Parties wise, Constituency, Geographically and Demographically.
</p>

<div class="why-box-survey">
<h2 class="oval-thought-survey" style="height:191px;font:24px 'Oswald',Arial;" >Why Voters Pulse?
<span class="survey-span" style="margin-top:37px;">Voters pulse provides scientific Political surveys in accurate and cost effective way.</span>
</h2>
</div>

<div class="Survey-struct-box">
<h1>Voters Pulse Survey Synopsis:</h1>
<ul class="survey-feature-box">
		<li>
			<h2>Problems and Developments</h2>
			<ul>
				<li>Identifying the Problems, which requires immediate attention to get into the people?</li>
				<li>On what developments the localities are happy</li>			
			</ul>
		</li>

		<li>
			<h2>Demographical& Geographical</h2>
			<ul>
				<li>Caste wise Analysis</li>
				<li>Literate/Illiterate Wise Analysis</li>
				<li>Analysis from hamlets</li>
			</ul>
		</li>
		
		<li>
			<h2>Parties Fame / Performances</h2>
			<ul>
				<li>How many people know about the Party?</li>
				<li>Is the Party Performance good?</li>			
			</ul>
		</li>
		
		<li>
			<h2>Leaders Fame / Performances</h2>
			<ul>
				<li>How many people know about the Leader?</li>
				<li>Is the Leader Performance good?</li>
			</ul>
		</li>
		
		<li>
			<h2>Party / Leader Influencing Groups</h2>
			<ul>
				<li>Who are influenced people?</li>
				<li>Finding out the right people's group to boost the political Arena.</li>			
			</ul>
		</li>
		
		<li>
			<h2>Strengthen Groups and Opposition Parties</h2>
			<ul>
				<li>Identifying the Strengthen groups.</li>
				<li>Finding out the opposition Parties.</li>			
			</ul>
		</li>		
</ul>

</div>
<div class="helpbox-red" style="top:0px;">
<div class="Sample-Report">
<a class="inline" href="#fancydivbox">View Sample</a>
</div>
 
<div class="Query-box">
<h3>Any questions?</h3>
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
<a href="partyCandidateAnalyzer.action"><span>Political Management Tool</span></a>
<img src="images/new_homepage/sp-pa.jpg"/>
Analyze your constituency, track your progress and pending facilities.
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
	<img src="images/voterspulse.png" /><br/>
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