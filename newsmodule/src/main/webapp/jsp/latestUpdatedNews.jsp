<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst</title>


<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>

<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid headerBg" style="padding-left: 0px; padding-right: 0px;">
		<!---Header----->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<div class="span2">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>News</h4></div>
					</div>
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading" style="text-transform: capitalize;"><h4>News Details </h4></div>
							<div id="latestNewsDiv"></div>
						<!----pagination Div----->
						<div class="span12 text-center">
							<div id="paginationId"></div>
						</div>
						<!-----pagination Div end---->
					</div>
				</div>
				<!-----View your Constituency News End------>
				<!-----All News DIv------>
				<div class="span3" style="height:554px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>All News</h4></div>
						<div class="span12">
							<ul class=" nav nav-list bs-docs-sidenav">
								<li>
									<h6>News Main Title</h6>
									<p>
About Gmail - email from Google

Video chat with a friend, or give someone a ring all from your inbox. See more reasons to switch or check out our newest features. <p>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-----All News DIv End ------>
			</div>
		<!--------- Row-1 End -------->
		
	</div>



<script type="text/javascript">

getNewsForPagination(1);
function getNewsForPagination(startIndex)
 {
	
var jObj=
	{
		
	  firstResult:startIndex,
	  maxResult:10,
	  task:"getLatestNews"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "latestUpdatedNewsAction.action?"+rparam;
	callAjax(jObj,url);
}

function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);					
			if(jsObj.task == "getLatestNews")
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

function buildPaginatedNews(results,jsObj)
{
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		str+="<h4><a href='#'>"+results[i].title+"</a></h4>";
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}'>";
		
		var path = results[i].fileVOList[0].fileVOList[0].path;
		var source = results[i].fileVOList[0].source;

		str+="<img id='myImg' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
		str+="<p class='span8'>"+results[i].description+"</p>";
		str+="</div>";

		str+="<div class='row-fluid m_top10'><div class='span9'>";
		str +='<table><tr><td>';
		str +='<p class="text-error">Source :';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><tr><td><p class="text-error">Date : '+results[i].fileDate+'</p></td></tr>';
		
		str +='</table>';
		str +='</div>';
		
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>More...</a></div></li>";
	}
	
	var itemsCount=results[0].count;
	
	var maxResults=jsObj.maxResult;
	str+="</ul>";

	$("#latestNewsDiv").html(str);
	
	if(jsObj.firstResult==1){
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
  
</script>
</body>
</html>