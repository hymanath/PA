<!DOCTYPE html>
<html>
<head>
	<title> TDP News Portal </title>
	<meta name="" content="">
	
	<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->
	
    <script type="text/javascript" src="pagination/jquery.simplePagination.js"></script> 

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
</style>
<script>

$( document ).ready(function() {

	

 });
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
						<div class="span12 boxHeading"><h4>State News</h4></div>
					</div>				
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Latest News Updates </h4></div>
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
			<input type="button" value="Get News" id="selectedNewsDetBtn" onclick="getSelectedNewsDetails()" class="btn btn-info"/>
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
var galalryId = '${gallaryId}';
var categoryId = '${category}';
showAllFilesofAGallry(0,10,galalryId,1);

function showAllFilesofAGallry(startIndex , endIndex , gallaryId,selectedvalue)
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
	 data: {startIndex:startIndex,endIndex:endIndex,gallaryId:gallaryId,categoryId:categoryId,},
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,selectedvalue);
	 },
	  error:function() { 
	  }
	});

}


function buildFilesInGallaryDetails(results,selectedvalue)
{   
	$('#imageForMail').css("display","none");
var totalPages = Math.ceil(results[0].totalResultsCount / 10);
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
	if(source == "Eenadu Telugu")
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
				if(source == "Eenadu Telugu")
				{
					str+='<p class="span8 enadu">'+results[i].fileDescription1+'</p>';
				}
				else
				{
					str+='<p class="span8">'+results[i].fileDescription1+'</p>';
				}
				
			str+='</div>';
			str+='<div class="row-fluid m_top10">';
				str+='<div class="span9">';
					str+='<p class="text-error"><span style="font-weight:bold;">Source : </span> <span style="color:black;">'+results[i].fileType+'</span>';
					str+=' <span style="font-weight:bold;margin-left:55px;">Date : </span><span style="color:black;">'+results[i].fileDate+'</span></p>';
				str+='</div>';
				str+='<div class="span2 ">';
				
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
		startIndex = selectedvalue1*10+1;
	}

showAllFilesofAGallry(startIndex,endIndex,galalryId,selectedvalue)
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
		maxDate: new Date()
		
	}).datepicker("show");
});
</script>
</body>
</html>