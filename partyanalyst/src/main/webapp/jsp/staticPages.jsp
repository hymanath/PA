<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="description" content="This page gives Exit Polls 2011 Results For Tamilnadu,West Bengal,Assam,Kerala,Pudducherry,Kadapa,Pulivendula Provided by Different Media">

<meta name="keywords" content="Tamilnadu,Assam,Kerala,Pududcherry,West Bengal Updated and latest Results 2011 , Exit Polls 2011,Previous Election Reults,Tamilnadu 2011 results,West Bengal 2011 results,Assam 2011 results,Kerala 2011 results,Pudducherry 2011 results ,Kadapa 2011 Election Results,Pulivendula 2011 Election Results,Indian Elections, Election Analysis,Andhra Pradesh Politics,Know Analyze, Act, MLA Elections, MP Elections, Cross Voting, District Election Results,Constituency Management, Cadre Management, Party Performance, Election Comparison">

<meta name="copyright" content="IT Grids (India) Pvt. Ltd.">

<meta name="author" content="Ashok Dakavaram">

<meta name="email" content="info@itgrids.com">

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script src="http://www.google.com/uds/api?file=uds.js&v=1.0"
    type="text/javascript"></script>
<link href="http://www.google.com/uds/css/gsearch.css"
    rel="stylesheet" type="text/css"/>
<script src="http://www.google.com/uds/solutions/videobar/gsvideobar.js"
    type="text/javascript"></script>
		
<link href="http://www.google.com/uds/solutions/videobar/gsvideobar.css"
    rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<link rel="stylesheet" type="text/css" href="styles/cadreManagement/cadreManagement.css">
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

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
<!-- YUI Dependency files (End) -->


<script type="text/javascript" src="js/candidatePage/candidatePage.js"></script>
<script type="text/javascript" src="js/candidatePage/carousel.js"></script>
<script type="text/javascript" src="js/SWFObject/swfobject.js" ></script>
<script type="text/javascript">
		google.load("elements", "1", {packages : ["newsshow"]});

</script>


<title>Exit Polls 2011 And Previous Election Reults For Tamilnadu,West Bengal,Assam,Kerala,Pudducherry,Kadapa 2011 Election Results,Pulivendula 2011 Election Results </title>
<style>
#exitPollStyle{

	background-color: #FFFCFC;
    border: 4px solid appworkspace;
    text-align: center;
	width:75%;
	
}
#headerStyle{
font-size: 17px;
padding: 22px;
color: navy;
text-decoration: underline;
}
#statesCheckBoxes{
color: green;
font-weight: bold;
font-size: 14px;
}
#tdStyles{
text-align: center;
font-weight: bold;
font-size: 14px;
color: navy;
padding-top: 20px;
}

.productFeatureBody {
    background-color: #FFFFFF;
    border-left: 2px solid #D8D8D8;
    border-right: 2px solid #D8D8D8;
	border-top: 2px solid #D8D8D8;
    border-bottom: 2px solid #D8D8D8;
    color: #12283A;
    line-height: 16px;
    padding: 5px;
    text-align: justify;
}

.newsHeader
{
	font-weight: bold;
	font-size: 14px;
	color: navy;
	padding-top: 20px;
}
</style>
<script type="text/javascript">

function buildCandidateNews(stateName)
{
	var options = {
		"format" : "300x250",
		"queryList" : [
			  {
				"title" : stateName+'  Assembly Elections 2011',
				"q" : stateName+"Assembly Elections 2011, India"
			  }
		 ],
		"linkTarget" : "_blank"
	  }
	
	var content = document.getElementById('newsDiv');
	content.innerHTML = '';
	
	var newsShow = new google.elements.NewsShow(content, options);
}

function buildCandidateNews2(stateName)
{
	var options = {
		"format" : "300x250",
		"queryList" : [
			  {
				"title" : stateName+' News',
				"q" : stateName+"Elections , India"
			  }
		 ],
		"linkTarget" : "_blank"
	  }
	
	var content = document.getElementById('newsDiv2');
	content.innerHTML = '';
	
	var newsShow = new google.elements.NewsShow(content, options);
}

function buildCandidateVideoGallery()
{
	

	var value="http://www.youtube.com/v/8jhiIWiCch8?enablejsapi=1&playerapiid=ytplayer&rel=1&color1=0x2b405b&color2=0x6b8ab6&border=1&fs=1";
	var params = { allowScriptAccess: "always" };	 
	var atts = { id: "myytplayer" };
	swfobject.embedSWF(value, "videoBarOne", "500", "300", "8", null, null, params,atts);
	var elmtId = document.getElementById( "videoBarOne");
  
}

function LoadVideoBar(stateName)
{
	var vbl;
	var vbr;

	var searchString1 = stateName+'Assembly Election 2011 ';
		

	var options = {
	  largeResultSet : true,
		  horizontal:true
		  
			}
	vbl = new GSvideoBar(
				document.getElementById("videoBarOne"),
				document.getElementById("ytVideoPlayer"),
				options
				);
	

	
	vbl.execute(searchString1);
	
}


function onYouTubePlayerReady(playerId)
{
	var ytplayer = document.getElementById("myytplayer");
	//ytplayer.cueVideoById(videoId:, startSeconds:Number, suggestedQuality:String):Void
}


function tamilnaduExitPollAnd2006ElectionDetails(){

	LoadVideoBar("Tamilnadu");
	buildCandidateNews("Tamilnadu");
	buildCandidateNews2("Tamilnadu");
    var str =' ';
	var cadreGroupsHeadDivElmt = document.getElementById("cadreGroupsHeadDiv");
	str += '<font size="3px">Tamilnadu Latest Election Results 2011</font>';
	cadreGroupsHeadDivElmt.innerHTML = str;

	var latestResultsDivElmt =document.getElementById("latestResultsDiv");
    var str =' ';
	str +='<table style="width: 94%; text-align: center;">';
	str +='<tr style="background-color:white;color:Chocolate;font-family:times New Roman;font-size:19px;">';
	str +='<th>Party</th>';
	str +='<th>No of seats</th>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>AIADMK</th>';
	str +='<td>150</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>DMK</th>';
	str +='<td>23</td>';
	str +='</tr>';
		str +='<tr>';
	str +='<th>CPM</th>';
	str +='<td>10</td>';
	str +='</tr>';
		str +='<tr>';
	str +='<th>Congress</td>';
	str +='<td>5</td>';
	str +='</tr>';
		str +='<tr>';
	str +='<th>CPI</th>';
	str +='<td>9</td>';
	str +='</tr>';
		str +='<tr>';
	str +='<th>PMK</th>';
	str +='<td>3</td>';
	str +='</tr>';
		str +='<tr>';
	str +='<th>AIFB</th>';
	str +='<td>1</td>';
	str +='</tr>';
		str +='<tr>';
	str +='<th>OTHERS</th>';
	str +='<td>33</td>';
	str +='</tr>';
	latestResultsDivElmt.innerHTML = str;
	var TNexitPollsDivelmt = document.getElementById("TNexitPollsDiv");
	str ='';
	str +=' <div id="tdStyles" style="margin-top:5px;"><b>TamilNadu Exit Polls Provided By Different Media</b></div>';
	str +='<div id="exitPollStyle"><table  cellspacing="2" cellpadding="2">';
	str +=' <tr> ';
	str +=' <th> Party </th>';
	str +='<th> No of seats expected<br>(IBN TV) </th>';
	str +='<th> No of seats expected <br>(AllOverWorld.com) </th>';
	str +='<th> No of seats expected<br> (Junior Vikatan) </th>';
	str +='<th> No of seats expected<br> (STAR TV) </th>';
	str +='<th> No of seats expected<br> (Malayalam Manorama) </th>';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> DMK & Alliances</th> ';
	str +=' <td> 149 </td> ';
	str +=' <td> 50 </td> ';
	str +=' <td> 89 </td> ';
	str +=' <td> 124 </td> ';
	str +=' <td> 110-112 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> AIADMK & Alliances</th> ';
	str +=' <td> 64 </td> ';
	str +=' <td> 171 </td> ';
	str +=' <td> 137 </td> ';
	str +=' <td> 110 </td> ';
	str +=' <td> 120-132 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> MDMK </th> ';
	str +=' <td> 10 </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> ---</td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> PCK </th> ';
	str +=' <td> 9 </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> --- </td> ';
	str +=' </tr> ';
	str +='</div>';
	str +='</table>';
  
  TNexitPollsDivelmt.innerHTML = str;

	var TNPreviousPollsDivElmt = document.getElementById("TNPreviousPollsDiv");
	var str= '';
		str += ' <br><br> <div style=" color: Navy; font-size: 14px; text-align:left;margin-left:200px;"><b>Tamilnadu  PartyWise Election Results in 2006</b>';
		str += '</div>';
		str += '<table><tr>';
		str += '  <td style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/TN/TN.png" /></td>';
		str += '<td><div>';
		str += '<div style="padding:10px;"><a href="statePageAction.action?stateId=24">View More Details About Tamilnadu State</a></div>';
		str += '<div style="padding:10px;"><a href="electionDetailsReportAction.action?electionId=10&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 Tamilnadu Election Results</a></div>';
		str += '<div style="padding:10px;"><a href="electionDetailsReportAction.action?electionId=10&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 Tamilnadu Election Results</a></div>';
		str += '</div></td></tr></table>';
		
		str += '<div style="margin:10px;">';
		str += '<table cellspacing="2px" cellpadding="2px">';
		str += '<tr><td id="tdStyles">Tamilnadu 2006 DPA Alliance Results  </td>';
		str += '<td id="tdStyles">Tamilnadu 2006 DPF Alliance Results</td></tr>';
		str += '<tr><td><img src="<%=request.getContextPath()%>/images/TN/TamilnaduAssemblyDPAAEC.png" /></td>';
		str += '<td valign="top"><img src="<%=request.getContextPath()%>/images/TN/TamilnaduAssemblyDPFAEC.png" /></td></tr>';
		str += '</table></div>';

		str += '<table>';
		str += '<tr><td id="tdStyles">Tamilnadu 2006 DPA Alliance Results  graph</td><td id="tdStyles">Tamilnadu 2006 DPF Alliance Results  graph</td></tr>';
		str += '<tr>';
		str += '<td><img src="<%=request.getContextPath()%>/images/TN/DPAGraph.png" style="width: 465px;" /></td>';
		str += '<td><img src="<%=request.getContextPath()%>/images/TN/DPFallianceGraph.png"           style="width: 465px;"/></td>';
		str += '</tr>';
		str += '</table>';

   TNPreviousPollsDivElmt.innerHTML =str;
}

function westBengalExitPollAnd2006ElectionDetails(){

	LoadVideoBar("West Bengal");
    buildCandidateNews("West Bengal");
	buildCandidateNews2("West Bengal");
	var cadreGroupsHeadDivElmt = document.getElementById("cadreGroupsHeadDiv");
	var str ='';
	str += '<font size="3px">West Bengal Latest Election Results 2011</font>';
	cadreGroupsHeadDivElmt.innerHTML = str;

	var latestResultsDivElmt =document.getElementById("latestResultsDiv");
    var str =' ';
	str +='<table style="width: 94%; text-align: center;">';
	str +='<tr style="background-color:white;color:Chocolate;font-family:times New Roman;font-size:19px;">';
	str +='<th>Party</th>';
	str +='<th>No of seats</th>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>AITC</th>';
	str +='<td>184</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>CPM</th>';
	str +='<td>40</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>Congress</td>';
	str +='<td>42</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>CPI</th>';
	str +='<td>2</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>AIFB</th>';
	str +='<td>11</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>RCP</th>';
	str +='<td>7</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>SP</th>';
	str +='<td>1</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>OTHERS</th>';
	str +='<td>7</td>';
	str +='</tr>';
	latestResultsDivElmt.innerHTML = str;
	var TNexitPollsDivelmt = document.getElementById("TNexitPollsDiv");
	var str =' ';
	str +=' <div id="tdStyles" style="margin-top:5px;"><b>West Bengal Exit Polls Provided By Different Media</b></div>';
	str +='<div id="exitPollStyle"><table  cellspacing="2" cellpadding="2">';
	str +=' <tr> ';
	str +=' <th> Party </th>';
	str +='<th> No of seats expected<br>(CVB NEWS) </th>';
	str +='<th> No of seats expected <br>(CNN-IBN) </th>';
	str +='<th> No of seats expected<br> (STAR NEWS) </th>';
	str +='<th> No of seats expected<br> (Today Headlines) </th>';
	str +='<th> No of seats expected<br> (News 365 Today) </th>';
	str +='<th> No of seats expected<br> (Today ORG) </th>';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> AITC Alliances </th> ';
	str +=' <td> 223-239 </td> ';
	str +=' <td> 222-234 </td> ';
	str +=' <td> 225 </td> ';
	str +=' <td> 182 </td> ';
	str +=' <td> 220-240 </td> ';
	str +=' <td> 210-220 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> Left Front </th> ';
	str +=' <td> 48-56 </td> ';
	str +=' <td> 60-72 </td> ';
	str +=' <td> 60 </td> ';
	str +=' <td> 101 </td> ';
	str +=' <td> 55-70 </td> ';
	str +=' <td> 65-70 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> OTHERS </th> ';
	str +=' <td> --- </td> ';
	str +=' <td> 3-5 </td> ';
	str +=' <td> 4-6 </td> ';
	str +=' <td> 11 </td> ';
	str +=' <td> 5-7 </td> ';
	str +=' <td> 5-9 </td> ';
	str +=' </tr> ';
	str +='</div>';
	str +='</table>';
  
  TNexitPollsDivelmt.innerHTML = str;

	var TNPreviousPollsDivElmt = document.getElementById("TNPreviousPollsDiv");
	var str= '';
		str += ' <br><br> <div style=" color: Navy; font-size: 14px;"><b>West Bengal  PartyWise Election Results in 2006</b>';
		str += '</div>';
		str += '<table><tr>';
		str += '  <td  style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/WB/WBAssemblypartywise1.png" /></td>';
		str += '<td valign="top" style="text-align: left; padding: 13px;">';
		str += '<div><img src="<%=request.getContextPath()%>/images/WB/WBassemblypartywise-2ndpage1.png" /></div><BR><BR><div> ';
		str += '<div style="padding:10px"><a href="statePageAction.action?stateId=28">View More Details About West Bengal State</a></div>';
		str += '<div style="padding:10px"><a href="electionDetailsReportAction.action?electionId=31&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 West Bengal Election Results</a></div>';
		str += '<div style="padding:10px"><a href="electionDetailsReportAction.action?electionId=31&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 West Bengal Election Results</a></div>';
		str += '</div></td></tr>';
		str += '</table>';

		str += '<table cellspacing="2px" cellpadding="2px">';
		str += '<tr><td id="tdStyles">West Bengal 2006 LEFT Front Alliance Results</td><td id="tdStyles">West Bengal 2006 TC+ Alliance Results</td></tr>'
		str += '<tr><td><img src="<%=request.getContextPath()%>/images/WB/WBLFalliance.png"/></td>';
		str += '<td valign="top"><img src="<%=request.getContextPath()%>/images/WB/WBTC+alliance.png"/></td></tr>';
		str += '</table>';

		str += '<table cellspacing="2px" cellpadding="2px">';
		str += '<tr><td id="tdStyles">West Bengal 2006 LF Alliance Results graph</td><td id="tdStyles">West Bengal 2006 TC+ Alliance Results graph</td></tr>';
		str += '<tr>';
		str += '<td><img src="<%=request.getContextPath()%>/images/WB/LFallianceGraph.png" style="width:470px" /></td>';
		str += '<td><img src="<%=request.getContextPath()%>/images/WB/TC+alliancegraph.png"           style="width:470px"/></td>';
		str += '</tr>';
		str += '</table>';

   TNPreviousPollsDivElmt.innerHTML =str;
}

function assamExitPollAnd2006ElectionDetails(){
	
	LoadVideoBar("Assam");
    buildCandidateNews("Assam");
	buildCandidateNews2("Assam");
	var cadreGroupsHeadDivElmt = document.getElementById("cadreGroupsHeadDiv");
	var str = '';
	str += '<font size="3px">Assam Latest Election Results 2011</font>';
	cadreGroupsHeadDivElmt.innerHTML = str;

	var latestResultsDivElmt =document.getElementById("latestResultsDiv");
    var str =' ';
	str +='<table style="width: 94%; text-align: center;">';
	str +='<tr style="background-color:white;color:Chocolate;font-family:times New Roman;font-size:19px;">';
	str +='<th>Party</th>';
	str +='<th>No of seats</th>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>Congress</th>';
	str +='<td>78</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>AIUDF</th>';
	str +='<td>18</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>BDF</td>';
	str +='<td>12</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>AGP</th>';
	str +='<td>10</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>BJP</th>';
	str +='<td>5</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>AITC</th>';
	str +='<td>1</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>OTHERS</th>';
	str +='<td>2</td>';
	str +='</tr>';
	latestResultsDivElmt.innerHTML = str;
	var TNexitPollsDivelmt = document.getElementById("TNexitPollsDiv");
	var str =' ';
	str +=' <div id="tdStyles" style="margin-top:5px;"><b>Assam  Exit Polls Provided By Different Media</b></div>';
	str +='<div id="exitPollStyle"><table  cellspacing="2" cellpadding="2">';
	str +=' <tr> ';
	str +=' <th> Party </th>';
	str +='<th> No of seats expected<br>(infoelection.com) </th>';
	str +='<th> No of seats expected <br>(CVB NEWS) </th>';
	str +='<th> No of seats expected<br> (CNN-IBN) </th>';
	str +='<th> No of seats expected<br> (Today Chanakya) </th>';
	str +='<th> No of seats expected<br> (HeadLine Today) </th>';
	str +='<th> No of seats expected<br> (C Voter) </th>';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> INC </th> ';
	str +=' <td> 52 </td> ';
	str +=' <td> 41-45 </td> ';
	str +=' <td> 64-72 </td> ';
	str +=' <td> 70-73 </td> ';
	str +=' <td> 42-48 </td> ';
	str +=' <td> 41-45 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> AUDF </th> ';
	str +=' <td> 25 </td> ';
	str +=' <td> 11-15 </td> ';
	str +=' <td> 11-15 </td> ';
	str +=' <td> 9-11 </td> ';
	str +=' <td> 13-15 </td> ';
	str +=' <td> 10-15 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> BJP </th> ';
	str +=' <td> 19 </td> ';
	str +=' <td> 11-18 </td> ';
	str +=' <td> 7-11 </td> ';
	str +=' <td> 8-13 </td> ';
	str +=' <td> 16-18 </td> ';
	str +=' <td> 14-18 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> AGP </th> ';
	str +=' <td> 16 </td> ';
	str +=' <td> 31-35 </td> ';
	str +=' <td> 16-22 </td> ';
	str +=' <td> 20-29 </td> ';
	str +=' <td> 31-35 </td> ';
	str +=' <td> 31-35 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> BOPF </th> ';
	str +=' <td> 10 </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> 10-14 </td> ';
	str +=' <td> 10-12 </td> ';
	str +=' <td> 8-10 </td> ';
	str +=' <td> 7-11 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> OTHERS </th> ';
	str +=' <td> 4 </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> 12-20 </td> ';
	str +=' <td> --- </td> ';
	str +=' <td> 16-19 </td> ';
	str +=' <td> 10-14 </td> ';
	str +=' </tr> ';
	str +='</table>';
	str +='</div>';
  
  TNexitPollsDivelmt.innerHTML = str;

	var TNPreviousPollsDivElmt = document.getElementById("TNPreviousPollsDiv");
	var str= '';
		str += ' <br><br> <div style=" color: Navy; font-size: 14px;"><b>Assam   PartyWise Election Results in 2006</b>';
		str += '</div>';
		str += '<table><tr>';
		str += '  <td  style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/Assam/AssamAssemblyElectionResults.png" /></td></tr>';
		str += '</table>';
		str += '<div style=" color: Navy; font-size: 14px;"><b>Assam PartyWise Election Results in 2006 Graph</b></div>';
		
		str += '<table><tr>';
		str += '  <td  style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/Assam/AssamPartyResultsIn2006.png" width="820px"/></td></tr>';
		str += '</table>';
		
		str += '<table>';
		str += '<tr><td><a style="float: right;margin-right: 20px;" href="statePageAction.action?stateId=3">View More Details About Assam  State</a></td>';
		str += '<td><a style="float: right;margin-right: 20px;" href="electionDetailsReportAction.action?electionId=56&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 Assam  Election Results</a></td>';
		str += '<td><a style="float: right;margin-right: 20px;" href="electionDetailsReportAction.action?electionId=56&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 Assam  Election Results</a></td>';
		str += '</tr></table>';
		
   TNPreviousPollsDivElmt.innerHTML =str;
}

function keralaExitPollAnd2006ElectionDetails(){

	LoadVideoBar("Kerala");
    buildCandidateNews("Kerala");
	buildCandidateNews2("Kerala");
	
	var cadreGroupsHeadDivElmt = document.getElementById("cadreGroupsHeadDiv");
	 var str =' ';
	str += '<font size="3px">Kerala Latest Election Results 2011</font>';
	cadreGroupsHeadDivElmt.innerHTML = str;

	var latestResultsDivElmt =document.getElementById("latestResultsDiv");
    var str =' ';
	str +='<table style="width: 94%; text-align: center;">';
	str +='<tr style="background-color:white;color:Chocolate;font-family:times New Roman;font-size:19px;">';
	str +='<th>Party</th>';
	str +='<th>No of seats</th>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>CPI</th>';
	str +='<td>13</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>CPM</th>';
	str +='<td>45</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>INC</td>';
	str +='<td>38</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>NCP</th>';
	str +='<td>2</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>JD(S)</th>';
	str +='<td>4</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>KeralaCongress(M)</th>';
	str +='<td>9</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>MLKSC</th>';
	str +='<td>20</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>RSP</th>';
	str +='<td>2</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>OTHERS</th>';
	str +='<td>7</td>';
	str +='</tr>';
	latestResultsDivElmt.innerHTML = str;
	
	var TNexitPollsDivelmt = document.getElementById("TNexitPollsDiv");
	var str =' ';
	str +=' <div id="tdStyles" style="margin-top:5px;"><b>Kerala Exit Polls Provided By Different Media</b></div>';
	str +='<div id="exitPollStyle"><table  cellspacing="2" cellpadding="2">';
	str +=' <tr> ';
	str +=' <th> Party </th>';
	str +='<th> No of seats expected<br>(ASIANET) </th>';
	str +='<th> No of seats expected <br>(CVB-NEWS) </th>';
	str +='<th> No of seats expected<br> (CNN-IBN) </th>';
	str +='<th> No of seats expected<br> (HeadLines Today) </th>';
	str +='<th> No of seats expected<br> (Jai Hind TV) </th>';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> UDF </th> ';
	str +=' <td> 77-84 </td> ';
	str +=' <td> 83-91 </td> ';
	str +=' <td> 63-71 </td> ';
	str +=' <td> 85-92 </td> ';
	str +=' <td> 86-96 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> LDF </th> ';
	str +=' <td> 53-63 </td> ';
	str +=' <td> 49-57 </td> ';
	str +=' <td> 69-77 </td> ';
	str +=' <td> 45-52 </td> ';
	str +=' <td> 42-48 </td> ';
	str +=' </tr> ';
	str +='</table>';
	str +='</div>';
  
  TNexitPollsDivelmt.innerHTML = str;

	var TNPreviousPollsDivElmt = document.getElementById("TNPreviousPollsDiv");
	var str= '<br>';
		str += '<table>';
		str += '<tr><td width="240px"><a href="statePageAction.action?stateId=13">View More Details About Kerala State</a></td>';
		str += '<td width="240px"><a href="electionDetailsReportAction.action?electionId=21&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 Kerala Election Results</a></td>';
		str += '<td width="240px"><a href="electionDetailsReportAction.action?electionId=21&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 Kerala Election Results</a></td>';
		str += '</tr></table>';

		str += ' <br> <div style=" color: Navy; font-size: 14px;"><b>Kerala  PartyWise Election Results in 2006</b>';
		str += '</div>';
		str += '<table><tr>';
		str += '  <td  style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/Kerala/KeralaAssemblyEC.png" /></td>';
		str += '</tr>';
		str += ' <tr> <td  style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/Kerala/Keralaassembly-2ndpage.png" /></td></tr>';
		str += '</table>';

		str += '<table>';
		str += '<tr><td width="240px"><a href="statePageAction.action?stateId=13">View More Details About Kerala State</a></td>';
		str += '<td width="240px"><a href="electionDetailsReportAction.action?electionId=21&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 Kerala Election Results</a></td>';
		str += '<td width="240px"><a href="electionDetailsReportAction.action?electionId=21&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 Kerala Election Results</a></td>';
		str += '</tr></table>';
		

   TNPreviousPollsDivElmt.innerHTML =str;
}

function puducherryExitPollAnd2006ElectionDetails(){
	
	LoadVideoBar("Puducherry");
    buildCandidateNews("Puducherry");
    buildCandidateNews2("Puducherry");

	var cadreGroupsHeadDivElmt = document.getElementById("cadreGroupsHeadDiv");
	 var str =' ';
	str += '<font size="3px">Puducherry Latest Election Results 2011</font>';
	cadreGroupsHeadDivElmt.innerHTML = str;

	var latestResultsDivElmt =document.getElementById("latestResultsDiv");
    var str =' ';
	str +='<table style="width: 94%; text-align: center;">';
	str +='<tr style="background-color:white;color:Chocolate;font-family:times New Roman;font-size:19px;">';
	str +='<th>Party</th>';
	str +='<th>No of seats</th>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>ADMK+</th>';
	str +='<td>20</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>Congress</th>';
	str +='<td>7</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>DMK</th>';
	str +='<td>2</td>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>OTHERS</th>';
	str +='<td>1</td>';
	str +='</tr>';
	latestResultsDivElmt.innerHTML = str;
	

	var TNexitPollsDivelmt = document.getElementById("TNexitPollsDiv");
	var str ='';
	str +=' <div id="tdStyles" style="margin-top:5px;"><b>Puducherry  Exit Polls Provided By Different Media</b></div>';
	str +=' <table><tr><td width="450px;"> '; 
	str +='<div id="exitPollStyle"><table cellspacing="2" cellpadding="2">';
	str +=' <tr> ';
	str +=' <th> Party </th>';
	str +='<th> No of seats expected<br>(CVB-NEWS) </th>';
	str +='<th> No of seats expected <br>(HeadLines Today) </th>';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> AIADMK </th> ';
	str +=' <td> 11 </td> ';
	str +=' <td> 14 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> DMK </th> ';
	str +=' <td> 6 </td> ';
	str +=' <td> 7 </td> ';
	str +=' </tr> ';
	str +=' <tr> ';
	str +=' <th> INC </th> ';
	str +=' <td> 9 </td> ';
	str +=' <td> 8 </td> ';
	str +=' </tr> ';
	str +='</table>';
	str +='</div></td>';
	str +='<td><div>';
	str +='<div style="padding:15px;"><a style="float: right;margin-right: 20px;" href="statePageAction.action?stateId=35">View More Details About Puducherry  State</a></div>';
	str +='<div style="padding:15px;"><a style="float: right;margin-right: 20px;" href="electionDetailsReportAction.action?electionId=33&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 Puducherry  Election Results</a></div>';
	str +='<div style="padding:15px;"><a style="float: right;margin-right: 20px;" href="electionDetailsReportAction.action?electionId=33&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 Puducherry  Election Results</a></div>';
	str +='</td></tr></table>';
	  
  TNexitPollsDivelmt.innerHTML = str;

	var TNPreviousPollsDivElmt = document.getElementById("TNPreviousPollsDiv");
	var str= '';
		str += ' <br><br> <div style=" color: Navy; font-size: 14px;"><b>Puducherry PartyWise Election Results in 2006</b>';
		str += '</div>';
		str += '<table><tr>';
		str += '  <td  style="text-align: left; padding: 13px;">';
		str += '<img src="<%=request.getContextPath()%>/images/puducherry/PuducherryAssemblypartywise.png" /></td></tr>';
		str += '</table>';

		str += '<table>';
		str += '<tr><td><a style="float: right;margin-right: 20px;" href="statePageAction.action?stateId=35">View More Details About Puducherry  State</a></td>';
		str += '<td><a style="float: right;margin-right: 20px;" href="electionDetailsReportAction.action?electionId=33&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2006">Analyze 2006 Puducherry  Election Results</a></td>';
		str += '<td><a style="float: right;margin-right: 20px;" href="electionDetailsReportAction.action?electionId=33&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2001">Analyze 2001 Puducherry  Election Results</a></td>';
		str += '</tr></table>';
		

   TNPreviousPollsDivElmt.innerHTML =str;
}

</script>
</head>
<body onload="">
<div id="headerStyle">

 Latest Election Results 2011 & Exit Polls & Previous Election Results For Tamilnadu, West Bengal, Assam, Kerala, Puducherry
<br>
</div>

<div id="bannerDiv"><img src="<%=request.getContextPath()%>/images/icons/ExitPolls_banner.jpg" /></div>
<div id="statesCheckBoxes">
<table cellspacing="9px"><tr>
<td><input type="radio" name="stateRadio" onclick="tamilnaduExitPollAnd2006ElectionDetails()" checked="true">Tamilnadu</td>
<td><input type="radio" name="stateRadio" onclick="westBengalExitPollAnd2006ElectionDetails()">West Bengal</td>
<td><input type="radio" name="stateRadio" onclick="assamExitPollAnd2006ElectionDetails()">Assam</td>
<td><input type="radio" name="stateRadio" onclick="keralaExitPollAnd2006ElectionDetails()">Kerala
</td>
<td><input type="radio" name="stateRadio" onclick="puducherryExitPollAnd2006ElectionDetails()">Puducherry</td></tr></table></div>
<table>
<tr>
<td><marquee scrolldelay=100 onmouseover="this.stop()" onmouseout="this.start()"><div id="videoBarOne" ></div></marquee></td></tr></table>
<table><tr>
<!--<td><div style="padding:12px;" id="ytVideoPlayer"></div></td>-->
<td>
<div class="productFeatureMain">							
			 <div class="newsHeader"> 
				<!--<table  border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td width="10px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
						<td width="125px"><div class="districtPageRoundedHeaders_center" style="padding:11px;width:255px;"><span id="newsTitle"></span></div></td>
						<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
                        
						<td width="10px">
						  <div style="overflow:hidden;"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></div>
						</td>
						<td width="125px"><div class="districtPageRoundedHeaders_center" style="padding:11px;width:255px;"><span id="newsTitle"></span></div></td>
						<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
					</tr>
				</table>-->
			</div>

			<table>
			<tr>
			<td>
			<div id="newsDiv" class="productFeatureBody"  style="overflow:hidden;width:300px;height:206px;">
				
			</div>	
			</td>

			<td><div style="padding:12px;" id="ytVideoPlayer"></div></td>

			<td>
			<div id="newsDiv2" class="productFeatureBody"  style="overflow:hidden;width:300px;height:206px;">
				
			</div>
			</td>
			</tr>
		  </table>
		</div>



</tr>
</table>
<div id="cadreGroupsMainDiv"  style="width: 50%;" class="yui-skin-sam">
<div id="cadreGroupsHeadDiv" class=".yui-widget-hd"></div>
<div id="latestResultsDiv" class=""></div></div>
<div id="TNexitPollsDiv"></div>
<div id="TNPreviousPollsDiv"></div>
<script type="text/javascript">
tamilnaduExitPollAnd2006ElectionDetails();

</script>
</body>
</html>