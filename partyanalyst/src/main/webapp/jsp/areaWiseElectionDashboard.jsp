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
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
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
		<h3 class="text-capital text-center"><span id="mianHeadingId"></span> Level Election Results</h4>
		<div class="row">
			<div class="col-sm-12">
				<div id="candidatesResultsDivId"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 m_top10">
				<div class="block">
					<div class="row">
						<div class="col-sm-12">
						<div class="col-sm-3">
							<label>Election Years</label>
							<select class="" id="electionYearId1" multiple>
							</select>
						</div>
						<div class="col-sm-3 m_top30">
							<label class="radio-inline">
							  <input type="radio" id="mainId1" value="MAIN" name="optionsRadios2" class="electionSubTypeCls1" checked>Main Election
							</label>
							<label class="radio-inline">
							  <input type="radio" id="byeId1" value="BYE" name="optionsRadios2"  class="electionSubTypeCls1">Bye Election
							</label>
						</div>
						<div class="col-sm-3 m_top30">
							<button class="btn btn-success btn-xs getDetailsCls1">Submit</button>
						</div>
					</div>
					</div>
					
					<div id="levelWiseCandidatesResultsDivId" class="m_top10"></div>
					<div id="locationWiseCandidatesResultsDivId" class="m_top20"></div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 m_top10">
				<div class="block">
					<div class="row">
						<div class="col-sm-2 elctionTypeValCls" style="display:none;">
							<label>Election Type</label>
							<select class="form-control chosen-select" id="elctionTypeValId">
								<option value="0">Select Election Type</option>
								<option value="1">Parliament</option>
								<option value="2" selected>Assembly</option>
								<option value="3">MPTC</option>
								<option value="4">ZPTC</option>
								<option value="5">MUNCIPALITY</option>
								<option value="6">CORPORATION</option>
								<option value="7">Greater Municipal Corp</option>
								<option value="8">Panchayar</option>
								<option value="9">Ward</option>
							</select>
						</div>
						<div class="col-sm-2 searchLevelCls" style="display:none;">
							<label>Search Level</label>
							<select class="form-control chosen-select" id="searchLevelId">
								<option value="district" selected>District</option>
								<option value="constituency">Constituency</option>
								<option value="parliament">Parliament</option>
							</select>
						</div>
						<div class="col-sm-2">
							<label>Party</label>
							<select class="form-control chosen-select" id="partyId">
								<option value="0">Select Party</option>
								<option value="872" selected>TDP</option>
								<option value="1117">YSRC</option>
								<option value="163">BJP</option>
								<option value="72">AIMIM</option>
								<option value="362">INC</option>
								<option value="265">CPI</option>
								<option value="269">CPM</option>
								<option value="662">PRP</option>
								<option value="886">TRS</option>
							</select>
						</div>
						<div class="col-sm-3">
							<label>Election Years</label>
							<select class="" id="electionYearId" multiple>
							</select>
						</div>
						<div class="col-sm-3 m_top30">
							<label class="radio-inline">
							  <input type="radio" id="mainId" value="MAIN" name="optionsRadios" class="electionSubTypeCls" checked>Main Election
							</label>
							<label class="radio-inline">
							  <input type="radio" id="byeId" value="BYE" name="optionsRadios"  class="electionSubTypeCls">Bye Election
							</label>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<button class="btn btn-success btn-sm pull-right getDetailsCls">Submit</button>
						</div>
					</div>
					<div id="locationWiseStrongVsPoor" class="m_top10"></div>
				</div>
			</div>
		</div>
		
	</div>
<main>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
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
<script type="text/javascript" src="coreApi/js/bootstrap-multiselect.js"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="coreApi/js/electionConstituencyPage.js" type="text/javascript"></script>
<script type="text/javascript">
var locationLevelId = "${param.locationLevelId}";
var constituencyId = "${param.constituencyId}";
var locationLevelName = "${param.locationLevelName}";
var userAccessLevelValue = "${param.userAccessLevelValuesArray}";
var userAccessLevelValuesArray =[];
	userAccessLevelValuesArray.push(userAccessLevelValue);
</script>
</body>
</html>