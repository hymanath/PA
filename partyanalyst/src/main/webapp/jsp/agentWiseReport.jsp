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
			   	<h3>Call Center User(Agent) Wise Report</h3>
				<ul class="list-inline callCenterUser">
					<li>Today</li>
					<li>This Week(April 9-15)</li>
					<li>Last Week(Apr)</li>
					<li>This Month(Apr)</li>
					<li>Last Month(Apr)</li>
					<li>
					<div class="input-group">
		                 <input type="text" class="form-control" id="dateRangePickerId"/>
			             <span class="input-group-addon">
		                  <i class="glyphicon glyphicon-calendar"></i>
			                 </span>     
			                </div>
					</li>
				  </ul>
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

<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script>

	$(document).ready(function(){
	$("#agentWiseOverViewId").DataTable();
	$(document).ready(function(){
	   var start = moment().subtract(29, 'days');
       var end = moment();
     
	 $("#dateRangePickerId").daterangepicker({
		opens:'left',
		startDate:start,    
		endDate:end,
		ranges: {
        }     
	     });
  });
});
</script>
<script>
getTotalUserLogingDtls();
function getTotalUserLogingDtls(){
    var jsObj ={
    fromDate:"12/04/2017",             
    toDateStr:"12/04/2017"      
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
						 str+='<th>Login Time</th>';
						 str+='<th>Login Time</th>';
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
						str+='<td>'+result.callCenterVOList[i].loginTime+'</td>';
						str+='<td>'+result.callCenterVOList[i].logoutTime+'</td>';
						str+='<td>'+result.callCenterVOList[i].totalHours+'</td>';
						str+='<td>'+result.callCenterVOList[i].noOfAlertCreated+'</td>';
						str+='<td><i class="glyphicon glyphicon-chevron-right"></i></td>';
					 str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					
					$('#tableId').html(str);
					$("#callerDatailsId").DataTable();
				}
			}
		
			
    });
}
$(document).on("click",".dateCls",function(){
	$(".dateCls").removeClass("btn-success");
	$(this).addClass("btn-success");  
});
   $(document).on("click",".callCenterUser li",function(){
         
			var date =  $(this).html();
		
			if(date == 'Today')
			{
				callCenterUserFDate = moment().format('DD-MM-YYYY');
				callCenterUserTDate = moment().format('DD-MM-YYYY');
				
			 
			}else if(date == 'This Week(April 9-15)'){
				callCenterUserFDate = moment().startOf("week").format('DD-MM-YYYY');
				callCenterUserTDate = moment().endOf('week').format('DD-MM-YYYY');
				
			}else if(date == 'Last Week(Apr)'){
				callCenterUserFDate = moment().subtract(6, 'days').format('DD-MM-YYYY');
				callCenterUserTDate = moment().format('DD-MM-YYYY');
				
				}else if(date == 'This Month(Apr)'){
				callCenterUserFDate = moment().startOf("month").format('DD-MM-YYYY');
				callCenterUserTDate = moment().endOf('month').format('DD-MM-YYYY');
				
			}else if(date == 'Last Month(Apr)'){
				callCenterUserFDate = moment().subtract(1, 'month').startOf('month').format('DD-MM-YYYY');
				callCenterUserTDate = moment().subtract(1, 'month').endOf('month').format('DD-MM-YYYY');
				
			}
		});
</script>
</body>
</html>