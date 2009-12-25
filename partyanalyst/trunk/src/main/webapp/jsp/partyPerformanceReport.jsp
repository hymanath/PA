<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<s:url value='/styles/table.css'/>" rel="stylesheet" type="text/css" media="all"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<script type="text/javascript" src="js/yahoo/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>

<!-- Combo-handled YUI CSS files: -->
<link rel="stylesheet" type="text/css"
	href="http://yui.yahooapis.com/combo?2.8.0r4/build/datatable/assets/skins/sam/datatable.css">
<!-- Combo-handled YUI JS files: -->
<script type="text/javascript"
	src="http://yui.yahooapis.com/combo?2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js&2.8.0r4/build/element/element-min.js&2.8.0r4/build/datasource/datasource-min.js&2.8.0r4/build/datatable/datatable-min.js"></script>

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
function fadeIn(objId, opacity)
{
	var obj = document.getElementById(objId);
	if(obj && opacity <= 100){
		setOpacity(obj, opacity);
		opacity += 4;
		window.setTimeout("fadeIn('"+objId+"',"+opacity+")", 100);		
	}
}
function setOpacity(obj, opacity)
{
	opacity = (opacity == 100)?99.999:opacity;
	obj.style.filter ="alpha(opacity:"+opacity+")";// IE/Win
	obj.style.KHTMLOpacity = opacity/100;// Safari<1.2, Konqueror
	obj.style.MozOpacity = opacity/100;// Older Mozilla and Firefoxv
	obj.style.opacity = opacity/100;// Safari 1.2, newer Firefox and Mozilla, CSS3
}

function getDetails(pos)
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

	
	var jsObj=
	{
			positionValue:position,
			partyValue:party,			
			eId:electionTypeId,
			stateValue:state,
			yearValue:year,
			districtValue:district,
			hasAlliances:alliances
	}
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	callAjax(param,jsObj);
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

function displayPartyPositions(jsObj,data)
{
	var imgElmt = document.getElementById("loaderGif");
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
	fadeIn('partyPositions',40);
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
	var count=1;

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
		count++;
		var key4={key : "cName"+k};
		var key5={key : "pName"+k};		
		var key7={key : "vPercentage"+k,parser:"number"};
		resultsDataSource.responseSchema.fields.push(key4);
		resultsDataSource.responseSchema.fields.push(key5);
		resultsDataSource.responseSchema.fields.push(key7);
	}

	var headElmt = document.getElementById('positionHeading');
	var tstr='<table width="100%">';
	tstr+='<tr>';
	tstr+='<td align="center">Candidate Details in position '+rank+'</td>';
	for(var i=1;i<count;i++)
	{	
		tstr+='<td align="center"> Candidate Details in position '+i+'</td>';
	}
	tstr+='</tr>';
	tstr+='</table>';

	headElmt.innerHTML=tstr;
	//--------
	var resultsColumnDefs = [];
	var obj1= {
		key : "constituencyName",		
		label : "Constituency Name",
		sortable : true
	};
	var obj2={
		key : "candidateName",		
		label : "Candidate Name",
		sortable : true
	};
	var obj3= {
		key : "votePercentage",
		parser:"number",
		label : "Votes&nbsp%",
		sortable : true
	};
	resultsColumnDefs.push(obj1);
	resultsColumnDefs.push(obj2);
	resultsColumnDefs.push(obj3);

	for (var d in info[0].oppPartyPositionInfoList)
	{
		var obj4={
		key : "cName"+d,		
		label : "Name",
		sortable : true
		};
		var obj5= {
			key : "pName"+d,		
			label : "Party",
			sortable : true
		};
		var obj7= {
			key : "vPercentage"+d,
			parser:"number",
			label : "Votes&nbsp%",
			sortable : true
		};	
		resultsColumnDefs.push(obj4);
		resultsColumnDefs.push(obj5);
		resultsColumnDefs.push(obj7);
	}

	var myDataTable = new YAHOO.widget.DataTable("partyPositionsBody",resultsColumnDefs, resultsDataSource,{});  

	}

	function buildpartyPerformanceDataTable(data,divId)
	{
		if(data.partyPerformanceArray == "")
			return;
		
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
			var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource, {}); 			
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
			 var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource, {}); 
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
			var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource, {}); 
		}	
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

	var myDataTable = new YAHOO.widget.DataTable("rebelsDiv",resultsColumnDefs, resultsDataSource,{});  

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
		position:absolute;
		margin-right:20px;
		margin-top:15px;
		z-index:10;
		display:none;
		opacity:0;
		left:155px;
		border:2px solid #839AB7;
	}
	#closeSpan
	{
		float:right;
		cursor:pointer;
		font-weight:bold;
		margin-right:10px;
		border:1px solid;
	}
	#partyPositionsBody
	{
		padding-left:10px;
		padding-bottom:20px;
		padding-right:10px;
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

	.yui-skin-sam .yui-dt-liner 
	{
		padding:0px;
	}
	.yui-skin-sam thead .yui-dt-sortable
	{
		background-color:#B0C7EB;
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
</style>
</head> 
<body>
<div id="partyPerformanceReportMainDiv">
<div style="padding: 5px; font-weight: bold; color: #46505B; font-family: Trebuchet MS; font-size: 15px;text-align:center;">
	<u>
		Party Performance Report for the year <s:property value="stateData.year" /> 
	</u>
	<!--<u><s:property value="stateData.state" /> State - Party <s:property value="stateData.party" /> </u> -->
</div>
<br/><br/>
 
<div style="margin-left: 15px;">

    <table>
	<tr>
		<th style="background-color: #ECF1F5"><u>State : </u></th>
		<td><b><s:property value="stateData.state" /></b></td>
		<td></td>
		<th style="background-color: #ECF1F5"><u>Party :</u></th>
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
<table class="partyPerformanceReportTable" border="1" width="385px">
	<tr>
	    <th></th>
	    <th align="center"><s:property value="stateData.year" /></th>
		<s:if test="stateData.prevYearTotalSeatsWon != 0">
		<th align="center"><s:property value="stateData.prevYear" /></th>
		</s:if> 
	</tr>
	<tr>
		<td style="background-color: #ECF1F5" align="center"><b>Seats Won</b></td>
		<td style="background-color: #ECF1F5" width="100px" align="center"><s:property value="stateData.totalSeatsWon" /></td>
		<s:if test="stateData.prevYearTotalSeatsWon != 0">
		<td style="background-color: #ECF1F5" width="100px" align="center"><s:property value="stateData.prevYearTotalSeatsWon" /></td>
		</s:if> 
	</tr>
	<tr>
		<td style="background-color: #ECF1F5" align="center"><b>Votes %</b></td>
		<td style="background-color: #ECF1F5" align="center"><s:property value="stateData.totalPercentageOfVotesWon" />&nbsp;%</td>
		<s:if test="stateData.prevYearTotalSeatsWon != 0">
		<td style="background-color: #ECF1F5" style="background-color: #FFFFFF" align="center"><s:property value="stateData.prevYeartotalPercentageOfVotesWon" />&nbsp;%</td>
		</s:if> 
	</tr>
	<s:if test="stateData.prevYearTotalSeatsWon != 0">
	<tr>
	    <td style="background-color: #ECF1F5" align="center" colspan="3"><b>Votes % Difference ( <s:property value="stateData.year" /> - <s:property value="stateData.prevYear" /> ) : </b>
        <s:if test="stateData.diffOfTotalPercentageWinWithPrevElection  < 0">
		<font color="red"><s:property value="stateData.diffOfTotalPercentageWinWithPrevElection" />%</font>
		</s:if>
		<s:if test="stateData.diffOfTotalPercentageWinWithPrevElection  > 0">
		<font color="green"><s:property value="stateData.diffOfTotalPercentageWinWithPrevElection" />%</font>
		</s:if>
		</td>
	</tr>
	</s:if>
</table>
</div>
<div style="left:650px;margin-right:20px;position:absolute;top:320px;">
	<IMG id="chartImg" SRC="charts/<%=request.getAttribute("chartName")%>" WIDTH="400" HEIGHT="350" style="opacity:0.0;">
</div>
<!--Total Seats Won: <s:property value="stateData.totalSeatsWon" />( <s:property value="stateData.diffSeatsWon" /> ) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<s:label labelposition="right">Total Percentage of Votes: <s:property value="stateData.totalPercentageOfVotesWon" />%( <s:property value="stateData.diffOfTotalPercentageWinWithPrevElection"/>%) </s:label> -->
<BR/><BR/>
 <s:label labelposition="left"><b><U>Party Positions:</U></b></s:label>
<!--<br><p align="center"> -->
<div style="margin-left: 15px;">
<c:set var="data" value="stateData" scope="session" />
<c:set var="myId" value="row" />
<table>
<tr>
<td>
<display:table class="partyPerformanceReportTable" name="stateData.positionDistribution" id="${myId}" length="1" cellpadding="10px" >
    <c:forEach var="pd" items="${stateData.positionDistribution}" varStatus="status">
    	<jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
		<c:choose>
			<c:when test="<%=status.getIndex() == 1%>">
			       	<c:if test="${pd.value > 0}">
					<display:column title="2nd Position" ><a href="javascript:{}" onclick="getDetails('2')"> <c:out value="${pd.value}" /> </a> </display:column>
					</c:if>
					<c:if test="${pd.value == 0}">
					<display:column title="2nd Position" ><c:out value="${pd.value}" /></display:column>
                    </c:if>
			</c:when>
			<c:when test="<%=status.getIndex() == 2%>">
			        <c:if test="${pd.value > 0}">
					<display:column title="3rd Position" ><a href="javascript:{}" onclick="getDetails('3')"> <c:out value="${pd.value}" /> </a> </display:column> 
					</c:if>
					<c:if test="${pd.value == 0}">
					<display:column title="3rd Position" ><c:out value="${pd.value}" /></display:column>
					</c:if>
			</c:when>
			<c:when test="<%=status.getIndex() == 3%>">
			        <c:if test="${pd.value > 0}">
					<display:column title="4th Position" ><a href="javascript:{}" onclick="getDetails('4')"> <c:out value="${pd.value}" /></a>  </display:column>
					</c:if>
					<c:if test="${pd.value == 0}">
					<display:column title="4th Position" ><c:out value="${pd.value}" /></display:column>
					</c:if>
			</c:when>
			<c:when test="<%=status.getIndex() == 4%>">
			        <c:if test="${pd.value > 0}">
					<display:column title="Nth Position" ><a href="javascript:{}" onclick="getDetails('-1')"> <c:out value="${pd.value}" /></a></display:column>
					</c:if>
					<c:if test="${pd.value == 0}">
					<display:column title="Nth Position" ><c:out value="${pd.value}" /></display:column>
					</c:if>
			</c:when>
		</c:choose>	          
	</c:forEach>		
</display:table>
</td>
<td>
<span>
	<img id="loaderGif" src="<%=request.getContextPath()%>/images/icons/arrows.gif" style="display:none;"/>
</span>
</td>
</tr></table>
</div>
<!--<center><IMG SRC="charts/partyPositionsChart_<%=request.getSession().getId()%>.png" WIDTH="300" HEIGHT="200"  BORDER="0" ></center> -->
<div id="partyPositions">
	<div id="partyPositionsHead">
		<span id="closeSpan" onclick="closeSpan()">X</span>
		<span id="closeLabelSpan"style="" onclick="closeSpan()"><u>Close</u></span>
		<center>
			<span id="labelHead"></span>		
		</center>
		<div id="positionHeading">Heading</div>
	</div>
	<div id="partyPositionsBody" class="yui-skin-sam"></div>
</div>
<br/><br/><br/><br/><br/>
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
<script type="text/javascript">
	fadeIn("chartImg",10);
</script>
</div>
</body>
</html>

