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
<img src="images/specialPage/Rajasthan Banner.jpg" style="align:center;width:975px;">
</div>

<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->


<div id="upComing" class="breadcrumb">
<div style="text-align:justify;margin:10px;padding:10px;"> 

<table>
<tr>

<td  width="45%" valign="top">
<table>
		
	
<h3 class="resulth3">Rajastan 2013 Elections Statistics</h3>
<div id="ElectionStats" style="margin-bottom:10px;">
<span> Total Assembly Constituencies - <font color="#05A8E9">200</font></span><br>
<span> SC Constituencies - <font color="#05A8E9">34</font> </span><br>
<span>ST Constituencies - <font color="#05A8E9">25</font></span><br>
<span>General Constituencies - <font color="#05A8E9">-</font></span><br>
</div>
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;">Schedule for the Rajasthan Legislative Assembly, 2013	</h4>
		
		
			<td>
			<table class="table table-bordered table-striped table-hover">
<tr>
<th>Poll Event</th>
<th>For 200 Assembly Constituency</th>

</tr>
<tr>
<td>Issue of Notification</td>
<td>18.10.2013(Firday)
</td>

</tr>
<tr>
<td>Last date for making Nominations</td>
<td>25.10.2013(Friday)
</td>
</tr>
<td>Scrutiny of Nominations</td>
<td>26.10.2013(Saturday)

</td>
</tr>
<tr>
<!--<td>Last date for withdrawal of
candidature</td>-->
<td>Last date for withdrawal of candidature</td>
<td>28.10.2013(Monday)
</td>
</tr>
<tr>
<td>Date of Poll</td>
<td>11.11.2013(Monday)
</td>
</tr>
<tr>
<td>Counting of Votes</td>
<td>8.12.2013 (Sunday)
</td>
</tr>
<tr>
<td>Date before which election process
shall be completed</td>
<td>-</td>
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
Rajasthan Assembly Election Analysis</h3>

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
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Rajasthan&electionType=2&reportLevel=State&stateSelectName=22&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Rajasthan&electionType=2&reportLevel=State&stateSelectName=22&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=CPI&selectedPartyId=265&selectedElectionTypeName=Assembly&selectedLocationName=Rajasthan&electionType=2&reportLevel=State&stateSelectName=22&partySelectName=265&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=CPM&selectedPartyId=269&selectedElectionTypeName=Assembly&selectedLocationName=Rajasthan&electionType=2&reportLevel=State&stateSelectName=22&partySelectName=269&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
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
<span style="clear: both; display: inline-block; width: 100%;">Previous Rajasthan Assembly Elections Results </span>


<a  href="electionDetailsReportAction.action?electionId=239&stateID=22&stateName=Rajasthan&electionType=Assembly&electionTypeId=2&year=2008" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2008</a>


<a href="electionDetailsReportAction.action?electionId=231&stateID=22&stateName=Rajasthan&electionType=Assembly&electionTypeId=2&year=2003" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2003</a>


<a  href="electionDetailsReportAction.action?electionId=232&stateID=22&stateName=Rajasthan&electionType=Assembly&electionTypeId=2&year=1998" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1998</a>


<a  href="electionDetailsReportAction.action?electionId=233&stateID=22&stateName=Rajasthan&electionType=Assembly&electionTypeId=2&year=1993" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1993</a>


<a href="electionDetailsReportAction.action?electionId=234&stateID=22&stateName=Rajasthan&electionType=Assembly&electionTypeId=2&year=1988"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1985</a>


<a  href="electionDetailsReportAction.action?electionId=235&stateID=22&stateName=Rajasthan&electionType=Assembly&electionTypeId=2&year=1983"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1990</a>

</td></tr>
<tr> <td>
<table class="breadcrumb" style="margin-top:10px;">
<tr>
<td width="50%" colspan="2" >
<span>View Rajasthan Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Rajasthan Districts</span>
	<p style="padding:5px;text-align:left;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
	<option value="343">Ganganagar	</option>	
	<option value="344">Hanumangarh	</option>	
	<option value="345">Bikaner	</option>	
	<option value="346">Churu	</option>	
	<option value="347">Jhunjhunun	</option>	
	<option value="348">Alwar	</option>	
	<option value="349">Bharatpur	</option>	
	<option value="350">Dhaulpur	</option>	
	<option value="351">Karauli	</option>	
	<option value="352">Sawai Madhopur	</option>	
	<option value="353">Dausa	</option>	
	<option value="354">Jaipur	</option>	
	<option value="355">Sikar	</option>	
	<option value="356">Nagaur	</option>	
	<option value="357">Jodhpur	</option>	
	<option value="358">Jaisalmer	</option>	
	<option value="359">Barmer	</option>	
	<option value="360">Jalor	</option>	
	<option value="361">Sirohi	</option>	
	<option value="362">Pali	</option>	
	<option value="363">Ajmer	</option>	
	<option value="364">Tonk	</option>	
	<option value="365">Bundi	</option>	
	<option value="366">Bhilwara	</option>	
	<option value="367">Rajsamand	</option>	
	<option value="368">Udaipur	</option>	
	<option value="369">Dungarpur	</option>	
	<option value="370">Banswara	</option>	
	<option value="371">Chittaurgarh	</option>	
	<option value="372">Kota	</option>	
	<option value="373">Baran	</option>	
	<option value="374">Jhalawar	</option>	
   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Rajasthan Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value="	40456">Sadulshahar</option>
	<option value="	40457">Ganganagar</option>
	<option value="	40458">Karanpur</option>
	<option value="	40459">Suratgarh</option>
	<option value="	40460">Raisinghnagar</option>
	<option value="	40461">Anupgarh</option>
	<option value="	40462">Sangaria</option>
	<option value="	40463">Hanumangarh</option>
	<option value="	40464">Pilibanga</option>
	<option value="	40465">Nohar</option>
	<option value="	40466">Bhadra</option>
	<option value="	40467">Khajuwala</option>
	<option value="	40468">Bikaner West</option>
	<option value="	40469">Bikaner East</option>
	<option value="	40470">Kolayat</option>
	<option value="	40471">Lunkaransar</option>
	<option value="	40472">Dungargarh</option>
	<option value="	40473">Nokha</option>
	<option value="	40474">Sadulpur</option>
	<option value="	40475">Taranagar</option>
	<option value="	40476">Sardarshahar</option>
	<option value="	40477">Churu</option>
	<option value="	40478">Ratangarh</option>
	<option value="	40479">Sujangarh</option>
	<option value="	40480">Pilani</option>
	<option value="	40481">Surajgarh</option>
	<option value="	40482">Jhunjhunu</option>
	<option value="	40483">Mandawa</option>
	<option value="	40484">Nawalgarh</option>
	<option value="	40485">Udaipurwati</option>
	<option value="	40486">Khetri</option>
	<option value="	40487">Fatehpur</option>
	<option value="	40488">LACHMANGARH</option>
	<option value="	40489">Dhod</option>
	<option value="	40490">Sikar</option>
	<option value="	40491">DantaRamgarh</option>
	<option value="	40492">Khandela</option>
	<option value="	40493">Neem Ka Thana</option>
	<option value="	40494">Srimadhopur</option>
	<option value="	40495">Kotputli</option>
	<option value="	40496">Viratnagar</option>
	<option value="	40497">Shahpura</option>
	<option value="	40498">Chomu</option>
	<option value="	40499">Phulera</option>
	<option value="	40500">Dudu</option>
	<option value="	40501">Jhotwara</option>
	<option value="	40502">Amber</option>
	<option value="	40503">Jamwa Ramgarh</option>
	<option value="	40504">HawaMahal</option>
	<option value="	40505">Vidhyadhar Nagar</option>
	<option value="	40506">Civil Lines</option>
	<option value="	40507">KishanPole</option>
	<option value="	40508">Adarsh Nagar</option>
	<option value="	40509">Malviya Nagar</option>
	<option value="	40510">Sanganer</option>
	<option value="	40511">Bagru</option>
	<option value="	40512">Bassi</option>
	<option value="	40513">Chaksu</option>
	<option value="	40514">Tijara</option>
	<option value="	40515">Kishangarh Bas</option>
	<option value="	40516">Mundawar</option>
	<option value="	40517">Behror</option>
	<option value="	40518">Bansur</option>
	<option value="	40519">Thanagazi</option>
	<option value="	40520">Alwar Rural</option>
	<option value="	40521">Alwar Urban</option>
	<option value="	40522">Ramgarh</option>
	<option value="	40523">Rajgarh Laxmangarh</option>
	<option value="	40524">Kathumar</option>
	<option value="	40525">Kaman</option>
	<option value="	40526">Nagar</option>
	<option value="	40527">Deeg Kumher</option>
	<option value="	40528">Bharatpur</option>
	<option value="	40529">Nadbai</option>
	<option value="	40530">Weir</option>
	<option value="	40531">Bayana</option>
	<option value="	40532">Baseri</option>
	<option value="	40533">Bari</option>
	<option value="	40534">Dholpur</option>
	<option value="	40535">Rajakhera</option>
	<option value="	40536">Todabhim</option>
	<option value="	40537">Hindaun</option>
	<option value="	40538">Karauli</option>
	<option value="	40539">Sapotra</option>
	<option value="	40540">Bandikui</option>
	<option value="	40541">Mahuwa</option>
	<option value="	40542">Sikrai</option>
	<option value="	40543">Dausa</option>
	<option value="	40544">Lalsot</option>
	<option value="	40545">Gangapur</option>
	<option value="	40546">Bamanwas</option>
	<option value="	40547">Sawai Madhopur</option>
	<option value="	40548">Khandar</option>
	<option value="	40549">Malpura</option>
	<option value="	40550">Niwai</option>
	<option value="	40551">Tonk</option>
	<option value="	40552">Deoli Uniara</option>
	<option value="	40553">Kishangarh</option>
	<option value="	40554">Pushkar</option>
	<option value="	40555">Ajmer North</option>
	<option value="	40556">Ajmer South</option>
	<option value="	40557">Nasirabad</option>
	<option value="	40558">Beawar</option>
	<option value="	40559">Masuda</option>
	<option value="	40560">Kekri</option>
	<option value="	40561">Ladnun</option>
	<option value="	40562">Deedwana</option>
	<option value="	40563">Jayal</option>
	<option value="	40564">Nagaur</option>
	<option value="	40565">Khinwsar</option>
	<option value="	40566">Merta</option>
	<option value="	40567">Degana</option>
	<option value="	40568">Makrana</option>
	<option value="	40569">Parbatsar</option>
	<option value="	40570">Nawan</option>
	<option value="	40571">Jaitaran</option>
	<option value="	40572">Sojat</option>
	<option value="	40573">Pali</option>
	<option value="	40574">Marwar Junction</option>
	<option value="	40575">Bali</option>
	<option value="	40576">Sumerpur</option>
	<option value="	40577">Phalodi</option>
	<option value="	40578">Lohawat</option>
	<option value="	40579">Shergarh</option>
	<option value="	40580">Osian</option>
	<option value="	40581">Bhopalgarh</option>
	<option value="	40582">Sardarpura</option>
	<option value="	40583">Jodhpur</option>
	<option value="	40584">Soorsagar</option>
	<option value="	40585">Luni</option>
	<option value="	40586">Bilara</option>
	<option value="	40587">Jaisalmer</option>
	<option value="	40588">Pokaran</option>
	<option value="	40589">Sheo</option>
	<option value="	40590">Barmer</option>
	<option value="	40591">Baytoo</option>
	<option value="	40592">Pachpadra</option>
	<option value="	40593">Siwana</option>
	<option value="	40594">Gudha Malani</option>
	<option value="	40595">Chohtan</option>
	<option value="	40596">Ahore</option>
	<option value="	40597">Jalore</option>
	<option value="	40598">Bhinmal</option>
	<option value="	40599">Sanchore</option>
	<option value="	40600">Raniwara</option>
	<option value="	40601">Sirohi</option>
	<option value="	40602">Pindwara Abu</option>
	<option value="	40603">Reodar</option>
	<option value="	40604">Gogunda</option>
	<option value="	40605">Jhadol</option>
	<option value="	40606">Kherwara</option>
	<option value="	40607">Udaipur Rural</option>
	<option value="	40608">Udaipur</option>
	<option value="	40609">Mavli</option>
	<option value="	40610">Vallabhnagar</option>
	<option value="	40611">Salumber</option>
	<option value="	40612">Dhariawad</option>
	<option value="	40613">Dungarpur</option>
	<option value="	40614">Aspur</option>
	<option value="	40615">Sagwara</option>
	<option value="	40616">Chorasi</option>
	<option value="	40617">GHATOL</option>
	<option value="	40618">Garhi</option>
	<option value="	40619">Banswara</option>
	<option value="	40620">Bagidora</option>
	<option value="	40621">Kushalgarh</option>
	<option value="	40622">Kapasan</option>
	<option value="	40623">Begun</option>
	<option value="	40624">Chittorgarh</option>
	<option value="	40625">Nimbahera</option>
	<option value="	40626">Bari Sadri</option>
	<option value="	40627">Pratapgarh</option>
	<option value="	40628">Bhim</option>
	<option value="	40629">Kumbhalgarh</option>
	<option value="	40630">Rajsamand</option>
	<option value="	40631">Nathdwara</option>
	<option value="	40632">Asind</option>
	<option value="	40633">Mandal</option>
	<option value="	40634">Sahara</option>
	<option value="	40635">Bhilwara</option>
	<option value="	40636">Shahpura</option>
	<option value="	40637">Jahazpur</option>
	<option value="	40638">Mandalgarh</option>
	<option value="	40639">Hindoli</option>
	<option value="	40640">Keshoraipatan</option>
	<option value="	40641">Bundi</option>
	<option value="	40642">Pipalda</option>
	<option value="	40643">Sangod</option>
	<option value="	40644">Kota North</option>
	<option value="	40645">Kota South</option>
	<option value="	40646">Ladpura</option>
	<option value="	40647">Ramganj Mandi</option>
	<option value="	40648">Anta</option>
	<option value="	40649">Kishanganj</option>
	<option value="	40650">Baran Atru</option>
	<option value="	40651">Chhabra</option>
	<option value="	40652">Dag</option>
	<option value="	40653">Jhalrapatan</option>
	<option value="	40654">Khanpur</option>
	<option value="	40655">Manohar Thana</option>
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
	electionResult+='Party Wise Results Of Rajasthan Vidhan Sabha 2013</h3>';
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
	chart.draw(data,{width:400, height: 250, title:'Rajasthan Vidhan Sabha Election 2013 Lead/Won Chart'});
	}
}

 getSpecialPageHighLights();



</script>