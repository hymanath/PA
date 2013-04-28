<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
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

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/subRegionsWiseAnalysis.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
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

#votersBasicInfoSubChartDiv{
  margin-left: 0px;
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
#partyWiseJqTable, #subLevelTable,#impfamilydatatable,#votersBasicInfoSubDivForAgeWiseDetls table,#votersBasicInfoSubDivForLclCastSts table,#votersBasicInfoSubDivForImpFam table,#impFamilesBasicSubDetails table,#impFamDtls table,#votersBasicInfoSubDiv table,#localCastStatsTabContent_body table,#localCastStatsTabContent_subbody1 table,#impFamilesBasicSubDetailsForHamlet table,#impFamilesnfoForHamletByBooth table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

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
#censusTab{width:98%;}
#censusTab th{background:#d9edf7; color:#454545;}
#censusTab td{color:#000;}

#censusTab{clear: both;margin-bottom: 10px;
    margin-top: 8px;}
	#censusReportMainDiv{padding-bottom:1px;}

#censusReportDiv h4{background-color: #05A8E9;
    border-radius: 4px 4px 4px 4px;
    color: snow;
    font-family: arial;
    height: 27px;
    margin-left: 4px;
    padding-top: 11px;
    width: 947px;}
#parliamentElecResDiv{margin-top:56px !important;}
#parliamentElecResDiv > table,#elecResDiv > table{border-collapse:collapse;border:1px solid #d3d3d3;width:100%;}
#parliamentElecResDiv > table * td,#elecResDiv > table * td{padding:3px;padding-left:5px;font-weight:normal;border:1px solid #d3d3d3;}
#parliamentElecResDiv > table * th,#elecResDiv > table * th{height:20px;text-align:left;background-color:#cde6fc; padding:5px;font-weight:bold;border:1px solid #d3d3d3;}
#parliamentElecResDiv > table * tr:nth-child(even),#elecResDiv > table * tr:nth-child(even){background:#f9f9f9;}
#parliamentElecResDiv > table * a,#elecResDiv > table * a{text-decoration:none;color:#3d3d3d;font-weight:bold;}

#labelRadioDiv{font-family:verdana;font-size:11px;}
#mainDiv{margin-left:auto;margin-right:auto;width:990px;}
</style>

 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<!--<script type="text/javascript" language="javascript" src="js/jQuery.min.js"></script>-->
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
var id= "${id}";
var publicationId= "${publicationDateId}";
var type= "${type}";
var mainname= "${typename}";
var publicationYear= "${publicationYear}";
var buildType= "${buildType}";
var constituencyId= "${constituencyId}";
var mainname = '${typeName}';
var constituencyResults,createGroupDialog;;
var constituencyType = 'Assembly';
var parliamentResult;
var counter = 0;


</script>

</head>
<body>
<div id="mainDiv">

 <div id='localCastStatsTabContent_subbody'  class="yui-skin-sam yui-dt-sortable"></div>

<div id="ajaxImageDiv" align="center" style="margin-top: 100px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>
<div id="votersBasicInfoMainDiv">
	<div id="censusReportMainDiv">
	   <div id="censusReportDiv"></div>
	</div>
	<div id="votersBasicInfoTitleDiv"></div>
	<div id="votersBasicInfoMsgDiv"></div>
	<div id="votersBasicInfoSubChartDiv"></div>
	</br>
	<div id="assAndUnass"></div>
	<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable"></div>	
	
</div>

<div id="votingTrendzDiv" class="widget blue">
	 <div class="clear"></div>
	
	<div id="detailedChartDIV" class="yui-skin-sam"></div>
<div id="MandalwiseVotingTrendz" class="rounded" >
			  <div id="mandalwisevotingTrendz"class="trenzCss">
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
				<div id="MandalVotingTrendz_head"></div>
				<div id="electionIdsSelectDiv"></div>
			
				<div id="parliamentElectionResultsDivNew" style="display:none;overflow:auto;margin-top:10px;"></div>
				<div id="censusSelectDiv"></div>
				<div id="censusErrorMsgDiv" style="padding-left:10px;"></div>
				<div id="mandalOrConstiElecResultDiv">
				<div id="parliamentElectionResultsDiv" style="overflow:auto;margin-top:10px;"></div>
				<div id="electionResultsInConstituencyDiv" style="margin-top: -1px;"></div>
				<div id="labelRadioDiv"></div>			
				<div id="resultsDataTableDiv"></div>
				<div id="missingDataInfoDiv"></div>
				
				</div>
						
			</div>
			</div>
			
			</div>
			
</div>			

<script type="text/javascript">
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
								else if(jsObj.task == "getCensusInfo")
								{
								  showSubLevelWiseCensusReport(myResults,jsObj);
								}
								else if(jsObj.task == "getConstituencyResultsBySubLocations")
								{	
                                   
									constituencyResults = myResults;
									buildCensusSelect(constituencyResults);
									buildConstituencyElecResultsDataTable("number");
									buiidElecResultRadioSelect();
									buildConstituencyElectionResultsDataTable("number");
									
								}
								
								else if(jsObj.task == "getConstituencyElections")
								{		
									buildElectionsSelectBox(myResults);									
								}
								
								else if(jsObj.task == "getCensusDetailsForAConstituency")
								{
								    document.getElementById("censusAjaxImgDiv").style.display="none";
									censusResult = myResults;
									buildCensusChartForAConstituency(myResults);
									buiidCensusRadioSelect();	
									buildConstituencyElectionResultsDataTableWithCensus(myResults,"number");
								}
								
								else if(jsObj.task == "getConstituencyElectionsYersForAss"){
								    buildConstituencyElectionsYersForAss(myResults);
								}
								else if(jsObj.task == "getParliamentConstituencyElectionResults")
								{		
								   document.getElementById("censusAjaxImgDivForParlinit").style.display ="none";	
									parliamentResult = myResults;
									buildParliamentResults(jsObj.electionYear,myResults);			
								}
								else if(jsObj.task == "getCastInfoForsubLevels")
								{  
									buildCastInfoForSubLevels(myResults,jsObj);
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
			title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" wise Voters Information in "+jsObj.typename+" Constituency";
		$("#votersBasicInfoTitleDiv").append('<h3 style="background: none repeat scroll 0% 0% #4285F4; color: rgb(255, 255, 255); padding: 5px; border-radius: 5px 5px 5px 5px; text-align: center; margin: 10px; border-top-width: 40px;">'+title+'</h3>');
		}
		else{
		 title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" wise Voters Information in "+jsObj.typename+" ";
		 $("#votersBasicInfoTitleDiv").append('<h3 style="background: none repeat scroll 0% 0% #4285F4; color: rgb(255, 255, 255); padding: 5px; border-radius: 5px 5px 5px 5px; text-align: center; margin: 10px; border-top-width: 40px;">'+title+'</h3>');
		}
	}

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

function getCensusInfoForSubLevels()
{
	var jsObj=
			{
				type:type,
				id:id,
				typename:mainname,
				constituencyId:constituencyId,
                task:"getCensusInfo"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getCensusReportForSubLevelsAction.action?"+rparam;	
   
		callAjax(jsObj,url);
	
}

function showSubLevelWiseCensusReport(result,jsObj)
{
	
	$("#censusReportDiv").html('');
		if(result == null)
		{
			$("#censusReportMainDiv").css("display","none");
			return;
		}
		$("#censusReportMainDiv").css("display","block");

		var name = jsObj.typename;
		if(jsObj.type == "constituency")
			name += " Constituency";
		var str = '';
		str +='<h4>Mandal Wise Census Information in '+name+'</h4>';
		str +='<table id="censusTab" class="table table-bordered table-striped table-hover">';
		str +='<tr>';
		str +='<th>Mandal</th>';
		str +='<th>Population</th>';
		str +='<th>SC Population</th>';
		str +='<th>ST Population</th>';
		str +='<th>Literate Populations</th>';
		str +='<th>Illiterate Population</th>';
		str +='<th>Working Population</th>';
		str +='</tr>';

		for(var i in result)
		{
			str +='<tr>';
			str +='<td>'+result[i].mandalName+'</td>';
			str +='<td>'+result[i].totalPersons+'</td>';
			str +='<td>'+result[i].totalSCPersons+'</td>';
			str +='<td>'+result[i].totalSTPersons+'</td>';
			str +='<td>'+result[i].totalLiteratePersons+'</td>';
			str +='<td>'+result[i].totalIlliteratePersons+'</td>';
			str +='<td>'+result[i].totalWorkingPersons+'</td>';
			str +='</tr>';
		}
		str +='</table>';
		$("#censusReportDiv").html(str);

}


function buildElectionsSelectBox(myResults){
	var selectDiv = document.getElementById("electionIdsSelectDiv");
	var electionYearSelect = '';	
	var selectDivEl = document.getElementById("MandalwiseVotingTrendz");
	if(myResults.length == 0){
		if(selectDivEl){
			selectDivEl.style.display = 'none';
		}
			
		
		return;
	}

	var headingDiv = document.getElementById("MandalVotingTrendz_head");
	var str='';
	if(headingDiv == null)
		return;
	if(constituencyType == 'Assembly'){
		//headingDiv.innerHTML = ' Mandal Wise Voting Trendz ';
	str +='<h1 class="topfour"></h1>';
	str +='<h1 class="gre-title">';
	str +='<h4>Mandal Wise Voting Trendz</h4>';
		str +='</h1>';
	headingDiv.innerHTML=str;
	}
	if(constituencyType == 'Parliament')
		{
		//headingDiv.innerHTML = ' Assembly Wise Voting Trendz '; 
	str +='<h1 class="topfour"></h1>';
	str +='<h1 class="gre-title">';
	str +='<h4> Assembly Wise Voting Trendz</h4>';
	str +='</h1>';
		headingDiv.innerHTML = str;
	str+='<br>';
	str+='<br>';
	}
	electionYearSelect += '<table>';
	if(constituencyType == 'Assembly')
		  electionYearSelect += '<th>Select Assembly Election Year :</th>';
		else
		  electionYearSelect += '<th>Select Parliament Election Year :</th>';
	electionYearSelect += '<th>';
	electionYearSelect += '<select id="electionYearSelect" class = "selectWidth" onchange = "getConstituencyResults(this.options[this.selectedIndex].text)">';
	for(var i in myResults)
	{			
		electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	electionYearSelect += '</select>';
	electionYearSelect += '</th>';
	electionYearSelect += '<td><div id="AjaxImgDiv" align="center" style="display:none;"><img width="5" height="5" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	electionYearSelect += '<td><div id="parliamentResultsButtonDiv"></td>';
	electionYearSelect += '<td><div id="detailsDiv"></td>';
	electionYearSelect += '</table>';
	selectDiv.innerHTML = electionYearSelect; 
	getConstituencyResults(myResults[0].name);
	}

function buildConstituencyElectionResultsDataTable(value)
	{

	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var str = '';
	str += '<div id="elecResDiv" style="width:900px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';
	for(var i in constituencyResults.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName == 'Others *' || 
				!constituencyResults.constituencyOrMandalWiseElectionVO[i].showLink)
			str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</td>';
		else
		if(constituencyResults.electionType == 'Assembly'){
			if(constituencyResults.constituencyOrMandalWiseElectionVO[i].isUrban)
				str += '<td><a  href="localBodyElectionAction.action?stateId=1&localBodyElectionTypeId=5&localBodyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
			else
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else
			str += '<td><a href="constituencyPageAction.action?constituencyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		for(var j in constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs){
			if(value == 'number')
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
		str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	var resultDiv = document.getElementById("missingDataInfoDiv");	
	var missingVotesDiv = '';
	missingVotesDiv+='<b>';
	missingVotesDiv += '<font color="Red"><b>*</b></font>';		
	missingVotesDiv += ' Others Include Postal Ballet Votes';
	missingVotesDiv+='</b>';
	resultDiv.innerHTML = missingVotesDiv;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constituencyResults.electionType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 

	 for(var i in constituencyResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					label:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							        };

	 if(constituencyResults.electionType == 'Parliament'){
		var extraInfoDiv = document.getElementById("missingDataInfoDiv");
		var str = '';
		str += '<br>';
		str += '<table>';
		str += '<tr>';
		str += '<td>';
		str += '<font color="Red"><b>*</b></font>';				
		str += '</td>';
		str += '<td><b>';
			if(constituencyResults.isDataInsufficient){
				str += ' Others Include Postal Ballet Votes, ';
				for(var i in constituencyResults.missingConstituencies)
					str += '<a href="constituencyPageAction.action?constituencyId='+constituencyResults.missingConstituencies[i].id+'">'+constituencyResults.missingConstituencies[i].name + '</a> Assembly ,';
				str = str.substring(0,str.length-1);
				str += ' Wise  Election Results';
			}else{
				str += ' Others Include Postal Ballet Votes';
			}
		str += '</b></td>';
		str += '</tr>';
		str += '</table>';		
		extraInfoDiv.innerHTML = str;		
	 }
	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource,{caption:"Mandal Wise Election Results For  Constituency In "+constituencyResults.electionYear+" "});
	}
	

function getConstituencyResults(elecYear){
	
	var jsObj = {
			constituencyId:232,
			electionYear:2009,
			chartHeight: 350,
			chartWidth: 500,
			others:true,
			task:"getConstituencyResultsBySubLocations"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/assemblyWiseParliamentResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}

	
	function getConstituencyElections(){

	var jsObj = {
			constituencyId:232,
			task:"getConstituencyElections"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
	}
	
	
function getConstiElecYearsForAss(){
   var jsObj = {
			constituencyId:495,
			task:"getConstituencyElectionsYersForAss"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getParliamentResults(elecYear){
		if(elecYear == 0 || elecYear == '0')
		 return;
		counter++;
	    document.getElementById("censusAjaxImgDivForParlinit").style.display ="block";	
		var jsObj = {
				constituencyId:232,
				electionYear:2012,
				censusYear:'',
				delimitationYear:'',
				seletedIndex:'',
				seletedText:'',
				task:"getParliamentConstituencyElectionResults"
			};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getParliamentMandalResultsAction.action?"+rparam;
		callAjax(jsObj, url);
		}



	function getCensusDetailsForAConstituency(constituencyId,index,text)
	{
	if(index == 0)
			return;

	var imgElmt = document.getElementById('censusAjaxImgDiv');
	
	if(imgElmt.style.display == "none")
	{
          imgElmt.style.display = "block";
	}
	
	var electionSelectEle = document.getElementById("electionYearSelect");
	var electionYear      = electionSelectEle.options[electionSelectEle.selectedIndex].text;
	//var constituencyType  = '${constituencyDetails.constituencyType}';

		var jsObj = {
			constituencyId  : 232,
			censusYear      : 2001,
			delimitationYear: 2009,
			seletedIndex    : index,
			seletedText     : text,
			electionYear    : 2009,
			constituencyType: constituencyType,
			others          : false,
			task:"getCensusDetailsForAConstituency"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getCensusDetailsForAConstituency.action?"+rparam;
	callAjax(jsObj, url);
	}

	
//GET CAST DATA START
function getLatestCastsSubcategoryWise(){
  $("#voterCasteAjaxImg").css("display","block");
  $("#localCastStatsTabContent_subbody").html("");

  var jsObj=
		{		
				type:type,	
				id:id,
				typeName:mainname,
				publicationDateId:publicationId,
				constituencyId:constituencyId,
                buildType:"hamlet",
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
		var restype=jsObj.buildType;
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
		  return;
		}  
		str +='<table id="subLevelTable">';
		if(type == 'constituency')
		str+='<h4 id="sublevelHeading">Mandal/Muncipality wise Caste Statistics In '+typeName+' Constituency</h4>';
		else if(type == "mandal")
		str+='<h4 id="sublevelHeading">Panchayat wise Caste Statistics In '+typeName+' </h4>';
        else if(type =="panchayat"){
		 if(restype== "booth")  
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Panchayat</h4>';
		else 
		str+='<h4 id="sublevelHeading">Hamlet wise Caste Statistics In '+typeName+' </h4>';
		}
		else if(type =="ward")
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Ward</h4>';
		else if(type =="hamlet"){
		   if(res == "booth")
		   str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Hamlet</h4>';
		else
		str+='<h4 id="sublevelHeading">Locality wise Caste Statistics In '+typeName+' Hamlet</h4>';
			}
			else if(type == "booth")
		str+='<h4 id="sublevelHeading">Hamlet wise Caste Statistics In '+typeName+' </h4>';

		
		str+='<thead>';
		str+='<tr>';

		if(type == "constituency")
		str +='<th>Mandal</th>';
		if(type == "mandal")
		str +='<th>Panchayat</th>';
		if(type =="panchayat"){
		if(restype== "booth")  
		str +='<th>Booth</th>';
		else 
		str +='<th>Hamlet</th>';
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
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'panchayat\',\'Hamlet - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="hamlet")
		{
		if(jsObj.resultFor == "booth")
				str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+constMgmtMainObj.castStatssubArray[i].locationId+','+jsObj.id+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\''+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';

		else
			str+='<td><a href="javascript:{}" onclick="getVotersInACasteForLocality('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+','+constMgmtMainObj.castStatssubArray[i].castStateId+',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\''+constMgmtMainObj.castStatssubArray[i].locationId+'\',\'Locality\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';

		//str+='<td><a href="javascript:{}" onclick="getVotersInACasteForLocality('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].hamletId+','+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
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

$('#localCastStatsTabContent_subbody').html(str);
		//divId.innerHTML = str;
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


<script type="text/javascript">

getvotersBasicInfo("voters",id,publicationId,type);
getCensusInfoForSubLevels();
getConstituencyElections();
getLatestCastsSubcategoryWise();

</script>

</body>


</html>