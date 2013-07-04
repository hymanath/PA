<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Announcemts</title>
</head>
<body>
<div id="notificationDiv" align="center"></div>
<div><a class="btn btn-primary" onClick="getAllAnnoncements();">View All</a></div>
<div id="allNotificationDiv" align="center" style="overflow-y: scroll; height: 300px;"></div>
<script>
var myResults = window.opener.result;

if(myResults != null)
{
	var str = "";
	for(var i in myResults)
	{
		str += '<div></br>';
		str += '<b>Title : </b><span>'+myResults[i].title+'</span></br>';
		str += '<b>Description : </b><span>'+myResults[i].description+'</span></br>';
		str += '<b>Date : </b><span>'+myResults[i].date+'</span></br>';
		str += '<b>Name : </b><span>'+myResults[i].name+'</span></br>';
		if(myResults[i].filePath =! null && myResults[i].filePath != "")
		{
			str += '<b>File Title : </b><a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileTitle+'</span></a></br>';
			str += '<b>File Description : </b><span>'+myResults[i].fileDescription+'</span></br>';
			str += '<b>File Date : </b><span>'+myResults[i].fileDate+'</span></br>';
		}
		str += '</div>';
	}
	$('#notificationDiv').html(str);
}

function getAllAnnoncements()
{
	var jsObj =
		{  	
			task           : "getAllAnnouncements"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getTopAnnouncementsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}
function buildAllAnnouncements(myResults)
{
	if(myResults != null)
	{
		var str = "";
		for(var i in myResults)
		{
			str += '<div></br>';
			str += '<b>Title : </b><span>'+myResults[i].title+'</span></br>';
			str += '<b>Description : </b><span>'+myResults[i].description+'</span></br>';
			str += '<b>Date : </b><span>'+myResults[i].date+'</span></br>';
			str += '<b>Name : </b><span>'+myResults[i].name+'</span></br>';
			if(myResults[i].filePath =! null && myResults[i].filePath != "")
			{
				str += '<b>File Title : </b><a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileTitle+'</span></a></br>';
				str += '<b>File Description : </b><span>'+myResults[i].fileDescription+'</span></br>';
				str += '<b>File Date : </b><span>'+myResults[i].fileDate+'</span></br>';
			}
		   str += '</div>';
	   }
	}
	$('#allNotificationDiv').html(str);
}
function callAjaxForComments(jsObj,url)
{
	 var myResults;

	 var callback = {			
	   success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			if(jsObj.task =="getAllAnnouncements")
			{
				buildAllAnnouncements(myResults);
			}
			}catch (e) {
			 
			}  
   },
   scope : this,
   failure : function( o ) {
			
			 }
   };

	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
</script>
</body>
</html>