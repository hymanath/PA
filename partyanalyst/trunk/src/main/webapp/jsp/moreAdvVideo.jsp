<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style>
</style>
<title>More Videos</title>
</head>
<body>

<div id="mainDiv" >
<div id="headingDiv" align="center"  style="margin-top: 10px; background-color: #06ABEA; height: 31px; color: white; font-family: verdana; padding-top: 10px; font-size: 21px; border-radius: 4px 4px 4px 4px;">More Videos</div>
<div align="center" style="margin-bottom: 10px; margin-top: 32px;"><b>From Date : </b> <input type="text" id="fromDate"  style="width: 110px;"  readonly="readonly"></input>
<b style="margin-left: 11px;">To Date : </b> <input type="text" id="toDate"   readonly="readonly" style="width: 110px;"></input>
<input type="button" class = "btn btn-info" value="Get" onClick="getVideosForSelectdDates();" style="margin-bottom: 10px; margin-left: 12px;"></input>
</div>
<div align="center" id="moreVideosDiv"  style="margin-top: 24px;"></div>
<div id="advVideoDialogDiv"></div>
</div>

<script>

$( document ).ready(function() {
  $('#fromDate').datepicker(
		{
			dateFormat: "yy-mm-dd",
			changeMonth: true,
			changeYear: true
			
		}
	);
	$('#toDate').datepicker(
		{
			dateFormat: "yy-mm-dd",
			changeMonth: true,
			changeYear: true
			
		}
	);
});
/*
This Method is used for openibg the calender for the date selection 
for getting the more videos from between dates
*/
/* function openCal(divId)
{
	$('#'+divId+'').datepicker(
		{
			dateFormat: "yy-mm-dd",
			changeMonth: true,
			changeYear: true
			
		}
	).datepicker().datepicker();
} */
/*
This Method is used for making the ajax cal for getting the videos for selected dates
*/
function getVideosForSelectdDates()
{
	var fromDate = $('#fromDate').val();
	var toDate   = $('#toDate').val();
	var jsObj=
	{ 
		fromDate: fromDate,
		toDate  : toDate,
		task    :"getVideosForSelection"
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getVideosForSelectionAction.action?"+rparam;
	ajaxResults(jsObj,url);
}
/*
This metod is used for handling all ajax calls
*/
function ajaxResults(jsObj,url)
{
	var myResults;

	var callback = {			
	success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);	
			if(jsObj.task =="getVideosForSelection")
			{
				openPouUpForVideo(myResults);
			}
			else if(jsObj.task =="advVideoDisplay")
			{
				openPouUpForSelectedVideo(myResults);
			}
			}catch (e) {
			 
			}  
   },
	   scope : this,
	   failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				 }
	   };

YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
/*
This method is used for building the videos getting from ajax call for selected dates
*/
function openPouUpForVideo(result)
{
	var str = '';
	str += '<div id="advVideoDiv" style="width: 600px; height: auto;">';
	str += '<div class="row" style="margin-bottom: 25px;">';
	var m = 0;
	for(var i in result)
	{	
	m=m+1;
	str += '<span style="margin-right: 21px;"><a  onClick="openAdvVidepForView('+result[i].advVideoId+')"><img  class="thumbnail" style="display: inline-block;width: 100px; height: 95px;cursor: pointer;" src="'+result[i].thumbnailUrl+'"/></a></span>';
	str += '<span style="margin-top: 109px; margin-left: -129px; position: absolute; width: 100px;">'+result[i].title+'</span>';
	if(m==3){
		m=0;
		str += '</div>';
		str += '<div class="row" style="margin-bottom: 25px;">';
		}	
		
	}
	str += '</div>';
	str += '</div>';
	$('#moreVideosDiv').html(str);
	
}
/*
This method is used for making the ajax cal to get the selected video details to display
*/
function openAdvVidepForView(id)
{
	var jsObj=
	{ 
		id:id,
		task:"advVideoDisplay"
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "advVideoAction.action?"+rparam;
	ajaxResults(jsObj,url);
}/*
This method is used for opening the popup and displaying the selected video
*/
function openPouUpForSelectedVideo(result)
{
	var str = '';
	///var vstr = '';
	//vstr += '<b style="color:seagreen">'+result.title+'</b>';
	//$('#myModalLabel').html(vstr);	
	str += '<div>';
	str += '<div  style="padding-left: 25px;">'+result.code+'</div>'; 
	str += '<div style="margin-top: 23px;"><b style="color:seagreen">Description : </b> '+result.description+'</div>';
	str += '<div><b style="color:seagreen">Tags : </b> '+result.tags+'</div>';
	//str += '<div><b>Duration : </b></div><div> '+result.duration+' Seconds</div>';
	str += '</div>';
	
	//$('#modelHeader').html(vstr);	
	$('#advVideoDialogDiv').html(str);
	
	//$('#myModal').model();
	 
	$('#advVideoDialogDiv').dialog({
		title  : ''+result.title+'',
		width  :500,
		height : 500
	}); 
}

</script>
</body>
</html>