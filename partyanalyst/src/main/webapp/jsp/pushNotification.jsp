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
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
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
						<h4 class="panel-title">NOTIFICATION DETAILS</h4>
					</div>
					<div class="panel-body">
						<div>
						  <ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">NOTIFICATION CODE</a></li>
							<li role="presentation" ><a href="#createNotificationType" aria-controls="createNotificationType" role="tab" data-toggle="tab"> CREATE NOTIFICATION TYPE</a></li>
							<li role="presentation" id="createNotificationId"><a href="#createNotification" aria-controls="createNotification" role="tab" data-toggle="tab">CREATE NOTIFICATION</a></li>
							<li role="presentation" id="updateNotificationTypeStatusId"><a href="#updateNotificationTypeStatus" aria-controls="updateNotificationTypeStatus" role="tab" data-toggle="tab">UPDATE NOTIFICATION TYPE STATUS</a></li>
							<li role="presentation" id="updateNotificationTextId"><a href="#updateNotificationStatus" aria-controls="updateNotificationStatus" role="tab" data-toggle="tab">UPDATE NOTIFICATION STATUS</a></li>
						  </ul>
						  <div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="home">
								<div class="col-md-6 col-md-offset-3">
								<label>PUSH NOTIFICATION CODE :</label>
								<input type="text"  name="name"  class="form-control" id="notificationTextId" placeholder="Please Enter Notification Code "/>
								</div>
								<div class="col-md-12" style="margin-top:10px;">
								<div class="row">
								<div class="col-md-12">
								  <input type="button" class="btn btn-success btn-block"   value="PUSH NOTIFICATION" onclick="pushNotificationDetailsStats();"></button>
								  <div id="pushNotificationCodeId" style="color:green;"></div>
								</div>
								</div>
							    </div>
							</div>
							
							<div role="tabpanel" class="tab-pane" id="createNotificationType">
								<div class="col-md-6 col-md-offset-3">
								<label>Notification Type Text:</label>
								<input type="text"  name="name"  class="form-control" id="notificationTypeTextId" placeholder="Please Enter Notification Text"/>
								<div style="color:red;" id="notificationTypeTextErrId"></div>
								</div>
								<div class="col-md-6 col-md-offset-3">	
								<input type="button" id="ntfctnTypeId"  attr_success="NOTIFICATION TYPE ADDED..." class="btn btn-success btn-block"   value="ADD NOTIFICATION TYPE" onclick="addNotificationType();"/>
								<div id="ntfctnTypeTextSuccessId" sytle="color:green"></div>
								</div>
							</div>
							
							<div role="tabpanel" class="tab-pane" id="createNotification">
								<div class="col-md-6 col-md-offset-3">
									<label>Notification Type:</label>
									<select class="dropkick" id="notificationTypeId">
									</select>
									<div style="color:red;" id="notificationTypeErrId"></div>
								</div>
								
								<div class="col-md-6 col-md-offset-3">
								<label>Notification Text:</label>
								<input type="text"  name="name"  class="form-control" id="addNotificationTextId" placeholder="Please Enter Notification Text"/>
								<div style="color:red;" id="addNotificationTypeTextErrId"></div>
								</div>
								
								<div class="col-md-6 col-md-offset-3">
								<input type="button" id="notificationId" attr_success="NOTIFICATION ADDED..." class="btn btn-success btn-block"   value="ADD NOTIFICATION"  onclick="addNotification();"/>
								<div id="notificationSuccessId" sytle="color:green"></div>
								</div>
								
							</div>
							
							
							<div role="tabpanel" class="tab-pane" id="updateNotificationTypeStatus">
								<div class="col-md-6 col-md-offset-3">
									<label>Notification Type:</label>
									<select type="select" class="dropkick" id="updateNotificationTypeId">
									</select>
									<div style="color:red;" id="updateNotificationTypeErrId"></div>
								</div>
								
								<div class="col-md-6 col-md-offset-3">
									<label>Active Status:</label>
									<select type="select" class="form-control" id="activeStatusForNotificationtypeId" >
									 <option value="0">Please Select Active Status </option>
									 <option value="true"> TRUE </option>	
									 <option value="false"> FALSE </option>
									</select>
									<div id="activeStatusForNotificationTypeErrId" style="color:red;"></div>
								</div>
								<div class="col-md-6 col-md-offset-3">	
								<input type="button" id="updateNtfctnTypeId" class="btn btn-success btn-block"   value="UPDATE NOTIFICATION TYPE" ></button>
								<div id="updateNtfctnTypeSuccessId" sytle="color:green;"></div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="updateNotificationStatus">
								<div class="col-md-6 col-md-offset-3">
									<label>Notification Type:</label>
									<select class="dropkick" id="notificationTypeTextUpdateId">
									</select>
									<div style="color:red;" id="notificationTypeTextUpdateErrId"></div>
								</div>
								
								<div class="col-md-6 col-md-offset-3">
								<label>Notification:</label>
								<select type="select" class="dropkick" id="addNotificationTextUpdateId" >
									<option value="0">select notification</option>
								</select>
								<div style="color:red;" id="addNotificationTextUpdateErrId"></div>
								</div> 
								<div class="col-md-6 col-md-offset-3">
									<label>Active Status:</label>
									<select type="select" class="form-control" id="activeStatusForNotificationtypeUpdateId" >
									 <option value="0">Please Select Active Status </option>
									 <option value="true"> TRUE </option>	
									 <option value="false"> FALSE </option>
									</select>
									<div id="activeStatusForNotificationtypeUpdateErrId" style="color:red;"></div>
								</div>
								<div class="col-md-6 col-md-offset-3"">	
								<input type="button" id="updateNotificationId"  class="btn btn-success btn-block"   value="UPDATE NOTIFICATION" ></button>
								<div id="updateNotificationSuccessId" sytle="color:green;"></div>
								</div> 
							</div>
						  </div>

						</div>
					<!--	<div class="row">
						<div class="col-md-10 col-md-offset-3" >
						<a href="" class="btn btn-primary btn-xs" id="createNotificationTypeId"> CREATE NOTIFICATION TYPE </a>
						
						<a  class="btn btn-info btn-xs" id="createNotificationId"> CREATE NOTIFICATION  </a>
						
						<a  class="btn btn-primary btn-xs" id="updateNotificationTypeStatusId"> UPDATE NOTIFICATION TYPE STATUS </a>
						
						<a  class="btn btn-info btn-xs" id="updateNotificationStatusId"> UPDATE NOTIFICATION STATUS</a>
						</div>
						</div>-->
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
 
<script src='dist/2016DashBoard/js/bootstrap.js' type='text/javascript'></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>                                              
<script type="text/javaScript">

$(".dropkick").dropkick();
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
	
	var jsObj={
			notificationTypeId:notificationTypeId,
			notificationText:notificationText
		}
		
		   $.ajax({
			type : 'POST',
			url : 'saveNotificationAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){   
		if(result=="Success"){
			$("#notificationSuccessId").html("Notification Added Successfully");
		}
	});

}
function addNotificationType(){
$("#pushNotificationCodeId,#ntfctnTypeTextSuccessId,#notificationSuccessId,#updateNtfctnTypeSuccessId,#updateNotificationSuccessId").html("");

	$("#notificationTypeTextErrId").html("");
	var notificationText = $("#notificationTypeTextId").val().trim();
	if(notificationText.length==0){
		$("#notificationTypeTextErrId").html("Please type one notification type.");
		return;
	}
	var jsObj={
			notificationTypeText:notificationText
		}
		
		   $.ajax({
			type : 'POST',
			url : 'saveNotificationTypeTextAction.action', 
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="Success"){
				$("#ntfctnTypeTextSuccessId").html("Notification Type Added Successfully");
			}
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
				$("#notificationTypeId").find("option").remove();
				str +='<option value="0">Select Notification Type</option>'; 
				for(var i in result){
				str +='<option value="'+result[i].id+'">'+result[i].deviceName+'</option>'; 
				}
			}
			$("#notificationTypeId").html(str);
			$("#notificationTypeId").dropkick();
			var select = new Dropkick("#notificationTypeId");
			select.refresh(); 
			
			$("#updateNotificationTypeId").html(str);
			$("#updateNotificationTypeId").dropkick();
			var select1 = new Dropkick("#updateNotificationTypeId");
			select1.refresh(); 
			
			$("#notificationTypeTextUpdateId").html(str);
			$("#notificationTypeTextUpdateId").dropkick();
			var select2 = new Dropkick("#notificationTypeTextUpdateId");
			select2.refresh(); 
			
	});

}
$(document).on("change","#notificationTypeId",function(){
	
	var typeId=$(this).val();
	getNotificationTypeDetailsStats(typeId);
});
$(document).on("change","#notificationTypeTextUpdateId",function(){
	
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
//sdsds
$(document).on("change","#addNotificationTextUpdateId",function(){
	
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
			$("#activeStatusForNotificationtypeUpdateId").val(result.trim());
			}
		});
	
}
$(document).on("change","#activeStatusId",function(){    
	$("#activeStatusErrId").html("");
});
$("#trigger,#loginId").hide();
//sss
$(document).on('click','#updateNotificationId',function(){
	$("#pushNotificationCodeId,#ntfctnTypeTextSuccessId,#notificationSuccessId,#updateNtfctnTypeSuccessId,#updateNotificationSuccessId").html("");

	var newNotificationTypeId = $("#notificationTypeTextUpdateId").val();
	if(newNotificationTypeId==0){
		$("#notificationTypeTextUpdateErrId").html("Please select notification type");
		return;
	}
	var notificationId = $("#addNotificationTextUpdateId").val();
	if(notificationId==0){
		$("#addNotificationTextUpdateErrId").html("Please select one notification");
		return;
	}
	var status = $("#activeStatusForNotificationtypeUpdateId").val();
	var updationTypeStr = "notification";
	var jsObj={
			id:notificationId,
			activeStatus:status,
			updationTypeStr:updationTypeStr
		}
		$.ajax({
			type : 'POST',   
			url : 'updateNotificationAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){   
			if(result!=null){
			$("#updateNotificationSuccessId").html("Notification Updated Successfully");
			}
		});
	
});
//bgbb
$(document).on('click','#updateNtfctnTypeId',function(){
	$("#pushNotificationCodeId,#ntfctnTypeTextSuccessId,#notificationSuccessId,#updateNtfctnTypeSuccessId,#updateNotificationSuccessId").html("");

	var notificationTypeId = $("#updateNotificationTypeId").val(); 
	if(notificationTypeId==0){
		$("#updateNotificationTypeErrId").html("Please select a notification type");
		return;
	}
	var status = $("#activeStatusForNotificationtypeId").val();
	if(status==0){
		$("#activeStatusForNotificationTypeErrId").html("Please select a notification type active status");
		return;
	}
	
	var updationTypeStr = "notificationType";
	var jsObj={
			id:notificationTypeId,
			activeStatus:status,
			updationTypeStr:updationTypeStr
		}
		$.ajax({
			type : 'POST',
			url : 'updateNotificationTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){
			$("#updateNtfctnTypeSuccessId").html("Notification Type Updated Successfully");
			}
		});
	
	
});
$(document).on('click','#createNotificationTypeId',function(){
});
$(document).on('click','#createNotificationId',function(){
	getNotificationTypeDetails();
});
$(document).on('click','#updateNotificationTypeStatusId',function(){
	getNotificationTypeDetails();
});

$(document).on('click','#updateNotificationTextId',function(){
	getNotificationTypeDetails();
});
function pushNotificationDetailsStats(){

$("#pushNotificationCodeId,#ntfctnTypeTextSuccessId,#notificationSuccessId,#updateNtfctnTypeSuccessId,#updateNotificationSuccessId").html("");
  var jsObj={
      notificationTypeId:0,
      notificationText:$('#notificationTextId').val()
    }
    
       $.ajax({
      type : 'POST',
      url : 'pushNotificationAction.action',
      dataType : 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null){
			$("#pushNotificationCodeId").html("send successfully");
		}
});

}
</script>
</html>
