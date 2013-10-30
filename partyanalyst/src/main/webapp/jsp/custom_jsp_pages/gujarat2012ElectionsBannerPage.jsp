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
.pre-year-links a{margin:5px;}
body{font:14px "Helvetica Neue",Helvetica,Arial,sans-serif;}
	.main-mbg{
		width:962px;
		margin:0px;
		border-radius:6px 6px 0px 0px;
		
	}

.electionresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.electionresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.electionresulttable th{background:#21B2ED;color:#fff;}
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
    width: 880px;
	float :left;
}

.hglgts li {
    -moz-font-feature-settings: normal;
    -moz-font-language-override: normal;
    -x-system-font: none;
    background-attachment: scroll;
    background-clip: border-box;
    background-color: transparent;
   /* background-image: url("http://static.ibnlive.in.com/ibnlive/pix/ibnhome/blts_c.jpg");*/
	background-image:url("images/icons/diamond.png");
    background-origin: padding-box;
    background-position: 0 8px;
    background-repeat: no-repeat;
    background-size: auto auto;
    color: #333333;
    font-family: verdana;
    font-size: 14px;
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
table.gujaratTableDiv {background-color:transparent;border-collapse:collapse;width:100%;}
table.gujaratTableDiv th, table.gujaratTableDiv td {text-align:center;}
table.gujaratTableDiv th {}
table.gujaratTableDiv td:first-child {width:50%;}

.textalignright{
  text-align:right;
}
.textalignclass{
  text-align:center;
}

#GujaratResultBody{display:inline-block;background:#EDF9FF;width:100%;padding:4px;}
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

</style>

<div style="margin-bottom:10px;">
<img src="images/specialPage/gujarat_banner.jpg" style="align:center;width:975px;">
</div>

<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->


<div id="upComing" class="breadcrumb">
<h3 class="resulth3">Gujarat 2012 Vidhan Sabha Election</h3>

<br><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Total Assembly Constituencies - <font color="#05A8E9">182</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">13</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">27</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">142</font></span>


<div style="text-align:justify;margin:10px;padding:10px;"> 

<!--<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">Gujarat </span> -

<span style="font-family:verdana;font-size:13px;">The Election Commission of India released Notification for General Election of <b><a href="statePageAction.action?stateId=7"> Gujarat</a></b> Legislative Assembly, 2012. Polls in <b><a href="statePageAction.action?stateId=7">Gujarat</a></b> will take place in two phases. First phase on December 13, 2012 and second phase on December 17, 2012. The counting will take place on December 20, 2012.
</span></br></br>-->

<!-- Analysis Tools Start-->
<div class="breadcrumb" id="dynaUpload"   style="width: 885px; margin-left: -5px; height: 228px;">
<h3>Live Results Analysis Tools</h3>
<div class="row-fluid">


<div class="span4 popover"  style="width: 275px; left: -9px;">
<h4 class="popover-title" style="width: 257px;"><span id="live">Compare Live</span> <small class="badge">vs</small> <span id="previous">Previous Results </span></h4>
<p class="popover-content" style="width: 262px;">
Compare Present election live results with previous election results,  
Analyze the party's performances in gaining and losing of seats from other parties 
Party's performances in the new constituencies.
<a class="btn btn-success pull-right" href="electionLiveResultsAnalysisAction.action?electionId=202&electionType=2&stateId=7">Compare Now !!</a>
</p>

</div>

<div class="span4 popover" style="width: 275px;">
<h4 class="popover-title" style="width: 262px;">State Ministers Trend </h4>
<p class="popover-content" style="width: 264px;">
Analyze the present status of Gujarat state ministers for 2007-2012 duration.
<a class="btn btn-success pull-right" href="ministerAnalysisAction.action?electionId=202&electionType=2&stateId=7&eventType=ministers">Analyze Now !!</a>
</p>

</div>
<div class="span4 popover" style="width: 279px; left: 13px;">
<h4 class="popover-title">Key Candidates Present Status</h4>
<p class="popover-content">
Analyze the present status of key candidates from Gujarat State
<a class="btn btn-success pull-right" href="ministerAnalysisAction.action?electionId=202&electionType=2&stateId=7&eventType=impCandidates">View Now !!</a>
</p>

</div>
</div></div>
<!-- Analysis Tools END-->

<div id="electionResultDivMain" style="padding-bottom:10px;">
  <div id="electionResultDiv"></div>
</div>

	
<div id="districtWiseElectionResultDiv" style="margin-top:40px;clear:both;margin-bottom:40px">
<div><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;border-radius:3px 3px 0px 0px;width: 100%;">DISTRICT WISE Party Wise Results Of Gujarat Vidhan Sabha 2012</h3></div>
  <div style="background:#EDF9FF;width:100%;padding:4px;">
  <select id="selectDistrictWise" style="margin-top:10px;width:200px;margin-bottom:10px;margin-left:20px;" onchange="getDistrictWiseElectionResults()">
     <option value="152">Ahmadabad</option>
     <option value="158">Amreli</option>
     <option value="160">Anand</option>
     <option value="147">Banas Kantha</option>
     <option value="166">Bharuch</option>
     <option value="159">Bhavnagar</option>
     <option value="163">Dohad</option>
     <option value="151">Gandhinagar</option>
     <option value="155">Jamnagar</option>
     <option value="157">Junagadh</option>
     <option value="146">Kachchh</option>
     <option value="161">Kheda</option>
     <option value="149">Mahesana</option>
     <option value="165">Narmada</option>
     <option value="169">Navsari</option>
     <option value="162">Panch Mahals</option>
     <option value="148">Patan</option>
     <option value="156">Porbandar</option>
     <option value="154">Rajkot</option>
     <option value="150">Sabar Kantha</option>
     <option value="167">Surat</option>
     <option value="153">Surendranagar</option>
     <option value="168">The Dangs</option>
     <option value="164">Vadodara</option>
     <option value="170">Valsad</option>  
  </select>
  <div id="districtWiseElectionResultDisplayDiv"></div>
  </div>
</div>


<div id="candidateWinLeadElectionResultDiv" style="margin-top:40px;clear:both;margin-bottom:40px">
<div><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">WINNING/LEADING CANDIDATES INFO FOR GUJARAT VIDHAN SABHA 2012</h3></div>
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
<div id="specialPageHighLight"  style="
    width: 900px;margin-left: 10px;clear:both;"></div>

<div id="ExitPoll" style="display:table;">
<div class="span6">
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
<div class="span5">
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

</div>


<table>
<tr>

<td  width="45%" valign="top">
<table>
		
	
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;">Schedule for the Gujarat Legislative Assembly, 2012	</h4>
		
		
			<td>
			<table class="nominationresulttable">
<tr>
<th>Poll Event</th>
<th>1st Phase
( 87 ACs)
<th>2nd Phase
( 95 ACs)</th>

</tr>
<tr>
<td>Issue of Notification</td>
<td>17.11.2012
(Saturday)</td>
<td>23.11.2012
(Friday)</td>
</tr>
<tr>
<td>Last date for making Nominations</td>
<td>24.11.2012
(Saturday)</td>
<td>30.11.2012
(Friday)</td>
</tr>
<td>Scrutiny of Nominations</td>
<td>26.11.2012
(Monday)</td>
<td>01.12.2012
(Saturday)</td>
</tr>
<tr>
<!--<td>Last date for withdrawal of
candidature</td>-->
<td>Last date for withdrawal of candidature</td>
<td>28.11.2012
(Wednesday)</td>
<td>03.12.2012
(Monday)</td>
</tr>
<tr>
<td>Date of Poll</td>
<td>13.12.2012
(Thursday)</td>
<td>17.12.2012
(Monday)</td>
</tr>
<tr>
<td>Counting of Votes</td>
<td>20.12.2012
(Thursday)</td>
<td>20.12.2012
(Thursday)</td>
</tr>
<tr>
<td>Date before which election process
shall be completed</td>
<td>24.12.2012
(Monday)</td>
<td>24.12.2012
(Monday)</td>
</tr>
</table>	

			</td>


		</tr>

	</table>

	<table class="whitegloss">
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
<table style="padding-left:20px" width="98%" valign="top">
<h3 style="">
Gujarat Assembly 2012 Live Election Analysis</h3>

<tr>
<td colspan="2">


<div class="breadcrumb">
	<span>Party wise Male and Female Candidates Seats Allocation and their Performances</span>
	<div style="margin:15px;margin-bottom:0px"><span style="">Select Election Year:</span>

		<select id="selectedElectionYears" onchange="getGenderInfo(this.value,this.id)" style="width:120px;">
			<option value="0">Select Year</option>
			<option value="126">2007</option>
			<option value="127">2002</option>
			<option value="128">1998</option>
			<option value="129">1995</option>
			<option value="130">1990</option>
			<option value="131">1985</option>
			<option value="132">1980</option>
			<option value="133">1975</option>
		</select>
	</div>
	</div>


<table class="breadcrumb">
<tr><th colspan="5">
<h4>View Party Previous Performances From 1977 - 2012</h4>
</th></tr>
<tr>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BSP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=SAD&selectedPartyId=794&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=794&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=SAD(M)&selectedPartyId=796&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=796&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
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
<span style="clear: both; display: inline-block; width: 100%;">Previous Gujarat Assembly Elections Results </span>

	

	<a href="electionDetailsReportAction.action?electionId=126&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2007"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 2007</a>


	<a href="electionDetailsReportAction.action?electionId=127&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2002"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 2002</a>


<a  href="electionDetailsReportAction.action?electionId=128&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1997" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1998</a>


<a href="electionDetailsReportAction.action?electionId=129&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1992" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1995</a>


<a  href="electionDetailsReportAction.action?electionId=130&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1985" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1990</a>


<a  href="electionDetailsReportAction.action?electionId=131&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1980" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1985</a>


<a href="electionDetailsReportAction.action?electionId=132&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1977"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1980</a>


<a  href="electionDetailsReportAction.action?electionId=133&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1977"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1975</a>

</td></tr>
<tr> <td>
<table class="breadcrumb" style="margin-top:10px;">
<tr>
<td width="50%" colspan="2" >
<span>View Gujarat Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Gujarat Districts</span>
	<p style="padding:5px;text-align:left;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
       <option value='152'>Ahmadabad</option>
	<option value='158'>Amreli</option>
	<option value='160'>Anand</option>
	<option value='147'>Banas kantha</option>
	<option value='166'>Bharuch</option>
	<option value='159'>Bhavnagar</option>
	<option value='163'>Dohad</option>
	<option value='151'>Gandhinagar</option>
	<option value='155'>Jamnagar</option>
	<option value='157'>Junagadh</option>
	<option value='146'>Kachchh</option>
	<option value='161'>Kheda</option>
	<option value='149'>Mahesana</option>
	<option value='165'>Narmada</option>
	<option value='169'>Navsari</option>
	<option value='162'>Panch mahals</option>
	<option value='148'>Patan</option>
	<option value='156'>Porbandar</option>
	<option value='154'>Rajkot</option>
	<option value='150'>Sabar kantha</option>
	<option value='167'>Surat</option>
	<option value='153'>Surendranagar</option>
	<option value='168'>The dangs</option>
	<option value='164'>Vadodara</option>
	<option value='170'>Valsad</option>




   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Gujarat Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value='34777'>Abdasa</option>
<option value='34821'>Amreli</option>
<option value='34909'>Anand</option>
<option value='34781'>Anjar</option>
<option value='34933'>Ankleshwar</option>
<option value='34848'>Asarwa</option>
<option value='34819'>Babra</option>
<option value='34900'>Balasinor</option>
<option value='34942'>Bardoli</option>
<option value='34923'>Baroda</option>
<option value='34927'>City</option>
<option value='34837'>Baroda</option>
<option value='34885'>Rural</option>
<option value='34915'>Bavla</option>
<option value='34805'>Bayad</option>
<option value='34833'>Bhadran</option>
<option value='34834'>Bhanvad</option>
<option value='34881'>Bhavnagar north</option>
<option value='34779'>Bhavnagar south</option>
<option value='34914'>Bhiloda</option>
<option value='34825'>Bhuj</option>
<option value='34932'>Borsad</option>
<option value='34954'>Botad</option>
<option value='34916'>Broach</option>
<option value='34908'>Bulsar</option>
<option value='34868'>Cambay</option>
<option value='34917'>Chakalasi</option>
<option value='34952'>Chanasma</option>
<option value='34948'>Chhota udaipur</option>
<option value='34786'>Chikhli</option>
<option value='34921'>Chorasi</option>
<option value='34953'>Chotila</option>
<option value='34878'>Dabhoi</option>
<option value='34845'>Dangs bansda</option>
<option value='34783'>Danta</option>
<option value='34841'>Dariapur kazipur</option>
<option value='34935'>Dasada</option>
<option value='34874'>Daskroi</option>
<option value='34842'>Dediapada</option>
<option value='34872'>Deesa</option>
<option value='34892'>Dehgam</option>
<option value='34835'>Deodar</option>
<option value='34875'>Devgadh baria</option>
<option value='34955'>Dhandhuka</option>
<option value='34822'>Dhanera</option>
<option value='34836'>Dharampur</option>
<option value='34798'>Dhari</option>
<option value='34788'>Dholka</option>
<option value='34890'>Dhoraji</option>
<option value='34807'>Dhrangadhra</option>
<option value='34844'>Dohad</option>
<option value='34826'>Dwarka</option>
<option value='34951'>Ellis</option>
<option value='34855'>Bridge</option>
<option value='34832'>Gadhada</option>
<option value='34896'>Gandevi</option>
<option value='34796'>Gandhinagar</option>
<option value='34894'>Ghogho</option>
<option value='34787'>Godhra</option>
<option value='34882'>Gondal</option>
<option value='34880'>Halol</option>
<option value='34949'>Halvad</option>
<option value='34852'>Himatnagar</option>
<option value='34930'>Idar</option>
<option value='34804'>Jalalpore</option>
<option value='34801'>Jamalpur</option>
<option value='34802'>Jambusar</option>
<option value='34792'>Jamjodhpur</option>
<option value='34797'>Jamnagar</option>
<option value='34918'>Jamnagar rural</option>
<option value='34934'>Jasdan</option>
<option value='34888'>Jetpur</option>
<option value='34800'>Jetpur</option>
<option value='34858'>Jhagadia</option>
<option value='34818'>Jhalod</option>
<option value='34857'>Jodiya</option>
<option value='34803'>Jotana</option>
<option value='34895'>Junagadh</option>
<option value='34856'>Kadi</option>
<option value='34847'>Kalawad</option>
<option value='34943'>Kalol</option>
<option value='34873'>Kalol</option>
<option value='34901'>Kalupur</option>
<option value='34929'>Kamrej</option>
<option value='34904'>Kankrej</option>
<option value='34812'>Kapadvanj</option>
<option value='34851'>Karjan</option>
<option value='34806'>Kathlal</option>
<option value='34879'>Keshod</option>
<option value='34863'>Khadia</option>
<option value='34823'>Khambhalia</option>
<option value='34829'>Khedbrahma</option>
<option value='34809'>Kheralu</option>
<option value='34820'>Kodinar</option>
<option value='34785'>Kundla</option>
<option value='34889'>Kutiyana</option>
<option value='34891'>Lathi</option>
<option value='34898'>Limbdi</option>
<option value='34906'>Limdi</option>
<option value='34941'>Limkheda</option>
<option value='34830'>Lunavada</option>
<option value='34817'>Mahudha</option>
<option value='34811'>Mahuva</option>
<option value='34838'>Mahuva</option>
<option value='34778'>Maliya</option>
<option value='34938'>Manavadar</option>
<option value='34810'>Mandal</option>
<option value='34853'>Mandvi</option>
<option value='34860'>Mangrol</option>
<option value='34913'>Mangrol</option>
<option value='34886'>Maninagar</option>
<option value='34905'>Mansa</option>
<option value='34859'>Matar</option>
<option value='34884'>Meghraj</option>
<option value='34789'>Mehmedabad</option>
<option value='34956'>Mehsana</option>
<option value='34780'>Modasa</option>
<option value='34907'>Morvi</option>
<option value='34854'>Mota pondha</option>
<option value='34919'>Mundra</option>
<option value='34950'>Nadiad</option>
<option value='34937'>Naroda</option>
<option value='34944'>Nasvadi</option>
<option value='34928'>Navsari</option>
<option value='34876'>Nijhar</option>
<option value='34827'>Olpad</option>
<option value='34957'>Padra</option>
<option value='34867'>Palanpur</option>
<option value='34911'>Palitana</option>
<option value='34808'>Pardi</option>
<option value='34883'>Patan</option>
<option value='34870'>Petlad</option>
<option value='34893'>Porbandar</option>
<option value='34793'>Prantij</option>
<option value='34794'>Radhanpur</option>
<option value='34795'>Rajgadh</option>
<option value='34936'>Rajkoti</option>
<option value='34824'>Rajkotii</option>
<option value='34849'>Rajkot rural</option>
<option value='34899'>Rajpipla</option>
<option value='34925'>Rajula</option>
<option value='34782'>Rakhial</option>
<option value='34843'>Randhikpur</option>
<option value='34869'>Raopura</option>
<option value='34920'>Rapar</option>
<option value='34887'>Sabarmati</option>
<option value='34840'>Sami</option>
<option value='34910'>Sankheda</option>
<option value='34922'>Santrampur</option>
<option value='34924'>Sarkhej</option>
<option value='34850'>Sarsa</option>
<option value='34846'>Savli</option>
<option value='34897'>Sayajiganj</option>
<option value='34865'>Shaher kotda</option>
<option value='34828'>Shahpur</option>
<option value='34912'>Shehra</option>
<option value='34814'>Sidhpur</option>
<option value='34939'>Sihor</option>
<option value='34946'>Sojitra</option>
<option value='34945'>Somnath</option>
<option value='34947'>Songadh</option>
<option value='34831'>Surat city east</option>
<option value='34813'>Surat city north</option>
<option value='34790'>Surat city west</option>
<option value='34902'>Talaja</option>
<option value='34958'>Talala</option>
<option value='34903'>Tankara</option>
<option value='34815'>Thasra</option>
<option value='34864'>Umbergaon</option>
<option value='34799'>Umreth</option>
<option value='34877'>Una</option>
<option value='34866'>Unjha</option>
<option value='34926'>Upleta</option>
<option value='34931'>Vadgam</option>
<option value='34871'>Vagdod</option>
<option value='34861'>Vaghodia</option>
<option value='34839'>Vagra</option>
<option value='34816'>Vav</option>
<option value='34862'>Vijapur</option>
<option value='34940'>Viramgam</option>
<option value='34784'>Visavadar</option>
<option value='34791'>Visnagar</option>


 </select>
<div id="alertMessage_const_Gujarat"></div>

<div class="view-results"><a onclick="navigateToConstituencyPageFrmSpeclPge('selectedConstituencyInSpecialPage','alertMessage_const_Gujarat')" href="javascript:{}">view constituency</a></div>
</div>
</td>
</tr>
</table>
</td>
</tr>


</table>
</td></tr>
</table>
 
  </div>
<a title="Gujarat 2012 Vidhan Sabha Election" href="specialPageAction.action?specialPageId=14" class="btn btn-primary btn-large">Click Here To View Himachal Pradesh Election 2012</a>


<div id="genderInfoDiv">
<div id="genderAnalysisDiv"></div></div>

<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});

$(document).ready(function() {
	//$('#selectedElectionYears').selectmenu('refresh');
  getElectionInfo();
  getDistrictWiseElectionResults();
  getWonLeadCandidates();
   
  $("#dynamicText").insertBefore($("#dynaUpload"));
  $("#dynamicText").css("margin","10px 0px");
});

function getDistrictWiseElectionResults(){
  var districtId = $("#selectDistrictWise").val();
  if(districtId == 0)
     return;
  var jsObj = {
				electionId:202,
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
				electionId:202,
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
				electionId:202,
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
		 str+='  <th style="width: 153px;">Constituency</th>';
		 str+='  <th style="width: 103px;">District</th>';
		 str+='  <th style="width: 325px;">Candidate</th>';
		 str+='  <th style="width: 75px;">Party</th> ';
		 str+='  <th style="width: 75px;">Status</th>';
		 str+='  <th style="width: 85px;">Assests</th>';
		 str+='  <th style="width: 85px;">liabilities</th>';
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
		 str+='  <th style="width: 135px;">Constituency</th>';
		 str+='  <th style="width: 285px;">Candidate</th>';
		 str+='  <th style="width: 217px;">Party</th> ';
		 str+='  <th  style="width: 69px;">Status</th>';
		 str+='  <th style="width: 80px;">Assests</th>';
		 str+='  <th style="width: 85px;">liabilities</th>';
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
	
	str +='<fieldset  style="verdana,sans-serif;font-weight:bold;margin-top:14px;">';
	str +='<legend style="border-radius: 3px;background:#21B2ED;font-family: verdana;">Gujarat HighLights</legend>';
	str +='<div class="hglgts">';
	if(results != null && results!='')
	{
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



function buildImportantCnadidatesData(results){

	if(results == null || results == "null"){
	 $('#importantCandidateHeadingDiv').hide();
	  return false;
	}
var str='';


    str+='<div class="span10" style="border:1px solid #c3c3c3;">';

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

	str +='<option value="126">2007</option>';
	str +='	<option value="127">2002</option>';
	str +='	<option value="128">1998</option>';
	str +='	<option value="129">1995</option>';
	str +='	<option value="130">1990</option>';
	str +='	<option value="131">1985</option>';
	str +='	<option value="132">1980</option>';
	str +='	<option value="133">1975</option>';
	str +='</select>';
	str +='</td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	str +='<h3 style="background: none repeat scroll 0pt 0pt rgb(33, 178, 237); padding: 7px 0px 6px; color: rgb(255, 255, 255); margin-top: 13px; border-left-width: 0px; margin-left: 43px; font-size: 13px; border-radius: 3px 3px 3px 3px; text-align: center; width: 726px;">Partywise Male And Female Participation and their Performance In Gujarat <font color="pink">'+year+'</font> Assembly Election</h3>';

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
				specialPageId:"13",
	           
				task:"getHighLights"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSpecialPageHighLights.action?"+param;						
		
	callAjax(jsObj,url);
}


 getSpecialPageHighLights();



function getImportantCandidatesInfo()
{
	var jsObj = {
	           	electionId:202,
				task:"getImportantCandidatesInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getImportantCandidatesInfoAction.action?"+param;
	callAjax(jsObj,url);
}

 function showDetails(){

	 $('#showLink').hide();
	 $('#hideLink').show();
	 $('#refreshDiv').show();


	 $('.importantPersonsDivClass').show();

 }
 function hideDetails(){

	$('#showLink').show();
	$('#hideLink').hide();
	$('#refreshDiv').hide();

	$('.importantPersonsDivClass').hide('slow');

 }
 function buildGujaratElectionResult(myResults)
{
	if(myResults!=null && myResults.electionLiveResultVOList!=null &&  myResults.electionLiveResultVOList.length>0)
	{
	var electionResult= '';	
	electionResult+='<div id="gujratResultsHeader">';
	electionResult+='<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">';
	electionResult+='Party Wise Results Of Gujarat Vidhan Sabha 2012</h3>';
	electionResult+='</div>';
	electionResult+='<div id="GujaratResultBody" >';
	electionResult+='<span id="gujratResultsBody1" style ="width:450px;float:left;margin:11px;">';
	electionResult+='<table class="table table-bordered table-striped">';
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
	electionResult+='<span id="gujratResultsBody2" style ="width:450px;float:left">';
	electionResult+='<table class="gujaratTableDiv table table-bordered table-striped">';
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
	electionResult+='<span  id="GujaratResultGraph" style ="width:400px;float:right;border-radius:5px;box-shadow:0px 1px 2px #aaa;margin-right:5px;padding:2px;">';
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
	chart.draw(data,{width:400, height: 250, title:'Gujarat Vidhan Sabha Election 2012 Lead/Won Chart'});
	}
}


getImportantCandidatesInfo();

</script>