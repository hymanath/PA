<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>call center user</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
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
					<ul class="list-inline">
					<li><button class="btn-success" type="button">today</button></li>
					<li>This Week(April 9-15)</li>
					<li>Last Week(Apr)</li>
					<li>This Month(Apr)</li>
					<li>Last Month(Apr)</li>
					<li><i class="glyphicon glyphicon-time" ></i>Custom Date Range</li>
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
							 <p><i class="glyphicon glyphicon-user" style="font-size:50px;">&#xe8d3;</i></p>
						   </div>
						   <div class="col-md-10">
							  <ul class="list-group">
								<li>Total Login Agents</li> 
								 <li style="font-size:40px;">20</li>
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
							  <li style="font-size:30px;">15:30 min</li>
						  </ul>
					   </div>
				   </div>
			  </div>
			   <div class="col-md-4">
				  <div class="row">
					   <div class="col-md-2">
						 <p><i class="glyphicon glyphicon-file" style="font-size:50px;"></i></p>
					   </div>
					   <div class="col-md-10">
						 <ul class="list-group">
						   <li>Total Requests Created</li> 
							 <li style="font-size:30px;">150</li>
						 </ul>
					   </div>
				   </div>
			  </div>
			</div>
		 
		</div>
   </div>

  <div class="panel panel-default">
		 <div class="panel-body">
		   <h4>Agent Wise Overview</h4>
		 </div>
	 
		 <table class="table table-bordered  table-responsive" id="agentWiseOverViewId">
		 <thead>
		  <tr style="font-size:14px;">
			 <th>Agent Name</th>
			 <th>ContactNumber</th>
			 <th>Login Time</th>
			 <th>Login Time</th>
			 <th>Total Worked Hours</th>
			<th>No Of Requested Created</th>
			 <th>View Datails</th>
		  </tr>
		 </thead>
			 <tbody>
					  <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					<td><i class="glyphicon glyphicon-chevron-right"></i></td>
				  </tr>
				  <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					<td><i class="glyphicon glyphicon-chevron-right"></i></td>
				 </tr>
				 <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					<td><i class="glyphicon glyphicon-chevron-right"></i></td>
				 </tr>
				 <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					<td><i class="glyphicon glyphicon-chevron-right"></i></td>
				  </tr>
				  <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					<td><i class="glyphicon glyphicon-chevron-right"></i></td>
				 </tr>
				  <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					
					 <td><i class="glyphicon glyphicon-chevron-right"></i></td>
					 </tr>
				  <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					 
					 <td><i class="glyphicon glyphicon-chevron-right"></i></td>
					 </tr>
				  <tr>
					 <td><img class="img-circle" src="ImageEight.jpg"/><span>ramesh</span></td>
					 <td>9493567967</td>
					 <td>8:10am</td>
					 <td>6:00pm</td>
					<td>9:50</td>
					<td>10</td>
					 <td><i class="glyphicon glyphicon-chevron-right"></i></td>
				  </tr>
			  </tbody>
		  </table>
	 </div>
 </div>

<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script>

	$(document).ready(function(){
	$("#agentWiseOverViewId").DataTable();
	});
</script>
</body>
</html>