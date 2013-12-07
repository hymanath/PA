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
<img src="images/specialPage/Mp Banner.jpg" style="align:center;width:975px;">
</div>

<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->


<div id="upComing" class="breadcrumb">
<div style="text-align:justify;margin:10px;padding:10px;"> 

<table>
<tr>

<td  width="45%" valign="top">
<table>
		
	
<h3 class="resulth3">Madhya Pradesh 2013 Elections Statistics</h3>
<div id="ElectionStats" style="margin-bottom:10px;">
<span> Total Assembly Constituencies - <font color="#05A8E9">230</font></span><br>
<span> SC Constituencies - <font color="#05A8E9">35</font> </span><br>
<span>ST Constituencies - <font color="#05A8E9">47</font></span><br>
<span>General Constituencies - <font color="#05A8E9">-</font></span><br>
</div>
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;">Schedule for the Madhya Pradesh Legislative Assembly, 2013	</h4>
		
		
			<td>
			<table class="table table-bordered table-striped table-hover">
<tr>
<th>Poll Event</th>
<th>For 90 Assembly Constituency
</th>

</tr>
<tr>
<td>Issue of Notification</td>
<td>01.11.2013(Friday)
</td>

</tr>
<tr>
<td>Last date for making Nominations</td>
<td>08.11.2013(Friday)
</td>
</tr>
<td>Scrutiny of Nominations</td>
<td>09.11.2013(Saturday)
</td>
</tr>
<tr>
<!--<td>Last date for withdrawal of
candidature</td>-->
<td>Last date for withdrawal of candidature</td>
<td>11.11.2013(Monday)
</td>
</tr>
<tr>
<td>Date of Poll</td>
<td>25.11.2013(Monday)
</td>
</tr>
<tr>
<td>Counting of Votes</td>
<td>11.12.2013 (Wednesday)
</td>
</tr>
<tr>
<td>Date before which election process
shall be completed</td>
<td>11.12.2013 (Wednesday)
</td>
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
Madhya Pradesh Assembly Election Analysis</h3>

<tr>
<div id="electionResultDivMain" style="padding-bottom:10px;">
  <div id="electionResultDiv"></div>
</div>
<tr>
	<td>
		<table class="table table-bordered table-hover">
			<thead>
			<tr><th>PARTY</th><th>2013</th><th>2008</th><th>CHANGE</th></tr>
			</thead>
			<tbody>
			<tr><td>CPI</td><td>1</td><td>1</td><td>--</td></tr>
			<tr><td>INC</td><td>10</td><td>10</td><td>--</td></tr>
			<tr><td>CPM</td><td>49</td><td>46</td><td>+3</td></tr>
			<tr><td>OTHERS</td><td>0</td><td>3</td><td>-3</td></tr>
			</tbody>
		</table>
		<span style="color:red;">*</span> Change is compared to 2008 Election results
		
	</td style="margin-bottom:20px;">
</tr>

<td colspan="2">
<table class="breadcrumb">
<tr><th colspan="5">
<h4>View Party Previous Performances From 1983 - 2008</h4>
</th></tr>
<tr>
<!--<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BSP</a>
</c:if>
</th>-->
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Madhya Pradesh&electionType=2&reportLevel=State&stateSelectName=14&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Madhya Pradesh&electionType=2&reportLevel=State&stateSelectName=14&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=CPI&selectedPartyId=265&selectedElectionTypeName=Assembly&selectedLocationName=Madhya Pradesh&electionType=2&reportLevel=State&stateSelectName=14&partySelectName=265&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=CPM&selectedPartyId=269&selectedElectionTypeName=Assembly&selectedLocationName=Madhya Pradesh&electionType=2&reportLevel=State&stateSelectName=14&partySelectName=269&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
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
<span style="clear: both; display: inline-block; width: 100%;">Previous Madhya Pradesh Assembly Elections Results </span>


<a  href="electionDetailsReportAction.action?electionId=248&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=2008" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2008</a>


<a href="electionDetailsReportAction.action?electionId=247&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=2003" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2003</a>


<a  href="electionDetailsReportAction.action?electionId=246&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=1998" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1998</a>


<a  href="electionDetailsReportAction.action?electionId=245&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=1993" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1993</a>


<a href="electionDetailsReportAction.action?electionId=244&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=1988"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1990</a>


<a  href="electionDetailsReportAction.action?electionId=243&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=1983"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1985</a>

<a  href="electionDetailsReportAction.action?electionId=242&stateID=14&stateName=Madhya Pradesh&electionType=Assembly&electionTypeId=2&year=1983"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1980</a>

</td></tr>
<tr> <td>
<table class="breadcrumb" style="margin-top:10px;">
<tr>
<td width="50%" colspan="2" >
<span>View Madhya Pradesh Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Madhya Pradesh Districts</span>
	<p style="padding:5px;text-align:left;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
	<option value="390">Sheopur	</option>	
<option value="391">Morena	</option>	
<option value="392">Bhind	</option>	
<option value="393">Gwalior	</option>	
<option value="394">Datia	</option>	
<option value="395">Shivpuri	</option>	
<option value="396">Guna	</option>	
<option value="397">Tikamgarh	</option>	
<option value="398">Chhatarpur	</option>	
<option value="399">Panna	</option>	
<option value="400">Sagar	</option>	
<option value="401">Damoh	</option>	
<option value="402">Satna	</option>	
<option value="403">Rewa	</option>	
<option value="404">Umaria	</option>	
<option value="405">Shahdol	</option>	
<option value="406">Sidhi	</option>	
<option value="407">Neemuch	</option>	
<option value="408">Mandsaur	</option>	
<option value="409">Ratlam	</option>	
<option value="410">Ujjain	</option>	
<option value="411">Shajapur	</option>	
<option value="412">Dewas	</option>	
<option value="413">Jhabua	</option>	
<option value="414">Dhar	</option>	
<option value="415">Indore	</option>	
<option value="416">West Nimar	</option>	
<option value="417">Barwani	</option>	
<option value="418">East Nimar	</option>	
<option value="419">Rajgarh	</option>	
<option value="420">Vidisha	</option>	
<option value="421">Bhopal	</option>	
<option value="422">Sehore	</option>	
<option value="423">Raisen	</option>	
<option value="424">Betul	</option>	
<option value="425">Harda	</option>	
<option value="426">Hoshangabad	</option>	
<option value="427">Katni	</option>	
<option value="428">Jabalpur	</option>	
<option value="429">Narsimhapur	</option>	
<option value="430">Dindori	</option>	
<option value="431">Mandla	</option>	
<option value="432">Chhindwara	</option>	
<option value="433">Seoni	</option>	
<option value="434">Balaghat	</option>	

   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Madhya Pradesh &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value="	40935">Sheopur	</option>
	<option value="	40936">Vijaypur	</option>
	<option value="	40937">Sabalgarh	</option>
	<option value="	40938">Joura	</option>
	<option value="	40939">Sumawali	</option>
	<option value="	40940">Morena	</option>
	<option value="	40941">Dimani	</option>
	<option value="	40942">Ambah	</option>
	<option value="	40943">Ater	</option>
	<option value="	40944">Bhind	</option>
	<option value="	40945">Lahar	</option>
	<option value="	40946">Mehgaon	</option>
	<option value="	40947">Gohad	</option>
	<option value="	40948">Gwalior Rural	</option>
	<option value="	40949">Gwalior	</option>
	<option value="	40950">Gwalior East	</option>
	<option value="	40951">Gwalior South	</option>
	<option value="	40952">Bhitarwar	</option>
	<option value="	40953">Dabra	</option>
	<option value="	40954">Sewda	</option>
	<option value="	40955">Bhander	</option>
	<option value="	40956">Datia	</option>
	<option value="	40957">Karera	</option>
	<option value="	40958">Pohari	</option>
	<option value="	40959">Shivpuri	</option>
	<option value="	40960">Pichhore	</option>
	<option value="	40961">Kolaras	</option>
	<option value="	40962">Bamori	</option>
	<option value="	40963">Guna	</option>
	<option value="	40964">Chachoura	</option>
	<option value="	40965">Raghogarh	</option>
	<option value="	40966">Ashok Nagar	</option>
	<option value="	40967">Chanderi	</option>
	<option value="	40968">Mungaoli	</option>
	<option value="	40969">Bina	</option>
	<option value="	40970">Khurai	</option>
	<option value="	40971">Surkhi	</option>
	<option value="	40972">Deori	</option>
	<option value="	40973">Rehli	</option>
	<option value="	40974">Naryoli	</option>
	<option value="	40975">Sagar	</option>
	<option value="	40976">Banda	</option>
	<option value="	40977">Tikamgarh	</option>
	<option value="	40978">Jatara	</option>
	<option value="	40979">Prithvipur	</option>
	<option value="	40980">Niwari	</option>
	<option value="	40981">Khargapur	</option>
	<option value="	40982">Maharajpur	</option>
	<option value="	40983">Chandla	</option>
	<option value="	40984">Rajnagar	</option>
	<option value="	40985">Chhatarpur	</option>
	<option value="	40986">Bijawar	</option>
	<option value="	40987">Malhara	</option>
	<option value="	40988">Pathariya	</option>
	<option value="	40989">Damoh	</option>
	<option value="	40990">Jabera	</option>
	<option value="	40991">Hatta	</option>
	<option value="	40992">Pawai	</option>
	<option value="	40993">Gunnaor	</option>
	<option value="	40994">Panna	</option>
	<option value="	40995">Chitrakoot	</option>
	<option value="	40996">Raigaon	</option>
	<option value="	40997">Satna	</option>
	<option value="	40998">Nagod	</option>
	<option value="	40999">Maihar	</option>
	<option value="	41000">Amarpatan	</option>
	<option value="	41001">Rampur Baghelan	</option>
	<option value="	41002">Sirmour	</option>
	<option value="	41003">Semariya	</option>
	<option value="	41004">Teonthar	</option>
	<option value="	41005">Mauganj	</option>
	<option value="	41006">Deotalab	</option>
	<option value="	41007">Mangawan	</option>
	<option value="	41008">Rewa	</option>
	<option value="	41009">Gurh	</option>
	<option value="	41010">Churhat	</option>
	<option value="	41011">Sidhi	</option>
	<option value="	41012">Sihawal	</option>
	<option value="	41013">Chitrangi	</option>
	<option value="	41014">Singrauli	</option>
	<option value="	41015">Devsar	</option>
	<option value="	41016">Dhauhani	</option>
	<option value="	41017">Beohari	</option>
	<option value="	41018">Jaisinghnagar	</option>
	<option value="	41019">Jaitpur	</option>
	<option value="	41020">Kotma	</option>
	<option value="	41021">Anuppur	</option>
	<option value="	41022">Pushprajgarh	</option>
	<option value="	41023">Bandhavgarh	</option>
	<option value="	41024">Manpur	</option>
	<option value="	41025">Barwara	</option>
	<option value="	41026">Vijayraghogarh	</option>
	<option value="	41027">Murwara	</option>
	<option value="	41028">Bahoriband	</option>
	<option value="	41029">Patan	</option>
	<option value="	41030">Bargi	</option>
	<option value="	41031">Jabalpur Purba	</option>
	<option value="	41032">Jabalpur Uttar	</option>
	<option value="	41033">Jabalpur Cantt.	</option>
	<option value="	41034">Jabalpur Paschim	</option>
	<option value="	41035">Panagar	</option>
	<option value="	41036">Sihora	</option>
	<option value="	41037">Shahpura	</option>
	<option value="	41038">Dindori	</option>
	<option value="	41039">Bichhiya	</option>
	<option value="	41040">Niwas	</option>
	<option value="	41041">Mandla	</option>
	<option value="	41042">Baihar	</option>
	<option value="	41043">Lanji	</option>
	<option value="	41044">Paraswada	</option>
	<option value="	41045">Balaghat	</option>
	<option value="	41046">Waraseoni	</option>
	<option value="	41047">Katangi	</option>
	<option value="	41048">Barghat	</option>
	<option value="	41049">Seoni	</option>
	<option value="	41050">Keolari	</option>
	<option value="	41051">Lakhnadon	</option>
	<option value="	41052">Gotegaon	</option>
	<option value="	41053">Narsingpur	</option>
	<option value="	41054">Tendukheda	</option>
	<option value="	41055">Gadarwara	</option>
	<option value="	41056">Junnardeo	</option>
	<option value="	41057">Amarwara	</option>
	<option value="	41058">Churai	</option>
	<option value="	41059">Saunsar	</option>
	<option value="	41060">Chhindwara	</option>
	<option value="	41061">Parasia	</option>
	<option value="	41062">Pandhurna	</option>
	<option value="	41063">Multai	</option>
	<option value="	41064">Amla	</option>
	<option value="	41065">Betul	</option>
	<option value="	41066">Ghoradongri	</option>
	<option value="	41067">Bhainsdehi	</option>
	<option value="	41068">Timarni	</option>
	<option value="	41069">Harda	</option>
	<option value="	41070">Seoni Malwa	</option>
	<option value="	41071">Hoshangabad	</option>
	<option value="	41072">Sohagpur	</option>
	<option value="	41073">Pipariya	</option>
	<option value="	41074">Udaipura	</option>
	<option value="	41075">Bhojpur	</option>
	<option value="	41076">Sanchi	</option>
	<option value="	41077">Silwani	</option>
	<option value="	41078">Vidisha	</option>
	<option value="	41079">Basoda	</option>
	<option value="	41080">Kurwai	</option>
	<option value="	41081">Sironj	</option>
	<option value="	41082">Shamshabad	</option>
	<option value="	41083">Berasia	</option>
	<option value="	41084">Bhopal Uttar	</option>
	<option value="	41085">Narela	</option>
	<option value="	41086">Bhopal Dakshin Paschim	</option>
	<option value="	41087">Bhopal Madhya	</option>
	<option value="	41088">Govindpura	</option>
	<option value="	41089">Huzur	</option>
	<option value="	41090">Budhni	</option>
	<option value="	41091">Ashta	</option>
	<option value="	41092">Ichhawar	</option>
	<option value="	41093">Sehore	</option>
	<option value="	41094">Narsinghgarh	</option>
	<option value="	41095">Biaora	</option>
	<option value="	41096">Rajgarh	</option>
	<option value="	41097">Khilchipur	</option>
	<option value="	41098">Sarangpur	</option>
	<option value="	41099">Susner	</option>
	<option value="	41100">Agar	</option>
	<option value="	41101">Shajapur	</option>
	<option value="	41102">Shujalpur	</option>
	<option value="	41103">Kalapipal	</option>
	<option value="	41104">Sonkatch	</option>
	<option value="	41105">Dewas	</option>
	<option value="	41106">Hatpipliya	</option>
	<option value="	41107">Khategaon	</option>
	<option value="	41108">Bagali	</option>
	<option value="	41109">Mandhata	</option>
	<option value="	41110">Harsud	</option>
	<option value="	41111">Khandwa	</option>
	<option value="	41112">Pandhana	</option>
	<option value="	41113">Nepanagar	</option>
	<option value="	41114">Burhanpur	</option>
	<option value="	41115">Bhikangaon	</option>
	<option value="	41116">Badwah	</option>
	<option value="	41117">Maheshwar	</option>
	<option value="	41118">Kasrawad	</option>
	<option value="	41119">Khargone	</option>
	<option value="	41120">Bhagwanpura	</option>
	<option value="	41121">Sendhawa	</option>
	<option value="	41122">Rajpur	</option>
	<option value="	41123">Pansemal	</option>
	<option value="	41124">Badwani	</option>
	<option value="	41125">Alirajpur	</option>
	<option value="	41126">Jobat	</option>
	<option value="	41127">Jhabua	</option>
	<option value="	41128">Thandla	</option>
	<option value="	41129">Petlawad	</option>
	<option value="	41130">Sardarpur	</option>
	<option value="	41131">Gandhwani	</option>
	<option value="	41132">Kukshi	</option>
	<option value="	41133">Manawar	</option>
	<option value="	41134">Dharampuri	</option>
	<option value="	41135">Dhar	</option>
	<option value="	41136">Badnawar	</option>
	<option value="	41137">Depalpur	</option>
	<option value="	41138">Indore 1	</option>
	<option value="	41139">Indore 2	</option>
	<option value="	41140">Indore 3	</option>
	<option value="	41141">Indore 4	</option>
	<option value="	41142">Indore 5	</option>
	<option value="	41143">Dr Ambedkar Nagar Mhow	</option>
	<option value="	41144">Rau	</option>
	<option value="	41145">Sanwer	</option>
	<option value="	41146">Nagada Khachrod	</option>
	<option value="	41147">Mahidpur	</option>
	<option value="	41148">Tarana	</option>
	<option value="	41149">Ghatiya	</option>
	<option value="	41150">Ujjain Uttar	</option>
	<option value="	41151">Ujjain Dakshin	</option>
	<option value="	41152">Badnagar	</option>
	<option value="	41153">Ratlam Rural	</option>
	<option value="	41154">Ratlam City	</option>
	<option value="	41155">Sailana	</option>
	<option value="	41156">Jaora	</option>
	<option value="	41157">Alot	</option>
	<option value="	41158">Mandsour	</option>
	<option value="	41159">Malhargarh	</option>
	<option value="	41160">Suwasra	</option>
	<option value="	41161">Garoth	</option>
	<option value="	41162">Manasa	</option>
	<option value="	41163">Neemuch	</option>
	<option value="	41164">Jawad	</option>

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
				electionId:216,
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
				electionId:216,
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
				electionId:216,
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


function getSpecialPageHighLights()
{
var jsObj = {
				specialPageId:"17",
	           
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
	electionResult+='Party Wise Results Of Madhya Pradesh Vidhan Sabha 2013</h3>';
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
	chart.draw(data,{width:400, height: 250, title:'Madhya Pradesh Vidhan Sabha Election 2013 Lead/Won Chart'});
	}
}

 getSpecialPageHighLights();



</script>