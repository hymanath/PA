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
	.t_align_sec{text-align:center;padding:5px;}
	.font_desc{font-size:15px;font-family:helvetica;line-height:21px;}
	#saveAnnouncement{margin-left:300px;margin-bottom:10px;margin-top:10px;}
	.AnnouncementForm{ margin-top: 5px;}
	.widget{background:#fcfcfc;  height:100px;border:1px solid #2DCFFE;}
	.widget:hover{box-shadow:0px 0px 15px #c0c0c0;}
	 h3{font-family: 'Open Sans';font-size:15px;line-height:20px;text-align: center;font-weight:bold;color:#6B6265;}
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
	.align{ width:630px;margin-left: 90px;
   margin-top:7px;}
	.t_align{text-align:center;}
	#forFileId{width:630px;margin-bottom:10px;}
	#statusMessage{margin:10px;}
	.comment_sec{border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;box-shadow: 1px 1px 1px -1px;margin-bottom: 10px;margin-left:15px;padding: 12px;}
	.label1 {background: none repeat scroll 0 0 #3A87AD;color:#fff;font-weight:bold;padding:1px;}
	.title_sec{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec1{color: #0989C9; font-size: 14px;font-weight: bold;text-transform: uppercase;}
	.posted_sec{ font-family: Verdana;font-size: 16px;font-weight: bold;margin-left: 6px;text-align: left;}
	.AnnouncementForm{ margin-top: 5px;padding: 7px; border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;}
	.getallcomment{margin-top: 8px;padding: 7px;display: none;background-color:#fff;border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;}
	#totalCommentsBtDates{display:inline-block;}
	.getallcommentsBlock{margin-top: 8px;padding: 7px;background-color:#fff;border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;}
   
	.notifications a{color:#1a1a1a; text-decoration:none;}
			
			
			.hero-unit{height:60px;}
			
			.notifications a{color:#1a1a1a; text-decoration:none;}
			
			.title h1{font-size:14px; line-height:18px;color:1a1a1a; padding:5px;background:#fff; border-bottom:1px solid #c0c0c0;}
			.title p{font-size:12px; line-height:21px;padding:5px;background:#fff;min-height:40px;}
			
			.title{ margin-left: 65px !important;margin-top: 13px;}
			.title p{margin-top:-9px;}
			.date {font-size:12px;font-weight:bold;color:#2DCFFE; float:left;}
			
			.menu:hover>a>h3{color:#ffffff;}
			.menu:hover>a{text-decoration:none;}
			.menu:hover{background:#41B7D8;}
			.active{background:#41B7D8;color:#ffffff;}
			.active>a>h3{color:#ffffff;}
			.indiComment{margin:4px;padding:10px;}
		#indiCommentName{margin:2px;}
		#indiCommentDate{margin:2px;color:#28B0D6;}
			
	
</style>

<div id="mainBodyId" class="container well">
	
	<!--<div class="row-fluid">
        <div class=" white-panel no-padding bar">
            <ul class="nav nav-tabs" class="">
                <li class="span3"><a class="gc-icon agency-icon" data-toggle="tab" href="#home"><i class="icon-search"></i>Create User</a></li>
                <li class=" span3 "><a class="gc-icon enterprise-icon" data-toggle="tab" href="#profile"><i class="icon-search"></i>Create Announcement</a></li>
                <li class="span3"><a class="gc-icon higher-ed-icon" data-toggle="tab" href="#messages"><i class="icon-search"></i>Create Announcement</a></li>
                <li class="span3"><a class="gc-icon gov-icon" data-toggle="tab" href="#settings"><i class="icon-search"></i>Get All Comments</a></li>
            </ul> <!-- End tabs

        
        </div>




    </div>-->
	
	   <div class="row-fluid">
	      <div class="span12">
		   <div class="span3 breadcrumb  thumbnail menu active"  id="user">
		  
		    <a id="CareId1" href="javascript:{};">
			
		    <img src="image/cancel_vote.png" style="width: 90px; margin-right: 0px; margin-left: 40px;" />
			
			
			<h3>Create User</h3>
			</a>
             		
		   </div>
		   
		   <div class="span3 breadcrumb thumbnail menu " id="announ">
		<a id="CareId2" href="javascript:{};">
		    <img src="image/cancel_vote.png"  style="width: 90px; margin-right: 0px; margin-left: 40px;"/>
	
			
		<h3>Create Announcement</h3>
			</a>
            	
		   </div>
		   
		   <div class="span3 breadcrumb thumbnail menu" id="comment">
		<a id="CareId3" href="javascript:{};">
		    <img src="image/cancel_vote.png" style="width: 90px; margin-right: 0px; margin-left: 40px;" />
			
			
		<h3>Get All Comments</h3>
			
             </a>	
		   </div>
		   <div class="span3 breadcrumb thumbnail menu" id="allannoun">
		 <a id="CareId4" href="javascript:{};">
		    <img src="image/cancel_vote.png" style="width: 90px; margin-right: 0px; margin-left: 40px;" />
			
			
		<h3>Get All Announcement</h3>
			</a>
             	
		   </div>
		  </div>
	   
	   </div>
	
	 <!--<div class="row-fluid">
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
	</div>--->
	<div id="BOX-1" style="display: show" >
			<div id="formDivId" class=" getallcommentsBlock"  >
			<div class="control-group" style="color:red;">
					<s:actionerror />
					<s:fielderror />
				</div>
		   <form class="form-horizontal" name='userDetailsForm' action="adminRegisterSaveAction.action" method="post" >
				<legend>Personal Information</legend>
				<input type="hidden" value="admin" name="userType"></input>
				<input type="hidden" value="1" name="type"></input>
				<div class="persol_align thumbnail">
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
				</div>
			<!--	<div id="userType"><input type="hidden" id="userType" name="userType" value="${userType}"></input></div>-->
				
				<div id="successMsg" style="margin-left: 77px;"></div>
			</form>
			
		
			<button class="btn btn-primary" id="saveUser">Save changes</button>
		
		
		<span style="float:right;display:none;"><a class="btn btn-primary" id="changePassword" >Change Password</a><span>
		
	
	
	</div>
			</div>
	<div id="commentsDiv" >
	<div id="BOX-3" class="getallcommentsBlock" style="display:none;">
		<div class="t_align_sec">
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
						<input type="text" id="datepicker" name="date" readonly="true" onChange="validateDate();"/>
					<div style="display: none; float: right; margin-right: 295px; margin-top: 6px;" id="dateErrorMessge"><b style="color:red">Please Enter Valid Date</b></div></div>
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
			<div><input type="radio" name="announcement" value="all" id="forAnnouncementAllId" checked="checked" class="annoncementsBlock" onClick='getPaginationForData(0,"all");'>All</input>
			<input type="radio" name="announcement" value="btDates" class="annoncementsBlock" onClick=''>Between Dates</input>
			<input type="radio" name="announcement" value="type" class="annoncementsBlock">Type</input></div>
			<div id='btAnnouncementsDiv'></div>
			<div id='typeAnnouncementsDiv'></div>
			<div id="pagedAnnouncementsId"  class="getallcomment" style="display: inline-block; width: 785px;">
			
			</div>
						<!----pagination Div----->
						<div class="span12 text-center">
							<div id="paginationId"></div>
						</div>
			</div>
			
		</div>

		<div id="totalCommentsModal" class="modal hide fade">
		<div class="modal-body">
			<a class="close" data-dismiss="modal">X</a>
			<legend>Comments On this Announcement</legend>
			<div id="totalComments"></div>
			<a id="moreId"></a>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
		</form>
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
	
	$('.menu').click(function(){
    $('.menu').removeClass("active");
    $(this).addClass("active");
});

	function validateDate()
	{
		
		var date = $('#datepicker').val();
		var flag = false;
		if(date != null && date.length > 0)
		{
			flag = true;
			$('#dateErrorMessge').hide();
		}
		else
		{
			$('#dateErrorMessge').show();
		}
		return flag;
		
	}
	$('#saveAnnouncement').click(function(){
		    $('[name="AnnouncementForm"]').validate();
		    if ( $('[name="AnnouncementForm"]').valid() && validateDate())
		    document.AnnouncementForm.submit();
			});
	$('.commentsBlock').click(function(){
			var value=$('input:radio[name=comment]:checked').val();
			$("#pagedCommentsId").html('');
			$("#paginationAnnouncementId").html('');
			if(value=="all"){
				getAllCommentsPagination(0);
			}
			else{
				getCommentsBetweenDates();
			}
			
	});
	$('.annoncementsBlock').click(function(){
			var name = $(this).val();
			$("#paginationId").html('');
		//	$("#pagedAnnouncementsId").html('');
			if(name == "all"){
				getPaginationForData(0,name);
				$('#btAnnouncementsDiv').html('');
			//	$("#paginationId").html('');
				$('#typeAnnouncementsDiv').html('');
			}
			else if(name == "type"){
				var str = '';
				str += '<span>Select Type : </span>';
				str += '<select id="selType" class="calender input-medium">';
				str += '<option value=0>Select Type</option>';
				str += '<option value=1>Electoral Updates</option>';
				str += '<option value=2>Press Releases</option>';
				str += '</select>';
				str += '<a class="btn btn-primary"  onClick="getPaginationForData(0,\''+name+'\')">Find</a>';
				$('#btAnnouncementsDiv').html('');
			//	$("#paginationId").html('');
				$('#typeAnnouncementsDiv').html(str);
			}
			else if(name == "btDates")
			{
				var str = '';
				str += '<span>Start Date : </span>';
				str += '<input type="text" name="startDateName" id="startDateName" class="calender input-medium"></input>';
				str += '<span>End Date : </span>';
				str += '<input type="text" name="endDateName" id="endDateName" class="calender input-medium"></input>';
				str += '<a class="btn btn-primary" onClick="getPaginationForData(0,\''+name+'\')">Find</a>';
				$('#btAnnouncementsDiv').html(str);
				$('#typeAnnouncementsDiv').html('');
			//	$("#paginationId").html('');
				$( "#startDateName" ).datepicker({
					defaultDate: "+1w",
					changeMonth: true,
					numberOfMonths: 1,
					dateFormat: 'yy-mm-dd',
					onClose: function( selectedDate ) {
					$( "#endDateName" ).datepicker( "option", "minDate", selectedDate );
					}
				   });
					
				$( "#endDateName" ).datepicker({
					defaultDate: "+1w",
					changeMonth: true,
					numberOfMonths: 1,
					dateFormat: 'yy-mm-dd',
					onClose: function( selectedDate ) {
						$( "#startDateName" ).datepicker( "option", "maxDate", selectedDate );
						}
					});
				}
			
	});
	
	$(function(){	
		$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
	});
	
	$('#createAnnouncementId').click(function(){
		$('#announcementForm').css('display','block');
	});
	
	
	
	$('input[name=attachFile]:checked').click(function(){
		$('#forFileId').toggle('fast');
	});
	
	function getPaginationForData(pageNo,type)
	{
		if(type == 'all')
		{
			var jsObj=
			{
				startRecord : pageNo,
				maxRecord   : 10,
				type        : type,
				task        : "getAllAnnouncements"
			}
			
		}
		else if(type == 'btDates')
		{
			var startDate = $('#startDateName').val();
			var endDate   = $('#endDateName').val();
			var jsObj=
			{
				startDate  : startDate,
				endDate    : endDate,
				type       : type,
				startIndex : pageNo,
				maxIndex   : 10,
				task       : "getAllAnnouncementsBtdates"
			}
		}
		else if(type == 'type')
		{
			var type = $('#selType option:selected').val();
			var jsObj=
			{
				type       : type,
				startIndex : pageNo,
				maxIndex   : 10,
				type       : type,
				task       : "getAllAnnouncementsSelType"
			}
			
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAllAnnouncementsForAdmin.action?"+rparam;
	callAjaxForAdmin(jsObj, url);
	}
	
	
	
	$('#allannoun').click(function(){getPaginationForData(0,'all')});
		
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
		 str +='<div style="margin-top: 10px;" id="dateErrMsg"></div>';
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
		if(startDate != "" && endDate != "")
		{
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
		else
		{
			$('#dateErrMsg').html('<b style="color:red">Please Select Date Fields</b>');
		}
		
	}
	

  function buildAllCommentsList(myResults,jsObj)
	{
		//alert('in123');
		
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
			str +='<span class="font_desc">'+myResults[i].comment+'</span>';
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

	function buildTotalCommentsListBtDates(myResults)
	{
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
		$("#btDatesMainDiv").show();
		if(myResults != null)
		{
			$('#dateErrMsg').html('');
			totalCount = myResults[0].total;
			var str = "";
			for(var i in myResults)
			{
			str += '<div id="commentsDIv'+myResults[i].commentId+'">';
			str +='<div class="comment_sec span9">';
			if(myResults[i].abused.toLowerCase() == "no")
			{
			str += '<a style="float: right;" id="abusedButton'+myResults[i].commentId+'" onClick="abuseComment('+myResults[i].commentId+');" ><img src="img/error.png"></a>';
			}
			str +='<span class="title_sec"></span><span class="title_sec1">'+myResults[i].announcement+'</span>';
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
				getCommentsSelectdDates(num);
				
			}
		});
		
	  }

   	}
	else
	{
		$('#pagedCommentsId').html('');
		$('#paginationAnnouncementId').html('');
		$('#dateErrMsg').html('<b style="color:red">No Data Avaliable</b>');
	}	
		
	}
	function getRemaingTotalCommentsList()
	{
		getAllCommentsPagination(stIndex);
	}
	
	function abuseComment(id)
	{
	  var r=confirm("Are you sure you want to delete?");
	  if (r==true)
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
									if(jsObj.showMore == true)
									{
										buildTotalCommentsList(myResults,jsObj)
									}
									else
									{
										//buildTotalCommentsList(myResults,"totalCommentsList",0);
										buildAllCommentsList(myResults,jsObj);
									}								
								}
								/* else if(jsObj.task =="getTotalComments" )
								{
									//buildTotalCommentsList(myResults,"totalCommentsList",0);
									buildAllCommentsList(myResults,jsObj);
								} */
								else if(jsObj.task =="getAllAnnouncements" || jsObj.task == "getAllAnnouncementsBtdates" ||
								jsObj.task == "getAllAnnouncementsSelType" )
								{
									buildAllAnnouncements(myResults,jsObj);
								}
								else if(jsObj.task =="abuseComment")
								{
									if(myResults.resultCode == 0)
									{
										$("#abusedStatus"+jsObj.id+"").html("<b style='color:green'>Abused Successfull..</b>");
										$("#commentsDIv"+jsObj.id+"").hide();
										getAllCommentsPagination(0);
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
										getPaginationForData(0);
																			
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
	str+='<legend>Get All Announcement</legend>';
	for(var i in results){
	  
		str+="<li>";
	
		
		
		str +='<article class=" notifications">';
			str +='<div  class="thumbnail title span8" id="announcemtsDiv'+results[i].announcementId+'">';				
	
	str+= "<div style='float:right; margin-right: 17px;margin-top: 7px;'><a class='icon-pencil' onClick='editAnnouncement("+results[i].announcementFileId+")' style='cursor: pointer; margin-right: 7px;'></a>";
	str+= "<a class='icon-remove' onClick='deleteAnnouncement("+results[i].announcementId+")' style='cursor: pointer;'></a>";
	str += "<span class='pull-right label' style='margin-left: 10px;'><a style='cursor: pointer;' onClick='getComments("+results[i].announcementId+",startIndex,maxIndex,true);'><i class='icon-comment'></i></a><small>"+results[i].commentsCount+"</small></span></div>";	
	str +='<h1>'+results[i].name+'</h1>';
	
   str +='<p>'+results[i].description+'</p>';
	str +='<div class="date span2">'+results[i].dateString+'</div>';
	str +='<div class=" span1 date pull-right">'+results[i].updatedBy+'</div>';
	str += "<div id='statusMsg"+results[i].announcementId+"'></div>";
	str +='</div>';	
	str +='</article>';
		str+="</li>";
		
	}
	
	var itemsCount=results[0].allAnnouncementsCount;
	var maxResults=jsObj.maxRecord;
	str+="</ul>";
	$("#pagedAnnouncementsId").html(str);
	if(jsObj.startRecord==0 || jsObj.startIndex == 0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getPaginationForData(num,jsObj.type);
				
			}
		});
		
	}
	}
	function getComments(id,startIndex,maxIndex,showMore)
	{
		/**
			The showMore is used in callback function to append the results to existing div..else we clear the div
		**/
		var jsObj =
			{  	
				id          : id,
				startIndex  : startIndex,
				maxIndex    : maxIndex,
				task        : "getTotalComments",
				showMore    : showMore
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "getCommentsAction.action?"+rparam;
			callAjaxForAdmin(jsObj, url);
	}
	function buildTotalCommentsList(myResults,jsObj)
	{
		var announcementId=jsObj.id;
		
		if(!jsObj.showMore)
		$('#totalComments').html("");
		
		if(myResults != null)
		{
			totalCount = myResults[0].total;
			var recordsPerPage=5;
			var str = "";
			var date="";
				for(var i in myResults)
				{
					str += '<div class="thumbnail indiComment">';
					str +='<span id="indiCommentId">'+myResults[i].comment+'</span></br>';
					str +='<span class="pull-left label label-important" id="indiCommentName">'+myResults[i].name+'</span>';
					str +='<span class="pull-right " id="indiCommentDate">'+myResults[i].commentedTime+'</span></br>';
					str += '</div>';
				}
				<!--str += '<a id="moreButton" style="display:none;" class="btn btn-primary" onClick="getRemaingCommentsList('+announcementId+');">More</a>';-->
				$('#totalComments').append(str);
			if(recordsPerPage+startIndex < totalCount)
			{
				$('#moreId').html('<a class="btn btn-primary" onClick="getRemaingCommentsList('+announcementId+');">More</a>');
				startIndex = startIndex + maxIndex;
				maxIndex   =  maxIndex;
				$('#moreId').css('display','block');
			}
			else
			{
				$('#moreId').css('display','none');
				startIndex=0;
			}
		}
		else
		{
			$('#moreId').css('display','none');
			startIndex=0;
		}
		$('#totalCommentsModal').modal('show');
	}
	function getRemaingCommentsList(announcementId)
	{
		getComments(announcementId,startIndex,maxIndex,true);
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
	  var r=confirm("Are you sure you want to delete?");
	  if (r==true)
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
				
	
	<c:if test="${resultStr=='SUCCESS'}">
		$('#successMsg').html("Registered SuccessFully");
		$('#successMsg').css("color","green");
	</c:if>
	<c:if test="${resultStr=='FAILURE'}">
		$('#successMsg').html("Error Occured While Register..");
		$('#successMsg').css("color","red");
	</c:if>
</script>
</body>
</html>