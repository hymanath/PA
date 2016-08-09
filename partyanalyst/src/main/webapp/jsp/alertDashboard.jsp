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
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	<script src="dist/alertDashBoard/alertDashboard.js" type="text/javascript"></script>
	
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	
 </head>                                         							
<body style="position:relative;">
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default">
           		<div class="panel-heading bg_cc">
					<div class="row">
						<div class="col-md-9 col-xs-12 col-sm-6">
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
					</div>
                	
                </div> 

						
                <div class="panel-body bg_EF">
                	<div class="table-responsive" id="locationLevelId">
                       
                    </div>
                    <!--<h4 class="text-success text-capital m_top10">view alerts<small class="text-capitalize">responsible alert owner wise</small></h4>
                    <div class="row">
                    	<div class="col-md-3 col-xs-12 col-sm-6">
                        	<label>Assigning To</label>
                            <select class="selectChosen">
                            	<option>Constituency Incharge</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                        	<label>State</label>
                            <select class="selectChosen">
                            	<option>Constituency Incharge</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                        	<label>District</label>
                            <select class="selectChosen">
                            	<option>Constituency Incharge</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                        	<label>Constituency</label>
                            <select class="selectChosen">
                            	<option>Constituency Incharge</option>
                            </select>
                        </div>
                    </div>-->
					<div class="row  m_top10">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="locationLevelDataId"></div>
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
						<select class="dropkickClass" id="statusId">
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
function createAlert(){
	window.open("createAlertAction.action", '_blank');
}

$('[data-toggle="tooltip"]').tooltip()

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
$(".dropkickClass").dropkick();
getAlertAssignedCandidate();
function getAlertAssignedCandidate()
{
	alert(3);
	//$("#alertCommentsDiv").html('<img src="images/search.gif" />');
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
	});
	
}
</script>
</body>
</html>