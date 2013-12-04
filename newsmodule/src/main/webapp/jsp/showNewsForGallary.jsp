<!DOCTYPE html>
<html>
<head>
	<title> TDP News Portal </title>
	<meta name="" content="">
	
	<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->
	
    <script type="text/javascript" src="pagination/jquery.simplePagination.js"></script> 
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityDateOperations.js"> </script>
    <link href="styles/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="styles/css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>


	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
	 <link rel="stylesheet" type="text/css" href="pagination/simplePagination.css">
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

#existingFromText,#existingToText{width:155px;cursor: text;}
#errorMsgDiv{font-size:12px;}
#responseNewsCountImg{height: 30px; width: 40px; margin-right: 3px;cursor: pointer;}
</style>
<script>

$( document ).ready(function() {

	

 });

 var galalryId = '${gallaryId}';
var categoryId = '${category}';
</script>

</head>
<body>
		<!----Container---->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<div class="span2">
				 	<div id="mainDiv"></div>
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Gallery News</h4></div>
					</div>				
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>News Updates </h4></div>
						<div id="imageForMail"  class = "span3"  style="display:none;font-weight:bold;color: #0174DF;height:20px;width:500px;">
								<font>Please wait...</font>
								<img src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;" width="18" height="11"/>
						</div>
						<div class="span12" id="newsDisplayDiv">						
						</div>

						<div class="pagination-holder clearfix">
			<div id="light-pagination" class="pagination"></div>
	</div>



<div class="pagination-holder clearfix">
			<div id="light-pagination" class="pagination"></div>
	</div>
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
			<input type="button" value="Get News" id="selectedNewsDetBtn" onclick="getSelectedNewsDetails1()" class="btn btn-info"/>
							</ul>
						</div>
					</div>
				</div>
				<!-----All News DIv End ------>
			</div>
		<!--------- Row-1 End -------->
		
		
		
	</div>
		<!------JS------>
	<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>	
	
	<script type="text/javascript" src="pagination/pagination1.js"></script>
    <script type="text/javascript" src="pagination/jquery.simplePagination.js"></script>

<script>

var requestedFor = '${requestFor}';
//showAllFilesofAGallry(0,10,galalryId,1,requestedFor);
showAllFilesofACategory(0 , 10 , categoryId,1,requestedFor,galalryId);
function showAllFilesofAGallry(startIndex , endIndex , gallaryId,selectedvalue,requestedFor)
{
$('#imageForMail').css("display","block");
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
	  type:'POST',
	  url: 'getFilesInAGallary.action',
	  dataType: 'json',
	 data: {startIndex:startIndex,endIndex:endIndex,gallaryId:gallaryId,categoryId:categoryId,requestedFor:requestedFor,fromDate:"",toDate:""},
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,selectedvalue);
	 },
	  error:function() { 
	  }
	});

}
function showAllFilesofACategory(startIndex , endIndex , categoryId,selectedvalue,requestedFor,gallaryId)
{

$('#imageForMail').css("display","block");
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
	  type:'POST',
	  url: 'getFilesInACategory.action',
	  dataType: 'json',
	 data: {startIndex:startIndex,endIndex:endIndex,categoryId:categoryId,gallaryId:gallaryId,requestedFor:requestedFor,fromDate:"",toDate:""},
		 
	  success: function(results){ 
		   buildFilesInCategoryDetails(results,selectedvalue);
	 },
	  error:function() { 
	  }
	});

}
function getSelectedNewsDetails1()
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
	

	$('#imageForMail').css("display","block");
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
	  type:'POST',
	  url: 'getFilesInACategory.action',
	  dataType: 'json',
	 data: {startIndex:0,endIndex:10,gallaryId:galalryId,categoryId:categoryId,fromDate:fromDate,toDate:toDate},
		 
	  success: function(results){ 
		   buildFilesInCategoryDetails(results,1);
	 },
	  error:function() { 
	  }
	});


}
function buildFilesInCategoryDetails(results,selectedvalue)
{   
	
	var totalPages;
	$('#imageForMail').css("display","none");
	$("#newsDisplayDiv").html('');
  if(results == null || results.length == 0)
  {
    $("#light-pagination").html('');
    $("#newsDisplayDiv").html('No Data Found.');
	return;
  }
	
	/*if(requestedFor=="respondedNews"){
		totalPages = Math.ceil(results[0].respondedFilesCountInGall / 10);
	}
	else{
		totalPages = Math.ceil(results[0].totalResultsCount / 10);
	}*/
	totalPages = Math.ceil(results[0].totalResultsCount /10);
  $('#light-pagination').pagination({
	pages:totalPages,
	currentPage:selectedvalue,	 
	cssStyle: 'light-theme'
  });
   var str='';

   str+='<div class="span12">';
   str+='<ul class="unstyled pad10">';
   for(var i in results)
   {
	   str+='<li>';
	//alert(results[i].fileType.trim());
	str+='<div class="">';
	var source = results[i].fileType.trim();
	var fontId = results[i].fontId;
	var descFont = results[i].descEenadu;
	if(fontId == 1)
	{
		str+='<h4 style="text-transform: capitalize;" class="enadu"><a style="color: #005580;" href="javascript:{getNewsDetailsByContentId('+results[i].fileId+')}">'+results[i].fileName1+'</a></h4>';
	}
	else
	{
		str+='<h4 style="text-transform: capitalize;"> <a style="color: #005580;" href="javascript:{getNewsDetailsByContentId('+results[i].fileId+')}">'+results[i].fileName1+'</a></h4>';
	}
		
			str+='<div class="row-fluid">';
			//	str+='<a style="width: 146px;" href="javascript:{getNewsDetailsByContentId('+results[i].fileGallaryId+')}" class="thumbnail span4">';
				 if(results[i].filePath1 != null && results[i].filePath1 == "") 
					str+='<img style="width:100%" src="'+results[i].filePath1+'" >';
                  else
					  str+='<img style="width:100%"src="/TDP/images/TDP.PNG" >';
				str+='</a>';
				if(descFont)
				{
					str+='<p class="span8 enadu">'+results[i].fileDescription1+'</p>';
				}
				else
				{
					str+='<p class="span8">'+results[i].fileDescription1+'</p>';
				}
				
			str+='</div>';
			str+='<div class="row-fluid m_top10">';
				str+='<div class="span9" style="width:550px;">';
					str+='<table><tr><td style="width:260px;font-weight:bold;"><p class="text-error" >Source : <span style="font-weight:normal;color:black;">'+results[i].fileType+'</span></p></td><td style="font-weight:bold;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">'+results[i].fileDate+'</span></p></td>';
			if(results[i].responseCount > 0)
				str+='<td style="font-weight:bold;padding-left: 20px;"><p class="text-error" ><a href="javascript:{populateNewsResponseWindow('+results[i].fileId+')}; " "><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /><span style="font-weight:normal;color:black;">'+results[i].responseCount+'</span></a></p></td>';
				
				str+='</tr></table></div>';
				str+='<br><div class="span2" style="float:right;">';
				
					//str+='	<a type="button" class="btn btn-mini btn-info pull-right" onClick="getNewsDetailsByContentId('+results[i].fileGallaryId+')">Details...</a>';
					
				str+='</div>';
			str+='</div>';
	str+='</div>';
	str+='</li>';
   }
    str+='</ul>';
   str+='</div>';
document.getElementById("newsDisplayDiv").innerHTML = str;

 $('html,body').animate({
        scrollTop: $("#mainDiv").offset().top},
        'slow');
}
function buildFilesInGallaryDetails(results,selectedvalue)
{   
	var totalPages;
	$('#imageForMail').css("display","none");
	$("#newsDisplayDiv").html('');
  if(results == null || results.length == 0)
  {
    $("#light-pagination").html('');
    $("#newsDisplayDiv").html('No Data Found.');
	return;
  }
	
	if(requestedFor=="respondedNews"){
		totalPages = Math.ceil(results[0].respondedFilesCountInGall / 10);
	}
	else{
		totalPages = Math.ceil(results[0].totalResultsCount / 10);
	}
  $('#light-pagination').pagination({
	pages:totalPages,
	currentPage:selectedvalue,	 
	cssStyle: 'light-theme'
  });
   var str='';

   str+='<div class="span12">';
   str+='<ul class="unstyled pad10">';
   for(var i in results)
   {
	   str+='<li>';
	//alert(results[i].fileType.trim());
	str+='<div class="">';
	var source = results[i].fileType.trim();
	if(source.indexOf("Eenadu Telugu") != -1)
	{
		str+='<h4 style="text-transform: capitalize;" class="enadu"><a style="color: #005580;" href="javascript:{getNewsDetailsByContentId('+results[i].fileGallaryId+')}">'+results[i].fileName1+'</a></h4>';
	}
	else
	{
		str+='<h4 style="text-transform: capitalize;"> <a style="color: #005580;" href="javascript:{getNewsDetailsByContentId('+results[i].fileGallaryId+')}">'+results[i].fileName1+'</a></h4>';
	}
		
			str+='<div class="row-fluid">';
				str+='<a style="width: 146px;" href="javascript:{getNewsDetailsByContentId('+results[i].fileGallaryId+')}" class="thumbnail span4">';
				 if(results[i].filePath1 != null && results[i].filePath1 == "") 
					str+='<img style="width:100%" src="'+results[i].filePath1+'" >';
                  else
					  str+='<img style="width:100%"src="/TDP/images/TDP.PNG" >';
				str+='</a>';
				if(source.indexOf("Eenadu Telugu") != -1)
				{
					str+='<p class="span8 enadu">'+results[i].fileDescription1+'</p>';
				}
				else
				{
					str+='<p class="span8">'+results[i].fileDescription1+'</p>';
				}
				
			str+='</div>';
			str+='<div class="row-fluid m_top10">';
				str+='<div class="span9" style="width:550px;">';
					str+='<table><tr><td style="width:260px;font-weight:bold;"><p class="text-error" >Source : <span style="font-weight:normal;color:black;">'+results[i].fileType+'</span></p></td><td style="font-weight:bold;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">'+results[i].fileDate+'</span></p></td>';
			if(results[i].responseCount > 0)
				str+='<td style="font-weight:bold;padding-left: 20px;"><p class="text-error" ><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /><span style="font-weight:normal;color:black;">'+results[i].responseCount+'</span></p></td>';
				
				str+='</tr></table></div>';
				str+='<br><div class="span2" style="float:right;">';
				
					str+='	<a type="button" class="btn btn-mini btn-info pull-right" onClick="getNewsDetailsByContentId('+results[i].fileGallaryId+')">Details...</a>';
					
				str+='</div>';
			str+='</div>';
	str+='</div>';
	str+='</li>';
   }
    str+='</ul>';
   str+='</div>';
document.getElementById("newsDisplayDiv").innerHTML = str;

 $('html,body').animate({
        scrollTop: $("#mainDiv").offset().top},
        'slow');
}

function callAjaxToGetTheResults(selectedvalue)
{
	
	var startIndex = 0;
	var endIndex = 10; 
	var selectedvalue1 = 0;

	if(selectedvalue == "1")
		startIndex = 0;
	else{
		selectedvalue1 = selectedvalue - 1;
		startIndex = selectedvalue1*10;
	}

//	showAllFilesofAGallry(startIndex,endIndex,galalryId,selectedvalue,requestedFor)
showAllFilesofACategory(startIndex,endIndex,categoryId,selectedvalue,requestedFor,galalryId)
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

	
	/* var urlstr = "selectedNewsDetailsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&";
	
    var browser1 = window.open(urlstr,"newsDetails"+fromDate+"And"+toDate+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();*/

	$('#imageForMail').css("display","block");
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
	  type:'POST',
	  url: 'getFilesInAGallary.action',
	  dataType: 'json',
	 data: {startIndex:0,endIndex:10,gallaryId:galalryId,categoryId:categoryId,fromDate:fromDate,toDate:toDate},
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,1);
	 },
	  error:function() { 
	  }
	});


}


  $(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date(),
		onSelect:function(date1,date2){	
			isDateValid();
		}
		
	}).datepicker("show");
});
function populateNewsResponseWindow(contentId){

var urlstr = 'showNewsResponseAction.action?responseContentId ='+contentId;
var browser1 = window.open(urlstr,"newsDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
browser1.focus();

}
</script>
</body>
</html>