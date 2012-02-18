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
.yui-dt-sortable{
	color:#000;
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
<div style="background:#EEF4F6;width: 800px;margin-top: 12px;">
 <div style="margin-bottom: 30px;padding-top: 30px;">
  <input type="radio" value="2" name="electionTypeRadio" id="assemblyId" onclick="getStates(this.value)">&nbsp;<strong>Assembly </strong>
	<span style="padding-left:12px;font-weight:bold;">
	 <input type="radio" value="1" name="electionTypeRadio" id="parliamentId" onclick="getStates(this.value)" >
	 </span>&nbsp;Parliament
</div>
<div>Select State :
<select  id="stateId" onchange="getElectionYears()" width="150px">
<option value="0">Select State</option>
</select>
Select Election Year : 
<select id="electionYearId" onchange="getConstituenciesCount();getPartyWinningOrLeadingConstituenciesCount();" width="150px">
<option value="0">Select Election Year</option>
</select>
</div>

<div id="overViewDiv" style="height: 50px;">
 </div>


<div>
 <div id="partyOldResultsDiv" class="yui-skin-sam" style="float: left;margin-left:110px;"></div>
<div id="partyNewResultsDiv" class="yui-skin-sam"></div>
<div id="partyWonResultsDiv" class="yui-skin-sam"></div>
</div>
</div>
 </center>
 
<script type="text/javascript">


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
				showOverView(myResults);
			}
			  else if(jObj.task == "getPartyWonOrLeadConstituenciesCount"){
				  console.log(myResults[0].partialResult);
				  if(myResults[0].partialResult)
					buildPartyWonOrLeadConstituenciesCount(myResults);
				  else
					  buildPartyWonConstituenciesTable(myResults);
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
	
	var newConstituencyArray = new Array();
	var oldConstituencyArray = new Array();
	for (var i in myResults){
	
		var newResultObj  = {

							partyName :myResults[i].partyName,
							leadCountInNew:myResults[i].leadCountInNew,
							wonCountInNew :myResults[i].wonCountInNew
		};
		newConstituencyArray.push(newResultObj);

			var oldResultObj = {
							partyName :myResults[i].partyName,
							leadCountInOld:myResults[i].leadCountInOld,
							wonCountInOld :myResults[i].wonCountInOld
			};
		oldConstituencyArray.push(oldResultObj);

	
	}
	buildOldDataTable(myResults,oldConstituencyArray);
	buildNewDataTable(myResults,newConstituencyArray);
	
}

function buildNewDataTable(myResults,newConstituencyArray,divElmt){
debugger;
document.getElementById("partyNewResultsDiv").innerHTML = '';
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

				
	var myDataTable = new YAHOO.widget.DataTable("partyNewResultsDiv",resultsColumnDefs, myDataSource,myConfigs);  


}
function buildOldDataTable(myResults,oldConstituencyArray,divElmt){
debugger;
document.getElementById("partyOldResultsDiv").innerHTML = '';

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

	
	var myDataTable = new YAHOO.widget.DataTable("partyOldResultsDiv",resultsColumnDefs, myDataSource,myConfigs);  
	

}
function buildPartyWonConstituenciesTable(myResults){
	debugger;
		document.getElementById("partyNewResultsDiv").innerHTML = '';
	document.getElementById("partyOldResultsDiv").innerHTML = '';
		document.getElementById("partyWonResultsDiv").innerHTML = '';


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
	}

	if(oldConstituencyArray.length>0)
		buildDatatableForOldResults(oldConstituencyArray);

	if(newConstituencyArray.length>0)
		buildDataTableForNewResults(newConstituencyArray);

if(myResults[0].countOfWinningConstituencies !=null){
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
		var myDataSource = new YAHOO.util.DataSource(myResults);
		myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						fields : [ "partyName","countOfWinningConstituencies" ]
					};

	
	var myDataTable = new YAHOO.widget.DataTable("partyWonResultsDiv",resultsColumnDefs, myDataSource,myConfigs); 
 }

}
function buildDataTableForNewResults(newConstituencyArray){
	debugger;

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
}
function buildDatatableForOldResults(oldConstituencyArray){

document.getElementById("partyOldResultsDiv").innerHTML = '';
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
	
	var overViewDiv=document.getElementById("overViewDiv");
	var str='';
	str +='<div style="margin-top: 32px; border-right-width: 0px; border-left-width: 0px; padding-left: 0px; padding-right: 310px;">Total Seats - '+results.totalSeats+'</div>';
	if(results.countOfLeadConstituences !=0){
	str +='<div style="margin: -18px 0px 0px 276px; border-right-width: 0px;">Known Result/Leading Constituencies - '+results.countOfLeadConstituences+'</div>';
	}
	str +='<div style="margin: 20px 344px 0px 0px;">Old Constituencies - '+results.oldConstituenciesCount+'</div>';
	str +='<div style="margin: -20px 0px 0px 333px;">New Constituencies - '+results.newConstituenciesCount+'</div>';

	overViewDiv.innerHTML = str;

}
</script>
</body>
</html>