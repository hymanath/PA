<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> VIEW PETITIONS DETAILS </title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.css" media="screen" />
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<!--<link href="Assests/Plugins/sliderbar/bootstrap-slider.css" rel="stylesheet" type="text/css"/>-->
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>

 <link rel="stylesheet" type="text/css" href="Assests/SimplePagination/simplePagination.css"/>
 <style>
	
	
 </style>
</head>
<body>

<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="bg_backGroundMain">
					<div class="container-fluid">
						<div class="row">
							<div class="text-center col-sm-11">
								<img src="Assests/images/Group 2.png" class="" alt="logo">
							</div>
							<div class="col-sm-1 m_top20">
								<i class="glyphicon glyphicon-th menu-cls pull-right" style="top:8px;"></i>
							
								<div class="menu-data-cls">
									<div class="arrow_box_top">
										<div class="row">
											<div style="padding:10px;">
												<div class="col-sm-6">
													<h4><a target="_blank" href="representationRequestEntry">ADD PETITION</a></h4>
												</div>
												<div class="col-sm-6">
													<h4 class=""><a target="_blank" href="representationRequestEntry">VIEW PETITION</a></h4>
												</div>
											</div>
										</div>
										<div class="row">
											<div style="padding:10px;">
											<div class="col-sm-12">
												<a class="btn btn-primary btnSearch m_top5 pull-right" href="petitionsLogout" style="display:inline-block" style="cursor:pointer;">LOGOUT</a>
												
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
	</nav>
</header>
<main>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="white-block petition_block">
						<div class="row">
						
							<div class="col-sm-6">
								<h4><img src="Assests/icons/Group 4361.png"><b>Complete Overview</b></h4>
								<div class="petition_cls m_top10" style="border:1px solid #6DA4D6;background-color:#E2F2F9" id="completeOverviewId">
									</div>
							</div>
							
							
							
							<div class="col-sm-6">
								<h4><img src="Assests/icons/Group 4375.png" style="height:25px"><b>My Actions</b></h4>
								<div class="petition_cls m_top10" style="border:1px solid #FFBB00;background-color:#FFF8E5" id="myActionsId">
									</div>
							</div>
						
						
						</div>
					</div>
				</div>
				
				<div class="col-sm-12 m_top20">
					<div class="white-block">
						<div class="media">
							<div class="media-left"></div>
							<div class="media-body">
								<h4 class="m_top10"><img src="Assests/icons/Group 4631.png"><b>Status Overview</b></h4>
							</div>
						</div>
						<div class="row m_top10">
							<div class="col-sm-12" id="statusOverviewId">
							</div>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</section>
</main>

<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<!--<script src="Assests/Plugins/sliderbar/bootstrap-slider.js" type="text/javascript"></script>-->
<script src="Assests/SimplePagination/simplePagination3.js" type="text/javascript"></script>

<!--<script src="Assests/ruralWaterSupply/custom.js" type="text/javascript"></script>-->
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
<!-- for file uploader -->
<script type="text/javascript" src="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.js"></script>
<script src="Assests/representationRequest/representationsDashboard.js" type="text/javascript"></script>
<script type="text/javascript">

$(document).on("click",".closeSecondModal",function(){
	
    setTimeout(function(){
      $("body").addClass("modal-open")
    },1000);
  });
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/representationRequestEntryViewMembers")));

</script>
</body>
</html>