<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<link href="<s:url value='/styles/table.css'/>" rel="stylesheet" type="text/css" media="all"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Party Performance Report</title> 
<!-- YUI Dependency Files-->
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/container-min.js"></script>	
    <!-- YUI Skin Sam -->    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
   <LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/electionResultsAnalysisReport.css">
   

<script type="text/javaScript">

var partyId = '';
var partyName = '${partyNameHidden}';
var stateId = '${state}';
var stateName = '${stateNameHidden}';
var electionYear = '${year}';
var electionTypeId = '${electionType}';
var electionId = '${stateData.electionId}';
var electionType = '${electionTypeLiteral}';
var reportLevel = '${stateData.reportLevel}';
var districtId = '${stateData.districtId}';
var labelResources = { <%		
	ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
	String electionYear = rb.getString("electionYear");
	String constituency = rb.getString("constituency");
	String candidate  = rb.getString("candidate");
	String rebelParty  = rb.getString("rebelParty");
	String votesPercentage = rb.getString("votesPercentage");
	String rank = rb.getString("rank");
	String party = rb.getString("party");
	String moreDetails = rb.getString("moreDetails");
	String participatedConsts = rb.getString("participatedConsts");
	String pc = rb.getString("pc");
	String seatsWon = rb.getString("seatsWon");
	String wonConst = rb.getString("wonConst");
	String lostConst  = rb.getString("lostConst");
	String analyzed = rb.getString("analyzed");
	String votesMargin = rb.getString("votesMargin");
	String secondPos = rb.getString("secondPos");
	String thirdPos = rb.getString("thirdPos");
	String fourthPos  = rb.getString("fourthPos");
	String nthPos  = rb.getString("nthPos");
	String alliances = rb.getString("alliances");
	//retrieving PPR specific labels
	ResourceBundle pprRb = ResourceBundle.getBundle("ppr_Labels");
	
	String mainParty = pprRb.getString("mainParty");
	String allianceParty = pprRb.getString("allianceParty");		
	String overAllVotesPercent = pprRb.getString("overAllVotesPercent"); 
	String analysisHeading = pprRb.getString("analysisHeading");
	String votesMarginOptionText =  pprRb.getString("votesMarginOptionText");
	String partyPositions =  pprRb.getString("partyPositions");
	String winPosLowMargin =  pprRb.getString("winPosLowMargin");
	String winPosHighMargin  =  pprRb.getString("winPosHighMargin");
	String loosingPosLowMargin =  pprRb.getString("loosingPosLowMargin");
	String loosingPosHighMargin =  pprRb.getString("loosingPosHighMargin");
	String winPosPositiveSwing =  pprRb.getString("winPosPositiveSwing");
	String winPosNegativeSwing =  pprRb.getString("winPosNegativeSwing");
	String loosingPosPositiveSwing =  pprRb.getString("loosingPosPositiveSwing");
	String loosingPosNegativeSwing =  pprRb.getString("loosingPosNegativeSwing");
	String loosingPosDroppingPercent =  pprRb.getString("loosingPosDroppingPercent");
	String yearVsSeatsChart =  pprRb.getString("yearVsSeatsChart");
	String v  =  pprRb.getString("v");
	String opv =  pprRb.getString("opv");
	String opvDef =  pprRb.getString("opvDef");
	String op =  pprRb.getString("op");
	String opDef =  pprRb.getString("opDef");
	String opc =  pprRb.getString("opc");
	String opcDef =  pprRb.getString("opcDef");
	String vp =  pprRb.getString("vp");
	String vpDef =  pprRb.getString("vpDef");
	String ec =  pprRb.getString("ec");
	String ecDef =  pprRb.getString("ecDef");
	String electionSummaryHeading =  pprRb.getString("electionSummaryHeading");
	String close =  pprRb.getString("close");
	String view =  pprRb.getString("view");	
	String totalSeatsWon =  pprRb.getString("totalSeatsWon");
	String votesPcntGained =  pprRb.getString("votesPcntGained");
	String diffVotesPctn =  pprRb.getString("diffVotesPctn");
	String mv = pprRb.getString("mv");
	String mvDef = pprRb.getString("mvDef");
%> }
function showBand(divtag)
{ 
	var divElmt=document.getElementById(divtag);
	var spanElmt=document.getElementById(divtag+"span");	
	if(!divElmt || !spanElmt)
		return;
	if(divElmt.style.display=="none")
	{
		divElmt.style.display = 'block';
		spanElmt.innerHTML="<%=close%>";
	}
	else
	{
		divElmt.style.display = 'none';
		spanElmt.innerHTML="<%=view%>";
	}
}



function openConstituencyResultsWindow(constituencyId)
{
	var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constituencyId+"&electionType="+electionType+"&electionYear="+electionYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	browser1.focus();
}

function getPartyPositionDetails(pos,partyId)
{	
	var searchElmt=document.getElementById("partyPosImg");
    searchElmt.style.display="block"
	var position = pos;
	var party = partyId;
	var partyName='${stateData.party}';
	var electionTypeId='${stateData.electionTypeId}';
	var state = '${stateData.stateId}';
	var year = '${stateData.year}';
	var district = '${stateData.districtId}';	
	var alliances = '${stateData.hasAlliances}';
    var reportLevel =  '${stateData.reportLevel}';
	
	var jsObj=
	{
			positionValue:position,
			partyValue:party,			
			eId:electionTypeId,
			stateValue:state,
			yearValue:year,
			districtValue:district,
			hasAlliances:alliances,
			reportLevel:reportLevel
	}
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	callPositionAjax(param,jsObj);
}

function callAjax(param,jsObj,url){
	var myResults;

	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							if(jsObj.task == "getVotesMarginInfo")
							{
								buildVotesMarginContent(jsObj,myResults);
							}
														
						}catch (e) {   
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

function callPositionAjax(param,jsObj){
	var myResults;
	var url = "<%=request.getContextPath()%>/partyPositionDetailsAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayPartyPositions(jsObj,myResults);							
						}catch (e) {   
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

function displayPartyPositions(jsObj,data)
{	
	var imgElmt = document.getElementById("partyPosImg");
	imgElmt.style.display='none';
	
	if(data[0]==null)
	{
		alert("No results found");
		return;
	}

	var divElmt = document.getElementById("partyPositions");
	var divElmtBody = document.getElementById("partyPositionsBody");

	var divElmtHead = document.getElementById("labelHead");	
	rank = jsObj.positionValue;
	if(rank==-1)
	 rank = 'N';
	if(rank!=1)
	{
	divElmtHead.innerHTML=" "+'${stateData.party}'+" In position : "+rank+" And Its Opposition Party Details";
	}
	
	if(rank==1 || rank == 'N')
	{
	divElmtHead.innerHTML=" "+'${stateData.party}'+" Party In position : "+rank+" ";
	}
	
	divElmt.style.display = 'block';
	var str='';
	str+='<table id="partyPositionTable"  class="partyPerformanceReportTable" border="1">';	
	for(var i in data)
	{		
		str+='<tr>';
		str+='<td>'+data[i].constituencyName+'</td>';
		str+='<td>'+data[i].candidateName+'</td>';
		str+='<td>'+data[i].partyName+'</td>';
		str+='<td align="right">'+data[i].votePercentage+'</td>';
		for (var d in data[i].oppPartyPositionInfoList)
		{
			str+='<td>'+data[i].oppPartyPositionInfoList[d].candidateName+'</td>';
			str+='<td>'+data[i].oppPartyPositionInfoList[d].partyName+'</td>';
			str+='<td align="right">'+data[i].oppPartyPositionInfoList[d].votePercentage+'</td>';
		}
		str+='</tr>';
	}	
	str+='</table>'
	divElmtBody.innerHTML=str;
	
	buildPartyPositionDataTable(data,rank);	
}

function closeSpan()
{
	var divElmt = document.getElementById("partyPositions");  
	divElmt.style.display = 'none';
}

function buildPartyPositionDataTable(info,rank)
{
	if(info[0]==null)	
		return;
	var count=0;

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("partyPositionTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : []
	};	
	
	var key1={key : "constituencyName"};
	var key2={key:"candidateName"};
	var key3={key:"partyName"};
	var key4={key : "votePercentage",parser:"number"};
	resultsDataSource.responseSchema.fields.push(key1);
	resultsDataSource.responseSchema.fields.push(key2);
	resultsDataSource.responseSchema.fields.push(key3);
	resultsDataSource.responseSchema.fields.push(key4);

	for (var k in  info[0].oppPartyPositionInfoList)
	{		
		var key5={key : "cName"+k};
		var key6={key : "pName"+k};		
		var key7={key : "vPercentage"+k,parser:"number"};
		resultsDataSource.responseSchema.fields.push(key5);
		resultsDataSource.responseSchema.fields.push(key6);
		resultsDataSource.responseSchema.fields.push(key7);
	}	
	//--------
	var resultsColumnDefs = [
		{
			label:"Candidate Details in "+rank+" position ",
			className:"yui-dt-sortable ",
			children:[ 	
						{
							key : "constituencyName",		
							label : "<%=constituency%>",
							sortable : true
						},
						{
							key : "candidateName",		
							label : "<%=candidate%>",
							sortable : true
						},
						{
							key : "partyName",		
							label : "<%=party%>",
							sortable : true
						},
						{
							key : "votePercentage",
							parser:"number",
							label : "<%=votesPercentage%>",
							sortable : true
						}
					 ]
		}
	];
	
	for (var d in info[0].oppPartyPositionInfoList)
	{
		count++;
		var obj = {
						label:"Candidate Details in "+(count)+" position ",
						className:"yui-dt-sortable ",
						children:[ 	
									{
										key : "cName"+d,		
										label : "<%=candidate%>",
										sortable : true
									},
									{
										key : "pName"+d,		
										label : "<%=party%>",
										sortable : true
									},
									{
										key : "vPercentage"+d,
										parser:"number",
										label : "<%=votesPercentage%>",
										sortable : true
									}
								 ]
					}
		
		resultsColumnDefs.push(obj);		
	}

	var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};	

	var myDataTable = new YAHOO.widget.DataTable("partyPositionsBody",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildpartyPerformanceDataTable(data,divId)
	{
		if(data.partyPerformanceArray == "")
			return;
		
		var partyColor='';
		var partyName = "${stateData.party}";
		if(partyName.indexOf('&') != -1)
		{
			partyName = partyName.substring(0,partyName.indexOf('&')-1);		
		}		
			
				
		if(divId == "POSITIONS_WON_MAJOR_BAND" || divId == "POSITIONS_WON_MINOR_BAND" || divId == "POSITIONS_LOST_MINOR_BAND" || divId == "POSITIONS_LOST_MAJOR_BAND")
		{	
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "<%=constituency%>",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "<%=candidate%>", sortable:true, resizeable:true}, 
				{key:"mainParty",label : "<%=party%>", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "<%=v%>", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "<%=opv%>", sortable:true, resizeable:true},
				{key:"marginVotesPercentage",label:"<%=mv%>",sortable:true},
	            {key:"oppositionParty",label : "<%=op%>",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "<%=opc%>", sortable:true, resizeable:true},
	            {key:"moreDetails",label : "", sortable:true, resizeable:true}
	                
				
	        ]; 
			var myDataSource = new YAHOO.util.LocalDataSource(data.partyPerformanceArray); 		
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			myDataSource.responseSchema = { 
			fields : [
						{key : "constituencyName"}, {key : "candidateName"}, {key : "mainParty"}, {key : "percentageOfVotes",parser:"number"},
						{key :"oppositionPartyPercentageOfVotes",parser:"number"},{key : "oppositionParty"}, {key : "oppositionPartyCandidate"},
						{key : "moreDetails"},{key : "marginVotesPercentage"}
					 ]
	        };
			
			
			var myRowFormatter = function(elTr, oRecord) { 
				var mainParty = oRecord.getData('mainParty');
				if ( mainParty== partyName) { 					
					YAHOO.util.Dom.addClass(elTr, 'mainPartyColor'); 
				} 
				else
				{
					YAHOO.util.Dom.addClass(elTr, 'oppositionPartColor'); 
				}
				return true; 
			};  
			var myDataTable = new YAHOO.widget.DataTable(
						divId,myColumnDefs, myDataSource,
						{
							formatRow : myRowFormatter,
							caption : "<div style='text-align:left;'><font style='color:#2B5181;font-weight:bold;font-size:11px;'> *<%=mainParty%></font><font style='color:#7EADBC;font-weight:bold;font-size:11px;'> * <%=allianceParty%></font><Br><font style='font-size:11px;'> <%=v%>=<%=votesPercentage%>, <%=opv%>=<%=opvDef%>, <%=op%>=<%=opDef%>, <%=opc%>=<%=opcDef%>, <%=mv%>=<%=mvDef%></font></div>",
							paginator : new YAHOO.widget.Paginator({ 
								rowsPerPage    : 10,
								template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
								rowsPerPageOptions: [10,20,30], 
							    pageLinks: 20 
								})
						}); 			
		}
		else if(divId == "POSITIONS_WON_WITH_POSITIVE_SWING" || divId == "POSITIONS_WON_WITH_NEGATIVE_SWING" || divId == "POSITIONS_LOST_WITH_POSITIVE_SWING" || divId == "POSITIONS_LOST_WITH_NEGATIVE_SWING")
		{			
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "<%=constituency%>",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "<%=candidate%>", sortable:true, resizeable:true}, 
				{key:"mainParty",label : "<%=party%>", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "<%=v%>", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesGained",label : "<%=v%> in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "<%=opv%>", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "<%=op%>",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "<%=opc%>", sortable:true, resizeable:true},
	            {key:"moreDetails",label : "", sortable:true, resizeable:true}    
				
	        ]; 

			var myDataSource = new YAHOO.util.DataSource(data.partyPerformanceArray); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["constituencyName","candidateName","mainParty","percentageOfVotes","previousElectionPercentageOfVotesGained","oppositionPartyPercentageOfVotes","oppositionParty","oppositionPartyCandidate","moreDetails"] 
	        };

			var myRowFormatter = function(elTr, oRecord) { 
				var mainParty = oRecord.getData('mainParty');
				if ( mainParty== partyName) { 					
					YAHOO.util.Dom.addClass(elTr, 'mainPartyColor'); 
				} 
				else
				{
					YAHOO.util.Dom.addClass(elTr, 'oppositionPartColor'); 
				}
				return true; 
			};  

			var myDataTable = new YAHOO.widget.DataTable(
						divId,myColumnDefs, myDataSource,
						{
							formatRow : myRowFormatter,
							caption : "<div style='text-align:left;'><font style='color:#2B5181;font-weight:bold'> *<%=mainParty%></font> <font style='color:#7EADBC;font-weight:bold'> *  <%=allianceParty%></font><Br><font style='font-size:11px;'> <%=v%>=<%=votesPercentage%>, <%=vp%>=<%=vpDef%>, <%=ec%>=<%=ecDef%>, <%=opv%>=<%=opvDef%>, <%=op%>=<%=opDef%>, <%=opc%>=<%=opcDef%></font></div>",
							paginator : new YAHOO.widget.Paginator({ 
								rowsPerPage    : 10,
								template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
								rowsPerPageOptions: [20,40,60], 
							    pageLinks: 20 
								})
						}); 	
		}		  
		else if(divId == "POSITIONS_LOST_BY_DROPPING_VOTES")
		{
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "<%=constituency%>",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "<%=candidate%>", sortable:true, resizeable:true}, 
				{key:"mainParty",label : "<%=party%>", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "<%=v%>", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesGained",label : "<%=v%> in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"percentageOfVotesPolled",label : "<%=vp%>", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesPolled",label : "<%=vp%> in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"previousElectionCandidate",label : "<s:property value="stateData.prevYear" /><%=ec%>", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "<%=opv%>", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "<%=op%>",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "<%=opc%>", sortable:true, resizeable:true},
	            {key:"moreDetails",label : "", sortable:true, resizeable:true}    
				
	        ]; 

			var myDataSource = new YAHOO.util.DataSource(data.partyPerformanceArray); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["constituencyName","candidateName","mainParty","percentageOfVotes","previousElectionPercentageOfVotesGained","percentageOfVotesPolled","previousElectionPercentageOfVotesPolled","previousElectionCandidate","oppositionPartyPercentageOfVotes","oppositionParty","oppositionPartyCandidate","moreDetails"] 
	        };

			var myRowFormatter = function(elTr, oRecord) { 
				var mainParty = oRecord.getData('mainParty');
				if ( mainParty== partyName) { 					
					YAHOO.util.Dom.addClass(elTr, 'mainPartyColor'); 
				} 
				else
				{
					YAHOO.util.Dom.addClass(elTr, 'oppositionPartColor'); 
				}
				return true; 
			};  

			var myDataTable = new YAHOO.widget.DataTable(
						divId,myColumnDefs, myDataSource,
						{
							formatRow : myRowFormatter,
							caption : "<div style='text-align:left;'><font style='color:#2B5181;font-weight:bold'> *<%=mainParty%></font> <font style='color:#7EADBC;font-weight:bold'> * <%=allianceParty%></font><Br><font style='font-size:11px;'> <%=v%>=<%=votesPercentage%>, <%=vp%>=<%=vpDef%>, <%=ec%>=<%=ecDef%>,<%=opv%>=<%=opvDef%>, <%=op%>=<%=opDef%>, <%=opc%>=<%=opcDef%></font></div>",
							paginator : new YAHOO.widget.Paginator({ 
								rowsPerPage    : 10,
								template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
								rowsPerPageOptions: [20,40,60], 
							    pageLinks: 20 
								})
						}); 	
		}	
	}

	function viewPartyBasicResults()
	{
        var obj = {
                    year:'${stateData.year}',
					previousYear:'${stateData.prevYear}',
					totalSeatsWon:'${stateData.totalSeatsWon}',
					prevYearTotSeatsWon:'${stateData.prevYearTotalSeatsWon}',
					votesPercent:'${stateData.totalPercentageOfVotesWon}',
					prevYearVotesPercent:'${stateData.prevYeartotalPercentageOfVotesWon}',
					votesPercentDiff:'${stateData.diffOfTotalPercentageWinWithPrevElection}'
		           };

		 var str='';
		 str+='<table border="1" id="partyPositionDetailsTable" width="100%">';
	     str+='<tr>';
	     str+='<th></th>';
	     str+='<th align="center">'+obj.year+'</th>';
		 if(obj.prevYearTotSeatsWon != 0)
		   str+='<th  width="100px" align="center">'+obj.previousYear+'</th>';
		 str+='</tr>';
	     str+='<tr>';
		 str+='<td style="background-color: #ECF1F5" align="center"><b>Seats Won</b></td>';
		 str+='<td style="background-color: #ECF1F5" width="100px" align="center">'+obj.totalSeatsWon+'</td>';
		 if(obj.prevYearTotSeatsWon != 0)
		    str+='<td style="background-color: #ECF1F5" width="100px" align="center">'+obj.prevYearTotSeatsWon+'</td>';
		 str+='</tr>';
	     str+='<tr>';
		 str+='<td style="background-color: #ECF1F5" align="center"><b>Votes %</b></td>';
		 str+='<td style="background-color: #ECF1F5" width="100px" align="center">'+obj.votesPercent+'&nbsp;%</td>';
		 if(obj.prevYearTotSeatsWon != 0)
		   str+='<td style="background-color: #ECF1F5" width="100px" style="background-color: #FFFFFF" align="center">'+obj.prevYearVotesPercent+'&nbsp;%</td>';
		 str+='</tr>';
	     if(obj.prevYearTotSeatsWon != 0){
	     str+='<tr>';
	     str+='<td style="background-color: #ECF1F5" align="center" colspan="3"><b>Votes % Difference ('+obj.year+' - '+obj.previousYear+' )  :  </b>';
         if(obj.votesPercentDiff < 0)
		   str+='<font color="red">'+obj.votesPercentDiff+'%</font>';
		 if(obj.votesPercentDiff > 0)
		   str+='<font color="green">'+obj.votesPercentDiff+'%</font>';
		 str+='</td>';
	     str+='</tr>';
	     }
         str+='</table>';

	   myPanel = new YAHOO.widget.Panel("party", {
                 width: "375px", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
       });
	   myPanel.setHeader("Party Complete Results ...");
       myPanel.setBody(str);
       myPanel.render();

	}

	function viewResizeablePanel()
	{
		var arr = new Array();
		var elmt = document.getElementById("resizablepanel");
		
		<c:forEach var="results" items="${stateData.partyPositionsVO}">
			var obj = {
			            partyId:'${results.partyId}',
						constituency:'${results.totalConstiParticipated}',
						seats:'${results.totalSeatsWon}',
						partyName:'${results.partyName}',
						secondPos:'${results.secondPosWon}',
						thirdPos:'${results.thirdPosWon}',
						fourthPos:'${results.fourthPosWon}',
						nthPos:'${results.nthPosWon}',
						votesPercent:'${results.votesPercentage}',
						overallVotesPercent:'${results.completeVotesPercent}'
					  };
					arr.push(obj);
		</c:forEach>
            var str='';
			str+='<table border="1" id="partyPositionDetailsTable" style="margin-bottom: 10px;">';
			str+='<tr>';
			str+='<th align="center"><%=party%></th>';
			str+='<th align="center"><%=pc%></th>';
			str+='<th align="center"><%=seatsWon%></th>';
			str+='<th align="center"><%=secondPos%></th>';
			str+='<th align="center"><%=thirdPos%></th>';
			str+='<th align="center"><%=fourthPos%></th>';
			str+='<th align="center"><%=nthPos%></th>';
			str+='<th align="center"><%=votesPercentage%></th>';
			str+='<th align="center"><%=overAllVotesPercent%></th>';
			str+='</tr>';
			for(var i in arr)
		    {
			
			str+='<tr>';
			if(i == 0)
				str+='<td align="center"><span style="color:#FF0000">'+arr[i].partyName+'*</span></td>';
			else
			    str+='<td align="center">'+arr[i].partyName+'</td>';
			str+='<td align="center">'+arr[i].constituency+'</td>';
			if(arr[i].seats == 0)
				str+='<td align="center" style="font-weight:bold;"><span style="color:#539E41;">'+arr[i].seats+'</span></td>';
			else
			   str+='<td align="center"><a href="javascript:{}" style="color:#539E41;" onclick="getPartyPositionDetails(1,\''+arr[i].partyId+'\')">'+arr[i].seats+'</a></td>';

			if(arr[i].secondPos == 0)
               str+='<td align="center" style="font-weight:bold;"><span style="color:#C44C50;">'+arr[i].secondPos+'</span></td>';
			else
			   str+='<td align="center"><a href="javascript:{}" style="color:#E8A0A5;font-weight:bold;" onclick="getPartyPositionDetails(2,\''+arr[i].partyId+'\')">'+arr[i].secondPos+'</td>';

			if(arr[i].thirdPos == 0)
               str+='<td align="center" style="font-weight:bold;"><span style="color:#FF979E;">'+arr[i].thirdPos+'</span></td>';
			else
               str+='<td align="center"><a style="color:#FF979E;font-weight:bold;" href="javascript:{}" onclick="getPartyPositionDetails(3,\''+arr[i].partyId+'\')">'+arr[i].thirdPos+'</td>';

			if(arr[i].fourthPos == 0)
               str+='<td align="center" style="font-weight:bold;"><span style="color:#FF7F87;">'+arr[i].fourthPos+'</span></td>';
			else
			   str+='<td align="center"><a href="javascript:{}" style="color:#FF7F87;font-weight:bold;" onclick="getPartyPositionDetails(4,\''+arr[i].partyId+'\')">'+arr[i].fourthPos+'</td>';

			if(arr[i].nthPos == 0)
                str+='<td align="center"><span style="color:#FF1515;font-weight:bold;">'+arr[i].nthPos+'</span></td>';
			else
		    	str+='<td align="center"><a href="javascript:{}" style="color:#FF1515;font-weight:bold;" onclick="getPartyPositionDetails(-1,\''+arr[i].partyId+'\')">'+arr[i].nthPos+'</td>';
		    str+='<td align="center">'+arr[i].votesPercent+'</td>';
			str+='<td align="center">'+arr[i].overallVotesPercent+'</td>';
			str+='</tr>';
			}
			
			
			
			str+='<span id="partyPosImg" align="right" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/partypositions.gif" /></img></span>';
			str+='</table>';

			if(${stateData.hasAlliances == true})
			str+='<b style="color: red;">* <%=mainParty%>&nbsp;,&nbsp;</b>';
			str+='<b style="color: red;">PC* - <%=participatedConsts%></b>';
			
			

		 myPanel = new YAHOO.widget.Panel("resizablepanel", {
					 width: "800px", 
					 fixedcenter: false, 
					 constraintoviewport: false, 
					 underlay: "none", 
					 close: false, 
					 visible: true, 
					 draggable: false
		   });
     
       myPanel.setBody(str);
	   myPanel.render();

   }

	function initializeGraph()
	{
        var partyPositionsDataGraph = new Array();
		<c:forEach var="results" items="${stateData.partyPositionsVO}">
			var obj = {
			           	partyName:'${results.partyName}',
						seatsWon:'${results.totalSeatsWon}',
						secndPos:'${results.secondPosWon}',
						thirdPos:'${results.thirdPosWon}'
					  };
					partyPositionsDataGraph.push(obj);
		</c:forEach>

        YAHOO.widget.Chart.SWFURL = "http://yui.yahooapis.com/2.8.0r4/build/charts/assets/charts.swf";

		var incomeData = new YAHOO.util.DataSource(partyPositionsDataGraph);
		incomeData.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		incomeData.responseSchema = { fields: [ "partyName","seatsWon", "secndPos","thirdPos"] };

		var seriesDef =
		[		
			{
				yField: "seatsWon",
				displayName: "SeatsWon"
			},
			{
				yField: "secndPos",
				displayName: "2nd Pos"
			},
			{
				yField: "thirdPos",
				displayName: "3rd Pos"
			}
		];

        
		

	  	var currencyAxis = new YAHOO.widget.NumericAxis(); 
		currencyAxis.minimum = 50; 
		currencyAxis.maximun = 200; 
		
		var seatsCount = new Array();
        seatsCount.push(100);
		seatsCount.push(200);
		seatsCount.push(300);

		var mychart = new YAHOO.widget.ColumnChart( "partyPosChart", incomeData,
		{
		series: seriesDef,
		xField: "partyName",
		yField: currencyAxis
		
		});

		
        
	}


    function swfProperties()
	{
       var params = {
		version: 10.42,
		useExpressInstall: true,
		fixedAttributes: {
			allowScriptAccess: "always",
			allowNetworking: "all",
			width: 50
		},
		flashVars: {
			flashvar1: "One word", 
			flashvar2: "A word & another", 
			flashvar3: "Three words - 100% done & done"
		}
	   }; 
	   var newswf = new YAHOO.widget.SWF("swfContainer", "charts.swf", params);
	}
	function initializePartyPositionsDetails()
	{
		var arr = new Array();
		<c:forEach var="results" items="${stateData.partyPositionsVO}">
			var obj = {
			            partyId:'${results.partyId}',
						constituency:'${results.totalConstiParticipated}',
						seats:'${results.totalSeatsWon}',
						partyName:'${results.partyName}',
						secondPos:'${results.secondPosWon}',
						thirdPos:'${results.thirdPosWon}',
						fourthPos:'${results.fourthPosWon}',
						nthPos:'${results.nthPosWon}',
						votesPercent:'${results.votesPercentage}',
						overallVotesPercent:'${results.completeVotesPercent}'
					  };
					arr.push(obj);
		</c:forEach>
			
		var tabView = new YAHOO.widget.TabView(); 
		
		for(var i in arr)
		{
			var str='';
			str+='<table border="1" style="border-collapse:collapse;height:75px;">';
			str+='<tr>';
			str+='<th align="center">Seats Won</th>';
			str+='<th align="center">2nd Pos</th>';
			str+='<th align="center">3rd Pos</th>';
			str+='<th align="center">4th Pos</th>';
			str+='<th align="center">Nth Pos</th>';
			str+='<th align="center">Participated Constituencies</th>';
			str+='<th align="center">Votes %</th>';
			str+='</tr>';
			str+='<tr>';
			if(arr[i].seats == 0)
				str+='<td align="center">'+arr[i].seats+'</td>';
			else
			   str+='<td align="center"><a href="javascript:{}" onclick="getPartyPositionDetails(1,\''+arr[i].partyId+'\')">'+arr[i].seats+'</a></td>';
			if(arr[i].secondPos == 0)
               str+='<td align="center">'+arr[i].secondPos+'</td>';
			else
			   str+='<td align="center"><a href="javascript:{}" onclick="getPartyPositionDetails(2,\''+arr[i].partyId+'\')">'+arr[i].secondPos+'</td>';
			if(arr[i].thirdPos == 0)
               str+='<td align="center">'+arr[i].thirdPos+'</td>';
			else
               str+='<td align="center"><a href="javascript:{}" onclick="getPartyPositionDetails(3,\''+arr[i].partyId+'\')">'+arr[i].thirdPos+'</td>';
			if(arr[i].fourthPos == 0)
               str+='<td align="center">'+arr[i].fourthPos+'</td>';
			else
			   str+='<td align="center"><a href="javascript:{}" onclick="getPartyPositionDetails(4,\''+arr[i].partyId+'\')">'+arr[i].fourthPos+'</td>';
			if(arr[i].nthPos == 0)
                str+='<td align="center">'+arr[i].nthPos+'</td>';
			else
		    	str+='<td align="center"><a href="javascript:{}" onclick="getPartyPositionDetails(-1,\''+arr[i].partyId+'\')">'+arr[i].nthPos+'</td>';
			str+='<td align="center">'+arr[i].constituency+'</td>';
            str+='<td align="center">'+arr[i].votesPercent+'</td>';
			str+='</tr>';
			str+='</table>';
			
            	if(i == 0)
			    {
				tabView.addTab( new YAHOO.widget.Tab({ 
				label: arr[i].partyName, 
				content: str,
				active: true
				}));
				}
				else
				{
				tabView.addTab( new YAHOO.widget.Tab({ 
				label: arr[i].partyName, 
				content: str
				}));
				}
		}

		tabView.appendTo('tabView'); 
    }

	function initializeRebelsDataTable() {
	
	var resultsDataSource = new YAHOO.util.DataSource(partyObj.rebelsPerformanceArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constiuencyName"
		}, {
			key : "candidateName"
		}, {
			key : "partyName"
		}, {
			key : "percentageOfVotes",parser:"number"
		}, {
			key : "rank",parser:"number"
		}, {
			key : "oppositeParty"
		} , {
			key : "oppositePartyCandidate"
		} , {
			key : "oppositePartyPercentageOfVotes",parser:"number"
		} , {
			key : "oppositePartyRank",parser:"number"
		},  {
			key : "moreDetails"
		}]
	};

	var resultsColumnDefs = [ {
		key : "constiuencyName",
		label : "<%=constituency%>",
		sortable : true
	}, {
		key : "candidateName",
		label : "<%=candidate%>",
		sortable : true
	}, {
		key : "partyName",
		label : "<%=rebelParty%>",
		sortable : true
	}, {
		key : "percentageOfVotes",
		label : "<%=votesPercentage%>",
		sortable : true
	}, {
		key : "rank",
		label : "<%=rank%>",
		sortable : true
	}, {
		key : "oppositeParty",
		label : "<%=party%>",
		sortable : true
	}, {
		key : "oppositePartyCandidate",
		label : "<%=candidate%>",
		sortable : true
	} , {
		key : "oppositePartyPercentageOfVotes",
		label : "<%=votesPercentage%>",
		sortable : true
	} , {
		key : "oppositePartyRank",
		label : "<%=rank%>",
		sortable : true
	} , {
		key : "moreDetails",
		label : "",
		sortable: true
		
	} ];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,		        
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [10,20,30], 
			    pageLinks: 20
			    }) 
				};	
	var myDataTable = new YAHOO.widget.DataTable("rebelsDiv",resultsColumnDefs, resultsDataSource,myConfigs);  

}


function reportTitleDivFunc()
{
	var str='${reportTitle}';

	var reportTitle = new YAHOO.widget.Panel("reportTitleDiv", { width:"600",
		         fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false } );
	reportTitle.setHeader(str);
	reportTitle.render();

}

function buildMarginVotes(localPartyId,status)
{	
    var locationId = "0";
	if("1" == reportLevel)
	{
		locationId=stateId;
	}
	else if("2" == reportLevel)
	{
		locationId=districtId;
	}


	partyId = localPartyId;
	var jsObj= 
	{	
		electionId: electionId,
		partyId: partyId,		
		status:status,
		reportLevel:reportLevel,
		locationId:locationId,
		task:"getVotesMarginInfo"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/votesMaringInfoForElectionInPartyPerformance.action?"+param;
	callAjax(param,jsObj,url);
}

function buildVotesMarginContent(jsObj,results)
{
	var elmt,str='';
	var partyId = jsObj.partyId;

	if(jsObj.status == "WON")
		elmt = document.getElementById("votesMarginInfo_won");
	else if(jsObj.status == "LOST")
		elmt = document.getElementById("votesMarginInfo_lost");
	
	if(!elmt)
		return;
	
	str += '<table width="100%" style="width:100%" class="votesMarginTable" border="0">';
	str += '<tr>';
	str += '<th style="background-color:#DFE2E5;"></th>';
	str += '<th align="left" style="background-color:#DFE2E5;"><%=votesMargin%></th>';
	str += '<th style="background-color:#DFE2E5;">'+jsObj.status+' Constituencies</th>';
	str += '<th style="background-color:#DFE2E5;"><%=analyzed%></th>';
	str += '</tr>';
	if(results[0].resultStatus)
	{
		str+= '<tr>';
		str+= '<td colspan="4">No Results To Display</td>';
		str+= '</tr>';	
		str += '</table>';
	
		if(elmt)
		elmt.innerHTML = str;
		
		return;
	}
	for(var i in results)
	{		
		str+= '<tr onclick="showMarginAnalysisData('+i+',\''+jsObj.status+'\')" style="cursor:pointer;">';
		str+= '<td style="background-color:#FFFFFF;"><img src="images/icons/indexPage/listIcon.png"></img></td>';
				
		str+= '<td align="left">'+results[i].marginValueOne+' - '+results[i].marginValueTwo+' % </td>';		
		

		if(results[i].candidatesCount != 0)
			str+= '<td><a href="javascript:{}" onclick="showMarginCountAnalysisForConstituenciesPopup('+i+','+partyId+',\''+jsObj.status+'\')">'+results[i].candidatesCount+'</a></td>';
		else
			str+= '<td>'+results[i].candidatesCount+'</td>';		
		
		if(results[i].analyzedCount != 0)
			str+= '<td><a href="javascript:{}" onclick="showMarginCountAnalysisForAnalyzedConstituenciesPopup('+i+','+partyId+',\''+jsObj.status+'\')">'+results[i].analyzedCount+'</a></td>';
		else
			str+= '<td>'+results[i].analyzedCount+'</td>';

		str+= '</tr>';
		if(results[i].analysisCategoryBasicVO != null)
		{
			str+= '<tr id="marginInfo_'+jsObj.status+'_row_'+i+'" style="display:none;">';
			str+= '<td colspan="4">';
			str+= '<div class="marginBodyDivClass">';
			str+= '<table width="95%" border="0" class="votesMarginDataTable">';
			for(var j in results[i].analysisCategoryBasicVO)
			{
				var dt = results[i].analysisCategoryBasicVO[j];				
				str+= '<tr>';
				str+= '<td align="left">'+dt.categoryType+'</td>';
				str+= '<td><a href="javascript:{}" onclick="showMarginCountAnalysisForCategory('+i+','+partyId+','+dt.categoryId+',\''+jsObj.status+'\')">'+dt.categoryResultCount+'</a></td>';						
				str+= '</tr>';
			}
			str+= '</table>';
			str+= '</div>';
			str+= '</td>';
			str+= '</tr>';
		}
	}	
	str += '</table>';

	if(elmt)
		elmt.innerHTML = str;
}

function showMarginAnalysisData(index,task)
{
	var elmt = document.getElementById("marginInfo_"+task+"_row_"+index);

	if(!elmt)
		return;

	if(elmt.style.display == 'none')
		elmt.style.display = '';
	else if(elmt.style.display == '')
		elmt.style.display = 'none';
}

function showMarginCountAnalysisForConstituenciesPopup(index,partyId,status)
{

	var locationId = "0";
	if("1" == reportLevel)
	{
		locationId=stateId;
	}
	else if("2" == reportLevel)
	{
		locationId=districtId;
	}

	index = index+1;
	if(status == "WON")
		rank = 1;
	else if(status == "LOST")
		rank = 0;

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAction.action?electionId="+electionId+"&electionYear="+electionYear+"&electionTypeId="+electionTypeId+"&electionType="+electionType+"&partyId="+partyId+"&rank="+rank+"&clickIndex="+index+"&resultStatus="+status+"&reportLevel="+reportLevel+"&locationId="+locationId+"&windowTask=mainPartyMarginCountAnalysisPopup";
	var browser1 = window.open(urlStr,"partyElectionResultsPopup","scrollbars=yes,height=600,width=1300,left=200,top=200");
	
	browser1.focus();
}

function showMarginCountAnalysisForAnalyzedConstituenciesPopup(index,partyId,status)
{
	index = index+1;

	var position = '';
	if(status == "WON")
		position = "Won";
	else if(status == "LOST")
		position = "Lost";

	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&clickIndex="+index+"&resultStatus="+status+"&windowTask=mainPartyMarginCountAnalyzedConstituenciesPopup";
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
}

function showMarginCountAnalysisForCategory(index,partyId,categoryId,status)
{	
	index = index+1;
	
	var position = '';
	if(status == "WON")
		position = "Won";
	else if(status == "LOST")
		position = "Lost";
			
	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?stateId="+stateId+"&electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear+"&position="+position+"&clickIndex="+index+"&categoryId="+categoryId+"&resultStatus="+status+"&windowTask=mainPartyMarginCountAnalyzedCategoryPopup";
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();	
}

function callMarginVotes(partyId)
{
	buildMarginVotes(partyId,"WON");
	buildMarginVotes(partyId,"LOST");
}


</script>

<style type="text/css">
	#partyPerformanceReportMainDiv
	{
		text-align:left;
		margin-left:auto;
		margin-right:auto;
		width:914px;
		font-size:12px;
	}
	#partyPositions
	{
		background-color:#FFFFFF;
		border:2px solid #839AB7;
		display:none;
		left:300px;
		margin-right:20px;
		margin-top:15px;
		position:absolute;
		top:500px;
		z-index:10;
	}
	#closeSpan
	{
		float:right;
		cursor:pointer;
		font-weight:bold;
		margin-right:10px;
		border:1px solid;
	}	
	#closeLabelSpan
	{
		float:right;padding-right:5px;
		cursor:pointer;
	}
	#labelHead
	{
		font-weight:bold;
		font-size:14px;		
		color:#394351;
	}
	#partyPositionsHead
	{
		padding:10px;
		text-decoration:underline;
		background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat-x scroll 0 -200px transparent;
	}
	#partyPositionsBody table
	{
		width:100%;
		border:1px solid #5C687D;
	}

	.yui-skin-sam .yui-dt-liner 
	{
		padding:0px;
	}
	.yui-skin-sam thead .yui-dt-sortable
	{
		background-color:#B0C7EB;
		color:#707070;
		font-size:12px;
		text-decoration:none;
	}
	.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
	{
		background-color:#B0C7EB;
		background-image:none;
	}
	.yui-skin-sam th.yui-dt-asc .yui-dt-liner 
	{
		background-color:#B0C7EB;
	}

	.tableDiv
	{
		display:none;
		margin-right:20px;
	}
	.tableColoumn
	{
		text-align:left;
	}

	#positionHeading
	{
		font-weight:bold;
		background-color:#FFFFFF;
	}
	.yui-skin-sam .yui-dt-paginator 
	{
		margin-right:20px;
		text-align:right;
		padding:10px 0;
	}
	#tabView
	{
		width:53%;
	}
		
	.yui-overlay, .yui-panel-container
	{
		position:relative;
	}
	
	#resizablepanelMain
	{
		/*
		margin-top:30px;
		height:300px;*/
	}
	#partyPositionDetailsTable
	{
		border-collapse:collapse;
		height:75px;
	}
	#partyPositionDetailsTable th
	{
		padding:8px;
	}
	#partyPositionDetailsTable td
	{
		padding:8px;
	}
	.yui-skin-sam .yui-panel 
	{
		border:none;
		width:700px;
	}
	#reportTitleDiv.yui-panel .hd {
		
	    padding:0; 	    
	    color:#61396E; 
	    height:22px; 	    
	    text-align:center; 
	    overflow:visible; 
		font-size:13px;
		
	} 
	#titleDiv
	{
		background-image:url(images/icons/partyPerformance/partyPerformanceReportHeading.png);
		color:#FBAD2B;
		font-size:15px;
		font-weight:bold;
		margin-left:43px;
		margin-right:100px;
		padding:8px;
		width:792px;
		margin-top: 15px;		
	}

	.yui-skin-sam .yui-panel .bd
	{
		border:1px solid #adadad;
	}
	
	.yui-skin-sam .yui-panel .hd 
	{
		border:1px solid #CCCCCC;
		background-color:#EFEFEF;
	}
	.yui-skin-sam .yui-dt table
	{
		border:2px solid #DFDFDF;
		border-collapse:separate;
		border-spacing:0;
		font-family:Verdana;
		color:background;
	}
	#VotersInfoTable th
	{
		background-color:#7992A5;
		color:#FFFFFF;
		padding:5px;
	}
	#VotersInfoTable td
	{
		background-color:#E0E8EE;
		padding:5px;
	}

	.partyInfoHeading
	{
		font-size:14px;
		font-weight:bold;
		height:25px;
		padding-right:10px;
		padding-top:10px;
		text-decoration:underline;
		background-image:url(images/icons/partyPerformance/reportHeaders.png);
		width:790px;
		padding-left:10px;
	}
	.detailReportHeader
	{
		padding: 5px 5px 6px 15px;
		font-family: Trebuchet MS;
		font-weight: bold;
		font-size: 14px;
		background-image:url(images/icons/partyPerformance/detailReportHeading.png);
		width:500px;
		margin-top:5px;
		color:#FBAD2B;
	}
	#electionSummary_head
	{
		background-image:url("images/icons/partyPerformance/reportHeaders.png");
		font-size:14px;
		font-weight:bold;
		height:30px;
		margin-bottom:10px;
		padding-left:10px;
		padding-right:10px;
		padding-top:5px;
		width:790px;
	}
	#resizablepanel table
	{
		width:100%;
	}
	#electionSummary_body
	{
		border:2px solid #DBDCDB;
		margin-left:3px;
		margin-right:85px;
		padding-left:5px;
		margin-bottom:10px;
	}
	.yearHeaders
	{
		text-decoration:underline;
		font-weight:bold;
		font-size:13px;

	}
	.seatsDataTable th 
	{		
		padding:5px;
		color:#203360;
	}
	.seatsDataTable td 
	{		
		padding:5px;
		color:#69A74E;
		font-weight:bold;
	}
	.typeTable table
	{
		width:100%;
	}
	.mainPartyColor
	{
		color:#2B5181;
		font-size:12px;		
	}
	.oppositionPartColor
	{
		color:#7EADBC;
		font-size:12px;		
	}
	.votesMarginContentHead
	{
		color:#4A515A;
		font-weight:bold;
		padding:5px;
		text-decoration:underline;
	}
	#barChartMsg {
    font-size: 12px;
    margin-top: 47px;
    padding: 5px;
    width: 100%;
}
.partyPerformanceReportTable th
{
	background-color : #A6BAD1;
}
.bd
{
	font-family: verdana;
}


</style>
</head> 
<body>
<div id="partyPerformanceReportMainDiv">
<center>
<div id="titleDiv">
	   ${reportTitle}	
</div>
</center>
<br/>
<div style="margin-left: 43px;">
<div id="electionSummary" style="width:812px;background:#ffffff;">
	<div id="electionSummary_head"><%=electionSummaryHeading%></div>
	<div id="electionSummary_body" style="width:800px;">
		<table width="100%">		
			<tr>
				<td>
					<div class="yearHeaders"><%=electionYear%> : ${stateData.year}</div>
					<div>
						<table class="seatsDataTable" border="0">
							<tr>
								<th><%=totalSeatsWon%></th>
								<td>:</td>
								<td>${stateData.totalSeatsWon}</td>
							</tr>
							<tr>
								<th><%=votesPcntGained%></th>
								<td>:</td>
								<td>${stateData.totalPercentageOfVotesWon}</td>
							</tr>
							<c:if test="${stateData.allianceParties != null}">
								<tr>
									<th><%=alliances%></th>
									<td>:</td>
									<c:forEach var="allianceParty" items="${stateData.allianceParties}">							<td>${allianceParty.shortName}</td>					
									</c:forEach>									
								</tr>	
							</c:if>
							<tr>
								<th><%=diffVotesPctn%>(${stateData.year} - ${stateData.prevYear} ) </th>
								<td>:</td>
								<td>${stateData.diffOfTotalPercentageWinWithPrevElection}</td>
							</tr> 
						</table>
					</div>					
				</td>
				<td style="vertical-align: top;">
					<div class="yearHeaders"><%=electionYear%> : ${stateData.prevYear}</div> 
					<div>
						<table class="seatsDataTable">
							<tr>
								<th><%=totalSeatsWon%></th>
								<td>:</td>
								<td>${stateData.prevYearTotalSeatsWon}</td>
							</tr>
							<tr>
								<th><%=votesPcntGained%></th>
								<td>:</td>
								<td>${stateData.prevYeartotalPercentageOfVotesWon}</td>
							</tr>
							<c:if test="${stateData.previousYearAllianceParties != null}">
								<tr>
									<th><%=alliances%>:</th>
									<td>:</td>
									<c:forEach var="allianceParti" items="${stateData.previousYearAllianceParties}">				<td>${allianceParti.shortName}</td>													
									</c:forEach>									
								</tr>	
							</c:if>
						</table>
					</div>							
				</td>
			</tr>
			
		</table>	
	</div>
</div>

<table style="background:#ffffff;">
	<tr>
		<td colspan="2">
			<div class="partyInfoHeading"> 					
					<span style="float:right;margin-right:190px;"><%=yearVsSeatsChart%></span>
					<span style="float:left;margin-left:50px;"><%=partyPositions%> </span>				
			</div>
		</td>		
	</tr>
	<tr>
		<td style="vertical-align:top">
			<div id="partyResultsChartOuter" >
				<div id="partyResultsChart" style="width:100%">
					 <IMG id="chartImg" SRC="charts/<%=request.getAttribute("chartName")%>" WIDTH="350" HEIGHT="250">
				</div>
                       <c:if test="${(stateData.totalSeatsWon==0)&&(stateData.prevYearTotalSeatsWon==0)}">
                       	<script type="text/javascript">
						var elmt=document.getElementById("partyResultsChart");
						elmt.innerHTML='<h4><font color="green">Reason:<span id="barChartMsg"> '+partyName+' Party has 0 results<br>in 1st, 2nd, 3rd position so Unable To Build BarChart</span></font></h4>';
						</script>
					 </c:if>
			</div>		
		</td>
		<td style="vertical-align:top">
			<div id="partyPosChartOuter"  style="width:400px;">
				<div id="partyPosChart" style="width:100%">
					 <IMG id="chartImg" SRC="charts/<%=request.getAttribute("lineChartName")%>" WIDTH="350" HEIGHT="250">
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="partyInfoHeading"> 
				<%=partyPositions%>					
			</div>
		</td>		
	</tr>
	<tr>
		<td style="vertical-align:top" colspan="2">
			<div id="resizablepanelMain" class="yui-skin-sam" style="margin-top:10px;">
				<div id="resizablepanel" class="yui-skin-sam"></div>
			</div>
		</td>	
	</tr>	

</table>



<div id="partyPositions">
	<div id="partyPositionsHead">
		<span id="closeSpan" onclick="closeSpan()">X</span>
		<span id="closeLabelSpan"style="" onclick="closeSpan()"><u>Close</u></span>
		<center>
			<span id="labelHead"></span>		
		</center>
		<div id="positionHeading"></div>
	</div>
	<div id="partyPositionsBody" class="yui-skin-sam"></div>
</div>
<br/>

<div id="votesMarginInfo_main">
	<div id="votesMarginInfo_head"  class="partyInfoHeading"><%=analysisHeading%></div>
	<div id="votesMarginInfo_body" style="background:#ffffff;width:811px;">
		<table width="85%" style="margin-left:19px;">
			<tr>
				<td colspan="2" >
					<%=votesMarginOptionText%> : 
					<c:if test="${not empty stateData.allianceParties}">
						<input type="radio" name="alliance" value="${party}" onclick="callMarginVotes(this.value)" checked="checked">
					</c:if>
					<font style="color:red">${partyNameHidden}	</font>						
					
					<c:forEach var="alliance" items="${stateData.allianceParties}">
			
						<input type="radio" name="alliance" onclick="callMarginVotes(this.value)" value="${alliance.partyId}"><font style="color:#B77643">${alliance.shortName}	</font>							
					</c:forEach>					
				</td>
			</tr>
			<tr>
				<td style="vertical-align:top;">
					<div class="votesMarginContentHead"><%=wonConst%></div>
					<div id="votesMarginInfo_won"></div>
				</td>
				<td style="vertical-align:top;">
					<div class="votesMarginContentHead"><%=lostConst%></div>
					<div id="votesMarginInfo_lost"></div>
				</td>
			</tr>
		</table>
	</div>
</div>

<script type="text/javascript">
	callMarginVotes('${party}');	
</script>

<br/>

<div class="partyInfoHeading">
	<B><U>Detailed Report...</U></B>
</div>

<div style="background:#ffffff;width:811px;">
<c:set var="constituencyPositionsList" value="stateData.constituencyPositions" scope="session" />
<c:forEach var="constPositions" items="${stateData.constituencyPositions}" >
	<c:choose>
		<c:when test="${constPositions.type=='POSITIONS_WON_MINOR_BAND'}">			
			<div class="detailReportHeader">	
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=winPosLowMargin%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;">
					<span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span>	
					</td>
				</c:if>
				</tr></table>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_WON_MAJOR_BAND'}">		
			<div class="detailReportHeader">
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=winPosHighMargin%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}"/> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_LOST_MINOR_BAND'}">			
			<div class="detailReportHeader">
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=loosingPosLowMargin%></td> 
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_MAJOR_BAND'}">			
			<div class="detailReportHeader">			
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=loosingPosHighMargin%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>	
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_POSITIVE_SWING'}">
			<div class="detailReportHeader">	
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=winPosPositiveSwing%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_POSITIVE_SWING'}">
			<div class="detailReportHeader">		
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=loosingPosPositiveSwing%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_NEGATIVE_SWING'}">
			<div class="detailReportHeader">	
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=winPosNegativeSwing%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_NEGATIVE_SWING'}">
			<div class="detailReportHeader">
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=loosingPosNegativeSwing%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td> 
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>	
		<c:when test="${constPositions.type=='POSITIONS_LOST_BY_DROPPING_VOTES'}">
			<div class="detailReportHeader">	
				<table width="100%">
				<tr>
				<td style="width:310px;"><%=loosingPosDroppingPercent%></td>
				<td align="left"><font color="white"><c:out value="${constPositions.positionsWon}" /> seats</font></td>
				<c:if test="${constPositions.positionsWon > 0}" >
					<td style="text-align:right;"><span id="${constPositions.type}span" style="color: #FFFFCC; cursor: pointer;" onclick="showBand('${constPositions.type}');">View Details</span></td>
				</c:if>
				</tr></table>
			</div>
		</c:when>
</c:choose>
<div id="${constPositions.type}" style="display:none;background-color:#EAEAEA;margin-right:20px;" class="yui-skin-sam typeTable">
<center>
</center>
<!--<a href="#" onclick="closeSection('${constPositions.type}');">close</a><BR>-->
</div> 
	<script type="text/javascript">
	
	var partyObj={
					partyPerformanceArray:[],
					rebelsPerformanceArray:[]
				 };
	
	

	<c:if test="${constPositions.type == 'POSITIONS_WON_MAJOR_BAND' ||
							  constPositions.type == 'POSITIONS_WON_MINOR_BAND' ||
							  constPositions.type == 'POSITIONS_LOST_MINOR_BAND' ||
							  constPositions.type == 'POSITIONS_LOST_MAJOR_BAND'}">
		<c:forEach var="performance" items="${constPositions.constituencyPositionDetails}" >			
			
			var performanceObj={
									constituencyName:"${performance.constiuencyName}",
									candidateName:"${performance.candidateName}",
									mainParty:"${performance.partyName}",
									percentageOfVotes:"${performance.percentageOfVotes}",
									oppositionPartyPercentageOfVotes:"${performance.oppositePartyPercentageOfVotes}",
									oppositionParty:"${performance.oppositeParty}",
									oppositionPartyCandidate:"${performance.oppositePartyCandidate}",
									marginVotesPercentage:"${performance.marginVotesPercentage}",
									moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${performance.constituencyId})">Details</A>'
									
								};
			partyObj.partyPerformanceArray.push(performanceObj);
		</c:forEach>
       
	</c:if>
	<c:if test="${constPositions.type == 'POSITIONS_WON_WITH_POSITIVE_SWING' ||
					  constPositions.type == 'POSITIONS_LOST_WITH_POSITIVE_SWING' ||
					  constPositions.type == 'POSITIONS_WON_WITH_NEGATIVE_SWING' ||
					  constPositions.type == 'POSITIONS_LOST_WITH_NEGATIVE_SWING'}">	
		<c:forEach var="performance" items="${constPositions.constituencyPositionDetails}" >			
			
			var performanceObj={
									constituencyName:"${performance.constiuencyName}",
									candidateName:"${performance.candidateName}",
									mainParty:"${performance.partyName}",
									percentageOfVotes:"${performance.percentageOfVotes}",
									previousElectionPercentageOfVotesGained:"${performance.prevElectionPercentage}",
									oppositionPartyPercentageOfVotes:"${performance.oppositePartyPercentageOfVotes}",
									oppositionParty:"${performance.oppositeParty}",
									oppositionPartyCandidate:"${performance.oppositePartyCandidate}",
									marginVotesPercentage:"${performance.marginVotesPercentage}",
									moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${performance.constituencyId})">Details</A>'
								};
			partyObj.partyPerformanceArray.push(performanceObj);
		</c:forEach>
	</c:if>				
	<c:if test="${constPositions.type == 'POSITIONS_LOST_BY_DROPPING_VOTES'}">
		<c:forEach var="performance" items="${constPositions.constituencyPositionDetails}" >
			
			var performanceObj={
								constituencyName:"${performance.constiuencyName}",
								candidateName:"${performance.candidateName}",
								mainParty:"${performance.partyName}",
								percentageOfVotes:"${performance.percentageOfVotes}",
								previousElectionPercentageOfVotesGained:"${performance.prevElectionPercentage}",											
								percentageOfVotesPolled:"${performance.percentageOfVotesPolled}",
								previousElectionPercentageOfVotesPolled:"${performance.prevElectionPercentageOfVotesPolled}",
								previousElectionCandidate:"${performance.prevElectionCandidateName}",
								oppositionPartyPercentageOfVotes:"${performance.oppositePartyPercentageOfVotes}",
								oppositionParty:"${performance.oppositeParty}",
								oppositionPartyCandidate:"${performance.oppositePartyCandidate}",
								moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${performance.constituencyId})">Details</A>'
							};
			partyObj.partyPerformanceArray.push(performanceObj);	
		</c:forEach>
	</c:if>
	
	buildpartyPerformanceDataTable(partyObj,"${constPositions.type}");	
	
	</script>

</c:forEach> 
</div>
<br/>
<!--ref-->

<s:if test="stateData.toPartySwing.size > 0">

	<c:if test="${stateData.diffOfTotalPercentageWinWithPrevElection > 0}">
		<div class="partyInfoHeading">Votes from any one of the below parties are flown to you</div>
	</c:if>
	<c:if test="${stateData.diffOfTotalPercentageWinWithPrevElection < 0}">
		<div class="partyInfoHeading">Your votes are flown to any one of the below parties</div>
	</c:if>

<div style="background:#FFFFFF; margin-top: -3px; padding-left: 64px; width: 746px;margin-bottom: 15px; padding-bottom: 20px;padding-top: 15px;">
<div style="margin-left: 43px;clear:both;background:#ffffff;margin-top:3px;"> 
<table border="1" class="partyPerformanceReportTable">
	<c:forEach var="p" items="${stateData.toPartySwing}" >
	<tr>
		<th>${p.key}</th>
		<td style="background-color: #eec">${p.value}% </td>
	</tr>
	</c:forEach>
</table>
</div>
</s:if>
</div>


<s:if test="stateData.rebelPartyCandidates.size > 0">
<div class="partyInfoHeading"><b><U>Rebel Candidates </U></b></div>
<div id="rebelsDiv" class="yui-skin-sam" style="display: block; background-color: #ffffff;  margin-right: 58px;
    margin-top: -10px;">
</div>
<script language="javascript">//wkg
<c:forEach var="rebelsData" items="${stateData.rebelPartyCandidates}" >			

var rebelPerformanceObj={
		constiuencyName:"${rebelsData.constiuencyName}",
		candidateName:"${rebelsData.candidateName}",
		partyName:"${rebelsData.partyName}",
		percentageOfVotes:"${rebelsData.percentageOfVotes}",
		rank:"${rebelsData.rank}",
		oppositeParty:"${rebelsData.oppositeParty}",
		oppositePartyCandidate:"${rebelsData.oppositePartyCandidate}",
		oppositePartyPercentageOfVotes:"${rebelsData.oppositePartyPercentageOfVotes}",
		oppositePartyRank: "${rebelsData.oppositePartyRank}",						
		moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${rebelsData.constituencyId})">Details</A>'
					};
partyObj.rebelsPerformanceArray.push(rebelPerformanceObj);
</c:forEach>

	initializeRebelsDataTable();
	
</script>
<br>
</s:if>   
<div>
<s:form action="partyPerformanceJasper.action" style="float: left;margin-right:20px;">
<input type="hidden" name="jasperFile" value="jasper\partyPerformance\partyPerformanceReport.jrxml" />
<input type="hidden" name="type" value="normal" />
<input type="submit" value="Generate PDF">
</s:form>
<s:form action="partyPerformanceJasper.action">
<input type="hidden" name="jasperFile" value="jasper\partyPerformance\partyPerformanceReport.jrxml" />
<input type="hidden" name="type" value="detailed" />
<input type="submit" value="Generate Detailed PDF">
</s:form>
</div> </div>
<script language="javascript">
viewResizeablePanel();
viewPartyBasicResults();

</script>
</div>
</body>
</html>

