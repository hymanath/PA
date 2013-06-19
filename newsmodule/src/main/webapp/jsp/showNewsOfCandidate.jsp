<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> News - Telugudesam Party</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	
	<!--<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>-->
	<script src="js/partyWiseNewsDisplay.js"></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

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
	<script src="js/sendUpdatesByEmail.js"></script>
	<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>-->
	
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
	
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
	select {
    background-color: #FFFFFF;
    border: 1px solid #F3E81E;
    width: 130px;
	}
	#requiredValue{
	color:red;
	font-size:large;
	}	

	.tdWidth1{
	width: 92px;
	}
	#boxHeading1{
	color: #66B16B;
    font-family: 'PT Sans',sans-serif;
    font-size: 16px;
	font-weight: bold;
	height: 40px; 
	}
	
	#candidatesListId{width:150px;}
	</style>
</head>
<body>
		<!---Header----->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<div class="span2">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Candidates News</h4></div>
						<s:select theme="simple" label="Candidates" name="candidates" id="candidatesListId" list ="candidatesMap"  headerKey="0" headerValue="Select Candidate" onchange="getCandiNews();" value="candidateId"/> 
						
						<!--<s:select list ="candidatesMap" name ="candidateId" value="candidateId" headerKey="0" headerValue = "Select Candidate"/>-->
						<div class="span12 errorDiv"></div>
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
<Script type="text/javascript">
var candidateId = '${candidateId}';
getNewsForPagination(0);
//getCandidates();

function getNewsForPagination(num){
	var jsObj={
		candidateId:candidateId,
		firstRecord:num,
		maxRecords:10,
		type:'Public',
		task:'getCandidatesNewsInHomePage'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateNewsInHomePageAction.action?"+rparam;
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
			if(jsObj.task == "getCandidatesNewsInHomePage")
			{
				buildPaginatedNewsOfCandidate(myResults,jsObj);
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
function buildPaginatedNewsOfCandidate(results,jsObj){

	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		if(results[i].source == "Eenadu Telugu")
		 str+="<h4 style='text-transform: capitalize;'><span class='enadu'>"+results[i].title+"</span></h4>";
		else
		 str+="<h4 style='text-transform: capitalize;'>"+results[i].title+"</h4>";

		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}'>";
		str+="<img id='myImg' style='width:100%' src="+results[i].displayImagePath+" onerror='imgError(this)'></a>";
		if(results[i].source == "Eenadu Telugu")
		 str+="<p class='span8 enadu'>"+results[i].description+"</p>";
		else
		 str+="<p class='span8'>"+results[i].description+"</p>";

		str+="</div>";
		str+="<div class='row-fluid m_top10'><div class='span6'><p class='text-error'>Source : "+results[i].source+"</p></div>";
		str+="<div class='span3'><p class='text-info'>Response Count: "+results[i].responseCount+"</p></div>";
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].ids+")' class='btn btn-mini btn-info pull-right' type='button'>More</a></div></li>";
	}
	
	var itemsCount=results[0].count;
	var maxResults=jsObj.maxRecords;
	str+="</ul>";
	$("#pagedNewsId").html(str);
	
	if(jsObj.firstRecord==0){
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
function getCandidates(){
	var jsObj={
		task:'getCandidateNamesInHomePage'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateNamesInHomePageAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getCandiNews(){
	var candidateId=$('#candidatesListId option:selected').val();
	
	if(candidateId==0){
		$('.errorDiv').html('<span class="text-error" style="margin-left:10px;">Please Select Candidate</span>');
		return;
	}
	 var urlstr = "showNewsOfCandidateAction.action?candidateId="+candidateId+"";
		
     var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}
</script>	
</body>
</html>