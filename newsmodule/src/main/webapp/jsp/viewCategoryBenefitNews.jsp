<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> TDP News Portal </title>

<!-- YUI Dependency files (Start) -->
	
	
	
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css">
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> 
<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	<script type="text/javascript" src="js/jquery.google.api/jquery.2.8.2.combo.js"></script>
	
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
 <script type="text/javascript" src="pagination/jquery.simplePagination.js"></script>
	<link rel="stylesheet" type="text/css" href="pagination/simplePagination.css">
	<script type="text/javascript" src="pagination/pagination1.js"></script>
   
<style>
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

#existingFromText,#existingToText{width:155px;cursor: text;}
#errorMsgDiv{font-size:12px;}
#responseNewsCountImg{height: 30px; width: 40px; margin-right: 3px;cursor: pointer;}
#paginationId{ margin-left: 30px;margin-bottom:5px;}
</style>

</head>
<body>


<div style="" id="mainDiv">
<div class="span12">
<div class="row-fluid widget">
<div class="span12 boxHeading" align="center"><h4>News Details </h4></div>
<div id="imageForMail"  class = "span3"  style="display:none;font-weight:bold;color: #0174DF;height:20px;width:500px;">
<font>Please wait...</font>
<img src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;" width="18" height="11"/>
</div>
<div class="span12" id="newsDisplayDiv" style="text-align: left;">	<div id="ajaximg"><img width="18" height="11" src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;margin-left:400px;margin-top:100px;" id=""></div>					
</div>


<div id="paginationId"></div>

</div>
</div>
</div>
<script type="text/javascript">
		var fromDate = '${fromDate}';
		var toDate = '${toDate}';
		var stateId = '${stateId}';
		var benefitId = '${benefitId}';
		var categoryId = '${categoryId}';
		var partyId = '${partyId}';
		var candidateId = '${candidateId}';
		var type = '${type}';
		var name = '${name}';
		var locationId= '${locationId}';
		
<s:if test="buildType == 'category'">      
function getCategoryBenefitNewsDetails(startIndex,endIndex)
{
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	}); 

	$.ajax({
	  type:'POST',
	  url: 'getCategoryBenifitWiseNewsAction.action',
	  dataType: 'json',
	  data: {startValue:startIndex,
	         endValue:endIndex,
			 fromDate:fromDate,
			 toDate:toDate,
			 stateId : stateId,
			 categoryId:categoryId,
			 benefitId:benefitId,
			 partyId: partyId,
			 type:type
	  },
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,0,startIndex,endIndex);
	 },
	  error:function() { 
	     $("#newsDisplayDiv").html('');
	  }
	});

}
</s:if>
<s:if test="buildType == 'candidate'">            
function getCategoryBenefitNewsDetails(startIndex,endIndex)
{
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	}); 

	$.ajax({
	  type:'POST',
	  url: 'getCandidateGroupWiseBenifitNews.action',
	  dataType: 'json',
	  data: {
	         type:type,
			 fromDate:fromDate,
			 toDate:toDate,
			 candidateId:candidateId,
	         benefitId:benefitId,
	         startValue:startIndex,
			 endValue : endIndex 
	  },
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,0,startIndex,endIndex);
	 },
	  error:function() { 
	     $("#newsDisplayDiv").html('');
	  }
	});

}
</s:if>
<s:if test="buildType == 'location'">            
function getCategoryBenefitNewsDetails(startIndex,endIndex)
{
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	}); 

	$.ajax({
	  type:'POST',
	  url: 'getLocationBenifitNewsAction.action',
	  dataType: 'json',
	  data: {
	         type:type,
			 fromDate:fromDate,
			 toDate:toDate,
			 locationId:locationId,
			 name:name,
	         benefitId:benefitId,
			  partyId: partyId,
	         startValue:startIndex,
			 endValue : endIndex 
	  },
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,0,startIndex,endIndex);
	 },
	  error:function() { 
	     $("#newsDisplayDiv").html('');
	  }
	});

}
</s:if>

function buildFilesInGallaryDetails(results,selectedvalue,index,endValue)
{   
	var totalPages;
	var requestedFor = "";
	$('#imageForMail').css("display","none");
	$("#newsDisplayDiv").html('');
  if(results == null || results.length == 0)
  {
    $("#newsDisplayDiv").html('No Data Found.');
	return;
  }
	

   var str='';

   str+='<div class="span12">';
   str+='<ul class="unstyled pad10">';
   for(var i in results)
   {
	str+='<li>';
	str+='<div class="">';
	
	var fontId = results[i].titleFont;
	if(fontId != null)
	{
		str+='<h4 style="" class="enadu"><a style="color: #005580;font-size: 25px;" href="javascript:{getNewsDetailsByContentId('+results[i].id+')}">'+results[i].title+'</a></h4>';
	}
	else
	{
		str+='<h4 style="text-transform: capitalize;"> <a style="color: #005580;font-size: 18px;" href="javascript:{getNewsDetailsByContentId('+results[i].id+')}">'+results[i].title+'</a></h4>';
	}
		

		str+='<div class="row-fluid">';
		
		
		if(results[i].font != null)
		{
			str+='<p class="span11 enadu" style="font-size: 25px;">'+results[i].description+'</p>';
		}
		else
		{
			str+='<p class="span11" style="font-size: 18px;">'+results[i].description+'</p>';
		}
		str+='</div>';
		
	str+='</div>';
	str+='</li>';
   }
    str+='</ul>';
   str+='</div>';
$("#newsDisplayDiv").html(str);
var itemsCount=results[0].count;

var maxResults=endValue;
if(index==0){
	$("#paginationId").pagination({
		items: itemsCount,
		itemsOnPage: maxResults,
		cssStyle: 'light-theme'
	});
}
 $('html,body').animate({
        scrollTop: $("#mainDiv").offset().top},
        'slow');
}
function callAjaxToGetTheResults(selectedvalue)
{
	var startIndex = 0;
	var endIndex = 1000; 
	var selectedvalue1 = 0;
	if(selectedvalue == "1")
		startIndex = 0;
	else{
		selectedvalue1 = selectedvalue - 1;
		startIndex = selectedvalue1*1000;
	}
	getCategoryBenefitNewsDetails(startIndex,endIndex)
}
getCategoryBenefitNewsDetails(0,1000);

function getNewsDetailsByContentId(contentId)
{
  var urlstr = "newsDetailsPopupAction.action?contentId="+contentId+"&";
	
    var browser1 = window.open(urlstr,"gallaryDetails"+contentId+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
}
</script>

</body>