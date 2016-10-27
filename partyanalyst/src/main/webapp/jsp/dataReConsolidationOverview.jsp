<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Core Dashboard</title>
<link href="js/dataReconsolidationOverview/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="js/dataReconsolidationOverview/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="js/dataReconsolidationOverview/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
					
						<select  class="form-control" id="stateOverViewId" >
							<option value="0">Select State</option>
							<option value="1">ANDHRA PRADESH</option>
							<option value="36">TELANGANA</option>
						</select>
					</div>
						
					<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
						<select  class="form-control" id = "districtOverViewId">
							<option value="0">Select District</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
						<select  class="form-control" id ="constituencyOverViewId" >
							<option value="0">Select Constituency</option>
						</select>
				   </div>
						
					 <div class="col-md-3 col-xs-12 col-sm-6 pull-right">
							<div class="input-group inputGCustom">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								 <input type="text" class="form-control datePicker"/>
							</div>
					</div>
					</div>
					<input type="button" class ="btn btn-success pull-right" value ="submit" id="submitId" style="margin-bottom: 0px; margin-top: 5px;">
					<div id = "errorDivId" style = "color:red"></div>
				</div>
				
			</div>
		
				<div class="panel panel-default m_top10" id="dataReconsalationOverviewId">
					<!--<h4 class=" headingStyle text-capital"><b>DATA RECONSALATION OVERVIEW</b></h4>
					<div class="panel-body" style="padding: 25px;">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 ">
								<div class="col-md-2 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Total Smart Devices</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Total Registration</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Data Synced Records</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Data Synced Pending Records</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6">
									<p class="text_bold">Data Synced Pending Devices</p>
									<p class="text_bold">2000</p>
								</div>
							</div>
						</div>
					</div>-->
				</div>
				<div class="panel panel-default" id="userWiseTotalViewId">
					<!--<h4 class=" headingStyle text-capital"><b>user wise total registrations & sync pending details</b></h4>
					<div class="panel-body" style="padding: 25px;">
						<table class="table table-condensed">
							<thead>
								<tr> 
									<th>User Id</th> 
									<th>Device Number</th>
									<th>IMEI Number</th>
									<th>Vendor Name</th> 
									<th>Total Registration</th> 
									<th>Sync Pending</th> 
								</tr>
							</thead>
							<tbody> 
								<tr> 
									<td >U1984</td>
									<td>D1234</td>
									<td>96358571237985</td>
									<td>Ramu</td>
									<td>1000</td>
									<td>200 <button class="btn btn-sm btn-success openPopUpModel" >VIEW DAY WISE</button></td>
								</tr>
								</tbody>
						</table>
					</div>
				</div>-->
			</div>

	

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document" style="width: 85%;">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="text-capital" id="userHeadinId"></h4>
		  </div>
		  <div class="modal-body">
			<table class="table table-condensed id ="tabUserWiseRegistionDetilsId">
				<!-- <thead>
					<tr> 
						<th>Date</th> 
						<th>Name</th>
						<th>Mobile No</th>
						<th>First Record Received</th> 
						<th>Last Record Received</th> 
						<th>Registrations</th> 
						<th>Synced</th>
						<th>Sync Pending</th>
						<th>Total Amount</th>
					</tr>
				</thead>
				<tbody> 
					<tr> 
						<td >29-10-2016</td>
						<td><img src="dist/img/human.jpg" style="width: 40px; height: 40px;"/>Ramesh</td>
						<td class="text_center">999999999</td>
						<td class="text_center">7:15 am</td>
						<td class="text_center">6:30 pm</td>
						<td class="text_center">700</td>
						<td class="text_center">600</td>
						<td class="text_center">50</td>
						<td class="text_center">7000/- </td>
					</tr>
					<tr> 
						<td >29-10-2016</td>
						<td><img src="dist/img/human.jpg" style="width: 40px; height: 40px;"/>Ramesh</td>
						<td class="text_center">999999999</td>
						<td class="text_center">7:15 am</td>
						<td class="text_center">6:30 pm</td>
						<td class="text_center">700</td>
						<td class="text_center">600</td>
						<td class="text_center">50</td>
						<td class="text_center">7000/- </td>
					</tr>
					<tr> 
						<td >29-10-2016</td>
						<td><img src="dist/img/human.jpg" style="width: 40px; height: 40px;"/>Ramesh</td>
						<td class="text_center">999999999</td>
						<td class="text_center">7:15 am</td>
						<td class="text_center">6:30 pm</td>
						<td class="text_center">700</td>
						<td class="text_center">600</td>
						<td class="text_center">50</td>
						<td class="text_center">7000/- </td>
					</tr>
				</tbody> -->
			</table>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		  </div>
		</div>
	  </div>
</div>


<script src="js/dataReconsolidationOverview/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/js/bootstrap.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/js/dataReConsolidationOverview.js" type="text/javascript"></script>
<script type="text/javascript">
$(".datePicker").daterangepicker({
	opens :'left'
});

$(document).on('click','.openPopUpModel',function(){
	$("#myModal").modal("show");
});
</script>
</body>
</html>