<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> --%>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
 <script type="text/javascript" src="js/validationTools/validationTools.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">


<title>Voters Validation Tool</title>

<style type="text/css">
.mainDiv{margin-left:auto;margin-right:auto;width:980px;}
.requiredFont{
	color:red;
	font-size:13px;
}
#sublevel{margin-top:10px;}
#sublevel th{background:#CDE6FC;}
.widget h4 {font-family: arial;color:#000;}
.f2 {margin-right: 15px;}
#voterInfoHeading{margin-bottom: 15px;}
#votersBasicInfoInnerDiv,#panchayatMappingDiv{padding-bottom: 27px;}
#mandalTab{
    border: 1px solid #D3D3D3;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    padding: 10px;
    width: 95%;
}
.voterInfoDiv{margin-top: 10px;}

#votersBasicInfoInnerDiv table th ,#votersInfoDiv table th,#voterFamilyInfoDiv table th,#voterAgeInfoDiv table th,#voterModificationDiv table th,#constituencyVotesTab th,#mandalTab th,#panchayatMappingInnerDiv table th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#votersBasicInfoInnerDiv table td ,#votersInfoDiv table td,#voterFamilyInfoDiv table td,#voterAgeInfoDiv table td,#voterModificationDiv table td,#mandalTab td,#constituencyVotesTab td,#panchayatMappingInnerDiv table td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}

.headingSpan{font-size: 14px;margin-left: 23px;}
#votersBasicInfo{padding-bottom: 10px;}
#errorMsgDiv,#panchayatErrorMsgDiv {
    font-size: 13px;
    margin-top: 10px;
    text-align: center;
}
#electionResultsDiv{padding-bottom: 25px;}
#electionTypeDiv{padding-top: 14px;}
#eleErrorMsgDiv{color:red;}
.tdCls{vertical-align: top;}
.constituencyDiv{max-height: 300px;
    overflow-y: scroll;}
#constituencyVotesTab{ margin-left: 23px;margin-top: 15px;width: 95%;}
#electionTypeId{margin-left: 45px;}
#stateId{margin-left: 51px;}
#electionYear{margin-left: 6px;}
#eleBtnId{margin-left: 75px; margin-bottom: 21px; margin-top: 9px;}
#eleResAjaxImg{margin-left: 10px;margin-top: -10px;}
#boothResDiv{margin-top: 24px;}
.paraCls{color: #676A67;font: small-caption;margin-left: 25px;}

#conTotalVotesDiv > span,#conValidVotesDiv > span {
    line-height: 1.8em;
}
#panchayatSelectDiv{margin-top: 15px;}
#panchayatMappingInnerDiv{margin-top: 25px;}
#panchayatErrorMsgDiv{color:red;}
</style>
</head>

<body>

<div class="mainDiv">
<div class="widget blue">

<div id="errorMsgDiv"></div>
<div id="ConstituencyDiv" class="selectDiv" style="margin-top:10px;">
	 Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDate();"/> &nbsp;&nbsp;

		
	 Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
	
	
	 
	</div>
<div id="ajaxImgId" style="display:none;margin-right:427px;float:right;"><img src="./images/icons/search.gif" alt="Processing Image"/></div>

<div style="margin-top:10px;margin-bottom:10px;text-align: center;clear:both;">
<input type="button" style="position: absolute:top: 50%;" value="Get Hamlet Assigned Info" class="btn btn-success" onclick="getSubLevelInfo();" />

<input type="button" value="check voters Data" id="votersBasicInfoId" class="btn btn-success"/>

</div>
<br>
</div>
<div class="widget blue" id="subLevelDataId" style="display:none;">
<div id="subLevelInfo" style="overflow-x:scroll;">
</div>
</div>
 <div id="votersBasicInfo" class="widget blue" style="display:none;">
  <span id="hideAndShow"><a href="javascript:{}" class="btn pull-right" id="hideMenu">Hide<i class="icon-chevron-up"></i></a></span>
  <h4 id="voterInfoHeading"></h4>
  <div id="votersBasicInnerDiv">
   <div id="votersBasicInfoInnerDiv"></div>
   <div id="votersInfoDiv"></div>
   <div id="voterFamilyInfoDiv"></div>
   <div id="voterAgeInfoDiv"></div>
   <div id="voterModificationDiv"></div>
   <div id="voterModificationAgeDiv"></div>
   </div>
 </div>


 <!-- election Results -->
	<div id="electionResultsDiv" class="widget blue">
	<h4>Election Results Validation</h4>
	<div id="eleErrorMsgDiv"></div>
	  <div id="electionTypeDiv">

		<p> Election Type : <select id="electionTypeId">
		  <option value="0">Select Type</option>
		  <option value="2">Assembly</option>
		  <option value="1">Parliament</option>
	    </select></p>

		<p id="statesDiv">
		 Select State : <select id="stateId"></select>
		</p>
		<p>Select Election Year : <select id="electionYear"></select></p>

		<input type="button" value="Election validation" id="eleBtnId" class="btn btn-info"/>
	    <img src="./images/icons/search.gif" id="eleResAjaxImg" style="display:none;"/>
	  </div>
	  <div id="constResDiv">
	    <h4 id="conEleHeading" style="display:none;"></h4>
		<div id="constResMainDiv"></div>
	  </div>
	 <div id="boothResDiv">
		<h4 id="boothEleHeading" style="display:none;"></h4>
		<div id="boothResMainDiv"></div>
	</div>
	</div>

 <!-- election results -->

<!-- Panchayat Mapping -->
 <div id="panchayatMappingDiv" class="widget blue">
   <h4>Panchayat Mapping Validation</h4>
    <div id="panchayatErrorMsgDiv"></div>
   <div id="panchayatSelectDiv" class="selectDiv">
     Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList1" list="constituencyList" listKey="id" listValue="name" onchange="buildPublicationDates();"/> &nbsp;&nbsp;

	 Publication Date<font class="requiredFont">*</font> <select id="publicationList" class="selectWidth" style="width:172px;height:25px;" name="publicationList" >
		</select>
	
	<input type="button" value="Panchayat Mapping Validation" class="btn btn-info" id="panchayatMappingId"/><img style="display:none;" id="ajaxImg" src="./images/icons/search.gif" alt="Processing Image"/>
	 
	</div>
   	<div id="panchayatMappingInnerDiv"></div>
 </div>

 <!-- Panchayat Mapping End -->
</div>

<script type="text/javascript">

function getSubLevelInfo()
{
$("#ajaxImgId").show();
$("#subLevelDataId").show();
var constituencyId = $("#constituencyList").val();
var publicationDateId = $("#publicationDateList").val();
var jsObj=
		{		
			constituencyId:constituencyId,
			publicationDateId:publicationDateId,
            task:"getSubLevelInfo"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSubLevelInfoAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function callAjax(jsObj,url)
{
 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getSubLevelInfo")
								{
								buildData(myResults);	
								}
								else if(jsObj.task == "getPublicationDate")
								{
								
									buildPublicationDateList(myResults);
									buildPublicationDateListForPanchayat(myResults);

								}

								else if(jsObj.task == "checkVotersBasicInfo")
								{

								 showVotersBasicInfo(myResults,jsObj);
								 showVoterInfoData(myResults,jsObj);
								 showVoterFamilyData(myResults,jsObj);
								 showVoterAgeData(myResults,jsObj);
								 showVoterModificationData(myResults,jsObj);
								 showVoterModificationAgeData(myResults,jsObj);
								}
								 else if(jsObj.task == "getStates")
								  buildStates(myResults);
								
								else if(jsObj.task == 'getElectionYearsForAState')
							      buildElectionYears(myResults);
								else if(jsObj.task == "validateEleResults")
								{
								  $("#eleResAjaxImg").css("display","none");
								  showConstituencyWiseEleResults(myResults,jsObj);
								  showBoothWiseEleResults(myResults,jsObj);
								}
								else if(jsObj.task == "validatePanchayatHamletData")
								 showMappingDataForPanchayat(myResults,jsObj);
								
							
							}catch (e) {
							    //alert(Exception);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 }
 
 function getPublicationDate()
	{
	var constituencyID = $("#constituencyList").val();
	
	var jsObj=
	{
		selected:constituencyID,
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	
	callAjax(jsObj,url);
}
var publicationDatesList;
function buildPublicationDateList(results)
	{
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}

	var largest = Math.max.apply(Math, publicationIdsArray);

	$('#publicationDateList').val(largest);
	$('#publicationDateList').trigger("change");

}

function buildPublicationDateListForPanchayat(results)
	{
	
	var selectedElmt=document.getElementById("publicationList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}
		

	}

	//var largest = Math.max.apply(Math, publicationIdsArray);

	//$('#publicationDateList').val(largest);
	//$('#publicationDateList').trigger("change");

}

function buildPublicationDates()
{
  var constituencyID = $("#constituencyList1").val();
	
	var jsObj=
	{
		selected:constituencyID,
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	callAjax(jsObj,url);
}

function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	

function buildData(results)
{
	$("#ajaxImgId").hide();
	$("#votersBasicInfo").css("display","none");
	var str = '';
	var divele = document.getElementById("subLevelInfo");
	
	str +='<table id="sublevel" class="table table-bordered table-striped table-hover">';
	str += '<tr>'
	str += '<th>Mandal</th>';
	str += '<th>Panchayat</th>';
	str += '<th>Total Voters</th>';
	str += '<th>Assigned Voters</th>';
	str += '<th>Not Assigned Voters</th>';
	str += '<th>Total Hamlets</th>';
	str += '<th>Hamlets Assigned</th>';
	str += '<th>Hamlets Not Assigned</th>';
	str += '</tr>';

	for(var i in results)
	{
		str += '<tr>';	
		str += '<td>'+results[i].tehsilName+'</td>';
		str += '<td>'+results[i].panchayatName+'</td>';
		str += '<td>'+results[i].totalVoters+'</td>';
		str += '<td>'+results[i].hamletAssignedVoters+'</td>';
		str += '<td>'+results[i].hamletsNotAssignedVoters+'</td>';

		str += '<td>';
		for(var j in results[i].hamletsList)
			str += ''+results[i].hamletsList[j].name+'<br>';
		str += '</td>';
	
		str += '<td>';
		for(var k in results[i].assignedHamletsList)
			str += ''+results[i].assignedHamletsList[k].name+' ('+results[i].assignedHamletsList[k].populateId+')<br>';
		str += '</td>';

		str += '<td>';
		for(var l in results[i].notAssignedHamletsList)
			str += ''+results[i].notAssignedHamletsList[l].name+'<br>';
		str += '</td>';

		str += '</tr>';	
	}
	str +='</table>';
	divele.innerHTML = str;
}

function showVotersBasicInfo(result,jsObj)
{

	$("#votersBasicInfoInnerDiv").html('');
	$("#ajaxImgId").hide();
	if(result == null)
		return;
	
	$("#voterInfoHeading").html(''+jsObj.name+' Constituency Information');
	var str = '';
	//str +='<h4 id="voterInfoHeading">'+jsObj.name+' Constituency Information</h4>';
	if(result.areaType == "RURAL-URBAN" || result.areaType == "RURAL")
	{
	  str +='<span class="btn btn-info btn-small">'+result.totalmandals+'</span><span class="help-inline f2">Mandals</span>';
	
	  str +='<span class="btn btn-info btn-small">'+result.totalPanchayats+'</span><span class="help-inline f2">Panchayats</span>';
			
	}

	if(result.areaType == "RURAL-URBAN" || result.areaType == "URBAN")
	{
	   str +='<span class="btn btn-info btn-small">'+result.totalMuncipalities+'</span><span class="help-inline f2">Muncipalities</span>';
	
	   str +='<span class="btn btn-info btn-small">'+result.totalWards+'</span><span class="help-inline f2">Wards</span>';
		
	}
		
	str +='<span class="btn btn-info btn-small">'+result.totalBooths+'</span><span class="help-inline f2">Booths</span>';
		
	
	$("#votersBasicInfoInnerDiv").html(str);

}

function showVoterInfoData(myResults,jsObj)
{
  var result = myResults.voterInfo;
  var str='';
  str +='<h4>Voter Basic Details</h4>';

		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Constituency Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Constituencies</th>';
		str +='<th>Data Saved Constituencies</th>';
		str +='<th>Missed Constituencies</th>';
		str +='<th>Extra Constituencies</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalConstituencies+'</td>';
		str +='<td>'+result.savedConstituencyCount+'</td>';
		str +='<td>'+result.missedConstituencyCount+'';
		if(result.missedConstituencyCount > 0)
		{
			if(result.missedConstituencyList != null && result.missedConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedConstituencyList.length;j++)
				str +='<br><span>'+result.missedConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedConstituencyCount+'';
		if(result.repeatedConstituencyCount > 0)
		{
		  if(result.repeatedConstituencyList != null && result.repeatedConstituencyList.length > 0)
		  {
			 str +='<br>----------------';
			 for(var i=0;i<result.repeatedConstituencyList.length;i++)
			  str +='<br><span>'+result.repeatedConstituencyList[i].name+' --- '+result.repeatedConstituencyList[i].id+'</span>';
		  }
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

  if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "RURAL")
	{
		//Mandal Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Mandals Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Mandals</th>';
		str +='<th>Data Saved Mandals</th>';
		str +='<th>Missed Mandals</th>';
		str +='<th>Extra Mandals</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalmandals+'</td>';
		str +='<td>'+result.savedMandalsCount+'</td>';
		str +='<td>'+result.missedMandalsCount+'';
		if(result.missedMandalsCount > 0)
		{
			if(result.missedMandalList != null && result.missedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedMandalList.length;j++)
				str +='<br><span>'+result.missedMandalList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMandalsCount+'';
		if(result.repeatedMandalsCount > 0)
		{
		  if(result.repeatedMandalList != null && result.repeatedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.repeatedMandalList.length;j++)
				str +='<br><span>'+result.repeatedMandalList[j].name+' --- '+result.repeatedMandalList[j].id+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
		//Panchayat Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Panchayats Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Panchayats</th>';
		str +='<th>Data Saved Panchayats</th>';
		str +='<th>Missed Panchayats</th>';
		str +='<th>Extra Panchayats</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalPanchayats+'</td>';
		str +='<td>'+result.savedPanchayatsCount+'</td>';
		str +='<td>'+result.missedPanchayatsCount+'';
		if(result.missedPanchayatsCount > 0)
		{
			if(result.missedPanchayatList != null && result.missedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.missedPanchayatList.length;i++)
		      str +='<br><span>'+result.missedPanchayatList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedPanchayatsCount+'';
		if(result.repeatedPanchayatsCount > 0)
		{
		  if(result.repeatedPanchayatList != null && result.repeatedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.repeatedPanchayatList.length;i++)
		      str +='<br><span>'+result.repeatedPanchayatList[i].name+' --- '+result.repeatedPanchayatList[i].id+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

	}
		
	if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "URBAN")
	{
	   //Muncipalities info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Muncipalities Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Muncipalities</th>';
		str +='<th>Data Saved Muncipalities</th>';
		str +='<th>Missed Muncipalities</th>';
		str +='<th>Extra Muncipalities</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalMuncipalities+'</td>';
		str +='<td>'+result.savedMuncipalitiesCount+'</td>';
		str +='<td>'+result.missedMuncipalitiesCount+'';
		if(result.missedMuncipalitiesCount >0)
		{
		  if(result.missedMuncipalityList != null && result.missedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedMuncipalityList.length;k++)
				str +='<br><span>'+result.missedMuncipalityList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMuncipalitiesCount+'';
		if(result.repeatedMuncipalitiesCount >0)
		{
		  if(result.repeatedMuncipalityList != null && result.repeatedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedMuncipalityList.length;k++)
				str +='<br><span>'+result.repeatedMuncipalityList[k].name+' --- '+result.repeatedMuncipalityList[k].id+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

		//Wards info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Wards Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Wards</th>';
		str +='<th>Data Saved Wards</th>';
		str +='<th>Missed Wards</th>';
		str +='<th>Extra Wards</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalWards+'</td>';
		str +='<td>'+result.savedWardsCount+'</td>';
		str +='<td>'+result.missedWardsCount+'';
		if(result.missedWardsCount > 0)
		{
		  if(result.missedWardList != null && result.missedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedWardList.length;k++)
				str +='<br><span>'+result.missedWardList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedWardsCount+'';
		if(result.repeatedWardsCount > 0)
		{
		  if(result.repeatedWardList != null && result.repeatedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedWardList.length;k++)
				str +='<br><span>'+result.repeatedWardList[k].name+' --- '+result.repeatedWardList[k].id+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
	}
	    //Booths info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Booths Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Booths</th>';
		str +='<th>Data Saved Booths</th>';
		str +='<th>Missed Booths</th>';
		str +='<th>Extra Booths</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalBooths+'</td>';
		str +='<td>'+result.savedBoothsCount+'</td>';
		str +='<td>'+result.missedBoothsCount+'';
		if(result.missedBoothsCount > 0)
		{
		  if(result.missedBoothsList != null && result.missedBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.missedBoothsList.length;k++)
			 str +='<br><span>'+result.missedBoothsList[k].name+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.repeatedBoothsCount+'';
		if(result.repeatedBoothsCount > 0)
		{
		  if(result.repeatedBoothsList != null && result.repeatedBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.repeatedBoothsList.length;k++)
			 str +='<br><span>'+result.repeatedBoothsList[k].name+' --- '+result.repeatedBoothsList[k].id+'</span>';
		  }
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		$("#votersInfoDiv").html(str);

}

function showVoterFamilyData(myResults,jsObj)
{
	var result = myResults.familyInfo;
	
  var str='';
  str +='<h4>Voter Family Details</h4>';

		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Constituency Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Constituencies</th>';
		str +='<th>Data Saved Constituencies</th>';
		str +='<th>Missed Constituencies</th>';
		str +='<th>Extra Constituencies</th>';
		str +='<th>Incomplete Data Saved Constituencies</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalConstituencies+'</td>';
		str +='<td>'+result.savedConstituencyCount+'</td>';
		str +='<td>'+result.missedConstituencyCount+'';
		if(result.missedConstituencyCount > 0)
		{
			if(result.missedConstituencyList != null && result.missedConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedConstituencyList.length;j++)
				str +='<br><span>'+result.missedConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedConstituencyCount+'';
		if(result.repeatedConstituencyCount > 0)
		{
			if(result.repeatedConstituencyList != null && result.repeatedConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.repeatedConstituencyList.length;j++)
				str +='<br><span>'+result.repeatedConstituencyList[j].name+' --- '+result.repeatedConstituencyList[j].id+'</span>';
			}
		}
		str +='</td>';

		str +='<td>'+result.familyConstituencyCount+'';
		if(result.familyConstituencyCount > 0)
		{
			if(result.familyConstituencyList != null && result.familyConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.familyConstituencyList.length;j++)
				str +='<br><span>'+result.familyConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

  if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "RURAL")
	{
		//Mandal Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Mandals Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Mandals</th>';
		str +='<th>Data Saved Mandals</th>';
		str +='<th>Missed Mandals</th>';
		str +='<th>Extra Mandals</th>';
		str +='<th>Incomplete Data Saved Mandals</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalmandals+'</td>';
		str +='<td>'+result.savedMandalsCount+'</td>';
		str +='<td>'+result.missedMandalsCount+'';
		if(result.missedMandalsCount > 0)
		{
			if(result.missedMandalList != null && result.missedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedMandalList.length;j++)
				str +='<br><span>'+result.missedMandalList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMandalsCount+'';
		if(result.repeatedMandalsCount > 0)
		{
			if(result.repeatedMandalList != null && result.repeatedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.repeatedMandalList.length;j++)
				str +='<br><span>'+result.repeatedMandalList[j].name+' --- '+result.repeatedMandalList[j].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.familyMandalCount+'';
		if(result.familyMandalCount > 0)
		{
			if(result.familyMandalsList != null && result.familyMandalsList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.familyMandalsList.length;j++)
				str +='<br><span>'+result.familyMandalsList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
		//Panchayat Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Panchayats Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Panchayats</th>';
		str +='<th>Data Saved Panchayats</th>';
		str +='<th>Missed Panchayats</th>';
		str +='<th>Extra Panchayats</th>';
		str +='<th>Incomplete Data Saved Panchayats</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalPanchayats+'</td>';
		str +='<td>'+result.savedPanchayatsCount+'</td>';
		str +='<td>'+result.missedPanchayatsCount+'';
		if(result.missedPanchayatsCount > 0)
		{
			if(result.missedPanchayatList != null && result.missedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.missedPanchayatList.length;i++)
		      str +='<br><span>'+result.missedPanchayatList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedPanchayatsCount+'';

		if(result.repeatedPanchayatsCount > 0)
		{
		  if(result.repeatedPanchayatList != null && result.repeatedPanchayatList.length > 0)
		  {
			 str +='<br>----------------';
			 for(var i=0;i<result.repeatedPanchayatList.length;i++)
		      str +='<br><span>'+result.repeatedPanchayatList[i].name+' --- '+result.repeatedPanchayatList[i].id+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.familyPanchayatCount+'';
		if(result.familyPanchayatCount > 0)
		{
			if(result.familyPanchayatsList != null && result.familyPanchayatsList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.familyPanchayatsList.length;i++)
		      str +='<br><span>'+result.familyPanchayatsList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

	}
		
	if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "URBAN")
	{
	   //Muncipalities info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Muncipalities Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Muncipalities</th>';
		str +='<th>Data Saved Muncipalities</th>';
		str +='<th>Missed Muncipalities</th>';
		str +='<th>Extra Muncipalities</th>';
		str +='<th>Incomplete Data Saved Muncipalities</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalMuncipalities+'</td>';
		str +='<td>'+result.savedMuncipalitiesCount+'</td>';
		str +='<td>'+result.missedMuncipalitiesCount+'';
		if(result.missedMuncipalitiesCount >0)
		{
		  if(result.missedMuncipalityList != null && result.missedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedMuncipalityList.length;k++)
				str +='<br><span>'+result.missedMuncipalityList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMuncipalitiesCount+'';

		if(result.repeatedMuncipalitiesCount >0)
		{
		  if(result.repeatedMuncipalityList != null && result.repeatedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedMuncipalityList.length;k++)
				str +='<br><span>'+result.repeatedMuncipalityList[k].name+' --- '+result.repeatedMuncipalityList[k].id+'</span>';
			}
		}
		str +='</td>';

		str +='<td>'+result.familyMuncipalityCount+'';
		if(result.familyMuncipalityCount >0)
		{
		  if(result.familyMuncipalitiesList != null && result.familyMuncipalitiesList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.familyMuncipalitiesList.length;k++)
				str +='<br><span>'+result.familyMuncipalitiesList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

		//Wards info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Wards Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Wards</th>';
		str +='<th>Data Saved Wards</th>';
		str +='<th>Missed Wards</th>';
		str +='<th>Extra Wards</th>';
		str +='<th>Incomplete Data Saved Wards</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalWards+'</td>';
		str +='<td>'+result.savedWardsCount+'</td>';
		str +='<td>'+result.missedWardsCount+'';
		if(result.missedWardsCount > 0)
		{
		  if(result.missedWardList != null && result.missedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedWardList.length;k++)
				str +='<br><span>'+result.missedWardList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedWardsCount+'';
		if(result.repeatedWardsCount > 0)
		{
		  if(result.repeatedWardList != null && result.repeatedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedWardList.length;k++)
				str +='<br><span>'+result.repeatedWardList[k].name+' --- '+result.repeatedWardList[k].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.familyWardCount+'';
		if(result.familyWardCount > 0)
		{
		  if(result.familyWardsList != null && result.familyWardsList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.familyWardsList.length;k++)
				str +='<br><span>'+result.familyWardsList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
	}
	    //Booths info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Booths Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Booths</th>';
		str +='<th>Data Saved Booths</th>';
		str +='<th>Missed Booths</th>';
		str +='<th>Extra Booths</th>';
		str +='<th>Incomplete Data Saved Booths</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalBooths+'</td>';
		str +='<td>'+result.savedBoothsCount+'</td>';
		str +='<td>'+result.missedBoothsCount+'';
		if(result.missedBoothsCount > 0)
		{
		  if(result.missedBoothsList != null && result.missedBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.missedBoothsList.length;k++)
			 str +='<br><span>'+result.missedBoothsList[k].name+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.repeatedBoothsCount+'';
		if(result.repeatedBoothsCount > 0)
		{
		  if(result.repeatedBoothsList != null && result.repeatedBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.repeatedBoothsList.length;k++)
			 str +='<br><span>'+result.repeatedBoothsList[k].name+' --- '+result.repeatedBoothsList[k].id+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.familyBoothCount+'';
		if(result.familyBoothCount > 0)
		{
		  if(result.familyBoothsList != null && result.familyBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.familyBoothsList.length;k++)
			 str +='<br><span>'+result.familyBoothsList[k].name+'</span>';
		  }
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		$("#voterFamilyInfoDiv").html(str);
 
}

function showVoterAgeData(myResults,jsObj)
{
	var result = myResults.ageInfo;
  var str='';
  str +='<h4>Voter Age Details</h4>';

	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Constituency Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Constituencies</th>';
		str +='<th>Data Saved Constituencies</th>';
		str +='<th>Missed Constituencies</th>';
		str +='<th>Extra Constituencies</th>';
		str +='<th>Incomplete Data Saved Constituencies</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalConstituencies+'</td>';
		str +='<td>'+result.savedConstituencyCount+'</td>';
		str +='<td>'+result.missedConstituencyCount+'';
		if(result.missedConstituencyCount > 0)
		{
			if(result.missedConstituencyList != null && result.missedConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedConstituencyList.length;j++)
				str +='<br><span>'+result.missedConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedConstituencyCount+'';
		if(result.repeatedConstituencyCount > 0)
		{
		  if(result.repeatedConstituencyList != null && result.repeatedConstituencyList.length > 0)
		  {
			 str +='<br>----------------';
			 for(var i=0;i<result.repeatedConstituencyList.length;i++)
			  str +='<br><span>'+result.repeatedConstituencyList[i].name+' --- '+result.repeatedConstituencyList[i].id+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.ageWiseConstituencyCount+'';
		if(result.ageWiseConstituencyCount > 0)
		{
			if(result.ageWiseConstituencyList != null && result.ageWiseConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.ageWiseConstituencyList.length;j++)
				str +='<br><span>'+result.ageWiseConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

  if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "RURAL")
	{
		//Mandal Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Mandals Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Mandals</th>';
		str +='<th>Data Saved Mandals</th>';
		str +='<th>Missed Mandals</th>';
		str +='<th>Extra Mandals</th>';
		str +='<th>Incomplete Data Saved Mandals</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalmandals+'</td>';
		str +='<td>'+result.savedMandalsCount+'</td>';
		str +='<td>'+result.missedMandalsCount+'';
		if(result.missedMandalsCount > 0)
		{
			if(result.missedMandalList != null && result.missedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedMandalList.length;j++)
				str +='<br><span>'+result.missedMandalList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMandalsCount+'';
		if(result.repeatedMandalsCount > 0)
		{
			if(result.repeatedMandalList != null && result.repeatedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.repeatedMandalList.length;j++)
				str +='<br><span>'+result.repeatedMandalList[j].name+' --- '+result.repeatedMandalList[j].id+'</span>';
			}
		}
		str +='</td>';

		str +='<td>'+result.ageWiseMandalCount+'';
		if(result.ageWiseMandalCount > 0)
		{
			if(result.ageWiseMandalsList != null && result.ageWiseMandalsList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.ageWiseMandalsList.length;i++)
		      str +='<br><span>'+result.ageWiseMandalsList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
		//Panchayat Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Panchayats Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Panchayats</th>';
		str +='<th>Data Saved Panchayats</th>';
		str +='<th>Missed Panchayats</th>';
		str +='<th>Extra Panchayats</th>';
		str +='<th>Incomplete Data Saved Panchayats</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalPanchayats+'</td>';
		str +='<td>'+result.savedPanchayatsCount+'</td>';
		str +='<td>'+result.missedPanchayatsCount+'';
		if(result.missedPanchayatsCount > 0)
		{
			if(result.missedPanchayatList != null && result.missedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.missedPanchayatList.length;i++)
		      str +='<br><span>'+result.missedPanchayatList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedPanchayatsCount+'';
		if(result.repeatedPanchayatsCount > 0)
		{
			if(result.repeatedPanchayatList != null && result.repeatedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.repeatedPanchayatList.length;i++)
		      str +='<br><span>'+result.repeatedPanchayatList[i].name+' --- '+result.repeatedPanchayatList[i].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.ageWisePanchayatCount+'';
		if(result.ageWisePanchayatCount > 0)
		{
			if(result.ageWisePanchayatsList != null && result.ageWisePanchayatsList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.ageWisePanchayatsList.length;i++)
		      str +='<br><span>'+result.ageWisePanchayatsList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

	}
		
	if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "URBAN")
	{
	   //Muncipalities info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Muncipalities Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Muncipalities</th>';
		str +='<th>Data Saved Muncipalities</th>';
		str +='<th>Missed Muncipalities</th>';
		str +='<th>Extra Muncipalities</th>';
		str +='<th>Incomplete Data Saved Muncipalities</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalMuncipalities+'</td>';
		str +='<td>'+result.savedMuncipalitiesCount+'</td>';
		str +='<td>'+result.missedMuncipalitiesCount+'';
		if(result.missedMuncipalitiesCount >0)
		{
		  if(result.missedMuncipalityList != null && result.missedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedMuncipalityList.length;k++)
				str +='<br><span>'+result.missedMuncipalityList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMuncipalitiesCount+'';
		if(result.repeatedMuncipalitiesCount >0)
		{
		  if(result.repeatedMuncipalityList != null && result.repeatedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedMuncipalityList.length;k++)
				str +='<br><span>'+result.repeatedMuncipalityList[k].name+' --- '+result.repeatedMuncipalityList[k].id+'</span>';
			}
		}
		str +='</td>';

		str +='<td>'+result.ageWiseMuncipalityCount+'';
		if(result.ageWiseMuncipalityCount >0)
		{
		  if(result.ageWiseMuncipalitiesList != null && result.ageWiseMuncipalitiesList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.ageWiseMuncipalitiesList.length;k++)
				str +='<br><span>'+result.ageWiseMuncipalitiesList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

		//Wards info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Wards Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Wards</th>';
		str +='<th>Data Saved Wards</th>';
		str +='<th>Missed Wards</th>';
		str +='<th>Extra Wards</th>';
		str +='<th>Incomplete Data Saved Wards</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalWards+'</td>';
		str +='<td>'+result.savedWardsCount+'</td>';
		str +='<td>'+result.missedWardsCount+'';
		if(result.missedWardsCount > 0)
		{
		  if(result.missedWardList != null && result.missedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedWardList.length;k++)
				str +='<br><span>'+result.missedWardList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedWardsCount+'';
		if(result.repeatedWardsCount > 0)
		{
		  if(result.repeatedWardList != null && result.repeatedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedWardList.length;k++)
				str +='<br><span>'+result.repeatedWardList[k].name+' --- '+result.repeatedWardList[k].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.ageWiseWardCount+'';
		if(result.ageWiseWardCount > 0)
		{
		  if(result.ageWiseWardsList != null && result.ageWiseWardsList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.ageWiseWardsList.length;k++)
				str +='<br><span>'+result.ageWiseWardsList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
	}
	    //Booths info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Booths Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Booths</th>';
		str +='<th>Data Saved Booths</th>';
		str +='<th>Missed Booths</th>';
		str +='<th>Extra Booths</th>';
		str +='<th>Incomplete Data Saved Booths</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalBooths+'</td>';
		str +='<td>'+result.savedBoothsCount+'</td>';
		str +='<td>'+result.missedBoothsCount+'';
		if(result.missedBoothsCount > 0)
		{
		  if(result.missedBoothsList != null && result.missedBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.missedBoothsList.length;k++)
			 str +='<br><span>'+result.missedBoothsList[k].name+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.repeatedBoothsCount+'';
		if(result.repeatedBoothsCount > 0)
		{
		  if(result.repeatedBoothsList != null && result.repeatedBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.repeatedBoothsList.length;k++)
			 str +='<br><span>'+result.repeatedBoothsList[k].name+' --- '+result.repeatedBoothsList[k].id+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.ageWiseBoothCount+'';
		if(result.ageWiseBoothCount > 0)
		{
		  if(result.ageWiseBoothsList != null && result.ageWiseBoothsList.length > 0)
		  {
		    str +='<br>----------------';
			for(var k=0;k<result.ageWiseBoothsList.length;k++)
			 str +='<br><span>'+result.ageWiseBoothsList[k].name+'</span>';
		  }
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		$("#voterAgeInfoDiv").html(str);
  
}


function showVoterModificationData(myResults,jsObj)
{
	var result = myResults.modificationInfo;
  var str='';
  str +='<h4>Voter Modification Details</h4>';

        str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Constituency Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Constituencies</th>';
		str +='<th>Data Saved Constituencies</th>';
		str +='<th>Missed Constituencies</th>';
		str +='<th>Extra Constituencies</th>';
		str +='<th>Incomplete Data Saved Constituencies</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalConstituencies+'</td>';
		str +='<td>'+result.savedConstituencyCount+'</td>';
		str +='<td>'+result.missedConstituencyCount+'';
		if(result.missedConstituencyCount > 0)
		{
			if(result.missedConstituencyList != null && result.missedConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedConstituencyList.length;j++)
				str +='<br><span>'+result.missedConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedConstituencyCount+'';
		if(result.repeatedConstituencyCount > 0)
		{
		  if(result.repeatedConstituencyList != null && result.repeatedConstituencyList.length > 0)
		  {
			 str +='<br>----------------';
			 for(var i=0;i<result.repeatedConstituencyList.length;i++)
			  str +='<br><span>'+result.repeatedConstituencyList[i].name+' --- '+result.repeatedConstituencyList[i].id+'</span>';
		  }
		}
		str +='</td>';
		str +='<td>'+result.statusWiseConstituencyCount+'';
		if(result.statusWiseConstituencyCount > 0)
		{
			if(result.statusWiseConstituencyList != null && result.statusWiseConstituencyList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.statusWiseConstituencyList.length;j++)
				str +='<br><span>'+result.statusWiseConstituencyList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

  if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "RURAL")
	{
		//Mandal Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Mandals Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Mandals</th>';
		str +='<th>Data Saved Mandals</th>';
		str +='<th>Missed Mandals</th>';
		str +='<th>Extra Mandals</th>';
		str +='<th>Incomplete Data Saved Mandals</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalmandals+'</td>';
		str +='<td>'+result.savedMandalsCount+'</td>';
		str +='<td>'+result.missedMandalsCount+'';
		if(result.missedMandalsCount > 0)
		{
			if(result.missedMandalList != null && result.missedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.missedMandalList.length;j++)
				str +='<br><span>'+result.missedMandalList[j].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMandalsCount+'';
		if(result.repeatedMandalsCount > 0)
		{
		  if(result.repeatedMandalList != null && result.repeatedMandalList.length > 0)
			{
			  str +='<br>----------------';
			  for(var j=0;j<result.repeatedMandalList.length;j++)
				str +='<br><span>'+result.repeatedMandalList[j].name+' --- '+result.repeatedMandalList[j].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.statusWiseMandalCount+'';
		if(result.statusWiseMandalCount > 0)
		{
			if(result.statusWiseMandalsList != null && result.statusWiseMandalsList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.statusWiseMandalsList.length;i++)
		      str +='<br><span>'+result.statusWiseMandalsList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
		//Panchayat Info
		str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Panchayats Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Panchayats</th>';
		str +='<th>Data Saved Panchayats</th>';
		str +='<th>Missed Panchayats</th>';
		str +='<th>Extra Panchayats</th>';
		str +='<th>Incomplete Data Saved Panchayats</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalPanchayats+'</td>';
		str +='<td>'+result.savedPanchayatsCount+'</td>';
		str +='<td>'+result.missedPanchayatsCount+'';
		if(result.missedPanchayatsCount > 0)
		{
			if(result.missedPanchayatList != null && result.missedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.missedPanchayatList.length;i++)
		      str +='<br><span>'+result.missedPanchayatList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedPanchayatsCount+'';
		if(result.repeatedPanchayatsCount > 0)
		{
		  if(result.repeatedPanchayatList != null && result.repeatedPanchayatList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.repeatedPanchayatList.length;i++)
		      str +='<br><span>'+result.repeatedPanchayatList[i].name+' --- '+result.repeatedPanchayatList[i].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.statusWisePanchayatCount+'';
		if(result.statusWisePanchayatCount > 0)
		{
			if(result.statusWisePanchayatsList != null && result.statusWisePanchayatsList.length >0)
			{
			  str +='<br>----------------';
			 for(var i=0;i<result.statusWisePanchayatsList.length;i++)
		      str +='<br><span>'+result.statusWisePanchayatsList[i].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

	}
		
	if(myResults.areaType == "RURAL-URBAN" || myResults.areaType == "URBAN")
	{
	   //Muncipalities info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Muncipalities Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Muncipalities</th>';
		str +='<th>Data Saved Muncipalities</th>';
		str +='<th>Missed Muncipalities</th>';
		str +='<th>Extra Muncipalities</th>';
		str +='<th>Incomplete Data Saved Muncipalities</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalMuncipalities+'</td>';
		str +='<td>'+result.savedMuncipalitiesCount+'</td>';
		str +='<td>'+result.missedMuncipalitiesCount+'';
		if(result.missedMuncipalitiesCount >0)
		{
		  if(result.missedMuncipalityList != null && result.missedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedMuncipalityList.length;k++)
				str +='<br><span>'+result.missedMuncipalityList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedMuncipalitiesCount+'';
		if(result.repeatedMuncipalitiesCount >0)
		{
		  if(result.repeatedMuncipalityList != null && result.repeatedMuncipalityList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedMuncipalityList.length;k++)
				str +='<br><span>'+result.repeatedMuncipalityList[k].name+' --- '+result.repeatedMuncipalityList[k].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.statusWiseMuncipalityCount+'';
		if(result.statusWiseMuncipalityCount >0)
		{
		  if(result.statusWiseMuncipalitiesList != null && result.statusWiseMuncipalitiesList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.statusWiseMuncipalitiesList.length;k++)
				str +='<br><span>'+result.statusWiseMuncipalitiesList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';

		//Wards info
	    str +='<div class="voterInfoDiv">';
		str +='<h3 class="headingSpan">Wards Info</h3>';
		str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
		str +='<tr>';
		str +='<th>Total Wards</th>';
		str +='<th>Data Saved Wards</th>';
		str +='<th>Missed Wards</th>';
		str +='<th>Extra Wards</th>';
		str +='<th>Incomplete Data Saved Wards</th>';
		str +='</tr>';
		str +='<tr>';
		str +='<td>'+myResults.totalWards+'</td>';
		str +='<td>'+result.savedWardsCount+'</td>';
		str +='<td>'+result.missedWardsCount+'';
		if(result.missedWardsCount > 0)
		{
		  if(result.missedWardList != null && result.missedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.missedWardList.length;k++)
				str +='<br><span>'+result.missedWardList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.repeatedWardsCount+'';
		if(result.repeatedWardsCount > 0)
		{
		  if(result.repeatedWardList != null && result.repeatedWardList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.repeatedWardList.length;k++)
				str +='<br><span>'+result.repeatedWardList[k].name+' --- '+result.repeatedWardList[k].id+'</span>';
			}
		}
		str +='</td>';
		str +='<td>'+result.statusWiseWardCount+'';
		if(result.statusWiseWardCount > 0)
		{
		  if(result.statusWiseWardsList != null && result.statusWiseWardsList.length > 0)
			{
			  str +='<br>----------------';
			  for(var k=0;k<result.statusWiseWardsList.length;k++)
				str +='<br><span>'+result.statusWiseWardsList[k].name+'</span>';
			}
		}
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		str +='</div>';
		
	}
	    
	$("#voterModificationDiv").html(str);
 }


function showVoterModificationAgeData(myResults,jsObj)
{
  var result = myResults.modificationAgeInfo.modificationAgeInfoList;

  var str='';
   str +='<h4>Voter Modification Age Details</h4>';
   if(result == null || result.length == 0)
	   str +='<p class="paraCls">There is no conflicts in saved data.</p>';
	if(result != null && result.length > 0)
	{
	   str +='<div class="voterInfoDiv">';
	   str +='<table class="table table-bordered table-striped table-hover" id="mandalTab">';
	   str +='<tr>';
	   str +='<th>Type</th>';
	   str +='<th>Incomplete Data Saved Locations</th>';
	   str +='</tr>';
	  
	   for(var i in result)
	   {
		str +='<tr>';
		str +='<td>'+result[i].url+'</td>';
		str +='<td>'+result[i].name+'</td>';
		str +='</tr>';
	   }
	   str +='</table>';
	   str +='</div>';
	}
		
	$("#voterModificationAgeDiv").html(str);
}



</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#votersBasicInfoId").click(function(){
		
	$("#errorMsgDiv").html('');
	$("#votersBasicInnerDiv").css('display','block');
	$("#votersBasicInfo").css('display','block');
	
	$("#subLevelDataId").hide();
	var constituencyId = $("#constituencyList").val();
	var publicationDateId = $("#publicationDateList").val();
	if(constituencyId == 0 || constituencyId == "0")
	{
      $('#errorMsgDiv').html("Please Select Constituency.");
	  return;
	}
	else if(publicationDateId == 0 || publicationDateId == "0")
	{
      $('#errorMsgDiv').html("Please Select Publication Date.");
	  return;
	}
	var name = $('#constituencyList :selected').text();
	$("#ajaxImgId").show();
	var jsObj=
		{		
			constituencyId:constituencyId,
			publicationDateId:publicationDateId,
			name:name,
            task:"checkVotersBasicInfo"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "checkVotersBasicInfoAction.action?"+rparam;						
		callAjax(jsObj,url);

	});

		
	$("#hideMenu").live("click",function(){
		$("#votersBasicInnerDiv").css("display","none");
		$("#hideAndShow").html('<a id="showMenu" class="btn pull-right"  href="javascript:{}">show<i class="icon-chevron-down"></i></a>');
	});


	$("#showMenu").live("click",function(){
		$("#votersBasicInnerDiv").css("display","block");
		$("#hideAndShow").html('<a id="hideMenu" class="btn pull-right"  href="javascript:{}">Hide<i class="icon-chevron-up"></i></a>');
	});

	
});
</script>
</body>
</html>