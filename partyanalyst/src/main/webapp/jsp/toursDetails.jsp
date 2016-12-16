<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Update Tour Details</title>
<link href="Assets/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assets/css/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">tour details overview</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table tableTours">
								<thead>
									<th></th>
									<th>total leaders</th>
									<th>submited leaders</th>
									<th>not submited leaders</th>
									<th>compliance</th>
									<th>non compliance</th>
								</thead>
								<tr>
									<td>minister <i class="glyphicon glyphicon-info-sign"></i></td>
									<td>17</td>
									<td>17</td>
									<td>17</td>
									<td>17</td>
									<td>17</td>
								</tr>
								<tr>
									<td>minister <i class="glyphicon glyphicon-info-sign"></i></td>
									<td>10</td>
									<td>10</td>
									<td>10</td>
									<td>10</td>
									<td>10</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">update your details</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-xs-12 col-sm-3">
								<label>Designation Level</label>
								<select>
									<option>a</option>
								</select>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3">
								<label>Select Name</label>
								<select>
									<option>a</option>
								</select>
							</div>
						
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<h4 class="panel-title text-capital">selected profile</h4>
								<div class="pad_15 b_1">
									<ul class="list-inline profileUl">
										<li>
											<div class="pad_10">
												<img src="Assests/img/profile.png" style="height: 30px;width: 30px;"/>
												<p>Kollu Ravindra</p>
												<p>IXR9816267</p>
												<p>+91 9988998899</p>
											</div>
											<div class="pad_10 borderGreen">
												<label class="checkbox-inline">
													<input type="checkbox"/>Select Profile</label>
											</div>
										</li>
									</ul>
								</div>

							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<h4 class="panel-title text-capital">update tour details</h4>
								<div class="panel panel-default">
									<div class="panel-body borderGreen">
										<i class="closeIcon glyphicon glyphicon-remove"></i>
										<div class="row">
											<div class="col-md-3 col-xs-12 col-sm-3">
												<label>Tour Date</label>
												<div class="input-group inputGCustom">
													<input type="text" class="form-control">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-calendar"></i>
													</span>
												</div>
											</div>
											<div class="col-md-4 col-xs-12 col-sm-4">
												<label>Tour Category</label>
												<select>
													<option>a</option>
												</select>
											</div>
											<div class="col-md-2 col-xs-12 col-sm-2">
												<label>Tour Location</label>
												<select>
													<option>a</option>
												</select>
											</div>
											<div class="col-md-3 col-xs-12 col-sm-3">
												<label>Tour Type</label>
												<select>
													<option>a</option>
												</select>
											</div>
										</div>
										<div class="row m_top10">
											<div class="col-md-12 col-xs-12 col-sm-12">
												<label>Add Commment/Tour Description</label>
												<textarea class="form-control"></textarea>
											</div>
										</div>
									</div>
									<div class="panel-footer borderGreen text-right">
										<button class="btn btn-success">+ ADD TOUR</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
<script src="Assets/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assets/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
