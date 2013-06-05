<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script>  
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css -->  
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<!-- YUI Dependency files (End) -->
<!-- <script type="text/javascript" src="js/highcharts/js/highcharts.js"></script> -->

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<script type="text/javascript" src="js/myCustChart.js"></script>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<title>Party Analyst</title>
<style type="text/css">
#customVoterAgeMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 980px;}
	.tableCls th{text-align:center;background-color: #D9EDF7;
    font-family: verdana;
    font-size: 14px;
    font-weight: normal;
    text-align: center;}
.ageTable
  {
	width: 104%; 
	max-width: 104%; 
	margin: 1px -18px 1px 10px;
	border: 2px solid powderblue;
}
table.dataTable tr.odd {
    background-color: #ffffff;
}
table.dataTable tr.even {
    background-color:#EdF5FF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #ffffff;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #EdF5FF;
}
.dataTables_filter {
    padding:5px;
}

table.dataTable td {
    font-family: arial;
    font-size: 16px;
    font-weight: normal;
    padding: 3px 10px;
}
#customVoterAgeMainDiv{ margin-top: 40px;margin-bottom: 60px;}
#groupWiseAgeAndGenderTbl{width:100%;}
/* #customGroupAgeAndGenderDiv,#customGroupGenderDiv{margin-top: 28px;}*/
.customGroupAgeGraphDiv{border: 1px solid #000000;margin-bottom: 22px;width: 985px;}

#headingId {
    background-color: #05A8E9;
    border-radius: 4px 4px 4px 4px;
    color: snow;
    font-family: arial;
    height: 27px;
    margin-left: 4px;
    padding-top: 11px;
    width: auto;
}
</style>
<script type="text/javascript">

var areaType = "${areaType}";
var constituencyId = "${constituencyId}";
var publicationDateId = "${publicationDateId}";
var locationValue = "${locationValue}";
var locationName = "${locationName}";


</script>
</head>
<body>
<div id="customVoterAgeMainDiv">

<div id="ajaxImageDiv" align="center" style="margin-top: 100px;display:none;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>

<div id="customGroupAgeGraphDiv"></div>

 <div id="voterAgeAngGenderwiseDetailsNote" class="noteDiv thumbnail breadcrumb" style="text-align:center;"></div>
 <div class="thumbnail" style="margin:15px 10px;">
     <div id="customGroupAgeDiv"></div>
 </div>
 <div class="thumbnail" style="margin:15px 10px;">
   <div id="customGroupAgeAndGenderDiv"></div>
 </div>
 <div class="thumbnail" style="margin:15px 10px;">
   <div id="customGroupGenderDiv"></div>
 </div>
</div>

<script type="text/javascript">

function getCustomVoterAgeDetails()
{
   $("#ajaxImageDiv").css("display","block");
   var jsObj=
   {
	  constituencyId:constituencyId,
	  publicationDateId:publicationDateId,
	  id:locationValue,
	  areaType:areaType,
	  task:"getCustomVoterAgeDetails"
   };
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getCustomVoterAgeDetailsAction.action?"+rparam;	
   callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
  var myResults;
  var callback = {			
 		success : function( o ) {
		   try {												
			 myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "getCustomVoterAgeDetails")
			    {
				  $("#ajaxImageDiv").css("display","none");
				  buildGraphForAgeDetails(myResults);
				  buildCustomGroupAgeWisePercentageDetails(myResults,jsObj);
				  buildCustomGroupAgeDetails(myResults,jsObj);
				  buildCustomGroupAgeWiseGenderDetails(myResults,jsObj);
				  				 	  
			    }

					
				 }catch (e) {
							     
							}  
 		        },
 		           scope : this,
 		            failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		           };

 	YAHOO.util.Connect.asyncRequest('POST', url, callback);
 }

function buildCustomGroupAgeDetails(results,jsObj)
{
  $("#customGroupAgeDiv").html('');
  if(results == null || results.length == 0)
  {
	//$("#customGroupAgeDiv").html('No Data Found'); 
	return;
  }
  var str = '';
   str +='<table class="ageTable table table-bordered table-hover dataTable tableCls">';
   str +='<tr>';
   str +='<th rowspan="2">Group Name</th>';
   str +='<th rowspan="2">Total Voters</th>';
   str +='<th colspan="2">18-25</th>';
   str +='<th colspan="2">26-35</th>';
   str +='<th colspan="2">36-45</th>';
   str +='<th colspan="2">46-60</th>';
   str +='<th colspan="2">60-Above</th>';
   str +='</tr>';
   str +='<tr>';
   str +='<th>Voters</th>';
   str +='<th>%</th>';
   str +='<th>Voters</th>';
   str +='<th>%</th>';
   str +='<th>Voters</th>';
   str +='<th>%</th>';
   str +='<th>Voters</th>';
   str +='<th>%</th>';
   str +='<th>Voters</th>';
   str +='<th>%</th>';
   str +='</tr>';
  for(var i in results)
  {
    str +='<tr>';
	str +='<td>'+results[i].name+'</td>';
	str +='<td>'+results[i].totalVoters+'</td>';
	str +='<td>'+results[i].totalVotersFor18To25+'</td>';
	str +='<td>'+results[i].votersPercentFor18To25+'</td>';
	str +='<td>'+results[i].totalVotersFor26To35+'</td>';
	str +='<td>'+results[i].votersPercentFor26To35+'</td>';
	str +='<td>'+results[i].totalVotersFor36To45+'</td>';
	str +='<td>'+results[i].votersPercentFor36To45+'</td>';
	str +='<td>'+results[i].totalVotersFor46To60+'</td>';
	str +='<td>'+results[i].votersPercentFor46To60+'</td>';
	str +='<td>'+results[i].totalVotersForAbove60+'</td>';
	str +='<td>'+results[i].votersPercentForAbove60+'</td>';
	
	str +='</tr>';
  }
  $("#customGroupAgeDiv").html(str);
  //$('#customGroupAgeTable').dataTable();
}

function buildCustomGroupAgeWiseGenderDetails(results,jsObj)
{
  $("#customGroupAgeAndGenderDiv").html('');
  if(results == null || results.length == 0)
  {
	//$("#customGroupAgeAndGenderDiv").html('No Data Found'); 
	return;
  }
  var str = '';
   str +='<table id="groupWiseAgeAndGenderTbl" class="ageTable table table-bordered table-hover dataTable tableCls">';
   str +='<tr>';
   str +='<th rowspan="2">Group Name</th>';
   str +='<th colspan="2">18-25</th>';
   str +='<th colspan="2">26-35</th>';
   str +='<th colspan="2">36-45</th>';
   str +='<th colspan="2">46-60</th>';
   str +='<th colspan="2">60-Above</th>';
   str +='</tr>';
   str +='<tr>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='</tr>';
  for(var i in results)
  {
    str +='<tr>';
	str +='<td>'+results[i].name+'</td>';
	str +='<td>'+results[i].maleVotersCountBetween18To25+'</td>';
	str +='<td>'+results[i].femaleVotersCountBetween18To25+'</td>';
	str +='<td>'+results[i].maleVotersCountBetween26To35+'</td>';
	str +='<td>'+results[i].femaleVotersCountBetween26To35+'</td>';
	str +='<td>'+results[i].maleVotersCountBetween36To45+'</td>';
	str +='<td>'+results[i].femaleVotersCountBetween36To45+'</td>';
	str +='<td>'+results[i].maleVotersCountBetween46To60+'</td>';
	str +='<td>'+results[i].femaleVotersCountBetween46To60+'</td>';
	str +='<td>'+results[i].maleVotersCountAbove60+'</td>';
	str +='<td>'+results[i].femaleVotersCountAbove60+'</td>';
	str +='</tr>';
  }
  $("#customGroupAgeAndGenderDiv").html(str);
  
  $('#groupWiseAgeAndGenderTbl').dataTable({
		"aaSorting": [[ 1, "desc" ]]
		});

}

function buildCustomGroupAgeWisePercentageDetails(results,jsObj)
{
  
  $("#voterAgeAngGenderwiseDetailsNote").html('');
  
  $("#voterAgeAngGenderwiseDetailsNote").html('<h4 id="headingId">Age wise voter groups details in '+locationName+'</h4>');
  $("#customGroupGenderDiv").html('');
  if(results == null || results.length == 0)
  {
	$("#customGroupGenderDiv").html('No Data Found'); 
	return;
  }
   var str = '';
   str +='<table class="ageTable table table-bordered table-hover dataTable tableCls">';
   str +='<tr>';
   str +='<th rowspan="2">Group Name</th>';
   str +='<th colspan="3">18-25</th>';
   str +='<th colspan="3">26-35</th>';
   str +='<th colspan="3">36-45</th>';
   str +='<th colspan="3">46-60</th>';
   str +='<th colspan="3">60-Above</th>';
   str +='</tr>';
   str +='<tr>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Total</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Total</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Total</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Total</th>';
   str +='<th>Male</th>';
   str +='<th>Female</th>';
   str +='<th>Total</th>';
   str +='</tr>';
  for(var i in results)
  {
    str +='<tr>';
	str +='<td>'+results[i].name+'</td>';

	str +='<td>'+results[i].maleVotersPercentFor18To25+'</td>';
	str +='<td>'+results[i].femaleVotersPercentFor18To25+'</td>';
	str +='<td>'+results[i].totalVotersFor18To25+'</td>';

	str +='<td>'+results[i].maleVotersPercentFor26To35+'</td>';
	str +='<td>'+results[i].femaleVotersPercentFor26To35+'</td>';
	str +='<td>'+results[i].totalVotersFor26To35+'</td>';

	str +='<td>'+results[i].maleVotersPercentFor36To45+'</td>';
	str +='<td>'+results[i].femaleVotersPercentFor36To45+'</td>';
	str +='<td>'+results[i].totalVotersFor36To45+'</td>';

	str +='<td>'+results[i].maleVotersPercentFor46To60+'</td>';
	str +='<td>'+results[i].femaleVotersPercentFor46To60+'</td>';
	str +='<td>'+results[i].totalVotersFor46To60+'</td>';

	str +='<td>'+results[i].maleVotersPercentForAbove60+'</td>';
	str +='<td>'+results[i].femaleVotersPercentForAbove60+'</td>';
	str +='<td>'+results[i].totalVotersForAbove60+'</td>';

	str +='</tr>';
  }
  str +='</table>';
  $("#customGroupGenderDiv").html(str);

  
}

function buildGraphForAgeDetails(results)
{
  if(results == null || results.length == 0)
	return;
  
  $("#customGroupAgeGraphDiv").html('');
  $("#customGroupAgeGraphDiv").addClass("customGroupAgeGraphDiv");
  var xAxisArray = new Array("18-25","26-35","36-45","46-60","60-Above");
  var YDataObject = new Array();
  
  for(var i in results)
  {
	 var YDataObjectTemp = new Object();
	 var ageTemp = new Object();
	 YDataObjectTemp['name'] = results[i].name;

     ageTemp['18-25'] = results[i].totalVotersFor18To25;
     ageTemp['26-35'] = results[i].totalVotersFor26To35;
	 ageTemp['36-45'] = results[i].totalVotersFor36To45;
	 ageTemp['46-60'] = results[i].totalVotersFor46To60;
	 ageTemp['60-Above'] = results[i].totalVotersForAbove60;

	 YDataObjectTemp['data'] = ageTemp;
     YDataObject.push(YDataObjectTemp);
  }

  var tempLine = new Array();
  for(var i in YDataObject)
  {
	var tempvar = YDataObject[i];

    var dataObj = tempvar['data'];
	var newObj = new Array();

	var tempObj = new Object();
   
	for(var j in xAxisArray)
	{
	 if(dataObj[xAxisArray[j]]){
      newObj.push(dataObj[xAxisArray[j]]);
	 }
	else{
	 newObj.push(0);
	 }
	}
	
	tempObj['name'] = tempvar['name'];
    tempObj['data'] = newObj;
    
	tempLine.push(tempObj);

  }
	
 var chart1;

chart1 = new Highcharts.Chart({
  chart: {
        renderTo: "customGroupAgeGraphDiv",
                type: 'line',
				 zoomType: 'x',
                        events: {
                            click: function() {
                                this.xAxis[0].setExtremes();
								                       }
                        }
                   },
            title: {
                text: "Age wise Voter Groups Analysis",
                x: -20 //center
            },
            subtitle: {
                text: 'Drag Between Any 3 Points To See In Zoom',
                x: -20
            },
            xAxis: {
               categories: xAxisArray,
				
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
				min:0,
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
             series: tempLine 
        });
   
   
}



getCustomVoterAgeDetails();
</script>

</body>
</html>