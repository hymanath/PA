<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

	<!-- YUI Dependency files (End) -->
   <script type="text/javascript" src="js/highcharts/js/highcharts.js"></script>
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
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<title>Cast Wise Voter Details</title>
</head>

<style type="text/css">



.localCastStatsVotersTitle{border-radius: 3px 3px 3px 3px;
    color: #FFFFFF !important;
    font-family: verdana;
    font-size: 15px !important;
    font-weight: bold;
    padding: 5px;
    width: 650px;}

	#sublevelHeading{background: none repeat scroll 0 0 #06ABEA;
    border-radius: 3px;
    color: #FFFFFF;
    font-size: 14px;
    margin-top: 8px;
    padding: 4px;
    width: 550px;}


	.localCastStatsVotersTitle{background:#06ABEA;}

	#localCastStatsTabContent_subbody caption{
    color: #000;
    font-size: 13px;
    font-style: normal;
	font-family: verdana,sans-serif;
   }


#subLevelTable,#localCastStatsTabContent_subbody1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#subLevelTable tr:nth-child(even),#localCastStatsTabContent_subbody1 table tr:nth-child(even){background:#EdF5FF;}

#subLevelTable td,#localCastStatsTabContent_subbody1 table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#subLevelTable th,#localCastStatsTabContent_subbody1 table th{
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

	#localCastStatsTabContent_subbody1 table th a{
color:#333333;
}

#subLevelTable_info,#subLevelTable_paginate,{
	font-family: verdana;
    font-size: 12px;
    margin-top: 12px;
	
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

.popupcontainer {		
    	background-color: #FFFFFF;
    	margin: 9px auto 10px;
    	max-width: 780px;
    	padding: 10px;
		box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
}
.main-mbg {
    -moz-border-radius: 6px 6px 6px 6px;
	border-radius :6px;
    background-color: #06ABEA;
    clear: both;
    color: #FFFFFF;
    float: left;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    margin-bottom: 5px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 974px;
}
.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 6px 0;
    margin-left: 183px;
    margin-top: 48px;
    white-space: nowrap;
}

#sse2
{
    /*You can decorate the menu's container, such as adding background images through this block*/
    background-color: #0088CC;
    height: 38px;
    padding: 15px;
    border-radius: 6px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
}
#sses2
{
    margin:0 auto;/*This will make the menu center-aligned. Removing this line will make the menu align left.*/
}
#sses2 ul 
{ 
    position: relative;
    list-style-type: none;
    float:left;
    padding:0;margin:0;
}
#sses2 li
{
    float:left;
    list-style-type: none;
    padding:0;margin:0;background-image:none;
}

#openProblemEditFormId{cursor:pointer;}

.ui-widget {
font-family: verdana;
}

.yui-skin-sam .yui-pg-container {
    display: block;
    margin: 6px 0;
    margin-left: 183px;
    margin-top: 48px;
    white-space: nowrap;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
    
}
.ui-widget-header{
	background: none repeat scroll 0 0 #4285F4;
	color:#fff;
	}
	.ui-widget-overlay{
	  opacity: 0.8;
	}
.yui-skin-sam .yui-dt-liner {
    margin: 0;
    padding: 4px 10px;
    width: 65px;
}
#casteSelectDiv{margin-top:20px;margin-bottom:30px;}
#casteSelectDiv {
    margin-bottom: 15px;
    margin-left: 150px;
    margin-right: 150px;
    padding: 5px;
    width: 700px;
	 padding-bottom: 10px;
}
.casteSelectDivCls{border: 2px solid #4499EE;}
#castSelectdId{height:55px;}
#castAllRadio,#castSelectRadio{margin-right: 4px;margin-top: 0;}
#castSelectRadio,#castAllRadio{ margin-left: 16px;}
#castErrorDiv{color: red;font-family: verdana;text-align: center;}
#casteHideAndShowOptionsDiv{ margin-left: 188px;
    margin-top: -21px;}
#casteSelectDiv h4{ margin-bottom: 13px;margin-top: 5px; font-size: 15px;text-align:center;}
#notePara{margin-left: 172px; font-weight: bold; margin-top: -27px; font-family: verdana;}
</style>

<script type="text/javascript">

var type               =  "${type}";
var id				   =  "${id}";
var typeName		   =  "${typeName}";
var constituencyId     =  "${constituencyId}";
var publicationDateId  =  "${publicationDateId}";
var buildType          =  "${buildType}";
var	queryType		   =  "${queryType}";
 var resultFor = '${resultFor}';
//myBtn

if(/panchayat/i.test(type) && /hamlet/i.test(buildType))
{//alert('ok');
//$("#getLatestCastsSubcategoryWise").hide();
$("#getLatestCastsSubcategoryWise").css('display','none');
}else
 $("#getLatestCastsSubcategoryWise").css('display','block');



var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };

getCastInfoForsubLevel(id,publicationDateId,type,resultFor);
/* This Method is used to get Updated CastInfo */
function getLatestCastsSubcategoryWise(){
	
  $("#voterCasteAjaxImg").css("display","block");
  $("#localCastStatsTabContent_subbody").html("");
  var jsObj=
		{		
				type:type,	
				id:id,
				typeName:typeName,
				publicationDateId:publicationDateId,
				constituencyId:constituencyId,
                buildType:buildType,
                queryType:"main",
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
}

/*This Methid is Used for to send ajaxcall and get castWiseVotersDetails */

function getCastInfoForsubLevel(id,publicationId,type,resultFor)
	{
	//document.getElementById("localCastStatsVotersTitle").innerHTML='';
	//document.getElementById("localCastStatsTabContent_subbody").innerHTML='';
	//document.getElementById("localCastStatsTabContent_subbody1").innerHTML='';
	//var typeName=mainname;
	/*var publicationDateId = $("#publicationDateList").val();
	var level = $("#reportLevel").val();
	var type = '';
	var id='';
	var typeName='';
	var mandalId='';
	var validflag =0;
	var str ='';
	var flag = true;
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	typeName = $("#constituencyList :selected").text();
	if(id == 0 || id == null)
	{
	flag =false;
	}
	}
	else if(level == 2 ||id == null){
	type = 'mandal';
	id = $("#mandalField").val();
	var mandalText = $('#mandalField :selected').text();
	var validflag= mandalText.search("MUNCIPALITY");
	typeName = $("#mandalField :selected").text();
	if(id == 0)
	{
	flag =false;
	}
	else if(validflag != -1)
	{
	flag =false;
	}
	}
	else if(level == 3 || id == null){
	type = 'panchayat';
	id = $("#panchayatField").val();
	mandalid = $("#mandalField").val();
	typeName = $("#panchayatField :selected").text();
	var mandalText = $('#mandalField :selected').text();
	 validflag= mandalText.search("MUNCIPALITY");
	 if(mandalid == 0)
		{
		 flag =false;
		}
	else if(validflag != -1)
		{
		flag=false;
		}
	  else if(id == 0)
		{
		flag =false;
		}
		
	}
	else if(level == 4 || id == null)
	{
	
		return false;
	}
	if(publicationDateId == 0|| publicationDateId == null)
		{
		flag =false;
		}*/
		
		if(true)
		{
		var jsObj=
		{		
				type:type,	
				id:id,
				typeName:typeName,
				publicationDateId:publicationId,
				constituencyId:constituencyId,
                buildType:buildType,
				resultFor:resultFor,
                queryType:"sub",
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
		
		}
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
									 if(jsObj.task == "getCastInfoForsubLevels")
									{   $("#voterCasteAjaxImg").hide();
									 buildCastInfoForSubLevels(myResults,jsObj);
									 buildCastSubLevelsDiv(myResults);
									}
									else if(jsObj.task =="getVotersInACaste")
								{
								    
						              $("#localCastStatsVotersPopUpDiv").dialog({
											modal: true,
											title: "<b>Voters Details</b>",
											width: 970,
											height: 600
										   
										});
									buildVotersInACaste(myResults,jsObj);
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

function getVotersInACaste(id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
{
	$("#localCastStatsVotersTitle").html("");
	$("#localCastStatsTabContent_subbody1").html("");
	var level = $("#reportLevel").val();
	if(level == 2)
	var typename = $('#mandalField :selected').text();
	if(level == 3)
	var typename = $('#panchayatField :selected').text()+ ' Panchayat ';
	if(level == 4)
	var typename = $('#pollingStationField :selected').text();
	var publicationDateVal=$('#publicationDateList :selected').text();
	var year=publicationDateVal.substr(publicationDateVal.length - 4)
	var jsObj={
			id:id,
			publicationDateId:publicationDateId,
			//caste:"32",
			caste:casteStateId,
			casteName:caste,
			typename:typename,
			type:type,
			publicationDate:year,
			Name:Name,
            buildType:buildType,
			constituencyId:$("#constituencyList").val(),
			casteCategory:casteCategory,
			task:"getVotersInACaste"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);
}

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
		//$("#voterCasteAjaxImg").css("display","none");
		
      	var type = '${type}';
		//$("#voterCasteAjaxImg").css("display","none");

		if((type =="hamlet" && buildType == "hamlet") || (type == "booth" && buildType == "hamlet") || (type =="panchayat" && buildType == "hamlet"))
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
		  return;
		}  
		//if(type != 'booth')
		//{
		str +='<table id="subLevelTable">';
		if(type == 'constituency')
		str+='<h4 id="sublevelHeading">Mandal/Muncipality wise Caste Statistics In '+typeName+' Constituency</h4>';
		else if(type == "mandal")
		str+='<h4 id="sublevelHeading">Panchayat wise Caste Statistics In '+typeName+' </h4>';
		else if(type =="panchayat")
		{
			var temp = "";
		  if(buildType == "hamlet")
			temp = "Hamlet";
		  else
			temp = "Booth";

		 str+='<h4 id="sublevelHeading">'+temp+' wise Caste Statistics In '+typeName+' </h4>';
		}
		else if(type =="ward")
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Ward</h4>';
		else if(type == "booth")
		{
		 str+='<h4 id="sublevelHeading">Hamlet wise Caste Statistics In'+typeName+'Booth</h4>';	
		}
		
		else if(type =="hamlet"){
		   if(res == "booth")
		   str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Hamlet</h4>';
		else
		str+='<h4 id="sublevelHeading">Locality wise Caste Statistics In '+typeName+' Hamlet</h4>';
			}
		
		str+='<thead>';
		str+='<tr>';
		
		if(type == "constituency")
		str +='<th>Mandal</th>';
		if(type == "mandal")
		str +='<th>Panchayat</th>';
		if(type =="panchayat")
		{
		  if(buildType == "hamlet")
			str +='<th>Hamlet</th>';
		  else
			str +='<th>Booth</th>';
		}
		if(type =="ward")
		str +='<th>Booth</th>';
		if(type =="hamlet")
		{ if(res == "booth")
		str +='<th>Booth</th>';
		else
	    str +='<th>Locality</th>';
		
		}
		if(type =="booth")
	    str +='<th>Hamlet</th>';

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
		str+='<tr>';
		
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].mandal+'</td>';
		castTemp.push(constMgmtMainObj.castStatssubArray[i].caste);
		hamletTemp.push(constMgmtMainObj.castStatssubArray[i].mandal);
		if(type == "mandal")
		{
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'panchayat\',\''+constMgmtMainObj.castStatssubArray[i].mandal+' Panchayat\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="panchayat" && buildType != "hamlet")
		{
		
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'booth\',\'boothNo - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="panchayat" && buildType == "hamlet")
		{
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'panchayat\',\'boothNo - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="hamlet")
		{
		if(jsObj.resultFor == "booth")
				str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+constMgmtMainObj.castStatssubArray[i].locationId+','+jsObj.id+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\''+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';

		else
		str+='<td><a href="javascript:{}" onclick="getVotersInACasteForLocality('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+','+constMgmtMainObj.castStatssubArray[i].castStateId+',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\''+constMgmtMainObj.castStatssubArray[i].locationId+'\',\'Locality\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}else if(type =="booth")
		{
		
		str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+jsObj.id+','+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\'Hamlet - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
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
	
		}
		
		str +='</tr>';
		str+='</tbody>';
		str +='</table>';

		divId.innerHTML = str;
		$('#subLevelTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null
		] 
		});
	$('#subLevelTable tr').removeClass("odd");
	$('#subLevelTable tr').removeClass("even");
	$('#subLevelTable td').removeClass("sorting_1");
	//}
	buildHamletWiseCastResultsGraph(null);
	}


	function getVotersInACasteForLocality(id , publicationDateId , casteStateId,casteCategory,caste,localityId,name)
	{
	var publicationDateVal=$('#publicationDateList :selected').text();
	var year=publicationDateVal.substr(publicationDateVal.length - 4)
	var jsObj={
			id:id,
			publicationDateId:publicationDateId,
			caste:casteStateId,
			hamletId:'${id}',
			type:"locality",
            buildType:"",
			task:"getVotersInACaste",
			casteCategory:casteCategory,
			Name:name+"-"+localityId,
			publicationDate:year,
			casteName:caste,

		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);

}

function buildVotersInACaste(results,jsObj)
{

var result = results;

	if(results.votersByHouseNos != null)
		$("#localCastStatsVotersTitle").addClass("localCastStatsVotersTitle").html(" "+jsObj.casteName+"("+jsObj.casteCategory+") Caste voters Details In " +jsObj.Name+" in "+jsObj.publicationDate+" ");
	else
		$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");

	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var boothId=oRecord.getData("boothId");
		var id=oRecord.getData("voterId");
		var name = oRecord.getData("name");
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	 var votersResultColumnDefs = [ 		    	             
		    	            
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,
							formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"voterIdCardNo",label:"Voter Id",sortable: true},
	                        {key:"mobileNo",label:"MobileNo",sortable:true}
		    	        ]; 

    
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20
			    }) 
				};
		
		
	var myDataSource = new YAHOO.util.DataSource(results.votersByHouseNos);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId","voterIdCardNo"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersResultColumnDefs,myDataSource, myConfigs);


}
function getVotersInACasteForDidffrentLevels(mainId,id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
{
$("#localCastStatsVotersTitle").html("");
$("#localCastStatsTabContent_subbody1").html("");
var level = $("#reportLevel").val();
if(level == 2)
var typename = $('#mandalField :selected').text();
if(level == 3)
var typename = $('#panchayatField :selected').text()+ ' Panchayat ';
if(level == 4)
var typename = $('#pollingStationField :selected').text();
var publicationDateVal=$('#publicationDateList :selected').text();
var year=publicationDateVal.substr(publicationDateVal.length - 4)
var jsObj={ hamletId:id,
			id:mainId,
			publicationDateId:publicationDateId,
			//caste:"32",
			caste:casteStateId,
			casteName:caste,
			typename:typename,
			type:type,
			publicationDate:year,
			Name:Name,
            buildType:buildType,
			constituencyId:$("#constituencyList").val(),
			casteCategory:casteCategory,
			task:"getVotersInACaste"

		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);

}

function openProblemEditForm(id,boothId)
{

	var urlStr="votersEditAction.action?voterId="+id+"&boothId="+boothId+" ";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();	
}	



function buildHamletWiseCastResultsGraph(selectedCast)
{  
	var myChart1 = new Array();
	

	if(selectedCast == null)
	  var castMain = sort_unique(castTemp);
	else
	 var castMain = sort_unique(selectedCast);

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
      // building highchart
	/* var chart;
	   chart = new Highcharts.Chart({
	      chart: {
	         renderTo: 'castGrid',
			 //width : 4000,
			     zoomType: 'x',
                        events: {
                            click: function() {
                                this.xAxis[0].setExtremes();
								                       }
                        }
	      },
	      title: {
	         text: 'Hamlet Wise Cast Statastics In Panchayat'
	      },
	      xAxis: {
	         categories: newCast,
			  minRange: 1,
			    labels: {
            rotation: -90,
            align: 'right',
            style: {
                font: 'normal 9px Verdana, sans-serif'
            },
            formatter: function() {
            return this.value;                
            }

         }
			 
	      },
	      tooltip: {
	         formatter: function() {
	            var s;
	            if (this.point.name) { // the pie chart
	               s = ''+
	                  this.point.name +': '+ this.y +' Cast Voters';
	            } else {
	               s = ''+
	                  this.x  +': '+ this.y;
	            }
	            return s;
	         }
	      },
	      labels: {
	         items: [{
	            html: 'Hamlet Wise Cast',
	            style: {
	               left: '40px',
	               top: '8px',
	               color: 'black'            
	            }
	         }]
	      },
	      series: myChart1,
		  
	   }); */var titleString= $("#sublevelHeading").text();
	
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
            /* legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            }, */
            series: tempLine //myChart1 
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

function buildCastSubLevelsDiv(myResults)
{
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
						str += '<option value="'+casteInfo[i].voterCastInfoVO.castVOs[j].castStateId+'">'+casteInfo[i].voterCastInfoVO.castVOs[j].castName+'</option>';
					 }
				 }
			 }
		 }
		  str += '</select>';
		  str +='<input type="button" style="margin-left: 24px;font-weight:bold;margin-top: -33px;" onclick="buildCastWiseChart()" value="View" class="btn btn-info">';
		  str +='<p id="notePara">Press Ctrl Key To Select Multiple Castes</p>';
		  str +='</div>';
		  str +='</div>';
		  $("#casteSelectDiv").html(str);
	}
}

function buildCastWiseChart()
{
	$("#castErrorDiv").html('');
	var selectedCastArray = new Array();
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
	
	  buildHamletWiseCastResultsGraph(selectedCastArray);
}

function buildCastInfoBasedOnOptions(option)
{
	
	if(option == "all")
	{
		$("#casteHideAndShowOptionsDiv").css('display','none');
		buildHamletWiseCastResultsGraph(null);
	}
	else
		$("#casteHideAndShowOptionsDiv").css('display','block');
	
		
}

</script>
<body>
<div id="voterCasteAjaxImg" style=" display:block;margin-top:100px;margin-left:300px;margin-right:auto;"><img  src="./images/icons/goldAjaxLoad.gif" /></div>
<div id="casteSelectDiv"></div>
<div id="castGrid1" style="height: 500px; display: block; overflow-x: auto;"></div>	
<div id="castGrid" style="height: 500px; display: none; overflow-x: auto;"></div>	
<div id="errorDiv" align="center"></div>

<div id="localCastStatsVotersPopUpDiv" style="display:none;">
	    <div id ="localCastStatsVotersTitle" ></div>
	    <div id='localCastStatsTabContent_subbody1'  class="yui-skin-sam yui-dt-sortable"></div>
	</div>

 <!--<div id="maindiv" class="widget blue whitegloss"  style="display:inline-block;width: 96%;color:#000;position:relative;background:#fff;">-->
<div id="getLatestCastsSubcategoryWise"  style="float:right;display:none">
<input  type="button" id="myBtn" onclick="getLatestCastsSubcategoryWise();" style="background: none repeat scroll 0 0 #06ABEA; border-radius: 8px;color: #FFFFFF; font-size: 16px;
    padding: 4px;width:206px" value="Get Updated Caste Info"/></div>
	


	
<div id="localCastStatsTabContent_subbody"></div>	
<!--<div>-->
</body>
</html>