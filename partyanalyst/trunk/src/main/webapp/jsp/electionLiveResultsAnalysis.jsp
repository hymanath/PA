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
	padding: 4px;
	font-weight: bold;
	font-family: verdana;
	width: 98px;
	-webkit-border-radius: 3px;
	padding-right: 142px;
	padding-left: 6px;
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
<div style="background:#EEF4F6;width: 950px;margin-top:12px;">
<span style="color:#990099;font-weight:bold;font-size:15px;margin:10px;">Live Election Results Analysis</span>
 <div style="margin-bottom: 30px;padding-top: 30px;font-family:verdana;">
  <input type="radio" value="2" name="electionTypeRadio" id="assemblyId" onclick="getStates(this.value)">&nbsp;<strong>Assembly </strong>
	<span style="padding-left:12px;font-weight:bold;">
	 <input type="radio" value="1" name="electionTypeRadio" id="parliamentId" onclick="getStates(this.value)" >
	 &nbsp;Parliament</span>
</div>
<div>Select State :
<select  id="stateId" onchange="getElectionYears()" style="width:150px;">
<option value="0">Select State</option>
</select>
Select Election Year : 
<select id="electionYearId" onchange="getConstituenciesCount();getPartyWinningOrLeadingConstituenciesCount();getPartiesGainAndLossInfo();" style="width:150px;">
<option value="0">Select Election Year</option>
</select>
</div>

<div id="overViewDiv" style="height: 50px;">
 </div>


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

<table width="100%"><tr><td width="50%">
<div id="partyAnalysisHeading" style="margin-left:15px;margin-bottom:10px;"></div>
<div id="partyGainedAnalysisDiv" style="text-align:left;"></div>
</td>
<td width="50%" valign="top"><div id="partyLostAnalysisHeading" style="margin-left:15px;margin-bottom:10px;"></div>
<div id="partyLostAnalysisDiv" style="text-align:left;"></div>
</td>
</tr></table>
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
					buildPartyWonOrLeadConstituenciesCount(myResults);
				  else
					  buildPartyWonConstituenciesTable(myResults);
			  }
			  else if(jObj.task =="getPartiesGainAndLossInfo"){
					showPartyGainedResults(myResults);
					showPartyLossResults(myResults);
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
debugger;
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
document.getElementById('wonResultHeading').innerHTML ="Partywise Results Winning Constituencies"; 

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
	var overViewDiv=document.getElementById("overViewDiv");
	var str='';
	if(results[i].totalSeats != null)
	{
		str +='<div style="margin-top: 32px; border-right-width: 0px; border-left-width: 0px; padding-left: 0px; padding-right: 310px;">          Total Seats - '+results[i].totalSeats+'</div>';

	if(results[i].countOfLeadConstituences !=null && results[i].countOfLeadConstituences !=0)
	{
		str +='<div style="margin: -18px 0px 0px 276px; border-right-width: 0px;">Known Result/Leading Constituencies - '+results[i].countOfLeadConstituences+'</div>';
	}
	if(results[i].oldConstituenciesCount !=null && results[i].oldConstituenciesCount >0)
	{
		str +='<div style="margin: 20px 344px 0px 0px;">Old Constituencies - '+results[i].oldConstituenciesCount+'</div>';
	}
	if(results[i].newConstituenciesCount !=null && results[i].newConstituenciesCount !=0)
	{
		str +='<div style="margin: -20px 0px 0px 333px;">New Constituencies - '+results[i].newConstituenciesCount+'</div>';
	}

	overViewDiv.innerHTML = str;
	}
  }

}

function getPartiesGainAndLossInfo()
{
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
	debugger;
	var partyGainedAnalysisDivElmt = document.getElementById("partyGainedAnalysisDiv");
	partyGainedAnalysisDivElmt.innerHTML ='';
	var str ='';
	var headstr='';
	headstr+='<span class="headingstyle">Partywise Seats Gained Analysis</span>';
	document.getElementById("partyAnalysisHeading").innerHTML =headstr;

  if(myResults.length >0)
	{
	  
	for(var i in myResults)
	{
	str+='<div class="wonContainer" >';
	str+='<div style="color:#05A8E9;">Party  :'+myResults[i].partyName+'';
	str+='<span style="padding-left: 72px;padding-right: 20px;">Total Seats Participated  :'+myResults[i].totalSeatsParticipated+'</span>';
	str+='<span>Won/Lead Seats  :' +myResults[i].wonOrLeadCount+'</span>';
	str+='</div>';

	if(myResults[i].retainedCount !=null)
	{
		str+='<div style="color:#05A8E9;"> Retained Seats  : '+myResults[i].retainedCount+'';
		str+='</div>';
	}
	else
	{
		str+='<div style="color:#05A8E9;"> Retained Seats  : 0';
		str+='</div>';
	}

	if(myResults[i].retainedCount !=null)
	{
		var seats = myResults[i].wonOrLeadCount-myResults[i].retainedCount;
		//alert(seats);
		str+='<div style="color:#05A8E9;margin-top: 13px;margin-bottom: 10px;width:50%;"> Seats Gained From Other Parties  : '+seats+'';
		str+='</div>';
	}
	else 
	{
		var seats = myResults[i].wonOrLeadCount-0;
		//alert(seats);
		str+='<div style="color:#05A8E9;margin-top: 13px;margin-bottom: 10px;"> Seats Gained From Other Parties  : '+seats+'';
		str+='</div>';
	}
	

	if(myResults[i].wonFromOtherParties != null && myResults[i].wonFromOtherParties.length !=0 )
	{
		str+='<table valign="top" style="border: 1px solid #cdcdcd;width:29%;   border-collapse: collapse;">';
		for(var j=0;j<myResults[i].wonFromOtherParties.length;j++)
		{
			
		if(j==0)
		{
			str+='<tr style="background:#cdcdcd;"><th>Party</th><th>Seats</th>';
			str+='</tr>';
		}
		str+='<tr><td>'+myResults[i].wonFromOtherParties[j].name+' :</td>';
		str+='<td>'+myResults[i].wonFromOtherParties[j].id+'</td>';
		str+='</tr>';

		
		}
		str+='</table>';
	}
	str+='<div id="partyGainedDivChart'+i+'" style="position: relative; width: 290px;bottom: 0px;left: 133px;top: -45px;">';
	str+='</div>';

		str+='</div>';

	partyGainedAnalysisDivElmt.innerHTML =str;
	
	}
	/**  For Chart **/

	for(var k=0 ; k<myResults.length; k++)
	{
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'PartyName');
		data.addColumn('number', 'Id');
		data.addRows(myResults[k].wonFromOtherParties.length+1);
				
		if(myResults[i].wonFromOtherParties != null && myResults[k].wonFromOtherParties.length !=0)
		{

			for(var j=0;j<myResults[k].wonFromOtherParties.length;j++)
			{
				data.setValue(j, 0, myResults[k].wonFromOtherParties[j].name);
				data.setValue(j, 1, parseInt(myResults[k].wonFromOtherParties[j].id));
				
			}
			data.setValue(myResults[k].wonFromOtherParties.length, 0, "Retained Seats");
			data.setValue(myResults[k].wonFromOtherParties.length, 1, parseInt(myResults[k].retainedCount));
		
			var chart = new google.visualization.PieChart(document.getElementById('partyGainedDivChart'+k+''));
			chart.draw(data, {width: 290, height: 220, title: 'Party Wise Seats Gained From Other Parties'});
		}
    }
	/*End*/
	}
}

function showPartyLossResults(myResults){
	debugger;
	var partyGainedAnalysisDivElmt = document.getElementById("partyLostAnalysisDiv");
	partyGainedAnalysisDivElmt.innerHTML ='';
	var str ='';
	var headstr='';
	headstr+='<span class="headingstyle">Partywise Seats Lost Analysis</span>';
	document.getElementById("partyLostAnalysisHeading").innerHTML =headstr;

  if(myResults.length >0)
	{
	  
	for(var i in myResults)
	{
	str+='<div class="lostContainer" >';
	str+='<div style="color:#05A8E9;">Party  :'+myResults[i].partyName+'';
	str+='<span style="padding-left: 72px;padding-right: 20px;">Total Seats Participated  :'+myResults[i].totalSeatsParticipated+'</span>';
	str+='<span>Won/Lead Seats  :' +myResults[i].lostCount+'</span>';
	str+='</div>';

	if(myResults[i].retainedCount !=null)
	{
		str+='<div style="color:#05A8E9;"> Retained Seats  : '+myResults[i].retainedCount+'';
		str+='</div>';
	}
	else
	{
		str+='<div style="color:#05A8E9;"> Retained Seats  : 0';
		str+='</div>';
	}

	if(myResults[i].retainedCount !=null)
	{
		var seats = myResults[i].wonOrLeadCount-myResults[i].retainedCount;
		//alert(seats);
		str+='<div style="color:#05A8E9;margin-top: 13px;margin-bottom: 10px;width:50%;"> Seats Gained From Other Parties  : '+seats+'';
		str+='</div>';
	}
	else 
	{
		var seats = myResults[i].wonOrLeadCount-0;
		//alert(seats);
		str+='<div style="color:#05A8E9;margin-top: 13px;margin-bottom: 10px;"> Seats Gained From Other Parties  : '+seats+'';
		str+='</div>';
	}
	

	if(myResults[i].lostToOtherParties != null && myResults[i].lostToOtherParties.length !=0 )
	{
		str+='<table valign="top" style="border: 1px solid #cdcdcd;width:29%;   border-collapse: collapse;">';
		for(var j=0;j<myResults[i].lostToOtherParties.length;j++)
		{
			
		if(j==0)
		{
			str+='<tr style="background:#cdcdcd;"><th>Party</th><th>Seats</th>';
			str+='</tr>';
		}
		str+='<tr><td>'+myResults[i].lostToOtherParties[j].name+' :</td>';
		str+='<td>'+myResults[i].lostToOtherParties[j].id+'</td>';
		str+='</tr>';

		
		}
		str+='</table>';
	}
	str+='<div id="partyLostDivChart'+i+'" style="position: relative; width: 290px;bottom: 0px;left: 133px;top: -45px;">';
	str+='</div>';

		str+='</div>';

	partyGainedAnalysisDivElmt.innerHTML =str;
	
	}
	/**  For Chart **/

	for(var k=0 ; k<myResults.length; k++)
	{
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'PartyName');
		data.addColumn('number', 'Id');
		data.addRows(myResults[k].lostToOtherParties.length+1);
				
		if( myResults[i].lostToOtherParties != null && myResults[k].lostToOtherParties.length !=0)
		{

			for(var j=0;j<myResults[k].lostToOtherParties.length;j++)
			{
				data.setValue(j, 0, myResults[k].lostToOtherParties[j].name);
				data.setValue(j, 1, parseInt(myResults[k].lostToOtherParties[j].id));
				
			}
			data.setValue(myResults[k].lostToOtherParties.length, 0, "Retained Seats");
			data.setValue(myResults[k].lostToOtherParties.length, 1, parseInt(myResults[k].retainedCount));
		
			var chart = new google.visualization.PieChart(document.getElementById('partyLostDivChart'+k+''));
			chart.draw(data, {width: 290, height: 220, title: 'Party Wise Seats Gained From Other Parties'});
		}
    }
	/*End*/
	}
}
</script>
</body>
</html>