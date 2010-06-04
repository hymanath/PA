<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<s:url value='/styles/table.css'/>" rel="stylesheet" type="text/css" media="all"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<!-- YUI Dependency Files-->
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/conainer/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/dom-min.js"></script> 
	
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<script type="text/javascript" src="js/json/json-min.js"></script> 
	<script type="text/javascript" src="js/json/json.js"></script> 
	<script type="text/javascript" src="js/json/json-debug.js"></script> 
	
    <!-- YUI Skin Sam -->
   
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
    <link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-styles-2.8/container.css"> 
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-styles-2.8/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-styles-2.8/layout.css">	
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-styles-2.8/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-styles-2.8/button.css">	
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-styles-2.8/calendar.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">

   

<script type="text/javaScript">
var electionType = '${electionTypeLiteral}';
var electionYear = '${reportVO.year}';
function showBand(divtag)
{ 
	var divElmt=document.getElementById(divtag);
	var spanElmt=document.getElementById(divtag+"span");	
	if(!divElmt || !spanElmt)
		return;
	if(divElmt.style.display=="none")
	{
		divElmt.style.display = 'block';
		spanElmt.innerHTML="Hide Details";
	}
	else
	{
		divElmt.style.display = 'none';
		spanElmt.innerHTML="View Details";
	}
}

/*function getDetails(pos)
{		
	var imgElmt = document.getElementById("loaderGif");
	imgElmt.style.display='block';
	
	var position = pos;
	var party = '${stateData.partyId}';
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
	callAjax(param,jsObj);
}
*/

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

function callAjax(param,jsObj){
	var myResults;
	var url = "<%=request.getContextPath()%>/partyPositionAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayPartyPositions(jsObj,myResults);							
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

function callPositionAjax(param,jsObj){
	var myResults;
	var url = "<%=request.getContextPath()%>/partyPositionDetailsAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayPartyPositions(jsObj,myResults);							
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
	divElmtHead.innerHTML=" "+'${stateData.party}'+" In position : "+rank+" And Its Opposition Party Details";

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
							label : "Constituency",
							sortable : true
						},
						{
							key : "candidateName",		
							label : "Candidate Name",
							sortable : true
						},
						{
							key : "partyName",		
							label : "Party",
							sortable : true
						},
						{
							key : "votePercentage",
							parser:"number",
							label : "Votes&nbsp%",
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
										label : "Name",
										sortable : true
									},
									{
										key : "pName"+d,		
										label : "Party",
										sortable : true
									},
									{
										key : "vPercentage"+d,
										parser:"number",
										label : "Votes&nbsp%",
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
	            {key:"constituencyName",label : "Constituency",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "Candidate", sortable:true, resizeable:true}, 
				{key:"mainParty",label : "Party", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "V*%", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "OPV*%", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "OP*",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "OPC*", sortable:true, resizeable:true},
	            {key:"moreDetails",label : "More Details", sortable:true, resizeable:true}
	                
				
	        ]; 
			var myDataSource = new YAHOO.util.LocalDataSource(data.partyPerformanceArray); 		
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			myDataSource.responseSchema = { 
			fields : [
						{key : "constituencyName"}, {key : "candidateName"}, {key : "mainParty"}, {key : "percentageOfVotes",parser:"number"},
						{key :"oppositionPartyPercentageOfVotes",parser:"number"},{key : "oppositionParty"}, {key : "oppositionPartyCandidate"},
						{key : "moreDetails"}
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
							caption : "<font style='color:#2B5181;font-weight:bold;font-size:11px;'> * Main Party</font><font style='color:#7EADBC;font-weight:bold;font-size:11px;'> * Alliance Party</font><font style='font-size:11px;'> V*%=Votes Percentage, OPV*%=Opposition Party Votes Percentage, OP*=Opposition Party, OPC*=Opposition Party Candidate</font>",
							paginator : new YAHOO.widget.Paginator({ 
								rowsPerPage    : 10,
								template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
								rowsPerPageOptions: [20,40,60], 
							    pageLinks: 20 
								})
						}); 			
		}
		else if(divId == "POSITIONS_WON_WITH_POSITIVE_SWING" || divId == "POSITIONS_WON_WITH_NEGATIVE_SWING" || divId == "POSITIONS_LOST_WITH_POSITIVE_SWING" || divId == "POSITIONS_LOST_WITH_NEGATIVE_SWING")
		{			
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "Constituency",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "Candidate", sortable:true, resizeable:true}, 
				{key:"mainParty",label : "Party", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "V*% ", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesGained",label : "V*% in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "OPV*%", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "OP*",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "OPC*", sortable:true, resizeable:true},
	            {key:"moreDetails",label : "More Details", sortable:true, resizeable:true}    
				
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
							caption : "<font style='color:#2B5181;font-weight:bold'> * Main Party</font> <font style='color:#7EADBC;font-weight:bold'> * Alliance Party</font><font style='font-size:11px;'> V*%=Votes Percentage, VP*%=Votes Polled Percentage, EC*=Election Candidate, OPV*%=Opposition Party Votes Percentage, OP*=Opposition Party, OPC*=Opposition Party Candidate</font>",
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
	            {key:"constituencyName",label : "Constituency",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "Candidate", sortable:true, resizeable:true}, 
				{key:"mainParty",label : "Party", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "V*%", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesGained",label : "V*% in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"percentageOfVotesPolled",label : "VP*%", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesPolled",label : "VP*% in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"previousElectionCandidate",label : "<s:property value="stateData.prevYear" />EC*", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "OPV*%", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "OP*",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "OPC*", sortable:true, resizeable:true},
	            {key:"moreDetails",label : "More Details", sortable:true, resizeable:true}    
				
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
							caption : "<font style='color:#2B5181;font-weight:bold'> * Main Party</font> <font style='color:#7EADBC;font-weight:bold'> * Alliance Party</font><font style='font-size:11px;'> V*%=Votes Percentage, VP*%=Votes Polled Percentage, EC*=Election Candidate, OPV*%=Opposition Party Votes Percentage, OP*=Opposition Party, OPC*=Opposition Party Candidate</font>",
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

		var icon='';
			icon+='<table><tr><td align="right">Party Positions ... </td><td id="partyPosImg" align="right" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></td></tr></table>';
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
			str+='<table border="1" id="partyPositionDetailsTable">';
			str+='<tr>';
			str+='<th align="center">Party Name</th>';
			str+='<th align="center">PC*</th>';
			str+='<th align="center">Seats Won</th>';
			str+='<th align="center">2nd Pos</th>';
			str+='<th align="center">3rd Pos</th>';
			str+='<th align="center">4th Pos</th>';
			str+='<th align="center">Nth Pos</th>';
			str+='<th align="center">Votes %</th>';
			str+='<th align="center">Overall Votes %</th>';
			str+='</tr>';
			for(var i in arr)
		    {
			
			str+='<tr>';
			if(i == 0)
				str+='<td align="center" style="color:#FF0000">'+arr[i].partyName+'*</td>';
			else
			    str+='<td align="center">'+arr[i].partyName+'</td>';
			str+='<td align="center">'+arr[i].constituency+'</td>';
			if(arr[i].seats == 0)
				str+='<td align="center" style="color:#539E41;font-weight:bold;">'+arr[i].seats+'</td>';
			else
			   str+='<td align="center"><a href="javascript:{}" style="color:#539E41;" onclick="getPartyPositionDetails(1,\''+arr[i].partyId+'\')">'+arr[i].seats+'</a></td>';

			if(arr[i].secondPos == 0)
               str+='<td align="center" style="color:#C44C50;font-weight:bold;">'+arr[i].secondPos+'</td>';
			else
			   str+='<td align="center"><a href="javascript:{}" style="color:#E8A0A5;font-weight:bold;" onclick="getPartyPositionDetails(2,\''+arr[i].partyId+'\')">'+arr[i].secondPos+'</td>';

			if(arr[i].thirdPos == 0)
               str+='<td align="center" style="color:#FF979E;font-weight:bold;">'+arr[i].thirdPos+'</td>';
			else
               str+='<td align="center"><a style="color:#FF979E;font-weight:bold;" href="javascript:{}" onclick="getPartyPositionDetails(3,\''+arr[i].partyId+'\')">'+arr[i].thirdPos+'</td>';

			if(arr[i].fourthPos == 0)
               str+='<td align="center" style="color:#FF7F87;font-weight:bold;">'+arr[i].fourthPos+'</td>';
			else
			   str+='<td align="center"><a href="javascript:{}" style="color:#FF7F87;font-weight:bold;" onclick="getPartyPositionDetails(4,\''+arr[i].partyId+'\')">'+arr[i].fourthPos+'</td>';

			if(arr[i].nthPos == 0)
                str+='<td align="center" style="color:#FF1515;font-weight:bold;">'+arr[i].nthPos+'</td>';
			else
		    	str+='<td align="center"><a href="javascript:{}" style="color:#FF1515;font-weight:bold;" onclick="getPartyPositionDetails(-1,\''+arr[i].partyId+'\')">'+arr[i].nthPos+'</td>';
		    str+='<td align="center">'+arr[i].votesPercent+'</td>';
			str+='<td align="center">'+arr[i].overallVotesPercent+'</td>';
			str+='</tr>';
			}
			str+='<tr>';
			str+='<th colspan="8" style="color:#FF0000">';
			str+='<b>* Main Party , PC* - Participated Constituencies</b>';
			str+='<span id="partyPosImg" align="right" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/partypositions.gif" /></img></span>';
			str+='</th>';
			str+='</tr>';
			str+='</table>';

		 myPanel = new YAHOO.widget.Panel("resizablepanel", {
					 width: "800px", 
					 fixedcenter: false, 
					 constraintoviewport: false, 
					 underlay: "none", 
					 close: false, 
					 visible: true, 
					 draggable: false
		   });
      // myPanel.setHeader(icon);
       myPanel.setBody(str);
	  // myPanel.setFooter(icon);
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

		/*var columns =
		[
			{key: "partyName", sortable: true, resizeable: true },
			{key: "secndPos", sortable: true, resizeable: true },
			{key: "seatsWon", sortable: true, resizeable: true }
		];
		var table = new YAHOO.widget.DataTable( "datatable", columns, incomeData);*/

        
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
		label : "Constituency",
		sortable : true
	}, {
		key : "candidateName",
		label : "Candidate",
		sortable : true
	}, {
		key : "partyName",
		label : "Rebel Party",
		sortable : true
	}, {
		key : "percentageOfVotes",
		label : "Votes %",
		sortable : true
	}, {
		key : "rank",
		label : "Rank",
		sortable : true
	}, {
		key : "oppositeParty",
		label : "Party",
		sortable : true
	}, {
		key : "oppositePartyCandidate",
		label : "Candidate",
		sortable : true
	} , {
		key : "oppositePartyPercentageOfVotes",
		label : "Votes %",
		sortable : true
	} , {
		key : "oppositePartyRank",
		label : "Rank",
		sortable : true
	} , {
		key : "moreDetails",
		label : "More Details",
		sortable: true
		
	} ];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,		        
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [20,40,60], 
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
</script>

<style type="text/css">
	#partyPerformanceReportMainDiv
	{
		text-align:left;
		margin-left:50px;
		font-size:12px;
	}
	#partyPositions
	{
		background-color:#DCE3E9;
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
		background-color:#A6BAD1;
		text-decoration:underline;
	}
	#partyPositionsBody table
	{
		width:100%;
	}

	.yui-skin-sam .yui-dt-liner 
	{
		padding:0px;
	}
	.yui-skin-sam thead .yui-dt-sortable
	{
		background-color:#B0C7EB;
		color:#707070;
		font-size:11px;
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
		margin-left:15px;
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
		font-size:14px;
		font-weight:bold;
		height:20px;
		padding-right:10px;
		padding-top:5px;
		padding-left:4px;
		background-image:url(images/icons/partyPerformance/electionSummary.png);
		width:127px;
		margin-bottom:10px;
	}
	#resizablepanel table
	{
		width:100%;
	}
	#electionSummary_body
	{
		padding-left:20px;
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
		color:#6C5846;
	}
	.seatsDataTable td 
	{		
		padding:5px;
		color:#A97F6A;
		font-weight:bold;
	}
	.typeTable table
	{
		width:100%;
	}

	/*.reportHeaders
	{
		background-image:url(images/icons/partyPerformance/reportHeaders.png);
		width:800px;
	}*/
	.mainPartyColor
	{
		color:#2B5181;
		font-size:10px;		
	}
	.oppositionPartColor
	{
		color:#7EADBC;
		font-size:10px;		
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
<br/><br/><br/>
 <!--383a3c-->
<div style="margin-left: 15px;">
<div id="electionSummary">
	<div id="electionSummary_head">Election Summary</div>
	<div id="electionSummary_body">
		<table width="100%">		
			<tr>
				<td>
					<div class="yearHeaders">In year ${stateData.year}</div>
					<div>
						<table class="seatsDataTable">
							<tr>
								<th>Total Number Of Seats Won </th>
								<td>:</td>
								<td>${stateData.totalSeatsWon}</td>
							</tr>
							<tr>
								<th>Votes percentage Gained By Party </th>
								<td>:</td>
								<td>${stateData.totalPercentageOfVotesWon}</td>
							</tr>
							<tr>
								<th>Votes % Difference (${stateData.year} - ${stateData.prevYear} ) </th>
								<td>:</td>
								<td>${stateData.diffOfTotalPercentageWinWithPrevElection}</td>
							</tr> 
						</table>
					</div>					
				</td>
				<td style="vertical-align: top;">
					<div class="yearHeaders">In year  ${stateData.prevYear}</div> 
					<div>
						<table class="seatsDataTable">
							<tr>
								<th>Total Number Of Seats Won</th>
								<td>:</td>
								<td>${stateData.prevYearTotalSeatsWon}</td>
							</tr>
							<tr>
								<th>Votes percentage Gained By Party</th>
								<td>:</td>
								<td>${stateData.prevYeartotalPercentageOfVotesWon}</td>
							</tr>
						</table>
					</div>							
				</td>
			</tr>
			
		</table>	
	</div>
</div>

<table>
	<tr>
		<td colspan="2">
			<div class="partyInfoHeading"> 					
					<span style="float:right;margin-right:250px;">Year Vs Seats</span>
					<span style="float:left">Party Positions Graph </span>				
			</div>
		</td>		
	</tr>
	<tr>
		<td style="vertical-align:top">
			<div id="partyResultsChartOuter" >
				<div id="partyResultsChart" style="width:100%">
					 <IMG id="chartImg" SRC="charts/<%=request.getAttribute("chartName")%>" WIDTH="350" HEIGHT="250">
				</div>
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
				Party Positions					
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
<br/><br/>

<div class="partyInfoHeading">
	<B><U>Detailed Report...</U></B>
</div>
<br/>
<div>
<c:set var="constituencyPositionsList" value="stateData.constituencyPositions" scope="session" />
<c:forEach var="constPositions" items="${stateData.constituencyPositions}" >
	<c:choose>
		<c:when test="${constPositions.type=='POSITIONS_WON_MINOR_BAND'}">			
			<div class="detailReportHeader">	
				<table width="100%">
				<tr>
				<td style="width:340px;">Winning Positions with lower  %  margin </td>
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
				<td style="width:340px;">Winning Positions with highest % margin </td>
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
				<td style="width:340px;">Losing Positions with lower  %  margin </td> 
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
				<td style="width:340px;">Losing Positions with highest % margin</td>
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
				<td style="width:340px;">Winning Positions with Positive Swing </td>
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
				<td style="width:340px;">Loosing Positions with Positive Swing </td>
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
				<td style="width:340px;">Winning Positions with Negative Swing </td>
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
				<td style="width:340px;">Loosing Positions with Negative Swing  </td>
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
				<td style="width:340px;">Losing Positions with droping voting percentage  </td>
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
									moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${performance.constituencyId})">More Details</A>'
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
									moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${performance.constituencyId})">More Details</A>'
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
								moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${performance.constituencyId})">More Details</A>'
							};
			partyObj.partyPerformanceArray.push(performanceObj);	
		</c:forEach>
	</c:if>
	
	buildpartyPerformanceDataTable(partyObj,"${constPositions.type}");	
	
	</script>

</c:forEach> 
</div>
<br/><br/>

<div class="partyInfoHeading">
 <s:label labelposition="left"><b><U>Your votes are flown to any one of the below parties</U></b></s:label>
</div>

<div style="margin-left: 15px;"> 
<table class="partyPerformanceReportTable" border="1">
	<c:forEach var="p" items="${stateData.toPartySwing}" >
	<tr>
		<th>${p.key}</th>
		<td style="background-color: #eec">${p.value}% </td>
	</tr>
	</c:forEach>
</table>
</div>
<br>

<s:if test="stateData.rebelPartyCandidates.size > 0">
<div class="partyInfoHeading"><s:label labelposition="left"><b><U>Rebel Candidates </U></b></s:label></div>
<div id="rebelsDiv" class="yui-skin-sam" style="display: block; background-color: rgb(234, 234, 234); margin-right: 20px;">
<!--<display:table class="partyPerformanceReportTable" name="${stateData.rebelPartyCandidates}" id="rebelsTable" style="margin-top:0px;"> 
							<display:column title="Constiuency" property="constiuencyName" />
							<display:column title="Candidate" property="candidateName" />
							<display:column title="RebelParty" property="partyName" />
							<display:column title="% of Votes" property="percentageOfVotes" />
							<display:column title="Position" property="rank" />
							<display:column title="Party" property="oppositeParty" />
							<display:column title="Candidate" property="oppositePartyCandidate" />
							<display:column title="% of Votes" property="oppositePartyPercentageOfVotes" />
							<display:column title="Position" property="oppositePartyRank" />
							<display:column title="constituencyId" property="constituencyId" />
							
</display:table>	
--></div>
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
		moreDetails: '<A href="javascript:{}" onclick="openConstituencyResultsWindow(${rebelsData.constituencyId})">More Details</A>'
					};
partyObj.rebelsPerformanceArray.push(rebelPerformanceObj);
</c:forEach>

	initializeRebelsDataTable();
	
</script>
<br>
</s:if>   
<div>
<s:form action="partyPerformanceJasper.action?jasperFile=jasper\partyPerformance\partyPerformanceReport.jrxml&type=normal" style="float: left;margin-right:20px;">
<input type="submit" value="Generate PDF">
</s:form>
<s:form action="partyPerformanceJasper.action?jasperFile=jasper\partyPerformance\partyPerformanceReport.jrxml&type=detailed">
<input type="submit" value="Generate Detailed PDF">
</s:form>
</div>
<script language="javascript">
viewResizeablePanel();
viewPartyBasicResults();
</script>
</div>
</body>
</html>

