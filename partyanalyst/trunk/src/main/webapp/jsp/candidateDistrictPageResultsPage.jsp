<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Results</title>


<!-- YUI files dependencies (start) -->

<!--CSS files (default YUI Sam Skin) -->
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
 
<!--JS files Dependencies -->
<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/json/json-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/get/get-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

<!-- YUI files dependencies (end) -->


<script type="text/javascript">
var districtId = '${disId}';
var electionType = '${eleType}';
var electionYear = '${eleYear}'; 
var tehsilDetails={
		winnersArray:[],
		partiesList:[]
};
var myDataTableForTehsil;
function callAjax(param,jsObj,url){
	
	var results;	
	var callback = {			
	    success : function( o ) {
			try {												
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllCandidates")
					{					
						showAllCandidates(results);
					}			
					if(jsObj.task == "getWinners")
					{	
						showWinners(results);
					}
					if(jsObj.task == "getPartyWise")
					{
						showPartyWise(results);
					}	
					if(jsObj.task == "getParties")
					{
						showPartys(results);
					}		
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

function showPartys(results)
{	
	 if(myDataTableForTehsil){
		 myDataTableForTehsil.destroy();
		}
	var showParties = document.getElementById("showParties");
	var populateParties='';
	populateParties+="<b>Select Party</b> :";
	populateParties+='<select id="tehsilParties" style="width:80px;" onchange="partyWise(this.options[this.selectedIndex].value)">';
	for(var i in results.partyInfo)
	{
		populateParties+='<option value="'+results.partyInfo[i].id+'">'+results.partyInfo[i].name+'</option>';
	}
	populateParties+='</select>';
	showParties.innerHTML = populateParties;
}

function initializeResultsTableForWinners() {

	var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.winnersArray);
	resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSourceForTehsil.responseSchema = {
		fields : [ {
			key : "candidateName"
		}, {
			key : "tehsilName"
		}, {
			key : "polledVotes"
		}, {
			key : "votesEarned"
		}, {
			key : "votesDifference"
		}, {
			key : "rank"
		}, {
			key : "partyFlag"
		}]
	};

	var resultsColumnDefsForTehsil = [ {
		key : "candidateName",
		label : "Candidate Name",
		sortable : true
	}, {
		key : "tehsilName",
		label : "Mandal Name",
		sortable : true
	}, {
		key : "polledVotes",
		label : "Polled Votes",
		sortable : true
	}, {
		key : "votesEarned",
		label : "Earned Votes",
		sortable : true
	}, {
		key : "votesDifference",
		label : "Votes Difference",
		sortable : true
	}, {
		key : "rank",
		label : "Rank",
		sortable : true
	}, {
		key : "partyFlag",
		label : "Party Flag",
		sortable : true
	} ];

	var myConfigsForTehsil = {
    paginator : new YAHOO.widget.Paginator({
        rowsPerPage: 10
    })
};
	 myDataTableForTehsil = new YAHOO.widget.DataTable("winnersInfoDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);  
}

function showAllCandidates(results)
{
	 var imgElmt = document.getElementById("showParties");
	 imgElmt.innerHTML="";

		assignToMptcDataArray = new Array();
		for(var i in results.allVotersDetails)
		{		
			var partyFlag = results.allVotersDetails[i].partyFlag;
			var problemObj=
			 {				 
					candidateName:results.allVotersDetails[i].candidateName,
					tehsilName:results.allVotersDetails[i].tehsilName,
					polledVotes:results.allVotersDetails[i].polledVotes,
					votesEarned:results.allVotersDetails[i].votesEarned, 	
					votesDifference:results.allVotersDetails[i].votesDifference,	
					rank:results.allVotersDetails[i].rank,			
					partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>'					
			 };
			
			assignToMptcDataArray.push(problemObj);
			tehsilDetails.winnersArray=assignToMptcDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.winnersArray = emptyArr;				
		}
	    initializeResultsTableForWinners();  
}

function showWinners(results)
{
	 var imgElmt = document.getElementById("showParties");
	 imgElmt.innerHTML="";
	 
	assignToMptcDataArray = new Array();
	for(var i in results.allVotersDetails)
	{		
		var partyFlag = results.allVotersDetails[i].partyFlag;
		var problemObj=
		 {				 
				candidateName:results.allVotersDetails[i].candidateName,
				tehsilName:results.allVotersDetails[i].tehsilName,
				polledVotes:results.allVotersDetails[i].polledVotes,
				votesEarned:results.allVotersDetails[i].votesEarned, 	
				votesDifference:results.allVotersDetails[i].votesDifference,	
				rank:results.allVotersDetails[i].rank,			
				partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>'					
		 };
		
		assignToMptcDataArray.push(problemObj);
		tehsilDetails.winnersArray=assignToMptcDataArray;	
	}

	var emptyArr = new Array();
    if(results.length == 0)
	{	
    	tehsilDetails.winnersArray = emptyArr;				
	}
    initializeResultsTableForWinners(); 
}

function showPartyWise(results)
{
	assignToMptcDataArray = new Array();
	for(var i in results.allVotersDetails)
	{		
		var partyFlag = results.allVotersDetails[i].partyFlag;
		var problemObj=
		 {				 
				candidateName:results.allVotersDetails[i].candidateName,
				tehsilName:results.allVotersDetails[i].tehsilName,
				polledVotes:results.allVotersDetails[i].polledVotes,
				votesEarned:results.allVotersDetails[i].votesEarned, 	
				votesDifference:results.allVotersDetails[i].votesDifference,	
				rank:results.allVotersDetails[i].rank,			
				partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>'					
		 };
		
		assignToMptcDataArray.push(problemObj);
		tehsilDetails.winnersArray=assignToMptcDataArray;	
	}

	var emptyArr = new Array();
    if(results.length == 0)
	{	
    	tehsilDetails.winnersArray = emptyArr;				
	}
    initializeResultsTableForWinners();
}
function allCandidates()
{
	var jsObj=
	{
			districtId:districtId,
			electionType:electionType,
			electionYear:electionYear,
			task:"getAllCandidates"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/tehsilPageAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function winners()
{
	var jsObj=
	{	
			districtId:districtId,
			electionType:electionType,
			electionYear:electionYear,
			task:"getWinners"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/tehsilPageAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function partyWise(id)
{
	var jsObj=
	{
			partyId:id,
			districtId:districtId,
			electionType:electionType,
			electionYear:electionYear,
			task:"getPartyWise"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/tehsilPageAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function getParties()
{
	var jsObj=
	{
			districtId:districtId,
			electionType:electionType,
			electionYear:electionYear,
			task:"getParties"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/tehsilPageAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}
</script>
</head>
<body onload="allCandidates()">
	<table>
		<tr>
			<td>View By</td>
		</tr>
		<tr>
			<td><input  type="radio" name="location" value="candidates" onclick="allCandidates()" checked="checked"> All Candidates</td>		
			<td><input  type="radio" name="location" value="winners" onclick="winners()"> Winners</td>
			<td><input  type="radio" name="location" value="partyWise" onclick="getParties()"> PartyWise</td>
			<td><div id="showParties"></div>
		</tr>
	</table>
	<div class="yui-skin-sam"><div id="winnersInfoDiv"></div></div>
</body>
</html>