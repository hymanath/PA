<%@ page language="java" contentType="text/html; charset=utf-8" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <META http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> TDP News Portal </title>

<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>

<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />

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

@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}

.currentTab
{
 background-color:yellowgreen;
 border-radius:8px;
}

#existingFromText,#existingToText{width:155px; cursor: text;}
#errorMsgDiv{font-size:12px;}
.fontStyle{font-size:21px;}
</style>

<script type="text/javascript">
var candidateId = '${candidateId}';
var locationScope = '${locationScope}';
var fromDate = '${fromDate}';
var toDate = '${toDate}';
var categoryIdsList = '${categoryIds}';
var galleryIdsList = '${galleryIds}';

</script>


</head>
<body>
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
								<!-- <li>
									<h6>News Main Title</h6>
									<p>
About Gmail - email from Google

Video chat with a friend, or give someone a ring all from your inbox. See more reasons to switch or check out our newest features. <p>
								</li> -->
								<div id="errorMsgDiv"></div>
		       <p> From Date: <input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="20"/></p>	
			   <p>To Date: <input type="text" id="existingToText" class="dateField" readonly="true" name="fileDate" size="20"/></p>
			<input type="button" value="Get News" id="selectedNewsDetBtn" onclick="getSelectedNewsDetails()" class="btn btn-info"/>
											
							</ul>
						</div>
					</div>
				</div>
				<!-----All News DIv End ------>
			</div>
		<!--------- Row-1 End -------->
		
	</div>

<script type="text/javascript">


$(document).ready(function(){

	$('#newsTabId').addClass('currentTab');
});
</script>

<script type="text/javascript">

getNewsForPagination(0);
function getNewsForPagination(startIndex)
 {
	
	  var jsObj={
		candidateId:candidateId,
		fromDate:fromDate,
		toDate:toDate,
		locationScope:locationScope,
		firstResult:startIndex,
	    maxResult:10,
		categoryIdsList:categoryIdsList,
        galleryIdsList:galleryIdsList,
	    task:'getLocationWiseNewsDetailsForACandidate'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocationWiseNewsDetailsForACandidateAction.action?"+rparam;
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
			if(jsObj.task == "getLocationWiseNewsDetailsForACandidate")
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
		var source = results[i].fileVOList[0].source.trim();
		if(source == "Eenadu Telugu")
		{
			str+="<a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'><span class='enadu fontStyle' style='font-weight:bold;'>"+results[i].title+"</span></a>";
		}
		else
		{
			str+="<h4 style='text-transform: capitalize'><a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h4>";
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

		str+="<div class='row-fluid m_top10'><div class='span9'>";
		str +='<table><tr><td style="vertical-align: top;">';
		str +='<p style="width: 140px; margin-right: 6px;"><span class="text-error" style="font-weight:bold;">Source : </span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td>';
		str +='<td style="vertical-align: top;"><p style="width: 110px;"><span  class="text-error" style="font-weight:bold;">Date : </span> '+results[i].fileDate+'</p></td>';
		str +='<td style="vertical-align: top;"><p style="width: 122px;"><span class="text-error" style="font-weight:bold;">Response Count: </span>'+results[i].responseCount+'</p></td>';
		str +='<td style="vertical-align: top;"><p style="width: 150px;"><span class="text-error" style="font-weight:bold;">Location :</span> '+results[i].locationName+'</p></td>';
		str +='</tr>';
		
		str +='</table>';
		str +='</div>';
		
		str+="<div style='clear:both;' class='span4 pull-right'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' style='font-weight: bold; font-size: 13px; margin-right: 20px;' class='btn btn-mini btn-info pull-right' type='button'>More...</a>";
		if(results[i].responseCount > 0)
		{
		  str +="<a style='font-size: 13px; margin-left: 28px; font-weight: bold;' type='button' class='btn btn-mini btn-info' href='javascript:{}' onclick='getNewsTrackDetails("+results[i].contentId+")'>Track</a>";
		}

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
			cssStyle: 'light-theme'
		});
	}
}
function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}

function getSelectedNewsDetails()
{
	$("#errorMsgDiv").html('');
	
	var fromDate = $("#existingFromText").val();
	var toDate = $("#existingToText").val();
	if(fromDate=="" && toDate == "")
	{
		$("#errorMsgDiv").html('Please Select From And To Dates');
		return;
	}
	else if(fromDate =="")
	{
	  $("#errorMsgDiv").html('Please Select From Date');
		return;
	}
	else if(toDate =="")
	{
	  $("#errorMsgDiv").html('Please Select To Date');
		return;
	}
	else if (Date.parse(fromDate) > Date.parse(toDate)) {
      $("#errorMsgDiv").html('Invalid Date Selection.');
      return;
	} 

	
	var urlstr = "selectedNewsDetailsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&";
	
    var browser1 = window.open(urlstr,"newsDetails"+fromDate+"And"+toDate+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
}

function getNewsTrackDetails(contentId)
{
   
  var urlstr = "showNewsResponseAction.action?responseContentId ="+contentId+"&";
		
     var browser1 = window.open(urlstr,"newsTrackDetails"+contentId+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();

}


$(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
});
</script>

</body>
</html>