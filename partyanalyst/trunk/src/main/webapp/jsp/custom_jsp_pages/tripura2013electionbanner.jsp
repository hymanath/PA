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
<img src="images/specialPage/tripura2013electionbanner.jpg" style="align:center;width:975px;">
</div>

<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->


<div id="upComing" class="breadcrumb">
<div style="text-align:justify;margin:10px;padding:10px;"> 

<table>
<tr>

<td  width="45%" valign="top">
<table>
		
	
<h3 class="resulth3">Tripura 2013 Elections Statistics</h3>
<div id="ElectionStats" style="margin-bottom:10px;">
<span> Total Assembly Constituencies - <font color="#05A8E9">60</font></span><br>
<span> SC Constituencies - <font color="#05A8E9">10</font> </span><br>
<span>ST Constituencies - <font color="#05A8E9">20</font></span><br>
<span>General Constituencies - <font color="#05A8E9">30</font></span><br>
</div>
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;">Schedule for the Tripura Legislative Assembly, 2013	</h4>
		
		
			<td>
			<table class="nominationresulttable">
<tr>
<th>Poll Event</th>
<th>For 60 Assembly Constituency</th>

</tr>
<tr>
<td>Issue of Notification</td>
<td>21.1.2013
(Monday)</td>

</tr>
<tr>
<td>Last date for making Nominations</td>
<td>28.1.2013
(Monday)</td>
</tr>
<td>Scrutiny of Nominations</td>
<td>29.1.2013
(Tuesday)</td>
</tr>
<tr>
<!--<td>Last date for withdrawal of
candidature</td>-->
<td>Last date for withdrawal of candidature</td>
<td>31.1.2013
(Thursday)</td>
</tr>
<tr>
<td>Date of Poll</td>
<td>14.2.2013
(Thursday)</td>
</tr>
<tr>
<td>Counting of Votes</td>
<td>28.2.2013
(Thursday)</td>
</tr>
<tr>
<td>Date before which election process
shall be completed</td>
<td>3.3.2013
(Sunday)</td>
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
Tripura Assembly 2013 Live Election Analysis</h3>

<tr>
<div id="electionResultDivMain" style="padding-bottom:10px;">
  <div id="electionResultDiv"></div>
</div>
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
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Tripura&electionType=2&reportLevel=State&stateSelectName=25&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Tripura&electionType=2&reportLevel=State&stateSelectName=25&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=CPI&selectedPartyId=265&selectedElectionTypeName=Assembly&selectedLocationName=Tripura&electionType=2&reportLevel=State&stateSelectName=25&partySelectName=265&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=CPM&selectedPartyId=269&selectedElectionTypeName=Assembly&selectedLocationName=Tripura&electionType=2&reportLevel=State&stateSelectName=25&partySelectName=269&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
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
<span style="clear: both; display: inline-block; width: 100%;">Previous Tripura Assembly Elections Results </span>


<a  href="electionDetailsReportAction.action?electionId=203&stateID=25&stateName=Tripura&electionType=Assembly&electionTypeId=2&year=2008" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2008</a>


<a href="electionDetailsReportAction.action?electionId=204&stateID=25&stateName=Tripura&electionType=Assembly&electionTypeId=2&year=2003" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2003</a>


<a  href="electionDetailsReportAction.action?electionId=205&stateID=25&stateName=Tripura&electionType=Assembly&electionTypeId=2&year=1998" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1998</a>


<a  href="electionDetailsReportAction.action?electionId=206&stateID=25&stateName=Tripura&electionType=Assembly&electionTypeId=2&year=1993" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1993</a>


<a href="electionDetailsReportAction.action?electionId=207&stateID=25&stateName=Tripura&electionType=Assembly&electionTypeId=2&year=1988"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1988</a>


<a  href="electionDetailsReportAction.action?electionId=208&stateID=25&stateName=Tripura&electionType=Assembly&electionTypeId=2&year=1983"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1983</a>

</td></tr>
<tr> <td>
<table class="breadcrumb" style="margin-top:10px;">
<tr>
<td width="50%" colspan="2" >
<span>View Tripura Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Tripura Districts</span>
	<p style="padding:5px;text-align:left;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
	<option value="311">Dhalai</option>
	<option value="309">Gomati</option>
	<option value="308">Khowai</option>
	<option value="313">North Tripura</option>
	<option value="307">Sepahijala</option>
	<option value="310">South Tripura</option>
	<option value="312">Unakoti</option>
	<option value="306">West Tripura</option>
   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Tripura Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value="40171">Agartala</option>
<option value="40207">Ampinagar</option>
<option value="40190">Asharambari</option>
<option value="40179">Badharghat</option>
<option value="40195">Bagma</option>
<option value="40168">Bamutia</option>
<option value="40174">Banamalipur</option>
<option value="40169">Barjala</option>
<option value="40201">Belonia</option>
<option value="40208">Birganj</option>
<option value="40181">Bishalgarh</option>
<option value="40184">Boxanagar</option>
<option value="40217">Chandipur</option>
<option value="40183">Charilam</option>
<option value="40214">Chhawmanu</option>
<option value="40187">Dhanpur</option>
<option value="40221">Dharmanagar</option>
<option value="40216">Fatikroy</option>
<option value="40182">Golaghati</option>
<option value="40203">Hrishyamukh</option>
<option value="40204">Jolaibari</option>
<option value="40222">Jubarajnagar</option>
<option value="40220">Kadamtala</option>
<option value="40218">Kailasahar</option>
<option value="40199">Kakraban</option>
<option value="40192">Kalyanpur</option>
<option value="40180">Kamalasagar</option>
<option value="40210">Kamalpur</option>
<option value="40225">Kanchanpur</option>
<option value="40170">Khayerpur</option>
<option value="40189">Khowai</option>
<option value="40193">Krishnapur</option>
<option value="40213">Kulai</option>
<option value="40219">Kurti</option>
<option value="40175">Majlishpur</option>
<option value="40176">Mandaibazar</option>
<option value="40205">Manu</option>
<option value="40198">Matarbari</option>
<option value="40167">Mohanpur</option>
<option value="40185">Nalchar</option>
<option value="40215">Pabiachhara</option>
<option value="40224">Panisagar</option>
<option value="40223">Pencharthal</option>
<option value="40191">Pramodenagar</option>
<option value="40178">Pratapgarh</option>
<option value="40197">Radhakishorepur</option>
<option value="40209">Raima Valley</option>
<option value="40200">Rajnagar</option>
<option value="40188">Ramchandraghat</option>
<option value="40172">Ramnagar</option>
<option value="40206">Sabroom</option>
<option value="40212">Salema</option>
<option value="40196">Salgarh</option>
<option value="40202">Santirbazar</option>
<option value="40166">Simna</option>
<option value="40186">Sonamura</option>
<option value="40211">Surma</option>
<option value="40177">Takarjala</option>
<option value="40194">Teliamura</option>
<option value="40173">Town Bordowali</option>
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