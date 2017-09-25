<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Door To Door Campaign</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="D2D_Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="D2D_Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href=D2D_Assestss/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<script src="D2D_Assests/Plugins/Less/less.js"></script>
<link href="D2D_Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style type="text/css">
.panel
{
	box-shadow:0 5px 8px 2px rgba(0, 0, 0, 0.3);
}
#menu_multilevelpushmenu{box-shadow:none;}
</style>
</head>
<body>
<!--<div class="background-head"></div>
<header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6">
				<img src="D2D_Assests/images/itdplogo.png" class="img-responsive"/>
			</div>
			<div class="col-sm-6">
				<img src="D2D_Assests/images/itdplogoright.png" class="img-responsive pull-right m_top10"/>
			</div>
		</div>
	</div>
</header>-->
<section class="">
	<div class="container-fluid m_top10">
		<div class="row">
			<div class="col-sm-3 col-sm-offset-9">
				
				<div class="input-group">
					<span class="iconRefresh pull-right">
						<i class="glyphicon glyphicon-refresh tooltipCls" data-placement="top" data-toggle="tooltip" data-original-title="Refresh"></i>
					</span>
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
					</span>
					<input type="text" class="form-control" id="dateRangePickerAUM"/>
				</div>
			</div>
			<!--<div class="col-sm-2 pull-right">
				<select class="form-control chosen-select" id="districtNamesId">
				</select>
			</div>-->
		</div>
		<div class="row m_top10">			
			<div class="col-sm-4">
				<div class="block_styles">
					<div id="usersWiseCountsId"></div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="block_styles">
					<div id="houseHoldsWiseCountsId"></div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="block_styles">
					<div id="grievanceWiseCountsId"></div>
				</div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-12 m_top10">
					<div class="block_styles_nav">
						<div class="m_top5">
							<label class="radio-inline">
							 <input type="radio" id="surveyId" name = "optionsRadios" value="survey" class="imagesTypeCls" checked>Survey Images
							</label>
							<label class="radio-inline">
							 <input type="radio" id="hoistingId" value="hoisting" name = "optionsRadios"  class="imagesTypeCls">Flag Hoisting Images
							</label>
						</div>
						<div id="imagesSliderDivId"></div>
					</div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="m_top10">
				<div class="col-sm-4">
					<div class="block_styles">
						<div class="media">
							<div class="media-left">
								<img src="D2D_Assests/icons/Location_Level_Campign_icon.png"></img>
							</div>
							<div class="media-body">
								<h4 class="text-capital" style="line-height: 25px;"><b>Location Level Wise<br/> Campaign Started</b></h4>
							</div>
						</div>
						<div class="row">
							<div id="campignLeveldistrict"></div>
							<div id="campignLevelparliament"></div>
							<div id="campignLevelconstituency"></div>
							<div id="campignLevelmandal"></div>
							<div id="campignLevelpanchayat"></div>
							<div id="campignLevelmuncipality"></div>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="panel-group"  role="tablist" aria-multiselectable="true">
							<div class="panel panel-default panel-black">
								<div class="panel-heading" role="tab">
									<h4><span class="panel-title text-capital">Department wise Grievance</span>
									</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-12">	
												<label class="radio-inline">
													<input class="deptIssueCls" name="reportCls" value="1" checked="" type="radio" style="cursor:pointer;"> Dept Wise
												</label>
												<label class="radio-inline">
													<input class="deptIssueCls" name="reportCls" value="2" type="radio" style="cursor:pointer;"> Issue Wise
												</label>
												<label class="radio-inline">
													<input class="deptIssueCls" name="reportCls" value="3" type="radio" style="cursor:pointer;"> Sub Issue Wise
												</label>
										</div>
									</div>
									<div class="row">
									<div class="col-sm-12 m_top10">	
										<div id="departmentWiseGrievanceDivId"></div>
									</div>
									</div>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-12 m_top10">
				<div id="levelWiseDetailsDivId"></div>	
			</div>
		</div>		
	
</section>

<script src="D2D_Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="D2D_Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/slick/slick.js" type="text/javascript"></script>
<script src="D2D_Assests/js/doorToDoorCampaign.js" type="text/javascript"></script>
<script>
  var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>