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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<title></title>
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
	position:relative; padding:10px; margin:10px 0;background-color:#FFFFFF;
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
    width: 992px;
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
  height:240px;
  width:144px;
}
#toDate_Div{
  height:240px;
  width:144px;
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
</style>
<script type="text/javascript">

var ajaxCount = 0;
var newsDetails = null;

$(document).ready(function(){
  $("#newsSearch").slideUp("fast");
});
function getNews(task,queryType,fileType,sourceId,languegeId,categoryId,newsImportanceId,locationScope,location,title,fromDate,toDate){
    
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
str+='<select style="width:90px;" id="graphType" onchange="getOtherGraphs(\''+obj.task+'\',\''+obj.fromDate+'\',\''+obj.toDate+'\');showAjaxImg(\'ajaxImgSpan\');"><option value="1">Category</option>';
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
				bildDate(myResults,"source");
			 }
			 else if(jsObj.queryType == "getAllCategoryDetails")
			 {	
				bildDate(myResults,"category");
			 }
			 else if(jsObj.queryType == "getAllSourceLanguageDetails")
			 {	
				bildDate(myResults,"language");
			 }
			 else if(jsObj.queryType == "getAllNewsImportanceDetails")
			 {	
				bildDate(myResults,"importance");
			 }
			}catch (e) {   		
		   	alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			//alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function bildDate(optionsList,elmtId){
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
	 
	document.getElementById("newsHeading").innerHTML=jsObj.title+" News";

  document.getElementById("showNews").innerHTML='';
  YAHOO.widget.DataTable.news = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var title = oRecord.getData("fileTitle1");
	var fileId = oRecord.getData("fileId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='showNews(\""+fileId+"\")'>"+title+"</a>";
  };
  var newsResultColumnDefs = [ 		    	             
		    	            
							{key:"categoryType", label: "NEWS CATEGORY", sortable: true},
		    	           	{key:"source", label: "SOURCE", sortable: true},
							{key:"fileTitle1", label: "TITLE",formatter:YAHOO.widget.DataTable.news, sortable: true},
							{key:"description", label: "DESCRIPTIONS", sortable: true},
		    				{key:"locationScopeValue", label: "IMPACT AREA",sortable:true},
							{key:"locationValue", label: "AREA NAME", sortable: true},
							{key:"fileDate", label: "NEWS DATE", sortable: true}
							
		    	        ]; 
	var newsResultDataSource = new YAHOO.util.DataSource(result); 
	


    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    }) 
				};
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "categoryType","source","fileTitle1","description","locationScopeValue","locationValue","fileDate","fileId"]
					};

		var newsResultDataSource = new YAHOO.widget.DataTable("showNews", newsResultColumnDefs,myDataSource, myConfigs);
}

function showNews(fileId)
{	
	 $.fx.speeds._default = 1000;
	  $("#showNewsOuterDiv").dialog({ stack: false,
								height: 'auto',
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
		 str += '        <B>Source</B> : <font color="#FF4500">'+newsDetails[i].source+'</font> &nbsp;&nbsp;&nbsp;<B> Date </B>:<font color="#FF4500"> '+newsDetails[i].fileDate+'</font>';
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
		 str += '<td><div class="container"><img alt="'+newsDetails[i].fileTitle1+'" src="'+newsDetails[i].path+'" title="'+newsDetails[i].description+'" style="max-width:820px;max-length:800px;"/></div></td>';
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
		 str += '<table>';	 
		 str += '</center></div>';	 
		 document.getElementById("showNewsDiv").innerHTML= str;
		}
	}
 }

 function showDates(){
    
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
  var title = 'Between Dates '+fromDate+' and '+toDate;
   getNews("betweendates","getCount","All",source,language,category,importance,"","","",fromDate,toDate);
   getGraphDetails("betweendates","categoryDetailsForGraph",fromDate,toDate,"categoryGraphDiv",source,language,category,importance);
   getNews("betweendates","getNews","All",source,language,category,importance,"","",title,fromDate,toDate);
   
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

</script>
</head>
<body>
<div style="background-color:width:900px;" class="rounded">
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
	      <td><input type="radio" name="dates" checked="true" value="today" id="today" onclick=" showDates();"><font color="navy"><b>&nbsp;Today</b></font></input></td>
		  <td style="padding-left:10px;"><input type="radio" name="dates" id="thisweek" value="thisweek" onclick="showDates();"><font color="navy"><b>&nbsp;This Week</b></font></input></td>
		  <td style="padding-left:10px;"><input type="radio" name="dates" id="thismonth" value="thismonth" onclick="showDates();"><font color="navy"><b>&nbsp;This Month</b></font></input></td>
		  <td style="padding-left:10px;"><input type="radio" name="dates" id="betweendates" value="betweendates" onclick="showDates();"><font color="navy"><b>&nbsp;Between Dates</b></font></input></td>
	   </tr>
	</table>
	<div id="showDates" style="display:none;">
	<table style="padding-bottom:10px;">
	   <tr>	<td style="padding-left:240px;color:#4B74C6;"><b>From Date</b></td>														
					<td>											
						<input type="text" value=""  READONLY="READONLY" name ="fromDate" id="fromDate" size="15"/>										
						<div class="yui-skin-sam"><div id="fromDate_Div" class="tinyDateCal"></div></div>										
					</td>										
					<td valign="top">										
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('fromDate_Div','fromDate','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
					</td>														
															
					<td style="padding-left:50px;color:#4B74C6;"><b>To Date</b></td>									
					<td>										
						<input type="text" READONLY="READONLY" name ="toDate" id="toDate" size="15"/>										
						<div class="yui-skin-sam"><div id="toDate_Div" class="tinyDateCal"></div></div>										
					</td>														
					<td valign="top">										
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('toDate_Div','toDate','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
					</td>
       </tr>					
    </table>
    </div>
	<div style="padding-top:5px;">
	   <center><b>Select Options To Filter Results</b><input type="button" value="Options" onclick="toggleOption();"></input></center>
	</div>
<div id="newsSearch" style="padding-bottom:5px;padding-top:10px;">	   
	   <table align="center">
			<tr>									
					
                    <td style="padding-left:10px;color:#4B74C6;"><b>Source</b></td>
                    <td><select style="width:90px;" id="source"><option value="0">All</option></select></td>	

					<td style="padding-left:10px;color:#4B74C6;"><b>Category</b></td>
                    <td><select style="width:90px;" id="category"><option value="0">All</option></select></td>	
					
					<td style="padding-left:10px;color:#4B74C6;"><b>Language</b></td>
                    <td><select style="width:90px;" id="language"><option value="0">All</option></select></td>	
					
					<td style="padding-left:10px;color:#4B74C6;"><b>Importance</b></td>
                    <td><select style="width:90px;" id="importance"><option value="0">All</option></select></td>	
				</tr>										
														
		</table>		
</div>
<div style="padding-top:15px;">
 <center>
 <a href="javascript:{}" onclick="newsSearch();showAjaxImg('searchAjaxImgSpan')" ><img src="images/search_button.jpg" /></a>
 <a href="javascript:{}" onclick="clearAll();" style="padding-left:10px;" ><img src="images/clear_all_buttom.jpg" ></a>
 <span id="searchAjaxImgSpan" style="margin-left:15px;display:none;"><img src="images/icons/search.gif"></img width="18px" height="18px;"></div></center>
</span>
</div>
<table  align="center" width="100%">

  <tr><td><div id="showNewsCount"></div></td></tr>
  
  <center><div id="showTypes"></div></center>
  <center><div id="categoryGraphDiv" style="position:inherit;"></div></center>
  <center><div id="sourceGraphDiv" style="position:inherit;"></div></center>
  <center><div id="languageGraphDiv" style="position:inherit;"></div></center>
  <center><div id="newsImportantGraphDiv" style="position:inherit;"></div></center>
  
 <tr> 
   <td>
	<table width="100%" style="padding-top:15px;" cellpadding="0" cellspacing="0">
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

<script type="text/javascript">
getNews("byTodayDate","getCount","All","","","","","","","","","");
getNews("byTodayDate","getNews","All","","","","","","","Today","","");
getGraphDetails("byTodayDate","categoryDetailsForGraph","","","categoryGraphDiv","","","","");
getNews("","getAllSourceDetails","","","","","","","","","","");
getNews("","getAllCategoryDetails","","","","","","","","","","");
getNews("","getAllSourceLanguageDetails","","","","","","","","","","");
getNews("","getAllNewsImportanceDetails","","","","","","","","","","");
</script>
</body>
</html>