<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>call center user</title>
  	<meta charset="utf-8">
    <link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
    <link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
   	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="dist/alertDashBoard/dist/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	
	
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
     <script src="dist/DateRange/moment.js" type="text/javascript"></script>
	 <script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
  	<style>
   		li {
    		list-style:none;
  		}
  		img{
		  height:20px;
		  width:20px;
  		}
  	</style>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row">
					<div class="col-md-6">
						<h3>Call Center User(Agent) Wise Report</h3>
					</div>
					<div class="col-md-3">  
						<div class="input-group" style="margin-bottom: -10px">
							<input type="text" class="form-control" id="dateRangePickerId"/>
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
							</span>   							 
						</div>
					</div>
				</div>
			</div>
		  	<div class="panel-body">
				<h3>Overview</h3>
		  	</div>
			<div clas=" panel-body">  
		       	<div class="row">
				 	<div class="col-md-4">
						<div class="row">
						   	<div class="col-md-2">
							  	<p><i class="fa fa-group" style="font-size:40px;margin-left:15px;"></i></p>
						   	</div>
						   	<div class="col-md-10">
							  	<ul class="list-group">
									<li>Total Login Agents</li> 
								 	<li style="font-size:40px;" id="totalLoginId"></li>
							  	</ul>
						   	</div>                      
				   		</div>
			  		</div>
			   		<div class="col-md-4">
				   	<div class="row">
					   	<div class="col-md-2">
						  	<p><i class="glyphicon glyphicon-time" style="font-size:50px;"></i></p>
					   	</div>
					  <div class="col-md-10">
						   <ul class="list-group">
							  <li>Total Login Hours</li> 
							  <li style="font-size:30px;" id="totalTimeId"></li>
						  </ul>
					   </div>
				   </div>
			  </div>
			   <div class="col-md-4">
				  <div class="row">
					   <div class="col-md-2">
						<p><i class="fa fa-file-o" style="font-size:50px;"></i></p>
					   </div>
					   <div class="col-md-10">
						 <ul class="list-group">
						   <li>Total Requests Created</li> 
							 <li style="font-size:30px;" id="totalRequestId"></li>
						 </ul>
					   </div>
				   </div>
			  </div>
			</div>
		</div>
   </div>

 
	 <div id="tableId"></div>
 </div>

 <div class="modal fade" id="userLoginDetailsId" tabindex="-2" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width:85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="">Modal title</h4>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-body">
						<!--<div class="row">
							<div class="col-md-4">
								<div class="row">
								   <div class="col-md-12">  
										<ul class="list-group">
											<li>Santhosh</li> 
											<li>+919493565654</li>
										</ul>
								   </div>                      
								</div>
							</div>
							<div class="col-md-8">
								<ul class="list-inline">
									<li><button class="btn btn btn-mg btn-success" type="button">today</button></li>
									<li>This Week(April 9-15)</li>
									<li>Last Week(Apr)</li>
									<li>This Month(Apr)</li>
									<li>Last Month(Apr)</li>
									<li><i class="glyphicon glyphicon-time" ></i>Custom Date Range</li>
								</ul>
							</div>
						</div>-->
					
						<div class="row">
							<div class="col-md-5 multiUserCls" >
								<div class="row">
									<div class="col-md-2">
										<i class="glyphicon glyphicon-time" style="font-size:40px;"></i>
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
							<div class="col-md-2 singleUserCls">  
								<div class="row">
									<div class="col-md-3">
										<i class="glyphicon glyphicon-time" style="font-size:40px;"></i>
									</div>
									<div class="col-md-9">
										<ul class="list-group">
											<li> Logoff Time</li> 
											<li style="font-size:10px;" id="individualLongoffId">5:00 PM</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="row">
									<div class="col-md-3">
										<i class="glyphicon glyphicon-time" style="font-size:40px;"></i>
									</div>
									<div class="col-md-9">
										<ul class="list-group">
											<li>Total Login Hours</li> 
											<li style="font-size:10px;" id="individualLoginHrId">15:30</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-3">
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
								<button class="btn btn-success btn-lg">
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
 
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script>
    var callCenterUserFDate=moment().format('DD/MM/YYYY');
    var callCenterUserTDate=moment().format('DD/MM/YYYY');
	
	$(document).ready(function(){
	$("#agentWiseOverViewId").DataTable();
	 $("#dateRangePickerId").daterangepicker({
		opens:'left',
		startDate:moment(),    
		endDate:moment(),  
		ranges: {
        }
	 });
  });  
</script>
<script>
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
								
					
					$('#totalLoginId').html(result.totalAgent);
					$('#totalRequestId').html(result.totalAlert);
					$('#totalTimeId').html(result.totalTime);
					
					var str ='';
					str+='<table class="table table-bordered" id="callerDatailsId">';
					str+='<thead>';
					  str+='<tr style="font-size:14px;">';
						 str+='<th>Agent Name</th>';
						 str+='<th>ContactNumber</th>';
						 if(result.range == "multiple"){
							 str+='<th>Login Days</th>';
						 }else{
							 str+='<th>Login Time</th>';
							str+='<th>Logoff Time</th>';
						 }
						 str+='<th>Total Worked Hours</th>';
						 str+='<th>No Of Requested Created</th>';
						 str+='<th>View Datails</th>';
					  str+='</tr>';
					 str+='</thead>';
					str+='<tbody>';
					for(var i in result.callCenterVOList){	
					 str+='<tr>';

						str+='<td>'+result.callCenterVOList[i].firstName+'</td>';
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
							str+='<td style="cursor:pointer;" class="showDtlsOfUser" attr_user_id="'+result.callCenterVOList[i].userId+'"><i class="glyphicon glyphicon-chevron-right"></i></td>';
						}else{
							str+='<td ><i class="glyphicon glyphicon-chevron-right"></i></td>';
						}
						
					 str+='</tr>';
					}    
					str+='</tbody>';
					str+='</table>';
					
					$('#tableId').html(str);
					$("#callerDatailsId").DataTable();
				}else{
					$("#tableId").html('No Data Available.');  
				}
			}
		
			
    });
}
$(document).on("click",".dateCls",function(){
	$(".dateCls").removeClass("btn-success");
	$(this).addClass("btn-success");  
});
$(document).on("click",".showDtlsOfUser",function(){
	alert(callCenterUserFDate);
	alert(callCenterUserTDate);
	$("#userLoginDetailsId").modal("show");  
	var userId = $(this).attr("attr_user_id");
	alert(userId);
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
			buildUserDetails(result);
		}
		
	});
});
function buildUserDetails(result){
	if(result.subList!=null && result.subList.length>0){
		if(result.range == "multiple"){
			$('.singleUserCls').hide();              
			$('#individualLonginDaysId').html(result.attendedCount);
		}else{
			$('.multiUserCls').hide();
			$('#individualLonginId').html(result.loginTime);    
			$('#individualLongoffId').html(result.logoutTime);
		}
		
		$('#individualLoginHrId').html(result.totalHours);
		$('#individualAlertCreatedId').html(result.noOfAlertCreated); 
		var str ='';
		
		str+='<table class="table table-bordered" id="singleUsercallerDataTableId">';
		str+='<thead>';
			 str+='<tr style="font-size:14px;">';
				 str+='<th>Complaint Id</th>';
				 str+='<th>Date & Time</th>';
				 str+='<th>Category</th>';
				 str+='<th>Title</th>';
				 str+='<th>Caller Details </th>';
				 str+='<th>Assigned Department</th>';
				 str+='<th>ImpactLocation Level</th>';
				 str+='<th>Location Name</th>';
				 str+='<th>Officer Designation</th>';
				 str+='<th>Officer Contact No</th>';
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
   $(document).on("click",".callCenterUser li button.dateCls",function(){
			var date =  $(this).html();
			if(date == 'Today')
			{     
				callCenterUserFDate = moment().format('DD/MM/YYYY');
				callCenterUserTDate = moment().format('DD/MM/YYYY');
				alert(callCenterUserFDate);
				alert(callCenterUserTDate);
			}else if(date == 'This Week'){
				callCenterUserFDate = moment().startOf("week").format('DD/MM/YYYY');
				callCenterUserTDate = moment().endOf('week').format('DD/MM/YYYY');
			}else if(date == 'Last Week'){
				callCenterUserFDate = moment().subtract(6, 'days').format('DD/MM/YYYY');
				callCenterUserTDate = moment().format('DD/MM/YYYY');
				}else if(date == 'This Month'){
				callCenterUserFDate = moment().startOf("month").format('DD/MM/YYYY');
				callCenterUserTDate = moment().endOf('month').format('DD/MM/YYYY');
			}else if(date == 'Last Month'){
				callCenterUserFDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY');
				callCenterUserTDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
			}
		});
</script>
</body>
</html>