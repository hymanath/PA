<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Election Details</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/responsive.css" rel="stylesheet" type="text/css">
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<script src="https://use.fontawesome.com/e94c241642.js"></script>
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
		<div class="row">
			<div class="col-sm-12">
				<div class="block">
					<h4 class="theme-title-color">Assembly Election Year Wise Details</h4>
					<div id="candidatesResultsDivId"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 m_top10">
				<div id="levelWiseCandidatesResultsDivId" class="m_top10"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 m_top10">
				<div class="block">
					<div id="locationWiseStrongVsPoor" class="m_top10"></div>
				</div>
			</div>
		</div>
		
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
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="coreApi/js/electionConstituencyPage.js" type="text/javascript"></script>
</body>
</html>