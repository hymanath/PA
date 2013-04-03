<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 

<!-- Sam Skin CSS for TabView -->
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.2r1/build/tabview/assets/skins/sam/tabview.css">

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js" ></script>

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>


<!-- JQuery files (End) -->
<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){
%>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
<% }
%>

<title>Census Report</title>

<style type="text/css">

input[type="radio"], input[type="checkbox"] {
    margin:0px;
}
#censusReport_main
{
	margin-left: auto;
    margin-right: auto;
    float: none;
    width: 960px;
}
#censusReportHeading
{
	/* background: #87CEFA;*/
	background:#003399;
    border-radius: 4px 4px 4px 4px;
    color: #FFF;
    float: none;
    font-size: 20px;
    font-weight: bold;
    margin-bottom: -15px;
    margin-left: auto;
    margin-right: auto;
    padding: 3px;
    text-align: center;
    width: 275px;
	margin-top: 30px; 
}
#censusReport_body_heading
{
	font-size: 15px;
	color: #3D362E;

}
#censusReport_body
{
margin-bottom: 20px; 
padding: 15px;
border-radius: 3px 3px 3px 3px;
text-align:left;
}
select
{
	width:150px !important;
	padding:0px;
	
}

#censusPopulationRange
{
	//margin-top:20px;
}
#censusPopulationRange_body_head
	{
		background-color:#EEF4F6;
		color:#444F52;
		font-weight:bold;
		margin:5px;
		padding:2px;
		margin-top:15px;
	    padding-left: 10px;
		padding-top: 5px; 
		padding-bottom: 5px; 
		border-radius: 6px 6px 6px 6px;
		width: 510px;
	}
	.censusPopulationRange_body_table_inner_head th
	{
		//background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
		background: #d9d9d9;
		padding:5px;
		color:#64420F;
	}
	#censusPopulationRange_body_table_inner th
	{
		padding:5px;
	}

	#censusPopulationRange_body_table_inner td
	{
		padding:5px;
	}
	.mainWidgetsBody
	{
		border-bottom:1px solid #E0E0D6;
		border-left:1px solid #E0E0D6;
		border-right:1px solid #E0E0D6;
		padding:10px;
		font-size:11px;
		
	}

	.mainWidgetsDiv
	{
		margin:10px;
	}
	#censusPopulationRange_body_table_outer td
	{
		padding:10px;
		text-align:center;
		font-family:arial;
		color:#444444;
	}

	#censusPopulationRange_body_table_inner th
	{
		padding:5px;

	}
	.censusWidgetHeader
	{
		background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
		height:30px;
	}

	.censusWidgetMainHeader
	{
		//background-image:url("images/icons/districtPage/header_body.png");
		height:36px;
		background:#4263A3;
		font-family:verdana;
	}
	.censusWidgetHeader_span
	{
		position:relative;
		top:8px;
		left:10px;
		/*color:#4B74C6;*/
		color:white;
		font-weight:bold;
	}
.censusPopulationRange_body_table_inner th
{
	//background:#EEF0F2;
	padding:5px;
}
.censusPopulationRange_body_table_inner td
	{
		padding:5px;
	}
	.ajaxImgClass
	{
		position:relative;
		left:10px;
	}
	#censusPopulationRange_body
	{
		background:#FFF;
	}
	#partyResultsPerformance_body
	{
		background:#FFF;
	}
	#censusPopulationRange_body1 {
		background: none repeat scroll 0 0 #FFFFFF;
	}
	#censusPopulationRange_selectParty{ 
	//background-image: url("images/icons/districtPage/header_body.png");
	background:#eaeaea;
    height: 36px;
	text-align:center;
	color:#563E6D;
	font-weight:bold;
	margin-top:10px;
	padding:10px;
	font-size:15px;
	}
</style>

<script type="text/javascript">
	
	var rangeResults = [];
	var constiIds = [];
	var	yearValue = '';
	var partyResultsList = [];
	var partyResultsByRanges;
	var allPartyResultsByRanges;
	var jobjP;
	var resultsP;
	var checkedRadioName;
    google.load("visualization", "1", {packages:["corechart"]});
	$(document).ready(function(){
	document.getElementById("districtList").disabled= true;
		$('#stRadioId').click(function(){
			document.getElementById("districtList").disabled= true;
		});
		$('#diRadioId').click(function(){
			document.getElementById("districtList").disabled= false;
		});
	});
	function showAjaxImage()
	{
		var ajaxImageDivEle = document.getElementById("ajaxImageDiv");
		ajaxImageDivEle.innerHTML = '<center><span><b>Your Request is Processing, Please Wait....</b> </span>		<img src="images/icons/goldAjaxLoad.gif" class="ajaxImgClass"></center>';
		ajaxImageDivEle.style.display = 'block';
	}
	function hideAjaxImage()
	{
		var ajaxImageDivEle = document.getElementById("ajaxImageDiv");
		ajaxImageDivEle.innerHTML = '';
	}

	function showPartySelectAjaxImage()
	{
		var ajaxImageDivEle = document.getElementById("partySelectAjaxImgId");
		ajaxImageDivEle.style.display = 'block';
	}
	
	function hidePartySelectAjaxImage()
	{
		var ajaxImageDivEle = document.getElementById("partySelectAjaxImgId");
		if(ajaxImageDivEle)
		ajaxImageDivEle.style.display = 'none';
	}

	function getCensusDetails()
	{
		changeCensusType();
		$("#censusReporterror_Div").html("");
		var censusElmt = document.getElementById("censusSelect");
		var yearElmts = document.getElementById("yearSelect").value;
		var yearElmt = document.getElementById("yearSelect");
		var errorElmt = document.getElementById("censusReporterror_Div");
		var stateElmt = document.getElementById("stateList");
		var districtElmt = document.getElementById("districtList");
		var stateId = stateElmt.options[stateElmt.selectedIndex].value;
		yearValue = yearElmt.options[yearElmt.selectedIndex].text;
		var censusValue = censusElmt.options[censusElmt.selectedIndex].value;
		var districtId = districtElmt.options[districtElmt.selectedIndex].value;
		var diRadioEle = document.getElementById("diRadioId");
		var stRadioEle = document.getElementById("stRadioId");		
		errorElmt.innerHTML = "";
		
		if(diRadioEle.checked == true){
			if(stateId == 0 || districtId == 0)
			{
				errorElmt.innerHTML = "Please Select State and District";
				return;
			}
		}
		
		if(stateId == 0)
		{
			errorElmt.innerHTML = "Please Select State";
			return;
		}
		
		if(yearElmts == '0')
		{
			errorElmt.innerHTML = "Please Select Year";
			return;
		}
		if(censusValue == '0')
		{
				errorElmt.innerHTML = ' Please Select Census Type ';
				return;
		}
		
		showAjaxImage();
		var stateElmt = document.getElementById("stateList");		
		var reportLevel = '';
		var censusText = censusElmt.options[censusElmt.selectedIndex].text;
		var stateId = stateElmt.options[stateElmt.selectedIndex].value;		
		var stateName = stateElmt.options[stateElmt.selectedIndex].text;
		var districtName = districtElmt.options[districtElmt.selectedIndex].text;
		var stRadioEle = document.getElementById("stRadioId");
		var partyResultsPerformance_mainEl =  document.getElementById("partyResultsPerformance_main");
		partyResultsPerformance_mainEl.style.display = 'none';
		if(stRadioEle.checked == true)
			reportLevel = stRadioEle.value;
		else if(diRadioEle.checked == true)
			reportLevel = diRadioEle.value;			
		
		var jsObj=
		{
				stateId		: stateId,
				districtId	: districtId,
				stateName 	: stateName,
				districtName :districtName,
				censusText	: censusText,
				censusValue	: censusValue,
				yearValue	: yearValue,
				reportLevel : reportLevel,
				task		: "getCensusDetails"					
		}; 

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCensusInfoInAStateAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}
	
	function getPartyResultsByRanges(value)
	{
		var resultByRangesVar = partyResultsByRanges;
		var resultByRanges = new Array();
		var partyEle = document.getElementById("partySelectElmt");
		var partyName = partyEle.options[partyEle.selectedIndex].text;
		var ctitle = '';

		for(var i=0; i<resultByRangesVar.length; i++)
		{
			if(resultByRangesVar[i].avgPercent != null && resultByRangesVar[i].avgPercent > 0)
				resultByRanges.push(resultByRangesVar[i])
		}

		var data = new google.visualization.DataTable();
		data.addRows(resultByRanges.length);	
		
		if(value == "seats")
		{	
			ctitle = partyName+' Party Result Based On Seats Won Percentage';
			data.addColumn('string', 'Range');
			data.addColumn('number', 'Seats Won');
			data.addColumn('number', 'Total Seats');
				
			for(var i=0; i<resultByRanges.length; i++)
			{
			
				data.setValue(i, 0, resultByRanges[i].range);
				/*data.setValue(i, 1, parseInt((resultByRanges[i].seatsWon)*(100)/resultByRanges[i].count));*/
				data.setValue(i, 1, resultByRanges[i].seatsWon);
				data.setValue(i, 2, resultByRanges[i].count);
			}			
		}
		else if(value == "percentage")
		{
			ctitle = partyName+' Party Result Based On Votes Percentage';
			data.addColumn('string', 'Range');
			data.addColumn('number', 'Percentage');
			data.addColumn('number', 'Avg Polling Percent');
			for(var i=0; i<resultByRanges.length; i++)
			{
				var totalVotingPercent=parseFloat(resultByRanges[i].votingPercent);
				//console.log(resultByRanges[i]);
				data.setValue(i, 0, resultByRanges[i].range);
				data.setValue(i, 1, resultByRanges[i].avgPercent);
				data.setValue(i, 2, totalVotingPercent);
			}
		}
		else if(value == "cpAvg")
		{
			
			ctitle = partyName+' Party Result Based On CP-AVG Votes Percentage';
			data.addColumn('string', 'Range');
			data.addColumn('number', 'CP*-Avg');
			data.addColumn('number', 'Avg Polling Percentage');
		
			for(var i=0; i<resultByRanges.length; i++)
			{
				var totalVotingPercentcp=parseFloat(resultByRanges[i].votingPercent);
				data.setValue(i, 0, resultByRanges[i].range);
				data.setValue(i, 1, resultByRanges[i].PConstavgPercent);
				data.setValue(i, 2, totalVotingPercentcp);
			}
		}

		var chart = new google.visualization.LineChart(document.getElementById('onePartyCensusResults_body'));
		chart.draw(data, {width: 450, height: 350, pointSize: 4,title:ctitle,titleTextStyle:{color:'#DC1CF2'}});
	}	
	
	function getAllPartyResultsByRanges(value)
	{
		var resultsVar = allPartyResultsByRanges;
		var results = new Array();
		var ctitle = '';
		
		for(var i=0;i<resultsVar.length; i++)
		{
		//	console.log(resultsVar);
			var flag = null;
			for(var j=0;j<resultsVar[i].partiesResults.length;j++)
				if(resultsVar[i].partiesResults[j].votesPercent != null && resultsVar[i].partiesResults[j].votesPercent > 0)
					flag = "true";
			if(flag == "true")
				results.push(resultsVar[i]);
		}
		
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Range');
		for(var i=0; i<results[0].partiesResults.length; i++)
			data.addColumn('number', results[0].partiesResults[i].partyName);
		
		
		
		var partiesArray = new Array();
		for(var i=0; i<results[0].partiesResults.length; i++)
			partiesArray.push(results[0].partiesResults[i].partyName);

		var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	
		if(value == "seats")
		{	
			data.addColumn('number', 'Total Seats');	
			data.addRows(results.length+1);
			ctitle = 'All Parties Results based on Seats Won Percentage';

			for(var i=0; i<results.length; i++)
			{			
				data.setValue(i, 0, results[i].range);
				for(var j=0; j<results[i].partiesResults.length; j++)
					/*data.setValue(i, j+1, parseInt((results[i].partiesResults[j].totalSeatsWon)*(100)/(results[i].count)));*/
					data.setValue(i, j+1,results[i].partiesResults[j].totalSeatsWon);
					data.setValue(i,results[i].partiesResults.length+1,results[i].count);
			}
					
		}
		else if(value == "percentage")
		{	
			data.addRows(results.length);
			ctitle = 'All Parties Results based on Votes Percentage';
			for(var i=0; i<results.length; i++)
			{			
					data.setValue(i, 0, results[i].range);
					
					for(var j=0; j<results[i].partiesResults.length; j++)
						data.setValue(i, j+1,results[i].partiesResults[j].votesPercent);		
				}
		}
		else if(value == "cpAvg")
		{	
			data.addRows(results.length);
			ctitle = 'All Parties Results based on Votes Percentage in CP* ';
			for(var i=0; i<results.length; i++)
			{			
				data.setValue(i, 0, results[i].range);

				for(var j=0; j<results[i].partiesResults.length; j++)
					data.setValue(i, j+1, parseFloat(results[i].partiesResults[j].PConstavgPercentage));	
			}			
		}

        var chart = new google.visualization.LineChart(document.getElementById('allPartyCensusResults_body'));
        chart.draw(data, {width: 450, height: 350,colors:staticColors,pointSize: 4, title:ctitle, titleTextStyle:{color:'#DC1CF2'}});
	}
	function buildAllPartyCensusResults(jsObj,results)
	{
		if(results.length == 0)
			return;
		allPartyResultsByRanges = results;
		
		var graphHeadElmt = document.getElementById("allPartyCensusResults_head");
		var ctitle = 'All Parties Results based on Votes Percentage';

		var gStr = '';
		gStr += '<table class="graphHeadTable" cellpadding="3">';
		gStr += '<tr>';
		gStr += '<th>Constituencies Count</th>';
		gStr += '<th><input type="radio" name="graphRadioName1" checked="checked" value="percentage" onclick="getAllPartyResultsByRanges(this.value)" style="margin-right: 3px;">By Percentage</input></th>';
		gStr += '<th><input type="radio" name="graphRadioName1" value="seats" onclick="getAllPartyResultsByRanges(this.value)" style="margin-right: 3px;">By Seats Won</input></th>';
		gStr += '<th><input type="radio" name="graphRadioName1" value="cpAvg" onclick="getAllPartyResultsByRanges(this.value)" style="margin-right: 3px;">By CP* Avg</input></th>';
		gStr += '</tr>';
		gStr += '</table>';
		graphHeadElmt.innerHTML = gStr;
		
		var partiesArray = new Array();
		for(var i=0; i<results[0].partiesResults.length; i++)
			partiesArray.push(results[0].partiesResults[i].partyName);

		var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Range');
		for(var i=0; i<results[0].partiesResults.length; i++)
	        data.addColumn('number', results[0].partiesResults[i].partyName);
		
		var resultsVar = new Array();
		for(var i=0; i<results.length; i++)
		{
			var flag = null;
			for(var j=0;j<results[i].partiesResults.length;j++)
				if(results[i].partiesResults[j].votesPercent != null && results[i].partiesResults[j].votesPercent > 0)
					flag = "true";
			if(flag == "true")
				resultsVar.push(results[i]);
		}
		
		data.addRows(resultsVar.length);

		for(var i=0; i<resultsVar.length; i++)
		{			
			data.setValue(i, 0, resultsVar[i].range);
			
			for(var j=0; j<resultsVar[i].partiesResults.length; j++)
				data.setValue(i, j+1,resultsVar[i].partiesResults[j].votesPercent);		
		}

        var chart = new google.visualization.LineChart(document.getElementById('allPartyCensusResults_body'));
        chart.draw(data, {width: 450, height: 350,pointSize: 4, title:ctitle ,colors:staticColors,slantedText:true,slantedTextAngle:35,titleTextStyle:{color:'#DC1CF2'}});
	}

	function buildPartyResultsByRanges(jsObj,resultByRanges)
	{
		partyResultsByRanges = resultByRanges;
		var rangeElmt = document.getElementById("censusPopulationRange");
		var elmt = document.getElementById("censusPopulationRangeData");
		var spanElmt = document.getElementById("censusPopulationRange_head_span");
		var gElmt = document.getElementById("censusPopulationRangeGraph_body");
		var partyEle = document.getElementById("partySelectElmt");
		var partyName = partyEle.options[partyEle.selectedIndex].text;
		if(rangeElmt && rangeElmt.style.display == "none")
			rangeElmt.style.display = "block";
		
		var hStr = '';

		if(jsObj.districtId==0){
		hStr += jsObj.partyName+' Party Performance in '+jsObj.stateName+' in the year '+jsObj.yearValue+' Vs '+jsObj.censusText+' % range In Total Population ';
		if(spanElmt)
			spanElmt.innerHTML = hStr;

		}
		else{
				hStr += jsObj.partyName+' Party Performance in '+jsObj.districtName+'      District in the year '+jsObj.yearValue+' Vs '+jsObj.censusText+' % range In Total Population ';
		if(spanElmt)
			spanElmt.innerHTML = hStr;

		}
		gElmt.innerHTML = '';
		
		var str = '';
		str += '<div id="onePartyCensusResults_main" style="text-align:left;">';
		str += '<div id="onePartyCensusResults_head"></div>';
		str += '<div id="onePartyCensusResults_body"></div>';
		str += '</div>';

		var pStr = '';
		pStr += '<div id="allPartyCensusResults_main" style="text-align:left;">';
		pStr += '<div id="allPartyCensusResults_head"></div>';
		pStr += '<div id="allPartyCensusResults_body"></div>';
		pStr += '</div>';

		var myTabs = new YAHOO.widget.TabView();
		myTabs.addTab( new YAHOO.widget.Tab({
		label: "Party",
		active:true,
		content: str
		}));

		myTabs.addTab( new YAHOO.widget.Tab({
			label: "All Parties",
			content: pStr
		}));
		
		myTabs.appendTo("censusPopulationRangeGraph_body");
		

		var electionResultsMainDiv = document.getElementById("partyResultsPerformance_main");
		if(electionResultsMainDiv.style.display == "block")
			electionResultsMainDiv.style.display = "none";
		
		var radioElmt = document.getElementById("onePartyCensusResults_head");
		var rStr = '';
		rStr += '<table class="graphHeadTable" cellpadding="3">';
		rStr += '<tr>';
		rStr += '<th>Constituencies Count</th>';
		rStr += '<th><input type="radio" name="graphRadioName" checked="checked" value="percentage" onclick="getPartyResultsByRanges(this.value)" style="margin-right: 3px;">By Percentage</input></th>';
		rStr += '<th><input type="radio" name="graphRadioName" value="seats" onclick="getPartyResultsByRanges(this.value)" style="margin-right: 3px;">By Seats Won</input></th>';	
		rStr += '<th><input type="radio" name="graphRadioName" value="cpAvg" onclick="getPartyResultsByRanges(this.value)" style="margin-right: 3px;">By CP* - Avg </input></th>';	
		rStr += '</tr>';
		rStr += '</table>';
		radioElmt.innerHTML = rStr;

		var str = '';
		//str += '<div><a href="javascript:{}" onclick="callAjaxForPartiesSelectBox(\'censusPopulationRange_body\')">View Party wise Results By Census Percentage Range</a></div>';
		str += '<div id="censusPopulationRange_body">';
		str += '<table id="censusPopulationRange_body_table_outer" border="0">';
		str += '<tr>';
		str += '<td>';
		str += '<table class="censusPopulationRange_body_table_inner_head" border="0">';								
		str += '<tr>';				
		str += '<th>'+jsObj.censusText+' % Range</th>';
		str += '<th>Total Seats</th>';
		str += '<th>CP*</th>';
		str += '<th>Seats Won*</th>';
		str += '<th>Avg %</th>';
		str += '<th>CP*-Avg%</th>';
		str += '<th>Avg Polling %</th>';
		str += '</tr>';	
		//str += '</table>';		
		for(var i=0; i<resultByRanges.length; i++)
		{
			str += '<tr>';
			str += '<td class="newTh" align="center" style="font-weight:bold;">'+resultByRanges[i].range+'</th>';
			if(resultByRanges[i].count != 0)
				str += '<td align="center"><a href="javascript:{}" onclick="viewPerformanceGraph('+i+',\''+jsObj.censusText+'\','+jsObj.censusValue+',\''+jsObj.yearValue+'\')" title="Click Here To View Election Results of All Parties in '+resultByRanges[i].count+' Constituencies Having '+jsObj.censusText+' Between '+resultByRanges[i].range+'">'+resultByRanges[i].count+'</a></td>';
			else
				str += '<td align="center">'+resultByRanges[i].count+'</td>';

			if(resultByRanges[i].seatsParticipated != null)
				str += '<td align="center">'+resultByRanges[i].seatsParticipated+'</td>';
			else 
				str += '<td align="center">--</td>';

			if(resultByRanges[i].seatsWon != null)
				str += '<td align="center">'+resultByRanges[i].seatsWon+'</td>';
			else 
				str += '<td align="center">--</td>';

			if(resultByRanges[i].avgPercent != null)
				str += '<td align="center">'+resultByRanges[i].avgPercent+'</td>';
			else 
				str += '<td align="center">--</td>';
			
			if(resultByRanges[i].PConstavgPercent != null)
				str += '<td align="center">'+resultByRanges[i].PConstavgPercent+'</td>';
			else 
				str += '<td align="center">--</td>';

			if(resultByRanges[i].votingPercent != null)
				str += '<td align="center">'+resultByRanges[i].votingPercent+'</td>';
			else
				str += '<td align="center">--</td>';
		
			str += '</tr>';						
		}
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '<div style="font-weight:bold;color:#906316;text-align:right;">* CP - Constituencies Participated </div>';
		str += '</div>';

		elmt.innerHTML = str;
		
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Range');
        data.addColumn('number', 'Percentage');
		data.addColumn('number', 'Avg Polling Percentage');
		var ctitle = partyName+' Party Result Based On Votes Percentage';
		
		var resultByRangesVar = new Array();
		for(var i=0; i<resultByRanges.length; i++)
		{
			if(resultByRanges[i].avgPercent != null && resultByRanges[i].avgPercent > 0)
				resultByRangesVar.push(resultByRanges[i]);
		}
        
		data.addRows(resultByRangesVar.length);

		for(var i=0; i<resultByRangesVar.length; i++)
		{			
				var totalVotingPercentcp=parseFloat(resultByRangesVar[i].votingPercent);
				
				data.setValue(i, 0, resultByRangesVar[i].range);
				data.setValue(i, 1, resultByRangesVar[i].avgPercent);
				data.setValue(i, 2, totalVotingPercentcp);
		}

        var chart = new google.visualization.LineChart(document.getElementById('onePartyCensusResults_body'));
        chart.draw(data, {width: 450, height: 350,pointSize: 4, title:ctitle,titleTextStyle:{color:'#DC1CF2'}});
		//getAllPartiesCensusResults();	
		buildAllPartyCensusResults(jobjP,resultsP);
	}

	function getLatestElectionYears()
	{
		$('#censusSelect').val(0);
		var stateLitsEle = document.getElementById("stateList");
		var stateId = stateLitsEle.options[stateLitsEle.selectedIndex].value;
		$("#censusReporterror_Div").html('');
		if(stateId == 0){
			$("#censusReporterror_Div").html('Please select State');
			return;
		}
		
		var jsObj=
		{
			stateId : stateId,
			selectElementId : "yearSelect",
			task	: "getLatestElectionYears"
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getLatestElectionYearsAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}

	function buildPopulationRange(jsObj,results)
	{
		
		$('#censusPopulationRange1').css('display','none');
		$('#censusPopulationRange').css('display','none');
		var rangeElmt = document.getElementById("censusPopulationRange1");
		var selectPartyElmt = document.getElementById("censusPopulationRange_selectParty");
		var elmt = document.getElementById("censusPopulationRangeData1");
		var spanElmt = document.getElementById("censusPopulationRange_head_span1");
		var censusPopulationRange_footerEl = document.getElementById("censusPopulationRange_footer1");
		var censusPopulationHead = document.getElementById("censusPopulationRange_body_head1");		
		var graphHeadElmt = document.getElementById("censusPopulationRangeGraph_head1");
		graphHeadElmt.innerHTML = '';

		var cpStr = '';

		censusPopulationHead.innerHTML = cpStr;

		if(rangeElmt && rangeElmt.style.display == "none")
			rangeElmt.style.display = "block";
		if(selectPartyElmt && selectPartyElmt.style.display == "none")
			selectPartyElmt.style.display = "block";
			
		rangeResults = results;
		if(!elmt)
			return;
		
		if(jsObj.districtId==0){
			var hStr = '';
			hStr += jsObj.stateName+' Assembly Constituencies Results in '+jsObj.yearValue+' Vs '+jsObj.censusText+' % range In Total Population';;
			if(spanElmt)
				spanElmt.innerHTML = hStr;
		}
		else{
				var hStr = '';
			hStr += jsObj.districtName+' District Assembly Constituencies Results in '+jsObj.yearValue+' Vs '+jsObj.censusText+' % range In Total Population';;
			if(spanElmt)
				spanElmt.innerHTML = hStr;
		}
		if(results[0].totalConstituencies == 0)
		{
			$("#censusPopulationRange_body_head1").html('<div style="margin-top: 10px; text-align: center; font-size: 14px; font-family: verdana; margin-bottom: 10px;">No Data Available</div>');
			$("#censusPopulationRange_selectParty").css("display","none");
			$("#censusPopulationRange_body_body1").css("display","none");
			$("#censusPopulationRange_footer1").css("display","none");
		}
		else
		{
			$("#censusPopulationRange_body_head1").html('');
			$("#censusPopulationRange_selectParty").css("display","block");
			$("#censusPopulationRange_body_body1").css("display","block");
			$("#censusPopulationRange_footer1").css("display","block");
		var str = '';
		//str += '<div><a href="javascript:{}" onclick="callAjaxForPartiesSelectBox(\'censusPopulationRange_body\')">View Party wise Results By Census Percentage Range</a></div>';
		str += '<div id="censusPopulationRange_body">';
		str += '<table id="censusPopulationRange_body_table_outer" border="0">';
		str += '<tr>';
		str += '<td>';
		str += '<table class="censusPopulationRange_body_table_inner_head" border="0">';
		str += '<tr>';		
		str += '<th width="72px">'+jsObj.censusText+'<br> % Range</th>';
		str += '<th width="40px">Total Seats</th>';
		str += '<th width="60px">AVG %</th>';
		str += '</tr>';	
		str += '</table>';
		
		for(var i=0; i<results.length; i++)
		{
			if(i==5)
			{
				str += '</td>';				
				str += '<td>';
				str += '<table class="censusPopulationRange_body_table_inner_head" border="0">';
				str += '<tr>';				
				str += '<th width="72px">'+jsObj.censusText+'<br> % Range</th>';
				str += '<th width="40px">Total Seats</th>';
				str += '<th width="60px">AVG %</th>';
				str += '</tr>';	
				str += '</table>';
			}
			str += '<table class="censusPopulationRange_body_table_inner" border="0">';
			str += '<tr>';
			str += '<th width="80px">'+results[i].range+'</th>';
			if(results[i].count != 0)
				str += '<td width="50px"><a href="javascript:{}" onclick="viewPerformanceGraph('+i+',\''+jsObj.censusText+'\','+jsObj.censusValue+',\''+jsObj.yearValue+'\');scrollToPartyResultsPerformanceDiv()" title="Click Here To View Election Results of All Parties in '+results[i].count+' Constituencies Having '+jsObj.censusText+' Between '+results[i].range+'">'+results[i].count+'</a></td>';
			else
				str += '<td width="50px">'+results[i].count+'</td>';

			if(results[i].votingPercent != null)
				str += '<td width="60px">'+results[i].votingPercent+'</td>';
			else
				str += '<td width="60px"> -- </td>';
			str += '</tr>';	
			str += '</table>';			
		}
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		elmt.innerHTML = str;
		
		}
		var countStr = '';
		countStr += '<b>Total Number of Constituencies Considered: </B>';
		countStr += ''+results[0].totalConstituencies+'';
		censusPopulationRange_footerEl.innerHTML = countStr;		
		
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Range');
        data.addColumn('number', 'Constituencies');
        data.addRows(results.length);

		for(var i=0; i<results.length; i++)
		{
			data.setValue(i, 0, results[i].range);
	        data.setValue(i, 1, parseInt(results[i].count));
		}

        var chart = new google.visualization.PieChart(document.getElementById('censusPopulationRangeGraph_body1'));
        chart.draw(data, {width: 360, height: 250, title: 'Constituencies Count Based On Range'});
		
		callAjaxForPartiesSelectBox('censusPopulationRange_selectParty');

	}
	function scrollToPartyResultsPerformanceDiv(){
		$('html,body').animate({scrollTop: $("#partyResultsPerformance_body").offset().top}, 2000);
	}
	function callAjaxForPartiesSelectBox(divId){
		var stateEle = document.getElementById("stateList");
		var stateId = stateEle.options[stateEle.selectedIndex].value;

		var jsObj=
		{
				stateId		: stateId,
				task		: "getPartiesByState",
				divId		: divId		
		}; 

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartiesPerformanceInCensusReportAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}

	function buildPartiesSelectBox(divId, partiesList){
		var divEle = document.getElementById(divId);
		var str = '';
		str += '<div>';
		str += '<table>';
		str += '<tr>';
		str += '<td>Select Party To View Results By Census Percentage Range:</td>';
		str += '<td><select id="partySelectElmt" style="margin-left:10px;" onchange="getAjaxResultForPartyResultsByRanges(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
		str += '<option value="0">Select Party</option>';
		for(var i=0; i<partiesList.length; i++)
			str += '<option value="'+partiesList[i].id+'">'+partiesList[i].name+'</option>';
		str += '</select></td>';
		str += '<td><img id="partySelectAjaxImgId" class="ajaxImgClass" style="display:none;" src="images/icons/search.gif"></img></td>';
		str += '</tr>';
		str += '</table>';
		str += '<div>';
		str += '</div>';
		str += '</div>';
		divEle.innerHTML = str;
		
		var elmt = document.getElementById("partySelectElmt");
		getAllPartiesCensusResults();
		//getAjaxResultForPartyResultsByRanges(elmt.options[0].value);
	}

	function getAllPartiesCensusResults()
	{
		var censusElmt = document.getElementById("censusSelect");
		var yearElmt = document.getElementById("yearSelect");
		var stateElmt = document.getElementById("stateList");
		var districtElmt = document.getElementById("districtList");
		var reportLevel = '';
		var censusValue = censusElmt.options[censusElmt.selectedIndex].value;
		var censusText = censusElmt.options[censusElmt.selectedIndex].text;
		yearValue = yearElmt.options[yearElmt.selectedIndex].text;
		var stateId = stateElmt.options[stateElmt.selectedIndex].value;
		var districtId = districtElmt.options[districtElmt.selectedIndex].value;
		var stRadioEle = document.getElementById("stRadioId");
		var diRadioEle = document.getElementById("diRadioId");
		
		if(yearValue == 'Select Year')
			return;

		if(stRadioEle.checked == true)
			reportLevel = stRadioEle.value;
		else if(diRadioEle.checked == true)
			reportLevel = diRadioEle.value;			

		var jsObj=
		{
				stateId		: stateId,
				districtId	: districtId,
				censusText	: censusText,
				censusValue	: censusValue,
				yearValue	: yearValue,
				reportLevel : reportLevel,
				task		: "allPartyCensusResults"					
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCensusInfoForAllPartiesAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}

	function getAjaxResultForPartyResultsByRanges(partyId,partyName){
		if(partyId==0){
			alert("Please Select Party");
			return;
		}
		var censusElmt = document.getElementById("censusSelect");
		var yearElmt = document.getElementById("yearSelect");
		var stateElmt = document.getElementById("stateList");
		var districtElmt = document.getElementById("districtList");
		var reportLevel = '';
		var censusValue = censusElmt.options[censusElmt.selectedIndex].value;
		var censusText = censusElmt.options[censusElmt.selectedIndex].text;
		yearValue = yearElmt.options[yearElmt.selectedIndex].text;
		var stateId = stateElmt.options[stateElmt.selectedIndex].value;
		var stateName = stateElmt.options[stateElmt.selectedIndex].text; 
		var districtId = districtElmt.options[districtElmt.selectedIndex].value;
		var districtName = districtElmt.options[districtElmt.selectedIndex].text;
		var stRadioEle = document.getElementById("stRadioId");
		var diRadioEle = document.getElementById("diRadioId");
		
		if(yearValue == 'Select Year')
			return;

		showPartySelectAjaxImage();

		if(stRadioEle.checked == true)
			reportLevel = stRadioEle.value;
		else if(diRadioEle.checked == true)
			reportLevel = diRadioEle.value;			

		var jsObj=
		{
				partyId		: partyId,
				partyName   : partyName,
				stateId		: stateId,
				stateName	: stateName,
				districtId	: districtId,
				districtName : districtName,
				censusText	: censusText,
				censusValue	: censusValue,
				yearValue	: yearValue,
				reportLevel : reportLevel,
				task		: "censusByParty"					
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCensusInfoInAStateAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}

		
	function viewPerformanceGraph(index,censusType,censusTypeId,year)
	{	
		myCount = 0;	
		var elmt = document.getElementById("partyResultsPerformance_main");
		if(elmt && elmt.style.display == "none")
			elmt.style.display = "block";
		

		var mainHeaderSpanElmt = document.getElementById("censusWidgetMainHeader_span");
		if(mainHeaderSpanElmt)
			mainHeaderSpanElmt.innerHTML = '';
		
		var partyWiseElmt = document.getElementById("partyResultsDataDiv");
		partyWiseElmt.innerHTML = '<center><span><b>Your Request is Processing, Please Wait....</b> </span>		<img src="images/icons/goldAjaxLoad.gif" class="ajaxImgClass"></center>';
		var partyResultsGraphElmt = document.getElementById("partyResultsGraphDiv");
		partyResultsGraphElmt.innerHTML = '';


		var constituencyWiseElmt = document.getElementById("performanceGraphDiv_body");
		constituencyWiseElmt.innerHTML = '<center><span><b>Your Request is Processing, Please Wait....</b> </span><img src="images/icons/goldAjaxLoad.gif" class="ajaxImgClass"></center>';

		var idsList = rangeResults[index].locationIds;
		var range = rangeResults[index].range;

		var jsObj=
		{
				idsList:idsList,
				yearValue:yearValue,
				display:true,
				range:range,
				censusType:censusType,
				censusTypeId: censusTypeId,
				year:year,	
				task:"getPartiesPerformanceInCensusReport"					
		}; 

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartiesPerformanceInCensusReportAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}
	
	function showPartyResultsByFilter(censusType,censusTypeId,range)
	{
		var elmts = document.getElementsByName("partyResultsRadio");
		var districtSpanElmt = document.getElementById("district_selectElmt");
		var partiesSpanElmt = document.getElementById("parties_selectElmt");
		var yearElmt = document.getElementById("yearSelect");

		var checkedValue;
		var districtIds = [];
		var partyIds = [];
		var isAll = true;
		var yearValue = yearElmt.options[yearElmt.selectedIndex].text; 
		var isAll = false;
		
		if(!elmts || yearValue == 'Select Year')
			return;

		for(var i=0; i<elmts.length; i++)
		{
			if(elmts[i].checked == true)
				checkedValue = elmts[i].value;
		}
		
		if(checkedValue == "all")
		{			
			isAll = true;
		}
		
		else if(checkedValue == "district")
		{
			
			for(var i = 0; i < districtSpanElmt.options.length; i++)
			{		
				if(districtSpanElmt.options[i].selected == true)			
				{		
					districtIds.push(districtSpanElmt.options[i].value);
				}
					
			}	
		}
		
		else if(checkedValue == "parties")
		{
			for(var i = 0; i < partiesSpanElmt.options.length; i++)
			{		
				if(partiesSpanElmt.options[i].selected == true)			
				{		
					partyIds.push(partiesSpanElmt.options[i].value);
				}
					
			}	
			$('#partyCheckId').attr('checked',true);
		}

		var jsObj = {
						censusType:censusType,
						censusTypeId:censusTypeId,
						range:range,
						idsList:constiIds,
						checkedValue:checkedValue,
						districtIds:districtIds,
						partyIds:partyIds,
						display:false,
						isAll:isAll,
						yearValue:yearValue,
						task:"showPartyResultsByFilterView"
					};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartiesPerformanceInCensusReportByDistrictOrPartiesAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
		checkedRadioName = checkedValue;
		
	}
	// myCount used to stop refresh districts everyTime  Updated By Srishailam
	var myCount =0;
	function showSelectOptions(value)
	{
	//alert(value);
		var districtSpanElmt = document.getElementById("district_select");
		var partiesSpanElmt = document.getElementById("parties_select");

		if(value == "all")
		{
		//alert("1");
			districtSpanElmt.style.display = 'none';
			partiesSpanElmt.style.display = 'none';
		}
		else if(value == "district")
		{
		//alert("2");
			myCount = myCount+1;
			districtSpanElmt.style.display = 'block';
			partiesSpanElmt.style.display = 'none';
		}
		else if(value == "parties")
		{
		//alert("3");
			districtSpanElmt.style.display = 'none';
			partiesSpanElmt.style.display = 'block';
		}
	}

	function buildPerformanceTable(jsObj,results)
	{
	//alert("buildPerformanceTable");
	//alert(checkedRadioName)
		constiIds = jsObj.idsList;
		partyResultsList = results.partyResultsList;

		var optionsElmt = document.getElementById("resultsOptionsDiv_main");
		var partyWiseElmt = document.getElementById("partyResultsDataDiv");
		var constituencyWiseElmt = document.getElementById("performanceGraphDiv_body");
		var graphDivElmt = document.getElementById("partyResultsGraphDiv");

		var mainHeaderSpanElmt = document.getElementById("censusWidgetMainHeader_span");
		if(mainHeaderSpanElmt)
			mainHeaderSpanElmt.innerHTML = 'Election Results Of All Parties In Constituencies Having '+jsObj.censusType+' Between '+jsObj.range+' Range';
		
		if(!optionsElmt || !partyWiseElmt || !constituencyWiseElmt || !graphDivElmt)
			return;
		
		var optionStr = '';
		if(myCount == '0')
			{
			optionStr += '<table width="100%">';
			optionStr += '<tr>';
			optionStr += '<th valign="top">Select options to change view </th>';
			optionStr += '<td ><input name="partyResultsRadio" onclick="showSelectOptions(this.value)" type="radio"  value="all" checked="checked" id="allCheckId"/> All </td>';
			optionStr += '<td><input name="partyResultsRadio" type="radio" value="district" id="districtCheckId" onclick="showSelectOptions(this.value)"/> District </td>';	
			optionStr += '<td valign="top"><div id="district_select" style="display:none;"><select id="district_selectElmt" multiple="multiple" size="3">';
			for(var i=0; i<results.districts.length; i++)
				optionStr += '<option value="'+results.districts[i].id+'">'+results.districts[i].name+'</option>';
			optionStr += '</select></div></td>';		
			optionStr += '<td ><input name="partyResultsRadio" type="radio" value="parties" id="partyCheckId" onclick="showSelectOptions(this.value)"/> Parties </td>';
			optionStr += '<td valign="top"><div id="parties_select" style="display:none;"><select id="parties_selectElmt" multiple="multiple" size="3">';
			for(var i=0; i<results.parties.length; i++)
				optionStr += '<option value="'+results.parties[i].id+'">'+results.parties[i].name+'</option>';
			optionStr += '</select></div></td>';
			optionStr += '<td valign="top"><input type="button" value="View" onclick="showPartyResultsByFilter(\''+jsObj.censusType+'\',\''+jsObj.censusTypeId+'\',\''+jsObj.range+'\')" style="color:#FFF; font-weight: bold; padding-left: 10px; padding-right: 10px; border-radius: 5px 5px 5px 5px; background:#4B74C6"></td>';
			optionStr += '</tr>';
			optionStr += '</table>';
			optionStr += '<div id="partyResultsTable_errorDiv" style="color:red;font-size:11px;"></div>';
			optionsElmt.innerHTML = optionStr;
		}
		var str = '';
		str += '<table border="0" id="partyResultsTable">';
         for(var j in results.constituenciesResults)
        {
        	str += '<tr>';
        	str += '<td>'+results.constituenciesResults[j].constituencyName+'</td>';
        	str += '<td>'+results.constituenciesResults[j].districtName+'</td>';
        	str += '<td>'+results.constituenciesResults[j].censusReportPercent+'</td>';
			str += '<td>'+results.constituenciesResults[j].votingPercentage+'</td>';
        	for(var k in results.constituenciesResults[j].partyResultsVO)
            	if(results.constituenciesResults[j].partyResultsVO[k].percentage != null)
        			str += '<td>'+results.constituenciesResults[j].partyResultsVO[k].votesPercent+'</td>';
        		else
        			str += '<td></td>';
        	str += '</tr>';
        }
        str += '</table>';
		constituencyWiseElmt.innerHTML = str;
		
	
		var tableDiv = document.getElementById("partyResultsTable");    	
    	var divEl = document.getElementById("partyResultsTableDiv");
    	
    		
   		 var myColumnDefs = new Array();
   		 var myFields = new Array();

   		var constHead = {
	 			key:"Constituency",
	 			lable: "Constituency",
	 			sortable:true
		   }
   		var constValue = {key:"Constituency"}

   		var distHead = {
	 			key:"District",
	 			lable: "District",
	 			sortable:true
		   }
   		var distValue = {key:"District"}

   		var censusTypeHead = {
   		   		key: "censusReportPercent",
   		   		label:jsObj.censusType + " %",
   		   		sortable:true
   		   		}
   		var censusTypeValue = {key:"censusReportPercent"} 

		var votingPerHead = {
	 			key:"VotingPer",
	 			label:"Voting %",
	 			sortable:true
		   }
   		var votingPerValue = {key:"VotingPer"}
   				 
		myColumnDefs.push(constHead);
		myFields.push(constValue);
		myColumnDefs.push(distHead);
		myFields.push(distValue);
		myColumnDefs.push(censusTypeHead);
		myFields.push(censusTypeValue);
		myColumnDefs.push(votingPerHead);
		myFields.push(votingPerValue);
		
    		 for(var l in results.allPartiesList){
    			var obj1 = {
    						key:results.allPartiesList[l],
    						label:results.allPartiesList[l],
    						sortable:true
						}
    			var obj2 = {
 						key:results.allPartiesList[l]
 					}
    			myColumnDefs.push(obj1);
    			myFields.push(obj2);    			
    		 }			
    		 	
    		 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
    					.get("partyResultsTable")); 
    		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
    		 myDataSource.responseSchema = { 
    								            fields:myFields     
    								        };
		    
			var myConfigs = { 					
					paginator : new YAHOO.widget.Paginator({ 
					rowsPerPage    : 10 
					}) 
					};
    	               
    		var villageDataTable = new YAHOO.widget.DataTable("performanceGraphDiv_body",myColumnDefs, myDataSource, myConfigs);
    		
    		
    		var localLeadersColumnDefs = [ 

    								{key:"partyName", label:"Party", sortable:true},         
    								{key:"seatsParticipated", label: "Seats Participated", sortable:true},
    			    	            {key:"totalSeatsWon", label: "Seats Won", sortable:true},
    			    	            {key:"avgPercentage", label: "Avg %", sortable:true},
									{key:"PConstavgPercentage", label: "PC-Avg %", sortable:true}
    			    	        ]; 
    		var localLeadersDataSource = new YAHOO.util.DataSource(results.partyResultsList); 
    			localLeadersDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
    			localLeadersDataSource.responseSchema = { 
    	            fields: [{key:"partyName"},{key:"seatsParticipated", parser:"number"},{key:"totalSeatsWon", parser:"number"},{key:"avgPercentage", parser:"number"},{key:"PConstavgPercentage"}] 
    	        };
    			
    	        var myConfigs = { 
    					    paginator : new YAHOO.widget.Paginator({ 
    				        rowsPerPage    : 10 
    					    }) 
    						};
    	       	    	        
    		var localLeadersDataTable =  new YAHOO.widget.DataTable("partyResultsDataDiv", localLeadersColumnDefs, localLeadersDataSource,myConfigs);
			
			var gStr = '';
			gStr += '<div id="graphOptionsDiv" style="text-align:center;color:#4B74C6;font-weight:bold;">';
			gStr += 'Party Performance - ';
			gStr += '<input type="radio" value="seats" name="graphOptionRadio" checked="checked" onclick="buildGraph(this.value)" style="margin-right:4px;">By Seats Won';
			gStr += '<input type="radio" value="percentage" name="graphOptionRadio" onclick="buildGraph(this.value)" style="margin-left: 15px;margin-right:4px;">By Percentage';
			gStr += '</div>';
			gStr += '<div id="graphDiv"></div>';

			graphDivElmt.innerHTML = gStr;

			buildGraph("seats");
			if(checkedRadioName == 'all')
			{
				$('#allCheckId').attr('checked',true);
				showSelectOptions(checkedRadioName);
			}
			else if(checkedRadioName == 'district')
			{
				$('#districtCheckId').attr('checked',true);
				showSelectOptions(checkedRadioName);
			}
			else if(checkedRadioName == 'parties')
			{
				$('#partyCheckId').attr('checked',true);
				showSelectOptions(checkedRadioName);
			}
	}
	
	function buildGraph(graphType)
	{		
		var data = new google.visualization.DataTable();
        data.addRows(partyResultsList.length);


		if(graphType == "seats")
		{
			data.addColumn('string', 'Party');
		    data.addColumn('number', 'Seats Won');
			for(var i=0; i<partyResultsList.length; i++)
			{
				data.setValue(i, 0, ""+partyResultsList[i].partyName);
				data.setValue(i, 1, parseInt(partyResultsList[i].totalSeatsWon));
			}	
		}
		else if(graphType == "percentage")
		{
			data.addColumn('string', 'Party');
		    data.addColumn('number', 'Percentage');
			for(var i=0; i<partyResultsList.length; i++)
			{
				data.setValue(i, 0, ""+partyResultsList[i].partyName);
				data.setValue(i, 1, parseFloat(partyResultsList[i].avgPercentage));
			}
		}

		var chart = new google.visualization.PieChart(document.getElementById('graphDiv'));
        chart.draw(data, {width: 450, height: 250, title: 'Party Results'});

	}

	 //mandalwise google charts
	 function getCensusDataInteractiveChart(results,divId)
	 {
		 var chartColumns = results[0].partyResultsVO;
		 var chartRows = results;

		 var data = new google.visualization.DataTable();
		 var colorArray = new Array(); 
		 var colorStatic = new Array('#088A85','#00FFFF','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A');

		  data.addColumn('string', 'Party');
		  //for columns
		  for(var i in chartColumns){
		   var colData = chartColumns[i].partyName;
		   data.addColumn('number', colData);

		   if(chartColumns[i].partyName == 'TDP'){
			   colorArray.push('#C7005D');
		   }
		   else if(chartColumns[i].partyName == 'TRS'){
				colorArray.push('#F5A9F2');
		   }else if(chartColumns[i].partyName == 'INC'){
				colorArray.push('#2A00D2');
		   }else if(chartColumns[i].partyName == 'BJP'){
				colorArray.push('#FE9A2E');
		   }else if(chartColumns[i].partyName == 'PRP'){
				colorArray.push('#74DF00');
		   }
		   else{
			   colorArray.push(colorStatic[i]);
		   }

		  }

		  //for rows
		  for(var j in chartRows)
		  {
			  var array = new Array();
			  array.push(chartRows[j].constituencyName);

			  var partyRes = chartRows[j].partyResultsVO;
			  
			  for(var k in partyRes){
				array.push(partyRes[k].votesPercent);
			  }
			  
			  data.addRow(array);
		  }
		  var ctitle = 'All Parties Performance Graph'; 
		  new google.visualization.LineChart(document.getElementById(divId)).
		  draw(data, {curveType: "function",width: 800, height: 450,title:ctitle,colors:colorArray,titleColor:'red' ,titleFontSize:14,lineWidth:2,hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
			
	 }

	function censusAjaxCall(jsObj,url){
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getCensusDetails")
								{	
									hideAjaxImage();
									buildPopulationRange(jsObj,myResults);
								} 
								else if(jsObj.task == "getPartiesPerformanceInCensusReport" || jsObj.task == "showPartyResultsByFilterView")
									buildPerformanceTable(jsObj,myResults);
								else if(jsObj.task == "getPartiesByState")
									buildPartiesSelectBox(jsObj.divId, myResults.parties);
								else if(jsObj.task == "allPartyCensusResults")
								{	
									jobjP = jsObj;
									resultsP = myResults;
									buildAllPartyCensusResults(jsObj,myResults);
								}
								else if(jsObj.task == "censusByParty")
								{
									hidePartySelectAjaxImage();
									buildPartyResultsByRanges(jsObj,myResults);
									
								}
								else if(jsObj.task == "getLatestElectionYears")
								{
									clearOptionsListForSelectElmtId(jsObj.selectElementId);
									fillOptionsForSelectedElmt(jsObj.selectElementId, myResults);
								}
						}
						catch (e)
							{   
							  // 	alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
	function showDistrictSelect()
	{	
		$('#censusReporterror_Div').html('');
		var districtListValues = document.getElementById("districtList");
		removeSelectElements(districtListValues)
		$("#stateList").val(0);		 
		$("#districtList").val(0);		 
		$("#yearSelect").val(0);
		$("#censusSelect").val(0);
		changeCensusType();
	}
	function changeCensusType()
	{
		$('#censusPopulationRange1').css('display','none');
		$('#censusPopulationRange').css('display','none');
		$('#censusPopulationRange_selectParty').css('display','none');
		$('#partyResultsPerformance_main').css('display','none');		
	}
	
	function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	
	
</script>
</head>
<body>
	<div id="censusReport_main">
	<div id="censusReportHeading"> Elections Vs Demographics</div>
		<div id="censusReport_body">
		  <div style="background:#FFF; padding: 20px; border-radius:10px;">
		   <div style="padding-bottom: 15px;border-bottom:1px solid #ADADAD;">
				  <div id="censusReport_body_heading">
					<img src="images/icons/infoicon.png" style="margin-left: 35px; padding-left: 0px; padding-right: 20px;"/>
					Select the following Options to view Election Results Comparision With Census details
				  </div>
		    </div>
			<div>
				<table cellpadding="2" >
				<tr>
				   <th>Please Select Report Level :&nbsp;&nbsp;</th>
					<td><input  id="stRadioId" type="radio" name="location" value="state" onclick="showDistrictSelect()" checked="checked"></input></td>
					<th>State Wise Report</th>
					<td><input  id="diRadioId" type="radio" name="location" value="district" onclick="showDistrictSelect()" style="margin-left: 15px;"></input></td>
					<th>District Wise Report</th>
				</tr>
			 </table>
			 <table id="censusReport_body_input_table" cellpadding="5">
			
			<tr>
				<th>State</th>
				<td>
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList" list="states" listKey="id" listValue="name"   onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtList','currentAdd');getLatestElectionYears();changeCensusType();validate();"/>	
				</td>

				<th>District</th>
				<td>
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district_s" id="districtList" list="{}" listKey="id" listValue="name" onchange="getCensusDetails()" headerKey="0" headerValue="Select District" />	
				</td>
				
				<th>Year</th>
				<td>
					
					<s:select theme="simple" cssClass="selectWidth" id="yearSelect" label="Year" name="year" list="{}" listKey="id" listValue="name"  headerKey = "0" headerValue = "Select Year" />

				<!--	<select id="yearSelect">
						<option>2009</option>
						<option>2006</option> 
					</select>  --->
				</td>
				<th>CensusType</th>
				<td><s:select theme="simple" cssClass="selectWidth" id="censusSelect" label="Select Census Parameter" name="censusParam" list="#session.censusParamList" listKey="id" listValue="name"  headerKey = "0" headerValue = "Select" onchange="getCensusDetails()"/>
				</td>
			</tr>
		</table>
		<div id="censusReporterror_Div" style="color:red;font-weight:bold;font-size:13px;"></div>
		</div>
		<div id="ajaxImageDiv" style="display:none;"></div>
		</div>
		
		
		<div id="censusPopulationRange1" style="display:none;">
			<div id="censusPopulationRange_head1">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<!--<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>-->
					<td><div class="censusWidgetMainHeader"><span id="censusPopulationRange_head_span1" class="censusWidgetHeader_span" style="top:11px;"></span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
			</div>
			<div id="censusPopulationRange_body1" class="mainWidgetsBody ">
				<div id="censusPopulationRange_body_head1" ></div>
				<div id="censusPopulationRange_body_body1" >
					<table width="100%">					
						<tr>
							<td valign="top" width="50%"><div id="censusPopulationRangeData1"></div></td>
							<td valign="top" width="50%">
								<div id="censusPopulationRangeGraph_head1"></div>
								<div id="censusPopulationRangeGraph_body1" class="yui-skin-sam"></div>
							</td>
						</tr>
					</table>
				</div>
				<div id="censusPopulationRange_footer1" style="margin-left:15px;"></div>
				<div id="censusPopulationRange_note" style="margin-left:15px;"><B>AVG % - Average Percentage</B></div>
				
			</div>
			
		</div>
		
		
		<div id="censusPopulationRange_selectParty" style="display:none;"></div> 
		
		<div id="censusPopulationRange" style="display:none;">
			<div id="censusPopulationRange_head">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td><div class="censusWidgetMainHeader"><span id="censusPopulationRange_head_span" class="censusWidgetHeader_span" style="top:11px;"></span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
			</div>
			<div id="censusPopulationRange_body" class="mainWidgetsBody ">
				<!--<div id="censusPopulationRange_body_head" ></div>-->
				<div id="censusPopulationRange_body_body" >
					<table width="100%">					
						<tr>
							<td valign="top" width="50%"><div id="censusPopulationRangeData"></div></td>
							<td valign="top" width="50%">
								<div id="censusPopulationRangeGraph_head"></div>
								<div id="censusPopulationRangeGraph_body" class="yui-skin-sam"></div>
							</td>
						</tr>
					</table>
				</div>
				<div id="censusPopulationRange_footer" style="margin-left:15px;"></div>	
			</div>
			
		</div>
		
		
		<div id="partyResultsPerformance_main" class="yui-skin-sam" style="display:none;margin-top:10px;">
			<div id="partyResultsPerformance_head">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td><div class="censusWidgetMainHeader"><span id="censusWidgetMainHeader_span" class="censusWidgetHeader_span" style="top:11px;"></span></div></td>
						<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
			</div>
			<div id="partyResultsPerformance_body" class="mainWidgetsBody">
				<div id="resultsOptionsDiv_main"></div>
				<div id="partyResultsDiv_main" class="mainWidgetsDiv">
					<div id="partyResultsDiv_head">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="3px"><img src="images/icons/electionResultsAnalysisReport/first.png"/></td>
								<td><div class="censusWidgetHeader"><span class="censusWidgetHeader_span" style="color: Navy;font-size: 13px;">Party Wise Performance</span></div></td>
								<td width="3px"><img src="images/icons/electionResultsAnalysisReport/second.png"/></td>								
							</tr>
						</table>
					</div>
					<div id="partyResultsDiv_body" class="mainWidgetsBody">
						<table width="100%">
							<tr>
								<td><div id="partyResultsDataDiv"><center>
								<span><b>Your Request is Processing, Please Wait....</b> </span>
								<img class="ajaxImgClass" src="images/icons/goldAjaxLoad.gif"></img></center></div></td>
								<td><div id="partyResultsGraphDiv"></div></td>
							</tr>
						</table>
						<h5 style="color:red";>PC : Participated Constituencies</h5>
					</div>
					
				</div>			
				<div id="performanceGraphDiv_main" class="mainWidgetsDiv">
					<div id="performanceGraphDiv_head">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="3px"><img src="images/icons/electionResultsAnalysisReport/first.png"/></td>
								<td><div class="censusWidgetHeader"><span class="censusWidgetHeader_span" style="color: Navy;font-size: 13px;">Constituency Wise Performance</span></div></td>
								<td width="3px"><img src="images/icons/electionResultsAnalysisReport/second.png"/></td>
							</tr>							
						</table>
					</div>
					<div id="performanceGraphDiv_body" class="mainWidgetsBody" style="width:856px;overflow:auto;"><center>
					<span><b>Your Request is Processing, Please Wait....</b> </span>
						<img class="ajaxImgClass" src="images/icons/goldAjaxLoad.gif"></img></center>
					</div>
				</div>
			</div>
		</div>
	 </div>
  </div>
	<script>
	showDistrictSelect();
	</script>
</body>
</html>