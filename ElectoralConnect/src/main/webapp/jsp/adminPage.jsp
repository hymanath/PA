<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN PAGE</title>


</head>
<body>
<script type="text/javascript"  src="js/RegisterFormValidation.js"></script>
<style>
	#mainBodyId{width:800px;margin-left:auto;margin-right:auto;}
	input[type="radio"],#attachFileId{margin-bottom:5px;margin-right:5px;}
	
	#mainBodyId legend,#saveAnnouncement{margin-left:140px;margin-bottom:5px;}
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
	.align{ margin-left: 100px;
   margin-top:7px;}
	.t_align{text-align:center;}
	#forFileId{width:600px;}

</style>

<div id="mainBodyId">
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
		<h3 class="comment">Get All Comments</h3>
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
		   <form class="form-horizontal" name='userDetailsForm' action="registerAdmin.action?userType=admin" method="post">
				<legend>Personal Information</legend>
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
				
				<div id="userType"><input type="hidden" id="userType" name="userType" value="${userType}"></input></div>
				
				${resultStr}
			</form>
			
		
			<button class="btn btn-primary" id="saveUser">Save changes</button>
		
	
		<div id="successMsg"></div>
		
		<span style="float:right;display:none;"><a class="btn btn-primary" id="changePassword" >Change Password</a><span>
		
	
	
	</div>
			</div>
	<div id="commentsDiv">
	<div id="BOX-3" style="display: none;background-color:#fff;">
		<div class="t_align">
			<input type="radio" name="comment" onClick="getAllComments(0,5,'getTotalComments');">All</input>
			<input type="radio" name="comment" onClick="getCommentsBetweenDates();">Between Dates</input>
		</div></br>
		<div id="btDatesMainDiv">
			<div id="betweenDatesDiv"></div></br>
			<div id="totalCommentsBtDates"></div>
		</div>
		<div id="totalCommentsMainDiv  ">
			<div id="totalCommentsList"></div>
		</div>
	</div>
	</div>
	<div id="announcementForm" >
	<div id="BOX-2" style="display: none;background-color:#fff;">
		<form class="form-horizontal AnnouncementForm" name="AnnouncementForm" action="createAnnouncementAction.action"  method="post" enctype="multipart/form-data">
				<h2 class="row_border header">Announcement Form</h2>
				<div class="align">
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
				
				<input type="submit" class="btn btn-primary " id="saveAnnouncement" value="submit"></input>
						
			</form>
	</div>
			</div>
			<div id="BOX-4" style="display: none">
			<h3>jjjjjjjjjjjj</h3>
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
	
	$('#createAnnouncementId').click(function(){
		$('#announcementForm').css('display','block');
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
		str +='<div class=t_align>';
		str +='<form class="form-inline">';
		str +='<label>Start Date:&nbsp;&nbsp;</label>';
		str += '<input type="text" id="startDate" class="calender input-medium"></input>';
		str +='<label>End Date:&nbsp;&nbsp;</label>';
		str += '<input type="text" id="endDate" class="calender input-medium"></input><br><br>';
		str +='<div class="pull-right span5">';
		str += '<a class="btn btn-primary" onClick="getCommentsSelectdDates(startIndex,maxIndex,\'commentsBetweenDates\');">View</a>';
		str +='</div>';
		str +='</form>';
		str +='</div>';
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
				str += '<div id="commentsDIv'+myResults[i].commentId+'" class="span7 breadcrumb"  style=" border: 1px solid #c3c3c3; margin-bottom: 10px;">';
				str +='<span><b>Comment : </b></span><span>'+myResults[i].comment+'</span></br>';
				str +='<span><b>Announcement : </b></span><span>'+myResults[i].announcement+'</span></br>';
				str +='<span style="float:left;"><b>Commented By : <b>'+myResults[i].name+'</span>';
				
				str +='<span style="float:right"><b>Date : </b>'+myResults[i].date+'</span></br>';
				if(myResults[i].abused.toLowerCase() == "no")
				{
					str += '<a style="float: right;" id="abusedButton'+myResults[i].commentId+'" onClick="abuseComment('+myResults[i].commentId+');" class="btn btn-warning">Abuse</a>';
				}
				str += '<div id="abusedStatus'+myResults[i].commentId+'"></div>';
				
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
				str += '<div id="commentsDIv'+myResults[i].commentId+'" class="span9 breadcrumb"  style=" border: 1px solid #c3c3c3; margin-bottom: 10px;margin-left:29px;">';
				str +='<div style="color:#3A87AD;line-height:23px;">';
				str +='<span><b>Comment : </b></span><span style="color:#0088CC;font-size:15px;">'+myResults[i].comment+'</span></br>';
				str +='<span><b>Announcement : </b></span><span>'+myResults[i].announcement+'</span></br>';
				str +='<span style="float:left;"><b>Commented By : <b>'+myResults[i].name+'</span>';
				str +='</div>';
				str +='<div class="pull-right" >';
				str +='<span style="margin-top:3px;"><b>Date : </b>'+myResults[i].date+'</span></br>';
				if(myResults[i].abused.toLowerCase() == "no")
				{
					str += '<a style="float: right;" id="abusedButton'+myResults[i].commentId+'" onClick="abuseComment('+myResults[i].commentId+');" class="btn btn-warning">Abuse</a>';
				}
				str +='</div>';
				str += '<div id="abusedStatus'+myResults[i].commentId+'"></div>';
				str += '</div>';
				str += '<a id="moreButton" style="display:none;margin-top:5px;" class="btn btn-primary" onClick="getRemaingTotalCommentsList();">More</a>'
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
									buildTotalCommentsListBtDates(myResults);
								}
								else if(jsObj.task =="getTotalComments")
								{
									buildTotalCommentsList(myResults);
								}
								else if(jsObj.task =="abuseComment")
								{
									if(myResults.resultCode == 0)
									{
										$("#abusedStatus"+jsObj.id+"").html("<b style='color:green'>Abused Successfull..</b>");
										$("#commentsDIv"+jsObj.id+"").hide();
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
	
$(document).ready(function(){
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
					
</script>
</body>
</html>