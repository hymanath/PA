<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Suggestive Model</title>
 <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
<style type="text/css">	
	select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 180px;
	}
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
	input, button, select {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		 border: 1px solid lightBlue;
	}
	.tdWidth1,#tdWidth{
	width: 150px;
	}

	
	#requiredValue{
	color:red;
	font-size:large;
	}	
	/*#mainDiv{
	font-family: serif verdana sans-serif;
	border: 1px lightBlue solid ;
	width: 920px; 
	margin-left: 15px;
	margin-bottom: 15px;
	background-color:white;
	font-weight:bold;
	font-size:15px;
	border-radius: 5px 5px 5px 5px;
	}	

	#tableRowS,#tableRowC,#tableRowM,#tableRowP,#tableRowB{
	margin-left: -10px;
	}

	#titleHeading{
	font-family: verdana sans-serif serif;
	margin-top: 30px;
	height: 25px;
	width: 920px; 
	margin-left: 15px;
	color:white;
	background:none repeat scroll 0 0 #06ABEA;
	margin-top: 30px; 
	margin-bottom: 5px;
	font-weight:bold;
	font-size:18px;
	border-radius: 5px 5px 5px 5px;
	}
	#errorMsgDiv{
	color: red; 
	float: left; 
	font-weight:normal;
	font-size:15px;
	width: 730px;
	height: 25px;
	padding-top: 5px;
	}*/
/*#partyPerformanceInnerDiv table th,#weakPollingPercentageDiv table th,#strongPollingPercentageDiv table th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#partyPerformanceInnerDiv table td,#weakPollingPercentageDiv table td,#strongPollingPercentageDiv table td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}*/
/* #partyPerformanceMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 960px;}
#partyPerformanceBtnDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 200px;} */



#suggestiveMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 990px;margin-top:20px;margin-bottom:20px;}
th {
    background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
}

.headingCls{ color: #005580;
    font-size: 15px;
    margin-bottom: 10px;}
	</style>
<script type="text/javascript" >

$(document).ready(function(){
getConstituencyList();
});
function getConstituencyList(){

var jsObj= 
	{	
		task:"getConstituencies"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getConstituenciesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);

}
function getMandals(){
	var value =  $("#listConstituencyNames option:selected").val();
	var list = document.getElementById("listMandalNames");
	var partyElmts = document.getElementById('partySelectEl')
	var electionyrElmt1 = document.getElementById('electionYearSelectEl1')
	var electionyrElmt2 = document.getElementById('electionYearSelectEl2')
	$("#listConstituencyNames").css("border","1px solid lightBlue");
	removeSelectElements(partyElmts);
	removeSelectElements(electionyrElmt1);
	removeSelectElements(electionyrElmt2);
	removeSelectElements(list);
	addDefaultSelectValues(partyElmts);
	addDefaultSelectValues(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt1);
	addDefaultSelectValues(list);
	$('#errorMsgDiv').html('');
		if(value == 0){
		$("#listConstituencyNames").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Constituency');
		return;
		}
		var jsObj=
			{
					
					selected:value,
					selectElmt:"mandalField",
					str   : "all",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url)
	}
function getPartyDetails(mandalId){
	
	var list = document.getElementById("partySelectEl");
	var electionyrElmt1 = document.getElementById('electionYearSelectEl1');
	var electionyrElmt2 = document.getElementById('electionYearSelectEl2');
	$("#listMandalNames").css("border","1px solid lightBlue");
	removeSelectElements(electionyrElmt1);
	removeSelectElements(electionyrElmt2);
	removeSelectElements(list);
	
	addDefaultSelectValues(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt1);
	addDefaultSelectValues(list);
	$('#errorMsgDiv').html('');
	if(mandalId == 0){
		$("#listMandalNames").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Mandal');
		return;
	}
	var jsObj = 
	{
	mandalId : mandalId.slice(1),
	task:"getPartyDetails"
	}	
	var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyForSuggestiveAction.action?"+param;
	callAjax(param,jsObj,url);

}

	function getElectionYears(id){
	$('#errorMsgDiv').html('');
	var electionyrElmt1 = document.getElementById('electionYearSelectEl1');
	var electionyrElmt2 = document.getElementById('electionYearSelectEl2');
	$("#partySelectEl").css("border","1px solid lightBlue");
	removeSelectElements(electionyrElmt1);
	removeSelectElements(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt2);
	addDefaultSelectValues(electionyrElmt1);
		var mandalId = $('#listMandalNames').val();
		if(id == 0){
		$("#partySelectEl").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Party Name');
		return;
		}
		var jsObj=
			{
					electionTypeId:2,
					mandalId : mandalId.slice(1),
					task:"getElectionYearsInPanchayat"
                    					
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsForMandalAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
function validateYear1(yearId){
	$('#errorMsgDiv').html('');
		$("#electionYearSelectEl1").css("border","1px solid lightBlue");
	if(yearId == 0){
		$("#electionYearSelectEl1").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Election Years');
		return;
	}
	
}

function validateYear2(yearId){
	$('#errorMsgDiv').html('');
		$("#electionYearSelectEl2").css("border","1px solid lightBlue");
	if(yearId == 0){
		$("#electionYearSelectEl2").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Election Years');
		return;
	}	
}
function getLeadersList(){
var mandalId = $('#listMandalNames option:selected').val();
var constituencyId = $('#listConstituencyNames option:selected').val();
var jsObj= 
	{	
		mandalId       : mandalId.slice(1),
		constituencyId : constituencyId,
		task           : "getLeadersList"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getLeadersDataAction.action?"+param;
	callAjax(param,jsObj,url);

}
function callAjax(param,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{												
						if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
						
						if(jsObj.task == "getElectionYearsInPanchayat")
						{
							populateElectionYearDropdown(myResults);
						}
						else if(jsObj.task == "getPartyDetails")
						{
							populatePartiesDropdown(myResults);
						}								
						else if(jsObj.task == "getConstituencies")
						{	
							populateConstituencyListDropdown(myResults);
						}
						else if(jsObj.task == "getMandalList")
						{
							buildMandalList(myResults);
						}
						else if(jsObj.task == "getPartyPerformanceReport")
						{
							showPartyPerformanceReport(myResults,jsObj);
							showStrongAndWeakPollingPercentage(myResults,jsObj);
							//showSuggestedLocations(myResults,jsObj);
						}
						else if(jsObj.task == "getLeadersList")
						{
							buildLeadersTable(myResults);
						}
						else if(jsObj.task == "getDeletedVotersInfo")
						
						buildDeletedVotersInfo(myResults);
					}catch (e){
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
function populatePartiesDropdown(results)
{
	var partySelectEl = document.getElementById("partySelectEl");
	removeSelectElements(partySelectEl);
	var opElmt=document.createElement('option');
		opElmt.value='0';
		opElmt.text='Select Party';
		addOptions(partySelectEl,opElmt);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
		addOptions(partySelectEl,opElmt);	
	}	
}

function populateElectionYearDropdown(results)
{
	var electionYearsEl1 = document.getElementById("electionYearSelectEl1");
	var electionYearsEl2 = document.getElementById("electionYearSelectEl2");

		removeSelectElements(electionYearsEl1);
		removeSelectElements(electionYearsEl2);

	if(results!=null)
		for(var i in results)
		{	
			var opElmt1=document.createElement('option');
			var opElmt2=document.createElement('option');
			opElmt1.value=results[i].id;
			opElmt1.text=results[i].name;
			opElmt2.value=results[i].id;
			opElmt2.text=results[i].name;
			addOptions(electionYearsEl1,opElmt1);
			addOptions(electionYearsEl2,opElmt2);
		}
}
function buildMandalList(results){

var list = document.getElementById("listMandalNames");
	removeSelectElements(list); 
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}
function populateConstituencyListDropdown(results){

	var list = document.getElementById("listConstituencyNames");
	removeSelectElements(list);
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}

function addOptions(list,opElmt){
	try
		{
		list.add(opElmt,null); // standards compliant
		}
	catch(ex)
		{
		list.add(opElmt); // IE only
		}
}

function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	

}

function addDefaultSelectValues(elmt){
	var opElmt=document.createElement('option');
		opElmt.value='0';
		opElmt.text='Select ';	
	addOptions(elmt,opElmt);
}

function buildLeadersTable(results)
{
	if(results != null && results.length > 0)
	{
		var mandalName = $('#listMandalNames option:selected').text().toUpperCase();
		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+mandalName+' BOOTH LEVEL CASTE DETAILS </h4>';
		//str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;"></h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Booth</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '</tr>';
		for(var i in results)
		{
			str += '<tr>';
			var rowLength = results[i].boothLevelLeadersList.length;
			str += '<td rowspan='+rowLength+'>'+results[i].mandalName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatTotalVoters+'</td>'; 
			str += '<td rowspan='+rowLength+' >';
			for(var j in results[i].panchayatLevelLeadersList)
			{
				str += ''+results[i].panchayatLevelLeadersList[j].casteName +'('+results[i].panchayatLevelLeadersList[j].casteVotersPerc+')  '; 
			}
			str += '</td>';
			
			for(var k in results[i].boothLevelLeadersList)
			{
			
				if(k > 0)
				{
					str += '<tr>';
				}
				str += '<td>'+results[i].boothLevelLeadersList[k].boothName+'</td>'; 
				str += '<td>'+results[i].boothLevelLeadersList[k].boothTotalVoters+'</td>';
				str += '<td>';
				for(var m in results[i].boothLevelLeadersList[k].boothLevelLeadersList)
				{
					str += ''+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteName+'('+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteVotersPerc+')  '; 
				}
				str += '</td>';
				if(k > 0)
				{
					str += '</tr>';
				}
			}
			str += '</tr>';
		}
		str += '</table>';
		str += '</div>';
		str += '</div>';
		$('#leadersTable').html(str);
	}
	
}

function showSuggestedLocations(myResults,jsObj){
 var str ='';
 if(myResults != null && myResults.length > 0 && myResults[0].suggestedLocations != null && myResults[0].suggestedLocations.length > 0){
    str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">Order OF Priority to Target Geographically </h4>';
	str+='<table  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; font-weight: lighter; color: black;">';
	str+='  <tr>';
	str+='    <th>Panchayat Name</th>';
	str+='  </tr>';
	 for(var i in myResults[0].suggestedLocations){
	   str+='<tr>';
	   str+='  <td>'+myResults[0].suggestedLocations[i].name+'</td>';
	   str+='</tr>';
	 }
	str+='</table>';
 }
 $("#suggestedLocationsDiv").html(str);
}

</script>
</head>
<body>
<div id="suggestiveMainDiv" align="center">
  <!--<div id="titleHeading" align="center"> SUGGESTIVE MODEL </div>-->
  <div class="widget blue">
  <div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">SUGGESTIVE MODEL</h4>
   <div id="mainDiv" align="center" >
     <div id="errorMsgDiv" >&nbsp;</div><br><br>
     <div style="margin-left: -7px;margin-bottom: 4px;">
		<table>
			<tr id="tableRowS">
				<td id="tdWidth">
					Constituency Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listConstituencyNames" onchange="getMandals(this.options[this.selectedIndex].value);">
					<option value="0"> Select Constituency </option>
					</select>
				</td>		
			
			<td id="tdWidth">
					Mandal Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listMandalNames" onchange="getPartyDetails(this.options[this.selectedIndex].value);">
					<option value="0"> Select Mandal </option>
					</select>
				</td>	
			</tr>
	</table>		
</div>
<div style="width: 500px; margin-left: -340px;margin-bottom: 4px;">
	<table>
		<tr id="tableRowS">
			<td id="tdWidth">
				Party Name:<font id="requiredValue" class="requiredFont">*</font> 
			</td>
			<td>
				<select id="partySelectEl" onchange="getElectionYears(this.options[this.selectedIndex].value)">
				<option value="0"> Select Party </option>
				</select>
			</td>
			</tr>
</table>
</div>
<div style="margin-bottom: 4px;"">
<table>
		<tr>
			<td id="tdWidth">
				Election Year :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="electionYearSelectEl1" onchange="validateYear1(this.options[this.selectedIndex].value)">
				<option value="0"> Select Year </option>
				</select>
			</td>
			<td></td><td id="tdWidth"></td>
			<td>
				<select id="electionYearSelectEl2" onchange="validateYear2(this.options[this.selectedIndex].value)">
				<option value="0"> Select Year </option>
				</select>
			</td>
		</tr>
	</table>
</div>
<!--<input type="button" value="Submit" class="btn btn-success" style="margin-bottom: 10px; margin-top: 10px;" />-->
<div id="partyPerformanceBtnDiv" style="margin-bottom: 4px;"><input type="button" value="submit" id="getPartyPer" class="btn btn-success" onclick="getLeadersList();"></div>
</div>
</div></div>
<div>
<div id="leadersTable"></div>
<div id="suggestedLocationsDiv"></div>
<div id="partyPerformanceMainDiv">
   <div id="partyPerformanceInnerDiv"></div>
</div>
<div id="strongPollingPerDiv" class="row-fluid">
    <div id="strongPollingPercentageDiv" class="span6"></div>
</div>
<div id="weakPollingPerDiv" class="row-fluid">
<div id="weakPollingPercentageDiv" class="span6"></div>
</div>
<!--<div id="deletedVotersInfo">

</div>-->

 
</div>


<script>
$(document).ready(function(){

$("#getPartyPer").click(function(){
	  var constituencyId = $('#listConstituencyNames option:selected').val();
	  var mandalId = $('#listMandalNames option:selected').val();
	  var partyId = $('#partySelectEl option:selected').val();
	  var eleId1 = $('#electionYearSelectEl1 option:selected').val();
	  var eleId2 = $('#electionYearSelectEl2 option:selected').val();
	  var eleIds = new Array();
	  eleIds.push(eleId1);
	  eleIds.push(eleId2);
	var jsObj = {
	        constituencyId:constituencyId,
			electionId:eleIds,
			partyId:partyId,
			locationId:mandalId,
			locationType:"mandal",
			tempVar:"",
			task:"getPartyPerformanceReport"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getPartyPerformanceAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
		
  });

});

 function showPartyPerformanceReport(result,jsObj)
{
	$("#partyPerformanceInnerDiv").html('');
  if(result == null || result.length == 0)
	{
     $("#partyPerformanceInnerDiv").html('No Data Found');
	 return;
	}
	var partyName = $('#partySelectEl option:selected').text();
	var str = '';
	str+='<div class="widget green">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>';
	//str +='<h4 style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">PANCHAYAT WISE INC PARTY PERFORMANCE REPORT</h4>';
	str +='<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
    str +='<tr>';
	str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">Type</th>';
	for(var i in result)
	 str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">'+result[i].name+'</th>';
	str +='</tr>';
    var length = result[0].partyPositionVOList.length;
	for(var j=0;j<length;j++){
		str +='<tr>';
		str +='<td>'+result[0].partyPositionVOList[j].name+'</td>';
		for(var i in result)
		{
		  str +='<td>'
		  for(var k in result[i].partyPositionVOList[j].partyPositionVOList)
		  {
			var tempVar = result[i].partyPositionVOList[j].partyPositionVOList.length-1;
			str +=''+result[i].partyPositionVOList[j].partyPositionVOList[k].name+'';
		    
			if(k != tempVar)
			 str +=', ';
		  }
		  
		  str +='</td>'
		}
		str +='</tr>';
    }
	str +='</table>';
	str += '</div>';
	str += '</div>';
	$("#partyPerformanceInnerDiv").html(str);

}

/* function showStrongAndWeakPollingPercentage(result,jsObj)
{
  if(result == null || result.length == 0)
  {
     $("#strongPollingPercentageDiv").html('');
	 $("#weakPollingPercentageDiv").html('');
	 return;
  }
  var StrongPollingPerList = result[0].strongPollingPercentVOList;
  var weakPollingPerList = result[0].weakPollingPercentVOList;
  var locationtype = "";
  alert(StrongPollingPerList);
  alert(weakPollingPerList);
  if(jsObj.locationType == "mandal")
   locationtype = "Panchayat";

  else if(jsObj.locationType == "panchayat")
   locationtype = "Booth";


  var str = '';
  var temp = '';
  str +='<h4 class="headingCls">Low Voting % in Strong '+locationtype+'s</h4>'; 
  temp +='<h4 class="headingCls">High Voting % in Weak '+locationtype+'s</h4>'; 

  if(StrongPollingPerList == null || StrongPollingPerList.length == 0)
  {
	$("#strongPollingPercentageDiv").css('display','none');
	$("#strongPollingPercentageDiv").html(''); 
  }
  if(weakPollingPerList == null || weakPollingPerList.length == 0)
  {
    $("#weakPollingPercentageDiv").css('display','none');
	$("#weakPollingPercentageDiv").html(''); 
  }
  
  //Strong
  if(StrongPollingPerList != null && StrongPollingPerList.length > 0)
  {
	
    str+='<table class="table table-bordered table-striped table-hover">';
    str += '<tr>';
    str +='<th>'+locationtype+' Name</th>';
    str +='<th>Party %</th>';
    str += '<th>Polling %</th>';
    str +='</tr>';
    for(var j in weakPollingPerList)
    {
	 str += '<tr>';
	 str += '<td>'+weakPollingPerList[j].name+'</td>';
	 str += '<td>'+weakPollingPerList[j].partyPercentage+'</td>';
	 str += '<td>'+weakPollingPerList[j].pollingPercentage+'</td>';
	 str += '</tr>';
    }

    str+='</table>';
	$("#strongPollingPercentageDiv").html(str); 
   }


  //weak
 if(weakPollingPerList != null && weakPollingPerList.length > 0)
 {
   
   temp+='<table class="table table-bordered table-striped table-hover">';
   temp += '<tr>';
   temp +='<th>'+locationtype+' Name</th>';
   temp +='<th>Party %</th>';
   temp += '<th>Polling %</th>';
   temp +='</tr>';
   for(var j in weakPollingPerList)
   {
	temp += '<tr>';
	temp += '<td>'+weakPollingPerList[j].name+'</td>';
	temp += '<td>'+weakPollingPerList[j].partyPercentage+'</td>';
	temp += '<td>'+weakPollingPerList[j].pollingPercentage+'</td>';
	temp += '</tr>';
   }

   temp+='</table>';
   $("#weakPollingPercentageDiv").html(temp); 
 }

} */
 
function showStrongAndWeakPollingPercentage(result,jsObj)
{
  if(result == null || result.length == 0)
  {
     $("#strongPollingPercentageDiv").html('');
	 $("#weakPollingPercentageDiv").html('');
	 return;
  }
  var StrongPollingPerList = result[0].strongPollingPercentVOList;
 
  var str = '';
  
	str += '<table >';
	var z = 0;
  for(var i in StrongPollingPerList)
  {
	if(z%2 == 0)
	{
		str += '<tr>';
	}
	str+='<td valign="top" style="padding-left:10px;"><div class="widget blue">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:450px;color: black;" class="">'+StrongPollingPerList[i].name+ ' AND POLLING % IS LESS</h4>';
	str += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	str += '<tr>';
	str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">PANCHAYAT</th>';
	str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">POLLING PERCENTAGE</th>';
	str += '</tr>';
	
	for(var j in StrongPollingPerList[i].partyPositionVOList)
	{
		str += '<tr>';
		str += '<td>'+StrongPollingPerList[i].partyPositionVOList[j].name+'</td>';
		str += '<td>'+StrongPollingPerList[i].partyPositionVOList[j].pollingPercentage+'</td>';
		str += '</tr>';
	}
	
	
	str += '</table>';
	str += '</div>';
	str += '</div></td>';
	if((z-1)%2 == 0)
	{
		str += '</tr>';
	}
	z++;
  }
  if(z%2 == 0)
  {
	str += '</tr>';
  }
  str += '</table>'
  $("#strongPollingPercentageDiv").html(str);
  var weakPollingPerList = result[0].weakPollingPercentVOList;
  var wstr = '';
  wstr += '<table >';
  var v = 0;
  for(var m in weakPollingPerList)
  {
	if(v%2 == 0)
	{
		wstr += '<tr>';
	}
	wstr+='<td valign="top" style="padding-left:10px;"><div class="widget blue">';
	wstr+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	wstr+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:450px;color: black;" class="">'+weakPollingPerList[m].name+ ' AND POLLING % IS MORE </h4>';
	//wstr += '<h4 style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">'+weakPollingPerList[m].name+ ' AND POLLING % IS MORE </h4>';
	wstr += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	wstr += '<tr>';
	wstr += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">PANCHAYAT</th>';
	wstr += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">POLLING PERCENTAGE</th>';
	wstr += '</tr>';
	
	for(var n in weakPollingPerList[m].partyPositionVOList)
	{
		wstr += '<tr>';
		wstr += '<td>'+weakPollingPerList[m].partyPositionVOList[n].name+'</td>';
		wstr += '<td>'+weakPollingPerList[m].partyPositionVOList[n].pollingPercentage+'</td>';
		wstr += '</tr>';
	}
	
	
	wstr += '</table>';
	wstr += '</div>';
	wstr += '</div><td>';
	if((v-1)%2 == 0)
	{
		wstr += '</tr>';
	}
	v++;
  }
  if(v%2 == 0)
  {
	wstr += '</tr>';
  }
  wstr += '</table>'
  $("#weakPollingPercentageDiv").html(wstr);
}
function getDeletedVotersInfo()
{
var panchayats = [];
panchayats.push(1392,1393,1394,1395);
var jsObj = {
			panchayats:panchayats,
			task:"getDeletedVotersInfo"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getDeletedVotersInfoByPanchayatIdsAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
}

function buildDeletedVotersInfo(results)
{
var divEle = document.getElementById("deletedVotersInfo");
if(results != null)
var str='';
str+='<h4 style="margin-left:10px;">Deleted Voters</h4>';
str+='<table class="table table-bordered table-striped table-hover">';
str+='<tr>';
str+='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;"> PanchayatName</th>';
str+='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;"> Deleted Voters</th>';
str+='</tr>';
for(var i in results)
{
str+='<tr>';
str+='<td>'+results[i].panchayatName+'</td>';
str+='<td>'+results[i].totalVoters+'</td>';
str+='</tr>';
}
str+='</table>';
divEle.innerHTML = str;
}
//getDeletedVotersInfo();

</script>
</body>
</html>
