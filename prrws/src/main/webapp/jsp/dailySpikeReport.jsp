<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FMS DASHBOARD</title>
<link href="Assests/css/bootstrap.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" type="text/less" rel="stylesheet"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
<style>
         .heading-bgColor {
			background-color: rgb(74, 88, 99);
         }
         .heading-textColor {
			color: rgb(255, 255, 255);
         }
         .pad_5 {
			padding: 5px;
         }
         .bg_ED {
			background-color: rgb(237, 238, 240);
         }
         .m_top10 {
			margin-top: 10px !important;
         }
		 
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-default panel-black">
				<div class="panel-heading" style="background: rgb(255, 194, 31);">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor">Total Overview</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row bg_ED pad_5">
						<div class="col-xs-12 col-md-6">
							<span><h3>Total Dengue and Malaria Cases Registered : <span id="totalCase">100</span></h3></span>
						</div>
					</div>
					<div class="row m_top20">   
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" style="background: rgb(255, 194, 31);">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Districts</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover">
												<thead>
												  <tr>
													<th>Firstname</th>
													<th>Lastname</th>
													<th>Email</th>
												  </tr>
												</thead>
												<tbody>
												  <tr>
													<td>John</td>
													<td>Doe</td>
													<td>john@example.com</td>
												  </tr>
												  <tr>
													<td>Mary</td>
													<td>Moe</td>
													<td>mary@example.com</td>
												  </tr>
												  <tr>
													<td>July</td>
													<td>Dooley</td>
													<td>july@example.com</td>
												  </tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" style="background: rgb(255, 194, 31);">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Mandals</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover">
												<thead>
												  <tr>
													<th>Firstname</th>
													<th>Lastname</th>
													<th>Email</th>
												  </tr>
												</thead>
												<tbody>
												  <tr>
													<td>John</td>
													<td>Doe</td>
													<td>john@example.com</td>
												  </tr>
												  <tr>
													<td>Mary</td>
													<td>Moe</td>
													<td>mary@example.com</td>
												  </tr>
												  <tr>
													<td>July</td>
													<td>Dooley</td>
													<td>july@example.com</td>
												  </tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-default panel-black">
				<div class="panel-heading" style="background: rgb(255, 194, 31);">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h4 class="m_0 panel-title text-capital fontColor">Diseases Wise Overview</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row bg_ED pad_5">
						<span><h3  style="margin-left:15px;">Total Dengue Registered : <span id="totalCase">60</span><span>60%</span></h3></span>
					</div>
					<div class="row m_top20">   
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" style="background: rgb(255, 194, 31);">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Districts</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover">
												<thead>
												  <tr>
													<th>Firstname</th>
													<th>Lastname</th>
													<th>Email</th>
												  </tr>
												</thead>
												<tbody>
												  <tr>
													<td>John</td>
													<td>Doe</td>
													<td>john@example.com</td>
												  </tr>
												  <tr>
													<td>Mary</td>
													<td>Moe</td>
													<td>mary@example.com</td>
												  </tr>
												  <tr>
													<td>July</td>
													<td>Dooley</td>
													<td>july@example.com</td>
												  </tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" style="background: rgb(255, 194, 31);">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Mandals</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover">
												<thead>
												  <tr>
													<th>Firstname</th>
													<th>Lastname</th>
													<th>Email</th>
												  </tr>
												</thead>
												<tbody>
												  <tr>
													<td>John</td>
													<td>Doe</td>
													<td>john@example.com</td>
												  </tr>
												  <tr>
													<td>Mary</td>
													<td>Moe</td>
													<td>mary@example.com</td>
												  </tr>
												  <tr>
													<td>July</td>
													<td>Dooley</td>
													<td>july@example.com</td>
												  </tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row bg_ED pad_5">
						<span><h3  style="margin-left:15px;">Total Malaria Registered : <span id="totalCase">40</span></h3></span>
					</div>
					<div class="row m_top20">   
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" style="background: rgb(255, 194, 31);">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Districts</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover">
												<thead>
												  <tr>
													<th>Firstname</th>
													<th>Lastname</th>
													<th>Email</th>
												  </tr>
												</thead>
												<tbody>
												  <tr>
													<td>John</td>
													<td>Doe</td>
													<td>john@example.com</td>
												  </tr>
												  <tr>
													<td>Mary</td>
													<td>Moe</td>
													<td>mary@example.com</td>
												  </tr>
												  <tr>
													<td>July</td>
													<td>Dooley</td>
													<td>july@example.com</td>
												  </tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" style="background: rgb(255, 194, 31);">
									<div class="row">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="m_0 panel-title text-capital fontColor">Top 5 Mandals</h4>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover">
												<thead>
												  <tr>
													<th>Firstname</th>
													<th>Lastname</th>
													<th>Email</th>
												  </tr>
												</thead>
												<tbody>
												  <tr>
													<td>John</td>
													<td>Doe</td>
													<td>john@example.com</td>
												  </tr>
												  <tr>
													<td>Mary</td>
													<td>Moe</td>
													<td>mary@example.com</td>
												  </tr>
												  <tr>
													<td>July</td>
													<td>Dooley</td>
													<td>july@example.com</td>
												  </tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="Assests/js/jquery-1.11.3.js"></script>        
<script type="text/javascript" src="Assests/js/bootstrap.js"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script>
//getCaseCountDiseasesWise();
function getCaseCountDiseasesWise(){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : '01/07/2017',
		toDate : '31/07/2017', 
		diseasesIdList : diseasesIdArr
    }
    $.ajax({
      url : "getCaseCountDiseasesWise",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		      
      }
    
    });
}
//getCaseCountLocationWise();
function getCaseCountLocationWise(){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	//diseasesIdArr.push(2);
	var json = {
		fromDate : '01/07/2017',
		toDate : '31/07/2017', 
		diseasesIdList : diseasesIdArr,
		scopeId : 3        
    }
    $.ajax({
      url : "getCaseCountLocationWise",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		      
      }
    
    });
}
getCaseCountDateWise();
function getCaseCountDateWise(){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : '01/07/2017',
		toDate : '31/07/2017', 
		diseasesIdList : diseasesIdArr,
		rangeType : 'month'          
    }
    $.ajax({
      url : "getCaseCountDateWise",           
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		      
      }
    
    });
}
</script>
</body>
</html>