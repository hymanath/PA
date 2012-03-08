<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
 #electionResultLinks li {
    background: url("images/icons/diamond.png") no-repeat scroll left 12px transparent;
    float: left;
    line-height: 18px;
    padding-left: 14px;
    width: 167px;
}
.grad {
    background: #0f4b93; border-radius:5px;
}
#imgSpan {
	background-color: #F0F0F0;
	float: left;
	width: 127px;
	text-align: center;
	position: absolute;
	left: 0px;
	bottom: 0px;
	height: 21px;
	color: #2A4F97;
	font-size: 11px;
	line-height: 21px;
}
a:hover{
text-decoration:none;
}

#UPtableDiv > table * td{text-align:center;}


</style>

<div style="background:#ffffff; width: 979px;padding-bottom: 13px;">
<img src="images/specialPage/up2012election.png" style="align:center;width:985px;" usemap="#2012ElectionsMap" alt="UttarPradesh 2012 Election" title="UttarPradesh 2012 Election" />

<table width="100%" style="margin-left: 9px;margin-center: 9px; border-top: 1px solid #dddddd; margin-top: 4px;" >
<tr>
 <td  width="45%">

 
 <table bordercolor="#d2e888" cellspacing="0" cellpadding="0" border="1" align="center"  style="border-collapse: collapse;font-size:12px;" width="100%">

<!-- <h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;">Election Schedule For UttarPradesh</h3><tr><td><br>
	 	 <a style="color: rgb(255, 255, 255); background-color: CornflowerBlue; font-weight: bold; padding: 3px; margin-top:10px;cursor:pointer;margin-left: 4px;" onclick="openFile('images/specialPage/UttarPradesh.pdf')" title="Click Here To view Election Schedule for 5 Phases"> View Election Schedule for 5 Phases And Participating Constituency In Each Phase</a><br></br><br /></td></tr>-->

		 <br /><h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px; margin-top: -18px;">2012 UttarPradesh Exit Polls And Surveys</h3>
<tr style="border: medium none rgb(255, 255, 255);">

	<td>
	<div>
	<br><span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;font-weight:bold;">Male And Female Candidates Performances</span>

	<div style="margin:15px;margin-bottom:0px"><span style="font-weight: bold;">Select Election Year:</span>

		<select onchange="getGenderInfo()" id="selectedYear" style="width:120px;">
		<option value="0">Select Year</option>
		<option value="181">2012</option>
		<option value="159">2002</option>
		<option value="160">1996</option>
		<option value="162">1993</option>
		<option value="161">1991</option>
		<option value="163">1989</option>
		</select>
	</div>
	</div>
	</td>
</tr>

<tr style="border: medium none rgb(255, 255, 255);"><td>
<div id="UPtableDiv" style="margin-top:17px;">
<span style="background:#D2E888;padding:4px;margin-left:9px;-moz-border-radius: 3px;font-weight:bold;">2012 UttarPradesh State Exit polls</span>
<table width="96%" cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse;margin-top:9px;margin-left:9px;">
<tr>

<!--<th>Source</th>
<th>INC</th>
<th>BJP</th>
<th>NCP</th>
<th>MAG</th>
<th>OTHERS</th>-->
<th> Source</th>
<th>BJP</th>
<th>BSP</th>
<th>CONG+</th>
<th>SP</th>
<th>OTHERS</th>
</tr>
<tr>
    <th>NDTV</th>
    <td>68</td>
    <td>98</td>
    <td>53</td>
	<td>172</td>
    <td>12</td>

  </tr>
  <tr>


  
 <tr> <th>STAR</th>
    <td>71</td>
    <td>83</td>
    <td>62</td>
	<td>183</td>
    <td>-</td></tr>



	<tr><th>NEWS-24</th>
    <td>55</td>
    <td>85</td>
    <td>55</td>
	<td>185</td>
    <td>-</td>

	</tr>
<tr>	<th>India TV-C</th>
    <td>71</td>
    <td>83</td>
    <td>62</td>
	<td>137-145</td>
    <td>-</td></tr><tr>



  </tr>
</table></div>
</div>



</td></tr>

<tr><td>

<br><b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888; margin-left: 10px;">2012 UttarPradesh Assembly Survey Results</span></b><br /><br />
<table bordercolor="#d2e888" cellspacing="0" cellpadding="5" border="1" align="center"  style="border-collapse: collapse; " width="100%"><strong style="padding-left: 176px;">Bundelkhand (19 Seats)</strong>
            <tbody><tr>
			  <td><b>Source</b></td>
              <td><b>BSP</b></td>
              <td><b>SP</b></td>
			  <td><b>INC</b></td>
			  <td><b>BJP</b></td>
			  <td><b>RLD</b></td>
            </tr>
			<tr>
				<td>STAR NEWS</td>
				<td>5</td>
				<td>9</td>
				<td>5</td>
				<td>0</td>
				<td>0</td>
			</tr>
</table>
<table bordercolor="#d2e888" cellspacing="0" cellpadding="5" border="1" align="center"  style="border-collapse: collapse; " width="100%"><strong style="padding-left: 192px;">Awadh (63 Seats)</strong>
            <tbody><tr>
			  <td><b>Source</b></td>
              <td><b>BSP</b></td>
              <td><b>SP</b></td>
			  <td><b>INC</b></td>
			  <td><b>BJP</b></td>
			  <td><b>RLD</b></td>
            </tr>
			<tr>
				<td>STAR NEWS</td>
				<td>10</td>
				<td>24</td>
				<td>22</td>
				<td>7</td>
				<td>0</td>
			</tr>
</table>
<table bordercolor="#d2e888" cellspacing="0" cellpadding="5" border="1" align="center"  style="border-collapse: collapse; " width="100%"><strong style="margin-left: 170px;">Paschim Pradesh (145 Seats)</strong>
            <tbody><tr>
			  <td><b>Source</b></td>
              <td><b>BSP</b></td>
              <td><b>SP</b></td>
			  <td><b>INC</b></td>
			  <td><b>BJP</b></td>
			  <td><b>RLD</b></td>
            </tr>
			<tr>
				<td>STAR NEWS</td>
				<td>40</td>
				<td>42</td>
				<td>8</td>
				<td>40</td>
				<td>10</td>
			</tr>
</table>
<table bordercolor="#d2e888" cellspacing="0" cellpadding="5" border="1" align="center"  style="border-collapse: collapse; " width="100%"><strong style="padding-left: 178px;">Purvanchal (176 Seats)</strong>
            <tbody><tr>
			  <td><b>Source</b></td>
              <td><b>BSP</b></td>
              <td><b>SP</b></td>
			  <td><b>INC</b></td>
			  <td><b>BJP</b></td>
			  <td><b>RLD</b></td>
            </tr>
			<tr>
				<td>STAR NEWS</td>
				<td>65</td>
				<td>60</td>
				<td>33</td>
				<td>18</td>
				<td>0</td>
			</tr>
</table>
<table width="70%" align="center" border="0"><tr><td align="center">
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
</td></tr>
</table>

</td>
	<td width="2%"></td>

	<td width="43%" valign="top">
<table style="border:1px solid #d2e888;padding-left:20px" width="98%" valign="top">
<tr><td>
<table><td><tr>
<td style="padding-left: 47px;"><span style="font-weight:bold">Total Seats:403</span></td>
<td style="margin-right: 2px; border-right-width: 3px; padding-right: 7px; padding-left: 53px;"><span style="font-weight:bold">Know Results/Leading:403</span></td>
</td></tr></table></td>
</tr>

<tr>
	<td>
		<table cellspacing="0" cellpadding="5" bordercolor="#d2e888" border="1" style="border-collapse: collapse; margin-top: 9px; width: 100%; margin-left: -10px;">
			<tr><th>Party Name</th>
			<th>Participated Seats</th>
			<th>2007 Won</th>
			<th>Won/Leading</th>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=239">BSP</a></th>
			<td>403</td>
			<td>206</td>
			<td>84</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=163">BJP</a></th>
			<td>350</td>
			<td>51</td>
			<td>52</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=362">CONC+</a></th>
			<td>393</td>
			<td>22</td>
			<td>37</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=839">SP</a></th>
			<td>393</td>
			<td>97</td>
			<td>214</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=729">RLD</a></th>
			<td>254</td>
			<td>10</td>
			<td>0</td>
			</tr>
			<tr>
			<th><a href="partyPageAction.action?partyId=729">OTHERS</a></th>
			<td>--</td>
			<td>--</td>
			<td>16</td>
			</tr>
		</table>
	</td>
</tr>

<tr>
<td>
<center><object height="220" width="320"><param value="http://www.youtube.com/v/mMTRWXNVXCw?version=3&autoplay=1" name="movie"><param value="true" name="allowFullScreen"><param value="always" name="allowscriptaccess"><embed height="220" width="320" allowfullscreen="true" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://www.youtube.com/v/mMTRWXNVXCw?version=3&autoplay=1"></object></center>
<table>
<br>
<span style="-moz-border-radius:3px;padding:3px;background: #d2e888;font-weight:bold;">View Party Previous Performances</span>
<br><br>
<tr>

<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BSP&selectedPartyId=239&selectedElectionTypeName=Assembly&selectedLocationName=Uttar+Pradesh&electionType=2&reportLevel=State&stateSelectName=27&partySelectName=239&alliances=true&__checkbox_alliances=true&submitButton=Submit">BSP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('${sessionScope.USER}','loginPopupDiv')">
BSP</a>
</c:if>
</th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=BJP&selectedPartyId=163&selectedElectionTypeName=Assembly&selectedLocationName=Uttar+Pradesh&electionType=2&reportLevel=State&stateSelectName=27&partySelectName=163&alliances=true&__checkbox_alliances=true&submitButton=Submit">BJP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('${sessionScope.USER}','loginPopupDiv')">
BJP</a>
</c:if></th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="partyResultsAction.action?selectedPartyShortName=INC&selectedPartyId=362&selectedElectionTypeName=Assembly&selectedLocationName=Uttar+Pradesh&electionType=2&reportLevel=State&stateSelectName=27&partySelectName=362&alliances=true&__checkbox_alliances=true&submitButton=Submit">INC</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('${sessionScope.USER}','loginPopupDiv')">
INC</a>
</c:if></th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="javascript:{}">SP</a>
</c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('${sessionScope.USER}','loginPopupDiv')">
SP</a>
</c:if></th>
<th style="padding-right: 27px;">
<c:if test="${loginStatus == 'out'}"> 
<a href="javascript:{}">RLD</a></c:if>
<c:if test="${sessionScope.USER == null}"> 
<a href="javascript:{}" onclick="checkUserLoginStatus('${sessionScope.USER}','loginPopupDiv')">
RLD</a>
</c:if></th></tr>
</table>
</td>
</tr>

<tr>
<td colspan="7"> 
<br>
<b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Previous UttarPradesh Assembly Elections Results </span></b><br></br><br>
<img src="images/icons/diamond.png" />
	<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=158&stateID=27&stateName=Uttar%20Pradesh&electionType=Assembly&electionTypeId=2&year=2007"> 2007</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a  style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=159&stateID=27&stateName=Uttar Pradesh&electionType=Assembly&electionTypeId=2&year=2002"> 2002</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=160&stateID=27&stateName=Uttar%20Pradesh&electionType=Assembly&electionTypeId=2&year=1996"> 1996</a>
&nbsp;
<img src="images/icons/diamond.png">
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=162&stateID=27&stateName=Uttar%20Pradesh&electionType=Assembly&electionTypeId=2&year=1993"> 1993</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=161&stateID=27&stateName=Uttar%20Pradesh&electionType=Assembly&electionTypeId=2&year=1991"> 1991</a>
<br /><br />
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=163&stateID=27&stateName=Uttar%20Pradesh&electionType=Assembly&electionTypeId=2&year=1989"> 1989</a>
&nbsp;
<img src="images/icons/diamond.png" />
<a style="color: rgb(255, 255, 255); background-color: threeddarkshadow; font-weight: bold; padding: 3px;" href="electionDetailsReportAction.action?electionId=164&stateID=27&stateName=Uttar%20Pradesh&electionType=Assembly&electionTypeId=2&year=1985"> 1985</a><br></br>
&nbsp;

</td></tr>

<!--<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 96.5%;">
UttarPradesh Assembly Previous Election Results</h3>-->
<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 96.5%;">
2012 UttarPradesh Live Election Results</h3>

<tr style="margin-left: 10px; margin-top: 5px;">
<td colspan="7"><br><b><span style="-moz-border-radius:3px;padding:3px;margin-top: 15px;background: #d2e888;">Major Parties In the State  </span></b><br /><br />
		   
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=239">BSP<img src="images/party_flags/BSP.png" height="40px" width="60px">
<span></span></a>&nbsp;&nbsp;&nbsp; 
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=163" align="top">BJP<img src="images/party_flags/BJP.png" height="40px" width="60px"> 
<img src="images/icons/diamond.png"><span></span></a>&nbsp;&nbsp;&nbsp;
<a href="partyPageAction.action?partyId=362" align="top">INC&nbsp;<img src="images/party_flags/INC.png" height="40px" width="60px"></a>&nbsp;&nbsp;&nbsp; <BR /><BR />
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=839">SP&nbsp;<img src="images/party_flags/SP.png" height="40px" width="60px"></a>&nbsp;&nbsp;
<img src="images/icons/diamond.png">
<a href="partyPageAction.action?partyId=729">RLD</a>&nbsp;<br></br>
</tr>


</table>




</td></tr>
</table>
<div id="genderInfoDiv">
<div id="genderAnalysisDiv"></div></div>
<script type="text/javascript">

function getGenderInfo()
{
	var electionIdSelectedEle = document.getElementById('selectedYear');
	var electionId = electionIdSelectedEle.value;
	var jsObj = {
	            time:new Date().getTime(),
				electionId:electionId,
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
										buildGenderCountResultsDataTable(myResults);
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

function buildGenderCountResultsDataTable(myResults)
{
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
								minHeight: 650,
								title:'<center><font color="Navy"></font><center>',
								overlay: { opacity: 0.5, background: 'black'}
								});
		$("#genderInfoDiv").dialog();

	
	var str= '';
	str +='<h3 style="background: none repeat scroll 0% 0% rgb(163, 163, 163); text-align: center; padding: 10px 0px 8px; border-right-width: 0px; font-size: 16px;color:#FFFFFF;margin-top: 13px;">Male And Female Participants And Their Results</h3>';

	str +='<table cellspacing="0" cellpadding="5" bordercolor="#cccccc" border="1" style="margin-top: 22px;">';
	str +='<tr style="background: none repeat scroll 0% 0% aliceBlue;">';
	str +='<th style="font-size: 13px;">Party</th>';
	str +='<th style="font-size: 13px;">TP*</th>';
	str +='<th style="font-size: 13px;">Won</th>';
	str +='<th style="font-size: 13px;">CV* %</th>';
	str +='<th style="font-size: 13px;">PV* %</th>';
	str +='<th style="font-size: 13px;">Male Participants</th>';
	str +='<th style="font-size: 13px;">Male Won</th>';
	str +='<th style="font-size: 13px;">Male Votes %</th>';
	str +='<th style="font-size: 13px;">Female Participants</th>';
	str +='<th style="font-size: 13px;">Female Won</th>';
	str +='<th style="font-size: 13px;">Female Votes %</th>';
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
	str +='<tr><td><b>TP* = Total Participation ,CV* % = Complete Votes Percentage </b></td>'
	str +='</tr>';
	str +='<td><b>PV* % = Participated Votes Percentage</b></td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	document.getElementById('genderAnalysisDiv').innerHTML = str;

}

</script>


