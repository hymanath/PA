<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LED VENDOR DASHBOARD</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>
<main>
	<section>
		<div class="container-fluid">
			<div class="col-sm-12">
				<div class="white_block">
					<div class="row">
						<div class="col-sm-9">
							<h4 class="font_weight">LED Dashboard - Vendors View</h4>
						</div>
						<div class="col-sm-3 pull-right">
							<div class="input-group inline-block">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								</span>
								<input type="text" class="form-control" id="dateRangePickerLedDashBoard"/>
							</div>
						</div>
					</div>
					<div id="ledDashBoardVendorViewDivId" class="m_top10"></div>
					<div id="levelWiseLedDashBoardId" class="m_top20"></div>
				</div>
			</div>
		</div>
	</section>
</main>

<div class="modal fade" id="dashboardDateModalId" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document" style="width:60%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-capital" id="dashBoardDateModalTitleId"><b></b></h4>
			</div>
			<div class="modal-body">
				<div id="dashboardDateModalDivId"></div>
			</div>
		</div>
	</div>
</div>


<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="Assests/lightMonitoringDashBoard/ledEntry.js" type="text/javascript"></script>
<script>
	var vendorName = '${sessionScope.User.vendorName}';
	var vendorId = '${sessionScope.User.vendorId}';
</script>
</body>
</html>
