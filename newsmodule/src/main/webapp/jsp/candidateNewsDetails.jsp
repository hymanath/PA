<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> TDP News Portal </title>

<!--<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

 YUI Dependency files (Start) -->
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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries.js"></script>
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
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script type="text/javascript" src="js/bootstrap.js"></script>

<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/>

<style type="text/css">
.table td {
   text-align: center;   
}
/* #respondNotRespondNewsCountDiv table*/
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
 .enadu
{
font-family: eFont;
font-size:20px;
}

@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
#candidateNewsCountDiv table,#candidateCritiesNewsDiv table{
border:1px solid #d3d3d3;
border-collapse:collapse;
padding:10px;
margin-left:auto;
margin-right:auto;width:100%;
} 
#respondNotRespondNewsCountDiv table
{
border:1px solid #d3d3d3;
border-collapse:collapse;


}
#candidateNewsCountDiv table tr:nth-child(even),#respondNotRespondNewsCountDiv table tr:nth-child(even),#candidateCritiesNewsDiv table tr:nth-child(even){background:#EdF5FF;}
#candidateNewsCountDiv table td,#respondNotRespondNewsCountDiv table td,#candidateCritiesNewsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#candidateNewsCountDiv table th,#candidateCritiesNewsDiv table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: center;
	color:#333333;
	}
	#respondNotRespondNewsCountDiv table th{
	background-color: #CDE6FC;
    font-size: 13px;
	  text-align: center;
	
	}
#candidateNewsMainDiv{margin-left: auto; margin-right: auto; float: none; width: 950px; padding-top: 20px; padding-bottom: 20px;}


#allRadio{margin-top: 0px; margin-right: 4px;}
#byDateRadio,#CriticsbyDateRadio{margin-top: 0px; margin-left: 15px; margin-right: 4px;}
#existingFromText,#existingToText,#criticsExistingFromText,#criticsExistingToText{width: 130px;}
#radioBtnDiv{margin-bottom: 20px;}
#selectNewsInnerDiv{margin-left: 149px;}
#showAndHideDateSelectDiv,#showAndHideDateCriticsDiv{margin-left: 17px;}
#newsDetailsBtn{margin-top: -7px; margin-left: 16px;}
#candidateNewsGraphDiv{clear:both;}
#selectRadioBtnTable td{vertical-align: top;}
#galleryRadioDiv,#categoryRadioDiv,#districtCheckboxDiv{margin-left: 10px;}
#galleryCheckBoxId,#categoryCheckBoxId,#districtCheckboxId{margin-top: 0px;margin-right: 4px;}
#headingSpan{background:#49AFCD;color:#FFF;}
#candidateNewsMainDiv h4{text-align: center; margin-bottom: 20px;}
#headingSpan{padding: 5px 68px; border-radius: 5px;}
#categoryGallary{margin-left: 12px; margin-right: 4px; margin-top: 0px;}
#errorMsgDiv,#errorMsgDivForCritics{text-align: center; font-size: 13px; margin-bottom: 10px;}
#districtList,#categoryList,#galleryList{margin-left: 12px;width:200px;}
#respondNotRespondNewsCountDiv{margin-top: 11px; margin-bottom: 25px;margin-left:100px;width:800px;}
#respondNotRespondNewsCountDiv table{margin-top: 10px;}
.newsCount{cursor:pointer;}

#candidateNewsCountDiv table td, #respondNotRespondNewsCountDiv table td{color:#005580;}
.filter2{display:none;}
#candidateNewsCountDiv{width:800px;margin-left:75px;}
.nav > li > a{font-weight:bold;}

.blue:before {
    background: none repeat scroll 0 0 #548BD4;
    content: " ";
    height: 5px;
    left: 0;
    position: absolute;
    top: -5px;
    width: 45px;
}
.blue {
    color: #2A4F97;
}
.widget {
    background: none repeat scroll 0 0 #FAFAFA;
    border-top: 5px solid #000000;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
    margin: 10px 0 20px;
    position: relative;
}
.widget h4, h2 {
    border-bottom: 1px solid #C0C0C0;
    color: #000000;
    font-size: 14px;
    font-weight: bold;
    line-height: 20px;
    padding-left: 10px;
    text-rendering: optimizelegibility;
    text-transform: uppercase;
}
.widget h4, h2 {
    font-family: arial;
}
.criticsNewsCls{float: right; margin-top: -54px;}
#latestNewsDiv{display:table;margin-top: -58px;}
#paginationId{margin-left: 21px; margin-top: 30px; margin-bottom: 20px;}
.unstyled.pad10 > li {
    display: table;
}
#errorMsgDivForCritics{color:red;}
#showAndHideDateCriticsDiv{ margin-bottom: 12px;margin-top: 20px;}
#radioBtnDivForCritics{margin-top: 10px;}
#showAndHideDateCriticsDiv{ margin-top: 2px;}
#criticsBtnDiv{margin-left: 43px; margin-top: 8px;}
#selectNewsInnerDiv{margin-top: 25px;}
#hideCriticsId,#showCriticsId{ margin-right: 15px;margin-top: -56px;}
#partyWiseCriticsNewsMainDiv h4{margin-top: 10px; margin-bottom: 20px; padding-bottom: 11px;}
#candidateCritiesNewsDiv h4,.headingDivCls h4{background:  #06ABEA; color: #FFF; border-radius: 5px 5px 5px 5px; margin-left: auto; margin-right: auto; float: none; width: 401px; padding: 5px 21px;}

#candidateCritiesNewsTab_wrapper{ padding: 28px 17px;border:1px solid #d3d3d3;border-radius:2px;}
#candidateCritiesNewsDiv{margin-top: 35px;}
#partyWiseCriticsNewsMainDiv{ margin-left: auto;
    margin-right: auto;
    width: 950px;}
#getCandidatesNewsId,#criticsId{cursor:pointer;}
</style>
<script type="text/javascript">
var fromDate = '${fromDate}';
var toDate = '${toDate}';

var requestFor = '${requestFor}';


</script>

</head>

<body>

<div id="candidateNewsMainDiv">
	<div style="width:800px;margin-left:auto;margin-right:auto;text-align:center;">
		<!--<span id="getCandidatesNewsIds" class="btn btn-success">
		Get Candidate News Details</span>
		<span id="criticsIds" class="btn btn-success">Get Critics Information</span>-->
		
		<ul id="myTab" class="nav nav-tabs">
			<li><a data-toggle="tab" id="getCandidatesNewsId"> Candidate News Report</a></li>
			<li><a data-toggle="tab" id="criticsId">Opponent Party Critics Report</a></li>
		</ul>
	</div>
	
	
 
   <div id="errorMsgDiv"></div>
   <div id="errorMsgDivForCritics"></div>
    <div id="selectNewsDiv">
     <div id="selectNewsInnerDiv" style="display:none;">
	  <table id="selectRadioBtnTable">
	   <tr>
		  <td>
			<div id="radioBtnDiv">
				<input type="radio" value="all" name="candidateNewsRadio" class="candidateNewsRadioCls" id="allRadio" checked="true"/><b>All</b>
				<input type="radio" value="byDate" name="candidateNewsRadio" class="candidateNewsRadioCls" id="byDateRadio"/><b>By Date</b>
			</div>

			<div id="radioBtnDivForCritics" style="display:none;">
				<input type="radio" value="all" name="candidateCriticsNewsRadio" class="candidateCriticsNewsRadioCls" id="allRadio" checked="true"/><b>All</b>
				<input type="radio" value="byDate" name="candidateCriticsNewsRadio" class="candidateCriticsNewsRadioCls" id="CriticsbyDateRadio"/><b>By Date</b>
			</div>

	      </td>
		  
		  <td><div id="showAndHideDateCriticsDiv" style="display:none;">
			 <label style="float:left;"><b>From Date: </b><input type="text" id="criticsExistingFromText" class="dateField" readonly="true" name="fileDate" size="20"></label>
			 <label style="float: left; margin-left: 11px;"><b>To Date: </b><input type="text" id="criticsExistingToText" class="dateField" readonly="true" name="fileDate" size="20"></label>
			</div></td>
			<td><div style="text-align:center;" style='display:none;' id="criticsBtnDiv"><input type="button" value="submit"  id="criticsNewsDetailsBtn" class="btn btn-info"/> <img src="images/search.jpg" id="ajaxImg" style="display:none;"/></div></td>

		  <td>
		   <div id="galleryRadioDiv" class="filter2"><input type="checkbox" value="byGallery" class="galleryCheckBoxCls" id="galleryCheckBoxId"/><b>By Gallery</b></div>
		  </td>
		  <td>
		   <div id="categoryRadioDiv" class="filter2"><input type="checkbox" value="byCategory" id="categoryCheckBoxId"/><b>By Category</b></div>
		  </td>
		  <td>
		    <div id="districtCheckboxDiv" class="filter2"><input type="checkbox" value="byDistrict" id="districtCheckboxId"><b>By District</b></div>
		  </td>
		 </tr> 
		</table>
		<table>
		 <tr>
	      <td style="vertical-align: top;">
			<div id="showAndHideDateSelectDiv" style="display:none;">
			 <label style="float:left;"><b>From Date: </b><input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="20"></label>
			 <label style="float: left; margin-left: 11px;"><b>To Date: </b><input type="text" id="existingToText" class="dateField" readonly="true" name="fileDate" size="20"></label>
			</div>

			<!-- <div id="showAndHideDateCriticsDiv" style="display:none;">
			 <label style="float:left;"><b>From Date: </b><input type="text" id="criticsExistingFromText" class="dateField" readonly="true" name="fileDate" size="20"></label>
			 <label style="float: left; margin-left: 11px;"><b>To Date: </b><input type="text" id="criticsExistingToText" class="dateField" readonly="true" name="fileDate" size="20"></label>
			</div> -->

	      </td>
		 </tr>
		 <tr>
		 <td>
		  <div id="districtShowAndHideDiv" style="display:none;"><b>Select District:</b><select id="districtList" multiple="multiple"></select></div>
		  </td>
		</tr>

		 <tr>
		  <td id="categoryShowAndHideTb" style="display:none;">
		    <div id="categoryShowAndHideDiv"><b>Select Category: </b><select id="categoryList" multiple="multiple"></select><input type="checkbox" id="categoryGallary"><b>Gallery</b></div>
		  </td>
		 
		  <td id="galleryListShowHideTb" style="display:none;">
		   <div id="galleryListShowHideDiv"><b>Select Gallery:</b> <select id="galleryList" multiple="multiple"></select></div>
		  </td>
		</tr>
		
	  </table>
			
	 <div style="text-align:center;"><input type="button" value="submit" id="newsDetailsBtn" class="btn btn-info"/> <img src="images/search.jpg" id="ajaxImg" style="display:none;"/></div>

  </div>
  <hr>
	<div id="respondNotRespondNewsCountDiv" style="display:none;"></div>
    
	<div>
	 <div id="partyWiseCriticsNewsMainDiv" style="display:none;" class="widget blue">
	  <h4>Total Critics </h4>
	  <span id="hideAndShowCriticsSpan"><a class="btn btn-info criticsNewsCls" href="javascript:{}" id="hideCriticsId">Hide</a></span>
      <div id="hideAndShowLatestNewsDiv">
	   <div id="latestNewsDiv"></div>
	   <div id="paginationId"></div>
	  </div>
	 </div>
	</div>

	<div id="candidateNewsCountDiv" style="display:none;"></div>
    <div id="candidateCritiesNewsDiv" style="display:none;"></div>

</div>
	<div id="graphOuterId" style="display:none;">
		<div id="candidateNewsGraphDiv"></div>
	</div>
</div>


<script type="text/javascript">

$('#myTab a').click(function(e) {
    e.preventDefault();
    $(this).tab('show');
});
$(function(){
$('#myTab a:first').tab('show');
$('#myTab a:first').trigger('click',function(){
	hideWhenCandiNewsClcked();
});
});




$('#getCandidatesNewsId').click(function(){
	hideWhenCandiNewsClcked();
	$("#newsDetailsBtn").css("display","inline-block");
	$("#criticsBtnDiv").css("display","none");
});
function hideWhenCandiNewsClcked(){
	$('#candidateNewsCountDiv').css('display','block');
	$('#graphOuterId').css('display','block');
	$('#selectNewsInnerDiv').css('display','block');
	$('#respondNotRespondNewsCountDiv').css('display','none');
	$('.filter2').css('display','block');
	$("#partyWiseCriticsNewsMainDiv").css('display','none');

	if($("#galleryCheckBoxId").is(":checked"))
	 $("#galleryListShowHideTb").css('display','inline-block');

	if($("#categoryCheckBoxId").is(":checked"))
	 $("#categoryShowAndHideTb").css('display','inline-block');

	if($("#categoryGallary").is(":checked"))
     $("#galleryListShowHideTb").css('display','inline-block');

	if($("#districtCheckboxId").is(":checked"))
     $("#districtShowAndHideDiv").css('display','inline-block');

	$("#candidateCritiesNewsDiv").css('display','none');
	$("#partyWiseCriticsNewsMainDiv").css('display','none');

	$("#radioBtnDiv").css('display','inline-block');
	$("#radioBtnDivForCritics").css('display','none');

	
	$("#showAndHideDateCriticsDiv").css('display','none');
    
    
	var radio = $("input:radio[name=candidateNewsRadio]:checked").val();
	if(radio == "byDate")
	 $("#showAndHideDateSelectDiv").css('display','inline-block');
	else
	 $("#showAndHideDateSelectDiv").css('display','none');
	$("#errorMsgDiv").html('');
	$("#errorMsgDivForCritics").html('');

}

$('#criticsId').click(function(){

	$("#newsDetailsBtn").css("display","none");
	$("#criticsBtnDiv").css("display","inline-block");

	$('#candidateNewsCountDiv').css('display','none');
	$('#graphOuterId').css('display','none');
	$('#selectNewsInnerDiv').css('display','block');
	$('#respondNotRespondNewsCountDiv').css('display','block');
	$('.filter2').css('display','none');
	getPartyWiseCriticsNews(0);
	getCandidateCritiesNewsDetails();

	$("#galleryListShowHideTb").css('display','none');
	$("#categoryShowAndHideTb").css('display','none');
	$("#galleryListShowHideTb").css('display','none');
	$("#districtShowAndHideDiv").css('display','none');

    $("#radioBtnDiv").css('display','none');
	$("#radioBtnDivForCritics").css('display','block');

	
	$("#showAndHideDateSelectDiv").css('display','none');
	
    var radio = $("input:radio[name=candidateCriticsNewsRadio]:checked").val();
	if(radio == "byDate")
	 $("#showAndHideDateCriticsDiv").css('display','inline-block');
	else
	 $("#showAndHideDateCriticsDiv").css('display','none');
	

    $("#errorMsgDiv").html('');
	$("#errorMsgDivForCritics").html('');

});

function getCandidateNewsCount()
{
   $("#errorMsgDiv").html('');
   var radioVal = $("input:radio[name=candidateNewsRadio]:checked").val();
   var fromDate = '';
   var toDate = '';
   var galleryIdsArray = new Array();
   var categoryIdsArray = new Array();
   var locationScope = "";
   var locationValue = 0;
   var locationIdsList = new Array();
   var tempVar = "";

   if(radioVal == "byDate")
   {
	 $("#showAndHideDateSelectDiv").css("display","inline-block");
	 fromDate = $("#existingFromText").val();
     toDate = $("#existingToText").val();
	 if(fromDate == '' && toDate =='')
	 {
	  $("#errorMsgDiv").html('Please Select From And To Dates.');
	  return;
	 }
	 if(fromDate == '')
	 {
	  $("#errorMsgDiv").html('Please Select From Date.');
	  return;
	 }
	 if(toDate =='')
	 {
	  $("#errorMsgDiv").html('Please Select To Date.');
	  return;
	 }
	 tempVar = "byDate";

   }
   else
   {
    $("#showAndHideDateSelectDiv").css("display","none");
    tempVar = "all";
	
   }

    if($("#categoryCheckBoxId").is(":checked"))
	{
	  $('#categoryList > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
       });
	   if(categoryIdsArray.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select Category.');
	     return; 
	   }
	   tempVar = "";
	   
	}

    if($("#galleryCheckBoxId").is(":checked") || $("#categoryGallary").is(":checked"))
	{
	  
       $('#galleryList > option:selected').each(
       function(i){
         galleryIdsArray.push($(this).val());
       });
	   if(galleryIdsArray.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select Gallery.');
	     return; 
	   }
	   tempVar = "";
	   
	}

	if($("#districtCheckboxId").is(":checked"))
	{
      $('#districtList > option:selected').each(
       function(i){
         locationIdsList.push($(this).val());
       });
	   if(locationIdsList.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select District.');
	     return; 
	   }

	   locationScope = "DISTRICT";
	}
   $("#ajaxImg").css("display","inline-block");

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		galleryIdsArray:galleryIdsArray,
        categoryIdsArray:categoryIdsArray,
        locationScope:locationScope,
        tempVar:tempVar,
		locationValuesList:locationIdsList,
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
			else if(jsObj.task == "getGalleryListForAParty" || jsObj.task == "getGalleryListForSelectedCategory")
			{
			  clearOptionsListForSelectElmtId('galleryList');
			  createOptionsForSelectElmtId('galleryList',myResults);
			}
			else if(jsObj.task == "getCategoryList")
			{
			  clearOptionsListForSelectElmtId('categoryList');
			  createOptionsForSelectElmtId('categoryList',myResults);
			}
			else if(jsObj.task == "getLocationsListByScopeId")
			{
			  clearOptionsListForSelectElmtId('districtList');
			  createOptionsForSelectElmtId('districtList',myResults);
			} //18111
			else if(jsObj.task == "getRespondedAndNotRespondedNewsCount")
			{ 
			    $("#ajaxImg").css("display","none");
			      showPartyWiseNewsCount(myResults);
			}

			else if(jsObj.task == "getCandidateCritiesNewsDetails")
			{
			  buildCriticsCandidates(myResults);
			}
			else if(jsObj.task == "getNewsDetails")
			{
				buildPaginatedNews(myResults,jsObj);
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

  $("#ajaxImg").css("display","none");
  $("#candidateNewsCountDiv").html('');
  $("#candidateNewsGraphDiv").html('');

  if(results == null || results.length == 0)
  {
	$("#candidateNewsCountDiv").html('No Data Found');
	return;
  }

  var str = '';
  str +='<table id="newsCountTab">';
  str +='<thead>';
  str +='<tr>';
 
  str +='<th>Candidate Name</th>';
  if(jsObj.locationScope != "DISTRICT")
   str +='<th>State</th>';
  str +='<th>District</th>';

  if(jsObj.locationScope != "DISTRICT")
  {
    str +='<th>Constituency</th>';
    str +='<th>Mandal</th>';
  }
  /* str +='<th>village</th>';
  str +='<th>Muncipality</th>';
  str +='<th>Ward</th>';
  str +='<th>Booth</th>';*/
  
  str +='</tr>';
  str +='</thead>';
  str+='<tbody>';
  for(var i in results)
  {
   str +='<tr>';
   
   str +='<td>'+results[i].name+'</td>';

  if(jsObj.locationScope != "DISTRICT")
  {
   
   if(results[i].stateNewsCount > 0)
    str +='<td class="newsCount" onclick="getLocationWiseNewsDetails(\''+results[i].id+'\',\'STATE\')">'+results[i].stateNewsCount+'</td>';
	else
	 str +='<td>'+results[i].stateNewsCount+'</td>';
  }

   if(results[i].districtNewsCount > 0)
   str +='<td class="newsCount" onclick="getLocationWiseNewsDetails(\''+results[i].id+'\',\'DISTRICT\')">'+results[i].districtNewsCount+'</td>';
   else
	str +='<td>'+results[i].districtNewsCount+'</td>';

  if(jsObj.locationScope != "DISTRICT")
  {
   if(results[i].constituencyNewsCount > 0)
    str +='<td  class="newsCount" onclick="getLocationWiseNewsDetails(\''+results[i].id+'\',\'CONSTITUENCY\')">'+results[i].constituencyNewsCount+'</td>';
   else
	str +='<td>'+results[i].constituencyNewsCount+'</td>';

   if(results[i].mandalNewsCount >0)
    str +='<td class="newsCount" onclick="getLocationWiseNewsDetails(\''+results[i].id+'\',\'MANDAL\')">'+results[i].mandalNewsCount+'</a></td>';
   else
	str +='<td>'+results[i].mandalNewsCount+'</td>';
  }
   /* str +='<td>'+results[i].villageNewsCount+'</td>';
   str +='<td>'+results[i].localEleBodyNewsCount+'</td>';
   str +='<td>'+results[i].wardNewsCount+'</td>';
   str +='<td>'+results[i].boothNewsCount+'</td>';*/
   
   str +='</tr>';
  }
  str+='</tbody>';
 str +='</table>';
 $("#candidateNewsCountDiv").html(str);

 if(jsObj.locationScope != "DISTRICT")
 {
 
   $('#newsCountTab').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null] 
		
		});
 }
 else
 {
	$('#newsCountTab').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null] 
		});
 }

buildChart(results,jsObj);
}

function getLocationWiseNewsDetails(candidateId,locationScope)
{
  
  var fromDate = "";
  var toDate = "";
  var categoryIds = "";
  var galleryIds = "";

  var radioVal = $("input:radio[name=candidateNewsRadio]:checked").val();
  if(radioVal == "byDate")
  {
	fromDate = $("#existingFromText").val();
    toDate = $("#existingToText").val();
  }
  if($("#categoryCheckBoxId").is(":checked"))
	categoryIds = $("#categoryList").val();
  
  if($("#galleryCheckBoxId").is(":checked") || $("#categoryGallary").is(":checked"))
  {
    galleryIds = $("#galleryList").val(); 
  }
  
  var urlstr = "locationWiseNewsDetailsAction.action?candidateId="+candidateId+"&locationScope="+locationScope+"&fromDate="+fromDate+"&toDate="+toDate+"&categoryIds="+categoryIds+"&galleryIds="+galleryIds+"&";
		
     var browser1 = window.open(urlstr,"locationWiseCandidateNewsDetails"+locationScope+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();

}




function getSelectedNewsDetails()
{
  $("#existingFromText").val('');
  $("#existingToText").val('');
  if(fromDate != '' && toDate !='')
  {
	 $("#byDateRadio").attr("checked",true);
	 $("#showAndHideDateSelectDiv").css("display","inline-block");
	 $("#existingFromText").val(fromDate);
	 $("#existingToText").val(toDate);
  }
  else
   $("#allRadio").attr("checked",true);
 
}
function buildChart(results,jsObj)
{
	$("#candidateNewsGraphDiv").html('');
	if(results == null || results.size == 0)
	 return;

	var dataarr = [];
	var xaxis = results[0].candidateNames;

	if(results[0].stateCounts != null && jsObj.locationScope != "DISTRICT")
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
	
	/* if(results[0].constituencyCounts != null && jsObj.locationScope != "DISTRICT")
	{
		var obj3 = {
		name: 'Constituency',
        data: results[0].constituencyCounts
		}
		dataarr.push(obj3);
	}
	if(results[0].mandalCounts != null && jsObj.locationScope != "DISTRICT")
	{
		var obj4 = {
		name: 'Mandal',
        data: results[0].mandalCounts
		}
		dataarr.push(obj4);
	}*/
	dataarr.push();
	
        $('#candidateNewsGraphDiv').highcharts({
            chart: {
                type: 'column',
				height:700
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
				fontSize: '14px',
				fontFamily: 'arial, sans-serif',
				color:'#202B35'
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


//gallery List

function getGalleriesList()
{
  var fromDate = $("#existingFromText").val();
  var toDate = $("#existingToText").val();
  var locationIdsArray = new Array();
  var locationScope = "";
  $("#errorMsgDiv").html('');
  if($("#districtCheckboxId").is(":checked"))
  {
	locationScope = "DISTRICT";
    $('#districtList > option:selected').each(
       function(i){
         locationIdsArray.push($(this).val());
     });

    if(locationIdsArray.length == 0)
    {
      $("#errorMsgDiv").html('Please Select District.');
      return; 
	   
     }
  }

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		locationIdsArray:locationIdsArray,
        locationScope:locationScope,
		task:'getGalleryListForAParty'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGalleryListForAPartyAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getCategoryList()
{
 var fromDate = $("#existingFromText").val();
 var toDate = $("#existingToText").val();
 var locationScope = "";
 var locationIdsArray = new Array();

 if($("#districtCheckboxId").is(":checked"))
  {
	locationScope = "DISTRICT";
    $('#districtList > option:selected').each(
       function(i){
         locationIdsArray.push($(this).val());
     });

    if(locationIdsArray.length == 0)
    {
      $("#errorMsgDiv").html('Please Select District.');
      return; 
	   
     }
  }

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		locationScope:locationScope,
        locationIdsArray:locationIdsArray,
		task:'getCategoryList'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCategoryListAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getGalleriesForASelectedCategory()
{
 $("#errorMsgDiv").html('');
 var categoryIdsArray = new Array();
 $('#categoryList > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
     });

 if(categoryIdsArray.length == 0)
 {
   $("#errorMsgDiv").html('Please Select Category.');
   return; 
	   
 }

 var jsObj={
		categoryIdsList:categoryIdsArray,
		task:'getGalleryListForSelectedCategory'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGalleryListForSelectedCategory.action?"+rparam;
	callAjax(jsObj, url);
}


function getDistrictList()
{
  var fromDate = $("#existingFromText").val();
  var toDate = $("#existingToText").val();

	var jsObj={
		locationScope:"DISTRICT",
		fromDate:fromDate,
		toDate:toDate,
		task:'getLocationsListByScopeId'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getDistrictListForACandidateAction.action?"+rparam;
	callAjax(jsObj, url);
}



function getResponseNewsCountNewsCount()
{
   $("#errorMsgDiv").html('');
   $("#errorMsgDivForCritics").html('');
   var radioVal = $("input:radio[name=candidateCriticsNewsRadio]:checked").val();
   var fromDate = '';
   var toDate = '';
   var galleryIdsArray = new Array();
   var categoryIdsArray = new Array();
   var locationScope = "";
   var locationValue = 0;
   var locationIdsList = new Array();
   var tempVar = "";

   if(radioVal == "byDate")
   {
	 $("#showAndHideDateCriticsDiv").css("display","inline-block");
	 fromDate = $("#criticsExistingFromText").val();
     toDate = $("#criticsExistingToText").val();
	 if(fromDate == '' && toDate =='')
	 {
	  $("#errorMsgDivForCritics").html('Please Select From And To Dates.');
	  return;
	 }
	 if(fromDate == '')
	 {
	  $("#errorMsgDivForCritics").html('Please Select From Date.');
	  return;
	 }
	 if(toDate =='')
	 {
	  $("#errorMsgDivForCritics").html('Please Select To Date.');
	  return;
	 }
	 tempVar = "byDate";

   }
   else
   {
    $("#showAndHideDateCriticsDiv").css("display","none");
    tempVar = "all";
	
   }

    if($("#categoryCheckBoxId").is(":checked"))
	{
	  $('#categoryList > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
       });
	   if(categoryIdsArray.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select Category.');
	     return; 
	   }
	   tempVar = "";
	   
	}

    if($("#galleryCheckBoxId").is(":checked") || $("#categoryGallary").is(":checked"))
	{
	  
       $('#galleryList > option:selected').each(
       function(i){
         galleryIdsArray.push($(this).val());
       });
	   if(galleryIdsArray.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select Gallery.');
	     return; 
	   }
	   tempVar = "";
	   
	}

	if($("#districtCheckboxId").is(":checked"))
	{
      $('#districtList > option:selected').each(
       function(i){
         locationIdsList.push($(this).val());
       });
	   if(locationIdsList.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select District.');
	     return; 
	   }

	   locationScope = "DISTRICT";
	}
   $("#ajaxImg").css("display","inline-block");

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		galleryIdsArray:galleryIdsArray,
        categoryIdsArray:categoryIdsArray,
        locationScope:locationScope,
        tempVar:tempVar,
		locationValuesList:locationIdsList,
	    task:'getRespondedAndNotRespondedNewsCount'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getRespondedAndNotRespondedNewsCountAction.action?"+rparam;
	callAjax(jsObj, url);
}

function  showPartyWiseNewsCount(results)
{
  $("#respondNotRespondNewsCountDiv").html('');
  if(results == null)
	  return;
  var str = '';
  str+='<div class="headingDivCls"><h4>Party Wise Critics</h4></div>';
  str +='<div >';
   if(results.totalNewsCount>0)
   str +='<span class ="span10" style="text-align:center" ><b> TOTAL CRITICS : </b><a href="javascript:{}" onclick="getResponseNewsDetails(\'total\',0,\'partyDetails\',0)"><span class="badge badge-info">'+results.totalNewsCount+'</span></a></span>';
  else
     str +='<span class ="span10"  style="text-align:center"><b>Total News : </b>'+results.totalNewsCount+'</span>';
	  str +='</div>';
	   str +='</br>';
  str +='<div class="thumbnail" style="margin-top:8px;">';
  //str +='<span><b>Total News :</b>'+results.totalNewsCount+'</span>';
   str +='<div >';
  if(results.responseNewsCount > 0)
   str +='<span class ="span5" ><b>  Responded News : </b><a href="javascript:{}" onclick="getResponseNewsDetails(\'responded\',0,\'partyDetails\',0)"><span class="badge badge-info">'+results.responseNewsCount+'</span></a></span> ';
  else
	str +='<span class ="span5" ><b> Not Responded News  : </b><a href="javascript:{}">'+results.responseNewsCount+'</a></span>  ';
  if(results.notResponseNewsCount > 0)
   str +='<span class ="span4" ><b>Not Responded News : </b><a href="javascript:{}" onclick="getResponseNewsDetails(\'notResponded \',0,\'partyDetails\',0)"><span class="badge badge-info">'+results.notResponseNewsCount+'</span></a></span>';
   else
	str +='<span class ="span4" ><b>NotRespond News : </b><a href="javascript:{}">'+results.notResponseNewsCount+'</a></span>';
     str+='</br>';
      str +='</div>';
  // str +='<hr/>';
  str +='<div>';
  str +='<div class="row-fluid">';
  
  str +='<div  class="span6">';
    str +='<table class="table table-hover">';
    str +='<tr>';
    str +='<th>Party</th>';
    str +='<th>Responde News</th>';
    str +='</tr>';

    var selectOptionVo = results.selectOptionVO;
     for(var i in selectOptionVo.selectOptionsList)
     {
       str +='<tr>';
       str +='<td>'+selectOptionVo.selectOptionsList[i].name+'</td>';


       str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'responded \','+selectOptionVo.selectOptionsList[i].id+',\'partyDetails\',0)">'+selectOptionVo.selectOptionsList[i].populateId+'<a></td>';
    
       str +='</tr>';
    }
     str +='</table>';
	 str +='</div>';

    str +='<div class="span6">';
    str +='<table class="table table-hover" >';
    str +='<tr class="success" >';
    str +='<th >Party</th>';
    str +='<th>Not Responde News</th>';
    str +='</tr>';

    var selectOptionVo = results.selectOptionVO;
     for(var i in selectOptionVo.selectOptionsList1)
     {
       str +='<tr>';
       str +='<td>'+selectOptionVo.selectOptionsList1[i].name+'</td>';
       str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'notResponded \','+selectOptionVo.selectOptionsList1[i].id+',\'partyDetails\',0)">'+selectOptionVo.selectOptionsList1[i].populateId+'</a></td>';
    
       str +='</tr>';
    }
     str +='</table>';
	 str +='</div>';
	 str +='</div>';
       str +='</div>';  
    str +='</div>';

  $("#respondNotRespondNewsCountDiv").html(str);
}


//
function getResponseNewsDetails(newsType,partyId,tempVarForParty,candidateId)
{
 
 var radioVal = $("input:radio[name=candidateCriticsNewsRadio]:checked").val();
   var fromDate = '';
   var toDate = '';
   var galleryIdsArray = new Array();
   var categoryIdsArray = new Array();
   var locationScope = "";
   var locationValue = 0;
   var locationIdsList = new Array();
   var tempVar = "";

   if(radioVal == "byDate")
   {
	 $("#showAndHideDateSelectDiv").css("display","inline-block");
	 fromDate = $("#criticsExistingFromText").val();
     toDate = $("#criticsExistingToText").val();
	 if(fromDate == '' && toDate =='')
	 {
	  $("#errorMsgDiv").html('Please Select From And To Dates.');
	  return;
	 }
	 if(fromDate == '')
	 {
	  $("#errorMsgDiv").html('Please Select From Date.');
	  return;
	 }
	 if(toDate =='')
	 {
	  $("#errorMsgDiv").html('Please Select To Date.');
	  return;
	 }
	 tempVar = "byDate";

   }
   else
   {
    $("#showAndHideDateSelectDiv").css("display","none");
    tempVar = "all";
	
   }

    if($("#categoryCheckBoxId").is(":checked"))
	{
	  $('#categoryList > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
       });
	   if(categoryIdsArray.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select Category.');
	     return; 
	   }
	   tempVar = "";
	   
	}

    if($("#galleryCheckBoxId").is(":checked") || $("#categoryGallary").is(":checked"))
	{
	  
       $('#galleryList > option:selected').each(
       function(i){
         galleryIdsArray.push($(this).val());
       });
	   if(galleryIdsArray.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select Gallery.');
	     return; 
	   }
	   tempVar = "";
	   
	}

	if($("#districtCheckboxId").is(":checked"))
	{
      $('#districtList > option:selected').each(
       function(i){
         locationIdsList.push($(this).val());
       });
	   if(locationIdsList.length == 0)
	   {
		 $("#errorMsgDiv").html('Please Select District.');
	     return; 
	   }

	   locationScope = "DISTRICT";
	}

  var urlstr = "getNewsDetailsAction.action?newsType="+newsType+"&partyId="+partyId+"&categoryIds="+categoryIdsArray+"&galleryIds="+galleryIdsArray+"&locationIdsList="+locationIdsList+"&locationScope="+locationScope+"&fromDate="+fromDate+"&toDate="+toDate+"&tempVarForParty="+tempVarForParty+"&candidateId="+candidateId+"&";
  var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}


function getCandidateCritiesNewsDetails()
{

var fromDate = $("#criticsExistingFromText").val();
var toDate = $("#criticsExistingToText").val();
  var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		tempVar:"all",
		task:'getCandidateCritiesNewsDetails'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateCritiesNewsDetailsAction.action?"+rparam;
	callAjax(jsObj, url);
}

function buildCriticsCandidates(results)
{
	$("#candidateCritiesNewsDiv").css("display","block");
   var str = '';
   str +='<div><h4>Candidate Wise Critics</h4></div>';
  str +='<table id="candidateCritiesNewsTab">';
  str +='<thead>';
  str +='<tr>';
 
  str +='<th>Candidate Name</th>';
  str +='<th>Party Name</th>';
  str +='<th>Total News</th>';
  str +='<th>Response News</th>';
  str +='<th>Not Response News</th>';
 
  
  str +='</tr>';
  str +='</thead>';
  str+='<tbody>';
  for(var i in results)
  {
   str +='<tr>';
   
   str +='<td>'+results[i].name+'</td>';
   str +='<td>'+results[i].partyName+'</td>';
   str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'total\',0,\'candidateDetails\','+results[i].id+')">'+results[i].totalNewsCount+'</a></td>';
   str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'responded\',0,\'candidateDetails\','+results[i].id+')">'+results[i].responseNewsCount+'</a></td>';
   str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'notResponded\',0,\'candidateDetails\','+results[i].id+')">'+results[i].notResponseNewsCount+'</a></td>';
  
   
   str +='</tr>';
  }
  str+='</tbody>';
 str +='</table>';
 $("#candidateCritiesNewsDiv").html(str);

 
 
   $('#candidateCritiesNewsTab').dataTable({
		"aaSorting": [[ 2, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null] 
		
		});
 
}


function getPartyWiseCriticsNews(startIndex)
 {
   var fromDate = '';
   var toDate = '';
   var galleryIdsArray = new Array();
   var categoryIdsArray = new Array();
   var locationIdsList = new Array();
   var radioVal = $("input:radio[name=candidateCriticsNewsRadio]:checked").val();
   var tempVar = "all";
   $("#errorMsgDiv").html('');
   $("#errorMsgDivForCritics").html('');
   if(radioVal == "byDate")
   {
	 $("#showAndHideDateCriticsDiv").css("display","inline-block");
	 fromDate = $("#criticsExistingFromText").val();
     toDate = $("#criticsExistingToText").val();
	 if(fromDate == '' && toDate =='')
	 {
	  $("#errorMsgDivForCritics").html('Please Select From And To Dates.');
	  return;
	 }
	 if(fromDate == '')
	 {
	  $("#errorMsgDivForCritics").html('Please Select From Date.');
	  return;
	 }
	 if(toDate =='')
	 {
	  $("#errorMsgDivForCritics").html('Please Select To Date.');
	  return;
	 }
	 tempVar = "byDate";

   }
   

 var jObj=
	{
		
	  firstResult:startIndex,
	  maxResult:10,
      newsType:"total",
      partyId :0,
      categoryIds:"",
	  galleryIds:"",
      locationIdsList :"",
      locationScope :"",
      fromDate:fromDate,
      toDate :toDate,
      tempVar :tempVar,
	  tempVarForParty:"partyDetails",
      candidateId:0,
	  task:"getNewsDetails"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getNewsDetailsForAPartyAction.action?"+rparam;
	callAjax(jObj,url);
}

function buildPaginatedNews(results,jsObj)
{
   $("#partyWiseCriticsNewsMainDiv").css("display","block");
   $("#latestNewsDiv").html('');
   if(results == null || results.length == 0)
	{
     $("#latestNewsDiv").html('No Data Found.');
     $("#paginationId").html('');
	 return;
	}

	var str="";
	str+="<ul class='unstyled pad10'>";
	var temp = results.length; 
	for(var i in results){
		str+="<li style='margin-top: 35px;'>";
		var source = results[i].fileVOList[0].source.trim();
		if(source == "Eenadu Telugu")
		{
			str+="<span class='enadu fontStyle' style='font-weight:bold;'><a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></span>";
		}
		else
		{
			str+="<h3 style='text-transform: capitalize;color: #005580;font-size:14px;'><a  href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h3>";
		}
		
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>";
		
		var path = results[i].filePath1;
		var source = results[i].fileVOList[0].source;
		
		str+="<img id='myImg' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
		if(source == "Eenadu Telugu")
		{
			str+="<p class='span8 enadu fontStyle'>"+results[i].description+"</p>";
		}
		else
		{
			str+="<p class='span8'>"+results[i].description+"</p>";
		}
		
		str+="</div>";

		str+="<div class='span11' style='margin-top: 9px;'>";
		str +='<table><tr><td style="width: 300px; vertical-align: top;">';
		str +='<p style="margin-right: 5px;"><span class="text-error" style="font-weight:bold;">Source : </span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p style="width: 110px;margin-right: 5px;"><span class="text-error" style="font-weight:bold;">Date : </span > '+results[i].fileDate+'</p></td>';
		if(results[i].responseCount > 0)
		str+='<td style="vertical-align: top;"><p style="width: 75px;margin-right: 5px;"><span class="text-error" style="font-weight:bold;padding-left: 20px;"><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /></span > '+results[i].responseCount+'</p></td>';
		
		if(results[i].candidateName != null)
		{
		 str +='<td style="vertical-align: top;"><p style="width: 200px;margin-right: 5px;"><span class="text-error" style="font-weight:bold;">Candidate :</span> '+results[i].candidateName+'</p></td>';
		}
		str +='<td style="vertical-align: top;"><p style="width: 190px;margin-right: 5px;"><span class="text-error" style="font-weight:bold;">Location :</span> '+results[i].locationName+'</p></td>';
		
		str +='<td style="vertical-align: top;font-weight:bold;"><a onclick="getNewsDetailsByContentId('+results[i].contentId+')" class="btn btn-mini btn-info pull-right" type="button">Details...</a></td>';
		if(results[i].responseCount > 0)
		{
		 str +='<td style="vertical-align: top;"><a style="clear:both;font-weight: bold; margin-left: 8px;" onclick="getNewsDetailsByContentId('+results[i].contentId+')" class="btn btn-mini btn-info pull-right" type="button">Track</a></td>';
		}
		str +='</tr></table>';
		str +='</div>';
		
		/* str+="<br><div class='span4 pull-right' style='clear:both;font-weight: bold;'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>Details...</a>";

		if(results[i].responseCount > 0)
		{
		  str +="<a style='font-size: 13px; margin-left: 185px; font-weight: bold;' type='button' class='btn btn-mini btn-info' href='javascript:{}' onclick='getNewsTrackDetails("+results[i].contentId+")'>Track</a>";
		}*/
		
		str +='</div></li>';

		

	}
	
	var itemsCount=results[0].count;
	
	var maxResults=jsObj.maxResult;
	str+="</ul>";

	$("#latestNewsDiv").html(str);
	
	if(jsObj.firstResult==0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getPartyWiseCriticsNews(num);
				
			}

		});
	}
}
function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}

getCandidateNewsCount();
getSelectedNewsDetails();
getResponseNewsCountNewsCount();

$(document).ready(function(){

 $(".candidateNewsRadioCls").click(function(){

 $("#existingFromText").val('');
 $("#existingToText").val('');
 var radioVal = $("input:radio[name=candidateNewsRadio]:checked").val();

 $("#districtShowAndHideDiv").css("display","none");
 $("#categoryShowAndHideTb").css("display","none");
 $("#galleryListShowHideTb").css("display","none");

 $("#districtList").find('option').remove();
 $("#categoryList").find('option').remove();
 $("#galleryList").find('option').remove();

 $("#categoryCheckBoxId").attr("checked",false);
 $("#galleryCheckBoxId").attr("checked",false);
 $("#districtCheckboxId").attr("checked",false);
 $("#categoryGallary").attr("checked",false);

 if(radioVal == "byDate")
 {
	$("#showAndHideDateSelectDiv").css("display","inline-block");

 }
 else
 {
  $("#showAndHideDateSelectDiv").css("display","none");
 }
		
 });
//18111
 
 /* $("#newsDetailsBtn").click(function(){
  var a = $("#candidateNewsMainDiv > div > ul > li[class='active']  > a").attr('id');
  if( /criticsId/i.test(a))
  {
	getResponseNewsCountNewsCount();
	getCandidateCritiesNewsDetails();
	getPartyWiseCriticsNews(0);

  }
else
  getCandidateNewsCount();
	 
	 
 });*/

 $("#newsDetailsBtn").click(function(){
  getCandidateNewsCount();
 });

 $("#criticsNewsDetailsBtn").click(function(){
    getResponseNewsCountNewsCount();
	getCandidateCritiesNewsDetails();
	getPartyWiseCriticsNews(0);
 });

 $(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
});


//Gallery Checkbox

$("#galleryCheckBoxId").click(function(){
$('#errorMsgDiv').html('');
 $("#galleryList").find('option').remove();

 

 if($("#galleryCheckBoxId").is(":checked"))
 {
  $("#categoryCheckBoxId").attr("checked",false);
  $("#galleryListShowHideTb").css("display","inline-block");
  $("#categoryShowAndHideTb").css("display","none");
  

  getGalleriesList();
 }
 else
 {
  $("#galleryListShowHideTb").css("display","none");

 }
	
});

//Category CheckBox

$("#categoryCheckBoxId").click(function(){
   $('#errorMsgDiv').html('');

    $("#categoryList").find('option').remove();
    $("#galleryList").find('option').remove();

	$("#categoryGallary").attr("checked",false);

	if($("#categoryCheckBoxId").is(":checked"))
	{
	 $("#galleryCheckBoxId").attr("checked",false);
	 $("#categoryShowAndHideTb").css("display","inline-block");
	 $("#galleryListShowHideTb").css("display","none");
	 getCategoryList();
	}
	else
	{
	 $("#categoryShowAndHideTb").css("display","none");
	 $("#galleryListShowHideTb").css("display","none");
	}
	
});

//include Gallery

$("#categoryGallary").live("click",function(){
 $('#errorMsgDiv').html('');
 $("#galleryList").find('option').remove();
 if($("#categoryGallary").is(":checked"))
 {
  $("#galleryListShowHideTb").css("display","inline-block");
  getGalleriesForASelectedCategory();
 }
 else
  $("#galleryListShowHideTb").css("display","none");
	
});


//by district

$("#districtCheckboxId").click(function(){

  $('#errorMsgDiv').html('');
  $("#districtList").find('option').remove();
  $("#categoryList").find('option').remove();
  $("#galleryList").find('option').remove();
  $("#galleryListShowHideTb").css("display","none");
  $("#categoryShowAndHideTb").css("display","none");
  $("#galleryCheckBoxId").attr("checked",false);
  $("#categoryCheckBoxId").attr("checked",false);

  if($("#districtCheckboxId").is(":checked"))
  {
	$("#districtShowAndHideDiv").css("display","block");
    getDistrictList();
  }
  else
   $("#districtShowAndHideDiv").css("display","none");
	
});

$("#categoryList").live("click",function(){
   $('#errorMsgDiv').html('');
   $("#categoryGallary").attr("checked",false);
   $("#galleryListShowHideTb").css("display","none");
	
});

$("#districtList").live("click",function(){
 $("#errorMsgDiv").html('');
 $("#galleryCheckBoxId").attr("checked",false);
 $("#categoryCheckBoxId").attr("checked",false);
 $("#galleryListShowHideTb").css("display","none");
	
});

//$("#criticsId").live("click",function(){
// var a= $("#candidateNewsMainDiv > div > ul > li[class='active']  > a").attr('id');
// alert(/criticsId/i.test(a));
 
//});

$("#hideCriticsId").live("click",function(){
	
 $("#hideAndShowLatestNewsDiv").css("display","none");
 $("#hideAndShowCriticsSpan").html("<a class='btn btn-info criticsNewsCls' href='javascript:{}' id='showCriticsId'>Show</a>");

});

$("#showCriticsId").live("click",function(){
 $("#hideAndShowLatestNewsDiv").css("display","block");
 $("#hideAndShowCriticsSpan").html("<a class='btn btn-info criticsNewsCls' href='javascript:{}' id='hideCriticsId'>Hide</a>");

});


$(".candidateCriticsNewsRadioCls").click(function(){

 $("#criticsExistingFromText").val('');
 $("#criticsExistingToText").val('');
 var radioVal = $("input:radio[name=candidateCriticsNewsRadio]:checked").val();

 if(radioVal == "byDate")
 {
	$("#showAndHideDateCriticsDiv").css("display","inline-block");


 }
 else
 {
  $("#showAndHideDateCriticsDiv").css("display","none");
 }
		
 });

});//End OF Ready

$(function(){
	if(requestFor=="candidate"){
		$('#getCandidatesNewsId').trigger('click');
		$('#reportsTabId').addClass('menuActive');
	}
	else if(requestFor="opponent"){
		$('#criticsId').trigger('click');
		$('#reportsTabId').addClass('menuActive');
	}
	else{
		$('#getCandidatesNewsId').trigger('click');
		$('#reportsTabId').addClass('menuActive');
	}
});

</script>
</body>
</html>