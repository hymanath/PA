<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
<title>Compared Results</title>
<style>
	.yui-skin-sam .yui-dt-liner 
	{
		margin:0 0 0 0px;
		padding:5px;
	}
	.yui-skin-sam .yui-dt table
	{
		border:2px solid #DFDFDF;
		border-collapse:separate;
		border-spacing:0;
		font-family:Verdana;
		color:#000000;
	}	
	#votesPercentageIncDiv table
	{
		width:100%;
	}
	#votesPercentageDecDiv table
	{
		width:100%;
	}	
	legend {
		background-color:#9696C0;
		color:#FFFFFF;
		font-family:status-bar;
		font-size:11px;
		font-weight:bold;
		padding:5px;		
    }    
	fieldset {
		border:4px solid #F6DFC9;
		margin-bottom:10px;
	}		
	.greenColorClass{
		color:green;
	}
	.redColorClass{
		color:red;
	}	
	.bodyClass
	{
		font-size:12px;
	}
	#completeOneField, #completeTwoField
	{
	border-bottom:1px solid #DBDCDB;
	border-left:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	margin-bottom:10px;
	width:500px;
	}
	h3 {
	background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
	color:#4B74C6;
	font-size:12px;
	font-weight:bold;
	height:20px;
	margin:0;
	padding:5px;
	text-align:left;
	width:485px;
	}
	.head {
	background-color:#EBEBEB;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	padding:5px;
	}
	.middle {
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
	text-align:center;
	}
	.edgeH {
	background-color:#EBEBEB;
	border-bottom:1px solid #DBDCDB;
	padding:5px;
	text-align:center;
	}
	#title
	{
	background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
	color:#4B74C6;
	font-size:12px;
	font-weight:bold;
	height:20px;
	margin-top:10px;
	margin-bottom:10px;
	padding:5px;
	text-align:left;	
	}
	#descDiv
	{
	color:DodgerBlue;
	font-size:12px;
	}
	#electionPageAjaxImgDiv
	{
		border:1px solid #ADADAD;
		font-size:12px;
		font-weight:bold;
		width:300px;
		margin-bottom:10px;
	}
	#alertMsg{
        color:red;
		font-size:23px;
	}
</style>
</head>
<script type="text/javascript">
var labelResources = { <%		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String firstPos = rb.getString("firstPos");			
		String secondPos = rb.getString("secondPos");
		String thirdPos = rb.getString("thirdPos");
		String fourthPos  = rb.getString("fourthPos");
		String nthPos  = rb.getString("nthPos");
		String pc = rb.getString("pc");
		String participatedConsts = rb.getString("participatedConsts");
		String party  = rb.getString("party");		
		String tc = rb.getString("tc");
		String tcDef  = rb.getString("tcDef");
		String pp = rb.getString("pp");
		String ppDef = rb.getString("ppDef");
		String pnp = rb.getString("pnp");
		String pnpDef = rb.getString("pnpDef");
		String stateWise = rb.getString("stateWise");
		String stateWiseDef = rb.getString("stateWiseDef");
		String dist = rb.getString("dist");
		String constituency = rb.getString("constituency");
		String candidate  = rb.getString("candidate");
		String votesPercentage = rb.getString("votesPercentage");
		String seatsWon = rb.getString("seatsWon");
		String votes = rb.getString("votes");
		String rank = rb.getString("rank");
		String electionType = rb.getString("electionType");
		String electionYear = rb.getString("electionYear");
		String votesEarned = rb.getString("votesEarned");
		String state = rb.getString("state");
		String results = rb.getString("results");
		String electionRes = rb.getString("electionRes");
		String oppPartyRes = rb.getString("oppPartyRes");
		
		ResourceBundle ecrRb = ResourceBundle.getBundle("ecr_Labels");
		String diff = ecrRb.getString("diff");
		String diffWonSeats = ecrRb.getString("diffWonSeats");
		String diffVotesPercent = ecrRb.getString("diffVotesPercent");
		String resultsHead = ecrRb.getString("resultsHead");
		String overAll = ecrRb.getString("overAll");
		String votesPcntInc = ecrRb.getString("votesPcntInc");
		String votesPcntDec = ecrRb.getString("votesPcntDec");
		String notConsidered = ecrRb.getString("notConsidered");
		String won = ecrRb.getString("won");
		String seatsDiff = ecrRb.getString("seatsDiff");
		String electionProf = ecrRb.getString("electionProf");
		%> }

var elecIdOne = '${elecIdOne}';
var elecIdTwo = '${elecIdTwo}';
var stateOrDistrictId = '${stateOrDistrictId}';
var partyId = '${partyId}';
var hasAlliances = '${hasAlliances}';
var electionType = '${electionType}';
var elecYear1= '${elecYear1}';
var elecYear2= '${elecYear2}';
function getComparedResults()
{
   var stateOrdistrictId;
   var elements = document.getElementsByTagName('input'); 
	  for(var i=0;i<elements.length;i++)
	  {
		if(elements[i].type=="radio" && elements[i].name=="comparedResults_radio" && elements[i].checked==true)
		{
			stateOrdistrictId = elements[i].value;
		}       

	  }
	  var elecId1=elecIdOne;
      var elecId2=elecIdTwo;
	  var party=partyId;
	  var hasAllianc=hasAlliances;
	  var stateOrdistrictId = stateOrDistrictId;
	  var jsObj= 
	  {
		  electionIdOne: elecIdOne,
		  electionIdTwo: elecIdTwo,
		  stateOrDistrictId: stateOrDistrictId,
		  partyId: partyId,
          hasAlliance: hasAlliances
	  }
	  var url = "<%=request.getContextPath()%>/electionComparisonCompareElectionsAjax.action?"+param;	
	  var param ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	  callAjax(param,jsObj, url);
}
function callAjax(param,jsObj, url){
	var myResults;
	var url = "<%=request.getContextPath()%>/electionComparisonAjax.action?"+param;
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText); 
							
							displayComparedResults(jsObj,myResults);
							var electionPageAjaxImgDiv = document.getElementById("electionPageAjaxImgDiv");
							electionPageAjaxImgDiv.style.display="none";
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
function displayComparedResults(jsObj,data)
{
	var str='';
	var headerElmt=document.getElementById("header");
	if(data.positionsYearOne[0]==null){
	  
	      str+='<div id="alertMsg">';
		  str+='<table>'; 
		  str+='<tr>No data available for the year '+'  '+data.yearOne+' to compare results.'; 
		  str+='</tr>'; 
		  str+='</table>'; 
		  str+='</div>';
		headerElmt.innerHTML = str;
		return;
	}
	if(data.positionsYearTwo[0]==null){
	  
	      str+='<div id="alertMsg">';
		  str+='<table>'; 
		  str+='<tr>No data available for the year '+'  '+data.yearTwo+' to compare results.'; 
		  str+='</tr>'; 
		  str+='</table>'; 
		  str+='</div>';
		headerElmt.innerHTML = str;
		return;
	}
	var elmt = document.getElementById("comparedResults");
	var titleElmt = document.getElementById("title");
	var electionPageAjaxImgDiv = document.getElementById("electionPageAjaxImgDiv");
	electionPageAjaxImgDiv.style.display="none";
	
	if(!elmt)
		return;
	titleElmt.innerHTML = 'Constituencywise '+electionType+' Results Comparision For '+data.locName+'';
	
	str+='<table>';
	str+='<tr>';
	str+='<td valign="top">';
	str+='<div id="completeOneField">';
	str+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	str+='<TR>';
	str+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	str+='<TD><H3>Complete Results In '+data.positionsYearOne[0].totalConstituencies+' Constituencies For '+electionType+' '+data.yearOne+' Elections</H3></TD>';
	str+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	str+='</TR>';
	str+='</TABLE>';	
	//str+='<legend><U></U></legend>';
	str+='<table width="100%" style="margin-bottom:10px;" cellpadding="0" cellspacing="0">';
	str+='<tr>';
    str+='<th align="center" class="head"><%=party%></th>';
	str+='<th align="center" class="head" style="color:DodgerBlue;"><%=pc%></th>';
	str+='<th align="center" class="head"><%=seatsWon%></th>';
	str+='<th align="center" class="head"><%=secondPos%></th>';
	str+='<th align="center" class="head"><%=thirdPos%></th>';
	str+='<th align="center" class="head"><%=votesPercentage%></th>';
    str+='<th align="center" class="head"><%=overAll%></th>';
	for(i in data.positionsYearOne){
	str+='<tr>';
	if(data.positionsYearOne[i].partyId == partyId){
    	str+='<td align="center" class="middle" style="color:red;">';
	if(data.positionsYearOne[i].partyName != 'IND' && data.positionsYearOne[i].partyId != null )
      str+='<a href="partyPageAction.action?partyId='+data.positionsYearOne[i].partyId+'" style="text-decoration:none;">'+data.positionsYearOne[i].partyName+'</a>';
	else
		str+='<a href="javascript:{}" style="text-decoration:none;">'+data.positionsYearOne[i].partyName+'</a>';
	str +='</td>';
  }
	else
	{
	  str+='<td align="center" class="middle" style="color:red;">';
	if(data.positionsYearOne[i].partyName != 'IND' && data.positionsYearOne[i].partyId != null )
    	str+='<a href="partyPageAction.action?partyId='+data.positionsYearOne[i].partyId+'" style="text-decoration:none;">'+data.positionsYearOne[i].partyName+'</a>';
	else
		str+='<a href="javascript:{}" style="text-decoration:none;">'+data.positionsYearOne[i].partyName+'';
	    str+='</a></td>';
	}
	str+='<td align="center" class="middle">'+data.positionsYearOne[i].totalConstiParticipated+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearOne[i].totalSeatsWon+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearOne[i].secondPosWon+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearOne[i].thirdPosWon+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearOne[i].votesPercentage+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearOne[i].completeVotesPercent+'</td>';
	str+='</tr align="center">';
	}
	str+='<tr>';
    str+='<td style="color:DodgerBlue;" colspan="7"><%=pc%> -- <%=participatedConsts%></td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='</td>';
	str+='<td valign="top">';
	str+='<div id="completeTwoField">';
	str+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	str+='<TR>';
	str+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	str+='<TD><H3>Complete Results In '+data.positionsYearTwo[0].totalConstituencies+' Constituencies For '+electionType+' '+data.yearTwo+' Elections</H3></TD>';
	str+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	str+='</TR>';
	str+='</TABLE>';
	str+='<table width="100%"  style="margin-bottom:10px;" cellpadding="0" cellspacing="0">';
	str+='<tr>';
    str+='<th align="center" class="head"><%=party%></th>';
	str+='<th align="center" class="head" style="color:DodgerBlue;"><%=pc%></th>';
	str+='<th align="center" class="head"><%=seatsWon%></th>';
	str+='<th align="center" class="head"><%=secondPos%></th>';
	str+='<th align="center" class="head"><%=thirdPos%></th>';
	str+='<th align="center" class="head"><%=votesPercentage%></th>';
    str+='<th align="center" class="head"><%=overAll%></th>';
	for(i in data.positionsYearTwo){
	str+='<tr>';
	if(data.positionsYearTwo[i].partyId == partyId){
     str+='<td align="center" style="color:red;" class="middle">';
	  if(data.positionsYearTwo[i].partyId != null && data.positionsYearTwo[i].partyName != 'IND')
		str+='<a href="partyPageAction.action?partyId='+data.positionsYearTwo[0].partyId+'" style="text-decoration:none">'+data.positionsYearTwo[i].partyName+'</a>';
	  else
		str+='<a href="javascript:{}" style="text-decoration:none;">'+data.positionsYearTwo[i].partyName+'</a>';
	 str+='</td>';
	}
	else
	 {
		str+='<td align="center" class="middle">';
	  if(data.positionsYearTwo[i].partyId != null && data.positionsYearTwo[i].partyName != 'IND')
		str+='<a href="partyPageAction.action?partyId='+data.positionsYearTwo[0].partyId+'" onclick="window.opener.location.href=this.href;window.blur();return false;" style="text-decoration:none;">'+data.positionsYearTwo[i].partyName+'</a>';
	  else
		str+='<a href="javascript:{}">'+data.positionsYearTwo[i].partyName+'</a>';
		str+='</td>';
	}
	str+='<td align="center" class="middle">'+data.positionsYearTwo[i].totalConstiParticipated+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearTwo[i].totalSeatsWon+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearTwo[i].secondPosWon+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearTwo[i].thirdPosWon+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearTwo[i].votesPercentage+'</td>';
	str+='<td align="center" class="middle">'+data.positionsYearTwo[i].completeVotesPercent+'</td>';
	str+='</tr align="center">';
	}
	str+='<tr>';
    str+='<td style="color:DodgerBlue;" colspan="7"><%=pc%> -- <%=participatedConsts%></td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='</td>';
	str+='</tr>';
	str+='</table>';
	
	str+='<fieldset id="electionProfileField">';
	str+='<legend><%=votesPcntInc%></legend>';	
	str+='<div id="votesPercentageIncDiv">';
	
	str+='</div>';	
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="electionResultsField">';
	str+='<legend><%=votesPcntDec%></legend>';
	str+='<div id="votesPercentageDecDiv">';	
	str+='</div>';	
	str+='</fieldset>';
	//--------

	str+='<table>'
	str+='<tr>';
	str+='<td style="vertical-align:top;">';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend><%=notConsidered%> - '+data.yearOne+' </legend>';
	str+='<div id="notConsYearOneDiv">';
	str+='</div>';
	str+='<div id="descDiv">';
	str+='<table>';
	str+='<tr>';
	str+='<th>DC:</th>Delimited Constituency<td></td>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>NC:</th><td>New Constituency</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>PNP:</th><td>Party Not Participated</td>';	
	str+='</tr>';	
	str+='</table>';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='<td style="vertical-align:top;">';
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> <%=notConsidered%> - '+data.yearTwo+' </legend>';
	str+='<div id="notConsYearTwoDiv">';	
	str+='</div>';
	str+='<div id="descDiv">';
	str+='<table>';
	str+='<tr>';
	str+='<th>DC:</th>Delimited Constituency<td></td>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>NC:</th><td>New Constituency</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>PNP:</th><td>Party Not Participated</td>';	
	str+='</tr>';	
	str+='</table>';	
	str+='</div>';
	str+='</fieldset>';
	str+='</td>';
	str+='</tr></table>';

	str+='</div>';
	elmt.innerHTML = str;
	buildDataTable("votesPercentageIncDiv",data.votesPercentGainedResults,data.yearOne,data.yearTwo);
    buildDataTable("votesPercentageDecDiv",data.votesPercentLostResults,data.yearOne,data.yearTwo);
	buildYearDataTable("notConsYearOneDiv",data.notConsideredYearOneResults);
	buildYearDataTable("notConsYearTwoDiv",data.notConsideredYearTwoResults);
}
function buildYearDataTable(divId,data)
{
	
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		if(oData != 'IND' ){
		 elLiner.innerHTML =
		  "<a href='partyPageAction.action?partyId="+partyId+"' style='text-decoration:none;'>"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}" style="text-decoration:none;">'+Party+'</a>';
	};
	
	var resultsDataSource = new YAHOO.util.DataSource(data);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constituencyName"
		}, {
			key : "candidateName"
		}, {
			key : "partyName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "votesPercent",parser:"number"
		} ,	{
			key : "rank",parser:"number"
		}, {
			key : "reason"
		}
		 ]
	};

	var resultsColumnDefs = [ 
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
		sortable : true,formatter:YAHOO.widget.DataTable.partyLink
	},
	{
		key : "votesEarned",
		label : "<%=votes%>",
		sortable : true
	},
	{
		key : "votesPercent",
		label : "<%=votesPercentage%>",
		sortable : true
	},
	{
		key : "rank",
		label : "<%=rank%>",
		sortable : true
	},
	{
		key : "reason",
		label : "Reason",
		sortable : true
	},
	]
	
	myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 
}

function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
{	
   //var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();
}

function buildDataTable(divId,arr,yearOne,yearTwo)
{	

	for(var i in arr)
	{
		arr[i].viewResultsOne = '<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\''+arr[i].constituencyId+'\',\''+arr[i].electionType+'\',\''+elecYear1+'\')">View</a>';
		arr[i].viewResultsTwo = '<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\''+arr[i].constituencyId+'\',\''+arr[i].electionType+'\',\''+elecYear2+'\')">View</a>';
	}
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("partyName");
		if(oData != 'IND'){
		 elLiner.innerHTML =
		 "<a href='partyPageAction.action?partyId="+partyId+"' style='text-decoration:none;'>"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}" style="text-decoration:none;">'+Party+'</a>';
	};

    var colorClass = '';
	if(divId == "votesPercentageIncDiv")
	colorClass = "greenColorClass";
    if(divId == "votesPercentageDecDiv")
	colorClass = "redColorClass";

	var resultsDataSource = new YAHOO.util.DataSource(arr);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constiName"
		}, {
			key : "candName"
		}, {
			key : "partyName"
		}, {
			key : "votesEarned",parser:"number"
		}, {
			key : "votesPercent",parser:"number"
		}, {
			key : "rank",parser:"number"
		},{
			key : "viewResultsOne"
		},

		{
			key : "votesPercentDiff",parser:"number"
		} ,	{
			key : "secndCandName"
		} , {
			key : "secndCandPartyName"
		}, {
			key : "secndCandRank" ,parser:"number"
		},
		{
			key : "votesEarnedBySecnd",parser:"number"
		} , {
			key : "secndVotesPercent",parser:"number"
		},{
			key : "viewResultsTwo"
		} ]
	};

	var resultsColumnDefs = [ 
	{
		key : "constiName",
		label : "<%=constituency%>",
		sortable : true
	}, 
	{
		label:"Year - "+yearOne,
		className:"yui-dt-sortable ",
		children:[ 
					{
						key : "candName",
						label : "<%=candidate%>",
						sortable : true
					},
					{
						key : "partyName",
						label : "<%=party%>",
						sortable : true,formatter:YAHOO.widget.DataTable.partyLink
					},
					{
						key : "rank",
						label : "<%=rank%>",
						sortable : true
					},
					{
						key : "votesEarned",
						label : "<%=votes%>",
						sortable : true
					},
					{
						key : "votesPercent",
						label : "%",
						sortable : true
					},
					{
						key : "viewResultsOne",
						label : "<%=results%>",
						sortable : true
					}
				]
	},
	{
		label:"Diff %",
		className:"yui-dt-sortable ",
		children:[ 					
					{
						key : "votesPercentDiff",
						label : "<%=diff%>",
						className:colorClass,
						sortable : true
					}
				]
	},
	{
		label:"Year - "+yearTwo,
		className:"yui-dt-sortable ",
		children:[ 
					
					{
						key : "secndCandName",
						label : "<%=candidate%>",
						sortable : true
					},
					{
						key : "secndCandPartyName",
						label : "<%=party%>",
						sortable : true
					},
					{
						key : "secndCandRank",
						label : "<%=rank%>",
						sortable : true
					},
					{
						key : "votesEarnedBySecnd",
						label : "<%=votes%>",
						sortable : true
					},
					{
						key : "secndVotesPercent",
						label : "%",
						sortable : true
					},
					{
						key : "viewResultsTwo",
						label : "<%=results%>",
						sortable : true
					}									
				 ]
	}
	];

    myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,{}); 
}

</script>
<body class="bodyClass">
<center>
<div id="header">
<TABLE border="0" cellpadding="0" cellspacing="0">
	<TR>
	<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>
	<TD><div id="title"></div></TD>
	<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>
	</TR>
	</TABLE>
</div>
<DIV id="electionPageAjaxImgDiv">
	<DIV> Loading .... Please Wait..</DIV>
	<IMG src="images/icons/barloader.gif"/>
</DIV>	
<div id="comparedResults" class="yui-skin-sam"></div>
</center>
<script type="text/javascript">
getComparedResults();
</script>
</body>
</html>