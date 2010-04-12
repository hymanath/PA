<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>${year} ${electionType} Election Candidates Details</TITLE>
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
var participatedCandidatesDetailsDataTable;
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
									showCandidates(myResults,jsObj);											
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
	
	var allCandidatesRadio = document.getElementById("allCandidates");
	var wonCandidatesRadio = document.getElementById("wonCandidates");
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var selectPartyEl = document.getElementById("selectParty");
	if(regionalOptionsRow)
	{
		regionalOptionsRow.style.display = 'none';
	}	 		
	if(allCandidatesRadio.checked == true)
	{
		allCandidatesRadio.checked = false;
	}
	if(wonCandidatesRadio.checked == true)
	{
		wonCandidatesRadio.checked = false;
	}	
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}
	
	if(selectPartyEl.style.display == 'none')
	{
		selectPartyEl.style.display = 'block';
		selectPartyEl.selectedIndex = '0';
	} else 	selectPartyEl.style.display = 'none';	
}

function selectPartyOnchangeHandler(value)
{
	var allCandidatesRadio = document.getElementById("allCandidates");
	var wonCandidatesRadio = document.getElementById("wonCandidates");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var regionSelect = document.getElementsByName("regionSelect");
	if(regionalOptionsRow)
	{
		regionalOptionsRow.style.display = 'none';
	}			
	if(allCandidatesRadio.checked == true)
	{
		allCandidatesRadio.checked = false;
	}
	if(wonCandidatesRadio.checked == true)
	{
		wonCandidatesRadio.checked = false;
	}	
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{regionSelect[i].style.display = 'none';}
	}	
}

function allCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var partywiseCheckBoxEl = document.getElementById("partywiseCheckBox");
	if(regionalOptionsRow && regionalOptionsRow.style.display == 'none')
	{		
		regionalOptionsRow.style.display = '';
	}
	
	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
		if(regionalRadioBtns[i].id == 'stateLevelA' && partywiseCheckBoxEl.checked == false) 
			{regionalRadioBtns[i].disabled = true;}
		else if(regionalRadioBtns[i].id == 'stateLevelA' && partywiseCheckBoxEl.checked == true)
		{
				regionalRadioBtns[i].disabled = false;
		}
		if(regionalRadioBtns[i].id == 'countryLevelP' && partywiseCheckBoxEl.checked == false)
		{
			regionalRadioBtns[i].disabled = true;
		}else if(regionalRadioBtns[i].id == 'countryLevelP' && partywiseCheckBoxEl.checked == true)
		{
			regionalRadioBtns[i].disabled = false;
		}//wkg
		if(regionalRadioBtns[i].id == 'stateLevelZ' && partywiseCheckBoxEl.checked == false)
		{
			regionalRadioBtns[i].disabled = true;
		}else if(regionalRadioBtns[i].id == 'stateLevelZ' && partywiseCheckBoxEl.checked == true)
		{
			regionalRadioBtns[i].disabled = false;
		}
	}
	for (j=0; j< regionSelect.length; j++)
	{
		if(regionSelect[j].style.display == "block")			
		{
			regionSelect[j].style.display = 'none';}
	}	
}

function wonCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	
	if(regionalOptionsRow && regionalOptionsRow.style.display == 'none')
	{
		regionalOptionsRow.style.display = '';
	}	
	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
		if(regionalRadioBtns[i].id == 'stateLevelA')
		{regionalRadioBtns[i].disabled = false;}
		if(regionalRadioBtns[i].id == 'countryLevelP')
		{regionalRadioBtns[i].disabled = false;}
		if(regionalRadioBtns[i].id == 'stateLevelZ')
		{regionalRadioBtns[i].disabled = false;}
	}
	for (k=0; k< regionSelect.length; k++)
	{
		if(regionSelect[k].style.display == "block")			
		{
			regionSelect[k].style.display = 'none';}
	}		
}

function countryLevelPClickHandler()
{
	var selectStatePEl = document.getElementById("selectStateP");
	if(selectStatePEl.style.display == "block")
	{		
		selectStatePEl.style.display = 'none';
	}
	allCandidates();	
}

function stateLevelPClickHandler()
{
	var selectStatePEl = document.getElementById("selectStateP");
	if(selectStatePEl.style.display == "none")
	{		
		selectStatePEl.style.display = 'block';
		selectStatePEl.selectedIndex = '0';
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
	allCandidates();
}

function distLevelAClickHandler()
{
	var selectdistrictAEl = document.getElementById("selectdistrictA");
	if(selectdistrictAEl.style.display == "none")
	{		
		selectdistrictAEl.style.display = 'block';
		selectdistrictAEl.selectedIndex='0';
	}	
}
function distLevelZClickHandler()
{
	var selectdistrictZEl = document.getElementById("selectdistrictZ");
	if(selectdistrictZEl.style.display == "none")
	{		
		selectdistrictZEl.style.display = 'block';
		selectdistrictZEl.selectedIndex='0';
	}
}
function distLevelMClickHandler()
{
	var selectdistrictMEl = document.getElementById("selectdistrictM");
	if(selectdistrictMEl.style.display == "none")
	{		
		selectdistrictMEl.style.display = 'block';
		selectdistrictMEl.selectedIndex='0';
	}
}
function buildParticipatedCandidatesDetailsDataTable(data)
{
	
	var participatedCandidatesDetailsColumnDefs = [
								{key: "count", label: "S No",formatter:"number", sortable:true},	
								{key: "name", label: "Name", sortable:true},		
								{key: "constituency", label: "Constituency", sortable:true},	
								{key: "party", label: "Party", sortable:true},
								{key: "partyFlag", label: "Party Flag", sortable:true},
								{key: "votesEarned", label: "Votes Earned",formatter:"number", sortable:true},
								{key: "votesPercentage", label: "Votes %", formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},		
								{key: "rank", label: "Rank", formatter:"number", sortable:true},	
		              	 	    {key: "marginVotes", label: "Margin Votes",formatter:"number", sortable:true},
		              	 	 	{key: "marginVotesPercentage", label: "Margin Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true}		              	 	 			              	 	 	
		              	 	    ];                	 	    

		var participatedCandidatesDetailsDataSource = new YAHOO.util.DataSource(data); 
		participatedCandidatesDetailsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		participatedCandidatesDetailsDataSource.responseSchema = {
                fields: [ {key: "count", parser:"number"},"name", "constituency", "party", "partyFlag",
                         {key:  "votesEarned", parser:"number"},
                		  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                		  {key: "rank", parser:"number"},
                		  {key: "marginVotes", parser:YAHOO.util.DataSourceBase.parseNumber},
                		  {key: "marginVotesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber} ]    
                         		   
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
				rowsPerPage    : 50,
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [50,100,150,200], 
			    pageLinks: 50			        
			    }),
			    caption:"Candidates Details" 
				};
		
		participatedCandidatesDetailsDataTable = new YAHOO.widget.DataTable("participatedCandidatesDetailsDataTable", participatedCandidatesDetailsColumnDefs, participatedCandidatesDetailsDataSource,myConfigs);				
            	
	
}
function allCandidates()
{
	
	var electionLevel;
	var partywiseCheckBoxEl = document.getElementById("partywiseCheckBox");
	var allCandidatesRadioEl = document.getElementById("allCandidates");
	var wonCandidatesRadioEl = document.getElementById("wonCandidates");
	var stateLevelAEl = document.getElementById("stateLevelA");
	var distLevelAEl = document.getElementById("distLevelA");
	var countryLevelPEl =  document.getElementById("countryLevelP");
	var stateLevelPEl = document.getElementById("stateLevelP");
	var selectdistrictAEl = document.getElementById("selectdistrictA"); 
	var selectStatePEl = document.getElementById("selectStateP");
	var selectPartyEl = document.getElementById("selectParty");
	var stateLevelZEl = document.getElementById("stateLevelZ");
	var distLevelZEl = document.getElementById("distLevelZ");
	var selectdistrictZEl = document.getElementById("selectdistrictZ");
	var stateId;
	var partyId;
	var locationId;
	var resultsCategory;   

	if(partywiseCheckBoxEl.checked == true)
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
			stateId = stateID;
			locationId = 0;
		} 
		if(distLevelAEl.checked == true)
		{   
			electionLevel =distLevelAEl.value;
			locationId=selectdistrictAEl.value;
			stateId = stateID;
		}		
	}
	if(electionType == 'Parliament')
	{
		if(countryLevelPEl.checked == true)
		{
			
			electionLevel = countryLevelPEl.value;
			locationId = "1";
			stateId = 0;
		} 
		if(stateLevelPEl.checked == true)
		{   
			
			electionLevel =stateLevelPEl.value;
			stateId=selectStatePEl.value;
			locationId = "0";
		}		
	}
	if(electionType == 'ZPTC')
	{
		if(stateLevelZEl.checked == true)
		{
			electionLevel = "stateWiseZptc";
			locationId = 0;
			stateId = stateID;
		}
		if(distLevelZEl.checked == true)
		{
			electionLevel = "districtWiseZptc";
			locationId = selectdistrictZEl.value;
			stateId = stateID;
		}					
	}
	if(electionType == 'MPTC')
	{
		
			electionLevel = "stateWiseMptc";
			locationId = 0;
			stateId = stateID;				
	}
	var jsObj=		
	{		
			electionType:electionType,
			stateID: stateId,
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
function showCandidates(results,jsObj)
{
	var emptyArray = new Array();
	var assignTocandidateDetailsArr = new Array();
	var candidateDetails = results.candidateDetails;	
	var count=0;
	var errorEl = document.getElementById("error");
	if(candidateDetails.length != 0)
	{
		if(errorEl.style.display == 'block')
		{errorEl.style.display = 'none'}
		for(var i in candidateDetails)
		{		
			var partyFlag = results.candidateDetails[i].partyFlag;
			count = count + 1;
			var candidateDetailsObj1 = {
					
					count: count, 
					name: candidateDetails[i].candidateName,
					constituency: candidateDetails[i].constituencyName,
					party: candidateDetails[i].partyName,
					partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>',
					votesEarned: candidateDetails[i].votesEarned,
					votesPercentage: candidateDetails[i].votesPercentage,
					rank: candidateDetails[i].rank,
					marginVotes: candidateDetails[i].votesDifference,
					marginVotesPercentage: candidateDetails[i].marginVotesPercentage
			};
			assignTocandidateDetailsArr.push(candidateDetailsObj1);		
		}
		candidateDetailsObj.candidateDetailsArr = assignTocandidateDetailsArr;
		buildParticipatedCandidatesDetailsDataTable(candidateDetailsObj.candidateDetailsArr);
	} 
	else 	{
		candidateDetailsObj.candidateDetailsArr = emptyArray;
		if(participatedCandidatesDetailsDataTable)
		{participatedCandidatesDetailsDataTable.destroy();}
		if(errorEl.style.display == 'none')
		{errorEl.style.display = 'block'}
		//buildParticipatedCandidatesDetailsDataTable(emptyArray);
	}	
}
</SCRIPT>
</HEAD>
<BODY class="yui-skin-sam">
<CENTER>
<H3>${year} ${electionType} Election Candidates Details</H3>
<DIV id="optionsDiv" class="optionsDiv">
	<P class="paraText">To View Candidates Details for a particular party, please select the "Partywise Candidate Details" check box and select a party from drop down list and select the options provided below. </P>
	<TABLE  class="optionsTable" width="75%">
	<TR>
	<TD align="left" class="td" style="width:25%;"><INPUT type="checkbox" name="partywiseCheckBox" id="partywiseCheckBox" onclick="partywiseClickHandler()" />Partywise Candidates</TD>
	<TD align="left" colspan="1" style="width:25%;"><s:select id="selectParty" theme="simple"  name="selectParty" cssClass="selectBoxStyle" list="partiesList" listKey="id" style="display:none;" listValue="name" onchange="selectPartyOnchangeHandler()" /></TD>
	<TD align="left" colspan="1" style="width:25%;"></TD>
	</TR>	
	<TR>
	<TD align="left" class="td" style="width:25%;"><INPUT type="radio" name="candidatesOption" id="wonCandidates" value="wonCandidatesOnly" onClick="wonCandidatesClickHandler()" checked="true"/>Won Candidates</TD>
	<TD align="left" class="td" style="width:25%;"><INPUT type="radio" name="candidatesOption" id="allCandidates" value="allCandidates" onClick="allCandidatesClickHandler()" />All Participated Candidates</TD>	
	<TD align="left" colspan="1" style="width:25%;"></TD>
	</TR>	
	<c:if test="${electionType == 'Parliament'}">
		<TR id="regionalOptionsRow">
		<TD class="td" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="countryLevelP" value="countrywiseParliament" onClick="countryLevelPClickHandler()" checked="true"/>Countrywise</TD>		
		<TD class="td" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="stateLevelP" value="statewiseParliament" onClick="stateLevelPClickHandler()"/>Statewise</TD>
		<TD style="width:25%;"><s:select id="selectStateP" name="regionSelect" cssClass="selectBoxStyle" style="display:none;" theme="simple" list="statesListObj.getAllStates" listKey="id"  listValue="name" onChange="allCandidates()"  /></TD>
		</TR>
	</c:if>	
	 <c:if test="${electionType == 'Assembly'}" >  
		<TR id="regionalOptionsRow">
		<TD class="td" name="RegionalOptionsA" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="stateLevelA" value="statewiseAssembly" onClick="stateLevelAClickHandler()" checked="true"/>Statewise</TD>		
		<TD class="td" name="RegionalOptionsA" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="distLevelA" value="districtwiseAssembly" onClick="distLevelAClickHandler()"/>Districtwise</TD>
		<TD style="width:25%;"><s:select id="selectdistrictA" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="display:none;" onChange="allCandidates()"  /></TD>
		</TR>
	</c:if>		
	<c:if test="${electionType == 'ZPTC'}">  
	<TR id="regionalOptionsRow">
		<TD class="td" name="RegionalOptionsA" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="stateLevelZ" value="stateWiseZptc" onClick="stateLevelAClickHandler()" checked="true"/>Statewise</TD>		
		<TD class="td" name="RegionalOptionsA" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="distLevelZ" value="districtwiseZptc" onClick="distLevelZClickHandler()"/>Districtwise</TD>
		<TD style="width:25%;"><s:select id="selectdistrictZ" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="display:none;" onChange="allCandidates()"  /></TD>
		</TR>
	</c:if>
	<c:if test="${electionType == 'MPTC'}">  
	<TR>
		<TD class="td" name="RegionalOptionsA" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="stateLevelM" value="stateWiseMptc" onClick="stateLevelAClickHandler()" checked="true"/>Statewise</TD>		
		<TD class="td" name="RegionalOptionsA" style="width:25%;"><INPUT type="radio" name="regionalRadio" id="distLevelM" value="districtwiseMptc" onClick="distLevelMClickHandler()"/>Districtwise</TD>
		<TD style="width:25%;"><s:select id="selectdistrictM" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="display:none;" onChange="allCandidates()"  /></TD>
		</TR>
	</TR>
	</c:if>
	</TABLE>
</DIV>
<DIV id="error" class="errorMessage" style="display:none;">No candidates matched by this selection criteria </DIV>
<DIV id="participatedCandidatesDetailsDataTable" align="left"></DIV>

</CENTER>
<SCRIPT type="text/javascript">
allCandidates();
</SCRIPT>

</BODY>
</HTML>