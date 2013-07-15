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

<style type="text/css">
#candidateNewsCountDiv table,#respondNotRespondNewsCountDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#candidateNewsCountDiv table tr:nth-child(even),#respondNotRespondNewsCountDiv table tr:nth-child(even){background:#EdF5FF;}
#candidateNewsCountDiv table td,#respondNotRespondNewsCountDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#candidateNewsCountDiv table th{
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
#candidateNewsMainDiv{margin-left: auto; margin-right: auto; float: none; width: 950px; padding-top: 20px; padding-bottom: 20px;}


#allRadio{margin-top: 0px; margin-right: 4px;}
#byDateRadio{margin-top: 0px; margin-left: 15px; margin-right: 4px;}
#existingFromText,#existingToText{width: 130px;}
#radioBtnDiv{margin-bottom: 20px;}
#selectNewsInnerDiv{margin-left: 149px;}
#showAndHideDateSelectDiv{margin-left: 17px;}
#newsDetailsBtn{margin-top: -7px; margin-left: 16px;}
#candidateNewsGraphDiv{clear:both;}
#selectRadioBtnTable td{vertical-align: top;}
#galleryRadioDiv,#categoryRadioDiv,#districtCheckboxDiv{margin-left: 10px;}
#galleryCheckBoxId,#categoryCheckBoxId,#districtCheckboxId{margin-top: 0px;margin-right: 4px;}
#headingSpan{background:#49AFCD;color:#FFF;}
#candidateNewsMainDiv h4{text-align: center; margin-bottom: 20px;}
#headingSpan{padding: 5px 68px; border-radius: 5px;}
#categoryGallary{margin-left: 12px; margin-right: 4px; margin-top: 0px;}
#errorMsgDiv{text-align: center; font-size: 13px; margin-bottom: 10px;}
#districtList,#categoryList,#galleryList{margin-left: 12px;width:200px;}
#respondNotRespondNewsCountDiv{margin-top: 11px; margin-bottom: 25px;}
#respondNotRespondNewsCountDiv table{margin-top: 10px;}
.newsCount{cursor:pointer;}

#candidateNewsCountDiv table td, #respondNotRespondNewsCountDiv table td{color:#005580;}
</style>
<script type="text/javascript">
var fromDate = '${fromDate}';
var toDate = '${toDate}';
</script>

</head>

<body>

<div id="candidateNewsMainDiv">
 <div>
  <h4><span id="headingSpan">Candidate News Details</span></h4>
 
   <div id="errorMsgDiv"></div>
    <div id="selectNewsDiv">
     <div id="selectNewsInnerDiv">
	  <table id="selectRadioBtnTable">
	   <tr>
		 <td>
			<div id="radioBtnDiv"><input type="radio" value="all" name="candidateNewsRadio" class="candidateNewsRadioCls" id="allRadio" checked="true"/><b>All</b>
			<input type="radio" value="byDate" name="candidateNewsRadio" class="candidateNewsRadioCls" id="byDateRadio"/><b>By Date</b></div>
	      </td>
		  <td>
		   <div id="galleryRadioDiv"><input type="checkbox" value="byGallery" class="galleryCheckBoxCls" id="galleryCheckBoxId"/><b>By Gallery</b></div>
		  </td>
		  <td>
		   <div id="categoryRadioDiv"><input type="checkbox" value="byCategory" id="categoryCheckBoxId"/><b>By Category</b></div>
		  </td>
		  <td>
		    <div id="districtCheckboxDiv"><input type="checkbox" value="byDistrict" id="districtCheckboxId"><b>By District</b></div>
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
  <div id="respondNotRespondNewsCountDiv"></div>

 <div id="candidateNewsCountDiv"></div>
</div>

<div id="candidateNewsGraphDiv"></div>
</div>


<script type="text/javascript">

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
			}
			else if(jsObj.task == "getRespondedAndNotRespondedNewsCount")
			{
			 showPartyWiseNewsCount(myResults);
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
  
  if(results == null)
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
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null] 
		//  "aoColumns": [{ "sType": null }, { "sType": "numeric" }, { "sType": "numeric" },        { "sType": "numeric" }, { "sType": "numeric" }]
		});
 }
 else
 {
	$('#newsCountTab').dataTable({
		"aaSorting": [[ 1, "asc" ]],
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
  str +='<div>';
  //str +='<span><b>Total News :</b>'+results.totalNewsCount+'</span>';
  if(results.responseNewsCount > 0)
   str +='<span><b> TDP Candidates Responded News :</b><a href="javascript:{}" onclick="getResponseNewsDetails(\'responded\',0)">'+results.responseNewsCount+'</a></span> <span style="width:1px;padding-right:10px;"></span>';
  else
	str +='<span><b> Not Responded News  :</b><a href="javascript:{}">'+results.responseNewsCount+'</a></span>  ';
  if(results.notResponseNewsCount > 0)
   str +='<span><b>Not Responded News :</b><a href="javascript:{}" onclick="getResponseNewsDetails(\'notResponded \',0)">'+results.notResponseNewsCount+'</a></span>';
   else
	str +='<span><b>NotRespond News :</b><a href="javascript:{}">'+results.notResponseNewsCount+'</a></span>';

  str +='</div>';
  
  str +='<div class="row-fluid">';
  str +='<div class="span3">';
    str +='<table>';
    str +='<tr>';
    str +='<th>Party</th>';
    str +='<th>Responde News</th>';
    str +='</tr>';

    var selectOptionVo = results.selectOptionVO;
     for(var i in selectOptionVo.selectOptionsList)
     {
       str +='<tr>';
       str +='<td>'+selectOptionVo.selectOptionsList[i].name+'</td>';


       str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'responded \','+selectOptionVo.selectOptionsList[i].id+')">'+selectOptionVo.selectOptionsList[i].populateId+'<a></td>';
    
       str +='</tr>';
    }
     str +='</table>';
	 str +='</div>';

    str +='<div class="span4">';
    str +='<table>';
    str +='<tr>';
    str +='<th>Party</th>';
    str +='<th>Not Responde News</th>';
    str +='</tr>';

    var selectOptionVo = results.selectOptionVO;
     for(var i in selectOptionVo.selectOptionsList1)
     {
       str +='<tr>';
       str +='<td>'+selectOptionVo.selectOptionsList1[i].name+'</td>';
       str +='<td><a href="javascript:{}" onclick="getResponseNewsDetails(\'notResponded \','+selectOptionVo.selectOptionsList1[i].id+')">'+selectOptionVo.selectOptionsList1[i].populateId+'</a></td>';
    
       str +='</tr>';
    }
     str +='</table>';
	 str +='</div>';

    str +='</div>';

  $("#respondNotRespondNewsCountDiv").html(str);
}


//
function getResponseNewsDetails(newsType,partyId)
{
 
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

  var urlstr = "getNewsDetailsAction.action?newsType="+newsType+"&partyId="+partyId+"&categoryIds="+categoryIdsArray+"&galleryIds="+galleryIdsArray+"&locationIdsList="+locationIdsList+"&locationScope="+locationScope+"&fromDate="+fromDate+"&toDate="+toDate+"&";
  var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
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

 $("#newsDetailsBtn").click(function(){
	 getResponseNewsCountNewsCount();
     getCandidateNewsCount();
	 
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

});//End OF Ready
</script>
</body>
</html>