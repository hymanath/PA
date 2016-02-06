<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>2016  Registration Drive</title>
<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/dataTables.fixedHeader.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<div class="well wellCustom">
						<img src="dist/2016DashBoard/img/headpart.jpg" class="img-block img-responsive">
						<h4 class="panel-title text-center"><b>DASHBOARD</b></h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="block pad_10">
						<div class="row">
							<div class="col-md-5">
								<div class="row">
									<div class="col-md-6">
										<table class="">
											<tr>
											<td class="pad_10">
												<br/><br/><br/><br/><br/>
												<img src="dist/2016DashBoard/img/online.jpg" style="width:50px;">
											<span class="themeColor" style="font-size:18px">WEB</span>
											</td>
											</tr>
											<tr>
											<td class="pad_10">
												<img src="dist/2016DashBoard/img/mobile.jpg" style="width:50px;">
											<span class="themeColor" style="font-size:18px">TAB</span>
											</td>
											</tr>
										</table>						
									</div>
									<div class="col-md-6">
										<table class="table table-bordered tableCust2">
											<tr><td class="text-center">
												<h4 class="m_bottom0 themeColor">TOTAL</h4>
												<p class="text-italic">Registered Members</p>
											</td></tr>
											<tr><td class="bg_tab text-center" id="totalWebCountId"></td></tr>
											<tr><td class="bg_tab text-center" id="totalTabCountId"></td></tr>
										</table>
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<div class="row">
									<!--<div class="col-md-4">
										<table class="table table-bordered tableCust2">
											<tr><td class="text-center">
												<h4 class="m_bottom0 themeColor">THIS MONTH</h4>
												<p class="text-italic">Registered Members</p>
											</td></tr>
											<tr><td class="bg_tab text-center">5000</td></tr>
											<tr><td class="bg_tab text-center">2000</td></tr>
										</table>
									</div>
									<div class="col-md-4">
										<table class="table table-bordered tableCust2">
											<tr><td class="text-center">
												<h4 class="m_bottom0 themeColor">THIS WEEK</h4>
												<p class="text-italic">Registered Members</p>
											</td></tr>
											<tr><td class="bg_tab text-center">5000</td></tr>
											<tr><td class="bg_tab text-center">2000</td></tr>
										</table>
									</div>-->
									<div class="col-md-4">
										<table class="table table-bordered tableCust2">
											<tr><td class="text-center">
												<h4 class="m_bottom0 themeColor">TODAY</h4>
												<p class="text-italic">Registered Members</p>
											</td></tr>
											<tr><td class="bg_tab text-center" id="todayWebCountId"></td></tr>
											<tr><td class="bg_tab text-center" id="todayTabCountId"></td></tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio">All
							</label>
							<label class="radio-inline">
								<input type="radio">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">zone wise registrations</h4>
							<span class="themeColor font-12">Total Zone - 04</span>
							<span class="themeColor font-12">Started - 02</span>
							<span class="themeColor font-12">Not Started - 02</span>
						</div>
						<table class="table tableCustom">
							<thead class="bg_ee">
								<th>Zone Name</th>
								<th>Total</th>
								<th>Web</th>
								<th>Tab</th>
							</thead>
							<tr>
								<td>NELLORE</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>KADAPA</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>VIJAYAWADA</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>VIZIANAGARAM</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio">All
							</label>
							<label class="radio-inline">
								<input type="radio">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">region wise registrations</h4>
							<span class="themeColor font-12">Total Zone - 04</span>
							<span class="themeColor font-12">Started - 02</span>
							<span class="themeColor font-12">Not Started - 02</span>
						</div>
						<table class="table tableCustom">
							<thead class="bg_ee">
								<th>Region Name</th>
								<th>Total</th>
								<th>Web</th>
								<th>Tab</th>
							</thead>
							<tr>
								<td>NELLORE</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>KADAPA</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>VIJAYAWADA</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>VIZIANAGARAM</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio">All
							</label>
							<label class="radio-inline">
								<input type="radio">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">depot wise registrations</h4>
							<span class="themeColor font-12">Total Zone - 04</span>
							<span class="themeColor font-12">Started - 02</span>
							<span class="themeColor font-12">Not Started - 02</span>
						</div>
						<table class="table tableCustom">
							<thead class="bg_ee">
								<th>Deport Name</th>
								<th>Total</th>
								<th>Web</th>
								<th>Tab</th>
							</thead>
							<tr>
								<td>NELLORE</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>KADAPA</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>VIJAYAWADA</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
							<tr>
								<td>VIZIANAGARAM</td>
								<td>100</td>
								<td>40</td>
								<td>60</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio">All
							</label>
							<label class="radio-inline">
								<input type="radio">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">non operational units</h4>
							<span class="color66 font-12">Registered 80 Members</span>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio">All
							</label>
							<label class="radio-inline">
								<input type="radio">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">other</h4>
							<span class="color66 font-12">Registered 80 Members</span>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<table class="table table-bordered">
							<tr>
								<td class="text-center">
									<h4>ONLINE <br/>USERS</h4>
									<p>50</p>
								</td>
								<td class="text-center">
									<h4>TAB <br/>USERS</h4>
									<p>80</p>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="block m_top10">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio">All
							</label>
							<label class="radio-inline">
								<input type="radio">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-left pad_10">
							<h4 class="text-capitalize m_bottom0">depot wise registrations</h4>
							<span class="themeColor font-12">Total Zone - 04</span>
							<span class="themeColor font-12">Started - 02</span>
							<span class="themeColor font-12">Not Started - 02</span>
						</div>
						<table class="table tableCust1">
							<thead class="bg_ee">
								<th>Depot Name</th>
								<th>Total</th>
								<th>Web</th>
								<th>Tab</th>
							</thead>
							<tbody>
								<tr>
									<td>VIJAYAWADA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
								<tr>
									<td>GOWORPETA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
								<tr>
									<td>GOWORPETA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
								<tr>
									<td colspan="4" class="insideTable arrow_box">
										<div class="pad_10">
											<label class="radio-inline">
												<input type="radio">All
											</label>
											<label class="radio-inline">
												<input type="radio">Today
											</label>
											<span class="b_left">
												<label class="radio-inline">
													<input type="radio">Total
												</label>
												<label class="radio-inline">
													<input type="radio">Web
												</label>
												<label class="radio-inline">
													<input type="radio">Tab
												</label>
											</span>
											<span class="closeIcon">X</span>
										</div>
										<table class="table bShadow">
											<thead class="bg_ee">
												<th></th>
												<th>NAME</th>
												<th>MOBILE NUMBER</th>
												<th>VOTER NUMBER</th>
												<th>EMPLOYE ID</th>
												<th>REGISTERED THROUGH</th>
											</thead>
											<tbody>
												<tr>
													<td><img src="dist/2016DashBoard/img/profileIcon.jpg" class="profileIcon"></td>
													<td>Rajesh</td>
													<td>9889848465</td>
													<td>IXR347589</td>
													<td>31585</td>
													<td>Tab</td>
												</tr>
												<tr>
													<td><img src="dist/2016DashBoard/img/profileIcon.jpg" class="profileIcon"></td>
													<td>Rajesh</td>
													<td>9889848465</td>
													<td>IXR347589</td>
													<td>31585</td>
													<td>Web</td>
												</tr>
												<tr>
													<td><img src="dist/2016DashBoard/img/profileIcon.jpg" class="profileIcon"></td>
													<td>Rajesh</td>
													<td>9889848465</td>
													<td>IXR347589</td>
													<td>31585</td>
													<td>Tab</td>
												</tr>
												<tr>
													<td><img src="dist/2016DashBoard/img/profileIcon.jpg" class="profileIcon"></td>
													<td>Rajesh</td>
													<td>9889848465</td>
													<td>IXR347589</td>
													<td>31585</td>
													<td>Web</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td>GOWORPETA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
								<tr>
									<td>GOWORPETA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
								<tr>
									<td>GOWORPETA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
								<tr>
									<td>GOWORPETA</td>
									<td>100</td>
									<td>40</td>
									<td>60</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="dist/2016DashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/dataTables.fixedHeader.js" type="text/javascript"></script>

<script>
$(".Dattable").dataTable({
		"paging":   false,
        "info":     false,
		"searching": false,
		//"scrollY":   "100px",
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		fixedHeader: true
})




getRtcUnionRegisteredBasicDetails();
getRtcUnionAllLocationDetails();
getRtcUnionZoneWiseDetails();
function getRtcUnionRegisteredBasicDetails(){
	$("#dataLoadingsImgForOverAllDetails").show();
	var jObj={
		task : "basicDetails"
	};
	$.ajax({
		type:"POST",
		url:"getRtcUnionRegisteredBasicDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		if(result != null){
			$("#totalWebCountId").html(result.webCount);
			if(result.tabCount == null)result.tabCount=0;
			$("#totalTabCountId").html(result.tabCount);
			$("#todayWebCountId").html(result.todayWebCount);
			if(result.todayTabCount == null)result.todayTabCount=0;
			$("#todayTabCountId").html(result.todayTabCount);
		}
	});
}
function getRtcUnionZoneWiseDetails(){
	$("#dataLoadingsImgForRegionalWiseDetails").show();
	var jObj={
		task : "zoneDetails"
	};
	$.ajax({
		type:"POST",
		url:"getRtcUnionRegisteredBasicDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		
	});
	
}
function getRtcUnionAllLocationDetails(){
	$("#dataLoadingsImgForZoneWiseDetails").show();
	$.ajax({
		type:"POST",
		url:"getRtcUnionAllLocationDetailsAction.action",
		dataType: 'json',
		data:{}	
	}).done(function(result) {
		
	});
}

function getRtcUnionLocationWiseDetails(){
	var jObj={
		task:"depot",
		locationId:0
	};
	$.ajax({
		type:"POST",
		url:"getRtcUnionLocationWiseDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		
	});
}

function getAffiliatedCadreDetails(){
	var jObj={
		type:"depot",
		searchType:"",
		locationId:0
	};
	$.ajax({
		type:"POST",
		url:"getAffiliatedCadreDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		
	});
}


</script>
</body>
</html>