<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
	<s:property value="%{electionGoverningBodyVO.stateName}"/> State
	</c:if>
	<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">Indian 
	</c:if>
		Ministers of <s:property value="%{electionGoverningBodyVO.electionYear}" />
</title>

<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js"></script> 

<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script>

<script type="text/javascript"> 
$(document).ready(function(){
	$('.ministerDiv').mouseover(function(){

		$('.ministerDiv').removeClass('ministerDivClass');

		$(this).addClass('ministerDivClass');
	});
});
</script>

<style type="text/css">
.ministerDivClass{

	background-color:#d3d3d3;
	border-radius:5px;
}

.main-mbg {
    background-color: #06ABEA;
    border-radius: 7px 7px 7px 7px;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 977px;
}


#DataTable > table * th{background-color:#ceedf0 !important;}
#ministerOfStateWithIndChargeDiv > table * th {background:#ceedf0 !important;}
#ministersofStateDiv > table * th{background-color:#ceedf0 !important;}

.headingDiv{
	 padding-bottom: 13px;
	 padding-left: 260px;
	 padding-top: 15px;
}

.headingStyle{
	background: #9966CC;
	color: #FFFFFF;
	font-size: 16px;
	font-weight: bold;
	border-radius: 3px;
	padding: 1px 8px;
}
</style>

<script type="text/javascript">

var qtype = "minis";

function getElectionYears(electionType)
{
   stateId = 1;
   if(electionType == "Assembly")
   {
	var stateEle = document.getElementById("stateListId");
    stateId = stateEle.options[stateEle.selectedIndex].value;
	if(stateId == 0)
	  {
	    removeData("yearSelId");
        addData("yearSelId");
	    return ;
	  }
   }
	removeData("yearSelId");
	var jObj = {
			stateId : stateId,
		electionType: electionType,
				task: 'getElectionYearsForAState'
				};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
	callAjax(jObj,url);
}

function showHidsState()
{
	if(document.getElementById("state").checked == true)
		document.getElementById("showHideState").style.display ="block";
	else
	  document.getElementById("showHideState").style.display ="none";
}

function getAllStates()
{    
	var jsObj =
	{ 
		time : new Date().getTime(),
		eleType: 2,
		task:"getStatesForAssign"
	};

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;				
callAjax(jsObj,url);
}

function navigateToMinisterPage()
{
	var electionSelectEl = document.getElementById("yearSelId");
	var electionIdVal = electionSelectEl.options[electionSelectEl.selectedIndex].value
	window.location="ministersPageAction.action?electionId="+electionIdVal; 
}

function getStatesForAssembly()
{
    var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getAllStatesForParliamentMinisters"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;			callAjax(jsObj,url);
}

function getYearsForAssembly()
{
  
    var stateEle = document.getElementById("stateListId");
    var stateId = stateEle.options[stateEle.selectedIndex].value;
	removeData("yearSelId");
    addData("yearSelId");
    if(stateId == 0)
     return;	
    var jsObj =
		{ 
            time : new Date().getTime(),
			stateId: stateId,
			task:"getAllYearsAndElecIdsForAssembly"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getYearsForParliament()
{
	var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getAllYearsAndElecIdsForParliament"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsForParliamentAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jsObj.task == "getMinistryYears")
									{
									      removeData("yearSelId");
									      buildData(myResults,"yearSelId");
										  //setSelectedyear(electionId);
									}
									else if(jsObj.task == "getStatesForAssign")
									{
									      removeData("stateListId");
										  addState("stateListId");
									      buildData(myResults,"stateListId");
									}
									else if(jsObj.task == "getAllStatesForParliamentMinisters")
									{
									      removeData("stateListId");
										  addState("stateListId");
									      buildData(myResults,"stateListId");
										  //setselectedIdforAState(stateId);
									}
									else if(jsObj.task == "getAllYearsAndElecIdsForAssembly")
									{
									      removeData("yearSelId");
										  addData("yearSelId");										  
									      buildData(myResults,"yearSelId");
										 
									}
									else if(jsObj.task == "getAllYearsAndElecIdsForParliament")
									{
									       removeData("yearSelId");
										   //addData("yearSelId");
									      buildData(myResults,"yearSelId");
										  
									}
									
								}
							catch (e) {   
							   //	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildData(results,id)
{
   var elmt = document.getElementById(id);
   
      for(var i in results)
	  {
		var option = document.createElement('option');     
			option.value=results[i].id;
		    option.text=results[i].name;       
        
			try
			{
				elmt.add(option,null); // standards compliant
			}
			catch(ex)
			{
				elmt.add(option); // IE only
			}
	} 
	  
}

function showOthers()
{
	removeData("yearSelId");
	addData("yearSelId");
	document.getElementById("stateListId").value = 0;
}

function getDetails(reqtype)
{
	document.getElementById("showData").innerHTML = "";
    document.getElementById("keyCandidatesData").innerHTML = "";
    var yearEle =  document.getElementById("yearSelId");
	var eleId = yearEle.options[yearEle.selectedIndex].value;
	
	if(eleId == 0)
		return;
	
	var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			reqtype:reqtype,
			task:"getMinsKeyCandAnalysisDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function removeData(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}

function addData(id)
{
  
    var elmt = document.getElementById(id);
   	var option = document.createElement('option');     
		
	option.value= 0;
	option.text= "Select Year";       
        
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	} 
}

function addState(id)
{
  
	var elmt = document.getElementById(id);
	var option = document.createElement('option');     
	
	option.value= 0;
	option.text= "Select State";       
        
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	} 
}

function showMinisPerf(id)
{
	  qtype = "minis";
	  $("#impCandPerf").removeClass("dashBoardtabsDivSelected");
	  $("#minisPerf").addClass("dashBoardtabsDivSelected");
	  document.getElementById("showData").innerHTML = "";
	  document.getElementById("keyCandidatesData").innerHTML = "";
	  document.getElementById("state").checked = true;  
	  document.getElementById("showHideState").style.display = "block";
	  getStatesForAssembly();
}

function showImpCandPerf(id)
{
  qtype = "impCand";
  $("#minisPerf").removeClass("dashBoardtabsDivSelected");
  $("#impCandPerf").addClass("dashBoardtabsDivSelected");
  document.getElementById("showData").innerHTML = "";
  document.getElementById("keyCandidatesData").innerHTML = "";
  document.getElementById("state").checked = true;
  document.getElementById("showHideState").style.display = "block";
  getAllStates();
}

function getYears(type)
{
  if(qtype == "minis")
   { 
    if(type == "Assembly")
	getMinistryYears();
	else
      getYearsForParliament();
   }
   
}

function getMinistryYears()
{
	
	var selectScopeRadio = document.getElementsByName("selectScope");
	var electionType;
	var stateIdEle = document.getElementById("stateListId");
	var stateId = stateIdEle.options[stateIdEle.selectedIndex].value;
	
	for(var i=0;i<selectScopeRadio.length;i++)
	{
		if(selectScopeRadio[i].checked == true)
			electionType = selectScopeRadio[i].value;
	}
		
	
	var jsObj =
		{ 
            time : new Date().getTime(),
			electionType:electionType,
			stateId : stateId,
			task:"getMinistryYears"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMinisterDataAvailableYearsForAState.action?"+rparam;						
	callAjax(jsObj,url);
}

function getDeltailForMinisImpCand()
{
   if(qtype == "minis")
     getDetails('ministers');
   else 
    getDetails('ImportantCandidates');
}

function removeDataDIV()
{
    document.getElementById("showData").innerHTML = "";
	document.getElementById("keyCandidatesData").innerHTML = "";
}

</script>
</head>

<body>

<div id="ministerAnalysisMainDiv" style="width:998px;margin-left:auto;margin-right:auto;float:none;">
	<div id="heading" class="main-mbg" style="width:980px;">
		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
			<s:property value="%{electionGoverningBodyVO.stateName}"/> state
		</c:if>
		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
			Indian 
		</c:if> 
		Ministers Of <s:property value="%{electionGoverningBodyVO.electionYear}"/>
		<span style="margin-top:10px;margin-right:30px;float:right">
		<g:plusone size="medium"></g:plusone>

		<script type="text/javascript">
		(function() {
		var po = document.createElement('script'); 
		po.type = 'text/javascript';
		po.async = true;
		po.src = 'https://apis.google.com/js/plusone.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
		 })();
  
		</script>
		</span>
		
		<span style="margin-top:10px;float:right">
		<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/ministersPageAction.action?electionId=${electionId}">
	Tweet</a>
		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
		</script>
		</span>

		<span style="margin-top:10px;margin-right:18px;float:right">
		<a name="fb_share" share_url="www.partyanalyst.com/ministersPageAction.action?electionId=${electionId}" type="button_count">Share in Facebook</a> 
		<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript">
		</script>
		</span>

	</div>

	<div style="text-align: center;
    width: 700px;
    border-radius: 3px; background:#EEF4F6;margin-top: 13px; margin-left: 97px; padding: 7px 11px 7px 31px; width: 700px; border-radius: 3px 3px 3px 3px;">
	<table>
		<tr>
		<td>
		<input type="radio" id="state" checked="true" name="selectScope" value="Assembly" onclick="removeDataDIV();showHidsState();showOthers();" />&nbsp;&nbsp;<b>Assembly</b>
		</td>
		<td><input type="radio" name="selectScope"  id="parlSel" value="Parliament" onchange="removeDataDIV();showHidsState(); getMinistryYears();"/>&nbsp;&nbsp;<b>Parliament</b>&nbsp;&nbsp;</td>
		  <td><div id="showHideState" style="display:none;"><b>&nbsp;&nbsp;Select State :</b>&nbsp;&nbsp;<select  id="stateListId"   onchange="getMinistryYears();"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId"  onchange="removeDataDIV();getDeltailForMinisImpCand();" ><option value="0">Select Year</option></select></td>
		  
		<td>&nbsp;&nbsp;&nbsp;&nbsp;<b><a onclick="navigateToMinisterPage()"><input type="button" value="View" style="-moz-border-radius: 5px;cursor:pointer;font-weight:bold;font-size:14px;color:#ffffff;background:#06ABEA;border:solid 1px #c3c3c3;"/></a></b></td>
		</tr>
	</table>
	</div>
	
	<div style="width:980px;" id="showData"></div>
	<div style="width:980px;margin-left: 5px;"" id="keyCandidatesData"></div>
	
	<div id="DataTable" style="background:#ffffff;padding-left:70px;margin-top: 20px;">

	<!-- Chief Minister Details Div Start -->

	<c:forEach var="ministerData" items="${candidateMinistriesVO}">
	
	<c:if test="${ministerData.isChiefMinister == true}">
		<div class="headingDiv"><span class="headingStyle">Chief Minister of <s:property value="%{electionGoverningBodyVO.stateName}"/></span></div>
		
	<div id="chiefMinisterDiv"  style="padding-left:0px;" >
		<table cellpadding="0" border="1" style="border-color:#d3d3d3;border-collapse:collapse;" width="95%">
			<tr>
				<td width="10%" style="border-right: solid 1px #FFFFFF;">
					<div style="padding: 10px 14px; margin-right: 8px" class="ministerDiv">
						<a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}">
						<img src="images/candidates/${ministerData.candidateName}.jpg" width="120px;" height="100px;"/></a>
					</div>
				</td>

				<td style="border-right: solid 1px #d3d3d3;" width="25%">
					<table>
						<tr>
							<td>
								<a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" style="color:#8B4724;"><span style="font-weight:bold;font-size:12px;">${ministerData.candidateName}</span>
								</a>
							</td>
						 </tr>
						<tr>
							<td><span style="font-weight:bold;">Party &nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;: &nbsp&nbsp</span> 
								<span><a href="partyPageAction.action?partyId=${ministerData.partyId}"  style="color:#8B4724;">${ministerData.partyName}&nbsp;&nbsp </a></span>
								<a href="partyPageAction.action?partyId=${ministerData.partyId}">
								<span><img src="images/party_flags/${ministerData.partyName}.png" width="50px" height="30px"/></span></a>
							</td>
						</tr>
						<tr>
							<td>
								<c:forEach var="ministry" items="${ministerData.ministries}">
									<c:if test="${ministry.ministry == 'Chief Minister'}">
										<span style="font-weight:bold;">From Date : </span> &nbsp;&nbsp;<span style="color:#8B4724;">${ministry.fromDate}</span>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</table>
				</td>

				<td width="60%">
					<table border="1" style="border-collapse:collapse;border-color:#FFFFFF;">
							<tr>
								<th width="20%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><div>Ministries</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;"><div>From Date</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;border-left:1px solid #d3d3d3;"><div>
								To Date
								</div></th>
							</tr>

							<c:forEach var="ministry" items="${ministerData.ministries}">
									<c:if test="${ministry.ministry != 'Chief Minister'}">
							<tr>
								<td width="30%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><span style="margin-left:40px;font-weight:bold;">${ministry.ministry}</span>
								</td>
								<td width="15%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><span style="margin-left:40px;font-weight:bold;">${ministry.fromDate}</span>
								</td>
								<td style="text-align: center;border-bottom:1px solid #d3d3d3;" width="15%">
								<b>
								<c:if test="${ministry.toDate == null}">
								Working
								</c:if>
								<c:if test="${ministry.toDate != null}">
								${ministry.toDate}
								</c:if>
								</b>
								</td>
							</tr>

							</c:if>
							</c:forEach>
				 </table>
			</td>
		</tr>
	 </table>
    </div>
   </c:if>
  </c:forEach>
	<!-- Chief Minister Details Div End -->

	<!-- Prime Minister Details Div Start -->
	<c:forEach var="primeMinisterData" items="${candidateMinistriesVO}">
	<c:if test="${primeMinisterData.isPrimeMinister == true}">
	<div class="headingDiv"><span class="headingStyle">Prime Minister Of India</span></div>
		<div id="primeMinisterDiv"  style="padding-left:0px;" >
		<table cellpadding="0" border="1" style="border-color:#d3d3d3;border-collapse:collapse;" width="95%">
			<tr>
				<td width="10%" style="border-right: solid 1px #FFFFFF;">
					<div style="padding: 10px 14px; margin-right: 8px" class="ministerDiv">
						<a href="candidateElectionResultsAction.action?candidateId=${primeMinisterData.candidateId}">
						<img src="images/candidates/${ministerData.candidateName}.jpg" style="" width="120px;" height="100px;"/></a>
					</div>
				</td>
				<td style="border-right: solid 1px #d3d3d3;" width="25%">
					<table>
						<tr>
							<td>
								<a href="candidateElectionResultsAction.action?candidateId=${primeMinisterData.candidateId}" style="color:#8B4724;"><span style="font-weight:bold;font-size:12px;">${primeMinisterData.candidateName}</span>
								</a>
							</td>
						 </tr>
						<tr>
							<td><span style="font-weight:bold;">Party &nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;: &nbsp&nbsp</span> 
								<span><a href="partyPageAction.action?partyId=${ministerData.partyId}"  style="color:#8B4724;">${primeMinisterData.partyName}&nbsp;&nbsp </a></span>
								<a href="partyPageAction.action?partyId=${ministerData.partyId}">
								<span><img src="images/party_flags/${primeMinisterData.partyName}.png" width="50px" height="30px"/></span></a>
							</td>
						</tr>
						<tr>
							<td>
								<c:forEach var="ministry" items="${ministerData.ministries}">
									<c:if test="${ministry.ministry == 'Prime Minister'}">
										<span style="font-weight:bold;">From Date : </span> &nbsp;&nbsp;<span style="color:#8B4724;">${ministry.fromDate}</span>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</table>
				</td>
				<td  width="60%">
						<table border="1" style="border-collapse:collapse;border-color:#FFFFFF;">
							<tr>
								<th width="20%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><div>Ministries</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;"><div>From Date</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;border-left:1px solid #d3d3d3;"><div>
								To Date
								</div></th>
							</tr>
							<c:forEach var="ministry" items="${ministerData.ministries}">
									<c:if test="${ministry.ministry != 'Prime Minister'}">
							<tr>
								<td width="30%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><span style="margin-left:40px;font-weight:bold;">${ministry.ministry}</span>
								</td>
								<td width="15%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><span style="margin-left:40px;font-weight:bold;">${ministry.fromDate}</span>
								</td>
								<td style="text-align: center;border-bottom:1px solid #d3d3d3;" width="15%">
								<b>
								<c:if test="${ministry.toDate == null}">
								Working
								</c:if>
								<c:if test="${ministry.toDate != null}">
								${ministry.toDate}
								</c:if>
								</b>
								</td>
							</tr>
							  </c:if>
							</c:forEach>
				 </table>
			</td>
		</tr>
	 </table>
    </div> 
  </c:if>
  </c:forEach>

	<!-- Prime Minister Details Div End -->

	<!-- Deputy Chief Minister Details Div Start -->
	<c:forEach var="deputyChiefMinisterData" items="${candidateMinistriesVO}">
	
	<c:if test="${deputyChiefMinisterData.isDeputyChiefMinister == true}">
		<div class="headingDiv"><span class="headingStyle">Deputy ChiefMinister of <s:property value="%{electionGoverningBodyVO.stateName}"/></span></div>
		
		<div id="deputyChiefMinisterDiv">
			<table border="1" width="95%" style="border-collapse:collapse;border-color:#d3d3d3;" cellpadding="0">
				<tr>
					<td width="10%" style="border-right:1px solid #FFFFFF;">
					<div style="padding: 10px 14px; margin-right: 8px;" class="ministerDiv"><a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}"><img src="images/candidates/${deputyChiefMinisterData.candidateName}.jpg" style="" width="120px;" height="100px;"/></a></div>
					</td>
					<td width="25%" style="border-right:1px solid #d3d3d3;">
						<table>
							<tr>
								<td><span><a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:12px;">${deputyChiefMinisterData.candidateName}</a></span></td>
								</tr>
								<tr>
								<td>
									<span style="font-weight:bold;">Party : </span>
									<span><a href="partyPageAction.action?partyId=${ministerData.partyId}" style="color:#8B4724;">${deputyChiefMinisterData.partyName}</a></span>
									<span><a href="partyPageAction.action?partyId=${ministerData.partyId}"><img src="images/party_flags/${deputyChiefMinisterData.partyName}.png" width="50px" height="30px"/></a></span>

								</td>
							</tr>
							<tr>
								<td>
									<c:forEach var="ministry" items="${deputyChiefMinisterData.ministries}">
									<c:if test="${ministry.ministry == 'Deputy Chief Minister'}">
									<span style="font-weight:bold;">From Date : </span>
									<span style="color:#8B4724;">${ministry.fromDate}</span>
									</c:if>
									</c:forEach>
								</td>
							</tr>
						
						</table>
					</td>
					<td width="60%" valign="top">
						<table border="1" style="border-collapse:collapse;border-color:#ffffff;" width="100%">
						<tr>
							<th width="24%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><span style="font-weight:bold; text-align: center;">Ministries</span></th>
							<th style="font-weight:bold;border-bottom:1px solid #d3d3d3; text-align: center;border-right:1px solid #d3d3d3;" width="15%">From Date</th>
							<th width="15%" style="border-bottom:1px solid #d3d3d3;text-align:center;"><span style="font-weight:bold;">To Date</span></th>
						</tr>
							<c:forEach var="ministry" items="${deputyChiefMinisterData.ministries}">
							<c:if test="${ministry.ministry != 'Deputy Chief Minister'}">
							<tr>
							<td style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;text-align:center;" width="24%"><span style="font-weight:bold;">${ministry.ministry}</span></td>
							<td style="border-bottom:1px solid #d3d3d3;text-align:center;border-right:1px solid #d3d3d3;" width="15%"><span style="font-weight:bold;">${ministry.fromDate}</span></td>
							<td width="15%" style="text-align:center;border-bottom:1px solid #d3d3d3;">
							<b>
							<c:if test="${ministry.toDate == null}">
							<span>Working</span>
							</c:if>
							<c:if test="${ministry.toDate != null}">
							<span>
							${ministry.toDate}
							</span>
							</c:if>
							</b>
							</td>
							</tr>
							</c:if>
							</c:forEach>
							
						</table>
					
					</td>

				</tr>
			</table>

		</div>
	</c:if>
	</c:forEach>

	<!-- Deputy Chief Minister Details Div End -->

	<!-- Cabinet Minister Div Start -->
	<c:if test="${candidateMinistriesVO[0].hasCabinetMinisters}">
		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
		<div class="headingDiv"><span class="headingStyle">Cabinet Minister of <s:property value="%{electionGoverningBodyVO.stateName}" /></span></div>
	</c:if>
	<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
	<div class="headingDiv"><span class="headingStyle">Cabinet Minister of Parliament </span></div>
	</c:if>
	<div id="DataTable" style="background:#ffffff;">
	 <table border="1" style="border-collapse: collapse; margin-top: 9px; width: 95%; text-align:center;">
	<tr>
	<th width="20%">
	Candidate Name
	</th>
	<th width="15%">
	Party
	</th>
	<th width="30%">
	Ministry
	</th>
	<th width="20%">
	From Date
	</th>
	<th width="20%">
	To Date
	</th>
	</tr>
</c:if>
	<c:forEach var="ministerData" varStatus="stat" items="${candidateMinistriesVO}">
	<c:if test="${!ministerData.isDeputyChiefMinister && !ministerData.isChiefMinister}">
	<c:forEach var="cabinetMinister" items="${ministerData.ministryTypes}">
			
			<c:if test="${cabinetMinister == 'Cabinet Minister'}">
	<tr>

	<td rowspan="${ministerData.noOfMinistries}" width="20%"><b><div id="cabinetMinisterDiv${stat.index}" class="ministerDiv"><a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;"> <img src="images/candidates/${ministerData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerData.candidateName}</a></div></b>

	</td>

	<td rowspan="${ministerData.noOfMinistries}" width="10%"><b><a href="partyPageAction.action?partyId=${ministerData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;"><img src="images/party_flags/${ministerData.partyName}.png" width="80px" height="60px"/><br>  ${ministerData.partyName}</a></b>
	</td>

	<c:forEach var="ministry" varStatus="stat" items="${ministerData.ministries}">
	<td style="text-align:left;" width="30%">
	<b> ${ministry.ministry}</b>
	</td>

	<td width="10%">
	<b> ${ministry.fromDate}</b>
	</td>
	<td width="10%">
	<b>
	<c:if test="${ministry.toDate == null}">
	Working
	</c:if>
	<c:if test="${ministry.toDate != null}">
	${ministry.toDate}
	</c:if>
	</b>
	</td>
	</tr>
	</c:forEach>
	</c:if>
	</c:forEach>
	</c:if>
	</c:forEach>
	</table>
	</div>
	<!-- Cabinet Minister Div End -->

	<!-- Ministers of State with Independent Charge Div Start -->
	<c:if test="${candidateMinistriesVO[0].hasMSIC}">
	<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
	<div class="headingDiv"><span class="headingStyle">Ministers of State with Independent Charge of 
		<s:property value="%{electionGoverningBodyVO.stateName}" /></span></div>
	</c:if>
	<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
	<div class="headingDiv"><span class="headingStyle">Ministers of State with Independent Charge of 
		Parliament</span></div>
	</c:if>
	<div id="DataTable" style="background:#ffffff;">
	 <table border="1" style="border-collapse: collapse; margin-top: 9px; width: 95%; text-align:center;">
	<tr>
	<th width="20%">
	Candidate Name
	</th>
	<th width="15%">
	Party
	</th>
	<th width="30%">
	Ministry
	</th>
	<th width="20%">
	From Date
	</th>
	<th width="20%">
	To Date
	</th>
	</tr>
</c:if>
	<c:forEach var="ministerOfStateIndData" varStatus="stat" items="${candidateMinistriesVO}">
	<c:if test="${!ministerOfStateIndData.isDeputyChiefMinister && !ministerOfStateIndData.isChiefMinister}">
	<c:forEach var="ministerOfStateInd" items="${ministerOfStateIndData.ministryTypes}">
			
			<c:if test="${ministerOfStateInd == 'Ministers of State with Independent Charge'}">
	<tr>

	<td rowspan="${ministerOfStateIndData.noOfMinistries}" width="20%"><b><div id="ministerOfStateDiv${stat.index}" class="ministerDiv"><a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerOfStateIndData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;"> <img src="images/candidates/${ministerOfStateIndData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerOfStateIndData.candidateName}</a></div></b>

	</td>

	<td rowspan="${ministerOfStateIndData.noOfMinistries}" width="10%"><b><a href="partyPageAction.action?partyId=${ministerOfStateIndData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;"><img src="images/party_flags/${ministerOfStateIndData.partyName}.png" width="80px" height="60px"/><br>  ${ministerOfStateIndData.partyName}</a></b>
	</td>

	<c:forEach var="ministry" varStatus="stat" items="${ministerOfStateIndData.ministries}">
	<td style="text-align:left;" width="30%">
	<b> ${ministry.ministry}</b>
	</td>

	<td width="10%">
	<b> ${ministry.fromDate}</b>
	</td>
	<td width="10%">
	<b>
	<c:if test="${ministry.toDate == null}">
	Working
	</c:if>
	<c:if test="${ministry.toDate != null}">
	${ministry.toDate}
	</c:if>
	</b>
	</td>
	</tr>
	</c:forEach>
	</c:if>
	</c:forEach>
	</c:if>
	</c:forEach>
	</table>
	</div>
	<!-- Ministers of State with Independent Charge Div End -->

	<!-- Ministers of State Div Start -->

	<c:if test="${candidateMinistriesVO[0].hasMS}">
	<c:out value="${candidateMinistriesVO[0].hasMS}" />
	<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
		<div class="headingDiv" style=" padding-left: 187px;">
			<span class="headingStyle">Ministers of State of <s:property value="%{electionGoverningBodyVO.stateName}" /></span></div>
		</c:if>
		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
		<div class="headingDiv" style=" padding-left: 187px;">
			<span class="headingStyle">Ministers of State of Parliament</span></div>
		</c:if>

		
		<div id="ministersofStateDiv">
		<table border="1" style="border-collapse: collapse; margin-top: 9px; width: 95%;text-align:center;">
	<tr>
		<th width="20%">
		Candidate Name
		</th>
		<th width="10%">
		Party
		</th>
		<th width="30%">
		Ministry
		</th>
		<th width="20%">
		From Date
		</th>
		<th width="20%">
		To Date
		</th>
	</tr>
	</c:if>
		
	<c:forEach var="ministerOfStateData" varStatus="stat" items="${candidateMinistriesVO}">
	<c:if test="${!ministerOfStateData.isDeputyChiefMinister && !ministerOfStateData.isChiefMinister}">
	<c:forEach var="ministersOfState" items="${ministerOfStateData.ministryTypes}" varStatus="loop">
			
	<c:if test="${ministersOfState == 'Ministers of State'}">
	<tr>

	<td rowspan="${ministerOfStateData.noOfMinistries}" width="20%"><b><div id="ministeOfState${stat.index}" class="ministerDiv"><a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerOfStateData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;"> <img src="images/candidates/${ministerOfStateData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerOfStateData.candidateName}</a></div></b>

	</td>

	<td rowspan="${ministerOfStateData.noOfMinistries}" width="10%"><b><a href="partyPageAction.action?partyId=${ministerOfStateData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;"><img src="images/party_flags/${ministerOfStateData.partyName}.png" width="80px" height="60px"/><br>  ${ministerOfStateData.partyName}</a></b>
	</td>

	<c:forEach var="ministry" varStatus="stat" items="${ministerOfStateData.ministries}">
	<td style="text-align:left;" width="30%">
	<b> ${ministry.ministry}</b>
	</td>

	<td width="20%">
	<b> ${ministry.fromDate}</b>
	</td>
	<td width="20%">
	<b>
	<c:if test="${ministry.toDate == null}">
	Working
	</c:if>
	<c:if test="${ministry.toDate != null}">
	${ministry.toDate}
	</c:if>
	</b>
	</td>
	</tr>
	</c:forEach>
	
	</c:if>
	</c:forEach>
	</c:if>
	</c:forEach>
	</table>
	</div>
<!-- Ministers of State Div End -->
	
</div>

</div>
<script type="text/javascript">

	getStatesForAssembly();
	$("#impCandPerf").removeClass("dashBoardtabsDivSelected");
     $("#minisPerf").addClass("dashBoardtabsDivSelected");
	 showHidsState();
	
   </script>
</body>
</html>
