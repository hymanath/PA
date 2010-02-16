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
	<!-- Dependencies -->
	<script src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script>
	<script src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script>
	<script src="http://yui.yahooapis.com/2.8.0r4/build/datasource/datasource-min.js"></script>
	<script src="http://yui.yahooapis.com/2.8.0r4/build/json/json-min.js"></script>
	<script src="http://yui.yahooapis.com/2.8.0r4/build/swf/swf-min.js"></script>
	
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
		spanElmt.innerHTML="Display Details";
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
	var key3={key : "votePercentage",parser:"number"};
	resultsDataSource.responseSchema.fields.push(key1);
	resultsDataSource.responseSchema.fields.push(key2);
	resultsDataSource.responseSchema.fields.push(key3);

	for (var k in  info[0].oppPartyPositionInfoList)
	{		
		var key4={key : "cName"+k};
		var key5={key : "pName"+k};		
		var key7={key : "vPercentage"+k,parser:"number"};
		resultsDataSource.responseSchema.fields.push(key4);
		resultsDataSource.responseSchema.fields.push(key5);
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

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};	
		
		if(divId == "POSITIONS_WON_MAJOR_BAND" || divId == "POSITIONS_WON_MINOR_BAND" || divId == "POSITIONS_LOST_MINOR_BAND" || divId == "POSITIONS_LOST_MAJOR_BAND")
		{	
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "Constituency",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "Candidate", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "Votes %", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "Opposition Party Votes %", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "Opposition Party",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "Opposition Party Candidate", sortable:true, resizeable:true}    
				
	        ]; 
			var myDataSource = new YAHOO.util.LocalDataSource(data.partyPerformanceArray); 		
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			myDataSource.responseSchema = { 
			fields : [
						{key : "constituencyName"}, {key : "candidateName"}, {key : "percentageOfVotes",parser:"number"},
						{key :"oppositionPartyPercentageOfVotes",parser:"number"},{key : "oppositionParty"}, {key : "oppositionPartyCandidate"}
					 ]
	        };
			var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource,myConfigs); 			
		}
		else if(divId == "POSITIONS_WON_WITH_POSITIVE_SWING" || divId == "POSITIONS_WON_WITH_NEGATIVE_SWING" || divId == "POSITIONS_LOST_WITH_POSITIVE_SWING" || divId == "POSITIONS_LOST_WITH_NEGATIVE_SWING")
		{			
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "Constituency",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "Candidate", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "Votes % ", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesGained",label : "Votes % in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "Opposition Party Votes %", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "Opposition Party",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "Opposition Party Candidate", sortable:true, resizeable:true}    
				
	        ]; 

			var myDataSource = new YAHOO.util.DataSource(data.partyPerformanceArray); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["constituencyName","candidateName","percentageOfVotes","previousElectionPercentageOfVotesGained","oppositionPartyPercentageOfVotes","oppositionParty","oppositionPartyCandidate"] 
	        };
			 var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource,myConfigs); 
		}		  
		else if(divId == "POSITIONS_LOST_BY_DROPPING_VOTES")
		{
			var myColumnDefs = [ 	           
	            {key:"constituencyName",label : "Constituency",sortable:true,resizeable:true}, 
				{key:"candidateName",label : "Candidate", sortable:true, resizeable:true}, 
				{key:"percentageOfVotes",label : "Votes %", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesGained",label : "Votes % in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"percentageOfVotesPolled",label : "Votes Polled %", sortable:true, resizeable:true},
				{key:"previousElectionPercentageOfVotesPolled",label : "Votes Polled % in <s:property value="stateData.prevYear" />", sortable:true, resizeable:true},
				{key:"previousElectionCandidate",label : "<s:property value="stateData.prevYear" />  Election Candidate", sortable:true, resizeable:true},
				{key:"oppositionPartyPercentageOfVotes",label : "Opposition Party Votes %", sortable:true, resizeable:true}, 
	            {key:"oppositionParty",label : "Opposition Party",sortable:true, resizeable:true}, 
	            {key:"oppositionPartyCandidate",label : "Opposition Party Candidate", sortable:true, resizeable:true}    
				
	        ]; 

			var myDataSource = new YAHOO.util.DataSource(data.partyPerformanceArray); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["constituencyName","candidateName","percentageOfVotes","previousElectionPercentageOfVotesGained","percentageOfVotesPolled","previousElectionPercentageOfVotesPolled","previousElectionCandidate","oppositionPartyPercentageOfVotes","oppositionParty","oppositionPartyCandidate"] 
	        };
			var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource,myConfigs); 
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
		 str+='<table border="1" id="partyPositionDetailsTable">';
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
                 width: "300px", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
       });
	   myPanel.setHeader("");
       myPanel.setBody(str);
       myPanel.render();

	}

	function viewResizeablePanel()
	{
		var arr = new Array();
		var icon='';
			icon+='<table><tr><td id="partyPosImg" align="left" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/partypositions.gif" /></img></td></tr></table>';
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
						votesPercent:'${results.votesPercentage}'
					  };
					arr.push(obj);
		</c:forEach>
            var str='';
			str+='<table border="1" id="partyPositionDetailsTable">';
			str+='<tr>';
			str+='<th align="center">Party Name</th>';
			str+='<th align="center">Seats Won</th>';
			str+='<th align="center">2nd Pos</th>';
			str+='<th align="center">3rd Pos</th>';
			str+='<th align="center">4th Pos</th>';
			str+='<th align="center">Nth Pos</th>';
			str+='<th align="center">Participated Constituencies</th>';
			str+='<th align="center">Votes %</th>';
			str+='</tr>';
			for(var i in arr)
		    {
			
			str+='<tr>';
			if(i == 0)
				str+='<td align="center" style="color:#FF0000">'+arr[i].partyName+'*</td>';
			else
			    str+='<td align="center">'+arr[i].partyName+'</td>';

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
			str+='<td align="center">'+arr[i].constituency+'</td>';
            str+='<td align="center">'+arr[i].votesPercent+'</td>';
			str+='</tr>';
			}
			str+='<tr>';
			str+='<b><th colspan="8" style="color:#FF0000">* Main Party</b></th>';
			str+='</tr>';
			str+='</table>';

       myPanel = new YAHOO.widget.Panel("resizablepanel", {
                 width: "450px", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: false, 
                 visible: true, 
                 draggable: false
       });
       myPanel.setHeader("Party Positions ...");
       myPanel.setBody(str);
	   myPanel.setFooter(icon);
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
						votesPercent:'${results.votesPercentage}'
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

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("rebelsTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
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
		} ]
	};

	var resultsColumnDefs = [ {
		key : "constiuencyName",
		label : "Constiuency Name",
		sortable : true
	}, {
		key : "candidateName",
		label : "Candidate Name",
		sortable : true
	}, {
		key : "partyName",
		label : "Rebel Party",
		sortable : true
	}, {
		key : "percentageOfVotes",
		label : "% Votes",
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
		label : "Candidate Name",
		sortable : true
	} , {
		key : "oppositePartyPercentageOfVotes",
		label : "Votes %",
		sortable : true
	} , {
		key : "oppositePartyRank",
		label : "Rank",
		sortable : true
	} ];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
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
		padding:4px;
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
		margin-left:20px;
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
		background-color:#8FAFD0;
		color:#FFFFFF;
		font-size:15px;
		font-weight:bold;
		margin-left:15px;
		margin-right:100px;
		padding:6px;		
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
 
<div style="margin-left: 15px;">

    <table>
	<tr>
		<th style="background-color: #E9E9E9;font-weight:bold;font-size:12px;"><u>State : </u></th>
		<td><b><s:property value="stateData.state" /></b></td>
		<td></td>
		<th style="background-color: #E9E9E9;font-weight:bold;font-size:12px;"><u>Party :</u></th>
		<td><b><s:property value="stateData.party" /></b></td>
	</tr>
	<tr id="district"
	  <% java.lang.String district = (java.lang.String) request.getAttribute("stateData.district");
		if(district == null) { %> 
			style="display:none"
		<% } %>
		>
		<th>District</th>
		<td style="background-color: #ccb"><s:property value="stateData.district" /></td>
	</tr>
	</table>

<table>
	<tr>
		<td>
			<div id="partyPosChartOuter"  style="width:400px;">
				<div id="partyPosChart" style="width:100%">
					 <IMG id="chartImg" SRC="charts/<%=request.getAttribute("lineChartName")%>" WIDTH="350" HEIGHT="250">
				</div>
			</div>
		</td>
		<td>
			<div id="partyResultsChartOuter"  style="margin-top:40px;">
				<div id="partyResultsChart" style="width:100%">
					 <IMG id="chartImg" SRC="charts/<%=request.getAttribute("chartName")%>" WIDTH="400" HEIGHT="350">
				</div>
			</div>		
		</td>
	</tr>
	<tr>
		<td>
			<div id="seatsDetailsDiv" class="yui-skin-sam" style="width:40%height:210px;">
				<div id="party" class="yui-skin-sam"></div>
			</div>
		</td>
		<td>
			<div id="resizablepanelMain" class="yui-skin-sam" style="margin-top:25px;">
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

<div>
	<B><U>Detailed Report...</U></B>
</div>
<br/>
<div>
<c:set var="constituencyPositionsList" value="stateData.constituencyPositions" scope="session" />
<c:forEach var="constPositions" items="${stateData.constituencyPositions}" >
	<c:choose>
		<c:when test="${constPositions.type=='POSITIONS_WON_MINOR_BAND'}">			
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with lower % margin: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>	
				</c:if>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_WON_MAJOR_BAND'}">		
			<div style="padding: 5px 5px 10px 0px;font-family: Trebuchet MS;font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with highest % margin: <c:out value="${constPositions.positionsWon}" /> 
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_LOST_MINOR_BAND'}">			
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Losing Positions with lower % margin: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_MAJOR_BAND'}">			
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Losing Positions with highest % margin: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display details</span>
				</c:if>
			</div>
		</c:when>	
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_POSITIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with Positive Swing: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_POSITIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Loosing Positions with Positive Swing: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>		
		<c:when test="${constPositions.type=='POSITIONS_WON_WITH_NEGATIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Winning Positions with Negative Swing: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>
		<c:when test="${constPositions.type=='POSITIONS_LOST_WITH_NEGATIVE_SWING'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Loosing Positions with Negative Swing: <c:out value="${constPositions.positionsWon}" /> 
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>	
		<c:when test="${constPositions.type=='POSITIONS_LOST_BY_DROPPING_VOTES'}">
			<div style="padding: 5px 5px 10px 0px; font-family: Trebuchet MS; font-weight: bold; font-size: 14px;">
				<img height="10" width="10" src="<%=request.getContextPath()%><s:property value="getText('iconURL')" />arrow.png"/> 
				Losing Positions with droping voting percentage: <c:out value="${constPositions.positionsWon}" />
				<c:if test="${constPositions.positionsWon > 0}" >
					<span id="${constPositions.type}span" style="color: blue; cursor: pointer;" onclick="showBand('${constPositions.type}');">Display Details</span>
				</c:if>
			</div>
		</c:when>
</c:choose>
<div id="${constPositions.type}" style="display:none;" class="yui-skin-sam">
<center>
</center>
<!--<a href="#" onclick="closeSection('${constPositions.type}');">close</a><BR>-->
</div> 
	<script type="text/javascript">
	
	var partyObj={
					partyPerformanceArray:[]
				 };
	

	<c:if test="${constPositions.type == 'POSITIONS_WON_MAJOR_BAND' ||
							  constPositions.type == 'POSITIONS_WON_MINOR_BAND' ||
							  constPositions.type == 'POSITIONS_LOST_MINOR_BAND' ||
							  constPositions.type == 'POSITIONS_LOST_MAJOR_BAND'}">
		<c:forEach var="performance" items="${constPositions.constituencyPositionDetails}" >
			var performanceObj={
									constituencyName:"${performance.constiuencyName}",
									candidateName:"${performance.candidateName}",
									percentageOfVotes:"${performance.percentageOfVotes}",
									oppositionPartyPercentageOfVotes:"${performance.oppositePartyPercentageOfVotes}",
									oppositionParty:"${performance.oppositeParty}",
									oppositionPartyCandidate:"${performance.oppositePartyCandidate}"
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
									percentageOfVotes:"${performance.percentageOfVotes}",
									previousElectionPercentageOfVotesGained:"${performance.prevElectionPercentage}",
									oppositionPartyPercentageOfVotes:"${performance.oppositePartyPercentageOfVotes}",
									oppositionParty:"${performance.oppositeParty}",
									oppositionPartyCandidate:"${performance.oppositePartyCandidate}"
								};
			partyObj.partyPerformanceArray.push(performanceObj);
		</c:forEach>
	</c:if>				
	<c:if test="${constPositions.type == 'POSITIONS_LOST_BY_DROPPING_VOTES'}">
		<c:forEach var="performance" items="${constPositions.constituencyPositionDetails}" >
			var performanceObj={
								constituencyName:"${performance.constiuencyName}",
								candidateName:"${performance.candidateName}",
								percentageOfVotes:"${performance.percentageOfVotes}",
								previousElectionPercentageOfVotesGained:"${performance.prevElectionPercentage}",											
								percentageOfVotesPolled:"${performance.percentageOfVotesPolled}",
								previousElectionPercentageOfVotesPolled:"${performance.prevElectionPercentageOfVotesPolled}",
								previousElectionCandidate:"${performance.prevElectionCandidateName}",
								oppositionPartyPercentageOfVotes:"${performance.oppositePartyPercentageOfVotes}",
								oppositionParty:"${performance.oppositeParty}",
								oppositionPartyCandidate:"${performance.oppositePartyCandidate}"
							};
			partyObj.partyPerformanceArray.push(performanceObj);	
		</c:forEach>
	</c:if>
	
	buildpartyPerformanceDataTable(partyObj,"${constPositions.type}");
	
	</script>

</c:forEach> 
</div>
<br/><br/>

 <s:label labelposition="left"><b><U>Your votes are flown to any one of the below parties:</U></b></s:label>
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
<s:label labelposition="left"><b><U>Rebel Candidates::</U></b></s:label>
<div id="rebelsDiv" class="yui-skin-sam">
<display:table class="partyPerformanceReportTable" name="${stateData.rebelPartyCandidates}" id="rebelsTable" style="margin-top:0px;"> 
							<display:column title="Constiuency Name" property="constiuencyName" />
							<display:column title="Candidate Name" property="candidateName" />
							<display:column title="RebelParty" property="partyName" />
							<display:column title="% of Votes" property="percentageOfVotes" />
							<display:column title="Position" property="rank" />
							<display:column title="Party" property="oppositeParty" />
							<display:column title="Candidate Name" property="oppositePartyCandidate" />
							<display:column title="% of Votes" property="oppositePartyPercentageOfVotes" />
							<display:column title="Position" property="oppositePartyRank" />
							
</display:table>	
</div>
<script language="javascript">
	initializeRebelsDataTable();
	//initializePartyPositionsDetails();
	
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

