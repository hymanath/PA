<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
   
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> 
  
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 


<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />



</head>
<body>
<style>
	.unstyled li{
	float:left;width:20%;border:1px solid #ccc;padding:5px;text-align:center;margin:4px;
	}
</style>
<div id="gallarysId" style="margin:22px 0px 31px 50px;float:left;"></div>
<div id="paginationId" style="float:left;margin-left:280px;"></div>
<script>
getNewsForPagination(1);

function getNewsForPagination(num){
var startRecord=1;
	if(num==1){
		startRecord=1;
	}
	else{
		startRecord=num*2
	}

	var jsObj =
		{  	
			partyId:872,
			startRecord:startRecord,
			maxRecord:20,
			queryType:'public',
			task:'getMoreVideos'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getMoreVideosAction.action?"+rparam;
		callAjax(jsObj, url);
		
}

function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="getMoreVideos")
								{
									buildMoreVideos(myResults,jsObj);
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
	
	function buildMoreVideos(results,jsObj){

	var str="";
	str+="<ul class='unstyled' id='videoGalUlId'>";
	for(var i in results){
		str+="<li>";
		str+="<h6 id='titleNameId' title='"+results[i].name+"' style='cursor:default;'>"+results[i].name+"</h6>";
		str+="<div>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}' onclick='getVideosByContentId("+results[i].ids+")'>";
		str+="<img id='myImg' style='width:100%' src=http://img.youtube.com/vi/"+results[i].path+"/1.jpg ></a>";
		//str+="<p class='span8'>"+results[i].description+"</p>";
		str+="</div>";
		//str+="<div class='row-fluid m_top10'><div class='span9'><p class='text-error'>Source : "+results[i].source+"</p></div>";
		//str+="<div class='span2'><a href='#'></div></li>";
	}
	
	//var itemsCount=results[0].count;
	
	str+="</ul>";
	$("#gallarysId").html(str);
	
	var maxResults=jsObj.maxRecord;
	var itemsCount=results[0].count;
	
	
	if(jsObj.startRecord==1){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme'
		});
	}
	
	$('#videoGalUlId #titleNameId').each(function(index,value) {
		ellipsetext=".."
	 	var showChar = 28;
        var content = $(this).html();
        if(content.length > showChar) {
 
            var c = content.substr(0, showChar);
            var h = content.substr(showChar-1, content.length - showChar);
			var html = c + ellipsetext;
            $(this).html(html);
        }
 
    });
	
}
function getVideosByContentId(galId){

}
</script>

</body>