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
	  <link href="styles/assets/css/bootstrap.css" rel="stylesheet">
	  <script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
	  <script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>
<style type="text/css">

.headings{
	background: none repeat scroll 0% 0% #4285F4;
	color: rgb(255, 255, 255);
	padding: 5px; border-radius: 5px 5px 5px 5px;
	text-align: center; 
	margin: 10px;
	border-top-width: 40px;
}
#headingDiv1
{
	background-color: #05A8E9;
    border-radius: 4px 4px 4px 4px;
    color:snow;
    padding: 2px;
}

#amount{width:90%;text-align:center;}
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
#censusTab th{background:#cde6fc; color:#454545;}
#censusTab td{color:#000;}
label{display:inline-block;}
		input[type="radio"], input[type="checkbox"] {margin:5px;}
		.hero-unit{padding:22px;color:black;font-size:15px;margin-bottom: 5px;margin-top: 10px;}
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
.dataTables_wrapper{
   margin-bottom: 20px;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #EDF5FF;
}
table.dataTable tr.odd {
    background-color: #EDF5FF;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #DAEDFF;
	<!-- background-color: #EDF5FF; -->
}
table.dataTable thead th {
    border-bottom: 1px none #D3D3D3;
	background-color: #CDE6FC;
}
.selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }
 .ourGuideLine
 {
 font-size:18px;
 color:#FF0000;
 margin-top:5px;
 margin-bottom: 5px;
 
 }
 .myTittle
 {
 color:#62C462;
 
 }
</style>

 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<!--<script type="text/javascript" language="javascript" src="js/jQuery.min.js"></script>-->
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
var id= "${id}";
var publicationId= "${publicationDateId}";
var type= "${type}";
var publicationYear= "${publicationYear}";
var buildType= "${buildType}";
var constituencyId= "${constituencyId}";
var mainname = '${typeName}';
var constituencyResults,createGroupDialog;;
var constituencyType = 'Assembly';
var parliamentResult;
var counter = 0;
var tehsilId = '${mandalId}';
var isGhmc = '${ghmc}';
var partiesList = new Array();

$(document).ready(function(){


	$('.elecSelForPanc').live("change",function(){

		if($('.elecSelForPanc').filter(":checked").length == $('.elecSelForPanc').length )
			$('#selectAllEle').attr('checked',true);
		else
			$('#selectAllEle').attr('checked',false);


		if($('.elecSelForPanc').filter(":checked").length == 0 )
			$('#deSelectAllEle').attr('checked',true);
		else
			$('#deSelectAllEle').attr('checked',false);		

		
	});


	$('.partySelForPanc').live("change",function(){

		if($('.partySelForPanc').filter(":checked").length == $('.partySelForPanc').length )
			$('#selectAll').attr('checked',true);
		else
			$('#selectAll').attr('checked',false);


		if($('.partySelForPanc').filter(":checked").length == 0 )
			$('#deSelectAll').attr('checked',true);
		else
			$('#deSelectAll').attr('checked',false);		

		
	});

	$('.delimitation').change(function(){
		getResultsForConstituency();
	});
	
	 $('#castesAsPerLocId').live('click', function(event) {        
         $('#castGrid2Outer').toggle('show');
		 //$(this).val(if($((this).val()))
    });
	
 if(type == "mandal"){
       $("#mandalElecResultsDiv").show();
	   //$("#votingTrendzDiv").hide();
       var jsObj=
			{
				
				tehsilId:id.substr(1),
				task:"getElectionsAndParties"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatWiseElectionResultsAction.action?"+rparam;	
   
		callAjax(jsObj,url);
  }
 if(type == "panchayat")
  {
   
   // $("#votingTrendzDiv").hide();

   if( "${buildType}" == "hamlet"){
	   $('#mandalElecResultsDiv').hide();
   }else{
	   $("#mandalElecResultsDiv").show();
     getBoothPArtiesAndElections();
   }
  }
 else if(type == "constituency"){
 //$("#votingTrendzDiv").show();
   // getConstituencyElections();
   getConstituencyEleAndParties();
  }

	$('#ShowConstMenu').live('click',function(){
		$("#ShowConstMenu").css("display","none");
		$("#ShowConstMenu1").css("display","block");
		$('#dataDiv').toggle();
	});
	$('#ShowConstMenu1').live('click',function(){
		$("#ShowConstMenu").css("display","block");
		$("#ShowConstMenu1").css("display","none");
		 $('#dataDiv').toggle();
	});
  if(buildType != 'hamlet'){
	$('#mainDev').css('display','block');
	}
});

$('#mandals').live("change",function() {
       
	var mainreqid =$('#mandals').val();
	if(mainreqid == 0)
	return;
	var mainname =$("#mandals option:selected").text();
	
var urlstr = "subRegionsWiseAnalysisAction.action?id="+mainreqid+"&publicationDateId="+publicationId+"&type="+type+"&publicationYear="+publicationYear+"&buildType=&constituencyId="+constituencyId+"&typeName="+mainname;
  //var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     //browser1.focus();
	 window.location.href = urlstr;
});
$("#panchayats").live("change",function(){
	
	var mainreqid =$('#panchayats').val();
	if(mainreqid == 0)
	return;
	var mainname =$("#panchayats option:selected").text()+" Panchayat";
	   var urlstr = "subRegionsWiseAnalysisAction.action?id="+mainreqid+"&publicationDateId="+publicationId+"&type="+type+"&publicationYear="+publicationYear+"&buildType="+buildType+"&constituencyId="+constituencyId+"&typeName="+mainname+"&mandalId="+tehsilId;
	   // var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     //browser1.focus();
	 window.location.href = urlstr;
});
</script>

</head>
<body>

<div id="mainDiv">

<div id="votersBasicInfoMainDiv">
<div id="headingDiv1" align="center"></div>
	<div id="censusReportMainDiv">
	   <div id="censusReportDiv" class="widget blue"></div>
	</div>
	
	<div class="widget blue">
	
	<div id="basicInfoAjaxDiv" align="center" style="margin-top: 100px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>

	<c:if test="${type == 'mandal'}">
	
	
	<div id="othersmandalDiv" style="display:none;float:right;margin-top:5px;">Select Mandal : <select id="mandals"></select></div>
	</c:if>
	<c:if test="${type == 'panchayat'}">
	
	<div id="othersPanchayatDiv" style="display:none;float:right;margin-top:5px;">Select Panchayat : <select id="panchayats"></select></div>
	</c:if>
	<br>
	<div id="votersBasicInfoTitleDiv"></div>
	<!--<div id="votersBasicInfoMsgDiv" class="widget blue"></div>
	<div id="votersBasicInfoSubChartDiv" class="widget blue"></div>-->
	<div id="assAndUnass"></div>
	<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable"></div>	
	</div>
</div>
<!--<div id="votingTrendzDiv" class="widget blue">
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
			
			</div>-->
<div class="widget blue" id="mandalElecResultsDiv" style="margin-top:10px;display:none;" >
   <!--<h4 id="sublevelHeading">Caste Wise Voters Analysis</h4>-->
   <div id="sublevelHeading"> </div>

 <div id="dialogDiv" style="margin-left:20px;"></div>
<div id="delimitationOptionsDiv" class="hero-unit" style="padding:2px;text-align:center;display:none;">
<span style="font-weight:bold;margin-left:60px;">Compare Results With - </span>
<label><input type='radio' name="election" value="previous" class="delimitation"/>Previous Delimitation</label>
<label><input type='radio' name="election" value="present" class="delimitation" checked />Latest Delimitation</label>
<label><input type='radio' name="election" value="all" class="delimitation"/>Both</label>

<a  style="float:right;font-size:13px;font-weight:bold;margin-right:15px;" href="javascript:{callAjaxToShowTehsilDetails()}">Show All Delimitation Mandal Details</a>
</div>

<div class="hero-unit" >
    <div id="mandalElecResultsErrMsg" style="color:red;"></div>
    <!--<div id="mandalElecResultsParties"></div>-->
    <div id="mandalElecResultsElections"></div>
	  <div id="mandalElecResultsParties" style="clear:both;"></div>
    <div id="mandalElecResultsButton" style='margin-left:81px;'></div>
	<div id="mandalElecResultsButton1" style='margin-left:81px;'></div>
  </div>
  <div align="center">
    <div id="container" style="height:600px;"></div>
	<div style="margin-left:auto;margin-right:auto;width:200px;margin-bottom:10px;">
		<span class="label label-info btn btn-info" style="padding:5px;" id="show_votes">Show All </span>
		<span class="label label-info btn btn-info" style="padding:5px;" id="hide_votes">Hide All 	</span>
	</div>
  </div>
</div>
 <div id="mainDev" class="widget blue" style="display:none;">
  <div id="sublevelHeading"><h3> Analysis Based On Votes Percentage Difference</h3></div>
  <a id="ShowConstMenu" class="btn pull-right btn-primary" style="margin-top:-20px;margin-right: 30px; width: 46px;" href="javascript:{}" >Hide<i class="icon-chevron-up"></i></a>
  <a id="ShowConstMenu1" class="btn pull-right btn-primary" style="margin-top:-30px;margin-right: 30px;display:none;" href="javascript:{}" >Show<i class="icon-chevron-down"></i></a>
  <div id="dataDiv">
  <div align="center" id = "subDev1">
	   <div id="selectYoptionId" ></div>
	   <table>
	   <th><span class ='noteString'> Select Latest Party Results </span></th>
	   <th><span class ='noteString'> Select Party Results </span></th>
	   <th><span class ='noteString'> Select Areas </span></th>
	   <th></th>
	   <th></th>
		<tr>
		  <td>
	          <select multiple  id="selId1" style="width:182px;" ></select>  
	      </td>
		  <td>
	          <select multiple id="selId2" style="width:182px;" ></select> 
	      </td>
		  <td>
		  	   <select multiple  id="selId3" style="width:182px;"></select> 
          </td>
		 <td>
	         <input type="button"  id ="meanClick" style="margin-left: 18px;  height: 39px;" value="Compare Results" class="btn btn-info">
	     </td>
		 <td>
	         <input type="button"  id ="guideClick" style="margin-left: 18px; " value=" Instructions " class="btn btn-success">
	     </td>
		</tr>
		<tr>
		  <td>
		         <input type="button"  id ="reverseClick1"  selid ="selId1" style="" value="Reverse Inputs Of Box1" class="btn btn-info">
	        </td>
		  <td>
			    <input type="button"  id ="reverseClick2" selid = "selId2" style="" value="Reverse Inputs Of Box2" class="btn btn-info">
		
	      </td>
		   </tr>
		 
	  
	    </table>
		<table>
		
		 <tr>
		    <td >
		  <span class="ourGuideLine" >  </span>
		    </td>
		  </tr> 
		  </table>
		
      <div id="deviation1" style=""></div>
	  
	</div>
   
	  <div align="center" id = "subDev2" style="display:none;">
      <div id="deviation2" style="height:600px;"></div>
	  </div>
	 </div>
	  
 	  
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
 <!-- <div style="margin-left:auto;margin-right:auto;width:200px;margin-bottom:10px;">
	<span class="label label-info" style="padding:5px;">Show All <input type="radio" name="show_hide" value="show" checked="checked"></span>
	<span class="label label-info" style="padding:5px;">Hide All <input type="radio" name="show_hide" value="hide"></span>
  </div>-->
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

<div  style="display:none;"  class="widget blue">
<div id="voterDetailsNote" class="noteDiv" style="display:none;text-align:center;"></div>
<div id="tableDiv" style="padding:10px;display:none;overflow-x:scroll" class="voterDetails"></div>
</div>



<div  style="display:none;" class="widget blue">
<div id="voterAgeAngGenderwiseDetailsNote" class="noteDiv " style="text-align:center;"></div>
<div id="ageAndgenderWiseDetails" style="padding:10px;overflow-x:scroll;" class="voterDetails"> </div>
</div>

<div style="display:none;" class="widget blue">
<div id="voterAgeAngGenderwiseDetailsNoteInPercent" class="noteDiv" style="text-align:center;"></div>
<div id="voterAgeAngGenderwiseDetailsInPercent" style="overflow-x:scroll;padding:10px;" class="voterDetails "></div>
</div>

 <div id='localCastStatsTabContent_subbody'  class="yui-skin-sam yui-dt-sortable widget blue"></div>

<div id="ajaxImageDiv" align="center" style="margin-top: 100px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>

<div class="widget blue">
<div id="voterAgewiseDetailsNote" class="noteDiv" style="text-align:center;color:#3F3636;"></div>
<div id="agewiseDetails" style="padding:10px;overflow-x:scroll;" class="voterDetails"></div>
</div>

			
</div>

<div id="container1"></div>

<!--<div id="electionResultsDiv" class="widget blue"></div>-->

<div id="instructionDialog" ></div>

<script type="text/javascript">
function getvotersBasicInfo(buttonType,id,publicationId,type){
//var ajaxImageDiv =  document.getElementById('basicInfoAjaxDiv');
	$("#basicInfoAjaxDiv").show();
    var level = $("#reportLevel").val();
	var flag =true;
	var typename=mainname;
	var hresult="";
	if(true)
	{
	if(type == "panchayat")
	//buildType="booth";
		buildType=buildType;
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
	
var startNumber =  2;
var retrieveType = "all";
var mandalId = id.substring(1);
function getAgewiseVoterDetails(){
	if(type == "constituency")
	{
	var jsObj=
				{					
					constituencyId:constituencyId,
					publicationDateId:publicationId,
					mandalId:'0',
					boothId:'0',
					panchayatId:'0',
					name:name,
					retrieveType:retrieveType,
					task:"getAgewiseVoterDetails",
				    type:"constituency",
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;						
		
		callAjax(jsObj,url);
}
/*
	This Condition is used for checking for Mandal level Age Wise analysis
*/
 else if(type == "mandal")
{
	if(id.charAt(0) == "2"){

			var jsObj=
					{
						constituencyId:constituencyId,
						publicationDateId:publicationId,
						mandalId:mandalId,
						boothId:'0',
						panchayatId:'0',
						name:name,
						retrieveType:retrieveType,
						task:"getAgewiseVoterDetails",
						type:"mandal"
						
					};
		}else if(id.charAt(0) == "1"){

			var jsObj=
					{
					constituencyId:constituencyId,
					mandalId:mandalId,
					publicationDateId:publicationId,
					name:name,
					boothId:0,
					panchayatId:'0',
					retrieveType:retrieveType,
					task:"getAgewiseVoterDetails",
					type:"localElectionBody"
						
					};
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

		callAjax(jsObj,url);
}
/*
	This Condition is used for checking for Panchayat level Age Wise analysis
*/
 else if(type == "panchayat")
{
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:id,
					publicationDateId:publicationId,
					name:name,
					retrieveType:retrieveType,
					buildType:buildType,
					task:"getAgewiseVoterDetails",
					type:"panchayat"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
} 
/*
	This Condition is used for checking for Ward level Age Wise analysis
*/
 else if(type == "ward")
{
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:id,
					publicationDateId:publicationDateId,
					name:name,
					retrieveType:retrieveType,
					task:"getAgewiseVoterDetails",
					type:"ward"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
}
/*
	This Condition is used for checking for Hamlet level Age Wise analysis
*/
else if(type == "hamletLocalArea" || type == "hamletBooths" || type == "boothHamlets" || type == "wardBooths")
{
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:panchayatId,
					publicationDateId:publicationDateId,
					name:name,
					retrieveType:retrieveType,
					task:"getAgewiseVoterDetails",
					type:type
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
}

}
function getVotersInACaste(id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
{
	var year=publicationYear;	
	var mainId = 0;
	var urlStr="allVotersInAcasteAction.action?hamletId="+id+"&mainId="+mainId+"&publicationDateId="+publicationDateId+"&caste="+caste+"&type="+type+"&Name="+Name+"&casteStateId="+casteStateId+"&typename=&casteCategory="+casteCategory+"&typename=&year="+year+"&buildTypes=booth&constituencyId="+constituencyId+" ";
	var updateBrowser = window.open(urlStr,"allVoterDetailsInAcaste"+casteStateId,"scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}
function getVotersInACasteForPanchayatHamlet(id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
{
	var year=publicationYear;	
	var mainId = 0;
	var urlStr="allVotersInAcasteAction.action?hamletId="+id+"&mainId="+mainId+"&publicationDateId="+publicationDateId+"&caste="+caste+"&type="+type+"&Name="+Name+"&casteStateId="+casteStateId+"&typename=&casteCategory="+casteCategory+"&typename=&year="+year+"&buildTypes=panchayatHamlet&constituencyId="+constituencyId+" ";
	var updateBrowser = window.open(urlStr,"allVoterDetailsInAcaste"+casteStateId,"scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
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
									}else if(jsObj.task == "getElectionsAndParties" || jsObj.task == "getPanchayatElectionsAndParties" || jsObj.task == "getConstiEleAndParties"){
									  buildElectionsAndParty(myResults);
									   partiesList = myResults.parties;
									}
									
									else if(jsObj.task == "getResults" ||jsObj.task == "getResultsForBooth" || jsObj.task == "getResultsForConstituency"){
									  buildLineChart(myResults,jsObj);
									 
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
									castePercent = myResults.castPercent;
									buildCastInfoForSubLevels(myResults,jsObj,null,null);
									buildCastSubLevelsDiv(myResults,jsObj);
									buildCastSubLevelsDiv1(myResults,jsObj);
									
								}
								else if(jsObj.task == "getAgewiseVoterDetails"){
									$("#ajaxImageDiv").css('display','none');
					buildVoterDetailsTable(myResults,jsObj.type,jsObj.retrieveType);
				    buildAgewiseDetails(myResults,jsObj);
					buildAgeAndGenderWiseDetails(myResults,jsObj);
					buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj)
								}
						else if(jsObj.task == "getMandals")
								{
						buildMandals(myResults);
								}
								else if(jsObj.task == "getPanchayatsByMandalId")
								{
								buildPanchayats(myResults);
								}else if(jsObj.task == "getTehsilDetailsForAllDelimitations"){

									
									showAllTehsilDetailsForAllDelimitations(myResults);
								}else if(jsObj.task == "checkDelimitationYearsAndMandalsForConstituency")
									if(myResults.oneDelimitation != "true" && myResults.sameMandals != "true")
									$('#delimitationOptionsDiv').show();

				
								}catch (e) {
								console.log(e);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

 function buildElectionsAndParty(myResults){
	
	  partiesList = myResults.parties; 
	  if(myResults.electionsInMandal.length == 0)
		  $("#mandalElecResultsDiv").css("display","none");
		  else
		$("#mandalElecResultsDiv").css("display","block");
		  var electionsLength = myResults.electionsInMandal.length;
	     var str='';
		 str+='<table>';
   
		 str+='<tr><th align="left">Elections  : </th><td>';
		 str+='<table>';
		 for(var i in myResults.electionsInMandal[0]){
			if(i%6==0)
				str+='<tr>';
			if(i == electionsLength-1)
			 {
				str+='<td><input id="elections-'+i+'" checked="true" type="checkbox" class="elecSelForPanc" value="'+myResults.electionsInMandal[0][i].id+'" name="parties"><label class="checkboxLabel" for="elections-'+i+'">'+myResults.electionsInMandal[0][i].name+'</label></td>';
			 }
			else{
				 str+='<td><input id="elections-'+i+'"  type="checkbox" class="elecSelForPanc" value="'+myResults.electionsInMandal[0][i].id+'" name="parties"><label class="checkboxLabel" for="elections-'+i+'">'+myResults.electionsInMandal[0][i].name+'</label></td>';
		   }
			if((i%6)+1==0)
		   str+='</tr>';
		 }
		  str+='<td></td>';

		 str+='<td colspan="2"><label style="color:#0088cc;"><b><input type="radio"  id="selectAllEle" name="electionsSle" value="Select All Elections" onclick="selectAllCheckBoxes(this.value); buildParty(partiesList,\'click\');">Select All</b></label>';
		str+='<label style="color:#0088cc;"><b><input type="radio" id="deSelectAllEle" name="electionsSle" value="Unselect All Elections" onclick="deSelectAllCheckBoxes(this.value); ">Unselect All</label></b></td>';
		 str+='</table>';

		 str+='</td></tr>';
		
		 str+='</table>';
	     $("#mandalElecResultsElections").html(str);
		 
		 buildParty(partiesList,"onload");
		 
		getElectionData();
  }

$(".elecSelForPanc").live("click",function(){

buildParty(partiesList,"click");
$(".partySelForPanc").removeAttr("checked");
});

function hideParties()
{
	$("#mandalElecResultsParties").hide();
	$("#alliance").hide();
}
function buildParty(partiesList,param)
{
	
$("#mandalElecResultsParties").show();
$("#alliance").show();
var eleIds = [];
var party =[];
var str='';

 $('.elecSelForPanc').each(function() {

	        if($(this).is(':checked')){
			var eleId = $(this).val();
		var isExist = $.inArray(eleId, partiesList);
	
		if(isExist == -1)
		{
		for(var i in partiesList){
		if(eleId == partiesList[i].id)
		{
		for(var j in partiesList[i].selectOptionsList)
		{
	
		 var obj = {
				id: partiesList[i].selectOptionsList[j].id,
				name:partiesList[i].selectOptionsList[j].name

			  }
		party.push(obj);
		 }
	
		}
	  }
	}
	}
});

var results = [];
var idsSeen = {}, idSeenValue = {};
for (var i = 0, len = party.length, id; i < len; ++i) {
    id = party[i].id;
    if (idsSeen[id] !== idSeenValue) {
        results.push(party[i]);
        idsSeen[id] = idSeenValue;
    }
}
results.sort(dynamicSort("name")); 
		str+='<div>';
		 str+='<table><tr><th align="left">Parties : </th><td>';
		  str+='<table>';
		 for(var i in results){
		 
			 if(i%12== 0)
			str+='<tr>';
			if(param == "onload")
		  str+='<td><input id="parties-'+i+'" checked="true" class="partySelForPanc" type="checkbox" value="'+results[i].id+'" name="parties"><label class="checkboxLabel" for="parties-'+i+'">'+results[i].name+'</label></td>';
		if(param == "click")
		 str+='<td><input id="parties-'+i+'" class="partySelForPanc" type="checkbox" value="'+results[i].id+'" name="parties"><label class="checkboxLabel" for="parties-'+i+'">'+results[i].name+'</label></td>';
		  
			if((i%12)+1 == 0)
			str+='</tr>';
		 }
		 
		 str+='<td></td>';

		 str+='<td colspan="3"><label style="color:#0088cc;"><b><input type="radio"  id="selectAll" value="Select All Parties" name="partiesSel"  onclick="selectAllCheckBoxes(this.value)">Select All</b></label></td>';
		 str+='<td  colspan="3"><label style="color:#0088cc;"><b><input type="radio" id="deSelectAll" value="Unselect All Parties"  name="partiesSel"  onclick="deSelectAllCheckBoxes(this.value)">Unselect All</b></label></td>';
		

		 str+='</table>'; 
		 str+='</td></tr></table>';
		 str+='</div>';
   $("#mandalElecResultsParties").html(str);
  
}

function dynamicSort(property) {
    var sortOrder = 1;
    if(property[0] === "-") {
        sortOrder = -1;
        property = property.substr(1, property.length - 1);
    }
    return function (a,b) {
        var result = (a[property] < b[property]) ? -1 : (a[property] > b[property]) ? 1 : 0;
        return result * sortOrder;
    }
}

function getElectionData()

{

	 if(type == "mandal")
		 {
		$("#mandalElecResultsButton").html('<span id="alliance"><input id="includeAlliancesDiv" type="checkbox" /><label  for="includeAlliancesDiv"><b>Include Aliance Parties</b></label></span>&nbsp;&nbsp;<input type="button"  class="btn btn-success" value="Submit" onclick="getPanchayatData()">');
		 $("#mandalElecResultsButton1").html('<input type="radio" name="votes"  class="btn" value="percentage" id="votingPercentageID" checked="true" onclick="getPanchayatData()"/>By Percentage&nbsp;<input type="radio" name="votes"  class="btn" value="validvotes" id="votingValuesID" onclick="getPanchayatData()"/>By Votes');
		  getPanchayatData();
		 }
		 if(type == "panchayat")
		 {
		$("#mandalElecResultsButton").html('<span id="alliance"><input id="includeAlliancesDiv" type="checkbox" /><label  for="includeAlliancesDiv"><b>Include Aliance Parties</b></label></span>&nbsp;&nbsp;<input type="button"  class="btn btn-success" value="Submit" onclick="getResultsForBooths()">');
		 $("#mandalElecResultsButton1").html('<input type="radio" name="boothvotes" class="btn" value="percentage" id="boothvotingPercentageID" checked="true" onclick="getResultsForBooths()"/>By Percentage&nbsp;<input type="radio"  name="boothvotes" class="btn" value="validvotes" id="boothvotingValuesID" onclick="getResultsForBooths()"/>By Votes');
		  getResultsForBooths();

		 
		}
		
		if(type == "constituency")
		{
		$("#mandalElecResultsButton").html('<span id="alliance"><input id="includeAlliancesDiv" type="checkbox" /><label  for="includeAlliancesDiv"><b>Include Aliance Parties</b></label></span>&nbsp;&nbsp;<input type="button"  class="btn btn-info" value="Submit" onclick="getResultsForConstituency()">');
		 $("#mandalElecResultsButton1").html('<input type="radio" name="constituencyvotes" class="btn" value="percentage" id="constituencyvotingPercentageID" checked="true" onclick="getResultsForConstituency()"/>By Percentage&nbsp;<input type="radio"  name="constituencyvotes" class="btn" value="validvotes" id="constituencyvotingValuesID" onclick="getResultsForConstituency()"/>By Votes');
		  getResultsForConstituency();
		}
	  
}
  function getPanchayatData(){
  	emptyDivs();
  var parties = '';
  var elections = '';
  var str = '';
    $("#mandalElecResultsErrMsg").html("");
    $('.partySelForPanc').each(function(){
	  if($(this).is(':checked'))
	    parties+=','+$(this).val();
    });
	$('.elecSelForPanc').each(function(){
	  if($(this).is(':checked'))
	    elections+=','+$(this).val();
    });
	var btnName=$('input:radio[name=votes]:checked').val();
	 var invalid = false;
	 if(parties.length == 0)
	 {
	   invalid = true;
	   str+="<div>Please check atleast one party</div>";
	 }
	 if(elections.length == 0)
	 {
	   invalid = true;
	   str+="<div>Please check atleast one election</div>";
	 }
	 if(invalid){
	   $("#mandalElecResultsErrMsg").html(str);
	   return;
	 }
	 $("#container").html('<img alt="Processing Image" src="./images/icons/goldAjaxLoad.gif"/>');
     var jsObj=
			{
				
				tehsilId:id.substr(1),
				parties:parties.substr(1),
				elections:elections.substr(1),
				btnName:btnName,
				includeAlliance:$("#includeAlliancesDiv").is(':checked'),
				task:"getResults"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatWiseElectionResultsAction.action?tehsilId="+id.substr(1)+"&"+rparam;	
   
		callAjax(jsObj,url);
  }
  
  var chart;
    var globalResultes;

  function buildLineChart(myResults,jsObj){

	var linechartDataArr = new Array();
	var results = new Array();
	var data = new Array();
	 if(myResults[0].length == 0 || myResults[1].length == 0) {
             $("#container").html("<b>Data Not Available</b>");
             return;
       }
	   
	    for(var i in myResults[0]){
	      if(linechartDataArr.indexOf(myResults[0][i].constituencyName) == -1)
				linechartDataArr.push(myResults[0][i].constituencyName);
					
		}
			if(jsObj.btnName == "percentage"){
			 results = myResults[1];
			var textValue = 'Votes Percent( % )';
			}
		if(jsObj.btnName == "validvotes"){
			results = myResults[2];
			var textValue = 'Voters Votes';
		}
	
         for(var i in results){	
           var obj = {};
           var obj1 = new Array();		   
           obj["color"] = getColorCodeForParty(i);		   
           obj["name"] = i;	
		   for(var j in results[i]){
		     obj1.push(parseFloat(results[i][j]));
		   }
           	obj["data"] = obj1;	 
            data.push(obj);			
	    }
		
		
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'line',
               /* marginRight: 130,
                marginBottom: 25 */
            },
			
            title: {
                text: 'Percentage and Votes Analysis'
            },
			
			 
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
				min: 0,
                title: {
                    text: textValue
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
						if(jsObj.btnName == "percentage")
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'%';
						else
						return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +' Votes Earned';
                }
            },
			
           /* legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },*/
            series: data
        });
		
		$('tspan:last').hide();

		
		$('#show_votes').click(function(){
			var chart = $('#container').highcharts();
			var series = chart.series;
		
			var _redraw = chart.redraw;
			chart.redraw = function(){};
		
			$.each(series, function(index, series1) {
				series1.show();
			});
		
			chart.redraw = _redraw;
			chart.redraw();
		
		});
	
		$('#hide_votes').click(function(){
			var chart = $('#container').highcharts();
			var series = chart.series;
		
			var _redraw = chart.redraw;
			chart.redraw = function(){};
		
			$.each(series, function(index, series1) {
				series1.hide();
			});
		
			chart.redraw = _redraw;
			chart.redraw();
		});
	
		/*$('input[name="show_hide_votes"]').click(function(){
		var chart = $('#container').highcharts();
		var series = chart.series;
			if(this.value === "show"){
				var _redraw = chart.redraw;
				chart.redraw = function(){};

				$.each(series, function(index, series1) {
                    series1.show();
                });
				
				chart.redraw = _redraw;
				chart.redraw();
			}
			else if (this.value === "hide"){
				var _redraw = chart.redraw;
				chart.redraw = function(){};
				
				$.each(series, function(index, series1) {
                   series1.hide();
                });
				
				chart.redraw = _redraw;
				chart.redraw();
			}
		});*/
			
		globalResultes =  myResults;
		try{
		buildSelectBoxes(myResults ,jsObj );
		}catch(e){}
  }
  /*
   * this method  used to build deviation graph basedon parties gained votes percentage .
   * selObj1 and selObj2 as of now length must be same 
   * myResults  are depend to party permonace report see globalResultes object 
  */
  function buildDeviationChart(myResults ,selObj1 , selObj2 ,myArray){
    

	$('#deviation2').html("");
     $('#subDev2').show();
	
	
	var linechartDataArr = new Array();
	var results = new Array();
	var data_perc=[];
	var data_vv=[];
		   var data = new Array();
		 //  var myArray = ['ALLUR','BATRAKAGOLLU']; 
		  var newResults = new Object();
     
	    for(var i in myResults[0]){
	      if(linechartDataArr.indexOf(myResults[0][i].constituencyName) == -1){
			/* commented by srishailam
				linechartDataArr.push(myResults[0][i].constituencyName); */
				 //  $("#selId3").append('<option value="' +myResults[0][i].constituencyName+'">' +myResults[0][i].constituencyName+ '</option>');	 
		}
		 if (myArray != null  )
		 for (var j in myArray)
		 { 
		 if(myResults[0][i].constituencyName.toUpperCase().trim() == myArray[j].toUpperCase().trim() ){
		//alert( myResults[0][i].constituencyName.toUpperCase());
		 <!-- Start by srishailam -->
				if(linechartDataArr.indexOf(myResults[0][i].constituencyName) == -1){
					linechartDataArr.push(myResults[0][i].constituencyName);
				}
		<!-- end by srishailam -->
		 var pname = myResults[0][i].partyName ;
          var aryObj = newResults[pname];
          if(aryObj)
		  { 
		  aryObj.push(myResults[0][i].votesPercent);
		  }else
		  {
		  var valObj = new Array();
		  valObj.push(myResults[0][i].votesPercent);
		  newResults[myResults[0][i].partyName] = valObj;
		   }
		
		}
		} 
		 
		}
		
		if(myArray != null)
		results = newResults;
		else
		 results = myResults[1];
		
	    
   
		   var obj1 = new Array();	
		   var obj2 = new Array();	
		   var obj = {};
	
		var selectedOptions = new  Array();
		
		if(selObj1 != null)
		selectedOptions = selObj1;
		else
		return;
		
		for(var k = 0 ; k < selectedOptions.length-1 ; k++ ) 
		{  
		for(var l in results[selectedOptions[k]]){
		     obj1.push(parseFloat(results[selectedOptions[k]][l]) - parseFloat(results[selectedOptions[k+1]][l]) );
		   }
		
		}
		
		 var selectedOptions1 = new  Array();
	
		if(selObj2 != null)
		selectedOptions1 = selObj2;
		for(var k = 0 ; k < selectedOptions1.length-1 ; k++ ) 
		{  
		for(var l in results[selectedOptions1[k]]){
		     obj2.push(parseFloat(results[selectedOptions1[k]][l]) - parseFloat(results[selectedOptions1[k+1]][l]) );
		   }
		
		} 
		    $(".ourGuideLine").text("You Are Seeing How   "+selectedOptions[0]+" Got Variations By Comparing With "+selectedOptions[1]);
		   //  thise data used for deviation between any party in diff elections or two parties in same election 
		    obj['name']='Deviation Between '+selectedOptions[0]+' And '+selectedOptions[1];
		    obj['type']='column';
			obj['color']='#2f7ed8';
			//obj['yAxis']=1;
			//obj['tooltip']=tooltipobj;
           	obj["data"] = obj1;	 
            data_perc.push(obj);
			if( selObj2 != null ) {
			
			//  thise data used for deviation between  two parties in comparision with another two parties
			var  obj3 = {};
			 obj3['name']='Deviation Between'+selectedOptions1[0]+' And '+selectedOptions1[1];
		    obj3['type']='column';
			obj['color']='#AA4643';
			//obj['yAxis']=1;
			//obj['tooltip']=tooltipobj;
           	obj3["data"] = obj2;	 
           data_perc.push(obj3); 
		   
         }
		 if(selObj2 != null)
		 {
		  $(".ourGuideLine").text("We Are Comparing  voter pencentage variation Between Two Elections("+selectedOptions[0]+' ,  '+selectedOptions[1]+") with variation of ("+selectedOptions1[0]+' ,  '+selectedOptions1[1]+") ");
		 
		 
		 }
       
	   // not that much best 
		for(var i=0;i<data_perc.length;i++){
			var ob=data_perc[i];
			
			
			//data.push(ob1);
			data.push(ob);
			
		} 
	
		 $('#deviation2').highcharts({
            chart: {
                type: 'column',
             
				zoomType: 'xy'
            },
			
			title: {
                text: 'Deviation  Analysis Based on Votes Percentage Gained '
            },
            
            xAxis: [{
                categories:linechartDataArr,
				labels: {
                                align:'right',
                                style: {
                                      cursor: 'pointer',
                                      fontSize: '12px',
                                },
                                rotation: -45,
                            } 
            }],
            yAxis: [{ // Secondary yAxis
				
                gridLineWidth: 1,
                title: {
                    text: 'Percentage',
                    style: {
                        color: '#AA4643'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'%';
                    },
                    style: {
                        color: '#AA4643'
                    }
                },
                opposite: true
            }],
			
			colors: ['#2f7ed8','#0d233a','#8bbc21','#910000','#1aadce','#492970','#f28f43',  '#77a1e5', '#c42525', '#a6c96a'],
			
			legend: {
					margin:20
				},
			
				tooltip: {
	                formatter: function() {
	                    return '<b>'+ this.x +'</b><br/>'+'<b>'+ this.series.name +'</b>'+
	                        ' : ' +Highcharts.numberFormat(this.y,2)
							 ;
	                }
	            },
		
            series:data
			
			
        });  
				
    }
 function emptyDivs()
 {
           $('#deviation2').html("");
           $("#selId1").empty();
		   $("#selId2").empty();
		   $("#selId3").empty();
           $(".ourGuideLine").text("");
 }
 function buildSelectBoxes(myResults , jsObj)
 {     var linechartDataArr = new Array();
       var results = new Array();
		 emptyDivs();
			results = myResults[1];
			 for(var i in myResults[0]){
	      if(linechartDataArr.indexOf(myResults[0][i].constituencyName) == -1){
				linechartDataArr.push(myResults[0][i].constituencyName);
				 $("#selId3").append('<option value="' +myResults[0][i].constituencyName+'">' +myResults[0][i].constituencyName+ '</option>');	 
		}
		}	
	   for(var i in results)
		   {
		    $("#selId1").append('<option value="' + i + '">' +i+ '</option>');
		    $("#selId2").append('<option value="' + i + '">' +i+ '</option>');
           }
		  var size = Object.size(results);
		 if(size == 2)
		 {
		  $("#selId1").find('option')[0].selected = 'selected';
		  $("#selId2").find('option')[1].selected = 'selected';
		 getSelected();
		 }
  }
  Object.size = function(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
};


 $("#meanClick").live('click',
 function(){
 
 getSelected();
 });
 $("#guideClick").live('click',
 function(){
 var str='';
  str+='<p>';
str+='<span class="ui-icon ui-icon-circle-check" style="float: left; " ></span>';
 str+= ' Selecting Area is Optional.';

str+='</p>';
 str+='<p>';
str+='<span class="ui-icon ui-icon-circle-check" style="float: left;"></span>';
 str+= ' We Can Compare One Party Deviation In TWO Elections.';
  str+='<p >';
 str+= ' LIKE ';
 str+='<span class="myTittle">'
 str+= 'TDP IN Assembly 2009 ';
 str+='</span>';
 str+= ' AND ';
 str+='<span class="myTittle">'
 str+= 'TDP IN Assembly 2004 ';
 str+='</span>';	 
str+='</p>';


str+='</p>';
str+='<p>';
 str+='<p>';
str+='<span class="ui-icon ui-icon-circle-check" style="float: left;"></span>';
 str+= ' We Can Compare One Party Deviation With  Other Party In Same Election .';
  str+='<p >';
 str+= ' LIKE ';
 str+='<span class="myTittle">'
 str+= 'TDP IN Assembly 2009 ';
 str+='</span>';
 str+= ' AND ';
 str+='<span class="myTittle">'
 str+= 'INC IN Assembly 2009 ';
 str+='</span>';	 
str+='</p>';
str+='</p>';
str+='<p>';
 str+='<p>';
str+='<span class="ui-icon ui-icon-circle-check" style="float: left; "></span>';
 str+= ' We Can Compare One Party Deviation In TWO Elections  with Other Party  Deviation In SAME TWO Elections/Difeerent TWO ELECTIONS.';
 str+='<p>';
 str+= ' LIKE ';
 str+='<span class="myTittle">'
 str+= '[TDP IN Assembly 2009 ';
 str+='</span>';
 str+= ' AND ';
 str+='<span class="myTittle">'
 str+= 'TDP IN Assembly 2004] ';
 str+='</span>';
 str+= ' Deviation With  ';
  str+='<span class="myTittle">'
 str+= '[INC IN Assembly 2009 ';
 str+='</span>';
 str+= ' AND ';
 str+='<span class="myTittle">'
 str+= 'INC IN Assembly 2004 ]';
 str+='</span>';
 str+= ' Deviation. ';
 
str+='</p>';
str+='<p align="center">';
 str+= ' OR ';
 str+='</p>';
  str+='<p>';
 str+= ' LIKE ';
 str+='<span class="myTittle">'
 str+= '[TDP IN Parliament 2012 ';
 str+='</span>';
 str+= ' AND ';
 str+='<span class="myTittle">'
 str+= 'INC IN Parliament 2012] ';
 str+='</span>';
 str+= ' Deviation With  ';
  str+='<span class="myTittle">'
 str+= '[TDP IN Parliament 2012 ';
 str+='</span>';
 str+= ' AND ';
 str+='<span class="myTittle">'
 str+= 'YSRC  IN Parliament 2012 ]';
 str+='</span>';
 str+= ' Deviation. ';
 
str+='</p>';
 
 str+='</p>';

    $("#instructionDialog" ).html(str);
 $("#instructionDialog" ).dialog({width: 600} );
 });
 $("#reverseClick1,#reverseClick2").live('click',
 function(event){
  reverseArrayElements(this.attributes.selid.value);
 });


 function getSelected()
  {  
        var a1   =       $("#selId1").val();
		var a2   =      $("#selId2").val();
		 var a3  =      $("#selId3").val();
		 var destObj = new Array();
		var selObj1 = new Array();
		var selObj2 = new Array();
		
	if(a3 == null )
	destObj = null;
	else if(a3.length == 1)
	destObj.push(a3+"")
	else if(a3.length > 1)
	destObj = a3;
	

  if( (a1 != null && a2 != null) && a1.length == a2.length   )
  {
  if(a1.length == 1 &&  a1+"" != a2+"")
  {
  selObj1.push(a1);
  selObj1.push(a2);
  
  buildDeviationChart(globalResultes,selObj1,null,destObj);
  
  }else if(a1.length == 2 )
  {
  //alert(a1+'----'+a2);
   buildDeviationChart(globalResultes,a1,a2,destObj);
  
  }else{
   alert('Invalid Selection');
  }
  
  
  }else
  {
  alert('Invalid Selection');
  
  }
  
  }
  function reverseArrayElements(divId)
  {
    var options = $('#'+divId).find('option');
		//alert(options+'======'+options.length)
		  $('#'+divId).empty();
		for(var i = parseInt(options.length) -1 ; i >=0 ;i--)
		{
		     
			 
			    $('#'+divId).append(options[i]);
				
		}
    }
  
 
function buildVotersBasicInfo(votersbasicinfo,jsObj)
{
	$("#votersBasicInfoSubChartDiv").html('');
	$("#votersBasicInfoSubDiv").html('');
	$("#basicInfoAjaxDiv").css('display','none');
	$("#votersInfoAjaxImg").css("display","none");
	var str = '<div id="votersBasicInfoDivSub">';
	var title = " Voters Basic Information of "+jsObj.typename+" in "+jsObj.year+"";
	if(votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0)
	{
		if(jsObj.type == "constituency"){
		/* sublevelHeading updated by srishailam */
		$('#sublevelHeading').html('<h3> '+votersbasicinfo.votersInfoForMandalVOList[0].type+'/Muncipality Wise Party Performance Analysis in Different Elections of '+jsObj.typename+' Constituency</h3>');	
			title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+"/Muncipality Wise Voters Information in "+jsObj.typename+" Constituency";
			heading = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+"/Muncipality Wise Analysis of "+jsObj.typename+" ";
			casteChartHeading = '<h4> '+votersbasicinfo.votersInfoForMandalVOList[0].type+'/Muncipality Wise Caste Analysis in '+jsObj.typename+' Constituency</h4>';
		$("#votersBasicInfoTitleDiv").append('<h3>'+title+'</h3>');
		$("#headingDiv1").append('<h3>'+heading+'</h3>');
		}
		else{
		$('#sublevelHeading').html('<h4> '+votersbasicinfo.votersInfoForMandalVOList[0].type+' Wise Party Performance Analysis in Different Elections of '+jsObj.typename+'</h4>');	
		 title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" Wise Voters Information in "+jsObj.typename+" ";
		 heading = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" Wise Analysis of "+jsObj.typename+" ";
		 casteChartHeading = '<h4> '+votersbasicinfo.votersInfoForMandalVOList[0].type+' Wise Caste Analysis in '+jsObj.typename+'</h4>';
		 $("#votersBasicInfoTitleDiv").append('<h3>'+title+'</h3>');
		 $("#headingDiv1").append('<h3>'+heading+'</h3>');
		}
	}

	 if(votersbasicinfo.votersInfoForMandalVOList == null || votersbasicinfo.votersInfoForMandalVOList.length == 0)
	 {
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);
			$("#votersBasicInfoSubChartDiv").css('border','1px solid #FFF');
			//$("#votersBasicInfoSubDiv").css('border','1px solid #FFF');

			$("#votersBasicInfoMsgDiv").html("<span id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</span>");
		 return;
	}
	if(votersbasicinfo != null && votersbasicinfo.datapresent)
	{
		
		$("#votersBasicInfoSubChartDiv").css('border','1px solid black');
		///$("#votersBasicInfoSubDiv").css('border','1px solid black');
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
			//strl ='';
    		//	strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
    		//	strl += '<tbody><td style="text-align:center;">'+totalvoter+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersByUser+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersByUser+'</td></tbody>';
			
			//strl += '</table>';
			//$("#assAndUnass").html(strl);
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
	          //$("#votersBasicInfoSubDiv").css("border","1px solid black");
			 }
		
		str = '';
		if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       
		YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var name = oRecord.getData("name");
		var id = oRecord.getData("id");
		var type = oRecord.getData("type");
		
		if(type == "booth" || type == "LOCAL ELECTION BODY" || type == "Booth" || buildType == "hamlet"){
		
		elLiner.innerHTML ='<span id="openCompleteInfoFormId" style="cursor:default;text-decoration:none;">'+name+'</span>';
		}
		else
		{
		elLiner.innerHTML ='<a style="cursor:pointer;" id="openCompleteInfoFormId" onclick=" openCompleteInfoForm('+id+',\''+name+'\',\''+type+'\');">'+name+'</a>';
		}
	}
	var areaType = '';
	if(votersbasicinfo.votersInfoForMandalVOList[0].type == 'Mandal')
		areaType = 'Mandal/Muncipality';
	else
		areaType = votersbasicinfo.votersInfoForMandalVOList[0].type;
		var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"name", label:areaType , sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
		    	           	{key:"totalMaleVoters", label: "Male Voters", sortable: true},
							
							{key:"totalFemaleVoters", label: "Female Voters", sortable: true},
		    				{key:"totVoters", label: "Total Voters",sortable:true},
							{key:"totPercent", label: votersbasicinfo.votersInfoForMandalVOList[0].type+" % Share", sortable: true}
		    	        ]; 
		var myConfigs = {};
		var myDataSource = new YAHOO.util.DataSource(votersbasicinfo.votersInfoForMandalVOList);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "name","maleVoters","femaleVoters","totVoters","totPercent"]
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
//getLatestCastsSubcategoryWise();
}

function openCompleteInfoForm(id,name,type){
var myBuildType ='';
var mndId = 0;
if(type == "Mandal" || type == "mandal")
{
id="2"+id;
type = "mandal";
}
if(type == "Panchayat" || type == "panchayat")
{
    var reqId = "${id}";
    mndId = reqId.substr(1);
	name=name +" "+ type;
	type = "panchayat";
	myBuildType ='booth';
}
if(type == "LOCAL ELECTION BODY" || type == "localElec")
{
return;
}
var urlStr="subRegionsWiseAnalysisAction.action?id="+id+"&mandalId="+mndId+"&publicationDateId=${publicationDateId}&type="+type+"&publicationYear=${publicationYear}&buildType="+myBuildType+"&constituencyId=${constituencyId}&typeName="+name+"";
var updateBrowser = window.open(urlStr,"editAnnouncement"+type,"scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();	
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
		str +='<h3>Mandal Wise Census Information in '+name+'</h3>';
		str +='<table id="censusTab" class="table table-bordered table-striped table-hover">';
		str +='<tr>';
		str +='<th>Mandal</th>';
		str +='<th>Population</th>';
		str +='<th>SC Population</th>';
		str +='<th>ST Population</th>';
		str +='<th>Literate Population</th>';
		str +='<th>Illiterate Population</th>';
		str +='<th>Working Population</th>';
		str +='</tr>';

		for(var i in result)
		{
			str +='<tr>';
			str +='<td><a href="javaScript:{}"  onclick="openCompleteInfoForm('+result[i].mandalID+',\''+result[i].mandalName+'\',\'mandal\')">'+result[i].mandalName+'</a></td>';
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
	str +='<h3>Mandal Wise Voting Trendz</h3>';
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
				/*str += '<td><a  href="localBodyElectionAction.action?stateId=1&localBodyElectionTypeId=5&localBodyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';*/
				str += '<td><a  href="javascript:{}">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
			else
				str += '<td><a  href="javascript:{}" onclick="openCompleteInfoForm('+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+',\''+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'\',\'mandal\')">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
				/*str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';*/
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
	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource,{caption:"Mandal Wise Election Results For  "+mainname+" "+type+" In "+constituencyResults.electionYear+" "});
	}
	

function getConstituencyResults(elecYear){
          $("#censusSelectDiv").show();
         $("#electionResultsInConstituencyDiv").show();
         $("#labelRadioDiv").show();
         $("#resultsDataTableDiv").show();

         $("#parliamentElectionResultsDivNew").hide();
	
	var jsObj = {
			constituencyId:id,
			electionYear:elecYear,
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
			constituencyId:constituencyId,
			task:"getConstituencyElections"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
	}
	
	
function getConstiElecYearsForAss(){
   var jsObj = {
			constituencyId:'${parliamentConstituencyId}',
			task:"getConstituencyElectionsYersForAss"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getParliamentResults(elecYear){
		if(elecYear == 0 || elecYear == '0')
		 return;
		 $("#censusSelectDiv").hide();
         $("#electionResultsInConstituencyDiv").hide();
         $("#labelRadioDiv").hide();
         $("#resultsDataTableDiv").hide();

         $("#parliamentElectionResultsDivNew").show();
		counter++;
	    document.getElementById("censusAjaxImgDivForParlinit").style.display ="block";	
		var jsObj = {
				constituencyId:id,
				electionYear:elecYear,
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
  var buildType = "hamlet";
  if(type == "panchayat")
	  buildType = "${buildType}";
  var jsObj=
		{		
				type:type,	
				id:id,
				typeName:mainname,
				publicationDateId:publicationId,
				constituencyId:constituencyId,
                buildType:buildType,
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
		  
	   }); */
	   //var titleString= $("#sublevelHeading").text();
	
	   //alert(myChart[0]['name']+"---"+myChart[0]['data'].length);
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
		  $('#casteDetailsDiv').hide();
		  return;
		}  
		//if(type != 'booth')
		//{
		str +='<table id="subLevelTable">';
		if(type == 'constituency')
		str+='<h3 id="sublevelHeading">Mandal/Muncipality Wise Caste Statistics In '+typeName+' Constituency</h3>';
		else if(type == "mandal"){

			if(jsObj.id.substring(0,1) == "1"){
				var muncipalType = myresults.castVosList[0].muncipalityType;

                if( muncipalType == "MUNCIPALITY" || muncipalType == "CORPORATION")
				 str+='<h4 id="sublevelHeading">Booth Wise Caste Statistics In '+typeName+' </h4>';
				else
				 str+='<h4 id="sublevelHeading">Ward Wise Caste Statistics In '+typeName+' </h4>';
			}else
		str+='<h4 id="sublevelHeading">Panchayat Wise Caste Statistics In '+typeName+' </h4>';
		}
		else if(type =="panchayat")
		{
			var temp = "";
		  if(buildType == "hamlet")
			temp = "Hamlet";
		  else
			temp = "Booth";

		 str+='<h4 id="sublevelHeading">'+temp+' Wise Caste Statistics In '+typeName+' </h4>';
		}
		else if(type =="ward")
		str+='<h4 id="sublevelHeading">Booth Wise Caste Statistics In '+typeName+' Ward</h4>';
		else if(type == "booth")
		{
		 str+='<h4 id="sublevelHeading">Hamlet Wise Caste Statistics In'+typeName+'Booth</h4>';	
		}
		
		else if(type =="hamlet"){
		   if(res == "booth")
		   str+='<h4 id="sublevelHeading">Booth Wise Caste Statistics In '+typeName+' Hamlet</h4>';
		else
		str+='<h4 id="sublevelHeading">Locality Wise Caste Statistics In '+typeName+' Hamlet</h4>';
			}
			else if(type =="customWard"){
		   if(res == "booth")
		   str+='<h4 id="sublevelHeading">Booth Wise Caste Statistics In '+typeName+' Ward</h4>';
		//else
		//str+='<h4 id="sublevelHeading">Locality Wise Caste Statistics In '+typeName+' Hamlet</h4>';
			}
		
		str+='<thead>';
		str+='<tr>';
		
		if(type == "constituency")
		str +='<th>Mandal/Muncipality</th>';
		if(type == "mandal"){

			if(jsObj.id.substring(0,1) == "1"){
				var muncipalType = myresults.castVosList[0].muncipalityType;
				

                if( muncipalType == "MUNCIPALITY" || muncipalType == "CORPORATION")
				 str +='<th>Booth</th>';
				else
				 str +='<th>Ward</th>';
			}else
		    str +='<th>Panchayat</th>';
		}


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
		else if(type =="customWard"){
		   if(res == "booth")
		   str +='<th>Booth</th>';
		//else
		//str+='<h4 id="sublevelHeading">Locality Wise Caste Statistics In '+typeName+' Hamlet</h4>';
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
		//console.log(constMgmtMainObj.castStatssubArray);//sasir
		if(castesSlctdList == null|| (castesSlctdList != null && castesSlctdList.indexOf(constMgmtMainObj.castStatssubArray[i].caste)!= -1))
		{
		if(lgndItemSlctd == null|| (lgndItemSlctd != null && lgndItemSlctd.indexOf(constMgmtMainObj.castStatssubArray[i].mandal)== -1))
		{
		str+='<tr>';
		str+='<td>'+(constMgmtMainObj.castStatssubArray[i].mandal)+'</td>';
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
		
	str+='<td><a href="javascript:{}" onclick="getVotersInACasteForPanchayatHamlet('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'hamlet\',\'boothNo - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="hamlet")
		{
		if(jsObj.resultFor == "booth")
				str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+constMgmtMainObj.castStatssubArray[i].locationId+','+jsObj.id+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\''+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';

		else
		str+='<td><a href="javascript:{}" onclick="getVotersInACasteForLocality('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+','+constMgmtMainObj.castStatssubArray[i].castStateId+',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\''+constMgmtMainObj.castStatssubArray[i].locationId+'\',\'Locality\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}else if(type =="customWard"){
		   if(res == "booth")
		   	str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+constMgmtMainObj.castStatssubArray[i].locationId+','+jsObj.id+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\''+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';

		else
		str+='<td><a href="javascript:{}" onclick="getVotersInACasteForLocality('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+','+constMgmtMainObj.castStatssubArray[i].castStateId+',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\''+constMgmtMainObj.castStatssubArray[i].locationId+'\',\'Locality\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
			}
		else if(type =="booth")
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
	//$('#subLevelTable tr').removeClass("odd");
	//$('#subLevelTable tr').removeClass("even");
	//$('#subLevelTable td').removeClass("sorting_1");
	//}
	
		if(lgndItemSlctd==null)
		{
			 var percentage = $( "#slider" ).slider( "value" );
			 buildHamletWiseCastResultsGraph(null,percentage);
		}
			
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
function buildVoterDetailsTable(result,type,retrieveType){

	if(result.votersDetailsVO == null || result.votersDetailsVO.length == 0){
		$('#votersDiv4').hide();
		return false;
	}
	else
	{
		$('#votersDiv4').show();
	}

	var noteString = '';
	if(type == "constituency")
	{
		noteString = name + " Constituency"; 
	}
	else if(type == "mandal")
		noteString = name ;
	else if(type == "panchayat")
		noteString = name;
	else if(type == "localElectionBody")
		noteString = name;
	else if(type == "hamlet")
	noteString =  "Hamlet" +" "+"Details";
	else 
		noteString = name;

	$('#voterDetailsNote').html('<div align="center"><h3>'+noteString+" "+"voters details"+' in '+publicationYear+'</h3></div>');
	
	var str='';
	str+='<table class="ageTable table table-bordered table-hover" id="ageWiseDetailsTable" >';
	
	str+='<thead class="info">'
	str+='<tr>'
	str+='<th rowspan="2" id="ageRangeId">Age Range</th>';
	str+='<th colspan="2">Total Voters</th>';
	str+='<th colspan="2">Male</th>';
	str+='<th colspan="2">Female</th>';
	//str+='<th colspan="2">UnKnown</th>';
	str+='</tr>';
    
	str+='<tr>';
	str+='<th>Total Voters</th>';
	str+='<th>Total Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	//str+='<th>Voters</th>';
	//str+='<th>Percentage</th>';
	str+='</tr>';
	str+='</thead><tbody>';
	if(result.votersDetailsVO == null ||result.votersDetailsVO.length ==0 ){
	str='<span style="color:red">No Data Available</span>';
	//$("#ageLink").hide();
	return;
}
	for(var i in result.votersDetailsVO){

	
	str+='<tr>';
	str+='<td>'+result.votersDetailsVO[i].ageRange+'</td>';
	str+='<td>'+result.votersDetailsVO[i].totalVoters+'</td>';

	if(result.votersDetailsVO[i].totalVotersPercent != null)
		 str+='<td>'+result.votersDetailsVO[i].totalVotersPercent.toFixed(2)+'</td>';	 
	else
		str+='<td>0.00</td>';
	

	str+='<td>'+result.votersDetailsVO[i].totalMaleVoters+'</td>';
    
	if(result.votersDetailsVO[i].totalMaleVotersPercent != null)
		str+='<td>'+result.votersDetailsVO[i].totalMaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';

	str+='<td>'+result.votersDetailsVO[i].totalFemaleVoters+'</td>';

	if(result.votersDetailsVO[i].totalFemaleVotersPercent != null)
	  str+='<td>'+result.votersDetailsVO[i].totalFemaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';
		
	str+='</tr>';

	}

	str+='</tbody>';
	str+='</table>';
	$('#tableDiv').html(str);
	$('#ageWiseDetailsTable').dataTable();
	if(retrieveType == "all"){
		$('#tableDiv').css('display','block');
		$('#voterDetailsNote').css('display','block');
	}
}

function buildAgewiseDetails(results , obj){

   var type = obj.type;
   var innerResults;
   var noteString;
	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal/Muncipality Wise voters age details";
	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
			noteString = "Panchayat Wise voters age details";
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		if(buildType == "hamlet")
		noteString = "Hamlet Wise voters age details";
		else
	  	noteString = "Booth Wise voters age details";
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
		   noteString = "Ward Wise voter age details";
		else
			 noteString = "Booth Wise voter age details";
	}
   else if(type == "ward"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth Wise voter age details" ;
	}
	else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea Wise voter age details" ;
	}
	else if( (type="hamlet" && obj.type == "hamletBooths") || obj.type == "wardBooths"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth Wise voter age details";
	}
	else if( type="booth" && obj.type == "boothHamlets"){
		innerResults = results.boothVotersDetails;
		noteString = "Hamlet Wise voter age details";
	}

	if(innerResults.length == 0){
		return false;
	}

	//$('#voterAgewiseDetailsNote').html('<div ><h3>'+noteString+'</h3></div>');
//$('#voterAgewiseDetailsNote').html('<div><h3 id="sublevelHeading">'+noteString+'</h3></div>');

	var str='';
	str+='<h3 id="sublevelHeading">'+noteString+'</h3>';
	str+='<table  class="ageTable table table-bordered table-hover" id="mandalWiseVoterAgeTable" >';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal/Muncipality Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	  	if(buildType == "hamlet")
		str+='<th rowspan="2">Hamlet Name</th>';
		else
	  str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody"){
		if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
	     str+='<th rowspan="2">Ward</th>';
		else
 		 str+='<th rowspan="2">Booth</th>';
	}
	else if(type == "ward" || (type="hamlet" && obj.type == "hamletBooths") || obj.type == "wardBooths" )
	   str+='<th rowspan="2">Booth No</th>';
	   else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">Local Area</th>';
	   
	else if( type="booth" && obj.type == "boothHamlets"){
		str+='<th rowspan="2">Hamlet Name</th>';
	}
	
	
//18111	 
  
	str+='<th  rowspan="2">Total Voters</th>';
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
	
		  var oldstr ="";

for(var i=0;i<innerResults.length;i++){

var YDataObjectTemp = new Object();
  if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';

	if(type == "constituency"){
		if(innerResults[i].areaType != 'localElec' )
			str+='<td><a href="javaScript:{}"  onclick="openCompleteInfoForm('+innerResults[i].id+',\''+innerResults[i].tehsilName+'\',\''+innerResults[i].areaType+'\')">'+innerResults[i].tehsilName+'</a></td>';
		else 
			str+='<td>'+innerResults[i].tehsilName+'</td>';
	}
	else if(type == "mandal")
	 str+='<td><a href="javaScript:{}"  onclick="openCompleteInfoForm('+innerResults[i].id+',\''+innerResults[i].panchayatname+'\',\''+innerResults[i].areaType+'\')">'+innerResults[i].panchayatname+'</a></td>';
	else if(type == "panchayat")
	 	if(buildType == "hamlet")
		str+='<td>'+innerResults[i].hamletName+'</td>';
		else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	 str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
	 str+='<td>'+innerResults[i].boothName+'</td>';
      else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	    else if((type="hamlet" && obj.type == "hamletBooths") ||(type="booth" && obj.type == "boothHamlets") || obj.type == "wardBooths" )
	   str+='<td>'+innerResults[i].hamletName+'</td>';
	  
	  /*  var mystr = str;
	  
       if(i != 0)
	   {
	   
     mystr= mystr.replace(oldstr);
	 oldstr = str;
	   } else
	     oldstr=mystr; */
	  /* 	 var str4 = str.match(/(<tr><td>(.*?)<\/td>)/g);
		
       var str2 = str4[i].replace("<tr><td>","").replace("</td>","");
	   YDataObjectTemp['name'] = str2;
	   var ageTemp = new Object();
	 
	   ageTemp['18-25']   =      innerResults[i].totalVotersFor18To25;
	   ageTemp['26-35']   =      innerResults[i].totalVotersFor26To35;
		ageTemp['36-45']  =	   innerResults[i].totalVotersFor36To45;
		ageTemp['46-60']   =   innerResults[i].totalVotersFor46To60;
		ageTemp['60-Above'] =	   innerResults[i].totalVotersForAbove60;
	   YDataObjectTemp['data'] = ageTemp;
	   YDataObject.push(YDataObjectTemp);*/
	   
	str+='<td>'+innerResults[i].totalVoters+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].votersPercentForAbove60+'</td>';
	str+='</tr>';
  }
}
str+='</tbody>';
str+='</table>';
/*var res = str.match(/(<tr><td>(.*?)<\/td>)/g);
for (var k in res )
{
yaxisOpt.push(res[k].replace("<tr><td>","").replace("</td>",""));
}*/

$('#agewiseDetails').html(str);

$('#mandalWiseVoterAgeTable').dataTable({
		"aaSorting": [[ 1, "desc" ]]
		});
	/*var utilObject = new Object();
		utilObject['title'] = noteString;
		utilObject['ytitle'] = 'No of Voters';
		utilObject['tooltipText'] = ' Voters';
		
		//alert(xaxisOpt);
		// build linechart based on avarage 
	var newXaxis = buildHamletWiseCastResultsGraph( xaxisOpt,YDataObject);
	
	var newYaxis = buildColumnsForLineChart(newXaxis , YDataObject );
	
	var myChart  = buildMyLineChart(newXaxis , newYaxis , utilObject ,"ageGrid");*/
}

function buildAgeAndGenderWiseDetails(results , obj){
    var type = obj.type;
	
	var innerResults;
	var noteString;


	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal Wise voters Age and gender details of "+obj.name+" in "+publicationYear;

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		if(buildType == "hamlet")
		noteString = "Hamlet Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
		else
	   	noteString = "Booth Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;

		if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
		  noteString = "Ward Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
		else
 		 noteString = "Booth Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
     else if(type == "ward"){
		innerResults = results.boothVotersDetails;
	   	noteString = "Booth Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
		else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if( (type="hamlet" && obj.type == "hamletBooths" )|| obj.type == "wardBooths"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth Wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if( type="booth" && obj.type == "boothHamlets"){
		innerResults = results.boothVotersDetails;
		noteString = "Hamlet Wise  voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNote').html('<div align="center"><h3>'+noteString+'</h3></div>');

	var str='';

	str+='	<table  class="ageTable table table-bordered table-hover" id="mandalWiseAgeAndGenderTable">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	  	if(buildType == "hamlet")
		 str+='<th rowspan="2">HamletName</th>';
		 else
	  str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody"){

    if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
	   str+='<th rowspan="2">Ward</th>';
	else
		str+='<th rowspan="2">Booth</th>';

	}
	else if(type == "ward" || (type="hamlet" && obj.type == "hamletBooths") || obj.type == "wardBooths")
	   str+='<th rowspan="2">Booth No</th>';
	    else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   
	else if( type="booth" && obj.type == "boothHamlets"){
		 str+='<th rowspan="2">HamletName</th>';
	}
	   
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
for(var i=0;i<innerResults.length;i++){
  if(innerResults[i].totalMaleVotesFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
		if(buildType == "hamlet")
		str+='<td>'+innerResults[i].hamletName+'</td>';
		else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
	str+='<td>'+innerResults[i].boothName+'</td>';
	 else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	    else if((type="hamlet" && obj.type == "hamletBooths")||(type="booth" && obj.type == "boothHamlets") || obj.type == "wardBooths")
	   str+='<td>'+innerResults[i].hamletName+'</td>';
	   
	str+='<td>'+innerResults[i].totalMaleVotesFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor18To25+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor26To35+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor36To45+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor46To60+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersForAbove60+'</td>';

	str+='</tr>';
  }
}
   str+='</tbody>';
   str+='</table>';

$('#ageAndgenderWiseDetails').html(str);
$('#mandalWiseAgeAndGenderTable').dataTable({
		"aaSorting": [[ 1, "desc" ]]
		});
}

function buildAgeAndGenderWiseDetailsForPercent(results , obj){

    var type = obj.type;
	var innerResults;
	var noteString;
	$("#ajaxImageDiv").css('display','none');
	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal Wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat Wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
	   		if(buildType == "hamlet")
			noteString = "Hamlet Wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
			else
		noteString = "Booth Wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		 if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
		  noteString = "Ward Wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
		 else
		 noteString = "Booth Wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "ward"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth Wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea Wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if( (type="hamlet" && obj.type == "hamletBooths") || obj.type == "wardBooths"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth Wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if( type="booth" && obj.type == "boothHamlets"){
		innerResults = results.boothVotersDetails;
		noteString = "Hamlet Wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNoteInPercent').html('<div align="center"><h3>'+noteString+'</h3></div>');

	var str='';

	str+='	<table id="mandalWiseAgePercentageTable"         class="ageTable table table-bordered table-hover">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	if(buildType == "hamlet")
	 str+='<th rowspan="2">Hamlet Name</th>';
	else
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody"){

		 if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
	       str+='<th rowspan="2">Ward</th>';
		 else
			 str+='<th rowspan="2">Booth</th>';
	}
	else if(type == "ward" || ( type="hamlet" && obj.type == "hamletBooths") || obj.type == "wardBooths" )
	   str+='<th rowspan="2">Booth</th>';  
	       else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   else if(type="booth" && obj.type == "boothHamlets"){
		
		  str+='<th rowspan="2">Hamlet Name</th>';
	}
	   
	str+='<th colspan="3">18-25</th>';
	str+='<th colspan="3">26-35</th>';
	str+='<th colspan="3">36-45</th>';
	str+='<th colspan="3">46-60</th>';
	str+='<th colspan="3">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
for(var i=0;i<innerResults.length;i++){
 if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	if(buildType == "hamlet")
	str+='<td>'+innerResults[i].hamletName+'</td>';
	else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "ward")
	str+='<td>'+innerResults[i].boothName+'</td>';
	 else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	    else if((type="hamlet" && obj.type == "hamletBooths")||(type="booth" && obj.type == "boothHamlets") || obj.type == "wardBooths")
	   str+='<td>'+innerResults[i].hamletName+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor18To25+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor26To35+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor36To45+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor46To60+'</td>';

    str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentForAbove60+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentForAbove60+'</td>';

	str+='</tr>';
  }
}
   str+='</tbody>';
   str+='</table>';

$('#voterAgeAngGenderwiseDetailsInPercent').html(str);

$('#mandalWiseAgePercentageTable').dataTable({
		"aaSorting": [[ 1, "desc" ]] 
		});
if(obj.retrieveType == "all"){
	$('#tableDiv').css('display','block');
	$('#voterDetailsNote').css('display','block');      
}

if(type == "constituency" || type == "mandal")
{
$("#AgeWiseNoteDiv").css("display","block"); 
$("#AgeWiseNoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family Wise Voter Details Select Report Level Panchayat/Polling Station</font>');
}
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
//this is to set the slider value as 1 bydefault when ever we searched
//$( "#slider" ).slider({ value: 1 });
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
	  var percentage = $( "#slider" ).slider( "value" );
	  buildHamletWiseCastResultsGraph(selectedCastArray,percentage);
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
	  var percentage = $( "#slider1" ).slider( "value" );
	  buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,selectedCastArray,'');
	  buildCastGrid2(selectedCastArray,percentage)
	  
	  
}

function getBoothPArtiesAndElections()
{

var jsObj=
			{
				
				panchayatId:id,
				task:"getPanchayatElectionsAndParties"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getBoothWiseElectionResultsAction.action?"+rparam;	
   
		callAjax(jsObj,url);
}
function getConstituencyEleAndParties()
{
	var jsObj= {
		constituencyId : constituencyId,
			delimitationType:"present",
			task:"getConstiEleAndParties"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituencyElectionYearsAction.action?"+rparam;	
   
	callAjax(jsObj,url);
}



function getResultsForBooths()
{
 var parties = '';
  var elections = '';
  var str = '';
    $("#mandalElecResultsErrMsg").html("");
    $('.partySelForPanc').each(function(){
	  if($(this).is(':checked'))
	    parties+=','+$(this).val();
    });
	$('.elecSelForPanc').each(function(){
	  if($(this).is(':checked'))
	    elections+=','+$(this).val();
    });
	var btnName=$('input:radio[name=boothvotes]:checked').val();
	 var invalid = false;
	 if(parties.length == 0)
	 {
	   invalid = true;
	   str+="<div>Please check atleast one party</div>";
	 }
	 if(elections.length == 0)
	 {
	   invalid = true;
	   str+="<div>Please check atleast one election</div>";
	 }
	 if(invalid){
	   $("#mandalElecResultsErrMsg").html(str);
	   return;
	 }
	 $("#container").html('<img alt="Processing Image" src="./images/icons/goldAjaxLoad.gif"/>');
var jsObj=
			{
				
				tehsilId:id,
				parties:parties.substr(1),
				elections:elections.substr(1),
				btnName:btnName,	includeAlliance:$("#includeAlliancesDiv").is(':checked'),
				//btnName:btnName,
				task:"getResultsForBooth"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getBoothWiseElectionResultsAction.action?tehsilId="+id+"&"+rparam;	
   
		callAjax(jsObj,url);
  }
function getResultsForConstituency()
{
	
var parties = '';
  var elections = '';
  var census = '';
$('.partySelForPanc').each(function(){
	  if($(this).is(':checked'))
	    parties+=','+$(this).val();
    });
	$('.elecSelForPanc').each(function(){
	  if($(this).is(':checked'))
	    elections+=','+$(this).val();
    });
	
	var btnName=$('input:radio[name=constituencyvotes]:checked').val();
var jsObj=
			{
				
				constituencyId:constituencyId,
				parties:parties.substr(1),
				elections:elections.substr(1),
                resultType:$('input:radio[name=election]:checked').val(),
				includeAlliance:$("#includeAlliancesDiv").is(':checked'),
				btnName:btnName,
				task:"getResultsForConstituency"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getConstituencyElectionYearsAction.action?"+rparam;	
   
		callAjax(jsObj,url);
}
  function getothersSelectDiv()
  {
  if(type == "mandal")
  {
	 var jsObj = {
		 constituencyId : constituencyId,
		
		 task : "getMandals"
	 };
}
if(type == "panchayat")
{

var jsObj = {
		 mandalId : '${mandalId}',
		
		 task : "getPanchayatsByMandalId"
	 };
}
	 
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMandalsAction.action?"+rparam;	
   callAjax(jsObj,url);
  }
  function buildMandals(result)
  {
	  if(result != null)
	$("#othersmandalDiv").css("display","block");
	var selectedElmt=document.getElementById("mandals");
		removeSelectElements(selectedElmt);
		var opElmt = document.createElement('option');
			opElmt.value=0;
			opElmt.text="Select Location";

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}
		for(var val in result)
		{
		
			var opElmt = document.createElement('option');
			opElmt.value=result[val].id;
			opElmt.text=result[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}
		}
		 // $("option[value="+id+"]").attr('selected', 'selected');
  }
  function buildPanchayats(result)
  {
	  if(result != null)
	$("#othersPanchayatDiv").css("display","block");
	var selectedElmt=document.getElementById("panchayats");
		removeSelectElements(selectedElmt);
		var opElmt = document.createElement('option');
			opElmt.value=0;
			opElmt.text="Select Location";

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}
		for(var val in result)
		{
		
			var opElmt = document.createElement('option');
			opElmt.value=result[val].id;
			opElmt.text=result[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}
		}
  }

  function removeSelectElements(selectedElmt)
  {
	  var len = selectedElmt.length;
	  for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
  }

  /* var allZPTCMPTCElecInfo = new Array();
<c:forEach var="zptcMptcElection" items="${mptcZptcElectionResultsVO}" >

		var zptcMptcElec = {
				year:'${zptcMptcElection.electionYear}',
				type:'${zptcMptcElection.electionType}',
				parties:[]		
		};	
		<c:forEach var="party" items="${zptcMptcElection.partyResultsVO}">
			var party = {
					partyName:'${party.partyName}',
					participated:'${party.seatsParticipated}',
					seatsWon:'${party.totalSeatsWon}',
					votesEarned:'${party.votesEarned}',
					polledVotes:'${party.totalPolledVotes}',
					percentage:'${party.percentage}',
					constituencies:[]
			};
			<c:forEach var="constituency" items="${party.constituencyWisePatiesInfoVOs}">
				var constituency = {
						name:'${constituency.constituencyName}',
						candidate:'${constituency.candidateName}',
						rank:'${constituency.rank}',
						votesEarned:'${constituency.votesEarned}',
						percentage:'${constituency.percentage}',
						results:'<a href="javascript:{}" class="anchorStyle" title="Click to view more results"  onclick=getMoreResults('+zptcMptcElec.year+',\''+zptcMptcElec.type+'\','+${constituency.constituencyId}+')> More Results </a>'
				};
				party.constituencies.push(constituency); 
			</c:forEach>	
			zptcMptcElec.parties.push(party);	
		</c:forEach>
		allZPTCMPTCElecInfo.push(zptcMptcElec);
	</c:forEach>*/
	
	//These Global Variables are used for Building chart for Local Body Elections
  var categories1 = [];//This globla variable is for making X-Axis elements
  var partiesInLocalElec=[];
  var partyObj;
  var partyWiseResArray=[];
/*function getLocalEleArraysForChart(allZPTCMPTCElecInfo){
	var partyName;
	var partyPercArry;
	for(var i in allZPTCMPTCElecInfo){
	   categories1.push(allZPTCMPTCElecInfo[i].type+" "+allZPTCMPTCElecInfo[i].year);

	   for(var j in allZPTCMPTCElecInfo[i].parties){
		if($.inArray(allZPTCMPTCElecInfo[i].parties[j].partyName,partiesInLocalElec) == -1)
			partiesInLocalElec.push(allZPTCMPTCElecInfo[i].parties[j].partyName);
		}
	}
	
	partiesInLocalElec.sort();
	
	for(var k in partiesInLocalElec){
		partyPercArry=[];
		partyObj={};
		
	for(var i in allZPTCMPTCElecInfo){
		var prtyParticipated=false;
	   partyName=partiesInLocalElec[k];
	   partyObj['color']=getColorCodeForParty(partyName);
	   partyObj['name']=partyName;
			for(var j in allZPTCMPTCElecInfo[i].parties){
				if(allZPTCMPTCElecInfo[i].parties[j].partyName == partyName){
					partyPercArry.push(parseFloat(allZPTCMPTCElecInfo[i].parties[j].percentage));
					prtyParticipated=true;
				}
			}
			if(!prtyParticipated){
				partyPercArry.push(null);
			}
		}
		partyObj['data']=partyPercArry;
		partyWiseResArray.push(partyObj);
	}
	
	
	return partyWiseResArray;
}*/
/*function showMPTCZPTCResults()
{
	var dataResultlocalele=getLocalEleArraysForChart(allZPTCMPTCElecInfo);
	var title='${typeName}';
	
	var str='';

	  str+='';

	  str+='<h4>MPTC & ZPTC Results of '+ '${typeName}'+'</h4>';

	   str+='<table border="1" class="gridtable" id="mptcZptcTable">';

		 str+='<thead>';
		    str += '<th>Election</th>';
			str += '<th>Party</th>';
			str += '<th>Participated</th>';
			str += '<th>Seats Won</th>';
			str += '<th>Votes Earned</th>';
			str += '<th>Votes Polled</th>';
			str += '<th>Percentage</th>';
		  str += '</thead>';
		  
     for(var i in allZPTCMPTCElecInfo){
	  
	   for(var j in allZPTCMPTCElecInfo[i].parties){
		
		str+='<tr>';
		    str +='<td>'+ allZPTCMPTCElecInfo[i].type +" "+allZPTCMPTCElecInfo[i].year+'</td>';
			str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].partyName+'</td>';
			str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].participated+'</td>';
			str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].seatsWon+'</td>';
			str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].votesEarned+'</td>';
			str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].polledVotes+'</td>';
			str += '<td>'+allZPTCMPTCElecInfo[i].parties[j].percentage+'</td>';
		  str += '</tr>';

	   }
  }
    str+='</table>';
	str+='<div id="localEleRsltsChart"></div>';
	
	 $('#electionResultsDiv').html(str);
	 $('#mptcZptcTable').dataTable();

	buildChart(dataResultlocalele,title);
}
	function buildChart(result,title){
		
	 $('#localEleRsltsChart').highcharts({
            chart: {
                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: 'MPTC & ZPTC Election Trends in '+title+'',
                x: -20 //center
            },
            
            xAxis: {
                categories: categories1
            },
            yAxis: {
				min: 0,
                title: {
                    text: 'Votes Percentages'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '%'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: result
        });
	}
	*/
	function rebuiltDataTable(invisib){
		var myResults_slctd=tempObj.result;
		var jsObj_slctd=tempObj.jsobj;
		
		if(selectedCastArray.length != 0)
		buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,selectedCastArray,invisib);
		
		else
		buildCastInfoForSubLevels(myResults_slctd,jsObj_slctd,null,invisib);
	}
	
	

	function selectAllCheckBoxes(value){
		//var elmts = document.getElementById('selectAll');
		//var deSelect = document.getElementById('deSelectAll');
		//var elections = document.getElementById('selectAllEle');
		//var deSelectEle = document.getElementById('deSelectAllEle');
			var elmtValue = value;
			/*if(elmts.checked == true){
				deSelect.checked = false;*/
			//For Parties SelectAll
			if(elmtValue == 'Select All Parties'){
			$('.partySelForPanc').each(function(){
			 if(!$(this).is(':checked'))
			$(this).attr("checked", "checked");
			});
			}
			//For Elections SelectAll
			/*if(elections.checked == true){
			deSelectEle.checked = false;*/
			if(elmtValue == 'Select All Elections'){
			$('.elecSelForPanc').each(function(){
			if(!$(this).is(':checked'))
		 $(this).attr("checked", "checked");
		 });
		}		
	
	}


	function deSelectAllCheckBoxes(value){
		
		//var elmts = document.getElementById('deSelectAll');
		//var elmt = document.getElementById('selectAll');
		//var elections = document.getElementById('selectAllEle');
		//var deSelectEle = document.getElementById('deSelectAllEle');
		var elmtValue = value;
			//For Parties UnSelectAll
			/*if(elmts.checked == true){
					elmt.checked = false;*/
			if(elmtValue == 'Unselect All Parties'){
			$('.partySelForPanc').each(function(){
			 if($(this).is(':checked'))
			 $(this).removeAttr('checked')
			});
			}
			
			//For Elections UnSelectAll
			/*if(deSelectEle.checked == true){
				elections.checked = false;*/
				if(elmtValue == 'Unselect All Elections'){
			$('.elecSelForPanc').each(function(){
			if($(this).is(':checked'))
			$(this).removeAttr('checked')
			});
			}
	}

function callAjaxToShowTehsilDetails()
{

  var jsObj=
			{				
				constituencyId:id,
				task:"getTehsilDetailsForAllDelimitations"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getConstituencyMandalDetailsForAllDelimitations.action?"+rparam;	
   
		callAjax(jsObj,url);

}

function callAjaxToCheckDelimitationYears()
{

var jsObj=
			{				
				constituencyId:id,
				task:"checkDelimitationYearsAndMandalsForConstituency"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "checkDelimitationYearsAndMandalsForConstituency.action?"+rparam;	
   
		callAjax(jsObj,url);

}

function showAllTehsilDetailsForAllDelimitations(results)
{

   var str='';

   for(var i in results){
    str+='<div style="float:left;margin:8px;">';
	  str+='<u><h6>'+results[i].year+'</h6></u>';
	   for(var j in results[i].names)
		    str+='<span>'+results[i].names[j]+'</span><br>';
	str+='</div>';
   }

   $('#dialogDiv').html(str);
   $('#dialogDiv').dialog({
	   title:'Mandal Details For All Delimitations',
	   height:'auto',
       width:'auto',
       show:'blind',
  	   hide:'blind',
	  buttons: { "Ok": function() { $(this).dialog("close"); } } 

   });

}

	
</script>


<script type="text/javascript">
 var casteChartHeading = '';


getvotersBasicInfo("voters",id,publicationId,type);
if('${type}' == "mandal" || '${type}' == "panchayat" )
getothersSelectDiv();
getCensusInfoForSubLevels();
  getResultsForConstituency();
getLatestCastsSubcategoryWise();
getAgewiseVoterDetails();
if('${type}' == "mandal")
showMPTCZPTCResults();

if('${type}' != "constituency" || isGhmc == "true"){
  $('#delimitationOptionsDiv').hide();
}else
callAjaxToCheckDelimitationYears();

</script>
</body>
</html>