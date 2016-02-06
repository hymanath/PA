<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>RTC UNION DASHBOARD</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
.m_top30
{
	margin-top:30px;
}
.bg_cc
{
	background-color:#ccc !important
}
.panel , .panel .panel-heading{border-radius:0px;}
.pad_0{padding:0px;}
table{margin:0px !important}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-offset-2 col-md-8 col-sm-8 col-sm-offset-2 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_cc">
					<h4 class="panel-title"><b>2016 AFFLIATED REGISTRATION DASHBOARD</b></h4>
				</div>
				<div class="panel-body">
					<div class="row">
					<div id="todayAndOverAllDetailsId"></div>
					<center><img id="dataLoadingsImgForOverAllDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">ZONE WISE</h4>
								</div>
								<div id="regionalWiseDetailsId"></div>
								<center><img id="dataLoadingsImgForRegionalWiseDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">REGIONAL WISE</h4>
								</div>
								<div id="zoneWiseDetailsId"></div>
								<center><img id="dataLoadingsImgForZoneWiseDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/js/custom.js" type="text/javascript"></script>

<script>

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
			var str='';
			
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">OVERALL</h4>';
					str+='</div>';
					str+='<div class="panel-body pad_0">';
						str+='<div class="table-responsive">';
							str+='<table class="table table-bordered">';
								str+='<thead>';
									str+='<th>Total</th>';
									str+='<th>Web</th>';
									str+='<th>Tab</th>';
									str+='<th>Online</th>';
								str+='</thead>';
								str+='<tbody>';
									str+='<tr>';
										if(result.totalCount != null)
											str+='<td>'+result.totalCount+'</td>';
										else
											str+='<td> 0 </td>';
										if(result.webCount != null)
											str+='<td>'+result.webCount+'</td>';
										else
											str+='<td> 0 </td>';
										if(result.tabCount != null)
											str+='<td>'+result.tabCount+'</td>';
										else
											str+='<td> 0 </td>';
										if(result.onlineCount != null)
											str+='<td>'+result.onlineCount+'</td>';
										else
											str+='<td> 0 </td>';
									str+='</tr>';
								str+='</tbody>';
							str+='</table>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">TODAY</h4>';
					str+='</div>';
					str+='<div class="panel-body pad_0">';
						str+='<div class="table-responsive">';
							str+='<table class="table table-bordered">';
								str+='<thead>';
									str+='<th>Total</th>';
									str+='<th>Web</th>';
									str+='<th>Tab</th>';
									str+='<th>Online</th>';
								str+='</thead>';
								str+='<tbody>';
									str+='<tr>';
										if(result.todayTotalCount != null)
											str+='<td>'+result.todayTotalCount+'</td>';
										else
											str+='<td> 0 </td>';
										if(result.todayWebCount != null)
											str+='<td>'+result.todayWebCount+'</td>';
										else
											str+='<td> 0 </td>';
										if(result.todayTabCount != null)
											str+='<td>'+result.todayTabCount+'</td>';
										else
											str+='<td> 0 </td>';
										if(result.todayOnlineCount != null)
											str+='<td>'+result.todayOnlineCount+'</td>';
										else
											str+='<td> 0 </td>';
									str+='</tr>';
								str+='</tbody>';
							str+='</table>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			$("#dataLoadingsImgForOverAllDetails").hide();
			$("#todayAndOverAllDetailsId").html(str);
		}
		else{
			$("#dataLoadingsImgForOverAllDetails").hide();
			$("#todayAndOverAllDetailsId").html("NO DATA AVAILABLE...");
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
		if(result != null){
			var str='';
			
			str+='<div class="panel-body pad_0">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered">';
						str+='<thead>';
							str+='<th>Zone Name</th>';
							str+='<th>Total</th>';
							str+='<th>Web</th>';
							str+='<th>Tab</th>';
							str+='<th>Online</th>';
						str+='</thead>';
						str+='<tbody>';
						if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
							for(var i in result.rtcUnionVoList1){
								str+='<tr>';
									str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].onlineCount+'</td>';
								str+='</tr>';
							}
						}
						else{
							$("#regionalWiseDetailsId").html("NO DATA AVAILABLE...");
						}
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		
			$("#dataLoadingsImgForRegionalWiseDetails").hide();
			$("#regionalWiseDetailsId").html(str);
		}
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
		if(result != null){
			var str='';
			
			str+='<div class="panel-body pad_0">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered">';
						str+='<thead>';
							str+='<th>Region Name</th>';
							str+='<th>Total</th>';
							str+='<th>Web</th>';
							str+='<th>Tab</th>';
							str+='<th>Online</th>';
						str+='</thead>';
						str+='<tbody>';
						if(result.rtcUnionVoList1 != null && result.rtcUnionVoList1.length > 0){
							for(var i in result.rtcUnionVoList1){
								str+='<tr>';
									str+='<td>'+result.rtcUnionVoList1[i].name+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].totalCount+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].webCount+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].tabCount+'</td>';
									str+='<td>'+result.rtcUnionVoList1[i].onlineCount+'</td>';
								str+='</tr>';
							}
						}
						else{
							$("#zoneWiseDetailsId").html("NO DATA AVAILABLE...");
						}
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
			
			$("#dataLoadingsImgForZoneWiseDetails").hide();
			$("#zoneWiseDetailsId").html(str);
		}
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