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
<div id="commentDis">
<span>Comment : </span><textarea rows="4" cols="50" style="width: 252px; height: 56px; margin-left: 10px;" id="commentText">
</textarea>
<input type="button" class="btn btn-primary" value="Comment" onClick="saveComment();"></input>
<div id="errorMsg"></div>
</div>
<div id="totalComments"></div>
<div><a class="btn btn-primary" onClick="getAllAnnoncements();">View All</a></div>
<div id="allNotificationDiv" align="center" style="overflow-y: scroll; height: 300px;"></div>
<script>
var myResults = window.opener.result;
var startIndex = 0;
var maxIndex = 5;
var totalCount = "";
var id = "";
if(myResults != null)
{
	var str = "";
	for(var i in myResults)
	{
		id = myResults[i].id;
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
function saveComment()
{
	var comment = $('#commentText').val();
	var jsObj =
		{  	
			id       : id,
			comment  : comment,
			task     : 'commentSave'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "commentSaveAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}
function getTop5Comments(id,startIndex,maxIndex,task)
{
	var jsObj =
		{  	
			id          : id,
			startIndex  : startIndex,
			maxIndex    : maxIndex,
			task        : task
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getCommentsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}
function buildTotalCommentsList(myResults)
{
	if(myResults != null)
	{
		totalCount = myResults[0].total;
		var str = "";
			for(var i in myResults)
			{
				str += '<div class="span10 widget"  style="border-radius: 4px 4px 4px 4px; border: 1px solid blue; margin-bottom: 10px;">';
				str +='<span><b>Comment : </b></span><span>'+myResults[i].comment+'</span></br>';
				str +='<span style="float:left;"><b>Commented By : <b>'+myResults[i].name+'</span>';
				
				str +='<span style="float:right"><b>Date : </b>'+myResults[i].date+'</span></br>';
				str += '</div>';
				str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
			}
			$('#totalComments').append(str);
		if(startIndex < totalCount)
		{
			startIndex = startIndex + maxIndex;
			maxIndex   =  maxIndex;
			$('#moreButton').show();
		}
		else
		{
			$('#moreButton').hide();
		}
	}
	else
	{
		$('#moreButton').hide();
	}
	
}
function getRemaingCommentsList()
{
	getTop5Comments(id,startIndex,maxIndex,"getTotalComments");
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
			else if(jsObj.task =="commentSave")
			{
				if(myResults.resultCode == 0)
				{
					$('#errorMsg').html('<b style="color:green">Comment Saved Successfully..</b>');
					getTop5Comments(id,startIndex,maxIndex,"getTotalComments");
				}
				else if(myResults == 'notLogged')
				{
					$('#errorMsg').html('<b style="color:red">Please Login To Comment..</b>');
				}
				else 
				{
					$('#errorMsg').html('<b style="color:red">Comment Not Saved Successfully?</b>');
				}
				
			}
			else if(jsObj.task =="getTotalComments")
			{
				buildTotalCommentsList(myResults);
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