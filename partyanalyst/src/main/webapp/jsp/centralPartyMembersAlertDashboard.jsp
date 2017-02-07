<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CENTRAL MEMBERS ALERT DASHBOARD</title>

<link href="dist/alertDashBoard/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/css/custom.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
			<div class="panel panel-default">
				<div class="panel-heading panel-headingColor">
					<div class="row">
						<div class="col-md-5 col-xs-12 col-sm-6">    
							<h3 class="text-capital" style="margin-top:5px !important;"><b>My Assigned Alerts</b></h3>
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
						</div>
						<div class="col-md-2 col-xs-12 col-sm-3">
							<select class="chosenSelect" id="alertTypeId">
								<option value="0">All</option>
								<option value="1" selected="selected">Party</option>            
								<option value="2">Govt</option>
								<option value="3">Others</option>
							</select>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row m_top20">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="overAllCount"></div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel panel-default">
								<div class="panel-heading panel-headingColor">
									<div class="row">
										<div class="col-md-3 col-xs-12 col-sm-3">
											<h4 class="panel-title text_capital"><b>alert status</b></h4>
										</div>
									</div>
								</div>
								<div class="panel-body bg_EF">
									<div id="alertCatTabId"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading panel-headingColor">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-4">
									<h3 style="margin-top:5px"><b>VIEW / ASSIGN ALERT</b></h3>
								</div>
								<div class="col-md-8 col-xs-12 col-sm-8">
									<button type="button" class="btn btn-default showfilterBlock pull-right" >Show Filters</button>
									<div class="filterBlockDiv updateDropDown" style="width:100%" >
										<div class="updateDropDownArrow">
											<button type="button" class="closedropdown close"  data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
											<div class="row">
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Assigned Cadre</label>
													<select class="chosenSelect chosen-select" id="assignedCadreId" onChange="assignCadreDetailsAllLevel();">
														<option value="0" selected="selected">Select Assigned Cadre</option>
														<option value="1">Party</option>
														<option value="2">Govt</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">State</label>
													 <select class="chosenSelect chosen-select " id="stateId" onChange="getDistrictsForReferPopup('');">
														 <option value="0">All</option>
														 <option value="1" selected="selected">Andhra Pradesh</option>    
														 <option value="36">Telangana</option>   
													 </select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">District</label>
														<select class="chosen-select chosenSelect" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
														<option value="0">Select District</option></select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Assembly</label>
													<select class="chosenSelect chosen-select " id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
													<option value="0">All</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Mandal/Municipality</label>
													<select class="chosenSelect chosen-select " id="refermandalNameId" onChange="getPanchayatsForReferPopup('');" >
														<option value="0">All</option>
													 </select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<label style="font-size:14px;" class="textcolor_black">Panchayat/Ward</label>
													<select class="chosenSelect chosen-select " id="referpanchayatId" >
														<option value="0">All</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-6">
													<button style="margin-top: 25px;" id="searchBtnId" onclick="getAdvanceLocationFilterAlertData();" class="btn btn-block btn-success m_top20 " type="button"  >Apply Filters</button>
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
									<div class="col-md-2 col-xs-12 col-sm-3">
										<label style="font-size:14px;" class="textcolor_black text_capital">Alert Category</label>
										<select class="chosenSelect" id="alertCategoryId">           
											<option value="0" selected="selected">All</option>
											<option value="1">Manual</option>
											<option value="2">Print Media</option>
											<option value="3">Electronic Media</option>  
										</select>  
									</div>
									<div class="col-md-2 col-xs-12 col-sm-3">
										<label style="font-size:14px;" class="textcolor_black text_capital">Alert Status</label>
										<select class="chosenSelect chosen-select" id="alertStatusId" >
											<option value="0" selected="selected">All</option>
											<option value="1">Pending</option>
											<option value="2">Notified</option>
											<option value="3">Action In Progess</option>
											<option value="4">Completed</option>
											<option value="5">Unable to Resolve</option>
											<option value="6">Action Not Required</option>
											<option value="7">Duplicate</option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<label style="font-size:14px;" class="textcolor_black text_capital">Alert Verification Status</label>
										<select class="chosenSelect chosen-select" id="alertVerificationStatusId" >
											<option value="0" selected="selected">All</option>
											<option value="1">Progress</option>
											<option value="2">Completed</option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<label style="font-size:14px;" class="textcolor_black text_capital">Alert Verification Date</label>  
										<div class="input-group">  
											<input placeholder="click here for date" type="text" class="form-control" id="verificationDateRangePickerId"/>  
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-calendar"></i>
											</span>     
										</div>    
									</div>  
									<div class="col-md-2 col-xs-12 col-sm-3">  
										<button style="margin-top: 25px;" id="searchBtId" onclick="getLocationFilterAlertData();" class="btn  btn-success m_top20 " type="button">View</button>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<h4 class="panel-title text_capital"><b><u>alert details</u></b></h4>
							<div id="locationLevelDataId" class="m_top20"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="verificationPosId" attr_get_pos_id="nonVerify"></input>

<script src="dist/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="dist/js/bootstrap.js"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/LocationHierarchy/locationHierarchyAlert.js" type="text/javascript"></script>

<script type="text/javascript">

$(document).on("click",".menuSelection li",function(){
	$(this).parent(".menuSelection").find("li").removeClass("active");
	$(this).addClass("active");
});
$('.chosenSelect').chosen({width:'100%'});

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
	$(".ranges").addClass("rangesNew")
	$("#verificationDateRangePickerId").daterangepicker({
		opens:'left',
		startDate:moment().subtract(29, 'days'),    
		endDate:moment(),
		ranges: {
        }     
	});
	$("#verificationDateRangePickerId").val(' ');
});

$(document).ready(function(){
	//getLocationLevelAlertCount();
	setTimeout(function(){ 
		var levelId = 0;
		var levelValue = 0;
		$('.stateCls').each(function(){
			if($(this).hasClass("active"))
				levelValue = $(this).attr("attr_state_id");
		});
		
		var statusId=0;
		 var fromDate='';
		 var toDate='';
		 var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
		var	categoryId =0;
		$("#errorId").html("");
		
		getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
	}, 2000);
});

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

var globalStateId = 1;
globalLocation = "state";  
var currentFromDate = moment().subtract(29, 'days').format("DD/MM/YYYY");
var currentToDate = moment().format("DD/MM/YYYY");  
var globalTdpCadreId = 0;
var assignedCadreIds = "${sessionScope.USER.assignCadreIds}";
if(assignedCadreIds != null && assignedCadreIds != "[]"){
	var cadresList = assignedCadreIds.split(",");
	if(cadresList != null && cadresList.length > 1){
		var cadId = cadresList[0].substring(1);
		globalTdpCadreId = cadId;
	}
	else{
		var cadId = cadresList[0].substring(1,cadresList[0].length-1);
		globalTdpCadreId = cadId;
	}
}
	
getAlertAssignedCandidate();
getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
getTotalAlertGroupByStatusThenCategory(globalStateId,currentFromDate,currentToDate);

function getAlertAssignedCandidate()
{
	$("#assignedCadreId option").remove();
	var jsObj={
		tdpCadreId : globalTdpCadreId
	}
	$.ajax({
	  type : 'GET',
	  url : 'getAlertAssignedCandidatesForCentralMembersAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
	  var str='';
	   str+='<option value="0">All</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].id > 0)
				str+='<option value="'+result[i].id+'">'+(result[i].name).toUpperCase()+'</option>';
			}
		}
		$("#assignedCadreId").html(str);
		$("#assignedCadreId").trigger('chosen:updated');
		//$("#assignedCadreId").val(globalTdpCadreId);
		//$("#assignedCadreId").trigger('chosen:updated');
	});
}  

$('#dateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
	$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
	  
	currentFromDate = picker.startDate.format('DD/MM/YYYY');
	currentToDate = picker.endDate.format('DD/MM/YYYY');
	
	getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
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
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
	
});

$(document).on("click",".stateCls",function(){
	$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
	$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
	
	var stateId = $(this).attr("attr_state_Id");
	globalStateId = stateId;      
	$("#stateId").val(stateId);
	$("#stateId").trigger("chosen:updated"); 
	getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
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
	$("#errorId").html("");
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
}); 

$(document).on("change","#alertTypeId",function(){      
	$("#overAllCount").html('<img style="margin-left:500px;width:30px;height:30px;" src="images/search.gif" />');
	$("#alertCatTabId").html('<img style="margin-left:510px;width:30px;height:30px;" src="images/search.gif" />');  
	
	getTotalAlertGroupByStatus(globalStateId,currentFromDate,currentToDate);
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
				
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
	//crearing Fields	
	$("#alertCategoryId").val(0);
	$("#alertStatusId").val(0);
	$("#alertCategoryId").trigger('chosen:updated');
	$("#alertStatusId").trigger('chosen:updated');
});

function getTotalAlertGroupByStatus(stateId,fromDate,toDate){
	var alertTypeId = $("#alertTypeId").val();
	var jsObj = { 
		stateId : stateId,     
		fromDate : fromDate,
		toDate : toDate,
		alertyTypeId : alertTypeId,
		tdpCadreId : globalTdpCadreId		
	}
	$.ajax({
		type : 'POST',      
		url : 'getTotalAlertGroupByStatusForCentralMembersAction.action',
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
		alertyTypeId : alertTypeId,
		tdpCadreId : globalTdpCadreId
	}
	$.ajax({
		type : 'POST',      
		url : 'getTotalAlertGroupByStatusThenCategoryForCentralMembersAction.action',
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
	if($(window).width() < 500)
	{
		str+='<div class="table-responsive">';
	}
	str+='<table class="table table-condensed b_1">';
		str+='<thead class="bg_CD" style="background-color:#CDCDD9;">';
			str+='<th></th>';    
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
	if($(window).width() < 500)
	{
		str+='</div>';
	}
	$("#alertCatTabId").html(str);  
}

function getAdvanceLocationFilterAlertData(){   
	var clickPosVal = $("#verificationPosId").attr("attr_get_pos_id");
	var actionTypeStatusId = $('#alertVerificationStatusId').val();
	var dateStr2 = $("#verificationDateRangePickerId").val();
	/*if(clickPosVal == "verification" && actionTypeStatusId > 0){
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
			assignedCadreId = 0;
		else if(assignedCadreId == 0)
			assignedCadreId = globalTdpCadreId;
		var fromDate='';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var alertTpeId = $('#alertTypeId').val();	
		var alertCategoryId = $('#alertCategoryId').val();	
		
		var statusId = $('#alertStatusId').val();
		var actionTypeId = 1;
		var impactScopeId = 0;      
		var fromDat2='';
		var toDat2='';
		
		if(dateStr2 != null && dateStr2.length>1){  
			fromDat2 = dateStr2.split("-")[0];
			toDat2 = dateStr2.split("-")[1];
		}
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
				categoryId:alertCategoryId,
				actionTypeStatusId:actionTypeStatusId,        
				task : "verification",
				fromDate2 : fromDat2,
				toDate2 : toDat2
			}
		$.ajax({
			type:'GET',
			url: 'getLocationFilterAlertDataAction.action',//imp
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildAlertData(result,jsObj);
		});
	}else */if(actionTypeStatusId > 0 || dateStr2.length > 1){
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
			assignedCadreId = 0;
		else if(assignedCadreId == 0)
			assignedCadreId = globalTdpCadreId;
		var fromDate='';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var alertTpeId = $('#alertTypeId').val();	
		var alertCategoryId = $('#alertCategoryId').val();	
		
		var statusId = $('#alertStatusId').val();
		var actionTypeId = 1;
		var impactScopeId = 0;      
		var fromDat2='';
		var toDat2='';
		
		if(dateStr2 != null && dateStr2.length>1){  
			fromDat2 = dateStr2.split("-")[0];
			toDat2 = dateStr2.split("-")[1];
		}
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
				categoryId:alertCategoryId,
				actionTypeStatusId:actionTypeStatusId,        
				task : "verification",
				fromDate2 : fromDat2,
				toDate2 : toDat2
			}
		$.ajax({
			type:'GET',
			url: 'getLocationFilterAlertDataAction.action',//imp
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildAlertData(result,jsObj);
		});
	}else{
		$(".filterBlockDiv").hide();
		$("#locationLevelDataId").html('<img src="images/search.gif" />');
		//GlobalAlertData = [];   
		var stateId = $("#stateId").val();
		var districtId = $("#referdistrictId").val();
		var constituencyId = $("#referconstituencyId").val();
		var mandalId = $("#refermandalNameId").val();
		var panchayatId = $("#referpanchayatId").val();
		var mandalType = $("#refermandalNameId option:selected").text();
	
		if(mandalType.indexOf("Mandal") == -1){
			mandalType = "localbody";
		}else{
			mandalType = "mandal";
		}
		var assignedCadreId =  $("#assignedCadreId").val();
		if(assignedCadreId.length == 0)
			assignedCadreId = 0;
		else if(assignedCadreId == 0)
			assignedCadreId = globalTdpCadreId;
		var fromDate='';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var alertTpeId = $('#alertTypeId').val();	
		var alertCategoryId = $('#alertCategoryId').val();	
		var statusId = $('#alertStatusId').val();	
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
				categoryId:alertCategoryId,
				actionTypeStatusId : 0,            
				task : "",
				fromDate2 : "",
				toDate2 : ""   
		}
		$.ajax({
			type:'GET',
			url: 'getLocationFilterAlertDataAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			//GlobalAlertData = result;
			buildAlertData(result,jsObj);
		});
	}
}

function buildAlertData(result,jsObj)
{
	if(result == null || result.length == 0)
	{
		$("#locationLevelDataId").html('No Data Available..');
		return;
	}
		
	var Level = "";
	
	if(jsObj.levelId == 2)
	{
		Level = "STATE";
	}
	else if(jsObj.levelId == 3)
	{
		Level = "DISTRICT";
	}
	else if(jsObj.levelId == 4)
	{
		Level = "CONSTITUENCY";
	}
	else if(jsObj.levelId == 5)
	{
		Level = "MANDAL";
	}
	else if(jsObj.levelId == 6)
	{
		Level = "VILLAGE";
	}
	else
	{
		Level = "";
	}
	var str='';
	
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered bg_ff text-center" id="alertDataTableId">';
	str+='<thead>';
	
	str+='<th>Alert Source </th>';
	str+='<th>Title</th>';
	str+='<th>Alert Category </th>';
	str+='<th>Alert Type </th>';
	str+='<th>Alert Status</th>';
	str+='<th>Verification Status</th>'; 
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
	str+='<td>'+result[i].alertSource+'</td>';
	str+='<td>'+result[i].title+'</td>';
	str+='<td>'+result[i].alertCategoryName+'</td>';
	str+='<td>'+result[i].alertType+'</td>';
	str+='<td>'+result[i].status+'</td>';
	if(result[i].verificationStatus != null){
		str+='<td>'+result[i].verificationStatus+'</td>';
	}else{
		str+='<td>-</td>';
	}
	str+='<td>'+result[i].count+'</td>';
	str+='<td>'+result[i].date+'</td>';
	str+='<td>'+result[i].userType+'</td>';
	str+='<td><span class="circle '+result[i].severity+'"></span>'+result[i].severity+'</td>';
	str+='<td><i class="glyphicon glyphicon-eye-open alertModel"  target="_blank" title="Click here to View Alert Details" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+' "></i>';
	
	str+='</tr>';	
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#locationLevelDataId").html(str);
	$("#alertDataTableId").dataTable(); 
}

$(document).on("click",".headerWiseDataCls",function(){
	$("#verificationPosId").attr("attr_get_pos_id","nonVerify");
	$("#verificationDateRangePickerId").val(' ');
	$("#alertVerificationStatusId").val(0);
	$("#alertVerificationStatusId").trigger("chosen:updated");
	var levelId = $(this).attr("attr_levlId");
	var levelValue = 0;
	var locationBlock = $(this).attr("attr_search_Location");  
	$('.stateCls').each(function(){
		if($(this).hasClass("active"))   
			levelValue = $(this).attr("attr_state_id");
	});
	
	var statusId=$(this).attr("attr_id");
	var categoryId=$(this).attr("attr_category_id");
	var fromDate='';
	var toDate='';
	var dateStr = $("#dateRangePickerId").val(); 
	
	if(dateStr !=null && dateStr.length>0){
		fromDate = dateStr.split("-")[0];
		toDate = dateStr.split("-")[1];
	}
	
	$("#errorId").html("");
	
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,locationBlock);
	//swadhin
	var postion = $(this).attr("attr_position");
	var id = $(this).attr("attr_id");
	if(postion == "first"){
		initSelectBoxForFirstPos(id);
	}
	if(postion == "second"){
		var category = $(this).attr("attr_category_id");
		initSelectBoxForSecondPos(id,category);
	}
	if(postion == "third"){
		initSelectBoxForFirstPos(id);
	}
});

function getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,locationBlock)
{
	 $("#assignedCadreId").val(0).trigger('chosen:updated'); 
	 $('html, body').animate({
        scrollTop: $('#locationLevelDataId').offset().top
     }, 2000);
	
	$("#locationLevelDataId").html('<img src="images/search.gif" />');
	var assignId = $('#assignedCadreId').val();
	var alertTpeId = $('#alertTypeId').val();
	if(assignId== null || assignId.length==0)
		assignId=0;
	else if(assignId == 0)
		assignId = globalTdpCadreId;
	if(alertTpeId== null || alertTpeId.length==0)
		alertTpeId=0;
	
    GlobalAlertData = [];
	var impactScopeId=0;
	if(locationBlock == "locationBlock"){
		impactScopeId=levelId;
		levelId=0;
	}
	var jsObj =
	{
		alertTypeId:alertTpeId,
		levelId  : levelId,
		statusId :statusId,
		fromDate :fromDate,
		toDate   :toDate,
		levelValue:levelValue,
		categoryId:categoryId,
		assignId:assignId,
		impactScopeId:impactScopeId,
		task : locationBlock
	}
	$.ajax({
		  type:'GET',
		  url: 'getLocationLevelWiseAlertsDataForCentralMembersAction.action',
		  data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
	   //GlobalAlertData = result;
		buildAlertData(result,jsObj);
		//getDistrictsForReferPopup('');
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

function getLocationFilterAlertData()
{	    
	var clickPosVal = $("#verificationPosId").attr("attr_get_pos_id");
	var actionTypeStatusId = $('#alertVerificationStatusId').val();
	var dateStr2 = $("#verificationDateRangePickerId").val();
	/*if(clickPosVal == "verification" && actionTypeStatusId > 0){
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
		
		var statusId = $('#alertStatusId').val();
		var actionTypeId = 1;
		var impactScopeId = 0;      
		var fromDate2='';
		var toDate2='';
		
		if(dateStr2 != null && dateStr2.length>1){  
			fromDate2 = dateStr2.split("-")[0];
			toDate2 = dateStr2.split("-")[1];
		}
		getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId,statusId,fromDate2,toDate2);
	}else */if(actionTypeStatusId > 0 || dateStr2.length > 1){
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
		
		var statusId = $('#alertStatusId').val();
		var actionTypeId = 1;
		var impactScopeId = 0;      
		var fromDate2='';
		var toDate2='';
		
		if(dateStr2 != null && dateStr2.length>1){  
			fromDate2 = dateStr2.split("-")[0];    
			toDate2 = dateStr2.split("-")[1];
		}
		getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId,statusId,fromDate2,toDate2);
	}else{
		$(".filterBlockDiv").hide();
		$("#locationLevelDataId").html('<img src="images/search.gif" />');
		GlobalAlertData = [];
		var stateId = $("#stateId").val();
		var districtId = $("#referdistrictId").val();
		var constituencyId = $("#referconstituencyId").val();
		var mandalId = $("#refermandalNameId").val();
		var panchayatId = $("#referpanchayatId").val();
		var mandalType = $("#refermandalNameId option:selected").text();
	
		if(mandalType.indexOf("Mandal") == -1){
			mandalType = "localbody";
		}else{
			mandalType = "mandal";
		}
		var assignedCadreId =  $("#assignedCadreId").val();
		if(assignedCadreId.length == 0)
			assignedCadreId = 0;
		else if(assignedCadreId == 0)
			assignedCadreId = globalTdpCadreId;
		var fromDate='';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var alertTpeId = $('#alertTypeId').val();	
		var alertCategoryId = $('#alertCategoryId').val();	
		var statusId = $('#alertStatusId').val();	
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
				categoryId:alertCategoryId,
				actionTypeStatusId : 0,            
				task : "",  
				fromDate2 : "",
				toDate2 : ""
		}
		$.ajax({      
			type:'GET',
			url: 'getLocationFilterAlertDataAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			GlobalAlertData = result;
			buildAlertData(result,jsObj);
		});
	}            
}

function getAllAlertsWithoutFilter(alertTpeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId,statusId,fromDat2,toDat2){
	$('html, body').animate({
        scrollTop: $('#locationLevelDataId').offset().top
     }, 2000);
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
		statusId : statusId,
		fromDate2 : fromDat2,
		toDate2 : toDat2,
		assignId : globalTdpCadreId
	}
	$.ajax({  
		type:'GET',            
		url: 'getAllAlertsWithoutFilterForCentralMembersAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
				buildAlertData(result,jsObj);
	});
}
var GlobalalertId;
$(document).on("click",".alertModel",function(){
GlobalalertId = $(this).attr("attr-id");
window.open("alertDetailsAction.action?alertId="+GlobalalertId+"", '_blank');
});
</script>
</body>
</html>