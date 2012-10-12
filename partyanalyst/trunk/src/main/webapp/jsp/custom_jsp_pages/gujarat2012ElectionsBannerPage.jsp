<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script> 
<style>
	.main-mbg{
		width:962px;
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
</style>

<div>
<img src="images/specialPage/gujarat_banner.jpg" style="align:center;width:985px;">
</div>

<div>

<div id="upComing" style="background:#FFF;padding-top: 12px;">
<span class="resulth3" style="font-weight:bold;font-family:verdana;margin:13px;padding:5px;width:560px;">Gujarat 2012 Vidhan Sadha Election</span>

<br><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Total Assembly Constituencies - <font color="#05A8E9">182</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">13</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">27</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">140</font></span>

<div style="text-align:justify;margin:10px;padding:10px;"> 
<!--<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">AP Bi Election Schedule</span> -

<span style="font-family:verdana;font-size:13px;">Election Notification - May 18,  Last date for Nomination - May 25, Nomination withdraw Last date - May 28, <br />Polling - June 12, Counting - June 15.</span><br /><br />
-->
<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">Gujarat </span> -

<span style="font-family:verdana;font-size:13px;">The Election Commission of India released Notification for General Election of <b><a href="statePageAction.action?stateId=7"> Gujarat</a></b> Legislative Assembly, 2012. Polls in <b><a href="statePageAction.action?stateId=7">Gujarat</a></b> will take place in two phases. First phase on December 13, 2012 and second phase on December 17, 2012. The counting will take place on December 20, 2012.
</span><br /><br />

<!--<center>
<table border="2" cellpadding="5" >
<tr>
<th>Poll Event</th>
<th>Dates</th>
</tr>
<tr>
<td>Issue of Notification</td>
<td><center>10.10.2012 <br>
(Wednesday)</center></td>
</tr>
<tr>
<td>Last date for making Nominations</td>
<td><center>17.10.2012 <br>
(Wednesday)</center></td>
</tr>
<td>Scrutiny of Nominations</td>
<td><center>18.10.2012 <br>
(Thursday)</center></td>
</tr>
<tr>
<td>Last date for withdrawal of
candidature</td>
<td><center>20.10.2012 <br>
(Saturday)</center></td>
</tr>
<tr>
<td>Date of Poll</td>
<td><center>04.11.2012 <br>
((Sunday)</center></td>
</tr>
<tr>
<td>Counting of Votes</td>
<td><center>20.12.2012 <br>
(Thursday)</center></td>
</tr>
<tr>
<td>Date before which election process
shall be completed</td>
<td><center>24.12.2012 <br>
(Monday)</center></td>
</tr>
</table>
</center><br><br>-->
<!---
<span style="font-family:verdana;font-size:13px;right:20px;color:#ED5B21;float:right;"> *All 68 ACs will go to poll on a single day</span><br>-->

<table width="100%" style="border-top: 1px solid rgb(221, 221, 221);">
<tr>

<td  width="45%" valign="top">
<table style="border:1px solid #d2e888;margin-left: 9px;" width="98%" valign="top">
		
	
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 9px;">Schedule for the Gujarat Legislative Assembly, 2012	</h4>
		
		
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
	<div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>

	<span class="gray">Are you a</span>
	<strong>Politician
	<span class="orange">/</span>Political Party
	<span class="orange">/</span>Media...</strong> Want to know how you can be benefited with 
	<span class="orange">PartyAnalyst</span> 
	<div class="clear"></div>
	<div class="clickhere-button">
	<a href="viewFeaturesAction.action">Click Here to Learn More...</a></div>
	</div>

</td>


 <td width="2%"></td>

	<!------------RIGHT SIDE PANEL ------------>
	<td width="43%" valign="top">
<table style="border:1px solid #d2e888;padding-left:20px" width="98%" valign="top">
<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 96.5%;">
Gujarat Assembly 2012 Live Election Analysis</h3>

<tr>
<td colspan="2">


<div>
	<div style="background: none repeat scroll 0% 0% rgb(210, 232, 136); margin-left: -17px; padding-left: 7px; border-radius: 5px 5px 5px 5px;margin-top: 6px;"><span><b>Partywise Male and Female Candidates Seats Allocation and their Performances</b></span></div>
	<div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>

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


<table>

<br>

<span style="-moz-border-radius:3px;padding:3px;background: #d2e888;font-weight:bold;">View Party Previous Performances From 1977 - 2012</span>
<br><br>
<tr>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
BSP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=SAD&selectedPartyId=794&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=794&alliances=true&__checkbox_alliances=true&submitButton=Submit">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=SAD(M)&selectedPartyId=796&selectedElectionTypeName=Assembly&selectedLocationName=Gujarat &electionType=2&reportLevel=State&stateSelectName=7&partySelectName=796&alliances=true&__checkbox_alliances=true&submitButton=Submit">CPM</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
CPM</a>
</c:if>
</th></tr>
</table>
</td>
</tr>

<tr><td colspan="7"> <br />
<b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Previous Gujarat Assembly Elections Results </span></b><br /><br />

	
<img src="images/icons/diamond.png">
	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=126&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2007">2007</a>

&nbsp;<img src="images/icons/diamond.png">


	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=127&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2002"> 2002</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=128&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1997"> 1998</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=129&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1992"> 1995</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=130&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1985"> 1990</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=131&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1980"> 1985</a>
&nbsp;<br /><br /><img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=132&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1977"> 1980</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=133&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1977"> 1975</a>

<br /><br /></td></tr>
<tr>
<td width="50%" colspan="2"><span style="background:#D2E888;color:#000;font-weight:bold;width:374px;margin:0px;padding:5px;-moz-border-radius:3px;">View Gujarat Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Gujarat Districts</span>
	<span style="margin-left: 5px;">Know About Your District  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
	<span style="margin-left: 12px;">Know About Your Assembly Constituency</span>
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


<!--<tr><td colspan="7">
<br>
<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3"></object></center></td>
</tr>-->
</table>
</td></tr>
</table>
 
  

<!-- <div>
  <table>
     <tr>
	   <td>INC won in Narsapuram (4,702) and Ramachandra Puram.<br><br>
           TRS won in Parkal with only 1562 (Votes).</td>
	   <td><div id="presidentelection"><ul><li><a title="Indian Presidential Elections 2012" href="specialPageAction.action?specialPageId=10"><span style="font-weight: bold;">Click Here To View Indian Presidential Election 2012</span></a></li>
	   </ul></div></td>
	 </tr>
  </table>
</div> -->

<!--<div style="width:250px; "><span style="font-weight:bold;font-family:verdana;margin:63px;top:10px;bottom:2px;padding:5px;width:560px;color:#ED5B21;">2007 Election Result</span>
<table class="electionresulttable" style="margin-left:35px;"> 
<th> Party</th>
<th>Seats</th>
<tr>
<td>BJP</td>
<td>43</td>
</tr>
<tr>
<td>INC</td>
<td>23</td>
</tr>
<tr>
<td>IND</td>
<td>3</td>
</tr>
<tr>
<td>BSP</td>
<td>1</td>
</tr>

</table>
</div>-->
<!--<div id="presidentelectionDiv" style="margin-left: 478px; margin-bottom: 0px; margin-top: -51px; clear: both;">
	<table>
		<tr>
			<td>
				<div style="padding: 8px; font-weight: bold; margin-top: 10px; font-size: 15px; border-radius: 3px 3px 3px 3px; border: 1px solid #d3d3d3;">
				<ul>
				<li style="background:#D2E888; padding: 5px 0px; border-radius: 2px 2px 2px 2px; width: 402px; margin-left: 5px;">
				<a title="Gujarat 2012 Vidhan Sabha Election" href="specialPageAction.action?specialPageId=14"><span style="font-weight: bold; color: black; margin-left: 16px;">Click Here To View Himachal Pradesh Election 2012</span></a></li>
				</ul>
				</div>
			</td>
		</tr>
		</table>
</div>-->
<div id="presidentelectionDiv" style="margin-left: 478px; margin-bottom: 0px; margin-top: -51px; clear: both;">
	<table>
		<tr>
			<td>
				<div style="padding: 8px; font-weight: bold; margin-top: 47px; font-size: 15px; border-radius: 3px 3px 3px 3px; border: 1px solid #d3d3d3;">
				<ul>
				<li style="background:#D2E888; padding: 5px 0px; border-radius: 2px 2px 2px 2px; width: 402px; margin-left: 5px;">
				<a title="Gujarat 2012 Vidhan Sabha Election" href="specialPageAction.action?specialPageId=14"><span style="font-weight: bold; color: black; margin-left: 16px;">Click Here To View Himachal Pradesh Election 2012</span></a></li>
				</ul>
				</div>
			</td>
		</tr>
		</table>
</div>
<br/>
<div id="genderInfoDiv">
<div id="genderAnalysisDiv"></div></div>

<script type="text/javascript">

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

function callAjax(jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task =="getPartyGenderInfo"){
										buildGenderCountResultsDataTable(myResults,jsObj.elecYearId);
									}
							}
							catch (e) {   
							   	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
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

</script>