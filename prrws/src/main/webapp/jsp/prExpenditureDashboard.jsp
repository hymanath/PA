<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PRExpenditure Dashboard</title>
<link href="Assests/css/bootstrap.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" type="text/less" rel="stylesheet"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/SlickSliderNew/slick.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/SlickSliderNew/slick-theme.less" type="text/less" rel="stylesheet"/>

<link href="Assests/Plugins/ngTable/ng-table.min.css" type="text/less" rel="stylesheet"/>     
    
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assests/Plugins/Less/less.js"></script>
<style>	  
	.tableMenu
	{
		border:1px solid #333;
		padding:1px;
	}
	.tableMenu li
	{
		cursor:pointer;
		padding:5px 12px;
	}
	.tableMenu li.active
	{
		border:1px solid #333;
		
		background-color:#333;
		color:#fff;
	}
	.img-circle {
		border: 2px solid black;
		border-radius: 50%;
		width:80px;
		height:80px;
	}
	.circle{
		width: 60px;
		height: 60px;
		border-radius: 50%;
		background: #8A2BE2;
		padding-left: 20px;
		padding-right: 20px;
		text-align: center;
		color: white;
		vertical-align: middle;
	}

	.border-box{
		padding:10px 20px;
		border: 1px solid #ddd;
	}
	.bg_color{
		background-color : #ddd;
	}
	.panel-default > .panel-heading {
		background-color: black;
	}

	.m_top20 {
		margin-top: 20px !important;
	}
	.block-border {
		border: 1px solid #fff;
		margin: 5px 0;
		padding: 10px;
		background-color:#F9F9F9;
		box-shadow:0px 0px 3px 3px #ABABAB
	}
	.spinner .dot1, .spinner .dot2 {
		animation: 2s ease-in-out 0s normal none infinite running sk-chasingDotsBounce;
		background-color: #1abc9c;
		border-radius: 100%;
		display: inline-block;
		height: 60%;
		position: absolute;
		top: 0;
		width: 60%;
	}
	.spinner .dot2 {
		animation-delay: -1s;
		bottom: 0;
		top: auto;
	}
</style>
</head>
<header style = "box-shadow:none;background-color:#fff;">
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayati Raj, RD & RWS</h4>
					<p>PR-EXPENDITURE - Dashboard</p>
				</div>
				
				<div class="col-sm-3 col-xs-12 pull-right">
					<i class="glyphicon glyphicon-th menu-cls pull-right"></i>
					<div class="menu-data-cls">
						<div class="arrow_box_top">
							<div class="row">
								<!--<div class="col-sm-12">
									<div class="menu-block" style="background-color:#FFBA00">
										<a href="newfundManagementDashboard">
											<h3>FMS</h3>
											<p>Fund Management System</p>
										</a>
									</div>
								</div>  -->
								<div class="col-sm-12">
									<div class="menu-heading-block">
										<h4 class="text-capital">Rural Water Supply</h4>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#56A3C5">
													<a href="ruralWaterSupplyDashBoard">
														<h3>RWS</h3>
														<p>Rural Water Supply</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#1e92b2">
													<a href="swachhBharatMissionIHHL">
														<h3>IHHL</h3>
														<p>Swatch Bharat Mission</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>PANCHAYATI RAJ</h4>
										<div class="row">
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#0F685C">
													<a href="prisDashBoard">
														<h3>PRIS</h3>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#31B8B7">
													<a href="drainDashBoard">
														<h3>DRAINS</h3>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#2C546C">
													<a href="#">
														<h3>ENC</h3>
														<p>Engineering Dept</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#512507">
													<a href="getdailySpikeReport">
														<h3>SA</h3>
														<p>Spike Analysis</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#888420">
													<a href="getlightsMonitoringDashboard">
														<h3>LED</h3>
														<p>Light Monitoring Dashboard</p>
													</a>
												</div>
											</div>
											<div class="col-sm-6 m_top10">
												<div class="menu-block" style="background-color:#ff5e1c">
													<a href="prExpenditureDashboard">
														<h3>PR EXP</h3>
														<p>Panchayat Raj <br/>Expenditure</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#1c94ef">
													<a href="waterTanksClorinationDashBoard">
														<h3>WTC</h3>
														<p>Water Tank Clorination</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-heading-block">
										<h4>RURAL DEVELOPMENT</h4>
										<div class="row">
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#88186B">
													<a href="MGNREGSDashboard">
														<h3>MGNREGS</h3>
														<p>Mahatma Gandhi Rural employement guarantee scheme</p>
													</a>
												</div>
											</div>
											<div class="col-sm-12 m_top10">
												<div class="menu-block" style="background-color:#ff1c5e">
													<a href="RuralDevelopmentDashboard">
														<h3>RD</h3>
														<p>Rural Development Dashboard</p>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12 m_top10">
									<div class="menu-block" style="background-color:#de4524 ">
										<a href="itcDashboard">
											<h3>IT E & C</h3>
											<p>Dashboard</p>
										</a>
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
<body class="bg_color" ng-app="prexpenditureApp" ng-controller="PrexpenditureController as cntrl">
	<div class="container m_top20">
	   <div class="row">
		  <div class="col-sm-12">
			 <h4>STATE LEVEL OVER-VIEW</h4>               
		  </div>
	   </div>
	   <div class="row">
		  <div class="col-sm-4 ">
			 <div class="text-center block-border">
				<div class="media">
				   <div class="media-left">
					  <div class="img-circle" style="padding-top: 14px;">
						 <img src="Assests/img/Gross_Amount_icon.png" >
					  </div>
				   </div>
				   <div class="media-body">
					  <h4>Gross-Amount</h4>
						<h4>
							<i class="fa fa-inr"></i> 
							<div ng-show="cntrl.showHideGrossAmountSpinner">
								<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
							</div>
							<div ng-show="cntrl.showHideGrossAmount" id="grossId">{{cntrl.grossAmount}}</div>
						</h4>
				   </div>
				</div>
			 </div>
		  </div>
		  <div class="col-sm-4 ">
			 <div class="text-center block-border">
				<div class="media">
				   <div class="media-left">
					  <div class="img-circle" style="padding-top: 14px; padding-left: 10px;">
						 <img  src="Assests/img/Deductions_icon.png">
					  </div>
				   </div>
				   <div class="media-body">   
					  <h4>DEDUCTIONS</h4>
					  <h4>
						<i class="fa fa-inr"></i> 
						<div ng-show="cntrl.showHideDeductionsSpinner">
							<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
						</div>	
						<div ng-show="cntrl.showHiDedeductions" id="deductionId">{{cntrl.deductions}}</div>
					  </h4>
				   </div>
				</div>
			 </div>
		  </div>
		  <div class="col-sm-4 ">
			 <div class="text-center block-border">
				<div class="media">
				   <div class="media-left">
					  <div class="img-circle"style="padding-top: 13px; padding-left: 20px;">
						 <img src="Assests/img/Netamount_icon.png">
					  </div>
				   </div>
				   <div class="media-body">
					  <h4>NET AMOUNT</h4>
					  <h4>
						<i class="fa fa-inr"></i>
						<div ng-show="cntrl.showHideNetAmountSpinner">
							<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
						</div>
						<div ng-show="cntrl.showHiNetAmount" id="netId">{{cntrl.netAmount}}</div>
					  </h4>
				   </div>
				</div>
			 </div>
		  </div>  
	   </div>
	   <div class="block-border m_top20" style="display:none;">
		  <div class="row">
			 <div class="col-sm-12 ">
				<h4>GRAMAPANCHAYAT TRANSACTIONS</h4>
			 </div>
			 <div class="col-sm-4">
				<div id="gramPanchayatTransactions"></div>
			 </div>
			 <div class="col-sm-8">
				<div class="range-slider m_top20">
				   <input type="text" class="range_03" value=""/>
				</div>
			 </div>
		  </div>
	   </div>	
<section>
	<div class="m_top20">
				<!--<div id="projectDataOverview" class="m_top20"></div>-->
				<div class="m_top20">   
				<div class="row">
			   <div class="col-sm-12">
				  <div class="panel-group" id="accordionOverview" role="tablist" aria-multiselectable="true">
					 <div class="panel panel-default panel-black" ng-repeat="block in cntrl.blockParamas">      
						<div class="panel-heading" role="tab" id="{{cntrl.headingConsolidatedIds[$index]}}">
						   <a role="button" class="panelCollapseIcon" overview-level="{{block}}" data-toggle="collapse" data-parent="#accordionOverview" href="{{cntrl.collapseConsolidatedLinkIds[$index]}}" aria-expanded="true" aria-controls="{{cntrl.collapseConsolidatedIds[$index]}}">
							  <h4 class="panel-title text-capital">{{block}} level - overview</h4>
						   </a>
						</div>     
						<div id="{{cntrl.collapseConsolidatedIds[$index]}}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="{{cntrl.headingConsolidatedIds[$index]}}">
						   <div class="panel-body">
								<div ng-show="{{cntrl.showHideBlockDataAvailable[$index]}}" class="col-sm-12">
									<h3>No Data Available.....</h3>
								</div> 
								<div ng-show="{{cntrl.showHideSearchSpinner[$index]}}" class="row pull-right">
									<div class="col-sm-12">
										<div class="form-group">  
											<label for="search">Search</label>    
											<input type="text" ng-model="searchValue" class="form-control" placeholder="Search">
										</div>                    
								  </div>
								</div>    
								<div class="row">           
									<div class="col-sm-12">  
										<div ng-show="{{cntrl.showHideSpinner[$index]}}">     
											<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
										</div>
										<div ng-show="{{cntrl.ngTableHideShow[$index]}}">
											<table ng-table="{{cntrl.ngTableArr[$index]}}" class="table table-condensed table-bordered table-striped" show-filter="false">
												<tr ng-repeat="data in $data | filter:searchValue">   
													<td title="'Location Name'" filter="{locationName}" sortable="'locationName'">{{data.locationName}}</td>   
													<td title="'Gross Amount'"  filter="{grossAmount}" sortable="'grossAmount'">{{data.grossAmount}}</td>
													<td title="'Deductions'"   filter="{deductions}" sortable="'deductions'"> {{data.deductions}}</td>
													<td title="'Net Amount'"    filter="{netAmount}" sortable="'netAmount'"> {{data.netAmount}}</td>
													</tr>          
												</table>     
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
</section>
<div class="modal fade" id="modalGsDivId" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document" style="width: 95%;">
		<div class="modal-content modal-custom">
			<div class="modal-header">
			   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#000000">&times;</span></button>
			   <h4 class="modal-title" id="modalGsHeadingId">Modal title</h4>
			</div>
			<div class="modal-body">
			   <div id="modalGsTable"></div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
   <div class="modal fade" id="modalGramPanchayatDivId" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document" style="width: 95%;">
		 <div class="modal-content modal-custom">
			<div class="modal-header">
			   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color:#000000">&times;</span></button>
			   <h4 class="modal-title" id="modalGramPanchayatHeadingId"></h4>
			</div>
			<div class="modal-body">
			   <div class="media">
				  <div class="media-left">
					 <p id="gramPanchayatcode"></p>
					 <p id="gramPanchayataccno"></p>
					 <p id="gramPanchayatisfc"></p>
				  </div>
				  <div class="media-body">
					 <div class="col-sm-4 ">
						<div class="text-center block-border">
						   <div class="media">
							  <div class="media-left">
								 <div class="img-circle" style="padding-top: 14px;">
									<img src="Assests/img/Gross_Amount_icon.png" >
								 </div>
							  </div>
							  <div class="media-body">
								 <h4>Gross-Amount</h4>
								 <h4 id="gramPanchayatGrossAmount"><i class="fa fa-inr"></i> </h4>
							  </div>
						   </div>
						</div>
					 </div>
					 <div class="col-sm-4 ">
						<div class="text-center block-border">
						   <div class="media">
							  <div class="media-left">
								 <div class="img-circle" style="padding-top: 14px; padding-left: 10px;">
									<img  src="Assests/img/Deductions_icon.png">
								 </div>
							  </div>
							  <div class="media-body">
								 <h4>DEDUCTIONS</h4>
								 <h4 id="gramPanchayatDeduction"><i class="fa fa-inr"></i> </h4>
							  </div>
						   </div>
						</div>
					 </div>
					 <div class="col-sm-4 ">
						<div class="text-center block-border">  
						   <div class="media">
							  <div class="media-left">     
								 <div class="img-circle"style="padding-top: 13px; padding-left: 20px;">
									<img src="Assests/img/Netamount_icon.png">
								 </div>
							  </div>
							  <div class="media-body">
								 <h4>NET AMOUNT</h4>
								 <h4 id="gramPanchayatNetAmount"><i class="fa fa-inr"></i></h4>
							  </div>
						   </div>
						</div>
					 </div>
				  </div>
			   </div>
			   <div id="modalGramPanchayatTable"></div>
			</div>
		 </div>
		 <!-- /.modal-content -->
	  </div>
	  <!-- /.modal-dialog -->
   </div>
</div>
<script type="text/javascript" src="Assests/js/jquery-1.11.3.js"></script>        
<script type="text/javascript" src="Assests/js/angular.js"></script>        
<script type="text/javascript" src="Assests/js/bootstrap.js"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/SlickSliderNew/slick.min.js" type="text/javascript"></script>   
<script src="Assests/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>   
<script src="Assests/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script> 
<script src="Assests/Plugins/ngTable/ng-table.min.js" type="text/javascript"></script>     
<!--<script src="Assests/PRExpenditure/prExpenditureDashboard.js" type="text/javascript"></script>-->
<script src="Assests/PRExpenditure/prexpenditureApp.js" type="text/javascript"></script> 
<script src="Assests/PRExpenditure/controller/prexpenditure_controller.js" type="text/javascript"></script> 
<script src="Assests/PRExpenditure/service/prexpenditure_service.js" type="text/javascript"></script> 


<script type="text/javascript">
/* $(".range_03").ionRangeSlider({
    type: "double",
    min: 0,
    max: 1000,
    from: 200,
    to: 800
});  
 */  
</script>  
 </body>
</html>