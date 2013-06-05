<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<!-- <script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>-->
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
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
 <script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
 <script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>

<title>Local Area Wise Voters Info</title>
<style type="text/css">

#votersBasicInfoSubDiv{
  margin-left: 0px;
  padding:10px;
  font-family : arial;
}
#votersBasicInfoSubChartDiv{
  margin-left: 0px;
}
#votersBasicInfoSubDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#votersBasicInfoSubDiv table tr:nth-child(even){background:#EdF5FF;}
#votersBasicInfoSubDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#votersBasicInfoSubDiv table th{
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

	#votersBasicInfoSubDiv table th a{
color:#333333;
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
.table-bordered {
    border-collapse: separate;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
    
}
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }
#titleDiv{background: none repeat scroll 0 0 #06ABEA;
    border-radius: 3px;
    color: #FFFFFF;
    font-size: 14px;
	padding: 4px;
    width: 550px;
	font-family: verdana;
    font-weight: bold;
    margin-bottom: 14px;
    margin-left: 10px;
    margin-top: 12px;
	}
	#votersBasicInfoSubChartDiv,#assAndUnass,#votersBasicInfoSubDiv{ margin-left: 10px;
    margin-right: 5px;}
	#votersBasicInfoSubDiv{margin-bottom:20px;}
	#subLevelTable{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;background:#EDF5FF !important;}
#subLevelTable th{
background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
	}
#subLevelTable tr:nth-child(even){background:#EDF5FF  !important;}
#subLevelTable tr:nth-child(odd){background:#EDF5FF !important;}
#subLevelTable td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
table.dataTable tr.odd td.sorting_1 {
    background-color: #EDF5FF !important;
}
table.dataTable tr.odd {
    background-color: #EDF5FF !important;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #DAEDFF !important;
	<!-- background-color: #EDF5FF; -->
}
}

table.dataTable thead th {
    background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
    border-bottom: 1px solid black;
    cursor: pointer;
    font-weight: bold;
    padding: 3px 18px 3px 10px;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #DBEAFF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #EDF5FF;
}
table.dataTable thead th {
    background: none repeat scroll 0 0 #D9EDF7;
    border-bottom: 1px #DDDDDD ;
    color: #454545;
    cursor: pointer;
    font-weight: bold;
    padding: 3px 18px 3px 10px;
}
#ageWiseDetailstable th{background:#CDE6FC ! important;}
#ageWiseDetailsDiv
{
    border-radius: 4px 4px 4px 4px;
    margin-left: 10px;
	margin-top: 17px;
    padding: 11px 10px 28px;
	
}

</style>

<script type="text/javascript">

	google.load("visualization", "1", {packages:["corechart"]});

 var id = '${id}';
 var type = '${type}';
 var publicationDateId  = '${publicationId}';
 var resultFor = '${resultFor}';
 var constituencyId = '${constituencyId}';
 var typename = '${typename}';
 var buildType = '${buildType}';
 var buttonType = '${buttonType}';
 var publicationYear = '${publicationDate}';
 var castePercent = [];
 var casteChartHeading = '';
 $(document).ready(function()
{
 $('#castesAsPerLocId').live('click', function(event) {        
         $('#castGrid2Outer').toggle('show');
		 //$(this).val(if($((this).val()))
    });
	if(type == "customWard" && resultFor=="localArea")
{
$("#localCastStatsTabContent_subbody").show();
 $('#casteDetailsDiv').show();
}
else
	{
	$("#localCastStatsTabContent_subbody").hide();
	 $('#casteDetailsDiv').hide();
	}
});
$('#castesAsPerLocId').click(function(){
	buildCastGrid2(null,null);
});

 </script>

</head>
<body>

<div id="localAreaWiseVoterInfoMainDiv">

<div id="ajaxImageDiv" style="margin-top:30px;margin-left:50px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>

	<div id="votersBasicInfoMainDiv">
	<div id="titleDiv"></div>
		<div id="votersBasicInfoMsgDiv"></div>
		<div id="wardSearchDiv" style="float: right; padding: 10px;display:none;margin-top:-48px;"><b>Select Ward : </b><select id="wardsList" onchange="getDetailsForSelectedWard();"></select></div>
		<div id="votersBasicInfoSubChartDiv"></div>
	</br>
		<div id="assAndUnass"></div>
		<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable"></div>

</div>
<div style="border:1px solid;" id="casteDetailsDiv">
<div id="casteSelectDiv" style="text-align:center;border:1px solid #ccc;"></div>
<div id="rangeSliderDiv" style="width:500px;margin:20px auto;border:1px solid #ccc;padding:10px 20px;" >
<h5>Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
<div id="slider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a></div>

<p>
<input type="text" id="amount" readonly="readonly" style="border: 0; color: #f6931f; font-weight: bold;" />
</p>
</div>
<div id="ajaxImageDiv1" align="center">
<img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> 
</div>
<div id="castGrid1" style="height: 500px; display: block; overflow-x: auto;"></div>	
 
  <div style="margin-left:auto;margin-right:auto;width:350px;margin-bottom:10px;">
	<span class="label label-info btn btn-info" style="padding:5px;margin-left:85px;" id="show_hide_show">Show All </span>
	<span class="label label-info btn btn-info" style="padding:5px;" id="show_hide_hide">Hide All </span>	
  </div>
  <div style=" margin-bottom: 10px;">
  <span class="btn btn-info " id="castesAsPerLocId" style="margin-top: 2px; margin-left: 650px;"> Show/Hide  Caste Wise Voters As Per Location</span>
  </div>
</div>
<div style="border-bottom:1px solid;border-left:1px solid;border-right:1px solid;display:none;" id="castGrid2Outer">
<div id="casteSelectDiv1" style="text-align:center;border:1px solid #ccc;"></div>
<div id="rangeSliderDiv" style="width:500px;margin:20px auto;border:1px solid #ccc;padding:10px 20px;" >
<h5>Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
<div id="slider1" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a></div>

<p>
<input type="text" id="amount1" readonly="readonly" style="border: 0; color: #f6931f; font-weight: bold;" />
</p>
</div>

<div id="castGrid2" style="height: 750px; overflow-x: auto;display:inline-block;"></div>	
<div style="margin-left:auto;margin-right:auto;width:200px;margin-bottom:10px;">
	<span class="label label-info btn btn-info" style="padding:5px;" id="show_hide_show1">Show All </span>
	<span class="label label-info btn btn-info" style="padding:5px;" id="show_hide_hide1">Hide All </span>
</div>
</div>


<div id='localCastStatsTabContent_subbody'  class="yui-skin-sam yui-dt-sortable widget blue"></div>
</div>

<div id="container1"></div>

<div id="instructionDialog" ></div>	

<div id="votersAgenformationDiv" class="widget blue whitegloss" style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; width: 949px;display:none;">

	
<div id="ageWiseDetailsDiv" class="yui-skin-sam yui-dt-sortable"><h4 id="ageWiseHeading"> etyrfutyi tyrty</h4></div></div>
</div>

<script type="text/javascript">
var temp = '${resultFor}';
function getDetailsForSelectedWard()
{
	
	id 		 = $('#wardsList option:selected').val();
	mainname = $('#wardsList option:selected').text();
	var urlStr ="localAreaWiseVotersInfoAction.action?id="+id+"&publicationId="+publicationDateId+"&type="+type+"&resultFor="+resultFor+"&constituencyId="+constituencyId+"&buildType="+buildType+"&typename="+mainname+"&publicationDate="+publicationYear+" ";
		
	 window.location.href = urlStr;	
}
function getLocalAreaWiseVotersInfo()
{
	if(temp == "muncipalityCustomWard")
		buildType = temp;

  var jsObj=
			{
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				year:publicationYear,
				typename:typename,
				constituencyId:constituencyId,
                buildType:buildType,
				resultFor:'${resultFor}',
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;	
		callAjax(jsObj,url);
	

}

/*
	This Method is used for ajax call handling for given responce
*/

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "votersbasicinfo")
								{    
								  if(jsObj.type=="panchayat")
								   {
								     if(jsObj.buildType != buildType )
								     return;
								   }
								   if(jsObj.type=="hamlet")
								   {
									//hideAjaxImgDiv("ajaxImageDiv1");	
									  if( jsObj.resultFor == "booth")
									  $("#assAndUnass").html('');  							
								   }
								   if(myResults != null)
									 buildVotersBasicInfo(myResults,jsObj);
								   else{
									 $("#votersBasicInfoSubChartDiv").removeAttr('style');
									 $("#votersBasicInfoSubDiv").removeAttr('style');
								   }
									 
								}
								 if(jsObj.task == "getCastInfoForsubLevels")
									{  
									castePercent = myResults.castPercent;
									buildCastInfoForSubLevels(myResults,jsObj,null,null);
									buildCastSubLevelsDiv(myResults,jsObj);
									buildCastSubLevelsDiv1(myResults,jsObj);
									//buildCasteData(myResults,jsObj);
									}
									else if(jsObj.task == "getWardsList")
								{
									buildWardsList(myResults);
								}
								else if(jsObj.type == "customWardLocalArea")
								{
									buildAgewiseDetails(myResults,jsObj);
								}
									}catch (e) {}  
 		},
 		scope : this,
 		failure : function( o ) {
 		    //alert( "Failed to load result" + o.status + " " + o.statusText);
        }
 		};

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}


function buildAgewiseDetails(myResults,jsObj)
{
	
	
	var result = myResults.boothVotersDetails;
	if(result != null)
	{
		$("#votersAgenformationDiv").show();
    
	var str = '';
	str+='<h4 id="sublevelHeading">LocalArea Wise voter age details of ${typename} in ${publicationDate}</h4>';
	str += '<table class="table table-bordered table-striped table-hover" id= "ageWiseDetailstable">';
	str += '<thead class="info">';
	str+='<tr>';
	str+='<th rowspan="2">LocalArea</th>';
	str+='<th rowspan="2">Total Voters</th>';
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='</tr>';
	str+='</thead>';
	
	str+='<tbody>';
	for(var i in result)
		{
			str+='<tr>';
			str+='<td>'+result[i].localityName+'</td>';
			str+='<td>'+result[i].totalVoters+'</td>';
			str+='<td>'+result[i].totalVotersFor18To25+'</td>';
			str+='<td>'+result[i].votersPercentFor18To25+'</td>';
			str+='<td>'+result[i].totalVotersFor26To35+'</td>';
			str+='<td>'+result[i].votersPercentFor26To35+'</td>';
			str+='<td>'+result[i].totalVotersFor36To45+'</td>';
			str+='<td>'+result[i].votersPercentFor36To45+'</td>';
			str+='<td>'+result[i].totalVotersFor46To60+'</td>';
			str+='<td>'+result[i].votersPercentFor46To60+'</td>';
			str+='<td>'+result[i].totalVotersForAbove60+'</td>';
			str+='<td>'+result[i].votersPercentForAbove60+'</td>';
			str+='</tr>';
		}
		str += '</tbody>';
		str += '</table>';
		$('#votersAgenformationDiv').html(str);
		$('#ageWiseDetailstable').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"bDestroy": true,
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
		});
	}
	}
	

function buildVotersBasicInfo(votersbasicinfo,jsObj)
{ 
	
      $("#votersBasicInfoSubChartDiv").html('');
	  $("#votersBasicInfoSubDiv").html('');
	  $("#titleDiv").html('');
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
	$("#votersInfoAjaxImg").css("display","none");
	  //$("#votersBasicInfoSubChartDiv").removeAttr('style');
	  //$("#votersBasicInfoSubDiv").removeAttr('style');

	var str = '<div id="votersBasicInfoDivSub">';
	var title = " Voters Basic Information of "+jsObj.typename+" in "+jsObj.year+"";
	if(votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0)
	{
		if(jsObj.type == "constituency")
			title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" wise Voters Information in "+jsObj.typename+" Constituency";
		else
		 title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" wise Voters Information in "+jsObj.typename+" ";
	}

	$("#titleDiv").html(title);
	if(votersbasicinfo.votersInfoForMandalVOList == null || votersbasicinfo.votersInfoForMandalVOList.length == 0)
	 {
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);
			$("#votersBasicInfoSubChartDiv").css('border','1px solid #FFF');
			$("#votersBasicInfoSubDiv").css('border','1px solid #FFF');

			$("#votersBasicInfoMsgDiv").html("<span id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</span>");
		 return;
	}
	if(votersbasicinfo != null && votersbasicinfo.datapresent)
	{
		
		$("#votersBasicInfoSubChartDiv").css('border','1px solid black');
		$("#votersBasicInfoSubDiv").css('border','1px solid black');
		$("#votersBasicInfoMsgDiv").html('');
		//$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);
		str += '<div>';
		str += '<b><span>Total Voters : '+votersbasicinfo.totVoters+'</span>';
		str += '<span style="margin-left:25px;">Male Voters : '+votersbasicinfo.totalMaleVoters+'</span>';
		str += '<span style="margin-left:25px;">Female Voters : '+votersbasicinfo.totalFemaleVoters+'</span>';
				
		if(votersbasicinfo.unKnowVoters != null && votersbasicinfo.unKnowVoters != 0 && votersbasicinfo.unKnowVoters != "0")
			str += '<span>UnKnown Voters : '+votersbasicinfo.unKnowVoters+'</span>';
		
		str += '</b></div></div></br></br>';
        if(votersbasicinfo.previousElectInfoList != null && votersbasicinfo.previousElectInfoList.length >0){
		    var prevElecInfo = votersbasicinfo.previousElectInfoList;
			str += '<table class="votersPrevCountTableDiv" style="margin-bottom:5px;font-family:verdana;">';
			str += '  <tr>';
			str += '    <th rowspan="2">Year</th>';
			str += '    <th rowspan="2">Total Voters</th>';
			str += '    <th rowspan="2">Male Voters</th>';
			str += '    <th rowspan="2">Female Voters</th>';
			str += '    <th colspan="3">Voters Comparision From '+prevElecInfo[prevElecInfo.length-1].electionYear+' To '+jsObj.year+'</th>';
			str += '  </tr>';
			str += '  <tr>';
			str += '    <th>Total Voters Comparision</th>';
			str += '    <th>Male Voters Comparision</th>';
			str += '    <th>Female Voters Comparision</th>';
			str += '  </tr>';
			for(var i in prevElecInfo){
			    str += '  <tr>';
				str += '    <td>'+prevElecInfo[i].electionYear+'</td>';
				str += '    <td>'+prevElecInfo[i].totalVoters+'</td>';
				str += '    <td>'+prevElecInfo[i].totalMaleVoters+'</td>';
				str += '    <td>'+prevElecInfo[i].totalFemaleVoters+'</td>';
			   if(prevElecInfo[i].totalVotersDiff < 0){
			     var count = prevElecInfo[i].totalVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(prevElecInfo[i].totalVotersDiff > 0)
			    str += '    <td>'+prevElecInfo[i].totalVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
			    str += '    <td>'+prevElecInfo[i].totalVotersDiff+'</td>';
				
			   if(prevElecInfo[i].maleVotersDiff < 0){
			    var count = prevElecInfo[i].maleVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(prevElecInfo[i].maleVotersDiff > 0)
			    str += '    <td>'+prevElecInfo[i].maleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
				str += '    <td>'+prevElecInfo[i].maleVotersDiff+'</td>';
				
			   if(prevElecInfo[i].femaleVotersDiff < 0){
			   var count = prevElecInfo[i].femaleVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(prevElecInfo[i].femaleVotersDiff > 0)
			    str += '    <td>'+prevElecInfo[i].femaleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
				str += '    <td>'+prevElecInfo[i].femaleVotersDiff+'</td>';
				str += '  </tr>';
			}
			    str += '  <tr>';
				str += '    <td>'+jsObj.year+'</td>';
				str += '    <td>'+votersbasicinfo.totVoters+'</td>';
				str += '    <td>'+votersbasicinfo.totalMaleVoters+'</td>';
				str += '    <td>'+votersbasicinfo.totalFemaleVoters+'</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';
				str += '  </tr>';
			str += '</table>';
			if(jsObj.type == "panchayat" && votersbasicinfo.panchayatInfoPresent){
			  str +='<div style="font-family:arial;margin-top:10px;margin-bottom:10px;font-size: 13px;">';
			  str +=' <div><b>Total Booths in '+votersbasicinfo.electionYear+' : '+votersbasicinfo.prevBoothsCount+'('+votersbasicinfo.prevBooths+')</b></div>';
			  str +=' <div><b>Total Booths in '+jsObj.year+' : '+votersbasicinfo.presentBoothsCount+'('+votersbasicinfo.presentBooths+')</b></div>';
			  if(votersbasicinfo.completelyRemoved != null && votersbasicinfo.completelyRemoved.length >0){
			     str +=' <div><b>Removed Booths Are :</b>';
				 for(var i in votersbasicinfo.completelyRemoved)
				  str +=votersbasicinfo.completelyRemoved[i]+' ';
				 str +=' </div>';
			  }
			  if(votersbasicinfo.newlyAdded != null && votersbasicinfo.newlyAdded.length >0){
			     str +=' <div><b>Newly Added Booths Are :</b>';
				 for(var i in votersbasicinfo.newlyAdded)
				  str +=votersbasicinfo.newlyAdded[i]+' ';
				 str +=' </div>';
			  }
			 
			  if(votersbasicinfo.otherComment != null && votersbasicinfo.otherComment.length >0){
			   for(var i in votersbasicinfo.otherComment)
			    str +=' <div><img src="images/icons/diamond.png"/> '+votersbasicinfo.otherComment[i]+'</div>';
			  }
			   str +='</div>';
			}
		}
		
		if(jsObj.type=="panchayat"){
		if(buildType=="hamlet"){
			var totalvoter=votersbasicinfo.assignedVotersByUser+votersbasicinfo.unassignedVotersByUser;
			strl ='';
    			strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
    			strl += '<tbody><td style="text-align:center;">'+totalvoter+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersByUser+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersByUser+'</td></tbody>';
			
			strl += '</table>';
			$("#assAndUnass").html(strl);
			}
			else{
			$("#assAndUnass").html('');
			}
		}
				
		else if(jsObj.type=="hamlet" && jsObj.resultFor == "localArea"){
			var totalvoterlclbds=votersbasicinfo.assignedVotersForLocalBodies+votersbasicinfo.unassignedVotersForLocalBodies;
			strl ='';
			strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
			strl += '<tbody><td style="text-align:center;">'+totalvoterlclbds+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersForLocalBodies+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersForLocalBodies+'</td></tbody>';
			
			strl += '</table>';
				strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Voters In Publication</th><th style="text-align:center;">Assigned by User In publication </th><th style="text-align:center;">Voters Need To Be Assigned </th></thead>';
    			strl += '<tbody><td style="text-align:center;">'+votersbasicinfo.publicationVoters+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersByUser+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersByUser+'</td></tbody>';
				strl += '</table>';
			$("#assAndUnass").html(strl);
			
		}
		else{
			$("#assAndUnass").html('');
		}
		
		
		//$("#votersBasicInfoDiv").html(str);
			if(jsObj.type != "booth"){
			  $("#votersBasicInfoSubChartDiv").css("border","1px solid black"); 
	          $("#votersBasicInfoSubDiv").css("border","1px solid black");
			 }
		
		str = '';
		if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       
		buildVotersChart(votersbasicinfo.votersInfoForMandalVOList,jsObj.typename);

	    	 
		var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"name", label: votersbasicinfo.votersInfoForMandalVOList[0].type, sortable: true},
		    	           	{key:"totalMaleVoters", label: "Male Voters", sortable: true},
							
							{key:"totalFemaleVoters", label: "Female Voters", sortable: true},
		    				{key:"totVoters", label: "Total Voters",sortable:true},
							{key:"percent", label: votersbasicinfo.votersInfoForMandalVOList[0].type+" % Share", sortable: true}
		    	        ]; 
		var myConfigs = {};
		var myDataSource = new YAHOO.util.DataSource(votersbasicinfo.votersInfoForMandalVOList);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "name","totalMaleVoters","totalFemaleVoters","totVoters","percent"]
					};

		var impFamliesResultDataSource = new YAHOO.widget.DataTable('votersBasicInfoSubDiv', votersResultColumnDefs,myDataSource, myConfigs);

		}
		//$('#votersByLocationTabContentDiv_body').css("border","1px solid black");
	}
	
	else
	{
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);

			$("#votersBasicInfoDiv").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");
		
	}
}

function buildVotersChart(chartInfo,reqTitle)
{

 // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'type');
        data.addColumn('number', 'value');
		data.addRows(chartInfo.length);

		for(var i = 0 ; i< chartInfo.length ; i++){
		var name = chartInfo[i].name;
		var val = parseFloat(chartInfo[i].percent);
		  data.setValue(i,0,name);
		  data.setValue(i,1,val);
		}
       
        // Set chart options
		var title = chartInfo[0].type+' wise Voters % Share in '+reqTitle; 
        var options = {'title':title,
                       'width':800,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('votersBasicInfoSubChartDiv'));
        chart.draw(data, options);
}


function hideAjaxImgDiv(id)
{
	document.getElementById(id).style.display = 'none';
}

function getLocalAreaWiseCasteInfo()
{

			var jsObj=
		{		
				type:"customWard",	
				id:id,
				typeName:typename,
				publicationDateId:publicationDateId,
				constituencyId:constituencyId,
                buildType:"customWardBooths",
				resultFor:"",
                queryType:"sub",
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
		
		}


		var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };

					 
					 
		 
					 
function buildHamletWiseCastResultsGraph(selectedCast,percentage)
{
	var myChart1 = new Array();
	var castMain = null;
	
	$('#ajaxImageDiv1').hide();
	if(percentage==null)
		percentage=1;
	
	if(selectedCast == null)
	{
		castMain = sort_unique(castTemp);
		var radios = document.getElementsByName('castTypeRadio');
		
		if(radios != null && radios.length > 0)
		{
			var selectValue;
			for (var r=0;r<radios.length;r++) 
			{
				if (radios[r].checked) 
					selectValue=radios[r].value;
				if(selectValue != 'All')
				{
					castMain=[];
					$("#castSelectdId option:selected").each(function()
					{
						castMain.push($(this).text());
					});
				}
			}
		}
    }

	else
      castMain = sort_unique(selectedCast);
	  
	  
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
			
			if(dataObj1[castMain[k]])
			{
				count++;
				avgData = parseInt(avgData)+parseInt(dataObj1[castMain[k]]);
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
	
	var gruopCast =new Array();
	
	for (var p in mySort)
	{
		var myObj = mySort[p];
		newCast.push(myObj['cast']);
	}

 	newCast.sort();
 	var dataGrouping1 = {
    groupPixelWidth: 40,
    units: [[
        'name',
        [1, 2, 3,4,5,6,7]
        ]]
	};

	var tempLine = new Array();

	for(var i in myChart) 
	{
		var clmTemp = new Object();
		var reqObj = myChart[i];
		
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
			else
			{
	 			newdataObj.push(0);
			}
		}
		
		clmTemp['data'] = newdataObj;
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
      
	   var chart1;
	   var invisib;
	   var isinvisib=false;
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
                text: casteChartHeading,
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
				min: 0,
                title: {
                    text: 'No of Voters'
                } /*,
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]*/
            }, 
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y + ' Voters';
                }
            },
			plotOptions: {
				series: {
					events: {
						legendItemClick: function(event) {
							isinvisib=true;
							var currEleVisib=false;
							var series = this.chart.series;
							var seriesIndex = this.index;
							var thisSeriesName=this.name;
							invisib=[];
							//invisib.push(thisSeriesName);
							
							var visibility = this.visible ? 'visible' : 'hidden';
							
							if(this.visible){
								currEleVisib=true;
							}
							
							for (var i = 0; i < series.length; i++){
								if(seriesIndex!=series[i].index){
									if(series[i].visible==false){
										invisib.push(series[i].name);
									}
								}
							}
							
							if(currEleVisib==true){
								invisib.push(thisSeriesName);
							}
							
							/*if(series.length==invisib.length && invisib.length!=0)
								$('input:radio[value=hide]').attr('checked', 'checked');
							
							if(invisib.length<series.length || invisib.length==0)
								$('input:radio[value=show]').attr('checked', 'checked');*/
														
							rebuiltDataTable(invisib);
							
						}
					}
				}
			},

            series: tempLine //myChart1 
        });
		
		
	$('tspan:last').hide();	
	
	
	/*$('input[name="show_hide"]').click(function(){
        if(this.value === "show")
            $.each(series, function(index, series1) {
                      series1.show();
                    });
        else if (this.value === "hide")
            $.each(series, function(index, series1) {
                      series1.hide();
                    });
    });*/
	
     $('#show_hide_show').click(function(){
		var chart = $('#castGrid1').highcharts();
		var series = chart.series;
		
		
		var _redraw = chart.redraw;
		chart.redraw = function(){};
		
		$.each(series, function(index, series1) {
            series1.show();
        });
		
		chart.redraw = _redraw;
		chart.redraw();
		
		
		rebuiltDataTable(null);
	});
	
	$('#show_hide_hide').click(function(){
		var chart = $('#castGrid1').highcharts();
		var series = chart.series;
		var totalSeriesName=[];
		
		
		var _redraw = chart.redraw;
		chart.redraw = function(){};
		
		$.each(series, function(index, series1) {
            series1.hide();
        });
		
		chart.redraw = _redraw;
		chart.redraw();
		
		for (var i = 0; i < series.length; i++){
				totalSeriesName.push(series[i].name);
		}
		
		rebuiltDataTable(totalSeriesName);
	});
	
	$('#show_hide_show1').click(function(){
		var chart = $('#castGrid2').highcharts();
		var series = chart.series;
		
		var _redraw = chart.redraw;
		chart.redraw = function(){};
		
		$.each(series, function(index, series1) {
            series1.show();
        });
		
		chart.redraw = _redraw;
		chart.redraw();
		
	});
	
	$('#show_hide_hide1').click(function(){
		var chart = $('#castGrid2').highcharts();
		var series = chart.series;
		
		var _redraw = chart.redraw;
		chart.redraw = function(){};
		
		$.each(series, function(index, series1) {
            series1.hide();
        });
		
		chart.redraw = _redraw;
		chart.redraw();
		
		
	});
	
	
	
}
function sort_unique(a) {
     var temp = {};
    for (var i = 0; i < a.length; i++)
        temp[a[i]] = true;
    var r = [];
    for (var k in temp)
        r.push(k);
    return r;
}

var castTemp = new Array();
var hamletTemp = new Array();

var hamletMain = new Array();
var hamletMainPie =  new Array();

var myChart = new Array();

var locationsForXAxis=[];
var dataForChart=[];					 
function buildCastInfoForSubLevels(myresults,jsObj,castesSlctdList,lgndItemSlctd)
	{	 
		
		  hamletMainPie=[];
	      myChart=[];
		  castTemp=[];
		
      	var type = '${type}';
		
		if(type == "customWard" && jsObj.buildType == "customWardBooths")
		  $("#getLatestCastsSubcategoryWise").css("display","none");
		else
		 $("#getLatestCastsSubcategoryWise").css("display","block");

		var str ='';
		$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");
		var divId=document.getElementById('localCastStatsTabContent_subbody');
		var publicationDateId = jsObj.publicationDateId;
		var type=jsObj.type;
		var	subLevelcastInfo = new Array();
		var cast = myresults.castVosList;
		var typeName=jsObj.typeName;
		var res=jsObj.resultFor;
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
			//alert(hamletTempColumn['name']);
			
			hamletTempPie['y'] = castSum;
			//hamletTempPie['color']  = Highcharts.getOptions().colors[i];
			hamletMainPie.push(hamletTempPie);
			
			myChart.push(hamletTempColumn);
            
		   }
		 }
		constMgmtMainObj.castStatssubArray =subLevelcastInfo;
		if(constMgmtMainObj.castStatssubArray == null || constMgmtMainObj.castStatssubArray.length == 0){
		  $("#localCastStatsTabContent_subbody").html("<b style='margin-left: 350px;'>No Data Available</b>");
		  $("#getLatestCastsSubcategoryWise").css("display","none");
		  $('#casteDetailsDiv').hide();
		  return;
		}  
		
		str +='<table id="subLevelTable">';
		
		 if(type =="customWard")
		{
		
			temp = "Booth";

		 str+='<h4 id="sublevelHeading">'+temp+' Wise Caste Statistics In '+typeName+' </h4>';
		}
		
		str+='<thead>';
		str+='<tr>';
		
		
		if(type =="customWard")
		{
		  
			str +='<th>Booth</th>';
		}
		str +='<th>Caste</th>';
		str+='<th>Caste Category</th>';
		str +='<th>Total Voters</th>';
		str +='<th>Caste Voters</th>';
		str +='<th>Male Voters</th>';
		str +='<th>Female Voters</th>';
		str +='<th>Caste Percentage</th>';
		
		str+='</tr>';
		str+='</thead>';
		
		str+='<tbody>';
		
		for(var i in constMgmtMainObj.castStatssubArray)
		{
		//console.log(constMgmtMainObj.castStatssubArray);//sasir
		if(castesSlctdList == null|| (castesSlctdList != null && castesSlctdList.indexOf(constMgmtMainObj.castStatssubArray[i].caste)!= -1))
		{
		if(lgndItemSlctd == null|| (lgndItemSlctd != null && lgndItemSlctd.indexOf(constMgmtMainObj.castStatssubArray[i].mandal)== -1))
		{
		str+='<tr>';
		str+='<td>'+(constMgmtMainObj.castStatssubArray[i].mandal)+'</td>';
		castTemp.push(constMgmtMainObj.castStatssubArray[i].caste);
		hamletTemp.push(constMgmtMainObj.castStatssubArray[i].mandal);
		
		 if(type =="customWard" && jsObj.buildType == "customWardBooths")
		{
		
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'customWardBooths\',\'boothNo - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].caste+'</td>';
		}
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].casteCategory+'</td>';
		str +='<td>'+constMgmtMainObj.castStatssubArray[i].totalVoters+'</td>';
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].castePopulation+'</td>';
		if(constMgmtMainObj.castStatssubArray[i].malePopulation ==null)
		str+='<td>'+0+'</td>';
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].malePopulation+'</td>';
		}
		if(constMgmtMainObj.castStatssubArray[i].femalePopulation ==null)
		{
			str+='<td>'+0+'</td>';
		}
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].femalePopulation+'</td>';
		}
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].castePercentage+'</td>';
	
		str +='</tr>';
		}
		}
		}
		str+='</tbody>';
		str +='</table>';

		divId.innerHTML = str;
		$('#subLevelTable').dataTable({
		"aaSorting": [[ 4, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null
		] 
		});
	
		if(lgndItemSlctd==null)
			buildHamletWiseCastResultsGraph(null,null);
	}
	
$('#castesAsPerLocId').click(function(){
	buildCastGrid2(null,null);
});
function buildCastGrid2(selectedCast1,percentage){
	//Copied
	var myChart1 = new Array();
	var castMain = null;
	
	if(percentage==null)
		percentage=1;
	
	if(selectedCast1 == null)
	{
		castMain = sort_unique(castTemp);
		var radios = document.getElementsByName('castTypeRadio1');
		
		if(radios != null && radios.length > 0)
		{
			var selectValue;
			for (var r=0;r<radios.length;r++) 
			{
				if (radios[r].checked) 
					selectValue=radios[r].value;
				if(selectValue != 'All')
				{
					castMain=[];
					$("#castSelectdId1 option:selected").each(function()
					{
						castMain.push($(this).text());
					});
				}
			}
		}
    }

	else
      castMain = sort_unique(selectedCast1);
	
		//console.log(castMain);
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
	//Copied End
	var dataForChart=[];
	var myresults=tempObj.result;
		locationsForXAxis=myresults.constituencyManagementVO.locations;
		data=myresults.constituencyManagementVO.locWiseCastePrcts;
		
		var dataObj;
		$.each(data, function( key, value ) {
			dataObj={};
			dataObj['name']=value.caste;
			dataObj['data']=value.locationWiseCastesCount;
			
			if($.inArray(value.caste,castMain) > -1)
				dataForChart.push(dataObj);
		});

//$('#castGrid2Outer').toggle();
 $('#castGrid2').highcharts({
            chart: {
                type: 'line',
				width:950
            },
            title: {
                text: casteChartHeading,
                x: -20 //center
            },
            
            xAxis: {
                categories: locationsForXAxis,
				labels: {
                                align:'right',
                                style: {
                                      cursor: 'pointer',
                                      fontSize: '12px',
                                },
                                rotation: -45,
                            } 
            },
            yAxis: {
				min: 0,
                title: {
                    text: 'No Of Voters'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ' voters'
            },
            series: dataForChart
        });
}

// The function is for slider value -- Created by sasi -- START
var votersRange;
$(function() {
$( "#slider" ).slider({
value:1,
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


$(function() {
$( "#slider1" ).slider({
value:1,
min: 0,
max: 50,
step: 1,
slide: function( event, ui ) {
$( "#amount1" ).val( "Percentage of Voters Caste: " + ui.value +" %");
},
change: function( event, ui ) {
$( "#amount1" ).val( "Percentage of Voters Caste: " + ui.value +" %");
votersRange=ui.value;
buildGraphBySlide1();
}
});
votersRange=$( "#amount1" ).val( "Percentage of Voters Caste: " + $( "#slider1" ).slider( "value" ) +" %");
votersRange=$( "#slider1" ).slider( "value" );
});

function buildGraphBySlide1(){
buildCastGrid2(null,votersRange);
}
//Selecting individual castes For Chart

//global variable tempObj to call buildCastWiseChart chart that consists ajax returned results and jsobj--created by sasi
var tempObj;
function buildCastSubLevelsDiv(myResults,jsObj)
{
	tempObj={
		result:myResults,
		jsobj:jsObj
	}
	var castStateIdsArr = new Array();
	var str ='';
	$("#casteSelectDiv").html('');
	if(myResults != null && myResults.castVosList.length > 0)
	{
		$("#casteSelectDiv").addClass('casteSelectDivCls');
		str +='<div>';
		str +='<div id="castErrorDiv"></div>';
		str +='<h4>Select Options To View Caste Wise Voter Analysis</h4>';
		
		str +='<input id="castSelectRadio" type="radio" checked="true" name="castTypeRadio" value="All" onclick="buildCastInfoBasedOnOptions(\'all\')" /><b>All</b>';

		str +='<input id="castAllRadio" type="radio" name="castTypeRadio" value="castWise" onclick="buildCastInfoBasedOnOptions(\'selected\')" /><b>Caste Wise</b>';
		
		str += '<div id="casteHideAndShowOptionsDiv" style="display:none;">';
		 str += '<select class="selectBoxStyle" id="castSelectdId" multiple="multiple">';
		 var casteInfo = myResults.castVosList;
		 var casteIdsArray = new Array();
		 var castesSortedArray= [];
		 
		 for(var i=0;i<casteInfo.length;i++)
		 {
		   if(casteInfo[i].voterCastInfoVO.castVOs != null && 
			   casteInfo[i].voterCastInfoVO.castVOs.length > 0)
			 {
				for(var j=0;j<casteInfo[i].voterCastInfoVO.castVOs.length;j++)
				 {
					if(casteIdsArray.indexOf(casteInfo[i].voterCastInfoVO.castVOs[j].castStateId) == -1)
					 {
						casteIdsArray.push(casteInfo[i].voterCastInfoVO.castVOs[j].castStateId);
						
						var obj={
							caste:casteInfo[i].voterCastInfoVO.castVOs[j].castName,
							id:casteInfo[i].voterCastInfoVO.castVOs[j].castStateId
						}
						castesSortedArray.push(obj);
					
						/*castesSortedArray.sort(sort_by('caste', true, function(a){return a.toUpperCase()}));
				
									
						str += '<option value="'+casteInfo[i].voterCastInfoVO.castVOs[j].castStateId+'">'+casteInfo[i].voterCastInfoVO.castVOs[j].castName+'</option>';*/
					 }
				 }
			 }
		 }

		 castesSortedArray.sort(sort_by('caste', true, function(a){return a.toUpperCase()}));
				
		for(var k=0;k<castesSortedArray.length;k++)
		{
			str += '<option value="'+castesSortedArray[k].Id+'">'+castesSortedArray[k].caste+'</option>';
		}

		  str += '</select>';
		  str +='<input type="button" style="margin-left: 24px;font-weight:bold;margin-top: -33px;" onclick="buildCastWiseChart(tempObj)" value="View" class="btn btn-info">';
		  str +='<p id="notePara">Press Ctrl Key To Select Multiple Castes</p>';
		  str +='</div>';
		  str +='</div>';
		  $("#casteSelectDiv").html(str);
	}
}
function buildCastSubLevelsDiv1(myResults,jsObj)
{
	tempObj={
		result:myResults,
		jsobj:jsObj
	}
	var castStateIdsArr = new Array();
	var str ='';
	$("#casteSelectDiv1").html('');
	if(myResults != null && myResults.castVosList.length > 0)
	{
		$("#casteSelectDiv1").addClass('casteSelectDivCls');
		str +='<div>';
		str +='<div id="castErrorDiv"></div>';
		str +='<h4>Select Options To View Caste Wise Voter Analysis</h4>';
		
		str +='<input id="castSelectRadio" type="radio" checked="true" name="castTypeRadio1" value="All" onclick="buildCastInfoBasedOnOptions1(\'all\')" /><b>All</b>';

		str +='<input id="castAllRadio" type="radio" name="castTypeRadio1" value="castWise" onclick="buildCastInfoBasedOnOptions1(\'selected\')" /><b>Caste Wise</b>';
		
		str += '<div id="casteHideAndShowOptionsDiv1" style="display:none;">';
		 str += '<select class="selectBoxStyle" id="castSelectdId1" multiple="multiple">';
		 var casteInfo = myResults.castVosList;
		 var casteIdsArray = new Array();
		 var castesSortedArray= [];
		 
		 for(var i=0;i<casteInfo.length;i++)
		 {
		   if(casteInfo[i].voterCastInfoVO.castVOs != null && 
			   casteInfo[i].voterCastInfoVO.castVOs.length > 0)
			 {
				for(var j=0;j<casteInfo[i].voterCastInfoVO.castVOs.length;j++)
				 {
					if(casteIdsArray.indexOf(casteInfo[i].voterCastInfoVO.castVOs[j].castStateId) == -1)
					 {
						casteIdsArray.push(casteInfo[i].voterCastInfoVO.castVOs[j].castStateId);
						
						var obj={
							caste:casteInfo[i].voterCastInfoVO.castVOs[j].castName,
							id:casteInfo[i].voterCastInfoVO.castVOs[j].castStateId
						}
						castesSortedArray.push(obj);
					
						/*castesSortedArray.sort(sort_by('caste', true, function(a){return a.toUpperCase()}));
				
									
						str += '<option value="'+casteInfo[i].voterCastInfoVO.castVOs[j].castStateId+'">'+casteInfo[i].voterCastInfoVO.castVOs[j].castName+'</option>';*/
					 }
				 }
			 }
		 }

		 castesSortedArray.sort(sort_by('caste', true, function(a){return a.toUpperCase()}));
				
		for(var k=0;k<castesSortedArray.length;k++)
		{
			str += '<option value="'+castesSortedArray[k].Id+'">'+castesSortedArray[k].caste+'</option>';
		}

		  str += '</select>';
		  str +='<input type="button" style="margin-left: 24px;font-weight:bold;margin-top: -33px;" onclick="buildCastWiseChart1(tempObj)" value="View" class="btn btn-info">';
		  str +='<p id="notePara">Press Ctrl Key To Select Multiple Castes</p>';
		  str +='</div>';
		  str +='</div>';
		  $("#casteSelectDiv1").html(str);
	}
}

var sort_by = function(field, reverse, primer){

   var key = function (x) {return primer ? primer(x[field]) : x[field]};

   return function (a,b) {
       var A = key(a), B = key(b);
       return ((A < B) ? -1 :
               (A > B) ? +1 : 0) * [-1,1][+!!reverse];                  
   }
}


var selectedCastArray=[];
function buildCastInfoBasedOnOptions(option)
{
	
	if(option == "all")
	{
		//used tempObj globla variable to get the json returned results and json object for calling the buildCastInfoForSubLevels() function -- sasi
		var myResults_slctd=tempObj.result;
		var jsObj_slctd=tempObj.jsobj;
		
		selectedCastArray = [];
		
		//called this function for changing the castTemp variable to store all the castes -- sasi
		buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,null,null);
		
		$("#casteHideAndShowOptionsDiv").css('display','none');
		buildHamletWiseCastResultsGraph(null,null);
	}
	else
		$("#casteHideAndShowOptionsDiv").css('display','block');
}

function buildCastInfoBasedOnOptions1(option)
{
	
	if(option == "all")
	{
		//used tempObj globla variable to get the json returned results and json object for calling the buildCastInfoForSubLevels() function -- sasi
		var myResults_slctd=tempObj.result;
		var jsObj_slctd=tempObj.jsobj;
		
		selectedCastArray = [];
		
		//called this function for changing the castTemp variable to store all the castes -- sasi
		buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,null,null);
		
		$("#casteHideAndShowOptionsDiv1").css('display','none');
		//buildHamletWiseCastResultsGraph(null,null);
		buildCastGrid2(null,null);
	}
	else
	{
		$("#casteHideAndShowOptionsDiv1").css('display','block');
	}
}


function buildCastWiseChart(temp)
{
var myResults_slctd=temp.result;
var jsObj_slctd=temp.jsobj;
	$("#castErrorDiv").html('');
	selectedCastArray = [];
	var selecteObj = document.getElementById('castSelectdId');
	for(var i=0;i<selecteObj.options.length;i++)
	{
		if(selecteObj.options[i].selected)
		{
		  selectedCastArray.push(selecteObj.options[i].text);
		}
	}
	if(selectedCastArray.length == 0)
	{
		$("#castErrorDiv").html('Please select at least one caste.');
		return;
	}
	//console.log(selectedCastArray);
	  buildHamletWiseCastResultsGraph(selectedCastArray,null);
	  buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,selectedCastArray,null);
}
function buildCastWiseChart1(temp)
{
var myResults_slctd=temp.result;
var jsObj_slctd=temp.jsobj;
	$("#castErrorDiv").html('');
	selectedCastArray = [];
	var selecteObj = document.getElementById('castSelectdId1');
	for(var i=0;i<selecteObj.options.length;i++)
	{
		if(selecteObj.options[i].selected)
		{
		  selectedCastArray.push(selecteObj.options[i].text);
		}
	}
	if(selectedCastArray.length == 0)
	{
		$("#castErrorDiv").html('Please select at least one caste.');
		return;
	}
	  //console.log(selectedCastArray);
	  //buildHamletWiseCastResultsGraph(selectedCastArray,null);
	  
	  buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,selectedCastArray,'');
	  buildCastGrid2(selectedCastArray,null)
	  
	  
}
function rebuiltDataTable(invisib){
		var myResults_slctd=tempObj.result;
		var jsObj_slctd=tempObj.jsobj;
		
		if(selectedCastArray.length != 0)
		buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,selectedCastArray,invisib);
		
		else
		buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,null,invisib);
	}

	
	function getVotersInACaste(id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
{
	var year=publicationYear;	
	var hamletId = ${id};
var urlStr="allVotersInAcasteAction.action?hamletId="+hamletId+"&mainId="+id+"&publicationDateId="+publicationDateId+"&caste="+caste+"&type=wardLocality&Name="+Name+"&casteStateId="+casteStateId+"&typename=hamlet&casteCategory="+casteCategory+"&year="+year+"&buildTypes=&constituencyId="+constituencyId+"";
	var updateBrowser = window.open(urlStr,"allVoterDetailsInAcaste"+casteStateId,"scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}
function getWardsListInMuncipality()
{
	var jsObj=
			{
				constituencyId:constituencyId,
				task:"getWardsList"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersAgeWiseDetailsAction.action?"+rparam;	
   
		callAjax(jsObj,url);
}
function buildWardsList(myResults)
{
	$('#wardSearchDiv').show();
	$("#wardsList").append('<option value="0">Select Ward</option>');
	if(myResults != null)
	{
		for(var i in myResults)
		{
			$("#wardsList").append('<option value='+myResults[i].id+' >'+myResults[i].name+'</option>');
		}
	}
	$('#wardsList').val(id);
}


function getAgeWiseData()
{
	
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:id,
					publicationDateId:publicationDateId,
					name:typename,
					retrieveType:"all",
					type:"customWardLocalArea"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
}
getLocalAreaWiseVotersInfo();
if(type == "customWard" && resultFor=="localArea")
{
getAgeWiseData();
getLocalAreaWiseCasteInfo();
getWardsListInMuncipality();
}
</script>
</body>
</html>