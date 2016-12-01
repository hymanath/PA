<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alert Dashboard </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	
	<link href="dist/alertDashBoard/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/alertDashBoard/dist/css/custom.css" rel="stylesheet" type="text/css">
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
	<script src="dist/alertDashBoard/alertDashboard.js" type="text/javascript"></script>
	
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="js/LocationHierarchy/locationHierarchyAlert.js" type="text/javascript"></script>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
</head>                                         							
<body style="position:relative;">
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default">
           		<div class="panel-heading bg_cc">
					<div class="row">
						<div class="col-md-5 col-xs-12 col-sm-6">    
							<h4 class="panel-title text-capital">alert dashboard</h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6">
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
									<option value="0">All</option>
									<option value="1" selected="selected">Party</option>            
									<option value="2">Govt</option>
									<option value="3">Others</option>
								</select>
							</div>
					</div>  
                </div> 
						
                <div class="panel-body">
                	<!--<div class="table-responsive" id="locationLevelId"></div>-->
					<div class="table-responsive" id="">
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="overAllCount"></div>
							
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div id="alertCatTabId"></div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel panel-default">
								<div class="panel-body bg_EF">
									<div  id="locWiseAltCntId"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingThree">
							  <h4 class="panel-title text-capital">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
								  location wise alerts 
								<span class="glyphicon glyphicon-chevron-down pull-right undoIcon"></span></a>
							  </h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
							  <div class="panel-body">
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
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<div class="bg_cc" id="multiLocationId"></div>
								</div>
							  </div>
							</div>
						</div>
			

							
						</div>
					</div>
					<!--location Filter-->
					<!--<div class="col-md-3 col-xs-12 col-sm-6" >
							<label>Assigned Cadre</label>
							 <select class="chosenSelect" id="assignedCadreId">
								 <option value="0">All</option>
								 
							 </select>
						</div>
					
					<div class="col-md-3 col-xs-12 col-sm-6  stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" id="stateId" onChange="getDistrictsForReferPopup('');">
								 <option value="0">All</option>
								 <option value="1">AP</option>
								 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
							 <option value="0">All</option></select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
							<option value="0">All</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup('');" >
								<option value="0">All</option>
							 </select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" >
							<option value="0">All</option>
							</select>
						</div>
						
					<div class="col-md-2 col-xs-12 col-sm-4">
							<button style="margin-top: 25px;" id="searchBtnId" onclick="getLocationFilterAlertData();" class="btn btn-block btn-success m_top20 " type="button">View</button>
						</div>-->
					<!--location Filter End-->
				
					<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="col-md-4 col-xs-12 col-sm-4">
						<h3>VIEW / ASSIGN ALERT</h3>
					</div>
					<div class="row pull-right">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="col-md-3 col-xs-12 col-sm-4">
								<button type="button" class="btn btn-default showfilterBlock" >Show Filters</button>
							</div>
							<div class="filterBlockDiv updateDropDown" style="width:1000px" >
							<div class="col-md-12 col-xs-12 col-sm-12">
							 <button type="button" class="closedropdown close"  data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
							</div>
							<div class="updateDropDownArrow">
							
								<div class="col-md-3 col-xs-12 col-sm-6">
									<label style="font-size:14px;" class="textcolor_black">Assigned Cadre</label>
									<select class="form-control chosen-select" id="assignedCadreId" >
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
										<option value="0">All</option></select>
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
								<label style="font-size:14px;" class="textcolor_black text_capital">Alert Status</label>
								<select class="form-control chosen-select" id="alertStatusId" >
									<option value="0" selected="selected">All</option>
									<option value="1">Pending</option>
									<option value="2">Notified</option>
									<option value="3">Action In Progess</option>
									<option value="4">Completed</option>
									<option value="5">Unable to Resolve</option>
									<option value="6">Action Not Required</option>
								</select>
							</div>
							
							<button style="margin-top: 25px;" id="searchBtnId" onclick="getLocationFilterAlertData();" class="btn  btn-success m_top20 " type="button"  >View</button>
							
						</div>
					</div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading panel-headingColor">
							<div class="row">
								<div class="col-md-3 col-xs-12 col-sm-3">
									<h4 class="panel-title text_capital"><b>alert details</b></h4>
								</div>
								
							</div>
							
							
						</div>
						<div class="panel-body bg_ff">
							<div id="locationLevelDataId"></div>
						</div>
					</div>
						
				</div>
			</div>
					<!--<div class="row  m_top10">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelDataId"></div>
						</div>
					</div>-->
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
var currentFromDate = moment().subtract(29, 'days').format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");  

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
        }
	});
	//$("#dateRangePickerId").val(moment().subtract(29, 'days').format("MM/DD/YYYY")+'-'+moment().format("MM/DD/YYYY"))
	$(".ranges").addClass("rangesNew")
});
	$('#dateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
		$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
		$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');     
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');     
		getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategoryLocationWise(globalStateId,currentFromDate,currentToDate,globalLocation);

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
		getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
		
	});
	
$(document).on("change","#dateRangePickerId",function(){
	$("#locationLevelDataId").html('');
	getLocationLevelAlertCount();
})
$(document).on("click","#createAlertBtn",function(){
	$("#createAlertModal").modal('show');
	buildLevels();
	showHideSearch("advanceSearch");
	showHideBySearchType();
	disableByLevel(1);
	getAlertsource();
	$("#apptmemberDetailsDiv").html("");
});
//$(".dropkickClass").dropkick();

getAlertAssignedCandidate();
function getAlertAssignedCandidate()
{

	//$("#alertCommentsDiv").html('<img src="images/search.gif" />');
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
	  //buildAlertCommentsForTracking(result,"");
	  var str='';
	   str+='<option value="0">ALL</option>';
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
		globalStateId = stateId;
		$("#stateId").val(stateId);
		$("#stateId").trigger("chosen:updated");   
		getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategoryLocationWise(globalStateId,currentFromDate,currentToDate,globalLocation);
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
		$("#errorId").html("");
		getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
	}); 
	$(document).on("click",".locationCls",function(){
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />'); 
		var locationType=$("input:radio[name=location]:checked").val();
		globalLocation = locationType;
		getTotalAlertGroupByStatusThenCategoryLocationWise(globalStateId,currentFromDate,currentToDate,globalLocation);
		
	});
	$(document).on("change","#alertTypeId",function(){      
		$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
		$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
		$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
		$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');

		getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategoryLocationWise(globalStateId,currentFromDate,currentToDate,globalLocation);
		
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
					
		getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
		//crearing Fields	
		$("#alertCategoryId").val(0);
		$("#alertStatusId").val(0);
		$("#alertCategoryId").trigger('chosen:updated');
		$("#alertStatusId").trigger('chosen:updated');
	});
	
	$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
	$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
	$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
	$("#multiLocationId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />'); 
	getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
	getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
	getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);
	getTotalAlertGroupByStatusThenCategoryLocationWise(globalStateId,currentFromDate,currentToDate,globalLocation);
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
		var alertTypeId = $("#alertTypeId").val();
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,
			toDate : toDate,
			alertyTypeId : alertTypeId
		}
		$.ajax({
			type : 'POST',      
			url : 'getTotalAlertGroupByStatusThenCategoryAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#alertCatTabId").html('');  
			if(result != null && result.length > 0){
				buildTotalAlertGroupByStatusThenCategory(result);
			}
		});
	}
	var colorArrHead = {"Pending":"#F08080","Notified":"#0000CD","Action In Progess":"#40E0D0","Completed":"#006400","Unable to Resolve":"#FF8C00","Action Not Required":"#8B0000"};
	function buildTotalAlertGroupByStatusThenCategory(result){
		var colorArr = {"Pending":"#F08080","Notified":"#D8E5F5","Action In Progess":"#C9EBF5","Completed":"#C0E1D8","Unable to Resolve":"#ECDDD6","Action Not Required":"#E7D2D7"};
		//var colorArrHead = {"Pending":"#F08080","Notified":"#0000CD","Action In Progess":"#40E0D0","Completed":"#006400","Unable to Resolve":"#FF8C00","Action Not Required":"#8B0000"};
		var str = '';  
		str+='<table class="table table-condensed b_1">';
			str+='<thead class="bg_CD" style="background-color:#CDCDD9;">';
				str+='<th>&nbsp;</th>';
				for(var i in result[0].subList1){
					str+='<th class="text-capital text-center" >'+result[0].subList1[i].category+'</th>';
				}  
			str+='</thead>';
			for(var i in result){  
				str+='<tr>';
				var appClr = colorArr[result[i].status];
				var appClrHd = colorArrHead[result[i].status];
				if(result[i].count != null && result[i].count > 0)
					str+='<td class="text-capital" style="color:'+appClrHd+';background-color:#eae9ef"><strong>'+result[i].status+'</strong><span class="pull-right text-muted"> </u> <a href="javascript:{};" class="headerWiseDataCls" attr_position="second" attr_category_id="0" attr_id="'+result[i].statusId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_levlId="0"  attr_search_Location="statusBlock">'+result[i].count+'</a> </u></span></td>';
				else  
					str+='<td class="text-capitalize" style="color:'+appClrHd+'"><strong>'+result[i].status+'</strong><span class="pull-right text-muted"> '+result[i].count+' </span></td>';
				
				for(var j in result[i].subList1){
					if(result[i].subList1[j].categoryCount != null && result[i].subList1[j].categoryCount >0)
						str+='<td style="background-color:'+appClr+'" class="text-center"> </u><a href="javascript:{};" class="headerWiseDataCls" attr_position="second" attr_id="'+result[i].statusId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_category_id="'+result[i].subList1[j].categoryId+'" attr_levlId="0"  attr_search_Location="statusBlock">'+result[i].subList1[j].categoryCount+' </a></u></td>';
					else
						str+='<td style="background-color:'+appClr+'" class="text-center"> '+result[i].subList1[j].categoryCount+' </td>';
				}
				str+='</tr>';   
			}
		str+='</table>';
		$("#alertCatTabId").html(str);  
	}
	 
	function getAlertCountGroupByLocationThenStatus(stateId,fromDate,toDate){
		var alertTypeId = $("#alertTypeId").val();
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,
			toDate : toDate,
			alertyTypeId : alertTypeId
		}
		$.ajax({
			type : 'POST',      
			url : 'getAlertCountGroupByLocationThenStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){  
			$("#locWiseAltCntId").html('');     
			if(result != null && result.length > 0){
				buildAlertCountGroupByLocationThenStatus(result);
			}
		});
	}
	function buildAlertCountGroupByLocationThenStatus(result){
		var str = '';
		if($(window).width() < 500)
		{
			str+='<div class="table-responsive">';
		}
		str+='<h4>IMPACTED AREA ALERTS</h4>';
		str+='<table class="table tableAlert">';
		str+='<thead>';
		str+='<th>&nbsp;</th>';
		for(var i in result[0].subList1){
			str+='<th class="text-capital">'+result[0].subList1[i].category+'</th>'; 
		}
		str+='</thead>';
		for(var i in result){
			if(result[i].statusId==12){ //7(v)+9(m)=12 for village/mandal                     
				continue;  
			}            
			str+='<tr>';   
			if(result[i].count != null && result[i].count>0)
				str+='<td>'+result[i].status+'<span class="pull-right text-muted"><u><a href="javascript:{};" class="headerWiseDataCls" attr_position="third" attr_id="0" title="Click here to view '+result[i].status+' Impact Alerts Details" attr_levlId="'+result[i].statusId+'"  attr_category_id="0"  attr_search_Location="locationBlock">'+result[i].count+'</a></u></span></td>';							
			else
				str+='<td>'+result[i].status+'<span class="pull-right text-muted"><u><'+result[i].count+'</span></td>';
			
			for(var j in result[i].subList1){   
				if(result[i].subList1[j].categoryCount != null && result[i].subList1[j].categoryCount > 0)
					str+='<td class="text-center"><u><a href="javascript:{};" class="headerWiseDataCls" attr_position="third" attr_id="'+result[i].subList1[j].categoryId+'" title="Click here to view '+result[i].status+' Impact Alerts Details" attr_levlId="'+result[i].statusId+'"  attr_category_id="0"  attr_search_Location="locationBlock">'+result[i].subList1[j].categoryCount+'</u></td>';
				else				
					str+='<td class="text-center">'+result[i].subList1[j].categoryCount+'</td>';
			}
			str+='</tr>';       
		}
		str+='</table>';
		if($(window).width() < 500)
		{
			str+='</div>';
		}
		$("#locWiseAltCntId").html(str);  
		
	}
	
	function getTotalAlertGroupByStatusThenCategoryLocationWise(stateId,fromDate,toDate,globalLocation){
		var alertTypeId = $("#alertTypeId").val();
		var jsObj = { 
			stateId : stateId,             
			fromDate : fromDate,
			toDate : toDate,
			Location : globalLocation,
			alertyTypeId : alertTypeId        
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getTotalAlertGroupByStatusThenCategoryLocationWiseAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			$("#multiLocationId").html('');
			if(result != null && result.length > 0){
				buildTotalAlertGroupByStatusThenCategoryLocationWise(result,globalLocation);     
			}
		});
	}
	function buildTotalAlertGroupByStatusThenCategoryLocationWise(result,globalLocation){
		var str = '';
		var levelId=2;
		
		if(globalLocation == 'State')
			levelId= 2;
		else if(globalLocation == 'District')
			levelId= 3;
		else if(globalLocation == 'Constituency')
			levelId= 4;
		else if(globalLocation == 'Village')
			levelId= 6;
		
		for(var i in result){
			str+='<table class="table b_1">';
				str+='<thead class="bg_ff">';
					str+='<th>'+result[i].locationName+'</th>';
					for(var l in result[0].subList2[0].subList1){
						str+='<th class="text-center">'+result[0].subList2[0].subList1[l].category+'</th>';
					}
				str+='</thead>';
				str+='<tbody class="bg_EF">';
					for(var j in result[i].subList2){
						str+='<tr>';  
						var cnt = 0;
						for(var k in result[i].subList2[j].subList1){
							cnt = parseInt(cnt) + parseInt(result[i].subList2[j].subList1[k].categoryCount);
						}
						var appClr = colorArrHead[result[i].subList2[j].status];
						if(cnt != null && cnt>0)
							str+='<td class="text-capitalize" style="color:'+appClr+';width:200px !important;"><strong>'+result[i].subList2[j].status+'</strong><span class="pull-right"> <u><a class="belowLocationCls" href="javascript:{};" attr_id="'+result[i].subList2[j].statusId+'" title="Click here to view '+globalLocation+' Alerts Details" attr_levlValue="'+result[i].locationId+'" attr_levlid="'+levelId+'" attr_category_id="0" attr_search_location="locationInnerBlock">'+cnt+'</a></u></span></td>';
						else
							str+='<td class="text-capital" style="color:'+appClr+'"><strong>'+result[i].subList2[j].status+'</strong><span class="pull-right"> '+cnt+'</span></td>';
						
						for(var k in result[i].subList2[j].subList1){
							if(result[i].subList2[j].subList1[k].categoryCount != null && result[i].subList2[j].subList1[k].categoryCount>0)
								str+='<td class=" text-center"> <u><a class="belowLocationCls" href="javascript:{};" attr_id="'+result[i].subList2[j].statusId+'" title="Click here to view '+globalLocation+' Alerts Details" attr_levlid="'+levelId+'" attr_category_id="'+result[i].subList2[j].subList1[k].categoryId+'" attr_search_location="locationInnerBlock" attr_levlValue="'+result[i].locationId+'" > '+result[i].subList2[j].subList1[k].categoryCount+' </u></td>';
							else
								str+='<td class=" text-center">'+result[i].subList2[j].subList1[k].categoryCount+'</td>';
						}
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';     
		}
		$("#multiLocationId").html(str);      
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
		$(".filterBlockDiv").toggle();  
	});
	$(document).on("click",".closedropdown",function(){
		$(".filterBlockDiv").hide();
	});
</script>
</body>
</html>