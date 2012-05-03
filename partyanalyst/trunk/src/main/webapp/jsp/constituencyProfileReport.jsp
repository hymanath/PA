<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Profile Report </title>
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
<h1 class="gradlightblack">Constituency Profile Report  <div class="Sample-Report-top">
<a class="inline" href="#fancydivbox">View Sample</a>
</div></h1>
<span class="titlespan">Know + Analyze + Act</span>
<p class="">Get the complete picture of your constituency to strategize the political picture up to village wise.
</p>
<div class="">
<h2 class="oval-thought1" style="width:280px;border-radius:50%;margin-bottom:10px;">Why you need this Report?
</h2>
<p class="gradcream" style="width:70%;padding:15px;margin-left:55px;text-shadow:0px 0px 5px #fff;">
<b>
In this competitive Politics, how you will know the past election results in a broad segments where you can visualize and educate yourself with ease.</b>
</p>

</div>
<!--
<div class="why-box-survey">
<h2 class="oval-thought-survey" style="height:191px;font:24px 'Oswald',Arial;" >Are you Aspirant candidate for 2014 elections in Andhra Pradesh?
<span class="survey-span" style="margin-top:37px;">.....................</span>
</h2>
</div>-->

<div class="Survey-struct-box">
<h1>Synopsis:</h1>
<ul class="Report-feature-box">
		<li class="gradBlueclass leftclass">
			<span>Demographic Wise - Party's Performances </span>
            <span>Polling Percentages vs Party's Performances</span>
            <span>Booth Wise -Party Weak and Strong Areas</span>
            <span>Cross Voting Report</span>
			
		</li>

		<li>
			<h3>Constituency</h3>
			
		</li>
		
		<li class="gradBlueclass rightclass">
			<span>Mandal Wise Election Results</span>
            <span>Comparison of Different Mandal's between 2004 & 2009</span>
            <span>Panchayat Wise Voting Percentage  </span>
            <span>Panchayat Wise Different Party's Performance</span>
			
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
Our qualified team will be happy to answer all your questions about Constituency Profile Report and Other Products.
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
<!--<div class="service-box">
<a href="#"><span>Constituency Profile Report</span></a>
<img src="images/site/imgpartyperf.jpg"/>
Get Constituency Reports
</div>-->
<div class="service-box">
<a href="#"><span>Party / Candidate Analyzer</span></a>
<img src="images/new_homepage/sp-pa.jpg"/>
Analyze your constituency,  track your progress and pending facilities.
</div>
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
	<div id="fancydivbox" style="height:450px;overflow-x:hidden;overflow-y:hidden;">
	<embed src="images/Constituency_Profile_Report.pdf" width="850px" height="450px"
	><br/>
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