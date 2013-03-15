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
<img src="images/specialPage/nagaland2013electionbanner.jpg" style="align:center;width:975px;">
</div>

<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->


<div id="upComing" class="breadcrumb">
<div style="text-align:justify;margin:10px;padding:10px;"> 

<table>
<tr>

<td  width="45%" valign="top">
<table>
		
	
<h3 class="resulth3">Nagaland 2013 Elections Statistics</h3>
<div id="ElectionStats" style="margin-bottom:10px;">
<span> Total Assembly Constituencies - <font color="#05A8E9">60</font></span><br>
<span> SC Constituencies - <font color="#05A8E9">Nil</font> </span><br>
<span>ST Constituencies - <font color="#05A8E9">59</font></span><br>
<span>General Constituencies - <font color="#05A8E9">1</font></span><br>
</div>
		
<h4 style="background-color: rgb(33, 178, 237); color: rgb(255, 255, 255); border-radius: 3px 3px 3px 3px;padding: 4px; margin-left: 2px;">Schedule for the Nagaland Legislative Assembly, 2013	</h4>
		
		
			<td>
			<table class="nominationresulttable">
<tr>
<th>Poll Event</th>
<th>For 60 Assembly Constituency</th>

</tr>
<tr>
<td>Issue of Notification</td>
<td>30.1.2013
(Wednesday)</td>

</tr>
<tr>
<td>Last date for making Nominations</td>
<td>6.2.2013
(Wednesday)</td>
</tr>
<td>Scrutiny of Nominations</td>
<td>7.2.2013
(Thursday)</td>
</tr>
<tr>
<!--<td>Last date for withdrawal of
candidature</td>-->
<td>Last date for withdrawal of candidature</td>
<td>9.2.2013
(Saturday)</td>
</tr>
<tr>
<td>Date of Poll</td>
<td>23.2.2013
(Saturday)</td>
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
Nagaland Assembly Election Analysis</h3>

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
			<tr><td>NPF</td><td>38</td><td>26</td><td>+12</td></tr>
			<tr><td>BJP</td><td>1</td><td>2</td><td>-1</td></tr>
			<tr><td>NCP</td><td>4</td><td>2</td><td>+2</td></tr>
			<tr><td>INC</td><td>8</td><td>24</td><td>-16</td></tr>
			<tr><td>OTHERS</td><td>8</td><td>6</td><td>+2</td></tr>
			</tbody>
		</table>
		<span style="color:red;">*</span> For 59 constituencies only </br>
		<span style="color:red;">*</span> Change compared to 2008 Election Results
	</td>
</tr>
<td colspan="2">

<table class="breadcrumb">
<tr><th colspan="5">
<h4>View Party Previous Performances From 1982 - 2008</h4>
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
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Nagaland&electionType=2&reportLevel=State&stateSelectName=19&partySelectName=163&__checkbox_alliances=true&submitButton=Submit" class="btn">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
BJP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Nagaland&electionType=2&reportLevel=State&stateSelectName=19&partySelectName=362&__checkbox_alliances=true&submitButton=Submit" class="btn">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
INC</a>
</c:if>
</th>
<!--
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=CPI&selectedPartyId=265&selectedElectionTypeName=Assembly&selectedLocationName=Meghalaya&electionType=2&reportLevel=State&stateSelectName=17&partySelectName=265&__checkbox_alliances=true&submitButton=Submit" class="btn">CPI</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPI</a>
</c:if>
</th>
<!--<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}">
<a href="partyResultsAction.action?selectedPartyShortName=CPM&selectedPartyId=269&selectedElectionTypeName=Assembly&selectedLocationName=Tripura&electionType=2&reportLevel=State&stateSelectName=25&partySelectName=269&alliances=true&__checkbox_alliances=true&submitButton=Submit" class="btn">CPM</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('loginPopupDiv')" class="btn">
CPM</a>
</c:if>
</th>-->
</tr>
</table>
</td>
</tr>

<tr><td colspan="7" class="breadcrumb pre-year-links"> 
<span style="clear: both; display: inline-block; width: 100%;">Previous Nagaland Assembly Elections Results </span>


<a  href="electionDetailsReportAction.action?electionId=223&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=2008" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2008</a>


<a href="electionDetailsReportAction.action?electionId=218&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=2003" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2003</a>


<a  href="electionDetailsReportAction.action?electionId=219&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=1998" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1998</a>


<a  href="electionDetailsReportAction.action?electionId=221&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=1993" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1993</a>


<!--<a href="electionDetailsReportAction.action?electionId=213&stateID=17&stateName=Meghalaya&electionType=Assembly&electionTypeId=2&year=1988"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1988</a>-->


<a  href="electionDetailsReportAction.action?electionId=222&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=1989"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1989</a>

<a href="electionDetailsReportAction.action?electionId=220&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=1987"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1987</a>

<a href="electionDetailsReportAction.action?electionId=224&stateID=19&stateName=Nagaland&electionType=Assembly&electionTypeId=2&year=1982"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1982</a>

</td></tr>
<tr> <td>
<table class="breadcrumb" style="margin-top:10px;">
<tr>
<td width="50%" colspan="2" >
<span>View Nagaland Districts And Constituencies Results</span>
<br></br></td></tr>
<tr>
<td >
<div class="selectHeading">
	<span class="selectDivStyle">Nagaland Districts</span>
	<p style="padding:5px;text-align:left;">Know About Your District </p>
	<select class="selectBoxWidth" id="selectedDistrictInSpecialPage" name="district" style="margin-top:45px;">
	<option value="0">Select District</option>
		<option value="321">Dimapur</option>
		<option value="332">Kiphire</option>
		<option value="323">Kohima</option>
		<option value="330">Longleng</option>
		<option value="326">Mokokchung</option>
		<option value="329">Mon</option>
		<option value="322">Peren</option>
		<option value="325">Phek</option>
		<option value="324">Pughoboto</option>
		<option value="331">Tuensang</option>
		<option value="328">Wokha</option>
		<option value="327">Zunheboto</option>
   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPageFrmSpeclPge('selectedDistrictInSpecialPage','alertMessage_district')" href="javascript:{}">view results</a></div>


</div>
</td>
<td>
<div class="selectHeading">
	<span class="selectDivStyle">Nagaland Constituencies </span>
	<p style="padding:5px;">Know About Your Assembly Constituency</p>
<select class="selectBoxWidth" id="selectedConstituencyInSpecialPage" name="constituency">
<option value="0">Select Constituency</option>
<option value="40410">Aboi</option>
<option value="40397">Aghunato</option>
<option value="40394">Akuluto</option>
<option value="40393">Alungtaki</option>
<option value="40387">Angetyongpang</option>
<option value="40389">Aonglenden</option>
<option value="40385">Arkakong</option>
<option value="40395">Atoizu</option>
<option value="40403">Bhandari</option>
<option value="40381">Chazouba</option>
<option value="40380">Chizami</option>
<option value="40364">Dimapur I</option>
<option value="40365">Dimapur Ii</option>
<option value="40366">Dimapur Iii</option>
<option value="40367">Ghaspani I</option>
<option value="40368">Ghaspani Ii</option>
<option value="40386">Impur</option>
<option value="40392">Jangpetkong</option>
<option value="40372">Kohima Town</option>
<option value="40391">Koridang</option>
<option value="40415">Longkhim Chare</option>
<option value="40413">Longleng</option>
<option value="40427">Mahendraganj</option>
<option value="40383">Meluri</option>
<option value="40411">Moka</option>
<option value="40390">Mokokchung Town</option>
<option value="40409">Mon Town</option>
<option value="40388">Monguya</option>
<option value="40419">Noklak</option>
<option value="40414">Noksen</option>
<option value="40424">Northern Angami I</option>
<option value="40426">Northern Angami I</option>
<option value="40425">Northern Angami Ii</option>
<option value="40373">Northern Angami I</option>
<option value="40374">Northern Angami Ii</option>
<option value="40370">Peren</option>
<option value="40379">Pfutsero</option>
<option value="40382">Phek</option>
<option value="40407">Phomching</option>
<option value="40376">Pughoboto</option>
<option value="40423">Pungro Kiphire</option>
<option value="40402">Sanis</option>
<option value="40399">Satakha</option>
<option value="40421">Shamtorr Chessore</option>
<option value="40422">Siyuchong Sitimi</option>
<option value="40377">Southern Angami I</option>
<option value="40378">Southern Angami Ii</option>
<option value="40396">Suruhuto</option>
<option value="40412">Tamlu</option>
<option value="40406">Tapi</option>
<option value="40408">Tehok</option>
<option value="40369">Tenning</option>
<option value="40420">Thonoknyu</option>
<option value="40404">Tizit</option>
<option value="40418">Tobu</option>
<option value="40375">Tseminyu</option>
<option value="40416">Tuensang Sadar I</option>
<option value="40417">Tuensang Sadar Ii</option>
<option value="40384">Tuli</option>
<option value="40400">Tyui</option>
<option value="40405">Wakching</option>
<option value="40371">Western Angami</option>
<option value="40401">Wokha</option>
<option value="40398">Zunheboto</option>
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
				electionId:226,
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
				electionId:226,
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
				electionId:226,
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
				specialPageId:"19",
	           
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
	electionResult+='Party Wise Results Of Nagaland Vidhan Sabha 2013</h3>';
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
	chart.draw(data,{width:400, height: 250, title:'Nagaland Vidhan Sabha Election 2013 Lead/Won Chart'});
	}
}

 getSpecialPageHighLights();



</script>