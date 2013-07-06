<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN PAGE</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<link rel="stylesheet" type="text/css" href="css/dataPicker.css"/>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script type="text/javascript"  src="js/RegisterFormValidation.js"></script>
<script type="text/javascript"  src="js/AnnouncementFormValidation.js"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>

<style>
	#mainBodyId{width:800px;margin-left:auto;margin-right:auto;}
	input[type="radio"],#attachFileId{margin-bottom:5px;margin-right:5px;}
	
	#saveAnnouncement{margin-left:300px;margin-bottom:10px;margin-top:10px;}
	.AnnouncementForm{ margin-top: 5px;}
	.widget{background:#fcfcfc;  height:100px;border:1px solid #2DCFFE;}
	.widget:hover{box-shadow:0px 0px 15px #c0c0c0;}
	 h3{font-family: 'Open Sans';font-size:15px;line-height:20px;text-align: center;font-weight:bold;}
	 .user{margin-left: -26px; margin-top:40px;}
	 .announ{margin-left: -34px; margin-top: 29px;}
	 .comment{margin-left: -18px; margin-top: 30px;}
	     .allannoun{margin-left: -32px; margin-top: 29px;}
		 .row_border{ border-bottom: 1px solid #c0c0c0;
    margin-left: 0}
	.header{ font-family: open sans;
    font-size: 25px;
    font-weight: bold;
    text-align: center;}
	.align{ margin-left: 90px;
   margin-top:7px;}
	.t_align{text-align:center;}
	#forFileId{width:661px;margin-bottom:10px;}
	#statusMessage{margin:10px;}
	.comment_sec{ border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;box-shadow: 1px 1px 1px -1px;margin-bottom: 10px;margin-left: 29px;padding: 12px;}
	.label1 {background: none repeat scroll 0 0 #3A87AD;color:#fff;font-weight:bold;padding:1px;}
	.title_sec{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec1{color: #0989C9; font-size: 14px;font-weight: bold;text-transform: uppercase;}
	.posted_sec{ font-family: Verdana;font-size: 16px;font-weight: bold;margin-left: 6px;text-align: left;}
	.AnnouncementForm{ margin-top: 5px;padding: 7px; border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;}
	.getallcomment{margin-top: 8px;padding: 7px;display: none;background-color:#fff;border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;}
	#totalCommentsBtDates{display:inline-block;}


</style>

<div id="mainBodyId" class="well">
	<!--<div>
		<div><a href="registerUser.action?userType=admin">Create User</a></div>
		<div><a href="#" id="createAnnouncementId">Create Announcement</a></div>
		<div><a href="">Show Comment</a></div>
	</div>-->
	
	 <div class="row-fluid">
	<div class="span12">
	    <div class="span3 widget active" id="user" >
		<div class="row-fluid">
		<a id="CareId1" href="javascript:{};">
		<div class="span6">
		<img src="image/06.jpg" style="width: 79px;"  />
		</div>
		<div class="span6">
		<h3 class="user">Create User</h3>
		</div>
		</div>
		</a>
		 
		</div>
	      <div class="span3 widget" id="announ" >
		<div class="row-fluid">
		<a id="CareId2" href="javascript:{};">
		<div class="span6">
		<img src="image/06.jpg" style="width: 79px;"  />
		</div>
		<div class="span6">
		<h3 class="announ">Create Announcement</h3>
		</div>
		</div>
		</a>
		 
		</div>
	   <div class="span3 widget" id="comment">
		<div class="row-fluid">
		<a id="CareId3" href="javascript:{};">
		<div class="span6">
		<img src="image/06.jpg" style="width: 79px;"  />
		</div>
		<div class="span6">
		<h3 class="comment getCmmnts">Get All Comments</h3>
		</div>
		</div>
		</a>
		 
		</div>
		   <div class="span3 widget" id="allannoun" >
		<div class="row-fluid">
		<a id="CareId4" href="javascript:{};">
		<div class="span6">
		<img src="image/06.jpg" style="width: 79px;"  />
		</div>
		<div class="span6">
		<h3 class="allannoun">Get All Announcement</h3>
		</div>
		</div>
		</a>
		 
		</div>
	</div>
	</div>
	<div id="BOX-1" style="display: show" >
			<div id="formDivId">
			<div class="control-group" style="color:red;">
					<s:actionerror />
					<s:fielderror />
				</div>
		   <form class="form-horizontal" name='userDetailsForm' action="adminRegisterSaveAction.action" method="post">
				<legend>Personal Information</legend>
				<input type="hidden" value="admin" name="userType"></input>
				<input type="hidden" value="1" name="type"></input>
				<div class="control-group">
					<label class="control-label requ" for="firstName">First Name</label>
					<div class="controls ">
						<input type="text" name="firstName" id="firstNameId" placeholder="First Name"> 
					</div>	
				</div>
				
				<div class="control-group ">
					<label class="control-label requ" for="lastName">Last Name</label>
					<div class="controls">
						<input type="text" id="lastNameId" name="lastName" placeholder="Last Name">
					</div>			
				</div>
				
				<div class="control-group ">
					<label class="control-label requ" for="emailid">Email-Id</label>
					<div class="controls ">
						<input type="text" id="emailId" name="emailId" placeholder="Email-Id">
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="mobileno">Mobile Number</label>
					<div class="controls">
						<input type="text" id="mobileId" name="mobileNo" placeholder="Mobile Number"><div></div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="epicNo">EPIC Number</label>
					<div class="controls">
						<input type="text" id="epicNoId" name="epicId" placeholder="EPIC Number">
					</div>
				</div>
				
			<!--	<div id="userType"><input type="hidden" id="userType" name="userType" value="${userType}"></input></div>-->
				
				${resultStr}
			</form>
			
		
			<button class="btn btn-primary" id="saveUser">Save changes</button>
		
	
		<div id="successMsg"></div>
		
		<span style="float:right;display:none;"><a class="btn btn-primary" id="changePassword" >Change Password</a><span>
		
	
	
	</div>
			</div>
	<div id="commentsDiv">
	<div id="BOX-3" class="getallcommentsBlock" style="display:none;">
		<div class="t_align_sec" style="display:inline-table;">
			<input type="radio" name="comment" value="all" id="forCommentsAllId" checked="checked" class="commentsBlock">All</input>
			<input type="radio" name="comment" value="betweenDates" class="commentsBlock">Between Dates</input>
		</div></br>
		<div id="btDatesMainDiv">
			<div id="betweenDatesDiv"></div></br>
			<div id="totalCommentsBtDates"></div>
		</div>
		<div id="totalCommentsMainDiv" style="display:inline-table" >
			<div id="totalCommentsList"></div>
		</div>
		<div id="pagedCommentsId" style="display:inline-block"></div>
						<!----pagination Div----->
			<div class="span12 text-center">
				<div id="paginationAnnouncementId"></div>
			</div>
		</div>
	</div>
	</div>
	<div id="announcementForm" >
	<div id="BOX-2" style="display: none;background-color:#fff;">
		<form class="form-horizontal AnnouncementForm" name="AnnouncementForm" action="createAnnouncementAction.action?fromForm=announcement"  method="post" enctype="multipart/form-data">
				<input type="hidden" value="admin" name="isAdmin"></input>
				<input type="hidden" value="2" name="type"></input>
				<legend>Announcement Form</legend>
				<div class="align thumbnail">
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
						<input type="text" id="datepicker" name="date" readonly="true"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="docs"></label>
					<div class="controls">
						<input type="checkbox" id="attachFileId" checked="checked" name="attachFile"> Attach File
					</div>
				</div>
				</div>
				
				<div id="forFileId" class=" thumbnail align">
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
				<div class="control-group">
						
						<div class="controls">
							<span id="statusMessage">${resultString}</span>
						</div>
				
				<input type="submit" class="btn btn-primary " id="saveAnnouncement" value="submit"></input>
						
			</form>
	</div>
			</div>
			<div id="BOX-4" style="display: none">
			<div id="pagedAnnouncementsId"  class="getallcomment" style="display:inline-block"></div>
						<!----pagination Div----->
						<div class="span12 text-center">
							<div id="paginationId"></div>
						</div>
			</div>
			
		</div>



<script>
	var startIndex = 0;
	var maxIndex   = 5;
	var stIndex    = 0;
	var enIndex    = 5;
	var announcementData = "";
	
	$('.getCmmnts').click(function(){
			getAllCommentsPagination(0);
	});

	
	$('.commentsBlock').click(function(){
			var value=$('input:radio[name=comment]:checked').val();
			if(value=="all"){
				getAllCommentsPagination(0);
			}
			else{
				getCommentsBetweenDates();
			}
			
	});
	
	$(function(){	
		$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
	});
	
	$('#createAnnouncementId').click(function(){
		$('#announcementForm').css('display','block');
	});
	
	$('#saveAnnouncement').click(function(){
		 $('[name="AnnouncementForm"]').validate();
		if ( $('[name="AnnouncementForm"]').valid())
		document.AnnouncementForm.submit();
	});
	
	$('input[name=attachFile]:checked').click(function(){
		$('#forFileId').toggle('fast');
	});
	
	function getPaginationForData(pageNo){
	var jsObj={
			startRecord:pageNo,
			maxRecord:10,
			task:"getAllAnnouncements"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getAllAnnouncementsForAdmin.action?"+rparam;
		callAjaxForAdmin(jsObj, url);
	}
	
	$('#allannoun').click(function(){getPaginationForData(0)});
		
	function getAllCommentsPagination(stIndex)
	{
		$('#btDatesMainDiv').hide();
		$('#totalCommentsMainDiv').show();
		var jsObj =
		{  	
			startIndex  : stIndex,
			maxIndex    : 10,
			task        : "getTotalComments"
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
		 str +='<div class=t_align>';
		 str +='<form class="form-inline">';
		 str +='<label>Start Date:&nbsp;&nbsp;</label>';
		 str += '<input type="text" id="startDate" class="calender input-medium"></input>';
		 str +='<label>End Date:&nbsp;&nbsp;</label>';
		 str += '<input type="text" id="endDate" class="calender input-medium"></input>';
		 str +='<span style="margin-left:5px">';
		 str += '<a class="btn btn-primary" onClick="getCommentsSelectdDates(0);">View</a>';
		 str +='</span>';
		 str +='</form>';
		 str +='</div>';
   		 $('#betweenDatesDiv').html(str);
		 
		  $( "#startDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			dateFormat: 'yy-mm-dd',
			onClose: function( selectedDate ) {
			$( "#endDate" ).datepicker( "option", "minDate", selectedDate );
			}
		   });
			
		 $( "#endDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			dateFormat: 'yy-mm-dd',
			onClose: function( selectedDate ) {
				$( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
				}
			});
		
	}

	function getCommentsSelectdDates(startIndex)
	{
		var startDate = $('#startDate').val();
		var endDate   = $('#endDate').val();
		var jsObj =
		{  	
			statrtDate  : startDate,
			endDate     : endDate,
			startIndex  : startIndex,
			maxIndex    : 10,
			task        : "commentsBetweenDates"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getCommentsByAdminAction.action?"+rparam;
		callAjaxForAdmin(jsObj, url);
	}
	

  function buildAllCommentsList(myResults,jsObj)
	{
		//alert('in123');
		$("#totalCommentsBtDates").css("display","block");
		$("#totalCommentsBtDates").html('');
		if(myResults != null)
		{
			totalCount = myResults[0].total ;
			var str = "";
			for(var i in myResults)
			{
			str += '<div id="commentsDIv'+myResults[i].commentId+'">';
			str +='<div class="comment_sec span7">';
			if(myResults[i].abused.toLowerCase() == "no")
			{
			str += '<a style="float: right;" id="abusedButton'+myResults[i].commentId+'" onClick="abuseComment('+myResults[i].commentId+');" ><img src="img/error.png"></a>';
			}
			str +='<span class="title_sec">TITLE:</span><span class="title_sec1">'+myResults[i].announcement+'</span>';
			str +='<hr style="margin-top:0px;">';
			str +='<span style="font-size: 15px;">'+myResults[i].comment+'</span>';
			str +='<hr style="margin-top:0px;">';
			str +=' <span class="label1 label-info">Posted By:</span>';
			str +='<span class="posted_sec">'+myResults[i].name+'</span>';
			str +='<span class="pull-right"><b>Date : </b>'+myResults[i].date+'</span></div>';


			str += '<div id="abusedStatus'+myResults[i].commentId+'"></div>';

			str +='</div>';
			str +='</div>';
			str += '<a id="moreButton" style="display:none;margin-top:5px;" class="btn btn-primary pull-right" onClick="getRemaingTotalCommentsList();">More</a>'

			}
		  var itemsCount=totalCount;
		  var maxResults=jsObj.maxIndex;
	     str+="</ul>";
	    $("#pagedCommentsId").html(str);
	    if(jsObj.startIndex==0){
		  $("#paginationAnnouncementId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getAllCommentsPagination(num);
				
			}
		});
		
	  }	
  
	}
 }

	function buildTotalCommentsListBtDates(myResults)
	{
		alert('in');
		if(myResults != null)
		{
			totalCount = myResults[0].total ;
			var str = "";
			for(var i in myResults)
			{
			str += '<div id="commentsDIv'+myResults[i].commentId+'">';
			str +='<div class="comment_sec span7">';
			if(myResults[i].abused.toLowerCase() == "no")
			{
			str += '<a style="float: right;" id="abusedButton'+myResults[i].commentId+'" onClick="abuseComment('+myResults[i].commentId+');" ><img src="img/error.png"></a>';
			}
			str +='<span class="title_sec">TITLE:</span><span class="title_sec1">'+myResults[i].announcement+'</span>';
			str +='<hr style="margin-top:0px;">';
			str +='<span style="font-size: 15px;">'+myResults[i].comment+'</span>';
			str +='<hr style="margin-top:0px;">';
			str +=' <span class="label1 label-info">Posted By:</span>';
			str +='<span class="posted_sec">'+myResults[i].name+'</span>';
			str +='<span class="pull-right"><b>Date : </b>'+myResults[i].date+'</span></div>';


			str += '<div id="abusedStatus'+myResults[i].commentId+'"></div>';

			str +='</div>';
			str +='</div>';
			str += '<a id="moreButton" style="display:none;margin-top:5px;" class="btn btn-primary pull-right" onClick="getRemaingTotalCommentsList();">More</a>'

			}
			$('#totalCommentsBtDates').append(str);
			if(startIndex <= totalCount)
			{
				startIndex = startIndex + maxIndex;
				maxIndex   = maxIndex;
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
		getCommentsSelectdDates(startIndex);
	}
	
	function buildTotalCommentsListBetweenDates(myResults,jsObj)
	{
		if(myResults != null)
		{
			totalCount = myResults[0].total;
			var str = "";
			for(var i in myResults)
			{
			str += '<div id="commentsDIv'+myResults[i].commentId+'">';
			str +='<div class="comment_sec span7">';
			if(myResults[i].abused.toLowerCase() == "no")
			{
			str += '<a style="float: right;" id="abusedButton'+myResults[i].commentId+'" onClick="abuseComment('+myResults[i].commentId+');" ><img src="img/error.png"></a>';
			}
			str +='<span class="title_sec">TITLE:</span><span class="title_sec1">'+myResults[i].announcement+'</span>';
			str +='<hr style="margin-top:0px;">';
			str +='<span style="font-size: 15px;">'+myResults[i].comment+'</span>';
			str +='<hr style="margin-top:0px;">';
			str +=' <span class="label1 label-info">Posted By:</span>';
			str +='<span class="posted_sec">'+myResults[i].name+'</span>';
			str +='<span class="pull-right"><b>Date : </b>'+myResults[i].commentedTime+'</span></div>';


			str += '<div id="abusedStatus'+myResults[i].commentId+'"></div>';

			str +='</div>';
			str +='</div>';
			str += '<a id="moreButton" style="display:none;margin-top:5px;" class="btn btn-primary pull-right" onClick="getRemaingTotalCommentsList();">More</a>'

			}
		var itemsCount=totalCount;
	    var maxResults=jsObj.maxIndex;
	    str+="</ul>";
	    $("#pagedCommentsId").html(str);
	     if(jsObj.startIndex==0){
		   $("#paginationAnnouncementId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getAllCommentsPagination(num);
				
			}
		});
		
	  }

   	}
		
		
	}
	function getRemaingTotalCommentsList()
	{
		getAllCommentsPagination(stIndex);
	}
	
	function abuseComment(id)
	{
		var jsObj =
		{  	
			id   : id,
			task : "abuseComment"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "abuseCommentAction.action?"+rparam;
		callAjaxForAdmin(jsObj, url);
	}
	function callAjaxForAdmin(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								
								if(jsObj.task =="commentsBetweenDates")
								{
									buildTotalCommentsListBetweenDates(myResults,jsObj);
								}
								else if(jsObj.task =="getTotalComments")
								{
									//buildTotalCommentsList(myResults,"totalCommentsList",0);
									buildAllCommentsList(myResults,jsObj);
								}
								else if(jsObj.task =="getAllAnnouncements")
								{
									buildAllAnnouncements(myResults,jsObj);
								}
								else if(jsObj.task =="abuseComment")
								{
									if(myResults.resultCode == 0)
									{
										$("#abusedStatus"+jsObj.id+"").html("<b style='color:green'>Abused Successfull..</b>");
										$("#commentsDIv"+jsObj.id+"").hide();
									}
									
								} 
								else if(jsObj.task =="getAllAnnouncementsByAnnounFileId")
								{
									if(myResults != null)
									{
										announcementData = myResults;
										window.open('editAnnouncementAction.action');
									}
								}
								else if(jsObj.task =="deleteAnnouncement")
								{
									if(myResults.resultCode == 0)
									{
										$('#statusMsg'+jsObj.announcementId+'').html('<b style="color:green">Deleted Successfully</b>');
										$('#announcemtsDiv'+jsObj.announcementId+'').hide();										
									}
									else
									{
										$('#statusMsg'+jsObj.announcementId+'').html('<b style="color:red">Error Occured While Deleteing The Announcement</b>');
									}
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
	
	$('#saveUser').click(function(){
		//document.userDetailsForm.submit();
		$(".form-horizontal").validate();
		
		if ($('.form-horizontal').valid()){
			document.userDetailsForm.submit();
		}
	});
	
	function buildAllAnnouncements(results,jsObj){
	
	var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
	  str +='<div class="comment_sec span7" id="announcemtsDiv'+results[i].announcementId+'">';
		str+="<li>";
		
		str+="<span class='title_sec1' >"+results[i].name+"</span>";
		str+= "<div style='float:right;'><a class='icon-pencil' onClick='editAnnouncement("+results[i].announcementFileId+")' style='cursor: pointer; margin-right: 7px;'></a>";
		str+= "<a class='icon-remove-sign' onClick='deleteAnnouncement("+results[i].announcementId+")' style='cursor: pointer;'></a></div>";
		  str +='<hr style="margin-top:0px;">';
		str+="<div class='row-fluid'>";
		<!--str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}'>";
		<!--str+="<img id='myImg' style='width:100%' src="+results[i].displayImagePath+" onerror='imgError(this)'></a>";-->
		str+="<span style='font-size: 15px;'>"+results[i].description+"</p>";
		  str +='<hr style="margin-top:0px;">';
		str+="</div>";
		str+="<span class='text-error'>Date: "+results[i].dateString+"</span>";
		<!--str+="<div class='span2'>"+results[i].announcementTypeName+"</div></li>";-->
		str+="<span class='pull-right'>"+results[i].updatedBy+"</span>";
		str += "<div id='statusMsg"+results[i].announcementId+"'></div>";
		 str +='</div>';
		
		str+="</li>";
		 str +='</div>';
	}
	
	var itemsCount=results[0].allAnnouncementsCount;
	var maxResults=jsObj.maxRecord;
	str+="</ul>";
	$("#pagedAnnouncementsId").html(str);
	if(jsObj.startRecord==0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getPaginationForData(num);
				
			}
		});
		
	}
	}

	function editAnnouncement(announcementId)
	{
		var jsObj =
			{  	
				announcenentTypeId : announcementId,
				task               : "getAllAnnouncementsByAnnounFileId"
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "getTopAnnouncementsAction.action?"+rparam;
			callAjaxForAdmin(jsObj, url);
	}

	function deleteAnnouncement(announcementId)
	{
		var jsObj =
			{  	
				announcementId : announcementId,
				task           : "deleteAnnouncement"
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "deleteAnnouncementAction.action?"+rparam;
			callAjaxForAdmin(jsObj, url);
	}
	$(document).ready(function(){

					var type = '${type}';
					if(type == 1)
					{
						$("#BOX-1").show();
						$("#BOX-2").hide();
					}
					else if(type == 2)
					{
						$("#BOX-1").hide();
						$("#BOX-2").show();
					}
				   $("#CareId1").click(function(){
				        if(document.getElementById("BOX-1").style.display == "none"){
						   $("#BOX-1").show();
						   $("#user").addClass("selected");
						   
						}else{
						  $("#BOX-1").hide();
						  $("#user").removeClass("selected");
						}
						 $("#BOX-2").hide();
						 $("#BOX-3").hide();
						 $("#BOX-4").hide();
						 
						  $("#announ").removeClass("selected");
						   $("#comment").removeClass("selected");
						    $("#allannoun").removeClass("selected");
							
				   });
				    $("#CareId2").click(function(){
				        if(document.getElementById("BOX-2").style.display == "none"){
						   $("#BOX-2").show();
						   $("#announ").addClass("selected");
						   
						}else{
						  $("#BOX-2").hide();
						   $("#announ").removeClass("selected");
						}
						 $("#BOX-1").hide();
						 $("#BOX-3").hide();
						 $("#BOX-4").hide();
						
						 $("#user").removeClass("selected");
						   $("#comment").removeClass("selected");
						    $("#allannoun").removeClass("selected");
							 
				   });
				    $("#CareId3").click(function(){
				        if(document.getElementById("BOX-3").style.display == "none"){
						   $("#BOX-3").show();
						   $("#comment").addClass("selected");
						}else{
						  $("#BOX-3").hide();
						   $("#comment").removeClass("selected");
						}
						 $("#BOX-1").hide();
						 $("#BOX-2").hide();
						 $("#BOX-4").hide();
						 
						 $("#user").removeClass("selected");
						   $("#announ").removeClass("selected");
						    $("#allannoun").removeClass("selected");
							 
				   });
				    $("#CareId4").click(function(){
				        if(document.getElementById("BOX-4").style.display == "none"){
						   $("#BOX-4").show();
						   $("#allannoun").addClass("selected");
						    
						}else{
						  $("#BOX-4").hide();
						  $("#allannoun").removeClass("selected");
						}
						 $("#BOX-1").hide();
						 $("#BOX-2").hide();
						 $("#BOX-3").hide();
						 
						 $("#user").removeClass("selected");
						   $("#announ").removeClass("selected");
						    $("#comment").removeClass("selected");
							 
				   });
				 });
				
		<c:if test="${fromForm=='announcement'}">
			getAnnouncementForm();
			<c:if test="${resultString=='SUCCESS'}">
				$('#statusMessage').html("Uploaded SuccessFully");
				$('#statusMessage').css("color","green");
			</c:if>
			<c:if test="${resultString=='FAILURE'}">
				$('#statusMessage').html("Sorry Failure Occur..Please upload later");
				$('#statusMessage').css("color","red");
			</c:if>
		</c:if>
			function getAnnouncementForm(){
				if(document.getElementById("BOX-2").style.display == "none"){
						   $("#BOX-2").show();
						   $("#announ").addClass("selected");
						   
						}else{
						  $("#BOX-2").hide();
						   $("#announ").removeClass("selected");
						}
						 $("#BOX-1").hide();
						 $("#BOX-3").hide();
						 $("#BOX-4").hide();
						
						 $("#user").removeClass("selected");
						 $("#comment").removeClass("selected");
						 $("#allannoun").removeClass("selected");
			}		
				
				
</script>
</body>
</html>