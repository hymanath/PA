<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN PAGE</title>
</head>
<body>

<style>
	#mainBodyId{width:800px;margin-left:auto;margin-right:auto;}
	input[type="radio"],#attachFileId{margin-bottom:5px;margin-right:5px;}
	
	#mainBodyId legend,#saveAnnouncement{margin-left:140px;margin-bottom:5px;}
	.AnnouncementForm{border:1px solid #ccc;}
</style>

<div id="mainBodyId">
	<div>
		<div><a href="registerUser.action?userType=admin">Create User</a></div>
		<div><a href="createAnnouncementAction.action?userType=admin">Create Announcement</a></div>
		<div><a href="">Show Comment</a></div>
	</div>
	
	<div id="commentsDiv">
		<div>
			<input type="radio" name="comment" onClick="getAllComments(0,5,'getTotalComments');">All</input>
			<input type="radio" name="comment" onClick="getCommentsBetweenDates();">Between Dates</input>
		</div></br>
		<div id="btDatesMainDiv">
			<div id="betweenDatesDiv"></div></br>
			<div id="totalCommentsBtDates"></div>
		</div>
		<div id="totalCommentsMainDiv">
			<div id="totalCommentsList"></div>
		</div>
	</div>
	<div id="announcementForm" style="display:none;">
		<form class="form-horizontal AnnouncementForm" name="AnnouncementForm" action="createAnnouncementAction.action"  method="post" enctype="multipart/form-data">
				<legend>Announcement Form</legend>
				<div class="control-group">
					<label class="control-label requ" for="Title">Title</label>
					<div class="controls ">
						<input type="text" name="title" id="titleId" placeholder="Title"> 
					</div>
				</div>
				
				<div class="control-group ">
					<label class="control-label requ" for="Description">Description</label>
					<div class="controls">
						<textarea rows="4" id="descriptionId" name="description" placeholder="Description"></textarea>
					</div>
				</div>
				
				<div class="control-group ">
					<label class="control-label requ" for="announcementType">Announcement Type</label>
					<div class="controls">
						<input type="radio" name="announcementType" value="1" checked="checked">Notification</input>
						<input type="radio" name="announcementType" value="2">Press Release</input>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="date">Date</label>
					<div class="controls">
						<input type="text" id="datepicker" name="date" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="docs"></label>
					<div class="controls">
						<input type="checkbox" id="attachFileId" checked="checked" name="attachFile"> Attach File
					</div>
				</div>
				<div id="forFileId" class="thumbnail">
					<div class="control-group">
						<label class="control-label requ" for="FileTitle">Title</label>
						<div class="controls ">
							<input type="text" name="filetitle" id="fileTitleId" placeholder="File Title"> 
						</div>
					</div>
				
					<div class="control-group ">
						<label class="control-label requ" for="FileDescription">Description</label>
						<div class="controls">
							<textarea rows="2" id="fileDescriptionId" name="fileDescription" placeholder="File Description"></textarea>
						</div>
					</div>
				
					<div class="control-group">
						<label class="control-label" for="docs">Related Documents</label>
						<div class="controls">
							<input type="file" id="docs" class="thumbnail" name="docs"></div>
					</div>
				</div>
				<input type="submit" class="btn btn-primary" id="saveAnnouncement" value="submit"></input>
						
			</form>
	</div>
			
			
		</div>
</div>


<script>
	var startIndex = 0;
	var maxIndex   = 5;
	var stIndex    = 0;
	var enIndex    = 5;
	$(function(){	
		$("#datepicker").datepicker();
	});
	
	$('#saveAnnouncement').click(function(){
		document.userDetailsForm.submit();
	});
	
	$('input[name=attachFile]:checked').click(function(){
		$('#forFileId').toggle('fast');
	});
	
	function getAllComments(stIndex,enIndex,task)
	{
		$('#btDatesMainDiv').hide();
		$('#totalCommentsMainDiv').show();
		var jsObj =
		{  	
			startIndex  : stIndex,
			maxIndex    : enIndex,
			task        : task
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getCommentsByAdminAction.action?"+rparam;
		callAjaxForAdmin(jsObj, url);
	}
	
	function getCommentsBetweenDates()
	{
		$('#btDatesMainDiv').show();
		$('#totalCommentsMainDiv').hide();
		var str = "";
		str += 'Start Date : <input type="text" id="startDate" class="calender"></input>';
		str += 'End Date : <input type="text" id="endDate" class="calender"></input></br>';
		str += '<a class="btn btn-primary" onClick="getCommentsSelectdDates(startIndex,maxIndex,\'commentsBetweenDates\');">View</a>';
		$('#betweenDatesDiv').html(str);
		$('.calender').datepicker({ 
			dateFormat: 'yy-mm-dd' 
		});
	}

	function getCommentsSelectdDates(startIndex,maxIndex,task)
	{
		var startDate = $('#startDate').val();
		var endDate   = $('#endDate').val();
		var jsObj =
		{  	
			statrtDate  : startDate,
			endDate     : endDate,
			startIndex  : startIndex,
			maxIndex    : maxIndex,
			task        : task
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getCommentsByAdminAction.action?"+rparam;
		callAjaxForAdmin(jsObj, url);
	}
	
	function buildTotalCommentsListBtDates(myResults)
	{
		
		totalCount = myResults[0].total ;
		var str = "";
			for(var i in myResults)
			{
				str += '<div class="span10 widget"  style="border-radius: 4px 4px 4px 4px; border: 1px solid green; margin-bottom: 10px;">';
				str +='<span><b>Comment : </b></span><span>'+myResults[i].comment+'</span></br>';
				str +='<span style="float:left;"><b>Commented By : <b>'+myResults[i].name+'</span>';
				
				str +='<span style="float:right"><b>Date : </b>'+myResults[i].date+'</span></br>';
				str += '</div>';
				str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList();">More</a>'
			}
			$('#totalCommentsBtDates').append(str);
		if(maxIndex < totalCount)
		{
			startIndex = maxIndex;
			maxIndex   = maxIndex + 5;
			$('#moreButton').show();
		}
		else
		{
			$('#moreButton').hide();
		}
		
	}
	function getRemaingCommentsList()
	{
		getCommentsSelectdDates(startIndex,maxIndex,"commentsBetweenDates");
	}
	
	function buildTotalCommentsList(myResults)
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
				str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingTotalCommentsList();">More</a>'
			}
			$('#totalCommentsList').append(str);
		if(enIndex < totalCount)
		{
			stIndex  = enIndex;
			enIndex   = enIndex + 5;
			$('#moreButton').show();
		}
		else
		{
			$('#moreButton').hide();
		}
		
	}
	function getRemaingTotalCommentsList()
	{
		getAllComments(stIndex,enIndex,'getTotalComments');
	}
	function callAjaxForAdmin(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								
								if(jsObj.task =="commentsBetweenDates")
								{
									buildTotalCommentsListBtDates(myResults);
								}
								else if(jsObj.task =="getTotalComments")
								{
									buildTotalCommentsList(myResults);
								}
								/* else if(jsObj.task =="getRemaingComments")
								{
									buildRemainingCommentsList(myResults);
								} */
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