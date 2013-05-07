<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script>
<LINK rel="stylesheet" type="text/css" href="styles/jqueryDataTable/css/datatable.css"> 
<script src="js/jqueryDataTable/jquery.dataTables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.columnFilter.js"></script>


<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});

$(document).ready(function() {
	//$('#selectedElectionYears').selectmenu('refresh');
  
   
  $("#dynamicText").insertBefore($("#dynaUpload"));
  $("#dynamicText").css("margin","10px 0px");
});

</script>


<style type="text/css">
.nominationresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.nominationresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.nominationresulttable tr:nth-child(odd){background:#e5e5e5;}
.nominationresulttable tr:nth-child(even){background:#f3f3f3;}
.nominationresulttable th{padding:5px;background:#489CDF;color:#fff;}
.nominationresulttable{margin-top:5px;}
.pft-sec{ border: 1px solid #DDDDDD;padding-left: 10px;padding-right: 5px;padding-bottom: 65px;}
.partyPreviousPerDiv,.assemblyEleDiv{border: 1px solid #dddddd; padding: 8px 14px; border-radius:3px;padding-bottom: 11px}
.partyPreviousPerDiv h4,.assemblyEleDiv h4,.headingStyle h4{margin-bottom: 15px;}
.partyPreviousPerDiv{margin-top:10px;margin-bottom:15px;}
.partyPreviousPerDiv a{margin-right:9px;}
.assemblyEleDiv a{margin-right:4px;}
.assemblyEleDiv{margin-bottom:15px;}
.voterLinksCls{clear: both; padding-right: 6px;}
.voterLinksCls p{padding:0px;font-size: 13px;
    text-align: justify;margin-top:8px;padding-bottom: 10px; display: table;}
	.voterLinksCls span{font-family:arial;font-size:12px;line-height:1.5em;}
	.voterLinksCls p a{float:right;}
#ElectionStats{font-family:arial;}
#ElectionStats span{line-height:1.5em;}

#KarnatakaResultBody{display:inline-block;background:#EDF9FF;width:100%;padding:4px;}
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

</style>
<div style="margin-bottom:10px;">
	<img src="images/specialPage/Karnataka_banner.jpg" style="align:center;width:985px;">
</div>

<div id="upComing" class="breadcrumb headingStyle">
<h3 class="resulth3">Karnataka 2013 Elections Statistics</h3>

<br><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Total Assembly Constituencies - <font color="#05A8E9">224</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">36</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">15</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">173</font></span>

  <div style="text-align:justify;margin:10px;padding:10px;"> 
















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
<a class="btn btn-success pull-right" href="electionLiveResultsAnalysisAction.action?electionId=229&electionType=2&stateId=12">Compare Now !!</a>
</p>

</div>

<div class="span4 popover" style="width: 275px;">
<h4 class="popover-title" style="width: 262px;">State Ministers Trend </h4>
<p class="popover-content" style="width: 264px;">
Analyze the present status of Karnataka state ministers for 2008-2013 duration.
<a class="btn btn-success pull-right" href="ministerAnalysisAction.action?electionId=229&electionType=2&stateId=12&eventType=ministers">Analyze Now !!</a>
</p>

</div>
<div class="span4 popover" style="width: 279px; left: 13px;">
<h4 class="popover-title">Key Candidates Present Status</h4>
<p class="popover-content">
Analyze the present status of key candidates from Karnataka State
<a class="btn btn-success pull-right" href="ministerAnalysisAction.action?electionId=229&electionType=2&stateId=12&eventType=impCandidates">View Now !!</a>
</p>

</div>
</div>
</div>
<!-- Analysis Tools END-->

<div id="electionResultDivMain" style="padding-bottom:10px;">
  <div id="electionResultDiv"></div>
</div>
	
<div id="districtWiseElectionResultDiv" style="margin-top:40px;clear:both;margin-bottom:40px">
<div><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;border-radius:3px 3px 0px 0px;width: 100%;">DISTRICT WISE Party Wise Results Of Karnataka Assembly Elections 2013</h3></div>
  <div style="background:#EDF9FF;width:100%;padding:4px;">
	<select id="selectDistrictWise" style="margin-top:10px;width:200px;margin-bottom:10px;margin-left:20px;"  onchange="getDistrictWiseElectionResults()">
		<option value="120">Bagalkot</option>
		<option value="139">Bangalore Rural</option>
		<option value="138">Bangalore Urban</option>
		<option value="119">Belgaum</option>
		<option value="130">Bellary</option>
		<option value="123">Bidar</option>
		<option value="121">Bijapur</option>
		<option value="145">Chamarajanagar</option>
		<option value="135">Chikmagalur</option>
		<option value="131">Chitradurga</option>
		<option value="142">Dakshina Kannada</option>
		<option value="132">Davanagere</option>
		<option value="127">Dharwad</option>
		<option value="126">Gadag</option>
		<option value="122">Gulbarga</option>
		<option value="141">Hassan</option>
		<option value="129">Haveri</option>
		<option value="143">Kodagu</option>
		<option value="137">Kolar</option>
		<option value="125">Koppal</option>
		<option value="140">Mandya</option>
		<option value="144">Mysore</option>
		<option value="124">Raichur</option>
		<option value="133">Shimoga</option>
		<option value="136">Tumkur</option>
		<option value="134">Udupi</option>
		<option value="128">Uttara Kannada</option>
	</select>
  <div id="districtWiseElectionResultDisplayDiv"></div>
  </div>
</div>


<div id="candidateWinLeadElectionResultDiv" style="margin-top:40px;clear:both;margin-bottom:40px">
<div><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">WINNING/LEADING CANDIDATES INFO FOR KARNATAKA  ASSEMBLY ELECTIONS 2013</h3></div>
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

	<table>
	   <tr>

		<!------------LEFT SIDE PANEL ------------>
		<td  width="45%" valign="top">
		  <!-- <h3 class="resulth3">Karnataka 2013 Elections Statistics</h3>
		   <div id="ElectionStats" style="margin-bottom:10px;">
			<span> Total Assembly Constituencies - <font color="#05A8E9">224</font></span><br>
			<span> SC Constituencies - <font color="#05A8E9">36</font> </span><br>
			<span>ST Constituencies - <font color="#05A8E9">15</font></span><br>
			<span>General Constituencies - <font color="#05A8E9">173</font></span><br>
		  </div>-->
		  <h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;margin-bottom:6px;">Schedule for the Karnataka Legislative Assembly, 2013	</h4>
			<!-- schdule table -->
			<table class="nominationresulttable">
				<tr>
				 <th>Poll Event</th>
				 <th>For 224 Assembly Constituency</th>
				<tr>
				<tr>
				 <td>Issue of Notification</td>
				 <td>10.4.2013 (Wednesday)</td>
				</tr>
				<tr>
				 <td>Last date for making Nominations</td>
				 <td>17.4.2013 (Wednesday)</td>
				</tr>
				<tr>
				 <td>Scrutiny of Nominations</td>
				 <td>18.4.2013 (Thursday)</td>
				</tr>
				<tr>
				 <td>Last date for withdrawal of candidature</td>
				 <td>20.4.2013 (Saturday)</td>
				</tr>
				<tr>
				 <td>Date of Poll</td>
				 <td>5.5.2013 (Sunday) 8.00AM to 5.00PM</td>
				</tr>
				<tr>
				 <td>Counting of Votes</td>
				 <td>8.5.2013 (Wednesday)</td>
				</tr>
				<tr>
				 <td>Date before which election shall be completed</td>
				 <td>11.5.2013 (Saturday)</td>
				</tr>

			</table>

			<!-- schdule table End -->
			
			 <!--<table class="whitegloss">
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
						<a href="viewFeaturesAction.action">Click Here to Learn More...</a>
					 </div>
				    </div>
				 </td>
				 <td style="vertical-align: top;">
				  <div class="voterLinksCls">
					<p style="border-bottom:1px solid #ddd;padding-bottom: 10px;">
					  <span>The Right To <font style="font-weight:bold;">VOTE</font> is the Foundation Of Democracy. Your Vote is your Voice.Don't lose your Voice.</span>
					   <a style="margin-top: 5px;" class="btn btn-primary" href="voters.action">Voter's Guide</a>
					</p>
					
					 <p style="border-bottom:1px solid #ddd;padding-bottom: 10px;"> 
					 <span><font style="font-weight:bold;">Voters Pulse</font>
					 Now You can drive your survey's through Party Analyst.</span><br>
					   <a style="" href="VotersPulse.action" class="btn btn-success">View Now</a>
					 </p>

					<p>
					  <span>You've come to the Right Place! The New Party Analyst.</span><br>
					  <a alt="Explore About PartyAnalyst Image"  class="btn btn-warning inline" href="viewFeaturesAction.action">Explore!</a>
					</p>
				   </div>
				 </td>
			</tr>
		  </table>-->


		</td>
		<td width="2%"></td>
		<!------------RIGHT SIDE PANEL ------------>
		<td width="43%" valign="top" class="breadcrumb headingStyle">

		 <!-- party previous performance -->
		  <div class="partyPreviousPerDiv">
		    <h4>View Party Previous Performances From 1983 - 2008</h4>

			<c:if test="${loginStatus == 'out'}"> 
			  <a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">BJP</a>
			</c:if>
			<c:if test="${loginStatus == 'out'}"> 
			  <a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BSP</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">BSP</a>
			</c:if>
			<c:if test="${loginStatus == 'out'}"> 
			 <a href="partyResultsAction.action?selectedPartyShortName=CPI&selectedPartyId=265&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=265&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">CPI</a>
			</c:if>
			<c:if test="${loginStatus == 'out'}"> 
			 <a href="partyResultsAction.action?selectedPartyShortName=CPM&selectedPartyId=269&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=269&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">CPM</a>
			</c:if>
			<c:if test="${loginStatus == 'out'}"> 
			 <a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">INC</a>
			</c:if>
			<c:if test="${loginStatus == 'out'}"> 
			 <a href="partyResultsAction.action?selectedPartyShortName=KCP&selectedPartyId=455&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=455&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">KCP</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">KCP</a>
			</c:if>
			<c:if test="${loginStatus == 'out'}"> 
			 <a href="partyResultsAction.action?selectedPartyShortName=KRRS&selectedPartyId=476&selectedElectionTypeName=Assembly&selectedLocationName=Karnataka&electionType=2&reportLevel=State&stateSelectName=12&partySelectName=476&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">KRRS</a>
			</c:if>
			<c:if test="${sessionScope.USER == null}"> 
			   <a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">KRRS</a>
			</c:if>
		  </div>
		<!-- party previous performance End -->
		<!-- previous Assembly Ele Results -->
		 <div class="assemblyEleDiv">
			<h4>Previous Karnataka Assembly Elections Results </h4>
			<a  href="electionDetailsReportAction.action?electionId=119&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=2008" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2008</a>
			<a  href="electionDetailsReportAction.action?electionId=125&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=2004" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2004</a>
			<a  href="electionDetailsReportAction.action?electionId=120&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1999" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1999</a>
			<a  href="electionDetailsReportAction.action?electionId=121&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1994" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1994</a>
			<a  href="electionDetailsReportAction.action?electionId=122&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1989" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1989</a>
			<a  href="electionDetailsReportAction.action?electionId=123&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1985" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1985</a>
			<a  href="electionDetailsReportAction.action?electionId=124&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1983" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1983</a>

		 </div>

		<!-- previous Assembly Ele Results End-->
		<!-- district and constituency Div -->
		<div class="breadcrumb headingStyle" style="margin-top:10px;">
		 <h4>View Karnataka Districts And Constituencies Results</h4>
		  <table style="width: 100%;">
		   <tr>
			 <td>
				<div class="selectHeading">
					<span class="selectDivStyle">Karnataka Districts</span>
					<p style="padding:5px;text-align:left;">Know About Your District </p>
					<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
						<option value="0">Select District</option>
						<option value="120">Bagalkot</option>
						<option value="139">Bangalore Rural</option>
						<option value="138">Bangalore Urban</option>
						<option value="119">Belgaum</option>
						<option value="130">Bellary</option>
						<option value="123">Bidar</option>
						<option value="121">Bijapur</option>
						<option value="145">Chamarajanagar</option>
						<option value="135">Chikmagalur</option>
						<option value="131">Chitradurga</option>
						<option value="142">Dakshina Kannada</option>
						<option value="132">Davanagere</option>
						<option value="127">Dharwad</option>
						<option value="126">Gadag</option>
						<option value="122">Gulbarga</option>
						<option value="141">Hassan</option>
						<option value="129">Haveri</option>
						<option value="143">Kodagu</option>
						<option value="137">Kolar</option>
						<option value="125">Koppal</option>
						<option value="140">Mandya</option>
						<option value="144">Mysore</option>
						<option value="124">Raichur</option>
						<option value="133">Shimoga</option>
						<option value="136">Tumkur</option>
						<option value="134">Udupi</option>
						<option value="128">Uttara Kannada</option>
			   </select>
			   <div id="alertMessage_district"></div>
			   <div class="view-results">
			    <a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>
				</div>
			 </td>
		   
			 <td>
				<div class="selectHeading">
					<span class="selectDivStyle">Karnataka Constituencies</span>
					<p style="padding:5px;text-align:left;">Know About Your Constituencies </p>
					<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
						<option value="0">Select Constituency</option>
						<option value="32707">Afzalpur</option>
						<option value="32719">Aland</option>
						<option value="32850">Anekal</option>
						<option value="32898">Ankola</option>
						<option value="32681">Arabhavi</option>
						<option value="32871">Arkalgud</option>
						<option value="32867">Arsikere</option>
						<option value="32676">Athani</option>
						<option value="32725">Aurad</option>
						<option value="32845">B T M Layout</option>
						<option value="32702">Babaleshwar</option>
						<option value="32696">Badami</option>
						<option value="32697">Bagalkot</option>
						<option value="32813">Bagepalli</option>
						<option value="32899">Bagewadi</option>
						<option value="32689">Bailhongal</option>
						<option value="32900">Baindur</option>
						<option value="32901">Ballolli </option>
						<option value="32849">Bangalore South</option>
						<option value="32820">Bangarapet</option>
						<option value="32902">Bannur</option>
						<option value="32878">Bantval</option>
						<option value="32720">Basavakalyan</option>
						<option value="32701">Basavana Bagevadi</option>
						<option value="32843">Basavanagudi</option>
						<option value="32903">Belgaum</option>
						<option value="32685">Belgaum Dakshin</option>
						<option value="32686">Belgaum Rural</option>
						<option value="32684">Belgaum Uttar</option>
						<option value="32766">Bellary</option>
						<option value="32767">Bellary City</option>
						<option value="32904">Bellavi</option>
						<option value="32873">Belthangady</option>
						<option value="32868">Belur</option>
						<option value="32906">Bethamangala </option>
						<option value="32785">Bhadravati</option>
						<option value="32724">Bhalki</option>
						<option value="32907">Bharamasagara </option>
						<option value="32908">Bharathinagar</option>
						<option value="32752">Bhatkal</option>
						<option value="32723">Bidar</option>
						<option value="32722">Bidar South</option>
						<option value="32909">Bijapur</option>
						<option value="32703">Bijapur City</option>
						<option value="32695">Bilgi</option>
						<option value="32910">Bilgi</option>
						<option value="32911">Binnypet</option>
						<option value="32912">Birur</option>
						<option value="32848">Bommanahalli</option>
						<option value="32913">Brahmavar</option>
						<option value="32758">Byadgi</option>
						<option value="32825">Byatarayana Pura</option>
						<option value="32791">Byndoor</option>
						<option value="32834">C V Ramannagar</option>
						<option value="32771">Challakere</option>
						<option value="32890">Chamaraja</option>
						<option value="32896">Chamarajanagar</option>
						<option value="32841">Chamrajpet</option>
						<option value="32888">Chamundesh Wari</option>
						<option value="32782">Channagiri</option>
						<option value="32858">Channapatna</option>
						<option value="32842">Chickpet</option>
						<option value="32814">Chikkaballapur</option>
						<option value="32801">Chikkanayakanahalli</option>
						<option value="32914">Chikkodi </option>
						<option value="32675">Chikkodi Sadalga</option>
						<option value="32798">Chikmagalur</option>
						<option value="32715">Chincholi</option>
						<option value="32816">Chintamani</option>
						<option value="32772">Chitradurga</option>
						<option value="32713">Chittapur</option>
						<option value="32828">Dasarahalli</option>
						<option value="32779">Davanagere North</option>
						<option value="32780">Davanagere South</option>
						<option value="32915">Davangere</option>
						<option value="32729">Devadurga</option>
						<option value="32852">Devanahalli</option>
						<option value="32700">Devar Hippargi</option>
						<option value="32744">Dharwad</option>
						<option value="32916">Dharwad Rural</option>
						<option value="32853">Doddaballapur</option>
						<option value="32739">Gadag</option>
						<option value="32837">Gandhi Nagar</option>
						<option value="32917">Gandsi</option>
						<option value="32735">Gangawati</option>
						<option value="32812">Gauribidanur</option>
						<option value="32682">Gokak</option>
						<option value="32839">Govindraj Nagar</option>
						<option value="32808">Gubbi</option>
						<option value="32918">Gulbarga</option>
						<option value="32717">Gulbarga Dakshin</option>
						<option value="32716">Gulbarga Rural</option>
						<option value="32718">Gulbarga Uttar</option>
						<option value="32919">Guledgud</option>
						<option value="32897">Gundlupet</option>
						<option value="32712">Gurmitkal</option>
						<option value="32761">Hadagalli</option>
						<option value="32762">Hagaribommana Halli</option>
						<option value="32749">Haliyal</option>
						<option value="32755">Hangal</option>
						<option value="32894">Hanur</option>
						<option value="32777">Harapanahalli</option>
						<option value="32778">Harihar</option>
						<option value="32869">Hassan</option>
						<option value="32757">Haveri</option>
						<option value="32831">Hebbal</option>
						<option value="32886">Heggadadevankote</option>
						<option value="32759">Hirekerur</option>
						<option value="32773">Hiriyur</option>
						<option value="32775">Holalkere</option>
						<option value="32920">Holehonnur </option>
						<option value="32870">Holenarasipur</option>
						<option value="32721">Homnabad</option>
						<option value="32783">Honnali</option>
						<option value="32774">Hosadurga</option>
						<option value="32851">Hosakote</option>
						<option value="32921">Hosanagar</option>
						<option value="32922">Hospet</option>
						<option value="32923">Hubli</option>
						<option value="32746">Hubli Dharwad Central</option>
						<option value="32745">Hubli Dharwad East</option>
						<option value="32747">Hubli Dharwad West</option>
						<option value="32924">Hubli Rural</option>
						<option value="32680">Hukkeri</option>
						<option value="32925">Huliyurdurga</option>
						<option value="32926">Hulsoor </option>
						<option value="32927">Humnabad</option>
						<option value="32698">Hungund</option>
						<option value="32885">Hunsur</option>
						<option value="32928">Huvin Hippargi</option>
						<option value="32705">Indi</option>
						<option value="32776">Jagalur</option>
						<option value="32694">Jamkhandi</option>
						<option value="32929">Jayamahal</option>
						<option value="32846">Jayanagar</option>
						<option value="32708">Jewargi</option>
						<option value="32824">K R Pura</option>
						<option value="32800">Kadur</option>
						<option value="32677">Kagwad</option>
						<option value="32748">Kalghatgi</option>
						<option value="32930">Kallambella</option>
						<option value="32931">Kalmala</option>
						<option value="32932">Kamalapur </option>
						<option value="32764">Kampli</option>
						<option value="32734">Kanakagiri</option>
						<option value="32857">Kanakapura</option>
						<option value="32794">Kapu</option>
						<option value="32795">Karkal</option>
						<option value="32750">Karwar</option>
						<option value="32933">Keragodu</option>
						<option value="32687">Khanapur</option>
						<option value="32934">Kiragaval</option>
						<option value="32688">Kittur</option>
						<option value="32821">Kolar</option>
						<option value="32819">Kolar Gold Field</option>
						<option value="32895">Kollegal</option>
						<option value="32737">Koppal</option>
						<option value="32807">Koratagere</option>
						<option value="32935">Kottur</option>
						<option value="32889">Krishnaraja</option>
						<option value="32884">Krishnaraja Nagar</option>
						<option value="32865">Krishnarajpet</option>
						<option value="32678">Kudachi</option>
						<option value="32769">Kudligi</option>
						<option value="32751">Kumta</option>
						<option value="32792">Kundapura</option>
						<option value="32743">Kundgol</option>
						<option value="32804">Kunigal</option>
						<option value="32936">Kurugodu</option>
						<option value="32733">Kushtagi</option>
						<option value="32730">Lingsugur</option>
						<option value="32860">Maddur</option>
						<option value="32811">Madhugiri</option>
						<option value="32881">Madikeri</option>
						<option value="32855">Magadi</option>
						<option value="32847">Mahadevapura</option>
						<option value="32829">Mahalakshmi Layout</option>
						<option value="32859">Malavalli</option>
						<option value="32830">Malleshwaram</option>
						<option value="32822">Malur</option>
						<option value="32862">Mandya</option>
						<option value="32877">Mangalore</option>
						<option value="32875">Mangalore City North</option>
						<option value="32876">Mangalore City South</option>
						<option value="32728">Manvi</option>
						<option value="32732">Maski</option>
						<option value="32781">Mayakonda</option>
						<option value="32861">Melukote</option>
						<option value="32770">Molakalmuru</option>
						<option value="32874">Moodabidri</option>
						<option value="32699">Muddebihal</option>
						<option value="32692">Mudhol</option>
						<option value="32797">Mudigere</option>
						<option value="32818">Mulbagal</option>
						<option value="32937">Mundargi</option>
						<option value="32864">Nagamangala</option>
						<option value="32704">Nagthan</option>
						<option value="32887">Nanjangud</option>
						<option value="32891">Narasimharaja</option>
						<option value="32741">Nargund</option>
						<option value="32742">Navalgund</option>
						<option value="32854">Nelamangala</option>
						<option value="32674">Nippani</option>
						<option value="32844">Padmanaba Nagar</option>
						<option value="32938">Pandavapura</option>
						<option value="32939">Parasgad</option>
						<option value="32810">Pavagada</option>
						<option value="32883">Piriyapatna</option>
						<option value="32832">Pulakeshinagar</option>
						<option value="32879">Puttur</option>
						<option value="32727">Raichur</option>
						<option value="32726">Raichur Rural</option>
						<option value="32838">Rajaji Nagar</option>
						<option value="32827">Rajarajeshwarinagar</option>
						<option value="32856">Ramanagaram</option>
						<option value="32691">Ramdurg</option>
						<option value="32760">Ranibennur</option>
						<option value="32679">Raybag</option>
						<option value="32740">Ron</option>
						<option value="32940">Sadalga</option>
						<option value="32790">Sagar</option>
						<option value="32872">Sakleshpur</option>
						<option value="32768">Sandur</option>
						<option value="32941">Sankeshwar</option>
						<option value="32942">Santhemarahalli </option>
						<option value="32833">Sarvagnanagar</option>
						<option value="32943">Sathanur</option>
						<option value="32690">Saundattiyellamma</option>
						<option value="32714">Sedam</option>
						<option value="32944">Shahabad </option>
						<option value="32710">Shahapur</option>
						<option value="32836">Shanti Nagar</option>
						<option value="32756">Shiggaon</option>
						<option value="32788">Shikaripura</option>
						<option value="32786">Shimoga</option>
						<option value="32784">Shimoga Rural</option>
						<option value="32738">Shirahatti</option>
						<option value="32835">Shivajinagar</option>
						<option value="32709">Shorapur</option>
						<option value="32866">Shravanabelagola</option>
						<option value="32815">Sidlaghatta</option>
						<option value="32706">Sindgi</option>
						<option value="32731">Sindhanur</option>
						<option value="32809">Sira</option>
						<option value="32753">Sirsi</option>
						<option value="32765">Siruguppa</option>
						<option value="32945">Somwarpet</option>
						<option value="32789">Sorab</option>
						<option value="32796">Sringeri</option>
						<option value="32817">Srinivaspur</option>
						<option value="32863">Srirangapatna</option>
						<option value="32880">Sullia</option>
						<option value="32946">Surathkal</option>
						<option value="32893">T Narasipur</option>
						<option value="32799">Tarikere</option>
						<option value="32693">Terdal</option>
						<option value="32947">Tikota</option>
						<option value="32802">Tiptur</option>
						<option value="32787">Tirthahalli</option>
						<option value="32948">Tumkur</option>
						<option value="32805">Tumkur City</option>
						<option value="32806">Tumkur Rural</option>
						<option value="32803">Turuvekere</option>
						<option value="32949">Uchagaon</option>
						<option value="32793">Udupi</option>
						<option value="32950">Ullal</option>
						<option value="32951">Uttarahalli</option>
						<option value="32952">Varthur</option>
						<option value="32892">Varuna</option>
						<option value="32953">Vemgal</option>
						<option value="32840">Vijay Nagar</option>
						<option value="32763">Vijayanagara</option>
						<option value="32882">Virajpet</option>
						<option value="32954">Vittal</option>
						<option value="32711">Yadgir</option>
						<option value="32823">Yelahanka</option>
						<option value="32736">Yelburga</option>
						<option value="32754">Yellapur</option>
						<option value="32683">Yemkanmardi</option>
						<option value="32826">Yeshvanthapura</option>
					  </select>
					<div id="alertMessage_const_Gujarat"></div>

					<div class="view-results">
						<a onclick="navigateToConstituencyPageFrmSpeclPge('selectedConstituencyInSpecialPage','alertMessage_const_Gujarat')" href="javascript:{}">view constituency</a>
					</div>
				 </div>
			  </td>
		    </tr>
		
		 </table>
		</div>
		 <!-- district and constituency Div End -->
		</td>
	  </tr>
	</table>

  </div>
</div>

<script type="text/javascript">
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
function buildSpecialPageHightLights(results)
{
	var str ='';
	str +='<fieldset  style="verdana,sans-serif;font-weight:bold;margin-top:14px;">';
	str +='<legend style="border-radius: 3px;background:#21B2ED;font-family: verdana;">Karnataka HighLights</legend>';
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
	$("#specialPageHighLight").html(str);
}

function getDistrictWiseElectionResults(){
  var districtId = $("#selectDistrictWise").val();
  if(districtId == 0)
     return;
  var jsObj = {
				electionId:229,
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
				electionId:229,
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
				electionId:229,
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



function getSpecialPageHighLights()
{
var jsObj = {
				specialPageId:"20",
	           
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
	electionResult+='Party Wise Results Of Karnataka Assembly Elections 2013</h3>';
	electionResult+='</div>';
	electionResult+='<div id="KarnatakaResultBody" >';
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
	chart.draw(data,{width:400, height: 250, title:'Meghalaya Vidhan Sabha Election 2013 Lead/Won Chart'});
	}
}


function getImportantCandidatesInfo()
{
	var jsObj = {
	           	electionId:229,
				task:"getImportantCandidatesInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getImportantCandidatesInfoAction.action?"+param;
	callAjax(jsObj,url);
}


function buildImportantCnadidatesData(results){

	if(results == null || results == "null"){
	 $('#importantCandidateHeadingDiv').css("display","none");
	  return;
	}
var str='';


    str+='<div class="span10" style="border:1px solid #c3c3c3;">';

	if(results.length == 0){
	  $('#importantCandidateHeadingDiv').css("display","none");
	  return;
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
function setImage(img)
{
  img.src = "pictures/profiles/member.jpg";
}

getElectionInfo();
getDistrictWiseElectionResults();
getWonLeadCandidates();
getSpecialPageHighLights();
getImportantCandidatesInfo();


</script>
