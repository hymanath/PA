<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assembly Session</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.expand-icon
{
	cursor:pointer;
	font-size:8px;
	color:#4cae4c;
	border:1px solid #4cae4c;
	border-radius:3px;
	padding:1px;
	margin-right:8px;
}
.form-control
{
	border-radius:0px;border-left:0px;
}
</style>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default panelNew">
				<div class="panel-heading">
					<h4 class="panel-title">Assembly Session View</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Election Year</label>
							<select id="electionYear" class="form-control" onchange="getSessionYears();">
								<option value="0"> Select Election Year</option>
							</select>
							<!--<div id="electionYear"></div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Session Year</label>
							<select id="sessionYear" class="form-control" onchange="getAllSessions();">
								<option value="0"> Select Session Year</option>
							</select>
							<!--<div id="sessionYear"></div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Assembly Session</label>
							<select id="assemblySession" class="form-control" onchange="getDates();">
								<option value="0"> Select Assembly Session</option>
							</select>
							<!--<div id="assemblySession"></div>-->
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6">
							<label>Date Range</label>
							<div class="input-group">
								<input class="form-control" type="text" id="dateRange" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</div>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
							<button type="button" class="btn btn-success" onClick="getSessionDetails();">VIEW</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default panelNew">
				<div class="panel-heading">
					<h4 class="panel-title">Assembly Session View</h4>
				</div>
				<div class="panel-body pad_0">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="sessionDetails"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script src="newCoreDashBoard/js/jquery-1.11.3" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="debate/js/assemblySessionReport.js" type="text/javascript"></script>
<script type="text/javascript">
	globalOnLoadCalls();
	onLoadInitialisations();
	$(".chosen-select").chosen({width:'100%'});
</script>
</body>
</html>