<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Live Election Results Analysis</title>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<style>
 
 .yui-skin-sam.yui-dt table th {
		background-color: #CEEDF0;
		font-size: 13px;
		font-weight: bold;
		padding: 7px;
		text-align: left;
		border-collapse :collapse;
}
.yui-skin-sam.yui-dt table  {
		
		border-collapse :collapse;
}
.yui-skin-sam.yui-dt tbody{
border: 1px solid #CDCDCD;
}
 .yui-skin-sam.yui-dt td {
	font-weight: normal;
	padding: 8px 8px 8px 10px;
}
#partyOldResultsDiv table tr:nth-child(2n) {
background: none repeat scroll 0 0 #F9F9F9;
}
#partyNewResultsDiv table tr:nth-child(2n) {
background: none repeat scroll 0 0 #F9F9F9;
}
#partyWonResultsDiv table tr:nth-child(2n) {
background: none repeat scroll 0 0 #F9F9F9;
}
.yui-dt-sortable{
	color:#000;
}
.partyOldResults
{
width: 900px;
height: auto;
display: inline-block;
border: 1px solid rgb(204, 204, 204); 
margin-top: 42px; 
background: none repeat scroll 0pt 0pt rgb(255, 255, 255);
}
.partyNewResults{
border: 1px solid rgb(204, 204, 204); 
width: 900px;
height: auto; 
display: inline-block;
margin-top: 25px; 
background: none repeat scroll 0% 0% rgb(255, 255, 255);
}
.partyWonResults{
border: 1px solid rgb(204, 204, 204); 
width: 900px;
height: auto; 
display: inline-block;
margin-top: 25px; 
background: none repeat scroll 0% 0% rgb(255, 255, 255);
}
.wonContainer {
    -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    background-color: #FFFFFF;
    margin: 0px 0px 13px 14px;
    max-width: 456px;
    padding: 10px;
}
.lostContainer {
    -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    background-color: #FFFFFF;
    margin: 0px 0px 13px 14px;
    max-width: 456px;
    padding: 10px;
}
.headingstyle
{
	background: #96C;
	color: white;
	font-weight: bold;
	font-family: verdana;
	width: 98px;
	-webkit-border-radius: 3px;
	padding-right: 10px;
	padding-left: 10px;
	padding-top: 6px;
	padding-bottom: 6px;
}
/*#partyResultsDiv{
    border: 1px solid #cdcdcd;
    width: 55%;
    margin: 38px;
}*/
</style>
</head>
<body>
<center>
<div style="background:#fff;width: 950px;margin-top:12px;">
<span id="mainHeading" style="color:#990099;font-weight:bold;font-size:20px;margin:10px;">Live Election Results Analysis</span>
 <div style="margin-bottom: 15px;padding-top: 21px;font-family:verdana;">
  <input type="radio" value="2" name="electionTypeRadio" id="assemblyId" onclick="getStates(this.value)">&nbsp;<strong>Assembly </strong>
	<span style="padding-left:5px;font-weight:bold;">
	 <input type="radio" value="1" name="electionTypeRadio" id="parliamentId" onclick="getStates(this.value)" >
	 Parliament</span>
</div>
<div>Select State :
<select  id="stateId" onchange="getElectionYears()" style="width:150px;">
<option value="0">Select State</option>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;Select Election Year : 
<select id="electionYearId" onchange="getPartyWinningOrLeadingConstituenciesCount();getPartiesGainAndLossInfo();" style="width:150px;">
<option value="0">Select Election Year</option>
</select><br><br><span style="margin-top:10px;"><img id="year_ImgSpan" src="images/icons/goldAjaxLoad.gif" style="display:none;"></span>
</div>

<div style="font-weight:bold;font-family:verdana;font-size:12px;text-align: left; margin-bottom:19px;margin-top:20px;" id="overViewDiv">
 </div>
<div id="tableDiv"></div>
<div id="partyWonOrLeadResult" style="text-align:left;margin-top:25px;"></div>
<div id="partyAnalysisHeading" style="margin-left:15px;margin-bottom:15px;margin-top:15px;width:100%;"></div>
<div id="partyGainedAnalysisDiv" style="text-align:left;"></div>
<div id="partiesSeatsFlownToOtherPartiesDiv" style="text-align:left;margin-top:25px;border:1px solid #cdcdcd;"></div>
<div>
<div id="partyOldResults">
 <div id="partyOldResultsDiv" class="yui-skin-sam" style="float: left;margin-left:110px;margin-top: 18px;"></div><span id="oldResultHeading" style="font-weight:bold;float: left;font-family:verdana;margin:10px;"></span>
   <div id="partyOldResultsChart" style="margin-top: 60px;"></div>
 </div>

<div id="partyNewResults">
 <div id="partyNewResultsDiv" class="yui-skin-sam"  style="width:241px; clear: both; margin-top:18px; float:left; margin-left:107px;">
 </div><span id="newResultHeading" style="font-weight:bold;float: left;font-family:verdana;margin:10px;"></span>
 <div id="partyNewResultsChart" style="margin-top:60px;">
 </div>
</div>

<div id="partyWonResults">
 <div id="partyWonResultsDiv" class="yui-skin-sam" style="float: left;margin-left:110px; margin-top:18px; ">
 </div><span id="wonResultHeading" style="font-weight:bold;float: left;font-family:verdana;margin:10px;"></span>
 <div id="partyWonResultsChart" style="position: relative; margin-top: 35px;">
 </div>
</div>

<table width="100%" style="background: #fff;"><tr><td width="50%">
<div id="partyAnalysisHeading" style="margin-top:15px;margin-left:15px;margin-bottom:10px;"></div>

</td>
<td width="50%" valign="top"><div id="partyLostAnalysisHeading" style="margin-left:15px;margin-bottom:10px;margin-top:15px;"></div>
</td>
</tr></table>
<div id="partyLostAnalysisDiv"></div>
</div>

</div>
</div>
</center>

<script type="text/javascript">

google.load("visualization", "1", {packages:["corechart"]});

function callAjaxForElectionResultPage(url,jObj){
	
	  var callback = {
		 success : function(o){
		 try {
			 myResults = YAHOO.lang.JSON.parse(o.responseText);
			 
			 if(jObj.task == "getElectionYears"){
				clearOptionsListForSelectElmtId("electionYearId");
			    createOptionsForSelectElmtIdWithSelectOption("electionYearId",myResults);
			 }
			 else if(jObj.task == "getStates"){
				clearOptionsListForSelectElmtId("stateId");
				createOptionsForSelectElmtIdWithSelectOption("stateId",myResults);

			 }
			 else if(jObj.task =="getConstituenciesCount"){
				//showOverView(myResults);
				//showPartyGainedResults();
			}
			  else if(jObj.task == "getPartyWonOrLeadConstituenciesCount"){
				  
				  if(myResults[0] !=null && myResults[0].partialResult)
					 {
						buildPartyWonOrLeadConstituenciesCount(myResults);
						
					 }
				  else
					  buildPartyWonConstituenciesTable(myResults);
			  }
			  else if(jObj.task =="getPartiesGainAndLossInfo"){
					buildCompareResultForWonorLead(myResults);
					showPartyGainedResults(myResults);
					hideBusyImgWithId("year");
					//showPartyLossResults(myResults);
			  }
		   }
	     catch(e)
		   {   
		    alert("Invalid JSON result" + e);   
		   }  
		},
		scope : this,
		failure : function( o )
			{
								//alert( "Failed to load result" + o.status + " " + o.statusText);
			}
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	 }

function buildCompareResultForWonorLead(myResults)
{
	var partyWonOrLeadResultElmt = document.getElementById("partyWonOrLeadResult");
	var str='';
	if(myResults !=null && myResults.length >0)
	{
		str += '<span class="headingstyle">Partywise Seats Gained/Lost Analysis In Result Known Constituencies</span>';
		str+='<div style="margin-top:10px;margin-bottom:20px;">'
		str += '<table><tr><td width="45%"><div>';
		str += '<table cellspacing="2px" cellpadding="6px" style="border:1px solid #cdcdcd;border-collapse:collapse;width:97%;">';
	 
	 for(var i in myResults)
	 {
		if(i==0)
		{
		  str+='<tr style="text-align:center;background:#dddddd;color:#000;font-family: verdana;font-size: 11px;">';
		  str+='<th>Party</th>';
		  str+='<th>Total Participated Count</th>';
		  str+='<th>Known Result count</th>';
		  str+='<th>Won/Lead Count</th>';
		  str+='<th>Won/Lead In (%)</th>';
		}

		str+='<tr style="text-align:center;">';
		str+='<td style="color:#05A8E9;">'+myResults[i].partyName+'</td>';
		str+='<td>'+myResults[i].totalSeatsParticipated+'</td>';
		
		if(myResults[i].totalKnownCount !=null)
			str+='<td>'+myResults[i].totalKnownCount+'</td>';
		else
			str+='<td>0</td>';

		if(myResults[i].wonOrLeadCount !=null)
			str+='<td>'+myResults[i].wonOrLeadCount+'</td>';
		else
			str+='<td>0</td>';
		
		if(myResults[i].winOrLeadPercent !=null)
			str+='<td>'+myResults[i].winOrLeadPercent+'</td>';
		else
			str+='<td>0</td>';
		
		str+='</tr>';
	 }
	 str += '</table></div></td>';

	 str += '<td width="55%"><div id="knownResultGraphDivId"></div></td></tr></table>';
	 str += '</div>';
	
	 if(myResults[i].isFirstElectionAfterDelimtation)
	 {
		str += '<span class="headingstyle">Partywise Seats Gained/Lost Analysis In Result Known New/Old Constituencies</span>';
		str += '<div style="margin-top:10px;">';
		str += '<table cellspacing="2px" cellpadding="6px" style="border:1px solid #cdcdcd;border-collapse:collapse;width:97%;margin-top:15px;">';
		str+='<tr style="text-align:center;background:#dddddd;color:#000;font-family: verdana;font-size: 11px;">';
		
		str += '<th>Party</th>';
		str += '<th>Participated Seats In Old Constituencies</th>';
		str += '<th>Known Result In old Constituency</th>';
		str += '<th>Won/Lead Count In Old Constituency</th>';
		str += '<th>Participated Seats In New Constituencies</th>';
		str += '<th>Known Result In New Constituency</th>';
		str += '<th>Won/Lead Count In New Constituency</th>';
		str += '<th>Won/Lead In (%) In Old Constituency</th>';
		str += '<th>Won/Lead In (%) In New Constituency</th>';

		str += '</tr>';

		for(var i in myResults)
		{
			str += '<tr style="text-align:center;">';
			str += '<td style="color:#05A8E9;">'+myResults[i].partyName+'</td>';
			str += '<td>'+myResults[i].oldConstituencyParticipatedCount+'</td>';
			str += '<td>'+myResults[i].oldKnownCount+'</td>';
			str += '<td>'+myResults[i].wonOrLeadCountInOld+'</td>';
			str += '<td>'+myResults[i].newConstituencyParticipatedCount+'</td>';
			str += '<td>'+myResults[i].newKnownCount+'</td>';
			str += '<td>'+myResults[i].wonOrLeadCountInNew+'</td>';
			str += '<td>'+myResults[i].oldWinOrLeadPercent+'</td>';
			str += '<td>'+myResults[i].newWinOrLeadPercent+'</td>';
			str += '</tr>';
		}

		str += '</table>';
		str += '</div>';

		str += '<div id="knownResultInOldAndNewGraphDivId" style="margin-top:15px;border:1px solid #cdcdcd;">';
		str += '</div>';
	 }
	partyWonOrLeadResultElmt.innerHTML = str;

	var parties = new Array();
	
	for(var i=0;i<myResults.length;i++)
		if(myResults[i].partyName != 'IND')
			parties.push(myResults[i].partyName);

	var data = new google.visualization.DataTable();
	
	data.addColumn('string', 'Party');
	data.addColumn('number', 'Won/Lead %');

	data.addRows(parties.length);

	for(var j=0;j<myResults.length;j++)
	{
		if(myResults[j].partyName != 'IND')
		{
			data.setValue(j,0,parties[j]);
			data.setValue(j,1,myResults[j].winOrLeadPer);
		}
	}

	var chart = new google.visualization.ColumnChart(document.getElementById('knownResultGraphDivId'));
		chart.draw(data, {width: 550, height: 410,legend:'right',legendTextStyle:{fontSize:12}, title:'Partywise Gain/Won % In Result Known Seats',hAxis: {title: 'Parties Participated', titleTextStyle: {color: 'red'}}
	});


	if(myResults[0].isFirstElectionAfterDelimtation)
	{
		var partiesArray = new Array();
	
		for(var i=0;i<myResults.length;i++)
		if(myResults[i].partyName != 'IND')
			partiesArray.push(myResults[i].partyName);

		var data = new google.visualization.DataTable();
		
		data.addColumn('string', 'Party');
		data.addColumn('number', 'Won/Lead % In Old Constituencies');
		data.addColumn('number', 'Won/Lead % In New Constituencies');

		data.addRows(partiesArray.length);

		for(var j=0;j<myResults.length;j++)
		{
			if(myResults[j].partyName != 'IND')
			{
				data.setValue(j,0,partiesArray[j]);
				data.setValue(j,1,myResults[j].oldWinOrLeadPer);
				data.setValue(j,2,myResults[j].newWinOrLeadPer);
			}
		}

		var chart = new google.visualization.ColumnChart(document.getElementById('knownResultInOldAndNewGraphDivId'));
		chart.draw(data, {width: 900, height: 500,legend:'right',legendTextStyle:{fontSize:12}, title:'Partywise Gain/Won % Comparison In New & Old Result Known Constituencies',hAxis: {title: 'Parties Participated', titleTextStyle:{color:'red'}}
		});

	}

  }
}

function buildPartiesSeatsFlownToOtherPartiesDiv(myResults)
{
	var seatsFlownDivEle = document.getElementById('partiesSeatsFlownToOtherPartiesDiv');
	
	if(myResults == null || myResults.length == 0)
	{
		seatsFlownDivEle.style.display = 'none';
		return;
	}

	var str = '';
	
	str += '<div style="margin:5px;">';

	var parties = new Array();
	
	str += '<table cellspacing="2px" cellpadding="6px" style="border:1px solid #cdcdcd;border-collapse:collapse;width:97%;margin-top:15px;">';
	str+='<tr style="text-align:center;background:#dddddd;color:#000;font-family: verdana;font-size: 11px;">';
		
	str += '<th>Party</th>';
	
	for(var i=0;i<partyArray.length;i++)
		str += '<th>'+partyArray[i]+'</th>';
	str += '</tr>';

	str += '<tr style="text-align:center;">'
	str += '<td style="color:#05A8E9;">'+myResults[i].partyName+'</td>';
	str += '</tr>';
	
	str += '</div>';

	seatsFlownDivEle.innerHTML = str;
}

function checkForParty(party,partyArray)
{
	for(var i=0;i<partyArray.length;i++)
		if(party == partyArray[i])
		return true;
	
	return false;
}

function hideBusyImgWithId(elmtId)
	{
		var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = "none";
	}

function showBusyImgWithId(elmtId)
	{
		var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = "block";
	}

function buildPartyWonOrLeadConstituenciesCount(myResults){

	document.getElementById("partyWonResultsDiv").innerHTML = '';
	document.getElementById("partyWonResultsChart").innerHTML = '';
	document.getElementById('wonResultHeading').innerHTML ="";
	$("#partyWonResults").removeClass('partyWonResults'); 

	var newConstituencyArray = new Array();
	var oldConstituencyArray = new Array();
	for (var i in myResults)
	{
		if(myResults[i].leadCountInNew !=null)
		{
			var newResultObj  = {

							partyName :myResults[i].partyName,
							leadCountInNew:myResults[i].leadCountInNew,
							wonCountInNew :myResults[i].wonCountInNew
		};
		newConstituencyArray.push(newResultObj);
		}
		if(myResults[i].leadCountInOld !=null)
		{
			var oldResultObj = {
							partyName :myResults[i].partyName,
							leadCountInOld:myResults[i].leadCountInOld,
							wonCountInOld :myResults[i].wonCountInOld
			};
		oldConstituencyArray.push(oldResultObj);
		}
	
	}
	showOverView(myResults);
	buildOldDataTable(myResults,oldConstituencyArray);
	buildNewDataTable(myResults,newConstituencyArray);
	
}

function buildNewDataTable(myResults,newConstituencyArray,divElmt){
	
	$("#partyNewResults").addClass('partyNewResults');
document.getElementById('newResultHeading').innerHTML ="Partywise Results In New Constituencies";
 			var resultsColumnDefs = [ 	
				{
				key : "partyName",
				label : "PartyName",
				sortable : true,
				
				},

				{
				key : "leadCountInNew",
				label : "Leads",
				sortable : true
				},

				{
				key : "wonCountInNew",
				label : "Won",
				sortable : true
				}
						
			];
		
    var myConfigs = {    
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 5,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [5,10,20,30], 
						pageLinks: 5
						})
						
					};	
		var myDataSource = new YAHOO.util.DataSource(newConstituencyArray);
		myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "partyName","leadCountInNew", "wonCountInNew" ]
					};

				
	var myDataTable = new YAHOO.widget.DataTable("partyNewResultsDiv",resultsColumnDefs, myDataSource,myConfigs,{
            caption: "My Caption",
            summary: "My Summary"
}); 
	
	/**  For Chart **/

	var data = new google.visualization.DataTable();
	data.addColumn('string','partyName');
	data.addColumn('number','wonCountInNew');
	data.addRows(newConstituencyArray.length);
	for(var i=0 ; i<newConstituencyArray.length ; i++)
	{
	data.setValue(i, 0, newConstituencyArray[i].partyName);
	data.setValue(i, 1, parseInt(newConstituencyArray[i].wonCountInNew));
	}
	
	var chart = new google.visualization.PieChart(document.getElementById('partyNewResultsChart')); 
	chart.draw(data,{width: 360, height: 250, title: 'Party Results In New Constituencies'});


}
function buildOldDataTable(myResults,oldConstituencyArray,divElmt){

	$("#partyOldResults").addClass('partyOldResults');
	
document.getElementById('oldResultHeading').innerHTML ="Partywise Results In Old Constituencies";

			var resultsColumnDefs = [ 	
				{
				key : "partyName",
				label : "PartyName",
				sortable : true,
				
				},

				{
				key : "leadCountInOld",
				label : "Leads",
				sortable : true
				},

				{
				key : "wonCountInOld",
				label : "Won",
				sortable : true
				}
						
			];
		
    var myConfigs = {    
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [5,10,20,30], 
						pageLinks: 5
						})
						
					};	
		var myDataSource = new YAHOO.util.DataSource(oldConstituencyArray );
		myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "partyName","leadCountInOld", "wonCountInOld" ]
					};

	/**  For Chart **/

	var myDataTable = new YAHOO.widget.DataTable("partyOldResultsDiv",resultsColumnDefs, myDataSource,myConfigs);  


	var data = new google.visualization.DataTable();
	data.addColumn('string','partyName');
	data.addColumn('number','wonCountInOld');
	data.addRows(oldConstituencyArray.length);
	for(var i=0; i<oldConstituencyArray.length; i++){
	data.setValue(i,0,oldConstituencyArray[i].partyName);
	data.setValue(i,1,oldConstituencyArray[i].wonCountInOld);
	}
	var chart = new google.visualization.PieChart(document.getElementById('partyOldResultsChart')); 
	chart.draw(data,{width: 360, height: 250, title:'Party Results In Old Constituencies'});
	

}
function buildPartyWonConstituenciesTable(myResults){
	document.getElementById('newResultHeading').innerHTML ="";
	document.getElementById('oldResultHeading').innerHTML ="";
	document.getElementById('wonResultHeading').innerHTML =""; 
	$("#partyWonResults").removeClass("partyWonResults");
	document.getElementById("partyNewResultsDiv").innerHTML = '';
	document.getElementById("partyNewResultsChart").innerHTML = '';
	document.getElementById("partyOldResultsDiv").innerHTML = '';
	document.getElementById("partyOldResultsChart").innerHTML = '';
	document.getElementById("partyWonResultsDiv").innerHTML = '';		document.getElementById("partyWonResultsChart").innerHTML = '';


	var wonResultsArray = new Array();
	var newConstituencyArray = new Array();
	var oldConstituencyArray = new Array();

	
	for(var i in myResults){
		
		if(myResults[i].wonCountInNew !=null){
			var newResultObj  = {

								partyName :myResults[i].partyName,
								wonCountInNew :myResults[i].wonCountInNew
			};
		 newConstituencyArray.push(newResultObj);
		}
		if(myResults[i].wonCountInOld !=null){
			var oldResultObj = {
							partyName :myResults[i].partyName,
							wonCountInOld :myResults[i].wonCountInOld
			};
		oldConstituencyArray.push(oldResultObj);
		}

	   if(myResults[i].countOfWinningConstituencies !=null)
	    {
		 var wonResultObj = {

			partyName :myResults[i].partyName,
			countOfWinningConstituencies :myResults[i].countOfWinningConstituencies
	 
		};
		wonResultsArray.push(wonResultObj);
	}
}
	showOverView(myResults);
	if(oldConstituencyArray.length>0)
		buildDatatableForOldResults(oldConstituencyArray);

	if(newConstituencyArray.length>0)
		buildDataTableForNewResults(newConstituencyArray);

	if(wonResultsArray.length >0){
		$("#partyNewResults").removeClass("partyNewResults");
		$("#partyOldResults").removeClass("partyOldResults");
		$("#partyWonResults").addClass("partyWonResults");
document.getElementById('wonResultHeading').innerHTML ="Partywise Results Winning Constituencies"; 

	    var resultsColumnDefs = [ 	
				{
				key : "partyName",
				label : "PartyName",
				sortable : true,
				
				},
				{
				key : "countOfWinningConstituencies",
				label : "Won",
				sortable : true
				}
						
			];
		  var myConfigs = {    
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [5,10,20,30], 
						pageLinks: 5
						})
						
					};	
		var myDataSource = new YAHOO.util.DataSource(wonResultsArray);
		myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						fields : [ "partyName","countOfWinningConstituencies" ]
					};

	
	var myDataTable = new YAHOO.widget.DataTable("partyWonResultsDiv",resultsColumnDefs, myDataSource,myConfigs); 
	
		/**  For Chart **/

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'PartyName');
    data.addColumn('number', 'countOfWinningConstituencies');
    data.addRows(myResults.length);
	 for(var i=0 ; i<myResults.length ; i++){

	data.setValue(i, 0, myResults[i].partyName);
	data.setValue(i, 1, parseInt(myResults[i].countOfWinningConstituencies));
	 }
	var chart = new google.visualization.PieChart(document.getElementById('partyWonResultsChart'));
        chart.draw(data, {width: 360, height: 250, title: 'Party Wise Winning Constituencies'});

		/*End*/
	
	}

}
function buildDataTableForNewResults(newConstituencyArray){
	
	$("#partyNewResults").addClass("partyNewResults");
	document.getElementById('newResultHeading').innerHTML ="Partywise Results In New Constituencies";

	var resultsColumnDefs = [ 	
				{
				key : "partyName",
				label : "PartyName",
				sortable : true,
				
				},
				{
				key : "wonCountInNew",
				label : "Won",
				sortable : true
				}
						
			];
			var myConfigs = {    
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [5,10,20,30], 
						pageLinks: 5
						})
						
					};	
		var myDataSource = new YAHOO.util.DataSource(newConstituencyArray);
		myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						fields : [ "partyName","wonCountInNew" ]
					};

	
	var myDataTable = new YAHOO.widget.DataTable("partyNewResultsDiv",resultsColumnDefs, myDataSource,myConfigs);  
	
		/**  For Chart **/

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'PartyName');
    data.addColumn('number', 'wonCountInNew');
    data.addRows(newConstituencyArray.length);
	 for(var i=0 ; i<newConstituencyArray.length ; i++){

	data.setValue(i, 0, newConstituencyArray[i].partyName);
	data.setValue(i, 1, parseInt(newConstituencyArray[i].wonCountInNew));
	 }
var chart = new google.visualization.PieChart(document.getElementById('partyNewResultsChart'));
    chart.draw(data, {width: 360, height: 250, title: 'Party Wise Winning In NewConstituencies'});

		/*End*/
}
function buildDatatableForOldResults(oldConstituencyArray){

	$("#partyOldResults").addClass("partyOldResults");
	document.getElementById('oldResultHeading').innerHTML ="Partywise Results In Old Constituencies";

	var resultsColumnDefs = [ 	
				{
				key : "partyName",
				label : "PartyName",
				sortable : true,
				
				},
				{
				key : "wonCountInOld",
				label : "Won",
				sortable : true
				}
						
			];
			var myConfigs = {    
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [5,10,20,30], 
						pageLinks:5
						})
						
					};	
		var myDataSource = new YAHOO.util.DataSource(oldConstituencyArray);
		myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						fields : [ "partyName","wonCountInNew" ]
					};

	
	var myDataTable = new YAHOO.widget.DataTable("partyOldResultsDiv",resultsColumnDefs, myDataSource,myConfigs); 
	
	/**  For Chart **/

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'PartyName');
    data.addColumn('number', 'wonCountInNew');
    data.addRows(oldConstituencyArray.length);
	 for(var i=0 ; i<oldConstituencyArray.length ; i++){

	data.setValue(i, 0, oldConstituencyArray[i].partyName);
	data.setValue(i, 1, parseInt(oldConstituencyArray[i].wonCountInOld));
	 }
	var chart = new google.visualization.PieChart(document.getElementById('partyOldResultsChart'));
    chart.draw(data, {width: 360, height: 250, title: 'Party Wise Winning In OldConstituencies'});

		/*End*/
}

	 function getConstituenciesCount(){

		var electionId = document.getElementById("electionYearId").value;;
		
		var jObj=
		{
			electionId:electionId,
			task:"getConstituenciesCount"						
		};
			
		var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
		var url = "getResultsAjaxAction.action?"+rparam;						
		callAjaxForElectionResultPage(url,jObj);


	 }
function getStates(electionTypeId)
 {	
	var jObj=
		{
			electionTypeId:electionTypeId,
			task:"getStates"						
		};
			
		var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
		var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
		callAjaxForElectionResultPage(url,jObj);
}	
function getElectionYears(){

	var stateId = document.getElementById("stateId").value;
    if(document.getElementById("assemblyId").checked == true)
		var electionTypeId = document.getElementById("assemblyId").value;

	if(document.getElementById("parliamentId").checked == true)
		var electionTypeId = document.getElementById("parliamentId").value;
	
	var jObj = {
				electionTypeId : electionTypeId,
				stateId :stateId,
				task : "getElectionYears"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;
	
	callAjaxForElectionResultPage(url,jObj);
 }
function getPartyWinningOrLeadingConstituenciesCount(){

	var electionId = document.getElementById("electionYearId").value;;
	
	var jObj=
	{
		electionId:electionId,
		task:"getPartyWonOrLeadConstituenciesCount"						
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getPartyWonOrLeadConstituenciesCountAction.action?"+rparam;						
	callAjaxForElectionResultPage(url,jObj);
	
}
function showOverView(results){

 for(var i in results)
    {
	var overViewDiv = document.getElementById("overViewDiv");
	var str='';
	str+='<div style="padding:7px;text-align:center">';
	if(results[i].totalSeats != null)
	{
		str +='<span style="padding:14px;">Total Seats - <font color="#05A8E9">'+results[i].totalSeats+'</font></span>';

	if(results[i].countOfLeadConstituences !=null && results[i].countOfLeadConstituences !=0)
	{
		str +='<span style="padding:14px;">Known Result/Leading Constituencies - <font color="#05A8E9">'+results[i].countOfLeadConstituences+'</font></span>';
	}
	if(results[i].oldConstituenciesCount !=null && results[i].oldConstituenciesCount >0)
	{
		str +='<span style="padding:14px;">Old Constituencies - <font color="#05A8E9">'+results[i].oldConstituenciesCount+'</font></span>';
	}
	if(results[i].newConstituenciesCount !=null && results[i].newConstituenciesCount !=0)
	{
		str +='<span style="padding:14px;">New Constituencies - <font color="#05A8E9">'+results[i].newConstituenciesCount+'</font></span>';
	}
	str+='</div>';
	overViewDiv.innerHTML = str;
	}
  }

}

function getPartiesGainAndLossInfo()
{
	showBusyImgWithId("year");

	var electionId = document.getElementById("electionYearId").value;
	var jObj=
	{
		electionId:electionId,
		task:"getPartiesGainAndLossInfo"						
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getPartyWonOrLeadConstituenciesCountAction.action?"+rparam;						
	callAjaxForElectionResultPage(url,jObj);
}

function showPartyGainedResults(myResults){

	var data= new Array();
	var stateName = $("#stateId option:selected").text();
	var electionYear = $("#electionYearId option:selected").text();

	document.getElementById("mainHeading").innerHTML ='Live Election Results For  '+stateName+'  In '+electionYear+'';

	var partyGainedAnalysisDivElmt = document.getElementById("partyGainedAnalysisDiv");
	partyGainedAnalysisDivElmt.innerHTML ='';
	var str ='';
	var headstr='';
	headstr+='<span class="headingstyle">Partywise Seats Gained / Lost Analysis</span>';
	document.getElementById("partyAnalysisHeading").innerHTML =headstr;

  if(myResults.length >0)
	{
	  data = checkForWonCountInNew(myResults);

	 str+='<table cellspacing="2px" cellpadding="6px" style="border:1px solid #cdcdcd;border-collapse:collapse;width:97%;">';
	for(var i in myResults)
	{
		
	if(myResults[i].totalSeatsParticipated != null && 
		myResults[i].totalSeatsParticipated > 0){
	if(i==0)
	{
		str+='<tr style="text-align:center;background:#dddddd;color:#000;font-family: verdana;font-size: 11px;">';
		str+='<th>Party</th>';
		str+='<th>No.of Seats Participated</th>';
		str+='<th>Won/Lead Count</th>';
		
		if(myResults[i].isFirstElectionAfterDelimtation)
		{
			str+='<th>Won/Lead in old Constituency</th>';
			str+='<th>Won/Lead in New Constituency</th>';
		}

		str+='<th>Retained Seats</th>';
		str+='<th>Lost/Trail Seats</th>';
		str+='<th>Seats Gained From Other Parties</th>';
		str+='<th>Lost To Other Parties</th>';
		str+='<th>Lost Count In Prev Lost</th>';
		str+='<th>Lost Count In Prev Won</th>';
		str+='</tr>';
	}
	str+='<tr style="text-align:center;">';
	str+='<td style="color:#05A8E9;">'+myResults[i].partyName+'</td>';
	str+='<td>'+myResults[i].totalSeatsParticipated+'</td>';
	str+='<td>' +myResults[i].wonOrLeadCount+'</td>';
	
	if(myResults[i].isFirstElectionAfterDelimtation)
	{
		if(myResults[i].wonCountInOld != null || myResults[i].leadCountInOld !=null)
		{
			var wonOrLeadCountInOld = myResults[i].wonCountInOld + myResults[i].leadCountInOld;
			str+='<td>' +wonOrLeadCountInOld+'</td>';
		}
		else
			str+='<td>0</td>';

		if(myResults[i].wonCountInNew !=null || myResults[i].leadCountInNew !=null)
		{
			var wonOrLeadCountInNew = myResults[i].wonCountInNew + myResults[i].leadCountInNew;
			str+='<td>' +wonOrLeadCountInNew+'</td>';
		}
		else
			str+='<td>0</td>';
	}
	if(myResults[i].retainedCount !=null)
	{
		str+='<td>'+myResults[i].retainedCount+'</td>';
	}
	else
	{
		str+='<td>0</td>';
	}
	str+='<td>' +myResults[i].lostCount+'</td>';

	if(myResults[i].wonFromOtherParties != null)
	{
		var count=0;
		for(var j=0;j<myResults[i].wonFromOtherParties.length;j++)
			{
			  count+= myResults[i].wonFromOtherParties[j].id;
							
			}
		str+='<td>'+count+'</td>';
	}
	else
	{
		str+='<td>0</td>';
	}
	if(myResults[i].lostToOtherParties != null)
	{
		var count=0;
		for(var j=0;j<myResults[i].lostToOtherParties.length;j++)
			{
			  count+= myResults[i].lostToOtherParties[j].id;
							
			}
		str+='<td>'+count+'</td>';
		
	}
	else
	{
		str+='<td>0</td>';
	}
	str+='<td>'+myResults[i].lostCountInPrevLost+'</td>';
	str+='<td>'+myResults[i].lostCountInPrevWon+'</td>';

	str+='</tr>';

	}
   }
	str+='</table>';
	partyGainedAnalysisDivElmt.innerHTML =str;
	
	
  }
}

function showPartyLossResults(myResults){
	
	var data= new Array();
	var partyGainedAnalysisDivElmt = document.getElementById("partyLostAnalysisDiv");
	partyGainedAnalysisDivElmt.innerHTML ='';
	var str ='';
	var headstr='';
	headstr+='<span>Seats To Lost Other Parties</span>';
	document.getElementById("partyLostAnalysisHeading").innerHTML =headstr;

  if(myResults.length >0)
	{
	   str+='<table>';
	  str+='<th>Party</th>';
	  str+='<th>Lost To Parties</th>';
	 for(var i in myResults)
	 {
		 str+='<tr>';
		 str+='<td>'+myResults[i].partyName+'</td>';
	
	if(myResults[i].lostToOtherParties != null && myResults[i].lostToOtherParties.length !=0)
	{
		str+='<td>'
		for(var j=0;j<myResults[i].lostToOtherParties.length;j++)
		{
		data.push([myResults[i].partyName, myResults[i].lostToOtherParties[j].name,myResults[i].lostToOtherParties[j].id]);

		str+=''+myResults[i].lostToOtherParties[j].name+' : '+myResults[i].lostToOtherParties[j].id+'';
		str+='<br />';
		
		
		}
		str+='</td>';
	}
	 str+='</tr>';
	
	}
	
	str+='</table>';

	partyGainedAnalysisDivElmt.innerHTML =str;
	 }
	//console.log(data);
	//CreateDetailView(myResults);
}

function checkForWonCountInNew(myResults) {

var checkForWonCountInNew =new Array();
     for(var i in myResults){
		if(myResults[i].wonCountInNew !=null){
		var checkForWonCountInNewObj ={
				wonCountInNew :myResults[i].wonCountInNew
		};
		checkForWonCountInNew.push(checkForWonCountInNewObj);
	  }
	 }
	 return checkForWonCountInNew;
  } 

 
function CreateDetailView(myResults) {
	
	for(var i in myResults){
	if(myResults[i].lostToOtherParties != null && myResults[i].lostToOtherParties.length !=0)
	{
		str+='<td>'
		for(var j=0;j<myResults[i].lostToOtherParties.length;j++)
		{
		data.push([myResults[i].partyName, myResults[i].lostToOtherParties[j].name,myResults[i].lostToOtherParties[j].id]);

		str+=''+myResults[i].lostToOtherParties[j].name+' : '+myResults[i].lostToOtherParties[j].id+'';
				
		}
	}
  }
}
</script>
</body>
</html>