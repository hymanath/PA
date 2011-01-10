<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>

<title>Census Report</title>

<style type="text/css">
	
	#censusReport_main
	{
		padding:20px;
	}
	#censusReportHeading
	{
		color:#6C3C05;
		font-size:20px;
		font-weight:normal;
		padding:20px;
		text-decoration:underline;
	}
	
	#censusReport_body
	{
		text-align:left;
	}
	
	#censusReport_body_heading
	{
		border-bottom:1px solid #ADADAD;
	}

	#censusReport_body_heading_table td
	{
		color:#3D362E;
		font-size:14px;
		font-weight:bold;
		padding:10px;
	}
		
	#censusReport_body_input_table th
	{
		padding:10px;
		width:80px;
	}

	#censusReport_body_input_table td
	{
		padding:10px;
	}

	#censusPopulationRange
	{
		margin-top:20px;
	}

	.dataHeaderDiv
	{
		font-size:15px;
		font-weight:normal;
		height:40px;	
		text-decoration:underline;
		color:#3D362E;
	}

	#performanceGraphDiv
	{
		margin-top:30;
	}
	
	#censusPopulationRange_body_table_outer td
	{
		padding:10px;
	}

	#censusPopulationRange_body_table_inner th
	{
		padding:5px;
	}

	#censusPopulationRange_body_table_inner td
	{
		padding:5px;
	}
</style>

<script type="text/javascript">
	
	var rangeResults = [];
	var	yearValue = '';
    google.load("visualization", "1", {packages:["corechart"]});
	
	function getCensusDetails()
	{
		var censusElmt = document.getElementById("censusSelect");
		var yearElmt = document.getElementById("yearSelect");
		var stateElmt = document.getElementById("stateList");
		var districtElmt = document.getElementById("districtList");
		var reportLevel = '';

		var censusValue = censusElmt.options[censusElmt.selectedIndex].value;
		var censusText = censusElmt.options[censusElmt.selectedIndex].text;
		yearValue = yearElmt.options[yearElmt.selectedIndex].text;
		var stateId = stateElmt.options[stateElmt.selectedIndex].value;
		var districtId = districtElmt.options[districtElmt.selectedIndex].value;
		var stRadioEle = document.getElementById("stRadioId");
		var diRadioEle = document.getElementById("diRadioId");
		
		if(stRadioEle.checked == true)
			reportLevel = stRadioEle.value;
		else if(diRadioEle.checked == true)
			reportLevel = diRadioEle.value;			
		
		var jsObj=
		{
				stateId		: stateId,
				districtId	: districtId,
				censusText	: censusText,
				censusValue	: censusValue,
				yearValue	: yearValue,
				reportLevel : reportLevel,
				task		: "getCensusDetails"					
		}; 

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCensusInfoInAStateAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}
	
	function buildPopulationRange(jsObj,results)
	{
		var elmt = document.getElementById("censusPopulationRange");
		rangeResults = results;
		if(!elmt)
			return;

		var str = '';
		str += '<div id="censusPopulationRange_head" class="dataHeaderDiv">';
		str += 'Assembly Constituencies Results in '+jsObj.yearValue+' for '+jsObj.censusText;
		str += '</div>';
		str += '<div id="censusPopulationRange_body">';
		str += '<table id="censusPopulationRange_body_table_outer" border="0">';
		str += '<tr>';
		str += '<td>';
		for(var i=0; i<results.length; i++)
		{
			if(i==5)
			{
				str += '</td><td>';
			}
			str += '<table id="censusPopulationRange_body_table_inner" border="0">';
			str += '<tr>';
			str += '<th><img src="images/icons/districtPage/listIcon.png"></th>';
			str += '<th>'+results[i].range+'</th>';
			if(results[i].count != 0)
				str += '<td><a href="javascript:{}" onclick="viewPerformanceGraph('+i+')">'+results[i].count+'</a></td>';
			else
				str += '<td>'+results[i].count+'</td>';
			str += '</tr>';	
			str += '</table>';			
		}
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';

		elmt.innerHTML = str;
	}
	
	function viewPerformanceGraph(index)
	{
		var idsList = rangeResults[index].locationIds;
		yearValue
		var jsObj=
		{
				idsList:idsList,
				yearValue:yearValue,				
				task:"getPartiesPerformanceInCensusReport"					
		}; 

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartiesPerformanceInCensusReportAction.action?"+rparam;						
		censusAjaxCall(jsObj,url);
	}
	
	function buildPerformanceTable(results)
	{
		var elmt = document.getElementById("performanceGraphDiv");
        var str = '';
        str += '<div style="padding:10px;font-weight:bold;font-size:12px;color:#707070;">Constituencywise Performance</div>';	
        str += '<div id="partyResultsTableDiv">';
        str += '<table border="0" id="partyResultsTable">';
         for(var j in results.constituenciesResults)
        {
        	str += '<tr>';
        	str += '<td>'+results.constituenciesResults[j].constituencyName+'</td>';
        	for(var k in results.constituenciesResults[j].partyResultsVO)
            	if(results.constituenciesResults[j].partyResultsVO[k].percentage != null)
        			str += '<td>'+results.constituenciesResults[j].partyResultsVO[k].percentage+'</td>';
        		else
        			str += '<td></td>';
        	str += '</tr>';
        }
        str += '</table>';
        str += '</div>';
      //  str += '<div style="padding:10px;font-weight:bold;font-size:12px;color:#707070;">Constituencywise Performance</div>';
        elmt.innerHTML = str;
		//getCensusDataInteractiveChart(results,"performanceGraphDiv");
		var tableDiv = document.getElementById("partyResultsTable");
    	var str = '';
    	var divEl = document.getElementById("partyResultsTableDiv");
    	
    		
   		 var myColumnDefs = new Array();
   		 var myFields = new Array();

   		var constHead = {
	 			key:"Constituency",
	 			lable: "Constituency",
	 			sortable:true
		   }
   		var constValue = {key:"Constituency"}   		 
		myColumnDefs.push(constHead);
		myFields.push(constValue);
		    		 

    		 for(var l in results.allPartiesList){
    			var obj1 = {
    						key:results.allPartiesList[l],
    						label:results.allPartiesList[l],
    						sortable:true
    					}
    			var obj2 = {
 						key:results.allPartiesList[l]    						
 					}
    			myColumnDefs.push(obj1);
    			myFields.push(obj2);    			
    		 }			
    		 	
    		 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
    					.get("partyResultsTable")); 
    		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
    		 myDataSource.responseSchema = { 
    								            fields:myFields    
    								        };
    		 if(results.constituenciesResults.length > 10)
    	        {
    				var myConfigs = { 
    					    paginator : new YAHOO.widget.Paginator({ 
    				        rowsPerPage    : 10 
    					    }) 
    						};
    	        }        
    		var villageDataTable = new YAHOO.widget.DataTable("partyResultsTableDiv",myColumnDefs, myDataSource, myConfigs);
    		var headingEl = document.getElementById("heading");
    		headingEl.innerHTML = 'Partywise Performance';
    		var localLeadersColumnDefs = [ 

    								{key:"partyName", label:"Party", sortable:true},         
    								{key:"seatsParticipated", label: "Seats Participated", sortable:true},
    			    	            {key:"totalSeatsWon", label: "Seats Won", sortable:true},
    			    	            {key:"avgPercentage", label: "Average Percentage", sortable:true}    			    	            		    				
    			    	        ]; 
    		var localLeadersDataSource = new YAHOO.util.DataSource(results.partyResultsList); 
    			localLeadersDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
    			localLeadersDataSource.responseSchema = { 
    	            fields: [{key:"partyName"},{key:"seatsParticipated", parser:"number"},{key:"totalSeatsWon", parser:"number"},{key:"avgPercentage", parser:"number"}] 
    	        };
    			if(results.partyResultsList.length > 10)
    	        {	
    	        var myConfigs = { 
    					    paginator : new YAHOO.widget.Paginator({ 
    				        rowsPerPage    : 10 
    					    }) 
    						};
    	        }	    	        
    		var localLeadersDataTable =  new YAHOO.widget.DataTable("partyResultsDiv", localLeadersColumnDefs, localLeadersDataSource,myConfigs);
	}

	 //mandalwise google charts
	 function getCensusDataInteractiveChart(results,divId)
	 {
		 var chartColumns = results[0].partyResultsVO;
		 var chartRows = results;

		 var data = new google.visualization.DataTable();
		 var colorArray = new Array(); 
		 var colorStatic = new Array('#088A85','#00FFFF','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A');

		  data.addColumn('string', 'Party');
		  //for columns
		  for(var i in chartColumns){
		   var colData = chartColumns[i].partyName;
		   data.addColumn('number', colData);

		   if(chartColumns[i].partyName == 'TDP'){
			   colorArray.push('#C7005D');
		   }
		   else if(chartColumns[i].partyName == 'TRS'){
				colorArray.push('#F5A9F2');
		   }else if(chartColumns[i].partyName == 'INC'){
				colorArray.push('#2A00D2');
		   }else if(chartColumns[i].partyName == 'BJP'){
				colorArray.push('#FE9A2E');
		   }else if(chartColumns[i].partyName == 'PRP'){
				colorArray.push('#74DF00');
		   }
		   else{
			   colorArray.push(colorStatic[i]);
		   }

		  }

		  //for rows
		  for(var j in chartRows)
		  {
			  var array = new Array();
			  array.push(chartRows[j].constituencyName);

			  var partyRes = chartRows[j].partyResultsVO;
			  
			  for(var k in partyRes){
				array.push(partyRes[k].votesPercent);
			  }
			  
			  data.addRow(array);
		  }
		  var ctitle = 'All Parties Performance Graph'; 
		  new google.visualization.LineChart(document.getElementById(divId)).
		  draw(data, {curveType: "function",width: 800, height: 450,title:ctitle,colors:colorArray,titleColor:'red' ,titleFontSize:14,lineWidth:2,hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
			
	 }

	function censusAjaxCall(jsObj,url){
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getCensusDetails")
								{	
									buildPopulationRange(jsObj,myResults);
								} 
								else if(jsObj.task == "getPartiesPerformanceInCensusReport")
									buildPerformanceTable(myResults);
								
						}
						catch (e)
							{   
							  // 	alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            	  			// alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

	function hideDistrictSelect()
	{
		document.getElementById("districtList").disabled= true; 
	}
	
	function showDistrictSelect()
	{
		document.getElementById("districtList").disabled= false; 
	}
</script>

</head>
<body>
	<div id="censusReport_main">
	<div id="censusReportHeading"> Census Report </div>
		<div id="censusReport_body">
		<div id="censusReport_body_heading">
			<table id="censusReport_body_heading_table">
				<tr>
					<td><img src="images/icons/infoicon.png"/></td>
					<td>Select the following options to view the census report.</td>
				</tr>
			</table>
		</div>
		<div>
		<table>	
		<tr>
			<th>Please Select Report Level :&nbsp;&nbsp;</th>
			<td><input  id="stRadioId" type="radio" name="location" value="state" onclick="hideDistrictSelect()" checked="checked"></input></td>
			<th>State Wise Report</th>
			<td><input  id="diRadioId" type="radio" name="location" value="district" onclick="showDistrictSelect()"></input></td>
			<th>District Wise Report</th>
		</tr>
		</table>
		
		<table id="censusReport_body_input_table">
			
			<tr>
				<th>State</th>
				<td>
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList" list="states" listKey="id" listValue="name" headerKey="0" headerValue="Select State"  onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtList','currentAdd');"/>	
				</td>

				<th>District</th>
				<td>
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district_s" id="districtList" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select District" />	
				</td>
			
				<th>Year</th>
				<td>
					<select id="yearSelect">
						<option>2009</option>
						<option>2004</option>
					</select>
				</td>
			
				<th>Census Type</th>
				<td>
					<select onchange="getCensusDetails()" class="selectWidth" id="censusSelect">
						<option value="0">Select</option>
						<option value="1">SC Population</option>
						<option value="2">ST Population</option>
						<option value="3">Literates</option>
						<option value="4">illiterates</option>
						<option value="5">Working People</option>
						<option value="6">Non Working People</option>
					</select>
				</td>
			</tr>
		</table>
		</div>
		<div id="censusPopulationRange">
		</div>
		<div style="width:800px;overflow-y : auto;margin:30px;"><div id="performanceGraphDiv"  class="yui-skin-sam"></div></div>
		<div id="heading" style="margin:30px;padding:10px;font-weight:bold;font-size:12px;color:#707070;"></div>
		<div class="yui-skin-sam" style="margin:30px;"><div id="partyResultsDiv"></div></div>
		</div>
	</div>
	<script>
	hideDistrictSelect();
	</script>
</body>
</html>