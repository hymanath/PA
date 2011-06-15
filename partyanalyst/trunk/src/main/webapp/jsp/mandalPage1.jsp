<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<!-- YUI Dependency files (Start) -->

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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/tabview/tabview-min.js"></script>


<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/tabview/assets/skins/sam/tabview.css">
<!-- YUI Dependency files (End) -->

	<!-- Sam Skin CSS for TabView -->

 

 
<!-- Source file for TabView -->

<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	

	
<style type="text/css">



#VillageTable th
{
	background-color:#afafaf;
}

#boothResultsDiv {
	text-align: left;
	margin-left: 50px;
	font-size: 12px;
	margin-right:10px;
	
}
#villageCensusDivHead,#mandalCensusDiv
{
	color:#0D3A5C;
}
#villageCensusDivBody
{
	border:2px solid #A5CCFF;
}
.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background-image:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	color:#3F546F;
	text-decoration:none;
}

.searchresultsTable td {
	background-color:#F8FBFF;
}
.searchresultsTable th {
	background-color:#C4DEFF;
}
.yui-skin-sam .yui-dt-liner {
	padding:4px 8px;
}
.ConstituencyElectionsTable th
{
	text-align:left;
	background-color:#C4DEFF;
}
.ConstituencyElectionsTable td
{
	text-align:right;
}
fieldset
{
	background-color:#F3F6F7;
	border:4px solid #CFD6DF;
	margin-bottom:10px;
	margin-top:20px;
}

legend
{
background-color:#567AAF;
color:#FFFFFF;
font-size:12px;
padding:5px;
}

.censusInfoTable th
{
	background-color:#567AAF;
	border:1px solid white;
	color:#FFFFFF;
	padding:8px;
}

.censusInfoTable td
{
	background-color:#FFFFFF;
	border:1px solid #D2D9DF;
	padding:8px;
}
.dataTableSize table{
width:100%;
}
.selectWidth {
padding:2px;
width:120px;
}
.yui-skin-sam .yui-panel .bd
{
background-color:#FFFFFF;
}
#revenueVillageDiv table{
width:100%;
}
#boothInfoTable
{
	border: 2px solid #EFEFEF;
}
#boothInfoTable th
{
	background-color:#567AAF;
	color:#FFFFFF;
	padding:5px;
	width:20%;
}

#boothInfoTable td
{
	background-color:#F2F6F9;
	font-weight:bold;
	padding:5px;
}

#boothInfoDiv_main
{
	margin-left:10px;
}

#boothInfoDiv_head
{	
	color:#747E84;
	font-weight:bold;
	padding:5px;
	text-decoration:underline;
}
.boothDatatable table
{
	width:100%;
}
.commonVotersTableClass th
{
	padding:5px;
	background-color:#728194;
	color:#FFFFFF;
}
.commonVotersTableClass td
{
	padding:5px;
	background-color:#FFFFFF;
	color:#728194;

}
.commonVotersHeadDiv
{
	color:#323E4E;
	font-weight:bold;
	margin-bottom:5px;
	padding:5px;
	text-decoration:underline;
}
.infoDivMainClass
{
	color:#3B4B58;
	margin-top:10px;
	
}
.partyInfoClass
{
	/*font-weight:bold;*/	
	background-color:#F3F6F7;
	margin-top:10px;
	
	width:expression(document.body.clientWidth > 700? "700px": "auto" );
}
.infoDivHeadClass
{
	font-weight:bold;
	text-decoration:underline;
}
.partyInfoHead
{
	width:800px;
}
.partyInfoBody
{	
	margin-left:30px;
}


.partyInfoHead table th
{
	color:#94A3AC;
	padding-right:5px;
	
	text-align:left;
}

.partyInfoHead table td
{
	color:#275D81;R
	padding-right:5px;
	
	text-align:left;
}

.partyInfoBody table
{
	width:100%;
}

#boothResultsDiv .yui-skin-sam .yui-navset .yui-content
{
	background:none;
}

.reportAnchors
{
	color:#3B4B58;
	font-weight:bold;
}

#townshipPartyResultsPanel
{
	border:2px solid #3B4B58;
}

#townshipPartyResultsPanel .hd
{
	background-image:url(images/icons/contHeading.png);
	background-repeat:repeat;
}
#townshipPartyResultsPanel .bd table
{
	width:100%;
}

.graphLabelTd
{
	font-weight:bold;
	vertical-align:top;
	
}

.yui-skin-sam .yui-dt table {
	border:1px solid #7F7F7F;
	border-collapse:separate;
	border-spacing:0;
	font-family:arial;
	font-size:inherit;
	margin:0;
	padding:0;
	width:90%;
}
.anchorStyle{
   color:#3B4B58;
   font-weight:bold;
   text-decoration:none;
}
#mandalDivHeadstyle {
    color: #669900;
    font-size: 15px;
    font-weight: bold;
}
</style>



<script type="text/javascript">
var partyDetailsTable = '';
var candidateElectionResultPanel;
var allACPCElecInfo = new Array();
var allZPTCMPTCElecInfo = new Array();
	<c:forEach var="zptcMptcElection" items="${mptcZptcElectionResultsVO}" >
		var zptcMptcElec = {
				year:'${zptcMptcElection.electionYear}',
				type:'${zptcMptcElection.electionType}',
				parties:[]		
		};	
		<c:forEach var="party" items="${zptcMptcElection.partyResultsVO}">
			var party = {
					partyName:'${party.partyName}',
					participated:'${party.seatsParticipated}',
					seatsWon:'${party.totalSeatsWon}',
					votesEarned:'${party.votesEarned}',
					polledVotes:'${party.totalPolledVotes}',
					percentage:'${party.percentage}',
					constituencies:[]
			};
			<c:forEach var="constituency" items="${party.constituencyWisePatiesInfoVOs}">
				var constituency = {
						name:'${constituency.constituencyName}',
						candidate:'${constituency.candidateName}',
						rank:'${constituency.rank}',
						votesEarned:'${constituency.votesEarned}',
						percentage:'${constituency.percentage}',
						results:'<a href="javascript:{}" class="anchorStyle" title="Click to view more results"  onclick=getMoreResults('+zptcMptcElec.year+',\''+zptcMptcElec.type+'\','+${constituency.constituencyId}+')> More Results </a>'
				};
				party.constituencies.push(constituency); 
			</c:forEach>	
			zptcMptcElec.parties.push(party);	
		</c:forEach>
		allZPTCMPTCElecInfo.push(zptcMptcElec);
	</c:forEach>

<c:forEach var="electionResult" items="${electionWiseMandalPartyResultListVO.electionWiseMandalPartyResultVOList}" >
		var electionInfo = {
				year:'${electionResult.electionYear}',
				electionType:'${electionResult.electionType}',
				constituencyInfo:[]
		};
		<c:forEach var="constituencyInfo" items="${electionResult.constituencyWiseDataForMandalVOs}">
			var eachConstiInfo = {
					constituencyId:'${constituencyInfo.constituencyId}',
					constituencyName:'${constituencyInfo.constituencyName}',
					maleVoters:'${constituencyInfo.commonMaleVotersInMandalAndConstituency}',
					femaleVoters:'${constituencyInfo.commonFemaleVotersInMandalAndConstituency}',
					maleRfemaleVoters:'${constituencyInfo.commonMaleOrFemaleVoters}',
					totalVoters:'${constituencyInfo.commonTotalVotersInMandalAndConstituency}',
					polledVotes:'${constituencyInfo.totalPolledVotes}',
					malePolledVotes:'${constituencyInfo.malePolledVotes}',
					femalePolledVotes:'${constituencyInfo.femalePolledVotes}',
					maleOrFemalePolledVotes:'${constituencyInfo.maleOrFemaleValidVotes}',
					paritesinfo:[]
			}
			<c:forEach var="partyInfo" items="${constituencyInfo.partyVotes}">
				var singleParty = {
						partyId:'${partyInfo.partyID}',
						partyName:'${partyInfo.partyName}',
						candidateId:'${partyInfo.candidateID}',
						candidateName:'${partyInfo.candidateNameWithStatus}',
						rank:'${partyInfo.rank}',
						mandalVotes:'${partyInfo.totalVotesEarned}',
						mandalpercentage:'${partyInfo.totalVotesEarnedPercentage}',
						maleVotes:'${partyInfo.maleBoothResults}',
						malepercentage:'${partyInfo.maleBoothResultsPercentage}',
						femaleVotes:'${partyInfo.femaleBoothResults}',
						femalepercentage:'${partyInfo.femaleBoothResultsPercentage}',
						bothVotes:'${partyInfo.fmBoothResults}',
						bothpercentage:'${partyInfo.fmBoothResultsPercentage}'
				};
				eachConstiInfo.paritesinfo.push(singleParty);
			</c:forEach>
			electionInfo.constituencyInfo.push(eachConstiInfo);
		</c:forEach>
		allACPCElecInfo.push(electionInfo);
</c:forEach>

function getMoreResults(elecYear,elecType,constiId)
{
   var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();

}
	function buildCensusDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("villageCensusTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "townshipName"
		}, {
			key : "totalPersons",parser:"number"
		}, {
			key : "male",parser:"number"
		}, {
			key : "female",parser:"number"
		}, {
			key : "totalSCPersons",parser:"number"
		}, {
			key : "totalSTPersons",parser:"number"
		}, {
			key : "totalLiteratePersons",parser:"number"
		}, {
			key : "totalIlliteratePersons",parser:"number"
		} , {
			key : "totalWorkingPersons",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "townshipName",		
		label : "Village Name",
		sortable : true
	}, {
		key : "totalPersons",
		parser:"number",
		label : "Total Population",
		sortable : true
	}, {
		key : "male",
		parser:"number",
		label : "Male",
		sortable : true
	}, {
		key : "female",
		parser:"number",
		label : "Female",
		sortable : true
	}, {
		key : "totalSCPersons",
		parser:"number",
		label : "SCs",
		sortable : true
	}, {
		key : "totalSTPersons",
		parser:"number",
		label : "STs",
		sortable : true
	}, {
		key : "totalLiteratePersons",
		parser:"number",
		label : "Literates",
		sortable : true
	}, {
		key : "totalIlliteratePersons",
		parser:"number",
		label : "Illiterates",
		sortable : true
	}, {
		key : "totalWorkingPersons",
		parser:"number",
		label : "Working People",
		sortable : true
	} ];

	var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
		};
	
	var myDataTable = new YAHOO.widget.DataTable("dTTableDiv2",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildRevenueVillagesInfoTab(){
		var revenueInfo = '';
		revenueInfo += '<br>'
		revenueInfo += '<div id="div3_revenue">';
		revenueInfo += '<div id="revenueVillageTable">';
		revenueInfo += '</div>';
		revenueInfo += '<div id="revenueVillagesMainDiv">';
		revenueInfo += '<table>';
		revenueInfo += '<tr><td>Election Type:</td>';
		revenueInfo += '<td><select id="electionTypeSelect" onchange = "getElectionYears(this.options[this.selectedIndex].value)" class = "selectWidth">';
		revenueInfo += '<option value="0">Select </option>';
		revenueInfo += '<option value="1">Parliament</option>';
		revenueInfo += '<option value="2">Assembly</option>';
		revenueInfo += '</select></td>';
		revenueInfo += '<td><div id="electionIdSelectDivLabel"></div></td>';
		revenueInfo += '<td><div id="electionIdSelectDivData"></div></td>';
        revenueInfo += '<td><div id="AjaxImgDiv" align="center" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
        revenueInfo += '<td width="20px" height="10px"><div id="revenueVillagesResultsLinkDiv"></div></td>';
        revenueInfo += '</tr>';
		revenueInfo += '</table>';
		revenueInfo += '<br>';
		revenueInfo += '<div id="revenueVillagesInfo"></div>';
		revenueInfo += '</div>';
		revenueInfo += '</div>';
		
		return revenueInfo;
	}

	function getElectionYears(id){
		var jsObj=
			{
					electionTypeId:id,
					task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}

	var electionId;
	var mandalId;
	function getRevenueVillagesInfo(id){
         var imgElmt = document.getElementById('AjaxImgDiv');
		 if(imgElmt.style.display == "none")
		{
           imgElmt.style.display = "block";
		}

		electionId = id;
		mandalId = ${mandalId};
		var jsObj=
			{
					electionId:id,
					mandalId:${mandalId},
					task:"getRevenueVillagesInfo"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getRevenueVillagesInfoAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}

	function getTownshipElectionsInfo(name,townshipId, electionId){
		var jsObj=
		{
				villageName:name,
				townshipId:townshipId,
				electionId:electionId,
				task:"getRevenueVillagesElectionInfo"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getRevenueVillagesElectionsAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}

	function getConsolidatedRevenueVillagesElecInfo(){
		var jsObj=
		{
				mandalId:${mandalId},
				electionId:electionId,
				task:"getAllRevenueVillagesElectionResults"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllRevenueVillagesElectionAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	}

	var townshipsResults;
	function callAjax(rparam, jsObj, url){
		var resultVO;			
		var callback = {			
	               success : function( o ) {
						try {								
								resultVO = YAHOO.lang.JSON.parse(o.responseText);										
								
								if(jsObj.task == "getElectionYears")
								{								
									showElectionYearTextBox(resultVO);				
								}
								else if(jsObj.task == "getRevenueVillagesInfo")
								{								
									showRevenueVillagesInfo(resultVO);				
								}			
								else if(jsObj.task == "getRevenueVillagesElectionInfo")
								{								
									showRevenueVillageElectionInfo(resultVO,jsObj);			
								}
								else if(jsObj.task == "getAllRevenueVillagesElectionResults")
								{								
									townshipsResults = resultVO;
									showElectionResultsInAllRevenueVillages();			
								}					
						}catch (e)  {   
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

	function showElectionYearTextBox(resultVO){
		
		var electionYearSelect = '';
		var elmtLabel = document.getElementById('electionIdSelectDivLabel');
		var elmtData = document.getElementById('electionIdSelectDivData');
		
		electionYearSelect += '<select id="electionYearSelect" class = "selectWidth" onchange = "getRevenueVillagesInfo(this.options[this.selectedIndex].value)">';
		for(var i in resultVO)
		{			
			electionYearSelect += '<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
	
		electionYearSelect += '</select>';

		if(elmtLabel)
			elmtLabel.innerHTML='Election Year:';
		if(elmtData)
			elmtData.innerHTML=electionYearSelect;
	}

	function showRevenueVillageElectionInfo(resultVO,jsObj){

		var typeSelectElmt = document.getElementById("electionTypeSelect");
		var yearSelectElmt = document.getElementById("electionYearSelect");		

		var typeVal = ""+typeSelectElmt.options[typeSelectElmt.selectedIndex].text;
		var yearVal = ""+yearSelectElmt.options[yearSelectElmt.selectedIndex].text;

		
		 var rvEleStr = '';
		 rvEleStr += '<div class="commonVotersHeadDiv"> Voting Trendz Of Different Parties In '+jsObj.villageName+' Revenue Village </div>';
		 rvEleStr += '<table class="censusInfoTable" style="border:1px solid #ADADAD;">';
		 rvEleStr += '<tr>';
		 rvEleStr += '<th style="background-color:#8D7463">Party</th>';
		 for(var i in resultVO){
		 rvEleStr += '<td>'+resultVO[i].partyName+'</td>';
		 }
		 rvEleStr += '</tr><tr>';
		 rvEleStr += '<th style="background-color:#8D7463">Votes Earned</th>';
		 for(var i in resultVO){
		 rvEleStr += '<td>'+resultVO[i].votesEarned+'</td>';
		 }
		 rvEleStr += '</tr>';
		 rvEleStr += '</table>';

		 myPanel = new YAHOO.widget.Panel("townshipPartyResultsPanel", {
		 width: "550px",
		 x:210,
		 y:760,
		 constraintoviewport: true,
		 underlay: "none",
		 close: true,
		 visible: true,
		 draggable: false
		 });
		 
		 myPanel.setHeader(" Revenue Village : "+jsObj.villageName);
		 myPanel.setBody(rvEleStr);
		 myPanel.render(); 
	}
	
	function openwin(){
		
		var typeVal = ""+typeSelectElmt.options[typeSelectElmt.selectedIndex].text;
		var yearVal = ""+yearSelectElmt.options[yearSelectElmt.selectedIndex].text;
		
		var brow1 = window.open("<s:url action="townshipElectionResultsAction"/>?mandalId="+mandalId+"&electionId="+electionId+"&mandalName=${mandalInfoVO.mandalName}&electionType="+typeVal+"&electionYear="+yearVal,"brow1","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		brow1.focus();
	}
	
	var typeSelectElmt;
	var yearSelectElmt;
	function showRevenueVillagesInfo(resultVO){
		
		typeSelectElmt = document.getElementById("electionTypeSelect");
		yearSelectElmt = document.getElementById("electionYearSelect");		

		var typeVal = ""+typeSelectElmt.options[typeSelectElmt.selectedIndex].text;
		var yearVal = ""+yearSelectElmt.options[yearSelectElmt.selectedIndex].text;
		
		var rvStrDiv = document.getElementById('revenueVillagesInfo');

		var revenueResult = document.getElementById('revenueVillagesResultsLinkDiv');
		revenueResultStr = '<a href="#" onclick="openwin()" ><img style="border:medium none;"src="images/icons/mandalPage/clickHere.png" width="420px" height="30px"/></a>';
		revenueResult.innerHTML = revenueResultStr;
		
		var rvStr = '';		
		rvStr += '<a name="votersDiv"></a>';
		rvStr += '<div id="revenueVillageDiv_head" class="commonVotersHeadDiv">';
		rvStr += 'Voting Trendz In Revenue Villages for ${mandalInfoVO.mandalName} in  '+yearVal+' '+typeVal+' Election ';
		rvStr += '</div>';
		

		for(var k in resultVO.partiesResultsInVillages){
			rvStr += '<div style="margin-top:10px;margin-bottom:10px;"><b>'+resultVO.partiesResultsInVillages[k].constituencyName+' '+typeVal+' In '+yearVal+'</b></div>';
			rvStr += '<div id="revenueVillageDiv_'+k+'">';	
			rvStr += '<table id="revillageInfoTable_'+k+'" >';
			for(var i in resultVO.partiesResultsInVillages[k].revenueVillagesInfo)
			{			
				rvStr += '<tr>';
				rvStr += '<td><a href="townshipPageAction.action?TOWNSHIP_ID='+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationId+'&TOWNSHIP_NAME='+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationName+'" >'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationName+'</a></td>';
				rvStr += '<td>'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].population+'</td>';
				rvStr += '<td>'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].votesPolled+'</td>';
				rvStr += '<td>';
				for(var j in resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].booths)
				{
					if(j%3 == 0 && j!=0)
						rvStr += '<br>';
					rvStr += '<a href="javascript:{}" onclick="getBoothPageInfo('+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].booths[j].id+')">'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].booths[j].name+',';
				}
				rvStr += '</td>';
				rvStr += '<td>';
				for(var j in resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].hamletsOfTownship)
				{
					rvStr += resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].hamletsOfTownship[j].name+'<br>';
				}
				rvStr += '</td>';
				rvStr += '<td>';
				//rvStr += '<a href = "javascript:{}" class="reportAnchors">Census Info</a><br>';
				rvStr += '<a href = "#votersDiv" class="reportAnchors" onclick = "getTownshipElectionsInfo(\''+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationName+'\','+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationId+','+electionId+')"> View Voting Trendz </a><br>';
				//rvStr += '<a href = "javascript:{}" class="reportAnchors">Cast Details</a><br>';
				rvStr += '</td>';
				rvStr += '</tr>';
			}
			rvStr += '</table>';
			rvStr += '</div>';
		}
		

		if(rvStrDiv)
			rvStrDiv.innerHTML = rvStr;
		for(var k in resultVO.partiesResultsInVillages){
			var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get("revillageInfoTable_"+k)); 
			 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
			  myDataSource.responseSchema = { 
			            fields: [
									{
										key : "townshipName"
									},{
										key : "totalVoters",parser:"number"
									},{
										key : "votesPolled",parser:"number"
									},{
										key : "booths"
									},{
										key : "hamlets"
									},{
										key : "links"
									}
								]    
			        }; 
			
			 var myColumnDefs = [ 
			            {key:"townshipName",label:'Revenue Village', sortable:true, resizeable:true}, 
			            {key:"totalVoters", label:'Total Voters', sortable:true, resizeable:true}, 
			            {key:"votesPolled", label:'Votes Polled', sortable:true, resizeable:true},
			            {key:"booths",label:'Total Booths', resizeable:true}, 
			            {key:"hamlets",label:'Total Hamlets', resizeable:true},
			            {key:"links",label:'Links', resizeable:true}
			        ]; 
			 
			var myDataTable = new YAHOO.widget.DataTable("revenueVillageDiv_"+k,myColumnDefs, myDataSource);
		}
		
          var imgElmt = document.getElementById('AjaxImgDiv');
		 if(imgElmt.style.display == "block")
		{
           imgElmt.style.display = "none";
		}

	}
	
	function showElectionResultsInAllRevenueVillages(){
		var typeSelectElmt = document.getElementById("radioRevenueVillagesResultsDiv");
		rvStr = '';
		rvStr +='<input type="radio" name="display" value="VotesEarned" onclick="displayByVotesPercentage(this.value)"/> Votes Earned'; 
		rvStr +='<input type="radio" name="display" value="VotesPercentages" onclick="displayByVotesPercentage(this.value)"/> Votes Percentages';
		rvStr +='<div id="completeRVResultsDiv" />';
		typeSelectElmt.innerHTML = rvStr;
	}
	
	function buildTabNavigator(){
		var myTabs = new YAHOO.widget.TabView();
		var mandalElections = '';
		mandalElections+='<div id="div1" >';
		mandalElections+='<div id="allzptcmptcElectionsInfoMainDiv"></div>';
		mandalElections+='<div id="allACPCelectionsInfoMainDiv"></div>';
		mandalElections+='</div>';
		
		var cencusInfo = '';
		cencusInfo+='<div id="div2" >';
		cencusInfo+='<div id="dTTableDiv2"></div>';
		cencusInfo+='</div>';

		
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'All Election In Mandal',
		    active:true,
		    content: mandalElections
		}));
			
		<c:if test = "${villageDetailsVO.showRevenueVillageInfo}">
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Census Info in Revenue Villages',
		    content: cencusInfo
		}));
		</c:if>

		<c:if test = "${villageDetailsVO.showRevenueVillageInfo}">
			myTabs.addTab( new YAHOO.widget.Tab({
			    label: 'Revenue Villages Wise Election Info',
			    content: buildRevenueVillagesInfoTab()
			    
			}));
		</c:if>

		myTabs.appendTo('mandalPageTab');
				
	}

	function showElectionResultsInPopup()
	{
		<c:if test = "${villageDetailsVO.showRevenueVillageInfo}">
		var elmt = document.getElementById("allACPCelectionsInfoMainDiv");
		
		for(var i in allACPCElecInfo)
		{			
			var divChild = document.createElement("div");
			var electionInfo = '';
			for(var j in allACPCElecInfo[i].constituencyInfo){
				electionInfo += '<fieldset>';
				electionInfo += '<legend>'+allACPCElecInfo[i].constituencyInfo[j].constituencyName+' '+allACPCElecInfo[i].electionType+' '+allACPCElecInfo[i].year+'</legend>';
				electionInfo += '<div id = "data_div_'+i+'_'+j+'" class="">';
				electionInfo += '<div id = "data_head_div_'+i+'_'+j+'" class="commonVotersHeadDiv">Voters Info In ${mandalInfoVO.mandalName} For '+allACPCElecInfo[i].constituencyInfo[j].constituencyName+' '+allACPCElecInfo[i].electionType+'</div>';
				electionInfo += '<div id = "data_body_div_'+i+'_'+j+'" class="commonVotersBodyDiv">';
				electionInfo += '<table class="commonVotersTableClass"><tr>';				

				electionInfo += '<th> Total Voters</th>';	
				if(allACPCElecInfo[i].constituencyInfo[j].totalVoters || allACPCElecInfo[i].constituencyInfo[j].totalVoters == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].totalVoters+'</td>';
				else
					electionInfo += '<td> - </td>';

				electionInfo += '<th> Male Voters</th>';				
				if(allACPCElecInfo[i].constituencyInfo[j].maleVoters || allACPCElecInfo[i].constituencyInfo[j].maleVoters == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].maleVoters+'</td>';
				else
					electionInfo += '<td> - </td>';

				electionInfo += '<th> Female Voters</th>';
				if(allACPCElecInfo[i].constituencyInfo[j].femaleVoters || allACPCElecInfo[i].constituencyInfo[j].femaleVoters == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].femaleVoters+'</td>';
				else
					electionInfo += '<td> - </td>';

				electionInfo += '<th> Male / Female Voters</th>';
				if(allACPCElecInfo[i].constituencyInfo[j].maleRfemaleVoters || allACPCElecInfo[i].constituencyInfo[j].maleRfemaleVoters == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].maleRfemaleVoters+'</td>';
				else
					electionInfo += '<td> - </td>';

				electionInfo += '</tr><tr>';
				electionInfo += '<th> Polled Votes</th>';	
				if(allACPCElecInfo[i].constituencyInfo[j].polledVotes || allACPCElecInfo[i].constituencyInfo[j].polledVotes == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].polledVotes+'</td>';
				else
					electionInfo += '<td> - </td>';
				electionInfo += '<th> Male Polled Votes</th>';	
				if(allACPCElecInfo[i].constituencyInfo[j].malePolledVotes || allACPCElecInfo[i].constituencyInfo[j].malePolledVotes == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].malePolledVotes+'</td>';
				else
					electionInfo += '<td> - </td>';

				electionInfo += '<th> Female Polled Votes</th>';	
				if(allACPCElecInfo[i].constituencyInfo[j].femalePolledVotes || allACPCElecInfo[i].constituencyInfo[j].femalePolledVotes == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].femalePolledVotes+'</td>';
				else
					electionInfo += '<td> - </td>';

				electionInfo += '<th> Male / Female Polled Votes</th>';	
				if(allACPCElecInfo[i].constituencyInfo[j].maleOrFemalePolledVotes || allACPCElecInfo[i].constituencyInfo[j].maleOrFemalePolledVotes == 0)				
					electionInfo += '<td>'+allACPCElecInfo[i].constituencyInfo[j].maleOrFemalePolledVotes+'</td>';
				else
					electionInfo += '<td> - </td>';		
				electionInfo += '</tr></table>';
				electionInfo += '</div>';
				electionInfo += '<br>';
				electionInfo += '<div id = "div_'+i+'_'+j+'" class="dataTableSize">';
				electionInfo += '</div>';
				electionInfo += '</fieldset>';				
			}		
			divChild.innerHTML = electionInfo;
				
			if(elmt)
				elmt.appendChild(divChild);			
		}		

		for(var i in allACPCElecInfo){
			for(var j in allACPCElecInfo[i].constituencyInfo){
				var myDataSource = new YAHOO.util.DataSource(allACPCElecInfo[i].constituencyInfo[j].paritesinfo); 
				 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
				 myDataSource.responseSchema = { 
				            fields: [
										{
											key : "partyName"
										},{
											key : "candidateName"
										},{
											key : "rank",parser:"number"
										},{
											key : "mandalVotes",parser:"number"
										},{
											key : "mandalpercentage",parser:"float"
										},{
											key : "maleVotes",parser:"number"
										},{
											key : "malepercentage",parser:"float"
										},{
											key : "femaleVotes",parser:"number"
										},{
											key : "femalepercentage",parser:"float"
										},{
											key : "bothVotes",parser:"number"
										},{
											key : "bothpercentage",parser:"float"
										}
									]    
				        }; 
				
				 var myColumnDefs = [ 
				            {key:"partyName",label:'Party', sortable:true, resizeable:true}, 
				            {key:"candidateName", label:'Candidate Name', sortable:true, resizeable:true}, 
				            {key:"rank", label:'Rank',sortable:true, resizeable:true},
				            {key:"mandalVotes", label:'Total Votes Earned',sortable:true, resizeable:true}, 
				            {key:"mandalpercentage",label:'%', sortable:true, resizeable:true}, 
				            {key:"maleVotes",label:'Male Votes', sortable:true, resizeable:true}, 
				            {key:"malepercentage",label:'%', sortable:true, resizeable:true}, 
				            {key:"femaleVotes",label:'Female Votes', sortable:true, resizeable:true},
				            {key:"femalepercentage",label:'%', sortable:true, resizeable:true}, 
				            {key:"bothVotes",label:'M/F Votes', sortable:true, resizeable:true},
				            {key:"bothpercentage",label:'%', sortable:true, resizeable:true} 
				        ]; 
				 
				var myDataTable = new YAHOO.widget.DataTable("div_"+i+"_"+j,myColumnDefs, myDataSource);
			}
		}
	</c:if>
		
	}

	function getGraph(value)
	{	
		var str = '';
		var estr = '';
		var mandal = ${mandalId};
		var elmtData = document.getElementById("graphDataDiv");
			
		if(value=="0")
		{
			elmt.innerHTML='';
			elmtData.innerHTML='';
			return;
		}	
		
		<c:forEach var="party" items="${allElectionResults}">
			if('${party.partyId}' == value){
				estr+='<table id="boothInfoTable">';	
				estr+='<tr>';					
				estr+='<th>Election Type</th>';
				estr+='<th>Election Year</th>';
				estr+='<th>Votes %</th>';
				estr+='</tr>';
				<c:forEach var="result" items="${party.electionWiseResults}">
					estr+='<tr>';					
					estr+='<td>${result.electionType}</td>';
					estr+='<td>${result.electionYear}</td>';
					estr+='<td>${result.percentage}</td>';
					estr+='</tr>';
				</c:forEach>
				estr+='</table>';
			}			
		</c:forEach>
		
		if(elmtData)
			elmtData.innerHTML = estr;

		str+=''; 
		if(elmt)
			elmt.innerHTML=str;
		
	}
	
	function showMPTCZPTCResults(){

		var allZMElmt = document.getElementById("allzptcmptcElectionsInfoMainDiv");
		var str = '';
		str+='<div>';
		str+='<table>';
		str+='<tr>';
		str+='<td style="vertical-align:top"><div id="mandalGraphDiv"></div></td>';		
		str+='</tr>';
		str+='<tr>';
		str+='<td style="vertical-align:top"><div id="graphDataDiv" align="center"><img src="charts/allPartiesMandalWisePerformanceInAllElections_${mandalId}.png"/></div></td>';
		str+='</tr>';
		str+='</table>';		
		str+='</div>';
		for(var i in allZPTCMPTCElecInfo){
			str += '<div id="infoDivMain_'+i+'" class="infoDivMainClass">';			
			str += '<div id="infoDivMain_'+i+'_head" class="infoDivHeadClass">'+allZPTCMPTCElecInfo[i].type+' '+allZPTCMPTCElecInfo[i].year+'</div>';			
			for(var j in allZPTCMPTCElecInfo[i].parties){
				str += '<div id="partyInfo_main" class="partyInfoClass">';    
				str += '<div id="'+allZPTCMPTCElecInfo[i].type+'_'+allZPTCMPTCElecInfo[i].year+'_'+allZPTCMPTCElecInfo[i].parties[j].partyName+'_head" class="partyInfoHead">';
				str += '<table width="100%">';
				str += '<td>';
				str += '<a id="'+allZPTCMPTCElecInfo[i].type+'_'+allZPTCMPTCElecInfo[i].year+'_'+allZPTCMPTCElecInfo[i].parties[j].partyName+'_anc" href="javascript:{showPartyDetailsInfo(\''+allZPTCMPTCElecInfo[i].type+'_'+allZPTCMPTCElecInfo[i].year+'_'+allZPTCMPTCElecInfo[i].parties[j].partyName+'_anc\')}">';
				str+='		<img id="'+allZPTCMPTCElecInfo[i].type+'_'+allZPTCMPTCElecInfo[i].year+'_'+allZPTCMPTCElecInfo[i].parties[j].partyName+'_img" border="none" src="images/icons/plusNew.png" />';
				str += '</a>';
				str +='</td>';
				str += '<th width="50px">Party:</th><td width="50px">'+allZPTCMPTCElecInfo[i].parties[j].partyName+'</td>';
				str += '<th width="100px">Participated:</th><td width="50px">'+allZPTCMPTCElecInfo[i].parties[j].participated+'</td>';
				str += '<th width="100px">Seats Won:</th><td width="50px">'+allZPTCMPTCElecInfo[i].parties[j].seatsWon+'</td>';
				str += '<th width="100px">Votes Earned:</th><td width="50px">'+allZPTCMPTCElecInfo[i].parties[j].votesEarned+'</td>';
				str += '<th width="100px">Votes Polled:</th><td width="50px">'+allZPTCMPTCElecInfo[i].parties[j].polledVotes+'</td>';
				str += '<th width="100px">Percentage:</th><td width="50px">'+allZPTCMPTCElecInfo[i].parties[j].percentage+'</td>';
				str += '</table>';
				str += '</div>';
				str += '<div id="'+allZPTCMPTCElecInfo[i].type+'_'+allZPTCMPTCElecInfo[i].year+'_'+allZPTCMPTCElecInfo[i].parties[j].partyName+'_body" class="partyInfoBody" style="display:none;">';				
				str += '<table id="'+allZPTCMPTCElecInfo[i].type+'_'+allZPTCMPTCElecInfo[i].year+'_'+allZPTCMPTCElecInfo[i].parties[j].partyName+'_table">';
				for(var k in allZPTCMPTCElecInfo[i].parties[j].constituencies){					
					str += '<tr>';				
					str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].constituencies[k].name+'</td>';
					str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].constituencies[k].candidate+'</td>';
					str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].constituencies[k].rank+'</td>';
					str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].constituencies[k].votesEarned+'</td>';
					str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].constituencies[k].percentage+'</td>';
					str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].constituencies[k].results+'</td>';
					str += '</div>';
					str += '</tr>';
				}
				str += '</table>';
				str += '</div></div>';
			}
			str += '</div>';
		}

		if(allZMElmt)
			allZMElmt.innerHTML = str;

		for(var a in allZPTCMPTCElecInfo)
		{
			for(var b in allZPTCMPTCElecInfo[a].parties)
			{
				var id = allZPTCMPTCElecInfo[a].type+'_'+allZPTCMPTCElecInfo[a].year+'_'+allZPTCMPTCElecInfo[a].parties[b].partyName+'_table';
				var divId = allZPTCMPTCElecInfo[a].type+'_'+allZPTCMPTCElecInfo[a].year+'_'+allZPTCMPTCElecInfo[a].parties[b].partyName+'_body';
			
				var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
						.get(id)); 
				 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
				  myDataSource.responseSchema = { 
				            fields: [
										{
											key : "name"
										},{
											key : "candidate"
										},{
											key : "rank",parser:"number"
										},{
											key : "votesEarned",parser:"number"
										},{
											key : "percentage",parser:"float"
										},{
											key : "results"
										}
									]    
				        }; 
				
				 var myColumnDefs = [ 
								{key:"name",label:'Constituency', sortable:true, resizeable:true}, 
								{key:"candidate", label:'Candidate Name', sortable:true, resizeable:true}, 
								{key:"rank", label:'Rank',sortable:true, resizeable:true}, 
								{key:"votesEarned",label:'Votes Earned', sortable:true, resizeable:true}, 
								{key:"percentage",label:'%', sortable:true, resizeable:true},
					            {key:"results",label:'Results'}
				        ]; 
				 
				partyDetailsTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource);
			}
			
		}
		
	}

	function showPartyDetailsInfo(id)
	{		
		var bodyDivId = id.substring(0,id.lastIndexOf('_'))+'_body';
		var tableDivId = id.substring(0,id.lastIndexOf('_'))+'_table';
		var imgElmt = document.getElementById(id.substring(0,id.lastIndexOf('_'))+'_img');
		var divElmt = document.getElementById(bodyDivId);
		
		if(!divElmt)
			return;
	
		if(divElmt.style.display == "none")
		{
			divElmt.style.display = "block";
			imgElmt.src = "images/icons/minusNew.png";
		}
		else if(divElmt.style.display == "block")
		{
			divElmt.style.display = "none";
			imgElmt.src = "images/icons/plusNew.png";
		}
		
	}
	function getBoothPageInfo(id){
		var urlStr = "<%=request.getContextPath()%>/boothResultsForAllElectionsPopupAction.action?boothId="+id;
		var browser1 = window.open(urlStr,"boothResultsForAllElections","scrollbars=yes,height=600,width=900,left=200,top=200");
		browser1.focus();	
	}	
	
	</script>
</head>
<body> 

<table width="100%" border="0">
<tr><td><%@ include file="navigator.jsp" %></td></tr>
<tr><td align="center" id="mandalDivHeadstyle"><br>
<u><c:out value="${mandalInfoVO.mandalName}"/> Tehsil / Mandal Details</u>
</td></tr></table>
<div id="boothResultsDiv">

	<div id="mandalCensusDiv">
		<div id="mandalCensusDivHead"><h4><u>Mandal Details..</u></h4></div>
						
		<div id="mandalCensusDivBody" align="center" class="yui-skin-sam">
		<table class="censusInfoTable" style="border:1px solid #ADADAD;">		
				<tr>
					<th></th>
					<th>Population</th>
					<th>SC Population</th>
					<th>ST Population</th>
					<th>Literate Populations</th>
					<th>Illiterate Population</th>
					<th>Working Population</th>
				</tr>
				<tr>
					<th>Male</th>
					<td><c:out value="${mandalInfoVO.totalMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingMalePersons}"/></td>
				</tr>
				<tr>
					<th>Female</th>
					<td><c:out value="${mandalInfoVO.totalFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingFemalePersons}"/></td>
				</tr>
				<tr>
					<th>Total</th>
					<td><c:out value="${mandalInfoVO.totalPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiteratePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliteratePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingPersons}"/></td>
				</tr>
			</table>
		</div>
	</div>
	<br/><br/>
	<div id="villageCensusDiv" style="display: none;">
		<div id="villageCensusDivHead"><h4><u>Villages Details..</u></h4></div>
		<div id="villageCensusDivBody" class="yui-skin-sam">
			<display:table id="villageCensusTable" class="searchresultsTable"
			 name="${villageDetailsVO.villageCensusList}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Village Name" 
					property="townshipNameURL" sortable="true" />
				<display:column style="text-align: left;" title="Total Populations"
					property="totalPersons" sortable="true" />
				<display:column style="text-align: left;" title="Male Populations"
					property="totalMalePersons" sortable="true" />
				<display:column style="text-align: left;" title="Female Populations"
					property="totalFemalePersons" sortable="true" />
				<display:column style="text-align: left;" title=" Total SCs"
					property="totalSCPersons" sortable="true" />
				<display:column style="text-align: center;" title="Total STs"
					property="totalSTPersons" sortable="true" />
				<display:column style="text-align: center;" title="Literate Population"
					property="totalLiteratePersons" sortable="true" />
				<display:column style="text-align: center;" title="Illiterate Population"
					property="totalIlliteratePersons"  sortable="true"/>
				<display:column style="text-align: center;" title="Working Population"
					property="totalWorkingPersons" sortable="true" />
			</display:table>
		</div>
	</div>
	
	<div id="mandalPageTab" class="yui-skin-sam"></div>
	
	<div class="yui-skin-sam"><div id="townshipPartyResultsPanel" ></div></div>	
</div>

<script type="text/javascript">

	buildTabNavigator();
	showMPTCZPTCResults();
	showElectionResultsInPopup();
	buildCensusDataTable();
	
</script>
</body>



</html>