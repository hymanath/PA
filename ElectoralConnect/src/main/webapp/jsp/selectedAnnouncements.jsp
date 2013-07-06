<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Announcemts</title>
</head>
<style>
.align_div{width:800px;margin-left:auto;margin-right:auto;}

.comment_sec{ border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;box-shadow: 1px 1px 1px -1px;margin-bottom: 10px;margin-left:100px;padding: 12px;}
	.title_sec2{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec3{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec4{ font-family: Helvetica;font-size: 13px;}
	.title_sec1{color: #0989C9; font-size: 14px;font-weight: bold;text-transform: uppercase;}
	.getallcomment1{margin-top: 8px;padding: 7px;background-color:#fff;border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;line-height: 24px;}
	a:hover{color:red;}
	.link{color:#548BD4;font-weight:bold;}
		.indiCommentId{margin:5px;}
		.commentblockinsected{  margin-left: 63px;margin-right: 0;margin-top: 58px;}
		.allblockinsected{margin-right: 108px;margin-top:-29px;}
		.description_font{font-family: Helvetica;}
</style>
<body>
<div class="well align_div getallcomment1">
<div id="notificationDiv"></div>
<div id="commentDis">
<h3>Comments</h3>
<div>
<textarea rows="4" placeholder="Post your comment here..." cols="50" style="width:598px; margin-left:71px;" id="commentText">
</textarea>
</div>
<div class="span9">
<input type="button"  class="btn btn-success pull-right" value="Post Comment" onClick="saveComment();"></input>
</div>
<div class="span3">
<div id="errorMsg"></div>
</div>
</div>
<div class="commentblockinsected">
<div id="totalComments"></div>
<div><a class="btn btn-primary pull-right allblockinsected" onClick="getAllAnnoncements();">View All</a></div>
<div id="allNotificationDiv" align="center" ></div>
</div>
</div>
<script>
var myResults = window.opener.result;
var startIndex = 0;
var maxIndex = 5;
var totalCount = "";
var id = "";
var allAnnouncements = "";
var announcenentTypeId = "";
if(myResults != null)
{
	var str = "";
	for(var i in myResults)
	{
		id = myResults[i].id;
		announcenentTypeId = myResults[i].announcementType;
		str +='<div class="comment_sec" style="width:550px;">';
			
			str +='<div class=" title_sec1">'+myResults[i].title+'</div>';
			str +='<div class="title_sec4">'+myResults[i].description+'</div>';
	        str +='<div ><span class="title_sec2">Date Posted:</span><span>'+myResults[i].dateString+' </span></div>';
			<!--str += '<b>Name : </b><span>'+myResults[i].name+'</span></br>';-->
			if(myResults[i].filePath != null && myResults[i].filePath != "")
			{
			   str +='<div ><span class="title_sec3">Releted Document:</span><s:a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileName+'</span></s:a></div>';
				
				<!--str += '<b>File Description : </b><span>'+myResults[i].fileDescription+'</span></br>';-->
				<!--str += '<b>File Date : </b><span>'+myResults[i].fileDate+'</span></br>';-->
			}
			
			str +='</div>';
	}
	$('#notificationDiv').html(str);
}

<!--


-->

function getAllAnnoncements()
{
	var jsObj =
		{  	
			announcenentTypeId : announcenentTypeId,
			task               : "getAllAnnouncements"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getTopAnnouncementsAction.action?"+rparam;
		callAjaxForComments(jsObj, url);
}
function buildAllAnnouncements(myResults)
{
	if(myResults != null)
	{
		allAnnouncements = myResults;
		window.open('allAnnouncementsDisplayAction.action');
		/* var str = "";
		for(var i in myResults)
		{
			str += '<div></br>';
			str += '<b>Title : </b><span>'+myResults[i].title+'</span></br>';
			str += '<b>Description : </b><span>'+myResults[i].description+'</span></br>';
			str += '<b>Date : </b><span>'+myResults[i].dateString+'</span></br>';
			str += '<b>Name : </b><span>'+myResults[i].name+'</span></br>';
			if(myResults[i].filePath =! null && myResults[i].filePath != "")
			{
				str += '<b>File Title : </b><a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileTitle+'</span></a></br>';
				str += '<b>File Description : </b><span>'+myResults[i].fileDescription+'</span></br>';
				str += '<b>File Date : </b><span>'+myResults[i].fileDate+'</span></br>';
			}
		   str += '</div>';
	   } */
	}
	//$('#allNotificationDiv').html(str);
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
				str += '<div class="thumbnail indiCommentId" style="width:609px;">';
				str +='<span class="description_font">'+myResults[i].comment+'</span></br></br>';
				str +='<span class="pull-left label label-important">'+myResults[i].name+'</span>';
				str +='<span class="pull-right ">'+myResults[i].commentedTime+'</span></br>';
				str += '</div>';
				
			}
			str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
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
getTop5Comments(id,startIndex,maxIndex,"getTotalComments");
</script>
</body>
</html>