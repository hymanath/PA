<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FIELD MONITORING REPORT</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
.bg_ff
{
	background-color:#fff
}
</style>
</head>
<body>
<div class="container m_top20">
  <h4></h4>
  <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_ff">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6 m_top10">
							<h4>FIELD MONITORING REPORT</h4>
						</div>
						<!--<div class="col-md-3 col-xs-12 col-sm-3 pull-right">
							<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<input type="text" class="form-control" id="singleDate"/>
							</div>
						</div>-->
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<!--<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="btn-group pull-right">
							  <button type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Export to PDF/Excel <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
							  </ul>
							</div>
						</div>-->
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="fieldUserDetailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
							<div id="errorDivId"></div>
							<div id="fieldUserDetailsId"></div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="dataCollectorsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
							<div id="errorDivId"></div>
							<div id="dataCollectorsDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/FieldMonitoring/fieldMonitoringReport.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>


<script type="text/javascript">
$("#singleDate").daterangepicker({
	singleDatePicker : true,
	maxDate:moment()
})
getFieldMonitoringReport();

</script>
</body>
</html>