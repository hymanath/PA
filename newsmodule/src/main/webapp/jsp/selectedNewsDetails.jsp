<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> TDP News Portal </title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script> 
	 
	<!-- YUI Skin Sam -->
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>


<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>

<style type="text/css">
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
 .enadu
{
font-family: eFont;
font-size:20px;
}
#newDisplayMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 950px;margin-bottom: 30px;
    margin-top: 26px;}
	#newsDisplayDiv ul li {
    margin-left: 20px;
}

#headerDiv {
     background-color: #06ABEA;
    border-radius: 5px 5px 5px 5px;
    color: #FFFFFF;
    float: none;
    font: bold 17px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    margin-left: auto;
    margin-right: auto;
    padding-left: 13px;
    text-align: center;
    width: 650px;
}
#newDisplayInnerDiv{ border: 1px solid #CDCDCD;
    border-radius: 3px 3px 3px 3px;
    margin-top: 14px;
    padding-bottom: 10px;
    padding-top: 10px;display:table;}
</style>
<script type="text/javascript">
var fromDate = "${fromDate}";
var toDate = "${toDate}";
</script>
</head>
<body>
<body>
<div id="newDisplayMainDiv">
  <div id="headerDiv">News Details From ${fromDate} To ${toDate}</div>

 <div id="newDisplayInnerDiv">
   <div id="newsDisplayDiv"></div>
	<!----pagination Div----->
	 <div class="span12 text-center">
			<div id="paginationId"></div>
	 </div>
  </div>
 </div>

 <script type="text/javascript">

 function getNewsForPagination(startIndex)
{
	
  var jObj=
	{
	  fromDate:fromDate,
	  toDate:toDate,
	  firstResult:startIndex,
	  maxResult:10,
	  task:"getSelectedNewsBetweenDates"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getSelectedNewsBetweenDatesAction.action?"+rparam;
	callAjax(jObj,url);
}

function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);

		    if(jsObj.task == "getSelectedNewsBetweenDates")
			{
				buildPaginatedNews(myResults,jsObj);
			
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

function buildPaginatedNews(results,jsObj)
{
	$("#newsDisplayDiv").html('');
	if(results == null || results.length == 0)
	{
		$("#newsDisplayDiv").html('<p style="margin-left: 25px;">No Data Found.</p>');
		return;
	}
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		var source = results[i].fileVOList[0].source.trim();
		if(source == "Eenadu Telugu")
		{
			str+="<h4 class='enadu' style='color:#0088CC;'>"+results[i].title+"</h4>";
		}
		else
		{
			str+="<h4 style='text-transform: capitalize;color:#0088CC;'>"+results[i].title+"</h4>";
		}
		
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>";
		
		var path = results[i].fileVOList[0].fileVOList[0].path;
		var source = results[i].fileVOList[0].source;
		
		str+="<img id='myImg' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
		if(source == "Eenadu Telugu")
		{
			str+="<p class='span8 enadu'>"+results[i].description+"</p>";
		}
		else
		{
			str+="<p class='span8'>"+results[i].description+"</p>";
		}
		
		str+="</div>";

		str+="<div class='row-fluid m_top10'><div class='span9'>";
		str +='<table><tr><td>';
		str +='<p style="margin-right: 12px; width: 260px;"><span class="text-error">Source :</span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p><span class="text-error">Date :</span> '+results[i].fileDate+'</p></td></tr>';
		
		str +='</table>';
		str +='</div>';
		
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>More...</a></div></li>";
		var len = results.length;
		
		if(len-1 != i)
		 str +='<hr>';
	}
	
	var itemsCount=results[0].count;
	
	var maxResults=jsObj.maxResult;
	str+="</ul>";

	$("#newsDisplayDiv").html(str);
	
	if(jsObj.firstResult==0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme'
		});
	}
}
function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}

function getNewsDetailsByContentId(contentId)
{
  var urlstr = "newsDetailsPopupAction.action?contentId="+contentId+"&";
	
    var browser1 = window.open(urlstr,"gallaryDetails"+contentId+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
}
 getNewsForPagination(0);
 </script>
</body>
</html>