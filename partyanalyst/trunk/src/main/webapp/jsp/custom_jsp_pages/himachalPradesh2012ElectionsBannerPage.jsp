<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script> 
<LINK rel="stylesheet" type="text/css" href="styles/jqueryDataTable/css/datatable.css"> 
<script src="js/jqueryDataTable/jquery.dataTables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.columnFilter.js"></script>

<style>

.importantPersonsDivClass{

	height:196px;
	width:126px;
	float:left;
	background:#fff;
	padding: 11px;
	margin:3px;
	border:1px solid #c3c3c3;
	border-radius:3px;

}


.alignCenter{
	text-align:center;
}

.leadStatusClass{

	color: green;
	font-weight: bold;
}

.candidateNameClass{

color: #21B2ED;
font-weight: bold;
font-family:arial;
font-size:11px;
/*width: 200px;*/
}
 .main-mbg {
    width:962px;
	border-radius:6px 6px 0px 0px;
	margin-bottom:0px;
}

.nominationresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.nominationresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.nominationresulttable tr:nth-child(odd){background:#e5e5e5;}
.nominationresulttable tr:nth-child(even){background:#f3f3f3;}
.nominationresulttable th{padding:5px;background:#489CDF;color:#fff;}
.buttonClass {
	background-color: background;
    border-radius: 6px 6px 6px 6px;
    color: white;
    cursor: pointer;
    font-weight: bold;
    padding: 6px;
}
.thStyle{background:#5e5e5e;color:#fff;font-weight:bold;text-align: center;}

#presidentelection ul li a {
    background: none repeat scroll 0 0 #D2E888;
    border-radius: 5px 5px 5px 5px;
    color: #3D3D3D;
    display: block;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
    padding: 10px;
    width: 360px;
}
#presidentelection ul li a:hover{
    background: none repeat scroll 0 0 #21B2ED;
	text-decoration: none;
}
#presidentelection{
  margin-left:100px;
  border:1px solid #CCCCCC;
  padding:0px 10px 10px 10px;
}
#presidentelectionDiv ul li a:hover
{
	text-decoration: none;
}

.pft-sec{ border: 1px solid #DDDDDD;padding-left: 10px;padding-right: 5px;padding-bottom: 65px;}
.voterLinksCls{clear: both; padding-right: 6px;}
.voterLinksCls p{padding:0px;font-size: 13px;
    text-align: justify;margin-top:8px;padding-bottom: 10px; display: table;}
	.voterLinksCls span{font-family:arial;font-size:12px;line-height:1.5em;}
	.voterLinksCls p a{float:right;}

.hglgts {
   text-align: left;
    width: 700px;
	float :left;
}

.hglgts li {
    -moz-font-feature-settings: normal;
    -moz-font-language-override: normal;
    -x-system-font: none;
    background-attachment: scroll;
    background-clip: border-box;
    background-color: transparent;
    background-image:url("images/icons/diamond.png");
    background-origin: padding-box;
    background-position: 0 8px;
    background-repeat: no-repeat;
    background-size: auto auto;
    color: #333333;
    font-family: verdana;
    font-size: 13px;
    font-size-adjust: none;
    font-stretch: normal;
    font-style: normal;
    font-variant: normal;
    font-weight: normal;
   line-height: 20px;
    list-style-image: none;
    list-style-position: outside;
    list-style-type: none;
    margin-bottom: 15px;
    padding-left: 15px;
}
table.gujaratTableDiv {background-color:transparent;border-collapse:collapse;width:100%;}
table.gujaratTableDiv th, table.gujaratTableDiv td {text-align:center;border:1px solid black;padding:5px;}
table.gujaratTableDiv th {background-color:AntiqueWhite;}
table.gujaratTableDiv td:first-child {width:50%;}

.textalignright{
  text-align:right;
}
.textalignclass{
  text-align:center;
}

#GujaratResultBody{display:inline-block;background:#EDF9FF;width:100%;padding:5px;}
#upComing{margin-top:10px;}
body{font:14px "Helvetica Neue",Helvetica,Arial,sans-serif;}
#importantCandidateHeadingDiv{margin-top:10px;}
.popover {
     padding: 5px;
    position: relative;
	display:inline-block;
    }
.popover-title {
    background-color: #777;
    border-bottom: 1px solid #f8f8f8;
    border-radius: 3px 3px 0 0;
    line-height: 1;
    padding: 9px 15px;
	color:#fff;

}	
.popover-content{	height:115px;
	position:relative;}
.popover-content  a{position:absolute;bottom:5px;right:5px;}
.election-analyis-tools{margin-top:10px;}
.pre-year-links a{margin:5px;}
</style>

<div>
<img src="images/specialPage/himachal_Pradesh_banner.jpg" style="align:center;width:974px;">
</div>



<div id="upComing" class="breadcrumb">
<h3 class="resulth3">Himachal Pradesh 2012 Vidhan Sabha Election</h3>
<br><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Total Assembly Constituencies - <font color="#05A8E9">68</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">17</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">3</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">48</font></span>

<div style="text-align:justify;margin:10px;padding:10px;"> 
<!--<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">AP Bi Election Schedule</span> -

<span style="font-family:verdana;font-size:13px;">Election Notification - May 18,  Last date for Nomination - May 25, Nomination withdraw Last date - May 28, <br />Polling - June 12, Counting - June 15.</span><br /><br />
-->
<!--<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">Himachal Pradesh </span> -

<span style="font-family:verdana;font-size:13px;">The Election Commission of India released Notification for General Election of <b><a href="statePageAction.action?stateId=9">Himachal Pradesh</a></b>. <b><a href="statePageAction.action?stateId=9">Himachal Pradesh</a></b> Total Constituencies 68ACs Date of Poll in 4.11.2012 and Counting of Votes in 20.12.2012.</span><br /><br />-->
<!-- Analysis Tools Start-->
<div class="breadcrumb election-analyis-tools" style="width: 885px; margin-left: -5px; height: 228px;">
<h3>Live Results Analysis Tools</h3>
<div class="row-fluid">


<div class="span4 popover" style="width: 275px; left: -9px;">
<h4 class="popover-title" style="width: 257px;">Compare Live <small class="badge">vs</small> Previous Results</h4>
<p class="popover-content" style="width: 262px;">
Compare Present election live results with previous election results,  
Analyze the party's performances in gaining and losing of seats from other parties 
Party's performances in the new constituencies.
<a class="btn btn-success pull-right" href="electionLiveResultsAnalysisAction.action?electionId=203&electionType=2&stateId=9">Compare Now !!</a>
</p>

</div>

<div class="span4 popover" style="width: 275px;">
<h4 class="popover-title" style="width: 262px;">State Ministers Trend </h4>
<p class="popover-content" style="width: 264px;">
Analyze the present status of Himachal Pradesh state ministers for 2007-2012 duration.
<a class="btn btn-success pull-right" href="ministerAnalysisAction.action?electionId=203&electionType=2&stateId=9&eventType=ministers">Analyze Now !!</a>
</p>

</div>
<div class="span4 popover" style="width: 279px; left: 13px;">
<h4 class="popover-title">Key Candidates Present Status</h4>
<p class="popover-content">
Analyze the present status of key candidates from Himachal Pradesh State
<a class="btn btn-success pull-right" href="ministerAnalysisAction.action?electionId=203&electionType=2&stateId=9&eventType=impCandidates">View Now !!</a>
</p>

</div>
</div></div>
<!-- Analysis Tools END-->
<div id="electionResultDiv"></div>
<div id="districtWiseElectionResultDiv" style="margin-top:40px;clear:both;margin-bottom:40px">
<div><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">Party Wise Results in Districts Of HIMACHALPRADESH Vidhan sabha 2012</h3></div>
  <div style="background:#EDF9FF;width:100%;padding:4px;">
  <select id="selectDistrictWise" style="margin-top:10px;width:200px;margin-bottom:10px;margin-left:20px;" onchange="getDistrictWiseElectionResults()">
     <option value="278">Bilaspur</option>
     <option value="271">Chamba</option>
     <option value="276">Hamirpur</option>
     <option value="272">Kangra</option>
     <option value="282">Kinnaur</option>
     <option value="274">Kullu</option>
     <option value="273">Lahul & Spiti</option>
     <option value="275">Mandi</option>
     <option value="281">Shimla</option>
     <option value="280">Sirmaur</option>
     <option value="279">Solan</option>
     <option value="277">Una</option> 
  </select>
  <div id="districtWiseElectionResultDisplayDiv"></div>
  </div>
</div>
<div id="candidateWinLeadElectionResultDiv" style="margin-top:40px;clear:both;margin-bottom:40px">
<div><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">WINNING/LEADING CANDIDATES INFO FOR HIMACHALPRADESH VIDHAN SABHA 2012</h3></div>
  <div style="background:#EDF9FF;width:100%;padding:4px;">
  <div id="candidateWinLeadResultDisplayDiv"></div>
  </div>
</div>


<div id="importantCandidateHeadingDiv">

	<div class="breadcrumb"><h3>Important Candidates Present Status

    

	<a class="btn btn-mini btn-warning" id="showLink" href="javaScript:{showDetails();}" style="display:none;">Show <i class=" icon-chevron-up icon-white" style="margin-top: -1px;"></i></a>
	<a class="btn btn-mini btn-warning" id="hideLink"  href="javaScript:{hideDetails();}" title="Hide">Hide <i class=" icon-chevron-down icon-white" style="margin-top: -1px;"></i> </a>

	<a class="btn btn-inverse pull-right" href="javaScript:{getImportantCandidatesInfo();}" style="text-transform: capitalize; margin-right: 5px;" title="Click here to Update Results" rel="tooltip"><i class="icon-refresh icon-white" style="margin-top: 1px;"></i> Refresh</a>
	
</h3>
	

	</div>

	<div id="importantPersonsDiv"></div>

</div>
<div id="specialPageHighLight"  style="margin-left: 20px;
    width: 860px;clear:both;"></div>

<div id="ExitPoll" style="display:table;">

<div class="span6">
<h4 style="font-weight:bold;background:#F1F1F1;padding:4px;border-radius: 3px 3px 0px 0px;">2012 Himachal Pradesh Exit Poll Results</h4>
<table class="table table-bordered table-condensed table-striped">

<tr>
<th>Source</th>
<th>BJP</th>
<th>CONG+</th>
<th>GPP/OTHERS</th>
</tr>
<tr>
<td>C-VOTER</td>
<td>27-35</td>
<td>30-38</td>
<td>-</td>
</tr>

<tr>
<td>CNN-IBN</td>
<td>30</td>
<td>29-35</td>
<td>-</td>
</tr>
<tr>
<td>CHANAKYA</td>
<td>23</td>
<td>40</td>
<td>-</td>
</tr>
<tr>
<td>-</td>
<td>-</td>
<td>-</td>
<td>-</td>
</tr>

</table>
</div>
<div class="span5">
<h4 style="font-weight:bold;background:#F1F1F1;padding:4px;border-radius: 3px 3px 0px 0px;">2012 Gujarat Exit Poll Results</h4>
<table class="table table-bordered table-condensed table-striped">
<tr>
<th>Source</th>
<th>BJP</th>
<th>CONG+</th>
<th>GPP/OTHERS</th>
</tr>
<tr>
<td>C-VOTER</td>
<td>119-129</td>
<td>49-59</td>
<td>-</td>
</tr>
<tr>
<td>CHANAKYA</td>
<td>140</td>
<td>40</td>
<td>2</td>

</tr>
<tr>
<td>ABP NEWS</td>
<td>116</td>
<td>60</td>
<td>6</td>
</tr>
<tr>
<td>HEAD LINES TODAY</td>
<td>118-128</td>
<td>50-56</td>
<td>-</td>
</tr>
</table>
</div>



</div>
<br>
<table width="100%">
<tr>

<td  width="45%" valign="top">
<table style="border:1px solid #ccc;margin-left: 9px;" width="98%" valign="top">
		
	
		
<h6 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left:9px;">Schedule for the Himachal Pradesh Legislative Assembly, 2012	</h6>
		
		
			<td>
			<table class="nominationresulttable">
<tr>
<th>Poll Event</th>
<th>Dates</th>
</tr>
<tr>
<td>Issue of Notification</td>
<td>10.10.2012 
(Wednesday)</td>
</tr>
<tr>
<td>Last date for making <br>Nominations</td>
<td>17.10.2012 
(Wednesday)</td>
</tr>
<td>Scrutiny of Nominations</td>
<td>18.10.2012 
(Thursday)</td>
</tr>
<tr>
<!--<td>Last date for withdrawal of
candidature</td>-->
<td>Last date for withdrawal of <br>candidature</td>
<td>20.10.2012 
(Saturday)</td>
</tr>
<tr>
<td>Date of Poll</td>
<td>04.11.2012 
(Sunday)</td>
</tr>
<tr>
<td>Counting of Votes</td>
<td>20.12.2012 
(Thursday)</td>
</tr>
<tr>
<td>Date before which election process
shall be completed</td>
<td>24.12.2012 
(Monday)</td>
</tr>
</table>	

			</td>


		</tr>

	</table>
	<table style="border:1px solid #ccc; margin-top: 12px; width: 98%; margin-left: 8px;">
	<tr>
	<td style="display: table;">
		<div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>

		<span class="gray">Are you a</span>
		<strong>Politician
		<span class="orange">/</span>Political Party
		<span class="orange">/</span>Media...</strong> Want to know how you can be benefited	with 
		<span class="orange">PartyAnalyst</span> 
		<div class="clear"></div>
		<div class="clickhere-button">
		<a href="viewFeaturesAction.action">Click Here to Learn More...</a></div>
		</div>
		</td>
		<td style="vertical-align: top;">
		<div class="voterLinksCls">
		
		<p style="border-bottom:1px solid #ddd;padding-bottom: 10px;">
			<span>The Right To <font style="font-weight:bold;">VOTE</font> is the Foundation Of Democracy. Your Vote is your Voice.Don't lose your Voice.</span>
		   <a style="margin-top: 5px;" class="btn btn-primary" href="voters.action">Voter's Guide</a></p>
		
		 <p style="border-bottom:1px solid #ddd;padding-bottom: 10px;"> 
		 <span><font style="font-weight:bold;">Voters Pulse</font>
		 Now You can drive your survey's through Party Analyst.</span><br>
		   <a style="" href="VotersPulse.action" class="btn btn-success">View Now</a></p>

		    <p>
		<span>You've come to the Right Place! The New Party Analyst.</span><br>
		  <a alt="Explore About PartyAnalyst Image"  class="btn btn-warning inline" href="viewFeaturesAction.action">Explore!</a>
		  </p>
		</div>
		</td>
		</tr></table>


</td>


 <td width="2%"></td>

	<!------------RIGHT SIDE PANEL ------------>
	<td width="43%" valign="top" class="breadcrumb">
<table>
<h3>
Himachal Assembly 2012 Live Election Analysis</h3>

<tr>
<td colspan="2">


<div class="breadcrumb">
	<p>Partywise Male and Female Candidates Seats Allocation and their Performances</p>
	<div style="margin:15px;margin-bottom:0px"><span >Select Election Year:</span>

		<select id="selectedElectionYears" onchange="getGenderInfo(this.value,this.id)" style="width:120px;">
			<option value="0">Select Year</option>
			<option value="148">2007</option>
			<option value="149">2003</option>
			<option value="150">1998</option>
			<option value="151">1993</option>
			<option value="152">1990</option>
			<option value="153">1985</option>
			<option value="154">1982</option>
			<option value="155">1977</option>
		</select>
	</div>
	</div>


<table class="breadcrumb">
<tr> <th colspan="5"> 
View Party Previous Performances From 1977 - 2012</th></tr>
<tr>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit"class="btn">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BSP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit"class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit"class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=SAD&selectedPartyId=794&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=794&alliances=true&__checkbox_alliances=true&submitButton=Submit"class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=SAD(M)&selectedPartyId=796&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=796&alliances=true&__checkbox_alliances=true&submitButton=Submit"class="btn">CPM</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPM</a>
</c:if>
</th></tr>
</table>
</td>
</tr>

<tr><td colspan="7" class="breadcrumb pre-year-links">
<p>
Previous Himachal Assembly Elections Results 
</p>
	

	<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=148&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=2007"><img src="images/icons/diamond.png"> 2007</a>



	<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=149&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=2003"><img src="images/icons/diamond.png"> 2003</a>

<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=150&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1998"> <img src="images/icons/diamond.png"> 1998</a>


<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=151&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1993"><img src="images/icons/diamond.png"> 1993</a>

<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=152&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1990"> <img src="images/icons/diamond.png"> 1990</a>

<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=153&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1985"> <img src="images/icons/diamond.png"> 1985</a>

<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=154&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1982"> <img src="images/icons/diamond.png"> 1982</a>

<a class="btn btn-primary btn-mini" href="electionDetailsReportAction.action?electionId=155&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1977"> <img src="images/icons/diamond.png"> 1977</a>
</td></tr>
<tr>
<td width="50%" colspan="2" >
<table style="margin-top:10px;" class="breadcrumb"><tr><td colspan="2">
<h4>View Himachal Districts And Constituencies Results</h4>
</td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Himachal Districts</span>
	<p style="padding:5px;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
    <option value='278'>Bilaspur</option>
	<option value='271'>Chamba</option>
	<option value='276'>Hamirpur</option>
	<option value='272'>Kangra</option>
	<option value='282'>Kinnaur</option>
	<option value='274'>Kullu</option>
	<option value='273'>Lahul&spiti</option>
	<option value='275'>Mandi</option>
	<option value='281'>Shimla</option>
	<option value='280'>Sirmaur</option>
	<option value='279'>Solan</option>
	<option value='277'>Una</option>

   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Himachal Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value='38917'>Ani</option>
<option value='38869'>Arki</option>
<option value='38902'>Baijnath</option>
<option value='38922'>Balh</option>
<option value='38885'>Bamsan</option>
<option value='38910'>Banikhet</option>
<option value='38916'>Banjar</option>
<option value='38913'>Bharmour</option>
<option value='38909'>Bhattiyat</option>
<option value='38880'>Bilaspur</option>
<option value='38919'>Chachiot</option>
<option value='38912'>Chamba</option>
<option value='38889'>Chintpurni</option>
<option value='38864'>Chopal</option>
<option value='38926'>Darang</option>
<option value='38924'>Dharampur</option>
<option value='38907'>Dharamsala</option>
<option value='38870'>Doon</option>
<option value='38888'>Gagret</option>
<option value='38894'>Gangath</option>
<option value='38882'>Geharwin</option>
<option value='38881'>Ghumarwin</option>
<option value='38923'>Gopalpur</option>
<option value='38896'>Guler</option>
<option value='38884'>Hamirpur</option>
<option value='38897'>Jaswan</option>
<option value='38899'>Jawalamukhi</option>
<option value='38895'>Jawali</option>
<option value='38925'>Joginder nagar</option>
<option value='38863'>Jubbal kotkhai</option>
<option value='38908'>Kangra</option>
<option value='38918'>Karsog</option>
<option value='38872'>Kasauli</option>
<option value='38868'>Kasumpti</option>
<option value='38860'>Kinnaur</option>
<option value='38879'>Kotkehloor</option>
<option value='38915'>Kulu</option>
<option value='38865'>Kumarsain</option>
<option value='38892'>Kutlehar</option>
<option value='38914'>Lahaul and spiti</option>
<option value='38927'>Mandi</option>
<option value='38886'>Mewa</option>
<option value='38920'>Nachan</option>
<option value='38883'>Nadaun</option>
<option value='38887'>Nadaunta</option>
<option value='38905'>Nagrota</option>
<option value='38878'>Nahan</option>
<option value='38871'>Nalagarh</option>
<option value='38893'>Nurpur</option>
<option value='38874'>Pachhad</option>
<option value='38903'>Palampur</option>
<option value='38877'>Paonta doon</option>
<option value='38898'>Pragpur</option>
<option value='38875'>Rainka</option>
<option value='38901'>Rajgir</option>
<option value='38911'>Rajnagar</option>
<option value='38861'>Rampur</option>
<option value='38862'>Rohru</option>
<option value='38890'>Santokgarh</option>
<option value='38906'>Shahpur</option>
<option value='38876'>Shillai</option>
<option value='38867'>Simla</option>
<option value='38873'>Solan</option>
<option value='38904'>Sulah</option>
<option value='38921'>Sundernagar</option>
<option value='38866'>Theog</option>
<option value='38900'>Thural</option>
<option value='38891'>Una</option>
 </select>
<div id="alertMessage_const_Himachal Pradesh"></div>

<div class="view-results"><a onclick="navigateToConstituencyPageFrmSpeclPge('selectedConstituencyInSpecialPage','alertMessage_const_Himachal Pradesh')" href="javascript:{}">view constituency</a></div>
</div>
</td>
</tr>
</table>
</td></tr>

<!--<tr><td colspan="7">
<br>
<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3"></object></center></td>
</tr>-->
</table>
</td></tr>
</table>
 
  
<br>

</span><br>

<a title="Gujarat 2012 Vidhan Sabha Election" href="specialPageAction.action?specialPageId=13" class="btn btn-primary btn-large">Click Here To View Gujarat Election 2012</span></a>
</div>

</div>
<div id="genderInfoDiv">
<div id="genderAnalysisDiv"></div></div>

<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});

$(document).ready(function() {
	//$('#selectedElectionYears').selectmenu('refresh');
	$("#dynamicText").insertBefore($(".election-analyis-tools"));
	$("#dynamicText").css("margin","10px 0px");
	getElectionInfo();
    getDistrictWiseElectionResults();
	getWonLeadCandidates();
});
function getDistrictWiseElectionResults(){
  var districtId = $("#selectDistrictWise").val();
  if(districtId == 0)
     return;
  var jsObj = {
				electionId:203,
	            time:new Date().getTime(),
				districtId:districtId,
				task:"getDistrictWiseLiveResults"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getDistrictWiseLiveResultsAction.action?"+param;
	callAjax(jsObj,url);

}
function getWonLeadCandidates(){
  
  var jsObj = {
				electionId:203,
	            time:new Date().getTime(),
				task:"getWonLeadResults"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getDistrictWiseLiveResultsAction.action?"+param;
	callAjax(jsObj,url);

}
function getGenderInfo(selectedYear,elecYearId)
{
	var jsObj = {
				elecYearId:elecYearId,
	            time:new Date().getTime(),
				electionId:selectedYear,
				task:"getPartyGenderInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportWithGenderAction.action?"+param;
	callAjax(jsObj,url);
}
function getElectionInfo()
{
	var jsObj = {
				electionId:203,
	            time:new Date().getTime(),
				task:"getPartyElectionInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getLiveResultsAction.action?"+param;
	callAjax(jsObj,url);
}
function callAjax(jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task =="getPartyGenderInfo")
								{
									buildGenderCountResultsDataTable(myResults,jsObj.elecYearId);
								}
								else if(jsObj.task =="getHighLights")
								{
									buildSpecialPageHightLights(myResults);
								}
								else if(jsObj.task =="getPartyElectionInfo")
								{
									buildGujaratElectionResult(myResults);
										
								}
								else if(jsObj.task == "getImportantCandidatesInfo")
								{
								
									buildImportantCnadidatesData(myResults)
								}
								else if(jsObj.task == "getDistrictWiseLiveResults")
									{
									
										buildDistrictWiseLiveResults(myResults)
									}
							    else if(jsObj.task == "getWonLeadResults")
									{
									
										buildWonLeadResults(myResults)
									}
							}
							catch (e) {   
							   	//alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function buildWonLeadResults(results){
      if(results != null && results.length >0){
	var str='';
	
	  str+='<table id="candidateWinLeadResultTableDiv" style="width:95%;margin-left:auto;margin-right:auto;">';
	     str+='<thead>';
		 str+=' <tr>';
		 str+='  <th>Constituency</th>';
		 str+='  <th>District</th>';
		 str+='  <th>Candidate</th>';
		 str+='  <th>Party</th> ';
		 str+='  <th>Status</th>';
		 str+='  <th>Assests</th>';
		 str+='  <th>liabilities</th>';
		 str+=' </tr>';
		
		 str+='</thead>';
		 
		 str+='<tbody>';
		  for(var i in results){
		  
		  str+=' <tr> ';
		  str+='    <td><a href="constituencyPageAction.action?constituencyId='+results[i].constiId+'">'+results[i].constiName+'</a></td>';
		  str+='    <td><a href="districtPageAction.action?districtId='+results[i].districtId+'">'+results[i].districtName+'</a></td>';
		  str+='	  <td><a href="candidateElectionResultsAction.action?candidateId='+results[i].candidateId+'">'+results[i].candidateName+'</a></td>';
		  if(results[i].partyName != 'IND'){
		    str+='	    <td class="textalignclass"><a href="partyPageAction.action?partyId='+results[i].partyId+'" >'+results[i].partyName+'</a></td>';
		  }else{
		    str+='	    <td class="textalignclass">'+results[i].partyName+'</td>';
		}
		str+='	  <td class="textalignclass">'+results[i].status+'</td>';
		if(results[i].assets != null)
		  str+='	  <td class="textalignright">'+results[i].assets+'</td>';
		else
		   str+='	  <td ></td>';
		if(results[i].liabilities != null)
		  str+='	  <td class="textalignright">'+results[i].liabilities+'</td>';
		else
		 str+='	  <td ></td>';
		str+='   </tr>';
		}
		 str+='</tbody>';
		str+=' <tfoot>';
		str+='  <tr>';
		str+='   <th>Constituency</th>';
		str+='   <th>District</th>';
		str+='   <th>Candidate</th>';
		str+='   <th>Party</th> ';
		str+='   <th>Status</th>';
		str+='   <th>Assests</th>';
		str+='   <th>liabilities</th>';
		str+='  </tr>';
		str+=' </tfoot>';
	  str+='</table>';
		$("#candidateWinLeadResultDisplayDiv").html(str);
		

		  $('#candidateWinLeadResultTableDiv').dataTable({
		"aLengthMenu": [[10,25,50,100, -1], [10, 25,50,100,"All"]]
	})
		  .columnFilter({ 
		  	
			aoColumns: [ { type: "text"},
			             { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "number"},
						 { type: "number"}
						 
				]

		});
	}
}

function buildDistrictWiseLiveResults(results){
    if(results != null && results.length >0){
	var str='';
	
	  str+='<table id="districtResultTableDiv" style="width:95%;margin-left:auto;margin-right:auto;">';
	     str+='<thead>';
		 str+=' <tr>';
		 str+='  <th>Constituency</th>';
		 str+='  <th>Candidate</th>';
		 str+='  <th>Party</th> ';
		 str+='  <th>Status</th>';
		 str+='  <th>Assests</th>';
		 str+='  <th>liabilities</th>';
		 str+=' </tr>';
		
		 str+='</thead>';
		 
		 str+='<tbody>';
		  for(var i in results){
		  
		  str+=' <tr> ';
		  str+='    <td><a href="constituencyPageAction.action?constituencyId='+results[i].constiId+'">'+results[i].constiName+'</a></td>';
		  str+='	  <td><a href="candidateElectionResultsAction.action?candidateId='+results[i].candidateId+'">'+results[i].candidateName+'</a></td>';
		  if(results[i].partyName != 'IND'){
		    str+='	    <td class="textalignclass"><a href="partyPageAction.action?partyId='+results[i].partyId+'" >'+results[i].partyName+'</a></td>';
		  }else{
		    str+='	    <td class="textalignclass">'+results[i].partyName+'</td>';
		}
		str+='	  <td class="textalignclass">'+results[i].status+'</td>';
		if(results[i].assets != null)
		  str+='	  <td class="textalignright">'+results[i].assets+'</td>';
		else
		   str+='	  <td ></td>';
		if(results[i].liabilities != null)
		  str+='	  <td class="textalignright">'+results[i].liabilities+'</td>';
		else
		 str+='	  <td ></td>';
		str+='   </tr>';
		}
		 str+='</tbody>';
		str+=' <tfoot>';
		str+='  <tr>';
		str+='   <th>Constituency</th>';
		str+='   <th>Candidate</th>';
		str+='   <th>Party</th> ';
		str+='   <th>Status</th>';
		str+='   <th>Assests</th>';
		str+='   <th>liabilities</th>';
		str+='  </tr>';
		str+=' </tfoot>';
	  str+='</table>';
		$("#districtWiseElectionResultDisplayDiv").html(str);
		

		  $('#districtResultTableDiv').dataTable({
		"aLengthMenu": [[10,25,50,100, -1], [10, 25,50,100,"All"]]
	})
		  .columnFilter({ 
		  	
			aoColumns: [ { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "number"},
						 { type: "number"}
						 
				]

		});
	}
}
function buildSpecialPageHightLights(results)
{
	
	var str ='';
	var specialPageHighLight = document.getElementById('specialPageHighLight');
	if(results != null && results!='')
	{
	str +='<fieldset style="verdana,sans-serif;font-weight:bold;">';
	str +='<legend style="border-radius: 3px;background:#21B2ED;font-family: verdana;">Himachal Pradesh Highlights</legend>';
	str +='<div class="hglgts">';
	for(var i in results)
	{
	str += '<ul><li>';
	str += ' '+results[i].description+'</li></ul>';
	}
	
	str +='</div>';
	str +='</fieldset>';
	}

	specialPageHighLight.innerHTML = str;
}
function buildGenderCountResultsDataTable(myResults,elecYearId){
	var selectedYearEle = document.getElementById(''+elecYearId+'');
	var year = selectedYearEle.options[selectedYearEle.selectedIndex].text;

if(myResults == null)
		return;

	  $.fx.speeds._default = 1000;
	  $("#genderInfoDiv").dialog({ stack: false,
								height: 'auto',
								width: 850,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								minHeight: 450,
								title:'<center><font color="Navy">Partywise Male and Female Candidates Performances</font><center>',
								overlay: { opacity: 0.5, background: 'black'}
								});
		$("#genderInfoDiv").dialog();

	
	var str= '';
	str +='<div style="margin-left: 490px;">';
	str +='<table>';
	str +='<tr>';
	str +='<td><b style="font-size:13px;">Select Election Year:</b></td>';
	str +='<td>';
	str +='<select id="selectedEleYear" onchange="getGenderInfo(this.value,this.id)" style="width:120px;">';
	str +='<option value="0">Select Year</option>';

	str +='<option value="148">2007</option>';
	str +='<option value="149">2003</option>';
	str +='<option value="150">1998</option>';
	str +='<option value="151">1993</option>';
	str +='<option value="152">1990</option>';
	str +='<option value="153">1985</option>';
	str +='<option value="154">1982</option>';
	str +='<option value="155">1977</option>';
	str +='</select>';
	str +='</td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	str +='<h3 style="background: none repeat scroll 0pt 0pt rgb(33, 178, 237); padding: 7px 0px 6px; color: rgb(255, 255, 255); margin-top: 13px; border-left-width: 0px; margin-left: 43px; font-size: 13px; border-radius: 3px 3px 3px 3px; text-align: center; width: 726px;">Partywise Male And Female Participation and their Performance In Himachal <font color="pink">'+year+'</font> Assembly Election</h3>';

	str +='<table cellspacing="0" cellpadding="5" bordercolor="#cccccc" border="1" style="margin-top: 22px;">';
	str +='<tr style="background: none repeat scroll 0% 0% aliceBlue;">';
	str +='<th style="font-size: 13px;">Party</th>';
	str +='<th style="font-size: 13px;">TP*</th>';
	str +='<th style="font-size: 13px;">Won</th>';
	str +='<th style="font-size: 13px;">CV* %</th>';
	str +='<th style="font-size: 13px;">PV* %</th>';
	str +='<th style="font-size: 13px;">Male Participants</th>';
	str +='<th style="font-size: 13px;">Male Won</th>';
	str +='<th style="font-size: 13px;">MCGV* %</th>';
	str +='<th style="font-size: 13px;">Female Participants</th>';
	str +='<th style="font-size: 13px;">Female Won</th>';
	str +='<th style="font-size: 13px;">FCGV* %</th>';
	str +='</tr>';
	for(var i=0 ; i<myResults.length ; i++)
	{
	str +='<tr>';
	
	str +='<td><a href="partyPageAction.action?partyId='+myResults[i].partyId+'"><font color="blue">'+myResults[i].partyName+'</font></a></td>';
	str +='<td>'+myResults[i].totalParticipated+'</td>';
	str +='<td>'+myResults[i].totalSeatsWon+'</td>';
	str +='<td>'+myResults[i].completeVotesPercent+'</td>';
	str +='<td>'+myResults[i].PVotesPercent+'</td>';
	str +='<td>'+myResults[i].malePerticipated+'</td>';
	str +='<td>'+myResults[i].maleWon+'</td>';
	str +='<td>'+myResults[i].MVotesPercent+'</td>';
	str +='<td>'+myResults[i].femalePerticipated+'</td>';
	str +='<td>'+myResults[i].femaleWon+'</td>';
	str +='<td>'+myResults[i].FVotesPercent+'</td>';
	str +='</tr>';
	}
	str +='</table>';
	str +='<div style="margin-top: 31px;padding-left: 75px;">';
	str +='<table>';
	str +='<tr><td><b>TP* = Total Participation</b></td></tr>'
	str +='<tr><td><b>CV* % = Complete Votes Percentage</b></td></tr>';
	str +='<td><b>PV* % = Participated Votes Percentage</b></td>';
	str +='</tr>';
	str +='<tr>';
	str +='<td><b>MCGV* % = Male Candidates Gained Votes Percentage</b></td>';
	str +='</tr>';
	str +='<tr>';
	str +='<td><b>FCGV* % = Female Candidates Gained Votes Percentage</b></td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	document.getElementById('genderAnalysisDiv').innerHTML = str;

}

function getSpecialPageHighLights()
{
var jsObj = {
				specialPageId:"14",
	           
				task:"getHighLights"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSpecialPageHighLights.action?"+param;						
		
	callAjax(jsObj,url);
}
function buildGujaratElectionResult(myResults)
{
	if(myResults!=null && myResults.electionLiveResultVOList!=null &&  myResults.electionLiveResultVOList.length>0)
	{
	var electionResult= '';	
	electionResult+='<div id="gujratResultsHeader">';
	electionResult+='<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">';
	electionResult+='Party Wise Results Of Himachal pradesh Vidhan Sabha 2012</h3>';
	electionResult+='</div>';
	electionResult+='<div id="GujaratResultBody">';
	electionResult+='<span id="gujratResultsBody1" style ="width:475px;float:left">';
	electionResult+='<table class="table table-bordered">';
	electionResult+='<tr>';
	electionResult+='<th>Total Seats - <font color="#05A8E9">'+myResults.totalSeats+'</font></th>';
	electionResult+='<th> Result Known - <font color="#05A8E9">'+myResults.totalKnownCount+'</font></th>';
	electionResult+='</tr>';
	electionResult+='<tr>';
	electionResult+='<th>Total Lead - <font color="#05A8E9">'+myResults.newKnownCount+'</font></th>';
	electionResult+='<th> Won - <font color="#05A8E9">'+myResults.retainedCount+'</font></th>';
	electionResult+='</tr>';
	electionResult+='<table>';
	electionResult+='</span>';
	electionResult+='<span id="gujratResultsBody2" style ="width:500px;float:left">';
	electionResult+='<table class="gujaratTableDiv">';
	electionResult+='<tr>';
	electionResult+='<th>party</th>';
	electionResult+='<th>TP*</th>';
	electionResult+='<th>Lead</th>';
	electionResult+='<th>Won</th>';
	electionResult+='<th>Lead/Won</th>';
	electionResult+='</tr>';
	for(var i=0 ; i<myResults.electionLiveResultVOList.length ; i++)
	{
	electionResult+='<tr>';
	electionResult+='<th>'+myResults.electionLiveResultVOList[i].partyName+'</th>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].totalSeatsParticipated+'</td>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].leadCountInNew+'</td>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].wonCountInNew+'</td>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].wonOrLeadCount+'</td>';
	electionResult+='</tr>';
	}
	electionResult+='</table>';
	electionResult+='</span>';
	electionResult+='<span  id="GujaratResultGraph" style ="width: 400px; float: right; border-radius: 5px 5px 5px 5px; box-shadow: 0px 1px 2px rgb(170, 170, 170); margin-right: 5px; padding: 2px; position: relative;">';
	electionResult+='<b></b>';
	electionResult+='</span>';
	electionResult+='</div>';
	document.getElementById('electionResultDiv').innerHTML = electionResult;
	
	var data = new google.visualization.DataTable();
	data.addColumn('string','Area Type');
	data.addColumn('number','Count');
	data.addRows(myResults.electionLiveResultVOList.length);
	
	for(var j=0; j<myResults.electionLiveResultVOList.length; j++)
	{
		data.setValue(j,0,myResults.electionLiveResultVOList[j].partyName);
		data.setValue(j,1,myResults.electionLiveResultVOList[j].wonOrLeadCount);
	}
	var chart = new google.visualization.PieChart(document.getElementById('GujaratResultGraph')); 
	chart.draw(data,{width:400, height: 270, title:'HimachalPradesh Vidhan Sabha Election 2012 Lead/Won Chart'});
	}
}


 getSpecialPageHighLights();

</script>


<script>
function buildImportantCnadidatesData(results){
	
	if(results == null || results == "null"){
		$('#importantCandidateHeadingDiv').hide();
		  return false;		
		
	}
		

    var str='';

    str+='<div class="span12" style="border:1px solid #c3c3c3;width:900px;">';


	if(results.length == 0){
	  $('#importantCandidateHeadingDiv').hide();
	  return false;
	}
	for(var i in results){

		 str+='<div class="importantPersonsDivClass">';

             if(results[i].status == "Won")
 			   str+='<div style="text-align:center;margin-bottom:10px;"><span class="leadStatusClass" style="font-size:16px;">'+results[i].status+'</span><i style="float:right;" class="icon-thumbs-up"></i></div>';

			 else if(results[i].status == "Lead")
			
				str+='<div class="alignCenter" style="margin-bottom:10px;"><span style="color: #4D6185; font-weight: bold;font-size:16px;">'+results[i].status+'</span><i  style="float:right;" class="icon-circle-arrow-up"></i></div>';

			else

				str+='<div class="alignCenter" style="margin-bottom:10px;"><span style="color: red; font-weight: bold;font-size:16px;">'+results[i].status+'</span><i style="float:right;" class="icon-thumbs-down"></i></div>';

			str+='<div class="alignCenter"><img width="80" height="79" onerror="setImage(this)" src="images/candidates/'+results[i].candidateName+'.jpg"></div><br>';

			str+='<div class="alignCenter candidateNameClass"><span >'+results[i].candidateName+'</span></div>';

			//str+='<div class="alignCenter"><span style="color: #716F64; font-weight: bold;">'+results[i].party+'</span></div>';

			str+='<div class="alignCenter"><span style="color: red; font-weight: bold; text-align: center;">'+results[i].constituency+'</span></div>';


        str+='</div>';

		
	}


	str+='</div>';


	$('#importantPersonsDiv').html(str);

setTimeout(getImportantCandidatesInfo, 120000);

}

function getImportantCandidatesInfo()
{
	var jsObj = {
	           	electionId:203,
				task:"getImportantCandidatesInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getImportantCandidatesInfoAction.action?"+param;
	callAjax(jsObj,url);
}

 function showDetails(){

	 $('#showLink').css('display','none');
	 $('#hideLink').css('display','block');
	 $('#refreshDiv').css('display','block');


	 $('.importantPersonsDivClass').show();

 }
 function hideDetails(){

	$('#showLink').css('display','block');
	$('#hideLink').css('display','none');
	$('#refreshDiv').css('display','none');

	$('.importantPersonsDivClass').hide('slow');

 }

 getImportantCandidatesInfo();
</script>