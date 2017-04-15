<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>call center user</title>
  	<meta charset="utf-8">
  
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">
	<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link rel="stylesheet" href="dist/css/font-awesome.css">
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
	<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
	<style>
   		li {
    		list-style:none;
  		}
  		img{
		  height:20px;
		  width:20px;
  		}
		#singleUsercallerDataTableId thead tr th{background-color:#d3d3d3 !important;}
  	</style>
</head>
<body>
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-md-6">
						<h4>Call Center User(Agent) Wise Report</h4>
					</div>
					<div class="col-md-3 pull-right">  
						<div class="input-group">
							<input type="text" class="form-control" id="dateRangePickerId"/>
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
							</span>   							 
						</div>
					</div>
				</div>
				<div class="row">
					<div class="panel panel-default m_top10">
						<div class="panel-heading">
							<h4>Overview <a class="btn btn-success pull-right" href="locationWiseReportAction.action" style="margin-top: -8px;" > Geographical View  </a></h4>
						</div>
						<div class="panel-body">  
							<div class="row m_top5">
								<div class="col-md-4">
									<div class="row">
										<div class="col-md-2">
											<p><i class="fa fa-group" style="font-size:40px;margin-left:15px;"></i></p>
										</div>
										<div class="col-md-10">
											<ul class="list-group">
												<li>Total Login Agents</li> 
												<li id="totalLoginId"></li>
											</ul>
										</div>                      
									</div>
								</div>
								<div class="col-md-4">
								<div class="row">
									<div class="col-md-2">
										<p><i class="glyphicon glyphicon-time text-success" style="font-size:40px;margin-left:15px;"></i></p>
									</div>
								  <div class="col-md-10">
									   <ul class="list-group">
										  <li>Total Login Hours</li> 
										  <li  id="totalTimeId"></li>
									  </ul>
								   </div>
							   </div>
						  </div>
						   <div class="col-md-4">
							  <div class="row">
								   <div class="col-md-2">
									<p><i class="fa fa-file-o" style="font-size:40px;margin-left:15px;" ></i></p>
								   </div>
								   <div class="col-md-10">
									 <ul class="list-group">
									   <li>Total Requests Created</li> 
										 <li  id="totalRequestId"></li>
									 </ul>
								   </div>
							   </div>
						  </div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Agent Wise Overview</h4>
				</div>
				<div class="panel-body">
					<div id="tableId" class="m_top30"></div>   
				</div>
			</div>			
			</div>			
	
		</div>
	</div>
</div>

 <div class="modal fade" id="userLoginDetailsId" tabindex="-2" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="callerUserNameId"></h4>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-5 multiUserCls" >
								<div class="row">
									<div class="col-md-2">
										<i class="glyphicon glyphicon-calendar" style="font-size:40px;"></i>
									</div>
									<div class="col-md-10">
										<ul class="list-group">
											<li>Login Days</li> 
											<li style="font-size:10px;" id="individualLonginDaysId">5:00 PM</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-3 singleUserCls">
								<div class="row">
									<div class="col-md-2">
										<i class="glyphicon glyphicon-time" style="font-size:40px;"></i>
									</div>
									<div class="col-md-10">
										<ul class="list-group">
											<li>Login Time</li> 
											<li style="font-size:10px;" id="individualLonginId">5:00 PM</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-3 singleUserCls">  
								<div class="row">
									<div class="col-md-3">
										<i class="glyphicon glyphicon-time text-danger" style="font-size:40px;"></i>
									</div>
									<div class="col-md-9">
										<ul class="list-group">
											<li> Logoff Time</li> 
											<li style="font-size:10px;" id="individualLongoffId">5:00 PM</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-2" style="display:none;">  
								<div class="row">
									<div class="col-md-3">
										<i class="glyphicon glyphicon-time" style="font-size:40px;color:#B49963"></i>
									</div>
									<div class="col-md-9">
										<ul class="list-group">
											<li>Total Login Hours</li> 
											<li style="font-size:10px;" id="individualLoginHrId">15:30</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="row">
									<div class="col-md-2">
										<i class="fa fa-file-o" style="font-size:40px;"></i>
									</div>
									<div class="col-md-10">
										<ul class="list-group">
											<li>Total Requests Created</li> 
											<li style="font-size:10px;" id="individualAlertCreatedId">214</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<button class="btn btn-success btn-lg" onclick="generateExcel();">
									<span class="glyphicon glyphicon-download-alt"></span> Download
								</button>   
							</div>
						</div>
					</div>
				 </div>
				<div id="singleUsertableId"></div>
			</div>
			<div class="modal-footer">
				<button type="button closeSecondModal" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
  </div>
</div>  
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script>
	var callCenterUserFDate=moment().format("DD/MM/YYYY");
	var callCenterUserTDate=moment().format("DD/MM/YYYY");

	$("#agentWiseOverViewId").DataTable();    
	
	$("#dateRangePickerId").daterangepicker({  
	opens: 'left',
	startDate: callCenterUserFDate,
	endDate: callCenterUserTDate,
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
		'Today' : [moment(), moment()],
		'Yesterday' : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],               
		'This Month': [moment().startOf('month'), moment()],          
		'Last 30 Day': [moment().subtract(29, 'days'), moment()],    
		'Last 1 Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'Last 3 Months': [moment().subtract(3, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'Last 6 Months': [moment().subtract(6, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'This Year': [moment().startOf('Year'), moment()],
		'Last 1 Year': [moment().subtract(1, 'Year').startOf('Year'), moment().subtract(1, 'Year').endOf('Year')],             
		
	}
});

$('#dateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	callCenterUserFDate = picker.startDate.format('DD/MM/YYYY');
	callCenterUserTDate = picker.endDate.format('DD/MM/YYYY');
	
	
	onLoadCalls();
});

function onLoadCalls(){
	getTotalUserLogingDtls();               
}
getTotalUserLogingDtls();
function getTotalUserLogingDtls(){  
	$("#tableId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
    var jsObj ={
		fromDate:callCenterUserFDate,                         
		toDateStr:callCenterUserTDate             
    }
    $.ajax({
    type:'GET',         
    url: 'getTotalUserLogingDtlsAction.action',
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null){   
			if(result.callCenterVOList!=null && result.callCenterVOList.length>0){
								
					
					$('#totalLoginId').html('<h2>'+result.totalAgent+'</h2>');
					$('#totalRequestId').html('<h2>'+result.totalAlert+'</h2>');
					$('#totalTimeId').html('<h2>'+result.totalTime+'</h2>');
					
					var str ='';
					
					str+='<table class="table table-hover" id="callerDatailsId">';
					str+='<thead>';
					  str+='<tr style="font-size:14px;">';      
						 str+='<th class="text-capital">Image</th>';
						 str+='<th class="text-capital">Agent Name</th>';
						 str+='<th class="text-capital">ContactNumber</th>';
						 if(result.range == "multiple"){
							 str+='<th class="text-capital">Login Days</th>';
						 }else{
							 str+='<th class="text-capital">Login Time</th>';
							str+='<th class="text-capital">Logoff Time</th>';
						 }
						 str+='<th class="text-capital">Total Worked Hours</th>';
						 str+='<th class="text-capital">No Of Requested Created</th>';
						 str+='<th class="text-capital">View Datails</th>';
					  str+='</tr>';
					 str+='</thead>';
					str+='<tbody>';
					for(var i in result.callCenterVOList){	
					 str+='<tr>';  

						str+='<td><img class="img-circle" src="https://www.mytdp.com/images/user_images/'+result.callCenterVOList[i].imagePath+'" style="width: 45px; height: 45px;border:1px solid #ccc" /></td>';      
						str+='<td>'+result.callCenterVOList[i].userName+'</td>';  
						str+='<td>'+result.callCenterVOList[i].mobileNum+'</td>';
						if(result.range == "multiple"){
							 str+='<th>'+result.callCenterVOList[i].attendedCount+'</th>';
						 }else{
							str+='<td>'+result.callCenterVOList[i].loginTime+'</td>';
							str+='<td>'+result.callCenterVOList[i].logoutTime+'</td>';
						 }
						
						str+='<td>'+result.callCenterVOList[i].totalHours+'</td>';
						str+='<td>'+result.callCenterVOList[i].noOfAlertCreated+'</td>';
						if(result.callCenterVOList[i].noOfAlertCreated > 0){
							str+='<td style="cursor:pointer;" class="showDtlsOfUser" attr_user_id="'+result.callCenterVOList[i].userId+'" attr_user_name="'+result.callCenterVOList[i].userName+'"><i class="glyphicon glyphicon-chevron-right"></i></td>';
						}else{
							str+='<td ><i class="glyphicon glyphicon-chevron-right"></i></td>';
						}
						
					 str+='</tr>';
					}    
					str+='</tbody>';
					str+='</table>';
					
					$('#tableId').html(str);
					$("#callerDatailsId").DataTable();
				}
			}else{
				$("#tableId").html('No Data Available.');
				$('#totalLoginId').html('<h2>0</h2>');
				$('#totalRequestId').html('<h2>0</h2>');  
				$('#totalTimeId').html('<h2>0</h2>');
			}
    });
}
$(document).on("click",".dateCls",function(){
	$(".dateCls").removeClass("btn-success");
	$(this).addClass("btn-success");  
});
$(document).on("click",".showDtlsOfUser",function(){
	$("#singleUsertableId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#userLoginDetailsId").modal("show");        
	var userId = $(this).attr("attr_user_id");
	var userName = $(this).attr("attr_user_name");
	var jsObj ={
		fromDate:callCenterUserFDate,                                   
		toDateStr:callCenterUserTDate,    
		userId : userId
    }
    $.ajax({
    type:'GET',         
    url: 'getUserLogingDtlsAction.action',    
    data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result!=null){
			buildUserDetails(result,userName);
		}
		
	});
});  
function buildUserDetails(result,userName){
	
	$('#callerUserNameId').html('<h4>Agent Name:'+userName+'</h4>');    
	if(result.subList!=null && result.subList.length>0){
		if(result.range == "multiple"){
			$('.singleUserCls').hide();
			$('.multiUserCls').show();			
			$('#individualLonginDaysId').html('<h4>'+result.attendedCount+'</h4>');
		}else{
			$('.multiUserCls').hide();
			$('.singleUserCls').show();    
			$('#individualLonginId').html('<h4>'+result.loginTime+'</h4>');    
			$('#individualLongoffId').html('<h4>'+result.logoutTime+'</h4>');
		}
		
		$('#individualLoginHrId').html('<h4>'+result.totalHours+'</h4>');
		$('#individualAlertCreatedId').html('<h4>'+result.noOfAlertCreated+'</h4>'); 
		var str ='';
		
		str+='<table class="table table-bordered" id="singleUsercallerDataTableId">';
		str+='<thead>';
			 str+='<tr style="font-size:14px;">';
				 str+='<th class="text-capital">Complaint Id</th>';
				 str+='<th class="text-capital">Date & Time</th>';
				 str+='<th class="text-capital">Category</th>';
				 str+='<th class="text-capital">Title</th>';
				 str+='<th class="text-capital">Caller Details </th>';
				 str+='<th class="text-capital">Assigned Department</th>';
				 str+='<th class="text-capital">ImpactLocation Level</th>';
				 str+='<th class="text-capital">Location Name</th>';
				 str+='<th class="text-capital">Officer Designation</th>';
				 str+='<th class="text-capital">Officer Contact No</th>';
			  str+='</tr>';
			str+='</thead>';
		str+='<tbody>';
		for(var i in result.subList){	//name,mobileNo,email  
			str+='<tr>';
				str+='<td>'+result.subList[i].id+'</td>';
				if(result.subList[i].createdDate != null && result.subList[i].createdDate.length > 1){
					str+='<td>'+result.subList[i].createdDate+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result.subList[i].category != null && result.subList[i].category.length > 1){
					str+='<td>'+result.subList[i].category+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result.subList[i].title != null && result.subList[i].title.length > 1){
					str+='<td>'+result.subList[i].title+'</td>';
				}else{
					str+='<td>-</td>';
				}
				str+='<td>';
					str+='<ul>';
						if(result.subList[i].name != null && result.subList[i].name.length > 1){
							str+='<li>'+result.subList[i].name+'</li>';
						}
						if(result.subList[i].mobileNo != null && result.subList[i].mobileNo.length > 1){
							str+='<li>'+result.subList[i].mobileNo+'</li>';
						}
						if(result.subList[i].email != null && result.subList[i].email.length > 1){
							str+='<li>'+result.subList[i].email+'</li>';
						}
					str+='</ul>';
				str+='</td>';
				if(result.subList[i].department != null && result.subList[i].department.length > 1){
					str+='<td>'+result.subList[i].department+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result.subList[i].impactLevel != null && result.subList[i].impactLevel.length > 1){
					str+='<td>'+result.subList[i].impactLevel+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result.subList[i].location != null && result.subList[i].location.length > 1){
					str+='<td>'+result.subList[i].location+'</td>';
				}else{
					str+='<td>-</td>';
				}
				
				if(result.subList[i].designation != null && result.subList[i].designation.length > 1){
					str+='<td>'+result.subList[i].designation+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result.subList[i].officerMobileNo != null && result.subList[i].officerMobileNo.length > 1){
					str+='<td>'+result.subList[i].officerMobileNo+'</td>';
				}else{
					str+='<td>-</td>';
				}
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$('#singleUsertableId').html(str);
		$("#singleUsercallerDataTableId").DataTable();
	}    
	
}
</script>

<script>
function generateExcel(){
	tableToExcel('singleUsertableId', 'Call Center Report');
}
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>