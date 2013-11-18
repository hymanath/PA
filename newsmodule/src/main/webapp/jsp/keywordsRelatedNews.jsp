<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> TDP News Portal </title>


<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityDateOperations.js"> </script>
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />

<script>
  var keyword = '${keyword}';
</script>

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

.currentTab1
{
 background-color:yellowgreen;
 border-radius:8px;
}

#existingFromText,#existingToText{width:155px; cursor: text;}
#errorMsgDiv{font-size:12px;}
.fontStyle{color:#000;font-size:21px;}
#responseNewsCountImg{height: 30px; width: 40px; margin-right: 3px;cursor: pointer;}
</style>
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
						<div id="keywordNewImg" style="display:none;text-align: center;"><img src="images/icons/goldAjaxLoad.gif"  style="margin-top: 15px;"/></div>
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

function getKeywordRelatedNews(startIndex)
{
  
  $("#keywordNewImg").css("display","block");
  var jsObj={
	 keyword:keyword,
	 startIndex:startIndex,
	 maxIndex:10,
	 task:'getKeyWordNews'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeyWordNewsAction.action?"+rparam;
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
			if(jsObj.task == "getKeyWordNews")
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
	$("#keywordNewImg").css("display","none");
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li style='margin-top: 35px;'>";
		var source = results[i].fileVOList[0].source.trim();
		if(source == "Eenadu Telugu")
		{
			str+="<span class='enadu fontStyle' style='font-weight:bold;'><a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></span>";
		}
		else
		{
			str+="<h4 style='text-transform: capitalize;color: #005580;'><a  href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h4>";
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

		str+="<div class='span9' style='width:550px;'>";
		str +='<table><tr><td style="width:240px;">';
		str +='<p style="margin-right: 12px;"><span class="text-error" style="font-weight:bold;">Source : </span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p><span class="text-error" style="font-weight:bold;">Date : </span > '+results[i].fileDate+'</p></td>';
		if(results[i].responseCount > 0)
		str+='<td style="vertical-align: top;"><p><span class="text-error" style="font-weight:bold;padding-left: 20px;"><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /></span > '+results[i].responseCount+'</p></td>';
		
		str +='</tr></table>';
		str +='</div>';
		
		str+="<br><div class='span2' style='float:right;'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>Details...</a></div></li>";
	}
	
	var itemsCount=results[0].count;
	
	var maxResults=jsObj.maxIndex;
	str+="</ul>";

	$("#latestNewsDiv").html(str);
	
	if(jsObj.startIndex==0){
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
	/* else if (Date.parse(fromDate) > Date.parse(toDate)) {
      $("#errorMsgDiv").html('Invalid Date Selection.');
      return;
	}  */

	
	var urlstr = "selectedNewsDetailsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&";
	
    var browser1 = window.open(urlstr,"newsDetails"+fromDate+"And"+toDate+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
}
  $(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date(),
		onSelect: function(dateText, inst) { 
			isDateValid();		
		}		
		
	}).datepicker("show");
});

getKeywordRelatedNews(0);
</script>

</body>
</html>