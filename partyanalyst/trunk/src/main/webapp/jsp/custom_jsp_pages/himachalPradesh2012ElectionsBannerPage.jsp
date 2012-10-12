<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script> 

<style>
 .main-mbg {
    width:962px;
}

.electionresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.electionresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.electionresulttable th{background:#21B2ED;color:#fff;}
.nominationresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.nominationresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.nominationresulttable tr:nth-child(odd){background:#e5e5e5;}
.nominationresulttable tr:nth-child(even){background:#f3f3f3;}
.nominationresulttable th{padding:5px;background:#D8D8D8;}
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
<img src="images/specialPage/himachal_Pradesh_banner.jpg" style="align:center;width:985px;">
</div>

<div>
<div id="upComing" style="background:#FFF;padding-top: 12px;">
<span class="resulth3" style="font-weight:bold;font-family:verdana;margin:13px;padding:5px;width:560px;">Himachal Pradesh 2012 Vidhan Sadha Election</span>

<br><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Total Assembly Constituencies - <font color="#05A8E9">68</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">17</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">3</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">48</font></span>

<div style="text-align:justify;margin:10px;padding:10px;"> 
<!--<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">AP Bi Election Schedule</span> -

<span style="font-family:verdana;font-size:13px;">Election Notification - May 18,  Last date for Nomination - May 25, Nomination withdraw Last date - May 28, <br />Polling - June 12, Counting - June 15.</span><br /><br />
-->
<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">Himachal Pradesh </span> -

<span style="font-family:verdana;font-size:13px;">The Election Commission of India released Notification for General Election of <b><a href="statePageAction.action?stateId=9">Himachal Pradesh</a></b>. <b><a href="statePageAction.action?stateId=9">Himachal Pradesh</a></b> Total Constituencies 68ACs Date of Poll in 4.11.2012 and Counting of Votes in 20.12.2012.</span><br /><br />

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
		
	
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 7px;">Schedule for the Himachal Pradesh Legislative Assembly, 2012	</h4>
		
		
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
<td>Last date for making Nominations</td>
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
Himachal Assembly 2012 Live Election Analysis</h3>

<tr>
<td colspan="2">


<div>
	<div style="background: none repeat scroll 0% 0% rgb(210, 232, 136); margin-left: -17px; padding-left: 7px; border-radius: 5px 5px 5px 5px;margin-top: 6px;"><span><b>Partywise Male and Female Candidates Seats Allocation and their Performances</b></span></div>
	<div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>

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


<table>

<br>

<span style="-moz-border-radius:3px;padding:3px;background: #d2e888;font-weight:bold;">View Party Previous Performances From 1977 - 2012</span>
<br><br>
<tr>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
BSP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=SAD&selectedPartyId=794&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=794&alliances=true&__checkbox_alliances=true&submitButton=Submit">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=SAD(M)&selectedPartyId=796&selectedElectionTypeName=Assembly&selectedLocationName=Himachal Pradesh&electionType=2&reportLevel=State&stateSelectName=9&partySelectName=796&alliances=true&__checkbox_alliances=true&submitButton=Submit">CPM</a>
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
<b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Previous Himachal Assembly Elections Results </span></b><br /><br />

	
<img src="images/icons/diamond.png">
	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=148&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=2007">2007</a>

&nbsp;<img src="images/icons/diamond.png">


	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=149&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=2003"> 2003</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=150&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1998"> 1998</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=151&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1993"> 1993</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=152&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1990"> 1990</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=153&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1985"> 1985</a>
&nbsp;<br /><br /><img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=154&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1982"> 1982</a>
&nbsp;<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=155&stateID=9&stateName=Himachal Pradesh&electionType=Assembly&electionTypeId=2&year=1977"> 1977</a>
<br /><br /></td></tr>
<tr>
<td width="50%" colspan="2"><span style="background:#D2E888;color:#000;font-weight:bold;width:374px;margin:0px;padding:5px;-moz-border-radius:3px;">View Himachal Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">HP Districts</span>
	<span style="margin-left: 5px;">Know About Your District  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
     <option value='271'>Chamba</option>
	<option value='272'>Kangra</option>
	<option value='273'>Lahul & Spiti</option>
	<option value='274'>Kullu</option>
	<option value='275'>Mandi</option>
	<option value='276'>Hamirpur</option>
	<option value='277'>Una</option>
	<option value='278'>Bilaspur</option>
	<option value='279'>Solan</option>
	<option value='280'>Sirmaur</option>
	<option value='281'>Shimla</option>
	<option value='282'>Kinnaur</option>

   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">HP Constituencies </span>
	<span style="margin-left: 12px;">Know About Your Assembly Constituency</span>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
 <option value="650">HAMIRPUR</option><option value="652">Kangra</option><option value="653">Mandi</option><option value="654">Shimla </option><option value="655">Simla</option><option value="38860">KINNAUR</option><option value="38861">RAMPUR</option><option value="38862">ROHRU</option><option value="38863">JUBBAL KOTKHAI</option><option value="38864">CHOPAL</option><option value="38865">KUMARSAIN</option><option value="38866">THEOG</option><option value="38867">SIMLA</option><option value="38868">KASUMPTI</option><option value="38869">ARKI</option><option value="38870">DOON</option><option value="38871">NALAGARH</option><option value="38872">KASAULI</option><option value="38873">SOLAN</option><option value="38874">PACHHAD</option><option value="38875">RAINKA</option><option value="38876">SHILLAI</option><option value="38877">PAONTA DOON</option><option value="38878">NAHAN</option><option value="38879">KOTKEHLOOR</option><option value="38880">BILASPUR</option><option value="38881">GHUMARWIN</option><option value="38882">GEHARWIN</option><option value="38883">NADAUN</option><option value="38884">HAMIRPUR</option><option value="38885">BAMSAN</option><option value="38886">MEWA</option><option value="38887">NADAUNTA</option><option value="38888">GAGRET</option><option value="38889">CHINTPURNI</option><option value="38890">SANTOKGARH</option><option value="38891">UNA</option><option value="38892">KUTLEHAR</option><option value="38893">NURPUR</option><option value="38894">GANGATH</option><option value="38895">JAWALI</option><option value="38896">GULER</option><option value="38897">JASWAN</option><option value="38898">PRAGPUR</option><option value="38899">JAWALAMUKHI</option><option value="38900">THURAL</option><option value="38901">RAJGIR</option><option value="38902">BAIJNATH</option><option value="38903">PALAMPUR</option><option value="38904">SULAH</option><option value="38905">NAGROTA</option><option value="38906">SHAHPUR</option><option value="38907">DHARAMSALA</option><option value="38908">KANGRA</option><option value="38909">BHATTIYAT</option><option value="38910">BANIKHET</option><option value="38911">RAJNAGAR</option><option value="38912">CHAMBA</option><option value="38913">BHARMOUR</option><option value="38914">LAHAUL AND SPITI</option><option value="38915">KULU</option><option value="38916">BANJAR</option><option value="38917">ANI</option><option value="38918">KARSOG</option><option value="38919">CHACHIOT</option><option value="38920">NACHAN</option><option value="38921">SUNDERNAGAR</option><option value="38922">BALH</option><option value="38923">GOPALPUR</option><option value="38924">DHARAMPUR</option><option value="38925">JOGINDER NAGAR</option><option value="38926">DARANG</option><option value="38927">MANDI</option>
 </select>
<div id="alertMessage_const_Himachal Pradesh"></div>

<div class="view-results"><a onclick="navigateToConstituencyPageFrmSpeclPge('selectedConstituencyInSpecialPage','alertMessage_const_Himachal Pradesh')" href="javascript:{}">view constituency</a></div>
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
<!--<tr><td colspan="7">
<br>
<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3"></object></center></td>
</tr>-->
</table>
</td></tr>
</table>
 
  
<span style="font-family:verdana;font-size:13px;">
After 2008 Delimitation this is first Vidhan Sadha Election in Himachal Pradesh . In Himachal Pradesh 19 Constituencies are desloved out of 68 Constituencies and 19 Constituencies are Newly formed in 2008 Delimiatation. <br><br>
</span><br>

</span><br>

<div id="presidentelectionDiv" style="margin-left: 478px; margin-bottom: 0px; margin-top: -51px; clear: both;">
	<table>
		<tr>
			<td>
				<div style="padding: 8px; font-weight: bold; margin-top: 10px; font-size: 15px; border-radius: 3px 3px 3px 3px; border: 1px solid #d3d3d3;">
				<ul>
				<li style="background:#D2E888; padding: 5px 0px; border-radius: 2px 2px 2px 2px; width: 402px; margin-left: 5px;">
				<a title="Gujarat 2012 Vidhan Sabha Election" href="specialPageAction.action?specialPageId=13"><span style="font-weight: bold; color: black; margin-left: 16px;">Click Here To View Gujarat Election 2012</span></a></li>
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

</script>