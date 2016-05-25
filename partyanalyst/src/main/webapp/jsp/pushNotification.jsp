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
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">NOTIFICATION DETAILS</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							
							<div class="col-md-6 col-md-offset-3">
								<label>Notification Type:</label>
								<select type="select" class="form-control" id="notificationTypeId">
								 <option value="0">Please Select Notification Type</option>
								</select>
							</div>
							<div class="col-md-6 col-md-offset-3">
								<label>Notification:</label>
								<select type="select" class="form-control" id="notificationId" >
								 <option value="0">Please Select Notification</option>
							
								</select>
							</div>
							
							<div class="col-md-6 col-md-offset-3">
								<label>Active Status:</label>
								<select type="select" class="form-control" id="activeStatusId" >
								 <option value="0">Please Select Active Status </option>
								 <option value="true"> TRUE </option>	
								 <option value="false"> FALSE </option>
								</select>
								
								
							</div>
							<div class="col-md-6 col-md-offset-3">
								<label>Name:</label>
								<input type="text"  name="name"  class="form-control" id="notificationId" placeholder="Please Enter Notification"/>
							</div>
							
							<div class="col-md-6 col-md-offset-3" style="margin-top:10px;">
								<input type="button" class="btn btn-success btn-block"   value="submit" onclick="pushNotificationDetailsStats();"></button>
							</div>
							
							
						</div>
					</div>	     
				</div>
			</div>
		</div>
	</div>
</section>
 
 
                                              
<script type="text/javaScript">


function pushNotificationDetailsStats(){
	var jsObj={
			notificationTypeId:0,
			notificationText:$('#notificationId').val()
		}
		
		   $.ajax({
			type : 'POST',
			url : 'pushNotificationAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
});

}

getNotificationTypeDetails();
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
				for(var i in result){
				str +='<option value="'+result[i].id+'">'+result[i].deviceName+'</option>'; 
				}
			}
			$("#notificationTypeId").html(str);
	});

}
$(document).on("change","#notificationTypeId",function(){
	var typeId=$(this).val();
	getNotificationTypeDetailsStats(typeId);
});
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
				for(var i in result){
				str +='<option value="'+result[i].id+'">'+result[i].deviceName+'</option>'; 
				}
			}else{
				str +='<option value="0">No Notificatoins</option>';
			}
			$("#notificationId").html(str);
		});
}

$(document).on("change","#notificationId",function(){
	var typeId=$(this).val();
	notificationIsActiveStatus(typeId);
});

function notificationIsActiveStatus(typeId){
	
	var jsObj={
			notificatonsId:typeId
		}
		
		   $.ajax({
			type : 'POST',
			url : 'notificationIsActiveStatusAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){
			$("#activeStatusId").val(result.trim());
			}
		});
	
}
$("#trigger,#loginId").hide();
</script>
</html>
