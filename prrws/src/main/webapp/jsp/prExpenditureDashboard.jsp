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

</style>
</head>
<body class="bg_color" ng-app="prexpenditureApp" ng-controller="PrexpenditureController as cntrl">
	<div class="container ">
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
					  <h4><i class="fa fa-inr"></i> <div id="grossId">{{cntrl.grossAmount}}</div></h4>
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
					  <h4><i class="fa fa-inr"></i> <div id="deductionId">{{cntrl.deductions}}</div></h4>
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
					  <h4><i class="fa fa-inr"></i> <div id="netId">{{cntrl.netAmount}}</div></h4>
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
				<div class="blockHeightsScroll m_top20" style="overflow:scroll;height:325px;">
				   <table class="table">
					  <thead class="bg_color">
						 <tr>
							<th>District</th>
							<th>Panchayat</th>
							<th>Gross Amount</th>
							<th>Deductions</th>
							<th>Net Amount</th>
							<th>Total Transations</th>
							<th>Last Transation Amount & Date</th>
						 </tr>
					  </thead>
					  <tbody>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
						 <tr>
							<td>Srikakulam</td>
							<td>Ahmedguda</td>
							<td>44000</td>
							<td>44000</td>
							<td>44000</td>
							<td>6</td>
							<td>44000(15-08-2015)</td>
						 </tr>
					  </tbody>
				   </table>
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
					 <div class="panel panel-default panel-black">
						<div class="panel-heading" role="tab" id="headingConsolidatedDistrict">
						   <a role="button" class="panelCollapseIcon" overview-level="District" data-toggle="collapse" data-parent="#accordionOverview" href="#collapseConsolidatedDistrict" aria-expanded="true" aria-controls="collapseConsolidatedDistrict">
							  <h4 class="panel-title text-capital">District level - overview</h4>
						   </a>
						</div>
						<div id="collapseConsolidatedDistrict" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingConsolidatedDistrict">
						   <div class="panel-body">
							  <div class="row">
								 <div class="col-sm-12">
									<div id="collapseConsolidatedViewDistrict">
									   <table class="table table-condensed table-striped" id="datatableDistrict" style="width:100%;">
										  <thead>
											 <tr>
												<th> DISTRICT</th>
												<th><img src="Assests/img/Gross_Amount_icon.png"><br>Grossamount</th>
												<th><img src="Assests/img/Deductions_icon.png"><br>Deductions</th>
												<th><img src="Assests/img/Netamount_icon.png"><br>Netamount</th>
											 </tr>
										  </thead>
										  <tbody>
											 <tr ng-repeat="district in cntrl.districtData">
												<td>{{district.locationName}}</td>
												<td><i class="fa fa-inr"></i>{{district.grossAmount}}</td>
												<td><i class="fa fa-inr"></i>{{district.deductions}}</td>
												<td><i class="fa fa-inr"></i>{{district.netAmount}}</td>
											 </tr>
										  </tbody>
									   </table>
									</div>
								 </div>
							  </div>
						   </div>
						</div>
						<div class="panel panel-default panel-black">
						   <div class="panel-heading" role="tab" id="headingConsolidatedDivision">
							  <a role="button" class="panelCollapseIcon collapsed" overview-level="Division" data-toggle="collapse" data-parent="#accordionOverview" href="#collapseConsolidatedDivision" aria-expanded="true" aria-controls="collapseConsolidatedDivision">
								 <h4 class="panel-title text-capital">Division level - overview</h4>
							  </a>
						   </div>
						   <div id="collapseConsolidatedDivision" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingConsolidatedDivision">
							  <div class="panel-body">
								 <div class="row">
									<div class="col-sm-12">
									   <div id="collapseConsolidatedViewDivision">
										  <table class="table table-condensed table-striped" id="datatableDivision" style="width:100%;">
											 <thead>
												<tr>
												   <th>DIVISION</th>
												   <th><img src="Assests/img/Gross_Amount_icon.png"><br>Grossamount</th>
												   <th><img src="Assests/img/Deductions_icon.png"><br>Deductions</th>
												   <th><img src="Assests/img/Netamount_icon.png"><br>Netamount</th>
												</tr>
											 </thead>
											 <tbody>
												 <tr ng-repeat="district in cntrl.divisionData">
													<td>{{district.locationName}}</td>
													<td><i class="fa fa-inr"></i>{{district.grossAmount}}</td>
													<td><i class="fa fa-inr"></i>{{district.deductions}}</td>
													<td><i class="fa fa-inr"></i>{{district.netAmount}}</td>
											     </tr>
											 </tbody>
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
<!--<script src="Assests/PRExpenditure/prExpenditureDashboard.js" type="text/javascript"></script>-->
<script src="Assests/PRExpenditure/prexpenditureApp.js" type="text/javascript"></script> 
<script src="Assests/PRExpenditure/service/prexpenditure_service.js" type="text/javascript"></script> 
<script src="Assests/PRExpenditure/controller/prexpenditure_controller.js" type="text/javascript"></script> 

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