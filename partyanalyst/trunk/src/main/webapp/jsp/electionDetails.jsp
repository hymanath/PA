<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:if test="${electionType != 'Parliament'}"><TITLE>${stateName} ${electionType} Election Results, ${year}</TITLE></c:if>
<c:if test="${electionType == 'Parliament'}"><TITLE>${electionType} Election ${year} Results  </TITLE></c:if>

<meta http-equiv="Content-Language" content="en">

<meta name="description" content="${stateName} ${electionType} Election Details in india ${year},${stateName} ${electionType} Election results ${year},ditrist wise results ,partywise results in all districts .">

<meta name="keywords" content="election details in india, ${stateName} ${electionType} Election Results  ${year}  ,election analysis, ${stateName} ${electionType} Election Details in india ${year}, district wise results, Party Results in all Districts with alliance and without alliance , party results with graphs, election commission of india,election commissioner of india, elections in india, indian elections ${year}, general elections india, forthcoming indian elections, india-elections, elections ${year}, parliament election, forthcoming indian elections, ${year} election results, assembly elections,upcoming indian elections, general elections ${year} schedule india, election schedule, opinion polls, key contenders of forthcoming general elections in india, party profiles, party manifestoes, india. ">

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
	<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<LINK rel="stylesheet" type="text/css" href="styles/styles.css">
<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/electionResultsPage.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">

<style>

#stateResults{
 color:Maroon;
font-family:trebuchet MS;
font-size:14px;
 
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
    width: 900px;
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

}
.ui-widget-header{
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
</style>
<SCRIPT type="text/javascript">
var electionId = '${electionId}';
var electionType = '${electionType}';
var stateID =  '${stateID}' ;
var stateName = '${stateName}';
var year = '${year}';
var electionTypeId = '${electionTypeId}';
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
										
										<c:if test="${hasDeatiledAnalysis}">
										if(myResults.electionResultsInDistricts.allPartiesResults != null &&
											myResults.electionResultsInDistricts.allPartiesResults.length > 0)
										{
											showDistrictWiseResultsLineGraph(myResults);
											buildAllDistrictResultsDataTable(myResults);
											buildAllianceDistrictResultsDataTable(myResults.electionResultsInDistricts,"all", "null");
										}else
										{
											var toolsDiv = document.getElementById("analysisToolsDataDiv");
											var subRegionsDiv = document.getElementById("subRegionWiseDetailsDiv");
                                            toolsDiv.innerHTML='';
											subRegionsDiv.innerHTML='';
										}
										</c:if>

										<c:if test="${!hasDeatiledAnalysis}">
											var subRegionsDiv = document.getElementById("subRegionWiseDetailsDiv");
											subRegionsDiv.innerHTML='';
										</c:if>
									<c:if test="${hasDeatiledAnalysis == false}">
										showAlertMsg();
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
									  buildGenderCountResultsDataTable('genderWiseResultsDataTable',myResults);
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

function showAlertMsg(){

		var accessDivElmt = document.getElementById("accessDiv");
		var str='';

	str+='<img src="images/icons/smiley_sad.png" alt="sorry" style="display:inline;"/>';
	str+='<h3 style="color:#ff0000;display:inline;position:relative;top:-10px;">'; 
	str+='Sorry, You Don\'t have Access Privileges To View Detailed Report. Please Contact Us For Access Privileges.</h3>';
	str+='<span style="font: bold 14px/35px Trebuchet MS,Arial,Helvetica,sans-serif;text-align:center;color:#000;display:block;">';
	str+='Phone No:+91 40 40124153 / +91 096766 96760<br />';
	str+='Email: <a href="mailTo:info@partyanalyst.com">info@itgrids.com</a></span>';
	
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

	var objForPie = {
	         type: 'pie',
	         name: 'Total Constituencys',
	         data: [{
	            name: 'Telangana',
	            y: 119,
	            color: "#4572A7" // Telangana's color
	         }, {
	            name: 'Andhra',
	            y: 123,
	            color: "#AA4643" // Andhra's color
	         }, {
	            name: 'Rayalaseema',
	            y: 52,
	            color: "#89A54E" // Rayalaseema's color
	         }],
	         center: [100, 80],
	         size: 100,
	         showInLegend: false,
	         dataLabels: {
	            enabled: false
	         }
	      };
    
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
	showImg();
	var jsObj = {
				electionId:electionId,
				stateID:stateID,
				task:"getRegionWisePartyElectionResults"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/regionWisePartyElectionResultsAjaxAction.action?"+param;
	callAjax(param,jsObj,url);

}
function getPartyGenderInfo()
{
	var jsObj = {
				electionId:electionId,
				task:"getPartyGenderInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportWithGenderAction.action?"+param;
	callAjax(param,jsObj,url);

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
			  draw(data, {curveType: "function",width: 880, height: 550, pointSize: 4,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
	  else
	  {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {curveType: "function",width: 880, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
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
			  draw(data, {curveType: "function",width: 880, height: 550, pointSize: 4,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	   }
	   else
	  {
         new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {curveType: "function",width: 880, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
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
	  draw(data, {curveType: "function",width: 880, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
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
	  draw(data, {curveType: "function",width: 880, height: 550, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
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
		
	chart.draw(data, {width:880,height: 350,title:ctitle,legend:'right',legendTextStyle:{fontSize:10},
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
		str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); background: none repeat scroll 0pt 0pt rgb(255, 255, 255);"><td>${stateName}  ${electionType} Election Details Overview : </td></tr></table>';

		else if('${electionType}' == 'Parliament')
		str +='	<table cellspacing="0px" cellpadding="0px" width=62%><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144); background: none repeat scroll 0pt 0pt rgb(255, 255, 255);"><td>${electionType} Election Details Overview : </td></tr></table>';
		
		str +='<table class="searchresultsTable" style="width:600px"> ';

		str +='	<tr>';
		str +='		<th style="background-color : #C4DEFF">Year</th>';
		str +='		<th style="background-color : #C4DEFF">Total Seats</th>';
		str +='		<th style="background-color : #C4DEFF">Total Votes</th> ';
		str +='		<th style="background-color : #C4DEFF">Total Polled Votes</th> ';
		str +='		<th style="background-color : #C4DEFF">Voting Percentage </th> ';
		str +='	</tr>';
		
		str +='	<tr>';
		str +='		<th style="background-color:#C4DEFF">'+results.electionBasicVotersData[0].partyName+'</th>';
		str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalSeatsParticipated+'</td>';
		str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalVotesForState+'</td>';
		str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalPolledVotesForState+'</td>';
		str +='		<th style="background-color : #FFFFFF">'+results.electionBasicVotersData[0].totalVotingPercentageForState+'%</td>';
		str +='	</tr>';

		if(flag)
		{
			str +='	<tr>';
			str +='		<th style="background-color:#C4DEFF">'+results.electionBasicVotersData[1].partyName+'</th>';
			str +='     <th style="background-color : #FFFFFF">'+results.electionBasicVotersData[1].totalSeatsParticipated+'</td>';
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
	var partywiseResultsColumnDefs = [
								{key: "party", label: "<%=party%>", sortable:true},		
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
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber}] 
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
	var partywiseResultsWithGenderColumnDefs = [
								{key: "partyName", label: "<%=party%>", sortable:true},		
								{key: "totalParticipated", label: "TP*", sortable:true},	
		              	 	    {key: "totalSeatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "completeVotesPercent", label: "Complete Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
		              	 	 	{key: "PVotesPercent", label: "Participated Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
		              	 	 	{key: "malePerticipated", label: "Male Participants",formatter:"number", sortable:true},
		              	 	 	{key: "maleWon", label: "Male Won",formatter:"number", sortable:true}, 
								{key: "MVotesPercent", label: "Male Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},   	
		              	 	 	{key: "femalePerticipated", label:"Female Participants", formatter:"number",sortable: true},
		              	 	 	{key: "femaleWon", label:"Female Won",formatter:"number", sortable: true},
								{key: "FVotesPercent", label: "Female Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},   	
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
                         		  {key: "FVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15			        
			    }),
			    caption:"Partywise Male And Female Candidate Election Results" 
				};
		
		partywiseResultsWithGenderDataTable = new YAHOO.widget.DataTable(divId, partywiseResultsWithGenderColumnDefs, partywiseResultsWithGenderDataSource,myConfigs);
					
        return { 
            oDS: partywiseResultsWithGenderDataSource, 
            oDT: partywiseResultsWithGenderDataTable 
           
      };	     	
	
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
					completeVotesPercent: allianceResultsArr[i].partiesInAlliance[j].completeVotesPercent
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
	
	var allianceResultsColumnDefs = [
								{key: "partyName", label: "<%=party%>", sortable:true},		
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
                					  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber},"comments"] 
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
	<c:if test="${hasDeatiledAnalysis}">
		var urlStr = "<%=request.getContextPath()%>/candidateDetailsForElectionDetailsReportAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&year=${year}&electionId=${electionId}";
	var browser1 = window.open(urlStr,"browser1","scrollbars=yes,height=600,width=1200,left=200,top=200");
	
	browser1.focus();
	</c:if>
	
	<c:if test="${hasDeatiledAnalysis == false}">
		$("#accessDiv").css("width","474px");
			$("#accessDiv").dialog({ stack: false,
							    height: 'auto',
								width: 500,
								position:'center',								
								modal: true,
								title:'<font color="#ffffff">ALERT</font>',
								overlay: { opacity: 0.5, background: 'black'}
				});
		showAlertMsg();
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
	if(type == "party")
	{
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
				if(selectPartyName == innerObj[i].partyName)
				{
					var obj = {
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

	var allDistrictResultsColumnDefs = [
								{key: "district", label: "Location", sortable:true},		
								{key: "party", label: "<%=party%>", sortable:true},
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
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber} ] 
                					   
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
				
				var allianceDistrictResultsColumnDefs = [
										{key: "district", label: "Location", sortable:true},		
										{key: "party", label: "<%=party%>", sortable:true},
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
		                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber} ] 
		                					   
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

	var currentElectionyear=${year};	
	var selectYearEl = document.getElementById("selectYearDistrictwise");
	var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var yearAlertEl = document.getElementById("yearAlert");
	var browser1;
	var urlStr = "<%=request.getContextPath()%>/districtwiseElectionResultsAnalysysForElectionReportAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&currentElectionyear=${year}&selectedElectionYear="+selectedElectionYear+"";
	if(selectedElectionYear == 'Select Year') 
	{
		yearAlertEl.style.display ='block';
		yearAlertEl.innerHTML = "Please Select A Year!";
		return;
	}
	else {yearAlertEl.style.display ='none';} 
	
	browser1 = window.open(urlStr,"distcomparisioinElectioneport","scrollbars=yes,height=600,width=1000,left=200,top=200");
	browser1.focus();
}
function openPreYearStatewiseAnalysisWindow()
{
	var currentElectionyear=${year};	
	var selectYearEl = document.getElementById("selectYearStateWise");
	var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	var yearAlertSEl = document.getElementById("yearAlertS");
	var browser1;
	var urlStr = "<%=request.getContextPath()%>/statewiseElectionResultsComparisionToolAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&currentElectionyear=${year}&selectedElectionYear="+selectedElectionYear+"";
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
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var selectBoxEl = document.getElementsByName("selectBox");
	var allianceDistResultsEl = document.getElementById("allianceDistResults");
	for (i=0; i< selectBoxEl.length; i++)
	{
		if(selectBoxEl[i].style.display == "block")			
		{
			selectBoxEl[i].style.display = 'none';}
	}
	buildAllDistrictDatatable(innerObj,"districtResults","all","null","null");
	buildAllianceDistrictResultsDataTable(results1,"all", "null");
	getDistrictResultsInteractiveChartVotesPercent(results,null);
	getDistrictResultsInteractiveChartSeatsWon(results,null);

	for (j=0; j<allianceDistResultsEl.childNodes.length; j++){
		allianceDistResultsEl.childNodes[j].style.display='block';								
	}
	
	 
}
function partywiseRadioClickHandler()
{
	var partySelectBoxEl = document.getElementById("partySelectBox");
	var distSelectBoxEl = document.getElementById("distSelectBox");
	var stateSelectBoxEl = document.getElementById("stateSelectBox");
	
	if(partySelectBoxEl.style.display == "none")			
	{
		partySelectBoxEl.style.display = 'block';
		partySelectBoxEl.selectedIndex = '0';
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

function updateDistResultsPartywise(partyName,results)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var allianceDistResultsEl = document.getElementById("allianceDistResults");
	if(partyName != 'Select Party')
		{
		buildAllDistrictDatatable(innerObj,"districtResults","party",partyName,"null");
		//buildAllianceDistrictResultsDataTable(results1,"district", distName);
		getDistrictResultsInteractiveChartVotesPercent(results,partyName);
	    getDistrictResultsInteractiveChartSeatsWon(results,partyName);
 
		if(electionResultsObj.allianceGroupNamesArray != null && electionResultsObj.allianceGroupNamesArray.length != 0)
			{

				for (j=0; j<allianceDistResultsEl.childNodes.length; j++){
					
					if(allianceDistResultsEl.childNodes[j].id != partyName)
					{	
						allianceDistResultsEl.childNodes[j].style.display='none';						
					}else {allianceDistResultsEl.childNodes[j].style.display='block'}
				}				
			}
		
		}
	
	else return;
}
function updateDistResultsDistwise(distName,results,results1)
{
	
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var allianceDistResultsEl = document.getElementById("allianceDistResults");
	if(distName != 'Select District')
	{
		buildAllDistrictDatatable(innerObj,"districtResults","district","null",distName);
		buildAllianceDistrictResultsDataTable(results1,"district", distName);
		getInteractiveChartBySeatsWonForADistrict(results,distName);
		getInteractiveChartByVotesPercentForADistrict(results,distName);
	}
	else return;
	
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

</SCRIPT>
</HEAD>

<BODY>
<center>
<DIV id="sampleDiv"></DIV>

 <div class="main-mbg"><c:if test="${electionType != 'Parliament'}">${stateName} ${electionType} Election Results ${year}</c:if>
<c:if test="${electionType == 'Parliament'}">${electionType} Election Results ${year}</c:if>
 <span style="margin-top:10px;margin-right:18px;float:right">
 <a name="fb_share" type="button_count" 
share_url="www.partyanalyst.com/electionDetailsReportAction.action?electionId=${electionId}&stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&electionTypeId=${electionTypeId}&year=${year}">Share in Facebook</a> 
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript">
</script>
</span>
</div>
 <div class="clear"></div>

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

<DIV id="graphImage" class="yui-skin-sam" style="width:890px;overflow:hidden;margin:auto;"></DIV>
<DIV class="yui-skin-sam" style="width:880px;">
	<TABLE border="0" width="95%" >
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
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>		
	</TABLE>
	<DIV id="note" name="note" style="display:none;"><P><FONT style="font-weight:bold;color:red;" >Note:</FONT>&nbsp;TP and PC% columns are empty for alliance parties in Partywise Results table, to find TP and PC% for alliance parties kindly refer TP and PC% columns of Alliance Details Table.TP and PC% are Not Applicable for Independent Candidates(IND).</P></DIV>
</DIV>

<DIV style="padding:10px;">
	<TABLE width="50%" border="0" cellpadding="0" cellspacing="0">
	 <TR>
			<TD align="left"><IMG src="images/icons/infoicon.png" border="none" /></TD>
			<TD align="center" style="color:#606060;font-size:12px;">Click Here To Access Candidates Results</TD>
			<TD align="left"><DIV><A href="javascript:{}" class="viewChartsForResults1" onclick="showCandidateDetailsWindow(stateName,electionType,year)">View Candidates Results</A></DIV></TD>
		</TR>
		
	</TABLE>
</DIV>


<!--<DIV style="padding:10px;text-align:right;"><A href="javascript:{}" class="viewChartsForResults" onclick="showCandidateDetailsWindow(stateName,electionType,year)">View Candidates Results</A></DIV>
--></DIV>
<DIV id="viewCandidate" class="yui-skin-sam"></DIV>
<DIV class="graphBottom"></DIV>
<div id="accessDiv"></div>
<DIV id="subRegionWiseDetailsDiv">
<c:if test="${electionType != 'Parliament'}"><DIV class="graphTop">District Level Overview</DIV></c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="graphTop">State Level Overview</DIV></c:if>
<DIV id="distwiseGraph">
<DIV id="districtWiseGraph"></DIV>
<DIV id="districtWiseSeatsGraph"></DIV>
<DIV id="detailedGraph" style="text-align:right;padding:15px;"></DIV>
<DIV id="distResultsViewOptionsDiv">
	<TABLE width="100%">	
		<TR>
			<TD style="width:10%;"><INPUT type="radio" name="distResultsOption" id="allDistResultsRadio" value="all" onClick="allDistResultsRadioClickHandler(resultsGlobal,allianceResultsGlobal)" checked="true"/>All</TD>
			<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="partywiseRadio" value="partywise" onClick="partywiseRadioClickHandler()"/>Partywise</TD>
			<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="partySelectBox"  name="selectBox"  onchange="updateDistResultsPartywise(this.options[this.selectedIndex].text,resultsGlobal)" style="display:none;">
				<OPTION id="0" >Select Party</OPTION>
				</SELECT>
			</TD>
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
</DIV>
<DIV class="graphBottom"></DIV>
</DIV>
<DIV class="yui-skin-sam" >
	<TABLE border="0" width="95%" >
		<TR>
			<TD valign="top" align="left">
				<DIV id="genderWiseResultsDataTable"></DIV>
			</TD>
		</TR>		
	</TABLE>	
</DIV>
<div class="clear"></div>
<DIV id="analysisToolsDataDiv">
<DIV class="graphTop">Analysis Tools</DIV>
<DIV id="toolsDiv">
	<TABLE class="toolsTable"><TR>
		<TD class="td">
			<DIV class="toolsDisplay">
				<c:if test="${electionType != 'Parliament'}">
					<h3 align="left">Statewise Analysis</h3>
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
									<OPTION value="years.id">${years.name}</OPTION>
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
						<h3 align="left">District wise Analysis</h3>
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
										<OPTION value="years.id">${years.name}</OPTION>
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
					<h3 align="left">Party Performance</h3>
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
										<OPTION value="years.id">${years.name}</OPTION>								
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
					<h3 align="left">Elections Comparison</h3>
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
											<OPTION value="years.id">${years.name}</OPTION>
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
<DIV class = "yui-skin-sam"><div id="panel" style="left:69px;top:-180px;"></DIV></DIV>
<DIV class = "yui-skin-sam"><div id="detailedResultsPanel"></DIV></DIV>
<DIV class = "yui-skin-sam"><div id="commentsDialogDiv"></DIV></DIV>
</DIV>
<DIV id="task10"></DIV>
<SCRIPT type="text/javascript">
//getElctionsBasicInfo(electionType);
getResultsForAnElection(stateID,electionType,year);
<c:if test="${hasDeatiledAnalysis}">
getPartyGenderInfo();
</c:if>
</SCRIPT>
</center>
</BODY>
</HTML>