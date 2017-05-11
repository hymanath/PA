<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/css/custom.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12"> 
			<div class="panel panel-default panelheightsAlert">
			  <div class="panel-heading">
				<h4 class="panel-title text-capital">Title</h4>
			  </div>
			  <div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
						<label>District</label>
						<select class="form-control" id="districtId">
							<option value="0">Select District</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Constituency</label>
						<select class="form-control" id="constituencyId">
							<option value="0">Select Constituency</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Mandal/Town/Division</label>
						<select class="form-control" id="LevelId">
							<option value="0">Select Level</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Village/Ward</label>
						<select class="form-control" id="villWardId">
							<option value="0">Select Village/Ward</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Designation</label>
						<select class="form-control" id="designationId">
							<option value="0">Select Designation</option>
						</select>
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-sm">View</button>
					</div>
				</div>
			  </div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title text-capital">Title</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>District</th>
									<th>Constituency</th>
									<th>Mandal</th>
									<th>Village</th>
									<th>Designation</th>
									<th>Name</th>
									<th>Contact No</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Nellore</td>
									<td>Nellore-Rural</td>
									<td>Nellore-Rural</td>
									<td>Principal Sect</td>
									<td>Principal Sect</td>
									<td>999999999</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="dist/alertDashBoard/dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
</body>
</html>