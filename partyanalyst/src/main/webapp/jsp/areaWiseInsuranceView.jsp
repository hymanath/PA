<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insurance Details</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/responsive.css" rel="stylesheet" type="text/css">
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>

</style>
</head>
<body>
<div class="background-head"></div>
<header>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<img src="coreApi/img/logo.png" class="img-responsive"/>
			</div>
			<div class="col-sm-6">
				<img src="coreApi/img/right_side_head.png" class="img-responsive pull-right"/>
			</div>
		</div>
	</div>
</header>
<main>
	<div class="container m_top30">
		<section>
			<div class="row">
				<div class="col-sm-12">
					<div class="block boderBlock">
						<div id="CategoryInsuranceBlockDivId"></div>
					</div>
				</div>
			</div>
		</section>
		
		<section class="m_top10">
			<div class="row">
				<div class="col-sm-12">
					<div class="block boderBlock">
						<div id="locationIsuranceBlockDivId"></div>
					</div>
				</div>
			</div>
		</section>
	</div>
<main>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="coreApi/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="coreApi/js/insuranceConstituencyPage.js" type="text/javascript"></script>
<script type ="text/javascript">
var locationLevelId = "${param.locationLevelId}";
var locationName = "${param.locationLevelName}";
var customStartInsuranceDate = "${param.customStartInsuranceDate}";
var customEndInsuranceDate = "${param.customEndInsuranceDate}";
var userAccessLevelValue = "${param.userAccessLevelValuesArray}";
var userAccessLevelValuesArray =[];
	userAccessLevelValuesArray.push(userAccessLevelValue);
</script>
</body>
</html>