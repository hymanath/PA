<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> TDP News Portal </title>


<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityDateOperations.js"> </script>
<!--  <script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery.2.8.2.combo.js"></script>

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" /> -->
<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui.css"/>

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
#errorMsgDiv{font-size:12px;margin-left: 0px; margin-bottom: 0px;}
.fontStyle{color:#000;font-size:21px;}
#responseNewsCountImg{height: 30px; width: 40px; margin-right: 3px;cursor: pointer;}
</style>
<title>Insert title here</title>
</head>
<body>
		<!---Header----->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<div class="span2">
				<div id="mainDiv"></div>
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>News</h4></div>
					</div>
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading" style="text-transform: capitalize;"><h4>News Details </h4></div>
						<div id="latestNewsAjaxDiv" style="display:none;text-align: center;"><img src="images/icons/goldAjaxLoad.gif"  style="margin-top: 15px;"/></div>
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
			<input type="button" value="Get News" id="selectedNewsDetBtn" onclick="getSelectedNewsDetails(0)" class="btn btn-info"/>
			<input type="button" value="Cancel" id="newsCancelBtn" onclick="cancelAll1()" class="btn btn-info"/>
											
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

	$('#newsTabId').addClass('menuActive');
});
</script>

<script type="text/javascript">

getNewsForPagination(0);
function getNewsForPagination(startIndex)
 {
	$("#latestNewsAjaxDiv").css("display","block");
var jObj=
	{
		
	  firstResult:startIndex,
	  maxResult:10,
	  searchBy:"all",
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
			else if(jsObj.task == "getSelectedNewsBetweenDates")
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
	$("#latestNewsDiv").html('');
	$("#latestNewsAjaxDiv").css("display","none");
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li style='margin-top: 35px;'>";
		var source = results[i].fileVOList[0].source.trim();
		if(results[i].flagSet != null)
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
		if(results[i].descEenadu)
		{
			str+="<p class='span8 enadu fontStyle'>"+results[i].description+"</p>";
		}
		else
		{
			str+="<p class='span8'>"+results[i].description+"</p>";
		}
		
		str+="</div>";

		str+="<div class='span9' style='width:515px;'>";
		str +='<table><tr><td style="width:240px;">';
		str +='<p style="margin-right: 12px;"><span class="text-error" style="font-weight:bold;">Source : </span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;width:150px;"><p><span class="text-error" style="font-weight:bold;">Date : </span > '+results[i].fileDate+'</p></td>';
		if(results[i].responseCount > 0)
		str+='<td style="vertical-align: top;"><p><span class="text-error" style="font-weight:bold;padding-left: 20px;"><a href="javascript:{populateNewsResponseWindow('+results[i].contentId+')};"><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /></span > '+results[i].responseCount+'</p></a></td>';
		str+='</tr>';
		if(results[i].candidateName != null && results[i].candidateName != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Candidate(s) involved : <span style="font-weight:normal;color:black;">'+results[i].candidateName+'</span></p></td></tr>';
	
		if(results[i].keywords != null && results[i].keywords != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Keyword(s) : <span style="font-weight:normal;color:black;">'+results[i].keywords+'</span></p></td></tr>';
		if(results[i].categoryName != null && results[i].categoryName != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Category(s) : <span style="font-weight:normal;color:black;">'+ results[i].categoryName+'</span></p></td></tr>';
	

		str +='</table>';
		str +='</div>';
		
		str+="<br><div class='span2' style='float:right;'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' style='margin-top:-10px' class='btn btn-mini btn-info pull-right' type='button'>Details...</a></div></li>";
	}
	
	var itemsCount=results[0].count;
	
	var maxResults=jsObj.maxResult;
	str+="</ul>";

	$("#latestNewsDiv").html(str);
		$('html,body').animate({
        scrollTop: $("#mainDiv").offset().top},
        'slow');
	
	if(jsObj.firstResult==0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			searchType:jsObj.searchBy
		});
	}
}
function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}

function getSelectedNewsDetails(startIndex)
{
	$("#errorMsgDiv").html('');
	
	var fromDate = $("#existingFromText").val();
	var toDate = $("#existingToText").val();
	/*
	if(fromDate=="" && toDate == "")
	{
		getNewsForPagination(0);
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
*/
// STARTING : validations updated by srishailam
	   var arrr = fromDate.split("/");
			var fromDat=arrr[0];
			var frommonth=arrr[1];
			var fromyear=arrr[2];
	   var arr = toDate.split("/");
			var toDat=arr[0];
			var tomonth=arr[1];
			var toyear=arr[2];
	if(!fromDate){
			document.getElementById('errorMsgDiv').innerHTML='Please select from Date. ';
			return false;
		}
	if(!toDate){
			document.getElementById('errorMsgDiv').innerHTML='Please select to Date. ';
			return false;
		}
	if(fromyear>toyear){
		document.getElementById('errorMsgDiv').innerHTML='Invalid Date Selection. ';
		return false;
	}
	 if(frommonth>tomonth){
		   if(fromyear == toyear){
			document.getElementById('errorMsgDiv').innerHTML='Invalid Date Selection. ';
			return false;
		}
		
	}
	
	if(fromDat>toDat){	
		if(frommonth == tomonth && fromyear == toyear){			
			document.getElementById('errorMsgDiv').innerHTML='Invalid Date Selection.';
			return false;		
		   }
	}
	
	  var jObj=
	{
	  fromDate:fromDate,
	  toDate:toDate,
	  firstResult:startIndex,
	  maxResult:10,
	  level:0,
	  searchBy:"betweenDates",
	  task:"getSelectedNewsBetweenDates"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getSelectedNewsBetweenDatesAction.action?"+rparam;
	callAjax(jObj,url);
	
	
	/*
	var urlstr = "selectedNewsDetailsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&";
	
    var browser1 = window.open(urlstr,"newsDetails"+fromDate+"And"+toDate+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();*/
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
function cancelAll1()
{
	$("#errorMsgDiv").html('');
	document.getElementById('existingFromText').value = '';
	document.getElementById('existingToText').value = '';
}

function populateNewsResponseWindow(contentId){

var urlstr = 'showNewsResponseAction.action?responseContentId ='+contentId;
var browser1 = window.open(urlstr,"familyWiseDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
browser1.focus();

}
</script>
</body>
</html>