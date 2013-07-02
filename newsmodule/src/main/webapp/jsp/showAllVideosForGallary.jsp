<!DOCTYPE html>
<html>
<head>
	<title> TDP News Portal </title>
	<meta name="" content="">
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	
    <link href="styles/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="styles/css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>


	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
	 
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<style type="text/css">
#existingFromText,#existingToText{width:155px;cursor: text;}
#errorMsgDiv{font-size:12px;}
</style>
</head>
<body>
		<!----Container---->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<div class="span2">
				 	<div id="mainDiv"></div>
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>state news</h4></div>
						
					</div>				
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Latest Updates News</h4></div>
						<div class="span12" id="newsDisplayDiv">
						<div id="pagedNewsId"></div>
						<div class="span12 text-center">
							<div id="paginationId"></div>
						</div>
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
								</li> -->


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
	
	<div id="video_dialog">
		<div id="videos"></div>
	</div>
		
<script>
var galId = '${gallaryId}';
//showAllFilesofAGallry(0,10,galalryId,1);

getNewsForPagination(0)

function getNewsForPagination(num){
	var jsObj =
		{  	
			galId:galId,
			task:'getVideosForGallery',
			startingRecord:num,
			maxRecords:10
			
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getVideosOfGalleryId.action?"+rparam;
		callAjax(jsObj, url);
}

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="getVideosForGallery")
								{
									buildAllVideos(myResults,jsObj);
								}
								}catch (e) {
							     console.log(e);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	
	function buildAllVideos(results,jsObj){

	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		str+="<li>";
		str+="<h4>"+results[i].title+"</h4>";
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}' onclick=getVideo('"+results[i].path+"')>";
		str+="<img id='myImg' style='width:100%' src=http://img.youtube.com/vi/"+results[i].path+"/1.jpg onerror='imgError(this)'></a>";
		str+="<p class='span8'>"+results[i].description+"</p>";
		str+="</div>";
		//str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].ids+")' class='btn btn-mini btn-info pull-right' type='button'>More</a></div></li>";
	}
	
	var itemsCount=results[0].count;
	var maxResults=jsObj.maxRecords;
	str+="</ul>";
	$("#pagedNewsId").html(str);
	
	if(jsObj.startingRecord==0){
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

function getVideo(path){
	$("#video_dialog").dialog({
				resizable:false,
				title:'videos',
				height: 500,
				width:580,
				top:250,
				left:100,
				modal: true
				
	});
		var str = "";
		str += '<iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe></div>';
		$('#videos').html(str);
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