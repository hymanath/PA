<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:if test="${electionType != 'Parliament'}"><TITLE>${stateName}&nbsp;${electionType} Election Results ${year}</TITLE></c:if>
<c:if test="${electionType == 'Parliament'}"><TITLE>${electionType} Election ${year} Results  </TITLE></c:if>

<meta http-equiv="Content-Language" content="en">

<meta name="description" content="${stateName}&nbsp;${electionType} Election Details in india ${year},${stateName}&nbsp;${electionType} Election results ${year},ditrist wise results ,partywise results in all districts .">

<meta name="keywords" content="election details in india, ${stateName}&nbsp;${electionType} Election Results  ${year}  ,election analysis, ${stateName}&nbsp;${electionType} Election Details in india ${year}, district wise results, Party Results in all Districts with alliance and without alliance , party results with graphs, election commission of india,election commissioner of india, elections in india, indian elections ${year}, general elections india, forthcoming indian elections, india-elections, elections ${year}, parliament election, forthcoming indian elections, ${year} election results, assembly elections,upcoming indian elections, general elections ${year} schedule india, election schedule, opinion polls, key contenders of forthcoming general elections in india, party profiles, party manifestoes, india. ">

<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT> 
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></SCRIPT>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<link type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/highcharts/js/highcharts.js"></script>
<!-- Local Files-->
	<script type="text/javascript" src="js/CommentsDialog/commentsDialog.js"></script>
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
	
	<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){
    %>
	<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
	<% }
    %>
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<LINK rel="stylesheet" type="text/css" href="styles/styles.css">
<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/electionResultsPage.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">
<link href='http://fonts.googleapis.com/css?family=Asap' rel='stylesheet' type='text/css'>
<LINK rel="stylesheet" type="text/css" href="styles/jqueryDataTable/css/datatable.css">

<!--<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.columnFilter.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
-->

        <script src="js/jqueryDataTable/jquery.dataTables.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.columnFilter.js"></script>
<style>
.yui-skin-sam .yui-dt caption{
	background:#a3a3a3;
	
}
.container {
    	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    	background-color: #f0f0f0;
    	margin: 9px auto 40px;
    	width: auto;
    	padding: 10px;
	}
#stateResults{
 color:Maroon;
font-family:trebuchet MS;
font-size:14px;
margin-top:19px;
}
#contenttable {
padding: 0px;
width:1000px;
margin-left:auto;
margin-right:auto;
}
.resultsTable {
	border-collapse	:collapse;
	border-color	:#666666;
	border-width	:1px;
	color			:#333333;
	font-family		:verdana,arial,sans-serif;
	font-size		:11px;
	margin-top		:10px;
}
.yui-skin-sam .yui-dt table{
	color:DimGray;
	}
.main-bbg {
    background: url("images/icons/candidatePage/blue-crv-bgs.png") no-repeat scroll left -40px transparent;
    float: left;
    height: 35px;
    width: 6px;
}
.main-mbg {
    background-color: #06ABEA;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 977px;
	-moz-border-radius: 7px 7px 7px 7px;
	border-radius:7px;
}
.main-title-sec {
    background: url("images/icons/candidatePage/blue-crv-bgs.png") no-repeat scroll left top transparent;
    float: left;
    height: 35px;
    margin: 9px 0;
    padding-left: 6px;
}
.clear{
		clear:both;
		margin:0px; 
		padding:0px;
		line-height:0px;
		height:0px;
		font-size:0px;
		_display/**/:/**/ inline;
}
#accessDiv{
		-moz-border-radius: 7px 7px 7px 7px;
		background: none repeat scroll 0 0 #FFF8E5;
		 border: 1px solid #06ABEA;
		height: auto;
		padding: 10px;
		text-align: center;
		width: 500px;
		border-radius:7px;
		margin:0px auto;

}
#regionAccessDiv{
		
		background: none repeat scroll 0 0 #FFF8E5;
		 /*border: 1px solid #06ABEA;*/
		height: auto;
		padding: 10px;
		text-align: center;
		width: 500px;
		font-size:13px;
	}
#candidateResultAccessDiv .ui-widget-header{
	background: #06ABEA;
	border :0px;
	font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
}
#districtAccessPopupDiv .ui-widget-header{
	background: #06ABEA;
	border :0px;
	font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
}
.ui-dialog .ui-dialog-title {
    float: left;
    padding-top: 0;
}
.ui-draggable .ui-dialog-titlebar {
    cursor: move;
    padding: 0 12px;
}
.ui-widget-content a{
	color:blue;

}
.partyperformance {
    background: none repeat scroll 0 0 #f5f5f5;
    border-left: 1px solid #d3d3d3;
    border-right: 1px solid #d3d3d3;
	border-bottom: 1px solid #d3d3d3;
    color: #606060;
    padding-top: 5px;
    width: 998px;
	margin-bottom:10px;
}

#districtWiseGraph{display:table;margin-left:auto;margin-right:auto;}
#districtWiseSeatsGraph{display:table;margin-left:auto;margin-right:auto;}

#distwiseGraph .yui-skin-sam,#distwiseGraph .yui-skin-sam > table{width:980px;display:table;margin-left:auto;margin-right:auto;}

.yui-dt-data > tr td:first-child{padding-left:5px;}
#districtResults{background:#f9f9f9;padding:5px;}
#MahaKutami{background:#F9F9F9;padding:5px;}

.dashBoardtabsDiv {
    background: none repeat scroll 0 0 #A3A3A3;
    border: 1px solid #CDCDCD;
    border-radius: 5px 5px 5px 5px;
    font-family: verdana;
    font-weight: bold;
    padding: 10px 7px 10px;
    text-align: left;
    width: 65%;
}

.dashBoardtabsDivSelected
	{
	background :skyBlue; color: #000000;
	border-radius:10px;
	-moz-border-radius:10px;
	}
.dashBoardtabsDiv a {
    color: #000000;
    cursor: pointer;
    font-family: verdana;
    font-weight: bold;
    padding: 5px 16px 5px;
    text-decoration: none;
}
.buttonClass {
    background-color: #F52B43;
    border-radius: 6px 6px 6px 6px;
    color: #FFFFFF;
    cursor: pointer;
    font-weight: bold;
    padding: 4px;
}
.partyNames{
   height:26px;
   padding:3px;
   width:121px;
}
.topStories {
    border-left: 1px solid #d3d3d3;
    border-right: 1px solid #d3d3d3;
	border-bottom: 1px solid #d3d3d3;
    color: #606060;
    padding-top: 5px;
    width: 998px;
	padding-bottom: 10px;
	margin-bottom:5px;
	background:#f5f5f5;
}
.boxshawdow{
    	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
		box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
}
table.searchresultsTable,table.searchresultsTable * td,table.searchresultsTable * th{border:1px solid #d3d3d3;border-color:#d3d3d3 !important;}

#UbanPercentageWiseGraphSelectionDiv
{
	background: none repeat scroll 0 0 #EBE4F2;
    border: 1px solid #B7D3FC;
    margin-left: 150px;
    margin-right: 150px;
	margin-bottom : 15px;
    padding: 5px;
    width: 610px;
}
#wonCandidatesTable tr:nth-child(even){
 background:#EDF5FF;
}
#wonCandidatesTable tr:nth-child(odd){
 background:#FFFFFF;
}
#ConstituecyAreaTypeSelectOptionsDiv
{
	background: none repeat scroll 0 0 #EBE4F2;
    border: 1px solid #B7D3FC;
    margin-left: 150px;
    margin-right: 150px;
	margin-bottom : 15px;
    padding: 5px;
    width: 610px;
}
.text_filter{
   width:120px;
}
.number_filter{
  width:90px;
}
#wonCandidatesTableSearch th,#wonCandidatesTablefooter th{
    background-color: #C4DEFF;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 0px;
    padding-right: 0px;
    padding-top: 10px;
    text-align: center;
}

#wonCandidatesTableSearch th{
  cursor:pointer;
}
   table.dataTable thead th {
    border-bottom-color: black;
    border-bottom-style: solid;
    border-bottom-width: 1px;
    cursor: pointer;
    font-weight: bold;
}
 #wonCandidatesTableDiv{
    border-left: 1px solid #d3d3d3;
    border-right: 1px solid #d3d3d3;
	border-bottom: 1px solid #d3d3d3;
    color: #606060;
    width: 998px;
	padding-bottom: 30px;
	margin-bottom:5px;
	background:#f5f5f5;
	font-family: arial;
    font-size: 13px;
}
 #wonCandidatesTable_wrapper{
   padding-top:10px;
 }
.textalignclass{
  text-align:center;
}
.search_init{
  color:#d3d3d3 !important;
}
#wonCandidatesTable{
  margin-left:auto;
  margin-right:auto;
}
#overallDataTable{
   margin-left: 60px;
    margin-top: 19px;
}
#captionStyle {
    background: none repeat scroll 0 0 #A3A3A3;
    color: #FFFFFF;
    font-family: Trebuchet MS;
    font-size: 15px;
    font-style: normal;
    font-weight: bold;
    height: 30px;
    line-height: 1;
    padding: 5px 17px 0.5em;
    text-align: center;
}
#genderWiseResultsDataTable .yui-dt-liner{
 padding: 4px;
}
#partyPerfResultsDataTable .yui-dt-liner{
 padding: 4px;
}

#regionWiseDataTableDiv caption {
    background: none repeat scroll 0 0 #A3A3A3;
    color: #FFFFFF;
    font-family: Trebuchet MS;
    font-size: 15px;
    font-style: normal;
    font-weight: bold;
    height: 30px;
    line-height: 1;
    padding: 5px 17px 0.5em;
    text-align: center;
}
</style>
<SCRIPT type="text/javascript">
$(document).ready(function(){
	  
	    <c:if test="${loginStatus ==null && sessionScope.USER == null}">
		  $("#partyperformance").html("");
		</c:if>
});

var electionId = '${electionId}';
var electionType = '${electionType}';
var stateID =  '${stateID}' ;
var stateName = '${stateName} ';
var year = '${year}';
var electionTypeId = '${electionTypeId}';
var chkSize= 3;
var checkedId = "topVotes"; 
var maleFemale =false;
var ruralUrban =false;
var ruralUrbanChart = false;
var electionResultsObj = {
	partyWiseResultsArr:[],
	allianceResultsArr:[],
	partyWiseResultsWithoutAllianceArr:[],
    districtWiseResultsWithoutAllianceArr:[],
    allianceGroupNamesArray:[]
};
var allianceResults;
var partyResDistrictWise;
google.load("visualization", "1", {packages:["corechart"]});

var caption;
var resultsGlobal, graphImagesCarousel,allianceResultsGlobal;
var UbanPercentageResult = null;
var areaTypeWisePartiesResults = null;
if(electionType != 'Parliament')
{
	caption = "Partywise Results In All Districts"
		
} else caption = "Partywise Results In All States";
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String party = rb.getString("party");
		String totalParticipated = rb.getString("totalParticipated"); 
		String seatsWon = rb.getString("seatsWon");
		String seatsLost = rb.getString("seatsLost");
		String votesEarned = rb.getString("votesEarned");
		String percentage  = rb.getString("percentage");
		//String address  = rb.getString("address");
		//String location  = rb.getString("location");
		//String name = rb.getString("name");
		//String email = rb.getString("email");
		//String telephoneNo = rb.getString("telephoneNo");
		//String designation = rb.getString("designation"); 
		
%> }
var partywiseResultsDataTable,allianceResultsDataTable,candidateDetailsDialog;         
function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task == "getRegionWisePartyElectionResults")
									{
										buildRegionWiseElectionsResultsGraph(jsObj,myResults);
										buildRegionWiseDataTable(myResults);
										hideImg();
									}
									else if(jsObj.task == "elctionsBasicInfo")
									{										
										showElectionBasicInfo(myResults);											
									} else if(jsObj.task == "getResultForAnElection")
									{	
										var elmt = document.getElementById("electionPageAjaxImgDiv");
										if(elmt)
											elmt.style.display = 'none';
										showAllianceDetails(myResults);
										showStatewiseResultsBarChart(myResults);									
										showPartywiseDetailsDataTable(myResults);								
										
										<c:if test="${sessionScope.USER != null}">
										if(myResults.electionResultsInDistricts.allPartiesResults != null &&
											myResults.electionResultsInDistricts.allPartiesResults.length > 0)
										{
											showDistrictWiseResultsLineGraph(myResults);
										    <c:if test="${hasDeatiledAnalysis}">
											buildAllDistrictResultsDataTable(myResults);
										 
											buildAllianceDistrictResultsDataTable(myResults.electionResultsInDistricts,"all", "null");
										    </c:if>
										}else
										{
											var toolsDiv = document.getElementById("analysisToolsDataDiv");
											var subRegionsDiv = document.getElementById("subRegionWiseDetailsDiv");
                                            toolsDiv.innerHTML='';
											subRegionsDiv.innerHTML='';
										}
										</c:if>

										/*<c:if test="${!hasDeatiledAnalysis}">
											var subRegionsDiv = document.getElementById("subRegionWiseDetailsDiv");
											subRegionsDiv.innerHTML='';
										</c:if>*/
										
									<c:if test="${hasDeatiledAnalysis == false}">
										showAlertMsg("accessDiv");
										
										</c:if>

                                        if(electionType != 'Parliament'){
										if(myResults.hasRegions == true){
                                                
											var elmtDiv = document.getElementById("stateRegionsDiv");
											var str = '';
											str+='<input type="radio" name="regions" value="overall" checked="checked" title="Select to view overall results" onclick="showOverallResults()">							Overall          ';
											str+='<input type="radio" name="regions" value="region" title="Select to view region wise results" onclick="showRegionWiseResults()">				Region Wise';
											str+='&nbsp;<span id="ajaxImg" style="display:none">';
											 str+='<img height="" width=""  src="images/icons/search.gif"></img>';
											 str+='</span>';
											elmtDiv.innerHTML = str;
										}
									  }
									} 
                                    else if(jsObj.task == "getPartyGenderInfo") {
									  buildGenderCountResultsDataTable("genderWiseResultsDataTable",myResults);
									}
									else if(jsObj.task == "getConstituencyAreaTypeWiseResult") {
									  areaTypeWisePartiesResults = myResults;
									  buildPartyPerfRulUrbanDataTable('All');
									  buildConstituecyAreaTypeSelectOptionsDiv('All');
									}
									else if(jsObj.task == "getConstituencyAreaTypeWiseOverview")
									{
									  buildConstituencyAreaTypeWiseOverviewTable(myResults);
									}
									else if(jsObj.task == "TopVotesGained") {
									  buildTopStoriesTable(myResults,"topVotesGained");
									}
									else if(jsObj.task == "TopVotesGainedPerc") {
									  buildTopStoriesTable(myResults,"topVotesGainedPerc");
									}
									else if(jsObj.task == "HighestMarginGained") {
									  buildTopStoriesTable(myResults,"highestMarginGained");
									}
									else if(jsObj.task == "LowestMarginGained") {
									  buildTopStoriesTable(myResults,"lowestMarginGained");
									}
									else if(jsObj.task == "HighestAssets") {
									  buildTopStoriesTable(myResults,"highestAssetsDetails");
									}
									else if(jsObj.task == "getPartiesConstituencyUbanPercentage")
									{
										UbanPercentageResult = myResults;	buildUbanPercentageWisePartyDetailsGraph();
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
function buildTopStoriesTable(result,id){

   var str = '';
   document.getElementById("searchAjaxImgSpan").style.display = "none";
   if(result != null && result.length >0 )
   {
   var value='';
   <c:if test="${electionType != 'Parliament'}">
       value = '${stateName}&nbsp;${electionType} Election ${year}'; 
   </c:if>
   <c:if test="${electionType == 'Parliament'}">
    value+= ' ${electionType} Election ${year} ';
  </c:if>
  
	 str+='<div style="padding-top:10px;">';
	 if(id == "topVotesGained")
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); "><td> Highest Votes Earned By Candidates In '+value+'</td></tr></table>';
	 if(id == "topVotesGainedPerc")
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); "><td>Highest Votes Percentage Earned By Candidates In '+value+'</td></tr></table>';
	 if(id == "highestMarginGained")
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); "><td>Highest Margin Votes Earned By Candidates In '+value+'</td></tr></table>';
	 if(id == "lowestMarginGained")
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); "><td> Lowest Margin Votes Earned By Candidates In '+value+'</td></tr></table>';
     if(id == "highestAssetsDetails")
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); "><td> Candidates With Highest Assets In '+value+'</td></tr></table>';
		 
	 str+= '<table class="searchresultsTable" style="width:96%;padding-top:10px;">';
	 str+= '<tr><th style="background-color : #C4DEFF;width:200px;">Candidate Name</th><th style="background-color : #C4DEFF">Constituency Name</th><th style="background-color : #C4DEFF">Party Name</th><th style="background-color : #C4DEFF">Total Valid Polled Votes</th><th style="background-color : #C4DEFF">Votes Earned</th>';
	 
	 if(id == "topVotesGained" || id == "highestAssetsDetails")
	   str+= '<th style="background-color : #C4DEFF">Votes Percentage</th>';
	 else if(id == "topVotesGainedPerc")
	   str+= '<th style="background-color : #C4DEFF">Votes Percentage</th>';
	 else if(id == "highestMarginGained" || id == "lowestMarginGained")
	   str+= '<th style="background-color : #C4DEFF">Margin Votes</th>';
	  	 
	 if(id == "highestAssetsDetails")
	  str+= '<th style="background-color : #C4DEFF">Assets</th>';
	 
	 str+= '<th style="background-color : #C4DEFF">Result(Rank)</th></tr>';
	 for(var i in result)
	 {
	    str+= '<tr>';
	    str+= '     <td style="background-color : #FFFFFF"><a href="candidateElectionResultsAction.action?candidateId='+result[i].candidateId+' ">'+result[i].candidateName+'</a></td>';
		
		if(electionType != "Muncipality" && electionType != "Greater Municipal Corp"  && electionType != "Corporation")
		   str+= '		<td style="background-color : #FFFFFF"><a href="constituencyPageAction.action?constituencyId='+result[i].constiId+' ">'+result[i].constiName+'</a></td>';
        else
		   str+= '		<td style="background-color : #FFFFFF">'+result[i].constiName+'</td>';
		   
		if(result[i].partyName != 'IND')
		   str+= '		<td style="background-color : #FFFFFF"><a href="partyPageAction.action?partyId='+result[i].partyId+' ">'+result[i].partyName+'</a></td>';
		else
		   str+= '		<td style="background-color:#FFFFFF">'+result[i].partyName+'</td>';
		   
		str+= '     <td style="text-align:center;background-color : #FFFFFF">'+result[i].votesEarned+'</td>';

		if(id == "topVotesGained")
		  str+= '     <td style="text-align:center;background-color : #F3F3F3">'+result[i].validVotes+'</td>'; 
	    else
	      str+= '     <td style="text-align:center;background-color : #FFFFFF">'+result[i].validVotes+'</td>'; 
 
        if(id == "topVotesGained" || id == "topVotesGainedPerc" || id == "highestAssetsDetails")	
         {	 
           if(id == "topVotesGainedPerc")		
		     str+= '	    <td style="text-align:center;background-color:#F3F3F3">'+result[i].votesPercentage+'</td>';
		   else
		     str+= '	    <td style="text-align:center;background-color:#FFFFFF">'+result[i].votesPercentage+'</td>';
		 }
	    else
		   str+= '	    <td style="text-align:center;background-color : #F3F3F3">'+result[i].totalVotes+'</td>';
		   
		if(id == "highestAssetsDetails")
		  str+= '	    <td style="text-align:center;background-color:#F3F3F3">'+result[i].assets+'</td>';
		
		if(result[i].rank == 1)
		  str+= '     <td style="text-align:center;background-color : #FFFFFF"><font style="color:green">Won</font>('+result[i].rank+')</td>'; 
	    else
	      str+= '     <td style="text-align:center;background-color : #FFFFFF"><font style="color:red">Lost</font>('+result[i].rank+')</td>'; 
		
		str+= '</tr>';
	 }
	 
	 str+= '</table>';
	 str+= '</div>';
   }
   else
   {
     str+= '<b>No Records Found</b>';
   }
   document.getElementById(id).innerHTML = str;
}
function setId(id)
{
   checkedId = id;
}
function checkUserStatus(){
       <c:if test="${hasDeatiledAnalysis == false}">
		
			  $("#candidateResultAccessDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#000000">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'}
				});
		showAlertMsg("candidateResultAccessDiv");
		document.getElementById(checkedId).checked= true;
		return ;
		</c:if>
    getAllTopStories(chkSize,'HighestAssets');
}
function toggleOption(id){//year
 if(document.getElementById(id).value == "Hide Highlights Of ${year} Election Results")
   document.getElementById(id).value = "Show Highlights Of ${year} Election Results";
 else
   document.getElementById(id).value = "Hide Highlights Of ${year} Election Results";
   $("#topStoriesDIV").slideToggle("slow");
 }
 function getTopStoriesForParty(id){
   <c:if test="${hasDeatiledAnalysis == false}">
		
			  $("#candidateResultAccessDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#000000">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'}
				});
		showAlertMsg("candidateResultAccessDiv");
		document.getElementById(id).value = 0;
		return ;
		</c:if>
  if(document.getElementById("topVotes").checked == true)
    getAllTopStories(chkSize,"TopVotesGained");
  if(document.getElementById("topPercVotes").checked == true)
    getAllTopStories(chkSize,"TopVotesGainedPerc");
  if(document.getElementById("topMargin").checked == true)
    getAllTopStories(chkSize,"HighestMarginGained");
  if(document.getElementById("lowMargin").checked == true)
    getAllTopStories(chkSize,"LowestMarginGained");
  if(document.getElementById("highestAssets").checked == true)
    getAllTopStories(chkSize,"HighestAssets");
 }
function getTopStories(id,size){
  
  if(size !=3)
   {
     <c:if test="${hasDeatiledAnalysis == false}">
		
			  $("#candidateResultAccessDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#000000">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'}
				});
		showAlertMsg("candidateResultAccessDiv");
		return ;
		</c:if>
   }
  chkSize = size;

  $("#top3Id").removeClass("dashBoardtabsDivSelected");
  $("#top5Id").removeClass("dashBoardtabsDivSelected");
  $("#top10Id").removeClass("dashBoardtabsDivSelected");
  $("#"+id).addClass("dashBoardtabsDivSelected");
  
  if(document.getElementById("topVotes").checked == true)
    getAllTopStories(size,"TopVotesGained");
  if(document.getElementById("topPercVotes").checked == true)
    getAllTopStories(size,"TopVotesGainedPerc");
  if(document.getElementById("topMargin").checked == true)
    getAllTopStories(size,"HighestMarginGained");
  if(document.getElementById("lowMargin").checked == true)
    getAllTopStories(size,"LowestMarginGained");
  if(document.getElementById("highestAssets").checked == true)
    getAllTopStories(size,"HighestAssets");
}
function showAlertMsg(divElmt){

	$("#accessDiv").css("display","block");
		var accessDivElmt = document.getElementById(divElmt);
		var str='';

	str+='<img src="images/icons/smiley_sad.png" alt="sorry" style="display:inline;"/>&nbsp;&nbsp;';
	str+='<h3 style="color: rgb(255, 0, 0); display: inline; position: relative; top: -10px; font: bold 12px/25px verdana;">Sorry,you don\'t have access privileges to view detailed report. <span style="margin-left:35px;">Please contact us for access privileges.</span></h3>';
	str+='<span style="font: bold 14px/35px Trebuchet MS,Arial,Helvetica,sans-serif;text-align:center;color:#000;display:block;">';
	str+='Phone No:+91 40 40124153 / +91 96766 96760<br />';
	str+='Email: <a href="mailTo:info@partyanalyst.com">info@partyanalyst.com</a></span>';
	
	accessDivElmt.innerHTML=str;
}

function showImg(){
	
	var ajaxImgElmt = document.getElementById("ajaxImg");
	  ajaxImgElmt.style.display = "";
}
function hideImg(){
      
	  var ajaxImgElmt = document.getElementById("ajaxImg");
	  ajaxImgElmt.style.display = 'none';
}

function buildRegionWiseElectionsResultsGraph(jsObj,results)
{
	/**************************/
	var chartSeries = new Array();
	for(var i in results.partyResultsInRegionVOLst){
		var seatsWon = new Array();
		for(var j in results.partyResultsInRegionVOLst[i].partyResultsInRegion)
			seatsWon.push(results.partyResultsInRegionVOLst[i].partyResultsInRegion[j].totalSeatsWon);

		var obj = {
				 type: 'column',
		         name: results.partyResultsInRegionVOLst[i].regionName,
		         data: seatsWon
			  };
		chartSeries.push(obj);
	}

	var avgSeatsWon = new Array();
	for(var i in results.partyAndAVGSeatsWon)
		avgSeatsWon.push(results.partyAndAVGSeatsWon[i].averageSeatsWon);

	var obj = {
			 type: 'spline',
	         name: 'Average',
	         data: avgSeatsWon
		  };

	chartSeries.push(obj);

	var	regions = new Array();
	for(var i in results.partyResultsInRegionVOLst)
	{
		var obj = {
				name: results.partyResultsInRegionVOLst[i].regionName,
	            y: results.partyResultsInRegionVOLst[i].constituenciesCount,
	            
			};
			regions.push(obj);
	
	var objForPie = {
	         type: 'pie',
	         name: 'Total Constituencies',
	         data: regions,
	         center: [100, 80],
	         size: 100,
	         showInLegend: false,
	         dataLabels: {
	            enabled: false
	         }
	      };
    }
	chartSeries.push(objForPie); 
	/**************************/
	
	var chart;
	$(document).ready(function() {
	   chart = new Highcharts.Chart({
	      chart: {
	         renderTo: 'graphImage'
	      },
	      title: {
	         text: 'Region Wise Parties Results In State'
	      },
	      xAxis: {
	         categories: results.staticParties
	      },
	      tooltip: {
	         formatter: function() {
	            var s;
	            if (this.point.name) { // the pie chart
	               s = ''+
	                  this.point.name +': '+ this.y +' Constituencies';
	            } else {
	               s = ''+
	                  this.x  +': '+ this.y;
	            }
	            return s;
	         }
	      },
	      labels: {
	         items: [{
	            html: 'Region Wise Constituencies',
	            style: {
	               left: '40px',
	               top: '8px',
	               color: 'black'            
	            }
	         }]
	      },
	      series: chartSeries
	   });
	});
	
}

function showOverallResults()
{
	showImg();
   getResultsForAnElection(stateID,electionType,year);
}

function showRegionWiseResults()
{
	<c:if test="${loginStatus !=null && sessionScope.USER != null}">
		showImg();
		var jsObj = {
					electionId:electionId,
					stateID:stateID,
					task:"getRegionWisePartyElectionResults"
				};
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/regionWisePartyElectionResultsAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	</c:if>
	<c:if test="${sessionScope.USER == null}">
	 document.getElementById("regionAccessDiv").style.display='block';
		var str='';
		//alert("not logged in ......");
		$("#regionAccessDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#000">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'},
								
						});
		str+='<div>';
		str+='<h4 style="color:#ff0000;display:inline;position:relative;top:0px;">'; 
		str+='<div>Please register to view this content.   Click here to <a href="freeUserRegistration.action">Register</a></div><div style="margin: 10px;"> Already A member please login to get access. <div style="margin: 10px;">Click here to <a href="loginInputAction.action">Login</a></div></div></h4>';
		str+='</div>';
	document.getElementById("regionAccessDiv").innerHTML = str;
		</c:if>
}
function getPartyGenderInfo()
{
	var jsObj = {
	            time:new Date().getTime(),
				electionId:electionId,
				task:"getPartyGenderInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportWithGenderAction.action?"+param;
	callAjax(param,jsObj,url);

}
function getAllTopStories(maxResult,task)
{
    var partyId= 0 ;
   document.getElementById("searchAjaxImgSpan").style.display = "block";
   document.getElementById("topVotesGained").innerHTML = "";
   document.getElementById("topVotesGainedPerc").innerHTML =  "";
   document.getElementById("highestMarginGained").innerHTML = "";
   document.getElementById("lowestMarginGained").innerHTML =  "";
   document.getElementById("highestAssetsDetails").innerHTML =  "";
   if(electionType == 'Assembly')
   {
     if(document.getElementById("partyTopStories") != null)
	 {
       var partyIdEle = document.getElementById("partyTopStories");
       partyId  = partyIdEle.options[partyIdEle.selectedIndex].value;
	 }
   }
   else
   {
     if(document.getElementById("partiesNames") != null)
	 {
       var partyIdEle = document.getElementById("partiesNames");
       partyId  = partyIdEle.options[partyIdEle.selectedIndex].value;
	 }
   }
   <c:if test="${!hasDeatiledAnalysis}">
      partyId = 0;
	  maxResult = 3;
	  if(task == "HighestAssets")
	   return;
   </c:if>
	var jsObj = {
	            time:new Date().getTime(),
				maxResult:maxResult,
				electionId:electionId,
				partyId:partyId,
				task:task
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportWithGenderAction.action?"+param;
	callAjax(param,jsObj,url);

}
function getConstituencyAreaTypeWiseResult()
{
  document.getElementById("topVotesGained").innerHTML = "";
  document.getElementById("topVotesGainedPerc").innerHTML =  "";
  document.getElementById("highestMarginGained").innerHTML = "";
  document.getElementById("lowestMarginGained").innerHTML =  "";
	var jsObj = {
	            time:new Date().getTime(),
				electionId:electionId,
				task:"getConstituencyAreaTypeWiseResult"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportWithGenderAction.action?"+param;
	callAjax(param,jsObj,url);

}

function getConstituencyAreaTypeWiseOverview()
{
	var jsObj = {
	            time:new Date().getTime(),
				electionId:electionId,
				task:"getConstituencyAreaTypeWiseOverview"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "electionDetailsReportWithGenderAction.action?"+param;
	callAjax(param,jsObj,url);
}

function buildConstituencyAreaTypeWiseOverviewTable(result)
{
	var divEle = document.getElementById("constituencyTypeWiseOverviewTable");
	var str = '';
	if(result == null || result.length == 0)
	{
		divEle.innerHTML = str;
		document.getElementById("constituencyTypeWiseOverviewGraph").innerHTML = '';
		return;
	}
 $("#constituencyTypeWiseOverviewGraph").css("margin-top","53px");
	str += '<table cellspacing="0px" cellpadding="0px"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);"><td id="captionStyle">';

	if('${electionType}' == 'Assembly')
		str += '${stateName}&nbsp;';
	
	str +='${electionType} Election Rural/Urban Constituencies Overview </td></tr></table>';

	str +='<table class="searchresultsTable" align="left" style="width:550px"> ';

	str +='	<tr>';
	str +='		<th style="background-color : #C4DEFF"></th>';
	str +='		<th style="background-color : #C4DEFF">Constituencies</th>';
	str +='		<th style="background-color : #C4DEFF">Total Votes</th> ';
	str +='		<th style="background-color : #C4DEFF">Total Polled Votes</th> ';
	str +='		<th style="background-color : #C4DEFF">Voting Percentage </th> ';
	str +='	</tr>';

	for(var i=0;i<result.length;i++)
	{
		str +='	<tr>';
		str +='		<th style="background-color : #C4DEFF">'+result[i].constiName+'</th>';
		str +='		<th style="background-color : #FFFFFF">'+result[i].totalParticipated+'</th> ';
		str +='		<th style="background-color : #FFFFFF">'+result[i].totalVoters+'</th> ';
		str +='		<th style="background-color : #FFFFFF">'+result[i].validVotes+'</th> ';
		str +='		<th style="background-color : #FFFFFF">'+result[i].votesPercentage+'</th>';
		str +='	</tr>';
	}
	str +='</table>';
	divEle.innerHTML = str;
  if(result.length > 1){
	var data = new google.visualization.DataTable();
	data.addColumn('string','Area Type');
	data.addColumn('number','Count');
	data.addRows(result.length-1);

	for(var j=1; j<result.length; j++)
	{
		data.setValue(j-1,0,result[j].constiName);
		data.setValue(j-1,1,result[j].totalParticipated);
	}
	var chart = new google.visualization.PieChart(document.getElementById('constituencyTypeWiseOverviewGraph')); 
	chart.draw(data,{width:300, height: 170, title:'Constituency Area Type wise % Graph'});
   }
}


function buildConstituecyAreaTypeSelectOptionsDiv(selectOption)
{
	document.getElementById('ConstituecyAreaTypeSelectOptionsDiv').style.display = 'block';
	var divEle = document.getElementById('ConstituecyAreaTypeSelectOptionsDiv');
	var str = '';
	var myResults = areaTypeWisePartiesResults;

	if(myResults == null || myResults.length == 0)
	{
	    document.getElementById('ConstituecyAreaTypeSelectOptionsDiv').style.display = 'none';
		divEle.innerHTML = '';
		return;
	}
     $("#ubanDataHideShowDiv").show();
	str += '<b>Select Options to View Party Performance In Rural/Urban Constituencies</b>';
	if(selectOption == 'All')
	{
		str += '<table><tr><td width="50px"><input type="radio" checked="true" onclick="buildPartyPerfRulUrbanDataTable(\'All\'),buildUbanPercentageWisePartyDetailsGraph()" value="All" name="areaTypeRadio"><b><font color="#4B74C6">&nbsp;All</font></b></input></td>';
		str += '<td><input type="radio" name="areaTypeRadio" onClick="buildConstituecyAreaTypeSelectOptionsDiv(\'Partywise\')" value="Partywise"><b><font color="#4B74C6">&nbsp;Partywise</font></b></input></td>';
		str += '</tr></table>';
	}
	else if(selectOption == 'Partywise')
	{
		str += '<table><tr>';
		str += '<td width="50px"><input type="radio" onclick="buildConstituecyAreaTypeSelectOptionsDiv(\'All\'),buildPartyPerfRulUrbanDataTable(\'All\'),buildUbanPercentageWisePartyDetailsGraph()" value="All" name="areaTypeRadio"><b><font color="#4B74C6">&nbsp;All</font></b></input></td></td>';
		str += '<td width="80px"><input type="radio" name="areaTypeRadio" checked="true" onClick="buildConstituecyAreaTypeSelectOptionsDiv(\'Partywise\')" value="Partywise"><b><font color="#4B74C6">&nbsp;Partywise</font></b></input></td>';
		str += '<td><select class="selectBoxStyle" id="partySelectInUrbanPer" multiple="multiple">';
		
		for(var i=0;i<myResults.length;i++)
		{
			str += '<option value="'+myResults[i].partyName+'">'+myResults[i].partyName+'</option>';
		}

		str += '</select></td>';
		str += '<td><input type="button" class="buttonClass" value="View" onclick="buildPartyPerfRulUrbanDataTable(\'Partywise\'),buildUbanPercentageWisePartyDetailsGraph()"/><br><br><b>Press Ctrl Key To Select Multiple Parties</b></td>';
		str += '</tr></table>';
	}

	divEle.innerHTML = str;

}

function getPartiesConstituencyUbanPercentage()
{
	var jsObj = {
	            time:new Date().getTime(),
				electionId:electionId,
				task:"getPartiesConstituencyUbanPercentage"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "electionDetailsReportWithConstituencyUrbanPercentageAction.action?"+param;
	callAjax(param,jsObj,url);
}

function buildUbanPercentageWisePartyDetailsGraph()
{ 
	var partySelect = null;
	var selOption = null;
	var perSeats = document.getElementsByName('urbanPer');
	var partyRall = document.getElementsByName('areaTypeRadio');
	results = UbanPercentageResult;
	
	if(perSeats != null && perSeats.length > 0)
		for(var x=0;x<perSeats.length;x++)
			if(perSeats[x].checked)
				selOption = perSeats[x].value;

	if(partyRall != null && partyRall.length > 0)
		for(var x=0;x<partyRall.length;x++)
			if(partyRall[x].checked)
				partySelect = partyRall[x].value;

	if(partySelect == null)
		partySelect = 'All';

	if(selOption == null)
		selOption = 'percentage';

	var results = UbanPercentageResult;
	var partyN = new Array();

	if(partySelect == "Partywise")
	{
		var selectedPartiesResultArray = new Array()
		var selObj = document.getElementById('partySelectInUrbanPer');
		
		for (var i=0; i<selObj.options.length; i++)
		{
			if (selObj.options[i].selected)
					partyN.push(selObj.options[i].text);     
		}

	}

	if(results != null && results.length >0)
   {
    ruralUrbanChart =true;
	$("#ubanDataHideShowDiv").show();
	document.getElementById("partyperformance").style.display = "block";
	
	var ctitle = null;
	if(selOption == 'percentage')
		ctitle = 'Parties Performance By Urban Population Increase Based On Voting Percentage';
	else if(selOption == 'seats')
		ctitle = 'Parties Performance By Urban Population Increase Based On Seats Won';

	var partiesArray = new Array();
	
	if(partySelect == 'All')
		for(var i=0; i<results[0].partyResults.length; i++)
			partiesArray.push(results[0].partyResults[i].partyName);
	else
		for(var i=0; i<partyN.length; i++)
			partiesArray.push(partyN[i]);

	var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Range');

	if(partySelect == "All")
		for(var i=0; i<results[0].partyResults.length; i++)
	        data.addColumn('number', results[0].partyResults[i].partyName);
	else
		for(var i=0; i<partyN.length; i++)
			data.addColumn('number',partyN[i]);

	var resultsVar = new Array();
	
	for(var i=0; i<results.length; i++)
	{
		resultsVar.push(results[i]);
	}

	data.addRows(resultsVar.length);

	for(var i=0; i<resultsVar.length; i++)
	{
		data.setValue(i, 0, resultsVar[i].range);
		var indexj = 0;

		for(var j=0; j<resultsVar[i].partyResults.length; j++)
		{
			if(partiesArray.indexOf(resultsVar[i].partyResults[j].partyName) != -1)
			{
				if(selOption == 'percentage')
					data.setValue(i, indexj+1,resultsVar[i].partyResults[j].votesPercentage);
				else
					data.setValue(i, indexj+1,resultsVar[i].partyResults[j].totalSeatsWon);
				indexj++;
			}
		}
	}

	 var chart = new google.visualization.LineChart(document.getElementById('UbanPercentageWiseGraph'));
        chart.draw(data,{curveType: "function",width:880, height: 430,pointSize: 4, title:ctitle ,colors:staticColors,slantedText:true,slantedTextAngle:35,titleTextStyle:{color:'#DC1CF2'}});
  }  
  else
  {
     ruralUrbanChart =false;
     if(maleFemale == true || ruralUrban == true || ruralUrbanChart == true)
	  document.getElementById("partyperformance").style.display = "block";
	 else
	  document.getElementById("partyperformance").style.display = "none";

	 document.getElementById("UbanPercentageWiseGraphSelectionDiv").innerHTML = '';
	 document.getElementById("UbanPercentageWiseGraphSelectionDiv").style.display = "none";
  }  
}

function showDistrictWiseResultsLineGraph(results)
{

	/*var chartName = results.districtWiseElecResultsChartName;
	var detailedResultsChart = results.districtWiseElecDetailedResultsChartName;
	var districtWiseGraphEl = document.getElementById("districtWiseGraph");

	var contentStr = '';
	contentStr+='<IMG src="charts/'+chartName+'" style="margin-left:10px;"></IMG>';
	districtWiseGraphEl.innerHTML = contentStr;	

	var districtWiseDetailedGraphE1 = document.getElementById("detailedGraph"); 
    var contentStr1 = '';
	contentStr1+='<a href="javascript:{}" class="viewChartsForResults" title="Click to view Detailed Results Chart" onclick="showDetailedResultsChart(\''+detailedResultsChart+'\')">View Detailed Results Chart</a>';	
	districtWiseDetailedGraphE1.innerHTML = contentStr1;*/

	getDistrictResultsInteractiveChartVotesPercent(results,null);
	getDistrictResultsInteractiveChartSeatsWon(results,null);

}

function getDistrictResultsInteractiveChartSeatsWon(results,partyN)
 {
	 
	 var districtWiseGraphEl = document.getElementById("districtWiseSeatsGraph");
	 var chartColumns = results.partiesDistLevel;
	 var chartRows = results.partyResultsforDistWiseChart;
		
	 var data = new google.visualization.DataTable();
	 var partiesArray = new Array();
	
	 data.addColumn('string', 'Party');
	  var partysCount = 0;
      //for columns
	  for(var i in chartColumns){
		 if(partysCount > 15)
		  {
			  break;
		  }

		  if(partyN != null)
		  {
            data.addColumn('number', partyN);
			 partiesArray.push(partyN);
			break;
		  }
		  else
		  {
			data.addColumn('number', chartColumns[i].name);
			partiesArray.push(chartColumns[i].name);
			partysCount++;
		  }
	  }

      for(var j in chartRows)
	  {
		  
		var partyCount = 0;
        var array = new Array();
		array.push(chartRows[j].partyName);

		for(var k in chartRows[j].partyResultsInDistricts)
		{
		  if(partyN == null && partyCount > 15)
		  {
			  break;
		  }

		  if(partyN != null)
		  {
			  if(chartRows[j].partyResultsInDistricts[k].districtName == partyN)
			  {
				  var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
				   	array.push(seatsWon);
				     partyCount++;
                   break;
			  }
            
		  }
		  else{
             var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
			  array.push(seatsWon);
			 partyCount++;
			
		  }
          
		}

        data.addRow(array);
		
	  }

	  //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	  
	  var locStr = '';
	  if(electionType == 'Parliament')
		locStr = ' State ';
	  else if(electionType == 'Assembly')
		locStr = ' District ';

	  var ctitle='';
      if(partyN != null) 
	     ctitle = partyN+' '+locStr+' Wise Election Results By Seats Won'; 
	  else
         ctitle = 'All Parties '+locStr+' Wise Election Results By Seats Won';

	  if(staticColors != null && staticColors.length > 0)
	  {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 972, height: 550, pointSize: 4,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
	  else
	  {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 1002, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
		
 }

 function getDistrictResultsInteractiveChartVotesPercent(results,partyN)
 {
	 var districtWiseGraphEl = document.getElementById("districtWiseGraph");
	 var chartColumns = results.partiesDistLevel;
	 var chartRows = results.partyResultsforDistWiseChart;
		
	 var data = new google.visualization.DataTable();
	 var partiesArray = new Array();
	
	 data.addColumn('string', 'Party');
	 var partysCount = 0;
      //for columns
	  for(var i in chartColumns){
		  if(partysCount > 15)
		  {
			  break;
		  }
		   if(partyN != null)
		  {
            data.addColumn('number', partyN);
			partiesArray.push(partyN);
			break;
		  }
			data.addColumn('number', chartColumns[i].name);
            partiesArray.push(chartColumns[i].name);
			partysCount++;
	  }

     
	  for(var j in chartRows)
	  {
		  
		var partyCount = 0;
        var array = new Array();
		array.push(chartRows[j].partyName);

		for(var k in chartRows[j].partyResultsInDistricts)
		{
		  if(partyN == null && partyCount > 15)
		  {
			  break;
		  }

		  if(partyN != null)
		  {
			  if(chartRows[j].partyResultsInDistricts[k].districtName == partyN)
			  {
				   var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
				   array.push(seatsWon);
				   partyCount++;
                   break;
			  }
            
		  }
		  else
		  {
			  var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
			  array.push(seatsWon);

		      partyCount++;
		  }
		}

        data.addRow(array);
       
	  }
	  var locStr = '';
	  if(electionType == 'Parliament')
		locStr = ' State ';
	  else if(electionType == 'Assembly')
		locStr = ' District ';

      var ctitle='';
      if(partyN != null) 
	     ctitle = partyN+' '+locStr+' Wise Election Results By Votes Percentage'; 
	  else
         ctitle = 'All Parties '+locStr+' Wise Election Results By Votes Percentage';
	  
	   var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	   if(staticColors != null && staticColors.length > 0)
	   {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 972, height: 550, pointSize: 4,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	   }
	   else
	  {
         new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 1002, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
		
 }


function getInteractiveChartBySeatsWonForADistrict(results,districtN)
 {
	 
	 var districtWiseGraphEl = document.getElementById("districtWiseSeatsGraph");
	 var chartRows = results.electionResultsInDistricts.allPartiesResults;
		
	 var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'District');
	 data.addColumn('number', districtN);
	  
      var partyCount = 0;
      for(var j in chartRows)
	  {
		  
		if(partyCount > 12)
		{
			break;
		}
        var array = new Array();
		array.push(chartRows[j].partyName);
		var distFlag = false;

		for(var k in chartRows[j].partyResultsInDistricts)
		{
			
		     if(chartRows[j].partyResultsInDistricts[k].districtName == districtN)
			 {
				 var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
				 array.push(seatsWon);
				 partyCount++;
				 distFlag = true;
				 break;
			 }
		  
          
		}

		if(distFlag == false)
		{
          array.push(0);
		  partyCount++;
		}


        data.addRow(array);
		
	  }
		 
	  var ctitle='';
      if(districtN != null) 
	     ctitle = districtN+' District Election Results By Seats Won'; 
	  else
         ctitle = 'All Parties District Wise Election Results By Seats Won';
	  new google.visualization.LineChart(districtWiseGraphEl).
	  draw(data, {width: 880, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
      });
		
 }

 function getInteractiveChartByVotesPercentForADistrict(results,districtN)
 {
	 
	 var districtWiseGraphEl = document.getElementById("districtWiseGraph");
	 var chartRows = results.electionResultsInDistricts.allPartiesResults;
		
	 var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'District');
	 data.addColumn('number', districtN);
	  
      var partyCount = 0;
      for(var j in chartRows)
	  {
		  
		if(partyCount > 12)
		{
			break;
		}
        var array = new Array();
		array.push(chartRows[j].partyName);
		var distFlag = false;

		for(var k in chartRows[j].partyResultsInDistricts)
		{
			
		     if(chartRows[j].partyResultsInDistricts[k].districtName == districtN)
			 {
				 var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
				 array.push(seatsWon);
				 partyCount++;
				 distFlag = true;
				 break;
			 }
		  
          
		}

		if(distFlag == false)
		{
          array.push(0);
		  partyCount++;
		}


        data.addRow(array);
		
	  }
		 
	  var ctitle='';
      if(districtN != null) 
	     ctitle = districtN+' District Election Results By Votes Percent'; 
	  else
         ctitle = 'All Parties District Wise Election Results By Votes Percent';
	  new google.visualization.LineChart(districtWiseGraphEl).
	  draw(data, {width: 880, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
      });
		
 }


function showDetailedResultsChart(chartName){
   
   var contentStr ='<div id="detailedResultsChart_main">';
	contentStr +='<div id="detailedResultsChart_graph"><IMG src="charts/'+chartName+'"></IMG></div>';
	contentStr +='</div>';

	 var myPanel = new YAHOO.widget.Dialog("detailedResultsPanel", {
                 
                 width : "950px", 
                 visible : true,  
                 constraintoviewport : true, 
        		 iframe :true,
        		 modal :true,
        		 hideaftersubmit:true,
        		 close:true
       });
	   myPanel.setHeader("Detailed Election Results Chart");
       myPanel.setBody(contentStr);
       myPanel.render();
	    myPanel.bringToTop();
	   myPanel.show();
}


function getElctionsBasicInfo(electionType){
	var jsObj= 
	{			
		electionType: electionType,			
		task: "elctionsBasicInfo"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getResultsForAnElection(stateID,electionType,year)
{ 
     $("#regionWiseDataTableDiv").hide();
	var jsObj= 
	{
		electionId : electionId,
		stateID: stateID,
		electionType: electionType,
		year: year,		
		task:"getResultForAnElection"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}

function showStatewiseResultsBarChart(results)
{
	/*var graphNamesArray = new Array();
	var chartName = results.statewiseElectionResultsChartName;
	var chartName1 = results.statewiseResultsLineChartName;
	graphNamesArray.push(chartName);
	graphNamesArray.push(chartName1);
	
	buildGraphsCarousel("graphImage",graphNamesArray);	 */
	var ctitle = 'Party Results In ' +results.electionBasicResultsVO.electionType+ ' ' +results.electionBasicResultsVO.electionYear+''; 
	var electionType = results.electionBasicResultsVO.electionType;
	var allPartyRes = results.electionBasicResultsVO.allPartiesResults;
	var allianceParties = results.electionBasicResultsVO.alliancePartiesList;

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Year');
	data.addColumn('number', 'Seats Won');
	data.addColumn('number', '2nd Pos');
	data.addColumn('number', '3rd Pos');
	data.addColumn('number', '4th Pos');

	data.addRows(10);

    var count = 0;
   	for(var i=0;i<allPartyRes.length;i++)
	{
        //check for main parties, if more parties are available then only top 10 main parties are considered
		if(electionType == 'Parliament' && allianceParties.length ==0 && allPartyRes[i].partyName != 'TDP' && allPartyRes[i].partyName != 'TRS' && allPartyRes[i].partyName != 'CPI' && allPartyRes[i].partyName != 'CPM' && allPartyRes[i].partyName != 'BJP' && allPartyRes[i].partyName != 'INC' && allPartyRes[i].partyName != 'SP' && allPartyRes[i].partyName != 'BSP' && allPartyRes[i].partyName != 'DMK' && allPartyRes[i].partyName != 'JD(U)')
		{
				continue;
		}
		else if(count < 10)
		{
			data.setValue(count, 0, allPartyRes[i].partyName);				
			data.setValue(count, 1, allPartyRes[i].totalSeatsWon);
			data.setValue(count, 2, allPartyRes[i].secondPosWon);
			data.setValue(count, 3, allPartyRes[i].thirdPosWon);
			data.setValue(count, 4, allPartyRes[i].fourthPosWon);
			count++;
			
		}
	}

	var chart = new google.visualization.ColumnChart(document.getElementById('graphImage'));
		
	chart.draw(data, {width:1002,height: 350,title:ctitle,legend:'right',legendTextStyle:{fontSize:10},
				  hAxis: {title: 'Party',textStyle:{fontSize:'10',fontWeight:'bold'},slantedText:true, slantedTextAngle:30, titleTextStyle: {color: 'red'}}
	});


}

function showElectionBasicInfo(results)
{
	var task1El = document.getElementById("task1");
	var task1Content = '';
	task1Content = '<DIV class="basicDetailsDiv">';
	//task1Content+='<DIV class="basicDetailsHeadingDiv">Overview</DIV>';
	task1Content+='<TABLE class="basicDetailsTable" width="100%">';
	task1Content+='<tr><TH>Total Voters:</TH><TD>'+results.totalVoters+'</TD><TH>Male Voters:</TH><TD>'+results.maleVoters+'</TD><TH>Female Voters:</TH><TD>'+results.femaleVoters+'</TD></TR>';
	task1Content+='<tr><TH>Total Constituencies:</TH><TD>'+results.totalConstituencies+'</TD><TH>Total Polled Votes:</TH><TD>1,05,34343</TD></TR>';
	task1Content+='</TABLE>';
	task1Content+='</DIV>';
	task1El.innerHTML = task1Content;	
}

function showPartywiseDetailsDataTable(results)
{   
	var partywiseResultsArr = results.electionBasicResultsVO.allPartiesResults;
	var partywiseResultsWithoutAlliance = results.electionBasicResultsVO.allPartiesResultsWithoutGroupingOfAllianc;

	var assignToPartywiseResultsArr = new Array();	
	for(var i in  partywiseResultsArr){
		var partywiseResultsObj = { 
				party: partywiseResultsArr[i].partyName,
				partyId :partywiseResultsArr[i].partyId,
				totalParticipated: partywiseResultsArr[i].totalConstiParticipated, 
				seatsWon: partywiseResultsArr[i].totalSeatsWon,
				second: partywiseResultsArr[i].secondPosWon,
				third: partywiseResultsArr[i].thirdPosWon,
				fourth: partywiseResultsArr[i].fourthPosWon,
				nth: partywiseResultsArr[i].nthPosWon,
				pc: partywiseResultsArr[i].votesPercentage,
				overall: partywiseResultsArr[i].completeVotesPercent //electionType,electionId,year
				//comments: '<A href="javascript:{}" onclick="showCommentsDialog('+partywiseResultsArr[i].partyId+',\'party\',\'null\',\'null\')"><IMG src="images/icons/electionResultsReport/notes.png" border="none"></IMG></A>'
				};
		if(electionResultsObj.allianceGroupNamesArray.length > 0 )
		{	
			for(k in electionResultsObj.allianceGroupNamesArray)
				{
					if(electionResultsObj.allianceGroupNamesArray[k] == partywiseResultsArr[i].partyName)
					{
						partywiseResultsObj.pc = '';					
					}
				}
			
			if(partywiseResultsArr[i].partyName == "IND")
			{
				partywiseResultsObj.totalParticipated = '';				
			}	
		
		}
			assignToPartywiseResultsArr.push(partywiseResultsObj);		
	} 
	electionResultsObj.partyWiseResultsArr = assignToPartywiseResultsArr;
	buildPartywiseResultsDataTable("partywiseResultsDataTable",electionResultsObj.partyWiseResultsArr);

	var assignToPartywiseResultsArrWithoutAlliance = new Array();
	
	for(var j in  partywiseResultsWithoutAlliance){
		var partywiseResultsObj = { 
				party: partywiseResultsWithoutAlliance[j].partyName,
				partyId : partywiseResultsWithoutAlliance[j].partyId,
				totalParticipated: partywiseResultsWithoutAlliance[j].totalConstiParticipated, 
				seatsWon: partywiseResultsWithoutAlliance[j].totalSeatsWon,
				second: partywiseResultsWithoutAlliance[j].secondPosWon,
				third: partywiseResultsWithoutAlliance[j].thirdPosWon,
				fourth: partywiseResultsWithoutAlliance[j].fourthPosWon,
				nth: partywiseResultsWithoutAlliance[j].nthPosWon,
				pc: partywiseResultsWithoutAlliance[j].votesPercentage,
				overall: partywiseResultsWithoutAlliance[j].completeVotesPercent						 
				};
		assignToPartywiseResultsArrWithoutAlliance.push(partywiseResultsObj);	
	} 
		electionResultsObj.partyWiseResultsWithoutAllianceArr = assignToPartywiseResultsArrWithoutAlliance;

	var chartpartywiseImgChartElmt = document.getElementById("partywiseResults_img_anc");
	var imgStr = '';
	/*imgStr+='<div style="margin-top:10px;margin-bottom:10px;">';
	imgStr+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\''+results.statewiseResultsLineChartName+'\',\'Party Results Line Chart\')">View Party Results Line Charts</a>';*/
	if(electionResultsObj.allianceGroupNamesArray.length > 0 )
	{
		imgStr+='<a href="javascript:{}" class="viewChartsForResults" onclick="showPartyResultsWithoutAlliance(\''+results.stateLevelLineChartWithoutAllianc+'\')">View Party Results Without Alliance </a></div>';
	}
	chartpartywiseImgChartElmt.innerHTML = imgStr;
	var noteDivEl = document.getElementsByName("note");
	if(electionResultsObj.allianceGroupNamesArray.length>0)
	{
		for (k=0; k< noteDivEl.length; k++)
		{
			if(noteDivEl[k].style.display == "none")			
			{
				noteDivEl[k].style.display = '';}
		}
	}	
	var stateResultsElmt = document.getElementById("stateResults");
	
	if(results.electionBasicVotersData != null && results.electionBasicVotersData.length > 0 )
	{
		var flag = false;
		
		if(results.electionBasicVotersData[1] != null)
			flag = true;

		var str ='';
		
		if('${electionType}' == 'Assembly')
		str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);"><td>${stateName}&nbsp;${electionType} Election Details Overview : </td></tr></table>';

		else if('${electionType}' == 'Parliament')
		str +='	<table cellspacing="0px" cellpadding="0px" width=62%><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);"><td>${electionType} Election Details Overview : </td></tr></table>';
		
		str +='<table class="searchresultsTable" style="width:600px"> ';

		str +='	<tr>';
		str +='		<th style="background-color : #C4DEFF">Year</th>';
		str +='		<th style="background-color : #C4DEFF">Total Seats</th>';
		str +='		<th style="background-color : #C4DEFF">General</th>';
		str +='		<th style="background-color : #C4DEFF">SC</th>';
		str +='		<th style="background-color : #C4DEFF">ST</th>';
		str +='		<th style="background-color : #C4DEFF">Total Votes</th> ';
		str +='		<th style="background-color : #C4DEFF">Total Polled Votes</th> ';
		str +='		<th style="background-color : #C4DEFF">Voting Percentage </th> ';
		str +='	</tr>';
		
		str +='	<tr>';
		str +='		<th style="background-color:#C4DEFF">'+results.electionBasicVotersData[0].partyName+'</th>';
		str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalSeatsParticipated+'</td>';
		str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].generalCount+'</td>';
		str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].scCoutn+'</td>';
		str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].stCount+'</td>';
		str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalVotesForState+'</td>';
		str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalPolledVotesForState+'</td>';
		str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalVotingPercentageForState+'%</td>';
		str +='	</tr>';

		if(flag)
		{
			str +='	<tr>';
			str +='		<th style="background-color:#C4DEFF">'+results.electionBasicVotersData[1].partyName+'</th>';
			str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[1].totalSeatsParticipated+'</td>';
		    str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].generalCount+'</td>';
		    str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].scCoutn+'</td>';
		    str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].stCount+'</td>';			
			str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[1].totalVotesForState+'</td>';
			str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[1].totalPolledVotesForState+'</td>';
			str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[1].totalVotingPercentageForState+'%</td>';
			str +='	</tr>';
		}

		str +='</table><BR>';
		stateResultsElmt.innerHTML = str;

	}
}

function showPartyResultsWithoutAlliance(chartId)
{	
	//partywiseResultsWithoutAlliance
	var results = electionResultsObj.partyWiseResultsWithoutAllianceArr;

	var contentStr ='<div id="withoutAllianceDiv_main">';
	contentStr +='<div id="withoutAllianceDiv_graph"></div>';
	contentStr +='<div id="withoutAllianceDiv_Datatable"></div>';
	contentStr +='</div>';

	 var myPanel = new YAHOO.widget.Dialog("panel", {
                 
                 width : "820px", 
                 visible : true,  
                 constraintoviewport : true, 
        		 iframe :true,
        		 modal :true,
				 hideaftersubmit:true,
        		 close:true,
				
								  
       });
	   myPanel.setHeader("Party Results Without Alliance");
       myPanel.setBody(contentStr);
       myPanel.render();
	   myPanel.bringToTop();
	   myPanel.show();
		
	
	//interactive chart
	var data = new google.visualization.DataTable();
	var partiesArray = new Array();
   
	//for chart columns
	data.addColumn('string', 'Party');
	for(var i=0;i<results.length;i++)
	{
      data.addColumn('number',results[i].party);
	  partiesArray.push(results[i].party);
	}

	//for chart rows (seats won)
	var array = new Array();
	array.push('Seats Won');
    for(var j=0;j<results.length;j++)
	{
      array.push(results[j].seatsWon);
	}
	 data.addRow(array);

	//for chart rows (2nd pos)
	var array1 = new Array();
	array1.push('2nd Pos');
    for(var k=0;k<results.length;k++)
	{
      array1.push(results[k].second);
	}
	data.addRow(array1);

	//for chart rows (3rd pos)
	var array2 = new Array();
	array2.push('3rd Pos');
    for(var l=0;l<results.length;l++)
	{
      array2.push(results[l].third);
	}
	data.addRow(array2);

	//for chart rows (4th pos)
	var array3 = new Array();
	array3.push('4th Pos');
    for(var m=0;m<results.length;m++)
	{
      array3.push(results[m].fourth);
	}
	data.addRow(array3);

	//for chart rows (Nth pos)
	/*var array4 = new Array();
	array4.push('Nth Pos');
    for(var n=0;n<results.length;n++)
	{
      array4.push(results[n].nth);
	}
	data.addRow(array4);*/
    
	var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	if(staticColors != null && staticColors.length > 0)
	{
	   var chart = new google.visualization.LineChart(document.getElementById("withoutAllianceDiv_graph"));      
					chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",colors:staticColors,titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
	else
	{
		var chart = new google.visualization.LineChart(document.getElementById("withoutAllianceDiv_graph"));      
					chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
	
	buildPartywiseResultsDataTable("withoutAllianceDiv_Datatable",electionResultsObj.partyWiseResultsWithoutAllianceArr);

}	

function buildPartywiseResultsDataTable(divId,dtSourceArray)
{	
    <c:if test="${hasDeatiledAnalysis == false}">
	  if(divId == "partywiseResultsDataTable" && dtSourceArray != null && dtSourceArray.length > 0 && document.getElementById("overallDataTable") != null){
	     
	       var data = new google.visualization.DataTable();
           data.addColumn('string', 'type');
           data.addColumn('number', 'value');
		   data.addRows(dtSourceArray.length);
		 for(var i = 0 ; i< dtSourceArray.length ; i++){
		   var name = dtSourceArray[i].party;
		   var val = parseInt(dtSourceArray[i].seatsWon);
		  data.setValue(i,0,name);
		  data.setValue(i,1,val);
		}
        
        // Set chart options
		var title =  "Partywise seats sharing chart "; 
        var options = {'title':title,
                       'width':410,
                       'height':250};

		 
        var chart = new google.visualization.PieChart(document.getElementById("overallDataTable"));
        chart.draw(data, options);
	
	  }
	</c:if>
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("party");
		var partyIds = oRecord.getData("partyId");
		if(Party != 'IND' && partyIds != null){
			elLiner.innerHTML =
		"<a href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}">'+Party+'</a>';
	};

	var partywiseResultsColumnDefs = [
								{key: "party", label: "Party", sortable:true,
								formatter:YAHOO.widget.DataTable.partyLink},		
								{key: "totalParticipated", label: "TP*", sortable:true},	
		              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 				              	 	 		              	 	 	
		              	 	    ];                	 	    

		var partywiseResultsDataSource = new YAHOO.util.DataSource(dtSourceArray); 
		partywiseResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		partywiseResultsDataSource.responseSchema = {
                fields: ["party", {key: "totalParticipated", parser:"number"},
                         		  {key: "seatsWon", parser:"number"},
                         		  {key: "second", parser:"number"},
                         		  {key:  "third", parser:"number"},
                         		  {key:  "fourth", parser:"number"},
                         		  {key: "nth", parser:"number"},
                         		  {key: "pc", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber},
								  {key: "partyId", parser:"number"}] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15			        
			    }),
			    caption:"Partywise Results" 
				};
		
		partywiseResultsDataTable = new YAHOO.widget.DataTable(divId, partywiseResultsColumnDefs, partywiseResultsDataSource,myConfigs);
					
        return { 
            oDS: partywiseResultsDataSource, 
            oDT: partywiseResultsDataTable 
            
      };	     	
	
}

function buildGenderCountResultsDataTable(divId,dtSourceArray)
{	 
  if(dtSourceArray != null && dtSourceArray.length >0)
   {
    maleFemale =true;
	document.getElementById("partyperformance").style.display = "block";
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var Party = oRecord.getData("partyName");
		var partyIds = oRecord.getData("partyId");
		if(oData !='IND' && partyIds!=null){
		elLiner.innerHTML =
		"<a title='Click here to view "+Party+" party news, photos, videos and results' href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}">'+Party+"</a>";
			
	};
	YAHOO.widget.DataTable.totalParticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var totalParticipated = oRecord.getData("totalParticipated");
		if(totalParticipated == null || totalParticipated == "null")
		 totalParticipated = 0;
			elLiner.innerHTML ='<div title="'+Party+' party participated constituencies">'+totalParticipated+"</div>";
			
	};
	YAHOO.widget.DataTable.totalSeatsWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var totalSeatsWon = oRecord.getData("totalSeatsWon");
		if(totalSeatsWon == null || totalSeatsWon == "null")
		 totalSeatsWon = 0;
			elLiner.innerHTML ='<div title="'+Party+' party won constituencies">'+totalSeatsWon+"</div>";
			
	};
	YAHOO.widget.DataTable.completeVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var completeVotesPercent = oRecord.getData("completeVotesPercent");
		if(completeVotesPercent == null || completeVotesPercent == "null")
		 completeVotesPercent = 0.00;
			elLiner.innerHTML ='<div title="Complete votes percentage in all constituencies">'+completeVotesPercent+"</div>";
			
	};
	YAHOO.widget.DataTable.PVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var PVotesPercent = oRecord.getData("PVotesPercent");
		var totalParticipated = oRecord.getData("totalParticipated");
		if(PVotesPercent == null || PVotesPercent == "null")
		 PVotesPercent = 0.00;
			elLiner.innerHTML ='<div title="'+Party+' party votes percentage in participated constituencies('+totalParticipated+') ">'+PVotesPercent+"</div>";
			
	};
	YAHOO.widget.DataTable.malePerticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var malePerticipated = oRecord.getData("malePerticipated");
		if(malePerticipated == null || malePerticipated == "null")
		 malePerticipated = 0;
			elLiner.innerHTML ='<div title="'+Party+' party male candidates participated constituencies">'+malePerticipated+"</div>";
			
	};
	YAHOO.widget.DataTable.maleWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var maleWon = oRecord.getData("maleWon");
		if(maleWon == null || maleWon == "null")
		 maleWon = 0;
			elLiner.innerHTML ='<div title="'+Party+' party male candidates won seats">'+maleWon+"</div>";
			
	};
	YAHOO.widget.DataTable.MVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var MVotesPercent = oRecord.getData("MVotesPercent");
		if(MVotesPercent == null || MVotesPercent == "null")
		 MVotesPercent = 0.00;
			elLiner.innerHTML ='<div title="'+Party+' party male candidates gained votes percentage in participated constituencies">'+MVotesPercent+"</div>";
			
	};
	YAHOO.widget.DataTable.femalePerticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var femalePerticipated = oRecord.getData("femalePerticipated");
		if(femalePerticipated == null || femalePerticipated == "null")
		 femalePerticipated = 0;
			elLiner.innerHTML ='<div title="'+Party+' party female candidates participated constituencies">'+femalePerticipated+"</div>";
			
	};
	YAHOO.widget.DataTable.femaleWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var femaleWon = oRecord.getData("femaleWon");
		if(femaleWon == null || femaleWon == "null")
		 femaleWon = 0;
			elLiner.innerHTML ='<div title="'+Party+' party female candidates won seats">'+femaleWon+"</div>";
			
	};
	YAHOO.widget.DataTable.FVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		var FVotesPercent = oRecord.getData("FVotesPercent");
		if(FVotesPercent == null || FVotesPercent == "null")
		 FVotesPercent = 0.00;
			elLiner.innerHTML ='<div title="'+Party+' party female candidates gained votes percentage in participated constituencies">'+FVotesPercent+"</div>";
			
	};
	
	var partywiseResultsWithGenderColumnDefs = [
								{key: "partyName", label: "<span style='color:#383F38;'>Party</span>", sortable:true,
								formatter:YAHOO.widget.DataTable.partyLink},		
								{key: "totalParticipated", label: "<span style='color:#3E9E97;'>TP*</span>", sortable:true,formatter:YAHOO.widget.DataTable.totalParticipated},	
		              	 	    {key: "totalSeatsWon", label: "<span style='color:#3E9E97;'><%=seatsWon%></span>",formatter:YAHOO.widget.DataTable.totalSeatsWon, sortable:true},
		              	 	 	{key: "completeVotesPercent", label: "<span style='color:#3E9E97;'>CVP* %</span>",formatter:YAHOO.widget.DataTable.completeVotesPercent, sortable:true},
		              	 	 	{key: "PVotesPercent", label: "<span style='color:#3E9E97;'>CPVP* %</span>",formatter:YAHOO.widget.DataTable.PVotesPercent, sortable:true},
		              	 	 	{key: "malePerticipated", label: "<span style='color:#8767EE;'>Male Participants</span>",formatter:YAHOO.widget.DataTable.malePerticipated, sortable:true},
		              	 	 	{key: "maleWon", label: "<span style='color:#8767EE;'>Male Won</span>",formatter:YAHOO.widget.DataTable.maleWon, sortable:true}, 
								{key: "MVotesPercent", label: "<span style='color:#8767EE;'>MCGVP* %</span>",formatter:YAHOO.widget.DataTable.MVotesPercent, sortable:true},   	
		              	 	 	{key: "femalePerticipated", label:"<span style='color:#2C8927;'>Female Participants</span>", formatter:YAHOO.widget.DataTable.femalePerticipated,sortable: true},
		              	 	 	{key: "femaleWon", label:"<span style='color:#2C8927;'>Female Won</span>",formatter:YAHOO.widget.DataTable.femaleWon, sortable: true},
								{key: "FVotesPercent", label: "<span style='color:#2C8927;'>FCGVP* %</span>",formatter:YAHOO.widget.DataTable.FVotesPercent, sortable:true}  	
		              	 	    ];                	 	    

		var partywiseResultsWithGenderDataSource = new YAHOO.util.DataSource(dtSourceArray); 
		partywiseResultsWithGenderDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		partywiseResultsWithGenderDataSource.responseSchema = {
                fields: ["partyName", {key: "totalParticipated", parser:"number"},
                         		  {key: "totalSeatsWon", parser:"number"},
                         		  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key:  "PVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key:  "malePerticipated", parser:"number"},
                         		  {key: "maleWon", parser:"number"},
								  {key: "MVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "femalePerticipated", parser:"number"},
								  {key: "femaleWon", parser:"number"},
								  {key: "FVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
								{key: "partyId", parser:"number"}
									] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15			        
			    }),
			    caption:"Partywise Male And Female Candidates Participation And Results" 
				};
		
		partywiseResultsWithGenderDataTable = new YAHOO.widget.DataTable(divId, partywiseResultsWithGenderColumnDefs, partywiseResultsWithGenderDataSource,myConfigs);
					
        return { 
            oDS: partywiseResultsWithGenderDataSource, 
            oDT: partywiseResultsWithGenderDataTable 
           
      };	     	
  }
  else
  {
     maleFemale =false;
     if(maleFemale == true || ruralUrban == true || ruralUrbanChart == true)
	  document.getElementById("partyperformance").style.display = "block";
	 else
	  document.getElementById("partyperformance").style.display = "none";
  }
}

function buildPartyPerfRulUrbanDataTable(option)
{
	var dtSourceArray = null;
	
   if(option == 'Partywise')
   {
		var selectedPartiesResultArray = new Array()
		var selObj = document.getElementById('partySelectInUrbanPer');
		var partyN = new Array();
		
		for (var i=0; i<selObj.options.length; i++)
		{
			if (selObj.options[i].selected)
					partyN.push(selObj.options[i].text);     
		}
		
		if(partyN.length == 0)
			return;

		for(var i=0;i<areaTypeWisePartiesResults.length;i++)
		{
			if(partyN.indexOf(areaTypeWisePartiesResults[i].partyName) != -1)
			{
				selectedPartiesResultArray.push(areaTypeWisePartiesResults[i]);
			}
		}
		dtSourceArray = selectedPartiesResultArray;
   }

   else if(option == 'All')
		dtSourceArray = areaTypeWisePartiesResults;


   if(dtSourceArray != null && dtSourceArray.length >0)
   {
     $("#ubanDataHideShowDiv").show();
    ruralUrban =true;
	document.getElementById("partyperformance").style.display = "block";
	
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var Party = oRecord.getData("partyName");
		var partyIds = oRecord.getData("partyId");
		var isAlliance = oRecord.getData("isAlliance");
		
		if(oData !='IND' && partyIds!=null && !isAlliance)
		elLiner.innerHTML =
		"<a  title='Click here to view "+Party+" party news, photos, videos and results' href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		
		else
			elLiner.innerHTML = "<a href='javascript:{}'>"+Party+"</a>";
			
	};

	YAHOO.widget.DataTable.totalParticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var isAlliance = oRecord.getData("isAlliance");
		var party = oRecord.getData("partyName");
		if(isAlliance)
			elLiner.innerHTML =	"";
		
		else
			elLiner.innerHTML = "<div title='"+party+" participated constituencies'>"+oData+"</div>";
			
	};
	
	YAHOO.widget.DataTable.totalSeatsWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var totalSeatsWon = oRecord.getData("totalSeatsWon");
		if(totalSeatsWon == null || totalSeatsWon == "null")
		totalSeatsWon = 0;
			elLiner.innerHTML = "<div title='"+party+" won constituencies'>"+totalSeatsWon+"</div>";
	};
	
	YAHOO.widget.DataTable.completeVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var completeVotesPercent = oRecord.getData("completeVotesPercent");
		if(completeVotesPercent == null || completeVotesPercent == "null")
		completeVotesPercent = 0;
			elLiner.innerHTML = "<div title='Complete votes percentage in all constituencies'>"+completeVotesPercent+"</div>";
	};
	YAHOO.widget.DataTable.PVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var totalParticipated = oRecord.getData("totalParticipated");
		var PVotesPercent = oRecord.getData("PVotesPercent");
		if(PVotesPercent == null || PVotesPercent == "null")
		PVotesPercent = 0;
			elLiner.innerHTML = "<div title='"+party+" votes percentage in participated constituencies("+totalParticipated+") '>"+PVotesPercent+"</div>";
	};
	YAHOO.widget.DataTable.ruralParticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var ruralParticipated = oRecord.getData("ruralParticipated");
		if(ruralParticipated == null || ruralParticipated == "null")
		ruralParticipated = 0;
			elLiner.innerHTML = "<div title='"+party+" participated rural constituencies'>"+ruralParticipated+"</div>";
	};
	YAHOO.widget.DataTable.ruralWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var ruralWon = oRecord.getData("ruralWon");
		if(ruralWon == null || ruralWon == "null")
		ruralWon = 0;
			elLiner.innerHTML = "<div title='"+party+" won rural constituencies'>"+ruralWon+"</div>";
	};
	YAHOO.widget.DataTable.ruralVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var ruralVotesPercent = oRecord.getData("ruralVotesPercent");
		if(ruralVotesPercent == null || ruralVotesPercent == "null")
		ruralVotesPercent = 0;
			elLiner.innerHTML = "<div title='"+party+" votes percentage in rural participated constituencies'>"+ruralVotesPercent+"</div>";
	};
	YAHOO.widget.DataTable.urbanParticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var urbanParticipated = oRecord.getData("urbanParticipated");
		if(urbanParticipated == null || urbanParticipated == "null")
		urbanParticipated = 0;
			elLiner.innerHTML = "<div title='"+party+" participated urban constituencies'>"+urbanParticipated+"</div>";
	};
	YAHOO.widget.DataTable.urbanWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var urbanWon = oRecord.getData("urbanWon");
		if(urbanWon == null || urbanWon == "null")
		urbanWon = 0;
			elLiner.innerHTML = "<div title='"+party+" won urban constituencies'>"+urbanWon+"</div>";
	};
	YAHOO.widget.DataTable.urbanVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var urbanVotesPercent = oRecord.getData("urbanVotesPercent");
		if(urbanVotesPercent == null || urbanVotesPercent == "null")
		urbanVotesPercent = 0;
			elLiner.innerHTML = "<div title='"+party+" votes percentage in urban participated constituencies'>"+urbanVotesPercent+"</div>";
	};
	YAHOO.widget.DataTable.ruralUrbanParticipated = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var ruralUrbanParticipated = oRecord.getData("ruralUrbanParticipated");
		if(ruralUrbanParticipated == null || ruralUrbanParticipated == "null")
		ruralUrbanParticipated = 0;
			elLiner.innerHTML = "<div title='"+party+" participated rural-urban constituencies'>"+ruralUrbanParticipated+"</div>";
	};
	YAHOO.widget.DataTable.ruralUrbanWon = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var ruralUrbanWon = oRecord.getData("ruralUrbanWon");
		if(ruralUrbanWon == null || ruralUrbanWon == "null")
		ruralUrbanWon = 0;
			elLiner.innerHTML = "<div title='"+party+" won rural-urban constituencies'>"+ruralUrbanWon+"</div>";
	};
	YAHOO.widget.DataTable.ruralUrbanVotesPercent = function(elLiner, oRecord, oColumn, oData) 
	{
		var party = oRecord.getData("partyName");
		var ruralUrbanVotesPercent = oRecord.getData("ruralUrbanVotesPercent");
		if(ruralUrbanVotesPercent == null || ruralUrbanVotesPercent == "null")
		ruralUrbanVotesPercent = 0;
			elLiner.innerHTML = "<div title='"+party+" votes percentage in rural-urban participated constituencies'>"+ruralUrbanVotesPercent+"</div>";
	};
	var partyPerfRurlUrbnColDefs = [
								{key: "partyName", label: "<span style='color:#383F38;padding:20px;'>Party</span>", sortable:true,
								formatter:YAHOO.widget.DataTable.partyLink},		
								{key: "totalParticipated", label: "<span style='color:#2BA29A;'>TP*</span>", sortable:true,formatter:YAHOO.widget.DataTable.totalParticipated},	
		              	 	    {key: "totalSeatsWon", label: "<span style='color:#2BA29A;'><%=seatsWon%></span>",formatter:YAHOO.widget.DataTable.totalSeatsWon, sortable:true},
		              	 	 	{key: "completeVotesPercent", label: "<span style='color:#2BA29A;'>CV* %</span>",formatter:YAHOO.widget.DataTable.completeVotesPercent, sortable:true},
		              	 	 	{key: "PVotesPercent", label: "<span style='color:#2BA29A;'>PCV* %</span>",formatter:YAHOO.widget.DataTable.PVotesPercent, sortable:true},
		              	 	 	{key: "ruralParticipated", label: "<span style='color:#5030B7;'>RCP*</span>",formatter:YAHOO.widget.DataTable.ruralParticipated, sortable:true},
		              	 	 	{key: "ruralWon", label: "<span style='color:#5030B7;'>Won in RC*</span>",formatter:YAHOO.widget.DataTable.ruralWon, sortable:true}, 
								{key: "ruralVotesPercent", label: "<span style='color:#5030B7;'>RCV* %</span>",formatter:YAHOO.widget.DataTable.ruralVotesPercent, sortable:true},   	
		              	 	 	{key: "urbanParticipated", label:"<span style='color:#42973E;'>UCP*</span>", formatter:YAHOO.widget.DataTable.urbanParticipated,sortable: true},
		              	 	 	{key: "urbanWon", label:"<span style='color:#42973E;'>Won in UC*</span>",formatter:YAHOO.widget.DataTable.urbanWon, sortable: true},
								{key: "urbanVotesPercent", label: "<span style='color:#42973E;'>UCV* %</span>",formatter:YAHOO.widget.DataTable.urbanVotesPercent, sortable:true},
                                {key: "ruralUrbanParticipated", label:"<span style='color:#AB4176;'>RUCP*</span>", formatter:YAHOO.widget.DataTable.ruralUrbanParticipated,sortable: true},
		              	 	 	{key: "ruralUrbanWon", label:"<span style='color:#AB4176;'>Won in RUC*</span>",formatter:YAHOO.widget.DataTable.ruralUrbanWon, sortable: true},
								{key: "ruralUrbanVotesPercent", label: "<span style='color:#AB4176;'>RUCV* %</span>",formatter:YAHOO.widget.DataTable.ruralUrbanVotesPercent, sortable:true} 								
		              	 	    ];                	 	    

		var partyPerfRurlUrbnDataSource = new YAHOO.util.DataSource(dtSourceArray); 
		partyPerfRurlUrbnDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		partyPerfRurlUrbnDataSource.responseSchema = {
                fields: ["partyName", {key: "totalParticipated", parser:"number"},
                         		  {key: "totalSeatsWon", parser:"number"},
                         		  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key:  "PVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key:  "ruralParticipated", parser:"number"},
                         		  {key: "ruralWon", parser:"number"},
								  {key: "ruralVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "urbanParticipated", parser:"number"},
								  {key: "urbanWon", parser:"number"},
								  {key: "urbanVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
								  {key: "ruralUrbanParticipated", parser:"number"},
								  {key: "ruralUrbanWon", parser:"number"},
								  {key: "ruralUrbanVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},
								  {key: "partyId", parser:"number"},
								  {key: "isAlliance"}
									] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15			        
			    }),
			    caption:"Party Performance In Rural/Urban Constituencies" 
				};
		
		partywiseResultsWithGenderDataTable = new YAHOO.widget.DataTable('partyPerfResultsDataTable', partyPerfRurlUrbnColDefs, partyPerfRurlUrbnDataSource,myConfigs);
  }  
    else
  {
     ruralUrban =false;
     if(maleFemale == true || ruralUrban == true || ruralUrbanChart == true)
	  document.getElementById("partyperformance").style.display = "block";
	 else
	  document.getElementById("partyperformance").style.display = "none";
  }  
}

function showAllianceDetails(results)
{	

	var allianceResultsArr = results.electionBasicResultsVO.alliancePartiesList;
	allianceResults = allianceResultsArr;
	var assignToAllianceResultsArr = new Array();
	var allianceResultsDataTableEl = document.getElementById("allianceResultsDataTable");
	
	var allianceGrpName;	
	allianceResultsDataTableEl.innerHTML = '';

	for(var i in  allianceResultsArr){
		var dtArray =  new Array();
		var header = allianceResultsArr[i].allianceGroupName+" Alliance Details";
		allianceGrpName =  allianceResultsArr[i].allianceGroupName;
		electionResultsObj.allianceGroupNamesArray.push(allianceGrpName);		
		var createDiv = document.createElement("div");		
		createDiv.setAttribute("id","allianceResults_"+i+"_main");		
		createDiv.style.cssText = 'margin-top:32px;';
		for(var j in allianceResultsArr[i].partiesInAlliance)
		{
			var allianceObj = {
					partyName: allianceResultsArr[i].partiesInAlliance[j].partyName,
					totalConstiParticipated: allianceResultsArr[i].partiesInAlliance[j].totalConstiParticipated,
					totalSeatsWon: allianceResultsArr[i].partiesInAlliance[j].totalSeatsWon,
					secondPosWon: allianceResultsArr[i].partiesInAlliance[j].secondPosWon,
					thirdPosWon: allianceResultsArr[i].partiesInAlliance[j].thirdPosWon,
					fourthPosWon: allianceResultsArr[i].partiesInAlliance[j].fourthPosWon,
					nthPosWon: allianceResultsArr[i].partiesInAlliance[j].nthPosWon,
					votesPercentage: allianceResultsArr[i].partiesInAlliance[j].votesPercentage,
					completeVotesPercent: allianceResultsArr[i].partiesInAlliance[j].completeVotesPercent,
					partyId:
					allianceResultsArr[i].partiesInAlliance[j].partyId
					//comments:'<A href="javascript:{}" onclick="showCommentsDialog('+allianceResultsArr[i].partiesInAlliance[j].partyId+',\'party\',\'null\',\'null\')"><IMG src="images/icons/electionResultsReport/notes.png" border="none"></IMG></A>' 
						
				};
			dtArray.push(allianceObj);
			
		}
		var str = '';
		str+='<div id="allianceResults_'+i+'_datatable"></div>';
		str+='<div id="allianceResults_'+i+'_allianceGraph"></div>';
		str+='<div id="allianceResults_'+i+'_footer" style="text-align:left;margin-top:10px;margin-bottom:10px;">';
		str+='<a href="javascript:{}" class="viewChartsForResults" onclick="showInteractiveAllianceCharts('+i+',\''+header+'\')">View '+allianceResultsArr[i].allianceGroupName+' Alliance Graph<a>';
		str+='</div>';
		createDiv.innerHTML = str;
		if(allianceResultsDataTableEl)
			allianceResultsDataTableEl.appendChild(createDiv);
	    
		buildAllianceResultsDataTable("allianceResults_"+i+"_datatable",dtArray,allianceResultsArr[i].allianceGroupName+" Alliance Results");
			
	}			
}

function showInteractiveAllianceCharts(index,allianceGroupName)
{
	var results = allianceResults[index].partiesInAlliance;
   	var data = new google.visualization.DataTable();
	   
	//for chart columns
	data.addColumn('string', 'Party');
	for(var i=0;i<results.length;i++)
	{
      data.addColumn('number',results[i].partyName);
	}

	//for chart rows (seats won)
	var array = new Array();
	array.push('Seats Won');
    for(var j=0;j<results.length;j++)
	{
      array.push(results[j].totalSeatsWon);
	}
	 data.addRow(array);

	//for chart rows (2nd pos)
	var array1 = new Array();
	array1.push('2nd Pos');
    for(var k=0;k<results.length;k++)
	{
      array1.push(results[k].secondPosWon);
	}
	data.addRow(array1);

	//for chart rows (3rd pos)
	var array2 = new Array();
	array2.push('3rd Pos');
    for(var l=0;l<results.length;l++)
	{
      array2.push(results[l].thirdPosWon);
	}
	data.addRow(array2);

	//for chart rows (4th pos)
	var array3 = new Array();
	array3.push('4th Pos');
    for(var m=0;m<results.length;m++)
	{
      array3.push(results[m].fourthPosWon);
	}
	data.addRow(array3);

	//for chart rows (Nth pos)
	var array4 = new Array();
	array4.push('Nth Pos');
    for(var n=0;n<results.length;n++)
	{
      array4.push(results[n].nthPosWon);
	}
	data.addRow(array4);

	 var myPanel = new YAHOO.widget.Dialog("panel", {
                 
		 width : "820px", 
         visible : true,  
         constraintoviewport : true, 
		  iframe :true,
		  modal :true,
		  hideaftersubmit:true,
		  close:true
       });
	
	var str = '';
	str += '<div id="panelChartDiv"></div>';

	myPanel.setHeader(allianceGroupName);	  
	myPanel.setBody(str);
	myPanel.render();
	 myPanel.bringToTop();
	   myPanel.show();

	//static colors for parties
    var staticColors = setStaticPartyColorsForInteractiveCharts(results);
	if(staticColors != null && staticColors.length > 0)
	{
      var chart = new google.visualization.LineChart(document.getElementById("panelChartDiv"));      
				chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",colors:staticColors,titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
	else
	{

    var chart = new google.visualization.LineChart(document.getElementById("panelChartDiv"));      
				chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
   
}

function showAllianceGraph(chartId, chartName)
{
	if(myPanel)
		myPanel.destroy();
	//var contentStr='';
	//contentStr+='<DIV>'+chartName+'</DIV>';
	var contentStr ='<IMG src="charts/'+chartId+'"></IMG>';
	
	var contentStr='<IMG src="charts/'+chartId+'"></IMG>';

	 var myPanel = new YAHOO.widget.Dialog("panel", {
                 
		 width : "820px", 
         visible : true,  
         constraintoviewport : true, 
		  iframe :true,
		  modal :true,
		  hideaftersubmit:true,
		  close:true
       });
	   myPanel.setHeader(chartName);	  
       myPanel.setBody(contentStr);
       myPanel.render();
	    myPanel.bringToTop();
	   myPanel.show();
}
function buildAllianceResultsDataTable(id,dtSource,dtCaption)
{	

	  YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("partyId");
	if(id!=null && oData!='IND'){
	elLiner.innerHTML ="<a href='partyPageAction.action?partyId="+id+"'>"+oData+"</a>";
	}
	else
		elLiner.innerHTML =oData;

  };

	
	var allianceResultsColumnDefs = [
								{key: "partyName", label: "<%=party%>", sortable:true,formatter:YAHOO.widget.DataTable.edit},		
								{key: "totalConstiParticipated", label: "TP*",formatter:"number", sortable:true},	
		              	 	    {key: "totalSeatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "secondPosWon", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "thirdPosWon", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourthPosWon", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nthPosWon", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "votesPercentage", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "completeVotesPercent", label:"Overall %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true}
		              	 	 		              	 	 	
		              	 	    ];                	 	    

		var allianceResultsDataSource = new YAHOO.util.DataSource(dtSource); 
		allianceResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allianceResultsDataSource.responseSchema = {
                fields: ["partyName", {key: "totalConstiParticipated", parser:"number"},
                					  {key:  "totalSeatsWon", parser:"number"},
                					  {key:  "secondPosWon", parser:"number"},
                					  {key:  "thirdPosWon", parser:"number"},
                					  {key:  "fourthPosWon", parser:"number"},
                					  {key: "nthPosWon", parser:"number"},
                					  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                					  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},"comments",
									  {key: "partyId", parser:"number"}] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    }),
			    caption:dtCaption
				};
		
		allianceResultsDataTable = new YAHOO.widget.DataTable(id, allianceResultsColumnDefs, allianceResultsDataSource,{caption:dtCaption});
					
        return { 
            oDS: allianceResultsDataSource, 
            oDT: allianceResultsDataTable            
      };	
	
}
function showCandidateDetailsWindow(stateName,electionType,year,electionId)
{

	<c:if test="${sessionScope.USER == null}">
	 document.getElementById("regionAccessDiv").style.display='block';
		var str='';
		//alert("not logged in ......");
		$("#regionAccessDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#000">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'},
								
						});
		str+='<div>';
		str+='<h4 style="color:#ff0000;display:inline;position:relative;top:0px;">'; 
		str+='<div>Please register to view this content.   Click here to <a href="freeUserRegistration.action">Register</a></div><div style="margin: 10px;"> Already A member please login to get access. <div style="margin: 10px;">Click here to <a href="loginInputAction.action">Login</a></div></div></h4>';
		str+='</div>';
	document.getElementById("regionAccessDiv").innerHTML = str;
		</c:if>
	<c:if test="${sessionScope.USER != null}">
	var urlStr = "<%=request.getContextPath()%>/candidateDetailsForElectionDetailsReportAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&year=${year}&electionId=${electionId}";
	var browser1 = window.open(urlStr,"browser1","scrollbars=yes,height=600,width=1200,left=200,top=200");
	 browser1.focus();
	 </c:if>
}


function buildAllDistrictDatatable(innerObj,divID,type,partyName,districtName)
{

	var selectPartyName = partyName;
	var districtName = districtName;	
	var dtSource = new Array();
	var locStr = '';
	
	if(electionType == 'Parliament')
	   locStr = ' States ';
	else if(electionType == 'Assembly')
	   locStr = ' Districts ';

	var tableCaption = "Partywise Results In All "+locStr;
	if(type == "all")
	{
		
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
				
				var obj = {
                     		partyId:innerObj[i].partyId,	district:innerObj[i].partyResultsInDistricts[j].districtName,
							party:innerObj[i].partyName,
							totalParticipated: innerObj[i].partyResultsInDistricts[j].totalConstiParticipated,
							seatsWon:innerObj[i].partyResultsInDistricts[j].seatsWon,
							second:innerObj[i].partyResultsInDistricts[j].secondPos,
							third:innerObj[i].partyResultsInDistricts[j].thirdPos,
							fourth:innerObj[i].partyResultsInDistricts[j].fourthPos,
							nth:innerObj[i].partyResultsInDistricts[j].nthPos,
							pc:innerObj[i].partyResultsInDistricts[j].votesPercentage,
							overall:innerObj[i].partyResultsInDistricts[j].completeVotesPercent
						  }
				if(electionResultsObj.allianceGroupNamesArray.length > 0 )
				{
						for(k in electionResultsObj.allianceGroupNamesArray)
						{
							if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
							{
								obj.pc = '';
								obj.totalParticipated='';
							}
						}
				}
				if(innerObj[i].partyName == 'IND')
				{
					obj.totalParticipated='';
				}
				dtSource.push(obj);
			}
		}
	}
	if(type == "party")
	{
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
				if(selectPartyName == innerObj[i].partyName)
				{
					var obj = {
							    partyId:innerObj[i].partyId,
							    district:innerObj[i].partyResultsInDistricts[j].districtName,
								party:innerObj[i].partyName,
								totalParticipated: innerObj[i].partyResultsInDistricts[j].totalConstiParticipated,
								seatsWon:innerObj[i].partyResultsInDistricts[j].seatsWon,
								second:innerObj[i].partyResultsInDistricts[j].secondPos,
								third:innerObj[i].partyResultsInDistricts[j].thirdPos,
								fourth:innerObj[i].partyResultsInDistricts[j].fourthPos,
								nth:innerObj[i].partyResultsInDistricts[j].nthPos,
								pc:innerObj[i].partyResultsInDistricts[j].votesPercentage,
								overall:innerObj[i].partyResultsInDistricts[j].completeVotesPercent
							  }
					if(electionResultsObj.allianceGroupNamesArray.length > 0 )
					{
							for(k in electionResultsObj.allianceGroupNamesArray)
							{
								if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
								{
									obj.pc = '';
									obj.totalParticipated='';
								}
							}
					}
					if(innerObj[i].partyName == 'IND')
					{
						obj.totalParticipated='';
					}
					dtSource.push(obj);
				}	
			}
		}
	}
     if(type == "partyArray")
	{
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
			  for(var k in selectPartyName)
				if(selectPartyName[k] == innerObj[i].partyName)
				{
					var obj = {
							    partyId:innerObj[i].partyId,
							    district:innerObj[i].partyResultsInDistricts[j].districtName,
								party:innerObj[i].partyName,
								totalParticipated: innerObj[i].partyResultsInDistricts[j].totalConstiParticipated,
								seatsWon:innerObj[i].partyResultsInDistricts[j].seatsWon,
								second:innerObj[i].partyResultsInDistricts[j].secondPos,
								third:innerObj[i].partyResultsInDistricts[j].thirdPos,
								fourth:innerObj[i].partyResultsInDistricts[j].fourthPos,
								nth:innerObj[i].partyResultsInDistricts[j].nthPos,
								pc:innerObj[i].partyResultsInDistricts[j].votesPercentage,
								overall:innerObj[i].partyResultsInDistricts[j].completeVotesPercent
							  }
					if(electionResultsObj.allianceGroupNamesArray.length > 0 )
					{
							for(k in electionResultsObj.allianceGroupNamesArray)
							{
								if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
								{
									obj.pc = '';
									obj.totalParticipated='';
								}
							}
					}
					if(innerObj[i].partyName == 'IND')
					{
						obj.totalParticipated='';
					}
					dtSource.push(obj);
				}	
			}
		}
	}
	if(type == "district")
	{
		tableCaption = "Partywise Results In "+districtName;
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
				if(districtName == innerObj[i].partyResultsInDistricts[j].districtName)
				{
					var obj = {
							    partyId:innerObj[i].partyId,
							    district:innerObj[i].partyResultsInDistricts[j].districtName,
								party:innerObj[i].partyName,
								totalParticipated: innerObj[i].partyResultsInDistricts[j].totalConstiParticipated,
								seatsWon:innerObj[i].partyResultsInDistricts[j].seatsWon,
								second:innerObj[i].partyResultsInDistricts[j].secondPos,
								third:innerObj[i].partyResultsInDistricts[j].thirdPos,
								fourth:innerObj[i].partyResultsInDistricts[j].fourthPos,
								nth:innerObj[i].partyResultsInDistricts[j].nthPos,
								pc:innerObj[i].partyResultsInDistricts[j].votesPercentage,
								overall:innerObj[i].partyResultsInDistricts[j].completeVotesPercent
							  }
					if(electionResultsObj.allianceGroupNamesArray.length > 0 )
					{
							for(k in electionResultsObj.allianceGroupNamesArray)
							{
								if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
								{
									obj.pc = '';
									obj.totalParticipated='';
								}
							}
					}if(innerObj[i].partyName == 'IND')
					{
						obj.totalParticipated='';
					}
					dtSource.push(obj);
				}	
			}
		}
	}	

	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var Party = oRecord.getData("party");
		var partyIds = oRecord.getData("partyId");
		if(oData != 'IND' && partyIds != null){
		 elLiner.innerHTML =
		  "<a href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}">'+Party+'</a>';
	};

	var allDistrictResultsColumnDefs = [
								{key: "district", label: "Location", sortable:true},		
								{key: "party", label: "<%=party%>", sortable:true,
								formatter:YAHOO.widget.DataTable.partyLink },
								{key: "totalParticipated", label:"TP*", sortable:true},										
		              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
		              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 	
		              	 	 			              	 	 	
		              	 	    ];                	 	    

		var allDistrictResultsDataSource = new YAHOO.util.DataSource(dtSource); 
		allDistrictResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allDistrictResultsDataSource.responseSchema = {
                fields: ["district","party", {key: "totalParticipated", parser:"number"},
                         		  {key: "seatsWon", parser:"number"},                         		  
                         		  {key: "second", parser:"number"},
                         		  {key:  "third", parser:"number"},
                         		  {key:  "fourth", parser:"number"},
                         		  {key: "nth", parser:"number"},
                         		  {key: "pc",parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		   {key: "partyId", parser:"number"}] 
                					   
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 23			        
			    }),  
			    caption: tableCaption
				};
		
		var allDistrictResultsDataTable = new YAHOO.widget.DataTable(divID, allDistrictResultsColumnDefs, allDistrictResultsDataSource,myConfigs);
					
        return { 
            oDS: allDistrictResultsDataSource, 
            oDT: allDistrictResultsDataTable            
      };	
}

function buildAllDistrictResultsDataTable(results)
{	
		resultsGlobal = results;
	//districtResults_withoutAllianceDiv
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var districtsList = results.partiDistList;
	var stateList = results.partiDistList;	
	var participatedPartiesList = results.partiPartiesList; 
	var stateSelectBox = document.getElementById("stateSelectBox");
	electionResultsObj.districtWiseResultsWithoutAllianceArr = results.electionResultsInDistricts.allPartiesResultsWithoutGroupingOfAllianc;
	var distSelectElmt = document.getElementById("distSelectBox");
	var partySelectElmt = document.getElementById("partySelectBox");
	var partyNamesElmt = document.getElementById("partiesNames");
	
	for(var i in participatedPartiesList)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=participatedPartiesList[i].id;
		opElmt.text=participatedPartiesList[i].name;
	
		try
			{
				partySelectElmt.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
				partySelectElmt.add(opElmt); // IE only
			}			
	}
  if(partyNamesElmt != null)
  {
	for(var l in participatedPartiesList)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=participatedPartiesList[l].id;
		opElmt.text=participatedPartiesList[l].name;
	
		try
			{
				partyNamesElmt.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
				partyNamesElmt.add(opElmt); // IE only
			}			
	}
  }
	for(var j in districtsList)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=districtsList[j].id;
		opElmt.text=districtsList[j].name;
	
		try
			{
				if(distSelectElmt)
					distSelectElmt.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
				if(distSelectElmt)
					distSelectElmt.add(opElmt); // IE only
			}			
	}
	for(var k in stateList)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=stateList[k].id;
		opElmt.text=stateList[k].name;
	
		try
			{
				if(stateSelectBox)
					stateSelectBox.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
				if(stateSelectBox)
					stateSelectBox.add(opElmt); // IE only
			}			
	}	
	buildAllDistrictDatatable(innerObj,"districtResults","all","null","null");
<c:if test="${hasDeatiledAnalysis == false}">
return;
</c:if>
	var elmt = document.getElementById("districtResults_withoutAllianceDiv");
	var str = '';
	if(electionResultsObj.allianceGroupNamesArray.length > 0 )
	{
		str += '<div style="margin-top:10px;margin-bottom:10px;">';
		str += '<a href="javascript:{}" class="viewChartsForResults" onclick="showDistrictWisePartyResultsWithoutAlliance(\''+results.partyResultsDistrictLevelChartWithoutAllianc+'\',\''+results.partyResultsDistrictLevelDetailedResultsChartWithoutAllianc+'\')">';
		str += 'View Party Results Without Alliance';
		str += '</a></div>';
	}	
	str += '<div id="districtWiseWithoutAlliancePopupDiv"></div>';
	elmt.innerHTML = str;
}

function showDistrictWisePartyResultsWithoutAlliance(chartId,detailedResultsChart)
{
	//partywiseResultsWithoutAlliance

	var contentStr ='<div id="districtWiseWithoutAllianceDiv_main" style="height:500px;overflow-y:auto">';
	//contentStr +='<div id="districtWiseWithoutAllianceDiv_graph"><IMG src="charts/'+chartId+'"></IMG></div>';
	contentStr +='<div id="districtWiseWithoutAllianceDiv_graph"><IMG src="charts/'+detailedResultsChart+'"></IMG></div>';
	contentStr +='<div id="districtWiseWithoutAllianceDiv_Datatable"></div>';
	contentStr +='</div>';

	 var myPanel = new YAHOO.widget.Dialog("panel", {
                 
                 width : "950px", 
                 visible : true,  
                 constraintoviewport : true, 
        		  iframe :true,
        		  modal :true,
        		  hideaftersubmit:true,
        		  close:true
       });
	   myPanel.setHeader("District Wise Party Results Without Alliance");
       myPanel.setBody(contentStr);		
	   myPanel.render();
	    myPanel.bringToTop();
	   myPanel.show();
	   buildAllDistrictDatatable(electionResultsObj.districtWiseResultsWithoutAllianceArr,"districtWiseWithoutAllianceDiv_Datatable","all","null","null");
}	

function buildAllianceDistrictResultsDataTable(results,type, districtName)
{	
	allianceResultsGlobal = results;
	<c:if test="${hasDeatiledAnalysis == false}">
	  return;
	</c:if>
	var parentElmt = document.getElementById("allianceDistResults");
	var distName = districtName;
	var innerObj = results.alliancePartiesList;
	var caption;
	var locStr = '';
	
	if(electionType == 'Parliament')
	   locStr = ' States ';
	else if(electionType == 'Assembly')
	   locStr = ' Districts ';

	parentElmt.innerHTML ='';
		for(var i in innerObj)
			{	
				var header = innerObj[i].allianceGroupName+" Alliance Graph";
				var childElmt = document.createElement("div");
				childElmt.setAttribute('id',innerObj[i].allianceGroupName);	
				childElmt.innerHTML = '';			
				var str = '';
				str+='<div id="allianceResults_district_'+i+'_datatable"></div>';
				str+='<div id="allianceResults_district_'+i+'_footer" style="margin-top:10px;margin-bottom:10px;">';
				/*str+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\''+innerObj[i].alliancePartiesChart+'\',\''+header+'\')">View '+header+'<a>';*/
				str+='</div>';
				childElmt.innerHTML = str;
		
				parentElmt.appendChild(childElmt);

				if(type == "all")
				{	
					caption = innerObj[i].allianceGroupName+" Results in All "+locStr;
					var dtSourceArr = new Array();
					for(var j in innerObj[i].partiesInAlliance)
						{
							for(var k in innerObj[i].partiesInAlliance[j].partyResultsInDistricts)
							{
								var obj = {
											district:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].districtName,
										    partyId:innerObj[i].partiesInAlliance[j].partyId,
											party:innerObj[i].partiesInAlliance[j].partyName,
											totalParticipated: innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].totalConstiParticipated,
											seatsWon:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].seatsWon,
											second:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].secondPos,
											third:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].thirdPos,
											fourth:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].fourthPos,
											nth:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].nthPos,
											pc:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].votesPercentage,
											overall:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].completeVotesPercent
										  }
								dtSourceArr.push(obj);								 		
							}
						}
				} else if(type == "district")
				{
					caption = innerObj[i].allianceGroupName+" Results in "+distName;					
					var dtSourceArr = new Array();
					for(var j in innerObj[i].partiesInAlliance)
						{
							for(var k in innerObj[i].partiesInAlliance[j].partyResultsInDistricts)
							{
								if(distName ==  innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].districtName)
								{	
									var obj = {
												district:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].districtName,
												party:innerObj[i].partiesInAlliance[j].partyName,
												totalParticipated: innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].totalConstiParticipated,
												seatsWon:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].seatsWon,
												second:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].secondPos,
												third:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].thirdPos,
												fourth:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].fourthPos,
												nth:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].nthPos,
												pc:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].votesPercentage,
												overall:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].completeVotesPercent
											  }
									dtSourceArr.push(obj);
								}									 		
							}
						}
				}
				
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	 {
		var Party = oRecord.getData("party");
		var partyIds = oRecord.getData("partyId");
		if(oData != 'IND' && partyIds != null){
			elLiner.innerHTML =
		     "<a href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}">'+Party+'</a>';
	};

				var allianceDistrictResultsColumnDefs = [
										{key: "district", label: "Location", sortable:true},		
										{key: "party", label: "<%=party%>", sortable:true,formatter:YAHOO.widget.DataTable.partyLink},
										{key: "totalParticipated", label:"TP*",formatter:"number", sortable:true},										
				              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
				              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
				              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
				              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
				              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
				              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
				              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 	
				              	 	 			              	 	 	
				              	 	    ];                	 	    
		
				var allianceDistrictResultsDataSource = new YAHOO.util.DataSource(dtSourceArr); 
				allianceDistrictResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
				allianceDistrictResultsDataSource.responseSchema = {
		                fields: ["district","party", {key: "totalParticipated", parser:"number"},
		                         		  {key: "seatsWon", parser:"number"},
		                         		  {key: "second", parser:"number"},
		                         		  {key:  "third", parser:"number"},
		                         		  {key:  "fourth", parser:"number"},
		                         		  {key: "nth", parser:"number"},
		                         		  {key: "pc", parser:YAHOO.util.DataSourceBase.parseNumber},
		                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber},
		                         		  {key:"partyId", parser:"number"} ] 
		                					   
		        		};
				var myConfigs = { 
					    paginator : new YAHOO.widget.Paginator({rowsPerPage    : 10}) ,
						caption: caption
						};
				
				var allianceDistrictResultsDataTable = new YAHOO.widget.DataTable('allianceResults_district_'+i+'_datatable', allianceDistrictResultsColumnDefs, allianceDistrictResultsDataSource,myConfigs);			
		       	}   			
}
function openPreYearDistAnalysisWindow()
{
	
  <c:if test="${hasDeatiledAnalysis}">
		var currentElectionyear=${year};	
		var selectYearEl = document.getElementById("selectYearDistrictwise");
		var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
		var selectedElectionId =  selectYearEl.options[selectYearEl.selectedIndex].value;
		var yearAlertEl = document.getElementById("yearAlert");
		var browser1;
		var urlStr = "<%=request.getContextPath()%>/districtwiseElectionResultsAnalysysForElectionReportAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&currentElectionyear=${year}&selectedElectionYear="+selectedElectionYear+"&electionId="+selectedElectionId+"";
		if(selectedElectionYear == 'Select Year') 
		{
			yearAlertEl.style.display ='block';
			yearAlertEl.innerHTML = "Please Select A Year!";
			return;
		}
		else {yearAlertEl.style.display ='none';} 
		
		browser1 = window.open(urlStr,"distcomparisioinElectioneport","scrollbars=yes,height=600,width=1000,left=200,top=200");
		browser1.focus();
	</c:if>
	<c:if test="${hasDeatiledAnalysis == false}">
			
			$("#districtAccessPopupDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#ffffff">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'},
								
								});
		showAlertMsg("districtAccessPopupDiv");
	</c:if>
}
function openPreYearStatewiseAnalysisWindow()
{
	var currentElectionyear=${year};	
	var selectYearEl = document.getElementById("selectYearStateWise");
	var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var selectedElectionId =  selectYearEl.options[selectYearEl.selectedIndex].value;
	var yearAlertSEl = document.getElementById("yearAlertS");
	var browser1;
	var urlStr = "<%=request.getContextPath()%>/statewiseElectionResultsComparisionToolAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&currentElectionyear=${year}&selectedElectionYear="+selectedElectionYear+"&electionId="+selectedElectionId+"";
	if(selectedElectionYear == 'Select Year') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Year!";
		return;
	}else {yearAlertSEl.style.display ='none';} 
	
	if (selectedElectionYear == currentElectionyear)
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Yoy have selected same election year!";
		return;
	}else {yearAlertSEl.style.display ='none';} 
	
	browser1 = window.open(urlStr,"stateComparisioinElectioneport","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
	
}
function allDistResultsRadioClickHandler(results,results1)
{
    document.getElementById("showHidText").style.display="none";
	document.getElementById("showHidButton").style.display="none";
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var selectBoxEl = document.getElementsByName("selectBox");
	var allianceDistResultsEl = document.getElementById("allianceDistResults");
	for (i=0; i< selectBoxEl.length; i++)
	{
		if(selectBoxEl[i].style.display == "block")			
		{
			selectBoxEl[i].style.display = 'none';}
	}
	<c:if test="${hasDeatiledAnalysis}">
	buildAllDistrictDatatable(innerObj,"districtResults","all","null","null");
	buildAllianceDistrictResultsDataTable(results1,"all", "null");
	</c:if>
	
	getDistrictResultsInteractiveChartVotesPercent(results,null);
	getDistrictResultsInteractiveChartSeatsWon(results,null);
     <c:if test="${!hasDeatiledAnalysis}">
	   return;
	 </c:if>
	for (j=0; j<allianceDistResultsEl.childNodes.length; j++){
		allianceDistResultsEl.childNodes[j].style.display='block';								
	}
	
	 
}
function partywiseRadioClickHandler()
{
    unSelectAll();
	var partySelectBoxEl = document.getElementById("partySelectBox");
	var distSelectBoxEl = document.getElementById("distSelectBox");
	var stateSelectBoxEl = document.getElementById("stateSelectBox");
	
	if(partySelectBoxEl.style.display == "none")			
	{
		partySelectBoxEl.style.display = 'block';
		document.getElementById("showHidText").style.display = 'block';
		document.getElementById("showHidButton").style.display = 'block';
	}
	if(distSelectBoxEl)
	{	
		if(distSelectBoxEl.style.display == "block")			
		{
			distSelectBoxEl.style.display = 'none';
		}
	}
	if(stateSelectBoxEl)
	{	
		if(stateSelectBoxEl.style.display == "block")			
		{
			stateSelectBoxEl.style.display = 'none';
		}	
	}	 
}
function districtwiseRadioClickHandler()
{
    document.getElementById("showHidText").style.display="none";
	document.getElementById("showHidButton").style.display="none";
	var partySelectBoxEl = document.getElementById("partySelectBox");
	var distSelectBoxEl = document.getElementById("distSelectBox");
	if(partySelectBoxEl.style.display == "block")			
	{
		partySelectBoxEl.style.display = 'none';
	}
	if(distSelectBoxEl.style.display == "none")			
	{
		distSelectBoxEl.style.display = 'block';
		distSelectBoxEl.selectedIndex = '0';
	}
		
}

function  statewiseRadioClickHandler()
{
    document.getElementById("showHidText").style.display="none";
	document.getElementById("showHidButton").style.display="none";
	var stateSelectBoxEl = document.getElementById("stateSelectBox");
	stateSelectBoxEl.style.display = 'block';

	var partySelectBoxEl = document.getElementById("partySelectBox");
	partySelectBoxEl.style.display = 'none';
	
}

function updateResultsStatewise(distName,results)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	if(distName != 'Select State' )
		{
		getInteractiveChartBySeatsWonForADistrict(results,distName);
		getInteractiveChartByVotesPercentForADistrict(results,distName);
		buildAllDistrictDatatable(innerObj,"districtResults","district","null",distName);
		}
		else return;	
	
}
function unSelectAll(){
  var selObj = document.getElementById('partySelectBox');
  for (var i=0; i<selObj.options.length; i++) {
        selObj.options[i].selected = false;
    }
}
function getAllSelParties(){
  var selObj = document.getElementById('partySelectBox');
  var partyN = new Array();
  for (var i=0; i<selObj.options.length; i++) {
    if (selObj.options[i].selected) {
      partyN.push(selObj.options[i].text);     
    }
   }
   if(partyN.length > 0)
	updateDistResultsPartywise(partyN,resultsGlobal);
}
function updateDistResultsPartywise(partyName,results)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var allianceDistResultsEl = document.getElementById("allianceDistResults");
        <c:if test="${!hasDeatiledAnalysis}">
		  buildAllDistrictDatatable(innerObj,"districtResults","partyArray",partyName,"null");
		</c:if>
		//buildAllianceDistrictResultsDataTable(results1,"district", distName);
		//getDistrictResultsInteractiveChartVotesPercent(results,partyName);
	    //getDistrictResultsInteractiveChartSeatsWon(results,partyName);
        getDistrictResultsInteractiveChartVotesPercentForMultParties(results,partyName);
		getDistrictResultsInteractiveChartSeatsWonForMultParties(results,partyName);
		<c:if test="${!hasDeatiledAnalysis}">
		   return;
		</c:if>
		if(electionResultsObj.allianceGroupNamesArray != null && electionResultsObj.allianceGroupNamesArray.length != 0)
			{
			  for (j=0; j<allianceDistResultsEl.childNodes.length; j++)
					
					{	
						allianceDistResultsEl.childNodes[j].style.display='none';						
					}
					
              for(var k in partyName)
			  {
				for (j=0; j<allianceDistResultsEl.childNodes.length; j++){
					if(allianceDistResultsEl.childNodes[j].id == partyName[k])
					{	
						allianceDistResultsEl.childNodes[j].style.display='block';						
					}
				}	
              }				
			}
		
}
function getDistrictResultsInteractiveChartSeatsWonForMultParties(results,partyN)
{
	 var districtWiseGraphEl = document.getElementById("districtWiseSeatsGraph");
	 var chartColumns = results.partiesDistLevel;
	 var chartRows = results.partyResultsforDistWiseChart;
		
	 var data = new google.visualization.DataTable();
	 var partiesArray = new Array();
	
	 data.addColumn('string', 'Party');
      //for columns
	  for(var i in partyN){
			data.addColumn('number', partyN[i]);
            partiesArray.push(partyN[i]);
	  }

     
	  for(var j in chartRows)
	  {
		  
		var partyCount = 0;
        var array = new Array();
		array.push(chartRows[j].partyName);
        for(var l in partyN)
		for(var k in chartRows[j].partyResultsInDistricts)
		{
		  

		  
			  if(chartRows[j].partyResultsInDistricts[k].districtName == partyN[l])
			  {
				   var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
				   array.push(seatsWon);
			  }
            
		  
		  
		}

        data.addRow(array);
       
	  }
	  var locStr = '';
	  if(electionType == 'Parliament')
		locStr = ' State Wise';
	  else if(electionType == 'Assembly')
		locStr = ' District Wise';

      var ctitle=''+locStr+' Selected Parties Results By Seats Won';
	  
	   var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	   if(staticColors != null && staticColors.length > 0)
	   {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 972, height: 550, pointSize: 4,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	   }
	   else
	  {
         new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 1002, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
		
 }
function getDistrictResultsInteractiveChartVotesPercentForMultParties(results,partyN)
 {
	 var districtWiseGraphEl = document.getElementById("districtWiseGraph");
	 var chartColumns = results.partiesDistLevel;
	 var chartRows = results.partyResultsforDistWiseChart;
		
	 var data = new google.visualization.DataTable();
	 var partiesArray = new Array();
	
	 data.addColumn('string', 'Party');
      //for columns
	  for(var i in partyN){
			data.addColumn('number', partyN[i]);
            partiesArray.push(partyN[i]);
	  }

     
	  for(var j in chartRows)
	  {
		  
		var partyCount = 0;
        var array = new Array();
		array.push(chartRows[j].partyName);
        for(var l in partyN)
		for(var k in chartRows[j].partyResultsInDistricts)
		{
		  

		  
			  if(chartRows[j].partyResultsInDistricts[k].districtName == partyN[l])
			  {
				   var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
				   array.push(seatsWon);
			  }
            
		  
		  
		}

        data.addRow(array);
       
	  }
	  var locStr = '';
	  if(electionType == 'Parliament')
		locStr = ' State Wise';
	  else if(electionType == 'Assembly')
		locStr = ' District Wise';

      var ctitle=''+locStr+' Selected Parties Results By Votes Percentage';
	  
	   var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	   if(staticColors != null && staticColors.length > 0)
	   {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 972, height: 550, pointSize: 4,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	   }
	   else
	  {
         new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {width: 1002, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
		
 }

function updateDistResultsDistwise(distName,results,results1)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var allianceDistResultsEl = document.getElementById("allianceDistResults");
	if(distName != 'Select District')
	{
	  <c:if test="${!hasDeatiledAnalysis}">
		buildAllDistrictDatatable(innerObj,"districtResults","district","null",distName);
		buildAllianceDistrictResultsDataTable(results1,"district", distName);
	  </c:if>
		getInteractiveChartBySeatsWonForADistrict(results,distName);
		getInteractiveChartByVotesPercentForADistrict(results,distName);
	}
	else return;
	<c:if test="${!hasDeatiledAnalysis}">
	 return;
	</c:if>
	for (j=0; j<allianceDistResultsEl.childNodes.length; j++){
		allianceDistResultsEl.childNodes[j].style.display='block';								
	}
	
		
	
}
function openPartyPerformanceReportWindow()
{
	var selectYearEl = document.getElementById("selectYearPPR");
	var year =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var selectPartyEl = document.getElementById("selectPartyPPR");
	var party =  selectPartyEl.options[selectPartyEl.selectedIndex].value;
	var allianceCheckboxEl = document.getElementById("pprCheckBox");
	var alliances = allianceCheckboxEl.checked;
	var yearAlertSEl = document.getElementById("yearAlertPPR");
	var reportLevel = "1";
	var browser1;
	var urlStr = "<%=request.getContextPath()%>/partyPerformanceReportPopup.action?state=${stateID}&country=1&district=0&1="+reportLevel+"&electionType=${electionTypeId}&year="+year+"&party="+party+"&alliances="+alliances;
	if(year == 'Select Year' && party == '0') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select Year and Party!";
		return;
	}else {yearAlertSEl.style.display ='none';}
	if(year == 'Select Year') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Year!";
		return;
	}else {yearAlertSEl.style.display ='none';}
	if(party == '0') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Party!";
		return;
	}else {yearAlertSEl.style.display ='none';} 
		
	browser1 = window.open(urlStr,"partyPerformanceReport","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
}
function openElectionComparisionReportWindow()
{
	
	var selectYearEl = document.getElementById("selectYearECR");
	var electionYears2 =  selectYearEl.options[selectYearEl.selectedIndex].text;	
	var selectPartyEl = document.getElementById("selectPartyECR");
	var party =  selectPartyEl.options[selectPartyEl.selectedIndex].value;
	var selectedPartyName =  selectPartyEl.options[selectPartyEl.selectedIndex].text;
	var allianceCheckboxEl = document.getElementById("ecrCheckBox");
	var alliances = allianceCheckboxEl.checked;
	var yearAlertSEl = document.getElementById("yearAlertECR");
	var reportLevel = "1";
	var browser1;
	var urlStr = "<%=request.getContextPath()%>/electionComparisonReportPopUp.action?state=${stateID}&electionType=${electionTypeId}&electionYears2="+electionYears2+"&electionYears1=${year}&party="+party+"&allianceCheck="+alliances+"&selectedPartyName="+selectedPartyName;
	if(electionYears2 == 'Select Year' && party == '0') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select Year and Party!";
		return;
	}else {yearAlertSEl.style.display ='none';}
	if(electionYears2 == 'Select Year') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Year!";
		return;
	}else {yearAlertSEl.style.display ='none';}
	if(party == '0') 
	{
		yearAlertSEl.style.display ='block';
		yearAlertSEl.innerHTML = "Please Select A Party!";
		return;
	}else {yearAlertSEl.style.display ='none';} 
		
	browser1 = window.open(urlStr,"electionComparisionReport","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
}

function buildGraphsCarousel(divId,arr)
{
	var elmt = document.getElementById(divId);
	if(!elmt && arr.length == 0)
		return;

	var contentStr = '';
	contentStr+='<ul>';
	for(var i in arr)
	{				
		contentStr+='<LI style="width:880px;height:300px;"><IMG src="charts/'+arr[i]+'"></IMG></LI>';		
	}
	contentStr+='</ul>';

	elmt.innerHTML = contentStr;

	graphImagesCarousel = new YAHOO.widget.Carousel(divId,
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 1,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

	graphImagesCarousel.render(); 
	graphImagesCarousel.show();
}

function handleAddCommentsSubmit(id,category,constituencyId)
{
	
	var commentVal = document.getElementById("commentText").value; 
	var postedByVal = document.getElementById("commentPostedByText").value;
	var partyId;
	var candidateId;
	var constituencyId;
	var commentCategoryId; 
	if(category == "candidate")
	{
		var commentCategoryEl = document.getElementById("commentsClassificaitonSelectBox");
		if(commentCategoryEl)
		{
			commentCategoryId = commentCategoryEl.value
		}	
		partyId = '0';
		candidateId = id;
		constituencyId = constituencyId;
		
		
	}
	if(category == "party")
	{
		partyId = id;
		candidateId = '0';
		constituencyId = '0';
		commentCategoryId = '0';	
		
	}
	var jsObj={
			electionId: electionId,
			electionType: electionType,
			year: year,
			partyId: partyId,
			candidateId: candidateId,
			constituencyId: constituencyId,
			commentDesc: commentVal,
			postedBy: postedByVal,
			category: category,
			commentCategoryId: commentCategoryId,
			task:"addNewComment"
				
		  }
	 
	
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam;		
callAjax(rparam,jsObj,url);
	
	addCommentsDialog.hide();
	
}
function removeOptionSelected()
{
 if(document.getElementById('partyTopStories') != null)
 {
  var elSel = document.getElementById('partyTopStories');
  var i;
  for (i = elSel.length - 1; i>=0; i--) {
    if (elSel.options[i].value == 0) {
      elSel.remove(i);
	  $("#partyTopStories").prepend("<option value='0'>All Parties</option>");
	  document.getElementById('partyTopStories').value = 0;
    }
  }
 }
}
</SCRIPT>
</HEAD>

<BODY>
<center>
<DIV id="sampleDiv"></DIV>
<div id="candidateResultAccessDiv"></div>
<div id="districtAccessPopupDiv"></div>

 <div class="main-mbg"><h1 style = "font-size:15px;"><c:if test="${electionType != 'Parliament'}">${stateName}&nbsp;${electionType} Election Results ${year}</c:if></h1>
<h1 style = "font-size:15px;"><c:if test="${electionType == 'Parliament'}">Parliament Election Results ${year}</c:if></h1>

<span style="float: right; margin-top: -27px;">
			<g:plusone size="medium"></g:plusone>

			<script type="text/javascript">
			 (function() {
			  var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
				 po.src = 'https://apis.google.com/js/plusone.js';
				 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
			 })();
			</script>
		</span>
		<span style="margin-top: -27px; margin-right: 85px; float: right;">
			<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/electionDetailsReportAction.action?electionId=${electionId}&stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&electionTypeId=${electionTypeId}&year=${year}">
				Tweet</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
			</script>
		</span>

 <span style="margin-top: -27px; margin-right: 220px; float: right;">
 <a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/electionDetailsReportAction.action?electionId=${electionId}&stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&electionTypeId=${electionTypeId}&year=${year}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>
</span>
</div>
<div style="padding: 5px 60px 5px 0px;text-align:right;">
  <input type="button" class="buttonClass" id="buttonId" onclick="toggleOption(this.id);" value="Hide Highlights Of ${year} Election Results">
</div>
<div class="clear"></div>

<c:if test="${loginStatus ==null && sessionScope.USER == null}">
<div id="registerDiv" style="position:fixed;width:159px;z-index:9999;margin-top:-93px;margin-left:152px;background-color:#F9F9F9;color:#000;padding:5px;cursor:pointer;border:2px solid #06ABEA">
<a href="javaScript:hideRegisterDiv();" style="float: right;"><b>(X)</b></a>
<a 
href="freeUserRegistration.action"><b>Register</b>  </a>for free to view more details like 
election results 

<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">
district
</c:if>

<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">
state
</c:if>
wise etc. 

</div>
</c:if>

<script>
function hideRegisterDiv(){
	$('#registerDiv').hide('slow');

}

function setDefault(){
	$('#stateListId').val(0);
}
</script>



<div style="margin-top:46px;">


<span class="badge badge-info" style="padding:14px;background:#06ABEA;" >
	<label style="padding:9px;"><input type="radio" value="Assembly" name="selectScope" onClick="showStates();setDefault();" checked="true" id="state"><b style="font-size:14px;">Assembly</b></label>

	<label style="padding:9px;">
	<c:if test="${electionGoverningBodyVO.electionType == 'Assembly'}">	
	<input type="radio" onchange="hideStates();getMinistryYearsonChangeRadio();"  value="Parliament" id="parlSel" name="selectScope">
	</c:if>

	<c:if test="${electionGoverningBodyVO.electionType != 'Assembly'}">	
	<input type="radio" checked  onchange="hideStates();getMinistryYearsonChangeRadio();"  value="Parliament" id="parlSel" name="selectScope">
	</c:if>
	
	
	<b style="font-size:14px;">Parliament</b></label>



	<label style="margin-left:27px;"><span class="stateLabel"><b>Select State :</b></span>
		<select onchange="getMinistryYearsonChange();" id="stateListId" style="padding:3px;box-shadow:1px 2px 9px #005FCC;">
		<option value="0">Select State</option>			  
		</select>
	</label>

	<label><b>Select Year :</b>
		<select style="padding:3px;box-shadow:1px 2px 9px #005FCC;width:67px;" id="yearSelId">
		<option value="0">Select Year</option>
		</select>
	</label>

	<label>
	<a onclick="navigateToMinisterPage()"><input type="button" class="btn" value="View"></a>
	</label>

	</span>

</div>




	<script>

getStatesForAssembly();

function hideStates(){
	$('#stateListId').hide();
	$('.stateLabel').hide();

}

function showStates(){

	$('#yearSelId').find('option').remove();
	$('#stateListId').show();
	$('.stateLabel').show();

}

var selectedRadio ;
$(document).ready(function() {

	$("input:radio[name=selectScope]").click(function() {
     selectedRadio = $(this).val();
});

});

function getStatesForAssembly()
{
	var electionTypeVal ;

	if(selectedRadio == 'Parliament')
		electionTypeVal = 1;
	else
		electionTypeVal = 2;
	var jsObj=
		{						
			electionType:electionTypeVal,
			task:"getStates"
		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;						
	callAjax1(jsObj,url);
    
}


function getMinistryYearsonChangeRadio(){

	//$('#stateListId').val(0);

	var jObj = {
			stateId : 1,
		    electionType: selectedRadio,
				task: 'getElectionYearsForAState'
				};
    var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
    callAjax1(jObj,url);

}

function getMinistryYearsonChange(){

	var electionType;

	 electionType = $("input:radio[name=selectScope]").val();	
	var stateId = $('#stateListId').val()
		
	
	

	var jObj = {
			stateId : stateId,
		    electionType: electionType,
				task: 'getElectionYearsForAState'
				};
    var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
    callAjax1(jObj,url);	

}

getMinistryYearsDefault();

function getMinistryYearsDefault(taskType){


	var electionType = '${electionGoverningBodyVO.electionType}';
	var stateId = '${electionGoverningBodyVO.stateId}';


	if(electionType == "Parliament")
		stateId = 1;


	var jObj = {
			stateId : stateId,
		   electionType: electionType,
				task: 'getElectionYearsForAState'
				};
    var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
    callAjax1(jObj,url);


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
	callAjax1(jsObj,url);
}
function callAjax1(jsObj,url)
{

		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jsObj.task == "getMinistryYears")
									{

										buildYearsData(myResults);

									    
									}else if(jsObj.task == "ministerData"){
                               
										if(myResults == null)
											$('#ministerDetailsDiv').hide();


									}
									else if(jsObj.task ==  "getElectionYearsForAState"){

                                          buildYearsData(myResults);
									}
									else if(jsObj.task == "getStatesForAssign")
									{
									     // removeData("stateListId");
										 // addState("stateListId");
									     // buildData(myResults,"stateListId");
									}
									else if(jsObj.task == "getStates")
									{

										buildStatesData(myResults);
									      //removeData("stateListId");
										  //addState("stateListId");
									      //buildData(myResults,"stateListId");
										 // setselectedIdforAState(myResults,minStateId);
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

function buildRegionWiseDataTable(results)
{
   if(results != null && results.partyResultsInRegionVOLst!= null && results.partyResultsInRegionVOLst.length > 0)
   {
      var obj = new Array();
	  var regions = new Array();
	  for(var j in results.partyResultsInRegionVOLst){
	    var objval = new Array();
		objval["name"] = results.partyResultsInRegionVOLst[j].regionName;
		regions.push(objval);
	  }
	  for(var i in  results.partyResultsInRegionVOLst[0].partyResultsInRegion)
	  {
	    var objval = new Array();
	     objval["partyName"] = results.partyResultsInRegionVOLst[0].partyResultsInRegion[i].partyName;
	     for(var j in results.partyResultsInRegionVOLst)
		 {
		   //var x = j+1;
		   objval["seatsParticipated"+j] = results.partyResultsInRegionVOLst[j].partyResultsInRegion[i].seatsParticipated;
		   objval["totalSeatsWon"+j] = results.partyResultsInRegionVOLst[j].partyResultsInRegion[i].totalSeatsWon;
		   objval["percentage"+j] = results.partyResultsInRegionVOLst[j].partyResultsInRegion[i].percentage;
		   objval["PConstavgPercentage"+j] = results.partyResultsInRegionVOLst[j].partyResultsInRegion[i].PConstavgPercentage;
		 }
		  obj.push(objval);
	  }
      buldDataTable(obj,regions);
   }
}

function buldDataTable(obj,regions){
  $("#regionWiseDataTableDiv").show();
  var resultsDataSource = new YAHOO.util.DataSource(obj);
	resultsDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseschema = {
		fields : [
		{
			key : "partyName"
		}
		]
	};
    for(var i=0;i<regions.length;i++)
    {
	   var obj1 = {
    	     key :"seatsParticipated"+i,parser:"number"
    		};
	   var obj2 = {
    	     key :"totalSeatsWon"+i,parser:"number"
    		};
	   var obj3 = {
    	     key :"percentage"+i,parser:"number"
    		};
	   var obj4 = {
    	     key :"PConstavgPercentage"+i,parser:"number"
    		};
    		resultsDataSource.responseschema.fields.push(obj1);
			resultsDataSource.responseschema.fields.push(obj2);
			resultsDataSource.responseschema.fields.push(obj3);
			resultsDataSource.responseschema.fields.push(obj4);
	}
	var resultsColumnDefs = [ 
	{
		 label: "Party",
		 key  : "partyName",
		sortable : true
	}
	];
   for(var i in regions)
    {
	  var obj;
	     obj = {
    	label:""+regions[i].name,
		children:[ 
					{
						label : "TP*",
						key : "seatsParticipated"+i,
						sortable : true
					},
					{
						label : "Won",
						key : "totalSeatsWon"+i,
						sortable : true
					},
					{
						label : "CVP* %",
						key : "PConstavgPercentage"+i,
						sortable : true
					},
					{
						label : "CPVP* %",
						key : "percentage"+i,
						sortable : true
					}				
				]
    		};
    		resultsColumnDefs.push(obj);
	}
				
	var myConfigs = { 
			    caption:"	Region Wise Party Performance" 
				};			
				
    myDataTable = new YAHOO.widget.DataTable("regionWiseDataTableDiv",resultsColumnDefs, resultsDataSource,myConfigs); 
}


function buildStatesData(myResults){

 $('#stateListId').find('option').remove();
 $('#stateListId').append($("<option></option>").attr("value",0).text("Select State")); 

for(var i=0;i<myResults.length;i++)
  $('#stateListId').append($("<option></option>").attr("value",myResults[i].id).text(myResults[i].name)); 

$('#stateListId').val('${electionGoverningBodyVO.stateId}');

}

function buildYearsData(myResults){

  $('#yearSelId').find('option').remove();

for(var i=0;i<myResults.length;i++)
  $('#yearSelId').append($("<option></option>").attr("value",myResults[i].id).text(myResults[i].name)); 


   $('#yearSelId').val(${electionId});
}

function navigateToMinisterPage(){

	var year = $('#yearSelId').val();

	var url="electionDetailsReportAction.action?electionId ="+year+"";

	//var url = "ministersPageAction.action?electionId="+$('#yearSelId').val();

	window.location.href=url;

	//window.open(url, '_blank');


}
	</script>



<div id="topStoriesDIV">
   <DIV class="graphTop"> Highlights Of ${year} 
   <c:if test="${electionType != 'Parliament'}">${stateName}&nbsp;</c:if>&nbsp;${electionType}&nbsp;Elections</DIV>
   <div class="topStories">
       <div id="topStoriesMenu" style="width:100%;">
          <div id="topStoriesSelect" style="padding-top:10px;padding-bottom:5px;align:left;">
              <input type="radio" checked="true" name="topStoriesRadio" id="topVotes" onclick="setId(this.id);getAllTopStories(chkSize,'TopVotesGained');" />&nbsp;<b style="font-size11px;">Highest Votes Earned</b> &nbsp;&nbsp;<input type="radio" name="topStoriesRadio"  id="topPercVotes" onclick="setId(this.id);getAllTopStories(chkSize,'TopVotesGainedPerc');" />&nbsp;<b style="font-size11px;">Highest Votes Percentage Earned</b> &nbsp;&nbsp;<input type="radio" name="topStoriesRadio" id="topMargin"  onclick="setId(this.id);getAllTopStories(chkSize,'HighestMarginGained');"/>&nbsp;<b style="font-size11px;">Highest Margin Votes Earned</b> &nbsp;&nbsp;<input type="radio" name="topStoriesRadio" id="lowMargin"  onclick="setId(this.id);getAllTopStories(chkSize,'LowestMarginGained');"/>&nbsp;<b style="font-size11px;">Lowest Margin Votes Earned</b> &nbsp;&nbsp;<input type="radio" name="topStoriesRadio" id="highestAssets"  onclick="checkUserStatus();"/>&nbsp;<b style="font-size11px;">Highest Assets</b>
          </div>
            <c:if test="${hasDeatiledAnalysis}">
             <table style="width:100%;"><tr>
               <td style="width:16%;padding-left:70px;"><b style="font-size11px;">Select Party: </b></td>
               <c:if test="${electionType == 'Assembly'}">
                   <td style="width:15%;"><s:select id="partyTopStories" theme="simple"  name="selectParty" list="partiesList" listKey="id" listValue="name" cssClass ="partyNames" onchange="getTopStoriesForParty(this.id);" />	</td>
               </c:if>
               <c:if test="${electionType != 'Assembly'}">
                   <td style="width:15%;"><select id="partiesNames" class="partyNames" onchange="getTopStoriesForParty(this.id);"  ><option value="0">All Parties</option></select>	</td>
               </c:if>
               <td style="width:40%">
                  <div>
                     <div class="dashBoardtabsDiv" style="align:left;">
	                    <a onclick="getTopStories(this.id,3);" id="top3Id">Top 3</a>
	                    <a onclick="getTopStories(this.id,5);" id="top5Id">Top 5</a>
	                    <a onclick="getTopStories(this.id,10);" id="top10Id">Top 10</a>
                     </div>
                  </div>
               </td>
               <td>
                  <div style="width:10%:text-align:left;">
                     <div id="searchAjaxImgSpan" style="display:none;">
                         <img src="images/icons/search.gif">
                     </div>
                  </div>
               </td></tr>
             </table>
            </c:if>
			<c:if test="${!hasDeatiledAnalysis}">
			 <table style="width:100%;">
			  <tr>
			    <td style="width:70%">
                  <div style="padding-left:300px;">
                    <div class="dashBoardtabsDiv" style="align:left;">
	                  <a onclick="getTopStories(this.id,3);" id="top3Id">Top 3</a>
	                  <a onclick="getTopStories(this.id,5);" id="top5Id">Top 5</a>
	                  <a onclick="getTopStories(this.id,10);" id="top10Id">Top 10</a>
                    </div>
                  </div>
                </td>
                <td>
                  <div style="width:10%:text-align:left;">
                    <div id="searchAjaxImgSpan" style="display:none;">
                       <img src="images/icons/search.gif">
                    </div>
                  </div>
                </td>
			  </tr>
             </table>
			</c:if>
       </div>
       <div id="topVotesGained"></div>
       <div id="topVotesGainedPerc"></div>
       <div id="highestMarginGained"></div>
       <div id="lowestMarginGained"></div>
	   <div id="highestAssetsDetails"></div>
   </div>
   <DIV class="graphBottom"></DIV>
</div>

<div id="stateResults">
</div>
<div id="electionPageAjaxImgDiv">
	<div> Loading Election Results Please Wait..</div>
	<img src="images/icons/barloader.gif"/>
</div>



<DIV id="task1"></DIV>
<c:if test="${electionType != 'Parliament'}"><DIV class="graphTop">State Level Overview</DIV>
 </c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="graphTop">Country Level Overview</DIV></c:if>

<DIV id="statewiseGraph">
 <DIV id="stateRegionsDiv"></DIV>



<DIV id="graphImage" class="yui-skin-sam" style="width:970px;overflow:hidden;margin:auto;"></DIV>
<div class="yui-skin-sam" style="margin:15px;">
<div id="regionWiseDataTableDiv" style="overflow-x: scroll;"></div>
</div>
<div id="ministerDetailsDiv">
<c:if test="${electionType != 'Parliament'}"> 
<a class="btn btn-primary" style="float:right;" href="ministersPageAction.action?electionId=${electionId}"><b>View ${stateName} Minister Details of ${year} Assembly</b></a>
</c:if>

<c:if test="${electionType == 'Parliament'}">

<a class="btn btn-primary" style="float:right;" href="ministersPageAction.action?electionId=${electionId}"><b>View Minister Details of ${year} Parliament </b></a>
</c:if>

</div>



<DIV class="yui-skin-sam" style="width:880px;">
	<TABLE border="0" width="95%" class="container" >
		<TR>
			<TD valign="top" align="left">
				<DIV id="partywiseResultsDataTable_main">
					<div id="partywiseResultsDataTable"></div>					
					<div id="partywiseImgChart"></div>
					<div id="partywiseResults_img_anc"></div>
					<div id="partywiseResultsWithoutAlliance"></div>
				</DIV>
			</TD>
			<TD valign="top"><DIV id="allianceResultsDataTable"></DIV></TD>
			<!--<c:if test="${electionType == 'Assembly'}"> 
			    <TD valign="top"><DIV id="allianceResultsDataTable"></DIV></TD>
			</c:if>-->
			<c:if test="${!hasDeatiledAnalysis}">
			   <TD><DIV id="overallDataTable"></DIV></TD>
			</c:if>
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>		
	</TABLE>
	<DIV id="note" name="note" style="display:none;"><P><FONT style="font-weight:bold;color:red;" >Note:</FONT>&nbsp;TP and PC% columns are empty for alliance parties in Partywise Results table, to find TP and PC% for alliance parties kindly refer TP and PC% columns of Alliance Details Table.TP and PC% are Not Applicable for Independent Candidates(IND).</P></DIV>
</DIV>


<c:if test="${hasDeatiledAnalysis}">
<DIV style="padding:10px;">
	<TABLE width="50%" border="0" cellpadding="0" cellspacing="0">
		<TR>
			<TD align="left"><IMG src="images/icons/infoicon.png" border="none" /></TD>
			<TD align="center" style="color:#606060;font-size:12px;">Click Here To Access Candidates Results</TD>
			<TD align="left"><DIV><A href="javascript:{}" class="viewChartsForResults1" onclick="showCandidateDetailsWindow(stateName,electionType,year)">View Candidates Results</A></DIV></TD>
		</TR>
		
	</TABLE>
</DIV>
</c:if>



<!--<DIV style="padding:10px;text-align:right;"><A href="javascript:{}" class="viewChartsForResults" onclick="showCandidateDetailsWindow(stateName,electionType,year)">View Candidates Results</A></DIV>
--></DIV>
<DIV id="viewCandidate" class="yui-skin-sam"></DIV>
<DIV class="graphBottom"></DIV>

<DIV id="subRegionWiseDetailsDiv">
<c:if test="${sessionScope.USER != null}">
<c:if test="${electionType != 'Parliament'}">
<DIV class="graphTop">District Level Overview</DIV></c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="graphTop">State Level Overview</DIV></c:if>
<DIV id="distwiseGraph">
<DIV id="districtWiseGraph"></DIV>
<DIV id="districtWiseSeatsGraph"></DIV>
<DIV id="detailedGraph" style="text-align:right;padding:15px;"></DIV>
<c:if test="${hasDeatiledAnalysis}">
<DIV id="distResultsViewOptionsDiv">
	<TABLE width="100%">	
		<tr><td colspan="6"><div id="showHidText" style="padding-left:80px;display:none;" ><b>Press Ctrl Key To Select Multiple Parties</b></div></td></tr>		
		<TR style="vertical-align:top">
			<TD style="width:10%;"><INPUT type="radio" name="distResultsOption" id="allDistResultsRadio" value="all" onClick="allDistResultsRadioClickHandler(resultsGlobal,allianceResultsGlobal)" checked="true"/>All</TD>
			<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="partywiseRadio" value="partywise" onClick="partywiseRadioClickHandler()"/>Partywise</TD>
			<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="partySelectBox"  name="selectBox" multiple="multiple"  style="display:none;"></SELECT></TD>
			<td style="width:10%;display:none;" id="showHidButton"><input type="button" class="buttonClass" value="View" onclick="getAllSelParties();" /></td>
				<c:if test="${electionType != 'Parliament'}">
					<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="districtwiseRadio" value="districtwise" onClick="districtwiseRadioClickHandler()"/>Districtwise</TD>
					<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="distSelectBox"  name="selectBox"  onchange="updateDistResultsDistwise(this.options[this.selectedIndex].text,resultsGlobal,allianceResultsGlobal)" style="display:none;">
							<OPTION id="0" >Select District</OPTION>					
						</SELECT>				
					</TD>
				</c:if>
				<c:if test="${electionType == 'Parliament'}">
					<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="statewiseRadio" value="statewise" onClick="statewiseRadioClickHandler()"/>Statewise</TD>
					<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="stateSelectBox"  name="selectBox"  onchange="updateResultsStatewise(this.options[this.selectedIndex].text,resultsGlobal)" style="display:none;">
							<OPTION id="0" >Select State</OPTION>					
						</SELECT>				
					</TD>
				</c:if>			
		</TR>
	</TABLE>	
</DIV>
</c:if>
</c:if>
<c:if test="${hasDeatiledAnalysis}">
<DIV class="yui-skin-sam" >
	<TABLE border="0" width="95%" >
		<TR>
			<TD valign="top" align="left">
				<DIV id="districtResults"></DIV>
				<DIV id="districtResults_withoutAllianceDiv"></DIV>
			</TD>
			
			<TD valign="top"><DIV id="allianceDistResults"></DIV></TD>
		</TR>
		<TR>
			<c:if test="${electionType == 'Parliament'}">
				<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">Location column in above table refers State Name</SPAN></TD>
			</c:if>	
			<c:if test="${electionType != 'Parliament'}">
				<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">Location column in above tables refers District Name</SPAN></TD>
			</c:if>
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>
		<TR>
			<TD colspan="2" align="left"><DIV id="note" name="note" style="display:none;"><P><FONT style="font-weight:bold;color:red;" >Note:</FONT>&nbsp;TP and PC% columns are empty for alliance parties in Partywise Results table, to find TP and PC% for alliance parties kindly refer TP and PC% column of Alliance Details Table.TP and PC% are Not Applicable for Independent Candidates(IND).</P></DIV></TD>
		</TR>		
	</TABLE>
	
</DIV>
</c:if>	
</DIV>

<DIV class="graphBottom"></DIV>
</DIV>
<DIV class="yui-skin-sam" >
<div id="partyperformance" >
<c:if test="${loginStatus !=null && sessionScope.USER != null}">
<DIV class="graphTop"> Party Performance In Different Aspects</DIV>
<div class="partyperformance">
</c:if>
	<TABLE border="0" style="width:850px;" >
		<TR>
			<TD valign="top" align="center">
				<DIV id="genderWiseResultsDataTable"></DIV>
			</TD>
		</TR>

		<TR>
			<TD><Table><TR>
					<TD valign="top" align="center">
						<DIV id="constituencyTypeWiseOverviewTable"></DIV>
					</TD>
					<TD valign="top" align="center">
						<DIV id="constituencyTypeWiseOverviewGraph" ></DIV>
					</TD>
				</TR></Table>
			</TD>
		</TR>
		
					
	</TABLE>
	<div id="ubanDataHideShowDiv" style="padding:5px;margin:5px;border:1px solid #d3d3d3;display:none;">
	<TABLE>
	    <TR>
			<TD valign="top" align="center">
				<DIV id="ConstituecyAreaTypeSelectOptionsDiv" style="display:none;"></DIV>
			</TD>
		</TR>
        <TR>
			<TD valign="top" align="center">
				<DIV id="partyPerfResultsDataTable"></DIV>
			</TD>
		</TR>
	</TABLE>
	<c:if test="${hasDeatiledAnalysis}">
	
	<div id="UbanPercentageWiseGraphSelectionDiv">
		<table><tr>
		<td><input type="radio" checked="true" name="urbanPer" value="percentage" onClick="buildUbanPercentageWisePartyDetailsGraph()">
		<b><font id="visiblePublicText" color="#4B74C6">View Graph by Party Gained Votes Percentage</font></b></td>
		<td><input type="radio" name="urbanPer" value="seats" onClick="buildUbanPercentageWisePartyDetailsGraph()">
		<b><font id="visiblePublicText" color="#4B74C6">View Graph by Party Gained Seats</font></b></td>
		<td></td>
		</tr></table>
	</div>

	<div id="UbanPercentageWiseGraph"></div>
    </c:if>
	</div>
	<c:if test="${loginStatus !=null && sessionScope.USER != null}">
	<table>
	  <tr>
	    <td><b>TP* = Total Participation , CVP* % = Complete Votes Percentage , CPVP* % = Complete Participated Votes Percentage</b></td>
	  </tr>
	  <tr>
	    <td><b>MCGV* = Male Candidates Gained Votes % , FCGV* % = Female Candidates Gained Votes % ,CV* % = Complete Votes Percentage</b></td>
	  </tr>
	  <tr>
	    <td><b>PCV* % = Participated Constituency Votes Percentage , RCP* = Rural Constituency Participated , RC* = Rural Constituency</b></td>
	  </tr>
	  <tr>
	    <td><b>RCV* % = Rural Constituency Votes Percentage , UCP* = Urban Constituency Participated , UC* = Urban Constituency</b></td>
	  </tr>
	  <tr>
	    <td><b>UCV* % = Urban Constituency Votes Percentage , RUCP* = Rural Urban Constituency Participated , RUC* = Rural Urban Constituency</b></td>
	  </tr>
	  <tr>
	    <td><b>RUCV* % = Rural Urban Constituency Votes Percentage </b></td>
	  </tr>
	</table>
	</c:if>
<c:if test="${hasDeatiledAnalysis}">
</div>
<DIV class="graphBottom"></DIV>	
</c:if>
</div>
</DIV>
<c:if test="${electionType == 'Assembly' || electionType == 'Parliament'}">
 <s:if test="wonCandidateResults != null && wonCandidateResults.size() > 0">
  <div style="width:998px;margin-left:auto;margin-right:auto;">
 <DIV class="graphTop" style="text-align: center;"> Winning Candidates in ${year} 
   <c:if test="${electionType != 'Parliament'}">${stateName}&nbsp;</c:if>&nbsp;${electionType}&nbsp;Elections</DIV>
  <div id="wonCandidatesTableDiv" >
    
	  <table id="wonCandidatesTable">
	     <thead>
		  <tr id="wonCandidatesTableSearch">
		   <th>Constituency</th>
		   <th>Candidate</th>
		   <th>Party</th> 
		   <th>Total Voters</th>
		   <th>Polled Votes</th>
		   <th>Polled Votes %</th>
		   <th>Votes Gained</th>
		   <th>Votes Gained %</th>
<!--		   <th>Margin</th>
		   <th>Margin %</th>-->
		  </tr>
		  <tr id="wonCandidatesTablehead">
		   <th>Constituency</th>
		   <th>Candidate</th>
		   <th>Party</th> 
		   <th>Total Voters</th>
		   <th>Polled Votes</th>
		   <th>Polled Votes %</th>
		   <th>Votes Gained</th>
		   <th>Votes Gained %</th>
<!--		   <th>Margin</th>
		   <th>Margin %</th>-->
		  </tr>
		 </thead>
		 
		 <tbody>
		  <s:iterator value="wonCandidateResults" var="candidatesresults">
		   <tr> 
		      <td><a href='constituencyPageAction.action?constituencyId=<s:property value="constituencyId"/>'><s:property value="constituencyName"/></a></td>
			  <td><a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>'><s:property value="candidateName"/></a></td>
			  <c:if test="${candidatesresults.partyName != 'IND'}">
			    <td class="textalignclass"><a href='partyPageAction.action?partyId=<s:property value="partyId"/>' ><s:property value="partyName"/></a></td>
			  </c:if>
			  <c:if test="${candidatesresults.partyName == 'IND'}">
			    <td class="textalignclass"><s:property value="partyName"/></td>
			  </c:if>
			  <td class="textalignclass"><s:property value="totalVotes"/></td>
			  <td class="textalignclass"><s:property value="polledVotes"/></td>
			  <td class="textalignclass"><s:property value="votesPercentage"/></td>
			  <td class="textalignclass"><s:property value="votesEarned"/></td>
			  <td class="textalignclass"><s:property value="votesPercentageBySecond"/></td>
<!--			  <td class="textalignclass"><s:property value="marginVotes"/></td>
			  <td class="textalignclass"><s:property value="marginPercent"/></td>-->
		   </tr>
		  </s:iterator>
		 </tbody>
		 <tfoot>
		  <tr  id="wonCandidatesTablefooter">
		   <th>Constituency</th>
		   <th>Candidate</th>
		   <th>Party</th> 
		   <th>Total Voters</th>
		   <th>Polled Votes</th>
		   <th>Polled Votes %</th>
		   <th>Votes Gained</th>
		   <th>Votes Gained %</th>
<!--		   <th>Margin</th>
		   <th>Margin %</th> -->
		  </tr>
		 </tfoot>
	  </table>
  </div>
  <DIV class="graphBottom"></DIV>
   </div>
   <script type="text/javascript">
     $('#wonCandidatesTable').dataTable({
		"aLengthMenu": [[15,50,100,200, -1], [15, 50,100,200,"All"]]
	})
		  .columnFilter({ 	sPlaceHolder: "head:before",
		  	
			aoColumns: [ { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "number"},
				         { type: "number"},
						 { type: "number"},
						 { type: "number"},
						 { type: "number"}
						 
				]

		});
   </script>
 </s:if>
</c:if>
<div id="accessDiv" style="display:none"></div>
<div class="clear"></div>
<DIV id="analysisToolsDataDiv" style="width:998px;margin-left:auto;margin-right:auto;">
<DIV class="graphTop">Analysis Tools</DIV>
<DIV id="toolsDiv">
	<TABLE class="toolsTable"><TR>
		<TD class="td">
			<DIV class="toolsDisplay">
				<c:if test="${electionType != 'Parliament'}">
					<h3 align="left" style="color:crimson;">Statewise Analysis</h3>
					<P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze and Compare different election results Statewise.</P>
				</c:if>
				<c:if test="${electionType == 'Parliament'}">
					<h3 align="left">Countrywise Analysis</h3>
					<P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze and Compare different election results Countrywise.</P>
				</c:if>	
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD colspan="2" align="left"><DIV id="yearAlertS" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
						</TR>						
						<TR>	
							<TD align="left">Year:</TD>
							<TD align="left"><SELECT id="selectYearStateWise" name="selectYearStateWise" style="width: 100px; margin-top: 3px;">
								<c:forEach var="years"  items="${electionYears}">
								<c:if test="${year != years.name}">
									<OPTION value="${years.id}">${years.name}</OPTION>
								</c:if>
							</c:forEach>
							</SELECT>	
							</TD>
						</TR>							
					</TABLE>
					<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPreYearStatewiseAnalysisWindow()" /></A></DIV>		 
				</DIV>
			</DIV>	
		</TD>		
			<TD class="td">
				<DIV class="toolsDisplay">
					<c:if test="${electionType != 'Parliament'}">
						<h3 align="left" style="color:#827839;">District wise Analysis</h3>
						<P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze and Compare different election results Districtwise.</P>
					</c:if>
					<c:if test="${electionType == 'Parliament'}">
						<h3 align="left">Statewise Analysis</h3>
						<P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze and Compare different election results Statewise.</P>
					</c:if>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD colspan="2" align="left"><DIV id="yearAlert" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>							
						</TR>						
						<TR>
							<TD align="left">Year:</TD>
							<TD align="left"><SELECT id="selectYearDistrictwise" name="selectYearDistrictwise" style="width: 100px; margin-top: 3px;">
								<c:forEach var="years"  items="${electionYears}">
									<c:if test="${year != years.name}">
										<OPTION value="${years.id}">${years.name}</OPTION>
									</c:if>
								</c:forEach>
							</SELECT>
							</TD>							
						</TR>							
					</TABLE>
					<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" onclick="openPreYearDistAnalysisWindow()" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV>		 
				</DIV>				
				</DIV>				
			</TD>		
			<c:if test="${electionType == 'Assembly' || electionType == 'Parliament'}">
			<TD class="td">
				<DIV class="toolsDisplay">
					<h3 align="left" style="color:crimson;">Party Performance</h3>
					<P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze and Compare a party's performance for selected election year.</P>
					<DIV style="font-weight:bold">
						<TABLE width="100%">
							<TR>
								<TD colspan="2" align="left"><DIV id="yearAlertPPR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
							</TR>
							<TR>
								<TD align="left">Party Name:</TD>
								<TD>
								<s:select id="selectPartyPPR" theme="simple"  name="selectParty" list="partiesList" listKey="id" listValue="name"/>							
								</TD>
							</TR>	
							<TR>	
								<TD align="left">Year:</TD>
								<TD align="left"><SELECT id="selectYearPPR" name="selectYearPPR" style="width: 100px; margin-top: 3px;">
									<c:forEach var="years"  items="${electionYears}">
										<OPTION value="${years.id}">${years.name}</OPTION>								
									</c:forEach>
								</SELECT>
								</TD>
							</TR>	
							<TR>	
								<TD colspan="2" align="left"><INPUT type="checkbox" id="pprCheckBox" value="hasAllianceParties" name="alliances"/>Include Alliances</TD>
							</TR>							
						</TABLE>
						<DIV align="right"><A href="javascript:{}" onclick="openPartyPerformanceReportWindow()"><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV>		 
					</DIV>
				</DIV>				
			</TD>
			</c:if>
			<c:if test="${electionType == 'Assembly' || electionType == 'Parliament'}">		
			<TD class="td">
				<DIV id="comparePrevElection" class="toolsDisplay">
					<h3 align="left" style="color:#827839;">Elections Comparison</h3>
					<P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze and Compare current election results with selected election results for a party.</P>
					<DIV style="font-weight:bold">
						<TABLE width="100%">
							<TR>
								<TD colspan="2" align="left"><DIV id="yearAlertECR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
							</TR>	
							<TR>
								<TD align="left">Party Name:</TD>
								<TD>
									<s:select id="selectPartyECR" theme="simple"  name="selectParty" list="partiesList" listKey="id" listValue="name"/>							
								</TD>
							</TR>
							<TR>	
								<TD align="left">Year:</TD>
								<TD align="left"><SELECT id="selectYearECR" name="selectYearECR" style="width: 100px; margin-top: 3px;">
									<c:forEach var="years"  items="${electionYears}">
										<c:if test="${year != years.name}">
											<OPTION value="${years.id}">${years.name}</OPTION>
										</c:if>									
								</c:forEach>
								</SELECT>
								</TD>
							</TR>	
							<TR>	
								<TD colspan="2" align="left"><INPUT type="checkbox" id="ecrCheckBox" value="hasAllianceParties" name="alliances"/>Include Alliances</TD>
							</TR>							
						</TABLE>
						<DIV align="right"><A href="javascript:{}" onclick="openElectionComparisionReportWindow()"><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV>		 
					</DIV>
				</DIV>				
			</TD>
			</c:if>	
		</TR>
	</TABLE>
</DIV>
<DIV class="graphBottom"></DIV>
<DIV class = "yui-skin-sam"><div id="panel" style="left:15px;top:-180px;"></DIV></DIV>
<DIV class = "yui-skin-sam"><div id="detailedResultsPanel"></DIV></DIV>
<DIV class = "yui-skin-sam"><div id="commentsDialogDiv"></DIV></DIV>
</DIV>
<DIV id="task10"></DIV>
<div id="regionAccessDiv" style="display:none;"></div>
<SCRIPT type="text/javascript">
//getElctionsBasicInfo(electionType);
//alert(<%=session.getAttribute("USER")%>);
//alert('${loginStatus}');
getResultsForAnElection(stateID,electionType,year);
<c:if test="${loginStatus !=null && sessionScope.USER != null}">
  getPartyGenderInfo();
</c:if>
<c:if test="${hasDeatiledAnalysis}">

<c:if test="${year >= '2009'}">
	getConstituencyAreaTypeWiseOverview();
	getConstituencyAreaTypeWiseResult();
	getPartiesConstituencyUbanPercentage();
</c:if>
</c:if>
getAllTopStories(3,"TopVotesGained");
$("#top3Id").addClass("dashBoardtabsDivSelected");
removeOptionSelected();

hideStates1();
function hideStates1(){


if('${electionGoverningBodyVO.electionType}' == "Parliament"){
$('.stateLabel').hide();
$('#stateListId').hide();
}
}

hideMinisterDetailsDiv();
function hideMinisterDetailsDiv()
{

    var jsObj =
		{ 
            electionType : '${electionType}',
			electionId:'${electionId}',
			task:"ministerData"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "checkForMinisterData.action?"+rparam;			callAjax1(jsObj,url);
}

</SCRIPT>
</center>
</BODY>
</HTML>