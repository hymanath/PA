<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/less" href="Assests/less/bootstrap.less" />
		<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
		<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
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
			.m_Top10
			{
				margin-top:10px;
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
								<h3 class="borderBottom">Overall Overview</h3>
							</div>
						</div>
						<div class="row m_Top10">
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
										<div class="block m_Top10" style="background-color:#CCB064;">
											<div class="row">
												<div class="cols-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>REGISTERED COMPANIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" >
														<h2 class="white_color" id="devloperRegCompanyId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #CCB064;border-left:7px solid #CCB064;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>REGISTRED PROPERTIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" style="background-color:#CCB064;">
														<h2 class="white_color" id="devloperRegPropertiesId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #17AD17;border-left:7px solid #17AD17;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>APPROVED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#17AD17;">
														<h2 class="white_color" id="devloper3"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #FF0000;border-left:7px solid #FF0000;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>REJECTED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#FF0000;">
														<h2 class="white_color" id="devloper4"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #FFBA00;border-left:7px solid #FFBA00;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>IN-PROGRESS</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#FFBA00;">
														<h2 class="white_color" id="devloper2"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #FF6E00;border-left:7px solid #FF6E00;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>PENDING/NOT POSSIBLE</b></h5> 
													</div>
													<div class="col-sm-2 pull-right devloperCls" style="background-color:#FF6E00;">
														<h2 class="white_color" id="devloper1"></h2>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							<div class="col-sm-6">
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
										<div class="block m_Top10" style="background-color:#CCB064;">
											<div class="row">
												<div class="cols-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>REGISTERED COMPANIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" >
														<h2 class="white_color" id="regItCompanyId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #CCB064;border-left:7px solid #CCB064;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>REGISTRED PROPERTIES</b></h5> 
													</div>
													<div class="col-sm-2 pull-right" style="background-color:#CCB064;">
														<h2 class="white_color"id="regCompanyPropertiesId"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #17AD17;border-left:7px solid #17AD17;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>APPROVED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#17AD17;">
														<h2 class="white_color" id="company3"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #FF0000;border-left:7px solid #FF0000;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>REJECTED</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#FF0000;">
														<h2 class="white_color" id="company4"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #FFBA00;border-left:7px solid #FFBA00;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>IN-PROGRESS</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#FFBA00;">
														<h2 class="white_color" id="company2"></h2>
													</div>
												</div>
											</div>
										</div>
										<div class="block m_Top10" style="border:1px solid #FF6E00;border-left:7px solid #FF6E00;">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-8 m_Top10">
														<h5><b>PENDING/NOT POSSIBLE</b></h5> 
													</div>
													<div class="col-sm-2 pull-right companyCls" style="background-color:#FF6E00;">
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
				<div class="row m_top10">
							<div class="col-sm-6">
								<h3 class="borderBottom">Government Approval Process Flow</h3>
							</div>
						</div>
						<div class="row m_top5">
							<div class="col-sm-12">
								<div class="m_top10" style="border:1px solid #e3e3e3; padding:10px; border-radius:5px; background-color: #FFFFFF;">
									<div class="row " >
										<div class="col-sm-3">
											<div class="pad_5" style="border-right:1px solid grey; height: 56px;">
												<h6 class="font_weight">Pending@</h6>
											</div>
										</div>
										<div class="col-sm-9">
											<div class="row">
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding:10px;">
														<h6 class="font_weight">APPLICANT</h6>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;">
														<h6 class="font_weight">STAFF</h6>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey;height: 56px;">
														<h6 class="font_weight">CEO - INFRA / JD</h6>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey;height: 56px;">
														<h6 class="font_weight">TECHNICAL TEAM / LEGAL TEAM</h6>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;">
														<h6 class="font_weight">CCITI</h6>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20">
														<h6 class="font_weight">HMIT OFFICE</h6>
													</div>
												</div>
											</div>	
										</div>
									</div>
								</div>	
								<div class="m_top5" style="border:1px solid #e3e3e3; padding:10px; border-radius:5px; background-color: #FFFFFF;">
									<div class="row m_top5 ">
										<div class="col-sm-3">
											<div class="pad_5" style="border-right:1px solid grey; height: 56px;">
												<div class="row" >
													<div class="col-sm-6">
														<h5 class="font_weight m_Top20">Developer</h5>
													</div>
													<div class="col-sm-4">
														<h4 class="font_weight well-sm" style="background-color: #CDDAEF; ">300</h4>
													</div>
													
												</div>		
											</div>
										</div>
										<div class="col-sm-9  approvalDevloperCls">
											<div class="row">
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">	
															<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;" id="devloperId1">
															</h4>
														</div>	
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;" id="devloperId3">
															</h4>
														</div>	
													</div>
												</div>
												<div class="col-sm-2" >
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">	
															<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;" id="devloperId7">
															</h4>
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;" id="devloperId4">
															</h4>
														</div>
													</div>
												</div>
												<div class="col-sm-2" >
													<div class="pad_5 " style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;" id="devloperId6">
															</h4>
														</div>	
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5">
														<div class="col-sm-10">
															<h4 class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;" id="devloperId8">
															</h4>
														</div>
													</div>
												</div>
											</div>	
										</div>
									</div>
								</div>
								<div class="m_top5" style="border:1px solid #e3e3e3; padding:10px; border-radius:5px; background-color: #FFFFFF;">
									<div class="row">
										<div class="col-sm-3">
											<div class="pad_5" style="border-right:1px solid grey; height: 56px;">
												<div class="row">
													<div class="col-sm-6">
														<h5 class="font_weight m_Top20">IT Company</h5>
													</div>
													<div class="col-sm-4">
														<h4 class="font_weight well-sm" style="background-color: #CDDAEF; ">300</h4>
													</div>
												</div>		
											</div>
										</div>
										<div class="col-sm-9 approvalItCompanyCls">
											<div class="row">
												<div class="col-sm-2" >
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 id="itCompanyId9" class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">
															</h4>
														</div>
													</div>
												</div>
												<div class="col-sm-2" >
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 id="itCompanyId10" class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">
															</h4>
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 id="itCompanyId5"class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">
															
															</h4>
														</div>
													</div>
												</div>
												<div class="col-sm-2" >
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4  id="itCompanyId0"class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">
															</h4>
														</div>	
													</div>
												</div>
												<div class="col-sm-2">
													<div class="pad_5 m_left-20" style="border-right:1px solid grey; height: 56px;padding-right:10px;">
														<div class="col-sm-10">
															<h4 id="itCompanyId11"class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">
															</h4>
														</div >
													</div>
												</div>
												<div class="col-sm-2">
													<div class="">
														<div class="col-sm-10">
															<h4 id="itCompanyId12"class="font_weight well-sm" style="background-color: #FDF0C7; border-radius: 5px;">
															</h4>
														</div>
													</div>
												</div>
											</div>	
										</div>
									</div>
								</div>
								
								
								
							</div>
						</div>
			<div class="block box_shad m_Top10">
						<div class="row">
							<div class="col-sm-6">
								<h3 class="borderBottom">DTP Status Properties</h3>
							</div>
						</div>
					<div class="row">
					<div class="col-sm-6">
						<div class="block m_Top10" style="border:1px solid #C4D5FF;border-left:10px solid #C4D5FF;">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-8 m_Top10">
										<h4><b>Government Approved Properties</b></h4> 
									</div>
									<div class="col-sm-4">
										<div class="pull-right">
											<h2 class="approvedPropertiesCls"></h2>
											<p style="font-size:14px;" class = "approvedPropertiesSumCls"></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="block" style="border:1px solid #FF8686;border-left:10px solid #FF8686;">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-8 m_Top10">
										<h4><b>Available Properties</b></h4> 
									</div>
									<div class="col-sm-4">
										<div class="pull-right">
											<h2 class="availablePropertiesCls"></h2>
											<p style="font-size:14px;" class="availablePropertiesSumCls"></p>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="row m_Top10">
					<div class="col-sm-4">
						<div class="block" style="border:1px solid #7BD97B;border-left:10px solid #7BD97B;">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-4 m_Top10">
										<h5><b>Occupied Properties</b></h5> 
									</div>
									<div class="col-sm-6">
										<div class="" style="background-color:#7BD97B;padding:10px;">
											<h5 class="occupiedPropertiesCls"></h5>
												<p align="right" style="font-size:14px;" class="occupiedPropertiesSumCls" >
												</p>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="block" style="border:1px solid #7BD97B;border-left:10px solid #7BD97B;">
							<div class="row">
								<div class="col-sm-6">
									<div class="col-sm-5">
										<h5>Occupied  IT Companies</h5>
									</div>
									<div class="col-sm-7">
										<div class="" style="background-color:#7BD97B;padding:17px;">
											<h4 class="occupiedItCompanies"></h4>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="row m_Top10">
					<div class="col-sm-12">
						<div class="block">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-3 m_Top10">
										<h4><b>DTP Status Buildings</b></h4> 
									</div>
									<div class="col-sm-2 ">
										<div class="well" style="background-color:#C4D5FF;">
											<h4 class="statusBuildingCls">
											</h4>
										</div>
									</div>
									<div class="col-sm-3" style="">
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
									<div class="col-sm-3" style="">
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
						<div class="block m_Top10">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-3 m_Top10">
										<h4><b>Non DTP Status Buildings</b></h4> 
									</div>
									<div class="col-sm-2 ">
										<div class="well" style="background-color:#C4D5FF;">
											<h4 class="nonStatusBuildingCls">
											</h4>
										</div>
									</div>
									<div class="col-sm-3" style="">
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
									<div class="col-sm-3" style="">
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
		<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
		<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
		<script src ="Assests/itMinisterDashboard/itMinisterDashboard.js" type = "text/javascript" ></script>
	</body>
</html>