<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
a:hover{
text-decoration:none;
}
#GOAExitPolltable > table * td{text-align:center;}
</style>

<div style="background:#ffffff;width: 979px;margin-left: 3px;padding-bottom:16px;">
<img src="images/specialPage/goa2012election.png" style="align:center;width:985px;" usemap="#2012ElectionsMap" alt="Goa 2012 Election" title="Goa 2012 Election" />

<table width="100%" style="margin-left: 9px;margin-center: 9px; border-top: 1px solid #dddddd; margin-top: 4px;" ><tr><td  width="45%" valign="top">

<table cellspacing="0" cellpadding="2" border="0" style="border: 1px solid #D2E888;width:100%">

<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;">  2012 Goa State Exit polls and Surveys</h3>

<!--<tr style="border: medium none rgb(255, 255, 255);">
  <td>
   <div>
    <br><span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;font-weight:bold;">Male And Female Candidates Performances</span>
    <div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>
      <select onchange="getGenderInfo()" id="selectedElectionYear" style="width:120px;">
        <option value="0">Select Year</option>
		<option value="185">2012</option>
		<option value="165">2007</option>
	    <option value="166">2002</option>
		<option value="167">1999</option>
		<option value="168">1994</option>
		<option value="169">1989</option>
		<option value="170">1984</option>
		<option value="171">1980</option>
		<option value="172">1977</option>
	</select></div>
</div>
</td>
</tr>-->


<tr><td>
<table>
      <td>
	    <tr>
          <td style="padding-left: 47px;"><span style="font-weight:bold">Total Seats:40 </span></td>
          <td style="margin-right: 2px; border-right-width: 3px; padding-right: 7px; padding-left: 53px;"><span style="font-weight:bold">Know Results/Leading:40</span></td>
      
       </tr>
   </table>
  </td>
</tr>

<tr><td colspan="2"><br>
<span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;"><b>2012 Results</b></span>

<table id="goaLiveTable" cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse; margin-top: 9px; width: 97%; margin-left: 7px;text-align: center;">
			<tr><th>Party Name</th>
			
			<th>Participated Seats</th>
			<th>2007 Won Seats</th>
			<th>Won</th>
			</tr>
			
			<tr>
			<th><a href="partyPageAction.action?partyId=163">BJP</a></th>
			<td>33</td>
			<td>14</td>
			<td>21</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=362">INC</a></th>
			<td>32</td>
			<td>16</td>
			<td>9</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=523">MAG</a></th>
			<td>32</td>
			<td>16</td>
			<td>3</td>
			</tr>
			<tr>
			<th><a href="javascript:{}">Others</a></th>
			<td>-</td>
			<td>-</td>
			<td>7</td>
			</tr>
		</table></td></tr>
<tr><td>
<div id="GOAExitPolltable" style="margin-top:9px;">
<span style="font-weight:bold;background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;">Goa State Exit polls</span>
<table width="96%" cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse;margin-top:9px;margin-left:9px;">
<tr>

<!--<th>Source</th>
<th>INC</th>
<th>BJP</th>
<th>NCP</th>
<th>MAG</th>
<th>OTHERS</th>-->
<th> Source</th>
<th>CONG</th>
<th>BJP</th>

<th>OTHERS</th>
</tr>
<tr>
    <th>NDTV</th>
   
    <td>17</td>
	<td>20</td>
    <td>3</td>

  </tr>
  <tr>
<tr> <th>STAR</th>
    <td>15-19</td>
    <td>18-22</td>
    <td>-</td></tr><tr>
<th>India TV-C</th>
   
    <td>17</td>
	<td>20</td>
    <td>-</td>

  </tr>

</table>
</div>
</td></tr>

<tr><td>
<BR />
<b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">2012 Goa Assembly Survey Results</span></b><br /><br />
<table bordercolor="#d2e888" cellspacing="1" cellpadding="5" border="1" align="center"  style="border-collapse: collapse; " width="96%">
            <tbody><tr>
			  <td><b>Source</b></td>
              <td><b>INC</b></td>
              <td><b>BJP</b></td>
			  <td><b>NCP</b></td>
			  <td><b>MAG</b></td>
              <td><b>OTHERS</b></td>
            </tr>
			<tr>
				<td>NDTV</td>
				<td>16</td>
				<td>14</td>
				<td>3</td>
				<td>2</td>
				<td>5</td>
			</tr>
</table>
</td></tr>
<!--
<tr><td>
  <table  bordercolor="#d2e888" cellspacing="0" cellpadding="5" border="1" align="center"  style="border-collapse: collapse;font-size:12px " width="96%">
            <tbody><tr style="background-color:#d2e888">
              <td width="64"><b>S. No.&nbsp;</b></td>
              <td width="130" ><b>Poll Event&nbsp;</b></td>
              <td width="105"><b>Dates&nbsp;</b></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">1</span></td>
              <td><span class="paragraph1">Issue of Notification&nbsp;</span></td>
              <td><span class="paragraph1">06.02.2012 (Monday)&nbsp;</span></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">2</span></td>
              <td><span class="paragraph1">Last date for making Nominations&nbsp;</span></td>
              <td><span class="paragraph1">13.02.2012 (Monday)&nbsp;</span></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">3</span></td>
              <td><span class="paragraph1">Scrutiny of Nominations&nbsp;</span></td>
              <td><span class="paragraph1">14.02.2012 (Tuesday)&nbsp;</span></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">4</span></td>
              <td><span class="paragraph1">Last date for withdrawal of candidature&nbsp;</span></td>
              <td><span class="paragraph1">16.02.2012 (Thursday)&nbsp;</span></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">5</span></td>
              <td><span class="paragraph1">Date of Poll&nbsp;</span></td>
              <td><span class="paragraph1">03.03.2012 (Saturday)&nbsp;</span></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">6</span></td>
              <td><span class="paragraph1">Counting of Votes&nbsp;</span></td>
              <td><span class="paragraph1">04.03.2012 (Sunday)&nbsp;</span></td>
            </tr>
            <tr>
              <td align="center"><span class="paragraph1">7</span></td>
              <td><span class="paragraph1">Date before which election process shall be completed&nbsp;</span></td>
              <td><span class="paragraph1">09.03.2012 (Friday)&nbsp;</span></td>
            </tr>
          </tbody></table>
</td></tr>-->
<tr><td>
<div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>
<div class="clear"></div>
<p></p>
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
	</td>

	<td width="2%">
	
	</td>
	<td width="43%" valign="top">
<table style="border:1px solid #d2e888;padding-left:20px" width="98%" valign="top">
<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 96.5%;">
Goa Assembly 2012 Live Election Results</h3>
<!--<tr><td>
<table>
      <td>
	    <tr>
          <td style="padding-left: 47px;"><span style="font-weight:bold">Total Seats:40 </span></td>
          <td style="margin-right: 2px; border-right-width: 3px; padding-right: 7px; padding-left: 53px;"><span style="font-weight:bold">Know Results/Leading:40</span></td>
      
       </tr>
   </table>
  </td>
</tr>

<tr>
	<td>
		<table cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse; margin-top: 9px; width: 100%; margin-left: -10px;">
			<tr><th>Party Name</th>
			
			<th>Participated Seats</th>
			<th>2007 Won Seats</th>
			<th>Won</th>
			</tr>
			
			<tr>
			<th><a href="partyPageAction.action?partyId=163">BJP</a></th>
			<td>33</td>
			<td>14</td>
			<td>21</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=362">INC</a></th>
			<td>32</td>
			<td>16</td>
			<td>9</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=523">MAG</a></th>
			<td>32</td>
			<td>16</td>
			<td>3</td>
			</tr>
			<tr>
			<th><a href="javascript:{}">Others</a></th>
			<td>-</td>
			<td>-</td>
			<td>7</td>
			</tr>
		</table>
	</td></tr>-->


	<tr>
<td colspan="2">

<div>

<!--<span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;font-weight:bold;">Partywise Male and Female Candidates Seats Allocation and their Performances</span>-->

<div style="background: none repeat scroll 0% 0% rgb(210, 232, 136); margin-left: -17px; padding-left: 7px; border-radius: 5px 5px 5px 5px;margin-top: 7px;"><span><b>Partywise Male and Female Candidates Seats Allocation and their Performances</b></span></div>

<div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>
 <select onchange="getGenderInfo(this.value,this.id)" id="selectedElectionYear" style="width:120px;">
        <option value="0">Select Year</option>
		<option value="185">2012</option>
		<option value="165">2007</option>
	    <option value="166">2002</option>
		<option value="167">1999</option>
		<option value="168">1994</option>
		<option value="169">1989</option>
		<option value="170">1984</option>
		<option value="171">1980</option>
		<option value="172">1977</option>
	</select>
</div>
</div>


<!--<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3&autoplay=1" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3&autoplay=1"></object></center>-->
<table>
<br>
<span style="-moz-border-radius:3px;padding:3px;background: #d2e888;font-weight:bold;">View Party Previous Performances From 1984 - 2012</span>
<br><br>
<tr>

<th style="padding-right: 27px;">
	<c:if test="${loginStatus == 'out'}"> 
	<a href="javascript:{}">BSP</a>
	</c:if>
	<c:if test="${sessionScope.USER == null}"> 
	<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
	BSP</a>
	</c:if>
</th>
<th style="padding-right: 27px;">
	<c:if test="${loginStatus == 'out'}"> 
	<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Goa&electionType=2&reportLevel=State&stateSelectName=6&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit">BJP</a>
	</c:if>
	<c:if test="${sessionScope.USER == null}"> 
	<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
	BJP</a>
	</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Goa&electionType=2&reportLevel=State&stateSelectName=6&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit">INC</a></c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="javascript:{}">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"><a href="javascript:{}">CPM</a>
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
<b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Previous Goa Assembly Elections Results </span></b><br /><br />
<img src="images/icons/diamond.png" />
	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=165&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=2007">2007</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=166&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=2002"> 2002</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=167&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=1999"> 1999</a>
&nbsp;
<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=168&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=1994"> 1994</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=169&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=1989"> 1989</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=170&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=1984"> 1984</a>
&nbsp;<br /><br />
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=171&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=1980"> 1980</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=172&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=1977"> 1977</a>

</td></tr>
<tr><td colspan="2">
<br><br>
<span style="background:#D2E888;color:#000;font-weight:bold;width:374px;margin:0px;padding:5px;-moz-border-radius:3px;">View Goa Districts And Constituencies Results</span><br><br></td></tr>
<tr><td>
<div class="selectHeading" style="height:177px;">
	<span class="selectDivStyle">Goa Districts &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;</span>
	<span style="margin-left: 12px;">Know About Your District </span>
<select name="district" id="dist_goa" class="selectBoxWidth">
  <option value="0">Select District</option>
  <option value="283">North Goa</option>
  <option value="284">South Goa</option>
</select>
<div id="alertMessage_district_goa"></div>

<div class="view-results"><a href="javascript:{}" onclick="navigateToDistrictPageFrmSpeclPge('dist_goa','alertMessage_district_goa')">view results</a></div>
</div></td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Goa Constituencies </span>
	<span style="margin-left: 12px;">Know About Your Assembly Constituency</span>
 <select class="selectBoxWidth" id="const_goa" name="constituency">
	<option value="0">Select Constituency</option>
	<option value="39366">Aldona</option>
	<option value="39385">Benaulim</option>
	<option value="39372">Bicholim</option>
	<option value="39364">Calangute</option>
	<option value="39396">Canacona</option>
	<option value="39383">Cortalim</option>
	<option value="39371">Cumbarjua</option>
	<option value="39391">Cuncolim</option>
	<option value="39394">Curchorem</option>
	<option value="39388">Curtorim</option>
	<option value="39404">Dabolim</option>
	<option value="39405">Daman</option>
	<option value="39360">Dargalim </option>
	<option value="39406">Diu</option>
	<option value="39386">Fatorda</option>
	<option value="39384">Loutulim</option>
	<option value="39373">Maem</option>
	<option value="39358">Mandrem</option>
	<option value="39362">Mapusa</option>
	<option value="39379">Marcaim</option>
	<option value="39387">Margao</option>
	<option value="39381">Mormugao</option>
	<option value="39389">Navelim</option>
	<option value="39374">Pale</option>
	<option value="39367">Panaji</option>
	<option value="39359">Pernem</option>
	<option value="39397">Poinguinim</option>
	<option value="39377">Ponda</option>
	<option value="39375">Poriem</option>
	<option value="39378">Priol</option>
	<option value="39395">Quepem</option>
	<option value="39403">Rivona</option>
	<option value="39365">Saligao</option>
	<option value="39393">Sanguem</option>
	<option value="39369">Santa Cruz</option>
	<option value="39392">Sanvordem</option>
	<option value="39402">Satari</option>
	<option value="39363">Siolim</option>
	<option value="39380">Siroda</option>
	<option value="39370">St Andre</option>
	<option value="39368">Taleigao</option>
	<option value="39361">Tivim</option>
	<option value="39376">Valpoi</option>
	<option value="39382">Vasco Da Gama</option>
	<option value="39390">Velim</option>
</select>
	<div id="alertMessage_const_goa"></div>
	<div class="view-results"><a href="javascript:{}" onclick="navigateToConstituencyPageFrmSpeclPge('const_goa','alertMessage_const_goa')">view results</a></div>
</td></tr>


<tr style="margin-left: 10px; margin-top: 5px;">
<td colspan="7"><br><b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Major Parties In the State  </span></b><br /><br />
		   
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=579">NCP <img src="images/party_flags/NCP.png" height="40px" width="60px"></a>&nbsp;&nbsp;&nbsp; 
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=163" align="top">BJP&nbsp;<img src="images/party_flags/BJP.png" height="40px" width="60px"></a>&nbsp;&nbsp;&nbsp; 
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=362" align="top">INC&nbsp;<img src="images/party_flags/INC.png" height="40px" width="60px"></a> <br /><br />
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=812" align="top">SGF</a>&nbsp;
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=893" align="top">UGDP</a>&nbsp;&nbsp;&nbsp;
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=523" align="top">MAG</a>&nbsp;&nbsp;&nbsp;
<tr>
<td colspan="7">
<br>
<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3"></object></center></td></tr>



</table>
</td></tr>
	



</table>

<div id="genderInfoDiv">
<div id="genderAnalysisDiv"></div></div>
<script type="text/javascript">

function getGenderInfo(selectedElecYear,elecYearId)
{
	var jsObj = {
				elecYearId:elecYearId,
	            time:new Date().getTime(),
				electionId:selectedElecYear,
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

function buildGenderCountResultsDataTable(myResults,elecYearId)
{
	var electionIdSelectedEle = document.getElementById(''+elecYearId+'');
	var year = electionIdSelectedEle.options[electionIdSelectedEle.selectedIndex].text;
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
	str +='<div style="margin-left: 475px;">';
	str +='<table>';
	str +='<tr>';
	str +='<td><b style="font-size:13px;">Select Election Year:</b></td>';
	str +='<td>';
	str +='<select onchange="getGenderInfo(this.value,this.id)" id="selectedEleYear" style="width:120px;">';
	str +='<option value="0">Select Year</option>';
	str +='<option value="185">2012</option>';
	str +='<option value="165">2007</option>';
	str +='<option value="166">2002</option>';
	str +='<option value="167">1999</option>';
	str +='<option value="168">1994</option>';
	str +='<option value="169">1989</option>';
	str +='<option value="170">1984</option>';
	str +='<option value="171">1980</option>';
	str +='<option value="172">1977</option>';
	str +='</select>';
	str +='</td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	str +='<h3 style="background: none repeat scroll 0pt 0pt rgb(33, 178, 237); padding: 7px 0px; border-right-width: 0px; color: rgb(255, 255, 255); margin-top: 13px; border-left-width: 0px; margin-left: 49px; font-size: 13px; text-align: center; width: 706px; border-radius: 3px 3px 3px 3px;">Partywise Male and Female Participation and their Performance In Goa <font color="pink">'+year+'</font> Assembly Election</h3>';

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
