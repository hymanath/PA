<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/less" href="Assests/less/bootstrap.less" />
		<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
		<link href="Assests/css/cust om.less" rel="stylesheet" type="text/less"/>
		<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
		<title>ITMinisterDashboard</title>
		
		<style>
			.borderBottom
			{
			 border-bottom:1px solid #B7B7B7;
			}
			.box_shad
			{
			 box-shadow:0px 0px 2px rgba(0,0,0,0.6);
			}
			.white_color
			{
			 color:#fff;
			}
			h1,h2,h3,h4,h5,h6
			{
			 margin:0px !important;
			 border:none;
			}
			p{
			 margin:0px;
			}
			
			.block
			{
			 border:2px solid #E4E8EE;
			 background-color:#fff;
			 padding:10px;
			 
			}
			.m_Top20
			{
				margin-top:20px;
			}
			.m_top10
			{
				margin-top:10px;
			}
			.m_top5
			{
				margin-top:5px;
			}
			.m_Top15{
				margin-top:15px;
			}
			h2{
				text-align:center
			}
		</style>
	</head>
	<body>
		<div class="container-fluid m_Top20" >
			<div class="row">
				<div class="col-sm-12">
					<div class="block box_shad">
						<div class="row">
							<div class="col-sm-6">
								<h3>Overall Overview</h3>
								<p class="m_top5" style="border-top:1px solid grey; width:50%;"></p>
							</div>
						</div>
						<div class="row m_top10">
							<div class="col-sm-6">
								<div class="block">
									<div class="row">
										<div class="col-sm-12">
											<div class="media">
												<div class="media-left">
													<img src="" class="media-object" style="width:60px">
												</div>
												<div class="media-body">
													<h4 class="media-heading">DEVELOPERS</h4>
												</div>
											</div>
										</div>
									</div>
										<div class="block m_top10" style=" border:1px;background-color:#CCB064;border-radius: 5px;">
											<div class="row">
												<div class="cols-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>REGISTERED COMPANIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" >
														<h2 class="white_color" id="devloperRegCompanyId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #CCB064;border-left:7px solid #CCB064;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>REGISTRED PROPERTIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" style="background-color:#CCB064;border-radius: 5px;">
														<h2 style="text-align:center" class="white_color" id="devloperRegPropertiesId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #17AD17;border-left:7px solid #17AD17;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>APPROVED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#17AD17;border-radius: 5px;">
														<h2 class="white_color" id="devloper3"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #FF0000;border-left:7px solid #FF0000;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>REJECTED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#FF0000;border-radius: 5px;">
														<h2 class="white_color" id="devloper4"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #FFBA00;border-left:7px solid #FFBA00;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>IN-PROGRESS</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#FFBA00;border-radius: 5px;">
														<h2 class="white_color" id="devloper2"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #FF6E00;border-left:7px solid #FF6E00;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>PENDING/NOT POSSIBLE</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#FF6E00;border-radius: 5px;">
														<h2 class="white_color" id="devloper1"></h2>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							<div class="col-sm-6 ">
								<div class="block">
									<div class="row">
										<div class="col-sm-12">
											<div class="media">
												<div class="media-left">
													<img src="" class="media-object" style="width:60px">
												</div>
												<div class="media-body">
													<h4 class="media-heading">IT COMPANIES</h4>
												</div>
											</div>
										</div>
									</div>
										<div class="block m_top10" style=" border:1px;background-color:#CCB064;border-radius: 5px;">
											<div class="row">
												<div class="cols-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>REGISTERED COMPANIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right " >
														<h2 class="white_color" id="regItCompanyId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #CCB064;border-left:7px solid #CCB064;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>REGISTRED PROPERTIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" style="background-color:#CCB064;border-radius: 5px;">
														<h2 class="white_color"id="regCompanyPropertiesId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #17AD17;border-left:7px solid #17AD17;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>APPROVED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#17AD17;border-radius: 5px;">
														<h2 class="white_color" id="company3"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #FF0000;border-left:7px solid #FF0000;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>REJECTED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#FF0000;border-radius: 5px;">
														<h2 class="white_color" id="company4"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #FFBA00;border-left:7px solid #FFBA00;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>IN-PROGRESS</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#FFBA00;border-radius: 5px;">
														<h2 class="white_color" id="company2"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_top10" style="border:1px solid #FF6E00;border-left:7px solid #FF6E00;border-radius: 5px;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_top10">
														<h5><b>PENDING/NOT POSSIBLE</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#FF6E00;border-radius: 5px;">
														<h2 class="white_color" id="company1"></h2>
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
				<div class="block box_shad ">
					<div class="row ">
							<div class="col-sm-6 ">
								<h3>Government Approval Process Flow</h3>
								<p class="m_top5" style="border-top:1px solid grey; width:60%;"></p>
							</div>
						</div>
				
						<div class="row m_top10">
							<div class="col-sm-12">
								<div class="block m_top5" style="border:1px solid #e3e3e3; padding:10px; border-radius:5px; background-color: #FFFFFF;">
									<div class="row m_top5" >
										<div class="col-sm-3">
											<div class="pad_5 m_top10" style="border-right:1px solid grey; height: 56px;">
												<h4 align="center" class="font_weight"><b>Pending@</b></h4>
											</div>
										</div>
										<div class="col-sm-9">
											<div id="pendingLevelId"></div>
										</div>
									</div>
								</div>	
								<div class=" block m_top5" style="border:1px solid #e3e3e3; padding:10px; border-radius:5px; background-color: #FFFFFF;">
									<div class="row m_top5 ">
										<div class="col-sm-3">
											<div class="pad_5" style="border-right:1px solid grey; height: 56px;">
												<div class="row" >
													<div class="col-sm-6 m_Top15">
														<h4 style="text-align: center"class="font_weight m_Top20">Developer</h4>
													</div>
													<div class="col-sm-4 m_top10">
														<h4 class="font_weight well-sm" style="background-color: #CDDAEF; "id="devloperId"></h4>
													</div>
													
												</div>		
											</div>
										</div>
										<div id="devloperPendingLevelDivId"> </div>
									</div>
								</div>
								<div class="m_top5" style="border:1px solid #e3e3e3; padding:10px; border-radius:5px; background-color: #FFFFFF;">
									<div class="row">
										<div class="col-sm-3">
											<div class="pad_5" style="border-right:1px solid grey; height: 56px;">
												<div class="row">
													<div class="col-sm-6 m_Top15">
														<h4 style="text-align: center" class="font_weight m_Top20">IT Company</h4>
													</div>
													<div class="col-sm-4 m_top10">
														<h4 class="font_weight well-sm" style="background-color: #CDDAEF;" id="itCompanyId"></h4>
													</div>
												</div>		
											</div>
										</div>
										<div id="itCompanyPendingLevelDivId"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
			<div class="block box_shad m_Top20">
						<div class="row">
							<div class="col-sm-6">
								<h3>DTP Status Properties</h3>
								<p class="m_top5" style="border-top:1px solid grey; width:40%;"></p>
							</div>
						</div>
					<div class="row">
					<div class="col-sm-6">
						<div class="block m_top10" style="border:1px solid #C4D5FF;border-left:10px solid #C4D5FF;border-radius: 5px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-8 m_Top15">
										<h4><b>Government Approved Properties</b></h4> 
									</div>
									<div class="col-sm-4">
										<div class="pull-right">
											<div class="approvedPropertiesCls"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="block m_top10" style="border:1px solid #FF8686;border-left:10px solid #FF8686;border-radius: 5px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-8 m_Top15">
										<h4><b>Available Properties</b></h4> 
									</div>
									<div class="col-sm-4">
										<div class="pull-right">
											<h2 class="availablePropertiesCls"></h2>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-sm-5">
						<div class="block" style="border:1px solid #7BD97B;border-left:10px solid #7BD97B;border-radius: 5px;">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-6 m_Top15" >
										<h4><b>Occupied Properties</b></h4> 
									</div>
									<div class="col-sm-6">
										<div class="" style="background-color:#7BD97B;padding:10px;border-radius: 5px;">
											<h5 class="occupiedPropertiesCls"></h5>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="block" style="border:1px solid #7BD97B;border-left:10px solid #7BD97B;border-radius: 5px;">
							<div class="row">
								<div class="col-sm-7">
									<div class="col-sm-7 m_Top15">
										<h4>Occupied  IT Companies</h4>
									</div>
									<div class="col-sm-4">
										<div class="" style="background-color:#7BD97B;padding:17px;border-radius: 5px; text-align:center">
											<h4 class="occupiedItCompanies"></h4>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				</div>
				<div class="block box_shad ">
				<div class="row m_top10">
					<div class="col-sm-12">
						<div class="block" style ="border-radius: 5px;border-color:#C4D5FF; padding:2px">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-3 "style="margin-top:60px;">
										<h4><b>DTP Status Buildings</b></h4> 
									</div>
									<div class="col-sm-2 "style="margin-top:40px;">
										<div class="well" style="background-color:#C4D5FF;">
											<h4 class="statusBuildingCls">
											</h4>
										</div>
									</div>
									<div class="col-sm-3 m_Top20 " >
										<div class="panel panelHeadColR" style="background-color:#FFEEC5;">
											<div class="panel-heading">
												<div class="row">
													<div class="col-sm-6">
														<h4 class="statusBuildingAvailableCls"></h4>
													</div>
												</div>
											</div>
											<div class="panel-body" >
												<p id="statusAppliedItCompaniesId"></p>
											</div>
										</div>
									</div>
									<div class="col-sm-3 m_Top20" style="">
										<div class="panel panelHeadColG"style="background-color:#8FDE8F;" >
											<div class="panel-heading">
												<div class="row">
													<div class="col-sm-6">
														<h4 class="statusBuildingOccupiedCls"></h4>
													</div>
												</div>
											</div>
											<div class="panel-body" >
												<p id="statusOccupiedItCompaniesId"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="block m_top10" style ="border-radius: 5px;border-color:#C4D5FF;padding:3px">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-3" style="margin-top:60px;">
										<h4><b>Non DTP Status Buildings</b></h4> 
									</div>
									<div class="col-sm-2  " style="margin-top:40px;">
										<div class="well" style="background-color:#C4D5FF;">
											<h4 class="nonStatusBuildingCls">
											</h4>
										</div>
									</div>
									<div class="col-sm-3 m_Top20">
										<div class="panel panelHeadColR" style="background-color:#FFEEC5;">
											<div class="panel-heading">
												<div class="row">
													<div class="col-sm-6">
														<h4 class="nonStatusBuildingAvailableCls"></h4>
													</div>
												</div>
											</div>
											<div class="panel-body" >
												<p id="nonStatusAppliedItCompaniesId"></p>
											</div>
										</div>
									</div>
									<div class="col-sm-3 m_Top20" >
										<div class="panel panelHeadColG"style="background-color:#8FDE8F;" >
											<div class="panel-heading">
												<div class="row">
													<div class="col-sm-6">
														<h4 class="nonStatusBuildingOccupiedCls"></h4>
													</div>
												</div>
											</div>
											<div class="panel-body" >
												<p id="nonStatusOccupiedItCompaniesId"></p>
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
		<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
		<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
		<script src ="Assests/itMinisterDashboard/itMinisterDashboard.js" type = "text/javascript" ></script>
	</body>
</html>


										