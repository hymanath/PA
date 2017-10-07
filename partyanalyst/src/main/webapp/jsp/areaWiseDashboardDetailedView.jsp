<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Constituency Page Nominated Post</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/responsive.css" rel="stylesheet" type="text/css">
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
.bg_color{
	background-color : #ddd;
}
.DTFC_LeftBodyWrapper
{
	top:-13px !important;
}
.DTFC_LeftBodyWrapper tr td , .DTFC_LeftBodyWrapper thead th
{
	background-color:#fff;
}
table.DTFC_Cloned thead,table.DTFC_Cloned tfoot{background-color:white}div.DTFC_Blocker{background-color:white}div.DTFC_LeftWrapper table.dataTable,div.DTFC_RightWrapper table.dataTable{margin-bottom:0;z-index:2}div.DTFC_LeftWrapper table.dataTable.no-footer,div.DTFC_RightWrapper table.dataTable.no-footer{border-bottom:none}
.DTFC_LeftBodyLiner
{
	overflow-y:hidden !important;
}
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
	<div class="container m_top20">
		<section>
			<div class="row">
				<div class="col-sm-12">
					<div class="block">
						<div class="row">
							<div class="col-sm-12">
								<h4 class="theme-title-color">Overall - <b>Overview</b></h4>
								<div id="overAllAnalysisBlockId"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="row">
				<div class="col-sm-12 m_top30">
					<div class="block">
						<h4 class="theme-title-color">Position Level, Gender, Caste & Age - <b>Overview</b></h4>
						<div class="row">
							<div class="col-sm-12">
								<div id="positionLevelBlockId"></div>
							</div>
						</div>
					</div>
					<div class="block">
						<div class="row">
							<div class="col-sm-4">
								<div id="levelWiseBlockOverviewId"></div>
							</div>
							<div class="col-sm-4">
								<div id="ageRangeBlockId"></div>
							</div>
							<div class="col-sm-4">
								<div id="casteCategoryBlockId"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="row">
				<div class="col-sm-12 m_top30">
					<div class="block">
						<h4 class="theme-title-color">Mandal/Town/Division Level Wise Positions <b>Overview</b></h4>
						<div id="LocationWiseLevelBlockId" class="m_top20"></div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="row">
				<div class="col-sm-12 m_top30">
					<div class="block">
						<div class="row">
							<div class="col-sm-6">
								<h4 class="theme-title-color">Caste Wise Position <b>Overview</b></h4>
							</div>
							<div class="col-sm-6 text-right">
								<label class="checkbox-inline">
									<input type="checkbox" name="casteGroup" class="casteGroupId" attr_type="casteGroup" checked /> Caste Group
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" name="casteGroup" class="casteGroupId" attr_type="subCaste"/> Sub-Caste
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div id="casteGroupBlockId" class="m_top20"></div>
								<div id="subCasteBlockId" class="m_top20"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="row">
				<div class="col-sm-12 m_top30">
					<div class="block">
						<h4 class="theme-title-color">Age Wise Position <b>Overview</b></h4>
						<div id="ageGroupBlockId" class="m_top20"></div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="row">
				<div class="col-sm-12 m_top30">
					<div class="block">
						<h4 class="theme-title-color">Mandal/Town/Division Wise Positions <b>Overview</b></h4>
						<div id="mandalBlockId"></div>
					</div>
				</div>
			</div>
		</section>
		
	</div>
</main>
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
<script src="coreApi/js/nominatedPost.js" type="text/javascript"></script>
<script type ="text/javascript">
var locationLevelId = "${param.locationLevelId}";
var userAccessLevelValue = "${param.userAccessLevelValuesArray}";
var userAccessLevelValuesArray =[];
	userAccessLevelValuesArray.push(userAccessLevelValue);
</script>
</body>
</html>