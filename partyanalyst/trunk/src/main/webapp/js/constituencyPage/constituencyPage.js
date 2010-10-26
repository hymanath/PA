<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value='${constituencyDetails.constituencyName}'/> Constituency Page - News, Details, Mandals, Parties Performance, Voting Trendz, Election Results,MLA, MP,MPTC, ZPTC Election Results</title>

<META NAME="Keywords" CONTENT="<c:out value='${constituencyDetails.constituencyName}'/> Constituency, About <c:out value='${constituencyDetails.constituencyName}'/> Constituency, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Elections, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Elections Analysis,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Elections Results, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Leaders,  <c:out value='${constituencyDetails.constituencyName}'/> Constituency Parties, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Problems, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Politics, <c:out value='${constituencyDetails.constituencyName}'/> Constituency MLA's, <c:out value='${constituencyDetails.constituencyName}'/> Constituency MP's,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Voting Trends,<c:out value='${constituencyDetails.constituencyName}'/> Constituency MPTC, <c:out value='${constituencyDetails.constituencyName}'/> Constituency ZPTC, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Municipality, <c:out value='${constituencyDetails.constituencyName}'/> Constituency Corporation,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Cross Voting,<c:out value='${constituencyDetails.constituencyName}'/> Constituency Mandals">

<META NAME="Description" CONTENT=" <c:out value='${constituencyDetails.constituencyName}'/> constituency page provides the outline and basic information ,mandals information,constituency election results and latest news of the state.<c:out value='${statePage.stateName}'/> constituency page provides Assembly election results, Parliament election results, MPTC election results, ZPTC election results, Municipal election results, Corporation election results of all election years.">

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

<!-- YUI Dependency files (End) -->

<!-- jQuery dependency file start-->
	<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>	
<!-- jQuery dependency file end-->

<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>
<script type="text/javascript" src="js/constituencyPage/constituencyPage.js"></script>
<script type="text/javascript" src="js/districtPage/districtPage.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>


<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	

<script src="${mapKey}" type="text/javascript"></script>
<style type="text/css">
	.candidateDetailsStyle{
			background-color:#EFF3F7;
			border-color:#96B4D3;
			border-style:solid;
			border-width:1px 1px 1px 1px;
			margin-bottom:5px;margin-left:0;
			margin-top:0;
			padding:4px;
			color:#247CD4;
			font-size:13px;
			font-weight:bold;
		}
		
	.localBodyHeadStyle{
			color:#247CD4;
			font-size:13px;
			font-weight:bold;
			margin-bottom:5px;
			margin-left:0;
			margin-top:0;
			padding:4px;
	}

	a:link {
			COLOR: #247CD4;
	}
</style>
<script type="text/javascript">
	
	var constituencyResults,createGroupDialog;
	var parliamentResult;
	var tehsilElections={
			zptcElectionYears:[],
			mptcElectionYears:[]
	};
	var totalZptcSeats,totalMptcSeats;
	var counter = 0;
	var myDataTableForParty,myDataTableForMptcParty,zptcElectionYear,mptcElectionYear;
	var mptcElectionTypeId=${mptcElectionId},zptcElectionTypeId=${zptcElectionId};
	var mptcElectionType="${mptcElectionType}",zptcElectionType="${zptcElectionType}";
	var tehsilDetails={
			zptcArray:[],
			mptcArray:[],
			partyArray:[],
			partyMptcArray:[]
	};
	var constituencyId = ${constituencyId};
	var constituencyTYPE;
	var totalNoOflocalElectionsBodies = 0;
	google.load("visualization", "1", {packages:["corechart"]});
	function callAjax(jsObj,url)
	{	
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								
								if(jsObj.task == "getNextPrevCandidateTrendz")
								{
									buildCandidateVotingTrendzGraphData(jsObj,myResults);
								}										
								else if(jsObj.task == "getVotingTrendzForElectionYears")
								{									
									buildVotingTrendzData(myResults);
								}else if(jsObj.task == "getConstituencyResultsBySubLocations")
								{		
									constituencyResults = myResults;							
									buildConstituencyElecResultsDataTable("number");
								}else if(jsObj.task == "getConstituencyElections")
								{		
									buildElectionsSelectBox(myResults);									
								}else if(jsObj.task == "getParliamentConstituencyElectionResults")
								{		
									parliamentResult = myResults;
									buildParliamentResults(myResults);			
								}else if(jsObj.task == "getZptcElectionResults")
								{		
									if(myResults!= null &&  myResults.length>0){
										buildZptcResults(myResults);	
									}else{
										//hideZptcDiv();
										hideMptcZptcDiv();			
									}	
								}else if(jsObj.task == "getMptcElectionResults")
								{		
									if(myResults!= null &&  myResults.length>0){
										buildMptcResults(myResults);
									}else{
										//hideMptcDiv();
										hideMptcZptcDiv();			
									}	
								}else if(jsObj.task == "getProblemDetails")
								{									
									showProblemsHistoryReport(myResults);			
								}else if(jsObj.task == "municipalElectionsInfo")
								{		
									if(myResults.muncipalityVO!=null){	
										totalNoOflocalElectionsBodies++;						
										showMunicipalInfo(myResults);
									}else{										
										document.getElementById("muncipalDiv").style.display = "none";
										totalNoOflocalElectionsBodies--;
										checkTohideLocalElectionsBodyDiv();
									}				
								}else if(jsObj.task == "corporationElectionsInfo")
								{	
									if(myResults.muncipalityVO!=null){
										totalNoOflocalElectionsBodies++;								
										showCorporationInfo(myResults);
									}else{
										totalNoOflocalElectionsBodies--;
										document.getElementById("corporationDiv").style.display = "none";
										checkTohideLocalElectionsBodyDiv();
									}			
								}else if(jsObj.task == "greaterElectionsInfo" || jsObj.task == "getGhmcResultsBasedOnSelection")
								{		
									if(myResults.isDataExists==true){			
										totalNoOflocalElectionsBodies++;				
										showGreaterInfo(myResults);
									}else{
										totalNoOflocalElectionsBodies--;
										document.getElementById("greaterDiv").style.display = "none";
										checkTohideLocalElectionsBodyDiv();
									}			
								}else if(jsObj.task == "mandalVotesShareDetailsChart")
								{
									showMandalVotesShareDetailsChart(myResults);
								}else if(jsObj.task == "partiesPerformanceInDiffElectionsAjax")
								{
									showAllPartiesAllElectionResultsChart(myResults);
								}
								else if(jsObj.task == "connectToUser")
								{	
									closeConnectPanel(jsObj,myResults);
								}
								else if(jsObj.task == "getAllConnectedUsers")
								{	
									showAllConnectedUsersInPanel(jsObj,myResults);
								}	
								if(jsObj.task == "connectUserSet")
								{									
									showAllConnectedUsersStatus(jsObj,myResults);
								}
								if(jsObj.task == "getAllConnectedUsersByFilterView")
								{
									showAllConnectedUsersInPanelByFilterView(jsObj,myResults);
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

	function checkTohideLocalElectionsBodyDiv(){					
		if(totalNoOflocalElectionsBodies!=-3){
			document.getElementById("LocalElectionsDiv").style.display = "block";//Show's content
		}else{
			document.getElementById("LocalElectionsDiv").style.display = "none";//Hide's content
		}
	}
	
	function redirectZptcCandidateLink(){												
		 var browser1 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
		 browser1.focus();
	}

	function redirectMptcCandidateLink(){
		 var browser2 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
		 browser2.focus();
	}
		
		
	function buildParliamentResults(){
		var parliamentDiv = document.getElementById("parliamentElectionResultsDiv");
		if(parliamentDiv.style.display == "none")
		{
			parliamentDiv.style.display = "block";
		}
		var str = '';
		str += '<table>';
		str += '<th>Select Format You Want:</th>';
		str += '<td><input type="radio" name="parliament" value="number" checked="checked" onclick="buildParliamentResultDT(this.value)">By Votes</td>';
		str += '<td><input type="radio" name="parliament" value="percentage" onclick="buildParliamentResultDT(this.value)">By Percentage</td>';
		str += '</table>';
		str += '<div id="resultDataTableDiv"></div>';
		parliamentDiv.innerHTML = str;
		buildParliamentResultDT("number");
	}

	function showDetailedChart(chartName)
	{		
		var elmt = document.getElementById('detailedChartDIV');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');

	    var str='';
		str+='<img src="charts/'+chartName+'" />';
		divChild.innerHTML=str;
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "800px", 		
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:600,
				  y:800
	             } );
		createGroupDialog.render();
	}
	function handleCreateGroupSubmit()
	{
		createGroupDialog.hide();			
	}

	function handleCreateGroupCancel()
	{
		this.cancel();
	}
	function hideComparedResultsDiv()
    {
	var elmt = document.getElementById("detailedChartDiv");
	if(!elmt)
		return;
		
	elmt.style.display = 'none';
	
    }
	
	function buildParliamentResultDT(checked){
		var parliamentDiv = document.getElementById("resultDataTableDiv");
		var str = '';
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';

		if(parliamentResult == null){
			detailsDIV += 'No Data Available';
			return;	
		}
		
		str += '<div id="parliamentChartDiv"><img src="charts/'+parliamentResult.chartPath+'"></div>';
		detailsDIV += '<div><input type="button" class="button" onclick="showDetailedChart(\''+parliamentResult.detailedChartPath+'\')" value="Detailed Chart For Paliament"></div>';			
		str += '<div id="parliamentElecResDiv" style="margin-top:20px;">';
		str += '<table id = "parliamentElecResTable">';
		for(var j in parliamentResult.constituencyOrMandalWiseElectionVO){
			str += '<tr>';
			if(parliamentResult.constituencyOrMandalWiseElectionVO[j].showLink)
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationId+'&MANDAL_NAME='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'">'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</a></td>';
			else
				str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</td>';
			for(var k in parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs){
				if(checked == 'number')
					str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesEarned+'</td>';
				else
					str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesPercentage+'</td>';
			}
			str += '</tr>';
		}
		str += '</table>';
		str += '</div>';
		
		parliamentDiv.innerHTML = str;
		if(counter!=0){
			details.innerHTML = detailsDIV;
		}
		
		 var myColumnDefs = new Array();
		 var myFields = new Array();
		 
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
		 

		 for(var j in parliamentResult.candidateNamePartyAndStatus){
			var obj1 = {
						key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
						label:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
						sortable:true
					}
			var obj2 = {
						key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
						parser:"number"
					}
			myColumnDefs.push(obj1);
			myFields.push(obj2);
		 }

		 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get("parliamentElecResTable")); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
		 myDataSource.responseSchema = { 
								            fields:myFields    
								        };
		        
		var villageDataTable = new YAHOO.widget.DataTable("parliamentElecResDiv",myColumnDefs, myDataSource);
		
		var conName = parliamentResult.constituencyName;
		var elecYear = parliamentResult.electionYear;
		var elecTyp = parliamentResult.electionType;
		var parlDivElmnt = document.getElementById("parliamentChartDiv");
		getInteractiveChart(parlDivElmnt, parliamentResult.constituencyOrMandalWiseElectionVO,parliamentResult.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
	}
	
function showNextPreviousCandidateVotingTrendz(index,type)
{
	if(type == 'previous')
		candidateIndex-=1;
	else if(type == 'next')
		candidateIndex+=1;

	if(candidateIndex!=1)
		prevButtonElmt.disabled = false;
	else
		prevButtonElmt.disabled = true;

	if(candidateListSize>1 && candidateIndex!=candidateListSize)
		nextButtonElmt.disabled = false;
	else
		nextButtonElmt.disabled = true;

	for(var i =0;i<constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO.length;i++)
	{		
		var data = constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO[i];
		if(data.rank == candidateIndex)
		{
			var jsObj=	{
							partyId:data.partyId,
							partyName:data.partyName,
							partyLogo:data.partyLogo,
							partyFlag:data.partyFlag,
							candidateId:data.candidateId,
							candidateName:data.candidateName,							
							validVotes:data.validVotes,
							totalVotes:data.totalVotes,
							maleVotes:data.maleVotes,
							femaleVotes:data.femaleVotes,
							maleAndFemaleVotes:data.maleAndFemaleVotes,
							totalVotesPercent:data.totalVotesPercent,
							maleVotesPercent:data.maleVotesPercent,
							femaleVotesPercent:data.femaleVotesPercent,
							maleAndFemaleVotesPercent:data.maleAndFemaleVotesPercent,
							rank:data.rank,
							overallMaleVotesPercent : data.overallMaleVotesPercent,
							overallFemaleVotesPercent : data.overallFemaleVotesPercent,
							overallMaleOrFemaleVotesPercent : data.overallMaleOrFemaleVotesPercent,						
							status:data.status,
							maleVotesPercentInConstiVotes : data.maleVotesPercentInConstiVotes,
							femaleVotesPercentInConstiVotes :data.femaleVotesPercentInConstiVotes,
							maleOrFemaleVotesPercentInConstiVotes : data.maleOrFemaleVotesPercentInConstiVotes, 
							task:'getNextPrevCandidateTrendz'
						}

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getNextPrevCandidateVotingTrendz.action?"+rparam;
			
			callAjax(jsObj, url);			
			return;
		}
	}
}

function getProblemHistoryInfo(problemLocationId){
	var jsObj=
	{
			locationId:problemLocationId,
			task:"getProblemDetails"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/problemManagementHistoryResultsNew.action?"+rparam;
	callAjax(jsObj,url);
}

function getVotingTrendzForElectionYears()
{
	var jsObj=	{					
					task:'getVotingTrendzForElectionYears'
				}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
}

function getVotingTrendzForyear()
{

	var elements = document.getElementsByTagName('input'); 
	var electionType;
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="electionType" && elements[i].checked==true)
			electionType = elements[i].value;
	}
	if(!electionType)
		return;
	
	var selectElmt = document.getElementById(electionType+"_YearSelect");
	if(!selectElmt)
		return;
	selectElmt.disabled = false;

	var year = selectElmt.options[selectElmt.selectedIndex].text;
	if(year == "Assembly" || year == "Parliament")
		return;

	var value = selectElmt.options[selectElmt.selectedIndex].value;	
	var electionId = value.substring(0,(value.indexOf('_')));
	var electionTypeId = value.substring((value.indexOf('_')+1),value.length);

	

	var jsObj = {
					constituencyId:constituencyPageMainObj.electionTrendzReportVO.constituencyId,
					electionId:electionId,
					electionTypeId:electionTypeId,
					electionYear:year,
					task:'getVotingTrendzForElectionYears'
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
	
}
function getConstituencyResults(elecYear){
	var imgElmt = document.getElementById('AjaxImgDiv');
	var parliamentDiv = document.getElementById('parliamentElectionResultsDiv');
	parliamentDiv.style.display = "none";

	if(imgElmt.style.display == "none")
	{
          imgElmt.style.display = "block";
	}
	var jsObj = {
			constituencyId:${constituencyId},
			electionYear:elecYear,
			chartHeight: 350,
			chartWidth: 700,
			others:true,
			task:"getConstituencyResultsBySubLocations"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/assemblyWiseParliamentResultAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getConstituencyElections(){
	var jsObj = {
			constituencyId:${constituencyId},
			task:"getConstituencyElections"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
}

function buildConstituencyElecResultsDataTable(value){	
	if(constituencyResults == null)
		return;
	if(constituencyResults.electionType == 'Assembly'){		
		var parliamentButtonDiv = document.getElementById("parliamentResultsButtonDiv");
		var str = '';
		var electionSelect = document.getElementById("electionYearSelect");
		var elecYear = electionSelect.options[electionSelect.selectedIndex].text;
		str += "<table><tr>";
		str += '<td><input type="button" class="button" value="Know About Parliament(s)" onclick="getParliamentResults('+elecYear+')"/></td>';
		str += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart For Assembly"></div></td>';
		str += "</tr></table>";
		parliamentButtonDiv.innerHTML = str;		

		
	}
	if(constituencyResults.electionType != 'Assembly'){		
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart"></div></td>';
		details.innerHTML = detailsDIV;
	}
	if(counter>=1){
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart For Parliament"></div></td>';
		details.innerHTML = detailsDIV;
	}
	var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");
	var chart = '';
	chart += '<div><img src="charts/'+constituencyResults.chartPath+'" /></div>';
	chartResultDiv.innerHTML = chart;

    var conName = constituencyResults.constituencyName;
	var elecYear = constituencyResults.electionYear;
	var elecTyp = constituencyResults.electionType;
    getInteractiveChart(chartResultDiv,constituencyResults.constituencyOrMandalWiseElectionVO,constituencyResults.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
	
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var str = '';
	str += '<div id="elecResDiv" style="width=900px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';
	for(var i in constituencyResults.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName == 'Others *' || 
				!constituencyResults.constituencyOrMandalWiseElectionVO[i].showLink)
			str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</td>';
		else
		if(constituencyResults.electionType == 'Assembly'){
			if(constituencyResults.constituencyOrMandalWiseElectionVO[i].isUrban)
				str += '<td><a href="localBodyElectionAction.action?stateId=1&localBodyElectionTypeId=5&localBodyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
			else
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else
			str += '<td><a href="constituencyPageAction.action?constituencyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		for(var j in constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs){
			if(value == 'number')
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
		str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	var resultDiv = document.getElementById("missingDataInfoDiv");	
	var missingVotesDiv = '';
	missingVotesDiv+='<b>';
	missingVotesDiv += '<font color="Red"><b>*</b></font>';		
	missingVotesDiv += ' Others Include Postal Ballet Votes';
	missingVotesDiv+='</b>';
	resultDiv.innerHTML = missingVotesDiv;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constituencyResults.electionType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 

	 for(var i in constituencyResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					label:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							        };

	 if(constituencyResults.electionType == 'Parliament'){
		var extraInfoDiv = document.getElementById("missingDataInfoDiv");
		var str = '';
		str += '<br>';
		str += '<table>';
		str += '<tr>';
		str += '<td>';
		str += '<font color="Red"><b>*</b></font>';				
		str += '</td>';
		str += '<td><b>';
			if(constituencyResults.isDataInsufficient){
				str += ' Others Include Postal Ballet Votes, ';
				for(var i in constituencyResults.missingConstituencies)
					str += '<a href="constituencyPageAction.action?constituencyId='+constituencyResults.missingConstituencies[i].id+'">'+constituencyResults.missingConstituencies[i].name + '</a> Assembly ,';
				str = str.substring(0,str.length-1);
				str += ' Wise ${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Election Results';
			}else{
				str += ' Others Include Postal Ballet Votes';
			}
		str += '</b></td>';
		str += '</tr>';
		str += '</table>';		
		extraInfoDiv.innerHTML = str;		
	 }
	 
	var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource);
	var imgElmt = document.getElementById('AjaxImgDiv');
	if(imgElmt.style.display == "block")
	{
          imgElmt.style.display = "none";
	}
}

function getInteractiveChart(chartResultDiv,constituencyResults,partiesList,constiType,constiName,electionYear)
{
	
    var chartColumns = partiesList;
	var chartRows = constituencyResults;

	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

     //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number', colData);
	 }

      //for chart rows
	  for(var j in constituencyResults)
	  {
		  var array = new Array();
		  array.push(constituencyResults[j].locationName);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

	  var ctitle = 'Mandal Wise Election Results Chart For '+constiName+' '+constiType+' Constituency In '+electionYear; 
	  new google.visualization.LineChart(chartResultDiv).
	  //draw(data, {curveType: "function",width: 900, height: 400,title:ctitle});
	   draw(data, {curveType: "function",width: 900, height: 450,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
}

function getParliamentResults(elecYear){
	counter++;	
	var jsObj = {
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getParliamentConstituencyElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getParliamentMandalResultsAction.action?"+rparam;
	callAjax(jsObj, url);
}


function getZptcPartyDetails(elecYear){
	zptcElectionYear = elecYear;
	constituencyTYPE = constituencyPageMainObj.constituencyInfo.constituencyType;
	var jsObj = {
			constituencyType:constituencyPageMainObj.constituencyInfo.constituencyType,
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getZptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getMptcPartyDetails(elecYear){
	mptcElectionYear = elecYear;
	var jsObj = {
			constituencyType:constituencyPageMainObj.constituencyInfo.constituencyType,
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getMptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
	callAjax(jsObj, url);
}
function buildElectionsSelectBox(myResults){
	var selectDiv = document.getElementById("electionIdsSelectDiv");
	var electionYearSelect = '';
	var selectDivEl = document.getElementById("MandalwiseVotingTrendz");
	if(myResults.length == 0){
		selectDivEl.style.display = 'none';
		//electionYearSelect +='<b>Sorry, Constituency/Mandal Data Not Available For This Constituency</b>';
		//var resultDiv = document.getElementById("mandalOrConstiElecResultDiv");
		//resultDiv.style.display = "none";
		//selectDiv.innerHTML = electionYearSelect; 
		return;
	}

	var headingDiv = document.getElementById("MandalVotingTrendz_head");
	if('${constituencyDetails.constituencyType}' == 'Assembly')
		headingDiv.innerHTML = ' Mandal Wise Voting Trendz ';
	if('${constituencyDetails.constituencyType}' == 'Parliament')
		headingDiv.innerHTML = ' Assembly Wise Voting Trendz '; 
	
	electionYearSelect += '<table>';
	electionYearSelect += '<th>Select Election Year :</th>';
	electionYearSelect += '<th>';
	electionYearSelect += '<select id="electionYearSelect" class = "selectWidth" onchange = "getConstituencyResults(this.options[this.selectedIndex].text)">';
	for(var i in myResults)
	{			
		electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	electionYearSelect += '</select>';
	electionYearSelect += '</th>';
	electionYearSelect += '<td><div id="AjaxImgDiv" align="center" style="display:none;"><img src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	electionYearSelect += '<td><div id="parliamentResultsButtonDiv"></td>';
	electionYearSelect += '<td><div id="detailsDiv"></td>';
	electionYearSelect += '</table>';
	selectDiv.innerHTML = electionYearSelect; 
	getConstituencyResults(myResults[0].name);
}

function openConstVotingTrendzWindow(distId,constId,constName)
{			
	var browser1 = window.open("<s:url action="constituencyVotingTrendzAction.action"/>?districtId="+distId+"&constiId="+constId+"&constiName="+constName,"biElectionConstituencyResults1","scrollbars=yes,resizable=1,left=200,top=200");

	browser1.focus();
}

</script>
</head>
<body onLoad="getString()">
<div id="detailedChartDIV" class="yui-skin-sam"></div>
<div id="connectPeoplePopup_outer" class="yui-skin-sam"><div id="connectPeoplePopup"></div></div>
<div id="constituencyPageMain">

	<div id="electionResults_Panel_Main" class="yui-skin-sam">
			<div id="electionResults_Panel">
	</div>
	
	
	<table width="100%">
	<tr>
		<td><%@ include file="navigator.jsp" %></td>
		<c:if test="${constituencyDetails.hasAnalize}">
			<td><input type="button" class="button" value="${constituencyDetails.constituencyName} Detailed Analysis" onclick="openConstVotingTrendzWindow('${constituencyDetails.districtId}','${constituencyDetails.constituencyId}','${constituencyDetails.constituencyName}')" /></td>
		</c:if>
	</tr>
		<tr>
			<td class="alignTD">
				
				<div id="constituencyPageCenterInfoDiv">
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
						
							<div id="constituencyPageProfileInfoDiv_Main" class="innerLayoutDivClass">
								<div id="constituencyPageProfileInfoDiv_Head" class="layoutHeadersClass"> ${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType}  Constituency Details : </div>
								<div id="constituencyPageProfileInfoDiv_Body" class="layoutBodyClass">
									<div id="constituencyInfoDiv">
										<div id="constituencyInfoDiv_Head"></div>
										<div id="constituencyInfoDiv_Body"></div>
									</div>	
								</div>
							</div>
					</div>	
					
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
					
						<div id="constituencyPageCandidateInfo_Main" class="innerLayoutDivClass">
							<div id="constituencyPageCandidateInfo_Head" class="layoutHeadersClass"></div>
							<div id="constituencyPageCandidateInfo_Body" class="layoutBodyClass yui-skin-sam">
								<div id="constituencyPageCandidateInfo_Top"></div>
								<div id="constituencyPageCandidateInfo_Bottom"></div>
							</div>
						</div>		
					</div>

					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>	
						<div id="constituencyPageElectionInfoDiv_Main" class="innerLayoutDivClass">							
							<div id="constituencyPageElectionInfoDiv_Head" class="layoutHeadersClass"></div>
							<div id="constituencyPageElectionEnlargedImgDiv" class="layoutHeadersClass"></div>
							<div id="constituencyPageElectionImgDiv" class="layoutHeadersClass"></div>
							<div id="constituencyPageElectionInfoDiv_Body" class="layoutBodyClass"></div>
						</div>		
					</div>
				


			</td>
			<!--<td> <div class="spacerTD"> </div> </td>-->
			<td class="alignTD">
				<div id="constituencyRightContentDiv">					
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div> 

						<div id="constituencyPageRightMapDiv" class="contentDivClass">
							<div id="map_canvas"></div>
											
							<div id="constituencyPeopleConnectDiv">
								<div id="constituencyPeopleConnectDiv_Head"></div>
								<div id="constituencyPeopleConnectDiv_Body"></div>
							</div>
							<div id="problemViewingDiv">
								<div id="problemViewingDiv_Head"></div>
								<div id="problemViewingDiv_Body"></div>
							</div>
							<div id="problemPostingDiv">
								<div id="problemPostingDiv_Head"></div>
								<div id="problemPostingDiv_Body"></div>
							</div>
							<div id="votingTrendzDiv">
								<div id="votingTrendzDiv_Head"></div>
								<div id="votingTrendzDiv_Body"></div>
							</div>							
						</div>	
					</div>
				</div>
			</td>			
		</tr>
		<tr>
			<td colspan="2">
									<div id="constituencyCenterContentOuter1" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>

						<div id="mandalsVotersInfoDiv_Main" class="innerLayoutDivClass">
						<div id="mandalsVotersInfoDiv_Head" class="layoutHeadersClass"></div>
							<!--
							<table width="100%" border="1" cellspacing="3">
								<tr>
									<c:forEach var="chart" items="${constituencyVO.pieChartNames}">
										<td align="center"><img src="charts/${chart}" border="0"></td>
									</c:forEach>	
								</tr>
								<tr>
									<c:forEach var="extra" items="${constituencyVO.extraInfo}">
										<td align="left" style="border: 0px;color:#707070;"><b>${extra}</b></td>
									</c:forEach>
								</tr>
							</table>
							
						</div>-->
						
						<div id="mandalsVotersInfoDiv_Body" class="layoutBodyClass yui-skin-sam"></div>
						</div>
				
				</div>
			</td>
		</tr>
		
		<tr>		
		<td colspan="2">
		<div id="MandalwiseVotingTrendz" class="rounded" style="width: 910px;">
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
			<div id="MandalVotingTrendz_head" class="layoutHeadersClass"></div>
			<div id="electionIdsSelectDiv" style="padding-left:10px;"></div>
			<div id="mandalOrConstiElecResultDiv">
			<div id="parliamentElectionResultsDiv" style="overflow:auto;"></div>
			<div id="electionResultsInConstituencyDiv"></div>
			<table><tr>
					<td id="labelRadio"><b>Select The Format You Want :</b></td>
					<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElecResultsDataTable(this.value)">By Votes </td>
					<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElecResultsDataTable(this.value)"/>By Percentage </td>
			</tr></table>			
			<div id="resultsDataTableDiv"></div>
			<div id="missingDataInfoDiv"></div>
			</div>
		</div>
		</td>
		</tr>
	<!-- Local Elections Info -->	
		<tr>		
			<td colspan="2">
				<div id="LocalElectionsDiv" class="rounded" style="width: 910px;">
					<div class="corner topLeft"></div>
					<div class="corner topRight"></div>
					<div class="corner bottomLeft"></div>
					<div class="corner bottomRight"></div>
					<div id="LocalElections_heading" class="layoutHeadersClass">All Local Elections Happened In ${constituencyDetails.constituencyName} Constituency</div>
					<table width="100%">
						<tr>
							<td>
								<div id = "muncipalDiv">
									<div>
										<b>Select Municipal Election&nbsp;:&nbsp;</b><s:select theme="simple" id="municipalitySelect" name="municipalities" list="municipalElections" listKey="id" listValue="name" onchange="getMunicipalityResults()"></s:select>		
									</div>
									<div id="municipalityData_main"></div>
								</div>
							</td>
							<td>
								<div id = "corporationDiv">
									<div>
										<b>Select Corporation Election&nbsp;:&nbsp;</b><s:select theme="simple" id="corporationSelect" label="Select Cororation Election" name="corporations" list="corporateElections" listKey="id" listValue="name" onchange="getCoroporationResults()"></s:select>
									</div>
									<div id="coroporationData_main"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id = "greaterDiv">
									<div>
										<b>Select Greater Election&nbsp;:&nbsp;</b><s:select theme="simple" id="greaterSelect" label="Select Greater Election" name="greaters" list="greaterElections" listKey="id" listValue="name" onchange="getGreaterResults()"></s:select>
									</div>
									<div id="wardsElectionResults_body" class="productFeatureBody yui-skin-sam">
											<div id="wardsElectionResults_body_radioSelectDiv" style="padding:5px;font-weight:bold;">
												Select Results Criteria :
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)"  value="all" checked="checked">All</input>
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)" value="partyWise">Party Wise Results</input>	
												<s:select theme="simple" cssClass="selectBoxWidth" cssStyle="visibility:hidden;width:100px;" name="wardWise_parties" id="wardWise_parties" list="greaterInfo.listOfParties" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getWardWiseElectionResults('partyWise',this.options[this.selectedIndex].value)"/>
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)" value="wardWise">Ward Wise Results</input>	
												<s:select theme="simple" cssClass="selectBoxWidth" cssStyle="visibility:hidden;width:100px;" name="wardWise_wards" id="wardWise_ward" list="greaterInfo.listOfWards" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getWardWiseElectionResults('wardWise',this.options[this.selectedIndex].value)"/>
											</div>											
										</div>
									<div id="GHMCData_main"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	<!-- Local Elections Info -->	
		<tr>
			<td colspan="2">
			<div class="rounded" id="zptcMptcCompleteData" style="width: 910px;">
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
				<table>
						<tr>
							<td align="center" valign="center">
								<div id="zptc_main">
									<div id="zptc_head">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td>	
													<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:400px;padding:9px;height:18px;">
														<span>Total Number of ZPTC's : </span>
														<span id="totalZptcCountResultDiv"></span>
													</div>
												</td>
												<td><img src="images/icons/districtPage/header_right.gif"/></td>
											</tr>
										</table>
									</div>
								<div id="zptc_body">
										<table>									
											<tr><td>
													<table><tr><td>
																	<table ><tr>
																   		<td><div id="zptcElectionIdsSelectDiv" style="padding-left:10px;" class="yui-skin-sam"></div></td>
																   		<td><div id="zptcCandidateLink"></div></td>
															   		</tr></table>
															   </td></tr><tr><br></tr><tr><br></tr>
														   <tr>
															   <td><div id="zptcPartyTrendsDetailsDiv" style="margin-top: 13px;"></div></td>
													</tr></table>
											</td></tr>
										</table>	
									</div>
								</div>
							</td>			
							
							<td align="center"  valign="center">
								<div id="mptc_main">
									<div id="mptc_head">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td>	
													<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:400px;padding:9px;height:18px;">
														<span>Total Number of MPTC's : </span>
														<span id="totalMptcCountResultDiv"></span>
													</div>
												</td>
												<td><img src="images/icons/districtPage/header_right.gif"/></td>
											</tr>
										</table>
									</div>
								<div id="mptc_body">
										<table>									
											<tr><td>
													<table><tr><td>
																	<table ><tr>
																   		<td><div id="mptcElectionIdsSelectDiv" style="padding-left:10px;" class="yui-skin-sam"></div></td>
																   		<td><div id="mptcCandidateLink"></div></td>
															   		</tr></table>
															   </td></tr><tr><br></tr><tr><br></tr>
														   <tr>
															   <td> <div id="mptcPartyTrendsDetailsDiv" style="margin-top: 13px;" ></div></td>
													</tr></table>
											</td></tr>
										</table>	
									</div>
								</div>
							</td>	
						</tr>
				</table>
			</div>
			</td>
		</tr>		
	</table>	
</div>
</div>
<script type="text/javascript">

	/*	Constituency Page basic Info
		-----------------------------
	*/
	constituencyPageMainObj.constituencyAddress="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";
	constituencyPageMainObj.contextPath = "<%=request.getContextPath()%>";	
	
	constituencyPageMainObj.forwardTask = "${redirectLoc}";
	constituencyPageMainObj.constituencyInfo.constituencyId = "${constituencyId}";
	constituencyPageMainObj.constituencyInfo.constituencyName = "${constituencyDetails.constituencyName}";
	constituencyPageMainObj.constituencyInfo.districtName = "${constituencyDetails.districtName}";
	constituencyPageMainObj.constituencyInfo.stateName = "${constituencyDetails.stateName}";
	constituencyPageMainObj.constituencyInfo.startDate = "${constituencyDetails.startDate}";
	constituencyPageMainObj.constituencyInfo.deformDate = "${constituencyDetails.deformDate}";
	constituencyPageMainObj.constituencyInfo.constituencyType = "${constituencyDetails.constituencyType}";
	
	
	/*	Constituency Election Results Info
		----------------------------------
	*/

	<c:forEach var="constituencyElectionResults" items="${constituencyElectionResultsVO}">	
	var constiObj=
				{
					candidateName:'${constituencyElectionResults.candidateResultsVO.candidateName}',
					partyName:'${constituencyElectionResults.candidateResultsVO.partyName}',
					year:'${constituencyElectionResults.electionYear}',
					votesEarned:'${constituencyElectionResults.candidateResultsVO.votesEarned}',
					votesPercentage:'${constituencyElectionResults.candidateResultsVO.votesPercentage}',
					oppositionCandInfo:[]
				 };
			<c:forEach var="detailedResult" items="${constituencyElectionResults.candidateOppositionList}" >
				var oppositionList={
										candidateName:'${detailedResult.candidateName}',
										partyName:'${detailedResult.partyName}',
										year:'${constituencyElectionResults.electionYear}',
										votesEarned:'${detailedResult.votesEarned}',
										votesPercentage:'${detailedResult.votesPercentage}'										
									};
						
					constiObj.oppositionCandInfo.push(oppositionList);
			</c:forEach>			
			constituencyPageMainObj.constituencyElectionInfo.push(constiObj);			
	</c:forEach>
	
	/*	Constituency Voters Info
		-------------------------
	*/

function buildElectionResults()
{
		
	var HeadElmt = document.getElementById('constituencyPageElectionInfoDiv_Head');
	var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");
	var BodyElmt = document.getElementById('constituencyPageElectionInfoDiv_Body');

	
	
	if(HeadElmt)
		HeadElmt.innerHTML = ' Election Information in '+constituencyPageMainObj.constituencyInfo.constituencyName;

	var chartName = "${chartName}";
	var chart = '';
	chart+='<img src="charts/'+chartName+'" style="width:600px"/>';
	chartResultDiv.innerHTML = chart;

	var enlargedChartName = "${enlargedChartName}";
	var details = document.getElementById("constituencyPageElectionEnlargedImgDiv");
	var detailsDIV = '';
	detailsDIV += '<table><tr>';
	detailsDIV += '<td><div><input type="button" style="background:none repeat scroll 0 0 #335291;color:#FFFFFF;font-size:13px;margin-left:432px;padding:5px;width:113px;" onclick="showDetailedChart(\''+enlargedChartName+'\')" value="Detailed Chart"></div></td>';
	details.innerHTML = detailsDIV;
	
	var elecStr = '';
	for(var i in constituencyPageMainObj.constituencyElectionInfo)
	{
		var data = constituencyPageMainObj.constituencyElectionInfo[i];
		var info = constituencyPageMainObj.constituencyInfo;
		elecStr+='<div id="constituencyElectionInfo_'+i+'" class="electionInformationClass" onclick="showDetailedElectionResult(this.id)">';
		elecStr+='<span id="pointerImg"> <img height="10" width="10" src="'+constituencyPageMainObj.contextPath+'/images/icons/arrow.png"/></span>';
		elecStr+='<span id=""> '+info.constituencyType+' Election Results in '+data.year+' - '+data.candidateName+' Won with '+data.votesPercentage+' votes %</span>';		
		elecStr+='</div>';
	}
	
	if(BodyElmt)
		BodyElmt.innerHTML=elecStr;
}

/*if(imgElmt.style.display == "block")
{
      imgElmt.style.display = "none";
}*/

function showCorporationInfo(myResults){
	var HeadElmt = document.getElementById('coroporationData_main');
	if(myResults.muncipalityVO == null){
		var showDiv = document.getElementById('corporationDiv');
		showDiv.style.display = "none";
	}
	buildCorpOrMunicipTable(HeadElmt, myResults, "Corporation");
}

function buildCorpOrMunicipTable(divId, myResults, elecType){
	var str = '';
	for(var i in myResults.muncipalityVO){
		str += '<div  class="localBodyHeadStyle">';
		str += '<a href=\"localBodyElectionAction.action?stateId='+myResults.muncipalityVO[i].stateId+'&localBodyElectionTypeId='+myResults.muncipalityVO[i].electionTypeId+'&localBodyId='+myResults.muncipalityVO[i].muncipalityId+'\">Detailed View of '+myResults.muncipalityVO[i].muncipalityName+' Election Results '+elecType+' In '+myResults.muncipalityVO[i].latestMuncipalElectionYear+'</a></div>';
		str += '<table><tr>';
		str += '<td><div id=\"'+elecType+'TableDiv_'+i+'\"></div></td>';
		str += '<td><div><img src=\"charts\\'+myResults.muncipalityVO[i].chartName+'\"></div></td>';
		str += '</tr></table>';
	}
	divId.innerHTML = str;
	buildDataTable(elecType);
}

function buildDataTable(elecType){

	for(var i in myResults.muncipalityVO){
		var resultsDataSource = new YAHOO.util.DataSource(myResults.muncipalityVO[i].muncipalityVO);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]
		};

		var resultsColumnDefs = [ {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Seats Contested",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];		
		myDataTableForMptcParty = new YAHOO.widget.DataTable(elecType+"TableDiv_"+i,resultsColumnDefs, resultsDataSource,null);
	}
	
}

function showMunicipalInfo(myResults){
	var HeadElmt = document.getElementById('municipalityData_main');
	if(myResults.muncipalityVO == null){
		var showDiv = document.getElementById('muncipalDiv');
		showDiv.style.display = "none";
	}
	buildCorpOrMunicipTable(HeadElmt, myResults, "Municipality");
}

function showMandalVotesShareDetailsChart(myResults)
{
	var mandalwiseVotersShare = myResults.assembliesOfParliamentInfo;
		
		for(var c in mandalwiseVotersShare){

			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Mandals');
			data.addColumn('number', 'Voters % Share');


			data.addRows(mandalwiseVotersShare[c].votersInfoForMandalVO.length);
			var k=0;
			for (var i in mandalwiseVotersShare[c].votersInfoForMandalVO)
			{
			data.setValue(k, 0, mandalwiseVotersShare[c].votersInfoForMandalVO[i].mandalName);
			data.setValue(k, 1,  mandalwiseVotersShare[c].votersInfoForMandalVO[i].totVoters);
			k++;
			}
			
			var ctitle='';
			var chartDiv='';
            
			if(c == 0)
			{
				if(document.getElementById('divInteractive_Chart_0')){
					if(c == 0){
						chartDiv = document.getElementById('divInteractive_Chart_0');
						ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2004';
					}
					var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width: 580, height: 320, title: ctitle, legendFontSize:14,fontSize:13,titleFontSize:16,tooltipFontSize:15, stroke:3});
				}
			}
			else
			{
				if(document.getElementById('divInteractive_Chart_1')){
					if(c == 1){
					chartDiv = document.getElementById('divInteractive_Chart_1');
					ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2009';
					}
					var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width: 580, height: 320, title: ctitle, legendFontSize:14,fontSize:13,titleFontSize:16,tooltipFontSize:15, stroke:3});
				}
			}
			
		}
}

function showAllPartiesAllElectionResultsChart(myResults)
{
   var chartColumns = myResults[0].partiesList;
  
     var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

     //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].name;
	   data.addColumn('number', colData);
	 }

      //for chart rows
	  for(var j in myResults)
	  {
		  var array = new Array();
		  var year = myResults[j].electionYear+" "+myResults[j].electionType;
		  array.push(year);

		  for(var k in myResults[j].partyResultsVO)
		  {
			  var percentage = myResults[j].partyResultsVO[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

	  var ctitle = 'All Parties Performance In Different Elections'; 
	  var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");
	  new google.visualization.LineChart(chartResultDiv).
	  draw(data, {curveType: "function",width: 650, height: 400,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});


}

function showGreaterInfo(myResults){
	var HeadElmt = document.getElementById('GHMCData_main');
	if(myResults.localElectionsInfo == null){
		var showDiv = document.getElementById('greaterDiv');
		showDiv.style.display = "none";
	}
	var str = '';
	for(var i in myResults.localElectionsInfo){
		str += '<div class="localBodyHeadStyle">';
		str += '<a href=\"localBodyElectionAction.action?stateId='+myResults.localElectionsInfo[i].stateId+'&localBodyElectionTypeId='+myResults.localElectionsInfo[i].electionTypeId+'&localBodyId='+myResults.localElectionsInfo[i].id+'\">Detailed view of '+myResults.localElectionsInfo[i].name+' Election Results In '+myResults.localElectionsInfo[i].electionYear+'</a></div>';
		str += '<div><img src=\"charts\\'+myResults.localElectionsInfo[i].chartName+'\"></div>';
		str += '<div id=\"greaterTableDiv_'+i+'\"></div>';
	}
	HeadElmt.innerHTML = str;
	for(var i in myResults.localElectionsInfo){
		var resultsDataSource = new YAHOO.util.DataSource(myResults.localElectionsInfo[i].wardwiseResultsForParty);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "partyName"
			}, {
				key : "candidateName"
			}, {
				key : "votesEarned"
			}, {
				key : "votesPercentage"
			}, {
				key : "rank"
			}, {
				key : "totalVotes"
			}]
		};

		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Ward",
			sortable : true
		}, {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "candidateName",
			label : "Candidate",
			sortable : true
		}, {
			key : "votesEarned",
			label : "Votes Gained",
			sortable : true
		}, {
			key : "votesPercentage",
			label : "Votes %",
			sortable : true
		}, {
			key : "rank",
			label : "Rank",
			sortable : true
		}, {
			key : "totalVotes",
			label : "Total Voters",
			sortable : true
		}  ];		
		if(myResults.localElectionsInfo[i].wardwiseResultsForParty.length >10)
		{
			var recordsPerPage = {
		    paginator : new YAHOO.widget.Paginator({
		        rowsPerPage: 10 
		    })
			};
		}	
		myDataTableForMptcParty = new YAHOO.widget.DataTable("greaterTableDiv_"+i,resultsColumnDefs, resultsDataSource,recordsPerPage);
	}
	
}

function showDetailedElectionResult(id)
{

	var index = id.substring((id.indexOf('_')+1),id.length);
	var data = constituencyPageMainObj.constituencyElectionInfo[index];
	var info = constituencyPageMainObj.constituencyInfo;

	var constiId = ${constituencyId}; 
	var elecType = constituencyPageMainObj.constituencyInfo.constituencyType; 
	var elecYear = data.year; 

	
    var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
    browser1.focus();	
}

	function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
	{
	   var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   browser1.focus();
	}

	<c:forEach var="vInfo" items="${constituencyVO.assembliesOfParliamentBasicInfo}" >	
		var obj ={
					year:'${vInfo.year}',
					info:[]
				};
		<c:forEach var="info" items="${vInfo.votersBasicInfoForMandalVO}" >	
		var urlStr = '';
		if('${constituencyVO.electionType}' == 'Parliament')
			urlStr += 'constituencyPageAction.action?constituencyId=${info.mandalId}';
		else
			urlStr += 'mandalPageElectionInfoAction.action?MANDAL_ID=${info.mandalId}&MANDAL_NAME=${info.mandalName}';
		var vObj=
				{					
					mandalName:'<a href="'+urlStr+'"> ${info.mandalName}</a>',					
					isPartial:'${info.isPartial}'
				 };
			obj.info.push(vObj);
		</c:forEach>
			constituencyPageMainObj.constituencyVotersBasicInfo.push(obj);
	</c:forEach>
		
	
	<c:forEach var="vInfo" items="${constituencyVO.assembliesOfParliamentInfo}" >	
		var obj ={
					year:'${vInfo.year}',
					info:[]
				};
		<c:forEach var="info" items="${vInfo.votersInfoForMandalVO}" >	
		var urlStr = '';
		if('${constituencyVO.electionType}' == 'Parliament')
			urlStr += 'constituencyPageAction.action?constituencyId=${info.mandalId}';
		else
			urlStr += 'mandalPageElectionInfoAction.action?MANDAL_ID=${info.mandalId}&MANDAL_NAME=${info.mandalName}';
		var vObj=
				{
					mandalId:'${info.mandalId}',
					mandalName:'<a href="'+urlStr+'"> ${info.mandalName}</a>',
					mandalMaleVoters:'${info.totalMaleVoters}',
					mandalFemaleVoters:'${info.totalFemaleVoters}',
					mandalTotalVoters:'${info.totalVoters}',
					isPartial:'${info.isPartial}'
				 };
			obj.info.push(vObj);
		</c:forEach>
			constituencyPageMainObj.constituencyVotersInfo.push(obj);
	</c:forEach>
	

	/*	Assembly Candidate Info
		-------------------------
	*/
	<c:if test="${candidateDetailsForConstituency.assemblyCandidateInfo != null}">
	<c:forEach var="cInfo" items="${candidateDetailsForConstituency.assemblyCandidateInfo}">	
	var candidateObj={
						constituencyId : '${cInfo.constituencyId}',
						constituencyName : '<a href="constituencyPageAction.action?constituencyId=${cInfo.constituencyId}"> ${cInfo.constituencyName}</a>',
						constituencyType:'${cInfo.constituencyType}',
						deformDate:'${cInfo.deformDate}',
						candidateId : '${cInfo.candidateId}',
						candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${cInfo.candidateId}"> ${cInfo.candidateName}</a>',	
						partyId:' ${cInfo.partyId}',
						party : '${cInfo.party}',
						partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${cInfo.partyFlag}" height="30" width="40"/>',
						knowMore:'<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\'${cInfo.constituencyId}\',\'${cInfo.constituencyType}\',\'${cInfo.latestElecYear}\')">view results</a>'
					 };		
	
	constituencyPageMainObj.presentAssemblyCandidate.push(candidateObj);
	</c:forEach>
	</c:if>
	
	
	/*	Parliament Candidate Info
		-------------------------
	*/
	<c:if test="${candidateDetailsForConstituency.parliamentCandidateInfo != null}">
	var pmtObj = {
					constituencyId : '${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}',
					constituencyName :  '<a href="constituencyPageAction.action?constituencyId=${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}">${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}</a>',									
					constituencyType:'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}',
					deformDate:'${candidateDetailsForConstituency.parliamentCandidateInfo.deformDate}',
					candidateId : '${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}',
					candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"> ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</a>',		
					partyId:' ${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}',
					party : '${candidateDetailsForConstituency.parliamentCandidateInfo.party}',
					partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/>',
					knowMore:'<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}\',\'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}\',\'${candidateDetailsForConstituency.parliamentCandidateInfo.latestElecYear}\')">view results</a>'
				 };		
	constituencyPageMainObj.presentParliamentCandidate.push(pmtObj);
	</c:if>
	//constituencyName : '<a href="constituencyPageAction.action?constituencyId=${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}&electionType=${constituencyDetails.constituencyType}&delimitation=${constituencyDetails.deformDate}">${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}</a>',

	/*	Constituency problems Info
		-------------------------
	*/
	
	<c:forEach var="problem" items="${problemBean}">	
	var problemObj={
						problemId:'${problem.problemId}',
						problem:'${problem.problem}',
						description:'${problem.description}',
						state:'${problem.state}',
						district:'${problem.district}',
						constituency:'${problem.constituency}',
						tehsil:'${problem.tehsil}',
						village:'${problem.village}',
						hamlet:'${problem.hamlet}',
						reportedDate:'${problem.postedDate}',
						existingFrom:'${problem.existingFrom}',
						name:'${problem.name}',
						postedPersonName:'${problem.postedPersonName}',
						email:'${problem.email}',						
						phone:'${problem.phone}',
						mobile:'${problem.mobile}',
						address:'${problem.address}',
						problemLocationId:'${problem.problemLocationId}',
					};
		
	constituencyPageMainObj.problemsInfo.push(problemObj);
	</c:forEach>

	/*
		Voting Trendz Info 
		------------------
	*/	

		var vTObj = constituencyPageMainObj.electionTrendzReportVO;

		vTObj.state = '${electionTrendzReportVO.state}';
		vTObj.electionType = '${electionTrendzReportVO.electionType}';
		vTObj.electionYear = '${electionTrendzReportVO.electionYear}';
		vTObj.constituencyId = '${electionTrendzReportVO.constituencyId}';
		vTObj.constituencyName = '${electionTrendzReportVO.constituencyName}';
				

		vTObj.electionTrendzOverviewVO.totalVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.totalVoters}';
		vTObj.electionTrendzOverviewVO.maleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVoters}';
		vTObj.electionTrendzOverviewVO.femaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVoters}';
		vTObj.electionTrendzOverviewVO.maleAndFemaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemaleVoters}';	
		
		vTObj.electionTrendzOverviewVO.totalPolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.totalPolledVotes}';
		vTObj.electionTrendzOverviewVO.malePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.malePolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePolledVotes}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePolledVotes}';

		vTObj.electionTrendzOverviewVO.pollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.pollingPercent}';
		vTObj.electionTrendzOverviewVO.malePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercent}';
		vTObj.electionTrendzOverviewVO.femalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercent}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePollingPercent}';
		
		vTObj.electionTrendzOverviewVO.maleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercent}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercent}';
		vTObj.electionTrendzOverviewVO.maleOrFemaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemaleVotersPercent}';

		vTObj.electionTrendzOverviewVO.overallMalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallFemalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallMaleOrFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMaleOrFemalePollPercent}';
				
		vTObj.electionTrendzOverviewVO.maleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersInConstituency}';
		vTObj.electionTrendzOverviewVO.femaleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersInConstituency}';

		vTObj.electionTrendzOverviewVO.maleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercentInConsti}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercentInConsti}';

		vTObj.electionTrendzOverviewVO.malePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercentInTotalPolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercentInTotalPolledVotes}';
		vTObj.electionTrendzOverviewVO.maleOrFemalePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemalePollingPercentInTotalPolledVotes}';
	
		
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz}';


		<c:forEach var="candidate" items="${electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO}">	
			var cObj = {
							partyId:'${candidate.partyId}',
							partyName:'${candidate.partyName}',
							partyLogo:'${candidate.partyLogo}',
							partyFlag:'${candidate.partyFlag}',
							candidateId:'${candidate.candidateId}',
							candidateName:'${candidate.candidateName}',							
							validVotes:'${candidate.validVotes}',
							totalVotes:'${candidate.totalVotes}',
							maleVotes:'${candidate.maleVotes}',
							femaleVotes:'${candidate.femaleVotes}',
							maleAndFemaleVotes:'${candidate.maleAndFemaleVotes}',
							totalVotesPercent:'${candidate.totalVotesPercent}',
							maleVotesPercent:'${candidate.maleVotesPercent}',
							femaleVotesPercent:'${candidate.femaleVotesPercent}',
							maleAndFemaleVotesPercent:'${candidate.maleAndFemaleVotesPercent}',
							rank:'${candidate.rank}',
							overallMaleVotesPercent : '${candidate.overallMaleVotesPercent}',
							overallFemaleVotesPercent : '${candidate.overallFemaleVotesPercent}',
							overallMaleOrFemaleVotesPercent : '${candidate.overallMaleOrFemaleVotesPercent}',						
							maleVotesPercentInConstiVotes:'${candidate.maleVotesPercentInConstiVotes}',
							femaleVotesPercentInConstiVotes:'${candidate.femaleVotesPercentInConstiVotes}',
							maleOrFemaleVotesPercentInConstiVotes:'${candidate.maleOrFemaleVotesPercentInConstiVotes}',
							status:'${candidate.status}'
					   };
					  vTObj.electionTrendzOverviewVO.partyElectionTrendzVO.push(cObj);
		</c:forEach>
		
		
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.status = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.status}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes}';

		
		<c:forEach var="asmElection" items="${electionTrendzReportVO.prevElectionYearsInfo.assemblyElections}">	
			var assemblyElecObj={
									electionId:'${asmElection.electionId}',
									electionTypeId:'${asmElection.electionTypeId}',
									electionType:'${asmElection.electionType}',
									stateId:'${asmElection.stateId}',
									state:'${asmElection.state}',
									electionYear:'${asmElection.electionYear}' 
								};

			vTObj.previousElectionYears.assemblyElections.push(assemblyElecObj);
		</c:forEach>

		<c:forEach var="parElection" items="${electionTrendzReportVO.prevElectionYearsInfo.parliamentElections}">	
			var parElecObj={
									electionId:'${parElection.electionId}',
									electionTypeId:'${parElection.electionTypeId}',
									electionType:'${parElection.electionType}',
									stateId:'${parElection.stateId}',
									state:'${parElection.state}',
									electionYear:'${parElection.electionYear}'
								};

			vTObj.previousElectionYears.parliamentElections.push(parElecObj);
		</c:forEach>

		<c:forEach var="zptcElectionYears"  items="${zptcElectionYears}" >
		var ob={
					id:'${zptcElectionYears.id}',
					value:'${zptcElectionYears.name}'
				};
		tehsilElections.zptcElectionYears.push(ob);	
		</c:forEach>

		<c:forEach var="mptcElectionYears"  items="${mptcElectionYears}" >
		var ob={
					id:'${mptcElectionYears.id}',
					value:'${mptcElectionYears.name}'
				};
		tehsilElections.mptcElectionYears.push(ob);	
		</c:forEach>
		
		<c:forEach var="candidate" items="${userDetails.candidateVO}">	
			var userObj={
								id:'${candidate.id}',
								candidateName:'${candidate.candidateName}',
								status:'${candidate.status}',
								constituencyName:'${candidate.constituencyName}'
							};
				
			constituencyConnectedPeople.push(userObj);
		</c:forEach>
		<c:forEach var="status" varStatus="stat" items="${messageTypes.messageTypes}">
			var obj =	{
							id:'${status.id}',
							name:'${status.name}'
						};
			connectStatus.push(obj);
		</c:forEach>
		
		userLoginStatus = '${userDetails.loginStatus}';
		userId = '${userDetails.userId}';
	
	getAllZptcYears();	  
	getAllMptcYears();
	initializeConstituencyPage();
	getConstituencyElections();
	getMunicipalityResults();
	getCoroporationResults();
	getGreaterResults();
	
</script>
</body>
</html>