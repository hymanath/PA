<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election results in all revenue villages of ${tehsilName } Mandal </title>
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
    <!--<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
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

	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>		
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
    <link href="styles/assets/css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

	<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
	<!--<script type="text/javascript" src="js/highcharts/js/modules/exporting.js"></script>-->
	
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	
	<script>// Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});
	</script>
	<style type="text/css">
		.mainHeading 
		{
			background-image:url("images/icons/electionResultsReport/heading.png");
			border:0 solid #AEE2FF;
			color:#000000;
			font-family:MS Sans-serif;
			font-size:18px;
			font-weight:bold;
			height:25px;
			margin-bottom:15px;
			margin-top:15px;
			padding:10px;
			text-align:center;
		}
		.button 
		{
			background-attachment:scroll;
			background-color:#335291;
			background-image:none;
			background-position:0 0;
			background-repeat:repeat;
			color:#FFFFFF;
			width:113px;			
		}

		#partiesTrendzInputTable
		{
			font-size:13px;
		}
		
		#partiesTrendzInputTable th
		{
			padding:5px;
		}

		#votesPolledDtTableDiv_outer .yui-dt th a 
		{
			font-size:17px;
		}

		#votesPolledDtTableDiv_outer .yui-dt td 
		{
			color:#121922;
			font-size:16px;
			font-weight:bold;
		}
		label{display:inline-block;}
		input[type="radio"], input[type="checkbox"] {margin:5px;}
		.hero-unit{padding:40px;color:black;font-size:15px;}
	</style>
	<script type="text/javascript">
	var chartDataArr=[];
	var linechartDataArr=[];
	var constituency_name;
	var mandalId = '${tehsilId}';
	var tehsilId = '';
	var constituencyId = '';
	var publicationId = 8;
	var castePercent=[];
	
var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };





var castTemp = new Array();
var hamletTemp = new Array();

var hamletMain = new Array();
var hamletMainPie =  new Array();

var myChart = new Array();

var tittleString ='';
/*This method is used for building the data table for  votercastInfo details */

function buildCastInfoForSubLevels(myresults,jsObj)
	{	 hamletMainPie=[];
	      myChart=[];
		  castTemp=[];
	
		

		var type=jsObj.type;
		var	subLevelcastInfo = new Array();
		var cast = myresults.castVosList;

		for(var i in cast)
		{
		if(cast[i].voterCastInfoVO != null)
		{
		  //for build pie chart
		var hamletTempPie =new Object();
		var castSum = 0;
		  //for build columns
		  var hamletTempColumn =  new Object();
		  
		var subLevelcastData = cast[i].voterCastInfoVO; 
		
		if(cast[i].mandalName != null){
		hamletTempColumn['type'] = 'column';
		var name = cast[i].mandalName;
		hamletTempPie['name'] = cast[i].mandalName;
		hamletTempColumn['name'] = cast[i].mandalName;
		}
		else
		var name ="";
		if(cast[i].locationId != null)
		var locationId=cast[i].locationId;
		else
		locationId = 0;
		var totalVoters=subLevelcastData.totalVoters;
		var cast1 =subLevelcastData.castVOs;
			var castData = new Object();
			for(var k in cast1)
			{
			
		var castStats1 = {
			mandal : name,
			locationId:locationId,
			caste : cast1[k].castName,
			casteCategory:cast1[k].casteCategoryName,
			castePopulation : cast1[k].castCount,
			malePopulation : cast1[k].malevoters,
			femalePopulation : cast1[k].femalevoters,
			castePercentage:cast1[k].castPercentage,
			totalVoters:totalVoters,
			castStateId:cast1[k].castStateId,
			};
		subLevelcastInfo.push(castStats1);
		castSum = parseInt(castSum)+parseInt( cast1[k].castCount);
		
		castData[cast1[k].castName] = cast1[k].castCount;
		
			}
			hamletTempColumn['data'] = castData;

			
			hamletTempPie['y'] = castSum;
			hamletMainPie.push(hamletTempPie);
			
			myChart.push(hamletTempColumn);
            
		   }
		 }
		constMgmtMainObj.castStatssubArray =subLevelcastInfo;
		if(constMgmtMainObj.castStatssubArray == null || constMgmtMainObj.castStatssubArray.length == 0){
		 
		  return;
		}  

	
		for(var i in constMgmtMainObj.castStatssubArray)
		{
		castTemp.push(constMgmtMainObj.castStatssubArray[i].caste);
		hamletTemp.push(constMgmtMainObj.castStatssubArray[i].mandal);
		}
		
	
	buildHamletWiseCastResultsGraph(null);
	}

var percentage;
function buildHamletWiseCastResultsGraph(selectedCast,percentage)
{  
	var myChart1 = new Array();
	
	if(selectedCast == null)
	  var castMain = sort_unique(castTemp);
	else
	 var castMain = sort_unique(selectedCast);

	 if(percentage != null)
	{
		for(var per=0;per<castePercent.length;per++)
		{
			if(castePercent[per].id < percentage)
			{
				var cIndex = castMain.indexOf(castePercent[per].name);
				if(cIndex != -1)
					castMain.splice(cIndex,1);
			}
		}
	}
	 
	 var avgCal = new Array();
	 var mySort = new Array();
	 var newCast = new Array();
	 
	var newhamletMainPie = new Array();
	var countColor = 0;
		
	//for average calculation
	var avgTemp = new Object();
	avgTemp['type'] = 'spline';
	avgTemp['name'] = 'Average';
	
  
	for( var k in castMain )
	{ 
	var custSort = new Object();
	custSort["cast"] = castMain[k];
	 var avgData = 0;
	 var count = 0;
   
	for(var l in myChart) 
	{
	var reqObj1 = myChart[l];
	var dataObj1 = reqObj1['data'];
	if(dataObj1[castMain[k]]){
	count++;
	 avgData = parseInt(avgData)+parseInt(dataObj1[castMain[k]]);
	
	} else{
	 	 }
	}
	avgCal.push(avgData/parseInt(hamletMainPie.length));//(count));
	custSort["avg"] = avgData/(hamletMainPie.length);//(count));
	mySort.push(custSort);
	}
	mySort.sort(function(a,b) { return parseFloat(b.avg) - parseFloat(a.avg) } );
	avgCal.sort(function(a,b) { return parseFloat(b) - parseFloat(a) } );
  
	avgTemp['data'] = avgCal;
	myChart1.push(avgTemp);
	
	//sorting x axis
	var gruopCast =new Array();
	for (var p in mySort)
	{
	var myObj = mySort[p];
	newCast.push(myObj['cast']);
	
	}
 	var dataGrouping1 = {
    groupPixelWidth: 40,
    units: [[
        'name',
        [1, 2, 3,4,5,6,7]
        ]]
};
	var tempLine = new Array();

	// building column
  for(var i in myChart) 
	{
	var clmTemp = new Object();
	var reqObj = myChart[i];
	//clmTemp['type'] = 'column';
	clmTemp['name'] = reqObj['name'];
	//loop for getting same colors for piechart and bars
	for (var g in hamletMainPie )
	{ 
	  var newHamletTemp = hamletMainPie[g];
	if(newHamletTemp['name'] == reqObj['name'])
	{ 
	countColor = countColor + 1;
	newHamletTemp['color']  = Highcharts.getOptions().colors[countColor];
	newhamletMainPie.push(hamletMainPie[g]);
	}
	}
	 var dataObj= reqObj['data'];
	 var newdataObj = new Array();
	for( var j in newCast )
	{ 
	 if(dataObj[newCast[j]])
	 newdataObj.push(dataObj[newCast[j]]);
	 else{
	 	 newdataObj.push(0);
	 }
	}
	clmTemp['data'] = newdataObj;
	//clmTemp['dataGrouping'] = dataGrouping1;
	    tempLine.push(clmTemp);
		myChart1.push(clmTemp);
	}
	
   //building pie chart
	var objForPie = {
	         type: 'pie',
	         name: 'Total Hamlets',
	         data: newhamletMainPie,
	         center: [800, 50],
	         size: 150,
	         showInLegend: false,
	         dataLabels: {
	            enabled: false
	         }
	      };
		  myChart1.push(objForPie);
     var titleString= $("#sublevelHeading").text();
	
	   //alert(myChart[0]['name']+"---"+myChart[0]['data'].length);
	   var chart1;
	   
	    chart1 = new Highcharts.Chart({
            chart: {
                renderTo: 'castGrid1',
                type: 'line',
				 zoomType: 'x',
                        events: {
                            click: function() {
                                this.xAxis[0].setExtremes();
								                       }
                        }
              
            },
            title: {
                text: titleString,
                x: -20 //center
            },
            subtitle: {
                text: 'Drag Between Any 3 Castes To See In Zoom',
                x: -20
            },
            xAxis: {
               categories: newCast,
				
				 labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                } 
            },
            yAxis: {
                title: {
                    text: 'No of Voters'
                } 
            }, 
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y + ' Voters';
                }
            },
           
            series: tempLine //myChart1 
        });
}

// The function is for slider value -- Created by sasi -- START
var votersRange;
$(function() {
$( "#slider" ).slider({
value:0,
min: 0,
max: 50,
step: 1,
slide: function( event, ui ) {
$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
},
change: function( event, ui ) {
$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
votersRange=ui.value;
buildGraphBySlide();
}
});
votersRange=$( "#amount" ).val( "Percentage of Voters Caste: " + $( "#slider" ).slider( "value" ) +" %");
votersRange=$( "#slider" ).slider( "value" );
});

function buildGraphBySlide(){
buildHamletWiseCastResultsGraph(null,votersRange);
}

// The function is for slider value -- Created by sasi -- END


function sort_unique(a) {
     var temp = {};
    for (var i = 0; i < a.length; i++)
        temp[a[i]] = true;
    var r = [];
    for (var k in temp)
        r.push(k);
    return r;
}

function getAllPublicationDates(){
 jsObj=
		{		
			task:"getAllPublicationDates"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getLatestPublicationIdAction.action?"+rparam;						
		callAjax(jsObj,url);
}
var checkedType = '${checkedType}';
	$(document).ready(function(){
	  
	  if(checkedType == "panchayat"){
	     $("#panchayatChk").attr("checked","checked");
		 
	  }else if(checkedType == "revenueVillage"){
	     $("#revenueVillageChk").attr("checked","checked");
	  }
	});
		function buildVotesPolledDataTable() {
			var resultsDataSourceForTehsil = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get("votesPolledTable"));
			resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
			resultsDataSourceForTehsil.responseSchema = {
				fields : [  {
					key : "townshipName"
				},{
					key : "percentageOfValidVotes",
					parser:YAHOO.util.DataSourceBase.parseNumber
				}]
			};
	        if('${checkedType}' == "panchayat"){
				var resultsColumnDefsForTehsil = [  {
					key : "townshipName",
					label : "Panchayat Name",
					sortable : true
				},{
					key : "percentageOfValidVotes",
					label : "Votes %",
					sortable : true,
					formatter:YAHOO.widget.DataTable.formatFloat
				}];	
		    }else{
              var resultsColumnDefsForTehsil = [  {
				key : "townshipName",
				label : "Township Name",
				sortable : true
			   },{
				key : "percentageOfValidVotes",
				label : "Votes %",
				sortable : true,
				formatter:YAHOO.widget.DataTable.formatFloat
			  }];	
            }		   
			var myDataTableForTehsil = new YAHOO.widget.DataTable("votesPolledDtTableDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);			
		}

	function checkAllBoxes()
	{
		var elements = document.getElementsByTagName('input');

		for(var i =0; i<elements.length; i++)
		{
			if(elements[i].type=="checkbox" && (elements[i].name=="elections" || elements[i].name=="parties"))
			{	
				elements[i].checked=true;
			}
			
		}
	}

	function UncheckAllBoxes()
	{
		var elements = document.getElementsByTagName('input');

		for(var i =0; i<elements.length; i++)
		{
			if(elements[i].type=="checkbox" && (elements[i].name=="elections" || elements[i].name=="parties"))
			{	
				elements[i].checked=false;
			}
		}
	}

	function getConstituencyIds()
	{
		var jsObj=
             {
			     id:mandalId,
				 task:"getConstituencyId",
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getConstituencyByMandalIdAction.action?"+rparam;

		callAjax(jsObj, url);

	}
	function getConstituencieSubs(constituencyId)
	{
		var jsObj=
             {
			     id:constituencyId,
				 task:"subRegionsInConstituency",
				 taskType:"",
				 address:"",
				 areaType:"RURAL",
				 isParliament:"true"
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;

		callAjax(jsObj, url);
	}

	function callAjax(jsObj,url)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								if(jsObj.task == "subRegionsInConstituency"){
									buildMandals(myResults);
								}
								else if(jsObj.task == "getConstituencyId"){
									constituencyId = myResults;
									if(constituencyId != null && constituencyId != "null"){
									getConstituencieSubs(constituencyId);
									 if(checkedType == "panchayat"){
	                                   getAllPublicationDates();
									  }
									}
								}else if(jsObj.task == "getCastInfoForsubLevels"){
								
								castePercent = myResults.castPercent;
								
								$("#castGrid1Title").html("Cast Details By Panchayat Wise In  ${tehsilName} Mandal");
								if(myResults.castVosList != null && myResults.castVosList.length > 0)
								 buildCastInfoForSubLevels(myResults,jsObj);
								else
								 $("#castGrid1").html("<b>No Data Available</b>");
									 //buildCastSubLevelsDiv(myResults);
								}else if(jsObj.task == "getAllPublicationDates"){
								  publicationId = myResults;
								  getCastInfoForsubLevel();
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
	
	function buildMandals(result)
	{
		if(result != null && result.length > 0)
		{
			for(var i in result)
			{
				if(result[i].id != 0)
				{
					var id = new String(result[i].id);
					tehsilId = parseInt(id.substring(1));
					
						$('#mandalSelectId').append('<option value='+tehsilId+'>'+result[i].name+'</option>');
					$('#mandalSelectId').val(mandalId);
				}
			}
			$('#mandalShowHideDiv').show();
		}
		else
		{
			$('#mandalShowHideDiv').hide();
		}
		
	} 
	function getReqTehsilIds()
	{
		tehsilId =  $('#mandalSelectId').val();
		var name  = $("#mandalSelectId option:selected").text();
		var tehsilName = name.replace("MANDAL","");
		$('#reqtehsilId').val(tehsilId);
		$('#reqtehsilName').val(tehsilName);
	}
	getConstituencyIds();
	
</script>
</head>
<body>
<!--<table width="100%" bgcolor="black" cellpadding="0" cellspacing="0">
		<tbody><tr>		
		<!--<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"></td>
			<td width="72%" align="left">
				<table cellspacing="0" cellpadding="0" border="0" style="margin-left:30px;">
				<tbody><tr>
					<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo1.png"></td>
					<td valign="top"><div class="mainHeading" id="mainHead"> All Parties Results In All Elections</div></td>
					<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo2.png"></td>
				</tr>
				</tbody></table>
			</td>
			<!--<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"></td>
		</tr>
	</tbody></table>
	<div align="center"><h3>All Parties Results In All Elections</h3></div>-->
	<div style="color:#247CD4;font-size:19px;font-weight:bold;margin-bottom:23px;margin-top:36px;text-align:center;">All Parties Trends In All Elections Of ${tehsilName } Mandal</div>
	<div class="hero-unit" style="width:750px;margin-left:auto;margin-right:auto;">
	<c:if test="${! empty mandalVO}">
		<s:form action="mandalRevenueVillagesElecViewAction" name="MandalRevenueVillagesElecViewAction" method="GET" enctype="multipart/form-data">
		<div id="mandalShowHideDiv">
			<span>Select Mandal : </span>
			<span><select id="mandalSelectId" onChange="getReqTehsilIds();"></select></span>
		</div>
		<table id="partiesTrendzInputTable">
				
			    <tr>
					<th align="left">Show Result : </th>
					<th colspan="2" align="left"><input type="radio" id="revenueVillageChk" value="revenueVillage" name="resultType" >Revenue Villages Wise <input type="radio" id="panchayatChk" value="panchayat" name="resultType" checked="checked"> Panchayat Wise</th>
				</tr>
				<tr>
					<th align="left">Parties : </th>
					<th colspan="2" align="left"><s:checkboxlist theme="simple" list="mandalVO.partiesInMandal" listKey="id" listValue="name" name="parties" label="Parties:"/></th>
				</tr>
				<tr>
					<th align="left">Elections : </th>
					<th colspan="2" align="left"><s:checkboxlist theme="simple" list="mandalVO.electionsInMandal" listKey="id" listValue="name" name="elections" label="Elections:"/></th>
				</tr>
				<tr>
					<td align="center">
						
					</td>
					<td align="left">
							<s:checkbox theme="simple" id="allianceCheck" name="includeAlliance" value="hasAllianceParties"></s:checkbox><b> Include Aliance Parties</b>
							<input type="button" style="margin-left:0px" class="button" onclick="checkAllBoxes()" value="Select All"/>
							<input type="button" style="margin-left:0px" class="button" onclick="UncheckAllBoxes()" value="DeSelect All"/>
							<s:submit theme="simple" cssClass="button" label="View Chart" />
					</td>
					
				</tr>
				<tr>
					<td><input type="hidden" name="tehsilId" id="reqtehsilId" value="${tehsilId}"/></td>
					<td><input type="hidden" name="tehsilName" id="reqtehsilName" value="${tehsilName}"/></td>
					<td><input type="hidden" name="constituencyId" value="${constituencyId}"/></td>
				</tr>
				<tr><td colspan="2" align="center"></tr>
			</table>
		</s:form>
	</c:if>
	</div>
	<div align="center" style="background:#ffffff;margin-bottom:1px;">
		<c:if test="${! empty chartPath}">
		
			<!--<img src="charts/${chartPath}">-->
			<div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
			<div id="container1" style="width:800px;margin-left:auto;margin-right:auto;background:violet"></div>
		</c:if>
		   <div id="castGrid1Title" style='font-family:"Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif;font-size:16px;color:#274b6d;fill:#274b6d;'></div>
		   <div id="rangeSliderDiv" style="width:500px;margin-left:auto;margin-right:auto;border:1px solid #ccc;padding:5px 20px;margin-top:50px;" >
			<h5 style="text-align:center;">Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
			<div id="slider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a>
			</div>
				<p style="padding-bottom:2px;">
					<input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" />
				</p>
		</div>
	
		   <div id="castGrid1" style="padding-bottom:10px;"></div>
	</div>
	
	<div id="votesPollingInMandalDIV" style="background:#ffffff;" align="center">
		<table id="votesPollingInMandal" width="90%">
			<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">
				<tr>
					<td width="40%" style="vertical-align:top;">
						<div id="votesPolledDtTableDiv_outer" class="yui-skin-sam">
							<div id="votesPolledDtTableDiv" style="padding-top:40px;margin-top:80px;">
								<table id="votesPolledTable">	
									<c:forEach var="votesPolling" items="${votesPollingInMandal.townshipVotingTrends}">
										<tr>
											<td>
												${votesPolling.townshipName}
											</td>
											<td>
												${votesPolling.percentageOfValidVotes}
											</td>	
										</tr>
																											
									</c:forEach>	
								</table>	
							</div>
						</div>	
					</td>
					<td width="60%" style="vertical-align:top;" align="center">			
					
										
						<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">				
							
							
							<script>
								var charttitle='${votesPollingInMandal.chartTitle}'</script>							
							<c:forEach var="ex" items="${votesPollingInMandal.townshipVotingTrends}">
							<script>
								var chartObj={
									name:'${ex.townshipName}',
									percentage:'${ex.percentageOfValidVotes}'
								};
								chartDataArr.push(chartObj);
							</script>
							</c:forEach>
								<table>	
									<tr>	
										<td>
											<!--<img src="charts/${votesPollingInMandal.chartName}">-->
											<div id="chart_div" ></div>
										</td>	
									</tr>	
								</table>							
						</c:forEach>	
						
						
					</td>
				</tr>																				
			</c:forEach>
			
		</table>
		
	</div>	
	
	<script type="text/javascript">
	buildVotesPolledDataTable();
	/* Created By Sasi for HighCharts Start*/
	
	buildGoogleChart();
	
	var seriesValues = new Array();
	function buildGoogleChart(){
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('number', 'Percentage');
		data.addRows(chartDataArr.length);
		
		for(var i=0; i<chartDataArr.length; i++)
		{
			data.setValue(i, 0, chartDataArr[i].name);
	        data.setValue(i, 1, parseFloat(chartDataArr[i].percentage));
		}
        
        // Set chart options
        var options = {'title':charttitle,
                       'width':700,
                       'height':600};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
	}
	
	test();
	
	function test()
	{
	
	    <c:forEach var="a" items="${partiesResults}">
                  if(linechartDataArr.indexOf('${a.constituencyName}') == -1)
					linechartDataArr.push('${a.constituencyName}');
		</c:forEach>
		
	}
	
	buildLineChart();
	
	
    var chart;
	function buildLineChart(){
    
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'line',
               /* marginRight: 130,
                marginBottom: 25 */
            },
            title: {
                text: 'Voting Percentages in ${tehsilName } Mandal',
                x: -20 //center
            },
           /* subtitle: {
                text: 'Source: WorldClimate.com',
                x: -20
            },*/
            xAxis: {
                categories: linechartDataArr,
				
				 labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                title: {
                    text: 'Votes Percent( % )'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'%';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [
			
			<c:forEach var="entry" items="${partyResultMapPrcnt}" varStatus="loop">
		      {
				name: '<c:out value="${entry.key}"/>',
				data: <c:out value="${entry.value}"/>
			  }<c:if test="${!loop.last}">,</c:if>
		    </c:forEach>
			]
        });
		
		$('tspan:last').hide();
    }
    
/* Created By Sasi for HighCharts End*/
function getCastInfoForsubLevel(){
    var jsObj=
		{		
				type:"mandal",	
				id:2${tehsilId},
				typeName:"${tehsilName}",
				publicationDateId:publicationId,
				constituencyId:constituencyId,
                buildType:"hamlet",
				resultFor:"",
                queryType:"sub",
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
</script>
	
	
	
	
</body>
</html>