<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
a:hover{
text-decoration:none;}
#punjabExitPolltable > table * td{text-align:center;}
}
</style>
<div style="background:#ffffff; width: 979px;margin-left: 3px;padding-bottom: 13px;">
<img src="images/specialPage/panjab2012election.png" style="align:center;width:985px;" usemap="#2012ElectionsMap" alt="Panjab 2012 Election" title="Panjab 2012 Election" />

<table width="100%" style="margin-left: 9px;margin-right: 9px; border-top: 1px solid #dddddd; margin-top:0px;" ><tr><td  width="45%">

<table width="100%" style="margin-left: 9px;margin-right: 9px; border-top: 1px solid #dddddd; margin-top:0px;" >

<!------ LEFT SIDE NAV ------------->
<tr><td  width="45%">

<table cellspacing="0" cellpadding="2" border="0" style="border: 1px solid #D2E888;width:100%">

<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px; margin-top: 0px;margin-top: -94px;">Punjab 2012 Assembly Results, Exit Polls & Surveys</h3>

<!--<tr style="border: medium none rgb(255, 255, 255);">
	<td><div>
	<br><span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;font-weight:bold;">Male And Female Candidates Performances</span>-->
	<!--<div style="background: none repeat scroll 0% 0% rgb(210, 232, 136); margin-left: -17px;""><span><b>Partywise Male and Female Candidates Seats Allocation and their Performances</b></span></div>-->
	<!--<div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>

		<select id="selectedElectionYears" onchange="getGenderInfo()" style="width:120px;">
			<option value="0">Select Year</option>
			<option value="182">2012</option>
			<option value="136">2007</option>
			<option value="138">2002</option>
			<option value="139">1997</option>
			<option value="140">1992</option>
			<option value="141">1985</option>
			<option value="142">1980</option>
			<option value="144">1977</option>
		</select>
	</div>
	</div>
	</td>
</tr>-->

<tr><td>
<table>
      <td>
	    <tr>
          <td style="padding-left: 47px;"><span style="font-weight:bold">Total Seats:117 </span></td>
          <td style="margin-right: 2px; border-right-width: 3px; padding-right: 7px; padding-left: 53px;"><span style="font-weight:bold">Know Results/Leading:117</span></td>
      
       </tr>
   </table>
  </td>
</tr>

<tr><td colspan="2"><br>
<span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;"><b>2012 Results</b></span>

	<table cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse; margin-top: 9px; width: 100%; margin-left: 1px;">
			<tr><th>Party Name</th>
			
			<th>Participated Seats</th>
			<th>2007 Won Seats</th>
			<th>Won</th>
			</tr>
			<!--<tr>
			<th><a href="partyPageAction.action?partyId=239">BSP</a></th>
			<td>115</td>
			<td>0</td>
			<td>0</td>
			</tr>-->
			<tr>
			<th><a href="partyPageAction.action?partyId=163">BJP</a></th>
			<td>23</td>
			<td>19</td>
			<td>12</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=362">INC</a></th>
			<td>116</td>
			<td>44</td>
			<td>46</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=794">SAD+</a></th>
			<td>93</td>
			<td>48</td>
			<td>56</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=796">Others</a></th>
			<td>--</td>
			<td>--</td>
			<td>3</td>
			</tr>
		</table>



<div id="punjabExitPolltable" style="margin-top:9px;">
<span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;"><b>Punjab State Exit polls</b></span>
<table width="96%" cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse;margin-top:9px;margin-left:9px;">
<tr>
<th> Source</th>
<th>AKALI+</th>
<th>CONG</th>
<th>PPP+</th>
<th>BSP</th>
<th>OTHERS</th>
</tr>
<tr>
    <th>NDTV</th>
    <td>51</td>
    <td>59</td>
    <td>2</td>
	<td>2</td>
    <td>3</td>

  </tr>
  <tr>
<th>STAR</th>
    <td>56</td>
    <td>58</td>
    <td>-</td>
	<td>-</td>
    <td>2</td>
  </tr>

   <tr>
<th>NEWS-24</th>
    <td>52</td>
    <td>60</td>
    <td>-</td>
	<td>-</td>
    <td>-</td>
  </tr>
  </tr>

</table>
</div>

<tr><td colspan="2">
	<br>
	<b><span style="-moz-border-radius:3px;margin-left:9px;padding:3px;margin-top: 15px;background: #d2e888;">2012 Punjab Assembly Survey Results</b><br /><br />
	<table bordercolor="#d2e888" cellspacing="0" cellpadding="5" border="1" align="center"  style="border-collapse: collapse; " width="96%">
			   <tr>
				  <td><b>Source</b></td>
				  <td><b>INC</b></td>
				  <td><b>BJP</b></td>
				  <td><b>OTHERS</b></td>
				</tr>
			  <tr><td>Star News</td><td>63</td><td>53</td><td>1</td></tr>
	</table>

<table width="100%"><tr><td>
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
</td></tr>
</table>
</td></tr>
</table>
</td>
 <td width="2%"></td>

	<!------------RIGHT SIDE PANEL ------------>
	<td width="43%" valign="top">
<table style="border:1px solid #d2e888;padding-left:20px" width="98%" valign="top">
<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 96.5%;">
Punjab Assembly 2012 Live Election Analysis</h3>

<tr>
<td colspan="2">


<div>
	<div style="background: none repeat scroll 0% 0% rgb(210, 232, 136); margin-left: -17px; padding-left: 7px; border-radius: 5px 5px 5px 5px;margin-top: 6px;"><span><b>Partywise Male and Female Candidates Seats Allocation and their Performances</b></span></div>
	<div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>

		<select id="selectedElectionYears" onchange="getGenderInfo(this.value,this.id)" style="width:120px;">
			<option value="0">Select Year</option>
			<option value="182">2012</option>
			<option value="136">2007</option>
			<option value="138">2002</option>
			<option value="139">1997</option>
			<option value="140">1992</option>
			<option value="141">1985</option>
			<option value="142">1980</option>
			<option value="144">1977</option>
		</select>
	</div>
	</div>


<table>

<br>

<span style="-moz-border-radius:3px;padding:3px;background: #d2e888;font-weight:bold;">View Party Previous Performances From 1980 - 2012</span>
<br><br>
<tr>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Punjab&electionType=2&reportLevel=State&stateSelectName=21&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
BSP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Punjab&electionType=2&reportLevel=State&stateSelectName=21&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Punjab&electionType=2&reportLevel=State&stateSelectName=21&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=SAD&selectedPartyId=794&selectedElectionTypeName=Assembly&selectedLocationName=Punjab&electionType=2&reportLevel=State&stateSelectName=21&partySelectName=794&alliances=true&__checkbox_alliances=true&submitButton=Submit">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=SAD(M)&selectedPartyId=796&selectedElectionTypeName=Assembly&selectedLocationName=Punjab&electionType=2&reportLevel=State&stateSelectName=21&partySelectName=796&alliances=true&__checkbox_alliances=true&submitButton=Submit">CPM</a>
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
<b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Previous Punjab Assembly Elections Results </span></b><br /><br />
<img src="images/icons/diamond.png">
	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=182&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=2012">2012
	</a>

&nbsp;
<img src="images/icons/diamond.png">
	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=136&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=2007">2007</a>

&nbsp;<img src="images/icons/diamond.png">


	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=138&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=2002"> 2002</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=139&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1997"> 1997</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=140&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1992"> 1992</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=141&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1985"> 1985</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=142&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1980"> 1980</a>
&nbsp;<br /><br /><img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=144&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1977"> 1977</a>
<br /><br /></td></tr>
<tr>
<td width="50%" colspan="2"><span style="background:#D2E888;color:#000;font-weight:bold;width:374px;margin:0px;padding:5px;-moz-border-radius:3px;">View Punjab Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Punjab Districts</span>
	<span style="margin-left: 12px;">Know About Your District  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district">
	<option value="0">Select District</option>
     <option value="255">Amritsar</option><option value="267">Bathinda</option><option value="266">Faridkot</option><option value="261">Fatehgarh Sahib</option><option value="264">Firozpur</option><option value="254">Gurdaspur</option><option value="258">Hoshiarpur</option><option value="257">Jalandhar</option><option value="256">Kapurthala</option><option value="262">Ludhiana</option><option value="268">Mansa</option><option value="263">Moga</option><option value="265">Muktsar</option><option value="259">Nawanshahr </option><option value="270">Patiala</option><option value="260">Rupnagar</option><option value="269">Sangrur</option>
   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPage()" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Punjab Constituencies </span>
	<span style="margin-left: 12px;">Know About Your Assembly Constituency</span>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
  <option value="38833">Abohar</option><option value="38770">Adampur</option><option value="38762">Ajnala</option><option value="38820">Amloh</option><option value="38760">Amritsar Central</option><option value="38758">Amritsar North</option><option value="38761">Amritsar South</option><option value="38759">Amritsar West</option><option value="38808">Anandpur Sahib Ropar</option><option value="38764">Attari</option><option value="38842">Bagha Purana</option><option value="38786">Balachaur</option><option value="38832">Balluana</option><option value="38779">Banga</option><option value="38812">Banur</option><option value="38825">Barnala</option><option value="38744">Batala</option><option value="38754">Beas</option><option value="38826">Bhadaur</option><option value="38853">Bhatinda</option><option value="38782">Bholath</option><option value="38858">Budhlada</option><option value="38809">Chamkaur Sahib</option><option value="38815">Dakala</option><option value="38797">Dakha</option><option value="38793">Dasuya</option><option value="38827">Dhanaula</option><option value="38840">Dharamkot</option><option value="38748">Dhariwal</option><option value="38822">Dhuri</option><option value="38750">Dina Nagar</option><option value="38829">Dirbha</option><option value="38846">Faridkot</option><option value="38743">Fatehgarh</option><option value="38834">Fazilka</option><option value="38837">Firozepur</option><option value="38838">Firozepur Cantonment</option><option value="38792">Garhdiwala</option><option value="38787">Garhshankar</option><option value="38814">Ghanaur</option><option value="38848">Giddar Baha</option><option value="38749">Gurdaspur</option><option value="38836">Guru Har Sahai</option><option value="38789">Hoshiarpur</option><option value="38795">Jagraon</option><option value="38835">Jalalabad</option><option value="38757">Jandiala</option><option value="38856">Joga</option><option value="38771">Jullundur Cantonment</option><option value="38773">Jullundur Central</option><option value="38772">Jullundur North</option><option value="38774">Jullundur South</option><option value="38747">Kahnuwan</option><option value="38783">Kapurthala</option><option value="38775">Kartarpur</option><option value="38766">Khadoor Sahib</option><option value="38806">Khanna</option><option value="38811">Kharar</option><option value="38845">Kot Kapura</option><option value="38804">Kum Kalan</option><option value="38850">Lambi</option><option value="38831">Lehra</option><option value="38776">Lohian</option><option value="38801">Ludhiana East</option><option value="38799">Ludhiana North</option><option value="38802">Ludhiana Rural</option><option value="38800">Ludhiana West</option><option value="38788">Mahilpur</option><option value="38755">Majitha</option><option value="38823">Malerkotla</option><option value="38849">Malout</option><option value="38857">Mansa</option><option value="38841">Moga</option><option value="38810">Morinda</option><option value="38794">Mukerian</option><option value="38847">Muktsar</option><option value="38819">Nabha</option><option value="38777">Nakodar</option><option value="38807">Nangal</option><option value="38751">Narot Mehra</option><option value="38854">Nathana</option><option value="38767">Naushahra Panwan</option><option value="38780">Nawan Shahr</option><option value="38843">Nihal Singh Wala</option><option value="38778">Nur Mahal</option><option value="38852">Pakka Kalan</option><option value="38844">Panjgrain</option><option value="38752">Pathankot</option><option value="38818">Patiala Town</option><option value="38768">Patti</option><option value="38803">Payal</option><option value="38785">Phagwara</option><option value="38781">Phillaur</option><option value="38745">Qadian</option><option value="38798">Qila Raipur</option><option value="38796">Raikot</option><option value="38763">Raja Sansi</option><option value="38813">Rajpura</option><option value="38855">Rampura Phul</option><option value="38817">Samana</option><option value="38805">Samrala</option><option value="38828">Sangrur</option><option value="38859">Sardulgarh</option><option value="38790">Sham Chaurasi</option><option value="38824">Sherpur</option><option value="38816">Shutrana</option><option value="38821">Sirhind</option><option value="38746">Srihargobindpur</option><option value="38753">Sujanpur</option><option value="38784">Sultanpur</option><option value="38830">Sunam</option><option value="38851">Talwandi Sabo</option><option value="38791">Tanda</option><option value="38765">Tarn Taran</option><option value="38769">Valtoha</option><option value="38756">Verka</option><option value="38839">Zira</option>
 </select>
<div id="alertMessage"></div>

<div class="view-results"><a onclick="navigateToConstituencyPage()" href="javascript:{}">view constituency</a></div>
</div>
</td>
</tr>

<tr style="margin-left: 10px; margin-top: 5px;">
<td colspan="7"><br><b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Major Parties In the State  </span></b><br><br>
		   
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=794">SAD&nbsp;&nbsp;
<img src="images/icons/diamond.png"></a> 
<a href="partyPageAction.action?partyId=163" align="top">BJP&nbsp;<img src="images/party_flags/BJP.png" height="40px" width="60px">&nbsp;&nbsp;&nbsp; 
<img src="images/icons/diamond.png"></a>
<a href="partyPageAction.action?partyId=362" align="top">INC&nbsp;<img src="images/party_flags/INC.png" height="40px" width="60px">&nbsp;&nbsp;&nbsp; 
<img src="images/icons/diamond.png"></a>
<a href="partyPageAction.action?partyId=239" align="top">BSP&nbsp;<img src="images/party_flags/BSP.png" height="40px" width="60px"></a>&nbsp;&nbsp;
</td></tr>
<tr><td colspan="7">
<br>
<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3&autoplay=1" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3&autoplay=1"></object></center></td>
</tr>
</table>
</td></tr>
</table>
</div>
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
	str +='<option value="182">2012</option>';
	str +='<option value="136">2007</option>';
	str +='<option value="138">2002</option>';
	str +='<option value="139">1997</option>';
	str +='<option value="140">1992</option>';
	str +='<option value="141">1985</option>';
	str +='<option value="142">1980</option>';
	str +='<option value="144">1977</option>';
	str +='</select>';
	str +='</td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	str +='<h3 style="background: none repeat scroll 0pt 0pt rgb(33, 178, 237); padding: 7px 0px 6px; color: rgb(255, 255, 255); margin-top: 13px; border-left-width: 0px; margin-left: 43px; font-size: 13px; border-radius: 3px 3px 3px 3px; text-align: center; width: 726px;">Partywise Male And Female Participation and their Performance In Punjab <font color="pink">'+year+'</font> Assembly Election</h3>';

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
