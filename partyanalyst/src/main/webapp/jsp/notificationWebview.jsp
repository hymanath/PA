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
<title>Send Notification</title>
<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<style type='text/css'>
.nav li a
{
	padding:10px
}
.onoffswitch {
        position: relative; width: 144px;
        -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
    }
    .onoffswitch-checkbox {
        display: none;
    }
    .onoffswitch-label {
        display: block; overflow: hidden; cursor: pointer;
        border: 2px solid #999999; border-radius: 20px;
    }
    .onoffswitch-inner {
        display: block; width: 200%; margin-left: -100%;
        transition: margin 0.3s ease-in 0s;
    }
    .onoffswitch-inner:before, .onoffswitch-inner:after {
        display: block; float: left; width: 50%; height: 30px; padding: 0; line-height: 30px;
        font-size: 13px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
        box-sizing: border-box;
    }
    .onoffswitch-inner:before {
        content: "WITHOUT URL";
        padding-left: 16px;
        background-color: #EEEEEE; color: #999999;
    }
    .onoffswitch-inner:after {
        content: "WITH URL";
        padding-right: 16px;
        background-color: #34A7C1; color: #FFFFFF;
        text-align: right;
    }
    .onoffswitch-switch {
        display: block; width: 18px; margin: 8px;
        background: #FFFFFF;
        position: absolute; top: 0; bottom: 3px;
        right: 110px;
        border: 2px solid #999999; border-radius: 20px;
        transition: all 0.3s ease-in 0s; 
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
        margin-left: 0;
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
        right: 0px; 
    }
.text-capital{text-transform:uppercase}

</style>
</head>
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
					<div class="row">
						<div class="col-sm-6">
							<h4 class="panel-title">SEND NOTIFICATION </h4>
						</div>
						<div class="col-sm-6">
							<div class="onoffswitch pull-right">
								<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
								<label class="onoffswitch-label" for="myonoffswitch">
									<span class="onoffswitch-inner"></span>
									<span class="onoffswitch-switch"></span>
								</label>
							</div>
						</div>
					</div>
						
						
					</div>
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="createNotification">
								<div class="col-md-4 col-md-offset-3">
									<label>NOTIFICATION TYPE:</label>
									<select class="chosenClass" id="notificationTypeId">
									</select>
									<div style="color:red;" id="notificationTypeErrId"></div>
								</div>
								
								<div class="col-md-4 col-md-offset-3">
								<label>NOTIFICATION TEXT:</label>
								<textarea type="text"  name="name"  class="form-control" id="addNotificationTextId"
 									 maxlength="150" placeholder="Please Enter Notification Text"></textarea>								
 									<div style="color:red;" id="addNotificationTypeTextErrId"></div>
								</div>
								<div class="col-md-4 col-md-offset-3 urlLinkDivCls" style="display:none;">
									<label>URL LINK:</label>
									<input type="text"  name="name"  class="form-control" id="addYoutubeUrlId"	placeholder="Please Enter URL Key"/>								
 									<div style="color:red;" id="addNotificationTypeTextErrId"></div>
								</div>
								
								<div class="col-md-4 col-md-offset-3">
									<input type="button" id="notificationId" attr_success="NOTIFICATION ADDED..." style="padding-top: 5px; margin-top: 14px;"  class="btn btn-success btn-block"   value="SEND NOTIFICATION"  onclick="addNotification();"/>
									<div id="notificationSuccessId" sytle="color:green"></div>
								</div>
								
							</div>
						  </div>

						</div>
					</div>
				<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title text-capital" >  Notification Details </h4>
				</div>
				<div class="panel-body">
					<div class="table text-capital" id="NotificationDetailsId"></div>
				</div>
			</div>	
			</div>
		</div>
	</div>
	<!-- Modal -->
   <div class="modal fade text-capital" id="notificationDtlsModalId" role="dialog">
    <div class="modal-dialog" style="width:85%">
      <div class="modal-content">
        <div class="modal-header">
          <button id="removeClassModal" type="button" class="close" data-dismiss="modal">&times;</button>
         <div id="grivenaceModalHeedingId"> </div> 
         <div id="NotificationHeadinId"> </div> 
        </div>
        <div class="modal-body">
            <div class="table-responsive" id="totalDetailsTableId"> </div>
			<div id="notificationsdetailId"  class="table-responsive"></div>
        </div>
      </div>
    </div>
  </div>
</section>
 
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javaScript">
$(".chosenClass").css("width","100%");
getNotificationTypeDetails();
getAllNotificationsByuser();
function getAllNotificationsByuser(){
	var jsObj = {};
	$.ajax({
			type : 'POST',
			url : 'getAllNotificationsByuserAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){
				var str = '';
				str +='<table class="table table-bordered" id="leadersDetailsTab">'
					str +='<thead>';
						str +='<tr>';
					//	str +='<th style="text-align: center;">NotificationTypeId</th>';
						str +='<th style="text-align: center;">Notification Type</th>';
						str +='<th style="text-align: center;">Total Count</th>';
						str +='</tr>';
						str +='</thead>';
						str +='<tbody>';
							
							for(var i in result){
								str +='<tr>';
								//str +='<td  align="center" attr_notification_id="'+result[i].notificationTypeId+'" style="cursor:pointer;" class="getAlertDtlsOnCategoryWise"><a>'+result[i].notificationTypeId+'</a></td>';
								str +='<td  align="center"  attr_notification_id="'+result[i].notificationTypeId+'" attr_notification="'+result[i].notificationTypeId+'" style="cursor:pointer;" class="">'+result[i].notificationType+'</td>';
								if(result[i].orderNo != null && result[i].orderNo>0)
									str +='<td  align="center"  attr_notification_id="'+result[i].notificationTypeId+'" attr_notification="'+result[i].notificationTypeId+'" style="cursor:pointer;color:green;font-weight:bold;" class="getAlertDtlsOnCategoryWise"><u>'+result[i].orderNo+'</u></td>';
								else 
									str +='<td align="center" > - </td>';
								str +='</tr>';
							}
						str +='</tbody>';
				str +='</table>'; 
				$("#NotificationDetailsId").html(str);
				$('#notificationType').hover(function() {
					   $('.notificationType').show();
					},function() {
					  $('.notificationType').hide();
					});
			}
			else{
				$("#NotificationDetailsId").html('No Data available...');
			}
			
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

$("#loginId").hide();

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
			
			
		});
}
$(document).on("click",".getAlertDtlsOnCategoryWise",function(){
	
	    var notificationId = $(this).attr("attr_notification_id");
		$("#totalDetailsTableId").html("");  
		$("#notificationsdetailId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
		$("#notificationDtlsModalId").modal("show");     
		var jobj = {
		  notificationTypeId: notificationId,                              
		  }
		$.ajax({    
		  type : "POST",
		  url  : "getNotificationTypeDetailsuserAction.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result != null && result.length>0){
			  buildGrivenceDetailsTableOld(result);
			}else{
				$('#notificationsdetailId').html(' No Data available...');
			}
		});
	});
function addNotification(){
$("#pushNotificationCodeId,#ntfctnTypeTextSuccessId,#notificationSuccessId,#updateNtfctnTypeSuccessId,#updateNotificationSuccessId").html("");

	$("#notificationTypeErrId").html("");
	$("#addNotificationTypeTextErrId").html("");
	var notificationTypeId = $("#notificationTypeId").val();
	var notificationText = $("#addNotificationTextId").val().trim();
	var addYoutubeUrl = $("#addYoutubeUrlId").val().trim();
	if(notificationTypeId==0){
		$("#notificationTypeErrId").html("Please select one notification type.");
		return;
	}
	if(notificationText.length==0){
		$("#addNotificationTypeTextErrId").html("Please type one notification.");
		return;
	}
	var JSONObject= {"notificationTypeId":notificationTypeId,
	"notificationText":notificationText,
	"addYoutubeUrl":addYoutubeUrl};
	
	$.ajax({
		type : 'POST',
		url : 'pushNotificationAction.action',
		dataType : 'json',
		data: {task:JSON.stringify(JSONObject)}
	}).done(function(result){  
	if(result=="Success"){
		$("#notificationSuccessId").html("Notification Sent Done Successfully");
		$("#notificationTypeId").val(0);
		$("#notificationTypeId").trigger("chosen:updated");
		$("#addNotificationTextId").val('');
		$("#addYoutubeUrlId").val('');
		getAllNotificationsByuser();
		setTimeout(function(){ 
			$( "#notificationSuccessId" ).fadeOut( "slow" );
		}, 5000);
	}else{
		$("#notificationSuccessId").html("Please Try again...");
	}
});
}
function buildGrivenceDetailsTableOld(result){
	 $("#NotificationHeadinId").html('<h3>Notification Details</h3>');
	 var str='';
	
	str+='<table id="alertIdListTableId" class="table  table-bordered" cellspacing="0" width="100%">';
                    str+='<thead>';
                    str+='<tr>';
				    str+=' <th>Notification</th>';
					str+=' <th>Success Count</th>';   
					str+=' <th>Failure Count</th>';   
					str+=' <th>Time </th>';
                    str+='</tr>';
					str+=' </thead>';
					str+='<tbody>';
				   
				   for(var i in result){
					 str+='<tr>';
					 str+='<td >'+result[i].notification+'</td>';
					 str+='<td>'+result[i].successCount+'</td>';
					 str+='<td>'+result[i].failureCount+'</td>';
					 str+='<td>'+result[i].lastUpdatedTime+'</td>';
					 str+='</tr>';
				    }
				   str+='</tbody>';    
				   str+='</table>';
				   
				   $("#notificationsdetailId").html(str);
				   $('#alertIdListTableId').dataTable({});
				   
}
$("#myonoffswitch").click(function(){
  if($('#myonoffswitch').is(":checked")){
		$(".urlLinkDivCls").hide();
		$("#notificationTypeId").val(0);
	$("#notificationTypeId").trigger("chosen:updated");
  }else{
	$(".urlLinkDivCls").show();
	$("#notificationTypeId").val(7);
	$("#notificationTypeId").trigger("chosen:updated");
  }
});
</script>
</html>
