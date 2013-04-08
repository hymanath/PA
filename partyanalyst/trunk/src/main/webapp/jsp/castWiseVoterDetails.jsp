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

<title>Cast Wise Voter Details</title>
</head>

<style type="text/css">

#voterCasteAjaxImg{clear: both; display: block; margin-left: auto; margin-right: auto; float: none;}

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

</style>

<script type="text/javascript">

var type               =  "${type}";
var id				   =  "${id}";
var typeName		   =  "${typeName}";
var constituencyId     =  "${constituencyId}";
var publicationDateId  =  "${publicationDateId}";
var buildType          =  "${buildType}";
var	queryType		   =  "${queryType}";

var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };

getCastInfoForsubLevel(id,publicationDateId,type,"");
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
		$("#voterCasteAjaxImg").css("display","block");
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
									{   $("#votersDiv2").show();
									buildCastInfoForSubLevels(myResults,jsObj);
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



/*This method is used for building the data table for  votercastInfo details */

function buildCastInfoForSubLevels(myresults,jsObj)
	{	 
		$("#voterCasteAjaxImg").css("display","none");
		
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
		var subLevelcastData = cast[i].voterCastInfoVO; 
		if(cast[i].mandalName != null)
		var name = cast[i].mandalName;
		else
		var name ="";
		if(cast[i].locationId != null)
		var locationId=cast[i].locationId;
		else
		locationId = 0;
		var totalVoters=subLevelcastData.totalVoters;
		var cast1 =subLevelcastData.castVOs;
			
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
			}
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
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Panchayat</h4>';
		else if(type =="ward")
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Ward</h4>';
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
		str +='<th>Booth</th>';
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
	
	}


</script>
<body>
<div id="errorDiv" align="center"></div>

<div id="localCastStatsVotersPopUpDiv" style="display:none;">
	    <div id ="localCastStatsVotersTitle" ></div>
	    <div id='localCastStatsTabContent_subbody1'  class="yui-skin-sam yui-dt-sortable"></div>
	</div>

 <!--<div id="maindiv" class="widget blue whitegloss"  style="display:inline-block;width: 96%;color:#000;position:relative;background:#fff;">-->
<div id="getLatestCastsSubcategoryWise"  style="float:right;"><input  type="button" onclick="getLatestCastsSubcategoryWise();" style="background: none repeat scroll 0 0 #06ABEA; border-radius: 8px;color: #FFFFFF; font-size: 16px;
    padding: 4px;width:206px" value="Get Updated Caste Info"/></div>
	
<div style="margin-top: 10px; margin-bottom: 15px;"><img id="voterCasteAjaxImg" src="./images/icons/goldAjaxLoad.gif" style=" clear: both; display:none;"/></div>

	
<div id="localCastStatsTabContent_subbody"></div>	
<!--<div>-->
</body>
</html>