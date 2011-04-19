<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Election Result Page</title>


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
<style type="text/css">
.detailsHead
		{
			color:#247CD4;
			font-size:19px;
			font-weight:bold;
			padding:10px;
			text-align:center;
		}
		
.yui-skin-sam .yui-dt table
 {
		border-collapse:separate;
		border-spacing:0;
		color:highlight;
		font-family:arial;
		font-size:12px;
		margin:0;
		padding:0;
}
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a {
			color:SlateGray;
			font-weight:bold;
			text-decoration:none;
			vertical-align:bottom;
}
</style>

<script type="text/javascript">
var muncipalityId = '${muncipalityId}';
var eleType = '${muncipalityElectionType}';
var electionYear = '${electionYear}'; 
var muncipalityName = '${name}';
var errorMessage = '${dataAvailabilityFlag}';
var tehsilDetails={
		winnersArray:[],
		partiesList:[]
};
var myDataTableForTehsil,partyId;
var winners =  "winners";
var allCandidates = "allCandidates";
var partyWise = "partyWise";
var selectedPartyId;
function callAjax(param,jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {												
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllCandidates")
					{	
						if(results.resultStatus.resultCode==0){
							showAllCandidates(results);
						}else{
							errorMsgDiv();	
						}
					}			
					if(jsObj.task == "getWinners")
					{	
						if(results.resultStatus.resultCode==0){
							showWinners(results);
						}else{
							errorMsgDiv();	
						}
					}
					if(jsObj.task == "allCandidates")
					{
						if(results.resultStatus.resultCode==0){
							showPartyWise(results);
						}else{
							errorMsgDiv();	
						}
					}	
					if(jsObj.task == "getParties")
					{
						if(results.resultStatus.resultCode==0){
							showPartys(results);
						}else{
							errorMsgDiv();	
						}
					}		
			}catch (e) {   		
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

function errorMsgDiv(){
	var errorMsg = document.getElementById("winnersInfoDiv");
	var msg='';
	msg += '<b>'+errorMessage+'&nbsp;</b>';
	errorMsg.innerHTML += msg;	
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
			key : "reservationZone"
		}, {
			key : "votesPolled"
		}, {	
			key : "votesEarned"
		}, {
			key : "votesPercentage"
		}, {
			key : "votesDifference"
		}, {
			key : "marginVotesPercentage"
		}, {
			key : "rank"
		}, {
			key : "partyFlag"			
		}, {
			key : "completeResults"
		}]
	};

	var resultsColumnDefsForTehsil = [ {
		key : "candidateName",
		label : "Candidate Name",
		sortable : true
	}, {
		key : "tehsilName",
		label : "Ward",
		sortable : true
	}, {
		key : "reservationZone",
		label : "Reservation Zone",
		sortable : true
	}, {
		key : "votesPolled",
		label : "Polled Votes",
		sortable : true
	}, {
		key : "votesEarned",
		label : "Earned Votes",
		sortable : true
	}, {
		key : "votesPercentage",
		label : "Votes %",
		sortable : true
	}, {
		key : "votesDifference",
		label : "Votes Difference",
		sortable : true
	}, {
		key : "marginVotesPercentage",	
		label : "Margin Votes %",
		sortable : true
	}, {
		key : "rank",
		label : "Rank",
		sortable : true
	}, {
		key : "partyFlag",
		label : "Party Flag",
		sortable : true			
	} , {
		key : "completeResults",
		label : "Complete Results"
	} ];

	var myConfigsForTehsil = {
    paginator : new YAHOO.widget.Paginator({
        rowsPerPage: 10
    })
};
	 myDataTableForTehsil = new YAHOO.widget.DataTable("winnersInfoDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);

	 var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	 ajaxImgElmt.style.display = "none";  

}


function showPartys(results)
{	
	 var imgElmt = document.getElementById("showParties");
	 imgElmt.innerHTML="";
	 
	 if(myDataTableForTehsil){
		 myDataTableForTehsil.destroy();
		}
	var showParties = document.getElementById("showParties");
	var populateParties='';
	populateParties+='<select id="tehsilParties" style="width:80px;" onchange="partyWiseCandidateDetails(this.options[this.selectedIndex].value)">';
	populateParties+='<option value="select">select</option>';
	for(var i in results.partyInfo)
	{
		populateParties+='<option value="'+results.partyInfo[i].id+'">'+results.partyInfo[i].name+'</option>';
	}
	populateParties+='</select>';
	showParties.innerHTML = populateParties;
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
						reservationZone:results.allVotersDetails[i].reservationZone,
						votesPolled:results.allVotersDetails[i].votesPolled,
						votesEarned:results.allVotersDetails[i].votesEarned, 	
						votesDifference:results.allVotersDetails[i].votesDifference,	
						votesPercentage:results.allVotersDetails[i].votesPercentage,
						marginVotesPercentage:results.allVotersDetails[i].marginVotesPercentage,
						rank:results.allVotersDetails[i].rank,			
						completeResults: '<A href="javascript:{}" onclick="getMoreDetails('+results.allVotersDetails[i].constituencyId+',\''+results.allVotersDetails[i].electionType+'\','+results.allVotersDetails[i].electionYear+')">More Details</A>',	
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


function getMoreDetails(constiId,elecType,elecYear)
{	
	var url = "<%=request.getContextPath()%>/constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear;

 	var browser1 = window.open(url,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
 	browser1.focus();
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
				reservationZone:results.allVotersDetails[i].reservationZone,
				votesPolled:results.allVotersDetails[i].votesPolled,
				votesEarned:results.allVotersDetails[i].votesEarned, 	
				votesDifference:results.allVotersDetails[i].votesDifference,	
				votesPercentage:results.allVotersDetails[i].votesPercentage,
				marginVotesPercentage:results.allVotersDetails[i].marginVotesPercentage,
				rank:results.allVotersDetails[i].rank,	
				completeResults: '<A href="javascript:{}" onclick="getMoreDetails('+results.allVotersDetails[i].constituencyId+',\''+results.allVotersDetails[i].electionType+'\','+results.allVotersDetails[i].electionYear+')">More Details</A>',		
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
	var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	ajaxImgElmt.style.display = "block";
	assignToMptcDataArray = new Array();
	for(var i in results.allVotersDetails)
	{	
		if(selectedPartyId==results.allVotersDetails[i].partyId){
			var partyFlag = results.allVotersDetails[i].partyFlag;
			var problemObj=
			 {				 
					candidateName:results.allVotersDetails[i].candidateName,
					tehsilName:results.allVotersDetails[i].tehsilName,
					reservationZone:results.allVotersDetails[i].reservationZone,
					votesPolled:results.allVotersDetails[i].votesPolled,
					votesEarned:results.allVotersDetails[i].votesEarned, 	
					votesDifference:results.allVotersDetails[i].votesDifference,
					votesPercentage:results.allVotersDetails[i].votesPercentage,
					marginVotesPercentage:results.allVotersDetails[i].marginVotesPercentage,	
					rank:results.allVotersDetails[i].rank,			
					completeResults: '<A href="javascript:{}" onclick="getMoreDetails('+results.allVotersDetails[i].constituencyId+',\''+results.allVotersDetails[i].electionType+'\','+results.allVotersDetails[i].electionYear+')">More Details</A>',
					partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>'					
			 };
			
			assignToMptcDataArray.push(problemObj);
			tehsilDetails.winnersArray=assignToMptcDataArray;	
		}
	}

	var emptyArr = new Array();
    if(results.length == 0)
	{	
    	tehsilDetails.winnersArray = emptyArr;				
	}
    initializeResultsTableForWinners();
}

function allCandidatesForaMuncipality()
{
	var smallerCase = eleType.toLowerCase();
	smallerCase=smallerCase.replace(smallerCase[0],smallerCase[0].toUpperCase());
	eleType = smallerCase;
	selectedPartyId=0;	
	var eleTYPEDiv = document.getElementById("eleTYPE");
	var elecTYPEStr='';	
	elecTYPEStr += '<b>'+electionYear+'&nbsp;</b>';
	elecTYPEStr += '<b>'+muncipalityName+'&nbsp;</b>';	
	elecTYPEStr += '<b>'+eleType+'</b>';
	eleTYPEDiv.innerHTML = elecTYPEStr;	
	
	var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	ajaxImgElmt.style.display = "block";
	var jsObj=
	{
			partyId:0,
			muncipalityId:muncipalityId,
			electionType:eleType,
			candidateDetailsType:"allCandidates",
			task:"getAllCandidates"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/muncipalElectionReportAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function winnersForaMuncipality()
{
	var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	ajaxImgElmt.style.display = "block";
	var jsObj=
	{		
			partyId:0,
			candidateDetailsType:winners,
			muncipalityId:muncipalityId,
			electionType:eleType,
			task:"getWinners"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/muncipalElectionReportAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);	
}

function partyWiseCandidateDetails(id)
{
	selectedPartyId = id;
	var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	ajaxImgElmt.style.display = "block";
	var jsObj=
	{
			muncipalityId:muncipalityId,
			electionType:eleType,
			partyId:0,
			candidateDetailsType:"allCandidates",
			task:"allCandidates"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/muncipalElectionReportAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function getParties()
{
	var jsObj=
	{
			partyId:0,
			candidateDetailsType:"all",
			muncipalityId:muncipalityId,
			electionType:eleType,
			task:"getParties"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/muncipalElectionReportAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}
window.history.forward(1);
</script>
</head>
<body onload="allCandidatesForaMuncipality()">
<div class="detailsHead">
		<table align="center"><tr>
				<td><div id="eleTYPE"></div></td>
				<td><div id="elecYEAR"></div></td>
				<td>Candidates Election Results </td>
		</tr></table><br/>
</div>
	<table>
		<tr>
			<td style="color:#247CD4;font-weight:bold;">View By</td>
		</tr>
		<tr>
			<td style="font-weight:bold;"><input type="radio" name="location" value="candidates" onclick="allCandidatesForaMuncipality()" checked="checked"> All Candidates</td>		
			<td style="font-weight:bold;"><input style="font-weight:bold;" type="radio" name="location" value="winners" onclick="winnersForaMuncipality()"> Winners</td>
			<td style="font-weight:bold;"><input style="font-weight:bold;" type="radio" name="location" value="partyWise" onclick="getParties()"> PartyWise</td>
			<td><div id="showParties"></div></td>
		</tr>
	</table>
	<div id="ajaxLoadDiv" style="display:none;padding-top:20px;">
							<span><b>Processing Request</b> </span>
							<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/barloader.gif"/>
	</div>	
	<div class="yui-skin-sam"><div id="winnersInfoDiv"></div></div>
</body>
</html>