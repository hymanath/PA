<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> REPRESENTATIONS DASHBOARD DETAILS </title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/SlickSliderNew/slick.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/SlickSliderNew/slick-theme.less" type="text/less" rel="stylesheet"/>
<link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.css" media="screen" />
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<!--<link href="Assests/Plugins/sliderbar/bootstrap-slider.css" rel="stylesheet" type="text/css"/>-->
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/print.css" rel="stylesheet" type="text/css"/>
 <link rel="stylesheet" type="text/css" href="Assests/SimplePagination/simplePagination.css"/>
 <style>
	
	
 </style>
</head>
<body>
<header style="box-shadow:none;" class="dispalyNone">
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3">
					<img src="Assests/images/aplogo.png" class="" style="width: 80px; height: 80px; padding: 10px;"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h3 class="text-capital"><span style="color: rgb(255, 255, 255); font-weight: bold; margin-right: 7px;font-size: 20px;">MINISTER</span> <span class="text-color">PETITION MANAGEMENT SYSTEM</span></h3>
					<p style="color: rgb(255, 255, 255);">Information Technology,Panchayathi Raj,Rural Water Supply & </p>
					<p style="color: rgb(255, 255, 255);">Rural Development</p>
				</div>
				<div class="col-sm-5 col-xs-12 pull-right innerPageHeader">
					<i class="glyphicon glyphicon-th menu-cls pull-right" style="position:relative;color:#AA8440"></i>
					<div class="menuCls">
						<div class="arrow_box_top">
							<div class="row">
								<div style="padding:10px;">
									<!--<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationRequestEntry" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">ADD PETITION</a></h4>
									</div>
									<div class="col-sm-6">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;" ><a href="representationRequestEntryViewMembers" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">VIEW PETITION</a></h4>
									</div>-->
								</div>
								<div id="menuId"></div>
							</div>
							<!--<div class="row">
								<div style="padding:10px;">
								<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationsDashboard" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">DASHBOARD</a></h4>
									</div>
								</div>
							</div>-->
							<div class="row">
								<div style="padding:10px;">
								<div class="col-sm-12">
									<a class="btn btn-primary btnSearch m_top5 pull-right" href="petitionsLogout" style="display:inline-block" style="cursor:pointer;"><i class="fa fa-sign-out" style="color:#FFBA00;"></i>&nbsp;&nbsp;Sign-out</a>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</nav>
<%@ page import="com.itgrids.dto.UserVO" %>
<% 
	UserVO userVo=(UserVO)session.getAttribute("User");
out.println("<h4 class='pull-right' style='margin:6px 10px; color:green;'>&nbsp;&nbsp; "+userVo.getUserName()+"</h4>");   
%>
</header>
<main>
	<section>
		<div class="container-fluid">
		<div class="row dispalyNone">
			<div class="col-sm-12">
				<h4 class="pull-right">Note: All amounts in Crores</h4>
			</div>
		</div>
			<div class="row m_top10 dispalyNone">
				<div class="col-sm-12">
					<div class="white-block petition_block">
					<div class="row">
						<div class="col-sm-6 pull-right">
							<div class="row">
								<div class="col-sm-6 " id="deptsDivId">
									<select class="form-control chosen-select"  data-placeholder="SELECT DEPARTMENT"  id="departmntId" multiple>
									</select>
								</div>
								<div class="col-sm-6">	
									<div class="input-group inline-block">
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar clearDataCls" aria-hidden="true"></span>
										</span>
										<input type="text"  class="form-control" id="dateRangePicker"/>
									</div>
								</div>
							</div>
						</div>		
					</div>
						<div class="row m_top20">
						<div class="col-sm-6" style="display:none;" id="completeOverViewDivId"> 
								<h4><img src="Assests/icons/Group 4361.png"><b>Complete Overview</b></h4>
								<div class="petition_cls m_top10" style="border:1px solid #6DA4D6;background-color:#E2F2F9" id="completeOverviewId">
									</div>
						</div>
							<div class="col-sm-6" style="display:none;" id="myActionDivId">
								<h4><img src="Assests/icons/Group 4375.png" style="height:25px"><b>My Actions</b></h4>
								<div class="m_top10" id="myActionsId">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="panel-group"  style="display:none;" id="statusDivId">
						<div class="panel panel-default panel-black">
							<div class="panel-heading" role="tab" id="headingStatusOverview">
								<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseStatusOverview">
									<h4 class="panel-title">STATUS OVERVIEW</h4>
								</a>
							</div>
							<div id="collapseStatusOverview" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingStatusOverview">
								<div class="panel-body">
								<div>
									<div class="row">
										<div class="col-sm-12">
											<div id="statusOverviewId"></div>
										</div>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-group"  id="leadWiseDivId" style="display:none;">
						<div class="panel panel-default panel-black">
							<div class="panel-heading" role="tab" id="headingLeadWiseOverview">
								<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseLeadWiseOverview">
									<h4 class="panel-title">LEAD WISE OVERVIEW</h4>
								</a>
							</div>
							<div id="collapseLeadWiseOverview" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingLeadWiseOverview">
								<div class="panel-body">
									<div class="">
										<div class="row">
											<div class="col-sm-12">
												<div id ="leadWiseOverviewId"></div>						
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel-group" style="display:none;" id="officerBlock">
						<div class="panel panel-default panel-black">
							<div class="panel-heading" role="tab" id="headingReferralWiseOverview123">
								<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordionReferralWiseOverview123" href="#collapseReferralWiseOverview123">
									<h4 class="panel-title text-capital">Status - Officer Wise</h4>
								</a>
							</div>
							<div id="collapseReferralWiseOverview123" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingReferralWiseOverview123">
								<div class="panel-body">
								
								<div class="pad_5" >			
									<div class="row">
										<div id="officerWiseBlockDetailsDivId"></div>
									</div>
								</div>
								<div class="pad_5">
									<div class="row">
										<div class="col-sm-12">
											<h4 class=""><b>Offficer Status Wise Details</b></h4>
										</div>
									</div>
									<div class="row m_top10">
										<div class="col-sm-12">
											<div id="officerDesignationWiseTableDivId"></div>
										</div>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="panel-group" id="refWiseOverViewId" style="display:none;">
						<div class="panel panel-default panel-black">
							<div class="panel-heading" role="tab" id="headingReferralWiseOverview">
								<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseReferralWiseOverview">
									<h4 class="panel-title">REFERRAL WISE OVERVIEW</h4>
								</a>
							</div>
							<div id="collapseReferralWiseOverview" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingReferralWiseOverview">
								<div class="panel-body">
									<div >
										<div class="row">
											<div class="col-sm-2 pull-right">
												<label>Leads:</label>
												<select class="form-control" style="background-color:#E4E6E7;" id="briefLeadId">
													<option value="0">All</option>
												</select>
											</div>
										</div>
									</div>
									<div >
										<div class="row">
											<div class="col-sm-12">
												<div id="desigWiseCountId"></div>
											</div>
										</div>
										<div class="row" id="">
											<div class="col-sm-2 col-sm-offset-10" id="" >			
												<label>Designations:</label>
												<select class="form-control" style="background-color:#E4E6E7;" id="LeadersId">
												</select>
											</div> 
										</div>
									</div>
									<div id="refWiseOverViewDivId" style="display:none;">
										<div class="row">
											<div class="col-sm-12">
												<h4 id ="headingDetailsId" style="font-weight:bold; margin:10px;">MLA REFFERAL WISE OVERVIEW DETAILS</h4>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div id="desigWiseCandidatesView"></div>
											</div>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="panel-group" style="display:none;" id="locationWiseBlockId">
						<div class="panel panel-default panel-black">
							<div class="panel-heading" role="tab" id="headingLocationWise123">
								<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordionLocationWise123" href="#collapseLocationWise123">
									<h4 class="panel-title text-capital">Location Wise OVERVIEW </h4>
								</a>
							</div>
							<div id="collapseLocationWise123" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingLocationWise123">
								<div class="panel-body">
								
								<div class="pad_5" >			
									<div class="row">
										<div class="col-sm-7">
											<div class="row">
												<!--<div class="col-sm-4 m_top5">
													<select class="chosen-select form-control" id="departmentId" data-placeholder="All" multiple>
													
													</select>
												</div>-->
												<div class="col-sm-4 m_top5">
													<select class="chosen-select form-control" id="statusLocId" data-placeholder="All" multiple>
														<!--<option value="0">All</option>-->
													</select>
												</div>
												<div class="col-sm-4 m_top5">
													<select class="chosen-select form-control" id="subjectId" data-placeholder="All" multiple>
														<!--<option value="0">All</option>-->
													</select>
												</div>
											</div>
										</div>		
										<!--<div class="col-sm-2">
											<select class="chosen-select form-control" id="worksNonWorksLocId">
												<option value="0">Select Works/Non Works</option>
											</select>
										</div>-->
										<!--<div class="col-sm-3 m_top5">	
											<div class="input-group inline-block" >
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar clearDataCls" aria-hidden="true"></span>
												</span>
												<input type="text"  class="form-control" id="dateRangePicker"/>
											</div>
										</div>-->
										<div class="col-sm-2 pull-right">	
											<button type="button" class="btn btn-success getLocWiseDetailsCls form-control">Submit</button>
										</div>
									</div>
								</div>
								<div class="pad_5">
									<!--<div class="row">
										<div id="districtWiseLocationDetailsDivId"></div>
									</div>-->
									<div class="panel-group">
										<div class="panel panel-default panel-black">
											<div class="panel-heading" role="tab" id="headingDistrictOverview">
												<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseDistrictOverview">
													<h4 class="panel-title">DISTRICT WISE REPRESENTATION OVERVIEW</h4>
												</a>
											</div>
											<div id="collapseDistrictOverview" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingDistrictOverview">
												<div class="panel-body">
												<div>
													<div class="row">
														<div class="col-sm-12">
															<div id="districtWiseLocationDetailsDivId"></div>
														</div>
													</div>
												</div>
												</div>
											</div>
										</div>
									</div>
									<!--<div class="row">
										<div id="constituencyWiseLocationDetailsDivId"></div>
									</div>-->
									<div class="panel-group">
										<div class="panel panel-default panel-black">
											<div class="panel-heading" role="tab" id="headingConstituecyOverview">
												<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseConstituecyOverview">
													<h4 class="panel-title">CONSTITUECY WISE REPRESENTATION OVERVIEW</h4>
												</a>
											</div>
											<div id="collapseConstituecyOverview" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingConstituecyOverview">
												<div class="panel-body">
												<div>
													<div class="row">
														<div class="col-sm-12">
															<div id="constituencyWiseLocationDetailsDivId"></div>
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
				</div>
				<div id="printableArea" style="display:none;" class="displayBlock">
					<div class="white-block pad_10" id="printcontent">
						<div class="row">
							<div class="col-sm-12">
								<div id="pdfWiswPetitionsView" ></div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>
	</section>
</main>

<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/login/loginMenu.js"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/SlickSliderNew/slick.js" type="text/javascript"></script>
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
var loginDesigId = 0;

//wurl = wurl.replace("/PartyAnalyst","");

</script>
</body>
</html>