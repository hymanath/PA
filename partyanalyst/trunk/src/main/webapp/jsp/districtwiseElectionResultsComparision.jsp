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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/electionResultsPage.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
<c:if test="${electionType != 'Parliament'}"><TITLE>${stateName} ${electionType} Election Results Page ${year}</TITLE></c:if>
<c:if test="${electionType == 'Parliament'}"><TITLE>${electionType} Election ${year} Results Page </TITLE></c:if>
</HEAD>
<SCRIPT type="text/javascript">
var stateId = '${stateID}';
var electionType = '${electionType}';
var selectedYear = '${selectedElectionYear}';
google.load("visualization", "1", {packages:["corechart"]});
var electionResultsObj = {
		partyWiseResultsArr:[],
		allianceResultsArr:[],
		partyWiseResultsWithoutAllianceArr:[],
	    districtWiseResultsWithoutAllianceArr:[],
	    allianceGroupNamesArray:[]
	};
var resultsGlobal;		
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getSelectedYearElectionResults")
								{										
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									showDistrictWiseResultsLineGraph(myResults);									
									buildAllianceDistrictResultsDataTable(myResults.electionResultsInDistricts);
									buildAllDistrictResultsDataTable(myResults);
																					
								}   									
							}
						catch (e) {   
						   	("Invalid JSON result" + e);   
					}  
	               },
	               scope : this,
	               failure : function( o ) {
	                			//alert( "Failed to load result" + o.status + " " + o.statusText);
	                         }
	               };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function getSelectedYearElectionResults(stateId,electionType,selectedYear)
{
	var jsObj= 
	{
		stateID: stateId,
		electionType: electionType,
		year: selectedYear,		
		task: "getSelectedYearElectionResults"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/districtwiseElectionResultsAnalysysForElectionReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
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

function showDistrictWiseResultsLineGraph(results)
{
	/*var chartName = results.districtWiseElecResultsChartName;
	var districtWiseGraphEl = document.getElementById("districtWiseGraph");

	var contentStr = '';
	contentStr+='<IMG src="charts/'+chartName+'" style="margin-left:10px;"></IMG>';
	districtWiseGraphEl.innerHTML = contentStr;	*/

	getDistrictResultsInteractiveChartVotesPercent(results,null);
	getDistrictResultsInteractiveChartSeatsWon(results,null);
}

function getDistrictResultsInteractiveChartSeatsWon(results,partyN)
 {
	 var districtWiseGraphEl = document.getElementById("districtWiseSeatsGraph");
	 var chartColumns = results.partiesDistLevel;
	 var chartRows = results.partyResultsforDistWiseChart;
		
	 var data = new google.visualization.DataTable();
	 var partiesArray = new Array();
	
	 data.addColumn('string', 'Party');
	  var partysCount = 0;
      //for columns
	  for(var i in chartColumns){
		  if(partysCount > 15)
		  {
			  break;
		  }
		   if(partyN != null)
		  {
            data.addColumn('number', partyN);
			partiesArray.push(partyN);
			break;
		  }
	    data.addColumn('number', chartColumns[i].name);
		partiesArray.push(chartColumns[i].name);
		partysCount++;
	  }

      for(var j in chartRows)
	  {
		  
		var partyCount = 0;
        var array = new Array();
		array.push(chartRows[j].partyName);

		for(var k in chartRows[j].partyResultsInDistricts)
		{
		   if(partyN == null && partyCount > 15)
		  {
			  break;
		  }

		  if(partyN != null)
		  {
			  if(chartRows[j].partyResultsInDistricts[k].districtName == partyN)
			  {
				   var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
				   array.push(seatsWon);
				   partyCount++;

			  }
            
		  }
		  else
		  {
			  var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
			  array.push(seatsWon);
			  partyCount++;
		  }
		}

        data.addRow(array);
		
	  }
		 
      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

	  var ctitle='';
      if(partyN != null) 
	     ctitle = partyN+' District Wise Election Results By Seats Won'; 
	  else
         ctitle = 'All Parties District Wise Election Results By Seats Won';

	  if(staticColors != null && staticColors.length > 0)
	  {
        new google.visualization.LineChart(districtWiseGraphEl).
		  draw(data, {curveType: "function",width: 870, height: 550,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
		  });
	  }
	  else
	  {
	  new google.visualization.LineChart(districtWiseGraphEl).
		  draw(data, {curveType: "function",width: 870, height: 550,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
		  });
	  }
			
 }

 function getDistrictResultsInteractiveChartVotesPercent(results,partyN)
 {
	 var districtWiseGraphEl = document.getElementById("districtWiseGraph");
	 var chartColumns = results.partiesDistLevel;
	 var chartRows = results.partyResultsforDistWiseChart;
		
	 var data = new google.visualization.DataTable();
	 var partiesArray = new Array();
	
	 data.addColumn('string', 'Party');
	 var partysCount = 0;
      //for columns
	  for(var i in chartColumns){
		  if(partysCount > 15)
		  {
			  break;
		  }
		  if(partyN != null)
		  {
            data.addColumn('number', partyN);
			partiesArray.push(partyN);
			break;
		  }
	    data.addColumn('number', chartColumns[i].name);
		partiesArray.push(chartColumns[i].name);
		partysCount++;
	  }

     
	  for(var j in chartRows)
	  {
		  
		var partyCount = 0;
        var array = new Array();
		array.push(chartRows[j].partyName);

		for(var k in chartRows[j].partyResultsInDistricts)
		{
		  if(partyN == null && partyCount > 15)
		  {
			  break;
		  }

		  if(partyN != null)
		  {
			  if(chartRows[j].partyResultsInDistricts[k].districtName == partyN)
			  {
				   var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
				   array.push(seatsWon);
				   partyCount++;

			  }
            
		  }
		  else
		  {
			  var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
			  array.push(seatsWon);

			   partyCount++;
		  }
		}

        data.addRow(array);
       
	  }
		 
    
	 var ctitle='';
      if(partyN != null) 
	     ctitle = partyN+' District Wise Election Results By Votes Percentage'; 
	  else
         ctitle = 'All Parties District Wise Election Results By Votes Percentage'; 

	   //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

	  if(staticColors != null && staticColors.length > 0)
	  {
		   new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {curveType: "function",width: 870, height: 550,title:ctitle,colors:staticColors,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }
	  else
	  {
		  new google.visualization.LineChart(districtWiseGraphEl).
			  draw(data, {curveType: "function",width: 870, height: 550,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
			  });
	  }

 }

 function getInteractiveChartBySeatsWonForADistrict(results,districtN)
 {
	 
	 var districtWiseGraphEl = document.getElementById("districtWiseSeatsGraph");
	 var chartRows = results.electionResultsInDistricts.allPartiesResults;
		
	 var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'District');
	 data.addColumn('number', districtN);
	  
      var partyCount = 0;
      for(var j in chartRows)
	  {
		  
		if(partyCount > 12)
		{
			break;
		}
        var array = new Array();
		array.push(chartRows[j].partyName);
		var distFlag = false;

		for(var k in chartRows[j].partyResultsInDistricts)
		{
			
		     if(chartRows[j].partyResultsInDistricts[k].districtName == districtN)
			 {
				 var seatsWon = chartRows[j].partyResultsInDistricts[k].seatsWon;
				 array.push(seatsWon);
				 partyCount++;
				 distFlag = true;
				 break;
			 }
		  
          
		}

		if(distFlag == false)
		{
          array.push(0);
		  partyCount++;
		}


        data.addRow(array);
		
	  }
		 
	  var ctitle='';
      if(districtN != null) 
	     ctitle = districtN+' District Election Results By Seats Won'; 
	  else
         ctitle = 'All Parties District Wise Election Results By Seats Won';
	  new google.visualization.LineChart(districtWiseGraphEl).
	  draw(data, {curveType: "function",width: 870, height: 550,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
      });
		
 }

 function getInteractiveChartByVotesPercentForADistrict(results,districtN)
 {
	 
	 var districtWiseGraphEl = document.getElementById("districtWiseGraph");
	 var chartRows = results.electionResultsInDistricts.allPartiesResults;
		
	 var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'District');
	 data.addColumn('number', districtN);
	  
      var partyCount = 0;
      for(var j in chartRows)
	  {
		  
		if(partyCount > 12)
		{
			break;
		}
        var array = new Array();
		array.push(chartRows[j].partyName);
		var distFlag = false;

		for(var k in chartRows[j].partyResultsInDistricts)
		{
			
		     if(chartRows[j].partyResultsInDistricts[k].districtName == districtN)
			 {
				 var seatsWon = chartRows[j].partyResultsInDistricts[k].completeVotesPercentDouble;
				 array.push(seatsWon);
				 partyCount++;
				 distFlag = true;
				 break;
			 }
		  
          
		}

		if(distFlag == false)
		{
          array.push(0);
		  partyCount++;
		}


        data.addRow(array);
		
	  }
		 
	  var ctitle='';
      if(districtN != null) 
	     ctitle = districtN+' District Election Results By Votes Percent'; 
	  else
         ctitle = 'All Parties District Wise Election Results By Votes Percent';
	  new google.visualization.LineChart(districtWiseGraphEl).
	  draw(data, {curveType: "function",width: 870, height: 550,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
      });
		
 }

function buildAllDistrictDatatable(innerObj,divID,type,partyName,districtName)
{
	
	var selectPartyName = partyName;
	var districtName = districtName;
	var dtSource = new Array();
	if(type == "all")
	{
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
				if(electionResultsObj.allianceGroupNamesArray.length > 0 )
					for(k in electionResultsObj.allianceGroupNamesArray)
					{
						if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
						{
							obj.pc = '';
						}
					}
				dtSource.push(obj);
			}
		}
	}

	if(type == "party")
	{
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
				if(selectPartyName == innerObj[i].partyName)
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
					if(electionResultsObj.allianceGroupNamesArray.length > 0 )
						for(k in electionResultsObj.allianceGroupNamesArray)
						{
							if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
							{
								obj.pc = '';
							}
						}
					dtSource.push(obj);
				}	
			}
		}
	}

	if(type == "district")
	{
		for(var i in innerObj)
		{
			for(var j in innerObj[i].partyResultsInDistricts)
			{
				if(districtName == innerObj[i].partyResultsInDistricts[j].districtName)
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
					if(electionResultsObj.allianceGroupNamesArray.length > 0 )
						for(k in electionResultsObj.allianceGroupNamesArray)
						{
							if(electionResultsObj.allianceGroupNamesArray[k] == innerObj[i].partyName)
							{
								obj.pc = '';
							}
						}
					dtSource.push(obj);
				}	
			}
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
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
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
                         		  {key: "pc",parser:YAHOO.util.DataSourceBase.parseNumber},
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
	resultsGlobal = results;
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var districtsList = results.partiDistList;
	var stateSelectBox = document.getElementById("stateSelectBox");
	var stateList = results.partiDistList;
	var participatedPartiesList = results.partiPartiesList;
	var distSelectElmt = document.getElementById("distSelectBox");
	var partySelectElmt = document.getElementById("partySelectBox");
	
	for(var i in participatedPartiesList)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=participatedPartiesList[i].id;
		opElmt.text=participatedPartiesList[i].name;
	
		try
			{
				partySelectElmt.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
				partySelectElmt.add(opElmt); // IE only
			}			
	}

	for(var j in districtsList)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=districtsList[j].id;
		opElmt.text=districtsList[j].name;
	
		try
			{	if(distSelectElmt)
					distSelectElmt.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			if(distSelectElmt)
				distSelectElmt.add(opElmt); // IE only
			}			
	}
	for(var d in stateList)
	{		
		var opElmt=document.createElement('option');
		opElmt.value=stateList[d].id;
		opElmt.text=stateList[d].name;
	
		try
			{	
				if(stateSelectBox)
					stateSelectBox.add(opElmt,null); // standards compliant					
			}
		catch(ex)
			{
				if(stateSelectBox)
					stateSelectBox.add(opElmt); // IE only
			}			
	}
	electionResultsObj.districtWiseResultsWithoutAllianceArr = results.electionResultsInDistricts.allPartiesResultsWithoutGroupingOfAllianc;	
	buildAllDistrictDatatable(innerObj,"districtResults","all","null","null");

	var elmt = document.getElementById("districtResults_withoutAllianceDiv");
	var str = '';
	if(electionResultsObj.allianceGroupNamesArray.length > 0 )
	{
		str = '<div style="margin-top:10px;margin-bottom:10px;">';
		str += '<a href="javascript:{}" class="viewChartsForResults" onclick="showDistrictWisePartyResultsWithoutAlliance(\''+results.partyResultsDistrictLevelChartWithoutAllianc+'\')">';
		str += 'View Party Results Without Alliance';
		str += '</a></div>';
	}	
	str += '<div id="districtWiseWithoutAlliancePopupDiv"></div>';
	elmt.innerHTML = str;
}
function showDistrictWisePartyResultsWithoutAlliance(chartId)
{
	//partywiseResultsWithoutAlliance

	var contentStr ='<div id="districtWiseWithoutAllianceDiv_main" style="height:500px;overflow-y:auto">';
	contentStr +='<div id="districtWiseWithoutAllianceDiv_graph"><IMG src="charts/'+chartId+'"></IMG></div>';
	contentStr +='<div id="districtWiseWithoutAllianceDiv_Datatable"></div>';
	contentStr +='</div>';

	 var myPanel = new YAHOO.widget.Panel("panel", {
                 
		 width : "950px", 
         fixedcenter : true, 
         visible : true,  
         constraintoviewport : true, 
		  iframe :true,
		  modal :true,
		  hideaftersubmit:true,
		  close:true
       });
	   myPanel.setHeader("District Wise Party Results Without Alliance");
       myPanel.setBody(contentStr);
		
	   myPanel.render();
		
	
	   buildAllDistrictDatatable(electionResultsObj.districtWiseResultsWithoutAllianceArr,"districtWiseWithoutAllianceDiv_Datatable","all","null","null");
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
		str+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\'allianceResults_district_'+i+'_allianceGraph\',\''+innerObj[i].alliancePartiesChart+'\',\''+innerObj[i].allianceGroupName+'\')">View '+header+'<a>';
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
				electionResultsObj.allianceGroupNamesArray.push(innerObj[i].allianceGroupName);
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
function allDistResultsRadioClickHandler(results)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	var selectBoxEl = document.getElementsByName("selectBox");
	for (i=0; i< selectBoxEl.length; i++)
	{
		if(selectBoxEl[i].style.display == "block")			
		{
			selectBoxEl[i].style.display = 'none';}
	}

	getDistrictResultsInteractiveChartVotesPercent(results,null);
	getDistrictResultsInteractiveChartSeatsWon(results,null);

	buildAllDistrictDatatable(innerObj,"districtResults","all","null","null");
}
function partywiseRadioClickHandler()
{
	var partySelectBoxEl = document.getElementById("partySelectBox");
	var distSelectBoxEl = document.getElementById("distSelectBox");
	if(partySelectBoxEl.style.display == "none")			
	{
		partySelectBoxEl.style.display = 'block';
		partySelectBoxEl.selectedIndex = '0';
	}
	if(distSelectBoxEl)
	{	
		if(distSelectBoxEl.style.display == "block")			
		{
			distSelectBoxEl.style.display = 'none';
		}
	}	 
}
function districtwiseRadioClickHandler()
{
	var partySelectBoxEl = document.getElementById("partySelectBox");
	var distSelectBoxEl = document.getElementById("distSelectBox");
	if(partySelectBoxEl.style.display == "block")			
	{
		partySelectBoxEl.style.display = 'none';
	}
	if(distSelectBoxEl.style.display == "none")			
	{
		distSelectBoxEl.style.display = 'block';
		distSelectBoxEl.selectedIndex = '0';
	}		 
	
}
function updateDistResultsPartywise(partyName,results)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	if(partyName != 'Select Party')
	{	
		getDistrictResultsInteractiveChartVotesPercent(results,partyName);
	    getDistrictResultsInteractiveChartSeatsWon(results,partyName);
		buildAllDistrictDatatable(innerObj,"districtResults","party",partyName,"null");
	}else return;	
}

function updateDistResultsDistwise(distName,results)
{
	
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	if(distName != 'Select District')
	{	
		getInteractiveChartBySeatsWonForADistrict(results,distName);
		getInteractiveChartByVotesPercentForADistrict(results,distName);
		buildAllDistrictDatatable(innerObj,"districtResults","district","null",distName);
	}else return;	
}
function  statewiseRadioClickHandler()
{
	var stateSelectBoxEl = document.getElementById("stateSelectBox");
	stateSelectBoxEl.style.display = 'block';

	var partySelectBoxEl = document.getElementById("partySelectBox");
	partySelectBoxEl.style.display = 'none';
	
}
function updateResultsStatewise(distName,results)
{
	var innerObj = results.electionResultsInDistricts.allPartiesResults;
	if(distName != 'Select State' )
		{
		getInteractiveChartBySeatsWonForADistrict(results,distName);
		getInteractiveChartByVotesPercentForADistrict(results,distName);
		buildAllDistrictDatatable(innerObj,"districtResults","district","null",distName);
		}
		else return;	
}

</SCRIPT>
<BODY>
<CENTER>
<TABLE cellspacing="0" cellpadding="0" border="0" >
<TR>
<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" style="margin-top:15px;"/></TD><TD valign="top">
<c:if test="${electionType != 'Parliament'}"><DIV class="mainHeading">${stateName} ${electionType} Election Results ${selectedYear}</DIV></c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="mainHeading">${selectedYear} ${electionType} Election Results </DIV></c:if></TD><TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" style="margin-top:15px;" border="none"/>
</TD>
</TR>
</TABLE>
<DIV id="electionPageAjaxImgDiv">
	<DIV> Loading Election Results Please Wait..</DIV>
	<IMG src="images/icons/barloader.gif"/>
</DIV>
<c:if test="${electionType != 'Parliament'}"><DIV class="graphTop">District Level Overview</DIV></c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="graphTop">Country Level Overview</DIV></c:if>
<DIV id="distwiseGraph">
<DIV id="districtWiseGraph"></DIV>
<DIV id="districtWiseSeatsGraph"></DIV>
<DIV id="distResultsViewOptionsDiv">

	<TABLE width="100%">		
		<TR>
			<TD style="width:10%;"><INPUT type="radio" name="distResultsOption" id="allDistResultsRadio" value="all" onClick="allDistResultsRadioClickHandler(resultsGlobal)" checked="true"/>All</TD>
			<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="partywiseRadio" value="partywise" onClick="partywiseRadioClickHandler()"/>Partywise</TD>
			<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="partySelectBox"  name="selectBox"  onchange="updateDistResultsPartywise(this.options[this.selectedIndex].text,resultsGlobal)" style="display:none;">';
				<OPTION id="0" >Select Party</OPTION>
				</SELECT>
			</TD>
				<c:if test="${electionType != 'Parliament'}">
					<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="districtwiseRadio" value="districtwise" onClick="districtwiseRadioClickHandler()"/>Districtwise</TD>
					<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="distSelectBox"  name="selectBox"  onchange="updateDistResultsDistwise(this.options[this.selectedIndex].text,resultsGlobal)" style="display:none;">';
							<OPTION id="0" >Select District</OPTION>					
						</SELECT>				
					</TD>
				</c:if>
				<c:if test="${electionType == 'Parliament'}">
					<TD style="width:20%;"><INPUT type="radio" name="distResultsOption" id="statewiseRadio" value="statewise" onClick="statewiseRadioClickHandler()"/>Statewise</TD>
					<TD style="width:25%;" align="left"><SELECT class="selectBoxStyle" id="stateSelectBox"  name="selectBox"  onchange="updateResultsStatewise(this.options[this.selectedIndex].text,resultsGlobal)" style="display:none;">';
							<OPTION id="0" >Select State</OPTION>					
						</SELECT>				
					</TD>
				</c:if>				
		</TR>
	</TABLE>	
</DIV>

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
		<TR>
			<TD colspan="2" align="left"><DIV id="note" name="note"><P><FONT style="font-weight:bold;color:red;" >Note:</FONT>&nbsp;PC% column is empty for alliance parties in Partywise Results table, to find PC% for alliance parties kindly refer to the PC% column of the Alliance Details Table.PC% is Not Applicable for Independent Candidates(IND).</P></DIV></TD>
		</TR>		
	</TABLE>
	
</DIV>
</DIV>
<DIV class="graphBottom"></DIV>
<DIV class = "yui-skin-sam"><div id="panel"></DIV></DIV>
</CENTER>
<SCRIPT type="text/javascript">
getSelectedYearElectionResults(stateId,electionType,selectedYear);
//buildAllDistrictResultsDataTable();
//buildAllianceDistrictResultsDataTable();
</SCRIPT>
</BODY>
</HTML>