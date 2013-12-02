<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> TDP News Portal </title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityDateOperations.js"> </script>

<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
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


#existingFromText,#existingToText{width:155px; cursor: text;}
#errorMsgDiv{font-size:12px; margin-left:0px;margin-bottom: 1px;}
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
						<div class="span12 boxHeading"><h4 style="text-transform: capitalize;">${level} News</h4></div>
					</div>
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading" style="text-transform: capitalize;"><h4>${level} Wise Latest News Updates  </h4></div>
						<div id="imageForMail"  class = "span3"  style="display:none;font-weight:bold;color: #0174DF;height:20px;width:500px;">
							<font>Please wait...</font>
							<img src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;" width="18" height="11"/>
						</div>
						<div id="pagedNewsId"></div>
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
			<input type="button" value="Cancel" id="newsCancelBtn" onclick="cancelAll()" class="btn btn-info"/>

							</ul>
						</div>
					</div>
				</div>
				<!-----All News DIv End ------>
			</div>
		<!--------- Row-1 End -------->
		
	</div>
		<!------JS------>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	<script>
	
	getNewsForPagination(0);
		
      

function getNewsForPagination(pageNo){
	var level="${level}";
	
var jsObj = {
			partyId:872,
			firstResult:pageNo,
			maxResult:10,
			queryType:'public',
			stateId:1,
			scopeId:2,
			level:level,
			task:"getNewsByPaging"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getNewsByPagingAction.action?"+rparam;
	callAjax(jsObj, url);
	$('#imageForMail').css("display","block");
}

function callAjax(jsObj,url)
{
	var myResults;
	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);					
			
			if(jsObj.task == "getNewsByPaging")
			{
				$('#imageForMail').css("display","none");
				buildPaginatedNews(myResults,jsObj);
			}
		    else if(jsObj.task == "getSelectedNewsBetweenDates")
			{
				 buildPaginatedNews1(myResults,jsObj);
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

function buildPaginatedNews(results,jsObj){
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		var source = results[i].eenadu;
		if(source)
		{
			str+="<span class='enadu fontStyle' style='font-weight:bold;'> <a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].fileTitle1+"</a></span>";
		}
		else
		{
			str+="<h4 style='text-transform: capitalize;'> <a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].fileTitle1+"</span></h4>";
		}
		
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>";
		str+="<img id='myImg' style='width:100%' src="+results[i].displayImagePath+" onerror='imgError(this)'></a>";
		if(source)
		{
			str+="<p class='span8 enadu fontStyle'>"+results[i].description+"</p>";
		}
		else
		{
			str+="<p class='span8'>"+results[i].description+"</p>";
		}
				
		str+="</div>";
		str+="<div class='span9'  style='width:550px;'><table><tr><td style='width:240px;font-weight:bold;'><p class='text-error' >Source : <span style='font-weight:normal;color:black;'>"+results[i].source+"</span></p></td><td style='font-weight:bold;width: 135px;'><p class='text-error'>Date : <span style='font-weight:normal;color:black;'>"+results[i].fileDate+"</span></p></td>";
	if(results[i].responseCount > 0)
		str+="<td style='font-weight:bold;padding-left: 20px;width:200px;'><p class='text-error' ><img alt='response count' title='Response Count' src='images/responseCountIcon.png' id='responseNewsCountImg' /><span style='font-weight:normal;color:black;'>"+results[i].responseCount+"</span></p></td>";
	if(results[i].responseCount == 0)
		str+="<td style='font-weight:bold;padding-left: 20px;width:200px;'></td>";
		
		str+="</tr></table></div><br><br><div class='span2' style='float:right;'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>Details...</a></div></li>";
	}
	
	var itemsCount=results[0].count;
	var maxResults=jsObj.maxResult;
	str+="</ul>";
	$("#pagedNewsId").html(str);
	
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
		getNewsForPagination(0);
		return;
	}
	else if(fromDate =="" || toDate== "")
	{
	  $("#errorMsgDiv").html('Please Select Date');
		return;
	}
	
	/* else if (Date.parse(fromDate) > Date.parse(toDate)) {
      $("#errorMsgDiv").html('Invalid Date Selection.');
      return;
	}  */
	else
   getNewsForPagination1(0);
}
function getNewsForPagination1(startIndex){

    var level="${level}";
    var fromDate = $("#existingFromText").val();
	var toDate = $("#existingToText").val();
	var jsObj={
		partyId:872,
	    firstResult:startIndex,
		maxResult:10,
		fromDate:fromDate,
	    toDate:toDate,
		scopeId:2,
		level:level,
		task:'getSelectedNewsBetweenDates'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "selectedNewsDetailsBetweenDatesAction.action?"+rparam;
	callAjax(jsObj, url);
	$('#imageForMail').css("display","block");
}	 

$(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date(),
	    onSelect: function(date1, date2) { 
			isDateValid();		
		}		
	}).datepicker("show");
});
function buildPaginatedNews1(results,jsObj)
{
	 $('#imageForMail').css("display","none");	
	 $('#paginationId').css("display","block"); 
	 $("#pagedNewsId").html('');
	if(results == null || results.length == 0)
	{
        $('#paginationId').css("display","none"); 
		$("#pagedNewsId").html('<p style="margin-left: 25px;">No Data Found.</p>');
		return;
	}
	
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		var source = results[i].fileVOList[0].source.trim();
		if(results[i].flagSet != null)
		{
			str+="<h4 class='enadu' style='color:#0088CC;'> <a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h4>";
		}
		else
		{
				str+="<h4 style='text-transform: capitalize;color:#0088CC;'><a href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h4>";
		}
		
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>";
		var path = "";
		if(results[i].fileVOList[0].fileVOList != null && results[i].fileVOList[0].fileVOList.length > 0)
		{
		 path = results[i].fileVOList[0].fileVOList[0].path;
		}
		else
		 path = "";
		
		 var source = results[i].fileVOList[0].source;
		
		 str+="<img id='myImg1' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
	
		if(results[i].flagSet != null)
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
		str +='<p style="margin-right: 12px; width: 260px;"><span class="text-error" style="margin-left: 5px;font-weight: bold;">Source :</span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p><span class="text-error" style="font-weight: bold;">Date :</span> '+results[i].fileDate+'</p></td>';
	if(results[i].responseCount > 0)
		str+='<td style="vertical-align: top;padding-left: 50px;"><p><span class="text-error" style="font-weight: bold;">Respone Count :</span> '+results[i].responseCount+'</p></td>';
		
		str +='</tr></table>';
		str +='</div>';
		
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>Details...</a></div></li>";
		var len = results.length;
		
		if(len-1 != i)
		 str +='<hr>';
	}
	
	var itemsCount=results[0].count;
	
	var maxResults=jsObj.maxResult;
	str+="</ul>";

	$("#pagedNewsId").html(str);
	
	if(jsObj.firstResult==0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			searchType:'betweenDates1',
			cssStyle: 'light-theme'
		});
	}
}
function cancelAll()
{
	$("#errorMsgDiv").html('');
	document.getElementById('existingFromText').value = '';
	document.getElementById('existingToText').value = '';
}

		
</script>
</body>
</html>