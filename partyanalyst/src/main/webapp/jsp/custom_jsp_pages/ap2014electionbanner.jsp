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
.nominationresulttable th{padding:5px;background:#489CDF;color:#fff;text-align:center;}
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
.mainwrapper {background:#5d5d5d;}

</style>

<div style="margin-bottom:10px;">
<img src="images/specialPage/apassembly2014.jpg" style="align:center;width:975px;">
</div>

<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->


<div id="upComing" class="">
<div style="text-align:justify;margin:10px;padding:10px;"> 

<table>
<tr>

<td valign="top">
<table>
	<td>
	<h3 class="resulth3">AndhraPradesh 2014 Elections Statistics</h3>
<div id="ElectionStats" style="margin-bottom:10px;">
	<table class="ap2014 nominationresulttable" >
	<thead>
			<tr>
				<th></th>
				<th>Andhra Pradesh</th>
				<th>Telangana</th>
			</tr>
	</thead>
	<tbody>
			<tr>
				<th>Total Constituencies</th>
				<th>175</th>
				<th>119</th>
			</tr>
			<tr>
				<th>SC Constituencies</th>
				<th>29</th>
				<th>19</th>
			</tr>
			<tr>
				<th>ST Constituencies</th>
				<th>7</th>
				<th>12</th>
			</tr>
			<tr>
				<th>General Constituencies</th>
				<th>139</th>
				<th>88</th>
			</tr>
	</tbody>
	</table>
</div>
	
	<h4 style=" border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;">Schedule for the AndhraPradesh Legislative Assembly 2014	</h4>
	<table class="ap2014 nominationresulttable" >
<tbody><tr>
<th>Poll Event</th>
<th>Phase 1</th>
<th>Phase 2</th>
</tr>
<tr>
<td>Announcement &amp; Issue of Press Note</td>
<td align="center" colspan="2"></td>
</tr>
<tr>
<td>Issue of Notification</td>
<td align="center">Wednesday, 02 Apr 2014</td>
<td align="center">Saturday 12 Apr 2014</td>
</tr>
<tr>
<td>Last Date for filing Nominations</td>
<td align="center">Wednesday, 09 Apr 2014</td>
<td align="center">Saturday 19 Apr 2014</td>
</tr>
<tr>
<td>Scrutiny of Nominations</td>
<td align="center">Thursday, 10 Apr 2014</td>
<td align="center">Monday, 21 Apr 2014</td>
</tr>
<tr>
<td>Last date for withdrawal of Candidature</td>
<td align="center">Saturday, 12 Apr 2014</td>
<td align="center">Wednesday, 23 Apr 2014</td>
</tr>
<tr>
<td>Date of Poll</td>
<td align="center">Wednesday, 30 Apr 2014</td>
<td align="center">Wednesday, 7 May 2014</td>
</tr>
<tr>
<td>Counting of Votes on</td>
<td align="center" colspan="2">Friday, 16 May 2014</td>
</tr>
<tr>
<td>Date of election being completed</td>
<td align="center" colspan="2">Wednesday, 28 May 2014</td>
</tr>
<tr>
<th>Total Constituencies</th>
<th align="center">119</th>
<th align="center">175</th>
</tr>
</tbody></table>
</td>
 <td width="2%"></td>

	<!------------RIGHT SIDE PANEL ------------>
<td width="43%" valign="top" class="breadcrumb">
<table style="padding-left:20px" width="98%" valign="top">
<!--<h3 style="">AndhraPradesh Assembly Election Analysis</h3>-->

<tr>
<div id="electionResultDivMain" style="padding-bottom:10px;">
  <div id="electionResultDiv"></div>
</div>
<!--<tr>
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
</tr>-->

<td colspan="2">
<table class="breadcrumb">
<tr><th colspan="5">
<h4>View Party Previous Performances From 1978 - 2009</h4>
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
<a href="partyResultsAction.action?selectedPartyShortName=TDP&selectedPartyId=872&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=872&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">TDP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
TDP</a>
</c:if>
</th>

<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>

<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>

<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=TRS&selectedPartyId=886&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=886&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">TRS</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
TRS</a>
</c:if>
</th>

<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=YSRC&selectedPartyId=1117&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=1117&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">YSRC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
YSRC</a>
</c:if>
</th> <br>

<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=PRP&selectedPartyId=662&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=662&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">PRP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
PRP</a>
</c:if>
</th>

</tr>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=CPI&selectedPartyId=265&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=265&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=CPM&selectedPartyId=269&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=269&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPM</a>
</c:if>
</th>

<th style="padding-right: 27px;">

<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=AIMIM&selectedPartyId=72&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=72&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">AIMIM</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
AIMIM</a>
</c:if>
</th>


<th style="padding-right: 27px;">

<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BSP</a>
</c:if>
</th>

<th style="padding-right: 27px;">

<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=LSP&selectedPartyId=514&selectedElectionTypeName=Assembly&selectedLocationName=Andhra+Pradesh&electionType=2&reportLevel=State&stateSelectName=1&partySelectName=514&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">LSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
LSP</a>
</c:if>
</th>

</tr>
</table>
</td>
</tr>

<tr><td colspan="7" class="breadcrumb pre-year-links"> 
<span style="clear: both; display: inline-block; width: 100%;">Previous AndhraPradesh Assembly Elections Results </span>

<a  href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=2009" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2009</a>


<a  href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=2004" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2004</a>


<a href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=1999" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png">1999</a>


<a href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=1994" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png">1994</a>


<a  href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=1989" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1989</a>


<a  href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=1985" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1985</a>


<a href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=1983"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1983</a>


<a  href="electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra Pradesh&electionType=Assembly&electionTypeId=2&year=1978"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1978</a>

</td></tr>
<tr> <td>
<table class="breadcrumb" style="margin-top:10px;">
<tr>
<td width="50%" colspan="2" >
<span>View AndhraPradesh Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">AndhraPradesh Districts</span>
	<p style="padding:5px;text-align:left;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
<option value="1">Adilabad</option>
<option value="22">Anantapur</option>
<option value="23">Chittoor</option>
<option value="20">Cuddapah</option>
<option value="14">East Godavari</option>
<option value="17">Guntur</option>
<option value="5">Hyderabad</option>
<option value="3">Karimnagar</option>
<option value="10">Khammam</option>
<option value="16">Krishna</option>
<option value="21">Kurnool</option>
<option value="7">Mahbubnagar</option>
<option value="4">Medak</option>
<option value="8">Nalgonda</option>
<option value="19">Nellore</option>
<option value="2">Nizamabad</option>
<option value="18">Prakasam</option>
<option value="6">Rangareddi</option>
<option value="11">Srikakulam</option>
<option value="13">Visakhapatnam</option>
<option value="12">Vizianagaram</option>
<option value="9">Warangal</option>
<option value="15">West Godavari</option>
   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">AndhraPradesh Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value='8'>SIRPUR</option>
<option value='4'>CHENNUR</option>
<option value='295'>BELLAMPALLI</option>
<option value='296'>MANCHERIAL</option>
<option value='2'>ASIFABAD</option>
<option value='5'>KHANAPUR</option>
<option value='1'>ADILABAD</option>
<option value='3'>BOATH</option>
<option value='7'>NIRMAL</option>
<option value='6'>MUDHOLE</option>
<option value='10'>ARMOOR</option>
<option value='13'>BODHAN</option>
<option value='15'>JUKKAL</option>
<option value='12'>BANSWADA</option>
<option value='18'>YELLAREDDY</option>
<option value='16'>KAMAREDDY</option>
<option value='342'>NIZAMABAD URBAN</option>
<option value='343'>NIZAMABAD RURAL</option>
<option value='11'>BALKONDA</option>
<option value='321'>KORATLA</option>
<option value='23'>JAGTIAL</option>
<option value='322'>DHARMAPURI</option>
<option value='318'>RAMAGUNDAM</option>
<option value='26'>MANTHANI</option>
<option value='30'>PEDDAPALLI</option>
<option value='24'>KARIMNAGAR</option>
<option value='20'>CHOPPADANDI</option>
<option value='323'>VEMULAWADA</option>
<option value='31'>SIRCILLA</option>
<option value='319'>MANAKONDUR</option>
<option value='21'>HUZURABAD</option>
<option value='320'>HUSNABAD</option>
<option value='40'>SIDDIPET</option>
<option value='35'>MEDAK</option>
<option value='36'>NARAYANKHED</option>
<option value='32'>ANDOLE</option>
<option value='37'>NARSAPUR</option>
<option value='41'>ZAHIRABAD</option>
<option value='39'>SANGAREDDY</option>
<option value='337'>PATANCHERU</option>
<option value='336'>DUBBAK</option>
<option value='34'>GAJWEL</option>
<option value='57'>MEDCHAL</option>
<option value='367'>MALKAJGIRI</option>
<option value='345'>QUTHBULLAPUR</option>
<option value='346'>KUKATPALLY</option>
<option value='347'>UPPAL</option>
<option value='56'>IBRAHIMPATNAM</option>
<option value='348'>LAL BAHADUR NAGAR</option>
<option value='349'>MAHESWARAM</option>
<option value='351'>RAJENDRANAGAR</option>
<option value='350'>SERLINGAMPALLY</option>
<option value='55'>CHEVELLA</option>
<option value='58'>PARGI</option>
<option value='60'>VIKARABAD</option>
<option value='59'>TANDUR</option>
<option value='50'>MUSHEERABAD</option>
<option value='49'>MALAKPET</option>
<option value='315'>AMBERPET</option>
<option value='47'>KHAIRATABAD</option>
<option value='314'>JUBILEE HILLS</option>
<option value='51'>SANATHNAGAR</option>
<option value='316'>NAMPALLY</option>
<option value='46'>KARWAN</option>
<option value='317'>GOSHAMAHAL</option>
<option value='44'>CHARMINAR</option>
<option value='43'>CHANDRAYANGUTTA</option>
<option value='54'>YAKUTPURA</option>
<option value='313'>BAHADURPURA</option>
<option value='52'>SECUNDERABAD</option>
<option value='53'>SECUNDERABAD CANTONMENT</option>
<option value='66'>KODANGAL</option>
<option value='335'>NARAYANPET</option>
<option value='68'>MAHBUBNAGAR</option>
<option value='64'>JADCHERLA</option>
<option value='369'>DEVARKADRA</option>
<option value='69'>MAKTHAL</option>
<option value='73'>WANAPARTHY</option>
<option value='63'>GADWAL</option>
<option value='62'>ALAMPUR</option>
<option value='70'>NAGARKURNOOL</option>
<option value='61'>ACHAMPET</option>
<option value='65'>KALWAKURTHI</option>
<option value='71'>SHADNAGAR</option>
<option value='67'>KOLLAPUR</option>
<option value='77'>DEVERKONDA</option>
<option value='338'>NAGARJUNA SAGAR</option>
<option value='79'>MIRYALGUDA</option>
<option value='339'>HUZURNAGAR</option>
<option value='78'>KODAD</option>
<option value='84'>SURYAPET</option>
<option value='82'>NALGONDA</option>
<option value='80'>MUNGODE</option>
<option value='75'>BHONGIR</option>
<option value='81'>NAKREKAL</option>
<option value='85'>TUNGATURTHI</option>
<option value='74'>ALAIR</option>
<option value='89'>JANGAON</option>
<option value='87'>GHANPUR</option>
<option value='362'>PALAKURTHI</option>
<option value='86'>DORNAKAL</option>
<option value='91'>MAHBUBABAD</option>
<option value='93'>NARSAMPET</option>
<option value='94'>PARKAL</option>
<option value='363'>WARANGAL WEST</option>
<option value='364'>WARANGAL EAST</option>
<option value='97'>WARDHANNAPET</option>
<option value='365'>BHUPALPALLE</option>
<option value='92'>MULUG</option>
<option value='324'>PINAPAKA</option>
<option value='107'>YELLANDU</option>
<option value='101'>KHAMMAM</option>
<option value='104'>PALAIR</option>
<option value='103'>MADHIRA</option>
<option value='325'>WYRA</option>
<option value='105'>SATHUPALLI</option>
<option value='102'>KOTHAGUDEM</option>
<option value='326'>ASWARAOPET</option>
<option value='100'>BHADRACHALAM</option>
<option value='111'>ICHAPURAM</option>
<option value='352'>PALASA</option>
<option value='117'>TEKKALI</option>
<option value='114'>PATHAPATNAM</option>
<option value='116'>SRIKAKULAM</option>
<option value='108'>AMADALAVALASA</option>
<option value='109'>ETCHERLA</option>
<option value='112'>NARASANNAPETA</option>
<option value='353'>RAJAM</option>
<option value='113'>PALAKONDA</option>
<option value='360'>KURUPAM</option>
<option value='124'>PARVATHIPURAM</option>
<option value='125'>SALUR</option>
<option value='122'>BOBBILI</option>
<option value='120'>CHEEPURUPALLI</option>
<option value='121'>GAJAPATHINAGARAM</option>
<option value='361'>NELLIMARLA</option>
<option value='129'>VIZIANAGARAM</option>
<option value='127'>SRUNGAVARAPUKOTA</option>
<option value='368'>BHIMILI</option>
<option value='354'>VISAKHAPATNAM EAST</option>
<option value='355'>VISAKHAPATNAM SOUTH</option>
<option value='356'>VISAKHAPATNAM NORTH</option>
<option value='357'>VISAKHAPATNAM WEST</option>
<option value='358'>GAJUWAKA</option>
<option value='134'>CHODAVARAM</option>
<option value='136'>MADUGULA</option>
<option value='359'>ARAKU VALLEY</option>
<option value='138'>PADERU</option>
<option value='133'>ANAKAPALLI</option>
<option value='141'>PENDURTHI</option>
<option value='135'>ELAMANCHILI</option>
<option value='140'>PAYAKARAOPETA</option>
<option value='137'>NARSIPATNAM</option>
<option value='163'>TUNI</option>
<option value='157'>PRATHIPAD</option>
<option value='156'>PITHAPURAM</option>
<option value='307'>KAKINADA RURAL</option>
<option value='155'>PEDDAPURAM</option>
<option value='147'>ANAPARTHY</option>
<option value='308'>KAKINADA CITY</option>
<option value='159'>RAMACHANDRAPURAM</option>
<option value='153'>MUMMIDIVARAM</option>
<option value='146'>Amalapuram</option>
<option value='160'>RAZOLE</option>
<option value='310'>GANNAVARAM</option>
<option value='152'>KOTHAPETA</option>
<option value='309'>MANDAPETA</option>
<option value='303'>RAJANAGARAM</option>
<option value='304'>RAJAHMUNDRY CITY</option>
<option value='305'>RAJAHMUNDRY RURAL</option>
<option value='149'>JAGGAMPETA</option>
<option value='306'>RAMPACHODAVARAM</option>
<option value='172'>KOVVUR</option>
<option value='366'>NIDADAVOLE</option>
<option value='181'>ACHANTA</option>
<option value='174'>PALACOLE</option>
<option value='173'>NARASAPUR</option>
<option value='167'>BHIMAVARAM</option>
<option value='179'>UNDI</option>
<option value='178'>TANUKU</option>
<option value='177'>TADEPALLIGUDEM</option>
<option value='180'>UNGUTUR</option>
<option value='169'>DENDULUR</option>
<option value='170'>ELURU</option>
<option value='171'>GOPALPURAM</option>
<option value='176'>POLAVARAM</option>
<option value='168'>CHINTALAPUDI</option>
<option value='194'>TIRUVURU</option>
<option value='193'>NUZVID</option>
<option value='184'>GANNAVARAM</option>
<option value='185'>GUDIVADA</option>
<option value='187'>KAIKALUR</option>
<option value='327'>PEDANA</option>
<option value='328'>MACHILIPATNAM</option>
<option value='182'>AVANIGADDA</option>
<option value='329'>PAMARRU</option>
<option value='330'>PENAMALURU</option>
<option value='196'>VIJAYAWADA WEST</option>
<option value='331'>VIJAYAWADA CENTRAL</option>
<option value='195'>VIJAYAWADA EAST</option>
<option value='191'>MYLAVARAM</option>
<option value='192'>NANDIGAMA</option>
<option value='186'>JAGGAYYAPET</option>
<option value='210'>PEDDAKURAPADU</option>
<option value='215'>TADIKONDA</option>
<option value='206'>MANGALAGIRI</option>
<option value='211'>PONNUR</option>
<option value='217'>VEMURU</option>
<option value='213'>REPALLE</option>
<option value='216'>TENALI</option>
<option value='209'>BAPATLA</option>
<option value='212'>PRATHIPADU</option>
<option value='312'>GUNTUR WEST</option>
<option value='311'>GUNTUR EAST</option>
<option value='199'>CHILAKALURIPET</option>
<option value='208'>NARASARAOPET</option>
<option value='214'>SATTENAPALLI</option>
<option value='207'>VINUKONDA</option>
<option value='203'>GURZALA</option>
<option value='205'>MACHERLA</option>
<option value='344'>YERRAGONDAPALEM</option>
<option value='221'>DARSI</option>
<option value='228'>PARCHUR</option>
<option value='218'>ADDANKI</option>
<option value='219'>CHIRALA</option>
<option value='229'>SANTHANUTHALAPADU</option>
<option value='227'>ONGOLE</option>
<option value='223'>KANDUKUR</option>
<option value='225'>KONDEPI</option>
<option value='226'>MARKAPUR</option>
<option value='222'>GIDDALUR</option>
<option value='224'>KANIGIRI</option>
<option value='232'>KAVALI</option>
<option value='241'>ATMAKUR</option>
<option value='233'>KOVUR</option>
<option value='340'>NELLORE CITY</option>
<option value='341'>NELLORE RURAL</option>
<option value='236'>SARVEPALLI</option>
<option value='231'>GUDUR</option>
<option value='237'>SULLURPET</option>
<option value='239'>VENKATAGIRI</option>
<option value='238'>UDAYAGIRI</option>
<option value='242'>BADVEL</option>
<option value='252'>RAJAMPET</option>
<option value='243'>KADAPA</option>
<option value='246'>KODUR</option>
<option value='248'>RAYACHOTY</option>
<option value='251'>PULIVENDLA</option>
<option value='245'>KAMALAPURAM</option>
<option value='244'>JAMMALAMADUGU</option>
<option value='250'>PRODDATUR</option>
<option value='249'>MYDUKUR</option>
<option value='254'>ALLAGADDA</option>
<option value='332'>SRISAILAM</option>
<option value='261'>NANDIKOTKUR</option>
<option value='260'>KURNOOL</option>
<option value='263'>PANYAM</option>
<option value='262'>NANDYAL</option>
<option value='333'>BANAGANAPALLE</option>
<option value='257'>DHONE</option>
<option value='264'>PATTIKONDA</option>
<option value='258'>KODUMUR</option>
<option value='265'>YEMMIGANUR</option>
<option value='334'>MANTRALAYAM</option>
<option value='253'>ADONI</option>
<option value='255'>ALUR</option>
<option value='276'>RAYADURG</option>
<option value='279'>URAVAKONDA</option>
<option value='297'>GUNTAKAL</option>
<option value='278'>TADPATRI</option>
<option value='277'>SINGANAMALA</option>
<option value='298'>ANANTAPUR URBAN</option>
<option value='272'>KALYANDURG</option>
<option value='299'>RAPTADU</option>
<option value='273'>MADAKASIRA</option>
<option value='270'>HINDUPUR</option>
<option value='275'>PENUKONDA</option>
<option value='300'>PUTTAPARTHI</option>
<option value='267'>DHARMAVARAM</option>
<option value='271'>KADIRI</option>
<option value='290'>THAMBALLAPALLE</option>
<option value='285'>PILERU</option>
<option value='294'>MADANPALLE</option>
<option value='286'>PUNGANUR</option>
<option value='280'>CHANDRAGIRI</option>
<option value='291'>TIRUPATI</option>
<option value='289'>SRIKALAHASTI</option>
<option value='288'>SATYAVEDU</option>
<option value='283'>NAGARI</option>
<option value='301'>GANGADHARA NELLORE</option>
<option value='281'>CHITTOOR</option>
<option value='302'>PUTHALAPATTU</option>
<option value='284'>Palamaner</option>
<option value='282'>KUPPAM</option>
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
	electionResult+='Party Wise Results Of Tripura Vidhan Sabha 2013</h3>';
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
	chart.draw(data,{width:400, height: 250, title:'Tripura Vidhan Sabha Election 2013 Lead/Won Chart'});
	}
}

 getSpecialPageHighLights();



</script>