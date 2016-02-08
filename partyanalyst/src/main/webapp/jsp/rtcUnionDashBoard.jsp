<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>2016 Affiliated DashBoard</title>
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
							<div class="col-md-5 col-md-offset-1">
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
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-5">
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
								<input type="radio" class="zoneClass" checked="true" name="zoneRdi" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="zoneClass" name="zoneRdi" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">zone wise registrations</h4>
							<span class="themeColor font-12" id="zoneTotalId"></span>
							<span class="themeColor font-12" id="zoneStartedId"></span>
							<span class="themeColor font-12" id="zoneNotStartedId"></span>
							<span class="themeColor font-12" id="todayZoneTotalId" style="display:none"></span>
							<span class="themeColor font-12" id="todayZoneStartedId" style="display:none"></span>
							<span class="themeColor font-12" id="todayZoneNotStartedId" style="display:none"></span>
						</div>
						<div id="zoneWiseTotalDetailsDiv" style="display:none;"></div>
						<div id="zoneWiseTodayDetailsDiv" style="display:none;"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="regionClass" checked="true" name="regionRdi" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="regionClass" name="regionRdi" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">region wise registrations</h4>
							<span class="themeColor font-12" id="totalReionsId"></span>
							<span class="themeColor font-12" id="startedRegionsId"></span>
							<span class="themeColor font-12" id="nonStartedRegionsId"></span>
							<span class="themeColor font-12" id="todayTotalReionsId" style="display:none"></span>
							<span class="themeColor font-12" id="todayStartedRegionsId" style="display:none"></span>
							<span class="themeColor font-12" id="todayNonStartedRegionsId" style="display:none"></span>
						</div>
						<div id="regionWiseTotalDetailsDiv" style="display:none;"></div>
						<div id="regionWiseTodayDetailsDiv" style="display:none;"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="depotRadioCls" checked="true" name="depotRadio" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="depotRadioCls" name="depotRadio" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">depot wise registrations</h4>
							<span class="themeColor font-12" id="totlDepotId"></span>
							<span class="themeColor font-12" id="startedDepotId"></span>
							<span class="themeColor font-12" id="notStartedDepotId"></span>
							<span class="themeColor font-12" id="todayTotlDepotId" style="display:none"></span>
							<span class="themeColor font-12" id="todayStartedDepotId" style="display:none"></span>
							<span class="themeColor font-12" id="todayNotStartedDepotId" style="display:none"></span>
						</div>
						<div id="depotWiseTotalDetailsDiv" style="display:none;"></div>
						<div id="depotWiseTodayDetailsDiv" style="display:none;"></div>
					</div>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="nonOpernationalCls" name="nonOpertional" checked="true" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="nonOpernationalCls" name="nonOpertional" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">non operational units</h4>
							<span class="color66 font-12" id="nonOperationalId"></span>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="othersCls" name="others" checked="true" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="othersCls" name="others" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-center pad_10">
							<h4 class="text-capitalize m_bottom0">other</h4>
							<span class="color66 font-12" id="othersId"></span>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div id="onlineAndTabUsersDiv"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="block m_top10">
						<div class="pad_10">
							<label class="radio-inline">
								<input type="radio" class="depotClass" checked="true" name="depot" value="All">All
							</label>
							<label class="radio-inline">
								<input type="radio" class="depotClass" name="depot" value="Today">Today
							</label>
							<span class="pull-right">
								<img src="dist/2016DashBoard/img/icon.jpg" class="CursorH">
							</span>
						</div>
						<div class="text-left pad_10">
							<h4 class="text-capitalize m_bottom0">depot wise registrations</h4>
							<span class="themeColor font-12" id="depotTotalId"></span>
							<span class="themeColor font-12" id="depotStartedId"></span>
							<span class="themeColor font-12" id="depotNotStartedId"></span>
							<span class="themeColor font-12" id="todayDepotTotalId" style="display:none"></span>
							<span class="themeColor font-12" id="todayDepotStartedId" style="display:none"></span>
							<span class="themeColor font-12" id="todayDepotNotStartedId" style="display:none"></span>
						</div>
						<div id="depotWiseTotalDetailsId" style="display:none;"></div>
						<div id="depotWiseTodayDetailsId" style="display:none;"></div>
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
getRtcUnionZoneWiseTotalDetails();
getRtcUnionTotalLocationWiseDetails();
//getRtcUnionTotalLocationWiseDetails(2);
getOnlineAndTabUsersCount();

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
		if(result.webCount == null)result.webCount=0;
		$("#totalWebCountId").html(result.webCount);
		if(result.tabCount == null)result.tabCount=0;
		$("#totalTabCountId").html(result.tabCount);
		if(result.todayWebCount == null)result.todayWebCount=0;
		$("#todayWebCountId").html(result.todayWebCount);
		if(result.todayTabCount == null)result.todayTabCount=0;
		$("#todayTabCountId").html(result.todayTabCount);
	}
});
}

function getRtcUnionZoneWiseTotalDetails(){
	$("#todayZoneTotalId,#todayZoneStartedId,#todayZoneNotStartedId").hide();
	$("#zoneTotalId,#zoneStartedId,#zoneNotStartedId").show();
	$("#zoneWiseTotalDetailsDiv").html("");
	$("#zoneWiseTotalDetailsDiv").show();
	$("#zoneWiseTodayDetailsDiv").hide();
  var jObj={
    task : "zoneDetails"
  };
  $.ajax({
    type:"POST",
    url:"getRtcUnionRegisteredBasicDetailsAction.action",
    dataType: 'json',
    data:{task:JSON.stringify(jObj)}  
  }).done(function(result) {
    if(result != null){
	var totalZones = 0;
	var startedZones = 0;
	var notStartedZones = 0;
	var todayTotalZones = 0;
	var todayStartedZones = 0;
	var todayNotStartedZones = 0;
      var str='';
      str+='<table class="table tableCustom">';
        str+='<thead class="bg_ee">';
          str+='<th>Zone Name</th>';
          str+='<th>Total</th>';
          str+='<th>Web</th>';
          str+='<th>Tab</th>';
        str+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
			if(i==0){
				if(result.rtcUnionVoList1[i].totalDataCount != null)
					totalZones = result.rtcUnionVoList1[i].totalDataCount;
				if(result.rtcUnionVoList1[i].startedCount != null)
					startedZones = result.rtcUnionVoList1[i].startedCount;
				notStartedZones = parseInt(totalZones)-parseInt(startedZones);
			}
          str+='<tr>';
            str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
          str+='</tr>';
        }
      }
      str+='</table>';
	  $("#zoneTotalId").html("Total Zone - "+totalZones);
	  $("#zoneStartedId").html("Started - "+startedZones);
	  $("#zoneNotStartedId").html("Not Started - "+notStartedZones);
	  
      $("#zoneWiseTotalDetailsDiv").html(str);
	  
	  var str1='';
      str1+='<table class="table tableCustom">';
        str1+='<thead class="bg_ee">';
          str1+='<th>Zone Name</th>';
          str1+='<th>Total</th>';
          str1+='<th>Web</th>';
          str1+='<th>Tab</th>';
        str1+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
		if(i==0){
			if(result.rtcUnionVoList1[i].toDayTotalDataCount != null)
				todayTotalZones = result.rtcUnionVoList1[i].toDayTotalDataCount;
			if(result.rtcUnionVoList1[i].toDayStartedCount != null)
				todayStartedZones = result.rtcUnionVoList1[i].toDayStartedCount;
			todayNotStartedZones = parseInt(todayTotalZones)-parseInt(todayStartedZones);
		}
          str1+='<tr>';
            str1+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
          str1+='</tr>';
        }
      }
      str1+='</table>';
	  $("#todayZoneTotalId").html("Total Zone - "+todayTotalZones);
	  $("#todayZoneStartedId").html("Started - "+todayStartedZones);
	  $("#todayZoneNotStartedId").html("Not Started - "+todayNotStartedZones);
      $("#zoneWiseTodayDetailsDiv").html(str1);
    }
  });
}

function getRtcUnionAllLocationDetails(){
	$("#todayTotalReionsId,#todayStartedRegionsId,#todayNonStartedRegionsId").hide();
	$("#totalReionsId,#startedRegionsId,#nonStartedRegionsId").show();
	$("#regionWiseTotalDetailsDiv").html("");
	$("#regionWiseTodayDetailsDiv").hide();
	$("#regionWiseTotalDetailsDiv").show();
  $.ajax({
    type:"POST",
    url:"getRtcUnionAllLocationDetailsAction.action",
    dataType: 'json',
    data:{}  
  }).done(function(result) {
    if(result != null){
	var totalRegions = 0;
	var startedRegions = 0;
	var notStartedRegions = 0;
	var todayTotalRegions = 0;
	var todayStartedRegions = 0;
	var todayNotStartedRegions = 0;
      var str='';
      str+='<table class="table tableCustom">';
        str+='<thead class="bg_ee">';
          str+='<th>Region Name</th>';
          str+='<th>Total</th>';
          str+='<th>Web</th>';
          str+='<th>Tab</th>';
        str+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
		if(i==0){
			if(result.rtcUnionVoList1[i].totalDataCount != null)
				totalRegions = result.rtcUnionVoList1[i].totalDataCount;
			if(result.rtcUnionVoList1[i].startedCount != null)
				startedRegions = result.rtcUnionVoList1[i].startedCount;
			notStartedRegions = parseInt(totalRegions)-parseInt(startedRegions);
		}
          str+='<tr>';
            str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
            str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
          str+='</tr>';
        }
      }
      str+='</table>';
	  $("#totalReionsId").html("Total Region - "+totalRegions);
	  $("#startedRegionsId").html("Started - "+startedRegions);
	  $("#nonStartedRegionsId").html("Not Started - "+notStartedRegions);
      $("#regionWiseTotalDetailsDiv").html(str);
	  
	  var str1='';
      str1+='<table class="table tableCustom">';
        str1+='<thead class="bg_ee">';
          str1+='<th>Region Name</th>';
          str1+='<th>Total</th>';
          str1+='<th>Web</th>';
          str1+='<th>Tab</th>';
        str1+='</thead>';
      if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
        for(var i in result.rtcUnionVoList1){
		if(i==0){
			if(result.rtcUnionVoList1[i].toDayTotalDataCount != null)
				todayTotalRegions = result.rtcUnionVoList1[i].toDayTotalDataCount;
			if(result.rtcUnionVoList1[i].toDayStartedCount != null)
				todayStartedRegions = result.rtcUnionVoList1[i].toDayStartedCount;
			todayNotStartedRegions = parseInt(todayTotalRegions)-parseInt(todayStartedRegions);
		}
          str1+='<tr>';
            str1+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
            str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
			str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
          str1+='</tr>';
        }
      }
      str1+='</table>';
	  $("#todayTotalReionsId").html("Total Region - "+todayTotalRegions);
	  $("#todayStartedRegionsId").html("Started - "+todayStartedRegions);
	  $("#todayNonStartedRegionsId").html("Not Started - "+todayNotStartedRegions);
      $("#regionWiseTodayDetailsDiv").html(str1);
    }
  });
}

var nonOperationalTotalCount = 0;
var nonOperationalTodayCount = 0;
var othersTotalCount = 0;
var othersTodayCount = 0;

function getRtcUnionTotalLocationWiseDetails(){
$("#todayTotlDepotId,#todayStartedDepotId,#todayNotStartedDepotId").hide();
$("#totlDepotId,#startedDepotId,#notStartedDepotId").show();
$("#todayDepotTotalId,#todayDepotStartedId,#todayDepotNotStartedId").hide();
$("#depotTotalId,#depotStartedId,#depotNotStartedId").show();

$("#depotWiseTotalDetailsDiv").html("");
$("#depotWiseTodayDetailsDiv").hide();
$("#depotWiseTotalDetailsDiv").show();

$("#depotWiseTotalDetailsId").html("");
$("#depotWiseTodayDetailsId").hide();
$("#depotWiseTotalDetailsId").show();
	
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
	if(result != null){
		buildDepotWiseDetails1(result);
		buildDepotWiseDetails2(result);
	}
});
}

function buildDepotWiseDetails1(result){
	var totalDepots = 0;
	var startedDepots = 0;
	var notStartedDepots = 0;
	var todayTotalDepots = 0;
	var todayStartedDepots = 0;
	var todayNotStartedDepots = 0;
	
	var str='';
		str+='<table class="table tableCustom">';
			str+='<thead class="bg_ee">';
				str+='<th>Deport Name</th>';
				str+='<th>Total</th>';
				str+='<th>Web</th>';
				str+='<th>Tab</th>';
			str+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
			if(i==0){
				if(result.rtcUnionVoList1[i].totalDataCount != null)
					totalDepots = result.rtcUnionVoList1[i].totalDataCount;
				if(result.rtcUnionVoList1[i].startedCount != null)
					startedDepots = result.rtcUnionVoList1[i].startedCount;
				notStartedDepots = parseInt(totalDepots)-parseInt(startedDepots);
			}
				str+='<tr>';
					str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
					
					if(result.rtcUnionVoList1[i].name == "Non_Operational" && result.rtcUnionVoList1[i].totalCount != null)
						nonOperationalTotalCount = result.rtcUnionVoList1[i].totalCount;
					if(result.rtcUnionVoList1[i].name == "Others" && result.rtcUnionVoList1[i].totalCount != null)
						othersTotalCount = result.rtcUnionVoList1[i].totalCount;
						
					str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
				str+='</tr>';
			}
		}
		str+='</table>';
		$("#totlDepotId").html("Total Depot - "+totalDepots);
		$("#startedDepotId").html("Started - "+startedDepots);
		$("#notStartedDepotId").html("Not Started - "+notStartedDepots);
		$("#depotTotalId").html("Total Depot - "+totalDepots);
		$("#depotStartedId").html("Started - "+startedDepots);
		$("#depotNotStartedId").html("Not Started - "+notStartedDepots);
		$("#depotWiseTotalDetailsDiv").html(str);
			
		var str1='';
		str1+='<table class="table tableCustom">';
			str1+='<thead class="bg_ee">';
				str1+='<th>Deport Name</th>';
				str1+='<th>Total</th>';
				str1+='<th>Web</th>';
				str1+='<th>Tab</th>';
			str1+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
			if(i==0){
				if(result.rtcUnionVoList1[i].toDayTotalDataCount != null)
					todayTotalDepots = result.rtcUnionVoList1[i].toDayTotalDataCount;
				if(result.rtcUnionVoList1[i].toDayStartedCount != null)
					todayStartedDepots = result.rtcUnionVoList1[i].toDayStartedCount;
				todayNotStartedDepots = parseInt(todayTotalDepots)-parseInt(todayStartedDepots);
			}
				str1+='<tr>';
					str1+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
					
					if(result.rtcUnionVoList1[i].name == "Non_Operational" && result.rtcUnionVoList1[i].todayTotalCount != null)
						nonOperationalTodayCount = result.rtcUnionVoList1[i].todayTotalCount;
					if(result.rtcUnionVoList1[i].name == "Others" && result.rtcUnionVoList1[i].todayTotalCount != null)
						othersTodayCount = result.rtcUnionVoList1[i].todayTotalCount;
					
					str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
				str1+='</tr>';
			}
		}
		str1+='</table>';
		$("#todayTotlDepotId").html("Total Depot - "+todayTotalDepots);
	    $("#todayStartedDepotId").html("Started - "+todayStartedDepots);
	    $("#todayNotStartedDepotId").html("Not Started - "+todayNotStartedDepots);
	    $("#todayDepotTotalId").html("Total Zone - "+todayTotalDepots);
	    $("#todayDepotStartedId").html("Started - "+todayStartedDepots);
	    $("#todayDepotNotStartedId").html("Not Started - "+todayNotStartedDepots);
		$("#depotWiseTodayDetailsDiv").html(str1);
			
		$("#nonOperationalId").html("Registered "+nonOperationalTotalCount+" Members");
		$("#othersId").html("Registered "+othersTotalCount+" Members");
}

function buildDepotWiseDetails2(result){
	var str='';
		str+='<table class="table tableCust1">';
			str+='<thead class="bg_ee">';
				str+='<th>Deport Name</th>';
				str+='<th>Total</th>';
				str+='<th>Web</th>';
				str+='<th>Tab</th>';
			str+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
				str+='<tr>';
					str+='<td onclick="getAffiliatedCadreDetails('+result.rtcUnionVoList1[i].id+',\'depotDetailsId'+i+'\',\'depotTrId'+i+'\',\'All\');">'+result.rtcUnionVoList1[i].name+'</td>';
						
					str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
					str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
				str+='</tr>';
				str+='<tr class="depotTrCls" id="depotTrId'+i+'" style="display:none;"><td colspan="4" class="insideTable arrow_box"><div id="depotDetailsId'+i+'"></div></td></tr>';
			}
		}
		str+='</table>';
		$("#depotWiseTotalDetailsId").html(str);
			
		var str1='';
		str1+='<table class="table tableCust1">';
			str1+='<thead class="bg_ee">';
				str1+='<th>Deport Name</th>';
				str1+='<th>Total</th>';
				str1+='<th>Web</th>';
				str1+='<th>Tab</th>';
			str1+='</thead>';
		if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
			for(var i in result.rtcUnionVoList1){
				str1+='<tr>';
					str1+='<td onclick="getAffiliatedCadreDetails('+result.rtcUnionVoList1[i].id+',\'depotDetailsTdyId'+i+'\',\'depotTrTdyId'+i+'\',\'All\');">'+result.rtcUnionVoList1[i].name+'</td>';
					
					str1+='<td>'+result.rtcUnionVoList1[i].todayTotalCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayWebCount+'</td>';
					str1+='<td>'+result.rtcUnionVoList1[i].todayTabCount+'</td>';
				str1+='</tr>';
				str1+='<tr class="depotTrTdyCls" id="depotTrTdyId'+i+'" style="display:none;"><td colspan="4" class="insideTable arrow_box"><div id="depotDetailsTdyId'+i+'"></div></td></tr>';
			}
		}
		str1+='</table>';
	$("#depotWiseTodayDetailsId").html(str1);
}

function getAffiliatedCadreDetails(id,divId,trId,type){
$("#"+divId).html("");
$(".depotTrCls").hide();
$(".depotTrTdyCls").hide();
$("#"+trId).show();
var jObj={
	type:"depot",
	searchType:type,
	locationId:id
};
$.ajax({
	type:"POST",
	url:"getAffiliatedCadreDetailsAction.action",
	dataType: 'json',
	data:{task:JSON.stringify(jObj)}	
}).done(function(result) {
	if(result != null){
		var str='';
		
			str+='<div class="pad_10">';
				str+='<label class="radio-inline">';
					str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" checked="true" name="details" value="All">All';
				str+='</label>';
				str+='<label class="radio-inline">';
					str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" name="details" value="today">Today';
				str+='</label>';
				str+='<span class="b_left">';
					str+='<label class="radio-inline">';
						str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" name="details" value="web">Web';
					str+='</label>';
					str+='<label class="radio-inline">';
						str+='<input type="radio" class="depoRdoCls" attr_divId="'+divId+'" attr_id="'+id+'" attr_trId="'+trId+'" name="details" value="tab">Tab';
					str+='</label>';
				str+='</span>';
				str+='<span class="closeIcon" attr_divId="'+trId+'">X</span>';
			str+='</div>';
			str+='<table class="table bShadow">';
				str+='<thead class="bg_ee">';
					str+='<th></th>';
					str+='<th>NAME</th>';
					str+='<th>MOBILE NUMBER</th>';
					str+='<th>VOTER NUMBER</th>';
					str+='<th>EMPLOYE ID</th>';
					str+='<th>REGISTERED THROUGH</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td><img src="dist/2016DashBoard/img/profileIcon.jpg" class="profileIcon"></td>';
						str+='<td>'+result[i].firstName+'</td>';
						str+='<td>'+result[i].mobileNo+'</td>';
						str+='<td>'+result[i].voterCardId+'</td>';
						str+='<td>'+result[i].idCardNo+'</td>';
						str+='<td>'+result[i].dataSourceType+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		
		$("#"+divId).html(str);
	}
});
}

$(document).on("click",".depotRadioCls",function() {
var value = $(this).val();
if(value == "All"){
	$("#todayTotlDepotId,#todayStartedDepotId,#todayNotStartedDepotId").hide();
	$("#totlDepotId,#startedDepotId,#notStartedDepotId").show();
	$("#depotWiseTotalDetailsDiv").html("");
	$("#depotWiseTodayDetailsDiv").hide();
	$("#depotWiseTotalDetailsDiv").show();
}
else if(value == "Today"){
	$("#todayTotlDepotId,#todayStartedDepotId,#todayNotStartedDepotId").show();
	$("#totlDepotId,#startedDepotId,#notStartedDepotId").hide();
	$("#depotWiseTodayDetailsDiv").html("");
	$("#depotWiseTotalDetailsDiv").hide();
	$("#depotWiseTodayDetailsDiv").show();
}
});

$(document).on("click",".depotClass",function() {
var value = $(this).val();
if(value == "All"){
	$("#todayDepotTotalId,#todayDepotStartedId,#todayDepotNotStartedId").hide();
	$("#depotTotalId,#depotStartedId,#depotNotStartedId").show();
	$("#depotWiseTotalDetailsId").html("");
	$("#depotWiseTodayDetailsId").hide();
	$("#depotWiseTotalDetailsId").show();
}
else if(value == "Today"){
	$("#todayDepotTotalId,#todayDepotStartedId,#todayDepotNotStartedId").show();
	$("#depotTotalId,#depotStartedId,#depotNotStartedId").hide();
	$("#depotWiseTodayDetailsId").html("");
	$("#depotWiseTotalDetailsId").hide();
	$("#depotWiseTodayDetailsId").show();
}
});

$(document).on("click",".depoRdoCls",function() {
var value = $(this).val();
var divId = $(this).attr("attr_divId");
var id = $(this).attr("attr_id");
var trId = $(this).attr("attr_trId");
getAffiliatedCadreDetails(id,divId,trId,value);
});

$(document).on("click",".closeIcon",function() {
var divId = $(this).attr("attr_divId");
$("#"+divId).hide();
});

$(document).on("click",".zoneClass",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#todayZoneTotalId,#todayZoneStartedId,#todayZoneNotStartedId").hide();
	$("#zoneTotalId,#zoneStartedId,#zoneNotStartedId").show();
	$("#zoneWiseTotalDetailsDiv").html("");
	$("#zoneWiseTotalDetailsDiv").show();
	$("#zoneWiseTodayDetailsDiv").hide();
  }
  else if(value == "Today"){
	$("#todayZoneTotalId,#todayZoneStartedId,#todayZoneNotStartedId").show();
	$("#zoneTotalId,#zoneStartedId,#zoneNotStartedId").hide();
	$("#zoneWiseTodayDetailsDiv").html("");
	$("#zoneWiseTotalDetailsDiv").hide();
	$("#zoneWiseTodayDetailsDiv").show();
  }
});
$(document).on("click",".regionClass",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#todayTotalReionsId,#todayStartedRegionsId,#todayNonStartedRegionsId").hide();
	$("#totalReionsId,#startedRegionsId,#nonStartedRegionsId").show();
	$("#regionWiseTotalDetailsDiv").html("");
	$("#regionWiseTodayDetailsDiv").hide();
	$("#regionWiseTotalDetailsDiv").show();
  }
  else if(value == "Today"){
	$("#todayTotalReionsId,#todayStartedRegionsId,#todayNonStartedRegionsId").show();
	$("#totalReionsId,#startedRegionsId,#nonStartedRegionsId").hide();
	$("#regionWiseTodayDetailsDiv").html("");
	$("#regionWiseTotalDetailsDiv").hide();
	$("#regionWiseTodayDetailsDiv").show();
  }
});

$(document).on("click",".nonOpernationalCls",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#nonOperationalId").html("");
	$("#nonOperationalId").html("Registered "+nonOperationalTotalCount+" Members");
  }
  else if(value == "Today"){
	$("#nonOperationalId").html("");
	$("#nonOperationalId").html("Registered "+nonOperationalTodayCount+" Members");
  }
});

$(document).on("click",".othersCls",function() {
  var value = $(this).val();
  if(value == "All"){
	$("#othersId").html("");
	$("#othersId").html("Registered "+othersTotalCount+" Members");
  }
  else if(value == "Today"){
	$("#othersId").html("");
	$("#othersId").html("Registered "+othersTodayCount+" Members");
  }
});

function getOnlineAndTabUsersCount(){
	var jObj={};
	$.ajax({
		type:"POST",
		url:"getTodayTabAndWebUsersCountAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		if(result != null){
			var str='';
			
			str+='<div class="block">';
				str+='<table class="table table-bordered">';
					str+='<tr>';
						str+='<td class="text-center">';
							str+='<h4>WEB <br/>USERS</h4>';
							if(result.webCount != null)
								str+='<p>'+result.webCount+'</p>';
							else
								str+='<p> 0 </p>';
						str+='</td>';
						str+='<td class="text-center">';
							str+='<h4>TAB <br/>USERS</h4>';
							if(result.tabCount != null)
								str+='<p>'+result.tabCount+'</p>';
							else
								str+='<p> 0 </p>';
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			
			$("#onlineAndTabUsersDiv").html(str);
		}
	});
}
</script>
</body>
</html>