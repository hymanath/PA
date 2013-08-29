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
 <script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.js"></script>
 <!--<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.min.js"></script>-->
  <!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>-->
<link rel="stylesheet" href="/resources/demos/style.css" />
<!--<link rel="stylesheet" href="styles/jqueryDataTable/css/datatable.css" />-->
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_page.css" />
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
 <script type="text/javascript" src="js/suggestiveModel.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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


<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});

</script>
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

.fromDiv,.toDiv{float:left;margin:10px;}
.inputDiv{float:right;margin-left:10px;}
#errorMsg{color:red;}
.table thead th{text-align:center;}
.ageGroupTable .table th, .table td{text-align:center;}

#tableageGroupTableId1,#tableageGroupTableId2,#tableageGroupTableId3{border:1px solid #ccc;}
#ageGroupTableId1,#ageGroupTableId2,#ageGroupTableId3,#ageGroupBoothTableId1,ageGroupBoothTableId2,ageGroupBoothTableId3{clear:both;}
.table-bordered th, .table-bordered td{font-family:verdana;}
.table th, .table td{font-family:verdana;}
.table thead th{vertical-align:middle;}
tr.even td.sorting_1{background-color:#ffffff;}

#titleageGroupBoothTableId1,#titleageGroupTableId1,#titleageGroupBoothTableId2,#titleageGroupTableId2,#titleageGroupBoothTableId3,#titleageGroupTableId3{clear:both;padding:15px;background:#9CE7FC;border:1px solid #cccccc;display:none;margin-top:25px;color:#000000;}

#panchayatWisePollingPercentageDiv{padding-top: 9px; padding-bottom: 21px;}

html{overflow-x: hidden;}
.spanCls{padding: 3px 5px; border-radius: 3px;}
#panchayatTab th,#panchayatTab1 th{background:#D9EDF7;color: #454545;}
.dataTables_info {text-align:left;}
.yearSpan{font-size: 18px;
    font-weight: bold;}

#partyPerformanceInnerDiv table th{background: none repeat scroll 0 0 #D9EDF7;color: #454545;}
</style>


<script type="text/javascript" >
$(document).ready(function(){
 <c:if test="${!hideMainMenu}">
  getConstituencyList();
 </c:if>
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
/*
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
*/

function clearAll(){
 	$('#leadersTable').html('');
 	$('#leadersTable1').html('');
 	$('#leadersTable2').html('');
 	$('#addedVoterDetailsDiv').html('');
 	$('#panchayatWisePollingPercentageDiv').html('');
 	$('#panchayatWisePollingPercHeadingDiv').html('');
	$('#ageGroupTableId1').html('');
	$('#ageGroupTableId2').html('');
	$('#ageGroupTableId3').html('');
	$('#ageGroupBoothTableId1').html('');
	$('#ageGroupBoothTableId2').html('');
	$('#ageGroupBoothTableId3').html('');
	$('#newPartyDiv').html('');
	$("#partyPerformanceInnerDiv").html('');
	$("#strongPollingPercentageDiv").html('');
	$("#weakPollingPercentageDiv").html('');
	$("#partyPerformanceBoothDiv").html('');
	$("#suggestedLocationsDiv").html('');
	$('#titleDiv').hide();
	$('#titleageGroupBoothTableId2').css("display","none");
	$('#titleageGroupTableId2').css("display","none");
	$('#titleageGroupBoothTableId1').css("display","none");
	$('#titleageGroupTableId1').css("display","none");
	$('#panchayatWisePollingPercMainDiv').css("display","none");
	$('#leadersTable2').css("display","none");

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
		var constituencyId = $('#listConstituencyNames').val();
		if(id == 0){
		$("#partySelectEl").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Party Name');
		return;
		}

		var jsObj=
			{
				electionScopeId:2,
				partyId:id,
				constituencyId:constituencyId,
				task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionsYearsForParties.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
function validateYear1(yearId,yearValue){
	$('#errorMsgDiv').html('');
		$("#electionYearSelectEl1").css("border","1px solid lightBlue");
	if(yearId == 0){
		$("#electionYearSelectEl1").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Election Years');
		return;
	}

var select = new Array();
$('#electionYearSelectEl1 option').each(function(){
	if(!(yearValue == $(this).text()|| yearValue < $(this).text()))
		{
		 var obj = {
			 id : $(this).val(),
			 name : $(this).text()
		}
	select.push(obj);
	}
});

	$('#electionYearSelectEl2').children().remove();
	$('<option>').val(0).text('Select Year').appendTo('#electionYearSelectEl2');

for(var i=0;i<select.length;i++)
	{	 					$('<option>').val(''+select[i].id+'').text(''+select[i].name+'').appendTo('#electionYearSelectEl2');
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
var checkStatus = $('#expCaste').is(":checked");
if(checkStatus == false)
{
	expCasteArray = new Array();
}
//var mandalId = $('#listMandalNames option:selected').val();
 <c:if test="${!castDetails}">
var constituencyId = $('#listConstituencyNames option:selected').val();
var casteIds=0;
 if(constituencyId == 0)
	return;
$('#candidateCastes :selected').each(function(i, selected){ 
	   casteIds+=','+$(this).val();
});
</c:if>
<c:if test="${castDetails}">
  var constituencyId = '${constituencyId}';
  var casteIds = '${casteIds}';
</c:if>
var jsObj= 
	{	
		//mandalId       : mandalId.slice(1),
		constituencyId   : constituencyId,
		casteIds         : casteIds,
		expCasteArray    : expCasteArray,
		checkStatus      : checkStatus,
		task             : "getLeadersList"		
	};
	 $("#getAllExpCasteDetails").val(YAHOO.lang.JSON.stringify(jsObj));
			  
		var uploadHandler = {
		success: function(o) {
		var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
		 $("#dashBoardImgLoading").hide();
		 if(checkStatus == false)
		 {
			buildLeadersTable(uploadResult);
		 }
		 else 
		 {
			buildLeadersTableWithExpPerc(uploadResult);
		 }
		}
		};
		YAHOO.util.Connect.setForm('exceptedCasteDetailsForm',false);
		YAHOO.util.Connect.asyncRequest('POST','getLeadersDataAction.action',uploadHandler);
}

function getLeadersListInRuralUrbans(){
var casteIds=0;
<c:if test="${!castDetails}"> 
var constituencyId = $('#listConstituencyNames option:selected').val();
$('#candidateCastes :selected').each(function(i, selected){ 
	   casteIds+=','+$(this).val();
   });
</c:if>
<c:if test="${castDetails}"> 
var constituencyId = '${constituencyId}';
casteIds = '${casteIds}';
$("#dashBoardImgLoadingNew").show();
</c:if>


var jsObj= 
	{	
		constituencyId : constituencyId,
		casteIds:casteIds,
		task           : "getLeadersListInRuralUrbans"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getLeadersListInRuralUrbansAction.action?"+param;
	callAjax(param,jsObj,url);
}

function getCandidateCastes(constituencyIds){
	var constituencyId = $('#listConstituencyNames').val();
	var jsObj ={
		constituencyId : constituencyId,
		task : "getUserAssignedVoterCastes"
		};
	var rparam="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url="getUserAssignedVoterCastesAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

function getConstituencyType()
	{
	    <c:if test="${!castDetails}"> 
		var constituencyId = $("#listConstituencyNames option:selected").val();
		</c:if>
		<c:if test="${castDetails}"> 
		var constituencyId = '${constituencyId}';
		</c:if>
		if(constituencyId == 0)
		return;
		var jsObj=
				{
					constituencyId : constituencyId,
					publicationId  : 8,
					task           : "getConstituencyType"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getReportLevelDetails.action?"+rparam;	

		callAjax(rparam,jsObj,url);
}

function callAjax(param,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{												
						if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
						
						if(jsObj.task == "getCasteDetails")
						{
							buildCasteDetails(myResults);
						}
						if(jsObj.task == "getElectionYears")
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
						    $("#ajaxImg").css("display","none");
							$("#dashBoardImgLoading").hide();
					      if(myResults[0].partyPositionVOList.length > 0)
							showPartyPerformanceReport(myResults,jsObj);
							if(myResults[0].boothwisePartyPositionVOList.length > 0)
							showPartyPerformanceReportForBooth(myResults,jsObj);
							showStrongAndWeakPollingPercentage(myResults,jsObj);
							buildAddedVotersDetails(myResults);
							showPartyPerformancePieChart(myResults,jsObj);
							showSuggestedLocations(myResults,jsObj);
							panchayatMatrx(myResults);
						}
						else if(jsObj.task == "getLeadersList")
						{
						    
							
						}
						else if(jsObj.task == "getDeletedVotersInfo")
						{
							buildDeletedVotersInfo(myResults);
						}
						else if(jsObj.task == "getAgeGroupWiseReport"){
							$('#ajaxLoaderImg').css('display','none');
							$("#dashBoardImgLoading").hide();
							buildAgeGroupWiseTable(myResults,jsObj);
						}
						else if(jsObj.task == "getEffectOfNewParty"){
							buildnewPartyEffectResults(myResults);
						}
						else if(jsObj.task== "getUserAssignedVoterCastes"){
							buildUserAssignedVotersCastes(myResults);
						}
						else if(jsObj.task== "getConstituencyType"){
							if(myResults[0].name == "RURAL-URBAN")
								getLeadersListInRuralUrbans();
						}
						else if (jsObj.task== "getLeadersListInRuralUrbans"){
						    $("#dashBoardImgLoadingNew").hide();
							buildLeadersTableForNONUrbanAreas(myResults);					
						}
						else if (jsObj.task== "getMandalsAndPanchayts"){
							storeMandalPanchayatValues(myResults);
						}
					}catch (e){
					//alert("Invalid JSON result" + e);   
					  $("#dashBoardImgLoading").hide();
					  $("#dashBoardImgLoadingNew").hide();
					}  
				},
			       scope : this,
			       failure : function( o ) {
			        			//alert( "Failed to load result" + o.status + " " + o.statusText);
			        }
			    };
		YAHOO.util.Connect.asyncRequest('POST', url, callback);
	}

function buildUserAssignedVotersCastes(results){
	var candidateCastesEl = document.getElementById("candidateCastes");
	removeSelectElements(candidateCastesEl);
	
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
		addOptions(candidateCastesEl,opElmt);	
	}	
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
	var opElmt1=document.createElement('option');
	var opElmt2=document.createElement('option');
		opElmt1.value='0';
		opElmt2.value='0';
		opElmt1.text='Select Year';
		opElmt2.text='Select Year';
		addOptions(electionYearsEl1,opElmt1);
		addOptions(electionYearsEl2,opElmt2);
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



function buildLeadersTableForNONUrbanAreas(results){

if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();
		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' MUNCIPALITY BOOTH LEVEL CASTE DETAILS </h4>';
		//str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;"></h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Selected Castes</th>';
		str += '<th>Booth</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Selected Castes</th>';
		str += '</tr>';
		for(var i in results)
		{
			str += '<tr>';
			var rowLength = results[i].boothLevelLeadersList.length;
			str += '<td rowspan='+rowLength+'>'+results[i].mandalName+' Muncipality </td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].boothTotalVoters+'</td>'; 
			str += '<td rowspan='+rowLength+' >';
			for(var j in results[i].panchayatLevelLeadersList)
			{
				str += ' '+results[i].panchayatLevelLeadersList[j].casteName +' ('+results[i].panchayatLevelLeadersList[j].casteVotersPerc+')  '; 
			}
			str += '</td>';
			str += '<td rowspan='+rowLength+'> ';
			
			for(var j in results[i].selectedCastesList)
			{
				str += ''+results[i].selectedCastesList[j].casteName +'('+results[i].selectedCastesList[j].casteVotersPerc+')  '; 
			}
			
			str +='</td>';
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
					str += ' '+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteName+' ('+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteVotersPerc+')  ';
				}
				str += '</td>';
				str += '<td>';
				for(var m in results[i].boothLevelLeadersList[k].selectedCastesList)
				{
					str += ' '+results[i].boothLevelLeadersList[k].selectedCastesList[m].casteName+' ('+results[i].boothLevelLeadersList[k].selectedCastesList[m].casteVotersPerc+')  '; 
				}
				str +='</td>';
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
		$('#leadersTable1').html(str);
	}


}
function showSuggestedLocations(myResults,jsObj){
 var str ='';
 if(myResults != null && myResults.length > 0 && myResults[0].suggestedLocations != null && myResults[0].suggestedLocations.length > 0){
	str+='<div class="widget blue">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">Order OF Priority to Target Geographically </h4>';
    //str+='<h4  style="border-radius: 4px 4px 4px 4px; margin-top: 10px; padding-bottom: 10px; margin-bottom: 10px; padding-top: 10px; color: white; background-color: rgb(6, 171, 234); height: 22px;">Order OF Priority to Target Geographically </h4>';
	str+='<table  class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	str+='  <tr>';
	str+='    <th>Panchayat Name</th>';
	str+='    <th>Priority</th>';
	str+='  </tr>';
	 for(var i in myResults[0].suggestedLocations){
	   str+='<tr>';
	   str+='  <td>'+myResults[0].suggestedLocations[i].name+'</td>';
	   str+='  <td>'+myResults[0].suggestedLocations[i].priorityOrder+'</td>';
	   str+='</tr>';
	 }
	str+='</table>';
	str+= '</div>';
	str+= '</div>';
 }
 $("#suggestedLocationsDiv").html(str);
}

function panchayatMatrx(result)
{
	//alert(123);
	//debugger;
	var latestYearDetails = result[0].partyPositionVOList.reverse();
	//console.log(latestYearDetails);
	var preYearDetails = result[1].partyPositionVOList.reverse();
	//console.log(preYearDetails);
	var pachayatIds = new Array();
	var partyName = $('#partySelectEl option:selected').text();
	var str = '';
	str+='<div class="widget green">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>';
	str += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
	str += '<tr>';
	str += '<th>';
	var year = '';
	for(var x in result)
	{
		year += result[x].name + '/';
		
	}
	str += ''+year.slice(0,-1)+'</th>';
	for(var a in latestYearDetails)
	{
		str += '<th style="background: none repeat scroll 0% 0% '+latestYearDetails[a].tempVar+';">'+latestYearDetails[a].name+'</th>';	
	}
	str += '</tr>';

	for(var i in latestYearDetails)
	{
		str += '<tr>';
		str += '<td style="background: none repeat scroll 0% 0% '+latestYearDetails[i].tempVar+';">'+latestYearDetails[i].name+'</td>';
		var latestYearPanchayatData = latestYearDetails[i].partyPositionVOList;
		for(var j in preYearDetails)
		{
			str += '<td>';
			var preYearPanchayatData = preYearDetails[j].partyPositionVOList;
			for(var m in latestYearPanchayatData)
			{
				
				for(var n in preYearPanchayatData)
				{
					if(latestYearPanchayatData[m].id == preYearPanchayatData[n].id)
					{
						str += latestYearPanchayatData[m].name +'</br>';
					}
				}
				
			}
			str += '</td>';
		} 
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	str += '</div>';
	//alert(str);
	$('#matrixDiv').html(str);
	//console.log(pachayatIds);
	//alert(pachayatIds);
}
</script>
</head>
<body>
<div id="suggestiveMainDiv" align="center">
  <!--<div id="titleHeading" align="center"> SUGGESTIVE MODEL </div>-->
 <c:if test="${!hideMainMenu}"> 
  <div class="widget blue">
  <div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px; height: 450px;" class="widget-block">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">SUGGESTIVE MODEL</h4>
   <div id="mainDiv" align="center" style="margin-left: 100px;">
     <div id="errorMsgDiv" >&nbsp;</div><br><br>
     <div style="width: 500px; float: left;margin-bottom: 5px;">
		<table>
			<tr id="tableRowS">
				<td id="tdWidth">
					Constituency Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listConstituencyNames" onchange="clearAll(),getPartyDetails(this.options[this.selectedIndex].value),getCandidateCastes(this.options[this.selectedIndex].value);;">
					<option value="0"> Select Constituency </option>
					</select>
				</td>		
		<!--	
			<td id="tdWidth">
					Mandal Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listMandalNames" onchange="getPartyDetails(this.options[this.selectedIndex].value);">
					<option value="0"> Select Mandal </option>
					</select>
				</td>	
		-->
			</tr>
	</table>		
</div>
<br><br>
<div style="width: 500px; float: left;margin-bottom: 5px;">
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
<br><br>
<div style=" margin-bottom: 5px;float: left; margin-left: 82px;">
<table>
		<tr>
			<td id="tdWidth">
				Election Year :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="electionYearSelectEl1" onchange="validateYear1(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
				<option value="0"> Select Year </option>
				</select>
			</td>
			<td Style="padding-left: 15px;">
				<select id="electionYearSelectEl2" onchange="validateYear2(this.options[this.selectedIndex].value)">
				<option value="0"> Select Year </option>
				</select>
			</td>
		</tr>
	</table>
</div></br>
<div style=" margin-bottom: 5px;float: left; margin-left: 82px;">
	<span style="float: left; margin-left: 1px; padding-top: 13px; padding-bottom: 13px;">Please Check For Excepted Caste Details <input type="checkbox" name="expCaste" id="expCaste" value="" onclick="showExpCasteDetailsButton();"><br></span></br>
	<table>
		<tr>
			<td id="tdWidth">
				Caste Names :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="candidateCastes" multiple="multiple" >
				<option value="0"> Select Caste </option>
				</select>
			</td>	
			<td>
				<input type="button" id="expCasteButton" value="CASTE PERCENTAGES" class="btn-success" onClick="ckeckForExpCasteDetails()" style="height: 29px; border-radius: 4px 4px 4px 4px; margin-left: 59px;display:none;"></input>
			</td>
		</tr>
	</table>
</div>

<div id="selCastesDisplayDiv">
<div id="sublevelSelectionDiv"></div>
</div>
<div style="margin-left:80px;margin-top:70px;">
<div id="ageGroupWiseId">
	<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
		<div class="pull-left" style="margin:10px;font-weight:bold;">Young Voters -</div><div class="fromDiv">
			From
			<div class="inputDiv"><input type="text" id="fromTxt" class="fromInput" style="width:80px;" value="18"/></div>
		</div>
		<div class="toDiv">
			To
			<div class="inputDiv"><input type="text" id="toTxt" class="toInput" style="width:80px;" value="22"/></div>
		</div>
		<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="25px" style="display:none;"/></div>
	</div>
	<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
		<div class="pull-left" style="margin:10px;font-weight:bold;">Old Voters     -</div><div class="fromDiv" style="margin-left:30px;">
			From
			<div class="inputDiv"><input type="text" id="fromTxt" class="fromInput" style="width:80px;" value="60"/></div>
		</div>
		<div class="toDiv">
			To
			<div class="inputDiv"><input type="text" id="toTxt" class="toInput" style="width:80px;" value="120"/></div>
		</div>
		<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="25px" style="display:none;"/></div>
	</div>
</div>
</div>
<div style="clear:both;">
	<span id="errorMsg"></span>
</div>

<div id="scrollBtnDiv">
	<div style="position: fixed; left :0px; top: 190px;z-index:1;">
	   <img src="images/up_Arrow .png" id="pageUpBtn" width="30" title="click here to scroll up page"/>
	</div>
	<div style="position: fixed; left :0px; top: 240px;z-index:1;">
		<img src="images/down_Arrow.png" id="pageDownBtn" width="30" title="click here to scroll down page"/>
	</div>

 </div>


<div id="partyPerformanceBtnDiv" style="margin-bottom: 4px;float: left; width: 980px;">
<input type="button" id="getPartyPer" value="Submit" class="btn btn-success" style="margin-bottom: 10px; margin-top: 10px;" onclick="clearAll(),casteDetailsByPanchayatId(),getLeadersList(),getAgeGroupWiseResults(),getConstituencyType(),getPanchayatWiseResultsForAllPartiesOfAConstituency();getSelPartyPerformanceAction();"/>

<img src="images/icons/search.gif" id="ajaxImg" style="display:none;"/>
<!--<img src="images/icons/loading.gif" id="ajaxLoaderImg" height="25px" width="25px;" style="display:none;"/>-->
</div>

</div>
</div></div>
</c:if>
<span id="dashBoardImgLoading" style="display:none;"><img src="images/icons/goldAjaxLoad.gif"/></span>
<div id="leadersTable"></div>
<span id="dashBoardImgLoadingNew" style="display:none;"><img style="margin-top:20px;" src="images/icons/goldAjaxLoad.gif"/></span>
<div id="leadersTable1"></div>

<div id="leadersTable2"></div>

<div id="matrixDiv"></div>
<div id="suggestedLocationsDiv"></div>
<div class="widget green" id="panchayatWisePollingPercMainDiv" style="display:none;">
  <div id="panchayatWisePollingPercHeadingDiv"></div>
  <div id="panchayatWisePollingPercentageDiv" class="row-fluid"></div>
</div>
<div id="partyPerformanceMainDiv">
   <div id="partyPerformanceInnerDiv"></div>
   <div id="partyPerformanceBoothDiv" style="display:none;"></div>
</div>

<div id="strongPollingPerDiv" class="row-fluid" style="display:none;">
    <div id="strongPollingPercentageDiv" class="span6"></div>
</div>
<div id="weakPollingPerDiv" class="row-fluid" style="display:none;">
<div id="weakPollingPercentageDiv" class="span6"></div>
</div>
<div id="addedVotesDib" class="row-fluid">
<div id="addedVoterDetailsDiv" class="span6"></div>
</div>

<!--<div id="deletedVotersInfo">

</div>-->

 


<!--<div style="clear:both;margin-top:10px;">
	<span id="addMoreBtn" class="btn btn-info" >Add More</span>
	<span id="getAgeGroupWiseResults" class="btn btn-info">Get Results</span>
	<span id="ajaxLoaderImg" style="display:none;"><img src="images/icons/loading.gif" height="30px" width="30px;"/></span>
</div>-->

<div style="background:#fff;">
	<span id="ajaxLoaderImg" style="display:none;background:#ECECEC;"><img src="images/icons/goldAjaxLoad.gif"/></span>
	<div class="titleageGroupTableId1Cls"><div id="titleageGroupTableId1" ></div><div id="ageGroupTableId1"  style="margin:20px;"></div></div>
	<div class="titleageGroupBoothTableId1Cls"><div id="titleageGroupBoothTableId1"></div><div id="ageGroupBoothTableId1" style="margin-top:10px;"></div></div>
	<div class="titleageGroupTableId2Cls"><div id="titleageGroupTableId2"></div><div id="ageGroupTableId2" style="margin-top:10px;"></div></div>
	<div class="titleageGroupBoothTableId2Cls"><div id="titleageGroupBoothTableId2"></div><div id="ageGroupBoothTableId2" style="margin-top:10px;"></div></div>
	<div class="titleageGroupTableId3Cls"><div id="titleageGroupTableId3"></div><div id="ageGroupTableId3" style="margin-top:10px;"></div></div>
	<div class="titleageGroupBoothTableId3Cls"><div id="titleageGroupBoothTableId3"></div><div id="ageGroupBoothTableId3" style="margin-top:10px;"></div></div>
</div>


<div id="titleDiv" style="display:none;">
<h4>PANCHAYAT WISE ELECTION RESULTS COMPARISION</h4>
</div>
<div id="newPartyDiv">
</div>
<img src="images/icons/loading.gif" id="ajaxLoaderImgForNewPartyDiv" height="25px" width="25px;" style="display:none;"/>

<div id="conclusionStatements" style="margin-left:177px;margin-top:34px;"></div>
</div>

<form id="ExceptedCasteDetailsDiv" method="post" action="getLeadersDataAction.action" name="exceptedCasteDetailsForm">
<input type="hidden" name="task" id="getAllExpCasteDetails" /></form>
<!--
<div id="titleDiv" style="display:none;">
<h4>PANCHAYAT WISE ELECTION RESULTS COMPARISION</h4>
</div>
<div id="newPartyDiv">
</div>
<img src="images/icons/loading.gif" id="ajaxLoaderImgForNewPartyDiv" height="25px" width="25px;" style="display:none;"/>

<div id="conclusionStatements" style="margin-left:177px;margin-top:34px;"></div>
-->
<script>
var noOfClicks = 0;
$(document).ready(function(){

$("#pageUpBtn").live("click",function(){
	if(noOfClicks < 0 || noOfClicks > 10)
		noOfClicks = 10;
		if(noOfClicks == 0)
			window.scroll(0,findPos(document.getElementById("leadersTable")));
		else if(noOfClicks == 1)
			window.scroll(0,findPos(document.getElementById("leadersTable")));
		else if(noOfClicks == 2)
			window.scroll(0,findPos(document.getElementById("leadersTable1")));
		else if(noOfClicks == 3)
			window.scroll(0,findPos(document.getElementById("leadersTable2")));
		else if(noOfClicks == 4)
			window.scroll(0,findPos(document.getElementById("matrixDiv")));
		else if(noOfClicks == 5)
			window.scroll(0,findPos(document.getElementById("suggestedLocationsDiv")));
		else if(noOfClicks == 6)
			window.scroll(0,findPos(document.getElementById("panchayatWisePollingPercMainDiv")));
		else if(noOfClicks == 7)
			window.scroll(0,findPos(document.getElementById("weakPollingPerDiv")));
		else if(noOfClicks == 8)
			window.scroll(0,findPos(document.getElementById("addedVotesDib")));
		else if(noOfClicks == 9)
			window.scroll(0,findPos(document.getElementById("titleageGroupTableId1")));
		else if(noOfClicks == 10)
			window.scroll(0,findPos(document.getElementById("titleDiv")));
		else
			window.scroll(0,findPos(document.getElementById("leadersTable")));
			
		noOfClicks = noOfClicks-1;

});

$("#pageDownBtn").live("click",function(){
		if(noOfClicks < 0 || noOfClicks > 9)
		noOfClicks = 0;
		if(noOfClicks == 0)
			window.scroll(0,findPos(document.getElementById("leadersTable")));
		else if(noOfClicks == 1)
			window.scroll(0,findPos(document.getElementById("leadersTable1")));
		else if(noOfClicks == 2)
			window.scroll(0,findPos(document.getElementById("leadersTable2")));
		else if(noOfClicks == 3)
			window.scroll(0,findPos(document.getElementById("matrixDiv")));
		else if(noOfClicks == 4)
			window.scroll(0,findPos(document.getElementById("suggestedLocationsDiv")));
		else if(noOfClicks == 5)
			window.scroll(0,findPos(document.getElementById("panchayatWisePollingPercMainDiv")));
		else if(noOfClicks == 6)
			window.scroll(0,findPos(document.getElementById("weakPollingPerDiv")));
		else if(noOfClicks == 7)
			window.scroll(0,findPos(document.getElementById("addedVotesDib")));
		else if(noOfClicks == 8)
			window.scroll(0,findPos(document.getElementById("titleageGroupTableId1")));
		else if(noOfClicks == 9)
			window.scroll(0,findPos(document.getElementById("titleDiv")));
		else
			window.scroll(0,findPos(document.getElementById("titleDiv")));

	noOfClicks = noOfClicks+1;
});

function findPos(obj) {
    var curtop = 0;
    if (obj.offsetParent) {
        do {
            curtop += obj.offsetTop;
        } while (obj = obj.offsetParent);
    return [curtop];
    }
}



});

function ckeckForExpCasteDetails()
{
	var checkStatus = $('#expCaste').is(":checked");
	if(checkStatus == true)
	{
		getMandalsAndPanchayts();
		
	}
}
var noOfCastesSelected ;
var selectedCastes = new Array();
function getSelectedCastes()
{
	$('#selCastesDisplayDiv').html('');
	selectedCastes = new Array();
	var casteId ;
	var casteName;
	var constituencyId = $('#listConstituencyNames option:selected').val();
	$('#candidateCastes :selected').each(function(i, selected){ 
		var casteDetails = {};
        casteDetails["casteId"] =  $(this).val();
		casteDetails["casteName"] =  $(this).text();
		selectedCastes.push(casteDetails);
	});
	noOfCastesSelected = selectedCastes.length;
		var str = '';
		for(var i in selectedCastes)
		{
			var casteId = selectedCastes[i].casteId;
			var casteName = selectedCastes[i].casteName;
			str += '<b>'+casteName+'</b>' ;
			str += '<table class="table table-hover table-bordered expCasteDetails'+i+'">';
			str += '<th>PANCHAYAT</th>';
			str += '<th>MANDAL</th>';
			str += '<th>EXCEPTED  %</th>';
			str += '<th>ACTIONS</th>';
			
			for(j in madalPanchayatsArray)
			{
			str += '<tr>';
				var panchayatId   = madalPanchayatsArray[j].panchayatId;
				var panchayatName = madalPanchayatsArray[j].panchayatname;
				var mandelId      = madalPanchayatsArray[j].mandalId;
				var mandelName    = madalPanchayatsArray[j].mandalName;
				
				str += '<td><input type="hidden" value="'+panchayatId+'" id="panchayaId'+i+''+j+'" class="panchayatIdClass"></input><input id="panchayaName'+i+''+j+'" class="'+casteId+''+constituencyId+'" type="text" style="width:100px;" value="'+panchayatName+'" readonly="readonly"></input></td>';
				str += '<td><input type="hidden" value="'+mandelId+'" id="mandalId'+i+''+j+'" class="mandalIdClass"></input><input id="mandalName'+i+''+j+'" class="'+casteId+''+mandelId+'" type="text" style="width:100px;" value="'+mandelName+'" readonly="readonly"></input></td>';
				str += '<td><input class="expPercClass" id="expPerc'+i+''+j+'" id="" type="text" style="width:100px;"></input></td>';
				str += '<td><span><a class="icon-ok" title ="Apply To  Mandal" onClick="applyExpCasteToSelectedMandal(\''+casteId+''+mandelId+'\','+i+','+j+',\'expPerc\');"></a></span><span> <a class=" icon-ok-sign" title = "Apply to Constituency"><i class="icon-ok" onClick="applyExpCasteToConstituency(\''+casteId+''+constituencyId+'\','+i+','+j+',\'expPerc\');"></i></a></span></td>';
			str += '</tr>';
			}
			str += '</table>';
		}
		str += '<div><input type="button" class="btn-success" value="Get" onClick="getAllExpcetedCasteDetails(\''+constituencyId+'\');"></input></div>';
		$('#selCastesDisplayDiv').append(str);
		$('#selCastesDisplayDiv').dialog({
			 width: 700,
			 height:500
		});
	
} 

function applyExpCasteToSelectedMandal(value,i,j,id)
{
	var expPercentage = $('#'+id+''+i+''+j+'').val();
	$('.'+value+'').each(function() {
		$(this).closest("tr").find('.expPercClass').val(expPercentage);
	});
	
}
function applyExpCasteToConstituency(value,i,j,id)
{
	var expPercentage = $('#'+id+''+i+''+j+'').val();
	$('.'+value+'').each(function() {
		$(this).closest("tr").find('.expPercClass').val(expPercentage);
	});
}
var expCasteArray = new Array();
function getAllExpcetedCasteDetails(id)
{
		expCasteArray = new Array();
		for(var i = 0 ; i < noOfCastesSelected ; i++)
		{
			var casteName = selectedCastes[i].casteName;
			var casteId = selectedCastes[i].casteId;
			$('.'+casteId+''+id+'').each(function() {
			var casteDetails = {};
			var mandalId = $(this).closest("tr").find('.mandalIdClass').val();
			var panchayatId = $(this).closest("tr").find('.panchayatIdClass').val();
			var expPerc = $(this).closest("tr").find('.expPercClass').val();
			casteDetails["panchayatId"]  =    panchayatId,
			casteDetails["casteId"]      =    casteId,
			casteDetails["expPerc"]      =     expPerc
			expCasteArray.push(casteDetails);
			});
		}
}
function getMandalsAndPanchayts()
{
	var constituencyId = $('#listConstituencyNames option:selected').val();
	var jsObj = {
	        constituencyId:constituencyId,
			task:"getMandalsAndPanchayts"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getMandalsAndPanchaytsAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
}
var madalPanchayatsArray = new Array();
function storeMandalPanchayatValues(result)
{
	if(result != null && result.length > 0)
	{
		for(var i in result)
		{
			var mandalPanchayatDetails = {};
			mandalPanchayatDetails["panchayatId"]   =  result[i].panchayatId;
			mandalPanchayatDetails["panchayatname"] =  result[i].panchayatname;
			mandalPanchayatDetails["mandalId"]      =  result[i].mandaId;
			mandalPanchayatDetails["mandalName"]    =  result[i].mandalName;
			madalPanchayatsArray.push(mandalPanchayatDetails);
		}
		getSelectedCastes();
	}
}
function showExpCasteDetailsButton()
{
	var checkStatus = $('#expCaste').is(":checked");
	if(checkStatus == true)
	{
		$('#expCasteButton').show();
	}
	else
	{
		$('#expCasteButton').hide();
	}
}
function buildLeadersTableWithExpPerc(results)
{
	$('#leadersTable').html('')
	if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();

		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' CONSTITUENCY BOOTH LEVEL CASTE DETAILS </h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Total Voters</th>';
		//str += '<th>Major Castes</th>';
		
			for(var b in results[0].exceptdCateDetails)
			{
				str += '<th>'+results[0].exceptdCateDetails[b].name+'</th>';
				str += '<th>Excepted Perc</th>';
				str += '<th>Excepted Votes</th>';
			}

		str += '</tr>';

		for(var i in results)
		{
		try{
			str += '<tr>';
			str += '<td>'+results[i].mandalName+'</td>'; 
			str += '<td>'+results[i].panchayatName+'</td>'; 
			str += '<td>'+results[i].panchayatTotalVoters+'</td>'; 
			for(var k in results[i].exceptdCateDetails)
			{
				str += '<td>'+results[i].exceptdCateDetails[k].count+'</td>';
				str += '<td>'+results[i].exceptdCateDetails[k].perc+'</td>';
				str += '<td>'+results[i].exceptdCateDetails[k].expCount+'</td>';
			}
			
			str += '</tr>';
			}catch(e){
			//console.log(e);
			}
		}
		
		str += '</table>';
		str += '</div>';
		str += '</div>';

		$('#leadersTable').html(str);
	}
	
}
function buildLeadersTable(results)
{
	$('#leadersTable').html('')
	if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();

		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' CONSTITUENCY BOOTH LEVEL CASTE DETAILS </h4>';

		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Selected Castes</th>';
		str += '<th>Booth</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Selected Castes</th>';
		str += '</tr>';

		for(var i in results)
		{
		try{
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
			str += '<td rowspan='+rowLength+'>';
			
			for(var j in results[i].selectedCastesList)
			{
				str += ' '+results[i].selectedCastesList[j].casteName +' ('+results[i].selectedCastesList[j].casteVotersPerc+')  '; 
			}
			str +='</td>';
			
			
			if(results[i].boothLevelLeadersList != null && results[i].boothLevelLeadersList.length > 0){
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
					str += ' '+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteName+' ('+results[i].boothLevelLeadersList[k].boothLevelLeadersList[m].casteVotersPerc+')  '; 
				}
				str += '</td>';
				str += '<td>';
				
				for(var m in results[i].boothLevelLeadersList[k].selectedCastesList)
				{
					str += ' '+results[i].boothLevelLeadersList[k].selectedCastesList[m].casteName+'  ('+results[i].boothLevelLeadersList[k].selectedCastesList[m].casteVotersPerc+')'; 
				}
				str +='</td>';
				if(k > 0)
				{
					str += '</tr>';
				}
			}
			}
			
			str += '</tr>';
		}catch(e){
		//console.log(e);
		}
		}
		
		str += '</table>';
		str += '</div>';
		str += '</div>';

		$('#leadersTable').html(str);
	}
	
}
function getSelPartyPerformanceAction(){
   var eleIds = new Array();
  <c:if test="${!prevElecResults}">
	  var constituencyId = $('#listConstituencyNames option:selected').val();
	  var mandalId = $('#listMandalNames option:selected').val();
	  var partyId = $('#partySelectEl option:selected').val();
	  var eleId1 = $('#electionYearSelectEl1 option:selected').val();
	  var eleId2 = $('#electionYearSelectEl2 option:selected').val();
	  
	  eleIds.push(eleId1);
	  eleIds.push(eleId2);
	  if(constituencyId == 0)
	  return;
  
    $("#ajaxImg").css("display","inline-block");
  </c:if>
  <c:if test="${prevElecResults}">
  var constituencyId = '${constituencyId}';
      eleIds.push("${fromYear}");
	  eleIds.push("${toYear}");
	  var partyId = "${partyId}";
  </c:if>
	var jsObj = {
	        constituencyId:constituencyId,
			electionId:eleIds,
			partyId:partyId,
			//locationId:2844, //mandal Id
			//locationType:"mandal",
			tempVar:"",
			task:"getPartyPerformanceReport"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getPartyPerformanceAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
		
  }
/* function showPartyPerformanceReport(result,jsObj)
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
	
	str +='<div style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
    str +='<tr>';
	str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">Type</th>';
	
	for(var i in result)
	  str +='<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">'+result[i].name+'</th>';
	
	str +='</tr>';

    var length = result[0].partyPositionVOList.length;

	
   for(var j=0;j<length;j++)
   {
	var strengthType = result[0].partyPositionVOList[j].name;
     str +='<tr>';
	str +='<td style="background: none repeat scroll 0% 0% '+result[0].partyPositionVOList[j].tempVar+';font-weight:bold;">'+strengthType+'</td>';

	
	  
	 var panchayatIdsArray = new Array();

     str +='<td>';
	  
	  if(result[0].partyPositionVOList[j].partyPositionVOList.length > 0)
	  {
		 str +='<table id="panchayatTab" style="width:100%">';

		 str +='<tr>';
		 str +='<th>Panchayat</th>';
         str +='<th>Total Votes</th>';
		 str +='<th>Votes Polled</th>';
		 str +='<th>Polling %</th>';
    	 str +='<th>Margin</th>';
		  str +='<th> Votes Gained('+partyName+')</th>';
		 str +='</tr>';
		   
		 for(var k in result[0].partyPositionVOList[j].partyPositionVOList)
		 {
		   panchayatIdsArray.push(result[0].partyPositionVOList[j].partyPositionVOList[k].id);

		   str +='<tr>';
		   str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[k].name+'</td>';
		   str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[k].totalVoters+'</td>';
		   str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[k].totalValidVotes+'</td>';
		   str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[k].percentage+'</td>';

		   str +='<td><span style="background: none repeat scroll 0% 0% '+result[0].partyPositionVOList[j].tempVar+'" class="spanCls">'+result[0].partyPositionVOList[j].partyPositionVOList[k].margin+'</span></td>';

		  str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[k].selectedPartyTotalVoters+'</td>';
		    
			str +='</tr>';
		  }

		   str +='</table>';
		}

	  str +='</td>';

	if(result.length > 1)
	{
	  str +='<td>';
      var t = 0;
	  for(var n=0;n<panchayatIdsArray.length;n++)
		for(var k=0;k<result[1].partyPositionVOList.length;k++)
		  for(var m=0;m<result[1].partyPositionVOList[k].partyPositionVOList.length;m++)
			if(panchayatIdsArray[n] == result[1].partyPositionVOList[k].partyPositionVOList[m].id)
			  t ++;

	  
      if(t > 0)
	  {
	  	 str +='<table id="panchayatTab1">';
		 str +='<tr>';
		 str +='<th>Panchayat</th>';
         str +='<th>Total Votes</th>';
		 str +='<th>Votes Polled</th>';
		 str +='<th>polling %</th>';
    	 str +='<th>Margin</th>';
		 str +='<th>Votes Gained('+partyName+')</th>';
		 str +='</tr>';
		 
		 
		 for(var n=0;n<panchayatIdsArray.length;n++)
		 {
		  
          for(var k=0;k<result[1].partyPositionVOList.length;k++)
		  {
			for(var m=0;m<result[1].partyPositionVOList[k].partyPositionVOList.length;m++)
			{
			  str +='<tr>';
              if(panchayatIdsArray[n] == result[1].partyPositionVOList[k].partyPositionVOList[m].id)
			  {
				str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].name+'</td>';
				str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].totalVoters+'</td>';
				str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].totalValidVotes+'</td>';
				str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].percentage+'</td>';

				str +='<td><span style="background: none repeat scroll 0% 0% '+result[1].partyPositionVOList[k].tempVar+'" class="spanCls">'+result[1].partyPositionVOList[k].partyPositionVOList[m].margin+'</span></td>';

				
				 str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].selectedPartyTotalVoters+'</td>';
			  }


			  str +='</tr>';
			}
		  }
          
		 }
		   
	  str +='</table>';
	  }
      str +='</td>'; 
	}
		
	str += '</tr>';
  }
	str += ' </table>';
	str += '</div>';
	str += '</div>';
	str += '</div>';
	$("#partyPerformanceInnerDiv").html(str);

}*/



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
	
	str +='<div style="margin-top:12px;">';
	str +='<span class="yearSpan" style="margin-right: 530px;">'+result[0].name+'</span>';
	str +='<span class="yearSpan">'+result[1].name+'</span>';
	str +='</div>';
	str +='<div style="overflow-x:scroll;">';
	str +='<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
    str +='<tr>';
	str +='<th>Type</th>';
	str +='<th>Panchayat</th>';
	str +='<th>Total Votes</th>';
	str +='<th>Votes Polled</th>';
	str +='<th>Polling %</th>';
	str +='<th>Margin</th>';
	str +='<th>Votes Gained('+partyName+')</th>';

	str +='<th>Panchayat</th>';
	str +='<th>Total Votes</th>';
	str +='<th>Votes Polled</th>';
	str +='<th>Polling %</th>';
	str +='<th>Margin</th>';
	str +='<th>Votes Gained('+partyName+')</th>';
	
	str +='</tr>';

    var length = result[0].partyPositionVOList.length;
    
	
   for(var j=0;j<length;j++)
   {
	  
     var listSize1 = result[0].partyPositionVOList[j].partyPositionVOList.length;
	
		 
	 var trFlag = false;
	
	 var strengthType = result[0].partyPositionVOList[j].name;
     
	
    if(result[0].partyPositionVOList[j].partyPositionVOList.length == 0)
	{
		str +='<tr>';
	    str +='<td style="background: none repeat scroll 0% 0% '+result[0].partyPositionVOList[j].tempVar+';font-weight:bold;">'+strengthType+'</td>';
		str +='<td></td><td></td><td></td><td></td><td></td><td></td>';
		str +='<td></td><td></td><td></td><td></td><td></td><td></td>';
		str +='</tr>';

	}

	else if(result[0].partyPositionVOList[j].partyPositionVOList.length > 0)
	{
	  str +='<tr>';
	  str +='<td rowspan="'+listSize1+'" style="background: none repeat scroll 0% 0% '+result[0].partyPositionVOList[j].tempVar+';font-weight:bold;">'+strengthType+'</td>';
	   for(var r=0;r<listSize1;r++)
	   {
		  var panchayatId = result[0].partyPositionVOList[j].partyPositionVOList[r].id;
		  var inFlag = true;

		   if(trFlag)
			 str +='<tr>';

				str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].name+'</td>';
				str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].totalVoters+'</td>';
				str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].totalValidVotes+'</td>';
				str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].percentage+'</td>';
				str +='<td><span style="background: none repeat scroll 0% 0% '+result[0].partyPositionVOList[j].tempVar+'" class="spanCls">'+result[0].partyPositionVOList[j].partyPositionVOList[r].margin+'</span></td>';
				str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].selectedPartyTotalVoters+'</td>';

				for(var k=0;k<result[1].partyPositionVOList.length;k++)
				{
					for(var m=0;m<result[1].partyPositionVOList[k].partyPositionVOList.length;m++)
					{
					 if(result[1].partyPositionVOList[k].partyPositionVOList[m].id==panchayatId)
					 {
					    inFlag = false;
					    str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].name+'</td>';
					    str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].totalVoters+'</td>';
						str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].totalValidVotes+'</td>';
						str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].percentage+'</td>';
						str +='<td><span style="background: none repeat scroll 0% 0% '+result[1].partyPositionVOList[k].tempVar+'" class="spanCls">'+result[1].partyPositionVOList[k].partyPositionVOList[m].margin+'</span></td>';
						str +='<td>'+result[1].partyPositionVOList[k].partyPositionVOList[m].selectedPartyTotalVoters+'</td>';
					 }
					}
				}
				if(inFlag)
				 str +='<td></td><td></td><td></td><td></td><td></td><td></td>';

				str +='</tr>';
		  	     trFlag = true;
					
		}
	}//else if

   }//for loop

	str += ' </table>';
	str += '</div>';
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
  //alert(StrongPollingPerList);
  //alert(weakPollingPerList);
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
  $("#strongPollingPerDiv").show();
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
  $("#weakPollingPerDiv").show();
}
function buildAddedVotersDetails(result)
{
	var z = 0;
	var str = "";
	var myResult = new Array();
	var addedVoterDetails = result[0].addedVoterDetails;
	for(var i in addedVoterDetails)
	{
		if(addedVoterDetails[i].addedVotersPresent == true)
		{
			myResult.push(addedVoterDetails[i]);
		}
	}
		if(myResult != null && myResult.length > 0)
		{
			str += '<table >';
			for(var j in myResult)
			{
				if(z%2 == 0)
				{
					str += '<tr>';
				}
				str+='<td valign="top" style="padding-left:10px;"><div class="widget blue">';
				str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;" class="widget-block">';
				str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;width:450px;color: black;" class="">'+myResult[j].name+ ' AND ADDED VOTERS ARE MORE</h4>';
				str += '<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';
				str += '<tr>';
				str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">PANCHAYAT</th>';
				str += '<th style="background: none repeat scroll 0 0 #D9EDF7;color: #454545;">ADDED VOTERS</th>';
				str += '<tr>';
				for(var m in myResult[j].partyPositionVOList)
				{
					if(myResult[j].partyPositionVOList[m].addedVotersCount != null && myResult[j].partyPositionVOList[m].addedVotersCount > 0)
					{
							str += '<tr>';
							str += '<td>'+myResult[j].partyPositionVOList[m].name+'</td>';
							str += '<td>'+myResult[j].partyPositionVOList[m].addedVotersCount+'</td>';
							str += '</tr>'; 
					}
				}
				str += '</table>';
				str += '</div>';
				str += '</div><td>';
				if((z-1)%2 == 0)
				{
					str += '<tr>';
				}
				z++;
			}
			if(z%2 == 0)
			{ 
				str += '</tr>';
			}
			str += '</table>';
		}
	$('#addedVoterDetailsDiv').html(str);
}
/* We are Not using this method
 
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

*/
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

function showPartyPerformancePieChart(result,jsObj)
{
  
  $('#panchayatWisePollingPercentageDiv').html('');
  $('#panchayatWisePollingPercHeadingDiv').html('');
  if(result == null || result.length == 0)
   return;
  $("#panchayatWisePollingPercMainDiv").css("display","block");
  var partyName = $('#partySelectEl option:selected').text();

  $('#panchayatWisePollingPercHeadingDiv').html('<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE REPORT</h4>');

  for(var i in result)
  {
   var divEle = '<div id="partyPerformance'+i+'" class="span6"></div>'; 
    $("#panchayatWisePollingPercentageDiv").append(divEle);
     
   
   var results = result[i].partyPositionVOList;
   var data = new google.visualization.DataTable();
        data.addColumn('string', 'name');
        data.addColumn('number', 'value');
      
		data.addRows(results.length);

		for(var j = 0 ; j< results.length ; j++){		
		var name = results[j].name;
		var val = parseFloat(results[j].rangePercentage);
		  data.setValue(j,0,name);
		  data.setValue(j,1,val);
		}
        // Set chart options
		var title = ''+result[i].name+' PANCHAYAT WISE '+partyName+' PARTY PERFORMANCE'; 
        var options = {'title':title,
                       'width':450,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('partyPerformance'+i));
        chart.draw(data, options);
	       
  }
       
}

//getDeletedVotersInfo();
function casteDetailsByPanchayatId(){
	var constituencyId = $('#listConstituencyNames option:selected').val();
	var candidateCastes = $('#candidateCastes').val();
	 
	if(constituencyId == 0)
	 return ;	 
var jsObj= 
	{	
		constituencyId:constituencyId,
		candidateCastes:candidateCastes,
		publicationId:8,
		task:"getCasteDetails"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getCasteDetailsByPanchayatAction.action?"+param;
	callAjax(param,jsObj,url);
}
function buildCasteDetails(results){
var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();
	if(results != null && results.length > 0)
	{
		$("#leadersTable2").css("display","block");
		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' CONSTITUENCY HAMLET LEVEL CASTE DETAILS </h4>';
		str += '<table class="table table-hover table-bordered" style="color: black; font-family: verdana; font-size: 12px; font-weight: lighter; margin-top: 15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Selected Castes</th>';
		str += '<th>Hamlet</th>';
		str += '<th>Total Voters</th>';
		str += '<th>Major Castes</th>';
		str += '<th>Selected Castes</th>';
		str += '</tr>';
		for(var i in results)
		{
			
			str += '<tr>';
			var rowLength = results[i].hamletVoterInfo.length;
			str += '<td rowspan='+rowLength+'>'+results[i].mandalName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].name+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].count+'</td>'; 
			str += '<td rowspan='+rowLength+' >';
			for(var j in results[i].panchayatVoterInfo)
			{
				str += ''+results[i].panchayatVoterInfo[j].casteName +'('+results[i].panchayatVoterInfo[j].persent+')  '; 
			}
			str += '</td>';

			str += '<td rowspan='+rowLength+' >';
			for(var j in results[i].selectedCasteDetails)
			{
				str += ''+results[i].selectedCasteDetails[j].casteName +'('+results[i].selectedCasteDetails[j].persent+')  '; 
			}
			str += '</td>';

			for(var k in results[i].hamletVoterInfo)
			{
				if(k > 0)
				{
					str += '<tr>';
				}
				str += '<td>'+results[i].hamletVoterInfo[k].name+'</td>';
				str += '<td>'+results[i].hamletVoterInfo[k].count+'</td>';
				str += '<td>';
				for(var m in results[i].hamletVoterInfo[k].hamletCasteInfo)
				{
					str += ''+results[i].hamletVoterInfo[k].hamletCasteInfo[m].name+'('+results[i].hamletVoterInfo[k].hamletCasteInfo[m].persent+')  '; 
				}
					str += '</td>';

				str += '<td>';
				for(var m in results[i].hamletVoterInfo[k].selectedCasteDetails)
				{
					str += ''+results[i].hamletVoterInfo[k].selectedCasteDetails[m].name+'('+results[i].hamletVoterInfo[k].selectedCasteDetails[m].persent+')  '; 
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
		$('#leadersTable2').html(str);
	}
}
//casteDetailsByPanchayatId();

var count=0;
	$('#addMoreBtn').click(function(){
		
		if($('.fromToDivClass').length==2){
			$('#addMoreBtn').css('display','none');
		}
		var template=$('.fromToDivTemplateClass');
		var templateClone=template.clone();
		
		count=count+1;
		templateClone.removeClass('fromToDivTemplateClass');
		templateClone.attr('id','fromToDivId'+count);
		
		
		templateClone.find('.fromDiv').html('From <div class="inputDiv"><input type="text" id="fromTxt" class="fromInput"/></div>');
		templateClone.find('.toDiv').html('To <div class="inputDiv"><input type="text" id="toTxt" class="toInput"/></div>');
		templateClone.find('.closeImgDiv').html('<img src="images/close.png" height="25px" width="25px"/>');
		
		templateClone.appendTo('#ageGroupWiseId');
		
	});
	
	$('.closeImgDiv').live('click',function(){
		$(this).closest('.fromToDivClass').remove();
		if($('.fromToDivClass').length<=2){
			$('#addMoreBtn').css('display','inline-block');
		}
	});
	
	
	var valuesArr;
	
	function getAgeGroupWiseResults(){
		var electionIds = new Array();
		var agesArr=[];
		var selectedCasteIds = new Array();
		<c:if test="${!youngVoters && !oldVoters}">
		var k=validateAndPush();
		
		if(k!=0){
			if($('#errorMsg').html()==""){
				$('#errorMsg').html('Invalid Input..Please Give valid Input');
			}
			$('#errorMsg').css('display','inline-block');
			return;
		}
		$('#ajaxLoaderImg').css('display','inline-block');
		
		
		if(valuesArr.length==1){
			var val1=valuesArr[0]['from'];
			var val2=valuesArr[0]['to'];
			agesArr.push(val1);
			agesArr.push(val2);
		}
		if(valuesArr.length>1){
			var val1=valuesArr[0]['from'];
			var val2=valuesArr[0]['to'];
			var val3=valuesArr[1]['from'];
			var val4=valuesArr[1]['to'];
			agesArr.push(val1);
			agesArr.push(val2);
			agesArr.push(val3);
			agesArr.push(val4);
		}
		if(valuesArr.length>2){
			var val5=valuesArr[2]['from'];
			var val6=valuesArr[2]['to'];
			agesArr.push(val5);
			agesArr.push(val6);
		}
		
		var constituencyId = $('#listConstituencyNames option:selected').val();
		if(constituencyId==0){
		$('#errorMsg').html('Please Select the Constituency');
		}
		
		
		$('#candidateCastes :selected').each(function(i,selected){
		selectedCasteIds.push($(this).val());
		});
		
		
		
		electionIds.push($('#electionYearSelectEl1').val());
		electionIds.push($('#electionYearSelectEl2').val());
		partyId = $('#partySelectEl').val();
		</c:if>
		<c:if test="${youngVoters || oldVoters}">
		    var constituencyId = '${constituencyId}';
		    partyId = 1;
		    agesArr.push('${fromAge}');
			agesArr.push('${toAge}');
			if('${casteIds}'.length > 0){
			  selectedCasteIds = '${casteIds}'.split(",");
			}
		</c:if>
		var jsObj = {
			
	        constituencyId:constituencyId,
			electionIds:electionIds,
			partyId:partyId,
			locationId:0, // panchayathId
			locationType:"panchayat",
			tempVar:"all",
			task:"getAgeGroupWiseReport",
			agesList:agesArr,
			castesSelcted:selectedCasteIds
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getAgeGroupWiseReportAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
	}	
  
		
		
		
	
	function validateAndPush(){
	var errorFree=0;
	valuesArr=[];
	$( ".fromToDivClass" ).each(function (i) {	
			
			var valuesList={
                 from:$(this).find('.fromInput').val(),
			     to:$(this).find('.toInput').val()
             };
			 var from=valuesList['from'];
			 var to=valuesList['to'];
			 
			 
			 var constituencyId = $('#listConstituencyNames option:selected').val();
				if(constituencyId==0){
					$('#errorMsg').html('Please Select the Constituency');
					errorFree=1;
					return;
				}
			 
			 if($.isNumeric(from) && $.isNumeric(to)){
				if(!(parseInt(from)>17 && parseInt(from)<=140) || !(parseInt(to)>17 && parseInt(to)<=140)){
					$('#errorMsg').html('Age should be between 18 & 140');
					errorFree=1;
					return;
				}
			 	if(parseInt(from) >= parseInt(to)){
					$('#errorMsg').html('Invalid Input..From Age > To Age');
					errorFree=1;
					return;
				}else{
					valuesArr.push(valuesList);
					$('#errorMsg').html('');
				}
			 }else{
					if(constituencyId==0){
						$('#errorMsg').html('Please Select the Constituency');
						errorFree=1;
						return;
					}
					else{
						$('#errorMsg').html('Invalid Input..Please Enter only Numerics');
						errorFree=1;
						return;
					}
			 }
		});
		return errorFree;
	}
	
	
		 $(".fromToDivClass input").live('blur',function(){
			var value=$(this).val();
			
			var numStatus=$.isNumeric(value);
			
			if(numStatus==false){
				$('#errorMsg').html('Invalid Input..Please Enter only Numerics');
				return;
			}
			else{
				$('#errorMsg').html('');
			}
			
			if(!(parseInt(value)>17 && parseInt(value)<140)){
				$('#errorMsg').html('Age should be between 18 & 140');
				return;
			}
		});
	
	function buildAgeGroupWiseTable(myResults,jsObj){
		$('#ageGroupTableId1').html('');
		$('#ageGroupTableId2').html('');
		$('#ageGroupTableId3').html('');
		$('#ageGroupBoothTableId1').html('');
		$('#ageGroupBoothTableId2').html('');
		$('#ageGroupBoothTableId3').html('');
		buildHeadBodyForTable(myResults,jsObj);
		
	}
	
	function buildHeadBodyForTable(myResults,jsObj){
		var tablesCount=myResults.length;
		
		if(tablesCount==1){
			createTable(myResults[0],'ageGroupTableId1');
			
			$.each(myResults[0].municipalitesBoothsMap, function(key, value){
				createBoothsTable(value,'ageGroupBoothTableId1',key);
			}); 
			
		}
		else if(tablesCount==2){
			for(var i=0;i<tablesCount;i++){
				var num=i+1;
				createTable(myResults[i],'ageGroupTableId'+num);
				
				$.each(myResults[i].municipalitesBoothsMap, function(key, value){
					createBoothsTable(value,'ageGroupBoothTableId'+num,key);
				});
			}
			
						
		}
		else{
			for(var i=0;i<tablesCount;i++){
				var num=i+1;
				createTable(myResults[i],'ageGroupTableId'+num);
				
				$.each(myResults[i].municipalitesBoothsMap, function(key, value){
					createBoothsTable(value,'ageGroupBoothTableId'+num,key);
				});
			}
		}
	}
	
	
	function createTable(result,tableId){
	$('#title'+tableId).css('display','block');
	$('#title'+tableId).html('<h4>Panchayat Wise Voters Analysis of Age Range - '+result.ageRange+'</h4>');
		var str='';
		<!--str+='<div style="width:800px" align="center"><h4>Panchayat Wise Voters Analysis of Age Range - +'+result.ageRange+'</h4></div>'-->
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead><tr><th rowspan=2>Panchayat</th><th rowspan=2>Total Voters In Panchayat</th><th colspan=4>'+result.ageRange+'</th><th rowspan=2>Top Castes</th><th rowspan=2>Selected Castes</th></tr>';
		str+='<tr><th>Total Voters</th><th>Male Voters</th><th>Female Voters</th>	<th>Percentage</th></tr></thead>';
		str+='<tbody>';
		var res=result.panchayatList;
		for(var i in result.panchayatList){
		str+='<tr>';
		str+='<td>'+result.panchayatList[i].panchayatName+'</td>';
		str+='<td>'+result.panchayatList[i].totalPanchayatVoters+'</td>';
		str+='<td>'+result.panchayatList[i].totalVoters+'</td>';
		str+='<td>'+result.panchayatList[i].maleVoters+'</td>';
		str+='<td>'+result.panchayatList[i].femaleVoters+'</td>';
		str+='<td>'+result.panchayatList[i].percentage+'</td>';
		str+='<td>';
		var topCastesLength=result.panchayatList[i].topCastes.length;
		for(var j in result.panchayatList[i].topCastes){
		str+=result.panchayatList[i].topCastes[j].castName+"("+result.panchayatList[i].topCastes[j].castCount+")";
			 if(topCastesLength>j){
				str+=", ";
			}
		}
		str+='</td>';
		str+='<td>';
			var slctdCastesLength=result.panchayatList[i].selectedCastes.length;
			if(slctdCastesLength>0){
		for(var j in result.panchayatList[i].selectedCastes){
		str+=result.panchayatList[i].selectedCastes[j].castName+"("+result.panchayatList[i].selectedCastes[j].castCount+")";
			 if(slctdCastesLength>j){
				str+=", ";
			}
		}}
		else{
			str+="-";
		}
		str+='</td>'
		str+='</tr>';
		}
		str+='</tbody></table>';
		
		$('#'+tableId).html(str);
		$('#table'+tableId).dataTable({
			"iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
		});
		
		$("."+tableId+"Cls").css({
			'padding' : '25px',
			'margin' : '5px',
			'border':'1px solid #cccccc'
		});
	}
	
	function createBoothsTable(result,tableId,mncplName){
	$('#title'+tableId).css('display','block');
	$('#title'+tableId).html('<h4>'+mncplName+'- Booth Wise Voters Analysis of Age Range - '+result[0].ageRange+'</h4>');
		var str='';
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead><tr><th rowspan=2>Booths</th><th rowspan=2>Total Voters In Booth</th><th colspan=4>'+result[0].ageRange+'</th><th rowspan=2>Top Castes</th><th rowspan=2>Selected Castes</th></tr>';
		str+='<tr><th>Total Voters</th><th>Male Voters</th><th>Female Voters</th>	<th>Percentage</th></tr></thead>';
		str+='<tbody>';
		for(var i in result){
		var vstr="";
		vstr+='<tr>';
		vstr+='<td>'+result[i].panchayatName+'</td>';
		vstr+='<td>'+result[i].totalPanchayatVoters+'</td>';
		vstr+='<td>'+result[i].totalVoters+'</td>';
		vstr+='<td>'+result[i].maleVoters+'</td>';
		vstr+='<td>'+result[i].femaleVoters+'</td>';
		vstr+='<td>'+result[i].percentage+'</td>';
		vstr+='<td>';
		var topCastesLength=result[i].topCastes.length;
		for(var j in result[i].topCastes){
		vstr+=result[i].topCastes[j].castName+"("+result[i].topCastes[j].castCount+")";
			 if(topCastesLength>j){
				vstr+=", ";
			}
		}
		vstr+='</td>';
		vstr+='<td>';
		var slctedCastesLength=result[i].selectedCastes.length;
		if(slctedCastesLength>0){
		for(var j in result[i].selectedCastes){
		vstr+=result[i].selectedCastes[j].castName+"("+result[i].selectedCastes[j].castCount+")";
			 if(slctedCastesLength>j){
				vstr+=", ";
			}
		}
		}
		else{
			vstr+="-";
		}
		vstr+='</td>';
		vstr+='</tr>';
		
		str+=vstr;
		}
		str+='</tbody></table>';
		
		$('#'+tableId).html(str);
		$('#table'+tableId).dataTable({
			"iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
		});
		
		$("."+tableId+"Cls").css({
			'padding' : '25px',
			'margin' : '5px',
			'border':'1px solid #cccccc'
		});
		
	}

function getPanchayatWiseResultsForAllPartiesOfAConstituency(){
       $('#newPartyDiv').html('');
		if($('#listConstituencyNames').val() == "0")
			return false;
$('#ajaxLoaderImgForNewPartyDiv').show();
     var jsObj= 
	{	
        constituencyId:$('#listConstituencyNames').val(),
		partyName : $('#partySelectEl option:selected').text(),
		task:"getEffectOfNewParty"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getEffectOfNewPartyOnTraditionalParties.action?"+param;
	callAjax(param,jsObj,url);

}
function buildnewPartyEffectResults(results)
{
	$('#titleDiv').show();
    $('#ajaxLoaderImgForNewPartyDiv').hide();
	var party = $('#partySelectEl option:selected').text();
  var parties = new Array();
	parties.push("INC");
	parties.push("TDP");
	parties.push("IND");
	parties.push("PRP");

	/*var i=0;
	 $.each(results,function(key,value){
         $.each(value,function(key1,value1){
            if(i == 0)
		     parties = value1.considerableParties;
			i++;
	      });
	 });*/




   var str='';

  str+='<table class="table table-bordered table-striped table-hover" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;">';

  //THIS IS FOR HEADING START
   str+='<tr>';
    
	str+='<th rowspan="2">PANCHAYAT NAME</th>';

   for(var i in parties)
   {
     str+='<th colspan="3">'+parties[i]+'</th>';
   }
   str+='<th rowspan="2">PRP EFFECT ON '+party+' PARTY</th>';
   str+='</tr>';
   for(var i in parties)
   {
     str+='<th>2004</th>';
	 str+='<th>2009</th>';
	 str+='<th>DIFF</th>';
	 /* if(parties[i] == 'PRP')
	 {
		str+='<th>Votes</th>';
	 } */
   }
   str+='</tr>';

     //THIS IS FOR HEADING END


	 $.each(results,function(key,value){

		 str+='<tr>';
		  str+='<td>'+key+'</td>';

		   for(var i in parties)
		   {
			   if(value[parties[i]] == undefined)
			   {
				str+='<td>---</td>';
				str+='<td>---</td>';
				str+='<td>---</td>';

			   }else{

				str+='<td>'+value[parties[i]].previousElectionVotesPercent+'</td>';
				str+='<td>'+value[parties[i]].presentElectionVotesPercent+'</td>';
				if(value[parties[i]].difference != "--")
				  str+='<td>'+parseFloat(value[parties[i]].difference).toFixed(2)+'</td>';
				else
					 str+='<td>--</td>';
				if(parties[i] == 'PRP')
				{
					var value = parseFloat(value[parties[i]].fromPrpVoters).toFixed(2)
					if(value == 'NaN')
					str+='<td>--</td>';
					else
					str+='<td>'+value+'</td>';
					
				}
			   }

		   }

		 str+='</tr>';

	 });
  str+='</table>';


	$('#newPartyDiv').html(str);
}	 
<c:if test="${hideMainMenu}">
  <c:if test="${castDetails}">
    $("#dashBoardImgLoading").show();
    getLeadersList();
  </c:if>
  <c:if test="${castDetails}"> 
   
   getConstituencyType();
  </c:if>
  <c:if test="${youngVoters || oldVoters}"> 
     $("#dashBoardImgLoading").show();
	 getAgeGroupWiseResults();
  </c:if>	
  <c:if test="${prevElecResults}">
    $("#dashBoardImgLoading").show();
    getSelPartyPerformanceAction();
  </c:if>
</c:if>	   
</script>
</body>
</html>
