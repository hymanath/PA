<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>Insert title here</title>
</head>
<body>
		
		<!---Header----->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<!-- <div class="span2">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>News</h4></div>
					</div> -->
				</div>
				<!---View your Constituency News Div--->
				<div class="span8">
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
			<input type="button" value="Get News" id="selectedNewsDetBtn" onclick="getSelectedNewsDetails(0)" class="btn btn-info"/>
											
							</ul>
						</div>
					</div>
				</div>
				<!-----All News DIv End ------>
			</div>
		<!--------- Row-1 End -------->
		
	</div>
<script type="text/javascript">

getNewsForPagination(0);
function getNewsForPagination(startIndex)
{
	
var jObj=
	{
		
	  firstResult:startIndex,
	  maxResult:10,
	  task:"getLatestResponsefiles"

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
			if(jsObj.task == "getLatestResponsefiles")
			{
				buildPaginatedNews(myResults,jsObj);
			}
			if(jsObj.task == "getLatestResponsefilesForSelectedNews")
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
	$("#latestNewsDiv").html("");
	var str="";
	
	if(results != null)
	{
		str+="<ul class='unstyled pad10'>";
		for(var i in results){
		str+="<li>";
		
		var source = results[i].fontId;
		if(source == 1)
		{
		str+="<span class='enadu fontStyle' style='font-weight:bold;cursor: pointer;'  onclick='getNewsDetailsByContentId("+results[i].contentId+")'>"+results[i].title+"</span>";
		}
		else
		{
			str+="<h4 style='text-transform: capitalize;cursor: pointer;' onclick='getNewsDetailsByContentId("+results[i].contentId+")'>"+results[i].title+"</h4>";
		}
		
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>";
		
		var path = results[i].filePath1;
		//var source = results[i].fontId;
		
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

		str+="<div class='row-fluid m_top10'><div class='span10'>";
		str +='<table style="width:100%;"><tr><td tyle="width:32%;">';
		str +='<p style="margin-left: 5px;"><span class="text-error" style="font-weight:bold;">Source : '+results[i].source+'</span>';
		var length = results[i].count;
/* 		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].source+'';
		  if(length-1 != j)
			str +=',';
		} */
		str +='</p></td><td style="vertical-align: top;width:37%;"><p><span class="text-error" style="margin-left: 45px;font-weight: bold;">Date :</span> '+results[i].fileDate+'</p></td><td style="vertical-align: top;width:33%;"><p ><span class="text-error" style="font-weight: bold; margin-left: 15px;"><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /> </span>'+results[i].responseCount+'</p></td></tr>';
		if(results[i].candidateName != null && results[i].candidateName != "")
		str +='<td colspan="2" style="vertical-align: top;width:37%;"><p><span class="text-error" style="font-weight: bold;">Candidate(s) involved :</span> '+results[i].candidateName+'</p></td>';
		
		if(results[i].keywords != null && results[i].keywords != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Keyword(s) : <span style="font-weight:normal;color:black;">'+results[i].keywords+'</span></p></td></tr>';
		if(results[i].categoryName != null && results[i].categoryName != "")
		str+='<tr><td colspan="3" style="font-weight:bold;"><p class="text-error" >Category(s) : <span style="font-weight:normal;color:black;">'+ results[i].categoryName+'</span></p></td></tr>';
		
		str +='</table>';
		str +='</div>';
		str+='<div class="span3 pull-right"><a class="btn btn-mini btn-info" type="button" onclick="getNewsDetailsByContentId('+results[i].contentId+')">Details...</a>&nbsp;&nbsp;';
		str+="<a href='showNewsResponseAction.action?responseContentId ="+results[i].contentId+"' class='btn btn-mini btn-info' type='button'>track</a></div></li>";
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
	else
	{
		$("#latestNewsDiv").html("No data avaliable");
		$('#paginationId').html('');
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
	var jObj=
	{
		
	  firstResult : startIndex,
	  maxResult   : 10,
	  fromDate    : fromDate,
	  toDate      : toDate,
	  task        : "getLatestResponsefilesForSelectedNews"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "latestUpdatedNewsAction.action?"+rparam;
	callAjax(jObj,url);
	//var urlstr = "selectedNewsDetailsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&";
	
    //var browser1 = window.open(urlstr,"newsDetails"+fromDate+"And"+toDate+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
   // browser1.focus();
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