<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> TDP News Portal </title>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> 

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>

<style type="text/css">
#candidateNewsCountDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#candidateNewsCountDiv table tr:nth-child(even){background:#EdF5FF;}
#candidateNewsCountDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#candidateNewsCountDiv table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}
#candidateNewsMainDiv{margin-left: auto; margin-right: auto; float: none; width: 950px; padding-top: 20px; padding-bottom: 20px;}

</style>

</head>

<body>

<div id="candidateNewsMainDiv">
<div id="candidateNewsCountDiv"></div>
<div id="container"></div>
</div>


<script type="text/javascript">

function getCandidateNewsCount()
{
	var jsObj={
		candidateId:0,
		fromDate:"",
		toDate:"",
	    task:'getLocationWiseNewsCountForACandidate'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocationWiseNewsCountForACandidateAction.action?"+rparam;
	callAjax(jsObj, url);
}

function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);					
			if(jsObj.task == "getLocationWiseNewsCountForACandidate")
			{
				buildCandidateNews(myResults,jsObj);
			}
			}catch (e)
			{
							     
			}  
 		},
 		scope : this,
		failure : function( o ) 
		{
			//alert( "Failed to load result" + o.status + " " + o.statusText);
		}
	   };

 	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function buildCandidateNews(results,jsObj)
{
  $("#candidateNewsCountDiv").html('');
  if(results == null)
  {
	$("#candidateNewsCountDiv").html('No Data Found');
	return;
  }

  var str = '';
  str +='<table id="newsCountTab">';
  str +='<tr>';
  str +='<th>Candidate Name</th>';
  str +='<th>State</th>';
  str +='<th>District</th>';
  str +='<th>Constituency</th>';
  str +='<th>Mandal</th>';
  /* str +='<th>village</th>';
  str +='<th>Muncipality</th>';
  str +='<th>Ward</th>';
  str +='<th>Booth</th>';*/

  str +='</tr>';
  for(var i in results)
  {
   str +='<tr>';
   str +='<td>'+results[i].name+'</td>';
   str +='<td><a href="javascript:{}" onclick="getLocationWiseNewsDetails(\''+results[i].id+'\',\'state\')">'+results[i].stateNewsCount+'</a></td>';
   str +='<td>'+results[i].districtNewsCount+'</td>';
   str +='<td>'+results[i].constituencyNewsCount+'</td>';
   str +='<td>'+results[i].mandalNewsCount+'</td>';
   /* str +='<td>'+results[i].villageNewsCount+'</td>';
   str +='<td>'+results[i].localEleBodyNewsCount+'</td>';
   str +='<td>'+results[i].wardNewsCount+'</td>';
   str +='<td>'+results[i].boothNewsCount+'</td>';*/
   str +='</tr>';
  }
 str +='</table>';
 $("#candidateNewsCountDiv").html(str);
 buildChart(results);
}

function getLocationWiseNewsDetails(candidateId,locationScope)
{
  /* var jsObj={
		candidateId:candidateId,
		fromDate:"",
		toDate:"",
		locationScope:locationScope,
		firstResult:0,
	    maxResult:10,
	    task:'getLocationWiseNewsDetailsForACandidate'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocationWiseNewsDetailsForACandidateAction.action?"+rparam;
	callAjax(jsObj, url);*/
}
function buildChart(results)
{
	var dataarr = [];
	var xaxis = results[0].candidateNames;
	if(results[0].stateCounts != null)
	{
		var obj = {
		name: 'State',
        data: results[0].stateCounts
		}
		dataarr.push(obj);
	}
	if(results[0].districtCounts != null)
	{
		var obj1 = {
		name: 'District',
        data: results[0].districtCounts
		}
		dataarr.push(obj1);
	}
	if(results[0].constituencyCounts != null)
	{
		var obj3 = {
		name: 'Constituency',
        data: results[0].constituencyCounts
		}
		dataarr.push(obj3);
	}
	if(results[0].mandalCounts != null)
	{
		var obj4 = {
		name: 'Mandal',
        data: results[0].mandalCounts
		}
		dataarr.push(obj4);
	}
	dataarr.push();
	
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Regionwise News Count'
            },
            
            xAxis: {
                categories: xaxis,
					labels: {
				rotation: -70,
				align: 'right',
				style: {
				fontSize: '13px',
				fontFamily: 'Verdana, sans-serif'
				}

				}
            },
            yAxis: {
               
				
                min: 0,
               
               /* title: {
                    text: 'Rainfall (mm)'
                }*/
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
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
           
			 series:dataarr
        });
}
    

getCandidateNewsCount();
</script>
</body>
</html>