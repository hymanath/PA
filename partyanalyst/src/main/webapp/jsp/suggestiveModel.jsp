<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Suggestive Model</title>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
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
	<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
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
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
		<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>


	<!-- YUI Dependency files (End) -->


<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});

</script>
<style type="text/css">	
	
	#populationCensusDiv table,#populationCensusDiv table th,#populationCensusDiv table td{	
	text-align:center;
	border:1px solid #DDDDDD;
	}
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
	#paginationDivId{float: none;
    margin-left: auto;
    margin-right: auto;
  
    width: 900px;}
	

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
#Castetable th{
	background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
	}
.headingCls{ color: #005580;
    font-size: 15px;
    margin-bottom: 10px;}

.fromDiv {
    float: left;
    margin-bottom: 12px;
    margin-left: 56px;
    margin-top: 10px;
}

.toDiv {
    float: left;
    margin-bottom: 12px;
    margin-left: 18px;
    margin-top: 10px;
}
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

#titleageGroupBoothTableId1,#titleageGroupTableId1,#titleageGroupBoothTableId2,#titleageGroupTableId2,#titleageGroupBoothTableId3,#titleageGroupTableId3{clear:both;padding:15px 10px;display:none;color:#000000;}

#panchayatWisePollingPercentageDiv{padding-top: 9px; padding-bottom: 21px;}

html{overflow-x: hidden;}
.spanCls{padding: 3px 5px; border-radius: 3px;}
#panchayatTab th,#panchayatTab1 th{background:#D9EDF7;color: #454545;}
.dataTables_info {text-align:left;}
.yearSpan{font-size: 18px;
    font-weight: bold;}

#partyPerformanceInnerDiv table th{background: none repeat scroll 0 0 #D9EDF7;color: #454545;}
.dataTables_wrapper {
    overflow-x: scroll;
}
.paginate_disabled_previous,.paginate_enabled_previous,.paginate_enabled_next{
   padding-bottom: 10px;
}
</style>


<script type="text/javascript" >
var constituencyType ;
var latestPublicationId;
$(document).ready(function(){
 <c:if test="${!hideMainMenu}">
  getConstituencyList();
  
 </c:if>
	 getLatestPublicationId();
});

function getConstituencyCensusDetails(){

    var jsObj= 
	{	
		constituencyId:$('#listConstituencyNames').val(),
		task:"censusDetails"
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCensusDetailsForAConstituency.action?"+param;
	callAjax(param,jsObj,url);
}

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
	
	$('#assemblyElectionDiv').css("display","none");
	$('#parliamentElectionDiv').css("display","none");
	$('#mptcZptcElectionDiv').css("display","none");
	$("#voterAgeGroupDiv").css("display","none");
	$("#voterAgeGroupDiv1").css("display","none");
	$("#voterAgeGroupDiv2").css("display","none");
	
	$('.titleageGroupTableId1Cls').removeClass('widget').removeClass('blue');
	$('.titleageGroupBoothTableId1Cls').removeClass('widget').removeClass('blue');
	$('.titleageGroupTableId2Cls').removeClass('widget').removeClass('blue');
	$('.titleageGroupBoothTableId2Cls').removeClass('widget').removeClass('blue');
	$('.titleageGroupTableId3Cls').removeClass('widget').removeClass('blue');
	$('.titleageGroupBoothTableId3Cls').removeClass('widget').removeClass('blue');

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
	mandalId : mandalId,
	task:"getPartyDetails"
	}	
	var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getParticipatedPartyForSuggestiveAction.action?"+param;
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
	if(constituencyType == 'RURAL-URBAN' || constituencyType == 'RURAL' || constituencyType == 'URBAN')
	{
		var checkStatus = $('#expCaste').is(":checked");
		if(checkStatus == false)
		{
			expCasteArray = new Array();
		}
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
				constituencyId   : constituencyId,
				casteIds         : casteIds,
				expCasteArray    : expCasteArray,
				expCasteArrayForMuncipality  : expCasteArrayForMuncipality,
				checkStatus      : checkStatus,
				constituencyType : constituencyType,
				publiationId     : $('#listPublicationValues').val(),
				task             : "getLeadersList"		
			};
			 $("#getAllExpCasteDetails").val(YAHOO.lang.JSON.stringify(jsObj));
					  
				var uploadHandler = {
				success: function(o) {
				var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
				 $("#dashBoardImgLoading").hide();
				 if(checkStatus == false)
				 {
					if(jsObj.constituencyType == 'URBAN')
					{
						buildLeadersTableForUrban(uploadResult);
					}
					else
					{
						buildLeadersTable(uploadResult);
					}
					
				 }
				 else 
				 {
					 if(jsObj.constituencyType == 'URBAN')
					{
						buildLeadersTableWithExpPercForUrban(uploadResult);
					}
					else
					{
						buildLeadersTableWithExpPerc(uploadResult);
					} 
				 }
				}
				};
				YAHOO.util.Connect.setForm('exceptedCasteDetailsForm',false);
				YAHOO.util.Connect.asyncRequest('POST','getLeadersDataAction.action',uploadHandler);
	}
	
	if(constituencyType == 'RURAL-URBAN' || constituencyType == 'RURAL')
	{
		getVotersCountsForPanchayats();
		getDelimationEffect();
	}

}

function getLeadersListInRuralUrbans(){
var casteIds=0;
var checkStatus = $('#expCaste').is(":checked");
if(checkStatus == false)
{
	expCasteArrayForMuncipality = new Array();
}
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
		constituencyId              : constituencyId,
		casteIds                    : casteIds,
		checkStatus                 : checkStatus,
		expCasteArrayForMuncipality : expCasteArrayForMuncipality,
		publicationId               : $('#listPublicationValues').val(),
		task                        : "getLeadersListInRuralUrbans"		
	};
	 $("#muncipalityExpCasteDetails").val(YAHOO.lang.JSON.stringify(jsObj));
			  
		var uploadHandler = {
		success: function(o) {
		var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
		 $("#dashBoardImgLoadingNew").hide();
		 if(checkStatus == false)
		 {
			buildLeadersTableForNONUrbanAreas(uploadResult);
		 }
		 else 
		 {
			buildLeadersTableWithExpPercForMuncipal(uploadResult);
		 }
		}
		};
		YAHOO.util.Connect.setForm('muncipalityexceptedCasteDetailsForm',false);
		YAHOO.util.Connect.asyncRequest('POST','getLeadersListInRuralUrbansAction.action',uploadHandler);
}

function getCandidateCastes(constituencyIds){
	var constituencyId = $('#listConstituencyNames').val();
	var publicationId = $('#listPublicationValues').val();
	var jsObj ={
		constituencyId : constituencyId,
		publicationId : publicationId,
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
		var publicationId = $("#listPublicationValues option:selected").val();
		var jsObj=
				{
					constituencyId : constituencyId,
					publicationId  : publicationId,
					task           : "getConstituencyType"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getReportLevelDetails.action?"+rparam;	

		callAjax(rparam,jsObj,url);
}

function getLatestPublicationId()
	{
	    var constituencyId = $("#listConstituencyNames option:selected").val();
		if(constituencyId == 0)
		return;
		var jsObj=
				{
					constituencyId : constituencyId,
				
					task           : "getLatestPublication"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getLatestPublicationByConstituencyAction.action?"+rparam;	

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
						else if(jsObj.task == "getPublicationDate")
								{
								$('#publicationAjaxImage').css('display','none');

									buildPublicationDateList(myResults);
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
					      if(myResults[0].partyPositionVOList.length > 0 && myResults[0].constituencyType != "urban")
							showPartyPerformanceReport(myResults,jsObj);
							if(myResults[0].boothwisePartyPositionVOList.length > 0)
							showPartyPerformanceReportForBooth(myResults,jsObj,myResults[0].constituencyType);
							//showStrongAndWeakPollingPercentage(myResults,jsObj);
							
							//buildAddedVotersDetails(myResults);
							if(myResults[0].constituencyType != "urban"){
							 showPartyPerformancePieChart(myResults,jsObj);
							 showSuggestedLocations(myResults,jsObj);
							 panchayatMatrx(myResults);
							}
						}
						else if(jsObj.task == "getDeletedVotersInfo")
						{
							buildDeletedVotersInfo(myResults);
						}
						else if(jsObj.task == "getAgeGroupWiseReport"){
							$('#ajaxLoaderImg').css('display','none');
							$("#dashBoardImgLoading").hide();
							if(myResults != null && myResults.length > 0){
								for(var i in myResults){
									if(myResults[i].ageRange == '60> & <120'){
										myResults[i].ageRange = "ABOVE 60";
									}
									if(myResults[i].ageRange == '18> & <22'){
										myResults[i].ageRange = "18 TO 22";
									}
							    }	
							}
							buildAgeGroupWiseTable(myResults,jsObj);
						}
						else if(jsObj.task == "getEffectOfNewParty"){
							buildnewPartyEffectResults(myResults);
						}
						else if(jsObj.task== "getUserAssignedVoterCastes"){
							buildUserAssignedVotersCastes(myResults);
						}
						else if(jsObj.task== "getConstituencyType"){
							/* if(myResults[0].name == "RURAL-URBAN")
								getLeadersListInRuralUrbans(); */
								constituencyType = myResults[0].name;
								<c:if test="${castDetails}"> 
		                            $("#dashBoardImgLoading").show();
                                    getLeadersList();
		                        </c:if>
						}
						/* else if (jsObj.task== "getLeadersListInRuralUrbans"){
						    $("#dashBoardImgLoadingNew").hide();
							buildLeadersTableForNONUrbanAreas(myResults);					
						} */
						else if (jsObj.task== "getMandalsAndPanchayts"){
							storeMandalPanchayatValues(myResults);
						}
						else if(jsObj.task== "getPollingPercentages")
						{
							buildPollingHighPercentageForBooths(myResults);
							buildPollingLowPercentageForBooths(myResults);
						}
						else if(jsObj.task== "getVoterDetailsByPartNo")
						{
							buildAddedVoterDetails(myResults,jsObj);
						}
						
						else if(jsObj.task== "getVoterscountInPanchayats")
						{
							buildVoterscountInPanchayats(myResults);
						}
						
						else if(jsObj.task== "getSelectedCountPAnchayatsDetails")
						{
							buildgetSelectedCountPAnchayatsDetails(myResults);
						}
						else if(jsObj.task== "getConstituencyBasicCountInfo")
						{
							buildConstituencyBasicCountInfo(myResults);
						}
						
						else if(jsObj.task== "getDelimationEffect")
						{
							buildgetDelimationEffect(myResults);
						}
						else if(jsObj.task== "getCastInfo")
						{
						buildCasteInfo(myResults,jsObj);
						}
						else if(jsObj.task== "censusDetails")
						{
							buildPopulationCensusTable(myResults,jsObj);
						}
						else if(jsObj.task=="partiesPerformanceInDiffElection")
						{
					buildAssemblyPartiesElection(myResults,jsObj);
					buildParliamentPartiesElection(myResults,jsObj);
					buildMptcZptcPartiesElection(myResults,jsObj);
						}
						else if(jsObj.task=="getVoterAgeGroupResults"){
							buildVoterAgeGroupResults(myResults,jsObj);
						}
						else if(jsObj.task=="getAgewiseVoterDetails"){
							buildAgewiseVoterDetails(myResults,jsObj);
						}
						else if(jsObj.task=="getLatestPublication")
						{
							latestPublicationId = myResults;
							buildDataFunction(myResults);
						
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

function buildDataFunction(LatestpublicationId)
{
	getConstituencyType();
	getCasteBasicInfo();
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
	for(var i in results.partiesInMandal)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results.partiesInMandal[i].id;
		opElmt.text=results.partiesInMandal[i].name;
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
	str+='    <th><div style="margin-left:190px">Panchayat</div></th>';
	str+='    <th><div style="margin-left:190px">Priority</div></th>';
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
  <div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px; height: 560px;" class="widget-block">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">SUGGESTIVE MODEL</h4>

 <div class="well" style="float:left;width:300px;margin-top:70px;height:auto;">

  <h4>TOOLS</h4>
 <input type="button" value="Get FamilyWise Details" class="btn btn-info btn-block" id="familyWisebtn" onclick="redirectToFamilyInfo()" style="margin-top:20px;"/>
 <input type="button" value="Get Panchayat FamilyWise Details" class="btn btn-info btn-block" id="familyWisebtn" onclick="redirectTopanchayatFamilyInfo()" style="margin-top:10px;"/>
<!-- <span>Caste Wise Info</span>-->
 <input type="button" value="Get Mandal Wise CasteInfo" class="btn btn-info btn-block" id="mandalcasteInfobtn" onclick="redirectToCasteInfo('mandal')" style="margin-top:10px;"/>
 <input type="button" value="Get Panchayat Wise CasteInfo" class="btn btn-info btn-block" id="panchayatcasteInfobtn" onclick="redirectToCasteInfo('panchayat')" style="margin-top:10px;"/>
 <input type="button" value="Get Booth Wise CasteInfo" class="btn btn-info btn-block" id="boothcasteInfobtn" onclick="redirectToCasteInfo('booth')" style="margin-top:10px;"/>
  <input type="button" value="Get Hamlet Wise CasteInfo" class="btn btn-info btn-block" id="hamletcasteInfobtn" onclick="redirectToCasteInfo('hamlet')" style="margin-top:10px;"/>
  <input type="button" value="Get Voter Address" class="btn btn-info btn-block" id="voterAddressbtn" onclick="redirectToVoterAddress()" style="margin-top:10px;"/>
   <input type="button" value="Get Voter Locations" class="btn btn-info btn-block" id="voterrangebtn" onclick="redirectToVoterRange()" style="margin-top:10px;"/>

  </div>
   <div id="mainDiv" align="center"  style="margin-left: 196px;">
     <div id="errorMsgDiv" >&nbsp;</div><br><br>
     <div style="width: 500px; float: left;margin-bottom: 5px;">
		<table>
			<tr id="tableRowS">
				<td id="tdWidth">
					Constituency Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listConstituencyNames" onchange="clearAll(),getLatestPublicationId();getPartyDetails(this.options[this.selectedIndex].value);getCandidateCastes(this.options[this.selectedIndex].value);getPublicationDate();">
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
				Publication Date:<font id="requiredValue" class="requiredFont">*</font> 
			</td>
			<td><select id="listPublicationValues" class="selectWidth"  name="listPublicationValues" onchange="getConstituencyBasicCountInfo();">
			<option value="0">Select Publication Date</option>
			</select>
			</td>
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
				From Year :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="electionYearSelectEl1" onchange="validateYear1(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);">
				<option value="0"> Select Year </option>
				</select>
			</td>
		</tr>
		<tr>
		<td id="tdWidth">
				To Year :<font id="requiredValue" class="requiredFont">*</font> 
			</td>	
			<td>
				<select id="electionYearSelectEl2" onchange="validateYear2(this.options[this.selectedIndex].value)">
				<option value="0"> Select Year </option>
				</select>
			</td>
		</tr>
	</table>
</div>
<div style=" margin-bottom: 5px;float: left; margin-left: 82px;" id="casteInfoDiv">
	
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
</tr>
<tr><td></td>
		<td><input type="checkbox" name="expCaste" id="expCaste" value="expCaste" onclick="showExpCasteDetailsButton();" style="margin-top: 0px;"><span id="ecpCheckBox" style="margin-left:10px;">Please Check Here For Expected Castes</span></td>
		
		</tr>
	</table>
	<input type="button" id="expCasteButton" value="Add Expected Votes Percentage Caste Wise" class="btn-success" onClick="ckeckForExpCasteDetails()"  style="border-radius: 4px 4px 4px 4px; height: 21px; margin-top: 7px; margin-left: 348px;display:none;"></input>
</div>
<div style=" margin-bottom: 5px;float: left; margin-left: 82px;">
<div id="ageGroupWiseId" style="display:none;">
<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
<div class="pull-left"  style="margin-left: -2px; float: left; padding-top: 10px;">Young Voters :</div><div class="fromDiv">
From
<div class="inputDiv"><input type="text" id="fromTxt" class="fromInput" style="width: 35px; height: 12px;" value="18"/></div>
</div>
<div class="toDiv">
To
<div class="inputDiv"><input type="text" id="toTxt" class="toInput" style="width: 35px; height: 12px;" value="22"/></div>
</div>
<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="35px" style="display:none;"/></div>
</div>
<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
<div class="pull-left"  style="margin-left: -2px; float: left; padding-top: 10px;">Old Voters :</div><div class="fromDiv" style="margin-left: 74px;">
From
<div class="inputDiv" ><input type="text" id="fromTxt" class="fromInput" style="width: 25px; height: 12px;" value="61"/></div>
</div>
<div class="toDiv">
To
<div class="inputDiv" ><input type="text" id="toTxt" class="toInput" style="width: 35px; height: 12px;" value="120"/></div>
</div>
<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="35px" style="display:none;"/></div>
</div>
</div>
</div>

<!--<div style=" margin-bottom: 5px;float: left; margin-left: 82px;">
<div id="ageGroupWiseId">
	<table>
		<tr>
			<td id="tdWidth">Young Voters : 
			</td>
			<td><span>From</span>
			<input type="text" id="fromTxt" class="fromInput"  style="height: 10px; width: 25px; margin-left: 16px;" value="18"/>
			<span  style="margin-left: 15px;">To</span>
			<input type="text" id="toTxt" class="toInput"  style="height: 10px; width: 25px; margin-left: 16px;" value="22"/>
			</td>
		</tr>
		<tr>
			<td id="tdWidth">Old Voters : 
			</td>
			<td><span>From</span>
			<input type="text" id="fromTxt" class="fromInput"  style="height: 10px; width: 25px; margin-left: 16px;" value="60"/>
			<span  style="margin-left: 15px;">To</span>
			<input type="text" id="toTxt" class="toInput"  style="height: 10px; width: 25px; margin-left: 16px;" value="120"/>
			</td>
		</tr>
	</table>
		<!--<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="25px" style="display:none;"/></div>
	<div style="clear:both;" class="fromToDivTemplateClass fromToDivClass" id="fromToDivId0">
		<div class="pull-left" >Old Voters : </div><div class="fromDiv" style="margin-left:30px;">
			From
			<div class="inputDiv"><input type="text" id="fromTxt" class="fromInput" style="width:25px;" value="60"/></div>
		</div>
		<div class="toDiv">
			To
			<div class="inputDiv"><input type="text" id="toTxt" class="toInput" style="width:25px;" value="120"/></div>
		</div>
		<div class="closeImgDiv pull-left" style="margin-top:12px;"><img src="images/close.png" height="25px" width="25px" style="display:none;"/></div>
	</div>
</div>
</div>-->


<div id="selCastesDisplayDiv">
<div id="sublevelSelectionDiv"></div>
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


<div id="partyPerformanceBtnDiv" style="float: left; margin-top: 17px; margin-left: 272px;">
<input type="button" id="getPartyPer" value="Submit" class="btn btn-success"  onclick="clearAll(),casteDetailsByPanchayatId(),getLeadersList(),getAgeGroupWiseResults(),getConstituencyType(),getPanchayatWiseResultsForAllPartiesOfAConstituency();getSelPartyPerformanceAction();getPollingPercentageForBooths();getConstituencyCensusDetails();"/>

<img src="images/icons/search.gif" id="ajaxImg" style="display:none;"/>
<!--<img src="images/icons/loading.gif" id="ajaxLoaderImg" height="25px" width="25px;" style="display:none;"/>-->
</div>

</div>
</div></div>
</c:if>
<div id="basicCountDiv" class="widget blue" style="display:none;"></div>
<div id="basicCasteInfoDiv" class="widget blue" style="display:none;"></div>
<span id="dashBoardImgLoading" style="display:none;"><img src="images/icons/goldAjaxLoad.gif"/></span>
<span id="dashBoardImgLoading1Lat" style="display:none;"><img src="images/icons/goldAjaxLoad.gif"/></span>
<div id="votersCountRageDiv" style="display:none"></div>
<div id="delimationEffectDiv"></div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
    <h3 id="myModalLabel">Panchayats</h3>
  </div>
  <div class="modal-body">
  <div id="processingImg"><img style="margin-top:20px;" src="images/icons/goldAjaxLoad.gif"/></div>
  <div id="pachayatListDiv"></div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
</div>

<div id="leadersTable"></div>
<span id="dashBoardImgLoadingNew" style="display:none;"><img style="margin-top:20px;" src="images/icons/goldAjaxLoad.gif"/></span>
<div id="leadersTable1"></div>

<div id="leadersTable2"></div>

<div id="leadersTableForUrban"></div>

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

<!--<div id="strongPollingPerDiv" class="row-fluid" style="display:none;">
    <div id="strongPollingPercentageDiv" class="span6"></div>
</div>
<div id="weakPollingPerDiv" class="row-fluid" style="display:none;">
<div id="weakPollingPercentageDiv" class="span6"></div>
</div>-->
<div id="strongPollingPerDiv" class="row-fluid" style="display:none;" >
   
</div>
<div id="weakPollingPerDiv" class="row-fluid" style="display:none;" >
   
</div>
<div id="addedVoterDetailsDiv1" class="row-fluid" style="display:none;" ></div><br>
<div id="voterDetailsDiv" style="display:none;"><div id="voterDetailsInnerDiv"></div><div id="paginationDivId"></div>
</div>
<!--<div id="addedVotesDib" class="row-fluid">
<div id="addedVoterDetailsDiv" class="span6"></div>
</div>-->

<!--<div id="deletedVotersInfo">

</div>-->

 


<!--<div style="clear:both;margin-top:10px;">
	<span id="addMoreBtn" class="btn btn-info" >Add More</span>
	<span id="getAgeGroupWiseResults" class="btn btn-info">Get Results</span>
	<span id="ajaxLoaderImg" style="display:none;"><img src="images/icons/loading.gif" height="30px" width="30px;"/></span>
</div>-->

<div>
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
<div id="newPartyDiv" style=" width: 988px;display:none;overflow-x:scroll;">
</div>
<img src="images/icons/loading.gif" id="ajaxLoaderImgForNewPartyDiv" height="25px" width="25px;" style="display:none;"/>

<div id="conclusionStatements" style="margin-left:177px;margin-top:34px;"></div>
<div id="censusMainDiv" style="background:#FFFFFF;overflow:scroll;"  >
<div id="populationCensusDiv" class="widget blue"> </div>
</div>

</div>

<form id="ExceptedCasteDetailsDiv" method="post" action="getLeadersDataAction.action" name="exceptedCasteDetailsForm">
<input type="hidden" name="task" id="getAllExpCasteDetails" /></form>

<form id="muncipalityExceptedCasteDetailsDiv" method="post" action="getLeadersListInRuralUrbansAction.action" name="muncipalityexceptedCasteDetailsForm">
<input type="hidden" name="task" id="muncipalityExpCasteDetails" /></form>
<form id="ageGroupCasteDetailsForm" method="post" action="getLeadersDataAction.action" name="ageGroupCasteDetailsForm">
<input type="hidden" name="task" id="ageGroupCasteDetailsId" /></form>
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
		//$('#ecpCheckBox').hide();
	}
}
var noOfCastesSelected ;
var selectedCastes = new Array();
var otherCastes = new Array();
var casteDetails = {};
casteDetails["casteId"] =  0;
casteDetails["casteName"] = "others";
otherCastes.push(casteDetails);
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
	for(var m in otherCastes)
	{
		var casteDetail = {};
        casteDetail["casteId"] =  otherCastes[m].casteId;
		casteDetail["casteName"] =  otherCastes[m].casteName;
		selectedCastes.push(casteDetail);
	}
	noOfCastesSelected = selectedCastes.length;
	var str = '';
	str += '<div id="expCasteErrorMsgDiv" style="color:red"></div></br>';
	str += '<div> <b style="color:red;">Hint : EXPECTED CASTE PERCENTAGE MUST BE BETWEEN 0 TO 1</b></div></br>';
	str += '<div><input type="button" class="btn btn-primary" value="Get values" onClick="getAllExpcetedCasteDetails(\''+constituencyId+'\');" style="position: fixed; margin-left: -16px; top: 254px;"></input></div>';

		var casteId = selectedCastes[0].casteId;
		//var casteName = selectedCastes[0].casteName;
		//var capsCasteName = casteName.toUpperCase();
		//str += '<div id="'+casteName+'div"><a  id="'+casteName+''+casteId+'"   onClick="showSelectedCasteFadeIn(\''+casteName+''+constituencyId+'\')" style="border-radius: 4px 4px 4px 4px; background-color: rgb(88, 172, 250); margin-left: 10px; padding: 10px 10px 11px; margin-bottom: 19px; float: left; width: 600px; cursor: pointer;" >CLICK HERE TO FILL THE <b style="color:black;">'+capsCasteName+' </b>CASTE EXPECTED CASTE DETAILS</a>';
		str += '</div></br>';
		str += '<div id="'+constituencyId+'" style="display: block; float: left; width: 645px;"><table class="table table-hover table-bordered expCasteDetails'+i+'" >';
		str += '<th>PANCHAYAT</th>';
		str += '<th>MANDAL</th>';
		//str += '<th colspan='+noOfCastesSelected+'>EXPECTED  %</th>';
		for(var q = 0 ; q < noOfCastesSelected ; q++)
		{
			str += '<th>'+selectedCastes[q].casteName+'</th>';
		}
		//str += '<th>ACTIONS</th>';
		
		for(var j in madalPanchayatsArray)
		{
			str += '<tr>';
			var panchayatId   = madalPanchayatsArray[j].panchayatId;
			var panchayatName = madalPanchayatsArray[j].panchayatname;
			var mandelId      = madalPanchayatsArray[j].mandalId;
			var mandelName    = madalPanchayatsArray[j].mandalName;
			str += '<td><input type="hidden" value="'+panchayatId+'" id="panchayaId'+i+''+j+'" class="panchayatIdClass"></input>';
			
			if(panchayatName != null)
			{
				str += '<input id="panchayaName'+i+''+j+'" class="'+casteId+''+constituencyId+'" type="text" style="width:100px;" value="'+panchayatName+'" readonly="readonly"></input></td>';
			}
			else
			{
				str += '<input id="panchayaName'+i+''+j+'" class="'+casteId+''+constituencyId+'" type="hidden" style="width:100px;" value="'+panchayatName+'" readonly="readonly"></input></td>';
			}
			str += '<td><input type="hidden" value="'+mandelId+'" id="mandalId'+i+''+j+'" class="mandalIdClass"></input><input id="mandalName'+i+''+j+'" class="'+casteId+''+mandelId+'" type="text" style="width:100px;" value="'+mandelName+'" readonly="readonly"></input></td>';
			for(var p = 0 ; p < noOfCastesSelected ; p++)
			{
				str += '<td><input class="expPercClass'+p+'" id="expPerc'+i+''+j+''+p+'" id="" type="text" style="width:50px;" value="0.0"></input>	<span><a class="icon-ok" title ="Apply To  Mandal" onClick="applyExpCasteToSelectedMandal(\''+casteId+''+mandelId+'\','+i+','+j+',\'expPerc\','+p+');"></a></span><span> <a class=" icon-ok-sign" title = "Apply to Constituency"><i class="icon-ok" onClick="applyExpCasteToConstituency(\''+casteId+''+constituencyId+'\','+i+','+j+',\'expPerc\','+p+');"></i></a></span>';
			}
			
			//str += '<td><span><a class="icon-ok" title ="Apply To  Mandal" onClick="applyExpCasteToSelectedMandal(\''+casteId+''+mandelId+'\','+i+','+j+',\'expPerc\');"></a></span><span> <a class=" icon-ok-sign" title = "Apply to Constituency"><i class="icon-ok" onClick="applyExpCasteToConstituency(\''+casteId+''+constituencyId+'\','+i+','+j+',\'expPerc\');"></i></a></span></td>';
			str += '</tr>';
			
		}
		
		str += '</table></div>';
		
	/* console.log(otherCastes);
	for(var i in otherCastes)
	{
		var casteId = otherCastes[i].casteId;
		var casteName = otherCastes[i].casteName;
		var capsCasteName = casteName.toUpperCase();
		str += '<div id="'+casteName+'div"><a  id="'+casteName+''+casteId+'"   onClick="showSelectedCasteFadeIn(\''+casteName+''+constituencyId+'\')" style="border-radius: 4px 4px 4px 4px; background-color: rgb(88, 172, 250); margin-left: 10px; padding: 10px 10px 11px; margin-bottom: 19px; float: left; width: 600px; cursor: pointer;" >CLICK HERE TO FILL THE <b style="color:black;">'+capsCasteName+' </b>CASTE EXPECTED CASTE DETAILS</a>';
		str += '</div></br>';
		str += '<div id="'+casteName+''+constituencyId+'" style="display: block; float: left; width: 645px;"><table class="table table-hover table-bordered expCasteDetails'+i+'" >';
		str += '<th>PANCHAYAT</th>';
		str += '<th>MANDAL</th>';
		str += '<th>EXPECTED  %</th>';
		str += '<th>ACTIONS</th>';
		
		for(var j in madalPanchayatsArray)
		{
			str += '<tr>';
			var panchayatId   = madalPanchayatsArray[j].panchayatId;
			var panchayatName = madalPanchayatsArray[j].panchayatname;
			var mandelId      = madalPanchayatsArray[j].mandalId;
			var mandelName    = madalPanchayatsArray[j].mandalName;
			
			str += '<td><input type="hidden" value="'+panchayatId+'" id="panchayaId'+i+''+j+'" class="panchayatIdClass"></input>';
			
			if(panchayatName != null)
			{
				str += '<input id="'+i+''+j+'panchayaName" class="'+casteId+''+constituencyId+'" type="text" style="width:100px;" value="'+panchayatName+'" readonly="readonly"></input></td>';
			}
			else
			{
				str += '<input id="'+i+''+j+'panchayaName" class="'+casteId+''+constituencyId+'" type="hidden" style="width:100px;" value="'+panchayatName+'" readonly="readonly"></input></td>';
			}
			str += '<td><input type="hidden" value="'+mandelId+'" id="'+i+''+j+'mandalId" class="mandalIdClass"></input><input id="'+i+''+j+'mandalName" class="'+casteId+''+mandelId+'" type="text" style="width:100px;" value="'+mandelName+'" readonly="readonly"></input></td>';
			str += '<td><input class="expPercClass" id="'+i+''+j+'expPerc"  type="text" style="width:100px;" value="0.0"></input></td>';
			str += '<td><span><a class="icon-ok" title ="Apply To  Mandal" onClick="applyOtherExpCasteToSelectedMandal(\''+casteId+''+mandelId+'\','+j+','+i+',\'expPerc\');"></a></span><span> <a class=" icon-ok-sign" title = "Apply to Constituency"><i class="icon-ok" onClick="applyOtherExpCasteToConstituency(\''+casteId+''+constituencyId+'\','+j+','+i+',\'expPerc\');"></i></a></span></td>';
			str += '</tr>';
			
		}
		
		str += '</table></div>';
		
	} */
	$('#selCastesDisplayDiv').append(str);
	for(var i in selectedCastes)
	{
		var casteId = selectedCastes[i].casteId;
		//var casteName = selectedCastes[i].casteName;
			$('#'+0+''+constituencyId+'').fadeOut();
	}
	

	$('#selCastesDisplayDiv').dialog({
		 title : "EXPECTED CASTE PERCENTAGES",
		 width: 750,
		 height:600
	});
}	
function showSelectedCasteFadeIn(divId)
{
	$('#'+divId+'').fadeToggle("slow");
} 

function applyExpCasteToSelectedMandal(value,i,j,id,p)
{
	var expPercentage = $('#'+id+''+i+''+j+''+p+'').val();
	$('.'+value+'').each(function() {
		$(this).closest("tr").find('.expPercClass'+p+'').val(expPercentage);
	});
	
}
function applyExpCasteToConstituency(value,i,j,id,p)
{
	var expPercentage = $('#'+id+''+i+''+j+''+p+'').val();
	$('.'+value+'').each(function() {
		$(this).closest("tr").find('.expPercClass'+p+'').val(expPercentage);
	});
}
function applyOtherExpCasteToSelectedMandal(value,j,i,id)
{
	var expPercentage = $('#'+i+''+j+''+id+'').val();
	$('.'+value+'').each(function() {
		$(this).closest("tr").find('.expPercClass').val(expPercentage);
	});
	
}
function applyOtherExpCasteToConstituency(value,j,i,id)
{
	var expPercentage = $('#'+i+''+j+''+id+'').val();
	$('.'+value+'').each(function() {
		$(this).closest("tr").find('.expPercClass').val(expPercentage);
	});
}
var expCasteArray = new Array();
var expCasteArrayForMuncipality = new Array();
function getAllExpcetedCasteDetails(id)
{
	expCasteArray = new Array();
	var flag = false;
	expCasteArrayForMuncipality = new Array();
	for(var j = 0 ; j < noOfCastesSelected ; j++)
	{
		//console.log(j);
		var casteId = selectedCastes[j].casteId;
		var casteFirstId = selectedCastes[0].casteId;
		//console.log(casteFirstId);
		$('.'+casteFirstId+''+id+'').each(function() {
		
			var casteDetails = {};
			var casteName = selectedCastes[j].casteName;
			var mandalId = $(this).closest("tr").find('.mandalIdClass').val();
			var panchayatId = $(this).closest("tr").find('.panchayatIdClass').val();
			var expPerc = $(this).closest("tr").find('.expPercClass'+j+'').val();
			if(parseFloat(''+expPerc+'') >= 0 && parseFloat(''+expPerc+'') <= 1)
			{
				if(mandalId.charAt(0) == 2)
				{
					casteDetails["panchayatId"]  =    panchayatId,
					casteDetails["casteId"]      =    casteId,
					casteDetails["expPerc"]      =    expPerc
					expCasteArray.push(casteDetails);
					
				}
				else
				{
					casteDetails["mandalId"]     =     mandalId.slice(1),
					casteDetails["casteId"]      =     casteId,
					casteDetails["expPerc"]      =     expPerc
					expCasteArrayForMuncipality.push(casteDetails);
				}
				
			}
			else
			{
				$('#expCasteErrorMsgDiv').show();
				$('#expCasteErrorMsgDiv').html('<b>PLEASE ENTER VALID EXPECTED CASTE PERCENTAGE..</b>');
				flag = true;
				$('#expCasteErrorMsgDiv').show().delay("3000").hide('slow');
			}
		});
		
	
	}
	if(flag)
	{
		alert("PLEASE ENTER VALID EXPECTED CASTE PERCENTAGE..");
	}
	else
	{
		$("#selCastesDisplayDiv").dialog('close');
	}
		
		//console.log(expCasteArray);
		//console.log(expCasteArrayForMuncipality);
		/* for(var j = 0 ; j < otherCastes.length ; j++)
		{
			var casteName = otherCastes[j].casteName;
			var casteId = otherCastes[j].casteId;
			$('.'+casteId+''+id+'').each(function() {
			var casteDetails = {};
			var mandalId = $(this).closest("tr").find('.mandalIdClass').val();
			var panchayatId = $(this).closest("tr").find('.panchayatIdClass').val();
			var expPerc = $(this).closest("tr").find('.expPercClass').val();
			if(parseFloat(''+expPerc+'') >= 0 && parseFloat(''+expPerc+'') <= 1)
			{
				if(mandalId.charAt(0) == 2)
				{
					casteDetails["panchayatId"]  =    panchayatId,
					casteDetails["casteId"]      =    casteId,
					casteDetails["expPerc"]      =     expPerc
					expCasteArray.push(casteDetails);
					
				}
				else
				{
					casteDetails["mandalId"]     =     mandalId.slice(1),
					casteDetails["casteId"]      =     casteId,
					casteDetails["expPerc"]      =     expPerc
					expCasteArrayForMuncipality.push(casteDetails);
				}
			}
			else
			{
				$('#expCasteErrorMsgDiv').show();
				$('#expCasteErrorMsgDiv').html('<b>PLEASE ENTER VALID EXPECTED CASTE PERCENTAGE..</b>');
				$('#expCasteErrorMsgDiv').show().delay("3000").hide('slow');
			}
			
			});
		} */
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
	madalPanchayatsArray = new  Array();
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

function getVotersCountsForPanchayats()
{
	var constituencyId = $('#listConstituencyNames option:selected').val();
	var publicationId = $('#listPublicationValues option:selected').val();
	var jsObj = 
		{
	        constituencyId : constituencyId,
			publicationId  : publicationId,
			task           : "getVoterscountInPanchayats"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getVoterscountInPanchayatsAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
}

function getDelimationEffect()
{
	var constituencyId = $('#listConstituencyNames option:selected').val();
	var partyId   = $('#partySelectEl option:selected').val();
	var jsObj = {
	        constituencyId : constituencyId,
			partyId        : partyId,
			task           : "getDelimationEffect"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getDelimationEffectAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
}

function buildgetDelimationEffect(result)
{
	$('#delimationEffectDiv').show();
	var str = '';
	str+='<div class="widget blue">';
	str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">Delimitation Effect With Out Alliance</h4>';
	str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
	str += '<tr>';
	str += '<th rowspan="2">Election Year</th>';
	str += '<th rowspan="2">Total Votes</th>';
	str += '<th rowspan="2">Polled votes</th>';
	str += '<th rowspan="2">Poll %</th>';
	for(var i  in result.delimitationEffectVO)
	{
		str += '<th colspan="2"><span style="float: left; margin-left: 30px;">'+result.delimitationEffectVO[i].partyName+'</span></th>';
		
	}
	
	str += '</tr>';
	str += '<tr>';
	for(var n  in result.delimitationEffectVO)
	{
		str += '<th>Gain Votes</th>';
		str += '<th>%</th>';
	}
	str += '</tr>';
	str += '<tr>';
	str += '<td>'+result.previousyear+'</td>';
	str += '<td>'+result.previousCount+'</td>';
	str += '<td>'+result.previousPolledVotes+'</td>';
	str += '<td>'+result.previousPerc+'</td>';
	for(var j in result.delimitationEffectVO)
	{
		str += '<td>'+result.delimitationEffectVO[j].previousCount+'</td>';
		str += '<td>'+result.delimitationEffectVO[j].previousPerc+'</td>';
	}
	str += '</tr>';
	str += '<tr>';
	str += '<td>'+result.presentYear+'</td>';
	str += '<td>'+result.presentCount+'</td>';
	str += '<td>'+result.presentPolledVotes+'</td>';
	str += '<td>'+result.presentPerc+'</td>';
	for(var k in result.delimitationEffectVO)
	{
		str += '<td>'+result.delimitationEffectVO[k].presentCount+'</td>';
		str += '<td>'+result.delimitationEffectVO[k].presentPerc+'</td>';
	}
	str += '</tr>';
	str += '</table>';
	
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">Delimitation Effect With Alliance</h4>';
	str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
	str += '<tr>';
	str += '<th rowspan="2">Election Year</th>';
	str += '<th rowspan="2">Total Votes</th>';
	str += '<th rowspan="2">Polled votes</th>';
	str += '<th rowspan="2">Poll %</th>';
	for(var i  in result.presentElections)
	{
		str += '<th colspan="2"><span style="float: left; margin-left: 30px;">'+result.presentElections[i].partyName+'</span></th>';
		
	}
	
	str += '</tr>';
	str += '<tr>';
	for(var n  in result.presentElections)
	{
		str += '<th>Gain Votes</th>';
		str += '<th>%</th>';
	}
	str += '</tr>';
	str += '<tr>';
	str += '<td>'+result.previousyear+'</td>';
	str += '<td>'+result.previousCount+'</td>';
	str += '<td>'+result.previousPolledVotes+'</td>';
	str += '<td>'+result.previousPerc+'</td>';
	for(var j in result.presentElections)
	{
		str += '<td>'+result.presentElections[j].previousCount+'</td>';
		str += '<td>'+result.presentElections[j].previousPerc+'</td>';
	}
	str += '</tr>';
	str += '<tr>';
	str += '<td>'+result.presentYear+'</td>';
	str += '<td>'+result.presentCount+'</td>';
	str += '<td>'+result.presentPolledVotes+'</td>';
	str += '<td>'+result.presentPerc+'</td>';
	for(var k in result.presentElections)
	{
		str += '<td>'+result.presentElections[k].presentCount+'</td>';
		str += '<td>'+result.presentElections[k].presentPerc+'</td>';
	}
	str += '</tr>';
	str += '</table>';
	str += '</div></div>';
	$('#delimationEffectDiv').html(str);
}
function buildVoterscountInPanchayats(result)
{
	if(result != null && result.length > 0)
	{
		$('#votersCountRageDiv').show();
		var str = '';
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">VOTER DENSITY VS PANCHAYATS</h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Voters Range</th>';
		for(var i in result)
		{
			str += '<th>'+result[i].type+'</th>';
			///str += '<input type="hidden" id="minValue" value="'+result[i].minValue+'"></input>';
			//str += '<input type="hidden" id="maxValue" value="'+result[i].maxValue+'"></input>';
		}
		str += '</tr>';
		str += '<tr>';
		str += '<td>No of Panchayats</td>';
		for(var j in result)
		{
			str += '<td><a href="#myModal" role="button"  data-toggle="modal" onClick="getPachayatsList('+result[j].minValue+','+result[j].maxValue+');">'+result[j].count+'</a></td>';
		}
		str += '</tr>';
		str += '</table>';
		str += '</div></div>';
		$('#votersCountRageDiv').html(str);
	}
}

function getPachayatsList(minValue,maxValue)
{
	$('#pachayatListDiv').html('');
	var constituencyId = $('#listConstituencyNames option:selected').val();
	var jsObj = {
	        constituencyId : constituencyId,
			minValue       : minValue,
			maxValue       : maxValue,
			task           : "getSelectedCountPAnchayatsDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getSelectedCountPAnchayatsDetailsAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
}

function buildgetSelectedCountPAnchayatsDetails(result)
{
	if(result != null && result.length > 0 )
	{
		var str = '';
		$('#processingImg').hide();
		str += '<table class="table table-hover table-bordered" style="width: 223px;">';
		str += '<tr>';
		str += '<th>Panchayat</th>';
		str += '</tr>';
		for(var i in result)
		{
			str += '<tr>';
			str += '<td>'+result[i].name+'('+result[i].location+')</td>';
			str += '</tr>';
		}
		str += '</table>';
		$('#pachayatListDiv').html(str);
		$('#myModal').model();
	}
}
function showExpCasteDetailsButton()
{
	var checkStatus = $('#expCaste').is(":checked");
	if(checkStatus == true)
	{
		$("#casteInfoDiv").css({ 'margin-top': '-172px', 'margin-left': '128px'});
		$('#expCasteButton').show();
	}
	else
	{
		$('#expCasteButton').hide();
			$("#casteInfoDiv").css({ 'margin-top': '0px', 'margin-left': '82px'});
	}
}
function buildLeadersTableWithExpPercForMuncipal(results)
{
	//$('#leadersTable').html('')
	if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();

		var str = "";
		/* str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' MUNCIPALITY EXPECTED CASTE DETAILS </h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">'; */
		/* str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th></t';
		str += '<th>Total Voters</th>';
			for(var b in results[0].exceptdCateDetails)
			{
				str += '<th>'+results[0].exceptdCateDetails[b].name+'</th>';
				str += '<th>Expected Perc</th>';
				str += '<th>Expected Votes</th>';
			}

		str += '</tr>'; */

		for(var i in results)
		{
		try{
			str += '<tr>';
			str += '<td>'+results[i].mandalName+' Muncipality</td>';
			str += '<td></td>';
			str += '<td>'+results[i].boothTotalVoters+'</td>'; 
			for(var k in results[i].exceptdCateDetails)
			{
				str += '<td>'+results[i].exceptdCateDetails[k].count+'</td>';
				str += '<td>'+results[i].exceptdCateDetails[k].perc+'</td>';
				str += '<td>'+results[i].exceptdCateDetails[k].expCount+'</td>';
			}
			
			str += '</tr>';
			}catch(e){
			}
		}
		
		str += '</table>';

		$('#leadersListTable').append(str);
	}
	
}
function buildLeadersTableWithExpPercForUrban(results)
{
	$('#leadersTable').html('')
	if(results != null && results.length > 0)
	{
		var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();

		var str = "";
		str+='<div class="widget blue">';
		str+='<div style="margin-top: 0px; clear: both; display: block; padding-bottom:1px;overflow:scroll;" class="widget-block">';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' CONSTITUENCY PANCHAYAT LEVEL EXPECTED CASTE DETAILS </h4>';
		str += '<table class="table table-hover table-bordered" id="expCasteDetailsForUrban" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;" >';
		str += '<thead><tr>';
		str += '<th>Mandal</th>';
		str += '<th>Ward</th>';
		str += '<th>Total Voters</th>';	
		str += '<th>Booth</th>';
		str += '<th>Total Voters</th>';	
		for(var c in results[0].boothLevelLeadersList[0].exceptdCateDetails)
		{
			str += '<th>'+results[0].boothLevelLeadersList[0].exceptdCateDetails[c].name+'</th>';
			str += '<th>Expected Perc</th>';
			str += '<th>Expected Votes</th>';
		}

		str += '</tr></thead><tbody>';

		for(var i in results)
		{
		try{
			var rowLength = results[i].boothLevelLeadersList.length;
			str += '<tr>';
			str += '<td rowspan='+rowLength+'>'+results[i].mandalName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatTotalVoters+'</td>'; 
			for(var m in results[i].boothLevelLeadersList)
			{
				if(m > 0)
				{
					str += '<tr>';
				}
				str += '<td>'+results[i].boothLevelLeadersList[m].boothName+'</td>';
				str += '<td>'+results[i].boothLevelLeadersList[m].boothTotalVoters+'</td>';
			
			
				for(var k in results[i].boothLevelLeadersList[m].exceptdCateDetails)
				{
				if(results[i].boothLevelLeadersList[m].exceptdCateDetails[k].count !=null)
				{
					str += '<td>'+results[i].boothLevelLeadersList[m].exceptdCateDetails[k].count+'</td>';
					str += '<td>'+results[i].boothLevelLeadersList[m].exceptdCateDetails[k].perc+'</td>';
					str += '<td>'+results[i].boothLevelLeadersList[m].exceptdCateDetails[k].expCount+'</td>';
				}
				else
				{
					str += '<td>0</td>';
					str += '<td>0</td>';
					str += '<td>0</td>';
				}
					
				}
				if(m > 0)
				{
					str += '</tr>';
				}
			}
			str += '</tr>';
			}catch(e){
			}
		}
		
		str += '</tbody></table>';
		str += '</div>';
		str += '</div>';

		$('#leadersTableForUrban').html(str);
		//$('#expCasteDetailsForUrban').dataTable();
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
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">'+constituencyName+' CONSTITUENCY PANCHAYAT LEVEL EXPECTED CASTE DETAILS </h4>';
		str += '<table class="table table-hover table-bordered" id="leadersListTable" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Mandal</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Total Voters</th>';		
			for(var b in results[0].exceptdCateDetails)
			{
				str += '<th>'+results[0].exceptdCateDetails[b].name+'</th>';
				str += '<th>Expected Perc</th>';
				str += '<th>Expected Votes</th>';
			}

		str += '</tr>';

		for(var i in results)
		{
		try{
			if(results[i].panchayatTotalVoters != null )
			{
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
			}
			}catch(e){
			}
		}
		
		str += '</table>';
		str += '</div>';
		str += '</div>';

		$('#leadersTable').html(str);
		if(constituencyType == 'RURAL-URBAN')
		{
			getLeadersListInRuralUrbans();
		} 
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
		}
		}
		
		str += '</table>';
		str += '</div>';
		str += '</div>';

		$('#leadersTable').html(str);
	}
	
} 
function buildLeadersTableForUrban(results)
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
		//str += '<th>Mandal</th>';
		str += '<th>Ward</th>';
		str += '<th>Total Voters</th>';
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
			//str += '<td rowspan='+rowLength+'>'+results[i].mandalName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatName+'</td>'; 
			str += '<td rowspan='+rowLength+'>'+results[i].panchayatTotalVoters+'</td>'; 
						
			if(results[i].boothLevelLeadersList != null && results[i].boothLevelLeadersList.length > 0){
			for(var k in results[i].boothLevelLeadersList)
			{
				if(k > 0)
				{
					str += '<tr>';
				}
				str += '<td>'+results[i].boothLevelLeadersList[k].boothName+'</td>'; 
				str += '<td>'+results[i].boothLevelLeadersList[k].boothTotalVoters+'</td>';
				
				if(results[i].boothLevelLeadersList[k].topThreeCateList != null)
				{
					str += '<td>';
					for(var m in results[i].boothLevelLeadersList[k].topThreeCateList)
					{
					str += ' '+results[i].boothLevelLeadersList[k].topThreeCateList[m].name+' ('+results[i].boothLevelLeadersList[k].topThreeCateList[m].perc+')  '; 
					}
					str += '</td>';
				}
				else
				{
					str += '<td>0</td>';
				}
				if(results[i].boothLevelLeadersList[k].selectedCateList != null)
				{
					str += '<td>';
				
					for(var m in results[i].boothLevelLeadersList[k].selectedCateList)
					{
						str += ' '+results[i].boothLevelLeadersList[k].selectedCateList[m].name+'  ('+results[i].boothLevelLeadersList[k].selectedCateList[m].perc+')'; 
					}
					str +='</td>';
				}
				else
				{
					str += '<td>0</td>';
				}
				if(k > 0)
				{
					str += '</tr>';
				}
			}
			}
			
			str += '</tr>';
		}catch(e){
		}
		}
		
		str += '</table>';
		str += '</div>';
		str += '</div>';

		$('#leadersTableForUrban').html(str);
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
	str +='<span class="yearSpan" style="margin-right: 530px;">'+result[1].name+'</span>';
	str +='<span class="yearSpan">'+result[0].name+'</span>';
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
				    str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].name+'</td>';
					str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].totalVoters+'</td>';
					str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].totalValidVotes+'</td>';
					str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].percentage+'</td>';
					str +='<td><span style="background: none repeat scroll 0% 0% '+result[0].partyPositionVOList[j].tempVar+'" class="spanCls">'+result[0].partyPositionVOList[j].partyPositionVOList[r].margin+'</span></td>';
					str +='<td>'+result[0].partyPositionVOList[j].partyPositionVOList[r].selectedPartyTotalVoters+'</td>';

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
	return;
	var constituencyId = $('#listConstituencyNames option:selected').val();
	if($('#candidateCastes').val() != null)
	var candidateCastes = $('#candidateCastes').val();
	else
	 var candidateCastes = [];
	if(constituencyId == 0)
	 return ;	 
var jsObj= 
	{	
		constituencyId:constituencyId,
		candidateCastes:candidateCastes,
		publicationId:latestPublicationId,
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
			 $("#dashBoardImgLoading").hide();
			  $("#dashBoardImgLoading1Lat").show();
			  $("#censusMainDiv").hide();
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
			castesSelcted:selectedCasteIds,
			expCasteArray:expCasteArray,
			expCasteArrayForMuncipality:expCasteArrayForMuncipality
		};
		
		$("#ageGroupCasteDetailsId").val(YAHOO.lang.JSON.stringify(jsObj));
			  
		var uploadHandler = {
		success: function(o) {
		var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
		
			$('#ajaxLoaderImg').css('display','none');
			$("#dashBoardImgLoading").hide();
		/*	if(uploadResult != null && uploadResult.length > 0){
								if(uploadResult[0].ageRange == '60> & <120'){
									uploadResult[0].ageRange = "ABOVE 60";
								}
								if(uploadResult[0].ageRange == '18> & <22'){
									uploadResult[0].ageRange = "18 TO 22";
								}
								
							}*/
							<c:if test="${youngVoters || oldVoters}">
			                      $("#dashBoardImgLoading1Lat").hide();
		                    </c:if>
			buildAgeGroupWiseTable(uploadResult,jsObj);
		
		}
		};
		YAHOO.util.Connect.setForm('ageGroupCasteDetailsForm',false);
		YAHOO.util.Connect.asyncRequest('POST','getAgeGroupWiseReportAction.action',uploadHandler);
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
		if(!(myResults.panchayatList != null && myResults.panchayatList.length > 0)){
		$('#ageGroupTableId1').html("<b>No Data Available</b>");
		}
		buildHeadBodyForTable(myResults,jsObj);
		
	}
	
	function buildHeadBodyForTable(myResults,jsObj){
		var tablesCount=myResults.length;
		
		if(tablesCount==1){
			createTable(myResults[0],'ageGroupTableId1',myResults[0].areaType);
			
			if(myResults[0].boothsList!=null){
				var checked = $('input[name=expCaste]:checked').val();
				if(checked!='expCaste')
				{
					createBoothsTable(myResults[0],'ageGroupBoothTableId1');
				}
			}
			
		}
		else if(tablesCount==2){
			for(var i=0;i<tablesCount;i++){
				var num=i+1;
				createTable(myResults[i],'ageGroupTableId'+num,myResults[0].areaType);
				if(myResults[i].boothsList!=null){
					var checked = $('input[name=expCaste]:checked').val();
					if(checked!='expCaste')
					{
						createBoothsTable(myResults[i],'ageGroupBoothTableId'+num);
					}
				}
			}
			
						
		}
		else{
			for(var i=0;i<tablesCount;i++){
				var num=i+1;
				createTable(myResults[i],'ageGroupTableId'+num,myResults[0].areaType);
				
				if(myResults[i].boothsList!=null){
					var checked = $('input[name=expCaste]:checked').val();
					if(checked!='expCaste')
					{
						createBoothsTable(myResults[i],'ageGroupBoothTableId'+num);
					}
					
				}
			}
		}
	}
	
	
	function createTable(result,tableId,areaType){
	$('#title'+tableId).css('display','block');
	$('.title'+tableId+"Cls").addClass('widget').addClass('blue');
	$('.title'+tableId+"Cls").css('margin-top','50px');
	<c:if test="${hideMainMenu}">
	$('.title'+tableId+"Cls").css('margin-top','0px');
	</c:if>
	var area="";
	if(areaType=="RURAL" || areaType=="RURAL-URBAN"){
		area=" Panchayat";
	}else{
		area=" Booth"
	}
	var constituencyName = $('#listConstituencyNames option:selected').text().toUpperCase();
	if(constituencyName != '')
	$('#title'+tableId).html('<h4> '+constituencyName+' Constituency '+area+' Wise Voters Analysis Based On Age Range - '+result.ageRange+'</h4>');
	else
$('#title'+tableId).html('<h4> ${constituencyName} Constituency '+area+' Wise Voters Analysis Based On Age Range - '+result.ageRange+'</h4>');
	
	var allSlctedCastes=[];
	for(var i in result.panchayatList[0].allSelectedCastes){
		allSlctedCastes.push(result.panchayatList[0].allSelectedCastes[i].castName);
	}
	
	
		var checked = $('input[name=expCaste]:checked').val();
		var str='';
		<!--str+='<div style="width:800px" align="center"><h4>Panchayat Wise Voters Analysis of Age Range - +'+result.ageRange+'</h4></div>'-->
		if(checked!='expCaste'){
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead style="background:#D9EDF7;color:#000000;"><tr><th rowspan=2>'+area+'</th><th rowspan=2>Total Voters In '+area+'</th><th colspan=4>'+result.ageRange+'</th><th rowspan=2>Top Castes</th><th rowspan=2>Selected Castes</th>';
		str+='</tr>';
		str+='<tr><th>Total Voters</th><th>Male Voters</th><th>Female Voters</th>	<th>Percentage</th></tr></thead>';
		str+='<tbody style="font-size:12px;color:#000000;">';
		var res=result.panchayatList;
				
		for(var i in result.panchayatList){
		str+='<tr>';
		if(areaType=="RURAL"|| areaType=="RURAL-URBAN"){
			str+='<td>'+result.panchayatList[i].panchayatName+'</td>';
		}else{
			str+='<td> Booth - '+result.panchayatList[i].panchayatName+'</td>';
		}
		
		str+='<td>'+result.panchayatList[i].totalPanchayatVoters+'</td>';
		str+='<td>'+result.panchayatList[i].totalVoters+'</td>';
		str+='<td>'+result.panchayatList[i].maleVoters+'</td>';
		str+='<td>'+result.panchayatList[i].femaleVoters+'</td>';
		str+='<td>'+result.panchayatList[i].percentage+'</td>';
		str+='<td>';
		
		var topCastesLength=0;
		if(result.panchayatList[i].topCastes!=null){
			var topCastesLength=result.panchayatList[i].topCastes.length;
		}
		
		for(var j in result.panchayatList[i].topCastes){
		str+=result.panchayatList[i].topCastes[j].castName+"("+result.panchayatList[i].topCastes[j].castCount+")";
			 if(topCastesLength>j){
				str+=", ";
			}
		}
		str+='</td>';
		str+='<td>';
		var slctdCastesLength=0;
		
		if(result.panchayatList[i].selectedCastes!=null){
			slctdCastesLength=result.panchayatList[i].selectedCastes.length;
		}
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
		str+='</td>';
		str+='</tr>';
		}
		str+='</tbody></table>';
		}
		
		<!---->
		else{
		
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead style="background:#D9EDF7;color:#000000;"><tr><th>'+area+'</th><th>Total Voters In '+area+'</th>';
		for(var i in allSlctedCastes){
		str+='<th>'+allSlctedCastes[i]+'</th>';
		str+='<th>Expected Votes</th>';
		str+='<th>Expected % </th>';
		}
		str+='<th>Other Votes</th>';
		str+='<th>Expected Votes</th>';
		str+='<th>Expected % </th>';
		
		str+='</tr>';
		str+='</thead>';
		str+='<tbody style="font-size:12px;color:#000000;">';
		var res=result.panchayatList;
		for(var i in result.panchayatList){
		str+='<tr>';
		if(areaType=="RURAL"|| areaType=="RURAL-URBAN"){
			str+='<td>'+result.panchayatList[i].panchayatName+'</td>';
		}else{
			str+='<td> Booth - '+result.panchayatList[i].panchayatName+'</td>';
		}
		str+='<td>'+result.panchayatList[i].totalVoters+'</td>';
				
		for(var j in result.panchayatList[i].allSelectedCastes){
			str+='<td>'+result.panchayatList[i].allSelectedCastes[j].castCount+'</td>';
			str+='<td>'+result.panchayatList[i].allSelectedCastes[j].expctdVotesCount+'</td>';
			str+='<td>'+result.panchayatList[i].allSelectedCastes[j].expctdPercentage+'</td>';
		}
		str+='<td>'+result.panchayatList[i].otherVotes+'</td>';
		str+='<td>'+result.panchayatList[i].othrExpctdVotes+'</td>';
		str+='<td>'+result.panchayatList[i].othrExpctdPrcntg+'</td>';
		
		str+='</tr>';
		
		}
		if(result.muncipalityCasteList != null)
		{
			str += '<tr>';
			str += '<td>'+result.muncipalityName+'</td>';
			str += '<td>'+result.muncipaltotalVoters+'</td>';
			for(var p in result.muncipalityCasteList)
			{
				str += '<td>'+result.muncipalityCasteList[p].count+'</td>';
				str += '<td>'+result.muncipalityCasteList[p].expCount+'</td>';
				str += '<td>'+result.muncipalityCasteList[p].perc+'</td>';
			}
			str += '</tr>';
		}	
	str+='</tbody></table>';
		}
		<!---->
		
		
		
		
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
	
	
	function createBoothsTable(result,tableId){
	  if(!(result.boothsList != null &&  result.boothsList.length > 0))
				return;
	mncplName=result.boothsList[0].muncipalityName;
	$('#title'+tableId).css('display','block');
	$('.title'+tableId+"Cls").addClass('widget').addClass('blue');
	$('.title'+tableId+"Cls").css('margin-top','50px');
	<c:if test="${hideMainMenu}">
	$('.title'+tableId+"Cls").css('margin-top','0px');
	</c:if>
	$('#title'+tableId).html('<h4>'+mncplName+'- Booth Wise Voters Analysis of Age Range - '+result.ageRange+'</h4>');
	
	var allSlctedCastes=[];
	for(var i in result.boothsList[0].allSelectedCastes){
		allSlctedCastes.push(result.boothsList[0].allSelectedCastes[i].castName);
	}
	
	
		var expCaste = $('input[name=expCaste]:checked').val();
		var str='';
		if(expCaste!='expCaste'){
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead style="background:#D9EDF7;color:#000000;"><tr><th rowspan=2>Booths</th><th rowspan=2>Total Voters In Booth</th><th colspan=4>'+result.ageRange+'</th><th rowspan=2>Top Castes</th><th rowspan=2>Selected Castes</th>';
		str+='</tr>';
		str+='<tr><th>Total Voters</th><th>Male Voters</th><th>Female Voters</th>	<th>Percentage</th></tr></thead>';
		str+='<tbody style="font-size:12px;color:#000000;">';
		for(var i in result.boothsList){
		str+='<tr>';
		str+='<td> Booth - '+result.boothsList[i].panchayatName+'</td>';
		str+='<td>'+result.boothsList[i].totalPanchayatVoters+'</td>';
		str+='<td>'+result.boothsList[i].totalVoters+'</td>';
		str+='<td>'+result.boothsList[i].maleVoters+'</td>';
		str+='<td>'+result.boothsList[i].femaleVoters+'</td>';
		str+='<td>'+result.boothsList[i].percentage+'</td>';
		str+='<td>';
		
		var topCastesLength=0;
		if(result.boothsList[i].topCastes!=null){
		var topCastesLength=result.boothsList[i].topCastes.length;
		for(var j in result.boothsList[i].topCastes){
		str+=result.boothsList[i].topCastes[j].castName+"("+result.boothsList[i].topCastes[j].castCount+")";
			 if(topCastesLength>j){
				str+=", ";
			}
		}
		}
		str+='</td>';
		str+='<td>';
		var slctedCastesLength=0;
		if(result.boothsList[i].selectedCastes!=null){
		var slctedCastesLength=result.boothsList[i].selectedCastes.length;
		if(slctedCastesLength>0){
		for(var j in result.boothsList[i].selectedCastes){
		str+=result.boothsList[i].selectedCastes[j].castName+"("+result.boothsList[i].selectedCastes[j].castCount+")";
			 if(slctedCastesLength>j){
				str+=", ";
			}
		}
		}
		else{
			str+="-";
		}
		str+='</td>';
		str+='</tr>';
		}
		}
		str+='</tbody></table>';
		
		}
		else{
		str+='<table class="table table-bordered table-striped table-hover" style="font-family:verdana,font-size:12px;" id="table'+tableId+'"><thead style="background:#D9EDF7;color:#000000;"><tr><th>Booths</th><th>Total Voters In Booth</th>';
		for(var i in allSlctedCastes){
		str+='<th>'+allSlctedCastes[i]+'</th>';
		str+='<th>Expected Votes</th>';
		str+='<th>Expected %</th>';
		}
		str+='<th>Other Votes</th>';
		str+='<th>Expected Votes</th>';
		str+='<th>Expected % </th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody style="font-size:12px;color:#000000;">';
		for(var i in result.boothsList){
		str+='<tr>';
		str+='<td> Booth - '+result.boothsList[i].panchayatName+'</td>';
		str+='<td>'+result.boothsList[i].totalVoters+'</td>';
				
		for(var j in result.boothsList[i].allSelectedCastes){
			str+='<td>'+result.boothsList[i].allSelectedCastes[j].castCount+'</td>';
			str+='<td>'+result.boothsList[i].allSelectedCastes[j].expctdVotesCount+'</td>';
			str+='<td>'+result.boothsList[i].allSelectedCastes[j].expctdPercentage+'</td>';
		}
		str+='<td>'+result.boothsList[i].otherVotes+'</td>';
		str+='<td>'+result.boothsList[i].othrExpctdVotes+'</td>';
		str+='<td>'+result.boothsList[i].othrExpctdPrcntg+'</td>';
		str+='</tr>';
		}
		str+='</tbody></table>';
		
		}
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
		publicationId:$('#listPublicationValues').val(),
		partyName : $('#partySelectEl option:selected').text(),
		task:"getEffectOfNewParty"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getEffectOfNewPartyOnTraditionalParties.action?"+param;
	callAjax(param,jsObj,url);

}

function getConstituencyBasicCountInfo()
{
$("#basicCountDiv").css("display","none");
var id = $('#listConstituencyNames').val();
var publicationId = $('#listPublicationValues').val();
if(id == 0)
return;
var jsObj= 
	{	
        constituencyId:id,
		publicationId:publicationId,
		task:"getConstituencyBasicCountInfo"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getConstituencyBasicCountInfoAction.action?"+param;
	callAjax(param,jsObj,url);
}
function buildConstituencyBasicCountInfo(results)
{
$("#basicCountDiv").css("display","block");
var str='';
var divEle = document.getElementById("basicCountDiv");
var name = $("#listConstituencyNames option:selected").text(); 
str+='<h4>'+name+' Constituenyc Basic Information </h4>';
str+='<div id="basicInfoDiv" class="widget-block" style="padding:10px;">';
if(results[0].totalmandals != 0)
	{
str+='<span class="btn btn-info btn-small">'+results[0].totalmandals+'</span>';
str+='<span class="help-inline f2">Mandals &nbsp;</span>';
	}
	if(results[0].totalPanchayats != 0)
	{
str+='<span class="btn btn-info btn-small">'+results[0].totalPanchayats+'</span>';
str+='<span class="help-inline f2">Panchayats &nbsp;</span>';
	}
if(results[0].noOfLocalBodies != 0)
	{
str+='<span class="btn btn-info btn-small">'+results[0].noOfLocalBodies+'</span>';
str+='<span class="help-inline f2">Muncipalities &nbsp;</span>';
	}
	if(results[0].totalNoOfWards != 0)
	{
str+='<span class="btn btn-info btn-small">'+results[0].totalNoOfWards+'</span>';
str+='<span class="help-inline f2">Wards &nbsp;</span>';
	}
	if(results[0].totalBooths != 0)
	{
str+='<span class="btn btn-info btn-small">'+results[0].totalBooths+'</span>';
str+='<span class="help-inline f2">Booths &nbsp;</span>';
	}
str+='</div>';
divEle.innerHTML = str;
}
function buildnewPartyEffectResults(results)
{
	$('#titleDiv').show();
	$('#newPartyDiv').show();
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
   str+='<th rowspan="2">Major Castes</th>';
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
					str+='<td>';
					for(var j=0;j<value[parties[i]].panchayatList.length;j++){
					var casteName = value[parties[i]].panchayatList[j].casteName;
					var count = value[parties[i]].panchayatList[j].count;
					var persent = value[parties[i]].panchayatList[j].persent;

					str+='('+casteName+','+persent+','+count+') ';
					}
					str+='</td>';

					var value = parseFloat(value[parties[i]].fromPrpVoters).toFixed(2)
					
					str+='<td>'+value+'</td>';
					
				}
			   }

		   }

		 str+='</tr>';

	 });
  str+='</table>';


	$('#newPartyDiv').html(str);
}	 

function getPollingPercentageForBooths()
{
	$("#strongPollingPerDiv").css("display","none");
	$("#weakPollingPerDiv").css("display","none");
	$("#addedVoterDetailsDiv1").css("display","none");
	var constituencyId = $('#listConstituencyNames').val();
	var partyId = $('#partySelectEl').val();
	/*var electionIdsArr = new Array();
	$("#electionYearSelectEl1 option").each(function() {
	var val = $(this).val();
	electionIdsArr.push(val);
	});
	var largest = Math.max.apply(Math, electionIdsArr);*/
	var eleId =  $('#electionYearSelectEl1').val();
	var eleId1 = $('#electionYearSelectEl2').val();
	if(eleId==0 || eleId1 == 0)
		return;
	var jsObj= 
	{	
		constituencyId:constituencyId,
		partyId:partyId,
		eleId:eleId,
		eleId1:eleId1,
		task:"getPollingPercentages"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPollingPercentagesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);
	
}

function getVoterDetailsByPartNo(partno,constituencyId,startIndex)
{
 var jsObj= 
			{
			
				constituencyId:constituencyId,
				partno:partno,
				startIndex:startIndex,
				results:10,
				task:"getVoterDetailsByPartNo"
	
			}
	   var param ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVoterDetailsByPartNo.action?"+param;						
		callAjax(param,jsObj,url);;
}
function getCasteBasicInfo()
{
$("#basicCasteInfoDiv").css("display","none");
var id = $('#listConstituencyNames').val();
var publicationId = $('#listPublicationValues').val();
var text = $('#listConstituencyNames option:selected').text();
if(id == 0)
return;
var jsObj= 
	{	
        constituencyId:id,
		id:id,
		publicationDateId:publicationId,
		queryType:"sub",
		type:"constituency",
		typename:text,
		task:"getCastInfo"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}


<c:if test="${hideMainMenu}">  
  <c:if test="${castDetails}">
    
	getConstituencyType();
  </c:if>
  <c:if test="${castDetails}"> 
   

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

function buildPopulationCensusTable(results,jsObj){
$('#populationCensusDiv').html('');
var constiName = $('#listConstituencyNames :selected').text();
var str='';
str +='<h4>'+constiName+' Constituency 2001 and 2011 Census Information </h4>';
str +='<table class="table"  style="margin-top:10px;">';

	str +='<tr>';
	str +='<td style="border-left:1px solid #F1F1F1; border-top: 1px solid #F1F1F1; border-bottom: 1px solid #F1F1F1;"></td>';
	str +='<th colspan="2">2001</th>';
	str +='<th colspan="2">2011</th>';
	str +='<th colspan="4">2001 to 2011 Change in Percentages</th>';
	str +='</tr>';
	
	str +='<tr>';
	str +='<td style="border-left:1px solid #F1F1F1; border-top: 1px solid #F1F1F1;"></td>';
	str +='<th>'+constiName+'</th>';
	str +='<th>'+constiName+'</th>';
	str +='<th>'+constiName+'</th>';
	str +='<th>'+constiName+'</th>';
	str +='<th>'+constiName+'</th>';
	str +='<th>'+constiName+'</th>';
	str +='<th>'+results.districtName+' Dist.</th>';
	str +='<th>'+results.stateName+' State</th>';
	str +='</tr>';
	str +='<tr>';
	str +='<th>Parameter</th>';
	str +='<th>Values</th>';
	str +='<th>%</th>';
	str +='<th>Values</th>';
	str +='<th>%</th>';
	str +='<th>Values</th>';
	str +='<th>%</th>';
	str +='<th>%</th>';
	str +='<th>%</th>';
	str +='</tr>';
	
	str +='<tr>';
	str +='<td>Population</td>';

		str +='<td>'+results.censusDetailsList[0].totalPopulation+'</td>';
		str +='<td>'+results.censusDetailsList[0].totalPopulationPercentage+'</td>';
		
		if(results.count >1){
			str +='<td>'+results.censusDetailsList[1].totalPopulation+'</td>';
			str +='<td>'+results.censusDetailsList[1].totalPopulationPercentage+'</td>';


			str +='<td>'+results.differencePopulation+'</td>';
			str +='<td>'+results.differencePopulationPercent+'</td>';
			str +='<td>'+results.districtDetails.differencePopulationPercent+'</td>';
			str +='<td>'+results.stateDetails.differencePopulationPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}

	str +='</tr>';
		
	str +='<tr>';
	str +='<td>Male</td>';

		str +='<td>'+results.censusDetailsList[0].malePopulation+'</td>';
		str +='<td>'+results.censusDetailsList[0].malePopulationPercentage+'</td>';
	if(results.count >1){	
		str +='<td>'+results.censusDetailsList[1].malePopulation+'</td>';
		str +='<td>'+results.censusDetailsList[1].malePopulationPercentage+'</td>';


		str +='<td>'+results.differenceMalePopulation+'</td>';
		str +='<td>'+results.differenceMalePercent+'</td>';
		str +='<td>'+results.districtDetails.differenceMalePercent+'</td>';
		str +='<td>'+results.stateDetails.differenceMalePercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
		
	str +='</tr>';
	
	str +='<tr>';
	str +='<td>Female</td>';

		str +='<td>'+results.censusDetailsList[0].femalePopulation+'</td>';
		str +='<td>'+results.censusDetailsList[0].femalePopulationPercentage+'</td>';
	if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].femalePopulation+'</td>';
		str +='<td>'+results.censusDetailsList[1].femalePopulationPercentage+'</td>';


		str +='<td>'+results.differenceFemalePopulation+'</td>';
		str +='<td>'+results.differenceFemalePercent+'</td>';
		str +='<td>'+results.districtDetails.differenceFemalePercent+'</td>';
		str +='<td>'+results.stateDetails.differenceFemalePercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';

	str +='<tr>';
	str +='<td>Households</td>';

		str +='<td>'+results.censusDetailsList[0].houseHolds+'</td>';
		str +='<td>'+results.censusDetailsList[0].houseHoldsPercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].houseHolds+'</td>';
		str +='<td>'+results.censusDetailsList[1].houseHoldsPercentage+'</td>';


		str +='<td>'+results.differenceHouseHolds+'</td>';
		str +='<td>'+results.differenceHouseHoldsPercent+'</td>';
		str +='<td>'+results.districtDetails.differenceHouseHoldsPercent+'</td>';
		str +='<td>'+results.stateDetails.differenceHouseHoldsPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
		str +='<tr>';
	str +='<td>SC</td>';

		str +='<td>'+results.censusDetailsList[0].populationSC+'</td>';
		str +='<td>'+results.censusDetailsList[0].populationSCPercent+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].populationSC+'</td>';
		str +='<td>'+results.censusDetailsList[1].populationSCPercent+'</td>';


		str +='<td>'+results.differenceSC+'</td>';
		str +='<td>'+results.differenceSCPercent+'</td>';
		str +='<td>'+results.districtDetails.differenceSCPercent+'</td>';
		str +='<td>'+results.stateDetails.differenceSCPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	str +='<td>ST</td>';

		str +='<td>'+results.censusDetailsList[0].populationST+'</td>';
		str +='<td>'+results.censusDetailsList[0].populationSTPercent+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].populationST+'</td>';
		str +='<td>'+results.censusDetailsList[1].populationSTPercent+'</td>';


		str +='<td>'+results.differenceST+'</td>';
		str +='<td>'+results.differenceSTPercent+'</td>';
		str +='<td>'+results.districtDetails.differenceSTPercent+'</td>';
		str +='<td>'+results.stateDetails.differenceSTPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
		str +='<td>Working People</td>';

		str +='<td>'+results.censusDetailsList[0].workingPeople+'</td>';
		str +='<td>'+results.censusDetailsList[0].workingPeoplePercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].workingPeople+'</td>';
		str +='<td>'+results.censusDetailsList[1].workingPeoplePercentage+'</td>';


		str +='<td>'+results.differenceWorkingPeople+'</td>';
		str +='<td>'+results.differenceWorkingPeoplePercent+'</td>';
		str +='<td>'+results.districtDetails.differenceWorkingPeoplePercent+'</td>';
		str +='<td>'+results.stateDetails.differenceWorkingPeoplePercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
		str +='<td>Male Working People</td>';

		str +='<td>'+results.censusDetailsList[0].workingMale+'</td>';
		str +='<td>'+results.censusDetailsList[0].totalWorkingMalePercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].workingMale+'</td>';
		str +='<td>'+results.censusDetailsList[1].totalWorkingMalePercentage+'</td>';


		str +='<td>'+results.differenceMaleWorkingPeople+'</td>';
		str +='<td>'+results.differenceMaleWorkingPeoplePercent+'</td>';
		str +='<td>'+results.districtDetails.differenceMaleWorkingPeoplePercent+'</td>';
		str +='<td>'+results.stateDetails.differenceMaleWorkingPeoplePercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
		str +='<td>Female Working People</td>';

		str +='<td>'+results.censusDetailsList[0].workingFemale+'</td>';
		str +='<td>'+results.censusDetailsList[0].totalWorkingFemalePercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].workingFemale+'</td>';
		str +='<td>'+results.censusDetailsList[1].totalWorkingFemalePercentage+'</td>';


		str +='<td>'+results.differenceFemaleWorkingPeople+'</td>';
		str +='<td>'+results.differenceFemaleWorkingPeoplePercent+'</td>';
		str +='<td>'+results.districtDetails.differenceFemaleWorkingPeoplePercent+'</td>';
		str +='<td>'+results.stateDetails.differenceFemaleWorkingPeoplePercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
		str +='<td>Non Working People</td>';

		str +='<td>'+results.censusDetailsList[0].nonWorkingPeople+'</td>';
		str +='<td>'+results.censusDetailsList[0].nonWorkingPeoplePercent+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].nonWorkingPeople+'</td>';
		str +='<td>'+results.censusDetailsList[1].nonWorkingPeoplePercent+'</td>';


		str +='<td>'+results.differenceNonWorkingPeople+'</td>';
		str +='<td>'+results.differenceNonWorkingPeoplePercent+'</td>';
		str +='<td>'+results.districtDetails.differenceNonWorkingPeoplePercent+'</td>';
		str +='<td>'+results.stateDetails.differenceNonWorkingPeoplePercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
		str +='<td>Population(Age < 6)</td>';

		str +='<td>'+results.censusDetailsList[0].populationUnderSix+'</td>';
		str +='<td>'+results.censusDetailsList[0].populationUnderSixPercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].populationUnderSix+'</td>';
		str +='<td>'+results.censusDetailsList[1].populationUnderSixPercentage+'</td>';

		str +='<td>'+results.differenceLessthan6Population+'</td>';
		str +='<td>'+results.differenceLessthan6Percent+'</td>';
		str +='<td>'+results.districtDetails.differenceLessthan6Percent+'</td>';
		str +='<td>'+results.stateDetails.differenceLessthan6Percent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
		str +='<td>Literates</td>';

		str +='<td>'+results.censusDetailsList[0].literates+'</td>';
		str +='<td>'+results.censusDetailsList[0].literatesPercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].literates+'</td>';
		str +='<td>'+results.censusDetailsList[1].literatesPercentage+'</td>';

		str +='<td>'+results.differenceLiterates+'</td>';
		str +='<td>'+results.differenceLiteratesPercent+'</td>';
		str +='<td>'+results.districtDetails.differenceLiteratesPercent+'</td>';
		str +='<td>'+results.stateDetails.differenceLiteratesPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
		str +='<td>Male Literates</td>';

		str +='<td>'+results.censusDetailsList[0].maleLiterates+'</td>';
		str +='<td>'+results.censusDetailsList[0].maleLiteraturePercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].maleLiterates+'</td>';
		str +='<td>'+results.censusDetailsList[1].maleLiteraturePercentage+'</td>';

		str +='<td>'+results.differenceMaleLiterates+'</td>';
		str +='<td>'+results.differenceMaleLiteratesPercent+'</td>';
		str +='<td>'+results.districtDetails.differenceMaleLiteratesPercent+'</td>';
		str +='<td>'+results.stateDetails.differenceMaleLiteratesPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
		str +='<td>Female Literates</td>';

		str +='<td>'+results.censusDetailsList[0].femaleLiterates+'</td>';
		str +='<td>'+results.censusDetailsList[0].femaleLiteraturePercentage+'</td>';
		if(results.count >1){
		str +='<td>'+results.censusDetailsList[1].femaleLiterates+'</td>';
		str +='<td>'+results.censusDetailsList[1].femaleLiteraturePercentage+'</td>';


		str +='<td>'+results.differenceFemaleLiterates+'</td>';
		str +='<td>'+results.differenceFemaleLiteratesPercent+'</td>';
		str +='<td>'+results.districtDetails.differenceFemaleLiteratesPercent+'</td>';
		str +='<td>'+results.stateDetails.differenceFemaleLiteratesPercent+'</td>';
		}
		else
		{
		str += ''+getEmptyTableData();
		}
	str +='</tr>';
	
	
	str +='</tr>';


str +='</table>';


$('#populationCensusDiv').html(str);


}  

function getEmptyTableData(){
var str1 ='';
	str1 +='<td> - </td>';
	str1 +='<td> - </td>';
	str1 +='<td> - </td>';
	str1 +='<td> - </td>';
	str1 +='<td> - </td>';
	str1 +='<td> - </td>';
	
return str1;
}

function getElectionResults(){
	var constituencyId = $('#listConstituencyNames').val();
	
	var jsObj = {
			constituencyId:constituencyId,
			task:"partiesPerformanceInDiffElection"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getPartiesPerformanceInDiffElectionAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

function buildAssemblyPartiesElection(results,jsObj){
	$('#assemblyElectionDiv').html('');
	$('#assemblyElectionDiv').css('display','block');
 var str = '';
 $("#assemblyElectionDiv").append('<h4 id="electionDisplaySpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">Previous Assembly Election Voting Trends</h4>');
 str +='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;color: black;">';
str +='<thead class="info"><tr>';
str +='<th>Year</th>';
str +='<th>Total Voters</th>';
str +='<th>Polled Voters</th>';
str +='<th>INC</th>';
str +='<th>% Votes (INC)</th>';
str +='<th>TDP</th>';
str +='<th>% Votes (TDP)</th>';
str +='<th>PRP</th>';
str +='<th>% Votes (PRP)</th>';
str +='<th>OTHERS</th>';
str +='<th>% Votes (OTHERS)</th>';
str +='</tr></thead><tbody>';

 str +='<tr>';
	for(var j in results.assemblyList){
		str += '<td>'+results.assemblyList[j].electionYear+'</td>';
		str += '<td>'+results.assemblyList[j].totalVotes+'</td>';
		str += '<td>'+results.assemblyList[j].validVotes+'</td>';
		for(var i=0;i<=results.assemblyList[j].partiesList.length;i++){

		if(i<3){
			if(results.assemblyList[j].partiesList[i].validCount>0)
			str += '<td>'+results.assemblyList[j].partiesList[i].validCount+'</td>';
			else
				str += '<td>--</td>';
		str += '<td>'+results.assemblyList[j].partiesList[i].location+'</td>';
		}

		}

		var othersCount = 0;
		for(var k in results.assemblyList[j].partiesList){
			othersCount += results.assemblyList[j].partiesList[k].validCount;
			
		}
		var othersCountPercent = (othersCount*100)/results.assemblyList[j].validVotes;
		str += '<td>'+othersCount+'</td>';
		str += '<td>'+othersCountPercent.toFixed(2)+'</td>';
		str +='</tr>';
	}

  str +='</tbody></table>';
  $("#assemblyElectionDiv").append(str);
 
}

function buildParliamentPartiesElection(results,jsObj){
	$('#parliamentElectionDiv').html('');
	$('#parliamentElectionDiv').css('display','block');
 var str = '';
 $("#parliamentElectionDiv").append('<h4 id="electionDisplaySpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">Previous Parliament Election Voting Trends</h4>');
 str +='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;color: black;">';
str +='<thead class="info"><tr>';
str +='<th>Year</th>';
str +='<th>Total Voters</th>';
str +='<th>Polled Voters</th>';
str +='<th>INC</th>';
str +='<th>% Votes (INC)</th>';
str +='<th>TDP</th>';
str +='<th>% Votes (TDP)</th>';
str +='<th>PRP</th>';
str +='<th>% Votes (PRP)</th>';
str +='<th>OTHERS</th>';
str +='<th>% Votes (OTHERS)</th>';
str +='</tr></thead><tbody>';

str +='<tr>';
	for(var j in results.parliamentList){
		str += '<td>'+results.parliamentList[j].electionYear+'</td>';
		str += '<td>'+results.parliamentList[j].totalVotes+'</td>';
		str += '<td>'+results.parliamentList[j].validVotes+'</td>';
		for(var i=0;i<=results.parliamentList[j].partiesList.length;i++){

		if(i<3){
			if(results.parliamentList[j].partiesList[i].validCount>0)
			str += '<td>'+results.parliamentList[j].partiesList[i].validCount+'</td>';
			else
			str += '<td>--</td>';
		var value = (results.parliamentList[j].partiesList[i].validCount)*100/(results.parliamentList[j].totalVotes);
		if(value>0)
		str += '<td>'+value.toFixed(2)+'</td>';
		else
			str += '<td>--</td>';
		}

		}

		var othersCount = 0;
		for(var k in results.parliamentList[j].partiesList){
			if(k>=3)
			othersCount += results.parliamentList[j].partiesList[k].validCount;
			
		}
		var othersCountPercent = (othersCount*100)/results.parliamentList[j].validVotes;
		str += '<td>'+othersCount+'</td>';
		str += '<td>'+othersCountPercent.toFixed(2)+'</td>';
		str +='</tr>';
	}
	
  str +='</tbody></table>';
  $("#parliamentElectionDiv").append(str);
}

function buildMptcZptcPartiesElection(results,jsObj){
	$('#mptcZptcElectionDiv').html('');
	$('#mptcZptcElectionDiv').css('display','block');
var str = '';
$("#mptcZptcElectionDiv").append('<h4 id="electionDisplaySpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">Previous Mptc/Zptc Election Voting Trends</h4>');
str +='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;color: black;">';
str +='<thead class="info"><tr>';
str +='<th>Year</th>';
str +='<th>Total Voters</th>';
str +='<th>Polled Voters</th>';
str +='<th>INC</th>';
str +='<th>% Votes (INC)</th>';
str +='<th>TDP</th>';
str +='<th>% Votes (TDP)</th>';
str +='<th>PRP</th>';
str +='<th>% Votes (PRP)</th>';
str +='<th>OTHERS</th>';
str +='<th>% Votes (OTHERS)</th>';
str +='</tr></thead><tbody>';

	 str +='<tr>';
	for(var j in results.mptcZptcList){
		str += '<td>'+results.mptcZptcList[j].electionYear+'-'+results.mptcZptcList[j].electionType+'</td>';
		str += '<td>--</td>';
		str += '<td>'+results.mptcZptcList[j].validVotes+'</td>';
		for(var i=0;i<=results.mptcZptcList[j].partiesList.length;i++){

		if(i<3){
			if(results.mptcZptcList[j].partiesList[i].validCount>0)
			str += '<td>'+results.mptcZptcList[j].partiesList[i].validCount+'</td>';
			else
				str += '<td>--</td>';
		var value1 = (results.mptcZptcList[j].partiesList[i].validCount)*100/(results.mptcZptcList[j].validVotes);
		if(value1>0)
		str += '<td>'+value1.toFixed(2)+'</td>';
		else
			str += '<td>--</td>';
		}

		}

		var othersCount = 0;
		for(var k in results.mptcZptcList[j].partiesList){
			othersCount += results.mptcZptcList[j].partiesList[k].validCount;
			
		}
		var othersCountPercent = (othersCount*100)/results.mptcZptcList[j].validVotes;
		str += '<td>'+othersCount+'</td>';
		str += '<td>'+othersCountPercent.toFixed(2)+'</td>';
		str +='</tr>';
	}
	
  str +='</tbody></table>';
  $("#mptcZptcElectionDiv").append(str);
}

function getVoterAgeGroupResults(){
	var constituencyId = $('#listConstituencyNames').val();
	
	var jsObj = {
			constituencyId:constituencyId,
			locationType:"constituency",
			publicationDateId:latestPublicationId,
			task:"getVoterAgeGroupResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getVoterAgeGroupResultsAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

function getVoterDetailsForConstituency(){
	var constituencyId = $('#listConstituencyNames').val();
	var constituencyName = $('#listConstituencyNames option:selected').text();
	var jsObj=
				{					
					constituencyId:constituencyId,
					publicationDateId:latestPublicationId,
					mandalId:'0',
					boothId:'0',
					panchayatId:'0',
					name:constituencyName,
					retrieveType:"brief",
				    type:"constituency",
					task:"getAgewiseVoterDetails"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;				
		callAjax(rparam,jsObj,url);
	}

function buildVoterAgeGroupResults(results,jsObj){
	$('#voterAgeGroupDiv').html('');
	$("#voterAgeGroupDiv").css("display","block");

	var str='';
	 $("#voterAgeGroupDiv").append('<h4 id="electionDisplaySpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">VOTERS</h4>');
	str+='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;color: black;"><tbody><tr>';
	str+='<td style="background:#D9EDF7">Total Voters</td><td>'+results.totalVoters+'</td></tr>';
	str+='<td style="background:#D9EDF7">Male</td><td>'+results.maleVoters+'</td><td>'+results.totalMalePercentage+'</td></tr>';
	str+='<td style="background:#D9EDF7">Female</td><td>'+results.femaleVoters+'</td><td>'+results.totalFemalePercentage+'</td>';
	str+='</tr></tbody></table>';

	$("#voterAgeGroupDiv").append(str);
}

function buildAgewiseVoterDetails(results,jsObj){
if(results != null){

$('#voterAgeGroupDiv1').html('');
$("#voterAgeGroupDiv1").css("display","block");
var str='';
 $("#voterAgeGroupDiv1").append('<h4 id="electionDisplaySpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">FIRST TIME VOTERS</h4>');
str+='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;color: black;"><thead>';
str+='<tr>';
str+='<th rowspan="2">First Time Voters</th>';
str+='<th colspan="2">Total Voters</th>';
str+='<th colspan="2">Total Percentage</th>';
str+='<th colspan="2">Voters</th>';
str+='</tr>';
str+='<tr>';
str+='<th>Total Voters</th>';
str+='<th>Total Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='</thead><tbody><tr>';
str+='<td>18-22</td>';
str+='<td>'+results.votersDetailsVO[0].totalVoters+'</td><td>'+results.votersDetailsVO[0].totalVotersPercent+'</td>';
str+='<td>'+results.votersDetailsVO[0].totalMaleVoters+'</td><td>'+results.votersDetailsVO[0].totalMaleVotersPercent+'</td>';
str+='<td>'+results.votersDetailsVO[0].totalFemaleVoters+'</td><td>'+results.votersDetailsVO[0].totalFemaleVotersPercent+'</td>';
str+='</tr></tbody></table>';

$("#voterAgeGroupDiv1").append(str);

//For Age wise voter group
$('#voterAgeGroupDiv2').html('');
$("#voterAgeGroupDiv2").css("display","block");
var str='';
 $("#voterAgeGroupDiv2").append('<h4 id="electionDisplaySpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">VOTERS BY AGE GROUP</h4>');
str+='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;color: black;"><thead>';
str+='<tr>';
str+='<th rowspan="2">Age Range</th>';
str+='<th colspan="2">Total Voters</th>';
str+='<th colspan="2">Total Percentage</th>';
str+='<th colspan="2">Voters</th>';
str+='</tr>';
str+='<tr>';
str+='<th>Total Voters</th>';
str+='<th>Total Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='</thead><tbody><tr>';
	for(var i=1;i<results.votersDetailsVO.length;i++){
	str+='<td>'+results.votersDetailsVO[i].ageRange+'</td>';
	str+='<td>'+results.votersDetailsVO[i].totalVoters+'</td><td>'+results.votersDetailsVO[i].totalVotersPercent+'</td>';
	str+='<td>'+results.votersDetailsVO[i].totalMaleVoters+'</td><td>'+results.votersDetailsVO[i].totalMaleVotersPercent+'</td>';
	str+='<td>'+results.votersDetailsVO[i].totalFemaleVoters+'</td><td>'+results.votersDetailsVO[i].totalFemaleVotersPercent+'</td>';
	str+='</tr>';
	}
str+='</tbody></table>';

$("#voterAgeGroupDiv2").append(str);
}
}
function redirectToFamilyInfo()
{ 
  var win=window.open('familyDetailsAction.action', '_blank');
  win.focus();
}
function redirectToCasteInfo(type)
{
var win=window.open('casteReportAction.action?type='+type+'', '_blank');
win.focus();
}
function redirectTopanchayatFamilyInfo()
{
	var win=window.open('criticalPanchayatsFamilyAction.action', '_blank');
  win.focus();
}

function getPublicationDate()
	{
	var constituencyID = document.getElementById("listConstituencyNames");
	var name=constituencyID.options[constituencyID.selectedIndex].name;
	var value=constituencyID.options[constituencyID.selectedIndex].value;

	var jsObj = 
	{
		selected:value,
		task:"getPublicationDate"
	}	
	var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function buildPublicationDateList(results)
	{
	publicationDatesList=results;
	var selectedElmt=document.getElementById("listPublicationValues");
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

	$('#listPublicationValues').val(largest);
	$('#listPublicationValues').trigger("change");

}
function redirectToVoterAddress()
{
var win=window.open('voterAddressAction.action', '_blank');
win.focus();
}
function redirectToVoterRange()
{
var win=window.open('voterLocationAction.action', '_blank');
win.focus();
}
</script>
</body>
</html>
