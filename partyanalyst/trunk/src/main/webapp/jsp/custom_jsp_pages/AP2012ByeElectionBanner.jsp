<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
      <!-- For image display on mouseover -->	
<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script> 

<style>
 .main-mbg {
    width:962px;
}


.resulttableclass a{text-decoration:none;}
.resulttableclass a:hover{text-decoration:underline;}
.resulttableclass{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.resulttableclass td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;height:35px;}
.resulttableclass th:nth-child(2){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(2) a{color:#fff;}
.resulttableclass th:nth-child(3){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(3) a{color:#fff;}
.resulttableclass td a{color:#000;}
.resulttableclass th:nth-child(4){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(4) a{color:#fff;}
.resulttableclass th:nth-child(5){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(5) a{color:#fff;}
.resulttableclass th:nth-child(6) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(6) a{color:#fff;}
.resulttableclass th:nth-child(7) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(7) a{color:#fff;}
.resulttableclass th:nth-child(8) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(8) a{color:#fff;}
.resulttableclass th:nth-child(9) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(9) a{color:#fff;}
.resulttableclass th:nth-child(10) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(10) a{color:#fff;}
.resulttableclass th:nth-child(11) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(11) a{color:#fff;}

.resulttableclass th{border:1px solid #d3d3d3;width:15%;padding:10px 5px;}
.resulttableclass tr:nth-child(odd){background:#f3f3f3;}
.resulttableclass th:nth-child(1){color:#000;}
.resulttableclass th:nth-child(1) a{color:#000;}
.resulttableclass td {text-align:center;font-weight:bold}
.winTD{background-color : GreenYellow;}

.resulth3{border-bottom:2px solid #999999;margin-top:0px;background:#ED5B21;color:#fff;padding:2px;width:550px; -moz-border-radius:2px;margin-left:3px; padding-left: 10px;}



.resulttableclass1 a{text-decoration:none;}
.resulttableclass1 a:hover{text-decoration:underline;}
.resulttableclass1{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.resulttableclass1 td{border:1px solid #d3d3d3;padding:5px 3px;color:#3d3d3d;height:35px;}
.resulttableclass1 th:nth-child(2){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(2) a{color:#fff;}
.resulttableclass1 th:nth-child(3){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(3) a{color:#fff;}
.resulttableclass1 td a{color:#000;}
.resulttableclass1 th:nth-child(4){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(4) a{color:#fff;}
.resulttableclass1 th:nth-child(5){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(5) a{color:#fff;}
.resulttableclass1 th:nth-child(6) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(6) a{color:#fff;}
.resulttableclass1 th:nth-child(7) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(7) a{color:#fff;}
.resulttableclass1 th:nth-child(8) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(8) a{color:#fff;}
.resulttableclass1 th:nth-child(9) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(9) a{color:#fff;}
.resulttableclass1 th:nth-child(10) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(10) a{color:#fff;}
.resulttableclass1 th:nth-child(11) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(11) a{color:#fff;}

.resulttableclass1 th{border:1px solid #d3d3d3;padding:10px 5px;}
.resulttableclass1 tr:nth-child(odd){background:#f3f3f3;}
.resulttableclass1 th:nth-child(1){color:#000;}
.resulttableclass1 th:nth-child(1) a{color:#000;}
.resulttableclass1 td {text-align:center;font-weight:bold}
.winTD{background-color : GreenYellow;}

.resulth3{border-bottom:2px solid #999999;margin-top:0px;background:#ED5B21;color:#fff;padding:2px;width:550px; -moz-border-radius:2px;margin-left:3px; padding-left: 10px;}

.buttonClass {
	background-color: background;
    border-radius: 6px 6px 6px 6px;
    color: white;
    cursor: pointer;
    font-weight: bold;
    padding: 6px;
}
.thStyle{background:#5e5e5e;color:#fff;font-weight:bold;text-align: center;}
.spltableclass a{text-decoration:none;}
.spltableclass a:hover{text-decoration:underline;}
.spltableclass{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.spltableclass td{border:1px solid #d3d3d3;width:1%;padding:5px 3px;color:#3d3d3d;height:35px;}
.spltableclass th:nth-child(2){background:#fcfd03;color:#000;}
.spltableclass th:nth-child(2) a{color:#000;}
.spltableclass th:nth-child(3){background:#ffbfcd;color:#000;}
.spltableclass th:nth-child(3) a{color:#000;}
.spltableclass td a{color:#000;}
.spltableclass th:nth-child(4){background:#006524;color:#fff;}
.spltableclass th:nth-child(4) a{color:#fff;}
.spltableclass th:nth-child(5){background:#0c35ad;color:#fff;}
.spltableclass th:nth-child(5) a{color:#fff;}
.spltableclass th:nth-child(6) {background:#F58220;color:#fff;}
.spltableclass th:nth-child(6) a{color:#fff;}
.spltableclass th{border:1px solid #d3d3d3;width:15%;padding:10px 5px;}
.spltableclass tr:nth-child(odd){background:#f3f3f3;}
.spltableclass th:nth-child(1){background:#5e5e5e;color:#fff;padding:5px;text-align:left;padding-left:15px;}
.spltableclass th:nth-child(1) a{color:#fff;}
</style>

<div>

<div>
<IMG src="images/specialPage/AP_BYE_ELECTION_2012.jpg" style="width: 974px;" alt="Andhra Pradesh 2012 Bye Election"/>
</div>
<br>
<div id="upComing" style="background:#FFF;padding-top: 12px;">
<span class="resulth3" style="font-weight:bold;font-family:verdana;margin:13px;padding:5px;width:560px;">Andhra Pradesh 2012 Bi Elections</span>
<div style="text-align:justify;margin:10px;padding:10px;"> 
<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">AP Bi Election Schedule</span> -

<span style="font-family:verdana;font-size:13px;">Election Notification - May 18,  Last date for Nomination - May 25, Nomination withdraw Last date - May 28, <br />Polling - June 12, Counting - June 15.</span><br /><br />

<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;">
<b>Party Analyst Detailed Analysis on Andhra Pradesh 2012 Bi Elections</b></span>
<br /><br />
<div style="margin-left:20px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;">
<b>What factors makes TDP and INC to fight with YSRC   ?</b></span>
<ul style="margin: 18px 18px 18px 0px; font-family: verdana; font-size: 13px;">
<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png"> INC and TDP are able to give good fight in some constituencies where candidates are very strong (Kothapalli Subbarayudu from &nbsp;&nbsp;&nbsp;Narsapur, Thota Trimurthulu from Ramachandrapuram, Chengala Venkata Rao from Payakaraopeta, Deepak Reddy from Raydurg &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;etc...).</li>

<li style="margin-bottom: 14px;"><img src="images/icons/diamond.png" style="padding: 5px 5px 0px;">Interestingly most of these leaders in INC came from PRP. Thus, PRP merger benefited to congress in this way rather than &nbsp;&nbsp;&nbsp;&nbsp;Chiranjeevi image.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Present government is INC. So, if people vote for INC, they will at least get some development activities in these two years.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png"> Everyone in YSRC are sitting MLAs, they are having lot of opposition from the voters. Even this opposition has been overcome &nbsp;&nbsp;&nbsp;&nbsp;with YSR factor.</li>

 <li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Caste factor for TDP, especially from Kamma Community.</li>

</ul>
</div>
<div style="margin-left:20px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;"><b>What factors makes YSRC to get this much hype    ?</b></span>
<ul style="margin: 18px 18px 18px 0px;font-family: verdana; font-size: 13px;">
<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">YSR - Low and middle class people are feeling that all the developments and benefits they got in INC government, is only due to &nbsp;&nbsp;&nbsp;&nbsp;YSR. No one is able to create confidence in these people after YSR death. Voters are feeling that, if YSRC comes into ruling, only &nbsp;&nbsp;&nbsp; then,they will get better beneficial schemes. This hope is making the voters to move with YSRCP.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Sympathy (Especially with YSR family on roads), Why the government is harassing their leader's family, who has done something &nbsp;&nbsp;&nbsp;for them.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Mr. Chandra Babu Naidu is working hard to bring his party into power. Unfortunately YSR created some trademark in the voters &nbsp;&nbsp;(middle class and lower class people), not to believe Chandra Babu Naidu�s promises. This has become a bottleneck to improve &nbsp;TDP.</li>
</ul>
</div>
<div style="margin-left:20px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;"><b>What factors are involved in increasing Voting Percentage ?</b></span>
<ul style="margin: 18px 18px 18px 0px;font-family: verdana; font-size: 13px;">
<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">It is in circulation that the polling percentage has increased in AP Bye elections, because of voters voluntary participation or their 
&nbsp;&nbsp;&nbsp;opposition to government or favoring one party.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">But we need to consider one important factor which is missed, a great step which Election Commission has taken in 2011 and must &nbsp;&nbsp;&nbsp;appreciate it for this.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Election Commission has taken certain steps to eliminate duplicate, transferred and dead voters from 2011 voters list, before &nbsp;&nbsp;&nbsp;publishing 2012 voter rolls. In this process, around 10-12% of voters are cleaned up from old list. Every time, this 10-12% won't get &nbsp;&nbsp;&nbsp;polled. Then this 10-12% of voters will not be polled, even when everyone votes.</li>
</ul>
</div>

<span style="font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;"><b>Party Analyst View on Coming Results of 18 Assembly & Nellore Parliament of Andhra Pradesh 2012 Bye Election</b> </span><br><br>

<span style="font-family:verdana;font-size:13px;">Party Analyst announcing its Views on Coming Results of Andhra Pradesh 2012 Bi Elections. In 18 Assembly Constituencies <b>YSRC Party may Win 12 - 15 Seats, INC Party may Win 1 - 2 Seats, TDP Party may Win 1 - 3 Seats, TRS may Win 1 Seat (Parakala Constituency) and YSRC will Win in Nellore Parliament Constituency</b>.</span><br /><br>

<style>
	

.resultclass{
width:275px;
color:#fff;
display:inline-block;
position:relative;
margin:5px 10px 5px 10px;
text-align:center;
}
.resultclass span.favour{float:left;padding:5px;background:#aaa;width:265px;height:33px;over-flow:auto;}

.resultclass span.cons{background:#f3f3f3;width:100%;
color:#000;font-size:15px;display:inline-block;float:left;
border-radius:5px 5px 0px 0px;box-shadow:0px -2px 1px #888;
padding:5px 0px;
}

.resultclass .borderperc{position:relative;display:inline-block;width:275px;
background:#efefef;
padding:1px 0px; clear:both;
text-shadow:0px 1px 2px #fff;color:#000;
}
.perc1{float:left;display:inline-block;box-shadow:0px 0px 2px #aaa;border-radius:0px 0px 0px 5px;padding:5px 0px;}
.perc2{float:left;display:inline-block;box-shadow:0px 0px 2px #aaa;border-radius:0px 0px 5px 0px;padding:5px 0px;}
.perc3{float:left;display:inline-block;box-shadow:0px 0px 2px #aaa;border-radius:0px 0px 5px 5px;padding:5px 0px;}

</style>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=252" title="Click here to View Rajampet Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Rajampet</a></span>
	<span class="favour">YSRC Party (Amarnath Reddy) will Win</span>

	
<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=248" title="Click here to View Rayachoti Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Rayachoti</a></span>
	<span class="favour">YSRC Party (Gadikota Srikanth Reddy) will Win</span>

	
<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117"  title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=298" title="Click here to View Ananthapur Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ananthapur</a></span>
	<span class="favour">YSRC Party (B.Gurunatha Reddy) will Win</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;">

<a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=254" title="Click here to View Allagadda Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Allagadda</a></span>
	<span class="favour">YSRC Party (Bhuma Shobha Reddy) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;">

<a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=291" title="Click here to View Tirupathi Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Tirupathi</a></span>
	<span class="favour">YSRC Party (Karunakar Reddy Bhumana) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=238" title="Click here to View Udayagiri Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Udayagiri</a></span>
	<span class="favour">YSRC Party (Mekapati Chandrashekar Reddy ) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=265" title="Click here to View Yemmiganuru Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Yemmiganuru</a></span>
	<span class="favour">YSRC Party (K.Chenna kesava Reddy) will Win(TDP Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=246" title="Click here to View Railway Kodur Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Railway Kodur (SC)</a></span>
	<span class="favour">YSRC Party (Koramutla Srinivasulu) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=227" title="Click here to View Ongole Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ongole</a></span>
	<span class="favour">YSRC Party (Balineni Srinivasa Reddy (Vasu)) will Win(TDP Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=140" title="Click here to View Payakaraopet Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Payakaraopet (SC)</a></span>
	<span class="favour">YSRC Party (Golla Babu Rao) will Win(TDP Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=205" title="Click here to View Macherla Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Macherla</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>
	
	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=176" title="Click here to View Polavaram Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Polavaram</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117"  title="Click here to View YSRC Party Details"style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=212" title="Click here to View Prathipadu Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Prathipadu (SC)</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=276" title="Click here to View Rayadurg Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Rayadurg</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>

	<div class="borderperc">
	
<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/TRS.png" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=94" title="Click here to View Parakala Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Parakala</a><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between TRS and YSRC</span>

	<div class="borderperc">
	<div class="perc1" style="background:#F89BEC;width:50%;"><a href="partyPageAction.action?partyId=886" title="Click here to View TRS Party Details" style="color:#000000;">TRS (50%)</div>
	<div class="perc2" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=112" title="Click here to View Narasannapeta Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Narasannapeta</a><img width="25" height="20" src="images/party_flags/INC.png" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and INC</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:75%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (75%)</a></div>
	<div class="perc2" style="background:#56EC58;width:25%;"><a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Details" style="color:#ffffff;">INC (25%)</a></div>
	</div>
</div>

<div class="resultclass" style="margin-left:31px;">
	<span class="cons"><span><img width="25" height="20" src="images/party_flags/INC.png" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=173" title="Click here to View Narsapur Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Narsapuram</a></span>
	<span class="favour">INC Party (Kothapalli Subbarayudu (Peda Babu)) will Win</span>

	<div class="borderperc">
	<div class="perc3" style="background:#56EC58;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Details" style="color:#ffffff;">INC</a></div>
	</div>
</div>

<div class="resultclass" style="margin-left:31px;">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=159" title="Click here to View Ramachandrapuram Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ramachandrapuram</a><img width="25" height="20" src="images/party_flags/INC.png" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRCand INC</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#56EC58;width:50%;"><a href="partyPageAction.action?partyId=362" style="color:#ffffff;"><a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Details" style="color:#ffffff;">INC (50%)</a></a></div>
	</div>
</div>
<br><br>
<span style="font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;">Party wise Votes Share in 2009 Assembly Elections of 2012 Bi election Constituencies</span><br>

<table width="100%" class="resulttableclass1" style="margin-top:9px;margin-bottom:10px;text-align:center">
  <tr>
    <th style="background-color:#21B2ED;color:#fff;padding:0px;">SNO</th>
    <th>Region</th>
    <th>Constituency</th>
	<th>Total Voters
</th>
	<th>Polled Votes
</th>
	<th>TDP 
</th>
    <th>INC
</th>
	<th>PRP
</th>
	<th>Others
</th>
	
   </tr>
  <tr>
    
    <td>1</td>
    <td>Costal Andhra</td>
	<td ><a href="constituencyPageAction.action?constituencyId=227" title="Click here to View Ongole Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ongole</a></td>
    <td>2,34,265</td>
	<td>1,56,530</td>
    <td>44,228</td>
	<td class="winTD">67,214</td>
    <td>33,716</td>
	<td>11,365</td>
 
  </tr>
   <tr>
    
    <td>2</td>
    <td>Costal Andhra</td>
	<td ><a href="constituencyPageAction.action?constituencyId=238" title="Click here to View Udayagiri Constituency Details, Previous Election Results, Voting Trendz" >Udayagiri</a> </td>
    <td>2,03,782</td>
	<td>1,48,133</td>
    <td>55,870</td>
	<td class="winTD">69,352</td>
    <td>14,512</td>
	<td>8,073</td>
	
  </tr>
   <tr>
    
    <td>3</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=159" title="Click here to View Ramachandrapuram Constituency Details, Previous Election Results, Voting Trendz" >Ramachandrapuram </a></td>
    <td>1,75,344</td>
	<td>1,42,302</td>
    <td>23,252</td>
	<td class="winTD">56,589</td>
    <td>52,558</td>
	<td>9,451</td>
 
  </tr>
   <tr>
    
    <td>4</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=112" title="Click here to View Narasannapeta Constituency Details, Previous Election Results, Voting Trendz">Narasannapeta </a></td>
    <td>1,72,020</td>
	<td>1,36,923</td>
    <td>42,837</td>
	<td class="winTD">60,426</td>
    <td>25,280</td>
	<td>8,170</td>
 
  </tr>
   <tr>
    
    <td>5</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=205" title="Click here to View Macherla Constituency Details, Previous Election Results, Voting Trendz" >Macherla</a></td>
    <td>2,01,861</td>
	<td>1,52,057</td>
    <td>57,168</td>
	<td class="winTD">66,953</td>
    <td>16,386</td>
	<td>11,544</td>
 
  </tr>
 
 <tr>
    
    <td>6</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=173" title="Click here to View Narsapur Constituency Details, Previous Election Results, Voting Trendz" >Narsapuram</a></td>
    <td>1,32,956</td>
	<td>1,15,957</td>
    <td>10,841</td>
	<td class="winTD">58,560</td>
    <td>41,235</td>
	<td>5,257</td>
 
  </tr>
  <tr>
    
    <td>7</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=176" title="Click here to View Polavaram Constituency Details, Previous Election Results, Voting Trendz" >Polavaram</a></td>
    <td>1,66,300</td>
	<td>1,44,687</td>
    <td>44,634</td>
	<td class="winTD">50,298</td>
    <td>36,483</td>
	<td>13,247</td>
 
  </tr>
  <tr>
    
    <td>8</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=140" title="Click here to View Payakaraopet Constituency Details, Previous Election Results, Voting Trendz" >Payakaraopet (SC)</a></td>
    <td>2,10,830</td>
	<td>1,57,926</td>
    <td>50,042</td>
	<td class="winTD">50,698</td>
    <td>49,264</td>
	<td>7,886</td>
 
  </tr>
  <tr>
    
    <td>9</td>
    <td>Costal Andhra</td>
	<td><a href="constituencyPageAction.action?constituencyId=212" title="Click here to View Prathipadu Constituency Details, Previous Election Results, Voting Trendz" >Prathipadu (SC)</a></td>
    <td>2,07,396</td>
	<td>1,68,896</td>
    <td>64,282</td>
	<td class="winTD">66,324</td>
    <td>33,889</td>
	<td>4,174</td>
 
  </tr>
  <tr>
    
    <td>10</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=246" title="Click here to View Railway Kodur Constituency Details, Previous Election Results, Voting Trendz">Railway Kodur (SC)</a></td>
    <td>1,58,139</td>
	<td>1,17,703</td>
    <td>39,359</td>
	<td class="winTD">51,747</td>
    <td>22,122</td>
	<td>4,435</td>
 
  </tr>
  <tr>
    
    <td>11</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=252" title="Click here to View Rajampet Constituency Details, Previous Election Results, Voting Trendz" >Rajampet</a></td>
    <td>1,85,475</td>
	<td>1,38,346</td>
    <td>48,055</td>
	<td class="winTD">60,397</td>
    <td>21,499</td>
	<td>8,363</td>
 
  </tr>
  <tr>
    
    <td>12</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=248" title="Click here to View Rayachoti Constituency Details, Previous Election Results, Voting Trendz">Rayachoti</a></td>
    <td>1,86,579</td>
	<td>1,43,194</td>
    <td>57,069</td>
	<td class="winTD">71,901</td>
    <td>6,469</td>
	<td>7,755</td>
 
  </tr>
  <tr>
    
    <td>13</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=298" title="Click here to View Ananthapur Constituency Details, Previous Election Results, Voting Trendz" >Ananthapur</a></td>
    <td>2,29,876</td>
	<td>1,16,008</td>
    <td>32,033</td>
	<td class="winTD">45,275</td>
    <td>28,489</td>
	<td>10,211</td>
 
  </tr>
  <tr>
    
    <td>14</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=276" title="Click here to View Rayadurg Constituency Details, Previous Election Results, Voting Trendz">Rayadurg</a></td>
    <td>1,98,167</td>
	<td>1,55,854</td>
    <td>62,168</td>
	<td class="winTD">76,259</td>
    <td>9,670</td>
	<td>7,757</td>
 
  </tr>
  
  <tr>
    
    <td>15</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=265" title="Click here to View Yemmiganuru Constituency Details, Previous Election Results, Voting Trendz" >Yemmiganuru</a></td>
    <td>1,89,278</td>
	<td>1,28,816</td>
    <td>51,443</td>
	<td class="winTD">53,766</td>
    <td>16,638</td>
	<td>6,876</td>
 
  </tr>
  <tr>
    
    <td>16</td>
    <td>Rayalaseema
</td>
	<td><a href="constituencyPageAction.action?constituencyId=254" title="Click here to View Allagadda Constituency Details, Previous Election Results, Voting Trendz" >Allagadda</a>
</td>
    <td>2,00,691</td>
	<td>1,53,925</td>
    <td>23,800</td>
	<td>59,597</td>
    <td class="winTD">61,555</td>
	<td>8,895</td>
   </tr>
  <tr>
    
    <td>17</td>
    <td>Rayalaseema</td>
	<td><a href="constituencyPageAction.action?constituencyId=291" title="Click here to View Tirupathi Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Tirupathi</a></td>
    <td>2,47,160</td>
	<td>1,27,627</td>
    <td>21,307</td>
	<td>40,379</td>
    <td class="winTD">56,309</td>
	<td>9,632</td>
 
  </tr>
  <tr>
    
    <td>18</td>
    <td>Telangana</td>
	<td><a href="constituencyPageAction.action?constituencyId=94" title="Click here to View Parkal Constituency Details, Previous Election Results, Voting Trendz" >Parkal</a></td>
    <td>1,93,381</td>
	<td>1,47,872</td>
    <td>56,335(TRS)</td>
	<td class="winTD">69,135</td>
    <td>11,968</td>
	<td>10,315</td>
 
  </tr>
  <tr>
  <td></td>
  <td>Total</td>
  <td></td>
  <td>34,93,500</td>
  <td>25,52,756</td>
  <td>7,28,388</td>
  <td>10,74,870</td>
  <td>5,38,042</td>
  <td>1,53,406</td>
  </tr>
  <tr><td></td><td></td><td></td><td></td>
  <td>100 %</td>
  <td>28.53 %</td>
<td>42.11 %</td>
<td>21.08 %</td>
<td>6.01 %</td>
</tr>
</table>

<span style="color:#ED5B21;font-weight:bold;">In Parliament</span> - Nellore MP <a href="candidateElectionResultsAction.action?candidateId=13626">Mekapati Rajamohan Reddy</a>, who is a well-known supporter of YSR Congress Party President Jagan Mohan Reddy, has submitted his resignation to Lok Sabha Speaker Meira Kumar on August 24. 
<!--<div style="float: right; margin-right: 32px; margin-top: 49px;"><a class="buttonClass" href="javascript:{}" style="text-decoration:none;" onclick="getConstituencyElecResultsWindow(495,'Parliament',2009)">View Complete Results</a></div>-->
</div>
<table width="100%;"> <span class="resulth3" style="font-weight:bold;font-family:verdana;margin:13px;padding:5px;width:560px;">Nellore Parliament Candidate Details</span>
<tr><!--<td valign="top">

<table border="1" width="96%" style="margin-top: 10px;border-collapse: collapse;margin-left:13px;
 text-align:center;"  cellspacing="0" cellpadding="5">

<tr><div class="schedule">Schedule</div></tr>
<TR style="color: #fff;"> <TH COLSPAN=2 BGCOLOR="#21B2ED;color:#ffffff;">Schedule</TH> </TR>

<tr>
<td>18-05-2012</td>
<td>Notification</td></tr>
<tr><td>25-05-2012</td>
<td>Last Date for Nominations</td></tr>
<tr><td>28-05-2012</td>
<td>Last Date for withdraw</td></tr>

<tr><td>12-06-2012</td>
<td>Polling</td></tr>

<tr><td>15-06-2012</td>
<td>Counting</td></tr>

</tr>
</table></td>-->

<td valign="top">
<fieldset id="WinningCandidateFieldSet" style="margin-top: 12px; width: 91%;"><legend style="font-weight: bold;">About Mekapati Rajamohan Reddy </legend><div id="WinningCandidateDiv" style="width: 301px;">
<table class="legendTable" id="WinningCandidateTableClass">
 <tr>
  <td style="width: 96px;"><img onerror="setDefaultImage(this)" height="80" width="80" src="images/candidates/MEKAPATI RAJAMOHAN REDDY.jpg"></img></td>
  <td><a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="candidateElectionResultsAction.action?candidateId=13626" title="Click here to view MEKAPATI RAJAMOHAN REDDY profile -previous election result, Updated News, Photos ,Videos"> MEKAPATI RAJAMOHAN REDDY</a>
<br />
Party Flag - <a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="partyPageAction.action?partyId=1117" title="Click here to view YSRC party profile - previous election result, Updated News, Photos and Videos"><img style="margin-top:10px;" height="30" width="40" src="/PartyAnalyst/images/party_flags/YSRC.PNG"></a></td>
</tr>
<!--<tr>
<td colspan="2"><b><u>Recent Election Info</u> :</b></td></tr>-->
<tr><td colspan="2"><strong>Won in 2009 </strong>from <a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="partyPageAction.action?partyId=362" title="Click here to view INC party profile -previous election result, Updated News, Photos and Videos">INC </a>party in <a href="constituencyPageAction.action?constituencyId=495" style="text-decoration: none;color: #247CD4;" title="Click here to view Nellore constituency profile - previous election results and Updated News about constituency.">Nellore</a> parliament constituency.<br /><strong>Won in 2004 </strong> from INC party in <a href="constituencyPageAction.action?constituencyId=493" style="text-decoration: none;color: #247CD4;" title="Click here to view Narasaraopet constituency profile - previous election results and Updated News about constituency.">Narasaraopet </a>parliament constituency.</td>
</tr>
<tr><td colspan="2" 
style="text-align: right;"><a onclick="getConstituencyElecResultsWindow(495,'Parliament',2009)" style="text-decoration:none;color:green;font-weight:bold;font-family:verdana;font-size:12px" href="javascript:{}">View Details</a></td>
</tr>
<!--<tr><td><b>Status :</b></td><td>Won</td></tr>
<tr><td><b>Votes Earned :</b></td><td>430235</td></tr>
<tr><td><b>Votes Percentage:</b></td><td>42.92 %</td></tr>
<tr><td><b>Votes Margin Gained	:</b></td><td>54993</td></tr>
<tr><td><b>Votes Margin % Gained	: </b></td><td>5.49%</td></tr>-->

</table></div></fieldset></div></td>
<td valign="top"><fieldset id="WinningCandidateFieldSet" style="margin-top: 12px; width: 91%;"><legend style="font-weight: bold;">About T SubbaRami Reddy </legend><div id="WinningCandidateDiv" style="width: 272px;">
<table class="legendTable" id="WinningCandidateTableClass">
 <tr>
  <td style="width: 99px;"><img onerror="setDefaultImage(this)" height="80" width="80" src="images/candidates/T SUBBARAMI REDDY.jpg"></img></td>
  <td><a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="candidateElectionResultsAction.action?candidateId=41939" title="Click here to view T SUBBARAMI REDDY profile -previous election result, Updated News, Photos and Videos"> T SUBBARAMI REDDY</a><br />

Party Flag -<a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="partyPageAction.action?partyId=362" title="Click here to view INC party profile -previous election result, Updated News, Photos and Videos"> <img style="margin-top:10px;" height="30" width="40" src="/PartyAnalyst/images/party_flags/INC.png"></a></td>
</tr>
<!--<tr>
<td colspan="2"><b><u>Recent Election Info</u> :</b></td></tr>-->
<tr><td colspan="2">Member of the Parliament in the Rajya Sabha from Andhra Pradesh. <br /><br /><br /></td>
</tr>
<tr><td colspan="2" 
style="text-align: right;"><a onclick="getConstituencyElecResultsWindow(508,'Parliament',1999)" style="text-decoration:none;color:green;font-weight:bold;font-family:verdana;font-size:12px" href="javascript:{}">View Details</a></td>
</tr>
<!--<tr><td><b>Status :</b></td><td>Lost</td></tr>
<tr><td><b>Votes Earned :</b></td><td>403,117</td></tr>
<tr><td><b>Votes Percentage:</b></td><td>45.56 %</td></tr>
<tr><td><b>Votes Margin 	:</b></td><td>38919</td></tr>
<tr><td><b>Votes Margin %	: </b></td><td>4.25 %</td></tr>-->

</table></div></fieldset></td>
<td valign="top"><fieldset id="WinningCandidateFieldSet" style="margin-top: 12px; width: 91%;"><legend style="font-weight: bold;">About Vanteru Venugopal Reddy</legend><div id="WinningCandidateDiv" style="width:306px;">
<table class="legendTable" id="WinningCandidateTableClass">
 <tr>
  <td style="width: 87px;"><img onerror="setDefaultImage(this)" height="80" width="80" src="images/candidates/VANTERU VENUGOPAL REDDY.jpg"></img></td>
  <td><a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="candidateElectionResultsAction.action?candidateId=6100" title="Click here to view VANTERU VENUGOPAL REDDY profile -previous election result, Updated News, Photos and Videos"> VANTERU VENUGOPAL REDDY</a>
 <br />
Party Flag -<a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="partyPageAction.action?partyId=872" title="Click here to view TDP party profile -previous election result, Updated News, Photos and Videos"> <img style="margin-top:10px;" height="30" width="40" src="/PartyAnalyst/images/party_flags/TDP.PNG"></a></td>
</tr>
<!--<tr>
<td colspan="2"><b><u>Recent Election Info</u>  :</b></td></tr>-->
<tr><td colspan="2"><strong>Lost in 2009</strong> from <a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="partyPageAction.action?partyId=872" title="Click here to view TDP party profile -previous election result, Updated News, Photos and Videos"> TDP </a>party in <a href="constituencyPageAction.action?constituencyId=495" style="text-decoration: none;color: #247CD4;" title="Click here to view Nellore constituency profile - previous election results and Updated News about constituency.">Nellore </a>parliament constituency.<br /><strong>Won in 1999 </strong>from <a style="text-decoration: none;color: #247CD4;" onclick="window.opener.location.href=this.href;window.blur();return false;" href="partyPageAction.action?partyId=872" title="Click here to view TDP party profile -previous election result, Updated News, Photos and Videos">TDP </a>party in <a href="constituencyPageAction.action?constituencyId=232" style="text-decoration: none;color: #247CD4;" title="Click here to view Kavali constituency profile - previous election results and Updated News about constituency.">Kavali </a>assembly constituency.</td>
</tr><tr><td colspan="2" 
style="text-align: right;"><a onclick="getConstituencyElecResultsWindow(495,'Parliament',2009)" style="text-decoration:none;color:green;font-weight:bold;font-family:verdana;font-size:12px" href="javascript:{}">View Details</a></td></tr>
<!--<tr><td><b>Status :</b></td><td>Lost</td></tr>
<tr><td><b>Votes Earned :</b></td><td>375,242</td></tr>
<tr><td><b>Votes Percentage:</b></td><td>37.43 %</td></tr>
<tr><td><b>Votes Margin 	:</b></td><td>54993</td></tr>
<tr><td><b>Votes Margin % 	: </b></td><td>5.49 %</td></tr>-->

</table></div></fieldset></td></tr>
</table>
<div style="text-align:justify;margin:10px;padding:10px;"> <span style="color:#ED5B21;font-weight:bold;font-size:16px">In Assembly</span> - Assembly constituencies details where the bi elections are happening due to JAGAN MLA's who voted against to confidence motion and disqualified by speaker Nadendla Manohar and the Praja Rajyam president Chiranjeevi resigned from Tirupati assembly constituency.</div>
<div style="margin-left: 12px;"><span style=" background:#ED5B21;color: #FFFFFF;font-family: verdana;font-size: 14px;font-weight: bold;padding: 4px;text-align: center;cursor:pointer;border-radius:5px;" onclick="byeEleParticipatedCandidateInfo()">Andhra Pradesh Bi Election Participating Candidates</span>

</div>
<div style="margin-left: 450px; margin-bottom: 0px; margin-top: -21px;"><span id="byeElePaticipatedCandInfoSpan"><img id="plusImgIdEle" title="Click Here To Expand" style="" src="images/icons/plusImg .png" onclick="byeEleParticipatedCandidateInfo()"></img></span></div>



<div style="font-weight:bold;font-family:verdana;font-size:12px;margin:17px;">

<span>Total Assembly Constituencies - <font color="#05A8E9">18</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">3</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">1</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">14</font></span>
</div>
<table id="byeEleParticipatedCandidatetableId" class="spltableclass" style="display:none;">
<tbody>
 <tr>
   
   <th style="background:#21B2ED; color:#FFFFFF; text-align: center; padding: 9px;width: 1%;">Constituency</th>
   <th style="background: #21B2ED; color: rgb(255, 255, 255); text-align: center; padding: 9px; width: 4%;">District</th>
   <th style="background: none repeat scroll 0% 0% rgb(33, 178, 237); color: rgb(255, 255, 255); text-align: center; padding: 9px; width: 10%;">INC</th>
   <th style="background: none repeat scroll 0% 0% rgb(33, 178, 237); color: rgb(255, 255, 255); text-align: center; padding: 9px; width: 10%;">YSRC</th>
   <th style="background: none repeat scroll 0% 0% rgb(33, 178, 237); color: rgb(255, 255, 255); text-align: center; padding: 9px; width: 9%;">TDP</th>
    <!--<th style="background: none repeat scroll 0% 0% rgb(33, 178, 237); color: rgb(255, 255, 255); text-align: center; padding: 5px 0px;">Others</th>-->
  <th style="background: none repeat scroll 0% 0% rgb(33, 178, 237); color: rgb(255, 255, 255); text-align: center; padding: 0px; width: 13%;">Detailed Results</th>
   </tr>
   <tr>
   
   <td><a href="constituencyPageAction.action?constituencyId=227" title="Click here to View Ongole Constituency Details, Previous Election Results, Voting Trendz">Ongole</a>
   </td>
   <td><a href="districtPageAction.action?districtId=18&districtName=Prakasam"title="Click here to view Prakasam District page">Prakasam</a></td>
   <td><div><a id='MAGUNTA PARVATHAMMA' href="candidateElectionResultsAction.action?candidateId=111975" onmouseover="displayImage(this.id);" onmouseout="return nd();" alt="Image not available" title="click here to view Magunta Parvathamma profile-previous election result, Updated News, Photos ,Videos"> Magunta Parvathamma</a></div>
   </td>
   <td><a id='BALINENI SRINIVASA REDDY (VASU)' href="candidateElectionResultsAction.action?candidateId=2656" onmouseover="displayImage(this.id);" onmouseout="return nd();" alt="Image not available" title="click here to view Balineni Srinivasa Reddy (Vasu) profile-previous election result, Updated News, Photos ,Videos">Balineni Srinivasa Reddy (Vasu)</a>
   </td>
   <td><a href="#">Damacharla Janardhan</a> <!--/ <a id='ANJANEYULU DAMACHARLA' href="candidateElectionResultsAction.action?candidateId=6048" onmouseover="displayImage(this.id);" onmouseout="return nd();" alt="Image not available" title="click here to view Anjaneyulu Damacharla profile-previous election result, Updated News, Photos ,Videos">Anjaneyulu Damacharla</a>-->
   </td>
   <!--<td><a id='EDARA HARI BABU' 
   href="candidateElectionResultsAction.action?candidateId=2657" onmouseover="displayImage(this.id);" onmouseout="return nd();"distitle="click here to view Edara Hari Babu profile-previous election result, Updated News, Photos ,Videos">Edara Hari Babu</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a>
   </td>-->
   <td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(227,'Assembly',2009)" title="Click here to view Ongole Constituency election results">View Complete Details</a>
   </td>
   </tr>
   <tr>
   </td>
   <td><a href="constituencyPageAction.action?constituencyId=238" title="Click here to View Udayagiri Constituency Details, Previous Election Results, Voting Trendz" >Udayagiri
   </td>
   <td><a href="districtPageAction.action?districtId=19&districtName=Nellore" title="Click here to view Nellore District Page">Nellore</a></td>
   <td><a id='KAMBHAM VIJAYARAMI REDDY' 
   href="candidateElectionResultsAction.action?candidateId=3583" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Kambham Vijayarami Reddy
	profile-previous election result, Updated News, Photos ,Videos">Kambham Vijayarami Reddy </a> 
   </td>
   <td><a id='MEKAPATI  CHANDRA SEKHAR REDDY' href="candidateElectionResultsAction.action?candidateId=3582" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Mekapati Chandrashekar Reddy profile-previous election result, Updated News, Photos ,Videos"> Mekapati Chandrashekar Reddy
   </td>
   <td><a href="#">Bollineni Rama Rao</a>
   </td>
   <!--<td><a id='KAMBHAM VIJAYARAMI REDDY' 
   href="candidateElectionResultsAction.action?candidateId=3583" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Kambham Vijayarami Reddy
	profile-previous election result, Updated News, Photos ,Videos">Kambham Vijayarami Reddy </a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a>
   </td>-->
   <td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(238,'Assembly',2009) " title="Click here to view Udayagiri Constituency election results">View Complete Details</a>
   </td>
   </tr>
   <tr>
   </td>
   <td><a href="constituencyPageAction.action?constituencyId=159" title="Click here to View Ramachandrapuram Constituency Details, Previous Election Results, Voting Trendz" >Ramachandrapuram
   </td>
	<td><a href="districtPageAction.action?districtId=14&districtName=East Godavari" title="Click here to view East Godavari District page">East Godavari</a></td>
   <td><a id='THOTA  THRIMURTULU' href="candidateElectionResultsAction.action?candidateId=1825" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Thota Trimurthulu  profile-previous election result, Updated News, Photos ,Videos">Thota Trimurthulu</a>
   </td>
   <td><a id='SUBHASH CHANDRA BOSE PILLI' href="candidateElectionResultsAction.action?candidateId=1824" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Subhash Chandra Bose Pilli profile-previous election result, Updated News, Photos ,Videos">Subhash Chandra Bose Pilli
   </td>
   <td><a id='CHIKKALA RAMACHANDRA RAO' href="candidateElectionResultsAction.action?candidateId=5560" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Chikkala Ramachandra Rao profile-previous election result, Updated News, Photos ,Videos">Chikkala Ramachandra Rao
   </td>
     <!--<td><a id='THOTA THRIMURTULU' href="candidateElectionResultsAction.action?candidateId=1825" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Thota Thrimurtulu profile-previous election result, Updated News, Photos ,Videos">Thota Thrimurtulu - <a href="partyPageAction.action?partyId=662" title="Click here to view PRP profile-previous election result, Updated News, Photos ,Videos">PRP</a>
   </td>-->
 <td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(159,'Assembly',2009)" title="Click here to view Ramachandrapuram Constituency election results">View Complete Details</a>
   </td>
   
   </tr>
   <tr>
   <td ><a href="constituencyPageAction.action?constituencyId=112" title="Click here to View Narasannapeta Constituency Details, Previous Election Results, Voting Trendz">Narasannapeta
   </td>
   <td><a href="districtPageAction.action?districtId=11&districtName=Srikakulam"title="Click here to view Srikakulam District page">Srikakulam</a></td>
   <td >
	<a id='DHARMANA RAMDAS' 
	href="#">Dharmana Ramdas</a>
   </td>
   <td ><a id='DHARMANA  KRISHNA DAS' href="candidateElectionResultsAction.action?candidateId=1449" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Dharmana Krishna Das profile-previous election results, Updated News, Photos ,Videos">Dharmana Krishna Das</a>
   </td>
   <td ><a href="#">Simma Swamy Babu</a>
   </td>
    <!--<td > <a id='LAKSHMANARAO BAGGU' href="candidateElectionResultsAction.action?candidateId=1450" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Baggu Lakshmana Rao profile-previous election result, Updated News, Photos ,Videos">Lakshmanarao Baggu </a> - <a href="partyPageAction.action?partyId=872"title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a>
   </td>-->
  <td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(112,'Assembly',2009)" title="Click here to view Narasannapeta Constituency election results">View Complete Details</a>
   </td>
   </tr>
   <tr>
   <td ><a href="constituencyPageAction.action?constituencyId=205" title="Click here to View Macherla Constituency Details, Previous Election Results, Voting Trendz" >Macherla</td>
   <td><a href="districtPageAction.action?districtId=17&districtName=Guntur"title="Click here to view Guntur District page">Guntur</a></td>
   <td ><a id='PINNELLI LAKSHMA REDDY' href="candidateElectionResultsAction.action?candidateId=5954" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Pinnelli Lakshma Reddy  profile-previous election result, Updated News, Photos ,Videos">Pinnelli Lakshma Reddy</a></td>

    <td><a id='RAMA KRISHNA REDDY  PINNELLI' href="candidateElectionResultsAction.action?candidateId=2553" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Rama Krishna Reddy Pinnelli profile-previous election result, Updated News, Photos ,Videos">Rama Krishna Reddy Pinnelli</a></td>
	<td><a href="#">Chirumamilla Madhu</a></td>
	<!--<td><a id='Julakanti  Brahmananda Reddy' href="candidateElectionResultsAction.action?candidateId=2554" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Julakanti  Brahmananda Reddy profile-previous election result, Updated News, Photos ,Videos"> Julakanti  Brahmananda Reddy</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
	<td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(205,'Assembly',2009)" title="Click here to view Macherla Constituency election results">View Complete Details</a></td>
   </tr>
   <tr>
   <td ><a href="constituencyPageAction.action?constituencyId=173" title="Click here to View Narsapur Constituency Details, Previous Election Results, Voting Trendz" >Narsapuram</td>
   <td><a href="districtPageAction.action?districtId=15&districtName=West Godavari"title="Click here to view WestGodavari District page">West Godavari</a></td>
   <td><a id='KOTHAPALLI SUBBARAYUDU (PEDA BABU)' href="candidateElectionResultsAction.action?candidateId=3952" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Kothapalli Subbarayudu (Peda Babu) profile-previous election result, Updated News, Photos ,Videos">Kothapalli Subbarayudu (Peda Babu)</a></td>
   <td><a id='MUDUNURI  PRASADA RAJU' href="candidateElectionResultsAction.action?candidateId=1996" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Mudunuri Prasada Raju profile-previous election result, Updated News, Photos ,Videos">Mudunuri Prasada Raju</a></td>
   <td><a href="#">Dr. Chinamilli Satyanaraya</a></td>
   <!--<td><a id='Kothapalli Subbarayudu' href="candidateElectionResultsAction.action?candidateId=1997" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Kothapalli Subbarayudu (Pedababu) profile-previous election result, Updated News, Photos ,Videos">Kothapalli Subbarayudu (Pedababu)</a> - <a href="partyPageAction.action?partyId=662" title="Click here to view  PRP profile-previous election result, Updated News, Photos ,Videos">PRP</a></td>-->
   <td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(173,'Assembly',2009)" title="Click here to view Narsapur Constituency election results">View Complete Details</a></td>
   </tr>
   
   <tr><td ><a href="constituencyPageAction.action?constituencyId=176" title="Click here to View Polavaram Constituency Details, Previous Election Results, Voting Trendz" >Polavaram</a></td>
   <td><a href="districtPageAction.action?districtId=15&districtName=West Godavari"title="Click here to view WestGodavari District page">West Godavari</a></td>
   <td ><a href="#" >Nupa Parvathi</a></td>
   <td ><a id='TELLAM  BALA RAJU' href="candidateElectionResultsAction.action?candidateId=2092" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Tellam Bala Raju profile-previous election result, Updated News, Photos ,Videos" >Tellam Bala Raju</td>
   <td ><a href="#" >Mudiyam Srinivas</a></td>
    <!--<td > <a id='PUNEM SINGANNA DORA' href="candidateElectionResultsAction.action?candidateId=2093" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Punem  Singanna Dora  profile-previous election result, Updated News, Photos ,Videos" >Punem Singanna Dora</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
  <td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(176,'Assembly',2009)" title="Click here to view Polavaram Constituency election results">View Complete Details</a></td>
   </tr>
   <tr>
   <td ><a href="constituencyPageAction.action?constituencyId=140" title="Click here to View Payakaraopet Constituency Details, Previous Election Results, Voting Trendz" >Payakaraopet (SC)</a></td>
   <td><a href="districtPageAction.action?districtId=13&districtName=Visakhapatnam" title="Click here to view Visakhapatnam District page">Visakhapatnam</a></td>
   <td ><a id='SUMANA GANTELA' 
   href="candidateElectionResultsAction.action?candidateId=3842" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Sumana Gantela profile-previous election result, Updated News, Photos ,Videos">Sumana Gantela</a></td>
   <td ><a id='GOLLA  BABURAO' 
   href="candidateElectionResultsAction.action?candidateId=1693" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Golla Babu Rao profile-previous election result, Updated News, Photos ,Videos">Golla Babu Rao</td>
   <td ><a id='CHENGALA VENKATARAO' 
   href="candidateElectionResultsAction.action?candidateId=1694" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Chengala Venkata Rao profile-previous election result, Updated News, Photos ,Videos">Chengala Venkata Rao</td>

   <!--<td > <a id='CHENGALA VENKATARAO' href="candidateElectionResultsAction.action?candidateId=1694" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Chengala Venkatarao profile-previous election result, Updated News, Photos ,Videos">Chengala Venkatarao</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
   <td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(140,'Assembly',2009)" title="Click here to view Payakaraopet Constituency election results">View Complete Details</a></td>
   </tr>
   <tr>
   <td ><a href="constituencyPageAction.action?constituencyId=212" title="Click here to View Prathipadu Constituency Details, Previous Election Results, Voting Trendz" >Prathipadu (SC)</td>
   <td><a href="districtPageAction.action?districtId=17&districtName=Guntur" title="Click here to view Guntur District page">Guntur</a></td>
   <td ><a  href="#">TJR Sudhakarbabu</a></td>
   <td ><a id='Sucharitha Mekathoti' href="candidateElectionResultsAction.action?candidateId=2440" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Sucharitha Mekathoti profile-previous election result, Updated News, Photos ,Videos">Sucharitha Mekathoti</a></td>
   <td ><a id='Kandukuri Veeraiah' href="candidateElectionResultsAction.action?candidateId=2441" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Kandukuri Veeraiah profile-previous election result, Updated News, Photos ,Videos">Kandukuri Veeraiah</a></td>

   <!--<td ><a id='Kandukuri Veeraiah' href="candidateElectionResultsAction.action?candidateId=2441" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Kandukuri Veeraiah profile-previous election result, Updated News, Photos ,Videos">Kandukuri Veeraiah</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
   <td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(212,'Assembly',2009)" title="Click here to view Prathipadu Constituency election results">View Complete Details</a></td>
   </tr>
  <tr>
  <td ><a href="constituencyPageAction.action?constituencyId=246" title="Click here to View Railway Kodur Constituency Details, Previous Election Results, Voting Trendz">Railway Kodur (SC)</td>
  <td><a href="districtPageAction.action?districtId=20&districtName=Cuddapah"title="Click here to view Cuddapah District page">Cuddapah</a></td>
  <td ><a href="#">Easwaraiah</a></td>
  <td ><a id='KORAMUTLA SRINIVASULU' href="candidateElectionResultsAction.action?candidateId=2887" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Koramutla Srinivasulu  profile-previous election result, Updated News, Photos ,Videos">Koramutla Srinivasulu</a></td>
  <td ><a id='Ajay Babu Nandavaram Benjiman' href="candidateElectionResultsAction.action?candidateId=2888" onmouseover="displayImage(this.id);" onmouseout="return nd();"
	title="click here to view Ajay Babu Nandavaram Benjiman profile-previous election result, Updated News, Photos ,Videos"
	>Ajay Babu Nandavaram Benjiman</a></td>

  <!--<td ><a id='Ajay Babu Nandavaram Benjiman' href="candidateElectionResultsAction.action?candidateId=2888" onmouseover="displayImage(this.id);" onmouseout="return nd();"
title="click here to view Ajay Babu Nandavaram Benjiman profile-previous election result, Updated News, Photos ,Videos"
>Ajay Babu Nandavaram Benjiman</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view  TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
  <td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(246,'Assembly',2009)" title="Click here to view Railway Kodur Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=252" title="Click here to View Rajampet Constituency Details, Previous Election Results, Voting Trendz" >Rajampet</td>
<td><a href="districtPageAction.action?districtId=20&districtName=Cuddapah"title="Click here to view Cuddapah District page">Cuddapah</a></td>
<td><a href="#">Meda Mallikarjun Reddy</a></td>
<td><a id='AMARANATH REDDY  AKEPATI' href="candidateElectionResultsAction.action?candidateId=2862" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Akepati Amarnath Reddy profile-previous election result, Updated News, Photos ,Videos">Amarnath Reddy</a></td>
<td><a id='BRAHMAIAH  PASUPULETI' href="candidateElectionResultsAction.action?candidateId=4472" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Brahmaiah  Pasupuleti profile-previous election result, Updated News, Photos ,Videos">Brahmaiah  Pasupuleti</td>
<!--<td><a id='KASIREDDI MADAN MOHAN REDDY' href="candidateElectionResultsAction.action?candidateId=2863" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Madan Mohan Reddy K profile-previous election result, Updated News, Photos ,Videos"
>Kasireddi Madan Mohan Reddy </a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
<td><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(252,'Assembly',2009)" title="Click here to view Rajampet Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=248" title="Click here to View Rayachoti Constituency Details, Previous Election Results, Voting Trendz">Rayachoti</td>
<td><a href="districtPageAction.action?districtId=20&districtName=Cuddapah" title="Click here to view Cuddapah District page">Cuddapah</a></td>
<td ><a href="#">Ramprasad Reddy</a>

</td>
<td ><a id='GADIKOTA  SRIKANTH REDDY' 
href="candidateElectionResultsAction.action?candidateId=3493" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Gadikota Srikanth Reddy profile-previous election result, Updated News, Photos ,Videos">Gadikota Srikanth Reddy</a></td>
<td ><a href="#">Balasubrahmanyam</a></td>
<!--<td ><a id='SUGAVASI PALAKONDRAYUDU' href="candidateElectionResultsAction.action?candidateId=3494" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Sugavasi Palakondrayudu  profile-previous election result, Updated News, Photos ,Videos" >Sugavasi Palakondrayudu</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(248,'Assembly',2009)" title="Click here to view Rayachoti Constituency election results">View Complete Details</a></td>
</tr>


   <tr>
<td ><a href="constituencyPageAction.action?constituencyId=298" title="Click here to View Ananthapur Constituency Details, Previous Election Results, Voting Trendz" >Ananthapur</td>
<td><a href="districtPageAction.action?districtId=22&districtName=Anantapur"title="Click here to view Anantapur District page">Anantapur</a></td>
<td ><a  href="#">Musheera Begum</a></td>
<td ><a id='B.Gurunatha Reddy' href="candidateElectionResultsAction.action?candidateId=3434" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to B.Gurunatha Reddy profile-previous election result, Updated News, Photos ,Videos">B.Gurunatha Reddy</a></td>
<td ><a id='Mahalakshmi Sreenivasulu' href="candidateElectionResultsAction.action?candidateId=3435" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Mahalakshmi Sreenivasulu  profile-previous election result, Updated News, Photos ,Videos">Mahalakshmi Sreenivasulu</td>
<!--<td ><a id='Mahalakshmi Sreenivasulu' href="candidateElectionResultsAction.action?candidateId=3435" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Mahalakshmi Sreenivasulu  profile-previous election result, Updated News, Photos ,Videos">Mahalakshmi Sreenivasulu</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(298,'Assembly',2009)" title="Click here to view Ananthapur Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=276" title="Click here to View Rayadurg Constituency Details, Previous Election Results, Voting Trendz">Rayadurg</td>
<td><a href="districtPageAction.action?districtId=22&districtName=Anantapur" title="Click here to view Anantapur District page">Anantapur</a></td>
<td >
<a id='PATIL VENUGOPAL REDDY' 
href="candidateElectionResultsAction.action?candidateId=8955" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Patil Venugopal Reddy profile-previous election result, Updated News, Photos ,Videos">Patil Venugopal Reddy</a>
</td>
<td ><a id='KAPU  RAMACHANDRA REDDY' 
href="candidateElectionResultsAction.action?candidateId=3462" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Kapu Ramachandra Reddy  profile-previous election result, Updated News, Photos ,Videos">Kapu Ramchandra Reddy</a></td>
<td ><a  href="#">Deepak Reddy</a></td>

<!--<td ><a id='METTU GOVINDA REDDY' href="candidateElectionResultsAction.action?candidateId=3463" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  Mettu Govinda Reddy  profile-previous election result, Updated News, Photos ,Videos" >Mettu Govinda Reddy</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(276,'Assembly',2009)" title="Click here to view Rayadurg Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=265" title="Click here to View Yemmiganuru Constituency Details, Previous Election Results, Voting Trendz" >Yemmiganuru</td>
<td><a href="districtPageAction.action?districtId=21&districtName=Kurnool" title="Click here to view Kurnool District page">Kurnool</a></td>
<td ><a  href="#">Rudra Goud</a></td>
<td ><a id='K. Chenna Kesava Reddy' href="candidateElectionResultsAction.action?candidateId=4658" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view K.Chenna Kesava Reddy  profile-previous election result, Updated News, Photos ,Videos">K.Chenna kesava Reddy</a></td>
<td ><a id='B V MOHAN REDDY' href="candidateElectionResultsAction.action?candidateId=3111" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  B V Mohan Reddy profile-previous election result, Updated News, Photos ,Videos">B V Mohan Reddy</a></td>
<!--<td ><a id='B V MOHAN REDDY' href="candidateElectionResultsAction.action?candidateId=3111" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  B V Mohan Reddy profile-previous election result, Updated News, Photos ,Videos">B V Mohan Reddy</a> - <a href="partyPageAction.action?partyId=872" title="Click here to view TDP profile-previous election result, Updated News, Photos ,Videos">TDP</a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(265,'Assembly',2009)" title="Click here to view Yemmiganuru Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=254" title="Click here to View Allagadda Constituency Details, Previous Election Results, Voting Trendz" >Allagadda</td>
<td><a href="districtPageAction.action?districtId=21&districtName=Kurnool" title="Click here to view Kurnool District page">Kurnool</a></td>
<td> <a id='GANGULA PRATHAPA REDDY' href="candidateElectionResultsAction.action?candidateId=2966" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  Gangula Prathapa Reddy profile-previous election result, Updated News, Photos ,Videos">Gangula Pratapa Reddy</a>

</td>
<td ><a id='BHUMA  SHOBHA NAGI REDDY' href="candidateElectionResultsAction.action?candidateId=2965" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Bhooma Shobha Nagireddy  profile-previous election result, Updated News, Photos ,Videos">Bhuma Shobha Reddy</a></td>
<td ><a id='ERIGELA  RAMPULLAREDDY' href="candidateElectionResultsAction.action?candidateId=2967" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view Erigela  Rampulla Reddy profile-previous election result, Updated News, Photos ,Videos">Erigela  Rampulla Reddy</a></td>
<!--<td ><a id='GANGULA PRATHAPA REDDY' href="candidateElectionResultsAction.action?candidateId=2966" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  Gangula Prathapa Reddy profile-previous election result, Updated News, Photos ,Videos">Gangula Prathapa Reddy</a> - <a href="partyPageAction.action?partyId=362" title="Click here to view INC profile-previous election result, Updated News, Photos ,Videos">INC</a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(254,'Assembly',2009)" title="Click here to view Allagadda Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=94" title="Click here to View Parkal Constituency Details, Previous Election Results, Voting Trendz" >Parkal</td>
<td><a href="districtPageAction.action?districtId=9&districtName=Warangal" title="Click here to view Warangal District page">Warangal</a></td>
<td ><a  href="#">Sambari Samma Rao</a>
<td ><a id='KONDA SUREKHA' href="candidateElectionResultsAction.action?candidateId=1202" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Konda Surekha   profile-previous election result, Updated News, Photos ,Videos">Konda Surekha</a></td>
<td ><a  href="#">Dharma Reddy</a></td>

<!--<td ><a id='Bikshapathy Moluguri' href="candidateElectionResultsAction.action?candidateId=1203" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  Bikshapathy Moluguri profile-previous election result, Updated News, Photos ,Videos">Bikshapathy Moluguri</a> - <a href="partyPageAction.action?partyId=886" title="Click here to view TRS profile-previous election result, Updated News, Photos ,Videos">TRS</a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(94,'Assembly',2009)" title="Click here to view Parkal Constituency election results">View Complete Details</a></td>
</tr>
<tr>
<td ><a href="constituencyPageAction.action?constituencyId=291" title="Click here to View Tirupathi Constituency Details, Previous Election Results, Voting Trendz" >Tirupathi</td>
<td><a href="districtPageAction.action?districtId=23&districtName=Chittoor" title="Click here to view Warangal District page">Chittor </a></td>
<td ><a id='M VENKATA RAMANA' href="candidateElectionResultsAction.action?candidateId=4461" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to M Venkata Ramana profile-previous election result, Updated News, Photos ,Videos">M Venkata Ramana</td>
<td ><a id='KARUNAKAR REDDY  BHUMANA' href="candidateElectionResultsAction.action?candidateId=3348" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Karunakar Reddy  Bhumana profile-previous election result, Updated News, Photos ,Videos">Karunakar Reddy  Bhumana</a></td>
<td ><a id='CHADALAVADA KRISHNA MURTHY' href="candidateElectionResultsAction.action?candidateId=8398" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to Chadalavada Krishna murthy profile-previous election result, Updated News, Photos ,Videos">Chadalavada Krishna murthy</a></td>
<!--<td ><a id='Bikshapathy Moluguri' href="candidateElectionResultsAction.action?candidateId=1203" onmouseover="displayImage(this.id);" onmouseout="return nd();" title="click here to view  Bikshapathy Moluguri profile-previous election result, Updated News, Photos ,Videos"></a><a href="partyPageAction.action?partyId=886" title="Click here to view TRS profile-previous election result, Updated News, Photos ,Videos"></a></td>-->
<td ><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(291,'Assembly',2009)" title="Click here to view Parkal Constituency election results">View Complete Details</a></td>
</tr>
</tbody></table>

<div style="margin-left: 610px; margin-top: 20px; margin-bottom: 0px;">
<span id="imageId"><img title="Click Here To Expand" style="" src="images/icons/plusImg .png" onclick="showVotesInfo('votesInfoId')"></img></span></div>

<div style="margin-left: 10px; margin-bottom: 13px; padding-bottom: 19px; margin-top: -21px;">
<span style="background: none repeat scroll 0 0 #ED5B21;border-radius: 5px 5px 5px 5px;color: #FFFFFF;font-family: verdana;font-size: 13px;font-weight: bold;margin-bottom: 20px;margin-left: 0;padding: 5px;cursor:pointer;"  onclick="showVotesInfo('votesInfoId')">View 2012 Andhra Pradesh Bi Election Results Completed In 7 Constituencies</span>
</div>
<table width="100%" class="resulttableclass" style="margin-top:9px;display:none;margin-bottom:10px;" id="votesInfoId">
  <tr>
    <th style="background-color:#21B2ED;color:#fff;">Constituency</th>
    <th>Total Voters</th>
    <th>Polled Votes</th>
	<th>TRS</th>
	<th>INC</th>
	<th>TDP</th>
    <th>YSRC</th>
	<th>BJP</th>
	<th>IND</th>
	<th>Majority in 2012</th>
	<th>Majority in 2009</th>
   </tr>
  <tr>
    <th><a href="constituencyPageAction.action?constituencyId=1">Adilabad</a></th>
    <td>172045</td>
    <td>120450</td>
	<td class="winTD">59452</td>
    <td>28056</td>
	<td>24288</td>
    <td></td>
	<td></td>
    <td></td>
	<td>31396</td>
   	<td>25580</td>
  </tr>
  <tr>
    <th><a href="constituencyPageAction.action?constituencyId=16">Kamareddy</a></th>
    <td>203111</td>
	<td>137051</td>
    <td class="winTD">75699</td>
	<td>31234</td>
    <td>17839</td>
	<td></td>
    <td></td>
	<td></td>
    <td>44465</td>
	<td>47708</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=67">Kollapur</a></th>
    <td>195798</td>
    <td>151217</td>
	<td class="winTD">58107</td>
    <td>43083</td>
	<td>35287</td>
    <td></td>
	<td></td>
    <td></td>
	<td>15024</td>
    <td>1508</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=233">Kovur</a></th>
    <td>209626</td>
    <td>175912</td>
	<td></td>
    <td>41397</td>
	<td>50382</td>
    <td class="winTD">&nbsp;&nbsp;73876&nbsp;&nbsp;</td>
	<td></td>
    <td></td>
	<td>23494</td>
    <td>7444</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=68">Mahabubnagar</a></th>
	<td>188156</td>
    <td>124706</td>
	<td>37506</td>
    <td>25333</td>
	<td>17518</td>
    <td></td>
	<td class="winTD">&nbsp;&nbsp;39385&nbsp;&nbsp;</td>
    <td></td>
	<td>1879</td>
    <td>--</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=70">Nagarkurnool</a></th>
    <td>191379</td>
    <td>140394</td>
	<td></td>
    <td>43676</td>
	<td>18608</td>
    <td></td>
	<td></td>
    <td class="winTD">&nbsp;&nbsp;71001&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;27325&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;6593&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <th><a href="constituencyPageAction.action?constituencyId=87">Station Ghanpur</a></th>
    <td>221451</td>
    <td>169911</td>
	<td class="winTD">81279</td>
    <td>28965</td>
	<td>48641</td>
    <td></td>
	<td></td>
    <td></td>
	<td>32638</td>
    <td>11210</td>
  </tr>
</table>
</div></div>

<script type="text/javascript">

function showVotesInfo(tableId)
{
	
	var imageIdElmt = document.getElementById("imageId");
	var str = '';
	
	if(document.getElementById("votesInfoId").style.display == 'block')
	{
		$("#votesInfoId").slideToggle("slow");

	   document.getElementById("votesInfoId").style.display = 'none';
	   str += '<img title="Click Here To Expand" style="" src="images/icons/plusImg .png" onclick="showVotesInfo()"></img>';
	}
	else if(document.getElementById("votesInfoId").style.display == 'none')
	{
		$("#votesInfoId").slideToggle("slow");

	   document.getElementById("votesInfoId").style.display = 'block';

	   str += '<img title="Click here to hide Table" style="" src="images/icons/minusImage.png" onclick="showVotesInfo()"></img>';
	}
	
	
	imageIdElmt.innerHTML = str;

}

function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
	{
	   
	   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   browser1.focus();
	}
	/*function displayImage(candidateName){
		overlib('<div><img style="height:115px;width:115px;" src="images/candidates/'+candidateName+'.jpg" alt="'+candidateName+' Image not available"></img></div>');
     }*/

	

	 function byeEleParticipatedCandidateInfo()
	 {
		var str = '';
		var byeEleInfo = document.getElementById("byeElePaticipatedCandInfoSpan");
		 if(document.getElementById("byeEleParticipatedCandidatetableId").style.display == 'none') 
		 {
		 
			$("#byeEleParticipatedCandidatetableId").slideToggle("slow");
			document.getElementById("byeEleParticipatedCandidatetableId").style.display = 'block';
			str += '<img id="minusImageId" title="Click here to Hide the Table" style="" src="images/icons/minusImage.png" onclick="byeEleParticipatedCandidateInfo()"></img>';
		 }
		 else if(document.getElementById("byeEleParticipatedCandidatetableId").style.display == 'block')
		 {
			 
			$("#byeEleParticipatedCandidatetableId").slideToggle("slow");
			document.getElementById("byeEleParticipatedCandidatetableId").style.display = 'none';
			str += '<img id="plusImgId" title="Click Here To View Andhra Pradesh Bye Election Participating Candidates" style="" src="images/icons/plusImg .png" onclick="byeEleParticipatedCandidateInfo()"></img>';
		 }
		byeEleInfo.innerHTML = str;
	 }

	
</script>