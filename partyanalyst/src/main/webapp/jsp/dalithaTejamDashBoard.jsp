<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Dalitha Tejam</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="D2D_Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="D2D_Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="D2D_Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<script src="D2D_Assests/Plugins/Less/less.js"></script>
<link href="D2D_Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/slickNew/slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/slickNew/slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/css/styles.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link href="newCoreDashBoard/iconmoon/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<style type="text/css">
.panel
{
	box-shadow:0 5px 8px 2px rgba(0, 0, 0, 0.3);
}
#menu_multilevelpushmenu{box-shadow:none;}
.DTFC_LeftBodyWrapper
{
	top:-5px !important;
}
.DTFC_LeftBodyWrapper tr td
{
	background-color:#fff;
}
.DTFC_LeftBodyLiner{
	overflow-y:hidden;
}
nav{
	background-color:#fff;
	box-shadow:0px 0px 3px rgba(0,0,0,0.3);
}
</style>
</head>
<body>
<nav>
	<div class="container-fluid full-width-image" style="background-color:#e6b62e;">
		<div class="row">
			<div class="col-sm-2">
				<img src="D2D_Assests/images/Mask Group 1.png" class="img-responsive" style="display:inline-block">
			</div>
			<div class="col-sm-4 m_top20">
				<img src="D2D_Assests/images/Group 3138.png" class="img-responsive"  style="display:inline-block">
			</div>
			<div class="col-sm-2 pull-right">
				<img src="D2D_Assests/images/Group 4.png" class="img-responsive pull-right" style="margin-top:20px;" >
			</div>
		</div>
		<div class="row" style="background-color:#ff0000; line-height:3px;">&nbsp;</div>
	</div>
	<div class="container-fluid">
		<div class="" style="padding:8px">
			<div class="row">
				<!--<div class="col-sm-2 pull-right">
					<div class="form-group">
						<select class="form-control chosen-select" id="districtId">
						</select>
					</div>
				</div>-->
				<div class="col-sm-3 pull-right">
					<div class="input-group pull-right daterangepickerCustom">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
						<input class="form-control" id="dateRangePickerAUM" style="width: 200px;" type="text" />
					</div>
				</div>
				<div class="col-sm-2 pull-right" style="margin-right: 25px;">
					<div class="yellow-square btn btn-sm pull-right">
						<i class="glyphicon glyphicon-refresh refreshDalithaTejam" style="color:#fff;cursor:pointer;"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>
<section class="navbar-section main_bg">
	
	<div class="container-fluid">
		<div class="m_top10"  style="border-top:1px solid #ccc">
			<div class="row">
				<div class="col-sm-12 m_top10">
					<div class="col-sm-4">
						<h4 class="green-text font_weight" id="blockId"></h4>
						<div class="greenBox-today m_top10">
							<div class="row">
								<div class="col-sm-8 pad_right0">
									<div class="media">
										<div class="media-left">
											<img src="D2D_Assests/images/icon-DalithaTejam.png" class="m_top8"/>
										</div>
										<div class="media-body">
											<h5 class="m_top10 font_weight">Visited<br>Villages/ Wards</h5>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="bg_yash_color">
										<h4 class="font_weight" id="todayVisitedCount"></h4>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-5 m_top20">
									<h5 class="font_weight">SC&nbsp;Loan<br/>Applied</h5>
									<div class="bg_yash_color m_top8">
										<h4 class="font_weight" id="todayLoanAppliedCount"></h4>
									</div>
								</div>
								<div class="col-sm-7 m_top20">
									<h5 class="font_weight">Active&nbsp;Dalitha&nbsp;Youth<br/>Registered</h5>
									<div class="bg_yash_color m_top8">
										<h4 class="font_weight" id="todayRegistration"></h4>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<h4 class="grey-text font_weight">Overall</h4>
						<div class="greyBox-overall m_top10">
							<div class="row">
								<div class="col-sm-4 border_right_dalitha">
									<div class="">
										<div class="row">
											<div class="col-sm-8">
												<div class="media">
													<div class="media-left">
														<img src="D2D_Assests/images/icon-TotalVillages.png" class="m_top8" style="width: 50px;"/>
													</div>
													<div class="media-body">
														<h5 class="m_top10 font_weight">Total<br>Village/Ward</h4>
													</div>
												</div>
											</div>
											<div class="col-sm-4 m_top10">
												<div class="bg_yash_color">
													<h4 class="font_weight" id="totalVillagesCountId"></h4>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 m_top10"><hr class="m_0" style="border-color: #000;margin-top:0px;"><p class="text-center m_top-10"><span class="bg-fff" style="padding:0px 8px;">Images <span id="imagesCovered"></span></span></p></div>
										</div>
										<div class="row">
											<div class="col-sm-4 m_top10">
												<h5 class="font_weight">Visited</h5>
												<div class="bg_yash_color m_top10">
													<h4 class="font_weight" id ="totalyesCountId"></h4>
												</div>
											</div>
											<div class="col-sm-4 m_top10">
												<h5 class="font_weight">Maybe</h5>
												<div class="bg_yash_color m_top10">
													<h4 class="font_weight" id="totalMaybeCountId"></h4>
												</div>
											</div>
											<div class="col-sm-4 m_top10">
												<h5 class="font_weight">Pending</h5>
												<div class="bg_yash_color m_top10">
													<h4 class="font_weight" id="totalNoCountId"></h4>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-sm-4 border_right_dalitha">
									<div class="">
										<div class="row">
											<div class="col-sm-8">
												<div class="media">
													<div class="media-left">
														<img src="D2D_Assests/images/icon-SCloansApplied.png" class="m_top8"/>
													</div>
													<div class="media-body">
														<h5 class="m_top10 font_weight">Total SC<br>Loan Applied</h5>
													</div>
												</div>
											</div>
											<div class="col-sm-4 m_top10">
												<div class="bg_yash_color">
													<h4 class="font_weight" id="totalLoanApplied"></h4>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-8 m_top15">
												<div class="media">
													<div class="media-left">
														<img src="D2D_Assests/images/icon-YouthRegistered.png" class="m_top8"/>
													</div>
													<div class="media-body">
														<h5 class="m_top10 font_weight">Total&nbsp;Active Dalitha<br>Youth&nbsp;Reg</h5>
													</div>
												</div>
											</div>
											<div class="col-sm-4 m_top30">
												<div class="bg_yash_color">
													<h4 class="font_weight" id="totalRegistered"></h4>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-sm-4">
									<div class="row">
										<div class="col-sm-7">
											<div class="media">
												<div class="media-left">
													<img src="D2D_Assests/images/icon-TotalPopulation.png" style="height:40px; width:40px;" class="m_top8"/>
												</div>
												<div class="media-body">
													<h5 class="m_top10 font_weight">Total SC<br>Population</h5>
												</div>
											</div>
										</div>
										<div class="col-sm-5 m_top10">
											<div class="bg_yash_color" >
												<h4 class="font_weight" id="totalSCPopulation"></h4>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6 m_top20">
											<h5 class="font_weight">Covered</h5>
											<div class="bg_yash_color m_top10" >
												<h4 class="font_weight" id="totalCoveredPopulation"></h4>
											</div>
										</div>
										<div class="col-sm-6 m_top20">
											<h5 class="font_weight">Not Covered</h5>
											<div class="bg_yash_color m_top10" >
												<h4 class="font_weight" id ="notCovered"></h4>
											</div>
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
						<div class="block_styles_nav" style="border:1px solid #ccc;box-shadow:none;">
							<div id="imagesSliderDivId"></div>
						</div>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-sm-12">
				<div class="yash-border">
					<div class="row">
						<div class="media">
							<div class="media-left">
								<img src="D2D_Assests/images/icon-News.png" style="height:40px; width:40px;" class="m_top8"/>
							</div>
							<div class="media-body">
								<h4 class="m_top15 font_weight">News On Dalitha Tejam - <small class="font_weight" id="newsDateId"></small></h4>
							</div>
							<div id="dalithaTejamOnNewsDivId"></div>
						</div>
					</div>
				</div>
				</div>
			</div>
			<div class="row">
				<div id="leaderandlocationwiseOverviewId"></div>	
			</div>
			<div class="row">
				<div id="levelWiseOverviewId"></div>	
			</div>
		</div>
	</div>
	<div class="modal" tabindex="-1" role="dialog" id="prajaSankalpaYatraModalId">
		  <div class="modal-dialog " style="width:85%;">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<div class="row">
					<div class="col-md-9 col-xs-12 col-sm-9">
						<h4 class="modal-title text-capital" id="prajaSankalpaHeadingId"></h4>
					</div>
					
				</div>
			  </div>
			  <div class="modal-body" style="padding-bottom: 0px; padding-top: 5px;">
						<div class="row">
							<div id="locationWiseArticlesDivId"></div>
						</div>
			  </div>
			  <div class="modal-footer" style="padding-bottom: 0px;">     
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	</div>

<div class="modal" tabindex="-1" role="dialog" id="articleDataID">
	  <div class="modal-dialog " style="width:90%;">       
		<div class="modal-content" style="border-radius:0px">
			<div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close newsImagemodal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<div class="row">
						<div class="col-md-9 col-xs-12 col-sm-9">
							<h4 class="modal-title text-capital" id="articleId"></h4>
						</div>
						
					</div>
				</div>
			<div class="modal-body" style="padding-bottom: 0px; padding-top: 5px;">
				<div class="container m_top20">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="newspopImgDiv"></div>
							<div class="row ">
								<div class="col-md-12 m_top20">
									<div class="paginationId"></div>
								</div>
							</div>
									
						</div>
					</div>
				</div>
			</div>
		  
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div>
<!-------start popup build------------>
<div class="modal fade" id="myModalImageId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%;z-index:9999" >
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close imageCloseBtnCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabelId" style="text-transform: uppercase;"></h4>
      </div>
      <div class="modal-body" style="padding:0px 15px;">
       	<div id="buildPoupupImage"></div>
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="hiddenActivityScopeId"></input>
<!--------End Popup build----------->
<div class="modal fade" id="myModalShowNew"></div>
</section>
<script src="D2D_Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="D2D_Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>
<script src="D2D_Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/slickNew/slick/slick.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript" src="newCoreDashBoard/slimscroller/jquery.slimscroll.js"></script>
<script src="D2D_Assests/js/dalithTejamDashBoard.js" type="text/javascript"></script>	
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