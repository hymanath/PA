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

<SCRIPT type="text/javascript">
var stateId = '${stateID}';
var electionType = '${electionType}';
var selectedYear = '${selectedElectionYear}';
var allianceResults;
var electionResultsObj = {
		partyWiseResultsArr:[],
		allianceResultsArr:[],
		partyWiseResultsWithoutAllianceArr:[],
	    districtWiseResultsWithoutAllianceArr:[],
	    allianceGroupNamesArray:[]
	};
google.load("visualization", "1", {packages:["corechart"]});
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
								if(jsObj.task == "getSelectedYearElectionResults")
								{										
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									showAllianceDetails(myResults);
									showStatewiseResultsBarChart(myResults);									
									showPartywiseDetailsDataTable(myResults);
																					
								}   									
							}
						catch (e) {   
						   	alert("Invalid JSON result" + e);   
					}  
	               },
	               scope : this,
	               failure : function( o ) {
	                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
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
	var url = "<%=request.getContextPath()%>/statewiseElectionResultsComparisionToolAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function showAllianceDetails(results)
{
	var allianceResultsArr = results.electionBasicResultsVO.alliancePartiesList;
	allianceResults = allianceResultsArr;
	var assignToAllianceResultsArr = new Array();
	var allianceResultsDataTableEl = document.getElementById("allianceResultsDataTable");
	var allianceGrpName;
	
	for(var i in  allianceResultsArr){
		var header = allianceResultsArr[i].allianceGroupName +"AllianceGraph";
		allianceGrpName =  allianceResultsArr[i].allianceGroupName;
		electionResultsObj.allianceGroupNamesArray.push(allianceGrpName);				
		var createDiv = document.createElement("div");		
		createDiv.setAttribute("id","allianceResults_"+i+"_main");		
		createDiv.style.cssText = 'margin-top:32px;';
		var str = '';
		str+='<div id="allianceResults_'+i+'_datatable"></div>';
		str+='<div id="allianceResults_'+i+'_allianceGraph"></div>';
		str+='<div id="allianceResults_'+i+'_footer" style="text-align:left;margin-top:10px;margin-bottom:10px;">';
		str+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph('+i+',\''+header+'\')">View '+allianceResultsArr[i].allianceGroupName+' Alliance Graph<a>';
		str+='</div>';
		createDiv.innerHTML = str;
		allianceResultsDataTableEl.appendChild(createDiv);
	
		buildAllianceResultsDataTable("allianceResults_"+i+"_datatable",allianceResultsArr[i].partiesInAlliance,allianceResultsArr[i].allianceGroupName+" Alliance Details");
			
	}			
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
function showStatewiseResultsBarChart(results)
{
	/*var chartName = results.statewiseElectionResultsChartName;
	var statewiseGraphEl = document.getElementById("graphImage");

	var contentStr = '';
	contentStr+='<IMG src="charts/'+chartName+'"  border="1" style="border-color:#EFF3F7;"></IMG>';
	statewiseGraphEl.innerHTML = contentStr;*/	
	
	var ctitle = 'Party Results In ' +results.electionBasicResultsVO.electionType+ ' ' +results.electionBasicResultsVO.electionYear+''; 
	var electionType = results.electionBasicResultsVO.electionType;
	var allPartyRes = results.electionBasicResultsVO.allPartiesResults;

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Year');
	data.addColumn('number', 'Seats Won');
	data.addColumn('number', '2nd Pos');
	data.addColumn('number', '3rd Pos');
	data.addColumn('number', '4th Pos');

	data.addRows(10);

    var count = 0;
   	for(var i=0;i<allPartyRes.length;i++)
	{
        //check for main parties, if more parties are available then only top 10 main parties are considered
		if(electionType == 'Parliament' && allPartyRes[i].partyName != 'TDP' && allPartyRes[i].partyName != 'TRS' && allPartyRes[i].partyName != 'CPI' && allPartyRes[i].partyName != 'CPM' && allPartyRes[i].partyName != 'BJP' && allPartyRes[i].partyName != 'INC' && allPartyRes[i].partyName != 'SP' && allPartyRes[i].partyName != 'BSP' && allPartyRes[i].partyName != 'DMK' && allPartyRes[i].partyName != 'JD(U)')
		{
				continue;
		}
		else if(count < 10)
		{
			data.setValue(count, 0, allPartyRes[i].partyName);				
			data.setValue(count, 1, allPartyRes[i].totalSeatsWon);
			data.setValue(count, 2, allPartyRes[i].secondPosWon);
			data.setValue(count, 3, allPartyRes[i].thirdPosWon);
			data.setValue(count, 4, allPartyRes[i].fourthPosWon);
			count++;
			
		}
	}

	var chart = new google.visualization.ColumnChart(document.getElementById('graphImage'));
		
	chart.draw(data, {width:880,height: 350,title:ctitle,legend:'right',legendTextStyle:{fontSize:10},
				  hAxis: {title: 'Party',textStyle:{fontSize:'10',fontWeight:'bold'},slantedText:true, slantedTextAngle:30, titleTextStyle: {color: 'red'}}
	});
}
function showPartywiseDetailsDataTable(results)
{   
	var partywiseResultsArr = results.electionBasicResultsVO.allPartiesResults;
	var partywiseResultsWithoutAlliance = results.electionBasicResultsVO.allPartiesResultsWithoutGroupingOfAllianc;

	var assignToPartywiseResultsArr = new Array();	
	for(var i in  partywiseResultsArr){
		var partywiseResultsObj = { 
				partyId: partywiseResultsArr[i].partyId,
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
		if(electionResultsObj.allianceGroupNamesArray.length > 0 )
				for(k in electionResultsObj.allianceGroupNamesArray)
				{
					if(electionResultsObj.allianceGroupNamesArray[k] == partywiseResultsArr[i].partyName)
					{
						partywiseResultsObj.pc = '';
					}
				}
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
	/*imgStr+='<div style="margin-top:10px;margin-bottom:10px;">';
	imgStr+='<a href="javascript:{}" class="viewChartsForResults" onclick="showAllianceGraph(\'partywiseImgChart\',\''+results.statewiseResultsLineChartName+'\',\'Party Results Line Chart\')">View Party Results Line Charts</a>';*/
	if(electionResultsObj.allianceGroupNamesArray.length > 0 )
	{
		imgStr+='<a href="javascript:{}" class="viewChartsForResults" onclick="showPartyResultsWithoutAlliance(\''+results.stateLevelLineChartWithoutAllianc+'\')">View Party Results Without Alliance </a></div>';
	}
	chartpartywiseImgChartElmt.innerHTML = imgStr;
	var noteDivEl = document.getElementsByName("note");
	if(electionResultsObj.allianceGroupNamesArray.length>0)
	{
		for (k=0; k< noteDivEl.length; k++)
		{
			if(noteDivEl[k].style.display == "none")			
			{
				noteDivEl[k].style.display = '';}
		}
	}	
}
function buildPartywiseResultsDataTable(divId,dtSourceArray)
{	
	
	YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var Party = oRecord.getData("party");
		var partyIds = oRecord.getData("partyId");
		if(oData != 'IND' && partyIds != null){
		
	elLiner.innerHTML =
		"<a href='partyPageAction.action?partyId="+partyIds+"'>"+Party+"</a>";
		}
		else
			elLiner.innerHTML ='<a href="javascript:{}">'+Party+'</a>';
	};

	var partywiseResultsColumnDefs = [
								{key: "party", label: "<%=party%>", sortable:true,formatter:YAHOO.widget.DataTable.partyLink},		
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
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber},
								  {key: "partyId", parser:"number"}] 
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
function showAllianceGraph(index,allianceGroupName)
{
	var results = allianceResults[index].partiesInAlliance;
   	var data = new google.visualization.DataTable();
   
	//for chart columns
	data.addColumn('string', 'Party');
	for(var i=0;i<results.length;i++)
	{
      data.addColumn('number',results[i].partyName);
	}

	//for chart rows (seats won)
	var array = new Array();
	array.push('Seats Won');
    for(var j=0;j<results.length;j++)
	{
      array.push(results[j].totalSeatsWon);
	}
	 data.addRow(array);

	//for chart rows (2nd pos)
	var array1 = new Array();
	array1.push('2nd Pos');
    for(var k=0;k<results.length;k++)
	{
      array1.push(results[k].secondPosWon);
	}
	data.addRow(array1);

	//for chart rows (3rd pos)
	var array2 = new Array();
	array2.push('3rd Pos');
    for(var l=0;l<results.length;l++)
	{
      array2.push(results[l].thirdPosWon);
	}
	data.addRow(array2);

	//for chart rows (4th pos)
	var array3 = new Array();
	array3.push('4th Pos');
    for(var m=0;m<results.length;m++)
	{
      array3.push(results[m].fourthPosWon);
	}
	data.addRow(array3);

	//for chart rows (Nth pos)
	var array4 = new Array();
	array4.push('Nth Pos');
    for(var n=0;n<results.length;n++)
	{
      array4.push(results[n].nthPosWon);
	}
	data.addRow(array4);

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
	
	var str = '';
	str += '<div id="panelChartDiv"></div>';

	myPanel.setHeader(allianceGroupName);	  
	myPanel.setBody(str);
	myPanel.render();

	var staticColors = setStaticPartyColorsForInteractiveCharts(results);

    if(staticColors != null && staticColors.length > 0)
	{
		var chart = new google.visualization.LineChart(document.getElementById("panelChartDiv"));      
				chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",colors:staticColors,titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
	else
	{
		var chart = new google.visualization.LineChart(document.getElementById("panelChartDiv"));      
				chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
}
function showPartyResultsWithoutAlliance(chartId)
{	
	//partywiseResultsWithoutAlliance
	var results = electionResultsObj.partyWiseResultsWithoutAllianceArr;

	var contentStr ='<div id="withoutAllianceDiv_main" style="height:250px;overflow-y:auto">';
	contentStr +='<div id="withoutAllianceDiv_graph"></div>';
	contentStr +='<div id="withoutAllianceDiv_Datatable"></div>';
	contentStr +='</div>';

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
	   myPanel.setHeader("Party Results Without Alliance");
       myPanel.setBody(contentStr);
       myPanel.render();	

	   //interactive chart
	var data = new google.visualization.DataTable();
	var partiesArray = new Array();
   
	//for chart columns
	data.addColumn('string', 'Party');
	for(var i=0;i<results.length;i++)
	{
      data.addColumn('number',results[i].party);
	  partiesArray.push(results[i].party);
	}

	//for chart rows (seats won)
	var array = new Array();
	array.push('Seats Won');
    for(var j=0;j<results.length;j++)
	{
      array.push(results[j].seatsWon);
	}
	 data.addRow(array);

	//for chart rows (2nd pos)
	var array1 = new Array();
	array1.push('2nd Pos');
    for(var k=0;k<results.length;k++)
	{
      array1.push(results[k].second);
	}
	data.addRow(array1);

	//for chart rows (3rd pos)
	var array2 = new Array();
	array2.push('3rd Pos');
    for(var l=0;l<results.length;l++)
	{
      array2.push(results[l].third);
	}
	data.addRow(array2);

	//for chart rows (4th pos)
	var array3 = new Array();
	array3.push('4th Pos');
    for(var m=0;m<results.length;m++)
	{
      array3.push(results[m].fourth);
	}
	data.addRow(array3);

	var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	if(staticColors != null && staticColors.length > 0)
	{
		var chart = new google.visualization.LineChart(document.getElementById("withoutAllianceDiv_graph"));      
		chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",colors:staticColors,titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
	else
	{
		var chart = new google.visualization.LineChart(document.getElementById("withoutAllianceDiv_graph"));      
		chart.draw(data, {curveType: "function",width: 600, height: 350, pointSize: 4,title:"",titleColor:'red' ,titleFontSize:18,lineWidth:3});
	}
 
	buildPartywiseResultsDataTable("withoutAllianceDiv_Datatable",electionResultsObj.partyWiseResultsWithoutAllianceArr);
}

</SCRIPT>
</HEAD>
<BODY>
<CENTER>
<TABLE cellspacing="0" cellpadding="0" border="0" >
<TR>
<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" style="margin-top:15px;" /></TD><TD valign="top">
<c:if test="${electionType != 'Parliament'}"><DIV class="mainHeading">${stateName} ${electionType} Election Results ${year}</DIV></c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="mainHeading">${electionType} Election Results ${year}</DIV></c:if></TD><TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" style="margin-top:15px;" border="none"/>
</TD>
</TR>
</TABLE>
<DIV id="electionPageAjaxImgDiv">
	<DIV> Loading Election Results Please Wait..</DIV>
	<IMG src="images/icons/barloader.gif"/>
</DIV>
<c:if test="${electionType != 'Parliament'}"><DIV class="graphTop">State Level Overview</DIV></c:if>
<c:if test="${electionType == 'Parliament'}"><DIV class="graphTop">Country Level Overview</DIV></c:if>
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
		<TR>
			<TD colspan="2"><DIV id="note" name="note" style="display:none;"><P><FONT style="font-weight:bold;color:red;" >Note:</FONT>&nbsp;PC% column is empty for alliance parties in Partywise Results table, to find PC% for alliance parties kindly refer to the PC% column of the Alliance Details Table.PC% is Not Applicable for Independent Candidates(IND).</P></DIV></TD>
		</TR>		
	</TABLE>
	
</DIV>
</DIV>
<DIV class="graphBottom"></DIV>
<DIV class = "yui-skin-sam"><div id="panel"></DIV></DIV>
</CENTER>
<SCRIPT type="text/javascript">
getSelectedYearElectionResults(stateId,electionType,selectedYear);
</SCRIPT>
</BODY>
</HTML>