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
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!--<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>-->
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
	
	<link href="styles/assets/css/bootstrap.css" rel="stylesheet" />
		

	<!-- YUI Dependency files (End) -->
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
</script>


<style>
.descriptionInnerDiv{margin-left: 6px; font-size: 13px; line-height: 1.7em;}
.descriptionInnerDiv span{margin-right: 5px;}
.yuiTableCss table{
	border:1px solid #d3d3d3;
	border-collapse:collapse;
	padding:10px;
	margin-left:auto;
	margin-right:auto;
	width:100%;
	margin-top:5px;
	margin-bottom:5px;
}

.yuiTableCss table tr:nth-child(even){
	background:#EdF5FF;
}
.yuiTableCss table  tr:nth-child(odd){background:#ffffff;}

.yuiTableCss table th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
.yuiTableCss table a{
	color:#0088CC;
	text-decoration:none;
}
.yuiTableCss table td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
/*
#impfamilydatatable.dataTable tr.odd {background-color: #FFF !important;}
#impfamilydatatable.dataTable tr.even td.sorting_1 {background:#DBEBFF !important;}
#impfamilydatatable.dataTable tr.odd td.sorting_1{background:#FFF !important;}
#impfamilydatatable.dataTable td {padding: 7px 10px;}
#impfamilydatatable{border:1px solid #000;}
#impfamilydatatable.dataTable thead th {background: none repeat scroll 0 0 #DBEBFF;padding: 10px 18px 10px 10px;}
#impfamilydatatable.dataTable tr.even{background:#DBEBFF !important;}


#impFamDtls table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}


#impFamDtls table tr:nth-child(even){background:#EdF5FF;}

#impFamDtls table tr:nth-child(odd){background:#ffffff;}
#impFamDtls table th{ background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}*/

</style>
</head>
<body>

<h4 style="text-align:center;margin-top:10px;font-size:18px;" class="breadcrumb">FAMILY WISE  VOTER GROUPS DETAILS IN <s:property value="groupName"/>
</h4>
<div id="impFamilesBasicInfoSubChartDiv" class="widget blue" style="margin-left:21px;width:922px;"></div>

<div id="impFamilesBasicSubDetailsDiv" class="widget blue whitegloss" style="width:926px;margin-left:auto;margin-right:auto;padding-bottom:10px;">
<h4 id="impFamilesBasicSubDetailsTitle">Custom Voter Group Wise Voters Family analysis of <s:property value="groupName"/> </h4>
<div id="impFamiliesDetailsForCustomVoterGroups" class="yuiTableCss"></div>
</div>

<div class="descriptionInnerDiv" id="descriptionsDiv" style="margin-left:23px;display:none;">
	<span> <b> &lt; 3 -</b> No Of Families Having Below 3 Voters</span><br>
	<span> <b>&lt;3 % -</b> No Of Families Having Below 3 Voters In Percent</span><br>
	<span> <b>4 to 6 -</b> No Of Families Having 4 to 6 Voters</span><br>
	<span> <b>4 to 6 % -</b>No Of Families Having 4 to 6 Voters In Percent</span><br>
	<span> <b>7 to 10 -</b> No Of Families Having 7 to 10 Voters</span><br>
	<span> <b>7 to 10 % -</b> No Of Families Having 7 to 10 Voters In Percent</span><br>
	<span> <b>&gt;10 - </b> No Of Families Having Above 10 Voters</span><br>
	<span> <b>&gt;10 % -</b> No Of Families Having Above 10 Voters In Percent</span><br>
</div>

<script>
getCustomVoterGroupsFamiliesDetails();

var constituencyId = '${constituencyId}';
var locationValue = '${locationValue}';
function getCustomVoterGroupsFamiliesDetails()
{  
    var jsObj=
    {
	  constituencyId:constituencyId,
	  locationValue:'${locationValue}',
	  task:"getCustomVoterGroupsFamilyDetails"
   };
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getCustomVoterFamilyDetailsForMandalOrMuncipality.action?"+rparam;	
   callAjaxForFamiliesDetails(jsObj,url);

}

function callAjaxForFamiliesDetails(jsObj,url)
{
  var myResults;
  var callback = {			
 		success : function( o ) {
		   try {												
			 myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "getCustomVoterGroupsFamilyDetails")			    
				buildCustomVoterGroupsFamilyDetails(myResults);
				buildImpFamilesChartForCustomVoterGroups(myResults);
					
				 }catch (e) {
							     
							}  
 		        },
 		           scope : this,
 		            failure : function( o ) 
					{
 		            }
 		           };

 	YAHOO.util.Connect.asyncRequest('POST', url, callback);
 }

 function buildCustomVoterGroupsFamilyDetails(myResults)
 {

	 if(myResults == null || myResults.length == 0)
	 {
		 $('#descriptionsDiv').hide();
		 return false;
	 }

	 $('#descriptionsDiv').show();

	 var impFamilesColumnDefs = [
    {key:"name", label: "Custom Voter Group Name", sortable: true},
	{key:"totalVoters", label:"Total",sortable: true},
	{key:"totalMaleVoters", label:"Male Voters",sortable: true},
	{key:"totalFemaleVoters", label:"Female Voters",sortable: true},
    {key:"below3", label: "<3", formatter:"number", sortable: true},
    {key:"below3perc", label: "<3 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn4to6", label: "4 to 6", formatter:"number", sortable: true},
    {key:"betwn4to6perc", label: "4 to 6%", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn7to10", label: "7 to 10", formatter:"number", sortable: true},
    {key:"betwn7to10perc", label: "7 to 10 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"above10", label: ">10", formatter:"number",sortable:true},
    {key:"above10perc", label: ">10 %", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}
  ];
  
var impFamilesDataSource = new YAHOO.util.DataSource(myResults.subList);
impFamilesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
impFamilesDataSource.responseSchema = {
fields: [{key:"name"},{key:"below3", parser:"number"},{key:"totalVoters"},{key:"totalMaleVoters"},{key:"totalFemaleVoters"},{key:"below3perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn4to6", parser:"number"},{key:"betwn4to6perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn7to10", parser:"number"},{key:"betwn7to10perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"above10", parser:"number"},{key:"above10perc", parser:YAHOO.util.DataSourceBase.parseNumber}]
};


var myConfigs = {
};
var impFamilesDataTable = new YAHOO.widget.DataTable("impFamiliesDetailsForCustomVoterGroups", impFamilesColumnDefs,
impFamilesDataSource, myConfigs);
return {
oDS: impFamilesDataSource,
oDT: impFamilesDataTable
};
}

function buildImpFamilesChartForCustomVoterGroups(chartInfo) {


	//$("#ajaxImageDiv").css('display','none');
	var data = google.visualization.arrayToDataTable([
			  ['Task', 'Percentage'],
			  ['Families Below 3 Voters',  chartInfo.below3perc],
			  ['Families Between 4-6 Voters', chartInfo.betwn4to6perc],
			  ['Families Between 7-10 Voters',  chartInfo.betwn7to10perc],
			  ['Families Above 10 Voters', chartInfo.above10perc]
			]);

	
		title= " Family Wise Voters details chart for custom voter groups ";
	var options = {'title':title,
	'width':958,
	'height':280};
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('impFamilesBasicInfoSubChartDiv'));
	chart.draw(data, options);


}

 
</script>
</body>
</html>