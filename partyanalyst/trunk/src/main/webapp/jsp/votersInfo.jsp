<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link media="screen" rel="stylesheet" href="styles/colorbox/colorbox.css"/>
<link type="text/css" rel="stylesheet" href="styles/newsstyle.css">
<script src="js/colorbox/jquery.colorbox.js"></script>
<script src="js/jQuery/jquery-1.5.2.min.js"></script>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters Guide</title>
<style>


.maindiv {
  
    font-family: Arial;
    font-size: 14px;
  
    line-height: normal;
    margin-left: auto;
    margin-right: auto;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 5px;
    text-align: left;
    width: 960px;
	display: table;
}

.rightdiv{
	float: left;
    margin-left: 29px;
    padding-top: 32px;
    width: 31%;
	}
	
	.leftdiv {
    float: left;
    width: 65%;
}
h1 {
    -moz-font-feature-settings: normal;
    -moz-font-language-override: normal;
    -x-system-font: none;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    color: #FFFFFF;
    font-family: 'Oswald',Arial;
    font-size: 25px;
    font-size-adjust: none;
    font-stretch: normal;
    font-style: normal;
    font-variant: normal;
    font-weight: normal;
    line-height: normal;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
    padding-bottom: 8px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 8px;
    text-shadow: 0 1px 2px #333333;
    text-transform: uppercase;
}
#heading {
	width: 960px;
    margin-left: auto;
    margin-right: auto;
}
.ulInAddPages {padding:5px;margin-left:30px;list-style-type:disc !important;}
   .breadcrumb .ulInAddPages li{list-style-type:disc ;margin-top:5px;line-height:25px ;display:list-item;word-spacing:1px;text-align:justify;padding:5px;
}
  .row-fluid{padding:10px;}

  .nav .active a:hover{
	background:cornflowerblue;
	color:#FFFFFF;

	}
	.image_styles{
    border: 1px solid #CCCCCC;
	width: 199px;
	margin-left:2px
  }
  p{
    line-height: 25px;
    list-style-type: disc;
    margin-top: 5px;
    word-spacing: 1px;
	padding:10px;
	text-align:justify;
 }
 .subcontents{
	position:relative;
	padding:20px;
 }
 #contentBlock a{
	text-decoration:none;
	position:absolute;
	right:10px;
	bottom:5px;
 }
 .tovoters h3{
	margin-bottom:10px;
	
 }
 .tovoters{
	list-style-type: disc !important;
	margin-left: 30px;
    padding: 5px;
 }
</style>

</head>
<body>
<script>
$(document).ready(function(){
     $(".iframe").colorbox({iframe:true, width:"90%", height:"90%"});
     });  
</script>
<div id="heading">
	<h1 class="gradlightblack" style="width:940px;">Voters Guide</h1>
</div>
<div style="width:960px;margin-left:auto;margin-right:auto;margin-top:5px;margin-bottom:5px;">
<IMG src="images/Voters_Guide Img.jpg" style="width: 951px;" alt="Andhra Pradesh 2012 Bye Election"/>
</div>
<div class="maindiv">
	<div class="leftdiv" id="contentBlock" >	
		<div class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;" id="ImportanceOfVoting" >
			
			
		<!--	<div class="breadcrumb">
			<h2>Do you have Answer For This Question, If not Have a look..</h2>
				<ul>
					<li><h3>Do you have Vote ? if not How to Register for Vote ?</h3></li><br>
					<li><h3>Why Should You Vote ?</h3></li><br>
					<li><h3>What is the Problem if you have more Than one Vote ?</h3></li><br>
					<li><h3>How to transfer the Vote ?</h3></li><br>
					<li><h3>How to Cancel the Vote ?</h3></li><br>
					<li><h3>Are You NRI Do you know how to Register vote?</h3></li><br>
					<li><h3>Are you working in Army, Do you know how to vote?</h3></li><br>
					
				</ul>
				<ul class="tovoters">
					<li><h3>Don't you have Vote - Register Now!</h3></li><br>
				
					<li><h3>Do you have more than one Vote - Keep the one at Right Place & Cancel the remaining!</h3></li><br>
				
					<li><h3>Does your family have Votes in different Places - Cancel Them and Register all in one Right Place!</h3></li><br>
				
					<li><h3>Don't you have Vote in Right Place - Transfer It to the Right Place! (OR) Cancel It & Register in Right Place!</h3></li><br>
					
					<li><h3>Do you know anyone having more than One Vote or Vote at Wrong Place - Cancel Them!</h3></li><br>
					
					<p>Do this to give your support to strengthen our Democracy! Vote is your Right, have it in Right Place and Must Use it! Not using your Vote or having Votes at more than one place causes Misuse of Vote or Decrease in Polling Percentages. As a Responsible Citizen of India don't make it happen!</p>

					
				</ul>
			</div>-->
			
		
			<h2>Importance of Voting</h2>
			<div class="span12">
			  	<p id="initialdisplay1" >In Democracy, Government is "of the people, by the people and for the people". It means that we are not here to serve the government, but that the government is here to serve us - and we have the right to decide who will represent us and how we want to be represented. A Democracy is a nation whose leaders are elected by the people.<span id="displayText1" style="cursor:pointer;right: 10px;color:#4CB1CF;margin-left:2px; " onclick="toggle(1)" >Read More..</span></p>

				<div id="toggleText1" style="display:none;" >
					<p >In Democracy, Government is "of the people, by the people and for the people". It means that we are not here to serve the government, but that the government is here to serve us - and we have the right to decide who will represent us and how we want to be represented. A Democracy is a nation whose leaders are elected by the people.  Out of all the nations in this world, not all are democracies. In all the other nations, which are not democratic, people are not allowed to criticize their government. They are not allowed to choose their leaders. But, in India, we can choose our leaders.  It means that we have one of the greatest rights, any individual can have: the right to vote. Every single vote makes a difference, in a democracy.</p>
					
					<p>Voting is a right, for which, throughout history, many have fought for and sacrificed everything to achieve it. It's a right that millions of people throughout the world still fighting for. As Indians, we have the great privilege to live in a free society and voting is the right that makes us free. Remember the sacrifice of the leaders, who laid their lives for the protection and future of our country, our freedom and the life we are living now. If we throw away our right to vote, then, we are greatly dishonoring the heroes who have sacrificed their lives for us, our lives.</p>
				
					<p>Your vote is an authority given by God. Many countries don't have this opportunity. You have no right to complain about the government, if you did not try to protect your right to vote. We, as citizens of India, have not only the right to vote, but also, the responsibility to vote and help the country in getting the right leader for its development.</p>
					
					<span class="btn-info btn-small" id="displayText2" style="margin-bottom: 5px;cursor:pointer;right: 10px; position: absolute; bottom: 0px;;" onclick="toggle(1)" >Hide..</span>
				</div>
			</div>
		</div>
		
		<div class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;" id="WhyShouldIVote" >
		
			<h2>Why Should I Vote?</h2>
			<div class="span12" style="">
			  	<p style="display:block" id="initialdisplay2">
				Maybe you're asking yourself, "With all the millions of people who vote in any given election, does my vote really count?" Or perhaps you feel like you can't really make a difference, so why bother? <span id="displayText2" style="cursor:pointer;right: 10px;color:#4CB1CF;margin-left:2px; " onclick="toggle(2)" >Read More..</span>
				</p>
				<div id="toggleText2" style="display:none;" >
				<p>
					Maybe you're asking yourself, "With all the millions of people who vote in any given election, does my vote really count?" Or perhaps you feel like you can't really make a difference, so why bother? The truth is that your vote does count and you do make a difference every time you vote! Every single vote decides the leadership of this nation. Every single vote, decides the future of our country, our future.
				</p>
				<p>
				We should not sit around and hope that our candidate will win; we must take action and know that our vote and our neighbours votes too, really do count. When we go to the poll and cast our vote, we are speaking loudly and clearly, about what we think and who we think are the right persons, to lead our nation.  Every single vote counts, in deciding this. Contesting candidates want to win and they need to pay attention to what people decide, by the way they vote. Make sure that your voice is heard by the candidates in every election. "Go out and vote. Your one vote will make a difference". Do not give up your voice, by choosing not to vote. 
				</p>
				<span class="btn-info btn-small" id="displayText2" style="margin-bottom: 5px;cursor:pointer;right: 10px; position: absolute; bottom: 0px;" onclick="toggle(2)" >Hide..</span>
				</div>
				
			</div>
		</div>
		
		<div class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;" id="VoteRightResponsibility" >
		
			<h2>Vote! Your Right, Your Responsibility!</h2>
			<div class="span12" style="">
			  	<p style="display:block" id="initialdisplay3">It is observed that mostly educated peoples don't go and cast their voting rights. If voting is done collectively, we can elect the right political parties for our country. But due to certain incidents like corruption during election, influenced voting, tampering with votes etc., cause distortion in the end results, <span id="displayText2" style="cursor:pointer;right: 10px;color:#4CB1CF;margin-left:2px; " onclick="toggle(3)" >Read More..</span>
				</p>
				<div id="toggleText3" style="display:none;" >
				<p>
					It is observed that mostly educated peoples don't go and cast their voting rights. If voting is done collectively, we can elect the right political parties for our country. But due to certain incidents like corruption during election, influenced voting, tampering with votes etc., cause distortion in the end results, which takes away the belief of common man, in the voting system. People must be made aware of the national issues and must be taught about the importance and the power of a single vote. It is important to make them literate, in the first place. Only literacy will make them aware of the national issues and will change their mindset, and most importantly, they will realize what their role in the country is.
				</p>
				<p>
				However it does not mean that all educated people vote. Many enjoy the election days as holidays thinking that their vote doesn't make much difference. But this is real fault on their part. We should not ignore our responsibilities towards the country. The country has given us many Rights, for our own benefit. As a Democratic nation, every citizen of India is provided with the right to choose our own leader through voting.  Rights come with duties. So, it is our responsibility to complete our duties sincerely. Only this can help in having a perfect democratic government which will work for the development of the country.

				</p>
				<span class="btn-info btn-small" id="displayText3" style="margin-bottom: 5px;cursor:pointer;right: 90px; position: absolute; bottom: 5px;" onclick="toggle(3)" >Hide</span>
				
				<a class="btn-info btn-small" style="margin-bottom: 5px;" href="#ImportanceOfVoting">Go To Top..</a>
				</div>
				
				
			</div>
		</div>
		
		<div class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;" id="registerDiv">
			<h2>How to Register,Transfer and Cancel A Vote</h2>
			<div class="span12" >
			
			<div class="breadcrumb">
			<h3>Register</h3>
			<ul class="ulInAddPages ">
					<li>House-to-house enumeration is done and electors residing in each house are registered by official enumerators who go physically from door-to-door to collect the information about electors. </li>
					
					<li>Any eligible person can file claim in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM6.pdf" title="Click here to View Form-6 ( For Registering in the Electoral roll ) Application">Form No. 6 </a>for inclusion of his name in the roll.</li>
					
					<a class="inline link btn-inverse btn " style="position:relative;bottom:0px;right:0px;margin-top:10px;margin-bottom:10px;" target="_blank" href="images/votersInfo/Form 6 - Application for inclusion of name in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-6 ( For Registering in the Electoral roll )">How to File Form-6 </a>
					
			</ul>
			</div>
			
			<div class="breadcrumb">
			<h3>Cancel</h3>
			<ul class="ulInAddPages">
					<li>Any eligible person can raise an objection to somebody's name or for deletion of his or any other person's name using <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM7.pdf" title="Click here to View Form-7( For Deletion roll in Electoral ) Application">Form No. 7.</li>
					
					<a class="inline link btn-inverse btn " style="position:relative;bottom:0px;right:0px;margin-top:10px;margin-bottom:10px;" target="_blank" href="images/votersInfo/Form 7 - Application for objecting inclusion or seeking deletion of name in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-7( For Deletion roll in Electoral )">How to File Form-7 </a>
			</ul>
		
			
			<h5>Reasons why we should cancel a Duplicate Vote:</h5>
					<ul class="ulInAddPages">
						<li>Chances of Misuse of Vote.</li>
						<li>Polling % Decreases.</li>
					</ul>
					
					</div>
					
					<div class="breadcrumb">
			<h3>Transfer & Modify</h3>
			<ul class="ulInAddPages">
					<li>If any particulars in the electoral roll are to be modified such as name, house number, middle name, last name, age, sex, epic number etc. a claim in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM8.pdf" title="Click here to View Form-8 ( For modifiying the particulars in the registered roll )Application">Form No. 8</a> can be filed.</li>
					
					<a class="inline link btn-inverse btn " style="position:relative;bottom:0px;right:0px;margin-top:10px;margin-bottom:10px;" target="_blank" href="images/votersInfo/Form 8 - Application for correction to particulars entered in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-8( For modifiying the particulars in the registered roll )">How to File Form-8 </a>
					
					<li>In case any elector has changed his house from the polling area of one booth to other booth in the same Assembly Constituency he can file application in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM8A.pdf" title="Click here to View Form-8A ( For Transporting the roll ) Application">Form No. 8A</a> for change/transposition from one electoral part to other part.</li>
					
					<a class="inline link btn-inverse btn " style="position:relative;bottom:0px;right:0px;margin-top:10px;margin-bottom:10px;" target="_blank" href="images/votersInfo/Form 8A - Application for transposition of entry in electoral roll.pdf" title="Click here to know How?, Where?, When? ,Who? file the Form-8A ( For Transporting the roll )">How to File Form-8A </a>
			</ul>
			<a class="btn-info btn-small" style="margin-bottom: 5px;" href="#ImportanceOfVoting">Go To Top..</a>
			</div>
			</div>
			
		</div>
	
		<div id="Armyvote"class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;">
			<h2>Army vote</h2>
			<div class="span12" style="">
			
			<h4>Different Voting Procedures for Army Personnel </h4>
			<ul class="ulInAddPages">
					
					<li><b>Proxy</b><br/>
					<p>Any person appointed by a Classified Service Voters(CSV) as his proxy under Rule 27N of Conduct of Election Rules 1961 to give vote on his behalf and in his name. Any person who is eligible to vote and is not disqualified for registration as an elector in an electoral roll under Section 16 of the Representation of the People Act, 1951 (43 of 1950) can be nominated as Proxy by filling up <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM13F&G.pdf" title="Click here to View Form-13F ( For Proxy registration ) Application">Form 13F</a>. (Rule 27 N ) lays down the qualification and procedure for appointment/revocation of the proxy by the (CSV).</p></li>
					<li><b>Substitute Proxy</b><br/><p>When a CSV changes the proxy the changed proxy is known as Substitute Proxy. Substitute proxy will be effective from the date of receipt of <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM13F&G.pdf" title="Click here to View Form-13G ( For Substitue-Proxy registration ) Application">Form 13G</a> (as given at appex 'C' attached) by the Returning Officer and will continue to act as the Proxy of the CVS until his/her nomination is revoked by the CSV.</p></li>
					
					
			</ul>
			<a class="btn-info btn-small" style="margin-bottom: 5px;" href="#ImportanceOfVoting">Go To Top..</a>
			</div>
			
		</div>
		
		<div id="NRIvotes" class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;">
			<h2>NRI votes</h2>
			<div class="span12" style="">
			<h4>NRI Voter Registration Procedure</h4>
			<ul class="ulInAddPages">
					
					<li>Every Indian citizen staying in a foreign country, who has not acquired citizenship of a Foreign country, and has completed 18 years as on 1st January of the year, can make an Application in Form 6A for being registered in the roll for the constituency pertaining to the Locality in which his place of residence in India as mentioned in the passport is located. The Application in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM6A.pdf" title="Click here to View Form-6A ( For NRI'S to register in the Electoral roll ) Application"> Form 6A</a> can be presented to the registration officer concerned.</li>
					<li>After Filling the Details the application should be submitted directly to the Electoral Registration Officer (ERO) of the constituency within which the place of ordinary residence of the applicant as given in the valid passport falls. The Application in <a class="inline link " style="position:relative;bottom:0px;right:0px;" target="_blank" href="images/votersInfo/FORM6A.pdf" title="Click here to View Form-6A ( For NRI'S to register in the Electoral roll ) Application"> Form 6A</a> can be presented in person to the ERO or sent by post addressed to the ERO concerned.</li>
					
					
			</ul>
			<a class="btn-info btn-small" style="margin-bottom: 5px;" href="#ImportanceOfVoting">Go To Top..</a>
			</div>
			
		</div>
		
		<div id="PostalBallotVoting" class="row-fluid breadcrumb whitegloss subcontents" style="margin:0px;">
			<h2>Postal Ballot Voting</h2>
			
			
			<p id="initialdisplay4" style="display:block;">
			 Postal voting describes the method of voting in an election whereby ballot papers are distributed and/or returned by post to electors.
			<span id="displayText4" style="cursor:pointer;right: 10px;color:#4CB1CF;margin-left:2px; " onclick="toggle(4)" >Read More..</span>
			</p>
			<div id="toggleText4" style="display:none;">
			<p>
			 Postal voting describes the method of voting in an election whereby ballot papers are distributed and/or returned by post to electors.
			
			A postal paper will have a counterfoil attached to it. As Returning Officer for an Assembly Constituency and Assistant Returning Officer for a Parliamentary Constituency, you will have to send postal ballot papers to those of the voters at each of the two elections who are entitled to vote by post. 
			</p>
			<div class="span12" style="">
			<h4>Voters entitled to postal ballot</h4>
			<ul class="ulInAddPages">
					<li><b>Service voters </b>include members of the armed forces of the Union, members of a force to which the provisions of the Army Act, 1950, members of the armed police force of a State who are serving outside that State, persons who are employed under the Government of India in a post outside India comes under this category.</li>
					<li><b>Special voters</b> include any persons holding an office declared by the President to be an office to which the provisions of sub-section (4) of section 20 of the Representation of the People Act, 1950 are declared to apply. </li>
					<li><b>The wives of persons referred to Service voters</b> and Special voters means wife of such person if he and his wife have been registered as electors by virtue of a statement made under sub-section (5) of the said section.  </li>
					<li><b>Electors subjected to preventive detention</b> means any person subjected to preventive detention under any law for the time being in force.</li>
					<li><b>Voters on election duty </b> means a Polling Agent, a Polling Officer, Presiding Officer or other public servant who is an elector in the constituency and is by reason of his being on election duty, unable to vote at the polling station where he is entitled to vote.</li>
					<li><b>Notified voter's</b> means a voter who belongs to the class of persons notified by the Election Commission under clause (c) of Section 60 of the Representation of the People Act, 1951.</li>
			</ul>
			
			
			
			</div>
			<span class="btn-info btn-small" id="displayText3" style="margin-bottom: 5px;cursor:pointer;right: 90px; position: absolute; bottom: 5px;" onclick="toggle(4)" >Hide</span>
			
			</div>
			<a class="btn-info btn-small" style="margin-bottom: 5px;" href="#ImportanceOfVoting">Go To Top..</a>
			
		</div>
		
		
		
		
	</div>

	<div class="rightdiv whitegloss">
		<div class="span4 ">
		<ul class="nav nav-tabs nav-stacked span3">
<li class="active"><a style="margin-bottom: 5px;" href="#ImportanceOfVoting">Importance of Voting</a></li>
<li class="active"><a style="margin-bottom: 5px;" href="#WhyShouldIVote">Why Should I Vote?</a></li>
<li class="active"><a style="margin-bottom: 5px;" href="#VoteRightResponsibility"> Vote! Your Right, Your Responsibility </a></li>
<li class="active"><a style="margin-bottom: 5px;" href="#registerDiv"> Register/Cancel/Transfer</a></li>
<li class="active"><a style="margin-bottom: 5px;" href="#Armyvote"> Army vote</a></li>
<li class="active"><a style="margin-bottom: 5px;" href="#NRIvotes">  NRI votes</a></li>
<li class="active"><a style="margin-bottom: 5px;" href="#PostalBallotVoting">  Postal Ballot Voting</a></li>

<li><a class="inline" target="_blank" href="images/votersInfo/FORM6.pdf" title="Click to view Form-6(For Registering in the Electoral roll) application">Form 6</a></li>
<li><a class="inline" target="_blank" href="images/votersInfo/FORM6A.pdf" title="Click to view Form-6(For NRI'S Registering in the Electoral roll) application">Form 6A</a></li>
<li><a class="inline" target="_blank" href="images/votersInfo/FORM7.pdf" title="Click to view Form-6(For Canceling the electoral roll) application">Form 7</a></li>
<li><a class="inline" target="_blank" href="images/votersInfo/FORM8.pdf" title="Click to view Form-6(For Modifying the particulars in the Electoral roll) application">Form 8</a></li>
<li><a class="inline" target="_blank" href="images/votersInfo/FORM8A.pdf" title="Click to view Form-6( For Transporting the roll) application">Form 8A</a></li>
<li><a class="inline" target="_blank" href="images/votersInfo/FORM13F&G.pdf" title="Click here to View Form-13F&13G ( For Proxy and substitute proxy registration ) Application">Form 13F& 13G</a></li>
<li><a class="inline" target="_blank" href="images/votersInfo/EVM.pdf" title="Click to know more about the EVM">EVM</a></li>
</ul>

		
		</div>
<div class="span4">
<!--<h3 >Our Services</h3>-->

<div class="image_styles">

<div id="videogallery">
<h3>Video's</h3>
	<div>
      <a rel="#voverlay"  href="http://www.youtube.com/v/Kwilrv3LVLA?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">
       <img style="width:180px;height:175px;margin-left:10px;margin-top: 10px;margin-bottom: 10px;" src="http://img.youtube.com/vi/Kwilrv3LVLA/1.jpg">
      </a>
   </div>

    <div>
	   <a rel="#voverlay" href='http://www.youtube.com/v/2Kexv-ec7Jg?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/2Kexv-ec7Jg/2.jpg' style="width: 180px; height: 175px;margin-left:10px;margin-top: 10px;"/></a>
    </div>
	
	<div>
	   <a rel="#voverlay" href='http://www.youtube.com/v/BUVly4H7kGU?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/BUVly4H7kGU/1.jpg' style="width: 180px; height: 175px;margin-left:10px;margin-top: 10px;"/></a>
    </div>
	
   



</div>	
	
	</div>	
	</div>
</div>

<script>
$(document).ready(function(){
 alert('a');
});

function toggle(id){
	var ele = document.getElementById("toggleText"+id);
	var text = document.getElementById("displayText"+id);
	var initial=document.getElementById("initialdisplay"+id);
	initial.style.display = "none";
	
	if(ele.style.display == "block") {
		initial.style.display = "block";
		ele.style.display = "none";
		text.innerHTML = "Read More..";
	}
	else {
		ele.style.display = "block";
		text.innerHTML = "Hide";
	}
}
</script>
</body>
</html>