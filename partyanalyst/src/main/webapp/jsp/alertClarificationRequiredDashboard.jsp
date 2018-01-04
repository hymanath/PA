<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>  
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> CLARIFICATION REQUIRED DASHBOARD </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	
	<link href="dist/alertDashBoard/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
	
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
	<link href="dist/alertDashBoard/dist/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
	<link href="dist/alertDashBoard/dist/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
	<link href="dist/alertDashBoard/dist/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
	<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>

	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	<!--<script src="dist/alertDashBoard/alertDashboard.js" type="text/javascript"></script>-->
	
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/alertDashBoard/dist/css/custom.css" rel="stylesheet" type="text/css">

<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="js/LocationHierarchy/locationHierarchyAlert.js" type="text/javascript"></script>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
</head>                                         							
<body style="position:relative;">
<div class="container">
	<div class="row">
		
    	<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
			<div class="panel panel-default">
				<div class="panel-heading panel-headingColor">
					<div class="row">
						<div class="col-md-5 col-xs-12 col-sm-6">    
							<h4 class="text-capital" style="margin-top:5px !important;"><b>CLARIFICATION REQUIRED DASHBOARD</b></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<div class="input-group">
								<input type="text" class="form-control" id="dateRangePickerId"/>
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>     
							</div>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3">
							<ul class="menuSelection">
								<li attr_state_Id="0" class="stateCls">ALL</li>
								<li attr_state_Id="1" class="stateCls active">AP</li>
								<li attr_state_Id="36" class="stateCls">TS</li>   
							</ul>  
							<!--<div class="btn-group alertMenuDiv alertMenu">
							  <i class="glyphicon glyphicon-align-justify  dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>
							  <ul class="dropdown-menu pull-right alertMenuDropdown">
								<li><a href="#">Create New Alert</a></li>
								<li><a href="#">View Alert</a></li>
								<li><a href="#">Alerts Overview</a></li>
							  </ul>
							</div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3">
							<select class="form-control" id="alertTypeId">
								<!--<option value="0">All</option>-->
								<option value="1" selected="selected">Party</option>            
								<option value="2">Govt</option>
								<option value="3">Others</option>
							</select>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row m_top20">
						<!--<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="overAllCount"></div>
							
						</div>-->
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel panel-default">
								<div class="panel-heading panel-headingColor">
									<div class="row">
										<div class="col-md-3 col-xs-12 col-sm-3">
											<h4 class="panel-title text_capital"><b>alert status over view</b></h4>
										</div>
									</div>
								</div>
								<div class="panel-body bg_EF">
									<div id="alertCatTabId"></div>
								</div>
							</div>
						</div>
							
						<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >
							<div class="panel panel-default">
								<div class="panel-heading panel-headingColor">
									<div class="row">
										<div class="col-md-3 col-xs-12 col-sm-3">
											<h4 class="panel-title text_capital"><b>impacted region alerts</b></h4>
										</div>
									</div>
								</div>
								<div class="panel-body bg_EF" style="padding-top:20px;">
									<div id="locWiseAltCntId" class="" ></div>
								</div>
							</div>
						</div>-->
							
						
						<!--<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading panel-headingColor" role="tab" id="headingThree">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<h4 class="panel-title text-capital undoIcon">
												<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
												  <b>location wise alerts</b> 
												<span class="glyphicon glyphicon-chevron-down pull-right "></span></a>
											</h4>
										</div>
										<div class="col-md-12 col-xs-12 col-sm-12">
											<label class="radio-inline">
												<input class="locationCls" name="location" type="radio" value="State" checked/> State    
											</label>
											<label class="radio-inline">
												<input class="locationCls" name="location" type="radio" value="District"/> District
											</label>
											<label class="radio-inline">
												<input class="locationCls" name="location" type="radio" value="Constituency"/> Constituency
											</label>
											<!--<label class="radio-inline">
												<input class="locationCls" name="location" type="radio" value="Village"/> Village/Ward
											</label>-->
										<!--</div>
									</div>
								</div>
								<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
								  <div class="panel-body" style="background-color:#EFF3F4">
									<div class="row">
										<div class="col-md-12 col-xs-12 col-sm-12">
											<div id="multiLocationId"></div>
										</div>
									</div>
								  </div>
								</div>
							</div>
						</div>-->
					</div>
					
					
				</div>
			</div>
           	<div class="panel panel-default">
				<div class="panel-heading panel-headingColor">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-4">
									<h4 class="text-capital" style="margin-top:5px"><b>ALERT DETAILS</b></h4>
								</div>
								<div class="col-md-8 col-xs-12 col-sm-8">
									<button type="button" class="btn btn-default showfilterBlock pull-right" >Show Filters</button>
									<div class="filterBlockDiv updateDropDown" style="width:100%" >
										<div class="updateDropDownArrow">
											<button type="button" class="closedropdown close"  data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
											<div class="row">
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Assigned Cadre</label>
													<select class="form-control chosen-select" id="assignedCadreId" onChange="assignCadreDetailsAllLevel();">
														<option value="0" selected="selected">Select Assigned Cadre</option>
														<option value="1">Party</option>
														<option value="2">Govt</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">State</label>
													 <select class=" form-control chosen-select " id="stateId" onChange="getDistrictsForReferPopup('');">
														 <option value="0">All</option>
														 <option value="1" selected="selected">Andhra Pradesh</option>    
														 <option value="36">Telangana</option>   
													 </select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">District</label>
														<select class="chosen-select " id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
														<option value="0">Select District</option></select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Assembly</label>
													<select class=" form-control chosen-select " id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
													<option value="0">All</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Mandal/Municipality</label>
													<select class=" form-control chosen-select " id="refermandalNameId" onChange="getPanchayatsForReferPopup('');" >
														<option value="0">All</option>
													 </select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Panchayat/Ward</label>
													<select class=" form-control chosen-select " id="referpanchayatId" >
														<option value="0">All</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<button style="margin-top: 25px;" id="searchBtnId" onclick="getLocationFilterAlertData();" class="btn btn-block btn-success m_top20 " type="button"  >Apply Filters</button>
												</div>
											</div>
										</div>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row m_top10">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="alertheadingcolor">
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-3">
										<label style="font-size:14px;" class="textcolor_black text_capital">Alert Category</label>
										<select class="form-control" id="alertCategoryId">           
											<option value="0" selected="selected">All</option>
											<option value="1">Manual</option>
											<option value="2">Print Media</option>
											<option value="3">Electronic Media</option>  
										</select>  
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<label style="font-size:14px;" class="textcolor_black text_capital">Alert Clarification Status</label>
										<select class="form-control chosen-select" id="alertStatusId" >
											<option value="0" selected="selected">All</option>
											<option value="1">Progress</option>
											<!--<option value="2">Notified</option>
											<option value="3">Action In Progess</option>-->
											<option value="2">Completed</option>
											<!--<option value="5">Unable to Resolve</option>
											<option value="6">Action Not Required</option>
											<option value="7">Duplicate</option>-->
										</select>
									</div>
									
									<button style="margin-top: 25px;" id="filterBtnId" onclick="getFilterAlertData();" class="btn  btn-success m_top20 " type="button"  >View</button>
									
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<!--<h4 class="panel-title text_capital"><b><u>alert details</u></b></h4>-->
							<div id="locationLevelDataId" class="m_top20"></div>
							<!--<div class="panel panel-default">
								<div class="panel-heading panel-headingColor">
									<div class="row">
										<div class="col-md-3 col-xs-12 col-sm-3">
											
										</div>
									</div>
								</div>
								<div class="panel-body bg_ff">
									
								</div>
							</div>-->
						</div>
					</div>
				</div>
			</div>	
					
                
					

			
			
        </div>
    </div>
</div>
<!--<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#ccc;">
					<div class="row">
						<div class="col-md-9 col-xs-12 col-sm-6">
							<h4 class="panel-title m_top10">ALERT DASHBOARD</h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6">
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
						<!--<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelId"></div>
						</div>-->
					</div>
					<br/>
					<br/>
					<!--<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="alertCandidateDataId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>        
	</div>
</div>-->
	<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CREATE_ALERT_ENTITLEMENT' )}">
<button target="_blank" class="btn btn-danger btnCreateAlert" data-toggle="tooltip" data-placement="left" onClick="createAlert()" title="Create Alert" ><i class="glyphicon glyphicon-plus" style="font-size:20px;right:-1px;top:-1px"></i></button>
</c:if>
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
				
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">ALERT TYPE</h4>
					</div>
					<div class="panel-body">
						<table class="table table-condensed tableModal">
							<tr>
								<td colspan="2"><b>Type Of Alert :</b><span id="typeId"></span> <b>created on</b> <span  id="createdDate"></span></td>
							</tr>
							<tr>
								<td style="width:50%;">
									<b>Alert Level </b>: <span id="levelId"></span>
								</td>
								<td style="width:50%;">
									<b>Severity </b>: <span id="severityId"></span>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">ALERT LOCATION</h4>
					</div>
					<div class="panel-body">
						<p id="LocationId"></p>
					</div>
				</div>
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">ALERT DESCRIPTION</h4>
					</div>
					<div class="panel-body">
						<p id="descriptionId"></p>
						<div class="media" style="border:1px solid #ddd;padding:8px;margin-top:5px;" id="alertCandidateDataId">
							
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<!--<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div id="alertCandidateDataId"></div>
			</div>
		</div>-->
       <div class="row">
		  <div class="col-md-12 col-xs-12 col-sm-12">
			
			<div id="alertCommentsDiv"></div>
		  </div>  
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default panelAlert">
					<div class="panel-heading">
						<h4 class="panel-title text-success">UPDATE ALERT STATUS</h4>
					</div>
					<div class="panel-body">
						<label>Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<select class="" id="statusId">
							<option value='0'>Select Status</option>
							
							<option value='1'>Created</option>
							<option value='2'>Action In Progess</option>
							<option value='3'>Completed</option>
						</select>
						<label>Comments</label><span ></span>
						<textarea class="form-control" id="commentsId"></textarea>
						<div id="errorId"></div>
						<button class="btn btn-success updateAlertStatusCls">UPDATE</button>
					</div>
				</div>
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

<script type="text/javascript">
if($(window).width() < 500)
{
	$("table").wrap( "<div class='table-responsive'></div>" );
}
$(document).on("click",".menuSelection li",function(){
	$(this).parent(".menuSelection").find("li").removeClass("active");
	$(this).addClass("active");
});
$('.chosenSelect').chosen({width:'100%'});
function createAlert(){
	window.open("createAlertAction.action", '_blank');
}

$('[data-toggle="tooltip"]').tooltip()
var globalStateId = 1;
globalLocation = "state";  
var currentFromDate = moment().subtract(29, 'days').format("MM/DD/YYYY");
var currentToDate = moment().format("MM/DD/YYYY");  

$(document).ready(function(){
	$("#dateRangePickerId").daterangepicker({
		opens:'left',
		startDate:moment().subtract(29, 'days'),    
		endDate:moment(),
		ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
		locale: {
            format: 'MM/DD/YYYY'
        }
			
	});
	$(".ranges").addClass("rangesNew")
});
	$('#dateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
		$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
		$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');     
		currentFromDate = picker.startDate.format('MM/DD/YYYY');
		currentToDate = picker.endDate.format('MM/DD/YYYY'); 
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 

		var levelId = 0;
		var levelValue = 0;
			$('.stateCls').each(function(){
				if($(this).hasClass("active"))  
					levelValue = $(this).attr("attr_state_id");
			});
			
		var statusId=0;
		var fromDate = '';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var	categoryId =0;
		$("#errorId").html("");
		$("#alertCategoryId").val(0).trigger("chosen:updated");
		$("#alertStatusId").val(0).trigger("chosen:updated");
	});

$(document).on("click","#createAlertBtn",function(){
	$("#createAlertModal").modal('show');
	buildLevels();
	showHideSearch("advanceSearch");
	showHideBySearchType();
	disableByLevel(1);
	getAlertsource();
	$("#apptmemberDetailsDiv").html("");
});


getAlertAssignedCandidate();
function getAlertAssignedCandidate()
{
	$("#assignedCadreId option").remove();
	var jsObj={
    			alertId:0,
				task:""
    		}
	$.ajax({
	  type : 'GET',
	  url : 'getAlertAssignedCandidateAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
	  var str='';
	   str+='<option value="0">All</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].id > 0)
				str+='<option value="'+result[i].id+'">'+(result[i].uname).toUpperCase()+'</option>';
			}
		}
		$("#assignedCadreId").html(str);
		/*$("#assignedCadreId").dropkick();
		var select1 = new Dropkick("#assignedCadreId");
		select1.refresh();*/
		$("#assignedCadreId").trigger('chosen:updated');
	});
	
}    
	$(document).on("click",".stateCls",function(){
		$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
		$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
		$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />'); 
		var stateId = $(this).attr("attr_state_Id");
		var alertTypeId = $("#alertTypeId").val();

		var actionTypeId = 1;
		globalStateId = stateId;
		$("#stateId").val(stateId);
		$("#stateId").trigger("chosen:updated");
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		
		var levelId = 0;
		var levelValue = stateId;
				
		var levelId = 0;
		var levelValue = stateId;
				
		var statusId=0;
		var fromDate = '';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var	categoryId =0;
		var actionTypeStatusId = 0;
		var impactScopeId = 0;
		$("#errorId").html("");            
		$("#alertCategoryId").val(0).trigger("chosen:updated");
		$("#alertStatusId").val(0).trigger("chosen:updated");//move  
		getAllAlertsWithoutFilter(alertTypeId,categoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId);
		
	}); 
	$(document).on("click",".locationCls",function(){    
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />'); 
		var locationType=$("input:radio[name=location]:checked").val();
		globalLocation = locationType;
		
		
	});
	$(document).on("change","#alertTypeId",function(){     //move1 
		$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
		$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
		$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		
		var statusId=0;
		var levelId = 0;
		var levelValue = 0;
		var categoryId=0;
		$('.stateCls').each(function(){
			if($(this).hasClass("active"))
				levelValue = $(this).attr("attr_state_id");
		});	

		
		 var fromDate='';
		 var toDate='';
		 var dateStr = $("#dateRangePickerId").val(); 
		 if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		 }
		var alertTypeId = $("#alertTypeId").val();
		var actionTypeId = 1;
		var actionTypeStatusId = 0;
		var impactScopeId = 0;
		getAllAlertsWithoutFilter(alertTypeId,categoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId);		
		
		
		$("#alertCategoryId").val(0);
		$("#alertStatusId").val(0);
		$("#alertCategoryId").trigger('chosen:updated');
		$("#alertStatusId").trigger('chosen:updated');
	});
	
	$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
	$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
	$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
	$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />'); 
	
	getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate);
	
	function getTotalAlertGroupByStatus(stateId,fromDate,toDate){
		
		var alertTypeId = $("#alertTypeId").val();
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,
			toDate : toDate,
			alertyTypeId : alertTypeId         
		}
		$.ajax({
			type : 'POST',      
			url : 'getTotalAlertGroupByStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#overAllCount").html('');
			if(result != null && result.length > 0){
				buildTotalAlertGroupByStatus(result);
			}
		});
	}
	function buildTotalAlertGroupByStatus(result){
		var colorArr = ['#191970','#F08080','#0000CD','#40E0D0','#006400','#FF8C00','#8B0000'];
		var totalAlert = 0;
		for(var i in result){
			totalAlert = totalAlert + result[i].count;
		}
		var str = '';
		if($(window).width() < 500)
		{
			str+='<div class="table-responsive">';
		}
		str+='<table class="table tableCounts" >';
			str+='<tr>';
				str+='<td>';
					str+='<p style="color:#191970;">TOTAL ALERTS</p>';
					if(totalAlert != null && totalAlert>0)
						str+='<h3 style="margin-top:10px !important;"> </u><a href="javascript:{};" class="headerWiseDataCls" attr_position="first" attr_id="0" title="Click here to view total Alerts Details" attr_levlId="0"  attr_category_id="0" attr_search_Location="totalBlock">'+totalAlert+'</a></u></h3>'; 
					else
						str+='<h3>'+totalAlert+'</h3>'; 
				str+='</td>';
				var j = 1;
				for(var i in result){
					str+='<td>';
					str+='<p>'+result[i].status+'</p>';
					if(result[i].count != null && result[i].count >0 )
						str+='<h3 style="color:'+colorArr[j]+';margin-top:10px !important;"><u><a style="color:'+colorArr[j]+'" href="javascript:{};" class="headerWiseDataCls" attr_position="first" attr_id="'+result[i].statusId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_levlId="0"  attr_category_id="0"  attr_search_Location="totalBlock">'+result[i].count+'</a></u></h3>';  
					else
						str+='<h3 style="margin-top:10px !important;">'+result[i].count+'</h3>';
					
					str+='</td>';
					j = j+1;     
				}
			str+='</tr>';
		str+='</table>';
		if($(window).width() < 500)
		{
			str+='</div>';
		}
		$("#overAllCount").html(str);
	}
	
	function getTotalAlertGroupByStatusThenCategory(stateId,fromDate,toDate){
		$("#locationLevelDataId").html('<img src="images/search.gif" />');
		var alertTypeId = $("#alertTypeId").val();
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,      
			toDate : toDate,
			alertTypeId : alertTypeId,
			verificationUserType :"infoCellCommittee"
		}
		$.ajax({
			type : 'POST',      
			url : 'getStatusAndCategoryWiseAlertsCountAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#alertCatTabId").html('');  
			if(result != null && result.length > 0){
				buildTotalAlertGroupByStatusThenCategory(result,alertTypeId);
			}else{
				$("#alertCatTabId").html("No Data Available.");
				$("#locationLevelDataId").html('No Data Available.');  
			}
		});
	}
	
	var colorArrHead = {"Pending":"#F08080","Notified":"#0000CD","Action In Progess":"#40E0D0","Completed":"#006400","Unable to Resolve":"#FF8C00","Action Not Required":"#8B0000"};
	var colorArrForStsHead = {"pending":"#F08080","completed":"#006400"};  
	function buildTotalAlertGroupByStatusThenCategory(result,alertTypeId){
		var colorArr = {"Pending":"#F08080","Notified":"#D8E5F5","Action In Progess":"#C9EBF5","Completed":"#C0E1D8","Unable to Resolve":"#ECDDD6","Action Not Required":"#E7D2D7"};
		var str = '';  
		if($(window).width() < 500)  
		{
			str+='<div class="table-responsive">';
		}
		str+='<table class="table table-condensed b_1">';
		str+='<thead class="bg_CD" style="background-color:#CDCDD9;">';
			str+='<th></th>';    
			for(var i in result[0].statusTypeList[0].categoryTypeList){
				str+='<th class="text-capital text-center" >'+result[0].statusTypeList[0].categoryTypeList[i].category+'</th>';
			}  
		str+='</thead>';
		for(var i in result){
			for(var j in result[i].statusTypeList){
				var appClrHd = colorArrForStsHead[result[i].statusTypeList[j].status];
				str+='<tr>';
				if(result[i].statusTypeList[j].count > 0){  
					str+='<td class="text-capital" style="color:'+appClrHd+';background-color:#eae9ef"><strong>'+result[i].statusTypeList[j].status+'</strong><span class="pull-right text-muted"> </u> <a href="javascript:{};" class="showDtlsForCountCls" attr_alert_type_id="'+alertTypeId+'" attr_position="second" attr_category_id="0" attr_action_type_id="'+result[i].actionTypeId+'" attr_action_type_status_id="'+result[i].statusTypeList[j].actionTypeStatusId+'" title="Click here to view '+result[i].statusTypeList[j].status+' Alerts Details" attr_levlId="0"  attr_search_Location="statusBlock">'+result[i].statusTypeList[j].count+'</a> </u></span></td>';
				}else{
					continue;   
				}
				
				for(var k in result[i].statusTypeList[j].categoryTypeList){
					if(result[i].statusTypeList[j].categoryTypeList[k].count > 0){
						str+='<td style="background-color:#eae9ef" class="text-center"> </u><a href="javascript:{};" class="showDtlsForCountCls" attr_position="second" attr_alert_type_id="'+alertTypeId+'" attr_category_id="'+result[i].statusTypeList[j].categoryTypeList[k].alertCategoryId+'" attr_action_type_id="'+result[i].actionTypeId+'" attr_action_type_status_id="'+result[i].statusTypeList[j].actionTypeStatusId+'" title="Click here to view '+result[i].statusTypeList[j].categoryTypeList[k].category+' Alerts Details" attr_levlId="0"  attr_search_Location="statusBlock">'+result[i].statusTypeList[j].categoryTypeList[k].count+' </a></u></td>';
					}else{
						str+='<td style="background-color:#eae9ef" class="text-center">0</td>';  
					}
					
				}
				str+='</tr>';  
			}
		}
		str+='</table>';
		if($(window).width() < 500)
		{
			str+='</div>';
		}
		$("#alertCatTabId").html(str); 
		
		
		var fromDate='';  
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}  
		
		var levelValue = 0;
		$('.stateCls').each(function(){
			if($(this).hasClass("active"))   
				levelValue = $(this).attr("attr_state_id");
		});
		var alertCategoryId = 0;
		var actionTypeStatusId = 0;
		var actionTypeId = 1;
		var impactScopeId = 0;  
		getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId);
	
	}
	
	
	$("#alertTypeId").chosen();
	$("#alertCategoryId").chosen();  
	$("#alertStatusId").chosen();    
	$("#stateId").chosen();
	$("#assignedCadreId").chosen();
	$("#referdistrictId").chosen();
	$("#referconstituencyId").chosen();
	$("#refermandalNameId").chosen();
	$("#referpanchayatId").chosen();
	
	$(".datatableId").dataTable();
		$(document).on("click",".showfilterBlock",function(){  
			$("#assignedCadreId").val(0).trigger('chosen:updated');
			$("#stateId").val(globalStateId).trigger('chosen:updated');
			//$('#referdistrictId').empty(); 
	        $("#referdistrictId").val(0).trigger('chosen:updated');
			//$('#referdistrictId').append('<option value="0">Select District</option>'); 
			$('#referconstituencyId').empty(); 
			$('#referconstituencyId').append('<option value="0">All</option>'); 
			$("#referconstituencyId").trigger('chosen:updated');
			$('#refermandalNameId').empty(); 
			$('#refermandalNameId').append('<option value="0">All</option>'); 
			$("#refermandalNameId").val(0).trigger('chosen:updated');
			$('#referpanchayatId').empty();
            $('#referpanchayatId').append('<option value="0">All</option>'); 			
			$("#referpanchayatId").val(0).trigger('chosen:updated');
		$(".filterBlockDiv").toggle();  
	});
	$(document).on("click",".closedropdown",function(){
		$(".filterBlockDiv").hide();
	});
	$(document).on("click",".undoIcon",function(){
		$(this).find("span").toggleClass("glyphicon-chevron-up").toggleClass("glyphicon-chevron-down")
	}); 
	getDistricts();  
	function getDistricts() {
		var jobj = {
			stateId : 1  
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			if(result != null && result.length > 0){ 
				var str = ""; 
				str+='<option value="0">Select District</option>'; 
				for(var i in result){
					if(result[i].id > 0)
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#referdistrictId").html(str);
				$("#referdistrictId").trigger('chosen:updated');
				$("#referdistrictId").chosen();         
			}  
		});     
	}  
function assignCadreDetailsAllLevel(){
	
            $('#referdistrictId').empty(); 
			$('#referdistrictId').append('<option value="0">Select District</option>'); 
			$("#referdistrictId").trigger('chosen:updated');
			$('#refermandalNameId').empty();
            $("#stateId").val(globalStateId).trigger('chosen:updated');			
			$('#refermandalNameId').append('<option value="0">All</option>'); 
			$("#refermandalNameId").trigger('chosen:updated');
			$('#referpanchayatId').empty(); 
			$('#referpanchayatId').append('<option value="0">All</option>'); 
			$("#referpanchayatId").trigger('chosen:updated');
			$('#referconstituencyId').empty(); 
			$('#referconstituencyId').append('<option value="0">All</option>'); 
			$("#referconstituencyId").trigger('chosen:updated');
	
}    

function buildAlertData(result,jsObj)
{
	if(result == null || result.length == 0)
	{
		$("#locationLevelDataId").html('No Data Available..');
		return;
	}
	var str='';
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered bg_ff text-center" id="alertDataTableId">';
	str+='<thead>';
	str+='<th>Alert Location</th>';
	str+='<th>Alert Source </th>';
	str+='<th>Title</th>';
	str+='<th>Alert Category </th>';
	str+='<th>Alert Type </th>';
	str+='<th>Alert Status</th>';
	str+='<th>Alert Verification Status</th>';  
	str+='<th>Involved Candidates</th>';
	str+='<th>Created Date</th>'; 
	str+='<th>Information Source </th>';
	str+='<th>Severity</th>';
	
	str+='<th></th>';	
	
	str+='</thead>';
	str+='<tbody>';
	var j=0;
	for(var i in result)
	{
		j++;
		str+='<tr>';	
		 var locationDtls = result[i].locationVO;
		if(locationDtls != null){
			if(locationDtls.constituencyName != null && locationDtls.constituencyName.length > 0){
				str+='<td>'+locationDtls.constituencyName+' Constituency</td>';
			}else if(locationDtls.districtName != null && locationDtls.districtName.length > 0){
				str+='<td>'+locationDtls.districtName+' District</td>';
			}else if(locationDtls.state != null && locationDtls.state.length > 0){
				str+='<td>'+locationDtls.state+'</td>';
			}
		}
		else{
			str+='<td> - </td>';
		}
		str+='<td>'+result[i].alertSource+'</td>';
		str+='<td>'+result[i].title+'</td>';
		str+='<td>'+result[i].alertCategoryName+'</td>';
		if(result[i].alertType == 1){
			str+='<td>Party</td>';
		}else if(result[i].alertType == 2){
			str+='<td>Govt</td>';
		}else if(result[i].alertType == 3){
			str+='<td>Other</td>';
		}else{
			str+='<td>'+result[i].alertType+'</td>';
		}
		str+='<td>'+result[i].status+'</td>';
		str+='<td>'+result[i].verificationStatus+'</td>';
		str+='<td>'+result[i].count+'</td>';
		str+='<td>'+result[i].date+'</td>';
		str+='<td>'+result[i].userType+'</td>';
		str+='<td><span class="circle '+result[i].severity+'"></span>'+result[i].severity+'</td>';
		
		str+='<td><i class="glyphicon glyphicon-eye-open alertModel"  target="_blank" title="Click here to View Alert Details" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+' "></i></td>';
		  
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#locationLevelDataId").html(str);
	$("#alertDataTableId").dataTable(); 
}

$(document).on("click",".alertModel",function(){
var GlobalalertId = $(this).attr("attr-id");
window.open("alertDetailsAction.action?alertId="+GlobalalertId+"", '_blank');
});

$(document).on("click",".showDtlsForCountCls",function(){
	var alertTypeId=$(this).attr("attr_alert_type_id");
	var alertCategoryId=$(this).attr("attr_category_id");
	var actionTypeId=$(this).attr("attr_action_type_id");
	var actionTypeStatusId=$(this).attr("attr_action_type_status_id");
	var levelId = $(this).attr("attr_levlId");
	var levelValue = 0;
	var impactScopeId = 0;
	$('.stateCls').each(function(){
		if($(this).hasClass("active"))   
			levelValue = $(this).attr("attr_state_id");
	});
	var fromDate='';  
	var toDate='';
	var dateStr = $("#dateRangePickerId").val(); 
	
	if(dateStr !=null && dateStr.length>0){
		fromDate = dateStr.split("-")[0];
		toDate = dateStr.split("-")[1];
	}
	$("#alertCategoryId").val(alertCategoryId);
	$("#alertCategoryId").trigger("chosen:updated");  
	$("#alertStatusId").val(actionTypeStatusId);
	$("#alertStatusId").trigger("chosen:updated");
	$("#locationLevelDataId").html('<img src="images/search.gif" />');
	getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId);
});
function getAllAlertsWithoutFilter(alertTpeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId){
	var jsObj =
		{
			alertTypeId:alertTpeId,
			categoryId:alertCategoryId,
			actionTypeId:actionTypeId,
			actionTypeStatusId:actionTypeStatusId,
			levelValue:levelValue,
			fromDate :fromDate,
			toDate   :toDate,
			impactScopeId:impactScopeId,
			statusId : 0,
			fromDate2 : "",  
			toDate2 : "",
			verificationUserType :"infoCellCommittee"
			
		}
		$.ajax({  
			type:'GET',          
			url: 'getAllAlertsWithoutFilter.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
					buildAlertData(result,jsObj);
		});
}

function initSelectBoxForFirstPos(statusId){         
	$("#alertCategoryId").val(0);
	$("#alertCategoryId").trigger("chosen:updated");
	$("#alertStatusId").val(statusId);
	$("#alertStatusId").trigger("chosen:updated"); 
}  
function initSelectBoxForSecondPos(statusId,category){    
	$("#alertStatusId").val(statusId);
	$("#alertStatusId").trigger("chosen:updated");
	$("#alertCategoryId").val(category);
	$("#alertCategoryId").trigger("chosen:updated");   
}
function getFilterAlertData(){
	$("#locationLevelDataId").html('<img src="images/search.gif" />');
	var fromDate='';
	var toDate='';
	var dateStr = $("#dateRangePickerId").val(); 
	if(dateStr !=null && dateStr.length>0){
		fromDate = dateStr.split("-")[0];
		toDate = dateStr.split("-")[1];
	}
	
	var levelValue = 0;
	$('.stateCls').each(function(){
		if($(this).hasClass("active"))   
			levelValue = $(this).attr("attr_state_id");
	});
	var alertTypeId = $("#alertTypeId").val();
	var alertCategoryId = $('#alertCategoryId').val();
	var actionTypeStatusId = $('#alertStatusId').val();
	var actionTypeId = 1;
	var impactScopeId = 0;
	getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId);	
	
}
function getLocationFilterAlertData()
{
	$(".filterBlockDiv").hide();
	$("#locationLevelDataId").html('<img src="images/search.gif" />');
	var stateId = $("#stateId").val();
	var districtId = $("#referdistrictId").val();
	var constituencyId = $("#referconstituencyId").val();
	var mandalId = $("#refermandalNameId").val();
	var panchayatId = $("#referpanchayatId").val();
	var mandalType = $("#refermandalNameId option:selected").text();
	
	if(mandalType.indexOf("Mandal") == -1){
		mandalType = "localbody";
	}
	else{
		mandalType = "mandal";
	}
	var assignedCadreId =  $("#assignedCadreId").val();
	if(assignedCadreId.length == 0)
		assignedCadreId =0;
	var fromDate='';
	var toDate='';
	var dateStr = $("#dateRangePickerId").val(); 
	if(dateStr !=null && dateStr.length>0){
		fromDate = dateStr.split("-")[0];
		toDate = dateStr.split("-")[1];
	}
	var alertTpeId = $('#alertTypeId').val();	
	var alertCategoryId = $('#alertCategoryId').val();	
	var statusId = 0;	  
	var actionTypeStatusId = $('#alertStatusId').val();
	if(alertTpeId== null || alertTpeId.length==0)
		alertTpeId=0;
	var jsObj =
		{
			statusId:statusId,
			alertTypeId:alertTpeId,  
			stateId  : stateId,
			districtId :districtId,
			constituencyId :constituencyId,
			mandalId   :mandalId,
			panchayatId:panchayatId,
			mandalType:mandalType,
			fromDate:fromDate,
			toDate:toDate,
			assignedCadreId:assignedCadreId,    
			involvedCadreId:0, 
			impactId:0,   
			categoryId:alertCategoryId,
			actionTypeStatusId:actionTypeStatusId,        
			task : "verification",
			fromDate2 : "",
			toDate2 : "",
			radioVal : '',
			verificationUserType :"infoCellCommittee"

		}
	$.ajax({
		type:'GET',
		url: 'getLocationFilterAlertDataAction.action',//imp
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildAlertData(result,jsObj);
	});
}
</script>  
</body>
</html>