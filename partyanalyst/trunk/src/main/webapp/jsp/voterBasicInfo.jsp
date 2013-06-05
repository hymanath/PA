<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});
</script>
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

<style type="text/css">
#emprtyData
{

color:red;

}


.mybtn
{
color:#FFFFFF !important;
}

.wid
{
width: 194px !important;
word-wrap: break-word;
height: 34px;
}
.marg1
{
margin-top: 5px !important;
font-size:12px;
color:#0088CC;
font-family:Arial;
}

.marg
{
margin-top: 3px !important;
}
.locationErrorMsg{
	color:red;
}

.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

}

#voterBasicInfoTable td{
	text-align:center;

}
.radio {
	height: 25px;
	width: 19px;
	clear:left;
	float:left;
	margin: 0 0 3px;
	padding: 0 0 0 26px;
	background: url("js/jtransform/radio.png");
	background-repeat:no-repeat;
	cursor: default;
}
.checkbox {
	height: 25px;
	width: 19px;
	clear:left;
	float:left;
	margin: 0 0 3px;
	/*padding: 0 0 0 26px;*/
	background: url("js/jtransform/checkbox.png") no-repeat;
	cursor: default;
	text-align:left;
}
.checkbox input,.radio input {
	display: none;
}
.checkbox input.show,.radio input.show {
	display: inline;
}
.selected {
	background-position: 0 -52px;
}
#MainDiv{
margin-left:auto;
margin-right:auto;
width:980px;
}
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#000;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #DBEBFF;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}


table.gridtable1 {
	font-family: Verdana,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable1 th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #DBEBFF;
}
table.gridtable1 td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}


#votersBasicInfoDivSub{
    background-color: #EDF5FF;
    font-size: 15px;
    margin-top: 5px;
    text-align: center;
	height: 25px;
}	

#votersBasicInfoSubDiv{
  margin-left: 0px;
  padding:10px;
  font-family : arial;
}
#ageWiseDetailsDiv,#votersBasicInfoSubDiv,#votersBasicInfoSubChartDiv
{
    border-radius: 4px 4px 4px 4px;
    margin-left: 10px;
	margin-top: 17px;
    padding: 11px 10px 28px;
	
}

.pull-right{
	 //margin-top: -15px;
}

.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
	.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
.buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
	height:24px;
	
}
#votersBasicInfoDivSub{font-family:verdana;}
#impFamShowBasicInfo,#lclCastStsShowBasicInfo,
#ageWiseDetlsShowBasicInfo
{
	margin-right:10px;
}
 #votersouterDiv{
	 margin-left: auto;
	margin-right: auto;
  
 }
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }
   /*
 fieldset,#votersMainOuterDiv1,#votersMainOuterDiv2,
	 #votersMainOuterDiv3,#votersMainOuterDiv4{
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
 border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px; 
}
#votersMainOutertopDiv3{
-moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
	margin-bottom: 10px;
	height:100px;
}*/
p {
    color: #333333;
    font-size: 11px;
    line-height: 18px;
	font-family: verdana;
}
.divInfo
{
 background-color:#FFFFFF;
  border-top: 1px solid #B3C1CE;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
 padding-bottom:40px;

}
#partyWiseJqTable, #impfamilydatatable,#votersBasicInfoSubDivForAgeWiseDetls table,#votersBasicInfoSubDivForLclCastSts table,#votersBasicInfoSubDivForImpFam table,#impFamilesBasicSubDetails table,#impFamDtls table,#votersBasicInfoSubDiv table,#localCastStatsTabContent_body table,#localCastStatsTabContent_subbody1 table,#impFamilesBasicSubDetailsForHamlet table,#impFamilesnfoForHamletByBooth table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#votersByLocationTabContentDiv_body table,#InfluencingPeopleDetailsDiv table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#partyWiseJqTable tr:nth-child(even),#impfamilydatatable tr:nth-child(even),#votersBasicInfoSubDivForAgeWiseDetls table tr:nth-child(even),#votersBasicInfoSubDivForLclCastSts table tr:nth-child(even),#votersBasicInfoSubDivForImpFam table tr:nth-child(even),#impFamDtls table tr:nth-child(even),#impFamilesBasicSubDetails table tr:nth-child(even),#votersBasicInfoSubDiv table tr:nth-child(even),#localCastStatsTabContent_body table  tr:nth-child(even),#localCastStatsTabContent_subbody1 table tr:nth-child(even),#votersByLocationTabContentDiv_body table tr:nth-child(even),#InfluencingPeopleDetailsDiv table tr:nth-child(even),#impFamilesBasicSubDetailsForHamlet table tr:nth-child(even),#impfamilydatatable1 table
tr:nth-child(even),#impFamilesnfoForHamletByBooth  table tr:nth-child(even){background:#EdF5FF;}

#partyWiseJqTable td,#impfamilydatatable td,#impfamilydatatable1 td,#votersBasicInfoSubDivForAgeWiseDetls table td,#votersBasicInfoSubDivForLclCastSts table td,#votersBasicInfoSubDivForImpFam table td,#impFamDtls table td,#impFamilesBasicSubDetails table td,#impFamilesnfoForHamletByBooth td , #votersBasicInfoSubDiv table td,#localCastStatsTabContent_body table td,#localCastStatsTabContent_subbody1 table td,#votersByLocationTabContentDiv_body table td,#InfluencingPeopleDetailsDiv table td,#impFamilesBasicSubDetailsForHamlet table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#partyWiseJqTable th,#impfamilydatatable th,#impfamilydatatable1 th,#votersBasicInfoSubDivForAgeWiseDetls table th,#votersBasicInfoSubDivForLclCastSts table th,#votersBasicInfoSubDivForImpFam table th,#impFamDtls table th,#impFamilesBasicSubDetails table th,#impFamilesnfoForHamletByBooth table th , #votersBasicInfoSubDiv table th,#localCastStatsTabContent_body table th,#localCastStatsTabContent_subbody1 table th,#votersByLocationTabContentDiv_body table th,#votersByPanchayatTabContentDiv_body table th,#InfluencingPeopleDetailsDiv table th,#impFamilesBasicSubDetailsForHamlet table th{
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

#votersBasicInfoSubDivForAgeWiseDetls table th a,#votersBasicInfoSubDivForLclCastSts table th a,#votersBasicInfoSubDivForImpFam table th a,#impFamDtls table th a,#impFamilesBasicSubDetails table th a,#impFamilesnfoForHamletByBooth table th a ,#votersBasicInfoSubDiv table th a,#localCastStatsTabContent_body table th a,#localCastStatsTabContent_subbody1 table th a,#votersByLocationTabContentDiv_body table th a,#votersByPanchayatTabContentDiv_body table th a,#InfluencingPeopleDetailsDiv table th a,{
color:#333333;
}

#votersByPanchayatTabContentDiv_body,#votersByLocationTabContentDiv_body,#InfluencingPeopleDetailsDiv
{
	font-family : arial;
	font-size: 13px;
    margin-top: 20px;
	padding: 10px 10px 10px 15px;
}

#impFamPancBothDtls,#impFamDtls{
 margin-top:12px;
 margin-left: auto;
    margin-right: auto;
    width:100%;
}
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
</style>

<script type="text/javaScript" >

var id= "${id}";
var publicationId= "${publicationDateId}";
var type= "${type}";
var mainname= "${typename}";
var publicationYear= "${publicationYear}";
var buildType= "${buildType}";
var constituencyId= "${constituencyId}";
var mainname = '${typeName}';
var typeName = "${typeName}";
var casteChartHeading = '';
var lclBodyId = "${lclBodyId}"
var castePercent = [];
if(type == "wardBooth")
{
	getBoothWiseAgeDetailsInSelectedCustomWard();
	getWardsListInMuncipality();
}
$(document).ready(function()
{
 $('#castesAsPerLocId').live('click', function(event) {        
         $('#castGrid2Outer').toggle('show');
		 //$(this).val(if($((this).val()))
    });
	if(type == "wardBooth")
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
$("#wards").live("change",function(){
var id= $("#wards").val();
var typeName = $("#wards option:selected").text();
var urlStr = "voterBasicInfoAction.action?id="+id+"&publicationDateId="+publicationId+"&publicationYear="+publicationYear+"&typeName="+typeName+"&constituencyId="+constituencyId+"&buildType="+buildType+"&resultFor=&type=wardBooth&lclBodyId="+lclBodyId+" ";

 window.location.href = urlStr;	
});
function getvotersBasicInfo(buttonType,id,publicationId,type){
  // var ajaxImageDiv =  document.getElementById('ImpFamwiseAjaxDiv');
    var level = $("#reportLevel").val();
	var flag =true;
	var typename=mainname;
	var hresult="";
	if(true)
	{
	if(type == "booth")
	buildType="hamlet";
	
	 if(type == "hamletBooth"){
	 hresult="booth";
	type="hamlet";
	}
	if(type == "hamletLocal"){
	type="hamlet";
	hresult="localArea";
	}
	if(type == "wardBooth"){
	 hresult="booth";
	type="customWard";
	}
	if(type == "wardLocal"){
	type="customWard";
	hresult="localArea";
	}
	if(type == "mandal"){
	hresult="localElectionBodyBooths";
	}
	var jsObj=
			{
				type:type,
				id:id,
				publicationDateId:publicationId,
				year:publicationYear,
				typename:typename,
				constituencyId:constituencyId,
                buildType:buildType,
				resultFor:hresult,
				task:"votersbasicinfo"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;	
   
		callAjax(jsObj,url);
	}
}

function callAjax(jsObj,url)
{
			 var myResults;
			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "votersbasicinfo")
								{
									buildVotersBasicInfo(myResults,jsObj);
								}
								else if(jsObj.task == "getBoothWiseAgeDetails")
								{
									buildVotersAgeWiseInfo(myResults);
								}
								else if(jsObj.task == "getWardsList")
								{
									buildWardsList(myResults);
								}
								else if(jsObj.task == "getCastInfoForsubLevels")
									
								{
								
									castePercent = myResults.castPercent;
									buildCastInfoForSubLevels(myResults,jsObj,null,null);
									buildCastSubLevelsDiv(myResults,jsObj);
									buildCastSubLevelsDiv1(myResults,jsObj);
									//buildCasteData(myResults,jsObj);
								}
								
								}catch (e) {
								//console.log(e);
								//alert(e);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}



 function removeSelectElements(selectedElmt)
  {
	  var len = selectedElmt.length;
	  for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
  }
function buildVotersBasicInfo(votersbasicinfo,jsObj)
{
	  $("#votersBasicInfoSubChartDiv").html('');
	  $("#votersBasicInfoSubDiv").html('');
	  $("#ajaxImageDiv").css('display','none');
	//var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	//hideAjaxImgDiv('ajaxImageDiv');
	$("#votersInfoAjaxImg").css("display","none");
	  //$("#votersBasicInfoSubChartDiv").removeAttr('style');
	  //$("#votersBasicInfoSubDiv").removeAttr('style');

	var str = '<div id="votersBasicInfoDivSub">';
	var title = " Voters Basic Information of "+jsObj.typename+" in "+jsObj.year+"";
	if(votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0)
	{
		if(jsObj.type == "constituency"){
			title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+"/Muncipality Wise Voters Information in "+jsObj.typename+" Constituency";
		$("#votersBasicInfoTitleDiv").append('<h3 style="background: none repeat scroll 0% 0% #4285F4; color: rgb(255, 255, 255); padding: 5px; border-radius: 5px 5px 5px 5px; text-align: center; margin: 10px; border-top-width: 40px;">'+title+'</h3>');
		}
		else{
		 title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" Wise Voters Information in "+jsObj.typename+" ";
		 $("#votersBasicInfoTitleDiv").append('<h3 style="background: none repeat scroll 0% 0% #4285F4; color: rgb(255, 255, 255); padding: 5px; border-radius: 5px 5px 5px 5px; text-align: center; margin: 10px; border-top-width: 40px;">'+title+'</h3>');
		}
	}

	 if(votersbasicinfo.votersInfoForMandalVOList == null || votersbasicinfo.votersInfoForMandalVOList.length == 0)
	 {
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);
			$("#votersBasicInfoSubChartDiv").css('border','1px solid #EEEEEE');
			$("#votersBasicInfoSubDiv").css('border','1px solid #EEEEEE');

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
			str += '<table class="votersPrevCountTableDiv table table-bordered table-striped table-hover" style="margin-bottom:5px;font-family:verdana;">';
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
			  $("#votersBasicInfoSubChartDiv").css("border","1px solid #EEEEEE"); 
	          $("#votersBasicInfoSubDiv").css("border","1px solid #EEEEEE");
			 }
		
		str = '';
		if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       
		buildVotersChart(votersbasicinfo.votersInfoForMandalVOList,jsObj.typename);

	    var areaType = votersbasicinfo.votersInfoForMandalVOList[0].type;
			if(areaType == 'Mandal')
				areaType = votersbasicinfo.votersInfoForMandalVOList[0].type+'/Muncipality';
		var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"name", label: areaType, sortable: true},
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
		var title = chartInfo[0].type+' Wise Voters % Share in '+reqTitle; 
        var options = {'title':title,
                       'width':800,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('votersBasicInfoSubChartDiv'));
        chart.draw(data, options);
}
function getBoothWiseAgeDetailsInSelectedCustomWard()
{
	
	var jsObj=
			{
				id:id,
				publicationDateId:publicationId,
				constituencyId:constituencyId,
				task:"getBoothWiseAgeDetails"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersAgeWiseDetailsAction.action?"+rparam;	
   
		callAjax(jsObj,url);
}
function buildVotersAgeWiseInfo(myResults)
{
	if(myResults != null)
	{
		$('#votersAgenformationDiv').show();
		$('#ageWiseHeading').html('Booth Wise Voters Age Information in '+mainname+' ');
		var str = "";
		str += '<table class="table table-bordered table-striped table-hover" id= "ageWiseDetailstable">';
		str += '<thead class="info">';
		str += '<tr>';
		str += '<th rowspan="2">Booth NO</th>';
		str += '<th rowspan="2">Toatl Voters</th>';
		str += '<th colspan="2" style="padding-left: 63px;">18-25</th>';
		str += '<th colspan="2" style="padding-left: 63px;">26-35</th>';
		str += '<th colspan="2" style="padding-left: 63px;">36-45</th>';
		str += '<th colspan="2" style="padding-left: 63px;">46-60</th>';
		str += '<th colspan="2" style="padding-left: 45px;">Above 60</th>';
		str += '</tr>';
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
		str += '</thead>';
		str += '<tbody>';
		for(var i in myResults)
		{
			str+='<tr>';
			str+='<td>'+myResults[i].boothName+'</td>';
			str+='<td>'+myResults[i].totalVoters+'</td>';
			str+='<td>'+myResults[i].totalVotersFor18To25+'</td>';
			str+='<td>'+myResults[i].votersPercentFor18To25+'</td>';
			str+='<td>'+myResults[i].totalVotersFor26To35+'</td>';
			str+='<td>'+myResults[i].votersPercentFor26To35+'</td>';
			str+='<td>'+myResults[i].totalVotersFor36To45+'</td>';
			str+='<td>'+myResults[i].votersPercentFor36To45+'</td>';
			str+='<td>'+myResults[i].totalVotersFor46To60+'</td>';
			str+='<td>'+myResults[i].votersPercentFor46To60+'</td>';
			str+='<td>'+myResults[i].totalVotersForAbove60+'</td>';
			str+='<td>'+myResults[i].votersPercentForAbove60+'</td>';
			str+='</tr>';
		}
		str += '</tbody>';
		str += '</table>';
		$('#ageWiseDetailsDiv').html(str);
		$('#ageWiseDetailstable').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"bDestroy": true,
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
		});
	}
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
	
function getDetailsForSelectedWard()
{
	
	id 		 = $('#wardsList option:selected').val();
	mainname = $('#wardsList option:selected').text();
	var urlStr="voterBasicInfoAction.action?id="+id+"&publicationDateId="+publicationId+"&publicationYear="+publicationYear+"&typeName="+mainname+"&constituencyId="+constituencyId+"&buildType="+buildType+"&type="+type+" ";
		var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=850,left=200,top=200");	
		updateBrowser.focus();	
}
function getCasteInfoForCustomWard()
{

			var jsObj=
		{		
				type:"customWard",	
				id:id,
				typeName:typeName,
				publicationDateId:publicationId,
				constituencyId:constituencyId,
                buildType:"hamlet",
				resultFor:"booth",
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
	
	/*$('#btn_hide').click(function() {  
        var chart = $('#castGrid1').highcharts();
        var series = chart.series;
        $.each(series, function(index, series1) {
                      series1.hide();
                    });
    });
    
    $('#btn_show').click(function() {
       var chart = $('#castGrid1').highcharts();
        var series = chart.series;
        $.each(series, function(index, series1) {
                      series1.show();
                    });
    });*/
	
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
		
		if((type =="hamlet" && jsObj.buildType == "hamlet") || (type == "booth" && jsObj.buildType == "hamlet") || (type =="customWard" && jsObj.buildType == "hamlet"))
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
		
		 if(type =="customWard" && jsObj.buildType == "hamlet")
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
var urlStr="allVotersInAcasteAction.action?hamletId="+hamletId+"&mainId="+id+"&publicationDateId="+publicationDateId+"&caste="+caste+"&type=booth&Name="+Name+"&casteStateId="+casteStateId+"&typename=hamlet&casteCategory="+casteCategory+"&year="+year+"&buildTypes=customWardBooths&constituencyId="+constituencyId+"";
	var updateBrowser = window.open(urlStr,"allVoterDetailsInAcaste"+casteStateId,"scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}
</script>
</head>
<body>
<div id="ajaxImageDiv" align="center" style="margin-top: 100px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>
<div id="votersBasicInfoMainDiv">
	
	<div id="votersBasicInfoTitleDiv"></div>
	
	<div id="votersBasicInfoMsgDiv"></div>
	<div id="wardSearchDiv" style="float: right; padding: 10px;display:none;"><b>Select Ward : </b><select id="wardsList" onchange="getDetailsForSelectedWard();"></select></div>
	<div id="votersBasicInfoSubChartDiv" style="border: 1px solid #EEEEEE; margin-top: 67px;">
	</div>
	
	</br>
	
	<div id="assAndUnass"></div>
	<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable" style="border: 1px solid #EEEEEE;"></div>	
	
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
	<h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="ageWiseHeading"></h4>
	
	<div id="ageWiseDetailsDiv" class="yui-skin-sam yui-dt-sortable"></div></div>
<script type="text/javascript">

getvotersBasicInfo("voters",id,publicationId,type);
if(type == "wardBooth")
{
getCasteInfoForCustomWard();

}
</script>
</body>


</html>