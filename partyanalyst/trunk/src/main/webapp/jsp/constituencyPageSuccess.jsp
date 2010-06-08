<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> ${constituencyDetails.constituencyName} Constituency Details</title>


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

<script type="text/javascript" src="js/constituencyPage/constituencyPage.js"></script>
<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
<script type="text/javascript" src="js/districtPage/districtPage.js"></script>	
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRSqcWSqDo-rwCiW8VjO_0U_k7HAuxQBSweyAZ1v5ozDSPMDKAFtPwSrGw&sensor=true"
            type="text/javascript"></script>
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
</style>
<script type="text/javascript"><!--
	var constituencyResults,createGroupDialog;
	var parliamentResult;
	var tehsilElections={
			zptcElectionYears:[],
			mptcElectionYears:[]
	};
	var counter = 0;
	var myDataTableForParty,myDataTableForMptcParty,zptcElectionYear,mptcElectionYear,mptcElectionTypeId=3,zptcElectionTypeId=4,mptcElectionType="MPTC",zptcElectionType="ZPTC";
	var tehsilDetails={
			zptcArray:[],
			mptcArray:[],
			partyArray:[],
			partyMptcArray:[]
	};
	var constituencyId = ${constituencyId};
	var constituencyTYPE;
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
										hideZptcDiv();			
									}	
								}else if(jsObj.task == "getMptcElectionResults")
								{		
									if(myResults!= null &&  myResults.length>0){
										buildMptcResults(myResults);
									}else{
										hideMptcDiv();			
									}	
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

	function initializeResultsTableForParty(){
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyArray);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
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
	
		var resultsColumnDefsForTehsil = [ {
			key : "partyName",
			label : "Party Name",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Participated Seats",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Seats Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];

		var myConfigsForTehsil = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10 
	    })
	};
				
		myDataTableForParty = new YAHOO.widget.DataTable("zptcPartyTrendsDetailsDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);
		 
		return {
			oDS:resultsDataSourceForTehsil, 
			oDT:myDataTableForParty			
		};  		
	}
	function initializeMptcResultsTableForParty(){
		
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyMptcArray);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
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
	
		var resultsColumnDefsForTehsil = [ {
			key : "partyName",
			label : "Party Name",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Participated Seats",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Seats Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];

		var myConfigsForTehsil = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10 
	    })
	};
				
		myDataTableForMptcParty = new YAHOO.widget.DataTable("mptcPartyTrendsDetailsDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);

		return {
			oDS:resultsDataSourceForTehsil, 
			oDT:myDataTableForMptcParty			
		}; 		
	}
	
	function redirectZptcCandidateLink(){												
		 var browser1 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
		 browser1.focus();
	}

	function redirectMptcCandidateLink(){
		 var browser2 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
		 browser2.focus();
	}
	function buildZptcResults(results){
		assignToPartyDataArray = new Array();
		
		var candLink = document.getElementById("zptcCandidateLink");
		var linkRef = '<a href="javascript:{}" onclick="redirectZptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Candidate Details</a>';
		candLink.innerHTML = linkRef;
		
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyArray=assignToPartyDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	tehsilDetails.partyArray = emptyArr;				
		}
	    initializeResultsTableForParty();
	}

	function buildMptcResults(results){
		assignToPartyDataArray = new Array();

		var candLink = document.getElementById("mptcCandidateLink");
		var linkRef = '<a href="javascript:{}" onclick="redirectMptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Candidate Details</a>';
		candLink.innerHTML = linkRef;
		
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyMptcArray=assignToPartyDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyMptcArray = emptyArr;				
		}
	    initializeMptcResultsTableForParty(); 
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
		for(var i in parliamentResult){
			str += '<div><img src="charts/'+parliamentResult[i].chartPath+'"></div>';
			detailsDIV += '<div><input type="button" class="button" onclick="showDetailedChart(\''+parliamentResult[i].detailedChartPath+'\')" value="Detailed Chart For Paliament"></div>';			
			str += '<div id="parliamentElecResDiv_'+i+'" style="margin-top:20px;">';
			str += '<table id = "parliamentElecResTable_'+i+'">';
			for(var j in parliamentResult[i].constituencyOrMandalWiseElectionVO){
				str += '<tr>';
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+parliamentResult[i].constituencyOrMandalWiseElectionVO[j].locationId+'&MANDAL_NAME='+parliamentResult[i].constituencyOrMandalWiseElectionVO[j].locationName+'">'+parliamentResult[i].constituencyOrMandalWiseElectionVO[j].locationName+'</a></td>';
				for(var k in parliamentResult[i].constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs){
					if(checked == 'number')
						str += '<td>'+parliamentResult[i].constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesEarned+'</td>';
					else
						str += '<td>'+parliamentResult[i].constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesPercentage+'</td>';
				}
				str += '</tr>';
			}
			str += '</table>';
			str += '</div>';
		}
		parliamentDiv.innerHTML = str;
		if(counter!=0){
			details.innerHTML = detailsDIV;
		}
		
		for(var i in parliamentResult){
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
			 

			 for(var j in parliamentResult[i].candidateNamePartyAndStatus){
				var obj1 = {
							key:parliamentResult[i].candidateNamePartyAndStatus[j].party +'['+parliamentResult[i].candidateNamePartyAndStatus[j].rank+']',
							label:parliamentResult[i].candidateNamePartyAndStatus[j].party +'['+parliamentResult[i].candidateNamePartyAndStatus[j].rank+']',
							sortable:true
						}
				var obj2 = {
							key:parliamentResult[i].candidateNamePartyAndStatus[j].party +'['+parliamentResult[i].candidateNamePartyAndStatus[j].rank+']',
							parser:"number"
						}
				myColumnDefs.push(obj1);
				myFields.push(obj2);
			 }

			 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
						.get("parliamentElecResTable_"+i)); 
			 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
			 myDataSource.responseSchema = { 
									            fields:myFields    
									        };
			        
			var villageDataTable = new YAHOO.widget.DataTable("parliamentElecResDiv_"+i,myColumnDefs, myDataSource);
		}
		
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
	
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var str = '';
	str += '<div id="elecResDiv" style="width=900px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';
	for(var i in constituencyResults.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName == 'Others *')
			str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</td>';
		else
		if(constituencyResults.electionType == 'Assembly')
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
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
					str += constituencyResults.missingConstituencies[i].name + ' Assembly,';
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
function hideZptcDiv(){
	var imgElmt = document.getElementById("zptcPartyTrendsDetailsDiv");
	var electionDetails="";
	electionDetails +="<br/>";
	electionDetails +="<b>Zptc Data is not available.</b>";			
	imgElmt.innerHTML = electionDetails;		

	 var candLink = document.getElementById("zptcCandidateLink");
	 var candidateLink="";
	 candLink.innerHTML = candidateLink;
}
function hideMptcDiv(){
	var imgElmt = document.getElementById("mptcPartyTrendsDetailsDiv");
	var electionDetails="";
	electionDetails +="<br/>";
	electionDetails +="<b>Mptc Data is not available.</b>";			
	imgElmt.innerHTML = electionDetails;

	 var candLink = document.getElementById("mptcCandidateLink");
	 var candidateLink="";
	 candLink.innerHTML = candidateLink;
}
function getAllZptcYears()
{	 			
	if(tehsilElections.zptcElectionYears.length!=0){
		var selectDiv = document.getElementById("zptcElectionIdsSelectDiv");
		var electionYearSelect="";
		electionYearSelect+="<b>Select a Election Year :</b>";
		electionYearSelect+='<select class="selectWidth" id="staticGrpSelectBox" name="zptcYears" onchange="getZptcPartyDetails(this.options[this.selectedIndex].value)">';
		for(var i in tehsilElections.zptcElectionYears)
		{
			electionYearSelect+='<option value='+tehsilElections.zptcElectionYears[i].id+'>'+tehsilElections.zptcElectionYears[i].value+'</option>';
		}
		electionYearSelect+='</select>';
		selectDiv.innerHTML = electionYearSelect;
		getZptcPartyDetails(tehsilElections.zptcElectionYears[0].value);
	}
}

function getAllMptcYears()
{
	if(tehsilElections.mptcElectionYears.length!=0){
		var selectDiv = document.getElementById("mptcElectionIdsSelectDiv");
		var electionYearSelect="";
		electionYearSelect+="<b>Select a Election Year :</b>";
		electionYearSelect+='<select class="selectWidth" id="staticGrpSelectBox" name="mptcYears" onchange="getMptcPartyDetails(this.options[this.selectedIndex].value)">';	   

		for(var i in tehsilElections.zptcElectionYears)
		{			   
			electionYearSelect+='<option value='+tehsilElections.mptcElectionYears[i].id+'>'+tehsilElections.mptcElectionYears[i].value+'</option>';
		}
		electionYearSelect+='</select>';
		selectDiv.innerHTML = electionYearSelect;
		getMptcPartyDetails(tehsilElections.mptcElectionYears[0].value);
	}			  		
}

function buildElectionsSelectBox(myResults){
	var selectDiv = document.getElementById("electionIdsSelectDiv");
	var electionYearSelect = '';
	
	if(myResults.length == 0){
		electionYearSelect +='<b>Sorry, Constituency/Mandal Data Not Available For This Constituency</b>';
		var resultDiv = document.getElementById("mandalOrConstiElecResultDiv");
		resultDiv.style.display = "none";
		selectDiv.innerHTML = electionYearSelect; 
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

--></script>
</head>
<body onLoad="getString()">
<div id="detailedChartDIV" class="yui-skin-sam"></div>
<div id="constituencyPageMain">

	<div id="electionResults_Panel_Main" class="yui-skin-sam">
			<div id="electionResults_Panel">
	</div>
	
	<table width="100%">
		<tr>
			<td class="alignTD">
				
				<div id="constituencyPageCenterInfoDiv">
					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>
						<%@ include file="navigator.jsp" %>
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
				

					<div id="constituencyCenterContentOuter" class="rounded"> 						
						<div class="corner topLeft"></div>
						<div class="corner topRight"></div>
						<div class="corner bottomLeft"></div>
						<div class="corner bottomRight"></div>

						<div id="mandalsVotersInfoDiv_Main" class="innerLayoutDivClass">
							<div id="mandalsVotersInfoDiv_Head" class="layoutHeadersClass"></div>
							<div id="mandalsVotersInfoDiv_Body" class="layoutBodyClass yui-skin-sam"></div>
						</div>
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
		<div class="rounded" style="width: 910px;">
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
		
		
		<tr>
			<td colspan="2">
			<div class="rounded" style="width: 910px;">
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
				<table>
						<tr>
							<td style="vertical-align:top;">
								<div id="zptc_main">
									<div id="zptc_head">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td>	
													<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:400px;padding:9px;height:18px;">
														<span>ZPTC Voting Trends : </span>
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
															   </td></tr>
														   <tr>
															   <td> <div id="zptcPartyTrendsDetailsDiv"></div></td>
													</tr></table>
											</td></tr>
										</table>	
									</div>
								</div>
							</td>			
							
							<td style="vertical-align:top;">
								<div id="mptc_main">
									<div id="mptc_head">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
											<tr>
												<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
												<td>	
													<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:400px;padding:9px;height:18px;">
														<span>MPTC Voting Trends : </span>
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
															   </td></tr>
														   <tr>
															   <td> <div id="mptcPartyTrendsDetailsDiv"></div></td>
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
	
	/*	Parliament Candidate Info
		-------------------------
	*/
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
						reportedDate:'${problem.reportedDate}',
						existingFrom:'${problem.existingFrom}',
						name:'${problem.name}',
						postedPersonName:'${problem.postedPersonName}',
						email:'${problem.email}',						
						phone:'${problem.phone}',
						mobile:'${problem.mobile}',
						address:'${problem.address}'
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
	getAllZptcYears();	  
	getAllMptcYears();
	initializeConstituencyPage();
	getConstituencyElections();
	
</script>
</body>
</html>