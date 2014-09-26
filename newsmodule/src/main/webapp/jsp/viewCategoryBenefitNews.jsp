<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
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
<div class="span12" id="newsDisplayCategoryDiv" style="text-align: left;">	<div id="ajaximg"><img width="18" height="11" src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;margin-left:400px;margin-top:100px;" id=""></div>					
</div>


<div id="paginationId"></div>

</div>
</div>
</div>
<script>
		var fromDate = '${fromDate}';
		var toDate = '${toDate}';
		var stateId = '${stateId}';
		var benefitId = '${benefitId}';
		var categoryId = '${categoryId}';
		var partyId = '${partyId}';
		getCategoryBenefitNewsDetails(0,1000);
            
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
			 partyId: partyId
	  },
		 
	  success: function(results){ 
		   buildCategoryBenefitNewsDetails(results,0,startIndex,endIndex);
	 },
	  error:function() { 
	     $("#newsDisplayCategoryDiv").html('');
	  }
	});

}
buildFilesInGallaryDetails(results,0,startIndex,endIndex){
}

function buildFilesInGallaryDetails1(results,selectedvalue,index,endValue)
{  
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
function getNewsDetailsByContentId(contentId)
{
  var urlstr = "newsDetailsPopupAction.action?contentId="+contentId+"&";
	
    var browser1 = window.open(urlstr,"gallaryDetails"+contentId+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
}
</script>

</body>