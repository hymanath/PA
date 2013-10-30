<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
	<s:property value="%{electionGoverningBodyVO.stateName}"/> State </c:if><c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">Indian </c:if> Ministers of <s:property value="%{electionGoverningBodyVO.electionYear}" />
</title>
<s:if test="metaInfoVO != null && metaInfoVO.keywords != null">
<META NAME="Keywords" CONTENT="${metaInfoVO.keywords}"/>
</s:if>
<s:if test="metaInfoVO != null && metaInfoVO.description != null">
<meta name="description" content="${metaInfoVO.description}"/>
</s:if>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js"></script> 
<script type="text/javascript"> 
$(document).ready(function(){

	$('.constituencyInState').hide();
	 $('.closebutton').hide();
	 $('.notesDiv').hide();
var rowCountForIndependentResigned = $('#independentResigned tr').length;
var rowCountForministersOfStateResigned = $('#ministersOfStateResigned tr').length;
var rowCountForCabinetResigned = $('#cabinetResigned tr').length;
	$(".cabinetMinistersDiv tr").each(function(){

		if($(this).find("td:eq(2) > table").hasClass('cabinetMinistersTable'))
		if($(this).find("td:eq(2) > table tr").length == 0)
			$(this).remove();
	});

	$(".independentMinistersDiv tr").each(function(){

		if($(this).find("td:eq(2) > table").hasClass('independentMinistersTable'))
		if($(this).find("td:eq(2) > table tr").length == 0)
			$(this).remove();
	});



	$(".ministerOfStateDiv tr").each(function(){

		if($(this).find("td:eq(2) > table").hasClass('ministerOfStateTable'))
		if($(this).find("td:eq(2) > table tr").length == 0)
			$(this).remove();
	});

	$(".cabinetResignedMinistersDiv tr").each(function(){

		if($(this).find("td:eq(2) > table").hasClass('cabinetResignedMinistersTable'))
		if($(this).find("td:eq(2) > table tr").length == 0)
			$(this).remove();
	});


	$(".resignedIndependentMinistersDiv tr").each(function(){


		if($(this).find("td:eq(2) > table").hasClass('resignedIndependentMinistersTable')){
			
		if($(this).find("td:eq(2) > table tr").length == 0)
			$(this).remove();
		}
	});

	$(".resignedMinistersOfState tr").each(function(){

		if($(this).find("td:eq(2) > table").hasClass('resignedMinistersOfTable'))
		if($(this).find("td:eq(2) > table tr").length == 0)
			$(this).remove();
	});
	
	if($('#ministersofStateDivTable tr').length == 2)
	{
		$('#ministersOfStateMainDiv').hide();
		$('#ministersofStateDivTable').hide();
	}
	if($('#cabinetResignedDivTable tr').length == 2)
	{
		$('#cabinetResignedDiv').hide();
		$('#cabinetResignedDivTable').hide();
	}
	if($('#ministersofTable tr').length == 2)
	{
		$('#ministersOfStateResignedDiv').hide();
		$('#ministersofTable').hide();
	}
	if($('#ministersofStateTable tr').length == 2)
	{
		$('#ministersofDiv').hide();
		$('#ministersofStateTable').hide();
	}
	if($('#CabinetMinistersTable tr').length == 2)
	{
		$('#cabinetMinisterDiv').hide();
		$('#CabinetMinistersTable').hide();
	}
if(rowCountForIndependentResigned == 1){
	 $('#independentResigned').hide();
	 $('#independentResignedDiv').hide();
	  
 }

 if(rowCountForCabinetResigned == 1){
  $('#cabinetResigned').hide();
  $('#cabinetResignedDiv').hide();

 }

 if(rowCountForministersOfStateResigned == 1){
	 $('#ministersOfStateResigned').hide();
	 $('#ministersOfStateResignedDiv').hide();
 }
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
	 padding-top: 10px;
}
.headingStyle{
	background: #9966CC;
	color: #FFFFFF;
	font-size: 16px;
	font-weight: bold;
	border-radius: 3px;
	padding: 1px 8px;
}
.dl-horizontal-cust{display:inline-block;clear:both;background:#fff;width:100%;margin-top:0px;}
.dl-horizontal-cust dt{display:inline-block;width:50%;text-align:right;font-weight:normal;margin:5px;}
.dl-horizontal-cust dd{display:inline-block;width:auto;clear:right;margin:5px;}
.constituencyInState>div:nth-child(4n+1){clear:left;}
.constituencyInState>div{margin-left:15px;}
.constituencyInState h4{text-align:center; background-color: #49AFCD;
    background-image: -moz-linear-gradient(center top , #5BC0DE, #2F96B4);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);border-image: none;
    border-radius: 4px 4px 4px 4px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);  color: #FFFFFF;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);}
.constituencyInState h4 span{margin-left:15px;}
</style>
<script type="text/javascript">
var qtype = "minis";
var minStateId = '${electionGoverningBodyVO.stateId}';
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
		$('#showHideState').show('fast');
		//document.getElementById("showHideState").style.display ="block";
	else
		$('#showHideState').hide('fast');
	 // document.getElementById("showHideState").style.display ="none";
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
										  
										  if(jsObj.taskType != null && jsObj.taskType == 'onLoad')
										  setSelectedyear(myResults,'${electionGoverningBodyVO.electionYear}');
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
										  setselectedIdforAState(myResults,minStateId);
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
function setselectedIdforAState(result,stateId)
{
	if('${electionGoverningBodyVO.electionType}' == 'Parliament')
	{
		document.getElementById("parlSel").checked = true;
		showHidsState();
	}
	else
	{
		for(var i=0;i<result.length;i++)
		if(result[i].id == stateId)
			document.getElementById('stateListId').selectedIndex = i+1;
	}
	getMinistryYears('onLoad');
}
function setSelectedyear(result,selYear)
{
	for(var i=0;i<result.length;i++)
	if(result[i].name == selYear)
		document.getElementById('yearSelId').selectedIndex = i;
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
function getMinistryYears(taskType)
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
			taskType : taskType,
			task:"getMinistryYears"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMinisterDataAvailableYearsForAState.action?"+rparam;						
	callAjax(jsObj,url);
}
</script>
</head>
<body>
<div id="ministerAnalysisMainDiv" style="width:998px;margin-left:auto;margin-right:auto;float:none;font-family:sans-serif">
	<div id="heading" class="main-mbg" style="width:980px;">
		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
			<s:property value="%{electionGoverningBodyVO.stateName}"/> State Ministers 
		</c:if>
		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
			Manmohan Singh Cabinet Ministers  
		</c:if> 
		<!-- <s:property value="%{electionGoverningBodyVO.electionYear}"/> To tillDate.-->
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
		<a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/ministersPageAction.action?electionId=${electionId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>
		</span>

	</div>


<div style="margin-top:46px;margin-bottom:40px;text-align:center;">
<span class="badge badge-info" style="padding:14px;background-color:#06ABEA;" >
	<label style="padding:9px;"><input type="radio" value="Assembly" name="selectScope" checked="true" id="state"><b style="font-size:14px;" onclick="showHidsState();showOthers();showStatesDiv();">Assembly</b></label>

	<label style="padding:9px;"><input type="radio"  value="Parliament" id="parlSel" name="selectScope" onchange="hideStatesDiv(); getMinistryYears();"><b style="font-size:14px;">Parliament</b></label>
	<label style="margin-left:27px;"><span class="stateLabel"><b>Select State :</b></span>
		<select  onchange="getMinistryYears();" id="stateListId" style="padding:3px;box-shadow:1px 2px 9px #005FCC;">
		<option value="0">Select State</option>			  
		</select>
	</label>
	<label>
	<span><b>Select Year :</b></span>
		<select style="padding:3px;box-shadow:1px 2px 9px #005FCC;width:67px;" id="yearSelId">
		<option value="0">Select Year</option>
		</select>
	</label>
	<label>
	<a onclick="navigateToMinisterPage()"><input type="button" class="btn" value="View"></a>
	</label>
	</span>

</div>
<!--


	<div style="text-align:center;width:700px;border-radius:3px;background:#E0F3A3;;margin-top: 13px;margin-left:97px;padding:7px 11px 7px 31px;width:725px;border-radius:3px 3px 3px 3px;margin-bottom:10px;">
	<table>
		<tr>
		<td>
		<label><input type="radio" id="state" checked="true" name="selectScope" value="Assembly" onclick="showHidsState();showOthers();" />&nbsp;&nbsp;<b>Assembly</b></label>
		</td>
		<td>
		<label><input type="radio" name="selectScope"  id="parlSel" value="Parliament" onchange="showHidsState(); getMinistryYears();"/>&nbsp;&nbsp;<b>Parliament</b></label>&nbsp;&nbsp;</td>
		  <td><div id="showHideState" style="display:none;"><b>&nbsp;&nbsp;Select State :</b>&nbsp;&nbsp;<select  style="padding:3px;box-shadow:1px 2px 9px #53A753;" id="stateListId"   onchange="getMinistryYears();"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId" style="padding:3px;box-shadow:1px 2px 9px #53A753;width:67px;" ><option value="0">Select Year</option></select></td>
		  
		<td>&nbsp;&nbsp;&nbsp;&nbsp;<b><a onclick="navigateToMinisterPage()"><input type="button" value="View" class="btn btn-info"/></a></b></td>
		</tr>
	</table>
	</div>
	-->
<!-- NO OF MINISTERS IN EACH STATE START FOR PARLIMENT-->
<!--<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
	<div class="alert alert-info" style="margin-top:15px;height:89px;padding:21px;border:1px solid #3A87AD;">
		<c:forEach var="entry" items="${candidateMinistriesVO[0].statesMap}">
		<div style="width:150px;float:left;margin-right:9px;border-bottom:1px solid #3A87AD;"><b> <c:out value="${entry.key}"/>  </b>
		<b style="float:right;"> - &nbsp<c:out value="${entry.value}"/></b></div>
		</c:forEach>
	 </div>
 </c:if>-->
 <!-- NO OF MINISTERS IN EACH STATE END FOR PARLIMENT-->
 <!-- NO OF MINISTERS IN EACH STATE START FOR PARLIMENT-->
<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
<b class="alert alert-info" style="padding:4px;border:1px solid #005580;">Ministers Count in States:</b>
	<div class="alert alert-info" style="margin-top:15px;height:89px;padding:21px;border:1px solid #3A87AD;">
		<c:forEach var="state" items="${candidateMinistriesVO[0].statesList}">
		<div style="width:179px;float:left;margin-right:9px;border-bottom:1px solid #3A87AD;"><b><a href="statePageAction.action?stateId=${state.stateId}" title="Click Here to View ${state.stateName}  Details ">${state.stateName} </a></b>
		<b style="float:right;"> - &nbsp${state.stateCount} </b></div>		
		</c:forEach>
	 </div>
 </c:if>
 <!-- NO OF MINISTERS IN EACH STATE END FOR PARLIMENT-->
  <!-- NO OF MINISTERS IN EACH STATE START FOR ASSEMBLY -->
<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
<b class="alert alert-info" style="padding:4px;border:1px solid #005580;">Ministers Count in Districts:</b>
	<div class="alert alert-info" style="margin-top:15px;height:89px;padding:21px;border:1px solid #3A87AD;">
		<c:forEach var="district" items="${candidateMinistriesVO[0].districtList}">
		<div style="width:175px;float:left;margin-right:9px;border-bottom:1px solid #3A87AD;"><b><a href="districtPageAction.action?districtId=${district.districtId}&districtName=${district.districtName}" title="Click Here to View ${district.districtName}  District Details">${district.districtName}</a> </b>
		<b style="float:right;"> - &nbsp${district.districtCount} </b></div>		
		</c:forEach>
	 </div>
 </c:if>
 <!-- NO OF MINISTERS IN EACH STATE END FOR ASSEMBLY -->
 <!-- TOTAL CONSTITUENCIES INFORMATIONFOR ASSEMBLY  START-->
 <c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
 <a  class="btn btn-primary completeDetailsBtn" href="javaScript:{showBlock();}"  title="Click here to View District wise Party Performance"><b> Click here to View District wise Party Performances in ${electionGoverningBodyVO.electionYear} Assembly Election </b></a>

<a class="btn btn-primary closebutton" href="javaScript:{hideBlock();}" title="Hide more details"><b> Hide District Details</b></a>
<a href="javaScript:{redirectToResultsPage();}" title="Click here to view  <s:property value='%{electionGoverningBodyVO.stateName}'/> ${electionGoverningBodyVO.electionYear} Assembly Election Results" class="btn btn-primary"><b>Click here to view <s:property value="%{electionGoverningBodyVO.stateName}"/> ${electionGoverningBodyVO.electionYear} Assembly Election Results</b></a>
<div  class="alert alert-info notesDiv"  style="margin-left:24px;margin-top:20px;">
	 <span><b>NOTE : </b> *  : Total constituencies participated by party.</span><br>
	 <span style="margin-left:45px;"> ** : Total no of constituencies won by party.</span>
</div>
<div class="constituencyInState row breadcrumb" style="width:999px;">
<c:forEach var="district" items="${candidateMinistriesVO[0].districtConstituencyCountMap}">
<div  class="span2 breadcrumb">
	<h4>${district.key}<span class="pager"><a>${district.value.totalConstituencies}</a></span></h4>
	<dl class="dl-horizontal-cust">
	 <c:forEach var="party" items="${district.value.partyElectionResultsList}">
	  <dt> ${party.partyName}(${party.totalSeatsParticipated}*)</dt> <dd class="badge badge-success">${party.totalSeatsOwn}</dd>**
	 </c:forEach>
	 	</dl>
	 </div>
</c:forEach>
</div> 
<a class="btn btn-primary closebutton" href="javaScript:{hideBlock();}" title="Hide more details"><b>Hide District Details</b></a>
 </c:if>
  <!-- TOTAL CONSTITUENCIES INFORMATION FOR ASSEMBLY END-->
<script>
function redirectToResultsPage(){

	var stateId = $('#stateListId').val();
	var stateName = $('#stateListId :selected').text();
	var year = $('#yearSelId :selected').text();
	var url="electionDetailsReportAction.action?electionId=${electionId}";

	//var url="electionDetailsReportAction.action?electionId=${electionId}&stateID="+stateId+"&stateName="+stateName+"&electionType=Assembly&electionTypeId=2&year="+year+"";

	window.open(url, '_blank');
   // window.focus();

}
</script>
<!--<a class="btn btn-primary" href="electionDetailsReportAction.action?electionId=38&stateID=1&stateName=AndhraPradesh&electionType=Assembly&electionTypeId=2&year=2009" >Click here to view 2009 election results </a>-->
 <!-- TOTAL CONSTITUENCIES INFORMATIONFOR PARLIAMENT  START-->
 <c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
 <a class="btn btn-primary completeDetailsBtn" href="javaScript:{showBlock();}" title="Click here for State wise Party Performances"> <b>Click here for State wise Party Performances</b></a>
  <a class="btn btn-primary closebutton" href="javaScript:{hideBlock();}" title="Hide more details"><b> Hide State Details</b></a>
  <a href="javaScript:{redirectToResultsPage();}" title="Click here to view  ${electionGoverningBodyVO.electionYear} Parliament Election Results" class="btn btn-primary"><b>Click here to View <s:property value="%{electionGoverningBodyVO.stateName}"/> ${electionGoverningBodyVO.electionYear} Parliament Election Results</b></a>
  <div  class="alert alert-info notesDiv"  style="margin-left:24px;margin-top:20px;">
	 <span><b>NOTE : </b> *  : total constituencies participated by party.</span><br>
	 <span style="margin-left:45px;"> ** : total no of constituencies won by party.</span>
  </div>
 <div class="constituencyInState row breadcrumb" style="width:999px;">
 <c:forEach var="district" items="${candidateMinistriesVO[0].stateConstituencyCountMap}">
<div  class="span2 breadcrumb">

	<h4>${district.key}<span class="pager"><a>${district.value.totalNoOfConstituencies}</a></span></h4>
	<dl class="dl-horizontal-cust">
	  <c:forEach var="party" items="${district.value.partyResultList}">
	  <dt> ${party.partyName}(${party.totalSeatsParticipated})*</dt> <dd class="badge badge-success">${party.totalSeatsOwn} </dd>**
	 </c:forEach>
	 	</dl>
	 </div>
</c:forEach>
</div> 
<a class="btn btn-primary closebutton" href="javaScript:{hideBlock();}"> Hide State Details</a>
</c:if>
  <!-- TOTAL CONSTITUENCIES INFORMATION FOR PARLIAMENT END-->
  <!-- TOTAL CONSTITUENCIES INFORMATIONFOR PARLIAMENT  START-->
 <!--<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
<a class="btn btn-primary" href="javaScript:{showBlock();}"> Click here for more details</a>

 <div class="constituencyInState" style="width:999px;">

	<c:forEach var="district" items="${candidateMinistriesVO[0].stateConstituencyCountMap}">
	<table  class="alert alert-success" width="24%" class="alert alert-success" style="height:152px;float:left;margin-right:10px;"><tr>
	 <td width="60%" style="border-right:1px solid #3A87AD;"> <b>${district.key} (${district.value.totalNoOfConstituencies})</b></td>
	 <td width="50%">	 	
		 <c:forEach var="party" items="${district.value.partyResultList}">
		 ${party.partyName} - ${party.totalSeatsOwn} <br>
		 </c:forEach>
	 
	 </td>

	</tr>
	</table>
	</c:forEach>
</div>
<a class="btn btn-primary closebutton" href="javaScript:{hideBlock();}"> Hide more details</a>

 </c:if>-->
  <!-- TOTAL CONSTITUENCIES INFORMATION FOR PARLIAMENT END-->
  <script>
  function showBlock(){
	  $('.constituencyInState').show('slow');
	  $('.closebutton').show('slow');
	  $('.notesDiv').show();
	  $('.completeDetailsBtn').hide();
  }
  function hideBlock(){
	   $('.constituencyInState').hide('slow');
	   $('.closebutton').hide('slow');
	   $('.notesDiv').hide();
	   $('.completeDetailsBtn').show();


  }
  </script>
<div id="DataTable" style="background:#ffffff;padding-left:70px;margin-top: 20px;clear:both;">
<!-- Chief Minister Details Div Start -->
<c:forEach var="ministerData" items="${candidateMinistriesVO}">
<c:if test="${ministerData.isChiefMinister == true}">
		<!--<div class="headingDiv"><span class="headingStyle">Chief Minister of <s:property value="%{electionGoverningBodyVO.stateName}"/></span></div>-->
		<h3 class="alert alert-info" style="padding:2px;text-align:center;width:95%;">Chief Minister of <s:property value="%{electionGoverningBodyVO.stateName}"/></h3>
	<div id="chiefMinisterDiv"  style="padding-left:0px;margin-bottom:10px;" >
		<table name="chiefMinisterDivTable"cellpadding="0" border="1" style="border-color:#d3d3d3;border-collapse:collapse;" width="95%">
			<tr>
				<td width="10%" style="border-right: solid 1px #FFFFFF;">
					<div style="padding: 10px 14px; margin-right: 8px" class="ministerDiv">
						<a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" title="Shri ${ministerData.candidateName} is the Chief Minister Of ${electionGoverningBodyVO.stateName}, Click here view ${ministerData.candidateName}'s Profile - News, Photos, Videos, Election Results">
						<img src="images/candidates/${ministerData.candidateName}.jpg" width="120px;" height="100px;"/></a>
					</div>
				</td>
			<td style="border-right: solid 1px #d3d3d3;" width="25%">
					<table>
						<tr>
							<td>
								<a href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" style="color:#8B4724;"><span style="font-weight:bold;font-size:12px;"  title="Shri ${ministerData.candidateName} is the Chief Minister Of ${electionGoverningBodyVO.stateName}, Click here view ${ministerData.candidateName}'s Profile - News, Photos, Videos, Election Results">${ministerData.candidateName}</span>
								</a>
							</td>
						 </tr>
						<tr>
							<td><span style="font-weight:bold;">Party &nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;: &nbsp&nbsp</span> 
								<span><a href="partyPageAction.action?partyId=${ministerData.partyId}"  style="color:#8B4724;" title="Click here to view ${ministerData.partyName} Party Profile - News, Election Results, Photos, Videos, Manifestoes">${ministerData.partyName}&nbsp;&nbsp </a></span>
								<a href="partyPageAction.action?partyId=${ministerData.partyId}">
								<span><img src="images/party_flags/${ministerData.partyName}.png" width="50px" height="30px"  title="Click here to view ${ministerData.partyName} Party Profile - News, Election Results, Photos, Videos, Manifestoes" alt="${ministerData.partyName} Party Flag"/></span></a>
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
								<th width="20%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><div>Portfolios</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;"><div>From Date</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;border-left:1px solid #d3d3d3;"><div>
								To Date
								</div></th>
							</tr>

							<c:forEach var="ministry" items="${ministerData.ministries}">
									<c:if test="${ministry.ministry != 'Chief Minister'}">
                            
                          <c:if test="${ministry.toDate == null}">
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
	<!--<div class="headingDiv"><span class="headingStyle">Prime Minister Of India</span></div>-->
     <h3 class="alert alert-info" style="padding:2px;text-align:center;width:95%;">Prime Minister of India</h3>
		<div id="primeMinisterDiv"  style="padding-left:0px;margin-bottom:10px;" >
		<table id="primeMinisterDivTable" cellpadding="0" border="1" style="border-color:#d3d3d3;border-collapse:collapse;" width="95%">
			<tr>
				<td width="10%" style="border-right: solid 1px #FFFFFF;">
					<div style="padding: 10px 14px; margin-right: 8px" class="ministerDiv">
						<a href="candidateElectionResultsAction.action?candidateId=${primeMinisterData.candidateId}"  title="Shri ${primeMinisterData.candidateName} is the Prime Minister Of India, Click here view ${primeMinisterData.candidateName}'s Profile - News, Photos, Videos, Election Results">
						<img src="images/candidates/${primeMinisterData.candidateName}.jpg" style="" width="120px;" height="100px;"/></a>
					</div>
				</td>
				<td style="border-right: solid 1px #d3d3d3;" width="25%">
					<table>
						<tr>
							<td>
								<a href="candidateElectionResultsAction.action?candidateId=${primeMinisterData.candidateId}" style="color:#8B4724;"  title="Shri ${primeMinisterData.candidateName} is the Prime Minister Of India, Click here view ${primeMinisterData.candidateName}'s Profile - News, Photos, Videos, Election Results"><span style="font-weight:bold;font-size:12px;">${primeMinisterData.candidateName}</span>
								</a>
							</td>
						 </tr>
						<tr>
							<td><span style="font-weight:bold;">Party &nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;: &nbsp&nbsp</span> 
								<span><a href="partyPageAction.action?partyId=${ministerData.partyId}"  style="color:#8B4724;" title="Click here to view ${primeMinisterData.partyName} Party Profile - News, Election Results, Photos, Videos, Manifestoes">${primeMinisterData.partyName}&nbsp;&nbsp </a></span>
								<a href="partyPageAction.action?partyId=${ministerData.partyId}">
								<span><img src="images/party_flags/${primeMinisterData.partyName}.png" width="50px" height="30px" alt="${primeMinisterData.partyName} Party Flag" title="Click here to view ${primeMinisterData.partyName} Party Profile - News, Election Results, Photos, Videos, Manifestoes"/></span></a><br>
								

								<c:if test="${primeMinisterData.candidateStateName != null}">
								<a href="statePageAction.action?stateId=${primeMinisterData.candidateConstiuencyId}" title="Click Here to View ${primeMinisterData.candidateConstiuencyName} Details">${primeMinisterData.candidateConstiuencyName}</a>,
								</c:if>

								<c:if test="${primeMinisterData.candidateStateName != null}">
								<a href="statePageAction.action?stateId=${primeMinisterData.candidateStateId}" title="Click Here to View ${primeMinisterData.candidateStateName} Details">${primeMinisterData.candidateStateName}</a>
								</c:if>


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
								<th width="20%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><div>Portfolios</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;"><div>From Date</div>
								</th>
								<th width="10%" style="border-bottom:1px solid #d3d3d3;border-left:1px solid #d3d3d3;"><div>
								To Date
								</div></th>
							</tr>
							<c:forEach var="ministry" items="${primeMinisterData.ministries}">
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
							</c:forEach>
				 </table>
			</td>
		</tr>
	 </table>
    </div> 
  </c:if>
  </c:forEach>
	<!-- Prime Minister Details Div End -->
	<!-- Prime Minister Details Div End -->
	<!-- Deputy Chief Minister Details Div Start -->
	<c:forEach var="deputyChiefMinisterData" items="${candidateMinistriesVO}">	
	<c:if test="${deputyChiefMinisterData.isDeputyChiefMinister == true}">
		<!--<div class="headingDiv"><span class="headingStyle">Deputy Chief Minister of <s:property value="%{electionGoverningBodyVO.stateName}"/></span></div>-->
		<h3 class="alert alert-info" style="padding:2px;text-align:center;width:95%;">Deputy Chief Minister of <s:property value="%{electionGoverningBodyVO.stateName}"/></h3>		
		<div id="deputyChiefMinisterDiv" style="margin-bottom:10px;">
			<table id="deputyChiefMinisterDivTable" border="1" width="95%" style="border-collapse:collapse;border-color:#d3d3d3;" cellpadding="0">
				<tr>
					<td width="10%" style="border-right:1px solid #FFFFFF;">
					<div style="padding: 10px 14px; margin-right: 8px;" class="ministerDiv"><a href="candidateElectionResultsAction.action?candidateId=${deputyChiefMinisterData.candidateId}"  title="Shri ${deputyChiefMinisterData.candidateName} is the Deputy Chief Minister Of ${electionGoverningBodyVO.stateName}, Click here view ${deputyChiefMinisterData.candidateName}'s Profile - News, Photos, Videos, Election Results"><img src="images/candidates/${deputyChiefMinisterData.candidateName}.jpg" style="" width="120px;" height="100px;"/></a></div>
					</td>
					<td width="25%" style="border-right:1px solid #d3d3d3;">
						<table>
							<tr>
								<td><span><a href="candidateElectionResultsAction.action?candidateId=${deputyChiefMinisterData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:12px;"  title="Shri ${deputyChiefMinisterData.candidateName} is the Deputy Chief Minister Of ${electionGoverningBodyVO.stateName}, Click here view ${deputyChiefMinisterData.candidateName}'s Profile - News, Photos, Videos, Election Results">${deputyChiefMinisterData.candidateName}</a></span></td>
								</tr>
								<tr>
								<td>
									<span style="font-weight:bold;">Party : </span>
									<span><a href="partyPageAction.action?partyId=${deputyChiefMinisterData.partyId}" style="color:#8B4724;" title="Click here to view ${deputyChiefMinisterData.partyName} Party Profile - News, Election Results, Photos, Videos, Manifestoes" >${deputyChiefMinisterData.partyName}</a></span>
									<span><a href="partyPageAction.action?partyId=${deputyChiefMinisterData.partyId}"><img src="images/party_flags/${deputyChiefMinisterData.partyName}.png" width="50px" height="30px"  title="Click here to view ${deputyChiefMinisterData.partyName} Party Profile - News, Election Results, Photos, Videos, Manifestoes" alt="${deputyChiefMinisterData.partyName} Party Flag"/></a></span>

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
							<th width="24%" style="border-bottom:1px solid #d3d3d3;border-right:1px solid #d3d3d3;"><span style="font-weight:bold; text-align: center;">Portfolios</span></th>
							<th style="font-weight:bold;border-bottom:1px solid #d3d3d3; text-align: center;border-right:1px solid #d3d3d3;" width="15%">From Date</th>
							<th width="15%" style="border-bottom:1px solid #d3d3d3;text-align:center;"><span style="font-weight:bold;">To Date</span></th>
						</tr>
							<c:forEach var="ministry" items="${deputyChiefMinisterData.ministries}">
							<c:if test="${ministry.ministry != 'Deputy Chief Minister'}">
							<c:if test="${ministry.toDate == null}">
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
		<!--<div class="headingDiv"><span class="headingStyle">Cabinet Minister of <s:property value="%{electionGoverningBodyVO.stateName}" /></span></div>-->
       <div id="cabinetMinisterDiv" style="background-color:#f1f1f1;;width:95%;"><h3 class="alert alert-info" style="padding:2px;text-align:center;width:95%;">Cabinet Minister of <s:property value="%{electionGoverningBodyVO.stateName}" /></h3>
		</c:if>
		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
		<!--<div class="headingDiv"><span class="headingStyle">Cabinet Ministers of India </span></div>-->
		<h3 class="alert alert-info" style="padding:1px;text-align:center;width:95%;">Cabinet Ministers of Manmohan Singh  </h3>
		</c:if>
	</div>
		<div id="DataTable"  class="cabinetMinistersDiv" style="background:#ffffff;margin-bottom:10px;">
			<table id="CabinetMinistersTable" border="1"  style="border-collapse:collapse; margin-top: 9px; width: 95%; text-align:center;border-right:none;">
			<tr>
				<th width="20%" style="height:30px;">Candidate Name</th>
				<th width="15%">Party</th>
				<th>

					<table width="100%">
					<tr>

					<th width="30%">Portfolios</th>
					<th width="20%">From Date</th>
					<th width="20%">To Date</th>
					</tr>

					</table>

				<th>
			</tr>
	</c:if>
	<c:forEach var="ministerData" varStatus="stat" items="${candidateMinistriesVO}">
	 <c:if test="${ministerData.expisreAllMinistry != 'true'}">	
		<c:if test="${!ministerData.isDeputyChiefMinister && !ministerData.isChiefMinister &&  !ministerData.isPrimeMinister}">
		
		<c:forEach var="cabinetMinister" items="${ministerData.ministryTypes}">
		<c:if test="${cabinetMinister == 'Cabinet Minister'}">
		<tr>
		<td  width="20%"><b><div id="cabinetMinisterDiv${stat.index}" class="ministerDiv"><a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;" title="Click here to view ${ministerData.candidateName}'s Profile - News, Election Results, photos, Videos"><img src="images/candidates/${ministerData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerData.candidateName}</a></div></b>
			</td>
			<td  width="20%"><b><a href="partyPageAction.action?partyId=${ministerData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;" title="Click here to view ${ministerData.partyName} Party Profile - Election Results, Voting Trenz, News, Photos, Videos, Manifesteos"><img src="images/party_flags/${ministerData.partyName}.png" width="80px" height="60px"/><br> ${ministerData.partyName}</a><br>
			 <a href="constituencyPageAction.action?constituencyId=${ministerData.candidateConstiuencyId}" title="Click Here to View ${ministerData.candidateConstiuencyName} Constituency Details">${ministerData.candidateConstiuencyName}</a>,
		  <c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">
			<a href="statePageAction.action?stateId=${ministerData.candidateStateId}" title="Click Here to View ${ministerData.candidateStateName} Details">${ministerData.candidateStateName}</a>
		  </c:if>
		  <c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
		  <a href="districtPageAction.action?districtId=${ministerData.candidateDistrictId}&districtName=${ministerData.candidateDistrictName}" title="Click Here to View ${ministerData.candidateDistrictName}  District Details">${ministerData.candidateDistrictName}</a>
		  </c:if>
		 </b>
		</b>
			</td>
			<td width="60%">
			<table class="cabinetMinistersTable">
			<c:forEach var="ministry" varStatus="stat" items="${ministerData.ministries}">
			<c:if test="${ministry.toDate == null && ministry.ministerType == 'Cabinet Minister'}">
            <tr>			
			<td style="text-align:left;" width="22%"><b> ${ministry.ministry}</b></td>
			<td width="10%"><b>${ministry.fromDate}</b></td>
			<td width="10%"><b><c:if test="${ministry.toDate == null}">Working</c:if></b></td>			
		    </tr>			
		    </c:if>
		    </c:forEach>
		   </table>
		   </td>
		</tr>
		</c:if>
	</c:forEach>
	</c:if>
	</c:if>
	</c:forEach>
	</table>
	</div>
<!-- Cabinet Minister Div End -->
	<!-- Ministers of State with Independent Charge Div Start -->
	<c:if test="${candidateMinistriesVO[0].hasMSIC}">
		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
			<div class="headingDiv" id="ministersofDiv"><span class="headingStyle">Ministers of State(with Independent Charge) of <s:property value="%{electionGoverningBodyVO.stateName}" /></span>
		</c:if>
		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
			<!--<div class="headingDiv"><span class="headingStyle">Ministers of State (Independent Charge) of India</span></div>-->
			<h3 class="alert alert-info" style="padding:1px;text-align:center;width:95%;">Ministers of State (with Independent Charge) in Manmohan Singh Cabinet</h3>
		</c:if>
</div>
		<div id="DataTable" class="independentMinistersDiv" style="background:#ffffff;">
		<table id="ministersofStateTable" border="1" style="border-collapse: collapse; margin-top: 9px; width: 95%; text-align:center;margin-bottom:10px;">
		<tr>
			<th width="20%" style="height:30px;">Candidate Name</th>
			<th width="15%">Party</th>
			<th>
			<table width="100%">
					<tr>

					<th width="30%">Portfolios</th>
					<th width="20%">From Date</th>
					<th width="20%">To Date</th>
					</tr>

			</table>
			</th>


		</tr>
	
	<c:forEach var="ministerOfStateIndData" varStatus="stat" items="${candidateMinistriesVO}">

	 <c:if test="${ministerOfStateIndData.expisreAllMinistry != 'true'}">	

		<c:if test="${!ministerOfStateIndData.isDeputyChiefMinister && !ministerOfStateIndData.isChiefMinister}">
		<c:forEach var="ministerOfStateInd" items="${ministerOfStateIndData.ministryTypes}">
		
		<c:if test="${ministerOfStateInd == 'Ministers of State with Independent Charge'}">
		<tr>

			<td width="20%"><b>
			<div id="ministerOfStateDiv${stat.index}" class="ministerDiv">
			<a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerOfStateIndData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;" title="Click here to view ${ministerOfStateIndData.candidateName}'s Profile - News, Election Results, photos, Videos">
			<img src="images/candidates/${ministerOfStateIndData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerOfStateIndData.candidateName}</a>
			</div></b>
			</td>

			<td  width="20%"><b>
			<a href="partyPageAction.action?partyId=${ministerOfStateIndData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;" title="Click here to view ${ministerOfStateIndData.partyName} Party Profile - Election Results, Voting Trenz, News, Photos, Videos, Manifesteos">
			<img src="images/party_flags/${ministerOfStateIndData.partyName}.png" width="80px" height="60px"/><br>${ministerOfStateIndData.partyName}</a><br>


            <a href="constituencyPageAction.action?constituencyId=${ministerOfStateIndData.candidateConstiuencyId}" title="Click Here to View ${ministerOfStateIndData.candidateConstiuencyName} Constituency Details">${ministerOfStateIndData.candidateConstiuencyName}</a>		,	
			

			<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">
			<a href="statePageAction.action?stateId=${ministerOfStateIndData.candidateStateId}" title="Click Here to View ${ministerOfStateIndData.candidateStateName} Details">${ministerOfStateIndData.candidateStateName}</a>
			</c:if>

			<c:if test="${electionGoverningBodyVO.electionType== 'Assembly'}">
			<a href="districtPageAction.action?districtId=${ministerOfStateIndData.candidateDistrictId}&districtName=${ministerOfStateIndData.candidateDistrictName}" title="Click Here to View ${ministerOfStateIndData.candidateDistrictName} District Details">${ministerOfStateIndData.candidateDistrictName}</a>
			</c:if>

			
			</b>
			</td>

			<td width="60%">
			 <table class="independentMinistersTable">

				<c:forEach var="ministry" varStatus="stat" items="${ministerOfStateIndData.ministries}">
			    <c:if test="${ministry.toDate == null && ministry.ministerType == 'Ministers of State with Independent Charge'}">
					<tr>
					<td style="text-align:left;" width="22%"><b>${ministry.ministry}</b></td>
					<td width="10%"><b>${ministry.fromDate}</b></td>
					<td width="10%"><b>	<c:if test="${ministry.toDate == null}">Working</c:if></b></td>	
					</tr>
				</c:if>
				</c:forEach>
			</table>
			</td>

		</tr>
			
		</c:if>
		</c:forEach>
		</c:if>
		</c:if>
	</c:forEach>
	</table>
	</div>
	</c:if>

	<!-- Ministers of State with Independent Charge Div End -->


	<!-- Ministers of State Div Start -->

	<c:if test="${candidateMinistriesVO[0].hasMS}">
	
		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
			<!--<div class="headingDiv" style=" padding-left: 187px;">
				<span class="headingStyle">Ministers of State of <s:property value="%{electionGoverningBodyVO.stateName}" /></span>
			</div>-->
			<div id="ministersOfStateMainDiv" style="background-color:#f1f1f1;;width:95%;"><h3  class="alert alert-info" style="padding:1px;text-align:center;">Ministers of State of <s:property value="%{electionGoverningBodyVO.stateName}" /></h3>
		</c:if>

		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
			<!--<div class="headingDiv" style=" padding-left: 187px;">
				<span class="headingStyle">Ministers of State of India</span>
			</div>-->
			<h3  class="alert alert-info" style="padding:1px;text-align:center;width:95%;">Ministers of State in Manmohan Singh Cabinet</h3>


		</c:if>
	</div>
		
	<div id="ministersofStateDiv" style="margin-bottom:10px;" class="ministersOfStateResigned">
			<table id="ministersofStateDivTable" border="1"  style="border-collapse: collapse; margin-top: 9px; width: 95%;text-align:center;">
			<tr>
				<th width="20%" style="height:30px;">Candidate Name</th>
				<th width="10%">Party</th>

				<th>
			    <table width="100%">
					<tr>

					<th width="30%">Portfolios</th>
					<th width="20%">From Date</th>
					<th width="20%">To Date</th>
					</tr>

			   </table>
			   </th>
				
			</tr>
					
		<c:forEach var="ministerOfStateData" varStatus="stat" items="${candidateMinistriesVO}">

		 <c:if test="${ministerOfStateData.expireOneMinistry == 'true'}">	

		<c:if test="${!ministerOfStateData.isDeputyChiefMinister && !ministerOfStateData.isChiefMinister}">
			<c:forEach var="ministersOfState" items="${ministerOfStateData.ministryTypes}" varStatus="loop">
				
			<c:if test="${ministersOfState == 'Ministers of State'}">
			<tr>

				<td  width="20%"><b>
				<div id="ministeOfState${stat.index}" class="ministerDiv">
				<a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerOfStateData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;" title="Click here to view ${ministerOfStateData.candidateName}'s Profile - News, Election Results, photos, Videos">
				<img src="images/candidates/${ministerOfStateData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerOfStateData.candidateName}</a></div></b>
				</td>

				<td  width="20%"><b><a href="partyPageAction.action?partyId=${ministerOfStateData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;" title="Click here to view ${ministerOfStateData.partyName} Party Profile - Election Results, Voting Trenz, News, Photos, Videos, Manifesteos">
				<img src="images/party_flags/${ministerOfStateData.partyName}.png" width="80px" height="60px"/><br>${ministerOfStateData.partyName}</a><br>



				<a href="constituencyPageAction.action?constituencyId=${ministerOfStateData.candidateConstiuencyId}" title="Click Here to View ${ministerOfStateData.candidateConstiuencyName} Constituency Details">${ministerOfStateData.candidateConstiuencyName}</a>,
				
				
				<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">

				<a href="statePageAction.action?stateId=${ministerOfStateData.candidateStateId}" title="Click Here to View ${ministerOfStateData.candidateStateName} Details">${ministerOfStateData.candidateStateName}</a>

			    </c:if>

			    <c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">

				<a href="districtPageAction.action?districtId=${ministerOfStateData.candidateDistrictId}&districtName=${ministerOfStateData.candidateDistrictName}" title="Click Here to View ${ministerOfStateData.candidateDistrictName} District Details" >${ministerOfStateData.candidateDistrictName}</a>
			    </c:if>
				
				</b>
				</td>

                <td width="60%">
				<table class="resignedMinistersOfStateTable">

				<c:forEach var="ministry" varStatus="stat" items="${ministerOfStateData.ministries}">
				<c:if test="${ministry.toDate != null}">
				<tr>
				<td style="text-align:left;" width="30%"><b>${ministry.ministry}</b></td>
				<td width="10%"><b>${ministry.fromDate}</b></td>
				<td width="10%"><b><c:if test="${ministry.toDate != null}">${ministry.toDate}</c:if></b></td>
				</tr>
				</c:if>			
				</c:forEach>
				
				</table>
				</td>
		
			</c:if>
		</c:forEach>
		</c:if>
		</c:if>
		</c:forEach>
		</table>
	</div>
</c:if>
<!-- Ministers of State Div End -->

  <!-- Cabinet Resigned Ministries START-->

	
	<c:if test="${candidateMinistriesVO[0].hasCabinetMinisters}">
      <!--<div id="cabinetResignedDiv" style="background-color:#f1f1f1;"><h3 class="alert alert-error" style="padding:1px;text-align:center;">Resigned Cabinet Ministries of Manmohan Singh Cabinet</h3></div>-->
		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
		<div id="cabinetResignedDiv" style="background-color:#f1f1f1;;width:95%;"><h3 class="alert alert-error" style="padding:1px;text-align:center;">Ex Cabinet Minister of <s:property value="%{electionGoverningBodyVO.stateName}"/></h3></div>
		<!--<div class="headingDiv"><span class="headingStyle">Resigned Cabinet Minister of <s:property value="%{electionGoverningBodyVO.stateName}" /></span></div>-->
		</c:if>

		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
		<div id="cabinetResignedDiv" style="background-color:#f1f1f1;width:95%;"><h3 class="alert alert-error" style="padding:1px;text-align:center">Ex Cabinet Ministers in Manmohan Singh Cabinet</h3></div>
		<!--<div class="headingDiv"><span class="headingStyle">Resigned Cabinet Ministers in Manmohan Singh Cabinet </span></div>-->
		</c:if>

		<div id="DataTable" style="background:#ffffff;margin-bottom:10px;" class="cabinetResignedMinistersDiv">
			<table id="cabinetResignedDivTable" border="1" id="cabinetResigned" style="border-collapse:collapse; margin-top: 9px; width: 95%; text-align:center;">
			<tr>
				<th width="20%" style="height:30px;">Candidate Name</th>
				<th width="15%">Party</th>

			    <th>
			     <table width="100%">
					<tr>

					<th width="30%">Portfolios</th>
					<th width="20%">From Date</th>
					<th width="20%">To Date</th>
					</tr>

			   </table>
			 </th>

			</tr>
	</c:if>

	<c:forEach var="ministerData" varStatus="stat" items="${candidateMinistriesVO}">
	 <c:if test="${ministerData.expireOneMinistry == 'true'}">	
		
		<c:forEach var="cabinetMinister" items="${ministerData.ministryTypes}">
		<c:if test="${cabinetMinister == 'Cabinet Minister'}">

		
		<tr>

			<td  width="20%"><b><div id="cabinetMinisterDiv${stat.index}" class="ministerDiv"><a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;" title="Click here to view ${ministerData.candidateName}'s Profile - News, Election Results, photos, Videos"><img src="images/candidates/${ministerData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerData.candidateName}</a></div></b>

			</td>

			<td  width="20%"><b><a href="partyPageAction.action?partyId=${ministerData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;" title="Click here to view ${ministerData.partyName} Party Profile - Election Results, Voting Trenz, News, Photos, Videos, Manifesteos"><img src="images/party_flags/${ministerData.partyName}.png" width="80px" height="60px"/><br> ${ministerData.partyName}</a><br>

             <a href="constituencyPageAction.action?constituencyId=${ministerData.candidateConstiuencyId}" title="Click Here to View ${ministerData.candidateConstiuencyName} Constituency Details">${ministerData.candidateConstiuencyName}</a>,

			<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">
			<a href="statePageAction.action?stateId=${ministerData.candidateStateId}" title="Click Here to View ${ministerData.candidateStateName} Details">${ministerData.candidateStateName}</a>
			</c:if>

			<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
			<a href="districtPageAction.action?districtId=${ministerData.candidateDistrictId}&districtName=${ministerData.candidateDistrictName}" title="Click Here to View ${ministerData.candidateDistrictName} District Details">${ministerData.candidateDistrictName}</a>
			</c:if>
			
			</b>
			</td>

			<td width="60%">
			<table class="cabinetResignedMinistersTable">

			<c:forEach var="ministry" varStatus="stat" items="${ministerData.ministries}">
			<c:if test="${ministry.toDate != null && ministry.ministerType == 'Cabinet Minister'}">
			<tr>
			<td style="text-align:left;" width="30%"><b> ${ministry.ministry}</b></td>
			<td width="10%"><b>${ministry.fromDate}</b></td>
			<td width="10%"><b><c:if test="${ministry.toDate != null}">${ministry.toDate}</c:if></b></td>
			</tr>
			</c:if>
			</c:forEach>

			</table>            
			</td>

		</tr>
		</c:if>
	</c:forEach>

	</c:if>
	</c:forEach>
	</table>
	</div>

	    <!-- Cabinet Resigned Ministries END-->

<!-- Ministers of State with Independent Charge Div Start Resigned -->

	<c:if test="${candidateMinistriesVO[0].hasMSIC}">
        
		<div id="independentResignedDiv" style="background-color:#f1f1f1;width:95%;" >
			<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
				<h3 class="alert alert-error" style="padding:1px;text-align:center;">Ex Ministries of State(with Independent Charge) of <s:property value="%{electionGoverningBodyVO.stateName}" /></h3>
			</c:if>

			<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
				<h3 class="alert alert-error" style="padding:1px;text-align:center;">Ex Ministries  of State ( with Independent Charge) of Manmohan Singh Cabinet</h3>
			</c:if>
		</div>

		<div id="DataTable" style="background:#ffffff;" class="resignedIndependentMinistersDiv">
		
		<table id="independentResigned" border="1" style="border-collapse: collapse; margin-top: 9px; width: 95%; text-align:center;margin-bottom:10px;" >
		<tr>
			<th width="20%" style="height:30px;">Candidate Name</th>
			<th width="15%">Party</th>
			<th>
			<table width="100%">
					<tr>

					<th width="30%">Portfolios</th>
					<th width="20%">From Date</th>
					<th width="20%">To Date</th>
					</tr>

			</table>
			</th>
		
		</tr>
	
	<c:forEach var="ministerOfStateIndData" varStatus="stat" items="${candidateMinistriesVO}">

	<c:if test="${ministerOfStateIndData.expireOneMinistry == 'true'}">
		<c:if test="${!ministerOfStateIndData.isDeputyChiefMinister && !ministerOfStateIndData.isChiefMinister}">
		<c:forEach var="ministerOfStateInd" items="${ministerOfStateIndData.ministryTypes}">
		
		<c:if test="${ministerOfStateInd == 'Ministers of State with Independent Charge'}">
		<tr>

			<td  width="20%"><b>
			<div id="ministerOfStateDiv${stat.index}" class="ministerDiv">
			<a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerOfStateIndData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;" title="Click here to view ${ministerOfStateIndData.candidateName}'s Profile - News, Election Results, photos, Videos">
			<img src="images/candidates/${ministerOfStateIndData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerOfStateIndData.candidateName}</a>
			</div></b>
			</td>

			<td  width="20%"><b>
			<a href="partyPageAction.action?partyId=${ministerOfStateIndData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;" title="Click here to view ${ministerOfStateIndData.partyName} Party Profile - Election Results, Voting Trenz, News, Photos, Videos, Manifesteos">
			<img src="images/party_flags/${ministerOfStateIndData.partyName}.png" width="80px" height="60px"/><br>${ministerOfStateIndData.partyName}</a>



             <a href="constituencyPageAction.action?constituencyId=${ministerOfStateIndData.candidateConstiuencyId}" title="Click Here to View ${ministerOfStateIndData.candidateConstiuencyName} Constituency Details">${ministerOfStateIndData.candidateConstiuencyName}</a>,

			<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">
				<a href="statePageAction.action?stateId=${ministerOfStateIndData.candidateStateId}" title="Click Here to View ${ministerOfStateIndData.candidateStateName} Details">${ministerOfStateIndData.candidateStateName}</a>
			</c:if>

			<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
				<a href="districtPageAction.action?districtId=${ministerOfStateIndData.candidateDistrictId}&districtName=${ministerOfStateIndData.candidateDistrictName}" title="Click Here to View ${ministerOfStateIndData.candidateDistrictName} District Details">${ministerOfStateIndData.candidateDistrictName}</a>
			</c:if>
			</b>
			</td>
			<td width="60%">
			 <table class="resignedIndependentMinistersTable">

			<c:forEach var="ministry" varStatus="stat" items="${ministerOfStateIndData.ministries}">			
				<c:if test="${ministry.toDate != null && ministry.ministerType == 'Ministers of State with Independent Charge'}">
				<tr>
				<td style="text-align:left;" width="30%"><b>${ministry.ministry}</b></td>
				<td width="10%"><b>${ministry.fromDate}</b></td>
				<td width="10%"><b><c:if test="${ministry.toDate != null}">${ministry.toDate}</c:if></b></td>
				</tr>
			    </c:if>				
		   </c:forEach>

		   </table>
		   </td>

			</tr>
		</c:if>
		</c:forEach>
		</c:if>

		</c:if>
	</c:forEach>
	</table>
	</div>
	</c:if>

	<!-- Ministers of State with Independent Charge Div End Resigned -->


<!-- Ministers of State Div Start  Resigned-->

	<c:if test="${candidateMinistriesVO[0].hasMS}">

	    <div id="ministersOfStateResignedDiv" style="background-color:#f1f1f1;width:95%;" class="resignedMinistersOfState">

		<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
			<h3 class="alert alert-error" style="padding:1px;text-align:center;">Ex Ministries of State of <s:property value="%{electionGoverningBodyVO.stateName}" /></h3>
		</c:if>

		<c:if test="${electionGoverningBodyVO.electionType == 'Parliament'}">
			<h3 class="alert alert-error" style="padding:1px;text-align:center;">Ex Ministries of State in Manmohan Singh Cabinet</h3>
		</c:if>

		</div>

		
		<div id="ministersofStateDiv" style="margin-bottom:10px;" class="ministersOfStateResigned">
			<table id="ministersofTable" border="1"  style="border-collapse: collapse; margin-top: 9px; width: 95%;text-align:center;">
			<tr>
				<th width="20%" style="height:30px;">Candidate Name</th>
				<th width="10%">Party</th>

				<th>
			    <table width="100%">
					<tr>

					<th width="30%">Portfolios</th>
					<th width="20%">From Date</th>
					<th width="20%">To Date</th>
					</tr>

			   </table>
			   </th>
				
			</tr>
					
		<c:forEach var="ministerOfStateData" varStatus="stat" items="${candidateMinistriesVO}">

		 <c:if test="${ministerOfStateData.expireOneMinistry == 'true'}">	

		<c:if test="${!ministerOfStateData.isDeputyChiefMinister && !ministerOfStateData.isChiefMinister}">
			<c:forEach var="ministersOfState" items="${ministerOfStateData.ministryTypes}" varStatus="loop">
				
			<c:if test="${ministersOfState == 'Ministers of State'}">
			<tr>

				<td  width="20%"><b>
				<div id="ministeOfState${stat.index}" class="ministerDiv">
				<a id="anchor${stat.index}" href="candidateElectionResultsAction.action?candidateId=${ministerOfStateData.candidateId}" style="color:#8B4724;font-weight:bold;font-size:11px;" title="Click here to view ${ministerOfStateData.candidateName}'s Profile - News, Election Results, photos, Videos">
				<img src="images/candidates/${ministerOfStateData.candidateName}.jpg" width="113px" height="85px" style="margin-top:10px;"/><br> ${ministerOfStateData.candidateName}</a></div></b>
				</td>

				<td  width="20%"><b><a href="partyPageAction.action?partyId=${ministerOfStateData.partyId}" style="color:#8B4724;font-weight:bold;font-size:12px;" title="Click here to view ${ministerOfStateData.partyName} Party Profile - Election Results, Voting Trenz, News, Photos, Videos, Manifesteos">
				<img src="images/party_flags/${ministerOfStateData.partyName}.png" width="80px" height="60px"/><br>${ministerOfStateData.partyName}</a><br>



				<a href="constituencyPageAction.action?constituencyId=${ministerOfStateData.candidateConstiuencyId}" title="Click Here to View ${ministerOfStateData.candidateConstiuencyName} Constituency Details">${ministerOfStateData.candidateConstiuencyName}</a>,
				
				
				<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">

				<a href="statePageAction.action?stateId=${ministerOfStateData.candidateStateId}" title="Click Here to View ${ministerOfStateData.candidateStateName} Details">${ministerOfStateData.candidateStateName}</a>

			    </c:if>

			    <c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">

				<a href="districtPageAction.action?districtId=${ministerOfStateData.candidateDistrictId}&districtName=${ministerOfStateData.candidateDistrictName}" title="Click Here to View ${ministerOfStateData.candidateDistrictName} District Details" >${ministerOfStateData.candidateDistrictName}</a>
			    </c:if>
				
				</b>
				</td>

                <td width="60%">
				<table class="resignedMinistersOfStateTable">

				<c:forEach var="ministry" varStatus="stat" items="${ministerOfStateData.ministries}">
				<c:if test="${ministry.toDate != null}">
				<tr>
				<td style="text-align:left;" width="30%"><b>${ministry.ministry}</b></td>
				<td width="10%"><b>${ministry.fromDate}</b></td>
				<td width="10%"><b><c:if test="${ministry.toDate != null}">${ministry.toDate}</c:if></b></td>
				</tr>
				</c:if>			
				</c:forEach>
				
				</table>
				</td>
		
			</c:if>
		</c:forEach>
		</c:if>
		</c:if>
		</c:forEach>
		</table>
	</div>
</c:if>
<!-- Ministers of State Div End Resigned -->
	
</div>

</div>
<script type="text/javascript">

	getStatesForAssembly();
	$("#impCandPerf").removeClass("dashBoardtabsDivSelected");
    $("#minisPerf").addClass("dashBoardtabsDivSelected");
	showHidsState();

	if('${electionGoverningBodyVO.electionType}' == 'Parliament')
	{
		$('.stateLabel').hide();
		$('#stateListId').hide();
	}


function hideStatesDiv(){

	$('#stateListId').hide();
	$('.stateLabel').hide();

}

function showStatesDiv(){

	$('#stateListId').show();
	$('.stateLabel').show();

}

</script>
</body>
</html>
