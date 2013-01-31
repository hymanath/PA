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

<link type="text/css" rel="stylesheet" href="styles/newsstyle.css">
<link type="text/css" rel="stylesheet" href="styles/bubble.css">
<!-- start of vedio files -->

<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script>
<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  
<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>

<!-- End of vedio files-->
</head>
<body>
<style>
  .features-box{
    width: 274px;
  }  
  .helpbox-red{
   width: 680px;
  }
  .image_styles{
    border: 1px solid #CCCCCC;
	width: 199px;
	margin-left:2px
  }
 
</style>
<div class="mainnewsdiv">
<div class="Promo-box-left gradfill">
<h1 class="gradlightblack">Election Analysis And Management Tool <div class="Sample-Report-top">

</div></h1>
<span class="titlespan">KNOW - ANALYZE - ACT</span>
<p>The first-of-its-kind Election Analysis Platform, for Politicians, Political Parties and Media.
</p>
<p>Party Analyst provides you with different management tools, to manage your Cadre, Constituency and the Problems<br /> faced by the public. It provides you with exclusive election analysis tools equipped with different data sets, which help<br /> you to understand the parties performance pattern.
</p>

<ul class="contentmenutab">
<li><a href="#" id="politicanshow" onclick="showDiv('politican')" class="selectedmenu"> Politician </a></li>
<li><a href="#" id="politicalpartyshow" onclick="showDiv('politicalparty')"> Political Parties </a></li>
<li><a href="#" id="mediashow" onclick="showDiv('media')"> Media </a></li>
</ul>
<div id="politicanDiv">
<div class="ManageBG">
<ul class="contentsubmenutab">
<li><a href="#" id="LinkCanElecAna" class="selectedsubmenu"> Election Analysis </a></li>
<li><a href="#" id="LinkCanCadMgt"> Cadre Management </a></li>
<li><a href="#" id="LinkCanConMgt"> Constituency Management </a></li>
<li><a href="#" id="LinkCanProMgt"> Profile Management </a></li>
</ul>
	<div class="contentProd" id="CanElecAna">
			<div class="Block-Content-full">
				<h3>You Can Analyze</h3>
				<ul class="survey-feature-box-product"><li>Voting Patterns based on Geographics, Demographics and Parties.</li>
				<li>Polling Percentage Vs Party Performances.</li>
				<li>Cross Voting Patterns.</li>
				<li>All local body elections information .</li>
				<li>The previous Election results Vs present Election survey results to know the variation in voting pattern.</li>
				</ul>
			</div>	
			<div class="Block-Content-full">
				<h3>What you can get?</h3>				
				<ul class="survey-feature-box-product"><li>All elections information, geographical information, demographics, delimitation information is integrated together which you can get any information in a single click (Ex: How many booths in each Panchayat, what is party performance in any panchayat or booth or Mandal etc).</li>
				</ul>
				
				<ul class="survey-feature-box-product"><li>Identifying the areas where you are weak where you are strong and where you need to concentrate more.</li>
				</ul>
				
				<ul class="survey-feature-box-product"><li>Party voting percentages versus literates or illiterates percentages etc</li>
				</ul>
				
				<ul class="survey-feature-box-product"><li>Get all independent candidates information that got more votes in municipal or MPTC or ZPTC elections.</li>
				</ul>
				<ul class="survey-feature-box-product"><li>Get the complete picture of geographical relationships (Panchayats for each Mandal / Tehsil, Hamlets for each Panchayats etc).</li>
				</ul>
				<ul class="survey-feature-box-product"><li>Complete cross voting information up to village level.</li>
				</ul>
			</div>	
	</div>
	
	<div class="contentProd" id="CanCadMgt">
			<div class="Block-Content-full">
				<h3>What is it? </h3>
				Cadre Management allows you to manage your cadre effectively and improve your communication channels with them. 
			</div>	
			<div class="Block-Content-full">
				<h3>How?</h3>
				<ul class="survey-feature-box-product">
				<li>You can notify the entire cadre at once, about your visit or achievements or Party updates through SMS or email. </li>
				<li>You can assign some of the local problems to the cadre.</li>
				<li>You can plan cadre booth wise, for election planning.</li>
				<li>You can analyze the cadre strength in different areas.</li>

				</ul>
			</div>	
	</div>
	
	<div class="contentProd" id="CanConMgt">
			<div class="Block-Content-full">
				<h3>What it Involve?</h3><br />
				<b>Constituency Management feature helps you.</b>  
					<ul class="survey-feature-box-product">
				<li>The political changes happening every day and their impact on your constituency.</li>
				<li>Maintains the mandal-level leaders details for your constituency.</li>
				<li>Also maintains voter's details from hamlet level, segregated based on caste, literacy and other criteria.</li>
				</ul>
				<b>Problem Management feature assists you in</b> 
				<ul class="survey-feature-box-product"><li>Track Problems in your constituency.</li>
				<li>Maintain the problems based on location</li>
				<li>Quick way to know the Status of the problem and where (department) it is residing.</li>
				<li>Thus, it helps you in conveying these problems to the government officials, effectively. You can send updates to the public through SMS or Email.</li>
				<li>Solved problems you can publish in your profile.</li>
				</ul>
				
			    <b>Maintaining Influential People/Groups Information</b>
				<ul class="survey-feature-box-product"><li>Maintaining influential people groups information each village wise.</li>
				 <li>Tools to communicate your updates. </li>
				 <li>This increases repo in these influential people and groups.</li>
				</ul>
				
			</div>	
	</div>
	
	<div class="contentProd" id="CanProMgt">
			<div class="Block-Content-full">
				<h3>What it Provides?</h3><br />
				<b>Profile Management features</b>  
					<ul class="survey-feature-box-product">
				<li>It enables you to share your views and publish your profile to everyone.</li>
				<li>You can upload news articles from different news magazines to your profile.</li>
				<li>You can maintain photo and video galleries of your activities and events in your profile.</li>
				</ul>
				
			</div>	
	</div>
	
	
</div>

</div>
<div id="politicalPartyDiv"  style="display:none;">
<div class="ManageBG">
<ul class="contentsubmenutab">
<li><a href="#" id="LinkCanElecAna1" class="selectedsubmenu"> Election Analysis </a></li>
<li><a href="#" id="LinkCanCadMgt1"> Cadre Management </a></li>
<li><a href="#" id="LinkCanConMgt1"> Constituency Management </a></li>
<li><a href="#" id="LinkCanProMgt1"> Profile Management </a></li>
</ul>
	<div class="contentProd" id="CanElecAna1">
			<div class="Block-Content-full">
				<h3>You Can Analyze</h3>
				<ul class="survey-feature-box-product"><li>The Voting Percentage Levels</li>
				<li>Demographically and Geographically</li>
				<li>From Hamlet Level to State Level and lot more.</li>
				</ul>
			</div>	
			<div class="Block-Content-full">
				<h3>What you can get?</h3>
				Clear Picture About<br />
				<b>Party Strengths/Weakness</b>
				<ul class="survey-feature-box-product"><li>Used to analyze the areas where you are weak where you are strong and where you need to concentrate more</li>
				</ul>
				<b>Party Performance Report</b>
				<ul class="survey-feature-box-product"><li>It is used to understand how a party is performed in a particular election</li>
				</ul>
				<b>Elections Comparison Report</b>
				<ul class="survey-feature-box-product"><li>It is used to analyze party performance in a particular election by comparing to any of its participated elections</li>
				</ul>
				<b>Party Results Report</b>
				<ul class="survey-feature-box-product"><li>This report is used to get all years Assembly,Parliament,Zptc,Mptc party wise election results from state level to constituency level including Alliances</li>
				</ul>
				<b>Elections Vs Census</b>
				<ul class="survey-feature-box-product"><li>This report is used to analyze party performance census wise that means how a party is performs if SC or ST or Literates or Illiterates Population were more </li>
				</ul>
				Also ,
				<ul class="survey-feature-box-product">
				</ul>
				<ul class="survey-feature-box-product"><li>You can compare your stand and opposition party's stand in different areas. </li>
				<li>Thus, it supports you in deciding the next plan of action.</li>
				</ul>
			</div>	
	</div>
	
	<div class="contentProd" id="CanCadMgt1">
			<div class="Block-Content-full">
				<h3>What is it? </h3>
				Cadre Management allows you to add and manage your cadre effectively. 
			</div>	
			<div class="Block-Content-full">
				<h3>How?</h3>
				All the communication with your cadre is made easy with this feature.
				<ul class="survey-feature-box-product"><li>About the areas where you need to concentrate more</li>
				<li>You can notify the entire cadre at once, about your visit to specific areas and achievements, through SMS or email. </li>
				<li>You can assign some of the local problems to the cadre. </li>
				<li>You can plan cadre booth wise, for election planning. </li>
				<li>You can analyze the cadre strength in different areas.</li>

				</ul>
			</div>	
	</div>
	
	<div class="contentProd" id="CanConMgt1">
			<div class="Block-Content-full">
				<h3>What it Involve?</h3><br />
				<b>Constituency Management feature helps you.</b>  
					<ul class="survey-feature-box-product">
				<li>The political changes happening every day and their impact on your constituency.</li>
				<li>Maintains the mandal-level leaders details for your constituency.</li>
				<li>Also maintains voter's details from hamlet level, segregated based on caste, literacy and other criteria.</li>
				</ul>
				<b>Problem Management feature assists you in tracking</b> 
				<ul class="survey-feature-box-product"><li>To keep track of the problems in your constituency</li>
				<li>The problems based on location</li>
				<li>Status of the problem and department. </li>
				<li>Thus, it helps you in conveying these problems to the government officials, effectively. You can send updates to the public through SMS or Email.</li>
				</ul>
				
			    <b>Maintaining Influential People/Groups Information</b>
				<ul class="survey-feature-box-product"><li>Maintaining influential people groups information each village wise.</li>
				 <li>Tools to communicate your updates. </li>
				 <li>This increases repo in these influential people and groups.</li>
				</ul>
				
			</div>	
	</div>
	
	<div class="contentProd" id="CanProMgt1">
			<div class="Block-Content-full">
				<h3>What it Provides?</h3><br />
				<b>Profile Management features</b>  
					<ul class="survey-feature-box-product">
				<li>It enables you to share your party views and publish your party profile to everyone.</li>
				<li>You can upload news articles from different news magazines to your party profile.</li>
				<li>You can maintain photo and video galleries of your party activities and events in party profile.</li>
				</ul>
				
			</div>	
	</div>
	
	
</div>
</div>
<div id="mediaDIV" style="display:none;">
<div class="ManageBG">
<ul class="contentsubmenutab">
<li><a href="#" id="LinkCanElecAna2" class="selectedsubmenu"> Election Analysis </a></li>

</ul>
	<div class="contentProd" id="CanElecAna2">
			<div class="Block-Content-full">
				<h3>You Can Analyze</h3>
				<ul class="survey-feature-box-product"><li>Voting Patterns based on Geographics, Demographics and Parties.</li>
				</ul>
			</div>	
			<div class="Block-Content-full">
				<h3>What you can get?</h3>
				Clear Picture About<br />
				
				<ul class="survey-feature-box-product"><li>Comparison of election information against the demographic data to get a clear picture of the voting patterns of people based on literacy,caste ,work,status etc.</li>
				</ul>

				<ul class="survey-feature-box-product"><li>Easy understanding of party performance in a particular state,district,constituency or further at mandal/tehsil and village level.</li>
				</ul>
				
				<ul class="survey-feature-box-product"><li>Comparison of a political party's performance geographically in any two elections.</li>
				</ul>
				
			</div>	
	</div>
	
	
	
</div>
</div>
<!--
<div class="ManageBG">
    <h3>Cadre Management</h3>
    <p>Cadre Management feature allows you to add and manage your cadre effectively. You can notify the entire cadre at once, about your visit to specific areas and achievements, through SMS or email. You can assign some of the local problems to the cadre. You can plan cadre booth wise, for election planning. You can analyze the cadre strength in different areas. All the communication with your cadre is made easy with this feature.</p>
</div>
<div class="ManageBG">
   <h3>Constituency Management</h3> 	
   <p>Constituency Management feature helps you to keep track of the problems in your constituency, the political changes happening every day, and their impact on your constituency. Problem Management feature assists you in tracking the problems based on location, status of the problem and department. Thus, it helps you in conveying these problems to the government officials, effectively. You can send updates to the public through SMS or Email.</p>

   <p>We provide you an interface to communicate with the public and publish your activities to them. When you partner with Party Analyst, you can reach people efficiently, you will know almost everything, and anything will be possible.</p>

</div> -->
<div class="featureofferbox">
<div class="Offer-box triangle-border">
<h3>Special Offers from Party Analyst</h3>
<ul>
<li>For Parliament Constituency Subscription, get a free Ad in your Parliament Constituency page and all the Assembly Constituency pages of your Parliament Constituency + 3 Lakhs free SMS service.</li>
<li>For Assembly Constituency Subscription, get a free Ad in your Assembly Constituency page + 1Lakh free SMS service.</li>
<li><b>You can gift the Party Analyst subscription to your leader and get 15% off.</b></li>
</ul>
</div>
<div class="features-box">
<h3>Features</h3>
<ul class="listdisc">
<li>Profile Management <span>This feature enables you to share your views and publish your profile to everyone. You can upload news articles of interest to you, from different news magazines to your profile. You can maintain photo and video galleries of your activities and events in your profile. </span></li>
<li>Constituency Management <span>This feature allows you to keep track of the problems in your constituency, the political changes and their impact on your constituency. You can also maintain the details of the most influential people, create and manage groups, maintain the mandal-level leaders details from your constituency and all the voters details from hamlet level, segregated based on caste, literacy and other criteria. </span></li>
<li>Election Analysis<span>This feature provides you with many specialized election reports like Election Results Analysis Report, Elections Comparison Report, etc.. These reports help you in quickly analyzing the election results in different areas, compare them, and get a clear idea about everything. </span></li>
<li>Problem Management<span>This feature helps you to keep track of the problems faced by the people or your constituency problems. You can track these problems grouped by location, status of the problem and department. You can also send updates of the problems, to the public and other users, through SMS. This feature thus, enables you to convey the problems to the government officials, effectively. </span></li>
<li>Cadre Management <span>Using this feature, you can add and manage your cadre efficiently. You can identify the strength of your cadre, village wise, booth wise, etc. You can communicate with cadre through SMS or email. You can plan and organize events, and maintain associate groups. You can maintain important dates to plan different constituency activities. </span></li>
</ul>
</div>

<div class="helpbox-red">
<div class="Sample-Report">

</div>
 
<div class="Query-box">
<h3>Any questions?</h3>
<span>
Our qualified team will be happy to answer all your questions about Election Analysis And Management Tool and Other Products.
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
<div class="image_styles">
<!--<h3 style="border-bottom: 1px solid #CCCCCC;">Our Services</h3>-->
<div id="videogallery">
    <div>
	    <a rel="#voverlay" href='http://www.youtube.com/v/PKZpPe1pYIw?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/PKZpPe1pYIw/0.jpg' style="width: 180px; height: 175px;margin-left:10px;margin-top: 10px;"/></a>
    </div>
	
    <div>
       <a rel="#voverlay"  href="http://www.youtube.com/v/mMTRWXNVXCw?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">
        <img style="width:180px;height:170px;margin-left:10px;margin-top: 10px; " src="http://img.youtube.com/vi/mMTRWXNVXCw/0.jpg">
       </a>
    </div>
   <div>
      <a rel="#voverlay"  href="http://www.youtube.com/v/3k9vFj0Ca54?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">
       <img style="width:180px;height:175px;margin-left:10px;margin-top: 10px;margin-bottom: 10px;" src="http://img.youtube.com/vi/3k9vFj0Ca54/0.jpg">
      </a>
   </div>
  </div>
</div>
<h3 >Our Services</h3>
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

<script type='text/javascript'>
   var jQuery142 = jQuery.noConflict(true);
</script>
<script>
$(document).ready(function(){

$("#LinkCanCadMgt").click(function(){

	$(".contentsubmenutab a").removeClass("selectedsubmenu");
    $(this).addClass("selectedsubmenu");
	var str=$(this).attr("id");
	str=str.split("Link");
	var strid= "#"+str[1];
	$(".contentProd").hide();
	$(strid).show("slow");
	});
	$("#LinkCanConMgt").click(function(){

	$(".contentsubmenutab a").removeClass("selectedsubmenu");
    $(this).addClass("selectedsubmenu");
	var str=$(this).attr("id");
	str=str.split("Link");
	var strid= "#"+str[1];
	$(".contentProd").hide();
	$(strid).show("slow");
	});
	$("#LinkCanProMgt").click(function(){
	
	$(this).addClass("selectedsubmenu");
	var str=$(this).attr("id");
	str=str.split("Link");
	var strid= "#"+str[1];
	$(".contentProd").hide();
	$(strid).show("slow");
	});
	$("#LinkCanElecAna").click(function(){
	
	$(this).addClass("selectedsubmenu");
	var str=$(this).attr("id");
	str=str.split("Link");
	var strid= "#"+str[1];
	$(".contentProd").hide();
	$(strid).show("slow");
	});
	$(".contentmenutab a").click(function(){
	
	$(".contentmenutab a").removeClass("selectedmenu");
    $(this).addClass("selectedmenu");
	});
		$(".inline").fancybox();
		//$(".inline").trigger("click");
		
   	$(".service-box").click(function(){
      $(location).attr('href',$(this).find("a").attr("href"));
    });
	
	$(".contentProd").hide();
	$(".contentProd:first").show();
	
	$(".contentsubmenutab  a").click(function(){
	
	
	$(".contentsubmenutab a").removeClass("selectedsubmenu");
    $(this).addClass("selectedsubmenu");
	var str=$(this).attr("id");
	str=str.split("Link");
	var strid= "#"+str[1];
	
	$(".contentProd").hide();
	$(strid).show("slow");
	});
	
	$(".contentmenutab a").click(function(){
	
	$(".contentmenutab a").removeClass("selectedmenu");
    $(this).addClass("selectedmenu");
	});
});

function showDiv(id)
{

 if(id == "politican" )
 {

   $('#politicanDiv').show();
   $('#politicalPartyDiv').hide();
   $('#mediaDIV').hide();
   $('#politicalpartyshow').removeClass('selectedmenu');
	$('#politicanshow').addClass('selectedmenu');
	$('#mediashow').removeClass('selectedmenu');
	$('.contentProd').show();
	//$('#LinkCanElecAna').trigger('click');
 }
 else if(id == "politicalparty")
 {
  document.getElementById('politicalPartyDiv').style.display = 'block';
   $('#politicanDiv').hide();
   $('#politicalPartyDiv').show();
   $('#mediaDIV').hide();
    $('#politicalpartyshow').addClass('selectedmenu');
	$('#politicanshow').removeClass('selectedmenu');
	$('#mediashow').removeClass('selectedmenu');
	$('.contentProd').show();
	//$('#LinkCanElecAna1').trigger('click');
 }
 else if(id == "media" )
 {
 document.getElementById('mediaDIV').style.display = 'block';
   $('#politicanDiv').hide();
   $('#politicalPartyDiv').hide();
   $('#mediaDIV').show();
   $('#politicalpartyshow').removeClass('selectedmenu');
   $('#politicanshow').removeClass('selectedmenu');
   $('#mediashow').addClass('selectedmenu');
   $('.contentProd').show();
	//$('#LinkCanElecAna2').trigger('click');
 }
}
</script>
</body>
</html>