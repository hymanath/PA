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
		<div><a href="registerUser.action?userType=admin">Create User</a><div>
		<div><a href="createAnnouncementAction.action?userType=admin">Create Announcement</a></div>
	</div>
	
	<div id="announcementForm">
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
	$(function(){	
		$("#datepicker").datepicker();
	});
	
	$('#saveAnnouncement').click(function(){
		document.userDetailsForm.submit();
	});
	
	$('input[name=attachFile]:checked').click(function(){
		$('#forFileId').toggle('fast');
	});
</script>
</body>
</html>