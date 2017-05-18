<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
<title>Notification</title>
<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<style type='text/css'>
.nav li a
{
	padding:10px
}
</style>
</head>
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">SEND NOTIFICATION </h4>
					</div>
					<div class="panel-body">
						<div class="tab-content">

							<div role="tabpanel" class="tab-pane active" id="createNotification">
								<div class="col-md-6 col-md-offset-3">
									<label>Notification Type:</label>
									<select class="chosenClass" id="notificationTypeId">
									</select>
									<div style="color:red;" id="notificationTypeErrId"></div>
								</div>
								
								<div class="col-md-6 col-md-offset-3">
								<label>Notification Text:</label>
								<input type="text"  name="name"  class="form-control" id="addNotificationTextId" placeholder="Please Enter Notification Text"/>
								<div style="color:red;" id="addNotificationTypeTextErrId"></div>
								</div>
								
								<div class="col-md-6 col-md-offset-3">
								<input type="button" id="notificationId" attr_success="NOTIFICATION ADDED..." class="btn btn-success btn-block"   value="SEND NOTIFICATION"  onclick="addNotification();"/>
								<div id="notificationSuccessId" sytle="color:green"></div>
								</div>
								
							</div>
						  </div>

						</div>
					</div>
			</div>
		</div>
	</div>
</section>
 
<script src='dist/2016DashBoard/js/bootstrap.js' type='text/javascript'></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javaScript">
$(".chosenClass").css("width","100%");
getNotificationTypeDetails();
getAllNotificationsByuser();
function getAllNotificationsByuser(){
	var jsObj = {};
	$.ajax({
			type : 'POST',
			url : 'getNotificationTypeDetailsAction',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str = '';
		});
}
function getNotificationTypeDetails(){
	var jsObj = {};
	$.ajax({
			type : 'POST',
			url : 'getNotificationTypeDetailsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str = '';
			if(result!=null && result.length>0){
				$("#notificationTypeId").find("option").remove();
				str +='<option value="0">Select Notification Type</option>'; 
				for(var i in result){
				str +='<option value="'+result[i].id+'">'+result[i].deviceName+'</option>'; 
				}
			}
			$("#notificationTypeId").html(str);
			$("#notificationTypeId").chosen();
			$("#notificationTypeId").trigger("chosen:updated");

			
	});

}
$(document).on("change","#notificationTypeId",function(){
	
	var typeId=$(this).val();
	getNotificationTypeDetailsStats(typeId);
});

$("#trigger,#loginId").hide();

function getNotificationTypeDetailsStats(typeId){
	
	var jsObj={
			
			notificationTypeId : typeId
		}
		$.ajax({   
			type : 'POST',
			url : 'getNotificationTypeDetailsStatsAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str = '';
			if(result!=null && result.length>0){
				str +='<option value="0">Select Notification</option>'; 
				for(var i in result){
				str +='<option value="'+result[i].id+'">'+result[i].deviceName+'</option>'; 
				}
			}else{
				str +='<option value="0">No Notificatoins</option>';
			}
			
			$("#addNotificationTextUpdateId").html(str);
			$("#addNotificationTextUpdateId").dropkick();
			var select2 = new Dropkick("#addNotificationTextUpdateId");
			select2.refresh(); 
		});
}
function addNotification(){
$("#pushNotificationCodeId,#ntfctnTypeTextSuccessId,#notificationSuccessId,#updateNtfctnTypeSuccessId,#updateNotificationSuccessId").html("");

	$("#notificationTypeErrId").html("");
	$("#addNotificationTypeTextErrId").html("");
	var notificationTypeId = $("#notificationTypeId").val();
	var notificationText = $("#addNotificationTextId").val().trim();
	if(notificationTypeId==0){
		$("#notificationTypeErrId").html("Please select one notification type.");
		return;
	}
	if(notificationText.length==0){
		$("#addNotificationTypeTextErrId").html("Please type one notification.");
		return;
	}
	var JSONObject= {"notificationTypeId":notificationTypeId, "notification":notificationText };
	
	$.ajax({
		type : 'POST',
		url : 'pushNotificationAction.action',
		contentType: "application/json; charset=utf-8",
		dataType : 'json',
		data: {task:JSON.stringify(JSONObject)}
	}).done(function(result){  
	if(result=="SUCCESS"){
		$("#notificationSuccessId").html("Notification Added Successfully");
		var select = new Dropkick("#ID");
		select.refresh();
	}
});
}
</script>
</html>
