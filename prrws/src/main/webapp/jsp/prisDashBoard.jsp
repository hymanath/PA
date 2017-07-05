<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mahatma Gandhi National Rural EGS</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>

</head>
<body>
<style>
.bg_color{
	background-color : #ddd;
}
</style>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">PRIS Survey</h4>
					<p>DASHBOARD</p>
				</div>
			</div>
		</div>
	</nav>
	<section class="navbar-section">
		<div class="container-fluid">
			<div class="row navbar_section_whitebk">
				<div class="col-sm-12 m_top5 ">
					<ul class="list-inline pull-right calendar_active_cls">
						<li><img src="Assests/icons/Overall_icon.png"/>&nbsp;&nbsp;<b>Overall</b></li>
						<li><img src="Assests/icons/Today_icon.png"/>&nbsp;&nbsp;<b>Today</b></li>
						<li><img src="Assests/icons/Week_icon.png"/>&nbsp;&nbsp;<b> Week</b></li>
						<li class="active"><img src="Assests/icons/Month_icon.png"/>&nbsp;&nbsp;<b>Month</b></li>
						<li><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b>3Months</b></li>
						<li><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b>6Months</b></li>
						<li><img src="Assests/icons/3612months_icon.png"/>&nbsp;&nbsp;<b>Year</b></li>
						<li id="singleDateRangePicker"><img src="Assests/icons/CustomRange_icon.png"/>&nbsp;&nbsp;<b>Custom Range</b></li>
						</li>
					</ul>  
				</div>
			</div>
		</div>
	</section>
</header>
<main>
	<div class="container-fluid">
	<section>
			<div class="row">
				<div class="col-sm-12 m_top5 ">
					<div class="col-sm-3">
						<div class="white-block">
							<div class="media blockHeights block_styles">
							  <div class="media-left img_middle">
								  <img class="media-object" src="Assests/icons/house_icon.png" alt="house_icon">
							  </div>
							  <div class="media-body">
								<h4 class="">TOTAL HOUSEHOLDS</h4>
								<h4 class="m_top10 title_align">985932445</h4>
							  </div>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="white-block">
							<div class="media blockHeights block_styles">
							  <div class="media-left img_middle">
								  <img class="media-object" src="Assests/icons/Target_icon.png" alt="Target_icon">
							  </div>
							  <div class="media-body">
								<h4 class="">TARGET <span class="pull-right color_Tlabel">30%</span></h4>
								<small>Overall</small>
								<h4 class="m_top10 title_align">985932445</h4>
							  </div>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="white-block">
							<div class="media blockHeights block_styles">
							  <div class="media-left img_middle">
								  <img class="media-object" src="Assests/icons/Achived_icon.png" alt="Achived_icon">
							  </div>
							  <div class="media-body">
								<h4 class="">ACHIVED <span class="pull-right color_Alabel">30%</span></h4>
								<small>Overall</small>
								<h4 class="m_top10 title_align">9859324452</h4>
							  </div>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="white-block">
							<div class="media blockHeights block_styles">
								<p>THIS MONTH - JUNE </p>
								<div class="row m_top5">
									<div class="col-sm-6">
										<img class="img_width" src="Assests/icons/Target_icon.png" alt="Achived_icon">
										<span class="pull-right color_Tlabel">30%</span>
										<h5>TARGET</h5>
										<h4 class="m_top5"><b>985656</b></h4>
									</div>
									<div class="col-sm-6 border_right border_adjust_align">
										<img  class="img_width" src="Assests/icons/Achived_icon.png" alt="Achived_icon">
										<span class="pull-right color_Alabel">30%</span>
										<h5>ACHIVED</h5>
										<h4 class="m_top5"><b>9856565</b></h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div id="levelWiseBlockId" class="m_top20"></div>
		</section>
	</div>
</main>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/js/Pris.js" type="text/javascript"></script>
<script src="Assests/prisDashBoard/prisDashBoard.js" type="text/javascript"></script>
</body>
</html>