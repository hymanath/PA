<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dalitha Tejam</title>
<link href="dalithaTejam/Less/bootstrap.less" rel="stylesheet" type="text/less">
<script src="dalithaTejam/Plugins/Less/less.js"></script>
<link href="dalithaTejam/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="dalithaTejam/css/custom.less" type="text/css" rel="stylesheet"/>
<link href="dalithaTejam/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dalithaTejam/Less/glyphicons.less" rel="stylesheet" type="text/less">
<script type ="text/javascript" src="dalithaTejam/js/jquery-3.2.1.js"></script>
<script src="dalithaTejam/js/bootstrap.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="dalithaTejam/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="dalithaTejam/slick/slick-theme.css"/>
<script type="text/javascript" src="dalithaTejam/slick/slick.min.js"></script>
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<!-- Date Range Picker -->
<link href="dalithaTejam/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<nav>
	<div class="container-fluid full-width-image" style="background-color:#e6b62e;">
		<div class="row">
			<div class="col-sm-6">
				<img src="dalithaTejam/images/header-bg-logo.png" class="img-responsive" >
			</div>
			<div class="col-sm-2 pull-right">
				<img src="dalithaTejam/images/Group 4.png" class="img-responsive pull-right" style="margin-top:20px;" >
			</div>
		</div>
		<div class="row" style="background-color:#ff0000; line-height:3px;">&nbsp;</div>
	</div>
</nav>
<section class="navbar-section">
		<div class="container-fluid">
			<div class="row  m_top10">
				<div class="col-sm-7 pull-right">
					<div class="row">
						<div class="col-sm-2">
							<div class="yellow-square pull-right">
								<i class="fas fa-sync-alt"></i>
							</div>
						</div>
						<div class="col-sm-5">
							<div class="input-group pull-right">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<input class="form-control" id="dateRangePickerAUM" style="width: 200px;" type="text" />
							</div>
						</div>
						<div class="col-sm-5">
							<div class="form-group">
								<select class="form-control chosen-select" id="district">
								</select>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
<main>
	<section>
		<div class="container-fluid">
			<div class="row m_top20">
				<div class="col-sm-12">
					<div class="white_block_emeeting_new">
						<div class="row">
							<div class="col-sm-4">
								<h3 class="green-text font_weight">Today</h3>
								<div class="greenBox-today m_top10">
									<div class="row">
										<div class="col-sm-8 pad_right0">
											<div class="media">
												<div class="media-left">
													<img src="dalithaTejam/images/icon-DalithaTejam.png" class="m_top8"/>
												</div>
												<div class="media-body">
													<h5 class="m_top10">Visited<br>Villages/ Wards</h5>
												</div>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="bg_yash_color">
												<h4 class="font_weight" id="todayVisitedCount"></h4>
											</div>
										</div>
									</div>
									
									<div class="row m_top46">
										<div class="col-sm-4">
											<h5>SC Loan Applied</h5>
											<div class="bg_yash_color m_top8">
												<h4 class="font_weight" id="todayLoanAppliedCount"></h4>
											</div>
										</div>
										<div class="col-sm-8">
											<h5>Active Dalitha Youth <br>Registered</h5>
											<div class="bg_yash_color m_top8">
												<h4 class="font_weight" id="todayRegistration"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-8">
								<h3 class="grey-text font_weight">Overall</h3>
								<div class="greyBox-overall m_top10">
									<div class="row">
										<div class="col-sm-4">
											<div class="row">
												<div class="col-sm-8 pad_right0">
													<div class="media">
														<div class="media-left">
															<img src="dalithaTejam/images/icon-TotalVillages.png" class="m_top8"/>
														</div>
														<div class="media-body">
															<h5 class="m_top10">Total<br>Villages/ Wards</h4>
														</div>
													</div>
												</div>
												<div class="col-sm-4 new-pad">
													<div class="bg_yash_color">
														<h4 class="font_weight" id="totalVillagesCountId"></h4>
													</div>
												</div>
											</div>
											<div class="row m_top8">
												<div class="col-sm-2 no-padding">
													<div class="side-borders"></div>
												</div>
												<div class="col-sm-8 text-align-center">
													<h5>Images <span class="font_weight" id ="imagesCovered"></span></h5>
												</div>
												<div class="col-sm-2 no-padding">
													<div class="side-borders"></div>
												</div>
											</div>
											
											<div class="row m_top20">
												<div class="col-sm-4 new-pad">
													<h5>Visited</h5>
													<div class="bg_yash_color m_top8">
														<h4 class="font_weight" id ="totalyesCountId"></h4>
													</div>
												</div>
												<div class="col-sm-4 new-pad">
													<h5>Maybe</h5>
													<div class="bg_yash_color m_top8">
														<h4 class="font_weight" id="totalMaybeCountId"></h4>
													</div>
												</div>
												<div class="col-sm-4 new-pad">
													<h5>Pending</h5>
													<div class="bg_yash_color m_top8">
														<h4 class="font_weight" id="totalNoCountId"></h4>
													</div>
												</div>
											</div>
										</div>
										
										<div class="col-sm-4 border-rt-lt">
											<div class="row">
												<div class="col-sm-8">
													<div class="media">
														<div class="media-left">
															<img src="dalithaTejam/images/icon-SCloansApplied.png" class="m_top8"/>
														</div>
														<div class="media-body">
															<h5 class="m_top10">Total SC<br>Loan Applied</h5>
														</div>
													</div>
												</div>
												<div class="col-sm-4 new-pad">
													<div class="bg_yash_color">
														<h4 class="font_weight" id="totalLoanApplied"></h4>
													</div>
												</div>
											</div>
											<hr class="hr-new res">
											<div class="row">
												<div class="col-sm-8">
													<div class="media">
														<div class="media-left">
															<img src="dalithaTejam/images/icon-YouthRegistered.png" class="m_top8"/>
														</div>
														<div class="media-body">
															<h5 class="m_top10">Total Active<br>Dalitha Youth Regd</h5>
														</div>
													</div>
												</div>
												<div class="col-sm-4 new-pad">
													<div class="bg_yash_color">
														<h4 class="font_weight" id="totalRegistered"></h4>
													</div>
												</div>
											</div>
										</div>
										
										<div class="col-sm-4">
											<div class="row">
												<div class="col-sm-8">
													<div class="media">
														<div class="media-left">
															<img src="dalithaTejam/images/icon-TotalPopulation.png" style="height:40px; width:40px;" class="m_top8"/>
														</div>
														<div class="media-body">
															<h5 class="m_top10">Total SC<br>Population</h5>
														</div>
													</div>
												</div>
												<div class="col-sm-4 new-pad">
													<div class="bg_yash_color" style="padding-left:5px !important;">
														<h4 class="font_weight" id="totalSCPopulation"></h4>
													</div>
												</div>
											</div>
											<div class="row m_top60">
												<div class="col-sm-6">
													<h5>Covered</h5>
													<h4 class="font_weight" id="totalCoveredPopulation"></h4>
												</div>
												<div class="col-sm-6">
													<h5>Not Covered</h5>
													<h4 class="font_weight" id ="notCovered"></h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row m_top10">
							<div class="col-sm-12 m_top10">
									<div class="block_styles_nav">
										<div id="imagesSliderDivId"></div>
									</div>
							</div>
						</div>
						
						<div class="row m_top20">
							<div class="col-sm-12">
								<div class="yash-border">
									<div class="row">
										<div class="media">
											<div class="media-left">
												<img src="dalithaTejam/images/icon-News.png" style="height:40px; width:40px;" class="m_top8"/>
											</div>
											<div class="media-body">
												<h3 class="m_top15">News On Dalitha Tejam</h3>
											</div>
											<div class="row m_top10">
												<div class="col-sm-6">
													<div class="yash_color_news">
														<h4 class="font_weight">TDP Party</h4>
														<div class="row m_top10">
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>MAIN EDITION</h4>
																	<h3 class="blue-text-news m_top10" id="tdpMediatotalCount"></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>POSITIVE</h4>
																	<h3 class="green-text-news m_top10" id="tdpMediaPostiveCount"> &nbsp;&nbsp;<span class="small-font"id="tdpMediapostivePercentage"></span></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>NEGATIVE</h4>
																	<h3 class="red-text-news m_top10" id="tdpMedianegativeCount"> &nbsp;&nbsp;<span class="small-font" id="tdpMedianaegativePercentage"></span></h3>
																</div>
															</div>
														</div>
														<div class="row m_top10">
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>DIST EDITION</h4>
																	<h3 class="blue-text-news m_top10" id ="tdpMediaDistricttotalCount"></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>POSITIVE</h4>
																	<h3 class="green-text-news m_top10" id="tdpMediaDistrictPostiveCount"> &nbsp;&nbsp;<span class="small-font" id="tdpMediaDistrictpostivePercentage"></span></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>NEGATIVE</h4>
																	<h3 class="red-text-news m_top10" id="tdpMediaDistrictnegativeCount"> &nbsp;&nbsp;<span class="small-font" id="tdpMediaDistrictnaegativePercentage"></span></h3>
																</div>
															</div>
														</div>
														
													</div>
												</div>
												
												<div class="col-sm-6">
													<div class="yash_color_news">
														<h4 class="font_weight">Other Party</h4>
														<div class="row m_top10">
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>MAIN EDITION</h4>
																	<h3 class="blue-text-news m_top10" id="otherMediatotalCount"></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>POSITIVE</h4>
																	<h3 class="green-text-news m_top10" id="otherMediaPostiveCount"> &nbsp;&nbsp;<span class="small-font"id="otherMediapostivePercentage"></span></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>NEGATIVE</h4>
																	<h3 class="red-text-news m_top10" id="otherMedianegativeCount"> &nbsp;&nbsp;<span class="small-font" id="otherMedianaegativePercentage"></span></h3>
																</div>
															</div>
														</div>
														<div class="row m_top10">
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>DIST EDITION</h4>
																	<h3 class="blue-text-news m_top10" id="otherMediaDistricttotalCount"></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>POSITIVE</h4>
																	<h3 class="green-text-news m_top10" id="otherMediaDistrictPostiveCount"> &nbsp;&nbsp;<span class="small-font" id="otherMediaDistrictpostivePercentage"></span></h3>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="dark_yash_box">
																	<h4>NEGATIVE</h4>
																	<h3 class="red-text-news m_top10" id ="otherMediaDistrictnegativeCount"> &nbsp;&nbsp;<span class="small-font" id="otherMediaDistrictnaegativePercentage"></span></h3>
																</div>
															</div>
														</div>
														
													</div>
												</div>
												
											</div>
										</div>
									</div>
								
								</div>
							</div>
						</div>
						
						<div class="row m_top20">
							
							<div class="col-md-12">
								<div class="col-sm-12 m_top10">
									<div id="levelWiseOverviewId"></div>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<script src="dalithaTejam/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dalithaTejam/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dalithaTejam/js/dalithTejamDashBoard.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript">
window.onload=function(){
  $('.slider').slick({
  autoplay:true,
  autoplaySpeed:1500,
  arrows:true,
  prevArrow:'<button type="button" class="slick-prev"></button>',
  nextArrow:'<button type="button" class="slick-next"></button>',
  centerMode:true,
  slidesToShow:5,
  slidesToScroll:1
  });
};
</script>		
</body>
</html>