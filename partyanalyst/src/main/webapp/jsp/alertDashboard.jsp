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
						<div class="col-md-6 col-xs-12 col-sm-6">
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
						<div class="col-md-3 col-xs-12 col-sm-3">
							<ul class="menuSelection">
								<li attr_state_Id="0" class="stateCls">ALL</li>
								<li attr_state_Id="1" class="stateCls active">AP</li>
								<li attr_state_Id="36" class="stateCls">TS</li>   
							</ul>  
							<div class="btn-group alertMenuDiv">
							  <i class="glyphicon glyphicon-align-justify alertMenu dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>
							  <ul class="dropdown-menu pull-right alertMenuDropdown">
								<li><a href="#">Create New Alert</a></li>
								<li><a href="#">View Alert</a></li>
								<li><a href="#">Alerts Overview</a></li>
							  </ul>
							</div>
						</div>
					</div>  
                </div> 
						
                <div class="panel-body bg_EF">
                	<!--<div class="table-responsive" id="locationLevelId"></div>-->
					<div class="table-responsive" id="">
					</div>
					<div class="row">
						
						<div class="col-md-12 col-xs-12 col-sm-12">
							<table class="table tableCounts" id="overAllCount"></table>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<table class="table table-condensed b_1" id="alertCatTabId"></table>  
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-body bg_EF">
									<table class="table tableAlert" id="locWiseAltCntId"></table>
								</div>
							</div>
						</div>
					</div>
					<!--location Filter-->
					<div class="col-md-3 col-xs-12 col-sm-6" >
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
						</div>
					<!--location Filter End-->
					
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
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');     
		getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);   
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
				str+='<option value="'+result[i].id+'">'+result[i].uname+'</option>';
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
		var stateId = $(this).attr("attr_state_Id");
		globalStateId = stateId;
		getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
		getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
		getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);  
	});  
	$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
	$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
	$("#locWiseAltCntId").html('<img style="margin-left:495px;width:30px;height:30px;" src="images/search.gif" />');
	getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
	getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate); 
	getAlertCountGroupByLocationThenStatus(globalStateId,currentFromDate,currentToDate);   
	function getTotalAlertGroupByStatus(stateId,fromDate,toDate){
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,
			toDate : toDate            
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
		str+='<tr>';
		str+='<td>';
			str+='<h4 style="color:#191970;">TOTAL ALERTS</h4>';
			if(totalAlert != null && totalAlert>0)
				str+='<h3> </u><a href="javascript:{};" class="headerWiseDataCls" attr_id="0" title="Click here to view total Alerts Details" attr_levlId="2"  attr_category_id="0">'+totalAlert+'</a></u></h3>'; 
			else
				str+='<h3>'+totalAlert+'</h3>'; 
		str+='</td>';
		var j = 1;
		for(var i in result){
			str+='<td>';
			str+='<h4 style="color:'+colorArr[j]+'">'+result[i].status+'</h4>';
			if(result[i].count != null && result[i].count >0 )
				str+='<h3><u><a href="javascript:{};" class="headerWiseDataCls" attr_id="'+result[i].statusId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_levlId="2"  attr_category_id="0">'+result[i].count+'</a></u></h3>';  
			else
				str+='<h3>'+result[i].count+'</h3>';
			
			str+='</td>';
			j = j+1;     
		}
		str+='</tr>';
		
		$("#overAllCount").html(str);
	}
	
	function getTotalAlertGroupByStatusThenCategory(stateId,fromDate,toDate){
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,
			toDate : toDate            
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
	function buildTotalAlertGroupByStatusThenCategory(result){
		var colorArr = {"Pending":"#F08080","Notified":"#D8E5F5","Action In Progess":"#C9EBF5","Completed":"#C0E1D8","Unable to Resolve":"#ECDDD6","Action Not Required":"#E7D2D7"};
		var colorArrHead = {"Pending":"#F08080","Notified":"#0000CD","Action In Progess":"#40E0D0","Completed":"#006400","Unable to Resolve":"#FF8C00","Action Not Required":"#8B0000"};
		var str = '';  
		str+='<thead class="bg_CD">';
		str+='<th>&nbsp;</th>';
		for(var i in result[0].subList1){
			str+='<th class="text-capital">'+result[0].subList1[i].category+'</th>';
		}  
		str+='</thead>';
		for(var i in result){  
			str+='<tr>';
			var appClr = colorArr[result[i].status];
			var appClrHd = colorArrHead[result[i].status];
			if(result[i].count != null && result[i].count > 0)
				str+='<td class="text-capital" style="color:'+appClrHd+'"><strong>'+result[i].status+'</strong><span class="pull-right text-muted"> </u> <a href="javascript:{};" class="headerWiseDataCls" attr_category_id="0" attr_id="'+result[i].statusId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_levlId="2">'+result[i].count+'</a> </u></span></td>';
			else
				str+='<td class="text-capital" style="color:'+appClrHd+'"><strong>'+result[i].status+'</strong><span class="pull-right text-muted"> '+result[i].count+' </span></td>';
			
			for(var j in result[i].subList1){
				if(result[i].subList1[j].categoryCount != null && result[i].subList1[j].categoryCount >0)
					str+='<td style="background-color:'+appClr+'"> </u><a href="javascript:{};" class="headerWiseDataCls" attr_id="'+result[i].statusId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_category_id="'+result[i].subList1[j].categoryId+'" attr_levlId="2">'+result[i].subList1[j].categoryCount+' </a></u></td>';
				else
					str+='<td style="background-color:'+appClr+'"> '+result[i].subList1[j].categoryCount+' </td>';
			}
			str+='</tr>';   
		}
		$("#alertCatTabId").html(str);  
		
	}
	 
	function getAlertCountGroupByLocationThenStatus(stateId,fromDate,toDate){
		var jsObj = { 
			stateId : stateId,     
			fromDate : fromDate,
			toDate : toDate            
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
		str+='<thead>';
		str+='<th>&nbsp;</th>';
		for(var i in result[0].subList1){
			str+='<th class="text-capital">'+result[0].subList1[i].category+'</th>'; 
		}
		str+='</thead>';
		for(var i in result){
			if(result[i].statusId==1 || result[i].statusId==5 || result[i].statusId==6 || result[i].statusId==7 || result[i].statusId==8 || result[i].statusId==9 || result[i].statusId==10){
				continue;
			}
			str+='<tr>';
			if(result[i].count != null && result[i].count>0)
				str+='<td>'+result[i].status+'<span class="pull-right text-muted"><u><a href="javascript:{};" class="headerWiseDataCls" attr_id="0" title="Click here to view '+result[i].status+' Alerts Details" attr_levlId="'+result[i].statusId+'"  attr_category_id="0">'+result[i].count+'</a></u></span></td>';							
			else
				str+='<td>'+result[i].status+'<span class="pull-right text-muted"><u><'+result[i].count+'</span></td>';
			
			for(var j in result[i].subList1){
				if(result[i].subList1[j].categoryCount != null && result[i].subList1[j].categoryCount > 0)
					str+='<td><u><a href="javascript:{};" class="headerWiseDataCls" attr_id="'+result[i].subList1[j].categoryId+'" title="Click here to view '+result[i].status+' Alerts Details" attr_levlId="'+result[i].statusId+'"  attr_category_id="0">'+result[i].subList1[j].categoryCount+'</u></td>';
				else				
					str+='<td>'+result[i].subList1[j].categoryCount+'</td>';
			}
			str+='</tr>';
		}
		$("#locWiseAltCntId").html(str);  
		
	}
</script>
</body>
</html>