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

	function getCensusDetails()
	{
		var censusElmt = document.getElementById("censusSelect");
		var yearElmt = document.getElementById("yearSelect");
		var stateElmt = document.getElementById("stateList");

		var censusValue = censusElmt.options[censusElmt.selectedIndex].value;
		var censusText = censusElmt.options[censusElmt.selectedIndex].text;
		yearValue = yearElmt.options[yearElmt.selectedIndex].text;
		var stateValue = stateElmt.options[stateElmt.selectedIndex].text;


		var jsObj=
		{
				stateId:stateValue,
				censusText:censusText,
				censusValue:censusValue,
				yearValue:yearValue,
				task:"getCensusDetails"					
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
		str += '<table id="censusPopulationRange_body_table_outer">';
		str += '<tr>';
		str += '<td>';
		for(var i=0; i<results.length; i++)
		{
			if(i==5)
			{
				str += '</td><td>';
			}
			str += '<table id="censusPopulationRange_body_table_inner">';
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
	
	function buildPerformanceGraph(results)
	{
		var elmt = document.getElementById("performanceGraphDiv");

		var str = '';
		str += '<div id="performanceGraphDiv_head" class="dataHeaderDiv"> All Parties Performance Graph </div>';
		str += '<div id="performanceGraphDiv_body"><img src="charts/'+results+'"></div>';

		elmt.innerHTML = str ;
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
									buildPerformanceGraph(myResults);
								
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
		<table id="censusReport_body_input_table">
			<tr>
				<th>State</th>
				<td>
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList" list="states" listKey="id" listValue="name" />		
				</td>
			
				<th>Select Year</th>
				<td>
					<select id="yearSelect">
						<option>2009</option>
						<option>2004</option>
					</select>
				</td>
			
				<th>Select Census</th>
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
		<div id="performanceGraphDiv">
		</div>
		</div>
	</div>
</body>
</html>