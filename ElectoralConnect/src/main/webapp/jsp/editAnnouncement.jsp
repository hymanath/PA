<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Edit Announcement Form</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/dataPicker.css"/>
<script type="text/javascript"  src="js/AnnouncementFormValidation.js"></script>
<style>
.AnnouncementForm{ margin-top: 5px;padding: 7px; border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;}
</style>
<div id="announcementForm" ></div>

<script>
var myResults = window.opener.announcementData;
var resultString = '${resultString}';
if(myResults != null)
{
	var title = htmlspecialchars(myResults[0].title);
	var description = htmlspecialchars(myResults[0].description);
	var filetitle = htmlspecialchars(myResults[0].fileTitle);
	var fileDescription = htmlspecialchars(myResults[0].fileDescription);
	var str = "";
	str += '<div id="BOX-2" style="background-color:#fff;">';
	str += '<form class="form-horizontal AnnouncementForm" name="AnnouncementForm" action="editUpdateAnnouncementAction.action?fromForm=announcement"  method="post" enctype="multipart/form-data">';
	str += '<input type="hidden" value="admin" name="isAdmin"></input>';
	str += '<input type="hidden" value="2" name="type"></input>';
	str += '<input type="hidden" name="announcementId" value='+myResults[0].id+'></input>';
	str += '<input type="hidden" name="announcementFileId" value='+myResults[0].announcementFileId+'></input>';
	str += '<input type="hidden" name="fileId" value='+myResults[0].fileId+'></input>';
	str += '<legend>Announcement Form</legend>';
	str += '<div class="align thumbnail">';
	str += '<div class="control-group">';
	str += '<label class="control-label requ" for="Title">Title</label>';
	str += '<div class="controls ">';
	str += '<input type="text" name="title" id="titleId"></input> ';
	str += '</div></div>';
	str += '<div class="control-group ">';
	str += '<label class="control-label requ" for="Description">Description</label>';
	str += '<div class="controls">';
	str += '<textarea rows="4" id="descriptionId" name="description" placeholder="Description"></textarea>';
	str += '</div></div>';
	str += '<div class="control-group ">';
	str += '<label class="control-label requ" for="announcementType">Announcement Type</label>';
	str += '<div class="controls">';
	if(myResults[0].announcementType == 1)
	{
		str += '<input type="radio" name="announcementType" value="1" checked="checked">Electoral Update</input>';
		str += '<input type="radio" name="announcementType" value="2">Press Release</input>';
	}
	else if(myResults[0].announcementType == 2)
	{
		str += '<input type="radio" name="announcementType" value="1" >Notification</input>';
		str += '<input type="radio" name="announcementType" value="2" checked="checked">Press Release</input>';
	}
	
	str += '</div></div>';
	str += '<div class="control-group">';
	str += '<label class="control-label" for="date">Date</label>';
	str += '<div class="controls">';
	str += '<input type="text" id="datepicker" name="date" value='+myResults[0].dateString+' readonly="true" onChange="validateDate();">';
	str += '<div style="display: none; float: right; margin-right: 295px; margin-top: 6px;" id="dateErrorMessge"><b style="color:red">Please Enter Valid Date</b></div></div></div>';
	str += '<div class="control-group">';
	str += '<label class="control-label" for="docs"></label>';
	str += '<div class="controls">';
	
	
	if(myResults[0].filePath != null && myResults[0].filePath != "")
	{
	str += '<input type="checkbox" id="attachFileId" name="attachFile" checked="checked"> Attach File</div></div></div>';
	str += '<div id="forFileId" class=" thumbnail align">';
	str += '<div class="control-group">';
	str += '<label class="control-label requ" for="FileTitle">Title</label>';
	str += '<div class="controls ">';
	str += '<input type="text" name="filetitle" id="fileTitleId" placeholder="File Title"> ';
	str += '</div></div>';
	str += '<div class="control-group ">';
	str += '<label class="control-label requ" for="FileDescription">Description</label>';
	str += '<div class="controls">';
	str += '<textarea rows="2" id="fileDescriptionId" name="fileDescription" placeholder="File Description"></textarea></div></div>';
	str += '<div class="control-group">';
	str += '<label class="control-label" for="docs">Related Documents</label>';
	str += '<div class="controls">';
	str += '<input type="file" id="docs" class="thumbnail" name="docs" value='+myResults[0].filePath+'></div>	</div></div>';
	}
	else
	{
	str += '<input type="checkbox" id="attachFileId" name="attachFile" onClick="getFileDetailsDiv();"> Attach File</div></div></div>';
	str += '<div id="forFileId" class=" thumbnail align" style="display:none;">';
	str += '<div class="control-group">';
	str += '<label class="control-label requ" for="FileTitle">Title</label>';
	str += '<div class="controls ">';
	str += '<input type="text" name="filetitle" id="fileTitleId" placeholder="File Title"> ';
	str += '</div></div>';
	str += '<div class="control-group ">';
	str += '<label class="control-label requ" for="FileDescription">Description</label>';
	str += '<div class="controls">';
	str += '<textarea rows="2" id="fileDescriptionId" name="fileDescription" placeholder="File Description"></textarea></div></div>';
	str += '<div class="control-group">';
	str += '<label class="control-label" for="docs">Related Documents</label>';
	str += '<div class="controls">';
	str += '<input type="file" id="docs" class="thumbnail" name="docs"></div>	</div></div>';
	}
	str += '<div class="control-group">';
	str += '<div class="controls">';
	str += '<span id="statusMessage">${resultString}</span>';
	str += '</div>';
	str += '<input type="submit" class="btn btn-primary " id="saveAnnouncement" value="Update"></input>';
	str += '</form></div></div>';
	$('#announcementForm').html(str);
	if(resultString == '')
	{
		$('#titleId').val(title);
		$('#fileTitleId').val(filetitle);
		$('#fileDescriptionId').val(fileDescription);
		$('#descriptionId').val(description);
	}
	else
	{
		$('#titleId').val('');
		$('#fileTitleId').val('');
		$('#fileDescriptionId').val('');
		$('#descriptionId').val('');
	}
	
}

function getFileDetailsDiv()
{
	if($('#attachFileId').is(":checked") == true)
	{
		$('#forFileId').show();
	}
	else
	{
		$('#forFileId').hide();
	}
}
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
$(function(){	
	$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
});

function htmlspecialchars(str) {
     if (typeof(str) == "string") {
      str = str.replace(/&/g, "&amp;"); /* must do &amp; first */
      str = str.replace(/"/g, "&quot;");
      str = str.replace(/'/g, "&#039;");
      str = str.replace(/</g, "&lt;");
      str = str.replace(/>/g, "&gt;");
      }
     return str;
     }
<c:if test="${fromForm=='announcement'}">
		<c:if test="${resultString=='SUCCESS'}">
			$('#statusMessage').html("Uploaded SuccessFully");
			$('#statusMessage').css("color","green");
		</c:if>
		<c:if test="${resultString=='FAILURE'}">
			$('#statusMessage').html("Sorry Failure Occur..Please upload later");
			$('#statusMessage').css("color","red");
		</c:if>
	</c:if>
</script>
</body>
</html>