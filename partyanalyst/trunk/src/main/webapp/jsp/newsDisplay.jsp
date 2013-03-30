<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js">
</script>
<script type="text/javascript" src="js/problemManagement/problemManagement.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 

<script type="text/javascript" src="js/specialPage/specialPage.js"></script>
<script type="text/javascript" src="js/newsDisplay/newsDisplay.js"></script>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<title></title>


<style>
#fromDate,#toDate{
 cursor: text !important;
}
.pr-cont-sec
{
	margin-left: 10px;
}

.pft-sec {
    color: #777777;
    float: left;
    font-size: 20px;
    line-height: 24px;
    padding: 32px 0 16px 21px;
    width: 244px;
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
.profile-left-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll left top transparent;
    display: inline-block;
    float: left;
    margin-right: 2px;
    padding-top: 5px;
    width: 204px;
}
.profile-mid-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -211px top transparent;
    display: inline-block;
    float: left;
    padding-top: 5px;
    position: relative;
    width: 441px;
}
.profile-right-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -683px top transparent;
    float: left;
    margin-left: 2px;
    padding-top: 5px;
    width: 300px;
}
#contenttable {
    display: block;
    margin-left: auto;
    margin-right: auto;
    padding: 0;
    width: 975px;
}
.imageButton{
	
	-moz-border-radius: 4px 4px 4px 4px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
.view-results a {
    background: url("images/icons/homePage_new/b3.jpg") no-repeat scroll 0 0 transparent;
    border: medium none;
    cursor: pointer;
    display: block;
    height: 27px;
    margin: 11px 0 15px 113px;
    text-indent: -9999px;
    width: 94px;
}
.selectBoxWidth{
	margin-left: 4px;
    margin-top: 26px;
    padding-left: 3px;
    width: 179px;
}
.selectDivStyle {
   
    background-color: #21B2ED;
    color: #FFFFFF;
    float: left;
    padding: 4px;
    width: 179px;
	 font-weight: bold;
	 margin-bottom:10px;
}
.flagStyle  
{
	background:#f0f0f0;float: left;
	width: 60px;
	text-align: center;
	left: 0px;
	clear:both;
	bottom: 0px;
	height: 21px;
	color: #2A4F97;
	font-size: 12px;
    font-weight: bold;
	font-size: 11px;
	line-height: 21px;

}
.selectHeading{
font-size: 13px; width: 187px; border-right: 1px solid rgb(205, 205, 205); border-left: 1px solid rgb(205, 205, 205); border-bottom: 1px solid rgb(205, 205, 205); margin-bottom: 16px;


}

  .gallaryImg
{
	width  : 150px;
	height : 130px;
}
.pagenationStyle{
  background-color:#F5371C;color:#fff;
}
.paginatorElmtClass a {
    border: 1px solid #ADADAD;
    font-size: 11px;
    margin: 0 3px;
    padding: 3px;
}
#buildSources,#buildNewSources{
border:1px solid #d3d3d3;
margin-left: auto;
margin-right: auto;
margin-top: 10px;
margin-bottom: 20px;
width: 618px;
}
#buildVideoNewSources{
border:1px solid #d3d3d3;
margin-left: auto;
margin-right: auto;
margin-top: 10px;
margin-bottom: 20px;
width: 500px;
}
.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

}
.newsParts{
  
  color:#FF4500;
}
 #contentAjaxCallImg{ 
	padding-left: 369px;
    padding-top: 110px;
	display:none;
  }


#videoGallaryPopUpDiv {
    height: 526px;
    min-height: 0;
    width: auto;
}
.titleStyle{color:black;}
.close{opacity:1.5;}
  .close:hover{opacity:1.5;}
#videogallery a{width:120px}

</style>
<style>
#showNews #showNewsCount table{
border:1px solid #dddddd;
}
thead{
background:#dddddd;
}
.yui-skin-sam .yui-pg-container{

text-align:center;
}
.newsImage {
   height:53px;
   width:29px;
}
#headerImageCenterDiv {
		background-image:url(images/icons/constituencyManagement/header_body_blue.png);
		height:30px;
		text-align:center;
		width:250px;		
		}
#headerImageCenterSpan {
		color:#FFFFFF;
		font-size:14px;
		font-weight:bold;
		position:relative;
		top:6px;
		}
.rounded
{
	width: 980px;position:relative; padding:0px; margin:10px 0;background-color:#FFFFFF;
}
.corner 
{
	position:absolute; width:17px; height:17px;
}

.topLeft 
{
	top:0; left:0; background-position:-1px -1px;
}
.topRight 
{
	top:0; right:0;background-position:20px -1px;
}
.bottomLeft
{
	bottom:0; left:0; background-position:-1px 18px;
}
.bottomRight
{
	bottom:0; right:0; background-position:18px 17px;
}

.rounded .corner
{	
background-image:url(../../images/icons/constituencyPage/cornerSprite1.png);
background-color:#E4EDF0;
}
.widgetHeader
		{
			background-image:url("images/icons/districtPage/header_body.png");
			height:36px;
			padding-left: 15px;
		}
		.widgetHeaderSpan
		{
			
			position:relative;
			top:11px;
			color:#4B74C6;
			font-weight:bold;
		}
.f3 {
    border: 2px solid #CFD6DF;
    border-radius: 4px 4px 4px 4px;
    margin-bottom: 10px;
    padding: 0px;
    width: 962px;
	margin-left : auto;
	margin-right : auto;
}
.tinyDateCal
{
position:absolute;
}
.calendarWidth
	{
		height:24px;
		width:22px;
	}
#fromDate_Div{
  height:auto;
  width:170px;
  z-index:9999;
}
#toDate_Div{
  height:auto;
  width:170px;
  z-index:9999;
}
#newsSearch_head {
    background-color: #EEF4F6;
    margin-bottom: 15px;
    padding: 5px;
}

.newsNavigationImage {
    height: 53px;
    width: 29px;
}

.container {
    background-color: #FFFFFF;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    margin: 9px auto 20px;
    max-width: 840px;
    padding: 10px;
}
textarea {
    background-color: #FFFFFF;
    border: 1px solid #5D75A6;
    color: #000000;
    font: 12px/17px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 78px;
    padding: 5px 0 5px 0;
    width: 220px;
}
.tdWidth{
    color: #4B74C6;
    font-weight: bold;
	padding-left: 60px;
	position: inherit;
	width: 160px;
}
.requiredFont {
    color: red;
    font-size: 18px;
}

.imageButton {
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
}
#buildSources{
border:1px solid #d3d3d3;
margin-left: auto;
margin-right: auto;
margin-top: 10px;
margin-bottom: 20px;
width: 618px;
}
.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

}
.newsParts{
  
  color:#FF4500;
}

.unflagClass{
	opacity:0.35;
}
.widgetHeader {
    background-image: url("images/icons/districtPage/header_body.png");
    height: 36px;
    padding-left: 17px;
    width: 916px;
}

.yui-skin-sam .yui-dt-liner {
    margin: 0 0 0 -8px;
    padding: 9px;
	
}
</style>
<script type="text/javascript">

var ajaxCount = 0;
var newsDetails = null;
 var reqFile;
var noOfRowsPerPage = 10;
var modifiedRecord = 0;
var returnedResults;
var bvalue = false;
$(document).ready(function(){

	$('.yui-pg-rpp-options').live('change',function(){
		noOfRowsPerPage = $(this).val();
	});
  $("#newsSearch").slideUp("fast");
        $( "#fromDate" ).datepicker({
            changeMonth: true,
            changeYear: true,
			dateFormat: 'dd-mm-yy',
			maxDate: new Date()
        });
		$( "#toDate" ).datepicker({
            changeMonth: true,
            changeYear: true,
			dateFormat: 'dd-mm-yy',
			maxDate: new Date()
        });
});
function getNews(task,queryType,fileType,sourceId,languegeId,categoryId,newsImportanceId,locationScope,location,title,fromDate,toDate){
if(returnedResults != undefined){
	modifiedRecord = returnedResults.fileGallaryId;
}

    document.getElementById("newsDeleteMessage").innerHTML = "";
    var timeST = new Date().getTime();	
var jsObj=
	      { 
		    queryType	:queryType,
			fileType	:fileType,
			sourceId	:sourceId,
            languegeId	:languegeId,
            categoryId	:categoryId,
            newsImportanceId :newsImportanceId,
            locationScope :locationScope,
            location	:location,
			fromDate	:fromDate,
			toDate		:toDate,
			title		:title,
			timeST      :timeST,
			task		:task
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "getNewsToDisplayAction.action?"+rparam;						
      callAjax(jsObj,url);
}
function drawChart(result,obj){

 document.getElementById("categoryGraphDiv").innerHTML="";
 document.getElementById("sourceGraphDiv").innerHTML="";
 document.getElementById("languageGraphDiv").innerHTML="";
 document.getElementById("newsImportantGraphDiv").innerHTML="";
   
 document.getElementById("showTypes").innerHTML="";
 var str = '<b>Select Other Options   </b>';
str+='<select style="width:150px;" id="graphType" onchange="getOtherGraphs(\''+obj.task+'\',\''+obj.fromDate+'\',\''+obj.toDate+'\');showAjaxImg(\'ajaxImgSpan\');"><option value="1">Category</option>';
str+='<option value="2">Source</option>';
str+='<option value="3">Language</option>';
str+='<option value="4">News Importance</option>';
str+='</select><span id="ajaxImgSpan" style="margin-left:15px;display:none;"><img src="images/icons/search.gif"></img width="18px" height="18px;"></span>';
document.getElementById("showTypes").innerHTML = str;
 if(result.length > 0)
 {
  var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Date');
	 for(var i in result[0].fileVOList){
	 data.addColumn('number',result[0].fileVOList[i].candidateName );
	 }
	 for(var i in result)
	 {
	     var array = new Array();
	     array.push(result[i].fileDate);
	     for(var j in result[i].fileVOList)
	      {
		    array.push(result[i].fileVOList[j].count);
		  }
		  data.addRow(array);
	 }  
	 var chartResultDiv = document.getElementById(obj.id);
	 if(obj.id == "categoryGraphDiv")
	  {
	    ctitle = "Category Wise News Comparison";
		document.getElementById("graphType").value = 1;
	  }
	 else if(obj.id == "sourceGraphDiv")
	 {
	   ctitle = "Source Wise News Comparison";
	   document.getElementById("graphType").value = 2;
	  }
	 else if(obj.id == "languageGraphDiv")
	 {
	  ctitle = "Language Wise News Comparison";
	  document.getElementById("graphType").value = 3;
	  }
	 else if(obj.id == "newsImportantGraphDiv")
	 {
	  ctitle = "News Importance Wise Comparison";
	  document.getElementById("graphType").value = 4;
	  }
	  
	 new google.visualization.LineChart(chartResultDiv).
		  draw(data, {curveType: "function",width: 800, height: 300,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		
		  $('#'+obj.id+'').css('position','inherit');
 }
}
function getOtherGraphs(task,fromDate,toDate){
   var graphTypeEle =   document.getElementById("graphType");
   var  graphType = graphTypeEle.options[graphTypeEle.selectedIndex].value;
   var sourceEle =   document.getElementById("source");
   var  source = sourceEle.options[sourceEle.selectedIndex].value;
   var categoryEle =  document.getElementById("category");
   var  category = categoryEle.options[categoryEle.selectedIndex].value;
   var languageEle =  document.getElementById("language");
   var  language = languageEle.options[languageEle.selectedIndex].value;
   var importanceEle =  document.getElementById("importance");
   var  importance = importanceEle.options[importanceEle.selectedIndex].value;

   if(source == 0)
	 source ="";
  
   if(category == 0)
	 category ="";
   
   if(language == 0)
	 language ="";
   
   if(importance == 0)
	 importance ="";

   if(graphType == 1)
   {
     getGraphDetails(task,"categoryDetailsForGraph",fromDate,toDate,"categoryGraphDiv",source,language,category,importance);
   }
   else if(graphType == 2)
   {
     getGraphDetails(task,"sourceDetailsForGraph",fromDate,toDate,"sourceGraphDiv",source,language,category,importance);
   }
   else if(graphType == 3)
   {
     getGraphDetails(task,"languageDetailsForGraph",fromDate,toDate,"languageGraphDiv",source,language,category,importance);
   }
   else if(graphType == 4)
   {
     getGraphDetails(task,"newsImpDetailsForGraph",fromDate,toDate,"newsImportantGraphDiv",source,language,category,importance);
   }
}
function getGraphDetails(task,queryType,fromDate,toDate,id,sourceId,languegeId,
categoryId,newsImportanceId)
{
 var timeST = new Date().getTime();	
	var jsObj=
	      { 
		  fromDate		:	fromDate,
		  toDate		:	toDate,
		  fileType		:	"All",
		  task			:	task,
		  id			:	id,
		  sourceId		:	sourceId,
		  languegeId	:	languegeId,
		  categoryId	:	categoryId,
		  newsImportanceId :newsImportanceId,
          timeST        : timeST,		  
		  queryType		:queryType
     }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "getNewsToDrawAction.action?"+rparam;						
      callAjax(jsObj,url);
}
google.load("visualization", "1", {packages:["corechart"]});
function callAjax(jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			
			 if(jsObj.queryType == "categoryDetailsForGraph" || jsObj.queryType == "sourceDetailsForGraph" || jsObj.queryType == "languageDetailsForGraph" || jsObj.queryType == "newsImpDetailsForGraph")
			 {
			   drawChart(myResults,jsObj);
			   hideImg();
			   hideAjaxImg('ajaxImgSpan');
			 }  
			 else if(jsObj.queryType == "getCount")
			 {
			   showNewsCountDetails(myResults,jsObj);
			   hideImg();
			 }
			 else if(jsObj.queryType == "getNews")
			 {	
				newsDetails = myResults;
				showNewsDetails(jsObj,myResults);
				hideImg();
			 }
			else if(jsObj.queryType == "getAllSourceDetails")
			 {	
			     if(jsObj.task != "sourceEdit")
				  bildDate(myResults,jsObj.task,jsObj.fileType);
				 else
				   bildDateForSource(myResults);
			 }
			 else if(jsObj.queryType == "getAllCategoryDetails")
			 {	
				bildDate(myResults,jsObj.task,jsObj.fileType);
			 }
			 else if(jsObj.queryType == "getAllSourceLanguageDetails")
			 {	
			    if(jsObj.task != "languageEdit")
				   bildDate(myResults,jsObj.task,jsObj.fileType);
				else
				  bildDateForLanguage(myResults); 
			 }
			 else if(jsObj.queryType == "getAllNewsImportanceDetails")
			 {	
				bildDate(myResults,jsObj.task,jsObj.fileType);
			 }
			 else if(jsObj.task == "Delete")
			 {
			    newsSearch();
				showDeletedMessage(myResults);
			 }
			 else if(jsObj.task == "Update")
			 {
				returnedResults = myResults;
			    newsSearch();
				showUpdatedMessage(myResults.resultStatus);
			 }
			}catch (e) {   		
		   	//alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			//alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showDeletedMessage(result){
  var str='';
 if(result.resultCode == 0)
   str += '<font color="green"><b>News Article Deleted Successfully.</b>';
 else if(result.resultCode == 1)
  str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
  
  document.getElementById("newsDeleteMessage").innerHTML = str;
}

function showUpdatedMessage(result){
  var str='';
  if(result.resultCode == 0)
   str += '<font color="green"><b>News Updated Successfully.</b>';
  else if(result.resultCode == 1)
  str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
  
  document.getElementById("uploadNewsFileErrorDiv").innerHTML = str;
}

function bildDate(optionsList,elmtId,val){
   var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].ids;
		option.text=optionsList[i].names;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	if(elmtId == "sourceEdit")
	{
	  document.getElementById("sourceEdit").value = val;
	}
	else if(elmtId == "categoryEdit")
	{
	  document.getElementById("categoryEdit").value = val;
	}
	else if(elmtId == "languageEdit")
	{
	  document.getElementById("languageEdit").value = val;
	}
	else if(elmtId == "newsimportance")
	{
	  document.getElementById("newsimportance").value = val;
	}
}

function getMaxCount(result)
{
  var count = 0;
  for(var i in result){
   if(result[i].fileVOList !=null && result[i].fileVOList.length > 0)
      if(result[i].fileVOList.length > count)
         count = result[i].fileVOList.length;
	}
   return count;
}

function showNewsCountDetails(result,jsObj)
{
	document.getElementById("showNewsCount").innerHTML='';
	var maxCount = getMaxCount(result);
 if(maxCount >0)
{
  var str = "";
  str += '<table border="1px" CELLSPACING="0" align="center">';
  str += '     <tr style="text-align:center">';
  str += '       <th style="color:#AE6623;">CATEGORY</th><th style="color:#AE6623;">SOURCE</th><th style="color:#AE6623;">LANGUAGE</th><th style="color:#AE6623;">NEWS IMPORTANCE</th><th style="color:#AE6623;">IMPACT LEVEL</th>';
  str += '     </tr>';
  for(i=0 ; i < maxCount ; i++)
   {
   str+= '<tr style="text-align:center">';
      if(result[0].fileVOList[i] != null)
       str+= '<td>'+result[0].fileVOList[i].categoryType+' -  <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\'\',\'\',\''+result[0].fileVOList[i].categoryId+'\',\'\',\'\',\'\',\''+result[0].fileVOList[i].categoryType+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"><font color="brown">  '+result[0].fileVOList[i].sizeOfGallary+'</font></a></td>';
	  else
	   str+= '<td style="text-align:center">--</td>';
	  if(result[1].fileVOList[i] != null)
	    str+= '<td>'+ result[1].fileVOList[i].source+' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\''+result[1].fileVOList[i].sourceId+'\',\'\',\'\',\'\',\'\',\'\',\''+result[1].fileVOList[i].source+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"> <font color="brown"> '+result[1].fileVOList[i].sizeOfGallary+'</font></a></td>';
	  else
	    str+= '<td>--</td>';
	  if(result[2].fileVOList[i] != null)
	   str+= '<td>'+  result[2].fileVOList[i].language+' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\'\',\''+result[2].fileVOList[i].languegeId+'\',\'\',\'\',\'\',\'\',\''+result[2].fileVOList[i].language+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"><font color="brown"> '+ result[2].fileVOList[i].sizeOfGallary+'</font></a></td>';
	  else
	    str+= '<td>--</td>';
	  if(result[3].fileVOList[i] != null)
	    str+= '<td>'+ result[3].fileVOList[i].importance +' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\'\',\'\',\'\',\''+result[3].fileVOList[i].newsImportanceId+'\',\'\',\'\',\''+result[3].fileVOList[i].importance+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"><font color="brown"> '+result[3].fileVOList[i].sizeOfGallary+'</font></a></td>';
	  else
	   str+= '<td>--</td>';
	  if(result[4].fileVOList[i] != null)	
	    str+= '<td>'+ result[4].fileVOList[i].locationScopeValue+' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\'\',\'\',\'\',\'\',\''+result[4].fileVOList[i].locationScope+'\',\'\',\''+result[4].fileVOList[i].locationScopeValue+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"><font color="brown"> '+ result[4].fileVOList[i].sizeOfGallary+'</font></a></td>';
	  else
	    str+= '<td>--</td>';
	 /* if(result[5].fileVOList[i] != null)
	      if(result[5].fileVOList[i].location != null)
	        str+= '<td>'+ result[5].fileVOList[i].locationValue+' -  <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\'\',\'\',\'\',\'\',\''+result[5].fileVOList[i].locationScope+'\',\''+result[5].fileVOList[i].location+'\',\''+result[5].fileVOList[i].locationValue+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"><font color="brown"> '+ result[5].fileVOList[i].sizeOfGallary+'</font></a></td>';
	      else
		    str+= '<td>'+ result[5].fileVOList[i].locationValue+' -  <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'All\',\'\',\'\',\'\',\'\',\''+result[5].fileVOList[i].locationScope+'\',\'\',\''+result[5].fileVOList[i].locationValue+'\',\''+jsObj.fromDate+'\',\''+jsObj.toDate+'\');"><font color="brown"> '+ result[5].fileVOList[i].sizeOfGallary+'</font></a></td>';
	  else
	   str+= '<td>--</td>'; */
	str+= '	  </tr>';
   }
  
  str+= '<table>';
  
 document.getElementById("showNewsCount").innerHTML = str;
 }
}
function showNewsDetails(jsObj,result){

if(result.length == 0)
	$('#flagCountDiv').html('');

   var pageNumber = 0;
	for(var i in result){
		if(i==0){
			var unflaggedNews = result.length - result[i].totalFlaggedNews;
			var noNotesNewsCount = result.length - result[i].totalNotesNews;
			var str='';

			//str+='<div><span><b>Flagged News Count:</b></span>'+result[i].totalFlaggedNews+'<span><b>News with notes Count:</b></span>'+result[i].totalNotesNews+'</div>';

			str+='<div style="margin:12px 0px 0px 125px;"><span><b>Flagged News Count:</b></span><input style="width:16px;margin-right:80px;" type="text" readonly value='+result[i].totalFlaggedNews+' id="totalFlagNewsCount"></input><span><b>News with notes Count:</b></span><input readonly style="width:16px;" type="text" id="totalNotesNewsCount" value='+result[i].totalNotesNews+'></input></div>';

			
			$('#flagCountDiv').html(str);
		}



		if(result[i].contentId == modifiedRecord){
			var j= parseInt(i)+1;
			//pageNumber = Math.ceil(j/10);
			pageNumber = Math.ceil(j/noOfRowsPerPage);
		}
	}

	if(pageNumber == 0)
		pageNumber = 1;

	document.getElementById("newsHeading").innerHTML=jsObj.title+" News";

  document.getElementById("showNews").innerHTML='';
  YAHOO.widget.DataTable.news = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var title = oRecord.getData("fileTitle1");
	var fileId = oRecord.getData("fileId");


	//BY SAMBA START
	var gallaryId = oRecord.getData("contentId");
	//elLiner.innerHTML ="<a href='javascript:{}' onclick='showNews(\""+fileId+"\")'>"+title+"</a>";
	elLiner.innerHTML ="<a href='javascript:{}' onclick='getVideoDetails(\""+gallaryId+"\")'>"+title+"</a>";

	//elLiner.innerHTML ="<a href='javascript:{}' onclick='showNews(\""+fileId+"\")'>"+title+"</a>";

		//BY SAMBA END
  };
  YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var fileId= oRecord.getData("fileId");

	var str='';
	//str+="<a style='float:left;' title='edit this news' href='javascript:{}' onclick='editNewsDetails("+fileId+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";

	str+="<a style='float:left;' title='edit this news' href='javascript:{}' onclick='editNewsDetails("+fileId+")'><i class='icon-pencil'></i></a>";

	//str+="<a style='float:right;' title='delete this news'  href='javascript:{}' onclick='updateDeleteNews(\"Delete\","+fileId+");'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";

   str+="<a style='float:right;' title='delete this news'  href='javascript:{}' onclick='updateDeleteNews(\"Delete\","+fileId+");'><i class='icon-remove-sign'></i></a>";


	elLiner.innerHTML =str;
		
  };
  /*YAHOO.widget.DataTable.delet = function(elLiner, oRecord, oColumn, oData) 
  {
	var fileId= oRecord.getData("fileId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='updateDeleteNews(\"Delete\","+fileId+");'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";
		
  };*/

   YAHOO.widget.DataTable.visibilty = function(elLiner, oRecord, oColumn, oData) 
  {
	var visibility= oRecord.getData("visibility");
	var contentId= oRecord.getData("contentId");
    var str='';
	if(visibility == "false"){
	  str+="<div id='visibilityDiv"+contentId+"'><a href='javaScript:{updateVisibilityToPrivate("+contentId+")}' title='make this news as private'><i class='icon-bullhorn'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+"/ value='public'></input></div>";

	  str+="<div id='nonVisibilityDiv"+contentId+"' style='display:none;'><a href='javaScript:{updateVisibilityToPublic("+contentId+")}' title='make this news as public'><i class='icon-lock'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+" value='private'></input></div>";
	}
	   
	//elLiner.innerHTML ="";
	else{

		 str+="<div id='visibilityDiv"+contentId+"'  style='display:none;'><a href='javaScript:{updateVisibilityToPrivate("+contentId+")}' title='make this news as private'><i class='icon-bullhorn'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+"/ value='public'></input></div>";

	     str+="<div id='nonVisibilityDiv"+contentId+"'><a href='javaScript:{updateVisibilityToPublic("+contentId+")}' title='make this news as public'><i class='icon-lock'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+" value='private'></input></div>";
	
	}

	elLiner.innerHTML = str;
	  

		
  };

  YAHOO.widget.DataTable.flagSet = function(elLiner, oRecord, oColumn, oData) 
  {
	var flagSet= oRecord.getData("flagSet");
	var contentId= oRecord.getData("contentId");
    var str='';
	if(flagSet == "true"){

		str+="<div id='flagSetDiv"+contentId+"'><a href='javaScript:{removeFlagInTable("+contentId+")}' title='unflag this news'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";

		str+="<div id='unFlagSetDiv"+contentId+"' style='display:none;'><a href='javaScript:{saveFlagInTable("+contentId+")}' title='flag this news' class='unflagClass'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";
	
	
	}
	  
	// elLiner.innerHTML ="";
	else if(flagSet == "false"){

		str+="<div id='flagSetDiv"+contentId+"'  style='display:none;'><a href='javaScript:{removeFlagInTable("+contentId+")}' title='unflag this news'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";

		str+="<div id='unFlagSetDiv"+contentId+"'><a href='javaScript:{saveFlagInTable("+contentId+")}' title='flag this news' class='unflagClass'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";
	
	
	}

	 elLiner.innerHTML = str;
		
  };

  YAHOO.widget.DataTable.notes = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var notesExist= oRecord.getData("notesExist");

	if(notesExist == "true")
	  elLiner.innerHTML ="<div style='text-align:center;'><a href='javaScript:{}' ><i class='icon-edit'></i></a></div>";
	else
	  elLiner.innerHTML ="<div style='text-align:center;'><a href='javaScript:{}' class='unflagClass'><i class='icon-edit'></i></a></div>";

		
  };

   YAHOO.widget.DataTable.description = function(elLiner, oRecord, oColumn, oData) 
  {
	var strshortened;
	var user = oData;
	var description= oRecord.getData("description");
	var length = description.length;
	if(length > 50)
	{
		strshortened = description.slice(0,50)+'......';
	}
	else
	{
		strshortened = description;
	}
	elLiner.innerHTML ='<div style="width:70px;">'+strshortened+'</div>';

		
  };

  var newsResultColumnDefs = [ 		    	             
		    	            
							{key:"categoryType", label: "NEWS CATEGORY", resizeable:true, sortable: true},
							{key:"gallaryName", label: "GALLERY", sortable: true},
		    	           	{key:"source", label: "SOURCE", sortable: true, resizeable:true},
							{key:"fileTitle1", label: "TITLE",formatter:YAHOO.widget.DataTable.news, sortable: true, resizeable:true},
							//YAHOO.widget.DataTable.description
								{key:"description", label: "DESCRIPTIONS",
								formatter:YAHOO.widget.DataTable.description, sortable: true, resizeable:true},
							//{key:"description", label: "DESCRIPTIONS", sortable: true},
		    				{key:"locationScopeValue", label: "IMPACT AREA",sortable:true, resizeable:true},
							{key:"locationValue", label: "AREA NAME", sortable: true, resizeable:true},
							{key:"fileDate", label: "NEWS DATE",sortable: true, resizeable:true},
							

							//{key:"delete", label: "DELETE",formatter:YAHOO.widget.DataTable.delet},
							//{key:"visibility", label: "VISIBILITY",formatter:YAHOO.widget.DataTable.visibilty},
					        {key:"visibility", label: "<i class=' icon-eye-open'></i>",formatter:YAHOO.widget.DataTable.visibilty, sortable: true},
							{key:"flagSet", label: "<a href='javaScript:{}' ><i class='icon-flag'></i></a>", sortable: true,formatter:YAHOO.widget.DataTable.flagSet},
							{key:"notesExist", label: "<i class='icon-edit'></i>",formatter:YAHOO.widget.DataTable.notes, sortable: true},
							{key:"update", label: "",formatter:YAHOO.widget.DataTable.edit}
		    	        ]; 
	var newsResultDataSource = new YAHOO.util.DataSource(result); 


    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		       // rowsPerPage    : 10,
			    rowsPerPage   :  noOfRowsPerPage ,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ],
				totalRecords:result.length,
					row:1,
               // initialPage:2
				initialPage:pageNumber
			    }) 
				};

	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "categoryType","gallaryName","source","fileTitle1","description","locationScopeValue","locationValue","fileDate","fileId","edit","delete"]
					};

		var newsResultDataSource = new YAHOO.widget.DataTable("showNews", newsResultColumnDefs,myDataSource, myConfigs);

}

function showNews(fileId)
{	
	 $.fx.speeds._default = 1000;
	  $("#showNewsOuterDiv").dialog({ stack: false,
								height: 500,
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsOuterDiv").dialog();

	displayNews(fileId);
}	

function displayNews(fileId)
{


	for(var i=0;i<newsDetails.length;i++)
	{
	if(newsDetails[i].fileId == fileId)
	{
		document.getElementById('ui-dialog-title-showNewsOuterDiv').
			innerHTML = '<center><font color="Navy">'+newsDetails[i].fileTitle1+'</font><center>';
		 var str ='<div><center>';
		 str += ' <table>';
		 str += '     <tr>';
		 str += '       <td>';
		 str += '		 <B>Total News Aricles : <font color="#FF4500">'+newsDetails.length+'</font> &nbsp;&nbsp;&nbsp;';
		 str += '		 <B>Present Article is : <font color="#FF4500">'+(i+1)+'</font> &nbsp;&nbsp;&nbsp;';
		 str += '        <B>Source</B> :<span id="sourcetypeimgDiv"> <font color="#FF4500">'+newsDetails[i].fileVOList[0].source+'</font> &nbsp;&nbsp;&nbsp;</span><span><B> Date </B>:<font color="#FF4500"> '+newsDetails[i].fileDate+'</font></span>';
		 str += '       </td>';
		 str += '     </tr>';
		 str += '     </table>';
		
		 str += '     <table>';
		 str += '			<tr>';
		 str += '<td width="5%">';
		
		 if(i != 0)
		 {
			str += '<a onclick="displayNews('+newsDetails[i-1].fileId+')" href="javascript:{}"><img class="newsNavigationImage" src="images/icons/jQuery/previous.png" alt=""></a>';
		 }
		 str += '</td>';
		 str += '<td><div class="container" id="newschangeDIV"><img alt="'+newsDetails[i].fileTitle1+'" src="'+newsDetails[i].fileVOList[0].fileVOList[0].path+'" title="'+newsDetails[i].description+'" style="max-width:820px;max-length:800px;"/></div></td>';
		 str += '<td width="5%">';
		 if(i != (newsDetails.length - 1))
		 {
			str += '<a onclick="displayNews('+newsDetails[i+1].fileId+')" href="javascript:{}"><img class="newsNavigationImage" src="images/icons/jQuery/next.png" alt=""></a>';
		 }
		 str += '</td>';
		 str += '	</tr></table>';	 
		 
		 str += '<table><tr>';
		 str += '       <td>';
		 str += '        '+newsDetails[i].description+'';
		 str += '       </td>';
		 str += '     </tr>';
		 str += '</table>';	 
		 str += '</center></div>';
         str +='<div id="buildSourceParts">';
	 str += '<center><table><tr>';

	for(var j=1;j<newsDetails[i].fileVOList[0].fileVOList.length;j++)
	{
	  str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+newsDetails[i].fileVOList[0].fileSourceLanguageId+','+newsDetails[i].fileVOList[0].fileVOList[j].orderNo+','+fileId+',\''+newsDetails[i].fileTitle1+'\',\''+newsDetails[i].description+'\',\''+newsDetails[i].fileVOList[0].fileVOList[j].path+'\')">'+newsDetails[i].fileVOList[0].fileVOList[j].orderName+'</a></td>';
	}
	str += '  </tr></table>';
	str +='</center></div>';
	
	if(newsDetails[i].multipleSource >1 )
	{
	  str +='<div id="buildSources">';
	  str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	  str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	  for(var k=1;k<newsDetails[i].fileVOList.length;k++)
	  {
	     str += '<td><a class="newssources" href="javascript:{}" onclick="showAnotherSource('+newsDetails[i].fileVOList[k].fileSourceLanguageId+','+fileId+')">'+newsDetails[i].fileVOList[k].source+'</a></td>';
	  }
	  str += '  </tr></table>';
	  str +='</center></div>';
	}		 
		 document.getElementById("showNewsDiv").innerHTML= str;
		}
	}
 }
function showAnotherNewsPart(fileSourceLanguageId,orderNo,fileId,title,desc,path)
 {
    var results;
	 
	    for(var i in newsDetails){
	       if(newsDetails[i].fileId == fileId)
	        results = newsDetails[i];
	     }	 
	 
	  document.getElementById("newschangeDIV").innerHTML = '<img alt="'+title+'" src="'+path+'" title="'+desc+'" style="max-width:820px;max-length:800px;"/>';

	  var str='';
	  str += ' <center> <table><tr>';
	 for(var j in results.fileVOList)
	 {
	    if(results.fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
		 for(var k in results.fileVOList[j].fileVOList)
		 {
		   if(results.fileVOList[j].fileVOList[k].orderNo != orderNo)
		    str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+results.fileVOList[j].fileSourceLanguageId+','+results.fileVOList[j].fileVOList[k].orderNo+','+results.fileId+',\''+results.fileTitle1+'\',\''+results.description+'\',\''+results.fileVOList[j].fileVOList[k].path+'\')">'+results.fileVOList[j].fileVOList[k].orderName+'</a></td>';
		 }
	 }
	 str += '  </tr></table></center>';
	 document.getElementById("buildSourceParts").innerHTML = str;
 }
 function showAnotherSource(fileSourceLanguageId,fileId)
 {
     var results;
	 
	    for(var i in newsDetails){
	       if(newsDetails[i].fileId == fileId)
	        results = newsDetails[i];
	     }
    for(var j in results.fileVOList)
    {
      if(results.fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
       {	  
         document.getElementById("sourcetypeimgDiv").innerHTML = '<font color="#FF4500">'+results.fileVOList[j].source+'</font> &nbsp;&nbsp;&nbsp;';		 
         document.getElementById("newschangeDIV").innerHTML = '<img alt="'+results.fileTitle1+'" src="'+results.fileVOList[j].fileVOList[0].path+'" title="'+results.description+'" style="max-width:820px;max-length:800px;"/>';
 
	      var str='';
          str += '<center><table><tr>';
	   
	      for(var k=1;k<results.fileVOList[j].fileVOList.length;k++)
	      {
	          str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+results.fileVOList[j].fileSourceLanguageId+','+results.fileVOList[j].fileVOList[k].orderNo+','+fileId+',\''+results.fileTitle1+'\',\''+results.description+'\',\''+results.fileVOList[j].fileVOList[k].path+'\')">'+results.fileVOList[j].fileVOList[k].orderName+'</a></td>';
	       }
	      str += '  </tr></table>';
	       str +='</center>';
          document.getElementById("buildSourceParts").innerHTML = str;
		}
	}
	var str='';
	str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	  str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	  for(var m=0;m<results.fileVOList.length;m++)
	  {
	    if(results.fileVOList[m].fileSourceLanguageId != fileSourceLanguageId)
	     str += '<td><a class="newssources" href="javascript:{}" onclick="showAnotherSource('+results.fileVOList[m].fileSourceLanguageId+','+fileId+')">'+results.fileVOList[m].source+'</a></td>';
	  }
	  str += '  </tr></table>';
	  str +='</center>';
	  document.getElementById("buildSources").innerHTML = str;
   
 }
 function showDates(){
 
	 //document.getElementById("fromDate").value=null;
     //document.getElementById("toDate").value=null;
	 document.getElementById('DateErrDiv').innerHTML='&nbsp;';

   if(document.getElementById("betweendates").checked == true)
     document.getElementById("showDates").style.display = "block";
   else
     document.getElementById("showDates").style.display = "none";
 }
 function toggleOption(){
    document.getElementById("source").value ="0";
	document.getElementById("category").value ="0";
	document.getElementById("language").value ="0";
	document.getElementById("importance").value ="0";
   $("#newsSearch").slideToggle("slow");
 }
 
 function newsSearch(){
 showAjaxImg('searchAjaxImgSpan');
   var sourceEle =   document.getElementById("source");
   var  source = sourceEle.options[sourceEle.selectedIndex].value;
   var categoryEle =  document.getElementById("category");
   var  category = categoryEle.options[categoryEle.selectedIndex].value;
   var languageEle =  document.getElementById("language");
   var  language = languageEle.options[languageEle.selectedIndex].value;
   var importanceEle =  document.getElementById("importance");
   var  importance = importanceEle.options[importanceEle.selectedIndex].value;

   if(source == 0)
	 source ="";
  
   if(category == 0)
	 category ="";
   
   if(language == 0)
	 language ="";
   
   if(importance == 0)
	 importance ="";
	
 if(document.getElementById("today").checked == true)
   {
      getNews('byTodayDate','getCount','All',source,language,category,importance,'','','','','');
	  getGraphDetails("byTodayDate","categoryDetailsForGraph","","","categoryGraphDiv",source,language,category,importance);
	  getNews('byTodayDate','getNews','All',source,language,category,importance,'','','Today','','');
   }
 if(document.getElementById("thisweek").checked == true)
  {
     getNews('byThisWeek','getCount','All',source,language,category,importance,'','','','','');
	 getGraphDetails("byThisWeek","categoryDetailsForGraph","","","categoryGraphDiv",source,language,category,importance);
	 getNews('byThisWeek','getNews','All',source,language,category,importance,'','','This Week','','');
   }
 if(document.getElementById("thismonth").checked == true)
  {
    getNews('byThisMonth','getCount','All',source,language,category,importance,'','','','','');
	getGraphDetails("byThisMonth","categoryDetailsForGraph","","","categoryGraphDiv",source,language,category,importance);
	getNews('byThisMonth','getNews','All',source,language,category,importance,'','','This Month','','');
   }
 if(document.getElementById("betweendates").checked == true)
  {
	  var fromDate = "";
	  var toDate = "";	
		fromDate = document.getElementById("fromDate").value;
		toDate =  document.getElementById("toDate").value;

// STARTING : validations updated by srishailam

	   var arrr = fromDate.split("-");
			var fromDat=arrr[0];
			var frommonth=arrr[1];
			var fromyear=arrr[2];
	   var arr = toDate.split("-");
			var toDat=arr[0];
			var tomonth=arr[1];
			var toyear=arr[2];
	if(!fromDate){
			document.getElementById('DateErrDiv').innerHTML='Please select from Date ';
			$('#searchImg').hide();
			return false;
		}
	if(!toDate){
			$('#searchImg').hide();
			document.getElementById('DateErrDiv').innerHTML='Please select to Date ';
			return false;
		}
	if(fromyear>toyear){
		$('#searchImg').hide();
		document.getElementById('DateErrDiv').innerHTML='From Date should not greater then To Date ';
		return false;
	}
	 if(frommonth>tomonth){
		   if(fromyear == toyear){
		   $('#searchImg').hide();
			document.getElementById('DateErrDiv').innerHTML='From Date should not greater then To Date ';
			return false;
		}
		
	}
	
	if(fromDat>toDat){	
		if(frommonth == tomonth && fromyear == toyear){			
			 $('#searchImg').hide();
			document.getElementById('DateErrDiv').innerHTML='From Date should not greater then To Date ';
			return false;		
		   }
	}
	document.getElementById('DateErrDiv').innerHTML='&nbsp;';
	$('#searchImg').show();
	
	var title = 'Between Dates '+fromDat+' and '+toDat;	
	startDate=fromyear+"-"+frommonth+"-"+fromDat;	
	endDate=toyear+"-"+tomonth+"-"+toDat;
	
	getGraphDetails("betweendates","categoryDetailsForGraph",startDate,endDate,"categoryGraphDiv",source,language,category,importance);
  	getNews("betweendates","getCount","All",source,language,category,importance,"","","",startDate,endDate);
  	getNews("betweendates","getNews","All",source,language,category,importance,"","",title,startDate,endDate);
	
// ENDING : validations updated by srishailam
   }
  }
  function clearAll(){
  
   document.getElementById("newsSearch").style.display = "none";
    document.getElementById("source").value ="0";
	document.getElementById("category").value ="0";
	document.getElementById("language").value ="0";
	document.getElementById("importance").value ="0";
    document.getElementById("fromDate").value = "";
    document.getElementById("toDate").value = "";
 }

 function showAjaxImg(divId)
 {
	 ajaxCount = 0;
	 document.getElementById(divId).style.display = 'inline-block';
 }

 function hideAjaxImg(divId)
 {
	 document.getElementById(divId).style.display = 'none';
 }

 function hideImg()
 {
	ajaxCount++;
	if(ajaxCount == 3)
	{
		hideAjaxImg('searchAjaxImgSpan');
		ajaxCount = 0;
	}
 }
function editNewsDetails(fileId){

	bvalue = false;
  $("#editNewsOuter").dialog({ stack: false,
							    height: 'auto',
								width: 700,
								position:[150,120],								
								modal: true,
								title:'Edit News Details',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#editNewsOuter").dialog();
	var str= '';
	
	for(var i in newsDetails){
	  if(newsDetails[i].fileId == fileId)
	  {
	    reqFile = newsDetails[i];
		
	  }
	}


	var str ='';

	str+='<div style="float:right;"><div style="float:left;"><b>Flag Status:</b><input type="hidden" name="flagValue" id="flagInd" value="'+reqFile.flagSet+'"></input></div>';
      if(reqFile.flagSet == "true"){
		  str+='<div id="flagSet" style="width: 130px;"><a href=javaScript:{setFlagVariable(\'false\');}   ><i class="icon-flag"></i></a></div>';

		  str+='<div id="flagUnSet" style="display:none;width: 130px;"><a href=javaScript:{setFlagVariable(\'true\');}  class="unflagClass" ><i class="icon-flag"></i></a></div>';
	  }
	  else{
		    str+='<div id="flagSet" style="display:none;width: 130px;"><a href=javaScript:{setFlagVariable(\'false\');}><i class="icon-flag"></i></a></div>';

		   str+='<div id="flagUnSet" style="width: 130px;"><a href=javaScript:{setFlagVariable(\'true\')}  class="unflagClass" ><i class="icon-flag"></i></a></div>';
		 // str+='';
	  }
	  str+='<div style="clear: both" ></div>';
	  str+='</div>';

	str+='<div>';
	str += '<fieldset>';
	
	str += '<table><tr><td><div id="uploadNewsFileErrorDiv"  style="padding-left:40px;" /></td></tr></table>';
	str += '<table>';
	str+='<tr>';
	str+='<td class="tdWidth">Select News Gallery<font class="requiredFont">*</font></td>';
	str+='<td class="selectWidthPadd"><select style="width:222px;"  id="gallaryId" name="gallaryId" class="selectWidth"/><option value="0">Select</option></select></td>';
	str+='</tr>';
    str += '   <tr>';
	str += '       <td class="tdWidth">Title<font class="requiredFont">*</font></td>';
	str += '       <td><input type="text" id="fileTitle" size="25" maxlength="100"></text></td>'; 
	str += '   </tr>';
	str += '   <tr>';
    
	str += '       <td class="tdWidth">News Description<font class="requiredFont">*</font></td>';
	str += '       <td><textarea id="fileDescription" cols="20" rows="3"></textarea></td>';
	str += '   </tr>';

    str += '   <tr>';
	str += '       <td class="tdWidth">Keywords</td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="keywords" name="keywords" size="25" maxlength="200" style="margin-top:8px;"></text></td></tr>';

	str += '   <TR>';
	
	
	str += ' <td class="tdWidth"><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD><input type="text" id="existingFromText" readonly="true" name="fileDate" size="20" style="margin-top:8px;"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	
	str += '   </TR>';


	for(var i in reqFile.fileVOList)
	{
	   str += '   <tr>';
	   str += '       <td class="tdWidth">Source<font class="requiredFont">*</font></td>';
	   str += '       <td><select id="sourceEdit'+i+'" style="width:222px;"></select></td>';
	   str += '       <td><input type="hidden" id="sourceEditId'+i+'" value="'+reqFile.fileVOList[i].fileSourceLanguageId+'" /></td>';
	   str += '   </tr>';
	
	   str += '   <tr>';
	   str += '       <td class="tdWidth">Language<font class="requiredFont">*</font></td>';
	   str += '       <td><select id="languageEdit'+i+'" style="width:222px;"></select></td>';
	   str += '       <td><input type="hidden" id="languageEditId'+i+'" value="'+reqFile.fileVOList[i].fileSourceLanguageId+'" /></td>';
	   str += '   </tr>';
	}
	str += '       <td class="tdWidth">Category</td>';
	str += '  <td><select id="categoryEdit" style="width:222px;"><option value="0">-select category-</option></select></td>';
	str += '   </tr>';
	str += '   </tr>';
	str += '       <td class="tdWidth">News Importance<font class="requiredFont">*</font></td>';
	str += '       <td><select id="newsimportance" style="width:222px;"></select></td>';
	str += '   </tr>';

	/*
		str += '   <tr>';
		str += '       <td class="tdWidth">File Path<font class="requiredFont">*</font></td>';
		str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId" size="25" style="margin-top:8px;"/></td>';
		str += '       <td class="selectWidthPadd" style="padding-left:24px;"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreFiles()" title="Click here to add more images" alt=""Click here to add more images""/></td>';
		str += '   </tr>';
	*/

	str += '   <tr>';
	str += '      <td colspan="2"> <div id="addMoreFilesDiv" style="margin-left:107px;"></div></td>';
	str += '   </tr>';

	/*
		str +='<tr><td class="tdWidth" style="width:97px;">Image To Display:</td><td><input type="file" name="imageForDisplay" id="newsfileId" size="25" style="margin-top:8px;"/></td></tr>';
	*/
	//str += '   <tr>';
	

	str += '   <tr>';
	str += '       <td></td>';

	//Here visibility means isPrivate
	if(reqFile.visibility == "false")

		//str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font id="visiblePublicText" color="#4B74C6">Visible to Public Also</font></b></input></label></td>';

		str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><i  class="icon-bullhorn" ></i></input></label></td>';
	else

		//str += '       <td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font id="visiblePublicText" color="#4B74C6">Visible to Public Also</font></b></input></label></td>';

		str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId"><i  class="icon-bullhorn" ></i></input></label></td>';

    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';

	if(reqFile.visibility == "true")

	 // str += '       <td id="newsPrivateRadioId"><label><input type="radio" value="private" name="visibility" checked="true" id="privateRadioId"><b><font id="visiblePrivateText" color="#4B74C6">Make This Private</font></b></input></label></td>';
	  str += '<td id="newsPrivateRadioId"><label><input type="radio" value="private" name="visibility" checked="true" id="privateRadioId"></input><i  class="icon-lock" ></i></label></td>';
   else
	  // str += '       <td id="newsPrivateRadioId"><label><input type="radio" value="private"  name="visibility" id="privateRadioId"><b><font id="visiblePrivateText" color="#4B74C6">Make This Private</font></b></input></label></td>';

    str += '<td id="newsPrivateRadioId"><label><input type="radio" value="private"  name="visibility" id="privateRadioId"></input><i  class="icon-lock" ></label></td>';

	str += '   </tr>';

	str +='    <tr>';
    str +='	   <td class="tdWidth">Location Scope<font class="requiredFont">*</font></td>';
    str +='	   <td><select id="scopeDiv" name="locationScope" style="width:222px;" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
 
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs" />'; 
	str +='    </td>';
	str +='  </tr>';

   if(reqFile.locationScope != null && reqFile.locationScope != "null"){


		str +='  <tr>';
		str +='    <td>';

       str+='<input type="hidden" id="locationValueId" value='+reqFile.location+'></input>';

		str +='       <div style="font-weight:bold;margin-left: 54px;">'+reqFile.locationValue+'</div>'; 
		str +='    </td>';
		str +='    <td>';
		str +='<input type="button" class="btn" value="Edit" onClick="getLocationDiv();"/>'; 
		str +='    </td>';
		str +='  </tr>';

   }
   
	



	str += '</table>';
	str += '<div style="padding-left:223px;padding-top:10px;"> <input type="button" value="Update" class="imageButton" onclick="updateDeleteNews(\'Update\','+fileId+');" /></div>';
	str += '</fieldset>';
	str+='</div>';

	
    
	str+='<input type="hidden" name="fileGallaryId"  id="fileGallaryId" value='+reqFile.contentId+'></input>';
	    document.getElementById("editNewsInner").innerHTML = str;
		
	
	   
		//document.getElementById("existingFromText").value = reqFile.fileDate;
	    document.getElementById("fileTitle").value = reqFile.fileTitle1;
	    document.getElementById("keywords").value = reqFile.keywords;
		document.getElementById("fileDescription").value = reqFile.description;
		document.getElementById("existingFromText").value = reqFile.fileDateAsString;

		getNews("sourceEdit","getAllSourceDetails","","","","","","","","","","");
        getNews("categoryEdit","getAllCategoryDetails",reqFile.categoryId,"","","","","","","","","");
        getNews("languageEdit","getAllSourceLanguageDetails","","","","","","","","","","");
        getNews("newsimportance","getAllNewsImportanceDetails",reqFile.newsImportanceId,"","","","","","","","","");
	
		
		//CHANGE BY SAMBA
	getCandidateGallariesForUplaod("News Gallary",reqFile.fileId);  
	getScopes();
//alert(reqFile.contentId);


 }

function getLocationDiv(){
	bvalue=true;
	var id =  $('#scopeDiv').val();
 getLocations1(id);
}
 function getLocations(id){
bvalue=true;
   if(id==0)
  {
   var val ='';
  val +='<table>';
  val +='  <tr><td></td>';
  val +='  </tr>';
  val +='</table>';
  document.getElementById("showScopeSubs").innerHTML = val;
    
  }
  else if(id==1)
  {
    var str ='';
  str +='<table style="margin-left:-5px">';
  str +='  <tr><td></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
  }
  else if(id==2)
  {
   var str ='';
  str +='<table style="width: 450px;">';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" name="locationValue" style="width:222px;" class="selectWidth"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==3)
  {	 
   var str ='';
 str +='<table style="margin-left:-5px">';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;"><span>State<font class="requiredFont">*</font></span></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" style="width:222px;"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" style="width:222px;"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table style="margin-left:-5px">';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;"><span>State<font class="requiredFont">*</font></span></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" style="width:222px;"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" style="width:222px;"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv" name="locationValue" style="width:222px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==5 || id==7)
  {
   if(id==5)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==7)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
   
    var str ='';
  str +='<table style="margin-left:-5px">';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv"   class="selectWidth" style="width:222px;" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" style="width:222px;" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" style="width:222px;" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/ Municipality/ Corp/ GMC<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" style="width:222px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==6 || id==8)
  {
   if(id==6)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==8)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
    var str ='';
  str +='<table style="margin-left:-5px">';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv"  class="selectWidth" style="width:222px;" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" style="width:222px;" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" style="width:222px;" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/ Municipality/ Corp/ GMC<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv"  class="selectWidth" style="width:222px;" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Village/Ward/Division<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" style="width:222px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table style="margin-left:-5px">';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" style="width:222px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" style="width:222px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv" class="selectWidth" style="width:222px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/ Municipality/ Corp/ GMC<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" class="selectWidth" style="width:222px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Village/Ward/Division<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" style="width:222px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
}

function getStatesForLocationScope()
{
  var jsObj =
		{ 
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
}



function getCandidateGallariesForUplaod(contentType , fileId)
{
//var candidateId=document.getElementById("candidateId").value;
	var jsObj =
		{ 
			contentType : contentType,
		   	task : "candidateGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidateGallaries.action?"+rparam;
	callnewAjax(jsObj,url);
}

function getScopes(){
  
 var jsObj =
		{ 
            time : timeST,
			task:"getScopesForNewSearch"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
 
}

function getDistricts1(stateId){
  var jsObj =
		{ 
            time : timeST,
			stateId : stateId,
			task:"getDistrictsByStateId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
 
}

 function getAllDetails(id,task,areaType,constId)
{
	var jsObj =
	{ 
		time : timeST,
		id:id,
		task:task,
		taskType:"",
		selectElementId:"",
		address:"",
		areaType:areaType,
		constId:constId
	};
 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
 callnewAjax(jsObj,url);
}


function callnewAjax(jsObj,url){
var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
         	 
         if(jsObj.task == "getCandidatePhotoGallaryDetail")
			{
               buildCandidatePhotoGallary(myResults);
			}
			else if(jsObj.task == "candidateGallariesForUplaod")
			{ 
			   if(jsObj.contentType=="News Gallary")
				{
					//clearOptionsListForSelectElmtId('gallaryId');
					//createOptionsForSelectElmtId('gallaryId',myResults);

					//$("#gallaryId").prepend("<option value='0'>Select Gallery</option>");
					//document.getElementById("gallaryId").value = 0;
						//$('#gallaryId').val(reqFile.contentId);

						appendResults(myResults,"gallaryId");
				
				}
				else if(jsObj.contentType=="Video Gallary")
				{
					clearOptionsListForSelectElmtId('gallarySelectId');
					createOptionsForSelectElmtId('gallarySelectId',myResults);

					$("#gallarySelectId").prepend("<option value ='0'>Select Gallery</option>");
					document.getElementById("gallarySelectId").value = 0;
				}
				else if(jsObj.contentType=="Photo Gallary")
				{
					clearOptionsListForSelectElmtId('gallaryphotoselectId');
					createOptionsForSelectElmtId('gallaryphotoselectId',myResults);
					$("#gallaryphotoselectId").prepend("<option value='0'>Select Gallery</option>");
                    document.getElementById("gallaryphotoselectId").value = 0;
				}
			}
			else if(jsObj.task == "getScopesForNewSearch")
			{ 
			   //clearOptionsListForSelectElmtId("scopeDiv");
              // buildResults(myResults,"scopeDiv");

			  appendResults(myResults,"scopeDiv");
			}
			else if(jsObj.task == "getStates"){

              appendResults(myResults,"stateDiv");
			}
			else if(jsObj.task == "getDistrictsByStateId"){
				appendResults(myResults,"districtDiv");
			}else if(jsObj.task == "constituenciesInDistrict"){
				appendResults(myResults,"constituencyDiv");
			}else if(jsObj.task == "getConstNotInGivenAreaType"){
                appendResults(myResults,"constituencyDiv");
			}else if(jsObj.task == "subRegionsInConstituency"){ 
               appendResults(myResults,"mandalDiv");
			}else if(jsObj.task == "hamletsOrWardsInRegion"){ 
               appendResults(myResults,"villageDiv");
			}else if(jsObj.task == "boothsInTehsilOrMunicipality"){ 
               appendResults(myResults,"villageDiv");
			}else if(jsObj.task == "saveFileComment"){

				alert("Your comment is saved successfully.");
			}
		
}
		catch(e)
		{   
		}  
	 },
	scope : this,
	failure : function( o )
	{
							
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}


function appendResults(results , divId){

	$('#'+divId).find('option').remove();

    if(divId == "scopeDiv"){
		//$('#'+divId).append(new Option("Select Scope", "0"));

		for(var i in results){			

			$('#'+divId).append('<option value='+results[i].locationScope+'>'+results[i].locationScopeValue+'</option>');
		}

		if(reqFile.locationScope != null && reqFile.locationScope != "null")
		$('#scopeDiv').val(reqFile.locationScope);
		//getLocations(reqFile.locationScope);

	}


	if(divId == "stateDiv"){
		//$('#'+divId).append(new Option("Select State", "0"));

		for(var i in results){

		$('#'+divId).append('<option value='+results[i].ids+'>'+results[i].names+'</option>');
	    }
	}

	if(divId == "districtDiv"){

		//$('#'+divId).append(new Option("Select District", "0"));

		for(var i in results){

		$('#'+divId).append('<option value='+results[i].ids+'>'+results[i].names+'</option>');
	    }
	}
   
    if(divId == "constituencyDiv"){

		//$('#'+divId).append(new Option("Select Constituency", "0"));

		for(var i in results){
		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
	    }

	} 

	if(divId == "mandalDiv"){

		//$('#'+divId).append(new Option("Select Mandal", "0"));

		for(var i in results){
		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
	    }

	}

	if(divId == "villageDiv"){

		//$('#'+divId).append(new Option("Select Village", "0"));

		for(var i in results){
		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
	    }

	}

	if(divId == "gallaryId"){

		//$("#gallaryId").append("<option value='0'>Select Gallery</option>");	

		for(var i in results){

		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');

	    }

	$('#gallaryId').val(reqFile.fileGallaryId);

	}
    
}

 function bildDateForSource(results)
 {
   for(var j in reqFile.fileVOList)
  {
    elmt = document.getElementById('sourceEdit'+j+'');
    for(var i in results)
	{
		var option = document.createElement('option');
		option.value=results[i].ids;
		option.text=results[i].names;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	document.getElementById('sourceEdit'+j+'').value = reqFile.fileVOList[j].sourceId;
  }
}
 function bildDateForLanguage(results)
 {
      for(var j in reqFile.fileVOList)
  {
    elmt = document.getElementById('languageEdit'+j+'');
    for(var i in results)
	{
		var option = document.createElement('option');
		option.value=results[i].ids;
		option.text=results[i].names;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	document.getElementById('languageEdit'+j+'').value = reqFile.fileVOList[j].languegeId;
  }
 }
 
 function updateDeleteNews(task,fileId){

var fileTitle = $('#fileTitle').val();
var fileDescription = $('#fileDescription').val();
var flagInd = $('#flagInd').val();
var scope = $('#scopeDiv').val();
var errorMessage = "";
var validate = false;

if(fileTitle == ""){
 errorMessage+="File title is required<br>";
 validate = true;
}

if(fileDescription == ""){
	errorMessage+="File description is required";
	validate = true;
}
if(bvalue){
if(scope == 3)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.';
		validate = true;
		}
	}

	if(scope == 4)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.';
		validate = true;
		}
	}
	if(scope == 5)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.<br>';
		validate = true;
		}
		if(mandalVal == 0)
		{
		errorMessage += 'Mandal Name is Required.';
		validate = true;
		}
	}
	if(scope == 6 || scope == 8 || scope == 9)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		var villageVal=document.getElementById('villageDiv').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.<br>';
		validate = true;
		}
		if(mandalVal == 0)
		{
		errorMessage += 'Mandal Name is Required.<br>';
		validate = true;
		}
		if(villageVal == 0)
		{
		errorMessage += 'Villiage Name is Required.';
		validate = true;
		}
	}
	if(scope == 7)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.<br>';
		validate = true;
		}
		if(mandalVal == 0)
		{
		errorMessage += 'Mandal Name is Required.';
		validate = true;
		}
		
	}
}
if(validate == true) {
$('#uploadNewsFileErrorDiv').html('<span style="color:red;">'+errorMessage+'</span>');
validate = false;
return false;
}

document.getElementById("newsDeleteMessage").innerHTML = "";	
 var timeST = new Date().getTime();
 

   var title ="";
   var description ="" ;
   var sourceId =""  ;
   var languegeId ="" ;
   var categoryId ="" ;
   var newsImportanceId ="" ;
   var sourceString ='';
   var languageString = '';

   //CHANGE BY SAMBA START

   var gallaryId = "";
   var keywords = "";
   var fileDate = "";
   var locationScopeId = "0";
   var locationScopeValue = "";
   var visibility = "";
   var fileGallaryId  = 0;
   //CHANGE BY SAMBA END

  if(task == "Update")
  {
   title  = document.getElementById("fileTitle").value;
   description  = document.getElementById("fileDescription").value;

    //CHANGE BY SAMBA START

	visibility = $('input:radio[name=visibility]:checked').val();

	gallaryId = document.getElementById("gallaryId").value;
   keywords = document.getElementById("keywords").value;
   fileDate = document.getElementById("existingFromText").value;
   locationScopeId = document.getElementById("scopeDiv").value;
   fileGallaryId = $('#fileGallaryId').val();

   try
  {
  	 locationScopeValue = document.getElementsByName('locationValue')[0].value; 
  }
   catch(err)
  { 
	locationScopeValue = reqFile.locationVal;
  }

  if(locationScopeValue == null || locationScopeValue == "null")
	  locationScopeValue = 0;



   keywords = removeAllUnwantedCharacters(keywords); 
   

    //CHANGE BY SAMBA END
   
   title = removeAllUnwantedCharacters(title);   
   description = removeAllUnwantedCharacters(description);
  
   for(var i in reqFile.fileVOList)
   {
      var sourceEle  = document.getElementById("sourceEdit"+i);
      sourceId  = sourceEle.options[sourceEle.selectedIndex].value;
      var filesourceId1 = document.getElementById("sourceEditId"+i).value;
	  sourceString = sourceString+''+sourceId+','+filesourceId1+'-';
	  
	  
      var languegeEle = document.getElementById("languageEdit"+i);
      languegeId = languegeEle.options[languegeEle.selectedIndex].value;
	  var filesourceId2 = document.getElementById("languageEditId"+i).value;
	  languageString = languageString+''+languegeId+','+filesourceId2+'-';
	  
	  
   }
   var categoryEle = document.getElementById("categoryEdit");
   categoryId = categoryEle.options[categoryEle.selectedIndex].value;
   
   var newsImportanceEle = document.getElementById("newsimportance");
   newsImportanceId = newsImportanceEle.options[newsImportanceEle.selectedIndex].value;
  }
  if(task == "Delete")
  {
    var r = confirm("Do you want to delete this news article");
      if (r == false)
       return;  
  
  }

  if(locationScopeId == 5 || locationScopeId == 7 || locationScopeId == 6 || locationScopeId == 8){

try{
    locationScopeValue = locationScopeValue.substring(1);
}catch(e){
	locationScopeValue = locationScopeValue;

}

  }
   var jsObj=
	      { 
		  title		       :	title,
		  description	   :	description,
		  task			   :	task,
		  sourceId		   :	sourceId,
		  languegeId	   :	languegeId,
		  categoryId	   :	categoryId,
		  fileId           :    fileId,
		  timeST           :    timeST,
		  newsImportanceId :    newsImportanceId,
		  sourceData       :    sourceString.slice(0, -1),
		  languageData     :    languageString.slice(0, -1),
		  gallaryId        :    gallaryId,
          keywords         :    keywords,
          fileDate         :    fileDate,
          locationScopeId  :    locationScopeId,
          locationScopeValue:   locationScopeValue,
		  visibility        :visibility,
		  flagInd          :flagInd,
		  fileGallaryId:fileGallaryId
     }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "updateDeleteNewsAction.action?"+rparam;	
  callAjax(jsObj,url);
 
 }
</script>
</head>
<body>
<div class="rounded">
<fieldset class="f3">
<body>
<center>
	<div style="margin:15px;color:white;">
		<table border="0" cellpadding="0" cellspacing="0">          
			<tr>
			   <td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
			   <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">News Details</span></div></td>
			   <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
			 </tr>
		</table>
	</div>
</center>

<div id="newsSearch_head">
    <table align="center" style="padding-top:15px;padding-bottom:10px;">
	   <tr>
	      <td><input type="radio" name="dates"  value="today" id="today" onclick=" showDates();"><font color="navy"><b>&nbsp;Today</b></font></input></td>
		  <td style="padding-left:10px;"><input type="radio" checked="true" name="dates" id="thisweek" value="thisweek" onclick="showDates();"><font color="navy"><b>&nbsp;This Week</b></font></input></td>
		  <td style="padding-left:10px;"><input type="radio" name="dates" id="thismonth" value="thismonth" onclick="showDates();"><font color="navy"><b>&nbsp;This Month</b></font></input></td>
		  <td style="padding-left:10px;"><input type="radio" name="dates" id="betweendates" value="betweendates" onclick="showDates();"><font color="navy"><b>&nbsp;Between Dates</b></font></input></td>
	   </tr>
	</table>
	<div id="showDates" style="display:none;">
	<table style="padding-bottom:10px;">
	   <tr>	<td style="padding-left:240px;color:#4B74C6;"><b>From Date</b></td>														
					<td>											
						<input type="text" value=""  READONLY="READONLY" name ="fromDate" id="fromDate" size="15"/>										
																
					</td>										
																			
															
					<td style="padding-left:50px;color:#4B74C6;"><b>To Date</b></td>									
					<td>										
						<input type="text" READONLY="READONLY" name ="toDate" id="toDate" size="15"/>										
	
					</td>														
					
       </tr>					
    </table>
	<div id="DateErrDiv"  align="center" style="color:red">&nbsp; </div>
    </div>
	<div style="padding-top:5px;">
	   <center><b>Select Options To Filter Results</b><input type="button"  class="btn btn-primary" value="Options" onclick="toggleOption();" style="margin-left: 5px;"></input></center>
	</div>
<div id="newsSearch" style="padding-bottom:5px;padding-top:10px;">	   
	   <table align="center">
			<tr>									
					
                    <td style="padding-left:10px;color:#4B74C6;"><b>Source</b></td>
                    <td><select style="width:146px;" id="source"><option value="0">All</option></select></td>	

					<td style="padding-left:10px;color:#4B74C6;"><b>Category</b></td>
                    <td><select style="width:229px;" id="category"><option value="0">All</option></select></td>	
					
					<td style="padding-left:10px;color:#4B74C6;"><b>Language</b></td>
                    <td><select style="width:120px;" id="language"><option value="0">All</option></select></td>	
					
					<td style="padding-left:10px;color:#4B74C6;"><b>Importance</b></td>
                    <td><select style="width:120px;" id="importance"><option value="0">All</option></select></td>	
				</tr>										
														
		</table>		
</div>
<div style="padding-top:15px;">
 <center>
 <a href="javascript:{}" onclick="newsSearch();" ><img src="images/search_button.jpg" /></a>
 <a href="javascript:{}" onclick="clearAll();" style="padding-left:10px;" ><img src="images/clear_all_buttom.jpg" ></a>
 <span id="searchAjaxImgSpan" style="margin-left:15px;display:none;"><img src="images/icons/search.gif"></img width="18px" height="18px;"></div></center>
</span>
</div>
<table  align="center" width="100%">

  <tr><td><div id="showNewsCount"></div></td></tr>
  
  <center><div id="showTypes" style="margin-top: 10px;"></div></center>
  <center><div id="categoryGraphDiv" style="position:inherit;"></div></center>
  <center><div id="sourceGraphDiv" style="position:inherit;"></div></center>
  <center><div id="languageGraphDiv" style="position:inherit;"></div></center>
  <center><div id="newsImportantGraphDiv" style="position:inherit;"></div></center>
  <tr><td>
  <div id="newsDeleteMessage"></div>
  </td></tr>

    <tr><td><div id="flagCountDiv"></div></td></tr>

 <tr> 
   <td>
	<table width="90%" style="padding-top:15px;" cellpadding="0" cellspacing="0">
	<tr>
		<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>
	   <td><div class="widgetHeader"><span class="widgetHeaderSpan"> <div id="newsHeading"></div> </span></div></td>
	   <td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>
	</tr>
    </table>
   </td>
 </tr> 
  
  <tr><td align="center"><div id="showNews" class="yui-skin-sam" style="width:950px;" ></div></td></tr>
  <tr><td>
     <div id="showNewsOuterDiv">
           <div id="showNewsDiv"></div>
     </div>
  </td></tr>
</table>
</fieldset>
</div>



<!--DISPLAY NEWS GALLARIES START-->
 <div id="showContentDiv">
	<div id="showContentDivInnerDiv"></div>
	<div id="showContentDivInnerDiv"></div>
 </div>
	<div id="videoGallaryPopUpDiv"></div>
	<div id="emailAlertDiv"></div>
	<div id="sendMessageDiv">
    <div id="constituencySelectDiv"/>
	</div>
	<div id="logInDiv"></div>
<!--DISPLAY NEWS GALLARIES END-->



<div id="editNewsOuter">
  <div id="editNewsInner"></div>
</div>
<script type="text/javascript">

getNews("byThisWeek","getCount","All","","","","","","","","","");
getNews("byThisWeek","getNews","All","","","","","","","Today","","");
getGraphDetails("byThisWeek","categoryDetailsForGraph","","","categoryGraphDiv","","","","");

//getNews("byTodayDate","getCount","All","","","","","","","","","");
//getNews("byTodayDate","getNews","All","","","","","","","Today","","");
//getGraphDetails("byTodayDate","categoryDetailsForGraph","","","categoryGraphDiv","","","","");
getNews("source","getAllSourceDetails","","","","","","","","","","");
getNews("category","getAllCategoryDetails","","","","","","","","","","");
getNews("language","getAllSourceLanguageDetails","","","","","","","","","","");
getNews("importance","getAllNewsImportanceDetails","","","","","","","","","","");
</script>



<!--DISPLAY NEWS GALLARIES START-->

<script>
function getVideoDetails(contentId)
{
	$.fx.speeds._default = 1000;
	  $("#showContentDiv").dialog({ stack: false,
								height: 'auto',
								width:1000,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 1000,
								minHeight: 650,
								title:'News Gallery',
								overlay: { opacity: 0.5, background: 'black'},
								close: function(event, ui) {
								document.getElementById("showContentDivInnerDiv").innerHTML ='';
							 }
		  
								});
		$("#showContentDiv").dialog();
		getContentDetails(contentId);
}

function getContentDetails(contentId)
{
	//document.getElementById("contentAjaxCallImg").style.display="block";
	var jsObj =
		{   
		    contentId : contentId,
			requestFrom : 'Candidate Page',
			requestPageId :'0',
			isCustomer:'true',
			task:"getSelectedContent"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedContentAndRelatedGalleriesAForCustomer.action?"+rparam;					callAjaxForSpecialPage(jsObj,url); 
}
var showContentResultList;
function callAjaxForSpecialPage(jsObj,url)
{

	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);




		  if(jsObj.task == "getSelectedContent")
			{
				showContentResultList = myResults;
				buildContentDetails();
			}
		}
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{
								//alert( "Failed to load result" + o.status + " " + o.statusText);
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
var commentsObject = new Object();
var result;
function buildContentDetails()
{
//console.log(111);
	//document.getElementById("contentAjaxCallImg").style.display="none";
	result = showContentResultList;

//console.log(result);
	if(result == null)
		return;

	var divEle = document.getElementById('showContentDivInnerDiv');
	var str = '';
	var titleStr = null;
	var pathStr = null;
	var descriptionStr = null;
	var preContentId = null;
	var curPos = null;
	var totSize = null;
	var commentText="" ;
    var fileId1  = null;
	
	//document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>${specialPageVO.heading} - '+result.contentType;

	//document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>'+result.contentType;

	str += '<Div><center>';
	str += '<div class="main-title-sec" style="clear:both;">';
	str += '<div id="showContentHeaderDiv" class="main-mbg" style="width:900px;border-radius:0px 0px 0px 0px;"></div><div class="main-bbg"/></div>';

	
	for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{

		
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		curPos = i+1;
		totSize = result.relatedGalleries[0].filesList.length;



		if(result.contentType == 'Video Gallary' || result.contentType == 'News Gallary')
		{


			str+='<table style="display: -moz-inline-box;">';
			str+='<tr>';
			str+='<td>';

			

			if(result.relatedGalleries[0].filesList[i].categoryType != null)
				str+='<B>CategoryType</B>:<font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].categoryType+'</span></font>';
			 str+='</td>';

			str+='<td>';
			if(result.relatedGalleries[0].filesList[i].locationScopeValue != null)
				str+='<B>Region Scope</B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].locationScopeValue+'</font>';
			 str+='</td>';

			 str+='<td>';
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].source != null)
				str+='<B>Source</B>:<font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].fileVOList[0].source+'</span></font>';
			 str+='</td>';

			 str+='<td>';
			if(result.relatedGalleries[0].filesList[i].fileDate != null)
				str+='<B>Date</B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].fileDate+'</font>';
			 str+='</td>';
			
			 str+='<td>';
           
				//str+='<a href="javaScript:{}"><i class="icon-tag"></i></a>';
				//str+='<div  style="display:none;" id="flagDiv'+result.relatedGalleries[0].filesList[i].contentId+'"><a class="btn btn-mini btn-primary" href="javaScript:{saveFlag('+result.relatedGalleries[0].filesList[i].contentId+')}">This news flagged</a><i class="icon-tag"></i></div>';

				str+='<div  style="display:none;border:1px solid red;" id="flagDiv'+result.relatedGalleries[0].filesList[i].contentId+'"><a  href="javaScript:{removeFlag('+result.relatedGalleries[0].filesList[i].contentId+');}" title="unflag this news"><i class="icon-flag"></i></a></div>';
			
				//str+='<div  style="display:none;" id="unFlagDiv'+result.relatedGalleries[0].filesList[i].contentId+'"><a class="btn btn-mini" href="javaScript:{saveFlag('+result.relatedGalleries[0].filesList[i].contentId+')}">Flag this news</a></div>';

				str+='<div  style="display:none;border:1px solid red;" id="unFlagDiv'+result.relatedGalleries[0].filesList[i].contentId+'"><a class="unflagClass" href="javaScript:{saveFlag('+result.relatedGalleries[0].filesList[i].contentId+')}" title="flag this news"><i class="icon-flag"></i></a></div>';
				

			 str+='</td>';

             str+='<td>';

  		     str+='<div id="public'+result.relatedGalleries[0].filesList[i].contentId+'" style="display:none;border:1px solid red;"><a  title="make this news as private" href="javaScript:{updateVisibilityToPrivate1('+result.relatedGalleries[0].filesList[i].contentId+')}"><i class="icon-bullhorn"></i></a></div>';

 			 str+='<div  style="display:none;border:1px solid red;" id="private'+result.relatedGalleries[0].filesList[i].contentId+'"><a  title="make this news as public"  href="javaScript:{updateVisibilityToPublic1('+result.relatedGalleries[0].filesList[i].contentId+');}"><i class="icon-lock"></i></a></div>';

             str+='</td>';


			 str+='</tr>';
			 str+='</table>';

		}
	}

	

	if(result.contentType == 'Video Gallary')
	{
		if(result.relatedGalleries[0].filesList.length < 2)
			str += '<table width="530px">';
		else
			str += '<table width="880px">';
		str += '<tr>';

		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="popupcontainer" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';
		
		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 0))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
		str += '<td valign="top" style="horizontal-align:center;">';
		str += '<div class="popupcontainer" id="nextPartImage" style="width:500px;text-align:center;">';
		str += '<iframe width="500" height="396" src="http://www.youtube.com/embed/'+pathStr+'" frameborder="0" allowfullscreen="true"></iframe></div>';
		str += '<table><tr>';
		str += '<td>';
		str += ''+descriptionStr+'';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		


		
		str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';

	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[0].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
		   
		   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildVideoNewSources">';
	             str += '<center><table><tr><td><b>Same Video in another sources</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'video\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		   
		
		str += '</div>';
		str += '</td>';
		
		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="popupcontainer" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 1))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
	str += '</tr>';
	str += '</table>';
	}

	else if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
	{

		str += '<table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{

			var contentIdForNotes =  result.relatedGalleries[0].filesList[i].contentId;
			descriptionStr = result.relatedGalleries[0].filesList[i].description;

	        getNotes(contentIdForNotes);
			getFlagStatus(contentIdForNotes);
			getVisibilityStatus(contentIdForNotes);

			
			 if(result.relatedGalleries[0].filesList.length == 1){
					
			  }else{
			    if(i > 0)
				{


					str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
				}else{
				   str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[result.relatedGalleries[0].filesList.length-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[result.relatedGalleries[0].filesList.length-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
				}
			  }
			str += '<td><div class="popupcontainer" id="nextPartImage" style="width:780px;text-align:center;"><img alt="'+result.relatedGalleries[0].filesList[i].title+'" title="'+result.relatedGalleries[0].filesList[i].description+'" align="middle" style="max-width:780px;max-length:800px;" src="'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'" /></div></td>';



          // commentsObject[result.relatedGalleries[0].filesList[i].fileId] = result.relatedGalleries[0].filesList[i].comments;


		   fileId1 = result.relatedGalleries[0].filesList[i].fileId;

		   var id = result.relatedGalleries[0].filesList[i].fileId;

			 if(commentsObject[id] == undefined){

				 commentText = result.relatedGalleries[0].filesList[i].comments;

				 commentsObject[result.relatedGalleries[0].filesList[i].fileId] = result.relatedGalleries[0].filesList[i].comments

			 }else{
               commentText = commentsObject[result.relatedGalleries[0].filesList[i].fileId];
			 }
			
			if(result.relatedGalleries[0].filesList.length == 1){
				
			  }else{
				if(i != result.relatedGalleries[0].filesList.length-1)
				{
					str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i+1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i+1].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
				}else{
				   str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[0].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[0].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
				}
			  }

		}

		str += '</table>';

		
		str += '<div>';
		str += '<table>';
		str += '<tr><td>Description : <b>'+descriptionStr+'</b></td></tr>';
		str += '</table>';
		str += '</div>';

		str+='<div id="contentNotesDiv'+contentIdForNotes+'"></div>';
			
/*
		//FOR COMMNET START


		if(commentText != null && commentText !=""){
		str+='<div class="popupcontainer" style="width:800px;margin:4px;text-align:left;" id="commentDefault'+fileId1+'"><b>Notes :</b>'+commentText;
			str+='<a hrfe="#" class="btn btn-mini btn-info" style="margin-left:8px;" onClick="editComment('+fileId1+');"  title="click here to edit notes">Edit</a></div>';
            str+='<div class="commentClass'+fileId1+'" style="width: 700px;height:104px;display:none;margin:11px;"><b style="float:left;">Enter Notes:</b><textarea style="width:650px;height:50px;margin-right:49px;" id="commentId'+fileId1+'">'+commentText+'</textarea>';

			str+='<input type="button" style="margin:3px 8px 0px 410px" class="btn btn-mini" value="Save" onClick="saveFileComment('+fileId1+');"/>';

			str+='<input type="button" class="btn btn-mini" value="Cancel" onClick="cancelPostComment('+fileId1+');"/></div>';
		}
		else{
		str+='<div style="width:700px;height:104px;margin:11px;"><b style="float:left;">Enter Notes:</b><textarea style="width:650px;height:50px;margin-right:49px;" id="commentId'+fileId1+'"></textarea>';

	     str+='<input type="button" style="float:right;margin:3px 49px;" class="btn btn-mini" value="Save" onClick="saveFileComment('+fileId1+');"/></div>';
		}

   		   //FOR COMMNET END


*/

     // str+='<div id="contentNotesDiv'+result.relatedGalleries[0].filesList[i].contentId+'"></div>';

		
	   for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		  
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{
		   selectedContentFile = result.relatedGalleries[0].filesList[i];
		   str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';

	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
		   
		   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildNewSources">';
	             str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		}

		
	}

	

	//str+='<div><b>Enter Comment:</b><textarea style="width:650px;height:50px;" id="commentId'+fileId+'"></textarea></div>';

	//str+='<input type="button" style="float:right;margin:3px 133px;" class="btn" value="Save" onClick="saveFileComment('+fileId+');"/>';


	if(result.otherGalleries != null && result.otherGalleries.length > 0)
	{
		var galType = null;
		
		if(result.contentType == 'Photo Gallary')
			galType = ' Photo ';
		else if(result.contentType == 'News Gallary')
			galType = ' News ';
		else if(result.contentType == 'Video Gallary')
			galType = ' Video ';

		str += '<div>';

		str += '<Div><center>';
		str += '<div class="main-title-sec" style="clear:left;">';
		str += '<div class="main-mbg" style="width:900px;border-radius:0px 0px 0px 0px;">Other '+galType+' galleries</div><div class="main-bbg"/></div>';
		
		str += '<div class="popupcontainer" style="overflow:auto;width:880px;max-width:850px;">';
		str += '<Table>';
		
		for(var i=0;i<result.otherGalleries.length;i++)
		{
			if(i%5 == 0)
				str += '<tr>';
			
			str += '<td width="20%" valign="top">';

			str += '<table>';
			str += '<tr><td class="videoGalTitleStyle"><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+' Gallery"><font color="red">'+result.otherGalleries[i].gallaryName+'</font></a></td></tr>';
			str += '<tr><td><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+' Gallery">';
			
			if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
				str += '<img width="120px" height="90px" alt="'+result.otherGalleries[i].gallaryName+'" src="'+result.otherGalleries[i].filesList[0].path+'"></img>';
				
			else if(result.contentType == 'Video Gallary')
				str += '<img src="http://img.youtube.com/vi/'+result.otherGalleries[i].filesList[0].path+'/1.jpg"></img>';
			
			str += '</a></td></tr>';
			str += '<tr><td class="videoGallDescStyle">Gallery Size : ('+result.otherGalleries[i].orderNo+')</td></tr>';
			str += '<tr><td class="videoGallDescStyle">'+result.otherGalleries[i].description+'</td></tr>';
			str += '</table>';

			str += '</td>';

			if(i%5 == 4)
				str += '</tr>';
		}
		str += '</Table>';
		str += '</div>';

		str += '</div>';
	}
	
	str += '</center></Div>';

	divEle.innerHTML = str;

	var str = '';
	str += ''+titleStr+' ('+curPos+' of '+totSize+')<span style="margin-top:10px;margin-right:18px;float:right">';
	//str +='<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/specialPageAction.action?specialPageId=${specialPageId}&contentId='+preContentId+'\')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
	str += '</span>';
	
	document.getElementById("showContentHeaderDiv").innerHTML=str;
	
}

function editComment(fileId){

	$('.commentClass'+fileId).css('display','block');
	$('#commentDefault'+fileId).css('display','none');
}


function cancelPostComment(fileId){

	$('.commentClass'+fileId).css('display','none');
	$('#commentDefault'+fileId).css('display','block');

}

function saveFileComment(fileId){


	 var comment = $('#commentId'+fileId).val();

	 commentsObject[fileId] = comment;

	  var jsObj =
		{ 
		    comment:comment,
            fileId : fileId,
			task:"saveFileComment"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveFileCommentAction.action?"+rparam;						
	callnewAjax(jsObj,url);

 }

function buildContentDetailsOfSelected(preId,selId)
{
	for(var i=0;i<showContentResultList.relatedGalleries[0].filesList.length;i++)
	{
		if(showContentResultList.relatedGalleries[0].filesList[i].contentId == preId)
			showContentResultList.relatedGalleries[0].filesList[i].isSelectedContent = false;
		if(showContentResultList.relatedGalleries[0].filesList[i].contentId == selId)
			showContentResultList.relatedGalleries[0].filesList[i].isSelectedContent = true;
	}

	buildContentDetails();
}

function showNextNewsPart(fileSourceLanguageId,orderNo,path,type)
{
  for(var i in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[i].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(type != 'video')
	    var str='<img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" align="middle" style="max-width:780px;max-length:800px;" src="'+path+'" />';
	  else
	   var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=0;j<selectedContentFile.fileVOList[i].fileVOList.length;j++)
	     {
		   if(selectedContentFile.fileVOList[i].fileVOList[j].orderNo != orderNo)
		    {
			  if(type != 'video')
	             str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'other\')"><img width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" src="'+selectedContentFile.fileVOList[i].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
	          else
			     str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[i].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
		    }
		 }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
  
  }

}

function showNewAnotherSource(fileSourceLanguageId,type)
{
     var str1 ='';
	   if(type != 'video')
	     str1 += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	   else 
         str1 += '<center><table><tr><td><b>Same Video in another sources</b></td></tr></table></center>';	   
		 str1 += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
  for(var m in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[m].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(document.getElementById("sourceChangeSpan") != null)
	    document.getElementById("sourceChangeSpan").innerHTML = ''+selectedContentFile.fileVOList[m].source+'';
	  if(type != 'video')
	    var str='<img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" align="middle"  style="max-width:780px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" />';
	  else
	    var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" frameborder="0" allowfullscreen="true"></iframe>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=1;j<selectedContentFile.fileVOList[m].fileVOList.length;j++)
	     {
		    if(type != 'video')
	         str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[m].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
			else 
			 str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[m].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
	     }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
    else
	{
	   if(type != 'video')
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';	             	          	
	   else
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'video\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';
	}
  }
     	str1 += '  </tr></table>';
	    str1 +='</center>';
     if(document.getElementById("buildNewSources") != null)
       document.getElementById("buildNewSources").innerHTML = str1;
	 else
	   document.getElementById("buildVideoNewSources").innerHTML = str1;
}
</script>

<!--DISPLAY NEWS GALLARIES END-->

<script>

</script>
</body>
</html>