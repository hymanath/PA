<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Suggestive Model</title>
 <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<style type="text/css">	
	select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 180px;
	}
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
	input, button, select {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		 border: 1px solid lightBlue;
	}
	.tdWidth1,#tdWidth{
	width: 150px;
	}

	
	#requiredValue{
	color:red;
	font-size:large;
	}	
	#mainDiv{
	font-family: serif verdana sans-serif;
	border: 1px lightBlue solid ;
	width: 920px; 
	margin-left: 70px;
	margin-bottom: 15px;
	background-color:white;
	font-weight:bold;
	font-size:15px;
	border-radius: 5px 5px 5px 5px;
	}	

	#tableRowS,#tableRowC,#tableRowM,#tableRowP,#tableRowB{
	margin-left: -10px;
	}

	#titleHeading{
	font-family: verdana sans-serif serif;
	margin-top: 30px;
	height: 25px;
	width: 920px; 
	margin-left: 73px;
	color:white;
	background:none repeat scroll 0 0 #06ABEA;
	margin-top: 30px; 
	margin-bottom: 5px;
	font-weight:bold;
	font-size:18px;
	border-radius: 5px 5px 5px 5px;
	}
	#errorMsgDiv{
	color: red; 
	float: left; 
	font-weight:normal;
	font-size:15px;
	width: 775px;
	height: 25px;
	padding-top: 5px;
	}
#partyPerformanceInnerDiv table th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#partyPerformanceInnerDiv table td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
#partyPerformanceMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 960px;}
#partyPerformanceBtnDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 200px;}
	</style>
<script type="text/javascript" >

$(document).ready(function(){
getConstituencyList();
});
function getConstituencyList(){

var jsObj= 
	{	
		electionYear : 2011,
		electionType : 2,
		partyId:823,		
		stateId:1,	
		task:"getConstituencies"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getConstituenciesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);

}
function getMandals(){
		var value =  $("#listConstituencyNames option:selected").val();
		var jsObj=
			{
					
					selected:value,
					selectElmt:"mandalField",
					str   : "all",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url)
	}
function getPartyDetails(mandalId){

	var jsObj = 
	{
	mandalId : mandalId.slice(1),
	task:"getPartyDetails"
	}	
	var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyForSuggestiveAction.action?"+param;
	callAjax(param,jsObj,url);

}
function getElectionYears222(partyId){

		var task;
		if(name == 'panchayat')
			task = 'getElectionYearsForPanchayat';
		else
			task = 'getElectionYears';
		var jsObj=
			{
					electionTypeId:2,
					task:"getElectionYearsForPanchayat"			
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
	
	function getElectionYears(id){
		var mandalId = $('#listMandalNames').val();
		var jsObj=
			{
					electionTypeId:2,
					mandalId : mandalId.slice(1),
					task:"getElectionYearsInPanchayat"
                    					
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsForMandalAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}

function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								
								if(jsObj.task == "getElectionYearsInPanchayat")
								{
									populateElectionYearDropdown(myResults);
								}
								else if(jsObj.task == "getPartyDetails")
								{	
									populatePartiesDropdown(myResults);
								}								
								else if(jsObj.task == "getConstituencies")
								{	
									populateConstituencyListDropdown(myResults);
								}
								else if(jsObj.task == "getConstituencyType")
								{
									constituencyType = myResults[0].name;
									if(myResults[0].name == 'URBAN'){
										$('#urban_areasDiv').css("display","block");
									}
									else{
										$('#rural_urban_areasDiv').css("display","block");
									}
								}
								else if(jsObj.task == "getAllWards")
									{
											buildAllMandals(myResults,jsObj,'ward');
									}
								else if(jsObj.task == "getMandalList")
									{
											buildMandalList(myResults);
									}
									else if(jsObj.task == "getReportLevelDetails")
									{
											buildReportLevelData(myResults,jsObj);
									}
							
							   else if(jsObj.task == "getPartyPerformanceReport")
								showPartyPerformanceReport(myResults,jsObj);

							}catch (e) {   
								   	//alert("Invalid JSON result" + e);   
							}  
			               },
			               scope : this,
			               failure : function( o ) {
			                			//alert( "Failed to load result" + o.status + " " + o.statusText);
			                         }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}	
function populatePartiesDropdown(results)
{
	var partySelectEl = document.getElementById("partySelectEl");
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
		addOptions(partySelectEl,opElmt);	
	}	
}

function populateElectionYearDropdown(results)
{
	var electionYearsEl1 = document.getElementById("electionYearSelectEl1");
	var electionYearsEl2 = document.getElementById("electionYearSelectEl2");

		removeSelectElements(electionYearsEl1);
		removeSelectElements(electionYearsEl2);

	if(results!=null)
		for(var i in results)
		{	
			var opElmt1=document.createElement('option');
			var opElmt2=document.createElement('option');
			opElmt1.value=results[i].id;
			opElmt1.text=results[i].name;
			opElmt2.value=results[i].id;
			opElmt2.text=results[i].name;
			addOptions(electionYearsEl1,opElmt1);
			addOptions(electionYearsEl2,opElmt2);
		}
}
function buildMandalList(results){

var list = document.getElementById("listMandalNames");
	removeSelectElements(list); 
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}
function populateConstituencyListDropdown(results){

	var list = document.getElementById("listConstituencyNames");
	removeSelectElements(list);
	var opElmt=document.createElement('option');
			opElmt.value='0';
			opElmt.text='Select Constituency';
		addOptions(list,opElmt);
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);
			
		}	

}


function addOptions(list,opElmt){
	try
		{
		list.add(opElmt,null); // standards compliant
		}
	catch(ex)
		{
		list.add(opElmt); // IE only
		}
}

function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	

}

function addDefaultSelectValues(elmt){
	var opElmt=document.createElement('option');
		opElmt.value='0';
		opElmt.text='Select ';	
	addOptions(elmt,opElmt);
}

</script>
</head>
<body>
<div id="titleHeading" align="center"> SUGGESTIVE MODEL </div>
<div id="mainDiv" align="center" >
<div id="errorMsgDiv" >&nbsp;</div><br><br>
<div>
		<table>
			<tr id="tableRowS">
				<td id="tdWidth">
					Constituency Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listConstituencyNames" onchange="getMandals(this.options[this.selectedIndex].value);">
					<option value="0"> Select Constituency </option>
					</select>
				</td>		
			
			<td id="tdWidth">
					Mandal Name :<font id="requiredValue" class="requiredFont">*</font> 
				</td>
				<td>
					<select id="listMandalNames" onchange="getPartyDetails(this.options[this.selectedIndex].value);">
					<option value="0"> Select Mandal </option>
					</select>
				</td>	
			</tr>
	</table>		
</div>
<div style="width: 500px; margin-left: -340px;">
	<table>
		<tr id="tableRowS">
			<td id="tdWidth">
				Party Name:<font id="requiredValue" class="requiredFont">*</font> 
			</td>
			<td>
				<select id="partySelectEl" onchange="getElectionYears(this.options[this.selectedIndex].value)">
				<option value="0"> Select Party </option>
				</select>
			</td>
			</tr>
</table>
</div>
<div style="margin-left: -150px;">
<table>
		<tr>
			<td id="tdWidth">
				Election Year :<font id="requiredValue" class="requiredFont">*</font> 
			</td>		
			<td>
				<select id="electionYearSelectEl1">
				<option value="0"> Select Year </option>
				</select>
			</td>
			<td></td><td></td>
			<td>
				<select id="electionYearSelectEl2">
				<option value="0"> Select Year </option>
				</select>
			</td>
		</tr>
	</table>
</div>
<input type="button" value="Submit" class="btn btn-success" style="margin-bottom: 10px; margin-top: 10px;"/>
</div>
<div id="partyPerformanceBtnDiv"><input type="button" value="submit" id="getPartyPer" class="btn btn-info"></div>
</div>

<div id="partyPerformanceMainDiv">
 <div id="partyPerformanceInnerDiv"></div>
</div>
</div>

<script>
$(document).ready(function(){

$("#getPartyPer").click(function(){
	  
	var jsObj = {
	        constituencyId:232,
			electionId:0,
			partyId:362,
			locationId:2844,
			locationType:"mandal",
			tempVar:"all",
			task:"getPartyPerformanceReport"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getPartyPerformanceAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
		
  });

});

function showPartyPerformanceReport(result,jsObj)
{
	$("#partyPerformanceInnerDiv").html('');
  if(result == null || result.size == 0)
	{
     $("#partyPerformanceInnerDiv").html('No Data Found');
	 return;
	}
	var str = '';
	str +='<h4>Party Performance Report</h4>';
	str +='<table class="table table-bordered table-striped table-hover">';
    str +='<tr>';
	str +='<th></th>';
	for(var i in result)
	 str +='<th>'+result[i].name+'</th>';
	str +='</tr>';
    var length = result[0].partyPositionVOList.length;
	for(var j=0;j<length;j++){
		str +='<tr>';
		str +='<td>'+result[0].partyPositionVOList[j].name+'</td>';
		for(var i in result)
		{
		  str +='<td>'
		  for(var k in result[i].partyPositionVOList[j].partyPositionVOList)
		    str +=''+result[i].partyPositionVOList[j].partyPositionVOList[k].name+',';
		  
		  str +='</td>'
		}
		str +='</tr>';
    }
	str +='</table>';
	$("#partyPerformanceInnerDiv").html(str);
}


</script>
</body>
</html>
