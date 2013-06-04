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
#partyWiseJqTable, #subLevelTable,#impfamilydatatable,#votersBasicInfoSubDivForAgeWiseDetls table,#votersBasicInfoSubDivForLclCastSts table,#votersBasicInfoSubDivForImpFam table,#impFamilesBasicSubDetails table,#impFamDtls table,#votersBasicInfoSubDiv table,#localCastStatsTabContent_body table,#localCastStatsTabContent_subbody1 table,#impFamilesBasicSubDetailsForHamlet table,#impFamilesnfoForHamletByBooth table{#d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#votersByLocationTabContentDiv_body table,#InfluencingPeopleDetailsDiv table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#partyWiseJqTable tr:nth-child(even),#subLevelTable tr:nth-child(even),#impfamilydatatable tr:nth-child(even),#votersBasicInfoSubDivForAgeWiseDetls table tr:nth-child(even),#votersBasicInfoSubDivForLclCastSts table tr:nth-child(even),#votersBasicInfoSubDivForImpFam table tr:nth-child(even),#impFamDtls table tr:nth-child(even),#impFamilesBasicSubDetails table tr:nth-child(even),#votersBasicInfoSubDiv table tr:nth-child(even),#localCastStatsTabContent_body table  tr:nth-child(even),#localCastStatsTabContent_subbody1 table tr:nth-child(even),#votersByLocationTabContentDiv_body table tr:nth-child(even),#InfluencingPeopleDetailsDiv table tr:nth-child(even),#impFamilesBasicSubDetailsForHamlet table tr:nth-child(even),#impfamilydatatable1 table
tr:nth-child(even),#impFamilesnfoForHamletByBooth  table tr:nth-child(even){background:#EdF5FF;}

#partyWiseJqTable td,#subLevelTable td,#impfamilydatatable td,#impfamilydatatable1 td,#votersBasicInfoSubDivForAgeWiseDetls table td,#votersBasicInfoSubDivForLclCastSts table td,#votersBasicInfoSubDivForImpFam table td,#impFamDtls table td,#impFamilesBasicSubDetails table td,#impFamilesnfoForHamletByBooth td , #votersBasicInfoSubDiv table td,#localCastStatsTabContent_body table td,#localCastStatsTabContent_subbody1 table td,#votersByLocationTabContentDiv_body table td,#InfluencingPeopleDetailsDiv table td,#impFamilesBasicSubDetailsForHamlet table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#partyWiseJqTable th,#subLevelTable th,#impfamilydatatable th,#impfamilydatatable1 th,#votersBasicInfoSubDivForAgeWiseDetls table th,#votersBasicInfoSubDivForLclCastSts table th,#votersBasicInfoSubDivForImpFam table th,#impFamDtls table th,#impFamilesBasicSubDetails table th,#impFamilesnfoForHamletByBooth table th , #votersBasicInfoSubDiv table th,#localCastStatsTabContent_body table th,#localCastStatsTabContent_subbody1 table th,#votersByLocationTabContentDiv_body table th,#votersByPanchayatTabContentDiv_body table th,#InfluencingPeopleDetailsDiv table th,#impFamilesBasicSubDetailsForHamlet table th{
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







 

<!--<script type="text/javascript" language="javascript" src="js/jQuery.min.js"></script>-->
<script type="text/javaScript" >

var id= "${id}";
var publicationId= "${publicationDateId}";
var type= "${type}";
var mainname= "${typename}";
var publicationYear= "${publicationYear}";
var buildType= "${buildType}";
var constituencyId= "${constituencyId}";
var mainname = '${typeName}';
if(type == "wardBooth")
{
	getBoothWiseAgeDetailsInSelectedCustomWard();
	getWardsListInMuncipality();
}
getvotersBasicInfo("voters",id,publicationId,type);
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
	<div id="votersAgenformationDiv" class="widget blue whitegloss" style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; width: 949px;display:none;">
	<h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="ageWiseHeading"></h4>
	
	<div id="ageWiseDetailsDiv" class="yui-skin-sam yui-dt-sortable"></div></div>
</div>
</body>


</html>