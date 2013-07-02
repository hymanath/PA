<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> TDP News Portal </title>
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
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />

	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<style type="text/css">
    @font-face
	{
	 font-family:eFont;src: url('img/eenadu.ttf');
	}
	@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
	@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
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
	#existingFromText,#existingToText{width:155px; cursor: text;}
	#errorMsgDiv{font-size:12px;}
	#candidateFromText,#candidateToText{width:75px; cursor: text;}
	#byAllRadio{margin-right: 4px;margin-top: 0;}
	#byDateRadio{margin-left: 10px; margin-right: 4px;margin-top: 0;}
	#candidateNewsShowHideDatesDiv{margin-top: 10px;}
	#fromParaId{margin-left: 4px; margin-bottom: 5px;}
    #toParaId{margin-left: 3px;}
	#candidateToText{margin-left: 15px;}
	#candidateNewsBtn{margin-left: 30px;}
	#cadidateRadioDiv{text-align: center; margin-top: 6px;margin-bottom: 15px;}
	#categoryCheckBoxId{margin-top: 0px; margin-left: 11px; margin-right: 8px;}
	#gallaryRadioId{margin-right: 8px;margin-top: 0;}
	#gallaryCategoryDiv{margin-top: 4px; line-height: 1.9em;}
	.errorDiv{color:red;}
	#categoryGallary{margin: 0px 5px 0px 0px;}
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
					    <div id="cadidateRadioDiv">
						  <input type="radio" id="byAllRadio" value="byAll" name="candidateNewsRadio" class="candidateRadioCls" checked="true"/>All
						  <input type="radio" id="byDateRadio" value="byDate" name="candidateNewsRadio" class="candidateRadioCls"/>By Date
						
						  <!-- <input type="radio" value="gallaryRadio" id="gallaryRadioId" class="gallaryCategoryRadio" onclick="getCandidateGallaries()"/>By Gallary -->
				          <div id="gallaryCategoryDiv">
						    <input type="checkbox" value="gallaryRadio" id="gallaryRadioId" class="gallaryCategoryRadio" />By Gallery
							<br>
						    <input type="checkbox" value="categoryCheckBox" id="categoryCheckBoxId" class="categoryCheckBoxCls" />By Category
						  </div>
						</div>
						<div id="candidateNewsShowHideDatesDiv" style="display:none;">
						  <p id="fromParaId">From: <input type="text" size="20" name="fileDate" readonly="true" class="candidateDateField" id="candidateFromText" /></p>
						   <p id="toParaId">To: <input type="text" size="20" name="fileDate" readonly="true" class="candidateDateField" id="candidateToText" /> </p>
						</div>

						<s:select theme="simple" label="Candidates" name="candidates" id="candidatesListId" list ="candidatesMap"  headerKey="0" headerValue="Select Candidate"  value="candidateId"/> 

						<div id="gallaryShowHideDiv" style="display:none;">
						  <select id="gallaryId" multiple="multiple"></select>
						</div>

						<div id="categoryShowHideDiv" style="display:none;">
						   <select id="candidateCategoryId" multiple="multiple"></select>
						   <br>
						   <input type="checkbox" value="categoryGallaries" id="categoryGallary" onclick="getCategoryGallaries()"/>Gallery 
						 </div>
						
						<div id="categoryGallaryShowHideDiv" style="display:none;">
						 <select id="categoryGallarySelect" multiple="multiple"></select>
						</div>

						<input id="candidateNewsBtn" type="button" class="btn btn-info" value="submit" onclick="getCandiNews();"/>
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
								<!-- <li>
									<h6>News Main Title</h6>
									<p>
About Gmail - email from Google

Video chat with a friend, or give someone a ring all from your inbox. See more reasons to switch or check out our newest features. <p>
								</li>-->
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
<Script type="text/javascript">
var candidateId = '${candidateId}';
var fromDate = '${fromDate}';
var toDate = '${toDate}';
var gallaryIds = '${gallaryIds}';
var categoryIds = '${categoryIds}';
var tempVarable = '${tempVarable}';

getNewsForPagination(0);
//getCandidates();

function getNewsForPagination(num){
	var jsObj={
		candidateId:candidateId,
		firstRecord:num,
		maxRecords:10,
		fromDate:fromDate,
		toDate:toDate,
		gallaryIds:gallaryIds,
		categoryIds:categoryIds,
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
			else if(jsObj.task == "getCandidateRelatedGallaries")
			{
			   clearOptionsListForSelectElmtId('gallaryId');
			   buildGallaryList('gallaryId',myResults);
			  
			}

			else if(jsObj.task == "getCandidateRelatedCategories")
			{
			  clearOptionsListForSelectElmtId('candidateCategoryId');
			  buildCategoryList('candidateCategoryId',myResults);
			}

			else if(jsObj.task == "getGallariesForSelectedCategory")
			{
			  clearOptionsListForSelectElmtId('categoryGallarySelect');
			  buildCategoryGallaryList('categoryGallarySelect',myResults);
			 
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

	$("#pagedNewsId").html('');
	if(results == null)
	{
		$("#pagedNewsId").html('No Data Found.');
		return;
	}
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		var source = results[i].fileVOList[0].source.trim();
		if(source == "Eenadu Telugu")
		  str+="<h4 class='enadu' style='font-size:23px;'><a style='color: #005580;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h4>";
		else
		  str+="<h4 style='text-transform: capitalize'><a  style='color: #005580;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>"+results[i].title+"</a></h4>";
				
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{getNewsDetailsByContentId("+results[i].contentId+")}'>";
		
		var path = results[i].fileVOList[0].fileVOList[0].path;
		var source = results[i].fileVOList[0].source;
		
		str+="<img id='myImg' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
		if(source == "Eenadu Telugu")
		  str+="<p class='span8 enadu' style='color:#000;'>"+results[i].description+"</p>";
		else
		 str+="<p class='span8'>"+results[i].description+"</p>";
		
		
		str+="</div>";

		str+="<div class='row-fluid m_top10'><div class='span9'>";
		str +='<table><tr><td>';
		str +='<p style="margin-left: 5px;"><span class="text-error" style="font-weight:bold;">Source :</span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p><span class="text-error" style="margin-left: 45px;font-weight: bold;">Date :</span> '+results[i].fileDate+'</p></td><td style="vertical-align: top;"><p ><span class="text-error" style="font-weight: bold; margin-left: 15px;">Response Count: </span>'+results[i].responseCount+'</p></td></tr>';
		
		str +='</table>';
		str +='</div>';
		
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>Details...</a></div></li>";
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
	$('.errorDiv').html('');
	if(candidateId==0){
		$('.errorDiv').html('<span class="text-error" style="margin-left:10px;">Please Select Candidate</span>');
		return;
	}
	 var fromDate = "";
	 var toDate = "";
	 var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();

	 if(radioVal == "byDate")
	 {
		fromDate = $("#candidateFromText").val();
	    toDate   = $("#candidateToText").val();
	    if(fromDate=="" && toDate == "")
	    {
		 $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select From And To Dates</span>');
		 return;
	    }
	    else if(fromDate =="")
	    {
	     $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select From Date</span>');
		 return;
	    }
	    else if(toDate =="")
	    {
	      $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select To Date</span>');
		  return;
	    }
	    else if (Date.parse(fromDate) > Date.parse(toDate))
	    {
          $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Invalid Date Selection.</span>');
          return;
	    } 
     
	 }
	var selectedGallaryIds = "";
	if($("#gallaryRadioId").is(":checked"))
	{
	  selectedGallaryIds = $('#gallaryId').val();
	  if(selectedGallaryIds == null || selectedGallaryIds == "null")
	  {
		$(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select Gallary.</span>');
          return;
	  }

	}
	else
	 selectedGallaryIds = "";
	
	var categoryIds = "";
	if($("#categoryCheckBoxId").is(":checked"))
	{
      categoryIds = $("#candidateCategoryId").val();
	  if(categoryIds == null || categoryIds == "null")
	  {
		$(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select Category.</span>');
          return;
	  }

	  if($("#categoryGallary").is(":checked"))
	  {
        selectedGallaryIds = $("#categoryGallarySelect").val();
		 if(selectedGallaryIds == null || selectedGallaryIds == "null")
	    {
		  $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select Gallary.</span>');
          return;
	    }
	  }
	}
	 var urlstr = "showNewsOfCandidateAction.action?candidateId="+candidateId+"&fromDate="+fromDate+"&toDate="+toDate+"&gallaryIds="+selectedGallaryIds+"&categoryIds="+categoryIds+"&tempVarable=true&";
     var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
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
$(document).ready(function(){

 $(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
});


  $(".candidateDateField").live("click", function(){
   $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
 });

$(".candidateRadioCls").click(function(){
	var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	if(radioVal=="byAll")
	{
	 $("#candidateFromText").val('');
	 $("#candidateToText").val('');
	 $("#candidateNewsShowHideDatesDiv").css("display","none");
	}
	else
     $("#candidateNewsShowHideDatesDiv").css("display","block");

	});

$("#candidatesListId").live("change",function(){

  tempVarable = false;
  var candidateId = $("#candidatesListId").val();
   $('.errorDiv').html('');
    clearOptionsListForSelectElmtId('gallaryId');
	clearOptionsListForSelectElmtId('candidateCategoryId');
    clearOptionsListForSelectElmtId('categoryGallarySelect');
	$("#categoryGallary").attr('checked', false);
	$("#categoryGallaryShowHideDiv").css("display","none");

  if(candidateId == 0)
  {
    $('.errorDiv').html('<span class="text-error" style="margin-left:20px;">Please Select Candidate</span>');
		return;
  }
   $("#categoryGallary").attr('checked', false);
   $("#categoryGallaryShowHideDiv").css("display","none");
   
  if($("#gallaryRadioId").is(":checked"))
  {
	$("#gallaryShowHideDiv").css("display","block");
    $("#gallaryRadioId").attr('checked', true);
	getCandidateGallaries();
  }
  else
  {
	$("#gallaryShowHideDiv").css("display","none");
  }

  if($("#categoryCheckBoxId").is(":checked"))
  {
	$("#categoryShowHideDiv").css("display","block");
    $("#categoryCheckBoxId").attr('checked', true);
	getCandidatecategories();
  }
  else
  {
	$("#categoryShowHideDiv").css("display","none");
  }
	
});

$("#gallaryRadioId").click(function(){
	if($("#gallaryRadioId").is(":checked"))
	{
      $("#gallaryShowHideDiv").css("display","block");
	  getCandidateGallaries();
	  $("#categoryCheckBoxId").attr('checked', false);
	  $("#categoryShowHideDiv").css("display","none");
	  $("#categoryGallaryShowHideDiv").css("display","none");
	}
	else
    {
	  $("#gallaryShowHideDiv").css("display","none");
    }

});


$("#categoryCheckBoxId").click(function(){
	
	$("#categoryGallary").attr('checked', false);
    $("#categoryGallaryShowHideDiv").css("display","none");
    tempVarable = false;
    clearOptionsListForSelectElmtId('categoryGallarySelect');
	if($("#categoryCheckBoxId").is(":checked"))
    {
	  $("#gallaryRadioId").attr('checked', false);
	  $("#gallaryShowHideDiv").css("display","none");
	  $("#categoryShowHideDiv").css("display","block");
	  getCandidatecategories();
	}
	else
	  $("#categoryShowHideDiv").css("display","none");

});



$("#candidateCategoryId").live("click",function(){
	
	$("#categoryGallary").attr('checked', false);
	$("#categoryGallaryShowHideDiv").css("display","none");
});

});//End of Ready

function getCandidateGallaries()
{

  $("#gallaryShowHideDiv").css("display","block");
    var fromDateStr = "";
	var toDateStr = "";
	var candidateId = $("#candidatesListId").val();
	$(".errorDiv").html('');
	if(candidateId == 0)
	{
	  $(".errorDiv").html('Please Select Candidate');
	  return;	
	}
	if(fromDate != "")
	 fromDateStr = fromDate;
	if(toDate != "")
	 toDateStr = toDate;

	var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	if(radioVal == "byDate")
	{
	   fromDateStr = $("#candidateFromText").val();
	   toDateStr = $("#candidateToText").val();
	
	   if(fromDateStr=="" && toDateStr == "")
	   {
		 $(".errorDiv").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDateStr =="")
	   {
	     $(".errorDiv").html('Please Select From Date');
		 return;
	   }
	   else if(toDateStr =="")
	   {
	     $(".errorDiv").html('Please Select To Date');
		 return;
	   }
	   else if (Date.parse(fromDateStr) > Date.parse(toDateStr)) {
         $(".errorDiv").html('Invalid Date Selection.');
         return;
	   } 
	}

	var jsObj={
		fromDate:fromDateStr,
		toDate:toDateStr,
		candidateId:candidateId,
		task:'getCandidateRelatedGallaries'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedGallariesAction.action?"+rparam;
	callAjax(jsObj, url);


}

function getCategoryGallaries()
{
	
	var candidateId = $("#candidatesListId").val();
	$('.errorDiv').html('');
  if($("#categoryGallary").is(":checked"))
  {
	 

	 if(candidateId == 0)
	 {
       $('.errorDiv').html('<span class="text-error" style="margin-left:20px;">Please Select Candidate.</span>');
		return;
	 }
	 var categoryIdsArray = new Array();
    
	  $('#candidateCategoryId > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
     });

       if(categoryIdsArray == null || categoryIdsArray == "null" || categoryIdsArray.length == 0)
	   {
         $('.errorDiv').html('<span class="text-error" style="margin-left:20px;">Please Select Category.</span>');
		 return;
	   }
	$("#categoryGallaryShowHideDiv").css("display","block");
      var jsObj={
		candidateId:candidateId,
		categoryIds:categoryIdsArray,
		task:'getGallariesForSelectedCategory'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGallariesForSelectedCategoryAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
  }
  else
	{
     clearOptionsListForSelectElmtId('categoryGallarySelect');
	 $("#categoryGallaryShowHideDiv").css("display","none");
	}


}

function getCandidateDetails()
{
	
 if(gallaryIds != "null" && gallaryIds != "" && (categoryIds =="null" ||  categoryIds ==""))
 {
  $("#categoryShowHideDiv").css("display","none");
  $("#candidateNewsShowHideDatesDiv").css("display","block");
  $("#gallaryRadioId").attr('checked', true);
  getCandidateGallaries();
  
 }

 if(categoryIds !="null" && categoryIds !="" && (gallaryIds == "null" || gallaryIds == ""))
 {
   
   $("#categoryCheckBoxId").attr('checked', true);
   $("#categoryShowHideDiv").css("display","block");
   getCandidatecategories();
 }

 if((categoryIds !="null" && categoryIds !="") && (gallaryIds != "null" && gallaryIds != ""))
 {
   $("#categoryCheckBoxId").attr('checked', true);
   $("#categoryShowHideDiv").css("display","block");
   getCandidatecategories();
  
 }
 
 if(fromDate=="" && toDate == "")
 {
  $('#byAllRadio').trigger('click');
  $("#candidateFromText").val('');
  $("#candidateToText").val('');
  $("#candidateNewsShowHideDatesDiv").css("display","none");
  
 }
 else
 {
  $('#byDateRadio').trigger('click');
  $("#candidateNewsShowHideDatesDiv").css("display","block");
  $("#candidateFromText").val(''+fromDate+'');
  $("#candidateToText").val(''+toDate+'');
 }
}



function buildGallaryList(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

	if(gallaryIds != "null" && gallaryIds != "" && (categoryIds == "null" || categoryIds == "") && tempVarable == "true")
   {
     var valoresArea=gallaryIds 
    var arrayArea = valoresArea.split(',');
    $('#gallaryId').val(arrayArea).attr('selected', true);
   }

}

function buildCategoryList(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

	 if(categoryIds != "null" && categoryIds != "" && tempVarable == "true")
   {
     var valoresArea=categoryIds 
    var arrayArea = valoresArea.split(',');
    $('#candidateCategoryId').val(arrayArea).attr('selected', true);
   }

   if(gallaryIds != null && gallaryIds !="" && tempVarable == "true")
	{
	   $("#categoryGallary").attr('checked', true);
       $("#categoryGallaryShowHideDiv").css("display","block");
       getCategoryGallaries();
	}

}
function buildCategoryGallaryList(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
   
	 if(categoryIds != null && categoryIds != "" && gallaryIds !=null && gallaryIds !="")
    {
     var valoresArea = gallaryIds; 
	
    var arrayArea = valoresArea.split(',');
    $('#categoryGallarySelect').val(arrayArea).attr('selected', true);
   }
}
function getCandidatecategories()
{
   var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesListId").val();
	$(".errorDiv").html('');
	if(candidateId == 0)
	{
	  $(".errorDiv").html('Please Select Candidate');
	  return;	
	}
	var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	if(radioVal == "byDate")
	{
	   fromDate = $("#candidateFromText").val();
	   toDate = $("#candidateToText").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $(".errorDiv").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $(".errorDiv").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $(".errorDiv").html('Please Select To Date');
		 return;
	   }
	   else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $(".errorDiv").html('Invalid Date Selection.');
         return;
	   } 
	}

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedCategories'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedCategoriesAction.action?"+rparam;
	callAjax(jsObj, url);

}

getCandidateDetails();
</script>	
</body>
</html>