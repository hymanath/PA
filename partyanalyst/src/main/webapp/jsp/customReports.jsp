<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cadre Demographic Reports</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
.text-capital{
	
	text-transform:uppercase;
}
.f_18{
	font-size:18px;
}
</style>
</head>
<body>  
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">  
				<div class="panel panel-default">
					  <div class="panel-heading">
						  <div class="row">
							<div class="col-md-9 col-xs-12 col-sm-12" style="margin-top: 10px;">
								<h3 class="panel-title text-capital f_18">Program</h3>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-12">
								<div class="input-group inputGCustom">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
									<input type="text" class="form-control multiDateRangePicker"/>
								</div>
							</div>
						  </div>
						
					  </div>
					  <div class="panel-body">
						Panel content
					  </div>
				</div>
			</div>
		</div>
	</div>


<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="js/customReports.js" type="text/javascript"></script>

<script type="text/javascript">
	$(".multiDateRangePicker").daterangepicker({
		opens: 'left',
	 	locale: {
		  format: 'MM/DD/YYYY'
		}		
	});
</script>	
</body>
</html>