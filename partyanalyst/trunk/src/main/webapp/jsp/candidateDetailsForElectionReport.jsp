<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>${year} ${electionType} Election Participated Candidates Details</TITLE>
<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/candiateDetailsForElectionReport.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable1.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css"> 
<!-- Dependencies -->
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>
<SCRIPT type="text/javascript">
var electionType = '${electionType}';
var stateID =  '${stateID}' ;
var stateName = '${stateName}';
var year = '${year}';
var candidateDetailsObj={
		candidateDetailsArr:[],
		partiesArr:[],
		statesArr:[],
		distArr:[]
};
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
									
								if(jsObj.task == "getAllCandidates")
								{																
									showCandidates(myResults);											
								}
							}
						catch (e) {   
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
function partywiseClickHandler()
{
	var regionSelect = document.getElementsByName("regionSelect");
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}
}

function selectPartyOnchangeHandler(value)
{
	var allCandidatesRadio = document.getElementById("allCandidates");
	var wonCandidatesRadio = document.getElementById("wonCandidates");
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
			
	if(allCandidatesRadio.checked == true)
	{
		allCandidatesRadio.checked = false;
	}
	if(wonCandidatesRadio.checked == true)
	{
		wonCandidatesRadio.checked = false;
	}
	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
	}
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}
	
}

function allCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");

	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
	}
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}	
}

function wonCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");

	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
	}
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}		
}

function countryLevelPClickHandler()
{
	var selectStatePEl = document.getElementById("selectStateP");
	if(selectStatePEl.style.display == "block")
	{		
		selectStatePEl.style.display = 'none';
	}	
}

function stateLevelPClickHandler()
{
	var selectStatePEl = document.getElementById("selectStateP");
	if(selectStatePEl.style.display == "none")
	{		
		selectStatePEl.style.display = 'block';
	}
}

function stateLevelAClickHandler()
{
	var regionSelect = document.getElementsByName("regionSelect");
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}
	
}
function distLevelAClickHandler()
{
	var selectdistrictAEl = document.getElementById("selectdistrictA");
	if(selectdistrictAEl.style.display == "none")
	{		
		selectdistrictAEl.style.display = 'block';
	}	
}

function buildParticipatedCandidatesDetailsDataTable(data)
{
	
	var participatedCandidatesDetailsColumnDefs = [
								{key: "count", label: "S No",formatter:"number", sortable:true},	
								{key: "name", label: "Name", sortable:true},		
								{key: "constituency", label: "Constituency", sortable:true},	
								{key: "party", label: "Party", sortable:true},
								{key: "partyFlag", label: "Party Flag"},
								{key: "votesEarned", label: "Votes Earned",formatter:"number", sortable:true},
								{key: "votesPercentage", label: "Votes %", formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},		
								{key: "status", label: "Status", formatter:"number", sortable:true},	
		              	 	    {key: "marginVotes", label: "Margin Votes",formatter:"number", sortable:true},
		              	 	 	{key: "marginVotesPercentage", label: "Margin Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true}		              	 	 			              	 	 	
		              	 	    ];                	 	    

		var participatedCandidatesDetailsDataSource = new YAHOO.util.DataSource(data); 
		participatedCandidatesDetailsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		participatedCandidatesDetailsDataSource.responseSchema = {
                fields: [ {key: "count", parser:"number"},"name", "constituency", "party", "partyFlag",
                         {key:  "votesEarned", parser:"number"},
                		  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                		  {key: "status", parser:"number"},
                		  {key: "marginVotes", parser:YAHOO.util.DataSourceBase.parseNumber},
                		  {key: "marginVotesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber} ]    
                         		   
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
				rowsPerPage    : 50			        
			    }),
			    caption:"All Participated Candidates Details" 
				};
		
		var participatedCandidatesDetailsDataTable = new YAHOO.widget.DataTable("participatedCandidatesDetailsDataTable", participatedCandidatesDetailsColumnDefs, participatedCandidatesDetailsDataSource,myConfigs);
					
            	
	
}
function allCandidates()
{
	
	var electionLevel;
	var partywiseRadioEl = document.getElementById("partywise");
	var allCandidatesRadioEl = document.getElementById("allCandidates");
	var wonCandidatesRadioEl = document.getElementById("wonCandidates");
	var stateLevelAEl = document.getElementById("stateLevelA");
	var distLevelAEl = document.getElementById("distLevelA");
	var countryLevelPEl =  document.getElementById("countryLevelP");
	var stateLevelPEl = document.getElementById("stateLevelP");
	var selectdistrictAEl = document.getElementById("selectdistrictA"); 
	var selectStatePEl = document.getElementById("selectStateP");
	var selectPartyEl = document.getElementById("selectParty");
	
	var partyId;
	var locationId;
	var resultsCategory;   

	if(partywiseRadioEl.checked == true)
	{
		partyId = selectPartyEl.value;
	} else{
		partyId = 0;
		}
 	
	if(allCandidatesRadioEl.checked == true)
	{
		resultsCategory = allCandidatesRadioEl.value;
	} else {
		resultsCategory = wonCandidatesRadioEl.value;
		}	

	if(electionType == 'Assembly')
	{
		if(stateLevelAEl.checked == true)
		{
			electionLevel = stateLevelAEl.value;
			locationId = 0;
		} 
		if(distLevelAEl.checked == true)
		{   
			electionLevel =distLevelAEl.value;
			locationId=selectdistrictAEl.value;
		}		
	}
	if(electionType == 'Parliament')
	{
		if(countryLevelPEl.checked == true)
		{
			electionLevel = countryLevelPEl.value;
			locationId = 0;
		} 
		if(stateLevelPEl.checked == true)
		{   
			electionLevel =stateLevelPEl.value;
			locationId=selectStatePEl.value;
		}		
	}
	if(electionType == 'ZPTC')
	{
			electionLevel = null;
			locationId = 0;				
	}
	
	var jsObj=		
	{		
			electionType:electionType,
			stateID:stateID,
			year : year,
			partyId: partyId,
			electionLevel: electionLevel,
			resultsCategory: resultsCategory,
			locationId: locationId,
			task:"getAllCandidates"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/candidateDetailsForElectionDetailsReportAjaxAction.action?"+rparam;		
			
	callAjax(rparam,jsObj,url);
}
function showCandidates(results)
{
	var assignTocandidateDetailsArr = new Array();
	var candidateDetails = results.candidateDetails;
	var count=0;
	for(var i in candidateDetails)
	{		
		var partyFlag = results.candidateDetails[i].partyFlag;
		count = count + 1;
		var candidateDetailsObj = {
				
				count: count, 
				name: candidateDetails[i].candidateName,
				constituency: candidateDetails[i].constituencyName,
				party: candidateDetails[i].partyName,
				partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>',
				votesEarned: candidateDetails[i].votesEarned,
				votesPercentage: candidateDetails[i].votesPercentage,
				status: candidateDetails[i].rank,
				marginVotes: candidateDetails[i].votesDifference,
				marginVotesPercentage:0
		};
		assignTocandidateDetailsArr.push(candidateDetailsObj);		
	}
	candidateDetailsObj.candidateDetailsArr = assignTocandidateDetailsArr;
	
	buildParticipatedCandidatesDetailsDataTable(candidateDetailsObj.candidateDetailsArr);
	
	
}
</SCRIPT>
</HEAD>
<BODY class="yui-skin-sam">
<CENTER>
<H3>${year} ${electionType} Election All Winning Candidates Details</H3>
<DIV id="optionsDiv" class="optionsDiv">
	<TABLE  class="optionsTable">
	<TR>
	<TD align="left" class="td"><INPUT type="radio" name="partiesOption" id="partywise" value="Partywise" onClick="partywiseClickHandler()" />Partywise Candidates Details</TD>
	<TD align="left"><s:select id="selectParty" theme="simple"  name="selectParty" cssClass="selectBoxStyle" list="partiesList" listKey="id" listValue="name" onchange="selectPartyOnchangeHandler()" /></TD>
	<!--<SELECT name="selectParty" id="selectParty" class="selectBoxStyle" onchange="selectPartyOnchangeHandler(this.options[this.selectedIndex].value)"><OPTION value="0">Select Party</OPTION><OPTION value="1">INC</OPTION><OPTION value="2">TDP</OPTION><OPTION value="3">TRS</OPTION></SELECT>
	--></TR>	
	<TR>
	<TD align="left" class="td"><INPUT type="radio" name="candidatesOption" id="allCandidates" value="allCandidates" onClick="allCandidatesClickHandler()" />All Participated Candidates Details</TD>
	<TD align="left" class="td"><INPUT type="radio" name="candidatesOption" id="wonCandidates" value="wonCandidatesOnly" onClick="wonCandidatesClickHandler()" checked="true"/>Won Candidates Details</TD>
	</TR>	
	<c:if test="${electionType == 'Parliament'}">
		<TR>
		<TD class="td"><INPUT type="radio" name="regionalRadio" id="countryLevelP" value="countrywiseParliament" onClick="countryLevelPClickHandler()" checked="true"/>Countrywise</TD>		
		<TD class="td"><INPUT type="radio" name="regionalRadio" id="stateLevelP" value="statewiseParliament" onClick="stateLevelPClickHandler()"/>Statewise</TD>
		</TR>
		<TR>
		<TD colspan="2" align="right"><s:select id="selectStateP" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="statesListObj.getAllStates" listKey="id"  listValue="name" /></TD>
		<!--<SELECT name="regionSelect"  id="selectStateP" class="selectBoxStyle" style="display:none;margin-right:25px;"><OPTION value="0">Select State</OPTION><OPTION value="1">Andhra Pradesh</OPTION><OPTION value="2">Karnataka</OPTION><OPTION value="3">Tamil Nadu</OPTION></SELECT>
		--></TR>
	</c:if>	
	 <c:if test="${electionType == 'Assembly'}">  
		<TR>
		<TD class="td"><INPUT type="radio" name="regionalRadio" id="stateLevelA" value="statewiseAssembly" onClick="stateLevelAClickHandler()" checked="true"/>Statewise</TD>		
		<TD class="td"><INPUT type="radio" name="regionalRadio" id="distLevelA" value="districtwiseAssembly" onClick="distLevelAClickHandler()"/>Districtwise</TD>
		</TR>
		<TR>
		<TD align="right" colspan="2" ><s:select id="selectdistrictA" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="margin-right:30px;display:none" /></TD>
		<!--<SELECT name="regionSelect" id="selectdistrictA" class="selectBoxStyle" style="display:none;" ><OPTION value="0">Select District</OPTION><OPTION value="1">Kurnool</OPTION><OPTION value="2">Mahaboob Nagar</OPTION><OPTION value="3">Nellore</OPTION></SELECT>
		--></TR>		
	</c:if>
	<TR>
		<TD><INPUT type="button" onclick="allCandidates()" value="UPDATE RESULTS"></TD>
	</TR>
	</TABLE>
</DIV>

<DIV id="participatedCandidatesDetailsDataTable" align="left"></DIV>

</CENTER>
<SCRIPT type="text/javascript">
allCandidates();
</SCRIPT>

</BODY>
</HTML>