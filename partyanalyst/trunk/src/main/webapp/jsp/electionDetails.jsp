<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT> 
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script>


<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">


<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/electionResultsPage.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">
<TITLE>${stateName} ${electionType} Election Results Page ${year}</TITLE>
<SCRIPT type="text/javascript">
var electionId = '${electionId}';
var electionType = '${electionType}';
var stateID =  '${stateID}' ;
var stateName = '${stateName}';
var year = '${year}';
var electionResultsObj = {
	partyWiseResultsArr:[],
	allianceResultsArr:[],
	partyWiseResultsWithoutAllianceArr:[],
    districtWiseResultsWithoutAllianceArr:[]
};
	
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String party = rb.getString("party");
		String totalParticipated = rb.getString("totalParticipated"); 
		String seatsWon = rb.getString("seatsWon");
		String seatsLost = rb.getString("seatsLost");
		String votesEarned = rb.getString("votesEarned");
		String percentage  = rb.getString("percentage");
		//String address  = rb.getString("address");
		//String location  = rb.getString("location");
		//String name = rb.getString("name");
		//String email = rb.getString("email");
		//String telephoneNo = rb.getString("telephoneNo");
		//String designation = rb.getString("designation"); 
		
%> }
var partywiseResultsDataTable,allianceResultsDataTable,candidateDetailsDialog;         
function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task == "elctionsBasicInfo")
									{										
										showElectionBasicInfo(myResults);											
									} else if(jsObj.task == "getResultForAnElection")
									{	
										var elmt = document.getElementById("electionPageAjaxImgDiv");
										if(elmt)
											elmt.style.display = 'none';

										showStatewiseResultsBarChart(myResults);									
										showPartywiseDetailsDataTable(myResults);
										showAllianceDetails(myResults);		

										showDistrictWiseResultsLineGraph(myResults);
										buildAllDistrictResultsDataTable(myResults);
										buildAllianceDistrictResultsDataTable(myResults.electionResultsInDistricts);
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


function showDistrictWiseResultsLineGraph(results)
{
	//districtWiseGraph

	var chartName = results.districtWiseElecResultsChartName;
	var districtWiseGraphEl = document.getElementById("districtWiseGraph");

	var contentStr = '';
	contentStr+='<IMG src="charts/'+chartName+'" style="margin-left:10px;"></IMG>';
	districtWiseGraphEl.innerHTML = contentStr;	
}

function getElctionsBasicInfo(electionType){
	var jsObj= 
	{	
			
		electionType: electionType,			
		task: "elctionsBasicInfo"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getResultsForAnElection(stateID,electionType,year)
{
	var jsObj= 
	{
		stateID: stateID,
		electionType: electionType,
		year: year,		
		task:"getResultForAnElection"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}

function showStatewiseResultsBarChart(results)
{
	var chartName = results.statewiseElectionResultsChartName;
	var statewiseGraphEl = document.getElementById("graphImage");

	var contentStr = '';
	contentStr+='<IMG src="charts/'+chartName+'"  border="1" style="border-color:#EFF3F7;"></IMG>';
	statewiseGraphEl.innerHTML = contentStr;	 
}

function showElectionBasicInfo(results)
{
	
	var task1El = document.getElementById("task1");
	var task1Content = '';
	task1Content = '<DIV class="basicDetailsDiv">';
	//task1Content+='<DIV class="basicDetailsHeadingDiv">Overview</DIV>';
	task1Content+='<TABLE class="basicDetailsTable" width="100%">';
	task1Content+='<tr><TH>Total Voters:</TH><TD>'+results.totalVoters+'</TD><TH>Male Voters:</TH><TD>'+results.maleVoters+'</TD><TH>Female Voters:</TH><TD>'+results.femaleVoters+'</TD></TR>';
	task1Content+='<tr><TH>Total Constituencies:</TH><TD>'+results.totalConstituencies+'</TD><TH>Total Polled Votes:</TH><TD>1,05,34343</TD></TR>';
	task1Content+='</TABLE>';
	task1Content+='</DIV>';
	task1El.innerHTML = task1Content;
	
}

function showPartywiseDetailsDataTable(results)
{   
	var partywiseResultsArr = results.electionBasicResultsVO.allPartiesResults;
	var partywiseResultsWithoutAlliance = results.electionBasicResultsVO.allPartiesResultsWithoutGroupingOfAllianc;



	var assignToPartywiseResultsArr = new Array();
	
	for(var i in  partywiseResultsArr){
		var partywiseResultsObj = { 
				party: partywiseResultsArr[i].partyName, 
				totalParticipated: partywiseResultsArr[i].totalConstiParticipated, 
				seatsWon: partywiseResultsArr[i].totalSeatsWon,
				second: partywiseResultsArr[i].secondPosWon,
				third: partywiseResultsArr[i].thirdPosWon,
				fourth: partywiseResultsArr[i].fourthPosWon,
				nth: partywiseResultsArr[i].nthPosWon,
				pc: partywiseResultsArr[i].votesPercentage,
				overall: partywiseResultsArr[i].completeVotesPercent						 
				};
		assignToPartywiseResultsArr.push(partywiseResultsObj);	
	} 
	electionResultsObj.partyWiseResultsArr = assignToPartywiseResultsArr;
	buildPartywiseResultsDataTable("partywiseResultsDataTable",electionResultsObj.partyWiseResultsArr);

	var assignToPartywiseResultsArrWithoutAlliance = new Array();
	
	for(var j in  partywiseResultsWithoutAlliance){
		var partywiseResultsObj = { 
				party: partywiseResultsWithoutAlliance[j].partyName, 
				totalParticipated: partywiseResultsWithoutAlliance[j].totalConstiParticipated, 
				seatsWon: partywiseResultsWithoutAlliance[j].totalSeatsWon,
				second: partywiseResultsWithoutAlliance[j].secondPosWon,
				third: partywiseResultsWithoutAlliance[j].thirdPosWon,
				fourth: partywiseResultsWithoutAlliance[j].fourthPosWon,
				nth: partywiseResultsWithoutAlliance[j].nthPosWon,
				pc: partywiseResultsWithoutAlliance[j].votesPercentage,
				overall: partywiseResultsWithoutAlliance[j].completeVotesPercent						 
				};
		assignToPartywiseResultsArrWithoutAlliance.push(partywiseResultsObj);	
	} 
		electionResultsObj.partyWiseResultsWithoutAllianceArr = assignToPartywiseResultsArrWithoutAlliance;

	var chartpartywiseImgChartElmt = document.getElementById("partywiseResults_img_anc");
	var imgStr = '';
	imgStr+='<div style="margin-top:10px;margin-bottom:10px;">';
	imgStr+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\'partywiseImgChart\',\''+results.statewiseResultsLineChartName+'\',\'View Party Results Line Chart\')">View Party Results Line Charts</a>';
	imgStr+='<a href="javascript:{}" class="viewChartsForResults" onclick="showPartyResultsWithoutAlliance(\''+results.stateLevelLineChartWithoutAllianc+'\')">View Party Results Without Alliance </a></div>';

	chartpartywiseImgChartElmt.innerHTML = imgStr;
}


function showPartyResultsWithoutAlliance(chartId)
{	
	//partywiseResultsWithoutAlliance

	var contentStr ='<div id="withoutAllianceDiv_main">';
	contentStr +='<div id="withoutAllianceDiv_graph"><IMG src="charts/'+chartId+'"></IMG></div>';
	contentStr +='<div id="withoutAllianceDiv_Datatable"></div>';
	contentStr +='</div>';

	 var myPanel = new YAHOO.widget.Panel("partywiseResultsWithoutAlliance", {
                 
                 fixedcenter: true, 
                 constraintoviewport: true, 
                 underlay: "none", 
                 close: true, 
                 visible: true, 
                 draggable: true
       });
	  // myPanel.setHeader("Party Results Without Alliance");
       myPanel.setBody(contentStr);
       myPanel.render();
		
	
		buildPartywiseResultsDataTable("withoutAllianceDiv_Datatable",electionResultsObj.partyWiseResultsWithoutAllianceArr);
}	

function buildPartywiseResultsDataTable(divId,dtSourceArray)
{	
	var partywiseResultsColumnDefs = [
								{key: "party", label: "<%=party%>", sortable:true},		
								//{key: "totalParticipated", label: "TP*", formatter:"number", sortable:true},	
		              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 	
		              	 	    ];                	 	    

		var partywiseResultsDataSource = new YAHOO.util.DataSource(dtSourceArray); 
		partywiseResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		partywiseResultsDataSource.responseSchema = {
                fields: ["party", {key: "totalParticipated", parser:"number"},
                         		  {key: "seatsWon", parser:"number"},
                         		  {key: "second", parser:"number"},
                         		  {key:  "third", parser:"number"},
                         		  {key:  "fourth", parser:"number"},
                         		  {key: "nth", parser:"number"},
                         		  {key: "pc", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber} ] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    }),
			    caption:"Partywise Results" 
				};
		
		partywiseResultsDataTable = new YAHOO.widget.DataTable(divId, partywiseResultsColumnDefs, partywiseResultsDataSource,myConfigs);
					
        return { 
            oDS: partywiseResultsDataSource, 
            oDT: partywiseResultsDataTable 
           
      };	     	
	
}
function showAllianceDetails(results)
{
	var allianceResultsArr = results.electionBasicResultsVO.alliancePartiesList;
	var assignToAllianceResultsArr = new Array();
	var allianceResultsDataTableEl = document.getElementById("allianceResultsDataTable");
	
	for(var i in  allianceResultsArr){
		var header = allianceResultsArr[i].allianceGroupName +"AllianceGraph";
		
		var createDiv = document.createElement("div");		
		createDiv.setAttribute("id","allianceResults_"+i+"_main");		
		createDiv.style.cssText = 'margin-top:32px;';
		var str = '';
		str+='<div id="allianceResults_'+i+'_datatable"></div>';
		str+='<div id="allianceResults_'+i+'_allianceGraph"></div>';
		str+='<div id="allianceResults_'+i+'_footer" style="text-align:left;margin-top:10px;margin-bottom:10px;">';
		str+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\'allianceResults_'+i+'_allianceGraph\',\''+allianceResultsArr[i].chartForPartyResults+'\',\''+header+'\')">View '+allianceResultsArr[i].allianceGroupName+' Alliance Graph<a>';
		str+='</div>';
		createDiv.innerHTML = str;
		allianceResultsDataTableEl.appendChild(createDiv);
	
		buildAllianceResultsDataTable("allianceResults_"+i+"_datatable",allianceResultsArr[i].partiesInAlliance,allianceResultsArr[i].allianceGroupName+" Alliance Details");	
	}		
}


function showAllianceGraph(divId,chartId, chartName)
{
	if(myPanel)
		myPanel.destroy();
	//var contentStr='';
	//contentStr+='<DIV>'+chartName+'</DIV>';
	var contentStr ='<IMG src="charts/'+chartId+'"></IMG>';
	
	var contentStr='<IMG src="charts/'+chartId+'"></IMG>';

	 var myPanel = new YAHOO.widget.Dialog("panel", {
                 
		 width : "820px", 
         fixedcenter : true, 
         visible : true,  
         constraintoviewport : true, 
		  iframe :true,
		  modal :true,
		  hideaftersubmit:true,
		  close:true
		  

       });
	   myPanel.setHeader(chartName);
	   myPanel.setHeader(chartName + " Alliance Graph");
       myPanel.setBody(contentStr);
       myPanel.render();
}
function buildAllianceResultsDataTable(id,dtSource,dtCaption)
{	
	
	var allianceResultsColumnDefs = [
								{key: "partyName", label: "<%=party%>", sortable:true},		
								{key: "totalConstiParticipated", label: "TP*",formatter:"number", sortable:true},	
		              	 	    {key: "totalSeatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "secondPosWon", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "thirdPosWon", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourthPosWon", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nthPosWon", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "votesPercentage", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "completeVotesPercent", label:"Overall %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true}		              	 	 	
		              	 	    ];                	 	    

		var allianceResultsDataSource = new YAHOO.util.DataSource(dtSource); 
		allianceResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allianceResultsDataSource.responseSchema = {
                fields: ["partyName", {key: "totalConstiParticipated", parser:"number"},
                					  {key:  "totalSeatsWon", parser:"number"},
                					  {key:  "secondPosWon", parser:"number"},
                					  {key:  "thirdPosWon", parser:"number"},
                					  {key:  "fourthPosWon", parser:"number"},
                					  {key: "nthPosWon", parser:"number"},
                					  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                					  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    }),
			    caption:dtCaption
				};
		
		allianceResultsDataTable = new YAHOO.widget.DataTable(id, allianceResultsColumnDefs, allianceResultsDataSource,{caption:dtCaption});
					
        return { 
            oDS: allianceResultsDataSource, 
            oDT: allianceResultsDataTable            
      };	
	
}
function showCandidateDetailsWindow(stateName,electionType,year)
{
	var urlStr = "<%=request.getContextPath()%>/candidateDetailsForElectionDetailsReportAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&year=${year}";
	var browser1 = window.open(urlStr,"browser1","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser1.focus();	
}


function buildAllDistrictDatatable(innerObj,divID)
{
	
	var dtSource = new Array();
	
	for(var i in innerObj)
	{
		for(var j in innerObj[i].partyResultsInDistricts)
		{
			var obj = {
						district:innerObj[i].partyResultsInDistricts[j].districtName,
						party:innerObj[i].partyName,
						seatsWon:innerObj[i].partyResultsInDistricts[j].seatsWon,
						second:innerObj[i].partyResultsInDistricts[j].secondPos,
						third:innerObj[i].partyResultsInDistricts[j].thirdPos,
						fourth:innerObj[i].partyResultsInDistricts[j].fourthPos,
						nth:innerObj[i].partyResultsInDistricts[j].nthPos,
						pc:innerObj[i].partyResultsInDistricts[j].votesPercentage,
						overall:innerObj[i].partyResultsInDistricts[j].completeVotesPercent
					  }
			dtSource.push(obj);
		}
	}

	

	var allDistrictResultsColumnDefs = [
								{key: "district", label: "District", sortable:true},		
								{key: "party", label: "<%=party%>", sortable:true},										
		              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 	
		              	 	 			              	 	 	
		              	 	    ];                	 	    

		var allDistrictResultsDataSource = new YAHOO.util.DataSource(dtSource); 
		allDistrictResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allDistrictResultsDataSource.responseSchema = {
                fields: ["district","party", {key: "totalParticipated", parser:"number"},
                         		  {key: "seatsWon", parser:"number"},
                         		  {key: "second", parser:"number"},
                         		  {key:  "third", parser:"number"},
                         		  {key:  "fourth", parser:"number"},
                         		  {key: "nth", parser:"number"},
                         		  {key: "pc", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber} ] 
                					   
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 23			        
			    }),
			    caption:"Districts Wise Election Results"
				};
		
		var allDistrictResultsDataTable = new YAHOO.widget.DataTable(divID, allDistrictResultsColumnDefs, allDistrictResultsDataSource,myConfigs);
					
        return { 
            oDS: allDistrictResultsDataSource, 
            oDT: allDistrictResultsDataTable            
      };	
}

function buildAllDistrictResultsDataTable(results)
{	
	//districtResults_withoutAllianceDiv
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	electionResultsObj.districtWiseResultsWithoutAllianceArr = results.electionResultsInDistricts.allPartiesResultsWithoutGroupingOfAllianc;

	buildAllDistrictDatatable(innerObj,"districtResults");

	var elmt = document.getElementById("districtResults_withoutAllianceDiv");
	var str = '<div style="margin-top:10px;margin-bottom:10px;">';
	str += '<a href="javascript:{}" class="viewChartsForResults" onclick="showDistrictWisePartyResultsWithoutAlliance(\''+results.partyResultsDistrictLevelChartWithoutAllianc+'\')">';
	str += 'View Party Results Without Alliance';
	str += '</a></div>';
	str += '<div id="districtWiseWithoutAlliancePopupDiv"></div>';
	elmt.innerHTML = str;
}

function showDistrictWisePartyResultsWithoutAlliance(chartId)
{
	//partywiseResultsWithoutAlliance

	var contentStr ='<div id="districtWiseWithoutAllianceDiv_main">';
	contentStr +='<div id="districtWiseWithoutAllianceDiv_graph"><IMG src="charts/'+chartId+'"></IMG></div>';
	contentStr +='<div id="districtWiseWithoutAllianceDiv_Datatable"></div>';
	contentStr +='</div>';

	 var myPanel = new YAHOO.widget.Panel("districtWiseWithoutAlliancePopupDiv", {
                 
                 fixedcenter: true, 
                 constraintoviewport: true, 
                 underlay: "none", 
                 close: true, 
                 visible: true, 
                 draggable: true
       });
	  // myPanel.setHeader("District Wise Party Results Without Alliance");
       myPanel.setBody(contentStr);
		
	   myPanel.render();
		
	
	   buildAllDistrictDatatable(electionResultsObj.districtWiseResultsWithoutAllianceArr,"districtWiseWithoutAllianceDiv_Datatable");
}	

function buildAllianceDistrictResultsDataTable(results)
{	
	var parentElmt = document.getElementById("allianceDistResults");

	var innerObj = results.alliancePartiesList;
	for(var i in innerObj)
	{	
		var header = innerObj[i].allianceGroupName + "Alliance Graph";	
		var childElmt = document.createElement("div");
		childElmt.setAttribute('id','allianceChildDiv'+i);
		
		var str = '';
		str+='<div id="allianceResults_district_'+i+'_datatable"></div>';
		str+='<div id="allianceResults_district_'+i+'_allianceGraph"></div>';
		str+='<div id="allianceResults_district_'+i+'_footer" style="margin-top:10px;margin-bottom:10px;">';
		str+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\'allianceResults_district_'+i+'_allianceGraph\',\''+innerObj[i].alliancePartiesChart+'\',\''+innerObj[i].allianceGroupName+'\')">View '+header+' Alliance Graph<a>';
		str+='</div>';
		childElmt.innerHTML = str;

		parentElmt.appendChild(childElmt);
		
		var dtSourceArr = new Array();

		for(var j in innerObj[i].partiesInAlliance)
		{
			for(var k in innerObj[i].partiesInAlliance[j].partyResultsInDistricts)
			{
				var obj = {
							district:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].districtName,
							party:innerObj[i].partiesInAlliance[j].partyName,
							seatsWon:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].seatsWon,
							second:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].secondPos,
							third:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].thirdPos,
							fourth:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].fourthPos,
							nth:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].nthPos,
							pc:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].votesPercentage,
							overall:innerObj[i].partiesInAlliance[j].partyResultsInDistricts[k].completeVotesPercent
						  }
				dtSourceArr.push(obj);
			}
		}
		
		var allianceDistrictResultsColumnDefs = [
								{key: "district", label: "District", sortable:true},		
								{key: "party", label: "<%=party%>", sortable:true},										
		              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 	
		              	 	 			              	 	 	
		              	 	    ];                	 	    

		var allianceDistrictResultsDataSource = new YAHOO.util.DataSource(dtSourceArr); 
		allianceDistrictResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allianceDistrictResultsDataSource.responseSchema = {
                fields: ["district","party", {key: "totalParticipated", parser:"number"},
                         		  {key: "seatsWon", parser:"number"},
                         		  {key: "second", parser:"number"},
                         		  {key:  "third", parser:"number"},
                         		  {key:  "fourth", parser:"number"},
                         		  {key: "nth", parser:"number"},
                         		  {key: "pc", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber} ] 
                					   
        		};
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({rowsPerPage    : 10}) ,
				caption:innerObj[i].allianceGroupName+" Alliance Details"
				};
		
		var allianceDistrictResultsDataTable = new YAHOO.widget.DataTable('allianceResults_district_'+i+'_datatable', allianceDistrictResultsColumnDefs, allianceDistrictResultsDataSource,myConfigs);
					
       	

	}

		
}
function openPreYearDistAnalysisWindow()
{
	var selectYearEl = document.getElementById("selectYearDistrictwise");
	var selectedElectionYear =  selectYearEl.options[selectYearEl.selectedIndex].text;
	alert(selectedElectionYear);
	var urlStr = "<%=request.getContextPath()%>/districtwiseElectionResultsAnalysysForElectionReportAction.action?stateID=${stateID}&stateName=${stateName}&electionType=${electionType}&currentElectionyear=${year}&selectedElectionYear=${selectedElectionYear}";
	var browser1 = window.open(urlStr,"browser1","scrollbars=yes,height=600,width=1200,left=200,top=200");
	
	browser1.focus();
}

</SCRIPT>
</HEAD>
<BODY>
<TABLE cellspacing="0" cellpadding="0" border="0" >
<TR>
<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" /></TD><TD valign="top"><DIV class="mainHeading">${stateName} ${electionType} Election Results ${year}</DIV></TD><TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" border="none"/></TD>
</TR>
</TABLE>
<div id="electionPageAjaxImgDiv">
	<div> Loading Election Results Please Wait..</div>
	<img src="images/icons/barloader.gif"/>
</div>
<DIV id="task1"></DIV>
<DIV class="graphTop">State Level Overview</DIV>
<DIV id="statewiseGraph">
<DIV id="graphImage"></DIV>
<DIV class="yui-skin-sam" style="width:880px;">
	<TABLE border="0" width="95%" >
		<TR>
			<TD valign="top" align="left">
				<DIV id="partywiseResultsDataTable_main">
					<div id="partywiseResultsDataTable"></div>					
					<div id="partywiseImgChart"></div>
					<div id="partywiseResults_img_anc"></div>
					<div id="partywiseResultsWithoutAlliance"></div>
				</DIV>
			</TD>
			<c:if test="${electionType == 'Assembly'}"> 
			<TD valign="top"><DIV id="allianceResultsDataTable"></DIV></TD>
			</c:if>
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>		
	</TABLE>
</DIV>
<DIV style="padding:10px;text-align:right;"><A href="javascript:{}" class="viewChartsForResults" onclick="showCandidateDetailsWindow(stateName,electionType,year)">View Candidates Results</A></DIV>
</DIV>
<DIV id="viewCandidate" class="yui-skin-sam"></DIV>
<DIV class="graphBottom"></DIV>
<DIV class="graphTop">District Level Overview</DIV>
<DIV id="distwiseGraph">
<div id="districtWiseGraph"></div>
<DIV class="yui-skin-sam">
	<TABLE border="0" width="95%" >
		<TR>
			<TD valign="top" align="left">
				<DIV id="districtResults"></DIV>
				<DIV id="districtResults_withoutAllianceDiv"></DIV>
			</TD>
			
			<TD valign="top"><DIV id="allianceDistResults"></DIV></TD>
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#909090;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>		
	</TABLE>
</DIV>
</DIV>
<DIV class="graphBottom"></DIV>
<DIV class="graphTop">Analysis Tools</DIV>
<DIV id="toolsDiv" align="left">
	<TABLE class="toolsTable"><TR>
		<TD class="td">
			<DIV id="viewPPR" class="toolsDisplay">
					<h3>District wise Analysis</h3>
					<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can compare previous elections results district wise.</P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Election Type:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">Assembly</OPTION><OPTION value="1">Parliament</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectYearDistrictwise" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" onclick="openPreYearDistAnalysisWindow()" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>				
			</DIV>
		</TD>
		<TD class="td">
			<DIV id="viewPPR" class="toolsDisplay">
				<h3>Compare Votes% & Seats %</h3>
				<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can compare previous elections by votes % and seats %.</P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Election Type:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">Assembly</OPTION><OPTION value="1">Parliament</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>
			</DIV>	
		</TD>
		<TD class="td">
			<DIV class="toolsDisplay">
				<h3>Party Performance</h3>
				<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can analysis a party's performance for an election in previous years. </P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Party Name:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">TDP</OPTION><OPTION value="1">BJP</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>
			</DIV>
		</TD>
		<TD class="td">
			<DIV id="comparePrevElection" class="toolsDisplay">
				<h3>Elections Comparison</h3>
				<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can compare current election results with previous election results</P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Election Type:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">Assembly</OPTION><OPTION value="1">Parliament</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>
			</DIV>
		</TD></TR>
	</TABLE>
</DIV>
<DIV class="graphBottom"></DIV>
<DIV class = "yui-skin-sam"><div id="panel"></DIV></DIV>
<DIV id="task10"></DIV>

<SCRIPT type="text/javascript">
//getElctionsBasicInfo(electionType);
getResultsForAnElection(stateID,electionType,year);

</SCRIPT>
</BODY>

</HTML>