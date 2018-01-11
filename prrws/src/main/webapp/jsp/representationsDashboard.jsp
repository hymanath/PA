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
<header style="box-shadow:none;">
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3">
					<img src="Assests/images/aplogo.png" class="" style="width: 80px; height: 80px; padding: 10px;"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h3 class="text-capital"><span style="color: rgb(255, 255, 255); font-weight: bold; margin-right: 7px;font-size: 20px;">MINISTER</span> <span class="text-color">PETITION MANAGEMENT SYSTEM</span></h3>
					<p style="color: rgb(255, 255, 255);">Information Technology,Panchayathi Raj & </p>
					<p style="color: rgb(255, 255, 255);">Rural Development</p>
				</div>
				<div class="col-sm-5 col-xs-12 pull-right innerPageHeader">
					<i class="glyphicon glyphicon-th menu-cls pull-right" style="position:relative;color:#AA8440"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<div class="row">
								<div style="padding:10px;">
									<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationRequestEntry" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">ADD PETITION</a></h4>
									</div>
									<div class="col-sm-6">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;" ><a href="representationRequestEntryViewMembers" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">VIEW PETITION</a></h4>
									</div>
								</div>
							</div>
							<div class="row">
								<div style="padding:10px;">
								<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationsDashboard" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">DASHBOARD</a></h4>
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
				<!--<div class="col-sm-12 m_top20">
					<div class="white-block" style="padding:10px">
						<h4><img src="Assests/icons/2000px-Circle-icons-crossroads.svg.png"><b style="padding-left:5px;">Lead wise Overview</b></h4>
						<div class="row m_top10" style="height:375px;overflow-y:">
						<div id ="leadWiseOverviewId">						
						</div>
					</div>
				</div>
				</div>-->
				</div>
				<div class="col-sm-12 m_top20">
				<div class="white-block petition_block bor_c2">
					<div class="row">
						<div class="col-sm-3">
							<img src="Assests/icons/group.PNG" style="float:left;"><h4 style="padding-top:18px;"><b>Referral wise Overview</b></h4>
						</div>
						<div class="col-sm-2 pull-right">
							<label>Leads:</label>
							<select class="form-control" style="background-color:#E4E6E7;" id="briefLeadId">
								<option value="0">All</option>
							</select>
						</div>	
					</div>
				</div>
			</div>
			<div class="col-sm-12">			
				<div class="white-block petition_block bor_c2">
					<div class="row" id="desigWiseCountId">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="white-block petition_block bor_c2">
					<div class="row" style="padding:5px 5px;" id="desigWiseCandidatesView">
					</div>
				</div>
			</div>
			<!--<div class="col-sm-12 m_top20">
					<div class="white-block" style="padding:10px">
						<div class="row">
							<div class="col-sm-9"><h4><img src=""><i class="fa fa-info info_icon" aria-hidden="true"></i><b style="padding-left:2px">Status - Officer Wise</b></h4></div>
							<div class="col-sm-3">
								<select>
									<option>All</option>
								</select>
							</div>
							<div class="col-sm-12">
								<div class="row m_top10">
									<div class="col-sm-3">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h5><i class="fa fa-user-circle-o" aria-hidden="true"></i> <b>Minister</b></h5>
											</div>
											<div class="panel-body">
												<div class="row" style="border-bottom:1px solid grey;padding-bottom:10px">
													<div class="col-sm-6">
														<p>Representations</p>
														<h4><b>187</b></h4>
													</div>
													<div class="col-sm-6">
														<p>Works</p>
														<h4><b>187</b></h4>
													</div>
												</div>
												<div class="row m_top5">
													<div class="col-sm-12" style="padding:2px">
														<div class="row">
															<div class="col-sm-6" style="padding-top:5px"><span>Pending</span><b class="pull-right">100</b></div>
															<div class="col-sm-6">
																<div class="well pad_5 m_bottom_0">
																	<span>Works</span><b class="pull-right">100</b>
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
							
							<div class="col-sm-12 m_top10">
								<div class="officers_block" style="padding:5px 10px">
									<div class="row">
										<div class="col-sm-3">
											<h5><b>Venkateshwar Rao</b></h5>
											<p style="font-size:13px">Principal Secretary</p>
										</div>
										<div class="col-sm-3">
											<div class="well pad_5 m_bottom_0">
												<div class="row">
													<div class="col-sm-4">
														<p style="font-size:12px">Total Representations</p>
														<h4><b>390</b></h4>
													</div>
													<div class="col-sm-8">
														<div style="padding:10px;background-color:#DCDCDC">
															<div class="row">
																<div class="col-sm-4" style="border-right:1px solid grey">
																	<p>Works</p>
																	<h5><b>39,000</b></h5>
																</div>
																<div class="col-sm-8">
																	<p>Estimated Cost</p>
																	<h5><b>39,000</b></h5>
																</div>
															</div>
														</div>
													</div>
													
												</div>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="well pad_5 m_bottom_0">
												<div class="row">
													<div class="col-sm-4">
														<p style="font-size:12px">Pending Action Memo</p>
														<h4><b>390</b></h4>
													</div>
													<div class="col-sm-8">
														<div style="padding:10px;background-color:#DCDCDC">
															<div class="row">
																<div class="col-sm-4" style="border-right:1px solid grey">
																	<p>Works</p>
																	<h5><b>39,000</b></h5>
																</div>
																<div class="col-sm-8">
																	<p>Estimated Cost</p>
																	<h5><b>39,000</b></h5>
																</div>
															</div>
														</div>
													</div>
													
												</div>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="well pad_5 m_bottom_0">
												<div class="row">
													<div class="col-sm-4">
														<p style="font-size:12px">Total Representations</p>
														<h4><b>390</b></h4>
													</div>
													<div class="col-sm-8">
														<div style="padding:10px;background-color:#DCDCDC">
															<div class="row">
																<div class="col-sm-4" style="border-right:1px solid grey">
																	<p>Works</p>
																	<h5><b>39,000</b></h5>
																</div>
																<div class="col-sm-8">
																	<p>Estimated Cost</p>
																	<h5><b>39,000</b></h5>
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
						
						
					</div>
				</div>
				-->
		
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
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/representationsDashboard")));
//wurl = wurl.replace("/PartyAnalyst","");

</script>
</body>
</html>