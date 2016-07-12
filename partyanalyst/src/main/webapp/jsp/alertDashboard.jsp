<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alerts </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<link href="dist/Appointment/custom.css" rel="stylesheet" type="text/css">
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<style type="text/css">
		.createAlertModalCls
		{
			border-radius:0px;
		}
		.form-control
		{
			border-radius:0px;
		}
		.createAlertModalCls .modal-header
		{background:#ccc;}
		.input-group .form-control , .input-group .input-group-addon
		{
			border-radius:0px;
		}
		
		
		.btnCreateAlert
		{
			position:absolute;bottom:30px;right:30px;height:40px;width:40px;border-radius:50%;box-shadow:0px 0px 5px rgba(0,0,0,0.4);padding:3px 7px
		}
		.m_top10
		{
			margin-top:10px;
		}
		.tableModal tr td
		{
			border:0px !important; 
		}
		p,ul,h1,h2,h3,h4,h5,h6
		{
			margin:0px;
		}
		.alertStatusTracking
		{
			padding-left:20px;
			border-left:1px solid #ddd;
		}
		.alertStatusTracking li
		{
			list-style:none;
			border-radius:4px;
			position:relative;
		}
		.alertStatusTracking li:before
		{
			content:' ';
			height:10px;
			width:10px;
			border-radius:50%;
			background:#ddd;
			top:10px;
			padding:4px;
			left:-26px;
			position:absolute;
		}
		.arrow_box {
			position: relative;
			background: #fff;
			border: 1px solid #ddd;
			padding:7px;
			border-radius:4px;
		}
		.arrow_box:after, .arrow_box:before {
			right: 100%;
			top: 10px;
			border: solid transparent;
			content: " ";
			height: 0;
			width: 0;
			position: absolute;
			pointer-events: none;
		}

		.arrow_box:after {
			border-color: rgba(255, 255, 255, 0);
			border-right-color: #fff;
			border-width: 5px;
			margin-top: -5px;
		}
		.arrow_box:before {
			border-color: rgba(194, 225, 245, 0);
			border-right-color: #c2e1f5;
			border-width: 6px;
			margin-top: -6px;
		}
	</style>
 </head>                                         							
<body style="position:relative;">
<div class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-8 col-xs-12 col-sm-6">
							<h4 class="panel-title">ALERT DASHBOARD</h4>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-6">
							<div class="input-group">
								<input type="text" class="form-control" id="dateRangePickerId"/>
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</div>
						</div>
					</div>
					
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelId"></div>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelDataId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>        
</div>
<button class="btn btn-danger btnCreateAlert" id="createAlertBtn" style=""><i style="font-size:18px;top:0;right:-1" class="glyphicon glyphicon-plus"></i></button>
<div class="modal fade" id="ModalShow" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-xs">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="descriptionTitleId">Modal title</h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<table class="table table-condensed tableModal">
					<tr>
						<td style="width:50%;">
							<label>Type</label>
							<p id="typeId"></p>
						</td>
						<td style="width:50%;">
							<label>Severity&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="severityId"></p>
						</td>
					</tr>
					<tr>
						<td>
							<label>Created&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="createdDate"></p>
						</td>
						<td>
							<label>Level&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="levelId"></p>
						</td>
					</tr>
					<tr>
						<td>
							<label>Location&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="LocationId"></p>
						</td>
						<td>
							<label>Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<select class="dropkickClass" id="statusId">
								<option value='0'>Select Status</option>
								
								<option value='1'>Created</option>
								<option value='2'>Action In Progess</option>
								<option value='3'>Completed</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<p id="descriptionId"></p>
						</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2">
							<label>Comments</label><span ></span>
							<textarea class="form-control" id="commentsId"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="errorId"></div>
		<div class="row">
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<button class="btn btn-success updateAlertStatusCls">UPDATE</button>
		  </div>
		</div>
       <div class="row">
		  <div class="col-md-12 col-xs-12 col-sm-12">
			
			<div id="alertCommentsDiv"></div>
		  </div>  
		</div>
	 </div>
      <!--<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>-->
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- create alert Modal-->
<div class="modal fade " id="createAlertModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content createAlertModalCls">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">CREATE ALERT</h4>
      </div>
	  <div class="modal-body">
	  <!--- include the create alert-->
	  
	  <jsp:include page="alert.jsp" flush="true"/>
	  </div>
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="dist/alertDashBoard/alertDashboard.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/CreateAlert/createAlert.js" type="text/javascript"></script>
<script type="text/javascript">
$("#dateRangePickerId").daterangepicker({opens:'left'});
$(document).on("click","#createAlertBtn",function(){
	$("#createAlertModal").modal('show');
	buildLevels();
	showHideSearch("advanceSearch");
	showHideBySearchType();
	disableByLevel(1);
	getAlertsource();
	$("#apptmemberDetailsDiv").html("");
});
$(".dropkickClass").dropkick();

function buildapptmemberDetails(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead><th></th></thead>';
			str+='<tbody>';
			for(var i in result){
				
				
					str+='<tr><td style="padding:0px !important;">';
					str+='<div class="col-md-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li>';
							str+='<div class="row">';
								
								str+='<div class="col-md-6 col-md-offset-1">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
										str+='</div>';
										str+='<div class="media-body">';
										
							
										if(result[i].constituency !=null && result[i].constituency.length>0 ){
											<c:choose>
											<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS')}">
											if(result[i].id != null && result[i].id > 0){
												if(result[i].candidateType=="voter"){
												 str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div>';
												}else{
													str+='<a  target="_blank" data-toggle="tooltip" data-placement="top" title="Cadre Details" style="cursor:pointer;" href="cadreDetailsAction.action?cadreId='+result[i].id+'"><div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div></a>';
												}
											}
											else
											str+='<span style="color:#34A7C1;">'+result[i].name+'</span> ';
											</c:when>
											<c:otherwise>
											str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span></div>';
											</c:otherwise>
											</c:choose>
											
										}else{
											str+='<div style="color:#34A7C1;">'+result[i].name+'</div>';
										}
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p ><span><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].mobileNo+'</span>';
										}else{
											str+='<p><span><i class="fa fa-mobile" style="font-size:15px"></i>   - </span>';
										}
										
										if(result[i].designation !=null && result[i].designation.length>0){
											
												str+='<span style="margin-left:10px;"> '+result[i].designation+'</span></p>';
										}else{
											
											//str+='<span style="margin-left:10px;">Designation: - </span></p>';
											
											 if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
												 if(result[i].candidateType == 'cadre'){
													str+='<span style="margin-left:10px;"> - Cadre</span></p>'; 
												 }else{
													 str+='<span style="margin-left:10px;"> - </span></p>';
												 }
											}else{
												str+='<span style="margin-left:10px;"> - </span></p>';
											} 
										}
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="btn btn-success btn-sm" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv candidatecls close'+result[i].id+'"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Create Appointment Request" attr-consti="'+result[i].constituency+'"> &nbsp;SELECT</label></div>';	
							  
								/*if(result[i].appointmentCandidateId != null && result[i].appointmentCandidateId > 0){
									
									str+='<div class="col-md-1 m_top10"><a  title="Click here to View '+result[i].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].appointmentCandidateId+'" attr-name="'+result[i].name+'" attr-designation="'+result[i].designation+'" attr-mobile="'+result[i].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a></div>&nbsp;&nbsp;';
									
								}
								
								
							if(result[i].designation==null){
								result[i].designation = "";
							}
							if(result[i].aptExists == false && result[i].appointmentCandidateId != null){
								str+='<div class="col-md-1   m_top10" attr_id="'+result[i].id+'" >';
							}
							else{
								str+='<div class="col-md-1 col-xs-offset-1 m_top10" attr_id="'+result[i].id+'" >';
							}
							if(result[i].aptExists == false)
							{
								str+='<div class="btn btn-success btn-sm" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Create Appointment Request"> &nbsp;SELECT</label></div>';	
							}								
							else{
								str+='<label data-toggle="tooltip"  data-placement="top" title="This Candidate Already in '+result[i].aptName+' Appointment with '+result[i].aptStatus+' Status: you can not addtion to another Appointment"> ';
								str+=''+result[i].aptName+' - '+result[i].aptStatus+'';
								str+='</label>';
							}
							
								str+='</div>';
								
							str+='</div>';*/
						str+='</li>';
					 
					str+='</ul>';
				str+='</div>';
				str+='</td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
		}
		
		$("#apptmemberDetailsDiv").html(str);
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		
		applyPagination();
	}
	function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
	var cloneCount=0;
   $(document).on("click",".apptDetailsDiv",function(){
		
		 if($(this).is(':checked')){
			 $(".membersBlock").show();
			  var name  = $(this).attr("attr_name");
			  var image = $(this).attr("attr_img_url");
			  var attrId = $(this).attr("attr_id");
			  var attrConsti =  $(this).attr("attr-consti");
			  
			/* $(".membersBlock").append('<div class="block"><input type="hidden" class="form-control candidatecls"  name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'" /><div id="memberDiv'+attrId+'" class="row m_top10"><div class="col-md-3 col-md-offset-1"><p>Name : '+name+'</p></div>  <div class="col-md-3"><p>Constituency : '+attrConsti+' </p></div><span class="closeIcon" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove"></i></span></span><div class="col-md-3"><label>Alert Impact</label><select class="form-control"  id="alertImpactId" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive </option>	<option value="2">Negative </option></select></div></div></div>');*/
			var str ='';
			str+='<div class="col-md-10 col-md-offset-1 block">';
			str+='<div class="media"><div class="media-left">';
			str+='<img src="http://mytdp.com/images/cadre_images/282/AP1431166113.jpg" alt="image" style="height:60px;width:60px;">';
			str+='</div>';
			str+='<div class="media-body">';
			str+='<input type="hidden" class="form-control memberDatacls" name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'"/>';
			str+='<label>Name</label>';
			str+='<p>'+name+'</p>';
			str+='<label>Constituency</label>';
			str+='<p>'+attrConsti+'</p><label>Alert Impact</label><select class="form-control" style="width:150px" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive</option><option value="2">Negative</option></select></div></div><span class="closeIcon" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove" ></i></span></div>';
			 $(".membersBlock").append(str);
							 
			  cloneCount = cloneCount+1;
		 }
   })
   $(document).on("click",".closeIcon",function(){
	$(this).parent().remove();
	var id=$(this).attr("id");
	
	$(".candidatecls"+id).prop('checked', false); 
	//var blockCount = $(this).attr("clone_block_count");
	$(".close"+id).prop('checked', false); 
	//$("#uncheck"+id).parent().find(".apptDetailsDiv").prop('checked', false); 
});
 var loginUserId = "${sessionScope.USER.registrationID}";
function getAlertsource(){
		$("#alertSourceId").html('');
		var jsObj =
		        {
					userId : loginUserId,
				task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertSourceForUserAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertSourceId').append('<option value="0"> Select Alert Source </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertSourceId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertSourceId").dropkick();
							var select1 = new Dropkick("#alertSourceId");
							select1.refresh();
					}
				  
				});
		}
</script>
</body>
</html>