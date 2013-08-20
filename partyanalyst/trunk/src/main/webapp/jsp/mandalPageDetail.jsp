<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Voting Trends in a Mandal</title>

<!-- YUI files dependencies (start) -->

<!--CSS files (default YUI Sam Skin) -->
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
 
<!--JS files Dependencies -->
<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/json/json-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/get/get-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>

<!-- YUI files dependencies (end) --> 
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
 <script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>
<script type="text/javascript">
	
function checkForFormSubmit()
{
	$('#partyAjaxImg').show();
	if(document.mandalVotingTrends.partyField.value != 0)
	{
			getMandalVotingReport();
	}
	$('#partyAjaxImg').hide();
}
function getList(name,value)
{ 
	//var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	//ajaxImgElmt.style.display = "block";
	$('#mandalAjaxImg').show();
	var jsObj=
	{
		type:name,
		value:value
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/mandalPageDetailAction.action?"+rparam;			
	callAjax(jsObj,url, name);
}


function callAjax(jsObj,url,name){
	var myResults;	 		
	var callback = {			
	   success : function( o ) {
		try {

			//var img1=document.getElementById('ajaxLoadDiv');
					//	img1.style.display='none';


			myResults = YAHOO.lang.JSON.parse(o.responseText);
			
			if(jsObj.task=="mandalVoting")
			{
				buildMandalVoting(myResults);
			}
			else if(jsObj.task=="getDistricts")
			{
				buildDistricts(myResults);
			}
			else if(jsObj.task=="getConstituencys")
			{
				buildConstituency(myResults);
			}
			else
			{
				buildSelectOption(myResults,name);
			}							
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

function buildMandalVoting(myResult)
{
	var result = myResult.mandalAllElectionDetailsVO;
	
	if(result == "")
	{
		$("#mandalVotingResultsDivBody").html('');
		alert("Result empty or not found");
		return;
	}
	var elmt= document.getElementById("mandalVotingResultsDiv");
	var elmtHead= document.getElementById("mandalVotingResultsDivHead");
	var elmtBody= document.getElementById("mandalVotingResultsDivBody");
	var elmtBodyGraph= document.getElementById("mandalVotingResultsDivGraph");

	var mandalElmt = document.getElementById("mandalField");
	var mandalValue = mandalElmt.options[mandalElmt.selectedIndex].text;

	if(elmtHead)
		elmtHead.innerHTML=result[0].partyShortName+ " Party Voting Trends in "+mandalValue+" Mandal:";
	
	var imgStr='';
	imgStr+='<IMG id="chartImg" SRC="charts/'+myResult.chart+'" WIDTH="450" HEIGHT="400">';

	if(elmtBodyGraph)
		elmtBodyGraph.innerHTML=imgStr;

	var str='';
	str+='<table id="mandalVotingTable">';
	for(var i in result)
	{
		str+='<tr>';
		str+='<td>'+result[i].candidateName+'</td>';
		str+='<td>'+result[i].electionType+'</td>';
		str+='<td>'+result[i].electionYear+'</td>';
		str+='<td>'+result[i].partyShortName+'</td>';
		str+='<td align="right">'+result[i].partyVotesPercentage+'</td>';			if(result[i].totalVoters == 0)
		str+='<td align="right">-</td>';
		else
		str+='<td align="right">'+result[i].totalVoters+'</td>';
		str+='<td align="right">'+result[i].validVoters+'</td>';
		str+='</tr>';	
	}
	str+='</table>';
	
	if(elmtBody)
		elmtBody.innerHTML=str;
	var result1 =  myResult.mandalAllElectionDetailsVO1;
		buildColumnChart(result1);		
	buildMandalDataTable();
}
function buildColumnChart(result)
{
	var arr = new Array();
	for(var i in result[0].zptcMandalAllElectionDetailsVO)
	{
		var doubleArr = new Array();
				for (var j = 0; j < result[0].zptcMandalAllElectionDetailsVO[i].electionTypes.length; j++) {
				
				doubleArr.push(parseFloat(result[0].zptcMandalAllElectionDetailsVO[i].electionTypes[j]));
				}
		var obj = {
			name: result[0].zptcMandalAllElectionDetailsVO[i].electionYear,
			data:doubleArr
		}
			arr.push(obj);
	}
	
	$('#mandalVotingResultsDivGraph').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''
            },
           
            xAxis: {
                categories: result[0].electionTypes
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Votes Percentage '
                }
            },
           tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
           series: arr
        });
 }
function buildMandalDataTable()
{			
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
	.get("mandalVotingTable"));
	
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
			
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "candidateName"
		}, {
			key : "electionType"
		}, {
			key : "electionYear",parser:"number"
		}, {
			key : "partyShortName"
		}, {
			key : "partyVotesPercentage",parser:"number"
		}, {
			key : "totalVoters"
		} , {
			key : "validVoters",parser:"number"
		} ]
	};
			
	var resultsColumnDefs = [ {
		key : "candidateName",		
		label : "Name",
		sortable : true
	}, {
		key : "electionType",
		label : "Election",
		sortable : true
	}, {
		key : "electionYear",
		label : "Year",
		sortable : true
	}, {
		key : "partyShortName",
		label : "Party",
		sortable : true
	}, {
		key : "partyVotesPercentage",
		label : "Votes %",
		sortable : true
	},{
		key : "totalVoters",
		label : "Total Voters",
		sortable : true
	}, {
		key : "validVoters",
		label : "Valid Voters",
		sortable : true
	} ];



var elmt = document.getElementById('mandalVotingResultsDivBody');

if(elmt)
	var myDataTable = new YAHOO.widget.DataTable("mandalVotingResultsDivBody",resultsColumnDefs, resultsDataSource,{});  
else
	alert('No div elmt found to render datatable');
	
$('#reportAjaxImg').hide();
}

function buildSelectOption(results,selectedValue)
{
	var selectedElmt;
	selectOption='';
	if(selectedValue=="STATE")
	{
		selectOption='District';
		selectedElmt=document.getElementById("districtField");
	}
	else if(selectedValue=="DISTRICT")
	{
		selectOption='Constituency';
		selectedElmt=document.getElementById("constituencyField");
	}
	else if(selectedValue=="CONSTITUENCY")
	{
		selectOption='Mandal';
		selectedElmt=document.getElementById("mandalField");
	}
	
	var len=selectedElmt.length;			
	for(i=len-1;i>=0;i--)
	{
		selectedElmt.remove(i);
	}
	var opElmt1=document.createElement('option');
	opElmt1.value=0;
	opElmt1.text='Select '+selectOption;
	
	try
	{
		selectedElmt.add(opElmt1,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt1); // IE only
	}	

	for(var val in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;
		
		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}			
	}
	$('#mandalAjaxImg').hide();
}

function getMandalVotingReport()
{
	//var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
	//ajaxImgElmt.style.display = "block";
	var stateElmt = document.getElementById("stateField");
	var districtElmt = document.getElementById("districtField");
	var constituencyElmt = document.getElementById("constituencyField");
	var mandalElmt = document.getElementById("mandalField");
	var partyElmt = document.getElementById("partyField");

	if(!stateElmt || !districtElmt || !constituencyElmt || !mandalElmt || !partyElmt)
		return;

	var stateValue = stateElmt.options[stateElmt.selectedIndex].value;
	var districtValue = districtElmt.options[districtElmt.selectedIndex].value;
	var constituencyValue = constituencyElmt.options[constituencyElmt.selectedIndex].value;
	var mandalValue = mandalElmt.options[mandalElmt.selectedIndex].value;
	var partyValue = partyElmt.options[partyElmt.selectedIndex].value;

	if(stateValue == "0" || districtValue == "0" || constituencyValue == "0" || mandalValue == "0" || partyValue == "0")
		return;
	
	$('#reportAjaxImg').show();
	var jsObj={
			state:stateValue,
			district:districtValue,
			constituency:constituencyValue,
			mandal:mandalValue,
			party:partyValue,
			"task":"mandalVoting"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/mandalVotingResultAction.action?"+rparam;			
	callAjax(jsObj,url,"");
}


function getDistrictsList()
{
	$('#districtAjaxImg').show();
	var stateId = $('#stateField option:selected').val();
	var jsObj =
		{  	
			stateId:stateId,
			task:'getDistricts'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getConstiAndDistrict.action?"+rparam;
		callAjax(jsObj, url ,"");
}

function getConstituencsList()
{
	$('#constituencyAjaxImg').show();
	var districtId = $('#districtField option:selected').val();
	var jsObj =
		{  	
			districtId:districtId,
			task:'getConstituencys'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getConstiAndDistrict.action?"+rparam;
		callAjax(jsObj, url,"");
}

function buildDistricts(myResults)
{
	if(myResults != null)
	{
		var str = "";
		$("#districtField option").remove();
		str += '<option value="0">Select District</option>';
		for(var i in myResults)
		{
			str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
		}
		$('#districtField').html(str);
	}
	$('#districtAjaxImg').hide();
}

function buildConstituency(myResults)
{
	if(myResults != null)
	{
		var str = "";
		$("#constituencyField option").remove();
		str += '<option value="0">Select Constituency</option>';
		for(var i in myResults)
		{
			str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
		}
		$('#constituencyField').html(str);
	}
	$('#constituencyAjaxImg').hide();;
}

</script>
	<style type="text/css">
		.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
		{
			background:none;
		}

		.yui-skin-sam thead .yui-dt-sortable {

			background-color:#B5BDCA;
			color:#3F546F;
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
		.yui-skin-sam tr.yui-dt-odd
		{
			background-color:#F3F6FC;
		}
		.yui-skin-sam tr.yui-dt-odd td.yui-dt-asc, .yui-skin-sam tr.yui-dt-odd td.yui-dt-desc 
		{
			background-color:#E8EDF5;
		}
		.yui-skin-sam tr.yui-dt-even td.yui-dt-asc, .yui-skin-sam tr.yui-dt-even td.yui-dt-desc 
		{
			background-color:#F8FAFF;
		}
		#mandalVotingResultsDiv
		{
			margin-top:25px;
			margin-left:40px;
		}
		#mandalVotingResultsDivHead
		{
			text-decoration:underline;
			text-align:left;
			font-weight:bold;
			padding:10px;
			color:#233B7A;
		}
		#mandalVotingResultsDivBody
		{
			padding:5px;
			text-align:left;
			margin-bottom: 20px;
			margin-top: 5px;
		}
		#mandalVotingTable
		{
			border:1px solid #AFC9DC;
		}
		
		#mainHeadingDiv
		{
			font-weight:bold;
			padding:10px;
			color:#23318B;
			font-size:17px;
			text-decoration:underline;
			margin-left:300px;
			
			
		}
		#mandalSelectTableDiv
		{
			background-color:#E8E9EE;
			margin-left:48px;
			text-align:left;
			border:2px solid #DADADA;
			width:800px;
			
		}
		
		#mandalSelectTableDivHead
		{
			padding:10px;
			color:#59629C;
			text-decoration:underline;
			font-weight:bold;
			
		}
		#mandalSelectTable th
		{
			padding:5px;
			color:#2B4A77;
		}
		.mySelectBox
		{
			width:180px;
			padding:2px;
			color:#334C72;
		}
		.tdLeftBorder
		{
			border-left:1px solid #8E9AAE;			
		}
		#mandalVotingResultsDivBody table{border:1px solid #cdcdcd;width:95%;}
	</style>

</head>

<body>
<div style="margin-left:auto;margin-right:auto;width:900px;font-size:14px;">
<div style="background:#ffffff;">
	<s:form action="mandalPageDetailAction" method="POST" theme="simple" name="mandalVotingTrends">

		<div id="mainHeadingDiv">Party Voting Trends in a Mandal / Tehsil</div>
		<div id="mandalSelectTableDiv">
			<div id="mandalSelectTableDivHead">Select Criteria ..</div>
			<div id="mandalSelectTableDivBody">
				<table id="mandalSelectTable" width="100%">
					<tr>
						<th align="left"><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /></th>
						<td align="left">
							<s:select id="stateField" name="state" list="states" listKey="id" listValue="name" onchange="getDistrictsList();" cssClass="mySelectBox"></s:select><img  id='districtAjaxImg'class="ajaxImgClass" src='images/icons/search.gif' style="display:none;"></img>					
						</td>

						
					
						<th align="left" class="tdLeftBorder" style="padding-left:30px;"><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /></th>
						<td align="left">
							<select class="mySelectBox" id="districtField" name="district" onchange="getConstituencsList();">
								<option value="0">Select District</option>
							</select> <img  id="constituencyAjaxImg" class="ajaxImgClass" src='images/icons/search.gif' style="display:none;"></img>	
						</td>
					</tr>
					<tr>
						<th align="left"><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}" /></th>
						<td align="left">
							<select class="mySelectBox" id="constituencyField" name="constituency" onchange="getList('CONSTITUENCY',this.options[this.selectedIndex].value)">
								<option value="0">Select Constituency</option>
							</select><img  id="mandalAjaxImg" class="ajaxImgClass" src='images/icons/search.gif' style="display:none;"></img>
						</td>
					
						
						<th align="left" class="tdLeftBorder" style="padding-left:30px;"><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}" /></th>
						<td align="left">
							<select class="mySelectBox" id="mandalField" name="mandal" onchange= "checkForFormSubmit()" >
								<option value="0">Select Mandal</option>
							</select>	
							<img  id="partyAjaxImg" class="ajaxImgClass" src='images/icons/search.gif' style="display:none;"></img>
						</td>							
					</tr>
					<tr>
						<th align="left"><s:label for="partyField" id="mandalLabel"  value="%{getText('PARTY')}" /></th>
						<td align="left">
							<s:select cssClass="mySelectBox" id="partyField" name="party" list="partyList" listKey="id" listValue="name" onchange="getMandalVotingReport()"></s:select>	<img  id="reportAjaxImg" class="ajaxImgClass" src='images/icons/search.gif' style="display:none;"></img>
						</td>
					</tr>
				</table>
				</div>
		</div>
	</s:form>

	<div id="ajaxLoadDiv" style="display:none;padding-top:20px;">
							<span><b>Processing Request ...</b> </span>
							<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>
	</div>

	<div id="mandalVotingResultsDiv" style="position:relative;">
		<div id="mandalVotingResultsDivHead"></div>
		<div id="mandalVotingResultsDivGraph" style="minwidth:310px;height:400px;margin:0 auto;"></div>
		<div id="mandalVotingResultsDivBody" class="yui-skin-sam"></div>
	</div>
	</div>
	</div>
</body>

</html>